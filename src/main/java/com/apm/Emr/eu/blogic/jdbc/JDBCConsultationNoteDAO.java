package com.apm.Emr.eu.blogic.jdbc;

import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.struts2.convention.annotation.Results;

import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Emr.eu.bi.ConsultationNoteDAO;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

public class JDBCConsultationNoteDAO extends JDBCBaseDAO implements ConsultationNoteDAO{
	
	public JDBCConsultationNoteDAO(Connection connection){
		this.connection = connection;
		
	}
	
	
	public ArrayList<Client> getClientListEmr(int practId,String clientid,String oldpractid,String apmtid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		UserProfile userProfile = userProfileDAO.getUserprofileDetails(DateTimeUtils.convertToInteger(oldpractid));
		
		String sql = "select clientId from apm_available_slot where diaryuserid = "+practId+" and clientId="+clientid+" order by id desc limit 0,1 ";
		if(userProfile.getJobgroup()!=null){
		if(userProfile.getJobgroup().equals("4")){
			sql = "select clientId from apm_available_slot where doctor = "+userProfile.getDoctor()+" and clientId="+clientid+" order by id desc limit 0,1 ";
		}
		}
		if(!apmtid.equals("0")){
			if(DateTimeUtils.convertToInteger(oldpractid)==practId){
				if(userProfile.getJobgroup().equals("4")){
					sql = "select clientId from apm_available_slot where doctor = "+oldpractid+" and clientId="+clientid+" order by id desc limit 0,1 ";
				}			}
		}
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				client.setId(rs.getInt(1));
				String clientname = getClientFullName(rs.getInt(1));
				client.setClientName(clientname);
				
				
				
				list.add(client);
			}
			
