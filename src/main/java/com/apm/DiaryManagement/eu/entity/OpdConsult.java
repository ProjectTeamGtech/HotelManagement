package com.apm.DiaryManagement.eu.entity;

public class OpdConsult {
 
public String getCondi_id() {
		return condi_id;
	}

	public void setCondi_id(String condi_id) {
		this.condi_id = condi_id;
	}

public String getUpdate_date_time() {
		return update_date_time;
	}

	public void setUpdate_date_time(String update_date_time) {
		this.update_date_time = update_date_time;
	}

public String getChief_complains() {
		return chief_complains;
	}

	public void setChief_complains(String chief_complains) {
		this.chief_complains = chief_complains;
	}

	public String getInvestigation() {
		return investigation;
	}

	public void setInvestigation(String investigation) {
		this.investigation = investigation;
	}

	public String getDiscadvnotes() {
		return discadvnotes;
	}

	public void setDiscadvnotes(String discadvnotes) {
		this.discadvnotes = discadvnotes;
	}

	public String getMedicine_text() {
		return medicine_text;
	}

	public void setMedicine_text(String medicine_text) {
		this.medicine_text = medicine_text;
	}

	public String getExamination_finding() {
		return examination_finding;
	}

	public void setExamination_finding(String examination_finding) {
		this.examination_finding = examination_finding;
	}

	public String getOndiet() {
		return ondiet;
	}

	public void setOndiet(String ondiet) {
		this.ondiet = ondiet;
	}

	public String getKarma() {
		return karma;
	}

	public void setKarma(String karma) {
		this.karma = karma;
	}

	public String getProcedurebams() {
		return procedurebams;
	}

	public void setProcedurebams(String procedurebams) {
		this.procedurebams = procedurebams;
	}

public String getOpdid() {
		return opdid;
	}

	public void setOpdid(String opdid) {
		this.opdid = opdid;
	}

public String getPatientcount() {
		return patientcount;
	}

	public void setPatientcount(String patientcount) {
		this.patientcount = patientcount;
	}

public String getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}

private String doctor;
 private int id;
 private String patientname;
 private int patientid;
 private int appointmentid;
 private String date;
 private String regdate;
 private String sttime;
 private String endtime;
 private String location;
 private String duration;
 private String slottime;
 private String history;
 private int practitionerid;
 private int condition_id;
 private int ipdid;
 private int diagnosis_id;
 private String payby;
 private String discount;
 private String debit;
 private String vat;
 private String chargetype;
 private String notes;
 private String balance,lastmodified;
 private int pclientid,phar_ipdid,phar_bedid,phar_wardid,bill_practid;
 private int priscid,tpid,oldparentid,newparentid,tempsessionid;
 private String cgst,bill_practname;
 private String ipdadmissiondte;
 private String atreatmentepid,room,apmtttype,diaryuserid;
 private String diagnosisname;
 private String department;
 private String newopdid;
 private String newipdid;
 private String oldipdid;
 private String fakeclientid;
 private String oldopd_id;
 private String new_admissiondate;
 private String flag_opd,opdid;
 private String chief_complains,investigation,discadvnotes,medicine_text,examination_finding,ondiet,karma,procedurebams,update_date_time;
 private String condi_id;
 public String getFlag_opd() {
	return flag_opd;
}

public void setFlag_opd(String flag_opd) {
	this.flag_opd = flag_opd;
}

public String getNew_admissiondate() {
	return new_admissiondate;
}

public void setNew_admissiondate(String new_admissiondate) {
	this.new_admissiondate = new_admissiondate;
}

public String getOldopd_id() {
	return oldopd_id;
}

public void setOldopd_id(String oldopd_id) {
	this.oldopd_id = oldopd_id;
}

public String getFakeclientid() {
	return fakeclientid;
}

public void setFakeclientid(String fakeclientid) {
	this.fakeclientid = fakeclientid;
}

public String getOldipdid() {
	return oldipdid;
}

public void setOldipdid(String oldipdid) {
	this.oldipdid = oldipdid;
}

public String getNewipdid() {
	return newipdid;
}

public void setNewipdid(String newipdid) {
	this.newipdid = newipdid;
}

public String getNewopdid() {
	return newopdid;
}

