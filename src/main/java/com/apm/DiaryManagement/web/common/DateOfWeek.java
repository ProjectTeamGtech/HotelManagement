package com.apm.DiaryManagement.web.common;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.Tdcode;

public class DateOfWeek {
	
	private String dateName;
	
	private ArrayList<DiaryManagement>userList ;
	
	private ArrayList<Tdcode>tdcodelist;
	
	private String weekListname;
	
	private String colorName;
	
	private String tdDateName;
	
	

	

	public String getTdDateName() {
		return tdDateName;
	}

	public void setTdDateName(String tdDateName) {
		this.tdDateName = tdDateName;
	}

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	public String getDateName() {
		return dateName;
	}

	public void setDateName(String dateName) {
		this.dateName = dateName;
	}

	public ArrayList<Tdcode> getTdcodelist() {
		return tdcodelist;
	}

	public void setTdcodelist(ArrayList<Tdcode> tdcodelist) {
		this.tdcodelist = tdcodelist;
	}

	public String getWeekListname() {
		return weekListname;
	}

	public void setWeekListname(String weekListname) {
		this.weekListname = weekListname;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

}
