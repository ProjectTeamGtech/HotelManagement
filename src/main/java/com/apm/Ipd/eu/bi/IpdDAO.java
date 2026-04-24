package com.apm.Ipd.eu.bi;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Discharge;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Master.eu.entity.Master;

import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Mrd.eu.entity.Mrd;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface IpdDAO {

	int changeMrdStatus(String id);

	int updatemrdetails(com.apm.Mrd.eu.entity.Mrd mrd);

	ArrayList<Mrd> getmrddetails(String fromdate, String todate, String searchText, String wardid);

	ArrayList<Bed> getAllBedList(String wardid, int clinicid, LoginInfo loginInfo, String filter_status, String action,
			String activefilter, String isfromandroid, String androidpractid, String excessamtbt);

	ArrayList<Master> getFilteredChargeList(String chargetype, String tpid, String wardid, String clientid,
			boolean show_wardname);

	ArrayList<Master> getFilteredOpdChargeList(String chargetype, String tpid, String payby);

	ArrayList<Bed> getWardWiseBedList(String wardid);

	ArrayList<Bed> geteditWardWiseBedList(String wardid, String bedid);

	boolean checkBedStatus(int id);

	public String getIpdWardName(String wardid);

	String getIpdBedName(String bedid);

	String getTreatmentEpisodeDischargeStatus(String tepisodeid);

	ArrayList<Bed> getBookedBedList();

	ArrayList<Priscription> getParentPriscList(String addmissionid);

	ArrayList<Master> getParentNursingList(String admissionid);

	ArrayList<Priscription> getClientPriscList(String parentid);

	ArrayList<Master> getClientNursingList(String parentid);

	boolean checkPrescExist(long mdicinedays, int id);

	boolean checkNursingExist(long mdicinedays, int id);

	int savePrescReminder(String dosecolumn, String doseqmark, long days, int priscid, String ipdid);

	int saveNursingReminder(String freqcolumn, String freqqmark, long days, int nursingid, String ipdid);

	int updatePriscReminder(String selectedid, String colname, boolean savetogle, String userid);

	int updateNursingReminder(String selectedid, String colname, boolean savetogle, String userid);

	ArrayList<Bed> getTreatmentSheetList(String clientid, String[] calldays);

	int updateorInsert(String id, String colname, String doseval, String datetime, String priscid);

	String getPriscId(String id);

	ArrayList<Bed> getTreetmentSheetInDays(String clientid, String day1, String day2, String day3);

	ArrayList<Bed> getAllDosageRemainders(String date, String nextday, LoginInfo loginInfo);

	ArrayList<Bed> getAllPricsandDoseTodayOfPatient(String wardid, LoginInfo loginInfo);

	ArrayList<Priscription> getDischargePrescList(String selectedid);

	Bed getDischargeData(String treatmentepisodeid);

	String getDischargedIpdid(String clientid);

	int getValueToUpdate(String column, String ipdtreatmentepisodeid);

	int updateInitialDischargeStatus(String column, String column2, int valuetoupdate, String datetime,
			String ipdtreatmentepisodeid, String userid);

	Discharge getDischargeDetails(String treatmentepisodeid);

	String getIpdDischargeDate(String treatmentepisode);

	int updateDischaregeStatus(String treatmentepisode, String dischargedate);

	ArrayList<Bed> getAllIpdBedList(String wardid, String searchtext, String fromdate, String todate, String status1,
			Pagination pagination, LoginInfo loginInfo, String maintypestatus,String patient_type);

	boolean checkInventoryChargeType(String chargetype);

	int getSumOfProdQty(String prodid);

	ArrayList<Bed> getVisitingConsultList(String ipdid, String searchtext, String fromdate, String todate,
			String searchdrname, Pagination pagination);

	public int getTotalVisitingConsultList(String ipdid, String searchText, String fromdate, String todate,
			String searchdrname);

	int addVisitingConsult(Bed bed);

	ArrayList<Bed> getVisitingConsult(String clientid, String ipdid);

	int updatePractitionerVisitedorNot(String id, String status);

	Bed getIpdVisitingConsult(String visitid);

	int updateVisitingPaymentStatus(String status, String visitid);

	int updateVisitingConsult(Bed bed);

	int deleteIpdVisitConsult(String visitid);

	ArrayList<Bed> getAllActiveIpdPatients();

	public ArrayList<Master> getAJaxNursing(String ipdid);

	String getIpdId(String clientid);

	ArrayList<Priscription> getAJaxPriscription(String ipdid);

	ArrayList<Bed> getAllNursingDoseTodayofPatient(String wardid, LoginInfo loginInfo);

	int updateorInsertNursingToggle(String id, String colname, String doseval, String datetime, String nursingid);

	public String getNursingId(String id);

	String getParentPriscId(String selectedid);

	String getDIscPrisc(String selectedid);

	int savefinalConditionDischarge(String sessionadmissionid, String treatmentEpisodeid, String datetime,
			String conditionname);

	Bed getAllFinalCondition(String ipdid, String tpid);

	String getNumofAdmissionCount(String clientid);

	ArrayList<Bed> getBedLogClientList(String clientid, String admissionid);

	public String getwardIdfromName(String wardname);

	int getLastIpdId(String clientid);

	ArrayList<Master> getOtFilteredChargeList(String chargetype, String clientId);

	public boolean checkifMainTp(String tpid);

	public String getFollowerTp(String tpid);

	String[] getFromIpdOrOpd(String clientId);

	ArrayList<Bed> getAllBedListForDietary(String string, int clinicid, String date1);

	ArrayList<String> getAllSecondaryConsultList(String selectedid);

	Priscription getMNameFromId(String string);

	ArrayList<Master> getPackageList();

	ArrayList<Accounts> getPackageDetailList(String id);

	Master getParentPackageData(String id);

	ArrayList<Master> getPatientPackageList(String admissionid, String ipdclientid);

	int saveparentpackagedata(String fromdate, String todate, PackageMaster master, String clientid, String ipdid,
			String amt);

	int savechildpackagedata(PackageMaster packageMaster, int result);

	int updateDischargeBed(String bedid, String ipdid);

	int updateDischargeDateandStatus(String treatmentepisodeid, String admittnotes);

	int updateDischargeBedandWard(String bedid, String wardid, String ipdid);

	int updateInProcessCharges(String sessionadmissionid);

	ArrayList<Bed> getAllIpdBedList1(String wardid, String searchtext, String fromdate, String todate, String status);

	int getAllTotalBed(String wardid, int clinicid, LoginInfo loginInfo);

	String getClientIDFromIPDID(String ipdid);

	ArrayList<Master> getVitalMasterList();

	ArrayList<Master> getVitalMasterByCategory(String vital_type);

	int saveClientVitalData(Master pmaster, String value, String time);

	boolean isVitalDateIsExist(String clientid, String ipdid, String date);

	int getVitalClientDataId(Master pmaster, String time);

	int updateClientVitalData(String value, int id, String dateTime);

	ArrayList<Master> getVitalMasterandValuesList(String ipdclientid, String ipdid, String date);

	public String getFindingofVital(String ipdclientid, String ipdid, String vital_master_id, String time, String date);

	public ArrayList<String> getVitalTimeList();

	ArrayList<Master> getVitalDataList(String ipdid, String clientid, String date);

	ArrayList<String> getVitalClientTimeList(String ipdid, String ipdclientid, String date);

	ArrayList<String> getDailyCareTimeList(String ipdid, String ipdclietid, String date, String type);

	ArrayList<Priscription> getTreatmentSheetPriscription(String ipdid, String clientid, String commencing);

	ArrayList<Master> getTreatmentSheetNursing(String ipdid, String clientid, String date);

	int saveDischargeCheckList(int tretmentEpisodeId, String clientid);

	ArrayList<Bed> getDischargeChecklistData(String treatmentepisodeid, String clientid, String disstepid);

	int updateCheckListStatus(String string, String string2, String treatmentepisodeid, String userid, String todate);

	String getClientVitalData(String vitalname, String clientid);

	public ArrayList<Bed> getClientVisitingConsultList(String ipdid, String date);

	int updateCheckListStatusById(String disdataid, String userid, String todate, String status);

	boolean isDisCheckListAlreadyPresent(String disdataid);

	int updateCheckListStatusModicfy(String disdataid, String userid, String todate, String string, String ische);

	boolean getIsDisCheckListStatus(String string, String treatmentepisodeid);

	int updateCheckListStatusSystemModify(String disstepid, String dischecklistid, String treatmentepisodeid,
			String userid, String todate, String ische);

	int getDisDataId(String string, String treatmentepisodeid);

	int updateDischargeCKLIndi(int disdataid1);

	public String getAlterNateDose(String dosage);

	int getTotalDischargePatientCount(String fromdate, String todate);

	String getBedIdFromLog(String selectedid);

	ArrayList<Master> getDailyCareDataListandValues(String clientid, String ipdid, String date, String type);

	boolean checkCheckListAvailable(String treatmentepisodeid);

	boolean checkHasInvoiceCreated(String ipdid, String clientid);

	int cancelInvoice(String ipdid, String clientid);

	int cancelBedStatus(String ipdid, String cancelNotes, String userid);

	ArrayList<Client> getAllDiagnosis(String search);

	int savenursenoteajax(String nursemsg, String ipdclientid, String ipdaddmissionid, String userid, String todate);

	ArrayList<Bed> getAllNurseNotes(String ipdaddmissionid);

	ArrayList<Master> getAdditionalFilteredChargeList(String chargetype, String ipdtpid, String wardid,
			String clientId);

	int saveIPDTemplate(String tempname, String ipdtempid, String department, String msg);

	int updateAdmDataFromDisc(Bed bed3);

	ArrayList<Master> getProcedureChargeList(String chargetype, String patientId, String otplaned);

	int removeMedicineDischarge(String id);

	int saveIpdGynicDetails(Bed bed);

	Bed getEditGynicFormData(String id);

	int updateIpdGynicDetails(Bed bed);

	int saveReasonGynicVisit(Bed bed);

	ArrayList<Bed> getGynicVisitReasonList(String clientid, String commencing);

	ArrayList<Bed> getGynicVisitReasonList(String visit_reason_ids);

	int deleteFinalConditions(String sessionadmissionid, String treatmentEpisodeid);

	int deleteConditionReport(String sessionadmissionid, String treatmentEpisodeid);

	int getTotalDischargePatientCount(String wardid, String searchtext, String fromdate, String todate, String status1,
			String maintypestatus,String patient_type);

	ArrayList<Bed> getAllIpdDischargeBedList(String searchtext, String fromdate, String todate, String status1,
			Pagination pagination, LoginInfo loginInfo, String patient_type);

	int getTotalDischargeCount(String searchtext, String fromdate, String todate, String status, String patient_type);

	int addnursingcareplan(Ipd ipd);

	ArrayList<Ipd> getallnursingdiagnosislist();

	ArrayList<Ipd> getplanninglist(String id);

	String diagnosisnoteslist(Ipd ipd);

	ArrayList<Ipd> getAllinterventionlist(String id);

	String getplanningnoteslist(Ipd ipd);

	String getinterventionnoteslist(Ipd ipd);

	ArrayList<Ipd> getallrationalelist();

	String getrationalecarenoteslist(Ipd ipd);

	Ipd printnursingcareplan(int result);

	ArrayList<Master> getAllOtNotes(String selectedid);

	ArrayList<String> getAllOTIds(String selectedid, String clientid);

	Boolean checkInhousePatientExists(String newdate);

	int saveInhousePatient(int total, String newdate, int totalbed1, String ipdids);

	Ipd getProcedureName(String selectedid);

	String getProcedureId(String procedurename);

	int updateotnotesid(String notesid);

	Boolean checkPatientAdmorDis(String date);

	Ipd getTotalAdmDisCount(String date, String today, String yesterday);

	int updatePatientAdmandDis(String date, int ipdnewadmission, int totaladmision, int dischargepatients,
			int totaldischarge);

	int savePatientAdmandDis(String date, int ipdnewadmission, int totaladmision, int dischargepatients,
			int totaldischarge);

	ArrayList<Master> getOtDatesAndIds(String selectedid, String clientid);

	UserProfile getVisitedDrInfor(String id);

	int updateVisitingPaymentStatus(String status, String visitid, String userid, String date, String total);

	String getVistingDrPer(String doctor);

	ArrayList<UserProfile> getSecConsWithDepartment(String selectedid);

	boolean checkStandardChargeExist(int parseInt);

	String getAllActiveStdCharges(int parseInt);

	ArrayList<Bed> geteditBedListAfterDischarge(String bedid);

	int updateDischrgeOTProcedure(String editotprocedure, String id);

	int updateDischrgePriscSrNo(String dicpriscmedsrno, String id, String dicpriscdose, String dicpriscdays);

	ArrayList<Master> getOtDatesAndIdsFromdischarge(String selectedid);

	int updatePriscChildFromIpd(String prischildid, String date, String userid);

	int insertTempIPDData(String ipdid, String clientid, String addmissionfor, String alergies, String packagename,
			String chiefcomplains, String presentillness, String onexamination, String pasthistory, String familyhist,
			String personalhist, String surgicalnotes, String suggestedtrtment, String earlierinvest, String diethist,
			String birthhist, String developmenthist, String emmunizationhist);

	boolean checkOfTempData(String ipdid, String clientid, String column);

	int updateTempIPDData(String ipdid, String clientid, String addmissionfor, String alergies, String packagename,
			String chiefcomplains, String presentillness, String onexamination, String pasthistory, String familyhist,
			String personalhist, String surgicalnotes, String suggestedtrtment, String earlierinvest, String diethist,
			String birthhist, String developmenthist, String emmunizationhist);

	Bed getAllIPDData(String ipdid, String clientid);

	ArrayList<Ipd> getallNursingplans(String ipdid, String date);

	int updateprisc(String id);

	int updateinvst(String id);

	int updateTempIPDDischargeData(String ipdid, String clientid, String history, String surgicalnotes,
			String hospitalcourse, String treatmentgiven, String investigation, String findondis,
			String dischargeadvice, String diethist, String birthhist, String developmenthist, String emmunizationhist);

	int insertTempIPDDischargeData(String ipdid, String clientid, String history, String surgicalnotes,
			String hospitalcourse, String treatmentgiven, String investigation, String findondis,
			String dischargeadvice, String diethist, String birthhist, String developmenthist, String emmunizationhist);

	Bed getAllIPDDischargeData(String ipdid, String clientid);

	String getIPDAdmissionDate(String ipdid);

	ArrayList<Ipd> getAllRMONotes(String ipdid, long day);

	Ipd getRmoNoteDay(String ipdid, long day);

	int updateRMONotes(Ipd ipd);

	int saveRMONotes(Ipd ipd);

	ArrayList<Ipd> getRMONotesList(String selectedid);

	int updateRMONotesDisplayed(String rmonotesid);

	boolean checkifSequenceExist(String y);

	int getSqeunceNumber(String cdate);

	int InserCdateSeq(String cdate, int seqno);

	ArrayList<Priscription> getAdmissionPrescList(String sessionadmissionid);

	int getipdidbyfinalDiagnosis(String finalcond);

	int getsecondLastIpdid(String clientid);

	int settoShiftedFromIpd(int ipdid, String lastopdno);

	int setfinalcond(String ipdid, String tpid, String finaldia);

	boolean checkWardWiseDataStored(String newdate);

	boolean checkPractWiseDataStored(String newdate);

	ArrayList<String> getWardListFromIpdids(String ipdids);

	int saveInhousePatientWardWise(int total, String newdate, int totalbed1, String ipdwardwiseids, String wardid);

	ArrayList<String> getPractListFromIpdids(String ipdids);

	int saveInhousePatientPractWise(int total, String newdate, String ipdwardwiseids);

	String getIpdidByClient(String ipdid);

	String getPractidbyipd(String ipdid);

	String getipdseqno(String ipdid);

	ArrayList<Ipd> getAllIpdList(String clientId);

	int gettreatmentstatus(String treatmentepisodeid);

	String generateIPDSequenceNewFormat(String action);

	String getIpdAbrivationIds(int id);

	String getIpdAbrivationIdsByClientid(int clientid);

	int updateAdmissionDate(int ipdid, String date);

	int getMaxIPDAbrivation(String date, String action);

	int insertIPDAbrivation(String date, int seqno, String action);

	String getclinicAbrivationCode();

	public ArrayList<PackageMaster> getappliedpakgeList(String parentid);

	Master getParentPackageAppliedData(String id);

	int updatePatientpkg(int id, String amount, String name);

	int updateParentPkgAmt(String id, String amt, String fromdate, String todate);

	String getDischargeChecklistDataText(String treamentepisodeid, String clientid, String type, Discharge discharge);

	int updatePkgInAssessment(String fromdate, String todate, String ipdid, String pkgid, String mastercharege);

	String getMasterchargeAss(String fromdate, String todate, String ipdid, String parentid);

	ArrayList<Master> getPatientPackageListByClientid(String clientId);

	int saveIpdTemplates(String id, String text, String name);

	String getClientIdByIpdSeqno(String ipdseqno);

	Bed getIpdDetails(String ipdid);

	String getLAstIpdIdByClient(String client);

	int updateDischargeBedReadmit(String selectedbedid, String wardid, String ipdid);

	void rateChangeFlagWard(String ipdid, String flag);

	void rateChangeWardFromMaster(String wardid, String bedid);

	String getWardidofPat(String ipdid);

	int saveInvestigationOfDischargeForm(Bed bed);

	int saveEmergencyDetailsOfDischargeForm(Bed bed);

	int saveOTHERDivDataOfDischargeForm(Bed bed);

	int saveTearmentGivenDivDataOfDischargeForm(Bed bed);

	int saveHospitalCourseDivDataOfDischargeForm(Bed bed);

	int saveHistoryDivDataOfDischargeForm(Bed bed);
	
	int saveAdmissionDataOfDischForm(Bed bed);
	
	int saveSurgicalNotesOfDiv(Bed bed);

	boolean checkAlreadyAdmit(String clientid);
	
	ArrayList<Bed> getAllIpdidsOfDiagnosis(String diagnosis);
	
	boolean checkIfJsonNewDischargeFormStatusExist(String ipdid);
	
	int insereJsonNewDischargeFormStatus(String ipdid);
	
	int updateJsonNewDischargeFormStatus(String ipdid, String column, String status);
	
	int chkStatusOfJsonNewDischargeForm(String column, String ipdid);
	
	ArrayList<Bed> getAllIpdOfClient(String clientid);

	ArrayList<Bed> getIPDfinalDiagnosisList(String fromdate, String todate, String diagnose,String filter_sortby);

	ArrayList<Bed> getOPDfinalDiagnosisList(String fromdate, String todate, String diagnose,String filter_sortby, LoginInfo loginInfo);
	int saveDeathNotesDivData(Bed bed);
	
	ArrayList<String> getAllWardIdsOFPerson(String ipdid);
	
	void startOnOffChargesWardChangingProcess(String ipdid, String currentWardid,LoginInfo loginInfo);
	ArrayList<Priscription> getTreatmentGivenDischargePrescList(String selectedid);
	
	Accounts getOnOffDataChild(String parentId);
	
	void inserDischargeCardFields();
	ArrayList<Master> commonTemplateList();
	int saveCommonTeplate(Master master);
	
	void startInvestigationChargeCancelProcess(Client client, String investId,LoginInfo loginInfo);

	ArrayList<Bed> getActiveIpdPatientList();

	String getActiveIpdIdFromClientId(String clientid);

	int cancelIpdVisitConsult(String visitid, String userId, String date);

	int updateWardBedInAdmission(int id, String wardid, String bedid);

	int getTotalBookedBedCount(String wardid, int clinicid, LoginInfo loginInfo, String filter_status, String action,
			String activefilter, String isfromandroid, String androidpractid, String excessamtbt);

	int savenewIpdGynicDetails(Bed bed);

	Bed getEditNewGynicFormData(String id);

	int updatenewIpdGynicDetails(Bed bed);

	int getLastGynicformId(String clientid);

	ArrayList<Priscription> getGynicPrescList(String sessionadmissionid);

	ArrayList<String> getPatientNameDataList(String val, String colmnname);

	boolean checkAdmissionView();

	int createAdmissionView();

	boolean checkDischargeView();

	int createDischargeView();

	int updateUserLanaguage(String language, String userId);

	String getConditionidofipdid(String ipdid);
	
	boolean isPatientDeadOrLAMADAMAForBGH(String clientId);
	
	void clearIpdDetails(String ipdid);
	void saveIpdInvoiceDataClearLog(LoginInfo loginInfo, Accounts accounts);

	int updateAdm_Tp(String selectedid, String typeName);

	int updateAdmNew_Tp(String selectedid, String typeName);

	ArrayList<Priscription> getDischargeParentPriscIds(String selectedid);

	ArrayList<Priscription> getDichargePriscriptionList(String ids, String apmtId);

	String getAdmissionParentPriscIds(String selectedid);

	ArrayList<Priscription> getAdmissionPriscList(String ids);

	ArrayList<Bed> getIpdReportKalmegh(String fromdate, String todate,String doctorid);

	Bed getConsultationNotesDiagnosis(String apmtId);

	int deleteFinalConditionsCF(String apmtId);

	int savefinalConditionCF(String apmtId, String allconditions, String chief_complains, String history, String investigation, String notes, String observation, String treatment);

	int getConsulationFormId(String apmtId);

	int updateConsultationForm(Bed bed, int consulationFormId);

	int insertConsultationForm(Bed bed);

	Bed getConsultationFormDetails(String apmtId, String id2);

	int getIpdTemplateId(String string);

	ArrayList<Priscription> getConsultFormPrescList(String apmtId);

	ArrayList<Priscription> getCFParentPriscIds(String apmtId);

	ArrayList<Bed> getConsultantNoteHistory(String clientid);

	String getDateTime(String clientId, String apmtId);

	int getTotalIPDcount(String fromdate, String todate, String doctorid);

	int getupdateDischrgeDate(String clientid, String date);

	Bed editdischargeDate(String selectedid);

	ArrayList<Bed> getIpdConsultFake(String fromdate, String todate, String doctorid, String userid, String searchtext, String department);

	int getipdFakeconsultcount(String fromdate, String todate, String doctorid);

	String getfakedatebyClientid(String fakclientid);

	String getfakeDischrgedate(String fakclientid);

	String getdepmnDate(String deptid);

	Bed getFinalDiagnosis(int au_ipdid, String au_treatmentid, String dbname);

	ArrayList<Bed> ipdDgetIpdgenderList(String fromdate, String todate);

	ArrayList<Bed> getpatientRequestNote(String ipdaddmissionid);

	int deletenote(String selectedid);

	String getRequestnotebyid(String id);

	int updateisSeen(String id);

	ArrayList<Ipd> getAllpatienttransactionList(String fromdate, String todate, String searchtext, String paymentid);

	String getprocedurebyid(String id);

	Ipd getDietData(int id, String opdid, int treatmentstatus);

	int getTreatmentId(int id);

	int gettreatmentStatus(String clientid);

	String getInvestigationNameFindings(String selectedid, String string, String string2);

	ArrayList<Bed> getInvTypelist(String clientid, String admissiondate, String dischargedate);

	ArrayList<Accounts> getdailynoteslist(String ipdid, String clientId, String admissiondate, String dischargedate);

	Bed getDailyipdnote(String id);

	ArrayList<Bed> getConsultationFormDetailslistbydate(String apmtid, String clientId, String datetime);

	int updateAdmissionid(String admissionid, String ipdseqno);

	String getnewAdmissionid(int ipdid);

	String getDiagnosisids(String apmtId);

	String getDiagnosisText(String ids);

	int getwardId(int ipdid, String clientId);

	ArrayList<Bed> getDailyNotelist(String apmtId, String id, String datetime);

	ArrayList<Master> getAllChiefComplains(String search, String id);

	String getChiefComplainsbyid(String chief_complains);

	Master getChiefComplainsIds(String apmtId);

	String getIpdChiefComplainname(String str);

	ArrayList<Master> getAllHistoryList(String search, String id);

	Master getHistoryIds(String apmtId);

	ArrayList<Master> getAllInvestigation(String search);

	String getInvestigationNameByid(String investigation);

	Master getInvestigationIds(String apmtId);

	String getInvestigationNames(String str);

	ArrayList<Master> getAllNotesList(String search, String id);

	String getNotestext(String notes);

	Master getNotesIds(String apmtId);

	String setObservText(String observation);

	Master getObservationIds(String apmtId);

	ArrayList<Master> getAllChargesList(String search);

	Master getTreatmentIds(String apmtId);

	String setTreatmentName(String treatment);

	Master getTreatmentPlan(String str);

	
}
