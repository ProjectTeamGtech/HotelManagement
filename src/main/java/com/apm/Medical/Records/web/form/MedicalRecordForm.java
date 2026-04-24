package com.apm.Medical.Records.web.form;

import java.io.File;
import java.util.ArrayList;

import com.apm.Medical.Records.eu.entity.MedicalRecord;

public class MedicalRecordForm {
	private int id;
	private String date;
	private String subject;
	private String description;
	private String filename;
	private String message;
	private File userImage;
	private String userImageContentType;
	private String pagerecords;
	private int totalRecords;
	private String searchText = "";
	private String clientId;
	private String client;
	private String clientName;
	private String searchClientId = "";
	public String getSearchClientId() {
		return searchClientId;
	}
	public void setSearchClientId(String searchClientId) {
		this.searchClientId = searchClientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	private ArrayList<MedicalRecord>medicalRecordList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<MedicalRecord> getMedicalRecordList() {
		return medicalRecordList;
	}
	public void setMedicalRecordList(ArrayList<MedicalRecord> medicalRecordList) {
		this.medicalRecordList = medicalRecordList;
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
	public String getUserImageContentType() {
		return userImageContentType;
	}
	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}
	public String getUserImageFileName() {
		return userImageFileName;
	}
	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}
	private String userImageFileName;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public File getUserImage() {
		return userImage;
	}
	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}
	
}
