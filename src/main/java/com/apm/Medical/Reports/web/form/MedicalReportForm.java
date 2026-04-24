package com.apm.Medical.Reports.web.form;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Medical.Reports.eu.entity.MedicalReports;
import com.apm.Registration.eu.entity.Location;

public class MedicalReportForm {
	
	private int id;
	
	private String invoiceid;
	
	private String amountBeforeThirtyTotal;
	
	private String amountAfterThirtyTotal;
	
	private String amountAfterSixtyTotal;
	
	private String amountAfterHundredTotal;
	
	ArrayList<MedicalReports>pendingPaymentList;
	
	ArrayList<Accounts>locationList;
	
	ArrayList<DiaryManagement>userList;
	
	private String location;
	
	private String diaryUser;
	
	private String payby;
	
	private String toDate = "";
	
	private String fromDate = "";
	
	private String order = "asc";
	
	private String orderField = "company_name";
	
	private ArrayList<Accounts>thirdPartyList;
	
	private String thirdParty;
	
	
	

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public ArrayList<Accounts> getThirdPartyList() {
		return thirdPartyList;
	}

	public void setThirdPartyList(ArrayList<Accounts> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

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

	public ArrayList<Accounts> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Accounts> locationList) {
		this.locationList = locationList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getAmountBeforeThirtyTotal() {
		return amountBeforeThirtyTotal;
	}

	public void setAmountBeforeThirtyTotal(String amountBeforeThirtyTotal) {
		this.amountBeforeThirtyTotal = amountBeforeThirtyTotal;
	}

	public String getAmountAfterThirtyTotal() {
		return amountAfterThirtyTotal;
	}

	public void setAmountAfterThirtyTotal(String amountAfterThirtyTotal) {
		this.amountAfterThirtyTotal = amountAfterThirtyTotal;
	}

	public String getAmountAfterSixtyTotal() {
		return amountAfterSixtyTotal;
	}

	public void setAmountAfterSixtyTotal(String amountAfterSixtyTotal) {
		this.amountAfterSixtyTotal = amountAfterSixtyTotal;
	}

	public String getAmountAfterHundredTotal() {
		return amountAfterHundredTotal;
	}

	public void setAmountAfterHundredTotal(String amountAfterHundredTotal) {
		this.amountAfterHundredTotal = amountAfterHundredTotal;
	}

	public ArrayList<MedicalReports> getPendingPaymentList() {
		return pendingPaymentList;
	}

	public void setPendingPaymentList(ArrayList<MedicalReports> pendingPaymentList) {
		this.pendingPaymentList = pendingPaymentList;
	}
	

}
