package com.apm.Accounts.eu.bi;

import java.util.ArrayList;
import java.util.Vector;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Accounts.eu.entity.Invoice;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface AccountsDAO {

	ArrayList<Accounts> getAccountList(String clientId, String payby, Pagination pagination, String transactionType,
			String location, String thirdParty, String raiseChargeType, String fromdate, String todate, String newipdid,String addOnTableName);

	ArrayList<Accounts> getAssesmentList(int invoiceid, int payby);

	int updatePayment(String invoiceid, String payAmount, String howpaid, String invoiceDate, String paid);

	int getPayAmount(int invoiceid);

	int updatePayBy(int invoiceid, String payby);

	int insertPayment(String invoiceid, String payAmount, String howpaid, String invoiceDate, String paid,
			String whoPay, String clientId, String payBuy);

	int updateTransaction(String invoiceid, double credit, double debit);

	double getCreditAmount(String invoiceid, String payBuy);

	double getPaymentAmount(int invoiceid, String payBuy);

	String getThirdartyName(String clientId);

	int getTotalAccountCount(String clientId, String payby, String transactionType, String location, String thirdParty,
			String raiseChargeType, String fromdate, String todate, String newipdid,String addOnTableName);

	int setInvoiceInactive(String string);

	int updateInvoiceChargeType(int invoiceid, int payby);

	ArrayList<Accounts> gettransactionList(String clientid, String invoiceid);

	ArrayList<Accounts> getClientDetails(String clientId);

	ArrayList<Accounts> getLocationList(int i);

	ArrayList<Accounts> getThirdPartyList(int i);

	ArrayList<Accounts> getAccountPendingList(String clientId, String payby, Pagination pagination,
			String transactionType, String location, String thirdParty);

	ArrayList<Accounts> getAccountPaidList(String clientId, String payby, Pagination pagination, String transactionType,
			String location, String thirdParty);

	int updateSubmitInvoiceChargeType(int invoiceid, String submitNotes);

	ArrayList<Accounts> getInvoiceList(String num, String invoiceid);

	int saveTempChargeAccounts(int invoiceid, String loginsessionid);

	ArrayList<Accounts> getAllChargeList(String payby, String clientid);

	int deleteChargeAccounts(String loginsessionid);

	ArrayList<Accounts> getChargeAccountPendingList(String clientId, String payby, Pagination pagination,
			String transactionType, String location, String thirdParty, String raiseChargeType, String fromDate,
			String toDate, String newipdid);

	ArrayList<Accounts> getChargeAccountPaidList(String clientId, String payby, Pagination pagination,
			String transactionType, String location, String thirdParty, String raiseChargeType, String fromDate,
			String toDate, String newipdid);

	ArrayList<Accounts> getChargeAccountList(String clientId, String payby, Pagination pagination,
			String transactionType, String location, String thirdParty, String raiseChargeType, String fromDate,
			String toDate, String newipdid);

	ArrayList<Accounts> getTempChagesInvoiceList(String loginsessionid);

	String getTempChagesInvoiceStringData(String loginsessionid);

	ArrayList<Accounts> getChargesInvoiceList(String chargesStr, String chargeType);

	int updateChargeType(String string, String chargeType);

	int saveChargesInvoice(String payby, String commencing, int clientid, double debit, double discount, String notes,
			int tpid, String location, String lastModified, int loginuserid, String invpstype, String invcetype,
			int rinv, String fromdate, String todate, String doctorid, int ipdid, int preDate, String sessioncount);

	int saveChargesAssesment(int invoiceid, int chargeinvoiceid);

	ArrayList<Accounts> getChargesTempInvoiceList(String string, String clientId, String loginsessionid);

	int delateTempCharges(String invoiceid, String loginsessionid);

	ArrayList<Accounts> getCashdeskChargeList(String allChargeList, String clientid, LoginInfo loginInfo);

	ArrayList<Accounts> getCashdeskChargesTempInvoiceList(String clientId);

	int saveChargesPayment(String clientId, int invoiceid, String totalamount, String paymode, int tpid,
			String paymentNote, String date, int creditInvoiceid, String userid, String chequeno, String bankname, 
			int preDate, String commencing, String invcetype, String upino, String remark);

	String getClientName(String clientId);

	Accounts getAppointmentDetailsl(String string);

	Vector<Accounts> getPreviewChargesList(int invoiceid);

	Client getClientData(String clientId);

	ArrayList<Accounts> getchargesInvoiceList(int invoiceid);

	int getThirdPartyID(String clientId);

	int saveEmailLogDetails(String to, String cc, String subject, String notes, String filename, int invoiceid,
			String date1, String time, String type);

	String getLocationName(String location);

	String getLocationID(int invoiceid);

	int updateDeliverStatus(int invoiceid, String status);

	int updatePaymentDeliverStatus(String id, String status);

	int getTotalLocation();

	ArrayList<Clinic> getLocationAddress(int clinicid);

	double getDiscount(int invoiceid);

	double getTotalPaymentReceived(int invoiceid);

	String getNotes(int invoiceid);

	int getTPId(int invoiceid);

	ThirdParty getTpDetails(int tpid);

	ThirdParty getTpAccountDetails(String clientId);

	int getAdditionalChargeValue(int invoiceid);

	ArrayList<Accounts> getSumitedChargesList(int invoiceid);

	int updateModifiedInvoiceChargesChargeType(String charges);

	int deleteChargesOfChargesAssesment(String charges);

	int saveUpdateCharges(String str, int invoiceid);

	double getSumOfCharges(String str);

	int updateDebitAmount(double sumofCharges, int invoiceid, String invoicedate, String notes, String practid);

	String getInvoiceDate(int invoiceid);

	int checkPolicyExcess(int invoiceid);

	double getPolicyamount(int invoiceid);

	int updatePolicyAmount(double amt, int invoiceid);

	CompleteAppointment getInvoiceDetails(int invoiceid);

	CompleteAppointment getInvoiceAssessmentDetails(int invoiceid);

	int getpayBy(int invoiceid);

	Accounts checkInvoiceHasPolicyExcess(int invoiceid);

	int updateChargesInvoiceDebitAmount(int invoiceid, double debit);

	Vector<Accounts> getAdditionalPreviewChargesList(String string);

	ArrayList<Accounts> getCreditChargeAccountList(String clientId, int invoiceid, int loginid);

	int getClientOfInvoice(int invoiceid);

	boolean checkCreditAmount(String clientId);

	double getBlanceAmount(String clientId);

	int deleteTempChagesInvoiceStringData(String loginsessionid);

	String getAppointmentInvoiceid(String appointmentid);

	double getInvoiceDebitAmmount(int invoiceid);

	ArrayList<Accounts> getchargeAssesmenyList(int invoiceid);

	int deleteChargeAssesmentList(int invoiceid);

	int deleteChargeInvoice(int invoiceid);

	String getChargeLocation(String appointmentid);

	NotAvailableSlot getClientsAppointmentData(String appointmentid);

	String getCashDeskLocation(String invoiceid);

	ArrayList<Accounts> getOnlyThirdPartyList(int id);

	ArrayList<Accounts> getOnlyGpList(int id);

	String getPaymentMode(int invoiceid);

	ArrayList<Invoice> getTotalInvoice(String appointmentid);

	int getPatientipdid(int invoiceid);

	ArrayList<Invoice> getCaInvoiceList(String invoicesearchid, String fromDate, String toDate, String invoicetype,
			String invcategory, String invpaid, String payby);

	ArrayList<Master> getMasterAssessmentList(int invoiceid, String filterbydate);

	int getInvoicePreparedBy(int invoiceid);

	ArrayList<Master> getEstimateMasterAssessmentList(String clientId, int loginid);

	String getChargesClientid(String invoiceid);

	ArrayList<Master> getChargesEstimateMasterAssessmentList(String invoiceid);

	ArrayList<Master> getInvoiceTypeList();

	int getMaxResetInv();

	int getMaxResetCreditInv();

	String getInvoiceTypeId(int invoiceid);

	String getInvoiceName(String invoicenameid);

	Accounts getFromtodateforHD(int invoiceid);

	int updateHDfromDatetoDate(String fromdate, String todate, int invoiceid);

	String getInvoicIdfromAppointment(String appointmentid);

	String getChargeInvoiceId(String invoiceid);

	Accounts getInvoiceChargesDetails(String chargeInvoiceid);

	public ArrayList<Clinic> getLetterHeadDetails(int clinicid);

	ArrayList<Accounts> getAllMedicineBill(String clientId);

	int saveInvoiceMedicine(CompleteAppointment completeAppointment, int billno);

	int saveInvoiceCharges(Priscription priscription, int invoiceid);

	ArrayList<Accounts> getAssesmentList(String invoiceid);

	String getInvoicePayBy(int invoiceid);

	int updateTpCharges(Accounts accounts, AppointmentType appointmentType);

	int updateInvoicePayeeandCharges(String invoicepayby, int invoiceid, double sumtTotal, String tpid);

	int updateDeleteStatus(int invoiceid, String notes, String userid, String date);

	int deleteChargesPayment(int invoiceid);

	String getChequeNumber(int invoiceid);

	ArrayList<Master> getAdvanceMasterAssessmentList(int invoiceid);

	Vector<Accounts> getAdvancePreviewChargesList(int invoiceid);

	Accounts getAdvanceInvoiceDetails(String invoiceid);

	int getPrePaymentID(String clientId);

	int updatePrePaymentID(int paymentid, int creditinvoiceid);

	ArrayList<Accounts> getPrePaymentList(int invoiceid, String clientId);

	int getIpdIdFromInvoice(int invoiceid);

	ArrayList<Master> getBankNameList();

	int getPeactOpdID(int invoiceid);

	Accounts getpaymentdetails(int invoiceid);

	boolean chekpaymentdone(int invoiceid);

	int updatePercentageAmount(String invoiceId, String discval, String disctype, String userid, String datetime);

	Accounts getAccDiscData(int invoiceid);

	int updateRecPayment(String id, String amount, String paymode);

	int delchargeAssesment(String id);

	int delChargeid(String id);

	int updateChargeqty(String id, String qty);

	ArrayList<Master> getdchargeMasterAssessmentList(String clientId, String curdate, LoginInfo loginInfo,
			boolean isprntchrg, String ipdid);

	String getAssessmentIdList(String id);

	int deleteStandardCharge(String assesmentidList);

	Accounts getAssesmentDetails(String assesmentid);

	boolean getIfSTdCharge(String chargeid);

	String getStdChargeStartEndTime(int assesmentid, String ipdid);

	int getstdonoffcurstatus(int parseInt, String ipdid);

	int getStdchargeid(String assesmentid, String ipdid);

	int updateStdChargeDateTime(int onofid, String ondate, String offdate);

	int updateAssessmentCommencing(String commencing, String assesmentid, String commtime, String ipdid);

	ArrayList<Accounts> getbedchargeList(String clientid, String fdate, String tdate);

	int updateBedLogDate(String id, String fdate);

	String getIpdFinalDiagnosis(String fdid);

	String getFdID(int ipdid);

	int deleteChargeAssesmentOnly(String id);

	Accounts getInvoiceDeleteInfo(int invoiceid);

	String getIpdidofClient(String clientid);

	int updateAutochargeFlag(String ipdid, String flag);

	int saveShareChargeToDr(Accounts accounts);

	int editShareChargeToDr(Accounts accounts);

	ArrayList<NotAvailableSlot> getOtDetailsList(String clientId);

	String getOtsmsUsers(String id);

	int updateOtmsgStatus(String id, int loginid);

	int getOpdIDFromInvoiceId(int invoiceid);

	boolean checkChargeStatus(String string);

	int getInvoiceTypeDrId(int invoiceid);

	String getPractitionerofInvoice(int invoiceid);

	int updatePerDicount(int invoiceid);

	int updateAmtDiscount(int invoiceid);

	String getInvoiceTime(int invoiceid);

	Vector<Accounts> getRefundPreviewChargesList(int invoiceid);

	Accounts getInvoicePaymentData(String invoiceid, String pid);

	String getInvoiceLogTime(int invoiceid);

	ArrayList<Accounts> getRefundList(int invoiceid);

	ArrayList<Master> getLedgerServicesList(int invoiceid);

	ArrayList<Master> getcashdeskLedgerServicesList(String allChargeList, String clientId);

	Accounts getDiscInvoiceDetails(String invoiceId);

	ArrayList<Accounts> getDiscountGivenUserList();

	Accounts getInvoiceDetails(String invoiceId);

	int updatePercentageAmountOfInvoice(String invoiceId, String discval, String disctype, String userid,
			String datetime);

	String getDiscApproveUserid(String invoiceId);

	int getpaymentInvoiceID(String id);

	ArrayList<Master> getBillMasterAssessmentList(int invoiceid, String filterbydate);

	int getMaxInvoiceTypePaymentNo(int re, String invcetype);

	int updateInvoicetypePaymentNo(int re, int maxno, String invcetype);

	double checkParkingChargeApplied(int invoiceid);

	int updateDiscountWithParking(int invoiceid, double discamt);

	int checkIsParking(int invoiceid);

	int getMaxIpdseqNo(String a_year);

	int getMaxOpdseqNo(String a_year);

	int updateInvoiceSeqNo(String string, int res, int invoiceid, String a_year);

	int getIpdOpdSeqNo(int invoiceid);

	int getMaxInvstseqNo(String a_year);

	int getMaxMedseqNo(String a_year);

	int getMaxAdvnRefseqNo(String a_year);

	String getIpdOpdSeqNoWithType(int invoiceid);

	String getAdvanceRefrundSerialNo(String reciptno);

	int getpractIdFromInvoice(String invid);

	String gettypenamebyid(String thirdPartyType);

	Client getTPClientData(String clientId);

	String gettpnamebyid(String thirdPartyType);

	String getPkgStr(int invoiceid);

	ArrayList<Master> getPkgMasterAssessmentList(String pkgstr, int invoiceid);

	ArrayList<Accounts> getTpServicesList(String invoiceid, String sname, LoginInfo loginInfo);

	double getPkgTotal(String invoiceid, String sname);

	double getTpsTotal(String invoiceid, String sname, LoginInfo loginInfo);

	ArrayList<Master> getKunalMasterAssessmentList(int invoiceid, String string, LoginInfo loginInfo);

	int getidforlist(String string);

	String getrefdrname(String reference);

	ArrayList<Master> getTpMasterAssesmentList(String pkgstr,LoginInfo loginInfo);

	String getTpPkgStr(String clientId);

	Client getDisandadmiss(String clientId);

	int getTPIdbyclient(String clientId);

	String getTpDetailsbyclientid(int tpid);

	String getcompany_name(int parseInt);

	ArrayList<Accounts> getSubServicesList(String clientId, String masterchargename, String pkgstr,LoginInfo loginInfo);

	double getsubchargeTotal(String clientId, String masterchargename);

	int updateOpdPaymentStatus(int appointmentid, int invoiceid);

	String getDrNamebyClientId(String clientId);

	int getPaymentAppoinetmentId(String invoiceid);

	int resetOpdStatus(int appointmentid, int invoiceid);

	int updateAutochargeFlagClient(String clientid, String flag);

	Vector<Accounts> getPreviewChargesListKunal(int invoiceid);

	ArrayList<Master> getMasterAssessmentPmntRptList(int invoiceid, String fdate, String tdate, String uid);

	String getcodeforapt(String stdchargeid, int apmtchargeid);

	ArrayList<Master> getInvoicedChargeidList(int invoiceid);

	int updateChargeInvoideid(int id, int invoiceid);

	ArrayList<Accounts> getchargelist(String mastername);

	String getInvestigationAltName(String name);

	ArrayList<Accounts> getMasternameforDisc(int invoiceid);

	double getsubchargeFinalTotal(String clientId, String masterchargename);

	String getPhysicalpaymentId(String paymnetid);

	String getPhysicalpaymentIdAdvRef(String invoiceid);

	int getTpkgId(String clientId, int ipdid, String tablename);

	int getMaxPaymentSeqNo(String year);

	int paymentSequenceGenerationProccess(int paymentid);

	int reverseCharges(int invoiceid);

	Accounts getApm_invoice_Assesments(String id);

	int saveTpServiceData(Accounts a, LoginInfo loginInfo);

	int deleteTpTempSessionData(String loginsessionid);

	ArrayList<Accounts> getTpTempAssesmentList(LoginInfo loginInfo);

	int reveseAssesmentAmount(int invoiceid);

	void updateInvoiceid(int invoiceid);

	ArrayList<Accounts> getUnitchargeList(int invoiceid);

	int updateDiscountedCharge(String unitcharge, int id);

	String gettextdiagnosis(int ipdid);

	String getMultiplePaymentIdAgainstInvoice(String invoiceid);

	void genearteSeqOfPaymentByFinancialYear(int paymentid);

	String getSeqNoForKunal(String commencingDate, String invseqid, String itype);
	void generateSeqOfBill(String billid,String itype);


	public String getTpChargeCode(int apmtchargeid, String stdchargeid);
	public int insertchargeDetailsLog(CompleteAppointment completeAppointment, String qty, String status,
			String userid, String datetime);

	Accounts getPaymentAgainstlist(String invoiceid);
	double getAdvanceAndRefundAmt(String fromdate, String todate, String userid, String type, String paymode);

	String getOpdPracName(String clientId);

	boolean getRequestedDiscountStatus(int invoiceid);

	double getRequestedDiscountAmount(int invoiceid);

	double getInvoiceBalanceAmount(String id);

	double getInRequestRefundBalance(String id);

	int resetAppliedDiscount(int invoiceid);
	
	int getMaxVaccinationseqNo(String a_year);

	int updatechargePaymrnt(int invoiceid);

	int updatecreditaccountsts(int crinvoiceid);

	AppointmentType getFollowUpIdCharge(String whopay);
	
	int getInvoiceIdOfApmt(String apmtId);
	int getPaymentammount(String invoiceid);
	double getDiscamtofInvoice(String invoiceid);
	double getDebitfromInvoice(String invoiceid);

	Accounts getonofftime(int result,String ipdid);
	Accounts showonofftime(int result,String ipdid);

	int savesmstemplate(String msg, String tempname);

	String getsmstemplate(String id);

	ArrayList<Accounts> getDiscountedCharges(Accounts accounts);

	int insertChildDiscData(Accounts acc, String disctype, int parentid, double disc);

	int insertDiscountRequest(LoginInfo loginInfo, String disctype, String clientid);

	Accounts gettotoalinvdisc(int parentid);

	int updateDiscountRequest(Accounts accounts, int parentid);

	Accounts getsinglediscountcharge(Accounts accounts);

	ArrayList<Accounts> getdiscountcharges(String parentid);

	Accounts getStdChargeChildData(int id);

	int insertStdChargeChild(String chargeid, String parentid, String userId, String ukCurrentDataTime,
			String ondatetime);

	int updateStdChargeChild(String userId, String ukCurrentDataTime, String childid, String offdatetime);

	int updateAssesmentData(CompleteAppointment completeAppointment);

	ArrayList<Master> getIndDisMasterAssessmentList(String clientId, String curdate, LoginInfo loginInfo, boolean b,
			String selectipdid);
	
	int updatestdOnFFDateTime(String ipdid, String stdchrgeId,String date, String onoroff);
	int updateChildOnOffDatetime(String datetime, String childid, String onoroff);
	ArrayList<Accounts> getOnOffChildList(String parentid);
	Accounts getOnOffChargesChildDates(String parentid);
	int delChildOnOffCharge(String childId);
	Accounts getOnOffChargesChildDateNEW(String id);
	Accounts getOnOffChargesParent(String id);

	String getChargeTpid(String chargeid);

	ArrayList<Accounts> getChargeAssesments(int invoiceid);

	int checkChargeAssements(Accounts accounts);

	Accounts getRefundData(int invoiceid);

	boolean getdiscountapprovests(int invoiceid);

	String getinddiscounttotal(int parentid, String str);
	
	CompleteAppointment chargeAssementDetails(String assemntId);
	
	ArrayList<Master> getMasterAssessmentListForCA(int invoiceid, String filterbydate);

	String getDrNamebyIPDId(String ipdid);
	int resetOpdInvoiceStatus(int appointmentid, int invoiceid);

	int getAppointmentIdFromInvoiceid(String id);

	int updateOpdchrgststaus(int appointmnt);
	
	String getFinancialSeqNoOfInvoice(String invoiceid);

	Clinic getLocationAddresswithnocon(String userId);

	String getInvoicePaymentNote(int invoiceid);
	
	
	String getInvoicePrefix(String invoiceType);

	ArrayList<Accounts> getCashInvoiceList(String fromDate, String toDate, Pagination pagination);

	int getCashInvoiceListcount(String fromDate, String toDate);
	int getMaxBillSeqNo(String year, String type);

	String getMultiplePaymentSequenceLMH(int invoiceid);

	String getLMHOPDSequence(int opdid);

	Accounts getchargeDetails(String chargeid);

	int saveRenderdata(Accounts accounts);

	ArrayList<Accounts> getrenderChargesrpt(String fromDate, String toDate);

	int getdoctorData(String doctorid);

	int saveRenderPaymentdata(String chargeid, String balcnceamt, String date_time, String paymenttype, int genearkey);

	int savetoParentrenderpayment(Accounts accounts, String userid);

	String getTransactionNo(int invoiceid);

	String getTokennoforOPD(int opdid, int opdparctid, String date);

	Vector<Accounts> getdebitTransectionlist(String clientId);

	double getPaymentList(String clientId);

	double getAllAdvanceamount(String clientId);

	int savePatientTransection(double balnace, double debit, double prepayment, double advance, String clientId, String date_time);

	double getDebitamountbyid(String clientId);

	Accounts getPatientTransactiondata(String clientId);

	double getAdvanceamtbyrefinvid(String clientId);

	boolean checkClientexist(String clientId);

	int updatePatientTransaction(double balance, double debit, double payment, double advanceamt, String clientId, String date_time);

	int updateAdvancerefundzero(String clientId);

	double getChargesSumAssessment(String clientId);

	ArrayList<Master> getMasterAssessmentPmntRptList1(String inv1, String fromDate, String toDate, String userid);

	Vector<Accounts> getdchargeFilteredPreviewChargesList(String mastername, String clientId, String curdate,
			LoginInfo loginInfo, boolean isprntchrg, String ipdid, int i, String string);

	ArrayList<Clinic> getLocationAddressForManasclinic(int id);

	ArrayList<Master> getIpdInvoiceTypeList(String string);

	int patientphysioIpdOrNot(String diaryuserid);

	Vector<Accounts> getFilteredPreviewChargesPmntRptList(int invoiceid, String name, String userid);
	Vector<Accounts> getFilteredPreviewChargesPmntRptList1(String inv1, String mastcharge, String userid);

	int saveChargesInvoice(String payby, String commencing,int clientid,double debit,double discount,
			String notes,int tpid,String location,String lastModified,int loginid,String invpstype,String invceype,int rinv,String fromdate,String todate,String doctorid, int ipdid,int preDate,String sessioncount, LoginInfo loginInfo);

	int inserLatestSeqNoOfBill(String yearrangenew, int maxSeqNo, String string);

	ArrayList<Master> getdepartmentchargeswiserevenuelist(String fromDate, String toDate, String mastercharge);

	ArrayList<Master> getchargeswiserevenuelist(String fromDate, String toDate, String mastercharge, String searchtext);

	int totalCount(String fromDate, String toDate, String mastercharge);
	ArrayList<Accounts> getProcedureListbyapmtid(String apptid);

	int updateOtid(int invoiceid, int appointmentid);

	String getPrintDatebyinvoiceid(int invoiceid);

	String getInvoiceNote(int int1);
}
