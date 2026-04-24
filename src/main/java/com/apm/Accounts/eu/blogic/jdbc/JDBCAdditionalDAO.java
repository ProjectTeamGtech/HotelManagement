package com.apm.Accounts.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.AdditionalDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.SummaryReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCSummaryReportDAO;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

public class JDBCAdditionalDAO extends JDBCBaseDAO implements AdditionalDAO{
	
	public JDBCAdditionalDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<AppointmentType> getAdditionalChargesList() {
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentType>list = new ArrayList<AppointmentType>();
		String sql  = "SELECT id,name,description,category,duration,charges,location,chargeType,reportField FROM apm_appointment_type where tpid=0";
		
	
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				AppointmentType appointmentType = new AppointmentType();
				
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				appointmentType.setDescription(rs.getString(3));
				appointmentType.setCategory(rs.getString(4));
				appointmentType.setDuration(rs.getString(5));				
				appointmentType.setCharges(rs.getString(6));
				appointmentType.setLocation(rs.getString(7));
				appointmentType.setChargeType(rs.getString(8));
				appointmentType.setReportField(rs.getString(9));
				
				list.add(appointmentType);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveInvoce(String clientId, String clientname, String type,
			String date,String location,String apmtid,String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_invoice(clientid,clientname,date,chargetype,location,apmt_charge_type,appointmentid,prepareuserid) values(?,?,?,?,?,?,?,?)";
		int invoiceid = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, clientname);
			preparedStatement.setString(3, date);
			preparedStatement.setString(4, "Charge");
			preparedStatement.setString(5, location);
			preparedStatement.setInt(6, 1);
			preparedStatement.setString(7, apmtid);
			//preparedStatement.setString(7, location);
			preparedStatement.setString(8, userid);
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

	public int saveAssessment(String clientId, String clientname, String type,
			String date, int invoiceid,CompleteAppointment c1, String docid,String outsource) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		//set department to charges
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		CompleteAptmDAO  completeAptmDAO = new JDBCCompleteAptmDAO(connection);
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
		
		boolean islmh = userProfileDAO.getIsLMH();
		if(islmh){
			CompleteAppointment appointment = completeAptmDAO.getChargeDiscDetails(c1.getChargeId());
			if(appointment.getDiscountShare()>0){
				double qty =c1.getQuantity();
				double discDiff = (DateTimeUtils.convertToDouble(appointment.getOriginalAmt())*qty) - (DateTimeUtils.convertToDouble(c1.getCharges())*qty);
				c1.setChargedisc(""+discDiff);				
				c1.setUnitcharge(appointment.getOriginalAmt());
				c1.setDiscperc(""+appointment.getDiscountShare());
			}
		}
		
		ChargesAccountProcessingDAO chargesAccountProcessingDAO=new  JDBCChargeAccountProcesDAO(connection);
		int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(c1.getMasterchargetype());
		
		String sql = "insert into apm_invoice_assesments (user,apmtType,charge,clientId,paybuy,markAppointment,"
				+ "invoiceid,thirdPartyId,commencing,transactiontype,quantity,ipdid,masterchargetype,"
				+ "chargeId,department,isshareablecharge,sharedrid,practitionerId, practitionerName,chargedisc,"
				+ "unitcharge,docid,outsource) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientname);
			
			if(compulsary_consultant==1){
				preparedStatement.setString(2, c1.getApmtType()+" - "+c1.getPractitionerName());
			}else{
				preparedStatement.setString(2, c1.getApmtType());
			}
			//AppointmentType a = getAppointmentTypeDetails(c1.getId());
			
			preparedStatement.setString(3, c1.getCharges());
			preparedStatement.setString(4, clientId);
			preparedStatement.setString(5, c1.getPayBuy());
			preparedStatement.setString(6, "1");
			preparedStatement.setInt(7,invoiceid);	
			if(c1.getPayBuy().equalsIgnoreCase("1")){
				int tpid = getTPId(clientId);
				preparedStatement.setInt(8, tpid);

			}
			else{
				preparedStatement.setString(8, "0");

			}
			preparedStatement.setString(9, date);
			preparedStatement.setString(10, c1.getChargeType());
			preparedStatement.setDouble(11, c1.getQuantity());
			preparedStatement.setInt(12, c1.getIpdid());
			preparedStatement.setString(13, c1.getMasterchargetype());
			preparedStatement.setString(14, c1.getChargeId()); 
			preparedStatement.setString(15, condition);
			preparedStatement.setString(16, c1.getIsindisharecharge());
			preparedStatement.setString(17, c1.getVisitingconsulatntdrid());
			preparedStatement.setString(18, c1.getPractitionerId());
			preparedStatement.setString(19, c1.getPractitionerName());
			preparedStatement.setString(20, DateTimeUtils.numberCheck(c1.getChargedisc()));
			preparedStatement.setString(21, c1.getUnitcharge());
			preparedStatement.setString(22, docid);
			preparedStatement.setString(23, outsource);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private int getTPId(String clientId) {
		int id = 0;
		PreparedStatement preparedStatement = null;
		
		String sql = "select third_party_name_id from apm_patient where id = "+clientId+"";
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

	public AppointmentType getAppointmentTypeDetails(String type) {
		PreparedStatement preparedStatement = null;
		AppointmentType appointmentType = new AppointmentType();

		String sql  = "SELECT id,name,description,category,duration,charges,location,chargeType,reportField FROM apm_appointment_type where id = "+type+"";
		
	
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				appointmentType.setDescription(rs.getString(3));
				appointmentType.setCategory(rs.getString(4));
				appointmentType.setDuration(rs.getString(5));				
				appointmentType.setCharges(rs.getString(6));
				appointmentType.setLocation(rs.getString(7));
				appointmentType.setChargeType(rs.getString(8));
				appointmentType.setReportField(rs.getString(9));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return appointmentType;
	}

	public int saveCharge(String clientId, String clientname, String type,
			String date,String creditDebitCharge,String payby,int loginid) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		
		
		
	
		
		String sql = "insert into apm_patient_complete_apmt(user,apmtType,charge,clientId,commencing,paybuy,markAppointment,transactiontype,loginid)values(?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
		
			preparedStatement.setString(1, clientname);
			
			AppointmentType a = getAppointmentTypeDetails(type);
			
			preparedStatement.setString(2, a.getName());
			preparedStatement.setString(3, a.getCharges());
			preparedStatement.setString(4, clientId);
			preparedStatement.setString(5, date);
			preparedStatement.setString(6, payby);
			preparedStatement.setString(7, "1");
			preparedStatement.setString(8, creditDebitCharge);
			preparedStatement.setInt(9, loginid);
			result = preparedStatement.executeUpdate();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<CompleteAppointment> getPatientChrageDetails(String id,int loginid) {
		PreparedStatement preparedStatement = null;
		ArrayList<CompleteAppointment> clientChargeListDetail = new ArrayList<CompleteAppointment>();
		String sql = "SELECT id,user,apmtType,charge,clientId,commencing,paybuy,transactiontype FROM apm_patient_complete_apmt where clientId = "+id+" and loginid="+loginid+" ";
		double chargeTotal = 0;
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				CompleteAppointment completeAppointment = new CompleteAppointment();
				completeAppointment.setId(rs.getInt(1));
				completeAppointment.setUser(rs.getString(2));
				completeAppointment.setApmtType(rs.getString(3));
				completeAppointment.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(4))));
				
				completeAppointment.setClientId(rs.getString(5));
				completeAppointment.setCommencing(rs.getString(6));
				double total = Double.parseDouble((rs.getString(4)));
				chargeTotal = chargeTotal + total;
				completeAppointment.setChargeTotal(chargeTotal);
				completeAppointment.setPayBuy(rs.getString(7));
				completeAppointment.setChargeType(rs.getString(8));
				
				completeAppointment.setChargeTotalx(DateTimeUtils.changeFormat(chargeTotal));
				
