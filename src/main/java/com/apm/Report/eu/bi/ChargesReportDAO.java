package com.apm.Report.eu.bi;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Master.eu.entity.Master;
import com.apm.Report.eu.entity.ChargesReport;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface ChargesReportDAO {

	ArrayList<Accounts> getChargesReportList(String fromDate, String toDate,String payby,String tpid,String invoiceStatus,String oredrby,String order,String condition);

	ArrayList<Accounts> getInvoiceReportList(String fromDate, String toDate,String payby,String paymentStatus,String thirdparty,String orderby,String order,String invcategory,String invoicetype, LoginInfo loginInfo);

	ArrayList<Accounts> getPaymentReportList(String fromDate, String toDate,String payby,String howpaid,String orderby,String order,String invcategory,String userid,String selectedInvctype, String sortfilter);

	int moveInvoiceToScecondary(String hdnprimaryinvoice);

	ArrayList<Accounts> getAuditorsInvoiceReportList(String fromDate,
			String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order,
			String invoicecategory,String invcetype);

	ArrayList<Accounts> getAdvanceInvoiceReportList(String fromDate,
			String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order,
			String invoicecategory, String invcetype,String userid);

	ArrayList<Accounts> getDoctorShareReport(String fromdate, String todate,String practitionerId,String jobtitle);

	NotAvailableSlot getOtAppointmentDetails(int parseInt);

	
	ArrayList<Accounts> getPaymentModeWiseList(String fromDate, String toDate,
			int i, String payby, String howpaid, String invoicecategory,
			String userid);

	ArrayList<Accounts> getAdvPaymentModeWiseList(String fromDate,
			String toDate, int i, String payby, String howpaid,
			String invoicecategory, String userid);

	ArrayList<Accounts> getSmallPaymentReportList(String fromDate,
			String toDate, String payby, String howpaid, String orderby,
			String order, String invoicecategory,String userid, String selectedInvctype,LoginInfo loginInfo);

	double getRefForInvoicet(String fromDate, String toDate, int i,
			String payby, String howpaid, String invoicecategory, String userid);

	double getRefOnly(String fromDate, String toDate, int i, String payby,
			String howpaid, String invoicecategory, String userid);

	ArrayList<Accounts> getAdvancePaymentReportList(String fromDate,
			String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order,
			String invoicecategory, String string, String userid, String paymode,String shiftedFromCancel, String howpaid);

	ArrayList<Accounts> getRefundPaymentReportList(String fromDate,
			String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order,
			String invoicecategory, String string, String userid, String selectedInvctype, String paymode);

	ArrayList<Master> getAccountUserList();

	int saveMisReportLog(String string, String userId, String fromDate, String toDate,String date,String string1);

	ArrayList<Accounts> getChargesReportDeatiled(String fromDate, String toDate, String payby, String tpid,
			   String invoiceStatus, String orderby, String order, String jobtitle, String apmttype,String ChargesType,String user,String opdipd ,String clientid,String searchinv, String dept);


	public ArrayList<Accounts> getpaymentreciptlist(String fromdate, String todate, String paymenttype, String userid,String invoicetype);
	
	int  getcountofinvoice(String fromdate, String todate, String paymenttype, String userid,String invoicetype);

	
	ArrayList<Client> getallInvoicedListOfClient(String fromdate, String todate);

	ArrayList<Accounts> getCreditBalanceReportList(String fromDate, String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order, String invoicecategory, String string, String userid);

	int dropAssesmentView();

	int createAssesmentView(String fromDate, String toDate);

	double getRequestedDiscountAmt(String fromDate, String toDate, String userid, int i);

	ArrayList<NotAvailableSlot> getDistlevelopdcount(String fromDate, String toDate);


	ArrayList<Accounts> getRefundPaymentReportListIpdOpd(String fromDate,
			String toDate, String payby, String paymentStatus,
			String thirdParty, String orderby, String order,
			String invoicecategory, String string, String userid,int isipdopd, String selectedInvctype,String paymode, boolean islmh);

	String getPhysicalpaymentIdbyinvoiceid(int payid);

	double getPaymentReportInvoiceAmt(String invoiceids);

	ArrayList<Accounts> getInvoiceReportListForPaymentReport(String fromDate, String toDate, String payby,
			String howpaid, String orderby, String order, String invoicecategory, String userid,
			String selectedInvctype, String sortfilter,LoginInfo loginInfo);

	String getTpname(int int1);

	String getClientFullName(String string);

	int getNoOfCharges(int int1);

	double getCreditAmount(double double1);
	public ArrayList<Accounts> creditInvoiceReportList(String fromDate, String toDate, String payby,
			String paymentStatus, String thirdParty, String orderby, String order, String invoicecategory,
			String string, String userid); 
	String getChargeDepartmentName(int id);

	String getDepartmentName(int int1);

	ArrayList<Client> getChargeTypeList();

	ArrayList<Accounts> getDeptPaymentReportList(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invoicecategory, String userid, String selectedInvctype,
			String sortfilter, String chargeType, String condition, String tpid, LoginInfo loginInfo);

	Map<Integer, Accounts> getInvoiceDetailsList(String fromDate, String toDate, String clientid);

	ArrayList<Accounts> getChargesReportDetailedList(Map<Integer, Accounts> invoiceDetailsList, String ids);

	ArrayList<Accounts> getChargesReportDetailedLists(String fromDate, String toDate);

    String getInvoiceFinacialSequence(String fromDate, int i, String string ,String toDate, LoginInfo loginInfo);

	String getAdvanceInvoiceFinacialSequence(String fromDate, int i, String string, String toDate ,String userid);

	String getOpdRefundReport(String fromDate, String string, String toDate ,int selectedInvctype, LoginInfo loginInfo);

	String getIpdRefundReport(String fromDate, String string, String toDate, int i, LoginInfo loginInfo);

	int getSittingAndFollowUpCount(String fromDate, String toDate, String department, String patientCategory, String sittingOrFollowUp, String sittingName, String proceduerMasterName, String procedureName);

	ArrayList<NotAvailableSlot> getSittingAndFollowUpList(String fromDate, String toDate, String department,
			String patientCategory, Pagination pagination, String sittingOrFollowUp, String sittingName, String proceduerMasterName, String procedureName);

	int getProcedureCount(String fromDate, String toDate,String patientCategory,String sittingOrFollowUp,String sittingName,String proceduerMasterName,String procedureName,String department );

    Vector<Accounts> getDeptPaymentReportList1(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invoicecategory, String userid, String selectedInvctype,
			String sortfilter, String chargeType, String condition, String tpid, LoginInfo loginInfo);

	Vector<Accounts> getchargesPaymentlistbyinvid(String inv1, String fromDate, String toDate, String outsource, String selectedInvctype, String chargeType, String condition, String tpid, String payby, String howpaid, LoginInfo loginInfo);

	ArrayList<NotAvailableSlot> getDailyOpdColorList(String fromDate, String toDate);

	ArrayList<NotAvailableSlot> getIpdSittingAndFollowUpList(String fromDate, String toDate, String department,
			String patientCategory, Pagination pagination, String sittingOrFollowUp, String sittingName,
			String proceduerMasterName, String procedureName, String deptid);

	Vector<Accounts> getDeptPaymentReportListForLmh(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invoicecategory, String userid, String selectedInvctype,
			String sortfilter, String chargeType, String condition, String tpid, LoginInfo loginInfo);

	ArrayList<NotAvailableSlot> getMainIpdSittingAndFollowUpList(String fromDate, String toDate, String department,
			String patientCategory, Pagination pagination, String sittingOrFollowUp, String sittingName,
			String proceduerMasterName, String procedureName);

	int getIpdSessionCount(String fromDate, String toDate, String department, String patientCategory,
			String sittingOrFollowUp, String sittingName, String proceduerMasterName, String procedureName);

	ArrayList<Accounts> getIpdSmallPaymentReportList(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invoicecategory, String userid, String string, LoginInfo loginInfo, String itype);

	ArrayList<Accounts> getIpdSmallPaymentDataList(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invcategory, String userid, String selectedInvctype,
			LoginInfo loginInfo, String masterchargetype, String itype);

	ArrayList<Accounts> getMainSmallPaymentList(String fromDate, String toDate, String payby, String howpaid,
			String orderby, String order, String invoicecategory, String userid, String string, LoginInfo loginInfo);

	ArrayList<Accounts> getDeptlist(String fromDate, String toDate, String department);

	ArrayList<Accounts> getDeptPaymentReportListForNkp(String fromDate, String toDate, String null1, String howpaid,
			String orderby, String order, String invoicecategory, String userid, String selectedInvctype,
			String sortfilter, String chargeType, String condition, String tpid, LoginInfo loginInfo, String cliidss);

	ArrayList<Accounts> getchargesPaymentlistbyinvidforNkpphysio(String inv1, String fromDate, String toDate,
			String outsource, String selectedInvctype, String chargeType, String condition, String tpid, String payby,
			String howpaid, LoginInfo loginInfo);

	double getNetAdvance(String fromDate, String toDate);

	ArrayList<Accounts> getInvestigationDetailedRevenueRpt(String commencingDate1, String commencingDate12,
			String type, String string, String itype, String search, LoginInfo loginInfo);
}
