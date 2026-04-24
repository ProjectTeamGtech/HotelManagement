package com.apm.Report.web.form;

import java.util.ArrayList;










import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Report.eu.entity.Commission;

public class CommissionForm {
	
	private int id;
	private String action;
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	private String clientName;
	
	private String commensing;
	
	private String charge;
	
	private int appointmentId;
	
	private String chargeType;	
	
	private String totalCharge;
	
	private int commissionCharge;
	
	private String DNACharge;
	
	private String CACharge;
	
	private ArrayList<Accounts>locationList;
	
	ArrayList<Commission>vsitingchargeList;
	ArrayList<Commission>ipcconsultingList;
	ArrayList<Commission>surgeonChargeList;
	
	
	
	
	public ArrayList<Commission> getVsitingchargeList() {
		return vsitingchargeList;
	}

	public void setVsitingchargeList(ArrayList<Commission> vsitingchargeList) {
		this.vsitingchargeList = vsitingchargeList;
	}

	public ArrayList<Commission> getIpcconsultingList() {
		return ipcconsultingList;
	}

	public void setIpcconsultingList(ArrayList<Commission> ipcconsultingList) {
		this.ipcconsultingList = ipcconsultingList;
	}

	public ArrayList<Commission> getSurgeonChargeList() {
		return surgeonChargeList;
	}

	public void setSurgeonChargeList(ArrayList<Commission> surgeonChargeList) {
		this.surgeonChargeList = surgeonChargeList;
	}

	public ArrayList<Accounts> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Accounts> locationList) {
		this.locationList = locationList;
	}

	public int getCommissionCharge() {
		return commissionCharge;
	}

	public void setCommissionCharge(int commissionCharge) {
		this.commissionCharge = commissionCharge;
	}

	
	private int totalRecords;
	
	ArrayList<Commission> commissionList;
	
	public ArrayList<Commission> getCommissionList() {
		return commissionList;
	}

	public void setCommissionList(ArrayList<Commission> commissionList) {
		this.commissionList = commissionList;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	private String pagerecords;
	

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

	private String diaryUser = "0";
	
	private String location;
	
	ArrayList<DiaryManagement> userList;
	
	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	
	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}
	
	ArrayList<Commission> practitionerList;
	
	private String fromDate="";
	
	private String toDate="";
	
	private String practitionerName;
	
	private String clinicLocation;
	
	private String practitionerId;
	
	private int totalAppNo;

	private int totalDNA;
	
	private int totalCompleted;
	
	private int totalIncompleted;
	
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

	public int getDebit() {
		return debit;
	}

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

	public String getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(String totalCommission) {
		this.totalCommission = totalCommission;
	}

	public String getCommssionFee() {
		return commssionFee;
	}

	public void setCommssionFee(String commssionFee) {
		this.commssionFee = commssionFee;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}
	
	ArrayList<Commission> totalChargeInvoicedList;
	
	private int totalChargeInvoiced;
	
	public int getTotalChargeInvoiced() {
		return totalChargeInvoiced;
	}

	public void setTotalChargeInvoiced(int totalChargeInvoiced) {
		this.totalChargeInvoiced = totalChargeInvoiced;
	}

	private int totalInvoicePaid;

	public int getTotalInvoicePaid() {
		return totalInvoicePaid;
	}

	public void setTotalInvoicePaid(int totalInvoicePaid) {
		this.totalInvoicePaid = totalInvoicePaid;
	}


	public ArrayList<Commission> getTotalChargeInvoicedList() {
		return totalChargeInvoicedList;
	}

	public void setTotalChargeInvoicedList(
			ArrayList<Commission> totalChargeInvoicedList) {
		this.totalChargeInvoicedList = totalChargeInvoicedList;
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
