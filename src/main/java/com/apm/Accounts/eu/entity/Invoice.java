package com.apm.Accounts.eu.entity;

import java.util.ArrayList;
import java.util.Vector;

import com.apm.Master.eu.entity.Master;

public class Invoice {
	
private int id;
	
	private int clientId;
	
	private int practitionerId;
	
	private String ClientName;
	
	private String practitionerName;
	
	private String commencing;
	private String inddisctot;
	private String indwodisctot;
	private boolean inddiscsts;
	public String getInddisctot() {
		return inddisctot;
	}

	public void setInddisctot(String inddisctot) {
		this.inddisctot = inddisctot;
	}

	public String getIndwodisctot() {
		return indwodisctot;
	}

	public void setIndwodisctot(String indwodisctot) {
		this.indwodisctot = indwodisctot;
	}

	public boolean isInddiscsts() {
		return inddiscsts;
	}

	public void setInddiscsts(boolean inddiscsts) {
		this.inddiscsts = inddiscsts;
	}

	private String appointmentType;
	
	private String creditCharge;
	
	private int invoiceid;
	private int deleted;
	
	private boolean paid;
	
	private ArrayList<Charges>invoiceChargeList;
	
	private String chargeInvoiceid;
	
	ArrayList<ChargesInvoice>chargeInvoiceList;
	
	private double discount;
	
	private ArrayList<ChargesInvoice>chargeinvList;
	
	private String locationName;
	
	private Vector<Accounts>assesmentList;
	
	private String client = "";
	
	private String dob;
	
	private String  admissionDate;
	
	private String dischargeDate;
	
	private String agegender;
	
	private int ipdid;
	
	ArrayList<Master>masterAssessmentList;
	
	private String preparedby;
	
	private int checkipdinvoice;
	
	private String  resetinv;
	
	private String invoicenameid;
	private String invoicename;
	
	private String discAmmount;
	private String refundAmountx;
	private double newdiscount;
	
	public double getNewdiscount() {
		return newdiscount;
	}

	public void setNewdiscount(double newdiscount) {
		this.newdiscount = newdiscount;
	}

	public String getRefundAmountx() {
		return refundAmountx;
	}

	public void setRefundAmountx(String refundAmountx) {
		this.refundAmountx = refundAmountx;
	}

	public String getDiscAmmount() {
		return discAmmount;
	}

	public void setDiscAmmount(String discAmmount) {
		this.discAmmount = discAmmount;
	}

	public String getInvoicenameid() {
		return invoicenameid;
	}

	public void setInvoicenameid(String invoicenameid) {
		this.invoicenameid = invoicenameid;
	}

	public String getInvoicename() {
		return invoicename;
	}

	public void setInvoicename(String invoicename) {
		this.invoicename = invoicename;
	}

	public String getResetinv() {
		return resetinv;
	}

	public void setResetinv(String resetinv) {
		this.resetinv = resetinv;
	}

	public int getCheckipdinvoice() {
		return checkipdinvoice;
	}

	public void setCheckipdinvoice(int checkipdinvoice) {
		this.checkipdinvoice = checkipdinvoice;
	}

	public String getPreparedby() {
		return preparedby;
	}

	public void setPreparedby(String preparedby) {
		this.preparedby = preparedby;
	}

	public ArrayList<Master> getMasterAssessmentList() {
		return masterAssessmentList;
	}