			ArrayList<Client>ipdList = getIpdClientListEmr(practId,clientid);
			list.addAll(ipdList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private ArrayList<Client> getIpdClientListEmr(int practId,String clientid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		String sql = "SELECT clientid FROM ipd_addmission_form where practitionerid = "+practId+" and clientId="+clientid+" order by id desc limit 0,1 ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				client.setId(rs.getInt(1));
				String clientname = getClientFullName(rs.getInt(1));
				client.setClientName(clientname);
				
				boolean checkClientexist = checkClientExist(client.getId(),practId);
				
				if(!checkClientexist){
					list.add(client);
				}
				
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public ArrayList<Client> getClientList(int practId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		
	/*	UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		UserProfile userProfile = userProfileDAO.getUserprofileDetails(practId);
		*/
		
		
		String sql = "select clientId from apm_available_slot inner join apm_patient on apm_available_slot.clientId=apm_patient.id where apm_patient.status=1 and apm_available_slot.diaryuserid="+practId+" group by apm_available_slot.clientId";
		
		/*if(userProfile.getJobgroup().equals("4")){
			sql = "select clientId from apm_available_slot where doctor = "+userProfile.getDoctor()+" group by clientId ";
		}
		*/
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				client.setId(rs.getInt(1));
				String clientname = getClientFullName(rs.getInt(1));
				client.setClientName(clientname);
				
				
				
				list.add(client);
			}
			
			ArrayList<Client>ipdList = getIpdClientList(practId);
			list.addAll(ipdList);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	

	private ArrayList<Client> getIpdClientList(int practId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		String sql = "SELECT clientid FROM ipd_addmission_form where practitionerid = "+practId+" group by clientId";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				client.setId(rs.getInt(1));
				String clientname = getClientFullName(rs.getInt(1));
				client.setClientName(clientname);
				
				boolean checkClientexist = checkClientExist(client.getId(),practId);
				
				if(!checkClientexist){
					list.add(client);
				}
				
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	private boolean checkClientExist(int id, int practId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_available_slot where diaryuserid = "+practId+" and clientid = "+id+" ";
		
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

	private String getClientFullName(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT title,firstname,surname FROM apm_patient where  id = "+id+"";
		
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

	public ArrayList<Client> getConditionList(int clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		String sql = "SELECT id,name FROM apm_condition ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				
				client.setId(rs.getInt(1));
				//String condition = getConditionName(rs.getInt(1));
				client.setTreatmentType(rs.getString(2));
				
				list.add(client);
			}
			
			/*ArrayList<Client>ipdConditionList = getIpdConditionList(clientId);
			list.addAll(ipdConditionList);*/
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	

	private ArrayList<Client> getIpdConditionList(int clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		String sql = "SELECT conditionid FROM ipd_addmission_form where clientid = "+clientId+" group by conditionid";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				
				client.setId(rs.getInt(1));
				String condition = getConditionName(rs.getInt(1));
				client.setTreatmentType(condition);
				
				boolean checkConditionExist = checkConditionExist(client.getId(),clientId);
				
				if(!checkConditionExist){
					list.add(client);
				}
				
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private boolean checkConditionExist(int conditionid, int clientId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_available_slot where clientid = "+clientId+" and condition_id = "+conditionid+" ";
		
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
	
	

	private String getConditionName(int id) {
		PreparedStatement preparedStatement = null;
		String condition = "";
	String sql = "SELECT id,name,diseasecode,icdcode FROM apm_condition where  id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				condition = rs.getString(2);
				
				if(rs.getString(3)!=null && rs.getString(4)!=null){
					condition =  condition  + " " + rs.getString(3) + " / " + rs.getString(4);
				}
				
				else if(rs.getString(4)!=null){
					condition = condition + " / " + rs.getString(4);
				}
				
				
			/*	if(rs.getString(2)!=null && rs.getString(3)!=null && rs.getString(4)!=null){
					condition = rs.getString(2) + " / " + condition + " / " + rs.getString(3) + " / " + rs.getString(4);
				}
				else if(rs.getString(2)!=null && rs.getString(3)!=null){
					condition = rs.getString(2) + " / " + condition + " / " + rs.getString(3);
				}	
				else if(rs.getString(2)!=null){
					condition = rs.getString(2) + " / " + condition;
				}
				*/
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return condition;
	}

	public ArrayList<Emr> getConsultationNoteList(String diaryUser,
			String clientname, String condition) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		ArrayList<Emr>invlist = new ArrayList<Emr>();
		ArrayList<Emr>prisclist = new ArrayList<Emr>();
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
		StringBuffer buffer=new StringBuffer();
		boolean islmh=profileDAO.getIsLMH();
		if(islmh){
			buffer.append("SELECT apm_consultation_note.id,history,lastmodified,practitionerid,appointmentid,discipline,patientid ");
			buffer.append("FROM apm_consultation_note ");
			buffer.append("inner join apm_user on apm_user.id=practitionerid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription ");
			buffer.append("where patientid = "+clientname+" "); 
			buffer.append("order by id asc");
		}else{
			buffer.append("SELECT id,history,lastmodified,practitionerid,appointmentid FROM apm_consultation_note where " );
			buffer.append("patientid = "+clientname+" order by id desc ");
		}
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);
				emr.setDepartment(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(profileDAO.getSpeciality(rs.getString(4))))));
				if(islmh){
					ArrayList<Emr> secondarylist=new ArrayList<Emr>();
					if(rs.getInt(5)!=0){
						String listofsecdr=emrDAO.getsecondarydrlist(rs.getString(7),rs.getString(5));
						
						String data[]=listofsecdr.split(",");
						if(!listofsecdr.equals("")) {
							if(data.length>0) {
								for (String string : data) {
									Emr emr1=new Emr();
									emr1.setSecondarydr(profileDAO.getFullName(string));
									secondarylist.add(emr1);
								}
							}
						}
					}
					emr.setSecondarylist(secondarylist);
					}
				list.add(emr);
				
			}
			invlist=getConsultationNoteListInvestigation(diaryUser, clientname, condition);
			prisclist=getConsultationNoteListPriscription(diaryUser, clientname, condition);
			list.addAll(invlist);
			list.addAll(prisclist);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	private String getConsultationHeadingData(int appointmentid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT name,aptmtype,commencing FROM apm_treatment_episode inner join apm_available_slot on ");
		sql.append("apm_available_slot.treatmentEpisodeId = apm_treatment_episode.id where apm_available_slot.id = "+appointmentid+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				String name = rs.getString(1);
				AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
				AppointmentType appointmentType = appointmentTypeDAO.getAppointment(rs.getInt(2));
	    		String appointmentText = appointmentType.getName();
	    		String commencing = DateTimeUtils.changeDateFormatTemplate(DateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3)));
	    		
	    		result = "Treatment "  + name +  " Appointment " + appointmentText  + " " + "-" + " " + commencing; 
	    		
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}

	private String getUserName(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT initial,firstname,lastname,usertype,clinicowner FROM apm_user where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){				
				int usertype = rs.getInt(4);
				if(usertype == 2){
					result = rs.getString(5);
				}
				else{
					result = rs.getString(1)+" "+ rs.getString(2) + " " + rs.getString(3);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return result;
	}

	public int saveNote(String practId, String clientId, String conditionId,
			String consNote) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_consultation_note(patientid,history,practitionerid,lastmodified,condition_id) values(?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, consNote);
			preparedStatement.setString(3, practId);
			preparedStatement.setTimestamp(4, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(5, conditionId);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String getNote(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT history FROM apm_consultation_note where  id = "+id+"";
		
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

	public int updateNote(String practId, String clientId, String conditionId,
			String consNote,String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_consultation_note set patientid = ?,history = ?,practitionerid = ?,lastmodified = ?,condition_id = ? where id = "+id+"";		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, consNote);
			preparedStatement.setString(3, practId);
			preparedStatement.setTimestamp(4, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(5, conditionId);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteNote(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_consultation_note where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, id);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getCondtion(int apmtId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT condition_id FROM apm_available_slot where  id = "+apmtId+"";
		
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

	public ArrayList<Client> getClientList() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Client> getConditionList() {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteAllConsultationNote(String apmtId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_consultation_note where appointmentid = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, apmtId);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Emr> getConsultationNoteList(String practionerId,
			String patientid, String condition, int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,lastmodified,practitionerid FROM apm_consultation_note where condition_id = "+condition+" and practitionerid="+practionerId+" "
				+ "and patientid = "+patientid+" and appointmentid = "+id+" order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(rs.getString(3));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int getconditionID(int apmtId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT condition_id FROM apm_available_slot where id  = "+apmtId+" ";
		
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

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		String sql = "SELECT id,name FROM apm_treatment_episode where clientid = "+clientId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(2));
				
				list.add(treatmentEpisode);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public String getTreatmentEpisodeid(String apmtId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT treatmentEpisodeId FROM apm_available_slot where id = '"+apmtId+"' ";
		
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

	public TreatmentEpisode getTreatmentEpisodeDischargeData(
			String treatmentEpisodeid) {
		PreparedStatement preparedStatement = null;
		TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
		String sql = "SELECT treatmentstatus,outcomes,dschargestatus FROM apm_treatment_episode where id = "+treatmentEpisodeid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				treatmentEpisode.setTrtmentStatus(rs.getInt(1));
				treatmentEpisode.setOutcomes(rs.getString(2));
				treatmentEpisode.setDschargestatus(rs.getString(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return treatmentEpisode;
	}

	public double getInvoiceDebitTotal(String whopay, String tpid,
			String patientid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(debit) FROM apm_charges_invoice where clientid = "+patientid+" and tpid = "+tpid+" and payby = '"+whopay+"' group by tpid";
		
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

	public double getTotalPaymentDone(String whopay, String tpid,
			String patientid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		
		if(whopay.equals(Constants.PAY_BY_CLIENT)){
			tpid = "0";
		}
		
		String sql = "SELECT sum(payment) FROM apm_charges_payment where clientid = "+patientid+" and tpid = "+tpid+" ";
		
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
	
	
	public ArrayList<Emr> printEditConsultationNote(String editid){
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		boolean islmh=profileDAO.getIsLMH();
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
		StringBuffer buffer=new StringBuffer();
		if(islmh){
			buffer.append("SELECT apm_consultation_note.id,history,lastmodified,practitionerid,appointmentid,discipline,patientid ");
			buffer.append("FROM apm_consultation_note ");
			buffer.append("inner join apm_user on apm_user.id=practitionerid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription ");
			buffer.append("and apm_consultation_note.id = "+editid+" "); 
			buffer.append("order by apm_consultation_note.id asc");
		}else{
			buffer.append("SELECT id,history,lastmodified,practitionerid,appointmentid FROM apm_consultation_note where id="+editid+" ");
		}
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
								
				if(islmh){
					String practid=getpractinerid(rs.getString(5));
					String userName = getUserName(Integer.parseInt(practid));
					emr.setPractitionerName(userName);
					}
				
				//emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);
				emr.setDepartment(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(profileDAO.getSpeciality(rs.getString(4))))));
				if(islmh){
					ArrayList<Emr> secondarylist=new ArrayList<Emr>();
					if(rs.getInt(5)!=0){
						String listofsecdr=emrDAO.getsecondarydrlist(rs.getString(7),rs.getString(5));
						
						String data[]=listofsecdr.split(",");
						if(!listofsecdr.equals("")) {
							if(data.length>0) {
								for (String string : data) {
									Emr emr1=new Emr();
									emr1.setSecondarydr(profileDAO.getFullName(string));
									secondarylist.add(emr1);
								}
							}
						}
					}
					emr.setSecondarylist(secondarylist);
					}
					
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Emr> getPrintConsultationNoteList(String practionerId,
			String clientId, String condition, String date) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -7); 
		date = dateFormat.format(cal.getTime());
		
		String todate = date + " 23:59:59";
		
		
		String sql = "SELECT id,history,lastmodified,practitionerid,appointmentid FROM apm_consultation_note where condition_id = "+condition+" and practitionerid="+practionerId+" "
				+ "and patientid = "+clientId+" and lastmodified between '"+date+"' and '"+todate+"' order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	
	public String getIpdTreatmentEpisodeid(String clientname) {
		PreparedStatement  preparedStatement = null;
		String result = "";
		String sql = "SELECT treatmentepisodeid FROM ipd_addmission_form where clientid = "+clientname+" order by id desc limit 0,1 ";
		
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


	public ArrayList<Client> getpbodyclient(String clientname) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		String sql = "select id, concat(title,' ',firstname,' ',surname) from apm_patient where id = "+clientname+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client c=  new Client();
				c.setId(rs.getInt(1));
				c.setClientName(rs.getString(2));
				
				list.add(c);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	public ArrayList<Emr> getConsultationNoteListwithDate(String practionerId, String clientname, String condition,
			String fromdate, String todate,String dept,String apmtid,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		todate=todate+" "+"59:59:59";
		ChargesReportDAO chargesReportDAO=new  JDBCChargesReportDAO(connection);
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ArrayList<Emr>list = new ArrayList<Emr>();
		/*String sql = "SELECT id,history,lastmodified,practitionerid,appointmentid FROM apm_consultation_note where condition_id = "+condition+" and practitionerid="+diaryUser+" "
				+ "and patientid = "+clientname+" order by id desc ";	*/	
		StringBuffer  buffer = new StringBuffer();
		boolean islmh=profileDAO.getIsLMH();
		if(islmh){
			buffer.append("SELECT apm_consultation_note.id,history,lastmodified,practitionerid,appointmentid,discipline,patientid ");
			buffer.append("FROM apm_consultation_note ");
			buffer.append("inner join apm_user on apm_user.id=practitionerid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription ");
			if(loginInfo.isMatrusevasang()){
				buffer.append(" where appointmentid="+apmtid+" ");
			}else{
			buffer.append("where lastmodified between '"+fromdate+"' and '"+todate+"' ");
			}
			buffer.append("and patientid = "+clientname+" "); 
			if(!dept.equals("")){
			buffer.append("and apm_discipline.id="+dept+" ");
			}
			buffer.append("order by id asc");
		}else{
			buffer.append("SELECT id,history,lastmodified,practitionerid,appointmentid,patientid FROM apm_consultation_note ");
			buffer.append(" where lastmodified between '"+fromdate+"' and '"+todate+"' and patientid = "+clientname+" ");
			buffer.append(" order by id asc ");
		}
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				if(islmh){
				String practid=getpractinerid(rs.getString(5));
				String userName = getUserName(Integer.parseInt(practid));
				emr.setPractitionerName(userName);
				}else {
					String userName = getUserName(rs.getInt(4));
					
					emr.setPractitionerName(userName);
				}
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);
				emr.setDepartment(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(profileDAO.getSpeciality(rs.getString(4))))));
				if(islmh){
				ArrayList<Emr> secondarylist=new ArrayList<Emr>();
				if(rs.getInt(5)!=0){
					String listofsecdr=emrDAO.getsecondarydrlist(rs.getString(7),rs.getString(5));
					
					String data[]=listofsecdr.split(",");
					if(!listofsecdr.equals("")) {
						if(data.length>0) {
							for (String string : data) {
								Emr emr1=new Emr();
								emr1.setSecondarydr(profileDAO.getFullName(string));
								secondarylist.add(emr1);
							}
						}
					}
				}
				emr.setSecondarylist(secondarylist);
				}
				
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	private String getpractinerid(String appid) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT diaryuserid from apm_available_slot  where id = "+appid+" ";
		try {
			 preparedStatement = connection.prepareStatement(sql);
			 ResultSet rs = preparedStatement.executeQuery();
			
			 while(rs.next()){
				 
				 result=rs.getString(1);
				 }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}


	


	public ArrayList<Emr> getConsultationNoteListwithDateInv(String practionerId, String clientId, String condition,
			String fromdate, String todate,String dept) {
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		PreparedStatement preparedStatement = null;
		todate=todate+" "+"59:59:59";
		ArrayList<Emr>list = new ArrayList<Emr>();
		StringBuffer buffer=new StringBuffer();
		ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		boolean islmh=profileDAO.getIsLMH();
		if(islmh){
			buffer.append("SELECT emr_investigation.id,history,lastmodified,practitionerid,appointmentid,discipline,patientid ");
			buffer.append("FROM emr_investigation ");
			buffer.append("inner join apm_user on apm_user.id=practitionerid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription ");
			buffer.append("where lastmodified between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("and patientid = "+clientId+" ");
			if(!dept.equals("")){
			buffer.append("and apm_discipline.id="+dept+" ");
			}
			buffer.append("order by id asc");
		}else{
			buffer.append("SELECT id,history,lastmodified,practitionerid,appointmentid,patientid FROM emr_investigation ");
			buffer.append("where lastmodified between '"+fromdate+"' and '"+todate+"' and patientid = "+clientId+"  order by id asc");
		}
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);
				emr.setDepartment(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(profileDAO.getSpeciality(rs.getString(4))))));
			//	emr.setDescription(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(profileDAO.getSpeciality(rs.getString(4))))));
				
				if(islmh){
					ArrayList<Emr> secondarylist=new ArrayList<Emr>();
					if(rs.getInt(5)!=0){
						String listofsecdr=emrDAO.getsecondarydrlist(rs.getString(7),rs.getString(5));
						
						String data[]=listofsecdr.split(",");
						if(!listofsecdr.equals("")) {
							if(data.length>0) {
								for (String string : data) {
									Emr emr1=new Emr();
									emr1.setSecondarydr(profileDAO.getFullName(string));
									secondarylist.add(emr1);
								}
							}
						}
					}
					emr.setSecondarylist(secondarylist);
					}
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	
	}


	public ArrayList<Emr> getConsultationNoteListwithDatePrisc(String practionerId, String clientId, String condition,
			String fromdate, String todate,String dept) {


		PreparedStatement preparedStatement = null;
		todate=todate+" "+"59:59:59";
		ArrayList<Emr>list = new ArrayList<Emr>();
		StringBuffer buffer=new StringBuffer();
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
		boolean islmh=profileDAO.getIsLMH();
		if(islmh){
			buffer.append("SELECT emr_priscription.id,history,lastmodified,practitionerid,appointmentid,discipline,patientid ");
			buffer.append("FROM emr_priscription ");
			buffer.append("inner join apm_user on apm_user.id=practitionerid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription ");
			buffer.append("where lastmodified between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("and patientid = "+clientId+" "); 
			if(!dept.equals("")){
			buffer.append("and apm_discipline.id="+dept+" ");
			}
			buffer.append("order by id asc");
		}else{
			buffer.append("SELECT id,history,lastmodified,practitionerid,appointmentid,patientid FROM emr_priscription ");
			buffer.append("where lastmodified between '"+fromdate+"' and '"+todate+"' and patientid = "+clientId+"  order by id asc");
		}
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);
				emr.setDepartment(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(profileDAO.getSpeciality(rs.getString(4))))));
				
				if(islmh){
					ArrayList<Emr> secondarylist=new ArrayList<Emr>();
					if(rs.getInt(5)!=0){
						String listofsecdr=emrDAO.getsecondarydrlist(rs.getString(7),rs.getString(5));
						
						String data[]=listofsecdr.split(",");
						if(!listofsecdr.equals("")) {
							if(data.length>0) {
								for (String string : data) {
									Emr emr1=new Emr();
									emr1.setSecondarydr(profileDAO.getFullName(string));
									secondarylist.add(emr1);
								}
							}
						}
					}
					emr.setSecondarylist(secondarylist);
					}
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	
	
	}


	public ArrayList<Emr> getConsultationNoteListInvestigation(String practionerId, String clientId, String condition) {

		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
		StringBuffer buffer=new StringBuffer();
		boolean islmh=profileDAO.getIsLMH();
		/*String sql = "SELECT id,history,lastmodified,practitionerid,appointmentid FROM apm_consultation_note where condition_id = "+condition+" and practitionerid="+diaryUser+" "
				+ "and patientid = "+clientname+" order by id desc ";	*/	
		if(islmh){
			buffer.append("SELECT apm_consultation_note.id,history,lastmodified,practitionerid,appointmentid,discipline,patientid ");
			buffer.append("FROM emr_investigation ");
			buffer.append("inner join apm_user on apm_user.id=practitionerid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription ");
			buffer.append("where patientid = "+clientId+" "); 
			buffer.append("order by id asc");
		}else{
			buffer.append("SELECT id,history,lastmodified,practitionerid,appointmentid FROM emr_investigation where " );
			buffer.append("patientid = "+clientId+" order by id desc ");
		}
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2).replaceAll("<br><table", "<table"));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);
				emr.setDepartment(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(profileDAO.getSpeciality(rs.getString(4))))));
				if(islmh){
					ArrayList<Emr> secondarylist=new ArrayList<Emr>();
					if(rs.getInt(5)!=0){
						String listofsecdr=emrDAO.getsecondarydrlist(rs.getString(7),rs.getString(5));
						
						String data[]=listofsecdr.split(",");
						if(!listofsecdr.equals("")) {
							if(data.length>0) {
								for (String string : data) {
									Emr emr1=new Emr();
									emr1.setSecondarydr(profileDAO.getFullName(string));
									secondarylist.add(emr1);
								}
							}
						}
					}
					emr.setSecondarylist(secondarylist);
					}
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	
	}


	public ArrayList<Emr> printEditConsultationNoteInvestigation(String editid) {

		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		
		
		
		
		String sql = "SELECT id,history,lastmodified,practitionerid,appointmentid FROM emr_investigation where id="+editid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	
	}


	public ArrayList<Emr> getPrintConsultationNoteListInvestigation(String practionerId, String clientId,
			String condition, String date) {

		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -7); 
		date = dateFormat.format(cal.getTime());
		
		String todate = date + " 23:59:59";
		
		
		String sql = "SELECT id,history,lastmodified,practitionerid,appointmentid FROM emr_investigation where condition_id = "+condition+" and practitionerid="+practionerId+" "
				+ "and patientid = "+clientId+" and lastmodified between '"+date+"' and '"+todate+"' order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	
	}

	
	
	
	public ArrayList<Emr> getConsultationNoteListPriscription(String practionerId, String clientId, String condition) {

		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ChargesReportDAO chargesReportDAO=new JDBCChargesReportDAO(connection);
		StringBuffer buffer=new StringBuffer();
		boolean islmh=profileDAO.getIsLMH();
		if(islmh){
			buffer.append("SELECT apm_consultation_note.id,history,lastmodified,practitionerid,appointmentid,discipline,patientid ");
			buffer.append("FROM emr_priscription ");
			buffer.append("inner join apm_user on apm_user.id=practitionerid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription ");
			buffer.append("where patientid = "+clientId+" "); 
			buffer.append("order by id asc");
		}else{
			buffer.append("SELECT id,history,lastmodified,practitionerid,appointmentid FROM emr_priscription where " );
			buffer.append("patientid = "+clientId+" order by id desc ");
		}
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2).replaceAll("<br>", ""));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);
				emr.setDepartment(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(profileDAO.getSpeciality(rs.getString(4))))));
				if(islmh){
					ArrayList<Emr> secondarylist=new ArrayList<Emr>();
					if(rs.getInt(5)!=0){
						String listofsecdr=emrDAO.getsecondarydrlist(rs.getString(7),rs.getString(5));
						
						String data[]=listofsecdr.split(",");
						if(!listofsecdr.equals("")) {
							if(data.length>0) {
								for (String string : data) {
									Emr emr1=new Emr();
									emr1.setSecondarydr(profileDAO.getFullName(string));
									secondarylist.add(emr1);
								}
							}
						}
					}
					emr.setSecondarylist(secondarylist);
					}
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	
	}


	public ArrayList<Emr> printEditConsultationNotePriscription(String editid) {

		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		
		
		
		
		String sql = "SELECT id,history,lastmodified,practitionerid,appointmentid FROM emr_priscription where id="+editid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	
	}


	public ArrayList<Emr> getPrintConsultationNoteListPriscription(String practionerId, String clientId,
			String condition, String date) {

		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -7); 
		date = dateFormat.format(cal.getTime());
		
		String todate = date + " 23:59:59";
		
		
		String sql = "SELECT id,history,lastmodified,practitionerid,appointmentid FROM emr_priscription where condition_id = "+condition+" and practitionerid="+practionerId+" "
				+ "and patientid = "+clientId+" and lastmodified between '"+date+"' and '"+todate+"' order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				emr.setAppointmentid(rs.getInt(5));
				
				String heading = getConsultationHeadingData(emr.getAppointmentid()); 
				emr.setHeading(heading);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	
	}


	@Override
	public ArrayList<Master> getodmrprocedureonly() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_newchargetype where procedures = 1";
		
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

}
