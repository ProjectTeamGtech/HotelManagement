package com.apm.Emr.eu.bi;

import java.util.ArrayList;

import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.web.common.helper.LoginInfo;

public interface EmrDAO {
	int updateTreatmentEpisodeDischargeForm(Bed bed2);

	ArrayList<Emr> getEmrList(String date,int practitionerid);

	int saveMedicalHistory(Emr emr);

	ArrayList<Emr> getMedicalHistoryList(int patientid,int practitonerid, int pid);

	String getEmrData(int selectedid);

	int updateMedicalHistory(int selectedid, String medicalhistory);

	int deleteMedicalHistory(int selectedid);

	int saveConsultationNote(Emr emr);

	ArrayList<Emr> getConsultationList(int patientid, int id,int pid, String firstName,String lastName, int apmtId);

	String getConsultationNoteText(String selectedid);

	int updateConsultationNote(int selectedid,String editconstext);

	int deleteConsultation(int selectedid);

	int saveSocialHistory(Emr emr);

	ArrayList<Emr> getSocialHistoryList(int patientid, int id, int pid, int conditionId);

	String getSocialData(int selectedid);

	int updateSocialHistory(int selectedid, String socialhistory);

	int deleteSocialHistory(int selectedid);

	ArrayList<Emr> getReminderList(int id, int practitionerID, int pid);

	int saveReminder(Emr emr);

	Emr getReminderText(String selectedid);

	int updateReminder(int selectedid, String reminder, String editalert, String edithh, String editmin);

	int deleteReminder(int selectedid);

	ArrayList<Emr> getPrescriptionList(int id, int practitionerID, int pid, int conditionId);

	int savePrescription(Emr emr);

	String getPrescriptionText(String selectedid);

	int updatePrescription(int selectedid, String prescription);

	int deletePrescription(int selectedid);

	ArrayList<Emr> getAllergyList(int id, int practitionerID, int id2);

	int saveAllergy(Emr emr);

	String getAllergyText(String selectedid);

	int updateAllergy(int selectedid, String allergy);

	int deleteAllergy(int selectedid);

	int saveDocument(Emr emr);

	ArrayList<Emr> getDocList(int id, int practitionerID, int id2, int conditionId,String filterdoctype);

	Emr getDocumentText(String selectedid);

	int updateDocument(int selectedid, String document, String fileName);

	int deleteDocument(int selectedid);

	String getReportFieldExist(int apmtid, int clientid);

	String getPractitionerEmail(int pid);

	Emr getSelectedPatientEmr(int apmtid, int practitionerID);

	ArrayList<Emr> getSelectedMedicalHistoryList(int appointmentid, int id,	int practitionerID, int id2);

	ArrayList<Emr> getSelectedConsultationList(int appointmentid, int id,int practitionerID, int id2, String firstName, String lastName);

	ArrayList<Emr> getSelectedDocList(int appointmentid, int id, int practitionerID,int id2);

	ArrayList<Emr> getSelectedSocialHistoryList(int appointmentid, int id,int practitionerID, int id2);

	ArrayList<Emr> getSelectedReminderList(int appointmentid, int id,int practitionerID, int id2);

	ArrayList<Emr> getSelectedPrescriptionList(int appointmentid, int id,int practitionerID, int id2);

	ArrayList<Emr> getSelectedAllergyList(int appointmentid, int id,int practitionerID, int id2);

	int getConditionOfAmpt(int apmtid);

	String getPractitionerName(String selectedPatientId1, int apmtId);

	int getLatestAppointmentId(String clientId);

