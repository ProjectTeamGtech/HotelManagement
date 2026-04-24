package com.apm.TreatmentEpisode.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCTreatmentEpisode extends JDBCBaseDAO implements TreatmentEpisodeDAO{
	
	public JDBCTreatmentEpisode(Connection connection){
		
		this.connection = connection;
		
	}

	public int saveTreatmentEpisode(TreatmentEpisode treatmentEpisode,String lastmodified) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_treatment_episode (clientid,clientname,practitionerid,name,ref_date,ref_source,ref_name,ref_contact,ref_letter,payby,spendlimit,sessions,discahrgeletter,startdate,authcode,invoicee,condition_id,urgent,lastmodified,ipdopd,refenddate,empname,approvedamt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		int t = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, treatmentEpisode.getClientId());
			preparedStatement.setString(2, treatmentEpisode.getClientName());
			preparedStatement.setString(3, treatmentEpisode.getDiaryUser());
			preparedStatement.setString(4, treatmentEpisode.getTreatmentEpisodeName());
			preparedStatement.setString(5, DateTimeUtils.getCommencingDate(treatmentEpisode.getReferalDate()));
			preparedStatement.setString(6, treatmentEpisode.getReferralSource());
			preparedStatement.setString(7, treatmentEpisode.getReferralName());
			preparedStatement.setString(8, treatmentEpisode.getReferralContact());
			preparedStatement.setString(9, treatmentEpisode.getReferralLetter());
			preparedStatement.setString(10, treatmentEpisode.getPayby());
			preparedStatement.setString(11, treatmentEpisode.getSpendLimit().trim());
			preparedStatement.setString(12, treatmentEpisode.getConsultationLimit().trim());
			preparedStatement.setString(13, treatmentEpisode.getDischargeLetter());
			preparedStatement.setString(14, treatmentEpisode.getTreatmentStartDate());
			preparedStatement.setString(15, treatmentEpisode.getAuthorisationCode());
			preparedStatement.setString(16, treatmentEpisode.getInvoicee());
			preparedStatement.setString(17, treatmentEpisode.getCondition());
			preparedStatement.setBoolean(18, treatmentEpisode.isUrgent());
			preparedStatement.setString(19, lastmodified);
			preparedStatement.setString(20, treatmentEpisode.getIpdopd());
			preparedStatement.setString(21, DateTimeUtils.getCommencingDate(treatmentEpisode.getReferalendDate()));
			preparedStatement.setString(22, treatmentEpisode.getEmpname());
			preparedStatement.setString(23, treatmentEpisode.getApprovedLimit());
			//preparedStatement.setString(17, "1");
			
			
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

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String clientId,String payby) {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		String sql = "SELECT id,name,sessions,usedsession,treatmentstatus FROM apm_treatment_episode where clientid="+clientId+" and payby='"+payby+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				int tretmentstatus = rs.getInt(5);
				String Status = "";
				if(tretmentstatus==0){
					Status = "(Ongoing)";
				}else{
					Status = "(Discharged)";
				}
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(2) + " " + "<a href='#'>"+Status+"</a>");
				treatmentEpisode.setSessions(rs.getString(3));
				treatmentEpisode.setUsedSession(rs.getString(4));
				
				list.add(treatmentEpisode);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public TreatmentEpisode getTreatmentEpisodeDetails(String tratmentepisodeid,String selectedAppointmentid) {
		PreparedStatement preparedStatement = null;
		TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
		String sql = "SELECT id,authcode,sessions,payby FROM apm_treatment_episode where id = "+tratmentepisodeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setAuthorisationCode(rs.getString(2));
				treatmentEpisode.setConsultationLimit(rs.getString(3));
				treatmentEpisode.setPayby(rs.getString(4));
				String usedSession = getUsedSessions(selectedAppointmentid);
				treatmentEpisode.setUsedSession(usedSession);
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return treatmentEpisode;
	}

	private String getUsedSessions(String selectedAppointmentid) {
		String usedsession = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "select usedsession from apm_available_slot where id = "+selectedAppointmentid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				usedsession = rs.getString(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return usedsession;
	}

	public int updateConsultationLimit(String sessions,
			String treatmentepisodeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set sessions=? where id = "+treatmentepisodeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sessions);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public TreatmentEpisode getParticularTreatEpiDetails(String id) {
		PreparedStatement preparedStatement = null;
		TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
		String sql = "SELECT id,clientid,clientname,practitionerid,name,ref_date,ref_source,ref_name,ref_contact,ref_letter,payby,spendlimit,sessions,discahrgeletter,startdate,authcode,invoicee,condition_id,urgent,ipdopd,refenddate,approvedamt FROM apm_treatment_episode where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setClientId(rs.getInt(2));
				treatmentEpisode.setClientName(rs.getString(3));
				treatmentEpisode.setDiaryUser(rs.getString(4));
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(5));
				treatmentEpisode.setReferalDate(rs.getString(6));
				treatmentEpisode.setReferralSource(rs.getString(7));
				treatmentEpisode.setReferralName(rs.getString(8));
				treatmentEpisode.setReferralContact(rs.getString(9));
				treatmentEpisode.setReferralLetter(rs.getString(10));
				treatmentEpisode.setPayby(rs.getString(11));
				treatmentEpisode.setSpendLimit(rs.getString(12));
				treatmentEpisode.setConsultationLimit(rs.getString(13));
				treatmentEpisode.setDischargeLetter(rs.getString(14));
				treatmentEpisode.setTreatmentStartDate(rs.getString(15));
				treatmentEpisode.setAuthorisationCode(rs.getString(16));
				treatmentEpisode.setInvoicee(rs.getString(17));
				//String conditionName = getConditionName(rs.getString(18));
				treatmentEpisode.setTreatmentType(rs.getString(18));
				treatmentEpisode.setUrgent(rs.getBoolean(19));
				treatmentEpisode.setIpdopd(rs.getString(20));
				treatmentEpisode.setReferalendDate(rs.getString(21));
				treatmentEpisode.setApprovedLimit(rs.getString(22));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return treatmentEpisode;
	}


	public ArrayList<TreatmentEpisode> getSourceOfReferralList() {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		String sql = "SELECT id,name FROM reference";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setReferralSource(rs.getString(2));
				list.add(treatmentEpisode);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateTreatmentEpisode(TreatmentEpisode treatmentEpisode, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode  set practitionerid = ?,name = ?,ref_date = ?,ref_source = ?,ref_name = ?,ref_contact = ?,ref_letter = ?,payby = ?,spendlimit = ?,sessions = ?,discahrgeletter = ?,startdate = ?,authcode = ?,invoicee = ?,condition_id = ? where id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, treatmentEpisode.getDiaryUser());
			preparedStatement.setString(2, treatmentEpisode.getTreatmentEpisodeName());
			preparedStatement.setString(3, treatmentEpisode.getReferalDate());
			preparedStatement.setString(4, treatmentEpisode.getReferralSource());
			preparedStatement.setString(5, treatmentEpisode.getReferralName());
			preparedStatement.setString(6, treatmentEpisode.getReferralContact());
			preparedStatement.setString(7, treatmentEpisode.getReferralLetter());
			preparedStatement.setString(8, treatmentEpisode.getPayby());
			preparedStatement.setString(9, treatmentEpisode.getSpendLimit());
			preparedStatement.setString(10, treatmentEpisode.getConsultationLimit());
			preparedStatement.setString(11, treatmentEpisode.getDischargeLetter());
			preparedStatement.setString(12, treatmentEpisode.getTreatmentStartDate());
			preparedStatement.setString(13, treatmentEpisode.getAuthorisationCode());
			preparedStatement.setString(14, treatmentEpisode.getInvoicee());
			preparedStatement.setString(15, treatmentEpisode.getTreatmentType());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteTreatmentEpisode(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_treatment_episode where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean isTreatmentNameExist(String treatmentName, String clientId) {
		PreparedStatement preparedStatement = null;
		
		boolean result = false;
		String sql = "select name,clientid from apm_treatment_episode where clientid = '"+clientId+"' and name = '"+treatmentName+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		return result;
	}

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		String sql = "SELECT id,name FROM apm_treatment_episode";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(2));
				list.add(treatmentEpisode);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
	}

	public TreatmentEpisode getLastTreatmentEpi(String clientId) {
		PreparedStatement preparedStatement = null;
		TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
		String sql = "SELECT max(id) FROM apm_treatment_episode where clientid="+clientId+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				treatmentEpisode.setId(rs.getInt(1));
				String name = getTreatmentEpisodeName(rs.getString(1));
				treatmentEpisode.setTreatmentEpisodeName(name);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return treatmentEpisode;
	}

	private String getTreatmentEpisodeName(String id) {
		String id1 = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "select name from apm_treatment_episode where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				 id1 = rs.getString(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return id1;
	}

	public int getTotalTreatmentEpisodeCount(String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "SELECT count(*) FROM apm_treatment_episode where clientid="+clientId+"";

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

	public ArrayList<TreatmentEpisode> getAllTreatmentEpisodeList(
			String clientId,Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		
		String sql = "";
		if(pagination.sortColumn==null){
			sql = "SELECT id,name,treatmentstatus,sessions FROM apm_treatment_episode where clientid="+clientId+" order by id desc ";
		}else{
			sql = "SELECT id,name,treatmentstatus,sessions FROM apm_treatment_episode where clientid="+clientId+"  ";
		}
		sql = pagination.getSQLQuery(sql);
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(2));
				treatmentEpisode.setTrtmentStatus(rs.getInt(3));
				treatmentEpisode.setSessions(rs.getString(4));
				list.add(treatmentEpisode);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public TreatmentEpisode getTreatmentEpisodeSessionDetails(
			String tratmentepisodeid) {
		TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
		PreparedStatement preparedStatement = null;
		String sql = "SELECT id,name,sessions,usedsession FROM apm_treatment_episode where id = "+tratmentepisodeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(2));
				treatmentEpisode.setSessions(rs.getString(3));
				treatmentEpisode.setUsedSession(rs.getString(4));
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return treatmentEpisode;
	}

	public int updateSessions(String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set usedsession=? where id = "+id+" ";
		
		try{
			int usedSession = getUseSession(id);
			usedSession = usedSession - 1 ;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, usedSession);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private int getUseSession(String id) {
		int useSession = 0;
		PreparedStatement preparedStatement = null;
		
		String sql = "select usedsession from apm_treatment_episode where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				useSession = rs.getInt(1);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return useSession;
	}

	
	public ArrayList<TreatmentEpisode> getSessionsId(String id,
			String currentdate) {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		String sql = "SELECT id,usedsession FROM apm_available_slot where treatmentEpisodeId = "+id+" and commencing >= '"+currentdate+"'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setUsedSession(rs.getString(2));
				list.add(treatmentEpisode);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateSessions(int id, int usedSession) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set usedsession = ? where id = "+id+" ";
		
		try{
			usedSession = usedSession - 1 ;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, usedSession);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public NotAvailableSlot getLastAppointmentData(String clientid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT aptmtype,treatmentEpisodeId,duration,whopay,dept ");
		sql.append("FROM apm_available_slot ");
		sql.append("where clientid = "+clientid+" and status=0 ");
		sql.append("ORDER BY id DESC LIMIT 1 ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				notAvailableSlot.setApmtType(rs.getString(1));
				notAvailableSlot.setTreatmentEpisodeId(rs.getString(2));
				notAvailableSlot.setDuration(rs.getString(3));
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode = getDetailsOfSessions(rs.getString(2));
				notAvailableSlot.setTreatmentSessions(treatmentEpisode.getSessions());
				notAvailableSlot.setUsedsession(treatmentEpisode.getUsedSession());
				notAvailableSlot.setWhopay(rs.getString(4));
				notAvailableSlot.setDept(rs.getString(5));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	private TreatmentEpisode getDetailsOfSessions(String id) {
		
		PreparedStatement preparedStatement = null;
		TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
		String sql = "select sessions,usedsession from apm_treatment_episode where id = "+id+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				treatmentEpisode.setSessions(rs.getString(1));
				treatmentEpisode.setUsedSession(rs.getString(2));
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return treatmentEpisode;
	}

	
	public String getTPNotes(String selectedAppointmentid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT apm_third_party_details.acct_setting_notes, apm_available_slot.whopay FROM apm_available_slot inner join apm_patient ");
		sql.append("on apm_patient.id = apm_available_slot.clientid inner join apm_third_party_details on ");
		sql.append("apm_third_party_details.id = apm_patient.third_party_name_id where apm_available_slot.id = "+selectedAppointmentid+" ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String getTreatmentEpisodeId(String apmtId) {
		PreparedStatement preparedStatement = null;
		String treatmnetId = "";
		String sql = "SELECT treatmentEpisodeId from apm_available_slot where id = "+apmtId+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				treatmnetId = rs.getString(1);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return treatmnetId;
	}

	public int editsave(TreatmentEpisode treatmentEpisode, String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode  set name = ?,ref_date = ?,ref_source = ?,ref_name = ?,payby = ?,spendlimit = ?,sessions = ?,authcode = ?,invoicee = ?,condition_id = ?,urgent=?,ipdopd=?,refenddate=?,approvedamt=? where id = "+selectedid+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, treatmentEpisode.getTreatmentEpisodeName());
			preparedStatement.setString(2, DateTimeUtils.getCommencingDate(treatmentEpisode.getReferalDate()));
			preparedStatement.setString(3, treatmentEpisode.getReferralSource());
			preparedStatement.setString(4, treatmentEpisode.getReferralName());
			preparedStatement.setString(5, treatmentEpisode.getPayby());
			preparedStatement.setString(6, treatmentEpisode.getSpendLimit());
			preparedStatement.setString(7, treatmentEpisode.getConsultationLimit());
			preparedStatement.setString(8, treatmentEpisode.getAuthorisationCode());
			preparedStatement.setString(9, treatmentEpisode.getInvoicee());
			preparedStatement.setString(10, treatmentEpisode.getCondition());
			preparedStatement.setBoolean(11, treatmentEpisode.isUrgent());
			preparedStatement.setString(12, treatmentEpisode.getIpdopd());
			preparedStatement.setString(13, DateTimeUtils.getCommencingDate(treatmentEpisode.getReferalendDate()));
			preparedStatement.setString(14, treatmentEpisode.getApprovedLimit());
			
			result = preparedStatement.executeUpdate();
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
	
		return result;
	}

	public ArrayList<NotAvailableSlot> getReportStatusList(
			String treatmentepisodeid) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot>list = new ArrayList<NotAvailableSlot>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT  apm_available_slot.id,commencing,name,sent,sentdate,sentnote FROM apm_available_slot ");
		sql.append("inner join apm_appointment_type on apm_appointment_type.id = apm_available_slot.aptmtype ");
		sql.append("where treatmentepisodeid = "+treatmentepisodeid+" and dna = 0 ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				notAvailableSlot.setApmttypetext(rs.getString(3));
				notAvailableSlot.setSent(rs.getBoolean(4));
				if(rs.getString(5)!=null){
					notAvailableSlot.setSentdate(rs.getString(5));
				}else{
					notAvailableSlot.setSentdate("");
				}
				
				if(rs.getString(6)!=null){
					notAvailableSlot.setSentNote(rs.getString(6));
				}else{
					notAvailableSlot.setSentNote("");
				}
				
				
				list.add(notAvailableSlot);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public TreatmentEpisode getTreatmentEpisodeData(String treatmentepisodeid) {
		PreparedStatement preparedStatement = null;
		TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
		String sql = "SELECT name,sessions,reportsent FROM apm_treatment_episode where id = "+treatmentepisodeid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(1));
				treatmentEpisode.setSessions(rs.getString(2));
				treatmentEpisode.setReportsent(rs.getBoolean(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return treatmentEpisode;
	}

	public int updateReportStatus(boolean usent, String sentDate,
			String sentNote, String appointmentid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot  set sent=?,sentdate=?,sentnote=? where id="+appointmentid+" ";
		
		try{
			
			if(sentDate!=null){
				if(!sentDate.equals("")){
					sentDate = DateTimeUtils.getCommencingDate(sentDate);
				}
				
			}
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, usent);
			preparedStatement.setString(2, sentDate);
			preparedStatement.setString(3, sentNote);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int updateSentReport(String treatmentepisodeid, boolean tpreportsent,String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set reportsent=?,sentdate=? where id="+treatmentepisodeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, tpreportsent);
			preparedStatement.setString(2, date);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		
		String sql = "SELECT id,name FROM apm_treatment_episode where clientid="+clientId+"  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(2));
				list.add(treatmentEpisode);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public NotAvailableSlot getAppointmentData(String appointmentid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT treatmentEpisodeid FROM apm_available_slot where id="+appointmentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				notAvailableSlot.setTreatmentEpisodeId(rs.getString(1));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	public NotAvailableSlot getWhoPay(String selectedAppointmentid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot noAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT whopay,clientid FROM apm_available_slot where id = "+selectedAppointmentid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				noAvailableSlot.setWhopay(rs.getString(1));
				noAvailableSlot.setClientId(rs.getString(2));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return noAvailableSlot;
	}

	public String getClientNotes(String clientId) {
		PreparedStatement preparedStatement = null;
		String result ="";
		String sql  = "SELECT clinicalnote FROM apm_patient where id = "+clientId+" ";
		
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

	public ArrayList<TreatmentEpisode> getIpdTreatmentEpisodeList(
			String clientid, String payby) {
		PreparedStatement preparedStatement = null;
		ArrayList<TreatmentEpisode>list = new ArrayList<TreatmentEpisode>();
		String sql = "SELECT id,name,sessions,usedsession,treatmentstatus FROM apm_treatment_episode where clientid="+clientid+" and payby='"+payby+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				treatmentEpisode.setId(rs.getInt(1));
				int tretmentstatus = rs.getInt(5);
				String Status = "";
				if(tretmentstatus==0){
					Status = "(Ongoing)";
				}else{
					Status = "(Discharged)";
				}
				treatmentEpisode.setTreatmentEpisodeName(rs.getString(2) + " " + ""+Status+"");
				treatmentEpisode.setSessions(rs.getString(3));
				treatmentEpisode.setUsedSession(rs.getString(4));
				
				list.add(treatmentEpisode);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public String getDischargeAdvoice(String treatmentepisodeid) {
		  
		  String s=null;
		  
		  try {
		    String sql="SELECT disadvnotes FROM apm_treatment_episode where id="+treatmentepisodeid+";";
		    PreparedStatement ps=connection.prepareStatement(sql);
		    ResultSet rs=ps.executeQuery();
		    
		    while(rs.next()){
		     s=rs.getString(1);
		    }
		    
		  } catch (Exception e) {

		      e.printStackTrace();
		  }
		  
		  return s;
		 }

	public ArrayList<TreatmentEpisode> getSelectedTreatmentEpisode(
			String treatmentepisodeid) {

		ArrayList<TreatmentEpisode> list=new ArrayList<TreatmentEpisode>();
		try {

		    String sql="SELECT name from apm_treatment_episode where id="+treatmentepisodeid+"";
		    PreparedStatement ps=connection.prepareStatement(sql);
		    ResultSet rs=ps.executeQuery();
		    
		    while(rs.next()){
		    	
		    	TreatmentEpisode treatmentEpisode=new TreatmentEpisode();
		    	treatmentEpisode.setTreatmentEpisodeName(rs.getString(1));
		    	treatmentEpisode.setId(Integer.parseInt(treatmentepisodeid));
		    	list.add(treatmentEpisode);
		    }
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}

	public String getRefEndDate(String treatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT refenddate FROM apm_treatment_episode where id = "+treatmentEpisodeId+" ";
		
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

	public String getRefFromDate(String treatmentId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT ref_date FROM apm_treatment_episode where id = "+treatmentId+" ";
		
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
	 public String getTreatmentTemplateData(String id) {

		  String data="";
		  try {
		   String sql="select text from ipd_template where id="+id+" ";
		   PreparedStatement ps=connection.prepareStatement(sql);
		   ResultSet rs =ps.executeQuery();
		   while(rs.next()){
		    
		     data =rs.getString(1);
		   }
		   
		  } catch (Exception e) {

		    e.printStackTrace();
		  } 
		   
		  return data;
		 }

	@Override
	public String getDepartmentBydocId(String docid) {
		PreparedStatement pst=null;
		String dept="";
		try {
			String sql="select discription from apm_user where id="+docid+"";
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
	}
