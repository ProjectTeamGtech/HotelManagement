package com.apm.Report.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.StyledEditorKit.BoldAction;

import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Report.eu.bi.ClientReportDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCClientReportDAO extends JDBCBaseDAO implements ClientReportDAO{
	public JDBCClientReportDAO(Connection connection){
		this.connection = connection;
	}

	public ArrayList<Client> getClientList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		String sql = "select id,title,firstname,surname,mobno,email,gender,dob,address,town,country,postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,emergencyContName,emergencyContNo,patientType,status,note,oldClientId,lastModified from apm_patient order by lastModified";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setTitle(rs.getString(2));
				client.setFirstName(rs.getString(3));
				client.setLastName(rs.getString(4));
				client.setMobNo(rs.getString(5));
				client.setEmail(rs.getString(6));
				client.setGender(rs.getString(7));
				client.setDob(rs.getString(8));
				client.setAddress(rs.getString(9));
				client.setTown(rs.getString(10));
				client.setCountry(rs.getString(11));
				client.setPostCode(rs.getString(12));
				client.setReference(rs.getString(13));
				client.setSourceOfIntro(rs.getString(14));
				client.setType(rs.getString(15));
				client.setTypeName(rs.getString(16));
				
				
				String thirdPartyType = getType(rs.getString(15));
				String thirdPartyTypeName = getTypeName(rs.getString(16));
				
				client.setThirdPartyType(thirdPartyType);
				client.setThirdPartyTypeName(thirdPartyTypeName);
				client.setOccupation(rs.getString(17));
				client.setExpiryDate(rs.getString(18));
				client.setWhopay(rs.getString(19));
				client.setPolicyAuthorzCode(rs.getString(20));
				client.setPolicyNo(rs.getString(21));
				client.setKnownAs(rs.getString(22));
				client.setCounty(rs.getString(23));
				client.setHomeNo(rs.getString(24));
				client.setWorkNo(rs.getString(25));
				client.setEmailCc(rs.getString(26));
				client.setPrefContactMode(rs.getString(27));
				client.setEmergencyContName(rs.getString(28));
				client.setEmergencyContNo(rs.getString(29));
				client.setPatientType(rs.getString(30));
				client.setStatus(rs.getBoolean(31));
				client.setNote(rs.getString(32));
				client.setOldclientId(rs.getString(33));
				client.setLastModified(rs.getString(34));
				int id = rs.getInt(1);
				int tpid = rs.getInt(16);
				 int noApmts = getNoOfApmts(id);
				 double totalTPCharges = getTotalTPCharges(id);
				 double totalSelfCharges = getTotalSelfCharges(id);
				 double totalTPPaidAmt =   getTotalTPAmtPaid(id,tpid);
				 double totalSelfPaidAmt = getTotalSelfAmtPaid(id);
				 double totalTPBalance = totalTPCharges - totalTPPaidAmt;
				 double totalSelfBalnace = totalSelfCharges - totalSelfPaidAmt;
				 int pracitonerId = getPractionerId(id);
				 String practitionerName = getPractitionerName(pracitonerId);
				 
				 client.setNoApmts(noApmts);
				 client.setTotalTPCharges(totalTPCharges);
				 client.setTotalSelfCharges(totalSelfCharges);
				 client.setTotalTPPaidAmt(totalTPPaidAmt);
				 client.setTotalSelfPaidAmt(totalSelfPaidAmt);
				 client.setTotalTPBalance(totalTPBalance);
				 client.setTotalSelfBalnace(totalSelfBalnace);
				 client.setDiaryUser(practitionerName);
				 
				 
				list.add(client);
}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private String getPractitionerName(int pracitonerId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select initial,firstname,lastname from apm_user where id = "+pracitonerId+"";
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

	private int getPractionerId(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select diaryuserid from apm_available_slot where clientId = "+id+"";
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

	private double getTotalSelfAmtPaid(int id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		//String sql = "select sum(payment) from apm_charges_payment inner join apm_charges_invoice where apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid and apm_charges_payment.clientid = "+id+" and apm_charges_invoice.payby = 'Client'";
		String sql = "select sum(payment) from apm_charges_payment inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid where apm_charges_payment.clientid = "+id+" and apm_charges_invoice.payby = 'Client'";

		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private double getTotalTPAmtPaid(int id, int tpid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		//String sql = "select sum(payment) from apm_charges_payment inner join apm_charges_invoice where apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid and apm_charges_payment.clientid = "+id+" and apm_charges_invoice.payby = 'Third Party'";
		String sql = "select sum(payment) from apm_charges_payment inner join apm_charges_invoice on apm_charges_invoice.id = apm_charges_payment.chargeinvoiceid where apm_charges_payment.clientid = "+id+" and apm_charges_invoice.payby = 'Third Party'";

		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private double getTotalSelfCharges(int id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(charge) FROM apm_invoice_assesments where clientId = "+id+" and paybuy = 0";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private double getTotalTPCharges(int id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(charge) FROM apm_invoice_assesments where clientId = "+id+" and paybuy = 1";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private int getNoOfApmts(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM apm_available_slot where clientId = "+id+"";
		
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

			private String getTypeName(String type) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "SELECT name FROM apm_third_party_details where  id = "+type+"";
				
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

			private String getType(String typeName) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "SELECT name FROM apm_third_party where  id = "+typeName+"";
				
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

			public ArrayList<Client> getCurrentTreatmentNoFutureApmtsList() {
				PreparedStatement preparedStatement = null;
				ArrayList<Client>list = new ArrayList<Client>();
				//String sql = "select id,title,firstname,surname,mobno,email,gender,dob,address,town,country,postcode,refrence from apm_patient";
				
				StringBuffer sql  = new StringBuffer();
				sql.append("SELECT apm_patient.id, concat(initial,'',apm_user.firstname,'',lastname),apm_clinic_location.city,concat(title,'',apm_patient.firstname,'',surname) ");
				sql.append("FROM apm_patient ");
				sql.append("inner join apm_available_slot on  apm_available_slot.clientid = apm_patient.id ");
				sql.append("inner join apm_clinic_location on apm_clinic_location.id = apm_available_slot.location ");
				sql.append("inner join apm_user on apm_user.id = apm_available_slot.diaryuserid ");
				sql.append("inner join apm_treatment_episode on apm_treatment_episode.id = apm_available_slot.treatmentepisodeid ");
				sql.append("where apm_available_slot.commencing between '2015-09-01' and '2015-10-01' ");
				
				
				try{
					
					preparedStatement = connection.prepareStatement(sql.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Client client = new Client();
						client.setId(rs.getInt(1));
						/*client.setTitle(rs.getString(2));
						client.setFirstName(rs.getString(3));
						client.setLastName(rs.getString(4));
						client.setMobNo(rs.getString(5));
						client.setEmail(rs.getString(6));
						client.setGender(rs.getString(7));
						client.setDob(rs.getString(8));
						client.setAddress(rs.getString(9));
						client.setTown(rs.getString(10));
						client.setCountry(rs.getString(11));
						client.setPostCode(rs.getString(12));
						client.setReference(rs.getString(13));*/
						
						int clientId = rs.getInt(1);
						String lastAmnptdate = getLastApmtDate(clientId);
						client.setLastApmtDate(lastAmnptdate);
						int treatmentEpisodeId = getMaxTreatmentId(rs.getInt(1));
						TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
						treatmentEpisode = getTreatmentEpisodeDetails(treatmentEpisodeId);
						int totalSession = Integer.parseInt(treatmentEpisode.getSessions());
						int usedSession = Integer.parseInt(treatmentEpisode.getUsedSession());
						boolean checkApmtAvailable = false;
						if(totalSession!=usedSession){
						checkApmtAvailable = checkApmtAvailableOrNot(clientId,treatmentEpisodeId);
							
						}
						if(checkApmtAvailable == false){
						list.add(client);
						}
					}
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			

			private String getLastApmtDate(int clientId) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "SELECT max(commencing) FROM apm_available_slot where clientId = "+clientId+"";
				
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

			private boolean checkApmtAvailableOrNot(int clientId,
					int treatmentEpisodeId) {
				PreparedStatement preparedStatement = null;
				boolean result = false;
				StringBuffer sql = new StringBuffer();
				String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				String temp1[] = temp[0].split("-");
				String date = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
				sql.append("SELECT * FROM apm_available_slot where clientId = "+clientId+" and treatmentEpisodeId = "+treatmentEpisodeId+" and commencing > '"+date+"' ");
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

			private TreatmentEpisode getTreatmentEpisodeDetails(
					int treatmentEpisodeId) {
				PreparedStatement preparedStatement = null;
				 TreatmentEpisode treatmentEpisode = new TreatmentEpisode();
				String sql = "SELECT id,name,sessions,usedsession FROM apm_treatment_episode where id = "+treatmentEpisodeId+"";
				
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

			private int getMaxTreatmentId(int id) {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "SELECT max(id) FROM apm_treatment_episode where clientid = "+id+"";
				
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

			public ArrayList<Client> getNoActivityRecordOfClientList() {
				PreparedStatement preparedStatement = null;
				ArrayList<Client> list = new ArrayList<Client>();
				String sql = "select apm_patient.id,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,apm_patient.email,"
							  + "apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.country,apm_patient.postcode from "
							  + "apm_patient where not exists (select apm_available_slot.id from apm_available_slot where apm_patient.id = "
							  + "apm_available_slot.clientId)";
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Client client = new Client();
						client.setId(rs.getInt(1));
						client.setFirstName(rs.getString(2));
						client.setLastName(rs.getString(3));
						client.setMobNo(rs.getString(4));
						client.setEmail(rs.getString(5));
						client.setDob(rs.getString(6));
						client.setAddress(rs.getString(7));
						client.setTown(rs.getString(8));
						client.setCountry(rs.getString(9));
						client.setPostCode(rs.getString(10));
						//client.setAppointmentId(rs.getString(11));
						
						list.add(client);
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return list;
			}

			public ArrayList<Client> getDNANoFutureAppReport(String practitionerId) {
				PreparedStatement preparedStatement = null;
				ArrayList<Client> list = new ArrayList<Client>();
				boolean checkAvailability = false;
				
				String sql = "";
				
				if(practitionerId!= null){		
					
					sql = "select apm_patient.id,apm_patient.title,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
							+ "apm_patient.email,apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.country,"
							+ "apm_patient.postcode,apm_patient.gender,apm_available_slot.id,apm_available_slot.diaryuserid "
							+ "from apm_patient inner join apm_available_slot on apm_patient.id = apm_available_slot.clientId "
							+ "where apm_available_slot.diaryuserid = '"+practitionerId+"' group by apm_patient.id";
				}
				else{
					
					sql = "select apm_patient.id,apm_patient.title,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
							+ "apm_patient.email,apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.country,"
							+ "apm_patient.postcode,apm_patient.gender,apm_available_slot.id,apm_available_slot.diaryuserid "
							+ "from apm_patient inner join apm_available_slot on apm_patient.id = apm_available_slot.clientId group by apm_patient.id";
				}
					try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Client client = new Client();
						client.setId(rs.getInt(1));
						client.setTitle(rs.getString(2));
						client.setFirstName(rs.getString(3));
						client.setLastName(rs.getString(4));
						client.setMobNo(rs.getString(5));
						client.setEmail(rs.getString(6));
						client.setDob(rs.getString(7));
						client.setAddress(rs.getString(8));
						client.setTown(rs.getString(9));
						client.setCountry(rs.getString(10));
						client.setPostCode(rs.getString(11));
						client.setGender(rs.getString(12));
						
						int DNAId = getClientWithDNA(rs.getInt(1));
						String date = getClientDate(DNAId);
						if(DNAId > 0){
							checkAvailability = isAvailableClient(DNAId,rs.getInt(1),date);
						
						
						if(checkAvailability == false){
							list.add(client);
						}
						}
						
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return list;
			}

			private String getClientDate(int dNAId) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "select commencing from apm_available_slot where id = "+dNAId+" ";
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

			private boolean isAvailableClient(int dNAId, int clientId,String date) {
				PreparedStatement preparedStatement = null;
				boolean result = false;
				String sql = "select * from apm_available_slot where clientId = "+clientId+" and commencing > '"+date+"'";
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						result = true;
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return result;
			}

			private int getClientWithDNA(int clientId) {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "select max(id) from apm_available_slot where dna = 1 and clientId = "+clientId+"";
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

			public ArrayList<Client> getNoActivityRecordList( String practitionerId) {
				PreparedStatement preparedStatement = null;
				ArrayList<Client> list = new ArrayList<Client>();
				
				String sql = "";
				
				if(practitionerId!= null){		
					
					sql = "select apm_patient.id,apm_patient.title,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
							+ "apm_patient.email,apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.country,"
							+ "apm_patient.postcode,apm_patient.gender,apm_available_slot.id,apm_available_slot.diaryuserid "
							+ "from apm_patient inner join apm_available_slot on apm_patient.id = apm_available_slot.clientId "
							+ "where  not exists (select id from apm_activity where apm_patient.id = apm_activity.clientId) and "
							+ "apm_available_slot.diaryuserid = '"+practitionerId+"' group by apm_patient.id";
				}else{
					sql = "select apm_patient.id,apm_patient.title,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
							+ "apm_patient.email,apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.country,"
							+ "apm_patient.postcode,apm_patient.gender from apm_patient where not exists (select id from apm_activity"
							+ " where apm_patient.id = apm_activity.clientId)";
				}
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Client client = new Client();
						client.setId(rs.getInt(1));
						client.setTitle(rs.getString(2));
						client.setFirstName(rs.getString(3));
						client.setLastName(rs.getString(4));
						client.setMobNo(rs.getString(5));
						client.setEmail(rs.getString(6));
						client.setDob(rs.getString(7));
						client.setAddress(rs.getString(8));
						client.setTown(rs.getString(9));
						client.setCountry(rs.getString(10));
						client.setPostCode(rs.getString(11));
						client.setGender(rs.getString(12));
						
						list.add(client);
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return list;
			}

			public int getTotalClientListCount() {
				PreparedStatement preparedStatement = null;
				int result = 0;
				
				String sql = "select count(*) from apm_patient";
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

			public ArrayList<Client> getClientReportList(String thirdpartypid,String location,String fromdate,String todate,String payby,String diaryUser,String orderby,String order) {
				PreparedStatement preparedStatement = null;
				ArrayList<Client>list = new ArrayList<Client>();
				//String sql = "select id,title,firstname,surname,mobno,email,gender,dob,address,town,country,postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,emergencyContName,emergencyContNo,patientType,status,note,oldClientId,lastModified from apm_patient order by lastModified";
				StringBuffer sql = new StringBuffer();
				//remove distinct
				//sql.append("select distinct(apm_available_slot.clientid),title,firstname,surname,mobno,email,gender,dob,address,town,country,postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,apm_patient.whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,emergencyContName,emergencyContNo,patientType,apm_patient.status,note,apm_patient.oldClientId,lastModified,location,abrivationid ");
				sql.append("select apm_available_slot.clientid,title,firstname,surname,mobno,email,gender,dob,address,town,country,postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,apm_patient.whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,emergencyContName,emergencyContNo,patientType,apm_patient.status,note,apm_patient.oldClientId,lastModified,location,abrivationid ");
                sql.append("from apm_patient inner join apm_available_slot on apm_available_slot.clientid = apm_patient.id ");
				
				if(fromdate.equals("") && todate.equals("")){
					if(thirdpartypid!=null){
						if(!thirdpartypid.equals("0") && !location.equals("0")){
							sql.append("where third_party_name_id="+thirdpartypid+" and location="+location+" ");
						}else if(!thirdpartypid.equals("0")){
							sql.append("where third_party_name_id="+thirdpartypid+" ");
						}else if(!location.equals("0")){
							sql.append("where location="+location+" ");
						}
					}
				
				}else{
					fromdate = DateTimeUtils.getCommencingDate(fromdate);
					todate = DateTimeUtils.getCommencingDate(todate);
					if(fromdate.equals(todate)){
						todate = todate + " "+ "23:59:59";
					}
					
					sql.append("where lastModified between '"+fromdate+"' and '"+todate+"' ");
					
					if(thirdpartypid!=null){
						
						if(!thirdpartypid.equals("0") && !location.equals("0") && !payby.equals("0") && !diaryUser.equals("0")){
							sql.append("and third_party_name_id="+thirdpartypid+" and location="+location+" and apm_available_slot.whopay='"+payby+"' and diaryuserid="+diaryUser+" ");
						}else if(!thirdpartypid.equals("0") && !payby.equals("0") && !diaryUser.equals("0")){
							sql.append("and third_party_name_id="+thirdpartypid+" and apm_available_slot.whopay='"+payby+"' and diaryuserid="+diaryUser+" ");
						}else if(!location.equals("0") && !payby.equals("0") && !diaryUser.equals("0")){
							sql.append("and location="+location+" and apm_available_slot.whopay='"+payby+"' and diaryuserid="+diaryUser+" ");
						}else if(!payby.equals("0") && !diaryUser.equals("0") ){
							sql.append("and apm_available_slot.whopay='"+payby+"' and diaryuserid="+diaryUser+" ");
						}else if(!diaryUser.equals("0")){
							sql.append("and diaryuserid="+diaryUser+" ");
						}
						
						if(!thirdpartypid.equals("0") && !location.equals("0") && !payby.equals("0")){
							sql.append("and third_party_name_id="+thirdpartypid+" and location="+location+" and apm_available_slot.whopay='"+payby+"' ");
						}else if(!thirdpartypid.equals("0") && !payby.equals("0")){
							sql.append("and third_party_name_id="+thirdpartypid+" and apm_available_slot.whopay='"+payby+"' ");
						}else if(!location.equals("0") && !payby.equals("0")){
							sql.append("and location="+location+" and apm_available_slot.whopay='"+payby+"' ");
						}else if(!payby.equals("0")){
							sql.append("and apm_available_slot.whopay='"+payby+"' ");
						}
						
						else if(!thirdpartypid.equals("0") && !location.equals("0")){
							sql.append("and third_party_name_id="+thirdpartypid+" and location="+location+" ");
						}else if(!thirdpartypid.equals("0")){
							sql.append("and third_party_name_id="+thirdpartypid+" ");
						}else if(!location.equals("0")){
							sql.append("and location="+location+" ");
						}
					}
				
				}
			
				
				
				sql.append("group by apm_available_slot.clientid order by "+orderby+" "+order+" ");
				//sql.append("group by apm_available_slot.clientid ");
				
				
				try{
					
					preparedStatement = connection.prepareStatement(sql.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Client client = new Client();
						client.setId(rs.getInt(1));
						client.setTitle(rs.getString(2));
						client.setFirstName(rs.getString(3));
						client.setLastName(rs.getString(4));
						client.setMobNo(rs.getString(5));
						client.setEmail(rs.getString(6));
						client.setGender(rs.getString(7));
						client.setDob(rs.getString(8));
						client.setAddress(rs.getString(9));
						/*if(rs.getString(35)!=null){
							if(rs.getString(35).equals("")){
								String city = getCityName(rs.getString(35));
								client.setTown(city);
							}
						}*/
						client.setTown(rs.getString(10));
						
						client.setCountry(rs.getString(11));
						client.setPostCode(rs.getString(12));
						client.setReference(rs.getString(13));
						client.setSourceOfIntro(rs.getString(14));
						client.setType(rs.getString(15));
						client.setTypeName(rs.getString(16));
						
						
						String thirdPartyType = getType(rs.getString(15));
						String thirdPartyTypeName = getTypeName(rs.getString(16));
						
						client.setThirdPartyType(thirdPartyType);
						client.setThirdPartyTypeName(thirdPartyTypeName);
						client.setOccupation(rs.getString(17));
						client.setExpiryDate(rs.getString(18));
						client.setWhopay(rs.getString(19));
						client.setPolicyAuthorzCode(rs.getString(20));
						client.setPolicyNo(rs.getString(21));
						client.setKnownAs(rs.getString(22));
						client.setCounty(rs.getString(23));
						client.setHomeNo(rs.getString(24));
						client.setWorkNo(rs.getString(25));
						client.setEmailCc(rs.getString(26));
						client.setPrefContactMode(rs.getString(27));
						client.setEmergencyContName(rs.getString(28));
						client.setEmergencyContNo(rs.getString(29));
						client.setPatientType(rs.getString(30));
						client.setStatus(rs.getBoolean(31));
						client.setNote(rs.getString(32));
						client.setOldclientId(rs.getString(33));
						client.setLastModified(rs.getString(34));
						int id = rs.getInt(1);
						int tpid = rs.getInt(16);
						 int noApmts = getNoOfApmts(id);
						 double totalTPCharges = getTotalTPCharges(id);
						 double totalSelfCharges = getTotalSelfCharges(id);
						 double totalTPPaidAmt =   getTotalTPAmtPaid(id,tpid);
						 double totalSelfPaidAmt = getTotalSelfAmtPaid(id);
						 double totalTPBalance = totalTPCharges - totalTPPaidAmt;
						 double totalSelfBalnace = totalSelfCharges - totalSelfPaidAmt;
						 int pracitonerId = getPractionerId(id);
						 String practitionerName = getPractitionerName(pracitonerId);
						 
						 client.setNoApmts(noApmts);
						 client.setTotalTPCharges(totalTPCharges);
						 client.setTotalSelfCharges(totalSelfCharges);
						 client.setTotalTPPaidAmt(totalTPPaidAmt);
						 client.setTotalSelfPaidAmt(totalSelfPaidAmt);
						 client.setTotalTPBalance(totalTPBalance);
						 client.setTotalSelfBalnace(totalSelfBalnace);
						 client.setDiaryUser(practitionerName);
						//  29 sep 2017 set abrivationid
						 client.setAbrivationid(rs.getString(36));
						 
						list.add(client);
		}
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			private String getCityName(String id) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "SELECT city FROM apm_clinic_location where id = "+id+" ";
				
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

			public int getTotalNoApptActivityCount() {
				PreparedStatement preparedStatement = null;
				int result = 0;
				
				String sql = "select count(apm_patient.id) from apm_patient where not exists (select apm_available_slot.id from "
						+ "apm_available_slot where apm_patient.id = apm_available_slot.clientId)";
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

			public ArrayList<Client> getNoActivityRecordOfClientList(
					Pagination pagination) {
				PreparedStatement preparedStatement = null;
				ArrayList<Client> list = new ArrayList<Client>();
				String sql = "select apm_patient.id,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,apm_patient.email,"
						  + "apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.country,apm_patient.postcode,apm_patient.abrivationid from "
						  + "apm_patient where not exists (select apm_available_slot.id from apm_available_slot where apm_patient.id = "
						  + "apm_available_slot.clientId)";
				sql = pagination.getSQLQuery(sql);
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Client client = new Client();
						client.setId(rs.getInt(1));
						client.setFirstName(rs.getString(2));
						client.setLastName(rs.getString(3));
						client.setMobNo(rs.getString(4));
						client.setEmail(rs.getString(5));
						client.setDob(rs.getString(6));
						client.setAddress(rs.getString(7));
						client.setTown(rs.getString(8));
						client.setCountry(rs.getString(9));
						client.setPostCode(rs.getString(10));
						//client.setAppointmentId(rs.getString(11));
						//  29 sep 2017 set abrivationid
						String abrivationid = "1";
						if(rs.getString(11)==null){
							abrivationid = "1";
						}else if(rs.getString(11).equals("")){
							abrivationid = "1";
						}else{
							abrivationid =rs.getString(11); 
						}
						client.setAbrivationid(abrivationid);
						list.add(client);
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return list;
			}

			public int getTotalNoActivityCount(String practitionerId) {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "";
				
				if(practitionerId!= null){	
					sql = "select count(apm_patient.id) from apm_patient inner join apm_available_slot on apm_patient.id = apm_available_slot.clientId "
							+ "where  not exists (select id from apm_activity where apm_patient.id = apm_activity.clientId) and "
							+ "apm_available_slot.diaryuserid = '"+practitionerId+"' group by apm_patient.id";
				}else{
					sql = "select count(apm_patient.id) from apm_patient inner join apm_available_slot on apm_patient.id = apm_available_slot.clientId "
							+ "where  not exists (select id from apm_activity where apm_patient.id = apm_activity.clientId) group by apm_patient.id";
				}
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

			public ArrayList<Client> getNoActivityRecordList(String practitionerId, Pagination pagination) {
				PreparedStatement preparedStatement = null;
				ArrayList<Client> list = new ArrayList<Client>();
				
				String sql = "";
				
				if(practitionerId!= null){		
					
					sql = "select apm_patient.id,apm_patient.title,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
							+ "apm_patient.email,apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.country,"
							+ "apm_patient.postcode,apm_patient.gender,apm_available_slot.id,apm_available_slot.diaryuserid,apm_patient.abrivationid "
							+ "from apm_patient inner join apm_available_slot on apm_patient.id = apm_available_slot.clientId "
							+ "where  not exists (select id from apm_activity where apm_patient.id = apm_activity.clientId) and "
							+ "apm_available_slot.diaryuserid = '"+practitionerId+"' group by apm_patient.id";
				}else{
					sql = "select apm_patient.id,apm_patient.title,apm_patient.firstname,apm_patient.surname,apm_patient.mobno,"
							+ "apm_patient.email,apm_patient.dob,apm_patient.address,apm_patient.town,apm_patient.country,"
							+ "apm_patient.postcode,apm_patient.gender,apm_patient.abrivationid from apm_patient where not exists (select id from apm_activity"
							+ " where apm_patient.id = apm_activity.clientId)";
				}
				sql = pagination.getSQLQuery(sql);
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Client client = new Client();
						client.setId(rs.getInt(1));
						client.setTitle(rs.getString(2));
						client.setFirstName(rs.getString(3));
						client.setLastName(rs.getString(4));
						client.setMobNo(rs.getString(5));
						client.setEmail(rs.getString(6));
						client.setDob(rs.getString(7));
						client.setAddress(rs.getString(8));
						client.setTown(rs.getString(9));
						client.setCountry(rs.getString(10));
						client.setPostCode(rs.getString(11));
						client.setGender(rs.getString(12));
						//  29 sep 2017 set abrivation
						String abrivationid="";
						if(rs.getString(13)==null){
							abrivationid="1";
						}else if(rs.getString(13).equals("")){
							abrivationid="1";
						}else{
							abrivationid=rs.getString(13);
						}
						client.setAbrivationid(abrivationid);
						list.add(client);
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return list;
			}

			public ArrayList<SummaryReport> getIpdConditionList(
					String condition1, String condition2, String condition3,
					String fromdate, String todate,Pagination pagination,String city) {
				todate = todate + " 23:59:59";
				ArrayList<SummaryReport> list=new ArrayList<SummaryReport>();
				
				StringBuffer buffer=new StringBuffer();
				ClientDAO clientDAO=new JDBCClientDAO(connection);
				DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection); 
				BedDao  bedDao=new JDBCBedDao(connection);
				try {
					
					if(condition1==null && condition2==null && condition3==null){
						buffer.append("SELECT ipdid,ipd_condition_report.conditionid from ipd_condition_report ");
						buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
					}
					else if(condition1!=null && condition2!=null && condition3!=null){
						buffer.append("select ipdid,ipd_condition_report.conditionid from ipd_condition_report ");
						buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						buffer.append("ipd_condition_report.conditionid in("+condition1+","+condition2+","+condition3+") ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by ipdid having count(distinct ipd_condition_report.conditionid)>=3 ");
					} else if(condition1!=null && condition2!=null){
						buffer.append("select ipdid,ipd_condition_report.conditionid from ipd_condition_report ");
						buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						buffer.append("ipd_condition_report.conditionid in("+condition1+","+condition2+") ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by ipdid having count(distinct ipd_condition_report.conditionid)=2 ");
					}else if(condition2!=null && condition3!=null){
						buffer.append("select ipdid,ipd_condition_report.conditionid from ipd_condition_report ");
						buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						buffer.append("ipd_condition_report.conditionid in("+condition2+","+condition3+") ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by ipdid having count(distinct ipd_condition_report.conditionid)=2 ");
					} else {
						
						 String conid="";
						 if(condition3!=null){
							  conid=condition3;
						 }
						 if(condition2!=null){
							  conid=condition2;
						 }
						 if(condition1!=null){
							  conid=condition1;
						 }
						 
						 buffer.append("select ipdid,ipd_condition_report.conditionid from ipd_condition_report ");
						 buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						 buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						 buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						 buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' and  ");
						 buffer.append("ipd_condition_report.conditionid="+conid+" ");
						 if(!city.equals("0")){
								buffer.append("and apm_city.city='"+city+"' ");
						}
						 
					}
					buffer.append("order by ipd_condition_report.id desc");
				
					String sql=pagination.getSQLQuery(buffer.toString()); 
					PreparedStatement ps=connection.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					
					while(rs.next()){
						
						SummaryReport summaryReport=new SummaryReport();
						summaryReport.setIpdid(rs.getString(1));
						Bed bed = bedDao.getEditIpdData(summaryReport.getIpdid());
						summaryReport.setClientId(bed.getClientid());
						summaryReport.setTreatmentId(rs.getString(2));
						Client client=clientDAO.getClientDetails(summaryReport.getClientId());
						summaryReport.setCondition(diagnosisDAO.getNameOfDiagnosisFromId(summaryReport.getTreatmentId()));
						summaryReport.setType("IPD");
						summaryReport.setClientname(client.getTitle()+" "+client.getFirstName()+" "+client.getLastName());
						summaryReport.setAddress(client.getAddress());
						summaryReport.setMobno(client.getMobNo());
						summaryReport.setAge(client.getAge());
						
						if(condition1!=null){
							condition1=diagnosisDAO.getNameOfDiagnosisFromId(condition1);
							summaryReport.setCondition(condition1);
						}  
						
						if(condition2!=null){
							condition2=diagnosisDAO.getNameOfDiagnosisFromId(condition2);
							summaryReport.setCondition2(condition2);
						} else {
							summaryReport.setCondition2("-");
						} 
						if(condition3!=null){
							condition3=diagnosisDAO.getNameOfDiagnosisFromId(condition3);
							summaryReport.setCondition3(condition3);
						} else {
							summaryReport.setCondition3("-");
						} 
						list.add(summaryReport);
					}
					
					
				} catch (Exception e) {

					e.printStackTrace();
				} 
				return list;
			}


			
			
			
			public ArrayList<SummaryReport> getOpdConditionList(String condition1, String condition2, String condition3,
					String fromdate, String todate,Pagination pagination, String city) {
				
				ArrayList<SummaryReport> list=new ArrayList<SummaryReport>();
				ClientDAO clientDAO=new JDBCClientDAO(connection);
				DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
				StringBuffer buffer=new StringBuffer();
				String conid="";
				try {
					if(condition1==null && condition2==null && condition3==null){
						
						buffer.append("select clientid,conditionid from apm_opd_condition  ");
						buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
					} else if(condition1!=null && condition2!=null && condition3!=null){
						
						buffer.append("select clientid,conditionid from apm_opd_condition ");
						buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						buffer.append("conditionid in("+condition1+","+condition2+","+condition3+") ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by apm_opd_condition.clientid having count(distinct conditionid)>=3 ");
					} else if(condition1!=null && condition2!=null){
						
						buffer.append("select clientid,conditionid from apm_opd_condition ");
						buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' and  ");
						buffer.append("conditionid in("+condition1+","+condition2+") ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by apm_opd_condition.clientid having count(distinct conditionid)=2 ");
						
					}else if(condition2!=null && condition3!=null){
						
						buffer.append("select clientid,conditionid from apm_opd_condition ");
						buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						buffer.append("conditionid in("+condition2+","+condition3+") ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by apm_opd_condition.clientid having count(distinct conditionid)=2 ");
						
					} else {
						
						 
						 if(condition3!=null) {
							 conid=condition3;
						 }
						 if(condition2!=null) {
							 conid=condition2;
						 }
						 if(condition1!=null) {
							 conid=condition1;
						 }
						 
						 buffer.append("SELECT clientid,conditionid from apm_opd_condition ");
						 buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						 buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						 buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						 buffer.append("conditionid="+conid+" ");
						 if(!city.equals("0")){
								buffer.append("and apm_city.city='"+city+"' ");
							}
					}
					buffer.append("order by apm_opd_condition.id desc");
					
				    PreparedStatement ps=connection.prepareStatement(buffer.toString());		
					ResultSet rs=ps.executeQuery();
					while(rs.next()){
						 SummaryReport summaryReport=new SummaryReport();
						 summaryReport.setClientId(rs.getString(1));
						 summaryReport.setTreatmentId(rs.getString(2));
						 Client client=clientDAO.getClientDetails(summaryReport.getClientId());
						 Diagnosis diagnosis=diagnosisDAO.getDiagnosisName(summaryReport.getTreatmentId());
						 summaryReport.setCondition(diagnosis.getName());
						 summaryReport.setType("OPD");
						 summaryReport.setClientname(client.getTitle()+" "+client.getFirstName()+" "+client.getLastName());
						 summaryReport.setAddress(client.getAddress());
						 summaryReport.setMobno(client.getMobNo());
						 summaryReport.setAge(client.getAge());

						 if(condition1!=null){
								condition1=diagnosisDAO.getNameOfDiagnosisFromId(condition1);
								summaryReport.setCondition(condition1);
						 }
						 if(condition2!=null){
								condition2=diagnosisDAO.getNameOfDiagnosisFromId(condition2);
								summaryReport.setCondition2(condition2);
						 } else {
								summaryReport.setCondition2("-");
						 } 
						 if(condition3!=null){
								condition3=diagnosisDAO.getNameOfDiagnosisFromId(condition3);
								summaryReport.setCondition3(condition3);
						 } else {
								summaryReport.setCondition3("-");
						 } 
						 list.add(summaryReport);
						 
					}
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				
				return list;
			}

			public int getIpdConditionCount(String condition1,
					String condition2, String condition3, String fromdate,
					String todate,String city) {
				todate = todate + " 23:59:59";
				StringBuffer buffer=new StringBuffer();
				
				int count=0;
				try {
					
					if(condition1==null && condition2==null && condition3==null){
						buffer.append("SELECT count(*) from ipd_condition_report  ");
						buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
					}
					else if(condition1!=null && condition2!=null && condition3!=null){
						buffer.append("select count(*) from ipd_condition_report  ");
						buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						buffer.append("ipd_condition_report.conditionid in("+condition1+","+condition2+","+condition3+")  ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by ipdid having count(distinct ipd_condition_report..conditionid)>=3 ");
					} else if(condition1!=null && condition2!=null){
						buffer.append("select count(*) from ipd_condition_report ");
						buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						buffer.append("ipd_condition_report.conditionid in("+condition1+","+condition2+")  ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by ipdid having count(distinct ipd_condition_report.conditionid)=2 ");
					}else if(condition2!=null && condition3!=null){
						buffer.append("select count(*) from ipd_condition_report ");
						buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						buffer.append("ipd_condition_report.conditionid in("+condition2+","+condition3+")  ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by ipdid having count(distinct ipd_condition_report.conditionid)=2 ");
					} else {
						
						 String conid="";
						 if(condition3!=null){
							  conid=condition3;
						 }
						 if(condition2!=null){
							  conid=condition2;
						 }
						 if(condition1!=null){
							  conid=condition1;
						 }
						 
						 buffer.append("select count(*) from ipd_condition_report ");
						 buffer.append("inner join ipd_addmission_form on ipd_condition_report.ipdid = ipd_addmission_form.id ");
						 buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
						 buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						 buffer.append("where ipd_condition_report.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						 if(!city.equals("0")){
								buffer.append("and apm_city.city='"+city+"' ");
						 }
						 buffer.append("ipd_condition_report.conditionid="+conid+" ");
						 
					}
					
					PreparedStatement ps=connection.prepareStatement(buffer.toString());
					ResultSet rs=ps.executeQuery();
					
					while(rs.next()){
						
						count=rs.getInt(1);
					}
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				
				return count;
	 }

			public int getOpdConditionCount(String condition1,
					String condition2, String condition3, String fromdate,
					String todate,String city) {
				int count=0;
				String conid="0";
				StringBuffer buffer=new StringBuffer();
				try {
					
					if(condition1==null && condition2==null && condition3==null){
						
						buffer.append("select count(*) from apm_opd_condition ");
						buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
					} else if(condition1!=null && condition2!=null && condition3!=null){
						
						buffer.append("select count(*) from apm_opd_condition ");
						buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						buffer.append("conditionid in("+condition1+","+condition2+","+condition3+") ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by apm_opd_condition.clientid having count(distinct conditionid)>=3 ");
					} else if(condition1!=null && condition2!=null){
						
						buffer.append("select count(*) from apm_opd_condition ");
						buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' and  ");
						buffer.append("conditionid in("+condition1+","+condition2+") ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by apm_opd_condition.clientid having count(distinct conditionid)=2 ");
						
					}else if(condition2!=null && condition3!=null){
						
						buffer.append("select count(*) from apm_opd_condition ");
						buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' and  ");
						buffer.append("conditionid in("+condition2+","+condition3+") ");
						if(!city.equals("0")){
							buffer.append("and apm_city.city='"+city+"' ");
						}
						buffer.append("group by apm_opd_condition.clientid having count(distinct conditionid)=2 ");
						
					} else {
						
						 
						 if(condition3!=null) {
							 conid=condition3;
						 }
						 if(condition2!=null) {
							 conid=condition2;
						 }
						 if(condition1!=null) {
							 conid=condition1;
						 }
						 
						 buffer.append("SELECT count(*) from apm_opd_condition  ");
						 buffer.append("inner join apm_patient on apm_patient.id = apm_opd_condition.clientid ");
						 buffer.append("inner join apm_city on apm_patient.town = apm_city.city ");
						 buffer.append("where apm_opd_condition.lastmodified between '"+fromdate+"' and '"+todate+"' and ");
						 buffer.append("conditionid="+conid+" ");
						 if(!city.equals("0")){
								buffer.append("and apm_city.city='"+city+"' ");
						}
					}
				    PreparedStatement ps=connection.prepareStatement(buffer.toString());		
					ResultSet rs=ps.executeQuery();
					while(rs.next()){
						 
						count=rs.getInt(1);
					}
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				
				return count;
			}

			public double getIpdInitialAssesmentPer() {

				double res=0;
				try {
					
					String sql="SELECT id,admissiondsate FROM ipd_addmission_form where bedid!=0;";
					PreparedStatement ps=connection.prepareStatement(sql);
					ResultSet rs =ps.executeQuery();
					int count=0;
					long tothours=0;
					while(rs.next()){
						count++;
						
						  int id = rs.getInt(1);
						  String date= rs.getString(2).split(" ")[0];
						  
						  String sql1="select lastmodified from apm_client_parent_nursing where ipdid="+id+"";
						  PreparedStatement ps1=connection.prepareStatement(sql1); 
						  String assentDate= "";
						  ResultSet rs1 =ps1.executeQuery();
						  while(rs1.next()){
							   
							  assentDate =rs1.getString(1).split(" ")[0];
							  break;
						  }
						  
						  if(assentDate!=null){

							   if(assentDate.equals("")){
								   
								   Calendar calendar=Calendar.getInstance();
								   SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
								   assentDate = dateFormat.format(calendar.getTime()); 
								   
							   }
						  } else {
							  
							  Calendar calendar=Calendar.getInstance();
							   SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
							   assentDate = dateFormat.format(calendar.getTime());
							  
						  }
						  long dif= DateTimeUtils.getDifferenceOfTwoDateDBFormatinHours(date, assentDate);
						  tothours = tothours +dif; 
						  
					}
					
					res = tothours / count;
					
					
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				
				return res;
			}

			public double getCasualityAssesmentPer(String fromdate,
					String todate) {

				double result =0;
				try {
					
					String sql="select  ";
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				
				return result;
			}

			public double getNursingCarePlanDocument(String fromdate,
					String todate) {
				double res= 0;
				int d=0;
				try {
					String sql="select count(*) from ipd_addmission_form where bedid==0 and admissiondsate between '"+fromdate+"' and '"+todate+"' ";
					PreparedStatement ps=connection.prepareStatement(sql);
					ResultSet rs =ps.executeQuery(); 
					
					while(rs.next()){
						
						d=rs.getInt(1);
					}
					if(d>0){
						res= 0;
					} else {
						res = d*d/100;
					}
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				
				
				return res;
			}

			public ArrayList<MisReport> getKPIAreaList() {
				//  12 oct 2017 for KPI dashboard select area list
				ArrayList<MisReport>  arrayList = new ArrayList<MisReport>();
				try {
					String sql ="select id,area from kpi_area";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						MisReport misReport = new MisReport();
						misReport.setId(rs.getInt(1));
						misReport.setName(rs.getString(2));
						arrayList.add(misReport);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<Report> getKPIIndicatorList(String areaid, String catagoryid, String subcatagorylist) {
				//  09 oct 2017 for KPI dashboard select indicator list
				ArrayList<Report>  arrayList = new ArrayList<Report>();
				try {
					StringBuilder builder = new StringBuilder();
					//builder.append("select id, areaid, indicator, no_of_input, formula, formula_desc,ischecked from his_indicator_master ");
					/*builder.append("select id, areaid, indicator, description, is_active, formula_decription,ismannual, function_name from kpi_indicator ");
					if(!areaid.equals("0")){
						builder.append("where areaid='"+areaid+"' ");
					}*/
					boolean flag=false;
					builder.append("select kpi_indicator.id, areaid, indicator, kpi_indicator.description, is_active, formula_decription,ismannual, function_name from kpi_indicator ");
					builder.append("inner join kpi_area on kpi_area.id = kpi_indicator.areaid ");
					builder.append("inner join nabh_subcatagory on kpi_area.subcatagoryid = nabh_subcatagory.id ");
					builder.append("inner join nabh_Catagory on nabh_Catagory.id = nabh_subcatagory.catagoryid ");
					if(!catagoryid.equals("0")){
						builder.append("where nabh_Catagory.id='"+catagoryid+"' ");
						flag=true;
					}
					if(!subcatagorylist.equals("0")){
						if(!flag){
							builder.append("where nabh_subcatagory.id='"+subcatagorylist+"' ");
						}else{
							builder.append("and nabh_subcatagory.id='"+subcatagorylist+"' ");
						}
					}
					
					PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Report report = new Report();
						report.setId(rs.getInt(1));
						report.setAreaid(rs.getString(2));
						report.setIndicator(rs.getString(3));
						String formula_desc ="";
						if(rs.getString(6)==null){
							formula_desc ="";
						}else{
							formula_desc =rs.getString(6);
						}
						report.setFormula_desc(formula_desc);
						String areaname = getAreaName(rs.getString(2));
						report.setArea(areaname);
						report.setIndi_check(rs.getString(5));
						report.setIsmannual(rs.getString(7));
						report.setFname(rs.getString(8));
						arrayList.add(report);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			private String getAreaName(String string) {
				//  09 oct 2017 for geting area name from ID
				String name ="";
				try {
					String sql ="select area from kpi_area where id='"+string+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						name = rs.getString(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return name;
			}

			public Report getMasterIndicator(String s) {
				Report report = new Report();
				try {
					StringBuilder builder = new StringBuilder();
					/*builder.append("select id, areaid, indicator, no_of_input, formula, formula_desc from his_indicator_master where id='"+s+"'");*/
					builder.append("select id, areaid, indicator, description, is_active, formula_decription,main_formula,totalinput from kpi_indicator where id='"+s+"'");
					PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						report.setIndicatorid(rs.getString(1));
						report.setAreaid(rs.getString(2));
						report.setIndicator(rs.getString(3));
						report.setFormula_desc(rs.getString(6));
						String areaname = getAreaName(rs.getString(2));
						report.setArea(areaname);
						report.setIndi_check(rs.getString(5));
						report.setFormula(rs.getString(7));
						report.setNo_of_input(rs.getString(8));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return report;
			}

			public int saveKPIData(Report report) {
				int result = 0;
				try {
					//String sql = "insert into his_kpi (areaid, indicatorid) values (?,?)";
					String sql = "insert into kpi_master (areaid, indicatorid, no_of_input, formula, formula_desc, is_active) values (?,?,?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, report.getAreaid());
					ps.setString(2, report.getIndicatorid());
					ps.setString(3, report.getNo_of_input());
					ps.setString(4, report.getFormula());
					ps.setString(5, report.getFormula_desc());
					ps.setString(6, "1");
					result = ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			public int updateIndicatorMasterData(String s, String status) {
				int result = 0;
				try {
					//String sql = "update his_indicator_master set ischecked='"+status+"' where id='"+s+"'";
					String sql = "update kpi_indicator set is_active='"+status+"' where id='"+s+"'";
					PreparedStatement ps = connection.prepareStatement(sql);
					result = ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			public int getIsKPIAlreadyExist(Report report) {
				int yes=0;
				try {
					//String sql ="select id from his_kpi where areaid='"+report.getAreaid()+"' and indicatorid ='"+report.getIndicatorid()+"' LIMIT    0, 1'";
					String sql ="select id from kpi_master where areaid='"+report.getAreaid()+"' and indicatorid ='"+report.getIndicatorid()+"' ";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						yes =1;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return yes;
			}

			
			public ArrayList<String> getKPIIndicatorId() {
				ArrayList<String> arrayList = new ArrayList<String>();
				String result = "";
				try{
					PreparedStatement preparedStatement = null;
					String sql = "select id from kpi_indicator";
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						result = rs.getString(1);
						arrayList.add(result);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<MisReport> getSms_Template() {
				ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
				String result = "";
				try{
					PreparedStatement preparedStatement = null;
					String sql = "select id, template_name, template_msg from sms_template";
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						MisReport misReport = new MisReport();
						misReport.setId(rs.getInt(1));
						misReport.setName(rs.getString(2));
						arrayList.add(misReport);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				return arrayList;
			}

			public MisReport getSmsTemplate(String tempid) {
				MisReport misReport = new MisReport();
				try{
					PreparedStatement preparedStatement = null;
					String sql = "select id, template_name, template_msg,subject from sms_template where id ='"+tempid+"'";
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						misReport.setId(rs.getInt(1));
						misReport.setName(rs.getString(2));
						misReport.setMessage(rs.getString(3));
						misReport.setSubject(rs.getString(4));
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				return misReport;
			}

			public boolean istemplateExist(String smtemplatename) {
				boolean flag = false;
				try{
					PreparedStatement preparedStatement = null;
					String sql = "select id, template_name, template_msg from sms_template where template_name ='"+smtemplatename+"'";
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						flag = true;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				return flag;
			}

			public int saveSMSTemplate(String smtemplatename, String message, String subject) {
				int result = 0;
				try {
					String sql = "insert into sms_template (template_name, template_msg,subject) values (?,?,?)";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, smtemplatename);
					ps.setString(2, message);
					ps.setString(3, subject);
					result = ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			public int saveIndicator(String areaid, String indicator, String formula, String formula_desc,
					String no_of_input) {
				int result = 0;
				try {
					String query = "insert into kpi_indicator(areaid, indicator, formula_decription, main_formula, totalinput) values(?,?,?,?,?)";
					PreparedStatement stmt = connection.prepareStatement(query); 
					stmt.setString(1, areaid);
					stmt.setString(2, indicator);
					stmt.setString(3, formula_desc);
					stmt.setString(4, formula);
					stmt.setString(5, no_of_input);
					result = stmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			public ArrayList<Report> getPatientTodayBList(String dobdate, String sendtype) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					String[] data = dobdate.split("-");
					/*String date = data[0]+"/"+data[1]+"/"+data[2];*/
					String date = data[0]+"/"+data[1];
					StringBuilder builder = new StringBuilder();
					builder.append("select mobno,address,fullname,email from apm_patient where dob like '"+date+"%' ");
					if(sendtype.equals("sms")){
						builder.append("and mobno is not null and mobno!='0' and mobno!='' ");
					}else{
						builder.append("and email is not null and email!='0' and email!='' ");
					}
					//builder.append("and mobno is not null and mobno!='' ");
					PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Report report = new Report();
						report.setFullname(rs.getString(3));
						report.setMobno(rs.getString(1));
						report.setAddress(rs.getString(2));
						report.setEmail(rs.getString(4));
						arrayList.add(report);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}
			public ArrayList<Report> getEmployeeTodayBList(String dobdate, String sendtype) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					String[] data = dobdate.split("-");
					/*String date = data[0]+"/"+data[1]+"/"+data[2];*/
					String date = data[0]+"/"+data[1];
					//String sql ="";
					StringBuffer buffer = new StringBuffer();
					buffer.append("select mobile,address,firstname, lastname,email from apm_user where dob like '"+date+"%' ");
					if(sendtype.equals("sms")){
						buffer.append("and mobile is not null and mobile!='0' and mobile!='' ");
					}else{
						buffer.append("and email is not null and email!='0' and email!='' ");
					}
					PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Report report = new Report();
						report.setFullname(rs.getString(3)+" "+rs.getString(4));
						report.setMobno(rs.getString(1));
						report.setAddress(rs.getString(2));
						report.setEmail(rs.getString(5));
						arrayList.add(report);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<Report> getPatientThirtPartyList(String thirdpartynameid, String sendtype) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					StringBuilder builder = new StringBuilder();
					builder.append("select mobno,address,fullname,email from apm_patient where third_party_name_id='"+thirdpartynameid+"' ");
					//builder.append("and mobno is not null and mobno!='' ");
					if(sendtype.equals("sms")){
						builder.append("and mobno is not null and mobno!='0' and mobno!='' ");
					}else{
						builder.append("and email is not null and email!='0' and email!='' ");
					}
					PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Report report = new Report();
						report.setFullname(rs.getString(3));
						report.setMobno(rs.getString(1));
						report.setAddress(rs.getString(2));
						report.setEmail(rs.getString(4));
						arrayList.add(report);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<Report> getPatientDRReferedByList(String refferedby, String sendtype) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					
					StringBuilder builder = new StringBuilder();
					//String sql ="select clientid from ipd_addmission_form where refferedby='"+refferedby+"'";
					builder.append("select mobno,address,fullname,email from ipd_addmission_form  ");
					builder.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientId ");
					builder.append("where refferedby='"+refferedby+"' ");
					if(sendtype.equals("sms")){
						builder.append("and mobno is not null and mobno!='0' and mobno!='' ");
					}else{
						builder.append("and email is not null and email!='0' and email!='' ");
					}
					PreparedStatement statement = connection.prepareStatement(builder.toString());
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
							Report report = new Report();
							report.setFullname(resultSet.getString(3));
							report.setMobno(resultSet.getString(1));
							report.setAddress(resultSet.getString(2));
							report.setEmail(resultSet.getString(4));
							arrayList.add(report);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<Report> getPatientNotEorMList(String mobileoremail, String sendtype) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					StringBuilder builder = new StringBuilder();
					builder.append("select mobno,address,fullname,email from apm_patient ");
					if(mobileoremail.equals("1")){
						builder.append("where mobno is null or mobno='' ");
					}else if(mobileoremail.equals("2")){
						builder.append("where email is null or email='' ");
					}else{
						builder.append("where (mobno is null or mobno='') and (email is null or email='') ");
					}
					
					PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Report report = new Report();
						report.setFullname(rs.getString(3));
						report.setMobno(rs.getString(1));
						report.setAddress(rs.getString(2));
						report.setEmail(rs.getString(4));
						arrayList.add(report);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<Report> getUserRoleorDepartList(String jobtitle, String specialityid, String sendtype) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					//String sql ="select mobile,address,firstname, lastname,email from apm_user where dob='"+date+"'";
					StringBuilder builder = new StringBuilder();
					builder.append("select mobile,address,firstname, lastname,email from apm_user ");
					if(!jobtitle.equals("0")  && !specialityid.equals("0")){
						builder.append("where jobtitle='"+jobtitle+"' and discription='"+specialityid+"' ");
					}else if(!jobtitle.equals("0")){
						builder.append("where jobtitle='"+jobtitle+"' ");
					}else{
						builder.append("where discription='"+specialityid+"' ");
					}
					if(sendtype.equals("sms")){
						builder.append("and mobile is not null and mobile!='0' and mobile!='' ");
					}else{
						builder.append("and email is not null and email!='0' and email!='' ");
					}
					PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Report report = new Report();
						report.setFullname(rs.getString(3)+" "+rs.getString(4));
						report.setMobno(rs.getString(1));
						report.setAddress(rs.getString(2));
						report.setEmail(rs.getString(5));
						arrayList.add(report);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<Report> getPatientInvtList(String invtname, String greaterthanid, String lessthanid,String sendtype) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					//StringBuilder builder = new StringBuilder();
					StringBuilder builder2 = new StringBuilder();
					//String sql ="select clientid from ipd_addmission_form where refferedby='"+refferedby+"'";
					builder2.append("SELECT mobno,address,fullname,email FROM apm_client_investigation ");
					builder2.append("inner join apm_patient on apm_patient.id = apm_client_investigation.clientid ");
					builder2.append("where invstname='"+invtname+"' ");
					if(!greaterthanid.equals("") && !lessthanid.equals("")){
						builder2.append("and obsvalue>'"+greaterthanid+"' and obsvalue<'"+lessthanid+"' ");
					}else if(!greaterthanid.equals("")){
						builder2.append("and obsvalue>'"+greaterthanid+"' ");
					}else if(!lessthanid.equals("")){
						builder2.append("and obsvalue<'"+lessthanid+"' ");
					}
					if(sendtype.equals("sms")){
						builder2.append("and mobno is not null and mobno!='0' and mobno!='' ");
					}else{
						builder2.append("and email is not null and email!='0' and email!='' ");
					}
					
					PreparedStatement statement = connection.prepareStatement(builder2.toString());
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
							Report report = new Report();
							report.setFullname(resultSet.getString(3));
							report.setMobno(resultSet.getString(1));
							report.setAddress(resultSet.getString(2));
							report.setEmail(resultSet.getString(4));
							arrayList.add(report);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public int saveMarketingSendHistory(String subject, String message, String allmobileno, String sendtype,
					String allemailid, String todate, String userid) {
				int result = 0;
				try {
					String query = "insert into marketing_history(templateid, subject, message, sendtype, tomailid, tomobileno, datetime, userid) values(?,?,?,?,?,?,?,?)";
					PreparedStatement stmt = connection.prepareStatement(query); 
					stmt.setString(1, "0");
					stmt.setString(2, subject);
					stmt.setString(3, message);
					stmt.setString(4, sendtype);
					stmt.setString(5, allemailid);
					stmt.setString(6, allmobileno);
					stmt.setString(7, todate);
					stmt.setString(8, userid);
					result = stmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			public ArrayList<Report> getMarketingSendList(String fromdate, String todate, String history_typefilter) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					todate = todate+" "+"59:59:59";
					StringBuilder builder = new StringBuilder();
					builder.append("select id, templateid, subject, message, sendtype, tomailid, tomobileno, datetime, userid from marketing_history ");
					builder.append("where datetime between '"+fromdate+"' and '"+todate+"' and sendtype='"+history_typefilter+"'");
					PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Report report = new Report();
						report.setId(rs.getInt(1));
						report.setTemplateid(rs.getString(2));
						report.setSubject(rs.getString(3));
						report.setMessage(rs.getString(4));
						report.setSendtype(rs.getString(5));
						report.setAllmailid(rs.getString(6));
						report.setAllmobno(rs.getString(7));
						String[] date = rs.getString(8).split(" ");
						String datetime = DateTimeUtils.getCommencingDate1(date[0])+" "+ date[1];
						report.setDate(datetime);
						report.setUserid(rs.getString(9));
						arrayList.add(report);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<Report> getPatientMedGivenList(String mdicinename, String medafterdate) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					String todate = dateFormat.format(cal.getTime());
					StringBuilder builder2 = new StringBuilder();
					builder2.append("select apm_client_parent_priscription.clientid from apm_client_parent_priscription ");
					builder2.append("inner join apm_client_priscription on apm_client_parent_priscription.id = apm_client_priscription.parentid ");
					builder2.append("where apm_client_parent_priscription.lastmodified between '"+medafterdate+"' and '"+todate+"' and mdicineid='"+mdicinename+"' ");
					PreparedStatement statement = connection.prepareStatement(builder2.toString());
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
						String sql1 ="select mobno,address,fullname,email from apm_patient where id='"+resultSet.getString(1)+"'";
						PreparedStatement preparedStatement = connection.prepareStatement(sql1);
						ResultSet rs = preparedStatement.executeQuery();
						while (rs.next()) {
							Report report = new Report();
							report.setFullname(rs.getString(3));
							report.setMobno(rs.getString(1));
							report.setAddress(rs.getString(2));
							report.setEmail(rs.getString(4));
							arrayList.add(report);
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<MisReport> getKPIAreaList(String string) {
				//  07 oct 2017 for KPI dashboard select area list
				ArrayList<MisReport>  arrayList = new ArrayList<MisReport>();
				try {
					//String sql ="select id,area from kpi_area";
					StringBuilder builder = new StringBuilder();
					builder.append("select kpi_area.id,area from kpi_area ");
					builder.append("inner join nabh_subcatagory on kpi_area.subcatagoryid = nabh_subcatagory.id ");
					builder.append("inner join nabh_Catagory on nabh_Catagory.id = nabh_subcatagory.catagoryid ");
					if(string!=null){
						builder.append("where nabh_Catagory.id='"+string+"'");
					}
					PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						MisReport misReport = new MisReport();
						misReport.setId(rs.getInt(1));
						misReport.setName(rs.getString(2));
						arrayList.add(misReport);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<Report> getListOfEmployeeForSMS(String sendtype) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					//String sql ="select mobile,address,firstname, lastname,email from apm_user";
					StringBuffer buffer = new StringBuffer();
					buffer.append("select mobile,address,firstname, lastname,email from apm_user ");
					if(sendtype.equals("sms")){
						buffer.append("where mobile is not null and mobile!='0' and mobile!='' ");
					}else{
						buffer.append("where email is not null and email!='0' and email!='' ");
					}
					PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Report report = new Report();
						report.setFullname(rs.getString(3)+" "+rs.getString(4));
						report.setMobno(rs.getString(1));
						report.setAddress(rs.getString(2));
						report.setEmail(rs.getString(5));
						arrayList.add(report);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public ArrayList<Report> getListOfPatientForSMS(String sendtype, String clientType) {
				ArrayList<Report> arrayList = new ArrayList<Report>();
				try{
					StringBuilder builder = new StringBuilder();
					builder.append("select mobno,address,fullname,email,client_type from apm_patient  ");
					if(sendtype.equals("sms")){
						builder.append("where mobno is not null and mobno!='0' and mobno!='' ");
					}else{
						builder.append("where email is not null and email!='0' and email!='' ");
					}
					if(!clientType.equals("") && !clientType.equals("0")) {
						builder.append("and client_type='"+clientType+"' ");
					}
					//builder.append("and mobno is not null and mobno!='' ");
					PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Report report = new Report();
						report.setFullname(rs.getString(3));
						report.setMobno(rs.getString(1));
						report.setAddress(rs.getString(2));
						report.setEmail(rs.getString(4));
						report.setClienttype(rs.getString(5));
						arrayList.add(report);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			@Override
			public ArrayList<Client> getsmsLogReportlist(String fromDate, String toDate, String patientname,
					String mobile) {
				PreparedStatement preparedStatement=null;
				ArrayList<Client>list=new  ArrayList<Client>();
				try {
					StringBuffer buffer=new StringBuffer();
					   buffer.append("select date_time, clinicid, sms_link, patient_name, mobno, error, status,smstype from sms_log "
							  + "where date_time between '"+fromDate+" 00:00:00' and '"+toDate+" 23:00:00'");
					if(!patientname.equals("")) {
					    buffer.append(" or patient_name like ('%" + patientname + "%')");	
					}
					if(!mobile.equals("")) {
						  buffer.append(" or mobno like ('%" + mobile + "%')");	
					}
					buffer.append(" order by id desc");
					preparedStatement=connection.prepareStatement(buffer.toString());
					ResultSet rs=preparedStatement.executeQuery();
					while(rs.next()) {
						Client client=new Client();
						client.setDate_time(rs.getString(1));
						client.setClinicid(rs.getString(2));
						client.setSms_link(rs.getString(3));
						client.setClientName(rs.getString(4));
						client.setMobNo(rs.getString(5));
						client.setError(rs.getString(6));
						client.setSmsstatus(rs.getString(7));
						client.setSmstype(rs.getString(8));
						list.add(client);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			@Override
			public ArrayList<Client> getClientReportList1(String thirdParty, String location, String fromDate,
					String toDate, String payby, String diaryUser, String orderby, String order) {
			 PreparedStatement preparedStatement=null;
			 ArrayList<Client>list = new ArrayList<Client>();
			 StringBuffer buffer = new StringBuffer();
			 StringBuffer sql = new StringBuffer();
			 //sql.append("select apm_patient.id,title,firstname,surname,mobno,email,gender,dob,address,town,country,postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,apm_patient.whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,emergencyContName,emergencyContNo,patientType,apm_patient.status,note,apm_patient.oldClientId,lastModified,abrivationid,sum(charge*quantity),sum(payment) ");
			// sql.append(" from apm_patient inner join apm_invoice_assesments on apm_invoice_assesments.clientId=apm_patient.id inner join apm_charges_payment on apm_charges_payment.clientid=apm_patient.id");
			// sql.append(" where lastModified between '"+fromDate+"' and '"+toDate+" 23:59:59' and whopay='client' group by apm_invoice_assesments.clientId");

			
					fromDate = DateTimeUtils.getCommencingDate(fromDate);
					toDate = DateTimeUtils.getCommencingDate(toDate);
					if(fromDate.equals(toDate)){
						toDate = toDate + " "+ "23:59:59";
					}
			sql.append("select apm_patient.id,title,firstname,surname,mobno,email,gender,dob,address,town,country,postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,apm_patient.whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,emergencyContName,emergencyContNo,patientType,apm_patient.status,note,apm_patient.oldClientId,lastModified,abrivationid,sum(charge*quantity)");
			sql.append(" from apm_invoice_assesments inner join apm_patient on apm_patient.id=apm_invoice_assesments.clientId");
			sql.append("  where whopay='client' and paybuy=0 and lastmodified between '"+fromDate+"' and '"+toDate+"'");	
			sql.append("  group by apm_invoice_assesments.clientId");
			 
			 try {
				 preparedStatement = connection.prepareStatement(sql.toString());
				 ResultSet rs = preparedStatement.executeQuery();
				 while(rs.next()) {
					 Client client = new Client();
						client.setId(rs.getInt(1));
						client.setTitle(rs.getString(2));
						client.setFirstName(rs.getString(3));
						client.setLastName(rs.getString(4));
						client.setMobNo(rs.getString(5));
						client.setEmail(rs.getString(6));
						client.setGender(rs.getString(7));
						client.setDob(rs.getString(8));
						client.setAddress(rs.getString(9));
						
						client.setTown(rs.getString(10));
						
						client.setCountry(rs.getString(11));
						client.setPostCode(rs.getString(12));
						client.setReference(rs.getString(13));
						client.setSourceOfIntro(rs.getString(14));
						client.setType(rs.getString(15));
						client.setTypeName(rs.getString(16));
						
						
						String thirdPartyType = getType(rs.getString(15));
						String thirdPartyTypeName = getTypeName(rs.getString(16));
						
						client.setThirdPartyType(thirdPartyType);
						client.setThirdPartyTypeName(thirdPartyTypeName);
						client.setOccupation(rs.getString(17));
						client.setExpiryDate(rs.getString(18));
						client.setWhopay(rs.getString(19));
						client.setPolicyAuthorzCode(rs.getString(20));
						client.setPolicyNo(rs.getString(21));
						client.setKnownAs(rs.getString(22));
						client.setCounty(rs.getString(23));
						client.setHomeNo(rs.getString(24));
						client.setWorkNo(rs.getString(25));
						client.setEmailCc(rs.getString(26));
						client.setPrefContactMode(rs.getString(27));
						client.setEmergencyContName(rs.getString(28));
						client.setEmergencyContNo(rs.getString(29));
						client.setPatientType(rs.getString(30));
						client.setStatus(rs.getBoolean(31));
						client.setNote(rs.getString(32));
						client.setOldclientId(rs.getString(33));
						client.setLastModified(rs.getString(34));
						client.setAbrivationid(rs.getString(35));
						int pracitonerId = getPractionerId(rs.getInt(1));
					    String practitionerName = getPractitionerName(pracitonerId);
						client.setDiaryUser(practitionerName);
						buffer=buffer.append(""+rs.getInt(1)+",");
					    client.setTotalSelfCharges(rs.getDouble(36));
						client.setClient_ids(""+buffer);
						list.add(client);
				 }
				
			} catch (Exception e) {
			   e.printStackTrace();
			}
				return list;
			}

			@Override
			public ArrayList<Client> getClientselfpaymentList(String fromDate, String toDate, String clientid) {
				PreparedStatement preparedStatement=null;
				 ArrayList<Client>list = new ArrayList<Client>();
				 StringBuffer sql = new StringBuffer();
					fromDate = DateTimeUtils.getCommencingDate(fromDate);
					toDate = DateTimeUtils.getCommencingDate(toDate);
					if(fromDate.equals(toDate)){
						toDate = toDate + " "+ "23:59:59";
					}
					sql.append("select sum(payment) from apm_charges_payment ");
					sql.append(" inner join apm_patient on apm_patient.id=apm_charges_payment.clientid "); 
					sql.append(" where lastmodified between '"+fromDate+"' and '"+toDate+"' and clientid in("+clientid+") group by apm_charges_payment.clientId");
				 try {
					 preparedStatement = connection.prepareStatement(sql.toString());
					 ResultSet rs = preparedStatement.executeQuery();
					 while(rs.next()) {
					 Client client = new Client();
					 client.setTotalSelfPaidAmt(rs.getDouble(1));
					 list.add(client);
					 }
					 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			@Override
			public ArrayList<Client> getClientReportTPList(String thirdParty, String location, String fromDate,
					String toDate, String payby, String diaryUser, String orderby, String order) {

				 PreparedStatement preparedStatement=null;
				 ArrayList<Client>list = new ArrayList<Client>();
				 StringBuffer buffer = new StringBuffer();
				 StringBuffer sql = new StringBuffer();
				 //sql.append("select apm_patient.id,title,firstname,surname,mobno,email,gender,dob,address,town,country,postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,apm_patient.whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,emergencyContName,emergencyContNo,patientType,apm_patient.status,note,apm_patient.oldClientId,lastModified,abrivationid,sum(charge*quantity),sum(payment) ");
				// sql.append(" from apm_patient inner join apm_invoice_assesments on apm_invoice_assesments.clientId=apm_patient.id inner join apm_charges_payment on apm_charges_payment.clientid=apm_patient.id");
				// sql.append(" where lastModified between '"+fromDate+"' and '"+toDate+" 23:59:59' and whopay='client' group by apm_invoice_assesments.clientId");

				
						fromDate = DateTimeUtils.getCommencingDate(fromDate);
						toDate = DateTimeUtils.getCommencingDate(toDate);
						if(fromDate.equals(toDate)){
							toDate = toDate + " "+ "23:59:59";
						}
				sql.append("select apm_patient.id,title,firstname,surname,mobno,email,gender,dob,address,town,country,postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,apm_patient.whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,emergencyContName,emergencyContNo,patientType,apm_patient.status,note,apm_patient.oldClientId,lastModified,abrivationid,sum(charge*quantity)");
				sql.append(" from apm_invoice_assesments inner join apm_patient on apm_patient.id=apm_invoice_assesments.clientId");
				sql.append("  where whopay='Third Party' and paybuy=1 and lastmodified between '"+fromDate+"' and '"+toDate+"'");	
				sql.append("  group by apm_invoice_assesments.clientId");
				 
				 try {
					 preparedStatement = connection.prepareStatement(sql.toString());
					 ResultSet rs = preparedStatement.executeQuery();
					 while(rs.next()) {
						 Client client = new Client();
							client.setId(rs.getInt(1));
							client.setTitle(rs.getString(2));
							client.setFirstName(rs.getString(3));
							client.setLastName(rs.getString(4));
							client.setMobNo(rs.getString(5));
							client.setEmail(rs.getString(6));
							client.setGender(rs.getString(7));
							client.setDob(rs.getString(8));
							client.setAddress(rs.getString(9));
							
							client.setTown(rs.getString(10));
							
							client.setCountry(rs.getString(11));
							client.setPostCode(rs.getString(12));
							client.setReference(rs.getString(13));
							client.setSourceOfIntro(rs.getString(14));
							client.setType(rs.getString(15));
							client.setTypeName(rs.getString(16));
							
							
							String thirdPartyType = getType(rs.getString(15));
							String thirdPartyTypeName = getTypeName(rs.getString(16));
							
							client.setThirdPartyType(thirdPartyType);
							client.setThirdPartyTypeName(thirdPartyTypeName);
							client.setOccupation(rs.getString(17));
							client.setExpiryDate(rs.getString(18));
							client.setWhopay(rs.getString(19));
							client.setPolicyAuthorzCode(rs.getString(20));
							client.setPolicyNo(rs.getString(21));
							client.setKnownAs(rs.getString(22));
							client.setCounty(rs.getString(23));
							client.setHomeNo(rs.getString(24));
							client.setWorkNo(rs.getString(25));
							client.setEmailCc(rs.getString(26));
							client.setPrefContactMode(rs.getString(27));
							client.setEmergencyContName(rs.getString(28));
							client.setEmergencyContNo(rs.getString(29));
							client.setPatientType(rs.getString(30));
							client.setStatus(rs.getBoolean(31));
							client.setNote(rs.getString(32));
							client.setOldclientId(rs.getString(33));
							client.setLastModified(rs.getString(34));
							client.setAbrivationid(rs.getString(35));
							int pracitonerId = getPractionerId(rs.getInt(1));
						    String practitionerName = getPractitionerName(pracitonerId);
							client.setDiaryUser(practitionerName);
							buffer=buffer.append(""+rs.getInt(1)+",");
						    client.setTotalTPCharges(rs.getDouble(36));
							client.setClient_ids(""+buffer);
							list.add(client);
					 }
					
				} catch (Exception e) {
				   e.printStackTrace();
				}
					return list;
				
			}

			@Override
			public ArrayList<Client> getClientTPpaymentList(String fromDate, String toDate, String clientid1) {
				PreparedStatement preparedStatement=null;
				 ArrayList<Client>list = new ArrayList<Client>();
				 StringBuffer sql = new StringBuffer();
					fromDate = DateTimeUtils.getCommencingDate(fromDate);
					toDate = DateTimeUtils.getCommencingDate(toDate);
					if(fromDate.equals(toDate)){
						toDate = toDate + " "+ "23:59:59";
					}
					sql.append("select sum(payment) from apm_charges_payment ");
					sql.append(" inner join apm_patient on apm_patient.id=apm_charges_payment.clientid "); 
					sql.append(" where lastmodified between '"+fromDate+"' and '"+toDate+"'  and clientid in("+clientid1+") group by apm_charges_payment.clientId");
				 try {
					 preparedStatement = connection.prepareStatement(sql.toString());
					 ResultSet rs = preparedStatement.executeQuery();
					 while(rs.next()) {
					 Client client = new Client();
					 client.setTotalTPPaidAmt(rs.getDouble(1));
					 list.add(client);
					 }
					 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			@Override
			public ArrayList<Client> getClientTPdebitlist(String clientid) {
				PreparedStatement pst=null;
				ArrayList<Client> list=new ArrayList<Client>();
				 StringBuffer sql = new StringBuffer();
				 sql.append("select sum(debit-discamt) from apm_charges_invoice where clientid in("+clientid+") group by clientid");
				 try {
					pst = connection.prepareStatement(sql.toString());
					 ResultSet rs = pst.executeQuery();
					 while(rs.next()) {
						 Client client = new Client();
						 client.setDebitSum(rs.getString(1));
						 list.add(client);
					 }
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			@Override
			public ArrayList<Client> getClientSelfdebitlist(String clientid) {
				PreparedStatement pst=null;
				ArrayList<Client> list=new ArrayList<Client>();
				 StringBuffer sql = new StringBuffer();
				 sql.append("select sum(charge*quantity) from apm_charges_invoice where clientid in("+clientid+") group by clientid");
				 try {
					pst = connection.prepareStatement(sql.toString());
					 ResultSet rs = pst.executeQuery();
					 while(rs.next()) {
						 Client client = new Client();
						 client.setDebitSum(rs.getString(1));
						 list.add(client);
					 }
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			@Override
			public DiaryManagement getsmtpData() {
				PreparedStatement pmt = null;
				DiaryManagement diaryManagement = new DiaryManagement();

				try {

					String sql = "select SMTP, password, emailId from apm_clinic_location where id=1 ";
					pmt = connection.prepareStatement(sql);

					ResultSet rs = pmt.executeQuery();

					while (rs.next()) {

						diaryManagement.setSMTP(rs.getString(1));;
						diaryManagement.setPassword(rs.getString(2));
						diaryManagement.setEmailId(rs.getString(3));
						
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				return diaryManagement;
				
			}

			@Override
			public int saveMarketingSendHistory1(String subject, String message, String allmobileno, String sendtype,
					String allemailid, String todate, String userid,DiaryManagement diaryManagement) {
				int result = 0;
				try {
					String query = "insert into marketing_history(templateid, subject, message, sendtype, tomailid, tomobileno, datetime, userid,client_id) values(?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt = connection.prepareStatement(query); 
					stmt.setString(1, diaryManagement.getTemplateid());
					stmt.setString(2, subject);
					stmt.setString(3, message);
					stmt.setString(4, sendtype);
					stmt.setString(5, diaryManagement.getEmailId());
					stmt.setString(6, diaryManagement.getMobNo());
					stmt.setString(7, todate);
					stmt.setString(8, userid);
					stmt.setString(9, diaryManagement.getClientid());
					result = stmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
				
			}



}
