package com.apm.CoreJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.action.RegistrationAction;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

public class CoreJava {

	public static void main(String[] args) {
		new CoreJava().uploadKaalmeghBackPatient();
        //new CoreJava().uploadKalmeghIpdPatient();
	}
	
	private void uploadKalmeghIpdPatient() {
		try(Connection connection = databaseLink("193.46.199.49", "kaalmegh")) {
			System.out.println("Start");
			FinderDAO finderDAO = new JDBCFinderDAO(connection);
			ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			LoginInfo loginInfo = new LoginInfo();
			
			int patientcount = 0;
			String type ="0";
			String userSessionId = "0";
			String patientAgeType ="";
			//2022-12-21
			String fromDate ="2023-01-17";
			String nextDate ="2023-01-18";
			String drId="0";
			String currentDate = "";
			loginInfo.setTimeZone("IST");
			Clinic clinicaccess=clinicDAO.getNewClinicAccess();
			
			System.out.println("Diary Set");
			//Fake Patient data
			//ipddata
			int ipddata=1;
			ArrayList<Client> fakepatientlist = finderDAO.getFakePatientData(patientcount,type,userSessionId,patientAgeType,true,fromDate,drId,nextDate,ipddata);
			for (Client client : fakepatientlist) {
				currentDate = client.getAdmissiondate();
				String abrivation=generateAbrivationId(currentDate,connection);
				String lastmodified = client.getAdmissiondate()+" "+ DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				int previousDate = 1;
				
				client.setRegDate(lastmodified);
				client.setLastModified(lastmodified);
				client.setLastModifiedDate(lastmodified);
				client.setAbrivationid(abrivation);
				client.setFakestatus("1");
				if(client.getMiddlename()==null){
					client.setMiddlename("");
				}
				ThirdParty thirdParty=thirdPartyDAO.getThirdPartyDetails(""+clinicaccess.getPatient_category());
				client.setTypeName(""+clinicaccess.getPatient_category());
				client.setType(""+thirdParty.getThirdPartyId());
				client.setPatientcategory(clientDAO.getThirdPartyCompanyName(client.getTypeName()));
				
				//Save New Patient main table
				int clientid=clientDAO.savePatient(client, client.getId(),loginInfo);
				finderDAO.updatePatientTransferSts(client.getId(),previousDate);
				System.out.print("Patient Saved:"+clientid+"  - ");
				
				//Save Fake Patient data
				String actionType="0";
				String ipAbrivationId = bedDao.generateIPDKalmeghAbrivation(currentDate,actionType);
				client.setAbrivationid(ipAbrivationId);
				client.setIpdid("0");
				int ipdid = bedDao.saveFakeIPDPatient(client,clientid);
				System.out.println("IPD Patient"+ipdid);
			}
			System.out.println("End");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void uploadKaalmeghBackPatient() {
		try(Connection connection = databaseLink("localhost","kaalmegh")) {
			System.out.println("Start");
			FinderDAO finderDAO = new JDBCFinderDAO(connection);
			ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			LoginInfo loginInfo = new LoginInfo();
			
			int patientcount = 0;
			String type ="0";
			String userSessionId = "0";
			String patientAgeType ="";
			//2022-12-21
			String fromDate ="2021-07-23";
			String nextDate ="2021-08-24";
			String drId="0";
			String currentDate = "";
			loginInfo.setTimeZone("IST");
			Clinic clinicaccess=clinicDAO.getNewClinicAccess();
			
			//check dr diary set or not
			setDiaryOfUser(fromDate,nextDate,connection);
			System.out.println("Diary Set");
			//Fake Patient data
			ArrayList<Client> fakepatientlist = finderDAO.getFakePatientData(patientcount,type,userSessionId,patientAgeType,true,fromDate,drId,nextDate,0);
			for (Client client : fakepatientlist) {
				currentDate = client.getApmtDate();
				String abrivation=generateAbrivationId(currentDate,connection);
				String lastmodified = currentDate +" "+ DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				int previousDate = 1;
				
				client.setRegDate(lastmodified);
				client.setLastModified(lastmodified);
				client.setLastModifiedDate(lastmodified);
				client.setAbrivationid(abrivation);
				client.setFakestatus("1");
				if(client.getMiddlename()==null){
					client.setMiddlename("");
				}
				ThirdParty thirdParty=thirdPartyDAO.getThirdPartyDetails(""+clinicaccess.getPatient_category());
				client.setTypeName(""+clinicaccess.getPatient_category());
				client.setType(""+thirdParty.getThirdPartyId());
				client.setPatientcategory(clientDAO.getThirdPartyCompanyName(client.getTypeName()));
				
				//Save New Patient main table
				int clientid=clientDAO.savePatient(client, client.getId(),loginInfo);
				finderDAO.updatePatientTransferSts(client.getId(),previousDate);
				System.out.print("Patient Saved:"+clientid+"  - ");
				//Save Appointment
				String diaryuserId = client.getDrnameId();
				String dept = userProfileDAO.getSpeciality(diaryuserId);
				String apmtId=savedirectappointment(diaryuserId, ""+clientid, dept, 1, currentDate, "0", 0,connection); 
				System.out.print("Appoitment Save:+"+apmtId+"");
				System.out.println();
			}
			System.out.println("End");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void setDiaryOfUser(String fromDate, String nextDate, Connection connection) {
		try {
			String sql="select apmtDate,drnameId from fake_patient "
					+ "where apmtDate between '"+fromDate+"' and '"+nextDate+"' "
					+ "group by apmtDate,drnameId order by apmtDate asc";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			while(rs.next()){
				boolean available = checkAvilableSlotOrNot(rs.getString(1),rs.getString(2),connection);
				if(!available){
					DiaryManagement diaryManagement = new DiaryManagement();
					String drName = userProfileDAO.getName(rs.getString(2));
					
					diaryManagement.setCommencing(rs.getString(1));
					diaryManagement.setSelectedDiaryUser(drName);
					diaryManagement.setDiarUserid(rs.getInt(2));
					diaryManagement.setLocation("1");
					diaryManagement.setRoom("0");
					diaryManagement.setDescription(null);
					diaryManagement.setSTime("09:00");
					diaryManagement.setEndTime("16:00");
					diaryManagement.setApmtDuration("00:05");
					diaryManagement.setOnlineBooking(false);
					//need to set
					diaryManagement.setTdCode("");
					//2022,12,22
					int year = DateTimeUtils.convertToInteger(rs.getString(1).split("-")[0]);
					int month = DateTimeUtils.convertToInteger(rs.getString(1).split("-")[1]);
					int day = DateTimeUtils.convertToInteger(rs.getString(1).split("-")[2]);
					LocalDate localDate = LocalDate.of(year, month, day);
			        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
			        
			        String weekname = dayOfWeek.toString().split("")[0];
			        diaryManagement.setWeekName(weekname);
					diaryManagement.setYear(""+year);
					diaryManagement.setWeekFullName(dayOfWeek.toString());
					diaryManagementDAO.saveAppointmentSlot(diaryManagement);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private boolean checkAvilableSlotOrNot(String apmtDate, String drnameId, Connection connection) {
		boolean flag= false;;
		try {
			String sql="SELECT * FROM apm_apmt_slot where commencing='"+apmtDate+"' and diaryuserid ='"+drnameId+"' limit 1;";
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

	public String savedirectappointment(String diaryuserid,String clientid, String dept, int preDate, 
			String commencingDate, String referfromdeptid, int deptOpdId, Connection connection) throws Exception {
		int appointmentid =0;
		try {
			String userid="kaalmegh";
			NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			Date date = new Date();
			String commencing= new SimpleDateFormat("yyyy-MM-dd").format(date);
			if(preDate==1){
				commencing = commencingDate;
			}
			NotAvailableSlot n = notAvailableSlotDAO.getNewOpdDiaryUserData(commencing,diaryuserid);
			String duration = n.getDuration();
			notAvailableSlot.setSTime(n.getSlotstime());
			
			/*
			Date date1 = new Date();
			DateFormat format = new SimpleDateFormat("HH:mm"); 
			String currentTime=format.format(date1);
			String mm=currentTime.split(":")[1];
			String strmm="";
			int rem=Integer.parseInt(mm)%5;
			if(rem==0){
				notAvailableSlot.setSTime(currentTime);
			}else{
				int mmm=Integer.parseInt(mm);
				while(mmm%5!=0){
					mmm--;
				}
				strmm=""+mmm;
				if(mmm<=9){
					String singlemm=String.valueOf(mmm);
					singlemm="0"+singlemm;
					strmm=singlemm;
				}
				String updatetime=currentTime.split(":")[0]+":"+strmm;
				notAvailableSlot.setSTime(updatetime);
			}*/
			notAvailableSlot.setApmtSlotId(n.getId());
			
			notAvailableSlot.setApmtDuration(duration);
			String endtime1=DateTimeUtils.getTotalofTwoTime(notAvailableSlot.getSTime(),duration);
			notAvailableSlot.setEndTime(endtime1);
			notAvailableSlot.setDiaryUserId(DateTimeUtils.convertToInteger(diaryuserid));
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			String dusername = userProfile.getInitial() + " " + userProfile.getFirstname() + " "+ userProfile.getLastname();
			notAvailableSlot.setDiaryUser(dusername);
			Client client=clientDAO.getClientDetails(clientid);
			notAvailableSlot.setDept(dept);
			notAvailableSlot.setLocation("1");
			notAvailableSlot.setRoom("Room1");
			notAvailableSlot.setCommencing(commencing);
			notAvailableSlot.setClient(client.getTitle()+" "+client.getFullname());
			notAvailableSlot.setApmtType("1");
			notAvailableSlot.setNotes("");
			notAvailableSlot.setClientId(clientid);
			notAvailableSlot.setTreatmentEpisodeId("0");
			notAvailableSlot.setAddedBy(userid);
			notAvailableSlot.setModifiedBy(userid);
			notAvailableSlot.setPayBy(client.getWhopay());
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
			Date now = new Date();
			String opdbooktime = sdfTime.format(now);
			notAvailableSlot.setOpdbooktime(opdbooktime);
			notAvailableSlot.setMobstatus("0");
			String speciality=userProfileDAO.getSpeciality(diaryuserid);
			notAvailableSlot.setSpeciality(speciality);
			notAvailableSlot.setOtplan("0");
			
			notAvailableSlot.setRefferedfrom("");
			notAvailableSlot.setPreDate(preDate);
			notAvailableSlot.setDeptOpdId(deptOpdId);
			appointmentid = notAvailableSlotDAO.saveAppointment(notAvailableSlot);
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ""+appointmentid;
	}
public String generateAbrivationId(String currentDate, Connection connection) {
		
		String abrivationid = "";
		try {
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		String cdate = currentDate;
		
//		String cdate="2021-11-30";
		boolean checkifseq = clientDAO.checkifSequenceExist(cdate);
		
		
		String clinicabrivation = clientDAO.getClinicAbrivation(1);
		String tempd[] = cdate.split("-");
		String y = tempd[0];
		String m = tempd[1];
		String d = tempd[2];
		String newseq="";
		if(checkifseq){
			
			int seqno = clientDAO.getSqeunceNumber(cdate);
			seqno++;
			int r = clientDAO.InserCdateSeq(cdate,seqno);
			//SNH170609001
			int yr = Integer.parseInt(y)%1000;
				yr=Integer.parseInt(y);
				if(String.valueOf(seqno).length()==1)
				{
					 newseq="000"+seqno;
				}else if(String.valueOf(seqno).length()==2){
					 newseq="00"+seqno;
				}else if(String.valueOf(seqno).length()==3){
					 newseq="0"+seqno;
				}else{
					newseq=""+seqno;
				}
			abrivationid = clinicabrivation + yr + m +d + "" +newseq ;
		}else{
			int seqno = 1;
			int r = clientDAO.InserCdateSeq(cdate,seqno);
			//String seqno = clientDAO.getSqeunceNumber(cdate);
			int yr = Integer.parseInt(y)%1000;
				yr=Integer.parseInt(y);
				if(String.valueOf(seqno).length()==1)
				{
					 newseq="000"+seqno;
				}else if(String.valueOf(seqno).length()==2){
					 newseq="00"+seqno;
				}else if(String.valueOf(seqno).length()==3){
					 newseq="0"+seqno;
				}else{
					newseq=""+seqno;
				}
			abrivationid = clinicabrivation + yr + m +d + "" + newseq;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return abrivationid;
		
	}

	public Connection databaseLink(String link,String databaseName){
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://"+link+"/"+databaseName+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			System.out.println("Connection Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