public void setNewopdid(String newopdid) {
	this.newopdid = newopdid;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public String getDiagnosisname() {
	return diagnosisname;
}

public void setDiagnosisname(String diagnosisname) {
	this.diagnosisname = diagnosisname;
}

public String getDiaryuserid() {
	return diaryuserid;
}

public void setDiaryuserid(String diaryuserid) {
	this.diaryuserid = diaryuserid;
}

private String added_by, apmttypetext, usedsession, otid, category, procedures, surgeon, anesthesia, wardid,otnotes, otimagecount, discharge_procedure;
 public String getOtnotes() {
	return otnotes;
}

public void setOtnotes(String otnotes) {
	this.otnotes = otnotes;
}

public String getOtimagecount() {
	return otimagecount;
}

public void setOtimagecount(String otimagecount) {
	this.otimagecount = otimagecount;
}

public String getDischarge_procedure() {
	return discharge_procedure;
}

public void setDischarge_procedure(String discharge_procedure) {
	this.discharge_procedure = discharge_procedure;
}

public String getAdded_by() {
	return added_by;
}

public void setAdded_by(String added_by) {
	this.added_by = added_by;
}

public String getApmttypetext() {
	return apmttypetext;
}

public void setApmttypetext(String apmttypetext) {
	this.apmttypetext = apmttypetext;
}

public String getUsedsession() {
	return usedsession;
}

public void setUsedsession(String usedsession) {
	this.usedsession = usedsession;
}

public String getOtid() {
	return otid;
}

public void setOtid(String otid) {
	this.otid = otid;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getProcedures() {
	return procedures;
}

public void setProcedures(String procedures) {
	this.procedures = procedures;
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

public String getWardid() {
	return wardid;
}

public void setWardid(String wardid) {
	this.wardid = wardid;
}

public String getApmtttype() {
	return apmtttype;
}

public void setApmtttype(String apmtttype) {
	this.apmtttype = apmtttype;
}

public String getRoom() {
	return room;
}

public void setRoom(String room) {
	this.room = room;
}

private String audischargedte,commencing,apmslotid,diaryusername;
 public String getDiaryusername() {
	return diaryusername;
}

public void setDiaryusername(String diaryusername) {
	this.diaryusername = diaryusername;
}

public String getApmslotid() {
	return apmslotid;
}

public void setApmslotid(String apmslotid) {
	this.apmslotid = apmslotid;
}

public String getCommencing() {
	return commencing;
}

public void setCommencing(String commencing) {
	this.commencing = commencing;
}

public String getAudischargedte() {
	return audischargedte;
}

public void setAudischargedte(String audischargedte) {
	this.audischargedte = audischargedte;
}

public String getAtreatmentepid() {
	return atreatmentepid;
}

public void setAtreatmentepid(String atreatmentepid) {
	this.atreatmentepid = atreatmentepid;
}

public String getIpdadmissiondte() {
	return ipdadmissiondte;
}

public void setIpdadmissiondte(String ipdadmissiondte) {
	this.ipdadmissiondte = ipdadmissiondte;
}

public String getChargetype() {
	return chargetype;
}

public void setChargetype(String chargetype) {
	this.chargetype = chargetype;
}

public String getNotes() {
	return notes;
}

public void setNotes(String notes) {
	this.notes = notes;
}

public String getBalance() {
	return balance;
}

public void setBalance(String balance) {
	this.balance = balance;
}

public int getPclientid() {
	return pclientid;
}

public void setPclientid(int pclientid) {
	this.pclientid = pclientid;
}

public int getPhar_ipdid() {
	return phar_ipdid;
}

public void setPhar_ipdid(int phar_ipdid) {
	this.phar_ipdid = phar_ipdid;
}

public int getPhar_bedid() {
	return phar_bedid;
}

public void setPhar_bedid(int phar_bedid) {
	this.phar_bedid = phar_bedid;
}

public int getPhar_wardid() {
	return phar_wardid;
}

public void setPhar_wardid(int phar_wardid) {
	this.phar_wardid = phar_wardid;
}

public int getBill_practid() {
	return bill_practid;
}

public void setBill_practid(int bill_practid) {
	this.bill_practid = bill_practid;
}

public int getPriscid() {
	return priscid;
}

public void setPriscid(int priscid) {
	this.priscid = priscid;
}

public int getTpid() {
	return tpid;
}

public void setTpid(int tpid) {
	this.tpid = tpid;
}

public int getOldparentid() {
	return oldparentid;
}

public void setOldparentid(int oldparentid) {
	this.oldparentid = oldparentid;
}

public int getNewparentid() {
	return newparentid;
}

public void setNewparentid(int newparentid) {
	this.newparentid = newparentid;
}

public int getTempsessionid() {
	return tempsessionid;
}

public void setTempsessionid(int tempsessionid) {
	this.tempsessionid = tempsessionid;
}

public String getCgst() {
	return cgst;
}

public void setCgst(String cgst) {
	this.cgst = cgst;
}

public String getBill_practname() {
	return bill_practname;
}

public void setBill_practname(String bill_practname) {
	this.bill_practname = bill_practname;
}

public String getSgst() {
	return sgst;
}

public void setSgst(String sgst) {
	this.sgst = sgst;
}

public String getGrosssubtotal() {
	return grosssubtotal;
}

public void setGrosssubtotal(String grosssubtotal) {
	this.grosssubtotal = grosssubtotal;
}

public String getInitial_paymode() {
	return initial_paymode;
}

public void setInitial_paymode(String initial_paymode) {
	this.initial_paymode = initial_paymode;
}

public String getFinal_paymode() {
	return final_paymode;
}

public void setFinal_paymode(String final_paymode) {
	this.final_paymode = final_paymode;
}

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public String getRefundamt() {
	return refundamt;
}

public void setRefundamt(String refundamt) {
	this.refundamt = refundamt;
}

public String getCredit() {
	return credit;
}

public void setCredit(String credit) {
	this.credit = credit;
}

public String getActual_total() {
	return actual_total;
}

public void setActual_total(String actual_total) {
	this.actual_total = actual_total;
}

public String getActual_discount() {
	return actual_discount;
}

public void setActual_discount(String actual_discount) {
	this.actual_discount = actual_discount;
}

public String getDisc_type() {
	return disc_type;
}

public void setDisc_type(String disc_type) {
	this.disc_type = disc_type;
}

public String getGrosstotal() {
	return grosstotal;
}

public void setGrosstotal(String grosstotal) {
	this.grosstotal = grosstotal;
}

private String sgst,grosssubtotal,initial_paymode,final_paymode;
 private String time,userid,refundamt,credit,actual_total,actual_discount,disc_type,grosstotal;
 private String patientcount;

public String getDebit() {
	return debit;
}

public void setDebit(String debit) {
	this.debit = debit;
}

public String getVat() {
	return vat;
}

public void setVat(String vat) {
	this.vat = vat;
}

public String getDiscount() {
	return discount;
}

public void setDiscount(String discount) {
	this.discount = discount;
}

public String getPayby() {
	return payby;
}

public void setPayby(String payby) {
	this.payby = payby;
}

public String getHistory() {
	return history;
}

public void setHistory(String history) {
	this.history = history;
}

public int getPractitionerid() {
	return practitionerid;
}

public void setPractitionerid(int practitionerid) {
	this.practitionerid = practitionerid;
}

public int getCondition_id() {
	return condition_id;
}

public void setCondition_id(int condition_id) {
	this.condition_id = condition_id;
}

public int getIpdid() {
	return ipdid;
}

public void setIpdid(int ipdid) {
	this.ipdid = ipdid;
}

public int getDiagnosis_id() {
	return diagnosis_id;
}

public void setDiagnosis_id(int diagnosis_id) {
	this.diagnosis_id = diagnosis_id;
}

public String getSlottime() {
	return slottime;
}

public void setSlottime(String slottime) {
	this.slottime = slottime;
}

public String getSttime() {
	return sttime;
}

public void setSttime(String sttime) {
	this.sttime = sttime;
}

public String getEndtime() {
	return endtime;
}

public void setEndtime(String endtime) {
	this.endtime = endtime;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getDuration() {
	return duration;
}

public void setDuration(String duration) {
	this.duration = duration;
}

public String getRegdate() {
	return regdate;
}

public void setRegdate(String regdate) {
	this.regdate = regdate;
}

public int getAppointmentid() {
	return appointmentid;
}

public void setAppointmentid(int appointmentid) {
	this.appointmentid = appointmentid;
}

public int getPatientid() {
	return patientid;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public void setPatientid(int patientid) {
	this.patientid = patientid;
}

public String getPatientname() {
	return patientname;
}

public void setPatientname(String patientname) {
	this.patientname = patientname;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getDoctor() {
	return doctor;
}

public void setDoctor(String doctor) {
	this.doctor = doctor;
}


}
