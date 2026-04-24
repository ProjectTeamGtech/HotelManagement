package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Registration.eu.entity.Location;

public class TreatmentTypeForm {
	
	private String icd_code;
	
	private String searchText;

	private int id;

	private String treatmentName;

	private String treatmentNotes;

	private String pagerecords;

	private int totalRecords;

	private String dateTime;

	private String message;

	private ArrayList<DiaryManagement> userList;

	private String diaryUser = "";

	private String clientId = "";

	private String client = "";

	private String master;
	
	private String mastername;
	
	ArrayList<Location>locationList;
	
	private String location;
	
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}

	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	private ArrayList<Master> masterlist;

	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}

	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
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

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private ArrayList<TreatmentType> treatmentTypeList;

	public ArrayList<TreatmentType> getTreatmentTypeList() {
		return treatmentTypeList;
	}

	public void setTreatmentTypeList(ArrayList<TreatmentType> treatmentTypeList) {
		this.treatmentTypeList = treatmentTypeList;
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

}
