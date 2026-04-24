package com.apm.ThirdParties.web.form;

import java.util.ArrayList;

import com.apm.ThirdParties.eu.entity.GP;

public class GPForm {
	
	private int id;
	
	private String thirdPartyId;
	
	private String thirdPartyType;	
	
	private String thirdPartyTypeId;
	
	private String message;
	
	private String searchText;
	
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getThirdPartyTypeId() {
		return thirdPartyTypeId;
	}

	public void setThirdPartyTypeId(String thirdPartyTypeId) {
		this.thirdPartyTypeId = thirdPartyTypeId;
	}

	private String thirdPartyName;
	
	private String thirdPartyCompanyName;
	
	private String name;
	
	private String fax;
	
	private String workphno;
	
	private String emailid;
	
	private String description;
	
	private String pagerecords;
	
	private int totalRecords;
	
	ArrayList<GP> gpList; 
	
	ArrayList<GP> tpCompanyList;
	
	public ArrayList<GP> getTpCompanyList() {
		return tpCompanyList;
	}

	public void setTpCompanyList(ArrayList<GP> tpCompanyList) {
		this.tpCompanyList = tpCompanyList;
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
	
	public ArrayList<GP> getGpList() {
		return gpList;
	}

	public void setGpList(ArrayList<GP> gpList) {
		this.gpList = gpList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getThirdPartyId() {
		return thirdPartyId;
	}

	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}

	public String getThirdPartyType() {
		return thirdPartyType;
	}

	public void setThirdPartyType(String thirdPartyType) {
		this.thirdPartyType = thirdPartyType;
	}

	public String getThirdPartyName() {
		return thirdPartyName;
	}

	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}

	public String getThirdPartyCompanyName() {
		return thirdPartyCompanyName;
	}

	public void setThirdPartyCompanyName(String thirdPartyCompanyName) {
		this.thirdPartyCompanyName = thirdPartyCompanyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWorkphno() {
		return workphno;
	}

	public void setWorkphno(String workphno) {
		this.workphno = workphno;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
