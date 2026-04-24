package com.apm.Report.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.common.utils.Pagination;

public interface ClientReportDAO {

	ArrayList<Client> getClientList();

	ArrayList<Client> getCurrentTreatmentNoFutureApmtsList();

	ArrayList<Client> getNoActivityRecordOfClientList();

	ArrayList<Client> getDNANoFutureAppReport(String practitionerId);

	ArrayList<Client> getNoActivityRecordList(String practitionerId);

	int getTotalClientListCount();

	ArrayList<Client> getClientReportList(String tpid,String location,String fromdate,String todate,String payby,String diaryUser,String orderby,String order);

	int getTotalNoApptActivityCount();

	ArrayList<Client> getNoActivityRecordOfClientList(Pagination pagination);

	int getTotalNoActivityCount(String practitionerId);

	ArrayList<Client> getNoActivityRecordList(String practitionerId,
			Pagination pagination);

	ArrayList<SummaryReport> getIpdConditionList(String condition1,
			String condition2, String condition3, String fromdate, String todate,Pagination pagination, String city);

	ArrayList<SummaryReport> getOpdConditionList(String condition1,
			String condition2, String condition3, String fromdate, String todate,Pagination pagination, String city);

	int getIpdConditionCount(String condition1, String condition2,
			String condition3, String fromdate, String todate, String city);

	int getOpdConditionCount(String condition1, String condition2,
			String condition3, String fromdate, String todate, String city);

	double getIpdInitialAssesmentPer();

	double getCasualityAssesmentPer(String fromdate, String todate);

	double getNursingCarePlanDocument(String fromdate, String todate);

	ArrayList<MisReport> getKPIAreaList();

	ArrayList<Report> getKPIIndicatorList(String areaid, String catagoryid, String subcatagorylist);

	Report getMasterIndicator(String s);

	int saveKPIData(Report report);

	int updateIndicatorMasterData(String s, String status);

	int getIsKPIAlreadyExist(Report report);

	ArrayList<String> getKPIIndicatorId();

	ArrayList<MisReport> getSms_Template();

	MisReport getSmsTemplate(String tempid);

	boolean istemplateExist(String smtemplatename);

	int saveSMSTemplate(String smtemplatename, String message, String subject);

	int saveIndicator(String areaid, String indicator, String formula, String formula_desc, String no_of_input);

	ArrayList<Report> getPatientTodayBList(String dobdate, String sendtype);

	ArrayList<Report> getEmployeeTodayBList(String dobdate, String sendtype);

	ArrayList<Report> getPatientThirtPartyList(String thirdpartynameid, String sendtype);

	ArrayList<Report> getPatientDRReferedByList(String refferedby, String sendtype);

	ArrayList<Report> getPatientNotEorMList(String mobileoremail, String sendtype);

	ArrayList<Report> getUserRoleorDepartList(String jobtitle, String specialityid, String sendtype);

	ArrayList<Report> getPatientInvtList(String invtname, String greaterthanid, String lessthanid, String sendtype);

	int saveMarketingSendHistory(String subject, String message, String allmobileno, String sendtype,
			String allemailid, String todate, String userid);

	ArrayList<Report> getMarketingSendList(String fromdate, String todate, String history_typefilter);

	ArrayList<Report> getPatientMedGivenList(String mdicinename, String medafterdate);

	ArrayList<MisReport> getKPIAreaList(String string);

	ArrayList<Report> getListOfEmployeeForSMS(String sendtype);

	ArrayList<Report> getListOfPatientForSMS(String sendtype, String clientType);

	ArrayList<Client> getsmsLogReportlist(String fromDate, String toDate, String patientname, String mobile);

	ArrayList<Client> getClientReportList1(String thirdParty, String location, String fromDate, String toDate,
			String payby, String diaryUser, String orderby, String order);

	ArrayList<Client> getClientselfpaymentList(String fromDate, String toDate, String clientid);

	ArrayList<Client> getClientReportTPList(String thirdParty, String location, String fromDate, String toDate,
			String payby, String diaryUser, String orderby, String order);

	ArrayList<Client> getClientTPpaymentList(String fromDate, String toDate, String clientid1);

	ArrayList<Client> getClientTPdebitlist(String clientid);

	ArrayList<Client> getClientSelfdebitlist(String clientid);

	DiaryManagement getsmtpData();

	int saveMarketingSendHistory1(String subject, String notes, String string, String string2, String allemailds,
			String todate, String userId, DiaryManagement diaryManagement);

	

}
