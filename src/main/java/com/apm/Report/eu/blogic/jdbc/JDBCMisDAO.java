package com.apm.Report.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.MisDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

public class JDBCMisDAO extends JDBCBaseDAO implements MisDAO{
	
	public JDBCMisDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<MisReport> getMisRecord(String fromDate,
			String toDate,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<MisReport>list = new ArrayList<MisReport>();
		String sql = "SELECT commencing,clientid,diaryuserid,location,starttime,charge,aptmtype,treatmentEpisodeId FROM apm_available_slot where commencing between '"+fromDate+"' and  '"+toDate+"' and status = 0 and treatmentEpisodeId!=0";
		
		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
		Clinic cliniclist = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				MisReport misReport = new MisReport();
				misReport.setClinicName(cliniclist.getClinicName());
				misReport.setInvoiceno("N/A");
				
				ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
				Clinic clinic = new Clinic();
				clinic = clinicListDAO.getLocationDetails(rs.getInt(4));
				String location = clinic.getCity() + " ("+clinic.getLocationname()+")";
				misReport.setLocation(location);
				
				
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(rs.getString(2));
				if(client.getDob()!=null){
					String temp[] = client.getDob().split("/");
					int dobYear = Integer.parseInt(temp[2]);
					int currentyear = Calendar.getInstance().get(Calendar.YEAR);
					int age = currentyear - dobYear;
					misReport.setAge(Integer.toString(age));
				}else{
					misReport.setAge("N/A");
				}
				if(client.getNhsNumber()!=null){
					misReport.setNhsNumber(client.getNhsNumber());
				}else{
					misReport.setNhsNumber("N/A");
				}
				
				
				int firstApptid = getFirstAppointId(client.getId(),fromDate,toDate);
				int invoiceNo = getInvoiceNumber(client.getId(),firstApptid);
				if(invoiceNo!=0){
					misReport.setInvoiceno(Integer.toString(invoiceNo));
				}
				
				misReport.setGender(client.getGender());
				misReport.setPracticeCode("N/A");
				misReport.setCommissionerCCG("N/A");
				misReport.setSubCCD("N/A");
				misReport.setOtherSubCCD("N/A");
				
				if(client.getReference()!=null){
					misReport.setSourceofRefferal(client.getReference());
				}else{
					misReport.setSourceofRefferal("N/A");
				}
				
				if(client.getReferedDate()!=null){
					misReport.setRefferalRecievedDate(client.getReferedDate());
				}else{
					misReport.setRefferalRecievedDate("N/A");
				}
				
				int treatmentEpisodeid = rs.getInt(8);
				if(treatmentEpisodeid!=0){
					TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
					TreatmentEpisode treatmentEpisode = treatmentEpisodeDAO.getParticularTreatEpiDetails(Integer.toString(treatmentEpisodeid));
					if(treatmentEpisode.isUrgent()){
						misReport.setUrgency("Urgent");
					}else{
						misReport.setUrgency("Routine");
					}
					
					
					String outcome = getOutComeName(treatmentEpisode.getOutcomes());
					String dischargeStatus = getDischargeStatus(treatmentEpisode.getDschargestatus());
					misReport.setTreatmentOutcome(outcome);
					misReport.setDischargeReason(dischargeStatus);
					
					if(treatmentEpisode.getOutcomes()==null){
						misReport.setTreatmentOutcome("N/A");
					}
					if(treatmentEpisode.getDschargestatus()==null){
						misReport.setDischargeReason("N/A");
					}
				}else{
					misReport.setUrgency("Routine");
					misReport.setTreatmentOutcome("N/A");
					misReport.setDischargeReason("N/A");
				}
				
				String assesmentDate = getFirstAppointmentDate(client.getId(),fromDate,toDate);
				assesmentDate = DateTimeUtils.getCommencingDate2(assesmentDate);
				misReport.setAssesmentDate(assesmentDate);
				
				if(treatmentEpisodeid!=0){
					TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
					TreatmentEpisode treatmentEpisode = treatmentEpisodeDAO.getParticularTreatEpiDetails(Integer.toString(treatmentEpisodeid));
					
					if(treatmentEpisode.getReferalDate()!=null){
						if(!treatmentEpisode.getReferalDate().equals("")){
							long reftoassesmentdays = DateTimeUtils.getDifferenceOfTwoDate(treatmentEpisode.getReferalDate(), assesmentDate);
							misReport.setReftoAssesmentDays(Long.toString(reftoassesmentdays));
						}else{
							misReport.setReftoAssesmentDays("N/A");
						}
				}else{
					misReport.setReftoAssesmentDays("N/A");
				}
				
					
					
				}else{
					misReport.setReftoAssesmentDays("N/A");
				}
				
				
				
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userprofile = userProfileDAO.getUserprofileDetails(rs.getInt(3));
				//String practitonerName = userprofile.getInitial() + " " + userprofile.getFirstname() + " " + userprofile.getLastname();
				String ptype = getPractitonerType(userprofile.getPractitonerType());
				
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				Master master = masterDAO.getDisciplineData(userprofile.getDiciplineName());
				
				String practitonerType = ptype + " " + master.getDiscipline();
				
				if(userprofile.getPractitonerType()!=null){
					if(!userprofile.getPractitonerType().equals("0")){
						misReport.setPractitonerType(practitonerType);
					}else{
						misReport.setPractitonerType("N/A");
					}
				}else{
					misReport.setPractitonerType("N/A");
				}
				
				
				String appointmentDate = DateTimeUtils.getCommencingDate1(rs.getString(1));
				misReport.setApptDate(appointmentDate);
				
				misReport.setApptTime(rs.getString(5));
				misReport.setPractitonerSurname(userprofile.getLastname());
				
				AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
				AppointmentType appointmentType = appointmentTypeDAO.getAppointment(rs.getInt(7));
				String apptName = appointmentType.getName();
				misReport.setApptName(apptName);
				
				misReport.setApptCharge(Constants.getCurrency(loginInfo)+rs.getString(6));
				
				list.add(misReport);
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	

	private int getInvoiceNumber(int clientid, int firstApptid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT invoiceid,chargeinvoiceid FROM apm_invoice  inner join apm_charges_assesment on ");
		sql.append("apm_charges_assesment.invoiceid = apm_invoice.id where appointmentid = "+firstApptid+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String getDischargeStatus(String dschargestatus) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_discharge_status where id = "+dschargestatus+" ";
		
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

	private String getOutComeName(String outcomes) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_discharge_outcomes where id = "+outcomes+" ";
		
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

	private String getPractitonerType(String practitonerType) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_practitoner_type where id = "+practitonerType+" ";
		
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

	private String getFirstAppointmentDate(int clientid,String fromdate,String todate) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT commencing FROM apm_available_slot where commencing between '"+fromdate+"' and  '"+todate+"' and status = 0 and treatmentEpisodeId!=0 and clientid="+clientid+" order by id asc limit 0,1";
		
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
	
	private int getFirstAppointId(int clientid,String fromdate,String todate){
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_available_slot where commencing between '"+fromdate+"' and  '"+todate+"' and status = 0 and treatmentEpisodeId!=0 and clientid="+clientid+" order by id asc limit 0,1";
		
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

	public ArrayList<NotAvailableSlot> getThreeDayRefList(String fromDate,
			String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ref_date,commencing,apm_available_slot.clientid FROM apm_available_slot inner join apm_treatment_episode on ");
		sql.append("apm_treatment_episode.id = apm_available_slot.treatmentEpisodeId ");
		sql.append("where commencing between '"+fromDate+"' and '"+toDate+"' and ");
		sql.append("ref_date is not null and ref_date!='' and ref_date!='null'  and urgent=1 group by treatmentEpisodeId ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setRefDate(rs.getString(1));
				String commencing = getFirstAppointmentDate(rs.getInt(3), fromDate, toDate);
				notAvailableSlot.setCommencing(commencing);
				
				list.add(notAvailableSlot);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<NotAvailableSlot> getTwentyEightRefList(String fromDate,String toDate) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ref_date,commencing,apm_available_slot.clientid,dischargedate FROM apm_available_slot inner join apm_treatment_episode on ");
		sql.append("apm_treatment_episode.id = apm_available_slot.treatmentEpisodeId ");
		sql.append("where commencing between '"+fromDate+"' and '"+toDate+"' and ");
		sql.append("ref_date is not null and ref_date!='' and ref_date!='null'  and urgent=0 group by treatmentEpisodeId ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setRefDate(rs.getString(1));
				String commencing = getFirstAppointmentDate(rs.getInt(3), fromDate, toDate);
				notAvailableSlot.setCommencing(commencing);
				notAvailableSlot.setDischargedate(rs.getString(4));
				list.add(notAvailableSlot);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public int getDnaCount(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM apm_available_slot where dna = 1 and commencing  between '"+fromDate+"' and '"+toDate+"' ";
		
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

	public int getLetterCount(String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM apm_email_letter_log where type = 'Letter' and datetime between '"+fromDate+"' and '"+toDate+"' ";
		
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

	public int getTotalAppt(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM apm_available_slot where commencing  between '"+fromDate+"' and '"+toDate+"' ";
		
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

	public int getNewRefralCount(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM apm_treatment_episode inner join apm_available_slot ");
		sql.append("on apm_available_slot.treatmentEpisodeId = apm_treatment_episode.id ");
		sql.append("where apm_treatment_episode.usedsession = 0 ");
		sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("group by apm_treatment_episode.id ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getNewRefralTriggeredCount(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT count(*) FROM apm_treatment_episode inner join apm_available_slot ");
		sql.append("on apm_available_slot.treatmentEpisodeId = apm_treatment_episode.id ");
		sql.append("where apm_treatment_episode.usedsession = 1 ");
		sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("group by apm_treatment_episode.id ");
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<NotAvailableSlot> getNewREfralNotTrigeredList(
			String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ref_date,commencing FROM apm_treatment_episode inner join apm_available_slot ");
		sql.append("on apm_available_slot.treatmentEpisodeId = apm_treatment_episode.id ");
		sql.append("where apm_treatment_episode.usedsession = 0 ");
		sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("group by apm_treatment_episode.id ");
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setRefDate(rs.getString(1));
				notAvailableSlot.setCommencing(rs.getString(2));
				
				list.add(notAvailableSlot);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int getCountInitialAssessment(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT count(*) FROM apm_available_slot where (apmttypetext = 'initial appointment' or apmttypetext='initial assessment') ");
		sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int getCountFollowupAttendence(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT count(*) FROM apm_available_slot where (apmttypetext = 'Follow up attendances') ");
		sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public int getRoutineCount(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT count(apm_treatment_episode.id) FROM apm_treatment_episode inner join apm_available_slot ");
		sql.append("on apm_available_slot.treatmentEpisodeId = apm_treatment_episode.id ");
		sql.append("where apm_treatment_episode.urgent = 0 ");
		sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("group by apm_treatment_episode.id ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getUrgentCount(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT count(apm_treatment_episode.id) FROM apm_treatment_episode inner join apm_available_slot ");
		sql.append("on apm_available_slot.treatmentEpisodeId = apm_treatment_episode.id ");
		sql.append("where apm_treatment_episode.urgent = 1 ");
		sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("group by apm_treatment_episode.id ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getDischargeCount(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT count(apm_treatment_episode.id) FROM apm_treatment_episode inner join apm_available_slot ");
		sql.append("on apm_available_slot.treatmentEpisodeId = apm_treatment_episode.id ");
		sql.append("where apm_treatment_episode.treatmentstatus = 1 ");
		sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("group by apm_treatment_episode.id ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getCountCancelledApmt(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT count(*) FROM apm_appointment_log ");
		sql.append("where commencing between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("and status = 'Cancelled' ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<NotAvailableSlot> getGreaterThanSixFollowups(
			String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_available_slot.clientid,apm_treatment_episode.usedsession ");
		sql.append("FROM apm_treatment_episode inner join apm_available_slot ");
		sql.append("on apm_available_slot.treatmentEpisodeId = apm_treatment_episode.id ");
		sql.append("where apm_treatment_episode.usedsession > 6 ");
		sql.append("and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("group by apm_treatment_episode.id ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(rs.getString(1));
				String clientName = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				notAvailableSlot.setClientName(clientName);
				notAvailableSlot.setUsedsession(rs.getString(2));
				if(client.getNhsNumber()!=null){
					notAvailableSlot.setNhsNumber(client.getNhsNumber());
				}else{
					notAvailableSlot.setNhsNumber("N/A");
				}
				
				
				list.add(notAvailableSlot);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

}

