package com.apm.Appointment.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Location;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;


public class JDBCAppointmentDAO extends JDBCBaseDAO implements AppointmentDAO{
	
	public JDBCAppointmentDAO(Connection connection){
		this.connection = connection;
		
		
	}

	
	public int saveAppointment(Appointment appointment) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into appointment(practitionerid,appt_type,patient_type,title,firstname,lastname,phone,phone_type,dob,email,remainder_type,whendate,fromtime,totime,repeat_type,repeveryweek,appttocreate,notes) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, appointment.getPractitioner());
			preparedStatement.setString(2, appointment.getAppointmentType());
			preparedStatement.setString(3, appointment.getPatientType());
			preparedStatement.setString(4, appointment.getTitle());
			preparedStatement.setString(5, appointment.getFirstName());
			preparedStatement.setString(6, appointment.getLastName());
			preparedStatement.setString(7, appointment.getContactNo());
			preparedStatement.setString(8, appointment.getComDevice());
			preparedStatement.setString(9, appointment.getDob());
			preparedStatement.setString(10, appointment.getEmail());
			preparedStatement.setString(11, appointment.getAutoremaindertype());;
			preparedStatement.setString(12, appointment.getWhen());
			preparedStatement.setString(13, appointment.getHour() + ":" + appointment.getMinute());
			preparedStatement.setString(14, appointment.getTohour() + ":" + appointment.getTominute());
			preparedStatement.setString(15, appointment.getRepeat());
			preparedStatement.setString(16, appointment.getRevery());
			preparedStatement.setString(17, appointment.getHappointment());
			preparedStatement.setString(18, appointment.getNotes());
			
			result = preparedStatement.executeUpdate();
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}


	public ArrayList<Appointment> getPractitionerList(int clinicId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Appointment>list = new ArrayList<Appointment>();
		String sql = "SELECT id,firstname,lastname FROM apm_user where clinicid= "+clinicId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Appointment appointment = new Appointment();
				appointment.setPractitionerID(rs.getInt(1));
				appointment.setPractitionerName(rs.getString(2) + " " + rs.getString(2));
				
				list.add(appointment);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Location> getLocationList(int practitionerid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Location>list = new ArrayList<Location>();
		String sql = "SELECT location FROM apm_apmt_slot where diaryuserid="+practitionerid+" group by location ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Location location = new Location();
				location.setLocation(rs.getString(1));
				
				list.add(location);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<AppointmentType> getAppointmentTypeList(int practitionerid) {
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentType>list = new ArrayList<AppointmentType>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_appointment_type.id,name,duration FROM apm_appointment_type_user ");
		sql.append("inner join apm_appointment_type on apm_appointment_type_user.appintmenttypeid = apm_appointment_type.id ");
		sql.append("and apm_appointment_type_user.practitionerid = "+practitionerid+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AppointmentType appointmentType = new AppointmentType();
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				appointmentType.setDuration(rs.getString(3));
				
				list.add(appointmentType);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	public ArrayList<Appointment> getApptAvailiability(int practitionerid, String date) {
		PreparedStatement preparedStatement = null;
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		String sql = "SELECT starttime,endtime,location FROM apm_apmt_slot where diaryuserid=? and commencing = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, practitionerid);
			preparedStatement.setString(2,date);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Appointment appointment = new Appointment();
				appointment.setStarttime(rs.getString(1));
				appointment.setEdndtime(rs.getString(2));
				appointment.setLocation(rs.getString(3));
				
				list.add(appointment);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public String getStartTime(int practitionerid, String location, String date) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT starttime FROM apm_apmt_slot where diaryuserid=? and location=? and commencing=? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, practitionerid);
			preparedStatement.setString(2, location);
			preparedStatement.setString(3, date);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public ArrayList<AppointmentType> getAdditionalChaergeTypeList(String who) {
		PreparedStatement preparedStatement = null;
		 ArrayList<AppointmentType>list = new ArrayList<AppointmentType>();
		 String sql = "SELECT id,name FROM apm_appointment_type order by name ";
		
		
		 
		
		try{
			/*preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();*/
			/*while(rs.next()){
				AppointmentType appointmentType = new AppointmentType();
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				
				list.add(appointmentType);
				
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public String getSelectedCharge(String selectedAppointmentID,String masterchargetype) {
		PreparedStatement preparedStatement = null;
		String result = "";
		
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		boolean checkInventoryChargeType = ipdDAO.checkInventoryChargeType(masterchargetype);
		String sql = "";
		
		if(checkInventoryChargeType){
			 sql = "SELECT charges FROM apm_appointment_type where id = "+selectedAppointmentID+" ";
		}else{
			 sql = "SELECT saleprice FROM inventory_product " +
			 		"inner join inventory_category on inventory_category.id = inventory_product.categoryid " +
			 		"where inventory_category.name = '"+masterchargetype+"'  and inventory_product.id = "+selectedAppointmentID+" ";
		}
		
		
		
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


	public ArrayList<Master> getmasterChageTypeList(LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		ArrayList<Master>inventoryMasterChargeList = new ArrayList<Master>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT id,name FROM apm_newchargetype ");
		if(loginInfo.isShowinvestigation()){
			buffer.append("where name !='INVESTIGATION'  and procedures='0' and isshowflag='0' ");
			/*buffer.append("where name !='INVESTIGATION'  ");*/
		}else{
			buffer.append("where procedures='0' and isshowflag='0' ");
			
		}
		buffer.append("order by name ");
		//String sql = "SELECT id,name FROM apm_newchargetype where name !='INVESTIGATION'  order by name ";
		
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
			/*inventoryMasterChargeList = getInventoryMasterChargeList();
			list.addAll(inventoryMasterChargeList);*/
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	private ArrayList<Master> getInventoryMasterChargeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM inventory_category order by name ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
			
	}


	public int updateApproveInvoiceDiscout(String invoiceid, String userid, String datetime,String approve_notes) {
		  int res =0;
		  try {
		   String sql ="update apm_charges_invoice set disc_approve=1,disc_approve_userid='"+userid+"',disc_approve_date='"+datetime+"',disc_request=2,approve_notes='"+approve_notes+"'  where id='"+invoiceid+"'";
		   PreparedStatement preparedStatement = connection.prepareStatement(sql);
		   res = preparedStatement.executeUpdate();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return res;
		 }


	public int updateRequestInvoiceDiscout(String invoiceid) {
		int res =0;
		try {
			String sql ="update apm_charges_invoice set disc_request=1 where id='"+invoiceid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public int saveDiscountRequest(String invoiceid, String userid, String datetime, String disctype, String discval, String invoiceamount, String discountgivenuserid, String discount_reason,int clientid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into discount_request(invoiceid,requested_userid,requested_date,disc_type,disc_amount,invoiceamount,druserid,remark,clientid) values(?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, invoiceid);
			preparedStatement.setString(2, userid);
			preparedStatement.setString(3, datetime);
			preparedStatement.setString(4, disctype);
			preparedStatement.setString(5, discval);
			preparedStatement.setString(6, invoiceamount);
			preparedStatement.setString(7, discountgivenuserid);
			preparedStatement.setString(8, discount_reason);
			preparedStatement.setInt(9, clientid);
			result = preparedStatement.executeUpdate();
			
			if(result>0){
				  ResultSet rs=preparedStatement.getGeneratedKeys();
				  while(rs.next()){
					    result=rs.getInt(1);
				  }
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<CompleteAppointment> getParentDiscountRequestList(String fromdate, String todate, boolean accessofapprove, int id, String filter_status,String userid, String countdata) {
		ArrayList<CompleteAppointment> arrayList = new ArrayList<CompleteAppointment>();
		try {
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			StringBuilder builder = new StringBuilder();
			todate = todate +" "+"59:59:59";
			builder.append("select discount_request.id,invoiceid,requested_userid,requested_date,apm_charges_invoice.clientId,apm_patient.fullname, ");
			builder.append("disc_type, disc_amount, invoiceamount,disc_request, druserid, remark,approved_userid,approve_notes,discount_request.deleted,apm_charges_invoice.itype,discount_request.clientid,discount_request.approve_note,discount_request.approved_date,apm_charges_invoice.isdeleted,abrivationid,deleteremark from discount_request ");
			builder.append("left join apm_charges_invoice on apm_charges_invoice.id = discount_request.invoiceid ");
			builder.append("left join apm_patient on apm_patient.id = apm_charges_invoice.clientId ");
			
			if(!countdata.equals("0")){
				if(countdata.equals("3")) {
					 builder.append("where discount_request.deleted='1' and requested_date between '"+fromdate+"' and '"+todate+"' ");
				}else {
				    builder.append("where disc_request='"+countdata+"'  and discount_request.deleted='0' ");
				if(!accessofapprove){
					builder.append("and (druserid='"+id+"' or requested_userid='"+userid+"') ");
				}
			}
			}else{
				builder.append("where requested_date between '"+fromdate+"' and '"+todate+"'  and discount_request.deleted='0' ");
				if(!filter_status.equals("0")){
					if(!filter_status.equals("4")){
						builder.append("and disc_request='"+filter_status+"' ");
					}else{
						builder.append("and discount_request.deleted='1' ");
					}
				}
				if(!accessofapprove){
					builder.append("and (druserid='"+id+"' or requested_userid='"+userid+"') ");
				}
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				CompleteAppointment completeAppointment = new CompleteAppointment();
				completeAppointment.setId(rs.getInt(1));
				completeAppointment.setInvoiceid(rs.getString(2));
				completeAppointment.setRequested_userid(rs.getString(3));
				String reqdate = "";
				if(rs.getString(4)!=null){
					String[] data =rs.getString(4).split(" ");
					reqdate = DateTimeUtils.getCommencingDate1(data[0]) +" "+data[1];
				}
				completeAppointment.setRequested_date(reqdate);
				if(rs.getInt(17)==0){
				completeAppointment.setClientId(rs.getString(5));
				}else{
					completeAppointment.setClientId(rs.getString(17));
				}
				String name=accountsDAO.getClientName(completeAppointment.getClientId());
				completeAppointment.setClient(name);
				completeAppointment.setDisc_type(rs.getString(7));
				completeAppointment.setDisc_amount(rs.getString(8));
				completeAppointment.setInvoiceamount(rs.getString(9));
				if(rs.getInt(2)==0){
					ArrayList<CompleteAppointment> list=getChargesid(Integer.parseInt(rs.getString(1)));
					int size=list.size();
					if(size>0){
						int ipdid=list.get(size-1).getIpdid();
						String clientid=list.get(size-1).getClientId();
					}
				}
				boolean invoiced=false;
				boolean approved=false;
				int invoiceid=0;
				if(rs.getInt(2)==0){
					ArrayList<CompleteAppointment> list=getChargesid(rs.getInt(1));
					for (CompleteAppointment completeAppointment2 : list) {
						 invoiced=checkInvoiceCreated(completeAppointment2);
						 if(invoiced==true){
							break;
							}
					}
					for (CompleteAppointment completeAppointment2 : list) {
						 approved=checkDiscountApprove(completeAppointment2);
						 if(approved==true){
							break;
							}
					}
					for (CompleteAppointment completeAppointment2 : list) {
						 invoiceid=getInvoiceId(completeAppointment2);
						 if(approved==true){
							break;
							}
					}
					if(invoiced && approved){
						completeAppointment.setStatus("2");
					}else if (invoiced) {
						completeAppointment.setStatus("1");
					}else if (approved) {
						completeAppointment.setStatus("2");
					}
					else {
						completeAppointment.setStatus("3");
					}
				}
				/*double d = (debit*discount)/100;
				if(disctype==1){
					d = discamt;
				}
				debittotal = debit - d;*/
				double d =0;
				Accounts accounts=accountsDAO.gettotoalinvdisc(completeAppointment.getId());
				if(rs.getInt(2)!=0){
					//invoice code
					if(rs.getString(7).equals("0")){
						d = (rs.getDouble(9)*rs.getDouble(8))/100;
					}else{
						d = rs.getDouble(8);
					}
				}else{
					d=accounts.getTotaldiscountammount();
				}
//				if(rs.getInt(2)==0){
//					d = rs.getDouble(8);
//				}
				double total=0;
				if(invoiced){
					double invoicetot=getSumofInvoice(invoiceid);
					total=invoicetot;
					completeAppointment.setInvoiceamount(String.valueOf(total+d));
				}
				else{
				 total = rs.getDouble(9) - d;
				}
				completeAppointment.setTotaldiscamount(String.valueOf(total));
				
				completeAppointment.setDiscaprovestatus(rs.getString(10));
				completeAppointment.setRemark(rs.getString(12));
				completeAppointment.setApprove_userid(rs.getString(13));
				completeAppointment.setApprove_note(rs.getString(14));
				if(rs.getInt(2)==0){
					completeAppointment.setApprove_note(rs.getString(18));
				}
				String drname = userProfileDAO.getFullName(rs.getString(11));
				completeAppointment.setPractitionerName(drname);
				completeAppointment.setCancelled(rs.getBoolean(15));
				/*if(rs.getInt(16)==1){
					completeAppointment.setChargeType("OPD");
				}else if(rs.getInt(16)==2){
					completeAppointment.setChargeType("IPD");
				}else if(rs.getInt(16)==3){
					completeAppointment.setChargeType("INVESTIGATION");
				}else if(rs.getInt(16)==4){
					completeAppointment.setChargeType("MEDICINE");
				}else if(rs.getInt(16)==5){
					completeAppointment.setChargeType("Advance & Refund");
				}else if(rs.getInt(16)==6){
					completeAppointment.setChargeType("HD");
				}else if(rs.getInt(16)==7){
					completeAppointment.setChargeType("Blood Bank");
				}else{
					completeAppointment.setChargeType("");
				}
				*/
				completeAppointment.setChargeType(accountsDAO.getInvoiceName(rs.getString(16)));
				
				completeAppointment.setIpdopdseq(accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(2)));
				
				if(!(rs.getString(19)==null || rs.getString(19).equals(""))){
					String approvedate = rs.getString(19);
					approvedate = DateTimeUtils.getIndianDateTimeFormat(approvedate);
					completeAppointment.setApprove_date(approvedate);
				}else{
					completeAppointment.setApprove_date("");
				}
				completeAppointment.setDeleted(rs.getInt(20));
				completeAppointment.setAbrivationid(rs.getString(21));
				completeAppointment.setDeleteremark(rs.getString(22));
				
				arrayList.add(completeAppointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	private double getSumofInvoice(int invoiceid) {
		double res=0;
		try {
			String sql="select debit from apm_charges_invoice  where id="+invoiceid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
					res=rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	private int getInvoiceId(CompleteAppointment completeAppointment2) {
		int res=0;
		try {
			String sql="select invoiced from apm_invoice_assesments  where id="+completeAppointment2.getId()+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)>0){
					res=rs.getInt(1);
							break;
				}else{
					res=0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	private double sumOfAssesmentCharge(int ipdid, String clientid) {
		double res=0;
		try {
			String sql="select charge,unitcharge,chargedisc from apm_invoice_assesments where ipdid="+ipdid+" and clientId="+clientid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				if(rs.getDouble(3)>0){
					res=res+rs.getDouble(2);
				}else{
					res=res+rs.getDouble(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	private boolean checkDiscountApprove(CompleteAppointment completeAppointment2) {
		boolean res=false;
		try {
			String sql="select discapprove from apm_invoice_assesments  where id="+completeAppointment2.getId()+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)>0){
					res=true;
							break;
				}else{
					res=false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	private boolean checkInvoiceCreated(CompleteAppointment completeAppointment2) {
		boolean res=false;
		try {
			String sql="select invoiced from apm_invoice_assesments  where id="+completeAppointment2.getId()+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)>0){
					res=true;
							break;
				}else{
					res=false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public ArrayList<CompleteAppointment> getChargesid(int int1) {
		ArrayList<CompleteAppointment> list=new ArrayList<CompleteAppointment>();
		try {
			String sql="select assesment_id,child_unitcharge,child_discount,child_disctype,child_chargedisc,child_discamt,child_quantity,child_ipdid, child_clientid from child_discount_request  where parent_discreq_id="+int1+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				CompleteAppointment completeAppointment= new CompleteAppointment();
				completeAppointment.setId(rs.getInt(1));
				if(rs.getInt(7)>1){
					double res=rs.getDouble(2)/rs.getInt(7);
				completeAppointment.setUnitcharge(String.valueOf(res));
				}else{
					completeAppointment.setUnitcharge(rs.getString(2));
				}
				
				completeAppointment.setDiscount(rs.getDouble(3));
				completeAppointment.setDisc_type(rs.getString(4));
				completeAppointment.setChargedisc(rs.getString(5));
				if(rs.getInt(7)>1){
					double res=rs.getDouble(6)/rs.getInt(7);
					completeAppointment.setCharges(String.valueOf(res));
				}else{
				completeAppointment.setCharges(rs.getString(6));
				}
				completeAppointment.setQuantity(rs.getInt(7));
				completeAppointment.setIpdid(rs.getInt(8));
				completeAppointment.setClientId(rs.getString(9));
				list.add(completeAppointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public int updateInvoiceDiscout(String id, String userid, String datetime,String approve_notes) {
		int res =0;
		try {
			String sql ="update discount_request set approved_userid='"+userid+"',approved_date='"+datetime+"',approve_note='"+approve_notes+"' where id='"+id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public CompleteAppointment getRequestDiscountData(String invoiceid) {
		CompleteAppointment appointment = new CompleteAppointment();
		try {
			String sql ="select disc_type, disc_amount, druserid, remark, invoiceamount from discount_request where invoiceid='"+invoiceid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				appointment.setDisc_type(rs.getString(1));
				appointment.setDisc_amount(rs.getString(2));
				appointment.setPractitionerId(rs.getString(3));
				appointment.setRemark(rs.getString(4));
				double d=0;
				if(rs.getString(1).equals("0")){
					d = (rs.getDouble(2) * rs.getDouble(5))/100;
				}else{
					d = rs.getDouble(2);
				}
				double doub =  rs.getDouble(5) - d;
				appointment.setTotal(doub);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appointment;
	}


	public int getSelectedSharableChargeStatus(String selectedAppointmentID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "";
		
			 sql = "SELECT shareablecharge FROM apm_appointment_type where id = "+selectedAppointmentID+" ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int deletediscountfromdashboard(String id, String userid, String date, String reason,String invid ) {
		int res=0;
		PreparedStatement ps= null;
		
		try {
			String sql=" update  discount_request set deleted=1 , deletedby='"+userid+"' , deleteremark='"+reason+"', deletedate='"+date+"' where id ="+id+"";
			ps= connection.prepareStatement(sql);
			res= ps.executeUpdate();
			int b=updateReqdiscStatus( invid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}	
private int updateReqdiscStatus(String invid){
	PreparedStatement ps= null;
	int x=0;
	try {
		String sql="update apm_charges_invoice set disc_request=0 where id='"+invid+"'";
		ps=connection.prepareStatement(sql);
		x=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return 0;
}



public ArrayList<Master> getmasterChageTypeListnew(LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		ArrayList<Master>inventoryMasterChargeList = new ArrayList<Master>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT id,name FROM apm_newchargetype ");
		if(loginInfo.isShowinvestigation()){
			buffer.append("where name !='INVESTIGATION'   and procedures='1' ");
		}else{
			buffer.append("where  procedures='1' ");
		}
		buffer.append("order by name ");
		//String sql = "SELECT id,name FROM apm_newchargetype where name !='INVESTIGATION'  order by name ";
		
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
			/*inventoryMasterChargeList = getInventoryMasterChargeList();
			list.addAll(inventoryMasterChargeList);*/
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	

}


public ArrayList<Master> getIpOpProcedureList(String clientid, LoginInfo loginInfo) {
	ArrayList<Master> list= new ArrayList<Master>();
	PreparedStatement ps= null;
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select id,name from apm_appointment_type where chargeType='IP/OP PROCEDURE' group by name");
		ps= connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Master master= new Master();
			master.setId(rs.getInt(1));
			master.setName(rs.getString(2));
			list.add(master);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}


public int getNonEditAmtSts(String selectedAppointmentID) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	
	String sql = "";
	
		 sql = "SELECT noneditamt FROM apm_appointment_type where id = "+selectedAppointmentID+" ";
	try{
		
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			result = rs.getInt(1);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return result;
}


public int getApproveAppliedCount(String fromdate, String todate, boolean accessofapprove, int id, String filter_status,
		String userid, String countdata) {
	int count =0;
	try {
		StringBuilder builder = new StringBuilder();
		todate = todate +" "+"59:59:59";
		builder.append("select count(*) from discount_request ");
		builder.append("inner join apm_charges_invoice on apm_charges_invoice.id = discount_request.invoiceid ");
		builder.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientId ");
		if(!countdata.equals("0")){
			if(countdata.equals("3")) {
				 builder.append("where discount_request.deleted='1' and requested_date between '"+fromdate+"' and '"+todate+"' ");
			}else {
				builder.append("where disc_request='"+countdata+"'  and discount_request.deleted='0' ");
				if(!accessofapprove){
					builder.append("and (druserid='"+id+"' or requested_userid='"+userid+"') ");
				}
			}
			
		}else{
			builder.append("where requested_date between '"+fromdate+"' and '"+todate+"'  and discount_request.deleted='0' ");
			if(!filter_status.equals("0")){
				if(!filter_status.equals("4")){
					builder.append("and disc_request='"+filter_status+"' ");
				}else{
					builder.append("and discount_request.deleted='1' ");
				}
			}
			if(!accessofapprove){
				builder.append("and (druserid='"+id+"' or requested_userid='"+userid+"') ");
			}
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			count =rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return count;
}

public double getDiscountRequestAmount(String invoiceId) {
	double res =0;
	try {
		String sql ="select disc_amount from discount_request where invoiceid='"+invoiceId+"' order by id desc limit 1";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res = rs.getDouble(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}


public int updateDiscRequestAmt(String invoiceId, double discamt) {
	int res =0;
	try {
		String sql ="update discount_request set deleted='1'  where invoiceid='"+invoiceId+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}


public int updateRefundRequestStatus(String invoiceId) {
	int res =0;
	try {
		String sql ="update refund_request_parent set isdeleted='1'  where isfromdisc=1 and manualinvoiceid='"+invoiceId+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}


public int deletediscountindfromdashboard(String id, String userid, String date, String reason) {
	int res=0;
	PreparedStatement ps= null;
	
	try {
		String sql=" update  discount_request set deleted=1 , deletedby='"+userid+"' , deleteremark='"+reason+"', deletedate='"+date+"' where id ="+id+"";
		ps= connection.prepareStatement(sql);
		res= ps.executeUpdate();
		ArrayList<CompleteAppointment> list=getChargesid(Integer.parseInt(id));
		for (CompleteAppointment completeAppointment : list) {
			 res= updateIndDiscount(completeAppointment);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
	
}


private int updateIndDiscount(CompleteAppointment completeAppointment) {
	int result=0;
	try {
		String sql="update apm_invoice_assesments set discreq=0 where id="+completeAppointment.getId()+"";
		PreparedStatement ps=connection.prepareStatement(sql);
		result=ps.executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}


public CompleteAppointment getDeleteDiscountReqData(String id) {
	CompleteAppointment appointment =new CompleteAppointment();
	try {
		String sql ="select invoiceid,requested_userid from discount_request where id='"+id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			appointment.setInvoiced(rs.getInt(1));
			appointment.setRequested_userid(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return appointment;
}


public int setUpdateDiscRequestStatusTo0(int invoiced) {
	int result=0;
	try {
		String sql="update apm_charges_invoice set disc_request=0 where id="+invoiced+"";
		PreparedStatement ps=connection.prepareStatement(sql);
		result=ps.executeUpdate();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return result;
}


public boolean checkDiscRequestDeleted(String discountid) {
	boolean flag = false;
	try {
		String sql ="select * from discount_request where deleted=1 and id='"+discountid+"' ";
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


public boolean checkInvoiceCreatedAgainstDiscReq(String invoiceid) {
	boolean flag = false;
	try {
		String sql ="select disc_request from apm_charges_invoice where id='"+invoiceid+"' and disc_request>=2 ";
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


public boolean checkalredyadmitted(String bedid) {
	boolean flag = false;
	try {
		String sql ="select * from ipd_addmission_form where bedid='"+bedid+"' ";
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


public CompleteAppointment getRequestDiscountDataFromId(String newid) {
	CompleteAppointment appointment = new CompleteAppointment();
	try {
		String sql ="select disc_type, disc_amount,druserid, remark,invoiceid from discount_request where id='"+newid+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			appointment.setDisc_type(rs.getString(1));
			appointment.setDisc_amount(rs.getString(2));
			appointment.setPractitionerId(rs.getString(3));
			appointment.setRemark(rs.getString(4));
			appointment.setInvoiceid(rs.getString(5));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return appointment;
}


public boolean checksetdiary(String commencing, String diaryuserid) {
	boolean flag = false;
	try {
		String sql ="select * from apm_apmt_slot where commencing='"+commencing+"' and diaryuserid="+diaryuserid+" ";
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


@Override
public String getTotalsessionno(String selectedAppointmentID, String masterchargetype) {
	PreparedStatement preparedStatement = null;
	String result = "";
	try {
	String	sql = "SELECT session FROM apm_appointment_type where id = "+selectedAppointmentID+" ";
	 preparedStatement=connection.prepareStatement(sql);
	 ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			result=rs.getString(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}


@Override
public ArrayList<Master> getPhysioIpdmasterChageTypeList(String deptid) {
	PreparedStatement preparedStatement = null;
	ArrayList<Master> list = new ArrayList<Master>();
	ArrayList<Master>inventoryMasterChargeList = new ArrayList<Master>();
	StringBuffer buffer = new StringBuffer();
	buffer.append("SELECT id,discipline FROM apm_discipline ");
	buffer.append("where id='"+deptid+"' ");
	
	
	
	try{
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			Master master = new Master();
			master.setId(rs.getInt(1));
			master.setName(rs.getString(2));
			
			list.add(master);
		}
		
		/*inventoryMasterChargeList = getInventoryMasterChargeList();
		list.addAll(inventoryMasterChargeList);*/
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
	
}

}
