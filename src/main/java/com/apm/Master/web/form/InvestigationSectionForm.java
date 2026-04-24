package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.CityMaster;
import com.apm.Master.eu.entity.InvestigationSection;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;


public class InvestigationSectionForm {
	ArrayList<State> statelist;
	ArrayList<Master> masterlist;
	private String mastername;
	private int id;
	private String name;
	private String searchText;
	private String jobtitle;
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	ArrayList<String> jobTitleList;
	public ArrayList<String> getJobTitleList() {
		return jobTitleList;
	}
	public void setJobTitleList(ArrayList<String> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}
	ArrayList<InvestigationSection> invsSectionList;
	
	private String  pagerecords;
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
	public ArrayList<State> getStatelist() {
		return statelist;
	}
	public void setStatelist(ArrayList<State> statelist) {
		this.statelist = statelist;
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
	public ArrayList<InvestigationSection> getInvsSectionList() {
		return invsSectionList;
	}
	public void setInvsSectionList(ArrayList<InvestigationSection> invsSectionList) {
		this.invsSectionList = invsSectionList;
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
