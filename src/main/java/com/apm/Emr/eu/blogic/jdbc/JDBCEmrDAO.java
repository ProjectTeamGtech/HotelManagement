package com.apm.Emr.eu.blogic.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.impl.AvalonLogger;
import org.apache.struts2.convention.annotation.Results;

import com.a.a.a.a.a.c;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Emr.eu.entity.MedicalHistory;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.bi.SittingFollowupDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSittingFollowupDAO;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.Result;

public class JDBCEmrDAO extends JDBCBaseDAO implements EmrDAO{
	
	public JDBCEmrDAO(Connection connection){
		this.connection = connection;
		
	}
	DateTimeUtils dateTimeUtils = new DateTimeUtils();

	public ArrayList<Emr> getEmrList(String date,int practitionerid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT clientid,title,firstname,surname,gender,starttime,endtime,apm_available_slot.id,dob,condition_id,treatmentEpisodeId FROM apm_available_slot  inner join apm_patient on ");
		sql.append("apm_patient.id = apm_available_slot.clientid ");
		sql.append("where commencing = '"+date+"' and dna=0 and diaryuserid="+practitionerid+" order by starttime");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				String name = rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
				emr.setPatientName(name);
				emr.setGender(rs.getString(5));
				emr.setStarttime(rs.getString(6));
				emr.setEndTime(rs.getString(7));
				emr.setAppointmentid(rs.getInt(8));
				emr.setDob(rs.getString(9));
				emr.setCommencing(date);
			
				int age = calculateAge(emr.getDob());
				emr.setAge(age);
				
				String conditionName = getConditionName(rs.getString(10));
				emr.setConditionName(conditionName);
				String treatmentEpisodeName = getTreatmentEpisodeName(rs.getString(11));
				emr.setTreatmentEpisodeName(treatmentEpisodeName);
				
				//check appointment completed
				boolean isCompleted = checkAppointmentCompleted(emr.getAppointmentid());
				
				if(!isCompleted){
					list.add(emr);
				}
				
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	private String getTreatmentEpisodeName(String treatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select name from apm_treatment_episode where id="+treatmentEpisodeId+"";
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

	private int calculateAge(String dob) {
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR); 
		
		String temp[] = dob.split("/");
		int dobYear = Integer.parseInt(temp[2]);
		
		int age = currentYear - dobYear;
		
		return age;
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

	public int saveMedicalHistory(Emr emr) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_medical_history(patientid,appointmentid,history,practitionerid,lastmodified) values(?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setInt(2, emr.getAppointmentid());
			preparedStatement.setString(3, emr.getDescription());
			preparedStatement.setInt(4, emr.getPractitionerId());
			preparedStatement.setTimestamp(5, DateTimeUtils.getCurrentDateInSQLCasting());
			
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Emr> getMedicalHistoryList(int patientid,int practitonerid,int pid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,practitionerid,lastmodified  FROM apm_medical_history where (practitionerid="+practitonerid+" or "
				+ "practitionerid = "+pid+") and patientid = "+patientid+" order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				String userName = getUserName(rs.getInt(3));
				emr.setPractitionerName(userName);
				emr.setLastModified(dateTimeUtils.getIndianDateTimeFormat(rs.getString(4)));
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public String getEmrData(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT history FROM apm_medical_history where id = "+selectedid+" ";
		
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

	public int updateMedicalHistory(int selectedid, String medicalhistory) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_medical_history set history=?,lastmodified=? where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, medicalhistory);
			preparedStatement.setTimestamp(2, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setInt(3, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteMedicalHistory(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_medical_history  where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int saveConsultationNote(Emr emr) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_consultation_note(patientid,appointmentid,history,practitionerid,lastmodified,condition_id) values(?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setInt(2, emr.getAppointmentid());
			preparedStatement.setString(3, emr.getDescription());
			preparedStatement.setInt(4, emr.getPractitionerId());
			preparedStatement.setTimestamp(5, DateTimeUtils.getCurrentDateInSQLCasting());
			String conditionId = getConditionId(emr.getAppointmentid());
			preparedStatement.setString(6, conditionId);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getConditionId(int appointmentid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select condition_id from apm_available_slot where id = "+appointmentid+"";
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

	public ArrayList<Emr> getConsultationList(int patientid, int practitonerid,int pid,String firstName,String lastName,int conditionId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,lastmodified,practitionerid,condition_id FROM apm_consultation_note where condition_id = "+conditionId+" and (practitionerid="+practitonerid+" "
				+ "or practitionerid = "+pid+") and patientid = "+patientid+" order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				//String desc = rs.getString(2);				
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	

	private String getUserName(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT firstname,lastname,usertype,clinicowner FROM apm_user where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){				
				int usertype = rs.getInt(3);
				if(usertype == 2){
					result = rs.getString(4);
				}
				else{
					result = rs.getString(1) + " " + rs.getString(2);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return result;
	}

	public String getConsultationNoteText(String selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT history FROM apm_consultation_note where id = "+selectedid+" ";
		
			
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

	public int updateConsultationNote(int selectedid, String editconstext) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_consultation_note set history=?,lastmodified=? where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, editconstext);
			preparedStatement.setTimestamp(2, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setInt(3, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteConsultation(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_consultation_note  where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;

	}

	public ArrayList<Emr> getSocialHistoryList(int patientid,int practitonerid, int pid,int conditionId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT apm_social_history.id,apm_social_history.history,apm_social_history.practitionerid,apm_social_history.lastmodified FROM apm_social_history "
				+ "inner join apm_available_slot where apm_social_history.appointmentid = apm_available_slot.id and "
				+ "apm_available_slot.condition_id = "+conditionId+" and (practitionerid="+practitonerid+" or"
				+ " practitionerid = "+pid+") and patientid = "+patientid+" order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));

				String userName = getUserName(rs.getInt(3));
				
				emr.setPractitionerName(userName);
				emr.setLastModified(dateTimeUtils.changeDateFormatTemplate(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(4))));

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveSocialHistory(Emr emr) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_social_history(patientid,appointmentid,history,practitionerid,lastmodified) values(?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setInt(2, emr.getAppointmentid());
			preparedStatement.setString(3, emr.getDescription());
			preparedStatement.setInt(4, emr.getPractitionerId());
			preparedStatement.setTimestamp(5, DateTimeUtils.getCurrentDateInSQLCasting());
			
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String getSocialData(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT history FROM apm_social_history where id = "+selectedid+" ";
		
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

	public int updateSocialHistory(int selectedid, String socialhistory) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_social_history set history=?,lastmodified=? where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, socialhistory);
			preparedStatement.setTimestamp(2, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setInt(3, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteSocialHistory(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_social_history  where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Emr> getReminderList(int patientid, int practitionerID, int pid) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,lastmodified,practitionerid,alertatetime  FROM apm_reminder where "
				+ "(practitionerid="+practitionerID+" or practitionerid = "+pid+") and  patientid = "+patientid+" order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(rs.getString(3));
				emr.setPractitionerId(rs.getInt(4));
				emr.setAlertDate(rs.getString(5));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveReminder(Emr emr) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_reminder(patientid,appointmentid,history,practitionerid,lastmodified,alertatetime) values(?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,emr.getPatientId());
			preparedStatement.setInt(2, emr.getAppointmentid());
			preparedStatement.setString(3, emr.getDescription());
			preparedStatement.setInt(4, emr.getPractitionerId());
			preparedStatement.setTimestamp(5, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setString(6, emr.getAlertDate()+" "+emr.getHour()+":"+emr.getMin()+"");
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	  public Emr getReminderText(String selectedid) {
		PreparedStatement preparedStatement = null;
		//String result = "";
		Emr emr = new Emr();
		String sql = "SELECT history,alertatetime FROM apm_reminder where id = "+selectedid+" ";
		
			
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				//result = rs.getString(1)+ " " + rs.getString(2);
				emr.setDescription(rs.getString(1));
				emr.setAlertDate(rs.getString(2));
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return emr;
	}

	public int updateReminder(int selectedid, String reminder, String editalert, String edithh, String editmin) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String alertdate = editalert+" "+edithh+":"+editmin+"";
		String sql = "update apm_reminder set history=?, alertatetime=?, lastmodified=? where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reminder);
			preparedStatement.setString(2, alertdate);
			preparedStatement.setTimestamp(3, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setInt(4, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteReminder(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_reminder  where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Emr> getPrescriptionList(int patientid, int practitionerID, int pid, int conditionId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr> list = new ArrayList<Emr>();
		String sql = "SELECT apm_prescription.id,apm_prescription.history,apm_prescription.lastmodified,apm_prescription.practitionerid"
				+ " FROM apm_prescription inner join apm_available_slot where apm_prescription.appointmentid = apm_available_slot.id "
				+ " and apm_available_slot.condition_id = "+conditionId+" and (practitionerid="+practitionerID+""
				+ " or practitionerid = "+pid+") and patientid = "+patientid+" order by id desc ";

		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(dateTimeUtils.changeDateFormatTemplate(dateTimeUtils.changeDateFormatToddmmyyyy(rs.getString(3))));
				emr.setPractitionerId(rs.getInt(4));
				
				String userName = getUserName(rs.getInt(4));				
				emr.setPractitionerName(userName);

				
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public int savePrescription(Emr emr) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_prescription(patientid,appointmentid,history,practitionerid,lastmodified) values(?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,emr.getPatientId());
			preparedStatement.setInt(2, emr.getAppointmentid());
			preparedStatement.setString(3, emr.getDescription());
			preparedStatement.setInt(4, emr.getPractitionerId());
			preparedStatement.setTimestamp(5, DateTimeUtils.getCurrentDateInSQLCasting());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getPrescriptionText(String selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT history FROM apm_prescription where id = "+selectedid+" ";
		
			
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

	public int updatePrescription(int selectedid, String prescription) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_prescription set history=?,lastmodified=? where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, prescription);
			preparedStatement.setTimestamp(2, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setInt(3, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int deletePrescription(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_prescription  where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Emr> getAllergyList(int patientid, int practitionerID, int pid) {
	    PreparedStatement preparedStatement = null;
	    ArrayList<Emr> list = new ArrayList<Emr>();
	    String sql = "select id,history,practitionerid,lastmodified from apm_allergy where (practitionerid = "+practitionerID+" "
	    		+ "or practitionerid = "+pid+") and patientid = "+patientid+" order by id desc";

	    try{
	    	preparedStatement = connection.prepareStatement(sql);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next())
	    	{
	    		Emr emr = new Emr();
	    		emr.setId(rs.getInt(1));
	    		emr.setDescription(rs.getString(2));
	    		emr.setPractitionerId(rs.getInt(3));
	    		emr.setLastModified(rs.getString(4));
	    		
	    		String userName = getUserName(rs.getInt(3));				
				emr.setPractitionerName(userName);
				
				list.add(emr);
	    	}
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return list;
	}

	public int saveAllergy(Emr emr) {
		PreparedStatement preparedStatement = null;
		int result=0;
		String sql = "insert into apm_allergy (patientid,appointmentid,history,practitionerid,lastmodified) values(?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setInt(2, emr.getAppointmentid());
			preparedStatement.setString(3, emr.getDescription());
			preparedStatement.setInt(4, emr.getPractitionerId());
			preparedStatement.setTimestamp(5, DateTimeUtils.getCurrentDateInSQLCasting());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getAllergyText(String selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT history FROM apm_allergy where id = "+selectedid+" ";
					
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

	public int updateAllergy(int selectedid, String allergy) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_allergy set history=?,lastmodified=? where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, allergy);
			preparedStatement.setTimestamp(2, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setInt(3, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteAllergy(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_allergy  where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int saveDocument(Emr emr) {
		PreparedStatement preparedStatement = null;
		int result=0;
		String sql = "insert into apm_document (patientid,appointmentid,history,filename,practitionerid,lastmodified) values(?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setInt(2, emr.getAppointmentid());
			preparedStatement.setString(3, emr.getDescription());
			preparedStatement.setString(4, emr.getFileName());
			preparedStatement.setInt(5, emr.getPractitionerId());
			preparedStatement.setTimestamp(6, DateTimeUtils.getCurrentDateInSQLCasting());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Emr> getDocList(int patientid, int practitionerID, int pid,int conditionId,String filterdoctype) {
		 PreparedStatement preparedStatement = null;
		    ArrayList<Emr> list = new ArrayList<Emr>();
		    /*String sql = "select apm_document.id,apm_document.history,apm_document.filename,apm_document.practitionerid,apm_document.lastmodified "
		    		+ "from apm_document inner join apm_available_slot where apm_document.appointmentid = apm_available_slot.id and apm_available_slot.condition_id = "+conditionId+" and "
		    		+ "(practitionerid = "+practitionerID+" or practitionerid = "+pid+") and patientid = "+patientid+" order by id desc";*/
		    
		    String sql = "";
		    if(filterdoctype.equals("0")){
		    	 sql = "select apm_document.id,apm_document.history,apm_document.filename,apm_document.practitionerid,apm_document.lastmodified,apm_document.doct_type,lnvstid,patientid "
		    		+ "from apm_document where patientid = "+patientid+" and apm_document.filename is not null ";
		    		
		    		/*+ "where condition_id = "+conditionId+" and "
		    		+ "(practitionerid = "+practitionerID+" or practitionerid = "+pid+") and patientid = "+patientid+" order by id desc";*/
		    }else{
		    	 sql = "select apm_document.id,apm_document.history,apm_document.filename,apm_document.practitionerid,apm_document.lastmodified,apm_document.doct_type,lnvstid,patientid "
		    		+ "from apm_document where condition_id = "+conditionId+" and "
		    		+ "(practitionerid = "+practitionerID+" or practitionerid = "+pid+") and patientid = "+patientid+" and doct_type='"+filterdoctype+"' and apm_document.filename is not null  order by id desc";
		    }
		    

		    try{
		    	preparedStatement = connection.prepareStatement(sql);
		    	ResultSet rs = preparedStatement.executeQuery();
		    	int i = 1;
		    	StringBuffer str = new StringBuffer();
		    	while(rs.next())
		    	{
		    		Emr emr = new Emr();
		    		emr.setId(rs.getInt(1));
		    		emr.setDescription(rs.getString(2));
		    		emr.setFileName(rs.getString(3));
		    		emr.setPractitionerId(rs.getInt(4));
		    		emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(5)));
		    		emr.setDoctType(rs.getString(6));
		    		
		    		String userName = getUserName(rs.getInt(4));				
					emr.setPractitionerName(userName);
					
					int invatid = rs.getInt(7);
					emr.setInvstid(invatid);
					
					
					
					if(invatid>0){
						InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
						String invsttype = investigationDAO.getPrintInvsttype(Integer.toString(invatid));
						emr.setInvstFoleName("("+i+") " + invatid + "_" +invsttype);
					}
					
					
					list.add(emr);
		    	}
		    	
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
			return list;
	}

	public ArrayList<Bed> getIpdAdmissionList(String clientid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed>list = new ArrayList<Bed>();
		String sql = "SELECT id,admissiondsate,casualty FROM ipd_addmission_form where clientid = "+clientid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setAdmissiondate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(2)));
				bed.setCasualtyipd(rs.getString(3));
				list.add(bed);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	public Emr getDocumentText(String selectedid) {
		PreparedStatement preparedStatement = null;
		//String result = "";
		Emr emr = new Emr();
		String sql = "SELECT history,filename FROM apm_document where id = "+selectedid+" ";
		
			
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				//result = rs.getString(1)+ " " + rs.getString(2);
				emr.setDescription(rs.getString(1));
				emr.setFileName(rs.getString(2));
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return emr;
	}

	public int updateDocument(int selectedid, String document, String fileName) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_document set history=?, filename=?, lastmodified=? where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, document);
			preparedStatement.setString(2, fileName);
			preparedStatement.setTimestamp(3, DateTimeUtils.getCurrentDateInSQLCasting());
			preparedStatement.setInt(4, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteDocument(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_document where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, selectedid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getReportFieldExist(int apmtid , int clientid) {
		PreparedStatement preparedStatement = null;
		String  result = "";
		String sql = "select aptmtype from apm_available_slot where id = "+apmtid+" and clientId = "+clientid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int apmtType = Integer.parseInt(rs.getString(1));
				result = getReportField(apmtType);				
			}
			if(result == null){
				result = "false";
			}	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	private String getReportField(int aptmtype) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select reportField from apm_appointment_type where id ="+aptmtype+" ";
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

	public String getPractitionerEmail(int pid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select email from apm_user where id = "+pid+" ";
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
	
	public Clinic getsmsCheckConditionList(int pid)
	{
		Clinic clinic = new Clinic();
		PreparedStatement preparedStatement = null;
		String result = "";
		/*String sql = "select smsrelativeDischarge, smsdoctorBedchange , smscheckrelativebedchange, smscheckdoctor ,smspatientCompleted,  smspatientApproved,smscheck from apm_user where id = "+pid+" ";*/
		String sql = "select smscheckdoctor,smscheck from apm_user where id = "+pid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				/*clinic.setSmsrelativeDischarge(rs.getBoolean(1));
				clinic.setSmsdoctorBedchange(rs.getBoolean(2));
				clinic.setSmscheckrelativebedchange(rs.getBoolean(3));*/
				clinic.setSmscheckdoctor(rs.getBoolean(1));
				/*clinic.setSmspatientCompleted(rs.getBoolean(5));
				clinic.setSmspatientApproved(rs.getBoolean(6));*/
				clinic.setSmscheck(rs.getBoolean(2));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return clinic;
	}

	public Emr getSelectedPatientEmr(int apmtid,int practitionerID) {
		PreparedStatement preparedStatement = null;	
		Emr emr = new Emr();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT clientid,title,firstname,surname,gender,starttime,endtime,apm_available_slot.id,dob FROM apm_available_slot  inner join apm_patient on ");
		sql.append("apm_patient.id = apm_available_slot.clientid ");
		sql.append("where apm_available_slot.id = '"+apmtid+"' and dna=0 and diaryuserid="+practitionerID+" order by starttime");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				//Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				String name = rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
				emr.setPatientName(name);
				emr.setGender(rs.getString(5));
				emr.setStarttime(rs.getString(6));
				emr.setEndTime(rs.getString(7));
				emr.setAppointmentid(rs.getInt(8));
				emr.setDob(rs.getString(9));
				//emr.setAppointmentid(apmtid);
			
				int age = calculateAge(emr.getDob());
				emr.setAge(age);				
				
				}			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return emr;
	}

	public ArrayList<Emr> getSelectedMedicalHistoryList(int appointmentid,int patientid, int practitionerID, int pid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,lastmodified,practitionerid  FROM apm_medical_history where (practitionerid="+practitionerID+" "
				+ "or practitionerid = "+pid+") and patientid = "+patientid+" and appointmentid = "+appointmentid+" order by id desc ";
		
		try{			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setDob(rs.getString(3));
				
				String userName = getUserName(rs.getInt(4));				
				emr.setPractitionerName(userName);
				
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Emr> getSelectedConsultationList(int appointmentid, int patientid,	int practitionerID, int pid, String firstName, String lastName) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,lastmodified,practitionerid  FROM apm_consultation_note where (practitionerid="+practitionerID+" "
				+ "or practitionerid = "+pid+") and patientid = "+patientid+" and appointmentid = "+appointmentid+" order by id desc ";
		
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

	public ArrayList<Emr> getSelectedDocList(int appointmentid, int patientid,int practitionerID, int pid) {
		 PreparedStatement preparedStatement = null;
		    ArrayList<Emr> list = new ArrayList<Emr>();
		    String sql = "select id,history,filename,practitionerid,lastmodified from apm_document where (practitionerid = "+practitionerID+" "
		    		+ "or practitionerid = "+pid+") and patientid = "+patientid+" and appointmentid = "+appointmentid+" order by id desc";

		    try{
		    	preparedStatement = connection.prepareStatement(sql);
		    	ResultSet rs = preparedStatement.executeQuery();
		    	while(rs.next())
		    	{
		    		Emr emr = new Emr();
		    		emr.setId(rs.getInt(1));
		    		emr.setDescription(rs.getString(2));
		    		emr.setFileName(rs.getString(3));
		    		emr.setPractitionerId(rs.getInt(4));
		    		emr.setLastModified(rs.getString(5));
		    		
		    		String userName = getUserName(rs.getInt(4));				
					emr.setPractitionerName(userName);
					
					list.add(emr);
		    	}
		    	
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
			return list;
	}

	public ArrayList<Emr> getSelectedSocialHistoryList(int appointmentid, int patientid,int practitionerID, int pid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,lastmodified,practitionerid FROM apm_social_history where (practitionerid="+practitionerID+" "
				+ "or practitionerid = "+pid+") and patientid = "+patientid+" and appointmentid = "+appointmentid+" order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setDob(rs.getString(3));
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Emr> getSelectedReminderList(int appointmentid, int patientid,int practitionerID, int pid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,lastmodified,practitionerid,alertatetime  FROM apm_reminder where (practitionerid="+practitionerID+" "
				+ "or practitionerid = "+pid+") and  patientid = "+patientid+" and appointmentid = "+appointmentid+" order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(rs.getString(3));
				emr.setPractitionerId(rs.getInt(4));
				emr.setAlertDate(rs.getString(5));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Emr> getSelectedPrescriptionList(int appointmentid, int patientid,	int practitionerID, int pid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr> list = new ArrayList<Emr>();
		String sql = "SELECT id,history,lastmodified,practitionerid  FROM apm_prescription where (practitionerid="+practitionerID+" or"
				+ " practitionerid = "+pid+") and patientid = "+patientid+" and appointmentid = "+appointmentid+" order by id desc ";

		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setLastModified(rs.getString(3));
				emr.setPractitionerId(rs.getInt(4));
				
				String userName = getUserName(rs.getInt(4));				
				emr.setPractitionerName(userName);
				
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<Emr> getSelectedAllergyList(int appointmentid, int patientid,	int practitionerID, int pid) {
		PreparedStatement preparedStatement = null;
	    ArrayList<Emr> list = new ArrayList<Emr>();
	    String sql = "select id,history,practitionerid,lastmodified from apm_allergy where (practitionerid = "+practitionerID+" or "
	    		+ "practitionerid = "+pid+") and patientid = "+patientid+" and appointmentid = "+appointmentid+" order by id desc";

	    try{
	    	preparedStatement = connection.prepareStatement(sql);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next())
	    	{
	    		Emr emr = new Emr();
	    		emr.setId(rs.getInt(1));
	    		emr.setDescription(rs.getString(2));
	    		emr.setPractitionerId(rs.getInt(3));
	    		emr.setLastModified(rs.getString(4));
	    		
	    		String userName = getUserName(rs.getInt(3));				
				emr.setPractitionerName(userName);
				
				list.add(emr);
	    	}
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return list;
	}

	public int getConditionOfAmpt(int apmtid) {
		PreparedStatement preparedStatement = null;
		int result =0;
		String sql = "select condition_id from apm_available_slot where id = "+apmtid+" ";
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

	public String getPractitionerName(String selectedPatientId1, int apmtId) {
		PreparedStatement preparedStatement = null;
		String pname = " ";
		String sql = "SELECT diaryuserid FROM apm_available_slot where clientid = "+selectedPatientId1+" and id = "+apmtId+" LIMIT 1";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){			
				pname = getPractitionerFullname(rs.getString(1));				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pname;
	}
	
	private String getPractitionerFullname(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT initial,firstname,lastname FROM apm_user where  id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1)+" " +rs.getString(2)+" " +rs.getString(3);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getConditionName(String conditionId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select name from apm_condition where id="+conditionId+"";
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

	public int getLatestAppointmentId(String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select max(id) from apm_available_slot where clientId = "+clientId+"";
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

	public int getPractitionerId(String clientId, int apmtId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT diaryuserid FROM apm_available_slot where clientid = "+clientId+" and id = "+apmtId+" LIMIT 1";
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

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String clientId,String practiionerId,String condition) {
		PreparedStatement preparedStatement = null;
	    ArrayList<TreatmentEpisode> list = new ArrayList<TreatmentEpisode>();
	    String sql = "select id,name,usedsession,treatmentstatus,outcomes,dschargestatus,dischargedate from apm_treatment_episode where clientid = "+clientId+" order by id desc";
	    int treatmentApmtCount = 1;
	    try{
	    	preparedStatement = connection.prepareStatement(sql);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next())
	    	{
	    		TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(2));
				treatmentEpisode.setUsedSession(rs.getString(3));
				treatmentEpisode.setTrtmentStatus(rs.getInt(4));
				
				
				
				ArrayList<NotAvailableSlot> appointmentList = new ArrayList<NotAvailableSlot>();
				ArrayList<TreatmentEpisode> apmtlist = getAppointmentList(clientId,practiionerId,condition,rs.getInt(1));
				
				
				if(apmtlist.size()==0){
					NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
					notAvailableSlot.setTrtmentStatus(rs.getInt(4));
					notAvailableSlot.setDischrgeOutcomes(rs.getString(5));
					notAvailableSlot.setDischargeStatus(rs.getString(6));
					
					if(rs.getString(7)!=null){
						if(!rs.getString(7).equals("")){
							String datetime = rs.getString(7);
							String temp[] = datetime.split(" ");
							String date = DateTimeUtils.getCommencingDate1(temp[0]);
							notAvailableSlot.setDischargedate(date);
							String time[] = temp[1].split(":");
							String hh = time[0];
							String mm = time[1];
							notAvailableSlot.setHour(hh);
							notAvailableSlot.setMinute(mm);
						}
						
					}
					
					notAvailableSlot.setTreatmentEpisodeName(treatmentEpisode.getTreatmentEpisodeName());
					notAvailableSlot.setId(treatmentEpisode.getId());
					notAvailableSlot.setDiaryUserId(Integer.parseInt(practiionerId));
					notAvailableSlot.setCondition(condition);
					appointmentList.add(notAvailableSlot);
					
					
				}
				
				for(TreatmentEpisode treatmentEpisode1 : apmtlist){
					NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
					notAvailableSlot.setTrtmentStatus(rs.getInt(4));
					notAvailableSlot.setDischrgeOutcomes(rs.getString(5));
					notAvailableSlot.setDischargeStatus(rs.getString(6));
					
					if(rs.getString(7)!=null){
						if(!rs.getString(7).equals("")){
							String datetime = rs.getString(7);
							String temp[] = datetime.split(" ");
							String date = DateTimeUtils.getCommencingDate1(temp[0]);
							notAvailableSlot.setDischargedate(date);
							String time[] = temp[1].split(":");
							String hh = time[0];
							String mm = time[1];
							notAvailableSlot.setHour(hh);
							notAvailableSlot.setMinute(mm);
						}
					}
					
					notAvailableSlot.setTreatmentEpisodeName(treatmentEpisode.getTreatmentEpisodeName());
					notAvailableSlot.setId(treatmentEpisode1.getId());
					notAvailableSlot.setApmttypetext(treatmentEpisode1.getAppointmnetType());
					notAvailableSlot.setCommencing(dateTimeUtils.changeDateFormatTemplate(dateTimeUtils.changeDateFormatToddmmyyyy(treatmentEpisode1.getAppointmentDate())));
					notAvailableSlot.setChkConsultationNote(treatmentEpisode1.getChkConsultationNote());
					notAvailableSlot.setApmtCount(treatmentEpisode1.getApmtCount());
					notAvailableSlot.setDiaryUserId(Integer.parseInt(practiionerId));
					notAvailableSlot.setCondition(condition);
					appointmentList.add(notAvailableSlot);
					
				}
				treatmentEpisode.setTreatmentApmtCount(treatmentApmtCount);
				treatmentEpisode.setAppointmnetList(appointmentList);
				list.add(treatmentEpisode);
				treatmentApmtCount = treatmentApmtCount +1;
	    	}
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return list;
	}

	private ArrayList<TreatmentEpisode> getAppointmentList(String clientId,
			String practiionerId, String condition, int treatmentEpisodeId) {
		int chk = 0;
		int apmtCount = 1;
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode> appointmentList = new ArrayList<TreatmentEpisode>();
	    String sql = "select id,aptmtype,commencing from apm_available_slot where clientId = "+clientId+" and diaryuserid = "+practiionerId+" and condition_id = "+condition+" and treatmentEpisodeId = "+treatmentEpisodeId+" order by id desc";

	    try{
	    	preparedStatement = connection.prepareStatement(sql);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	while(rs.next())
	    	{
	    		TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
	    		treatmentEpisode.setId(rs.getInt(1));
	    		
	    		AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
				AppointmentType appointmentType = appointmentTypeDAO.getAppointment(rs.getInt(2));
	    		
	    		treatmentEpisode.setAppointmnetType(appointmentType.getName());
	    		treatmentEpisode.setAppointmentDate(rs.getString(3));
	    		chk = chkConsulationNote(rs.getInt(1));
	    		treatmentEpisode.setChkConsultationNote(chk);
	    		treatmentEpisode.setApmtCount(apmtCount);
	    		appointmentList.add(treatmentEpisode);
	    		apmtCount = apmtCount+1;
	    	}
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return appointmentList;
	}

	private int chkConsulationNote(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_consultation_note where  appointmentid = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = 1;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<Emr> getMedicalRecordTypeList() {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,type FROM apm_medical_record_type  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setMedicalRecordType(rs.getString(2));
				
				list.add(emr);
			}
			int maxId = getMaxIdOfMedicalRecordType();
			Emr c1 = new Emr();
			c1.setId(maxId+1);
			c1.setMedicalRecordType("Other");
			list.add(c1);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	private int getMaxIdOfMedicalRecordType() {
		PreparedStatement preparedStatement = null;
		int id = 0;
		String sql = "select max(id) from apm_medical_record_type";
		
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


	public int saveMedicalHistoryRecords(Emr emr,String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_medical_history(patientid,history,practitionerid,lastmodified,condition_id,record_type) values(?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setString(2, emr.getMedicalHistoryNotes());
			preparedStatement.setInt(3, emr.getPractitionerId());
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, emr.getCondition_id());
			preparedStatement.setString(6, emr.getMedicalRecordType());
			
			
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Emr> getMedicalHistoryList(int patientid, int practitonerid,
			int pid, String condition,String medicalrecord_type) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,practitionerid,lastmodified,record_type FROM apm_medical_history where (practitionerid="+practitonerid+" or "
				+ "practitionerid = "+pid+") and patientid = "+patientid+" and condition_id = "+condition+" and record_type = '"+medicalrecord_type+"'  order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				String userName = getUserName(rs.getInt(3));
				emr.setPractitionerName(userName);
				emr.setLastModified(rs.getString(4));
				emr.setMedicalRecordType(rs.getString(5));
				
				
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Emr> getMedicalRecordTypeList(int patientid, int practitonerid,
			int pid, String condition) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,type FROM apm_medical_record_type  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setMedicalRecordType(rs.getString(2));
				
				ArrayList<MedicalHistory> medicalRecordList = new ArrayList<MedicalHistory>();
				ArrayList<Emr> medicallist = getMedicalHistoryList(patientid,practitonerid,pid,condition,emr.getMedicalRecordType());
				if(medicallist.size() == 0){
					/*MedicalHistory medicalHistory = new MedicalHistory();
					medicalHistory.setDescription("Not Available");
					medicalRecordList.add(medicalHistory);*/

				}
				else{
				for(Emr emr1 : medicallist){
				
					
					MedicalHistory medicalHistory = new MedicalHistory();
					medicalHistory.setId(emr1.getId());
					medicalHistory.setDescription(emr1.getDescription());
					medicalHistory.setPractitionerName(emr1.getPractitionerName());
					medicalHistory.setLastModified(emr1.getLastModified());
					medicalHistory.setMedicalRecordType(emr1.getMedicalRecordType());
					medicalRecordList.add(medicalHistory);
					
				}
				}
				emr.setMedicalRecordsList(medicalRecordList);
				
				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public int saveNewMedicalRecordType(String newtype) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_medical_record_type(type) values(?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newtype);
			
			
			
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public String getLastMedicalRecordType() {
		PreparedStatement preparedStatement = null;
		String type = null;
		String sql = "SELECT type FROM apm_medical_record_type ORDER BY id DESC LIMIT 1;";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				type = rs.getString(1);
			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}


	public int savePatientDocument(Emr emr,String date,String invstid,String userid) {
		PreparedStatement preparedStatement = null;
		int result=0;
		String sql = "insert into apm_document (patientid,history,filename,practitionerid,lastmodified,doct_type,condition_id,lnvstid,uploadby,packs_imgid,ipdopd,appointmentid) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setString(2, emr.getDescription());
			preparedStatement.setString(3, emr.getFileName());
			preparedStatement.setInt(4, emr.getPractitionerId());
			preparedStatement.setString(5, date);
			preparedStatement.setString(6, emr.getDoctType());
			preparedStatement.setString(7, emr.getCondition_id());
			preparedStatement.setString(8, invstid);
			preparedStatement.setString(9, userid);
			preparedStatement.setString(10, emr.getImgid());
			preparedStatement.setString(11, emr.getIpdopdemr());
			preparedStatement.setString(12, ""+emr.getAppointmentid());
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public int savePatientConsultationNote(Emr emr,String date) {
		PreparedStatement preparedStatement = null;
		int result=0;
		String sql = "insert into apm_consultation_note (patientid,appointmentid,history,practitionerid,lastmodified,condition_id,diagnosis_id,ipdid) values(?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setInt(2, emr.getAppointmentid());
			preparedStatement.setString(3, emr.getConsNote());
			preparedStatement.setInt(4, emr.getPractitionerId());
			preparedStatement.setString(5, date);
			preparedStatement.setString(6, emr.getCondition_id());
			preparedStatement.setString(7, emr.getFinaldiagnosis());
			preparedStatement.setString(8, emr.getEmripdid());
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public int updatePatientConsultationNote(Emr emr, int consulatation_note_id,String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_consultation_note set patientid = ?,history = ?,practitionerid = ?,condition_id = ? where id = "+consulatation_note_id+"";		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,  emr.getPatientId());
			preparedStatement.setString(2, emr.getConsNote());
			preparedStatement.setInt(3, emr.getPractitionerId());
			preparedStatement.setString(4, emr.getCondition_id());
			
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Assessment> getImageDataList(String clientId,
			String practionerId, String condition) {
		PreparedStatement preparedStatement = null;
		ArrayList<Assessment>list = new ArrayList<Assessment>();
		String sql = "SELECT id,imagedata FROM apm_assessment_client_details where practitionerId = '"+practionerId+"' and clientId = "+clientId+" and conditionId = "+condition+" order by id desc";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Assessment assessment = new Assessment();
				assessment.setId(rs.getInt(1));
				assessment.setImageData(rs.getString(2));
				
				list.add(assessment);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public int updateImageDateOfClient(String clientImageDataId,
			String imagedata) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_assessment_client_details set imagedata = ? where id = "+clientImageDataId+"";		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,imagedata);
			
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int saveImageDateOfClient(String imagedata, String practionerId,
			String patientid, String condition) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_assessment_client_details (clientId,practitionerId,imagedata,conditionId) values(?,?,?,?)";

		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,patientid);
			preparedStatement.setString(2,practionerId);
			preparedStatement.setString(3,imagedata);
			preparedStatement.setString(4,condition);
			
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int deleteImageDateOfClient(String clientImageDataId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_assessment_client_details  where id = ?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, clientImageDataId);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public ArrayList<Assessment> getAssessmentList(String clientId,
			String practionerId, String condition) {
		PreparedStatement preparedStatement = null;
		ArrayList<Assessment>list = new ArrayList<Assessment>();
		String sql = "SELECT id,imagedata,templateId,templateName,type,lastmodified FROM apm_assessment_client_details"
				+ " where practitionerId = '"+practionerId+"' and clientId = "+clientId+" and conditionId = "+condition+" and templateId!='null';";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Assessment assessment = new Assessment();
				assessment.setId(rs.getInt(1));
				assessment.setImageData(rs.getString(2));
				assessment.setTemplateId(rs.getString(3));
				assessment.setTemplateName(rs.getString(4));
				assessment.setType(rs.getString(5));
				if(rs.getString(6)!=null){
					String date = DateTimeUtils.getIndianDateTimeFormat(rs.getString(6));
					assessment.setLastmodified(date);
				}else{
					assessment.setLastmodified("");
				}
				
				
				String formtype = getAssesmentFormType(assessment.getTemplateId());
				assessment.setFormtype(formtype);
				
				
				
				list.add(assessment);
			}
			ArrayList<Assessment>combineAssesmentList = getCombineAssesmentList(clientId,practionerId,condition);
			list.addAll(combineAssesmentList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	private ArrayList<Assessment> getCombineAssesmentList(String clientId, String practionerId, String condition) {
		PreparedStatement preparedStatement = null;
		ArrayList<Assessment>list = new ArrayList<Assessment>();
		String sql = "SELECT id,imagedata,templateId,templateName,lastmodified FROM apm_combine_assessment_client_details "
				+ " where practitionerId = '"+practionerId+"' and clientId = "+clientId+" and conditionId = "+condition+"  group by templateid ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Assessment assessment = new Assessment();
				assessment.setId(rs.getInt(1));
				assessment.setImageData(rs.getString(2));
				assessment.setTemplateId(rs.getString(3));
				assessment.setTemplateName(rs.getString(4));
				if(rs.getString(5)!=null){
					String date = DateTimeUtils.getIndianDateTimeFormat(rs.getString(5));
					assessment.setLastmodified(date);
				}else{
					assessment.setLastmodified("");
				}
				
				assessment.setClientId(clientId);
				assessment.setDiaryUserId(practionerId);
				assessment.setConditionId(condition);
				
				String formtype = getAssesmentFormType(assessment.getTemplateId());
				assessment.setFormtype(formtype);
				list.add(assessment);	
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	private String getAssesmentFormType(String templateId) {
		PreparedStatement preparedStatement = null;
		String result = "0";
		String sql = "select formtype from apm_assment_template where id = "+templateId+" ";
		
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


	public int updateMedicalHistoryRecords(Emr emr, String id,String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_medical_history set history=?,lastmodified=?,record_type=? where id = "+id+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emr.getMedicalHistoryNotes());
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, emr.getMedicalRecordType());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
		
		
	}


	public int deleteMedicalHistoryRecords(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_medical_history  where id = "+id+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public int updatePatientDocument(String editDoctId, Emr emr,String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_document set history=?,lastmodified=?,doct_type=?,filename=? where id = "+editDoctId+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emr.getDescription());
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, emr.getDoctType());
			preparedStatement.setString(4, emr.getFileName());
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
		
		
	}


	public int deleteDocuments(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_document  where id = "+id+"";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public int saveVideoClip(Emr emr,String date) {
		PreparedStatement preparedStatement = null;
		int result=0;
		String sql = "insert into apm_emr_video_clips (patientid,filename,practitionerid,lastmodified,condition_id) values(?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setString(2, emr.getFileName());
			preparedStatement.setInt(3, emr.getPractitionerId());
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, emr.getCondition_id());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public ArrayList<Emr> getVideoList(String clientId,
			String practionerId, String condition) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,filename FROM apm_emr_video_clips where practitionerid = '"+practionerId+"' and patientid = "+clientId+" and condition_id = "+condition+" order by id desc";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setVideoFileName(rs.getString(2));
				
				list.add(emr);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public String getLastAppointmentid(String clientId,String practitionerid,String condition) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM apm_available_slot where clientid ='"+clientId+"' and diaryuserid ='"+practitionerid+"'  order by id desc limit 0,1";
		
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


	public ArrayList<Discharge> getDischrageOutcomeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Discharge>list = new ArrayList<Discharge>();
		String sql = "SELECT id,name FROM apm_discharge_outcomes ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Discharge discharge = new Discharge();
				discharge.setId(rs.getInt(1));
				discharge.setName(rs.getString(2));
				
				list.add(discharge);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Discharge> getDischrageStatusList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Discharge>list = new ArrayList<Discharge>();
		String sql = "SELECT id,name FROM apm_discharge_status ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Discharge discharge = new Discharge();
				discharge.setId(rs.getInt(1));
				discharge.setName(rs.getString(2));
				
				list.add(discharge);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public int updateTreatmentEpisodeSischargeStatus(String dischrgeOutcomes,
			String dischargeStatus, int status,String treatmentEpisodeid,String date,String hospcourse,String disadvnotes,String findondischarge,String treatmentgiven,String investigation,String otNotes) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set treatmentstatus=?,outcomes=?,dschargestatus=?,dischargedate=?,hospcourse=?,disadvnotes=?,findingondischarge=?,treatmentgiven=?,investigation=?,otNotes=?  where id = "+treatmentEpisodeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, status);
			preparedStatement.setString(2, dischrgeOutcomes);
			preparedStatement.setString(3, dischargeStatus);
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, hospcourse);
			preparedStatement.setString(6, disadvnotes);
			preparedStatement.setString(7, findondischarge);
			preparedStatement.setString(8, treatmentgiven);
			preparedStatement.setString(9, investigation);
			preparedStatement.setString(10,otNotes);
			result = preparedStatement.executeUpdate();
			
			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateDischargeForm(treatmentEpisodeid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int updateTreatmentEpisodeSischargeStatus(String dischrgeOutcomes,
			String dischargeStatus, int status,String treatmentEpisodeid,String date,String hospcourse,String disadvnotes,String exampleNotes,String findondischarge,String treatmentgiven,String investigation,String otNotes) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set treatmentstatus=?,outcomes=?,dschargestatus=?,dischargedate=?,hospcourse=?,disadvnotes=?,dis_extra_note=?,findingondischarge=?,treatmentgiven=?,investigation=?,otNotes=?  where id = "+treatmentEpisodeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, status);
			preparedStatement.setString(2, dischrgeOutcomes);
			preparedStatement.setString(3, dischargeStatus);
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, hospcourse);
			preparedStatement.setString(6, disadvnotes);
			preparedStatement.setString(7, exampleNotes);
			preparedStatement.setString(8, findondischarge);
			preparedStatement.setString(9, treatmentgiven);
			preparedStatement.setString(10, investigation);
			preparedStatement.setString(11, otNotes);
			result = preparedStatement.executeUpdate();
			
			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateDischargeForm(treatmentEpisodeid);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public Emr getAppointmentidConsultationNote(String apmtid) {
		PreparedStatement preparedStatement = null;
		Emr emr = new Emr();
		String sql = "SELECT id,history FROM apm_consultation_note where appointmentid = "+apmtid+"  order by id desc limit 0,1";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int id = rs.getInt(1);
				emr.setId(rs.getInt(1));
				emr.setConsNote(rs.getString(2));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return emr;
	}


	public int deleteAssesmentForm(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_assessment_client_details where id="+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Master> getmedicineCategoryList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_medicine_categeory  order by name ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int saveOtEquipment(Priscription priscription,String date,int parentid,String clientId,String practitionerID,String conditionid,String apmtid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_client_otequipment(clientid,practitionerid,conditionid,categoryid,mdicineid,mdicinename,dose,frequency,days,lastmodified,code,type,total,parentid,priscdurationtype,notes,apmtid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			if(practitionerID!=null){
				
				if(!practitionerID.equals("")){
					 
					   int practitionerid=Integer.parseInt(practitionerID);
					   UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
					   conditionid=userProfile.getDiciplineName();
				}
			}
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, practitionerID);
			preparedStatement.setString(3, conditionid);
			preparedStatement.setString(4, priscription.getCategoryid());
			preparedStatement.setString(5, priscription.getMdicinenameid());
			preparedStatement.setString(6, priscription.getMdicinenametxt());
			preparedStatement.setString(7, priscription.getPriscdose());
			preparedStatement.setString(8, priscription.getPriscfreq());
			preparedStatement.setString(9, priscription.getPriscdays());
			preparedStatement.setString(10, date);
			preparedStatement.setString(11, priscription.getPrisccode());
			preparedStatement.setString(12, priscription.getPrisctype());
			preparedStatement.setString(13, priscription.getPrisctotal());
			preparedStatement.setInt(14, parentid);
			preparedStatement.setString(15, priscription.getPriscdurationtype());
			preparedStatement.setString(16, priscription.getDosenotes());
			preparedStatement.setString(17, apmtid);
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}




	public int savePriscription(Priscription priscription,String date,int parentid,String clientId,String practitionerID,String conditionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_client_priscription(clientid,practitionerid,conditionid,categoryid,mdicineid,mdicinename,dose,frequency,days,lastmodified,code,type,total,parentid,priscdurationtype,notes,unit,sqno,prisctimeid,prisctimename,priscremark,unitextension,ipdtimeshow,dr_qty,nurse_qty,masterdose,dddose) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			if(practitionerID!=null){
				
				if(!practitionerID.equals("")){
					 
					   int practitionerid=Integer.parseInt(practitionerID);
					   UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
					   conditionid=userProfile.getDiciplineName();
				}
			}
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, practitionerID);
			preparedStatement.setString(3, conditionid);
			preparedStatement.setString(4, priscription.getCategoryid());
			preparedStatement.setString(5, priscription.getMdicinenameid());
			preparedStatement.setString(6, priscription.getMdicinenametxt());
			preparedStatement.setString(7, priscription.getPriscdose());
			preparedStatement.setString(8, priscription.getPriscfreq());
			preparedStatement.setString(9, priscription.getPriscdays());
			preparedStatement.setString(10, date);
			preparedStatement.setString(11, priscription.getPrisccode());
			preparedStatement.setString(12, priscription.getPrisctype());
			preparedStatement.setString(13, priscription.getPrisctotal());
			preparedStatement.setInt(14, parentid);
			preparedStatement.setString(15, priscription.getPriscdurationtype());
			preparedStatement.setString(16, priscription.getDosenotes());
			preparedStatement.setString(17, priscription.getUnit());
			String sqno = priscription.getSrno();
			preparedStatement.setString(18, priscription.getSrno());
			preparedStatement.setString(19, priscription.getPriscriptiontime());
			preparedStatement.setString(20, priscription.getPrisctimename());
			preparedStatement.setString(21, priscription.getPriscindivisualremark());
			preparedStatement.setString(22, priscription.getUnitextension());
			preparedStatement.setString(23, priscription.getDosegiventime());
			preparedStatement.setString(24, priscription.getPriscqty());
			preparedStatement.setString(25, priscription.getPriscqty());
			preparedStatement.setString(26, priscription.getMasterdose());
			
			preparedStatement.setString(27, priscription.getDddose());
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Priscription> getPractitionerList(String clientid,
			String practionerid, String conditionid, String categoryid,
			String medicineid,String date) {
		date = dateTimeUtils.getCommencingDate(date);
		
		String startdate = date;
		String enddate = date + " 23:59:59";
		
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,days,type,total,priscdurationtype,notes FROM apm_client_priscription where clientid = "+clientid+" and practitionerid="+practionerid+"  and conditionid = "+conditionid+" and lastmodified between  '"+startdate+"' and '"+enddate+"' order by id desc  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setDate(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setPriscdurationtype(rs.getString(9));
				priscription.setDosenotes(rs.getString(10));
				
				list.add(priscription);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public Priscription getEditPriscription(String selectedid) {
		PreparedStatement preparedStatement = null;
		Priscription priscription = new Priscription();
		String sql = "SELECT categoryid,mdicineid,dose,frequency,days,priscdurationtype,notes FROM apm_client_priscription where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				priscription.setCategoryid(rs.getString(1));
				priscription.setMdicinenameid(rs.getString(2));
				priscription.setPriscdose(rs.getString(3));
				priscription.setPriscfreq(rs.getString(4));
				priscription.setPriscdays(rs.getString(5));
				priscription.setPriscdurationtype(rs.getString(6));
				priscription.setDosenotes(rs.getString(7));
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return priscription;
	}


	public int editsavePriscription(Priscription priscription,
			String date, String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_client_priscription set categoryid=?,mdicineid=?,mdicinename=?,dose=?,frequency=?,days=?,lastmodified=?,notes=? where id="+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, priscription.getCategoryid());
			preparedStatement.setString(2, priscription.getMdicinenameid());
			preparedStatement.setString(3, priscription.getMdicinenametxt());
			preparedStatement.setString(4, priscription.getPriscdose());
			preparedStatement.setString(5, priscription.getPriscfreq());
			preparedStatement.setString(6, priscription.getPriscdays());
			preparedStatement.setString(7, date);
			preparedStatement.setString(8, priscription.getDosenotes());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int deletePriscriptionData(String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_client_priscription where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Priscription> getAllPriscList(String clientId,
			int practitionerID, String conditionid) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,days,type,total,notes FROM apm_client_priscription where clientid = "+clientId+"  and conditionid = "+conditionid+" order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					String date = DateTimeUtils.getDBDate(rs.getString(2));
					priscription.setDate(date);
				}else{
					priscription.setDate("");
				}
				
				
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setDosenotes(rs.getString(9));
				
				list.add(priscription);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Master> getDosageList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name,regional FROM apm_medicine_dosage ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setRegional(rs.getString(3));
				list.add(master);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int saveParentotequipment(Priscription priscription,
			String ukCurrentDataTime,String sessionadmissionid,String discharge) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		
		String followupdate = "0";
		
		if(!priscription.getFollowupsqty().equals("0")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			
			if(priscription.getFollowupstype().equals("Days")){
				cal.add(Calendar.DATE, Integer.parseInt(priscription.getFollowupsqty())); 
			}else if(priscription.getFollowupstype().equals("Week")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 7;
				cal.add(Calendar.DATE, day); 
			}else if(priscription.getFollowupstype().equals("Month")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 30;
				cal.add(Calendar.DATE, day); 
			}
			
			followupdate = dateFormat.format(cal.getTime());
		}
		
		if(priscription.getPrectionerid()!=null){
			
			if(!priscription.getPrectionerid().equals("")){
				 
				   int practitionerid=Integer.parseInt(priscription.getPrectionerid());
				   UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
				   priscription.setConditionid(userProfile.getDiciplineName());
			}
		}
		
		String sql = "insert into apm_client_parent_otequipment(clientid,practitionerid,conditionid,dosenotes,followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,ipdid,followupdate,discharge,department) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, priscription.getClientId());
			preparedStatement.setString(2, priscription.getPrectionerid());
			preparedStatement.setString(3, priscription.getConditionid());
			preparedStatement.setString(4, priscription.getPriscdosenotes());
			preparedStatement.setString(5, priscription.getFollowupsqty());
			preparedStatement.setString(6, priscription.getFollowupstype());
			preparedStatement.setString(7, priscription.getPriscadvoice());
			preparedStatement.setString(8, priscription.getEnglish());
			preparedStatement.setString(9, priscription.getRegional());
			preparedStatement.setString(10, priscription.getHindi());
			preparedStatement.setString(11, priscription.getPrepay());
			preparedStatement.setString(12, priscription.getPostpay());
			preparedStatement.setString(13, priscription.getOtherpay());
			preparedStatement.setString(14, ukCurrentDataTime);
			preparedStatement.setString(15, sessionadmissionid);
			preparedStatement.setString(16, followupdate);
			preparedStatement.setString(17, discharge);
			
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String department = investigationDAO.getConditionDepartment(priscription.getConditionid());
			preparedStatement.setString(18, department);
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					outoid = resultSet.getInt(1);  
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return outoid;
	}
	
	
	public int saveParentoteq(Priscription priscription,
			String ukCurrentDataTime,String sessionadmissionid,String discharge) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		
		String followupdate = "0";
		
		if(!priscription.getFollowupsqty().equals("0")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			
			if(priscription.getFollowupstype().equals("Days")){
				cal.add(Calendar.DATE, Integer.parseInt(priscription.getFollowupsqty())); 
			}else if(priscription.getFollowupstype().equals("Week")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 7;
				cal.add(Calendar.DATE, day); 
			}else if(priscription.getFollowupstype().equals("Month")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 30;
				cal.add(Calendar.DATE, day); 
			}
			
			followupdate = dateFormat.format(cal.getTime());
		}
		
		if(priscription.getPrectionerid()!=null){
			
			if(!priscription.getPrectionerid().equals("")){
				 
				   int practitionerid=Integer.parseInt(priscription.getPrectionerid());
				   UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
				   priscription.setConditionid(userProfile.getDiciplineName());
			}
		}
		
		String sql = "insert into apm_client_parent_otequipment(clientid,practitionerid,conditionid,dosenotes,followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,ipdid,followupdate,discharge,department) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, priscription.getClientId());
			preparedStatement.setString(2, priscription.getPrectionerid());
			preparedStatement.setString(3, priscription.getConditionid());
			preparedStatement.setString(4, priscription.getPriscdosenotes());
			preparedStatement.setString(5, priscription.getFollowupsqty());
			preparedStatement.setString(6, priscription.getFollowupstype());
			preparedStatement.setString(7, priscription.getPriscadvoice());
			preparedStatement.setString(8, priscription.getEnglish());
			preparedStatement.setString(9, priscription.getRegional());
			preparedStatement.setString(10, priscription.getHindi());
			preparedStatement.setString(11, priscription.getPrepay());
			preparedStatement.setString(12, priscription.getPostpay());
			preparedStatement.setString(13, priscription.getOtherpay());
			preparedStatement.setString(14, ukCurrentDataTime);
			preparedStatement.setString(15, sessionadmissionid);
			preparedStatement.setString(16, followupdate);
			preparedStatement.setString(17, discharge);
			
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String department = investigationDAO.getConditionDepartment(priscription.getConditionid());
			preparedStatement.setString(18, department);
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					outoid = resultSet.getInt(1);  
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return outoid;
	}



	public int saveParentPriscription(Priscription priscription,
			String ukCurrentDataTime,String sessionadmissionid,String discharge,String admission,
			String userid,String location, int default_location, String json_ipdid, String apmtId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		
		String followupdate = "0";
		
		//set department to charges
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		String practionerId = "0";
		String condition = "0";
		String ipdid = "0";
		String opdid = "0";
		
		String clientId = priscription.getClientId();
		//get ipd details
		int bedid = assessmentFormDAO.getIpdBedno(clientId);
		
		if(sessionadmissionid!=null){
			if(!sessionadmissionid.equals("")){
			
				  if(bedid==0){
					   // patient is discharged
					  ipdid= sessionadmissionid;
				  }
			}
		}
		
		if(bedid!=0){
			/*IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao=new JDBCBedDao(connection);*/
			
			ipdid = assessmentFormDAO.getAdmissionid(clientId);
			if(DateTimeUtils.isNull(discharge).equals("1")){
				if(!DateTimeUtils.numberCheck(json_ipdid).equals("0")){
					ipdid = json_ipdid;
				}
			}
			
		}else{
			if(DateTimeUtils.isNull(discharge).equals("1")){
				if(!DateTimeUtils.numberCheck(json_ipdid).equals("0")){
					ipdid = json_ipdid;
				}
			}
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
			if(notAvailableSlot.getDiaryUserId()!=0){
				practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
				opdid = Integer.toString(notAvailableSlot.getId());
			}
			
		}
		
		if(ipdid==null){
			ipdid="0";
		}else if(ipdid.equals("")){
			ipdid="0";
		}
		
		if(ipdid.equals("0")){
			
		}else{
			
		}
		
		if(location==null){
			location="0";
		}else if(location.equals("")){
			location="0";
		}else if(location.equals("ipd")){
			location="0";
		}else if(location.equals("ot")){
			location="2";
		}else{
			location="1";
		}
		
		if(!priscription.getFollowupsqty().equals("0")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			
			if(priscription.getFollowupstype().equals("Days")){
				cal.add(Calendar.DATE, Integer.parseInt(priscription.getFollowupsqty())); 
			}else if(priscription.getFollowupstype().equals("Week")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 7;
				cal.add(Calendar.DATE, day); 
			}else if(priscription.getFollowupstype().equals("Month")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 30;
				cal.add(Calendar.DATE, day); 
			}
			
			followupdate = dateFormat.format(cal.getTime());
		}
		
		if(priscription.getPrectionerid()!=null){
			
			if(!priscription.getPrectionerid().equals("")){
				 
				   int practitionerid=Integer.parseInt(priscription.getPrectionerid());
				   UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
				   priscription.setConditionid(userProfile.getDiciplineName());
			}
		}
		
		String sql = "insert into apm_client_parent_priscription(clientid,practitionerid,conditionid,dosenotes,"
				+ "followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,"
				+ "lastmodified,ipdid,followupdate,discharge,department,opdid,admission,userid,"
				+ "location_s,default_location,fromtreatmentgiven,lastappointmentid,"
				+ "gynicform,cf_apmt_id) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, priscription.getClientId());
			preparedStatement.setString(2, priscription.getPrectionerid());
			preparedStatement.setString(3, priscription.getConditionid());
			preparedStatement.setString(4, priscription.getPriscdosenotes());
			preparedStatement.setString(5, priscription.getFollowupsqty());
			preparedStatement.setString(6, priscription.getFollowupstype());
			preparedStatement.setString(7, priscription.getPriscadvoice());
			preparedStatement.setString(8, priscription.getEnglish());
			preparedStatement.setString(9, priscription.getRegional());
			preparedStatement.setString(10, priscription.getHindi());
			preparedStatement.setString(11, priscription.getPrepay());
			preparedStatement.setString(12, priscription.getPostpay());
			preparedStatement.setString(13, priscription.getOtherpay());
			preparedStatement.setString(14, ukCurrentDataTime);
			preparedStatement.setString(15, ipdid);
			preparedStatement.setString(16, followupdate);
			preparedStatement.setString(17, discharge);
			
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String department = investigationDAO.getConditionDepartment(priscription.getConditionid());
			preparedStatement.setString(18, department);
			preparedStatement.setString(19, opdid);
			preparedStatement.setString(20, admission);
			preparedStatement.setString(21, userid);
			preparedStatement.setString(22, location);
			preparedStatement.setString(23, ""+default_location);
			preparedStatement.setString(24, DateTimeUtils.numberCheck(priscription.getFromtreatmentgivenstatus()));
			preparedStatement.setString(25, priscription.getLastappointmentid());
			preparedStatement.setString(26, priscription.getGynicform());
			preparedStatement.setString(27, apmtId);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					outoid = resultSet.getInt(1);  
					updatePriscAdvoive(priscription.getPriscadvoice(), outoid);
				}
			}
		if(result==1){
			if(followupdate==null){
				followupdate="0";
			}else if(followupdate.equals("")){
				followupdate="0";
			}
			if(!followupdate.equals("0")){
				Client client= new Client();
				client.setFollowupdate(followupdate);
				String x="";
				if(location.equals("0")){
					client.setType("1");
				}else{
					client.setType("2");
					ClientDAO clientDAO = new JDBCClientDAO(connection);
					NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
					if(notAvailableSlot.getDiaryUserId()!=0){
						practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
						opdid = Integer.toString(notAvailableSlot.getId());
					}
				}
				client.setOpdid(opdid);
				client.setIpdid(ipdid);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			      Calendar cal = Calendar.getInstance();
			      String date = dateFormat.format(cal.getTime());
				client.setDate(date);
				client.setClientId(priscription.getClientId());
				client.setPractid(priscription.getPrectionerid());
				client.setLocation("Prescription");
				ClientDAO clientDAO= new JDBCClientDAO(connection);
				clientDAO.savefollowup(client);
				
			}
		}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return outoid;
	}


	public boolean checkParentIdExist(int saveparent) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_client_parent_priscription where id = "+saveparent+" ";
		
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
	
	
	public Priscription getTemplateoteqParentData(int parentid) {
		PreparedStatement preparedStatement = null;
		Priscription priscription = new Priscription();
		String sql = "select dosenotes,followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,clientid,lastmodified from apm_client_parent_priscription_template where id = "+parentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				priscription.setPriscdosenotes(rs.getString(1));
				priscription.setFollowupsqty(rs.getString(2));
				priscription.setFollowupstype(rs.getString(3));
				priscription.setPriscadvoice(rs.getString(4));
				priscription.setEnglish(rs.getString(5));
				priscription.setRegional(rs.getString(6));
				priscription.setHindi(rs.getString(7));
				priscription.setPrepay(rs.getString(8));
				priscription.setPostpay(rs.getString(9));
				priscription.setOtherpay(rs.getString(10));
				priscription.setClientId(rs.getString(11));
				priscription.setLastmodified(rs.getString(12));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return priscription;
	}

	
	
	
	public Priscription getTemplatePriscriptionParentData(int parentid) {
		PreparedStatement preparedStatement = null;
		Priscription priscription = new Priscription();
		String sql = "select dosenotes,followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,clientid,lastmodified from apm_client_parent_priscription_template where id = "+parentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				priscription.setPriscdosenotes(rs.getString(1));
				priscription.setFollowupsqty(rs.getString(2));
				priscription.setFollowupstype(rs.getString(3));
				priscription.setPriscadvoice(rs.getString(4));
				priscription.setEnglish(rs.getString(5));
				priscription.setRegional(rs.getString(6));
				priscription.setHindi(rs.getString(7));
				priscription.setPrepay(rs.getString(8));
				priscription.setPostpay(rs.getString(9));
				priscription.setOtherpay(rs.getString(10));
				priscription.setClientId(rs.getString(11));
				priscription.setLastmodified(rs.getString(12));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return priscription;
	}



	public Priscription getPriscriptionParentData(int parentid) {
		PreparedStatement preparedStatement = null;
		Priscription priscription = new Priscription();
		BedDao bedDao= new JDBCBedDao(connection);
		String sql = "select dosenotes,followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,clientid,lastmodified,practitionerid,ipdid,department,conditionid,location_s,default_location from apm_client_parent_priscription where id = "+parentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				priscription.setPriscdosenotes(rs.getString(1));
				priscription.setFollowupsqty(rs.getString(2));
				priscription.setFollowupstype(rs.getString(3));
				priscription.setPriscadvoice(rs.getString(4));
				priscription.setEnglish(rs.getString(5));
				priscription.setRegional(rs.getString(6));
				priscription.setHindi(rs.getString(7));
				priscription.setPrepay(rs.getString(8));
				priscription.setPostpay(rs.getString(9));
				priscription.setOtherpay(rs.getString(10));
				priscription.setClientId(rs.getString(11));
				priscription.setLastmodified(rs.getString(12));
				priscription.setPrectionerid(rs.getString(13));
				priscription.setIpdid(rs.getString(14));
				priscription.setDepartment(rs.getString(15));
				priscription.setConditionid(rs.getString(16));
				int location=rs.getInt(17);
				priscription.setLocationid(location);
				priscription.setDefault_location(rs.getString(18));
				boolean daycare=bedDao.isDayCare(priscription.getIpdid());
				if(location==0){
					if(daycare){
						priscription.setLocation("Daycare");
					}else{
						priscription.setLocation("IPD");	
					}
					
				}else if(location==2){
					priscription.setLocation("OT");
				}else{
					priscription.setLocation("OPD");
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return priscription;
	}


	public ArrayList<Priscription> getParentPriscList(String clientid,String practionerid,String conditionid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		String sql = "SELECT id,lastmodified FROM apm_client_parent_priscription where clientid = "+clientid+" and practitionerid='"+practionerid+"'  and conditionid = "+conditionid+" and prisc_delete=0 order by id desc ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				int deliverystatus = getNewPriscDeliverStatus(rs.getInt(1));
				priscription.setDeliverystatus(deliverystatus);
				list.add(priscription);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	private int getNewPriscDeliverStatus(int int1) {
		int res =0;
		try {
			String sql ="";
			sql ="select status from apm_parent_prisc where oldparentid='"+int1+"' and status=1 limit 1";
			PreparedStatement preparedStatement =  connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public ArrayList<Priscription> getSelectedTemplateoteqList(String selectedid) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
		String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,days,type,total,priscdurationtype,notes,mdicineid FROM apm_client_oteq_template where parentid = "+selectedid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				
				priscription.setLastmodified(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setPriscdurationtype(rs.getString(9));
				priscription.setDosenotes(rs.getString(10));
				priscription.setMdicinenameid(rs.getString(11));
				
				Priscription priscription2=prescriptionMasterDAO.getPrescriptionDetailsByName(priscription.getMdicinenametxt());
				priscription.setExpiry_date(priscription2.getExpiry_date());
				priscription.setPkg(priscription2.getPkg());
				priscription.setBatch_no(priscription2.getBatch_no());
				priscription.setMfg(priscription2.getMfg());
				
				
				list.add(priscription);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}

	public ArrayList<Priscription> getSelectedTemplatePriscList(String selectedid) {
		PreparedStatement preparedStatement = null;
		
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
		String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,days,type,total,priscdurationtype,notes,mdicineid,tempsqno,tempprisctimeid,tempprisctimename,temppriscremark,tempunitextension,tempunit,temp_nurse_qty,dddose FROM apm_client_priscription_template where parentid = "+selectedid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				
				priscription.setLastmodified(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setPriscdurationtype(rs.getString(9));
				priscription.setDosenotes(rs.getString(10));
				priscription.setMdicinenameid(rs.getString(11));
				
				String uom = getMedicineUom(rs.getString(11));
				priscription.setUom(uom);
				
				
				priscription.setSrno(rs.getString(12));
				priscription.setPriscriptiontime(rs.getString(13));
				priscription.setPrisctimename(rs.getString(14));
				priscription.setPriscindivisualremark(rs.getString(15));
				priscription.setUnitextension(rs.getString(16));
				priscription.setUnit(rs.getString(17));
				//Priscription priscription2=prescriptionMasterDAO.getPrescriptionDetailsByName(priscription.getMdicinenametxt());
				//  21 dec 2017 to set medicine name proper after update name
				Priscription priscription3 = prescriptionMasterDAO.getPrescriptionDetails(rs.getString(11));
				priscription.setExpiry_date(priscription3.getExpiry_date());
				priscription.setPkg(priscription3.getPkg());
				priscription.setBatch_no(priscription3.getBatch_no());
				priscription.setMfg(priscription3.getMfg());
				priscription.setMdicinenametxt(priscription3.getDrug());
				
				
				 EmrDAO emrDAO2= new JDBCEmrDAO(connection); 
				  String medtype="";
				  medtype= emrDAO2.getMedtype(priscription.getMdicinenameid());
				  if(medtype!=null){
					  priscription.setMdicinenametxt(priscription.getMdicinenametxt()+"("+medtype+")");
				  }
				  
				  priscription.setPriscqty(rs.getString(18));
				  priscription.setDddose(rs.getString(19));
				  
				  String regional=getregionalText(rs.getString(5));
				  priscription.setRegional(regional);
				  list.add(priscription);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}

	public ArrayList<Priscription> getPrintPriscList(String selectedid,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection); 
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
		//Priscription priscription1 = getPriscriptionParentData(Integer.parseInt(selectedid));
		
		/*String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,"
				+ "days,type,total,priscdurationtype,notes,mdicineid,sqno,"
				+ "prisctimeid,prisctimename,priscremark,unitextension,unit,"
				+ "ipdtimeshow,dr_qty,masterdose,dddose FROM apm_client_priscription where parentid = "+selectedid+" "
				+ "and isnurseprisc='0' or isnurseprisc is null order by (sqno+0) asc";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,lastmodified,mdicinename,dose,frequency, ");
		sql.append("days,type,total,priscdurationtype,notes,mdicineid,sqno, ");
		sql.append("prisctimeid,prisctimename,priscremark,unitextension,unit, ");
		sql.append("ipdtimeshow,dr_qty,masterdose,dddose FROM apm_client_priscription where parentid = "+selectedid+" ");
		
		if(loginInfo.getOutoprisc()!=1){
			sql.append("and (isnurseprisc='0' or isnurseprisc is null) order by (sqno+0) asc ");
		}
		
		
		boolean prisctimestatus = false;
		boolean priscreamrkstatus = false;
		boolean priscunitstatus = false;
		boolean masterdosestatus= false;
		boolean remarkstatus=false;
		boolean dosenotesstaus = false;
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				
				Product product= inventoryProductDAO.getMedicineProductDetails(rs.getString(11));
				if(product.getGenericname()==null){
					product.setGenericname("");
				}
				priscription.setGenericname(product.getGenericname());
				priscription.setLastmodified(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setPriscdurationtype(rs.getString(9));
				priscription.setDosenotes(rs.getString(10));
				priscription.setMdicinenameid(rs.getString(11));
				String uom = getMedicineUom(rs.getString(11));
				if(uom==null){
					uom="";
				}
				
				//priscription.setRegional(getregionalText(priscription.getPriscdose()));
				Priscription priscription2=prescriptionMasterDAO.getPrescriptionDetailsByName(priscription.getMdicinenametxt());
				priscription.setExpiry_date(priscription2.getExpiry_date());
				priscription.setPkg(priscription2.getPkg());
				priscription.setBatch_no(priscription2.getBatch_no());
				priscription.setMfg(priscription2.getMfg());
				priscription.setComponent(DateTimeUtils.isNull(priscription2.getComponent()));
				
				if(DateTimeUtils.isNull(priscription2.getComponent()).equals("")){
					String component = prescriptionMasterDAO.getComponentFromMedId(rs.getString(11));
					priscription.setComponent(DateTimeUtils.isNull(component));
				}
				
				priscription.setRegional(getregionalText(priscription.getPriscdose()));
				if(rs.getString(14)!=null){
					if(!rs.getString(14).equals("")){
						priscription.setPrisc_time_regional(getPriscTimeRegional(rs.getString(14)));
					}else{
						priscription.setPrisc_time_regional("");
					}
				}else{
					priscription.setPrisc_time_regional("");
				}
				
				String dosage =priscription.getPriscdose();
				int length= dosage.split("-").length;
				if(length<3){
					  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					  dosage =ipdDAO.getAlterNateDose(dosage);
				}
				
				int count=0;
				for(String str:dosage.split("-")){
					
					if(!str.equals("0")){
						
						try {
							int t=Integer.parseInt(str); 
							count=count+t;
						} catch (Exception e) {
							  e.printStackTrace();
						}
					}
					
				}
				if(priscription.getPriscdays()!=null){
					if(priscription.getPriscdays().equals("")){
						priscription.setPriscdays("0");
					}
				}
				count=count*Integer.parseInt(priscription.getPriscdays());
				priscription.setReqqty(count);
				
				priscription.setSrno(rs.getString(12));
				priscription.setPriscriptiontime(rs.getString(13));
				priscription.setPrisctimename(rs.getString(14));
				//  04 May 2018 hide priscription headinng in print if there is no data in coloum
				if(rs.getString(14)!=null){
					if(!rs.getString(14).equals("")){
						prisctimestatus=true;
					}
				}
				if(rs.getString(10)!=null){
					if(!rs.getString(10).equals("")){
						dosenotesstaus=true;
					}
				}
				if(rs.getString(15)!=null){
					if(!rs.getString(1).equals("")){
						priscreamrkstatus=true;
					}
				}
				
				if(rs.getString(17)!=null){
					if(!rs.getString(17).equals("")){
						if(!rs.getString(17).equals("0")){
							priscunitstatus=true;
						}
					}
				}
				priscription.setPrisctimestatus(prisctimestatus);
				priscription.setPriscreamrkstatus(priscreamrkstatus);
				priscription.setPriscunitstatus(priscunitstatus);
				priscription.setDosenotesstaus(dosenotesstaus);
				if(rs.getString(15)==null){
					priscription.setPriscindivisualremark("");
				}else if(rs.getString(15).equals("null")){
					priscription.setPriscindivisualremark("");
				}
				else{
					priscription.setPriscindivisualremark(rs.getString(15));
				}
				
				
				priscription.setUnitextension(rs.getString(16));
				priscription.setUnit(rs.getString(17));
				priscription.setUom(rs.getString(17));
				
				/*if(priscription1.getIpdid()!=null){
					if(!priscription1.getIpdid().equals("") || (!priscription1.getIpdid().equals("0"))){
						priscription.setIsfromipd("1");
					}
				}*/
				priscription.setPriscqty(rs.getString(19));
				priscription.setNewqty(rs.getInt(19));
				if(rs.getString(18)!=null){
					if(!rs.getString(18).equals("")){
						priscription.setIsipdtimeshow("1");
						try {
							String[] test = rs.getString(18).split("-");
							if(test.length>=3){
								priscription.setIpdtimeshow1(test[0]);
								priscription.setIpdtimeshow2(test[1]);
								priscription.setIpdtimeshow3(test[2]);
							}else if(test.length>=2){
								priscription.setIpdtimeshow1(test[0]);
								priscription.setIpdtimeshow2(test[1]);
								priscription.setIpdtimeshow3("");
							}else if(test.length>=1){
								
								priscription.setIpdtimeshow1(test[0]);
								priscription.setIpdtimeshow2("");
								priscription.setIpdtimeshow3("");
							}else{
								priscription.setIsipdtimeshow("0");
								priscription.setIpdtimeshow1("");
								priscription.setIpdtimeshow2("");
								priscription.setIpdtimeshow3("");
							}
						} catch (Exception e) {
							
						}
					}else{
						priscription.setIsipdtimeshow("0");
						priscription.setIpdtimeshow1("");
						priscription.setIpdtimeshow2("");
						priscription.setIpdtimeshow3("");
					}
				}else{
					priscription.setIsipdtimeshow("0");
					priscription.setIpdtimeshow1("");
					priscription.setIpdtimeshow2("");
					priscription.setIpdtimeshow3("");
				}
				priscription.setMasterdose(""+rs.getDouble(20));
				if(rs.getString(21)==null){
					priscription.setDddose("");
				}else{
					priscription.setDddose(rs.getString(21));
				}
				
				
				//  01 aug 2018
				if(rs.getDouble(20)>0.0){
					masterdosestatus = true;
				}
				priscription.setMasterdosestatus(masterdosestatus);
				
				String rnote = "";
				String emrnote = "";
				
				if(loginInfo.getOutoprisc()==1){
					int er =  islangtypetrue("english",selectedid);
					if(er==0){
						int rr =  islangtypetrue("regional",selectedid);
						if(rr==0){
							rnote = getrnote("hindi",priscription.getPriscindivisualremark());
							//emrnote = getrnote("emrhindi",priscription.getPriscindivisualremark());
						}else{
							rnote = getrnote("marathi",priscription.getPriscindivisualremark());
							//emrnote = getrnote("emrmarathi",priscription.getPriscindivisualremark());
						}
					}else{
						rnote = getrnote("eng",priscription.getPriscindivisualremark());
						//emrnote = rnote;
					}
					priscription.setPriscindivisualremark(rnote);
					//priscription.setEmrnote(emrnote);
				}
				if(priscription.getPriscindivisualremark()==null){
					priscription.setPriscindivisualremark("");
				}
				if(priscription.getPriscindivisualremark().equals("null")){
					priscription.setPriscindivisualremark("");
				}
				if(!priscription.getPriscindivisualremark().equals("")){
					remarkstatus=true;
					priscription.setPriscremark(true);
				}
				if(remarkstatus){
					priscription.setPriscremark(true);
				}
				
				
				list.add(priscription);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	private String getPriscTimeRegional(String string) {
		String result="";
		try {
			
			String sql="SELECT prisc_regional from priscription_time where prisc_time ='"+string+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				result=rs.getString(1);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}


	private int islangtypetrue(String col, String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select "+col+" from apm_client_parent_priscription where id = "+selectedid+" ";
		
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


	public ArrayList<Priscription> getSelectedPriscList(String selectedid) {
		PreparedStatement preparedStatement = null;
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection); 
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
		//Priscription priscription1 = getPriscriptionParentData(Integer.parseInt(selectedid));
		
		String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,"
				+ "days,type,total,priscdurationtype,notes,mdicineid,sqno,"
				+ "prisctimeid,prisctimename,priscremark,unitextension,unit,"
				+ "ipdtimeshow,dr_qty,masterdose,dddose FROM apm_client_priscription where parentid = "+selectedid+" "
				+ "and (isnurseprisc='0' or isnurseprisc is null) order by (sqno+0) asc";
		
		
		
		
		boolean prisctimestatus = false;
		boolean priscreamrkstatus = false;
		boolean priscunitstatus = false;
		boolean masterdosestatus= false;
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				
				Product product= inventoryProductDAO.getMedicineProductDetails(rs.getString(11));
				if(product.getGenericname()==null){
					product.setGenericname("");
				}
				priscription.setGenericname(product.getGenericname());
				priscription.setLastmodified(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setPriscdurationtype(rs.getString(9));
				priscription.setDosenotes(rs.getString(10));
				priscription.setMdicinenameid(rs.getString(11));
				String uom = getMedicineUom(rs.getString(11));
				priscription.setUom(uom);
				priscription.setRegional(getregionalText(priscription.getPriscdose()));
				Priscription priscription2=prescriptionMasterDAO.getPrescriptionDetailsByName(priscription.getMdicinenametxt());
				priscription.setExpiry_date(priscription2.getExpiry_date());
				priscription.setPkg(priscription2.getPkg());
				priscription.setBatch_no(priscription2.getBatch_no());
				priscription.setMfg(priscription2.getMfg());
				
				priscription.setRegional(getregionalText(priscription.getPriscdose()));
				
				String dosage =priscription.getPriscdose();
				int length= dosage.split("-").length;
				if(length<3){
					  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					  dosage =ipdDAO.getAlterNateDose(dosage);
				}
				
				int count=0;
				for(String str:dosage.split("-")){
					
					if(!str.equals("0")){
						
						try {
							int t=Integer.parseInt(str); 
							count=count+t;
						} catch (Exception e) {
							  e.printStackTrace();
						}
					}
					
				}
				String prisc_days = DateTimeUtils.numberCheck(priscription.getPriscdays());
				count=count*Integer.parseInt(prisc_days);
				priscription.setPriscdays(prisc_days);
				priscription.setReqqty(count);
				
				priscription.setSrno(rs.getString(12));
				priscription.setPriscriptiontime(rs.getString(13));
				priscription.setPrisctimename(rs.getString(14));
				//  04 May 2018 hide priscription headinng in print if there is no data in coloum
				if(rs.getString(14)!=null){
					if(!rs.getString(14).equals("")){
						prisctimestatus=true;
					}
				}
				if(rs.getString(15)!=null){
					if(!rs.getString(1).equals("")){
						priscreamrkstatus=true;
					}
				}
				
				if(rs.getString(17)!=null){
					if(!rs.getString(17).equals("")){
						priscunitstatus=true;
					}
				}
				priscription.setPrisctimestatus(prisctimestatus);
				priscription.setPriscreamrkstatus(priscreamrkstatus);
				priscription.setPriscunitstatus(priscunitstatus);
				
				priscription.setPriscindivisualremark(rs.getString(15));
				priscription.setUnitextension(rs.getString(16));
				priscription.setUnit(rs.getString(17));
				
				/*if(priscription1.getIpdid()!=null){
					if(!priscription1.getIpdid().equals("") || (!priscription1.getIpdid().equals("0"))){
						priscription.setIsfromipd("1");
					}
				}*/
				priscription.setPriscqty(rs.getString(19));

				if(rs.getString(18)!=null){
					if(!rs.getString(18).equals("")){
						priscription.setIsipdtimeshow("1");
						try {
							String[] test = rs.getString(18).split("-");
							if(test.length>=3){
								priscription.setIpdtimeshow1(test[0]);
								priscription.setIpdtimeshow2(test[1]);
								priscription.setIpdtimeshow3(test[2]);
							}else if(test.length>=2){
								priscription.setIpdtimeshow1(test[0]);
								priscription.setIpdtimeshow2(test[1]);
								priscription.setIpdtimeshow3("");
							}else{
								priscription.setIpdtimeshow1(test[0]);
								priscription.setIpdtimeshow2("");
								priscription.setIpdtimeshow3("");
							}
						} catch (Exception e) {
							
						}
					}else{
						priscription.setIsipdtimeshow("0");
						priscription.setIpdtimeshow1("");
						priscription.setIpdtimeshow2("");
						priscription.setIpdtimeshow3("");
					}
				}else{
					priscription.setIsipdtimeshow("0");
					priscription.setIpdtimeshow1("");
					priscription.setIpdtimeshow2("");
					priscription.setIpdtimeshow3("");
				}
				
				priscription.setMasterdose(""+rs.getDouble(20));
				priscription.setDddose(rs.getString(21));
				
				//  01 aug 2018
				if(rs.getDouble(20)>0.0){
					masterdosestatus = true;
				}
				priscription.setMasterdosestatus(masterdosestatus);
				
				list.add(priscription);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	private String getrnote(String col,String rmarkid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select "+col+" from his_nimai_remark where id = "+rmarkid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result =rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Priscription> getIpdSelectedPriscList(String ipdid,String clientid) {
		PreparedStatement preparedStatement = null;
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection); 
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
		
		StringBuffer buffer= new StringBuffer();
		buffer.append("SELECT apm_client_priscription.id,apm_client_priscription.lastmodified,apm_client_priscription.mdicinename, ");
		buffer.append("apm_client_priscription.dose,apm_client_priscription.frequency,apm_client_priscription.days, ");
		buffer.append("apm_client_priscription.type,apm_client_priscription.total,apm_client_priscription.priscdurationtype, ");
		buffer.append("apm_client_priscription.notes,apm_client_priscription.mdicineid FROM apm_client_priscription ");
		buffer.append("inner join apm_client_parent_priscription on apm_client_parent_priscription.id=apm_client_priscription.parentid ");
		buffer.append("where apm_client_parent_priscription.clientid="+clientid+" and apm_client_parent_priscription.ipdid="+ipdid+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				
				Product product= inventoryProductDAO.getMedicineProductDetails(rs.getString(11));
				if(product.getGenericname()==null){
					product.setGenericname("");
				}
				priscription.setGenericname(product.getGenericname());
				priscription.setLastmodified(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setPriscdurationtype(rs.getString(9));
				priscription.setDosenotes(rs.getString(10));
				priscription.setMdicinenameid(rs.getString(11));
				priscription.setRegional(getregionalText(priscription.getPriscdose()));
				Priscription priscription2=prescriptionMasterDAO.getPrescriptionDetailsByName(priscription.getMdicinenametxt());
				priscription.setExpiry_date(priscription2.getExpiry_date());
				priscription.setPkg(priscription2.getPkg());
				priscription.setBatch_no(priscription2.getBatch_no());
				priscription.setMfg(priscription2.getMfg());
				
				priscription.setRegional(getregionalText(priscription.getPriscdose()));
				
				String dosage =priscription.getPriscdose();
				int length= dosage.split("-").length;
				if(length<3){
					  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					  dosage =ipdDAO.getAlterNateDose(dosage);
				}
				
				int count=0;
				for(String str:dosage.split("-")){
					
					if(!str.equals("0")){
						
						try {
							int t=Integer.parseInt(str); 
							count=count+t;
						} catch (Exception e) {
							  e.printStackTrace();
						}
					}
					
				}
				count=count*Integer.parseInt(priscription.getPriscdays());
				priscription.setReqqty(count);
				
				list.add(priscription);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}


	public String getregionalText(String dose){
		
		String result="";
		try {
			
			//String sql="SELECT regional from apm_medicine_dosage where name='"+dose+"'";
			String sql="SELECT regional from apm_medicine_dosage where name ='"+dose+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				result=rs.getString(1);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteParentPrisc(String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_client_priscription where parentid = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int deleteParet(String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_client_parent_priscription where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Master> getMedicineTypeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_medicine_type ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Master> getInvesigationTypeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_investigation_type where isdeleted!=1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Master> getInvstReportTypeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_invtigation_report_type ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Master> getInvstUnitList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_invstigation_units ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public String getMedicineStrength(String mdicinenameid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT strength FROM apm_medicine_details where id="+mdicinenameid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result =rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public String getRegisteredLocationId() {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM apm_clinic_location where addressType='Registered' ";
		
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


	public ArrayList<Bed> getDischargeList(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed>list = new ArrayList<Bed>();
		String sql = "SELECT id,dischargedate,clientid FROM apm_treatment_episode where clientid = "+clientId+" and treatmentstatus = 1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Bed bed = new Bed();
				int treatmentepisodeid = rs.getInt(1);
				int ipdid = getAdmisionIpdid(treatmentepisodeid);
				bed.setId(ipdid);
				bed.setAdmissiondate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(2)));
				bed.setClientid(rs.getString(3));
				
				list.add(bed);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	private int getAdmisionIpdid(int treatmentepisodeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM ipd_addmission_form where treatmentepisodeid = "+treatmentepisodeid+" ";
		
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


	public String getpriscIpdId(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result ="";
		String sql = "SELECT ipdid FROM apm_client_parent_priscription where id = "+selectedid+" ";
		
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


	public int saveShareEmr(Client client) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		//String sql = "insert into apm_share_emr(fname, lname, email, mob, clientid, diaryuser, condition) value(?,?,?,?,?,?,?) ";
		
		String sql = "insert into apm_share_emr(fname, lname, email, mob, clientid, diaryuser, conditionid) values(?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, client.getFirstName());
			preparedStatement.setString(2, client.getLastName());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setString(4, client.getMobNo());
			preparedStatement.setString(5, client.getClientName());
			preparedStatement.setString(6, client.getDiaryUser());
			preparedStatement.setString(7, client.getTreatmentType());
			
			result = preparedStatement.executeUpdate();
			
			   if(result!=0) {
	            	 
                   ResultSet rs=preparedStatement.getGeneratedKeys();
            	   while(rs.next()) {
            		   result=rs.getInt(1);
            	   }
             }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int saveConfidentialEmr(String diaryUser, String clientname,
			String condition, String confpassd) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_emr_conf(diaryuser, clientid, conditionid, pass, status) values(?,?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, diaryUser);
			preparedStatement.setString(2, clientname);
			preparedStatement.setString(3, condition);
			preparedStatement.setString(4, confpassd);
			preparedStatement.setString(5, "1");
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int checkConfidentialEmr(String clientId, String practionerId,
			String condition) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT status FROM apm_emr_conf where diaryuser="+practionerId+" and clientid="+clientId+" and conditionid="+condition+" ";
		
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


	public boolean chkConfidentialExist(String diaryUser, String clientname,
			String condition) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_emr_conf where diaryuser="+diaryUser+" and clientid="+clientname+" and conditionid="+condition+" ";
		
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


	public String getConfidentialPassword(String diaryUser, String clientname,
			String condition) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT pass FROM apm_emr_conf where diaryuser="+diaryUser+" and clientid="+clientname+" and conditionid="+condition+" ";
		
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


	
	public String getAllConditionsFromApmt(String apmtId) {

		String data="";
		
		try {
			
			String sql="select conditionid from apm_multi_condition where apmtid='"+apmtId+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				  data=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}      
		
		return data;
	}


	public ArrayList<Emr> getDicomImageData() {

		ArrayList<Emr> list=new ArrayList<Emr>();
		
		try {
			
			String sql="SELECT id,imgid,imgname,clientid,invstid FROM his_pacs_data";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
			     
				 Emr emr=new Emr();
				 emr.setId(rs.getInt(1));
				 emr.setImgid(rs.getString(2));
				 InputStream stream=rs.getBlob(3).getBinaryStream();
				 emr.setDicomimageData(stream);
				 emr.setPatientId(rs.getInt(4));
				 emr.setInvstid(rs.getInt(5));
				 list.add(emr);
			}
				
		} catch (Exception e) {

		 e.printStackTrace();
		}
		
		return list;
	}


	public boolean ispackImageExists(String imgid) {

		boolean flag=false;
		try {
			
			String sql="select id from apm_document where packs_imgid='"+imgid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				
				flag=true;
			}
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return flag;
	}


	public String getEmrConditionName(String condition) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_condition where id = "+condition+" ";
		
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


	public boolean checkMdicineExist(String mdicinenameid,
			String editparentpriscid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT id,mdicineid FROM apm_client_priscription where parentid = "+editparentpriscid+" and mdicineid = "+mdicinenameid+" ";
		
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


	public int getSelectedPriscId(String mdicinenameid, String editparentpriscid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_client_priscription where parentid = "+editparentpriscid+" and mdicineid = "+mdicinenameid+" ";
		
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


	public int updateExixstMedicine(Priscription priscription2, int priscid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_client_priscription set dose=?,frequency=?,days=?,code=?,type=?,notes=?,unit=?,sqno=?,prisctimeid=?,prisctimename=?,priscremark=?,dr_qty=?, nurse_qty=? where id = "+priscid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, priscription2.getPriscdose());
			preparedStatement.setString(2, priscription2.getPriscfreq());
			preparedStatement.setString(3, priscription2.getPriscdays());
			preparedStatement.setString(4, priscription2.getPrisccode());
			preparedStatement.setString(5, priscription2.getPrisctype());
			preparedStatement.setString(6, priscription2.getDosenotes());
			preparedStatement.setString(7, priscription2.getUnit());
			preparedStatement.setString(8, priscription2.getSrno());
			preparedStatement.setString(9, priscription2.getPriscriptiontime());
			preparedStatement.setString(10, priscription2.getPrisctimename());
			preparedStatement.setString(11, priscription2.getPriscindivisualremark());
			preparedStatement.setString(12, priscription2.getPriscqty());
			preparedStatement.setString(13, priscription2.getPriscqty());
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList getDbPriscription(String editparentpriscid) {
		PreparedStatement preparedStatement = null;
		ArrayList list = new ArrayList();
		String sql = "Select mdicineid from apm_client_priscription where parentid="+editparentpriscid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	public int delSelectedPriscription(String mdcineid,String parentid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_client_priscription where mdicineid="+mdcineid+" and parentid="+parentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	
	public int saveParentOtEqTemplate(Priscription priscription,
			String currentDataTime, String sessionadmissionid, String discharge) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		
		String followupdate = "0";
		
		if(!priscription.getFollowupsqty().equals("0")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			
			if(priscription.getFollowupstype().equals("Days")){
				cal.add(Calendar.DATE, Integer.parseInt(priscription.getFollowupsqty())); 
			}else if(priscription.getFollowupstype().equals("Week")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 7;
				cal.add(Calendar.DATE, day); 
			}else if(priscription.getFollowupstype().equals("Month")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 30;
				cal.add(Calendar.DATE, day); 
			}
			
			followupdate = dateFormat.format(cal.getTime());
		}

		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		if(priscription.getPrectionerid()!=null){
			if(!priscription.getPrectionerid().equals("")){
				 
				   int practitionerid=Integer.parseInt(priscription.getPrectionerid());
				   UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
				   priscription.setConditionid(userProfile.getDiciplineName());
			}
		}
		
		
		String sql = "insert into apm_client_parent_oteq_template(clientid,practitionerid,conditionid,dosenotes,followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,ipdid,followupdate,discharge,department,templatename) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, priscription.getClientId());
			preparedStatement.setString(2, priscription.getPrectionerid());
			preparedStatement.setString(3, priscription.getConditionid());
			preparedStatement.setString(4, priscription.getPriscdosenotes());
			preparedStatement.setString(5, priscription.getFollowupsqty());
			preparedStatement.setString(6, priscription.getFollowupstype());
			preparedStatement.setString(7, priscription.getPriscadvoice());
			preparedStatement.setString(8, priscription.getEnglish());
			preparedStatement.setString(9, priscription.getRegional());
			preparedStatement.setString(10, priscription.getHindi());
			preparedStatement.setString(11, priscription.getPrepay());
			preparedStatement.setString(12, priscription.getPostpay());
			preparedStatement.setString(13, priscription.getOtherpay());
			preparedStatement.setString(14, currentDataTime);
			preparedStatement.setString(15, sessionadmissionid);
			preparedStatement.setString(16, followupdate);
			preparedStatement.setString(17, discharge);
			
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String department = investigationDAO.getConditionDepartment(priscription.getConditionid());
			preparedStatement.setString(18, department);
			preparedStatement.setString(19, priscription.getTemplatename());
			
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					outoid = resultSet.getInt(1);  
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return outoid;

	}



	public int saveParentPriscriptionTemplate(Priscription priscription,
			String currentDataTime, String sessionadmissionid, String discharge) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		
		String followupdate = "0";
		
		if(!priscription.getFollowupsqty().equals("0")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			
			if(priscription.getFollowupstype().equals("Days")){
				cal.add(Calendar.DATE, Integer.parseInt(priscription.getFollowupsqty())); 
			}else if(priscription.getFollowupstype().equals("Week")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 7;
				cal.add(Calendar.DATE, day); 
			}else if(priscription.getFollowupstype().equals("Month")){
				int day = Integer.parseInt(priscription.getFollowupsqty()) * 30;
				cal.add(Calendar.DATE, day); 
			}
			
			followupdate = dateFormat.format(cal.getTime());
		}

		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		if(priscription.getPrectionerid()!=null){
			if(!priscription.getPrectionerid().equals("")){
				 
				   int practitionerid=Integer.parseInt(priscription.getPrectionerid());
				   UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
				   priscription.setConditionid(userProfile.getDiciplineName());
			}
		}
		
		
		String sql = "insert into apm_client_parent_priscription_template(clientid,practitionerid,conditionid,dosenotes,followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,ipdid,followupdate,discharge,department,templatename) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, priscription.getClientId());
			preparedStatement.setString(2, priscription.getPrectionerid());
			preparedStatement.setString(3, priscription.getConditionid());
			preparedStatement.setString(4, priscription.getPriscdosenotes());
			preparedStatement.setString(5, priscription.getFollowupsqty());
			preparedStatement.setString(6, priscription.getFollowupstype());
			preparedStatement.setString(7, priscription.getPriscadvoice());
			preparedStatement.setString(8, priscription.getEnglish());
			preparedStatement.setString(9, priscription.getRegional());
			preparedStatement.setString(10, priscription.getHindi());
			preparedStatement.setString(11, priscription.getPrepay());
			preparedStatement.setString(12, priscription.getPostpay());
			preparedStatement.setString(13, priscription.getOtherpay());
			preparedStatement.setString(14, currentDataTime);
			preparedStatement.setString(15, sessionadmissionid);
			preparedStatement.setString(16, followupdate);
			preparedStatement.setString(17, discharge);
			
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String department = investigationDAO.getConditionDepartment(priscription.getConditionid());
			preparedStatement.setString(18, department);
			preparedStatement.setString(19, priscription.getTemplatename());
			
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					outoid = resultSet.getInt(1);  
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return outoid;

	}
	
	
	public int saveoteqTemplate(Priscription priscription,
			String currentDataTime, int saveparent, String clientId,
			String prectionerid, String conditionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_client_oteq_template(clientid,practitionerid,conditionid,categoryid,mdicineid,mdicinename,dose,frequency,days,lastmodified,code,type,total,parentid,priscdurationtype,notes) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			if(prectionerid!=null){
				if(!prectionerid.equals("")){
					 
					   int practitionerid=Integer.parseInt(prectionerid);
					   UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
					   conditionid=userProfile.getDiciplineName();
				}
			}
			
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, prectionerid);
			preparedStatement.setString(3, conditionid);
			preparedStatement.setString(4, priscription.getCategoryid());
			preparedStatement.setString(5, priscription.getMdicinenameid());
			preparedStatement.setString(6, priscription.getMdicinenametxt());
			preparedStatement.setString(7, priscription.getPriscdose());
			preparedStatement.setString(8, priscription.getPriscfreq());
			preparedStatement.setString(9, priscription.getPriscdays());
			preparedStatement.setString(10, currentDataTime);
			preparedStatement.setString(11, priscription.getPrisccode());
			preparedStatement.setString(12, priscription.getPrisctype());
			preparedStatement.setString(13, priscription.getPrisctotal());
			preparedStatement.setInt(14, saveparent);
			preparedStatement.setString(15, priscription.getPriscdurationtype());
			preparedStatement.setString(16, priscription.getDosenotes());
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}



	public int savePriscriptionTemplate(Priscription priscription,
			String currentDataTime, int saveparent, String clientId,
			String prectionerid, String conditionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_client_priscription_template(clientid,practitionerid,conditionid,categoryid,mdicineid,mdicinename,dose,frequency,days,lastmodified,code,type,total,parentid,priscdurationtype,notes,tempsqno,tempprisctimeid,tempprisctimename,temppriscremark,tempunitextension,tempunit,tempipdtimeshow,temp_dr_qty,temp_nurse_qty,temp_masterdose,dddose) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			if(prectionerid!=null){
				if(!prectionerid.equals("")){
					 
					   int practitionerid=Integer.parseInt(prectionerid);
					   UserProfile userProfile=userProfileDAO.getUserprofileDetails(practitionerid);
					   conditionid=userProfile.getDiciplineName();
				}
			}
			
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, prectionerid);
			preparedStatement.setString(3, conditionid);
			preparedStatement.setString(4, priscription.getCategoryid());
			preparedStatement.setString(5, priscription.getMdicinenameid());
			preparedStatement.setString(6, priscription.getMdicinenametxt());
			preparedStatement.setString(7, priscription.getPriscdose());
			preparedStatement.setString(8, priscription.getPriscfreq());
			preparedStatement.setString(9, priscription.getPriscdays());
			preparedStatement.setString(10, currentDataTime);
			preparedStatement.setString(11, priscription.getPrisccode());
			preparedStatement.setString(12, priscription.getPrisctype());
			preparedStatement.setString(13, priscription.getPrisctotal());
			preparedStatement.setInt(14, saveparent);
			preparedStatement.setString(15, priscription.getPriscdurationtype());
			preparedStatement.setString(16, priscription.getDosenotes());
			preparedStatement.setString(17, priscription.getSrno());
			preparedStatement.setString(18, priscription.getPriscriptiontime());
			preparedStatement.setString(19, priscription.getPrisctimename());
			preparedStatement.setString(20, priscription.getPriscindivisualremark());
			preparedStatement.setString(21, priscription.getUnitextension());
			preparedStatement.setString(22, priscription.getUnit());
			preparedStatement.setString(23, priscription.getDosegiventime());
			preparedStatement.setString(24, priscription.getPriscqty());
			preparedStatement.setString(25, priscription.getPriscqty());
			preparedStatement.setString(26, priscription.getMasterdose());
			
			preparedStatement.setString(27, priscription.getDddose());
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public ArrayList<Priscription> getoteqTemplateNameList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		String  sql = "SELECT id,templatename FROM apm_client_parent_oteq_template order by id desc ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setTemplatename(rs.getString(2));
				
				list.add(priscription);
				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	
	
	public ArrayList<Priscription> getTemplateNameList(LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		String sql="";
		if(loginInfo.isPrisc_temp_showother()){
			sql = "SELECT id,templatename FROM apm_client_parent_priscription_template where templatename!='' order by id desc ";
		}else{
			if(loginInfo.getJobTitle().equals("Practitioner")){
				UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
				int userid=userProfileDAO.getDiaryUserId(loginInfo.getUserId());
				sql="SELECT id,templatename FROM apm_client_parent_priscription_template where practitionerid ='"+userid+"' and templatename!='' order by id desc ";
			}else{
			  sql = "SELECT id,templatename FROM apm_client_parent_priscription_template where templatename!='' order by id desc ";
			}
		}
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setTemplatename(rs.getString(2));
				
				list.add(priscription);
				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	public String getopdconditionList(String apmtid, String clientname) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select conditionid from apm_opd_condition where apmtid = "+apmtid+" ";
		StringBuffer str = new StringBuffer();
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				str.append(rs.getString(1) + ",");
			}
			
			result = str.toString();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}


	public int deleteAppointmentCondition(String apmtId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_opd_condition where apmtid = "+apmtId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Master> getOtAssetList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM inventory_subcategory where category_id = 1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	public ArrayList<Emr> getOtEqList(String apmtid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT mdicinename,days,notes FROM apm_client_otequipment where apmtid = "+apmtid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setEquipment(rs.getString(1));
				emr.setQuantity(rs.getString(2));
				emr.setDescription(rs.getString(3));
				
				list.add(emr);				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	public int saveRemoteDicomId(String id, String userId,String action) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into his_remote_dicom(selectedid,userid,mid) values(?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, userId);
			preparedStatement.setString(3, action);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	

}


	public int getMultiPacsid(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select multpacsid from dicom_list where id = "+id+" ";
		
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


	public int updatePacsViewer(String id, String userId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update his_pacs_viewer set chg=1 where dicomid="+id+" and userid='"+userId+"' ";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	public int resetAllcgh(String userId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update his_pacs_viewer set chg=0 where  userid='"+userId+"' ";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	public String getMedicineShedule(String mdicinenameid) {
		String medicine_shedule = "0";
		try {
			String sql ="select medicine_shedule from apm_medicine_details where id = "+mdicinenameid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				medicine_shedule = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicine_shedule;
	}


	

	public int updateTreatmentEpisodeDischargeForm(Bed bed2) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set treatmentstatus=?,outcomes=?,dschargestatus=?,dischargedate=?,hospcourse=?,disadvnotes=?,findingondischarge=?,treatmentgiven=?,investigation=?,otNotes=?,dis_extra_note=?,operative_procedure=?, anesthesia=?, surgeon=?, anesthesiologist=?,dischargebyid=?,death_note=?,emergencydetail=?,ondiet=?  where id = "+bed2.getTreatmentepisodeid()+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(bed2.getStatus()));
			preparedStatement.setString(2, bed2.getDischrgeOutcomes());
			preparedStatement.setString(3, bed2.getDischargeStatus());
			preparedStatement.setString(4, bed2.getDischargedate());
			preparedStatement.setString(5, bed2.getHospitalcourse());
			preparedStatement.setString(6, bed2.getDiscadvnotes());
			preparedStatement.setString(7, bed2.getFindondischarge());
			preparedStatement.setString(8, bed2.getTreatmentgiven());
			preparedStatement.setString(9, bed2.getInvestigation());
			preparedStatement.setString(10,bed2.getOtNotes());
			preparedStatement.setString(11, bed2.getExample());
			preparedStatement.setString(12, bed2.getAppointmentText());
			preparedStatement.setString(13, bed2.getAnesthesia());
			preparedStatement.setString(14, bed2.getSurgeon());
			preparedStatement.setString(15, bed2.getAnesthesiologist());
			preparedStatement.setString(16, bed2.getDischargebyid());
			preparedStatement.setString(17, bed2.getDeathnote());
			preparedStatement.setString(18, bed2.getEmergencydetail());
			preparedStatement.setString(19, bed2.getOndiet());
			result = preparedStatement.executeUpdate();
			
			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateDischargeForm(bed2.getTreatmentepisodeid());
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int saveNewMedicine(String genericName, String medicineName) {

		int result=0;
		try {
			
			String sql="insert into apm_medicine_details (categeory,drug,genericname) values (?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, "1");
			ps.setString(2, medicineName);
			ps.setString(3, genericName);
			
			result =ps.executeUpdate();
			if(result>0){
				ResultSet rs =ps.getGeneratedKeys();
				while(rs.next()){
					 result =rs.getInt(1);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public ArrayList<Emr> getDocumentList(String clientid,String ipdopdemr,String fromdate,String todate){
		
		todate = todate + " 23:59:59";
		ArrayList<Emr> list= new ArrayList<Emr>();
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String newdate = dateFormat.format(calendar.getTime());
			String sql="select id,history,filename,practitionerid,lastmodified,doct_type,lnvstid,patientid,uploadby,history from apm_document where patientid="+clientid+" and ipdopd="+ipdopdemr+" and lastmodified between '"+fromdate+"' and '"+todate+"' order by id desc ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int i=0;
			while(rs.next())
	    	{
	    		Emr emr = new Emr();
	    		emr.setId(rs.getInt(1));
	    		emr.setDescription(rs.getString(2));
	    		emr.setFileName(rs.getString(3));
	    		emr.setPractitionerId(rs.getInt(4));
	    		emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(5)));
	    		emr.setDoctType(rs.getString(6));
	    		
	    		String userName = getUserName(rs.getInt(4));				
				emr.setPractitionerName(userName);
				
				int invatid = rs.getInt(7);
				emr.setInvstid(invatid);
				emr.setClientId(rs.getString(8));
				emr.setUploadBy(rs.getString(9));
				emr.setNotes(rs.getString(10));
				
				int delete=0;
				if(rs.getString(5)!=null){
					if(rs.getString(5).split(" ")[0].equals(newdate)){
						delete =1;
					}
				}
				emr.setIsavilablefordelete(delete);
				if(invatid>0){
					InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
					String invsttype = investigationDAO.getPrintInvsttype(Integer.toString(invatid));
					emr.setInvstFoleName("("+i+") " + invatid + "_" +invsttype);
				}
				
				list.add(emr);
				i++;
	    	}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}


	public int updateMedcinename(String ghdnmdnameid, String medicinename) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_medicine_details set drug=? where id = "+ghdnmdnameid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, medicinename);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	public ArrayList<Bed> getGynicFormList(String clientId) {

		ArrayList<Bed> list= new ArrayList<Bed>();
		try {
			
			String sql="SELECT id,commencing,formtype  from ipd_gynic_form where clientid="+clientId+" order by id desc ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				     Bed bed =new Bed();
				     bed.setId(rs.getInt(1));
				     bed.setCommencing(rs.getString(2));
				     bed.setFormtype(rs.getString(3));
				     list.add(bed);
				     
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Priscription> getTemplateSpecializationList(String department) {

		PreparedStatement preparedStatement = null;
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		String  sql = "SELECT id,templatename FROM apm_client_parent_priscription_template where department="+department+" order by id desc ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setTemplatename(rs.getString(2));
				
				list.add(priscription);
				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	

	public ArrayList<Emr> getConsultationListMobApi(String clientid){
		
		PreparedStatement preparedStatement = null;
		ArrayList<Emr>list = new ArrayList<Emr>();
		String sql = "SELECT id,history,lastmodified,practitionerid,condition_id FROM apm_consultation_note where patientid = "+clientid+" order by id desc ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				//String desc = rs.getString(2);				
				emr.setDescription(rs.getString(2));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
				
				String userName = getUserName(rs.getInt(4));
				
				emr.setPractitionerName(userName);

				list.add(emr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}


	public ArrayList<Priscription> getMedicineTimeList() {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		try {
			String sql ="select id, prisc_time,prisc_regional from priscription_time";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setPriscriptiontime(rs.getString(2));
				priscription.setPrisctimename(rs.getString(3));
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updatePrisc(Priscription priscription){
		PreparedStatement ps= null;
		String clientid= priscription.getClientId();
		String priscdose= priscription.getPriscdose();
		String priscdays= priscription.getPriscdays();
		String dosenote= priscription.getDosenotes();
		String unit= priscription.getUnit();
		String medid= priscription.getMdicinenameid();
		String medicine= priscription.getMdicinenametxt();
		String prisctime= priscription.getPriscriptiontime();
		
		//String s[]= medicine.split("-");
		
		int result = 0;
		try{
			String sql="update apm_medicine_details set dose=? ,days=?, note=?, unit=?, presc_time=?,dosegiventime=?,uom=? where id = "+medid+" ";
			ps= connection.prepareStatement(sql);
			ps.setString(1, priscdose);
			ps.setString(2, priscdays);
			ps.setString(3, dosenote);
			ps.setString(4, unit);
			ps.setString(5, prisctime);
			ps.setString(6, priscription.getDosegiventime());
			ps.setString(7, priscription.getUnitextension());
			result= ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}


	public int deleteChildPriscTempData(int saveparent) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_client_priscription_template  where parentid = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+saveparent);
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public String getMedicicneName(String medid) {
		String med="";
		PreparedStatement ps= null;
		try{
			String sql="select drug from apm_medicine_details where id='"+medid+"'";
			ps= connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				med=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return med;
	}


	public int checkifdicom(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select dicom from apm_document where id="+id+" ";
		
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


	public ArrayList<Priscription> getPriscriptionChildData(String parentid, int isforreturn) {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,mdicineid,mdicinename,parentid,dr_qty,dose,days,clientid,deliver_statuss from apm_client_priscription where parentid='"+parentid+"' ");
			if(isforreturn==1){
				buffer.append("and deliver_statuss=1 ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String totalid="0";
			String medid="0";
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setMdicinenameid(rs.getString(2));
				priscription.setParentid(parentid);
				totalid = totalid+","+rs.getInt(1);
				medid = medid+","+rs.getString(2);
				priscription.setTotalid(totalid);
				priscription.setMedid(medid);
				priscription.setPriscqty(rs.getString(5));
				priscription.setDosage(rs.getString(6));
				priscription.setClientId(rs.getString(8));
				if(rs.getString(7)!=null){
					if(!rs.getString(7).equals("")){
						priscription.setPriscdays(""+rs.getInt(7));
					}
				}else{
					priscription.setPriscdays("");
				}
				priscription.setDeliver_statuss(rs.getInt(9));
				priscription.setReq_qtys(rs.getInt(5));
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public int saveNewMedBynurse(Priscription priscription) {
		int res =0;
		try {
			String sql ="insert into apm_client_priscription(clientid, practitionerid, conditionid,mdicineid, mdicinename,lastmodified,parentid,isnurseprisc,nurseuserid) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, priscription.getClientId());
			preparedStatement.setString(2, priscription.getPrectionerid());
			preparedStatement.setString(3, priscription.getConditionid());
			preparedStatement.setString(4, priscription.getMdicinenameid());
			preparedStatement.setString(5, priscription.getMdicinenametxt());
			preparedStatement.setString(6, priscription.getDate());
			preparedStatement.setString(7, priscription.getParentid());
			preparedStatement.setString(8, "1");
			preparedStatement.setString(9, priscription.getUserid());
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public int saveParentPriscNew(Priscription priscription, String remark, String parentid, String default_phar_location, int directtransfer) {
		int result =0;
		try {
			String sql = "insert into apm_parent_prisc(oldparentid, clientid, ipdid, userid, date, remark, status, conditionid,practid,default_location_new,directtransfer) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, parentid);
			preparedStatement.setString(2, priscription.getClientId());
			preparedStatement.setString(3, priscription.getIpdid());
			preparedStatement.setString(4, priscription.getUserid());
			preparedStatement.setString(5, priscription.getDate());
			preparedStatement.setString(6, remark);
			preparedStatement.setString(7, "0");
			preparedStatement.setString(8, priscription.getConditionid());
			preparedStatement.setString(9, priscription.getPrectionerid());
			preparedStatement.setString(10, default_phar_location);
			preparedStatement.setString(11, ""+directtransfer);
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);  
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int saveChildPriscNew(Priscription priscription, String mdrequestqty, String parentid, String childid,
			int newparentid) {
		int res=0;
		try {
			String sql = "insert into apm_child_prisc(parentid, oldchildid, oldparentid, clientid, medicinename, medicineid, qty) values(?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+newparentid);
			preparedStatement.setString(2, childid);
			preparedStatement.setString(3, parentid);
			preparedStatement.setString(4, priscription.getClientId());
			preparedStatement.setString(5, priscription.getMdicinenametxt());
			preparedStatement.setString(6, priscription.getMdicinenameid());
			preparedStatement.setString(7, mdrequestqty);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public ArrayList<Priscription> getRptParentPriscList(String clientId, String prectionerid, String conditionid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		String sql = "SELECT id,lastmodified FROM apm_client_parent_priscription where clientid = "+clientId+" and practitionerid="+prectionerid+"   order by id desc ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				
				
				
				list.add(priscription);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public String getMedtype(String id) {
		PreparedStatement ps= null;
		String x="";
		try {
			String sql= "select type from apm_medicine_details where id='"+id+"'";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				x= rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}


	public String getMedicineUom(String mdicinenameid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT uom FROM apm_medicine_details where id="+mdicinenameid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result =rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public String getrname(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select eng from his_nimai_remark where id ="+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result =rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String getGenerictype(String id) {
		PreparedStatement ps= null;
		String x="";
		try {
			String sql= "select genericname from apm_medicine_details where id='"+id+"'";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				x= rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	private int updatePriscAdvoive(String data, int id){
		int x=0;
		try {
			PreparedStatement ps=connection.prepareStatement(" update apm_client_parent_priscription set advoice='"+data+"'  where id='"+id+"'");
			x=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}


	public ArrayList<Master> getOtroomList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT concat(firstname,' ',lastname) FROM apm_user where jobtitle ='OT' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setName(rs.getString(1));
				
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Master> getOtUserList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String id=getdiscriptionid();
		
		String sql = "SELECT id,concat(firstname,' ',lastname) FROM apm_user where  discription='"+id+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	private String getdiscriptionid() {
		String res="";
		PreparedStatement preparedStatement = null;
String sql = "SELECT id FROM  apm_discipline where discipline='OT'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				res=rs.getString(1);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}


	public boolean checkIsAdmitedPatient(String clientId) {
		boolean res=false;
		PreparedStatement preparedStatement = null;
		try{
			String sql = "select id from ipd_addmission_form where clientid='"+clientId+"' and bedid>0 limit 1";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				res = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}


	public ArrayList<DietaryDetails> dietryList(String clientId, String practioner) {
		ArrayList<DietaryDetails> list=new ArrayList<DietaryDetails>();
		try{
			String query = "select id,practitionerid,wardid, bedid,lastmodified, ipdid,clientid,time from apm_client_parent_dietary where clientid='"+clientId+"' and practitionerid='"+practioner+"'";
			PreparedStatement ps= connection.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				DietaryDetails diet= new DietaryDetails();
				diet.setId(rs.getInt(1));
				
				diet.setLastmodified(DateTimeUtils.getCommencingDate1(rs.getString(5))+" "+rs.getString("time"));
				
				list.add(diet);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Master> nursingcareList(String clientId, String practioner) {
		ArrayList<Master> list=new ArrayList<Master>();
		try{
			String str=" select clientid, conditionid, practitioner_id, ipdid, notes, lastmodified,todate,givento from apm_client_parent_nursing where clientid='"+clientId+"' and practitioner_id='"+practioner+"' ";
			PreparedStatement ps= connection.prepareStatement(str);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				Master master= new  Master();
				 master.setClientid(rs.getString(1));
			     master.setConditionid(rs.getString(2));
			     master.setPractitionerid(rs.getString(3));
			     master.setIpdid(rs.getString(4));
			   
			    master.setDatetime(rs.getString(6).split(" ")[0]+" "+rs.getString(6).split(" ")[1]);
			    master.setTodate(rs.getString(7));
			    master.setGivenTo(rs.getString(8));
			    list.add(master);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Master> vitalList(String clientId, String practioner) {
		String sql=" select clientid, ipdid from his_vital where clientid= '"+clientId+"' group by ipdid order by id desc ";
		ArrayList<Master> list= new  ArrayList<Master>();
		try {
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Master master= new Master();
				master.setClientid(rs.getString(1));
				master.setIpdid(rs.getString(2));
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public Master getVitals(String clientId, String practioner) {
	
		return null;
	}


	public int getPriscTokenId(String cdate) {
		int res=0;
		try {
			String sql ="select count,id from prisc_token_count where date='"+cdate+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1)+1;
				PreparedStatement ps=connection.prepareStatement(" update prisc_token_count set count=? where id='"+rs.getInt(2)+"' ");
				ps.setString(1, ""+count);
				res= ps.executeUpdate();
				res = count;
			}else{
				PreparedStatement ps= connection.prepareStatement("insert into prisc_token_count(count, date) values(?,?)");
				ps.setString(1, ""+1);
				ps.setString(2, cdate);
				res=ps.executeUpdate();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public int updateTokenInPric(int newparentid, int tokenid) {
		int res =0;
		try {
			PreparedStatement ps=connection.prepareStatement(" update apm_parent_prisc set prisc_tokenno=? where id='"+newparentid+"' ");
			ps.setString(1, ""+tokenid);
			res= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public ArrayList<Bed> getNewGynicFormList(String clientId) {


		ArrayList<Bed> list= new ArrayList<Bed>();
		try {
			
			String sql="SELECT id,commencing,formtype  from ipd_new_gynic_form where clientid="+clientId+" order by id desc ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				     Bed bed =new Bed();
				     bed.setId(rs.getInt(1));
				     bed.setCommencing(rs.getString(2));
				     bed.setFormtype(rs.getString(3));
				     list.add(bed);
				     
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	
	}


	public int getCheckUserVideoConfernce(String apmtId) {
		int res =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select opd_user_vid_access from apm_available_slot ");
			buffer.append("inner join apm_user on apm_user.userid = apm_available_slot.added_by ");
			buffer.append("where apm_available_slot.id='"+apmtId+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public ArrayList<String> getInvestigationOPDGiven(String apmtid) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select invsttype from apm_client_parent_investigation ");
			buffer.append("inner join apm_client_investigation on apm_client_investigation.parentid = apm_client_parent_investigation.id ");
			buffer.append("where opdid='"+apmtid+"' group by invsttype");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				arrayList.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public ArrayList<Priscription> getPrescriptionListOPDGiven(String apmtid) {
		ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select mdicinename,dose,dr_qty from apm_client_parent_priscription ");
			buffer.append("inner join apm_client_priscription on apm_client_priscription.parentid = apm_client_parent_priscription.id ");
			buffer.append("where opdid='"+apmtid+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setMdicinenametxt(rs.getString(1));
				priscription.setDosage(rs.getString(2));
				priscription.setDr_qty(rs.getString(3));
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public ArrayList<Emr> getOpdEmrLogList(String clientId) {
		ArrayList<Emr> arrayList = new ArrayList<Emr>();
		try {
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			StringBuffer buffer = new StringBuffer();
			buffer.append("select date,appointmentid,ipdid from emr_activity_log where clientid="+clientId+" order by date desc ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Emr emr=new Emr();
				emr.setCommencing(DateTimeUtils.getInvoiceCommencingDate(rs.getString(1)));
				if(rs.getInt(3)>0){
					emr.setHeading("ipd");
					emr.setNewabrivationid(ipdDAO.getIpdAbrivationIds(rs.getInt(3)));
					
				}else if(rs.getInt(2)>0){
					emr.setHeading("opd");
					String opdabbr=getOpdAbbrevationId(rs.getInt(2));
					emr.setNewabrivationid(DateTimeUtils.isNull(opdabbr));
				}
				arrayList.add(emr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public String getOpdAbbrevationId(int int1) {
		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("select opdabbrevationid from apm_available_slot where id =" + int1 + "");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public ArrayList<Emr> getIpdEmrLogList(String clientId) {
		ArrayList<Emr> arrayList = new ArrayList<Emr>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select lastmodified from apm_consultation_note where appointmentid=0 group by lastmodified order by lastmodified desc ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Emr emr=new Emr();
				String tmp[]=rs.getString(1).split(" ");
				String datecomm=DateTimeUtils.getCommencingDate1(tmp[0]);
				emr.setCommencing(datecomm+" "+tmp[1]);
				arrayList.add(emr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public int saveEntryToEMR(Emr emr, String ukCurrentDataTime, String tablename) {
		PreparedStatement preparedStatement = null;
		int result=0;
		String sql = "insert into "+tablename+" (patientid,appointmentid,history,practitionerid,lastmodified,condition_id,diagnosis_id,ipdid) values(?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, emr.getPatientId());
			preparedStatement.setInt(2, emr.getAppointmentid());
			preparedStatement.setString(3, emr.getConsNote());
			preparedStatement.setInt(4, emr.getPractitionerId());
			preparedStatement.setString(5, ukCurrentDataTime);
			preparedStatement.setString(6, emr.getCondition_id());
			preparedStatement.setString(7, emr.getFinaldiagnosis());
			preparedStatement.setString(8, emr.getEmripdid());
			result = preparedStatement.executeUpdate();
			String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			boolean flag= checkDateExistonEmrLog(date,emr.getPatientId());
			if(!flag){
				int res=insertIntoEmrActivity(date,emr.getPatientId(),emr.getAppointmentid(),emr.getEmripdid());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	public int insertIntoEmrActivity(String date, int patientId, int appintmentid, String ipdid) {
		PreparedStatement preparedStatement = null;
		int result=0;
		String sql = "insert into emr_activity_log (clientid,date,appointmentid,ipdid) values(?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, patientId);
			preparedStatement.setString(2, date);
			preparedStatement.setInt(3, appintmentid);
			preparedStatement.setString(4, ipdid);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public boolean checkDateExistonEmrLog(String date, int clientid) {
		boolean flag=false;
		try {
			String sql="select * from emr_activity_log where date='"+date+"' and clientid="+clientid+" ";
			PreparedStatement preparedStatement=null;
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();	
			if(rs.next()){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public String getsecondarydrlist(String clientId, String apmtId) {
		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("select seconadary_dr from opd_secondary_dr where opdappointment =" + apmtId + " and clientid="+clientId+"");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	@Override
	public ArrayList<NotAvailableSlot> getsittingfollowuplist(String clientid) {
		// TODO Auto-generated method stub
		
		ArrayList<NotAvailableSlot>list=new ArrayList<NotAvailableSlot>();
		PreparedStatement pmt=null;
		 
		try {
			
			String sql="select id, dept_id, sitting, followup, followup_date, remark, user_id, date_time, proceduremaster_id, procedure_id,deptopd_id,sitting_num,diagnosis from sitting_followuplist where patient_id="+clientid+"";
            pmt=connection.prepareStatement(sql);
			
			ResultSet rs=pmt.executeQuery();
			
			SittingFollowupDAO sittingFollowupDAO= new JDBCSittingFollowupDAO(connection);
			
			while(rs.next()){
				
				String deptname= sittingFollowupDAO.departmentid(rs.getString(2) );
				
				String sittingname=sittingname(rs.getString(3));
				String proceduremastername=procedurename(rs.getString(9));
				String procedurname=procedurenamelist(rs.getString(10));
				//String followupdone=followupstatus(rs.getString(11));
				
				NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
				
				
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setDepartmentname(deptname);
				notAvailableSlot.setSitting(sittingname);
				notAvailableSlot.setSittingFollowup(rs.getBoolean(4));
				notAvailableSlot.setDate(rs.getString(5));
				notAvailableSlot.setRemark(rs.getString(6));
				notAvailableSlot.setUser_id(rs.getString(7));
				notAvailableSlot.setDate_time(rs.getString(8));
				notAvailableSlot.setAll_procedure(proceduremastername);
				notAvailableSlot.setProcedure_list(procedurname);
				notAvailableSlot.setDeptOpdId(rs.getInt(11));
				notAvailableSlot.setSittingnum(rs.getString(12));
				notAvailableSlot.setDiagnosisarea(rs.getString(13));
				
				
				list.add(notAvailableSlot);
				
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}


    private String procedurenamelist(String string) {
		// TODO Auto-generated method stub
		
		 PreparedStatement pmt=null;
	        
			String name="";
			
			try {
				
				String sql="select name from apm_appointment_type where id="+string+"";
				pmt=connection.prepareStatement(sql);
				ResultSet rs=pmt.executeQuery();
				
				while(rs.next()){
					
					name=rs.getString(1);	
					
					}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return name;
	}


	private String procedurename(String string) {
		// TODO Auto-generated method stub
		
        PreparedStatement pmt=null;
        
		String procedurename="";
		
		try {
			
			String sql="select name from apm_newchargetype where id='"+string+"'";
			pmt=connection.prepareStatement(sql);
			ResultSet rs=pmt.executeQuery();
			
			while(rs.next()){
				
				procedurename=rs.getString(1);	
				
				}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return procedurename;
	}


	private String sittingname(String string) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
        
		String sittingname="";
		
		try {
			
			   String sql="select sittingFollowup from sittingfollowup_master where id='"+string+"'";
			   pmt=connection.prepareStatement(sql);
			
			   ResultSet rs=pmt.executeQuery();
			
			   while(rs.next()){
				
				sittingname=rs.getString(1);
				
				}
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return sittingname;
	}


	@Override
	public String followupstatus(int deptOpdId) {
		// TODO Auto-generated method stub
		
		 PreparedStatement pmt=null;
	     String followupstatus="";
			
			try {
				
				String sql="select followupdone from department_opd where id="+deptOpdId+"";
				pmt=connection.prepareStatement(sql);
				ResultSet rs=pmt.executeQuery();
				
				while(rs.next()){
					
					followupstatus=rs.getString(1);	
					
					}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return followupstatus;
		
	}


	@Override
	public String Departmentopdid(String clientid, String deptid) {
		// TODO Auto-generated method stub
		
		    PreparedStatement pmt=null;
		    String deptOpdid="0";
				
		try {
				
			String sql="select id from department_opd where clientId="+clientid+" and dept="+deptid+" order by id DESC limit 0,1";
		    pmt=connection.prepareStatement(sql);
			ResultSet rs=pmt.executeQuery();
					
			while(rs.next()){
				deptOpdid=rs.getString(1);	
			}
					
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			return deptOpdid;
	}


	@Override
	public String getAppointmentid(String clientid,String deptid) {
		// TODO Auto-generated method stub
		
        PreparedStatement pmt=null;
	    String apmtId="0";
			
	try {
		//@commented beacuse vspm 	
		//String sql="select id from apm_available_slot where deptOpdId="+deptOpdid+"  ";
		String sql="select id from apm_available_slot where clientId="+clientid+" order by id desc limit 0,1 ";
	    pmt=connection.prepareStatement(sql);
		ResultSet rs=pmt.executeQuery();
				
		while(rs.next()){
			apmtId=rs.getString(1);	
		}
				
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return apmtId;
		}
	public ArrayList<Priscription> getPrintPriscListWIthDate(String parentID,LoginInfo loginInfo,String fromdate,String todate,String clientId) {
		PreparedStatement preparedStatement = null;
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection); 
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
		//Priscription priscription1 = getPriscriptionParentData(Integer.parseInt(selectedid));
		
		/*String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,"
				+ "days,type,total,priscdurationtype,notes,mdicineid,sqno,"
				+ "prisctimeid,prisctimename,priscremark,unitextension,unit,"
				+ "ipdtimeshow,dr_qty,masterdose,dddose FROM apm_client_priscription where parentid = "+selectedid+" "
				+ "and isnurseprisc='0' or isnurseprisc is null order by (sqno+0) asc";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,lastmodified,mdicinename,dose,frequency, ");
		sql.append("days,type,total,priscdurationtype,notes,mdicineid,sqno, ");
		sql.append("prisctimeid,prisctimename,priscremark,unitextension,unit, ");
		sql.append("ipdtimeshow,dr_qty,masterdose,dddose FROM apm_client_priscription where clientid = "+clientId+" ");
		sql.append(" and lastmodified between '"+fromdate+"' and '"+todate+"'");
		
		if(loginInfo.getOutoprisc()!=1){
			sql.append("and (isnurseprisc='0' or isnurseprisc is null) order by (sqno+0) asc ");
		}
		
		
		boolean prisctimestatus = false;
		boolean priscreamrkstatus = false;
		boolean priscunitstatus = false;
		boolean masterdosestatus= false;
		boolean remarkstatus=false;
		boolean dosenotesstaus = false;
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				if(rs.getString(2)!=null){
					priscription.setDate(DateTimeUtils.getDBDate(rs.getString(2)));
				}else{
					priscription.setDate("");
				}
				
				Product product= inventoryProductDAO.getMedicineProductDetails(rs.getString(11));
				if(product.getGenericname()==null){
					product.setGenericname("");
				}
				priscription.setGenericname(product.getGenericname());
				priscription.setLastmodified(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setPriscdose(rs.getString(4));
				priscription.setPriscfreq(rs.getString(5));
				priscription.setPriscdays(rs.getString(6));
				priscription.setPrisctype(rs.getString(7));
				priscription.setPrisctotal(rs.getString(8));
				priscription.setPriscdurationtype(rs.getString(9));
				priscription.setDosenotes(rs.getString(10));
				priscription.setMdicinenameid(rs.getString(11));
				String uom = getMedicineUom(rs.getString(11));
				if(uom==null){
					uom="";
				}
				
				//priscription.setRegional(getregionalText(priscription.getPriscdose()));
				Priscription priscription2=prescriptionMasterDAO.getPrescriptionDetailsByName(priscription.getMdicinenametxt());
				priscription.setExpiry_date(priscription2.getExpiry_date());
				priscription.setPkg(priscription2.getPkg());
				priscription.setBatch_no(priscription2.getBatch_no());
				priscription.setMfg(priscription2.getMfg());
				priscription.setComponent(DateTimeUtils.isNull(priscription2.getComponent()));
				
				if(DateTimeUtils.isNull(priscription2.getComponent()).equals("")){
					String component = prescriptionMasterDAO.getComponentFromMedId(rs.getString(11));
					priscription.setComponent(DateTimeUtils.isNull(component));
				}
				
				priscription.setRegional(getregionalText(priscription.getPriscdose()));
				if(rs.getString(14)!=null){
					if(!rs.getString(14).equals("")){
						priscription.setPrisc_time_regional(getPriscTimeRegional(rs.getString(14)));
					}else{
						priscription.setPrisc_time_regional("");
					}
				}else{
					priscription.setPrisc_time_regional("");
				}
				
				String dosage =priscription.getPriscdose();
				int length= dosage.split("-").length;
				if(length<3){
					  IpdDAO ipdDAO = new JDBCIpdDAO(connection);
					  dosage =ipdDAO.getAlterNateDose(dosage);
				}
				
				int count=0;
				for(String str:dosage.split("-")){
					
					if(!str.equals("0")){
						
						try {
							int t=Integer.parseInt(str); 
							count=count+t;
						} catch (Exception e) {
							  e.printStackTrace();
						}
					}
					
				}
				if(priscription.getPriscdays()!=null){
					if(priscription.getPriscdays().equals("")){
						priscription.setPriscdays("0");
					}
				}
				count=count*Integer.parseInt(priscription.getPriscdays());
				priscription.setReqqty(count);
				
				priscription.setSrno(rs.getString(12));
				priscription.setPriscriptiontime(rs.getString(13));
				priscription.setPrisctimename(rs.getString(14));
				//  04 May 2018 hide priscription headinng in print if there is no data in coloum
				if(rs.getString(14)!=null){
					if(!rs.getString(14).equals("")){
						prisctimestatus=true;
					}
				}
				if(rs.getString(10)!=null){
					if(!rs.getString(10).equals("")){
						dosenotesstaus=true;
					}
				}
				if(rs.getString(15)!=null){
					if(!rs.getString(1).equals("")){
						priscreamrkstatus=true;
					}
				}
				
				if(rs.getString(17)!=null){
					if(!rs.getString(17).equals("")){
						if(!rs.getString(17).equals("0")){
							priscunitstatus=true;
						}
					}
				}
				priscription.setPrisctimestatus(prisctimestatus);
				priscription.setPriscreamrkstatus(priscreamrkstatus);
				priscription.setPriscunitstatus(priscunitstatus);
				priscription.setDosenotesstaus(dosenotesstaus);
				if(rs.getString(15)==null){
					priscription.setPriscindivisualremark("");
				}else if(rs.getString(15).equals("null")){
					priscription.setPriscindivisualremark("");
				}
				else{
					priscription.setPriscindivisualremark(rs.getString(15));
				}
				
				
				priscription.setUnitextension(rs.getString(16));
				priscription.setUnit(rs.getString(17));
				priscription.setUom(rs.getString(17));
				priscription.setStrength(rs.getString(17)+" "+rs.getString(16));
				
				/*if(priscription1.getIpdid()!=null){
					if(!priscription1.getIpdid().equals("") || (!priscription1.getIpdid().equals("0"))){
						priscription.setIsfromipd("1");
					}
				}*/
				priscription.setPriscqty(rs.getString(19));
				priscription.setNewqty(rs.getInt(19));
				if(rs.getString(18)!=null){
					if(!rs.getString(18).equals("")){
						priscription.setIsipdtimeshow("1");
						try {
							String[] test = rs.getString(18).split("-");
							if(test.length>=3){
								priscription.setIpdtimeshow1(test[0]);
								priscription.setIpdtimeshow2(test[1]);
								priscription.setIpdtimeshow3(test[2]);
							}else if(test.length>=2){
								priscription.setIpdtimeshow1(test[0]);
								priscription.setIpdtimeshow2(test[1]);
								priscription.setIpdtimeshow3("");
							}else if(test.length>=1){
								
								priscription.setIpdtimeshow1(test[0]);
								priscription.setIpdtimeshow2("");
								priscription.setIpdtimeshow3("");
							}else{
								priscription.setIsipdtimeshow("0");
								priscription.setIpdtimeshow1("");
								priscription.setIpdtimeshow2("");
								priscription.setIpdtimeshow3("");
							}
						} catch (Exception e) {
							
						}
					}else{
						priscription.setIsipdtimeshow("0");
						priscription.setIpdtimeshow1("");
						priscription.setIpdtimeshow2("");
						priscription.setIpdtimeshow3("");
					}
				}else{
					priscription.setIsipdtimeshow("0");
					priscription.setIpdtimeshow1("");
					priscription.setIpdtimeshow2("");
					priscription.setIpdtimeshow3("");
				}
				priscription.setMasterdose(""+rs.getDouble(20));
				if(rs.getString(21)==null){
					priscription.setDddose("");
				}else{
					priscription.setDddose(rs.getString(21));
				}
				
				
				//  01 aug 2018
				if(rs.getDouble(20)>0.0){
					masterdosestatus = true;
				}
				priscription.setMasterdosestatus(masterdosestatus);
				
				String rnote = "";
				String emrnote = "";
				
				if(loginInfo.getOutoprisc()==1){
					int er =  islangtypetrue("english",parentID);
					if(er==0){
						int rr =  islangtypetrue("regional",parentID);
						if(rr==0){
							rnote = getrnote("hindi",priscription.getPriscindivisualremark());
							//emrnote = getrnote("emrhindi",priscription.getPriscindivisualremark());
						}else{
							rnote = getrnote("marathi",priscription.getPriscindivisualremark());
							//emrnote = getrnote("emrmarathi",priscription.getPriscindivisualremark());
						}
					}else{
						rnote = getrnote("eng",priscription.getPriscindivisualremark());
						//emrnote = rnote;
					}
					priscription.setPriscindivisualremark(rnote);
					//priscription.setEmrnote(emrnote);
				}
				if(priscription.getPriscindivisualremark()==null){
					priscription.setPriscindivisualremark("");
				}
				if(priscription.getPriscindivisualremark().equals("null")){
					priscription.setPriscindivisualremark("");
				}
				if(!priscription.getPriscindivisualremark().equals("")){
					remarkstatus=true;
					priscription.setPriscremark(true);
				}
				if(remarkstatus){
					priscription.setPriscremark(true);
				}
				
				
				list.add(priscription);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}


	@Override
	public String getBamslastmodifiedDate(int saveparent) {
		 PreparedStatement pmt=null;
		  String lastmodifieddate="0";
				
		try {
				
			String sql="select lastmodified from apm_client_parent_priscription where id="+saveparent+"  ";
		    pmt=connection.prepareStatement(sql);
			ResultSet rs=pmt.executeQuery();
					
			while(rs.next()){
				lastmodifieddate=rs.getString(1);	
			}
					
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			return lastmodifieddate;
			}


	@Override
	public ArrayList<NotAvailableSlot> getipdsittingfollowuplist(String clientid) {
		ArrayList<NotAvailableSlot>list=new ArrayList<NotAvailableSlot>();
		PreparedStatement pmt=null;
		 
		try {
			
			String sql="select id, dept_id, sitting, followup, followup_date, remark, user_id, date_time, proceduremaster_id, procedure_id,deptopd_id,sitting_num,diagnosis,hospitalid,ward_bed from ipd_sitting_followuplist where patient_id="+clientid+"";
            pmt=connection.prepareStatement(sql);
			
			ResultSet rs=pmt.executeQuery();
			
			SittingFollowupDAO sittingFollowupDAO= new JDBCSittingFollowupDAO(connection);
			
			while(rs.next()){
				
				String deptname= sittingFollowupDAO.departmentid(rs.getString(2) );
				
				//String sittingname=sittingname(rs.getString(3));
				String ipdsessioname=sessionname(rs.getString(3));
				String proceduremastername=procedurename(rs.getString(9));
				String procedurname=procedurenamelist(rs.getString(10));
				String hospitalname=hospitalname(rs.getString(14));
				//String followupdone=followupstatus(rs.getString(11));
				
				NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
				
				
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setDepartmentname(deptname);
				notAvailableSlot.setSitting(ipdsessioname);
				notAvailableSlot.setSittingFollowup(rs.getBoolean(4));
				notAvailableSlot.setDate(rs.getString(5));
				notAvailableSlot.setRemark(rs.getString(6));
				notAvailableSlot.setUser_id(rs.getString(7));
				notAvailableSlot.setDate_time(rs.getString(8));
				notAvailableSlot.setAll_procedure(proceduremastername);
				notAvailableSlot.setProcedure_list(procedurname);
				notAvailableSlot.setDeptOpdId(rs.getInt(11));
				notAvailableSlot.setSittingnum(rs.getString(12));
				notAvailableSlot.setDiagnosisarea(rs.getString(13));
				notAvailableSlot.setHosp_name(hospitalname);
				notAvailableSlot.setBed_ward(rs.getString(15));
				
				
				list.add(notAvailableSlot);
				
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}


	public String sessionname(String string) {
        PreparedStatement pmt=null;
        
		String sessionname="";
		
		try {
			
			   String sql="select apmtType from apm_invoice_assesments where id='"+string+"'";
			   pmt=connection.prepareStatement(sql);
			
			   ResultSet rs=pmt.executeQuery();
			
			   while(rs.next()){
				
				   sessionname=rs.getString(1);
				
				}
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return sessionname;
		
	}


	private String hospitalname(String hospitalid) {
PreparedStatement pmt=null;
        
		String hospitalname="";
		
		try {
			
			   String sql="select hospital_name from physio_ipd_hospital where id='"+hospitalid+"'";
			   pmt=connection.prepareStatement(sql);
			
			   ResultSet rs=pmt.executeQuery();
			
			   while(rs.next()){
				
				   hospitalname=rs.getString(1);
				
				}
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return hospitalname;
		
	}


	@Override
	public String getdeptOpdId(String clientId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM department_opd  where clientId ='"+clientId+"' order by id desc limit 0,1";
		
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
	public ArrayList<Priscription> getPriscriptionListbyclientId(String datetime, String clientId) {
		PreparedStatement preparedStatement = null;
		datetime=datetime.split(" ")[0];
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection); 
		ArrayList<Priscription>list = new ArrayList<Priscription>();
		PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
		//Priscription priscription1 = getPriscriptionParentData(Integer.parseInt(selectedid));
		
		/*String sql = "SELECT id,lastmodified,mdicinename,dose,frequency,"
				+ "days,type,total,priscdurationtype,notes,mdicineid,sqno,"
				+ "prisctimeid,prisctimename,priscremark,unitextension,unit,"
				+ "ipdtimeshow,dr_qty,masterdose,dddose FROM apm_client_priscription where parentid = "+selectedid+" "
				+ "and isnurseprisc='0' or isnurseprisc is null order by (sqno+0) asc";*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_client_priscription.id,lastmodified,mdicinename,dose,frequency,days,notes, prisctimename,priscremark,"
				+ "dr_qty,regional,unit,unitextension FROM apm_client_priscription inner join apm_medicine_dosage on apm_client_priscription.dose=apm_medicine_dosage.name"
				+ "  where clientid = "+clientId+"  and lastmodified between '"+datetime+" 00:00:00' and '"+datetime+" 23:59:59'  group by mdicinename order by parentid desc");		/*
		 * sql.append("SELECT * FROM ("); sql.
		 * append("SELECT apm_client_priscription.id,lastmodified,mdicinename,dose,frequency,days,notes, "
		 * ); sql.append("prisctimename,priscremark,"); sql.
		 * append("dr_qty,regional,unit,unitextension,  ROW_NUMBER() OVER (PARTITION BY mdicinename ORDER BY parentid DESC) AS rn"
		 * +
		 * "  FROM apm_client_priscription inner join apm_medicine_dosage on apm_client_priscription.dose=apm_medicine_dosage.name"
		 * + " where clientid = "+clientId+" ");
		 * sql.append(" and lastmodified between '"+datetime+" 00:00:00' and '"
		 * +datetime+" 23:59:59'");
		 * 
		 * sql.append(") t " + "WHERE rn = 1;");
		 */
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Priscription priscription = new Priscription();
			priscription.setId(rs.getInt(1));
			priscription.setLastmodified(rs.getString(2));
			priscription.setMdicinenametxt(rs.getString(3));
			priscription.setPriscdose(rs.getString(4));
			priscription.setPriscfreq(rs.getString(5));
			priscription.setPriscdays(rs.getString(6));
			priscription.setDosenotes(rs.getString(7));
			priscription.setPrisctimename(rs.getString(8));
			priscription.setPriscindivisualremark(rs.getString(9));
			priscription.setPriscqty(rs.getString(10));
			priscription.setRegional(rs.getString(11));
			priscription.setUnit(rs.getString((12)));
			priscription.setUnitextension(rs.getString(13));
			list.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public String getMedicineDoseHindi(String dosage) {
		PreparedStatement preparedStatement = null;
        String regional="";
		String sql = "SELECT regional FROM apm_medicine_dosage where name='"+dosage+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
                regional=(rs.getString(1));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return regional;
	}


	@Override
	public int saveAfitechClientDocument(DiaryManagement diaryManagement, String ukCurrentDataTime, String userId) {
		PreparedStatement preparedStatement = null;
		int result=0;
		String sql = "insert into mail_attachment (clientid,filename,datetime,templateid) values(?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, diaryManagement.getClientid());
			preparedStatement.setString(2, diaryManagement.getFileName());
			preparedStatement.setString(3, diaryManagement.getDate_time());
			preparedStatement.setString(4, diaryManagement.getTemplateid());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
		
	}
		
	}

