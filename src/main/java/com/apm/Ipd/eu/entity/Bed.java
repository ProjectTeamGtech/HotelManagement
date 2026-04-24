package com.apm.Ipd.eu.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Dietary.eu.entity.Dietary;
import com.apm.Master.eu.entity.Master;

public class Bed {
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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

	public String getProcesurebams() {
		return procesurebams;
	}

	public void setProcesurebams(String procesurebams) {
		this.procesurebams = procesurebams;
	}

	public String getKarma() {
		return karma;
	}

	public void setKarma(String karma) {
		this.karma = karma;
	}

	public double getAdvance() {
		return advance;
	}

	public void setAdvance(double advance) {
		this.advance = advance;
	}

	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

	public int getMalecount() {
		return malecount;
	}

	public void setMalecount(int malecount) {
		this.malecount = malecount;
	}

	public int getFemalecount() {
		return femalecount;
	}

	public void setFemalecount(int femalecount) {
		this.femalecount = femalecount;
	}

	public int getGendercount() {
		return gendercount;
	}

	public void setGendercount(int gendercount) {
		this.gendercount = gendercount;
	}
	private int todayadmit;
	private String decreased ;
	private String perceives;
	private String notperceives;
	private String hrf_gdm,hrf_pih,hrf_boh,hrf_hypothyroid,hrf_hyperthyroid,hrf_prev_lscs,hrf_rh_negative,hrf_other,fundalheight_weeks,pa_soft,
	external_ballotment,effaced_text,pv_dialated,pv_dialatedcm;
	private String g,dating_usg_date,weeks_dating_scan,pog_dating_scan;
	private String usg_report,additonal_notes,diagonosis,treatment_advice,investigation_advice,pog;
	private String cephalicVal,baley_sizeVal,ps_cervixVal,ps_vagineVal,ps_vaultVal,pv_osVal,pv_MVal,days_dating_scan,hrf_otherval;
	private int newgynicid;
	private String createddate;
	private int lastappointmentid;
	private int check_indications=0;
	private int check_coamplications=0;
	private int check_institution=0;
	private String casualty;
	private String enddischargename;
	private String opdabbrivationid;
	private String oldClientId;
	private String clientAddress;
	private String updateUserId, updateDateTime;
	private int ischeck;
	private String examination;
	private int difference;
	private int totaldifference;
	private int malecount,femalecount,gendercount;
	private String seen;
    private double advance;
    private String punchkarma;
    private String opdid;
    private String opdclientid;
    private String ondiettempname;
	private String ondiet;
	private String physio;
	private String fakebackdate;
	private String karma;
	private String investigation_type;
	private Vector<Bed>invnamelist;
	private String obsvalue;
	private String investigation_name;
	private String inv_date;
	private String opdsequence;
	private String mbbsipdsequence;

	private String LSplRFI;
	private String LCylRFI;
	private String LAxisRFI;
	private String RSplRFI;
	private String RCylRFI;
	private String RAxisRFI;
    private String consNote;
	private String eyereading;
	private int wardwiseadmissioncount;
	private int wardwisedischargecount;
	private int wardwiseinhousecount;
    private int wardwiseoccupied;
    private int wardwiseavailablebed;
    private int refund;
    private String observation;
    private String refdocotor = "";;
    public String getRefdocotor() {
		return refdocotor;
	}

	public void setRefdocotor(String refdocotor) {
		this.refdocotor = refdocotor;
	}

	public int getRefund() {
		return refund;
	}

	public void setRefund(int refund) {
		this.refund = refund;
	}

	public int getWardwiseadmissioncount() {
		return wardwiseadmissioncount;
	}

	public void setWardwiseadmissioncount(int wardwiseadmissioncount) {
		this.wardwiseadmissioncount = wardwiseadmissioncount;
	}

	public int getWardwisedischargecount() {
		return wardwisedischargecount;
	}

	public void setWardwisedischargecount(int wardwisedischargecount) {
		this.wardwisedischargecount = wardwisedischargecount;
	}

	public int getWardwiseinhousecount() {
		return wardwiseinhousecount;
	}

	public void setWardwiseinhousecount(int wardwiseinhousecount) {
		this.wardwiseinhousecount = wardwiseinhousecount;
	}

	public int getWardwiseoccupied() {
		return wardwiseoccupied;
	}

	public void setWardwiseoccupied(int wardwiseoccupied) {
		this.wardwiseoccupied = wardwiseoccupied;
	}

	public int getWardwiseavailablebed() {
		return wardwiseavailablebed;
	}

	public void setWardwiseavailablebed(int wardwiseavailablebed) {
		this.wardwiseavailablebed = wardwiseavailablebed;
	}

	public String getLSplRFI() {
		return LSplRFI;
	}

	public void setLSplRFI(String lSplRFI) {
		LSplRFI = lSplRFI;
	}

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

	public String getMbbsipdsequence() {
		return mbbsipdsequence;
	}

	public void setMbbsipdsequence(String mbbsipdsequence) {
		this.mbbsipdsequence = mbbsipdsequence;
	}

	public String getOpdsequence() {
		return opdsequence;
	}

	public void setOpdsequence(String opdsequence) {
		this.opdsequence = opdsequence;
	}

	public String getInv_date() {
		return inv_date;
	}

	public void setInv_date(String inv_date) {
		this.inv_date = inv_date;
	}

	public String getInvestigation_name() {
		return investigation_name;
	}

	public void setInvestigation_name(String investigation_name) {
		this.investigation_name = investigation_name;
	}

	public String getObsvalue() {
		return obsvalue;
	}

	public void setObsvalue(String obsvalue) {
		this.obsvalue = obsvalue;
	}

	public Vector<Bed> getInvnamelist() {
		return invnamelist;
	}

	public void setInvnamelist(Vector<Bed> invnamelist) {
		this.invnamelist = invnamelist;
	}

	public String getInvestigation_type() {
		return investigation_type;
	}

	public void setInvestigation_type(String investigation_type) {
		this.investigation_type = investigation_type;
	}

	public String getFakebackdate() {
		return fakebackdate;
	}

	public void setFakebackdate(String fakebackdate) {
		this.fakebackdate = fakebackdate;
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

	public String getOpdclientid() {
		return opdclientid;
	}

	public void setOpdclientid(String opdclientid) {
		this.opdclientid = opdclientid;
	}

	public String getOpdid() {
		return opdid;
	}

	public void setOpdid(String opdid) {
		this.opdid = opdid;
	}

	public String getPunchkarma() {
		return punchkarma;
	}

	public void setPunchkarma(String punchkarma) {
		this.punchkarma = punchkarma;
	}

	public String getSeen() {
		return seen;
	}

	public void setSeen(String seen) {
		this.seen = seen;
	}

	public int getDifference() {
		return difference;
	}

	public void setDifference(int difference) {
		this.difference = difference;
	}

	public int getTotaldifference() {
		return totaldifference;
	}

	public void setTotaldifference(int totaldifference) {
		this.totaldifference = totaldifference;
	}

	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}

	public int getIscheck() {
		return ischeck;
	}

