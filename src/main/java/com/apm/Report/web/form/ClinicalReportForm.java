package com.apm.Report.web.form;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;

public class ClinicalReportForm {
	
	private String fromDate="";
	
	private String diaryUser;
	
	private String order = "asc";
	
	private String orderby = "";
	
	
	
	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
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

	private String toDate="";
	
	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	ArrayList<TreatmentEpisode>treatmentEpisodeList;
	
	ArrayList<DiaryManagement> userList;

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList() {
		return treatmentEpisodeList;
	}

	public void setTreatmentEpisodeList(
			ArrayList<TreatmentEpisode> treatmentEpisodeList) {
		this.treatmentEpisodeList = treatmentEpisodeList;
	}

}
