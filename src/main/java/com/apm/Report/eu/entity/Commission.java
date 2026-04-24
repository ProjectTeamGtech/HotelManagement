package com.apm.Report.eu.entity;

import java.util.ArrayList;

public class Commission {
	
	
	public String getCommssionFee() {
		return commssionFee;
	}

	public void setCommssionFee(String commssionFee) {
		this.commssionFee = commssionFee;
	}



	private int id;
	
	private String clientName;
	
	private String commensing;
	
	private String charge;
	
	private int appointmentId;
	
	private String chargeType;
	
	private String totalCharge;
	
	private int commissionCharge;
	
	private int chtype;
	
	private String revanue;
	private String shareamt;
	private String per;
	

	

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getRevanue() {
		return revanue;
	}

	public void setRevanue(String revanue) {
		this.revanue = revanue;
	}

	public String getShareamt() {
		return shareamt;
	}

	public void setShareamt(String shareamt) {
		this.shareamt = shareamt;
	}

	public int getChtype() {
		return chtype;
	}

	public void setChtype(int chtype) {
		this.chtype = chtype;
	}

	public int getCommissionCharge() {
		return commissionCharge;
	}

	public void setCommissionCharge(int commissionCharge) {
		this.commissionCharge = commissionCharge;
	}

	

	private String DNACharge;
	
	private String CACharge;
	

	public String getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}

	public String getDNACharge() {
		return DNACharge;
	}

	public void setDNACharge(String dNACharge) {
		DNACharge = dNACharge;
	}

	public String getCACharge() {
		return CACharge;
	}

	public void setCACharge(String cACharge) {
		CACharge = cACharge;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCommensing() {
		return commensing;
	}

	public void setCommensing(String commensing) {
		this.commensing = commensing;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	
	ArrayList<Commission> practitionerList;
	
	private String fromDate;
	
	private String toDate;
	
	private String practitionerName;
	
	private String clinicLocation;
	
	private String practitionerId;
	
	private int totalCompleted;
	
	private int totalIncompleted;
	
	private int totalChargeInvoiced;
	
	private int totalInvoicePaid;
	
	private int debit;
	
	private String totalCommission;
	
	private String clientId;
	
	private String appType;
	
	private String appStatus;
	
	private String invoiceTo;
	
	private String invoiceNo;
	
	private String invoiceDate;
	
	private String paymode;
	
	private String paidDate;
	
	private String commssionFee;
	
	private String commission1;
	
	private String commission2;
	

	public String getCommission1() {
		return commission1;
	}

	public void setCommission1(String commission1) {
		this.commission1 = commission1;
	}

	public String getCommission2() {
		return commission2;
	}

	public void setCommission2(String commission2) {
		this.commission2 = commission2;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	public String getInvoiceTo() {
		return invoiceTo;
	}

	public void setInvoiceTo(String invoiceTo) {
		this.invoiceTo = invoiceTo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	
	public String getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(String totalCommission) {
		this.totalCommission = totalCommission;
	}

	public int getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}

	public int getTotalInvoicePaid() {
		return totalInvoicePaid;
	}

	public void setTotalInvoicePaid(int totalInvoicePaid) {
		this.totalInvoicePaid = totalInvoicePaid;
	}

	public int getTotalChargeInvoiced() {
		return totalChargeInvoiced;
	}

	public void setTotalChargeInvoiced(int totalChargeInvoiced) {
		this.totalChargeInvoiced = totalChargeInvoiced;
	}

	public int getTotalIncompleted() {
		return totalIncompleted;
	}

	public void setTotalIncompleted(int totalIncompleted) {
		this.totalIncompleted = totalIncompleted;
	}

	public int getTotalCompleted() {
		return totalCompleted;
	}

	public void setTotalCompleted(int totalCompleted) {
		this.totalCompleted = totalCompleted;
	}
	
	public int getTotalDNA() {
		return totalDNA;
	}

	public void setTotalDNA(int totalDNA) {
		this.totalDNA = totalDNA;
	}

	private int totalAppNo;

	private int totalDNA;

	public int getTotalAppNo() {
		return totalAppNo;
	}

	public void setTotalAppNo(int totalAppNo) {
		this.totalAppNo = totalAppNo;
	}

	public String getPractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(String practitionerId) {
		this.practitionerId = practitionerId;
	}



	public String getClinicLocation() {
		return clinicLocation;
	}

	public void setClinicLocation(String clinicLocation) {
		this.clinicLocation = clinicLocation;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public ArrayList<Commission> getPractitionerList() {
		return practitionerList;
	}

	public void setPractitionerList(ArrayList<Commission> practitionerList) {
		this.practitionerList = practitionerList;
	}

	

}