	public void setIscheck(int ischeck) {
		this.ischeck = ischeck;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getOldClientId() {
		return oldClientId;
	}

	public void setOldClientId(String oldClientId) {
		this.oldClientId = oldClientId;
	}

	public String getOpdabbrivationid() {
		return opdabbrivationid;
	}

	public void setOpdabbrivationid(String opdabbrivationid) {
		this.opdabbrivationid = opdabbrivationid;
	}

	public int getCheck_indications() {
		return check_indications;
	}

	public String getEnddischargename() {
		return enddischargename;
	}

	public void setEnddischargename(String enddischargename) {
		this.enddischargename = enddischargename;
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

	public int getLastappointmentid() {
		return lastappointmentid;
	}

	public void setLastappointmentid(int lastappointmentid) {
		this.lastappointmentid = lastappointmentid;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public int getNewgynicid() {
		return newgynicid;
	}

	public void setNewgynicid(int newgynicid) {
		this.newgynicid = newgynicid;
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

	public String getDecreased() {
		return decreased;
	}

	public void setDecreased(String decreased) {
		this.decreased = decreased;
	}

	public String getPerceives() {
		return perceives;
	}

	public void setPerceives(String perceives) {
		this.perceives = perceives;
	}

	public String getNotperceives() {
		return notperceives;
	}

	public void setNotperceives(String notperceives) {
		this.notperceives = notperceives;
	}

public int getTodayadmit() {
		return todayadmit;
	}

	public void setTodayadmit(int todayadmit) {
		this.todayadmit = todayadmit;
	}
private String parentid;
private String dietician_incharge;
private String mlcabrivationid;
private String newadmndate;
private String diagnosis;
private String invoicetype;
private int treatmentstatus;

	public String getInvoicetype() {
	return invoicetype;
}

public void setInvoicetype(String invoicetype) {
	this.invoicetype = invoicetype;
}

	public String getMlcabrivationid() {
	return mlcabrivationid;
}

public void setMlcabrivationid(String mlcabrivationid) {
	this.mlcabrivationid = mlcabrivationid;
}

	public String getDietician_incharge() {
	return dietician_incharge;
}

public void setDietician_incharge(String dietician_incharge) {
	this.dietician_incharge = dietician_incharge;
}

	public String getParentid() {
	return parentid;
}

public void setParentid(String parentid) {
	this.parentid = parentid;
}

	public String getDiaettime() {
		return diaettime;
	}

	public void setDiaettime(String diaettime) {
		this.diaettime = diaettime;
	}
	private String diaetplan;
	private String diettimeshift;

	public String getDiettimeshift() {
		return diettimeshift;
	}

	public void setDiettimeshift(String diettimeshift) {
		this.diettimeshift = diettimeshift;
	}

	public String getDiaetplan() {
		return diaetplan;
	}

	public void setDiaetplan(String diaetplan) {
		this.diaetplan = diaetplan;
	}
	private String diaettime;
	
	
	private String userid;
	private String wardbedname;
	private String invseenstatus;
	private String logid;
	private String action;
	ArrayList<Dietary> categoryList;
	public ArrayList<Dietary> getCategoryList() {
		  return categoryList;
		 }

		 public void setCategoryList(ArrayList<Dietary> categoryList) {
		  this.categoryList = categoryList;
		 }
	private String dis_stepid, dis_checklistname;
	private String dischecklistid;
	private String isexecuted;
	private String remark;
	private String casualtyipd;
	private String ipdseqno;
	private String nursenotes;
	private String surgicalnotes;
	private int cancel=0;
	private String cancelUser="";
	private String gynicid="0";
	private String cancelnote;
	private String pt_history_notes;
	private String family_history_notes;
	private String family_history;
	private String formtype="1";
	private String treatmenthistory;
	//
	private String ivf_date;
	private String down_regulation;
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
	private String dysmenorrhoe;
	private String dysmenorrhoe_notes;
	private String flow;
	private String flow_notes;
	private String sleep_disruption_bleeding;
	private String sleep_disruption_bleeding_notes;
	private String blachouts, blachouts_notes;
	
	private String visit_reason_ids;
	private String edd;
	private String usg;
	private String reasonvisit;
	private String gravida;
	private String para;
	private String live;
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
	
	private	String reason;
	private String quality;
	private	String periodicity;
	private	String influence;
	private	String since;
	private	String notes;
	
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
	private String mlccase;
	private String isactive;
	
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

	private String gen_condition, temp, pallor, pedal_edema, pulse, bmi, height,weight, sys_bp, dia_bp, wall_edema, fundal_height;
	private String cephalic, breech, baley_size, transverse_fhs, liquor, uterine_contractions, ps_cervix, ps_vagine;
	private String ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes, pv_tubular, pv_just_ettacced, pv_mid_posits, pv_soft, pv_ettacced, pv_post;
	
	private String pmp, diagnosisgyn, le_vulva, le_vagina, pa_gynic, plan, finaldiagnosis;
	private String priscription, pv_uterus, pv_uterus_size, pv_bl_fomices, pv_mobility;
	
	private String ps_fhs;
	 private String pv_membrane;
	 private String pv_station;
	 private String pv_liquor;
	 private String pv_pelvis;
	 private String pv_position;
	 private String beats_min;
	
	 private String deathnote;
	 private String tds;
	 private String paid_amount;
	 private String emergencydetail;
	 
	public String getEmergencydetail() {
		return emergencydetail;
	}

	public void setEmergencydetail(String emergencydetail) {
		this.emergencydetail = emergencydetail;
	}

	public String getTreatmenthistory() {
		return treatmenthistory;
	}

	public void setTreatmenthistory(String treatmenthistory) {
		this.treatmenthistory = treatmenthistory;
	}

	public String getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(String paid_amount) {
		this.paid_amount = paid_amount;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getDeathnote() {
		return deathnote;
	}

	public void setDeathnote(String deathnote) {
		this.deathnote = deathnote;
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

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getMlccase() {
		return mlccase;
	}

	public void setMlccase(String mlccase) {
		this.mlccase = mlccase;
	}

	public String getNursenotes() {
		return nursenotes;
	}

	public void setNursenotes(String nursenotes) {
		this.nursenotes = nursenotes;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsexecuted() {
		return isexecuted;
	}

	public void setIsexecuted(String isexecuted) {
		this.isexecuted = isexecuted;
	}

	public String getDischecklistid() {
		return dischecklistid;
	}

	public void setDischecklistid(String dischecklistid) {
		this.dischecklistid = dischecklistid;
	}

	public String getDis_stepid() {
		return dis_stepid;
	}

	public void setDis_stepid(String dis_stepid) {
		this.dis_stepid = dis_stepid;
	}

	public String getDis_checklistname() {
		return dis_checklistname;
	}

	public void setDis_checklistname(String dis_checklistname) {
		this.dis_checklistname = dis_checklistname;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getInvseenstatus() {
		return invseenstatus;
	}

	public void setInvseenstatus(String invseenstatus) {
		this.invseenstatus = invseenstatus;
	}

	public String getWardbedname() {
		return wardbedname;
	}

	public void setWardbedname(String wardbedname) {
		this.wardbedname = wardbedname;
	}

	private String doanew;
	private String dodnew;
	public String getDoanew() {
		return doanew;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setDoanew(String doanew) {
		this.doanew = doanew;
	}

	public String getDodnew() {
		return dodnew;
	}

	public void setDodnew(String dodnew) {
		this.dodnew = dodnew;
	}

	private String patientIdAbrivation;
	public String getPatientIdAbrivation() {
		return patientIdAbrivation;
	}

	public void setPatientIdAbrivation(String patientIdAbrivation) {
		this.patientIdAbrivation = patientIdAbrivation;
	}

	
	
	private String appointmentText;
	 private String anesthesiologist;
	 private String surgeon;
	 private String anesthesia;
	int id;
	String wardid;
	String sectionid;
	String bedname;
    String wardname;
    String equipmentname;
    String sectionname;
	String bedid;
	private String appointmentid;
    private String commencing;
    private String abrivationid="0";
    private String otstatus;
    private String substancehistory;
    private String menstraulhistory;
	String example;
	private String stdchargesetup="0";
	String autochargeraised;
	String imagename;
	private String mlcrefdoctor;
	ArrayList<String> allConsultantList;
	private int totalbookedbed = 0;
	private int totolintitaldischarge=0;
	  private int totalbed;
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

	public ArrayList<String> getAllConsultantList() {
		return allConsultantList;
	}

	public void setAllConsultantList(ArrayList<String> allConsultantList) {
		this.allConsultantList = allConsultantList;
	}

	public String getMlcrefdoctor() {
		return mlcrefdoctor;
	}

	public void setMlcrefdoctor(String mlcrefdoctor) {
		this.mlcrefdoctor = mlcrefdoctor;
	}

	String otNotes;
	String investigation;
	String treatmentgiven;
	String findondischarge;
	
	private String parity_abortion_notes="";
	private String p="0";
	private String l="0";
	private String a="0";
	private String d="0";
	
	
	//Gynic Details
	private String alcohal="0";
	private String drugs="0";
	private String other_medication;
	private String tobaco="0";
	private String tobaconotes;
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
	private String nulligravida;
	private String current_pregnent="0";
	private String menopause;
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
	private String smoking="";
	
	
	private String ipdid;
	private int invsid;
	String billno;
	private String summary;
	private String history;
	private String clientid;
	
	private String practitionerid;
	private int increment;
	private int procedure;
	
	private String conditionid;
	
	private String clientname;
	
	private String practitionername;
	
	private String dob;
	
	private String town;
	
	private String addmissionid;
	
	private String whopay;
	
	private String tpname;
	
	private String tpid;
	
	private String status;
	
	private String age;
	
	private String conditionname;
	 
	private String admissiondate;
	
	private String practitionerMob;
	
	private String clientemail;
	
	private String dischargeDate;
	
	private String date;
	
	private String bedstatus;
	
	private ArrayList<Priscription>ipdPriscList;
	private String earlierinvest="";
	private String admission_reason;
	private String gender;
	
	private String dosenotes;
	
	  private String dischrgeOutcomes;
	    
	    private String dischargeStatus;
	    
	    private boolean chkDischarge;
	    
	    private String discadvnotes;
	    
	    private String hospitalcourse;
	    
	    private String dischargedate;
	    
	    private String hour;
	    
	    private String minute;
	    
	    private double balance;
	    
	    public String doa;
	    
	    public String dod;
	    
	    public String totalDays; 
	    
	    private Discharge dischargeDetails;
	    
	    private String dstatus;
	    
	    private String initialdischargeStatus;
	    
	    private String excessAmt;
	    
	    private boolean checkStandardChargeExist;    
		private String stdChargeID;   
	    
		private String alergies;		
		
	
	    //admission form fields
	
	
		 
		 //visiting consultant
		 private String fees;
		 private String payment;
         private String time;		 
		 
	
        private String payby; 
         
        private String speciality;
	
   
        
        ArrayList<Master> ipdNursingList;
        
        
        
        
        
        
    public ArrayList<Master> getIpdNursingList() {
			return ipdNursingList;
		}

		public void setIpdNursingList(ArrayList<Master> ipdNursingList) {
			this.ipdNursingList = ipdNursingList;
		}

	public String getPayby() {
			return payby;
		}

		public void setPayby(String payby) {
			this.payby = payby;
		}

	public String getFees() {
			return fees;
		}

		public void setFees(String fees) {
			this.fees = fees;
		}

		public String getPayment() {
			return payment;
		}

		public void setPayment(String payment) {
			this.payment = payment;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	public String getExcessAmt() {
			return excessAmt;
		}

		public void setExcessAmt(String excessAmt) {
			this.excessAmt = excessAmt;
		}

	public String getExample() {
			return example;
		}

		public void setExample(String example) {
			this.example = example;
		}

	public String getInitialdischargeStatus() {
			return initialdischargeStatus;
		}

		public void setInitialdischargeStatus(String initialdischargeStatus) {
			this.initialdischargeStatus = initialdischargeStatus;
		}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ArrayList<Priscription> getIpdPriscList() {
		return ipdPriscList;
	}

	public void setIpdPriscList(ArrayList<Priscription> ipdPriscList) {
		this.ipdPriscList = ipdPriscList;
	}

	public String getBedstatus() {
		return bedstatus;
	}

	public void setBedstatus(String bedstatus) {
		this.bedstatus = bedstatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getPractitionerMob() {
		return practitionerMob;
	}

	public String getClientemail() {
		return clientemail;
	}

	public void setClientemail(String clientemail) {
		this.clientemail = clientemail;
	}

	public void setPractitionerMob(String practitionerMob) {
		this.practitionerMob = practitionerMob;
	}

	public String getConditionname() {
		return conditionname;
	}

	public String getAdmissiondate() {
		return admissiondate;
	}

	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}

	public void setConditionname(String conditionname) {
		this.conditionname = conditionname;
	}

	
	private String surgical_ho;
	
	private String refferedby;
    private String addmissionfor;
    private String reimbursment;
    private String secndryconsult;
    private String mlcno;
    private String admissiondetails;
    private String suggestedtrtment;
    private String hosp;
    private String packagename;
    private String chiefcomplains;
    private String presentillness;
    private String pasthistory="";
    private String personalhist="";
    private String familyhist="";
    private String onexamination="";
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
    

	
    private String prisc_id, presc_rem_id, dosename, datetime;
    
    private String date1,date2,date3,date4;
    
    private String color;
    
    private String dis_initiate_time = "";
	private String dis_initiate_status = "";
	private String dis_form_time = "";
	private String dis_form_status = "";
	private String dis_mdicine_time = "";
	private String dis_mdicine_status = "";
	private String dis_bill_time = "";
	private String dis_bill_status = "";
	private String dis_nursing_time = "";
	private String dis_nursing_status = "";
	
	private String dis_initiate_date = "";
	private String dis_form_date = "";
	private String dis_mdicine_date = "";
	private String dis_bill_date = "";
	private String dis_nursing_date = "";
	
	 private String giddinesscondition;
	 private String giddinessnotes;
	 private String unconcondition;
	 private String unconnotes;
	 private String procesurebams;
    
 
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

	public String getDis_initiate_date() {
		return dis_initiate_date;
	}

	public void setDis_initiate_date(String dis_initiate_date) {
		this.dis_initiate_date = dis_initiate_date;
	}

	public String getDis_form_date() {
		return dis_form_date;
	}

	public void setDis_form_date(String dis_form_date) {
		this.dis_form_date = dis_form_date;
	}

	public String getDis_mdicine_date() {
		return dis_mdicine_date;
	}

	public void setDis_mdicine_date(String dis_mdicine_date) {
		this.dis_mdicine_date = dis_mdicine_date;
	}

	public String getDis_bill_date() {
		return dis_bill_date;
	}

	public void setDis_bill_date(String dis_bill_date) {
		this.dis_bill_date = dis_bill_date;
	}

	public String getDis_nursing_date() {
		return dis_nursing_date;
	}

	public void setDis_nursing_date(String dis_nursing_date) {
		this.dis_nursing_date = dis_nursing_date;
	}

	public String getDis_initiate_time() {
		return dis_initiate_time;
	}

	public void setDis_initiate_time(String dis_initiate_time) {
		this.dis_initiate_time = dis_initiate_time;
	}

	public String getDis_initiate_status() {
		return dis_initiate_status;
	}

	public void setDis_initiate_status(String dis_initiate_status) {
		this.dis_initiate_status = dis_initiate_status;
	}

	public String getDis_form_time() {
		return dis_form_time;
	}

	public void setDis_form_time(String dis_form_time) {
		this.dis_form_time = dis_form_time;
	}

	public String getDis_form_status() {
		return dis_form_status;
	}

	public void setDis_form_status(String dis_form_status) {
		this.dis_form_status = dis_form_status;
	}

	public String getDis_mdicine_time() {
		return dis_mdicine_time;
	}

	public void setDis_mdicine_time(String dis_mdicine_time) {
		this.dis_mdicine_time = dis_mdicine_time;
	}

	public String getDis_mdicine_status() {
		return dis_mdicine_status;
	}

	public void setDis_mdicine_status(String dis_mdicine_status) {
		this.dis_mdicine_status = dis_mdicine_status;
	}

	public String getDis_bill_time() {
		return dis_bill_time;
	}

	public void setDis_bill_time(String dis_bill_time) {
		this.dis_bill_time = dis_bill_time;
	}

	public String getDis_bill_status() {
		return dis_bill_status;
	}

	public void setDis_bill_status(String dis_bill_status) {
		this.dis_bill_status = dis_bill_status;
	}

	public String getDis_nursing_time() {
		return dis_nursing_time;
	}

	public void setDis_nursing_time(String dis_nursing_time) {
		this.dis_nursing_time = dis_nursing_time;
	}

	public String getDis_nursing_status() {
		return dis_nursing_status;
	}

	public void setDis_nursing_status(String dis_nursing_status) {
		this.dis_nursing_status = dis_nursing_status;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public String getMedicinename() {
		return medicinename;
	}

	public void setMedicinename(String medicinename) {
		this.medicinename = medicinename;
	}

	
	
	public String getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRefferedby() {
		return refferedby;
	}

	public void setRefferedby(String refferedby) {
		this.refferedby = refferedby;
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

		public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

		public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

		public void setId(int id) {
			this.id = id;
		}

		public String getWardid() {
			return wardid;
		}

		public void setWardid(String wardid) {
			this.wardid = wardid;
		}

		public String getSectionid() {
			return sectionid;
		}

		public void setSectionid(String sectionid) {
			this.sectionid = sectionid;
		}

		public String getBedname() {
			return bedname;
		}

		public void setBedname(String bedname) {
			this.bedname = bedname;
		}

		public String getWardname() {
			return wardname;
		}

		public void setWardname(String wardname) {
			this.wardname = wardname;
		}

		public String getEquipmentname() {
			return equipmentname;
		}

		public void setEquipmentname(String equipmentname) {
			this.equipmentname = equipmentname;
		}

		public String getSectionname() {
			return sectionname;
		}

		public void setSectionname(String sectionname) {
			this.sectionname = sectionname;
		}

		public String getBedid() {
			return bedid;
		}

		public void setBedid(String bedid) {
			this.bedid = bedid;
		}

		


	
	
	
	public String getClientname() {
		return clientname;
	}

	public String getWhopay() {
			return whopay;
		}

		public void setWhopay(String whopay) {
			this.whopay = whopay;
		}

		public String getTpname() {
			return tpname;
		}

		public void setTpname(String tpname) {
			this.tpname = tpname;
		}

		public String getTpid() {
			return tpid;
		}

		public void setTpid(String tpid) {
			this.tpid = tpid;
		}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getPractitionername() {
		return practitionername;
	}

	public void setPractitionername(String practitionername) {
		this.practitionername = practitionername;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAddmissionid() {
		return addmissionid;
	}

	public void setAddmissionid(String addmissionid) {
		this.addmissionid = addmissionid;
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

	public String getDosenotes() {
		return dosenotes;
	}

	public void setDosenotes(String dosenotes) {
		this.dosenotes = dosenotes;
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

	public String getDischargedate() {
		return dischargedate;
	}

	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDoa() {
		return doa;
	}

	public void setDoa(String doa) {
		this.doa = doa;
	}

	public String getDod() {
		return dod;
	}

	public void setDod(String dod) {
		this.dod = dod;
	}

	public String getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(String totalDays) {
		this.totalDays = totalDays;
	}

	public Discharge getDischargeDetails() {
		return dischargeDetails;
	}

	public void setDischargeDetails(Discharge dischargeDetails) {
		this.dischargeDetails = dischargeDetails;
	}

	public String getDstatus() {
		return dstatus;
	}

	public void setDstatus(String dstatus) {
		this.dstatus = dstatus;
	}

	public String getAutochargeraised() {
		return autochargeraised;
	}

	public void setAutochargeraised(String autochargeraised) {
		this.autochargeraised = autochargeraised;
	}

	public boolean isCheckStandardChargeExist() {
		return checkStandardChargeExist;
	}

	public void setCheckStandardChargeExist(boolean checkStandardChargeExist) {
		this.checkStandardChargeExist = checkStandardChargeExist;
	}

	public String getStdChargeID() {
		return stdChargeID;
	}

	public void setStdChargeID(String stdChargeID) {
		this.stdChargeID = stdChargeID;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getEarlierinvest() {
		return earlierinvest;
	}

	public void setEarlierinvest(String earlierinvest) {
		this.earlierinvest = earlierinvest;
	}

	public String getAdmission_reason() {
		return admission_reason;
	}

	public void setAdmission_reason(String admission_reason) {
		this.admission_reason = admission_reason;
	}

	public String getAlergies() {
		return alergies;
	}

	public void setAlergies(String alergies) {
		this.alergies = alergies;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public int getProcedure() {
		return procedure;
	}

	public void setProcedure(int procedure) {
		this.procedure = procedure;
	}

	public int getInvsid() {
		return invsid;
	}

	public void setInvsid(int invsid) {
		this.invsid = invsid;
	}

	public String getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(String appointmentid) {
		this.appointmentid = appointmentid;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getOtstatus() {
		return otstatus;
	}

	public void setOtstatus(String otstatus) {
		this.otstatus = otstatus;
	}

	public String getStdchargesetup() {
		return stdchargesetup;
	}

	public void setStdchargesetup(String stdchargesetup) {
		this.stdchargesetup = stdchargesetup;
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

	public String getOtNotes() {
		return otNotes;
	}

	public void setOtNotes(String otNotes) {
		this.otNotes = otNotes;
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

	public String getAppointmentText() {
		return appointmentText;
	}

	public void setAppointmentText(String appointmentText) {
		this.appointmentText = appointmentText;
	}

	public String getAnesthesiologist() {
		return anesthesiologist;
	}

	public void setAnesthesiologist(String anesthesiologist) {
		this.anesthesiologist = anesthesiologist;
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

	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}

	public String getSurgicalnotes() {
		return surgicalnotes;
	}

	public void setSurgicalnotes(String surgicalnotes) {
		this.surgicalnotes = surgicalnotes;
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public String getCancelUser() {
		return cancelUser;
	}

	public void setCancelUser(String cancelUser) {
		this.cancelUser = cancelUser;
	}

	public String getCancelnote() {
		return cancelnote;
	}

	public void setCancelnote(String cancelnote) {
		this.cancelnote = cancelnote;
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

	public String getVisit_reason_ids() {
		return visit_reason_ids;
	}

	public void setVisit_reason_ids(String visit_reason_ids) {
		this.visit_reason_ids = visit_reason_ids;
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

	public String getSurgical_ho() {
		return surgical_ho;
	}

	public void setSurgical_ho(String surgical_ho) {
		this.surgical_ho = surgical_ho;
	}

	public String getDate4() {
		return date4;
	}

	public void setDate4(String date4) {
		this.date4 = date4;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}

	public String getInfluence() {
		return influence;
	}

	public void setInfluence(String influence) {
		this.influence = influence;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getReasonvisit() {
		return reasonvisit;
	}

	public void setReasonvisit(String reasonvisit) {
		this.reasonvisit = reasonvisit;
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

	public String getGynicid() {
		return gynicid;
	}

	public void setGynicid(String gynicid) {
		this.gynicid = gynicid;
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

	ArrayList<Dietary> catlist;
	
	public ArrayList<Dietary> getCatlist() {
		return catlist;
	}

	public void setCatlist(ArrayList<Dietary> catlist) {
		this.catlist = catlist;
	}
	ArrayList<Bed>list2;
	public ArrayList<Bed> getList2() {
		return list2;
	}

	public void setList2(ArrayList<Bed> list2) {
		this.list2 = list2;
	}
	private String dietplan;
	
	public String getDietplan() {
		return dietplan;
	}

	public void setDietplan(String dietplan) {
		this.dietplan = dietplan;
	}

	
	private String getCate;
	public String getGetCate() {
		return getCate;
	}

	public void setGetCate(String getCate) {
		this.getCate = getCate;
	}
	public String getReferenceid() {
		return referenceid;
	}

	public void setReferenceid(String referenceid) {
		this.referenceid = referenceid;
	}

	
	private String referenceid;	
	
	private String birthhist="";
	 private String diethist="";
	 private String emmunizationhist="";
	 private String developmenthist="";

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
	public boolean isPeditric() {
		return peditric;
	}

	public void setPeditric(boolean peditric) {
		this.peditric = peditric;
	}
	private boolean peditric;
	private String findondis,dischargeadvice;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFulldiet() {
		return fulldiet;
	}

	public void setFulldiet(int fulldiet) {
		this.fulldiet = fulldiet;
	}

	public int getSoftdiet() {
		return softdiet;
	}

	public void setSoftdiet(int softdiet) {
		this.softdiet = softdiet;
	}

	public int getLiquiddiet() {
		return liquiddiet;
	}

	public void setLiquiddiet(int liquiddiet) {
		this.liquiddiet = liquiddiet;
	}

	public int getDiabetic() {
		return diabetic;
	}

	public void setDiabetic(int diabetic) {
		this.diabetic = diabetic;
	}

	public int getRtfeed() {
		return rtfeed;
	}

	public void setRtfeed(int rtfeed) {
		this.rtfeed = rtfeed;
	}

	public int getRenal() {
		return renal;
	}

	public void setRenal(int renal) {
		this.renal = renal;
	}

	public int getHepatic() {
		return Hepatic;
	}

	public void setHepatic(int hepatic) {
		Hepatic = hepatic;
	}

	public int getClearliquid() {
		return clearliquid;
	}

	public void setClearliquid(int clearliquid) {
		this.clearliquid = clearliquid;
	}

	public int getSemisolid() {
		return semisolid;
	}

	public void setSemisolid(int semisolid) {
		this.semisolid = semisolid;
	}

	public int getBlended() {
		return blended;
	}

	public void setBlended(int blended) {
		this.blended = blended;
	}

	public int getNbm() {
		return nbm;
	}

	public void setNbm(int nbm) {
		this.nbm = nbm;
	}

	public String getFindondis() {
		return findondis;
	}

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

	public void setFindondis(String findondis) {
		this.findondis = findondis;
	}

	public String getDischargeadvice() {
		return dischargeadvice;
	}

	public void setDischargeadvice(String dischargeadvice) {
		this.dischargeadvice = dischargeadvice;
	}
	
	
	public String getAgeonAdmn() {
		return ageonAdmn;
	}

	public void setAgeonAdmn(String ageonAdmn) {
		this.ageonAdmn = ageonAdmn;
	}
	
	
	
	public String getDischarge_end_date() {
		return discharge_end_date;
	}

	public void setDischarge_end_date(String discharge_end_date) {
		this.discharge_end_date = discharge_end_date;
	}
	public String getIpdabrivationid() {
		return ipdabrivationid;
	}

	public void setIpdabrivationid(String ipdabrivationid) {
		this.ipdabrivationid = ipdabrivationid;
	}
	public String getNewadmndate() {
		return newadmndate;
	}

	public void setNewadmndate(String newadmndate) {
		this.newadmndate = newadmndate;
	}
	
	public String getNewipdabbrseq() {
		return newipdabbrseq;
	}

	public void setNewipdabbrseq(String newipdabbrseq) {
		this.newipdabbrseq = newipdabbrseq;
	}
	private String ageonAdmn;
	private String headcircumference,midarmcircumference,length,wtaddmission,wtdischarge;
	private  int count=0,fulldiet=0,softdiet=0,liquiddiet=0,diabetic=0,rtfeed=0,renal=0,Hepatic=0,clearliquid=0,semisolid=0,blended=0,nbm=0;
	private String discharge_end_date;
	private String ipdabrivationid;
	private String newipdabbrseq;
	
	public String getKunal_manual_medicine_text() {
		return kunal_manual_medicine_text;
	}

	public void setKunal_manual_medicine_text(String kunal_manual_medicine_text) {
		this.kunal_manual_medicine_text = kunal_manual_medicine_text;
	}
	private String kunal_manual_medicine_text;
	private String kunal_final_diagnosis_text;
	public String getKunal_final_diagnosis_text() {
		return kunal_final_diagnosis_text;
	}

	public void setKunal_final_diagnosis_text(String kunal_final_diagnosis_text) {
		this.kunal_final_diagnosis_text = kunal_final_diagnosis_text;
	}
public String getIpdnewid() {
		return ipdnewid;
	}

	public void setIpdnewid(String ipdnewid) {
		this.ipdnewid = ipdnewid;
	}
public String getDis_status_name() {
		return dis_status_name;
	}

	public void setDis_status_name(String dis_status_name) {
		this.dis_status_name = dis_status_name;
	}
public String getGstureage() {
		return gstureage;
	}

	public void setGstureage(String gstureage) {
		this.gstureage = gstureage;
	}
public String getWtonbirth() {
		return wtonbirth;
	}

	public void setWtonbirth(String wtonbirth) {
		this.wtonbirth = wtonbirth;
	}
public String getPerinatal_history() {
		return perinatal_history;
	}

	public void setPerinatal_history(String perinatal_history) {
		this.perinatal_history = perinatal_history;
	}
public String getMaternal_history() {
		return maternal_history;
	}

	public void setMaternal_history(String maternal_history) {
		this.maternal_history = maternal_history;
	}
public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
public String getReasonlamadama() {
		return reasonlamadama;
	}

	public void setReasonlamadama(String reasonlamadama) {
		this.reasonlamadama = reasonlamadama;
	}
private String ipdnewid;
private String dis_status_name;
private String gstureage;

private String wtonbirth;
private String maternal_history,perinatal_history;
private String reasonlamadama;







//new ipd fields
private String  past_hist_HTN,past_hist_DM,past_hist_IHD,past_hist_Other,past_hist_CVE,past_hist_br_asthama,past_hist_COAD,past_hist_Thyroid;
private String past_hist_HTN_text,past_hist_DM_text,past_hist_IHD_text,past_hist_Other_text,past_hist_CVE_text,past_hist_br_asthama_text,past_hist_COAD_text,past_hist_Thyroid_text;
private String person_hist_Smoking,person_hist_Alchohol,person_hist_OtherAddt,person_hist_Bowel_Bladder,person_hist_Sleep,person_hist_Tobacco;
private String person_hist_Smoking_text,person_hist_Alchohol_text,person_hist_OtherAddt_text,person_hist_Bowel_Bladder_text,person_hist_Sleep_text,person_hist_Tobacco_text;
private String obng_menstrual_hist,obng_gpla,obng_tubesctomy,obng_lmp;
private String apearnace_Pallor,apearnace_Cynosis,apearnace_Clubbing,apearnace_Icterus,apearnace_ln,sys_exa_CVS,sys_exa_RS,sys_exa_CNS,sys_exa_PA,sys_exa_PVPR,sys_exa_Others;
private String gen_cond_Temp,gen_cond_Pulse,gen_cond_BP,gen_cond_CVS,gen_cond_PS,gen_cond_CNS,physio_th_adv_Mobilization,physio_th_adv_fallRisk,physio_th_adv_Driving,physio_th_adv_sexual_Activity,dietary_advice,local_relevant_area,tubes_and_training;

public String getPast_hist_HTN() {
	return past_hist_HTN;
}

public void setPast_hist_HTN(String past_hist_HTN) {
	this.past_hist_HTN = past_hist_HTN;
}

public String getPast_hist_DM() {
	return past_hist_DM;
}

public void setPast_hist_DM(String past_hist_DM) {
	this.past_hist_DM = past_hist_DM;
}

public String getPast_hist_IHD() {
	return past_hist_IHD;
}

public void setPast_hist_IHD(String past_hist_IHD) {
	this.past_hist_IHD = past_hist_IHD;
}

public String getPast_hist_Other() {
	return past_hist_Other;
}

public void setPast_hist_Other(String past_hist_Other) {
	this.past_hist_Other = past_hist_Other;
}

public String getPast_hist_CVE() {
	return past_hist_CVE;
}

public void setPast_hist_CVE(String past_hist_CVE) {
	this.past_hist_CVE = past_hist_CVE;
}

public String getPast_hist_br_asthama() {
	return past_hist_br_asthama;
}

public void setPast_hist_br_asthama(String past_hist_br_asthama) {
	this.past_hist_br_asthama = past_hist_br_asthama;
}

public String getPast_hist_COAD() {
	return past_hist_COAD;
}

public void setPast_hist_COAD(String past_hist_COAD) {
	this.past_hist_COAD = past_hist_COAD;
}

public String getPast_hist_Thyroid() {
	return past_hist_Thyroid;
}

public void setPast_hist_Thyroid(String past_hist_Thyroid) {
	this.past_hist_Thyroid = past_hist_Thyroid;
}

public String getPast_hist_HTN_text() {
	return past_hist_HTN_text;
}

public void setPast_hist_HTN_text(String past_hist_HTN_text) {
	this.past_hist_HTN_text = past_hist_HTN_text;
}

public String getPast_hist_DM_text() {
	return past_hist_DM_text;
}

public void setPast_hist_DM_text(String past_hist_DM_text) {
	this.past_hist_DM_text = past_hist_DM_text;
}

public String getPast_hist_IHD_text() {
	return past_hist_IHD_text;
}

public void setPast_hist_IHD_text(String past_hist_IHD_text) {
	this.past_hist_IHD_text = past_hist_IHD_text;
}

public String getPast_hist_Other_text() {
	return past_hist_Other_text;
}

public void setPast_hist_Other_text(String past_hist_Other_text) {
	this.past_hist_Other_text = past_hist_Other_text;
}

public String getPast_hist_CVE_text() {
	return past_hist_CVE_text;
}

public void setPast_hist_CVE_text(String past_hist_CVE_text) {
	this.past_hist_CVE_text = past_hist_CVE_text;
}

public String getPast_hist_br_asthama_text() {
	return past_hist_br_asthama_text;
}

public void setPast_hist_br_asthama_text(String past_hist_br_asthama_text) {
	this.past_hist_br_asthama_text = past_hist_br_asthama_text;
}

public String getPast_hist_COAD_text() {
	return past_hist_COAD_text;
}

public void setPast_hist_COAD_text(String past_hist_COAD_text) {
	this.past_hist_COAD_text = past_hist_COAD_text;
}

public String getPast_hist_Thyroid_text() {
	return past_hist_Thyroid_text;
}

public void setPast_hist_Thyroid_text(String past_hist_Thyroid_text) {
	this.past_hist_Thyroid_text = past_hist_Thyroid_text;
}

public String getPerson_hist_Smoking() {
	return person_hist_Smoking;
}

public void setPerson_hist_Smoking(String person_hist_Smoking) {
	this.person_hist_Smoking = person_hist_Smoking;
}

public String getPerson_hist_Alchohol() {
	return person_hist_Alchohol;
}

public void setPerson_hist_Alchohol(String person_hist_Alchohol) {
	this.person_hist_Alchohol = person_hist_Alchohol;
}

public String getPerson_hist_OtherAddt() {
	return person_hist_OtherAddt;
}

public void setPerson_hist_OtherAddt(String person_hist_OtherAddt) {
	this.person_hist_OtherAddt = person_hist_OtherAddt;
}

public String getPerson_hist_Bowel_Bladder() {
	return person_hist_Bowel_Bladder;
}

public void setPerson_hist_Bowel_Bladder(String person_hist_Bowel_Bladder) {
	this.person_hist_Bowel_Bladder = person_hist_Bowel_Bladder;
}

public String getPerson_hist_Sleep() {
	return person_hist_Sleep;
}

public void setPerson_hist_Sleep(String person_hist_Sleep) {
	this.person_hist_Sleep = person_hist_Sleep;
}

public String getPerson_hist_Tobacco() {
	return person_hist_Tobacco;
}

public void setPerson_hist_Tobacco(String person_hist_Tobacco) {
	this.person_hist_Tobacco = person_hist_Tobacco;
}

public String getPerson_hist_Smoking_text() {
	return person_hist_Smoking_text;
}

public void setPerson_hist_Smoking_text(String person_hist_Smoking_text) {
	this.person_hist_Smoking_text = person_hist_Smoking_text;
}

public String getPerson_hist_Alchohol_text() {
	return person_hist_Alchohol_text;
}

public void setPerson_hist_Alchohol_text(String person_hist_Alchohol_text) {
	this.person_hist_Alchohol_text = person_hist_Alchohol_text;
}

public String getPerson_hist_OtherAddt_text() {
	return person_hist_OtherAddt_text;
}

public void setPerson_hist_OtherAddt_text(String person_hist_OtherAddt_text) {
	this.person_hist_OtherAddt_text = person_hist_OtherAddt_text;
}

public String getPerson_hist_Bowel_Bladder_text() {
	return person_hist_Bowel_Bladder_text;
}

public void setPerson_hist_Bowel_Bladder_text(String person_hist_Bowel_Bladder_text) {
	this.person_hist_Bowel_Bladder_text = person_hist_Bowel_Bladder_text;
}

public String getPerson_hist_Sleep_text() {
	return person_hist_Sleep_text;
}

public void setPerson_hist_Sleep_text(String person_hist_Sleep_text) {
	this.person_hist_Sleep_text = person_hist_Sleep_text;
}

public String getPerson_hist_Tobacco_text() {
	return person_hist_Tobacco_text;
}

public void setPerson_hist_Tobacco_text(String person_hist_Tobacco_text) {
	this.person_hist_Tobacco_text = person_hist_Tobacco_text;
}

public String getObng_menstrual_hist() {
	return obng_menstrual_hist;
}

public void setObng_menstrual_hist(String obng_menstrual_hist) {
	this.obng_menstrual_hist = obng_menstrual_hist;
}

public String getObng_gpla() {
	return obng_gpla;
}

public void setObng_gpla(String obng_gpla) {
	this.obng_gpla = obng_gpla;
}

public String getObng_tubesctomy() {
	return obng_tubesctomy;
}

public void setObng_tubesctomy(String obng_tubesctomy) {
	this.obng_tubesctomy = obng_tubesctomy;
}

public String getObng_lmp() {
	return obng_lmp;
}

public void setObng_lmp(String obng_lmp) {
	this.obng_lmp = obng_lmp;
}

public String getApearnace_Pallor() {
	return apearnace_Pallor;
}

public void setApearnace_Pallor(String apearnace_Pallor) {
	this.apearnace_Pallor = apearnace_Pallor;
}

public String getApearnace_Cynosis() {
	return apearnace_Cynosis;
}

public void setApearnace_Cynosis(String apearnace_Cynosis) {
	this.apearnace_Cynosis = apearnace_Cynosis;
}

public String getApearnace_Clubbing() {
	return apearnace_Clubbing;
}

public void setApearnace_Clubbing(String apearnace_Clubbing) {
	this.apearnace_Clubbing = apearnace_Clubbing;
}

public String getApearnace_Icterus() {
	return apearnace_Icterus;
}

public void setApearnace_Icterus(String apearnace_Icterus) {
	this.apearnace_Icterus = apearnace_Icterus;
}

public String getApearnace_ln() {
	return apearnace_ln;
}

public void setApearnace_ln(String apearnace_ln) {
	this.apearnace_ln = apearnace_ln;
}

public String getSys_exa_CVS() {
	return sys_exa_CVS;
}

public void setSys_exa_CVS(String sys_exa_CVS) {
	this.sys_exa_CVS = sys_exa_CVS;
}

public String getSys_exa_RS() {
	return sys_exa_RS;
}

public void setSys_exa_RS(String sys_exa_RS) {
	this.sys_exa_RS = sys_exa_RS;
}

public String getSys_exa_CNS() {
	return sys_exa_CNS;
}

public void setSys_exa_CNS(String sys_exa_CNS) {
	this.sys_exa_CNS = sys_exa_CNS;
}

public String getSys_exa_PA() {
	return sys_exa_PA;
}

public void setSys_exa_PA(String sys_exa_PA) {
	this.sys_exa_PA = sys_exa_PA;
}

public String getSys_exa_PVPR() {
	return sys_exa_PVPR;
}

public void setSys_exa_PVPR(String sys_exa_PVPR) {
	this.sys_exa_PVPR = sys_exa_PVPR;
}

public String getSys_exa_Others() {
	return sys_exa_Others;
}

public void setSys_exa_Others(String sys_exa_Others) {
	this.sys_exa_Others = sys_exa_Others;
}

public String getGen_cond_Temp() {
	return gen_cond_Temp;
}

public void setGen_cond_Temp(String gen_cond_Temp) {
	this.gen_cond_Temp = gen_cond_Temp;
}

public String getGen_cond_Pulse() {
	return gen_cond_Pulse;
}

public void setGen_cond_Pulse(String gen_cond_Pulse) {
	this.gen_cond_Pulse = gen_cond_Pulse;
}

public String getGen_cond_BP() {
	return gen_cond_BP;
}

public void setGen_cond_BP(String gen_cond_BP) {
	this.gen_cond_BP = gen_cond_BP;
}

public String getGen_cond_CVS() {
	return gen_cond_CVS;
}

public void setGen_cond_CVS(String gen_cond_CVS) {
	this.gen_cond_CVS = gen_cond_CVS;
}

public String getGen_cond_PS() {
	return gen_cond_PS;
}

public void setGen_cond_PS(String gen_cond_PS) {
	this.gen_cond_PS = gen_cond_PS;
}

public String getGen_cond_CNS() {
	return gen_cond_CNS;
}

public void setGen_cond_CNS(String gen_cond_CNS) {
	this.gen_cond_CNS = gen_cond_CNS;
}

public String getPhysio_th_adv_Mobilization() {
	return physio_th_adv_Mobilization;
}

public void setPhysio_th_adv_Mobilization(String physio_th_adv_Mobilization) {
	this.physio_th_adv_Mobilization = physio_th_adv_Mobilization;
}

public String getPhysio_th_adv_fallRisk() {
	return physio_th_adv_fallRisk;
}

public void setPhysio_th_adv_fallRisk(String physio_th_adv_fallRisk) {
	this.physio_th_adv_fallRisk = physio_th_adv_fallRisk;
}

public String getPhysio_th_adv_Driving() {
	return physio_th_adv_Driving;
}

public void setPhysio_th_adv_Driving(String physio_th_adv_Driving) {
	this.physio_th_adv_Driving = physio_th_adv_Driving;
}

public String getPhysio_th_adv_sexual_Activity() {
	return physio_th_adv_sexual_Activity;
}

public void setPhysio_th_adv_sexual_Activity(String physio_th_adv_sexual_Activity) {
	this.physio_th_adv_sexual_Activity = physio_th_adv_sexual_Activity;
}

public String getDietary_advice() {
	return dietary_advice;
}

public void setDietary_advice(String dietary_advice) {
	this.dietary_advice = dietary_advice;
}

public String getLocal_relevant_area() {
	return local_relevant_area;
}

public void setLocal_relevant_area(String local_relevant_area) {
	this.local_relevant_area = local_relevant_area;
}

public String getTubes_and_training() {
	return tubes_and_training;
}

public void setTubes_and_training(String tubes_and_training) {
	this.tubes_and_training = tubes_and_training;
}
public String getLine_tube_drains() {
	return line_tube_drains;
}

public void setLine_tube_drains(String line_tube_drains) {
	this.line_tube_drains = line_tube_drains;
}
public String getWhen_to_get_help() {
	return when_to_get_help;
}

public void setWhen_to_get_help(String when_to_get_help) {
	this.when_to_get_help = when_to_get_help;
}
public int getTreatmentstatus() {
	return treatmentstatus;
}

public void setTreatmentstatus(int treatmentstatus) {
	this.treatmentstatus = treatmentstatus;
}
public String getApearnace_Oedema() {
	return apearnace_Oedema;
}

public void setApearnace_Oedema(String apearnace_Oedema) {
	this.apearnace_Oedema = apearnace_Oedema;
}
public String getGeneral_other() {
	return general_other;
}

public void setGeneral_other(String general_other) {
	this.general_other = general_other;
}
private String when_to_get_help;
private String line_tube_drains,apearnace_Oedema,general_other;
private String 
fm_hist_hypertension,fm_hist_asthma,fm_hist_heart_disease,fm_hist_stroke,fm_hist_diabetes,fm_hist_arthritis_gout,fm_hist_tuberculosis,fm_hist_cancer,fm_hist_epilepsy,fm_hist_other_chronic;

public String getFm_hist_hypertension() {
	return fm_hist_hypertension;
}

public void setFm_hist_hypertension(String fm_hist_hypertension) {
	this.fm_hist_hypertension = fm_hist_hypertension;
}

public String getFm_hist_asthma() {
	return fm_hist_asthma;
}

public void setFm_hist_asthma(String fm_hist_asthma) {
	this.fm_hist_asthma = fm_hist_asthma;
}

public String getFm_hist_heart_disease() {
	return fm_hist_heart_disease;
}

public void setFm_hist_heart_disease(String fm_hist_heart_disease) {
	this.fm_hist_heart_disease = fm_hist_heart_disease;
}

public String getFm_hist_stroke() {
	return fm_hist_stroke;
}

public void setFm_hist_stroke(String fm_hist_stroke) {
	this.fm_hist_stroke = fm_hist_stroke;
}

public String getFm_hist_diabetes() {
	return fm_hist_diabetes;
}

public void setFm_hist_diabetes(String fm_hist_diabetes) {
	this.fm_hist_diabetes = fm_hist_diabetes;
}

public String getFm_hist_arthritis_gout() {
	return fm_hist_arthritis_gout;
}

public void setFm_hist_arthritis_gout(String fm_hist_arthritis_gout) {
	this.fm_hist_arthritis_gout = fm_hist_arthritis_gout;
}

public String getFm_hist_tuberculosis() {
	return fm_hist_tuberculosis;
}

public void setFm_hist_tuberculosis(String fm_hist_tuberculosis) {
	this.fm_hist_tuberculosis = fm_hist_tuberculosis;
}

public String getFm_hist_cancer() {
	return fm_hist_cancer;
}

public void setFm_hist_cancer(String fm_hist_cancer) {
	this.fm_hist_cancer = fm_hist_cancer;
}

public String getFm_hist_epilepsy() {
	return fm_hist_epilepsy;
}

public void setFm_hist_epilepsy(String fm_hist_epilepsy) {
	this.fm_hist_epilepsy = fm_hist_epilepsy;
}

public String getFm_hist_other_chronic() {
	return fm_hist_other_chronic;
}

public void setFm_hist_other_chronic(String fm_hist_other_chronic) {
	this.fm_hist_other_chronic = fm_hist_other_chronic;
}
public String getCall_for_appointmant() {
	return call_for_appointmant;
}

public void setCall_for_appointmant(String call_for_appointmant) {
	this.call_for_appointmant = call_for_appointmant;
}
public String getConsent_sign() {
	return consent_sign;
}

public void setConsent_sign(String consent_sign) {
	this.consent_sign = consent_sign;
}
private String  call_for_appointmant,consent_sign;
private String fm_hist_hypertension_text,fm_hist_asthma_text,fm_hist_heart_disease_text,fm_hist_stroke_text,fm_hist_diabetes_text,fm_hist_arthritis_gout_text,fm_hist_tuberculosis_text,fm_hist_cancer_text,fm_hist_epilepsy_text,fm_hist_other_chronic_text;

public String getFm_hist_hypertension_text() {
	return fm_hist_hypertension_text;
}

public void setFm_hist_hypertension_text(String fm_hist_hypertension_text) {
	this.fm_hist_hypertension_text = fm_hist_hypertension_text;
}

public String getFm_hist_asthma_text() {
	return fm_hist_asthma_text;
}

public void setFm_hist_asthma_text(String fm_hist_asthma_text) {
	this.fm_hist_asthma_text = fm_hist_asthma_text;
}

public String getFm_hist_heart_disease_text() {
	return fm_hist_heart_disease_text;
}

public void setFm_hist_heart_disease_text(String fm_hist_heart_disease_text) {
	this.fm_hist_heart_disease_text = fm_hist_heart_disease_text;
}

public String getFm_hist_stroke_text() {
	return fm_hist_stroke_text;
}

public void setFm_hist_stroke_text(String fm_hist_stroke_text) {
	this.fm_hist_stroke_text = fm_hist_stroke_text;
}

public String getFm_hist_diabetes_text() {
	return fm_hist_diabetes_text;
}

public void setFm_hist_diabetes_text(String fm_hist_diabetes_text) {
	this.fm_hist_diabetes_text = fm_hist_diabetes_text;
}

public String getFm_hist_arthritis_gout_text() {
	return fm_hist_arthritis_gout_text;
}

public void setFm_hist_arthritis_gout_text(String fm_hist_arthritis_gout_text) {
	this.fm_hist_arthritis_gout_text = fm_hist_arthritis_gout_text;
}

public String getFm_hist_tuberculosis_text() {
	return fm_hist_tuberculosis_text;
}

public void setFm_hist_tuberculosis_text(String fm_hist_tuberculosis_text) {
	this.fm_hist_tuberculosis_text = fm_hist_tuberculosis_text;
}

public String getFm_hist_cancer_text() {
	return fm_hist_cancer_text;
}

public void setFm_hist_cancer_text(String fm_hist_cancer_text) {
	this.fm_hist_cancer_text = fm_hist_cancer_text;
}

public String getFm_hist_epilepsy_text() {
	return fm_hist_epilepsy_text;
}

public void setFm_hist_epilepsy_text(String fm_hist_epilepsy_text) {
	this.fm_hist_epilepsy_text = fm_hist_epilepsy_text;
}

public String getFm_hist_other_chronic_text() {
	return fm_hist_other_chronic_text;
}
private String sharePercent;
public void setFm_hist_other_chronic_text(String fm_hist_other_chronic_text) {
	this.fm_hist_other_chronic_text = fm_hist_other_chronic_text;
}

public String getSharePercent() {
	return sharePercent;
}

public void setSharePercent(String sharePercent) {
	this.sharePercent = sharePercent;
}

public String getOt_manual_surg() {
	return ot_manual_surg;
}

public void setOt_manual_surg(String ot_manual_surg) {
	this.ot_manual_surg = ot_manual_surg;
}
public String getOt_manual_anesthesio() {
	return ot_manual_anesthesio;
}

public void setOt_manual_anesthesio(String ot_manual_anesthesio) {
	this.ot_manual_anesthesio = ot_manual_anesthesio;
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
public String getHrf_otherval() {
	return hrf_otherval;
}

public void setHrf_otherval(String hrf_otherval) {
	this.hrf_otherval = hrf_otherval;
}
public String getDays_dating_scan() {
	return days_dating_scan;
}

public void setDays_dating_scan(String days_dating_scan) {
	this.days_dating_scan = days_dating_scan;
}
public String getCasualty() {
	return casualty;
}

public void setCasualty(String casualty) {
	this.casualty = casualty;
}
private String ot_manual_surg,ot_manual_anesthesio;

private  String invoiceno;


public String getInvoiceno() {
	return invoiceno;
}

public void setInvoiceno(String invoiceno) {
	this.invoiceno = invoiceno;
}

private String reqid;
}
