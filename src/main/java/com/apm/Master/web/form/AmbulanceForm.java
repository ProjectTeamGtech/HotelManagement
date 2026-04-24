package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Ambulance;
import com.apm.Master.eu.entity.Master;

public class AmbulanceForm {

	private String searchText;
	private String id;
	private String vehicleno;
	private String ambulancetype;
	private String driverfname;
	
	
	
	public String getDriverfname() {
		return driverfname;
	}
	public void setDriverfname(String driverfname) {
		this.driverfname = driverfname;
	}
	ArrayList<Ambulance>ambulancetypelist;
	ArrayList<Ambulance>driverlist;
	
	
	public ArrayList<Ambulance> getDriverlist() {
		return driverlist;
	}
	public void setDriverlist(ArrayList<Ambulance> driverlist) {
		this.driverlist = driverlist;
	}
	public ArrayList<Ambulance> getAmbulancetypelist() {
		return ambulancetypelist;
	}
	public void setAmbulancetypelist(ArrayList<Ambulance> ambulancetypelist) {
		this.ambulancetypelist = ambulancetypelist;
	}
	ArrayList<Master> masterlist;
	
	ArrayList<Ambulance>ambulancelist;
	private String mastername;
	private String pagerecords;
	private String totalRecords;
	
	
	
	
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getAmbulancetype() {
		return ambulancetype;
	}
	public void setAmbulancetype(String ambulancetype) {
		this.ambulancetype = ambulancetype;
	}
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}
	public ArrayList<Ambulance> getAmbulancelist() {
		return ambulancelist;
	}
	public void setAmbulancelist(ArrayList<Ambulance> ambulancelist) {
		this.ambulancelist = ambulancelist;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
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
