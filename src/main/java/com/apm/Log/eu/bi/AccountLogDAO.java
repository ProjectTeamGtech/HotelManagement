package com.apm.Log.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.Log.eu.entity.LogDetail;

public interface AccountLogDAO {

	int saveAmpmInvoice(CompleteAppointment completeAppointment, int invoice, String status,String lastModified);

	//int updateAmpmInvoice(CompleteAppointment completeAppointment);

	//int ReverseInvoiceEntry(CompleteAppointment completeAppointment, String id);

	int saveChargesInvoice(String payby, String commencing, int parseInt,double debit, double discount, String submitInvoiceNotes,int thirdPartyID, String location, int invoiceid,String lastModified);
	
	//int updateChargeType(String string, String chargeType);

	int saveChargesPayment(String clientId, int invoiceid, String amount,String howpaid, int thirdPartyID, String string,String lastModified,String paymentNote);

	//int updateDeliverStatus(int invoiceid, String string);

	//int updateModifiedInvoiceChargesChargeType(String charges);

	ArrayList<LogDetail> getAccChargeList(String clientId);

	int updateDebitAmount(double sumofCharges, int invoiceid, String invoiceDate,String submitInvoiceNotes,String lastModified);

	ArrayList<LogDetail> getAccInvoiceList(String clientId);

	ArrayList<LogDetail> getPaymentList(String clientId);

	boolean getInvoiceIdForColor(String invoiceNo);

	boolean checkColorForPaid(String invoiceNo);

	


}
