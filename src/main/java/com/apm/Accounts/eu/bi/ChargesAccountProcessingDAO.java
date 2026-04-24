package com.apm.Accounts.eu.bi;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.Pagination;

public interface ChargesAccountProcessingDAO {

	ArrayList<Accounts> getChargesAccountProcessingList(String clientId,
			String payby, String fromDate, String toDate,Pagination pagination,String actionType,String selectedInvoiceid);

	ArrayList<Accounts> getAssesmentList(int invoiceid);

	ArrayList<Accounts> getChildAssesmentList(int invoiceid);

	ArrayList<Accounts> gettransactionList(String invoiceid);

	int savePayment(String invoiceid, String payAmount, String howpaid,
			String invoiceDate, String clientId,int tpid,String paymentNote,String date,int creditInvoiceid,String chequeNo,String userid,String bankname, String discamt);

	int getTotalChargesAccountProcessingCount(String clientId, String payby,
			String fromDate, String toDate);

	ArrayList<Accounts> getChargesAccountProcessingPendingList(String clientId,
			String payby, String fromDate, String toDate, Pagination pagination);

	ArrayList<Accounts> getChargesAccountProcessingPaidList(String clientId,
			String payby, String fromDate, String toDate, Pagination pagination);

	double getDiscount(int invoiceid);

	Accounts getClientDetails(String clientId);

	ArrayList<Accounts> getChargesAccountProcessingPendingPrintList(
			String clientId, String payby, String fromDate, String toDate);

	ArrayList<Accounts> getChargesAccountProcessingPaidPrintList(
			String clientId, String payby, String fromDate, String toDate);

	ArrayList<Accounts> getChargesAccountProcessingPrintList(String clientId,
			String payby, String fromDate, String toDate);

	String getClientFullName(String clientId);

	Accounts getInvoiceDetails(int invoiceid);

	boolean checkIsPaymentExist(String invoiceid);

	int updateDiscount(String invoiceid,String discount);

	int checkIsAdditionalCharge(int invoiceid);

	String getLedgrServiceIds(String name);

	String getledgerID(String serviceid,String pmode,String bnkname);

	double getLedgerBalance(String ledgerid);

	int saveLedger(String partyid, String product, String debit, String credit, double lbal, String ledgerid,
			String commencing,String invoiceid,int paymntid, String otherclientid, String procurementid, String proc_paymentid, String phar_billno, String phar_paymentid,int vendorid,int paymentvendorid, String location);

	boolean checkDiscEsist(String invoiceId, String ledgerid);

	double getledgerDebitAmount(String invoiceId, String ledgerid);

	int updateLedgerDebitDisc(String invoiceId, String ledgerid,double debit);

	ArrayList<Master> getLedgerBalanceList(String ledgerid);

	int updateLedgerBalance(int id, double balance);

	double getAheadLedgerBalance(String string);

	int updateInvoiceLedgerDebit(double sumofCharges, int invoiceid);

	int updateLedgerPaymentMode(String ledgerid, String paymode, String id);

	int updateIclosed(String id,String date);

	int updateIpost(String id,String date);

	ArrayList<Accounts> getHospitalRevenueList(String fromDate, String toDate, String status);

	ArrayList<Accounts> getRefundList(String id);

	double getRefundTotal(String invoicestr);

	int saveExpenceLedger(String partyid, String product, String ldebit, String credit, double lbal, String ledgerid,
			String lcommencing, String string, int i,int expnctype,String epayment,int parentid,int expenceid);

	String getExpenceLedgerId(String category);
	 int updateIpostToPost(String id,String date,String user);
	 int changeWard_in_apm_invoice_assesments(String id,String wardid);
	String getledgerIDNew(String serviceid, String string, String string2, String string3, int vendorid);

	 int changeWard_in_apm_invoice_assesments_byinvid(String id,String wardid);


	int getLedgerSheetId(String ledgerid, String procurementid, String colmnname);
	
	int updateMasterChargeInAsseessment(String id,String mastercharge);
	int isCompulasryConsultant(String chargetype);

	int updateofficicaledgerstatus(int saveledger);

	int updateLedgerSheetExpenceID(int saveledger, int expenceparentid);


	int updateInvoicePost(String date4, String userId, String podate);
	CompleteAppointment getChargeInvoiceData(String invoiceid);

	int updategivendisc(String invoiceid, double actualamt);

	int deletediscreq(String invoiceid);

	double getCreditAmount(double double1);

	ArrayList<Accounts> getPaymentList(int invoiceid);

	String getInvoiceDoctorid(int invoiceid);

	boolean checkInvoicerequset(String selectedInvoiceid);

	double getReportCreditAmount(int int1);

	boolean checkRefundAgainstInvoice(String selectedInvoiceid);

	int getMobStatusFromAppoitmentId(String appointmentid, String patientid);

	ArrayList<Accounts> gettransactionListNew(String string, String clientId, int prepid);

	Master searchChargeByCode(String searchByCode, String ipdtpid);

	int getMasterChargeIdFromName(String chargetype);

	void getChargeAssesmentEntry(int invoiceid);

	int updateremark(String invoiceid, String term);

}
