package com.apm.DiaryManagement.eu.bi;

import java.util.ArrayList;

import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.form.NotAvailableSlotForm;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface FinderDAO {

	ArrayList<NotAvailableSlot> getFinderList(String clientId,String date, String toDAte, String practId,String payee,Appointment appointment, String apptype, String allapmt);

	ArrayList<NotAvailableSlot> departmentOPDList(String department, String date, Pagination pagination, String category, String orderby, LoginInfo loginInfo, String primarydoc);

	String getBookedDateTime(String commencing, String clientid);

	ArrayList<Client> getFakePatientData(int patientcount, String type, String userSessionId, String patientAgeType, boolean iskaalmegh, String fromDate, String drId, String nextDate, int ipddata);

	String generateAbrivationId(LoginInfo loginInfo, String currentDate);

	int updatePatientTransferSts1(int id, int previousDate, String specialityid);

	int updateModifyAppointment(String clientid, String aptid, String department);

	ArrayList<NotAvailableSlot> departmentOPDPreviewList(String patienttype, String department, String fromDate,
			String toDate, Pagination pagination, String category, String secondarydoc, String primarydoc, String referto, String orderby, String searchText,LoginInfo loginInfo, String referfrom);

	int getcountOfDepartmentOPDPreview(String patienttype, String department, String fromDate, String toDate,
			String category, String secondarydoc, String primarydoc, String referto, String orderby, String referfrom);

	int updatePatientDepartment(String clientid, String dept);

	int getcountOfDepartmentOPD(String department, String date, String category, String orderby);

	void insertinsideopd_secondary_dr(String deptaptid, String opdappointmentid, String secondarydr, String date, String clientid);

	ArrayList<Client> getMoveFakePatientData(String ids);

	int updatePatientTypeAndDob(Client client);

	ArrayList<NotAvailableSlot> fakedepartmentOPDList(String department, String date, Pagination pagination, String string,
			String string2);

	String getclientidbyODMRid(String aptid);

	int updatedepartmentfaketransfer(String appointment);

	int updatefakestatus(String appointment, int previousDate, int preDeptId);

	ArrayList<Client> getOldDepartmentList(int patientcount, String type, String department, String date, int preDeptId,String currentdate);

	int saveDetails(NotAvailableSlotForm notAvailableSlotForm);

	ArrayList<NotAvailableSlot> getadmissionlist();

	NotAvailableSlot viewcollegeadmission(String id);

	int updatePatient(Client client1, String id);

	ArrayList<NotAvailableSlot> getAdditionalChargeTypeList(String deptid);

	int saveFollowUpDetails(NotAvailableSlot notAvailableSlot, String clientid, String deptid, String givenDate);

	ArrayList<NotAvailableSlot> getProcedureList(String deptid, String clientid);

	ArrayList<NotAvailableSlot> gettpNameList(String patientType);

	ArrayList<NotAvailableSlot> gettpNameListAll();

	int getFakePatientCount(int patientcount, String type, String loginsessionid);

	int getPreDeptId(String dept, String date);

	int getsittingData(NotAvailableSlot notAvailableSlot);

	int updateDepartmentfollowupdate(String department, String date);

	void updateDepartmentFollowUpDone(int preDeptId);

	ArrayList<NotAvailableSlot> dailyRegistrationList(String fromdate, String todate);

	int getcountOfDailyregistrationreport(String patienttype, String department, String fromDate, String toDate,
			String category, String secondarydoc, String primarydoc, String referto, String orderby);

	ArrayList<NotAvailableSlot> dailyRegistrationreport(String patienttype, String department, String fromDate,
			String toDate, Pagination pagination, String category, String secondarydoc, String primarydoc,
			String referto, String orderby, String searchText);

	ArrayList<NotAvailableSlot> getreferedpatientlist(String fromDate, String toDate, String searchText, 
			boolean smallClinic,String sclinicid, boolean isHospital, String userid, String pro_userid, int superadmin);

	int cancelDeletePatient(String parentid, String delete_reason, String userid, String datetime);

	int confirmReferPatient(String parentid, String userid, String datetime);

	ArrayList<Master> getPatientStatusList();

	int updateReferPatientStatus(String parentid, String patientStatus, String userid, String datetime);

	int updateReferPatientPayment(String parentid, String totalBillAmt, String clinicShare, String consultantShare,
			String clinicShareAmt, String consultantShareAmt, String userid, String datetime);

	boolean checkLoginByUserOrHosp(String sclinicid);

	NotAvailableSlot geteditsitting(String selectedid);

	int getupdatesitting(NotAvailableSlot notAvailableSlot);

	int deletesittingdata(String selectedid);

	String Departmentopdid(String selectedid);

	void deletedata(String opdid);

	ArrayList<Master> getOffersList(int i);

	int updatedata(NotAvailableSlot notAvailableSlot);

	int saveDepartmentfollowupdate(String deptopdid, String date);

	ArrayList<NotAvailableSlot> dailyRegistrationReport1(String patienttype, String department, String fromDate,
			String toDate, String category, String secondarydoc, String primarydoc, String referto, String orderby,
			String searchText);

	ArrayList<NotAvailableSlot> dailyRegistrationReport2(String patienttype, String department, String fromDate,
			String toDate, String category, String secondarydoc, String primarydoc, String referto, String orderby,
			String searchText, long dnumber);

	void insertinnew_secondary_dr(String aptid, String newappointmentid, String dr, String dateTime, String clientid);

	ArrayList<NotAvailableSlot> getnewFinderList(String clientId, String fromDate, String todate, String practionerId,
			String payee, Appointment appointment, String apptype, String apmtType, String searchtext, String appf, String fkdepartment);

	String getfakedatebyClientid(String fakclientid);

	ArrayList<NotAvailableSlot> getconsultDepartmentList();

	int getinvestigationCount(String clientid);

	int getipdCount(String clientid);

	int getprescriptionCount(String clientid);

	int getpharmacyCount(String clientid);

	int getopdCount(String clientid);

	int getotCount(String clientid);

	String getfOTdatebyClientid(String fakclientid);

	String getdepmnDate(String fkdepartment);

	String getotDatebydropdown();

	int getdeleteData(String clientid);

	int updatePatientTransferSts(int id, int previousDate);

	ArrayList<NotAvailableSlot> getPatientHistory(String searchText);

	Client getRefereddata(String parentid);

	int getnoteCount(String ipdaddmissionid);

	NotAvailableSlot getClientId(String id);

	int saveColorCode(NotAvailableSlot notAvailableSlot);

	ArrayList<NotAvailableSlot> getSecondaryData(String secondary_dr2);

	ArrayList<NotAvailableSlot> PhysioIPDList(String department, String date, Pagination pagination, String category,
			String orderby, LoginInfo loginInfo, String searchtext);

	int getIpdphysioaccess(int id);

	int saveIpdsittingdata(NotAvailableSlot notAvailableSlot);

	int gettotalSittingcount(String selecteddept_id, String client_id);

	String getChargesInvoiceid(String clientid);

	ArrayList<NotAvailableSlot> departmentIPDPreviewList(String patienttype, String department, String fromDate,
			String toDate, Pagination pagination, String category, String secondarydoc, String primarydoc,
			String referto, String orderby, String searchText, LoginInfo loginInfo,String string2);

	int getcountOfDepartmentIPDPreview(String patienttype, String department, String fromDate, String toDate,
			String category, String secondarydoc, String primarydoc, String referto, String orderby);

	ArrayList<NotAvailableSlot> getdepartmentIpdList(String patienttype, String department, String fromDate,
			String toDate, Pagination pagination, String category, String secondarydoc, String primarydoc,
			String referto, String orderby, String searchText, LoginInfo loginInfo);

	String getdepartementname(String string);

	int updateVaccduedateforvet(String duedate, String mastername, String clientid, String action);

	int getDuedate_flagbyclientid(String clientid, String action, String mastername);

	NotAvailableSlot getPetmedcininedataFollowup(String clientId, String mastername, String action);

	int insertMedicinefollowup(NotAvailableSlot notAvailableSlot, String action);

	int updateFollowupid(NotAvailableSlot notAvailableSlot, String action, int id);

	int checkFollowUpGiven(int parseInt);

	int saveDeptapptid(int appointmentid, String clientid);

	NotAvailableSlot getClientnamebyId(String clientid);

	String getOdmrCount(String fromDate, String toDate);

	ArrayList<NotAvailableSlot> deptwisecount(String fromDate, String toDate, String referto);




	



}
