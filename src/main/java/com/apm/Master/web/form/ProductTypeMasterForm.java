package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;

public class ProductTypeMasterForm {
	private String searchText;
	private int id;
	private String name;
	ArrayList<Master> masterlist;
	private String mastername;
	ArrayList<Master> productypelist;
	private String pagerecords;
	private String totalRecords;
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
	public ArrayList<Master> getProductypelist() {
		return productypelist;
	}
	public void setProductypelist(ArrayList<Master> productypelist) {
		this.productypelist = productypelist;
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
