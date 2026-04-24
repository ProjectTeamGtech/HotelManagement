package com.apm.Log.eu.entity;

import java.util.ArrayList;

public class LogDetail {
	private int id;
	private String date;
	private String modifyDate;
	private String addedBy;
	private String payby;
	private String deliverstatus;
	private String paymentMode;
	private String apmtDate;
	private String apmtLocation;
	private int practiitonerId;
	private String clientname;
	private int apmtId;
	private String payeename;
	private String chargeType;
	private String cancelApmtsNotes  = " ";
	private ArrayList<Modify>modiAppintmentList;
	private ArrayList<DNA>dnaAppointmentList;
	private ArrayList<Completed>completedAppointmentList;
	private ArrayList<CompletedModified>cmAppointmentList;
	private ArrayList<Cancelled>canceledAppointmentList;
	
	
	
	
	
	private String paymentNote = "";
	
	private String reposrtStatus = "";
	
	private String sentDate = "";
	
	
	
	
	
	
	
	public ArrayList<Cancelled> getCanceledAppointmentList() {
		return canceledAppointmentList;
	}
	public void setCanceledAppointmentList(
			ArrayList<Cancelled> canceledAppointmentList) {
		this.canceledAppointmentList = canceledAppointmentList;
	}
	public ArrayList<CompletedModified> getCmAppointmentList() {
		return cmAppointmentList;
	}
	public void setCmAppointmentList(ArrayList<CompletedModified> cmAppointmentList) {
		this.cmAppointmentList = cmAppointmentList;
	}
	public ArrayList<Completed> getCompletedAppointmentList() {
		return completedAppointmentList;
	}
	public void setCompletedAppointmentList(
			ArrayList<Completed> completedAppointmentList) {
		this.completedAppointmentList = completedAppointmentList;
	}
	public ArrayList<DNA> getDnaAppointmentList() {
		return dnaAppointmentList;
	}
	public void setDnaAppointmentList(ArrayList<DNA> dnaAppointmentList) {
		this.dnaAppointmentList = dnaAppointmentList;
	}
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	public String getReposrtStatus() {
		return reposrtStatus;
	}
	public void setReposrtStatus(String reposrtStatus) {
		this.reposrtStatus = reposrtStatus;
	}
	public String getPaymentNote() {
		return paymentNote;
	}
	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}
	public ArrayList<Modify> getModiAppintmentList() {
		return modiAppintmentList;
	}
	public void setModiAppintmentList(ArrayList<Modify> modiAppintmentList) {
		this.modiAppintmentList = modiAppintmentList;
	}
	public String getCancelApmtsNotes() {
		return cancelApmtsNotes;
	}
	public void setCancelApmtsNotes(String cancelApmtsNotes) {
		this.cancelApmtsNotes = cancelApmtsNotes;
	}
	public double getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(double totalCharge) {
		this.totalCharge = totalCharge;
	}
	private double totalCharge;
	
	
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	private String invoiceDate;
	
	
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getPayeename() {
		return payeename;
	}
	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}
	public int getApmtId() {
		return apmtId;
	}
	public void setApmtId(int apmtId) {
		this.apmtId = apmtId;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getApmtDate() {
		return apmtDate;
	}
	public void setApmtDate(String apmtDate) {
		this.apmtDate = apmtDate;
	}
	public String getApmtLocation() {
		return apmtLocation;
	}
	public void setApmtLocation(String apmtLocation) {
		this.apmtLocation = apmtLocation;
	}
	public int getPractiitonerId() {
		return practiitonerId;
	}
	public void setPractiitonerId(int practiitonerId) {
		this.practiitonerId = practiitonerId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPayby() {
		return payby;
	}
	public void setPayby(String payby) {
		this.payby = payby;
	}
	public String getDeliverstatus() {
		return deliverstatus;
	}
	public void setDeliverstatus(String deliverstatus) {
		this.deliverstatus = deliverstatus;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	private String modifiedBy;
	
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	private String  modifyTime;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getPractitioner() {
		return practitioner;
	}
	public void setPractitioner(String practitioner) {
		this.practitioner = practitioner;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getCompletdDate() {
		return completdDate;
	}
	public void setCompletdDate(String completdDate) {
		this.completdDate = completdDate;
	}
	private String type;
	private String heading=" ";
	private String practitioner=" ";
	private String time;
	private String invoiceNo;
	private String total;
	private String completdDate;
	private int invoice_id;
	private String to;
	private String cc;
	private String body_text;
	private String subject;
	private String filename;
	private String deleteStatus = " ";
	private String status = " ";
	private String apmtStartTime;
	
	public String getApmtStartTime() {
		return apmtStartTime;
	}
	public void setApmtStartTime(String apmtStartTime) {
		this.apmtStartTime = apmtStartTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBody_text() {
		return body_text;
	}
	public void setBody_text(String body_text) {
		this.body_text = body_text;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	} 
}
