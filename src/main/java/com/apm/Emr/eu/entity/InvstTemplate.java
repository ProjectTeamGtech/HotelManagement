package com.apm.Emr.eu.entity;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;

public class InvstTemplate {
	
	private int id;
	private String name;
	private String date;
	
	private ArrayList<Master>invstTypeList;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<Master> getInvstTypeList() {
		return invstTypeList;
	}

	public void setInvstTypeList(ArrayList<Master> invstTypeList) {
		this.invstTypeList = invstTypeList;
	}
	
	

}
