package com.apm.Master.web.form;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SittingFollowup;

public class SittingFollowupForm {
	
    private String id;
	private String sittingFollowup;
	private String mastername;
	private String department;

	public String getDepartment() {
		return department;
	}

    public void setDepartment(String department) {
		this.department = department;
	}

    public String getMastername() {
		return mastername;
	}

    public void setMastername(String mastername) {
		this.mastername = mastername;
	}

    private ArrayList<SittingFollowup>slist;
	private	ArrayList<Master> masterlist;
	private ArrayList<Master>departmentlist;
	

     public ArrayList<Master> getDepartmentlist() {
		return departmentlist;
	}



	public void setDepartmentlist(ArrayList<Master> departmentlist) {
		this.departmentlist = departmentlist;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getSittingFollowup() {
		return sittingFollowup;
	}



	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}



	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}



	public void setSittingFollowup(String sittingFollowup) {
		this.sittingFollowup = sittingFollowup;
	}



	public ArrayList<SittingFollowup> getSlist() {
		return slist;
	}



	public void setSlist(ArrayList<SittingFollowup> slist) {
		this.slist = slist;
	}
	
	
}
