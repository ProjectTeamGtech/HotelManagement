package com.apm.Emr.eu.entity;

public class MedicalHistory {
	private int id;
	private String medicalRecordType;
	private String medicalHistoryNotes;
	private String description;
	private String lastModified;
	private String practitionerName;
	private String medicalRecordTypeOther;

	
	public String getMedicalRecordTypeOther() {
		return medicalRecordTypeOther;
	}
	public void setMedicalRecordTypeOther(String medicalRecordTypeOther) {
		this.medicalRecordTypeOther = medicalRecordTypeOther;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getPractitionerName() {
		return practitionerName;
	}
	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMedicalRecordType() {
		return medicalRecordType;
	}
	public void setMedicalRecordType(String medicalRecordType) {
		this.medicalRecordType = medicalRecordType;
	}
	public String getMedicalHistoryNotes() {
		return medicalHistoryNotes;
	}
	public void setMedicalHistoryNotes(String medicalHistoryNotes) {
		this.medicalHistoryNotes = medicalHistoryNotes;
	}
	

}
