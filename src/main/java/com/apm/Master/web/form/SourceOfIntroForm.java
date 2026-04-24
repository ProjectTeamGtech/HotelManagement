package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SourceOfIntro;

public class SourceOfIntroForm {

	private int id;

	private String sourceName;

	private String description;

	private String pagerecords;

	private int totalRecords;

	private String dateTime;

	private String message;
	private String masters;
	private String mastername;
	

	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	private ArrayList<Master> masterlist;

	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}

	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}

	public String getMasters() {
		return masters;
	}

	public void setMasters(String masters) {
		this.masters = masters;
	}

	private ArrayList<SourceOfIntro> sourceOfIntroList;

	public ArrayList<SourceOfIntro> getSourceOfIntroList() {
		return sourceOfIntroList;
	}

	public void setSourceOfIntroList(ArrayList<SourceOfIntro> sourceOfIntroList) {
		this.sourceOfIntroList = sourceOfIntroList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
