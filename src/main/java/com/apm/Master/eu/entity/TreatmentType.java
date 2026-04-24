package com.apm.Master.eu.entity;

public class TreatmentType {
	
	private String icd_code;
	
	private String searchText;
	
	private int id;
	
	private String diaryUser = "";
	
	private String location;
	private String smstmp;
	private String isdeleted;
	private int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public String getTreatmentNotes() {
		return treatmentNotes;
	}

	public void setTreatmentNotes(String treatmentNotes) {
		this.treatmentNotes = treatmentNotes;
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	private String treatmentName;
	
	private String treatmentNotes;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private String dateTime;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getIcd_code() {
		return icd_code;
	}

	public void setIcd_code(String icd_code) {
		this.icd_code = icd_code;
	}

	public String getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getSmstmp() {
		return smstmp;
	}

	public void setSmstmp(String smstmp) {
		this.smstmp = smstmp;
	}
}
