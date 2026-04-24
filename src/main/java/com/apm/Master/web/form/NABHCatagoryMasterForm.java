package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;

public class NABHCatagoryMasterForm {
	public String searchText;
	public ArrayList<Master> nabhcatagorylist;
	ArrayList<Master> masterlist;
	private String mastername;
	private String pagerecords;
	private String totalRecords;
	private String name;
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ArrayList<Master> getNabhcatagorylist() {
		return nabhcatagorylist;
	}

	public void setNabhcatagorylist(ArrayList<Master> nabhcatagorylist) {
		this.nabhcatagorylist = nabhcatagorylist;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
