package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.Unit;


public class UnitForm {
	
    private String id;
	private String unitMeasurement;
	private String mastername;
	private String searchText;
	
	ArrayList<Unit>unitlist;
	ArrayList<Master>masterlist;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitMeasurement() {
		return unitMeasurement;
	}
	public void setUnitMeasurement(String unitMeasurement) {
		this.unitMeasurement = unitMeasurement;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	
	public ArrayList<Unit> getUnitlist() {
		return unitlist;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public void setUnitlist(ArrayList<Unit> unitlist) {
		this.unitlist = unitlist;
	}
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}
	
	
	

}
