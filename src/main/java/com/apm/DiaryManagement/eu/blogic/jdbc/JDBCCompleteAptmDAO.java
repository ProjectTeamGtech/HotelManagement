package com.apm.DiaryManagement.eu.blogic.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Results;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.common.ApmDate;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.web.action.ProductAction;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.web.action.Tra;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;



public class JDBCCompleteAptmDAO extends JDBCBaseDAO implements CompleteAptmDAO {
	
		public JDBCCompleteAptmDAO(Connection connection){
				
				this.connection = connection;
			}

		public AppointmentType getAptmTypeCharge(String apmtType,
				AppointmentType appointmentType) {
			PreparedStatement preparedStatement = null;
			
			String sql = "SELECT id,duration,name,charges FROM apm_appointment_type where name = '"+apmtType+"' ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					appointmentType.setId(rs.getInt(1));
					appointmentType.setDuration(rs.getString(2));
					appointmentType.setName(rs.getString(3));
					appointmentType.setChargesx(DateTimeUtils.changeFormat((rs.getDouble(4))));
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return appointmentType;
		}

		public int saveCharge(CompleteAppointment completeAppointment,String apppointmentid,int loginid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			if(completeAppointment.getQuantity()==0){
				completeAppointment.setQuantity(1);
			}
			String sql = "insert into apm_patient_complete_apmt(user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,quantity,ipdid,masterchargetype,prodid,additioncharge_id,ginvstid,loginid,invpkgid,isshareablecharge,sharedrid,tax1,tax2,tax3,chargedescript,manualcharge,docid,session)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
			
				preparedStatement.setString(1, completeAppointment.getUser());
				
				AppointmentType appointmentType = new AppointmentType();
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				boolean checkInventoryChargeType = ipdDAO.checkInventoryChargeType(completeAppointment.getMasterchargetype());
				
				if(checkInventoryChargeType){
					if(!completeAppointment.getApmtType().equals("0")){
						//this code is added @rahul because in add charges charge name not show
						String appointmentTypeName = getAppointmentTypeName(completeAppointment.getApmtType());
						completeAppointment.setApmtType(appointmentTypeName);
						//End here
						boolean checkAppointmenTypeExist =  checkAppointmenTypeExist(completeAppointment.getApmtType());
						if(checkAppointmenTypeExist){
							 appointmentType = getAppointmentTypeData(completeAppointment.getApmtType());
						}else{
							
							appointmentType = getAppointmentTypeDetails(apppointmentid);
						}
					}else{
						appointmentType.setName(completeAppointment.getManualcharge());
						appointmentType.setCharges(completeAppointment.getCharges());
					}
				}
//					else{
//					appointmentType = getInventoryChargeDetails(completeAppointment.getApmtType());
//					if(completeAppointment.getApmtType().equals("0")){
//						appointmentType.setName(completeAppointment.getManualcharge());
//						appointmentType.setCharges(completeAppointment.getCharges());
//					}
//				}
			
				if(appointmentType.getName()==null){
					appointmentType.setName(completeAppointment.getManualcharge());
				}
				
				if(appointmentType.getCharges()==null){
					appointmentType.setCharges(completeAppointment.getCharges());
				}
				if(completeAppointment.getMasterchargetype()!=null){
					if(completeAppointment.getMasterchargetype().equals("Registration Charge")){
						if(completeAppointment.getManualcharge()!=null){
							if(!completeAppointment.getManualcharge().equals("")){
								appointmentType.setName(completeAppointment.getManualcharge());
								appointmentType.setCharges(completeAppointment.getCharges());
							}
						}
						
					}
				}
				
				preparedStatement.setString(2, appointmentType.getName());
				preparedStatement.setString(3, completeAppointment.getCharges());
				preparedStatement.setString(4, completeAppointment.getStartTime());
				preparedStatement.setString(5, completeAppointment.getDuration());
				preparedStatement.setString(6, completeAppointment.getPractitionerId());
				preparedStatement.setString(7, completeAppointment.getPractitionerName());
				preparedStatement.setString(8, completeAppointment.getClientId());
				preparedStatement.setString(9, completeAppointment.getCommencing());
				preparedStatement.setString(10, completeAppointment.getPayBuy());
				preparedStatement.setString(11, completeAppointment.getMarkAppointment());
				preparedStatement.setString(12, completeAppointment.getAppointmentid());
				preparedStatement.setDouble(13, completeAppointment.getQuantity());
				preparedStatement.setInt(14, completeAppointment.getIpdid());
				preparedStatement.setString(15, completeAppointment.getMasterchargetype());
				preparedStatement.setInt(16, completeAppointment.getProdid());
				preparedStatement.setString(17, completeAppointment.getAdditionalcharge_id());
				preparedStatement.setString(18, completeAppointment.getGinvstid());
				preparedStatement.setInt(19, loginid);
				preparedStatement.setInt(20, completeAppointment.getPkgid());
				preparedStatement.setString(21, completeAppointment.getIsindisharecharge());
				preparedStatement.setString(22, completeAppointment.getVisitingconsulatntdrid());
				preparedStatement.setString(23, completeAppointment.getTax1());
				preparedStatement.setString(24, completeAppointment.getTax2());
				preparedStatement.setString(25, completeAppointment.getTax3());
				preparedStatement.setString(26, completeAppointment.getChargedescription());
//				preparedStatement.setInt(27, completeAppointment.getTpkg());
				preparedStatement.setString(27, completeAppointment.getManuallyadded());
				preparedStatement.setString(28, completeAppointment.getDocid());
				preparedStatement.setString(29, completeAppointment.getSessionno());
				result = preparedStatement.executeUpdate();

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		
		
		public int updateProdStock(double rmainQty,
				String prodid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update inventory_product set stock="+rmainQty+" where id = "+prodid+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		public Product getInventoryProductDetails(String apmtType) {
			PreparedStatement preparedStatement = null;
			Product product = new Product();
			String sql = "SELECT stock,prodname,saleprice FROM inventory_product where id = "+apmtType+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					product.setStock(rs.getString(1));
					product.setProduct_name(rs.getString(2));
					product.setSale_price(rs.getString(3));
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return product;
		}

		private AppointmentType getInventoryChargeDetails(String apmtType) {
			PreparedStatement preparedStatement = null;
			AppointmentType appointmentType = new AppointmentType();
			String sql = "SELECT prodname,saleprice FROM inventory_product where id  = "+apmtType+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					appointmentType.setName(rs.getString(1));
					appointmentType.setCharges(rs.getString(2));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return appointmentType;
		}

		private AppointmentType getAppointmentTypeDetails(String apppointmentid) {
			PreparedStatement preparedStatement = null;
			AppointmentType appointmentType = new AppointmentType();
			String sql = "select apmttypetext,charge from apm_available_slot where id = "+apppointmentid+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					appointmentType.setName(rs.getString(1));
					appointmentType.setCharges(rs.getString(2));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return appointmentType;
		}

		private boolean checkAppointmenTypeExist(String apmtType) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			String sql  = "SELECT * FROM apm_appointment_type where name = '"+apmtType+"' ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		private AppointmentType getAppointmentTypeData(String apmtType) {
			PreparedStatement preparedStatement = null;
			AppointmentType appointmentType = new AppointmentType();
			String sql = "SELECT name,charges FROM apm_appointment_type where name= '"+apmtType+"' ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					appointmentType.setName(rs.getString(1));
					appointmentType.setCharges(rs.getString(2));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return appointmentType;
		}

