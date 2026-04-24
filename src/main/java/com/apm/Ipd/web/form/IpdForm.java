package com.apm.Ipd.web.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Bloodbank.eu.entity.Bloodbank;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Emr.eu.entity.InvstTemplate;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.entity.Bams;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.web.utils.PopulateList;

public class IpdForm {
	public String getNotes_text() {
		return notes_text;
	}

	public void setNotes_text(String notes_text) {
		this.notes_text = notes_text;
	}

	public String getChargetype() {
		return chargetype;
	}

	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}

	public String getTreatcharge() {
		return treatcharge;
	}

	public void setTreatcharge(String treatcharge) {
		this.treatcharge = treatcharge;
	}

	public Collection<Master> getCharges() {
		return Charges;
	}

	public void setCharges(Collection<Master> charges) {
		Charges = charges;
	}

	public ArrayList<Master> getFinalTreatment() {
		return finalTreatment;
	}

	public void setFinalTreatment(ArrayList<Master> finalTreatment) {
		this.finalTreatment = finalTreatment;
	}

	public ArrayList<Master> getFinalobservation() {
		return finalobservation;
	}

	public void setFinalobservation(ArrayList<Master> finalobservation) {
		this.finalobservation = finalobservation;
	}

	public Collection<Master> getObservation() {
		return observation;
	}

	public void setObservation(Collection<Master> observation) {
		this.observation = observation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Collection<Master> getNotes() {
		return notes;
	}

	public void setNotes(Collection<Master> notes) {
		this.notes = notes;
	}

	public ArrayList<Master> getFinalnotes() {
		return finalnotes;
	}

	public void setFinalnotes(ArrayList<Master> finalnotes) {
		this.finalnotes = finalnotes;
	}

	public ArrayList<Master> getFinalinvestigation() {
		return finalinvestigation;
	}

	public void setFinalinvestigation(ArrayList<Master> finalinvestigation) {
		this.finalinvestigation = finalinvestigation;
	}

	public Collection<Master> getInvestigations() {
		return investigations;
	}

	public void setInvestigations(Collection<Master> investigations) {
		this.investigations = investigations;
	}

	public ArrayList<Master> getFinalhistory() {
		return finalhistory;
	}

	public void setFinalhistory(ArrayList<Master> finalhistory) {
		this.finalhistory = finalhistory;
	}

	public Collection<Master> getHistories() {
		return histories;
	}

	public void setHistories(Collection<Master> histories) {
		this.histories = histories;
	}

	public ArrayList<Master> getFinalChifcomplain() {
		return finalChifcomplain;
	}

	public void setFinalChifcomplain(ArrayList<Master> finalChifcomplain) {
		this.finalChifcomplain = finalChifcomplain;
	}

	public Collection<Master> getChiefcomplain() {
		return chiefcomplain;
	}

	public void setChiefcomplain(Collection<Master> chiefcomplain) {
		this.chiefcomplain = chiefcomplain;
	}

	public ArrayList<Diagnosis> getDiagnosislist() {
		return diagnosislist;
	}

	public void setDiagnosislist(ArrayList<Diagnosis> diagnosislist) {
		this.diagnosislist = diagnosislist;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getExamplehist() {
		return examplehist;
	}

	public void setExamplehist(String examplehist) {
		this.examplehist = examplehist;
	}

	public ArrayList<Priscription> getPriscriptionListprachi() {
		return priscriptionListprachi;
	}

	public void setPriscriptionListprachi(ArrayList<Priscription> priscriptionListprachi) {
		this.priscriptionListprachi = priscriptionListprachi;
	}

	public String getEyereading() {
		return eyereading;
	}

	public void setEyereading(String eyereading) {
		this.eyereading = eyereading;
	}

	public String getConsNote() {
		return consNote;
	}

	public void setConsNote(String consNote) {
		this.consNote = consNote;
	}

	public ArrayList<Bed> getConsulthistorylist() {
		return consulthistorylist;
	}

	public void setConsulthistorylist(ArrayList<Bed> consulthistorylist) {
		this.consulthistorylist = consulthistorylist;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public ArrayList<Master> getOtdepartmentList() {
		return otdepartmentList;
	}

	public void setOtdepartmentList(ArrayList<Master> otdepartmentList) {
		this.otdepartmentList = otdepartmentList;
	}

	public ArrayList<Accounts> getDailynoteslist() {
		return dailynoteslist;
	}

	public void setDailynoteslist(ArrayList<Accounts> dailynoteslist) {
		this.dailynoteslist = dailynoteslist;
	}

	public String getProceduretempname() {
		return proceduretempname;
	}

	public void setProceduretempname(String proceduretempname) {
		this.proceduretempname = proceduretempname;
	}

	public Bams getKarmaprocedure() {
		return karmaprocedure;
	}

	public void setKarmaprocedure(Bams karmaprocedure) {
		this.karmaprocedure = karmaprocedure;
	}

	public String getProcedurebams() {
		return procedurebams;
	}

	public void setProcedurebams(String procedurebams) {
		this.procedurebams = procedurebams;
	}

	public ArrayList<Master> getProcedure_list() {
		return procedure_list;
	}

	public void setProcedure_list(ArrayList<Master> procedure_list) {
		this.procedure_list = procedure_list;
	}

	public String getKarma() {
		return karma;
	}

	public void setKarma(String karma) {
		this.karma = karma;
	}

	public String getKarmatempname() {
		return karmatempname;
	}

	public void setKarmatempname(String karmatempname) {
		this.karmatempname = karmatempname;
	}

	public ArrayList<Master> getKarma_list() {
		return karma_list;
	}

	public void setKarma_list(ArrayList<Master> karma_list) {
		this.karma_list = karma_list;
	}

	public ArrayList<Ipd> getPatienttransactionlist() {
		return patienttransactionlist;
	}

	public void setPatienttransactionlist(ArrayList<Ipd> patienttransactionlist) {
		this.patienttransactionlist = patienttransactionlist;
	}

	public ArrayList<Bed> getIpdgendercountlist() {
		return ipdgendercountlist;
	}

	public void setIpdgendercountlist(ArrayList<Bed> ipdgendercountlist) {
		this.ipdgendercountlist = ipdgendercountlist;
	}

	public String getRespiratory_rate() {
		return respiratory_rate;
	}

	public void setRespiratory_rate(String respiratory_rate) {
		this.respiratory_rate = respiratory_rate;
	}

	public String getOwner_qualification1() {
		return owner_qualification1;
	}

	public void setOwner_qualification1(String owner_qualification1) {
		this.owner_qualification1 = owner_qualification1;
	}

	public String getClinicOwner1() {
		return clinicOwner1;
	}

	public void setClinicOwner1(String clinicOwner1) {
		this.clinicOwner1 = clinicOwner1;
	}
	private String logipdid;
	public String refresh;
	public String otNotesids;
	private String examplehist;
	private String filename;
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String patient_department;
	public String diagnose;
	private String origbedid;
	ArrayList<TreatmentType> smstemplate;
	private String hrf_gdm,hrf_pih,hrf_boh,hrf_hypothyroid,hrf_hyperthyroid,hrf_prev_lscs,hrf_rh_negative,hrf_other,fundalheight_weeks,pa_soft,
	external_ballotment,effaced_text,pv_dialated,pv_dialatedcm;
	private String g,dating_usg_date,weeks_dating_scan,pog_dating_scan;
	private String usg_report,additonal_notes,diagonosis,treatment_advice,investigation_advice,pog;
	private String cephalicVal,baley_sizeVal,ps_cervixVal,ps_vagineVal,ps_vaultVal,pv_osVal,pv_MVal,commencing,days_dating_scan,hrf_otherval;
	
	private String patientaddress;
	private int lastappointmentid;
	private ArrayList<Priscription> gynicPriscList;
	private ArrayList<Master> birthPlaceList;
	private String freez_status;
	private int saveandprintadmission;
	private String bkapmtipd;
	private String enddischargename;
	private ArrayList<Bed> ipdReport;
	private String spo;
	private String sugarFasting;
	private String apmtId;
	private String consultationhistory;
	private ArrayList<Bed>consult_his;
	private String filter_sortby;
	private String userid;
	private String Disciplineid;
    private String allergynotes ,allergy;
    private String FromDate;
    private String ondiettempname;
	private String ondiet;
	private String physio;
	private Ipd Diet;
	private String physiotempname;
	private String karmatempname;
	private String proceduretempname;
	private String karma,procedurebams;
	private ArrayList<Master> on_diet_list;
	private ArrayList<Master>physio_list;
	private String opd_abbrivation_id;
	private ArrayList<Master> karma_list;
	private ArrayList<Master> procedure_list;
	private Bams karmaprocedure;
	private String ageondischarge;
	ArrayList<Bed>invtypelist;
	private String dis_investigation;
	private ArrayList<Accounts>dailynoteslist;
	public  String mbbsseqno;
	ArrayList<Master>otdepartmentList;
	private String middlename;
	private String LSplRFI;
	private String LCylRFI;
	private String LAxisRFI;
	private String RSplRFI;
	private String RCylRFI;
	private String RAxisRFI;
	private ArrayList<Bed> consulthistorylist;
    private String consNote;
    private String eyereading;
    private ArrayList<Priscription> priscriptionListprachi;
    private String notes_text;
	public String getLCylRFI() {
		return LCylRFI;
	}

	public void setLCylRFI(String lCylRFI) {
		LCylRFI = lCylRFI;
	}

	public String getLAxisRFI() {
		return LAxisRFI;
	}

	public void setLAxisRFI(String lAxisRFI) {
		LAxisRFI = lAxisRFI;
	}

	public String getRSplRFI() {
		return RSplRFI;
	}

	public void setRSplRFI(String rSplRFI) {
		RSplRFI = rSplRFI;
	}

	public String getRCylRFI() {
		return RCylRFI;
	}

	public void setRCylRFI(String rCylRFI) {
		RCylRFI = rCylRFI;
	}

	public String getRAxisRFI() {
		return RAxisRFI;
	}

	public void setRAxisRFI(String rAxisRFI) {
		RAxisRFI = rAxisRFI;
	}

	public String getLSplRFI() {
		return LSplRFI;
	}

	public void setLSplRFI(String lSplRFI) {
		LSplRFI = lSplRFI;
	}

	public String getMbbsseqno() {
		return mbbsseqno;
	}

	public void setMbbsseqno(String mbbsseqno) {
		this.mbbsseqno = mbbsseqno;
	}

	public String getDis_investigation() {
		return dis_investigation;
	}

	public void setDis_investigation(String dis_investigation) {
		this.dis_investigation = dis_investigation;
	}
	 public ArrayList<Bed> getInvtypelist() {
		return invtypelist;
	}

	public void setInvtypelist(ArrayList<Bed> invtypelist) {
		this.invtypelist = invtypelist;
	}

	public String getAgeondischarge() {
		return ageondischarge;
	}

	public void setAgeondischarge(String ageondischarge) {
		this.ageondischarge = ageondischarge;
	}

	public String getOpd_abbrivation_id() {
		return opd_abbrivation_id;
	}

	public void setOpd_abbrivation_id(String opd_abbrivation_id) {
		this.opd_abbrivation_id = opd_abbrivation_id;
	}

	public String getPhysiotempname() {
		return physiotempname;
	}

	public void setPhysiotempname(String physiotempname) {
		this.physiotempname = physiotempname;
	}

	public ArrayList<Master> getOn_diet_list() {
		return on_diet_list;
	}

	public void setOn_diet_list(ArrayList<Master> on_diet_list) {
		this.on_diet_list = on_diet_list;
	}

	public ArrayList<Master> getPhysio_list() {
		return physio_list;
	}

	public void setPhysio_list(ArrayList<Master> physio_list) {
		this.physio_list = physio_list;
	}

	public String getPhysio() {
		return physio;
	}

	public void setPhysio(String physio) {
		this.physio = physio;
	}

	public String getOndiettempname() {
			return ondiettempname;
		}

		public void setOndiettempname(String ondiettempname) {
			this.ondiettempname = ondiettempname;
		}

		public String getOndiet() {
			return ondiet;
		}

		public void setOndiet(String ondiet) {
			this.ondiet = ondiet;
		}
	
	public String getAllergy() {
		return allergy;
	}

	public String getFromDate() {
		return FromDate;
	}

	public void setFromDate(String fromDate) {
		FromDate = fromDate;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getAllergynotes() {
		return allergynotes;
	}

	public void setAllergynotes(String allergynotes) {
		this.allergynotes = allergynotes;
	}

	public String getDisciplineid() {
		return Disciplineid;
	}

	public void setDisciplineid(String disciplineid) {
		Disciplineid = disciplineid;
	}

	public String getFilter_sortby() {
		return filter_sortby;
	}

	public void setFilter_sortby(String filter_sortby) {
		this.filter_sortby = filter_sortby;
	}
	private int isConsultationForm;
	

	public int getIsConsultationForm() {
		return isConsultationForm;
	}

	public void setIsConsultationForm(int isConsultationForm) {
		this.isConsultationForm = isConsultationForm;
	}

	public ArrayList<Bed> getConsult_his() {
		return consult_his;
	}

	public void setConsult_his(ArrayList<Bed> consult_his) {
		this.consult_his = consult_his;
	}

	public String getConsultationhistory() {
		return consultationhistory;
	}

	public void setConsultationhistory(String consultationhistory) {
		this.consultationhistory = consultationhistory;
	}

	public String getApmtId() {
		return apmtId;
	}

	public void setApmtId(String apmtId) {
		this.apmtId = apmtId;
	}

	public String getSugarFasting() {
		return sugarFasting;
	}

	public void setSugarFasting(String sugarFasting) {
		this.sugarFasting = sugarFasting;
	}

	public String getSpo() {
		return spo;
	}

	public void setSpo(String spo) {
		this.spo = spo;
	}

	public ArrayList<Bed> getIpdReport() {
		return ipdReport;
	}

	public void setIpdReport(ArrayList<Bed> ipdReport) {
		this.ipdReport = ipdReport;
	}

	public String getEnddischargename() {
		return enddischargename;
	}

	public void setEnddischargename(String enddischargename) {
		this.enddischargename = enddischargename;
	}

	public String getBkapmtipd() {
		return bkapmtipd;
	}

	public void setBkapmtipd(String bkapmtipd) {
		this.bkapmtipd = bkapmtipd;
	}

	public int getSaveandprintadmission() {
		return saveandprintadmission;
	}

	public void setSaveandprintadmission(int saveandprintadmission) {
		this.saveandprintadmission = saveandprintadmission;
	}

	public String getFreez_status() {
		return freez_status;
	}

	public void setFreez_status(String freez_status) {
		this.freez_status = freez_status;
	}

	public ArrayList<Master> getBirthPlaceList() {
		return birthPlaceList;
	}

	public void setBirthPlaceList(ArrayList<Master> birthPlaceList) {
		this.birthPlaceList = birthPlaceList;
	}

	public ArrayList<Priscription> getGynicPriscList() {
		return gynicPriscList;
	}

	public void setGynicPriscList(ArrayList<Priscription> gynicPriscList) {
		this.gynicPriscList = gynicPriscList;
	}

	public int getLastappointmentid() {
		return lastappointmentid;
	}

	public void setLastappointmentid(int lastappointmentid) {
		this.lastappointmentid = lastappointmentid;
	}

	public String getPatientaddress() {
		return patientaddress;
	}

	public void setPatientaddress(String patientaddress) {
		this.patientaddress = patientaddress;
	}

	public String getDays_dating_scan() {
		return days_dating_scan;
	}

	public void setDays_dating_scan(String days_dating_scan) {
		this.days_dating_scan = days_dating_scan;
	}

	public String getHrf_otherval() {
		return hrf_otherval;
	}

	public void setHrf_otherval(String hrf_otherval) {
		this.hrf_otherval = hrf_otherval;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getPv_osVal() {
		return pv_osVal;
	}

	public void setPv_osVal(String pv_osVal) {
		this.pv_osVal = pv_osVal;
	}

	public String getBaley_sizeVal() {
		return baley_sizeVal;
	}

	public void setBaley_sizeVal(String baley_sizeVal) {
		this.baley_sizeVal = baley_sizeVal;
	}

	public String getCephalicVal() {
		return cephalicVal;
	}

	public void setCephalicVal(String cephalicVal) {
		this.cephalicVal = cephalicVal;
	}

	public String getPog() {
		return pog;
	}

	public void setPog(String pog) {
		this.pog = pog;
	}

	public String getHrf_gdm() {
		return hrf_gdm;
	}

	public void setHrf_gdm(String hrf_gdm) {
		this.hrf_gdm = hrf_gdm;
	}

	public String getHrf_pih() {
		return hrf_pih;
	}

	public void setHrf_pih(String hrf_pih) {
		this.hrf_pih = hrf_pih;
	}

	public String getHrf_boh() {
		return hrf_boh;
	}

	public void setHrf_boh(String hrf_boh) {
		this.hrf_boh = hrf_boh;
	}

	public String getHrf_hypothyroid() {
		return hrf_hypothyroid;
	}

	public void setHrf_hypothyroid(String hrf_hypothyroid) {
		this.hrf_hypothyroid = hrf_hypothyroid;
	}

	public String getHrf_hyperthyroid() {
		return hrf_hyperthyroid;
	}

	public void setHrf_hyperthyroid(String hrf_hyperthyroid) {
		this.hrf_hyperthyroid = hrf_hyperthyroid;
	}

	public String getHrf_prev_lscs() {
		return hrf_prev_lscs;
	}

	public void setHrf_prev_lscs(String hrf_prev_lscs) {
		this.hrf_prev_lscs = hrf_prev_lscs;
	}

	public String getHrf_rh_negative() {
		return hrf_rh_negative;
	}

	public void setHrf_rh_negative(String hrf_rh_negative) {
		this.hrf_rh_negative = hrf_rh_negative;
	}

	public String getHrf_other() {
		return hrf_other;
	}

	public void setHrf_other(String hrf_other) {
		this.hrf_other = hrf_other;
	}

	public String getFundalheight_weeks() {
		return fundalheight_weeks;
	}

	public void setFundalheight_weeks(String fundalheight_weeks) {
		this.fundalheight_weeks = fundalheight_weeks;
	}

	public String getPa_soft() {
		return pa_soft;
	}

	public void setPa_soft(String pa_soft) {
		this.pa_soft = pa_soft;
	}

	public String getExternal_ballotment() {
		return external_ballotment;
	}

	public void setExternal_ballotment(String external_ballotment) {
		this.external_ballotment = external_ballotment;
	}

	public String getEffaced_text() {
		return effaced_text;
	}

	public void setEffaced_text(String effaced_text) {
		this.effaced_text = effaced_text;
	}

	public String getPv_dialated() {
		return pv_dialated;
	}

	public void setPv_dialated(String pv_dialated) {
		this.pv_dialated = pv_dialated;
	}

	public String getPv_dialatedcm() {
		return pv_dialatedcm;
	}

	public void setPv_dialatedcm(String pv_dialatedcm) {
		this.pv_dialatedcm = pv_dialatedcm;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getDating_usg_date() {
		return dating_usg_date;
	}

	public void setDating_usg_date(String dating_usg_date) {
		this.dating_usg_date = dating_usg_date;
	}

	public String getWeeks_dating_scan() {
		return weeks_dating_scan;
	}

	public void setWeeks_dating_scan(String weeks_dating_scan) {
		this.weeks_dating_scan = weeks_dating_scan;
	}

	public String getPog_dating_scan() {
		return pog_dating_scan;
	}

	public void setPog_dating_scan(String pog_dating_scan) {
		this.pog_dating_scan = pog_dating_scan;
	}

	public String getUsg_report() {
		return usg_report;
	}

	public void setUsg_report(String usg_report) {
		this.usg_report = usg_report;
	}

	public String getAdditonal_notes() {
		return additonal_notes;
	}

	public void setAdditonal_notes(String additonal_notes) {
		this.additonal_notes = additonal_notes;
	}

	public String getDiagonosis() {
		return diagonosis;
	}

	public void setDiagonosis(String diagonosis) {
		this.diagonosis = diagonosis;
	}

	public String getTreatment_advice() {
		return treatment_advice;
	}

	public void setTreatment_advice(String treatment_advice) {
		this.treatment_advice = treatment_advice;
	}

	public String getInvestigation_advice() {
		return investigation_advice;
	}

	public void setInvestigation_advice(String investigation_advice) {
		this.investigation_advice = investigation_advice;
	}
	private String visitconslt;
	
	public String getVisitconslt() {
		return visitconslt;
	}

	public void setVisitconslt(String visitconslt) {
		this.visitconslt = visitconslt;
	}

	public ArrayList<TreatmentType> getSmstemplate() {
		return smstemplate;
	}

	public void setSmstemplate(ArrayList<TreatmentType> smstemplate) {
		this.smstemplate = smstemplate;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getPatient_department() {
		return patient_department;
	}

	public void setPatient_department(String patient_department) {
		this.patient_department = patient_department;
	}

	public String getOtNotesids() {
		return otNotesids;
	}
private String age;
	public void setOtNotesids(String otNotesids) {
		this.otNotesids = otNotesids;
	}
	ArrayList<Bed> ipddiagnosis;
	public ArrayList<Bed> getIpddiagnosis() {
		return ipddiagnosis;
	}

	public void setIpddiagnosis(ArrayList<Bed> ipddiagnosis) {
		this.ipddiagnosis = ipddiagnosis;
	}
	ArrayList<Master>nimaidoselist;
	ArrayList<Master>nimaiqtylist;
	ArrayList<Master>nimairemarklist;
	private ArrayList<Master> requestlocationlist;
	private String requestlocationid;
	
	
	public String getRequestlocationid() {
		return requestlocationid;
	}

	public void setRequestlocationid(String requestlocationid) {
		this.requestlocationid = requestlocationid;
	}

	public ArrayList<Master> getRequestlocationlist() {
		return requestlocationlist;
	}

	public void setRequestlocationlist(ArrayList<Master> requestlocationlist) {
		this.requestlocationlist = requestlocationlist;
	}

	public ArrayList<Master> getNimaidoselist() {
		return nimaidoselist;
	}

	public void setNimaidoselist(ArrayList<Master> nimaidoselist) {
		this.nimaidoselist = nimaidoselist;
	}

	public ArrayList<Master> getNimaiqtylist() {
		return nimaiqtylist;
	}

	public void setNimaiqtylist(ArrayList<Master> nimaiqtylist) {
		this.nimaiqtylist = nimaiqtylist;
	}

	public ArrayList<Master> getNimairemarklist() {
		return nimairemarklist;
	}

	public void setNimairemarklist(ArrayList<Master> nimairemarklist) {
		this.nimairemarklist = nimairemarklist;
	}
	private String  ageonadmn;
	public String Filter_status1;
	private String invseenstatus;
	private String wardname;
	private String curdatetime;
	ArrayList<String> timeList;
	private String casualtyipd;
	
	private String pagerecords;
	private String disbedid="0";
	private int totalRecords;
	private String ipdseqno;
	ArrayList<Master>pkgsList;
	private String invpkg;
	private String chiefcomplatetempname;
	private String presentillnesstempname;
	private String pasthistorytempname;
	private String familyhistorytempname;
	private String surgicalnotes;
	private String personalhistorytempname;
	private String onexaminationtempname;
	private String treatmentgiventempname;
	private String nursingadvicetempname;
	private String findingondistempname;
	private String investigationtempname;
	private String hospitalcoursetempname;
	
	private ArrayList<Diagnosis> diagnosisListGynic;
	private String disdefaulttempname;
	private String operativetempname;
	private boolean verification;
	// adarsh
	 private String subjectivecare;
	 private String objectivecare;
	 private String diagnosiscare;
	 private String inferencecare;
	 private String planningcare;
	 private String interventioncare;
	 private String rationalecare;
	 private String evaluationcare;
	 ArrayList<Ipd>nursingdiagnosislist;
	 ArrayList<Ipd>rationalelist;
	 private String deathnote;
	 private String dischargeStatusId;
	 private ArrayList<Master> otnoteslist;
	 
	 private ArrayList<Master> otdatesandids;
	 private String tds;
	 
	 private String activefilter;
	 
	 private ArrayList<UserProfile> allconsultantlistwithdepart;
	 
	 private String treatmenthistory;
	 
	 private String totalotids="0";
	 private String totalchildmedids="0";
	 private ArrayList<Priscription> medicinetimelist;
	 private ArrayList<Priscription> treatmentivendischargePriscListt;
	 private String invstids="0";
	 private String rmonotesids;
	 private String mlcabrivationid;
	 private ArrayList<Priscription> admissionPriscList;
	 private ArrayList<Master> emergencydetailslist;
	 private String emergencydetail;
	 
	 private String giddinesscondition;
	 private String giddinessnotes;
	 private String unconcondition;
	 private String unconnotes;
	 private  ArrayList<Product> allMedicieneList ;
	 private String fullname;
	 private String mobile;
	 private String practitionerName;
	 private String ispharmacy;
	 private String androidpractid;
	 private String isfromandroid;
	 private String androidpractuserid;
	 private String excessamtbt;
	 private String sucessmsg;
	 private String patient_type;
	 
	 
	 public String getPatient_type() {
		return patient_type;
	}

	public void setPatient_type(String patient_type) {
		this.patient_type = patient_type;
	}

	public String getSucessmsg() {
		return sucessmsg;
	}

	public void setSucessmsg(String sucessmsg) {
		this.sucessmsg = sucessmsg;
	}

	public String getExcessamtbt() {
		return excessamtbt;
	}

	public void setExcessamtbt(String excessamtbt) {
		this.excessamtbt = excessamtbt;
	}

	public String getAndroidpractuserid() {
		return androidpractuserid;
	}

	public void setAndroidpractuserid(String androidpractuserid) {
		this.androidpractuserid = androidpractuserid;
	}

	public String getIsfromandroid() {
		return isfromandroid;
	}

	public void setIsfromandroid(String isfromandroid) {
		this.isfromandroid = isfromandroid;
	}

	public String getAndroidpractid() {
		return androidpractid;
	}

	public void setAndroidpractid(String androidpractid) {
		this.androidpractid = androidpractid;
	}

	public String getIspharmacy() {
		return ispharmacy;
	}

	public void setIspharmacy(String ispharmacy) {
		this.ispharmacy = ispharmacy;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public ArrayList<Product> getAllMedicieneList() {
		return allMedicieneList;
	}

	public void setAllMedicieneList(ArrayList<Product> allMedicieneList) {
		this.allMedicieneList = allMedicieneList;
	}

	public String getGiddinesscondition() {
		return giddinesscondition;
	}

	public void setGiddinesscondition(String giddinesscondition) {
		this.giddinesscondition = giddinesscondition;
	}

	public String getGiddinessnotes() {
		return giddinessnotes;
	}

	public void setGiddinessnotes(String giddinessnotes) {
		this.giddinessnotes = giddinessnotes;
	}

	public String getUnconcondition() {
		return unconcondition;
	}

	public void setUnconcondition(String unconcondition) {
		this.unconcondition = unconcondition;
	}

	public String getUnconnotes() {
		return unconnotes;
	}

	public void setUnconnotes(String unconnotes) {
		this.unconnotes = unconnotes;
	}

	public String getEmergencydetail() {
		return emergencydetail;
	}

	public void setEmergencydetail(String emergencydetail) {
		this.emergencydetail = emergencydetail;
	}

	public ArrayList<Master> getEmergencydetailslist() {
		return emergencydetailslist;
	}

	public void setEmergencydetailslist(ArrayList<Master> emergencydetailslist) {
		this.emergencydetailslist = emergencydetailslist;
	}

	public ArrayList<Priscription> getAdmissionPriscList() {
		return admissionPriscList;
	}

	public void setAdmissionPriscList(ArrayList<Priscription> admissionPriscList) {
		this.admissionPriscList = admissionPriscList;
	}

	public String getMlcabrivationid() {
		return mlcabrivationid;
	}

	public void setMlcabrivationid(String mlcabrivationid) {
		this.mlcabrivationid = mlcabrivationid;
	}

	public String getRmonotesids() {
		return rmonotesids;
	}

	public void setRmonotesids(String rmonotesids) {
		this.rmonotesids = rmonotesids;
	}

	public String getInvstids() {
		return invstids;
	}

	public void setInvstids(String invstids) {
		this.invstids = invstids;
	}

	public ArrayList<Priscription> getMedicinetimelist() {
		return medicinetimelist;
	}

	public void setMedicinetimelist(ArrayList<Priscription> medicinetimelist) {
		this.medicinetimelist = medicinetimelist;
	}

	public String getTotalchildmedids() {
		return totalchildmedids;
	}

	public void setTotalchildmedids(String totalchildmedids) {
		this.totalchildmedids = totalchildmedids;
	}

	public String getTotalotids() {
		return totalotids;
	}

	public void setTotalotids(String totalotids) {
		this.totalotids = totalotids;
	}

	public String getTreatmenthistory() {
		return treatmenthistory;
	}

	public void setTreatmenthistory(String treatmenthistory) {
		this.treatmenthistory = treatmenthistory;
	}

	public ArrayList<UserProfile> getAllconsultantlistwithdepart() {
		return allconsultantlistwithdepart;
	}

	public void setAllconsultantlistwithdepart(ArrayList<UserProfile> allconsultantlistwithdepart) {
		this.allconsultantlistwithdepart = allconsultantlistwithdepart;
	}

	public String getActivefilter() {
		return activefilter;
	}

	public void setActivefilter(String activefilter) {
		this.activefilter = activefilter;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public ArrayList<Master> getOtdatesandids() {
		return otdatesandids;
	}

	public void setOtdatesandids(ArrayList<Master> otdatesandids) {
		this.otdatesandids = otdatesandids;
	}

	public ArrayList<Master> getOtnoteslist() {
		return otnoteslist;
	}

	public void setOtnoteslist(ArrayList<Master> otnoteslist) {
		this.otnoteslist = otnoteslist;
	}

	public String getDischargeStatusId() {
		return dischargeStatusId;
	}

	public void setDischargeStatusId(String dischargeStatusId) {
		this.dischargeStatusId = dischargeStatusId;
	}

	public String getDeathnote() {
		return deathnote;
	}

	public void setDeathnote(String deathnote) {
		this.deathnote = deathnote;
	}

	public ArrayList<Ipd> getRationalelist() {
	  return rationalelist;
	 }

	 public void setRationalelist(ArrayList<Ipd> rationalelist) {
	  this.rationalelist = rationalelist;
	 }

	 public ArrayList<Ipd> getNursingdiagnosislist() {
	  return nursingdiagnosislist;
	 }

	 public void setNursingdiagnosislist(ArrayList<Ipd> nursingdiagnosislist) {
	  this.nursingdiagnosislist = nursingdiagnosislist;
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
	private ArrayList<Master>disciplineList;
	private ArrayList<UserProfile> practionerlist;
	private ArrayList<Client> anesthesiaList;
	private String mlccase;
	private String searchdrname;
	private ArrayList<UserProfile> mlcdrlist;
	
	
	private String gen_condition, temp, pallor, pedal_edema, pulse, bmi, sys_bp, dia_bp, wall_edema, fundal_height,postmeal;

	private String cephalic, breech, baley_size, transverse_fhs, liquor, uterine_contractions, ps_cervix, ps_vagine;
	private String ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes, pv_tubular, pv_just_ettacced, pv_mid_posits, pv_soft, pv_ettacced, pv_post;
	private String totaldischarge;
	
	private String pmp, diagnosisgyn, le_vulva, le_vagina, pa_gynic, plan, finaldiagnosis;
	private String priscription, pv_uterus, pv_uterus_size, pv_bl_fomices, pv_mobility;
	private String supportiveDoctorName;
	private String supportiveQualification;
	private String useregno;
	
	private int check_indications=0;
	private int check_coamplications=0;
	private int check_institution=0;
	
	public String getPostmeal() {
		return postmeal;
	}

	public void setPostmeal(String postmeal) {
		this.postmeal = postmeal;
	}
	
	public int getCheck_indications() {
		return check_indications;
	}

	public void setCheck_indications(int check_indications) {
		this.check_indications = check_indications;
	}

	public int getCheck_coamplications() {
		return check_coamplications;
	}

	public void setCheck_coamplications(int check_coamplications) {
		this.check_coamplications = check_coamplications;
	}

	public int getCheck_institution() {
		return check_institution;
	}

	public void setCheck_institution(int check_institution) {
		this.check_institution = check_institution;
	}

	public String getUseregno() {
		return useregno;
	}

	public void setUseregno(String useregno) {
		this.useregno = useregno;
	}

	public String getSupportiveDoctorName() {
		return supportiveDoctorName;
	}

	public void setSupportiveDoctorName(String supportiveDoctorName) {
		this.supportiveDoctorName = supportiveDoctorName;
	}

	public String getSupportiveQualification() {
		return supportiveQualification;
	}

	public void setSupportiveQualification(String supportiveQualification) {
		this.supportiveQualification = supportiveQualification;
	}

	public String getTotaldischarge() {
		return totaldischarge;
	}

	public void setTotaldischarge(String totaldischarge) {
		this.totaldischarge = totaldischarge;
	}

	public String getGen_condition() {
		return gen_condition;
	}

	public void setGen_condition(String gen_condition) {
		this.gen_condition = gen_condition;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getPallor() {
		return pallor;
	}

	public void setPallor(String pallor) {
		this.pallor = pallor;
	}

	public String getPedal_edema() {
		return pedal_edema;
	}

	public void setPedal_edema(String pedal_edema) {
		this.pedal_edema = pedal_edema;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getSys_bp() {
		return sys_bp;
	}

	public void setSys_bp(String sys_bp) {
		this.sys_bp = sys_bp;
	}

	public String getDia_bp() {
		return dia_bp;
	}

	public void setDia_bp(String dia_bp) {
		this.dia_bp = dia_bp;
	}

	public String getWall_edema() {
		return wall_edema;
	}

	public void setWall_edema(String wall_edema) {
		this.wall_edema = wall_edema;
	}

	public String getFundal_height() {
		return fundal_height;
	}

	public void setFundal_height(String fundal_height) {
		this.fundal_height = fundal_height;
	}

	public String getCephalic() {
		return cephalic;
	}

	public void setCephalic(String cephalic) {
		this.cephalic = cephalic;
	}

	public String getBreech() {
		return breech;
	}

	public void setBreech(String breech) {
		this.breech = breech;
	}

	public String getBaley_size() {
		return baley_size;
	}

	public void setBaley_size(String baley_size) {
		this.baley_size = baley_size;
	}

	public String getTransverse_fhs() {
		return transverse_fhs;
	}

	public void setTransverse_fhs(String transverse_fhs) {
		this.transverse_fhs = transverse_fhs;
	}

	public String getLiquor() {
		return liquor;
	}

	public void setLiquor(String liquor) {
		this.liquor = liquor;
	}

	public String getUterine_contractions() {
		return uterine_contractions;
	}

	public void setUterine_contractions(String uterine_contractions) {
		this.uterine_contractions = uterine_contractions;
	}

	public String getPs_cervix() {
		return ps_cervix;
	}

	public void setPs_cervix(String ps_cervix) {
		this.ps_cervix = ps_cervix;
	}

	public String getPs_vagine() {
		return ps_vagine;
	}

	public void setPs_vagine(String ps_vagine) {
		this.ps_vagine = ps_vagine;
	}

	public String getPs_vault() {
		return ps_vault;
	}

	public void setPs_vault(String ps_vault) {
		this.ps_vault = ps_vault;
	}

	public String getPv_trim() {
		return pv_trim;
	}

	public void setPv_trim(String pv_trim) {
		this.pv_trim = pv_trim;
	}

	public String getPv_unettacced() {
		return pv_unettacced;
	}

	public void setPv_unettacced(String pv_unettacced) {
		this.pv_unettacced = pv_unettacced;
	}

	public String getPv_ant() {
		return pv_ant;
	}

	public void setPv_ant(String pv_ant) {
		this.pv_ant = pv_ant;
	}

	public String getPv_os() {
		return pv_os;
	}

	public void setPv_os(String pv_os) {
		this.pv_os = pv_os;
	}

	public String getPv_membranes() {
		return pv_membranes;
	}

	public void setPv_membranes(String pv_membranes) {
		this.pv_membranes = pv_membranes;
	}

	public String getPv_tubular() {
		return pv_tubular;
	}

	public void setPv_tubular(String pv_tubular) {
		this.pv_tubular = pv_tubular;
	}

	public String getPv_just_ettacced() {
		return pv_just_ettacced;
	}

	public void setPv_just_ettacced(String pv_just_ettacced) {
		this.pv_just_ettacced = pv_just_ettacced;
	}

	public String getPv_mid_posits() {
		return pv_mid_posits;
	}

	public void setPv_mid_posits(String pv_mid_posits) {
		this.pv_mid_posits = pv_mid_posits;
	}

	public String getPv_soft() {
		return pv_soft;
	}

	public void setPv_soft(String pv_soft) {
		this.pv_soft = pv_soft;
	}

	public String getPv_ettacced() {
		return pv_ettacced;
	}

	public void setPv_ettacced(String pv_ettacced) {
		this.pv_ettacced = pv_ettacced;
	}

	public String getPv_post() {
		return pv_post;
	}

	public void setPv_post(String pv_post) {
		this.pv_post = pv_post;
	}

	public boolean isVerification() {
		return verification;
	}

	public void setVerification(boolean verification) {
		this.verification = verification;
	}

	public ArrayList<UserProfile> getMlcdrlist() {
		return mlcdrlist;
	}

	public void setMlcdrlist(ArrayList<UserProfile> mlcdrlist) {
		this.mlcdrlist = mlcdrlist;
	}

	public String getSearchdrname() {
		return searchdrname;
	}

	public void setSearchdrname(String searchdrname) {
		this.searchdrname = searchdrname;
	}

	public String getMlccase() {
		return mlccase;
	}

	public void setMlccase(String mlccase) {
		this.mlccase = mlccase;
	}

	public ArrayList<Client> getAnesthesiaList() {
		return anesthesiaList;
	}

	public void setAnesthesiaList(ArrayList<Client> anesthesiaList) {
		this.anesthesiaList = anesthesiaList;
	}

	public ArrayList<UserProfile> getPractionerlist() {
		return practionerlist;
	}

	public void setPractionerlist(ArrayList<UserProfile> practionerlist) {
		this.practionerlist = practionerlist;
	}

	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public String getOperativetempname() {
		return operativetempname;
	}

	public void setOperativetempname(String operativetempname) {
		this.operativetempname = operativetempname;
	}

	public String getInvestigationtempname() {
		return investigationtempname;
	}

	public void setInvestigationtempname(String investigationtempname) {
		this.investigationtempname = investigationtempname;
	}

	public String getHospitalcoursetempname() {
		return hospitalcoursetempname;
	}

	public void setHospitalcoursetempname(String hospitalcoursetempname) {
		this.hospitalcoursetempname = hospitalcoursetempname;
	}

	public String getDisdefaulttempname() {
		return disdefaulttempname;
	}

	public void setDisdefaulttempname(String disdefaulttempname) {
		this.disdefaulttempname = disdefaulttempname;
	}

	public String getFindingondistempname() {
		return findingondistempname;
	}

	public void setFindingondistempname(String findingondistempname) {
		this.findingondistempname = findingondistempname;
	}

	public String getNursingadvicetempname() {
		return nursingadvicetempname;
	}

	public void setNursingadvicetempname(String nursingadvicetempname) {
		this.nursingadvicetempname = nursingadvicetempname;
	}

	public String getChiefcomplatetempname() {
		return chiefcomplatetempname;
	}

	public void setChiefcomplatetempname(String chiefcomplatetempname) {
		this.chiefcomplatetempname = chiefcomplatetempname;
	}

	public String getPresentillnesstempname() {
		return presentillnesstempname;
	}

	public void setPresentillnesstempname(String presentillnesstempname) {
		this.presentillnesstempname = presentillnesstempname;
	}

	public String getPasthistorytempname() {
		return pasthistorytempname;
	}

	public void setPasthistorytempname(String pasthistorytempname) {
		this.pasthistorytempname = pasthistorytempname;
	}

	public String getFamilyhistorytempname() {
		return familyhistorytempname;
	}

	public void setFamilyhistorytempname(String familyhistorytempname) {
		this.familyhistorytempname = familyhistorytempname;
	}

	public String getPersonalhistorytempname() {
		return personalhistorytempname;
	}

	public void setPersonalhistorytempname(String personalhistorytempname) {
		this.personalhistorytempname = personalhistorytempname;
	}

	public String getOnexaminationtempname() {
		return onexaminationtempname;
	}

	public void setOnexaminationtempname(String onexaminationtempname) {
		this.onexaminationtempname = onexaminationtempname;
	}

	public String getTreatmentgiventempname() {
		return treatmentgiventempname;
	}

	public void setTreatmentgiventempname(String treatmentgiventempname) {
		this.treatmentgiventempname = treatmentgiventempname;
	}

	public ArrayList<Master> getPkgsList() {
		return pkgsList;
	}

	public void setPkgsList(ArrayList<Master> pkgsList) {
		this.pkgsList = pkgsList;
	}

	public String getInvpkg() {
		return invpkg;
	}

	public void setInvpkg(String invpkg) {
		this.invpkg = invpkg;
	}

	public String getIpdseqno() {
		return ipdseqno;
	}

	public void setIpdseqno(String ipdseqno) {
		this.ipdseqno = ipdseqno;
	}

	public String getCasualtyipd() {
		return casualtyipd;
	}

	public void setCasualtyipd(String casualtyipd) {
		this.casualtyipd = casualtyipd;
	}

	private ArrayList<Priscription> listMedicineLog;
	private String height="";
	private String weight="";
	
	private ArrayList<Master> vitalMasterIOList;
	private ArrayList<Master> vitalMasterIVList;
	private ArrayList<Master> vitalMasterEquipmentList;
	private ArrayList<String> iotimeList;
	private ArrayList<String> ivtimeList;
	private ArrayList<String> eqtimeList;
	
	
	
	private ArrayList<Master> listNursingLog;
	private ArrayList<Bloodbank> bloodRequestedList;
	private  ArrayList<DietaryDetails> dietarydatalist;
	public ArrayList<DietaryDetails> getDietarydatalist() {
		return dietarydatalist;
	}

	public void setDietarydatalist(ArrayList<DietaryDetails> dietarydatalist) {
		this.dietarydatalist = dietarydatalist;
	}

	public String getCurdatetime() {
		return curdatetime;
	}

	public void setCurdatetime(String curdatetime) {
		this.curdatetime = curdatetime;
	}

	public String getWardname() {
		return wardname;
	}

	public void setWardname(String wardname) {
		this.wardname = wardname;
	}

	public String getInvseenstatus() {
		return invseenstatus;
	}

	public void setInvseenstatus(String invseenstatus) {
		this.invseenstatus = invseenstatus;
	}

	public String getFilter_status1() {
		return Filter_status1;
	}

	public void setFilter_status1(String filter_status1) {
		Filter_status1 = filter_status1;
	}

	public String filter_status="0";
	public String getFilter_status() {
		return filter_status;
	}

	public void setFilter_status(String filter_status) {
		this.filter_status = filter_status;
	}
	 private String addmitedbyuserid;
	 private String dischargebyid;
	public String getDischargebyid() {
		return dischargebyid;
	}

	public void setDischargebyid(String dischargebyid) {
		this.dischargebyid = dischargebyid;
	}

	public String getAddmitedbyuserid() {
		return addmitedbyuserid;
	}

	public void setAddmitedbyuserid(String addmitedbyuserid) {
		this.addmitedbyuserid = addmitedbyuserid;
	}

	private ArrayList<Master> otherTemplateList;
	private ArrayList<Master> procedureList;
	private ArrayList<UserProfile> staffList;
	private int id;
	
	private ArrayList<Bed> bedlist;
	private String substancehistory;
	private String menstraulhistory;
	
	private ArrayList<Bed> sectionlist;
	private String regno="";
	private ArrayList<String> countryList;
	
	private ArrayList<String> initialList;
	ArrayList<Bed> wardlist;
	ArrayList<Master> operativeList;
	
	ArrayList<Master> invSectionList;
	public ArrayList<Master> getInvSectionList() {
		return invSectionList;
	}

	public void setInvSectionList(ArrayList<Master> invSectionList) {
		this.invSectionList = invSectionList;
	}

	
	private String ps_fhs;
	 private String pv_membrane;
	 private String pv_station;
	 private String pv_liquor;
	 private String pv_pelvis;
	 private String pv_position;
	 private String beats_min;
	
	ArrayList<Master> vitalMasterList;
	ArrayList<String> departmentList;
	ArrayList<Location>locationList;
	private String printedBy;
	ArrayList<String> allConsultantList;
	ArrayList<Client> clientOccupationList;
	private String otstatus;
	private String anesthesia;
	private String country;
	
	boolean obstretic_history;
	boolean menstrual_history;  
	boolean substance_history;
	
	private String parity_abortion_notes="";
	private String p="0";
	private String l="0";
	private String a="0";
	private String d="0";
	private String mlcrefdoctor;
	ArrayList<Master>declerationTitleList;
	private String dectitle;
	public String getHdndecid() {
		return hdndecid;
	}

	public void setHdndecid(String hdndecid) {
		this.hdndecid = hdndecid;
	}

	private String hdndecid;
	
	public ArrayList<Master> getDeclerationTitleList() {
		return declerationTitleList;
	}

	public void setDeclerationTitleList(ArrayList<Master> declerationTitleList) {
		this.declerationTitleList = declerationTitleList;
	}

	public String getDectitle() {
		return dectitle;
	}

	public void setDectitle(String dectitle) {
		this.dectitle = dectitle;
	}

	public String getMlcrefdoctor() {
		return mlcrefdoctor;
	}

	public void setMlcrefdoctor(String mlcrefdoctor) {
		this.mlcrefdoctor = mlcrefdoctor;
	}

	String investigation;
	String treatmentgiven;
	String findondischarge;
	ArrayList<Master> citylist;
    ArrayList<Master> statelist;
    
    private String pt_history_notes;
	private String family_history_notes;
	private String family_history;
    
	private ArrayList<String> reasonVisitList;
    private String ivf_date;
	private String down_regulation;
	private String formtype="1";
	private String ovarian_stimulation;
	private String os_dosage;
	private String sperm_quality;
	private String et_day;
	private String oocytes_obtained;
	private String oocytes_quality;
	private String embroyos_grade;
	private String embroyos_transfered;
	private String embroyos_description;
	private String freezing;
	private String transfer_process;
	private String betahcgcm;
	private String ivf_remark;
	private String do_family_history;
	private String ho_fertility_family;
	private String ho_genetic_family;
	private String ho_premature_family;
	private String  age_of_menarche;
	private String age_of_menarche_notes;
	private String dysmenorrhoe="0";
	private String dysmenorrhoe_notes;
	private String flow;
	private String flow_notes;
	private String sleep_disruption_bleeding;
	private String sleep_disruption_bleeding_notes;
	private String blachouts, blachouts_notes;
	
	
	private String surgeon;
	private String anesthesiologist;
	private ArrayList<Client> diagnosisList;
	ArrayList<Diagnosis> diagnosislist;
	private String procedure;
	private String otNotes;
	private String ansintime;
	private ArrayList<Client> surgeryList;
	
	ArrayList<AppointmentType>additionalChargeList;
	private String appointmentText;
	private String asistantdoclist;
	ArrayList<Master>medicineList;
	ArrayList<Master> priscUnitList;
	ArrayList<Master>mdicneTypeList;

	private String selectedid;
	ArrayList<Master>mdicinecategoryList;
	
	ArrayList<Master>dosageList;
	
	private String priscdate;
	private String familyDetails;
	
	private String priscdateandtime;
	private String clinicLogo;
	
	ArrayList<TreatmentType> conditionlist;
	
    private ArrayList<DiaryManagement> userList;
    private ArrayList<DiaryManagement> ipdconsultdeptlist;
    public ArrayList<DiaryManagement> getIpdconsultdeptlist() {
		return ipdconsultdeptlist;
	}

	public void setIpdconsultdeptlist(ArrayList<DiaryManagement> ipdconsultdeptlist) {
		this.ipdconsultdeptlist = ipdconsultdeptlist;
	}
	private ArrayList<DiaryManagement> admittedbyuserList;
    public ArrayList<DiaryManagement> getAdmittedbyuserList() {
		return admittedbyuserList;
	}

	public void setAdmittedbyuserList(ArrayList<DiaryManagement> admittedbyuserList) {
		this.admittedbyuserList = admittedbyuserList;
	}
	private String contact;
    private ArrayList<String> monthList;
	private ArrayList<String> yearList;
    
    private ArrayList<Bed> nursingdoseList;
    private String payee;
    
    private String admissiondate;
    
    private ArrayList<Master>wardList;
    
    private Collection<Bed> conditions;
    private Collection<Master>chiefcomplain;
    
    private String conditionname;
    
    private ArrayList<EmailTemplate>smsTemplateList;
    
    private ArrayList<Priscription>parentPriscList;
    
    private String hour;
    
    private String minute;
    
    private ArrayList<String>hourList;
    
    private ArrayList<String>minuteList;
    private ArrayList<Bed> finalConditions;
    private ArrayList<Master>finalChifcomplain;
    private ArrayList<Master> finalhistory;
    private ArrayList<Master> finalinvestigation;
    private ArrayList<Master> finalnotes;
    ArrayList<Master>dosagenoteList;
    
    private String advoice;
    private String agegender;
    
    private String clientip;
    
    private String editclientid;
    
    ArrayList<Master>invsTypeList;
    
    ArrayList<Master>invstReportTypeList;
    
    ArrayList<Master>invstUnitList;
    
    ArrayList<Master>cbcIdList;
    
    private String locationid;
    
    private ArrayList<Bed> ipdpricsdoselist;
    
    private String secodryDoctorName;
    
    private String masterchargetype;
    
    ArrayList<Priscription>dischargePriscList;
    
    private String dischargedate;
    
    ArrayList<Discharge>dischargeOutcomeList;
    
    ArrayList<Discharge>dischargeStatusList;
    
    private String dischrgeOutcomes;
    
    private String dischargeStatus;
    
    private boolean chkDischarge;
    
    private String discadvnotes;
    
    private String hospitalcourse;
    
    private double balance;
    
    private String whopay;
    private String searchtext;
    
    private String clinicName;
	private String clinicOwner,clinicOwner1;
	private String clinicemail;
	private String clinicaddress;
	private String clinicity;
	private String websiteUrl;
	private String landLine;
	private String owner_qualification,owner_qualification1;
	private ArrayList<Clinic> locationAdressList;
    private String address;
    private String thirdParty;
    private ArrayList<String> jobTitleList;
    private String jobtitle;
    private String example;
    private String fromdate;
    private String todate;
    
  //admission form fields
    
    ArrayList<Client> thirdPartyTypeList;
    ArrayList<Master> standardChargesList;
    ArrayList<Bed> visitingConsultList;
    ArrayList<UserProfile> visitingConsultDoctors;
    ArrayList<String> visitingtimeList;
    private String date;
    private String time;
    private String fees;
    private ArrayList<Bed> activeIpdPatientList;
    
    private ArrayList<Master> nursingdetails;
    private ArrayList<Master> nursingcategorylist;
    
    
    private ArrayList<Master> chief_complaints_list;
	private ArrayList<Master> present_illness_list;
	private ArrayList<Master> past_history_list;
	private ArrayList<Master> family_history_list;
	private ArrayList<Master> personal_hist_list;
	private ArrayList<Master> on_exam_list;
	private ArrayList<Master> treatment_given_list;
    
    private ArrayList<Client> refrenceList;
    private String admission_reason;
    private String num_admission;
    private String relativename;
    private String relation;
    private String relationcont;
    private String earlierinvest;
    private String dob;
    private String alergies;
    private String filter;
    
    private String doctor_name;
    private String discipline;
    private String doctor_phone;
    ArrayList<Master> discharge_default_list;
	ArrayList<Master> hospital_course_list;
	ArrayList<Master> nursing_advice_list;
	ArrayList<Bed> bedLogList;
	private String qualification;
	
	ArrayList<Master> investigationList;
	ArrayList<Master> finding_on_dischargeList;
	
	
	
	//Gynic Details
	private String alcohal="0";
	private String drugs="0";
	private String other_medication;
	private String tobaco="0";
	private String tobaconotes;
	private String smoking="0";
	private String age_menarche="";
	private String lmp;
	private String llmp;
	private String pmc="0";
	private String cycle_length;
	private String duration_flow;;
	private String amount_flow;
	private String dysmenorrhea;
	private String dyspareunia="0";
	private String hopassing_clots;
	private String white_disc_itching;
	private String intercourse_freq;
	private String galactorrea;
	private String ho_contraception;
	private String rubella_vaccine;
	private String menopause;
	private String nulligravida;
	private String current_pregnent="0";
	private String iud="0";
	private String live_boys="0";
	private String live_girls="0";
	private String stillbirths="0";
	private String abortions="0";
	private String death_children="0";
	private String year="0";
	private String type="0";
	private String type_delivery="0";
	private String indications="";
	private String coamplications="";
	private String institution="";
	
	// new gynic form
	private String edd;
	private String usg;
	private String gravida;
	private String para;
	private String live;
	private String visit_reason_ids;
	private String abortion;
	private String mtp;
	private String still_born;
	private String death;
	private String high_risk_factor;
	private String hb;
	private String fbs;
	private String dpbs;
	private String urm;
	private String tsh;
	private String ict;
	private String gtt;
	private String hv_1m;
	private String hbs_ag;
	private String vdrl;
	private String hb_ac;
	private String hb_srecta;
	private String duet_markess;
	private String triple;
	private String quadrple_maicers;
	
	private String surgical_ho;
	private String fbs1;
	private String fbs2;
	private String fbs3;
	private String fbs4;
	
	private String dpbs1;
	private String dpbs2;
	private String dpbs3;
	private String dpbs4;
	
	private String urm1;
	private String urm2;
	private String urm3;
	private String urm4;
	
	private String tsh1;
	private String tsh2;
	private String tsh3;
	private String tsh4;
	
	private String ict1;
	private String ict2;
	private String ict3;
	private String ict4;
	
	private String gtt1;
	private String gtt2;
	private String gtt3;
	private String gtt4;
	
	private String hb1,hb2,hb3,hb4;
	
	private String treatcharge;
	private String chargetype;
	private Collection<Master>observation;
	private Collection<Master>Charges;
	private Collection<Bed> obslist;
	private ArrayList<Bed> gynicobsList;
	
	private String anormaly_scan_11week, cervical_length_11week, double_marker_11week;
	private String all_investigation_16week, sikling_16week, triple_marker_16week, abstinence_1visit;
	private String barrier_contra_1visit, bed_rest_1visit, book_1visit, csv_1visit, dispi_test_1visit;
	private String drug_reaction_1visit, hcg_1visit, heparin_1visit, oral_hygeine_1visit, other_test_1visit, physio_diet_1visit;
	private String rubelle_status_1visit, smart_doc_1visit, stream_cell_1visit, vaginities_1visit, animally_scan_20week;
	private String fetal_eco_20week, anti_d_24week, dipsi_24week, itc_24week, investigation_sos_30week, steroids_30week;
	private String vaginities_treatment_30week, breast_preparation_34week, color_doppler_34week, labour_counselling_34week, nst_34week, vaginities_treatment_34week;
	
	private String nst_date1, nst_date2, nst_date3, nst_date4, nst_date5, nst_date6;
	private String nst_time1, nst_time2, nst_time3, nst_time4, nst_time5, nst_time6;
	private String nst_indication1, nst_indication2, nst_indication3, nst_indication4, nst_indication5, nst_indication6;
	private String nst_duration1, nst_duration2, nst_duration3, nst_duration4, nst_duration5, nst_duration6;
	private String nst_interpretation1, nst_interpretation2, nst_interpretation3, nst_interpretation4, nst_interpretation5, nst_interpretation6;
	private String nst_intervention1, nst_intervention2, nst_intervention3, nst_intervention4, nst_intervention5, nst_intervention6;
	
	private String tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2, usgdate3, usgdate4;
	private String amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4, presentation1, presentation2, presentation3, presentation4;
	private String bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4;
	private String ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4;
	private String ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2, liquor3, liquor4;
	private String placenta1, placenta2, placenta3, placenta4, foetal_weight1, foetal_weight2, foetal_weight3, foetal_weight4;
	private String cervical_length1, cervical_length2, cervical_length3, cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan;

	
	private ArrayList<Master> specializationTemplateList;
	private ArrayList<Bed> allVisitReasonList;
	private boolean issystemicreview;
	
	private boolean history;
	private Collection<Master> histories;
	private Collection<Master> investigations;
	private Collection<Master> notes;
	private String remark;
	 private ArrayList<Client> refrencedoctorList;
	public ArrayList<Client> getRefrencedoctorList() {
		return refrencedoctorList;
	}

	public void setRefrencedoctorList(ArrayList<Client> refrencedoctorList) {
		this.refrencedoctorList = refrencedoctorList;
	}

	public ArrayList<Master> getSpecializationTemplateList() {
		return specializationTemplateList;
	}

	public void setSpecializationTemplateList(ArrayList<Master> specializationTemplateList) {
		this.specializationTemplateList = specializationTemplateList;
	}

	public String getTt_dose1() {
		return tt_dose1;
	}

	public void setTt_dose1(String tt_dose1) {
		this.tt_dose1 = tt_dose1;
	}

	public String getTt_dose2() {
		return tt_dose2;
	}

	public void setTt_dose2(String tt_dose2) {
		this.tt_dose2 = tt_dose2;
	}

	public String getInfluenza_vaccine() {
		return influenza_vaccine;
	}

	public void setInfluenza_vaccine(String influenza_vaccine) {
		this.influenza_vaccine = influenza_vaccine;
	}

	public String getUsgdate1() {
		return usgdate1;
	}

	public void setUsgdate1(String usgdate1) {
		this.usgdate1 = usgdate1;
	}

	public String getUsgdate2() {
		return usgdate2;
	}

	public void setUsgdate2(String usgdate2) {
		this.usgdate2 = usgdate2;
	}

	public String getUsgdate3() {
		return usgdate3;
	}

	public void setUsgdate3(String usgdate3) {
		this.usgdate3 = usgdate3;
	}

	public String getUsgdate4() {
		return usgdate4;
	}

	public void setUsgdate4(String usgdate4) {
		this.usgdate4 = usgdate4;
	}

	public String getAmenorrhea1() {
		return amenorrhea1;
	}

	public void setAmenorrhea1(String amenorrhea1) {
		this.amenorrhea1 = amenorrhea1;
	}

	public String getAmenorrhea2() {
		return amenorrhea2;
	}

	public void setAmenorrhea2(String amenorrhea2) {
		this.amenorrhea2 = amenorrhea2;
	}

	public String getAmenorrhea3() {
		return amenorrhea3;
	}

	public void setAmenorrhea3(String amenorrhea3) {
		this.amenorrhea3 = amenorrhea3;
	}

	public String getAmenorrhea4() {
		return amenorrhea4;
	}

	public void setAmenorrhea4(String amenorrhea4) {
		this.amenorrhea4 = amenorrhea4;
	}

	public String getPresentation1() {
		return presentation1;
	}

	public void setPresentation1(String presentation1) {
		this.presentation1 = presentation1;
	}

	public String getPresentation2() {
		return presentation2;
	}

	public void setPresentation2(String presentation2) {
		this.presentation2 = presentation2;
	}

	public String getPresentation3() {
		return presentation3;
	}

	public void setPresentation3(String presentation3) {
		this.presentation3 = presentation3;
	}

	public String getPresentation4() {
		return presentation4;
	}

	public void setPresentation4(String presentation4) {
		this.presentation4 = presentation4;
	}

	public String getBpdgs1() {
		return bpdgs1;
	}

	public void setBpdgs1(String bpdgs1) {
		this.bpdgs1 = bpdgs1;
	}

	public String getBpdgs2() {
		return bpdgs2;
	}

	public void setBpdgs2(String bpdgs2) {
		this.bpdgs2 = bpdgs2;
	}

	public String getBpdgs3() {
		return bpdgs3;
	}

	public void setBpdgs3(String bpdgs3) {
		this.bpdgs3 = bpdgs3;
	}

	public String getBpdgs4() {
		return bpdgs4;
	}

	public void setBpdgs4(String bpdgs4) {
		this.bpdgs4 = bpdgs4;
	}

	public String getHc1() {
		return hc1;
	}

	public void setHc1(String hc1) {
		this.hc1 = hc1;
	}

	public String getHc2() {
		return hc2;
	}

	public void setHc2(String hc2) {
		this.hc2 = hc2;
	}

	public String getHc3() {
		return hc3;
	}

	public void setHc3(String hc3) {
		this.hc3 = hc3;
	}

	public String getHc4() {
		return hc4;
	}

	public void setHc4(String hc4) {
		this.hc4 = hc4;
	}

	public String getAc1() {
		return ac1;
	}

	public void setAc1(String ac1) {
		this.ac1 = ac1;
	}

	public String getAc2() {
		return ac2;
	}

	public void setAc2(String ac2) {
		this.ac2 = ac2;
	}

	public String getAc3() {
		return ac3;
	}

	public void setAc3(String ac3) {
		this.ac3 = ac3;
	}

	public String getAc4() {
		return ac4;
	}

	public void setAc4(String ac4) {
		this.ac4 = ac4;
	}

	public String getFlcrl1() {
		return flcrl1;
	}

	public void setFlcrl1(String flcrl1) {
		this.flcrl1 = flcrl1;
	}

	public String getFlcrl2() {
		return flcrl2;
	}

	public void setFlcrl2(String flcrl2) {
		this.flcrl2 = flcrl2;
	}

	public String getFlcrl3() {
		return flcrl3;
	}

	public void setFlcrl3(String flcrl3) {
		this.flcrl3 = flcrl3;
	}

	public String getFlcrl4() {
		return flcrl4;
	}

	public void setFlcrl4(String flcrl4) {
		this.flcrl4 = flcrl4;
	}

	public String getGa_usg1() {
		return ga_usg1;
	}

	public void setGa_usg1(String ga_usg1) {
		this.ga_usg1 = ga_usg1;
	}

	public String getGa_usg2() {
		return ga_usg2;
	}

	public void setGa_usg2(String ga_usg2) {
		this.ga_usg2 = ga_usg2;
	}

	public String getGa_usg3() {
		return ga_usg3;
	}

	public void setGa_usg3(String ga_usg3) {
		this.ga_usg3 = ga_usg3;
	}

	public String getGa_usg4() {
		return ga_usg4;
	}

	public void setGa_usg4(String ga_usg4) {
		this.ga_usg4 = ga_usg4;
	}

	public String getLiquor1() {
		return liquor1;
	}

	public void setLiquor1(String liquor1) {
		this.liquor1 = liquor1;
	}

	public String getLiquor2() {
		return liquor2;
	}

	public void setLiquor2(String liquor2) {
		this.liquor2 = liquor2;
	}

	public String getLiquor3() {
		return liquor3;
	}

	public void setLiquor3(String liquor3) {
		this.liquor3 = liquor3;
	}

	public String getLiquor4() {
		return liquor4;
	}

	public void setLiquor4(String liquor4) {
		this.liquor4 = liquor4;
	}

	public String getPlacenta1() {
		return placenta1;
	}

	public void setPlacenta1(String placenta1) {
		this.placenta1 = placenta1;
	}

	public String getPlacenta2() {
		return placenta2;
	}

	public void setPlacenta2(String placenta2) {
		this.placenta2 = placenta2;
	}

	public String getPlacenta3() {
		return placenta3;
	}

	public void setPlacenta3(String placenta3) {
		this.placenta3 = placenta3;
	}

	public String getPlacenta4() {
		return placenta4;
	}

	public void setPlacenta4(String placenta4) {
		this.placenta4 = placenta4;
	}

	public String getFoetal_weight1() {
		return foetal_weight1;
	}

	public void setFoetal_weight1(String foetal_weight1) {
		this.foetal_weight1 = foetal_weight1;
	}

	public String getFoetal_weight2() {
		return foetal_weight2;
	}

	public void setFoetal_weight2(String foetal_weight2) {
		this.foetal_weight2 = foetal_weight2;
	}

	public String getFoetal_weight3() {
		return foetal_weight3;
	}

	public void setFoetal_weight3(String foetal_weight3) {
		this.foetal_weight3 = foetal_weight3;
	}

	public String getFoetal_weight4() {
		return foetal_weight4;
	}

	public void setFoetal_weight4(String foetal_weight4) {
		this.foetal_weight4 = foetal_weight4;
	}

	public String getCervical_length1() {
		return cervical_length1;
	}

	public void setCervical_length1(String cervical_length1) {
		this.cervical_length1 = cervical_length1;
	}

	public String getCervical_length2() {
		return cervical_length2;
	}

	public void setCervical_length2(String cervical_length2) {
		this.cervical_length2 = cervical_length2;
	}

	public String getCervical_length3() {
		return cervical_length3;
	}

	public void setCervical_length3(String cervical_length3) {
		this.cervical_length3 = cervical_length3;
	}

	public String getCervical_length4() {
		return cervical_length4;
	}

	public void setCervical_length4(String cervical_length4) {
		this.cervical_length4 = cervical_length4;
	}

	public String getNt_scan() {
		return nt_scan;
	}

	public void setNt_scan(String nt_scan) {
		this.nt_scan = nt_scan;
	}

	public String getAnomaly_scan() {
		return anomaly_scan;
	}

	public void setAnomaly_scan(String anomaly_scan) {
		this.anomaly_scan = anomaly_scan;
	}

	public String getColour_doppler_scan() {
		return colour_doppler_scan;
	}

	public void setColour_doppler_scan(String colour_doppler_scan) {
		this.colour_doppler_scan = colour_doppler_scan;
	}

	public String getNst_date1() {
		return nst_date1;
	}

	public void setNst_date1(String nst_date1) {
		this.nst_date1 = nst_date1;
	}

	public String getNst_date2() {
		return nst_date2;
	}

	public void setNst_date2(String nst_date2) {
		this.nst_date2 = nst_date2;
	}

	public String getNst_date3() {
		return nst_date3;
	}

	public void setNst_date3(String nst_date3) {
		this.nst_date3 = nst_date3;
	}

	public String getNst_date4() {
		return nst_date4;
	}

	public void setNst_date4(String nst_date4) {
		this.nst_date4 = nst_date4;
	}

	public String getNst_date5() {
		return nst_date5;
	}

	public void setNst_date5(String nst_date5) {
		this.nst_date5 = nst_date5;
	}

	public String getNst_date6() {
		return nst_date6;
	}

	public void setNst_date6(String nst_date6) {
		this.nst_date6 = nst_date6;
	}

	public String getNst_time1() {
		return nst_time1;
	}

	public void setNst_time1(String nst_time1) {
		this.nst_time1 = nst_time1;
	}

	public String getNst_time2() {
		return nst_time2;
	}

	public void setNst_time2(String nst_time2) {
		this.nst_time2 = nst_time2;
	}

	public String getNst_time3() {
		return nst_time3;
	}

	public void setNst_time3(String nst_time3) {
		this.nst_time3 = nst_time3;
	}

	public String getNst_time4() {
		return nst_time4;
	}

	public void setNst_time4(String nst_time4) {
		this.nst_time4 = nst_time4;
	}

	public String getNst_time5() {
		return nst_time5;
	}

	public void setNst_time5(String nst_time5) {
		this.nst_time5 = nst_time5;
	}

	public String getNst_time6() {
		return nst_time6;
	}

	public void setNst_time6(String nst_time6) {
		this.nst_time6 = nst_time6;
	}

	public String getNst_indication1() {
		return nst_indication1;
	}

	public void setNst_indication1(String nst_indication1) {
		this.nst_indication1 = nst_indication1;
	}

	public String getNst_indication2() {
		return nst_indication2;
	}

	public void setNst_indication2(String nst_indication2) {
		this.nst_indication2 = nst_indication2;
	}

	public String getNst_indication3() {
		return nst_indication3;
	}

	public void setNst_indication3(String nst_indication3) {
		this.nst_indication3 = nst_indication3;
	}

	public String getNst_indication4() {
		return nst_indication4;
	}

	public void setNst_indication4(String nst_indication4) {
		this.nst_indication4 = nst_indication4;
	}

	public String getNst_indication5() {
		return nst_indication5;
	}

	public void setNst_indication5(String nst_indication5) {
		this.nst_indication5 = nst_indication5;
	}

	public String getNst_indication6() {
		return nst_indication6;
	}

	public void setNst_indication6(String nst_indication6) {
		this.nst_indication6 = nst_indication6;
	}

	public String getNst_duration1() {
		return nst_duration1;
	}

	public void setNst_duration1(String nst_duration1) {
		this.nst_duration1 = nst_duration1;
	}

	public String getNst_duration2() {
		return nst_duration2;
	}

	public void setNst_duration2(String nst_duration2) {
		this.nst_duration2 = nst_duration2;
	}

	public String getNst_duration3() {
		return nst_duration3;
	}

	public void setNst_duration3(String nst_duration3) {
		this.nst_duration3 = nst_duration3;
	}

	public String getNst_duration4() {
		return nst_duration4;
	}

	public void setNst_duration4(String nst_duration4) {
		this.nst_duration4 = nst_duration4;
	}

	public String getNst_duration5() {
		return nst_duration5;
	}

	public void setNst_duration5(String nst_duration5) {
		this.nst_duration5 = nst_duration5;
	}

	public String getNst_duration6() {
		return nst_duration6;
	}

	public void setNst_duration6(String nst_duration6) {
		this.nst_duration6 = nst_duration6;
	}

	public String getNst_interpretation1() {
		return nst_interpretation1;
	}

	public void setNst_interpretation1(String nst_interpretation1) {
		this.nst_interpretation1 = nst_interpretation1;
	}

	public String getNst_interpretation2() {
		return nst_interpretation2;
	}

	public void setNst_interpretation2(String nst_interpretation2) {
		this.nst_interpretation2 = nst_interpretation2;
	}

	public String getNst_interpretation3() {
		return nst_interpretation3;
	}

	public void setNst_interpretation3(String nst_interpretation3) {
		this.nst_interpretation3 = nst_interpretation3;
	}

	public String getNst_interpretation4() {
		return nst_interpretation4;
	}

	
	public String getPs_fhs() {
		return ps_fhs;
	}

	public void setPs_fhs(String ps_fhs) {
		this.ps_fhs = ps_fhs;
	}

	public String getPv_membrane() {
		return pv_membrane;
	}

	public void setPv_membrane(String pv_membrane) {
		this.pv_membrane = pv_membrane;
	}

	public String getPv_station() {
		return pv_station;
	}

	public void setPv_station(String pv_station) {
		this.pv_station = pv_station;
	}

	public String getPv_liquor() {
		return pv_liquor;
	}

	public void setPv_liquor(String pv_liquor) {
		this.pv_liquor = pv_liquor;
	}

	public String getPv_pelvis() {
		return pv_pelvis;
	}

	public void setPv_pelvis(String pv_pelvis) {
		this.pv_pelvis = pv_pelvis;
	}

	public String getPv_position() {
		return pv_position;
	}

	public void setPv_position(String pv_position) {
		this.pv_position = pv_position;
	}

	public void setNst_interpretation4(String nst_interpretation4) {
		this.nst_interpretation4 = nst_interpretation4;
	}

	public String getNst_interpretation5() {
		return nst_interpretation5;
	}

	public void setNst_interpretation5(String nst_interpretation5) {
		this.nst_interpretation5 = nst_interpretation5;
	}

	public String getNst_interpretation6() {
		return nst_interpretation6;
	}

	public void setNst_interpretation6(String nst_interpretation6) {
		this.nst_interpretation6 = nst_interpretation6;
	}

	public String getNst_intervention1() {
		return nst_intervention1;
	}

	public void setNst_intervention1(String nst_intervention1) {
		this.nst_intervention1 = nst_intervention1;
	}

	public String getNst_intervention2() {
		return nst_intervention2;
	}

	public void setNst_intervention2(String nst_intervention2) {
		this.nst_intervention2 = nst_intervention2;
	}

	public String getNst_intervention3() {
		return nst_intervention3;
	}

	public void setNst_intervention3(String nst_intervention3) {
		this.nst_intervention3 = nst_intervention3;
	}

	public String getNst_intervention4() {
		return nst_intervention4;
	}

	public void setNst_intervention4(String nst_intervention4) {
		this.nst_intervention4 = nst_intervention4;
	}

	public String getNst_intervention5() {
		return nst_intervention5;
	}

	public void setNst_intervention5(String nst_intervention5) {
		this.nst_intervention5 = nst_intervention5;
	}

	public String getNst_intervention6() {
		return nst_intervention6;
	}

	public void setNst_intervention6(String nst_intervention6) {
		this.nst_intervention6 = nst_intervention6;
	}

	public String getAnormaly_scan_11week() {
		return anormaly_scan_11week;
	}

	public void setAnormaly_scan_11week(String anormaly_scan_11week) {
		this.anormaly_scan_11week = anormaly_scan_11week;
	}

	public String getCervical_length_11week() {
		return cervical_length_11week;
	}

	public void setCervical_length_11week(String cervical_length_11week) {
		this.cervical_length_11week = cervical_length_11week;
	}

	public String getDouble_marker_11week() {
		return double_marker_11week;
	}

	public void setDouble_marker_11week(String double_marker_11week) {
		this.double_marker_11week = double_marker_11week;
	}

	public String getEdd() {
		return edd;
	}

	public void setEdd(String edd) {
		this.edd = edd;
	}

	public String getUsg() {
		return usg;
	}

	public void setUsg(String usg) {
		this.usg = usg;
	}

	public String getGravida() {
		return gravida;
	}

	public void setGravida(String gravida) {
		this.gravida = gravida;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getLive() {
		return live;
	}

	public void setLive(String live) {
		this.live = live;
	}

	public String getAbortion() {
		return abortion;
	}

	public void setAbortion(String abortion) {
		this.abortion = abortion;
	}

	public String getMtp() {
		return mtp;
	}

	public void setMtp(String mtp) {
		this.mtp = mtp;
	}

	public String getStill_born() {
		return still_born;
	}

	public void setStill_born(String still_born) {
		this.still_born = still_born;
	}

	public String getDeath() {
		return death;
	}

	public void setDeath(String death) {
		this.death = death;
	}

	public String getHigh_risk_factor() {
		return high_risk_factor;
	}

	public void setHigh_risk_factor(String high_risk_factor) {
		this.high_risk_factor = high_risk_factor;
	}

	public String getHb() {
		return hb;
	}

	public void setHb(String hb) {
		this.hb = hb;
	}

	public String getFbs() {
		return fbs;
	}

	public void setFbs(String fbs) {
		this.fbs = fbs;
	}

	public String getDpbs() {
		return dpbs;
	}

	public void setDpbs(String dpbs) {
		this.dpbs = dpbs;
	}

	public String getUrm() {
		return urm;
	}

	public void setUrm(String urm) {
		this.urm = urm;
	}

	public String getTsh() {
		return tsh;
	}

	public void setTsh(String tsh) {
		this.tsh = tsh;
	}

	public String getIct() {
		return ict;
	}

	public void setIct(String ict) {
		this.ict = ict;
	}

	public String getGtt() {
		return gtt;
	}

	public void setGtt(String gtt) {
		this.gtt = gtt;
	}

	public String getHv_1m() {
		return hv_1m;
	}

	public void setHv_1m(String hv_1m) {
		this.hv_1m = hv_1m;
	}

	public String getHbs_ag() {
		return hbs_ag;
	}

	public void setHbs_ag(String hbs_ag) {
		this.hbs_ag = hbs_ag;
	}

	public String getVdrl() {
		return vdrl;
	}

	public void setVdrl(String vdrl) {
		this.vdrl = vdrl;
	}

	public String getHb_ac() {
		return hb_ac;
	}

	public void setHb_ac(String hb_ac) {
		this.hb_ac = hb_ac;
	}

	public String getHb_srecta() {
		return hb_srecta;
	}

	public void setHb_srecta(String hb_srecta) {
		this.hb_srecta = hb_srecta;
	}

	public String getDuet_markess() {
		return duet_markess;
	}

	public void setDuet_markess(String duet_markess) {
		this.duet_markess = duet_markess;
	}

	public String getTriple() {
		return triple;
	}

	public void setTriple(String triple) {
		this.triple = triple;
	}

	public String getQuadrple_maicers() {
		return quadrple_maicers;
	}

	public void setQuadrple_maicers(String quadrple_maicers) {
		this.quadrple_maicers = quadrple_maicers;
	}

	ArrayList<Priscription>templateNameList;
	ArrayList<Master>packageList;
	
	private String pkgfromdate;
	private String pkgtodate;
    private String abrivationid;
    
    private String patientIdAbrivation;
    
    private int totalbookedbed;
    private int totolintitaldischarge;
    private int totalbed;
    private int totalvacantbed;
    
	public int getTotalvacantbed() {
		return totalvacantbed;
	}

	public void setTotalvacantbed(int totalvacantbed) {
		this.totalvacantbed = totalvacantbed;
	}

	public int getTotalbed() {
		return totalbed;
	}

	public void setTotalbed(int totalbed) {
		this.totalbed = totalbed;
	}

	public int getTotalbookedbed() {
		return totalbookedbed;
	}

	public void setTotalbookedbed(int totalbookedbed) {
		this.totalbookedbed = totalbookedbed;
	}

	public int getTotolintitaldischarge() {
		return totolintitaldischarge;
	}

	public void setTotolintitaldischarge(int totolintitaldischarge) {
		this.totolintitaldischarge = totolintitaldischarge;
	}

	public String getPatientIdAbrivation() {
		return patientIdAbrivation;
	}
	public void setPatientIdAbrivation(String patientIdAbrivation) {
		this.patientIdAbrivation = patientIdAbrivation;
	}
    
    public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}

	public String getPkgfromdate() {
		return pkgfromdate;
	}

	public void setPkgfromdate(String pkgfromdate) {
		this.pkgfromdate = pkgfromdate;
	}

	public String getPkgtodate() {
		return pkgtodate;
	}

	public void setPkgtodate(String pkgtodate) {
		this.pkgtodate = pkgtodate;
	}

	public ArrayList<Master> getPackageList() {
		return packageList;
	}

	public void setPackageList(ArrayList<Master> packageList) {
		this.packageList = packageList;
	}

	public ArrayList<Priscription> getTemplateNameList() {
		return templateNameList;
	}

	public void setTemplateNameList(ArrayList<Priscription> templateNameList) {
		this.templateNameList = templateNameList;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public ArrayList<Bed> getBedLogList() {
		return bedLogList;
	}

	public void setBedLogList(ArrayList<Bed> bedLogList) {
		this.bedLogList = bedLogList;
	}

	public ArrayList<Master> getDischarge_default_list() {
		return discharge_default_list;
	}

	public void setDischarge_default_list(ArrayList<Master> discharge_default_list) {
		this.discharge_default_list = discharge_default_list;
	}

	public ArrayList<Master> getHospital_course_list() {
		return hospital_course_list;
	}

	public void setHospital_course_list(ArrayList<Master> hospital_course_list) {
		this.hospital_course_list = hospital_course_list;
	}

	public ArrayList<Master> getNursing_advice_list() {
		return nursing_advice_list;
	}

	public void setNursing_advice_list(ArrayList<Master> nursing_advice_list) {
		this.nursing_advice_list = nursing_advice_list;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getDoctor_phone() {
		return doctor_phone;
	}

	public void setDoctor_phone(String doctor_phone) {
		this.doctor_phone = doctor_phone;
	}

	public String getAlergies() {
		return alergies;
	}

	public void setAlergies(String alergies) {
		this.alergies = alergies;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEarlierinvest() {
		return earlierinvest;
	}

	public void setEarlierinvest(String earlierinvest) {
		this.earlierinvest = earlierinvest;
	}

	public String getRelativename() {
		return relativename;
	}

	public void setRelativename(String relativename) {
		this.relativename = relativename;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRelationcont() {
		return relationcont;
	}

	public void setRelationcont(String relationcont) {
		this.relationcont = relationcont;
	}

	public String getNum_admission() {
		return num_admission;
	}

	public void setNum_admission(String num_admission) {
		this.num_admission = num_admission;
	}

	public ArrayList<Client> getRefrenceList() {
		return refrenceList;
	}

	public void setRefrenceList(ArrayList<Client> refrenceList) {
		this.refrenceList = refrenceList;
	}

	public ArrayList<Master> getNursingdetails() {
		return nursingdetails;
	}

	public void setNursingdetails(ArrayList<Master> nursingdetails) {
		this.nursingdetails = nursingdetails;
	}

	public ArrayList<Master> getNursingcategorylist() {
		return nursingcategorylist;
	}

	public void setNursingcategorylist(ArrayList<Master> nursingcategorylist) {
		this.nursingcategorylist = nursingcategorylist;
	}

	public ArrayList<Bed> getActiveIpdPatientList() {
		return activeIpdPatientList;
	}

	public void setActiveIpdPatientList(ArrayList<Bed> activeIpdPatientList) {
		this.activeIpdPatientList = activeIpdPatientList;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<String> getVisitingtimeList() {
		return visitingtimeList;
	}

	public void setVisitingtimeList(ArrayList<String> visitingtimeList) {
		this.visitingtimeList = visitingtimeList;
	}

	public ArrayList<UserProfile> getVisitingConsultDoctors() {
		return visitingConsultDoctors;
	}

	public void setVisitingConsultDoctors(
			ArrayList<UserProfile> visitingConsultDoctors) {
		this.visitingConsultDoctors = visitingConsultDoctors;
	}

	public ArrayList<Bed> getVisitingConsultList() {
		return visitingConsultList;
	}

	public void setVisitingConsultList(ArrayList<Bed> visitingConsultList) {
		this.visitingConsultList = visitingConsultList;
	}

	public ArrayList<Master> getStandardChargesList() {
		return standardChargesList;
	}

	public void setStandardChargesList(ArrayList<Master> standardChargesList) {
		this.standardChargesList = standardChargesList;
	}

	public ArrayList<Priscription> getDischargePriscList() {
		return dischargePriscList;
	}

	public void setDischargePriscList(ArrayList<Priscription> dischargePriscList) {
		this.dischargePriscList = dischargePriscList;
	}

	public String getSecodryDoctorName() {
		return secodryDoctorName;
	}

	public void setSecodryDoctorName(String secodryDoctorName) {
		this.secodryDoctorName = secodryDoctorName;
	}

	public ArrayList<Bed> getIpdpricsdoselist() {
		return ipdpricsdoselist;
	}

	public void setIpdpricsdoselist(ArrayList<Bed> ipdpricsdoselist) {
		this.ipdpricsdoselist = ipdpricsdoselist;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public ArrayList<Master> getInvsTypeList() {
		return invsTypeList;
	}

	public void setInvsTypeList(ArrayList<Master> invsTypeList) {
		this.invsTypeList = invsTypeList;
	}

	public ArrayList<Master> getInvstReportTypeList() {
		return invstReportTypeList;
	}

	public void setInvstReportTypeList(ArrayList<Master> invstReportTypeList) {
		this.invstReportTypeList = invstReportTypeList;
	}

	public ArrayList<Master> getInvstUnitList() {
		return invstUnitList;
	}

	public void setInvstUnitList(ArrayList<Master> invstUnitList) {
		this.invstUnitList = invstUnitList;
	}

	public ArrayList<Master> getCbcIdList() {
		return cbcIdList;
	}

	public void setCbcIdList(ArrayList<Master> cbcIdList) {
		this.cbcIdList = cbcIdList;
	}

	public String getEditclientid() {
		return editclientid;
	}

	public void setEditclientid(String editclientid) {
		this.editclientid = editclientid;
	}

	public String getAgegender() {
		return agegender;
	}

	public void setAgegender(String agegender) {
		this.agegender = agegender;
	}

	public String getClientip() {
		return clientip;
	}

	public void setClientip(String clientip) {
		this.clientip = clientip;
	}

	public ArrayList<Master> getDosagenoteList() {
		return dosagenoteList;
	}

	public void setDosagenoteList(ArrayList<Master> dosagenoteList) {
		this.dosagenoteList = dosagenoteList;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public ArrayList<String> getHourList() {
		return hourList;
	}

	public void setHourList(ArrayList<String> hourList) {
		this.hourList = hourList;
	}

	public ArrayList<String> getMinuteList() {
		return minuteList;
	}

	public void setMinuteList(ArrayList<String> minuteList) {
		this.minuteList = minuteList;
	}

	public ArrayList<Priscription> getParentPriscList() {
		return parentPriscList;
	}

	public void setParentPriscList(ArrayList<Priscription> parentPriscList) {
		this.parentPriscList = parentPriscList;
	}

	public ArrayList<EmailTemplate> getSmsTemplateList() {
		return smsTemplateList;
	}

	public void setSmsTemplateList(ArrayList<EmailTemplate> smsTemplateList) {
		this.smsTemplateList = smsTemplateList;
	}

	public Collection<Bed> getConditions() {
		return conditions;
	}

	public void setConditions(Collection<Bed> conditions) {
		this.conditions = conditions;
	}

	public String getConditionname() {
		return conditionname;
	}

	public void setConditionname(String conditionname) {
		this.conditionname = conditionname;
	}

	public ArrayList<Master> getWardList() {
		return wardList;
	}

	public void setWardList(ArrayList<Master> wardList) {
		this.wardList = wardList;
	}

	private String clientid;
    private String practitionerid;
    private String conditionid;
    private String refferedby;
    private String wardid;
    private String bedid;
    private String tpid;
    private String status;
    private String addmissionfor;
    private String reimbursment;
    private String secndryconsult = "";;
    private String mlcno;
    private String admissiondetails;
    private String suggestedtrtment;
    private String hosp;
    private String packagename;
    private String chiefcomplains;
    private String presentillness;
    private String pasthistory;
    private String personalhist;
    private String familyhist;
    private String onexamination;
    private String treatmentepisodeid;
    private String department;
    
    
    //yes or no fields
    
    private String suggestoper;
    private String systdepartment;
    private String fpcondition;
    private String fpnotest;
    private String nauseacondition;
    private String nauseanotes;
    private String fnucondition;
    private String fnunotes;
    private String smcondition;
    private String smnotes;
    private String chestpaincond;
    private String chestpainnotes;
    private String dimvisioncond;
    private String dimvisionnotes;
    private String hgucondition;
    private String hgunotes;
    private String hmcondition;
    private String hmnotes;
    private String jointpaincond;
    private String jointpainnotes;
    private String constipationcond;
    private String constpationnotes;
    private String specialnotes;
    private String edemafeetcondi;
    private String edemafeetnotes;
    private String hematuriacondi;
    private String hematurianotes;
    private String bmcondition;
    private String bmnotes;
    private String oliguriacondi;
    private String oligurianotes;
    private String pstgucondi;
    private String pstgunotes;
    private String bmecondition;
    private String bmenotes;
    private String tnecondition;
    private String tnenotes;
    private String weaknesscondi;
    private String weaknessnotes;
    private String ihcondition;
    private String ihnotes;
    
    
    private ArrayList<Accounts>thirdPartyList;
    
    private ArrayList<Client> condtitionList;
    
    ArrayList<Client> sourceOfIntroList;
    
    private ArrayList<TreatmentEpisode> sourceOfReferralList;
    
    private ArrayList<TreatmentEpisode> treatmentEpisodeList;
    
    private String treatmentEpisode;
    
    private String client;
    
    private ArrayList<Master>masterChageTypeList;
    
    
    
    
    
    private String ipdid;
    
    private String parentpriscid;
    
    
    public String getParentpriscid() {
		return parentpriscid;
	}

	public void setParentpriscid(String parentpriscid) {
		this.parentpriscid = parentpriscid;
	}

	public String getIpdid() {
		return ipdid;
	}

	public String getRefdocotor() {
		return refdocotor;
	}

	public void setRefdocotor(String refdocotor) {
		this.refdocotor = refdocotor;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	private String today;
    private String tomorow;
    private String dayAfterTomorow;
    private String enddate;
    private String stddate;
    
    private String date1,date2,date3,date4;
    
    
    private ArrayList<Bed> doseremainders;
    private String refdocotor = "";;
       
    public ArrayList<Bed> getDoseremainders() {
		return doseremainders;
	}

	public void setDoseremainders(ArrayList<Bed> doseremainders) {
		this.doseremainders = doseremainders;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getDate3() {
		return date3;
	}

	public void setDate3(String date3) {
		this.date3 = date3;
	}

	private String prisc_id, presc_rem_id, dosename, datetime;
    
    

    
    public String getPrisc_id() {
		return prisc_id;
	}

	public void setPrisc_id(String prisc_id) {
		this.prisc_id = prisc_id;
	}

	public String getPresc_rem_id() {
		return presc_rem_id;
	}

	public void setPresc_rem_id(String presc_rem_id) {
		this.presc_rem_id = presc_rem_id;
	}

	public String getDosename() {
		return dosename;
	}

	public void setDosename(String dosename) {
		this.dosename = dosename;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	private String medicinename, dose,lastmodified;
    private ArrayList<Bed> treatmentlist;
    
    private ArrayList<Bed> ipdconditionlist;
    
    
    

	public ArrayList<Bed> getIpdconditionlist() {
		return ipdconditionlist;
	}

	public void setIpdconditionlist(ArrayList<Bed> ipdconditionlist) {
		this.ipdconditionlist = ipdconditionlist;
	}

	public String getMedicinename() {
		return medicinename;
	}

	public void setMedicinename(String medicinename) {
		this.medicinename = medicinename;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}

	public ArrayList<Bed> getTreatmentlist() {
		return treatmentlist;
	}

	public void setTreatmentlist(ArrayList<Bed> treatmentlist) {
		this.treatmentlist = treatmentlist;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getTomorow() {
		return tomorow;
	}

	public void setTomorow(String tomorow) {
		this.tomorow = tomorow;
	}

	public String getDayAfterTomorow() {
		return dayAfterTomorow;
	}

	public void setDayAfterTomorow(String dayAfterTomorow) {
		this.dayAfterTomorow = dayAfterTomorow;
	}

	public ArrayList<Master> getMasterChageTypeList() {
		return masterChageTypeList;
	}

	public void setMasterChageTypeList(ArrayList<Master> masterChageTypeList) {
		this.masterChageTypeList = masterChageTypeList;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getTreatmentEpisode() {
		return treatmentEpisode;
	}

	public void setTreatmentEpisode(String treatmentEpisode) {
		this.treatmentEpisode = treatmentEpisode;
	}

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList() {
		return treatmentEpisodeList;
	}

	public void setTreatmentEpisodeList(
			ArrayList<TreatmentEpisode> treatmentEpisodeList) {
		this.treatmentEpisodeList = treatmentEpisodeList;
	}

	public ArrayList<TreatmentEpisode> getSourceOfReferralList() {
		return sourceOfReferralList;
	}

	public void setSourceOfReferralList(
			ArrayList<TreatmentEpisode> sourceOfReferralList) {
		this.sourceOfReferralList = sourceOfReferralList;
	}

	public ArrayList<Client> getSourceOfIntroList() {
		return sourceOfIntroList;
	}

	public void setSourceOfIntroList(ArrayList<Client> sourceOfIntroList) {
		this.sourceOfIntroList = sourceOfIntroList;
	}

	public ArrayList<Client> getCondtitionList() {
		return condtitionList;
	}

	public void setCondtitionList(ArrayList<Client> condtitionList) {
		this.condtitionList = condtitionList;
	}

	public ArrayList<Accounts> getThirdPartyList() {
		return thirdPartyList;
	}

	public void setThirdPartyList(ArrayList<Accounts> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getRefferedby() {
		return refferedby;
	}

	public void setRefferedby(String refferedby) {
		this.refferedby = refferedby;
	}

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getBedid() {
		return bedid;
	}

	public void setBedid(String bedid) {
		this.bedid = bedid;
	}

	public String getTpid() {
		return tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddmissionfor() {
		return addmissionfor;
	}

	public void setAddmissionfor(String addmissionfor) {
		this.addmissionfor = addmissionfor;
	}

	public String getReimbursment() {
		return reimbursment;
	}

	public void setReimbursment(String reimbursment) {
		this.reimbursment = reimbursment;
	}

	public String getSecndryconsult() {
		return secndryconsult;
	}

	public void setSecndryconsult(String secndryconsult) {
		this.secndryconsult = secndryconsult;
	}

	public String getMlcno() {
		return mlcno;
	}

	public void setMlcno(String mlcno) {
		this.mlcno = mlcno;
	}

	public String getAdmissiondetails() {
		return admissiondetails;
	}

	public void setAdmissiondetails(String admissiondetails) {
		this.admissiondetails = admissiondetails;
	}

	public String getSuggestedtrtment() {
		return suggestedtrtment;
	}

	public void setSuggestedtrtment(String suggestedtrtment) {
		this.suggestedtrtment = suggestedtrtment;
	}

	public String getHosp() {
		return hosp;
	}

	public void setHosp(String hosp) {
		this.hosp = hosp;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getChiefcomplains() {
		return chiefcomplains;
	}

	public void setChiefcomplains(String chiefcomplains) {
		this.chiefcomplains = chiefcomplains;
	}

	public String getPresentillness() {
		return presentillness;
	}

	public void setPresentillness(String presentillness) {
		this.presentillness = presentillness;
	}

	public String getPasthistory() {
		return pasthistory;
	}

	public void setPasthistory(String pasthistory) {
		this.pasthistory = pasthistory;
	}

	public String getPersonalhist() {
		return personalhist;
	}

	public void setPersonalhist(String personalhist) {
		this.personalhist = personalhist;
	}

	public String getFamilyhist() {
		return familyhist;
	}

	public void setFamilyhist(String familyhist) {
		this.familyhist = familyhist;
	}

	public String getOnexamination() {
		return onexamination;
	}

	public void setOnexamination(String onexamination) {
		this.onexamination = onexamination;
	}

	public String getTreatmentepisodeid() {
		return treatmentepisodeid;
	}

	public void setTreatmentepisodeid(String treatmentepisodeid) {
		this.treatmentepisodeid = treatmentepisodeid;
	}

	public String getSuggestoper() {
		return suggestoper;
	}

	public void setSuggestoper(String suggestoper) {
		this.suggestoper = suggestoper;
	}

	public String getSystdepartment() {
		return systdepartment;
	}

	public void setSystdepartment(String systdepartment) {
		this.systdepartment = systdepartment;
	}

	public String getFpcondition() {
		return fpcondition;
	}

	public void setFpcondition(String fpcondition) {
		this.fpcondition = fpcondition;
	}

	public String getFpnotest() {
		return fpnotest;
	}

	public void setFpnotest(String fpnotest) {
		this.fpnotest = fpnotest;
	}

	public String getNauseacondition() {
		return nauseacondition;
	}

	public void setNauseacondition(String nauseacondition) {
		this.nauseacondition = nauseacondition;
	}

	public String getNauseanotes() {
		return nauseanotes;
	}

	public void setNauseanotes(String nauseanotes) {
		this.nauseanotes = nauseanotes;
	}

	public String getFnucondition() {
		return fnucondition;
	}

	public void setFnucondition(String fnucondition) {
		this.fnucondition = fnucondition;
	}

	public String getFnunotes() {
		return fnunotes;
	}

	public void setFnunotes(String fnunotes) {
		this.fnunotes = fnunotes;
	}

	public String getSmcondition() {
		return smcondition;
	}

	public void setSmcondition(String smcondition) {
		this.smcondition = smcondition;
	}

	public String getSmnotes() {
		return smnotes;
	}

	public void setSmnotes(String smnotes) {
		this.smnotes = smnotes;
	}

	public String getChestpaincond() {
		return chestpaincond;
	}

	public void setChestpaincond(String chestpaincond) {
		this.chestpaincond = chestpaincond;
	}

	public String getChestpainnotes() {
		return chestpainnotes;
	}

	public void setChestpainnotes(String chestpainnotes) {
		this.chestpainnotes = chestpainnotes;
	}

	public String getDimvisioncond() {
		return dimvisioncond;
	}

	public void setDimvisioncond(String dimvisioncond) {
		this.dimvisioncond = dimvisioncond;
	}

	public String getDimvisionnotes() {
		return dimvisionnotes;
	}

	public void setDimvisionnotes(String dimvisionnotes) {
		this.dimvisionnotes = dimvisionnotes;
	}

	public String getHgucondition() {
		return hgucondition;
	}

	public void setHgucondition(String hgucondition) {
		this.hgucondition = hgucondition;
	}

	public String getHgunotes() {
		return hgunotes;
	}

	public void setHgunotes(String hgunotes) {
		this.hgunotes = hgunotes;
	}

	public String getHmcondition() {
		return hmcondition;
	}

	public void setHmcondition(String hmcondition) {
		this.hmcondition = hmcondition;
	}

	public String getHmnotes() {
		return hmnotes;
	}

	public void setHmnotes(String hmnotes) {
		this.hmnotes = hmnotes;
	}

	public String getJointpaincond() {
		return jointpaincond;
	}

	public void setJointpaincond(String jointpaincond) {
		this.jointpaincond = jointpaincond;
	}

	public String getJointpainnotes() {
		return jointpainnotes;
	}

	public void setJointpainnotes(String jointpainnotes) {
		this.jointpainnotes = jointpainnotes;
	}

	public String getConstipationcond() {
		return constipationcond;
	}

	public void setConstipationcond(String constipationcond) {
		this.constipationcond = constipationcond;
	}

	public String getConstpationnotes() {
		return constpationnotes;
	}

	public void setConstpationnotes(String constpationnotes) {
		this.constpationnotes = constpationnotes;
	}

	public String getSpecialnotes() {
		return specialnotes;
	}

	public void setSpecialnotes(String specialnotes) {
		this.specialnotes = specialnotes;
	}

	public String getEdemafeetcondi() {
		return edemafeetcondi;
	}

	public void setEdemafeetcondi(String edemafeetcondi) {
		this.edemafeetcondi = edemafeetcondi;
	}

	public String getEdemafeetnotes() {
		return edemafeetnotes;
	}

	public void setEdemafeetnotes(String edemafeetnotes) {
		this.edemafeetnotes = edemafeetnotes;
	}

	public String getHematuriacondi() {
		return hematuriacondi;
	}

	public void setHematuriacondi(String hematuriacondi) {
		this.hematuriacondi = hematuriacondi;
	}

	public String getHematurianotes() {
		return hematurianotes;
	}

	public void setHematurianotes(String hematurianotes) {
		this.hematurianotes = hematurianotes;
	}

	public String getBmcondition() {
		return bmcondition;
	}

	public void setBmcondition(String bmcondition) {
		this.bmcondition = bmcondition;
	}

	public String getBmnotes() {
		return bmnotes;
	}

	public void setBmnotes(String bmnotes) {
		this.bmnotes = bmnotes;
	}

	public String getOliguriacondi() {
		return oliguriacondi;
	}

	public void setOliguriacondi(String oliguriacondi) {
		this.oliguriacondi = oliguriacondi;
	}

	public String getOligurianotes() {
		return oligurianotes;
	}

	public void setOligurianotes(String oligurianotes) {
		this.oligurianotes = oligurianotes;
	}

	public String getPstgucondi() {
		return pstgucondi;
	}

	public void setPstgucondi(String pstgucondi) {
		this.pstgucondi = pstgucondi;
	}

	public String getPstgunotes() {
		return pstgunotes;
	}

	public void setPstgunotes(String pstgunotes) {
		this.pstgunotes = pstgunotes;
	}

	public String getBmecondition() {
		return bmecondition;
	}

	public void setBmecondition(String bmecondition) {
		this.bmecondition = bmecondition;
	}

	public String getBmenotes() {
		return bmenotes;
	}

	public void setBmenotes(String bmenotes) {
		this.bmenotes = bmenotes;
	}

	public String getTnecondition() {
		return tnecondition;
	}

	public void setTnecondition(String tnecondition) {
		this.tnecondition = tnecondition;
	}

	public String getTnenotes() {
		return tnenotes;
	}

	public void setTnenotes(String tnenotes) {
		this.tnenotes = tnenotes;
	}

	public String getWeaknesscondi() {
		return weaknesscondi;
	}

	public void setWeaknesscondi(String weaknesscondi) {
		this.weaknesscondi = weaknesscondi;
	}

	public String getWeaknessnotes() {
		return weaknessnotes;
	}

	public void setWeaknessnotes(String weaknessnotes) {
		this.weaknessnotes = weaknessnotes;
	}

	public String getIhcondition() {
		return ihcondition;
	}

	public void setIhcondition(String ihcondition) {
		this.ihcondition = ihcondition;
	}

	public String getIhnotes() {
		return ihnotes;
	}

	public void setIhnotes(String ihnotes) {
		this.ihnotes = ihnotes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Bed> getBedlist() {
		return bedlist;
	}

	public void setBedlist(ArrayList<Bed> bedlist) {
		this.bedlist = bedlist;
	}

	public ArrayList<Bed> getSectionlist() {
		return sectionlist;
	}

	public void setSectionlist(ArrayList<Bed> sectionlist) {
		this.sectionlist = sectionlist;
	}

	public ArrayList<Bed> getWardlist() {
		return wardlist;
	}

	public void setWardlist(ArrayList<Bed> wardlist) {
		this.wardlist = wardlist;
	}

	public ArrayList<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}

	public ArrayList<AppointmentType> getAdditionalChargeList() {
		return additionalChargeList;
	}

	public void setAdditionalChargeList(
			ArrayList<AppointmentType> additionalChargeList) {
		this.additionalChargeList = additionalChargeList;
	}

	public ArrayList<Master> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(ArrayList<Master> medicineList) {
		this.medicineList = medicineList;
	}

	public ArrayList<Master> getMdicneTypeList() {
		return mdicneTypeList;
	}

	public void setMdicneTypeList(ArrayList<Master> mdicneTypeList) {
		this.mdicneTypeList = mdicneTypeList;
	}

	public ArrayList<Master> getMdicinecategoryList() {
		return mdicinecategoryList;
	}

	public void setMdicinecategoryList(ArrayList<Master> mdicinecategoryList) {
		this.mdicinecategoryList = mdicinecategoryList;
	}

	public ArrayList<Master> getDosageList() {
		return dosageList;
	}

	public void setDosageList(ArrayList<Master> dosageList) {
		this.dosageList = dosageList;
	}

	public String getPriscdate() {
		return priscdate;
	}

	public void setPriscdate(String priscdate) {
		this.priscdate = priscdate;
	}

	public String getPriscdateandtime() {
		return priscdateandtime;
	}

	public void setPriscdateandtime(String priscdateandtime) {
		this.priscdateandtime = priscdateandtime;
	}

	public ArrayList<TreatmentType> getConditionlist() {
		return conditionlist;
	}

	public void setConditionlist(ArrayList<TreatmentType> conditionlist) {
		this.conditionlist = conditionlist;
	}

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	public String getAdmissiondate() {
		return admissiondate;
	}

	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}

	public String getMasterchargetype() {
		return masterchargetype;
	}

	public void setMasterchargetype(String masterchargetype) {
		this.masterchargetype = masterchargetype;
	}

	public String getDischargedate() {
		return dischargedate;
	}

	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
	}

	public ArrayList<Discharge> getDischargeOutcomeList() {
		return dischargeOutcomeList;
	}

	public void setDischargeOutcomeList(ArrayList<Discharge> dischargeOutcomeList) {
		this.dischargeOutcomeList = dischargeOutcomeList;
	}

	public ArrayList<Discharge> getDischargeStatusList() {
		return dischargeStatusList;
	}

	public void setDischargeStatusList(ArrayList<Discharge> dischargeStatusList) {
		this.dischargeStatusList = dischargeStatusList;
	}

	public String getDischrgeOutcomes() {
		return dischrgeOutcomes;
	}

	public void setDischrgeOutcomes(String dischrgeOutcomes) {
		this.dischrgeOutcomes = dischrgeOutcomes;
	}

	public String getDischargeStatus() {
		return dischargeStatus;
	}

	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	public boolean isChkDischarge() {
		return chkDischarge;
	}

	public void setChkDischarge(boolean chkDischarge) {
		this.chkDischarge = chkDischarge;
	}

	public String getDiscadvnotes() {
		return discadvnotes;
	}

	public void setDiscadvnotes(String discadvnotes) {
		this.discadvnotes = discadvnotes;
	}

	public String getHospitalcourse() {
		return hospitalcourse;
	}

	public void setHospitalcourse(String hospitalcourse) {
		this.hospitalcourse = hospitalcourse;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getWhopay() {
		return whopay;
	}

	public void setWhopay(String whopay) {
		this.whopay = whopay;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicOwner() {
		return clinicOwner;
	}

	public void setClinicOwner(String clinicOwner) {
		this.clinicOwner = clinicOwner;
	}

	public String getClinicemail() {
		return clinicemail;
	}

	public void setClinicemail(String clinicemail) {
		this.clinicemail = clinicemail;
	}

	public String getClinicaddress() {
		return clinicaddress;
	}

	public void setClinicaddress(String clinicaddress) {
		this.clinicaddress = clinicaddress;
	}

	public String getClinicity() {
		return clinicity;
	}

	public void setClinicity(String clinicity) {
		this.clinicity = clinicity;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}

	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}

	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public ArrayList<String> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(ArrayList<String> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public ArrayList<Bed> getNursingdoseList() {
		return nursingdoseList;
	}

	public void setNursingdoseList(ArrayList<Bed> nursingdoseList) {
		this.nursingdoseList = nursingdoseList;
	}

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}

	public ArrayList<Client> getThirdPartyTypeList() {
		return thirdPartyTypeList;
	}

	public void setThirdPartyTypeList(ArrayList<Client> thirdPartyTypeList) {
		this.thirdPartyTypeList = thirdPartyTypeList;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getAdvoice() {
		return advoice;
	}

	public void setAdvoice(String advoice) {
		this.advoice = advoice;
	}

	public ArrayList<Bed> getFinalConditions() {
		return finalConditions;
	}

	public void setFinalConditions(ArrayList<Bed> finalConditions) {
		this.finalConditions = finalConditions;
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

	public ArrayList<Master> getChief_complaints_list() {
		return chief_complaints_list;
	}

	public void setChief_complaints_list(ArrayList<Master> chief_complaints_list) {
		this.chief_complaints_list = chief_complaints_list;
	}

	public ArrayList<Master> getPresent_illness_list() {
		return present_illness_list;
	}

	public void setPresent_illness_list(ArrayList<Master> present_illness_list) {
		this.present_illness_list = present_illness_list;
	}

	public ArrayList<Master> getPast_history_list() {
		return past_history_list;
	}

	public void setPast_history_list(ArrayList<Master> past_history_list) {
		this.past_history_list = past_history_list;
	}

	public ArrayList<Master> getFamily_history_list() {
		return family_history_list;
	}

	public void setFamily_history_list(ArrayList<Master> family_history_list) {
		this.family_history_list = family_history_list;
	}

	public ArrayList<Master> getPersonal_hist_list() {
		return personal_hist_list;
	}

	public void setPersonal_hist_list(ArrayList<Master> personal_hist_list) {
		this.personal_hist_list = personal_hist_list;
	}

	public ArrayList<Master> getOn_exam_list() {
		return on_exam_list;
	}

	public void setOn_exam_list(ArrayList<Master> on_exam_list) {
		this.on_exam_list = on_exam_list;
	}

	public ArrayList<Master> getTreatment_given_list() {
		return treatment_given_list;
	}

	public void setTreatment_given_list(ArrayList<Master> treatment_given_list) {
		this.treatment_given_list = treatment_given_list;
	}

	public String getAdmission_reason() {
		return admission_reason;
	}

	public void setAdmission_reason(String admission_reason) {
		this.admission_reason = admission_reason;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public ArrayList<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<String> monthList) {
		this.monthList = monthList;
	}

	public ArrayList<String> getYearList() {
		return yearList;
	}

	public void setYearList(ArrayList<String> yearList) {
		this.yearList = yearList;
	}

	public String getFamilyDetails() {
		return familyDetails;
	}

	public void setFamilyDetails(String familyDetails) {
		this.familyDetails = familyDetails;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public String getAnesthesia() {
		return anesthesia;
	}

	public void setAnesthesia(String anesthesia) {
		this.anesthesia = anesthesia;
	}

	public String getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}

	public String getAnesthesiologist() {
		return anesthesiologist;
	}

	public void setAnesthesiologist(String anesthesiologist) {
		this.anesthesiologist = anesthesiologist;
	}

	public String getOtNotes() {
		return otNotes;
	}

	public void setOtNotes(String otNotes) {
		this.otNotes = otNotes;
	}

	public String getAppointmentText() {
		return appointmentText;
	}

	public void setAppointmentText(String appointmentText) {
		this.appointmentText = appointmentText;
	}

	public String getAsistantdoclist() {
		return asistantdoclist;
	}

	public void setAsistantdoclist(String asistantdoclist) {
		this.asistantdoclist = asistantdoclist;
	}

	public String getAnsintime() {
		return ansintime;
	}

	public void setAnsintime(String ansintime) {
		this.ansintime = ansintime;
	}

	public ArrayList<String> getInitialList() {
		return initialList;
	}

	public void setInitialList(ArrayList<String> initialList) {
		this.initialList = initialList;
	}

	public ArrayList<Client> getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(ArrayList<Client> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}

	public ArrayList<Client> getSurgeryList() {
		return surgeryList;
	}

	public void setSurgeryList(ArrayList<Client> surgeryList) {
		this.surgeryList = surgeryList;
	}

	public ArrayList<Client> getClientOccupationList() {
		return clientOccupationList;
	}

	public void setClientOccupationList(ArrayList<Client> clientOccupationList) {
		this.clientOccupationList = clientOccupationList;
	}

	public ArrayList<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<String> countryList) {
		this.countryList = countryList;
	}

	public ArrayList<Master> getCitylist() {
		return citylist;
	}

	public void setCitylist(ArrayList<Master> citylist) {
		this.citylist = citylist;
	}

	public ArrayList<Master> getStatelist() {
		return statelist;
	}

	public void setStatelist(ArrayList<Master> statelist) {
		this.statelist = statelist;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOtstatus() {
		return otstatus;
	}

	public void setOtstatus(String otstatus) {
		this.otstatus = otstatus;
	}

	public String getSelectedid() {
		return selectedid;
	}

	public void setSelectedid(String selectedid) {
		this.selectedid = selectedid;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getStddate() {
		return stddate;
	}

	public void setStddate(String stddate) {
		this.stddate = stddate;
	}

	public ArrayList<Master> getOperativeList() {
		return operativeList;
	}

	public void setOperativeList(ArrayList<Master> operativeList) {
		this.operativeList = operativeList;
	}

	public ArrayList<String> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(ArrayList<String> departmentList) {
		this.departmentList = departmentList;
	}

	public boolean isIssystemicreview() {
		return issystemicreview;
	}

	public void setIssystemicreview(boolean issystemicreview) {
		this.issystemicreview = issystemicreview;
	}

	public boolean isHistory() {
		return history;
	}

	public void setHistory(boolean history) {
		this.history = history;
	}

	public ArrayList<String> getAllConsultantList() {
		return allConsultantList;
	}

	public void setAllConsultantList(ArrayList<String> allConsultantList) {
		this.allConsultantList = allConsultantList;
	}

	public String getAlcohal() {
		return alcohal;
	}

	public void setAlcohal(String alcohal) {
		this.alcohal = alcohal;
	}

	public String getDrugs() {
		return drugs;
	}

	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}

	public String getOther_medication() {
		return other_medication;
	}

	public void setOther_medication(String other_medication) {
		this.other_medication = other_medication;
	}

	public String getTobaco() {
		return tobaco;
	}

	public void setTobaco(String tobaco) {
		this.tobaco = tobaco;
	}

	public String getTobaconotes() {
		return tobaconotes;
	}

	public void setTobaconotes(String tobaconotes) {
		this.tobaconotes = tobaconotes;
	}

	public String getAge_menarche() {
		return age_menarche;
	}

	public void setAge_menarche(String age_menarche) {
		this.age_menarche = age_menarche;
	}

	public String getLmp() {
		return lmp;
	}

	public void setLmp(String lmp) {
		this.lmp = lmp;
	}

	public String getLlmp() {
		return llmp;
	}

	public void setLlmp(String llmp) {
		this.llmp = llmp;
	}

	public String getPmc() {
		return pmc;
	}

	public void setPmc(String pmc) {
		this.pmc = pmc;
	}

	public String getCycle_length() {
		return cycle_length;
	}

	public void setCycle_length(String cycle_length) {
		this.cycle_length = cycle_length;
	}

	public String getDuration_flow() {
		return duration_flow;
	}

	public void setDuration_flow(String duration_flow) {
		this.duration_flow = duration_flow;
	}

	public String getAmount_flow() {
		return amount_flow;
	}

	public void setAmount_flow(String amount_flow) {
		this.amount_flow = amount_flow;
	}

	public String getDysmenorrhea() {
		return dysmenorrhea;
	}

	public void setDysmenorrhea(String dysmenorrhea) {
		this.dysmenorrhea = dysmenorrhea;
	}

	public String getHopassing_clots() {
		return hopassing_clots;
	}

	public void setHopassing_clots(String hopassing_clots) {
		this.hopassing_clots = hopassing_clots;
	}

	public String getWhite_disc_itching() {
		return white_disc_itching;
	}

	public void setWhite_disc_itching(String white_disc_itching) {
		this.white_disc_itching = white_disc_itching;
	}

	public String getIntercourse_freq() {
		return intercourse_freq;
	}

	public void setIntercourse_freq(String intercourse_freq) {
		this.intercourse_freq = intercourse_freq;
	}

	public String getGalactorrea() {
		return galactorrea;
	}

	public void setGalactorrea(String galactorrea) {
		this.galactorrea = galactorrea;
	}

	public String getHo_contraception() {
		return ho_contraception;
	}

	public void setHo_contraception(String ho_contraception) {
		this.ho_contraception = ho_contraception;
	}

	public String getRubella_vaccine() {
		return rubella_vaccine;
	}

	public void setRubella_vaccine(String rubella_vaccine) {
		this.rubella_vaccine = rubella_vaccine;
	}

	public String getNulligravida() {
		return nulligravida;
	}

	public void setNulligravida(String nulligravida) {
		this.nulligravida = nulligravida;
	}

	public String getCurrent_pregnent() {
		return current_pregnent;
	}

	public void setCurrent_pregnent(String current_pregnent) {
		this.current_pregnent = current_pregnent;
	}

	public String getIud() {
		return iud;
	}

	public void setIud(String iud) {
		this.iud = iud;
	}

	public String getLive_boys() {
		return live_boys;
	}

	public void setLive_boys(String live_boys) {
		this.live_boys = live_boys;
	}

	public String getLive_girls() {
		return live_girls;
	}

	public void setLive_girls(String live_girls) {
		this.live_girls = live_girls;
	}

	public String getStillbirths() {
		return stillbirths;
	}

	public void setStillbirths(String stillbirths) {
		this.stillbirths = stillbirths;
	}

	public String getAbortions() {
		return abortions;
	}

	public void setAbortions(String abortions) {
		this.abortions = abortions;
	}

	public String getDeath_children() {
		return death_children;
	}

	public void setDeath_children(String death_children) {
		this.death_children = death_children;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType_delivery() {
		return type_delivery;
	}

	public void setType_delivery(String type_delivery) {
		this.type_delivery = type_delivery;
	}

	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
	}

	public String getCoamplications() {
		return coamplications;
	}

	public void setCoamplications(String coamplications) {
		this.coamplications = coamplications;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Collection<Bed> getObslist() {
		return obslist;
	}

	public void setObslist(Collection<Bed> obslist) {
		this.obslist = obslist;
	}

	public String getSmoking() {
		return smoking;
	}

	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}

	public String getDyspareunia() {
		return dyspareunia;
	}

	public void setDyspareunia(String dyspareunia) {
		this.dyspareunia = dyspareunia;
	}

	public String getMenopause() {
		return menopause;
	}

	public void setMenopause(String menopause) {
		this.menopause = menopause;
	}

	public ArrayList<Bed> getGynicobsList() {
		return gynicobsList;
	}

	public void setGynicobsList(ArrayList<Bed> gynicobsList) {
		this.gynicobsList = gynicobsList;
	}

	public String getInvestigation() {
		return investigation;
	}

	public void setInvestigation(String investigation) {
		this.investigation = investigation;
	}

	public String getTreatmentgiven() {
		return treatmentgiven;
	}

	public void setTreatmentgiven(String treatmentgiven) {
		this.treatmentgiven = treatmentgiven;
	}

	public String getFindondischarge() {
		return findondischarge;
	}

	public void setFindondischarge(String findondischarge) {
		this.findondischarge = findondischarge;
	}

	public boolean isObstretic_history() {
		return obstretic_history;
	}

	public void setObstretic_history(boolean obstretic_history) {
		this.obstretic_history = obstretic_history;
	}

	public boolean isMenstrual_history() {
		return menstrual_history;
	}

	public void setMenstrual_history(boolean menstrual_history) {
		this.menstrual_history = menstrual_history;
	}

	public boolean isSubstance_history() {
		return substance_history;
	}

	public void setSubstance_history(boolean substance_history) {
		this.substance_history = substance_history;
	}

	public String getParity_abortion_notes() {
		return parity_abortion_notes;
	}

	public void setParity_abortion_notes(String parity_abortion_notes) {
		this.parity_abortion_notes = parity_abortion_notes;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public ArrayList<Master> getInvestigationList() {
		return investigationList;
	}

	public void setInvestigationList(ArrayList<Master> investigationList) {
		this.investigationList = investigationList;
	}

	public ArrayList<Master> getFinding_on_dischargeList() {
		return finding_on_dischargeList;
	}

	public void setFinding_on_dischargeList(
			ArrayList<Master> finding_on_dischargeList) {
		this.finding_on_dischargeList = finding_on_dischargeList;
	}

	public ArrayList<Master> getProcedureList() {
		return procedureList;
	}

	public void setProcedureList(ArrayList<Master> procedureList) {
		this.procedureList = procedureList;
	}

	public ArrayList<UserProfile> getStaffList() {
		return staffList;
	}

	public void setStaffList(ArrayList<UserProfile> staffList) {
		this.staffList = staffList;
	}

	public String getPrintedBy() {
		return printedBy;
	}

	public void setPrintedBy(String printedBy) {
		this.printedBy = printedBy;
	}

	public ArrayList<Master> getOtherTemplateList() {
		return otherTemplateList;
	}

	public void setOtherTemplateList(ArrayList<Master> otherTemplateList) {
		this.otherTemplateList = otherTemplateList;
	}

	public String getSubstancehistory() {
		return substancehistory;
	}

	public void setSubstancehistory(String substancehistory) {
		this.substancehistory = substancehistory;
	}

	public String getMenstraulhistory() {
		return menstraulhistory;
	}

	public void setMenstraulhistory(String menstraulhistory) {
		this.menstraulhistory = menstraulhistory;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getRefresh() {
		return refresh;
	}

	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}

	public ArrayList<Master> getVitalMasterList() {
		return vitalMasterList;
	}

	public void setVitalMasterList(ArrayList<Master> vitalMasterList) {
		this.vitalMasterList = vitalMasterList;
	}

	public ArrayList<String> getTimeList() {
		return timeList;
	}

	public void setTimeList(ArrayList<String> timeList) {
		this.timeList = timeList;
	}

	public ArrayList<Priscription> getListMedicineLog() {
		return listMedicineLog;
	}

	public void setListMedicineLog(ArrayList<Priscription> listMedicineLog) {
		this.listMedicineLog = listMedicineLog;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public ArrayList<Master> getListNursingLog() {
		return listNursingLog;
	}

	public void setListNursingLog(ArrayList<Master> listNursingLog) {
		this.listNursingLog = listNursingLog;
	}

	public ArrayList<Bloodbank> getBloodRequestedList() {
		return bloodRequestedList;
	}

	public void setBloodRequestedList(ArrayList<Bloodbank> bloodRequestedList) {
		this.bloodRequestedList = bloodRequestedList;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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

	public String getDisbedid() {
		return disbedid;
	}

	public void setDisbedid(String disbedid) {
		this.disbedid = disbedid;
	}

	public ArrayList<Master> getVitalMasterIOList() {
		return vitalMasterIOList;
	}

	public void setVitalMasterIOList(ArrayList<Master> vitalMasterIOList) {
		this.vitalMasterIOList = vitalMasterIOList;
	}

	public ArrayList<Master> getVitalMasterIVList() {
		return vitalMasterIVList;
	}

	public void setVitalMasterIVList(ArrayList<Master> vitalMasterIVList) {
		this.vitalMasterIVList = vitalMasterIVList;
	}

	public ArrayList<Master> getVitalMasterEquipmentList() {
		return vitalMasterEquipmentList;
	}

	public void setVitalMasterEquipmentList(ArrayList<Master> vitalMasterEquipmentList) {
		this.vitalMasterEquipmentList = vitalMasterEquipmentList;
	}

	public ArrayList<String> getIotimeList() {
		return iotimeList;
	}

	public void setIotimeList(ArrayList<String> iotimeList) {
		this.iotimeList = iotimeList;
	}

	public ArrayList<String> getIvtimeList() {
		return ivtimeList;
	}

	public void setIvtimeList(ArrayList<String> ivtimeList) {
		this.ivtimeList = ivtimeList;
	}

	public ArrayList<String> getEqtimeList() {
		return eqtimeList;
	}

	public void setEqtimeList(ArrayList<String> eqtimeList) {
		this.eqtimeList = eqtimeList;
	}

	public String getSurgicalnotes() {
		return surgicalnotes;
	}

	public void setSurgicalnotes(String surgicalnotes) {
		this.surgicalnotes = surgicalnotes;
	}

	public String getHb2() {
		return hb2;
	}

	public void setHb2(String hb2) {
		this.hb2 = hb2;
	}

	public String getFbs2() {
		return fbs2;
	}

	public void setFbs2(String fbs2) {
		this.fbs2 = fbs2;
	}

	public String getDpbs2() {
		return dpbs2;
	}

	public void setDpbs2(String dpbs2) {
		this.dpbs2 = dpbs2;
	}

	public String getUrm2() {
		return urm2;
	}

	public void setUrm2(String urm2) {
		this.urm2 = urm2;
	}

	public String getTsh2() {
		return tsh2;
	}

	public void setTsh2(String tsh2) {
		this.tsh2 = tsh2;
	}

	public String getIct2() {
		return ict2;
	}

	public void setIct2(String ict2) {
		this.ict2 = ict2;
	}

	public String getGtt2() {
		return gtt2;
	}

	public void setGtt2(String gtt2) {
		this.gtt2 = gtt2;
	}

	public String getHb3() {
		return hb3;
	}

	public void setHb3(String hb3) {
		this.hb3 = hb3;
	}

	public String getFbs3() {
		return fbs3;
	}

	public void setFbs3(String fbs3) {
		this.fbs3 = fbs3;
	}

	public String getDpbs3() {
		return dpbs3;
	}

	public void setDpbs3(String dpbs3) {
		this.dpbs3 = dpbs3;
	}

	public String getUrm3() {
		return urm3;
	}

	public void setUrm3(String urm3) {
		this.urm3 = urm3;
	}

	public String getTsh3() {
		return tsh3;
	}

	public void setTsh3(String tsh3) {
		this.tsh3 = tsh3;
	}

	public String getIct3() {
		return ict3;
	}

	public void setIct3(String ict3) {
		this.ict3 = ict3;
	}

	public String getGtt3() {
		return gtt3;
	}

	public void setGtt3(String gtt3) {
		this.gtt3 = gtt3;
	}

	public String getSurgical_ho() {
		return surgical_ho;
	}

	public void setSurgical_ho(String surgical_ho) {
		this.surgical_ho = surgical_ho;
	}

	public String getFbs1() {
		return fbs1;
	}

	public void setFbs1(String fbs1) {
		this.fbs1 = fbs1;
	}

	public String getFbs4() {
		return fbs4;
	}

	public void setFbs4(String fbs4) {
		this.fbs4 = fbs4;
	}

	public String getDpbs1() {
		return dpbs1;
	}

	public void setDpbs1(String dpbs1) {
		this.dpbs1 = dpbs1;
	}

	public String getDpbs4() {
		return dpbs4;
	}

	public void setDpbs4(String dpbs4) {
		this.dpbs4 = dpbs4;
	}

	public String getUrm1() {
		return urm1;
	}

	public void setUrm1(String urm1) {
		this.urm1 = urm1;
	}

	public String getUrm4() {
		return urm4;
	}

	public void setUrm4(String urm4) {
		this.urm4 = urm4;
	}

	public String getTsh1() {
		return tsh1;
	}

	public void setTsh1(String tsh1) {
		this.tsh1 = tsh1;
	}

	public String getTsh4() {
		return tsh4;
	}

	public void setTsh4(String tsh4) {
		this.tsh4 = tsh4;
	}

	public String getIct1() {
		return ict1;
	}

	public void setIct1(String ict1) {
		this.ict1 = ict1;
	}

	public String getIct4() {
		return ict4;
	}

	public void setIct4(String ict4) {
		this.ict4 = ict4;
	}

	public String getGtt1() {
		return gtt1;
	}

	public void setGtt1(String gtt1) {
		this.gtt1 = gtt1;
	}

	public String getGtt4() {
		return gtt4;
	}

	public void setGtt4(String gtt4) {
		this.gtt4 = gtt4;
	}

	public String getHb1() {
		return hb1;
	}

	public void setHb1(String hb1) {
		this.hb1 = hb1;
	}

	public String getHb4() {
		return hb4;
	}

	public void setHb4(String hb4) {
		this.hb4 = hb4;
	}

	public String getDate4() {
		return date4;
	}

	public void setDate4(String date4) {
		this.date4 = date4;
	}

	public String getVisit_reason_ids() {
		return visit_reason_ids;
	}

	public void setVisit_reason_ids(String visit_reason_ids) {
		this.visit_reason_ids = visit_reason_ids;
	}

	public String getIvf_date() {
		return ivf_date;
	}

	public void setIvf_date(String ivf_date) {
		this.ivf_date = ivf_date;
	}

	public String getDown_regulation() {
		return down_regulation;
	}

	public void setDown_regulation(String down_regulation) {
		this.down_regulation = down_regulation;
	}

	public String getOvarian_stimulation() {
		return ovarian_stimulation;
	}

	public void setOvarian_stimulation(String ovarian_stimulation) {
		this.ovarian_stimulation = ovarian_stimulation;
	}

	public String getOs_dosage() {
		return os_dosage;
	}

	public void setOs_dosage(String os_dosage) {
		this.os_dosage = os_dosage;
	}

	public String getSperm_quality() {
		return sperm_quality;
	}

	public void setSperm_quality(String sperm_quality) {
		this.sperm_quality = sperm_quality;
	}

	public String getEt_day() {
		return et_day;
	}

	public void setEt_day(String et_day) {
		this.et_day = et_day;
	}

	public String getOocytes_obtained() {
		return oocytes_obtained;
	}

	public void setOocytes_obtained(String oocytes_obtained) {
		this.oocytes_obtained = oocytes_obtained;
	}

	public String getOocytes_quality() {
		return oocytes_quality;
	}

	public void setOocytes_quality(String oocytes_quality) {
		this.oocytes_quality = oocytes_quality;
	}

	public String getEmbroyos_grade() {
		return embroyos_grade;
	}

	public void setEmbroyos_grade(String embroyos_grade) {
		this.embroyos_grade = embroyos_grade;
	}

	public String getEmbroyos_transfered() {
		return embroyos_transfered;
	}

	public void setEmbroyos_transfered(String embroyos_transfered) {
		this.embroyos_transfered = embroyos_transfered;
	}

	public String getEmbroyos_description() {
		return embroyos_description;
	}

	public void setEmbroyos_description(String embroyos_description) {
		this.embroyos_description = embroyos_description;
	}

	public String getFreezing() {
		return freezing;
	}

	public void setFreezing(String freezing) {
		this.freezing = freezing;
	}

	public String getTransfer_process() {
		return transfer_process;
	}

	public void setTransfer_process(String transfer_process) {
		this.transfer_process = transfer_process;
	}

	public String getBetahcgcm() {
		return betahcgcm;
	}

	public void setBetahcgcm(String betahcgcm) {
		this.betahcgcm = betahcgcm;
	}

	public String getIvf_remark() {
		return ivf_remark;
	}

	public void setIvf_remark(String ivf_remark) {
		this.ivf_remark = ivf_remark;
	}

	public String getDo_family_history() {
		return do_family_history;
	}

	public void setDo_family_history(String do_family_history) {
		this.do_family_history = do_family_history;
	}

	public String getHo_fertility_family() {
		return ho_fertility_family;
	}

	public void setHo_fertility_family(String ho_fertility_family) {
		this.ho_fertility_family = ho_fertility_family;
	}

	public String getHo_genetic_family() {
		return ho_genetic_family;
	}

	public void setHo_genetic_family(String ho_genetic_family) {
		this.ho_genetic_family = ho_genetic_family;
	}

	public String getHo_premature_family() {
		return ho_premature_family;
	}

	public void setHo_premature_family(String ho_premature_family) {
		this.ho_premature_family = ho_premature_family;
	}

	public String getAge_of_menarche() {
		return age_of_menarche;
	}

	public void setAge_of_menarche(String age_of_menarche) {
		this.age_of_menarche = age_of_menarche;
	}

	public String getAge_of_menarche_notes() {
		return age_of_menarche_notes;
	}

	public void setAge_of_menarche_notes(String age_of_menarche_notes) {
		this.age_of_menarche_notes = age_of_menarche_notes;
	}

	public String getDysmenorrhoe() {
		return dysmenorrhoe;
	}

	public void setDysmenorrhoe(String dysmenorrhoe) {
		this.dysmenorrhoe = dysmenorrhoe;
	}

	public String getDysmenorrhoe_notes() {
		return dysmenorrhoe_notes;
	}

	public void setDysmenorrhoe_notes(String dysmenorrhoe_notes) {
		this.dysmenorrhoe_notes = dysmenorrhoe_notes;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getFlow_notes() {
		return flow_notes;
	}

	public void setFlow_notes(String flow_notes) {
		this.flow_notes = flow_notes;
	}

	public String getSleep_disruption_bleeding() {
		return sleep_disruption_bleeding;
	}

	public void setSleep_disruption_bleeding(String sleep_disruption_bleeding) {
		this.sleep_disruption_bleeding = sleep_disruption_bleeding;
	}

	public String getSleep_disruption_bleeding_notes() {
	 	return sleep_disruption_bleeding_notes;
	}

	public void setSleep_disruption_bleeding_notes(String sleep_disruption_bleeding_notes) {
		this.sleep_disruption_bleeding_notes = sleep_disruption_bleeding_notes;
	}

	public String getBlachouts() {
		return blachouts;
	}

	public void setBlachouts(String blachouts) {
		this.blachouts = blachouts;
	}

	public String getBlachouts_notes() {
		return blachouts_notes;
	}

	public void setBlachouts_notes(String blachouts_notes) {
		this.blachouts_notes = blachouts_notes;
	}

	public String getPt_history_notes() {
		return pt_history_notes;
	}

	public void setPt_history_notes(String pt_history_notes) {
		this.pt_history_notes = pt_history_notes;
	}

	public String getFamily_history_notes() {
		return family_history_notes;
	}

	public void setFamily_history_notes(String family_history_notes) {
		this.family_history_notes = family_history_notes;
	}

	public String getFamily_history() {
		return family_history;
	}

	public void setFamily_history(String family_history) {
		this.family_history = family_history;
	}

	public String getAll_investigation_16week() {
		return all_investigation_16week;
	}

	public void setAll_investigation_16week(String all_investigation_16week) {
		this.all_investigation_16week = all_investigation_16week;
	}

	public String getSikling_16week() {
		return sikling_16week;
	}

	public void setSikling_16week(String sikling_16week) {
		this.sikling_16week = sikling_16week;
	}

	public String getTriple_marker_16week() {
		return triple_marker_16week;
	}

	public void setTriple_marker_16week(String triple_marker_16week) {
		this.triple_marker_16week = triple_marker_16week;
	}

	public String getAbstinence_1visit() {
		return abstinence_1visit;
	}

	public void setAbstinence_1visit(String abstinence_1visit) {
		this.abstinence_1visit = abstinence_1visit;
	}

	public String getBarrier_contra_1visit() {
		return barrier_contra_1visit;
	}

	public void setBarrier_contra_1visit(String barrier_contra_1visit) {
		this.barrier_contra_1visit = barrier_contra_1visit;
	}

	public String getBed_rest_1visit() {
		return bed_rest_1visit;
	}

	public void setBed_rest_1visit(String bed_rest_1visit) {
		this.bed_rest_1visit = bed_rest_1visit;
	}

	public String getBook_1visit() {
		return book_1visit;
	}

	public void setBook_1visit(String book_1visit) {
		this.book_1visit = book_1visit;
	}

	public String getCsv_1visit() {
		return csv_1visit;
	}

	public void setCsv_1visit(String csv_1visit) {
		this.csv_1visit = csv_1visit;
	}

	public String getDispi_test_1visit() {
		return dispi_test_1visit;
	}

	public void setDispi_test_1visit(String dispi_test_1visit) {
		this.dispi_test_1visit = dispi_test_1visit;
	}

	public String getDrug_reaction_1visit() {
		return drug_reaction_1visit;
	}

	public void setDrug_reaction_1visit(String drug_reaction_1visit) {
		this.drug_reaction_1visit = drug_reaction_1visit;
	}

	public String getHcg_1visit() {
		return hcg_1visit;
	}

	public void setHcg_1visit(String hcg_1visit) {
		this.hcg_1visit = hcg_1visit;
	}

	public String getHeparin_1visit() {
		return heparin_1visit;
	}

	public void setHeparin_1visit(String heparin_1visit) {
		this.heparin_1visit = heparin_1visit;
	}

	public String getOral_hygeine_1visit() {
		return oral_hygeine_1visit;
	}

	public void setOral_hygeine_1visit(String oral_hygeine_1visit) {
		this.oral_hygeine_1visit = oral_hygeine_1visit;
	}

	public String getOther_test_1visit() {
		return other_test_1visit;
	}

	public void setOther_test_1visit(String other_test_1visit) {
		this.other_test_1visit = other_test_1visit;
	}

	public String getPhysio_diet_1visit() {
		return physio_diet_1visit;
	}

	public void setPhysio_diet_1visit(String physio_diet_1visit) {
		this.physio_diet_1visit = physio_diet_1visit;
	}

	public String getRubelle_status_1visit() {
		return rubelle_status_1visit;
	}

	public void setRubelle_status_1visit(String rubelle_status_1visit) {
		this.rubelle_status_1visit = rubelle_status_1visit;
	}

	public String getSmart_doc_1visit() {
		return smart_doc_1visit;
	}

	public void setSmart_doc_1visit(String smart_doc_1visit) {
		this.smart_doc_1visit = smart_doc_1visit;
	}

	public String getStream_cell_1visit() {
		return stream_cell_1visit;
	}

	public void setStream_cell_1visit(String stream_cell_1visit) {
		this.stream_cell_1visit = stream_cell_1visit;
	}

	public String getVaginities_1visit() {
		return vaginities_1visit;
	}

	public void setVaginities_1visit(String vaginities_1visit) {
		this.vaginities_1visit = vaginities_1visit;
	}

	public String getAnimally_scan_20week() {
		return animally_scan_20week;
	}

	public void setAnimally_scan_20week(String animally_scan_20week) {
		this.animally_scan_20week = animally_scan_20week;
	}

	public String getFetal_eco_20week() {
		return fetal_eco_20week;
	}

	public void setFetal_eco_20week(String fetal_eco_20week) {
		this.fetal_eco_20week = fetal_eco_20week;
	}

	public String getAnti_d_24week() {
		return anti_d_24week;
	}

	public void setAnti_d_24week(String anti_d_24week) {
		this.anti_d_24week = anti_d_24week;
	}

	public String getDipsi_24week() {
		return dipsi_24week;
	}

	public void setDipsi_24week(String dipsi_24week) {
		this.dipsi_24week = dipsi_24week;
	}

	public String getItc_24week() {
		return itc_24week;
	}

	public void setItc_24week(String itc_24week) {
		this.itc_24week = itc_24week;
	}

	public String getInvestigation_sos_30week() {
		return investigation_sos_30week;
	}

	public void setInvestigation_sos_30week(String investigation_sos_30week) {
		this.investigation_sos_30week = investigation_sos_30week;
	}

	public String getSteroids_30week() {
		return steroids_30week;
	}

	public void setSteroids_30week(String steroids_30week) {
		this.steroids_30week = steroids_30week;
	}

	public String getVaginities_treatment_30week() {
		return vaginities_treatment_30week;
	}

	public void setVaginities_treatment_30week(String vaginities_treatment_30week) {
		this.vaginities_treatment_30week = vaginities_treatment_30week;
	}

	public String getBreast_preparation_34week() {
		return breast_preparation_34week;
	}

	public void setBreast_preparation_34week(String breast_preparation_34week) {
		this.breast_preparation_34week = breast_preparation_34week;
	}

	public String getColor_doppler_34week() {
		return color_doppler_34week;
	}

	public void setColor_doppler_34week(String color_doppler_34week) {
		this.color_doppler_34week = color_doppler_34week;
	}

	public String getLabour_counselling_34week() {
		return labour_counselling_34week;
	}

	public void setLabour_counselling_34week(String labour_counselling_34week) {
		this.labour_counselling_34week = labour_counselling_34week;
	}

	public String getNst_34week() {
		return nst_34week;
	}

	public void setNst_34week(String nst_34week) {
		this.nst_34week = nst_34week;
	}

	public String getVaginities_treatment_34week() {
		return vaginities_treatment_34week;
	}

	public void setVaginities_treatment_34week(String vaginities_treatment_34week) {
		this.vaginities_treatment_34week = vaginities_treatment_34week;
	}

	public ArrayList<Bed> getAllVisitReasonList() {
		return allVisitReasonList;
	}

	public void setAllVisitReasonList(ArrayList<Bed> allVisitReasonList) {
		this.allVisitReasonList = allVisitReasonList;
	}

	public ArrayList<Master> getPriscUnitList() {
		return priscUnitList;
	}

	public void setPriscUnitList(ArrayList<Master> priscUnitList) {
		this.priscUnitList = priscUnitList;
	}

	public String getBeats_min() {
		return beats_min;
	}

	public void setBeats_min(String beats_min) {
		this.beats_min = beats_min;
	}

	public String getFormtype() {
		return formtype;
	}

	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}

	public ArrayList<String> getReasonVisitList() {
		return reasonVisitList;
	}

	public void setReasonVisitList(ArrayList<String> reasonVisitList) {
		this.reasonVisitList = reasonVisitList;
	}

	public String getPmp() {
		return pmp;
	}

	public void setPmp(String pmp) {
		this.pmp = pmp;
	}

	public String getDiagnosisgyn() {
		return diagnosisgyn;
	}

	public void setDiagnosisgyn(String diagnosisgyn) {
		this.diagnosisgyn = diagnosisgyn;
	}

	public String getLe_vulva() {
		return le_vulva;
	}

	public void setLe_vulva(String le_vulva) {
		this.le_vulva = le_vulva;
	}

	public String getLe_vagina() {
		return le_vagina;
	}

	public void setLe_vagina(String le_vagina) {
		this.le_vagina = le_vagina;
	}

	public String getPa_gynic() {
		return pa_gynic;
	}

	public void setPa_gynic(String pa_gynic) {
		this.pa_gynic = pa_gynic;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getFinaldiagnosis() {
		return finaldiagnosis;
	}

	public void setFinaldiagnosis(String finaldiagnosis) {
		this.finaldiagnosis = finaldiagnosis;
	}

	public String getPriscription() {
		return priscription;
	}

	public void setPriscription(String priscription) {
		this.priscription = priscription;
	}

	public String getPv_uterus() {
		return pv_uterus;
	}

	public void setPv_uterus(String pv_uterus) {
		this.pv_uterus = pv_uterus;
	}

	public String getPv_uterus_size() {
		return pv_uterus_size;
	}

	public void setPv_uterus_size(String pv_uterus_size) {
		this.pv_uterus_size = pv_uterus_size;
	}

	public String getPv_bl_fomices() {
		return pv_bl_fomices;
	}

	public void setPv_bl_fomices(String pv_bl_fomices) {
		this.pv_bl_fomices = pv_bl_fomices;
	}

	public String getPv_mobility() {
		return pv_mobility;
	}

	public void setPv_mobility(String pv_mobility) {
		this.pv_mobility = pv_mobility;
	}

	public ArrayList<Diagnosis> getDiagnosisListGynic() {
		return diagnosisListGynic;
	}

	public void setDiagnosisListGynic(ArrayList<Diagnosis> diagnosisListGynic) {
		this.diagnosisListGynic = diagnosisListGynic;
	}


	private String birthhist;
	 private String diethist;
	 private String emmunizationhist;
	 private String developmenthist;

	public String getBirthhist() {
		return birthhist;
	}

	public void setBirthhist(String birthhist) {
		this.birthhist = birthhist;
	}

	public String getDiethist() {
		return diethist;
	}

	public void setDiethist(String diethist) {
		this.diethist = diethist;
	}

	public String getEmmunizationhist() {
		return emmunizationhist;
	}

	public void setEmmunizationhist(String emmunizationhist) {
		this.emmunizationhist = emmunizationhist;
	}

	public String getDevelopmenthist() {
		return developmenthist;
	}

	public void setDevelopmenthist(String developmenthist) {
		this.developmenthist = developmenthist;
	}
public ArrayList<Ipd> getNursingcarelist() {
		return nursingcarelist;
	}

	public void setNursingcarelist(ArrayList<Ipd> nursingcarelist) {
		this.nursingcarelist = nursingcarelist;
	}
public ArrayList<Priscription> getTreatmentlist1() {
		return treatmentlist1;
	}

	public void setTreatmentlist1(ArrayList<Priscription> treatmentlist1) {
		this.treatmentlist1 = treatmentlist1;
	}
public ArrayList<Investigation> getInvestlist() {
		return investlist;
	}

	public void setInvestlist(ArrayList<Investigation> investlist) {
		this.investlist = investlist;
	}
public String getInvstgiven() {
		return invstgiven;
	}

	public void setInvstgiven(String invstgiven) {
		this.invstgiven = invstgiven;
	}
public String getPriscid() {
		return priscid;
	}

	public void setPriscid(String priscid) {
		this.priscid = priscid;
	}
public boolean isPaediatrichist() {
		return paediatrichist;
	}

	public void setPaediatrichist(boolean paediatrichist) {
		this.paediatrichist = paediatrichist;
	}
private ArrayList<Ipd> nursingcarelist;
private ArrayList<Priscription> treatmentlist1;
private ArrayList<Investigation> investlist;
public String getHeadcircumference() {
	return headcircumference;
}

public void setHeadcircumference(String headcircumference) {
	this.headcircumference = headcircumference;
}

public String getMidarmcircumference() {
	return midarmcircumference;
}

public void setMidarmcircumference(String midarmcircumference) {
	this.midarmcircumference = midarmcircumference;
}

public String getLength() {
	return length;
}

public void setLength(String length) {
	this.length = length;
}

public String getWtaddmission() {
	return wtaddmission;
}

public void setWtaddmission(String wtaddmission) {
	this.wtaddmission = wtaddmission;
}

public String getWtdischarge() {
	return wtdischarge;
}

public void setWtdischarge(String wtdischarge) {
	this.wtdischarge = wtdischarge;
}
public String getAgeonadmn() {
	return ageonadmn;
}

public void setAgeonadmn(String ageonadmn) {
	this.ageonadmn = ageonadmn;
}
public String getAge() {
	return age;
}

public void setAge(String age) {
	this.age = age;
}
private String priscid;
private String invstgiven;
private boolean paediatrichist;
private String headcircumference,midarmcircumference,length,wtaddmission,wtdischarge;
private boolean strengthflag=false;
private boolean quantityflag= false;
private boolean remarkflag= false;

public boolean isStrengthflag() {
	return strengthflag;
}

public void setStrengthflag(boolean strengthflag) {
	this.strengthflag = strengthflag;
}

public boolean isQuantityflag() {
	return quantityflag;
}

public void setQuantityflag(boolean quantityflag) {
	this.quantityflag = quantityflag;
}

public boolean isRemarkflag() {
	return remarkflag;
}

public void setRemarkflag(boolean remarkflag) {
	this.remarkflag = remarkflag;
}

public String getLogipdid() {
	return logipdid;
}

public void setLogipdid(String logipdid) {
	this.logipdid = logipdid;
}
public ArrayList<Master> getTreatmentgiventemplatelist() {
	return treatmentgiventemplatelist;
}

public void setTreatmentgiventemplatelist(ArrayList<Master> treatmentgiventemplatelist) {
	this.treatmentgiventemplatelist = treatmentgiventemplatelist;
}
public ArrayList<InvstTemplate> getInvestigationtemplatelist() {
	return investigationtemplatelist;
}
private String newadmndate;
public void setInvestigationtemplatelist(ArrayList<InvstTemplate> investigationtemplatelist) {
	this.investigationtemplatelist = investigationtemplatelist;
}
public String getNewadmndate() {
	return newadmndate;
}

public void setNewadmndate(String newadmndate) {
	this.newadmndate = newadmndate;
}
public String getNewipdabbr() {
	return newipdabbr;
}

public void setNewipdabbr(String newipdabbr) {
	this.newipdabbr = newipdabbr;
}
public Collection<PackageMaster> getPkglist() {
	return pkglist;
}

public void setPkglist(Collection<PackageMaster> pkglist) {
	this.pkglist = pkglist;
}
public String getKunal_final_diagnosis_text() {
	return kunal_final_diagnosis_text;
}

public void setKunal_final_diagnosis_text(String kunal_final_diagnosis_text) {
	this.kunal_final_diagnosis_text = kunal_final_diagnosis_text;
}
public String getKunal_manual_medicine_text() {
	return kunal_manual_medicine_text;
}

public void setKunal_manual_medicine_text(String kunal_manual_medicine_text) {
	this.kunal_manual_medicine_text = kunal_manual_medicine_text;
}
public String getDischargehead() {
	return dischargehead;
}

public void setDischargehead(String dischargehead) {
	this.dischargehead = dischargehead;
}
public String getAddr() {
	return addr;
}

public void setAddr(String addr) {
	this.addr = addr;
}
public String getWeightsts() {
	return weightsts;
}

public void setWeightsts(String weightsts) {
	this.weightsts = weightsts;
}
public String getRatewardid() {
	return ratewardid;
}

public void setRatewardid(String ratewardid) {
	this.ratewardid = ratewardid;
}
public String getJsonipdid() {
	return jsonipdid;
}

public void setJsonipdid(String jsonipdid) {
	this.jsonipdid = jsonipdid;
}
private ArrayList<Master> treatmentgiventemplatelist;
private ArrayList<InvstTemplate> investigationtemplatelist;
private String newipdabbr;
private Collection<PackageMaster> pkglist;
private String kunal_final_diagnosis_text;
private String kunal_manual_medicine_text;
private String dischargehead;
private String addr;
private String weightsts;
private String ratewardid;
private String jsonipdid;
private int admn_summdiv,histrydiv,surgical_notesdiv,hospital_coursediv,treatmnt_givendiv,invst_div,otherdiv,emergency_detdiv;

public int getAdmn_summdiv() {
	return admn_summdiv;
}

public void setAdmn_summdiv(int admn_summdiv) {
	this.admn_summdiv = admn_summdiv;
}

public int getHistrydiv() {
	return histrydiv;
}

public void setHistrydiv(int histrydiv) {
	this.histrydiv = histrydiv;
}

public int getSurgical_notesdiv() {
	return surgical_notesdiv;
}

public void setSurgical_notesdiv(int surgical_notesdiv) {
	this.surgical_notesdiv = surgical_notesdiv;
}

public int getHospital_coursediv() {
	return hospital_coursediv;
}

public void setHospital_coursediv(int hospital_coursediv) {
	this.hospital_coursediv = hospital_coursediv;
}

public int getTreatmnt_givendiv() {
	return treatmnt_givendiv;
}

public void setTreatmnt_givendiv(int treatmnt_givendiv) {
	this.treatmnt_givendiv = treatmnt_givendiv;
}

public int getInvst_div() {
	return invst_div;
}

public void setInvst_div(int invst_div) {
	this.invst_div = invst_div;
}

public int getOtherdiv() {
	return otherdiv;
}

public void setOtherdiv(int otherdiv) {
	this.otherdiv = otherdiv;
}

public int getEmergency_detdiv() {
	return emergency_detdiv;
}

public void setEmergency_detdiv(int emergency_detdiv) {
	this.emergency_detdiv = emergency_detdiv;
}

public String getActiontype() {
	return actiontype;
}

public void setActiontype(String actiontype) {
	this.actiontype = actiontype;
}
private String actiontype; 
private String title,fname,lname,gender,mob;

public String getTitle() {
	return title;
	
}

public void setTitle(String title) {
	this.title = title;
}

public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}

public String getLname() {
	return lname;
}

public void setLname(String lname) {
	this.lname = lname;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getMob() {
	return mob;
}

public void setMob(String mob) {
	this.mob = mob;
}

public boolean isDaycare() {
	return daycare;
}

public void setDaycare(boolean daycare) {
	this.daycare = daycare;
}
public String getLocationshift() {
	return locationshift;
}

public void setLocationshift(String locationshift) {
	this.locationshift = locationshift;
}
public boolean isNicuaccess() {
	return nicuaccess;
}

public void setNicuaccess(boolean nicuaccess) {
	this.nicuaccess = nicuaccess;
}
public String getGstureage() {
	return gstureage;
}

public void setGstureage(String gstureage) {
	this.gstureage = gstureage;
}
private boolean daycare;
private String locationshift;
private boolean nicuaccess;
private String gstureage;
private String mothername, fathername, birthplace;
private String wtonbirth;
public String getMothername() {
	return mothername;
}

public void setMothername(String mothername) {
	this.mothername = mothername;
}

public String getFathername() {
	return fathername;
}

public void setFathername(String fathername) {
	this.fathername = fathername;
}

public String getBirthplace() {
	return birthplace;
}

public void setBirthplace(String birthplace) {
	this.birthplace = birthplace;
}

public String getWtonbirth() {
	return wtonbirth;
}

public void setWtonbirth(String wtonbirth) {
	this.wtonbirth = wtonbirth;
}



public String getMaternal_history() {
	return maternal_history;
}

public void setMaternal_history(String maternal_history) {
	this.maternal_history = maternal_history;
}
public String getPerinatal_history() {
	return perinatal_history;
}

public void setPerinatal_history(String perinatal_history) {
	this.perinatal_history = perinatal_history;
}
public String getPerinataltemp() {
	return perinataltemp;
}

public void setPerinataltemp(String perinataltemp) {
	this.perinataltemp = perinataltemp;
}
public String getMaternalhisttemp() {
	return maternalhisttemp;
}

public void setMaternalhisttemp(String maternalhisttemp) {
	this.maternalhisttemp = maternalhisttemp;
}
public ArrayList<Master> getMaternal_histry_list() {
	return maternal_histry_list;
}

public void setMaternal_histry_list(ArrayList<Master> maternal_histry_list) {
	this.maternal_histry_list = maternal_histry_list;
}
public ArrayList<Master> getPerintal_hisry_list() {
	return perintal_hisry_list;
}

public void setPerintal_hisry_list(ArrayList<Master> perintal_hisry_list) {
	this.perintal_hisry_list = perintal_hisry_list;
}
public ArrayList<Master> getQualificationList() {
	return qualificationList;
}

public void setQualificationList(ArrayList<Master> qualificationList) {
	this.qualificationList = qualificationList;
}
public boolean isLamadamaacc() {
	return lamadamaacc;
}

public void setLamadamaacc(boolean lamadamaacc) {
	this.lamadamaacc = lamadamaacc;
}
public String getLamadamareason() {
	return lamadamareason;
}

public void setLamadamareason(String lamadamareason) {
	this.lamadamareason = lamadamareason;
}
public String getDischargeEndedbyId() {
	return dischargeEndedbyId;
}

public void setDischargeEndedbyId(String dischargeEndedbyId) {
	this.dischargeEndedbyId = dischargeEndedbyId;
}
public String getDischargteLastUpdatedId() {
	return dischargteLastUpdatedId;
}

public void setDischargteLastUpdatedId(String dischargteLastUpdatedId) {
	this.dischargteLastUpdatedId = dischargteLastUpdatedId;
}
public ArrayList<Priscription> getTreatmentivendischargePriscListt() {
	return treatmentivendischargePriscListt;
}

public void setTreatmentivendischargePriscListt(ArrayList<Priscription> treatmentivendischargePriscListt) {
	this.treatmentivendischargePriscListt = treatmentivendischargePriscListt;
}
public ArrayList<UserProfile> getReleaseList() {
	return releaseList;
}

public void setReleaseList(ArrayList<UserProfile> releaseList) {
	this.releaseList = releaseList;
}
public String getSubuploadfilesContentType() {
	return subuploadfilesContentType;
}

public void setSubuploadfilesContentType(String subuploadfilesContentType) {
	this.subuploadfilesContentType = subuploadfilesContentType;
}
public String getSubuploadfilesFileName() {
	return subuploadfilesFileName;
}

public void setSubuploadfilesFileName(String subuploadfilesFileName) {
	this.subuploadfilesFileName = subuploadfilesFileName;
}
public File getSubuploadfiles() {
	return subuploadfiles;
}

public void setSubuploadfiles(File subuploadfiles) {
	this.subuploadfiles = subuploadfiles;
}
public String getWarname() {
	return warname;
}

public void setWarname(String warname) {
	this.warname = warname;
}
public ArrayList<Master> getVitalList() {
	return vitalList;
}

public void setVitalList(ArrayList<Master> vitalList) {
	this.vitalList = vitalList;
}
public String getDietary_advice() {
	return dietary_advice;
}

public void setDietary_advice(String dietary_advice) {
	this.dietary_advice = dietary_advice;
}
public Bed getNewCardFields() {
	return newCardFields;
}

public void setNewCardFields(Bed newCardFields) {
	this.newCardFields = newCardFields;
}
public ArrayList<Master> getCommonTemplateList() {
	return commonTemplateList;
}

public void setCommonTemplateList(ArrayList<Master> commonTemplateList) {
	this.commonTemplateList = commonTemplateList;
}
public String getOrigbedid() {
	return origbedid;
}

public void setOrigbedid(String origbedid) {
	this.origbedid = origbedid;
}
public Bed getTreatmentEpisodeData() {
	return treatmentEpisodeData;
}

public void setTreatmentEpisodeData(Bed treatmentEpisodeData) {
	this.treatmentEpisodeData = treatmentEpisodeData;
}
public ArrayList<Master> getMnsWardList() {
	return mnsWardList;
}

public void setMnsWardList(ArrayList<Master> mnsWardList) {
	this.mnsWardList = mnsWardList;
}
public String getPs_cervixVal() {
	return ps_cervixVal;
}

public void setPs_cervixVal(String ps_cervixVal) {
	this.ps_cervixVal = ps_cervixVal;
}
public String getPs_vagineVal() {
	return ps_vagineVal;
}

public void setPs_vagineVal(String ps_vagineVal) {
	this.ps_vagineVal = ps_vagineVal;
}
public String getPs_vaultVal() {
	return ps_vaultVal;
}

public void setPs_vaultVal(String ps_vaultVal) {
	this.ps_vaultVal = ps_vaultVal;
}
public String getPv_MVal() {
	return pv_MVal;
}

public void setPv_MVal(String pv_MVal) {
	this.pv_MVal = pv_MVal;
}
public Client getPatient() {
	return patient;
}

public void setPatient(Client patient) {
	this.patient = patient;
}
private String maternal_history,perinatal_history,perinataltemp,maternalhisttemp;

private ArrayList<Master> maternal_histry_list;
private ArrayList<Master> perintal_hisry_list;
private ArrayList<Master> qualificationList;
private boolean lamadamaacc;
private String lamadamareason;
private String dischargeEndedbyId,dischargteLastUpdatedId;
private ArrayList<UserProfile> releaseList;
private String subuploadfilesContentType;
private String subuploadfilesFileName;
private File subuploadfiles;
private String warname;
private ArrayList<Master> vitalList;
private String dietary_advice;
private Bed newCardFields,treatmentEpisodeData;
private ArrayList<Master> commonTemplateList;
private ArrayList<Master> mnsWardList;
private Client patient;
private ArrayList<Master> examination_finding_list;
private String examtempname;
private String examination;
private String respiratory_rate;
private ArrayList<Bed>ipdgendercountlist;
private ArrayList<Ipd> patienttransactionlist;
private ArrayList<Bams> mainArrayList;
private Client DecData;
private ArrayList<Master> finalobservation;
private ArrayList<Master>finalTreatment;
public String getExamination() {
	return examination;
}

public void setExamination(String examination) {
	this.examination = examination;
}

public String getExamtempname() {
	return examtempname;
}

public void setExamtempname(String examtempname) {
	this.examtempname = examtempname;
}

public ArrayList<Master> getExamination_finding_list() {
	return examination_finding_list;
}

public void setExamination_finding_list(ArrayList<Master> examination_finding_list) {
	this.examination_finding_list = examination_finding_list;
}

public ArrayList<Bams> getMainArrayList() {
	return mainArrayList;
}

public void setMainArrayList(ArrayList<Bams> mainArrayList) {
	this.mainArrayList = mainArrayList;
}

public Client getDecData() {
	return DecData;
}

public void setDecData(Client decData) {
	DecData = decData;
}

public Ipd getDiet() {
	return Diet;
}

public void setDiet(Ipd diet) {
	Diet = diet;
}
}


