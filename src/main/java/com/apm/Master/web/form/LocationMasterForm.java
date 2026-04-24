package com.apm.Master.web.form;

import java.util.ArrayList;


import com.apm.Master.eu.entity.Master;

public class LocationMasterForm {
	private String pharmacycheck;
	private String checked1;
	public String getChecked1() {
		return checked1;
	}
	public void setChecked1(String checked1) {
		this.checked1 = checked1;
	}
	public String getPharmacycheck() {
		return pharmacycheck;
	}
	public void setPharmacycheck(String pharmacycheck) {
		this.pharmacycheck = pharmacycheck;
	}
	private String searchText;
	private int id;
	private String name;
	ArrayList<Master> masterlist;
	private String mastername;
	ArrayList<Master> locationList;
	ArrayList<Master>disciplineList;
	
	private String pagerecords;
	private String totalRecords;
	private String discipline;
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}
	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	public ArrayList<Master> getLocationList() {
		return locationList;
	}
	public void setLocationList(ArrayList<Master> locationList) {
		this.locationList = locationList;
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
	
}
