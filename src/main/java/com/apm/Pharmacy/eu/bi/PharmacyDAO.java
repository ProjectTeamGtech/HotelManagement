package com.apm.Pharmacy.eu.bi;

import java.util.ArrayList;
import java.util.Vector;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface PharmacyDAO {

	int updatebalance(int pharmacyclientid,String balance);

	
	ArrayList<Priscription> getPriscListAvailablity(String selectedid);

	int addMedicineBill(CompleteAppointment appointment);

	int addMedicineCharges(Priscription priscription, int billno);

	int updatePriscEmrBill(int billno, String selectedid);

	CompleteAppointment getBillDetails(int billno);

	ArrayList<Priscription> getMedicineChargesList(int billno, LoginInfo loginInfo);

	int getTotalProcessingChargesCount(String clientid, String transactionType,
			String fromDate, String toDate);

	ArrayList<Accounts> getAllProcessingCharges(Pagination pagination,
			String clientid, String transactionType, String fromDate,
			String toDate);

	int recordPaymentMedicine(String billno, double payAmount1, String howpaid,
			String invoiceDate, String clientId, String ccdamt, double discount,String paymentnote,String pclientid,String userid,String location,int onsamedate, int paymentseqno);
	
	public double getTotalPaymentReceived(int billno);
	public double getDiscount(int invoiceid);
	
	public ArrayList<Accounts> getAssesmentList(int invoiceid);

	int updateDeliverStatus(String status, String priscid);

	int addNewPharmacyPatient(Client client);

	int updateBillandStatus(int pharmacyclientid, int billno, String string);
	public Priscription getPharmacyPatientDetails(int billno);

	int addnewRequestStock(String medicineid, String qty, String date, String loc, String userid);
	
	public ArrayList<Product> getAllRequestedMedicine(String fromdate,String todate);

	int updateMedicineBill(CompleteAppointment appointment, int billno);

	int updateMedicineCharges(Priscription priscription);
	public String getPharmacyClientidFromBill(int billno);
	Priscription getPharmacyPatient(String pclientid);
	public int addPriscriptionForBill(Priscription priscription);

	String getBillPaymode(int billno);

	int addUpdateMedicineRefund(int billno, double reftot,String date);

	double getRefundAmt(int billno);

	ArrayList<Priscription> getReportPharmacyList(String searchtext,
			String fromdate, String todate, String from, String paymode,
			String report,String isreturn,String location);

	ArrayList<Priscription> getAllBillListofClient(String clientid,
			String flag, String fromdate, String todate);
	
	String getwardlocationofClient(String clientid);

	int getIpdPatientSaleCount(String fromdate,String todate);

	int getOpdPatientSaleCount(String fromdate, String todate);

	int getNewpatientSaleCount(String fromdate, String todate);
	public ArrayList<Product> getAllRequestedMedicine();

	ArrayList<Priscription> getAllDoctorReportList(String fromdate,
			String todate, String phar_practid);

	ArrayList<String> getAllBillListByDoctor(String fromdate, String todate,
			String doctorid);

	int getMedicineRequestedCount();
	public ArrayList<Product> getSaleProdutList(String fromdate,String todate);

	int addUpdateMedicinePoList(String pid, String qty, String commencing);

	ArrayList<Priscription> getPriscPharmacyAvailablity(String string);

	int updateRequestedStatus(String pid);

	String getCardBillInvoice(int billno);

	Clinic getPharmacyAddress();

	int updateLoginaccess(String id, String status, String accessdatetime, String currentUser);

	String getusernamefromusertbl(String userid);

	ArrayList<Priscription> getallbillbyuser(String userid, String fromdate,
			String todate,String loc);

	int getallusereportcount(String fromdate, String todate);

	ArrayList<Priscription> getuserreportinfo(Pagination pagination,
			String fromdate, String todate,String location,String userid, String selectuserid);

	Product getpatientinfo(String id);


	String getpreviousbalance(int pharmacyclientid);


	int clearbalfromdb(String extpid);


	int updatebalpatient(String balance, String selectedid);


	String getbalofpatient(String patientId);


	int clearpatientbal(String opdid);


	Priscription getusersetting(String userid, String status);


	Priscription setUserSaleSettting(String userid, String status, String accessdatetime, String currentUser);


	Priscription setUserDiscountSettting(String userid, String status, String accessdatetime, String currentUser);


	Priscription setUserLedgerSettting(String userid, String status, String accessdatetime, String currentUser);


	Priscription setUserAccountSettting(String userid, String status, String accessdatetime, String currentUser);


	Priscription setUserPurchase_orderSettting(String userid, String status, String accessdatetime, String currentUser);


	Priscription getPharacyUsrLInfo(String userid);


	String getIpdIdOfClient(String clientid);
	
	ArrayList<Priscription> getipdpatientlist();

	String getclientid(String ipdid);

	String getpricriptionid(String ipdid);

	String getclientidfrompriscid(int id);

	int getpriscidfromclientid(String clientid);
	ArrayList<Priscription> getbillno(String ipdid);


	Priscription getMedicineChargesbyid(int parseInt,int qty);


	int setIsReturn(int billno,int refundid);


	int getSelectedIdFromIpdid(String ipdid);


	int updateBalanceofBill(String billno, String balance);


	double getTotalBalanceofUsers(String location,String fromdate,String todate);


	double getTotalCollectionToday(String location,String fromdate,String todate);


	int updateMedicineReturnQty(int id, int returnqty);
	int updateBalanceByBillWise(String clientid,double amt,int status,Priscription priscription,String paynote, String location, LoginInfo loginInfo, String clearbilltotalids);
	public String getmobfromuserid(String userid);
	public double getTotalCardCollectedByUser(String userid,String fromdate,String todate,String location) ;
	public double getTotalCashCollectedByUser(String userid,String fromdate,String todate,String location);
	Priscription setUserSMSSendAuthority(String userid, String status, String accessdatetime, String currentUser);
	
	ArrayList<Master> getAllLocation();
	public String getLocationName(String id);


	int deleteMedicalBill(String bill);


	int updateBillCharges(int saleqty, String mrp, int id);


	int saveNewBillCharge(CompleteAppointment appointment, int saleqty,
			int mrp, String billno);


	int updatePaymentMedicine(String valueOf, String total, String date,
			String string, double discount, String vat);


	int updateBillTotal(String total, String billno,String edit_note);
	public double getCardPaymentAmt(String fromdate, String todate,
			String clientId,int res);


	ArrayList<String> getAllBillListByExDoctor(String fromdate, String todate,
			String fullname);


	Priscription setUserEditBillAuthority(String userid, String status, String accessdatetime, String currentUser);


	Priscription setUserDeleteBillAuthority(String userid, String status, String accessdatetime, String currentUser);


	Priscription setUserDeletePurchaseOrderAuthority(String userid,
			String status, String accessdatetime, String currentUser);


	Priscription setUserEditPurchaseOrderAuthority(String userid, String status, String accessdatetime, String currentUser);
	public String getPharmacyLocation(String locid);


	double getPayAmount(int billno);


	double getCahPayAmount(int id);


	double getCashToday(String location,String fromdate,String todate);


	double getTodayDisc(String location,String fromdate,String todate);


	double getCardToday(String location,String fromdate,String todate);


	double getReturnToday(String location,String fromdate,String todate);


	int updateBillBalance(String bill);


	String getinhousepatientstatus();


	int validatepharmacyuser(String userid);


	String getIpdIdFromClientID(int id);


	String getappointmentinfo(int id);


	Priscription setinhousepatient(String status);


	int updateRefundBillBalance(String oldbill, String refund);


	ArrayList<Priscription> getLocationWiseReport(String fromdate,
			String todate, String location);


	String getClientIDFromBillNo(int billno);


	int returnPayment(String oldbill,String refund);


	int updatePFlag(int result);


	int recordRefundPayment(int result, String refund);


	int setReturnBillNumber(String oldbill, int billno);


	String getOriginalBillId(int billno);


	Priscription setUserEditCatalogue(String userid, String status, String accessdatetime, String currentUser);


	Priscription setUserDeleteCatalogue(String userid, String status, String accessdatetime, String currentUser);


	int addEstimateMedicineBill(CompleteAppointment appointment);


	int addEstimateMedicineCharges(Priscription priscription, int billno);


	int recordEstimatePaymentMedicine(String valueOf, double tot,
			String paymode, String nowdate, String clientId, String string,
			double discount, String notes, String pclientid, String userId,
			String loc);


	CompleteAppointment getEstimateBillDetails(String estimatebill);


	ArrayList<Priscription> getEstimateMedicineChargesList(String estimatebill);


	int deleteEstimateBill(String estimatebill);


	Priscription setBackDateAccess(String userid, String status, String accessdatetime, String currentUser);
	public Priscription setTpBillAccess(String userid, String status, String accessdatetime, String currentUser);


	int cancelMedicineBill(String bill);


	ArrayList<Product> getAllRequestedMedFrTransfer(String fromdate,
			String todate, String loc);


	ArrayList<Product> getParentProductTransferData(String fromdate,
			String todate, String loc);


	Product getRequestedMedicineid(String m_id);


	int saveNewMedicineRequestParent(Product product1, String userid);


	int saveNewMedicineRequestChild(Product product, String req_qty, int result, Product product2);


	int updateRequestedStatusById(String s);


	int updateRejectTransferLog(String parentid, String string, String notes,
			String reject, String userid);


	int updateAproveTransferLog(String parentid, String string, String aprove, String userid);


	ArrayList<Product> getChildTempReqData(String parentid);


	int deleteTempRequestedData(String parentid);


	int transferRequestedMedicine(String parentid, Product product);


	int getRequisitionCount(String loc);


	String getBilPayNote(int billno);


	int updatePaymentNote(int result, String paynote);


	double getChequePayment(String location, String fromdate, String todate);


	Priscription setRequisition_AuthAccess(String userid, String status, String accessdatetime, String currentUser);


	double getNeftPayment(String location, String fromdate, String todate);


	double getChequePaymentAmt(String fromdate, String todate, String clientid,
			int i);


	double getNeftPaymentAmt(String fromdate, String todate, String clientid,
			int i);
	double  getTotalChequeByUser(String userid,String fromdate,String todate,String loc);
	double  getTotalNeftByUser(String userid,String fromdate, String todate, String loc);
	public int updateBalanceByBill(String billno,String balance);


	int updateRejectTransferLog(String parentid, String string, String notes,
			String reject);


	int updateAproveTransferLog(String parentid, String string, String string2);


	int updateNotesofParent(String parentid, String notes);


	double getTotalRecived(String fromdate, String todate, String location);


	double getTotalRefund(String fromdate, String todate, String location);


	double getTotalBalance(String fromdate, String todate, String location);


	ArrayList<Priscription> getAllBillHistory(String clientid, String flag,String location,String fromdate, String todate, int orderby);


	double getTotalHospital(String location, String fromdate, String todate);


	double getTotalBalance(String clientid, String flag);


	int updateTpidtoPayment(int result, String tpid);
	public ArrayList<Priscription> getMedicineReturnCharges(int billid,int oldbill, double discper, int idfromdirectreturn);


	double getHospitalPaymentAmt(String fromdate, String todate,
			String clientid, int i);


	double getTotalChequeCollectedByUser(String userid, String fromdate,
			String todate, String location);


	double getTotalNeftCollectedByUser(String userid, String fromdate,
			String todate, String location);


	double getTotalHospitalCollectedByUser(String userid, String fromdate,
			String todate, String location);


	double getTotalReturnByUser(String userid, String fromdate, String todate,
			String location);


	double getCreditReturn(String location, String fromdate, String todate);


	double getHospitalReturn(String location, String fromdate, String todate);


	int saveNewMedicineRequestChild(Product product, String req_qty,
			int result, Product product2, int avail_qty, String comment);


	Priscription setCheck_stock_availableAccess(String userid, String status, String accessdatetime, String currentUser);


	Priscription setDirect_TransferAccess(String userid, String status, String accessdatetime, String currentUser);


	int updateExPatient(int pharmacyclientid, Client client);


	ArrayList<Client> getPharmacyClientList();


	ArrayList<Client> getPharmacySearchClientList(String searchClient);


	int updatePharmaClient(String val, String column, String clientid);


	int saveNewPharmaPatient(String date, String adfullname, String admobile, String adaddress, String addoctor, String fullname, String abrivationid, String gstin, String dlno);


	int updateClientData(Priscription priscription);


	int updatePaymentDateTime(String dateTime,int result);


	int getAllPharmacySaleCount(String searchtext, String fromdate, String todate, String location,String isreturn,String paymode);


	int saveParentBillClearPayment(Priscription priscription);


	double getBillBalance(String billno);


	int saveChildBillClear(int parentid, Priscription priscription);


	Priscription getClearBillReceptInfo(int parentid);


	ArrayList<Priscription> getClearBillList(int parentid);
	public Priscription getClearBalBillInfo(String billno);


	int updateBillCreditReturn(int billno, String refund);


	double getTotalCredit(String location, String fromdate, String todate);


	ArrayList<Priscription> getAllPharmacySaleList(Pagination pagination, String searchtext, String fromdate,
			String todate, String location,String isreturn,String paymode, int dontshowreqbill,LoginInfo loginInfo, String payee, String tpid);


	Priscription setInventory_ReportAccess(String userid, String status, String accessdatetime, String currentUser);


	Priscription setReturn_StockAccess(String userid, String status, String accessdatetime, String currentUser);


	ArrayList<Product> getAccessList();


	Priscription setCancel_IndentAccess(String userid, String status, String accessdatetime, String currentUser);


	int getMaxLastReturnBillId();


	int getCountAccessList();


	ArrayList<Priscription> getPriscAvailableByMolecules(String parentpriscId);


	Priscription setReturn_MedicineAccess(String userid, String status, String accessdatetime, String currentUser);


	int deleteMedicineCharges(String str);


	int updateGstOfBill(String vat, String cgst, String billno);


	ArrayList<Product> getAllMedicinesofClient(String clientid, String ispharmacy, String location, int isrequest);


	int updateRefundAllBalance(String clientId, String valueOf, String string);


	int updateReturnQty(int saleqty, int chargeid);


	int getReturnQtyofCharge(String chargeid);


	ArrayList<Priscription> getAllBillListofClient(String clientid, String flag);


	Priscription setindent_approveAccess(String userid, String status, String accessdatetime, String currentUser);


	ArrayList<Priscription> getProductwiseReportList(String fromdate, String todate);


	ArrayList<Priscription> getPriscPharmacyAvailablityNew(String string, String string2, String todate, String loc);


	int updatePriscEmrBillnew(int billno, int newparentid);


	Priscription setpo_approveAccess(String userid, String status, String accessdatetime, String currentUser);
	//lokesh
	boolean isShowletterhd();
	String getBillPaymodeNew(int billno);
	Priscription setCancelPoAcces(String userid, String status, String accessdatetime, String currentUser);
	Priscription setPurchaseEditAccess(String userid, String status, String accessdatetime, String currentUser);


	ArrayList<Priscription> getAllPharmacySaleListNew(Pagination pagination, String searchtext, String fromdate,
			String todate, String location, String isreturn, String paymode, String userid);


	int getAllPharmacySaleCountNew(String searchtext, String fromdate, String todate, String location, String isreturn,
			String paymode, String userid);


	double getTotalcreditReturnByUser(String userid, String fromdate, String todate, String location);


	double getTotalAgainstCredit(String location, String fromdate, String todate);


	int updateBillBalance(String billno, double temp);


	double getPreviousBillDiscount(int parseInt);


	int updateBillDiscount(double totaldisc, int parseInt, double totaldebit);


	int getClearBalBillCount(String valueOf);


	ArrayList<Priscription> getClearBalBillList(String billno);


	ArrayList<Priscription> getAllPharmacySaleListRefund(String location, String medids, String hdnispharmacy, String hdnphclientid,LoginInfo loginInfo);


	Double getTotalRecivedAmt(String searchtext, String fromdate, String todate, String location, String isreturn,
			String paymode, String userid);

	

	int cancelEstimate(String bill);


	ArrayList<Product> getAllBillsofClient(String clientid, String ispharmacy, String paymodereturn, String location);


	ArrayList<Product> getAllBillWise(String clientid, String ispharmacy, String multireturnbillid, int i);


	int updateBillRefundBillids(int billno, String returnbillids);
	ArrayList<Priscription> getallTempMedList();
	int settemptoPermanat(String id, String date, String userid);


	int saveReturnMedicineByNurse(String clientid, String userId, String datetime, String ipdid);


	int returnByNurseChild(Priscription priscription);


	ArrayList<Priscription> getReturnMedicineDash(String fromdate, String todate);


	Priscription getReturnMedicineRequest(String id);


	int updateReturnRequestStatus(String returnrequestid, String returnchargeid, int billno);
	
	ArrayList<Priscription> getnewDrReport(String fromdate, String todate,String drname);
	
	ArrayList<Priscription> getnewMedicineDialyCountReport(String fromdate, String todate,String location,String productname);
	
	ArrayList<Priscription> getnewGSTReport(String fromdate, String todate,String paymode,int isreturn,String clinic ,String reportby);


	ArrayList<Priscription> getBillPayments(String billno);


	int updatePaymode(Priscription priscription);


	boolean checkopeningstockexist(String id, String date);


	int saveOpeningStock(String valueOf, String date, double openingstock, String string);


	int getPharmacyBillSeqNo(String location);


	int updatePharmacySeqNo(int billno, int billseqno);


	int getPharmacyPaymentSeqNo(String loc);


	int updatePharmacyPaymentSeqNo(int result, int paymentseqno);
	
	Priscription setpharmacy_backdate_print_access(String status,String userid);
	
	ArrayList<Priscription> gethsnwiseGSTReport(String fromdate, String todate);


	double getPharClientBalance(String ispharmacy, String clientid);


	int returnByNurseChildTemp(Priscription priscription, String sessionid);


	ArrayList<Priscription> getTempMedicineCharge(String sessionid);


	ArrayList<Priscription> getReturnMedicineTempList(String sessionid, Priscription priscription);


	int deleteTempNurcingMedicine(String sessionid);


	boolean checkPatientAvailableInLog(int tempclientid, int ispharmacypatient, String loginsessionid, int isfordiscount);


	int insertTempPharSaleSession(int tempclientid, int ispharmacypatient, String loginsessionid, int isfordiscount, String discsmt, String distype, String discinper);


	boolean checkTempIdInPharmacyBill(int tempsessionid);


	int deleteTempPharmacySession(String loginsessionid);


	int updateMedicineChargeGST(int id, double gst, double half, double tempvat, double tvat);


	ArrayList<Priscription> getMedicineChargeList(int billno);


	CompleteAppointment getTempPharDiscountData(int tempclientid, int ispharmacypatient, String loginsessionid, int isfordiscount);


	int getOpeningStock(String string);


	Product checkopeningstockexistData(String productid, String date);


	int updateIndentQtyInOpening(Product openingproduct, double indentqty);


	ArrayList<Priscription> getReturnAllMedicineByNurceList(String id, String clientid, String ispharmacy, String loginsessionid, String datetime);
	
	double getDecimalBillAmount(String billid);
	
	Priscription getBifurcationOfPharmacyBill(String billid);
	
	double getPaymentAgainstCredit(String fromdate,String todate,String paymode);


	int updateBillFinalPaymode(String billno, String paymode);


	boolean checkBillCreditStatus(String billno);


	int updateGstPaymentFlag(String billno);


	ArrayList<Priscription> getAllBillListofClientNew(String clientid, String flag, String fromdate, String todate, int orderby);
	
	double getTotalPaidAmountBYclient(int clientid,String fromdate,String todate,String balance,int ispharmacist);


	ArrayList<Priscription> getAllPharmacySaleListNew(Pagination pagination, String searchtext, String fromdate,
			String todate, String location, String isreturn, String paymode, int dontshowreqbill, LoginInfo loginInfo);


	int getAllPharmacySaleCountNew(String searchtext, String fromdate, String todate, String location, String isreturn,
			String paymode);

	
	int getCreditReturnPharmacy(String fromdate,String todate);
	ArrayList<Priscription> getpaymentAgainstCredit(String fromdate, String todate, String isreturn,String paymode);


	int getBillNoFromPaymentid(String change_paymode_id);


	int updateBillInitialPaymode(String billno, String paymode);


	boolean checkPatientChargeAvailableInLog(int tempclientid, int ispharmacypatient, String loginsessionid, int id,
			String qty, String fromDate, String todate);


	int insertMedicineChargeLog(int tempclientid, int ispharmacypatient, String loginsessionid, int id, String qty,
			String fromDate, String todate);


	int deleteProductFromSession(String chargesessionid);


	int updateProductFromSession(String chargesessionid, String saleqty ,String freeqty, String discount_perproduct);


	int updateChargeSessionStatus(int tempclientid, int ispharmacypatient, String loginsessionid, int status);


	int updateSessionChargeBillno(int tempclientid, int ispharmacypatient, String loginsessionid, int billno);


	int deleteTempChargePharmacySessionLog(String loginsessionid, String clientId, int ispharmacy);


	int deletePharmacyBill(String sessinbillno);


	int updateBillDebit(double totaldebit, int billno);


	int insertMedicineTempChargeLog(int tempclientid, int ispharmacypatient, String loginsessionid, int id, String qty,
			String fromDate, String todate, int chargesessionid, double totalavailablestock, String sale_price, String isbarcodeproductsale);


	int deleteTempPharmacyData(String loginsessionid);


	int updateProductFromTemp(String chargetempid, String saleqty, String freeqty, String discount_perproduct);


	int deleteProductFromTemp(String chargetempid);


	Vector<Priscription> getTempMedicineList(int tempclientid, int ispharmacypatient, String loginsessionid);


	Bed getIPDActiveDataFromClientid(String string);


	int deletePharmacyBillCharges(String sessinbillno);


	int deletePharmacyBillPayment(String sessinbillno);


	ArrayList<Priscription> getMedicineChargesListFast(int parseInt);


	boolean checkMedicineTempChargeLog(int tempclientid, int ispharmacypatient, String loginsessionid, int id,
			String qty, String fromDate, String todate);


	int getAllPharmacyChargeCount(int billno);


	int deletePharmacyBillData(String sessinbillno, String sessionuserid, String sessiondatetime);


	int getPatientChargeAvailableInLog(int tempclientid, int ispharmacypatient, String loginsessionid, int id,
			String qty, String fromDate, String todate);


	int deleteFromSessionLog(String loginsessionid);


	int deleteTempPharmacySaleData(String loginsessionid, String clientid, int isphamrmacy);


	boolean checkPatientInTempSaleData(String loginsessionid, String clientid, int isphamrmacy);


	boolean checkReturnProductInTemp(String chargeid, String hdnphclientid, String hdnispharmacy, String loginsessionid);


	int insertIntoPharReturnTemp(String chargeid, String hdnphclientid, String hdnispharmacy, String loginsessionid,
			String datetime, String qty);


	Vector<Priscription> getReturnTempMedicineList(int hdnphclientid, int hdnispharmacy, String loginsessionid);


	int updateProductFromReturnTemp(String tempreturnid, String returnqty);


	int deleteProductFromReturnTemp(String tempreturnid);


	int deleteTempReturnPharmacyData(String clientid, String ispharmacy, String userId);


	int deletePharmacyReturnBillData(String string);


	Priscription getPharmacySaleAndDiscount(String fromdate, String todate, String string, int isreturn, String location);


	double getPaidAmt(String fromdate, String todate, String string, String location, int isoutstanding, int onsamedate);


	int getAllReqStockPharmacy(String pid);


	int getAllReqStockPharmacy(String pid, int tempclientid, int ispharmacypatient, String loginsessionid);

	int deleteTempPharmacyTimelyData(String fromdate);


	String getRequestFromPriscriptionBill(String string);


	int checkDummyBillExist(String billno, String dummybillno);


	int updateDummyBillNo(String billno, String dummybillno, String dummybilldate);


	int saveDummyBillnoChange(String billno, String dummybillno, String dummybilldate, String datetime, String userId);


	int checkbillalreadydeletedornot(int int1);


	boolean checkIpdPatientStatus(int int1);


	int updateProductSalePriceFromTemp(String chargetempid, String sale_price);


	Priscription getPharmacyBillDetails(String billno);


	int savePharmacyDiscountRequest(String billno, String debit, String requestdisc_disctype, String requestdisc_amt,
			String userid, String datetime, String discinper, Priscription priscription, int disc_requeststatus);


	int updateDiscountBillStatus(String billno, int i);


	ArrayList<Priscription> getDiscountList(String fromdate, String todate, String searchText);


	Priscription getDiscountData(int id);


	int updateDiscountApproveNotes(int id, String approve_notes, int i, String userid, String datetime);


	int updateBillVatNew(double cgst, double gst, String billno, int disc_type, double discount);


	int checkpaymentdoneagainstbill(String billno);


	int getByDefaultPharmacyLocation();


	int getAllOnlineSaleCountNew(String searchtext, String fromdate, String todate, String location, String isreturn,
			String paymode, LoginInfo loginInfo);


	int checkProductReturnAgainstBill(String billno);


	ArrayList<Master> getAllLocationNew();


	int updatePharDiscountReq(String id, String userId, String datetime);


	int updateGrossTotalAndDisc(String billno, double grosstotal, String requestdisc_amt, String requestdisc_disctype);


	int getCheckDiscountNotRequested(String id);


	double getTotalBalanceNew(String clientid, String flag, String totalids);


	ArrayList<Product> getAllMedicinesForIPDReturn(String clientid, String ispharmacy, String string, int i);


	int getNurseReturnQty(int i);


	Priscription getNursePrescriptionParent(String returnrequestid);


	int updateBillIpdId(String ipdid, int billno);


	double getDiscountPerReturnMedicine(String string, double total);


	CompleteAppointment getBillIpdIdfromMultiId(String returnbillids);


	int updatePharmacyRefundIpdId(String bedid, String wardid, String phar_ipdid, int billno);


	ArrayList<Priscription> getReturnMedicineCharges(int billno);


	ArrayList<Product> getsessionAddedPharMedList(String location);


	Priscription setSession_Added_Med(String status, String userid);


	int checkProductInSale(String string);


	int getChargeIdFromProductId(int newproductid, String clientid, String ispharmacy, String paymode, String location);


	ArrayList<Priscription> getPriscInSaleList(String fromdate, String todate);


	ArrayList<Priscription> getPrintPriscReqcsv(String newparentid);


	ArrayList<String> getPatientNameData(String val);


	String getappointmentinfopractid(int parseInt);


	Priscription getLastBillDrDetails(int i, String clientid);


	int getBillPharmacyAvailablityNew(String billno, String loginsessionid, String todate, String loc, String hdnphclientid, int i);


	boolean checkifSequenceExist(String cdate);


	int getSqeunceNumber(String cdate);


	int InserCdateSeq(String cdate, int seqno);


	int updateNewPharmaPatient(String adfullname, String admobile, String adaddress, String addoctor, String fullname,
			String editpclinetid);


	int pharmacypatientupdatelog(Priscription priscription, String adfullname, String admobile, String adaddress,
			String addoctor, String editpclinetid, String datetime);


	int updatepharmacyaccess(String status, String userid, String colname);


	double getOnlineReturn(String location, String fromdate, String todate);


	double getOnlinePayment(String location, String fromdate, String todate);


	double getCardReturn(String location, String fromdate, String todate);


	int getPreviousSaleQty(int id);


	int updateMedicineChargesQty(int newqty, int id);


	ArrayList<Priscription> getMedicineReturnChargesList(int billno);


	int updateEditPharmacyCharges(Priscription priscription);


	int updatePharmacyEditBill(int billno, double discount1, double grosssubtotal, double grosstotal, double balance,
			double totalvats, String discper, String notes, String datetime, String userid);


	boolean checkProductAddedForSameClient(String pid, int tempclientid, int ispharmacypatient, String loginsessionid);


	int getPharmacyPatientCount(String searchtext);


	ArrayList<Client> getPharmacyPatientList(Pagination pagination, String searchtext);


	String getfakedatebyClientid(String fakclientid);


	String getdatebyClientid(String fakclientid);


	int getproductid(int billno);


	int getCatalogueid(int productid);


	int getsubcategoryid(int cataloguid);


	String getsubcategorynameById(int subcategoryid);


	int getlocationforParihar(String subcategoryname);


	int getUpdatelocationforParihar(int parlocation, int billno);


	ArrayList<Priscription> getAllPharmacySaleListNew1(Pagination pagination, String searchtext, String fromdate,
			String todate, String location, String isreturn, String paymode, String userid, LoginInfo loginInfo);


	int getAllPharmacySaleCountNew1(String searchtext, String fromdate, String todate, String location, String isreturn,
			String paymode, String userid, LoginInfo loginInfo);


	ArrayList<Priscription> getAllPaymentList(String fromdate, String todate, String location, double totalRefund, double totalRefundonline);


	double getTotaldiscount(String fromdate, String todate, String location);


	double getTotalRefundAmount(String fromdate, String todate, String location);


	String getLocationfromBillNo(int billno);


	String getthirdpartynameidByClientid(String clientId);


	String getThirdPartyName(int int1);
}


