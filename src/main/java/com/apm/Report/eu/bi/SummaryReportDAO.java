package com.apm.Report.eu.bi;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Report.eu.entity.Report;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.Report.web.form.SummaryReportForm;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface SummaryReportDAO {

	ArrayList<SummaryReport> getDNAAnalysisReport(String fromDate,String toDate);

	ArrayList<SummaryReport> getDNAAnalysisByPractitioner(int practitionerId,String fromDate, String toDate);

	ArrayList<SummaryReport> getAppKeptDNAList(String fromDate, String toDate);

	ArrayList<SummaryReport> getAppKeptDNAList();

	

	ArrayList<SummaryReport> getReferalList();

	ArrayList<SummaryReport> getTreatmentByReferral(int referalId);

	ArrayList<SummaryReport> getReptOutStatndingList(String fromDate,
			String toDate);

	ArrayList<SummaryReport> getNewPtsList(String fromDate, String toDate,String clinicName);

	ArrayList<SummaryReport> getDnaOtsList(String fromDate, String toDate);

	

	ArrayList<SummaryReport> getTreatmentReferralList(String payby,
			String diaryUser, String thirdParty, String gpid, String condition,
			String referal, String fromDate, String toDate);

	

	ArrayList<SummaryReport> getTotalPtsList(String fromDate, String toDate,
			String clinicName, String payby, String diaryUser, String location,
			String thirdParty, String condition, String apmtType);

	ArrayList<SummaryReport> getReturingPtsList(String fromDate,String toDate,String diaryUser,
			String location, String condition, String sourceOfIntro,
			String referal, String orderby, String order);

	ArrayList<SummaryReport> getODReportList(String fromDate, String toDate,
			String payby, String diaryUser, String location, String thirdParty,
			String dischrgeOutcomes, String dischargeStatus, String discharge, String ipdopd,String balancelimit, int i, String admitordischrage, LoginInfo loginInfo);

	ArrayList<Accounts> getChargesShareList(String fromdate, String todate, String practitionerId, String clientid);

	double getSelfOpdCharge(String string, String fromdate, String todate, String type, int whopay);

	double getAdvanceAmount(String fromdate, String todate, String string);

	ArrayList<Accounts> getBillingReport(String fromdate, String todate, String itype, String payee);

	ArrayList<Accounts> getDiscountReport(String fromdate, String todate,String itype);

	ArrayList<Accounts> getCancelInvoiceReport(String fromdate, String todate,String ipdopdfilter);

	ArrayList<Accounts> getChargeReportDepartmentWise(String fromdate, String todate);

	String getDepartmentWiseDebit(String fromdate, String todate, String string);

	int getTotalBeds(String string);

	ArrayList<NotAvailableSlot> getTotalOTSurgeries(String fromdate, String todate);

	String getNetDepartmentWiseDebit(String fromdate, String todate, String string, int i);

	ArrayList<Accounts> getDailyIPDAdmsDischList(String fromdate, String todate);

	ArrayList<Accounts> getPaymentReportList(String fromdate, String todate, String itype, String howpaid);



	ArrayList<String> getPaymentreceiveUserid(String fromdate, String todate);

	String getTotalPaidByUser(String fromdate, String todate, String userid);

	String getTotalAdvanceByUser(String fromdate, String todate, String userid);

	String getTotalRefundByUser(String fromdate, String todate, String userid);

	String getTotalPaymentPaymode(String fromdate, String todate, String userid, String string);

	String getTotalAdvancePaymode(String fromdate, String todate, String userid, String string);

	String getTotalRefundPaymode(String fromdate, String todate, String userid, String string);

	ArrayList<SummaryReport> getCurrentPatientsList(String diaryUser, String ward, String fromDate, String toDate,String issecondrey,int days,String orderbypract, int i, String paymentmode,int ismlc, String location);

	ArrayList<SummaryReport> getWardlist();

	ArrayList<SummaryReport> getDeathReportList(String fromDate, String toDate, String payby, String diaryu,
			String string, String thirdParty, String string2, String string3, String string4, String string5,
			String balancelimit);
	String getUserNamebyId(String id);

	Ipd getBedOccupancyrate(String fromdate, String todate);

	ArrayList<SummaryReport> getBedOccupancyList(String fromdate, String todate);

	ArrayList<SummaryReport> getMLCReportList(String fromDate, String toDate, String payby, String diaryu,
			String string, String thirdParty, String string2, String string3, String string4, String string5,
			String balancelimit);

	ArrayList<SummaryReport> getRefferedByList(String opdipd, String diaryUser, String refferdby, String fromDate,
			String toDate, String admissionOrdischarge);

	ArrayList<SummaryReport> CalculateBedOccupancyList(String fromdate, String todate, String yearmonthh);
	
	ArrayList<SummaryReport> getPatients(String fromDate, String toDate,String  dept,  String type,  String city); 
	 ArrayList<SummaryReport> getdepartments();
	 ArrayList<SummaryReport> getallLocations();
	 ArrayList<Accounts> getOpdIpdConversionRevenue(String fromdate, String todate, String netipddebitcount);

	ArrayList<SummaryReport> getDepartmentWiseRevenue(String fromdate, String todate, String newtodate,String isopdipd);
	SummaryReport getdeptcountlistopd(String deptid,String fromdate, String todate, LoginInfo loginInfo);
	SummaryReport getdeptcountlistipd(String deptid,String fromdate, String todate);
	SummaryReport getdeptcountlistot(String deptid,String fromdate, String todate);
	ArrayList<Investigation> getLabreport(String fromDate,String toDate,String Testname,String abovebelow, String value);
	ArrayList<Investigation> getNewInvestigationReport(String fromDate,String toDate,String investigationType);
	ArrayList<SummaryReport> getnewIpdCurrentPatientlist(String fromDate , String toDate, String practitioner,String admitted);

	String getTotalItypePayment(String fromdate, String todate, String userid, int i);
	SummaryReportForm getTotaldataOfRevenueReport(String fromdate,String todate);

	ArrayList<Accounts> getShareChargeList(String fromDate, String toDate, String practitionerId, String clientid);
	ArrayList<Investigation> getinvstPkgList(String fromDate, String toDate, String practitionerId, String pkgid);
	
	ArrayList<Product> getallstockreportlist(String fromdate, String todate  );

	ArrayList<Accounts> getInvoiceReportDepartmentWise(String fromdate, String todate);
	
	ArrayList<SummaryReport> getpractshareopdnew(String fromdate, String todate);
	
	ArrayList<SummaryReport> getwardwiseavgstayReport(String fromdate, String todate);
	
	int insertRevenueData(SummaryReportForm summaryReportForm);
	
	int deleteRevenueData(String date);
	
	ArrayList<SummaryReport> getIPDReportDetailed();
	ArrayList<SummaryReportForm> getTotalRevenueList(String fromdate,String todate);
	ArrayList<SummaryReport> getIPDreportTPWisePatients();
	ArrayList<SummaryReport> getPackageReportList(String fromdate, String todate);
	ArrayList<SummaryReport> getIPDOPDInvestigationRevenue(String fromdate, String todate, String type, String practitionerId, String itype, String search);
	String getInvoiceTypeByitype(int id);
	ArrayList<Accounts> getPaymentRepportNoteList(String fromdate, String todate, String status, LoginInfo loginInfo);

	ArrayList<Accounts> getDiscounchargetReport(String fromdate, String todate,String itype);

	int getOtReportCount(String fromDate, String toDate, String otroom, String otuser, String anesthesia);

	ArrayList<SummaryReport> getOtReport(String fromDate, String toDate, Pagination pagination, String otroom, String otuser, String anesthesia);



	ArrayList<SummaryReport> getRefferedByOPDList(String opdipd, String diaryUser, String refferdby, String fromDate,
			String toDate);

	ArrayList<Accounts> getPaymentReciptReportList(String fromDate, String toDate, String howpaid, String itype, String paymentStatus, String amountgreaterfilter);

	ArrayList<SummaryReport> getNosOfadmdischarge(String fromdate, String todate, String newtodate);

	ArrayList<SummaryReport> getipdrevenueRevenue(String fromdate, String todate, String newtodate);
	ArrayList<SummaryReport> getwardwiserevenueReport(String fromdate, String todate);
	ArrayList<SummaryReport> getdeptwiseOtReport(String fromdate, String todate);
	String getDepartmentRevenueMonthly(String newfromdate, String newtodate, String department,String isipdopd);

	Accounts getInvoiceBalDebitDetails(String fromdate, String todate, String userid);
	ArrayList<Accounts> pcsGSTReport(String fromdate, String todate, String clientId);

	String getPatientIPDAbrivationId(String string);
	ArrayList<Client> getBirthPlaceListData(Pagination pagination, Client client, String fromDate, String toDate, String order);
	int getBirthPlaceListDataCount( Client client, String fromDate, String toDate, String order);

	ArrayList<SummaryReport> getDepartmentWiseRevenueForLmh(String fromdatelmh, String todatelmh, String newtodate, String ipdopd, String masterchargetype);

	ArrayList<SummaryReport> getpaymentlist(String invids);

	SummaryReport getnewPatientcount(String deptid, String fromDate, String toDate);

	SummaryReport getoldPatientcount(String deptid, String fromDate, String toDate);

	ArrayList<Accounts> getPaymentModelist(String fromdate, String todate, String howpaid, String itype,
			String paymentStatus, String amountgreaterfilter);

	ArrayList<Accounts> getAdvancelist(String fromdate, String todate, String howpaid, String itype,
			String paymentStatus, String amountgreaterfilter);

	double getRefundOnly(String fromDate, String toDate, int i, String howpaid, String itype, String paymentStatus,
			String amountgreaterfilter);

	ArrayList<Accounts> getDepartmentWiseDiscounchargetReport(String fromdate, String todate, String invTpe,
			String patientcategory, String dept, String searchtext, int id, double billdisc);

	ArrayList<Accounts> getDiscountlist(String fromdate, String todate, String howpaid, String itype,
			String paymentStatus, String amountgreaterfilter);

	ArrayList<Accounts> getinvoicelist(String fromdate, String todate, String invTpe, String patientcategory,
			String dept, String searchtext);

	
}
