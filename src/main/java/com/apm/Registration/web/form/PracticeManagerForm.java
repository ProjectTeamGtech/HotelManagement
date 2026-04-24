package com.apm.Registration.web.form;

import java.util.ArrayList;

import com.apm.Registration.eu.entity.PracticeManager;

public class PracticeManagerForm {
	
	private int id;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private String searchText = "";
	
	ArrayList<PracticeManager>practiceManagerList;
	

	public ArrayList<PracticeManager> getPracticeManagerList() {
		return practiceManagerList;
	}

	public void setPracticeManagerList(
			ArrayList<PracticeManager> practiceManagerList) {
		this.practiceManagerList = practiceManagerList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

}
