package com.apm.Diagnosis.web.form;

import java.util.ArrayList;

import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.Master.eu.entity.Master;

public class DiagnosisForm {

	private int id;
	private String name;
	private String description;
	private String intervention;
	private String problem_name;
	
	private String diagnosis_id;
	private String diag_problem_id;
	private String intervention_id;
	private String searchText;
	private String illnessid;
	private String conditionid;
	private String problemid;
	

	private String pagerecords;
	private int totalRecords;
	
	private ArrayList<Master> masterlist;
	
	private ArrayList<Diagnosis> problemlist;
	private ArrayList<Diagnosis> interventionlist;

	
	public ArrayList<Diagnosis> getInterventionlist() {
		return interventionlist;
	}
	public void setInterventionlist(ArrayList<Diagnosis> interventionlist) {
		this.interventionlist = interventionlist;
	}
	public ArrayList<Diagnosis> getProblemlist() {
		return problemlist;
	}
	public void setProblemlist(ArrayList<Diagnosis> problemlist) {
		this.problemlist = problemlist;
	}
	private String mastername;
	
	
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
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
	private ArrayList<Diagnosis> diagnosislist;
	

	
	public String getIllnessid() {
		return illnessid;
	}
	public void setIllnessid(String illnessid) {
		this.illnessid = illnessid;
	}
	public String getConditionid() {
		return conditionid;
	}
	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}
	public String getProblemid() {
		return problemid;
	}
	public void setProblemid(String problemid) {
		this.problemid = problemid;
	}
	public ArrayList<Diagnosis> getDiagnosislist() {
		return diagnosislist;
	}
	public void setDiagnosislist(ArrayList<Diagnosis> diagnosislist) {
		this.diagnosislist = diagnosislist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIntervention() {
		return intervention;
	}
	public void setIntervention(String intervention) {
		this.intervention = intervention;
	}
	public String getProblem_name() {
		return problem_name;
	}
	public void setProblem_name(String problem_name) {
		this.problem_name = problem_name;
	}
	public String getDiagnosis_id() {
		return diagnosis_id;
	}
	public void setDiagnosis_id(String diagnosis_id) {
		this.diagnosis_id = diagnosis_id;
	}
	public String getDiag_problem_id() {
		return diag_problem_id;
	}
	public void setDiag_problem_id(String diag_problem_id) {
		this.diag_problem_id = diag_problem_id;
	}
	public String getIntervention_id() {
		return intervention_id;
	}
	public void setIntervention_id(String intervention_id) {
		this.intervention_id = intervention_id;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public int getLastclinicalnotesid() {
		return lastclinicalnotesid;
	}
	public void setLastclinicalnotesid(int lastclinicalnotesid) {
		this.lastclinicalnotesid = lastclinicalnotesid;
	}
	public String getIpdid() {
		return ipdid;
	}
	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}
	private String clientid;
	private int  lastclinicalnotesid;
	private String ipdid;
}
