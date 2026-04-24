package com.apm.DiaryManagement.eu.bi;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.GpData;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface ClientDAO {
	
	String  getcityfromid(String id);
	String  getstatefromid(String id);

	ArrayList<Client> getAllPatient(int id, String pro_userid);

	int savePatient(Client client, int i, LoginInfo loginInfo);

	Client getPatient(int id);

	int updatePatient(Client client, int id,LoginInfo loginInfo);

	int deleteClient(int id, Client client);

	ArrayList<Client> getThirdPartyType();

	ArrayList<Client> getThirdPartyTypeName();

	ArrayList<Client> getThirdTypeNameList(int id);

	int updateThirdPartyDetails(Client client, String id);

	ArrayList<Client> getOccupationList();
	ArrayList<Client> getOccupaList();

	ArrayList<Client> getReferenceList();

	int insertOtherOccupation(Client client, String otherOccupation);

	int insertOtherReference(Client client, String otherReference);

	ArrayList<String> getInitialList();

	ArrayList<Client> getClient(String searchClient,int id, String pro_userid);

	ArrayList<Client> getSourceOfIntroList();

	int getTotalClientCount(int id,boolean isShowAll,String diaryuser, String pstatus);

	ArrayList<Client> getAllPatient(Pagination pagination,int id,boolean isShowAll,String diaryUser, String pstatus, int days);

	int savePatientDetails(Client client,int id);

	ArrayList<Client> getReferList();

	ArrayList<Client> getInitailList();

	int updateThirdParty(Client client, String clientId, int id);

	String getThirdPartyCompanyName(String companyName);

	String getClientFullName(String id);

	int getTotalClientCountOfSearch(int id, String searchClient,String status,boolean isShowAll,String diaryuser, String pstatus, Pagination pagination);

	ArrayList<Client> getClientofSearch(Pagination pagination,
			String searchClient, int id,String status,boolean isShowAll,String diaryuser, String pstatus, String patientcategory, String clientType, String clientstatus, int days);

	int changeStatusToActive(String id);

	int changeStatusToInactive(String id);

	

	Client getClientDetails(String clientId);

	ArrayList<Client> getAllClientOfThirdParty(String id);

	String getDeclaration(int id);

	String getTitleOfDeclaration(int clientId);

	String getPractitionerName(String clientId);

	String getClinicName(int id);

	int saveNewPatient(Client client, int id);

	boolean isMobExist(String mob);

	boolean isEmailIdExist(String email);

	int deleteNote(String id);

	int updateNote(String id, String note);

	String getNote(String id);

	int saveNote(String id, String note);

	String getThirdPartyCompanyEmail(String id);

	int getTotalClientUnderPCount(String practionerId);

	ArrayList<Client> getAllPatientUnderP(Pagination pagination,
			String practionerId);

	ArrayList<Client> getGpList();

	Client getGPDeatils(String id);

	String getTPCompanyName(String typeName);

	ArrayList<Client> getTreatmentTypeList();

	String getSourceOfIntro(String sourceOfIntro);

	ArrayList<GpData> getGPDataList(String surgeryid);

	String getTpIdDetails(String gpname);

	int saveGPData(String popgptype, String gptypeNamepopup, String gpnameid,
			String workphno, String gpemailid, String gpfax, String gpNote);

	ArrayList<Client> getSurgeryList();

	String getWhoPayName(String clientId);

	int insertOtherSourceOfIntro(Client client, String otherSourceOfIntro);

	int insertOtherCondition(Client client, String otherCondition);

	String getClientEmail(String id);

	int getMaxIdOfRefernce();

	int getMaxIdOfOccupation();

	int getMaxIdOfSourceOfIntro();

	int getMaxIdOfCondition();

	int updateWhoPay(String patientId, String whopay);

	String getClientID(String userid);

	String getGPClientID(String userid);

	Client getSelectedSessionClientDetails(String clientid);

	NotAvailableSlot getLastAppointmentdetails(String clientId);

	int savePureSevaClient(LoginInfo loginInfo);

	boolean checkEmailidExist(String email);

	int getPureSevaClientid(String email);

	int saveCiimsClients(String title, String firstname, String lastname,
			String email, String mob, String dob, String gender,String address,String opdno);

	ArrayList<Master> getDeclerationTitleList();

	int updateAllDec();

	int updateDec(String id);

	String getSelectedDecId();

	String getIpdDischargeDate(String addmissionid);

	String getGPname(String gpid);

	int updateClientUserImage(String userImageFileName,int clientid);

	int updatePayeeofPatient(String clientid, String payee, String tpid);
	ArrayList<Client> getDepartmentTreatmentTypeList(String location);

	int updateEmergencyDetails(Client clientcont);

	String getReferenceName(String reference);

	int saveBMIPatient(Client client);

	Client getPatientBMIData(String clientid, int opdid);

	ArrayList<Client> getEmrTreatmentTypeList();
	public String getTpIDformPatient(String patientid);
	boolean checkifSequenceExist(String cdate);
	int getSqeunceNumber(String cdate);
	int InserCdateSeq(String cdate,int seqno);
	String getClinicAbrivation(int clinicid);
	ArrayList<Client> getAllPatient();
	String getIpdPractionerName(String clientId);
	String getIpdPractionerSpeciality(String clientId);
	String getPractitionerSpeciality(String clientId);
	ArrayList<Client> getAnesthesiaList();
	String getIpdPractionerId(String clientId);
	int saveDeclaration(Client client);
	Client getDeclarationData(int ipdid);
	ArrayList<Client> getDeclarationDataList(String clientId);
	Client getDeclarationByID(String id);
	int updateDeclarationData(Client client);
	Bed getdischargedata(String string);
	int check_child_growth_data(String clientId, String month);
	int updateChildGrowthData(Client client, String val);
	int saveChildGrowthData(Client client, String val);
	ArrayList<Client> getHeightMasterList(String gender, int heightcount);
	ArrayList<Client> getWeightMasterList(String gender, int weightcount);
	ArrayList<Client> getBmiMasterList(String gender, int bmicount);
	ArrayList<Client> getHeadMasterList(String gender, int headcount);
	ArrayList<Client> getClientGrowthList(String clientId);
	ArrayList<Client> getLengthHeightMasterList(String gender, int lengthcount);
	int getHeightMasterCount(String gender);
	int getWeightMasterCount(String gender);
	int getBmiMasterCount(String gender);
	int getHeadMasterCount(String gender);
	int getLengthHeightMasterCount(String gender);
	int getHeightDataCount(String gender);
	int getWeightDataCount(String gender);
	int getBmiDataCount(String gender);
	int getHeadDataCount(String gender);
	int getLengthHeightDataCount(String gender);
	ArrayList<Client> getClientGrowthHeightList(String clientId, int heightcount, int heightdatacount);
	ArrayList<Client> getClientGrowthWeightList(String clientId, int heightcount);
	ArrayList<Client> getClientGrowthBmiList(String clientId, int heightcount);
	ArrayList<Client> getClientGrowthHeadList(String clientId, int heightcount);
	ArrayList<Client> getClientGrowthLengthList(String clientId, int heightcount);
	String getClinicAbrivationFromUserid(String databasename); 

	ArrayList<Client> getallFeedbacks(String treatmenttype);
	int saveOPDFeedBackParent(Client client);
	String getparentId(Client client);
	int saveChildFeedBack(Client client);
	int saveIPDFeedBackParent(Client client);
	String getparentIdIPD(Client client);
	ArrayList<Client> getpatientlistForFeedback(String type);
	ArrayList<Client> getallfeedbacksbyPatient(String dept, String fromdate, String todate, String clientid, int ratingpercent);
	ArrayList<Client> printfeedbackForm(String parentid);
	String getProceFromClientId(String clientid);
	ArrayList<Client> getAllPatientSorted(int id);
	String getClientWeight(String clientid);
	ArrayList<Client> getbdaylistPatients(String dob,LoginInfo loginInfo);
	int savefollowup(Client client);
	ArrayList<Client> getallfollowupsToDash(String practid, String type, String fromdate, String todate );
	int setfollowupsmsflag(String id);
	String gettptypenamebyid(String typeName);
	String getwardnamebyid(String wardid);
//	String drmobno(int practitionerid);
	int setFollowupStatus(String id, String status,String followdate);
	ArrayList<Client> getApmtNameList(String mastername, String chargecolumnname, String tpid);
	Client getAppointmentType(String masterid, String chargecolumnname);
	int updateAssessment(Client client2, String assesmentid, String ipdid);
	String getopdnotes(String clientid); 
	Client getBMIData(String clientid, String appointmentid);
	Client getChildGrowthData(String clientId, String val, String month);
	ArrayList<Client> getVaccinListdataOfAll(String fromdate, String todate, String type);
	void startFollowUpSMSProcess(Connection connection, String date);
	ArrayList<Client> getAllPatientByApmtid(int id);
	int documentId(String docName);
	String documetData(String docId);
	void addToHISDocumentLog(String docId,String docValue, String clientId);
	String documentValueFromLog(String docName, String clientId);
	Client feebackDetails(String parentId); 
	int clientIdFromIPDOPD(String Type, String id);
	Client avgOfAllFeedBack(String feedBackId);
	int savePatientLog(String userId, int id, Client oldclientdata, Client client, String datetime);
	int insertintoapm_patient(String email, String fname, String lname, String mob, String dob, String abrivationid, String gender, String initial,Client client);
	Client getabravationidfrommob(String mobno);
	int updatePureSevaClientData(String clientid, String email, String fname, String lname, String mob, String dob,
			String gender, String initial, Client client2);
	int updateClientIdenttyDoc(String proimg, String clientid);
	int updateClientProfleDoc(String proimg, String clientid);
	ArrayList<Client> getAbravationIdOfAllUser(String mobno);
	int getPatientRegistrationStatus(String clientId);
	int updateRegistrationConfirm(String clientid, String registration);
	int updateBookingChecklist(String editAppointId, String pending, String remark, String doc_confirm,
			String vid_confirm);
	String getClientFullNameNew(String patientid);
	int updateClientRelativeDoc(String relativeimg, String clientid);
	ArrayList<Client> getAllPatientwithfilter(String type);
	int insertPatientwebrtc(String string, String clientid, String fullname, String clinicid, String appointmentid);
	ArrayList<Client> getClientofipd(String searchClient, int id);
	boolean checkUserExist(String drname, String type);
	int updateRatingMainform(int saveparent, int perc);
	String getOpdPractionerId(String clientId);
	String getIpdPractionerIdonly(String clientId);
	ArrayList<Client> getVitalsofPatient(String clientid);
	ArrayList<Client> getVitalList(String fromdate, String todate, String clientId);
	NotAvailableSlot getLastDepartmentAppointmentdetails(String clientId);
	int updateRegChargeApplied(int year, int result);
	ArrayList<ThirdParty> getPayerTypeList(String id);
	ArrayList<Client> getThirdPartyTypeNew(String string);
	String getPtypeNewOld(int id);
	NotAvailableSlot getRegistrationChargeAmount(String thirdPartyId, String masterCharge, String chargeName);
	int checkEnrollCode(String thirdPartyId);
	int updateFullName(String fname, String id);
	NotAvailableSlot getInvoiceDateTime(String string);
	String getDOBOfPatient(String clientid);
	NotAvailableSlot getInvoiceNewDateTime(String string);
	int savereferpatient(Client client);
	Client referClientDetails(String clientId, String sourceclinic_id);
	Client editDisease(String selectedid);
	int getupdatedisease(Client client);
	String getPayerPaymentType(String thirdPartyId);
	int deleteBMIData(String apmtId);
	int checkCampArea(String thirdPartyId);
	ArrayList<Client> getAllUser();
	ArrayList<Client> getAllEmployee(String searchClient);
	Client getUser(int id);
	ArrayList<Client> getAllPlanlist();
	ArrayList<Client> getAllChargeslist();
	NotAvailableSlot getRegistrationPhysioChargeAmount(String charges, String masterCharge);
	int savePhysioPatient(int result, Client client, int planid1, int activeplanid, int totalphysiodays);
	
	int updatePhysiopatientdays(Client client, int days, int activeplanid);
	public Client getPatientActiveplanDetails(String planid);
	Client getNumberofPhysioDays(String clientid);
	int updateRegularPatient(int patientid);
	int getPatientActiveplan(int id);
	int updateRegularPatienttoZero(int id);
	Client getRegiDatebyId(int id);
	int saveRegnobyfinyear(int result, int currentYear, Client client);
	Client getPatientnofromregno(int result);
	int updateReginobyfinyear(String finyear, int id);
	Client getCurrentyearbyid(int id);
	Client getPatientDetailfromopd(String clientid);
	ArrayList<Client> getallPlandayslist();
	ArrayList<Client> getallPlandayslistbyid(String planid);
	int insertoldPhysioPatient(Client client, String fullname);
	String getclientidbyuhid(String abrivationid);
	int updateregdatebyid(int id);
	Client getempCodenamebyempid(String searchcode);
	ArrayList<Client> getThirdTypeNameListSetupPayer(int id);
	ArrayList<Client> getAnniversarylistPatient(String anniversary, LoginInfo loginInfo);
	String getDateTimeonreciept(String clientId);
	int updateAllpayrolldec();
	int updatepayrollDec(String id);
	Client gethospitalnamebyid(String refclinicid);
	String getClinicnamebyid(String ref_userid);
	Client getClinicnamebyuserid(String refclinic, String sourceclientid);
	String getappointmentdate(String appointmentid);
	String getIpdid(String appointmentid);
	Vector<Client> getBmiVitalsdata(OpdConsult opdConsult2, String dbname, LoginInfo loginInfo, int patientid);
	int saveBamsVitalPatient(Client client);
	Client getvital(String oldipdid, String dbname, LoginInfo loginInfo, int patientid, int apmtid);
	int saveBamsVital1(Client client);
	Client getBamsDeclarationData(int ipdid, String dbname);
	int insertBamsdeclarationData(Client client);
	Client getConsentform();
	int getSequenceNumber1();
	int getMaxseqNo(String financialyr);
	int saveRegisstrationmaxseqno(String financialyr, int maxseq);
	ArrayList<Client> getAllPatientManasclinic(Pagination pagination, int id, boolean showAll, String diaryUser,
			String pstatus, LoginInfo loginInfo, String clinicstaff_id);
	ArrayList<Client> getManasClinicClient(String searchClient, int id, String pro_userid, LoginInfo loginInfo, String clinicstaff_id);
	ArrayList<Client> getAllManasClinicPatient(int id, String pro_userid, LoginInfo loginInfo, String clinicstaff_id);
	int updatePhysioIpd(int id, String physioipd);
	String getPhysioIpdorOpd(int id);
	ArrayList<Client> getManasAllPatient(int id, String pro_userid, String clinicstaff_id);
	int updateThirdpartyandwhopay(String clietid);
	int gettotalsessionByClientid(int int1);
	int getsessionByClientId(int int1);
	String getappointmentTypebyclientid(String selectedid);
	double getpenultimatecharge(String selectedid);
	int savePractioner(Client client);
	int getGpId(String clientId);
	String getPractName(int gpid);
	int updatePractid(int gpid, int practid);
	String getLastAppoitnmentDetails(String string);
	int updateUserImageUserphoto(String userImageFileName, int result);
	int updateBmidata(LoginInfo loginInfo, int bmiResult);
	int getupdateCategory(Client client);
	ArrayList<Client> getReferenceDoctorList(String selectedid);
	Master getClinicCity();
	int getsaveRemark(Client client);
	ArrayList<Client> getdailyRemark(String clientid);
	int getupdateClientStatus(String patientstatus, String clientid);
	int saveClient(Client client, int id);

	
	
}
