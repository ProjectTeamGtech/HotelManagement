package com.apm.Master.eu.entity;

public class Ambulance {

	private String id;
	private String vehicleno;
	private String ambulancetype;
	private String driverfname;
	private String driverlname;
	
	
	
	public String getDriverfname() {
		return driverfname;
	}
	public void setDriverfname(String driverfname) {
		this.driverfname = driverfname;
	}
	public String getDriverlname() {
		return driverlname;
	}
	public void setDriverlname(String driverlname) {
		this.driverlname = driverlname;
	}
	
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getAmbulancetype() {
		return ambulancetype;
	}
	public void setAmbulancetype(String ambulancetype) {
		this.ambulancetype = ambulancetype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
