package com.apm.DiaryManagement.eu.bi;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface NotAvailableSlotDAO  {
	
	String getDiaryUserName(String practitionerid) ;
	
	public ArrayList<DiaryManagement> getUserList(int clinicid,String commencing);
	ArrayList<Location> getLocationList(int id);
	ArrayList<NotAvailableSlot> getList(int id, String date);
	ArrayList<DiaryManagement> getUserList(int id);
	int saveAppointment(NotAvailableSlot notAvailableSlot);
	int saveBlockSlot(NotAvailableSlot notAvailableSlot,String opendb);
	ArrayList<AppointmentType> getAppointmentTypeList();
	int updateAppointment(NotAvailableSlot notAvailableSlot,int selectedid);
	int updateBlockSlot(NotAvailableSlot notAvailableSlot, int selectedid,String opendb);
	int updateClientHasArrived(int selectedid, int status);
	int updateClientIsBeingSeen(int selectedid, int status);
	int updateResetNotArrived(int selectedid, int status);
	int updateDNA(int selectedid, String notes, boolean dna,
			String dnaReason,String dnacharge);
	int saveCharge(NotAvailableSlot notAvailableSlot, String apmtType, int result);
	int updateCharge(NotAvailableSlot notAvailableSlot, String apmtType,
			int selectedid);
	
	ArrayList<NotAvailableSlot> getPrintDataOfWeek(String practionerId,
			String fromDate, String toDate);
	ArrayList<NotAvailableSlot> getPractitionerPrintData(String practionerId,
			String date);
	ArrayList<NotAvailableSlot> getAllPractitionerPrintData(String date,String enddate,String location,String diaryuser,String action,String opendb,String diaryuserid, String string, String toDate, LoginInfo loginInfo);
	String getAppointmentDuration(String connection);
	
	int UpdateDragAndDropData(String availableSlotID, String practitionerid,
			String startTime, String endTime,String slotid,String location,String commencing);
	int getSelecedTreatmentEpisodeId(int selectedid);
	ArrayList<NotAvailableSlot> getAvailableSlotList(String avlbltyDate,
			String chdiaryUser, String chlocation);
	boolean checkEventAllreadyExist(String commencing, String location,
			String diaryuserId, String starttime, String endtime);
	NotAvailableSlot getAvailableSlotdata(int editAppointId);
	boolean checkEventAllreadyExist(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId);
	ArrayList<NotAvailableSlot> getAllAvailableSlotList(String date,
			String diaryuserId, String location);
	String getDuration(String selectedid);
	
	 String getAppointmentTypeText(String id);
	int DeleteBlockedSlot(String selectedid,String opendb);
	int getStatus(String availableSlotID,String opendb);
	int saveDnaCharge(NotAvailableSlot notAvailableSlot, int selectedid);
	int saveApmInvoiceAssesment(NotAvailableSlot notAvailableSlot,
			int invoiceid, int appointmentid);
	int deleteDnaInvoiceAssesment(int selectedid);
	int deleteDnaInvoice(int selectedid);
	NotAvailableSlot getAvailableSlotDnadata(int selectedid);
	int updateSessions(int treatmeId);
	int getUserTotalCount(String opendb,String jobgroup);
	ArrayList<DiaryManagement> getDashBoardUserList(int id,
			Pagination pagination,String commencing,String opendb,String jobgroup);
	int saveApmtInLog(int appointmentid, String date, String time, String userId, String clientId, String commencingTemp, String sTime, String status,String lastModifiedDate);
	String getClientId(String availableSlotID);
	int saveDnaLog(String clientId, String date, String time, String userId,
			int selectedid);
	int getAppointmentSlotID(String commencing, int diaryUserId,String starttime,String endtime,String location);
	double getPendingBalanceTotal(String clientId);
	int getUsedSession(int treatmeId, String selectedid);
	ArrayList<NotAvailableSlot> getUsedSessionList(int treatmeId,
			int usedSession);
	int updateAllPrevious(int id, int treatmeId);
	String getConditionPatient(String clientId);
	int updateCondition(String clientId, String conditionID);
	int getUserListToalCount();
	int saveActivity(int clientId, int activity);
	int coutnEsistingSlot(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId);
	String getExistStartTime(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId);
	boolean checkDurationExist(String duration);
	String getEditExistStartTime(String commencing, String location,
			String diaryuserId, String starttime, String endtime,
			int editAppointId);
	double getDNAAercentage();
	ThirdParty getTPDNALimit(String clientId);
	NotAvailableSlot getApmtDetailsForLog(int selectedid);
	double getInvoiceCharge(int selectedid);
	int getDNAInvoicePayBy(int selectedid);
	int updateDNAInvoiceAssesment(String editdnaCharge, String dnapayby,int invoiceid);
	int getDNAInvoiceid(int selectedid);
	int saveCancelApmtInLog(int parseInt, String date, String time, String userId,
			String clientId, String commencingTemp, String sTime,
			String apmtstatus, String cancelApmtNote,String lastModifiedDate,NotAvailableSlot notAvailableSlot);
	
	int getPreviousTreatmentUsedSession(int selectedTreatmentEpisodeId);
	int updatePreviousTreatmentEpisode(int prevTreatmentSession,
			int selectedTreatmentEpisodeId);
	String getSpecificDnaCharge(int selectedid);
	int saveDnaApmInvoiceAssesment(NotAvailableSlot notAvailableSlot,
			int invoiceid,int appointmentid, String dnapercentage);
	int IncraeseDnaOffset(int selectedid, int usedsession);
	int getTpEpisodeUsedSession(String tepisodeid);
	int updateupdateTpEpisodeusedsession(String tepisodeid, int usedsession);
	int updateDnaOffset(boolean dnaOffset, int appointmentTypeid);
	int updateAppointmentDnaoffset(int selectedid, boolean dnaOffset);
	ArrayList<NotAvailableSlot> getDnaOffsetList(String treatmentEpisodeId);
	int updateAppointmentUsedSession(int id, int usedsession);
	int updateClientCondition(String clientId, String condition);
	NotAvailableSlot getInitilizedElementData(String diaryuserid,
			String commencing, String selectedStarttime, String prevEndTime);
	ArrayList<String> getRemainderAppointmentList(String dt);
	int getAdDurationData(int clinicid);
	String getDiaryUserFullname(String diaryuser);
	String getLocationName(String location);
	int saveLoggedInUserID(String userid);
	String getApmLoggedUserList(String clinicid);
	boolean checLoggedkUseridExist(String userid);
	String getSlotCommencingDate(String slotid);
	NotAvailableSlot getTreatmentDetails(int apptid);
	NotAvailableSlot getApmtConsNoteData(String apmtid);
	DiaryManagement getApmtSlotData(String date, String diaryuser,
			String location);
	String getDiaryUserIdName(int diaryUserId);
	boolean checkApmtExist(String clientId);
	int updateNewPtsStatus(String clientId, int sts);
	String getDiaryDuration(String slotid);
	ArrayList<Master> getMedicineList(String selectedid);
	ArrayList<Priscription> getFollowupApmtList(String followupdate);
	int updateAD(String appointmentid);
	ArrayList<UserProfile> getOTstaffList();
	int saveParenrotData(String commencing,String selectedot,String asistantdoctor,int apmtid);
	int checkotAppointment(String selectedid);
	int deleteOtApmt(int otid);
	ArrayList<UserProfile> getOTDoctorList();
	String getSelectedDiagnosisID(String editAppointId);
	int updateApmtDiagnosis(String editAppointId, String conid);
	boolean checkOtChargeExist(String editAppointId);
	int updateConsultationEmr(String editAppointId, String conid);
	int addMultiConditionstoEmr(String editAppointId, String moreconditions);
	String getDiagnosisNameFromID(String condition);
	ArrayList<Diagnosis> getMultipleConditions(String text);
	String getMultipleDiagnosis(String editAppointId);
	int updateWorkCompleted(int selectedid, String status,String datetime,String cancelnotes);
	double getCharge(String apmtType);
    int addOpdConditionReport(int apmtid,String clientid,String conditionid,String lastmodified);
	int deleteOpdConditionifExistsReport(String editAppointId);
	String getDiaryUserId(String appointmentid);
	ArrayList<Master> getProcedureList(String department);
	int updateOTdata(NotAvailableSlot notAvailableSlot, int selectedid);
	int deleteAsistantDoctor(int selectedid);
	int getOtAppointmentSlotID(String commencing, int parseInt, String time,
			String endTime, String location);
	int deleteBlockOt(String stafflistid);
	String getTemplateText(String id);
	NotAvailableSlot getOTData(String apmtid);
	int updateOtnotes(NotAvailableSlot notAvailableSlot, int id,String imageData);
	String getOtProcedure(String appointemntid);
	NotAvailableSlot getOTDataByIpd(String selectedid);
	ArrayList<DiaryManagement> getEditUserList(int id, String selectedid);
	int updateDischargeOtData(String sessionadmissionid,NotAvailableSlot notAvailableSlot);

	boolean checkifSequenceExist(String cdate,int diaryuserid);

	int getSqeunceNumber(String cdate,int diaryuserid);

	int InserCdateSeq(String cdate, int seqno,int appointmentid,int diaryuserid);

	ArrayList<Master> getAccountUserList();

	int getLastAppointmentId(String valueOf);

	String getDrNameFromApptId(int apptid);

	String getDrApptId(int apptid);
	ArrayList<DiaryManagement> getUserAccountList(int id);

	int saveOptionalForm(NotAvailableSlot notAvailableSlot);

	NotAvailableSlot getOptionalFormDetails(int id);

	ArrayList<NotAvailableSlot> getAllOptionFormList(String clientId);

	int updateOptionalForm(NotAvailableSlot notAvailableSlot);

	ArrayList<Master> getotDepartmenrList();

	
	int saveMultiImgData(String otnotesapmtid, String data22, String savemoreid, String otnotes);

	boolean checkMultiOtDataExist(String otnotesapmtid, String savemoreid);

	int updateMultimgotData(String otnotesapmtid, String data22, String savemoreid, String otnotes);

	ArrayList<Master> getMultiOtImgList(String otnotesapmtid, String savemoreid);

	int getSpeciFromRefernce(int surgeonid);

	ArrayList<NotAvailableSlot> getOTNotesFormList(String clientId);

	NotAvailableSlot getProcedureDepartment(String editAppointId);

	int getSpeciSurgonFromRefernce(int parseInt);

	int updateimmurtizationchart(String clientId, String colname, String val);

	Master getImmurtizeData(String clientid);

	Boolean checkimmurtizationdata(String clientid);

	int saveimmurtizationdata(String clientid);

	ArrayList<DiaryManagement> getDisplayDashBoardUserList(int id, Pagination pagination, String commencing,
			String opendb, String selectedjobgroup,String resultstr);

	ArrayList<DiaryManagement> getIdDashBoardUserList(int id, Pagination pagination, String commencing, String opendb,
			String selectedjobgroup);
//lokesh 
	

	ArrayList<Master> getVaccinationdata(Client client);
	int getvacinedependacyvale(String dependid);
	int savevacinationimmunizationajax(String mastername,String masterid, String clientid,String givendate,String duedate);
	int checkvacinationimmunizationajaxData(String masterid, String clientid);
	int updatevacinationimmunizationajaxdate(String id, String givendate,String duedate);
	int updatevacinationimmunizationajaxRemark(String client,String masterid,String remark);
	Master getremarkNdate(String clientid,String masterid);
	int setdatatoVacinationInfo(Master master,String clientid);
	boolean checksmsflag(int masterid,String clientid);
	int setsmsflag(int masterid,String clientid);
	
	ArrayList<Master> getAllClientVaccinations(String date, LoginInfo loginInfo, boolean is1daysms);
	String lmpDAte(String clientid);
	Master getVacinationInfo(String masterid,String clientid);
	ArrayList<Master> getVacinationForShow(String clientid);

	NotAvailableSlot getNewOpdDiaryUserData(String ndate, String nduserid);

	ArrayList<NotAvailableSlot> getNewOpdList(String ndate, String nduserid, LoginInfo loginInfo, String opdlogstatus, String patientcategory);

	int updateChargeStatus(String appointmentid);
	int saveDateOfOPDEvents(String appointmentid , String columnname);
	int saveDiagnosisOpd(String opdid,String diagnosisid);
	
	String getAllDiagnosisofOpd(String opdid);

	boolean chkmveapmtaxsist(String duserid, String commencing);

	NotAvailableSlot getMveDiaryUserDetails(String duserid, String commencing);

	String getmveapmtendtime(String duserid, String commencing);

	int updatemveappointment(NotAvailableSlot n);

	int updatemveapminvoice(String editAppointId,NotAvailableSlot n);

	Accounts getmveapmtchargeinfo(String editAppointId);

	int updatemveapminvoiceassesmnt(int id, NotAvailableSlot n);

	int updatemveapmtchargesinvoice(int invoiceid, NotAvailableSlot n);
	String getGivenDate(int dependson,String clientid);
	int updatedueDateOfVaccine(String clientid, String masterid, String date);
	
	void startImmnunizationAppTProccess(String date, LoginInfo loginInfo, String type);
	int updateDueDate(String value, String masterid, String clientid);
	String getDueDate(String masterid, String clientid);
	int getVaccinator();
	
	boolean bookededStatsu(String clientid);
	String getDOBChangeLogDate(String clientId);
	int insertDobChangeLog(String clientId, String dob);
	int updateScheduledDateAfterDOBChange(String masterid, String date, String clientId);

	int checkPatientChargesCreated(String apmtid);

	ArrayList<DiaryManagement> getUserListwithdept(int id, String dept);

	ArrayList<NotAvailableSlot> getctimeList(String cweekName);

	boolean checkallbooked(String date, int id, String sTime);

	NotAvailableSlot getDiaryData(String date, String string, String location);

	boolean checkLastAppointment(int diaryuserid, String speciality, String clientid);

	String getlastappointmentdate(int diaryuserid, String speciality, String clientid);

	String getAppointmentTypeId(String opdFollowupCharges);

	ArrayList<AppointmentType> getAppointmentTypeListForOPD();

	int checkBlockslot(String date, int diaryuserid, String starttime1);

	boolean checkAttachment(int id);

	int setnotesandstatus(String editAppointId, String remark);

	int setvideostatus(String editAppointId);

	boolean checkisappointment(int id, String commencing);

	ArrayList<NotAvailableSlot> getSheduleStaffReport(String fromDate, String toDate);

	ArrayList<DiaryManagement> getUserListwithdepartment(int id, String dept);

	int getUndoneAppointment(String nduserid, String ndate);

	ArrayList<DiaryManagement> getallchargesList(String month, String year, String practid);

	ArrayList<NotAvailableSlot> getVaccinationList(String fromDate, String toDate, String nduserid);

	ArrayList<DiaryManagement> getUserListofvaccination(int id);

	String getVaccinationLocation();

	NotAvailableSlot getVaccinationdataFromId(String vaccinationid);

	int updateConsumeStatus(String vaccinationid, String string);

	int updateVacinationConsumptiondata(String vaccinationid, int result, int prodid, String qty);
	ArrayList<NotAvailableSlot> vaccinationListOfPatients(String fromDate, String toDate,String type,String masterid,String clientId, String status, String serachText);
	boolean checkVaccineSmsSent(String date , String vaccineid);
	void saveToVaccineSMSLog(String vaccineId);

	boolean sendVaccineSMSManual(String vaccineId,LoginInfo loginInfo);
	void addApmtChangeLog(NotAvailableSlot notAvailableSlot);
	String lmpDateFromVital(String clientId);
	String hindiBdaySMSText(String clientId,LoginInfo loginInfo);

	int updatereferfrom(String appointment, String refferedfrom);
	int saveDepartmentAppointment(NotAvailableSlot notAvailableSlot);

	NotAvailableSlot getDepartmentAvailableSlotdata(int appointmentid);

	int updateDeparttmentrefferdfrom(String newappointmentid, String dept);

	int updatediaryuser(String aptid, String diaryuserid, String diaryusername);

	int updateclientDepartment(String dept, String clientid);

	ArrayList<Master> getselecteddeptlist(String commencing, String clientid);

	int updaterefferremark(String appointment, String referremark);

	int settrefertodepartment(String aptid, String dept);

	int updaterefferremarkforAllDept(NotAvailableSlot notavailableslot, String referremark, String commencing);

	ArrayList<NotAvailableSlot> getRemarklist(String commencing, String clientid);

	int updatePatientStatus(String appointment, int status);

	int patientNewOrOld(String clientid, String dept);

	ArrayList<NotAvailableSlot> vaccinationListOfPatient(String abrivationid, int consumption_status);

	int getPatientFakeStatus(String clientid);

	int updateDepartmentFakeStatus(int fakestatus, String appointment);

	String getConsumeIdFromVaccinId(String vaccinationid);

	Map<Integer, String> getVaccineProductList(String consumeid);

	String getPractIdFromApmtId(String apmtId);

	ArrayList<DiaryManagement> getadmittedbyUserList();

	ArrayList<DiaryManagement> getipdfakeDepartment();

	ArrayList<Master> getVaccineListbyid(int id, Client client);

	int setdatatoVacinationInfo1(Master master, String clientid);

	int getClientCount(String clientid, int masterid);

	ArrayList<NotAvailableSlot> getDeptwisegenderList(String fromDate, String toDate, LoginInfo loginInfo);

	Client getChargebyapmtid(String appointmentid);

	ArrayList<DiaryManagement> getOutsourcemasterlist();

	String getdatetimebyid(String clientid, String appointmentid);

	ArrayList<DiaryManagement> getUserListwithdeptToken(int id, String string);

	NotAvailableSlot getpatientdetails(String clientid);

	ArrayList<DiaryManagement> getManasClinicUserList(LoginInfo loginInfo);

	String getclinicstaff(int id);

	String getManasClinicName(String clinicstaff_id);

	ArrayList<NotAvailableSlot> getManasclinicAllPractitionerPrintData(String date, String endDate, String location,
			String diaryuser, String action, String openedb, String previewdiaryuser, String fromDate, String toDate,
			LoginInfo loginInfo, String clinicstaff_id);

	int updateVaccineflag(int id);

	String getDepatmentId(String id);

	String getdeptName(String deptid);

	ArrayList<NotAvailableSlot> getPhysioIpdAllPractitionerPrintData(String date, String endDate, String location,
			String diaryuser, String action, String openedb, String previewdiaryuser, String fromDate, String toDate,
			LoginInfo loginInfo);

	ArrayList<Master> getVaccinationdataveterinary(Client client);

	int getvacinedependacyvaleveterinary(String vacine_dependson);

	int checkvacinationimmunizationajaxDataVet(String valueOf, String clientid);

	int setdatatoVacinationInfoVet(Master master, String clientid);

	boolean checksmsflagvet(int id, String clientid);

	String getGivenDateVet(int dependson, String clientid);

	int updatedueDateOfVaccineVet(String clientid, String string, String dependate);

	String getDueDateVet(String string, String clientid);

	ArrayList<NotAvailableSlot> vaccinationListOfPatientsvet(String fromDate, String toDate, String vaccineType,
			String vaccineMasterId, String clientId, String status, String serachText);

	ArrayList<Master> getAllClientdeworming(String date1, LoginInfo loginInfo, boolean is1daysms);

	int savevacinationimmunizationajaxVet(String mastername, String masterid, String clientid, String givendate,
			String duedate);

	int updatevacinationimmunizationajaxdateVet(String valueOf, String givendate, String duedate);

	ArrayList<NotAvailableSlot> vaccinationListOfPatientVeterinary(String abrivationid, int consumption_status);

	int updateConsumeStatusVet(String vaccinationid, String string);

	int updateVacinationConsumptiondataVet(String vaccinationid, int parentid, int prodid, String qty);

	String getConsumeIdFromVaccinIdVet(String vaccinationid);

	ArrayList<DiaryManagement> getManasClinicUserList(int id, String clinicstaff_id);

	ArrayList<DiaryManagement> getUserList1(LoginInfo loginInfo);

	int saveAppointmentType(String chargename);

	ArrayList<DiaryManagement> getUserListByOutsideHosp(String practid);

	ArrayList<Master> getDoctorList();

	int insertProceduredata(int appointmentid, String string);

	double getProcedureCharges(String procedure);

	//int updateCharges(double chargediff, int id);

	int saveaApmInvoiceData(NotAvailableSlot apmt, int appointmentid);

	int saveAppointment1(NotAvailableSlot apmt);

	int saveApmInvoiceAssesmentformark(NotAvailableSlot apmt, int apminv, int appointmentid);

	String getDoctorsDept(String duserid);

	int updateTransferAppointment(NotAvailableSlot n, int diaryUserId);

	String getInvoice_id(NotAvailableSlot apmt);

	int deleteAppointment(int id);

	double getDebitfromChargeinvoice(String invoiceid);

	int updateChargeInvoicedebit(double newcharge, String invoiceid);

	int updateAssessmentCharge(double newcharge, String invoiceid);

	int updateChargesPayment(double newcharge, String invoiceid);

	NotAvailableSlot getDoctorname(String invoiceid);

	ArrayList<DiaryManagement> getUserListforOpdData(int id, String fromdate, String todate);
}
