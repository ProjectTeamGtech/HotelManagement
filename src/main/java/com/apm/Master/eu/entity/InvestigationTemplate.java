package com.apm.Master.eu.entity;

import java.util.ArrayList;

public class InvestigationTemplate {
	 private int id;
	 private int INV_id;
	 private String inv_id;
	 private String invType;
	 private String title;
	 private String template_text;
	 private String invs_section_name;
	 private String invs_section_id="0";
	 private String SearchText;
	 private String investigationtype;
	 private String dateTime;
	 
	 ArrayList<InvestigationTemplate> invsTypeList;
	public String getSearchText() {
		return SearchText;
	}
	public void setSearchText(String searchText) {
		SearchText = searchText;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getINV_id() {
		return INV_id;
	}
	public void setINV_id(int inv_id) {
		INV_id = inv_id;
	}
	public ArrayList<InvestigationTemplate> getInvsTypeList() {
		return invsTypeList;
	}
	public void setInvsTypeList(ArrayList<InvestigationTemplate> invsTypeList) {
		this.invsTypeList = invsTypeList;
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
	public String getInvestigationtype() {
		return investigationtype;
	}
	public void setInvestigationtype(String investigationtype) {
		this.investigationtype = investigationtype;
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
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
