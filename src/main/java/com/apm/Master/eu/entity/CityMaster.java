package com.apm.Master.eu.entity;

public class CityMaster {
	private int id;
	private String stateid;
	private String statename;
	private String city;
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStateid() {
		return stateid;
	}
	public void setStateid(String stateid) {
		this.stateid = stateid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
