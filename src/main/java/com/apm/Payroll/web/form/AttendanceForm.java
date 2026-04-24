package com.apm.Payroll.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Payroll.eu.entity.Attendance;
import com.apm.Payroll.eu.entity.Employee;

public class AttendanceForm {

	public ArrayList<String> getHourList() {
		return hourList;
	}

	public void setHourList(ArrayList<String> hourList) {
		this.hourList = hourList;
	}

	public ArrayList<String> getMinuteList() {
		return minuteList;
	}

	public void setMinuteList(ArrayList<String> minuteList) {
		this.minuteList = minuteList;
	}
	private int id;
	
	private String filter_status;
	private String empnamesearch;
	private String values;
	private String parentid;
	private String workhour;
	private String otallow;
	public String getFilter_status() {
		return filter_status;
	}
	private Collection<Attendance> collectionsal;
	public void setFilter_status(String filter_status) {
		this.filter_status = filter_status;
	}
	private String pagerecords;
	private int totalRecords;
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
	private String emp_id;
	private String emp_name;
	private String reason;
	private String date;
	private String weekno;
	private String month;
	private String year;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	private String total_hour;
	private String notes;
	private String branch_id;
	private String totalmonthday;
	private String weekoff;
	private int workwithleave;
	private String leaveday;
	private String userid;
	private String indatetime;
	private String outdatetime;
	private String totalhour;
	private String status;
	private ArrayList<String>hourList;
	private ArrayList<String>minuteList;
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIndatetime() {
		return indatetime;
	}

	public void setIndatetime(String indatetime) {
		this.indatetime = indatetime;
	}

	public String getOutdatetime() {
		return outdatetime;
	}

	public void setOutdatetime(String outdatetime) {
		this.outdatetime = outdatetime;
	}

	public String getTotalhour() {
		return totalhour;
	}

	public void setTotalhour(String totalhour) {
		this.totalhour = totalhour;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalmonthday() {
		return totalmonthday;
	}

	public void setTotalmonthday(String totalmonthday) {
		this.totalmonthday = totalmonthday;
	}

	public String getWeekoff() {
		return weekoff;
	}

	public void setWeekoff(String weekoff) {
		this.weekoff = weekoff;
	}
	private int daysmonth;
	ArrayList<Attendance> salaryList;
	public ArrayList<Attendance> getSalaryList() {
		return salaryList;
	}

	public void setSalaryList(ArrayList<Attendance> salaryList) {
		this.salaryList = salaryList;
	}

	private String fromdate;
	private String todate;
	private String branch;
	
	
	
	
	Collection<Attendance> attendance;
	
	
	
	
	private ArrayList<Employee> employeelist;

	ArrayList<Employee> branchlist;
	
	
	ArrayList<Attendance> attendanceList;
	
	
	
	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public ArrayList<Attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(ArrayList<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeekno() {
		return weekno;
	}

	public void setWeekno(String weekno) {
		this.weekno = weekno;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	public String getTotal_hour() {
		return total_hour;
	}

	public void setTotal_hour(String total_hour) {
		this.total_hour = total_hour;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ArrayList<Employee> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(ArrayList<Employee> branchlist) {
		this.branchlist = branchlist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Employee> getEmployeelist() {
		return employeelist;
	}

	public void setEmployeelist(ArrayList<Employee> employeelist) {
		this.employeelist = employeelist;
	}

	public Collection<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(Collection<Attendance> attendance) {
		this.attendance = attendance;
	}

	public int getDaysmonth() {
		return daysmonth;
	}

	public void setDaysmonth(int daysmonth) {
		this.daysmonth = daysmonth;
	}

	public String getEmpnamesearch() {
		return empnamesearch;
	}

	public void setEmpnamesearch(String empnamesearch) {
		this.empnamesearch = empnamesearch;
	}

	public int getWorkwithleave() {
		return workwithleave;
	}

	public void setWorkwithleave(int workwithleave) {
		this.workwithleave = workwithleave;
	}

	public String getLeaveday() {
		return leaveday;
	}

	public void setLeaveday(String leaveday) {
		this.leaveday = leaveday;
	}

	public Collection<Attendance> getCollectionsal() {
		return collectionsal;
	}

	public void setCollectionsal(Collection<Attendance> collectionsal) {
		this.collectionsal = collectionsal;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getWorkhour() {
		return workhour;
	}

	public void setWorkhour(String workhour) {
		this.workhour = workhour;
	}

	public String getOtallow() {
		return otallow;
	}

	public void setOtallow(String otallow) {
		this.otallow = otallow;
	}

}
