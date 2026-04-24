package com.apm.Master.eu.entity;

public class ShelfMaster {

	private int id;

	private String name;
	
	private String departmentid;
	
	private String departmentname;
	
	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getDepartmentid() {
		return departmentid;
	}
	
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
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

}
