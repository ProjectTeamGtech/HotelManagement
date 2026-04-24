package com.apm.Emr.eu.entity;

import java.io.InputStream;
import java.util.ArrayList;

import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Ipd.eu.entity.Bed;

public class Emr {	
	
	private int id;
	
	private String starttime;
	private String newabrivationid;
	private String endTime;
	private String department;
	
	private String patientName;
	private String finaldiagnosis;
	private String gender;
	private String secondarydr;
	private int age;
	private String notes;
	private int appointmentid;
	private ArrayList<Emr> secondarylist;
	private String dob;
	private String clientId;
	private String uploadBy;
	
	private String commencing;
	
	private String description;
	
	private String lastModified;
	private String ipdopdemr="0";
	private String emripdid;
	private String hour;	
	
	private String fileName;
	
	private String condition_id;
	
	private String treatmentEpisodeName;
	private String record_type;
	private String doctType;
	private String videoFileName;
	
	private String heading = "";
	
	private int invstid;
	
	private String invstFoleName;
	
	private ArrayList<Bed>addmissionList;
	
	private ArrayList<Bed>dischargeList;
	
	private InputStream dicomimageData;
	private String imgid;
	
	private String equipment;
	private String quantity;
	private int isavilablefordelete;
	
	
	
	
	public int getIsavilablefordelete() {
		return isavilablefordelete;
	}

	public void setIsavilablefordelete(int isavilablefordelete) {
		this.isavilablefordelete = isavilablefordelete;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getImgid() {
		return imgid;
	}

	public void setImgid(String imgid) {
		this.imgid = imgid;
	}

	public InputStream getDicomimageData() {
		return dicomimageData;
	}

	public void setDicomimageData(InputStream dicomimageData) {
		this.dicomimageData = dicomimageData;
	}

	public ArrayList<Bed> getAddmissionList() {
		return addmissionList;
	}

	public void setAddmissionList(ArrayList<Bed> addmissionList) {
		this.addmissionList = addmissionList;
	}

	public ArrayList<Bed> getDischargeList() {
		return dischargeList;
	}

	public void setDischargeList(ArrayList<Bed> dischargeList) {
		this.dischargeList = dischargeList;
	}

	public String getInvstFoleName() {
		return invstFoleName;
	}

	public void setInvstFoleName(String invstFoleName) {
		this.invstFoleName = invstFoleName;
	}

	public int getInvstid() {
		return invstid;
	}

	public void setInvstid(int invstid) {
		this.invstid = invstid;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getVideoFileName() {
		return videoFileName;
	}

	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	public String getConsNote() {
		return consNote;
	}

	public void setConsNote(String consNote) {
		this.consNote = consNote;
	}

	private String consNote;

	public String getDoctType() {
		return doctType;
	}

	public void setDoctType(String doctType) {
		this.doctType = doctType;
	}

	public String getMedicalRecordTypeOther() {
		return medicalRecordTypeOther;
	}

	public void setMedicalRecordTypeOther(String medicalRecordTypeOther) {
		this.medicalRecordTypeOther = medicalRecordTypeOther;
	}

	private String medicalRecordTypeOther;

	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}

	public String getMedicalHistoryNotes() {
		return medicalHistoryNotes;
	}

	public void setMedicalHistoryNotes(String medicalHistoryNotes) {
		this.medicalHistoryNotes = medicalHistoryNotes;
	}

	private String medicalHistoryNotes;
	private ArrayList<NotAvailableSlot> appointmnetList;
	private ArrayList<MedicalHistory> medicalRecordsList;
	private ArrayList<Assessment>imageDataList;

	public ArrayList<Assessment> getImageDataList() {
		return imageDataList;
	}

	public void setImageDataList(ArrayList<Assessment> imageDataList) {
		this.imageDataList = imageDataList;
	}

	public ArrayList<MedicalHistory> getMedicalRecordsList() {
		return medicalRecordsList;
	}

	public void setMedicalRecordsList(ArrayList<MedicalHistory> medicalRecordsList) {
		this.medicalRecordsList = medicalRecordsList;
	}

	private String medicalRecordType;
	
	public String getMedicalRecordType() {
		return medicalRecordType;
	}

	public void setMedicalRecordType(String medicalRecordType) {
		this.medicalRecordType = medicalRecordType;
	}

	public ArrayList<NotAvailableSlot> getAppointmnetList() {
		return appointmnetList;
	}

	public void setAppointmnetList(ArrayList<NotAvailableSlot> appointmnetList) {
		this.appointmnetList = appointmnetList;
	}

	public String getTreatmentEpisodeName() {
		return treatmentEpisodeName;
	}

	public void setTreatmentEpisodeName(String treatmentEpisodeName) {
		this.treatmentEpisodeName = treatmentEpisodeName;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	private String conditionName;
	
	
	public String getCondition_id() {
		return condition_id;
	}

	public void setCondition_id(String condition_id) {
		this.condition_id = condition_id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	private String min;
	
	private String alertDate;
	
	public String getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
	}

	private int patientId;
	
	private int practitionerId;
	
	private String practitionerName;
	
	
	
	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getPractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(int practitionerId) {
		this.practitionerId = practitionerId;
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

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIpdopdemr() {
		return ipdopdemr;
	}

	public void setIpdopdemr(String ipdopdemr) {
		this.ipdopdemr = ipdopdemr;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFinaldiagnosis() {
		return finaldiagnosis;
	}

	public void setFinaldiagnosis(String finaldiagnosis) {
		this.finaldiagnosis = finaldiagnosis;
	}

	public String getEmripdid() {
		return emripdid;
	}

	public void setEmripdid(String emripdid) {
		this.emripdid = emripdid;
	}

	public String getNewabrivationid() {
		return newabrivationid;
	}

	public void setNewabrivationid(String newabrivationid) {
		this.newabrivationid = newabrivationid;
	}

	public String getSecondarydr() {
		return secondarydr;
	}

	public void setSecondarydr(String secondarydr) {
		this.secondarydr = secondarydr;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ArrayList<Emr> getSecondarylist() {
		return secondarylist;
	}

	public void setSecondarylist(ArrayList<Emr> secondarylist) {
		this.secondarylist = secondarylist;
	}

	

}