		private String getCharge(String appointmentid) {
			String id = null;
			PreparedStatement preparedStatement = null;
			
			String sql = "select charges from apm_appointment_type where id = '"+appointmentid+"'";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					 id = rs.getString(1);
					}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return id;
		}

		public ArrayList<CompleteAppointment> getPatientChrageDetails(String id,String date,String apmtSlotId,int loginid) {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			//  24 jan 2018 back dated entry not shown
			//String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,quantity,masterchargetype,additioncharge_id FROM apm_patient_complete_apmt where clientId = "+id+" and commencing = '"+date+"' and appointmentid = "+apmtSlotId+" and loginid="+loginid+" ";
			//  27 jan 2018 showing all charge of all back date
			String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,quantity,masterchargetype,additioncharge_id,deletechargeid,tax1,tax2,tax3,docid,session FROM apm_patient_complete_apmt where clientId = "+id+" and appointmentid = "+apmtSlotId+" and loginid="+loginid+" ";
			double chargeTotal = 0;
			try{
				ChargesAccountProcessingDAO chargesAccountProcessingDAO= new  JDBCChargeAccountProcesDAO(connection);
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				double finaltaxtotal=0; 
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setUser(rs.getString(2));
					completeAppointment.setApmtType(rs.getString(3));
					
					
					completeAppointment.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(4))));
					completeAppointment.setStartTime(rs.getString(5));
					completeAppointment.setDuration(rs.getString(6));
					completeAppointment.setPractitionerId(rs.getString(7));
					completeAppointment.setPractitionerName(rs.getString(8));
					completeAppointment.setClientId(rs.getString(9));
					completeAppointment.setCommencing(rs.getString(10));
					double total = Double.parseDouble((rs.getString(4))) * rs.getInt(12);
					chargeTotal = chargeTotal + total;
					completeAppointment.setChargeTotal(chargeTotal);
					completeAppointment.setPayBuy(rs.getString(11));
					completeAppointment.setQuantity(rs.getInt(12));
					
					completeAppointment.setChargeTotalx(DateTimeUtils.changeFormat(chargeTotal));
					completeAppointment.setMasterchargetype(rs.getString(13));
					completeAppointment.setAdditionalcharge_id(rs.getString(14));
					completeAppointment.setCalCharge(String.valueOf(total));
					completeAppointment.setDeletechargeid(rs.getString(15));
					completeAppointment.setTax1(""+rs.getDouble(16));
					completeAppointment.setTax2(""+rs.getDouble(17));
					completeAppointment.setTax3(""+rs.getDouble(18));
					completeAppointment.setTotaltax(""+(rs.getDouble(16)+rs.getDouble(17)+rs.getDouble(18)));
					completeAppointment.setDocid(rs.getString(19));
					completeAppointment.setSessionno(rs.getString(20));
					int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(completeAppointment.getMasterchargetype());
					if(compulsary_consultant==1){
						completeAppointment.setApmtType(completeAppointment.getApmtType()+"-"+completeAppointment.getPractitionerName());
					}
					clientChargeListDetail.add(completeAppointment);
					
					completeAppointment.setTotaltaxamt(Math.round((Double.parseDouble(completeAppointment.getTotaltax())*rs.getDouble(4)/100.0)*rs.getDouble(12))+finaltaxtotal);
					finaltaxtotal=completeAppointment.getTotaltaxamt();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return clientChargeListDetail;
		}

		public CompleteAppointment getPatientDetails(String id,
				CompleteAppointment completeAppointment,String date,int loginid) {
			PreparedStatement preparedStatement = null;
			String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing FROM apm_patient_complete_apmt where clientId = "+id+" and commencing = '"+date+"' and loginid="+loginid+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setUser(rs.getString(2));
					completeAppointment.setApmtType(rs.getString(3));
					completeAppointment.setCharges(rs.getString(4));
					completeAppointment.setStartTime(rs.getString(5));
					completeAppointment.setDuration(rs.getString(6));
					completeAppointment.setPractitionerId(rs.getString(7));
					completeAppointment.setPractitionerName(rs.getString(8));
					completeAppointment.setClientId(rs.getString(9));
					completeAppointment.setCommencing(rs.getString(10));
					
					
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return completeAppointment;
		}

		public int deleteComplteApmt(int loginid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_patient_complete_apmt where loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
//				StringWriter sw = new StringWriter();
//		         PrintWriter pw = new PrintWriter(sw);
//		         e.printStackTrace(pw);
//				Tra.exceptionMail(sw.toString());
			}
			    
			return result;
		}

		public ArrayList<CompleteAppointment> getAssesmentList(String payBuy,int loginid) {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment>list = new ArrayList<CompleteAppointment>();
			String sql = "SELECT id,commencing,user,apmtType,charge FROM apm_patient_complete_apmt where paybuy= "+payBuy+" and loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setCommencing(rs.getString(2));
					completeAppointment.setUser(rs.getString(3));
					completeAppointment.setApmtType(rs.getString(4));
					completeAppointment.setCharges(rs.getString(5));
					
					
					
					list.add(completeAppointment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		public CompleteAppointment getInsuranceCompanyName(String clientId) {
			PreparedStatement preparedStatement = null;
			String result = "";
			CompleteAppointment completeAppointment = new CompleteAppointment();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT company_name,apm_third_party_details.address,name,apm_third_party_details.postcode,apm_third_party_details.telephone,apm_third_party_details.company_email FROM apm_third_party_details ");
			sql.append("inner join apm_patient on apm_patient.third_party_name_id = apm_third_party_details.id ");
			sql.append("and apm_patient.id = "+clientId+" ");
			
			try{
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					completeAppointment.setInsuranceCompanyName(DateTimeUtils.isNull(rs.getString(1)));
					completeAppointment.setInsuranceCompanyAddress(DateTimeUtils.isNull(rs.getString(2)));
					completeAppointment.setInsuranceCompanyOwnerName(DateTimeUtils.isNull(rs.getString(3)));
					completeAppointment.setThirdPartyPostcode(DateTimeUtils.isNull(rs.getString(4)));
					completeAppointment.setThirdPartyContacttno(DateTimeUtils.isNull(rs.getString(5)));
					completeAppointment.setThirdPartyemail(DateTimeUtils.isNull(rs.getString(6)));
					
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return completeAppointment;
		}

		public int saveAmpmInvoice(CompleteAppointment completeAppointment,int loginid,String userid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_invoice(clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location,policy_excess,apmt_charge_type,gpriscid,ginvstid,ipdid,standard_chargeid,std_charge_date,prepareuserid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int invoiceid = 0;
			String groupinvstid = "0";
			if(completeAppointment.getGinvstid()==null){
				completeAppointment.setGinvstid("0");
			}
			
			if(completeAppointment.getLocation()==null){
				completeAppointment.setLocation("1");
			}
			
			if(!completeAppointment.getGinvstid().equals("0")){
				groupinvstid = getGroupInvstid(completeAppointment.getClientId(),loginid);
			}
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, completeAppointment.getClientId());
				preparedStatement.setString(2, completeAppointment.getPractitionerId());
				preparedStatement.setString(3, completeAppointment.getUser());
				preparedStatement.setString(4, completeAppointment.getPractitionerName());
				preparedStatement.setString(5, completeAppointment.getInvoiceDate());
				preparedStatement.setString(6, completeAppointment.getChargeType());
				preparedStatement.setInt(7, Integer.parseInt(completeAppointment.getAppointmentid()));
				//String location = getLocation(completeAppointment.getAppointmentid());
				preparedStatement.setString(8, "1");
				preparedStatement.setString(9, completeAppointment.getPolicyExcess());
				preparedStatement.setString(10, completeAppointment.getIpd());
				preparedStatement.setString(11, completeAppointment.getGpriscid());
				preparedStatement.setString(12, groupinvstid);
				preparedStatement.setInt(13, completeAppointment.getIpdid());
				preparedStatement.setString(14, completeAppointment.getAdditionalcharge_id());
				preparedStatement.setString(15, completeAppointment.getInvoiceDate());
				preparedStatement.setString(16, userid);
				result = preparedStatement.executeUpdate();
				if(result==1){
					if(groupinvstid!=null){
						if(!groupinvstid.equals("")){
							InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
							int x= investigationDAO.setChargesRaisedStatus(groupinvstid);
						}
					}
				}
				if(result == 1){
					ResultSet resultSet = preparedStatement.getGeneratedKeys();
					if(resultSet.next()){
						invoiceid = resultSet.getInt(1);  
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
//				StringWriter sw = new StringWriter();
//		         PrintWriter pw = new PrintWriter(sw);
//		         e.printStackTrace(pw);
//				Tra.exceptionMail(sw.toString());
			}
			return invoiceid;
		}

		private String getGroupInvstid(String clientid,int loginid) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT ginvstid FROM apm_patient_complete_apmt where clientid = "+clientid+" and loginid="+loginid+" group by ginvstid ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = result + rs.getString(1) + ",";
				}
				
				result = result.substring(0,result.length()-1);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		private String getLocation(String appointmentid) {
			String location = null;
			PreparedStatement preparedStatement = null;
			
			String sql = "select location from apm_available_slot where id = "+appointmentid+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					location = rs.getString(1);
					}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return location;
		}

		public ArrayList<CompleteAppointment> getAssesmentList(String payBuy,
				String totalassesment,int loginid) {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment>list = new ArrayList<CompleteAppointment>();
			
			String temp[] = totalassesment.split(",");
			
			String sql = "";
			if(temp.length > 1){
				sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid FROM apm_patient_complete_apmt where paybuy= "+payBuy+" and id in("+totalassesment+") and loginid="+loginid+" ";
			}else{
				sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid FROM apm_patient_complete_apmt where paybuy= "+payBuy+" and loginid="+loginid+" ";
			}
			 
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setUser(rs.getString(2));
					completeAppointment.setApmtType(rs.getString(3));
					completeAppointment.setCharges(rs.getString(4));
					completeAppointment.setStartTime(rs.getString(5));
					completeAppointment.setDuration(rs.getString(6));
					completeAppointment.setPractitionerId(rs.getString(7));
					completeAppointment.setPractitionerName(rs.getString(8));
					completeAppointment.setClientId(rs.getString(9));
					completeAppointment.setCommencing(rs.getString(10));
					completeAppointment.setPayBuy(rs.getString(11));
					completeAppointment.setMarkAppointment(rs.getString(12));
					completeAppointment.setAppointmentid(rs.getString(13));
					
					
					
					list.add(completeAppointment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
			
		}

		
		
		public int saveInvoiceAssesment(CompleteAppointment completeAppointment, int invoice) throws Exception {
			PreparedStatement preparedStatement = null;
			int result = 0;
			
			String clientId = completeAppointment.getClientId();
			String admissionid = "";
			
			//set department to charges
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			String practionerId = "0";
			String condition = "0";
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			boolean islmh = userProfileDAO.getIsLMH();
			
			if(islmh){
				CompleteAppointment appointment = getChargeDiscDetails(completeAppointment.getChargeId());
				if(appointment.getDiscountShare()>0){
					double qty =completeAppointment.getQuantity();
					double discDiff = (DateTimeUtils.convertToDouble(appointment.getOriginalAmt()) *qty)  - (DateTimeUtils.convertToDouble(completeAppointment.getCharges()) *qty);
					completeAppointment.setChargedisc(""+discDiff);				
					completeAppointment.setUnitcharge(appointment.getOriginalAmt());
					completeAppointment.setDiscperc(""+appointment.getDiscountShare());
				}
			}
			
			//get ipd details
			int bedid = assessmentFormDAO.getIpdBedno(clientId);
			if(bedid!=0){
				BedDao bedDao=new JDBCBedDao(connection);
				admissionid = assessmentFormDAO.getAdmissionid(clientId);
				Bed bed = bedDao.getEditIpdData(admissionid);
				if(!completeAppointment.isIsfrommodify()){
					completeAppointment.setWardid(bed.getWardid());
				}
				practionerId = bed.getPractitionerid();
				if(practionerId==null){
					practionerId = "0";
				}
			    UserProfile	userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
				condition = userProfile.getDiciplineName();
			}else{
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				NotAvailableSlot notAvailableSlot =new NotAvailableSlot();
				if(islmh){
					notAvailableSlot=clientDAO.getLastDepartmentAppointmentdetails(clientId);
				}else{
					notAvailableSlot=clientDAO.getLastAppointmentdetails(clientId);	
				}
				if(notAvailableSlot.getDiaryUserId()!=0){
					practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
					completeAppointment.setAppointmentid(""+notAvailableSlot.getId()+"");
				}
				
				//check if doctor placed with machine
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
				if(userProfile.getJobgroup()==null){
					userProfile.setJobgroup("");
				}
				if(userProfile.getJobgroup().equals("4")){
					practionerId = userProfile.getDoctor();
				}
				if(practionerId==null){
					practionerId="0";
				}
				userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
				condition = userProfile.getDiciplineName();
				if(practionerId.equals("0")){
					condition = "0";
				}
			}
			IpdDAO ipdDAO1= new JDBCIpdDAO(connection);
			int ipdid=ipdDAO1.getLastIpdId(clientId);
			int pkgstr = accountsDAO.getTpkgId(clientId,ipdid,"apm_patient_complete_apmt");
			if(pkgstr!=0){
				int pkgids=getparentpkgid(clientId, String.valueOf(ipdid));
				String mastername=getPackageName(pkgids);
				completeAppointment.setMasterchargetype(mastername);
			}
			ChargesAccountProcessingDAO chargesAccountProcessingDAO=new  JDBCChargeAccountProcesDAO(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(completeAppointment.getMasterchargetype());
			
			boolean ispkg = false;
			Client client = clientDAO.getClientDetails(completeAppointment.getClientId());
			boolean ispkgvalid = false;
			//String tablename = "apm_invoice_assesments";
			String tablename="apm_invoice_assesments";
			int appliedpkgid = 0;
			Master m = new Master();
			if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
				appliedpkgid = getAppliedPkgid(completeAppointment.getClientId());
				ispkg = checkifpkg(completeAppointment.getMasterchargetype());
				if(ispkg){
					appliedpkgid = getpatientparentid(completeAppointment.getClientId(),String.valueOf(ipdid));
				}
				if(completeAppointment.getTpkg()!=0){
					m = getAppliedPkgData(completeAppointment.getTpkg());
				}else{
					m = getAppliedPkgData(appliedpkgid);
				}
				if(ispkg==false){
					ispkgvalid = checkifpkvalid(m.getFdate(),m.getTdate());
					if(ispkgvalid){
						if(!completeAppointment.getMasterchargetype().equalsIgnoreCase("IMPLANTS/CONSUMABLES")){
							tablename = "tp_invoice_assesments";
						}
					}
				}
			}else{
				appliedpkgid = getAppliedPkgid(completeAppointment.getClientId());
				ispkg = checkifpkg(completeAppointment.getMasterchargetype());
				if(ispkg){
					appliedpkgid = getpatientparentid(completeAppointment.getClientId(),String.valueOf(ipdid));
				}
				if(completeAppointment.getTpkg()!=0){
					m = getAppliedPkgData(completeAppointment.getTpkg());
				}else{
					m = getAppliedPkgData(appliedpkgid);
				}
				if(ispkg==false){
					ispkgvalid = checkifpkvalid(m.getFdate(),m.getTdate());
					if(ispkgvalid){
						tablename = "tp_invoice_assesments";
					}
				}
			}
			
			String sql = "insert into "+tablename+" (user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,invoiceid,thirdPartyId,quantity,ipdid,masterchargetype,std_charge_id,wardid,chargeId,backDate,stdflag,logid,department,invpkgid,chargedisc,unitcharge,isshareablecharge,sharedrid,tpcommencing,pkgcode,sqno,discrs,discper,inves_reqid,tpkg,invoiced,tax1,tax2,tax3,chargedescript,manualcharge,bedid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String pkgcode="0";
			int sqno = 0;
			String masterchname = "";
			try{
				String payBuy = "0";
				if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
					payBuy = "1";
				}
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, completeAppointment.getUser());
				if(compulsary_consultant==1){
					preparedStatement.setString(2, completeAppointment.getApmtType()+" - "+completeAppointment.getPractitionerName());	
				}else{
					preparedStatement.setString(2, completeAppointment.getApmtType());
				}
				double tatalamt=0;
				if(completeAppointment.getTotaltax()!=null){
					if(!completeAppointment.getTotaltax().equals("")){
						double taxmat=(Double.parseDouble(completeAppointment.getTotaltax())*Double.parseDouble( completeAppointment.getCharges()))/100;
						taxmat= Math.round(taxmat);
						tatalamt=taxmat+Double.parseDouble(completeAppointment.getCharges());
						tatalamt= Math.round(tatalamt);
					}
				}
				if(tatalamt!=0){
					preparedStatement.setString(3, ""+tatalamt);
				}else{
					preparedStatement.setString(3, completeAppointment.getCharges());
				}
				preparedStatement.setString(4, completeAppointment.getStartTime());
				preparedStatement.setString(5, completeAppointment.getDuration());
				preparedStatement.setString(6, completeAppointment.getPractitionerId());
				preparedStatement.setString(7, completeAppointment.getPractitionerName());
				preparedStatement.setString(8, completeAppointment.getClientId());
				preparedStatement.setString(9, completeAppointment.getCommencing());
				preparedStatement.setString(10, payBuy);
				preparedStatement.setString(11, completeAppointment.getMarkAppointment());
				preparedStatement.setString(12, completeAppointment.getAppointmentid());
				preparedStatement.setInt(13, invoice);
				
				if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
					String thirdPartyId = getThirdPartyId(completeAppointment.getClientId());
					preparedStatement.setString(14,thirdPartyId);
					if(!ipdDAO.checkifMainTp(thirdPartyId)){
						String temptpid= ipdDAO.getFollowerTp(thirdPartyId); 
						if(temptpid!=null){
							if(!temptpid.equals("0")){
								thirdPartyId=temptpid;  
								String chargeid = getSelectedChargeId(thirdPartyId,completeAppointment.getMasterchargetype(),completeAppointment.getApmtType());
								//completeAppointment.setChargeId(chargeid);
							}
						}
					}else{
						String chargeid = getSelectedChargeId(thirdPartyId,completeAppointment.getMasterchargetype(),completeAppointment.getApmtType());
						//completeAppointment.setChargeId(chargeid);
					}
					//tp package
					ispkg = checkifpkg(completeAppointment.getMasterchargetype());
					if(ispkg){
						pkgcode = getPkgServiceCode(m.getParentid(),completeAppointment.getApmtType());
						String fdate="",todate="";
						if(m.getFdate()!=null){
							fdate = m.getFdate().split(" ")[0];
							todate = m.getTdate().split(" ")[0];
						}
						masterchname = completeAppointment.getMasterchargetype();
						completeAppointment.setMasterchargetype("SURGICAL MANAGEMENT"  + " ("+DateTimeUtils.getCommencingDate1(fdate)+" - "+DateTimeUtils.getCommencingDate1(todate)+")");
					}
					 
					if(completeAppointment.getApmtType().equals("OT Charge")){
						completeAppointment.setMasterchargetype("IP / OP PROCEDURE");
					}
				}else{
					String chargeid = getSelectedChargeId("",completeAppointment.getMasterchargetype(),completeAppointment.getApmtType());
					//completeAppointment.setChargeId(chargeid);
					//tp package
					ispkg = checkifpkg(completeAppointment.getMasterchargetype());
					if(ispkg){
						pkgcode = getPkgServiceCode(m.getParentid(),completeAppointment.getApmtType());
						String fdate="",todate="";
						if(m.getFdate()!=null){
							fdate = m.getFdate().split(" ")[0];
							todate = m.getTdate().split(" ")[0];
						}
						if(!completeAppointment.isIskunal()){
							masterchname = completeAppointment.getMasterchargetype();
							completeAppointment.setMasterchargetype(masterchname  + " ("+DateTimeUtils.getCommencingDate1(fdate)+" - "+DateTimeUtils.getCommencingDate1(todate)+")");
						}
					}
					if(completeAppointment.getApmtType().equals("OT Charge")){
						completeAppointment.setMasterchargetype("IP / OP PROCEDURE");
					}
					preparedStatement.setString(14,"0");
				}
				preparedStatement.setDouble(15, completeAppointment.getQuantity());
				preparedStatement.setString(16, admissionid);
				if(ispkg){
					if(admissionid.equals("")){
						preparedStatement.setString(16, String.valueOf(ipdid));
					}
				}
				//change investigation mastercharge type
				if(completeAppointment.getMasterchargetype().equals("INVESTIGATION")){
					String department = investigationDAO.getInvdepartment(completeAppointment.getApmtType());
					completeAppointment.setMasterchargetype(department);
				}
				preparedStatement.setString(17, completeAppointment.getMasterchargetype());
				preparedStatement.setString(18, completeAppointment.getAdditionalcharge_id());
				preparedStatement.setString(19, completeAppointment.getWardid());
				preparedStatement.setString(20, completeAppointment.getChargeId());
				preparedStatement.setString(21, completeAppointment.getBackDate());
				preparedStatement.setString(22, completeAppointment.getStdflag());
				preparedStatement.setString(23, completeAppointment.getLogid());
				preparedStatement.setString(24, condition);
				preparedStatement.setInt(25, completeAppointment.getPkgid());
				if(completeAppointment.getChargedisc()==null){
					completeAppointment.setChargedisc("0");
				}
				preparedStatement.setString(26, completeAppointment.getChargedisc());
				preparedStatement.setString(27, completeAppointment.getUnitcharge());
				preparedStatement.setString(28, completeAppointment.getIsindisharecharge());
				preparedStatement.setString(29, completeAppointment.getVisitingconsulatntdrid());
				preparedStatement.setString(30, DateTimeUtils.getUKCurrentDataTime("IST"));
				String currenttime=DateTimeUtils.getUKCurrentDataTime("IST").split(" ")[1];
				String tpcommencing=completeAppointment.getCommencing()+" "+currenttime;
				preparedStatement.setString(30, tpcommencing);
				preparedStatement.setString(31, pkgcode);
				if(ispkg){
					sqno = 11;
				}else{
					sqno = getServiceSqno(completeAppointment.getMasterchargetype());
				}
				preparedStatement.setInt(32, sqno);
				preparedStatement.setString(33, completeAppointment.getDisc_amount());
				preparedStatement.setString(34, completeAppointment.getDiscperc());
				preparedStatement.setString(35, completeAppointment.getInvestigation_request_id());
				preparedStatement.setInt(36, completeAppointment.getTpkg());
				preparedStatement.setInt(37, completeAppointment.getInvoiced());
				preparedStatement.setString(38, completeAppointment.getTax1());
				preparedStatement.setString(39, completeAppointment.getTax2());
				preparedStatement.setString(40, completeAppointment.getTax3());
				preparedStatement.setString(41, completeAppointment.getChargedescription());
				preparedStatement.setString(42, completeAppointment.getManuallyadded());
				preparedStatement.setString(43, completeAppointment.getBedid());
				result = preparedStatement.executeUpdate();
				
				if(result>0){
					ResultSet rs= preparedStatement.getGeneratedKeys();
					while(rs.next()){
						result =rs.getInt(1);
						if(ispkg){
							/*int pkgid = getApprovedPackegeId(completeAppointment.getMasterchargetype());
							Master master = getPkgDetails(admissionid,pkgid);*/
							if(completeAppointment.getTpkg()!=0){
								int u = updateChargePkgDetails(completeAppointment.getTpkg(),m,result,tablename);	
							}else{
								int u = updateChargePkgDetails(appliedpkgid,m,result,tablename);
							}
						}
						if(ispkgvalid){
							Master master =new Master();
							appliedpkgid = getAppliedPkgid(completeAppointment.getClientId());
							if(completeAppointment.getTpkg()!=0){
								master = getAppliedPkgData(completeAppointment.getTpkg());
							}else{
								master = getAppliedPkgData(appliedpkgid);
							}
							int u = updateChargePkgDetails(master.getId(),master,result,tablename);
						}
					}
				}
				if (appliedpkgid>0) {
					if(!completeAppointment.getMasterchargetype().equals("IMPLANTS/CONSUMABLES")){
						swapPkgChargeList(m.getFdate(),m.getTdate(),String.valueOf(ipdid),appliedpkgid,completeAppointment.getClientId());
						deletefromapmminvoiceassessment(m.getFdate(),m.getTdate(),String.valueOf(ipdid),completeAppointment.getClientId());
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
//				StringWriter sw = new StringWriter();
//		         PrintWriter pw = new PrintWriter(sw);
//		         e.printStackTrace(pw);
//				Tra.exceptionMail(sw.toString());
			}
			
			return result;
		}

		
		

		

		

		public CompleteAppointment getChargeDiscDetails(String chargeId) {
			CompleteAppointment appointment = new CompleteAppointment();
			try {
				String sql="select discountShare,originalAmt,charges from apm_appointment_type where id='"+chargeId+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					appointment.setDiscountShare(rs.getDouble(1));
					appointment.setOriginalAmt(rs.getString(2));
					appointment.setCharges(rs.getString(3));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return appointment;
		}

		private int updateAppointmentChargeStatus(String appointmentid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_available_slot set chrgstatus=1 where id = "+appointmentid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		private int getpatientparentid(String clientId, String admissionid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql ="";
			if(admissionid!=null){
				if(!admissionid.equals("")){
					sql = "select max(id) from apm_parent_patient_package where clientid = "+clientId+" and ipdid = "+admissionid+" ";
				}else{
					sql = "select max(id) from apm_parent_patient_package where clientid = "+clientId+"  ";
				}
			}else{
				sql = "select max(id) from apm_parent_patient_package where clientid = "+clientId+"  ";
			}
			 
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		private int getServiceSqno(String masterchargetype) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "select sqno from apm_newchargetype where name = '"+masterchargetype+"' ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		private String getPkgServiceCode(String parentid, String apmtType) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "select tpcode from his_child_package where packageid = "+parentid+" and chargename = '"+apmtType+"' ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getString(1);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return result;
		}

		private boolean checkifpkvalid(String fdate, String tdate) {
			boolean result = false;
			
			try{
				String fromdate = fdate;
				String cdate = fromdate.split(" ")[0];
				String ctime = fromdate.split(" ")[1];
				String hh = ctime.split(":")[0];
				String mm = ctime.split(":")[1];
				fromdate = cdate + " " + hh + ":" + mm + ":" + "00.0";

				
				String todate = tdate;
			    cdate = todate.split(" ")[0];
				ctime = todate.split(" ")[1];
				 hh = ctime.split(":")[0];
				 mm = ctime.split(":")[1];
				todate = cdate + " " + hh + ":" + mm + ":" + "00.0";

				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				
				Date dt1 = sdf.parse(fromdate);
			  		//System.out.println("fromdate:"+dt1);

				Calendar cal1 = Calendar.getInstance();
		  		cal1.setTime(dt1);
		  		//cal.add(Calendar.HOUR, 36);
//		  		System.out.println("fromdate: "+cal1.getTime());
		  
		  		
		  		//to date
		  		Date dt2 = sdf.parse(todate);
		  		//System.out.println("fromdate:"+dt1);

			Calendar cal2 = Calendar.getInstance();
	  		cal2.setTime(dt2);
	  		//ca2.add(Calendar.HOUR, 36);
//	  		System.out.println("todate: "+cal2.getTime());
	  		
		  		
				
				//current date
				String newDateString = sdf.format(GregorianCalendar.getInstance().getTime());
				 ctime = newDateString.split(" ")[1];
				 cdate = newDateString.split(" ")[0];
				 hh = ctime.split(":")[0];
				 mm = ctime.split(":")[1];
						
						
				newDateString = cdate + " " + hh + ":" + mm + ":" + "00.0";
				
		  		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		  		Date dt3 = sdf.parse(newDateString);
		  		//System.out.println("new date to compare with reference date : "+newDate);

		  		Calendar cal3 = Calendar.getInstance();
		  		cal3.setTime(dt3);
//		  		System.out.println("currenr date : " + cal3.getTime());
		  		
		  		if(cal3.after(cal1) && cal3.before(cal2)){
		  		    result = true;
		  		}

				
			}catch (Exception e) {
				// TODO: handle exception
			}

			
			return result;
		}

		private Master getAppliedPkgData(int appliedpkgid) {
			PreparedStatement preparedStatement = null;
			Master master = new Master();
			String sql = "select fromdate,todate,id,packageid from apm_parent_patient_package where id = "+appliedpkgid+" ";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					master.setFdate(rs.getString(1));
					master.setTdate(rs.getString(2));
					master.setId(rs.getInt(3));
					master.setParentid(rs.getString(4));
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return master;
		}

		private int getAppliedPkgid(String clientid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "SELECT tpkg from apm_invoice_assesments where "
					+ " clientid = "+clientid+" and tpkg !=0 order by id desc limit 0,1 ";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return result;
		}

		private int updateChargePkgDetails(int pkgid, Master master, int id,String tablename) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update "+tablename+" set "
					+ " tpkg="+pkgid+",tpfdate='"+master.getFdate()+"',tptodate='"+master.getTdate()+"' "
							+ " where id="+id+" ";
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
					
			return result;
		}

		private Master getPkgDetails(String admissionid, int pkgid) {
			PreparedStatement preparedStatement = null;
			Master master = new Master();
			String sql = "select fromdate,todate, id from apm_parent_patient_package where packageid = "+pkgid+" and ipdid = "+admissionid+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					master.setFdate(rs.getString(1));
					master.setTdate(rs.getString(2));
					master.setId(rs.getInt(1));
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return master;
		}

		private int getApprovedPackegeId(String masterchargetype) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "SELECT id FROM his_parent_package where name = '"+masterchargetype+"' ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		private boolean checkifpkg(String masterchargetype) {
			PreparedStatement preparedStatement = null;
			boolean result  = false;
			String sql = "SELECT * FROM his_parent_package where name = '"+masterchargetype+"' ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		private String getThirdPartyId(String clientId) {
			String id = null;
			PreparedStatement preparedStatement = null;
			
			String sql = "select third_party_name_id from apm_patient where id = "+clientId+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					 id = rs.getString(1);
					}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return id;
		}

		public ThirdParty getThirdParty(String clientId, ThirdParty thirdParty) {
			PreparedStatement preparedStatement = null;
			
			String sql = "SELECT third_party_id from apm_patient where id = "+clientId+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					thirdParty.setThirdPartyId(rs.getInt(1));
					String company = getThirdPartycomanyName(rs.getInt(1));
					thirdParty.setCompanyName(company);
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return thirdParty;
		}

		private String getThirdPartycomanyName(int int1) {
			String company = null;
			PreparedStatement preparedStatement = null;
			
			String sql = "select company_name from apm_third_party_details where third_party_id = "+int1+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					
					company = rs.getString(1);
					}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return company;
		}

		public int deleteCash(int id, CompleteAppointment completeAppointment,int loginid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_patient_complete_apmt where id = "+id+" and loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public String getAppointmentTypeName(String apmtTypeid) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT name FROM apm_appointment_type where id = "+apmtTypeid+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = rs.getString(1);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return result;
		}

		public boolean checkAppointTypeExist(String apmtType,String appointmentid,int loginid) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			String sql = "select * from apm_patient_complete_apmt where apmtType = '"+apmtType+"' and appointmentid = "+appointmentid+" and loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int deleteComplteApmt(int appointmentid,int loginid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_patient_complete_apmt where appointmentid = "+appointmentid+" and loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
			return result;
		}

		public ArrayList<CompleteAppointment> getCompleteApmtList(
				String appointmentid,int loginid) {
		
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment>list = new ArrayList<CompleteAppointment>();
			
			String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,quantity,ipdid,masterchargetype,prodid,additioncharge_id,isshareablecharge,sharedrid FROM apm_patient_complete_apmt where appointmentid = "+appointmentid+" and loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setUser(rs.getString(2));
					completeAppointment.setApmtType(rs.getString(3));
					completeAppointment.setCharges(rs.getString(4));
					completeAppointment.setStartTime(rs.getString(5));
					completeAppointment.setDuration(rs.getString(6));
					completeAppointment.setPractitionerId(rs.getString(7));
					completeAppointment.setPractitionerName(rs.getString(8));
					completeAppointment.setClientId(rs.getString(9));
					completeAppointment.setCommencing(rs.getString(10));
					completeAppointment.setPayBuy(rs.getString(11));
					completeAppointment.setMarkAppointment(rs.getString(12));
					completeAppointment.setAppointmentid(rs.getString(13));
					completeAppointment.setQuantity(rs.getInt(14));
					completeAppointment.setIpdid(rs.getInt(15));
					completeAppointment.setMasterchargetype(rs.getString(16));
					completeAppointment.setProdid(rs.getInt(17));
					completeAppointment.setChargeId(rs.getString(18));
					completeAppointment.setIsindisharecharge(rs.getString(19));
					completeAppointment.setVisitingconsulatntdrid(rs.getString(20));
					list.add(completeAppointment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		public int updateInvoive(String invoiceid, String action,
				String date) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_invoice set date=?,chargetype=? where id=?";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, date);
				preparedStatement.setString(2, action);
				preparedStatement.setString(3, invoiceid);
				
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			return result;
		}

		public int updateTreatmentEpisodeSession(int tratmentepisodeid,
				int treatmenntsessions) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_treatment_episode set usedsession = "+treatmenntsessions+" where id = "+tratmentepisodeid+" ";
			
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			return result;
		}

		
		public int getSelfInvoiceTotal(String invoiceid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "SELECT sum(charge) FROM apm_invoice_assesments where invoiceid="+invoiceid+" and paybuy=0";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return result;
		}

		
		public int savePaymentForInvoice(Accounts accounts,String discount) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_invoice_payment(invoice_id,last_update_date,payby,pay_mode,amount,clientId,payBuy,thirdPatryId,discount) values(?,?,?,?,?,?,?,?,?) ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, accounts.getInvoiceid());
				preparedStatement.setString(2, accounts.getCommencing());
				preparedStatement.setString(3, accounts.getClientName());
				preparedStatement.setString(4, accounts.getHowPaid());
				preparedStatement.setDouble(5, accounts.getPayAmount());
				preparedStatement.setInt(6, accounts.getClientid());
				preparedStatement.setString(7, "0");
				preparedStatement.setString(8, "0");
				preparedStatement.setString(9, discount);
				
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
					
			return result;
		}

		public int updateCharge(CompleteAppointment completeAppointment,
				String apppointmentid) {
			// TODO Auto-generated method stub
			return 0;
		}

		public ArrayList<CompleteAppointment> getPendingApmtCharges(
				String practitioner, String date, String location,Pagination pagination) {
			PreparedStatement preparedStatement = null;
			
			ArrayList<CompleteAppointment>list = new ArrayList<CompleteAppointment>();
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
			String today = formatter.format(currentDate.getTime());
			String sql = "SELECT id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,charge,treatmentEpisodeId,clientId,duration,dna,apmttypetext from apm_available_slot where diaryuserid like('%"+practitioner+"%') and commencing like('%"+date+"%') and location like('%"+location+"%') and commencing <='"+today+"' order by commencing and starttime " ;
			//sql = pagination.getSQLQuery(sql);
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();

					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setApmtSlotId(rs.getInt(2));
					completeAppointment.setCommencing(rs.getString(3));
					completeAppointment.setStartTime(rs.getString(4));
					completeAppointment.setEndTime(rs.getString(5));
					completeAppointment.setNotes(rs.getString(6));
					completeAppointment.setDiaryUserId(rs.getInt(7));
					completeAppointment.setDiaryUser(rs.getString(8));
					completeAppointment.setDept(rs.getString(9));
					String locationName = getLocationName(rs.getString(10));
					completeAppointment.setLocation(locationName);
					completeAppointment.setRoom(rs.getString(11));
					completeAppointment.setClient(rs.getString(12));
					completeAppointment.setApmtType(rs.getString(13));
					completeAppointment.setCharges(rs.getString(14));
					completeAppointment.setTreatmentEpisodeId(rs.getString(15));
					completeAppointment.setClientId(rs.getString(16));
					completeAppointment.setDuration(rs.getString(17));
					completeAppointment.setDna(rs.getString(18));
					completeAppointment.setApmtypeText(rs.getString(19));
					String apmtTypeId = getAppointmentTypeId(rs.getString(13));
					
					
					completeAppointment.setAppointmentTypeId(apmtTypeId);
					//check appointment completed
					boolean isCompleted = checkAppointmentCompleted(rs.getInt(1));
					
					
					if(isCompleted){
					
					}
					else{
						list.add(completeAppointment);
					}
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		private String getLocationName(String id) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT location FROM apm_clinic_location where id = "+id+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getString(1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		private String getAppointmentTypeId(String apmt) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT id FROM apm_appointment_type where name = '"+apmt+"'";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = rs.getString(1);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return result;
			
		}

		private boolean checkAppointmentCompleted(int id) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			String sql = "SELECT * FROM apm_invoice_assesments where appointmentid = "+id+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int getTotalPendingApmtChargesCount(String practitioner,
				String date, String location) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			
			
			String sql = "SELECT count(*) from apm_available_slot where diaryuserid like('%"+practitioner+"%') and commencing like('%"+date+"%') and location like('%"+location+"%')";

			try{
				preparedStatement = connection.prepareStatement(sql);
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = rs.getInt(1);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public ArrayList<Accounts> getPayByList(String appointmentid,int loginid) {
			PreparedStatement preparedStatement = null;
			ArrayList<Accounts> list = new ArrayList<Accounts>();
			String sql = "SELECT payBuy FROM apm_patient_complete_apmt where appointmentid="+appointmentid+" and loginid="+loginid+" group by payBuy;";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Accounts accounts = new  Accounts();
					accounts.setPayBy(rs.getInt(1));
					list.add(accounts);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return list;
		}

		public ArrayList<CompleteAppointment> getSelfPatientChrageDetails(
				String id) {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			String sql = "SELECT id,apmtType,charge,paybuy FROM apm_invoice_assesments where invoiceid = "+id+" and paybuy =0";
			double chargeTotal = 0;
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					
					completeAppointment.setApmtType(rs.getString(2));
					completeAppointment.setCharges(rs.getString(3));
					
					double total = Double.parseDouble((rs.getString(3)));
					chargeTotal = chargeTotal + total;
					completeAppointment.setChargeTotal(chargeTotal);
					completeAppointment.setPayBuy(rs.getString(4));
					
					clientChargeListDetail.add(completeAppointment);
					
					
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return clientChargeListDetail;
		}

		public int updateInvoiceStatus(String invoiceId, String invoicenotes) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_invoice set chargetype=?,submitnotes=? where id=?";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "Submit");
				preparedStatement.setString(2, invoicenotes);
				preparedStatement.setString(3, invoiceId);
				
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			return result;
		}

		public int updateInvoiceStatus1(String invoiceid,String discount) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_invoice set chargetype = ?,discount = ? where id=?";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "Invoice");
				preparedStatement.setString(2, discount);
				preparedStatement.setString(3, invoiceid);
				
				
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			return result;
		}
		
