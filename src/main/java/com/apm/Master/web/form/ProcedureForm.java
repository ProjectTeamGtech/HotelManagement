package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.Procedure;
import com.apm.Master.eu.entity.SittingFollowup;

public class ProcedureForm {

	private String id;
	private String name;
	

	private String procedureName;
	private String charges;
	private String proceduretype;
	private String mastername;
	private String searchtext;
	
	
	
	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}

	public String getName() {
		return name;
	}

   public void setName(String name) {
		this.name = name;
	}
	
	
	public String getMastername() {
		return mastername;
	}


	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

    ArrayList<Procedure>plist;
	public ArrayList<Procedure> getPlist() {
		return plist;
	}


	public void setPlist(ArrayList<Procedure> plist) {
		this.plist = plist;
	}

	public ArrayList<Master> getDepartmentlist() {
		return departmentlist;
	}

	public void setDepartmentlist(ArrayList<Master> departmentlist) {
		this.departmentlist = departmentlist;
	}

	ArrayList<Master>masterlist;
	ArrayList<Master>proceduretypelist;
    ArrayList<SittingFollowup>sittinglist;
    ArrayList<Master>departmentlist;

	


	public ArrayList<SittingFollowup> getSittinglist() {
		return sittinglist;
	}

	public void setSittinglist(ArrayList<SittingFollowup> sittinglist) {
		this.sittinglist = sittinglist;
	}

	public ArrayList<Master> getProceduretypelist() {
		return proceduretypelist;
	}


	public void setProceduretypelist(ArrayList<Master> proceduretypelist) {
		this.proceduretypelist = proceduretypelist;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getProcedureName() {
		return procedureName;
	}


	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}


	public String getCharges() {
		return charges;
	}


	public void setCharges(String charges) {
		this.charges = charges;
	}


	public String getProceduretype() {
		return proceduretype;
	}


	public void setProceduretype(String proceduretype) {
		this.proceduretype = proceduretype;
	}


	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}


	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}
	
	
	
	
	
}
