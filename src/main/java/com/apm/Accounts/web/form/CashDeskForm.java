package com.apm.Accounts.web.form;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.CashDesk;

public class CashDeskForm {
	
	private int id;
	
	private int clientId;
	
	private String clientName;
	
	private int amount;
	
	private String datetime;
	
	private int totalRecords;
	
	private String pagerecords;
	
	private String searchText;
	
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private String firstName;
	
	private String lastName;
	
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
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

	
	
	private ArrayList<CashDesk> cashDeskList;

	public ArrayList<CashDesk> getCashDeskList() {
		return cashDeskList;
	}

	public void setCashDeskList(ArrayList<CashDesk> cashDeskList) {
		this.cashDeskList = cashDeskList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	

}
