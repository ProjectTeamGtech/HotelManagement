package com.apm.TreatmentEpisode.eu.entity;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;

public class TreatmentEpisode {
	private String abrivationid;
	private int id;
	
	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}
private String approvedLimit;
	public String getApprovedLimit() {
	return approvedLimit;
}

public void setApprovedLimit(String approvedLimit) {
	this.approvedLimit = approvedLimit;
}
	private int clientId;
	
	private String clientName;
	
	private String diaryUser;
	
	private String treatmentStartDate;
	
	private String treatmentEpisodeName;
	private String empname;
	private String referalDate;
	
	private String referralName;
	
	private String referralSource;
	
	private String referralContact;
	
	private String referralLetter;
	
	private String payby;
	
	private String spendLimit;
	
	private String consultationLimit;
	
	private String dischargeLetter;
	
	private String authorisationCode;
	
	private String referalendDate;
	
	public String getReferalendDate() {
		return referalendDate;
	}

	public void setReferalendDate(String referalendDate) {
		this.referalendDate = referalendDate;
	}

	public String getIpdopd() {
		return ipdopd;
	}

	public void setIpdopd(String ipdopd) {
		this.ipdopd = ipdopd;
	}

	private String invoicee;
	private String condition;
	
	public String treatmentType;
	public String appointmnetType;
	
	private boolean reportsent;
	
	private boolean urgent;
	
	private String ipdopd;
	
	
	
	
	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public int chkConsultationNote;
	public int apmtCount;
	
	private int trtmentStatus;
	
	private String outcomes;
	
	private String dschargestatus;
	
	
	
	
	
	
	
	public String getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(String outcomes) {
		this.outcomes = outcomes;
	}

	public String getDschargestatus() {
		return dschargestatus;
	}

	public void setDschargestatus(String dschargestatus) {
		this.dschargestatus = dschargestatus;
	}

	public int getTrtmentStatus() {
		return trtmentStatus;
	}

	public void setTrtmentStatus(int trtmentStatus) {
		this.trtmentStatus = trtmentStatus;
	}

	public boolean isReportsent() {
		return reportsent;
	}

	public void setReportsent(boolean reportsent) {
		this.reportsent = reportsent;
	}

	public int getApmtCount() {
		return apmtCount;
	}

	public void setApmtCount(int apmtCount) {
		this.apmtCount = apmtCount;
	}

	public int getTreatmentApmtCount() {
		return treatmentApmtCount;
	}

	public void setTreatmentApmtCount(int treatmentApmtCount) {
		this.treatmentApmtCount = treatmentApmtCount;
	}

	public int treatmentApmtCount;

	public int getChkConsultationNote() {
		return chkConsultationNote;
	}

	public void setChkConsultationNote(int chkConsultationNote) {
		this.chkConsultationNote = chkConsultationNote;
	}

	public String getAppointmnetType() {
		return appointmnetType;
	}

	public void setAppointmnetType(String appointmnetType) {
		this.appointmnetType = appointmnetType;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String appointmentDate;
	
	private ArrayList<NotAvailableSlot> appointmnetList;

	public ArrayList<NotAvailableSlot> getAppointmnetList() {
		return appointmnetList;
	}

	public void setAppointmnetList(ArrayList<NotAvailableSlot> appointmnetList) {
		this.appointmnetList = appointmnetList;
	}

	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	private String sessions;
	public String getSessions() {
		return sessions;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public String getUsedSession() {
		return usedSession;
	}

	public void setUsedSession(String usedSession) {
		this.usedSession = usedSession;
	}

	private String usedSession;
	
	public String getInvoicee() {
		return invoicee;
	}

	public void setInvoicee(String invoicee) {
		this.invoicee = invoicee;
	}

	public String getAuthorisationCode() {
		return authorisationCode;
	}

	public void setAuthorisationCode(String authorisationCode) {
		this.authorisationCode = authorisationCode;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getTreatmentStartDate() {
		return treatmentStartDate;
	}

	public void setTreatmentStartDate(String treatmentStartDate) {
		this.treatmentStartDate = treatmentStartDate;
	}

	public String getTreatmentEpisodeName() {
		return treatmentEpisodeName;
	}

	public void setTreatmentEpisodeName(String treatmentEpisodeName) {
		this.treatmentEpisodeName = treatmentEpisodeName;
	}

	public String getReferalDate() {
		return referalDate;
	}

	public void setReferalDate(String referalDate) {
		this.referalDate = referalDate;
	}

	public String getReferralName() {
		return referralName;
	}

	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}

	public String getReferralSource() {
		return referralSource;
	}

	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	public String getReferralContact() {
		return referralContact;
	}

	public void setReferralContact(String referralContact) {
		this.referralContact = referralContact;
	}

	public String getReferralLetter() {
		return referralLetter;
	}

	public void setReferralLetter(String referralLetter) {
		this.referralLetter = referralLetter;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public String getSpendLimit() {
		return spendLimit;
	}

	public void setSpendLimit(String spendLimit) {
		this.spendLimit = spendLimit;
	}

	public String getConsultationLimit() {
		return consultationLimit;
	}

	public void setConsultationLimit(String consultationLimit) {
		this.consultationLimit = consultationLimit;
	}

	public String getDischargeLetter() {
		return dischargeLetter;
	}

	public void setDischargeLetter(String dischargeLetter) {
		this.dischargeLetter = dischargeLetter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}
	

}
