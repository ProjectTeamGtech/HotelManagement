package com.apm.Accounts.eu.entity;

import java.util.ArrayList;

public class ChargesInvoice {
	private int id;
	private String chargeInvoiceid;
	private String chargeInvoiceDebit;
	private int invoiceid;
	private String invoiceDebitx;
	
	private String commencing;
	
	
	
	
	public String getCommencing() {
		return commencing;
	}
	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}
	ArrayList<Charges>chargeInvoList;
	
	
	
	
	public ArrayList<Charges> getChargeInvoList() {
		return chargeInvoList;
	}
	public void setChargeInvoList(ArrayList<Charges> chargeInvoList) {
		this.chargeInvoList = chargeInvoList;
	}
	public String getInvoiceDebitx() {
		return invoiceDebitx;
	}
	public void setInvoiceDebitx(String invoiceDebitx) {
		this.invoiceDebitx = invoiceDebitx;
	}
	public int getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChargeInvoiceid() {
		return chargeInvoiceid;
	}
	public void setChargeInvoiceid(String chargeInvoiceid) {
		this.chargeInvoiceid = chargeInvoiceid;
	}
	public String getChargeInvoiceDebit() {
		return chargeInvoiceDebit;
	}
	public void setChargeInvoiceDebit(String chargeInvoiceDebit) {
		this.chargeInvoiceDebit = chargeInvoiceDebit;
	}
	
	

}
