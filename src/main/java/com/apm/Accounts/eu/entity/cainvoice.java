package com.apm.Accounts.eu.entity;

import java.util.ArrayList;
import java.util.List;

import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Tools.eu.entity.EmailTemplate;

public  class cainvoice {
private String clientId = "";
	
	private String client = "";
	
	private String payby = "";
	
	private int numberOfChages;
	
	private String actionType = "";
	
	private int advref = 0;
	
	private String selectedInvoiceid = "";
	
	private String creditCharge;
	private double payAmount;
	private String payAmountx;
	private List<Accounts>assesmentList;
	private ArrayList<Accounts>accountList;
	private int invoiceid;
	private String invoiceDate;
	private String practitionerName;
	private String totalassesment;
	private String id;
	private String templateName;
	private String payeename;
	private String payeeadress;
	private ArrayList<AppointmentType> additionalChargesList;
	private String apmtType;
	private String raiseChargeType;
	private String apmtType1;
	private int apmtChargeType;
	private String payeeTown;
	private String payeePostcode;
	private String payeeEmail;
	private String payeeConatctNo;
	private String policyExcess;
	private String totalExcludingPolicyExcess; 
	private String balanceExcludingPolicyExcess;
	private String creditDebitCharge;
	private String creditChargeId;
	
	
	private ArrayList<Accounts>creditList;
	
	private String paymentNote = "";
	
	private String balanceAmount = "";
	
	private boolean balanceAmt;
	
	private String appointmentid = "";
	
	private String creditNote = "";
	
	private String crdAmount = "";
	
	private String message;
	
	private String clinicLogo;
	
	private String paymode;
	
	private ArrayList<Master>masterChageTypeList;
	
	private String masterchargetype;
	
	
	


	public int getAdvref() {
		return advref;
	}

	public void setAdvref(int advref) {
		this.advref = advref;
	}

	public ArrayList<Master> getMasterChageTypeList() {
		return masterChageTypeList;
	}

	public void setMasterChageTypeList(ArrayList<Master> masterChageTypeList) {
		this.masterChageTypeList = masterChageTypeList;
	}

	public String getMasterchargetype() {
		return masterchargetype;
	}

