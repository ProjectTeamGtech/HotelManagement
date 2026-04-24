package com.apm.DiaryManagement.web.form;

import java.util.ArrayList;
import java.util.Vector;

import com.apm.DiaryManagement.eu.entity.OpdConsult;

public class OpdConsultForm {

	private Vector<OpdConsult> docspecialitylist;
    private String commencing,patient_num;
    private boolean otcheck;
	
	public String getPatient_num() {
		return patient_num;
	}

	

	public boolean isOtcheck() {
		return otcheck;
	}



	public void setOtcheck(boolean otcheck) {
		this.otcheck = otcheck;
	}



	public void setPatient_num(String patient_num) {
		this.patient_num = patient_num;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public Vector<OpdConsult> getDocspecialitylist() {
		return docspecialitylist;
	}

	public void setDocspecialitylist(Vector<OpdConsult> docspecialitylist) {
		this.docspecialitylist = docspecialitylist;
	}
}
