package com.apm.DiaryManagement.web.common;

import java.util.ArrayList;

public class Month {
	
	private String monthName;
	
	private ArrayList<DateOfWeek>DateStringList;
	
	ArrayList<Month>monthList;

	public ArrayList<Month> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<Month> monthList) {
		this.monthList = monthList;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public ArrayList<DateOfWeek> getDateStringList() {
		return DateStringList;
	}

	public void setDateStringList(ArrayList<DateOfWeek> dateStringList) {
		DateStringList = dateStringList;
	}

}
