package com.apm.Emr.eu.bi;

import java.util.ArrayList;

import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.web.action.Template;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Emr.eu.entity.Cbc;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Emr.eu.entity.InvstTemplate;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

import oracle.jdbc.util.Login;

public interface InvestigationDAO {

	ArrayList<Master> getGroupList(String selectedid);

	ArrayList<Master> getNameList(String selectedid);

	ArrayList<Master> geSpecimenList(String selectedid);

	ArrayList<Master> geReportList(String selectedid);

	ArrayList<Master> getUnitList(String selectedid);

	int saveParentInvestigation(Investigation investigation,
			String ukCurrentDataTime,String sessionadmissionid, String invsttypeid,int invrequest, LoginInfo loginInfo);

	int saveInvestigation(Investigation investigation2,
			String ukCurrentDataTime, int saveparent, String clientId,
			String prectionerid, String conditionid);

	ArrayList<Investigation> getParentPriscList(String clientId,
			String prectionerid, String conditionid);

	Investigation getEditInvestigation(String selectedid);

	ArrayList<Investigation> getSelectedInvestigationData(String selectedid);

	int deleteInvestigationData(String selectedid);

	int deleteParentData(String selectedid);

	Investigation getInveStigationDetails(String selectedinvestigationnameid);

	ArrayList<Investigation> getViewInvestigationList(Pagination pagination,String searchText,String jobtitle, String invsttype, String fromdate, String todate,LoginInfo loginInfo, String filter_status, String filter_ward, String invstsecid, String outsource,String isdeleted);

	int updateReportData(int id, String obsvalue, String finding1, String finding2, String finding3, String finding4);

	int updateWriteupData(int id, String findings, String biorefrange,
			String methods);

	ArrayList<Investigation> getInvestigationNameList(String invstname);

	ArrayList<Master> getCbcIdList();

	Cbc getCbcReportData(String cbcid);

	int insertGPDetails(String doctorname,String diciplinename);

	int UpdateUpStatus(int id);

	int UpdateUpStatus(String editinvstparentid,int userid,String status);

	int getAllInvestigationCount(String searchText,String jobtitle, String invsttype, String fromdate, String todate, String filter_status, String filter_ward, LoginInfo loginInfo, String invstsecid, String outsource,String isdeleted);

	ArrayList<Investigation> getPrintSelectedInvestigationData(String selectedid);

	String getPrintInvsttype(String selectedid);

	String getAttchmentFile(String selectedid,String invsttype);

	int updateReportType(int parentid,String reporttype);

	ArrayList<Emr> getInvstDocList(String selectedid);

	Investigation getInvestigationTyeID(String selectedid);

	Investigation getInvstTypeCharges(String invsttypeid);

	ArrayList<String> getinvstClientChargeList(String selectedid);

	Investigation getClientInvstChargeDetails(String str);
	Investigation getSelectedInvestigationClient(String selectedid);

	int updateApproveStatus(String status,String id);

	String getConditionDepartment(String conditionid);


	int saveTemplateParentInvestigation(Investigation investigation,
			String currentDataTime, String sessionadmissionid,
			String invsttypeid,int templateid);

	int saveTemplateInvestigation(Investigation investigation2,
			String currentDataTime, int saveparent, String clientId,
			String prectionerid, String conditionid);

	int saveInvTemplateName(String templatetext, String currentDataTime);

	ArrayList<InvstTemplate> getTemplateList();

	ArrayList<Investigation> getInvstNameList(String templateid);
	public ArrayList<UserProfile> getAllInvestigationUsers(int clinicid);
	
	public String getGraphDateList(String clientid,String invstname,String fromdate,String todate);
	
	public String getGraphValueList(String clientid,String invstname,String fromdate,String todate);
	
	public String getGraphinvstTypeName(String clientid,String invstname,String fromdate,String todate);

	ArrayList<Client> getPaymentClientList(String searchText, String fromDate,
			String toDate,LoginInfo loginInfo);

	int updateInvsInvoiceId(String invsparentid, int chargeInvoiceid,String userid);
	public int getIpdIdFromInvestigation(String id);

	int getifInvoiceCreated(String invsparentid);

	AppointmentType getInvestigationPaybyCharge(String whopay,String invstname,String tpid,int clientid,LoginInfo loginInfo);

	int checkRoundCharge(String invstype);

	String getMasterChargeName(String invstype);

	ArrayList<String> getRequestedInvestigationList(String curdate,String ipdclientid,String ipdinvreq);

	int updateDateOFInvestigation(String editinvstparentid, String approved,String datetime, String userid);

	int updateCollectDateandStatus(String id, String date);

	ArrayList<Master> getInvestigationSectionList();

	ArrayList<Master> getInvTypeList(String sectionid);

	ArrayList<Master> getMasterInvstTypeList(String sectionid, String clientid,String fromdate,String todate,String reporttype,String selectedid,String invreq);

	String getInvSectionName(String sectionid);

	int updateRemark(String editinvstparentid, String remark);

	ArrayList<Master> getAllInvestigationTemplateList(String sectionid);

	String getInvsTemplateData(String id);

	Master getInvSectionDetails(String sectionid);

	String getInvstPrintbr(String invsttypeid);

	String getGpDepartment(String gpid);

	String getInvsJobtitle(String invsttypeid);

	int saveInvRequest(String ukCurrentDataTime);
	int cancelInvestigation(String selectedid, String userid);

	int saveUpDeleteInvestigation(String selectedid, String delete_reason, String userid, String loc);

