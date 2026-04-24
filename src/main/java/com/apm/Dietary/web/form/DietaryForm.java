package com.apm.Dietary.web.form;

import java.util.ArrayList;

import com.apm.Dietary.eu.entity.Dietary;
import com.apm.Master.eu.entity.Master;

public class DietaryForm {
	private String wardid;
	private String bedid;
	private String lastmodified;
	private String ipdid;
	private String wardname;
	private String bedname;
	private String clientname;
	
	private int id;
	private String name;
	private String description;
	ArrayList<Dietary> listdietary; 
	ArrayList<Master> masterlist;
	
	private String mastername;
	
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
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
	public ArrayList<Dietary> getListdietary() {
		return listdietary;
	}
	public void setListdietary(ArrayList<Dietary> listdietary) {
		this.listdietary = listdietary;
	}
}
