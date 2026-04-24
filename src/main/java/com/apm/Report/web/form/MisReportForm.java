package com.apm.Report.web.form;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Report.eu.entity.MisReport;

public class MisReportForm {
	
	private String fromDate = "";
	private String toDate = "";
	ArrayList<MisReport>mislist;
	ArrayList<String>headerList;
	
	private String hdnsendmail = "";
	
	private String count3;
	
	private String per3;
	
	private String count28;
	
	private String per28;
	
	private int dnaCount;
	
	private String perless6;
	
	private int dschargeCount;
	
	private String per5;
	
	private int letterCount;
	
	private String letterPer;
	
	
	
	//Report3
	
	private int newReferalCount;

	private int newRefralTriggered;

	private int notrigerdCount3;

	private int countInitialAssessment;

	private int countFollowupAttendence;

	private int routineCount;

	private int misUrgentCount;

	private int dischargeCount;

	private int countCanceldApmt;
	
	private ArrayList<NotAvailableSlot>greaterThanSixFollowupsList;
	
	private ArrayList<MisReport> opdappointmenttypelist;
	
	
	
	
	public ArrayList<MisReport> getOpdappointmenttypelist() {
		return opdappointmenttypelist;
	}
	public void setOpdappointmenttypelist(ArrayList<MisReport> opdappointmenttypelist) {
		this.opdappointmenttypelist = opdappointmenttypelist;
	}
	public ArrayList<NotAvailableSlot> getGreaterThanSixFollowupsList() {
		return greaterThanSixFollowupsList;
	}
	public void setGreaterThanSixFollowupsList(
			ArrayList<NotAvailableSlot> greaterThanSixFollowupsList) {
		this.greaterThanSixFollowupsList = greaterThanSixFollowupsList;
	}
	public int getNewReferalCount() {
		return newReferalCount;
	}
	public void setNewReferalCount(int newReferalCount) {
		this.newReferalCount = newReferalCount;
	}
	public int getNewRefralTriggered() {
		return newRefralTriggered;
	}
	public void setNewRefralTriggered(int newRefralTriggered) {
		this.newRefralTriggered = newRefralTriggered;
	}
	public int getNotrigerdCount3() {
		return notrigerdCount3;
	}
	public void setNotrigerdCount3(int notrigerdCount3) {
		this.notrigerdCount3 = notrigerdCount3;
	}
	public int getCountInitialAssessment() {
		return countInitialAssessment;
	}
	public void setCountInitialAssessment(int countInitialAssessment) {
		this.countInitialAssessment = countInitialAssessment;
	}
	public int getCountFollowupAttendence() {
		return countFollowupAttendence;
	}
	public void setCountFollowupAttendence(int countFollowupAttendence) {
		this.countFollowupAttendence = countFollowupAttendence;
	}
	public int getRoutineCount() {
		return routineCount;
	}
	public void setRoutineCount(int routineCount) {
		this.routineCount = routineCount;
	}
	public int getMisUrgentCount() {
		return misUrgentCount;
	}
	public void setMisUrgentCount(int misUrgentCount) {
		this.misUrgentCount = misUrgentCount;
	}
	public int getDischargeCount() {
		return dischargeCount;
	}
	public void setDischargeCount(int dischargeCount) {
		this.dischargeCount = dischargeCount;
	}
	public int getCountCanceldApmt() {
		return countCanceldApmt;
	}
	public void setCountCanceldApmt(int countCanceldApmt) {
		this.countCanceldApmt = countCanceldApmt;
	}
	public String getLetterPer() {
		return letterPer;
	}
	public void setLetterPer(String letterPer) {
		this.letterPer = letterPer;
	}
	public int getLetterCount() {
		return letterCount;
	}
	public void setLetterCount(int letterCount) {
		this.letterCount = letterCount;
	}
	public int getDschargeCount() {
		return dschargeCount;
	}
	public void setDschargeCount(int dschargeCount) {
		this.dschargeCount = dschargeCount;
	}
	public String getPer5() {
		return per5;
	}
	public void setPer5(String per5) {
		this.per5 = per5;
	}
	public int getDnaCount() {
		return dnaCount;
	}
	public void setDnaCount(int dnaCount) {
		this.dnaCount = dnaCount;
	}
	public String getPerless6() {
		return perless6;
	}
	public void setPerless6(String perless6) {
		this.perless6 = perless6;
	}
	public String getCount28() {
		return count28;
	}
	public void setCount28(String count28) {
		this.count28 = count28;
	}
	public String getPer28() {
		return per28;
	}
	public void setPer28(String per28) {
		this.per28 = per28;
	}
	public String getCount3() {
		return count3;
	}
	public void setCount3(String count3) {
		this.count3 = count3;
	}
	public String getPer3() {
		return per3;
	}
	public void setPer3(String per3) {
		this.per3 = per3;
	}
	public String getHdnsendmail() {
		return hdnsendmail;
	}
	public void setHdnsendmail(String hdnsendmail) {
		this.hdnsendmail = hdnsendmail;
	}
	public ArrayList<String> getHeaderList() {
		return headerList;
	}
	public void setHeaderList(ArrayList<String> headerList) {
		this.headerList = headerList;
	}
	public ArrayList<MisReport> getMislist() {
		return mislist;
	}
	public void setMislist(ArrayList<MisReport> mislist) {
		this.mislist = mislist;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	

}
