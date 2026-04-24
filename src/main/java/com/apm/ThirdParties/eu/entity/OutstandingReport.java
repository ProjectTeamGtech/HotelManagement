package com.apm.ThirdParties.eu.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;

public class OutstandingReport {
	
	private String thirdPartyName;
	private String ccEmail;
	private String invoiceNo;
	private double balanceTotal;
	private String creditReminderDuration = "";
	private Date currentdate;
	private double discount;
	
	private String selectedInvoiceid;
	
	ArrayList<Client>clientList;
	
	ArrayList<Accounts>invoiceList;
	
	ArrayList<Accounts>clientInvoiceList;
	
	private String allotamount;
	
	private String bankname;
	private String paymentType;
	private String cheqType;
	
	private String cheqNo;
	private String handoverTo;
	
	private String selfcharge;
	private String advance;
	private String refundAmt1;
	private String selfcredit;
	
	
	

	public String getRefundAmt1() {
		return refundAmt1;
	}
	public void setRefundAmt1(String refundAmt1) {
		this.refundAmt1 = refundAmt1;
	}
	public String getSelfcredit() {
		return selfcredit;
	}
	public void setSelfcredit(String selfcredit) {
		this.selfcredit = selfcredit;
	}
	public String getAdvance() {
		return advance;
	}
	public void setAdvance(String advance) {
		this.advance = advance;
	}
	public String getSelfcharge() {
		return selfcharge;
	}
	public void setSelfcharge(String selfcharge) {
		this.selfcharge = selfcharge;
	}
	public ArrayList<Accounts> getClientInvoiceList() {
		return clientInvoiceList;
	}
	public void setClientInvoiceList(ArrayList<Accounts> clientInvoiceList) {
		this.clientInvoiceList = clientInvoiceList;
	}
	public ArrayList<Client> getClientList() {
		return clientList;
	}
	public void setClientList(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}
	public ArrayList<Accounts> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(ArrayList<Accounts> invoiceList) {
		this.invoiceList = invoiceList;
	}
	public String getSelectedInvoiceid() {
		return selectedInvoiceid;
	}
	public void setSelectedInvoiceid(String selectedInvoiceid) {
		this.selectedInvoiceid = selectedInvoiceid;
	}
	public String getBalanceTotalx() {
		return balanceTotalx;
	}
	public void setBalanceTotalx(String balanceTotalx) {
		this.balanceTotalx = balanceTotalx;
	}
	public String getDiscountx() {
		return discountx;
	}
	public void setDiscountx(String discountx) {
		this.discountx = discountx;
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
	public String getDebitx() {
		return debitx;
	}
	public void setDebitx(String debitx) {
		this.debitx = debitx;
	}
	public String getCreditx() {
		return creditx;
	}
	public void setCreditx(String creditx) {
		this.creditx = creditx;
	}
	public String getBalancex() {
		return balancex;
	}
	public void setBalancex(String balancex) {
		this.balancex = balancex;
	}
	private String chargeType;
	
	private String balanceTotalx;
	private String discountx;
	
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		discount = Double.parseDouble(new DecimalFormat("##.##").format(discount));

		this.discount = discount;
	}
	public Date getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(Date currentdate) {
		this.currentdate = currentdate;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	private Date invoiceDate;
	public String getCreditReminderDuration() {
		return creditReminderDuration;
	}
	public void setCreditReminderDuration(String creditReminderDuration) {
		this.creditReminderDuration = creditReminderDuration;
	}
	private int noOfCharges;
	
	public int getNoOfCharges() {
		return noOfCharges;
	}
	public void setNoOfCharges(int noOfCharges) {
		this.noOfCharges = noOfCharges;
	}
	public double getBalanceTotal() {
		return balanceTotal;
	}
	public void setBalanceTotal(double balanceTotal) {
		balanceTotal = Double.parseDouble(new DecimalFormat("##.##").format(balanceTotal));

		this.balanceTotal = balanceTotal;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCcEmail() {
		return ccEmail;
	}
	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
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
		creditWarningLimit = Double.parseDouble(new DecimalFormat("##.##").format(creditWarningLimit));

		this.creditWarningLimit = creditWarningLimit;
	}
	public double getOutstandingInvoiceLimit() {
		return outstandingInvoiceLimit;
	}
	public void setOutstandingInvoiceLimit(double outstandingInvoiceLimit) {
		outstandingInvoiceLimit = Double.parseDouble(new DecimalFormat("##.##").format(outstandingInvoiceLimit));

		this.outstandingInvoiceLimit = outstandingInvoiceLimit;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		paidAmount = Double.parseDouble(new DecimalFormat("##.##").format(paidAmount));

		this.paidAmount = paidAmount;
	}
	public double getUnpaidAmout() {
		return unpaidAmout;
	}
	public void setUnpaidAmout(double unpaidAmout) {
		unpaidAmout = Double.parseDouble(new DecimalFormat("##.##").format(unpaidAmout));

		this.unpaidAmout = unpaidAmout;
	}
	private double creditWarningLimit;
	private double outstandingInvoiceLimit;
	private double paidAmount;
	private double unpaidAmout;
	private double reminderAmoutLimit;
	private String type;
	private String date;
	private String time;
	private String notes;
	private double debit;
	private String payby;
	
	private String creditWarningLimitx;
	private String outstandingInvoiceLimitx;
	private String paidAmountx;
	private String unpaidAmoutx;
	private String reminderAmoutLimitx;
	private String debitx;
	
	public String getPayby() {
		return payby;
	}
	public void setPayby(String payby) {
		this.payby = payby;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		debit = Double.parseDouble(new DecimalFormat("##.##").format(debit));

		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		credit = Double.parseDouble(new DecimalFormat("##.##").format(credit));

		this.credit = credit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		balance = Double.parseDouble(new DecimalFormat("##.##").format(balance));

		this.balance = balance;
	}
	private double credit;
	private double balance;
	
	private String creditx;
	private String balancex;
	
	private int clinicId;
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
	public double getReminderAmoutLimit() {
		return reminderAmoutLimit;
	}
	public void setReminderAmoutLimit(double reminderAmoutLimit) {
		this.reminderAmoutLimit = reminderAmoutLimit;
	}
	private int id;
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
	private int thirdPartyId;
	private String thirdPartyContactno;
	private String thirdPartEmail;
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
	public String getAllotamount() {
		return allotamount;
	}
	public void setAllotamount(String allotamount) {
		this.allotamount = allotamount;
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
