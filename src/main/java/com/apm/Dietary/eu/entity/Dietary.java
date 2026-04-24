package com.apm.Dietary.eu.entity;

import java.util.ArrayList;

public class Dietary {
	private int id;
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	ArrayList<Dietary>catlist;
	public ArrayList<Dietary> getCatlist() {
		return catlist;
	}
	public void setCatlist(ArrayList<Dietary> catlist) {
		this.catlist = catlist;
	}
	private String name;
	private String description;
	private int select=0;
	public int getSelect() {
		return select;
	}
	public void setSelect(int select) {
		this.select = select;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
