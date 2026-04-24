package com.apm.Report.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.tiles.jsp.taglib.GetAsStringTag;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCStatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.SourceOfIntroDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSourceOfIntroDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SourceOfIntro;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Mis.eu.blogic.jdbc.JDBCMisChartDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.bi.SummaryReportDAO;
import com.apm.Report.eu.entity.Report;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.Report.web.form.SummaryReportForm;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.GP;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class JDBCSummaryReportDAO extends JDBCBaseDAO implements SummaryReportDAO{
	
	public JDBCSummaryReportDAO(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<SummaryReport> getDNAAnalysisReport(String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
		fromDate=DateTimeUtils.getCommencingDate1(fromDate);
		toDate=DateTimeUtils.getCommencingDate1(toDate);
		String sql = "select apm_available_slot.id,apm_available_slot.commencing,apm_available_slot.location,"
				+ "apm_available_slot.clientname,apm_available_slot.aptmtype,apm_available_slot.clientId,"
				+ "apm_available_slot.dna,apm_available_slot.dnareason,apm_available_slot.charge,"
				+ "apm_available_slot.apmttypetext,apm_patient.mobno,apm_patient.email,apm_patient.address,"
				+ "apm_patient.town,apm_patient.country from apm_available_slot inner join apm_patient "
				+ "on apm_available_slot.clientId = apm_patient.id where apm_available_slot.dna = 1 and apm_available_slot.commencing between '"+fromDate+"' and'" +toDate+"'";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setAppointmentId(rs.getInt(1));
				summaryReport.setCommencing(rs.getString(2));
				String location  = getLocation(rs.getString(3));
				summaryReport.setLocation(location);
				summaryReport.setClientname(rs.getString(4));
				summaryReport.setAptmtypeId(rs.getString(5));
				summaryReport.setClientId(rs.getString(6));
				summaryReport.setDna(rs.getString(7));
				summaryReport.setDnareason(rs.getString(8));
				summaryReport.setCharge(rs.getString(9));
				summaryReport.setApmttypetext(rs.getString(10));
				summaryReport.setMobno(rs.getString(11));
				summaryReport.setEmail(rs.getString(12));
				summaryReport.setAddress(rs.getString(13));
				summaryReport.setTown(rs.getString(14));
				summaryReport.setCountry(rs.getString(15));
				
				list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	private String getLocation(String locationId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select location from apm_clinic_location where id = "+locationId+"";
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

	public ArrayList<SummaryReport> getDNAAnalysisByPractitioner(int practitionerId,String fromDate,String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
		fromDate=DateTimeUtils.getCommencingDate1(fromDate);
		toDate=DateTimeUtils.getCommencingDate1(toDate);
		StringBuffer sb=new StringBuffer();
		String sql="";
		
		if(practitionerId!= 0){			
		sb.append("select apm_available_slot.id,apm_available_slot.commencing,apm_available_slot.location, ");
		sb.append( "apm_available_slot.clientname,apm_available_slot.aptmtype,apm_available_slot.clientId, ");
		sb.append("apm_available_slot.dna,apm_available_slot.dnareason,apm_available_slot.charge, ");
		sb.append( "apm_available_slot.apmttypetext,apm_patient.mobno,apm_patient.email,apm_patient.address, ");
		sb.append("apm_patient.town,apm_patient.country from apm_available_slot inner join apm_patient ");
		sb.append( "on apm_available_slot.clientId = apm_patient.id where apm_available_slot.dna = 1 ");
		sb.append( "and apm_available_slot.diaryuserid = "+practitionerId+"");
		if(!fromDate.equals(""))
		{
		sb.append(" and apm_available_slot.commencing between '"+fromDate+"' and'" +toDate+"' ");
		}
//			sql = "select apm_available_slot.id,apm_available_slot.commencing,apm_available_slot.location,"
//				+ "apm_available_slot.clientname,apm_available_slot.aptmtype,apm_available_slot.clientId,"
//				+ "apm_available_slot.dna,apm_available_slot.dnareason,apm_available_slot.charge,"
//				+ "apm_available_slot.apmttypetext,apm_patient.mobno,apm_patient.email,apm_patient.address,"
//				+ "apm_patient.town,apm_patient.country from apm_available_slot inner join apm_patient "
//				+ "on apm_available_slot.clientId = apm_patient.id where apm_available_slot.dna = 1 "
//				+ "and apm_available_slot.diaryuserid = "+practitionerId+""
//				
//				+ " and apm_available_slot.commencing between '"+fromDate+"' and'" +toDate+"' ";
				
				
		}
		else{
			
			sb.append("select apm_available_slot.id,apm_available_slot.commencing,apm_available_slot.location, ");
			sb.append("apm_available_slot.clientname,apm_available_slot.aptmtype,apm_available_slot.clientId, ");
			sb.append("apm_available_slot.dna,apm_available_slot.dnareason,apm_available_slot.charge, ");
			sb.append("apm_available_slot.apmttypetext,apm_patient.mobno,apm_patient.email,apm_patient.address, ");
			sb.append("apm_patient.town,apm_patient.country from apm_available_slot inner join apm_patient ");
			sb.append( "on apm_available_slot.clientId = apm_patient.id where apm_available_slot.dna = 1 ");
			sb.append(" and apm_available_slot.commencing between '"+fromDate+"' and'" +toDate+"' ");
			
//			sql = "select apm_available_slot.id,apm_available_slot.commencing,apm_available_slot.location,"
//					+ "apm_available_slot.clientname,apm_available_slot.aptmtype,apm_available_slot.clientId,"
//					+ "apm_available_slot.dna,apm_available_slot.dnareason,apm_available_slot.charge,"
//					+ "apm_available_slot.apmttypetext,apm_patient.mobno,apm_patient.email,apm_patient.address,"
//					+ "apm_patient.town,apm_patient.country from apm_available_slot inner join apm_patient "
//					+ "on apm_available_slot.clientId = apm_patient.id where apm_available_slot.dna = 1  ";
		}
		
		try{
			
			preparedStatement = connection.prepareStatement(sb.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setAppointmentId(rs.getInt(1));
				summaryReport.setCommencing(rs.getString(2));
				String location  = getLocation(rs.getString(3));
				summaryReport.setLocation(location);
				summaryReport.setClientname(rs.getString(4));
				summaryReport.setAptmtypeId(rs.getString(5));
				summaryReport.setClientId(rs.getString(6));
				summaryReport.setDna(rs.getString(7));
				summaryReport.setDnareason(rs.getString(8));
				summaryReport.setCharge(rs.getString(9));
				summaryReport.setApmttypetext(rs.getString(10));
				summaryReport.setMobno(rs.getString(11));
				summaryReport.setEmail(rs.getString(12));
				summaryReport.setAddress(rs.getString(13));
				summaryReport.setTown(rs.getString(14));
				summaryReport.setCountry(rs.getString(15));
				
				list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<SummaryReport> getAppKeptDNAList(String fromDate,
			String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
		
		String sql = "";
		
		if(!fromDate.equals("") && !toDate.equals("")){	
		
			sql = "select apm_patient.id,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
					+ "apm_patient.email,apm_patient.address,apm_patient.town,apm_available_slot.diaryuserid,"
					+ "apm_available_slot.diaryusername from apm_patient inner join apm_available_slot on "
					+ "apm_patient.id = apm_available_slot.clientId and apm_available_slot.commencing "
					+ "between '"+fromDate+"' AND '"+toDate+"' group by apm_patient.id";
					
		}else{
			sql = "select apm_patient.id,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
					+ "apm_patient.email,apm_patient.address,apm_patient.town,apm_available_slot.diaryuserid,"
					+ "apm_available_slot.diaryusername from apm_patient inner join apm_available_slot on "
					+ "apm_patient.id = apm_available_slot.clientId group by apm_patient.id";
		}
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				String clientId = rs.getString(1);
				summaryReport.setClientId(clientId);
				String clientname = rs.getString(2)+""+rs.getString(3)+"";
				summaryReport.setClientname(clientname);
				summaryReport.setMobno(rs.getString(4));
				summaryReport.setEmail(rs.getString(5));
				summaryReport.setAddress(rs.getString(6));
				summaryReport.setTown(rs.getString(7));
				summaryReport.setPractitionerId(rs.getString(8));
				summaryReport.setPractitionerName(rs.getString(9));
				int totalApp = getTotalAppointment(clientId);
				summaryReport.setTotalApp(totalApp);
				int totalDNA = getTotalDNA(clientId);
				summaryReport.setTotalDNA(totalDNA);
				int totalComplete = getTotalCompleteApp(clientId);
				summaryReport.setTotalComplete(totalComplete);
				//int totalIncomplete = getTotalIncompleteApp(clientId);
				int totalIncomplete = totalApp - totalComplete;
				summaryReport.setTotalIncomplete(totalIncomplete);
				
				list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private int getTotalIncompleteApp(String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(apm_available_slot.id) from apm_available_slot where not exists "
				+ "(select apm_invoice.appointmentid from apm_invoice where apm_available_slot.id = "
				+ "apm_invoice.appointmentid) and apm_available_slot.clientid = "+clientId+"";
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

	private int getTotalCompleteApp(String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		//String sql = "select count(*) from apm_invoice where clientid = "+clientId+" and (chargetype = 'Charge'  or chargetype = 'Submit')";
		
		String sql = "select id from apm_invoice where clientid = "+clientId+" and apmt_charge_type = 0 group by appointmentid";
		int i = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				//result = rs.getInt(1);
				i++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}

	private int getTotalDNA(String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_invoice where clientid = "+clientId+" and chargetype = 'DNA'";
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

	private int getTotalAppointment(String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_available_slot where clientId = "+clientId+"  group by clientid";
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

	public ArrayList<SummaryReport> getAppKeptDNAList() {
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
		String sql = "select apm_patient.id,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
				+ "apm_patient.email,apm_patient.address,apm_patient.town,apm_available_slot.diaryuserid,"
				+ "apm_available_slot.diaryusername from apm_patient inner join apm_available_slot on "
				+ "apm_patient.id = apm_available_slot.clientId group by apm_patient.id";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			SummaryReport summaryReport = new SummaryReport();
			String clientId = rs.getString(1);
			summaryReport.setClientId(clientId);
			String clientname = rs.getString(2)+""+rs.getString(3)+"";
			summaryReport.setClientname(clientname);
			summaryReport.setMobno(rs.getString(4));
			summaryReport.setEmail(rs.getString(5));
			summaryReport.setAddress(rs.getString(6));
			summaryReport.setTown(rs.getString(7));
			summaryReport.setPractitionerId(rs.getString(8));
			summaryReport.setPractitionerName(rs.getString(9));
			int totalApp = getTotalAppointment(clientId);
			summaryReport.setTotalApp(totalApp);
			int totalDNA = getTotalDNA(clientId);
			summaryReport.setTotalDNA(totalDNA);
			int totalComplete = getTotalCompleteApp(clientId);
			summaryReport.setTotalComplete(totalComplete);
			//int totalIncomplete = getTotalIncompleteApp(clientId);
			int totalIncomplete = totalApp - totalComplete;
			summaryReport.setTotalIncomplete(totalIncomplete);
			
			list.add(summaryReport);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
	}

	public ArrayList<SummaryReport> getTreatmentReferralList(String payby,
			String diaryUser, String thirdParty, String gpid, String condition,
			String referal, String fromDate, String toDate) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
	/*	String sql = "select apm_patient.id,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
				+ "apm_patient.email,apm_patient.address,apm_patient.town,apm_patient.refrence,apm_treatment_episode.id,"
				+ "apm_treatment_episode.name,apm_treatment_episode.ref_date,apm_treatment_episode.ref_source,"
				+ "apm_treatment_episode.ref_name,apm_treatment_episode.ref_contact,apm_treatment_episode.ref_letter from "
				+ "apm_patient inner join apm_treatment_episode on apm_patient.id = apm_treatment_episode.clientid group by apm_patient.id";*/
		
		toDate = toDate + " 23:59:59";
		
		StringBuffer sql = new StringBuffer();
		sql.append("select apm_patient.id,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,apm_patient.email,apm_patient.address, ");
		sql.append("apm_patient.town,apm_patient.refrence,apm_treatment_episode.id,apm_treatment_episode.name,apm_treatment_episode.ref_date, ");
		sql.append("apm_patient.sourceofintro,apm_treatment_episode.ref_name,apm_treatment_episode.ref_contact,apm_treatment_episode.ref_letter, ");
		sql.append("apm_treatment_episode.lastmodified,apm_treatment_episode.payby,apm_treatment_episode.practitionerid,apm_patient.third_party_name_id,apm_patient.third_party_id,apm_treatment_episode.condition_id,apm_patient.gp_id,apm_patient.abrivationid,ipd_addmission_form.wardid ");
		sql.append("from apm_patient inner join apm_treatment_episode on apm_patient.id = apm_treatment_episode.clientid ");
		sql.append("left join ipd_addmission_form on ipd_addmission_form.clientid=apm_patient.id ");
		sql.append("where apm_treatment_episode.lastmodified between '"+fromDate+"' and '"+toDate+"' ");
		
		if(payby!=null){
			//if(!thirdParty.equals("0")){
				//payby
				if(diaryUser.equals("")){
					diaryUser = "0";
				}
				
				
				if(!payby.equals("0") && !thirdParty.equals("0") && !diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0") && !gpid.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_name_id="+thirdParty+" and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" and apm_patient.gp_id="+gpid+" ");
				}
				
				if(!payby.equals("0") && !thirdParty.equals("0") && !diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_name_id="+thirdParty+" and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!payby.equals("0") && !thirdParty.equals("0") && !diaryUser.equals("0") && !condition.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_name_id="+thirdParty+" and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_treatment_episode.condition_id="+condition+" ");
				}
				else if(!payby.equals("0") && !thirdParty.equals("0") && !diaryUser.equals("0") && !gpid.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_name_id="+thirdParty+" and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_patient.gp_id="+gpid+"  ");
				}
				else if(!payby.equals("0") && !thirdParty.equals("0") && !diaryUser.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_name_id="+thirdParty+" and apm_treatment_episode.practitionerid in("+diaryUser+")  ");
				}
				else if(!payby.equals("0") && !thirdParty.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_name_id="+thirdParty+"  ");
				}
				else if(!payby.equals("0") && !diaryUser.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_treatment_episode.practitionerid in("+diaryUser+")  ");
				}
				else if(!payby.equals("0") && !condition.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_treatment_episode.condition_id="+condition+"  ");
				}
				else if(!payby.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.refrence="+referal+"  ");
				}
				else if(!payby.equals("0") && !gpid.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.gp_id="+gpid+"  ");
				}
			
			
			//third  party
				else if(!thirdParty.equals("0") && !diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0") && !gpid.equals("0")){
					sql.append("and apm_patient.third_party_name_id="+thirdParty+" and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" and apm_patient.gp_id="+gpid+" ");
				}
				else if(!thirdParty.equals("0") && !diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_patient.third_party_name_id="+thirdParty+" and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!thirdParty.equals("0") && !diaryUser.equals("0") && !condition.equals("0")){
					sql.append("and apm_patient.third_party_name_id="+thirdParty+" and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_treatment_episode.condition_id="+condition+" ");
				}
				else if(!thirdParty.equals("0") && !diaryUser.equals("0")){
					sql.append("and apm_patient.third_party_name_id="+thirdParty+" and apm_treatment_episode.practitionerid in("+diaryUser+")  ");
				}
			
				
				//diary user
				else if(!diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0") && !gpid.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" and apm_patient.gp_id="+gpid+" ");
				}
				else if(!diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!diaryUser.equals("0") && !condition.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid in("+diaryUser+") and apm_treatment_episode.condition_id="+condition+" ");
				}
				
				
				//condition
				else if(!condition.equals("0") && !referal.equals("0") && !gpid.equals("0")){
					sql.append("and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" and apm_patient.gp_id="+gpid+" ");
				}
				else if(!condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+"  ");
				}
			
				
				//referal
				else if(!referal.equals("0") && !gpid.equals("0")){
					sql.append("and apm_patient.refrence="+referal+" and  apm_patient.gp_id="+gpid+" ");
				}
			
				
				//gpid
				else if(!gpid.equals("0")){
					sql.append("and apm_patient.gp_id="+gpid+" ");
				}
				else if(!payby.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' ");
				}
				else if(!thirdParty.equals("0")){
					sql.append("and apm_patient.third_party_name_id="+thirdParty+"  ");
				}
				else if(!diaryUser.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid in("+diaryUser+")  ");
				}
				else if(!condition.equals("0")){
					sql.append("and apm_treatment_episode.condition_id="+condition+" ");
				}
				else if(!referal.equals("0")){
					sql.append("and apm_patient.refrence="+referal+" ");
				}
			
			//}
			
		/*	else if(!gpid.equals("0")){
				//payby
				if(!payby.equals("0") && !gpid.equals("0") && !diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_id="+gpid+" and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!payby.equals("0") && !gpid.equals("0") && !diaryUser.equals("0") && !condition.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_id="+gpid+" and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" ");
				}
				else if(!payby.equals("0") && !gpid.equals("0") && !diaryUser.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_id="+gpid+" and apm_treatment_episode.practitionerid="+diaryUser+"  ");
				}
				else if(!payby.equals("0") && !gpid.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_patient.third_party_id="+gpid+"  ");
				}
				else if(!payby.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' ");
				}
				
				//third  party
				else if(!gpid.equals("0") && !diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_patient.third_party_id="+gpid+" and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!gpid.equals("0") && !diaryUser.equals("0") && !condition.equals("0")){
					sql.append("and apm_patient.third_partyid="+gpid+" and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" ");
				}
				else if(!gpid.equals("0") && !diaryUser.equals("0")){
					sql.append("and apm_patient.third_party_id="+gpid+" and apm_treatment_episode.practitionerid="+diaryUser+"  ");
				}
				else if(!gpid.equals("0")){
					sql.append("and apm_patient.third_party_id="+gpid+"  ");
				}
				
				//diary user
				else if(!diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!diaryUser.equals("0") && !condition.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" ");
				}
				else if(!diaryUser.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid="+diaryUser+"  ");
				}
				
				//condition
				else if(!condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!condition.equals("0")){
					sql.append("and apm_treatment_episode.condition_id="+condition+" ");
				}
				
				//referal
				else if(!referal.equals("0")){
					sql.append("and apm_patient.refrence="+referal+" ");
				}
			}else{
				//payby
				if(!payby.equals("0")  && !diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"'  and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!payby.equals("0") &&  !diaryUser.equals("0") && !condition.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" ");
				}
				else if(!payby.equals("0") && !diaryUser.equals("0")){
					sql.append("and apm_treatment_episode.payby='"+payby+"' and apm_treatment_episode.practitionerid="+diaryUser+"  ");
				}
				else if(!payby.equals("0") ){
					sql.append("and apm_treatment_episode.payby='"+payby+"' ");
				}
				
				
			
		
				//diary user
				else if(!diaryUser.equals("0") && !condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!diaryUser.equals("0") && !condition.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid="+diaryUser+" and apm_treatment_episode.condition_id="+condition+" ");
				}
				else if(!diaryUser.equals("0")){
					sql.append("and apm_treatment_episode.practitionerid="+diaryUser+"  ");
				}
				
				//condition
				else if(!condition.equals("0") && !referal.equals("0")){
					sql.append("and apm_treatment_episode.condition_id="+condition+" and apm_patient.refrence="+referal+" ");
				}
				else if(!condition.equals("0")){
					sql.append("and apm_treatment_episode.condition_id="+condition+" ");
				}
				
				//referal
				else if(!referal.equals("0")){
					sql.append("and apm_patient.refrence="+referal+" ");
				}
			}*/
		}
		
	
		
		sql.append("group by apm_patient.id ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				String clientId = rs.getString(1);
				summaryReport.setClientId(clientId);
				String clientname = rs.getString(2)+""+rs.getString(3)+"";
				summaryReport.setClientname(clientname);
				summaryReport.setMobno(rs.getString(4));
				summaryReport.setEmail(rs.getString(5));
				summaryReport.setAddress(rs.getString(6));
				summaryReport.setTown(rs.getString(7));				
				String referalname = getReferalName(rs.getString(8));
				summaryReport.setReferal(referalname);
				summaryReport.setTreatmentId(rs.getString(9));
				summaryReport.setTreatmentName(rs.getString(10));
				summaryReport.setRef_date(rs.getString(11));
				
				SourceOfIntroDAO SourceOfIntroDao = new JDBCSourceOfIntroDAO(connection);
				SourceOfIntro sourceOfIntro = SourceOfIntroDao.getSourceOfIntro(rs.getInt(12), new SourceOfIntro());
				summaryReport.setRef_source(sourceOfIntro.getSourceName());
				
				
				summaryReport.setRef_name(rs.getString(13));
				summaryReport.setRef_contact(rs.getString(14));
				summaryReport.setRef_letter(rs.getString(15));
				
				summaryReport.setPayby(rs.getString(17));
				if(rs.getString(18)!=null){
					if(rs.getInt(18)!=0){
						UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
						UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(18));
						String practitonerName = userProfile.getInitial() + " " + userProfile.getFirstname() + " " +userProfile.getLastname();
						summaryReport.setPractitionerName(practitonerName);
					}else{
						summaryReport.setPractitionerName("");
					}
				}else{
					summaryReport.setPractitionerName("");
				}
				ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
				if(rs.getString(19)!=null){
					
					ThirdParty party =  thirdPartyDAO.getThirdPartyDetails(rs.getString(19));
						summaryReport.setTpName(party.getCompanyName());
				}
				if(rs.getString(22)!=null){
					GP gp =  thirdPartyDAO.getGPDetails(rs.getString(22));
					summaryReport.setGpName(gp.getName());
				}
				TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
				TreatmentType treatmentType = treatmentTypeDAO.getTreatmentType(rs.getInt(21), new TreatmentType());
				summaryReport.setCondition(treatmentType.getTreatmentName());
				
				
				//  29 sep 2017 set abrivation
				String abrivationid ="";
				if(rs.getString(23) == null){
					abrivationid="1";
				}else if(rs.getString(23).equals("")){
					abrivationid="1";
				}else{
					abrivationid =rs.getString(23);
				}
				summaryReport.setAbrivationid(abrivationid);
				BedDao bedDao=new JDBCBedDao(connection);
				String wardname=bedDao.getWardName(rs.getString(24));
				summaryReport.setWardname(wardname);
				list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SummaryReport> getReferalList() {
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
		String sql = "select id,name from reference where isrefered='1'";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
			SummaryReport summaryReport = new SummaryReport();
			summaryReport.setId(rs.getInt(1));
			summaryReport.setReferal(rs.getString(2));
			
			list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<SummaryReport> getTreatmentByReferral(int referalId) {
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();

		String sql = "";
		
		if(referalId!= 0){		
			sql = "select apm_patient.id,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
				+ "apm_patient.email,apm_patient.address,apm_patient.town,apm_patient.refrence,apm_treatment_episode.id,"
				+ "apm_treatment_episode.name,apm_treatment_episode.ref_date,apm_treatment_episode.ref_source,"
				+ "apm_treatment_episode.ref_name,apm_treatment_episode.ref_contact,apm_treatment_episode.ref_letter from "
				+ "apm_patient inner join apm_treatment_episode on apm_patient.id = apm_treatment_episode.clientid "
				+ "where apm_patient.refrence = "+referalId+" group by apm_patient.id";
		}else{
			sql = "select apm_patient.id,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
					+ "apm_patient.email,apm_patient.address,apm_patient.town,apm_patient.refrence,apm_treatment_episode.id,"
					+ "apm_treatment_episode.name,apm_treatment_episode.ref_date,apm_treatment_episode.ref_source,"
					+ "apm_treatment_episode.ref_name,apm_treatment_episode.ref_contact,apm_treatment_episode.ref_letter from "
					+ "apm_patient inner join apm_treatment_episode on apm_patient.id = apm_treatment_episode.clientid group by apm_patient.id";
			
		}
			
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				String clientId = rs.getString(1);
				summaryReport.setClientId(clientId);
				String clientname = rs.getString(2)+""+rs.getString(3)+"";
				summaryReport.setClientname(clientname);
				summaryReport.setMobno(rs.getString(4));
				summaryReport.setEmail(rs.getString(5));
				summaryReport.setAddress(rs.getString(6));
				summaryReport.setTown(rs.getString(7));
				String referal = getReferalName(rs.getString(8));
				summaryReport.setReferal(referal);
				summaryReport.setTreatmentId(rs.getString(9));
				summaryReport.setTreatmentName(rs.getString(10));
				summaryReport.setRef_date(rs.getString(11));
				summaryReport.setRef_source(rs.getString(12));
				summaryReport.setRef_name(rs.getString(13));
				summaryReport.setRef_contact(rs.getString(14));
				summaryReport.setRef_letter(rs.getString(15));
				
				list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private String getReferalName(String referalId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select name from reference where id = "+referalId+"";
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

	public ArrayList<SummaryReport> getReptOutStatndingList(String fromDate,
			String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport>list = new ArrayList<SummaryReport>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT concat(title,' ',firstname,' ',surname),apmttypetext,diaryuserid,third_party_name_id FROM apm_available_slot inner join apm_patient on ");
		sql.append("apm_patient.id = apm_available_slot.clientid ");
		sql.append("where apm_available_slot.status=0 and commencing between ");
		sql.append("'"+fromDate+"' and '"+toDate+"' and sent=0 ");
		sql.append("order by firstname ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setClientname(rs.getString(1));
				
				/*AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
				AppointmentType appointmentType = appointmentTypeDAO.getAppointment(rs.getInt(2));*/
				summaryReport.setApptName(rs.getString(2));
				
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile profile = userProfileDAO.getUserprofileDetails(rs.getInt(3));
				summaryReport.setPractitionerName(profile.getInitial() + " " + profile.getFirstname() + " " + profile.getLastname());
				
				ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
				ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(rs.getString(4));
				summaryReport.setTpName(thirdParty.getCompanyName());
				
				list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SummaryReport> getNewPtsList(String fromDate, String toDate,String clinicname) {
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport>list = new ArrayList<SummaryReport>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT concat(title,' ',firstname,' ',surname),whopay,refrence FROM apm_patient where newpts=0 ");
		sql.append("and lastModified between '"+fromDate+"' and '"+toDate+"' order by firstname ");
	
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setClientname(rs.getString(1));
				summaryReport.setPayby(rs.getString(2));
				summaryReport.setClinicname(clinicname);
				
				String referencename = getReferenceName(rs.getString(3));
				summaryReport.setReferedby(referencename);
				
				list.add(summaryReport);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	private String getReferenceName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM reference where id = "+id+" ";
		
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

	public ArrayList<SummaryReport> getDnaOtsList(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport>list = new ArrayList<SummaryReport>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT concat(title,' ',firstname,' ',surname),diaryuserid,third_party_name_id ");
		sql.append("FROM apm_available_slot inner join apm_patient on ");
		sql.append("apm_patient.id = apm_available_slot.clientid ");
		sql.append("where commencing between '"+fromDate+"' and '"+toDate+"' and dna=1 ");
		sql.append("and apm_available_slot.whopay='Third Party' order by firstname ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setClientname(rs.getString(1));
				
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile profile = userProfileDAO.getUserprofileDetails(rs.getInt(2));
				summaryReport.setPractitionerName(profile.getInitial() + " " + profile.getFirstname() + " " + profile.getLastname());
				
				ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
				ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(rs.getString(3));
				summaryReport.setTpName(thirdParty.getCompanyName());
				
				
				list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	
	public ArrayList<SummaryReport> getTotalPtsList(String fromDate,
			String toDate, String clinicName, String payby, String diaryUser,
			String location, String thirdParty, String condition,
			String apmtType) {
	
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport>list = new ArrayList<SummaryReport>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_available_slot.whopay,third_party_name_id,diaryuserid,apmttypetext, ");
		sql.append("concat(title,' ',apm_patient.firstname,' ',apm_patient.surname),apm_available_slot.location,condition_id,count(apm_available_slot.id)  FROM apm_available_slot ");
		sql.append("inner join apm_invoice on apm_available_slot.id = apm_invoice.appointmentid ");
		sql.append("inner join apm_patient on apm_patient.id = apm_available_slot.clientid ");
		sql.append("where apm_available_slot.status = 0 and apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' and policy_excess=0 ");
		
		
		if(payby!=null){
			
		
			//5
			 if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0") && !condition.equals("0") && !apmtType.equals("0")){
				sql.append("and apm_available_slot.whopay = '"+payby+"' and diaryuserid = "+diaryUser+" and apm_available_slot.location="+location+" and condition_id="+condition+" and aptmtype="+apmtType+"  ");
			}
			 
				//4
				else if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0") && !condition.equals("0")){
					sql.append("and apm_available_slot.whopay = '"+payby+"' and diaryuserid = "+diaryUser+" and apm_available_slot.location="+location+" and condition_id="+condition+" ");
				}else if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0") && !apmtType.equals("0")){
					sql.append("and apm_available_slot.whopay = '"+payby+"' and diaryuserid = "+diaryUser+" and apm_available_slot.location="+location+" and aptmtype="+apmtType+" ");
				}
			 
				//3
				else if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0")){
					sql.append("and apm_available_slot.whopay = '"+payby+"' and diaryuserid = "+diaryUser+" and apm_available_slot.location="+location+" ");
				}else if(!payby.equals("0") && !diaryUser.equals("0") && !condition.equals("0")){
					sql.append("and apm_available_slot.whopay = '"+payby+"' and diaryuserid = "+diaryUser+" and condition_id="+condition+" ");
				}else if(!payby.equals("0") && !diaryUser.equals("0") && !apmtType.equals("0")){
					sql.append("and apm_available_slot.whopay = '"+payby+"' and diaryuserid = "+diaryUser+" and aptmtype="+apmtType+" ");
				}
			 
			//2
				else if(!payby.equals("0") && !diaryUser.equals("0")){
					sql.append("and apm_available_slot.whopay = '"+payby+"' and diaryuserid = "+diaryUser+" ");
				}else if(!payby.equals("0") && !location.equals("0")){
					sql.append("and apm_available_slot.whopay = '"+payby+"' and apm_available_slot.location = "+location+" ");
				}else if(!payby.equals("0") && !condition.equals("0")){
					sql.append("and apm_available_slot.whopay = '"+payby+"' and condition_id = "+condition+" ");
				}else if(!payby.equals("0") && !apmtType.equals("0")){
					sql.append("and apm_available_slot.whopay = '"+payby+"' and aptmtype = "+apmtType+" ");
				}
			 
				//1
			 if(!payby.equals("0")){
				sql.append("and apm_available_slot.whopay = '"+payby+"' ");
			}else if(!diaryUser.equals("0")){
				sql.append("and diaryuserid = "+diaryUser+" ");
			}else if(!location.equals("0")){
				sql.append("and apm_available_slot.location = "+location+" ");
			}else if(!condition.equals("0")){
				sql.append("and condition_id = "+condition+" ");
			}else if(!apmtType.equals("0")){
				sql.append("and aptmtype = "+apmtType+" ");
			}
			
		}
		
		
		
		sql.append("group by condition_id,diaryuserid,aptmtype,apm_available_slot.location,apm_available_slot.whopay ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			int total = 0;
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setPayby(rs.getString(1));
				
				if(rs.getString(2)!=null){
					ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
					ThirdParty tp = thirdPartyDAO.getThirdPartyDetails(rs.getString(2));
					summaryReport.setTpName(tp.getCompanyName());
				}
				
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile profile = userProfileDAO.getUserprofileDetails(rs.getInt(3));
				summaryReport.setPractitionerName(profile.getInitial() + " " + profile.getFirstname() + " " + profile.getLastname());
				
				summaryReport.setApmttypetext(rs.getString(4));
				summaryReport.setClientname(rs.getString(5));
				
				ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
				Clinic clinic = new Clinic();
				clinic = clinicListDAO.getLocationDetails(rs.getInt(6));
				String lname = clinic.getCity() + " ("+clinic.getLocationname()+")";
				summaryReport.setLocation(clinic.getCity());
				
				TreatmentTypeDAO treatmentTypeDAO = new JDBCTreatmentTypeDAO(connection);
				TreatmentType treatmentType = treatmentTypeDAO.getTreatmentType(rs.getInt(7), new TreatmentType());
				summaryReport.setCondition(treatmentType.getTreatmentName());
				
				summaryReport.setCount(rs.getString(8));
				
				total = total + rs.getInt(8);
				summaryReport.setTotalPts(Integer.toString(total));
				
				list.add(summaryReport);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	
	
	
	public ArrayList<SummaryReport> getReturingPtsList(String fromDate,String toDate, String diaryUser,
			String location, String condition, String sourceOfIntro,
			String referal, String orderby, String order) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport>list = new ArrayList<SummaryReport>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT concat(initial,' ',apm_user.firstname,' ',lastname),apm_clinic_location.city,concat(title,' ',apm_patient.firstname,' ',surname),apm_condition.name,apm_sourceofintro.sourceofintro,reference.name,treatmentEpisodeId ");
		sql.append("FROM apm_patient ");
		sql.append("inner join apm_available_slot on  apm_available_slot.clientid = apm_patient.id ");
		sql.append("inner join apm_clinic_location on apm_clinic_location.id = apm_available_slot.location ");
		sql.append("inner join apm_user on apm_user.id = apm_available_slot.diaryuserid ");
		sql.append("inner join apm_condition on apm_condition.id = apm_available_slot.condition_id ");
		sql.append("inner join apm_sourceofintro on apm_sourceofintro.id = apm_patient.sourceofintro ");
		sql.append("inner join reference on reference.id = apm_patient.refrence ");
		sql.append("where commencing between '"+fromDate+"' and '"+toDate+"' and usedsession = 1 ");
		
		if(diaryUser!=null){
			//5
			if(!diaryUser.equals("0") && !location.equals("0") && !condition.equals("0") && !sourceOfIntro.equals("0") && !referal.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and condition_id="+condition+" and apm_patient.sourceofintro="+sourceOfIntro+" and refrence="+referal+"  ");
			}
			
			//4
			else if(!diaryUser.equals("0") && !location.equals("0") && !condition.equals("0") && !sourceOfIntro.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and condition_id="+condition+" and apm_patient.sourceofintro="+sourceOfIntro+" ");
			}else if(!diaryUser.equals("0") && !location.equals("0") && !condition.equals("0") && !referal.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and condition_id="+condition+" and refrence="+referal+" ");
			}
			
			//3
			else if(!diaryUser.equals("0") && !location.equals("0") && !condition.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and condition_id="+condition+" ");
			}else if(!diaryUser.equals("0") && !location.equals("0") && !sourceOfIntro.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and apm_patient.sourceofintro="+sourceOfIntro+" ");
			}else if(!diaryUser.equals("0") && !location.equals("0") && !referal.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and refrence="+referal+" ");
			}
			
			//2
			else if(!diaryUser.equals("0") && !location.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" ");
			}else if(!diaryUser.equals("0") && !condition.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and condition_id="+condition+" ");
			}else if(!diaryUser.equals("0") && !sourceOfIntro.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and apm_patient.sourceofintro="+sourceOfIntro+" ");
			}else if(!diaryUser.equals("0") && !referal.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" and refrence="+referal+" ");
			}
			
			//1
			else if(!diaryUser.equals("0")){
				sql.append("and diaryuserid="+diaryUser+" ");
			}else if(!location.equals("0")){
				sql.append("and apm_available_slot.location="+location+" ");
			}else if(!condition.equals("0")){
				sql.append("and condition_id="+condition+" ");
			}else if(!sourceOfIntro.equals("0")){
				sql.append("and apm_patient.sourceofintro="+sourceOfIntro+" ");
			}else if(!referal.equals("0")){
				sql.append("and refrence="+referal+" ");
			}
		}
		
		
		sql.append("order by "+orderby+"  "+order+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setPractitionerName(rs.getString(1));
				summaryReport.setLocation(rs.getString(2));
				summaryReport.setClientname(rs.getString(3));
				summaryReport.setCondition(rs.getString(4));
				summaryReport.setSourceofintro(rs.getString(5));
				summaryReport.setReferal(rs.getString(6));
				
				String tmentstartdate = getTreatmentStartDate(rs.getInt(7));
				if(tmentstartdate!=null){
					summaryReport.setTmentstartdate(DateTimeUtils.getCommencingDate1(tmentstartdate));
				}else{
					summaryReport.setTmentstartdate("");
				}
				
				
				list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	private String getTreatmentStartDate(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT startdate FROM apm_treatment_episode where id = "+id+" ";
		
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

	
	public ArrayList<SummaryReport> getODReportList(String fromDate,
			String toDate, String payby, String diaryUser, String location,
			String thirdParty, String dischrgeOutcomes, String dischargeStatus,
			String discharge,String ipdopd,String balancelimit,int access,String admitordischrage,LoginInfo loginInfo) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport>list = new ArrayList<SummaryReport>();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		StringBuffer sql = new StringBuffer();
		if(ipdopd.equals("OPD")){
		sql.append("SELECT concat(initial,' ',apm_user.firstname,' ',lastname),apm_user.discription,concat(title,' ',apm_patient.firstname,' ',surname),apm_available_slot.whopay, ");
		sql.append("apm_available_slot.status,dna,apmslotid,apm_patient.third_party_name_id,apm_available_slot.clientid,apm_patient.abrivationid,apm_patient.mobno,apm_patient.emergencyContNo ");
		sql.append("FROM apm_patient ");
		sql.append("inner join apm_available_slot on  apm_available_slot.clientid = apm_patient.id ");
		sql.append("inner join apm_clinic_location on apm_clinic_location.id = apm_available_slot.location ");
		sql.append("inner join apm_user on apm_user.id = apm_available_slot.diaryuserid ");
		//sql.append("inner join apm_treatment_episode on apm_treatment_episode.id = apm_available_slot.treatmentEpisodeId ");
		sql.append("where commencing between '"+fromDate+"' and '"+toDate+"' ");
		//sql.append("and apm_available_slot.usedsession = 1 ");
		
	
		
		if(payby!=null){
			
			
			//6
			 if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0") && !dischrgeOutcomes.equals("0") && !dischargeStatus.equals("0") && !discharge.equals("All")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and outcomes="+dischrgeOutcomes+" and dschargestatus="+dischargeStatus+" and treatmentstatus="+discharge+" ");
			}
			
			//5
			else if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0") && !dischrgeOutcomes.equals("0") && !dischargeStatus.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and outcomes="+dischrgeOutcomes+" and dschargestatus="+dischargeStatus+"  ");
			}else if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0") && !dischrgeOutcomes.equals("0") && !discharge.equals("All")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and outcomes="+dischrgeOutcomes+" and treatmentstatus="+discharge+"  ");
			}
			
			//4
			else if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0") && !dischrgeOutcomes.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and outcomes="+dischrgeOutcomes+" ");
			}else if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0") && !dischargeStatus.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and dschargestatus="+dischargeStatus+" ");
			}else if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0") && !discharge.equals("All")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" and treatmentstatus="+discharge+" ");
			}
			
			//3
			else if(!payby.equals("0") && !diaryUser.equals("0") && !location.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and apm_available_slot.location="+location+" ");
			}else if(!payby.equals("0") && !diaryUser.equals("0") && !dischrgeOutcomes.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and outcomes="+dischrgeOutcomes+" ");
			}else if(!payby.equals("0") && !diaryUser.equals("0") && !dischargeStatus.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and dschargestatus="+dischargeStatus+" ");
			}else if(!payby.equals("0") && !diaryUser.equals("0") && !discharge.equals("All")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" and treatmentstatus="+discharge+" ");
			}
			
			//2
			else if(!payby.equals("0") && !diaryUser.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.diaryuserid="+diaryUser+" ");
			}else if(!payby.equals("0") && !location.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and apm_available_slot.location="+location+" ");
			}else if(!payby.equals("0") && !dischrgeOutcomes.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and outcomes="+dischrgeOutcomes+" ");
			}else if(!payby.equals("0") && !dischargeStatus.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and dschargestatus="+dischargeStatus+" ");
			}else if(!payby.equals("0") && !discharge.equals("All")){
				sql.append("and apm_available_slot.whopay='"+payby+"' and treatmentstatus="+discharge+" ");
			}
			
			//1
			else if(!payby.equals("0")){
				sql.append("and apm_available_slot.whopay='"+payby+"' ");
				
			}
			/*else if(!discharge.equals("All") && !diaryUser.equals("0") ){
				sql.append("and apm_available_slot.diaryuserid="+diaryUser+" ");
			}*/
			else if(!diaryUser.equals("0")){
				sql.append("and apm_available_slot.diaryuserid="+diaryUser+" ");
			}else if(!location.equals("0")){
				sql.append("and apm_available_slot.location="+location+" ");
			}else if(!dischrgeOutcomes.equals("0")){
				sql.append("and outcomes="+dischrgeOutcomes+" ");
			}else if(!dischargeStatus.equals("0")){
				sql.append("and dschargestatus="+dischargeStatus+" ");
			} /*
				 * else if(!discharge.equals("All")){
				 * sql.append("and treatmentstatus="+discharge+" "); }
				 */
			
		}
		
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setPractitionerName(rs.getString(1));
				String loc= rs.getString(2);
				Master master = masterDAO.getDisciplineData(loc);
				summaryReport.setLocation(master.getDiscipline());
				summaryReport.setClientname(rs.getString(3));
				summaryReport.setPayby(rs.getString(4));
				
				summaryReport.setDischarge("");
				
				summaryReport.setOutcomes("");
				summaryReport.setDschargeStatus("");
				
			/*	if(rs.getInt(6)!=0){
					String outcomes = getDischargeOutComes(rs.getString(6));
					summaryReport.setOutcomes(outcomes);
				}else{
					summaryReport.setOutcomes("");
				}
			
				if(rs.getInt(7)!=0){
					String dschargeStatus  = getDischargeStatus(rs.getString(7));
					summaryReport.setDschargeStatus(dschargeStatus);
				}else{
					summaryReport.setDschargeStatus("");
				}
				*/
				if(rs.getString(8)!=null){
					
					ThirdParty tp = thirdPartyDAO.getThirdPartyDetails(rs.getString(8));
					summaryReport.setTpName(tp.getCompanyName());
				}else{
					summaryReport.setTpName("");
				}
				
				
				
				String clientid = rs.getString(9);
				Client client = clientDAO.getClientDetails(clientid);
				summaryReport.setGender(client.getGender());
				summaryReport.setClientId(clientid);
				summaryReport.setAbrivationid(client.getAbrivationid());
				String age = DateTimeUtils.getAge(client.getDob());
				summaryReport.setClientage(age);
				summaryReport.setMobno(client.getMobNo());
				summaryReport.setAddress(client.getAddress());
				
				summaryReport.setWardbed("");
				summaryReport.setRefferedby("");
				
				String type = "opd";
				int whopay = 0;
				double selfcharge = getSelfOpdCharge(clientid,fromDate,toDate,type,whopay);
				summaryReport.setSelfcharge(DateTimeUtils.changeFormat(selfcharge));
				
				 type = "opd";
				 whopay = 1;
				double tpcharge = getSelfOpdCharge(clientid,fromDate,toDate,type,whopay);
				summaryReport.setTpcharge(DateTimeUtils.changeFormat(tpcharge));
				
				double advance = getAdvanceAmount(fromDate,toDate,clientid);
				summaryReport.setAdvance(DateTimeUtils.changeFormat(advance));
				
				double cashreceived = getCashReceived(fromDate,toDate,clientid);
				summaryReport.setCashreceived(DateTimeUtils.changeFormat(cashreceived));
				
				double totaldebit = getSelefBalance(clientid,fromDate,toDate);
				double selefbalance = selfcharge - (cashreceived + advance);
				summaryReport.setSelefbalance(DateTimeUtils.changeFormat(selefbalance));
				
				summaryReport.setAbrivationid(rs.getString(10));
				summaryReport.setMobno(rs.getString(11));
				summaryReport.setEmergencycontno(rs.getString(12));
				list.add(summaryReport);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		}else{
	
		ArrayList<SummaryReport>ipdlist = getIpdDischargeReport( fromDate,
				 toDate,  payby,  diaryUser,  location,
				 thirdParty,  dischrgeOutcomes,  dischargeStatus,
				 discharge,ipdopd,balancelimit,access,admitordischrage,loginInfo);
		
//		if(ipdopd.equals("IPD")){
//			
			return ipdlist;
//		}
		}
		/*if(ipdopd!=null){
			if(ipdopd.equals("0")){
				list.addAll(ipdlist);
				return list;
			}else if(ipdopd.equals("IPD")){
				return ipdlist;
			}else if(ipdopd.equals("OPD")){
				return list;
			}
			
		}else{
			list.addAll(ipdlist);
			return list;
		}*/
		
		
		return list;
	}

	private double getSelefBalance(String clientid,String froomdate,String todate) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select debit,discount,discamt,disctype from apm_charges_invoice " +
				"where clientid = "+clientid+" and payby='Client' and date between '"+froomdate+"' and '"+todate+"' ";
		
		double debittotal = 0;
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double debit = rs.getDouble(1);
				double discount = rs.getDouble(2);
				double discamt = rs.getDouble(3);
				int disctype = rs.getInt(4);
				
				double d = (debit*discount)/100;
				if(disctype==1){
					d = discamt;
				}
				debittotal = debit - d;
				
				result = result + debittotal;
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private double getCashReceived(String fromDate, String toDate,
			String clientid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		toDate = toDate + " 23:59:59";
		String sql = "select sum(payment) from apm_charges_payment where clientid = "+clientid+"  and tpid = 0  " +
				" and  date between '"+fromDate+"' and '"+toDate+"'   ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public double getAdvanceAmount(String fromDate, String toDate,
			String clientid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		toDate = toDate + " 23:59:59";
		String sql = "SELECT sum(balance) FROM apm_credit_account where client_id = "+clientid+" and payment_mode is not null " +
				" and  date between '"+fromDate+"' and '"+toDate+"' order by id desc  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public double getSelfOpdCharge(String clientid, String fromDate,String todate,String type,int payby){
		PreparedStatement preparedStatementyt = null;
		double result = 0;
		String sql = "";
		if(type.equals("opd")){
		 sql = "SELECT sum(charge*quantity) FROM apm_invoice_assesments where clientid = "+clientid+" and appointmentid!=0 and paybuy="+payby+" " +
				"and commencing between '"+fromDate+"' and '"+todate+"' ";
		}else{
			sql = "SELECT sum(charge*quantity) FROM apm_invoice_assesments where clientid = "+clientid+"  and paybuy="+payby+" " +
			"and commencing between '"+fromDate+"' and '"+todate+"' and commencing!='' group by clientid ";
		}
		
		try{
			preparedStatementyt = connection.prepareStatement(sql);
			ResultSet rs = preparedStatementyt.executeQuery();
			while(rs.next()){
				//result = result + (rs.getDouble(1)*rs.getInt(2));
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	
		return result;
	}

	private ArrayList<SummaryReport> getIpdDischargeReport(String fromDate,
			String toDate, String payby, String diaryUser, String location,
			String thirdParty, String dischrgeOutcomes, String dischargeStatus,
			String discharge,String ipdopd,String balancelimit, int access,String admitordischrage,LoginInfo loginInfo) {
		
		if(toDate!=null){
			toDate = toDate + " 23:59:59";
		}
		if(loginInfo.isAyushman()) {
			admitordischrage="2";
		}
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport>list = new ArrayList<SummaryReport>();
		ArrayList<SummaryReport>balancelimitlist = new ArrayList<SummaryReport>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT concat(initial,' ',apm_user.firstname,' ',lastname),apm_patient.town,concat(title,' ',apm_patient.firstname,' ',surname), ");
		sql.append("apm_patient.whopay, treatmentstatus,outcomes,dschargestatus,apm_patient.third_party_name_id,ipd_addmission_form_new.id,admissiondsate,ipd_addmission_form_new.clientid,ipd_addmission_form_new.wardid,bedid,apm_patient.mobno,apm_patient.emergencyContNo,ipdseqno,apm_user.discription ");
		sql.append("FROM apm_patient ");
		sql.append("inner join ipd_addmission_form_new on  ipd_addmission_form_new.clientid = apm_patient.id ");
		sql.append("inner join apm_user on apm_user.id = ipd_addmission_form_new.practitionerid ");
		sql.append("inner join apm_treatment_episode on apm_treatment_episode.id = ipd_addmission_form_new.treatmentEpisodeId ");
		if(!admitordischrage.equals("1")) {
	    sql.append("where apm_treatment_episode.dischargedate between '"+fromDate+"' and '"+toDate+"'  and casualty='0' ");
	    }else {
		sql.append("where ipd_addmission_form_new.admissiondsate between '"+fromDate+"' and '"+toDate+"'  and casualty='0' ");
        }
		//sql.append("where apm_treatment_episode.dischargedate between '"+fromDate+"' and '"+toDate+"'  and casualty='0' ");
		
		
	
		
		if(payby!=null){
			
			
			//6
			 if(!payby.equals("0") && !diaryUser.equals("0")  && !dischrgeOutcomes.equals("0") && !dischargeStatus.equals("0") && !discharge.equals("All")){
				sql.append("and apm_patient.whopay='"+payby+"' and ipd_addmission_form_new.practitionerid="+diaryUser+"  and outcomes="+dischrgeOutcomes+" and dschargestatus="+dischargeStatus+" and treatmentstatus="+discharge+" ");
			}
			
			//5
			else if(!payby.equals("0") && !diaryUser.equals("0")  && !dischrgeOutcomes.equals("0") && !dischargeStatus.equals("0")){
				sql.append("and apm_patient.whopay='"+payby+"' and ipd_addmission_form_new.practitionerid="+diaryUser+"  and outcomes="+dischrgeOutcomes+" and dschargestatus="+dischargeStatus+"  ");
			}else if(!payby.equals("0") && !diaryUser.equals("0")  && !dischrgeOutcomes.equals("0") && !discharge.equals("All")){
				sql.append("and apm_patient.whopay='"+payby+"' and ipd_addmission_form_new.practitionerid="+diaryUser+"  and outcomes="+dischrgeOutcomes+" and treatmentstatus="+discharge+"  ");
			}
			
			//4
			/*else if(!payby.equals("0") && !diaryUser.equals("0")  && !dischrgeOutcomes.equals("0")){
				sql.append("and apm_patient.whpay='"+payby+"' and ipd_addmission_form.practitionerid="+diaryUser+"  and outcomes="+dischrgeOutcomes+" ");
			}else if(!payby.equals("0") && !diaryUser.equals("0")  && !dischargeStatus.equals("0")){
				sql.append("and apm_patient.whopay='"+payby+"' and ipd_addmission_form.practitionerid="+diaryUser+"  and dschargestatus="+dischargeStatus+" ");
			}else if(!payby.equals("0") && !diaryUser.equals("0")  && !discharge.equals("All")){
				sql.append("and apm_patient.whopay='"+payby+"' and ipd_addmission_form.practitionerid="+diaryUser+"  and treatmentstatus="+discharge+" ");
			}*/
			
			//3
			else if(!payby.equals("0") && !diaryUser.equals("0") && !dischrgeOutcomes.equals("0")){
				sql.append("and apm_patient.whopay='"+payby+"' and ipd_addmission_form_new.practitionerid="+diaryUser+" and outcomes="+dischrgeOutcomes+" ");
			}else if(!payby.equals("0") && !diaryUser.equals("0") && !dischargeStatus.equals("0")){
				sql.append("and apm_patient.whopay='"+payby+"' and ipd_addmission_form.practitionerid="+diaryUser+" and dschargestatus="+dischargeStatus+" ");
			}else if(!payby.equals("0") && !diaryUser.equals("0") && !discharge.equals("All")){
				sql.append("and apm_patient.whopay='"+payby+"' and ipd_addmission_form_new.practitionerid="+diaryUser+"  ");
			}
			
			//2
			else if(!payby.equals("0") && !diaryUser.equals("0")){
				sql.append("and apm_patient.whopay='"+payby+"' and ipd_addmission_form_new.practitionerid="+diaryUser+" ");
			}else if(!payby.equals("0") && !dischrgeOutcomes.equals("0")){
				sql.append("and apm_patient.whopay='"+payby+"' and outcomes="+dischrgeOutcomes+" ");
			}else if(!payby.equals("0") && !dischargeStatus.equals("0")){
				sql.append("and apm_patient.whopay='"+payby+"' and dschargestatus="+dischargeStatus+" ");
			}else if(!payby.equals("0") && !discharge.equals("All")){
				sql.append("and apm_patient.whopay='"+payby+"'  ");
			}
			else if(!diaryUser.equals("0") && !discharge.equals("All")) {
				sql.append("and ipd_addmission_form_new.practitionerid="+diaryUser+" ");
				
			}
			//1
			else if(!payby.equals("0")){
				sql.append("and apm_patient.whopay='"+payby+"' ");
			}else if(!diaryUser.equals("0")){
				sql.append("and ipd_addmission_form_new.practitionerid="+diaryUser+" ");
			
			}
			else if(!dischrgeOutcomes.equals("0")){
				sql.append("and outcomes="+dischrgeOutcomes+" ");
			}else if(!dischargeStatus.equals("0")){
				sql.append("and dschargestatus="+dischargeStatus+" ");
			} /*
				 * else if(!discharge.equals("All")){
				 * sql.append("and treatmentstatus="+discharge+" "); }
				 */
			
		}
		if(admitordischrage.equals("1")) {
		  if(!discharge.equals("0")){
			sql.append("and bedid=0 ");
		}else {
			sql.append("and bedid!=0 ");
		 }
		
		}	  
		sql .append(" order by apm_patient.fullname ");
		
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setPractitionerName(rs.getString(1));
				summaryReport.setLocation("");
				summaryReport.setClientname(rs.getString(3));
				summaryReport.setPayby(rs.getString(4));
				summaryReport.setDischarge(rs.getString(5));
				
				String loc= rs.getString(17);
				Master master = masterDAO.getDisciplineData(loc);
				summaryReport.setLocation(master.getDiscipline());
				
				if(rs.getInt(6)!=0){
					String outcomes = getDischargeOutComes(rs.getString(6));
					summaryReport.setOutcomes(outcomes);
				}else{
					summaryReport.setOutcomes("");
				}
			
				if(rs.getInt(7)!=0){
					String dschargeStatus  = getDischargeStatus(rs.getString(7));
					summaryReport.setDschargeStatus(dschargeStatus);
				}else{
					summaryReport.setDschargeStatus("");
				}
				
				if(rs.getString(8)!=null){
					
					ThirdParty tp = thirdPartyDAO.getThirdPartyDetails(rs.getString(8));
					summaryReport.setTpName(tp.getCompanyName());
				}else{
					summaryReport.setTpName("");
				}
				
				//summaryReport.setAdmissiondate(rs.getString(10));
				
				//irfan 27 jan 2018
//				String admissionid = rs.getString(9);
//				ClientDAO clientDAO = new JDBCClientDAO(connection);
//				String dischargedate = clientDAO.getIpdDischargeDate(admissionid);
//				if(dischargedate!=null){
//					summaryReport.setDischargedate(dischargedate);
//					summaryReport.setAdmissiondate("("+rs.getString(10) + " - " + dischargedate+")");
//				}else{
//					summaryReport.setDischargedate("");
//					summaryReport.setAdmissiondate("("+rs.getString(10)+")");
//				}
//				
				String admissionid = rs.getString(9);
				summaryReport.setIpdid(rs.getString(9));
				
				String dischargedate = clientDAO.getIpdDischargeDate(admissionid);
				if(dischargedate!=null){
					summaryReport.setDischargedate(dischargedate);
					//	summaryReport.setAdmissiondate("("+rs.getString(10) + " - " + dischargedate+")");
						String datestr[]= rs.getString(10).split(" ");
						summaryReport.setAdmissiondate(datestr[0]);
						summaryReport.setTime(datestr[1]);
				}else{
					summaryReport.setDischargedate("");
					String datestr[]= rs.getString(10).split(" ");
					summaryReport.setAdmissiondate(datestr[0]);
					summaryReport.setTime(datestr[1]);
				}

				summaryReport.setAdmissiondate(DateTimeUtils.getCommencingDate1(summaryReport.getAdmissiondate()));
				
				String clientid = rs.getString(11);
				Client client = clientDAO.getClientDetails(clientid);
				summaryReport.setGender(client.getGender());
				summaryReport.setClientId(clientid);
				summaryReport.setAbrivationid(client.getAbrivationid());
				String age = DateTimeUtils.getAge(client.getDob());
				summaryReport.setClientage(age);
				summaryReport.setMobno(client.getMobNo());
				summaryReport.setAddress(client.getAddress());
				
				
				String wardname = ipdDAO.getIpdWardName(rs.getString(12));
				String bedname = ipdDAO.getIpdBedName(rs.getString(13));
				String refferedby = getreferby(rs.getString(11));
				
				summaryReport.setWardbed(wardname + " / " +bedname);
				summaryReport.setRefferedby(refferedby);
				summaryReport.setMobno(rs.getString(14));
				summaryReport.setEmergencycontno(rs.getString(15));
				if(access==1){
						   String newipdabbr=ipdDAO.getIpdAbrivationIds(rs.getInt(16));
					summaryReport.setNewipdabbr(newipdabbr);
					summaryReport.setIpdseqno(rs.getString(16));
				}else{
				summaryReport.setIpdseqno(rs.getString(16));
				summaryReport.setNewipdabbr(rs.getString(16));
				}
				String type = "ipd";
				int whopay = 0;
				/*double selfcharge = getSelfOpdCharge(clientid,fromDate,toDate,type,whopay);*/
				double selfcharge= getChargesComplete(summaryReport.getIpdid(), "0");
				summaryReport.setSelfcharge(DateTimeUtils.changeFormat(selfcharge));
				
				 type = "ipd";
				 whopay = 1;
				/*double tpcharge = getSelfOpdCharge(clientid,fromDate,toDate,type,whopay);*/
				double tpcharge= getChargesComplete(summaryReport.getIpdid(), "1");
				summaryReport.setTpcharge(DateTimeUtils.changeFormat(tpcharge));
				
				
				/*double advance = getAdvanceAmount(fromDate,toDate,clientid);*/
				double advance= getAdvanceByIPD(summaryReport.getIpdid());
				summaryReport.setAdvance(DateTimeUtils.changeFormat(advance));
				//lok
				/*double cashreceived = getCashReceived(fromDate,toDate,clientid);*/
				double cashreceived= getcashRecivedByIPDID(summaryReport.getIpdid());
				summaryReport.setCashreceived(DateTimeUtils.changeFormat(cashreceived));
				
				
				/*double totaldebit = getSelefBalance(clientid,fromDate,toDate);*/
				double totaldebit= getSelfBalanceByIPDID(summaryReport.getIpdid());
				summaryReport.setTotalipdamount(Double.toString(totaldebit));
				/*double totaldisc = getPatientTotalDiscount(clientid,fromDate,toDate);*/
				double totaldisc= getPatientTotalDiscountByIPD(summaryReport.getIpdid());
				double advcash = cashreceived + advance;
				double selefbalance = 0;
				double selfcredit = 0;
				
				if(loginInfo.isAyushman()){
					if(advcash>totaldebit){
						selfcredit =   advcash - totaldebit;
						selefbalance =   0;
					}else{
						selefbalance =   totaldebit - advcash;
					}
				}else {
				
				if(advcash>selfcharge){
					selfcredit =   advcash - selfcharge;
					selefbalance =   0;
				}else{
					selefbalance =   selfcharge - advcash;
				}
			  }	
				summaryReport.setSelfcredit(DateTimeUtils.changeFormat(selfcredit));
				summaryReport.setSelefbalance(DateTimeUtils.changeFormat(selefbalance));
				summaryReport.setTotaldiscount(DateTimeUtils.changeFormat(totaldisc));
				if(balancelimit!=null){
					if(!balancelimit.equals("0")){
						if(selefbalance>=Integer.parseInt(balancelimit)){
							balancelimitlist.add(summaryReport);
						}
					}
				}
				
				list.add(summaryReport);
			}
			
			if(balancelimit!=null){
				if(!balancelimit.equals("0")){
					return balancelimitlist;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private String getreferby(String clientid) {
		
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer buffer=new StringBuffer();
		buffer.append("SELECT refferedby FROM ipd_addmission_form where clientid = "+clientid+" ");
		
		try{
			
		    preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	
	}

	private double getPatientTotalDiscount(String clientid, String froomdate, String todate) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select debit,discount,discamt,disctype from apm_charges_invoice " +
				"where clientid = "+clientid+" and payby='Client' and date between '"+froomdate+"' and '"+todate+"' ";
		double debittotal = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double debit = rs.getDouble(1);
				double discount = rs.getDouble(2);
				double discamt = rs.getDouble(3);
				int disctype = rs.getInt(4);
				double d = (debit*discount)/100;
				if(disctype==1){
					d = discamt;
				}
				//debittotal = debit - d;
				//result = result + debittotal;
				result = result+d;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getDischargeStatus(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql  = "SELECT name FROM apm_discharge_status where id = "+id+" ";
		
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

	private String getDischargeOutComes(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql  = "SELECT name FROM apm_discharge_outcomes where id = "+id+" ";
		
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

	public ArrayList<Accounts> getChargesShareList(String fromdate, String todate, String practitionerId, String clientid) {
		ArrayList<Accounts> arrayList= new ArrayList<Accounts>();
		try {
			todate = todate + " 23:59:59";
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			StringBuilder builder = new StringBuilder();
			builder.append("select id, pract_id, clientid, chargeid, invoiceid, chargename, datetime, commission_type, amount, remark, userid, all_or_indi, total_charge from his_share_charges ");
			builder.append("where datetime between '"+fromdate+"' and '"+todate+"' ");
			if(practitionerId!=null){
				builder.append("and pract_id ='"+practitionerId+"' ");
			}
			if(practitionerId!=null){
				builder.append("and clientid ='"+clientid+"'");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPractitionerId(rs.getInt(2));
				String fullname = userProfileDAO.getFullName(rs.getString(2));
				accounts.setPractitionerName(fullname);
				accounts.setClientid(rs.getInt(3));
				String clientName = clientDAO.getClientFullName(rs.getString(3));
				accounts.setClientName(clientName);
				accounts.setChargeid(rs.getString(4));
				accounts.setInvoiceid(rs.getInt(5));
				accounts.setCharges(rs.getString(6));
				
				String datetime = rs.getString(7);
				String[] data= datetime.split(" ");
				String datetime1 = DateTimeUtils.getCommencingDate1(data[0]);
				String finaldatetime = datetime1+" "+data[1];
				accounts.setDate(finaldatetime);
				accounts.setCommission_type(rs.getString(8));
				accounts.setAmountx(rs.getString(9));
				accounts.setRemark(rs.getString(10));
				accounts.setUserid(rs.getString(11));
				accounts.setAll_or_indivisual(rs.getString(12));
				accounts.setTotalAmountx(rs.getString(13));
				arrayList.add(accounts);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Accounts> getBillingReport(String fromdate, String todate,String itype,String payee) {
		ArrayList<Accounts> arrayList= new ArrayList<Accounts>();
		ChargesAccountProcessingDAO accountProcessingDAO=new JDBCChargeAccountProcesDAO(connection);
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		try {
			todate = todate + " 23:59:59";
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			StringBuilder builder = new StringBuilder();
			builder.append("select apm_charges_invoice.id, apm_charges_invoice.date, discount, apm_charges_invoice.debit, apm_charges_invoice.clientid,itype, disctype, discamt, practid, ipdid,abrivationid,fullname ,apm_credit_account.debit,paymode ");
			builder.append("from apm_charges_invoice inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid left join apm_credit_account on apm_credit_account.manualinvoiceid= apm_charges_invoice.id  ");
			builder.append("left join apm_charges_payment on apm_charges_payment.chargeinvoiceid = apm_charges_invoice.id ");
			builder.append("where isdeleted=0 ");		
			
			builder.append("and apm_charges_invoice.date between '"+fromdate+"' and '"+todate+"' ");
			if(!itype.equals("0")){
				builder.append("and itype='"+itype+"' ");
			}
			if(payee.equals("2")){
				builder.append(" and apm_charges_invoice.tpid>0");
			}
			if(payee.equals("1")){
				builder.append(" and apm_charges_invoice.tpid=0");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				accounts.setDiscount(rs.getDouble(3));
				accounts.setDebitAmount(rs.getDouble(4));
				accounts.setClientid(rs.getInt(5));
				accounts.setItype(rs.getString(6));
				String invoicetype=getInvoiceTypeByitype(rs.getInt(6));
				accounts.setInvoicetype(invoicetype);
				accounts.setDisctype(rs.getString(7));
				accounts.setDiscAmmount(rs.getString(8));
				accounts.setPractitionerId(rs.getInt(9));
				accounts.setIpdid(rs.getString(10));
				accounts.setAbrivationid(rs.getString(11));
				accounts.setClientName(rs.getString(12));
				String fullname = userProfileDAO.getFullName(rs.getString(9));
				accounts.setTotalref(String.valueOf(rs.getInt(13)));
				accounts.setPractitionerName(fullname);
				double result = 0.0;
				String admissiondate ="";
				String dischargedate="";
				if(rs.getInt(10)>0){
					Ipd ipd = getAddandDisDate(rs.getInt(10));
					
					if(ipd.getAdmissiondate()!=null){
						String[] admissiondatee = ipd.getAdmissiondate().split(" ");
						admissiondate = DateTimeUtils.getCommencingDate1(admissiondatee[0])+" "+admissiondatee[1];
					}
					
					
					if(ipd.getDischargedate()!=null){
						String[] discdate = ipd.getDischargedate().split(" ");
						dischargedate = DateTimeUtils.getCommencingDate1(discdate[0])+" "+discdate[1];
					}
					
					accounts.setAdmissiondate(admissiondate);
					accounts.setDischargedate(dischargedate);
				}else{
					accounts.setAdmissiondate("");
					accounts.setDischargedate("");
				}
				
				double debit = rs.getDouble(4);
				double total = debit;
				double discount = rs.getDouble(3);
				int disctype=rs.getInt(7);
				double discamt= rs.getDouble(8);
//				double r1 = (total*discount)/100;
//				
//				if(disctype==0){
//					discamt= r1;
//				}else{
//					r1= discamt;
//				}
//				
//				total = total-r1;
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				total = total-r1;
				result = result + total;
				double creditAmount = accountProcessingDAO.getCreditAmount(rs.getDouble(1));
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				creditAmount = creditAmount - refundAmt;
				double amountpaid = getAllPaidAmout(accounts.getId());
//				double balance = debit - amountpaid;
				double balance = result - creditAmount;
				accounts.setBalance(balance);
				accounts.setAmountx(String.valueOf(amountpaid));
					
				if(rs.getString(7).equals("0")){
					accounts.setDiscountx(rs.getString(3));
					accounts.setDiscountamtt(r1);
				}else{
					accounts.setDiscountx(rs.getString(8));
				}
				
				AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			    int ipdopdseq=accountsDAO.getIpdOpdSeqNo(rs.getInt(1));
			    accounts.setIpdopdseq(String.valueOf(ipdopdseq));
				accounts.setPaymentmode(rs.getString(14));
				
				accounts.setIpdopdseq(accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(1)));
				
				arrayList.add(accounts);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getInvoiceTypeByitype(int int1) {
		String res="";
		try {
			String sql ="select type from apm_invoice_type where id='"+int1+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public double getAllPaidAmout(int id) {
		double total =0;
		try {
			String sql ="select sum(payment) from apm_charges_payment where chargeinvoiceid='"+id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public Ipd getAddandDisDate(int ipdid) {
		Ipd ipd = new Ipd();
		try {
			StringBuffer buffer = new  StringBuffer();
			buffer.append("select admissiondsate,dischargedate from ipd_addmission_form ");
			buffer.append("inner join apm_treatment_episode on apm_treatment_episode.id =ipd_addmission_form.treatmentepisodeid ");
			buffer.append("where ipd_addmission_form.id='"+ipdid+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ipd.setAdmissiondate(rs.getString(1));
				ipd.setDischargedate(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipd;
	}

	public ArrayList<Accounts> getDiscountReport(String fromdate, String todate, String itype) {
		ArrayList<Accounts> arrayList= new ArrayList<Accounts>();
		try {
			todate = todate + " 23:59:59";
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			StringBuilder builder = new StringBuilder();
			builder.append("select apm_charges_invoice.id, date, discount, debit, clientid,itype, disctype, discamt, practid, ipdid,abrivationid,fullname ");
			builder.append("from apm_charges_invoice inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid where isdeleted=0 ");
			builder.append("and date between '"+fromdate+"' and '"+todate+"' and (discount!=0 or discamt!=0) ");
			if(!itype.equals("")){
				builder.append(" and apm_charges_invoice.itype='"+itype+"'");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			JDBCSummaryReportDAO jdbcSummaryReportDAO=new JDBCSummaryReportDAO(connection);
			double totalbillamount=0.0,totalrecammount=0.0,totaldiscountammount=0.0,totalrefundamount=0.0;;
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				accounts.setDiscount(rs.getDouble(3));
				accounts.setDebitAmount(rs.getDouble(4));
				accounts.setClientid(rs.getInt(5));
				accounts.setItype(rs.getString(6));
				accounts.setDisctype(rs.getString(7));
				accounts.setDiscAmmount(rs.getString(8));
				accounts.setPractitionerId(rs.getInt(9));
				accounts.setIpdid(rs.getString(10));
				accounts.setAbrivationid(rs.getString(11));
				accounts.setClientName(rs.getString(12));
				String fullname = userProfileDAO.getFullName(rs.getString(9));
				accounts.setPractitionerName(fullname);
				
				String admissiondate ="";
				String dischargedate="";
				if(rs.getInt(10)>0){
					Ipd ipd = getAddandDisDate(rs.getInt(10));
					
					if(ipd.getAdmissiondate()!=null){
						String[] admissiondatee = ipd.getAdmissiondate().split(" ");
						admissiondate = DateTimeUtils.getCommencingDate1(admissiondatee[0])+" "+admissiondatee[1];
					}
					
					
					if(ipd.getDischargedate()!=null){
						String[] discdate = ipd.getDischargedate().split(" ");
						dischargedate = DateTimeUtils.getCommencingDate1(discdate[0])+" "+discdate[1];
					}
					
					accounts.setAdmissiondate(admissiondate);
					accounts.setDischargedate(dischargedate);
				}else{
					accounts.setAdmissiondate("");
					accounts.setDischargedate("");
				}
				
				
				
				double debit = rs.getDouble(4);
				double total = debit;
				double discount = rs.getDouble(3);
				int disctype=rs.getInt(7);
				double discamt= rs.getDouble(8);
				double r1 = (total*discount)/100;
				
				if(disctype==0){
					discamt= r1;
				}else{
					r1= discamt;
				}
				
				total = total-r1;
				
				double amountpaid = getAllPaidAmout(accounts.getId());
				   //16/11/2018   showing refund amount 
			    double refundamount=jdbcSummaryReportDAO.getrefundamt(rs.getInt(1));
				double balance = total - (amountpaid-refundamount);
				
				
				accounts.setAmountx(String.valueOf(amountpaid));
					double discount_amount_by_per=0.00;
				if(rs.getString(7).equals("0")){
					discount_amount_by_per=rs.getDouble(4)/100*rs.getDouble(3);
					accounts.setDiscountx(rs.getString(3));
					accounts.setDiscountbyrs(discount_amount_by_per);
					/*balance=discount_amount_by_per-refundamount;
				    accounts.setBalance(balance);*/
				}else{
					discount_amount_by_per=rs.getDouble(8);
					accounts.setDiscountx(String.valueOf(discount_amount_by_per));
					/*balance=rs.getDouble(8)-refundamount;
				    accounts.setBalance(balance);*/
				}
				accounts.setBalance(balance);
				accounts.setRefundamountbyid(refundamount);
				//lokesh
				totalbillamount=totalbillamount+ accounts.getDebitAmount();
				totalrecammount= totalrecammount+Double.parseDouble(accounts.getAmountx());
//				if(!accounts.getDisctype().equals("0")){
					totaldiscountammount= totaldiscountammount+discount_amount_by_per;
//				}
					totalrefundamount=totalrefundamount+refundamount;
				accounts.setTotalbillamount(totalbillamount);
				accounts.setTotalrecammount(totalrecammount);
				accounts.setTotaldiscountammount(totaldiscountammount);
				accounts.setTotalrefundamount(totalrefundamount);
				//showing seqno instead of invoice no   22/09/2018
				
				/*String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
				accounts.setIpdopdseq(ipdopdseq);*/
				accounts.setIpdopdseq(accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(1)));
				 
				    
				arrayList.add(accounts);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	

	public ArrayList<Accounts> getCancelInvoiceReport(String fromdate, String todate, String ipdopdfilter) {
		ArrayList<Accounts> arrayList= new ArrayList<Accounts>();
		try {
			todate = todate + " 23:59:59";
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			StringBuilder builder = new StringBuilder();
			builder.append("select apm_charges_invoice.id, date, discount, debit, clientid,itype, disctype, discamt, practid, ipdid,abrivationid,fullname,cancelNotes, cancelUserid, cancelDT ");
			builder.append("from apm_charges_invoice inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid where isdeleted=1 ");
			builder.append("and date between '"+fromdate+"' and '"+todate+"' ");
			if(!ipdopdfilter.equals("")){
				builder.append(" and itype='"+ipdopdfilter+"'");
			}
				
			
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				accounts.setDiscount(rs.getDouble(3));
				accounts.setDebitAmount(rs.getDouble(4));
				accounts.setClientid(rs.getInt(5));
				accounts.setItype(rs.getString(6));
				accounts.setDisctype(rs.getString(7));
				accounts.setDiscAmmount(rs.getString(8));
				accounts.setPractitionerId(rs.getInt(9));
				accounts.setIpdid(rs.getString(10));
				accounts.setAbrivationid(rs.getString(11));
				accounts.setClientName(rs.getString(12));
				String fullname = userProfileDAO.getFullName(rs.getString(9));
				accounts.setPractitionerName(fullname);
				
				String admissiondate ="";
				String dischargedate="";
				if(rs.getInt(10)>0){
					Ipd ipd = getAddandDisDate(rs.getInt(10));
					
					if(ipd.getAdmissiondate()!=null){
						String[] admissiondatee = ipd.getAdmissiondate().split(" ");
						admissiondate = DateTimeUtils.getCommencingDate1(admissiondatee[0])+" "+admissiondatee[1];
					}
					
					
					if(ipd.getDischargedate()!=null){
						String[] discdate = ipd.getDischargedate().split(" ");
						dischargedate = DateTimeUtils.getCommencingDate1(discdate[0])+" "+discdate[1];
					}
					
					accounts.setAdmissiondate(admissiondate);
					accounts.setDischargedate(dischargedate);
				}else{
					accounts.setAdmissiondate("");
					accounts.setDischargedate("");
				}
				
				
				
				double debit = rs.getDouble(4);
				double total = debit;
				double discount = rs.getDouble(3);
				int disctype=rs.getInt(7);
				double discamt= rs.getDouble(8);
				double r1 = (total*discount)/100;
				
				if(disctype==0){
					discamt= r1;
				}else{
					r1= discamt;
				}
				
				total = total-r1;
				
				double amountpaid = getAllPaidAmout(accounts.getId());
				double balance = total - amountpaid;
				
				accounts.setBalance(balance);
				accounts.setAmountx(String.valueOf(amountpaid));
					
				if(rs.getString(7).equals("0")){
					accounts.setDiscountx(rs.getString(3));
				}else{
					accounts.setDiscountx(rs.getString(8));
				}
				
				accounts.setCancelNotes(rs.getString(13));
				accounts.setCancelUserid(rs.getString(14));
				accounts.setCancelDT(rs.getString(15));
				
				Double debitamount = getDebitAmountFromLog(rs.getInt(1));
				accounts.setDebitAmount(debitamount);
				//showing seqno instead of invoice no   22/09/2018
				
				   /* String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
				    accounts.setIpdopdseq(ipdopdseq);*/
				accounts.setIpdopdseq(accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(1)));
				arrayList.add(accounts);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private Double getDebitAmountFromLog(int int1) {
		double res=0;
		try {
			String sql = "SELECT debit FROM apm_charges_invoice_log where invoiceChargeId='"+int1+"' ";
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

	public ArrayList<Accounts> getChargeReportDepartmentWise(String fromdate, String todate) {
		ArrayList<Accounts> arrayList = new ArrayList<Accounts>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT sum(charge*quantity),department,discipline ");
			buffer.append("FROM apm_invoice_assesments inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			buffer.append("inner join apm_patient on apm_patient.id = apm_invoice_assesments.clientId ");
			buffer.append("inner join apm_discipline on apm_discipline.id =apm_invoice_assesments.department ");
			buffer.append("where commencing between '"+fromdate+"' AND '"+todate+"' and apm_invoice_assesments.active=1 ");
			buffer.append("and apm_invoice_assesments.paybuy='0'   group by department order by sum(charge*quantity) desc ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setTotalAmountx(rs.getString(1));
				accounts.setDepartment(rs.getString(3));
				arrayList.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getDepartmentWiseDebit(String fromdate, String todate, String string) {
		String debit ="0";
		try {
			StringBuffer buffer = new StringBuffer();
			//buffer.append("select sum(debit) from apm_charges_invoice ");
			//buffer.append("where date between '"+fromdate+"' and '"+todate+"' and itype='"+string+"' ");
			
			buffer.append("select sum(debit),apm_discipline.discipline,sum(discamt),discount,disctype from apm_charges_invoice ");
			buffer.append("inner join apm_user on apm_user.id = apm_charges_invoice.practid ");
			buffer.append("inner join apm_discipline on apm_discipline.id =apm_user.discription ");
			buffer.append("where date between '"+fromdate+"' and '"+todate+"' ");
			if(string.equals("0")){
				buffer.append("and (itype='4' or itype='5' or itype='6' or itype='7') ");
			}else{
				buffer.append("and (itype='"+string+"') ");
			}
			
			buffer.append("group by apm_user.discription order by sum(debit) desc ");
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			while (rs.next()) {
				total= total+rs.getDouble(1);
			}
			debit = DateTimeUtils.changeFormat(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return debit;
	}

	public int getTotalBeds(String string) {
		int count =0;
		try {
			String sql ="select count(*) from apm_ipd_bed where casualty="+string+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<NotAvailableSlot> getTotalOTSurgeries(String fromdate, String todate) {
		ArrayList<NotAvailableSlot> arrayList = new ArrayList<NotAvailableSlot>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select procedures,count(*) ");
			buffer.append("from apm_available_slot where  procedures!='0' ");
			buffer.append("and commencing  between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("group by procedures order by count(*) desc ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int total=0;
			while (rs.next()) {
				NotAvailableSlot availableSlot = new NotAvailableSlot();
				availableSlot.setProcedure(rs.getString(1));
				availableSlot.setCount(rs.getInt(2));
				int couunt = rs.getInt(2);
				total = total + couunt;
				availableSlot.setTotal(""+total);
				arrayList.add(availableSlot);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getNetDepartmentWiseDebit(String fromdate, String todate, String string, int i) {
		String debit ="0";
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(payment) from apm_charges_invoice ");
			buffer.append("inner join apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
			buffer.append("inner join apm_charges_payment on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
			buffer.append("where apm_charges_invoice.date between '"+fromdate+"' AND '"+todate+"' ");
			if(i>0){
				if(!string.equals("0")){
					buffer.append("and itype="+string+" ");
				}else{
					buffer.append("and (itype='4' or itype='5' or itype='6' or itype='7') ");
				}	
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				debit = rs.getString(1);
				if(debit==null){
					debit = "0";
				}else{
					if(debit.equals("")){
						debit="0";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return debit;
	}

	public ArrayList<Accounts> getDailyIPDAdmsDischList(String fromdate, String todate) {
		ArrayList<Accounts> arrayList = new ArrayList<Accounts>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id, date, admission, total_admission, discharge, total_discharge from daily_ipd_summary ");
			buffer.append("where date between '"+fromdate+"' and '"+todate+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				
				accounts.setAdmission(rs.getInt(3));
				accounts.setTotaladmission(rs.getInt(4));
				accounts.setDischarge(rs.getInt(5));
				accounts.setTotaldischarge(rs.getInt(6));
				arrayList.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Accounts> getPaymentReportList(String fromdate, String todate,String itype,String howpaid) {
		ArrayList<Accounts> arrayList = new ArrayList<Accounts>();
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		ChargesAccountProcessingDAO processingDAO=new JDBCChargeAccountProcesDAO(connection);
		try {
			fromdate = fromdate +" "+ "00:00:00";
			todate = todate+" "+"59:59:59";
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			StringBuffer buffer = new StringBuffer();
			howpaid=DateTimeUtils.isNull(howpaid);
			buffer.append("select apm_charges_payment.id,chargeinvoiceid,apm_charges_payment.date,payment,paymode,userid,apm_charges_payment.clientid,itype,apm_charges_invoice.ipdid ");
			buffer.append("from apm_charges_payment left join apm_charges_invoice on apm_charges_invoice.id=apm_charges_payment.chargeinvoiceid ");
			buffer.append("left join apm_patient on apm_charges_payment.clientid=apm_patient.id ");
			buffer.append("left join apm_invoice_type on apm_charges_invoice.itype=apm_invoice_type.id ");
			buffer.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"' ");
			if(!itype.equals("0")){
				buffer.append("and itype='"+itype+"' ");
			}
			if(!howpaid.equals("")){
				buffer.append("and paymode='"+howpaid+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double tot = 0;
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setInvoiceid(rs.getInt(2));
				accounts.setIpdopdseq(String.valueOf(accountsDAO.getIpdOpdSeqNo(rs.getInt(2))));
				
				String finseq=accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(2));
				accounts.setBghseqId(finseq);
				
				String invoicedate="";
				if(rs.getString(3)!=null){
					String[] discdate = rs.getString(3).split(" ");
					invoicedate = DateTimeUtils.getCommencingDate1(discdate[0])+" "+discdate[1];
				}
				accounts.setDate(invoicedate);
				accounts.setAmountx(rs.getString(4));
				double payAmt = rs.getDouble(4);
				tot = tot + payAmt;
				accounts.setTotalamt(String.valueOf(tot));
				
				accounts.setPaymentmode(rs.getString(5));
				accounts.setUserid(rs.getString(6));
				accounts.setClientid(rs.getInt(7));
				accounts.setItype(rs.getString(8));
				accounts.setInvoicetype(accountsDAO.getInvoiceName(rs.getString(8)));
				Client client = clientDAO.getClientDetails(rs.getString(7));
				accounts.setClientName(client.getTitle() + " " + client.getFirstName() +" " + client.getLastName());
				accounts.setAbrivationid(client.getAbrivationid());
//				accounts.setPhysical_payment_id(processingDAO.getPhysicalpaymentId(rs.getString(1)));
				
				int ipdid=rs.getInt(9);
				if(ipdid>0){
					String newipdabbr = ipdDAO.getIpdAbrivationIds(ipdid);
		    		accounts.setIpdid(newipdabbr);
				}
				
				
				
				arrayList.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	

	public ArrayList<String> getPaymentreceiveUserid(String fromdate, String todate) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			StringBuffer buffer = new StringBuffer();
			
				todate = todate +" "+ "59:59:59";
				buffer.append("select userid from apm_charges_payment where date between '"+fromdate+"' and '"+todate+"' and paymode!='prepayment' group by userid");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					arrayList.add(rs.getString(1));
				}
				ArrayList<String> arrayList2 = getUserFromRefundPayment(fromdate,todate);
				ArrayList<String> arrayList3 = getUserFromAdvancePayment(fromdate,todate);
				ArrayList<String> arrayList4 = getUserFromInvoice(fromdate,todate);
				arrayList.addAll(arrayList2);
				arrayList.addAll(arrayList3);
				arrayList.addAll(arrayList4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private ArrayList<String> getUserFromInvoice(String fromdate, String todate) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select apm_user.userid ");
			sql.append("from apm_charges_invoice ");
			sql.append("left join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");
			sql.append("inner join apm_user on apm_user.id = apm_charges_invoice.updatedby ");
			sql.append("where apm_charges_invoice.date between '"+fromdate+"' AND '"+todate+"' ");
			sql.append("group by apm_user.userid ");
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				arrayList.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return arrayList;
	}

	private ArrayList<String> getUserFromAdvancePayment(String fromdate, String todate) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			StringBuffer buffer = new StringBuffer();
			
				buffer.append("select userid from apm_credit_account where charge>0 and date between '"+fromdate+"' and '"+todate+"' group by userid");
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

	private ArrayList<String> getUserFromRefundPayment(String fromdate, String todate) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			StringBuffer buffer = new StringBuffer();
		
				buffer.append("select userid from apm_credit_account where advref=1 and date between '"+fromdate+"' and '"+todate+"' group by userid");
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

	public String getTotalPaidByUser(String fromdate, String todate, String userid) {
		String string = "0";
		try {
			todate = todate +" "+ "59:59:59";
			String sql ="select sum((payment+0)+(original_pay+0)) from apm_charges_payment where date between '"+fromdate+"' and '"+todate+"' and paymode!='prepayment' and userid='"+userid+"'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				string = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public String getTotalAdvanceByUser(String fromdate, String todate, String userid) {
		String string = "0";
		try {
			todate = todate +" "+ "59:59:59";
			String sql ="select sum(charge) from apm_credit_account where charge>0 and date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"' and cancelpay=0";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				string = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public String getTotalRefundByUser(String fromdate, String todate, String userid) {
		String string = "0";
		try {
			todate = todate +" "+ "59:59:59";
			String sql ="select sum(charge) from apm_credit_account where advref=1 and date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				string = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public String getTotalPaymentPaymode(String fromdate, String todate, String userid, String paymentmode) {
		String string = "0";
		try {
			todate = todate +" "+ "59:59:59";
			String sql ="select sum((payment+0)+(original_pay+0)) from apm_charges_payment where date between '"+fromdate+"' and '"+todate+"' and paymode!='prepayment' and userid='"+userid+"' and paymode='"+paymentmode+"'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				string = rs.getString(1);
			}if(string==null){
				string ="0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public String getTotalAdvancePaymode(String fromdate, String todate, String userid, String paymentmode) {
		String string = "0";
		try {
			todate = todate +" "+ "59:59:59";
			String sql ="select sum(charge) from apm_credit_account where charge>0 and date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"' and payment_mode='"+paymentmode+"' and cancelpay=0";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				string = rs.getString(1);
			}if(string==null){
				string="0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public String getTotalRefundPaymode(String fromdate, String todate, String userid, String paymentmode) {
		String string = "0";
		try {
			todate = todate +" "+ "59:59:59";
			String sql ="select sum(debit) from apm_credit_account where advref=1 and date between '"+fromdate+"' and '"+todate+"' and userid='"+userid+"' and payment_mode='"+paymentmode+"' ";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				string = rs.getString(1);
			}if(string==null){
				string="0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}


	public ArrayList<SummaryReport> getCurrentPatientsList(String diaryUser, String ward, String fromDate, String toDate, String issecondrey,int days,String orderbypract,int access,String paymentmode,int ismlc, String location) {
		if(toDate!=null){
			toDate = toDate + " 23:59:59";
		}
		
		
		PreparedStatement preparedStatement = null;
		ArrayList<SummaryReport>list = new ArrayList<SummaryReport>();
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT concat(initial,' ',apm_user.firstname,' ',lastname),concat(title,' ',apm_patient.firstname,' ',surname),admissiondsate,ipd_addmission_form.clientid,apm_ipd_ward.wardname ,bedid,apm_diagnosis.name, ipd_addmission_form.secndryconsult, apm_patient.abrivationid,ipd_addmission_form.mlcrefdoctor,bedname,ipd_addmission_form.tpid,ipdseqno,ipd_addmission_form.tpid ");
		sql.append(",apm_patient.dob ,concat(apm_patient.address,', ',apm_patient.town,', ',apm_patient.county,',',apm_patient.country),fathername,apm_patient.third_party_name_id ,apm_patient.birthplace,ipdabrivationid,mothername,mobno    FROM apm_patient ");
		sql.append("inner join ipd_addmission_form on  ipd_addmission_form.clientid = apm_patient.id ");
		sql.append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
		sql.append("inner join apm_treatment_episode on apm_treatment_episode.id = ipd_addmission_form.treatmentEpisodeId ");
		sql.append("inner join apm_ipd_ward on apm_ipd_ward.id = ipd_addmission_form.wardid ");
		sql.append("left join apm_diagnosis on apm_diagnosis.id= ipd_addmission_form.conditionid ");
		sql.append("inner join apm_ipd_bed on apm_ipd_bed.id = ipd_addmission_form.bedid ");
		sql.append(" where (bedid!='0' or bedid is not null) and ipd_addmission_form.casualty='"+location+"' and active ='1'  " );
 
		
		
			if(!diaryUser.equals("0")&&issecondrey.equals("no")){
				sql.append( " and ipd_addmission_form.practitionerid="+diaryUser+" " );
			}

			if(!ward.equals("0")){
				sql.append( " and ipd_addmission_form.wardid="+ward+" " );
			}
			if(issecondrey.equals("yes")){
				sql.append( " and ipd_addmission_form.secndryconsult like '%,"+diaryUser+"' " );
			}
			if(paymentmode.equals("1")){
				sql.append("and whopay='Client'");
			}else if (paymentmode.equals("2")) {
				sql.append("and whopay='Third Party'");
			}
			if(ismlc==1){
				sql.append(" and mlcno!='' ");
			}else if(ismlc==2){
				sql.append(" and mlcno='' ");
			}
			
			if(orderbypract.equals("1")){
				sql.append(" order by  apm_user.firstname asc ");
			}else{
			sql.append(" order by  (ipd_addmission_form.wardid+0) asc , bedname asc ");
			}
			try{
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					SummaryReport summaryReport = new SummaryReport();
					summaryReport.setPractitionerName(rs.getString(1));
				
					summaryReport.setClientname(rs.getString(2));
				
					String ward1=rs.getString(5);
					String bed=rs.getString(6);
					summaryReport.setWardbed(ward1+" / "+ rs.getString(11));
					summaryReport.setAdmissiondate(rs.getString(3));
					summaryReport.setDiagnosis(rs.getString(7));
				String Seconderyconsult= rs.getString(8);
				summaryReport.setAbrivationid(rs.getString(9));
				String mlcdoctor= DateTimeUtils.isNull(rs.getString(10));
				ArrayList<SummaryReport> mlclist= new ArrayList<SummaryReport>();
				ArrayList<SummaryReport> secconlist= new ArrayList<SummaryReport>();
				secconlist= getSeconderyConsultantList(Seconderyconsult);
				summaryReport.setSec_consultantlist(secconlist);	
				mlclist= getMLCConsultantList(mlcdoctor);
				summaryReport.setMlclist(mlclist);
				summaryReport.setTpName(gettpname1(rs.getString(12)));
				summaryReport.setBirthplace(DateTimeUtils.isNull(rs.getString(19)));
				summaryReport.setAge1(DateTimeUtils.getAge1(rs.getString(15)));
				summaryReport.setAddress(rs.getString(16));
				summaryReport.setFathername(DateTimeUtils.isNull(rs.getString(17)));
				summaryReport.setMothername(DateTimeUtils.isNull(rs.getString(17)));
				summaryReport.setMobno(rs.getString(22));
				if(access==1){
					   //commenting beacuse it will slow down report
					   //String newipdabbr=ipdDAO.getIpdAbrivationIdsByClientid((rs.getInt(4)));
					   //summaryReport.setNewipdabbr(newipdabbr);
					summaryReport.setNewipdabbr(rs.getString(20));
					summaryReport.setIpdid(rs.getString(13));
				}else{
				summaryReport.setIpdid(rs.getString(13));
				}
				ClientDAO clientDAO=  new JDBCClientDAO(connection);
				summaryReport.setTpName(clientDAO.getThirdPartyCompanyName(rs.getString(18)));
					if(summaryReport.getAdmissiondate()!=null){
						 String date3= summaryReport.getAdmissiondate();
						 String date[]= date3.split(" ");
						 String date1[]=date[0].split("-");
						  date[0]= date1[2]+"/"+date1[1]+"/"+date1[0];
						  summaryReport.setAdmissiondate(date[0]);
						String df[]=toDate.split(" ");
						String todays=df[0];
						Date d1=new SimpleDateFormat("dd/MM/yyyy").parse(date[0]);
						Date d2= new SimpleDateFormat("yyyy-MM-dd").parse(todays);
						
						long diff=d2.getTime()- d1.getTime();
						long difference= (Math.abs((diff / (1000*60*60*24))));
						
						summaryReport.setAdmitdays(difference);
						int x= (int)difference;
						if(x+1>days){
						list.add(summaryReport);
						}
					}
					else{
						summaryReport.setAdmissiondate(" ");
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		return list;
	}
	public ArrayList<SummaryReport> getWardlist(){
		PreparedStatement ps= null;
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		try{
			String sql="SELECT id ,wardname FROM apm_ipd_ward ";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				SummaryReport sr= new SummaryReport();
				sr.setWardname((rs.getString(2)));
				sr.setWardid((rs.getString(1)));
				
				list.add(sr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SummaryReport> getDeathReportList(String fromDate, String toDate, String payby, String diaryUser,
			   String location, String thirdParty, String dischrgeOutcomes, String dischargeStatus, String discharge,
			   String ipdopd, String balancelimit) {

			  location = "0";

			  if (toDate != null) {
			   toDate = toDate + " 23:59:59";
			  }

			  PreparedStatement preparedStatement = null;
			  ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
			  ArrayList<SummaryReport> balancelimitlist = new ArrayList<SummaryReport>();
			  StringBuffer sql = new StringBuffer();

			  sql.append(
			    "SELECT concat(initial,' ',apm_user.firstname,' ',lastname),apm_patient.town,concat(title,' ',apm_patient.firstname,' ',surname), ");
			  sql.append(
			    "apm_patient.whopay, treatmentstatus,outcomes,dschargestatus,third_party_name_id,ipd_addmission_form.id,admissiondsate,ipd_addmission_form.clientid,ipd_addmission_form.wardid,bedid,refferedby,department ,dischargedate,ipdseqno , treatmentepisodeid,ipd_addmission_form.id, ipdabrivationid,wtonbirth ");
			  sql.append("FROM apm_patient ");
			  sql.append("inner join ipd_addmission_form on  ipd_addmission_form.clientid = apm_patient.id ");
			  sql.append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
			  sql.append(
			    "inner join apm_treatment_episode on apm_treatment_episode.id = ipd_addmission_form.treatmentEpisodeId ");
			  sql.append("where apm_treatment_episode.dischargedate between '" + fromDate + "' and '" + toDate + "' ");

			  sql.append("and dschargestatus=3");

			  if (!payby.equals("0")) {

			   sql.append("and apm_patient.whopay='" + payby + "' ");

			  }
			  if (!diaryUser.equals("0")) {
			   sql.append(" and ipd_addmission_form.practitionerid=" + diaryUser + " ");
			  }

			  // 4
			  /*
			   * else if(!payby.equals("0") && !diaryUser.equals("0") &&
			   * !dischrgeOutcomes.equals("0")){ sql.append("and apm_patient.whpay='"
			   * +payby+"' and ipd_addmission_form.practitionerid="
			   * +diaryUser+"  and outcomes="+dischrgeOutcomes+" "); }else
			   * if(!payby.equals("0") && !diaryUser.equals("0") &&
			   * !dischargeStatus.equals("0")){ sql.append("and apm_patient.whopay='"
			   * +payby+"' and ipd_addmission_form.practitionerid="
			   * +diaryUser+"  and dschargestatus="+dischargeStatus+" "); }else
			   * if(!payby.equals("0") && !diaryUser.equals("0") &&
			   * !discharge.equals("All")){ sql.append("and apm_patient.whopay='"
			   * +payby+"' and ipd_addmission_form.practitionerid="
			   * +diaryUser+"  and treatmentstatus="+discharge+" "); }
			   */

			  // 3

			  sql.append(" order by apm_patient.fullname ");
			  int death48count=0;
			  try {
			   
			   preparedStatement = connection.prepareStatement(sql.toString());
			   ResultSet rs = preparedStatement.executeQuery();
			   while (rs.next()) {
			    Date admt=null,dis=null;
			    SummaryReport summaryReport = new SummaryReport();
			    summaryReport.setPractitionerName(rs.getString(1));
			    summaryReport.setLocation("");
			    summaryReport.setClientname(rs.getString(3));
			    summaryReport.setPayby(rs.getString(4));
			    summaryReport.setDischarge(rs.getString(5));
			    summaryReport.setAdmissiondate(rs.getString(10));
			    summaryReport.setDepartment(rs.getString(15));
			    summaryReport.setDischargedate(rs.getString(16));
			    //summaryReport.setIpdseqno(rs.getString(17));
			    summaryReport.setIpdseqno(rs.getString(20));
			    summaryReport.setWtonbirth(rs.getString(21));
			  
			    
			    IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			    
			    
			    Bed bedconditions = ipdDAO.getAllFinalCondition(rs.getString(19),rs.getString(18));
			    BedDao bedDao= new JDBCBedDao(connection);
				String conditionlist="";

				if (bedconditions.getConditionname() != null) {

					for (String str : bedconditions.getConditionname().split(",")) {

						if (str == null) {
							continue;
						}
						if (str.equals("0")) {

							continue;
						}
						int id = Integer.parseInt(str);
						String conditionname = bedDao.getIpdConditionName(str);
						Bed bed2 = new Bed();
						if(conditionlist.equals("")){
							conditionlist=conditionname;
						}else{
							conditionlist=conditionlist+","+conditionname;
						}

					}
				}
				summaryReport.setCondition(conditionlist);
			    
			    if (summaryReport.getAdmissiondate() != null) {
			     String date3 = summaryReport.getAdmissiondate();
			     String date[] = date3.split(" ");
			     String date1[] = date[0].split("-");
			     date[0] = date1[2] + "/" + date1[1] + "/" + date1[0];
			     String admdt=date[0];
			     admt = new SimpleDateFormat("dd/MM/yyyy").parse(admdt);
			     summaryReport.setAdmissiondate(date[0]+" ("+date[1]+")");

			    } else {
			     summaryReport.setAdmissiondate(" ");
			    }
			    if (summaryReport.getDischargedate() != null) {
			     String date3 = summaryReport.getDischargedate();
			     String date[] = date3.split(" ");
			     String date1[] = date[0].split("-");
			     String disdt= date1[2] + "/" + date1[1] + "/" + date1[0];
			     dis = new SimpleDateFormat("dd/MM/yyyy").parse(disdt);
			     date[0] = date1[2] + "/" + date1[1] + "/" + date1[0] + " " + "(" + date[1] + ")";
			     summaryReport.setDischargedate(date[0]);

			    } else {
			     summaryReport.setDischargedate(" ");
			    }

			     
			    long diff = admt.getTime() - dis.getTime();
			    long difference = (Math.abs((diff / (1000 * 60 * 60 * 24))));

			    if(difference>2){
			     death48count= death48count+1;
			    }
			    
			    
			    
			    
			    
			    if (rs.getInt(6) != 0) {
			     String outcomes = getDischargeOutComes(rs.getString(6));
			     summaryReport.setOutcomes(outcomes);
			    } else {
			     summaryReport.setOutcomes("");
			    }

			    if (rs.getInt(7) != 0) {
			     String dschargeStatus = getDischargeStatus(rs.getString(7));
			     summaryReport.setDschargeStatus(dschargeStatus);
			    } else {
			     summaryReport.setDschargeStatus("");
			    }

			    if (rs.getString(8) != null) {
			     ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			     ThirdParty tp = thirdPartyDAO.getThirdPartyDetails(rs.getString(8));
			     summaryReport.setTpName(tp.getCompanyName());
			    } else {
			     summaryReport.setTpName("");
			    }

			    // summaryReport.setAdmissiondate(rs.getString(10));

			    // irfan 27 jan 2018
			    // String admissionid = rs.getString(9);
			    // ClientDAO clientDAO = new JDBCClientDAO(connection);
			    // String dischargedate =
			    // clientDAO.getIpdDischargeDate(admissionid);
			    // if(dischargedate!=null){
			    // summaryReport.setDischargedate(dischargedate);
			    // summaryReport.setAdmissiondate("("+rs.getString(10) + " - " +
			    // dischargedate+")");
			    // }else{
			    // summaryReport.setDischargedate("");
			    // summaryReport.setAdmissiondate("("+rs.getString(10)+")");
			    // }
			    //
			    String admissionid = rs.getString(9);
			    ClientDAO clientDAO = new JDBCClientDAO(connection);
			    String dischargedate = clientDAO.getIpdDischargeDate(admissionid);
			    /*
			     * if(dischargedate!=null){
			     * summaryReport.setDischargedate(dischargedate); //
			     * summaryReport.setAdmissiondate("("+rs.getString(10) + " - " +
			     * dischargedate+")"); String datestr[]=
			     * rs.getString(10).split(" ");
			     * summaryReport.setAdmissiondate(datestr[0]);
			     * summaryReport.setTime(datestr[1]); }else{
			     * summaryReport.setDischargedate(""); String datestr[]=
			     * rs.getString(10).split(" ");
			     * summaryReport.setAdmissiondate(datestr[0]);
			     * summaryReport.setTime(datestr[1]); }
			     */

			    String clientid = rs.getString(11);
			    Client client = clientDAO.getClientDetails(clientid);
			    if(summaryReport.getDischargedate()!=null){
					 if(!summaryReport.getDischargedate().equals("")){
						 String c=summaryReport.getDischargedate();
						 String h[]= c.split(" ");
						 c= DateTimeUtils.getCommencingDate4(h[0]);
						 String d=DateTimeUtils.getAge1onAddmission(client.getDob(), c);
						 summaryReport.setClientage(d);
					 }
				 }
			    summaryReport.setGender(client.getGender());
			    summaryReport.setClientId(clientid);
			    summaryReport.setAbrivationid(client.getAbrivationid());
			    String age = DateTimeUtils.getAge1(client.getDob());
			    //summaryReport.setClientage(age);
			    summaryReport.setMothername(client.getMothername());
			    summaryReport.setBirthplace(client.getBirthplace());

			   
			    String wardname = ipdDAO.getIpdWardName(rs.getString(12));
			    String bedname = ipdDAO.getIpdBedName(rs.getString(13));
			    String refferedby = rs.getString(14);

			    summaryReport.setWardbed(wardname + " / " + bedname);
			    summaryReport.setRefferedby(refferedby);

			    String type = "ipd";
			    int whopay = 0;
			    //commented because report is slow
				/*
				 * double selfcharge = getSelfOpdCharge(clientid, fromDate, toDate, type,
				 * whopay); summaryReport.setSelfcharge(DateTimeUtils.changeFormat(selfcharge));
				 * 
				 * type = "ipd"; whopay = 1; double tpcharge = getSelfOpdCharge(clientid,
				 * fromDate, toDate, type, whopay);
				 * summaryReport.setTpcharge(DateTimeUtils.changeFormat(tpcharge));
				 */

			    double advance = getAdvanceAmount(fromDate, toDate, clientid);
			    summaryReport.setAdvance(DateTimeUtils.changeFormat(advance));

			    double cashreceived = getCashReceived(fromDate, toDate, clientid);
			    summaryReport.setCashreceived(DateTimeUtils.changeFormat(cashreceived));

			    double totaldebit = getSelefBalance(clientid, fromDate, toDate);
			    double advcash = cashreceived + advance;
			    double selefbalance = 0;
			    double selfcredit = 0;
				/*
				 * if (advcash > selfcharge) { selfcredit = advcash - selfcharge; selefbalance =
				 * 0; } else { selefbalance = selfcharge - advcash; }
				 */
			   
			    summaryReport.setSelfcredit(DateTimeUtils.changeFormat(selfcredit));
			    summaryReport.setSelefbalance(DateTimeUtils.changeFormat(selefbalance));

			    if (balancelimit != null) {
			     if (!balancelimit.equals("0")) {
			      if (selefbalance >= Integer.parseInt(balancelimit)) {
			       balancelimitlist.add(summaryReport);
			      }
			     }
			    }
			summaryReport.setDeath48count(death48count);
			    list.add(summaryReport);
			   }

			   if (balancelimit != null) {
			    if (!balancelimit.equals("0")) {
			     return balancelimitlist;
			    }
			   }

			  } catch (Exception e) {
			   e.printStackTrace();
			  }
			  return list;

			 }	
	public String getUserNamebyId(String id) {
		  String user = "";
		  PreparedStatement preparedStatement = null;
		  String sql = "select concat(initial,' ',firstname,' ',lastname) from apm_user where userid = '" + id + "' ";
		  try {
		   preparedStatement = connection.prepareStatement(sql);
		   ResultSet rs = preparedStatement.executeQuery();
		   if (rs.next()) {
		    user = rs.getString(1);
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return user;
		 }

	public Ipd getBedOccupancyrate(String fromdate, String todate) {
		Ipd ipd = new Ipd();
		try {
			String sql ="select totalcount, totalbed from daily_inhouse_patient where date between '"+fromdate+"' and '"+todate+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			double totalpatient=0;
			double totalbed=0;
			double totalbedoccupancy= 0; 
			while (rs.next()) {
				totalpatient = totalpatient + rs.getInt(1);
				totalbed = totalbed + rs.getInt(2);
				totalbedoccupancy = (totalpatient*100)/totalbed;
				ipd.setTotalpatient(totalpatient);
				ipd.setTotalbed(totalbed);
				ipd.setTotalbedoccupancy(String.valueOf(Math.round(totalbedoccupancy * 100.0) / 100.0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipd;
	}

	public ArrayList<SummaryReport> getBedOccupancyList(String fromdate, String todate) {
		ArrayList<SummaryReport> arrayList = new ArrayList<SummaryReport>();
		try {
			StringBuffer buffer =new StringBuffer();
			buffer.append("select id,date,totalcount, totalbed from daily_inhouse_patient where date between '"+fromdate+"' and '"+todate+"' order by date asc");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setId(rs.getInt(1));
				summaryReport.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				summaryReport.setTotalpatient(rs.getString(3));
				summaryReport.setTotalbed(rs.getString(4));
				arrayList.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	public ArrayList<SummaryReport> getMLCReportList(String fromDate, String toDate, String payby, String diaryUser,
		      String location, String thirdParty, String dischrgeOutcomes, String dischargeStatus, String discharge,
		      String ipdopd, String balancelimit) {

		     location = "0";

		     if (toDate != null) {
		      toDate = toDate + " 23:59:59";
		     }

		     PreparedStatement preparedStatement = null;
		     ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
		     ArrayList<SummaryReport> balancelimitlist = new ArrayList<SummaryReport>();
		     StringBuffer sql = new StringBuffer();

		     sql.append(
		       "SELECT concat(initial,' ',apm_user.firstname,' ',lastname),apm_patient.town,concat(title,' ',apm_patient.firstname,' ',surname), ");
		     sql.append(
		       "apm_patient.whopay, treatmentstatus,outcomes,dschargestatus,third_party_name_id,ipd_addmission_form.id,admissiondsate,ipd_addmission_form.clientid,ipd_addmission_form.wardid,bedid,refferedby,department ,dischargedate,mlcno ");
		     sql.append("FROM apm_patient ");
		     sql.append("inner join ipd_addmission_form on  ipd_addmission_form.clientid = apm_patient.id ");
		     sql.append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
		     sql.append(
		       "inner join apm_treatment_episode on apm_treatment_episode.id = ipd_addmission_form.treatmentEpisodeId ");
		     sql.append("where ipd_addmission_form.admissiondsate between '" + fromDate + "' and '" + toDate + "' ");

		     sql.append("and ismlc=1");

		     if (!payby.equals("0")) {

		      sql.append("and apm_patient.whopay='" + payby + "' ");

		     }
		     if (!diaryUser.equals("0")) {
		      sql.append(" and ipd_addmission_form.practitionerid=" + diaryUser + " ");
		     }

		     // 4
		     /*
		      * else if(!payby.equals("0") && !diaryUser.equals("0") &&
		      * !dischrgeOutcomes.equals("0")){ sql.append("and apm_patient.whpay='"
		      * +payby+"' and ipd_addmission_form.practitionerid="
		      * +diaryUser+"  and outcomes="+dischrgeOutcomes+" "); }else
		      * if(!payby.equals("0") && !diaryUser.equals("0") &&
		      * !dischargeStatus.equals("0")){ sql.append("and apm_patient.whopay='"
		      * +payby+"' and ipd_addmission_form.practitionerid="
		      * +diaryUser+"  and dschargestatus="+dischargeStatus+" "); }else
		      * if(!payby.equals("0") && !diaryUser.equals("0") &&
		      * !discharge.equals("All")){ sql.append("and apm_patient.whopay='"
		      * +payby+"' and ipd_addmission_form.practitionerid="
		      * +diaryUser+"  and treatmentstatus="+discharge+" "); }
		      */

		     // 3

		     sql.append(" order by apm_patient.fullname ");
		     int death48count=0;
		     try {
		      
		      preparedStatement = connection.prepareStatement(sql.toString());
		      ResultSet rs = preparedStatement.executeQuery();
		      while (rs.next()) {
		       Date admt=null,dis=null;
		       SummaryReport summaryReport = new SummaryReport();
		       summaryReport.setPractitionerName(rs.getString(1));
		       summaryReport.setLocation("");
		       summaryReport.setClientname(rs.getString(3));
		       summaryReport.setPayby(rs.getString(4));
		       summaryReport.setDischarge(rs.getString(5));
		       summaryReport.setAdmissiondate(rs.getString(10));
		       summaryReport.setDepartment(rs.getString(15));
		       summaryReport.setDischargedate(rs.getString(16));
		       String dtt="";
		       if (summaryReport.getAdmissiondate() != null) {
		        String date3 = summaryReport.getAdmissiondate();
		        String date[] = date3.split(" ");
		        String date1[] = date[0].split("-");
		        date[0] = date1[2] + "/" + date1[1] + "/" + date1[0];
		        String admdt=date[0];
		        admt = new SimpleDateFormat("dd/MM/yyyy").parse(admdt);
		         dtt=date[0] +" "+date[1];
		        summaryReport.setAdmissiondate(date[0] );

		       } else {
		        summaryReport.setAdmissiondate("");
		       }
		       String dischargedate1="";
		       if (summaryReport.getDischargedate() != null) {
		        String date3 = summaryReport.getDischargedate();
		        String date[] = date3.split(" ");
		        String date1[] = date[0].split("-");
		        dischargedate1=date[0];
		        String disdt= date1[2] + "/" + date1[1] + "/" + date1[0];
		        dis = new SimpleDateFormat("dd/MM/yyyy").parse(disdt);
		        date[0] = date1[2] + "/" + date1[1] + "/" + date1[0] + " " + "(" + date[1] + ")";
		        summaryReport.setDischargedate(date[0]);

		       } else {
		        summaryReport.setDischargedate("");
		       }
		       long days=0;
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			      Calendar cal = Calendar.getInstance();
			      // cal.add(Calendar.DATE, -7);
			      String today = dateFormat.format(cal.getTime());
			      String  admissiondate=DateTimeUtils.getCommencingDate(summaryReport.getAdmissiondate());
		       if(!summaryReport.getDischargedate().equals("")){
		    	  
		    	    days=DateTimeUtils.getDiffofTwoDates(admissiondate, dischargedate1);
		       }else{
		    	   days=DateTimeUtils.getDiffofTwoDates(admissiondate, today);
		       }
		        summaryReport.setAdmitdays(days);
		     /*  long diff = admt.getTime() - dis.getTime();
		       long difference = (Math.abs((diff / (1000 * 60 * 60 * 24))));

		       if(difference>2){
		        death48count= death48count+1;
		       }
		*/       
		       
		       
		       
		       
		       if (rs.getInt(6) != 0) {
		        String outcomes = getDischargeOutComes(rs.getString(6));
		        summaryReport.setOutcomes(outcomes);
		       } else {
		        summaryReport.setOutcomes("");
		       }

		       if (rs.getInt(7) != 0) {
		        String dschargeStatus = getDischargeStatus(rs.getString(7));
		        summaryReport.setDschargeStatus(dschargeStatus);
		       } else {
		        summaryReport.setDschargeStatus("");
		       }

		       if (rs.getString(8) != null) {
		        ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		        ThirdParty tp = thirdPartyDAO.getThirdPartyDetails(rs.getString(8));
		        summaryReport.setTpName(tp.getCompanyName());
		       } else {
		        summaryReport.setTpName("");
		       }

		       // summaryReport.setAdmissiondate(rs.getString(10));

		       // irfan 27 jan 2018
		       // String admissionid = rs.getString(9);
		       // ClientDAO clientDAO = new JDBCClientDAO(connection);
		       // String dischargedate =
		       // clientDAO.getIpdDischargeDate(admissionid);
		       // if(dischargedate!=null){
		       // summaryReport.setDischargedate(dischargedate);
		       // summaryReport.setAdmissiondate("("+rs.getString(10) + " - " +
		       // dischargedate+")");
		       // }else{
		       // summaryReport.setDischargedate("");
		       // summaryReport.setAdmissiondate("("+rs.getString(10)+")");
		       // }
		       //
		       String admissionid = rs.getString(9);
		       ClientDAO clientDAO = new JDBCClientDAO(connection);
		       String dischargedate = clientDAO.getIpdDischargeDate(admissionid);
		       /*
		        * if(dischargedate!=null){
		        * summaryReport.setDischargedate(dischargedate); //
		        * summaryReport.setAdmissiondate("("+rs.getString(10) + " - " +
		        * dischargedate+")"); String datestr[]=
		        * rs.getString(10).split(" ");
		        * summaryReport.setAdmissiondate(datestr[0]);
		        * summaryReport.setTime(datestr[1]); }else{
		        * summaryReport.setDischargedate(""); String datestr[]=
		        * rs.getString(10).split(" ");
		        * summaryReport.setAdmissiondate(datestr[0]);
		        * summaryReport.setTime(datestr[1]); }
		        */
		       summaryReport.setIpdid(admissionid);
		       String clientid = rs.getString(11);
		       Client client = clientDAO.getClientDetails(clientid);
		       summaryReport.setGender(client.getGender());
		       summaryReport.setClientId(clientid);
		       summaryReport.setAbrivationid(client.getAbrivationid());
		       String age = DateTimeUtils.getAge(client.getDob());
		       summaryReport.setClientage(age);
		       summaryReport.setMlcno(DateTimeUtils.isNull(rs.getString(17)));
	 /*     IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		       String wardname = ipdDAO.getIpdWardName(rs.getString(12));
		       String bedname = ipdDAO.getIpdBedName(rs.getString(13));
		       String refferedby = rs.getString(14);

		       summaryReport.setWardbed(wardname + " / " + bedname);
		       summaryReport.setRefferedby(refferedby);

		       String type = "ipd";
		       int whopay = 0;
		       double selfcharge = getSelfOpdCharge(clientid, fromDate, toDate, type, whopay);
		       summaryReport.setSelfcharge(DateTimeUtils.changeFormat(selfcharge));

		       type = "ipd";
		       whopay = 1;
		       double tpcharge = getSelfOpdCharge(clientid, fromDate, toDate, type, whopay);
		       summaryReport.setTpcharge(DateTimeUtils.changeFormat(tpcharge));

		       double advance = getAdvanceAmount(fromDate, toDate, clientid);
		       summaryReport.setAdvance(DateTimeUtils.changeFormat(advance));
		       double cashreceived = getCashReceived(fromDate, toDate, clientid);
       summaryReport.setCashreceived(DateTimeUtils.changeFormat(cashreceived));

       double totaldebit = getSelefBalance(clientid, fromDate, toDate);
       double advcash = cashreceived + advance;
       double selefbalance = 0;
       double selfcredit = 0;
       if (advcash > selfcharge) {
        selfcredit = advcash - selfcharge;
        selefbalance = 0;
       } else {
        selefbalance = selfcharge - advcash;
       }
      
       summaryReport.setSelfcredit(DateTimeUtils.changeFormat(selfcredit));
       summaryReport.setSelefbalance(DateTimeUtils.changeFormat(selefbalance));

       if (balancelimit != null) {
        if (!balancelimit.equals("0")) {
         if (selefbalance >= Integer.parseInt(balancelimit)) {
          balancelimitlist.add(summaryReport);
         }
        }
       }
       
   summaryReport.setDeath48count(death48count);*/
		       summaryReport.setAdmissiondate(dtt);
      list.add(summaryReport);
      }

      if (balancelimit != null) {
       if (!balancelimit.equals("0")) {
        return balancelimitlist;
       }
      }

     } catch (Exception e) {
      e.printStackTrace();
     }
     return list;

    } 
	public ArrayList<SummaryReport> getRefferedByList(String opdipd, String diaryUser, String refferdby,
			   String fromDate, String toDate,String admissionOrdischarge) {

			  if (toDate != null) {
			   toDate = toDate + " 23:59:59";
			  }
			  HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			  LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			  PreparedStatement preparedStatement = null;
			  ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
			  StringBuffer sql = new StringBuffer();
			  StringBuffer buffer1 = new StringBuffer();
			  sql.append("SELECT concat(initial,' ',apm_user.firstname,' ',lastname),concat(title,' ',apm_patient.firstname,' ',surname),admissiondsate,ipd_addmission_form.clientid,ipd_addmission_form.refferedby,DISCIPLINE,debit,referenceid,treatmentepisodeid,refered_doc_id ");//@rahul apm_condition.name to DISCIPLINE
			  sql.append("from apm_patient ");
			  sql.append("inner join ipd_addmission_form on  ipd_addmission_form.clientid= apm_patient.id ");
			  sql.append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
			  sql.append("inner join apm_discipline on apm_discipline.id = apm_user.discription ");                 //replace inner join apm_condition to apm_discipline
			  sql.append("left join apm_charges_invoice on apm_charges_invoice.ipdid=ipd_addmission_form.id ");
			  sql.append("inner join apm_treatment_episode on apm_treatment_episode.id=ipd_addmission_form.treatmentepisodeid ");
			  if(admissionOrdischarge.equals("0")) {
			    sql.append("where ipd_addmission_form.admissiondsate between '" + fromDate + "' and '" + toDate + "' ");  //commented
	           }else {
				 sql.append("where apm_treatment_episode.dischargedate between '" + fromDate + "' and '" + toDate + "' ");  //commented
               }
			  //sql.append("where apm_charges_invoice.date between '" + fromDate + "' and '" + toDate + "' ");         

			  if (!refferdby.equals("0")) {
			   sql.append(" and ipd_addmission_form.refferedby='" + refferdby + "' ");             //ipd_addmission_form.refferedby
			  }
			  sql.append(" and referenceid!='' and  referenceid !='0' ");                     //old and refferedby !='0' and refferedby!=''
			  try {
			   preparedStatement = connection.prepareStatement(sql.toString());
			   ResultSet rs = preparedStatement.executeQuery();
			   while (rs.next()) {
			    SummaryReport summaryReport = new SummaryReport();
			    summaryReport.setPractitionerName(rs.getString(1));

			    summaryReport.setClientname(rs.getString(2));
			    summaryReport.setAdmissiondate(rs.getString(3));
			    summaryReport.setType("IPD");
			    String ref=(String) rs.getString(5);
				
				/*
				 * if(!ref.equals("")) { summaryReport.setRefferedby(rs.getString(5)); }else{
				 * continue; }
				 */
				 
			    summaryReport.setDepartment(rs.getString(6));
			    summaryReport.setPayment(rs.getString(7));
			    SummaryReport  summaryReport1=getReferenceShareAmt(rs.getString(8));
			    String dischargedate=getDischargeDate(rs.getString(9));
			    summaryReport.setDischargedate(dischargedate);
			    ClientDAO clientDAO= new JDBCClientDAO(connection);
				 Client client=clientDAO.getClientDetails(rs.getString(4));
				 summaryReport.setAbrivationid(client.getAbrivationid());
				 summaryReport.setMobno(client.getMobNo());
				 summaryReport.setAddress(client.getAddress());
			    double share_amt=0.0;
			    double billamt=0.0;
			    if((rs.getString(7))==null) {
			    	billamt=0;
			    }
			
	          if(!loginInfo.isAyushman()) {		    
		    	    if(summaryReport1.getType().equals("0")) { 
					    share_amt=(billamt) *Double.parseDouble(summaryReport1.getReferedshareAmt())/100; 
				  }else {
				        share_amt=Double.parseDouble(summaryReport1.getReferedshareAmt()); 
			      }
				  summaryReport.setReferedshareAmt(summaryReport1.getReferedshareAmt());
				  summaryReport.setShareAmt(share_amt);
				  summaryReport.setType(summaryReport1.getType());
	          }	 
	          
	            if(!ref.equals("")) {
			       summaryReport.setReferedby(rs.getString(5));
	            }else {
	             summaryReport.setReferedby(rs.getString(8));	
				 String refdoctore=getReferedDoctorData(summaryReport.getReferedby());
				 summaryReport.setReferedby(refdoctore);
	            }
				  //comment this theses code is for refere_doc_id
			    list.add(summaryReport);
			 }
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
			  return list;
			 }
	
	private String getDischargeDate(String treatmentid) {
		           
		       String dischargedate ="";
				
				try {
					String sql ="select dischargedate from apm_treatment_episode where id='"+treatmentid+"' ";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						dischargedate=rs.getString(1);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		return dischargedate;
	}

	private SummaryReport getReferenceShareAmt(String refereid) {
		//String ref_share_amt ="0";
		SummaryReport summaryReport=new SummaryReport();
		try {
			String sql ="select sharepercentage,reference_sharetype from reference where id='"+refereid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				summaryReport.setReferedshareAmt(rs.getString(1));
				summaryReport.setType(rs.getString(2));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryReport;
		
	}
	
	int beddate1=0,beddate2=0,beddate3=0,beddate4=0,beddate5=0,beddate6=0,beddate7=0,beddate8=0,beddate9=0,beddate10=0;
	int beddate11=0,beddate12=0,beddate13=0,beddate14=0,beddate15=0,beddate16=0,beddate17=0,beddate18=0,beddate19=0,beddate20=0;
	int beddate21=0,beddate22=0,beddate23=0,beddate24=0,beddate25=0,beddate26=0,beddate27=0,beddate28=0,beddate29=0,beddate30=0,beddate31=0;
	String checkipdid="0";
	public ArrayList<SummaryReport> CalculateBedOccupancyList(String fromdate, String todate,String yearmonthh) {
		ArrayList<SummaryReport> arrayList = new ArrayList<SummaryReport>();
		try {
			//Between same month
			calculatebedocupancy1condtion(fromdate,todate);
			//Admited in month bt not discharge in same month
			calculatebedocupancy2condtion(fromdate,todate);
			//discharge in month bt not Admited in same month
			calculatebedocupancy3condtion(fromdate,todate);
			//discharge after month and  Admited before month
			calculatebedocupancy4condtion(fromdate,todate);
			
			//admitted bt not discharge yet
			calculatebedocupancy5condtion(fromdate,todate);
			int k = Integer.parseInt(yearmonthh);
			for (int i = 1; i <=k; i++) {
				SummaryReport report = new SummaryReport();
				String[] adm = fromdate.split("-");
				String date = i+"-"+adm[1]+"-"+adm[0];
				report.setDate(date);
				int bed10 = beddate1+beddate2+beddate3+beddate4+beddate5+beddate6+beddate7+beddate8+beddate9+beddate10;
				int bed20 = beddate11+beddate12+beddate13+beddate14+beddate15+beddate16+beddate17+beddate18+beddate19+beddate20;
				int bed30 = beddate21+beddate22+beddate23+beddate24+beddate25+beddate26+beddate27+beddate28+beddate29+beddate30+beddate31;
				int total =bed10+bed20+bed30;
				report.setTotal(total);
				if(i==1){
					report.setTotalpatient(String.valueOf(beddate1));
					arrayList.add(report);
				}else if(i==2){
					report.setTotalpatient(String.valueOf(beddate2));
					arrayList.add(report);
				}else if(i==3){
					report.setTotalpatient(String.valueOf(beddate3));
					arrayList.add(report);
				}else if(i==4){
					report.setTotalpatient(String.valueOf(beddate4));
					arrayList.add(report);
				}else if(i==5){
					report.setTotalpatient(String.valueOf(beddate5));
					arrayList.add(report);
				}else if(i==6){
					report.setTotalpatient(String.valueOf(beddate6));
					arrayList.add(report);
				}else if(i==7){
					report.setTotalpatient(String.valueOf(beddate7));
					arrayList.add(report);
				}else if(i==8){
					report.setTotalpatient(String.valueOf(beddate8));
					arrayList.add(report);
				}else if(i==9){
					report.setTotalpatient(String.valueOf(beddate9));
					arrayList.add(report);
				}else if(i==10){
					report.setTotalpatient(String.valueOf(beddate10));
					arrayList.add(report);
				}else if(i==11){
					report.setTotalpatient(String.valueOf(beddate11));
					arrayList.add(report);
				}else if(i==12){
					report.setTotalpatient(String.valueOf(beddate12));
					arrayList.add(report);
				}else if(i==13){
					report.setTotalpatient(String.valueOf(beddate13));
					arrayList.add(report);
				}else if(i==14){
					report.setTotalpatient(String.valueOf(beddate14));
					arrayList.add(report);
				}else if(i==15){
					report.setTotalpatient(String.valueOf(beddate15));
					arrayList.add(report);
				}else if(i==16){
					report.setTotalpatient(String.valueOf(beddate16));
					arrayList.add(report);
				}else if(i==17){
					report.setTotalpatient(String.valueOf(beddate17));
					arrayList.add(report);
				}else if(i==18){
					report.setTotalpatient(String.valueOf(beddate18));
					arrayList.add(report);
				}else if(i==19){
					report.setTotalpatient(String.valueOf(beddate19));
					arrayList.add(report);
				}else if(i==20){
					report.setTotalpatient(String.valueOf(beddate20));
					arrayList.add(report);
				}else if(i==21){
					report.setTotalpatient(String.valueOf(beddate21));
					arrayList.add(report);
				}else if(i==22){
					report.setTotalpatient(String.valueOf(beddate22));
					arrayList.add(report);
				}else if(i==23){
					report.setTotalpatient(String.valueOf(beddate23));
					arrayList.add(report);
				}else if(i==24){
					report.setTotalpatient(String.valueOf(beddate24));
					arrayList.add(report);
				}else if(i==25){
					report.setTotalpatient(String.valueOf(beddate25));
					arrayList.add(report);
				}else if(i==26){
					report.setTotalpatient(String.valueOf(beddate26));
					arrayList.add(report);
				}else if(i==27){
					report.setTotalpatient(String.valueOf(beddate27));
					arrayList.add(report);
				}else if(i==28){
					report.setTotalpatient(String.valueOf(beddate28));
					arrayList.add(report);
				}else if(i==29){
					report.setTotalpatient(String.valueOf(beddate29));
					if(beddate29!=0){
						arrayList.add(report);
					}
				}else if(i==30){
					report.setTotalpatient(String.valueOf(beddate30));
					if(beddate30!=0){
						arrayList.add(report);
					}
				}else if(i==31){
					report.setTotalpatient(String.valueOf(beddate31));
					if(beddate31!=0){
						arrayList.add(report);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private void calculatebedocupancy5condtion(String fromdate, String todate) {
		try {
			StringBuffer buffer = new StringBuffer();
			todate = todate +" "+ "59:59:59";
			buffer.append("select admissiondsate,dischargedate,ipd_addmission_form.id,treatmentepisodeid from ipd_addmission_form ");
			buffer.append("inner join apm_treatment_episode on ipd_addmission_form.treatmentepisodeid = apm_treatment_episode.id ");
			buffer.append("where admissiondsate between '"+fromdate+"' and '"+todate+"' and ( dischargedate is null) and cancel=0 and casualty=0 ");
			//buffer.append("and ipd_addmission_form.id not in ("+checkipdid+") ");
			//buffer.append("and casualty=0 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				//Admission date
				String[] admtemp = rs.getString(1).split(" ");
				String[] admtime = admtemp[0].split("-");
				int admissiondate = Integer.parseInt(admtime[2]);
				
				int dischargedate = 31;
				checkipdid = checkipdid +","+rs.getInt(3);
				int result = setbedocupancy(admissiondate,dischargedate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void calculatebedocupancy4condtion(String fromdate, String todate) {
		try {
			StringBuffer buffer = new StringBuffer();
			todate = todate +" "+ "59:59:59";
			buffer.append("select admissiondsate,dischargedate,ipd_addmission_form.id,treatmentepisodeid from ipd_addmission_form ");
			buffer.append("inner join apm_treatment_episode on ipd_addmission_form.treatmentepisodeid = apm_treatment_episode.id ");
			buffer.append("where admissiondsate < '"+fromdate+"' and dischargedate > '"+todate+"' and cancel=0 and casualty=0 ");
			//buffer.append("and ipd_addmission_form.id not in ("+checkipdid+") ");
			//buffer.append("and casualty=0 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				//Admission date
				/*String[] admtemp = rs.getString(1).split(" ");
				String[] admtime = admtemp[0].split("-");
				int admissiondate = Integer.parseInt(admtime[2]);*/
				int admissiondate =1;
				//dischargedate
				/*String[] dischtemp = rs.getString(2).split(" ");
				String[] dischtime = dischtemp[0].split("-");
				int dischargedate = Integer.parseInt(dischtime[2]);*/
				int dischargedate =31;
				checkipdid = checkipdid +","+rs.getInt(3);
				int result = setbedocupancy(admissiondate,dischargedate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void calculatebedocupancy3condtion(String fromdate, String todate) {
		try {
			StringBuffer buffer = new StringBuffer();
			todate = todate +" "+ "59:59:59";
			buffer.append("select admissiondsate,dischargedate,ipd_addmission_form.id,treatmentepisodeid from ipd_addmission_form ");
			buffer.append("inner join apm_treatment_episode on ipd_addmission_form.treatmentepisodeid = apm_treatment_episode.id ");
			buffer.append("where admissiondsate not between '"+fromdate+"' and '"+todate+"' and dischargedate between '"+fromdate+"' and '"+todate+"' and cancel=0 and casualty=0 ");
			//buffer.append("and ipd_addmission_form.id not in ("+checkipdid+") ");
			//buffer.append("and casualty=0 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				//Admission date
				/*String[] admtemp = rs.getString(1).split(" ");
				String[] admtime = admtemp[0].split("-");
				int admissiondate = Integer.parseInt(admtime[2]);*/
				int admissiondate = 1;
				
				//dischargedate
				String[] dischtemp = rs.getString(2).split(" ");
				String[] dischtime = dischtemp[0].split("-");
				int dischargedate = Integer.parseInt(dischtime[2]);
				checkipdid = checkipdid +","+rs.getInt(3);
				int result = setbedocupancy(admissiondate,dischargedate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void calculatebedocupancy2condtion(String fromdate, String todate) {
		try {
			StringBuffer buffer = new StringBuffer();
			todate = todate +" "+ "59:59:59";
			buffer.append("select admissiondsate,dischargedate,ipd_addmission_form.id,treatmentepisodeid from ipd_addmission_form ");
			buffer.append("inner join apm_treatment_episode on ipd_addmission_form.treatmentepisodeid = apm_treatment_episode.id ");
			buffer.append("where admissiondsate between '"+fromdate+"' and '"+todate+"' and dischargedate not between '"+fromdate+"' and '"+todate+"' and cancel=0 and casualty=0 ");
			//buffer.append("and ipd_addmission_form.id not in ("+checkipdid+") ");
			//buffer.append("and casualty=0 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				//Admission date
				String[] admtemp = rs.getString(1).split(" ");
				String[] admtime = admtemp[0].split("-");
				int admissiondate = Integer.parseInt(admtime[2]);
				
				/*//dischargedate
				String[] dischtemp = rs.getString(2).split(" ");
				String[] dischtime = dischtemp[0].split("-");
				int dischargedate = Integer.parseInt(dischtime[2]);*/
				int dischargedate = 31;
				
				checkipdid = checkipdid +","+rs.getInt(3);
				int result = setbedocupancy(admissiondate,dischargedate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void calculatebedocupancy1condtion(String fromdate, String todate) {
		try {
			StringBuffer buffer = new StringBuffer();
			todate = todate +" "+ "59:59:59";
			buffer.append("select admissiondsate,dischargedate,ipd_addmission_form.id,treatmentepisodeid from ipd_addmission_form ");
			buffer.append("inner join apm_treatment_episode on ipd_addmission_form.treatmentepisodeid = apm_treatment_episode.id ");
			buffer.append("where admissiondsate between '"+fromdate+"' and '"+todate+"' and dischargedate between '"+fromdate+"' and '"+todate+"' and cancel=0 and casualty=0 ");
			//buffer.append("and casualty=0 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				//Admission date
				String[] admtemp = rs.getString(1).split(" ");
				String[] admtime = admtemp[0].split("-");
				int admissiondate = Integer.parseInt(admtime[2]);
				
				//dischargedate
				String[] dischtemp = rs.getString(2).split(" ");
				String[] dischtime = dischtemp[0].split("-");
				int dischargedate = Integer.parseInt(dischtime[2]);
				
				checkipdid = checkipdid +","+rs.getInt(3);
				
				int result = setbedocupancy(admissiondate,dischargedate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int setbedocupancy(int i, int dischargedate) {
		try {
			for (int admissiondate=i; admissiondate <=dischargedate; admissiondate++) {
				if(admissiondate==1){
					beddate1++;
				}else if(admissiondate==2){
					beddate2++;
				}else if(admissiondate==3){
					beddate3++;
				}else if(admissiondate==4){
					beddate4++;
				}else if(admissiondate==5){
					beddate5++;
				}else if(admissiondate==6){
					beddate6++;
				}else if(admissiondate==7){
					beddate7++;
				}else if(admissiondate==8){
					beddate8++;
				}else if(admissiondate==9){
					beddate9++;
				}else if(admissiondate==10){
					beddate10++;
				}else if(admissiondate==11){
					beddate11++;
				}else if(admissiondate==12){
					beddate12++;
				}else if(admissiondate==13){
					beddate13++;
				}else if(admissiondate==14){
					beddate14++;
				}else if(admissiondate==15){
					beddate15++;
				}else if(admissiondate==16){
					beddate16++;
				}else if(admissiondate==17){
					beddate17++;
				}else if(admissiondate==18){
					beddate18++;
				}else if(admissiondate==19){
					beddate19++;
				}else if(admissiondate==20){
					beddate20++;
				}else if(admissiondate==21){
					beddate21++;
				}else if(admissiondate==22){
					beddate22++;
				}else if(admissiondate==23){
					beddate23++;
				}else if(admissiondate==24){
					beddate24++;
				}else if(admissiondate==25){
					beddate25++;
				}else if(admissiondate==26){
					beddate26++;
				}else if(admissiondate==27){
					beddate27++;
				}else if(admissiondate==28){
					beddate28++;
				}else if(admissiondate==29){
					beddate29++;
				}else if(admissiondate==30){
					beddate30++;
				}else if(admissiondate==31){
					beddate31++;
				}
				
			}
			
		} catch (Exception e) {
			
		}
		return 0;
	}

	public ArrayList<SummaryReport> getPatients(String fromDate, String toDate, String dept, String type,String city) {
		PreparedStatement ps= null;
		StringBuffer sql= new StringBuffer();
		ArrayList<SummaryReport> patientlist= new ArrayList<SummaryReport>();
		try{
if(type.equals("IPD")){
			toDate = toDate+" "+"59:59:59";
			sql.append("SELECT concat(title,' ',apm_patient.firstname,' ',surname),ipd_addmission_form.clientid,apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.county ,ipd_addmission_form.department ");
			sql.append("FROM apm_patient ");
			sql.append("inner join ipd_addmission_form on  ipd_addmission_form.clientid = apm_patient.id ");
			sql.append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
			sql.append("inner join apm_treatment_episode on apm_treatment_episode.id = ipd_addmission_form.treatmentEpisodeId ");
			sql.append("inner join apm_ipd_ward on apm_ipd_ward.id = ipd_addmission_form.wardid ");
			sql.append("left join apm_diagnosis on apm_diagnosis.id= ipd_addmission_form.conditionid ");
			 sql.append("where ipd_addmission_form.admissiondsate between '" + fromDate + "' and '" + toDate + "' ");
			 sql.append("and  casualty='0' and cancel='0' ");
			 if(dept != null){
				 if(!dept.equals("")){
					 
				 sql.append(" and apm_user.discription='"+dept+"'"); 
			 }
			 }
			 if(!city.equals("0")){
				 sql.append(" and apm_patient.town='"+city+"'");
			 }
			
}if(type.equals("OPD")){
			sql.append("SELECT concat(title,' ',apm_patient.firstname,' ',surname),apm_available_slot.clientid,apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.county,apm_discipline.discipline ");
			sql.append(" FROM apm_patient ");
			sql.append(" inner join apm_available_slot on  apm_available_slot.clientid = apm_patient.id");
			sql.append(" inner join apm_treatment_episode on apm_treatment_episode.id = apm_available_slot.treatmentEpisodeId");
			sql.append(" inner join apm_user on apm_user.id=apm_available_slot.diaryuserid");
			sql.append(" inner join apm_discipline on apm_discipline.id = apm_user.discription ");
			sql.append(" where apm_available_slot.commencing  between '" + fromDate + "' and '" + toDate + "' ");
			
	if(dept!= null){
		 if(!dept.equals("")){
		sql.append(" and apm_user.discription='"+dept+"'");
		 }
	}
	 if(!city.equals("0")){
		 sql.append(" and apm_patient.town='"+city+"'");
	 }
}
	String sql1= sql.toString();
	
	ps= connection.prepareStatement(sql1);
	ResultSet rs = ps.executeQuery();
	while(rs.next()){
		SummaryReport sr= new  SummaryReport();
		sr.setPatientName(rs.getString(1));
		sr.setDob(rs.getString(3));
		sr.setAge1(DateTimeUtils.getAge1(rs.getString(3)));
		sr.setAddress(rs.getString(4));
		sr.setCity(rs.getString(5));
		sr.setState(rs.getString(6));
		sr.setDepartment(rs.getString(7));
		patientlist.add(sr);
	}
	
		}catch(Exception e){
			e.printStackTrace();
		}
		return patientlist;
	}
	public ArrayList<SummaryReport> getdepartments(){
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		PreparedStatement ps= null;
		try{
			String sql="select id , discipline from apm_discipline";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				SummaryReport sr= new SummaryReport();
				
				sr.setDiscipline_id(rs.getString(1));
				sr.setDiscipline_name(rs.getString(2));
				
				list.add(sr);
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<SummaryReport> getallLocations() {
		PreparedStatement ps= null;
		String sql="";
		ArrayList<SummaryReport> citylist= new ArrayList<SummaryReport>();
				try{
					sql="select id , city from apm_city ";
					ps= connection.prepareStatement(sql);
					ResultSet rs= ps.executeQuery();
					while(rs.next())
					{
						SummaryReport sr= new SummaryReport();
						sr.setCity(rs.getString(2));
						sr.setId(rs.getInt(1));
						citylist.add(sr);
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
		return citylist;
	}
	public ArrayList<Accounts> getOpdIpdConversionRevenue(String fromdate, String todate, String netipddebitcount) {
		ArrayList<Accounts> arrayList = new ArrayList<Accounts>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		try {
			todate = todate +" "+"59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,admissiondsate,clientid,ipdseqno,treatmentepisodeid from ipd_addmission_form ");
			buffer.append("where admissiondsate between '"+fromdate+"' and '"+todate+"' and cancel=0 and casualty=0");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int count =0;
			double totalrevenue = 0;
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			BedDao bedDao= new JDBCBedDao(connection);
			while (rs.next()) {
				Accounts accounts = new Accounts();
				String ipdid = rs.getString(1);
				String admissiondate = rs.getString(2);
				String[] addmisdate = admissiondate.split(" ");
				String clientid = rs.getString(3);
				accounts.setIpdid(ipdid);
				accounts.setClientid(rs.getInt(3));
				accounts.setIpdopdseq(rs.getString(4));
				accounts.setAdmissiondate(DateTimeUtils.getCommencingDate1(addmisdate[0])+" "+addmisdate[1]);
				Bed bed= ipdDAO.getDischargeData(rs.getString(5));
				
				accounts.setDischargedate(DateTimeUtils.isNull(bed.getDischargedate()));
				if(accounts.getDischargedate().contains("-")){
					accounts.setDischargedate(DateTimeUtils.getCommencingDate1(accounts.getDischargedate().split(" ")[0])+" "+accounts.getDischargedate().split(" ")[1]);
				}
				//accounts.setAdmissiondate(DateTimeUtils.addmisdate[0]);
				Accounts accounts2 = ipdadmifromopd(ipdid,clientid,addmisdate[0]);
				if(accounts2.getStatus().equals("1")){
					count++;
					accounts.setDebitAmount(accounts2.getDebitAmount());
					/*double temp=0;
					if(accounts2.getDebitAmount()>0){
						temp=(Double.parseDouble(netipddebitcount)/accounts2.getDebitAmount())*100;
						temp = Math.round(temp * 100.0) / 100.0;
					}
					accounts.setTotaldebitAmount(String.valueOf(temp));*/
					totalrevenue = totalrevenue +accounts2.getDebitAmount();
					accounts.setTotaldebitAmount(DateTimeUtils.changeFormat(totalrevenue));
					accounts.setCount(count);
					Client client = clientDAO.getClientDetails(rs.getString(3));
					accounts.setClientName(client.getTitle() + " " + client.getFirstName() +" " + client.getLastName());
					accounts.setAbrivationid(client.getAbrivationid());
					arrayList.add(accounts);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private Accounts ipdadmifromopd(String ipdid, String clientid, String admissiondate) {
		Accounts accounts = new Accounts();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select apm_available_slot.id,apm_available_slot.clientid,commencing from apm_available_slot ");
			buffer.append("where commencing='"+admissiondate+"' and clientid='"+clientid+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			accounts.setStatus("0");
			while (rs.next()) {
				accounts.setAppointmentid(rs.getInt(1));
				accounts.setClientid(rs.getInt(2));
				accounts.setCommencing(rs.getString(3));
				accounts.setStatus("1");
				String debitamout = getDebitAmountFromIPDPatient(ipdid);
				accounts.setDebitAmount(Double.parseDouble(debitamout));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	private String getDebitAmountFromIPDPatient(String ipdid) {
		String paymentamount ="0";
		try {
			String sql ="select sum(debit) from apm_charges_invoice where ipdid='"+ipdid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				paymentamount = DateTimeUtils.changeFormat(rs.getDouble(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentamount;
	}

	public ArrayList<SummaryReport> getDepartmentWiseRevenue(String fromdate, String todate, String year,String isopdipd) {
		ArrayList<SummaryReport> arrayList = new ArrayList<SummaryReport>();
		int jan1=0,feb1=0,march1=0,april1=0,may1=0,june1=0,jully1=0,aug1=0,sept1=0,oct1=0,nov1=0,dec1=0;
		int totalcount=0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(debit),apm_discipline.id,apm_discipline.discipline from apm_charges_invoice ");
			buffer.append("inner join apm_user on apm_user.id = apm_charges_invoice.practid ");
			buffer.append("inner join apm_discipline on apm_discipline.id =apm_user.discription ");
			buffer.append("where date between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("group by apm_discipline.discipline  ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				SummaryReport report = new SummaryReport();
				String jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec;
				String newfromdate="";
				String newtodate="";
				newfromdate = year+"-01-01";
				newtodate = year+"-01-31";
				jan = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				
				
				
				newfromdate = year+"-02-01";
				newtodate = year+"-02-31";
				feb = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				
				newfromdate = year+"-03-01";
				newtodate = year+"-03-31";
				mar = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				
				newfromdate = year+"-04-01";
				newtodate = year+"-04-31";
				apr = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);

				newfromdate = year+"-05-01";
				newtodate = year+"-05-31";
				may = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				
				
				newfromdate = year+"-06-01";
				newtodate = year+"-06-31";
				jun = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				
				newfromdate = year+"-07-01";
				newtodate = year+"-07-31";
				jul = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);

				newfromdate = year+"-08-01";
				newtodate = year+"-08-31";
				aug = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				
				newfromdate = year+"-09-01";
				newtodate = year+"-09-31";
				sep = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				
				newfromdate = year+"-10-01";
				newtodate = year+"-10-31";
				oct = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				
				newfromdate = year+"-11-01";
				newtodate = year+"-11-31";
				nov = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				
				newfromdate = year+"-12-01";
				newtodate = year+"-12-31";
				dec = getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				report.setDepartment(rs.getString(3));
				report.setJan(jan);
				report.setFeb(feb);
				report.setMar(mar);
				report.setApr(apr);
				report.setMay(may);
				report.setJun(jun);
				report.setJul(jul);
				report.setAug(aug);
				report.setSep(sep);
				report.setOct(oct);
				report.setNov(nov);
				report.setDec(dec);
				jan1=jan1+Integer.parseInt(jan);
				feb1=feb1+Integer.parseInt(feb);
				march1= march1+Integer.parseInt(mar);
				april1= april1+Integer.parseInt(apr);
				may1= may1+Integer.parseInt(may);
				june1= june1+Integer.parseInt(jun);
				jully1= jully1+Integer.parseInt(jul);
				aug1= aug1+ Integer.parseInt(aug);
				sept1= sept1+Integer.parseInt(sep);
				oct1= oct1+Integer.parseInt(oct);
				nov1= nov1+Integer.parseInt(nov);
				dec1= dec1+Integer.parseInt(dec);
				report.setJan1(jan1);
				report.setFeb1(feb1);
				report.setMarch1(march1);
				report.setApril1(april1);
				report.setMay1(may1);
				report.setJune1(june1);
				report.setJully1(jully1);
				report.setAug1(aug1);
				report.setSept1(sept1);
				report.setOct1(oct1);
				report.setNov1(nov1);
				report.setDec1(dec1);
				int count1=getDeptwisePatientCountOPD(fromdate, todate, rs.getString(2));
				int count2= getDeptwisePatientCount(fromdate, todate, rs.getString(2));
				int count=0;
				if (isopdipd.equals("IPD")) {
					count=count2;
				}else if (isopdipd.equals("OPD")) {
					count= count1;
				}else{
					count= count1+count2;
				}
				
				totalcount=totalcount+count;
				report.setTotalpatient(String.valueOf(totalcount));
				report.setCount(String.valueOf(count));
				arrayList.add(report);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getDepartmentRevenueMonthly(String newfromdate, String newtodate, String department,String isipdopd) {
		String res = "0";
		
		
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(debit),apm_discipline.id,apm_discipline.discipline from apm_charges_invoice ");
			buffer.append("inner join apm_user on apm_user.id = apm_charges_invoice.practid ");
			buffer.append("inner join apm_discipline on apm_discipline.id =apm_user.discription ");
			buffer.append("where date between '"+newfromdate+"' and '"+newtodate+"' ");
			buffer.append("and  apm_discipline.id='"+department+"' ");
			if(!isipdopd.equals("")){
				if(isipdopd.equals("IPD"))
				buffer.append("and itype=2 ");
			}
			if(!isipdopd.equals("")){
				if(isipdopd.equals("OPD"))
				buffer.append("and itype!=2 ");
			}
			buffer.append("group by apm_discipline.discipline  ");
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs= preparedStatement.executeQuery();
			while (rs.next()) {
				if(rs.getString(1)==null){
					res="0";
				}else{
					res= ""+rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
public SummaryReport getdeptcountlistopd(String deptid, String fromdate, String todate,LoginInfo loginInfo) {
		
		SummaryReport sr = new SummaryReport();
		PreparedStatement ps= null;
		try{
			
			StringBuffer buffer=  new StringBuffer();
			 if(loginInfo.isLmh()) {
				 buffer.append("select count(*) from department_opd where newpatient in(1,2) and commencing between  '"+fromdate+"' and  '"+todate+"' and dept='"+deptid+"';");
			 }
			 else {
			buffer.append(" select count(*) from apm_available_slot ");
			buffer.append(" inner join apm_user on apm_user.id=apm_available_slot.diaryuserid ");
			buffer.append("  inner join apm_discipline on apm_discipline.id = apm_user.discription ");
			buffer.append(" where apm_discipline.id='"+deptid+" ' ");
			
			buffer.append(" and  apm_available_slot.commencing  between  '"+fromdate+"' and  '"+todate+"'  ");
			buffer.append(" and apm_available_slot.procedures = '0' ");
			 }
		String sql= buffer.toString();
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			sr.setCount(rs.getString(1));
		}
		if(sr.getCount()==null){
			sr.setCount("0");
		}	
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return sr;
	}



	public SummaryReport getdeptcountlistipd(String deptid, String fromdate, String todate) {
		SummaryReport sr = new SummaryReport();
		PreparedStatement ps= null;
		 todate = DateTimeUtils.getCommencingDate1(todate);
		 fromdate= DateTimeUtils.getCommencingDate1(fromdate);
	try{
		
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select count(*) from ipd_addmission_form ");
		buffer.append(" inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
		buffer.append(" inner join apm_discipline on apm_discipline.id = apm_user.discription ");
		buffer.append(" where apm_discipline.id='"+deptid+"' ");
		buffer.append("  and  ipd_addmission_form.admissiondsate  between  '"+fromdate+"' and  '"+todate+" 23:59:59' and cancel ='0'  ");
		
		String sql= buffer.toString();
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			sr.setCount(rs.getString(1));
			if(sr.getCount()==null){
				sr.setCount("0");
			}
		}
		String dia= getdeptcountlistipddiagnosis(deptid, fromdate, todate);
		sr.setDiagnosis(dia);
	}
	catch(Exception e){
		e.printStackTrace();
	}
		return sr;
	}

	public SummaryReport getdeptcountlistot(String deptid, String fromdate, String todate) {
		SummaryReport sr = new SummaryReport();
		 todate = DateTimeUtils.getCommencingDate1(todate);
		 fromdate= DateTimeUtils.getCommencingDate1(fromdate);
	ArrayList<SummaryReport> otlist= new ArrayList<SummaryReport>();
		PreparedStatement ps= null;
		try{
			
			StringBuffer buffer=  new StringBuffer();
			 
			buffer.append(" select count(*)  from apm_available_slot ");
			buffer.append(" inner join apm_user on apm_user.id=apm_available_slot.surgeon ");
			buffer.append("  inner join apm_discipline on apm_discipline.id = apm_user.discription ");
			buffer.append(" where apm_discipline.id='"+deptid+" ' ");
			buffer.append(" and  apm_available_slot.commencing  between  '"+fromdate+"' and  '"+todate+" 23:59:59'  ");
			buffer.append(" and apm_available_slot.procedures != '0'  ");
		String sql= buffer.toString();
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			
			SummaryReport sr1= new SummaryReport();
			sr.setCount(rs.getString(1));
			
		
		}
		
		if(sr.getCount()==null){
			sr.setCount("0");
		}
		otlist= getallotprocedures(deptid, fromdate, todate);
		sr.setOtlist(otlist);
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
		
		return sr;
	}
	private ArrayList<SummaryReport> getallotprocedures(String deptid, String fromdate, String todate){
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		 todate = DateTimeUtils.getCommencingDate1(todate);
		 fromdate= DateTimeUtils.getCommencingDate1(fromdate);
		PreparedStatement ps= null;
		try{
			StringBuffer buffer=  new StringBuffer();
				 
				buffer.append(" select count(*)  ,procedures from apm_available_slot ");
				buffer.append(" inner join apm_user on apm_user.id=apm_available_slot.diaryuserid ");
				buffer.append("  inner join apm_discipline on apm_discipline.id = apm_user.discription ");
				buffer.append(" where apm_discipline.id='"+deptid+" ' ");
				buffer.append(" and  apm_available_slot.commencing  between  '"+fromdate+"' and  '"+todate+" 23:59:59'  ");
				buffer.append(" and apm_available_slot.procedures != '0' group by procedures order by count(*) desc limit 5 ");
				
				
			String sql= buffer.toString();
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				SummaryReport sr= new SummaryReport();
				sr.setProcedurename(rs.getString(2));
				list.add(sr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	
	private String getdeptcountlistipddiagnosis(String deptid, String fromdate, String todate){
		String string="";
		SummaryReport sr = new SummaryReport();
		PreparedStatement ps= null;
		 todate = DateTimeUtils.getCommencingDate1(todate);
		 fromdate= DateTimeUtils.getCommencingDate1(fromdate);
	try{
		
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select count(*),  apm_diagnosis.name from ipd_addmission_form ");
		buffer.append(" inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
		buffer.append(" inner join apm_discipline on apm_discipline.id = apm_user.discription ");
		buffer.append(" inner join apm_diagnosis on apm_diagnosis.id  =ipd_addmission_form.conditionid ");
		buffer.append(" where apm_discipline.id='"+deptid+"' ");
		buffer.append("  and  ipd_addmission_form.admissiondsate  between  '"+fromdate+"' and  '"+todate+" 23:59:59'   ");
		buffer.append("  group by  apm_diagnosis.name order by count(*) desc limit 5 ");
		
		String sql= buffer.toString();
		StringBuffer bs= new   StringBuffer();
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		 
		while(rs.next()){
		String	str= rs.getString(2);
			if(str!=null){
				if(!str.equals("SEE EMR")){
				bs.append(str+" ,");
				}
			}
			}
		string = bs.toString();
		
	}
	catch(Exception e){
		e.printStackTrace();
	}
		
		
		return string;
	}
	
	private ArrayList<SummaryReport> getSeconderyConsultantList(String userids){
		PreparedStatement ps = null;
		
		 ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		 String id="";
		
		String userid[]= userids.split(",");
		for(int i=0;i<userid.length;i++){
			if(userid[i].equals("0")){
				continue;
			}
			
		
	
		 try {
			 String sql="select concat(initial,' ',firstname,' ',lastname) from apm_user  where id='"+userid[i]+"'";
			 ps= connection.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 SummaryReport sr= new  SummaryReport();
		
			if(i>=2){
				sr.setSec_consultant(rs.getString(1)+",");
			}else{
				sr.setSec_consultant(rs.getString(1));
			}
			list.add(sr);
			 }
		
		} catch (Exception e) {
			e.printStackTrace();
		} }
		return list;
	}
	
	
	private ArrayList<SummaryReport> getMLCConsultantList(String userids){
		PreparedStatement ps = null;
		
		 ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		 String id="";
		
		String userid[]= userids.split(",");
		for(int i=0;i<userid.length;i++){
			if(userid[i].equals("0")){
				continue;
			}
			
		
	
		 try {
			 String sql="select name from reference  where id='"+userid[i]+"'";
			 ps= connection.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 SummaryReport sr= new  SummaryReport();
		
			if(i>=2){
				sr.setSec_consultant(rs.getString(1)+",");
			}else{
				sr.setSec_consultant(rs.getString(1));
			}
			list.add(sr);
			 }
		
		} catch (Exception e) {
			e.printStackTrace();
		} }
		return list;
	}

	public ArrayList<Investigation> getLabreport(String fromDate, String toDate, String Testname, String abovebelow,
			String value) {
		PreparedStatement ps= null;
		ArrayList<Investigation> list = new ArrayList<Investigation>();
		
		try {
			fromDate= DateTimeUtils.getCommencingDate1(fromDate);
			toDate= DateTimeUtils.getCommencingDate1(toDate);
			
			String sql="";
			if(!Testname.equals("")||!abovebelow.equals("")||!value.equals("")){
			StringBuffer buffer= new StringBuffer();
			buffer.append(" SELECT apm_client_investigation.lastmodified,clientid,fullname,invstname,obsvalue,invstUnit FROM akdcnagpur.apm_client_investigation ");
			buffer.append(" inner join apm_patient on apm_client_investigation.clientid=apm_patient.id  where ");
			buffer.append(" obsvalue !='0' and obsvalue !=' ' ");
			buffer.append(" and invstname='"+Testname+"' ");
			buffer.append(" and apm_client_investigation.lastmodified between '"+fromDate+"' and '"+toDate+" 23:59:59' ");
			buffer.append(" and obsvalue "+abovebelow+" "+value+" ");
			buffer.append(" order by clientid, lastmodified ");
			
			sql=buffer.toString();
			}
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				Investigation investigation= new Investigation();
				investigation.setDate(rs.getString(1));
				investigation.setClientId(rs.getString(2));
				ClientDAO clientDAO= new JDBCClientDAO(connection);
				Client client= clientDAO.getClientDetails(investigation.getClientId());
				investigation.setAbrivationid(client.getPatientIdAbrivation());
				investigation.setClientname(rs.getString(3));
				investigation.setName(rs.getString(4));
				investigation.setObsvalue(rs.getString(5));
				investigation.setInvstUnit(rs.getString(6));
				investigation.setObsvalue(investigation.getObsvalue()+" "+investigation.getInvstUnit());
				list.add(investigation);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Investigation> getNewInvestigationReport(String fromDate, String toDate,
			String investigationType) {
		ArrayList<Investigation> list= new ArrayList<Investigation>();
		PreparedStatement ps=null;
		try {
			fromDate= DateTimeUtils.getCommencingDate1(fromDate);
			toDate= DateTimeUtils.getCommencingDate1(toDate);
			StringBuffer buffer= new StringBuffer();
			buffer.append("  select clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other, apm_client_parent_investigation.lastmodified,apm_client_parent_investigation.reporttype,apm_client_parent_investigation.id,ipdid,upstatus, apm_client_parent_investigation.status,charginvoiceid,dateofupdated,dateofcompleted,compstatus,compdate,deleted, ");
			buffer.append(" a.name,a.charge,count(a.name)  ");
			buffer.append("   from apm_client_parent_investigation ");
			buffer.append("  inner join apm_investigation_type a on a.id=apm_client_parent_investigation.invsttypeid ");
			buffer.append(" inner join  apm_investigation_section on apm_investigation_section.id = a.department ");
			buffer.append(" where  apm_client_parent_investigation.lastmodified between '"+fromDate+"' and '"+toDate+" 23:59:59' ");
			//investigation type is department id
			if(!investigationType.equals("")){
				buffer.append(" and  a.department='"+investigationType+"' ");
			}
			buffer.append(" group by a.name ");
			String sql=buffer.toString();
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				Investigation investigation= new  Investigation();
				investigation.setName(rs.getString(23));
				investigation.setCount(rs.getInt(25));
				investigation.setDate(rs.getString(11));
				String x[]= investigation.getDate().split(" ");
				 x[0]= DateTimeUtils.getCommencingDate1(x[0]);
				investigation.setDate(x[0]); 
				int deleted=getdeletedInvst(fromDate, toDate, investigation.getName(),"and deleted=1");
				int newinvestigation=getdeletedInvst(fromDate, toDate, investigation.getName(),"and upstatus=0 and compstatus=0");
				int newcollected=getdeletedInvst(fromDate, toDate, investigation.getName(),"and upstatus=0 and compstatus=1");
				int newcompleted= getdeletedInvst(fromDate, toDate, investigation.getName(),"and upstatus=1 and compstatus=1 and apm_client_parent_investigation.status=0");
				int approved=getdeletedInvst(fromDate, toDate, investigation.getName()," and apm_client_parent_investigation.status=1");
				list.add(investigation);
				investigation.setDeleted(String.valueOf(deleted));
				investigation.setNew_invistigation(newinvestigation);
				investigation.setNew_collected(newcollected);
				investigation.setNew_completed(newcompleted);
				investigation.setNew_aproved(approved);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	private int getdeletedInvst(String fromDate, String toDate,String investgationtype ,String condition){
		int result=0;
		PreparedStatement ps= null;
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("  select  ");
			buffer.append(" count(*)  ");
			buffer.append("   from apm_client_parent_investigation ");
			buffer.append("  inner join apm_investigation_type a on a.id=apm_client_parent_investigation.invsttypeid ");
			buffer.append(" where  apm_client_parent_investigation.lastmodified between '"+fromDate+"' and '"+toDate+" 23:59:59' ");
			buffer.append(" "+condition+" "  );
			buffer.append(" and a.name='"+investgationtype+"' ");
			buffer.append(" group by a.name ");
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
			result=	rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<SummaryReport> getnewIpdCurrentPatientlist(String fromDate, String toDate, String practitioner,String admitted) {
		PreparedStatement ps = null;
		ArrayList<SummaryReport > list = new ArrayList<SummaryReport>();
		StringBuffer sql= new StringBuffer();
		ClientDAO clientDAO = new JDBCClientDAO(connection);		
		try {
			
			
			
			if(practitioner==null){
				practitioner="";
			}
			if(toDate==null){
				toDate="";
			}
			if(fromDate==null){
				fromDate="";
			}
			fromDate= DateTimeUtils.getCommencingDate1(fromDate);
			toDate= DateTimeUtils.getCommencingDate1(toDate);
			toDate= toDate + " 23:59:59";
			sql.append("SELECT concat(initial,' ',apm_user.firstname,' ',lastname),concat(title,' ',apm_patient.firstname,' ',surname),admissiondsate,ipd_addmission_form.clientid,apm_ipd_ward.wardname ,bedid,apm_diagnosis.name,ipd_addmission_form.secndryconsult, apm_patient.abrivationid,ipd_addmission_form.mlcrefdoctor,apm_patient.abrivationid ");
			sql.append(" ,ipd_addmission_form.ipdseqno,ipd_addmission_form.ismlc ,apm_treatment_episode.dischargedate,ipd_addmission_form.cancel,apm_treatment_episode.dschargestatus,mlcno,treatmentstatus ,third_party_name_id,ipdabrivationid,apm_patient.dob,apm_patient.gender,  ");
			sql.append("apm_patient.mothername,apm_patient.fathername FROM apm_patient ");
			sql.append("inner join ipd_addmission_form on  ipd_addmission_form.clientid = apm_patient.id ");
			sql.append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
			sql.append("inner join apm_treatment_episode on apm_treatment_episode.id = ipd_addmission_form.treatmentEpisodeId ");
			sql.append("inner join apm_ipd_ward on apm_ipd_ward.id = ipd_addmission_form.wardid ");
			sql.append("left join apm_diagnosis on apm_diagnosis.id= ipd_addmission_form.conditionid ");
		/*	sql.append("inner join apm_ipd_bed on apm_ipd_bed.id = ipd_addmission_form.bedid ");*/
			sql.append(" where ipd_addmission_form.casualty='0' " );
			if(!practitioner.equals("")){
			sql.append( " and ipd_addmission_form.practitionerid="+practitioner+" " );
			}
			if(!fromDate.equals("")||!toDate.equals("")){
				sql.append(" and ipd_addmission_form.admissiondsate between '"+fromDate+"' and '"+toDate+"' ");
			}
		/*	if(admitted.equals("admitted")){
				sql.append(" and bedid!=0 ");
			}*/
			sql.append(" order by ipdseqno desc");
			ps= connection.prepareStatement(sql.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setPractitionerName(rs.getString(1));
			
				summaryReport.setClientname(rs.getString(2));
			
				String ward1=rs.getString(5);
				//String bed=rs.getString(6);
				summaryReport.setWardbed(ward1+" / "+ rs.getString(11));
				summaryReport.setAdmissiondate(rs.getString(3));
				summaryReport.setClientId(rs.getString(4));
				
				Client client = clientDAO.getClientDetails(summaryReport.getClientId());
				String age = DateTimeUtils.getAge1(client.getDob());
                summaryReport.setAgegender(age+"/" + client.getGender());

				summaryReport.setDiagnosis(rs.getString(7));
			
				//String Seconderyconsult= rs.getString(8);
				summaryReport.setAbrivationid(rs.getString(9));
				//String mlcdoctor= rs.getString(10);
			/*	ArrayList<SummaryReport> mlclist= new ArrayList<SummaryReport>();
				ArrayList<SummaryReport> secconlist= new ArrayList<SummaryReport>();
				secconlist= getSeconderyConsultantList(Seconderyconsult);
				summaryReport.setSec_consultantlist(secconlist);	
				mlclist= getMLCConsultantList(mlcdoctor);
				summaryReport.setMlclist(mlclist);*/
			
				if(summaryReport.getAdmissiondate()!=null){
					/* String date3= summaryReport.getAdmissiondate();
					 String date[]= date3.split(" ");
					 String date1[]=date[0].split("-");
					  date[0]= date1[2]+"/"+date1[1]+"/"+date1[0];
					  summaryReport.setAdmissiondate(date[0]);
					String df[]=toDate.split(" ");
					String todays=df[0];
					Date d1=new SimpleDateFormat("dd/MM/yyyy").parse(date[0]);
					Date d2= new SimpleDateFormat("yyyy-MM-dd").parse(todays);
					
					long diff=d2.getTime()- d1.getTime();
					long difference= (Math.abs((diff / (1000*60*60*24))));
					
					summaryReport.setAdmitdays(difference);
				*/
					String y[]=summaryReport.getAdmissiondate().split(" ");
					String h[]=y[0].split("-");
					summaryReport.setAdmissiondate(h[2]+"-"+h[1]+"-"+h[0]+" "+y[1]);
				}
				else{
					summaryReport.setAdmissiondate(" ");
				}
				summaryReport.setIsmlc(rs.getString(13));
				summaryReport.setIpdid(rs.getString(12));
				summaryReport.setDischargedate(rs.getString(14));
				summaryReport.setMlcno(DateTimeUtils.isNull(rs.getString(17)));
				summaryReport.setTpName(gettpname1(rs.getString(19)));
				summaryReport.setIpdabrivationid(rs.getString(20));
				summaryReport.setDob(rs.getString(21));
				summaryReport.setGender(rs.getString(22));
				summaryReport.setFathername(rs.getString(23));
				summaryReport.setMothername(rs.getString(24));
				summaryReport.setSec_consultant(getSeconderyConsultantName(rs.getString(8)));;
				
				if(summaryReport.getDischargedate()==null){
					summaryReport.setDischargedate("");
				}
				summaryReport.setCancel(rs.getInt(15));
				if(!summaryReport.getDischargedate().equals("")){
				String x[]= summaryReport.getDischargedate().split(" ");
				summaryReport.setDischargedate(DateTimeUtils.getCommencingDate1(x[0])+" "+x[1]);
				}
				summaryReport.setDischarge(""+rs.getInt(18));
				if(!admitted.equals("admitted")){
					if(rs.getInt(16)==3){
						summaryReport.setIsdead(true);
					}
					list.add(summaryReport);
				}else{
					if(summaryReport.getDischargedate().equals("")){
						if(rs.getInt(15)==1){
							summaryReport.setDischargedate("Cancelled");
						}
						list.add(summaryReport);
					}
				}
			}
	 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getSeconderyConsultantName(String string) {
		String data="";
		try {
			String sql="select concat(initial,' ',apm_user.firstname,' ',lastname) from apm_user where id in ("+string+")";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if(data.equals("")){
					data = resultSet.getString(1);
				}else{
					data = data +","+ resultSet.getString(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public String getTotalItypePayment(String fromdate, String todate, String userid, int i) {
		String string = "0";
		try {
			todate = todate +" "+ "59:59:59";
			//String sql ="select sum(payment) from apm_charges_payment where date between '"+fromdate+"' and '"+todate+"' and paymode!='prepayment' and userid='"+userid+"' and paymode='"+paymentmode+"'";
			StringBuffer buffer= new StringBuffer();
			buffer.append("select sum(payment),itype,userid from apm_charges_payment ");
			buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid ");
			buffer.append("where apm_charges_payment.date between '"+fromdate+"' and '"+todate+"' and apm_charges_payment.paymode!='prepayment' and userid='"+userid+"' and itype='"+i+"' ");
			buffer.append("group by itype ");
			PreparedStatement statement = connection.prepareStatement(buffer.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				string = rs.getString(1);
			}if(string==null){
				string ="0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public ArrayList<Accounts> getShareChargeList(String fromDate, String toDate, String practitionerId, String clientid) {
		ArrayList<Accounts> arrayList= new ArrayList<Accounts>();
		try {
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			StringBuilder builder = new StringBuilder();
			builder.append("select user,apmtType,sharedrid,concat(initial,' ',firstname,' ',lastname),chargeId,commencing,charge from apm_invoice_assesments ");
			builder.append("inner join apm_user on apm_user.id = apm_invoice_assesments.sharedrid ");
			builder.append("where commencing between '"+fromDate+"' and '"+toDate+"' and isshareablecharge=1 ");
			if(clientid!=null){
				builder.append("and apm_invoice_assesments.clientId='"+clientid+"' ");
			}
			
			
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setClientName(rs.getString(1));
				accounts.setAppointmentType(rs.getString(2));
				accounts.setPractitionerName(rs.getString(4));
				
				int drid = rs.getInt(3);
				int chargeid = rs.getInt(5);
				String shareamount = getDrShareAmount(drid,chargeid);
				String datetime = rs.getString(6);
				String[] data= datetime.split(" ");
				String datetime1 = DateTimeUtils.getCommencingDate1(data[0]);
				String finaldatetime = datetime1;
				accounts.setDate(finaldatetime);
				accounts.setAmountx(rs.getString(7));
				accounts.setTotalAmountx(shareamount);
				arrayList.add(accounts);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private String getDrShareAmount(int drid, int chargeid) {
		String data ="0";
		try {
			String sql ="select shareamt from usersharecharge where chargeid='"+chargeid+"' and userid='"+drid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				data = rs.getString(1);
				if(data==null){
					data ="0";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
//lokesh
	public ArrayList<Investigation> getinvstPkgList(String fromDate, String toDate, String practitionerId,
			String pkgid) {
		ArrayList<Investigation> list= new ArrayList<Investigation>();
		fromDate= DateTimeUtils.getCommencingDate1(fromDate);
		toDate= DateTimeUtils.getCommencingDate1(toDate)+" 23:59:59";
		PreparedStatement ps= null;
		String name1="", name2="",name3="";
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("  select a.id,c.abrivationid,concat(c.title,' ',c.firstname,' ',c.surname) ,b.name,a.lastmodified, concat('Dr.',d.firstname,' ',d.lastname),b.amount,e.name   ");
			buffer.append("  from apm_client_parent_investigation a   ");
			buffer.append("  inner join his_parent_package b on b.id= a.pkg   ");
			buffer.append("  inner join apm_patient c on  c.id = a.clientid   ");
			buffer.append("  inner join apm_user d on d.id= a.practitionerid   ");
			buffer.append("  inner join apm_investigation_type e on e.id= a.invsttypeid  ");
			buffer.append("  where  a.lastmodified between '"+fromDate+"' and '"+toDate+"' ");
			if(!practitionerId.equals("")){
				buffer.append(" and a.practitionerid ='"+practitionerId+"' ");	
			}
			if(!pkgid.equals("")){
				buffer.append(" and a.pkg='"+pkgid+"' ");
			}
			
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				Investigation invst= new  Investigation();
				invst.setId(rs.getInt(1));
				

				if(!name2.equals(rs.getString(2))){
					name2= rs.getString(2);
					invst.setAbrivationid(rs.getString(2));
				}else{
					invst.setAbrivationid("");
				}
				if(!name1.equals(rs.getString(3))){
					name1= rs.getString(3);
					invst.setClientname(rs.getString(3));
					invst.setAmmount(rs.getString(7));
				}else{
					invst.setClientname("");
					invst.setAmmount("");
				}
				
				invst.setPackagename(rs.getString(4));
				invst.setDate(rs.getString(5));
					String str[]= invst.getDate().split(" ");
					str[0]= DateTimeUtils.getCommencingDate1(str[0]);
					invst.setDate(str[0]+"/"+str[1]);
				invst.setPractitionerName(rs.getString(6));
			
				
				invst.setInvsttype(rs.getString(8));
				list.add(invst);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Product> getallstockreportlist(String fromdate, String todate) {
		ArrayList<Product> list= new ArrayList<Product>();
		PreparedStatement ps= null;
		try {
			double totalpurprice=0;
			StringBuffer buffer= new StringBuffer(); 
			fromdate= DateTimeUtils.getCommencingDate1(fromdate);
			todate=  DateTimeUtils.getCommencingDate1(todate);
			buffer.append(" select  a.id,a.qty,a.purchase_price,a.date,b.prodname from inventory_stock_log a ");
			buffer.append(" inner join inventory_product b on b.id=a.productid  ");
			if(!fromdate.equals("")){
				buffer.append(" where a.date between '"+fromdate+"' and '"+todate+"' ");
			}
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				Product product= new Product();
				product.setId(rs.getInt(1));
				product.setQuantity(rs.getString(2));
				product.setPurchase_price(rs.getString(3));
				product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(4)));
				product.setName(rs.getString(5));
				if(product.getQuantity()==null){
					product.setQuantity("0");
				}
				if(product.getPurchase_price()==null){
					product.setPurchase_price("0");
				}
				totalpurprice= (Double.parseDouble(product.getQuantity())*Double.parseDouble(product.getPurchase_price()))+totalpurprice;
				product.setTotalAmt(totalpurprice);
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	 public String gettpname1(String tpid){
			PreparedStatement ps=null;
			String res="";
			String sql="select company_name from apm_third_party_details  where id='" +tpid+"'";
			try {
			
				ps= connection.prepareStatement(sql);
				ResultSet rs= ps.executeQuery();
				while(rs.next()){
					res=rs.getString(1);
					
				}
				if(res==""){
					res="Self";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
			
		}

	public ArrayList<Accounts> getInvoiceReportDepartmentWise(String fromdate, String todate) {
		ArrayList<Accounts> arrayList = new ArrayList<Accounts>();
		try {
			StringBuffer buffer = new StringBuffer();
			/*buffer.append("SELECT sum(charge*quantity),department,discipline ");
			buffer.append("FROM apm_invoice_assesments inner join apm_invoice on apm_invoice.id = apm_invoice_assesments.invoiceid ");
			buffer.append("inner join apm_patient on apm_patient.id = apm_invoice_assesments.clientId ");
			buffer.append("inner join apm_discipline on apm_discipline.id =apm_invoice_assesments.department ");
			buffer.append("where commencing between '"+fromdate+"' AND '"+todate+"' and apm_invoice_assesments.active=1 ");
			buffer.append("and apm_invoice_assesments.paybuy='0'   group by department order by sum(charge*quantity) desc ");*/
			buffer.append("select sum(debit),apm_discipline.discipline,sum(discamt),discount,disctype from apm_charges_invoice ");
			buffer.append("inner join apm_user on apm_user.id = apm_charges_invoice.practid ");
			buffer.append("inner join apm_discipline on apm_discipline.id =apm_user.discription ");
			buffer.append("where date between '"+fromdate+"' and '"+todate+"' ");
			//buffer.append("and (itype='1' or itype='2' or itype='3' or itype='4' or itype='5' or itype='6' or itype='7' )  ");
			buffer.append("group by apm_user.discription order by sum(debit) desc ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				//double totalx = getInvoiceReportDepartTotal(rs.getInt(1),fromdate,todate);
				accounts.setTotalAmountx(DateTimeUtils.changeFormat(rs.getDouble(1)));
				accounts.setDepartment(rs.getString(2));
				arrayList.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private double getInvoiceReportDepartTotal(int int1, String fromdate, String todate) {
		double  totalx =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select debit,discount,disctype,discamt from apm_charges_invoice ");
			buffer.append("inner join apm_user on apm_user.id = apm_charges_invoice.practid ");
			buffer.append("inner join apm_discipline on apm_discipline.id =apm_user.discription ");
			buffer.append("where date between '"+fromdate+"' and '"+todate+"' and apm_discipline.id='"+int1+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				double r1 = (rs.getDouble(1)*rs.getDouble(2))/100;
				int disctype=rs.getInt(3);
				double discamt= rs.getDouble(4);
				if(disctype==0){
					discamt= r1;
				}else{
					r1= discamt;
				}
				double total = rs.getDouble(1);
				total = total-r1;
				totalx = totalx+total;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalx;
	}

	public ArrayList<SummaryReport> getpractshareopdnew(String fromdate, String todate) {
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		fromdate= DateTimeUtils.getCommencingDate1(fromdate);
		todate= DateTimeUtils.getCommencingDate1(todate);
		PreparedStatement ps= null;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("  SELECT count(apm_invoice_assesments.id),sum(apm_invoice_assesments.charge)  ");
			buffer.append("  ,concat(initial,' ',firstname,' ',lastname),vivitingper,((vivitingper*sum(apm_invoice_assesments.charge))/100),apm_user.id,  ");
			buffer.append("  apm_discipline.discipline ,apm_discipline.id ");
			buffer.append("  FROM apm_invoice_assesments inner join apm_invoice on apm_invoice_assesments.invoiceid = apm_invoice.id  ");
			buffer.append("  inner join apm_available_slot on apm_available_slot.id = apm_invoice_assesments.appointmentid  ");
			buffer.append("  inner join apm_user on apm_available_slot.diaryuserid = apm_user.id  ");
			buffer.append("  left join apm_discipline on apm_discipline.id=apm_user.discription  ");
			buffer.append("  where apm_invoice_assesments.appointmentid!=0 and apm_invoice.chargetype='Submit' and apm_available_slot.procedures='0'  ");
			buffer.append("  and apm_available_slot.commencing between '"+fromdate+"' AND '"+todate+"'  ");
			buffer.append("  group by apm_user.discription order by apm_user.id  ");
			buffer.append("   ");
			int x=0;
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				SummaryReport sr= new SummaryReport();
				sr.setTotal(rs.getInt(1));
				sr.setCharge(rs.getString(2));
				sr.setPractitionerName(rs.getString(3));
				sr.setPercent(rs.getInt(4));
				sr.setDepartment(rs.getString(7));
				sr.setTotalApp(rs.getInt(5));
				x=x+rs.getInt(1);
				sr.setPatientcount(x);
				
				list.add(sr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private int getpatientCount(String deptid,String fromdate,String todate){
		int res=0;
		PreparedStatement ps= null;
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("  SELECT count(apm_user.discription)  ");
			buffer.append("   ");
			buffer.append("    ");
			buffer.append("  FROM apm_invoice_assesments inner join apm_invoice on apm_invoice_assesments.invoiceid = apm_invoice.id  ");
			buffer.append("  inner join apm_available_slot on apm_available_slot.id = apm_invoice_assesments.appointmentid  ");
			buffer.append("  inner join apm_user on apm_available_slot.diaryuserid = apm_user.id  ");
			buffer.append("  left join apm_discipline on apm_discipline.id=apm_user.discription  ");
			buffer.append("  where apm_invoice_assesments.appointmentid!=0 and apm_invoice.chargetype='Submit' and apm_available_slot.procedures='0'  ");
			buffer.append("  and apm_available_slot.commencing between '"+fromdate+"' AND '"+todate+"'  and apm_user.discription='"+deptid+"' ");
			buffer.append("  group by apm_user.discription  ");
			buffer.append("   ");
			
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<SummaryReport> getwardwiseavgstayReport(String fromdate, String todate) {
		ArrayList<SummaryReport> list=  new ArrayList<SummaryReport>();
		PreparedStatement ps= null;
		fromdate= DateTimeUtils.getCommencingDate1(fromdate);
		todate= DateTimeUtils.getCommencingDate1(todate);
		try {
			StringBuffer buffer= new StringBuffer();
			
			buffer.append("SELECT sum(charge*quantity),wardid,wardname FROM apm_invoice_assesments ");
			buffer.append("inner join apm_ipd_ward on apm_ipd_ward.id=apm_invoice_assesments.wardid ");
			buffer.append("where wardid is not null and wardid!='' and wardid!='0' ");
			buffer.append("and commencing between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("group by wardid order by sum(charge*quantity) desc; ");
			
			String sql= "update  daily_wardwise_patient set totalbed=count where (count+0)>(totalbed+0)";
			PreparedStatement ps2= connection.prepareStatement(sql);
			int c=ps2.executeUpdate();
			ps= connection.prepareStatement(buffer.toString());
			
			ResultSet rs= ps.executeQuery();
			String temp="";
			int x=0;
			BedDao bedDao= new JDBCBedDao(connection);
			while(rs.next()){
				SummaryReport summaryReport= new SummaryReport();
				summaryReport.setWardname(rs.getString(3));
				summaryReport.setWardid(rs.getString(2));
				summaryReport.setCharge(DateTimeUtils.changeFormat(rs.getDouble(1)));
				
				int patientcount=getWardCount(fromdate, todate,rs.getString(2));
				double bedoccupancy=getBedOccupancy(fromdate, todate,rs.getString(2));
				summaryReport.setTotal(patientcount);
				int totalbed=getTotalbedinWard(rs.getString(2));
				summaryReport.setAvgipd(bedoccupancy);
//				summaryReport.setTotal(getpatientCountForWaardwise(q, ""));
//				summaryReport.setAvgipd(Double.parseDouble(String.format("%.2f",(double)summaryReport.getTotal()/rs.getDouble(3))));
//				summaryReport.setTotalpayment(gettotalrevenueWard1(fromdate, todate, rs.getString(1)));
				Ipd ipd=getBedOccupancyrateInNew(fromdate, todate, rs.getString(2));
				int newpatcount=getIpdNewAdmissionWardwise(fromdate, todate, rs.getString(2));
				double d=(double)newpatcount;
				if(d==0.0){
					d=1.0;
				}
				Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(fromdate);
				Date d2= new SimpleDateFormat("yyyy-MM-dd").parse(todate);
				
				long diff=d2.getTime()- d1.getTime();
				long difference= (Math.abs((diff / (1000*60*60*24))));
				summaryReport.setCount(""+bedDao.bedCountOfWard(summaryReport.getWardid()));
				summaryReport.setBedoccupancy(String.valueOf(Math.round((((double)difference/summaryReport.getAvgipd())*100)* 100.0) / 1000.0));
			list.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private double getBedOccupancy(String fromdate, String todate, String wardid) {
		PreparedStatement preparedStatement=null;
		double bedoccu=0;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select avg(count/totalbed)*100 from daily_wardwise_patient ");
			buffer.append("where date between '"+fromdate+"' and '"+todate+"' and wardiid='"+wardid+"' ");
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				bedoccu=Double.parseDouble(String.format("%.2f", rs.getDouble(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bedoccu;
	}

	public int getWardCount(String fromdate, String todate, String wardid) {
		PreparedStatement ps= null;
		int result=0;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("SELECT count(*) FROM apm_invoice_assesments ");
			buffer.append("inner join apm_ipd_ward on apm_ipd_ward.id=apm_invoice_assesments.wardid ");
			buffer.append("where wardid='"+wardid+"' ");
			buffer.append("and commencing between '"+fromdate+"' and '"+todate+"' ");
			buffer.append("group by ipdid ");
ps= connection.prepareStatement(buffer.toString());
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()){;
				result++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getAllIPdis(String fromdate, String todate,String wardid){
		String temp="0,";
		PreparedStatement ps= null;
		try {
			StringBuffer buffer= new StringBuffer();
			
			buffer.append("  select ipdids from daily_wardwise_patient   ");
			buffer.append(" where date between '"+fromdate+"' and '"+todate+"' and wardiid='"+wardid+"'  ");
			
			ps= connection.prepareStatement(buffer.toString());
			
			ResultSet rs= ps.executeQuery();
			
			int x=0;
			while(rs.next()){
				if(rs.getString(1)!=null){
					if(rs.getString(1).contains(",")){
						temp=temp+","+rs.getString(1);
					}
				}
			}
			
			for(String y:temp.split(",")){
				x=x+1;
			}
			temp= String.valueOf(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return temp;
	}
	private int getpatientCountForWaardwise(String ipdids,String wardid){
		int res=0;
		PreparedStatement ps= null;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append(" select count(*) from ipd_addmission_form where id in ("+ipdids+") ");
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	private int gettotalrevenueWard(String fromdate,String todate,String wardid){
		int res=0;
		PreparedStatement ps= null;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("  select sum(apm_charges_invoice.debit)  ");
			buffer.append("  FROM apm_invoice_assesments inner join apm_invoice on apm_invoice_assesments.invoiceid = apm_invoice.id  ");
			buffer.append("  inner join apm_charges_assesment on apm_charges_assesment.invoiceid=apm_invoice.id  ");
			buffer.append("  inner join apm_charges_invoice on apm_charges_invoice.id= apm_charges_assesment.chargeinvoiceid  ");
			buffer.append("  where commencing between '"+fromdate+"' and '"+todate+"' and apm_charges_invoice.chargetype='Submit'  ");
			buffer.append("  and apm_invoice_assesments.wardid='"+wardid+"'  ");
			
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private int gettotalrevenueWard1(String fromdate,String todate,String wardid){
		int res=0;
		PreparedStatement ps= null;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select sum(charge*quantity) from apm_invoice_assesments where wardid='"+wardid+"' and commencing between '"+fromdate+"'  and '"+todate+"'");
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Ipd getBedOccupancyrateInNew(String fromdate, String todate,String wardid) {
		Ipd ipd = new Ipd();
		try {
			String sql ="select count, totalbed from daily_wardwise_patient where date between '"+fromdate+"' and '"+todate+"' and  wardiid='"+wardid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			double totalpatient=0;
			double totalbed=0;
			double totalbedoccupancy= 0; 
			while (rs.next()) {
				totalpatient = totalpatient + rs.getInt(1);
				totalbed = totalbed + rs.getInt(2);
				totalbedoccupancy = (totalpatient*100)/totalbed;
				ipd.setTotalpatient(totalpatient);
				ipd.setTotalbed(totalbed);
				ipd.setTotalbedoccupancy(String.valueOf(Math.round(totalbedoccupancy * 100.0) / 100.0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipd;
	}
	
	public int getIpdNewAdmissionWardwise(String fromdate, String todate,String wardid) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		todate = todate + " 23:59:59";

		String sql = "SELECT count(*) FROM ipd_addmission_form where admissiondsate  between '"
				+ fromdate + "'  and '" + todate + "' and  casualty='0' and cancel='0'  and wardid='"+wardid+"'";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private int getTotalbedinWard(String wardid){
		int res=0;
		PreparedStatement ps= null;
		try{
			String sql=" select count(*) from apm_ipd_bed where wardid='"+wardid+"' and active='1'  ";
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
	
	private double getrefundamt(int invoiceid) {
	double ramount=0.00;
	PreparedStatement ps=null;
	try{
		String sql="select sum(debit) from apm_credit_account where manualinvoiceid="+invoiceid+" group by manualinvoiceid";
		ps=connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			ramount=rs.getDouble(1);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return ramount;
	}
	
	private int getDeptwisePatientCount(String fromdate,String todate, String dept){
		int res=0;
		PreparedStatement ps= null;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select count(*) from ipd_addmission_form  ");
			buffer.append("  inner join apm_user on apm_user.id=practitionerid ");
			buffer.append("  inner join apm_condition on apm_condition.id=discription ");
			buffer.append("  where  ipd_addmission_form.admissiondsate between '"+fromdate+"' and '"+todate+"' and discription='"+dept+"'");
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	private int getDeptwisePatientCountOPD(String fromdate,String todate, String dept){
		int res=0;
		PreparedStatement ps= null;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select count(*) from apm_available_slot  ");
			buffer.append("  inner join apm_user on apm_user.id=diaryuserid ");
			buffer.append("  inner join apm_condition on apm_condition.id=discription ");
			buffer.append("  where  apm_available_slot.commencing between '"+fromdate+"' and '"+todate+"' and discription='"+dept+"'");
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int insertRevenueData(SummaryReportForm summaryReportForm) {
		int res=0;
		PreparedStatement ps= null;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("  insert into total_revenue_report (date,total_ipds,total_opds,bed_ocupancy,avg_length_stay,ipd_revenue,opd_revenue,investigation_revenue,other_revenue,gr_ipd_payment,gr_opd_payment,gr_invst_payment,gr_other_payment,opdtoipdconv,opdtoipdconvratio,total_revenue,conv_revenue , ");
			buffer.append(" totalcredit, credit_return,card_sale,cash_sale ,cheque,neft,cash_return,discount,total_pharm_payment)  ");
			buffer.append("  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			String sql=buffer.toString();                                        
			ps=connection.prepareStatement(sql);
			ps.setString(1, summaryReportForm.getFromDate());
			ps.setInt(2, summaryReportForm.getIpdnewadmission());
			ps.setInt(3, summaryReportForm.getTotalopdseen());
			ps.setString(4, summaryReportForm.getTotalbedoccupancy());
			ps.setString(5, String.valueOf(summaryReportForm.getAveragestay()));
			ps.setString(6, summaryReportForm.getIpddebitcount());
			ps.setString(7, summaryReportForm.getOpddebitcount());
			ps.setString(8, summaryReportForm.getInvestigationdebitcount());
			ps.setString(9, summaryReportForm.getMedicinedebitcount());
			ps.setString(10, summaryReportForm.getNetipddebitcount());
			ps.setString(11, summaryReportForm.getNetopddebitcount());
			ps.setString(12, summaryReportForm.getNetinvestigationdebitcount());
			ps.setString(13, summaryReportForm.getNetmedicinedebitcount());
			ps.setString(14, "");
			ps.setString(15, "");
			ps.setString(16, "");
			ps.setString(17, "");
			ps.setDouble(18,summaryReportForm.getTotalcredit() );
			ps.setDouble(19,summaryReportForm.getCreditReturn() );
			ps.setDouble(20,summaryReportForm.getTodaycard() );
			ps.setDouble(21,summaryReportForm.getTodaycash() );
			ps.setDouble(22,summaryReportForm.getChequepayments() );
			ps.setDouble(23,summaryReportForm.getNeftpayment() );
			ps.setDouble(24,summaryReportForm.getTodayreturn() );
			ps.setDouble(25,summaryReportForm.getTodaydisc() );
			ps.setDouble(26,summaryReportForm.getTotalpayment() );
			int x=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public SummaryReportForm getTotaldataOfRevenueReport(String fromdate,String todate) {
		SummaryReportForm summaryReportForm= new SummaryReportForm();
		PreparedStatement ps= null;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select date,total_ipds,total_opds,bed_ocupancy,avg_length_stay,ipd_revenue,opd_revenue,investigation_revenue,other_revenue,gr_ipd_payment,gr_opd_payment,gr_invst_payment,gr_other_payment,opdtoipdconv,opdtoipdconvratio,total_revenue,conv_revenue ,  ");
			buffer.append("  totalcredit, credit_return,card_sale,cash_sale ,cheque,neft,cash_return,discount,total_pharm_payment  ");
			buffer.append(" from  total_revenue_report where date ='"+fromdate+"'   ");
			String sql=buffer.toString();
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				summaryReportForm.setIpdnewadmission(rs.getInt(2));
				summaryReportForm.setTotalopdseen(rs.getInt(3));
				summaryReportForm.setTotalbedoccupancy(rs.getString(4));
				summaryReportForm.setAveragestay(rs.getDouble(5));
				summaryReportForm.setIpddebitcount(rs.getString(6));
				summaryReportForm.setOpddebitcount(rs.getString(7));
				summaryReportForm.setInvestigationdebitcount(rs.getString(8));
				summaryReportForm.setMedicinedebitcount(rs.getString(9));
				summaryReportForm.setNetipddebitcount(rs.getString(10));
				summaryReportForm.setNetopddebitcount(rs.getString(11));
				summaryReportForm.setNetinvestigationdebitcount(rs.getString(12));
				summaryReportForm.setNetmedicinedebitcount(rs.getString(13));
				//pharmacy
				summaryReportForm.setTotalcredit(rs.getDouble(18));
				summaryReportForm.setCreditReturn(rs.getDouble(19));
				summaryReportForm.setTodaycard(rs.getInt(20));
				summaryReportForm.setTodaycash(rs.getDouble(21));
				summaryReportForm.setChequepayments(rs.getDouble(22));
				summaryReportForm.setNeftpayment(rs.getDouble(23));
				summaryReportForm.setTodayreturn(rs.getDouble(24));
				summaryReportForm.setTodaydisc(rs.getDouble(25));
				summaryReportForm.setTotalpayment(rs.getDouble(26));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryReportForm;
	}

	public int deleteRevenueData(String date) {
		PreparedStatement ps= null;
		try {
			ps= connection.prepareStatement(" delete from total_revenue_report where date ='"+date+"'");
			int x= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<SummaryReport> getIPDReportDetailed() {
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		StringBuffer buffer= new StringBuffer();
		buffer.append(" SELECT  ipd_addmission_form.id,ipd_addmission_form.clientid,concat(apm_patient.title,' ',apm_patient.firstname,' ',apm_patient.surname),gender,dob,town,emergencyContNo,mobno,whopay ");
		buffer.append("  , ipd_addmission_form.department, concat(apm_user.firstname,' ',apm_user.lastname ), apm_ipd_ward.wardname,apm_ipd_bed.bedname  ");
		buffer.append(" FROM ipd_addmission_form  inner join apm_patient on  apm_patient.id=ipd_addmission_form.clientid  ");
		buffer.append(" inner join apm_ipd_ward on apm_ipd_ward.id = ipd_addmission_form.wardid  ");
		buffer.append("  inner join apm_ipd_bed on apm_ipd_bed.id = ipd_addmission_form.bedid ");
		buffer.append("  inner join apm_user on apm_user.id =ipd_addmission_form.practitionerid ");
		buffer.append("  where (bedid!=0 ) and ipd_addmission_form.casualty='0' and apm_ipd_bed.active ='1' ");
	
		buffer.append("   ");
		
		PreparedStatement preparedStatement= null;
		try {
			preparedStatement= connection.prepareStatement(buffer.toString());
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport= new SummaryReport();
				summaryReport.setIpdid(rs.getString(1));
				summaryReport.setClientId(rs.getString(2));
				summaryReport.setClientname(rs.getString(3));
				summaryReport.setGender(rs.getString(4));
				summaryReport.setAge1(DateTimeUtils.getAge1(rs.getString(5)));
				summaryReport.setTown(rs.getString(6));
				summaryReport.setEmergencycontno(rs.getString(7));
				summaryReport.setMobno(rs.getString(8));
				summaryReport.setWhopay(rs.getString(9));
				summaryReport.setDepartment(rs.getString(10));
				summaryReport.setUsername("Dr. "+rs.getString(11));
				summaryReport.setWardname(rs.getString(12));
				summaryReport.setBedname(rs.getString(13));
				summaryReport.setTotalcharge(Math.ceil(getChargesComplete(summaryReport.getIpdid(), "")));
				summaryReport.setTotaladvance(Math.ceil(getAdvanceByIPD(summaryReport.getIpdid())));
				summaryReport.setCashreceived(DateTimeUtils.changeFormat(getSelfBalanceByIPDID(summaryReport.getIpdid())));
					
				list.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return list;
	}
	
	private double getChargesComplete(String ipdid,String payby){
		double res=0;
		PreparedStatement ps= null;
		String buffer=" SELECT sum(charge*quantity) FROM apm_invoice_assesments where ipdid='"+ipdid+"' and  paybuy="+payby+"  ";
		try {
			ps= connection.prepareStatement(buffer);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private double getAdvanceByIPD(String ipdid){
		double res=0;
		PreparedStatement ps= null;
		String sql=" SELECT sum(charge) FROM apm_credit_account where advance_ipdid='"+ipdid+"' and payment_mode is not null   ";
		try {
			ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res= rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private double getSelfBalanceByIPDID(String ipdid){
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer buffer= new StringBuffer();
		/*
		 * buffer.
		 * append("  select apm_charges_invoice.debit,apm_charges_invoice.discount,apm_charges_invoice.discamt,apm_charges_invoice.disctype from apm_charges_invoice   "
		 * ); buffer.
		 * append("  inner join apm_charges_invoice on apm_charges_invoice.id=chargeinvoiceid  "
		 * ); buffer.append("  where  apm_charges_invoice.ipdid='"+ipdid+"' ");
		 */
		
		buffer.append(" select apm_charges_invoice.debit,apm_charges_invoice.discount,apm_charges_invoice.discamt,apm_charges_invoice.disctype from apm_charges_invoice ");
		buffer.append("where  apm_charges_invoice.ipdid='"+ipdid+"' ");
		double debittotal = 0;
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double debit = rs.getDouble(1);
				double discount = rs.getDouble(2);
				double discamt = rs.getDouble(3);
				int disctype = rs.getInt(4);
				
				double d = (debit*discount)/100;
				if(disctype==1){
					d = discamt;
				}
				debittotal = debit - d;
				
				result = result + debittotal;
				
			}
		}catch (Exception e) {
			// TODO: handle exception
	
	}
		return result;
	}
	
	private double getPatientTotalDiscountByIPD(String ipdid ) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select debit,discount,discamt,disctype from apm_charges_invoice " +
				"where ipdid = "+ipdid+" and payby='Client' ";
		double debittotal = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				double debit = rs.getDouble(1);
				double discount = rs.getDouble(2);
				double discamt = rs.getDouble(3);
				int disctype = rs.getInt(4);
				double d = (debit*discount)/100;
				if(disctype==1){
					d = discamt;
				}
				//debittotal = debit - d;
				//result = result + debittotal;
				result = result+d;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private double getcashRecivedByIPDID(String ipdid){
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer buffer= new StringBuffer();
		buffer.append("  select sum(payment) from apm_charges_payment   ");
		buffer.append("  inner join apm_charges_invoice on apm_charges_invoice.id=chargeinvoiceid  ");
		buffer.append("  where  apm_charges_invoice.ipdid='"+ipdid+"' and apm_charges_payment.tpid = 0 ");
	
		try {
			preparedStatement= connection.prepareStatement(buffer.toString());
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				result= rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<SummaryReportForm> getTotalRevenueList(String fromdate, String todate) {
		ArrayList<SummaryReportForm> list=new ArrayList<SummaryReportForm>();
		PreparedStatement ps= null;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select date,total_ipds,total_opds,bed_ocupancy,avg_length_stay,ipd_revenue,opd_revenue,investigation_revenue,other_revenue,gr_ipd_payment,gr_opd_payment,gr_invst_payment,gr_other_payment,opdtoipdconv,opdtoipdconvratio,total_revenue,conv_revenue ,  ");
			buffer.append("  totalcredit, credit_return,card_sale,cash_sale ,cheque,neft,cash_return,discount,total_pharm_payment  ");
			buffer.append(" from  total_revenue_report where date between '"+fromdate+"' and '"+todate+"' order by date asc  ");
			String sql=buffer.toString();
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				SummaryReportForm summaryReportForm= new SummaryReportForm();
				//yr
				summaryReportForm.setYr(rs.getString(1).split("-")[0]);
				//mnth
				summaryReportForm.setMnth(rs.getString(1).split("-")[1]);
				summaryReportForm.setIpdnewadmission(rs.getInt(2));
				summaryReportForm.setTotalopdseen(rs.getInt(3));
				summaryReportForm.setTotalbedoccupancy(rs.getString(4));
				summaryReportForm.setAveragestay(rs.getDouble(5));
				summaryReportForm.setIpddebitcount(rs.getString(6));
				summaryReportForm.setOpddebitcount(rs.getString(7));
				summaryReportForm.setInvestigationdebitcount(rs.getString(8));
				summaryReportForm.setMedicinedebitcount(rs.getString(9));
				summaryReportForm.setNetipddebitcount(rs.getString(10));
				summaryReportForm.setNetopddebitcount(rs.getString(11));
				summaryReportForm.setNetinvestigationdebitcount(rs.getString(12));
				summaryReportForm.setNetmedicinedebitcount(rs.getString(13));
				//pharmacy
				summaryReportForm.setTotalcredit(rs.getDouble(18));
				summaryReportForm.setCreditReturn(rs.getDouble(19));
				summaryReportForm.setTodaycard(rs.getInt(20));
				summaryReportForm.setTodaycash(rs.getDouble(21));
				summaryReportForm.setChequepayments(rs.getDouble(22));
				summaryReportForm.setNeftpayment(rs.getDouble(23));
				summaryReportForm.setTodayreturn(rs.getDouble(24));
				summaryReportForm.setTodaydisc(rs.getDouble(25));
				summaryReportForm.setTotalpayment(rs.getDouble(26));
				summaryReportForm.setAllttl(""+(rs.getInt(6)+rs.getInt(7)+rs.getInt(8)+rs.getInt(9)));
				list.add(summaryReportForm);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SummaryReport> getIPDreportTPWisePatients() {
		ArrayList<SummaryReport> list= new  ArrayList<SummaryReport>();
		StringBuffer buffer= new StringBuffer();
		buffer.append("  select apm_third_party_details.company_name,tpid from ipd_addmission_form  left join apm_third_party_details on apm_third_party_details.id=ipd_addmission_form.tpid  ");
		buffer.append(" inner join apm_ipd_bed on apm_ipd_bed.id = ipd_addmission_form.bedid  where (bedid!='0' or bedid is not null) and ipd_addmission_form.casualty='0' and active ='1' ");
		buffer.append("  group by ipd_addmission_form.tpid  ");
		try {
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport= new SummaryReport();
				summaryReport.setTpName(rs.getString(1));
				if(rs.getInt(2)==0){
					summaryReport.setTpName("Self");
				}
				summaryReport.setOtlist(getPatientRpoetIpdIDTpWise(rs.getInt(2)));
				list.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private ArrayList<SummaryReport> getPatientRpoetIpdIDTpWise(int tpid){
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		StringBuffer buffer= new StringBuffer();
		buffer.append(" SELECT apm_third_party_details.company_name,concat(initial,' ',apm_user.firstname,' ',lastname),concat(apm_patient.title,' ',apm_patient.firstname,' ',surname),admissiondsate,  ");
		buffer.append("   ipd_addmission_form.clientid,apm_ipd_ward.wardname ,bedid, ipd_addmission_form.secndryconsult,   ");
		buffer.append("    apm_patient.abrivationid,ipd_addmission_form.mlcrefdoctor,bedname,ipd_addmission_form.tpid,ipdseqno,ipd_addmission_form.tpid ,apm_patient.dob,ipd_addmission_form.id ");
		buffer.append("    from ipd_addmission_form  ");
		buffer.append("  inner join apm_patient on apm_patient.id=ipd_addmission_form.clientid   inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid    ");
		buffer.append("   inner join apm_ipd_ward on apm_ipd_ward.id = ipd_addmission_form.wardid  inner join apm_ipd_bed on apm_ipd_bed.id = ipd_addmission_form.bedid   ");
		buffer.append("   left join apm_third_party_details on apm_third_party_details.id=ipd_addmission_form.tpid   ");
		buffer.append("   where (bedid!='0' or bedid is not null) and ipd_addmission_form.casualty='0' and active ='1' and ipd_addmission_form.tpid='"+tpid+"'   ");
		buffer.append("  order by ipd_addmission_form.wardid     ");
		buffer.append("      ");
		try {
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport= new  SummaryReport();
				summaryReport.setUsername(rs.getString(2));
				summaryReport.setPatientName(rs.getString(3));
				String datetime[]=rs.getString(4).split(" ");
				summaryReport.setAdmissiondate(DateTimeUtils.getCommencingDate1(datetime[0])+" "+datetime[1]);
				summaryReport.setClientId(rs.getString(5));
				summaryReport.setWardname(rs.getString(6));
				summaryReport.setAbrivationid(rs.getString(9));
				summaryReport.setBedname(rs.getString(11));
				summaryReport.setTpid(rs.getInt(12));
				summaryReport.setIpdseqno(rs.getString(13));
				summaryReport.setAge1(DateTimeUtils.getAge1(rs.getString(15)));
				summaryReport.setTpcharge(String.valueOf(getChargesComplete(rs.getString(16), "1")));
				summaryReport.setSelfcharge(String.valueOf(getChargesComplete(rs.getString(16), "0")));
				summaryReport.setAdvance(String.valueOf(getAdvanceByIPD(rs.getString(16))));
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    Calendar cal = Calendar.getInstance();
			    String todays = dateFormat.format(cal.getTime());  
			    Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(datetime[0]);
				Date d2= new SimpleDateFormat("yyyy-MM-dd").parse(todays);
				long diff=d2.getTime()- d1.getTime();
				long difference= (Math.abs((diff / (1000*60*60*24))));
				summaryReport.setAdmitdays(difference);
				list.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SummaryReport> getPackageReportList(String fromdate, String todate) {
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		try {
			fromdate=DateTimeUtils.getCommencingDate1(fromdate)+" 00:00:00";
			todate= DateTimeUtils.getCommencingDate1(todate)+" 23.59.59";
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select apm_parent_patient_package.fromdate, apm_parent_patient_package.todate,apm_parent_patient_package.amount,his_parent_package.name  ");
			buffer.append("  ,concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),concat(apm_patient.title,' ',apm_patient.firstname,' ',apm_patient.surname)  ");
			buffer.append(" ,apm_parent_patient_package.id  ");
			buffer.append("  from apm_parent_patient_package  ");
			buffer.append("  inner join his_parent_package on his_parent_package.id=apm_parent_patient_package.packageid  ");
			buffer.append("  inner join ipd_addmission_form on ipd_addmission_form.id=apm_parent_patient_package.ipdid  ");
			buffer.append("  inner join apm_patient on apm_patient.id=apm_parent_patient_package.clientid  ");
			buffer.append("  inner join apm_user on apm_user.id=ipd_addmission_form.practitionerid  ");
			buffer.append("  where third_party='1' and  ((apm_parent_patient_package.fromdate between '"+fromdate+"' and '"+todate+"') or (apm_parent_patient_package.todate between '"+fromdate+"' and '"+todate+"'))  ");
			
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				SummaryReport summaryReport= new SummaryReport();
				summaryReport.setFromDate(rs.getString(1));
				summaryReport.setToDate(rs.getString(2));
				summaryReport.setTotalipdamount(rs.getString(3));
				summaryReport.setIpdpkg(rs.getString(4));
				summaryReport.setPractitionerName(rs.getString(5));
				summaryReport.setClientname(rs.getString(6));
				summaryReport.setId(rs.getInt(7));
				summaryReport.setInvoiceid(getinvoiceid(rs.getInt(7)));
				Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(summaryReport.getFromDate());
				Date d2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(summaryReport.getToDate());
				long diff=d2.getTime()- d1.getTime();
				long difference= (Math.abs((diff / (1000*60*60*24))));
				summaryReport.setAdmitdays(difference);
				summaryReport.setFromDate(DateTimeUtils.getCommencingDate1(summaryReport.getFromDate().split(" ")[0])+" "+summaryReport.getFromDate().split(" ")[1]);
				summaryReport.setToDate(DateTimeUtils.getCommencingDate1(summaryReport.getToDate().split(" ")[0])+" "+summaryReport.getToDate().split(" ")[1]);
				list.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private int getinvoiceid(int pkgid){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement("select invoiced from apm_invoice_assesments where tpkg!='0' and tpkg='"+pkgid+"'   ");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<SummaryReport> getIPDOPDInvestigationRevenue(String fromdate, String todate, String type,
			String practitionerId,String itype,String search) {
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		try {
			StringBuffer buffer= new  StringBuffer();
			buffer.append("select apm_invoice_assesments.id,concat(apm_patient.title,' ',apm_patient.firstname,' ',apm_patient.surname),apmtType,charge*quantity,commencing,invoiced,discrs,discper,concat(initial,' ',apm_user.firstname,' ',apm_user.lastname),apm_invoice_assesments.clientId,"
					+ "disctype,discamt,payment,apm_invoice_assesments.ipdid,abrivationid from apm_invoice_assesments ");
			buffer.append("  inner join apm_user on apm_user.id =apm_invoice_assesments.practitionerId  ");
			buffer.append(" left join apm_charges_invoice on apm_charges_invoice.id=invoiced ");
			buffer.append(" left join apm_charges_payment on apm_charges_payment.chargeinvoiceid=invoiced ");
			buffer.append(" inner join apm_patient on apm_patient.id=apm_invoice_assesments.clientId ");
			buffer.append(" where masterchargetype in ('Pathlab','Radiology','CARDIOLOGY','MICROBIOLOGY','INVESTIGATION')  and commencing between '"+fromdate+"' and '"+todate+"'  ");
			
//			StringBuffer buffer2= new  StringBuffer();
//			buffer2.append(" select apm_invoice_assesments.id,apm_invoice_assesments.user,apmtType,charge*quantity,commencing,invoiced,discrs,discper,concat(initial,' ',firstname,' ',lastname),apm_invoice_assesments.clientId,invoiced ");
//			buffer2.append(" from apm_invoice_assesments  inner join apm_user on apm_user.id =apm_invoice_assesments.practitionerId  ");
//			buffer2.append(" left join apm_charges_invoice on apm_invoice_assesments.invoiced=apm_charges_invoice.id ");
//			buffer2.append("  where masterchargetype in ('Pathlab','Radiology','CARDIOLOGY','MICROBIOLOGY','INVESTIGATION')  and commencing between '"+fromdate+"' and '"+todate+"'   ");
			if(!search.equals("")){
				buffer.append(" and apm_invoice_assesments.user like '%"+search+"%' or abrivationid like '%"+search+"%' ");
			}
			if(type.equals("ipd")){
				buffer.append("  and apm_invoice_assesments.ipdid not in ('','0') ");
			}else if(type.equals("opd")){
				buffer.append("  and apm_invoice_assesments.ipdid  in ('','0') ");
			}
			
			if(itype.equals("paid")){
				buffer.append(" and payment+0>0");
			}else if (itype.equals("unpaid")) {
				buffer.append(" and  IFNULL(payment,0)<=0");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SummaryReport summaryReport= new SummaryReport();
				summaryReport.setClientname(rs.getString(2));
				summaryReport.setApptName(rs.getString(3));
				summaryReport.setCharge(rs.getString(4));
				summaryReport.setDate(DateTimeUtils.getCommencingDate1(rs.getString(5)));
				summaryReport.setInvoiceid(rs.getInt(6));
				summaryReport.setPractitionerName(rs.getString(9));
				double discrs=0,discper=0;
				if(rs.getInt(11)>0){
					//amt
					summaryReport.setDiscamt(rs.getDouble(12));
					summaryReport.setTotalinvstamount(DateTimeUtils.numberCheck(rs.getString(13)));
				}else{
					//per
					double discmt=(rs.getDouble(12)/100.0)*rs.getDouble(4);
					summaryReport.setDiscamt((Math.round(discmt*100.0)/100.0));
					summaryReport.setTotalinvstamount(DateTimeUtils.numberCheck(rs.getString(13)));
				}
				summaryReport.setIpdid(DateTimeUtils.numberCheck(rs.getString(14)));
				summaryReport.setAbrivationid(rs.getString(15));
				list.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Accounts> getPaymentRepportNoteList(String fromdate, String todate, String status,
			LoginInfo loginInfo) {
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		
		try {
			fromdate=DateTimeUtils.getCommencingDate1(fromdate)+" 00:00:00";
			todate=DateTimeUtils.getCommencingDate1(todate)+" 23:59:59";
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("  select id,notes,status,reqdate, userid,approve_date,approver_userid,pending_date, pending_userid  from paymentreport_notes ");
			buffer.append(" where  reqdate between '"+fromdate+"' and '"+todate+"'  ");
			if(loginInfo.getJobTitle().equals("Accounts")||loginInfo.getJobTitle().equals("BILLING INCHARGE")||loginInfo.getJobTitle().equals("Admin")){
				
			}else{
				buffer.append(" and userid='"+loginInfo.getUserId()+"'");
			}
			
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			while (rs.next()) {
				Accounts accounts= new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setNotes(rs.getString(2));
				accounts.setStatus(""+rs.getInt(3));
				accounts.setUserid(rs.getString(5));
				String dt[]=rs.getString(4).split(" ");
				accounts.setDate(DateTimeUtils.getCommencingDate1(dt[0])+" "+dt[1]);
				if(DateTimeUtils.isNull(rs.getString(6)).contains(":")){
					String dt1[]=rs.getString(6).split(" ");
					accounts.setApprDate(DateTimeUtils.getCommencingDate1(dt1[0])+" "+dt1[1]);
				}
				if(DateTimeUtils.isNull(rs.getString(8)).contains(":")){
					String dt2[]=rs.getString(8).split(" ");
					accounts.setPenDate(DateTimeUtils.getCommencingDate1(dt2[0])+" "+dt2[1]);
				}
				accounts.setApprId(DateTimeUtils.isNull(rs.getString(7)));
				accounts.setPendId(DateTimeUtils.isNull(rs.getString(9)));
				accounts.setUsername(userProfileDAO.getUserNameFromUserid(accounts.getUserid()));
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Accounts> getDiscounchargetReport(String fromdate, String todate, String itype ) {
		ArrayList<Accounts> arrayList= new ArrayList<Accounts>();
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		try {
			todate = todate + " 23:59:59";
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			StringBuilder builder = new StringBuilder();
			builder.append("select discount_request.id,requested_date, disc_type, disc_amount, invoiceamount, clientid,abrivationid,fullname ");
			builder.append("from discount_request inner join apm_patient on apm_patient.id = discount_request.clientid where deleted=0 and invoiceid=0 ");
			builder.append("and requested_date between '"+fromdate+"' and '"+todate+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				accounts.setDisctype("1");
				accounts.setDiscAmmount(rs.getString(4));
				accounts.setDebitAmount(rs.getDouble(5));
				accounts.setClientid(rs.getInt(6));
				accounts.setAbrivationid(rs.getString(7));
				accounts.setClientName(rs.getString(8));
				
				String admissiondate ="";
				String dischargedate="";
				int ipdid=ipdDAO.getLastIpdId(rs.getString(6));
				if(ipdid>0){
					Ipd ipd = getAddandDisDate(ipdid);
					
					if(ipd.getAdmissiondate()!=null){
						String[] admissiondatee = ipd.getAdmissiondate().split(" ");
						admissiondate = DateTimeUtils.getCommencingDate1(admissiondatee[0])+" "+admissiondatee[1];
					}
					
					
					if(ipd.getDischargedate()!=null){
						String[] discdate = ipd.getDischargedate().split(" ");
						dischargedate = DateTimeUtils.getCommencingDate1(discdate[0])+" "+discdate[1];
					}
					
					accounts.setAdmissiondate(admissiondate);
					accounts.setDischargedate(dischargedate);
				}else{
					accounts.setAdmissiondate("");
					accounts.setDischargedate("");
				}
				
				
				
				double debit = rs.getDouble(5);
				double total = debit;
				double discount = rs.getDouble(4);
				int disctype=rs.getInt(3);
				double discamt= rs.getDouble(4);
				double r1 = (total*discount)/100;
				
//				if(disctype==0){
//					discamt= r1;
//				}else{
					r1= discamt;
//				}
				
				total = total-r1;
				
				
				accounts.setAmountx(String.valueOf(total));
					double discount_amount_by_per=0.00;
//				if(rs.getString(3).equals("0")){
//					discount_amount_by_per=rs.getDouble(4)/100*rs.getDouble(3);
//					accounts.setDiscountx(rs.getString(3));
//					accounts.setDiscountbyrs(discount_amount_by_per);
//					/*balance=discount_amount_by_per-refundamount;
//				    accounts.setBalance(balance);*/
//				}else{
					discount_amount_by_per=rs.getDouble(4);
					accounts.setDiscountx(String.valueOf(discount_amount_by_per));
					/*balance=rs.getDouble(8)-refundamount;
				    accounts.setBalance(balance);*/
//				}
				 
				 
				    
				arrayList.add(accounts);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int getOtReportCount(String fromDate, String toDate,String otroom, String otuser,String anesthesia) {
		int res=0;
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("  select count(*)  from apm_available_slot ");
			buffer.append("left join apm_ot_parent on apm_ot_parent.apmtid=apm_available_slot.id ");
			buffer.append(" where  apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' and  procedures!='0' ");
			if(!otroom.equals("0")){
				buffer.append(" and diaryusername=' "+otroom+"' ");
			}
			if(!otuser.equals("0")){
				buffer.append(" and surgeon="+otuser+" ");
			}
			if(!anesthesia.equals("0") && !anesthesia.equals("")){
				buffer.append(" and anesthesia="+anesthesia+" ");
			}
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<SummaryReport> getOtReport(String fromDate, String toDate, Pagination pagination,String otroom, String otuser,String anesthesia) {
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("select apm_available_slot.commencing,diaryusername,location,clientname,apmttypetext,category,  ");
			buffer.append("procedures, surgeon, anesthesia, duration, asistantdoctor,ipdno,clientId,apm_available_slot.id  from apm_available_slot ");
			buffer.append("left join apm_ot_parent on apm_ot_parent.apmtid=apm_available_slot.id ");
			buffer.append(" where  apm_available_slot.commencing between '"+fromDate+"' and '"+toDate+"' and  procedures!='0' ");
			if(!otroom.equals("0")){
				buffer.append(" and diaryusername=' "+otroom+"' ");
			}
			if(!otuser.equals("0")){
				buffer.append(" and surgeon="+otuser+" ");
			}
			if(!anesthesia.equals("0") && !anesthesia.equals("")){
				buffer.append(" and anesthesia="+anesthesia+" ");
			}
			String sql = pagination.getSQLQuery(buffer.toString());
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			while (rs.next()) {
				SummaryReport summaryReport=new SummaryReport();
				summaryReport.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(1)));
				summaryReport.setDiaryusername(rs.getString(2));
				summaryReport.setLocation(rs.getString(3));
				summaryReport.setClientname(rs.getString(4));
				summaryReport.setApmttypetext(rs.getString(5));
				summaryReport.setCategory(rs.getString(6));
				summaryReport.setProcedures(rs.getString(7));
				summaryReport.setSurgeon(userProfileDAO.getFullName(rs.getString(8)));
				summaryReport.setAnesthesia(userProfileDAO.getReferalDrName(rs.getString(9)));
				summaryReport.setDuration(rs.getString(10));
				summaryReport.setAsistantdoctor(userProfileDAO.getFullName(rs.getString(11)));
				
				String abrivationid= getPatientAbrivationId(rs.getString(13));
				String ipdabrivationid= getPatientIPDAbrivationId(rs.getString(12));
				summaryReport.setAbrivationid(abrivationid);
				summaryReport.setIpdseqno(ipdabrivationid);
				Client client = clientDAO.getClientDetails(rs.getString(13));
				double payment=getPayment(rs.getString(14));
				summaryReport.setPayment(Double.toString(payment));
				summaryReport.setMobno(client.getMobNo());
				list.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private double getPayment(String otid) {
		double payment = 0;
		try {
			String sql ="select debit from apm_charges_invoice where ot_id='"+otid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				payment = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payment;
		
	}

	public String getPatientIPDAbrivationId(String string) {
		String abrivationid = "";
		try {
			//for report fast:manoj sir
			//String sql ="select ipdabrivationid from ipd_addmission_form where id='"+string+"'";
			String sql ="select ipdabrivationid from ipd_addmission_form_new where id='"+string+"'";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				abrivationid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return abrivationid;
	}

	private String getPatientAbrivationId(String string) {
		String abrivationid = "";
		try {
			String sql ="select abrivationid from apm_patient where id='"+string+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				abrivationid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return abrivationid;
	}

	public ArrayList<Accounts> getPaymentReciptReportList(String fromDate, String toDate, String howpaid, String itype, String paymentStatus, String amountgreaterfilter) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		sql.append("select apm_charges_invoice.id,payby,date,clientid,debit,deliverstatus,discount,tpid,firstname,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype from apm_charges_invoice inner join ");
		sql.append("apm_patient on apm_patient.id = apm_charges_invoice.clientid ");
		sql.append("inner join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");
		sql.append("where apm_charges_payment.date between '"+fromDate+" 00:00:00' AND '"+toDate+" 23:59:59' and paymode!='prepayment' "); ////add inner join apm_charges_payment and chnage date to apm payment
		if(!itype.equals("0")){
		    sql.append(" and  apm_charges_invoice.itype="+itype+" ");
		}
		if(!amountgreaterfilter.equals("0")){
			 sql.append(" and debit>="+amountgreaterfilter+" ");
		}
		sql.append("order by apm_charges_invoice.id desc ");
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double totaldis=0;
			while(rs.next()){
				double balance = 0;
				double result = 0.0;
				double creditAmount = 0;
				
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setPayby(rs.getString(2));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				accounts.setClientid(rs.getInt(4));
				
				accounts.setDebitAmount(rs.getDouble(5));
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(rs.getDouble(5)));
				
				accounts.setDeliverstatus(rs.getString(6));
				
				double debit = rs.getDouble(5);
				double total = rs.getDouble(5);
				int disctype = rs.getInt(10);
				double discamt = rs.getDouble(11);
				double discount = rs.getDouble(7);
				double r1 = (total*discount)/100;
				double newdiscount =0.0;
				if(disctype==1){
					r1 = discamt;
					newdiscount = rs.getDouble(11);
				}else{
					newdiscount = rs.getDouble(7);
				}
				if(disctype==1) {
					accounts.setDiscamt(rs.getString(11));
					
				}else {
					double discount_amount_by_per=(total*discount)/100;
					accounts.setDiscamt(Double.toString(discount_amount_by_per));
				}
				accounts.setNewdiscount(newdiscount);
				accounts.setDiscAmmount(DateTimeUtils.changeFormat(r1));
				double dis=r1+discamt;
				totaldis=totaldis+dis;
				
				total = total-r1;
				result = result + total;
				
				creditAmount = chargesReportDAO.getCreditAmount(rs.getDouble(1));
				
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				accounts.setRefundAmountx(DateTimeUtils.changeFormat(refundAmt));
				creditAmount = creditAmount - refundAmt;
				
				accounts.setCreditAmount(creditAmount);
				accounts.setCreditCharge(Double.toString(creditAmount));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(creditAmount));
				
				
				balance = result - creditAmount;
				accounts.setBalance(balance);
				accounts.setBalancex(DateTimeUtils.changeFormat(balance));
				
				int noOfCharges = chargesReportDAO.getNoOfCharges(rs.getInt(1));
				accounts.setNumberOfChages(noOfCharges);
				accounts.setDiscount(rs.getDouble(7));
				String clientname = chargesReportDAO.getClientFullName(rs.getString(4));
				accounts.setClientName(clientname);
				String payee = rs.getString(2);
				String payeename = null;
				if(payee.equalsIgnoreCase("Third Party")){
					payeename = chargesReportDAO.getTpname(rs.getInt(8));
				}
				else{
					payeename = clientname;
				}
				accounts.setPayeeName(payeename);
				//showing seq no instead of invoice id 21/09/2018
				
			    String ipdopdseq=accountsDAO.getIpdOpdSeqNoWithType(rs.getInt(1));
			    accounts.setIpdopdseq(ipdopdseq);
			    
			    String finseq=accountsDAO.getFinancialSeqNoOfInvoice(""+rs.getInt(1));
				accounts.setBghseqId(finseq);
			  
				if(paymentStatus!=null){
					if(paymentStatus.equals("Paid")){
						if(balance==0){
							  //advance payment recipt list
						    int invoiceid = rs.getInt(1);
						    String clientId =rs.getString(4);
							ArrayList<Accounts>prepaymentList = getPrePaymentListForReceiptReport(invoiceid,clientId,howpaid);
							ArrayList<Accounts> refundList = accountsDAO.getRefundList(invoiceid);
							ArrayList<Accounts>transactionList = gettransactionListForReceiptReport(""+invoiceid,howpaid);
							accounts.setPrepaymentList(prepaymentList);
							accounts.setRefundList(refundList);
							accounts.setTransactionList(transactionList);
							list.add(accounts);
						}
					}else if(paymentStatus.equals("Not Paid")){
						if(balance!=0){
							  //advance payment recipt list
						    int invoiceid = rs.getInt(1);
						    String clientId =rs.getString(4);
							ArrayList<Accounts>prepaymentList = getPrePaymentListForReceiptReport(invoiceid,clientId,howpaid);
							ArrayList<Accounts> refundList = accountsDAO.getRefundList(invoiceid);
							ArrayList<Accounts>transactionList = gettransactionListForReceiptReport(""+invoiceid,howpaid);
							accounts.setPrepaymentList(prepaymentList);
							accounts.setRefundList(refundList);
							accounts.setTransactionList(transactionList);
							list.add(accounts);
						}
					}else{
						  //advance payment recipt list
					    int invoiceid = rs.getInt(1);
					    String clientId =rs.getString(4);
						ArrayList<Accounts>prepaymentList = getPrePaymentListForReceiptReport(invoiceid,clientId,howpaid);
						ArrayList<Accounts> refundList = accountsDAO.getRefundList(invoiceid);
						ArrayList<Accounts>transactionList = gettransactionListForReceiptReport(""+invoiceid,howpaid);
						accounts.setPrepaymentList(prepaymentList);
						accounts.setRefundList(refundList);
						accounts.setTransactionList(transactionList);
						list.add(accounts);
					}
				}else{
					  //advance payment recipt list
				    int invoiceid = rs.getInt(1);
				    String clientId =rs.getString(4);
					ArrayList<Accounts>prepaymentList = getPrePaymentListForReceiptReport(invoiceid,clientId,howpaid);
					ArrayList<Accounts> refundList = accountsDAO.getRefundList(invoiceid);
					ArrayList<Accounts>transactionList = gettransactionListForReceiptReport(""+invoiceid,howpaid);
					accounts.setPrepaymentList(prepaymentList);
					accounts.setRefundList(refundList);
					accounts.setTransactionList(transactionList);
					list.add(accounts);
				}
			   // list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}


	public ArrayList<SummaryReport> getRefferedByOPDList(String opdipd, String diaryUser, String refferdby,
			String fromDate, String toDate) {



		  PreparedStatement preparedStatement = null;
		  ArrayList<SummaryReport> list = new ArrayList<SummaryReport>();
		  StringBuffer sql = new StringBuffer();
//		  sql.append("SELECT concat(initial,' ',apm_user.firstname,' ',lastname),concat(title,' ',apm_patient.firstname,' ',surname),admissiondsate,ipd_addmission_form.clientid,ipd_addmission_form.refferedby,name,debit ");
//		  sql.append("from apm_patient ");
//		  sql.append("inner join ipd_addmission_form on  ipd_addmission_form.clientid= apm_patient.id ");
//		  sql.append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
//		  sql.append("inner join apm_condition on apm_condition.id = apm_user.discription ");
//		  sql.append("left join apm_charges_invoice on apm_charges_invoice.ipdid=ipd_addmission_form.id ");
//		  sql.append("where ipd_addmission_form.admissiondsate between '" + fromDate + "' and '" + toDate + "' ");
		  
		  sql.append("SELECT reference.name,concat(title,' ',apm_patient.firstname,' ',surname),apm_available_slot.commencing,diaryusername,apm_condition.name,charge ");
		  sql.append("from apm_patient ");
		  sql.append("left join apm_available_slot on apm_available_slot.clientid=apm_patient.id ");
		  sql.append("inner join reference on reference.id = apm_patient.refrence ");
		  sql.append("inner join apm_user on apm_user.id = apm_available_slot.diaryuserid ");
		  sql.append("inner join apm_condition on apm_condition.id = apm_user.discription ");
		  sql.append("where commencing between '"+fromDate+"' and '"+toDate+"' and diaryusername NOT like '%OT%' and opdpmnt>0 and chrgstatus>0 ");


		  if (!refferdby.equals("0")) {
		   sql.append(" and reference.name='" + refferdby + "' ");
		  }
		  sql.append(" and refrence !='0' and refrence!='' ");
		  try {
		   preparedStatement = connection.prepareStatement(sql.toString());
		   ResultSet rs = preparedStatement.executeQuery();
		   while (rs.next()) {
		    SummaryReport summaryReport = new SummaryReport();
		    summaryReport.setPractitionerName(rs.getString(4));

		    summaryReport.setClientname(rs.getString(2));
		    summaryReport.setAdmissiondate(DateTimeUtils.getCommencingDate1(rs.getString(3)));
		    summaryReport.setType("OPD");
		    summaryReport.setRefferedby(rs.getString(1));
		    summaryReport.setDepartment(rs.getString(5));
		    summaryReport.setPayment(rs.getString(6));
		    list.add(summaryReport);
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return list;
	}

	private ArrayList<Accounts> gettransactionListForReceiptReport(String invoiceid, String howpaid) {

		PreparedStatement preparedStatement = null;
		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "select id,chargeinvoiceid,date,paymode,payment,crdinvoiceid from apm_charges_payment where chargeinvoiceid = "+invoiceid+" and payment!='prepayment' ";
		if(!howpaid.equals("0")){
			sql = sql +" "+"and paymode='"+howpaid+"' ";
		}
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setInvoiceNo(rs.getString(2));
				accounts.setDate(rs.getString(3));
				
				String reqdate = "";
				if(rs.getString(3)!=null){
					String[] data = rs.getString(3).split(" ");
					reqdate = DateTimeUtils.getCommencingDate1(data[0]) +" "+data[1];
				}
				accounts.setFromDate(reqdate);
				
				accounts.setPaymentmode(rs.getString(4));
				accounts.setAmount(rs.getDouble(5));
				
				if(rs.getInt(6)>0){
					double creditAmount = chargesAccountProcessingDAO.getReportCreditAmount(rs.getInt(6));
					accounts.setCreditAmount(creditAmount);
				}else{
					accounts.setCreditAmount(0.0);
				}
				
				//Decimal Amount
				accounts.setAmountx(dateTimeUtils.changeFormat(rs.getDouble(5)));
				
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}

	private ArrayList<Accounts> getPrePaymentListForReceiptReport(int invoiceid, String clientId, String howpaid) {

		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		AccountsDAO accountsDAO =new JDBCAccountsDAO(connection);
		sql.append("SELECT apm_credit_account.id,payment,credit_note,apm_charges_payment.date,advance_ipdid,apm_credit_account.client_id  FROM apm_credit_account inner join apm_charges_payment on ");
		sql.append("apm_charges_payment.prepaymentid = apm_credit_account.id ");
		sql.append("where chargeinvoiceid = "+invoiceid+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				
				list = getAdvancePaymentListForReceiptRpt(accounts.getId(),""+rs.getInt(5),invoiceid,rs.getString(6),howpaid);
				
				Accounts acc=accountsDAO.getPaymentAgainstlist(""+invoiceid);
				if(acc!=null){
					if(Double.parseDouble(acc.getAmountx())>0){
						list.add(acc);
					}
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}

	private ArrayList<Accounts> getAdvancePaymentListForReceiptRpt(int id, String ipdid, int invoiceid,
			String clientId, String howpaid) {
		AccountsDAO accountsDAO =new JDBCAccountsDAO(connection);
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		String sql = "  ";
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT  id,charge,credit_note,date,refinvoiceid,payment_mode FROM apm_credit_account where advance_ipdid='"+ipdid+"' and  payment_mode!='prepayment' and id<="+id+" and client_id='"+clientId+"' ");
		if(!howpaid.equals("0")){
			buffer.append("and payment_mode='"+howpaid+"' ");
		}
		buffer.append("order by id desc ");
		try{
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setAmountx(DateTimeUtils.changeFormat(rs.getDouble(2)));
				accounts.setNotes(rs.getString(3));
				String date = rs.getString(4);
				String temp[] = date.split(" ");
				accounts.setDate(DateTimeUtils.getCommencingDate1(temp[0]));
				accounts.setPhysical_payment_id(accountsDAO.getPhysicalpaymentIdAdvRef(rs.getString(1)));
				accounts.setPaymentmode(rs.getString(6));
				double charge = rs.getDouble(2);
				accounts.setAdvref(0);
				if(charge>0){
					list.add(accounts);
				}
				
				int refinvoiceid = rs.getInt(5);
				if(refinvoiceid>0){
					
					Accounts accounts2 = accountsDAO.getRefundData(invoiceid);
					accounts2.setAdvref(1);
					if(accounts2.getAmount()>0){
						list.add(accounts2);
					}
					//break;
					//  16 april 2018 when above break then it not execute after that loop... if advance is there then it not show in invoice
					continue;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}

	public ArrayList<SummaryReport> getNosOfadmdischarge(String fromdate, String todate, String year) {
		ArrayList<SummaryReport> arrayList = new ArrayList<SummaryReport>();
		String admjan,admfeb,admmarch,admapr,admmay,admjune,admjuly,admaug,admsept,admoct,admnov,admdec;
		String discjan,discfeb,discmarch,discapr,discmay,discjune,discjuly,discaug,discsept,discoct,discnov,discdec;
		int totalcount=0;
		try {
			
				SummaryReport report = new SummaryReport();
				SummaryReport january =new SummaryReport();
				SummaryReport february =new SummaryReport();
				SummaryReport march =new SummaryReport();
				SummaryReport april =new SummaryReport();
				SummaryReport mayobj =new SummaryReport();
				SummaryReport june =new SummaryReport();
				SummaryReport july =new SummaryReport();
				SummaryReport august =new SummaryReport();
				SummaryReport september =new SummaryReport();
				SummaryReport october =new SummaryReport();
				SummaryReport november =new SummaryReport();
				SummaryReport december =new SummaryReport();
				
				String newfromdate="";
				String newtodate="";
				newfromdate = year+"-01-01";
				newtodate = year+"-01-31";
				january = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admjan=january.getAdmissioncount();
				discjan=january.getDischargecount();
				
				
				newfromdate = year+"-02-01";
				newtodate = year+"-02-31";
				february = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admfeb=february.getAdmissioncount();
				discfeb=february.getDischargecount();
				
				newfromdate = year+"-03-01";
				newtodate = year+"-03-31";
				march = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admmarch=march.getAdmissioncount();
				discmarch=march.getDischargecount();
				
				newfromdate = year+"-04-01";
				newtodate = year+"-04-31";
				april = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admapr=april.getAdmissioncount();
				discapr=april.getDischargecount();
				

				newfromdate = year+"-05-01";
				newtodate = year+"-05-31";
				mayobj = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admmay=mayobj.getAdmissioncount();
				discmay=mayobj.getDischargecount();
				
				
				newfromdate = year+"-06-01";
				newtodate = year+"-06-31";
				june = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admjune=june.getAdmissioncount();
				discjune=june.getDischargecount();
				
				newfromdate = year+"-07-01";
				newtodate = year+"-07-31";
				july = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admjuly=july.getAdmissioncount();
				discjuly=july.getDischargecount();

				newfromdate = year+"-08-01";
				newtodate = year+"-08-31";
				august = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admaug=august.getAdmissioncount();
				discaug=august.getDischargecount();
				
				newfromdate = year+"-09-01";
				newtodate = year+"-09-31";
				september = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admsept=september.getAdmissioncount();
				discsept=september.getDischargecount();
				
				newfromdate = year+"-10-01";
				newtodate = year+"-10-31";
				october = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admoct=october.getAdmissioncount();
				discoct=october.getDischargecount();
				
				newfromdate = year+"-11-01";
				newtodate = year+"-11-31";
				november = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admnov=november.getAdmissioncount();
				discnov=november.getDischargecount();
				
				newfromdate = year+"-12-01";
				newtodate = year+"-12-31";
				december = getNosofAdmissionAndDischarge(newfromdate,newtodate);
				admdec=december.getAdmissioncount();
				discdec=december.getDischargecount();
				report.setJan(admjan);
				report.setFeb(admfeb);
				report.setMar(admmarch);
				report.setApr(admapr);;
				report.setMay(admmay);
				report.setJun(admjune);
				report.setJul(admjuly);
				report.setAug(admaug);
				report.setSep(admsept);
				report.setOct(admoct);
				report.setNov(admnov);
				report.setDec(admdec);
				report.setJan1(Integer.parseInt(discjan));
				report.setFeb1(Integer.parseInt(discfeb));
				report.setMarch1(Integer.parseInt(discmarch));
				report.setApril1(Integer.parseInt(discapr));
				report.setMay1(Integer.parseInt(discmay));
				report.setJune1(Integer.parseInt(discjune));
				report.setJully1(Integer.parseInt(discjuly));
				report.setAug1(Integer.parseInt(discaug));
				report.setSept1(Integer.parseInt(discsept));
				report.setOct1(Integer.parseInt(discoct));
				report.setNov1(Integer.parseInt(discnov));
				report.setDec1(Integer.parseInt(discdec));
				arrayList.add(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private SummaryReport getNosofAdmissionAndDischarge(String newfromdate, String newtodate) {
		SummaryReport report=new SummaryReport();
		try {
			String admission = gettotaladmission( newfromdate,  newtodate);
			String discharge = gettotaldischarge( newfromdate,  newtodate);
			report.setAdmissioncount(admission);
			report.setDischargecount(discharge);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return report;
	}

	private String gettotaldischarge(String newfromdate, String newtodate) {
		PreparedStatement preparedStatement=null;
		String res="0";
	try {
		String sql="select count(*) from apm_treatment_episode where dischargedate between '"+newfromdate+"' and '"+newtodate+"' and dischargedate is not null and dischargedate!='' ";
		preparedStatement=connection.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
		while (rs.next()) {
			res=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return res;
		
	}

	private String gettotaladmission(String newfromdate, String newtodate) {
		PreparedStatement preparedStatement=null;
		String res="0";
	try {
		String sql="select count(*) from ipd_addmission_form where admissiondsate between '"+newfromdate+"' and '"+newtodate+"'";
		preparedStatement=connection.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
		while (rs.next()) {
			res=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return res;
	}

	public ArrayList<SummaryReport> getipdrevenueRevenue(String fromdate, String todate, String year) {
		ArrayList<SummaryReport> arrayList = new ArrayList<SummaryReport>();
		try {
			
			SummaryReport report=new SummaryReport();
			SummaryReportDAO  summaryReportDAO=new JDBCSummaryReportDAO(connection);
			MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
			String jancash,febcash,marcash,aprcash,maycash,juncash,julcash,augcash,sepcash,octcash,novcash,deccash;
			String jandcard,febdcard,mardcard,aprdcard,maydcard,jundcard,juldcard,augdcard,sepdcard,octdcard,novdcard,decdcard;
			String janneft,febneft,marneft,aprneft,mayneft,junneft,julneft,augneft,sepneft,octneft,novneft,decneft;
			String jancheque,febcheque,marcheque,aprcheque,maycheque,juncheque,julcheque,augcheque,sepcheque,octcheque,novcheque,deccheque;
			String janprepay,febprepay,marprepay,aprprepay,mayprepay,junprepay,julprepay,augprepay,sepprepay,octprepay,novprepay,decprepay;
			int jan1=0,feb1=0,march1=0,april1=0,may1=0,june1=0,jully1=0,aug1=0,sept1=0,oct1=0,nov1=0,dec1=0;
			String newfromdate="";
			String newtodate="";
			
			newfromdate = year+"-01-01";
			newtodate = year+"-01-31";
			jancash=getIPDcashrevenu(newfromdate,newtodate,"");
			jandcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			janneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			jancheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			janprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			jan1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			newfromdate = year+"-02-01";
			newtodate = year+"-02-31";
			febcash=getIPDcashrevenu(newfromdate,newtodate,"");
			febdcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			febneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			febcheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			febprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			feb1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			newfromdate = year+"-03-01";
			newtodate = year+"-03-31";
			marcash=getIPDcashrevenu(newfromdate,newtodate,"");
			mardcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			marneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			marcheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			marprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			march1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			newfromdate = year+"-04-01";
			newtodate = year+"-04-31";
			aprcash=getIPDcashrevenu(newfromdate,newtodate,"");
			aprdcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			aprneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			aprcheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			aprprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			april1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);

			newfromdate = year+"-05-01";
			newtodate = year+"-05-31";
			maycash=getIPDcashrevenu(newfromdate,newtodate,"");
			maydcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			mayneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			maycheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			mayprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			may1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			newfromdate = year+"-06-01";
			newtodate = year+"-06-31";
			juncash=getIPDcashrevenu(newfromdate,newtodate,"");
			jundcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			junneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			juncheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			junprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			june1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			newfromdate = year+"-07-01";
			newtodate = year+"-07-31";
			julcash=getIPDcashrevenu(newfromdate,newtodate,"");
			juldcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			julneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			julcheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			julprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			jully1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);

			newfromdate = year+"-08-01";
			newtodate = year+"-08-31";
			augcash=getIPDcashrevenu(newfromdate,newtodate,"");
			augdcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			augneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			augcheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			augprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			aug1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			newfromdate = year+"-09-01";
			newtodate = year+"-09-31";
			sepcash=getIPDcashrevenu(newfromdate,newtodate,"");
			sepdcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			sepneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			sepcheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			sepprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			sept1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			newfromdate = year+"-10-01";
			newtodate = year+"-10-31";
			octcash=getIPDcashrevenu(newfromdate,newtodate,"");
			octdcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			octneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			octcheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			octprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			oct1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			newfromdate = year+"-11-01";
			newtodate = year+"-11-31";
			novcash=getIPDcashrevenu(newfromdate,newtodate,"");
			novdcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			novneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			novcheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			novprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			nov1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			newfromdate = year+"-12-01";
			newtodate = year+"-12-31";
			deccash=getIPDcashrevenu(newfromdate,newtodate,"");
			decdcard=getIPDcashrevenu(newfromdate,newtodate,"D/Card");
			decneft=getIPDcashrevenu(newfromdate,newtodate,"NEFT");
			deccheque=getIPDcashrevenu(newfromdate,newtodate,"Cheque");
			decprepay=getIPDcashrevenu(newfromdate,newtodate,"prepayment");
			dec1 = misChartDAO.getIpdNewAdmission(newfromdate,newtodate);
			
			report.setJan1(jan1);
			report.setFeb1(feb1);
			report.setMarch1(march1);
			report.setApril1(april1);
			report.setMay1(may1);
			report.setJune1(june1);
			report.setJully1(jully1);
			report.setAug1(aug1);
			report.setSept1(sept1);
			report.setOct1(oct1);
			report.setNov1(nov1);
			report.setDec1(dec1);
			
			
			report.setJancash(jancash);
			report.setJanneft(janneft);
			report.setJancheque(jancheque);
			report.setJandcard(jandcard);
			report.setJanprepay(janprepay);
			
			report.setFebcash(febcash);
			report.setFebneft(febneft);
			report.setFebcheque(febcheque);
			report.setFebdcard(febdcard);
			report.setFebprepay(febprepay);
			
			report.setMarcash(marcash);
			report.setMarneft(marneft);
			report.setMarcheque(marcheque);
			report.setMardcard(mardcard);
			report.setMarprepay(marprepay);
			
			report.setAprcash(aprcash);
			report.setAprneft(aprneft);
			report.setAprcheque(aprcheque);
			report.setAprdcard(aprdcard);
			report.setAprprepay(aprprepay);
			
			report.setMaycash(maycash);
			report.setMayneft(mayneft);
			report.setMaycheque(maycheque);
			report.setMaydcard(maydcard);
			report.setMayprepay(mayprepay);
			
			report.setJuncash(juncash);
			report.setJunneft(junneft);
			report.setJuncheque(juncheque);
			report.setJundcard(jundcard);
			report.setJunprepay(junprepay);
			
			report.setJulcash(julcash);
			report.setJulneft(julneft);
			report.setJulcheque(julcheque);
			report.setJuldcard(juldcard);
			report.setJulprepay(julprepay);
			
			report.setAugcash(augcash);
			report.setAugneft(augneft);
			report.setAugcheque(augcheque);
			report.setAugdcard(augdcard);
			report.setAugprepay(augprepay);
			
			report.setSepcash(sepcash);
			report.setSepneft(sepneft);
			report.setSepcheque(sepcheque);
			report.setSepdcard(sepdcard);
			report.setSepprepay(sepprepay);
			
			report.setOctcash(octcash);
			report.setOctneft(octneft);
			report.setOctcheque(octcheque);
			report.setOctdcard(octdcard);
			report.setOctprepay(octprepay);
			
			report.setNovcash(novcash);
			report.setNovneft(novneft);
			report.setNovcheque(novcheque);
			report.setNovdcard(novdcard);
			report.setNovprepay(novprepay);
			
			report.setDeccash(deccash);
			report.setDecneft(decneft);
			report.setDeccheque(deccheque);
			report.setDecdcard(decdcard);
			report.setDecprepay(decprepay);
			
			arrayList.add(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private String getIPDcashrevenu(String fromdate, String todate, String paymode) {
		String debit ="0";
		try {
			StringBuffer buffer = new StringBuffer();
			//buffer.append("select sum(debit) from apm_charges_invoice ");
			//buffer.append("where date between '"+fromdate+"' and '"+todate+"' and itype='"+string+"' ");
			
			buffer.append("select sum(payment) from apm_charges_invoice ");
			buffer.append("inner join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");
			buffer.append("where itype=2  and apm_charges_invoice.date between '"+fromdate+"' and '"+todate+"' ");
			if(paymode.equals("D/Card")){
				buffer.append("and paymode ='"+paymode+"' ");
			}else if(paymode.equals("Cheque")){
				buffer.append("and paymode ='"+paymode+"' ");
			}else if(paymode.equals("NEFT")){
				buffer.append("and paymode ='"+paymode+"' ");
			}else if(paymode.equals("prepayment")){
				buffer.append("and paymode ='"+paymode+"' ");
			}
			else {
				buffer.append("and paymode ='Cash' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total=0;
			while (rs.next()) {
				total= total+rs.getDouble(1);
			}
			String total1=DateTimeUtils.changeFormat(total);
			debit =total1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return debit;
	}
	

	public ArrayList<SummaryReport> getwardwiserevenueReport(String fromdate, String todate) {
		ArrayList<SummaryReport> list= new ArrayList<SummaryReport>();
		try {
			StringBuffer buffer= new StringBuffer();
			/*
			 * buffer.
			 * append("    select sum(charge*quantity),debit,wardname,wardid from apm_invoice_assesments      "
			 * ); buffer.
			 * append(" inner join apm_charges_invoice on apm_charges_invoice.id=apm_invoice_assesments.invoiced "
			 * ); buffer.
			 * append("  inner join apm_ipd_ward on apm_ipd_ward.id=apm_invoice_assesments.wardid  "
			 * ); buffer.append("  where date between '"+DateTimeUtils.getCommencingDate1(
			 * fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)
			 * +"' and itype='2'  "); buffer.append("  group by wardid  ");
			 * buffer.append("    ");
			 */
			
			buffer.append("select sum(charge*quantity),debit,wardname,wardid from apm_invoice_assesments      ");
			buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id=apm_invoice_assesments.invoiced ");
			buffer.append("inner join apm_ipd_ward on apm_ipd_ward.id=apm_invoice_assesments.wardid  ");
			buffer.append("where date between '"+(fromdate)+"' and '"+(todate)+"' and itype='2'  ");
			buffer.append("group by wardid ");
			buffer.append("    ");
			
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				SummaryReport summaryReport= new SummaryReport();
				summaryReport.setIpdamount(rs.getString(1));
				summaryReport.setWardname(rs.getString(3));
				summaryReport.setWardid(""+rs.getInt(4));
				summaryReport.setCount(""+getIpdCountBycharge(fromdate, todate, summaryReport.getWardid()));
				list.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private int getIpdCountBycharge(String fromdate, String todate, String wardid){
		int res=0;
		try {
			StringBuffer buffer= new StringBuffer();
			/*
			 * buffer.
			 * append(" select distinct(apm_invoice_assesments.ipdid) from apm_invoice_assesments  "
			 * ); buffer.
			 * append("   inner join apm_charges_invoice on apm_charges_invoice.id=apm_invoice_assesments.invoiced  "
			 * ); buffer.append("   where date between '"+DateTimeUtils.getCommencingDate1(
			 * fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)
			 * +"' and itype='2' and wardid='"+wardid+"'  ");
			 * buffer.append("   group by apm_invoice_assesments.ipdid ");
			 * buffer.append("   ");
			 */
			buffer.append(" select distinct(apm_invoice_assesments.ipdid) from apm_invoice_assesments  ");
			buffer.append("   inner join apm_charges_invoice on apm_charges_invoice.id=apm_invoice_assesments.invoiced  ");
			buffer.append("   where date between '"+(fromdate)+"' and '"+(todate)+"' and itype='2' and wardid='"+wardid+"'  ");
			buffer.append("   group by apm_invoice_assesments.ipdid ");
			buffer.append("   ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			
			if(rs.last()){
				res=rs.getRow();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<SummaryReport> getdeptwiseOtReport(String fromdate, String todate) {
		ArrayList<SummaryReport> list=new ArrayList<SummaryReport>();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("  SELECT apm_condition.id,apm_condition.name,surgeon from  apm_available_slot   ");
			buffer.append("   inner join apm_user on apm_user.id = apm_available_slot.surgeon   ");
			buffer.append("   inner join apm_condition on apm_condition.id = apm_user.discription  ");
			buffer.append("    where apm_available_slot.commencing between '"+DateTimeUtils.getCommencingDate1(fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)+"'   and apm_available_slot.surgeon!=0 group by apm_condition.name  ");
			
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				SummaryReport summaryReport= new SummaryReport();
				summaryReport.setCondition(rs.getString(1));
				summaryReport.setDepartment(rs.getString(2));
				summaryReport.setPractitionerId(rs.getString(3));
				summaryReport.setCount(""+getpatientCountOfOtDeptWise(fromdate, todate, summaryReport.getCondition()));
				summaryReport.setTotal(getchargeRevenueOfOt(fromdate, todate, summaryReport.getCondition()));
				list.add(summaryReport);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private int getpatientCountOfOtDeptWise(String fromdate, String todate, String deptid){
		int res=0;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("  SELECT count(*) from  apm_available_slot   ");
			buffer.append("   inner join apm_user on apm_user.id = apm_available_slot.surgeon   ");
			buffer.append("   inner join apm_condition on apm_condition.id = apm_user.discription  ");
			buffer.append("    where apm_available_slot.commencing between '"+DateTimeUtils.getCommencingDate1(fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)+"'   and apm_available_slot.surgeon!=0  and  apm_condition.id='"+deptid+"'  ");
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private int getchargeRevenueOfOt(String fromdate, String todate, String deptid){
		int res=0;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("  SELECT sum(apm_invoice_assesments.charge*quantity) from  apm_available_slot   ");
			buffer.append("  inner join apm_user on apm_user.id = apm_available_slot.surgeon  inner join apm_condition on apm_condition.id = apm_user.discription    ");
			buffer.append("   inner join apm_invoice_assesments on apm_invoice_assesments.appointmentid=apm_available_slot.id   ");
			buffer.append(" where apm_available_slot.commencing between '"+DateTimeUtils.getCommencingDate1(fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)+"'   and  apm_condition.id='"+deptid+"'    ");
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return res;
	}

	public Accounts getInvoiceBalDebitDetails(String fromdate, String todate, String userid) {
		Accounts accounts = new Accounts();
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		StatementDAO statementDAO = new JDBCStatementDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		sql.append("select apm_charges_invoice.id,payby,apm_charges_invoice.date,apm_charges_invoice.clientid,debit,deliverstatus,discount,apm_charges_invoice.tpid, ");
		sql.append("apm_user.firstname,apm_charges_invoice.disctype,discamt,apm_charges_invoice.itype,apm_user.userid ");
		sql.append("from apm_charges_invoice ");
		sql.append("left join apm_charges_payment on apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id ");
		sql.append("inner join apm_user on apm_user.id = apm_charges_invoice.updatedby ");
		sql.append("where apm_charges_invoice.date between '"+fromdate+"' AND '"+todate+"' and apm_user.userid='"+userid+"' ");
		sql.append("group by apm_charges_invoice.id ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double totalbalance = 0;
			double totaldebit = 0;
			while(rs.next()){
				double balance = 0;
				double result = 0.0;
				double creditAmount = 0;
				double debit = rs.getDouble(5);
				totaldebit = totaldebit + debit;
				double total = rs.getDouble(5);
				int disctype = rs.getInt(10);
				double discamt = rs.getDouble(11);
				double discount = rs.getDouble(7);
				double r1 = (total*discount)/100;
				if(disctype==1){
					r1 = discamt;
				}
				total = total-r1;
				result = result + total;
				creditAmount = chargesReportDAO.getCreditAmount(rs.getDouble(1));
				
				//if refund against invoice
				double refundAmt = statementDAO.getRefundAmtAgainsInvoice(rs.getInt(1));
				creditAmount = creditAmount - refundAmt;
				balance = result - creditAmount;	
				totalbalance = totalbalance +balance;
			}
			accounts.setDebitAmount(totaldebit);
			accounts.setBalanceTotal(totalbalance);
		}catch(Exception e){
			e.printStackTrace();
		}
		return accounts;
	
	}

	public ArrayList<Accounts> pcsGSTReport(String fromdate, String todate, String clientId) {
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		try {
			String sql="select @gross :=(charge/(1+((tax1+tax2+tax3)/100)))  ,charge,(tax1+tax2+tax3),((@gross*(tax1/100))) as t1,"
					+ "((@gross*(tax2/100))) as t2,(@gross*(tax3/100)) as t3 ,invoiced,debit,date,time,apm_charges_invoice.clientid from apm_invoice_assesments "
					+"  inner join apm_charges_invoice on apm_invoice_assesments.invoiced= apm_charges_invoice.id "
					+ "  where invoiced!='0' and apm_charges_invoice.date between '"+DateTimeUtils.getCommencingDate1(fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)+"' and apm_charges_invoice.isdeleted=0 ";
			if(!clientId.equals("")){
				sql=sql+" and  apm_charges_invoice.clientid='"+clientId+"' ";
			}
			sql=sql+"  group by invoiced ";
			
			PreparedStatement ps= connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			
			double t1=0,t2=0,t3=0;
			
			while (rs.next()) {
				
				Accounts accounts= new Accounts();
				
				accounts.setDebitAmount(rs.getDouble(8));
				accounts.setInvoiceid(rs.getInt(7));
				accounts.setPayAmount(getPaidAmt(""+accounts.getInvoiceid()));
				accounts.setDate(DateTimeUtils.getCommencingDate1(rs.getString(9))+" "+rs.getString(10));
				accounts.setClientName(clientDAO.getClientFullName(rs.getString(11)));
				Accounts accounts2= getGSTDATA(fromdate, todate, ""+accounts.getInvoiceid());
				accounts.setTax1(accounts2.getTax1());
				accounts.setTax2(accounts2.getTax2());
				accounts.setTax3(accounts2.getTax3());
				
				accounts.setTaxname1(""+(DateTimeUtils.convertToDouble(accounts.getTax1())+t1));
				accounts.setTaxname2(""+(DateTimeUtils.convertToDouble(accounts.getTax2())+t2));
				accounts.setTaxname3(""+(DateTimeUtils.convertToDouble(accounts.getTax3())+t3));
				
				
				t1=DateTimeUtils.convertToDouble(accounts.getTax1());
				t2=DateTimeUtils.convertToDouble(accounts.getTax2()); 
				t3=DateTimeUtils.convertToDouble(accounts.getTax3());
				
				list.add(accounts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	private Accounts getGSTDATA(String fromdate, String todate, String invoiceId){
		Accounts accounts= new Accounts();
		String sql="select @gross :=(charge/(1+((tax1+tax2+tax3)/100)))  ,charge,(tax1+tax2+tax3),((@gross*(tax1/100))) as t1,"
				+ "((@gross*(tax2/100))) as t2,(@gross*(tax3/100)) as t3 ,invoiced,debit,date,time,apm_charges_invoice.clientid from apm_invoice_assesments "
				+"  inner join apm_charges_invoice on apm_invoice_assesments.invoiced= apm_charges_invoice.id "
				+ "  where invoiced!='0' and apm_charges_invoice.date between '"+DateTimeUtils.getCommencingDate1(fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)+"' ";
		if(!invoiceId.equals("")){
			sql=sql+" and  invoiced='"+invoiceId+"' ";
		}
		try {
			
			double t1=0,t2=0,t3=0;
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				accounts.setTax1(DateTimeUtils.changeFormat(t1+rs.getDouble(4)));
				accounts.setTax2(DateTimeUtils.changeFormat(t2+rs.getDouble(5)));
				accounts.setTax3(DateTimeUtils.changeFormat(t3+rs.getDouble(6)));
				
				t1=DateTimeUtils.convertToDouble(accounts.getTax1());
				t2=DateTimeUtils.convertToDouble(accounts.getTax2()); 
				t3=DateTimeUtils.convertToDouble(accounts.getTax3());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return accounts;
	}
	
	private double getPaidAmt(String invoiceid){
		double amt=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" select sum(payment) from apm_charges_payment where chargeinvoiceid='"+invoiceid+"' ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				amt=rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amt;
	}

	public ArrayList<Client> getBirthPlaceListData(Pagination pagination, Client client, String fromDate, String toDate, String order) {
		ArrayList<Client> clientList= new ArrayList<Client>();
		try {
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append("select apm_patient.id,concat(title,' ',firstname,' ',surname),whopay,mobno,email,gender,dob,");
			buffer2.append("concat(address,',',town,',',county,',',country),emergencyContNo,abrivationid,fathername,");
			buffer2.append("birthplace,third_party_id  from apm_patient ");
			if(order.equals("0")){
				//buffer2.append("where dob between '"+fromDate+"' and '"+toDate+"' ");
				buffer2.append("where STR_TO_DATE(dob, '%d/%m/%Y') between '"+fromDate+"' and '"+toDate+"' ");
			}else{
				toDate = toDate+" "+"59:59:59";
				buffer2.append("inner join ipd_addmission_form on ipd_addmission_form.clientid = apm_patient.id ");
				buffer2.append("where admissiondsate between '"+fromDate+"' and '"+toDate+"' ");
			}
			buffer2.append("and birthplace is not null and birthplace!='' ");
			if(!"".equals(client.getSearchText())){
				buffer2.append("and (concat(title,' ',firstname,' ',surname) like '%"+client.getSearchText()+"%' or abrivationid ='"+client.getSearchText()+"') ");
			}
			if(order.equals("1")){
				buffer2.append("group by ipd_addmission_form.clientid  ");
			}
			buffer2.append("order by apm_patient.id desc ");
			String sql = pagination.getSQLQuery(buffer2.toString());
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
		
			ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
			while (rs.next()) {
				Client client2= new Client();
				client2.setId(rs.getInt(1));
				client2.setFullname(rs.getString(2));
				client2.setWhopay(rs.getString(3));
				client2.setMobNo(rs.getString(4));
				client2.setEmail(rs.getString(5));
				client2.setGender(rs.getString(6));
				client2.setDob(rs.getString(7));
				client2.setAge1(DateTimeUtils.getAge1(rs.getString(7)));
				client2.setAddress(rs.getString(8));
				client2.setEmergencyContName(rs.getString(9));
				client2.setAbrivationid(rs.getString(10));
				client2.setFathername(rs.getString(11));
				client2.setBirthplace(rs.getString(12));
				String ipdid=getLastIpdId(""+client2.getId());
				if("".equals(ipdid)){
					ipdid=""+notAvailableSlotDAO.getLastAppointmentId(""+client2.getId());
				}
				client2.setIpdid(ipdid);
				ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(rs.getString(13));
				client2.setTpName(thirdParty.getCompanyName());
				clientList.add(client2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientList;
	}
	
	 String getLastIpdId(String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT ipdabrivationid FROM ipd_addmission_form where clientid = " + clientid + " order by id desc limit 0,1 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getBirthPlaceListDataCount(Client client, String fromDate, String toDate, String order) {
		int c=0;
		try {
			/*String buffer="select count(*) from apm_patient where birthplace is not null and birthplace!=''";
			if(!"".equals(client.getSearchText())){
				buffer=buffer+ "  and (concat(title,' ',firstname,' ',surname) like '%"+client.getSearchText()+"%' or abrivationid ='"+client.getSearchText()+"') ";
			}*/
			StringBuffer buffer2 = new StringBuffer();
			buffer2.append("select count(*) from apm_patient ");
			if(order.equals("0")){
				//buffer2.append("where dob between '"+fromDate+"' and '"+toDate+"' ");
				buffer2.append("where STR_TO_DATE(dob, '%d/%m/%Y') between '"+fromDate+"' and '"+toDate+"' ");
			}else{
				toDate = toDate+" "+"59:59:59";
				buffer2.append("inner join ipd_addmission_form on ipd_addmission_form.clientid = apm_patient.id ");
				buffer2.append("where admissiondsate between '"+fromDate+"' and '"+toDate+"' ");
			}
			buffer2.append("and birthplace is not null and birthplace!='' ");
			if(!"".equals(client.getSearchText())){
				buffer2.append("and (concat(title,' ',firstname,' ',surname) like '%"+client.getSearchText()+"%' or abrivationid ='"+client.getSearchText()+"') ");
			}
			if(order.equals("1")){
				buffer2.append("group by ipd_addmission_form.clientid  ");
			}
			
			
			PreparedStatement ps= connection.prepareStatement(buffer2.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				if(order.equals("0")){
					c=rs.getInt(1);
				}else{
					c++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public ArrayList<SummaryReport> getDepartmentWiseRevenueForLmh(String fromdate, String todate,String year,String isopdipd,String masterchargetype) {
		ArrayList<SummaryReport> arrayList = new ArrayList<SummaryReport>();
		
		int totalcount=0;
		try {
			StringBuffer buffer = new StringBuffer();
			StringBuffer buffer2=new StringBuffer();
			/*
			 * buffer.
			 * append("select sum(payment),masterchargetype from apm_charges_payment inner join apm_charges_payment on   "
			 * ); buffer.
			 * append("apm_charges_payment.chargeinvoiveid=apm_invoice_assesments.invoiced where  "
			 * ); buffer.append("where date between '"+fromdate+"' and '"+todate+"' ");
			 * buffer.append("group by masterchargetype  ");
			 */
			
			//20-06-2025 @rahul comment because we compare with dept wise payment rpt with dept wise summary rpt 
			//buffer.append("select invoiced,masterchargetype,quantity,charge,(charge*quantity) from apm_invoice_assesments where commencing between '"+fromdate+"' and '"+todate+"' and  masterchargetype='"+masterchargetype+"' and invoiced!=0 and invoiced IS NOT NULL group by apmtType");  //jst for checking its origanal code 
			
			buffer.append("SELECT apm_charges_invoice.id FROM apm_charges_invoice");
			buffer.append(" inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id where date between '"+fromdate+"' and '"+todate+"' and  masterchargetype='"+masterchargetype+"'");
			buffer.append(" group by invoiced order by apm_charges_invoice.id desc");
			
			/*
			 * buffer.append("select invoiced,masterchargetype from apm_charges_invoice ");
			 * buffer.
			 * append("inner join apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id  where masterchargetype='"
			 * +masterchargetype+"' and date between '"+fromdate+"' and '"
			 * +todate+"' and invoiced!=0 ");
			 */
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				SummaryReport report = new SummaryReport();
				report.setInvoiceid1(rs.getInt(1));
				buffer2=buffer2.append(rs.getString(1)+",");
				report.setInvoiceids(""+buffer2);
				/*
				 * report.setMasterchargetype(rs.getString(2)); double
				 * revenue=rs.getDouble(3)*(rs.getDouble(4));
				 * report.setTotalcharge(rs.getDouble(5));
				 */	/*
				 * String jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec; String
				 * newfromdate=""; String newtodate=""; newfromdate = year+"-01-01"; newtodate =
				 * year+"-01-31"; //jan =
				 * getDepartmentRevenueMonthly(newfromdate,newtodate,rs.getString(2),isopdipd);
				 * 
				 * jan = getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-02-01"; newtodate = year+"-02-31"; feb =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-03-01"; newtodate = year+"-03-31"; mar =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-04-01"; newtodate = year+"-04-31"; apr =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-05-01"; newtodate = year+"-05-31"; may =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * 
				 * newfromdate = year+"-06-01"; newtodate = year+"-06-31"; jun =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-07-01"; newtodate = year+"-07-31"; jul =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-08-01"; newtodate = year+"-08-31"; aug =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-09-01"; newtodate = year+"-09-31"; sep =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-10-01"; newtodate = year+"-10-31"; oct =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-11-01"; newtodate = year+"-11-31"; nov =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd);
				 * 
				 * newfromdate = year+"-12-01"; newtodate = year+"-12-31"; dec =
				 * getChargeNamewiseRevenueMonthly(newfromdate,newtodate,rs.getString(2),
				 * isopdipd); report.setDepartment(rs.getString(2)); report.setJan(jan);
				 * report.setFeb(feb); report.setMar(mar); report.setApr(apr);
				 * report.setMay(may); report.setJun(jun); report.setJul(jul);
				 * report.setAug(aug); report.setSep(sep); report.setOct(oct);
				 * report.setNov(nov); report.setDec(dec); jan1=jan1+Integer.parseInt(jan);
				 * feb1=feb1+Integer.parseInt(feb); march1= march1+Integer.parseInt(mar);
				 * april1= april1+Integer.parseInt(apr); may1= may1+Integer.parseInt(may);
				 * june1= june1+Integer.parseInt(jun); jully1= jully1+Integer.parseInt(jul);
				 * aug1= aug1+ Integer.parseInt(aug); sept1= sept1+Integer.parseInt(sep); oct1=
				 * oct1+Integer.parseInt(oct); nov1= nov1+Integer.parseInt(nov); dec1=
				 * dec1+Integer.parseInt(dec); report.setJan1(jan1); report.setFeb1(feb1);
				 * report.setMarch1(march1); report.setApril1(april1); report.setMay1(may1);
				 * report.setJune1(june1); report.setJully1(jully1); report.setAug1(aug1);
				 * report.setSept1(sept1); report.setOct1(oct1); report.setNov1(nov1);
				 * report.setDec1(dec1); int count1=getDeptwisePatientCountOPD(fromdate, todate,
				 * rs.getString(2)); int count2= getDeptwisePatientCount(fromdate, todate,
				 * rs.getString(2)); int count=0; if (isopdipd.equals("IPD")) { count=count2;
				 * }else if (isopdipd.equals("OPD")) { count= count1; }else{ count=
				 * count1+count2; }
				 * 
				 * totalcount=totalcount+count;
				 * report.setTotalpatient(String.valueOf(totalcount));
				 * report.setCount(String.valueOf(count));
				 */
				arrayList.add(report);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
		
	}

	private String getChargeNamewiseRevenueMonthly(String newfromdate, String newtodate, String masterchargetype,
			String isipdopd) {
String res = "0";
		
		
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(payment),masterchargetype from apm_charges_payment inner join apm_charges_payment on   ");
			buffer.append("apm_charges_payment.chargeinvoiveid=apm_invoice_assesments.invoiced where  ");
			buffer.append("where date between '"+newfromdate+"' and '"+newfromdate+"' ");
			buffer.append("and masterchargetype='"+masterchargetype+"' ");
			buffer.append("group by masterchargetype  ");
			/*
			 * if(!isipdopd.equals("")){ if(isipdopd.equals("IPD"))
			 * buffer.append("and itype=2 "); } if(!isipdopd.equals("")){
			 * if(isipdopd.equals("OPD")) buffer.append("and itype!=2 "); }
			 */
			/* buffer.append("group by apm_discipline.discipline  "); */
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs= preparedStatement.executeQuery();
			while (rs.next()) {
				if(rs.getString(1)==null){
					res="0";
				}else{
					res= ""+rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
		
	}

	@Override
	public ArrayList<SummaryReport> getpaymentlist(String invids) {
		StringBuffer buffer = new StringBuffer();
		ArrayList<SummaryReport> list=new ArrayList<SummaryReport>();
		try {
			//buffer.append("select sum(payment) from apm_charges_payment where chargeinvoiceid in ("+invids+")");   //this is origanal code
			//buffer.append("select count(*),charge from apm_invoice_assesments where invoiced in ("+invids+") group by apmtType");          //this code for charges wise
			
			 buffer.append("select payment,apm_charges_invoice.id from apm_charges_payment ");
			 buffer.append("inner join apm_charges_invoice on apm_charges_invoice.id=apm_charges_payment.chargeinvoiceid ");
			 buffer.append("inner join apm_invoice_assesments on apm_invoice_assesments.clientId=apm_charges_payment.clientid ");
			 buffer.append("where apm_charges_payment.chargeinvoiceid in("+invids+")");
			 buffer.append("and paymode!='prepayment' group by  apm_charges_payment.chargeinvoiceid order by apm_charges_payment.id desc");
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs= preparedStatement.executeQuery();
			while (rs.next()) {
				SummaryReport summaryReport=new SummaryReport();
				
				summaryReport.setTotalcharge(rs.getDouble(1));
				summaryReport.setInvoiceid1(rs.getInt(2));
				
				list.add(summaryReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public SummaryReport getnewPatientcount(String deptid, String fromDate, String toDate) {
		SummaryReport sr = new SummaryReport();
		PreparedStatement ps= null;
		try{
			
		   StringBuffer buffer=  new StringBuffer();
		   buffer.append("select count(*) from department_opd where newpatient=1 and commencing between  '"+fromDate+"' and  '"+toDate+"' and dept='"+deptid+"';");
		  String sql= buffer.toString();
		  ps= connection.prepareStatement(sql);
		  ResultSet rs= ps.executeQuery();
		while(rs.next()){
			sr.setCount(rs.getString(1));
		}
		if(sr.getCount()==null){
			sr.setCount("0");
		}	
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return sr;
	}

	@Override
	public SummaryReport getoldPatientcount(String deptid, String fromDate, String toDate) {
		SummaryReport sr = new SummaryReport();
		PreparedStatement ps= null;
		try{
			
		   StringBuffer buffer=  new StringBuffer();
		   buffer.append("select count(*) from department_opd where newpatient=2 and commencing between  '"+fromDate+"' and  '"+toDate+"' and dept='"+deptid+"';");
		  String sql= buffer.toString();
		  ps= connection.prepareStatement(sql);
		  ResultSet rs= ps.executeQuery();
		while(rs.next()){
			sr.setCount(rs.getString(1));
		}
		if(sr.getCount()==null){
			sr.setCount("0");
		}	
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return sr;
	}

	@Override
	public ArrayList<Accounts> getPaymentModelist(String fromdate, String todate, String howpaid, String itype,
			String paymentStatus, String amountgreaterfilter) {
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		PreparedStatement ps= null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("select paymode,payment from apm_charges_payment inner join apm_charges_invoice on\n"
					+ "apm_charges_invoice.id = apm_charges_payment.chargeinvoiceidwhere date  between '"+fromdate+"' AND '"+todate+" 23:59:59' ");
					
			if(!itype.equals("0")){
			    sql.append(" and  apm_charges_invoice.itype="+itype+" ");
			}
			if(!amountgreaterfilter.equals("0")){
				 sql.append(" and debit>="+amountgreaterfilter+" ");
			}
			
			ps= connection.prepareStatement(sql.toString());
			
			ResultSet rs=ps.executeQuery();

			while (rs.next()) {
				
				Accounts accounts= new Accounts();
				
				accounts.setPaymentmode(rs.getString(1));
				accounts.setAmount(rs.getDouble(2));
				
				
				list.add(accounts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public ArrayList<Accounts> getAdvancelist(String fromdate, String todate, String howpaid, String itype,
			String paymentStatus, String amountgreaterfilter) {
		PreparedStatement ps= null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		try {
			sql.append("select payment_mode,charge from apm_credit_account where date  between '"+fromdate+"' AND '"+todate+" 23:59:59' and payment_mode!='prepayment'");
					
			if(!itype.equals("0")){
			    sql.append(" and  apm_charges_invoice.itype="+itype+" ");
			}
			if(!amountgreaterfilter.equals("0")){
				 sql.append(" and debit>="+amountgreaterfilter+" ");
			}
			
			ps= connection.prepareStatement(sql.toString());
			
			ResultSet rs=ps.executeQuery();

			while (rs.next()) {
				
				Accounts accounts= new Accounts();
				
				accounts.setPaymentmode(rs.getString(1));
				accounts.setAmount(rs.getDouble(2));
				
				
				list.add(accounts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public double getRefundOnly(String fromDate, String toDate, int i, String howpaid, String itype,
			String paymentStatus, String amountgreaterfilter) {
		double result  = 0;
		PreparedStatement ps= null;
		toDate = toDate + " 23:59:59";
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(debit) from apm_credit_account where date between '"+fromDate+"' and '"+toDate+"' and advref=1 ");
			
		if(!itype.equals("0")){
		    sql.append(" and  apm_charges_invoice.itype="+itype+" ");
		}
		if(!amountgreaterfilter.equals("0")){
			 sql.append(" and debit>="+amountgreaterfilter+" ");
		}
		
		try{
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}

	@Override
	public ArrayList<Accounts> getDepartmentWiseDiscounchargetReport(String fromdate, String todate, String invTpe,
			String patientcategory, String dept,String searchtext,int id,double billdisc) {
		PreparedStatement ps= null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		try {
			sql.append("SELECT discountAmt,third_party_name_id,masterchargetype,commencing,originalAmt,discountAmt,charges,concat(title,' ',firstname,' ',middlename,' ',surname),apm_invoice_assesments.chargeId,code,discountShare,quantity FROM apm_invoice_assesments inner join apm_patient ");
		    sql.append("on apm_patient.id=apm_invoice_assesments.clientId inner join apm_appointment_type on apm_appointment_type.id=apm_invoice_assesments.chargeId where commencing between '"+fromdate+"' and '"+todate+"' and invoiced='"+id+"' ");
					
			if(!invTpe.equals("")){
			    sql.append(" and  apm_charges_invoice.itype="+invTpe+" ");
			}
			if(!patientcategory.equals("") && !patientcategory.equals("0")){
				 sql.append(" and third_party_name_id="+patientcategory+" ");
			}
			if(!dept.equals("") && !dept.equals("0")){
				 sql.append(" and masterchargetype='"+dept+"' ");
			}
			if(!searchtext.equals("") && !searchtext.equals("0")){
				 sql.append("and (concat(title,' ',firstname,' ',surname) like '%"+searchtext+"%' or code='"+searchtext+"' or name='"+searchtext+"') ");
			}
			
			ps= connection.prepareStatement(sql.toString());
			
			ResultSet rs=ps.executeQuery();
			 double discamt=0;
			 double amtreceved=0;
			while (rs.next()) {
				
				Accounts accounts= new Accounts();
				
				accounts.setDiscount(rs.getDouble(1));
				String thirdparty_name=getThirdPartyName(rs.getString(2));
				accounts.setThirdParty(thirdparty_name);
				accounts.setMasterchargetype(rs.getString(3));
				accounts.setCommencing(rs.getString(4));
				if(rs.getString(11).equals("0")) {
					accounts.setAmount(rs.getDouble(7));
				}else {
					accounts.setAmount(rs.getDouble(5));
				}
					
				//accounts.setAmount(rs.getDouble(5));
				//amtreceved=Double.parseDouble(rs.getString(7))*Double.parseDouble(rs.getString(12));
				/*
				 * amtreceved=amtreceved+(Double.parseDouble(rs.getString(7))*Double.parseDouble
				 * (rs.getString(12))); accounts.setDiscAmmount(Double.toString(amtreceved));
				 */
				if(rs.getString(11).equals("0")) {
					accounts.setChargedisc("0");
				}else {
				 discamt= accounts.getAmount()*Double.parseDouble(rs.getString(12))*Double.parseDouble(rs.getString(11))/100;	
				 accounts.setChargedisc(Double.toString(discamt));
				}
				
			//accounts.setDiscAmmount(rs.getString(7));
				accounts.setClientName(rs.getString(8));
				String chargename=getChargeName(rs.getString(9));
				accounts.setChargename(chargename);
				accounts.setCode(rs.getString(10));
				accounts.setDiscountx(rs.getString(11));
				list.add(accounts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
		
	}

	private String getThirdPartyName(String thirdpartyname_id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT apm_third_party_details.company_name FROM apm_third_party_details where id = " + thirdpartyname_id + "  ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
		
	}

	private String getChargeName(String chargeid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_appointment_type where id = " + chargeid + "  ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
		
	}

	@Override
	public ArrayList<Accounts> getDiscountlist(String fromdate, String todate, String howpaid, String itype,
			String paymentStatus, String amountgreaterfilter) {
		PreparedStatement ps= null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		try {
			sql.append("select apm_charges_invoice.discamt,discount,debit,disctype FROM apm_charges_payment inner join apm_charges_invoice on "
					+ "apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid where apm_charges_payment.date between '"+fromdate+"' AND '"+todate+" 23:59:59' ");
					
			if(!itype.equals("0")){
			    sql.append(" and  apm_charges_invoice.itype="+itype+" ");
			}
			if(!amountgreaterfilter.equals("0")){
				 sql.append(" and debit>="+amountgreaterfilter+" ");
			}
			
			sql.append("and paymode!='prepayment' ");
			
			ps= connection.prepareStatement(sql.toString());
			
			ResultSet rs=ps.executeQuery();

			while (rs.next()) {
				
				Accounts accounts= new Accounts();
				
				if(rs.getString(4).equals("1")) {
					accounts.setDiscamt(rs.getString(1));
					
				}else {
					double discount_amount_by_per=(rs.getDouble(3)*rs.getDouble(2))/100;
					accounts.setDiscamt(Double.toString(discount_amount_by_per));
				}
				
				
				list.add(accounts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public ArrayList<Accounts> getinvoicelist(String fromdate, String todate, String itype, String patientcategory,
			String dept, String searchtext) {
		
		PreparedStatement ps= null;
		StringBuffer sql = new StringBuffer();
		ArrayList<Accounts> list= new ArrayList<Accounts>();
		ArrayList<Accounts>discountlist = new ArrayList<Accounts>();
		try {
			//sql.append("select id,discount,debit,disctype,discamt,f_seq_no from apm_charges_invoice where apm_charges_invoice.date between '"+fromdate+"' AND '"+todate+"' ");
			sql.append("select apm_charges_invoice.id,discount,debit,disctype,discamt,f_seq_no from apm_charges_invoice inner join ");
			sql.append("apm_invoice_assesments on apm_invoice_assesments.invoiced=apm_charges_invoice.id inner join apm_patient on ");
			sql.append("apm_patient.id=apm_invoice_assesments.clientId inner join apm_appointment_type on apm_appointment_type.id=apm_invoice_assesments. ");
			sql.append("chargeId where apm_charges_invoice.date between '"+fromdate+"' AND '"+todate+"'	");	
			
			if(!itype.equals("0") && !itype.equals("")){
			    sql.append(" and  apm_charges_invoice.itype="+itype+" ");
			}
			if(!patientcategory.equals("") && !patientcategory.equals("0")){
				 sql.append(" and third_party_name_id="+patientcategory+" ");
			}
			if(!dept.equals("") && !dept.equals("0")){
				 sql.append(" and masterchargetype='"+dept+"' ");
			}
			if(!searchtext.equals("") && !searchtext.equals("0")){
				 sql.append("and (concat(title,' ',firstname,' ',surname) like '%"+searchtext+"%' or code='"+searchtext+"' or name='"+searchtext+"') ");
			}
			sql.append("group by invoiced ");
			
			ps= connection.prepareStatement(sql.toString());
			SummaryReportDAO summaryReportDAO = new JDBCSummaryReportDAO(connection);
			ResultSet rs=ps.executeQuery();
			double discount_amount_by_per=0;
			while (rs.next()) {
				
				Accounts accounts= new Accounts();
				
				accounts.setId(rs.getInt(1));
				accounts.setDiscount(rs.getDouble(2));
				accounts.setDebitAmount(rs.getDouble(3));
				accounts.setDisctype(rs.getString(4));
				
				
				if(accounts.getDisctype().equals("0")) {
					discount_amount_by_per=(rs.getDouble(3)*rs.getDouble(2))/100;
					accounts.setBilldiscamount(discount_amount_by_per);
					double amtreceived=rs.getDouble(3)-discount_amount_by_per;
					accounts.setDiscAmmount(Double.toString(amtreceived));
				}else {
					accounts.setBilldiscamount(rs.getDouble(5));
					double amtreceived=rs.getDouble(3)-rs.getDouble(5);
					accounts.setDiscAmmount(Double.toString(amtreceived));
				}
				accounts.setInvoiceNo(rs.getString(6));
				discountlist=summaryReportDAO.getDepartmentWiseDiscounchargetReport(fromdate, todate,
						itype,patientcategory,dept,searchtext,accounts.getId(),accounts.getDiscount());
				
				accounts.setChargedisc((""+discountlist.get(discountlist.size()-1).getChargedisc()));
				//accounts.setDiscAmmount(""+discountlist.get(discountlist.size()-1).getDiscAmmount());				
				accounts.setDiscountchargereportlist(discountlist);
				
				
				
				list.add(accounts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
		
	}

	private String getReferedDoctorData(String ref_dr2) {
		
		StringBuffer buffer = new StringBuffer();
		PreparedStatement preparedStatement = null;
		StringBuffer buffer1 = new StringBuffer();
		SummaryReport summaryReport = new SummaryReport();
	    //ref_dr2 = ref_dr2.replace("0,", "");
		//String result = ref_dr2.replaceFirst("0", "");
	    //ref_dr2=result.substring(0, result.length()-1);
	    
	    ref_dr2 = ref_dr2.replaceFirst("^0", "");
	    ref_dr2 = ref_dr2.replaceFirst("^,", "");
		String refdrnma="";
		try {
			
			buffer.append(
					"SELECT name FROM reference where id in("+ ref_dr2 + ")");

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				buffer1=buffer1.append(rs.getString(1)+",");
				summaryReport.setReferedby(""+buffer1);
			    refdrnma=summaryReport.getReferedby();
				refdrnma=refdrnma.substring(0, refdrnma.length()-1);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return refdrnma;
		
		
	}
	
}
 