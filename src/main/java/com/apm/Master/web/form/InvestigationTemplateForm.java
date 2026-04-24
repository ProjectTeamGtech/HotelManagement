package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.InvestigationTemplate;
import com.apm.Master.eu.entity.Master;

public class InvestigationTemplateForm {
	ArrayList<Master> masterlist;
	ArrayList<InvestigationTemplate> invsTypeList;
	public String pagerecords;
	private String totalRecords;
	private String title;
	private String template_text;
	private String invType;
	private String mastername;
	private String SearchText;
	public String inv_id;
	public int id;
	private String investigationtype;
	private ArrayList<Master> invsSectionList;
	 private String invs_section_name;
	 private String invs_section_id="0";
	
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
	public ArrayList<InvestigationTemplate> getInvsTypeList() {
		return invsTypeList;
	}
	public void setInvsTypeList(ArrayList<InvestigationTemplate> invsTypeList) {
		this.invsTypeList = invsTypeList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTemplate_text() {
		return template_text;
	}
	public void setTemplate_text(String template_text) {
		this.template_text = template_text;
	}
	public String getSearchText() {
		return SearchText;
	}
	public void setSearchText(String searchText) {
		SearchText = searchText;
	}
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
	public String getInv_id() {
		return inv_id;
	}
	public void setInv_id(String inv_id) {
		this.inv_id = inv_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvestigationtype() {
		return investigationtype;
	}
	public void setInvestigationtype(String investigationtype) {
		this.investigationtype = investigationtype;
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
	public ArrayList<Master> getInvsSectionList() {
		return invsSectionList;
	}
	public void setInvsSectionList(ArrayList<Master> invsSectionList) {
		this.invsSectionList = invsSectionList;
	}
	public String getInvs_section_name() {
		return invs_section_name;
	}
	public void setInvs_section_name(String invs_section_name) {
		this.invs_section_name = invs_section_name;
	}
	public String getInvs_section_id() {
		return invs_section_id;
	}
	public void setInvs_section_id(String invs_section_id) {
		this.invs_section_id = invs_section_id;
	}
	
	
}
