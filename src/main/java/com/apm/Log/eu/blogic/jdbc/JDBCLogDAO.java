package com.apm.Log.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.apm.Appointment.eu.entity.Appointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Log.eu.bi.LogDAO;
import com.apm.Log.eu.entity.Cancelled;
import com.apm.Log.eu.entity.Completed;
import com.apm.Log.eu.entity.CompletedModified;
import com.apm.Log.eu.entity.DNA;
import com.apm.Log.eu.entity.LogDetail;
import com.apm.Log.eu.entity.Modify;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;

public class JDBCLogDAO extends JDBCBaseDAO implements LogDAO{
	DateTimeUtils dateTimeUtils = new DateTimeUtils();
	public JDBCLogDAO(Connection connection){
		this.connection = connection;
		
	}

	public ArrayList<LogDetail> getTreatmentEpisodeList	(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		String sql = "SELECT practitionerid,startdate,name,reportsent,sentdate FROM apm_treatment_episode where clientid= "+clientId+" order by id desc  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				String practitioner = getPractitionerName(rs.getString(1));
				log.setPractitioner(practitioner);
				log.setDate(rs.getString(2));
				log.setHeading(rs.getString(3));
				if(rs.getBoolean(4)==true){
					log.setReposrtStatus("Sent");
				}else{
					log.setReposrtStatus("Not Sent");
				}
				
				if(rs.getString(5)!=null){
					log.setSentDate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(5)));
				}else{
					log.setSentDate("");
				}
				
				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getPractitionerName(String practitionerId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT firstname,lastname FROM apm_user where id= "+practitionerId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1) + " " + rs.getString(2);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/*public ArrayList<LogDetail> getAccChargeList(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		String sql = "select id,practitionername,date,chargetype,status,invoiceDate from apm_invoice_log where clientId = "+clientId+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				log.setInvoiceNo(rs.getString(1));				
				log.setPractitioner(rs.getString(2));
				log.setDate(rs.getString(3));
				log.setChargeType(rs.getString(4));
				log.setStatus(rs.getString(5));
				log.setInvoiceDate(rs.getString(6));
				
				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/

	/*public ArrayList<LogDetail> getAccInvoiceList(String clientId) {
		PreparedStatement preparedStatement = null;
		String s1 = "1";
		String s2 = "2";
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		//String sql = "select invoiceid,commencing,appointmentid,practitionerName,charge from apm_invoice_assesments where clientId = "+clientId+"";
		String sql = "select id,date,payby,debit,deliverstatus,tpid from apm_charges_invoice where clientid = "+clientId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				log.setInvoiceNo(rs.getString(1));
				log.setDate(rs.getString(2));
				log.setPayby(rs.getString(3));
				log.setTotal(rs.getString(4));
				String status = rs.getString(5);
				if(status.equals(s1)){
					log.setDeliverstatus("Print");
				}
				else if(status.equals(s2))
				{
					log.setDeliverstatus("Email");
				}
				else{
					log.setDeliverstatus("No Action");
				}
				
				String clientname = getClientFullName(clientId);
				log.setClientname(clientname);
				String payee = rs.getString(3);
				String payeename = null;
				if(payee.equalsIgnoreCase("Third Party")){
					payeename = getTpname(rs.getInt(6));
					
				}
				else{
					payeename = clientname;
				}
				log.setPayeename(payeename);
				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/
	private String getTpname(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT company_name FROM apm_third_party_details where id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	private String getClientFullName(String clientId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT title,firstname,surname FROM apm_patient where  id = "+clientId+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getInvoicee(String treatmentId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT invoicee FROM apm_treatment_episode where id= "+treatmentId+" ";
		
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

	private String getTreatmentId(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT treatmentEpisodeId FROM apm_available_slot where id= "+id+" ";
		
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

	public ArrayList<LogDetail> getAppointmentList(String clientId,String text,String action,String orderby,String order,String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		if(text!=null){
			if(!text.equals("")){
				String temp[] = text.split("/");
			}
		}
			StringBuffer sql = new StringBuffer();
		
		
//		if(action.equals("All")){
			
			//  10/01/2019  speed up log
//			sql.append("select appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes,id from apm_appointment_log where  ");
//			sql.append("id in(select max(id) from apm_appointment_log where clientId = "+clientId+" and commencing between '"+fromdate+"' and '"+todate+"' group by appmt_id ) ");
//		}
		
		String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
		String datetemp[] = currentDate.split(" ");
		String temp1[] = datetemp[0].split("-");
		String date = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		
//		if(action.equals("Past")){
//			sql.append("select appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes,id from apm_appointment_log where ");
//			sql.append("id in(select max(id) from apm_appointment_log where clientId = "+clientId+" and commencing < '"+date+"' group by appmt_id ) ");
//		}
		
	
		
//		if(action.equals("Future")){
//			 sql.append("select appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes,id from apm_appointment_log where  ");
//			 sql.append("id in(select max(id) from apm_appointment_log where clientId = "+clientId+" and commencing >= '"+date+"' group by appmt_id ) ");
//			
//		}
		
		/*sql.append("and status='Booked' ");
		sql.append("order by id desc ");*/
		if(order==null){
			order="";
		}
		sql.append("select appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes,id ");
		sql.append("from  (SELECT * FROM apm_appointment_log ORDER BY id DESC) AS tempr where clientId ="+clientId+" ");
		if(action.equals("All")){
		sql.append("and commencing between '"+fromdate+"' and '"+todate+"' ");
		}
		if(action.equals("Past")){
			sql.append("and commencing < '"+date+"' ");
		}
		if(action.equals("Future")){
			sql.append("and commencing >= '"+date+"' ");
		}
		sql.append("group by appmt_id order by "+orderby+ " "+order+" ");
		
		/*StringBuffer s = new StringBuffer();
		
		if(!text.equals("")){
			for(int i=0;i<temp.length;i++){
				s.append("'"+temp[i]+"'" + ",");
			}
			String str = s.substring(0, s.length()-1);
			sql.append("and status in("+str+") ");
		}else{
			sql.append("and status !='DNA' and status !='Cancelled'");
		}*/
		
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot = getDetailsOfApmt(rs.getInt(1));
				log.setApmtId(rs.getInt(1));
				log.setDate(rs.getString(2));
				log.setTime(rs.getString(3));
				log.setHeading(notAvailableSlot.getApmttypetext());
				log.setPractitioner(notAvailableSlot.getDiaryUser());
				log.setTotal(dateTimeUtils.changeFormat(notAvailableSlot.getCharge()));
				
				log.setAddedBy(rs.getString(4));
				log.setApmtLocation(notAvailableSlot.getLocation());
				log.setPractiitonerId(notAvailableSlot.getDiaryUserId());
				log.setPayby(notAvailableSlot.getWhopay());
				log.setClientname(notAvailableSlot.getClientName());
				log.setStatus(rs.getString(5));
				log.setApmtStartTime(rs.getString(6));
				log.setApmtDate(rs.getString(7));
				if(rs.getString(8)!=null){
					log.setCancelApmtsNotes(rs.getString(8));
				}else{
					log.setCancelApmtsNotes("");
				}
				
				int id = rs.getInt(9);
				log.setId(id);
				
				if(log.getStatus().equals("Cancelled")){
					NotAvailableSlot cancdata = getCalceledLogData(id);
					log.setPractitioner(cancdata.getDiaryUser());
					log.setApmtLocation(cancdata.getLocation());
					log.setHeading(cancdata.getApmtType());
					log.setTotal(dateTimeUtils.changeFormat(cancdata.getCharge()));
				}
				
				//DNA
				ArrayList<DNA>DNAList = getHistoryAppointmentList(log.getApmtId(),text,id);
				log.setDnaAppointmentList(DNAList);
				
				
			/*	//modified
				ArrayList<Modify>modifyList = getModifyAppointmentList(log.getApmtId(),text);
				log.setModiAppintmentList(modifyList);
				
				//DNA
				ArrayList<DNA>DNAList = getDNAAppointmentList(log.getApmtId(),text);
				log.setDnaAppointmentList(DNAList);
				
				//completed
				
				ArrayList<Completed>completedList = getCompletedAppointmentList(log.getApmtId(),text);
				log.setCompletedAppointmentList(completedList);
				
				//completed Modified
				
				ArrayList<CompletedModified>cmList = getCMAppointmentList(log.getApmtId(),text);
				log.setCmAppointmentList(cmList);
				
				//cancelled
				ArrayList<Cancelled>canceledList = getCancelledppointmentList(log.getApmtId(),text);
				log.setCanceledAppointmentList(canceledList);
*/				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	private NotAvailableSlot getCalceledLogData(int id) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT practitoner,location,apmttype,charges FROM apm_appointment_log  where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				if(rs.getString(1)!=null){
					notAvailableSlot.setDiaryUser(rs.getString(1));
					notAvailableSlot.setLocation(rs.getString(2));
					notAvailableSlot.setApmtType(rs.getString(3));
					notAvailableSlot.setCharge(rs.getDouble(4));
				}else{
					notAvailableSlot.setDiaryUser("");
					notAvailableSlot.setLocation("");
					notAvailableSlot.setApmtType("");
					notAvailableSlot.setCharge(0);
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	private ArrayList<DNA> getHistoryAppointmentList(int apmtId, String text,int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<DNA>list = new ArrayList<DNA>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes FROM apm_appointment_log where appmt_id = "+apmtId+" and id!="+id+" ");
		sql.append("order by id desc");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DNA data = new DNA();
				data.setApmtId(rs.getString(1));
				data.setDate(rs.getString(2));
				data.setTime(rs.getString(3));
				data.setAddedBy(rs.getString(4));
				data.setStatus(rs.getString(5));
				data.setApmtStartTime(rs.getString(6));
				data.setApmtDate(rs.getString(7));
				data.setCancelApmtsNotes(rs.getString(8));
				list.add(data);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	
	private ArrayList<Cancelled> getCancelledppointmentList(int apmtId, String text) {
		PreparedStatement preparedStatement = null;
		ArrayList<Cancelled>list = new ArrayList<Cancelled>();
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes FROM apm_appointment_log where status = 'Cancelled'  and appmt_id = "+apmtId+" ");
		sql.append("order by id desc");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Cancelled data = new Cancelled();
				data.setApmtId(rs.getString(1));
				data.setDate(rs.getString(2));
				data.setTime(rs.getString(3));
				data.setAddedBy(rs.getString(4));
				data.setStatus(rs.getString(5));
				data.setApmtStartTime(rs.getString(6));
				data.setApmtDate(rs.getString(7));
				data.setCancelApmtsNotes(rs.getString(8));
				list.add(data);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	private ArrayList<CompletedModified> getCMAppointmentList(int apmtId, String text) {
		PreparedStatement preparedStatement = null;
		ArrayList<CompletedModified>list = new ArrayList<CompletedModified>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes FROM apm_appointment_log where status = 'Completed Modified'  and appmt_id = "+apmtId+" ");
		sql.append("order by id desc");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				CompletedModified data = new CompletedModified();
				data.setApmtId(rs.getString(1));
				data.setDate(rs.getString(2));
				data.setTime(rs.getString(3));
				data.setAddedBy(rs.getString(4));
				data.setStatus(rs.getString(5));
				data.setApmtStartTime(rs.getString(6));
				data.setApmtDate(rs.getString(7));
				data.setCancelApmtsNotes(rs.getString(8));
				list.add(data);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	private ArrayList<Completed> getCompletedAppointmentList(int apmtId, String text) {
		PreparedStatement preparedStatement = null;
		ArrayList<Completed>list = new ArrayList<Completed>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes FROM apm_appointment_log where status = 'Completed'  and appmt_id = "+apmtId+" ");
		sql.append("order by id desc");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Completed data = new Completed();
				data.setApmtId(rs.getString(1));
				data.setDate(rs.getString(2));
				data.setTime(rs.getString(3));
				data.setAddedBy(rs.getString(4));
				data.setStatus(rs.getString(5));
				data.setApmtStartTime(rs.getString(6));
				data.setApmtDate(rs.getString(7));
				data.setCancelApmtsNotes(rs.getString(8));
				list.add(data);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	
	private ArrayList<DNA> getDNAAppointmentList(int apmtId, String text) {
		PreparedStatement preparedStatement = null;
		ArrayList<DNA>list = new ArrayList<DNA>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes FROM apm_appointment_log where status = 'DNA'  and appmt_id = "+apmtId+" ");
		sql.append("order by id desc");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DNA data = new DNA();
				data.setApmtId(rs.getString(1));
				data.setDate(rs.getString(2));
				data.setTime(rs.getString(3));
				data.setAddedBy(rs.getString(4));
				data.setStatus(rs.getString(5));
				data.setApmtStartTime(rs.getString(6));
				data.setApmtDate(rs.getString(7));
				data.setCancelApmtsNotes(rs.getString(8));
				list.add(data);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	

	private ArrayList<Modify> getModifyAppointmentList(int apmtId, String text) {
		PreparedStatement preparedStatement = null;
		ArrayList<Modify>list = new ArrayList<Modify>();
		String temp[] = text.split("/");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes FROM apm_appointment_log where status = 'Modified'  and appmt_id = "+apmtId+" ");
		sql.append("order by id desc");
		
		/*for(int i=0;i<temp.length;i++){
			
			
			if(i==(temp.length-1)){
			sql.append("status like('%"+temp[i]+"%'))");
			}
			else{
				sql.append("status like('%"+temp[i]+"%') or ");
			}
			
		}*/
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Modify modify = new Modify();
				modify.setApmtId(rs.getString(1));
				modify.setDate(rs.getString(2));
				modify.setTime(rs.getString(3));
				modify.setAddedBy(rs.getString(4));
				modify.setStatus(rs.getString(5));
				modify.setApmtStartTime(rs.getString(6));
				modify.setApmtDate(rs.getString(7));
				modify.setCancelApmtsNotes(rs.getString(8));
				list.add(modify);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	private NotAvailableSlot getDetailsOfApmt(int id) {
		PreparedStatement preparedStatement = null;
		
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "select diaryusername,apmttypetext,charge,commencing,diaryuserid,location,whopay,clientname from apm_available_slot where id = "+id+"";

		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				notAvailableSlot.setDiaryUser(rs.getString(1));
				notAvailableSlot.setApmttypetext(rs.getString(2));
				notAvailableSlot.setCharge(rs.getDouble(3));
				notAvailableSlot.setCommencing(rs.getString(4));
				notAvailableSlot.setDiaryUserId(rs.getInt(5));
				
				String locationname = getLocationName(rs.getString(6));
				notAvailableSlot.setLocation(locationname);
				notAvailableSlot.setWhopay(rs.getString(7));
				notAvailableSlot.setClientName(rs.getString(8));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	private String getLocationName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT location FROM apm_clinic_location where id= "+id+" ";
		
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

	/*public ArrayList<LogDetail> getPaymentList(String clientId) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		String sql = "select id,chargeinvoiceid,payment,paymode,date,deliver_status from apm_charges_payment where clientid = "+clientId+"";

		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				log.setId(rs.getInt(1));
				log.setInvoiceNo(rs.getString(2));
				log.setTotal(rs.getString(3));
				log.setPaymentMode(rs.getString(4));
				log.setDate(rs.getString(5));
				
				log.setDeliverstatus(rs.getString(6));
				
				if(rs.getString(6)==null){
					log.setDeliverstatus("No Action");
				}
				
				
				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/

	public ArrayList<LogDetail> getEmailHistoryList(String clientid) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		String sql = "";
		/*if(!fromDate.equals("") && !toDate.equals("")){
			
			sql = "select invoice_id,to1,cc,subject,body_text,date,time,filename from apm_email_log where type = 'Invoice' and date between '"+fromDate+"' and '"+toDate+"' and (subject like('%"+searchText+"%') or  body_text like('%"+searchText+"%'))";
		}else{
			sql = "select invoice_id,to1,cc,subject,body_text,date,time,filename from apm_email_log where type = 'Invoice' and subject like('%"+searchText+"%') or  body_text like('%"+searchText+"%')";
		}*/
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				log.setInvoice_id(rs.getInt(1));
				log.setTo(rs.getString(2));
				log.setCc(rs.getString(3));
				log.setSubject(rs.getString(4));
				log.setBody_text(rs.getString(5));
				log.setDate(rs.getString(6));
				log.setTime(rs.getString(7));
				log.setFilename(rs.getString(8));
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public ArrayList<LogDetail> getDNAAppointmentList(String clientId,String text) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		String sql = "select appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes from apm_appointment_log where clientId = "+clientId+" and status = 'DNA' order by id desc ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot = getDetailsOfApmt(rs.getInt(1));
				log.setDate(rs.getString(2));
				log.setTime(rs.getString(3));
				log.setHeading(notAvailableSlot.getApmttypetext());
				log.setPractitioner(notAvailableSlot.getDiaryUser());
				log.setTotal(dateTimeUtils.changeFormat(notAvailableSlot.getCharge()));
				
				log.setAddedBy(rs.getString(4));
				log.setDeleteStatus(rs.getString(5));
				log.setApmtLocation(notAvailableSlot.getLocation());
				log.setPractiitonerId(notAvailableSlot.getDiaryUserId());
				log.setPayby(notAvailableSlot.getWhopay());
				log.setClientname(notAvailableSlot.getClientName());
				log.setStatus(rs.getString(5));
				log.setApmtStartTime(rs.getString(6));
				log.setApmtDate(rs.getString(7));
				log.setCancelApmtsNotes(rs.getString(8));
				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public ArrayList<LogDetail> getPastAppointmentList(String clientId,String currentdate,String text,String action,String orderby,String order)  {
		PreparedStatement preparedStatement = null;
		
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		//String sql = "select appmt_id,date,time,performed_by,status,apmt_start_time,commencing from apm_appointment_log where clientId = "+clientId+" and commencing < '"+currentdate+"'";
		
		String temp[] = text.split("/");
		StringBuffer sql = new StringBuffer();
		
		sql.append("select appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes from apm_appointment_log  ");
		
		if(action.equals("All")){
			sql.append("where  id in(select max(id) from apm_appointment_log where clientId = "+clientId+" ");
		}
		
		if(action.equals("Past")){
			sql.append("where  id in(select max(id) from apm_appointment_log where clientId = "+clientId+" and commencing < '"+currentdate+"'  ");
		}
		
		if(action.equals("Future")){
			 sql.append("where  id in(select max(id) from apm_appointment_log where clientId = "+clientId+" and commencing >= '"+currentdate+"' ");
		}
		

		StringBuffer s = new StringBuffer();
		
		if(!text.equals("")){
			for(int i=0;i<temp.length;i++){
				s.append("'"+temp[i]+"'" + ",");
			}
			String str = s.substring(0, s.length()-1);
			sql.append("and status in("+str+") group by appmt_id) ");
		}else{
			sql.append("and status !='DNA' and status !='Cancelled' group by appmt_id) " );
		}
		
		sql.append(" order by "+orderby+ " "+order+" ");
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot = getDetailsOfApmt(rs.getInt(1));
				log.setApmtId(rs.getInt(1));
				log.setDate(rs.getString(2));
				log.setTime(rs.getString(3));
				log.setHeading(notAvailableSlot.getApmttypetext());
				log.setPractitioner(notAvailableSlot.getDiaryUser());
				log.setTotal(dateTimeUtils.changeFormat(notAvailableSlot.getCharge()));
				log.setAddedBy(rs.getString(4));
				log.setApmtLocation(notAvailableSlot.getLocation());
				log.setPractiitonerId(notAvailableSlot.getDiaryUserId());
				log.setPayby(notAvailableSlot.getWhopay());
				log.setClientname(notAvailableSlot.getClientName());
				log.setStatus(rs.getString(5));
				log.setApmtStartTime(rs.getString(6));
				log.setApmtDate(rs.getString(7));
				if(rs.getString(8)!=null){
					log.setCancelApmtsNotes(rs.getString(8));
				}else{
					log.setCancelApmtsNotes("");
				}
				
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public ArrayList<LogDetail> getFutureAppointmentList(String clientId,
			String currentDate,String text) {
PreparedStatement preparedStatement = null;
		
		ArrayList<LogDetail>list = new ArrayList<LogDetail>();
		//String sql = "select appmt_id,date,time,performed_by,status,apmt_start_time,commencing from apm_appointment_log where clientId = "+clientId+" and commencing >= '"+currentDate+"'";
		String temp[] = text.split("/");
		StringBuffer sql = new StringBuffer();
		 sql.append("select appmt_id,date,time,performed_by,status,apmt_start_time,commencing,cancel_apmts_notes from apm_appointment_log where clientId = "+clientId+" and commencing >= '"+currentDate+"' ");

			StringBuffer s = new StringBuffer();
			
			if(!text.equals("")){
				for(int i=0;i<temp.length;i++){
					s.append("'"+temp[i]+"'" + ",");
				}
				String str = s.substring(0, s.length()-1);
				sql.append("and status in("+str+") ");
			}else{
				sql.append("and status !='DNA' and status !='Cancelled'");
			}
			
			sql.append("order by id desc");
			
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				LogDetail log = new LogDetail();
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot = getDetailsOfApmt(rs.getInt(1));
				log.setApmtId(rs.getInt(1));
				log.setDate(rs.getString(2));
				log.setTime(rs.getString(3));
				log.setHeading(notAvailableSlot.getApmttypetext());
				log.setPractitioner(notAvailableSlot.getDiaryUser());
				log.setTotal(dateTimeUtils.changeFormat(notAvailableSlot.getCharge()));
				
				log.setAddedBy(rs.getString(4));
				log.setApmtLocation(notAvailableSlot.getLocation());
				log.setPractiitonerId(notAvailableSlot.getDiaryUserId());
				log.setPayby(notAvailableSlot.getWhopay());
				log.setClientname(notAvailableSlot.getClientName());
				log.setStatus(rs.getString(5));
				log.setApmtStartTime(rs.getString(6));
				log.setApmtDate(rs.getString(7));
				log.setCancelApmtsNotes(rs.getString(8));
				list.add(log);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean checkAppointmentCompleted(int apmtId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_invoice_assesments where appointmentid = "+apmtId+" ";
		
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

	public boolean checkApmtIsDNA(int apmtId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT dna FROM apm_available_slot where id = "+apmtId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int dna = rs.getInt(1);
				if(dna == 1){
				result = true;
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkApmtIsInvoice(int apmtId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT id FROM apm_invoice where appointmentid = "+apmtId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int id = rs.getInt(1);
				boolean checkinvoice = checkInvoice(id);
				if(checkinvoice){
				result = true;
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean checkInvoice(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_charges_assesment where invoiceid = "+id+" ";
		
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

	public boolean checkPaymentDone(int apmtId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT id FROM apm_invoice where appointmentid = "+apmtId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int invoiceid = rs.getInt(1);
				int chargeInvoiceId = getChargeInvoiceId(invoiceid);
				boolean checkpaymentDone = checkPayment(chargeInvoiceId);
				if(checkpaymentDone){
				result = true;
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean checkPayment(int chargeInvoiceId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_charges_payment where chargeinvoiceid = "+chargeInvoiceId+" ";
		
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

	private int getChargeInvoiceId(int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT chargeinvoiceid FROM apm_charges_assesment where invoiceid= "+invoiceid+" ";
		
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

	public ArrayList<NotAvailableSlot> getTreatmentEpisodeCountList(
			String clientId, String treatmentEpisodeid, String whopay) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT commencing,name,sessions,apm_available_slot.condition_id,apm_available_slot.usedsession,dnaoffset,apm_available_slot.id,apm_available_slot.dna FROM apm_available_slot inner join apm_treatment_episode on ");
		sql.append("apm_treatment_episode.id = apm_available_slot.treatmentEpisodeId ");
		sql.append("where apm_available_slot.clientid="+clientId+" and whopay='"+whopay+"' and treatmentEpisodeId="+treatmentEpisodeid+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setCommencing(dateTimeUtils.getCommencingDate1(rs.getString(1)));
				notAvailableSlot.setTreatmentEpisodeName(rs.getString(2) + " " + rs.getString(3) + " " + "sess" );
				notAvailableSlot.setSessions(rs.getString(3));
				String conditionid = rs.getString(4);
				String conditionName = getConditionName(conditionid);
				notAvailableSlot.setCondition(conditionName);
				notAvailableSlot.setUsedsession(rs.getString(5) + " " + "of"  + " " + rs.getString(3));
				notAvailableSlot.setDnaOffset(rs.getBoolean(6));
				notAvailableSlot.setId(rs.getInt(7));
				notAvailableSlot.setDna(rs.getBoolean(8));
				
				
				list.add(notAvailableSlot);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private String getConditionName(String conditionid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_condition where id ="+conditionid+" ";
		
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

	
	public String getCancelNote(String id) {
		PreparedStatement preparedStatement = null;
		String result ="";
		String sql = "SELECT cancel_apmts_notes FROM apm_appointment_log  where id = "+id+" ";
		
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

	@Override
	public int saveresponsetime(String module, String req_datetime) {
		int res=0;
		String sql="insert into response_time (module,request_time) values (?,?) ";
		try {
			PreparedStatement  preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, module);
			preparedStatement.setString(2, req_datetime);
			
			res=preparedStatement.executeUpdate();
			if(res==1) {
				ResultSet rs=preparedStatement.getGeneratedKeys();
				if(rs.next()) {
					res=rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateresponsetime(String module, String req_datetime, String requestid, String duration_of_time) {
		int res=0;
		String sql="update response_time set module=?,response_time=?,duration=? where id=? ";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, module);
			preparedStatement.setString(2, req_datetime);
			preparedStatement.setString(3, duration_of_time);
			preparedStatement.setString(4, requestid);
			res=preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	
}
