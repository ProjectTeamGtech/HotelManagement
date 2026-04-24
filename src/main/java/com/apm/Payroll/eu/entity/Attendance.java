package com.apm.Payroll.eu.entity;

import java.util.ArrayList;

public class Attendance {
	
	private int id;
	private String emp_id;
	private String parentid;
	private String showdate;
	private boolean readonly;
	private String commencing;
	private String values;
	private String emp_name;
	private String reason;
	private String date;
	private String weekno;
	private String month;
	private String year;
	private String monday;
	private String workingdayhour;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	private String total_hour;
	private String approvedby, approveremark, approvedate, userremark;
	public String getApprovedby() {
		return approvedby;
	}
	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}
	public String getApproveremark() {
		return approveremark;
	}
	public void setApproveremark(String approveremark) {
		this.approveremark = approveremark;
	}
	public String getApprovedate() {
		return approvedate;
	}
	public void setApprovedate(String approvedate) {
		this.approvedate = approvedate;
	}
	public String getUserremark() {
		return userremark;
	}
	public void setUserremark(String userremark) {
		this.userremark = userremark;
	}
	private String notes;
	private String branch_id;
	private String fromdate;
	private String todate;
	private String basicsalary;
	private String days;
	private String nummonth;
	private String salaryTotal;
	private String leaveday;
	private String remarkintime;
	private String remarkouttime;
	private String inlonglat, outlonglat;
	private ArrayList<Attendance> allcheckinlist;
	public String getInlonglat() {
		return inlonglat;
	}
	public void setInlonglat(String inlonglat) {
		this.inlonglat = inlonglat;
	}
	public String getOutlonglat() {
		return outlonglat;
	}
	public void setOutlonglat(String outlonglat) {
		this.outlonglat = outlonglat;
	}
	public String getRemarkintime() {
		return remarkintime;
	}
	public void setRemarkintime(String remarkintime) {
		this.remarkintime = remarkintime;
	}
	public String getRemarkouttime() {
		return remarkouttime;
	}
	public void setRemarkouttime(String remarkouttime) {
		this.remarkouttime = remarkouttime;
	}
	private boolean flag;
	private int workwithleave;
	/*public double getSalaryTotal() {
		return salaryTotal;
	}
	public void setSalaryTotal(double salaryTotal) {
		this.salaryTotal = salaryTotal;
	}*/
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getSalaryTotal() {
		return salaryTotal;
	}
	public void setSalaryTotal(String salaryTotal) {
		this.salaryTotal = salaryTotal;
	}
	public String getBasicsalary() {
		return basicsalary;
	}
	public void setBasicsalary(String basicsalary) {
		this.basicsalary = basicsalary;
	}
	private String basic;
	
	
	
	
	
	
	
	public String getBasic() {
		return basic;
	}
	public void setBasic(String basic) {
		this.basic = basic;
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
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNummonth() {
		return nummonth;
	}
	public void setNummonth(String nummonth) {
		this.nummonth = nummonth;
	}
	public String getLeaveday() {
		return leaveday;
	}
	public void setLeaveday(String leaveday) {
		this.leaveday = leaveday;
	}
	public int getWorkwithleave() {
		return workwithleave;
	}
	public void setWorkwithleave(int workwithleave) {
		this.workwithleave = workwithleave;
	}
	private String userid;
	private String indatetime;
	private String outdatetime;
	private String totalhour;
	private String status;
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
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public ArrayList<Attendance> getSubattendancelist() {
		return subattendancelist;
	}
	public void setSubattendancelist(ArrayList<Attendance> subattendancelist) {
		this.subattendancelist = subattendancelist;
	}
	public ArrayList<Attendance> getAllcheckinlist() {
		return allcheckinlist;
	}
	public void setAllcheckinlist(ArrayList<Attendance> allcheckinlist) {
		this.allcheckinlist = allcheckinlist;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getCommencing() {
		return commencing;
	}
	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getShowdate() {
		return showdate;
	}
	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	public String getWorkingdayhour() {
		return workingdayhour;
	}
	public void setWorkingdayhour(String workingdayhour) {
		this.workingdayhour = workingdayhour;
	}
	private ArrayList<Attendance> subattendancelist;

}
