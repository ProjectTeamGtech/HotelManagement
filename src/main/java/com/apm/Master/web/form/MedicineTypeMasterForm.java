package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.MedicineType;

public class MedicineTypeMasterForm {
	private String searchText;
	private int id;
	private String name;
	private String isstrip;
	ArrayList<Master> masterlist;
	private String mastername;
	ArrayList<MedicineType> medicinetypelist;
	private String pagerecords;
	private String totalRecords;
	public String getSearchText() {
		return searchText;
	}
	public int getId() {
		return id;
	}

	
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	public ArrayList<MedicineType> getMedicinetypelist() {
		return medicinetypelist;
	}
	public void setMedicinetypelist(ArrayList<MedicineType> medicinetypelist) {
		this.medicinetypelist = medicinetypelist;
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
	public String getIsstrip() {
		return isstrip;
	}
	public void setIsstrip(String isstrip) {
		this.isstrip = isstrip;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
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