	int getPractitionerId(String clientId, int apmtId);

	ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String clientId, String practionerId, String condition);

	ArrayList<Emr> getMedicalRecordTypeList();

	int saveMedicalHistoryRecords(Emr emr,String date);

	

	ArrayList<Emr> getMedicalRecordTypeList(int parseInt, int parseInt2,
			int id, String condition);

	int saveNewMedicalRecordType(String newtype);

	String getLastMedicalRecordType();

	int savePatientDocument(Emr emr1,String date,String invstid,String userid);

	int savePatientConsultationNote(Emr emr,String date);

	int updatePatientConsultationNote(Emr emr, int consulatation_note_id,String date);

	ArrayList<Assessment> getImageDataList(String clientId,
			String practionerId, String condition);

	int updateImageDateOfClient(String clientImageDataId, String imagedata);

	int saveImageDateOfClient(String imagedata, String practionerId,
			String patientid, String condition);

	int deleteImageDateOfClient(String clientImageDataId);

	ArrayList<Assessment> getAssessmentList(String clientId,
			String practionerId, String condition);

	int updateMedicalHistoryRecords(Emr emr, String id,String date);

	int deleteMedicalHistoryRecords(String id);

	int updatePatientDocument(String editDoctId, Emr emr,String date);

	int deleteDocuments(String id);

	int saveVideoClip(Emr emr,String date);

	ArrayList<Emr> getVideoList(String clientId, String practionerId,
			String condition);

	String getLastAppointmentid(String clientId,String practitionerid,String condition);

	ArrayList<Discharge> getDischrageOutcomeList();

	ArrayList<Discharge> getDischrageStatusList();

	int updateTreatmentEpisodeSischargeStatus(String dischrgeOutcomes,
			String dischargeStatus, int status,String treatmentEpisodeid,String datetime,String hospcourse,String disadvnotes,String findondischarge,String treatmentgiven,String investigation,String otNotes);



	int updateTreatmentEpisodeSischargeStatus(String dischrgeOutcomes,
			String dischargeStatus, int status, String treatmentEpisodeid,
			String dischargedate, String hospitalcourse, String discadvnotes,
			String example,String findondischarge,String treatmentgiven,String investigation,String otNotes);
	
	Emr getAppointmentidConsultationNote(String apmtid);

	int deleteAssesmentForm(String id);

	ArrayList<Master> getmedicineCategoryList();

	int savePriscription(Priscription priscription,String date,int parentid,String clientId,String practitionerID,String conditionid);

	ArrayList<Priscription> getPractitionerList(String clientid,
			String practionerid, String conditionid, String categoryid,
			String medicineid,String date);

	Priscription getEditPriscription(String selectedid);

	int editsavePriscription(Priscription priscription,
			String ukCurrentDataTime, String selectedid);

	int deletePriscriptionData(String selectedid);

	ArrayList<Priscription> getAllPriscList(String clientId,
			int practitionerID, String conditionid);

	ArrayList<Master> getDosageList();

	int saveParentPriscription(Priscription priscription,
			String ukCurrentDataTime,String sessionadmissionid,String discharge, String admission, String userid,String location, int default_location, String json_ipdid, String apmtId);

	boolean checkParentIdExist(int saveparent);

	Priscription getPriscriptionParentData(int parentid);

	ArrayList<Priscription> getParentPriscList(String clientid,String practionerid,String conditionid);

	ArrayList<Priscription> getSelectedPriscList(String selectedid);

	int deleteParentPrisc(String selectedid);

	int deleteParet(String selectedid);

	ArrayList<Master> getMedicineTypeList();

	ArrayList<Master> getInvesigationTypeList();

	ArrayList<Master> getInvstReportTypeList();

	ArrayList<Master> getInvstUnitList();

	String getMedicineStrength(String mdicinenameid);

	String getRegisteredLocationId();

	ArrayList<Bed> getIpdAdmissionList(String clientId);

	ArrayList<Bed> getDischargeList(String clientId);

	String getpriscIpdId(int selectedid);

	int saveShareEmr(Client client);

	int saveConfidentialEmr(String diaryUser, String clientname,
			String condition, String confpassd);

	int checkConfidentialEmr(String clientId, String practionerId,
			String condition);

	boolean chkConfidentialExist(String diaryUser, String clientname,
			String condition);

	String getConfidentialPassword(String diaryUser, String clientname,
			String condition);

	String getAllConditionsFromApmt(String apmtId);

	

	public ArrayList<Emr> getDicomImageData();

	boolean ispackImageExists(String imgid);

	String getEmrConditionName(String condition);

	boolean checkMdicineExist(String mdicinenameid, String editparentpriscid);

	int getSelectedPriscId(String mdicinenameid, String editparentpriscid);

	int updateExixstMedicine(Priscription priscription2, int priscid);

	ArrayList getDbPriscription(String editparentpriscid);

	int delSelectedPriscription(String mdcineid,String parentid);

	int saveParentPriscriptionTemplate(Priscription priscription,
			String currentDataTime, String sessionadmissionid, String discharge);

	int savePriscriptionTemplate(Priscription priscription2,
			String currentDataTime, int saveparent, String clientId,
			String prectionerid, String conditionid);

	ArrayList<Priscription> getTemplateNameList(LoginInfo loginInfo);

	Priscription getTemplatePriscriptionParentData(int parseInt);

	ArrayList<Priscription> getSelectedTemplatePriscList(String selectedid);

	String getopdconditionList(String apmtid, String clientname);

	int deleteAppointmentCondition(String apmtId);

	ArrayList<Master> getOtAssetList();
	public String getregionalText(String dose);
	
	int saveOtEquipment(Priscription priscription2, String currentDataTime,
			int saveparent, String clientId, String prectionerid,
			String conditionid,String apmtid);

	int saveParentotequipment(Priscription priscription,
			String currentDataTime, String sessionadmissionid, String discharge);

	int saveParentOtEqTemplate(Priscription priscription,
			String currentDataTime, String sessionadmissionid, String discharge);

	ArrayList<Priscription> getoteqTemplateNameList();

	Priscription getTemplateoteqParentData(int parseInt);

	ArrayList<Priscription> getSelectedTemplateoteqList(String selectedid);

	int saveoteqTemplate(Priscription priscription2, String currentDataTime,
			int saveparent, String clientId, String prectionerid,
			String conditionid);

	int saveParentoteq(Priscription priscription, String currentDataTime,
			String sessionadmissionid, String discharge);

	ArrayList<Emr> getOtEqList(String apmtid);

	int saveRemoteDicomId(String id, String userId,String action);

	int getMultiPacsid(String id);

	int updatePacsViewer(String id, String userId);

	int resetAllcgh(String userId);

	String getMedicineShedule(String mdicinenameid);

	int saveNewMedicine(String genericName, String medicineName);

	Clinic getsmsCheckConditionList(int practitionerid);

	public ArrayList<Emr> getDocumentList(String clientid,String ipdopdemr,String fromdate,String todate);

	ArrayList<Priscription> getIpdSelectedPriscList(String ipdid,String clientid);

	int updateMedcinename(String ghdnmdnameid, String medicinename);

	ArrayList<Bed> getGynicFormList(String clientId);

	ArrayList<Priscription> getTemplateSpecializationList(String department);
	public ArrayList<Emr> getConsultationListMobApi(String clientid);

	ArrayList<Priscription> getMedicineTimeList();
	public int updatePrisc(Priscription priscription);

	int deleteChildPriscTempData(int saveparent);
