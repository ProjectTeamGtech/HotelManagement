package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.ShelfMaster;

public class ShelfMasterForm {
	
	private ArrayList<ShelfMaster> shelflist;

	private int id;

	private String name;
	
	private ArrayList<Master> masterlist;
	
	private String mastername;
	
	private ArrayList<Master> locationlist;
	
	private String departmentid;
	
	
	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public ArrayList<Master> getLocationlist() {
		return locationlist;
	}

	public void setLocationlist(ArrayList<Master> locationlist) {
		this.locationlist = locationlist;
	}

	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
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

	public ArrayList<ShelfMaster> getShelflist() {
		return shelflist;
	}

	public void setShelflist(ArrayList<ShelfMaster> shelflist) {
		this.shelflist = shelflist;
	}
}
