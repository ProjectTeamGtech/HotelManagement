package com.apm.DiaryManagement.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface DiaryManagementDAO {

	int saveAppointmentSlot(DiaryManagement diaryManagement) ;

	ArrayList<DiaryManagement> getPractionerList(int year,int clinicid,Pagination pagination,String searchText,LoginInfo loginInfo) ;

	ArrayList<Location> getLocationList(int id) ;

	String getcityfrmaindashboard(int selectedid) ;

	String getWeekList(String tdCode, int year) ;

	ArrayList<DiaryManagement> getAppointmentSlotData(String diaryuserid,int clinicUserid,String tdcode,String year) ;

	int updateAppointment(int selectedid,DiaryManagement diaryManagement) ;

	int deleteAppointmentSlot(int selectedid) ;

	boolean checkUsertdCodeExist(String usertdcode,String year) ;
	public ArrayList<Master> getSpecializationList()  ;

	int deleteAppointSlotByTdcode(String usertdcode,String year) ;

	int deleteAppointSlotByTdcode(String tdCode, String string, String location) ;

	ArrayList<DiaryManagement> getAppointmentSlotData(int id, String date,
			String year1) ;

	ArrayList<DiaryManagement> getAppointmentSlotData2(String diaryuserid,
			int id, String date, String string,String locationid) ;

	ArrayList<DiaryManagement> getAppointmentSlotData3(int id, String date,
			String year1) ;

	ArrayList<DiaryManagement> getAllDiaryUserAppointmentSlotData(
			String diaryuserid, int id, String commencing,String location) ;

	ArrayList<NotAvailableSlot> getApmtList(String diaryuserid,String practitionerid,String opendb,String rdddvalue) ;

	boolean checkPractitionerAvailibility(String commencing, String diaryuserid) ;

	int getDiarymanagementTotalUser(int year,int clinicid, String searchText,LoginInfo loginInfo) ;

	int getTempUsedSession(String treatmentEpisodeId) ;

	int updateTreatmentSession(int usedsession, String treatmentEpisodeId) ;

	ArrayList<NotAvailableSlot> getAppintmentSendMailDetails(int appointmentid) ;

	int deleteDiarySlot(String selectedid) ;

	int updateCurrentUserPosition(int prevPosition, int curPosition) ;

	int updateUserPosition(int position, int id) ;

	int getUserIdToSetPosition(int prevPosition) ;

	ArrayList<NotAvailableSlot> getWrapedEventData(String eventid,String opendb) ;

	boolean checkEventAllreadyExist(String commencing, String location,
			String diaryuserId, String starttime, String endtime) ;

	boolean checkEventAllreadyExist(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId) ;

	DiaryManagement getAvailableSlotdata(int editAppointId) ;

	int coutnEsistingSlot(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId) ;

	String getExistStartTime(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId) ;

	String getEditExistStartTime(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId) ;

	boolean checkSlotExist(String dt, DiaryManagement diaryManagement) ;

	boolean checkApmtExist(String selectedid) ;

	double getClientDebitTotal(String clientId) ;

	double getClientPayment(String clientId) ;

	Clinic getClinicStartAndEndTime(int clinicid) ;

	int getTempSessions(String treatmentEpisodeId) ;

	int updateRoleAccess(DiaryManagement diaryManagement) ;

	DiaryManagement getRoleAccessofUser(String jobtitle) ;

	ArrayList<DiaryManagement> getAllEventList(Pagination pagination) ;

	int getTotalEventsCount() ;

	ArrayList<DiaryManagement> getAllJobTitleList() ;
	String getJobTitleNamefromID(int selectedid) ;

	int saveEvent(DiaryManagement diaryManagement) ;

	int deleteEvent(String id) ;

	DiaryManagement getEvent(String selectedid) ;

	int updateEvent(DiaryManagement diaryManagement) ;

	ArrayList<Master> getWardList() ;

	ArrayList<UserProfile> getRotaList(String fromDate, String toDate,String strdate,String jobtitle) ;

	ArrayList<DiaryManagement> getWeekNameList(String fromDate, String toDate) ;

	ArrayList<DiaryManagement> getAllEventListExist() ;

	String getAsistantDoctorList(int apmtid) ;

	ArrayList<String> getDepartmentList() ;

	DiaryManagement getEmergencyDetails() ;

	int updateEmergencyDetails(DiaryManagement diaryManagement) ;

	ArrayList<DiaryManagement> getAllDisplayDiaryUserAppointmentSlotData(String diaryuserid, int id, String commencing,
			String location,LoginInfo info);

	boolean checkAppointmentCompleted(int id);
	 UserProfile getUserProfile(int practitionerId);

	DiaryManagement getdoctorDiarydata(String docid);

	int saveDiaryforfakept(DiaryManagement diaryManagement);

	int getupdatehasDiary(String docid);

	DiaryManagement getdoctorDetails(int selectedid);

	ArrayList<DiaryManagement> getManasPractionerList(int year, int id, Pagination pagination, String searchText,
			LoginInfo loginInfo, String clinistaff);

	int updateData(String smtp, String password, String email);

	

	

	

	

}
