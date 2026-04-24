package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.CityMaster;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;

public class CityMasterForm {
	private int totalRecords;
	private String pagerecords;
	private String searchText;
	private int id;
	private String stateid;
	private String statename;
	private String city;
	ArrayList<Master> masterlist;
	private String mastername;
	ArrayList<CityMaster> citylist;
	ArrayList<State> statelist;
	
	public ArrayList<State> getStatelist() {
		return statelist;
	}
	public void setStatelist(ArrayList<State> statelist) {
		this.statelist = statelist;
	}
	public int getId() {
		return id;
	}
	public ArrayList<CityMaster> getCitylist() {
		return citylist;
	}
	public void setCitylist(ArrayList<CityMaster> citylist) {
		this.citylist = citylist;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStateid() {
		return stateid;
	}
	public void setStateid(String stateid) {
		this.stateid = stateid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getPagerecords() {
		return pagerecords;
	}
	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}
	
	
}
