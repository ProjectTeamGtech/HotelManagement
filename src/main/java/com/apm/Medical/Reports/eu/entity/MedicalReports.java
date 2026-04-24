package com.apm.Medical.Reports.eu.entity;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.ThirdParties.eu.entity.ThirdParty;

public class MedicalReports {
	
	private int id;
	
	private String invoiceId;
	
	private double amountBeforeThirty;
	
	private double amountAfterThirty;
	
	private double amountAfterSixty;
	
	private double amountAfterHundred;
	
	private String amountBeforeThirtyx = "0.00";
	
	private String amountAfterThirtyx = "0.00";;
	
	private String amountAfterSixtyx = "0.00";;
	
	private String amountAfterHundredx = "0.00";;

	
	private String name;
	
	private String address;
	
	private String contactNo;
	
	private String emailid;
	
	private String clientName;
	
	private String postcode;
	
	
	
	
	
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	private MedicalClients clientDetails;
	
	private double debit;
	
	private double paid;
	
	private String payBy;
	
	private String date;
	
	
	
	

	public String getAmountBeforeThirtyx() {
		return amountBeforeThirtyx;
	}

	public void setAmountBeforeThirtyx(String amountBeforeThirtyx) {
		this.amountBeforeThirtyx = amountBeforeThirtyx;
	}

	public String getAmountAfterThirtyx() {
		return amountAfterThirtyx;
	}

	public void setAmountAfterThirtyx(String amountAfterThirtyx) {
		this.amountAfterThirtyx = amountAfterThirtyx;
	}

	public String getAmountAfterSixtyx() {
		return amountAfterSixtyx;
	}

	public void setAmountAfterSixtyx(String amountAfterSixtyx) {
		this.amountAfterSixtyx = amountAfterSixtyx;
	}

	public String getAmountAfterHundredx() {
		return amountAfterHundredx;
	}

	public void setAmountAfterHundredx(String amountAfterHundredx) {
		this.amountAfterHundredx = amountAfterHundredx;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPayBy() {
		return payBy;
	}

	public void setPayBy(String payBy) {
		this.payBy = payBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public double getAmountBeforeThirty() {
		return amountBeforeThirty;
	}

	public void setAmountBeforeThirty(double amountBeforeThirty) {
		this.amountBeforeThirty = amountBeforeThirty;
	}

	public double getAmountAfterThirty() {
		return amountAfterThirty;
	}

	public void setAmountAfterThirty(double amountAfterThirty) {
		this.amountAfterThirty = amountAfterThirty;
	}

	public double getAmountAfterSixty() {
		return amountAfterSixty;
	}

	public void setAmountAfterSixty(double amountAfterSixty) {
		this.amountAfterSixty = amountAfterSixty;
	}

	public double getAmountAfterHundred() {
		return amountAfterHundred;
	}

	public void setAmountAfterHundred(double amountAfterHundred) {
		this.amountAfterHundred = amountAfterHundred;
	}

	public MedicalClients getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(MedicalClients clientDetails) {
		this.clientDetails = clientDetails;
	}

	
	
	
	

}