	public void setMasterchargetype(String masterchargetype) {
		this.masterchargetype = masterchargetype;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(String creditNote) {
		this.creditNote = creditNote;
	}

	public String getCrdAmount() {
		return crdAmount;
	}

	public void setCrdAmount(String crdAmount) {
		this.crdAmount = crdAmount;
	}

	public String getSelectedInvoiceid() {
		return selectedInvoiceid;
	}

	public void setSelectedInvoiceid(String selectedInvoiceid) {
		this.selectedInvoiceid = selectedInvoiceid;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(String appointmentid) {
		this.appointmentid = appointmentid;
	}

	public boolean isBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(boolean balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPaymentNote() {
		return paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public ArrayList<Accounts> getCreditList() {
		return creditList;
	}

	public void setCreditList(ArrayList<Accounts> creditList) {
		this.creditList = creditList;
	}

	public String getCreditChargeId() {
		return creditChargeId;
	}

	public void setCreditChargeId(String creditChargeId) {
		this.creditChargeId = creditChargeId;
	}

	public String getCreditDebitCharge() {
		return creditDebitCharge;
	}

	public void setCreditDebitCharge(String creditDebitCharge) {
		this.creditDebitCharge = creditDebitCharge;
	}

	private int policyexcesscode = 0;
	private ArrayList<Clinic> locationAdressList;
	
	
	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}

	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}

	public int getPolicyexcesscode() {
		return policyexcesscode;
	}

	public void setPolicyexcesscode(int policyexcesscode) {
		this.policyexcesscode = policyexcesscode;
	}

	public String getBalanceExcludingPolicyExcess() {
		return balanceExcludingPolicyExcess;
	}

	public void setBalanceExcludingPolicyExcess(String balanceExcludingPolicyExcess) {
		this.balanceExcludingPolicyExcess = balanceExcludingPolicyExcess;
	}

	public String getTotalExcludingPolicyExcess() {
		return totalExcludingPolicyExcess;
	}

	public void setTotalExcludingPolicyExcess(String totalExcludingPolicyExcess) {
		this.totalExcludingPolicyExcess = totalExcludingPolicyExcess;
	}

	public String getPolicyExcess() {
		return policyExcess;
	}

	public void setPolicyExcess(String policyExcess) {
		this.policyExcess = policyExcess;
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

	public int getApmtChargeType() {
		return apmtChargeType;
	}

	public void setApmtChargeType(int apmtChargeType) {
		this.apmtChargeType = apmtChargeType;
	}

	public String getApmtType1() {
		return apmtType1;
	}

	public void setApmtType1(String apmtType1) {
		this.apmtType1 = apmtType1;
	}

	public String getRaiseChargeType() {
		return raiseChargeType = "";
	}

	public void setRaiseChargeType(String raiseChargeType) {
		this.raiseChargeType = raiseChargeType;
	}

	public String getApmtType() {
		return apmtType;
	}

	public void setApmtType(String apmtType) {
		this.apmtType = apmtType;
	}

	public ArrayList<AppointmentType> getAdditionalChargesList() {
		return additionalChargesList;
	}

	public void setAdditionalChargesList(
			ArrayList<AppointmentType> additionalChargesList) {
		this.additionalChargesList = additionalChargesList;
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

	
	
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	private ArrayList<EmailTemplate> templateNameList;	

	public ArrayList<EmailTemplate> getTemplateNameList() {
		return templateNameList;
	}

	public void setTemplateNameList(ArrayList<EmailTemplate> templateNameList) {
		this.templateNameList = templateNameList;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	private String debitAmounnt;
	;
	private String insuranceCompany;
	
	private String hdnSelectedID;
	
	private String discount = "";
	
	private boolean hdnSelectAll;
	
	private String mobno;
	
	private String policyNo;
	private String thirdPartyContacttno;
	private String thirdPartyPostcode;
	private String thirdPartyemail;
	private String thirdPartyAddress;

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	private String postcode;
	
	public String getThirdPartyAddress() {
		return thirdPartyAddress;
	}

	public void setThirdPartyAddress(String thirdPartyAddress) {
		this.thirdPartyAddress = thirdPartyAddress;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	private String howpaid;
	
	private String email;
	private String filename;
	
	private ArrayList<Invoice>invoiceList;
	
	
	
	public String getThirdPartyContacttno() {
		return thirdPartyContacttno;
	}

	public void setThirdPartyContacttno(String thirdPartyContacttno) {
		this.thirdPartyContacttno = thirdPartyContacttno;
	}

	public String getThirdPartyPostcode() {
		return thirdPartyPostcode;
	}

	public void setThirdPartyPostcode(String thirdPartyPostcode) {
		this.thirdPartyPostcode = thirdPartyPostcode;
	}

	public String getThirdPartyemail() {
		return thirdPartyemail;
	}

	public void setThirdPartyemail(String thirdPartyemail) {
		this.thirdPartyemail = thirdPartyemail;
	}

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	private String totalamount;
	
	private String dob;
	
	private String  admissionDate;
	
	private String dischargeDate;
	
	private String agegender;
	
	private int ipdid;
	
	
	

	public int getIpdid() {
		return ipdid;
	}

	public void setIpdid(int ipdid) {
		this.ipdid = ipdid;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getAgegender() {
		return agegender;
	}

	public void setAgegender(String agegender) {
		this.agegender = agegender;
	}

	public String getHowpaid() {
		return howpaid;
	}

	public void setHowpaid(String howpaid) {
		this.howpaid = howpaid;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public boolean isHdnSelectAll() {
		return hdnSelectAll;
	}

	public void setHdnSelectAll(boolean hdnSelectAll) {
		this.hdnSelectAll = hdnSelectAll;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	private String invoiceNo;
	private double creditTotal;
	private double debitTotal;
	private double balanceTotal;
	private String creditTotalx;
	private String debitTotalx;
	private String balanceTotalx;
	private ArrayList<Accounts> clientDetailsList;
	private String initial;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String transactionType = "";
	private String location = "";
	private String thirdParty = "";
	private String submitInvoiceNotes;
	
	private String selectedPayBy;
	
	private String selectedLocation;
	
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	private String locationName;
	
	
	public String getSelectedLocation() {
		return selectedLocation;
	}

	public void setSelectedLocation(String selectedLocation) {
		this.selectedLocation = selectedLocation;
	}

	public String getSelectedPayBy() {
		return selectedPayBy;
	}

	public void setSelectedPayBy(String selectedPayBy) {
		this.selectedPayBy = selectedPayBy;
	}

	public String getSubmitInvoiceNotes() {
		return submitInvoiceNotes;
	}

	public void setSubmitInvoiceNotes(String submitInvoiceNotes) {
		this.submitInvoiceNotes = submitInvoiceNotes;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	private ArrayList<Accounts>locationList;
	private ArrayList<Accounts>thirdPartyList;
	public ArrayList<Accounts> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Accounts> locationList) {
		this.locationList = locationList;
	}

	public ArrayList<Accounts> getThirdPartyList() {
		return thirdPartyList;
	}

	public void setThirdPartyList(ArrayList<Accounts> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ArrayList<Accounts> getClientDetailsList() {
		return clientDetailsList;
	}

	public void setClientDetailsList(ArrayList<Accounts> clientDetailsList) {
		this.clientDetailsList = clientDetailsList;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(double creditTotal) {
		this.creditTotal = creditTotal;
	}

	public double getDebitTotal() {
		return debitTotal;
	}

	public void setDebitTotal(double debitTotal) {
		this.debitTotal = debitTotal;
	}

	public double getBalanceTotal() {
		return balanceTotal;
	}

	public void setBalanceTotal(double balanceTotal) {
		this.balanceTotal = balanceTotal;
	}

	private ArrayList<Accounts>transactionList ;
	public ArrayList<Accounts> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(ArrayList<Accounts> transactionList) {
		this.transactionList = transactionList;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	private String date;
	private String paymentmode;
	private double amount;
	private String amountx;
	
	public String getHdnSelectedID() {
		return hdnSelectedID;
	}

	public void setHdnSelectedID(String hdnSelectedID) {
		this.hdnSelectedID = hdnSelectedID;
	}

	private String creditAmt;
	private String pagerecords;
	private int totalRecords;
	private String clientId1;
	private String client1;
	private double totalAmount;
	private String totalAmountx;
	private String commencing;
	private double balance;
	public String getPayAmountx() {
		return payAmountx;
	}

	public void setPayAmountx(String payAmountx) {
		this.payAmountx = payAmountx;
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

	public String getAmountx() {
		return amountx;
	}

	public void setAmountx(String amountx) {
		this.amountx = amountx;
	}

	public String getTotalAmountx() {
		return totalAmountx;
	}

	public void setTotalAmountx(String totalAmountx) {
		this.totalAmountx = totalAmountx;
	}

	public String getBalancex() {
		return balancex;
	}

	public void setBalancex(String balancex) {
		this.balancex = balancex;
	}

	private String balancex;
	private String fromDate = "";
	private String toDate = "";
	private ArrayList<Accounts>chargeProcessingList;
	public ArrayList<Accounts> getChargeProcessingList() {
		return chargeProcessingList;
	}

	public void setChargeProcessingList(ArrayList<Accounts> chargeProcessingList) {
		this.chargeProcessingList = chargeProcessingList;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getClient1() {
		return client1;
	}

	public void setClient1(String client1) {
		this.client1 = client1;
	}

	public String getClientId1() {
		return clientId1;
	}

	public void setClientId1(String clientId1) {
		this.clientId1 = clientId1;
	}

	public String getPayby1() {
		return payby1;
	}

	public void setPayby1(String payby1) {
		this.payby1 = payby1;
	}

	private String payby1;
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

	public String getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}

	public String getDebitAmt() {
		return debitAmt;
	}

	public void setDebitAmt(String debitAmt) {
		this.debitAmt = debitAmt;
	}

	private String debitAmt;
	
	

	public String getDebitAmounnt() {
		return debitAmounnt;
	}

	public void setDebitAmounnt(String debitAmounnt) {
		this.debitAmounnt = debitAmounnt;
	}

	public String getTotalassesment() {
		return totalassesment;
	}

	public void setTotalassesment(String totalassesment) {
		this.totalassesment = totalassesment;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public ArrayList<Accounts> getAccountList() {
		return accountList;
	}

	public void setAccountList(ArrayList<Accounts> accountList) {
		this.accountList = accountList;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getNumberOfChages() {
		return numberOfChages;
	}

	public void setNumberOfChages(int numberOfChages) {
		this.numberOfChages = numberOfChages;
	}

	public String getCreditCharge() {
		return creditCharge;
	}

	public void setCreditCharge(String creditCharge) {
		this.creditCharge = creditCharge;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public List<Accounts> getAssesmentList() {
		return assesmentList;
	}

	public void setAssesmentList(List<Accounts> assesmentList) {
		this.assesmentList = assesmentList;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
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

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}
	
	private String owner_qualification;
}
