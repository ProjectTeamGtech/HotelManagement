package com.apm.Log.eu.entity;

public class Modify {
	
	private int id;
	
	private String status;
	
	private String commencing;
	private String apmtId;
	private String date;
	private String time;
	private String addedBy;
	private String apmtStartTime;
	private String apmtDate;
	private String cancelApmtsNotes;
	
	
	
	

	public String getApmtId() {
		return apmtId;
	}

	public void setApmtId(String apmtId) {
		this.apmtId = apmtId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getApmtStartTime() {
		return apmtStartTime;
	}

	public void setApmtStartTime(String apmtStartTime) {
		this.apmtStartTime = apmtStartTime;
	}

	public String getApmtDate() {
		return apmtDate;
	}

	public void setApmtDate(String apmtDate) {
		this.apmtDate = apmtDate;
	}

	public String getCancelApmtsNotes() {
		return cancelApmtsNotes;
	}

	public void setCancelApmtsNotes(String cancelApmtsNotes) {
		this.cancelApmtsNotes = cancelApmtsNotes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
