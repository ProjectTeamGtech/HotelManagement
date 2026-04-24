package com.apm.Ipd.eu.entity;

public class Ipd {
	public String getAdvance() {
		return advance;
	}

	public void setAdvance(String advance) {
		this.advance = advance;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	private String ipdseqno;
	private int id;
	
	private String clientid;
	
	private String practitionerid;
	
	private String conditionid;
	
	private String admissiondate;
	
	private String treatmentepisodeid;
	
	private String mlccase;
	private String procedurename;
	private int totaladmission;
	private int totaldischarge;
	private String surgeon, anesthesia;
	
	private double totalpatient=0;
	private double totalbed=0;
	private String totalbedoccupancy;
	private String day;
	private String notes;
	private String date;
	private String ipdabrivationid;
	
	public String getIpdabrivationid() {
		return ipdabrivationid;
	}

	public void setIpdabrivationid(String ipdabrivationid) {
		this.ipdabrivationid = ipdabrivationid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTotalbedoccupancy() {
		return totalbedoccupancy;
	}

	public void setTotalbedoccupancy(String totalbedoccupancy) {
		this.totalbedoccupancy = totalbedoccupancy;
	}

	

	public double getTotalpatient() {
		return totalpatient;
	}

	public void setTotalpatient(double totalpatient) {
		this.totalpatient = totalpatient;
	}

	public double getTotalbed() {
		return totalbed;
	}

	public void setTotalbed(double totalbed) {
		this.totalbed = totalbed;
	}

	public void setTotalpatient(int totalpatient) {
		this.totalpatient = totalpatient;
	}

	

	public void setTotalbed(int totalbed) {
		this.totalbed = totalbed;
	}

	public String getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}

	public String getAnesthesia() {
		return anesthesia;
	}

	public void setAnesthesia(String anesthesia) {
		this.anesthesia = anesthesia;
	}

	public int getTotaladmission() {
		return totaladmission;
	}

	public void setTotaladmission(int totaladmission) {
		this.totaladmission = totaladmission;
	}

	public int getTotaldischarge() {
		return totaldischarge;
	}

	public void setTotaldischarge(int totaldischarge) {
		this.totaldischarge = totaldischarge;
	}

	public String getProcedurename() {
		return procedurename;
	}

	public void setProcedurename(String procedurename) {
		this.procedurename = procedurename;
	}
	private String dischargedate;
	
	public String getDischargedate() {
		return dischargedate;
	}

	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
	}

	public String getSubjectivecare() {
		  return subjectivecare;
		 }

		 public void setSubjectivecare(String subjectivecare) {
		  this.subjectivecare = subjectivecare;
		 }

		 public String getObjectivecare() {
		  return objectivecare;
		 }

		 public void setObjectivecare(String objectivecare) {
		  this.objectivecare = objectivecare;
		 }

		 public String getDiagnosiscare() {
		  return diagnosiscare;
		 }

		 public void setDiagnosiscare(String diagnosiscare) {
		  this.diagnosiscare = diagnosiscare;
		 }

		 public String getInferencecare() {
		  return inferencecare;
		 }

		 public void setInferencecare(String inferencecare) {
		  this.inferencecare = inferencecare;
		 }

		 public String getPlanningcare() {
		  return planningcare;
		 }

		 public void setPlanningcare(String planningcare) {
		  this.planningcare = planningcare;
		 }

		 public String getInterventioncare() {
		  return interventioncare;
		 }

		 public void setInterventioncare(String interventioncare) {
		  this.interventioncare = interventioncare;
		 }

		 public String getRationalecare() {
		  return rationalecare;
		 }

		 public void setRationalecare(String rationalecare) {
		  this.rationalecare = rationalecare;
		 }

		 public String getEvaluationcare() {
		  return evaluationcare;
		 }

		 public void setEvaluationcare(String evaluationcare) {
		  this.evaluationcare = evaluationcare;
		 }
		private String ipdid;
		 public String getIpdid() {
		 return ipdid;
		}

		public void setIpdid(String ipdid) {
		 this.ipdid = ipdid;
		}
		private String name;
		 public String getName() {
		 return name;
		}

		public void setName(String name) {
		 this.name = name;
		}
		private String diagnosisid;
		private String planningname;
		private String planningid;
		private String intervention_name;
		private String rationale;
		 public String getRationale() {
		 return rationale;
		}

		public void setRationale(String rationale) {
		 this.rationale = rationale;
		}

		 public String getPlanningid() {
		 return planningid;
		}

		public void setPlanningid(String planningid) {
		 this.planningid = planningid;
		}

		 public String getIntervention_name() {
		 return intervention_name;
		}

		public void setIntervention_name(String intervention_name) {
		 this.intervention_name = intervention_name;
		}

		 public String getDiagnosisid() {
		 return diagnosisid;
		}

		public void setDiagnosisid(String diagnosisid) {
		 this.diagnosisid = diagnosisid;
		}

		public String getPlanningname() {
		 return planningname;
		}

		public void setPlanningname(String planningname) {
		 this.planningname = planningname;
		}
		 private String subjectivecare;
		 private String objectivecare;
		 private String diagnosiscare;
		 private String inferencecare;
		 private String planningcare;
		 private String interventioncare;
		 private String rationalecare;
		 private String evaluationcare;
		 private String advance,balance;
		//

	public String getMlccase() {
		return mlccase;
	}

	public void setMlccase(String mlccase) {
		this.mlccase = mlccase;
	}

	public String getTreatmentepisodeid() {
		return treatmentepisodeid;
	}

	public void setTreatmentepisodeid(String treatmentepisodeid) {
		this.treatmentepisodeid = treatmentepisodeid;
	}

	public String getAdmissiondate() {
		return admissiondate;
	}

	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getPractitionerid() {
		return practitionerid;
	}

	public void setPractitionerid(String practitionerid) {
		this.practitionerid = practitionerid;
	}

	public String getConditionid() {
		return conditionid;
	}

	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}
	public String getNursing_date() {
		return nursing_date;
	}

	public void setNursing_date(String nursing_date) {
		this.nursing_date = nursing_date;
	}
	public String getIpdseqno() {
		return ipdseqno;
	}

	public void setIpdseqno(String ipdseqno) {
		this.ipdseqno = ipdseqno;
	}
	public String getOndiet() {
		return ondiet;
	}

	public void setOndiet(String ondiet) {
		this.ondiet = ondiet;
	}
	private String nursing_date;
	private String ondiet;
	

}