	public void setMasterAssessmentList(ArrayList<Master> masterAssessmentList) {
		this.masterAssessmentList = masterAssessmentList;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public int getIpdid() {
		return ipdid;
	}

	public void setIpdid(int ipdid) {
		this.ipdid = ipdid;
	}

	public Vector<Accounts> getAssesmentList() {
		return assesmentList;
	}

	public void setAssesmentList(Vector<Accounts> assesmentList) {
		this.assesmentList = assesmentList;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public ArrayList<ChargesInvoice> getChargeinvList() {
		return chargeinvList;
	}

	public void setChargeinvList(ArrayList<ChargesInvoice> chargeinvList) {
		this.chargeinvList = chargeinvList;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public ArrayList<ChargesInvoice> getChargeInvoiceList() {
		return chargeInvoiceList;
	}

	public void setChargeInvoiceList(ArrayList<ChargesInvoice> chargeInvoiceList) {
		this.chargeInvoiceList = chargeInvoiceList;
	}

	public String getChargeInvoiceid() {
		return chargeInvoiceid;
	}

	public void setChargeInvoiceid(String chargeInvoiceid) {
		this.chargeInvoiceid = chargeInvoiceid;
	}

	public ArrayList<Charges> getInvoiceChargeList() {
		return invoiceChargeList;
	}

	public void setInvoiceChargeList(ArrayList<Charges> invoiceChargeList) {
		this.invoiceChargeList = invoiceChargeList;
	}

	public String getTotalAmountx() {
		return totalAmountx;
	}

	public void setTotalAmountx(String totalAmountx) {
		this.totalAmountx = totalAmountx;
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

	public String getDebitAmountx() {
		return debitAmountx;
	}

	public void setDebitAmountx(String debitAmountx) {
		this.debitAmountx = debitAmountx;
	}

	public String getBalancex() {
		return balancex;
	}

	public void setBalancex(String balancex) {
		this.balancex = balancex;
	}

	private String howPaid;
	
	private String selectedPayby;
	
	private boolean chargesInvoiced;
	
	
	private String clientAdrs;
	private String policyNo;
	private String clientDob;
	
	
	public String getClientAdrs() {
		return clientAdrs;
	}

	public void setClientAdrs(String clientAdrs) {
		this.clientAdrs = clientAdrs;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getClientDob() {
		return clientDob;
	}

	public void setClientDob(String clientDob) {
		this.clientDob = clientDob;
	}

	public boolean isChargesInvoiced() {
		return chargesInvoiced;
	}

	public void setChargesInvoiced(boolean chargesInvoiced) {
		this.chargesInvoiced = chargesInvoiced;
	}

	public String getSelectedPayby() {
		return selectedPayby;
	}

	public void setSelectedPayby(String selectedPayby) {
		this.selectedPayby = selectedPayby;
	}

	private int payBy;
	private String whoPay;
	private String apptId;
	private String treatmentEpisodeName;
	private double totalAmount;
	
	private String totalAmountx;
	
	public double getBalanceTotal() {
		return balanceTotal;
	}

	public void setBalanceTotal(double balanceTotal) {
		this.balanceTotal = balanceTotal;
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

	private double creditTotal;
	private double debitTotal;
	private double balanceTotal;
	
	private String creditTotalx;
	private String debitTotalx;
	private String balanceTotalx;
	
	private String initial;
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

	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String location;
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

	private String thirdParty;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTreatmentEpisodeName() {
		return treatmentEpisodeName;
	}

	public void setTreatmentEpisodeName(String treatmentEpisodeName) {
		this.treatmentEpisodeName = treatmentEpisodeName;
	}

	public String getApptId() {
		return apptId;
	}

	public void setApptId(String apptId) {
		this.apptId = apptId;
	}

	public String getWhoPay() {
		return whoPay;
	}

	public void setWhoPay(String whoPay) {
		this.whoPay = whoPay;
	}

	private String invoiceNo;
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
	
	public int getPayBy() {
		return payBy;
	}

	public void setPayBy(int payBy) {
		this.payBy = payBy;
	}

	public String getHowPaid() {
		return howPaid;
	}

	public void setHowPaid(String howPaid) {
		this.howPaid = howPaid;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	private double payAmount;
	
	private int numberOfChages;
	
	private String charges;
	
	private String payby;
	
	private double debitAmount;
	
	private String debitAmountx;
	
	private String chargeType;
	private double balance;
	
	private String balancex;
	
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

	private String fromDate;
	private String toDate;
	private String email;
	private String mobno;
	private String creditAmt;
	
	private String debitAmounnt;
	private int policyexcesscode = 0;
	private String policyExcess;
	private String payAmountx;
	private String submitInvoiceNotes;
	private String paymode;
	
	private String payeename;
	private String payeeadress;
	private String payeeTown;
	private String payeePostcode;
	private String payeeEmail;
	private String payeeConatctNo;
	
	
	
	public String getPayeeConatctNo() {
		return payeeConatctNo;
	}

	public void setPayeeConatctNo(String payeeConatctNo) {
		this.payeeConatctNo = payeeConatctNo;
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

	public String getSubmitInvoiceNotes() {
		return submitInvoiceNotes;
	}

	public void setSubmitInvoiceNotes(String submitInvoiceNotes) {
		this.submitInvoiceNotes = submitInvoiceNotes;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getPayAmountx() {
		return payAmountx;
	}

	public void setPayAmountx(String payAmountx) {
		this.payAmountx = payAmountx;
	}

	public int getPolicyexcesscode() {
		return policyexcesscode;
	}

	public void setPolicyexcesscode(int policyexcesscode) {
		this.policyexcesscode = policyexcesscode;
	}

	public String getPolicyExcess() {
		return policyExcess;
	}

	public void setPolicyExcess(String policyExcess) {
		this.policyExcess = policyExcess;
	}

	public String getDebitAmounnt() {
		return debitAmounnt;
	}

	public void setDebitAmounnt(String debitAmounnt) {
		this.debitAmounnt = debitAmounnt;
	}

	public String getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public int getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getCreditCharge() {
		return creditCharge;
	}

	public void setCreditCharge(String creditCharge) {
		this.creditCharge = creditCharge;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientid() {
		return clientId;
	}

	public void setClientid(int clientid) {
		this.clientId = clientid;
	}

	public int getPractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(int practitionerId) {
		this.practitionerId = practitionerId;
	}

	public String getClientName() {
		return ClientName;
	}

	public void setClientName(String clientName) {
		ClientName = clientName;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public int getNumberOfChages() {
		return numberOfChages;
	}

	public void setNumberOfChages(int numberOfChages) {
		this.numberOfChages = numberOfChages;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
	
	public String getIpdopdseq() {
		return ipdopdseq;
	}

	public void setIpdopdseq(String ipdopdseq) {
		this.ipdopdseq = ipdopdseq;
	}

	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}

	public String getIpseqno() {
		return ipseqno;
	}

	public void setIpseqno(String ipseqno) {
		this.ipseqno = ipseqno;
	}

	public ArrayList<Accounts> getRefundList() {
		return refundList;
	}

	public void setRefundList(ArrayList<Accounts> refundList) {
		this.refundList = refundList;
	}

	public String getBalanceN() {
		return balanceN;
	}

	public void setBalanceN(String balanceN) {
		this.balanceN = balanceN;
	}

	public ArrayList<Accounts> getPrepaymentList() {
		return prepaymentList;
	}

	public void setPrepaymentList(ArrayList<Accounts> prepaymentList) {
		this.prepaymentList = prepaymentList;
	}

	public ArrayList<Accounts> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(ArrayList<Accounts> transactionList) {
		this.transactionList = transactionList;
	}

	public String getCancelled() {
		return cancelled;
	}

	public void setCancelled(String cancelled) {
		this.cancelled = cancelled;
	}

	private String ipdopdseq;
private String itype,ipseqno;
private ArrayList<Accounts> refundList,prepaymentList,transactionList;
private String balanceN;
private String cancelled;
}
