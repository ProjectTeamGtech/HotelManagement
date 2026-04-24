package com.apm.Log.web.form;

public class LogForm {
	private int id;
	private String date;
	private String modifyDate;
	private String client;
	private String clientSearchLog;
	private String chargeType;
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
	public String getClientSearchLog() {
		return clientSearchLog;
	}
	public void setClientSearchLog(String clientSearchLog) {
		this.clientSearchLog = clientSearchLog;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
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
	private String heading;
	private String practitioner;
	private String time;
	private String invoiceNo;
	private String total;
	private String completdDate;
}
