package com.apm.Ambulance.web.form;

import java.util.ArrayList;

import com.apm.Ambulance.eu.entity.Duty;
import com.apm.Master.eu.entity.Master;

public class DutyForm {
	
	private String id;
	private String patient;
	private String city;
	private String pickdrop;
	private String hosp;
	private String currentdate;
	private String searchText;
	private String pagerecords;
	private String totalRecords;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private String driverid;
	private String toDate;
	private String fromDate;
	private String firstname;
	private String lastname;
	private String drivername;
	ArrayList<Duty>ambreportlist;
	ArrayList<Duty>dutylist;
	ArrayList<Duty>punchkarmarpt;
	ArrayList<Master>disciplineList;
	private String template_text;
	private String parentid;
	private String date_time;
	private String text;
	
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getTemplate_text() {
		return template_text;
	}
	public void setTemplate_text(String template_text) {
		this.template_text = template_text;
	}
	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}
	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}
	public ArrayList<Duty> getPunchkarmarpt() {
		return punchkarmarpt;
	}
	public void setPunchkarmarpt(ArrayList<Duty> punchkarmarpt) {
		this.punchkarmarpt = punchkarmarpt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPickdrop() {
		return pickdrop;
	}
	public void setPickdrop(String pickdrop) {
		this.pickdrop = pickdrop;
	}
	public String getHosp() {
		return hosp;
	}
	public void setHosp(String hosp) {
		this.hosp = hosp;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public ArrayList<Duty> getDutylist() {
		return dutylist;
	}
	public void setDutylist(ArrayList<Duty> dutylist) {
		this.dutylist = dutylist;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getPagerecords() {
		return pagerecords;
	}
	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public ArrayList<Duty> getAmbreportlist() {
		return ambreportlist;
	}
	public void setAmbreportlist(ArrayList<Duty> ambreportlist) {
		this.ambreportlist = ambreportlist;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	
	
	

}
