package com.apm.DiaryManagement.eu.entity;

public class IpdFakeConsult {
	
	private String doctor;
	private int id;
	private String patientName;
	private String speciality;
	private String commencing="";
	private String ipdid;
	private String autreatmentid;
	private String patientcount;
	public String getPatientcount() {
		return patientcount;
	}

	public void setPatientcount(String patientcount) {
		this.patientcount = patientcount;
	}

	public String getAutreatmentid() {
		return autreatmentid;
	}

	public void setAutreatmentid(String autreatmentid) {
		this.autreatmentid = autreatmentid;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getAdmissiondate() {
		return admissiondate;
	}

	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}

	private String clientid;
	private String regdate;
	private String admissiondate;

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	

}
