package com.apm.Appointment.eu.entity;

import java.util.ArrayList;

public class Category {
	
	private int id;
	
	private String categoryName;
	
	private String description;
	
	private String lastupdated;
	

	
	private ArrayList<Branch>branchList;
	

	
	private String searchText = "";
	
	private String productName;
	
	
	
	
	

	public Category(){
		
	}
	
	public Category(int id,String categoryName){
		this.id = id;
		this.categoryName = categoryName;
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	


}
