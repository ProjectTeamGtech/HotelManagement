package com.apm.DiaryManagement.web.form;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Registration.eu.entity.Location;

public class CompleteAppointmentForm {
	
	private String user;
	
	private ArrayList<Accounts>locationList;
	
	private ArrayList<AppointmentType>appointmentTypeList;
	
	private String charge;
	
	private String clientId;
	
	private int id;
	
	private String apmtType;
	
	private String charges;
	
	private String startTime;
	
	private String duration;
	
	private String practitionerId;
	
	private String practitionerName;
	
	private String chargeTotal;
	
	private String commencing;
	
	private String payBuy;
	
	private String invoiceDate;
	
	private int numberOfChages;
	
	private String insuranceCompany;

	private ArrayList<CompleteAppointment> clientChargeListDetail;
	
	private ArrayList<CompleteAppointment>assesmentList;
	
	private ArrayList<Accounts>invoiceAssesmentList;
	
	private String totalassesment;
	
	private Integer invoiceid;
	
	private String paidAmount;
	
	private String debitAmounnt;
	//unnati
	private ArrayList<DiaryManagement>userList;
	
	private ArrayList<String>startTimeList;
	
	private ArrayList<String>endTimeList;
	
	private ArrayList<String> apmtDurationList;
	
	private String diaryUser;
	
	private ArrayList<CompleteAppointment> pendingChargesList;
	private String location;
	private int apmtSlotId;
	private String sTime;
	private String endTime;
	private String notes;
	private int diaryUserId;
	private String dept;
	private String treatmentEpisodeId;
	private String appointmentid;
	private String appointmentTypeId;
	private String dna;
	private String commencing1;
	private String apmtypeText;

	public String getApmtypeText() {
		return apmtypeText;
	}
	public void setApmtypeText(String apmtypeText) {
		this.apmtypeText = apmtypeText;
	}
	public String getCommencing1() {
		return commencing1;
	}
	public void setCommencing1(String commencing1) {
		this.commencing1 = commencing1;
	}
	public String getDiaryUser1() {
		return diaryUser1;
	}
	public void setDiaryUser1(String diaryUser1) {
		this.diaryUser1 = diaryUser1;
	}
	public String getLocation1() {
		return location1;
	}
	public void setLocation1(String location1) {
		this.location1 = location1;
	}
	public String getPagerecords() {
		return pagerecords;
	}
	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	private String diaryUser1;
	private String location1;
	private String pagerecords;
	private int totalRecords;

	public String getDna() {
		return dna;
	}
	public void setDna(String dna) {
		this.dna = dna;
	}
	public String getAppointmentTypeId() {
		return appointmentTypeId;
	}
	public void setAppointmentTypeId(String appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}
	public String getAppointmentid() {
		return appointmentid;
	}
	public void setAppointmentid(String appointmentid) {
		this.appointmentid = appointmentid;
	}
	public String getTreatmentEpisodeId() {
		return treatmentEpisodeId;
	}
	public void setTreatmentEpisodeId(String treatmentEpisodeId) {
		this.treatmentEpisodeId = treatmentEpisodeId;
	}
	public int getApmtSlotId() {
		return apmtSlotId;
	}
	public void setApmtSlotId(int apmtSlotId) {
		this.apmtSlotId = apmtSlotId;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getDiaryUserId() {
		return diaryUserId;
	}
	public void setDiaryUserId(int diaryUserId) {
		this.diaryUserId = diaryUserId;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public void setInvoiceid(Integer invoiceid) {
		this.invoiceid = invoiceid;
	}
	private String room;
	private String client;
	
	


	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ArrayList<CompleteAppointment> getPendingChargesList() {
		return pendingChargesList;
	}
	public void setPendingChargesList(
			ArrayList<CompleteAppointment> pendingChargesList) {
		this.pendingChargesList = pendingChargesList;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getDebitAmounnt() {
		return debitAmounnt;
	}
	public void setDebitAmounnt(String debitAmounnt) {
		this.debitAmounnt = debitAmounnt;
	}
	public int getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}
	public String getTotalassesment() {
		return totalassesment;
	}
	public void setTotalassesment(String totalassesment) {
		this.totalassesment = totalassesment;
	}
	public ArrayList<CompleteAppointment> getClientChargeListDetail() {
		return clientChargeListDetail;
	}
	public void setClientChargeListDetail(
			ArrayList<CompleteAppointment> clientChargeListDetail) {
		this.clientChargeListDetail = clientChargeListDetail;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public ArrayList<Accounts> getLocationList() {
		return locationList;
	}
	public void setLocationList(ArrayList<Accounts> locationList) {
		this.locationList = locationList;
	}
	public ArrayList<AppointmentType> getAppointmentTypeList() {
		return appointmentTypeList;
	}
	public void setAppointmentTypeList(
			ArrayList<AppointmentType> appointmentTypeList) {
		this.appointmentTypeList = appointmentTypeList;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApmtType() {
		return apmtType;
	}
	public void setApmtType(String apmtType) {
		this.apmtType = apmtType;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getPractitionerId() {
		return practitionerId;
	}
	public void setPractitionerId(String practitionerId) {
		this.practitionerId = practitionerId;
	}
	public String getPractitionerName() {
		return practitionerName;
	}
	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}
	
	public String getChargeTotal() {
		return chargeTotal;
	}
	public void setChargeTotal(String chargeTotal) {
		this.chargeTotal = chargeTotal;
	}
	public String getCommencing() {
		return commencing;
	}
	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}
	public String getPayBuy() {
		return payBuy;
	}
	public void setPayBuy(String payBuy) {
		this.payBuy = payBuy;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public ArrayList<CompleteAppointment> getAssesmentList() {
		return assesmentList;
	}
	public void setAssesmentList(ArrayList<CompleteAppointment> assesmentList) {
		this.assesmentList = assesmentList;
	}
	public int getNumberOfChages() {
		return numberOfChages;
	}
	public void setNumberOfChages(int numberOfChages) {
		this.numberOfChages = numberOfChages;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public ArrayList<Accounts> getInvoiceAssesmentList() {
		return invoiceAssesmentList;
	}
	public void setInvoiceAssesmentList(ArrayList<Accounts> invoiceAssesmentList) {
		this.invoiceAssesmentList = invoiceAssesmentList;
	}
	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}
	public ArrayList<String> getStartTimeList() {
		return startTimeList;
	}
	public void setStartTimeList(ArrayList<String> startTimeList) {
		this.startTimeList = startTimeList;
	}
	public ArrayList<String> getEndTimeList() {
		return endTimeList;
	}
	public void setEndTimeList(ArrayList<String> endTimeList) {
		this.endTimeList = endTimeList;
	}
	public ArrayList<String> getApmtDurationList() {
		return apmtDurationList;
	}
	public void setApmtDurationList(ArrayList<String> apmtDurationList) {
		this.apmtDurationList = apmtDurationList;
	}
	public String getDiaryUser() {
		return diaryUser;
	}
	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}


}
