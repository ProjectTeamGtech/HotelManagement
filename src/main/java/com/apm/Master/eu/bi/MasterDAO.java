package com.apm.Master.eu.bi;

import java.sql.Connection;
import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Master.eu.entity.Book;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface MasterDAO {
	

ArrayList<Master> getTermsConditionList(Pagination pagination,String searchText);
	int getTotalTermsCondition();
	Master getTermsConditionListById(Master masterOBJ);
	int addTermsCondition(Master msterOBJ);
	int updateTermsConditionlist(Master masterOBJ);
	int deleteTermsCondition(Master masterOBJ);

	ArrayList<Master> getOccupationList(Pagination pagination, String searchText);

	int getTotalOccupationCount(String searchText);

	int saveOccupation(Master master);

	Master getOccupation(int id, Master master);

	int updateOccupation(Master master, int id);

	int deleteOccupation(int id, Master master);

	int getTotalJobTitleCount();

	ArrayList<Master> getJobTitleList(Pagination pagination);

	int saveJobTitle(Master master);

	Master getJobTitle(int id, Master master);

	int updateJobTitle(Master master, int id);

	int deleteJobTitle(int id, Master master);

	int getTotalReferenceCount();

	ArrayList<Master> getReferenceList(Pagination pagination);

	int saveReference(Master master);

	Master getReference(int id, Master master);

	int updateReference(Master master, int id);

	int deleteReference(int id, Master master);

	int getTotalDisciplineCount(String searchText);

	ArrayList<Master> getDisciplineList(Pagination pagination, String searchText);

	int saveDiscipline(Master master);

	Master getDisciplineData(String selectedid);

	int updateDiscipline(Master master);

	int deleteDiscipline(String selectedid);

	ArrayList<Master> getDisciplineDataList();

	ArrayList<Master> getPractitonerTypeList();

	ArrayList<Master> getNotAvaiableReasonList(Pagination pagination);

	int addNotAvailableReason(Master master);

	Master getNotAvailableReason(int id, Master master);

	int updateNotAvilableReason(Master master);
    int deleteNotAvilableReason(int id);
    public ArrayList<Master> getMasterList(LoginInfo loginInfo);

	ArrayList<Master> getCountryCodeList();

	ArrayList<Master> getNewChargeTypeList(String searchText, Pagination pagination);
	
	 ArrayList<Master>getchargetypelist(String sid);

	int  addNewChargeType(Master master);

	Master getNewChargeType(int id, Master master);

	int  updateNewChargetype(Master master);

	int  deleteNewChargeType(int id);
	
	public ArrayList<Master> getAllOtherTemplateList(String searchText, Pagination pagination);
	public int addOtherTemplate(Master master);
	
	public  Master getOtherTemplate(String selectedid);
	public int updateOtherTemplate(Master master);
	public int deleteOtherTemplate(String selectedid);

	ArrayList<Master> getEmrTemplateList(String disciplineid);

	ArrayList<Master> getMedicineList();

	ArrayList<Master> getWardList();

	public int getTotalExpenceCategoryCount();
	public ArrayList<Master> getExpenceCategoryList(Pagination pagination);
	public int addExpenceCategory(Master master);

	Master getExpenceCategory(String selectedid);

	int updateExpenceCategory(Master master);
	int deleteExpenceCategory(String selectedid);

	ArrayList<Master> getDosageNoteList();

	int getTotalStandardChargesCount();

	ArrayList<Master> getStandardChargesList(Pagination pagination);

	int addStandardCharge(Master master);

	Master getStandardCharge(String id);

	int updateStandardCharge(Master master);

	int deleteStandardCharge(String id);

	ArrayList<Master> getAllNursingCategory(Pagination pagination);

	int getNursingCategoryCount();

	int addNursingCategory(Master master);

	Master getNursingCategory(String selectedid);

	int updateNursingCategory(Master master);

	int deleteNursingCategory(String id);

	ArrayList<Master> getAllNursingDetails(Pagination pagination, String searchText);

	int getTotalNursingDetailsCount(String searchText);

	int addNursingDetails(Master master);

	Master getNursingDetails(String selectedid);

	int deleteNursingDetails(String selectedid);

	int updateNursingDetails(Master master);

	ArrayList<Master> getJobgroupList();
	String getJobTitleGroup(String jobgroup_id);

	int deleteGodown(String id);

	int updateGowdown(Master master);

	Master getGodown(String id);

	int addGodown(Master master);

	int getAllGodownCount();

	ArrayList<Master> getAllGodownlist(Pagination pagination);

	int getSMSCount();

	ArrayList<Master> getIpdTemplateList(Pagination pagination);

	int getIpdTemplateCount();

	ArrayList<Master> getIpdTemplateNameList();

	public int updateSMSCounter(int smscounter,String sms);
	int saveIpdTemplate(Master master);
	
	String getIpdTemplatenamefromId(String id);
	String getIpdTemplateId(String templateName);

	ArrayList<Master> getIpdTemplateListNames(String templateNameId);

	Master getIpdTemplate(String id);

	int updateIpdTemplate(Master master);

	int deleteIpdTemplate(String id);

	String getSharedChargeUser(String chargeType);

	int saveShareCharge(String str, String chargeType);

	int getShareIdIfExists(String chargeType);

	int updateShareCharge(String userids, String chargeType,int id);

	ArrayList<Master> getMedicineLocationList();

	ArrayList<Master> getIpdFormFiledList();
	public String getIpdFormFieldName(String id);

	boolean isIpdFormFieldActive(String sepcializationid, String field);
	public ArrayList<Master> getSpecializationList();

	ArrayList<Master> getAllIpdFormSettingList();
	public int saveIpdFormData(String speci, String f);

	ArrayList<String> getIpdFormSettingFields(String dept_id);

	int deletePreviousDeptFields(String specialization);

	ArrayList<Master> getAllLocation(String searchText, Pagination pagination);

	int addLocation(Master locationMaster);

	Master getlocationinfo(Master master);

	int updateLocation(Master master);

	int deletelocation(Master master);

	ArrayList<Master> getAllproductypelist(String searchText, Pagination pagination);

	int addproductypelist(Master master);

	Master getproducttypemasterinfo(Master master);

	int updateproducttypemaster(Master master);

	int deleteproducttypemaster(Master master);

	ArrayList<Master> getIpdTemplateList(Pagination pagination,
			String searchText);

	ArrayList<Master> getStandardChargesList(Pagination pagination,
			String searchText);

	int getTotalLocationCount();

	int getTotalapmEventsCount();

	int getOtherTemplateCount();

	int getProductTypeMasterCount();

	ArrayList<Master> getAllLocation(String searchText);

	int getTotalNewchargeCount();
	
	String getCombineFormDetails(String assementtemplateId,String combineformclientid,
			String combineformdoctorid,String combineformconditionid,int fieldid,String columname);
	ArrayList<Master> getNursingDetailsByNursingTypeid(String id);
	ArrayList<Master> getJobTitleList();
	ArrayList<Master> getInvestigationNameList();
	ArrayList<Master> getAllVitalList(Pagination pagination);
	int getCountVitalMasterList();
	ArrayList<Master> getAllVitalTypeList();
	int saveVitalMaster(Master master);
	int updateVitalImageName(int res, String filename);
	Master getVitalMasterData(String id);
	int updateVitalMaster(Master master);
	int deleteVitalMaster(String id);
	ArrayList<Master> getNABHCatagoryList(String searchText, Pagination pagination);
	int getTotalNABHCatagoryCount(String searchText);
	int addNABHCatagory(String name);
	int deleteNABHCatagory(String id);
	Master getNABHCatagory(String id);
	int updateNABHCatagory(String id, String name);
	ArrayList<Master> getNABHSubCatagoryList(String searchtext, Pagination pagination);
	ArrayList<Master> getNabhSubCatList(String id);
	int getTotalNABHSubCatagoryCount(String searchText);
	int addNABHSubCatagory(String name, String catagoryid);
	Master getNABHSubCatagory(String id);
	int updateNABHSubCatagory(int id, String name, String catagoryid);
	int deleteNABHSubCatagory(String id);
	int getTotalNABHAreaCount(String searchText);
	ArrayList<Master> getNABHAreaList(String searchText, Pagination pagination);
	int addNABHArea(String name, String subcatagoryid);
	Master getNABHArea(String id);
	int updateNABHArea(int id, String name, String subcatagoryid);
	int deleteNABHArea(String id);
	int getPriseTemplateCount(String searchText);
	ArrayList<Master> getPriscTemplateList(String searchText, Pagination pagination);
	ArrayList<Master> getMasterSpecializationList();
	String getApmtidFromClientid(String clientid);
	int getTotalSchedulerTaskCount();
	ArrayList<Master> getAllSchedulerTask(String searchText,Pagination pagination);
	int addSchedulerTask(Master master);
	Master getSchedulerTaskinfo(Master master);
	int deleteSchedulerTask(Master master);
	int updateSchedulerTask(Master master);
	int getTotalSchedulerSubtaskCount();
	public ArrayList<Master> getAllSchedulerSubtask(String searchText,Pagination pagination);
	int addSchedulerSubtask(Master master);
	Master getSchedulerSubtaskinfo(Master master);
	int updateSchedulerSubtask(Master master);
	int deleteSchedulerSubtask(Master master);
	ArrayList<Master> getSubTasklist(String id);
	String getNotesList(Master master);
	ArrayList<Master> getIpdTemplateBySpeciality(String surgical_template, String discipline);
	String getMasterChargeType(String chargetypeid);
	public ArrayList<Master> getPriscUnitList();
	int getTotalNursingDiagnosisCount();
	ArrayList<Master> getAllNursingDiagnosis(String searchText, Pagination pagination);
	 int addNursingDiagnosis(Master master);
	 Master getNursingDiagnosis(Master master);
	 int updateNursingDiagnosis(Master master);
	 int deleteNursingDiagnosis(Master master);
	 int getTotalNursingPlanningCount();
	 ArrayList<Master> getAllNursingPlanning(String searchText, Pagination pagination);
	 int addNursingPlanning(Master master);
	 int deleteNursingPlanning(Master master);
	 int getTotalNursingInterventionCount();
	 ArrayList<Master> getAllNursingIntervention(String searchText, Pagination pagination);
	 int addNursingIntervention(Master master);
	ArrayList<Master> getBankNameList();
	
public ArrayList<Master> getallBookChapter(String searchText,Pagination pagination);
	
	public int getBookChapterCount();
	public int updateBookChapterDB(Master master);
	public Master getBookChapterinfo(Master master);
	public int deleteBookChapterDB(Master master);
	public int addbookChapterDB(Master master);
	
	public Master getOutsourceinfo(Master master);
	public int deleteOutsourceDB(Master master);
	public int updateOutsourceDB(Master master);
	public int addOutsourceDB(Master master);
	public int getOutsourceCount();
	public ArrayList<Master> getallOutsource(String searchText,Pagination pagination);
	
	public Master getOutsourceDatainfo(Master master);
	public int deleteOutsourceDataDB(Master master);
	public int updateOutsourceDataDB(Master master);
	public int addOutsourceDataDB(Master master);
	public int getOutsourceDataCount();
	public ArrayList<Master> getallOutsourceData(String searchText,Pagination pagination);
	public ArrayList<Master> getallInvestigationtype();
	ArrayList<Master> getAllCityList();	
	int deleteMedicineTemplateDB(String id);
	int saveFeedBack(String name, String isopd, String isipd);
	int deleteFeedBack(String id);
	int updateFeedBack(String name, String isopd, String isipd, String id);
	Master getFeedBackInfo(String id);
	int deleteNursingcareIntervationMaster(String id);
	Master getNusringCareMasterInfo(String id);
	int updateNursingcareIntervationMaster(String id,String nursing_planning,String nursingIntervention);
	
	
	//for group by names
	ArrayList<Master> getInvestigationNameList1();
	ArrayList<Master> getAllSharedChargeList();
	int checkIssharedchargewithuser(String sharechargeid, String shareuserid);
	
	
	//lokesh 1/8/18
		int savevaccinationmaster(String vacinname,String vacine_dependson,String vacine_iscompulsary,String vacine_excludes,String vacine_parent,String vacine_info,String scheduled,String duration,int type, String scheduledependency,String genderspecified);
		int updatevaccinationmaster(String vacinname,String vacine_dependson,String vacine_iscompulsary,String vacine_excludes,String vacine_parent,String vacine_info,String id,String scheduled,String duration,int type,String scheduledependency,String genderspecified);
		//int savevaccinationmaster(String vacinname,String vacine_dependson,String vacine_iscompulsary,String vacine_excludes,String vacine_parent,String vacine_info);
		int deletevaccinationmaster(String id);
		ArrayList<Master> getallvaccinations();
		ArrayList<Master> getallparentvaccinations();
		Master getvaccinationMasterInfo(String id);
		int updatetotalvacindays(Master master, Master master2);
		
		int saveclinicalnotesmstr(String name);
		int saveclinicalproblemmster(String name,String parentid);
		int saveclinicalintervation(String name,String parentid);
		
		ArrayList<Master> getclinicalnoteslist();
		ArrayList<Master> getclinicalproblemlist();
		ArrayList<Master> getclinicalintervationlist();
		
		int deleteclinicalmster(String id);
		int deleteclinicalproblem(String id);
		int deleteclinicalintervation(String id);
		
		Master getclinicalnotesInfo(String id);
		Master getclinicalproblemInfo(String id);
		Master getclinicalintervationInfo(String id);
		
		int updateclinicalNote(Master master);
		int updateclinicalProblem(Master master);
		int updateclinicalIntervation(Master master);
		Master getMedicineDetails(int result);
		ArrayList<Master> getAllLocationNew(String location);
		ArrayList<Priscription> getseachedpricofMaster(String searchtxt);
		ArrayList<Master> getnimaidoselistt();
		ArrayList<Master> getnimaiqtylist();
		ArrayList<Master> getnimairemarlist();
		int saveremarkMaster(Master master );
		ArrayList<Master> getallRemarks();
		int updateRemarkMaster(Master master);
		Master getMasterInfo(String id);
		boolean chkdoseExsist(String chdose);
		double getnextGreaterDose(String chdose);
		int setbreakage(String id, String checked);
		int setAllbreakage(String selecteditem);
		int setnoneditable(String id, String checked);
		ArrayList<Master> getMasterList(String tablename);
		Master getMasterInfo(String tablename, String id);
		int deleteMaster(String tablename, String id);
		int updateMaster(String tablename,Master master);
		int addToTaxMaster(Master master);
		String getTaxnamebyType(String type);
		boolean getTaxTypeExist(String type);
		ArrayList<AppointmentType> getseachedCharge(String searchtxt);
		void addToMfgMaster(Master master);
		ArrayList<Master> getMfgList();
		Master getMasterInfoMfg(String id);
		int updatemfgMaster(String tablename, Master master);
		int getmfgExist(String mfgname);
		int getTotalGenericNameCount(String searchText);
		ArrayList<Master> getAllGenericName(String searchText, Pagination pagination);
		int addGenericName(String name);
		int checkGenericName(String genericname);
		ArrayList<Master> listDosages();
		int addDosages(Master master);
		ArrayList<Master> getHISDocumentList();
		ArrayList<Master> listsms();
		ArrayList<Master> getsmstypeList(String id);
		ArrayList<Master> getsmsitypelist();
		int getsmstempExist(String id);
		int addsmstmp(Master master);
		Master getMasterSMSTemp(String id);
		int updatSMSMaster(Master master);
		String getsmstype(String id, String table, String column) ;
		int updateSMSMonthCount(String month, String year, int i);
		ArrayList<Master> listoutsourcerate();
		int addoutsourceratemaster(Master master1);
		Master getoutsourcerateList(String selectedid);
		int updateoutsourceratemaster(Master master1);
		ArrayList<Master> getallVitalMasterdata(String type);
		String getdischargeVitalVal(String masterid, String ipdid);
		ArrayList<UserProfile> userSignaturesList();
		void addUserSignature(UserProfile userProfile);
		void updateUserSignature(UserProfile userProfile);
		boolean userSignExist(String userid);
		UserProfile userSignature(String userId);
		ArrayList<Master>getBirthPlaceList();
		int addBirthPlcae(String name);
		int getbirthplaceExist(String birthplace_name);
		Master getMasterInfoBirthplace(String id);
		int updateBirthPlaceMaster(Master master);
		void saveChargeIdOfVaccine(int chargeid,String vaccineid);
		ArrayList<Master> getDisciplineDataListWithChecked();
		ArrayList<Master> getMasterChargeList();
		ArrayList<Master> getDepartmentWisePatientCount(String date, String nextDate, String deptid, LoginInfo loginInfo);
		ArrayList<Master> getDisciplineWithChecked(String dicipline);
		ArrayList<Master> getprocedurelist();
		ArrayList<Master> getIpdTemplateListNamesList(String discharge_default_id);
		ArrayList<Master> getchargetype();
		boolean checkTermsandCondtion(String string);
		ArrayList<Master> getallhospitallist(String pro_userid_id, String clinic_userid);
		String getCommericalHospital(String pro_userid, int refclinicid);
		ArrayList<Master> getDepartmenDataList(String selecteddept_id);
		ArrayList<Master> editDisciplineDataList(String selectedid);
		String getCommericalHospitalPer(String refclinicid);
		ArrayList<Master> getSpecializalist();
		int chklocationexist(String speciality);
		ArrayList<Master> getdepartment();
		String departmentid(String string);
		ArrayList<Master> getConsultantNotesTemplateList(String string);
		
		int updatewhatsappcount(int wsms, String message);
		int getWSMSCount();
		ArrayList<Master> getDepartmentWisePatientCount1(String fromDate, String toDate);
		ArrayList<Master> getPayrollTemplateList();
		ArrayList<Master> getAllpayrolldeclarationList();
		String getpayrollTitleOfDeclaration(int id);
		String getpayrollDeclaration(int id);
		Master getpayrollPrint(String empid);
		int insertSmsdata(LoginInfo loginInfo, String smsUrl, String date_time, String mobileNo, int status,
				String error);
		String getApikey();
		ArrayList<Master> getPhysioIpdhospitalList();
		ArrayList<Master> getallveterinarymedicine();
		void saveChargeIdOfMedicine(int invoice, String vaccineid);
		Master getProcedureTemplate(String id);
		ArrayList<Master> getReferDepartmentwisePatientCount(String currentDate, String toDate, String dicipline, LoginInfo loginInfo);
		String getDiscriptionidbyuserid(String userId);
		String getdeptnameByDeptId(String deptid);
		int getArvpatientcount(String deptid, String fromDate, String toDate, String string, String patinetcategory);
		int getDeptPatientCount(int i, int int1, String fromDate, String toDate);
		ArrayList<Master> getDepartmentWisePatientCount2(String fromDate, String toDate, LoginInfo loginInfo, String deptid);
		Master getUserdatabyid(String empid);
		int saveAppointmentdocs(String empid, String declarationNotes, String declarationTitle);
		Master getUserApmtdocsbyid(int id);
		String getEmpidbyloginid(int id);
		int addNewDoctor(Master master);
		ArrayList<Master> getAllCampLocation();
		int addCampLocation(Master master);
		NotAvailableSlot getAppointmentchargedetails(String chargetype);
		ArrayList<Diagnosis> getDiagnosisList();
		ArrayList<Master> getMedicineListbydiagnosis(String diagnosisid);
		int saveclientType(Master master);
		ArrayList<Master> getAllClientTypeList();
		ArrayList<Accounts> invoicelistfornkp(String date, String nextDate, String cliidss);
		ArrayList<Accounts> getChargePaymentlist(String inv1, String date, String nextDate);
		Master getChiefComplainsname(String s);
		int checkExixstChiefComplains(String chiefcomplains, int id);
		int addChiefComplainName(Master master);
		Master getHistoryname(String s);
		Master getInvestigationName(String s);
		int checkExixstinvestigation(String investigation_name);
		int addInvestigationName(Master master);
		Master getNotesName(String s);
		Master getChragesName(String s);
		int insertCharges(String charge, String chargename);


}

