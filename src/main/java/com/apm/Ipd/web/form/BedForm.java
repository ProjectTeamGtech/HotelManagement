package com.apm.Ipd.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;

public class BedForm {
	String searchText;
	int id;
	String wardid;
	String sectionid;
	String bedname;
	String wardname;
	String equipmentname;
	String sectionname;

	

    private String bedid;
    private ArrayList<Bed> equipmentlist;
    private String pagerecords; 
    private int totalRecords;
    private String mastername;
    private ArrayList<Master> masterlist;

	private int increment;
	private int procedure;
	
	
	
	
	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public int getProcedure() {
		return procedure;
	}

	public void setProcedure(int procedure) {
		this.procedure = procedure;
	}

	public String getBedid() {
		return bedid;
	}

	public void setBedid(String bedid) {
		this.bedid = bedid;
	}

	public ArrayList<Bed> getEquipmentlist() {
		return equipmentlist;
	}

	public void setEquipmentlist(ArrayList<Bed> equipmentlist) {
		this.equipmentlist = equipmentlist;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

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


	ArrayList<Bed> sectionlist;
	    	
	public ArrayList<Bed> getSectionlist() {
		return sectionlist;
	}

	public void setSectionlist(ArrayList<Bed> sectionlist) {
		this.sectionlist = sectionlist;
	}

	public String getWardname() {
		return wardname;
	}

	public void setWardname(String wardname) {
		this.wardname = wardname;
	}

	public String getEquipmentname() {
		return equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	
	ArrayList<Bed> bedlist;
	
	
	public ArrayList<Bed> getBedlist() {
		return bedlist;
	}

	public void setBedlist(ArrayList<Bed> bedlist) {
		this.bedlist = bedlist;
	}

	ArrayList<Bed> wardlist;

	public ArrayList<Bed> getWardlist() {
		return wardlist;
	}

	public void setWardlist(ArrayList<Bed> wardlist) {
		this.wardlist = wardlist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getSectionid() {
		return sectionid;
	}

	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}

	public String getBedname() {
		return bedname;
	}

	public void setBedname(String bedname) {
		this.bedname = bedname;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getCasualty() {
		return casualty;
	}

	public void setCasualty(String casualty) {
		this.casualty = casualty;
	}


	private String casualty;
}
