package com.apm.DiaryManagement.eu.bi;

import java.sql.Connection;
import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface CompleteAptmDAO {

	AppointmentType getAptmTypeCharge(String apmtType, AppointmentType appointmentType);

	int saveCharge(CompleteAppointment completeAppointment,String apppointmentid,int loginid);

	ArrayList<CompleteAppointment> getPatientChrageDetails(String id,String date,String apmtSlotId,int loginid);

	CompleteAppointment getPatientDetails(String id,
			CompleteAppointment completeAppointment,String date,int loginid);

	int deleteComplteApmt(int loginid);

	ArrayList<CompleteAppointment> getAssesmentList(String payBuy,int loginid);

	CompleteAppointment getInsuranceCompanyName(String clientId);

	int saveAmpmInvoice(CompleteAppointment completeAppointment,int loginid, String userid);

	ArrayList<CompleteAppointment> getAssesmentList(String payBuy,
			String totalassesment,int loginid);

	int saveInvoiceAssesment(CompleteAppointment completeAppointment,
			int invoice) throws Exception;

	ThirdParty getThirdParty(String clientId, ThirdParty thirdParty);

	int deleteCash(int id, CompleteAppointment completeAppointment,int loginid);

	String getAppointmentTypeName(String apmtType);

	boolean checkAppointTypeExist(String apmtType,String appointmentID,int loginid);

	int deleteComplteApmt(int appointmentid,int loginid);

	ArrayList<CompleteAppointment> getCompleteApmtList(String appointmentid,int loginid);

	int updateInvoive(String appointmentid, String action, String date);

	int updateTreatmentEpisodeSession(int tratmentepisodeid,
			int treatmenntsessions);

	int getSelfInvoiceTotal(String invoiceid);

	int savePaymentForInvoice(Accounts accounts, String discount);

	int updateCharge(CompleteAppointment completeAppointment,
			String apppointmentid);

	ArrayList<CompleteAppointment> getPendingApmtCharges(String practitioner,
			String date, String location, Pagination pagination);

	int getTotalPendingApmtChargesCount(String practitioner, String date,
			String location);

	ArrayList<Accounts> getPayByList(String appointmentid,int loginid);

	ArrayList<CompleteAppointment> getSelfPatientChrageDetails(String id);

	int updateInvoiceStatus(String invoiceId, String invoicenotes);

	

	int updateInvoiceStatus1(String invoiceid, String discount);

	int saveNewAsessment(String id, CompleteAppointment completeAppointment);

	int inActiveAsessment(int id);

	ArrayList<CompleteAppointment> getAsessmentDetails(String id);

	ArrayList<CompleteAppointment> getCompleteApmtData(String id);

	int deleteInvoiceEntry(String id);

	int deleteInvoiceAssessment(String id);

	int updateArrievedStatus(String appointmentid);

	boolean checkExcessExist(String apppointmentid, String clientId,int loginid);

	int getTreatmentSession(String apppointmentid, String clientId);

	int saveExcessCharge(CompleteAppointment completeAppointment,int loginid);

	ArrayList<CompleteAppointment> getOldAssessmnetList(String apppointmentid);

	ArrayList<CompleteAppointment> getAsessmentDetailsTemp(String id,int loginid);

	ArrayList<Accounts> getApmInvoiceList(String appointmentid);

	int createInvoice(String clientId, String practitionerId, String client,
			String practitionerName, String commencing, String appointmentid,String location);

	boolean checkAppointTypeExistInAssessment(String apmtType,
			String appointmentid);

	int saveChargeNewUpdate(CompleteAppointment c2,int loginid);

	int saveIdOfAssessment(int id);

	ArrayList<CompleteAppointment> getTempDeleteAssessmnetList();

	int deleteTempAssessmnet();

	ArrayList<String> getInvoiceIdList(String appointmentid);

	ArrayList<CompleteAppointment> getModifiInvoiceAsessmentDetails(String id);

	ArrayList<CompleteAppointment> getModifyInvoiceAsessmentDetailsTemp(
			String id,int loginid);

	int saveModifyInvoiceChargeNewUpdate(CompleteAppointment c1, String id,int loginid, String disctype);

	CompleteAppointment getModifyInvoiceDetails(String invoiceid);

	int saveModifyInvoiceNewCharge(CompleteAppointment completeAppointment,int loginid);

	ArrayList<CompleteAppointment> getModifyInvoiceCompleteApmtList(
			String invoiceid,int loginid);

	int deleteAssesmentData(String invoiceid);

	int getChargeInvoiceid(String invoiceid);

	double getModifyInvoiceTotalDebitAmmount(int chargeInvoiceid);

	int updateModifyInvoiceDebitAmmount(double debitAmount, int chargeInvoiceid);

	int updateInvoiceLocation(String invoiceId,String location);

	boolean checkAppointmentInvoiced(String appointmentid);

	CompleteAppointment getInvntryCompProdDetails(int id,int loginid);

	Product getInventoryProductDetails(String apmtType);

	int updateProdStock(double rmainQty, String apmtType);

	ArrayList<CompleteAppointment> getListofActiveCharges(int id,String clientid,LoginInfo loginInfo);

	int updateAutocharge(int id,String date,String wardid,String time,int qty);

	int truncateApmcompAppointment(int loginid);

	int saveStndCharge(String clientid, String ipdid, String standardcharges);

	int isStdChargeExits(String standard_chargeid, String ipdid);

	int updatQntyByOne(int inv_acc_id);

	int getIfAlradyExistsStdId(String ipdid, String standardcharges);
  
	int getifAutoChargeRaised(String ipdid);

	int updateStndAccessmentCharge(String ipdid, String standardcharges);

	ArrayList<Master> getStandardCharges(String wardid, String payby);

	int updateWardCharges(int id, String stcharges);

	Clinic getIpdRegistrationCharge(int clinicid);

	int deletIpdVisitingConsultCharge(String visitid, String ipdid,
			String clientid);

	int updateCompStatus(String appointmentid);

	int updateQtyInvoice(String id, String qty);
	public CompleteAppointment getInvoiceAsessmentDetails(int id);
	int saveInvoiceAssementWithHalf(CompleteAppointment completeAppointment, int amt);

	int getInvoiceforStandardCharges(String ipdid, String stdcharges);

	int getAssesmentIdforStdChargeIfExits(String ipdid, String chargeId,int invoiceid, LoginInfo loginInfo);

	int getHalfInvoiceAssesmentIfExist(String stdChargeId,String ipdid, LoginInfo loginInfo);

	int updateAutochargeDate(int id, String date, String wardid, String time);

	int getAssesmentIdforStdChargeIfExits1(String ipdid, String chargeid,
			int invoiceid, String masterchargetype);

	boolean isAutoChargedOn(String string);

	String getIpdLogLastCommencing(int id);

	boolean checkInvoiceBalanace(String clientid);

	int updateUnitCharge(int result,String unitcharge);

	ArrayList<CompleteAppointment> getUpdateSaveInvoiceCompleteApmtList(String invoiceid, int id);

	int updateIndivisualDisc(String gdiscamt, String id,double charge, String disctype, String maindisc);

	int updateIndivQty(String id, String qty);

	boolean checkInvchargeExsist(String wid, String tpid, String apmtType, String masterchargetype);

	int updateInvestigationCharge(String wid, String tpid, String apmtType, String masterchargetype,String charges);

	int saveInvestigationCharge(String wid, String tpid, String apmtType, String masterchargetype, String charges);

	int updateInveNewCharge(String dbid, String charge);

	int saveInvoiceDeletedCharge(CompleteAppointment appointment1, String newinvoiceid, int id, int i, int chargeinvoiceid, int paymentdone, String datetime, String userid);

	String getAppointmentid(String wid, String tpid, String apmtType, String masterchargetype,String charges);

String getPreviousCharge(String wid,String tpid,String ApmtType,String Masterchargetype,String Charges);

boolean isclientIdInApmt(String clientId);

double getOpdRegCharge();

boolean isclientIdInApmtBefore(String clientId);

boolean checkAutoChargeAppliedStatus(String wardid, String bedid, int id, String logcommencing);
	 
int getqantityOfCharge(int apm_patient_complete_apmt_id);

boolean isclientRegisterChargeAdded(String clientId, int id);

CompleteAppointment getInvoiceDiscountByChargeId(String invoiceid);

int updateInvoiceDiscount(double newdiscamt, int chargeinvoiceid);

int saveModifyInvoiceLog(String userId, String datetime, String string, int status, String invoiceid);
	
boolean isOPDFirstCharge(String clinetid,String appmtid);

String getSelectedChargeId(String tpid, String masterchargetype, String apmtType);
void deletefromapmminvoiceassessment(String fdate, String tdate, String admissionid, String clientid);
void swapPkgChargeList(String fdate, String tdate, String admissionid, int appliedpkgid, String clientid);

CompleteAppointment getPackageDtaes(String parentid);
void swapPkgChargeTptoinvoiceList(String fdate, String tdate, String admissionid);
void deletefromtpinvoiceassessment(String fdate, String tdate, String admissionid);

String getwardnamebywardid(int int1);

int getPackageidassesment(String clientid);

int checkInvoiceCreated(String ipdid);

void kunalChargesUpdateProccess(String invoiceid,String discper, String dicsamt,String charge);
int getChargesInvoiceIdByApmInvoiveId(String invoiceid);

ArrayList<CompleteAppointment> getUpdateSaveInvoiceCompleteApmtListGrouped(String invoiceid,int loginid);

int getChargesInvoiceIdByApmInvoiveIdNew(String invoiceid);

ArrayList<CompleteAppointment> getChargeCode(String invoiceid);

CompleteAppointment getChargeDetailsTemp(int id);

int insertModify_invoice_deleted(CompleteAppointment completeAppointment, LoginInfo loginInfo);

CompleteAppointment getopdlastappointment(String clientid, LoginInfo loginInfo);

ArrayList<String> getMissingAutoChargeDate(int ipdidadmiss, String clientId, String admissiondatenew,
		String dis_discharge_date);

int getIPDWardIDFromLogWithHighPrice(int ipdidadmiss, String string, String admissiondatenew);

int getMaxWardChargeID(int id, String today);

String getWardIDFromInvoice(String invoiceid);

int saveManualChargeLog(CompleteAppointment appointment, int invoiceid);

int getifAutoChargeRaisedNew(String valueOf);

boolean checkInvoicedFromAssesments(String chargeid);

boolean testRegChargeApply(int id);

CompleteAppointment getChargeDiscDetails(String chargeId);

String getDocnamebyid(String docid);

String getpractitonerdept(int id);

int saveCompleteApmt(CompleteAppointment completeAppointment, String apmtId, int id);

int getmasterchargeExistOrNot(String clientId, String masterchargetype);

String getMasterChargeType(String clientId);
}