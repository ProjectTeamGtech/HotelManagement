package com.apm.Mis.eu.bi;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Expence.eu.entity.Expence;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.web.common.helper.LoginInfo;

public interface MisChartDAO {

	int getTotalOpdPatient(String fromdate, String todate);

	int getBookedAppointment(String fromdate, String todate);

	int getTotalDNA(String fromdate, String todate);

	int getTotalCompleted(String fromdate, String todate);

	int getIpdNewAdmission(String fromdate, String todate);

	int getInHousePatients(String fromdate, String todate);

	int getDischargePatients(String fromdate, String todate);

	int getTotalBeds(String fromdate, String todate);

	int getUnderMaintnanceBed(String fromdate, String todate);

	int getOccupiedBed(String fromdate, String todate);

	int getAvailableBed(String fromdate, String todate);

	int getChargeAddedd(String fromdate, String todate, String filter_ward);

	int getInvoiceGenerated(String fromdate, String todate,String invcategory,String jottitle, String filter_ward);

	int getPaymentrecieved(String fromdate, String todate,String invcategory,String jobtitle, String filter_ward);

	int getCashPayment(String fromdate, String todate,String invcategory,String jobtitle);

	int getChequepaymenyt(String fromdate, String todate,String invcategory,String jobtitle);

	int getCardpayment(String fromdate, String todate,String invcategory,String jobtitle);

	int getTotalPayment(String fromdate, String todate,String invcategory,String jobtitle);

	ArrayList<Expence> getExpenceCategoryList(String fromdate, String todate);

	ArrayList<Expence> getPaymentModeList(String fromdate, String todate);

	String getTotalExpence(String fromdate, String todate);

	int getTotalOpdSeen(String fromdate, String todate);

	int getPatientByCondiion(String fromdate, String todate);

	ArrayList<Client> getPaticlentByCondiion(String fromdate, String todate);

	int getRequestedPrescription(String fromdate, String todate);

	int getRequestedInvestigation(String fromdate, String todate);

	int getTotalPatientByCondition(String fromdate, String todate);

	ArrayList<Client> getpatientbyLocation(String fromdate, String todate);

	int getTptalPatient(String fromdate, String todate);

	ArrayList<Client> getOpdPatientListReport(String fromdate, String todate);

	ArrayList<Bed> getIpdPatientListReport(String fromdate, String todate);

	public long getAdvancedAmount(String fromdate, String todate);

	public long getRefundAmount(String fromdate, String todate);

	long getExpenseTotal(String fromdate, String todate);

	int getOtPatients(String fromdate, String todate);

	ArrayList<Client> getAllDailySummaryList(String fromdate, String todate);

	ArrayList<Client> getAllDailySummaryList1(String fromdate, String todate,LoginInfo loginInfo);

	ArrayList<Accounts> getAdvanceRefundList(String fromdate, String todate,LoginInfo loginInfo);

	ArrayList<Accounts> getAccountInfoList(String fromdate, String todate);

	ArrayList<Accounts> getAccountInfoList1(String fromdate, String todate);

    Client getAllYearReviewList();

	ArrayList<Expence> getAllExpenseList(String fromdate, String todate);

	long getAllOtherPayment(String fromdate, String todate);

