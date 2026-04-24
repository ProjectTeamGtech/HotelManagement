package com.apm.DiaryManagement.web.form;

import java.util.Vector;

import com.apm.DiaryManagement.eu.entity.IpdFakeConsult;

public class IpdFakeConsultForm {
	
	public boolean isFollowup() {
		return followup;
	}

	public void setFollowup(boolean followup) {
		this.followup = followup;
	}

	private Vector<IpdFakeConsult> docspecialitylist;
	private Vector<IpdFakeConsult>ipdfakepatientlist;
	private String fromDate,commencing;
	private boolean otcheck,followup;

	

	public boolean isOtcheck() {
		return otcheck;
	}

	public void setOtcheck(boolean otcheck) {
		this.otcheck = otcheck;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public Vector<IpdFakeConsult> getIpdfakepatientlist() {
		return ipdfakepatientlist;
	}

	public void setIpdfakepatientlist(Vector<IpdFakeConsult> ipdfakepatientlist) {
		this.ipdfakepatientlist = ipdfakepatientlist;
	}

	public Vector<IpdFakeConsult> getDocspecialitylist() {
		return docspecialitylist;
	}

	public void setDocspecialitylist(Vector<IpdFakeConsult> docspecialitylist) {
		this.docspecialitylist = docspecialitylist;
	}

}
