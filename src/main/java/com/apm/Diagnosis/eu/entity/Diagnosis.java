package com.apm.Diagnosis.eu.entity;

public class Diagnosis {

	public String getDiagnosisname() {
		return diagnosisname;
	}
	public void setDiagnosisname(String diagnosisname) {
		this.diagnosisname = diagnosisname;
	}
	private int id;
	 private int autodiagnosis;
	 public int getAutodiagnosis() {
	  return autodiagnosis;
	 }
	 public void setAutodiagnosis(int autodiagnosis) {
	  this.autodiagnosis = autodiagnosis;
	 }
	private String name;
	private String description;
	private String intervention;
	private String problem_name;
	
	private String diagnosis_id;
	private String diag_problem_id;
	private String intervention_id;
	
	private String illnessid;
	private String conditionid;
	private String problemid;
	private String icdcode="";
	private String department="0";
	private int dragid;
	private int dragprobid;
    private String diagnosisname;
	

	public int getDragid() {
		return dragid;
	}
	public void setDragid(int dragid) {
		this.dragid = dragid;
	}
	public int getDragprobid() {
		return dragprobid;
	}
	public void setDragprobid(int dragprobid) {
		this.dragprobid = dragprobid;
	}
	public String getIcdcode() {
		return icdcode;
	}
	public void setIcdcode(String icdcode) {
		this.icdcode = icdcode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
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
	
}
