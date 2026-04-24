package com.apm.DiaryManagement.web.form;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.AppointmentDiaryReport;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;

public class AppointmentDiaryReportForm{
	private String id;
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
	
	private boolean arrived;
	private boolean beingSeen;
	private boolean completed;
	private boolean doNotAttend;
	
	private String[] status;
	private String diaryUser1;
	public String getDiaryUser1() {
		return diaryUser1;
	}
	public void setDiaryUser1(String diaryUser1) {
		this.diaryUser1 = diaryUser1;
	}
	public String getFromDate1() {
		return fromDate1;
	}
	public void setFromDate1(String fromDate1) {
		this.fromDate1 = fromDate1;
	}
	public String getToDate1() {
		return toDate1;
	}
	public void setToDate1(String toDate1) {
		this.toDate1 = toDate1;
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
	private String fromDate1;
	private String toDate1;
	private String pagerecords;
	private int totalRecords;
	private ArrayList<AppointmentDiaryReport>diaryUserList;
	private ArrayList<AppointmentDiaryReport> apmtDiaryReportList;
	private ArrayList<AppointmentDiaryReport>deptList;
	private ArrayList<AppointmentDiaryReport>allWalkInPreBookedList;
	public ArrayList<AppointmentDiaryReport> getAllWalkInPreBookedList() {
		return allWalkInPreBookedList;
	}
	public void setAllWalkInPreBookedList(
			ArrayList<AppointmentDiaryReport> allWalkInPreBookedList) {
		this.allWalkInPreBookedList = allWalkInPreBookedList;
	}
	public ArrayList<AppointmentDiaryReport> getDeptList() {
		return deptList;
	}
	public void setDeptList(ArrayList<AppointmentDiaryReport> deptList) {
		this.deptList = deptList;
	}
	public ArrayList<AppointmentDiaryReport> getApmtDiaryReportList() {
		return apmtDiaryReportList;
	}
	public void setApmtDiaryReportList(
			ArrayList<AppointmentDiaryReport> apmtDiaryReportList) {
		this.apmtDiaryReportList = apmtDiaryReportList;
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
	public ArrayList<AppointmentDiaryReport> getDiaryUserList() {
		return diaryUserList;
	}
	public void setDiaryUserList(ArrayList<AppointmentDiaryReport> diaryUserList) {
		this.diaryUserList = diaryUserList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
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
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}
}