				clientChargeListDetail.add(completeAppointment);
				
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return clientChargeListDetail;
	}

	public ArrayList<CompleteAppointment> getCompleteApmtList(String clientId,int loginid) {
		PreparedStatement preparedStatement = null;
		ArrayList<CompleteAppointment>list = new ArrayList<CompleteAppointment>();
		
		String sql = "SELECT id,user,apmtType,charge,clientId,commencing,paybuy,markAppointment,transactiontype,quantity, ipdid, masterchargetype,additioncharge_id,isshareablecharge,sharedrid,practitionerName,tax1,tax2,tax3,chargedescript,tpkg,manualcharge,practitionerId FROM apm_patient_complete_apmt where clientId = "+clientId+" and loginid="+loginid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				CompleteAppointment completeAppointment = new CompleteAppointment();
				completeAppointment.setId(rs.getInt(1));
				completeAppointment.setUser(rs.getString(2));
				completeAppointment.setApmtType(rs.getString(3));
				completeAppointment.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(4))));
				completeAppointment.setClientId(rs.getString(5));
				completeAppointment.setCommencing(rs.getString(6));
				completeAppointment.setPayBuy(rs.getString(7));
				completeAppointment.setMarkAppointment(rs.getString(8));
				completeAppointment.setChargeType(rs.getString(9));
				completeAppointment.setQuantity(rs.getInt(10));
				completeAppointment.setIpdid(rs.getInt(11));
				completeAppointment.setMasterchargetype(rs.getString(12));
				completeAppointment.setChargeId(rs.getString(13));
				completeAppointment.setIsindisharecharge(rs.getString(14));
				completeAppointment.setVisitingconsulatntdrid(rs.getString(15));
				completeAppointment.setPractitionerName(rs.getString(16));
				completeAppointment.setTax1(""+rs.getDouble(17));
				completeAppointment.setTax2(""+rs.getDouble(18));
				completeAppointment.setTax3(""+rs.getDouble(19));
				completeAppointment.setTotaltax(""+(rs.getDouble(17)+rs.getDouble(18)+rs.getDouble(19)));
				completeAppointment.setChargedescription(rs.getString(20));
				completeAppointment.setTpkg(rs.getInt(21));
				completeAppointment.setManuallyadded(rs.getString(22));
				completeAppointment.setPractitionerId(rs.getString(23));
				list.add(completeAppointment);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveManualCharge(String clientId, String clientname,String manualTypeName, String manualCharge, 
			String date,String creditDebitCharge,String payby,int loginid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_patient_complete_apmt(user,apmtType,charge,clientId,commencing,paybuy,markAppointment,transactiontype,loginid)values(?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
		
			preparedStatement.setString(1, clientname);
			
			//AppointmentType a = getAppointmentTypeDetails(manualTypeName);
			
			preparedStatement.setString(2, manualTypeName);
			preparedStatement.setString(3, manualCharge);
			preparedStatement.setString(4, clientId);
			preparedStatement.setString(5, date);
			preparedStatement.setString(6, payby);
			preparedStatement.setString(7, "1");
			preparedStatement.setString(8, creditDebitCharge);
			preparedStatement.setInt(9, loginid);
			result = preparedStatement.executeUpdate();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getAdditionalCharge(String type) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select charges from apm_appointment_type where id = "+type+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch(Exception e){
			
		}
		
		return result;
	}

	public int saveCreditInvoice(String clientId, String clientname,
			String type, String date, String location) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_credit_account(date,client_id,advance_ipdid) values(?,?,?)";
		int invoiceid = 0;
		try{
			//lokesh 14/11/2018
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			int ipdid=ipdDAO.getLastIpdId(clientId);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, clientId);
			preparedStatement.setInt(3, ipdid);
		
			
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

	public int saveCreditAssessment(String clientId, String clientname,
			String type1, String date, int invoiceid,
			CompleteAppointment c1) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_credit_account_assesments (user,apmtType,charge,clientId,paybuy,markAppointment,invoiceid,thirdPartyId,commencing,transactiontype,quantity, ipdid, masterchargetype) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientname);
			
			//AppointmentType a = getAppointmentTypeDetails(c1.getId());
			preparedStatement.setString(2, c1.getApmtType());
			preparedStatement.setString(3, c1.getCharges());
			preparedStatement.setString(4, clientId);
			preparedStatement.setString(5, c1.getPayBuy());
			preparedStatement.setString(6, "1");
			preparedStatement.setInt(7,invoiceid);	
			if(c1.getPayBuy().equalsIgnoreCase("1")){
				int tpid = getTPId(clientId);
				preparedStatement.setInt(8, tpid);

			}
			else{
				preparedStatement.setString(8, "0");

			}
			preparedStatement.setString(9, date);
			preparedStatement.setString(10, c1.getChargeType());
			preparedStatement.setDouble(11, c1.getQuantity());
			preparedStatement.setInt(12, c1.getIpdid());
			preparedStatement.setString(13, c1.getMasterchargetype());
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int saveTempCreditAccounts(int invoiceid,String loginsessonid) {
		PreparedStatement preparedStatement = null;
		int result  = 0;
		String sql = "insert into apm_tempcharge_account(invoiceid,usersessionid) values(?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, invoiceid);
			preparedStatement.setString(2, loginsessonid);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int updateCredit(String payby, double debitTotal,
			String submitInvoiceNotes, String howpaid,String creditId,double balance) {
		PreparedStatement preparedStatement = null;
		int result  = 0;
		String sql = "update apm_credit_account set payby = ?,charge = ?,credit_note = ?,payment_mode = ?, balance = ? where id = "+creditId+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, payby);
			preparedStatement.setDouble(2, debitTotal);
			preparedStatement.setString(3, submitInvoiceNotes);
			preparedStatement.setString(4, howpaid);
			preparedStatement.setDouble(5, balance);

			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Accounts> getCreditAccountList(String clientId,String ipdid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		//String sql = "SELECT id,date,payby,payment_mode,charge,debit,balance FROM apm_credit_account where client_id = "+clientId+" and payment_mode IS NOT NULL";
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_credit_account.id,date,payby,payment_mode,charge,debit,balance,third_party_name_id,concat(title,' ',firstname,' ',surname),advref,manualinvoiceid,refinvoiceid,invoiceid FROM apm_credit_account inner join ");
		sql.append("apm_patient on apm_patient.id = apm_credit_account.client_id where client_id="+clientId+" and payment_mode IS NOT NULL ");
		if(!ipdid.equals("")){
		sql.append("  and apm_credit_account.advance_ipdid='"+ipdid+"'  ");
		}
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				String date = DateTimeUtils.getIndianDateTimeFormat(rs.getString(2));
				String temp[] = date.split(" ");		
				accounts.setCommencing(temp[0]);
				accounts.setPayby(rs.getString(3));
				accounts.setPaymentmode(rs.getString(4));
				accounts.setCharges(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(5))));
				accounts.setDebitTotalx(DateTimeUtils.changeFormat(Double.parseDouble(rs.getString(6))));
				
				double credit = rs.getDouble(5);
				double debit = rs.getDouble(6);
				double balance = rs.getDouble(7);
				
				if(rs.getString(3).equals(Constants.PAY_BY_THIRD_PARTY)){
					
					CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				    CompleteAppointment	completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(8));
				    accounts.setClientName(completeAppointment.getInsuranceCompanyName());
				}
				else{
					
					accounts.setClientName(rs.getString(9));
				}
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				accounts.setAdvref(rs.getInt(10));
				if(rs.getString(11)==null){
					accounts.setManualinvoiceid("0");
				}else{
					if(rs.getString(11).equals("")){
						accounts.setManualinvoiceid("0");
					}else{
						accounts.setManualinvoiceid(rs.getString(11));
					}
				}
				accounts.setRefinvoiceid(rs.getString(12));
				
				
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			      Calendar cal = Calendar.getInstance();
			      String dt = dateFormat.format(cal.getTime()); 
			      if(accounts.getCommencing()!=null){
			    	  if(dt.equals(accounts.getCommencing())){
			    		  accounts.setTodays(true);
			    	  }
			      }
			      accounts.setInvoiceid(rs.getInt(13));
			      if(accounts.getInvoiceid()>0){
			    	  String Notes=getCancelNote(rs.getInt(13));
			    	  accounts.setCancelNotes(Notes);
			      }
				list.add(accounts);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private String getCancelNote(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select cancelNotes from apm_charges_invoice where id = "+invoiceid+"";
		
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

	public double getCreditTotal(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select balance from apm_credit_account where client_id = "+clientId+"  and payment_mode IS NOT NULL order by id desc limit 1 ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int saveDebitRecord(String clientId, String type, String date,
			String creditnote, String payBuy, String charge,String paymode,double balance,int advref,int rinv,String userid, String manualinvoiceid, String refundnote,String refinvoiceid,String invtype,LoginInfo loginInfo,String refPayMode) {
		invtype=DateTimeUtils.isNull(invtype);
		/*if(loginInfo.isBalgopal()){*/
			
		if(invtype.equals("2")){
			invtype="9";
		}
		/*}*/
		PreparedStatement preparedStatement = null;
		int result = 0;
		int invoiceid = 0;
		String sql = "insert into apm_credit_account(date,client_id,payby,debit,credit_note,payment_mode,balance,advref,resetinv,userid,manualinvoiceid,refundnote,advance_ipdid,invoice_type,wardid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if(payBuy.equals(Constants.PAY_BY_THIRD_PARTY) || payBuy.equals("1")){
			payBuy = Constants.PAY_BY_THIRD_PARTY;
		}else{
			payBuy = Constants.PAY_BY_CLIENT;
		}
		
		if(manualinvoiceid!=null){
			if(!manualinvoiceid.equals("")){
				if(balance<0){
					balance=0;
				}
			}
			
		}
		
		try{
			int ipdid=0;
			if(manualinvoiceid!=null){
				if(!manualinvoiceid.equals("")){
					 ipdid=getipdIdFromInv(manualinvoiceid);
					}else{
						//lokesh 14/11/2018
						IpdDAO ipdDAO= new JDBCIpdDAO(connection);
						 ipdid=ipdDAO.getLastIpdId(clientId);
					}
				}else{
					//lokesh 14/11/2018
					IpdDAO ipdDAO= new JDBCIpdDAO(connection);
					 ipdid=ipdDAO.getLastIpdId(clientId);
				}
			
			if(refinvoiceid!=null){
				if(!refinvoiceid.equals("")){
					String refpaymode=DateTimeUtils.isNull(refPayMode);
					if(!refpaymode.equals("")){
						paymode=refpaymode;
					}else{
						paymode="Cash";
					}
					
					advref=1;
				}
			}
			preparedStatement = connection.prepareStatement(sql);
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			int wardid=ipdDAO.getwardId(ipdid,clientId);
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, clientId);
			preparedStatement.setString(3, payBuy);
			preparedStatement.setString(4, charge);
			preparedStatement.setString(5, creditnote);
			preparedStatement.setString(6, paymode);
			preparedStatement.setDouble(7, balance);
			preparedStatement.setInt(8, advref);
			preparedStatement.setInt(9, rinv);
			preparedStatement.setString(10, userid);
			preparedStatement.setString(11, manualinvoiceid);
			preparedStatement.setString(12, refundnote);
			preparedStatement.setInt(13, ipdid);
			preparedStatement.setString(14, invtype);
			preparedStatement.setInt(15, wardid);
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					invoiceid = resultSet.getInt(1);  
						if(charge==null){
							charge="0.0";
						}
						
						if(!paymode.equals("prepayment")){
							if(!charge.equals("0.0")){
								saveToPhysicalHISPayment("", String.valueOf(invoiceid), clientId, date, paymode, charge, "5","Refund");
							}
							
						}
						
					
				}
			}

			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return invoiceid;
	}

	

	public int saveCreditRecord(String clientId, String type, String date,
			String creditnote, String payBuy, String charge,String paymode,double balance,int rinv,String userid,int invoiceeid) {
		
		if(payBuy.equals("1") || payBuy.equals(Constants.PAY_BY_THIRD_PARTY)){
			payBuy = Constants.PAY_BY_THIRD_PARTY;
		}else{
			payBuy = Constants.PAY_BY_CLIENT;
		}
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		int invoiceid = 0;
		String sql = "insert into apm_credit_account(date,client_id,payby,charge,credit_note,payment_mode,balance,resetinv,userid,advance_ipdid,invoiceid,wardid) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			//lokesh 14/11/2018
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			int ipdid=ipdDAO.getLastIpdId(clientId);
			int wardid=ipdDAO.getwardId(ipdid,clientId);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, clientId);
			preparedStatement.setString(3, payBuy);
			preparedStatement.setString(4, charge);
			preparedStatement.setString(5, creditnote);
			preparedStatement.setString(6, paymode);
			preparedStatement.setDouble(7, balance);
			preparedStatement.setInt(8, rinv);
			preparedStatement.setString(9, userid);
			preparedStatement.setInt(10, ipdid);
			preparedStatement.setInt(11, invoiceeid);
			preparedStatement.setInt(12, wardid);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					invoiceid = resultSet.getInt(1);  
					saveToPhysicalHISPayment("", String.valueOf(invoiceid), clientId, date, paymode, charge, "5","Advance");
					
				}
			}

			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return invoiceid;
	}

	public int saveCreditAssessmentRecord(String clientId, String clientname,
			String type, String date, int invoiceid,String charge,int advref) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_credit_account_assesments(invoiceid,apmtType,charge,clientid,commencing,paybuy,markAppointment,thirdPartyId,active,transactiontype,advref) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, invoiceid);
			preparedStatement.setString(2, type);
			preparedStatement.setString(3, charge);
			preparedStatement.setString(4, clientId);
			preparedStatement.setString(5, date);
			preparedStatement.setString(6, "0");
			preparedStatement.setInt(7, 1);
			preparedStatement.setInt(8, 0);
			preparedStatement.setInt(9, 1);
			preparedStatement.setInt(10, 1);
			preparedStatement.setInt(11, advref);
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

	public double getAdvanceBalance(String clientid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT balance FROM apm_credit_account where client_id = "+clientid+" and balance!=0 order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateRefundInvoice(String invoiceid,int crinvoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_credit_account set refinvoiceid="+invoiceid+" where id="+crinvoiceid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int checkInvoiceIdofClient(String invoiceid, String clientid) {
		int res = 0;
		try {
			String sql ="select id from apm_charges_invoice where id='"+invoiceid+"' and clientid='"+clientid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getDebitFromInvoiceId(String invoiceid) {
		String result = "0";
		try {
			String sql ="select debit from apm_charges_invoice where id='"+invoiceid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result =rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveRequestRefund(String clientId, String clientname, String type, String date, String location,
			String creditDebitCharge, String manualinvoiceid, String refundnote, String userId, String requestedate) {
		int result = 0;
		try{
			String sql = "insert into refund_request_parent(clientid, clientname, type, date, location, creditDebitCharge, manualinvoiceid, refundnote, requested_date, requested_userid,isfromadvance) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, clientname);
			preparedStatement.setString(3, type);
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, location);
			preparedStatement.setString(6, creditDebitCharge);
			preparedStatement.setString(7, manualinvoiceid);
			preparedStatement.setString(8, refundnote);
			preparedStatement.setString(9, requestedate);
			preparedStatement.setString(10, userId);
			if(manualinvoiceid!=null){
				if(manualinvoiceid.equals("")){
					preparedStatement.setString(11, "0");
				}else{
					preparedStatement.setString(11, "1");
				}
			}else{
				preparedStatement.setString(11, "0");
			}
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);  
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int saveRequestRefundChild(int parentid, String clientId, int loginid) {
		PreparedStatement preparedStatement = null;
		int res =0;
		String sql = "SELECT user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid,quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid, loginid, invpkgid,deletechargeid FROM apm_patient_complete_apmt where clientId = "+clientId+" and loginid="+loginid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				String sql1 = "insert into refund_request_child(user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid,quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid, loginid, invpkgid,parentid,deletechareid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement1 = null;
				preparedStatement1 = connection.prepareStatement(sql1);
				preparedStatement1.setString(1, rs.getString(1));
				preparedStatement1.setString(2, rs.getString(2));
				preparedStatement1.setString(3, rs.getString(3));
				preparedStatement1.setString(4, rs.getString(4));
				preparedStatement1.setString(5, rs.getString(5));
				preparedStatement1.setString(6, rs.getString(6));
				preparedStatement1.setString(7, rs.getString(7));
				preparedStatement1.setString(8, rs.getString(8));
				preparedStatement1.setString(9, rs.getString(9));
				preparedStatement1.setString(10, rs.getString(10));
				preparedStatement1.setString(11, rs.getString(11));
				preparedStatement1.setString(12, rs.getString(12));
				preparedStatement1.setString(13, rs.getString(13));
				preparedStatement1.setString(14, rs.getString(14));
				preparedStatement1.setString(15, rs.getString(15));
				preparedStatement1.setString(16, rs.getString(16));
				preparedStatement1.setString(17, rs.getString(17));
				preparedStatement1.setString(18, rs.getString(18));
				preparedStatement1.setString(19, rs.getString(19));
				preparedStatement1.setString(20, rs.getString(20));
				preparedStatement1.setString(21, ""+parentid);
				preparedStatement1.setString(22, rs.getString(21));
				res = preparedStatement1.executeUpdate();
				if(res == 1){
					ResultSet resultSet = preparedStatement.getGeneratedKeys();
					if(resultSet.next()){
						res = resultSet.getInt(1);  
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<CompleteAppointment> getRefundRequestList(String fromdate, String todate,String ipdopdwise, String searchtext, boolean accessofapprove, int id, String filter_status, String userid, String countdata) {
		ArrayList<CompleteAppointment> arrayList = new ArrayList<CompleteAppointment>();
		try {
			StringBuilder builder = new StringBuilder();
			todate = todate +" "+"59:59:59";
			builder.append("select refund_request_parent.id, refund_request_parent.clientid, clientname, refund_request_parent.manualinvoiceid,refund_request_parent.refundnote, requested_date, requested_userid, approve_date, approve_userid,refund_request_parent.status, isfromadvance,approvednote,apm_charges_invoice.itype,apm_charges_invoice.isdeleted,abrivationid,isfromdisc,cancel_reason,refund_request_parent.isdeleted ");
			builder.append("from refund_request_parent ");
			builder.append("left join apm_charges_invoice on  apm_charges_invoice.id = refund_request_parent.manualinvoiceid ");
			builder.append("left join apm_patient on apm_patient.id = apm_charges_invoice.clientId ");
			
			if(countdata.equals("")){
				builder.append("where refund_request_parent.isdeleted=0 and requested_date between '"+fromdate+"' and '"+todate+"' ");
				if(searchtext!=null){
					builder.append("and (clientname like '%"+searchtext+"%' or refund_request_parent.clientid='"+searchtext+"' or refund_request_parent.id='"+searchtext+"') ");
				}
				if(!filter_status.equals("")){
					builder.append("and status='"+filter_status+"' ");
				}
				if(!accessofapprove){
					builder.append("and (apm_charges_invoice.practid='"+id+"' or requested_userid='"+userid+"') ");
				}
				
			}else{
				if(countdata.equals("2")) {
					builder.append("where refund_request_parent.isdeleted=1 and requested_date between '"+fromdate+"' and '"+todate+"' ");
				}else {
				builder.append("where refund_request_parent.isdeleted=0 and  refund_request_parent.status='"+countdata+"' ");
				if(!accessofapprove){
					builder.append("and (apm_charges_invoice.practid='"+id+"' or requested_userid='"+userid+"') ");
				}
			}
		}
		
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				CompleteAppointment completeAppointment = new CompleteAppointment();
				completeAppointment.setId(rs.getInt(1));
				completeAppointment.setClientId(rs.getString(2));
				completeAppointment.setClient(rs.getString(3));
				completeAppointment.setManualinvoiceid(rs.getString(4));
				completeAppointment.setRefundnote(rs.getString(5));
				String reqdate = "";
				if(rs.getString(6)!=null){
					String[] data = rs.getString(6).split(" ");
					reqdate = DateTimeUtils.getCommencingDate1(data[0]) +" "+data[1];
				}
				completeAppointment.setRequested_date(reqdate);
				completeAppointment.setRequested_userid(rs.getString(7));
				if(!(rs.getString(8)==null || rs.getString(8).equals(""))){
					String approvedate = rs.getString(8);
					approvedate = DateTimeUtils.getIndianDateTimeFormat(approvedate);
					completeAppointment.setApprove_date(approvedate);
				}else{
					completeAppointment.setApprove_date("");
				}
				//completeAppointment.setApprove_date(rs.getString(8));
				completeAppointment.setApprove_userid(rs.getString(9));
				completeAppointment.setStatus(rs.getString(10));
				completeAppointment.setIsfromadvance(rs.getString(11));
				String total = getTotalRefundCharge(rs.getInt(1));
				completeAppointment.setCharges(total);
				completeAppointment.setApprove_note(rs.getString(12));
				/*if(rs.getInt(13)==1){
					completeAppointment.setChargeType("OPD");
				}else if(rs.getInt(13)==2){
					completeAppointment.setChargeType("IPD");
				}else if(rs.getInt(13)==3){
					completeAppointment.setChargeType("INVESTIGATION");
				}else if(rs.getInt(13)==4){
					completeAppointment.setChargeType("MEDICINE");
				}else if(rs.getInt(13)==5){
					completeAppointment.setChargeType("Advance & Refund");
				}else if(rs.getInt(13)==6){
					completeAppointment.setChargeType("HD");
				}else if(rs.getInt(13)==7){
					completeAppointment.setChargeType("Blood Bank");
				}else{
					completeAppointment.setChargeType("");
				}*/
				completeAppointment.setChargeType(accountsDAO.getInvoiceName(rs.getString(13)));
				completeAppointment.setDeleted(rs.getInt(14));
				completeAppointment.setAbrivationid(rs.getString(15));
				completeAppointment.setIsfromdisc(rs.getInt(16));
				completeAppointment.setDeleteremark(rs.getString(17));
				completeAppointment.setIsdeleted(rs.getInt(18));
				arrayList.add(completeAppointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private String getTotalRefundCharge(int int1) {
		String result = "0";
		try {
			String sql ="select charge,quantity from refund_request_child where parentid='"+int1+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String charge = rs.getString(1);
				int qty = rs.getInt(2);
				String total =String.valueOf(Double.parseDouble(charge) * qty);
				result = String.valueOf(Double.parseDouble(total) + Double.parseDouble(result));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int cancelRefundrequest(String parentid, String userid, String date,String delete_reason) {
		int result = 0;
		try {
			String sql = "update refund_request_parent set isdeleted=1,cancel_userid='"+userid+"',cancel_date='"+date+"',cancel_reason='"+delete_reason+"' where id="+parentid+"";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int approveRefundrequest(String id, String userid, String date,String approve_reason) {
		int result =
				0;
		try {
			String sql = "update refund_request_parent set approve_userid='"+userid+"',approve_date='"+date+"',status=1,approvednote='"+approve_reason+"' where id="+id+"";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int saveRefundTemp(String id, int id2) {
		int res =0;
		String sql = "SELECT user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid,quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid, loginid, invpkgid FROM refund_request_child  where parentid="+id+" ";
		try{
			PreparedStatement preparedStatement =null;
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				String sql1 = "insert into apm_patient_complete_apmt(user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid,quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid, loginid, invpkgid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement1 = null;
				preparedStatement1 = connection.prepareStatement(sql1);
				preparedStatement1.setString(1, rs.getString(1));
				preparedStatement1.setString(2, rs.getString(2));
				preparedStatement1.setString(3, rs.getString(3));
				preparedStatement1.setString(4, rs.getString(4));
				preparedStatement1.setString(5, rs.getString(5));
				preparedStatement1.setString(6, rs.getString(6));
				preparedStatement1.setString(7, rs.getString(7));
				preparedStatement1.setString(8, rs.getString(8));
				preparedStatement1.setString(9, rs.getString(9));
				preparedStatement1.setString(10, rs.getString(10));
				preparedStatement1.setString(11, rs.getString(11));
				preparedStatement1.setString(12, rs.getString(12));
				preparedStatement1.setString(13, rs.getString(13));
				preparedStatement1.setString(14, rs.getString(14));
				preparedStatement1.setString(15, rs.getString(15));
				preparedStatement1.setString(16, rs.getString(16));
				preparedStatement1.setString(17, rs.getString(17));
				preparedStatement1.setString(18, rs.getString(18));
				preparedStatement1.setString(19, ""+id2);
				preparedStatement1.setString(20, rs.getString(20));
				res = preparedStatement1.executeUpdate();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}
	
	public CompleteAppointment getRefundRequestData(String id) {
		CompleteAppointment completeAppointment = new CompleteAppointment();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select refund_request_parent.id, refund_request_parent.clientid, clientname, manualinvoiceid,");
			builder.append("refundnote, requested_date, requested_userid,approve_date, approve_userid, status, isfromadvance,appointmentid ");
			builder.append("from refund_request_parent inner join refund_request_child on ");
			builder.append("refund_request_child.parentid = refund_request_parent.id ");
			builder.append("where isdeleted=0 and refund_request_parent.id="+id+" ");
			//String sql ="  ";
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				completeAppointment.setId(rs.getInt(1));
				completeAppointment.setClientId(rs.getString(2));
				completeAppointment.setClient(rs.getString(3));
				completeAppointment.setManualinvoiceid(rs.getString(4));
				completeAppointment.setRefundnote(rs.getString(5));
				String reqdate = "";
				if(rs.getString(6)!=null){
					String[] data = rs.getString(6).split(" ");
					reqdate = DateTimeUtils.getCommencingDate1(data[0]) +" "+data[1];
				}
				completeAppointment.setRequested_date(reqdate);
				completeAppointment.setRequested_userid(rs.getString(7));
				completeAppointment.setApprove_date(rs.getString(8));
				completeAppointment.setApprove_userid(rs.getString(9));
				completeAppointment.setStatus(rs.getString(10));
				completeAppointment.setIsfromadvance(rs.getString(11));
				String total = getTotalRefundCharge(rs.getInt(1));
				completeAppointment.setAppointmentid(rs.getString(12));
				completeAppointment.setCharges(total);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return completeAppointment;
	}

	public int updateRefundRequest(String refundrequestid, String userId, String datetime) {
		int result = 0;
		try {
			String sql = "update refund_request_parent set given_userid='"+userId+"',given_date='"+datetime+"',status=2 where id="+refundrequestid+"";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<CompleteAppointment> getClientRefundRequestList(String clientid) {
		ArrayList<CompleteAppointment> arrayList = new ArrayList<CompleteAppointment>();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select id, clientid, clientname, manualinvoiceid, refundnote, requested_date, requested_userid, ");
			builder.append("approve_date, approve_userid, status, isfromadvance from refund_request_parent ");
			builder.append("where isdeleted=0 and clientid='"+clientid+"' ");
			String sql ="  ";
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				CompleteAppointment completeAppointment = new CompleteAppointment();
				completeAppointment.setId(rs.getInt(1));
				completeAppointment.setClientId(rs.getString(2));
				completeAppointment.setClient(rs.getString(3));
				completeAppointment.setManualinvoiceid(rs.getString(4));
				completeAppointment.setRefundnote(rs.getString(5));
				String reqdate = "";
				if(rs.getString(6)!=null){
					String[] data = rs.getString(6).split(" ");
					reqdate = DateTimeUtils.getCommencingDate1(data[0]) +" "+data[1];
				}
				completeAppointment.setRequested_date(reqdate);
				completeAppointment.setRequested_userid(rs.getString(7));
				completeAppointment.setApprove_date(rs.getString(8));
				completeAppointment.setApprove_userid(rs.getString(9));
				completeAppointment.setStatus(rs.getString(10));
				completeAppointment.setIsfromadvance(rs.getString(11));
				String total = getTotalRefundCharge(rs.getInt(1));
				completeAppointment.setCharges(total);
				arrayList.add(completeAppointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int saveRefundTempFromDeleteInvoice(String id, int id2) {
		int res =0;
		String sql = "SELECT user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid,quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid, loginid, invpkgid,id,discamount,discperc FROM invoice_delete_charges  where actualinvoiceid="+id+" and status=0 and ispaydonestatus=1 ";
		try{
			PreparedStatement preparedStatement =null;
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double charge = rs.getDouble(3);
				double disc = rs.getDouble(22);
				charge = charge- disc;
				String sql1 = "insert into apm_patient_complete_apmt(user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid,quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid, loginid, invpkgid,deletechargeid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement1 = null;
				preparedStatement1 = connection.prepareStatement(sql1);
				preparedStatement1.setString(1, rs.getString(1));
				preparedStatement1.setString(2, rs.getString(2));
				preparedStatement1.setString(3, DateTimeUtils.changeFormat(charge));
				preparedStatement1.setString(4, rs.getString(4));
				preparedStatement1.setString(5, rs.getString(5));
				preparedStatement1.setString(6, rs.getString(6));
				preparedStatement1.setString(7, rs.getString(7));
				preparedStatement1.setString(8, rs.getString(8));
				preparedStatement1.setString(9, rs.getString(9));
				preparedStatement1.setString(10, rs.getString(10));
				preparedStatement1.setString(11, rs.getString(11));
				//appointment
				preparedStatement1.setString(12, "0");
				preparedStatement1.setString(13, rs.getString(13));
				preparedStatement1.setString(14, rs.getString(14));
				preparedStatement1.setString(15, rs.getString(15));
				preparedStatement1.setString(16, rs.getString(16));
				preparedStatement1.setString(17, rs.getString(17));
				preparedStatement1.setString(18, rs.getString(18));
				preparedStatement1.setString(19, ""+id2);
				preparedStatement1.setString(20, rs.getString(20));
				preparedStatement1.setString(21, rs.getString(21));
				res = preparedStatement1.executeUpdate();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	public ArrayList<String> getRefundRequestId(String refundrequestid) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			String sql ="select deletechareid from refund_request_child where parentid='"+refundrequestid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if(rs.getString(1)!=null){
					if(!rs.getString(1).equals("0")){
						arrayList.add(rs.getString(1));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updateRefundDeleteInvoice(String string, String datetime, String userId) {
		int result = 0;
		try {
			String sql = "update invoice_delete_charges set given_userid='"+userId+"',given_date='"+datetime+"',isdelivered=1 where id="+string+"";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateDebitRecordRefundId(int crinvoiceid,String refundrequestid) {
		int result = 0;
		try {
			String sql = "update apm_credit_account set refundrequestid='"+refundrequestid+"' where id="+crinvoiceid+"";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateRefundRequestDeleteInvoice(String string) {
		int result = 0;
		try {
			String sql = "update invoice_delete_charges set status=1 where id="+string+"";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int saveRequestRefundFromDiscount(String invoiceId, Accounts accounts2, String datetime2, String date,
			String refund_reason, String refundamount, String userid, String discaproveuserid) {
		int result = 0;
		try{
			String sql = "insert into refund_request_parent(clientid, clientname, type, date, location, creditDebitCharge, manualinvoiceid, refundnote, requested_date, requested_userid,isfromadvance,isfromdisc,status,approve_userid,approve_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+accounts2.getClientid());
			preparedStatement.setString(2, accounts2.getClientName());
			preparedStatement.setString(3, "");
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, accounts2.getLocation());
			preparedStatement.setString(6, "1");
			preparedStatement.setString(7, invoiceId);
			preparedStatement.setString(8, refund_reason);
			preparedStatement.setString(9, datetime2);
			preparedStatement.setString(10, userid);
			if(invoiceId!=null){
				if(invoiceId.equals("")){
					preparedStatement.setString(11, "0");
				}else{
					preparedStatement.setString(11, "1");
				}
			}else{
				preparedStatement.setString(11, "0");
			}
			preparedStatement.setString(12, "1");
			preparedStatement.setString(13, "1");
			preparedStatement.setString(14, discaproveuserid);
			preparedStatement.setString(15, datetime2);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);  
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int saveRequestRefundFromDiscuntChild(String invoiceId, Accounts accounts2, String datetime2, String date,
			String userid, int parentid, int id, String refundamount) {
		PreparedStatement preparedStatement = null;
		int res =0;
		try{
			String sql1 = "insert into refund_request_child(user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid,quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid, loginid, invpkgid,parentid,deletechareid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement1 = null;
			preparedStatement1 = connection.prepareStatement(sql1);
			//user, apmtType, charge, startTime, duration, practitionerId
			preparedStatement1.setString(1, accounts2.getClientName());
			preparedStatement1.setString(2, "Discount");
			preparedStatement1.setString(3, refundamount);
			preparedStatement1.setString(4, "");
			preparedStatement1.setString(5,"");
			preparedStatement1.setString(6, "");
			
			//practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid
			preparedStatement1.setString(7, "");
			preparedStatement1.setString(8, ""+accounts2.getClientid());
			preparedStatement1.setString(9, date);
			preparedStatement1.setString(10, "0");
			preparedStatement1.setString(11, "1");
			
			//quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid,
			preparedStatement1.setString(12, "0");
			preparedStatement1.setString(13, "1");
			preparedStatement1.setString(14, accounts2.getIpdid());
			preparedStatement1.setString(15, "Additional Charge");
			preparedStatement1.setString(16, "0");
			preparedStatement1.setString(17, "0");
			
			//loginid, invpkgid,parentid,deletechareid
			preparedStatement1.setString(18, "0");
			preparedStatement1.setString(19, ""+id);
			preparedStatement1.setString(20, "0");
			preparedStatement1.setString(21, ""+parentid);
			preparedStatement1.setString(22, "0");
			res = preparedStatement1.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public double getDiscRefundAmount(String clientId) {
		double tem =0;
		try {
			StringBuffer buffer = new StringBuffer();
			/*buffer.append("select debit from refund_request_parent ");
			buffer.append("inner join apm_credit_account on refund_request_parent.id =  apm_credit_account.refundrequestid ");
			buffer.append("where isfromdisc=1 and clientid='"+clientId+"' ");*/
			buffer.append("select debit from refund_request_parent ");
			buffer.append("inner join apm_credit_account on refund_request_parent.id =  apm_credit_account.refundrequestid ");
			buffer.append("where isfromdisc=1 and apm_credit_account.client_id='"+clientId+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				tem = tem + rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tem;
	}

	public int checkrefundStatus(String id, String clientid) {
		int res =0;
		try {
			String sql ="select id from apm_credit_account where refundrequestid='"+id+"' and client_id='"+clientid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int checkNewRefund(boolean accessofapprove, int id, String currentdate,String userid) {
		int res =1;
		try {
			String date = currentdate +" "+"59:59:59";
			/*String sql ="select refund_request_parent.status from refund_request_parent order by id desc limit 1"; */
			StringBuffer buffer = new StringBuffer();
			buffer.append("select refund_request_parent.status from refund_request_parent ");
			buffer.append("left join apm_charges_invoice on  apm_charges_invoice.id = refund_request_parent.manualinvoiceid ");
			buffer.append("where refund_request_parent.date between '"+currentdate+"' and '"+date+"' ");
			if(!accessofapprove){
				buffer.append("and (apm_charges_invoice.practid='"+id+"'  or requested_userid='"+userid+"') ");
			}
			buffer.append("order by refund_request_parent.id desc limit 1 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int checkNewDiscount(boolean accessofapprove, int id, String currentdate,String userid) {
		int res =2;
		try {
			String date = currentdate +" "+"59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select disc_request from discount_request ");
			buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id = discount_request.invoiceid ");
			buffer.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientId ");
			buffer.append("where discount_request.requested_date between '"+currentdate+"' and '"+date+"' ");
			if(!accessofapprove){
				buffer.append("and (druserid='"+id+"' or requested_userid='"+userid+"') ");
			}
			
			buffer.append("order by discount_request.id desc limit 1 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getMaxAdvno(int crinvoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select max(sno) from his_invoice_payment_sno ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
				result++;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
		
	}

	public int updateAdvMaxno(int crinvoiceid, int maxno,String invtype,int paymentid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql="";
		boolean checkceinvoieid=checkcrinvoiceidexist(crinvoiceid);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		//String sql = "update apm_credit_account set advno="+maxno+", cyear="+year+" where id = "+crinvoiceid+" ";
		/*
		 * if(!checkceinvoieid) {
		 * commented because manoj sir told 
		 * sql =
		 * "insert into his_invoice_payment_sno(invoiceid, paymentid, invtype, sno, cyear) values(?,?,?,?,?) "
		 * ;
		 * 
		 * }
		 */
		 sql = "insert into his_invoice_payment_sno(invoiceid, paymentid, invtype, sno, cyear) values(?,?,?,?,?) ";

		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, crinvoiceid);
			preparedStatement.setInt(2, paymentid);
			preparedStatement.setString(3, invtype);
			preparedStatement.setInt(4, maxno);
			preparedStatement.setInt(5, year);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean checkcrinvoiceidexist(int crinvoiceid) {
		boolean check=false;
		PreparedStatement ps= null;
		try {
			String sql="select * from his_invoice_payment_sno where invoiceid="+crinvoiceid+" and invtype in('ADVANCE','REFUND OPD','REFUND IPD') ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				check = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	//saving practitioner in credit acc  by lokesh
	public int setPractionerinCreditacc(int crinvoiceid,String practid){
		int result=0;
		PreparedStatement ps= null;
		try {
			String sql="update apm_credit_account set practitioner_id='"+practid+"' where id= "+crinvoiceid+" ";
			ps=connection.prepareStatement(sql);
			result= ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}

	public int updateAdvRefPaymodeCreditAcc(String recieptid,String paymode) {
		PreparedStatement ps= null;
		int res=0;
		try {
			String sql="update apm_credit_account set payment_mode='"+paymode+"' where id ='"+recieptid+"'"; 
			ps= connection.prepareStatement(sql);
			res=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateAdvrefLedger(String recieptid,String paymode) {
		if(paymode.equals("Cash")){
			paymode="2";
		}else if(paymode.equals("D/Card")){
			paymode="3";
		}else if(paymode.equals("Cheque")){
			paymode="4";
		}else if(paymode.equals("NEFT")){
			paymode="5";
		}
		PreparedStatement ps= null;
		int res=0;
		try {
			String sql="update ledger_sheet  set ledgerid='"+paymode+"' where invid ='"+recieptid+"'"; 
			ps= connection.prepareStatement(sql);
			res=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private int getipdIdFromInv(String invid){
		int res=0;
		String sql=" select ipdid from apm_charges_invoice where id='"+invid+"' ";
		PreparedStatement ps= null;
		try{
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<CompleteAppointment> getRefundReportList(String fromdate, String todate,String ipdopdwise,LoginInfo loginInfo,String invoiceid) {
		ArrayList<CompleteAppointment> arrayList = new ArrayList<CompleteAppointment>();
		SummaryReportDAO reportDAO=new JDBCSummaryReportDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		try {
			StringBuilder builder = new StringBuilder();
			todate = todate +" "+"59:59:59";
		if(loginInfo.isLmh()) {
			
			builder.append("select refund_request_parent.id, refund_request_parent.clientid, clientname, refund_request_parent.manualinvoiceid,refund_request_parent.refundnote, requested_date, requested_userid, approve_date, approve_userid,status, isfromadvance,approvednote,chargeinvoiceid ");
			builder.append("from refund_request_parent ");	
			builder.append("inner join apm_charges_payment on apm_charges_payment.clientid=refund_request_parent.clientid ");
			builder.append("where requested_date between '"+fromdate+"' and '"+todate+"' and cancelinv=1 ");
			if(!invoiceid.equals("")) {
				builder.append("and chargeinvoiceid='"+invoiceid+"'");
			}
		}else {
			builder.append("select refund_request_parent.id, refund_request_parent.clientid, clientname, refund_request_parent.manualinvoiceid,refund_request_parent.refundnote, requested_date, requested_userid, approve_date, approve_userid,status, isfromadvance,approvednote,itype ");
			builder.append("from refund_request_parent ");
			builder.append("left join apm_charges_invoice on apm_charges_invoice.id=refund_request_parent.manualinvoiceid ");
			builder.append("where refund_request_parent.isdeleted=0 and requested_date between '"+fromdate+"' and '"+todate+"' and status=2 ");
			if(ipdopdwise.equals("1")){
					builder.append("and itype=1" );
				
			}else if (ipdopdwise.equals("2")) {
				builder.append("and (itype='2' or itype is null or refund_request_parent.manualinvoiceid ='') " );
			}
			if(ipdopdwise.equals("3")){
				builder.append("and itype=3" );
			
			}
			}
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				CompleteAppointment completeAppointment = new CompleteAppointment();
				completeAppointment.setId(rs.getInt(1));
				completeAppointment.setClientId(rs.getString(2));
				completeAppointment.setClient(rs.getString(3));
			
				completeAppointment.setRefundnote(rs.getString(5));
				String reqdate = "";
				if(rs.getString(6)!=null){
					String[] data = rs.getString(6).split(" ");
					reqdate = DateTimeUtils.getCommencingDate1(data[0]) +" "+data[1];
				}
				completeAppointment.setRequested_date(reqdate);
				completeAppointment.setRequested_userid(rs.getString(7));
				completeAppointment.setApprove_date(rs.getString(8));
				completeAppointment.setApprove_userid(rs.getString(9));
				completeAppointment.setStatus(rs.getString(10));
				completeAppointment.setIsfromadvance(rs.getString(11));
				String total = getTotalRefundCharge(rs.getInt(1));
				completeAppointment.setCharges(total);
				completeAppointment.setApprove_note(rs.getString(12));
				if(loginInfo.isLmh()) {
					completeAppointment.setManualinvoiceid(rs.getString(13));
				}else {
				completeAppointment.setManualinvoiceid(rs.getString(4));
				completeAppointment.setInvoicetype(reportDAO.getInvoiceTypeByitype(rs.getInt(13)));
				}
				int creditid = getCreditIdFromRefundId(rs.getInt(1));
				String physicalid=accountsDAO.getPhysicalpaymentIdAdvRef(""+creditid);
				completeAppointment.setCreditid(creditid);
				completeAppointment.setPhysicalid(physicalid);
				
				//String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(4));
				//completeAppointment.setIpdopdseq(ipdopdseq);
				
				String ipdopdseq=accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(4));
				completeAppointment.setIpdopdseq(ipdopdseq);
				
				arrayList.add(completeAppointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private int getCreditIdFromRefundId(int int1) {
		int res=0;
		try {
			String sql ="select id from apm_credit_account where refundrequestid="+int1+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs= preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private int saveToPhysicalHISPayment(String invoiceid,String advrefid,String clientid,String commencing,String paymode, String amount,String itype,String type){
		int res=0;
		try {
			int itype1=Integer.parseInt(itype);
			
			String sql="insert into his_payment_record_physical(invoiceid,adv_ref_id,clientid,paymode,amount,itype,commencing,adv_or_refund) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setString(1, invoiceid);
			ps.setString(2, advrefid);
			ps.setString(3, clientid);
			ps.setString(4, paymode);
			ps.setString(5, amount);
			ps.setInt(6, itype1);
			ps.setString(7, commencing);
			ps.setString(8, type);
			res= ps.executeUpdate();
			
			int his_payId=0;
			if(res>0){
				ResultSet resultSet=ps.getGeneratedKeys();
				if(resultSet.next()){
					his_payId=resultSet.getInt(1);
				}
				
				AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
				accountsDAO.genearteSeqOfPaymentByFinancialYear(his_payId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private int getItype(String invoiceid){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement("select itype from apm_charges_invoice where id='"+invoiceid+"'");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checkRefundRequest(String id) {
		boolean flag = false;
		try {
			String sql = "SELECT * FROM invoice_delete_charges  where actualinvoiceid="+id+" and status=0 and ispaydonestatus=1 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String getDeletedChargeIDforRefund(String id) {
		String ids ="0";
		String sql = "SELECT id FROM invoice_delete_charges  where actualinvoiceid="+id+" and status=0 and ispaydonestatus=1 ";
		try{
			PreparedStatement preparedStatement =null;
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				ids = ids +","+rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ids;

	}

	public int saveRefundTempFromDeleteInvoiceNew(String id, int id2, String deletechargeid, double newbalane) {
		int res =0;
		String sql = "SELECT user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid,quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid, loginid, invpkgid,id,discamount,discperc FROM invoice_delete_charges  where actualinvoiceid="+id+" and status=0 and ispaydonestatus=1 ";
		try{
			PreparedStatement preparedStatement =null;
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				double charge = newbalane;
				/*double disc = rs.getDouble(22);
				charge = charge- disc;*/
				String sql1 = "insert into apm_patient_complete_apmt(user, apmtType, charge, startTime, duration, practitionerId, practitionerName, clientId, commencing,  paybuy, markAppointment,appointmentid,quantity,ipdid,masterchargetype, prodid, additioncharge_id,  ginvstid, loginid, invpkgid,deletechargeid,newdeletechargeids) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement1 = null;
				preparedStatement1 = connection.prepareStatement(sql1);
				preparedStatement1.setString(1, rs.getString(1));
				preparedStatement1.setString(2, "Refund");
				preparedStatement1.setString(3, DateTimeUtils.changeFormat(charge));
				preparedStatement1.setString(4, rs.getString(4));
				preparedStatement1.setString(5, rs.getString(5));
				preparedStatement1.setString(6, rs.getString(6));
				preparedStatement1.setString(7, rs.getString(7));
				preparedStatement1.setString(8, rs.getString(8));
				preparedStatement1.setString(9, rs.getString(9));
				preparedStatement1.setString(10, rs.getString(10));
				preparedStatement1.setString(11, rs.getString(11));
				//appointment
				preparedStatement1.setString(12, "0");
				preparedStatement1.setString(13, "1");
				preparedStatement1.setString(14, rs.getString(14));
				preparedStatement1.setString(15, "Additional Charge");
				preparedStatement1.setString(16, rs.getString(16));
				preparedStatement1.setString(17, rs.getString(17));
				preparedStatement1.setString(18, rs.getString(18));
				preparedStatement1.setString(19, ""+id2);
				preparedStatement1.setString(20, rs.getString(20));
				preparedStatement1.setString(21, rs.getString(21));
				preparedStatement1.setString(22, deletechargeid);
				res = preparedStatement1.executeUpdate();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	public String getRequestedRefundDeleteIDs(String clientId, int id) {
		String ids ="0";
		try {
			String sql = "SELECT newdeletechargeids FROM apm_patient_complete_apmt where clientId = "+clientId+" and loginid="+id+" ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				ids = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public int updateRefundParentIds(String ids, int parentid) {
		int res =0;
		try {
			String sql ="update refund_request_parent set deleteparentid='"+ids+"' where id='"+parentid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checkPendingRefund(String invoiceid) {
		boolean amt =false;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(charge*quantity) from refund_request_parent ");
			buffer.append("inner join refund_request_child on refund_request_child.parentid =refund_request_parent.id ");
			buffer.append("where manualinvoiceid="+invoiceid+" and isfromdisc=0 and status in (0,1) and refund_request_parent.isdeleted=0 ");
			buffer.append("group by manualinvoiceid ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				amt = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}

	public int getRefundApproveAppliedCount(String fromdate, String todate, String string, String searchtext,
			boolean accessofapprove, int id, String filter_status, String userid, String countdata) {
		int res =0;
		try {
			StringBuilder builder = new StringBuilder();
			todate = todate +" "+"59:59:59";
			builder.append("select count(*) from refund_request_parent ");
			builder.append("left join apm_charges_invoice on  apm_charges_invoice.id = refund_request_parent.manualinvoiceid ");
			if(countdata.equals("")){
				builder.append("where refund_request_parent.isdeleted=0 and requested_date between '"+fromdate+"' and '"+todate+"' ");
				if(searchtext!=null){
					builder.append("and (clientname like '%"+searchtext+"%' or refund_request_parent.clientid='"+searchtext+"' or refund_request_parent.id='"+searchtext+"') ");
				}
				if(!filter_status.equals("")){
					builder.append("and refund_request_parent.status='"+filter_status+"' ");
				}
				if(!accessofapprove){
					builder.append("and (apm_charges_invoice.practid='"+id+"' or requested_userid='"+userid+"') ");
				}
				
			}else{
				if(countdata.equals("2")) {
					builder.append("where refund_request_parent.isdeleted=1 and requested_date between '"+fromdate+"' and '"+todate+"'  ");
				}else {
				    builder.append("where refund_request_parent.isdeleted=0 and refund_request_parent.status='"+countdata+"' ");
				}
				    if(!accessofapprove){
					builder.append("and (apm_charges_invoice.practid='"+id+"' or requested_userid='"+userid+"') ");
				}
			}
		
			
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int createSeqnogenProccessForAdvAndRef(String itype,String creditaccId, String paymode,String refinvoiceid) {
		int result=0;
		//itype 8 and 9 is refopd and refipd for balgopal
		paymode=DateTimeUtils.isNull(paymode);
		refinvoiceid=DateTimeUtils.isNull(refinvoiceid);
		if(!refinvoiceid.equals("")){
			itype="9";
			paymode="";
		}
		try {
			if(!paymode.equals("prepayment")){
				int success=updateInvTypeAdvRef(itype, creditaccId);
				if(success>0){
					int maxNo=getMaxAdvRefNo(itype);
					maxNo=maxNo+1;
					updateAdvRefSeqnoNew(itype, creditaccId, maxNo);
					advRefFinacialYearGen(itype, creditaccId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private int updateInvTypeAdvRef(String itype,String creditaccId){
		int i=0;
		try {
			
			PreparedStatement ps= connection.prepareStatement(" update apm_credit_account set invtypenew='"+Integer.parseInt(itype)+"' where id='"+creditaccId+"'");
			i=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int getMaxAdvRefNo( String invcetype) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String cname = "";
		if(invcetype.equals("5")){
			cname = "newadvseq";
		}
		if(invcetype.equals("9")){
			cname = "ipdrefseq";
		}
		if(invcetype.equals("8")){
			cname = "opdrefseq";
		}
		String sql = "select max("+cname+") from apm_credit_account ";
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
	private int updateAdvRefSeqnoNew(String itype,String creditaccId,int maxno ){
		int res=0;
		
		String cname = "";
		if(itype.equals("5")){
			cname = "newadvseq";
		}
		if(itype.equals("9")){
			cname = "ipdrefseq";
		}
		if(itype.equals("8")){
			cname = "opdrefseq";
		}
		
		try {
			PreparedStatement ps=connection.prepareStatement("  update apm_credit_account set "+cname+"='"+maxno+"' where id='"+creditaccId+"' ");
			res =ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void advRefFinacialYearGen(String type,String id){
		try {
			String year=DateTimeUtils.getFinancialYearRange();
			int maxNo=getMaxSeqNoOfFinanceYear(year, type);
			
			type=DateTimeUtils.isNull(type);
			String prefix="";
			if(type.equals("5")){
				prefix="ADV";
			}else if(type.equals("8")){
				prefix="RFO";
			}else if(type.equals("9")){
				prefix="RFI";
			}
			
			
			AccountsDAO accountsDAO= new JDBCAccountsDAO(connection);
			//prefix=accountsDAO.getInvoicePrefix(type);
			
			
			int appndNo=maxNo+1;
			//prefix=year+"/"+prefix+"/"+(appndNo);
			String temp = String.valueOf(appndNo);
			int l = temp.length();
			String abrstr = "";
			if (l == 3) {
				abrstr = "0" + appndNo;
			} else if (l == 2) {
				abrstr = "00" + appndNo;
			} else if (l == 1) {
				abrstr = "000" + appndNo;
			} else {
				abrstr = "" + appndNo;
			}
			
			String[] data = year.split("-");
			String[] data1 = data[0].split("");
			String[] data2 = data[1].split("");
			String value = data1[2]+""+data1[3]+"-"+data2[2]+""+data2[3];
			//new seq arrangement by akash
			prefix=prefix+""+value+"/"+abrstr;
			int chargeAmt=getchargeOfAdvRef(id);
			if(chargeAmt>0 || type.equals("8") || type.equals("9")) {
				int saved=saveFinSeqToCreditAcc(id, prefix);
				if(saved>0){
					updateCountOfFinancYearSeq(year, appndNo, type);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private int getchargeOfAdvRef(String id) {
	
		int res=0;
		try {
			PreparedStatement ps=connection.prepareStatement(" select charge from apm_credit_account where id ='"+id+"'");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
			}catch (Exception e) {
			e.printStackTrace();	}
		return res;
	}
	
	private int getMaxSeqNoOfFinanceYear(String year ,String type){
		int maxNo=0;
		try {
			String sql=" select max(count) from finacial_year_seq_advref where type='"+type+"' and date='"+year+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				maxNo=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxNo;
	}
	
	private int saveFinSeqToCreditAcc(String invId, String seq){
		int suuccess=0;	
		try {
			String sql=" update apm_credit_account set fin_yr_seq=? where id="+invId+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, seq);
			suuccess=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return suuccess;
	}
	
	private int updateCountOfFinancYearSeq(String year , int count, String type){
		int success=0;
		try {
			String sql=" insert into finacial_year_seq_advref(date,count,type) values(?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, year);
			ps.setInt(2, count);
			ps.setString(3, type);
			success=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return success;
	}
	
	public String getBalgopalFinacialSeqNum (String creditaccId){
		String seqNo="";
		try {
			PreparedStatement ps= connection.prepareStatement("  select fin_yr_seq from apm_credit_account where id='"+creditaccId+"'");
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				seqNo=DateTimeUtils.isNull(rs.getString(1));
			}
			if(seqNo.equals("")){
				seqNo=""+getBalgopalSeqNum(creditaccId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqNo;
	}
	public int getBalgopalSeqNum(String creditaccId) {
		int id=0;
		try {
			PreparedStatement ps= connection.prepareStatement("  select invtypenew, newadvseq,ipdrefseq,opdrefseq from apm_credit_account where id='"+creditaccId+"'");
			ResultSet rs= ps.executeQuery();
			if(rs.next()){
				int type=rs.getInt(1);
				if(type==5){
					id=rs.getInt(2);
				}else if(type==9){
					id=rs.getInt(3);
				}else if(type==8){
					id=rs.getInt(4);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public ArrayList<CompleteAppointment> getAutoRefundList(String fromdate, String todate, String ipdopdwise) {
		ArrayList<CompleteAppointment> arrayList = new ArrayList<CompleteAppointment>();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		try {
			todate = todate+" "+"23:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select date,userid,debit,fullname,apm_credit_account.id from apm_credit_account ");
			buffer.append("inner join apm_patient on  apm_patient.id =apm_credit_account.client_id ");
			buffer.append("where advref=1 and refinvoiceid!=0 and apm_credit_account.date between '"+fromdate+"' and '"+todate+"' and debit>0 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs =preparedStatement.executeQuery();
			while (rs.next()) {
				CompleteAppointment completeAppointment = new CompleteAppointment();
				String date[] = rs.getString(1).split(" ");
				completeAppointment.setDate(DateTimeUtils.getCommencingDate1(date[0])+" "+date[1]);
				completeAppointment.setUser(rs.getString(2));
				completeAppointment.setDebit(rs.getDouble(3));
				completeAppointment.setClient(rs.getString(4));
				int creditid = rs.getInt(5);
				String physicalid=accountsDAO.getPhysicalpaymentIdAdvRef(""+rs.getInt(5));
				completeAppointment.setCreditid(creditid);
				completeAppointment.setPhysicalid(physicalid);
				arrayList.add(completeAppointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Accounts> getAutoRefundListNew(String fromdate, String todate, String ipdopdwise) {
		ArrayList<Accounts> arrayList = new ArrayList<Accounts>();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		try {
			todate = todate+" "+"23:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select date,userid,debit,fullname,apm_credit_account.id from apm_credit_account ");
			buffer.append("inner join apm_patient on  apm_patient.id =apm_credit_account.client_id ");
			buffer.append("where advref=1 and refinvoiceid!=0 and apm_credit_account.date between '"+fromdate+"' and '"+todate+"' ");
			//buffer.append(" and invtypenew in("+ipdopdwise+") ");
			if(ipdopdwise.equals("9")){
				buffer.append("and  advance_ipdid!='0'  and debit!='0' ");
			}else{
				buffer.append("and  advance_ipdid='0'  and debit!='0' ");
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs =preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts completeAppointment = new Accounts();
				String date[] = rs.getString(1).split(" ");
				completeAppointment.setDate(DateTimeUtils.getCommencingDate1(date[0])+" "+date[1]);
				completeAppointment.setUserid(rs.getString(2));
				completeAppointment.setDebitAmount(rs.getDouble(3));
				completeAppointment.setClientName(rs.getString(4));
				int creditid = rs.getInt(5);
				String physicalid=accountsDAO.getPhysicalpaymentIdAdvRef(""+rs.getInt(5));
				completeAppointment.setCreditid(creditid);
				completeAppointment.setPhysicalid(physicalid);
				arrayList.add(completeAppointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public double getAdvanceBalanceRemain(String clientid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT balance FROM apm_credit_account where client_id = "+clientid+" and resetinv!='0' order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getpaymentUserId(int invoiceid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT userid FROM apm_charges_payment where chargeinvoiceid = "+invoiceid+" order by id desc limit 0,1 ";
		
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

	@Override
	public String getDeletedChargeIDRefundTable(String parentid) {
		String ids="0";
		try {
			String sql="select deleteparentid from refund_request_parent where id='"+parentid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ids = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	@Override
	public int updateDeletedChargeStatus(String deleteparentid) {
		int res=0;
		try {
			String sql="update invoice_delete_charges set status=0 where id in ("+deleteparentid+")";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateAdvrefcredit(String id, String credit,double balance) {
		PreparedStatement ps= null;
		int res=0;
		try {
			String sql="update apm_credit_account set charge='"+credit+"', balance="+balance+" where id ='"+id+"'"; 
			ps= connection.prepareStatement(sql);
			res=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<Accounts> getCreditlistbyclientid(String clientId,String id) {
		ArrayList<Accounts> list = new ArrayList<Accounts>();
		PreparedStatement pst=null;
		try {
			 String sql="select charge,balance,id from apm_credit_account where client_id='"+clientId+"' and balance!=0 and id>='"+id+"'";
			 pst=connection.prepareStatement(sql);
			 ResultSet rs=pst.executeQuery();
			 while(rs.next()) {
				 Accounts accounts = new Accounts();
				 accounts.setCharges(rs.getString(1));
				 accounts.setBalance(rs.getDouble(2));
				 accounts.setId(rs.getInt(3));
				 list.add(accounts);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public double getPreviousbalance(String id) {
		PreparedStatement pst= null;
		double res=0;
		try {
			String sql=" select balance from apm_credit_account where id="+id+"";
			pst=connection.prepareStatement(sql);
			 ResultSet rs=pst.executeQuery();
			 while(rs.next()) {
				 res=rs.getDouble(1);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateAssessmentcharges(String id,String credit) {
		PreparedStatement ps= null;
		int res=0;
		try {
			String sql="update apm_credit_account_assesments set charge='"+credit+"' where invoiceid ='"+id+"'"; 
			ps= connection.prepareStatement(sql);
			res=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<Accounts> getLedgerrecord(String id,String clientId) {
		ArrayList<Accounts> list = new ArrayList<Accounts>();
		PreparedStatement pst=null;
		try {
			 String sql="select debit,credit,balance,id from ledger_sheet where clientid='"+clientId+"' and invid='"+id+"' ";
			 pst=connection.prepareStatement(sql);
			 ResultSet rs=pst.executeQuery();
			 while(rs.next()) {
				 
				 Accounts accounts = new Accounts();
				 accounts.setDebitAmount(rs.getDouble(1));
				 accounts.setCreditAmount(rs.getDouble(2));
				 accounts.setBalance(rs.getDouble(3));
				 accounts.setId(rs.getInt(4));
				 list.add(accounts);
					
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateLedgercreditdebit(String debit, String cred, String bal, int id, String id2) {
		PreparedStatement ps= null;
		int res=0;
		try {
			String sql="update ledger_sheet set debit='"+debit+"',credit='"+cred+"', balance='"+bal+"' where id ="+id+" and invid='"+id2+"'"; 
			ps= connection.prepareStatement(sql);
			res=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int getprepaymentid(String invoiceid) {
		PreparedStatement pst= null;
		int res=0;
		try {
			String sql="select prepaymentid from apm_charges_payment where paymode='prepayment' and chargeinvoiceid='"+invoiceid+"'";
			pst=connection.prepareStatement(sql);
			 ResultSet rs=pst.executeQuery();
			 while(rs.next()) {
				 res=rs.getInt(1);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateclientAdvance(int prepaymentid1, String invoiceid) {
		PreparedStatement ps= null;
		int res=0;
		try {
			String sql="update apm_credit_account set refinvoiceid='"+invoiceid+"' where id="+prepaymentid1+""; 
			ps= connection.prepareStatement(sql);
			res=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean PaymentidPresentOrNot(int paymentid) {
		PreparedStatement ps= null;
		boolean flag = false;
		try {
			String sql="select id from his_invoice_payment_sno where invoiceid="+paymentid+" and invtype in('OPD','IPD','INVESTIGATION','PHYSIO IPD') "; 
			ps= connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				 flag=true;
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}

	@Override
	public boolean InviceidPresentOrNot(int crinvoiceid) {
		PreparedStatement ps= null;
		boolean flag = false;
		try {
			String sql="select id from his_invoice_payment_sno where invoiceid="+crinvoiceid+" and invtype!='Refund' or invtype!='Advance' "; 
			ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				 flag=true;
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	@Override
	public ArrayList<AppointmentType> getPhysioIpdAdditionalChargesList(String deptname) {
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentType>list = new ArrayList<AppointmentType>();
		String sql  = "SELECT id,name,description,category,duration,charges,location,chargeType,reportField FROM apm_appointment_type where chargeType='"+deptname+"' and tpid=0";
		
	
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				AppointmentType appointmentType = new AppointmentType();
				
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				appointmentType.setDescription(rs.getString(3));
				appointmentType.setCategory(rs.getString(4));
				appointmentType.setDuration(rs.getString(5));				
				appointmentType.setCharges(rs.getString(6));
				appointmentType.setLocation(rs.getString(7));
				appointmentType.setChargeType(rs.getString(8));
				appointmentType.setReportField(rs.getString(9));
				
				list.add(appointmentType);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public String getClientfullname(String clientId) {
		PreparedStatement ps= null;
		String res="";
		try {
			String sql="select concat(firstname,' ',surname) from apm_patient where id="+clientId+""; 
			ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				res=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateRefundChild(int result,double newcharge) {
		PreparedStatement ps= null;
		int res=0;
		try {
			String sql="update refund_request_child set charge='"+newcharge+"' where id="+result+""; 
			ps= connection.prepareStatement(sql);
			res=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateChargeinassmnt(String debit, String invoiceid) {
		PreparedStatement ps= null;
		int res=0;
		try {
			String sql="update apm_invoice_assesments set charge='"+debit+"' where invoiceid="+invoiceid+""; 
			ps= connection.prepareStatement(sql);
			res=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}