public ArrayList<CompleteAppointment> getCompleteApmtData(String id) {
			

			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment> detailsList = new ArrayList<CompleteAppointment>();
			
			String sql = "SELECT id,commencing,starttime,diaryuserid,diaryusername,location,clientId,clientname,aptmtype,duration,apmttypetext,treatmentEpisodeId,charge,usedsession,whopay from apm_available_slot where id = "+id+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setCommencing(rs.getString(2));
					completeAppointment.setStartTime(rs.getString(3));
					completeAppointment.setPractitionerId(rs.getString(4));
					completeAppointment.setPractitionerName(rs.getString(5));
					String locationid = getInvoiceLocationid(id,rs.getString(15));
					completeAppointment.setLocationid(locationid);
					String locationname = getLocationName(rs.getString(6));
					completeAppointment.setLocation(locationname);
					completeAppointment.setClientId(rs.getString(7));
					completeAppointment.setClient(rs.getString(8));
					completeAppointment.setApmtType(rs.getString(9));
					completeAppointment.setDuration(rs.getString(10));
					completeAppointment.setApmtypeText(rs.getString(11));
					completeAppointment.setTreatmentEpisodeId(rs.getString(12));
					completeAppointment.setCharges(rs.getString(13));
					completeAppointment.setUsedSession(rs.getString(14));
					TreatmentEpisode treatmentEpisode = getDetails(rs.getString(12));
					completeAppointment.setTotalSession(treatmentEpisode.getSessions());
					completeAppointment.setPayBuy(treatmentEpisode.getPayby());
					completeAppointment.setInvoicee(treatmentEpisode.getInvoicee());
					
					TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
					String tpnotes = "";
					if(rs.getString(15).equals(Constants.PAY_BY_CLIENT)){
						tpnotes = treatmentEpisodeDAO.getClientNotes(completeAppointment.getClientId());
					}else{
						tpnotes = treatmentEpisodeDAO.getTPNotes(id);
					}
					
					completeAppointment.setTpNotes(tpnotes);
					
					detailsList.add(completeAppointment);
				
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return detailsList;
		}
		
		

		private String getInvoiceLocationid(String id, String payby) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT location FROM apm_invoice where   appointmentid = "+id+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getString(1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		private TreatmentEpisode getDetails(String id) {
			PreparedStatement preparedStatement = null;
			TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
			String sql = "SELECT sessions,payby,invoicee FROM apm_treatment_episode where id= "+id+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					treatmentEpisode.setSessions(rs.getString(1));
					treatmentEpisode.setPayby(rs.getString(2));
					treatmentEpisode.setInvoicee(rs.getString(3));
					
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return treatmentEpisode;
		}
		
		
		public ArrayList<CompleteAppointment> getModifiInvoiceAsessmentDetails(String id){
			
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,quantity,ipdid,masterchargetype,chargeId,chargedisc,unitcharge,isshareablecharge,sharedrid,discrs, discper,tpkg,invoiced,std_charge_id,tax1,tax2,tax3,chargedescript FROM apm_invoice_assesments where invoiceid = "+id+"";
			double chargeTotal = 0;
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setClient(rs.getString(2));
					completeAppointment.setApmtType(rs.getString(3));
					completeAppointment.setCharges(rs.getString(4));
					completeAppointment.setStartTime(rs.getString(5));
					completeAppointment.setDuration(rs.getString(6));
					completeAppointment.setPractitionerId(rs.getString(7));
					completeAppointment.setPractitionerName(rs.getString(8));
					completeAppointment.setClientId(rs.getString(9));
					completeAppointment.setCommencing(rs.getString(10));
					
					completeAppointment.setPayBuy(rs.getString(11));
					completeAppointment.setMarkAppointment(rs.getString(12));
					completeAppointment.setAppointmentid(rs.getString(13));
					completeAppointment.setQuantity(rs.getInt(14));
					completeAppointment.setIpdid(rs.getInt(15));
					completeAppointment.setMasterchargetype(rs.getString(16));
					completeAppointment.setChargeId(rs.getString(17));
					completeAppointment.setChargedisc(rs.getString(18));
					
					completeAppointment.setUnitcharge(rs.getString(19));
					if(rs.getString(19).equals("0")){
						completeAppointment.setUnitcharge(rs.getString(4));
					}
						
					completeAppointment.setIsindisharecharge(rs.getString(20));
					completeAppointment.setVisitingconsulatntdrid(rs.getString(21));
					completeAppointment.setDisc_amount(rs.getString(22));
					completeAppointment.setDiscperc(rs.getString(23));
					completeAppointment.setTpkg(rs.getInt(24));
					completeAppointment.setInvoiced(rs.getInt(25));
					completeAppointment.setStd_charge_id(rs.getString(26));
					
					completeAppointment.setTax1(""+rs.getDouble(27));
					completeAppointment.setTax2(""+rs.getDouble(28));
					completeAppointment.setTax3(""+rs.getDouble(29));
					completeAppointment.setChargedescription(""+DateTimeUtils.isNull(rs.getString(30)));
					
					clientChargeListDetail.add(completeAppointment);
					
					
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return clientChargeListDetail;
		}

		
		public ArrayList<CompleteAppointment> getAsessmentDetails(String id) {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			String sql = "SELECT id,apmtType,charge,paybuy,quantity,masterchargetype,chargeId FROM apm_invoice_assesments where appointmentid = "+id+" and active = 1";
			double chargeTotal = 0;
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setApmtType(rs.getString(2));
					completeAppointment.setCharges(rs.getString(3));
					double total = Double.parseDouble((rs.getString(3)))*rs.getInt(5);
					chargeTotal = chargeTotal + total;
					completeAppointment.setChargeTotal(chargeTotal);
					completeAppointment.setPayBuy(rs.getString(4));
					completeAppointment.setQuantity(rs.getInt(5));
					completeAppointment.setMasterchargetype(rs.getString(6));
					completeAppointment.setChargeId(rs.getString(7));
					clientChargeListDetail.add(completeAppointment);
					
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return clientChargeListDetail;
		}

		
		public int inActiveAsessment(int id) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_invoice_assesments set active = ? where id = "+id+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "0");
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			return result;
		}

		
		public int saveNewAsessment(String id,
				CompleteAppointment completeAppointment) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			
			String clientId = completeAppointment.getClientId();
			//set department to charges
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			String practionerId = "0";
			String condition = "0";
			
			//get ipd details
			int bedid = assessmentFormDAO.getIpdBedno(clientId);
			if(bedid!=0){
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				BedDao bedDao=new JDBCBedDao(connection);
				
				String admissionid = assessmentFormDAO.getAdmissionid(clientId);
				
				Bed bed = bedDao.getEditIpdData(admissionid);
				practionerId = bed.getPractitionerid();
			    UserProfile	userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
				
			    condition = userProfile.getDiciplineName();
				
			}else{
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
				if(notAvailableSlot.getDiaryUserId()!=0){
					practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
				}
				
				
				//check if doctor placed with machine
				
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
				
				if(userProfile.getJobgroup()==null){
					userProfile.setJobgroup("");
				}
				if(userProfile.getJobgroup().equals("4")){
					practionerId = userProfile.getDoctor();
				}
				
				if(practionerId==null){
					practionerId="0";
				}
				
				userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
				condition = userProfile.getDiciplineName();
				
				if(practionerId.equals("0")){
					condition = "0";
				}
				
			}
			
			String sql = "insert into apm_invoice_assesments (user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,invoiceid,thirdPartyId,department) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, completeAppointment.getClient());
				preparedStatement.setString(2, completeAppointment.getApmtypeText());
				preparedStatement.setString(3, completeAppointment.getCharges());
				preparedStatement.setString(4, completeAppointment.getStartTime());
				preparedStatement.setString(5, completeAppointment.getDuration());
				preparedStatement.setString(6, completeAppointment.getPractitionerId());
				preparedStatement.setString(7, completeAppointment.getPractitionerName());
				preparedStatement.setString(8, completeAppointment.getClientId());
				preparedStatement.setString(9, completeAppointment.getCommencing());
				preparedStatement.setString(10, completeAppointment.getPayBuy());
				preparedStatement.setString(11, "1");
				preparedStatement.setString(12, id);
				
				
				ArrayList<Accounts>apmInvoiceList = getApmInvoiceList(id);
				
				int selfInvoiceid = 0;
				int tpInvoiceid = 0;
				
				for(Accounts accounts : apmInvoiceList){
					
					if(apmInvoiceList.size() == 2){
						if(accounts.getPayBy()==0){
							selfInvoiceid = accounts.getId();
						}else{
							tpInvoiceid = accounts.getId();
						}
					}else{
						if(accounts.getPayBy()==0){
							selfInvoiceid = accounts.getId();
							if(completeAppointment.getPayBuy().equals("1")){
								tpInvoiceid = createInvoice(completeAppointment.getClientId(),completeAppointment.getPractitionerId(),completeAppointment.getClient(),completeAppointment.getPractitionerName(),completeAppointment.getCommencing(),id,accounts.getLocation());
							
								/*//logdata
								int resultlog = updateAmpmInvoice(completeAppointment.getClientId(),completeAppointment.getPractitionerId(),completeAppointment.getClient(),completeAppointment.getPractitionerName(),completeAppointment.getCommencing(),id,completeAppointment.getPayBuy(),tpInvoiceid);
							*/
							}
							
						}else{
							tpInvoiceid = accounts.getId();
							if(completeAppointment.getPayBuy().equals("0")){
								selfInvoiceid = createInvoice(completeAppointment.getClientId(),completeAppointment.getPractitionerId(),completeAppointment.getClient(),completeAppointment.getPractitionerName(),completeAppointment.getCommencing(),id,accounts.getLocation());
							
								/*//logdata
								int resultlog1 = updateAmpmInvoice(completeAppointment.getClientId(),completeAppointment.getPractitionerId(),completeAppointment.getClient(),completeAppointment.getPractitionerName(),completeAppointment.getCommencing(),id,completeAppointment.getPayBuy(),selfInvoiceid);
							*/
							}
						}
					}
					
					
				}
				
				if(completeAppointment.getPayBuy().equals("0")){
					preparedStatement.setInt(13, selfInvoiceid);
					preparedStatement.setString(14,"0");
					
					//logdata
					int resultlog = updateAmpmInvoice(completeAppointment.getClientId(),completeAppointment.getPractitionerId(),completeAppointment.getClient(),completeAppointment.getPractitionerName(),completeAppointment.getCommencing(),id,completeAppointment.getPayBuy(),selfInvoiceid);
				
					
				}else{
					String thirdPartyId = getThirdPartyId(completeAppointment.getClientId());
					preparedStatement.setInt(13, tpInvoiceid);
					preparedStatement.setString(14,thirdPartyId);
					
					//logdata
					int resultlog = updateAmpmInvoice(completeAppointment.getClientId(),completeAppointment.getPractitionerId(),completeAppointment.getClient(),completeAppointment.getPractitionerName(),completeAppointment.getCommencing(),id,completeAppointment.getPayBuy(),tpInvoiceid);
				
				}
				
				preparedStatement.setString(15, condition);
				
				/*String payBuy = completeAppointment.getPayBuy();
				if(payBuy.equalsIgnoreCase("1")){
					String thirdPartyId = getThirdPartyId(completeAppointment.getClientId());
					String invoiceId = getInvoiceId(id,payBuy,completeAppointment.getClientId());
					preparedStatement.setString(13, invoiceId);
					preparedStatement.setString(14,thirdPartyId);
					}
					else{
						String invoiceId = getInvoiceId(id,payBuy,completeAppointment.getClientId());
						if(invoiceId==null || invoiceId == ""){
								int invoiceId1 = createInvoice(completeAppointment.getClientId(),completeAppointment.getPractitionerId(),completeAppointment.getClient(),completeAppointment.getPractitionerName(),completeAppointment.getCommencing(),id);
								invoiceId = Integer.toString(invoiceId1);
						}
						preparedStatement.setString(13, invoiceId);
						preparedStatement.setString(14,"0");
					}*/
				
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}

		
		private int updateAmpmInvoice(String clientId, String practitionerId,String client, String practitionerName, String commencing,String id,String payby,int invoiceId) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String status = "Modified";
			String sql = "insert into apm_invoice_log(clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location,status,invoiceDate,invoiceId,payby) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			int invoiceid = 0;
			
			/*SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy hh:mm:ss");
			Calendar now = Calendar.getInstance();
			now.add(Calendar.HOUR, 5);
			now.add(Calendar.MINUTE, 30);
			String date = sdf.format(now.getTime());
			System.out.println(date);*/
			
			
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, clientId);
				preparedStatement.setString(2, practitionerId);
				preparedStatement.setString(3, client);
				preparedStatement.setString(4, practitionerName);
				preparedStatement.setString(5, commencing);
				preparedStatement.setString(6, "Charge");
				preparedStatement.setInt(7, Integer.parseInt(id));
				String location = getLocation(id);
				preparedStatement.setString(8, location);
				preparedStatement.setString(9, status);
				preparedStatement.setString(10, DateTimeUtils.getUKCurrentDataTime("Europe/London"));
				preparedStatement.setString(11, Integer.toString(invoiceId));
				preparedStatement.setString(12, payby);
				
				result = preparedStatement.executeUpdate();
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public ArrayList<Accounts> getApmInvoiceList(String appointmentid) {
			PreparedStatement preparedStatement = null;
			ArrayList<Accounts>list = new ArrayList<Accounts>();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT apm_invoice.id,paybuy,location FROM apm_invoice ");
			sql.append("inner join apm_invoice_assesments on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			sql.append("where apm_invoice.appointmentid="+appointmentid+" group by paybuy ");
			
			
			try{
				
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Accounts accounts = new Accounts();
					accounts.setId(rs.getInt(1));
					accounts.setPayBy(rs.getInt(2));
					accounts.setLocation(rs.getString(3));
					
					list.add(accounts);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return list;
		}
		
		
		

		public int createInvoice(String clientId, String practitionerId,
				String client, String practitionerName, String commencing,
				String id,String location) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_invoice(clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location) values(?,?,?,?,?,?,?,?)";
			int invoiceid = 0;
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, clientId);
				preparedStatement.setString(2, practitionerId);
				preparedStatement.setString(3, client);
				preparedStatement.setString(4, practitionerName);
				preparedStatement.setString(5, commencing);
				preparedStatement.setString(6, "Charge");
				preparedStatement.setInt(7, Integer.parseInt(id));
				//String location = getLocation(id);
				preparedStatement.setString(8, location);
				result = preparedStatement.executeUpdate();
				
				if(result == 1){
					ResultSet resultSet = preparedStatement.getGeneratedKeys();
					if(resultSet.next()){
						invoiceid = resultSet.getInt(1);  
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return invoiceid;
		}

		private String getInvoiceId(String id, String payBuy, String clientId) {
			PreparedStatement preparedStatement = null;
			String invoiceId = "";
			String sql = "SELECT invoiceid FROM apm_invoice_assesments where appointmentid= "+id+" and clientId = "+clientId+" and  paybuy = "+payBuy+" group by invoiceid ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					invoiceId = rs.getString(1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return invoiceId;
		}

		
		public int deleteInvoiceEntry(String id) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_invoice where appointmentid = "+id+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			    
			return result;

		}
		public int deleteInvoiceAssessment(String id) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_invoice_assesments where appointmentid = "+id+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			    
			return result;
		}

		public int updateArrievedStatus(String appointmentid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_available_slot set arrivedstatus=0 where id = "+appointmentid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public boolean checkExcessExist(String apppointmentid, String clientId,int loginid) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			String sql = "select * from apm_patient_complete_apmt where apmtType = '"+Constants.POLICYEXCESS+"' and appointmentid = "+apppointmentid+" and clientId = "+clientId+" and loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int getTreatmentSession(String apppointmentid, String clientId) {
			int id = 0;
			PreparedStatement preparedStatement = null;
			
			String sql = "select usedsession from apm_available_slot where id = "+apppointmentid+" and clientId = "+clientId+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					 id = rs.getInt(1);
					}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return id;
		}

		public int saveExcessCharge(CompleteAppointment completeAppointment,int loginid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_patient_complete_apmt(user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,additioncharge_id,loginid,masterchargetype)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
			
				preparedStatement.setString(1, completeAppointment.getUser());
				
				double acessAmt = getExcessAmmount(completeAppointment.getClientId());
				preparedStatement.setString(2, ""+Constants.POLICYEXCESS+"");
				preparedStatement.setDouble(3, acessAmt);
				preparedStatement.setString(4, completeAppointment.getStartTime());
				preparedStatement.setString(5, completeAppointment.getDuration());
				preparedStatement.setString(6, completeAppointment.getPractitionerId());
				preparedStatement.setString(7, completeAppointment.getPractitionerName());
				preparedStatement.setString(8, completeAppointment.getClientId());
				preparedStatement.setString(9, completeAppointment.getCommencing());
				preparedStatement.setString(10, "0");
				preparedStatement.setString(11, completeAppointment.getMarkAppointment());
				preparedStatement.setString(12, completeAppointment.getAppointmentid());
				preparedStatement.setString(13, completeAppointment.getChargeId());
				preparedStatement.setInt(14, loginid);
				preparedStatement.setString(15, completeAppointment.getMasterchargetype());
				result = preparedStatement.executeUpdate();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		private double getExcessAmmount(String clientId) {
			double id = 0;
			PreparedStatement preparedStatement = null;
			
			String sql = "select policyExcess from apm_patient where id = "+clientId+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					 id = rs.getDouble(1);
					}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return id;
		}

		public ArrayList<CompleteAppointment> getOldAssessmnetList(
				String apppointmentid) {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,quantity,masterchargetype FROM apm_invoice_assesments where appointmentid = "+apppointmentid+"";
			double chargeTotal = 0;
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setClient(rs.getString(2));
					completeAppointment.setApmtType(rs.getString(3));
					completeAppointment.setCharges(rs.getString(4));
					completeAppointment.setStartTime(rs.getString(5));
					completeAppointment.setDuration(rs.getString(6));
					completeAppointment.setPractitionerId(rs.getString(7));
					completeAppointment.setPractitionerName(rs.getString(8));
					completeAppointment.setClientId(rs.getString(9));
					completeAppointment.setCommencing(rs.getString(10));
					
					completeAppointment.setPayBuy(rs.getString(11));
					completeAppointment.setMarkAppointment(rs.getString(12));
					completeAppointment.setQuantity(rs.getInt(13));
					completeAppointment.setMasterchargetype(rs.getString(14));
					
					clientChargeListDetail.add(completeAppointment);
					
					
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return clientChargeListDetail;
		}
		
		public ArrayList<CompleteAppointment> getModifyInvoiceAsessmentDetailsTemp(String id,int loginid){
			ChargesAccountProcessingDAO chargesAccountProcessingDAO=new JDBCChargeAccountProcesDAO(connection);
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			String sql = "SELECT id,apmtType,charge,paybuy,quantity,masterchargetype,chargedisc,unitcharge,user,clientId,commencing,markAppointment,invpkgid,ginvstid,isshareablecharge,sharedrid,discrs,discper,tpkg,invoiced,chargeId,std_charge_id,tax1,tax2,tax3 FROM apm_patient_complete_apmt where invoiceid = "+id+" and loginid="+loginid+" ";
			double chargeTotal = 0;
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setApmtType(rs.getString(2));
					completeAppointment.setCharges(rs.getString(3));
					
					double total = Double.parseDouble((rs.getString(3)))*rs.getInt(5);
					//total = total - rs.getDouble(7);
					chargeTotal = chargeTotal + total;
					completeAppointment.setChargeTotal(chargeTotal);
					completeAppointment.setPayBuy(rs.getString(4));
					completeAppointment.setQuantity(rs.getInt(5));
					completeAppointment.setMasterchargetype(rs.getString(6));
					completeAppointment.setChargedisc(rs.getString(7));
					completeAppointment.setUnitcharge(rs.getString(8));
					//for refund
					completeAppointment.setClient(rs.getString(9));
					completeAppointment.setClientId(rs.getString(10));
					completeAppointment.setCommencing(rs.getString(11));
					completeAppointment.setMarkAppointment(rs.getString(12));
					completeAppointment.setIsindisharecharge(rs.getString(15));
					completeAppointment.setVisitingconsulatntdrid(rs.getString(16));
					if(rs.getString(17)==null){
						completeAppointment.setDisc_amount("0");
					}else{
						completeAppointment.setDisc_amount(rs.getString(17));
					}
					if(rs.getString(18)==null){
						completeAppointment.setDiscperc("0");
					}else{
						completeAppointment.setDiscperc(rs.getString(18));
					}
					completeAppointment.setTpkg(rs.getInt(19));
					completeAppointment.setInvoiced(rs.getInt(20));
					completeAppointment.setChargeId(rs.getString(21));
					completeAppointment.setStd_charge_id(rs.getString(22));
					
					
					
					completeAppointment.setTax1(""+rs.getDouble(23));
					completeAppointment.setTax2(""+rs.getDouble(24));
					completeAppointment.setTax3(""+rs.getDouble(25));
					completeAppointment.setTotaltax(""+(rs.getDouble(23)+rs.getDouble(24)+rs.getDouble(25)));
					
					
					clientChargeListDetail.add(completeAppointment);
					
					
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return clientChargeListDetail;
		}

		
		public ArrayList<CompleteAppointment> getAsessmentDetailsTemp(String id,int loginid) {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
			String sql = "SELECT id,apmtType,charge,paybuy,quantity,masterchargetype FROM apm_patient_complete_apmt where appointmentid = "+id+" and loginid="+loginid+" ";
			double chargeTotal = 0;
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setApmtType(rs.getString(2));
					completeAppointment.setCharges(rs.getString(3));
					double total = Double.parseDouble((rs.getString(3))) * rs.getInt(5);
					chargeTotal = chargeTotal + total;
					completeAppointment.setChargeTotal(chargeTotal);
					completeAppointment.setPayBuy(rs.getString(4));
					completeAppointment.setQuantity(rs.getInt(5));
					completeAppointment.setMasterchargetype(rs.getString(6));
					
					
					clientChargeListDetail.add(completeAppointment);
					
					
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return clientChargeListDetail;
		}

		public boolean checkAppointTypeExistInAssessment(String apmtType,
				String appointmentid) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			String sql = "select * from apm_invoice_assesments where apmtType = '"+apmtType+"' and appointmentid = "+appointmentid+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
		
		
		public int saveModifyInvoiceChargeNewUpdate(CompleteAppointment completeAppointment, String id,int loginid,String disctype){
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_patient_complete_apmt(user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,invoiceid,quantity,ipdid,masterchargetype,loginid,chargedisc,unitcharge,isshareablecharge,sharedrid,additioncharge_id,discrs,discper,tpkg,invoiced,chargeId,std_charge_id,tax1,tax2,tax3,chargedescript )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
			
				preparedStatement.setString(1, completeAppointment.getClient());
				
				//AppointmentType appointmentType = getAppointmentTypeData(completeAppointment.getApmtType());
				
				preparedStatement.setString(2, completeAppointment.getApmtType());
				preparedStatement.setDouble(3, Double.parseDouble(completeAppointment.getCharges()));
				preparedStatement.setString(4, completeAppointment.getStartTime());
				preparedStatement.setString(5, completeAppointment.getDuration());
				preparedStatement.setString(6, completeAppointment.getPractitionerId());
				preparedStatement.setString(7, completeAppointment.getPractitionerName());
				preparedStatement.setString(8, completeAppointment.getClientId());
				preparedStatement.setString(9, completeAppointment.getCommencing());
				preparedStatement.setString(10, completeAppointment.getPayBuy());
				preparedStatement.setString(11, completeAppointment.getMarkAppointment());
				preparedStatement.setString(12, completeAppointment.getAppointmentid());
				preparedStatement.setString(13, id);
				preparedStatement.setDouble(14, completeAppointment.getQuantity());
				preparedStatement.setInt(15, completeAppointment.getIpdid());
				preparedStatement.setString(16, completeAppointment.getMasterchargetype());
				preparedStatement.setInt(17, loginid);
				preparedStatement.setString(18, completeAppointment.getChargedisc());
				preparedStatement.setString(19, completeAppointment.getUnitcharge());
				preparedStatement.setString(20, completeAppointment.getIsindisharecharge());
				preparedStatement.setString(21, completeAppointment.getVisitingconsulatntdrid());
				if(completeAppointment.getChargeId()==null){
					completeAppointment.setChargeId("0");
				}
				else if(completeAppointment.getChargeId().equals("")){
					completeAppointment.setChargeId("0");
				}
				preparedStatement.setString(22, completeAppointment.getChargeId());
				
				//  store disc in rs or per 06/02/2019
				if(disctype.equals("0")){
					preparedStatement.setString(23, "0");
					preparedStatement.setString(24, completeAppointment.getDiscperc());
				}else{
					preparedStatement.setString(23, completeAppointment.getDisc_amount());
					preparedStatement.setString(24, "0");
				}
				preparedStatement.setInt(25, completeAppointment.getTpkg());
				preparedStatement.setInt(26, completeAppointment.getInvoiced());
				preparedStatement.setString(27, completeAppointment.getChargeId());
				preparedStatement.setString(28, completeAppointment.getStd_charge_id());
				
				preparedStatement.setString(29, completeAppointment.getTax1());
				preparedStatement.setString(30, completeAppointment.getTax2());
				preparedStatement.setString(31, completeAppointment.getTax3());
				preparedStatement.setString(32, completeAppointment.getChargedescription());
				
				result = preparedStatement.executeUpdate();

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int saveChargeNewUpdate(CompleteAppointment completeAppointment,int loginid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_patient_complete_apmt(user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,quantity,masterchargetype,prodid,loginid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
			
				preparedStatement.setString(1, completeAppointment.getUser());
				
				//AppointmentType appointmentType = getAppointmentTypeData(completeAppointment.getApmtType());
				
				
			
				
				preparedStatement.setString(2, completeAppointment.getApmtType());
				preparedStatement.setDouble(3, Double.parseDouble(completeAppointment.getCharges()));
				preparedStatement.setString(4, completeAppointment.getStartTime());
				preparedStatement.setString(5, completeAppointment.getDuration());
				preparedStatement.setString(6, completeAppointment.getPractitionerId());
				preparedStatement.setString(7, completeAppointment.getPractitionerName());
				preparedStatement.setString(8, completeAppointment.getClientId());
				preparedStatement.setString(9, completeAppointment.getCommencing());
				preparedStatement.setString(10, completeAppointment.getPayBuy());
				preparedStatement.setString(11, completeAppointment.getMarkAppointment());
				preparedStatement.setString(12, completeAppointment.getAppointmentid());
				preparedStatement.setDouble(13, completeAppointment.getQuantity());
				preparedStatement.setString(14, completeAppointment.getMasterchargetype());
				preparedStatement.setInt(15, completeAppointment.getProdid());
				preparedStatement.setInt(16, loginid);
				result = preparedStatement.executeUpdate();

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int saveIdOfAssessment(int id) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_temp_complete_apmt(assessment_id)values(?) ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1,id);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
					
			return result;
		}

		public ArrayList<CompleteAppointment> getTempDeleteAssessmnetList() {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment> tempDeleteList = new ArrayList<CompleteAppointment>();
			String sql = "select assessment_id from apm_temp_complete_apmt";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment c1 = new CompleteAppointment();
					c1.setId(rs.getInt(1));
					tempDeleteList.add(c1);
					}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return tempDeleteList;
		}

		public int deleteTempAssessmnet() {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "truncate apm_temp_complete_apmt";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			    
			return result;
		}

		
		public ArrayList<String> getInvoiceIdList(String appointmentid) {
			PreparedStatement preparedStatement = null;
			ArrayList<String> list = new ArrayList<String>();
			String result = "";
			String sql = "select id from apm_invoice where appointmentid = "+appointmentid+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = rs.getString(1);
					list.add(result);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}

		public CompleteAppointment getModifyInvoiceDetails(String invoiceid) {
			PreparedStatement preparedStatement = null;
			CompleteAppointment completeAppointment = new CompleteAppointment();
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT apm_invoice.id,apm_invoice.clientid,apm_invoice.practitionerid,clientname,apm_invoice.practitionername,date,apm_invoice.appointmentid,location,paybuy,thirdPartyid,starttime,duration,apm_invoice_assesments.ipdid ");
			sql.append("FROM apm_invoice ");
			sql.append("inner join apm_invoice_assesments on  apm_invoice_assesments.invoiceid = apm_invoice.id ");
			sql.append("where apm_invoice.id="+invoiceid+" group by apm_invoice.id ");
			
			try{
				
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					completeAppointment.setInvoiceid(rs.getString(1));
					completeAppointment.setClientId(rs.getString(2));
					completeAppointment.setPractitionerId(rs.getString(3));
					completeAppointment.setClient(rs.getString(4));
					completeAppointment.setPractitionerName(rs.getString(5));
					completeAppointment.setCommencing(rs.getString(6));
					completeAppointment.setAppointmentid(rs.getString(7));
					completeAppointment.setLocation(rs.getString(8));
					completeAppointment.setPayBuy(rs.getString(9));
					completeAppointment.setTpid(rs.getString(10));
					completeAppointment.setStartTime(rs.getString(11));
					completeAppointment.setDuration(rs.getString(12));
					completeAppointment.setIpdid(rs.getInt(13));
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return completeAppointment;
		}

		public int saveModifyInvoiceNewCharge(CompleteAppointment completeAppointment,int loginid) {
			ChargesAccountProcessingDAO chargesAccountProcessingDAO=new JDBCChargeAccountProcesDAO(connection);
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_patient_complete_apmt(user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,appointmentid,paybuy,invoiceid,quantity,ipdid,masterchargetype,prodid,additioncharge_id,loginid,isshareablecharge,sharedrid,invoiced,unitcharge,chargeId,tax1,tax2,tax3,chargedescript) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, completeAppointment.getClient());
				int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(completeAppointment.getMasterchargetype());
				if(compulsary_consultant==1){
					completeAppointment.setApmtType(completeAppointment.getApmtType()+"-"+completeAppointment.getPractitionerName());
				}
				preparedStatement.setString(2, completeAppointment.getApmtType());
				preparedStatement.setString(3, completeAppointment.getCharges());
				preparedStatement.setString(4, completeAppointment.getStartTime());
				preparedStatement.setString(5, completeAppointment.getDuration());
				preparedStatement.setString(6, completeAppointment.getPractitionerId());
				preparedStatement.setString(7, completeAppointment.getPractitionerName());
				preparedStatement.setString(8, completeAppointment.getClientId());
				preparedStatement.setString(9, completeAppointment.getCommencing());
				preparedStatement.setString(10, completeAppointment.getAppointmentid());
				preparedStatement.setString(11, completeAppointment.getPayBuy());
				preparedStatement.setString(12, completeAppointment.getInvoiceid());
				preparedStatement.setDouble(13, completeAppointment.getQuantity());
				preparedStatement.setInt(14, completeAppointment.getIpdid());
				preparedStatement.setString(15, completeAppointment.getMasterchargetype());
				preparedStatement.setInt(16, completeAppointment.getProdid());
				preparedStatement.setString(17, completeAppointment.getAdditionalcharge_id());
				preparedStatement.setInt(18, loginid);
				preparedStatement.setString(19, completeAppointment.getIsindisharecharge());
				preparedStatement.setString(20, completeAppointment.getVisitingconsulatntdrid());
				preparedStatement.setInt(21, completeAppointment.getInvoiced());
				preparedStatement.setString(22, completeAppointment.getCharges());
				preparedStatement.setString(23, completeAppointment.getChargeId());
				
				preparedStatement.setString(24, completeAppointment.getTax1());
				preparedStatement.setString(25, completeAppointment.getTax2());
				preparedStatement.setString(26, completeAppointment.getTax3());
				preparedStatement.setString(27, completeAppointment.getChargedescription());
				
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return result;
		}

		public ArrayList<CompleteAppointment> getModifyInvoiceCompleteApmtList(
				String invoiceid,int loginid) {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment>list = new ArrayList<CompleteAppointment>();
			
			String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,quantity,ipdid,masterchargetype,prodid,chargedisc FROM apm_patient_complete_apmt where invoiceid = "+invoiceid+" and loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setUser(rs.getString(2));
					completeAppointment.setApmtType(rs.getString(3));
					completeAppointment.setUnitcharge(rs.getString(4));

					
					double disc = rs.getDouble(18);
					double totalcharge = rs.getDouble(4);
					int qty = rs.getInt(14);
					totalcharge = totalcharge * qty;
					totalcharge = totalcharge -disc;
					
					completeAppointment.setCharges(Double.toString(totalcharge));
					completeAppointment.setStartTime(rs.getString(5));
					completeAppointment.setDuration(rs.getString(6));
					completeAppointment.setPractitionerId(rs.getString(7));
					completeAppointment.setPractitionerName(rs.getString(8));
					completeAppointment.setClientId(rs.getString(9));
					completeAppointment.setCommencing(rs.getString(10));
					completeAppointment.setPayBuy(rs.getString(11));
					completeAppointment.setMarkAppointment(rs.getString(12));
					completeAppointment.setAppointmentid(rs.getString(13));
					completeAppointment.setQuantity(rs.getInt(14));
					completeAppointment.setIpdid(rs.getInt(15));
					completeAppointment.setMasterchargetype(rs.getString(16));
					completeAppointment.setProdid(rs.getInt(17));
					completeAppointment.setChargedisc(rs.getString(18));
					
					list.add(completeAppointment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
/*
		public int deleteAssesmentData(String invoiceid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_invoice_assesments where invoiceid="+invoiceid+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		
			return result;
		}*/
		
		public ArrayList<CompleteAppointment> getUpdateSaveInvoiceCompleteApmtList(
				String invoiceid,int loginid) {
			PreparedStatement preparedStatement = null;
			ArrayList<CompleteAppointment>list = new ArrayList<CompleteAppointment>();
			
			String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,quantity,ipdid,masterchargetype,prodid,chargedisc,unitcharge,isshareablecharge,sharedrid,additioncharge_id,discrs,discper,tpkg,invoiced,tax1,tax2,tax3,chargedescript FROM apm_patient_complete_apmt where invoiceid = "+invoiceid+" and loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					CompleteAppointment completeAppointment = new CompleteAppointment();
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setUser(rs.getString(2));
					completeAppointment.setApmtType(rs.getString(3));
					

					
					double disc = rs.getDouble(18);
					double totalcharge = rs.getDouble(4);
					int qty = rs.getInt(14);
					totalcharge = totalcharge * qty;
					//totalcharge = totalcharge -disc;
					
					completeAppointment.setCharges(Double.toString(rs.getDouble(4)));
					completeAppointment.setStartTime(rs.getString(5));
					completeAppointment.setDuration(rs.getString(6));
					completeAppointment.setPractitionerId(rs.getString(7));
					completeAppointment.setPractitionerName(rs.getString(8));
					completeAppointment.setClientId(rs.getString(9));
					completeAppointment.setCommencing(rs.getString(10));
					completeAppointment.setPayBuy(rs.getString(11));
					completeAppointment.setMarkAppointment(rs.getString(12));
					completeAppointment.setAppointmentid(rs.getString(13));
					completeAppointment.setQuantity(rs.getInt(14));
					completeAppointment.setIpdid(rs.getInt(15));
					completeAppointment.setMasterchargetype(rs.getString(16));
					completeAppointment.setProdid(rs.getInt(17));
					completeAppointment.setChargedisc(rs.getString(18));
					completeAppointment.setUnitcharge(rs.getString(19));
					completeAppointment.setIsindisharecharge(rs.getString(20));
					completeAppointment.setVisitingconsulatntdrid(rs.getString(21));
					completeAppointment.setChargeId(rs.getString(22));
					completeAppointment.setDisc_amount(String.valueOf(rs.getInt(23)));
					completeAppointment.setDiscperc(String.valueOf(rs.getInt(24)));
					completeAppointment.setTpkg(rs.getInt(25));
					completeAppointment.setInvoiced(rs.getInt(26));
					
					
					completeAppointment.setTax1(""+rs.getDouble(27));
					completeAppointment.setTax2(""+rs.getDouble(28));
					completeAppointment.setTax3(""+rs.getDouble(29));
					completeAppointment.setChargedescription(rs.getString(30));
					
					list.add(completeAppointment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		public int deleteAssesmentData(String invoiceid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_invoice_assesments where invoiceid="+invoiceid+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		
			return result;
		}
		

		public int getChargeInvoiceid(String invoiceid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "SELECT chargeinvoiceid FROM apm_charges_assesment where invoiceid="+invoiceid+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public double getModifyInvoiceTotalDebitAmmount(int chargeInvoiceid) {
			PreparedStatement preparedStatement = null;
			double  result = 0;
			StringBuffer sql = new StringBuffer();
			double charge =0;
			/*
			 * sql.
			 * append("SELECT charge,quantity,apm_invoice_assesments.discper,apm_invoice_assesments.discrs FROM apm_charges_invoice inner join apm_charges_assesment on "
			 * ); sql.
			 * append("apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id inner join apm_invoice_assesments on "
			 * ); sql.
			 * append("apm_invoice_assesments.invoiceid = apm_charges_assesment.invoiceid "
			 * ); sql.append("where apm_charges_invoice.id = "+chargeInvoiceid+"  ");
			 */ //@ rahul remove apm_charges_assesment table beacuse services shows repeated in sramhosp
			
			sql.append("SELECT charge,quantity,apm_invoice_assesments.discper,apm_invoice_assesments.discrs FROM apm_charges_invoice ");
			sql.append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiced = apm_charges_invoice.id ");
			sql.append("where apm_charges_invoice.id = "+chargeInvoiceid+" " ) ;
			try{
				
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()){
					if(rs.getInt(3)>0 || rs.getInt(4)>0){
						 charge =  rs.getDouble(1);
					}else{
					 charge =  rs.getDouble(1) * rs.getInt(2);
					}
					 result = result + charge;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return result;
		}

		public int updateModifyInvoiceDebitAmmount(double debitAmount,
				int chargeInvoiceid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_charges_invoice set debit=? where id="+chargeInvoiceid+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setDouble(1, debitAmount);
				
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int updateInvoiceLocation(String apmtid,String location) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_invoice set location=? where appointmentid="+apmtid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, location);
				result = preparedStatement.executeUpdate();
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public boolean checkAppointmentInvoiced(String appointmentid) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT * FROM apm_charges_assesment  inner join apm_invoice on ");
			sql.append("apm_invoice.id = apm_charges_assesment.invoiceid ");
			sql.append("where appointmentid = "+appointmentid+" ");
			
			try{
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public CompleteAppointment getInvntryCompProdDetails(int id,int loginid) {
			PreparedStatement preparedStatement = null;
			CompleteAppointment completeAppointment = new CompleteAppointment();
			String sql = "SELECT quantity,prodid FROM apm_patient_complete_apmt where id = "+id+" and loginid="+loginid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					completeAppointment.setQuantity(rs.getInt(1));
					completeAppointment.setProdid(rs.getInt(2));
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return completeAppointment;
		}

		public ArrayList<CompleteAppointment> getListofActiveCharges(int id,String clientid,LoginInfo loginInfo) {

			ArrayList<CompleteAppointment> list=new ArrayList<CompleteAppointment>();
			
			try {
				AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				//get ward id
				int bedid = assessmentFormDAO.getIpdBedno(clientid);
				String wardid = "0";
				String thirdPartyId = "0";
				String admissionid = "0";
				if(bedid!=0){
					
					BedDao bedDao=new JDBCBedDao(connection);
					
					 admissionid = assessmentFormDAO.getAdmissionid(clientid);
					
					Bed bed = bedDao.getEditIpdData(admissionid);
					
					wardid = bed.getWardid();
				}
				
				//get client details
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);
				
				if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
					
					 thirdPartyId = getThirdPartyId(clientid);
					
					
					if(!ipdDAO.checkifMainTp(thirdPartyId)){
						 
						String temptpid= ipdDAO.getFollowerTp(thirdPartyId); 
						if(temptpid!=null){
							
							if(!temptpid.equals("0")){
								thirdPartyId=temptpid;  



							}
						}
					}
				}
				
				
				
				StringBuffer buffer= new StringBuffer();
				buffer.append("select apm_invoice_assesments.id,apm_invoice_assesments.invoiceid,apm_invoice_assesments.clientId,commencing,std_charge_id,paybuy,startTime,stdflag,tpcommencing ");
				buffer.append("from apm_invoice_assesments ");
				buffer.append("where ipdid = "+admissionid+" and ");
				if(loginInfo.getIskunal()!=1){
					buffer.append("masterchargetype = 'Bed Charge' order by id desc limit 0,1 ");
				}else{
					buffer.append("masterchargetype = 'Bed Charges' order by id desc limit 0,1 ");
				}
				/*buffer.append("from apm_invoice_assesments inner join apm_std_onoff_charge on apm_invoice_assesments.ipdid=apm_std_onoff_charge.ipdid ");
				buffer.append("where apm_invoice_assesments.ipdid="+id+" and clientId="+clientid+" and apm_std_onoff_charge.status=1 and apm_invoice_assesments.masterchargetype='Bed Charge' order by apm_invoice_assesments.id desc limit 0,1");*/
				
				
				PreparedStatement ps=connection.prepareStatement(buffer.toString());
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					
					  CompleteAppointment appointment=new CompleteAppointment();
					  appointment.setId(rs.getInt(1));
					  appointment.setInvoiceid(rs.getString(2));
					  appointment.setClientId(rs.getString(3));
					  appointment.setCommencing(rs.getString(4));
					  appointment.setAdditionalcharge_id(rs.getString(5));
					  appointment.setPayBuy(rs.getString(6));
					  appointment.setIpdid(id);
					  appointment.setStartTime(rs.getString(7));
					  appointment.setStdflag(rs.getString(8));
					  
					  String tpcommencing = rs.getString(9);
					  tpcommencing = tpcommencing.split(" ")[1];
					/* String tmp[] = tpcommencing.split(":");
					 String stime = tmp[0] + ":" + tmp[1];*/
					 appointment.setStartTime(tpcommencing);
					  list.add(appointment);
				}
				
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return list;
		}

		public int updateAutocharge(int id,String date,String wardid,String time,int qty1) {

			int result=0;

			int qty22=0;
			try {
				 
			/*	String sql="select quantity from apm_invoice_assesments where id="+id+"";
				PreparedStatement ps=connection.prepareStatement(sql);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					
					qty=rs.getInt(1);
				}
				
				qty++*/;
				
				String sql2="update apm_invoice_assesments set quantity=?,commencing=?,startTime=?,stdflag=0 where id="+id+" and wardid="+wardid+"";
				PreparedStatement ps1=connection.prepareStatement(sql2);
				ps1.setInt(1,qty1);
				ps1.setString(2, date);
				ps1.setString(3, time);
				
				result=ps1.executeUpdate();
			} catch (Exception e) {

				e.printStackTrace();
			}
			 
			return result;
		}

		public int truncateApmcompAppointment(int loginid) {
			
			return deleteComplteApmt(loginid);
		}


		public int saveStndCharge(String clientid, String ipdid,
				String standardcharges) {

			int result=0;
			try {
				
					String sql="insert into apm_standard_acess_charge (clientid,ipdid,std_charge_id) values (?,?,?)";
					PreparedStatement ps=connection.prepareStatement(sql);
					ps.setString(1,clientid);
					ps.setString(2,ipdid);
					ps.setString(3,standardcharges);
				 
				    result=ps.executeUpdate();
				
			} catch (Exception e) {

			 e.printStackTrace();
			} 
		
			return result;
		}

		public int isStdChargeExits(String standard_chargeid, String ipdid) {

			int result=0;
			try {
				
				String sql="select id from apm_invoice_assesments where ipdid="+ipdid+" and std_charge_id="+standard_chargeid+"";
				
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()){
					 
					result=rs.getInt(1);
				}
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result;
		}

		public int updatQntyByOne(int inv_acc_id) {

			int result=0;

			int qty=0;
			try {
				 
				String sql="select quantity from apm_invoice_assesments where id="+inv_acc_id+"";
				PreparedStatement ps=connection.prepareStatement(sql);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					
					qty=rs.getInt(1);
				}
				qty++;
				String sql2="update apm_invoice_assesments set quantity="+qty+" where id="+inv_acc_id+"";
				PreparedStatement ps1=connection.prepareStatement(sql2);
				
				result=ps1.executeUpdate();
			} catch (Exception e) {

				e.printStackTrace();
			}
			 
			return result;
		}

		public int getIfAlradyExistsStdId(String ipdid, String standardcharges) {

			int result=0;
			
			boolean flag=false;
			String std_charges="";
			int id=0;
			
			
			try {
			
				String sql="select id,std_charge_id from apm_standard_acess_charge where ipdid="+ipdid+"";   
				
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()){
			
					id=rs.getInt(1);
					std_charges=rs.getString(2);
				}
				
				
                for(String str:standardcharges.split(",")){
                	
                	for(String str1:std_charges.split(",")){
                		
                		if(str.equals("0") || str1.equals("0")){
                			continue;
                		}
                		
                		if(str.equals(str1)){
                			  
                			flag=true;
                			break;
                		}
                		
                		
                	}
                	if(flag){
            			break;
            		}
                }
				
				
               if(flag) { 
            	   String sql2="update apm_standard_acess_charge set std_charge_id='"+standardcharges+"' where id="+id+"";
            	   PreparedStatement ps2=connection.prepareStatement(sql2);
               
            	   result=ps2.executeUpdate();
          
               } else {
            	   result=0;
               }
                
                
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result;
		}


		public int getifAutoChargeRaised(String ipdid) {
			
			 int result=0;
			 try {
			
				 String sql="select id from apm_standard_acess_charge where ipdid="+ipdid+"";
				 
				 PreparedStatement ps=connection.prepareStatement(sql);
				 ResultSet rs=ps.executeQuery();
				 
				 while(rs.next()){
					 
					 result=rs.getInt(1);
				 }
				 
			} catch (Exception e) {

				e.printStackTrace();
			} 
			
			
			return result;
		}


		
		public int updateStndAccessmentCharge(String ipdid,
				String standardcharges) {

			int result=0;
			
			try {
				
				String sql="update apm_standard_acess_charge set std_charge_id='"+standardcharges+"' where ipdid="+ipdid+"";
				PreparedStatement ps=connection.prepareStatement(sql);
				result=ps.executeUpdate();
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result;
		}

		public ArrayList<Master> getStandardCharges(String wardid,
				String payby) {
			
			if(payby!=null){
			
			  if(payby.equals("Client")){
				 payby="Self";
			  }
			}
			ArrayList<Master> list=new ArrayList<Master>();
			
			try {
				
				 String sql="select id,name,charges from apm_standard_charges where wardid="+wardid+" and payby='"+payby+"'";
				 PreparedStatement ps=connection.prepareStatement(sql);
				 ResultSet rs=ps.executeQuery();
				 
				 while(rs.next()){
					   
					 Master master=new Master();
					 master.setId(rs.getInt(1));
					 master.setName(rs.getString(2));
					 master.setCharge(rs.getString(3));
					 master.setWardid(wardid);
					 master.setPayby(payby);
					 list.add(master);
				 }
				 
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		
			return list;
		}

		public int updateWardCharges(int id, String stdcharges) {

			int result=0;
			
			try {
				
				String sql="update apm_standard_acess_charge set std_charge_id='"+stdcharges+"' where ipdid="+id+"";
				PreparedStatement ps=connection.prepareStatement(sql);
				result=ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}

		public Clinic getIpdRegistrationCharge(int clinicid) {

			Clinic clinic=new Clinic();
			try {
				
				String sql="select ipdregcharge,ipdregtype,mlc_charge from apm_user where id="+clinicid+"";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()){
					
					clinic.setIpdregcharge(rs.getString(1));
					clinic.setIpdregtype(rs.getString(2));
					clinic.setMlc_charge(rs.getInt(3));
				}
				
				if(clinic.getIpdregcharge()!=null){
					 
					 if(clinic.getIpdregcharge().equals("")){
						 
						  clinic.setIpdregcharge("0");
					 }
				}
				
				if(clinic.getIpdregtype()==null){
					  
					   clinic.setIpdregtype("");
				}
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		
			return clinic;
		}

		public int deletIpdVisitingConsultCharge(String visitid, String ipdid,
				String clientid) {

			int result=0;
			
			try {
				String sql="delete FROM apm_invoice_assesments where apmtType='IPD Visiting Charge' and clientId="+clientid+" and ipdid="+ipdid+" and masterchargetype='IPD Visiting Charge' and std_charge_id='"+visitid+"' ";
				PreparedStatement ps=connection.prepareStatement(sql); 
				
				
				result=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}

		public int updateCompStatus(String appointmentid) {

			int result=0;
			try {
				
				String sql="update apm_available_slot set drcompleted=1 where id="+appointmentid+"";
				PreparedStatement ps=connection.prepareStatement(sql);
				result=ps.executeUpdate();
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result;
		}


		
		public int updateQtyInvoice(String id, String qty) {

			int result=0;
			try {
				
				String sql="update apm_patient_complete_apmt set quantity="+qty+" where id="+id+"";
				PreparedStatement ps=connection.prepareStatement(sql);
				result=ps.executeUpdate();
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result;
		}
		
		
		public CompleteAppointment getInvoiceAsessmentDetails(int id) {
			
			PreparedStatement preparedStatement = null;
			CompleteAppointment completeAppointment= new CompleteAppointment();
			String sql = "SELECT id,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,quantity,ipdid,masterchargetype,chargeId,invoiceid,std_charge_id,wardid,thirdPartyId FROM apm_invoice_assesments where id= "+id+"";
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					completeAppointment.setId(rs.getInt(1));
					completeAppointment.setClient(rs.getString(2));
					completeAppointment.setApmtType(rs.getString(3));
					completeAppointment.setCharges(rs.getString(4));
					completeAppointment.setStartTime(rs.getString(5));
					completeAppointment.setDuration(rs.getString(6));
					completeAppointment.setPractitionerId(rs.getString(7));
					completeAppointment.setPractitionerName(rs.getString(8));
					completeAppointment.setClientId(rs.getString(9));
					completeAppointment.setCommencing(rs.getString(10));
					
					completeAppointment.setPayBuy(rs.getString(11));
					completeAppointment.setMarkAppointment(rs.getString(12));
					completeAppointment.setAppointmentid(rs.getString(13));
					completeAppointment.setQuantity(rs.getInt(14));
					completeAppointment.setIpdid(rs.getInt(15));
					completeAppointment.setMasterchargetype(rs.getString(16));
					completeAppointment.setChargeId(rs.getString(17));
					completeAppointment.setInvoiceid(rs.getString(18));
					completeAppointment.setAdditionalcharge_id(rs.getString(19));
					completeAppointment.setWardid(rs.getString(20));
					completeAppointment.setTpid(rs.getString(21));
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return completeAppointment;
			
		}
		
		
		
		


		public int saveInvoiceAssementWithHalf(CompleteAppointment completeAppointment,
				int amt) {

				PreparedStatement preparedStatement = null;
				int result = 0;
				
				String clientId = completeAppointment.getClientId();
				//set department to charges
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
				String practionerId = "0";
				String condition = "0";
				
				//get ipd details
				int bedid = assessmentFormDAO.getIpdBedno(clientId);
				if(bedid!=0){
					IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					BedDao bedDao=new JDBCBedDao(connection);
					
					String admissionid = assessmentFormDAO.getAdmissionid(clientId);
					
					Bed bed = bedDao.getEditIpdData(admissionid);
					practionerId = bed.getPractitionerid();
				    UserProfile	userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
					
				    condition = userProfile.getDiciplineName();
					
				}else{
					ClientDAO clientDAO = new JDBCClientDAO(connection);
					NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
					if(notAvailableSlot.getDiaryUserId()!=0){
						practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
					}
					
					
					//check if doctor placed with machine
					
					UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
					
					if(userProfile.getJobgroup()==null){
						userProfile.setJobgroup("");
					}
					if(userProfile.getJobgroup().equals("4")){
						practionerId = userProfile.getDoctor();
					}
					
					if(practionerId==null){
						practionerId="0";
					}
					
					userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
					condition = userProfile.getDiciplineName();
					
					if(practionerId.equals("0")){
						condition = "0";
					}
					
				}
				
				String sql = "insert into apm_invoice_assesments (user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,invoiceid,thirdPartyId,quantity,ipdid,masterchargetype,std_charge_id,wardid,chargeId,transactiontype,department) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				try{
					
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, completeAppointment.getUser());
					preparedStatement.setString(2, completeAppointment.getApmtType());
					preparedStatement.setString(3, ""+amt+"");
					preparedStatement.setString(4, completeAppointment.getStartTime());
					preparedStatement.setString(5, completeAppointment.getDuration());
					preparedStatement.setString(6, completeAppointment.getPractitionerId());
					preparedStatement.setString(7, completeAppointment.getPractitionerName());
					preparedStatement.setString(8, completeAppointment.getClientId());
					preparedStatement.setString(9, completeAppointment.getCommencing());
					preparedStatement.setString(10, completeAppointment.getPayBuy());
					preparedStatement.setString(11, completeAppointment.getMarkAppointment());
					preparedStatement.setString(12, completeAppointment.getAppointmentid());
					preparedStatement.setString(13, completeAppointment.getInvoiceid());
					
					String payBuy = completeAppointment.getPayBuy();
					if(payBuy.equalsIgnoreCase("1")){
						String thirdPartyId = getThirdPartyId(completeAppointment.getClientId());
						preparedStatement.setString(14,thirdPartyId);
						}
						else{
							preparedStatement.setString(14,"0");
						}
					
					preparedStatement.setInt(15, 1);
					preparedStatement.setInt(16, completeAppointment.getIpdid());
					preparedStatement.setString(17, completeAppointment.getMasterchargetype());
					preparedStatement.setString(18, completeAppointment.getAdditionalcharge_id());
					preparedStatement.setString(19, completeAppointment.getWardid());
					preparedStatement.setString(20, completeAppointment.getChargeId());
					preparedStatement.setString(21, "1");
					preparedStatement.setString(22, condition);
					result = preparedStatement.executeUpdate();
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			return result;
		}

		public int getInvoiceforStandardCharges(String ipdid, String stdcharges) {

			int result=0;
			try {
				
				String sql="select id from apm_invoice where ipdid="+ipdid+" and standard_chargeid='"+stdcharges+"' and chargetype='Charge' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					 
					 result=rs.getInt(1);
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result;
		}

		public int getAssesmentIdforStdChargeIfExits(String ipdid,
				String chargeId,int invoiceid, LoginInfo loginInfo) {
			int result=0;
			
			try {
				//String sql=" and masterchargetype='Bed Charge' ";
				StringBuffer buffer = new StringBuffer();
				buffer.append("select id from apm_invoice_assesments where ipdid="+ipdid+" and invoiceid="+invoiceid+" and std_charge_id="+chargeId+" ");
				if(loginInfo.getIskunal()==1){
					buffer.append("and masterchargetype='Bed Charges' ");
				}else{
					buffer.append("and masterchargetype='Bed Charge' ");
				}
				PreparedStatement ps=connection.prepareStatement(buffer.toString());
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					 result =rs.getInt(1);
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result ;
		}
		
		public int getAssesmentIdforStdChargeIfExits1(String ipdid,
				String chargeId,int invoiceid,String masterchargetype) {
			int result=0;
			
			try {
				
				String sql="select id from apm_invoice_assesments where ipdid="+ipdid+" and invoiceid="+invoiceid+" and std_charge_id="+chargeId+" and masterchargetype='"+masterchargetype+"' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					 result =rs.getInt(1);
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result ;
		}

		public int getHalfInvoiceAssesmentIfExist(String stdChargeId,String ipdid, LoginInfo loginInfo) {

			int result=0;
			try {
				//String sql=" ";
				StringBuffer buffer = new StringBuffer();
				buffer.append("select id from apm_invoice_assesments where std_charge_id="+stdChargeId+" and transactiontype=1 and ipdid="+ipdid+" ");
				if(loginInfo.getIskunal()==1){
					buffer.append("and masterchargetype='Bed Charges' ");
				}else{
					buffer.append("and masterchargetype='Bed Charge' ");
				}
				PreparedStatement ps=connection.prepareStatement(buffer.toString());
				ResultSet rs =ps.executeQuery();
				
				while(rs.next()){
					  result =rs.getInt(1);
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			return result;
		}

		public int updateAutochargeDate(int id, String date, String wardid,
				String time) {
			
			int result=0;
			try {
				
				String sql2="update apm_invoice_assesments set commencing=?,startTime=?,stdflag=0 where id="+id+" and wardid="+wardid+"";
				PreparedStatement ps1=connection.prepareStatement(sql2);
				ps1.setString(1, date);
				ps1.setString(2, time);
				result=ps1.executeUpdate();
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result;
		}

		public boolean isAutoChargedOn(String clientid) {

			boolean res=false;
			try {
				
				String sql="select isautocharge from apm_patient where id="+clientid+"";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs =ps.executeQuery();
				while(rs.next()){
					 res =rs.getBoolean(1);
				}
				
				/*if(res==1){
					return false;
				} else {
					return true;
				}*/
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			return res;
		}

		public String getIpdLogLastCommencing(int id) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "select lastmodified from ipd_bed_change_log where admissionid ="+id+" group by commencing order by id desc limit 0,1 ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getString(1);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		public boolean checkInvoiceBalanace(String clientid) {
			PreparedStatement preparedStatement = null;
			boolean result = true;
			String sql = "select balance from apm_credit_account where client_id ="+clientid+" order by id desc limit 0,1 ";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					if(rs.getInt(1)>0){
						result =  false;
					}else{
						result =  true;
					}
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int updateUnitCharge(int id,String unitcharge) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_invoice_assesments set unitcharge="+unitcharge+" where id="+id+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		public int updateIndivisualDisc(String gdiscamt, String id,double charge,String disctype, String maindisc) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			StringBuffer  buffer=new StringBuffer();
			buffer.append("update apm_patient_complete_apmt set chargedisc="+gdiscamt+",charge="+charge+" ");
			if(disctype.equals("0")){
				buffer.append(",discper="+maindisc+" ");
			}else{
				buffer.append(",discrs="+maindisc+" ");
			}
			buffer.append("where id="+id+" ");
			
			try{
				preparedStatement = connection.prepareStatement(buffer.toString());
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return result;
		}

		public int updateIndivQty(String id, String qty) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_patient_complete_apmt set quantity="+qty+" where id="+id+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
			
		}

		public boolean checkInvchargeExsist(String wid, String tpid, String apmtType, String masterchargetype) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			String sql = "SELECT * FROM apm_appointment_type where name='"+apmtType+"' and "
					+ "chargeType='"+masterchargetype+"' and wardid='"+wid+"' and tpid='"+tpid+"' ";
			
			try{
				preparedStatement  = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		public int updateInvestigationCharge(String wid, String tpid, String apmtType, String masterchargetype,String charges) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql =  "update apm_appointment_type set charges='"+charges+"' where name='"+apmtType+"' and "
					+ "chargeType='"+masterchargetype+"' and wardid='"+wid+"' and tpid='"+tpid+"' ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			return result;
		}

		public int saveInvestigationCharge(String wid, String tpid, String apmtType, String masterchargetype,
				String charges) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_appointment_type(name,charges,chargeType,tpid,code,nabhcharge,wardid) values(?,?,?,?,?,?,?)";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, apmtType);
				preparedStatement.setString(2, charges);
				preparedStatement.setString(3, "INVESTIGATION");
				preparedStatement.setString(4, tpid);
				preparedStatement.setString(5, "0");
				preparedStatement.setString(6, "0");
				preparedStatement.setString(7, wid);
				
				result = preparedStatement.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int updateInveNewCharge(String dbid, String charge) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_patient_complete_apmt set charge='"+charge+"' where id="+dbid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				//for ayushman
				if(result==1) {
					CompleteAppointment completeAppointment=getApmtType(dbid);
					boolean isexist=chargenameExistorNot(completeAppointment.getApmtType());
					completeAppointment.setCharges(charge);
					if(isexist) {
						result=updatecharge(completeAppointment);
					}else {
						result=insertCharge(completeAppointment);
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		private int insertCharge(CompleteAppointment completeAppointment) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_appointment_type(name,charges,chargeType)values(?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, completeAppointment.getApmtType());
			preparedStatement.setString(2, completeAppointment.getCharges());
			preparedStatement.setString(3, completeAppointment.getMasterchargetype());
			result = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		private int updatecharge(CompleteAppointment completeAppointment) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_appointment_type set charges=? where name='"+completeAppointment.getApmtType()+"' ";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, completeAppointment.getCharges());
				result = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		private boolean chargenameExistorNot(String apmtname) {
			boolean flag=false;
			try {
				String sql="select id from apm_appointment_type where name='"+apmtname+"' and tpid = 0 and wardid=0";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					flag = true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}

		private CompleteAppointment getApmtType(String dbid) {
			PreparedStatement ps = null;
			String apmtType="";
			CompleteAppointment completeAppointment= new CompleteAppointment();
			try {
				String sql =  "select apmtType,masterchargetype from apm_patient_complete_apmt where  id="+dbid+" ";
				ps= connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					completeAppointment.setApmtType(rs.getString(1));
					completeAppointment.setMasterchargetype(rs.getString(2));
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return completeAppointment;

		}

		public int saveInvoiceDeletedCharge(CompleteAppointment completeAppointment, String newinvoiceid, int loginid, int isdeleted,int chargeinvoiceid, int paymentdone, String datetime, String userid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into invoice_delete_charges(user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,paybuy,markAppointment,appointmentid,invoiceid,quantity,ipdid,masterchargetype,loginid,chargedisc,unitcharge,isdelete,date,actualinvoiceid,discamount,discperc,reeq_userid,reeq_datetime,ispaydonestatus)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
			
				preparedStatement.setString(1, completeAppointment.getClient());
				
				//AppointmentType appointmentType = getAppointmentTypeData(completeAppointment.getApmtType());
				
				preparedStatement.setString(2, completeAppointment.getApmtType());
				preparedStatement.setDouble(3, Double.parseDouble(completeAppointment.getCharges()));
				preparedStatement.setString(4, completeAppointment.getStartTime());
				preparedStatement.setString(5, completeAppointment.getDuration());
				preparedStatement.setString(6, completeAppointment.getPractitionerId());
				preparedStatement.setString(7, completeAppointment.getPractitionerName());
				preparedStatement.setString(8, completeAppointment.getClientId());
				preparedStatement.setString(9, completeAppointment.getCommencing());
				preparedStatement.setString(10, completeAppointment.getPayBuy());
				preparedStatement.setString(11, completeAppointment.getMarkAppointment());
				preparedStatement.setString(12, completeAppointment.getAppointmentid());
				preparedStatement.setString(13, newinvoiceid);
				preparedStatement.setDouble(14, completeAppointment.getQuantity());
				preparedStatement.setInt(15, completeAppointment.getIpdid());
				preparedStatement.setString(16, completeAppointment.getMasterchargetype());
				preparedStatement.setInt(17, loginid);
				preparedStatement.setString(18, completeAppointment.getChargedisc());
				preparedStatement.setString(19, completeAppointment.getUnitcharge());
				preparedStatement.setString(20, ""+isdeleted);
				preparedStatement.setString(21, completeAppointment.getDate());
				preparedStatement.setString(22, ""+chargeinvoiceid);
				preparedStatement.setString(23, completeAppointment.getDisc_amount());
				preparedStatement.setString(24, completeAppointment.getDiscperc());
				
				preparedStatement.setString(25, userid);
				preparedStatement.setString(26, datetime);
				preparedStatement.setString(27, ""+paymentdone);
				result = preparedStatement.executeUpdate();

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}


	public String getAppointmentid(String wid, String tpid, String apmtType, String masterchargetype,String charges){
		PreparedStatement ps = null;
		String id="";
		try {
			String sql =  "select id from apm_appointment_type where  name='"+apmtType+"' and "
					+ "chargeType='"+masterchargetype+"' and wardid='"+wid+"' and tpid='"+tpid+"' ";
			ps= connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				id= rs.getString(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return id;
	}

	public String getPreviousCharge(String wid, String tpid, String ApmtType, String Masterchargetype, String Charges) {
	String rate="";
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql =  "select  charges from apm_appointment_type  where name='"+ApmtType+"' and "
			+ "chargeType='"+Masterchargetype+"' and wardid='"+wid+"' and tpid='"+tpid+"' ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			rate=rs.getString(1);
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return rate;
	}

	public boolean isclientIdInApmt(String clientId) {
		boolean flag=false;
		try {
			String sql="select count(*) from apm_available_slot where clientId='"+clientId+"' and procedures='0'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)>1){
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public double getOpdRegCharge() {
		double opdcharge=0;
		try {
			String sql ="select opd_reg_fee from apm_user where id=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				opdcharge = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opdcharge;
	}

	public boolean isclientIdInApmtBefore(String clientId) {
		boolean flag=false;
		try {
			String sql="select count(*) from apm_available_slot where clientId='"+clientId+"' and procedures='0'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)==0){
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean checkAutoChargeAppliedStatus(String wardid, String bedid, int id, String logcommencing) {
		boolean flag = false;
		try {
			String sql ="select id from apm_invoice_assesments where ipdid='"+id+"' and wardid='"+wardid+"' and commencing='"+logcommencing+"' and logid is not null and logid>0 limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int getqantityOfCharge(int apm_patient_complete_apmt_id) {
		PreparedStatement ps=null;
		int res=0;
		try {
			String sql=" select quantity from apm_patient_complete_apmt where id='"+apm_patient_complete_apmt_id+"'";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean isclientRegisterChargeAdded(String clientId, int id) {
		boolean flag=false;
		try {
			String sql="select id from apm_patient_complete_apmt where apmtType='OPD Registration Charge' and masterchargetype='Registration Charge' and clientid='"+clientId+"' and loginid='"+id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
					flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public CompleteAppointment getInvoiceDiscountByChargeId(String invoiceid) {
		PreparedStatement preparedStatement = null;
		CompleteAppointment completeAppointment = new CompleteAppointment();
		StringBuffer sql = new StringBuffer();
		sql.append("select discount,debit,disctype,discamt from apm_charges_invoice ");
		//sql.append("inner join apm_charges_assesment on apm_charges_assesment.chargeinvoiceid = apm_charges_invoice.id ");
		//sql.append("where invoiceid='"+invoiceid+"'  ");
		sql.append("where id='"+invoiceid+"' ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				completeAppointment.setDiscount(rs.getDouble(1));
				completeAppointment.setTotal(rs.getDouble(2));
				completeAppointment.setDisc_type(""+rs.getInt(3));
				completeAppointment.setDisc_amount(DateTimeUtils.changeFormat(rs.getDouble(4)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return completeAppointment;
	}

	public int updateInvoiceDiscount(double newdiscamt, int chargeinvoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice set discamt=? where id="+chargeinvoiceid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, newdiscamt);
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//  20/11/2018 Save modify user details
	public int saveModifyInvoiceLog(String userId, String datetime, String invoiceid, int status, String chargeinvoiceid) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into modify_invoice_log(invoiceid,userid,datetime,status,chargeinvoiceid)values(?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, invoiceid);
		preparedStatement.setString(2, userId);
		preparedStatement.setString(3, datetime);
		preparedStatement.setInt(4, status);
		preparedStatement.setString(5, chargeinvoiceid);
		result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean isOPDFirstCharge(String clinetid, String appmtid) {
		PreparedStatement ps= null;
		boolean result=false; 
		/*String sql="select regdate from apm_patient where id='"+clinetid+"'";
		try {
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				String date=rs.getString(1);
				if(date==null){
					date="";
				}
				if(!date.equals("")){
					String dt[]= date.split(" ");
					date= dt[0];
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			      Calendar cal = Calendar.getInstance();
			      String today = dateFormat.format(cal.getTime()); 
			    if(date.equals(today)){
			    	result= true;
			    }  
			}*/
		try{
			String sql=" select * from ipd_addmission_form where clientid='"+clinetid+"'";
			PreparedStatement ps2= connection.prepareStatement(sql);
			ResultSet rs2= ps2.executeQuery();
			while(rs2.next()){
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getSelectedChargeId(String tpid, String masterchargetype, String apmtType) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id  FROM apm_appointment_type where tpid ='"+tpid+"' and name = '"+apmtType+"' and chargeType='"+masterchargetype+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public void swapPkgChargeList(String fdate, String tdate, String admissionid, int appliedpkgid, String clientid) {
		PreparedStatement preparedStatement=null;
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("select invoiceid, user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, "
				+ "commencing, appointmentid, paybuy, markAppointment, thirdPartyId, active, transactiontype, quantity, ipdid, "
				+ "masterchargetype, std_charge_id, wardid, chargeId, backDate, stdflag, newshftcharge, logid, bedid, shared_amt, isshared,"
				+ " all_shared, department, invpkgid, chargedisc, unitcharge, isshareablecharge, sharedrid, tpcommencing, "
				+ "tpkg, tpfdate, tptodate, pkgcode, sqno, discrs, discper from apm_invoice_assesments ");
		buffer.append("where tpcommencing between '"+fdate+"' and '"+tdate+"'  and masterchargetype != 'IMPLANTS/CONSUMABLES' ");
		buffer.append(" and ipdid='"+admissionid+"'");
		try {
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts=new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				accounts.setUsername(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				accounts.setCharges(rs.getString(4));
				accounts.setStarttime(rs.getString(5));
				accounts.setDuration(rs.getString(6));
				accounts.setPractitionerId(rs.getInt(7));
				accounts.setPractitionerName(rs.getString(8));
				accounts.setClientid(rs.getInt(9));
				accounts.setCommencing(rs.getString(10));
				accounts.setAppointmentid(rs.getInt(11));
				accounts.setPayBy(rs.getInt(12));
				accounts.setMarkappointment(rs.getInt(13));
				accounts.setThirdpartyid(rs.getInt(14));
				accounts.setActive(rs.getInt(15));
				accounts.setTransactiontype(rs.getInt(16));
				accounts.setQuantity(rs.getInt(17));
				accounts.setIpdid(rs.getString(18));
				accounts.setMasterchargetype(rs.getString(19));
				accounts.setStdcharge(rs.getString(20));
				accounts.setWard(rs.getString(21));
				accounts.setChargeid(rs.getString(22));
				accounts.setBackDate(rs.getString(23));
				accounts.setStdflag(rs.getInt(24));
				accounts.setNewshftcharge(rs.getString(25));
				accounts.setLogid(rs.getInt(26));
				accounts.setBedid(rs.getInt(27));
				accounts.setShared_amount(rs.getString(28));
				accounts.setIsShared(rs.getString(29));
				accounts.setAll_shared(rs.getInt(30));
				accounts.setDepartment(rs.getString(31));
				accounts.setInvpkgid(rs.getInt(32));
				accounts.setChargedisc(rs.getString(33));
				accounts.setUnitcharge(rs.getString(34));
				accounts.setIsshareablecharge(rs.getInt(35));
				accounts.setSharedrid(rs.getInt(36));
				accounts.setTpcommencing(rs.getString(37));
				accounts.setTpkg(appliedpkgid);
				accounts.setTpfdate(rs.getString(39));
				accounts.setTptodate(rs.getString(40));
				accounts.setPkgcode(rs.getString(41));
				accounts.setSqno(rs.getInt(42));
				accounts.setDiscamt(rs.getString(43));
				accounts.setDiscpercent(rs.getString(44));
				int res=insertintotpassessment(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private int insertintotpassessment(Accounts accounts) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into tp_invoice_assesments(invoiceid, user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId,"
				+ " commencing, appointmentid, paybuy, markAppointment, thirdPartyId, active, transactiontype, quantity, ipdid, masterchargetype, std_charge_id, "
				+ "wardid, chargeId, backDate, stdflag, newshftcharge, logid, bedid, shared_amt, isshared, all_shared, department, invpkgid, chargedisc, unitcharge, "
				+ "isshareablecharge, sharedrid, tpcommencing, tpkg, tpfdate, tptodate, pkgcode, sqno, discrs, discper)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,accounts.getInvoiceid());
		preparedStatement.setString(2, accounts.getUsername());
		preparedStatement.setString(3, accounts.getAppointmentType());
		preparedStatement.setString(4, accounts.getCharges());
		preparedStatement.setString(5, accounts.getStarttime());
		preparedStatement.setString(6, accounts.getDuration());
		preparedStatement.setInt(7, accounts.getPractitionerId());
		preparedStatement.setString(8, accounts.getPractitionerName());
		preparedStatement.setInt(9, accounts.getClientid());
		preparedStatement.setString(10, accounts.getCommencing());
		preparedStatement.setInt(11, accounts.getAppointmentid());
		preparedStatement.setInt(12, accounts.getPayBy());
		preparedStatement.setInt(13, accounts.getMarkappointment());
		preparedStatement.setInt(14, accounts.getThirdpartyid());
		preparedStatement.setInt(15, accounts.getActive());
		preparedStatement.setInt(16, accounts.getTransactiontype());
		preparedStatement.setInt(17, accounts.getQuantity());
		preparedStatement.setString(18, accounts.getIpdid());
		preparedStatement.setString(19, accounts.getMasterchargetype());
		preparedStatement.setString(20, accounts.getStdcharge());
		preparedStatement.setString(21, accounts.getWard());
		preparedStatement.setString(22, accounts.getChargeid());
		preparedStatement.setString(23, accounts.getBackDate());
		preparedStatement.setInt(24, accounts.getStdflag());
		preparedStatement.setString(25, accounts.getNewshftcharge());
		//logid, bedid, shared_amt, isshared, all_shared, department, invpkgid, chargedisc, unitcharge
		preparedStatement.setInt(26, accounts.getLogid());
		preparedStatement.setInt(27, accounts.getBedid());
		preparedStatement.setString(28, accounts.getShared_amount());
		preparedStatement.setString(29, accounts.getIsShared());
		preparedStatement.setInt(30, accounts.getAll_shared());
		preparedStatement.setString(31, accounts.getDepartment());
		preparedStatement.setInt(32, accounts.getInvpkgid());
		preparedStatement.setString(33, accounts.getChargedisc());
		preparedStatement.setString(34, accounts.getUnitcharge());
		//isshareablecharge, sharedrid, tpcommencing, tpkg, tpfdate, tptodate, pkgcode, sqno
		preparedStatement.setInt(35, accounts.getIsshareablecharge());
		preparedStatement.setInt(36, accounts.getSharedrid());
		preparedStatement.setString(37, accounts.getTpcommencing());
		preparedStatement.setInt(38, accounts.getTpkg());
		preparedStatement.setString(39, accounts.getTpfdate());
		preparedStatement.setString(40, accounts.getTptodate());
		preparedStatement.setString(41, accounts.getPkgcode());
		preparedStatement.setInt(42, accounts.getSqno());
		preparedStatement.setString(43, accounts.getDiscamt());
		preparedStatement.setString(44, accounts.getDiscpercent());
		result = preparedStatement.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
return result;
	}
	public void deletefromapmminvoiceassessment(String fdate, String tdate, String admissionid, String clientid) {
		PreparedStatement preparedStatement=null;
		try{
	String sql="delete from apm_invoice_assesments where ipdid='"+admissionid+"' "
			+ " and tpcommencing between '"+fdate+"' and '"+tdate+"' "
					+ " and masterchargetype != 'IMPLANTS/CONSUMABLES' and clientId="+clientid+"";
	preparedStatement = connection.prepareStatement(sql);
	
	int result = preparedStatement.executeUpdate();
	
}catch (Exception e) {
	e.printStackTrace();
}
	}

	public CompleteAppointment getPackageDtaes(String parentid) {
		CompleteAppointment cp=new CompleteAppointment();
PreparedStatement ps=null;
try {
	String sql="select fromdate,todate from apm_parent_patient_package where id='"+parentid+"' ";
	ps=connection.prepareStatement(sql);
	ResultSet rs=ps.executeQuery();
	while (rs.next()) {
		
		cp.setPkgfdate(rs.getString(1));
		
		
		
		
		cp.setPkgtdate(rs.getString(2));
	}
} catch (Exception e) {
e.printStackTrace();
}
		
		return cp;
	}

	public void swapPkgChargeTptoinvoiceList(String fdate, String tdate, String admissionid) {
PreparedStatement preparedStatement=null;
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("select invoiceid, user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, "
				+ "commencing, appointmentid, paybuy, markAppointment, thirdPartyId, active, transactiontype, quantity, ipdid, "
				+ "masterchargetype, std_charge_id, wardid, chargeId, backDate, stdflag, newshftcharge, logid, bedid, shared_amt, isshared,"
				+ " all_shared, department, invpkgid, chargedisc, unitcharge, isshareablecharge, sharedrid, tpcommencing, "
				+ "tpkg, tpfdate, tptodate, pkgcode, sqno, discrs, discper from tp_invoice_assesments ");
		buffer.append("where tpcommencing between '"+fdate+"' and '"+tdate+"' and ipdid='"+admissionid+"' ");
		try {
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts=new Accounts();
				accounts.setInvoiceid(rs.getInt(1));
				accounts.setUsername(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				accounts.setCharges(rs.getString(4));
				accounts.setStarttime(rs.getString(5));
				accounts.setDuration(rs.getString(6));
				accounts.setPractitionerId(rs.getInt(7));
				accounts.setPractitionerName(rs.getString(8));
				accounts.setClientid(rs.getInt(9));
				accounts.setCommencing(rs.getString(10));
				accounts.setAppointmentid(rs.getInt(11));
				accounts.setPayBy(rs.getInt(12));
				accounts.setMarkappointment(rs.getInt(13));
				accounts.setThirdpartyid(rs.getInt(14));
				accounts.setActive(rs.getInt(15));
				accounts.setTransactiontype(rs.getInt(16));
				accounts.setQuantity(rs.getInt(17));
				accounts.setIpdid(rs.getString(18));
				accounts.setMasterchargetype(rs.getString(19));
				accounts.setStdcharge(rs.getString(20));
				accounts.setWard(rs.getString(21));
				accounts.setChargeid(rs.getString(22));
				accounts.setBackDate(rs.getString(23));
				accounts.setStdflag(rs.getInt(24));
				accounts.setNewshftcharge(rs.getString(25));
				accounts.setLogid(rs.getInt(26));
				accounts.setBedid(rs.getInt(27));
				accounts.setShared_amount(rs.getString(28));
				accounts.setIsShared(rs.getString(29));
				accounts.setAll_shared(rs.getInt(30));
				accounts.setDepartment(rs.getString(31));
				accounts.setInvpkgid(rs.getInt(32));
				accounts.setChargedisc(rs.getString(33));
				accounts.setUnitcharge(rs.getString(34));
				accounts.setIsshareablecharge(rs.getInt(35));
				accounts.setSharedrid(rs.getInt(36));
				accounts.setTpcommencing(rs.getString(37));
				accounts.setTpkg(0);
				accounts.setTpfdate(rs.getString(39));
				accounts.setTptodate(rs.getString(40));
				accounts.setPkgcode(rs.getString(41));
				accounts.setSqno(rs.getInt(42));
				accounts.setDiscamt(rs.getString(43));
				accounts.setDiscpercent(rs.getString(44));
				int res=insertintoapminvoiceassessment(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private int insertintoapminvoiceassessment(Accounts accounts) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_invoice_assesments(invoiceid, user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId,"
				+ " commencing, appointmentid, paybuy, markAppointment, thirdPartyId, active, transactiontype, quantity, ipdid, masterchargetype, std_charge_id, "
				+ "wardid, chargeId, backDate, stdflag, newshftcharge, logid, bedid, shared_amt, isshared, all_shared, department, invpkgid, chargedisc, unitcharge, "
				+ "isshareablecharge, sharedrid, tpcommencing, tpkg, tpfdate, tptodate, pkgcode, sqno, discrs, discper)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,accounts.getInvoiceid());
		preparedStatement.setString(2, accounts.getUsername());
		preparedStatement.setString(3, accounts.getAppointmentType());
		preparedStatement.setString(4, accounts.getCharges());
		preparedStatement.setString(5, accounts.getStarttime());
		preparedStatement.setString(6, accounts.getDuration());
		preparedStatement.setInt(7, accounts.getPractitionerId());
		preparedStatement.setString(8, accounts.getPractitionerName());
		preparedStatement.setInt(9, accounts.getClientid());
		preparedStatement.setString(10, accounts.getCommencing());
		preparedStatement.setInt(11, accounts.getAppointmentid());
		preparedStatement.setInt(12, accounts.getPayBy());
		preparedStatement.setInt(13, accounts.getMarkappointment());
		preparedStatement.setInt(14, accounts.getThirdpartyid());
		preparedStatement.setInt(15, accounts.getActive());
		preparedStatement.setInt(16, accounts.getTransactiontype());
		preparedStatement.setInt(17, accounts.getQuantity());
		preparedStatement.setString(18, accounts.getIpdid());
		preparedStatement.setString(19, accounts.getMasterchargetype());
		preparedStatement.setString(20, accounts.getStdcharge());
		preparedStatement.setString(21, accounts.getWard());
		preparedStatement.setString(22, accounts.getChargeid());
		preparedStatement.setString(23, accounts.getBackDate());
		preparedStatement.setInt(24, accounts.getStdflag());
		preparedStatement.setString(25, accounts.getNewshftcharge());
		//logid, bedid, shared_amt, isshared, all_shared, department, invpkgid, chargedisc, unitcharge
		preparedStatement.setInt(26, accounts.getLogid());
		preparedStatement.setInt(27, accounts.getBedid());
		preparedStatement.setString(28, accounts.getShared_amount());
		preparedStatement.setString(29, accounts.getIsShared());
		preparedStatement.setInt(30, accounts.getAll_shared());
		preparedStatement.setString(31, accounts.getDepartment());
		preparedStatement.setInt(32, accounts.getInvpkgid());
		preparedStatement.setString(33, accounts.getChargedisc());
		preparedStatement.setString(34, accounts.getUnitcharge());
		//isshareablecharge, sharedrid, tpcommencing, tpkg, tpfdate, tptodate, pkgcode, sqno
		preparedStatement.setInt(35, accounts.getIsshareablecharge());
		preparedStatement.setInt(36, accounts.getSharedrid());
		preparedStatement.setString(37, accounts.getTpcommencing());
		preparedStatement.setInt(38, accounts.getTpkg());
		preparedStatement.setString(39, accounts.getTpfdate());
		preparedStatement.setString(40, accounts.getTptodate());
		preparedStatement.setString(41, accounts.getPkgcode());
		preparedStatement.setInt(42, accounts.getSqno());
		preparedStatement.setString(43, accounts.getDiscamt());
		preparedStatement.setString(44, accounts.getDiscpercent());
		result = preparedStatement.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
	}
return result;
	}

	public void deletefromtpinvoiceassessment(String fdate, String tdate, String admissionid) {
		PreparedStatement preparedStatement=null;
		try{
	String sql="delete from tp_invoice_assesments where ipdid='"+admissionid+"' and tpcommencing between '"+fdate+"' and '"+tdate+"' ";
	preparedStatement = connection.prepareStatement(sql);
	
	int result = preparedStatement.executeUpdate();
	
}catch (Exception e) {
	e.printStackTrace();
}
	}

	public String getwardnamebywardid(int int1) {
		PreparedStatement ps=null;
		String res="";
		try {
			String sql="select wardname from apm_ipd_ward where id='"+int1+"' ";
			ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getString(1);
				
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return res;
	}
	private String getPackageName(int pkgids) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM his_parent_package where id ="+pkgids+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getPackageidassesment(String clientid) {
		PreparedStatement preparedStatement = null;
		int result =0;
		String sql = "select tpkg from apm_invoice_assesments where clientid='"+clientid+"' and tpkg!=0";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	private int getparentpkgid(String clientId, String admissionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select packageid from apm_parent_patient_package where clientid = "+clientId+" and ipdid = "+admissionid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int checkInvoiceCreated(String ipdid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(id) from apm_charges_invoice where ipdid='"+ipdid+"' and isdeleted=0";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void kunalChargesUpdateProccess(String invoiceid, String discper, String dicsamt,String chargename) {
		try {
			/*if discper not 0  then in % else amt */
			PreparedStatement ps= connection.prepareStatement("select id,unitcharge,charge,discper,discrs,quantity from apm_invoice_assesments where invoiced='"+invoiceid+"' and apmtType=? ");
			ps.setString(1, chargename);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				
				double charge= rs.getDouble(2)*rs.getDouble(6);
				if(charge<=0){
					charge=rs.getDouble(3)*rs.getDouble(6);
					if(rs.getDouble(4)>0){
						double discpernew=rs.getDouble(4);
						double temp=(discpernew/100.0);
						temp=1.0-temp;
						charge=charge/temp;
						
					}else if(rs.getDouble(5)>0){
						charge=charge+rs.getDouble(5);
					}
					charge=Math.round(charge);
				}
				if(discper==null){
					discper="0";
				}
				if(discper.equals("0")){
					charge= charge-Double.parseDouble(dicsamt);
				}else{
					double discamt=0.0;
					discamt= (Double.parseDouble(discper)*charge)/100.0;
					charge=charge-discamt;
					dicsamt=""+Math.round(discamt);
				}
				PreparedStatement ps2= null;
				if(discper.equals("0")){
					ps2= connection.prepareStatement(" update  apm_invoice_assesments set charge='"+charge+"',discrs='"+dicsamt+"',chargedisc='"+dicsamt+"',discper='0'  where id ='"+rs.getInt(1)+"'");	
				}else{
					ps2= connection.prepareStatement(" update  apm_invoice_assesments set charge='"+charge+"',discper='"+discper+"',chargedisc='"+dicsamt+"' ,discrs='0'  where id ='"+rs.getInt(1)+"'");
				}
				 
				int res= ps2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int getChargesInvoiceIdByApmInvoiveId(String invoiceid) {
		int res=0;
		try {
			PreparedStatement ps=connection.prepareStatement("  select invoiced from apm_invoice_assesments where invoiceid='"+invoiceid+"'   ");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<CompleteAppointment> getUpdateSaveInvoiceCompleteApmtListGrouped(String invoiceid, int loginid) {
		ArrayList<CompleteAppointment> list= new ArrayList<CompleteAppointment>();
		try {
			String sql="select  apmtType,discrs,discper,invoiced FROM apm_patient_complete_apmt where invoiceid = "+invoiceid+" and loginid="+loginid+"  group by apmtType";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				CompleteAppointment completeAppointment= new CompleteAppointment();
				completeAppointment.setApmtType(rs.getString(1));
				completeAppointment.setDisc_amount(""+rs.getInt(2));
				completeAppointment.setDiscperc(""+rs.getInt(3));
				completeAppointment.setInvoiced(rs.getInt(4));
				list.add(completeAppointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getChargesInvoiceIdByApmInvoiveIdNew(String invoiceid) {
		int res=0;
		try {
			String sql="select chargeinvoiceid from   apm_charges_assesment where invoiceid='"+invoiceid+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<CompleteAppointment> getChargeCode(String invoiceid) {
		ArrayList<CompleteAppointment> list=new ArrayList<CompleteAppointment>();
			String res="";
			PreparedStatement preparedStatement=null;
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			try {
			String sql="select std_charge_id,chargeId from apm_invoice_assesments where invoiceid='"+invoiceid+"'";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				CompleteAppointment appointment=new CompleteAppointment();
				
					res = accountsDAO.getTpChargeCode(rs.getInt(2),rs.getString(1));
					appointment.setCode(res);
					list.add(appointment);
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			
		}
			return list;

	}

	public CompleteAppointment getChargeDetailsTemp(int id) {
		CompleteAppointment completeAppointment=new CompleteAppointment();
		PreparedStatement ps=null;
		try {
			String sql="select  user, apmtType, charge, clientId, commencing, appointmentid,invoiceid, quantity, ipdid, masterchargetype,unitcharge,invoiced,std_charge_id, chargeId from apm_patient_complete_apmt where id='"+id+"' ";
			ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				completeAppointment.setPatientname(rs.getString(1));
				completeAppointment.setApmtType(rs.getString(2));
				completeAppointment.setCharges(rs.getString(3));
				completeAppointment.setClientId(rs.getString(4));
				completeAppointment.setCommencing(rs.getString(5));
				completeAppointment.setAppointmentid(rs.getString(6));
				completeAppointment.setInvoiceid(rs.getString(7));
				completeAppointment.setQuantity(rs.getInt(8));
				completeAppointment.setIpdid(rs.getInt(9));
				completeAppointment.setMasterchargetype(rs.getString(10));
				completeAppointment.setUnitcharge(rs.getString(11));
				completeAppointment.setInvoiced(rs.getInt(12));
				completeAppointment.setStd_charge_id(rs.getString(13));
				completeAppointment.setChargeId(rs.getString(14));
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
				
				return completeAppointment;
	}

	public int insertModify_invoice_deleted(CompleteAppointment completeAppointment,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into modify_invoice_delete_log(date, patient, ipdid, clientid, chargename, chargetype, charge, userid, appointmentid, "
				+ "invoiceid, quantity, unitcharge, std_charge_id, charge_id, commencing,invoiced)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());



			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, datetime);
			preparedStatement.setString(2, completeAppointment.getPatientname());
			preparedStatement.setInt(3, completeAppointment.getIpdid());
			preparedStatement.setString(4, completeAppointment.getClientId());
			preparedStatement.setString(5, completeAppointment.getApmtType());
			preparedStatement.setString(6, completeAppointment.getMasterchargetype());
			preparedStatement.setString(7, completeAppointment.getCharges());
			preparedStatement.setString(8, loginInfo.getUserId());
			preparedStatement.setString(9, completeAppointment.getAppointmentid());
			preparedStatement.setString(10, completeAppointment.getInvoiceid());
			preparedStatement.setDouble(11, completeAppointment.getQuantity());
			preparedStatement.setString(12, completeAppointment.getUnitcharge());
			preparedStatement.setString(13, completeAppointment.getStd_charge_id());
			preparedStatement.setString(14, completeAppointment.getChargeId());
			preparedStatement.setString(15, completeAppointment.getCommencing());
			preparedStatement.setInt(16, completeAppointment.getInvoiced());
			result=preparedStatement.executeUpdate();
		
	}catch (Exception e) {
		e.printStackTrace();
	}
		return result;
	}

	public CompleteAppointment getopdlastappointment(String clientid,LoginInfo loginInfo) {
		CompleteAppointment completeAppointment=new CompleteAppointment();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String today = dateFormat.format(cal.getTime());
			
			 DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			    Calendar cal1 = Calendar.getInstance();
			    cal1.add(Calendar.DATE, -20);
			   String prevdate = dateFormat.format(cal1.getTime());
			   String sql="";
	       if(loginInfo.isBalgopal()) {
			   sql="select apmttypetext,commencing,charge from apm_available_slot where clientid='"+clientid+"' order by commencing desc,starttime desc limit 1";
  
	      }else {
			   sql="select apmttypetext,commencing,charge from apm_available_slot where clientid='"+clientid+"' and commencing between '"+prevdate+"' and '"+today+"' order by commencing desc,starttime desc limit 1";
			  }
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				completeAppointment.setApmtType(rs.getString(1));
				completeAppointment.setCommencing(rs.getString(2));
				 long diff=DateTimeUtils.getDifferenceOfTwoDateDBFormat(rs.getString(2), today);
				completeAppointment.setCharges(rs.getString(3));
				completeAppointment.setDuration(String.valueOf(diff));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return completeAppointment;
	}

	public ArrayList<String> getMissingAutoChargeDate(int ipdidadmiss, String clientId, String admissiondatenew,
			String dis_discharge_date) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat(admissiondatenew, dis_discharge_date);
			int k = (int)differ;
			for (int i=0; i <= k; i++) {
			   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			   Date d=sdf1.parse(admissiondatenew);
			   Calendar cal = Calendar.getInstance();
			   cal.setTime(d);
			   cal.add(Calendar.DATE, i);
			   String dt=sdf1.format(cal.getTime());
			   if(!admissiondatenew.equals(dt)){
				   String sql = "select id from apm_invoice_assesments where commencing='"+dt+"' and commencing!='"+admissiondatenew+"' and clientid='"+clientId+"' and std_charge_id>0 and ipdid='"+ipdidadmiss+"' limit 1";
				   PreparedStatement preparedStatement = connection.prepareStatement(sql);
				   ResultSet rs = preparedStatement.executeQuery();
				   if(rs.next()){
					   
				   }else{
					   arrayList.add(dt);
				   }
			   }
			   
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int getIPDWardIDFromLogWithHighPrice(int ipdidadmiss, String string, String admissiondatenew) {
		int wardid =0;
		try {
			String newdate = string +" "+"23:59:59";
			String sql ="select wardid,count(*) from ipd_bed_change_log where lastmodified between '"+string+"' and '"+newdate+"'  and admissionid='"+ipdidadmiss+"'  group by admissionid";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				wardid = rs.getInt(1);
				if(rs.getInt(2)>1){
					wardid = getLastWardIdFromLogWithHighPrice(ipdidadmiss,string);
				}
			}else{
				wardid = getLastWardIdFromLog(ipdidadmiss,string,admissiondatenew);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wardid;
	}

	private int getLastWardIdFromLog(int ipdidadmiss, String string, String admissiondatenew) {
		int wardid =0;
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date d=sdf1.parse(string);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.DATE, -1);
			String dt=sdf1.format(cal.getTime());
			String newdate = dt +" "+"23:59:59";
			String sql ="select wardid,count(*),selectedshiftdata from ipd_bed_change_log where selectedshiftdata between '"+dt+"' and '"+newdate+"' and admissionid='"+ipdidadmiss+"' group by admissionid order by lastmodified desc";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				wardid = rs.getInt(1);
				if(rs.getInt(2)>1){
					String date = rs.getString(3).split(" ")[0];
					wardid = getLastWardIdFromLogWithHighPrice(ipdidadmiss,date);
				}
			}else{
				long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat(admissiondatenew, dt);
				if(differ>=0){
					wardid = getLastWardIdFromLog(ipdidadmiss,dt,admissiondatenew);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wardid;
	}

	private int getLastWardIdFromLogWithHighPrice(int ipdidadmiss, String date) {
		int wardid =0;
		try {
			String newdate = date +" "+"23:59:59";
			String sql ="select wardid from ipd_bed_change_log where selectedshiftdata between '"+date+"' and '"+newdate+"' and admissionid='"+ipdidadmiss+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			String wardids="";
			while(rs.next()) {
				wardid = rs.getInt(1);
				if(wardids.equals("")){
					wardids = ""+wardid;
				}else{
					wardids = wardids+","+wardid;
				}
			}
			wardid = getMaxWardIdofPrice(wardids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wardid;
	}

	private int getMaxWardIdofPrice(String wardids) {
		int wardid =0;
		try {
			String sql ="select wardid,max(charges) from apm_appointment_type where wardid in("+wardids+") and stdcharge>0 group by wardid order by max(charges) desc limit 1 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				wardid = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wardid;
	}

	public int getMaxWardChargeID(int id, String today) {
		int wardid =0;
		try {
			String newdate = today +" "+"23:59:59";
			String sql ="select wardid,count(*) from ipd_bed_change_log where selectedshiftdata between '"+today+"' and '"+newdate+"'  and admissionid='"+id+"'  group by admissionid";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				wardid = rs.getInt(1);
				if(rs.getInt(2)>1){
					wardid = getLastWardIdFromLogWithHighPrice(id,today);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wardid;
	}

	public String getWardIDFromInvoice(String invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "0";
		String sql = "select wardid from apm_invoice_assesments where invoiceid="+invoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = ""+resultSet.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return result;
	}

	public int saveManualChargeLog(CompleteAppointment completeAppointment, int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into manual_charge_log(apmtname, masterchargetype, chargeid, unitcost, quantity, clientid, ipdid, commencing, userid, chargeinvoiceid,date)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		try{



			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, completeAppointment.getApmtType());
			preparedStatement.setString(2, completeAppointment.getMasterchargetype());
			preparedStatement.setInt(3, completeAppointment.getId());
			preparedStatement.setString(4, completeAppointment.getCharges());
			preparedStatement.setDouble(5, completeAppointment.getQuantity());
			preparedStatement.setString(6, completeAppointment.getClientId());
			preparedStatement.setInt(7, completeAppointment.getIpdid());
			preparedStatement.setString(8, completeAppointment.getCommencing());
			preparedStatement.setString(9, completeAppointment.getUserid());
			preparedStatement.setInt(10, invoiceid);
			preparedStatement.setString(11, completeAppointment.getDate());
			result=preparedStatement.executeUpdate();
		
	}catch (Exception e) {
		e.printStackTrace();
	}
		return result;
	}

	public int getifAutoChargeRaisedNew(String ipdid) {
		 int result=0;
		 try {
			 String sql="select id from apm_standard_acess_charge where ipdid="+ipdid+" limit 1";
			 
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 
			 if(rs.next()){
				 
				 result=rs.getInt(1);
			 }
			 
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		
		return result;
	
	}

	public boolean checkInvoicedFromAssesments(String chargeid) {
		 boolean result=false;
		 try {
			 String sql="select * from apm_invoice_assesments where id="+chargeid+" and invoiced=0";
			 
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 
			 if(rs.next()){
				 
				 result=true;
			 }
			 
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		
		return result;
	}

	@Override
	public boolean testRegChargeApply(int id) {
		boolean opdcharge=false;
		try {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			String sql ="select * from apm_invoice_assesments where clientId="+id+" and commencing between '"+year+"-01-01' and '"+year+"-12-31' and apmtType='Registration Charge'  limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				opdcharge = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opdcharge;
	}

	@Override
	public String getDocnamebyid(String docid) {
	String name="";
	PreparedStatement pst=null;
	try {
	      String sql="select concat(firstname,' ',lastname) from apm_user where id="+docid+"";	
		  pst=connection.prepareStatement(sql);
		  ResultSet rs=pst.executeQuery();
		  while(rs.next()) {
			  name=rs.getString(1);
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
		return name;
	}

	@Override
	public String getpractitonerdept(int id) {
		String dept="";
		PreparedStatement pst=null;
		try {
		      String sql="select discription from apm_user where id="+id+"";	
			  pst=connection.prepareStatement(sql);
			  ResultSet rs=pst.executeQuery();
			  while(rs.next()) {
				  dept=rs.getString(1);
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
			return dept;
	}

	@Override
	public int saveCompleteApmt(CompleteAppointment completeAppointment, String apmtId, int loginid) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		if(completeAppointment.getQuantity()==0){
			completeAppointment.setQuantity(1);
		}
		String sql = "insert into apm_patient_complete_apmt(user,apmtType,charge,clientId,commencing,appointmentid,quantity,masterchargetype,additioncharge_id,loginid)values(?,?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
		
			preparedStatement.setString(1, completeAppointment.getUser());
			preparedStatement.setString(2, completeAppointment.getApmtType());
			preparedStatement.setString(3, completeAppointment.getCharges());
		
			preparedStatement.setString(4, completeAppointment.getClientId());
			preparedStatement.setString(5, completeAppointment.getCommencing());
			
			preparedStatement.setString(6, apmtId);
			preparedStatement.setDouble(7, 1.0);
			
			preparedStatement.setString(8, completeAppointment.getMasterchargetype());
			
			preparedStatement.setString(9, completeAppointment.getAdditionalcharge_id());
			preparedStatement.setInt(10, loginid);
			result = preparedStatement.executeUpdate();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}

	@Override
	public int getmasterchargeExistOrNot(String clientId ,String masterchargetype) {
		 int result=0;
		 try {
			 String sql="select id from apm_patient_complete_apmt where clientId="+clientId+" and masterchargetype='"+masterchargetype+"' ";
			 
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 
			 if(rs.next()){
				 
				 result=1;
			 }
			 
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		
		return result;
		
	}

	@Override
	public String getMasterChargeType(String clientId) {
		String result="";
		 try {
			 String sql="select masterchargetype from apm_patient_complete_apmt where clientId="+clientId+" ";
			 
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 
			 if(rs.next()){
				 
				 result=rs.getString(1);
			 }
			 
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		
		return result;
	}

}
