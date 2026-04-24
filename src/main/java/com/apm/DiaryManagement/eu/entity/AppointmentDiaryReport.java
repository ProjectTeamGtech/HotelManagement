package com.apm.DiaryManagement.eu.entity;

public class AppointmentDiaryReport{
	private int id;
	private String diaryUser;
	private String fromDate;
	private String toDate;
	private String date;
	private String startTime;
	private String duration;
	
	private String clientName;
	private String newClient;
	private String exist;
	private String attend;
	private String dna;
	private String notes;
	private String dept;
	private String endTime;
	private String room;
	private String aptmtype;
	
	private boolean arrived=false;
	private boolean beingSeen=false;
	private boolean completed=false;
	private boolean doNotAttend=false;
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiaryUser() {
		return diaryUser;
	}
	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getNewClient() {
		return newClient;
	}
	public void setNewClient(String newClient) {
		this.newClient = newClient;
	}
	public String getExist() {
		return exist;
	}
	public void setExist(String exist) {
		this.exist = exist;
	}
	public String getAttend() {
		return attend;
	}
	public void setAttend(String attend) {
		this.attend = attend;
	}
	public String getDna() {
		return dna;
	}
	public void setDna(String dna) {
		this.dna = dna;
	}
	public boolean isArrived() {
		return arrived;
	}
	public void setArrived(boolean arrived) {
		this.arrived = arrived;
	}
	public boolean isBeingSeen() {
		return beingSeen;
	}
	public void setBeingSeen(boolean beingSeen) {
		this.beingSeen = beingSeen;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public boolean isDoNotAttend() {
		return doNotAttend;
	}
	public void setDoNotAttend(boolean doNotAttend) {
		this.doNotAttend = doNotAttend;
	}
	public String getAptmtype() {
		return aptmtype;
	}
	public void setAptmtype(String aptmtype) {
		this.aptmtype = aptmtype;
	}
}