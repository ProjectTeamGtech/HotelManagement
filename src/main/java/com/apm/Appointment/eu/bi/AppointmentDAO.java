package com.apm.Appointment.eu.bi;

import java.util.ArrayList;

import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Location;
import com.apm.common.web.common.helper.LoginInfo;

public interface AppointmentDAO {

	int saveAppointment(Appointment appointment);

	ArrayList<Appointment> getPractitionerList(int clinicId);

	ArrayList<Location> getLocationList(int practitionerid);

	ArrayList<AppointmentType> getAppointmentTypeList(int practitionerid);

	ArrayList<Appointment> getApptAvailiability(int practitionerid, String date);

	String getStartTime(int practitionerid, String location, String date);

	ArrayList<AppointmentType> getAdditionalChaergeTypeList(String who);

	String getSelectedCharge(String selectedAppointmentID,String masterchargetype);

	ArrayList<Master> getmasterChageTypeList(LoginInfo loginInfo);

	int updateApproveInvoiceDiscout(String invoiceid, String userid, String datetime, String approve_notes);
	int updateRequestInvoiceDiscout(String invoiceid);

	int saveDiscountRequest(String invoiceid, String userid, String datetime, String disctype, String discval, String invoiceamount, String discountgivenuserid, String discount_reason, int clientid);

	ArrayList<CompleteAppointment> getParentDiscountRequestList(String fromdate, String todate, boolean accessofapprove, int id, String filter_status, String userid, String countdata);

	int updateInvoiceDiscout(String id, String userid, String datetime, String approve_notes);

	CompleteAppointment getRequestDiscountData(String invoiceid);

	int getSelectedSharableChargeStatus(String selectedAppointmentID);
	
	int deletediscountfromdashboard(String id, String userid,String date, String reason,String invid);
ArrayList<Master> getmasterChageTypeListnew(LoginInfo loginInfo);
ArrayList<Master> getIpOpProcedureList(String clientid,LoginInfo loginInfo);

int getNonEditAmtSts(String selectedAppointmentID);

int getApproveAppliedCount(String fromdate, String todate, boolean accessofapprove, int id, String filter_status,
		String userId, String countdata);
ArrayList<CompleteAppointment> getChargesid(int int1);

double getDiscountRequestAmount(String invoiceId);

int updateDiscRequestAmt(String invoiceId, double discamt);

int updateRefundRequestStatus(String invoiceId);

int deletediscountindfromdashboard(String id, String userid, String date, String reason);

CompleteAppointment getDeleteDiscountReqData(String id);

int setUpdateDiscRequestStatusTo0(int invoiced);

boolean checkDiscRequestDeleted(String discountid);

boolean checkInvoiceCreatedAgainstDiscReq(String invoiceid);

boolean checkalredyadmitted(String bedid);

CompleteAppointment getRequestDiscountDataFromId(String newid);

boolean checksetdiary(String commencing, String diaryuserid);

String getTotalsessionno(String selectedAppointmentID, String masterchargetype);

ArrayList<Master> getPhysioIpdmasterChageTypeList(String deptname);

}
