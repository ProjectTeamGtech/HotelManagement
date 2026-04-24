package com.apm.DiaryManagement.eu.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.common.Month;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Log.eu.entity.Modify;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.utils.DateTimeUtils;

public class NotAvailableSlot {
	public int getOldpatient() {
		return oldpatient;
	}
	public void setOldpatient(int oldpatient) {
		this.oldpatient = oldpatient;
	}
	public StringBuffer getConsultnotes() {
		return consultnotes;
	}
	public void setConsultnotes(StringBuffer consultnotes) {
		this.consultnotes = consultnotes;
	}
	public String getMasterid() {
		return masterid;
	}
	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	public String getDatecolor() {
		return datecolor;
	}
	public void setDatecolor(String datecolor) {
		this.datecolor = datecolor;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getIpdsetting() {
		return ipdsetting;
	}
	public void setIpdsetting(String ipdsetting) {
		this.ipdsetting = ipdsetting;
	}
	public String getTotalsetting() {
		return totalsetting;
	}
	public void setTotalsetting(String totalsetting) {
		this.totalsetting = totalsetting;
	}
	public String getOpdappoinmentid() {
		return opdappoinmentid;
	}
	public void setOpdappoinmentid(String opdappoinmentid) {
		this.opdappoinmentid = opdappoinmentid;
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
	public String getPunchkarma() {
		return punchkarma;
	}
	public void setPunchkarma(String punchkarma) {
		this.punchkarma = punchkarma;
	}
	public String getProcedureid() {
		return procedureid;
	}
	public void setProcedureid(String procedureid) {
		this.procedureid = procedureid;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public String getReqnote() {
		return reqnote;
	}
	public void setReqnote(String reqnote) {
		this.reqnote = reqnote;
	}
	public String getPunchnote() {
		return punchnote;
	}
	public void setPunchnote(String punchnote) {
		this.punchnote = punchnote;
	}
	public String getChief_complains() {
		return chief_complains;
	}
	public void setChief_complains(String chief_complains) {
		this.chief_complains = chief_complains;
	}
	public StringBuffer getInvestigation() {
		return investigation;
	}
	public void setInvestigation(StringBuffer investigation) {
		this.investigation = investigation;
	}
	public StringBuffer getPriscription() {
		return priscription;
	}
	public void setPriscription(StringBuffer priscription) {
		this.priscription = priscription;
	}
	public double getAdvance() {
		return advance;
	}
	public void setAdvance(double advance) {
		this.advance = advance;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
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
	private int id;
	
	private ArrayList<Modify>ModiAppintmentList;
	private ArrayList<Emr> secondarylist;
	private ArrayList<NotAvailableSlot> deptlist;
	private String diaryUser,selectedids,refertodept;
	private String doctorname;
	private String referto;
	private String title;
	private String refferremark;
	private String drselected;
	private String imgdata;
	private String docid;
	private String patientcategory;
	private String mobstatus;
	private String patienttype;
	private int surgeonid;
	private String opdbooktime;
	private String psurcharge;
	private String panetcharge;
	private String departmentname;
	private String anifees;
	private String speciality;
	private String sic;
	private String opdbookdate;
	private String mastername;
	private String vaccinationduration;
	private String givendate;
	private String duedate;
	private int smsflag;
	private int consumption_status;
	private int vaccinationid;
	private String vac_productid;
	private String refferedfrom;
	private String apptdate,opdAbbrId;
	private String referredfromdept;
	private String secondarydoc;
	private int newpatient=0;
	private int oldpatient=0;
	private String name,bloodgroup,fathername,parentname,parentaddress,mobileno,institudename,institudeaddress,caste,education;
    private String subcaste,cet,physicsmax,physicsmark,physicsper,chemistrymax,chemistrymark,chemistryper,biologymax,biologymark,biologyper,englishmark,englishmax,englishper,totalmax,totalmark,totalper;
	private int preDate;
	private String opdSequnce;
	private int deptOpdId;
	private String consumeid;
	private String dailyreport;
	private String city;
	private String referetohospital;
	private String referdate;
	private String commercial ;
	private String sourceclinic_id;
	private String referCity;
	private String smallClinicName;
	private int deleted;
	private int confirm;
	private int patient_status;
	private String patient_status_name;
	private double consultantShare;
	private double clinicShare,total_bill_amt, paid_clinic_amt, paid_consulatant_amt;
	private int paid_status;
	private String clinic_refer_userid;
	private String invoiceNote;
	private String disciplineName;
	private String sittingName;
	private String procedureMasterName;
	private String procedureName;
	private int referStatus;
	private int folloupGiven;
	private String sittingnum;
	private String admisionid;
	private String diagnosis;
	private int gendercount;
	private int malecount,femalecount;
	private String pstatus;
	private StringBuffer priscription,investigation,consultnotes;
	private String chief_complains;
	private String reqid,reqnote,punchnote,procedureid,punchkarma;
	private String karma,procedurebams;
	private String color;
	private String opdappoinmentid;
	private String physio_ipd;
	private String bed_ward,Hosp_name;
	private String mbbs_seq_no;
	private String totalsetting;
	private String ipdsetting;
	private String fullname;
    private String datecolor;
    private String masterid;
	private ArrayList<NotAvailableSlot>departmentIPDPreviewList;
	private ArrayList<NotAvailableSlot> sessionsittingAndFolloUpList;
	private boolean ot;
	
	
	public boolean isOt() {
		return ot;
	}
	public void setOt(boolean ot) {
		this.ot = ot;
	}
	public ArrayList<NotAvailableSlot> getSessionsittingAndFolloUpList() {
		return sessionsittingAndFolloUpList;
	}
	public void setSessionsittingAndFolloUpList(ArrayList<NotAvailableSlot> sessionsittingAndFolloUpList) {
		this.sessionsittingAndFolloUpList = sessionsittingAndFolloUpList;
	}
	public ArrayList<NotAvailableSlot> getDepartmentIPDPreviewList() {
		return departmentIPDPreviewList;
	}
	public void setDepartmentIPDPreviewList(ArrayList<NotAvailableSlot> departmentIPDPreviewList) {
		this.departmentIPDPreviewList = departmentIPDPreviewList;
	}
	public String getMbbs_seq_no() {
		return mbbs_seq_no;
	}
	public void setMbbs_seq_no(String mbbs_seq_no) {
		this.mbbs_seq_no = mbbs_seq_no;
	}
	public String getBed_ward() {
		return bed_ward;
	}
	public void setBed_ward(String bed_ward) {
		this.bed_ward = bed_ward;
	}
	public String getHosp_name() {
		return Hosp_name;
	}
	public void setHosp_name(String hosp_name) {
		Hosp_name = hosp_name;
	}
	public String getPhysio_ipd() {
		return physio_ipd;
	}
	public void setPhysio_ipd(String physio_ipd) {
		this.physio_ipd = physio_ipd;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getAdmisionid() {
		return admisionid;
	}
	public void setAdmisionid(String admisionid) {
		this.admisionid = admisionid;
	}
	public String getSittingnum() {
		return sittingnum;
	}
	public void setSittingnum(String sittingnum) {
		this.sittingnum = sittingnum;
	}
	public String getReferuserid() {
		return referuserid;
	}
	public void setReferuserid(String referuserid) {
		this.referuserid = referuserid;
	}
	private String disease;
	private String referuserid;
   
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public int getFolloupGiven() {
		return folloupGiven;
	}
	public void setFolloupGiven(int folloupGiven) {
		this.folloupGiven = folloupGiven;
	}
	public int getReferStatus() {
		return referStatus;
	}
	public void setReferStatus(int referStatus) {
		this.referStatus = referStatus;
	}
	public String getDisciplineName() {
		return disciplineName;
	}
	public void setDisciplineName(String disciplineName) {
		this.disciplineName = disciplineName;
	}
	public String getSittingName() {
		return sittingName;
	}
	public void setSittingName(String sittingName) {
		this.sittingName = sittingName;
	}
	public String getProcedureMasterName() {
		return procedureMasterName;
	}
	public void setProcedureMasterName(String procedureMasterName) {
		this.procedureMasterName = procedureMasterName;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getInvoiceNote() {
		return invoiceNote;
	}
	public void setInvoiceNote(String invoiceNote) {
		this.invoiceNote = invoiceNote;
	}
	public String getClinic_refer_userid() {
		return clinic_refer_userid;
	}
	public void setClinic_refer_userid(String clinic_refer_userid) {
		this.clinic_refer_userid = clinic_refer_userid;
	}
	public double getTotal_bill_amt() {
		return total_bill_amt;
	}
	public void setTotal_bill_amt(double total_bill_amt) {
		this.total_bill_amt = total_bill_amt;
	}
	public double getPaid_clinic_amt() {
		return paid_clinic_amt;
	}
	public void setPaid_clinic_amt(double paid_clinic_amt) {
		this.paid_clinic_amt = paid_clinic_amt;
	}
	public double getPaid_consulatant_amt() {
		return paid_consulatant_amt;
	}
	public void setPaid_consulatant_amt(double paid_consulatant_amt) {
		this.paid_consulatant_amt = paid_consulatant_amt;
	}
	public int getPaid_status() {
		return paid_status;
	}
	public void setPaid_status(int paid_status) {
		this.paid_status = paid_status;
	}
	public double getConsultantShare() {
		return consultantShare;
	}
	public void setConsultantShare(double consultantShare) {
		this.consultantShare = consultantShare;
	}
	public double getClinicShare() {
		return clinicShare;
	}
	public void setClinicShare(double clinicShare) {
		this.clinicShare = clinicShare;
	}
	public String getPatient_status_name() {
		return patient_status_name;
	}
	public void setPatient_status_name(String patient_status_name) {
		this.patient_status_name = patient_status_name;
	}
	public int getPatient_status() {
		return patient_status;
	}
	public void setPatient_status(int patient_status) {
		this.patient_status = patient_status;
	}
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getSmallClinicName() {
		return smallClinicName;
	}
	public void setSmallClinicName(String smallClinicName) {
		this.smallClinicName = smallClinicName;
	}
	public String getReferCity() {
		return referCity;
	}
	public void setReferCity(String referCity) {
		this.referCity = referCity;
	}
	public String getSourceclinic_id() {
		return sourceclinic_id;
	}
	public void setSourceclinic_id(String sourceclinic_id) {
		this.sourceclinic_id = sourceclinic_id;
	}
	public String getCommercial() {
		return commercial;
	}
	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}
	public String getReferdate() {
		return referdate;
	}
	public void setReferdate(String referdate) {
		this.referdate = referdate;
	}
	public String getReferetohospital() {
		return referetohospital;
	}
	public void setReferetohospital(String referetohospital) {
		this.referetohospital = referetohospital;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDailyreport() {
		return dailyreport;
	}
	public void setDailyreport(String dailyreport) {
		this.dailyreport = dailyreport;
	}
	public String getConsumeid() {
		return consumeid;
	}
	public void setConsumeid(String consumeid) {
		this.consumeid = consumeid;
	}
	public int getDeptOpdId() {
		return deptOpdId;
	}
	public void setDeptOpdId(int deptOpdId) {
		this.deptOpdId = deptOpdId;
	}
	public String getOpdSequnce() {
		return opdSequnce;
	}
	public void setOpdSequnce(String opdSequnce) {
		this.opdSequnce = opdSequnce;
	}
	public int getPreDate() {
		return preDate;
	}
	public void setPreDate(int preDate) {
		this.preDate = preDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public String getParentaddress() {
		return parentaddress;
	}
	public void setParentaddress(String parentaddress) {
		this.parentaddress = parentaddress;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getInstitudename() {
		return institudename;
	}
	public void setInstitudename(String institudename) {
		this.institudename = institudename;
	}
	public String getInstitudeaddress() {
		return institudeaddress;
	}
	public void setInstitudeaddress(String institudeaddress) {
		this.institudeaddress = institudeaddress;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSubcaste() {
		return subcaste;
	}
	public void setSubcaste(String subcaste) {
		this.subcaste = subcaste;
	}
	public String getCet() {
		return cet;
	}
	public void setCet(String cet) {
		this.cet = cet;
	}
	public String getPhysicsmax() {
		return physicsmax;
	}
	public void setPhysicsmax(String physicsmax) {
		this.physicsmax = physicsmax;
	}
	public String getPhysicsmark() {
		return physicsmark;
	}
	public void setPhysicsmark(String physicsmark) {
		this.physicsmark = physicsmark;
	}
	public String getPhysicsper() {
		return physicsper;
	}
	public void setPhysicsper(String physicsper) {
		this.physicsper = physicsper;
	}
	public String getChemistrymax() {
		return chemistrymax;
	}
	public void setChemistrymax(String chemistrymax) {
		this.chemistrymax = chemistrymax;
	}
	public String getChemistrymark() {
		return chemistrymark;
	}
	public void setChemistrymark(String chemistrymark) {
		this.chemistrymark = chemistrymark;
	}
	public String getChemistryper() {
		return chemistryper;
	}
	public void setChemistryper(String chemistryper) {
		this.chemistryper = chemistryper;
	}
	public String getBiologymax() {
		return biologymax;
	}
	public void setBiologymax(String biologymax) {
		this.biologymax = biologymax;
	}
	public String getBiologymark() {
		return biologymark;
	}
	public void setBiologymark(String biologymark) {
		this.biologymark = biologymark;
	}
	public String getBiologyper() {
		return biologyper;
	}
	public void setBiologyper(String biologyper) {
		this.biologyper = biologyper;
	}
	public String getEnglishmark() {
		return englishmark;
	}
	public void setEnglishmark(String englishmark) {
		this.englishmark = englishmark;
	}
	public String getEnglishmax() {
		return englishmax;
	}
	public void setEnglishmax(String englishmax) {
		this.englishmax = englishmax;
	}
	public String getEnglishper() {
		return englishper;
	}
	public void setEnglishper(String englishper) {
		this.englishper = englishper;
	}
	public String getTotalmax() {
		return totalmax;
	}
	public void setTotalmax(String totalmax) {
		this.totalmax = totalmax;
	}
	public String getTotalmark() {
		return totalmark;
	}
	public void setTotalmark(String totalmark) {
		this.totalmark = totalmark;
	}
	public String getTotalper() {
		return totalper;
	}
	public void setTotalper(String totalper) {
		this.totalper = totalper;
	}
	public int getNewpatient() {
		return newpatient;
	}
	public void setNewpatient(int newpatient) {
		this.newpatient = newpatient;
	}
	public String getVac_productid() {
		return vac_productid;
	}
	public void setVac_productid(String vac_productid) {
		this.vac_productid = vac_productid;
	}
	public int getVaccinationid() {
		return vaccinationid;
	}
	public void setVaccinationid(int vaccinationid) {
		this.vaccinationid = vaccinationid;
	}
	public int getConsumption_status() {
		return consumption_status;
	}
	public void setConsumption_status(int consumption_status) {
		this.consumption_status = consumption_status;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	public String getVaccinationduration() {
		return vaccinationduration;
	}
	public void setVaccinationduration(String vaccinationduration) {
		this.vaccinationduration = vaccinationduration;
	}
	private int chrgstatus;
	private int appt_type;
	private int patientisseen;
	 private String re_unaided_d;
	    private String 	re_unaided_n;
	    private String 	re_withglass_d;
	    private String 	re_withglass_n;
	    private String 	re_gtph_d;
	    private String re_gtph_n;
	    private String le_unaided_d;
	    private String le_unaided_n;
	    private String le_withglass_d;
	    private String le_withglass_n;
	    private String le_gtph_d;
	    private String le_gtph_n;
	    private String air_r;
	    private String air_l;
	    private String perkins_r;
	    private String perkins_l;
	    private String appl_r;
	    private String appl_l;
	    private String keratometry1;
	    private String keratometry2;
	    private String keratometry3;
	    private String keratometry4;
	    private String keratometry5;
	    private String keratometry6;
	    private String keratometry7;
	    private String keratometry8;
		private String re_usingglass_s;
	    private String re_usingglass_c;
	    private String re_usingglass_a;
	    private String re_usingglass_va;
	    private String re_usingglass_nv;
	    private String re_usingglass_add;
		private String re_ar_s;
		private String re_ar_c;
		private String re_ar_a;
		private String re_ar_va;
		private String re_ar_nv;
		private String re_ar_add;
		private String re_ace_s;
		private String re_ace_c;
		private String re_ace_a;
		private String re_ace_va;
		private String re_ace_nv;
		private String re_ace_add;
		private String le_usingglass_s;
		private String le_usingglass_c;
		private String le_usingglass_a;
		private String le_usingglass_va;
		private String le_usingglass_nv;
		private String le_usingglass_add;
		private String le_ar_s;
		private String le_ar_c;
		private String le_ar_a;
		private String le_ar_va;
		private String le_ar_nv;
		private String le_ar_add;
		private String le_ace_s;
		private String le_ace_c;
		private String le_ace_a;
		private String le_ace_va;
		private String le_ace_nv;
		private String le_ace_add;
		private String lens_left1;
		private String lens_left2;
		private String diagnosisarea;
		private String lens_right1;
		private String lens_right2;
		private String followup;
	    private String userid;
	    private String datetime;
	    private String opdid;
	    private String abrivationid;
	    private String mobno;
	    private String address;
	    private String ipdid;
	    private String tempaddmorecount;
	    private int count;
	    private String total;
	    private String bnkname;
	    private String assistaffcharge;
	    
	    private String uhid;
	    
	    private String slotstime;
	    
	    private String invoiceid;
	    
	    private int drcompleted;
	    
	    
	    private String pbodytemplate;
		private String pbodyeditedtmplate;
		
		private String district;
		private String opdnt;
		private String completedcnt;
		private String invoicedcnt,dob;
		
		private int mobstatusnew;
		private int med_doc_verify,reception_vid_verify,pending_verify,doctor_vid_verify;
		private String pending_remark,doctor_vid_reject_remark;
		private String weekday;
		private String oldclientid;
		private String diaryusername;
		
		public String getDiaryusername() {
			return diaryusername;
		}
		public void setDiaryusername(String diaryusername) {
			this.diaryusername = diaryusername;
		}
		public String getOldclientid() {
			return oldclientid;
		}
		public void setOldclientid(String oldclientid) {
			this.oldclientid = oldclientid;
		}
		public String getWeekday() {
			return weekday;
		}
		public void setWeekday(String weekday) {
			this.weekday = weekday;
		}
		public int getMed_doc_verify() {
			return med_doc_verify;
		}
		public void setMed_doc_verify(int med_doc_verify) {
			this.med_doc_verify = med_doc_verify;
		}
		public int getReception_vid_verify() {
			return reception_vid_verify;
		}
		public void setReception_vid_verify(int reception_vid_verify) {
			this.reception_vid_verify = reception_vid_verify;
		}
		public int getPending_verify() {
			return pending_verify;
		}
		public void setPending_verify(int pending_verify) {
			this.pending_verify = pending_verify;
		}
		public String getPending_remark() {
			return pending_remark;
		}
		public void setPending_remark(String pending_remark) {
			this.pending_remark = pending_remark;
		}
		public int getMobstatusnew() {
			return mobstatusnew;
		}
		public void setMobstatusnew(int mobstatusnew) {
			this.mobstatusnew = mobstatusnew;
		}
		public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
		}
		public String getOpdnt() {
			return opdnt;
		}
		public void setOpdnt(String opdnt) {
			this.opdnt = opdnt;
		}
		public String getCompletedcnt() {
			return completedcnt;
		}
		public void setCompletedcnt(String completedcnt) {
			this.completedcnt = completedcnt;
		}
		public String getInvoicedcnt() {
			return invoicedcnt;
		}
		public void setInvoicedcnt(String invoicedcnt) {
			this.invoicedcnt = invoicedcnt;
		}
	public String getPbodytemplate() {
			return pbodytemplate;
		}
		public void setPbodytemplate(String pbodytemplate) {
			this.pbodytemplate = pbodytemplate;
		}
		public String getPbodyeditedtmplate() {
			return pbodyeditedtmplate;
		}
		public void setPbodyeditedtmplate(String pbodyeditedtmplate) {
			this.pbodyeditedtmplate = pbodyeditedtmplate;
		}
	public int getDrcompleted() {
			return drcompleted;
		}
		public void setDrcompleted(int drcompleted) {
			this.drcompleted = drcompleted;
		}
	public String getInvoiceid() {
			return invoiceid;
		}
		public void setInvoiceid(String invoiceid) {
			this.invoiceid = invoiceid;
		}
	public String getSlotstime() {
			return slotstime;
		}
		public void setSlotstime(String slotstime) {
			this.slotstime = slotstime;
		}
	public String getUhid() {
			return uhid;
		}
		public void setUhid(String uhid) {
			this.uhid = uhid;
		}
	public String getAssistaffcharge() {
			return assistaffcharge;
		}
		public void setAssistaffcharge(String assistaffcharge) {
			this.assistaffcharge = assistaffcharge;
		}
	public String getBnkname() {
			return bnkname;
		}
		public void setBnkname(String bnkname) {
			this.bnkname = bnkname;
		}
	public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public String getTotal() {
			return total;
		}
		public void setTotal(String total) {
			this.total = total;
		}
	public String getTempaddmorecount() {
			return tempaddmorecount;
		}
		public void setTempaddmorecount(String tempaddmorecount) {
			this.tempaddmorecount = tempaddmorecount;
		}
	public String getIpdid() {
			return ipdid;
		}
		public void setIpdid(String ipdid) {
			this.ipdid = ipdid;
		}
	public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
	public String getMobno() {
			return mobno;
		}
		public void setMobno(String mobno) {
			this.mobno = mobno;
		}
	public String getAbrivationid() {
			return abrivationid;
		}
		public void setAbrivationid(String abrivationid) {
			this.abrivationid = abrivationid;
		}
	public int getSurgeonid() {
			return surgeonid;
		}
		public void setSurgeonid(int surgeonid) {
			this.surgeonid = surgeonid;
		}
	public String getOpdid() {
			return opdid;
		}
		public void setOpdid(String opdid) {
			this.opdid = opdid;
		}
	public String getRe_unaided_d() {
			return re_unaided_d;
		}
		public void setRe_unaided_d(String re_unaided_d) {
			this.re_unaided_d = re_unaided_d;
		}
		public String getRe_unaided_n() {
			return re_unaided_n;
		}
		public void setRe_unaided_n(String re_unaided_n) {
			this.re_unaided_n = re_unaided_n;
		}
		public String getRe_withglass_d() {
			return re_withglass_d;
		}
		public void setRe_withglass_d(String re_withglass_d) {
			this.re_withglass_d = re_withglass_d;
		}
		public String getRe_withglass_n() {
			return re_withglass_n;
		}
		public void setRe_withglass_n(String re_withglass_n) {
			this.re_withglass_n = re_withglass_n;
		}
		public String getRe_gtph_d() {
			return re_gtph_d;
		}
		public void setRe_gtph_d(String re_gtph_d) {
			this.re_gtph_d = re_gtph_d;
		}
		public String getRe_gtph_n() {
			return re_gtph_n;
		}
		public void setRe_gtph_n(String re_gtph_n) {
			this.re_gtph_n = re_gtph_n;
		}
		public String getLe_unaided_d() {
			return le_unaided_d;
		}
		public void setLe_unaided_d(String le_unaided_d) {
			this.le_unaided_d = le_unaided_d;
		}
		public String getLe_unaided_n() {
			return le_unaided_n;
		}
		public void setLe_unaided_n(String le_unaided_n) {
			this.le_unaided_n = le_unaided_n;
		}
		public String getLe_withglass_d() {
			return le_withglass_d;
		}
		public void setLe_withglass_d(String le_withglass_d) {
			this.le_withglass_d = le_withglass_d;
		}
		public String getLe_withglass_n() {
			return le_withglass_n;
		}
		public void setLe_withglass_n(String le_withglass_n) {
			this.le_withglass_n = le_withglass_n;
		}
		public String getLe_gtph_d() {
			return le_gtph_d;
		}
		public void setLe_gtph_d(String le_gtph_d) {
			this.le_gtph_d = le_gtph_d;
		}
		public String getLe_gtph_n() {
			return le_gtph_n;
		}
		public void setLe_gtph_n(String le_gtph_n) {
			this.le_gtph_n = le_gtph_n;
		}
		public String getAir_r() {
			return air_r;
		}
		public void setAir_r(String air_r) {
			this.air_r = air_r;
		}
		public String getAir_l() {
			return air_l;
		}
		public void setAir_l(String air_l) {
			this.air_l = air_l;
		}
		public String getPerkins_r() {
			return perkins_r;
		}
		public void setPerkins_r(String perkins_r) {
			this.perkins_r = perkins_r;
		}
		public String getPerkins_l() {
			return perkins_l;
		}
		public void setPerkins_l(String perkins_l) {
			this.perkins_l = perkins_l;
		}
		public String getAppl_r() {
			return appl_r;
		}
		public void setAppl_r(String appl_r) {
			this.appl_r = appl_r;
		}
		public String getAppl_l() {
			return appl_l;
		}
		public void setAppl_l(String appl_l) {
			this.appl_l = appl_l;
		}
		public String getKeratometry1() {
			return keratometry1;
		}
		public void setKeratometry1(String keratometry1) {
			this.keratometry1 = keratometry1;
		}
		public String getKeratometry2() {
			return keratometry2;
		}
		public void setKeratometry2(String keratometry2) {
			this.keratometry2 = keratometry2;
		}
		public String getKeratometry3() {
			return keratometry3;
		}
		public void setKeratometry3(String keratometry3) {
			this.keratometry3 = keratometry3;
		}
		public String getKeratometry4() {
			return keratometry4;
		}
		public void setKeratometry4(String keratometry4) {
			this.keratometry4 = keratometry4;
		}
		public String getKeratometry5() {
			return keratometry5;
		}
		public void setKeratometry5(String keratometry5) {
			this.keratometry5 = keratometry5;
		}
		public String getKeratometry6() {
			return keratometry6;
		}
		public void setKeratometry6(String keratometry6) {
			this.keratometry6 = keratometry6;
		}
		public String getKeratometry7() {
			return keratometry7;
		}
		public void setKeratometry7(String keratometry7) {
			this.keratometry7 = keratometry7;
		}
		public String getKeratometry8() {
			return keratometry8;
		}
		public void setKeratometry8(String keratometry8) {
			this.keratometry8 = keratometry8;
		}
		public String getRe_usingglass_s() {
			return re_usingglass_s;
		}
		public void setRe_usingglass_s(String re_usingglass_s) {
			this.re_usingglass_s = re_usingglass_s;
		}
		public String getRe_usingglass_c() {
			return re_usingglass_c;
		}
		public void setRe_usingglass_c(String re_usingglass_c) {
			this.re_usingglass_c = re_usingglass_c;
		}
		public String getRe_usingglass_a() {
			return re_usingglass_a;
		}
		public void setRe_usingglass_a(String re_usingglass_a) {
			this.re_usingglass_a = re_usingglass_a;
		}
		public String getRe_usingglass_va() {
			return re_usingglass_va;
		}
		public void setRe_usingglass_va(String re_usingglass_va) {
			this.re_usingglass_va = re_usingglass_va;
		}
		public String getRe_usingglass_nv() {
			return re_usingglass_nv;
		}
		public void setRe_usingglass_nv(String re_usingglass_nv) {
			this.re_usingglass_nv = re_usingglass_nv;
		}
		public String getRe_usingglass_add() {
			return re_usingglass_add;
		}
		public void setRe_usingglass_add(String re_usingglass_add) {
			this.re_usingglass_add = re_usingglass_add;
		}
		public String getRe_ar_s() {
			return re_ar_s;
		}
		public void setRe_ar_s(String re_ar_s) {
			this.re_ar_s = re_ar_s;
		}
		public String getRe_ar_c() {
			return re_ar_c;
		}
		public void setRe_ar_c(String re_ar_c) {
			this.re_ar_c = re_ar_c;
		}
		public String getRe_ar_a() {
			return re_ar_a;
		}
		public void setRe_ar_a(String re_ar_a) {
			this.re_ar_a = re_ar_a;
		}
		public String getRe_ar_va() {
			return re_ar_va;
		}
		public void setRe_ar_va(String re_ar_va) {
			this.re_ar_va = re_ar_va;
		}
		public String getRe_ar_nv() {
			return re_ar_nv;
		}
		public void setRe_ar_nv(String re_ar_nv) {
			this.re_ar_nv = re_ar_nv;
		}
		public String getRe_ar_add() {
			return re_ar_add;
		}
		public void setRe_ar_add(String re_ar_add) {
			this.re_ar_add = re_ar_add;
		}
		public String getRe_ace_s() {
			return re_ace_s;
		}
		public void setRe_ace_s(String re_ace_s) {
			this.re_ace_s = re_ace_s;
		}
		public String getRe_ace_c() {
			return re_ace_c;
		}
		public void setRe_ace_c(String re_ace_c) {
			this.re_ace_c = re_ace_c;
		}
		public String getRe_ace_a() {
			return re_ace_a;
		}
		public void setRe_ace_a(String re_ace_a) {
			this.re_ace_a = re_ace_a;
		}
		public String getRe_ace_va() {
			return re_ace_va;
		}
		public void setRe_ace_va(String re_ace_va) {
			this.re_ace_va = re_ace_va;
		}
		public String getRe_ace_nv() {
			return re_ace_nv;
		}
		public void setRe_ace_nv(String re_ace_nv) {
			this.re_ace_nv = re_ace_nv;
		}
		public String getRe_ace_add() {
			return re_ace_add;
		}
		public void setRe_ace_add(String re_ace_add) {
			this.re_ace_add = re_ace_add;
		}
		public String getLe_usingglass_s() {
			return le_usingglass_s;
		}
		public void setLe_usingglass_s(String le_usingglass_s) {
			this.le_usingglass_s = le_usingglass_s;
		}
		public String getLe_usingglass_c() {
			return le_usingglass_c;
		}
		public void setLe_usingglass_c(String le_usingglass_c) {
			this.le_usingglass_c = le_usingglass_c;
		}
		public String getLe_usingglass_a() {
			return le_usingglass_a;
		}
		public void setLe_usingglass_a(String le_usingglass_a) {
			this.le_usingglass_a = le_usingglass_a;
		}
		public String getLe_usingglass_va() {
			return le_usingglass_va;
		}
		public void setLe_usingglass_va(String le_usingglass_va) {
			this.le_usingglass_va = le_usingglass_va;
		}
		public String getLe_usingglass_nv() {
			return le_usingglass_nv;
		}
		public void setLe_usingglass_nv(String le_usingglass_nv) {
			this.le_usingglass_nv = le_usingglass_nv;
		}
		public String getLe_usingglass_add() {
			return le_usingglass_add;
		}
		public void setLe_usingglass_add(String le_usingglass_add) {
			this.le_usingglass_add = le_usingglass_add;
		}
		public String getLe_ar_s() {
			return le_ar_s;
		}
		public void setLe_ar_s(String le_ar_s) {
			this.le_ar_s = le_ar_s;
		}
		public String getLe_ar_c() {
			return le_ar_c;
		}
		public void setLe_ar_c(String le_ar_c) {
			this.le_ar_c = le_ar_c;
		}
		public String getLe_ar_a() {
			return le_ar_a;
		}
		public void setLe_ar_a(String le_ar_a) {
			this.le_ar_a = le_ar_a;
		}
		public String getLe_ar_va() {
			return le_ar_va;
		}
		public void setLe_ar_va(String le_ar_va) {
			this.le_ar_va = le_ar_va;
		}
		public String getLe_ar_nv() {
			return le_ar_nv;
		}
		public void setLe_ar_nv(String le_ar_nv) {
			this.le_ar_nv = le_ar_nv;
		}
		public String getLe_ar_add() {
			return le_ar_add;
		}
		public void setLe_ar_add(String le_ar_add) {
			this.le_ar_add = le_ar_add;
		}
		public String getLe_ace_s() {
			return le_ace_s;
		}
		public void setLe_ace_s(String le_ace_s) {
			this.le_ace_s = le_ace_s;
		}
		public String getLe_ace_c() {
			return le_ace_c;
		}
		public void setLe_ace_c(String le_ace_c) {
			this.le_ace_c = le_ace_c;
		}
		public String getLe_ace_a() {
			return le_ace_a;
		}
		public void setLe_ace_a(String le_ace_a) {
			this.le_ace_a = le_ace_a;
		}
		public String getLe_ace_va() {
			return le_ace_va;
		}
		public void setLe_ace_va(String le_ace_va) {
			this.le_ace_va = le_ace_va;
		}
		public String getLe_ace_nv() {
			return le_ace_nv;
		}
		public void setLe_ace_nv(String le_ace_nv) {
			this.le_ace_nv = le_ace_nv;
		}
		public String getLe_ace_add() {
			return le_ace_add;
		}
		public void setLe_ace_add(String le_ace_add) {
			this.le_ace_add = le_ace_add;
		}
		public String getLens_left1() {
			return lens_left1;
		}
		public void setLens_left1(String lens_left1) {
			this.lens_left1 = lens_left1;
		}
		public String getLens_left2() {
			return lens_left2;
		}
		public void setLens_left2(String lens_left2) {
			this.lens_left2 = lens_left2;
		}
		public String getDiagnosisarea() {
			return diagnosisarea;
		}
		public void setDiagnosisarea(String diagnosisarea) {
			this.diagnosisarea = diagnosisarea;
		}
		public String getLens_right1() {
			return lens_right1;
		}
		public void setLens_right1(String lens_right1) {
			this.lens_right1 = lens_right1;
		}
		public String getLens_right2() {
			return lens_right2;
		}
		public void setLens_right2(String lens_right2) {
			this.lens_right2 = lens_right2;
		}
		public String getFollowup() {
			return followup;
		}
		public void setFollowup(String followup) {
			this.followup = followup;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getDatetime() {
			return datetime;
		}
		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}
	public String getSic() {
		return sic;
	}
	public void setSic(String sic) {
		this.sic = sic;
	}
	public String getPsurcharge() {
		return psurcharge;
	}
	public void setPsurcharge(String psurcharge) {
		this.psurcharge = psurcharge;
	}
	public String getPanetcharge() {
		return panetcharge;
	}
	public void setPanetcharge(String panetcharge) {
		this.panetcharge = panetcharge;
	}
	public String getAnifees() {
		return anifees;
	}
	public void setAnifees(String anifees) {
		this.anifees = anifees;
	}
	public String getImgdata() {
		return imgdata;
	}
	public void setImgdata(String imgdata) {
		this.imgdata = imgdata;
	}
	String currentDate = DateTimeUtils.getDateinSimpleStringFormate(new Date());
	String dateTemp[] = currentDate.split(" ");
	String temp[] =  dateTemp[0].split("-");
	private String commencing = temp[2] + "/" + temp[1] + "/"  + temp[0];
	
	private ArrayList<DiaryManagement>userList;

	private ArrayList<String>startTimeList;
	private int appointmentid;
	
	private ArrayList<String>endTimeList;
	private String category;
	
	private ArrayList<String> apmtDurationList;
	
	private String actionType;
	private String locationColor;
	private String sTime;
	private String endTime;
	private String apmtDuration;
	private ArrayList<Location>locationList;
	private String location;
	private String dept;
	private String room;
	private String client;
	private String apmtType;
	private String notes;
	private int apmtSlotId;
	private int diaryUserId = 0;
	private String apmttypetext;
	private String chargeamout;
	private String otmsg;
	private String otaccname;
	
	public String getOtaccname() {
		return otaccname;
	}
	public void setOtaccname(String otaccname) {
		this.otaccname = otaccname;
	}
	public String getOtmsg() {
		return otmsg;
	}
	public void setOtmsg(String otmsg) {
		this.otmsg = otmsg;
	}
	public String getChargeamout() {
		return chargeamout;
	}
	public void setChargeamout(String chargeamout) {
		this.chargeamout = chargeamout;
	}
	private boolean sent;
	
	private String imagename;
	
	private String sentdate;
	
	private String sentNote = "";
	
	private String disciplineid;
	
	private String isReportsent;
	
	private String treatmentEpisodeName = "";
	
	private String sessions = "";
	
	private boolean apmtSlotStimeEmpty;
	
	private String dischrgeOutcomes;
	
	private String dischargeStatus;
	
	private int trtmentStatus;
	
	
	private String refDate = "";
	
	private String dischargedate;
	
	private String nhsNumber;
	
	private String hour;
	
	private String minute;
	
	private String stafflistid;
	
	private int otid;
	
	private String otname;
	
	private String work;
	
	private String workcompleted;
	
	//ot variables
	private String otplan;
	private String procedure;
	private String surgeon;
	private String anesthesia;
	private String ipdno;
	private String wardid;
	
	private String asistantdoclist;
	
	private int blockot;
	private String agegender;
	private String wardbed;
	private String admitdate;
	
	 private String timeofincision;
	 private String ansintime;
	 private String otnotes;
	 
	 private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOtnotes() {
		return otnotes;
	}
	public void setOtnotes(String otnotes) {
		this.otnotes = otnotes;
	}
	public String getAdmitdate() {
		return admitdate;
	}
	public void setAdmitdate(String admitdate) {
		this.admitdate = admitdate;
	}
	public int getBlockot() {
		return blockot;
	}
	public void setBlockot(int blockot) {
		this.blockot = blockot;
	}
	public String getAsistantdoclist() {
		return asistantdoclist;
	}
	public void setAsistantdoclist(String asistantdoclist) {
		this.asistantdoclist = asistantdoclist;
	}
	public String getOtplan() {
		return otplan;
	}
	public void setOtplan(String otplan) {
		this.otplan = otplan;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
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
	public String getIpdno() {
		return ipdno;
	}
	public void setIpdno(String ipdno) {
		this.ipdno = ipdno;
	}
	public String getWardid() {
		return wardid;
	}
	public void setWardid(String wardid) {
		this.wardid = wardid;
	}
	public String getWorkcompleted() {
		return workcompleted;
	}
	public void setWorkcompleted(String workcompleted) {
		this.workcompleted = workcompleted;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getOtname() {
		return otname;
	}
	public void setOtname(String otname) {
		this.otname = otname;
	}
	public int getOtid() {
		return otid;
	}
	public void setOtid(int otid) {
		this.otid = otid;
	}
	public String getStafflistid() {
		return stafflistid;
	}
	public void setStafflistid(String stafflistid) {
		this.stafflistid = stafflistid;
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
	public String getNhsNumber() {
		return nhsNumber;
	}
	public void setNhsNumber(String nhsNumber) {
		this.nhsNumber = nhsNumber;
	}
	public String getDischargedate() {
		return dischargedate;
	}
	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
	}
	public String getRefDate() {
		return refDate;
	}
	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}
	public int getTrtmentStatus() {
		return trtmentStatus;
	}
	public void setTrtmentStatus(int trtmentStatus) {
		this.trtmentStatus = trtmentStatus;
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
	public boolean isApmtSlotStimeEmpty() {
		return apmtSlotStimeEmpty;
	}
	public void setApmtSlotStimeEmpty(boolean apmtSlotStimeEmpty) {
		this.apmtSlotStimeEmpty = apmtSlotStimeEmpty;
	}
	public String getSessions() {
		return sessions;
	}
	public void setSessions(String sessions) {
		this.sessions = sessions;
	}
	public String getTreatmentEpisodeName() {
		return treatmentEpisodeName;
	}
	public void setTreatmentEpisodeName(String treatmentEpisodeName) {
		this.treatmentEpisodeName = treatmentEpisodeName;
	}
	public String getIsReportsent() {
		return isReportsent;
	}
	public void setIsReportsent(String isReportsent) {
		this.isReportsent = isReportsent;
	}
	public String getDisciplineid() {
		return disciplineid;
	}
	public void setDisciplineid(String disciplineid) {
		this.disciplineid = disciplineid;
	}
	public boolean isSent() {
		return sent;
	}
	public void setSent(boolean sent) {
		this.sent = sent;
	}
	public String getSentdate() {
		return sentdate;
	}
	public void setSentdate(String sentdate) {
		this.sentdate = sentdate;
	}
	public String getSentNote() {
		return sentNote;
	}
	public void setSentNote(String sentNote) {
		this.sentNote = sentNote;
	}
	public boolean isAppointmentDnaOffset() {
		return appointmentDnaOffset;
	}
	public void setAppointmentDnaOffset(boolean appointmentDnaOffset) {
		this.appointmentDnaOffset = appointmentDnaOffset;
	}
	private int firstApmt;
	
	private int lastApmt;
	
	
	private Client clientDetails;
	public int chkConsultationNote;
	private int apmtCount;
	
	private int ad;
	
	private boolean appointmentDnaOffset;
	
	
	

	public int getAd() {
		return ad;
	}
	public void setAd(int ad) {
		this.ad = ad;
	}
	public int getApmtCount() {
		return apmtCount;
	}
	public void setApmtCount(int apmtCount) {
		this.apmtCount = apmtCount;
	}
	public int getChkConsultationNote() {
		return chkConsultationNote;
	}
	public void setChkConsultationNote(int chkConsultationNote) {
		this.chkConsultationNote = chkConsultationNote;
	}
	public String getTpName() {
		return tpName;
	}
	public void setTpName(String tpName) {
		this.tpName = tpName;
	}
	private UserProfile userDEtails;
	private Location locationDetails;
	
	private boolean chargeCompleted;
	private String condition;
	
	private String checkMailSend;
	
	private String tptypeid;
	
	private String tpnameid;
	
	private String whopay;
	
	private String locid;
	
	private String tpName;
	
	private String dnaReason;
	
	private String dnaNotes;
	
	private int appointmentTypeid;
	
	private boolean dnaOffset;
	
	private String department;
	
	
	private String sitting;
	
	private String date;
	
	private String remark;
	
	private boolean sittingFollowup;
	
	private String user_id;
	
	private String date_time;
	
	private String all_procedure;
	private String procedure_list;
	
	public String getProcedure_list() {
		return procedure_list;
	}
	public void setProcedure_list(String procedure_list) {
		this.procedure_list = procedure_list;
	}
	
	public String getAll_procedure() {
		return all_procedure;
	}
	public void setAll_procedure(String all_procedure) {
		this.all_procedure = all_procedure;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSitting() {
		return sitting;
	}
	public void setSitting(String sitting) {
		this.sitting = sitting;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public boolean isSittingFollowup() {
		return sittingFollowup;
	}
	public void setSittingFollowup(boolean sittingFollowup) {
		this.sittingFollowup = sittingFollowup;
	}
	public boolean isDnaOffset() {
		return dnaOffset;
	}
	public void setDnaOffset(boolean dnaOffset) {
		this.dnaOffset = dnaOffset;
	}
	public int getAppointmentTypeid() {
		return appointmentTypeid;
	}
	public void setAppointmentTypeid(int appointmentTypeid) {
		this.appointmentTypeid = appointmentTypeid;
	}
	public ArrayList<Modify> getModiAppintmentList() {
		return ModiAppintmentList;
	}
	public void setModiAppintmentList(ArrayList<Modify> modiAppintmentList) {
		ModiAppintmentList = modiAppintmentList;
	}
	public String getDnaReason() {
		return dnaReason;
	}
	public void setDnaReason(String dnaReason) {
		this.dnaReason = dnaReason;
	}
	public String getDnaNotes() {
		return dnaNotes;
	}
	public void setDnaNotes(String dnaNotes) {
		this.dnaNotes = dnaNotes;
	}
	public String getLocid() {
		return locid;
	}
	public void setLocid(String locid) {
		this.locid = locid;
	}
	public String getWhopay() {
		return whopay;
	}
	public void setWhopay(String whopay) {
		this.whopay = whopay;
	}
	public String getTptypeid() {
		return tptypeid;
	}
	public void setTptypeid(String tptypeid) {
		this.tptypeid = tptypeid;
	}
	public String getTpnameid() {
		return tpnameid;
	}
	public void setTpnameid(String tpnameid) {
		this.tpnameid = tpnameid;
	}
	public String getCheckMailSend() {
		return checkMailSend;
	}
	public void setCheckMailSend(String checkMailSend) {
		this.checkMailSend = checkMailSend;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	private boolean oldata;
	private String treatmentSessions;
	private String gpname;
	 public String getGpname() {
		return gpname;
	}
	public void setGpname(String gpname) {
		this.gpname = gpname;
	}
	public ArrayList<Client> getGpList() {
		return gpList;
	}
	public void setGpList(ArrayList<Client> gpList) {
		this.gpList = gpList;
	}
	ArrayList<Client> gpList;
	
	
	public boolean isOldata() {
		return oldata;
	}
	public void setOldata(boolean oldata) {
		this.oldata = oldata;
	}
	public boolean isChargeCompleted() {
		return chargeCompleted;
	}
	public void setChargeCompleted(boolean chargeCompleted) {
		this.chargeCompleted = chargeCompleted;
	}
	public Location getLocationDetails() {
		return locationDetails;
	}
	public void setLocationDetails(Location locationDetails) {
		this.locationDetails = locationDetails;
	}
	public Client getClientDetails() {
		return clientDetails;
	}
	public void setClientDetails(Client clientDetails) {
		this.clientDetails = clientDetails;
	}
	public UserProfile getUserDEtails() {
		return userDEtails;
	}
	public void setUserDEtails(UserProfile userDEtails) {
		this.userDEtails = userDEtails;
	}
	public String getPayBy() {
		return payBy;
	}
	public void setPayBy(String payBy) {
		this.payBy = payBy;
	}
	private String payBy;
	
	
	public int getFirstApmt() {
		return firstApmt;
	}
	public void setFirstApmt(int firstApmt) {
		this.firstApmt = firstApmt;
	}
	public int getLastApmt() {
		return lastApmt;
	}
	public void setLastApmt(int lastApmt) {
		this.lastApmt = lastApmt;
	}
	public String getApmttypetext() {
		return apmttypetext;
	}
	public void setApmttypetext(String apmttypetext) {
		this.apmttypetext = apmttypetext;
	}
	public String getTreatmentSessions() {
		return treatmentSessions;
	}
	public void setTreatmentSessions(String treatmentSessions) {
		this.treatmentSessions = treatmentSessions;
	}
	
	private String duration;
	
	private String clientName;
	
	private String treatmentEpisodeId;
	
	private boolean appointmentCompleted;
	
	//block attributes
	
	private String blockslotId;
	private String blockdiaryUserId;
	private String blockuser;
	private String blocklocation;
	private String blockroom;
	private String blockdate;
	private String blocksTime;
	private String blockendTime;
	private String blockapmtDuration;
	private String reasonforblock;
	private String status;
	private String blocknotes;
	
	private String clientId;
	
	private String clientEmail;
	private String practitionerEmail;
	
	private double charge;
	//arrived status
	private int arrivedStatus;
	private boolean dna;
	private String addedBy;
	
	private String usedsession;
	private int tempusedSession;
	private String newEndTime;
	public String getNewEndTime() {
		return newEndTime;
	}
	public void setNewEndTime(String newEndTime) {
		this.newEndTime = newEndTime;
	}
	public int getTempusedSession() {
		return tempusedSession;
	}
	public void setTempusedSession(int tempusedSession) {
		this.tempusedSession = tempusedSession;
	}
	public String getUsedsession() {
		return usedsession;
	}
	public void setUsedsession(String usedsession) {
		this.usedsession = usedsession;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	private String modifiedBy;

	public boolean isDna() {
		return dna;
	}
	public void setDna(boolean dna) {
		this.dna = dna;
	}
	public int getArrivedStatus() {
		return arrivedStatus;
	}
	public void setArrivedStatus(int arrivedStatus) {
		this.arrivedStatus = arrivedStatus;
	}
	public String getBlocknotes() {
		return blocknotes;
	}
	public void setBlocknotes(String blocknotes) {
		this.blocknotes = blocknotes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getDiaryUserId() {
		return diaryUserId;
	}
	public void setDiaryUserId(int diaryUserId) {
		this.diaryUserId = diaryUserId;
	}
	public int getApmtSlotId() {
		return apmtSlotId;
	}
	public void setApmtSlotId(int apmtSlotId) {
		this.apmtSlotId = apmtSlotId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiaryUser() {
		return diaryUser;
	}
	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String[] getDateTemp() {
		return dateTemp;
	}
	public void setDateTemp(String[] dateTemp) {
		this.dateTemp = dateTemp;
	}
	public String[] getTemp() {
		return temp;
	}
	public void setTemp(String[] temp) {
		this.temp = temp;
	}
	public String getCommencing() {
		return commencing;
	}
	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}
	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}
	public ArrayList<String> getStartTimeList() {
		return startTimeList;
	}
	public void setStartTimeList(ArrayList<String> startTimeList) {
		this.startTimeList = startTimeList;
	}
	public ArrayList<String> getEndTimeList() {
		return endTimeList;
	}
	public void setEndTimeList(ArrayList<String> endTimeList) {
		this.endTimeList = endTimeList;
	}
	public ArrayList<String> getApmtDurationList() {
		return apmtDurationList;
	}
	public void setApmtDurationList(ArrayList<String> apmtDurationList) {
		this.apmtDurationList = apmtDurationList;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getSTime() {
		return sTime;
	}
	public void setSTime(String time) {
		sTime = time;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getApmtDuration() {
		return apmtDuration;
	}
	public void setApmtDuration(String apmtDuration) {
		this.apmtDuration = apmtDuration;
	}
	public ArrayList<Location> getLocationList() {
		return locationList;
	}
	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getApmtType() {
		return apmtType;
	}
	public void setApmtType(String apmtType) {
		this.apmtType = apmtType;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getLocationColor() {
		return locationColor;
	}
	public void setLocationColor(String locationColor) {
		this.locationColor = locationColor;
	}
	public String getBlockslotId() {
		return blockslotId;
	}
	public void setBlockslotId(String blockslotId) {
		this.blockslotId = blockslotId;
	}
	public String getBlockdiaryUserId() {
		return blockdiaryUserId;
	}
	public void setBlockdiaryUserId(String blockdiaryUserId) {
		this.blockdiaryUserId = blockdiaryUserId;
	}
	public String getBlockuser() {
		return blockuser;
	}
	public void setBlockuser(String blockuser) {
		this.blockuser = blockuser;
	}
	public String getBlocklocation() {
		return blocklocation;
	}
	public void setBlocklocation(String blocklocation) {
		this.blocklocation = blocklocation;
	}
	public String getBlockroom() {
		return blockroom;
	}
	public void setBlockroom(String blockroom) {
		this.blockroom = blockroom;
	}
	public String getBlockdate() {
		return blockdate;
	}
	public void setBlockdate(String blockdate) {
		this.blockdate = blockdate;
	}
	public String getBlocksTime() {
		return blocksTime;
	}
	public void setBlocksTime(String blocksTime) {
		this.blocksTime = blocksTime;
	}
	public String getBlockendTime() {
		return blockendTime;
	}
	public void setBlockendTime(String blockendTime) {
		this.blockendTime = blockendTime;
	}
	public String getBlockapmtDuration() {
		return blockapmtDuration;
	}
	public void setBlockapmtDuration(String blockapmtDuration) {
		this.blockapmtDuration = blockapmtDuration;
	}
	public String getReasonforblock() {
		return reasonforblock;
	}
	public void setReasonforblock(String reasonforblock) {
		this.reasonforblock = reasonforblock;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getPractitionerEmail() {
		return practitionerEmail;
	}
	public void setPractitionerEmail(String practitionerEmail) {
		this.practitionerEmail = practitionerEmail;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public String getTreatmentEpisodeId() {
		return treatmentEpisodeId;
	}
	public void setTreatmentEpisodeId(String treatmentEpisodeId) {
		this.treatmentEpisodeId = treatmentEpisodeId;
	}
	public boolean isAppointmentCompleted() {
		return appointmentCompleted;
	}
	public void setAppointmentCompleted(boolean appointmentCompleted) {
		this.appointmentCompleted = appointmentCompleted;
	}
	public String getAgegender() {
		return agegender;
	}
	public void setAgegender(String agegender) {
		this.agegender = agegender;
	}
	public String getWardbed() {
		return wardbed;
	}
	public void setWardbed(String wardbed) {
		this.wardbed = wardbed;
	}
	public String getTimeofincision() {
		return timeofincision;
	}
	public void setTimeofincision(String timeofincision) {
		this.timeofincision = timeofincision;
	}
	public String getAnsintime() {
		return ansintime;
	}
	public void setAnsintime(String ansintime) {
		this.ansintime = ansintime;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getAppointmentid() {
		return appointmentid;
	}
	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}

	public int getOpdpmnt() {
		return opdpmnt;
	}
	public void setOpdpmnt(int opdpmnt) {
		this.opdpmnt = opdpmnt;
	}
	public boolean isVaccineApmt() {
		return vaccineApmt;
	}
	public void setVaccineApmt(boolean vaccineApmt) {
		this.vaccineApmt = vaccineApmt;
	}
	private int opdpmnt;

	private boolean vaccineApmt;
	private String discount,payment,debit;









	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getDebit() {
		return debit;
	}
	public void setDebit(String debit) {
		this.debit = debit;
	}
	public int getChrgstatus() {
		return chrgstatus;
	}
	public void setChrgstatus(int chrgstatus) {
		this.chrgstatus = chrgstatus;
	}
	public String getOpdbooktime() {
		return opdbooktime;
	}
	public void setOpdbooktime(String opdbooktime) {
		this.opdbooktime = opdbooktime;
	}
	public String getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public String getMobstatus() {
		return mobstatus;
	}
	public void setMobstatus(String mobstatus) {
		this.mobstatus = mobstatus;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getOpdbookdate() {
		return opdbookdate;
	}
	public void setOpdbookdate(String opdbookdate) {
		this.opdbookdate = opdbookdate;
	}
	public int getDoctor_vid_verify() {
		return doctor_vid_verify;
	}
	public void setDoctor_vid_verify(int doctor_vid_verify) {
		this.doctor_vid_verify = doctor_vid_verify;
	}
	public String getDoctor_vid_reject_remark() {
		return doctor_vid_reject_remark;
	}
	public void setDoctor_vid_reject_remark(String doctor_vid_reject_remark) {
		this.doctor_vid_reject_remark = doctor_vid_reject_remark;
	}
	public int getAppt_type() {
		return appt_type;
	}
	public void setAppt_type(int appt_type) {
		this.appt_type = appt_type;
	}
	public int getPatientisseen() {
		return patientisseen;
	}
	public void setPatientisseen(int patientisseen) {
		this.patientisseen = patientisseen;
	}
	public String getGivendate() {
		return givendate;
	}
	public void setGivendate(String givendate) {
		this.givendate = givendate;
	}
	public int getSmsflag() {
		return smsflag;
	}
	public void setSmsflag(int smsflag) {
		this.smsflag = smsflag;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getRefferedfrom() {
		return refferedfrom;
	}
	public void setRefferedfrom(String refferedfrom) {
		this.refferedfrom = refferedfrom;
	}
	public String getApptdate() {
		return apptdate;
	}
	public void setApptdate(String apptdate) {
		this.apptdate = apptdate;
	}
	public String getReferredfromdept() {
		return referredfromdept;
	}
	public void setReferredfromdept(String referredfromdept) {
		this.referredfromdept = referredfromdept;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getOpdAbbrId() {
		return opdAbbrId;
	}
	public void setOpdAbbrId(String opdAbbrId) {
		this.opdAbbrId = opdAbbrId;
	}
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public String getPatientcategory() {
		return patientcategory;
	}
	public void setPatientcategory(String patientcategory) {
		this.patientcategory = patientcategory;
	}
	public String getPatienttype() {
		return patienttype;
	}
	public void setPatienttype(String patienttype) {
		this.patienttype = patienttype;
	}
	public String getRefferremark() {
		return refferremark;
	}
	public void setRefferremark(String refferremark) {
		this.refferremark = refferremark;
	}
	public String getDrselected() {
		return drselected;
	}
	public void setDrselected(String drselected) {
		this.drselected = drselected;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	public String getSelectedids() {
		return selectedids;
	}
	public void setSelectedids(String selectedids) {
		this.selectedids = selectedids;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRefertodept() {
		return refertodept;
	}
	public void setRefertodept(String refertodept) {
		this.refertodept = refertodept;
	}
	public String getSecondarydoc() {
		return secondarydoc;
	}
	public void setSecondarydoc(String secondarydoc) {
		this.secondarydoc = secondarydoc;
	}
	public ArrayList<Emr> getSecondarylist() {
		return secondarylist;
	}
	public void setSecondarylist(ArrayList<Emr> secondarylist) {
		this.secondarylist = secondarylist;
	}
	public String getReferto() {
		return referto;
	}
	public void setReferto(String referto) {
		this.referto = referto;
	}
	public ArrayList<NotAvailableSlot> getDeptlist() {
		return deptlist;
	}
	public void setDeptlist(ArrayList<NotAvailableSlot> deptlist) {
		this.deptlist = deptlist;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	private String requestDateTime,chargeId;
	private String campArea;
	private String enrollcode;
	private String chargeType;
	private String followupDate;
	private String followupReason;
	private String followupId;
	private double advance,balance;
	
	
	public String getFollowupDate() {
		return followupDate;
	}
	public void setFollowupDate(String followupDate) {
		this.followupDate = followupDate;
	}
	public String getFollowupReason() {
		return followupReason;
	}
	public void setFollowupReason(String followupReason) {
		this.followupReason = followupReason;
	}
	public String getFollowupId() {
		return followupId;
	}
	public void setFollowupId(String followupId) {
		this.followupId = followupId;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getEnrollcode() {
		return enrollcode;
	}
	public void setEnrollcode(String enrollcode) {
		this.enrollcode = enrollcode;
	}
	public String getCampArea() {
		return campArea;
	}
	public void setCampArea(String campArea) {
		this.campArea = campArea;
	}
	private int planid;


	public int getPlanid() {
		return planid;
	}
	public void setPlanid(int planid) {
		this.planid = planid;
	}
	private int day;


	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	private String activeplan;


	public String getActiveplan() {
		return activeplan;
	}
	public void setActiveplan(String activeplan) {
		this.activeplan = activeplan;
	}
	private String regno;


	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
}
