package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Declaration;
import com.apm.Master.eu.entity.Master;

public class DeclarationForm {
	private String searchText;
	private int id;
	private String declarationNotes;
	private String pagerecords;
	private int totalRecords;
	private ArrayList<Declaration> declarationList;
	private String message;
	private String title;

	private String master;
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

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<Declaration> getDeclarationList() {
		return declarationList;
	}

	public void setDeclarationList(ArrayList<Declaration> declarationList) {
		this.declarationList = declarationList;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeclarationNotes() {
		return declarationNotes;
	}

	public void setDeclarationNotes(String declarationNotes) {
		this.declarationNotes = declarationNotes;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

}
