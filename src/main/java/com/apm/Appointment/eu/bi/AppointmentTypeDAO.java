package com.apm.Appointment.eu.bi;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface AppointmentTypeDAO {

	ArrayList<AppointmentType> getAppointmentTypeList(Pagination pagination,String searchtext,String payby,String thirdparty, String ward, String chargeType, String viewaccess,String orderby, String order) ;

	ArrayList<AppointmentType> getColorList() ;

	int saveAppointmentType(AppointmentType appointmentType) ;

	AppointmentType getAppointment(int id) ;

	int updateAppointmentType(AppointmentType appointmentType, int id) ;

	int deleteAppoitmentType(int id, AppointmentType appointmentType) ;

	int getTotalApmtTypeCount(String searchtext,String payby,String thirdparty, String ward, String chargeType, String viewaccess, String orderby, String order) ;

	boolean isApmtTypeExist(String apmtType) ;

	ArrayList<AppointmentType> gettpAppointmentTypeList(String selectedid,
			String whopay,boolean isot,String otprocedureplaned, LoginInfo loginInfo, String docid, long newdiffer) ;
	
	public AppointmentType getMasterCharge(String id)  ;

	String getTpCompanyName(String clientId) ;

	ArrayList<AppointmentType> getAppointmentddList() ;
	AppointmentType getThirdParyChargeDetails(String tpid,String chargeid,int wardid) ;

	ArrayList<AppointmentType> getSelfChargesList() ;
	ArrayList<AppointmentType> getChargesList(String chargeType, String chargecolumn) ;
	public String getChargeName(String chargeId) ;
	int saveThirdPartyCharges(String tid, String mrp, String code,
			String chargeType,String tpid,String payee,String wardid,String invstype,String stdchargeStatus,String onOff,String hour,String ratio, String discount, String discountAmt, String chargeAmount) ;

	ArrayList<AppointmentType> getNewChargeList(String tpid, String wardid,
			String chargeType,String invstype) ;

	int getChargeIdIfExists(String tpid, String wardid, String chargeType,
			String tid,String invstype) ;

	int updateThirdPartyCharges(int chargeid, String mrp, String code,String stdchargeStatus,String onOff,String hour,String ratio, String tpid, String discount, String discountAmt, String chargeAmount) ;

	int getSelfChargeIdFromTpCharge(String chargeid,int wardid) ;

	AppointmentType getChargesDetailsByName(String appointmentType,String chargeType,
			String tpid, int wardid) ;

	ArrayList<Master> getStandardChargeList(String wardid,String tpid,String payee,LoginInfo loginInfo) ;

	int deleteStdCharge(String ipdid, String temp) ;

	int saveStdCharge(String ipdid, String chargeId,int assesmentid,String status,String ondatetime,String offdatetime) ;
	
	boolean isStdChargeSelected(String ipdid,int chargeId) ;

	Master getMasterCharges(String chargeId) ;

	ArrayList<String> getStdChargeHourList() ;

	int getStdChargeIdIdExists(String ipdid, String chargeId) ;

	int updateStdCharge(int result, String status,String ondatetime,String offdatetime) ;

	ArrayList<Master> getStdOnoffChargeList(String wardid, String tpid,
			String payee) ;

	int getstdAssesmentid(int result) ;

	int getDbQuantity(int assesmentid) ;

	String getstddbcurstatus(int result) ;

	Accounts getStdChargeDetails(int result)  ;

	int updateStdChargeDateTime(int result, String ondatetime,
			String offdatetime) ;

	String getLastShiftingDate(int id) ;

	int resetAllInprocessCharge(int id) ;

	int setInprocessforNewShiftCharges(int invoiceid,int logid) ;

	int updateLasteShiftedChargeQty(String lastshiftingdate,
			String curshiftingdate, int qty, int id,int chargeid,int assessmentid) ;

	int updateInprocessQty(int id,int qty,String chargeid) ;

	ArrayList<String> getChargeIdList(int invoiceid) ;

	int getChargeHour(String str) ;

	int getStdChargeAssesmentId(int id, String wardid, int id2) ;

	String getLastShiftedLogid(int id) ;

	ArrayList<Master> getLastShiftedChargeList(String lastshiftedlogid) ;

	int updateinvstgationChargeName(int chargeid, String chargeType,
			String invstype,String tid,String mrp,String code,String tpid, String discount, String discountAmt, String chargeAmount) ;

	int deleteallBedShiftingcharge(int id) ;

	String getLogIDList(int id) ;

	String getapminvoiceidlist(String logidList) ;

	ArrayList<Master> getLogCommencingList(int id) ;

	String getBedShiftigWardIdLIst(int id, String date) ;

	ArrayList<Master> getBedShiftingStandardChargeList(String wardidList,
			String tpid, String payee,LoginInfo loginInfo) ;

	ArrayList<AppointmentType> getInvestigationChargeList(String tpid,
			String wardid, String chargeType, String invstype, String name) ;

	String getIpdAdmissionDate(int id) ;

	ArrayList<Master> getsepetatrLogcommencingList(int id) ;

	Bed getLogwardId(String date, int id) ;

	Master getLastIpdLogData(int id) ;

	int getautosetchargelogid(int id,String wardid,String bedid) ;

	int updateAutosetWardID(int autosetchargelogid, String wardid, String bedid) ;

	boolean checkChargeExisist(String chargetype, String chargeName, String wardid, String tpid, String otchargetype) ;

	int updateOtcharge(String chargetype, String chargeName, String charge, String wardid, String tpid,
			String otchargetype,String duration) ;

	int saveOtCharges(String chargetype, String chargeName, String charge, String wardid, String tpid,
			String otchargetype,String duration) ;

	String getotChargeId(String chargetype, String chargeName, String wardid, String tpid) ;

	int saveAniDoctor(String doctor) ;

	ArrayList<Master> getLedgerServiceList() ;

	ArrayList<Master> getLedgerList(String ltype) ;

	int updateLedgerServices(String ledgername, String hdnledgerserviceid) ;

	String getdbSelectedServices(String ledgername) ;

	int saveNewLedger(String hdnnewledger,String howpaid,String bnkname,String ltype) ;

	//ArrayList<Accounts> getledgerreportData(String ledger);

	ArrayList<Master> getExpenceLedgerList(LoginInfo info) ;

	int saveNewAccountHead(String hdnnewahead,String obal,String actype) ;

	double getLedgerBalance(int saveid) ;

	int saveLedgerAhead(String partyid, String product, String lddebit, String credit, double lbal, int saveid,
			String ldcommencing,String gcommencing) ;

	ArrayList<Master> getAheadNameList() ;

	ArrayList<Master> getAheadServiceNameList() ;

	int updateAheadServices(String aheadname, String hdnaheadserviceid) ;

	Master  getdbSelectedAheadServices(String aheadname) ;

	int checkCurrentAccountForTheDay(String aheadname,String cdate) ;

	double getLastLedgerdetails(String ledgerid,int length) ;

	double getAheadBalance(String aheadname) ;

	double getAheadDebit(String aheadname) ;

	int getLastAheadID(String aheadname) ;

	int updateLedgerAhead(String partyid, String product, String lddebit, String credit, double lbal, int parseInt,
			String ldcommencing, int lastaheadid) ;

	ArrayList<Accounts> getAheadreportData(String aheadname,String searchtext) ;

	ArrayList<Master> getledgersheetList(Master master) ;

	int checkAheadSheetEntery(String date,String ledgerid) ;

	int updateLedgerAheadData(String credit, String lddebit, int checkaccountentry) ;

	ArrayList<Accounts> getOcreptData(String fromDate, String toDate) ;

	ArrayList<Accounts> getledgerreportData(String fromDate, String toDate, String ledgername, String servicename,
			String actype, String bnkname) ;

	ArrayList<Master> getLedgerList1(String string);
	

int insertappointmentlog(String userid,String Charge, String appid, String previous);  
boolean check();


ArrayList<Accounts> getScreditorList(String fromDate, String toDate);

ArrayList<Master> getDebitheadList(String fromDate, String toDate);

ArrayList<Master> getCreditAheadList(String fromDate, String toDate);

String getpreviousCharge(int id, String columnname);
String getLedgerName(String id);

ArrayList<Master> getManageLedgerList(String string);
ArrayList<AppointmentType> getallApmtList1();

String getLedgerStr(String aheadname);

int getGroupCount();

String getServiceidstr(int grpid);

int saveheadgroupid(String servicestr, int grpid);

boolean checkheadid(int grpid);

int updateServicestr(int grpid, String servicestr);

ArrayList<Master> getExpenceLedgerAmountList(LoginInfo loginInfo);

String getChargebyid(int int1, String chargecolumnname);

int deletetodayBedShiftingcharge(int id, String today, LoginInfo loginInfo);	
boolean isStdChargeExist(String ipdid, int chargeId);
public ArrayList<Master> getStdOnoffChargeListDiffWard(String wardid, String tpid,String payee,String ipdid);

String getChargeAmountFromId(int aptid);

int validateCodeDuplicate(String code, String id);

boolean checkChargeInNDS(String ndsId, String tpid);

String getnameFromId(String id);

ArrayList<AppointmentType> getProcedureList(String name);


String getprocedureid(String proceduretype);

AppointmentType getoldThirdPartyCharges(int chargeid);

int savePayerCharges(AppointmentType old_thirdparty_charges,String userId, String tid, String mrp, String code, String chargeType, String tpid, String payee,
		String wardid, String invstype, String standardcharge, String onoff, String hour, String ratio, String discount,
		String discountAmt, String chargeAmount, String datetime);

ArrayList<AppointmentType> getManasclinictpAppointmentTypeList(String selectedid, String whopay, boolean isot,
		String otprocedureplaned, String clinicstaff_id);

ArrayList<AppointmentType> getAllSessionChargeList(String selecteddept_id, String client_id);

String getLastopddatebyclientid(String clientId);

int getavailableslotclientcount(String clientId);

int updateOpd_count(int i, String clientId);

String getChargeTypebyid(String string);

NotAvailableSlot getAdmissiondatebyid(String clientid);

int updateAppointmentType1(AppointmentType appointmentType, int id);

ArrayList<AppointmentType> getPayerlist(int id);

String getApppointmentIdbyDocid(String docid, String apmt_name, String selectedid);

double getavailableslotCharges(String clientid);

String getLastChargedappointment(String clientid);
}
