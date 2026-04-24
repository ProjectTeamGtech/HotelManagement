package com.apm.Log.eu.bi;

import java.util.ArrayList;
import java.util.Date;

import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Log.eu.entity.LogDetail;


public interface LogDAO {

	ArrayList<LogDetail> getTreatmentEpisodeList(String clientId);

	//ArrayList<LogDetail> getAccChargeList(String clientId);

	//ArrayList<LogDetail> getAccInvoiceList(String clientId);

	ArrayList<LogDetail> getAppointmentList(String clientId, String text,String action,String orderby,String order, String fromdate, String todate);

	//ArrayList<LogDetail> getPaymentList(String clientId);

	ArrayList<LogDetail> getEmailHistoryList(String clientid);

	ArrayList<LogDetail> getDNAAppointmentList(String clientId, String text);

	ArrayList<LogDetail> getPastAppointmentList(String clientId,String currentdate, String text,String action,String orderby,String order);

	ArrayList<LogDetail> getFutureAppointmentList(String clientId,
			String currentDate, String text);

	boolean checkAppointmentCompleted(int apmtId);

	boolean checkApmtIsDNA(int apmtId);

	boolean checkApmtIsInvoice(int apmtId);

	boolean checkPaymentDone(int apmtId);

	ArrayList<NotAvailableSlot> getTreatmentEpisodeCountList(String clientId,
			String treatmentEpisodeid, String whopay);

	String getCancelNote(String id);

	int saveresponsetime(String module, String columname);

	int updateresponsetime(String module, String req_datetime, String requestid, String duration_of_time);

	
}