	int checkChargeRaised(int id);

	int getChargedInvoiceid(int id);

	int checkInvoiceCreated(int chargeid);

	String getInvestType(int id);

	String getInvstigationSectionId(int id);

	String getInveReqId(int id);

	boolean checkAttachment(int id);

	ArrayList<Investigation> getAprovedInvestigation(String ipdid);

	int updateInvestiSeenStatus(String ipdid);

	public ArrayList<Master> getInvestigationList(String clientid, String fromDate,
			String toDate,LoginInfo loginInfo);
	ArrayList<Investigation> getIPDInvestList(String ipdid);

	ArrayList<Master> getDepartmentinvList(String invreq);

	Investigation getInvChargeInfo(int invrequest);

	ArrayList<Master> getInvPaksLists();

	int getTpFollowupid(String tpid);

	int getAppliedPkgID(String selectedid);

	String getPkgCharge(int pkg, String str);

	ArrayList<Master> getPackageInvTypeList(String sectionid);

	int setPathTestingInDB(Cbc c);

	int getPkgIDFromInvID(String ginvstid);

	ArrayList<Investigation> getMobApiInvsList(String clientid);

	String getInvereqDate(int invrequest);

	ArrayList<Investigation> getSavedInvestigationList(String saveid);

	int updateInvestigationOutsource(String invid, String val, String userid, String date);

	ArrayList<Investigation> getOutSourceList();

	ArrayList<Investigation> getInvestiCriticalValue(String editinvstparentid);

	ArrayList<Investigation> getOutSourceList(String outsourceid);

	Investigation getInvestigationDetails(String inparentid);

	String getOutSourceName(String outsourceid);

	int updateInvesOutsourceNew(String invid, String userid, String date, String oshandoverto,
			String oshandoverfrom, String isreturnOS, String oshandovertostatus, String expdate);

	ArrayList<Investigation> getOutsourceReport(String fromDate, String toDate,String outsrclabid ,String dept, String clientId, String invstTypeId);

	ArrayList<Investigation> getInvestiCriticalValueNew(String editinvstparentid);

	ArrayList<Investigation> getallinvsttypewisecountrpt(String fromdate,String todate);
	
	ArrayList<Investigation> getallInvestigationincome(String fromdate,String todate,String practioner,String dept);
	
	String getClientdtails(String uhid);
	Investigation getcountoofinvsttype(String fromdate,String todate , String invstype);
	ArrayList<Investigation> getInvestigationsFrompakages(String reqid,String pkgid);
	ArrayList<ThirdParty> getTpListofInvestigation();
	String getInvestTypelistByTpid(String tpid);
	
	String getShortnameofTP(String tpid);
	int createColofInvst(String column);
	int updateTPInvsetigationName(String tpinvname, String id,String col);

	String getInvdepartment(String invsttype);
	String getDefaultRemark(String invsttypeid);
	int setChargesRaisedStatus(String invstid);
	ArrayList<Investigation> getinvestrevenuenCountReport(String fromdate,String todate);
	Investigation getApmtTypeName(String id);
	int updateInvstDate(String invreqid, int invesid,String date);
	int updateapmtAsssInvstDate(String chargename, String invreqid,String date);
	int saveChargesToInvestigation(String charge,String invstid,String chargeid, String invreqid);
	String getListOFIpdInvestigation(String ipdid);
	ArrayList<Investigation> getAllInvestigationRepoerTypeWise(String clientid,String ipdid);

	ArrayList<Investigation> getInvestigationTat(String fromDate, String toDate, String invsttype, String filter_status, String filter_ward, String isdeleted, Pagination pagination, LoginInfo loginInfo);

	int getInvestigationTatcount(String fromDate, String toDate, String invsttype, String filter_status, String filter_ward, String isdeleted, LoginInfo loginInfo);

	Investigation getparentdata(int saveparent);

	ArrayList<Master> getDepartmentinvListPrint(String invreq, String deptid);

	String getdeptinvid(String invreq);
	
	String getInvestigationInIpdList( String ipdid);
	int updateCompStatusAndDate(String parentId,String datetime);

	double getoutsourceAmount(String invid, String val, String invtypeid);

	int updateOutsourceChargeAmount(String invreq, double charge);
	int getinvestigationIdByApmtName(String apmtName, String invstReqId, int pkgid);
	ArrayList<Investigation> investigationCriticalValueReport(Investigation investigation);
	ArrayList<UserProfile> userList(String jobtitle);
	void updateSingUser(String invstids,String userid);
	ArrayList<Investigation> investigationNameListMaster(String invstTypeId);
	boolean isInvChargePaid(int chargeInvoiceid);

	int getchargeInvoiceidFromAssesment(int chargeid);
	ArrayList<Master> getParentList(String clientId, String investigationTypeid,String fromDate, String todate);
	ArrayList<Investigation> getNumericInvestigationListOfPatient(String fromDate, String todate,String clientId);

	String getListOFCFInvestigation(String ids);

	ArrayList<Investigation> getallThirdpartynamelist();

	ArrayList<Investigation> getallwardnamelist();

	String getShortnamebytpid(String tpid);
    
	int savealternatInvstnameByid(Investigation investigation);

	int saveInvestigationdetail(Investigation investigation);

	String getfakedatebyClientid(String fakclientid);
	ArrayList<Investigation> getAllInvestigation(String fromdate,String todate,int ipdid, String opdid);

	Investigation getFindingsbyid(String selectedid);

	Investigation getSignUser();

	ArrayList<UserProfile> userList1(String updatedby);
}
