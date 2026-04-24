package com.apm.ThirdParties.web.form;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.ThirdParties.eu.entity.OutstandingReport;

public class OutstandingReportForm {
	private String thirdPartyName;
	private int id;
	private int thirdPartyId;
	private String thirdPartyContactno;
	private String type;
	private String pagerecords;
	private int totalRecords;
	private String payby;
	private String debit;
	private String tpid;
	
	private String allotamount;
	
	ArrayList<Accounts>invoiceList;
	
	private String fromDate = "";
	private String toDate = "";
	ArrayList<OutstandingReport>allotList;
	ArrayList<Master>banknamelist;
	private String bankname;
	private String paymentType;
	private String cheqType;
	
	private String cheqNo;
	private String handoverTo;
	
	private String recno;
	private String recamt;
	
	public String getRecno() {
		return recno;
	}
	public void setRecno(String recno) {
		this.recno = recno;
	}
	public String getRecamt() {
		return recamt;
	}
	public void setRecamt(String recamt) {
		this.recamt = recamt;
	}
	public ArrayList<OutstandingReport> getAllotList() {
		return allotList;
	}
	public void setAllotList(ArrayList<OutstandingReport> allotList) {
		this.allotList = allotList;
	}
	public String getDebit() {
		return debit;
	}
	public void setDebit(String debit) {
		this.debit = debit;
	}
	public String getPayby() {
		return payby;
	}
	public void setPayby(String payby) {
		this.payby = payby;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	private String date;
	private String time;
	private String notes;
	private int clinicId;
	private int thirdPartyId1;
	private String thirdPartyContactno1;
	private String thirdPartEmail1;
	private String thirdPartyName1;
	private int thirdPartyId2;
	public int getThirdPartyId2() {
		return thirdPartyId2;
	}
	public void setThirdPartyId2(int thirdPartyId2) {
		this.thirdPartyId2 = thirdPartyId2;
	}
	public String getThirdPartyContactno2() {
		return thirdPartyContactno2;
	}
	public void setThirdPartyContactno2(String thirdPartyContactno2) {
		this.thirdPartyContactno2 = thirdPartyContactno2;
	}
	public String getThirdPartEmail2() {
		return thirdPartEmail2;
	}
	public void setThirdPartEmail2(String thirdPartEmail2) {
		this.thirdPartEmail2 = thirdPartEmail2;
	}
	public String getThirdPartyName2() {
		return thirdPartyName2;
	}
	public void setThirdPartyName2(String thirdPartyName2) {
		this.thirdPartyName2 = thirdPartyName2;
	}
	private String thirdPartyContactno2;
	private String thirdPartEmail2;
	private String thirdPartyName2;
	public int getThirdPartyId1() {
		return thirdPartyId1;
	}
	public void setThirdPartyId1(int thirdPartyId1) {
		this.thirdPartyId1 = thirdPartyId1;
	}
	public String getThirdPartyContactno1() {
		return thirdPartyContactno1;
	}
	public void setThirdPartyContactno1(String thirdPartyContactno1) {
		this.thirdPartyContactno1 = thirdPartyContactno1;
	}
	public String getThirdPartEmail1() {
		return thirdPartEmail1;
	}
	public void setThirdPartEmail1(String thirdPartEmail1) {
		this.thirdPartEmail1 = thirdPartEmail1;
	}
	public String getThirdPartyName1() {
		return thirdPartyName1;
	}
	public void setThirdPartyName1(String thirdPartyName1) {
		this.thirdPartyName1 = thirdPartyName1;
	}
	
	public String getThirdPartyContactno() {
		return thirdPartyContactno;
	}
	public void setThirdPartyContactno(String thirdPartyContactno) {
		this.thirdPartyContactno = thirdPartyContactno;
	}
	public String getThirdPartEmail() {
		return thirdPartEmail;
	}
	public void setThirdPartEmail(String thirdPartEmail) {
		this.thirdPartEmail = thirdPartEmail;
	}
	private String thirdPartEmail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getThirdPartyId() {
		
		return thirdPartyId;
	}
	public void setThirdPartyId(int thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}
	
	public String getThirdPartyName() {
		return thirdPartyName;
	}
	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}
	public double getCreditWarningLimit() {
		return creditWarningLimit;
	}
	public void setCreditWarningLimit(double creditWarningLimit) {
		this.creditWarningLimit = creditWarningLimit;
	}
	public double getOutstandingInvoiceLimit() {
		return outstandingInvoiceLimit;
	}
	public void setOutstandingInvoiceLimit(double outstandingInvoiceLimit) {
		this.outstandingInvoiceLimit = outstandingInvoiceLimit;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public String getCreditWarningLimitx() {
		return creditWarningLimitx;
	}
	public void setCreditWarningLimitx(String creditWarningLimitx) {
		this.creditWarningLimitx = creditWarningLimitx;
	}
	public String getOutstandingInvoiceLimitx() {
		return outstandingInvoiceLimitx;
	}
	public void setOutstandingInvoiceLimitx(String outstandingInvoiceLimitx) {
		this.outstandingInvoiceLimitx = outstandingInvoiceLimitx;
	}
	public String getPaidAmountx() {
		return paidAmountx;
	}
	public void setPaidAmountx(String paidAmountx) {
		this.paidAmountx = paidAmountx;
	}
	public String getUnpaidAmoutx() {
		return unpaidAmoutx;
	}
	public void setUnpaidAmoutx(String unpaidAmoutx) {
		this.unpaidAmoutx = unpaidAmoutx;
	}
	public String getReminderAmoutLimitx() {
		return reminderAmoutLimitx;
	}
	public void setReminderAmoutLimitx(String reminderAmoutLimitx) {
		this.reminderAmoutLimitx = reminderAmoutLimitx;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getUnpaidAmout() {
		return unpaidAmout;
	}
	public void setUnpaidAmout(double unpaidAmout) {
		this.unpaidAmout = unpaidAmout;
	}
	private double creditWarningLimit;
	private double outstandingInvoiceLimit;
	
	private String creditWarningLimitx;
	private String outstandingInvoiceLimitx;
	
	
	public ArrayList<OutstandingReport> getOutstandingReportList() {
		return outstandingReportList;
	}
	public void setOutstandingReportList(
			ArrayList<OutstandingReport> outstandingReportList) {
		this.outstandingReportList = outstandingReportList;
	}
	private double paidAmount;
	private double unpaidAmout;
	private double reminderAmoutLimit;
	
	private String paidAmountx;
	private String unpaidAmoutx;
	private String reminderAmoutLimitx;
	
	public double getReminderAmoutLimit() {
		return reminderAmoutLimit;
	}
	public void setReminderAmoutLimit(double reminderAmoutLimit) {
		this.reminderAmoutLimit = reminderAmoutLimit;
	}
	private ArrayList<OutstandingReport>outstandingReportList;
	private ArrayList<OutstandingReport>outstandingActionList;
	private ArrayList<OutstandingReport>outstandingLimitExceedList;
	public ArrayList<OutstandingReport> getOutstandingLimitExceedList() {
		return outstandingLimitExceedList;
	}
	public void setOutstandingLimitExceedList(
			ArrayList<OutstandingReport> outstandingLimitExceedList) {
		this.outstandingLimitExceedList = outstandingLimitExceedList;
	}
	public ArrayList<OutstandingReport> getOutstandingActionList() {
		return outstandingActionList;
	}
	public void setOutstandingActionList(
			ArrayList<OutstandingReport> outstandingActionList) {
		this.outstandingActionList = outstandingActionList;
	}
	
	
	private String clinicName;
	private String clinicOwner;
	private String clinicemail;
	private String mobileNo;
	private String landLine;
	private String country;
	private String clinicity;
	private String clinicaddress;
	private String pinCode;
	private int locationid;
	private String websiteUrl;
	private ArrayList<Clinic> locationAdressList;
	private String owner_qualification;
	
	ArrayList<Accounts>selecttedInvoiceList;
	
	ArrayList<Client>selectedClientList;
	
	
	private String creditTotalx;
	private String debitTotalx;
	private String balanceTotalx;
	
	
	private ArrayList<Accounts>thirdPartyList;
	
	private String thirdParty;
	
	private String clientId="";
	
	private String client = "";
	
	private String payeename;
	private String payeeadress;
	private String payeeTown;
	private String payeePostcode;
	private String payeeEmail;
	private String payeeConatctNo;
	
	private String howpaid = "";
	
	private String message = "";
	
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getHowpaid() {
		return howpaid;
	}
	public void setHowpaid(String howpaid) {
		this.howpaid = howpaid;
	}
	public String getPayeename() {
		return payeename;
	}
	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}
	public String getPayeeadress() {
		return payeeadress;
	}
	public void setPayeeadress(String payeeadress) {
		this.payeeadress = payeeadress;
	}
	public String getPayeeTown() {
		return payeeTown;
	}
	public void setPayeeTown(String payeeTown) {
		this.payeeTown = payeeTown;
	}
	public String getPayeePostcode() {
		return payeePostcode;
	}
	public void setPayeePostcode(String payeePostcode) {
		this.payeePostcode = payeePostcode;
	}
	public String getPayeeEmail() {
		return payeeEmail;
	}
	public void setPayeeEmail(String payeeEmail) {
		this.payeeEmail = payeeEmail;
	}
	public String getPayeeConatctNo() {
		return payeeConatctNo;
	}
	public void setPayeeConatctNo(String payeeConatctNo) {
		this.payeeConatctNo = payeeConatctNo;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
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
	public String getCreditTotalx() {
		return creditTotalx;
	}
	public void setCreditTotalx(String creditTotalx) {
		this.creditTotalx = creditTotalx;
	}
	public String getDebitTotalx() {
		return debitTotalx;
	}
	public void setDebitTotalx(String debitTotalx) {
		this.debitTotalx = debitTotalx;
	}
	public String getBalanceTotalx() {
		return balanceTotalx;
	}
	public void setBalanceTotalx(String balanceTotalx) {
		this.balanceTotalx = balanceTotalx;
	}
	public ArrayList<Client> getSelectedClientList() {
		return selectedClientList;
	}
	public void setSelectedClientList(ArrayList<Client> selectedClientList) {
		this.selectedClientList = selectedClientList;
	}
	public ArrayList<Accounts> getSelecttedInvoiceList() {
		return selecttedInvoiceList;
	}
	public void setSelecttedInvoiceList(ArrayList<Accounts> selecttedInvoiceList) {
		this.selecttedInvoiceList = selecttedInvoiceList;
	}
	public String getOwner_qualification() {
		return owner_qualification;
	}
	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}
	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}
	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getClinicOwner() {
		return clinicOwner;
	}
	public void setClinicOwner(String clinicOwner) {
		this.clinicOwner = clinicOwner;
	}
	public String getClinicemail() {
		return clinicemail;
	}
	public void setClinicemail(String clinicemail) {
		this.clinicemail = clinicemail;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getLandLine() {
		return landLine;
	}
	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getClinicity() {
		return clinicity;
	}
	public void setClinicity(String clinicity) {
		this.clinicity = clinicity;
	}
	public String getClinicaddress() {
		return clinicaddress;
	}
	public void setClinicaddress(String clinicaddress) {
		this.clinicaddress = clinicaddress;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public int getLocationid() {
		return locationid;
	}
	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
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
	public String getAllotamount() {
		return allotamount;
	}
	public void setAllotamount(String allotamount) {
		this.allotamount = allotamount;
	}
	public ArrayList<Accounts> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(ArrayList<Accounts> invoiceList) {
		this.invoiceList = invoiceList;
	}
	public String getTpid() {
		return tpid;
	}
	public void setTpid(String tpid) {
		this.tpid = tpid;
	}
	public ArrayList<Master> getBanknamelist() {
		return banknamelist;
	}
	public void setBanknamelist(ArrayList<Master> banknamelist) {
		this.banknamelist = banknamelist;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getCheqType() {
		return cheqType;
	}
	public void setCheqType(String cheqType) {
		this.cheqType = cheqType;
	}
	public String getCheqNo() {
		return cheqNo;
	}
	public void setCheqNo(String cheqNo) {
		this.cheqNo = cheqNo;
	}
	public String getHandoverTo() {
		return handoverTo;
	}
	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}
	
	
	
}