	long getTotalNotCompleted(String fromdate, String todate);

	ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String practitionerId,
			String fromdate, String todate, String string, String string2);

	ArrayList<Client> getClinicalViewList(String fromdate, String todate);

	int getDDpaymenyt(String fromdate, String todate, String invoicecategory,
			String jobTitle);

	int getOtherpaymenyt(String fromdate, String todate,
			String invoicecategory, String jobTitle);

	ArrayList<Bed> getBedStatusReport(String fromdate, String todate);

	ArrayList<Investigation> getInvestigationDetails(String fromdate, String todate, String filter_ward, LoginInfo loginInfo);

	Product getAllTotalProcurment(String fromdate, String todate, String loc);

	Product getAllTotalIndent(String fromdate, String todate, String loc);

	int getIpdNewAdmissionNew(String today);

	ArrayList<Accounts> getPatientViewByPackage(String fromdate, String todate);

	Accounts getCashCheque(String fromdate, String todate);

	ArrayList<MisReport> getAllKPIList(String kpiarea_filter, String year_filter, String month_filter);

	int updateKPITempTable(String input1, String input2, String input3, String input4, String input5);

	String getCalKPIResult(MisReport misReport);

	Boolean checkKPIStatus(String kpiid, String kpi_year, String month);

	int updateKPIData(String input1, String input2, String input3, String input4, String input5, String final_result,
			String kpiid, String todate, String userid, String kpi_dataid, String target, String evidence);

	int insertNewKPIRecord(String input1, String input2, String input3, String input4, String input5,
			String final_result, String indicatorid, MisReport misReport, String kpi_year, String month, String target, String evidence);

	ArrayList<String> getKPIID();

	boolean getIsKPIDetailsAvailable(String kid, String year_filter, String month_filter);

	MisReport getKPIMasterData(String kpiid);

	ArrayList<MisReport> getAllGrahicalKPIList(String kpiarea_filter, String year_filter, String month_filter);

	//MisReport getIndiKPIData(String kpiid, String kpimonth, String year_filter);

	MisReport getIndiKPIData1(String kpiid, String kpimonth, String year_filter);

	MisReport getKPIData(String kpiid, String kpi_year, String kpi_month);

	boolean iskpiDataPresent(String kpiid, String kpi_year, String kpi_month);

	boolean iskpiSyytemGen(String kpiid);

	ArrayList<MisReport> getNABHSubCatList(String catid);

	ArrayList<MisReport> getAllSelfAssessmentToolList(String kpicat, String satyear, String satmonth);

	int getOtPatientsCounts(String fromdate, String todate);

	MisReport getOPDAppointment(String fromdate, String newdate, String apptypeid);

	ArrayList<MisReport> getAppointmentTypeID();

	int getTotalDeaths(String fromdate, String newdate);

	int getTotalDama(String fromdate, String newdate);

	int getTotalMLCAddmission(String fromdate, String newdate);

	int getInvestigationJobTitle(String fromdate, String todate, String string);

	String getInvestSecIdFromName(String string);

	int getInvestCountBySec(String ctid, String fromdate, String newdate);

	ArrayList<MisReport> getOPDAppointmentList(String fromdate, String newdate, String string);

	ArrayList<MisReport> getipdnewadmissionlist(String fromdate, String todate);

	ArrayList<MisReport> getInHousePatientsList(String fromdate, String todate);

	MisReport getYearWiseData(String kpi_year, String string);
	MisReport getOPDAppointmentforpreview(String fromDate, String toDate, String string, String diaryuser);

	int getOPDCompletedAppointment(String fromdate, String todate);

	int getOPDdnaAppointment(String fromdate, String todate);

	ArrayList<String> getInHousePatientList(String todate, String todate2);

	ArrayList<String> getIpdNewAdmissionList(String todate, String todate2);

	String getDischargePatientList(String newdate, String newdate2);

	ArrayList<String> getDischargePatientList1(String newdate, String newdate2);

	int getInHousePatientsWardWise(String todate, String todate2, String ipdids, String wardid);

	int getAvailableBedWardWise(String date, String date2, String ipdids, String wardid);

	String getInHousePatientsWardWiseIds(String todate, String todate2, String ipdids, String wardid);

	int getInHousePatientsPractWise(String todate, String todate2, String ipdids, String wardid);

	String getInHousePatientsPractWiseIds(String todate, String todate2, String ipdids, String practid);

	boolean checkMISAccess(String androidpractuserid);

	int getCasualityCount(String fromdate, String todate);

	int getDayCareCount(String fromdate, String todate);

	MisReport getOPDAppointmentCountforpreview(String fromDate, String toDate, String string, String diaryuserid);

	ArrayList<Client> getOpdPatientCancelListReport(String fromdate, String todate);

	int getOPDcancelcountAppointment(String fromdate, String todate);

	MisReport getTotolRevenueForGraph(String newfromdate1, String newtodate1);

	ArrayList<Client> patientList(String fromdate, String todate, String type );

	int getNewRegistration(String fromdate, String todate);

	int getNewRegistrationGenderWise(String yesterday, String yesterday2, String string);

	double getDOBWisePatientReg(String yesterday, String yesterday2, String currentdate, String upto20year);

	int saveMainDashboardData(String yesterday, int yesterday_admission, int yesterday_discharge, int total_inhouse,
			int total_vacant_bed, int totalopdseen, int totalopdcompleted, int totalopddna, double totalmale,
			double totalfemale, double upto20, double upto40, double upto60, double above60);

	boolean checkMaindashboardDataSaved(String newdate);

	MisReport getMainDashboardData(String newdate1);

	int getDischargePatientsWardWise(String date, String date2, String wardid);
	
	int countOfReDosOfLabTests(String fromdate, String todate);
	int countOfOfLabTests(String fromdate, String todate);
	String getFunctionNameOfIndicator(String kpiId);
	int checkKPIAvailable(int kpiid, String kpi_year, String month);
	double getRescheduledOTPercentage(String fromdate, String todate);
	int getTotalRescheduledOTs(String fromdate, String todate);
	int getTotalOTs(String fromdate, String todate);
	
	int getICUInpatientCount(String fromdate, String todate);
	int getICUUtilizationRate(String fromdate, String todate);
	int getICUBedCount();
	
	int getIPDInpatientCount(int wardid,String fromdate, String todate);
	int getBedCount(int wardid);

	MisReport getPhysioOPDAppointmentCountforpreview(String fromDate, String toDate, String string, String diaryuserid);

	int getUpipayment(String fromdate, String todate, String invoicecategory, String jobTitle);

	ArrayList<Bed> getWardWiseData(String fromdate, String todate);

	int saveWardwiseMainDashboardData(String yesterday, int yesterday_admission, int total_inhouse1,
			int total_vacant_bed1, int i);

	int getInHousePatients1(String todate, String todate2, int id);

	int getIpdNewAdmission1(String todate, String todate2, int id);

	int getDischargePatients1(String todate, String todate2, int id);

	int getAvailableBed1(String date, String date2, int id);

	int getDocwiseTotalOpdPatient(String fromdate, String todate, int id);

	int getDocwiseBookedAppointment(String fromdate, String todate, int id);

	int getDocwiseOPDCompletedAppointment(String fromdate, String todate,int id);

	int getDocwiseOPDdnaAppointment(String fromdate, String todate,int id);

	int getDocwiseOPDcancelcountAppointment(String fromdate, String todate,int id);

	int getDocwiseOtPatientsCounts(String fromdate, String todate, int id);

	int getTotalRevnue(String fromdate, String todate, int id);

	int getInvestigationRevenue(String fromdate, String todate);
	
}