String getMedicicneName(String medid);

int checkifdicom(String id);

ArrayList<Priscription> getPriscriptionChildData(String parentid, int isforreturn);

int saveNewMedBynurse(Priscription priscription);

int saveParentPriscNew(Priscription priscription, String remark, String parentid, String default_phar_location, int directtransfer);

int saveChildPriscNew(Priscription priscription, String mdrequestqty, String parentid, String childid, int newparentid);

ArrayList<Priscription> getRptParentPriscList(String clientId, String prectionerid, String conditionid);
String getMedtype(String id);

String getMedicineUom(String mdicinenameid);

ArrayList<Priscription> getPrintPriscList(String string, LoginInfo loginInfo);

String getrname(String priscindivisualremark);
String getGenerictype(String id);

ArrayList<Master> getOtroomList();

ArrayList<Master> getOtUserList();

boolean checkIsAdmitedPatient(String clientId);

ArrayList<DietaryDetails> dietryList(String clientId, String practioner);
ArrayList<Master> nursingcareList(String clientId, String practioner);
ArrayList<Master> vitalList(String clientId, String practioner);
Master getVitals(String clientId, String practioner);

int getPriscTokenId(String cdate);

int updateTokenInPric(int newparentid, int tokenid);

ArrayList<Bed> getNewGynicFormList(String clientId);

int getCheckUserVideoConfernce(String apmtId);

ArrayList<String> getInvestigationOPDGiven(String apmtid);

ArrayList<Priscription> getPrescriptionListOPDGiven(String apmtid);

ArrayList<Emr> getOpdEmrLogList(String clientId);

ArrayList<Emr> getIpdEmrLogList(String clientId);

int saveEntryToEMR(Emr emr, String ukCurrentDataTime, String tablename);

boolean checkDateExistonEmrLog(String date,int clientid);

int insertIntoEmrActivity(String date,int clientid,int appintmentid, String ipdid);
String getOpdAbbrevationId(int apmtid);

String getsecondarydrlist(String clientId, String apmtId);

ArrayList<NotAvailableSlot> getsittingfollowuplist(String clientid);

String followupstatus(int deptOpdId);

String Departmentopdid(String clientid, String deptid);

String getAppointmentid(String deptOpdid, String deptid);
ArrayList<Priscription> getPrintPriscListWIthDate(String parentID,LoginInfo loginInfo,String fromdate,String todate, String clientId);

String getBamslastmodifiedDate(int saveparent);

ArrayList<NotAvailableSlot> getipdsittingfollowuplist(String clientid);

String sessionname(String string);

String getdeptOpdId(String clientId);

ArrayList<Priscription> getPriscriptionListbyclientId(String datetime, String clientId);

String getMedicineDoseHindi(String dosage);

int saveAfitechClientDocument(DiaryManagement diaryManagement, String ukCurrentDataTime, String userId);
}
