package com.apm.Master.web.form;

import java.util.ArrayList;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;

public class StateForm {
	private String searchText;
	private int id;
	private String name;
	
	ArrayList<State> statelist;
	ArrayList<Master> masterlist;
	private String mastername;
	private String pagerecords;
	private String totalRecords;
	public ArrayList<State> getStatelist() {
		return statelist;
	}
	public void setStatelist(ArrayList<State> statelist) {
		this.statelist = statelist;
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
	
	
}
