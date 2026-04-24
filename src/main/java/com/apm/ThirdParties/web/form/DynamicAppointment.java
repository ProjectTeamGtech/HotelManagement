package com.apm.ThirdParties.web.form;

public class DynamicAppointment {
	private int id;
	private String dnaCharge = "";
	private String apmtName;
	private String apmtCharge = "";
	private String apmtDuaration;
	private String dnaName;
	private boolean offset;
	
	
	
	
	
	
	
	
	public boolean isOffset() {
		return offset;
	}
	public void setOffset(boolean offset) {
		this.offset = offset;
	}
	public String getDnaName() {
		return dnaName;
	}
	public void setDnaName(String dnaName) {
		this.dnaName = dnaName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDnaCharge() {
		return dnaCharge;
	}
	public void setDnaCharge(String dnaCharge) {
		this.dnaCharge = dnaCharge;
	}
	public String getApmtName() {
		return apmtName;
	}
	public void setApmtName(String apmtName) {
		this.apmtName = apmtName;
	}
	public String getApmtCharge() {
		return apmtCharge;
	}
	public void setApmtCharge(String apmtCharge) {
		this.apmtCharge = apmtCharge;
	}
	public String getApmtDuaration() {
		return apmtDuaration;
	}
	public void setApmtDuaration(String apmtDuaration) {
		this.apmtDuaration = apmtDuaration;
	}

}
