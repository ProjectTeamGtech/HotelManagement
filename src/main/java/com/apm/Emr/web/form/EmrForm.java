package com.apm.Emr.web.form;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Dietary.eu.entity.Dietary;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Emr.eu.entity.InvstTemplate;
import com.apm.Emr.eu.entity.MedicalHistory;
//import com.apm.Emr.eu.entity.Report;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Pacs.eu.entity.Pacs;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;

public class EmrForm {
	public String getCommentwriteup() {
		return commentwriteup;
	}

	public void setCommentwriteup(String commentwriteup) {
		this.commentwriteup = commentwriteup;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getInvestigationtempname() {
		return Investigationtempname;
	}

	public void setInvestigationtempname(String investigationtempname) {
		Investigationtempname = investigationtempname;
	}

	public ArrayList<Master> getInvst_templt_list() {
		return Invst_templt_list;
	}

	public void setInvst_templt_list(ArrayList<Master> invst_templt_list) {
		Invst_templt_list = invst_templt_list;
	}

	public ArrayList<Master> getChief_complaints_list() {
		return Chief_complaints_list;
	}

	public void setChief_complaints_list(ArrayList<Master> chief_complaints_list) {
		Chief_complaints_list = chief_complaints_list;
	}

	public ArrayList<Master> getInvsSectionList() {
		return InvsSectionList;
	}

	public void setInvsSectionList(ArrayList<Master> invsSectionList) {
		InvsSectionList = invsSectionList;
	}

	public String getTemplate_text() {
		return template_text;
	}

	public void setTemplate_text(String template_text) {
		this.template_text = template_text;
	}

	public String getFindings() {
		return findings;
	}

	public void setFindings(String findings) {
		this.findings = findings;
	}
	private String printbr;
	private String emripdid;
	private String hiddenval;
	private boolean hidecolumn;
	private boolean bghsts;
	private String isfromdeptopd;
	private String enrollcode;
	private int statusenrollcode;
	private ArrayList<Master> odmrprocedurelist;
	private ArrayList<Bed> wardlist;
	private ArrayList<Master> requestlocationlist;
	private String requestlocationid;
	private  String phar_pre_balance;
	private boolean dosenotesstauss;
	private boolean nonsystembarcode;
	private String filter_location;
	private String filter_phar_location;
	private String aprovediscids;
	private  ArrayList<Priscription> priscriptionlistnew;
	private int shownavigation;
	private String opddiscount;
	private String ipddiscount;
	private String subcategoryname;
	private String fromdate_time;
	private String todate_time;
	private String findings;
	private String template_text;
	private ArrayList<Master>InvsSectionList;
	private String payee;
	private ArrayList<Master>Chief_complaints_list;
	private ArrayList<Master>Invst_templt_list;
	private String Investigationtempname,comment,commentwriteup;
	private ArrayList<Product>medicinesaleList;
	private String deptopd_id;
	
	public String getDeptopd_id() {
		return deptopd_id;
	}

	public void setDeptopd_id(String deptopd_id) {
		this.deptopd_id = deptopd_id;
	}

	public ArrayList<Product> getMedicinesaleList() {
		return medicinesaleList;
	}

	public void setMedicinesaleList(ArrayList<Product> medicinesaleList) {
		this.medicinesaleList = medicinesaleList;
	}
	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getFromdate_time() {
		return fromdate_time;
	}

	public void setFromdate_time(String fromdate_time) {
		this.fromdate_time = fromdate_time;
	}

	public String getTodate_time() {
		return todate_time;
	}

	public void setTodate_time(String todate_time) {
		this.todate_time = todate_time;
	}
	private double totaldiscount;
	
	public double getTotaldiscount() {
		return totaldiscount;
	}

	public void setTotaldiscount(double totaldiscount) {
		this.totaldiscount = totaldiscount;
	}

	public String getSubcategoryname() {
		return subcategoryname;
	}

	public void setSubcategoryname(String subcategoryname) {
		this.subcategoryname = subcategoryname;
	}
	public String getIpdpatientType() {
		return ipdpatientType;
	}

	public void setIpdpatientType(String ipdpatientType) {
		this.ipdpatientType = ipdpatientType;
	}
	private String pharpatientdiscount;
	private int discountpatienttype;
	private String tokenno;
	private int isfromtokendashbaord;
	private String component;
	private int hideuploadbtn;
	private ArrayList<UserProfile> pharmacydrlist;
	private String phar_practid;
	private String doctor_address;
	private String doctor_qualification;
	private String onlinepaymentreturn;
	private String onlinepayment;
	private String todayonline;
	private String bill_edituserid, bill_editdatetime;
	private ArrayList<Emr> consultationNoteListInv;
	private ArrayList<Emr> consultationNoteListPrisc;
	private ArrayList<Client> patientlist;
	private String patientcategory;
	private String lmh_department;
	private ArrayList<Emr> secsecondarylist;
	private String campArea;
	private String ipdpatientType;
	private int freeqty;
	private String fromhour;
	private String fromminute;
	private ArrayList<Priscription>paymodewisepaymentlist ;
	
	public ArrayList<Priscription> getPaymodewisepaymentlist() {
		return paymodewisepaymentlist;
	}

	public void setPaymodewisepaymentlist(ArrayList<Priscription> paymodewisepaymentlist) {
		this.paymodewisepaymentlist = paymodewisepaymentlist;
	}

	public String getFromhour() {
		return fromhour;
	}

	public void setFromhour(String fromhour) {
		this.fromhour = fromhour;
	}

	public String getFromminute() {
		return fromminute;
	}

	public void setFromminute(String fromminute) {
		this.fromminute = fromminute;
	}

	public int getFreeqty() {
		return freeqty;
	}

	public void setFreeqty(int freeqty) {
		this.freeqty = freeqty;
	}

	public String getPatientoccc() {
		return patientoccc;
	}

	public void setPatientoccc(String patientoccc) {
		this.patientoccc = patientoccc;
	}

	public String getPatientincm() {
		return patientincm;
	}

	public void setPatientincm(String patientincm) {
		this.patientincm = patientincm;
	}

	public String getPatienthusocc() {
		return patienthusocc;
	}

	public void setPatienthusocc(String patienthusocc) {
		this.patienthusocc = patienthusocc;
	}

	public String getPatienthusincome() {
		return patienthusincome;
	}

	public void setPatienthusincome(String patienthusincome) {
		this.patienthusincome = patienthusincome;
	}

	public String getPatientEductn() {
		return patientEductn;
	}

	public void setPatientEductn(String patientEductn) {
		this.patientEductn = patientEductn;
	}

	public String getPathusbEductn() {
		return pathusbEductn;
	}

	public void setPathusbEductn(String pathusbEductn) {
		this.pathusbEductn = pathusbEductn;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}
	private String practDepartment;
	private String isfrompharmacy;
	
	//chraibgh 
	private String patientoccc;
	private String patientincm;
	private String patienthusocc;
	private String patienthusincome;
	private String patientEductn;
	private String pathusbEductn;
	private String religion;
	private String cast;
	
	
	//ArrayList<Master>disciplineList;
	
	
	public String getIsfrompharmacy() {
		return isfrompharmacy;
	}

	public void setIsfrompharmacy(String isfrompharmacy) {
		this.isfrompharmacy = isfrompharmacy;
	}

	public String getPractDepartment() {
		return practDepartment;
	}

	public void setPractDepartment(String practDepartment) {
		this.practDepartment = practDepartment;
	}

	public String getCampArea() {
		return campArea;
	}

	public String setCampArea(String campArea) {
		return this.campArea = campArea;
	}

	public String getPatientcategory() {
		return patientcategory;
	}

	public void setPatientcategory(String patientcategory) {
		this.patientcategory = patientcategory;
	}

	public String getLmh_department() {
		return lmh_department;
	}

	public void setLmh_department(String lmh_department) {
		this.lmh_department = lmh_department;
	}

	public ArrayList<Client> getPatientlist() {
		return patientlist;
	}

	public void setPatientlist(ArrayList<Client> patientlist) {
		this.patientlist = patientlist;
	}

	public String getBill_edituserid() {
		return bill_edituserid;
	}

	public void setBill_edituserid(String bill_edituserid) {
		this.bill_edituserid = bill_edituserid;
	}

	public String getBill_editdatetime() {
		return bill_editdatetime;
	}

	public void setBill_editdatetime(String bill_editdatetime) {
		this.bill_editdatetime = bill_editdatetime;
	}

	public String getTodayonline() {
		return todayonline;
	}

	public void setTodayonline(String todayonline) {
		this.todayonline = todayonline;
	}

	public String getOnlinepayment() {
		return onlinepayment;
	}

	public void setOnlinepayment(String onlinepayment) {
		this.onlinepayment = onlinepayment;
	}

	public String getOnlinepaymentreturn() {
		return onlinepaymentreturn;
	}

	public void setOnlinepaymentreturn(String onlinepaymentreturn) {
		this.onlinepaymentreturn = onlinepaymentreturn;
	}

	public String getDoctor_qualification() {
		return doctor_qualification;
	}

	public void setDoctor_qualification(String doctor_qualification) {
		this.doctor_qualification = doctor_qualification;
	}

	public String getDoctor_address() {
		return doctor_address;
	}

	public void setDoctor_address(String doctor_address) {
		this.doctor_address = doctor_address;
	}

	public String getPhar_practid() {
		return phar_practid;
	}

	public void setPhar_practid(String phar_practid) {
		this.phar_practid = phar_practid;
	}

	public ArrayList<UserProfile> getPharmacydrlist() {
		return pharmacydrlist;
	}

	public void setPharmacydrlist(ArrayList<UserProfile> pharmacydrlist) {
		this.pharmacydrlist = pharmacydrlist;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public int getHideuploadbtn() {
		return hideuploadbtn;
	}

	public void setHideuploadbtn(int hideuploadbtn) {
		this.hideuploadbtn = hideuploadbtn;
	}

	public int getIsfromtokendashbaord() {
		return isfromtokendashbaord;
	}

	public void setIsfromtokendashbaord(int isfromtokendashbaord) {
		this.isfromtokendashbaord = isfromtokendashbaord;
	}

	public String getTokenno() {
		return tokenno;
	}

	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}

	public int getDiscountpatienttype() {
		return discountpatienttype;
	}

	public void setDiscountpatienttype(int discountpatienttype) {
		this.discountpatienttype = discountpatienttype;
	}

	public String getOpddiscount() {
		return opddiscount;
	}

	public void setOpddiscount(String opddiscount) {
		this.opddiscount = opddiscount;
	}

	public String getIpddiscount() {
		return ipddiscount;
	}

	public void setIpddiscount(String ipddiscount) {
		this.ipddiscount = ipddiscount;
	}

	public String getPharpatientdiscount() {
		return pharpatientdiscount;
	}

	public void setPharpatientdiscount(String pharpatientdiscount) {
		this.pharpatientdiscount = pharpatientdiscount;
	}

	public int getShownavigation() {
		return shownavigation;
	}

	public void setShownavigation(int shownavigation) {
		this.shownavigation = shownavigation;
	}

	public ArrayList<Priscription> getPriscriptionlistnew() {
		return priscriptionlistnew;
	}

	public void setPriscriptionlistnew(ArrayList<Priscription> priscriptionlistnew) {
		this.priscriptionlistnew = priscriptionlistnew;
	}

	public String getAprovediscids() {
		return aprovediscids;
	}

	public void setAprovediscids(String aprovediscids) {
		this.aprovediscids = aprovediscids;
	}

	public String getFilter_phar_location() {
		return filter_phar_location;
	}

	public void setFilter_phar_location(String filter_phar_location) {
		this.filter_phar_location = filter_phar_location;
	}

	public String getFilter_location() {
		return filter_location;
	}

	public void setFilter_location(String filter_location) {
		this.filter_location = filter_location;
	}

	public boolean isNonsystembarcode() {
		return nonsystembarcode;
	}

	public void setNonsystembarcode(boolean nonsystembarcode) {
		this.nonsystembarcode = nonsystembarcode;
	}

	public boolean isDosenotesstauss() {
		return dosenotesstauss;
	}

	public void setDosenotesstauss(boolean dosenotesstauss) {
		this.dosenotesstauss = dosenotesstauss;
	}

	public String getPhar_pre_balance() {
		return phar_pre_balance;
	}

	public void setPhar_pre_balance(String phar_pre_balance) {
		this.phar_pre_balance = phar_pre_balance;
	}

	public ArrayList<Master> getRequestlocationlist() {
		return requestlocationlist;
	}

	public void setRequestlocationlist(ArrayList<Master> requestlocationlist) {
		this.requestlocationlist = requestlocationlist;
	}

	public String getRequestlocationid() {
		return requestlocationid;
	}

	public void setRequestlocationid(String requestlocationid) {
		this.requestlocationid = requestlocationid;
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
	private String uom;
	
	ArrayList<Master>nimaidoselist;
	ArrayList<Master>nimaiqtylist;
	ArrayList<Master>nimairemarklist;
	
	
	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
	
	
	
	
	public String getPrintbr() {
		return printbr;
	}
private int pagelimit;
	public void setPrintbr(String printbr) {
		this.printbr = printbr;
	}
	private String address1;
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	private ArrayList<Priscription> prisMoleculessList;
	private int refundid=0;
	private String sectionid;
	private ArrayList<Master> sectionlist;
	private ArrayList<Master> alljobtitlelist;
	private String creditReturn="0";
	private String hospitalReturn="0";
	private String expectedDate;
	private String printedby;
	private String labname;
	private String creport;
	ArrayList<Master> priscUnitList;
	private int edited=0;
	private String priscid="0";
	private String edit_note;
	private ArrayList<Priscription> clearBillList;
	private ArrayList<Assessment> assesmenttemplateNameList ;
	private ArrayList<Bed> gynicFormList;
	private ArrayList<Master> warehouseList;
	private ArrayList<Emr> emrDocumentList; 
	private ArrayList<Emr> ipdDocumentList; 
	private ArrayList<Emr> opdDocumentList;
	private ArrayList<Emr> documentDetailedList;
	private String invreq;
	ArrayList<Master>pkgsList;
	private String invpkg;
	private String investigation_approve;
	private String approve_status;
	private String clinicphoneno;
	private String fullinfo;
	private ArrayList<Priscription> allBillList;
	private String isIpd;
	private String admissionDate;
	private String totalbill;
	private String totalreturn;
	private String totaldisc;
	private String roundOf;
	private String netamt;
	private ArrayList<NotAvailableSlot> optionformlist;
	private ArrayList<Client> declarationformlist;
	private String suagarfasting;
	private String postmeal;
	private String useregno;
	private ArrayList<Master> allSectionList;
	private String invstsecid;
	private String drregno;
	private ArrayList<Priscription> productwisereturnreport;
	private ArrayList<Priscription> medicinetimelist;
	private ArrayList<Investigation> outsourcelist;
	private String outsource;
	private boolean prisctimestatus = false;
	private boolean priscreamrkstatus = false;
	private boolean priscunitstatus = false;
	private String priscreqids;
	private String parentid;
	private String oldparentid;
	private int newselectedid;
	private int newparentid;
	private String isfromipd;
	private String isipdtimeshow;
	private String isnewcheckavailability="0";
	private String totalcreditbyuser;
	private double totalcreditReturn;
	private String todayagainstcredit;
	private String finalcredit;
	private String newcredit;
	private String discper;
	private String iswbd;
	private String frequency;
	private String caldose;
	private boolean masterdosestatus; 
	private String isfromreturndashboard;
	private String totalamt;
	private String multireturndash;
	private String multireturnbillid;
	private String hdnipdid;
	private String paymodereturn;
	private String totalrefundrs;
	private ArrayList<Priscription> returnmedlist;
	private String returnqty;
	private String returnrequestid;
	private String returnchargeid;
	
	private String prisctime;
	private String dosefreq;
	private String discounttype;
	private String salediscount;
	private String subbtotal;
	private String discinper;
	private int totalqty;
	private double actualtemptot;
	private String grosstotal;
	private String grosssubtotal;
	private int isdirectsale;
	private String phar_ipdid;
	private String phar_wardid;
	private String phar_bedid;
	private Collection<Priscription> salecollection;
	
	private double salecash=0,salecard=0,salecheque=0,saleneft=0,salecredit=0,salehospital=0,saletotal=0,saleonlinepayment=0;
	private double discountsalecash=0,discountsalecard=0,discountsalecheque=0,discountsaleneft=0,discountsalecredit=0,discountsalehospital=0,discountsaletotal=0,discountsaleonlinepayment=0;
	private double netsalecash=0,netsalecard=0,netsalecheque=0,netsaleneft=0,netsalecredit=0,netsalehospital=0,netsaletotal=0,netsaleonlinepayment=0;
	private double cashpayments=0,cardpayments=0,chequepayments=0,neftpayments=0,creditpayments=0,hospitalpayments=0,totalpayments=0,onlinepaymentpayments=0;
	private double outcashpayments=0,outcardpayments=0,outchequepayments=0,outneftpayments=0,outcreditpayments=0,outhospitalpayments=0,outtotalpayments=0,outonlinepaymentpayments=0;
	private double totalcashpayments=0,totalcardpayments=0,totalchequepayments=0,totalneftpayments=0,totalcreditpayments=0,totalhospitalpayments=0,totaltotalpayments=0,totalonlinepaymentpayments=0;
	private double returncash=0,returncard=0,returncheque=0,returnneft=0,returncredit=0,returnhospital=0,returntotal=0,returnonlinepayment=0;
	private double netcashcollection=0,netcardcollection=0,netchequecollection=0,netneftcollection=0,netcreditcollection=0,nethospitalcollection=0,nettotalcollection=0,netonlinepaymentcollection=0;
	private int dummybillno;
	private ArrayList<Priscription> discountList;
	private String approve_notes;
	private String reqfromlocation;
	private String default_phar_location;
	private String cardreturn;
	
	public String getCardreturn() {
		return cardreturn;
	}

	public void setCardreturn(String cardreturn) {
		this.cardreturn = cardreturn;
	}

	public double getSaleonlinepayment() {
		return saleonlinepayment;
	}

	public void setSaleonlinepayment(double saleonlinepayment) {
		this.saleonlinepayment = saleonlinepayment;
	}

	public double getDiscountsaleonlinepayment() {
		return discountsaleonlinepayment;
	}

	public void setDiscountsaleonlinepayment(double discountsaleonlinepayment) {
		this.discountsaleonlinepayment = discountsaleonlinepayment;
	}

	public double getNetsaleonlinepayment() {
		return netsaleonlinepayment;
	}

	public void setNetsaleonlinepayment(double netsaleonlinepayment) {
		this.netsaleonlinepayment = netsaleonlinepayment;
	}

	public double getOnlinepaymentpayments() {
		return onlinepaymentpayments;
	}

	public void setOnlinepaymentpayments(double onlinepaymentpayments) {
		this.onlinepaymentpayments = onlinepaymentpayments;
	}

	public double getOutonlinepaymentpayments() {
		return outonlinepaymentpayments;
	}

	public void setOutonlinepaymentpayments(double outonlinepaymentpayments) {
		this.outonlinepaymentpayments = outonlinepaymentpayments;
	}

	public double getTotalonlinepaymentpayments() {
		return totalonlinepaymentpayments;
	}

	public void setTotalonlinepaymentpayments(double totalonlinepaymentpayments) {
		this.totalonlinepaymentpayments = totalonlinepaymentpayments;
	}

	public double getReturnonlinepayment() {
		return returnonlinepayment;
	}

	public void setReturnonlinepayment(double returnonlinepayment) {
		this.returnonlinepayment = returnonlinepayment;
	}

	public double getNetonlinepaymentcollection() {
		return netonlinepaymentcollection;
	}

	public void setNetonlinepaymentcollection(double netonlinepaymentcollection) {
		this.netonlinepaymentcollection = netonlinepaymentcollection;
	}

	public String getDefault_phar_location() {
		return default_phar_location;
	}

	public void setDefault_phar_location(String default_phar_location) {
		this.default_phar_location = default_phar_location;
	}

	public String getReqfromlocation() {
		return reqfromlocation;
	}

	public void setReqfromlocation(String reqfromlocation) {
		this.reqfromlocation = reqfromlocation;
	}

	public String getApprove_notes() {
		return approve_notes;
	}

	public void setApprove_notes(String approve_notes) {
		this.approve_notes = approve_notes;
	}

	public ArrayList<Priscription> getDiscountList() {
		return discountList;
	}

	public void setDiscountList(ArrayList<Priscription> discountList) {
		this.discountList = discountList;
	}

	public int getDummybillno() {
		return dummybillno;
	}

	public void setDummybillno(int dummybillno) {
		this.dummybillno = dummybillno;
	}

	public double getOutcashpayments() {
		return outcashpayments;
	}

	public void setOutcashpayments(double outcashpayments) {
		this.outcashpayments = outcashpayments;
	}

	public double getOutcardpayments() {
		return outcardpayments;
	}

	public void setOutcardpayments(double outcardpayments) {
		this.outcardpayments = outcardpayments;
	}

	public double getOutchequepayments() {
		return outchequepayments;
	}

	public void setOutchequepayments(double outchequepayments) {
		this.outchequepayments = outchequepayments;
	}

	public double getOutneftpayments() {
		return outneftpayments;
	}

	public void setOutneftpayments(double outneftpayments) {
		this.outneftpayments = outneftpayments;
	}

	public double getOutcreditpayments() {
		return outcreditpayments;
	}

	public void setOutcreditpayments(double outcreditpayments) {
		this.outcreditpayments = outcreditpayments;
	}

	public double getOuthospitalpayments() {
		return outhospitalpayments;
	}

	public void setOuthospitalpayments(double outhospitalpayments) {
		this.outhospitalpayments = outhospitalpayments;
	}

	public double getOuttotalpayments() {
		return outtotalpayments;
	}

	public void setOuttotalpayments(double outtotalpayments) {
		this.outtotalpayments = outtotalpayments;
	}

	public double getTotalcashpayments() {
		return totalcashpayments;
	}

	public void setTotalcashpayments(double totalcashpayments) {
		this.totalcashpayments = totalcashpayments;
	}

	public double getTotalcardpayments() {
		return totalcardpayments;
	}

	public void setTotalcardpayments(double totalcardpayments) {
		this.totalcardpayments = totalcardpayments;
	}

	public double getTotalchequepayments() {
		return totalchequepayments;
	}

	public void setTotalchequepayments(double totalchequepayments) {
		this.totalchequepayments = totalchequepayments;
	}

	public double getTotalneftpayments() {
		return totalneftpayments;
	}

	public void setTotalneftpayments(double totalneftpayments) {
		this.totalneftpayments = totalneftpayments;
	}

	public double getTotalcreditpayments() {
		return totalcreditpayments;
	}

	public void setTotalcreditpayments(double totalcreditpayments) {
		this.totalcreditpayments = totalcreditpayments;
	}

	public double getTotalhospitalpayments() {
		return totalhospitalpayments;
	}

	public void setTotalhospitalpayments(double totalhospitalpayments) {
		this.totalhospitalpayments = totalhospitalpayments;
	}

	public double getTotaltotalpayments() {
		return totaltotalpayments;
	}

	public void setTotaltotalpayments(double totaltotalpayments) {
		this.totaltotalpayments = totaltotalpayments;
	}

	public double getReturncash() {
		return returncash;
	}

	public void setReturncash(double returncash) {
		this.returncash = returncash;
	}

	public double getReturncard() {
		return returncard;
	}

	public void setReturncard(double returncard) {
		this.returncard = returncard;
	}

	public double getReturncheque() {
		return returncheque;
	}

	public void setReturncheque(double returncheque) {
		this.returncheque = returncheque;
	}

	public double getReturnneft() {
		return returnneft;
	}

	public void setReturnneft(double returnneft) {
		this.returnneft = returnneft;
	}

	public double getReturncredit() {
		return returncredit;
	}

	public void setReturncredit(double returncredit) {
		this.returncredit = returncredit;
	}

	public double getReturnhospital() {
		return returnhospital;
	}

	public void setReturnhospital(double returnhospital) {
		this.returnhospital = returnhospital;
	}

	public double getReturntotal() {
		return returntotal;
	}

	public void setReturntotal(double returntotal) {
		this.returntotal = returntotal;
	}

	public double getNetcashcollection() {
		return netcashcollection;
	}

	public void setNetcashcollection(double netcashcollection) {
		this.netcashcollection = netcashcollection;
	}

	public double getNetcardcollection() {
		return netcardcollection;
	}

	public void setNetcardcollection(double netcardcollection) {
		this.netcardcollection = netcardcollection;
	}

	public double getNetchequecollection() {
		return netchequecollection;
	}

	public void setNetchequecollection(double netchequecollection) {
		this.netchequecollection = netchequecollection;
	}

	public double getNetneftcollection() {
		return netneftcollection;
	}

	public void setNetneftcollection(double netneftcollection) {
		this.netneftcollection = netneftcollection;
	}

	public double getNetcreditcollection() {
		return netcreditcollection;
	}

	public void setNetcreditcollection(double netcreditcollection) {
		this.netcreditcollection = netcreditcollection;
	}

	public double getNethospitalcollection() {
		return nethospitalcollection;
	}

	public void setNethospitalcollection(double nethospitalcollection) {
		this.nethospitalcollection = nethospitalcollection;
	}

	public double getNettotalcollection() {
		return nettotalcollection;
	}

	public void setNettotalcollection(double nettotalcollection) {
		this.nettotalcollection = nettotalcollection;
	}

	public double getCashpayments() {
		return cashpayments;
	}

	public void setCashpayments(double cashpayments) {
		this.cashpayments = cashpayments;
	}

	public double getCardpayments() {
		return cardpayments;
	}

	public void setCardpayments(double cardpayments) {
		this.cardpayments = cardpayments;
	}

	public double getChequepayments() {
		return chequepayments;
	}

	public void setChequepayments(double chequepayments) {
		this.chequepayments = chequepayments;
	}

	public double getNeftpayments() {
		return neftpayments;
	}

	public void setNeftpayments(double neftpayments) {
		this.neftpayments = neftpayments;
	}

	public double getCreditpayments() {
		return creditpayments;
	}

	public void setCreditpayments(double creditpayments) {
		this.creditpayments = creditpayments;
	}

	public double getHospitalpayments() {
		return hospitalpayments;
	}

	public void setHospitalpayments(double hospitalpayments) {
		this.hospitalpayments = hospitalpayments;
	}

	public double getTotalpayments() {
		return totalpayments;
	}

	public void setTotalpayments(double totalpayments) {
		this.totalpayments = totalpayments;
	}

	public double getSalecash() {
		return salecash;
	}

	public void setSalecash(double salecash) {
		this.salecash = salecash;
	}

	public double getSalecard() {
		return salecard;
	}

	public void setSalecard(double salecard) {
		this.salecard = salecard;
	}

	public double getSalecheque() {
		return salecheque;
	}

	public void setSalecheque(double salecheque) {
		this.salecheque = salecheque;
	}

	public double getSaleneft() {
		return saleneft;
	}

	public void setSaleneft(double saleneft) {
		this.saleneft = saleneft;
	}

	public double getSalecredit() {
		return salecredit;
	}

	public void setSalecredit(double salecredit) {
		this.salecredit = salecredit;
	}

	public double getSalehospital() {
		return salehospital;
	}

	public void setSalehospital(double salehospital) {
		this.salehospital = salehospital;
	}

	public double getSaletotal() {
		return saletotal;
	}

	public void setSaletotal(double saletotal) {
		this.saletotal = saletotal;
	}

	public double getDiscountsalecash() {
		return discountsalecash;
	}

	public void setDiscountsalecash(double discountsalecash) {
		this.discountsalecash = discountsalecash;
	}

	public double getDiscountsalecard() {
		return discountsalecard;
	}

	public void setDiscountsalecard(double discountsalecard) {
		this.discountsalecard = discountsalecard;
	}

	public double getDiscountsalecheque() {
		return discountsalecheque;
	}

	public void setDiscountsalecheque(double discountsalecheque) {
		this.discountsalecheque = discountsalecheque;
	}

	public double getDiscountsaleneft() {
		return discountsaleneft;
	}

	public void setDiscountsaleneft(double discountsaleneft) {
		this.discountsaleneft = discountsaleneft;
	}

	public double getDiscountsalecredit() {
		return discountsalecredit;
	}

	public void setDiscountsalecredit(double discountsalecredit) {
		this.discountsalecredit = discountsalecredit;
	}

	public double getDiscountsalehospital() {
		return discountsalehospital;
	}

	public void setDiscountsalehospital(double discountsalehospital) {
		this.discountsalehospital = discountsalehospital;
	}

	public double getDiscountsaletotal() {
		return discountsaletotal;
	}

	public void setDiscountsaletotal(double discountsaletotal) {
		this.discountsaletotal = discountsaletotal;
	}

	public double getNetsalecash() {
		return netsalecash;
	}

	public void setNetsalecash(double netsalecash) {
		this.netsalecash = netsalecash;
	}

	public double getNetsalecard() {
		return netsalecard;
	}

	public void setNetsalecard(double netsalecard) {
		this.netsalecard = netsalecard;
	}

	public double getNetsalecheque() {
		return netsalecheque;
	}

	public void setNetsalecheque(double netsalecheque) {
		this.netsalecheque = netsalecheque;
	}

	public double getNetsaleneft() {
		return netsaleneft;
	}

	public void setNetsaleneft(double netsaleneft) {
		this.netsaleneft = netsaleneft;
	}

	public double getNetsalecredit() {
		return netsalecredit;
	}

	public void setNetsalecredit(double netsalecredit) {
		this.netsalecredit = netsalecredit;
	}

	public double getNetsalehospital() {
		return netsalehospital;
	}

	public void setNetsalehospital(double netsalehospital) {
		this.netsalehospital = netsalehospital;
	}

	public double getNetsaletotal() {
		return netsaletotal;
	}

	public void setNetsaletotal(double netsaletotal) {
		this.netsaletotal = netsaletotal;
	}

	public Collection<Priscription> getSalecollection() {
		return salecollection;
	}

	public void setSalecollection(Collection<Priscription> salecollection) {
		this.salecollection = salecollection;
	}

	public String getPhar_ipdid() {
		return phar_ipdid;
	}

	public void setPhar_ipdid(String phar_ipdid) {
		this.phar_ipdid = phar_ipdid;
	}

	public String getPhar_wardid() {
		return phar_wardid;
	}

	public void setPhar_wardid(String phar_wardid) {
		this.phar_wardid = phar_wardid;
	}

	public String getPhar_bedid() {
		return phar_bedid;
	}

	public void setPhar_bedid(String phar_bedid) {
		this.phar_bedid = phar_bedid;
	}

	public int getIsdirectsale() {
		return isdirectsale;
	}

	public void setIsdirectsale(int isdirectsale) {
		this.isdirectsale = isdirectsale;
	}
	
	public String getGrosstotal() {
		return grosstotal;
	}

	public void setGrosstotal(String grosstotal) {
		this.grosstotal = grosstotal;
	}

	public String getGrosssubtotal() {
		return grosssubtotal;
	}

	public void setGrosssubtotal(String grosssubtotal) {
		this.grosssubtotal = grosssubtotal;
	}

	public double getActualtemptot() {
		return actualtemptot;
	}

	public void setActualtemptot(double actualtemptot) {
		this.actualtemptot = actualtemptot;
	}

	public int getTotalqty() {
		return totalqty;
	}

	public void setTotalqty(int totalqty) {
		this.totalqty = totalqty;
	}

	public String getDiscinper() {
		return discinper;
	}

	public void setDiscinper(String discinper) {
		this.discinper = discinper;
	}

	public String getSubbtotal() {
		return subbtotal;
	}

	public void setSubbtotal(String subbtotal) {
		this.subbtotal = subbtotal;
	}

	public String getSalediscount() {
		return salediscount;
	}

	public void setSalediscount(String salediscount) {
		this.salediscount = salediscount;
	}

	public String getDiscounttype() {
		return discounttype;
	}

	public void setDiscounttype(String discounttype) {
		this.discounttype = discounttype;
	}

	public String getDosefreq() {
		return dosefreq;
	}

	public void setDosefreq(String dosefreq) {
		this.dosefreq = dosefreq;
	}

	public String getPrisctime() {
		return prisctime;
	}

	public void setPrisctime(String prisctime) {
		this.prisctime = prisctime;
	}

	public String getReturnrequestid() {
		return returnrequestid;
	}

	public void setReturnrequestid(String returnrequestid) {
		this.returnrequestid = returnrequestid;
	}

	public String getReturnchargeid() {
		return returnchargeid;
	}

	public void setReturnchargeid(String returnchargeid) {
		this.returnchargeid = returnchargeid;
	}

	public String getReturnqty() {
		return returnqty;
	}

	public void setReturnqty(String returnqty) {
		this.returnqty = returnqty;
	}

	public ArrayList<Priscription> getReturnmedlist() {
		return returnmedlist;
	}

	public void setReturnmedlist(ArrayList<Priscription> returnmedlist) {
		this.returnmedlist = returnmedlist;
	}

	public String getTotalrefundrs() {
		return totalrefundrs;
	}

	public void setTotalrefundrs(String totalrefundrs) {
		this.totalrefundrs = totalrefundrs;
	}

	public String getPaymodereturn() {
		return paymodereturn;
	}

	public void setPaymodereturn(String paymodereturn) {
		this.paymodereturn = paymodereturn;
	}

	public String getHdnipdid() {
		return hdnipdid;
	}

	public void setHdnipdid(String hdnipdid) {
		this.hdnipdid = hdnipdid;
	}

	public String getMultireturnbillid() {
		return multireturnbillid;
	}

	public void setMultireturnbillid(String multireturnbillid) {
		this.multireturnbillid = multireturnbillid;
	}

	public String getMultireturndash() {
		return multireturndash;
	}

	public void setMultireturndash(String multireturndash) {
		this.multireturndash = multireturndash;
	}

	public String getTotalamt() {
		return totalamt;
	}

	public void setTotalamt(String totalamt) {
		this.totalamt = totalamt;
	}

	public String getIsfromreturndashboard() {
		return isfromreturndashboard;
	}

	public void setIsfromreturndashboard(String isfromreturndashboard) {
		this.isfromreturndashboard = isfromreturndashboard;
	}

	public boolean isMasterdosestatus() {
		return masterdosestatus;
	}

	public void setMasterdosestatus(boolean masterdosestatus) {
		this.masterdosestatus = masterdosestatus;
	}

	public String getIswbd() {
		return iswbd;
	}

	public void setIswbd(String iswbd) {
		this.iswbd = iswbd;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getCaldose() {
		return caldose;
	}

	public void setCaldose(String caldose) {
		this.caldose = caldose;
	}

	public String getDiscper() {
		return discper;
	}

	public void setDiscper(String discper) {
		this.discper = discper;
	}

	public String getNewcredit() {
		return newcredit;
	}

	public void setNewcredit(String newcredit) {
		this.newcredit = newcredit;
	}

	public String getFinalcredit() {
		return finalcredit;
	}

	public void setFinalcredit(String finalcredit) {
		this.finalcredit = finalcredit;
	}

	public String getTodayagainstcredit() {
		return todayagainstcredit;
	}

	public void setTodayagainstcredit(String todayagainstcredit) {
		this.todayagainstcredit = todayagainstcredit;
	}

	public double getTotalcreditReturn() {
		return totalcreditReturn;
	}

	public void setTotalcreditReturn(double totalcreditReturn) {
		this.totalcreditReturn = totalcreditReturn;
	}

	public String getTotalcreditbyuser() {
		return totalcreditbyuser;
	}

	public void setTotalcreditbyuser(String totalcreditbyuser) {
		this.totalcreditbyuser = totalcreditbyuser;
	}

	public String getIsnewcheckavailability() {
		return isnewcheckavailability;
	}

	public void setIsnewcheckavailability(String isnewcheckavailability) {
		this.isnewcheckavailability = isnewcheckavailability;
	}

	public String getIsipdtimeshow() {
		return isipdtimeshow;
	}

	public void setIsipdtimeshow(String isipdtimeshow) {
		this.isipdtimeshow = isipdtimeshow;
	}

	public String getIsfromipd() {
		return isfromipd;
	}

	public void setIsfromipd(String isfromipd) {
		this.isfromipd = isfromipd;
	}

	public int getNewparentid() {
		return newparentid;
	}

	public void setNewparentid(int newparentid) {
		this.newparentid = newparentid;
	}

	public int getNewselectedid() {
		return newselectedid;
	}

	public void setNewselectedid(int newselectedid) {
		this.newselectedid = newselectedid;
	}

	public String getOldparentid() {
		return oldparentid;
	}

	public void setOldparentid(String oldparentid) {
		this.oldparentid = oldparentid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getPriscreqids() {
		return priscreqids;
	}

	public void setPriscreqids(String priscreqids) {
		this.priscreqids = priscreqids;
	}

	public boolean isPrisctimestatus() {
		return prisctimestatus;
	}

	public void setPrisctimestatus(boolean prisctimestatus) {
		this.prisctimestatus = prisctimestatus;
	}

	public boolean isPriscreamrkstatus() {
		return priscreamrkstatus;
	}

	public void setPriscreamrkstatus(boolean priscreamrkstatus) {
		this.priscreamrkstatus = priscreamrkstatus;
	}

	public boolean isPriscunitstatus() {
		return priscunitstatus;
	}

	public void setPriscunitstatus(boolean priscunitstatus) {
		this.priscunitstatus = priscunitstatus;
	}

	public String getOutsource() {
		return outsource;
	}

	public void setOutsource(String outsource) {
		this.outsource = outsource;
	}

	public ArrayList<Investigation> getOutsourcelist() {
		return outsourcelist;
	}

	public void setOutsourcelist(ArrayList<Investigation> outsourcelist) {
		this.outsourcelist = outsourcelist;
	}

	public ArrayList<Priscription> getMedicinetimelist() {
		return medicinetimelist;
	}

	public void setMedicinetimelist(ArrayList<Priscription> medicinetimelist) {
		this.medicinetimelist = medicinetimelist;
	}

	public ArrayList<Priscription> getProductwisereturnreport() {
		return productwisereturnreport;
	}

	public void setProductwisereturnreport(ArrayList<Priscription> productwisereturnreport) {
		this.productwisereturnreport = productwisereturnreport;
	}

	public String getDrregno() {
		return drregno;
	}

	public void setDrregno(String drregno) {
		this.drregno = drregno;
	}

	public String getInvstsecid() {
		return invstsecid;
	}

	public void setInvstsecid(String invstsecid) {
		this.invstsecid = invstsecid;
	}

	public ArrayList<Master> getAllSectionList() {
		return allSectionList;
	}

	public void setAllSectionList(ArrayList<Master> allSectionList) {
		this.allSectionList = allSectionList;
	}

	public String getUseregno() {
		return useregno;
	}

	public void setUseregno(String useregno) {
		this.useregno = useregno;
	}

	public String getSuagarfasting() {
		return suagarfasting;
	}

	public void setSuagarfasting(String suagarfasting) {
		this.suagarfasting = suagarfasting;
	}

	public String getPostmeal() {
		return postmeal;
	}

	public void setPostmeal(String postmeal) {
		this.postmeal = postmeal;
	}

	public ArrayList<Client> getDeclarationformlist() {
		return declarationformlist;
	}

	public void setDeclarationformlist(ArrayList<Client> declarationformlist) {
		this.declarationformlist = declarationformlist;
	}

	public ArrayList<NotAvailableSlot> getOptionformlist() {
		return optionformlist;
	}

	public void setOptionformlist(ArrayList<NotAvailableSlot> optionformlist) {
		this.optionformlist = optionformlist;
	}

	public String getNetamt() {
		return netamt;
	}

	public void setNetamt(String netamt) {
		this.netamt = netamt;
	}

	public String getRoundOf() {
		return roundOf;
	}

	public void setRoundOf(String roundOf) {
		this.roundOf = roundOf;
	}

	public String getTotaldisc() {
		return totaldisc;
	}

	public void setTotaldisc(String totaldisc) {
		this.totaldisc = totaldisc;
	}

	public String getTotalreturn() {
		return totalreturn;
	}

	public void setTotalreturn(String totalreturn) {
		this.totalreturn = totalreturn;
	}

	public String getTotalbill() {
		return totalbill;
	}

	public void setTotalbill(String totalbill) {
		this.totalbill = totalbill;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getIsIpd() {
		return isIpd;
	}

	public void setIsIpd(String isIpd) {
		this.isIpd = isIpd;
	}

	public ArrayList<Priscription> getAllBillList() {
		return allBillList;
	}

	public void setAllBillList(ArrayList<Priscription> allBillList) {
		this.allBillList = allBillList;
	}

	public String getFullinfo() {
		return fullinfo;
	}

	public void setFullinfo(String fullinfo) {
		this.fullinfo = fullinfo;
	}

	public String getClinicphoneno() {
		return clinicphoneno;
	}

	public void setClinicphoneno(String clinicphoneno) {
		this.clinicphoneno = clinicphoneno;
	}

	public String getApprove_status() {
		return approve_status;
	}

	public void setApprove_status(String approve_status) {
		this.approve_status = approve_status;
	}

	public String getInvestigation_approve() {
		return investigation_approve;
	}

	public void setInvestigation_approve(String investigation_approve) {
		this.investigation_approve = investigation_approve;
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

	public String getInvreq() {
		return invreq;
	}

	public void setInvreq(String invreq) {
		this.invreq = invreq;
	}

	private String filter_status;
	private String dosagenote="0";
	private String regno;
	private String totalcredit;
	private String locationName;
	private String returnbill;
	private String filter_ward;
	private String hdnselectedid;
	ArrayList<Master>departmentInvList;
	private String return_medicine;
	public String getReturn_medicine() {
		return return_medicine;
	}

	public void setReturn_medicine(String return_medicine) {
		this.return_medicine = return_medicine;
	}

	public ArrayList<Master> getDepartmentInvList() {
		return departmentInvList;
	}

	public void setDepartmentInvList(ArrayList<Master> departmentInvList) {
		this.departmentInvList = departmentInvList;
	}

	public String getHdnselectedid() {
		return hdnselectedid;
	}

	public void setHdnselectedid(String hdnselectedid) {
		this.hdnselectedid = hdnselectedid;
	}

	public String getFilter_ward() {
		return filter_ward;
	}

	public void setFilter_ward(String filter_ward) {
		this.filter_ward = filter_ward;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getTotalcredit() {
		return totalcredit;
	}

	public void setTotalcredit(String totalcredit) {
		this.totalcredit = totalcredit;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getFilter_status() {
		return filter_status;
	}

	public void setFilter_status(String filter_status) {
		this.filter_status = filter_status;
	}

	public String getLabname() {
		return labname;
	}

	public void setLabname(String labname) {
		this.labname = labname;
	}

	public String getCreport() {
		return creport;
	}

	public void setCreport(String creport) {
		this.creport = creport;
	}

	private int deleted=0;
	private String returnmode="0";
	private String remark;
	
	private String collect_date;
	public String getCollect_date() {
		return collect_date;
	}

	public void setCollect_date(String collect_date) {
		this.collect_date = collect_date;
	}

	private String approved1;
	
	public String getApproved1() {
		return approved1;
	}

	public void setApproved1(String approved1) {
		this.approved1 = approved1;
	}

	public String getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

	public String getSectionid() {
		return sectionid;
	}

	public String getCreditReturn() {
		return creditReturn;
	}

	public void setCreditReturn(String creditReturn) {
		this.creditReturn = creditReturn;
	}

	public String getHospitalReturn() {
		return hospitalReturn;
	}

	public void setHospitalReturn(String hospitalReturn) {
		this.hospitalReturn = hospitalReturn;
	}

	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}

	public ArrayList<Master> getSectionlist() {
		return sectionlist;
	}

	public void setSectionlist(ArrayList<Master> sectionlist) {
		this.sectionlist = sectionlist;
	}

	private String hospital="0";
	private int counter;
	private String createdDateTime;
	private ArrayList<Product> parenttransferlist;
	private ArrayList<Product> productList;
	private String createdby;
	private ArrayList<Product> brandnameList;
	private ArrayList<Master> medicineTypeList;
	private ArrayList<Master> cellList;
	private String previous_balance;
	private String prebal;
	private String opdid;
	private String ipdidcheck;
	private String balance;
	private String extpid;
	private String creditTotal="0";
	private String printType="0";
	
	private String chequepayment="0";
	private String requisition_auth;
	private int id;

	private String back_date_access="0";
	private String tp_bill_access="0";
	private String commencing = "";
	private String netcard="0";
	private String estimatebill="0";
	private String netcash="0";
	private ArrayList<Emr> emrList;
	private String tpid="0";
	private String nettotal="0";
	private String ipdoropd="";
	private String inhousepatient="";
	private String from;
	private String update_date="";
	private int isreturn=0;
	private String neftpayment="0";
	private String complete_date="";
	private String cgst="0";
	  String todaycash= "0";
	  String todaycard= "0";
	  String todaydisc ="0";
	  String todayReturn= "0";
    private String totalCardRefund="0";	  
	   
	private String sgst="0";
	private String payamt="0";
	private String invoiceTime="";
	private String report;
	private String gender;
	private ArrayList<Priscription> locationWiseReport;
	private String netReceivedTotal="0";
	private String procurementType="0";
	private String ipdlocation="0";
	private String opdlocation="0";
	ArrayList<Product> inventoryPriscList;
	private ArrayList<Product> soldProductList;
	private String paymode;
	private String cardBill;
    private ArrayList<Master> locationListPharmacy;
 	private ArrayList<Priscription> dailyuserreport;
	private String billcount;
	private String payment;
	private String totalpayment;
	
	private String abrivationid;
	private int age;
	private String clinicLogo;
	private String refundamt="0";
	private ArrayList<Master> statelist;
	private ArrayList<Master> citylist;
	
	private String instruction1;
	private String instruction2;
	private String instruction3;
	private String gst="0";
	private String instruction4;
	private String place="0";
	
	private String inst1;
	private String inst2;
	private String inst3;
	private String inst4;

	

	 private String height;
		private String weight;
		private String bmi;
		private String pulse;
		private String sysbp;
		private String diabp;
		
	private int appointmentid;
	ArrayList<Master> listAllLocations;
    ArrayList<TreatmentType> listSpecializations;

	private String dob;
	private String department;
	private ArrayList<UserProfile> allInvsUserList;

	private String patientName;

	private int selectedid;

	private int selectedPatientId;
	private ArrayList<Priscription> salesreportList;

	private File[] fileUpload;

	private File[] files;

	private File[] editfUpload;
	private String wardbed;
	private ArrayList<Priscription> doctorreportList;
	private String msg;

	private String clientname = "";

	private String condition;

	private String condition_id;

	private String conditionName;

	private String treatmentEpisodeName;
	private ArrayList<Vendor> vendorList;
	ArrayList<Master> medicineLocationList;
	ArrayList<TreatmentType> specializationList;
	
	private String specialization="0";
	private String reqspecialization="0";
	private String location="0";
	private String risk="0";
	private String genericname;
	

	private String clientData;
	private String vat;
	public Collection<Product> allproduct;
	private String discount;

	private String medicalRecordType;

	private String caldate = "";

	private String action = "";

	private String medicalHistoryNotes;
	private String medicalRecordTypeOther;
	private String medicalRecordId;
	private String deleteMedicalId;
	private String editDoctId;
	private String deleteDoctId;
	private boolean apmtChk;
	private int isvideo;
	private String odconditionstr;
	private String opdchkcondition;

	// Investigation Master name

	private InputStream dicomimageData;
	private String imgid;
	
	private String ipdid;
	
	private String name;
	private String specimen;
	private String report_type;
	private String unit;
	private String normal_value;
	private ArrayList<Master> investigation_name_list;
	
	private ArrayList<Priscription> priscriptiondetails;
	private boolean roundcharge;
	
	private ArrayList<EmailTemplate>smsTemplateList;
	
	private ArrayList<Master>medicineList;
	
	private ArrayList<Master> reporttype_list;
	
	private ArrayList<Priscription>parentPriscList;
	
	private ArrayList<Master>cbcIdList;
	
	 private String hour;
	    
    private String minute;
    
    private ArrayList<String>hourList;
    
    private ArrayList<String>minuteList;
	
	private String dischargedate;
	
	private String editdischargedate;
	
	private ArrayList<Master>dosagenoteList;
	
	private String pathLabuser;
	
	private String qualification;
	private String reqqualification;
	
	
	private String searchText = "";
	
	private String invsttype;
	
	
	 private String wardname;
	    
	 private String bedname;
	
	private String filterdoctType = "0";
	
	private String sharedmob = "0";
	
	private int checkconfidential = 0;
	
	private String confdentialpass = "0";
	
	private String charge="0";
	
	private String transactionType;
	private String mrp="0";
    private String sale_price="0";
    private String purchase_price="0";
	
    private String fromdate;
    private String todate;
	
    
    private String expiry_date;
    private String pkg;
    private String batch_no;
    private String mfg;
    private int billno;
    private ArrayList<Accounts> chargeProcessingList;
    private ArrayList<InvstTemplate>templateList;
    private String selectedinvsttype;
    private String selectedtemplateid;
    private  String postcode;
    private String mobno;;
    private ArrayList<Product> requestedMedicineList;
    private String selectedclientid;
    
    private String fromDate = "";
	private String toDate = "";
    private String subtotal="0";
    
    
    private String initial;
	private String firstname;
	private String lastname;
	private String city;
	
	ArrayList<Master>assetList;
	ArrayList<Priscription>oteqtemplateNameList;
	ArrayList<Emr>listot;
	ArrayList<Master>disciplineList;
	private String diciplineName;
	
	private ArrayList<NotAvailableSlot> otformlist;
	
	private File[] relativefiles;
    
	private String[] relativefilesFileName;
	


	public File[] getRelativefiles() {
		return relativefiles;
	}

	public void setRelativefiles(File[] relativefiles) {
		this.relativefiles = relativefiles;
	}

	public String[] getRelativefilesFileName() {
		return relativefilesFileName;
	}

	public void setRelativefilesFileName(String[] relativefilesFileName) {
		this.relativefilesFileName = relativefilesFileName;
	}

	public ArrayList<NotAvailableSlot> getOtformlist() {
		return otformlist;
	}

	public void setOtformlist(ArrayList<NotAvailableSlot> otformlist) {
		this.otformlist = otformlist;
	}

	public String getDiciplineName() {
		return diciplineName;
	}

	public void setDiciplineName(String diciplineName) {
		this.diciplineName = diciplineName;
	}

	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public ArrayList<Emr> getListot() {
		return listot;
	}

	public void setListot(ArrayList<Emr> listot) {
		this.listot = listot;
	}

	public ArrayList<Priscription> getOteqtemplateNameList() {
		return oteqtemplateNameList;
	}

	public void setOteqtemplateNameList(ArrayList<Priscription> oteqtemplateNameList) {
		this.oteqtemplateNameList = oteqtemplateNameList;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getSelectedclientid() {
		return selectedclientid;
	}

	public void setSelectedclientid(String selectedclientid) {
		this.selectedclientid = selectedclientid;
	}

	public String getSelectedinvsttype() {
		return selectedinvsttype;
	}

	public void setSelectedinvsttype(String selectedinvsttype) {
		this.selectedinvsttype = selectedinvsttype;
	}

	public String getSelectedtemplateid() {
		return selectedtemplateid;
	}

	public void setSelectedtemplateid(String selectedtemplateid) {
		this.selectedtemplateid = selectedtemplateid;
	}

	public ArrayList<InvstTemplate> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(ArrayList<InvstTemplate> templateList) {
		this.templateList = templateList;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getMfg() {
		return mfg;
	}

	public void setMfg(String mfg) {
		this.mfg = mfg;
	}

	public String getImgid() {
		return imgid;
	}

	public void setImgid(String imgid) {
		this.imgid = imgid;
	}

	public InputStream getDicomimageData() {
		return dicomimageData;
	}

	public void setDicomimageData(InputStream dicomimageData) {
		this.dicomimageData = dicomimageData;
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

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getSale_price() {
		return sale_price;
	}

	public void setSale_price(String sale_price) {
		this.sale_price = sale_price;
	}

	public String getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(String purchase_price) {
		this.purchase_price = purchase_price;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getConfdentialpass() {
		return confdentialpass;
	}

	public void setConfdentialpass(String confdentialpass) {
		this.confdentialpass = confdentialpass;
	}

	public int getCheckconfidential() {
		return checkconfidential;
	}

	public void setCheckconfidential(int checkconfidential) {
		this.checkconfidential = checkconfidential;
	}

	public String getSharedmob() {
		return sharedmob;
	}

	public void setSharedmob(String sharedmob) {
		this.sharedmob = sharedmob;
	}

	public String getFilterdoctType() {
		return filterdoctType;
	}

	public void setFilterdoctType(String filterdoctType) {
		this.filterdoctType = filterdoctType;
	}

	public String getWardname() {
		return wardname;
	}

	public void setWardname(String wardname) {
		this.wardname = wardname;
	}

	public String getBedname() {
		return bedname;
	}

	public void setBedname(String bedname) {
		this.bedname = bedname;
	}

	public String getInvsttype() {
		return invsttype;
	}

	public void setInvsttype(String invsttype) {
		this.invsttype = invsttype;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPathLabuser() {
		return pathLabuser;
	}

	public void setPathLabuser(String pathLabuser) {
		this.pathLabuser = pathLabuser;
	}

	public ArrayList<Master> getDosagenoteList() {
		return dosagenoteList;
	}

	public void setDosagenoteList(ArrayList<Master> dosagenoteList) {
		this.dosagenoteList = dosagenoteList;
	}

	public String getDischargedate() {
		return dischargedate;
	}

	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
	}

	public String getEditdischargedate() {
		return editdischargedate;
	}

	public void setEditdischargedate(String editdischargedate) {
		this.editdischargedate = editdischargedate;
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

	public ArrayList<Master> getCbcIdList() {
		return cbcIdList;
	}

	public void setCbcIdList(ArrayList<Master> cbcIdList) {
		this.cbcIdList = cbcIdList;
	}

	public ArrayList<Priscription> getParentPriscList() {
		return parentPriscList;
	}

	public void setParentPriscList(ArrayList<Priscription> parentPriscList) {
		this.parentPriscList = parentPriscList;
	}

	public ArrayList<Master> getReporttype_list() {
		return reporttype_list;
	}

	public void setReporttype_list(ArrayList<Master> reporttype_list) {
		this.reporttype_list = reporttype_list;
	}

	public ArrayList<Master> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(ArrayList<Master> medicineList) {
		this.medicineList = medicineList;
	}

	public ArrayList<EmailTemplate> getSmsTemplateList() {
		return smsTemplateList;
	}

	public void setSmsTemplateList(ArrayList<EmailTemplate> smsTemplateList) {
		this.smsTemplateList = smsTemplateList;
	}

	public ArrayList<Priscription> getPriscriptiondetails() {
		return priscriptiondetails;
	}

	public void setPriscriptiondetails(ArrayList<Priscription> priscriptiondetails) {
		this.priscriptiondetails = priscriptiondetails;
	}

	Collection<Priscription> prescription_details;
	

	public Collection<Priscription> getPrescription_details() {
		return prescription_details;
	}

	public void setPrescription_details(
			Collection<Priscription> prescription_details) {
		this.prescription_details = prescription_details;
	}

	private String description;
	private String categoryid;
	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Master> getInvestigation_name_list() {
		return investigation_name_list;
	}

	public void setInvestigation_name_list(
			ArrayList<Master> investigation_name_list) {
		this.investigation_name_list = investigation_name_list;
	}


	private ArrayList<Priscription> prescriptioncategorylist;
	public ArrayList<Priscription> getPrescriptioncategorylist() {
		return prescriptioncategorylist;
	}

	public void setPrescriptioncategorylist(
			ArrayList<Priscription> prescriptioncategorylist) {
		this.prescriptioncategorylist = prescriptioncategorylist;
	}

	
	public Collection<Priscription> prescription_category;
	
	public Collection<Priscription> getPrescription_category() {
		return prescription_category;
	}

	public void setPrescription_category(
			Collection<Priscription> prescription_category) {
		this.prescription_category = prescription_category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecimen() {
		return specimen;
	}

	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}

	public String getReport_type() {
		return report_type;
	}

	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNormal_value() {
		return normal_value;
	}

	public void setNormal_value(String normal_value) {
		this.normal_value = normal_value;
	}

	private String conditionsApmt = "";
	ArrayList<Emr> videoList;

	private String treatmentEpisodeid = "";

	private String dischrgeOutcomes;

	private ArrayList<Discharge> dischargeOutcomeList;

	private String dischargeStatus;

	private ArrayList<Discharge> dischargeStatusList;

	private boolean chkDischarge;

	private boolean repeat;

	private String priscdate;

	private ArrayList<Master> mdicinecategoryList;
	private String mdicinecategory;
	private String dosenoteid="0";

	private String clinicName;
	private String clinicOwner;
	private String clinicemail;
	private String clinicaddress;
	private String clinicity;
	private String websiteUrl;
	private String landLine;
	private String owner_qualification;
	
	private String userid;
	private String password;
	
	private ArrayList<Clinic> locationAdressList;

	ArrayList<Master> dosageList;

	private String priscdateandtime;

	private ArrayList<Client> treatmentTypeList;
	private ArrayList<UserProfile> pharmacyUserList;

	private String consCondition;

	private ArrayList<Master> invsTypeList;

	private ArrayList<Master> invstReportTypeList;

	private ArrayList<Master> invstUnitList;

	private ArrayList<Priscription> priscriptionlist;

	private ArrayList<Master> masterlist;
	private String totalReceived="0";
	private String totalRefund="0";
	private String totalBalance="0";

	private String mastername;
    private String drug;
    private String strength;
    
    private String fullname;
    private String tinno;
    private String dlno;
    
    private ArrayList<Master> otherTemplateList;
    
    private String message;
    
    private String ageandgender;
    
    private ArrayList<Investigation>selectedInvstList;
    
    private String reportType;
    
    private ArrayList<Investigation> investigationList;
    private ArrayList<Priscription>templateNameList; 
   
    private String group;
   
    ArrayList<ThirdParty> thirdPartyTypeList;
    
    private Collection<Priscription> medicines;
    ArrayList<String> initialList;
    ArrayList<String> countryList;
    
    private String notes;
    ArrayList<Client> refrenceList;
    
    ArrayList<Client> condtitionList;
    
    ArrayList<Client> surgeryList;
    
    String doctorname;
    private String total="0";
    
    private ArrayList<String> jobTitleList;
    
    private String jobtitle;
    
    private ArrayList<AppointmentType>additionalChargeList;
    
    private ArrayList<Master>masterChageTypeList;
    
    private String masterchargetype;
    
    private ArrayList<Location>locationList;
    
    private String locationid;
    
    
    private String hdnphclientid;
    
    public String getHdnphclientid() {
		return hdnphclientid;
	}

	public void setHdnphclientid(String hdnphclientid) {
		this.hdnphclientid = hdnphclientid;
	}

	public String getHdnispharmacy() {
		return hdnispharmacy;
	}

	public void setHdnispharmacy(String hdnispharmacy) {
		this.hdnispharmacy = hdnispharmacy;
	}

	private String hdnispharmacy;
   
    
    private ArrayList<Priscription> medicineChargeList; 
   
	public ArrayList<Priscription> getMedicineChargeList() {
		return medicineChargeList;
	}

	public void setMedicineChargeList(ArrayList<Priscription> medicineChargeList) {
		this.medicineChargeList = medicineChargeList;
	}

	public ArrayList<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public ArrayList<AppointmentType> getAdditionalChargeList() {
		return additionalChargeList;
	}

	public void setAdditionalChargeList(
			ArrayList<AppointmentType> additionalChargeList) {
		this.additionalChargeList = additionalChargeList;
	}

	public ArrayList<Master> getMasterChageTypeList() {
		return masterChageTypeList;
	}

	public void setMasterChageTypeList(ArrayList<Master> masterChageTypeList) {
		this.masterChageTypeList = masterChageTypeList;
	}

	public String getMasterchargetype() {
		return masterchargetype;
	}

	public void setMasterchargetype(String masterchargetype) {
		this.masterchargetype = masterchargetype;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public ArrayList<String> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(ArrayList<String> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public ArrayList<Client> getSurgeryList() {
		return surgeryList;
	}

	public void setSurgeryList(ArrayList<Client> surgeryList) {
		this.surgeryList = surgeryList;
	}

	public ArrayList<Client> getCondtitionList() {
		return condtitionList;
	}

	public void setCondtitionList(ArrayList<Client> condtitionList) {
		this.condtitionList = condtitionList;
	}

	public ArrayList<Client> getRefrenceList() {
		return refrenceList;
	}

	public void setRefrenceList(ArrayList<Client> refrenceList) {
		this.refrenceList = refrenceList;
	}

	public ArrayList<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<String> countryList) {
		this.countryList = countryList;
	}

	public ArrayList<String> getInitialList() {
		return initialList;
	}

	public void setInitialList(ArrayList<String> initialList) {
		this.initialList = initialList;
	}

	public ArrayList<ThirdParty> getThirdPartyTypeList() {
		return thirdPartyTypeList;
	}

	public void setThirdPartyTypeList(ArrayList<ThirdParty> thirdPartyTypeList) {
		this.thirdPartyTypeList = thirdPartyTypeList;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public ArrayList<Investigation> getInvestigationList() {
		return investigationList;
	}

	public void setInvestigationList(ArrayList<Investigation> investigationList) {
		this.investigationList = investigationList;
	}

	public ArrayList<Investigation> getSelectedInvstList() {
		return selectedInvstList;
	}

	public void setSelectedInvstList(ArrayList<Investigation> selectedInvstList) {
		this.selectedInvstList = selectedInvstList;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getAgeandgender() {
		return ageandgender;
	}

	public void setAgeandgender(String ageandgender) {
		this.ageandgender = ageandgender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public ArrayList<Master> getOtherTemplateList() {
		return otherTemplateList;
	}

	public void setOtherTemplateList(ArrayList<Master> otherTemplateList) {
		this.otherTemplateList = otherTemplateList;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	private Collection<Master> investigation_type;
	private Collection<Master> investigation_name;


	private String pagerecords;
	private int totalRecords;

	
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

	public Collection<Master> getInvestigation_name() {
		return investigation_name;
	}

	public void setInvestigation_name(Collection<Master> investigation_name) {
		this.investigation_name = investigation_name;
	}

	public Collection<Master> getInvestigation_type() {
		return investigation_type;
	}

	public void setInvestigation_type(Collection<Master> investigation_type) {
		this.investigation_type = investigation_type;
	}

	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}

	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}

	public ArrayList<Priscription> getPriscriptionlist() {
		return priscriptionlist;
	}

	public void setPriscriptionlist(ArrayList<Priscription> priscriptionlist) {
		this.priscriptionlist = priscriptionlist;
	}

	private String investigation_type_id;

	public String getInvestigation_type_id() {
		return investigation_type_id;
	}

	public void setInvestigation_type_id(String investigation_type_id) {
		this.investigation_type_id = investigation_type_id;
	}

	public ArrayList<Master> getInvstUnitList() {
		return invstUnitList;
	}

	public void setInvstUnitList(ArrayList<Master> invstUnitList) {
		this.invstUnitList = invstUnitList;
	}

	public ArrayList<Master> getInvstReportTypeList() {
		return invstReportTypeList;
	}

	public void setInvstReportTypeList(ArrayList<Master> invstReportTypeList) {
		this.invstReportTypeList = invstReportTypeList;
	}

	public ArrayList<Master> getInvsTypeList() {
		return invsTypeList;
	}

	public void setInvsTypeList(ArrayList<Master> invsTypeList) {
		this.invsTypeList = invsTypeList;
	}

	public ArrayList<Client> getTreatmentTypeList() {
		return treatmentTypeList;
	}

	public void setTreatmentTypeList(ArrayList<Client> treatmentTypeList) {
		this.treatmentTypeList = treatmentTypeList;
	}

	public String getConsCondition() {
		return consCondition;
	}

	public void setConsCondition(String consCondition) {
		this.consCondition = consCondition;
	}

	public String getPriscdateandtime() {
		return priscdateandtime;
	}

	public void setPriscdateandtime(String priscdateandtime) {
		this.priscdateandtime = priscdateandtime;
	}

	public ArrayList<Master> getDosageList() {
		return dosageList;
	}

	public void setDosageList(ArrayList<Master> dosageList) {
		this.dosageList = dosageList;
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

	public String getPriscdate() {
		return priscdate;
	}

	public void setPriscdate(String priscdate) {
		this.priscdate = priscdate;
	}

	public ArrayList<Master> getMdicinecategoryList() {
		return mdicinecategoryList;
	}

	public void setMdicinecategoryList(ArrayList<Master> mdicinecategoryList) {
		this.mdicinecategoryList = mdicinecategoryList;
	}

	public String getMdicinecategory() {
		return mdicinecategory;
	}

	public void setMdicinecategory(String mdicinecategory) {
		this.mdicinecategory = mdicinecategory;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public boolean isChkDischarge() {
		return chkDischarge;
	}

	public void setChkDischarge(boolean chkDischarge) {
		this.chkDischarge = chkDischarge;
	}

	public String getDischrgeOutcomes() {
		return dischrgeOutcomes;
	}

	public void setDischrgeOutcomes(String dischrgeOutcomes) {
		this.dischrgeOutcomes = dischrgeOutcomes;
	}

	public ArrayList<Discharge> getDischargeOutcomeList() {
		return dischargeOutcomeList;
	}

	public void setDischargeOutcomeList(
			ArrayList<Discharge> dischargeOutcomeList) {
		this.dischargeOutcomeList = dischargeOutcomeList;
	}

	public String getDischargeStatus() {
		return dischargeStatus;
	}

	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	public ArrayList<Discharge> getDischargeStatusList() {
		return dischargeStatusList;
	}

	public void setDischargeStatusList(ArrayList<Discharge> dischargeStatusList) {
		this.dischargeStatusList = dischargeStatusList;
	}

	public String getTreatmentEpisodeid() {
		return treatmentEpisodeid;
	}

	public void setTreatmentEpisodeid(String treatmentEpisodeid) {
		this.treatmentEpisodeid = treatmentEpisodeid;
	}

	public ArrayList<Emr> getVideoList() {
		return videoList;
	}

	public void setVideoList(ArrayList<Emr> videoList) {
		this.videoList = videoList;
	}

	public String getConditionsApmt() {
		return conditionsApmt;
	}

	public void setConditionsApmt(String conditionsApmt) {
		this.conditionsApmt = conditionsApmt;
	}

	public int getIsvideo() {
		return isvideo;
	}

	public void setIsvideo(int isvideo) {
		this.isvideo = isvideo;
	}

	public String getCaldate() {
		return caldate;
	}

	public void setCaldate(String caldate) {
		this.caldate = caldate;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isApmtChk() {
		return apmtChk;
	}

	public void setApmtChk(boolean apmtChk) {
		this.apmtChk = apmtChk;
	}

	public String getDeleteDoctId() {
		return deleteDoctId;
	}

	public void setDeleteDoctId(String deleteDoctId) {
		this.deleteDoctId = deleteDoctId;
	}

	public String getEditDoctId() {
		return editDoctId;
	}

	public void setEditDoctId(String editDoctId) {
		this.editDoctId = editDoctId;
	}

	public String getDeleteMedicalId() {
		return deleteMedicalId;
	}

	public void setDeleteMedicalId(String deleteMedicalId) {
		this.deleteMedicalId = deleteMedicalId;
	}

	public String getMedicalRecordId() {
		return medicalRecordId;
	}

	public void setMedicalRecordId(String medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}

	ArrayList<Assessment> importImageList;
	private ArrayList<Assessment> imageDataList;
	private ArrayList<Assessment> assessmentFormsList;

	public ArrayList<Assessment> getAssessmentFormsList() {
		return assessmentFormsList;
	}

	public void setAssessmentFormsList(ArrayList<Assessment> assessmentFormsList) {
		this.assessmentFormsList = assessmentFormsList;
	}

	public ArrayList<Assessment> getImageDataList() {
		return imageDataList;
	}

	public void setImageDataList(ArrayList<Assessment> imageDataList) {
		this.imageDataList = imageDataList;
	}

	private int consulatation_note_id;
	private String clientImageDataId;
	private String clientImageData;
	private String clientDataId;

	public String getClientDataId() {
		return clientDataId;
	}

	public void setClientDataId(String clientDataId) {
		this.clientDataId = clientDataId;
	}

	public String getClientImageData() {
		return clientImageData;
	}

	public void setClientImageData(String clientImageData) {
		this.clientImageData = clientImageData;
	}

	private String imagedata;

	public String getClientImageDataId() {
		return clientImageDataId;
	}

	public void setClientImageDataId(String clientImageDataId) {
		this.clientImageDataId = clientImageDataId;
	}

	public String getImagedata() {
		return imagedata;
	}

	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}

	public int getConsulatation_note_id() {
		return consulatation_note_id;
	}

	public void setConsulatation_note_id(int consulatation_note_id) {
		this.consulatation_note_id = consulatation_note_id;
	}

	public ArrayList<Assessment> getImportImageList() {
		return importImageList;
	}

	public void setImportImageList(ArrayList<Assessment> importImageList) {
		this.importImageList = importImageList;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String getMedicalRecordTypeOther() {
		return medicalRecordTypeOther;
	}

	public void setMedicalRecordTypeOther(String medicalRecordTypeOther) {
		this.medicalRecordTypeOther = medicalRecordTypeOther;
	}

	private String record_type;
	ArrayList<Emr> medicalRecordsTypeList;

	public ArrayList<Emr> getMedicalRecordsTypeList() {
		return medicalRecordsTypeList;
	}

	public void setMedicalRecordsTypeList(ArrayList<Emr> medicalRecordsTypeList) {
		this.medicalRecordsTypeList = medicalRecordsTypeList;
	}

	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}

	private Collection<MedicalHistory> medicalHistory;

	public String getMedicalRecordType() {
		return medicalRecordType;
	}

	public void setMedicalRecordType(String medicalRecordType) {
		this.medicalRecordType = medicalRecordType;
	}

	public String getMedicalHistoryNotes() {
		return medicalHistoryNotes;
	}

	public void setMedicalHistoryNotes(String medicalHistoryNotes) {
		this.medicalHistoryNotes = medicalHistoryNotes;
	}

	public Collection<MedicalHistory> getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(Collection<MedicalHistory> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	private ArrayList<Emr> medicalRecordTypeList;

	public ArrayList<Emr> getMedicalRecordTypeList() {
		return medicalRecordTypeList;
	}

	public void setMedicalRecordTypeList(ArrayList<Emr> medicalRecordTypeList) {
		this.medicalRecordTypeList = medicalRecordTypeList;
	}

	private ArrayList<TreatmentEpisode> treatmentEpisodeList;

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList() {
		return treatmentEpisodeList;
	}

	public void setTreatmentEpisodeList(
			ArrayList<TreatmentEpisode> treatmentEpisodeList) {
		this.treatmentEpisodeList = treatmentEpisodeList;
	}

	public String getClientData() {
		return clientData;
	}

	public void setClientData(String clientData) {
		this.clientData = clientData;
	}

	public String getTreatmentEpisodeName() {
		return treatmentEpisodeName;
	}

	public void setTreatmentEpisodeName(String treatmentEpisodeName) {
		this.treatmentEpisodeName = treatmentEpisodeName;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getCondition_id() {
		return condition_id;
	}

	public void setCondition_id(String condition_id) {
		this.condition_id = condition_id;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	// private String

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public File[] getEditfUpload() {
		return editfUpload;
	}

	public void setEditfUpload(File[] editfUpload) {
		this.editfUpload = editfUpload;
	}

	private String[] fileUploadFileName;

	private String[] filesFileName;

	private String[] editfUploadFileName;

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String[] getEditfUploadFileName() {
		return editfUploadFileName;
	}

	public void setEditfUploadFileName(String[] editfUploadFileName) {
		this.editfUploadFileName = editfUploadFileName;
	}

	public String[] getEditfileUploadContentType() {
		return editfileUploadContentType;
	}

	public void setEditfileUploadContentType(String[] editfileUploadContentType) {
		this.editfileUploadContentType = editfileUploadContentType;
	}

	private String[] fileUploadContentType;

	private String[] editfileUploadContentType;

	private ArrayList<Emr> medicalHistoryList;

	private ArrayList<Emr> consultationList;

	private ArrayList<Emr> socialHistoryList;

	private ArrayList<Emr> reminderList;

	private ArrayList<Emr> prescriptionList;

	private ArrayList<Emr> allergyList;

	private ArrayList<Emr> docList;
	
	ArrayList<Master>invSectionList;
	
	ArrayList<Master>masterInvstTypeList;
	
	private String sectionName;

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public ArrayList<Master> getMasterInvstTypeList() {
		return masterInvstTypeList;
	}

	public void setMasterInvstTypeList(ArrayList<Master> masterInvstTypeList) {
		this.masterInvstTypeList = masterInvstTypeList;
	}

	public ArrayList<Master> getInvSectionList() {
		return invSectionList;
	}

	public void setInvSectionList(ArrayList<Master> invSectionList) {
		this.invSectionList = invSectionList;
	}

	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}

	public ArrayList<Client> getConditionList() {
		return conditionList;
	}

	public void setConditionList(ArrayList<Client> conditionList) {
		this.conditionList = conditionList;
	}

	ArrayList<Client> clientList;
	ArrayList<Client> conditionList;

	public ArrayList<Emr> getDocList() {
		return docList;
	}

	public void setDocList(ArrayList<Emr> docList) {
		this.docList = docList;
	}

	public File[] getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File[] fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String[] getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String[] fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String[] getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String[] fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public ArrayList<Emr> getAllergyList() {
		return allergyList;
	}

	public void setAllergyList(ArrayList<Emr> allergyList) {
		this.allergyList = allergyList;
	}

	public ArrayList<Emr> getPrescriptionList() {
		return prescriptionList;
	}

	public void setPrescriptionList(ArrayList<Emr> prescriptionList) {
		this.prescriptionList = prescriptionList;
	}

	public ArrayList<Emr> getReminderList() {
		return reminderList;
	}

	public void setReminderList(ArrayList<Emr> reminderList) {
		this.reminderList = reminderList;
	}

	public ArrayList<Emr> getSocialHistoryList() {
		return socialHistoryList;
	}

	public void setSocialHistoryList(ArrayList<Emr> socialHistoryList) {
		this.socialHistoryList = socialHistoryList;
	}

	private String practitionerName;
	private String documentDesc;
	private String doctType;
	private String consNote;
	private String apmtId = "";

	private File userImage;

	private String userImageContentType;

	private String userImageFileName;
	
	private ArrayList<Bed>addmissionList;
	
	private ArrayList<Bed>ipdsdischargeList;
	
	private  String emrClientName;
	private String emrDoctorName;
	private String emrConditionName;
	
	
	
	

	public String getEmrClientName() {
		return emrClientName;
	}

	public void setEmrClientName(String emrClientName) {
		this.emrClientName = emrClientName;
	}

	public String getEmrDoctorName() {
		return emrDoctorName;
	}

	public void setEmrDoctorName(String emrDoctorName) {
		this.emrDoctorName = emrDoctorName;
	}

	public String getEmrConditionName() {
		return emrConditionName;
	}

	public void setEmrConditionName(String emrConditionName) {
		this.emrConditionName = emrConditionName;
	}

	public ArrayList<Bed> getIpdsdischargeList() {
		return ipdsdischargeList;
	}

	public void setIpdsdischargeList(ArrayList<Bed> ipdsdischargeList) {
		this.ipdsdischargeList = ipdsdischargeList;
	}

	public ArrayList<Bed> getAddmissionList() {
		return addmissionList;
	}

	public void setAddmissionList(ArrayList<Bed> addmissionList) {
		this.addmissionList = addmissionList;
	}

	

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public String getConsNote() {
		return consNote;
	}

	public void setConsNote(String consNote) {
		this.consNote = consNote;
	}

	public String getApmtId() {
		return apmtId;
	}

	public void setApmtId(String apmtId) {
		this.apmtId = apmtId;
	}

	public String getDoctType() {
		return doctType;
	}

	public void setDoctType(String doctType) {
		this.doctType = doctType;
	}

	public String getDocumentDesc() {
		return documentDesc;
	}

	public void setDocumentDesc(String documentDesc) {
		this.documentDesc = documentDesc;
	}

	private ArrayList<DiaryManagement> userList;

	private String diaryUser = "";
	
	private String reqdiaryUser = "";

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public ArrayList<Emr> getConsultationList() {
		return consultationList;
	}

	public void setConsultationList(ArrayList<Emr> consultationList) {
		this.consultationList = consultationList;
	}

	public ArrayList<Emr> getMedicalHistoryList() {
		return medicalHistoryList;
	}

	public void setMedicalHistoryList(ArrayList<Emr> medicalHistoryList) {
		this.medicalHistoryList = medicalHistoryList;
	}

	public int getSelectedPatientId() {
		return selectedPatientId;
	}

	public void setSelectedPatientId(int selectedPatientId) {
		this.selectedPatientId = selectedPatientId;
	}

	public int getSelectedid() {
		return selectedid;
	}

	public void setSelectedid(int selectedid) {
		this.selectedid = selectedid;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public ArrayList<Emr> getEmrList() {
		return emrList;
	}

	public void setEmrList(ArrayList<Emr> emrList) {
		this.emrList = emrList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	// Client Details

	private String firstName = "";
	private String lastName = "";
	private String middleName = "";
	private String title = "";
	private String mobNo;
	private String email;
	private String town;
	private String country;
	private String address;
	private String sourceOfIntro;
	private String reference;
	private String postCode;
	private String sourceOfIntroName;
	private String clientId;
	private String client;
	private String gpname;
	private String treatmentType;
	private String policyExcess;
	private String employerName;
	private String dateTime;
	private String whopay;
	private String county;
	private String homeNo;
	private String workNo;
	private String thirdPartyCompanyName;
	private String occupation;

	private int requestedCount;
	
	ArrayList<Master> mdicneTypeList;
	
	private ArrayList<Master> specializationTemplateList;

	
	public ArrayList<Master> getSpecializationTemplateList() {
		return specializationTemplateList;
	}

	public void setSpecializationTemplateList(ArrayList<Master> specializationTemplateList) {
		this.specializationTemplateList = specializationTemplateList;
	}

	public ArrayList<Master> getMdicneTypeList() {
		return mdicneTypeList;
	}

	public void setMdicneTypeList(ArrayList<Master> mdicneTypeList) {
		this.mdicneTypeList = mdicneTypeList;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getThirdPartyCompanyName() {
		return thirdPartyCompanyName;
	}

	public void setThirdPartyCompanyName(String thirdPartyCompanyName) {
		this.thirdPartyCompanyName = thirdPartyCompanyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSourceOfIntro() {
		return sourceOfIntro;
	}

	public void setSourceOfIntro(String sourceOfIntro) {
		this.sourceOfIntro = sourceOfIntro;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getSourceOfIntroName() {
		return sourceOfIntroName;
	}

	public void setSourceOfIntroName(String sourceOfIntroName) {
		this.sourceOfIntroName = sourceOfIntroName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getGpname() {
		return gpname;
	}

	public void setGpname(String gpname) {
		this.gpname = gpname;
	}

	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	public String getPolicyExcess() {
		return policyExcess;
	}

	public void setPolicyExcess(String policyExcess) {
		this.policyExcess = policyExcess;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getWhopay() {
		return whopay;
	}

	public void setWhopay(String whopay) {
		this.whopay = whopay;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getHomeNo() {
		return homeNo;
	}

	public void setHomeNo(String homeNo) {
		this.homeNo = homeNo;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public String getWardbed() {
		return wardbed;
	}

	public void setWardbed(String wardbed) {
		this.wardbed = wardbed;
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

	public Collection<Priscription> getMedicines() {
		return medicines;
	}

	public void setMedicines(Collection<Priscription> medicines) {
		this.medicines = medicines;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public int getBillno() {
		return billno;
	}

	public void setBillno(int billno) {
		this.billno = billno;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public ArrayList<Accounts> getChargeProcessingList() {
		return chargeProcessingList;
	}

	public void setChargeProcessingList(ArrayList<Accounts> chargeProcessingList) {
		this.chargeProcessingList = chargeProcessingList;
	}

	public ArrayList<Priscription> getTemplateNameList() {
		return templateNameList;
	}

	public void setTemplateNameList(ArrayList<Priscription> templateNameList) {
		this.templateNameList = templateNameList;
	}

	public ArrayList<Product> getRequestedMedicineList() {
		return requestedMedicineList;
	}

	public void setRequestedMedicineList(ArrayList<Product> requestedMedicineList) {
		this.requestedMedicineList = requestedMedicineList;
	}

	public String getOdconditionstr() {
		return odconditionstr;
	}

	public void setOdconditionstr(String odconditionstr) {
		this.odconditionstr = odconditionstr;
	}

	public String getOpdchkcondition() {
		return opdchkcondition;
	}

	public void setOpdchkcondition(String opdchkcondition) {
		this.opdchkcondition = opdchkcondition;
	}

	public ArrayList<Master> getAssetList() {
		return assetList;
	}

	public void setAssetList(ArrayList<Master> assetList) {
		this.assetList = assetList;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ArrayList<UserProfile> getAllInvsUserList() {
		return allInvsUserList;
	}

	public void setAllInvsUserList(ArrayList<UserProfile> allInvsUserList) {
		this.allInvsUserList = allInvsUserList;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRefundamt() {
		return refundamt;
	}

	public void setRefundamt(String refundamt) {
		this.refundamt = refundamt;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public ArrayList<Priscription> getSalesreportList() {
		return salesreportList;
	}

	public void setSalesreportList(ArrayList<Priscription> salesreportList) {
		this.salesreportList = salesreportList;
	}

	public ArrayList<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorList(ArrayList<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	public Collection<Product> getAllproduct() {
		return allproduct;
	}

	public void setAllproduct(Collection<Product> allproduct) {
		this.allproduct = allproduct;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}

	public ArrayList<Priscription> getDoctorreportList() {
		return doctorreportList;
	}

	public void setDoctorreportList(ArrayList<Priscription> doctorreportList) {
		this.doctorreportList = doctorreportList;
	}

	public ArrayList<Master> getMedicineLocationList() {
		return medicineLocationList;
	}

	public void setMedicineLocationList(ArrayList<Master> medicineLocationList) {
		this.medicineLocationList = medicineLocationList;
	}

	public ArrayList<TreatmentType> getSpecializationList() {
		return specializationList;
	}

	public void setSpecializationList(ArrayList<TreatmentType> specializationList) {
		this.specializationList = specializationList;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getGenericname() {
		return genericname;
	}

	public void setGenericname(String genericname) {
		this.genericname = genericname;
	}

	public ArrayList<Master> getListAllLocations() {
		return listAllLocations;
	}

	public void setListAllLocations(ArrayList<Master> listAllLocations) {
		this.listAllLocations = listAllLocations;
	}

	public ArrayList<TreatmentType> getListSpecializations() {
		return listSpecializations;
	}

	public void setListSpecializations(ArrayList<TreatmentType> listSpecializations) {
		this.listSpecializations = listSpecializations;
	}

	public ArrayList<Product> getInventoryPriscList() {
		return inventoryPriscList;
	}

	public void setInventoryPriscList(ArrayList<Product> inventoryPriscList) {
		this.inventoryPriscList = inventoryPriscList;
	}

	public int getRequestedCount() {
		return requestedCount;
	}

	public void setRequestedCount(int requestedCount) {
		this.requestedCount = requestedCount;
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

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getSysbp() {
		return sysbp;
	}

	public void setSysbp(String sysbp) {
		this.sysbp = sysbp;
	}

	public String getDiabp() {
		return diabp;
	}

	public void setDiabp(String diabp) {
		this.diabp = diabp;
	}

	public ArrayList<Product> getSoldProductList() {
		return soldProductList;
	}

	public void setSoldProductList(ArrayList<Product> soldProductList) {
		this.soldProductList = soldProductList;
	}

	public String getTinno() {
		return tinno;
	}

	public void setTinno(String tinno) {
		this.tinno = tinno;
	}

	public String getDlno() {
		return dlno;
	}

	public void setDlno(String dlno) {
		this.dlno = dlno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<UserProfile> getPharmacyUserList() {
		return pharmacyUserList;
	}

	public void setPharmacyUserList(ArrayList<UserProfile> pharmacyUserList) {
		this.pharmacyUserList = pharmacyUserList;
	}

	public ArrayList<Master> getStatelist() {
		return statelist;
	}

	public void setStatelist(ArrayList<Master> statelist) {
		this.statelist = statelist;
	}

	public ArrayList<Master> getCitylist() {
		return citylist;
	}

	public void setCitylist(ArrayList<Master> citylist) {
		this.citylist = citylist;
	}

	public String getCardBill() {
		return cardBill;
	}

	public void setCardBill(String cardBill) {
		this.cardBill = cardBill;
	}

	public String getInstruction1() {
		return instruction1;
	}

	public void setInstruction1(String instruction1) {
		this.instruction1 = instruction1;
	}

	public String getInstruction2() {
		return instruction2;
	}

	public void setInstruction2(String instruction2) {
		this.instruction2 = instruction2;
	}

	public String getInstruction3() {
		return instruction3;
	}

	public void setInstruction3(String instruction3) {
		this.instruction3 = instruction3;
	}

	public String getInstruction4() {
		return instruction4;
	}

	public void setInstruction4(String instruction4) {
		this.instruction4 = instruction4;
	}

	public String getInst1() {
		return inst1;
	}

	public void setInst1(String inst1) {
		this.inst1 = inst1;
	}

	public String getInst2() {
		return inst2;
	}

	public void setInst2(String inst2) {
		this.inst2 = inst2;
	}

	public String getInst3() {
		return inst3;
	}

	public void setInst3(String inst3) {
		this.inst3 = inst3;
	}

	public String getInst4() {
		return inst4;
	}

	public void setInst4(String inst4) {
		this.inst4 = inst4;
	}

	public ArrayList<Priscription> getDailyuserreport() {
		return dailyuserreport;
	}

	public void setDailyuserreport(ArrayList<Priscription> dailyuserreport) {
		this.dailyuserreport = dailyuserreport;
	}

	public String getBillcount() {
		return billcount;
	}

	public void setBillcount(String billcount) {
		this.billcount = billcount;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTotalpayment() {
		return totalpayment;
	}

	public void setTotalpayment(String totalpayment) {
		this.totalpayment = totalpayment;
	}
	
	ArrayList<Product> pharmapatientlist;


	public ArrayList<Product> getPharmapatientlist() {
		return pharmapatientlist;
	}

	public void setPharmapatientlist(ArrayList<Product> pharmapatientlist) {
		this.pharmapatientlist = pharmapatientlist;
	}

	public String getExtpid() {
		return extpid;
	}

	public void setExtpid(String extpid) {
		this.extpid = extpid;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getIpdidcheck() {
		return ipdidcheck;
	}

	public void setIpdidcheck(String ipdidcheck) {
		this.ipdidcheck = ipdidcheck;
	}

	public String getOpdid() {
		return opdid;
	}

	public void setOpdid(String opdid) {
		this.opdid = opdid;
	}

	public String getPrebal() {
		return prebal;
	}

	public void setPrebal(String prebal) {
		this.prebal = prebal;
	} 
	private ArrayList<Priscription> ipdpatientlist;
	 private ArrayList<Priscription> ipdmedicineList;


	public ArrayList<Priscription> getIpdpatientlist() {
		return ipdpatientlist;
	}

	public void setIpdpatientlist(ArrayList<Priscription> ipdpatientlist) {
		this.ipdpatientlist = ipdpatientlist;
	}

	public ArrayList<Priscription> getIpdmedicineList() {
		return ipdmedicineList;
	}

	public void setIpdmedicineList(ArrayList<Priscription> ipdmedicineList) {
		this.ipdmedicineList = ipdmedicineList;
	}

	public boolean isRoundcharge() {
		return roundcharge;
	}

	public void setRoundcharge(boolean roundcharge) {
		this.roundcharge = roundcharge;
	}

	public String getPrevious_balance() {
		return previous_balance;
	}

	public void setPrevious_balance(String previous_balance) {
		this.previous_balance = previous_balance;
	}

	public ArrayList<Master> getLocationListPharmacy() {
		return locationListPharmacy;
	}

	public void setLocationListPharmacy(ArrayList<Master> locationListPharmacy) {
		this.locationListPharmacy = locationListPharmacy;
	}

	public String getIpdlocation() {
		return ipdlocation;
	}

	public void setIpdlocation(String ipdlocation) {
		this.ipdlocation = ipdlocation;
	}

	public String getOpdlocation() {
		return opdlocation;
	}

	public void setOpdlocation(String opdlocation) {
		this.opdlocation = opdlocation;
	}

	public String getProcurementType() {
		return procurementType;
	}

	public void setProcurementType(String procurementType) {
		this.procurementType = procurementType;
	}

	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getComplete_date() {
		return complete_date;
	}

	public void setComplete_date(String complete_date) {
		this.complete_date = complete_date;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public String getPayamt() {
		return payamt;
	}

	public void setPayamt(String payamt) {
		this.payamt = payamt;
	}

	public String getInvoiceTime() {
		return invoiceTime;
	}

	public void setInvoiceTime(String invoiceTime) {
		this.invoiceTime = invoiceTime;
	}

	public String getTodaycash() {
		return todaycash;
	}

	public void setTodaycash(String todaycash) {
		this.todaycash = todaycash;
	}

	public String getTodaycard() {
		return todaycard;
	}

	public void setTodaycard(String todaycard) {
		this.todaycard = todaycard;
	}

	public String getTodaydisc() {
		return todaydisc;
	}

	public void setTodaydisc(String todaydisc) {
		this.todaydisc = todaydisc;
	}

	public String getTodayReturn() {
		return todayReturn;
	}

	public void setTodayReturn(String todayReturn) {
		this.todayReturn = todayReturn;
	}

	public int getIsreturn() {
		return isreturn;
	}

	public void setIsreturn(int isreturn) {
		this.isreturn = isreturn;
	}

	public String getTotalCardRefund() {
		return totalCardRefund;
	}

	public void setTotalCardRefund(String totalCardRefund) {
		this.totalCardRefund = totalCardRefund;
	}

	public String getNetcard() {
		return netcard;
	}

	public void setNetcard(String netcard) {
		this.netcard = netcard;
	}

	public String getNetcash() {
		return netcash;
	}

	public void setNetcash(String netcash) {
		this.netcash = netcash;
	}

	public String getIpdoropd() {
		return ipdoropd;
	}

	public void setIpdoropd(String ipdoropd) {
		this.ipdoropd = ipdoropd;
	}

	public String getInhousepatient() {
		return inhousepatient;
	}

	public void setInhousepatient(String inhousepatient) {
		this.inhousepatient = inhousepatient;
	}

	public String getNetReceivedTotal() {
		return netReceivedTotal;
	}

	public void setNetReceivedTotal(String netReceivedTotal) {
		this.netReceivedTotal = netReceivedTotal;
	}

	public ArrayList<Priscription> getLocationWiseReport() {
		return locationWiseReport;
	}

	public void setLocationWiseReport(ArrayList<Priscription> locationWiseReport) {
		this.locationWiseReport = locationWiseReport;
	}

	public String getNettotal() {
		return nettotal;
	}

	public void setNettotal(String nettotal) {
		this.nettotal = nettotal;
	}

	public String getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(String creditTotal) {
		this.creditTotal = creditTotal;
	}

	public String getEstimatebill() {
		return estimatebill;
	}

	public void setEstimatebill(String estimatebill) {
		this.estimatebill = estimatebill;
	}

	public String getTpid() {
		return tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

	public String getBack_date_access() {
		return back_date_access;
	}

	public void setBack_date_access(String back_date_access) {
		this.back_date_access = back_date_access;
	}

	public String getTp_bill_access() {
		return tp_bill_access;
	}

	public void setTp_bill_access(String tp_bill_access) {
		this.tp_bill_access = tp_bill_access;
	}

	public ArrayList<Product> getParenttransferlist() {
		return parenttransferlist;
	}

	public void setParenttransferlist(ArrayList<Product> parenttransferlist) {
		this.parenttransferlist = parenttransferlist;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public ArrayList<Product> getBrandnameList() {
		return brandnameList;
	}

	public void setBrandnameList(ArrayList<Product> brandnameList) {
		this.brandnameList = brandnameList;
	}

	public ArrayList<Master> getMedicineTypeList() {
		return medicineTypeList;
	}

	public void setMedicineTypeList(ArrayList<Master> medicineTypeList) {
		this.medicineTypeList = medicineTypeList;
	}

	public ArrayList<Master> getCellList() {
		return cellList;
	}

	public void setCellList(ArrayList<Master> cellList) {
		this.cellList = cellList;
	}

	public String getChequepayment() {
		return chequepayment;
	}

	public void setChequepayment(String chequepayment) {
		this.chequepayment = chequepayment;
	}

	public String getRequisition_auth() {
		return requisition_auth;
	}

	public void setRequisition_auth(String requisition_auth) {
		this.requisition_auth = requisition_auth;
	}

	public String getNeftpayment() {
		return neftpayment;
	}

	public void setNeftpayment(String neftpayment) {
		this.neftpayment = neftpayment;
	}

	public String getTotalReceived() {
		return totalReceived;
	}

	public void setTotalReceived(String totalReceived) {
		this.totalReceived = totalReceived;
	}

	public String getTotalRefund() {
		return totalRefund;
	}

	public void setTotalRefund(String totalRefund) {
		this.totalRefund = totalRefund;
	}

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	

	public ArrayList<Master> getAlljobtitlelist() {
		return alljobtitlelist;
	}

	public void setAlljobtitlelist(ArrayList<Master> alljobtitlelist) {
		this.alljobtitlelist = alljobtitlelist;
	}

	public String getReturnmode() {
		return returnmode;
	}

	public void setReturnmode(String returnmode) {
		this.returnmode = returnmode;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getEdited() {
		return edited;
	}

	public String getEdit_note() {
		return edit_note;
	}

	public void setEdit_note(String edit_note) {
		this.edit_note = edit_note;
	}

	public void setEdited(int edited) {
		this.edited = edited;
	}

	public ArrayList<Priscription> getClearBillList() {
		return clearBillList;
	}

	public void setClearBillList(ArrayList<Priscription> clearBillList) {
		this.clearBillList = clearBillList;
	}

	public String getPrintedby() {
		return printedby;
	}

	public void setPrintedby(String printedby) {
		this.printedby = printedby;
	}

	public String getReqspecialization() {
		return reqspecialization;
	}

	public void setReqspecialization(String reqspecialization) {
		this.reqspecialization = reqspecialization;
	}

	public String getReqqualification() {
		return reqqualification;
	}

	public void setReqqualification(String reqqualification) {
		this.reqqualification = reqqualification;
	}

	public String getReqdiaryUser() {
		return reqdiaryUser;
	}

	public void setReqdiaryUser(String reqdiaryUser) {
		this.reqdiaryUser = reqdiaryUser;
	}

	public String getReturnbill() {
		return returnbill;
	}

	public void setReturnbill(String returnbill) {
		this.returnbill = returnbill;
	}

	public String getPriscid() {
		return priscid;
	}

	public void setPriscid(String priscid) {
		this.priscid = priscid;
	}

	public int getRefundid() {
		return refundid;
	}

	public void setRefundid(int refundid) {
		this.refundid = refundid;
	}

	public String getDosenoteid() {
		return dosenoteid;
	}

	public void setDosenoteid(String dosenoteid) {
		this.dosenoteid = dosenoteid;
	}

	public String getDosagenote() {
		return dosagenote;
	}

	public void setDosagenote(String dosagenote) {
		this.dosagenote = dosagenote;
	}

	public ArrayList<Priscription> getPrisMoleculessList() {
		return prisMoleculessList;
	}

	public void setPrisMoleculessList(ArrayList<Priscription> prisMoleculessList) {
		this.prisMoleculessList = prisMoleculessList;
	}

	public ArrayList<Assessment> getAssesmenttemplateNameList() {
		return assesmenttemplateNameList;
	}

	public void setAssesmenttemplateNameList(ArrayList<Assessment> assesmenttemplateNameList) {
		this.assesmenttemplateNameList = assesmenttemplateNameList;
	}

	public ArrayList<Emr> getEmrDocumentList() {
		return emrDocumentList;
	}

	public void setEmrDocumentList(ArrayList<Emr> emrDocumentList) {
		this.emrDocumentList = emrDocumentList;
	}

	public ArrayList<Emr> getIpdDocumentList() {
		return ipdDocumentList;
	}

	public void setIpdDocumentList(ArrayList<Emr> ipdDocumentList) {
		this.ipdDocumentList = ipdDocumentList;
	}

	public ArrayList<Emr> getOpdDocumentList() {
		return opdDocumentList;
	}

	public void setOpdDocumentList(ArrayList<Emr> opdDocumentList) {
		this.opdDocumentList = opdDocumentList;
	}

	public ArrayList<Master> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(ArrayList<Master> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public ArrayList<Emr> getDocumentDetailedList() {
		return documentDetailedList;
	}

	public void setDocumentDetailedList(ArrayList<Emr> documentDetailedList) {
		this.documentDetailedList = documentDetailedList;
	}

	public ArrayList<Bed> getGynicFormList() {
		return gynicFormList;
	}

	public void setGynicFormList(ArrayList<Bed> gynicFormList) {
		this.gynicFormList = gynicFormList;
	}

	public ArrayList<Master> getPriscUnitList() {
		return priscUnitList;
	}

	public void setPriscUnitList(ArrayList<Master> priscUnitList) {
		this.priscUnitList = priscUnitList;
	}
	
	
public String getInvesttemp() {
		return investtemp;
	}

	public void setInvesttemp(String investtemp) {
		this.investtemp = investtemp;
	}

public String getLabuserid() {
		return labuserid;
	}

	public void setLabuserid(String labuserid) {
		this.labuserid = labuserid;
	}
public String getAgegender() {
		return agegender;
	}

	public void setAgegender(String agegender) {
		this.agegender = agegender;
	}

public int getPagelimit() {
		return pagelimit;
	}

	public void setPagelimit(int pagelimit) {
		this.pagelimit = pagelimit;
	}
public ArrayList<Master> getPrisctypelist() {
		return prisctypelist;
	}

	public void setPrisctypelist(ArrayList<Master> prisctypelist) {
		this.prisctypelist = prisctypelist;
	}
public String getPrisctypename() {
		return prisctypename;
	}

	public void setPrisctypename(String prisctypename) {
		this.prisctypename = prisctypename;
	}
public boolean isIsdotmatrix() {
		return isdotmatrix;
	}

	public void setIsdotmatrix(boolean isdotmatrix) {
		this.isdotmatrix = isdotmatrix;
	}
public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
public String getDr_qty() {
		return dr_qty;
	}

	public void setDr_qty(String dr_qty) {
		this.dr_qty = dr_qty;
	}
private String investtemp;
private String labuserid;
private String agegender;
private ArrayList<Master> prisctypelist;
private String prisctypename;
private boolean isdotmatrix;
private int days;
private String dr_qty;
private String isindivisual;
private String indivisualclientid;
private String indivisualclientname;
public String getIsindivisual() {
	return isindivisual;
}

public void setIsindivisual(String isindivisual) {
	this.isindivisual = isindivisual;
}

public String getIndivisualclientid() {
	return indivisualclientid;
}

public void setIndivisualclientid(String indivisualclientid) {
	this.indivisualclientid = indivisualclientid;
}

public String getIndivisualclientname() {
	return indivisualclientname;
}

public void setIndivisualclientname(String indivisualclientname) {
	this.indivisualclientname = indivisualclientname;
}

private String indage;
private String indgender;
private String indtime;
public String getIndage() {
	return indage;
}

public void setIndage(String indage) {
	this.indage = indage;
}

public String getIndgender() {
	return indgender;
}

public void setIndgender(String indgender) {
	this.indgender = indgender;
}

public String getIndtime() {
	return indtime;
}

public void setIndtime(String indtime) {
	this.indtime = indtime;
}




public String getMedlimit() {
	return medlimit;
}

public void setMedlimit(String medlimit) {
	this.medlimit = medlimit;
}
public ArrayList<ThirdParty> getTplist() {
	return tplist;
}

public void setTplist(ArrayList<ThirdParty> tplist) {
	this.tplist = tplist;
}
private String medlimit;
private ArrayList<ThirdParty> tplist;
private double zerogst;
private double fivegst;
private double twelvegst;
private double eighteen;
private double twentyeight;
public double getZerogst() {
	return zerogst;
}

public void setZerogst(double zerogst) {
	this.zerogst = zerogst;
}

public double getFivegst() {
	return fivegst;
}

public void setFivegst(double fivegst) {
	this.fivegst = fivegst;
}

public double getTwelvegst() {
	return twelvegst;
}

public void setTwelvegst(double twelvegst) {
	this.twelvegst = twelvegst;
}

public double getEighteen() {
	return eighteen;
}

public void setEighteen(double eighteen) {
	this.eighteen = eighteen;
}

public double getTwentyeight() {
	return twentyeight;
}

public void setTwentyeight(double twentyeight) {
	this.twentyeight = twentyeight;
}
public double getTotalgst() {
	return totalgst;
}

public void setTotalgst(double totalgst) {
	this.totalgst = totalgst;
}


public double getTotalneftamtref() {
	return totalneftamtref;
}

public void setTotalneftamtref(double totalneftamtref) {
	this.totalneftamtref = totalneftamtref;
}



public double getGrandneftnetco() {
	return grandneftnetco;
}

public void setGrandneftnetco(double grandneftnetco) {
	this.grandneftnetco = grandneftnetco;
}



public double getFinalrettotal() {
	return finalrettotal;
}

public void setFinalrettotal(double finalrettotal) {
	this.finalrettotal = finalrettotal;
}
public String getDefaultremark() {
	return defaultremark;
}

public void setDefaultremark(String defaultremark) {
	this.defaultremark = defaultremark;
}
public String getIsdeleted() {
	return isdeleted;
}

public void setIsdeleted(String isdeleted) {
	this.isdeleted = isdeleted;
}
private double totalgst;
private double totalneftamtref;
private double grandneftnetco;
private double finalrettotal;
private String defaultremark; 
private String isdeleted;

private double zeroamt;
private double fiveamt;
private double twelveamt;
private double eighteenamt;
private double twentyeightamt;
private double totalnetgstamt;
private String maritalsts;
public double getZeroamt() {
	return zeroamt;
}

public void setZeroamt(double zeroamt) {
	this.zeroamt = zeroamt;
}

public double getFiveamt() {
	return fiveamt;
}

public void setFiveamt(double fiveamt) {
	this.fiveamt = fiveamt;
}

public double getTwelveamt() {
	return twelveamt;
}

public void setTwelveamt(double twelveamt) {
	this.twelveamt = twelveamt;
}

public double getEighteenamt() {
	return eighteenamt;
}

public void setEighteenamt(double eighteenamt) {
	this.eighteenamt = eighteenamt;
}

public double getTwentyeightamt() {
	return twentyeightamt;
}

public void setTwentyeightamt(double twentyeightamt) {
	this.twentyeightamt = twentyeightamt;
}

public double getTotalnetgstamt() {
	return totalnetgstamt;
}

public void setTotalnetgstamt(double totalnetgstamt) {
	this.totalnetgstamt = totalnetgstamt;
}

public String getMaritalsts() {
	return maritalsts;
}

public void setMaritalsts(String maritalsts) {
	this.maritalsts = maritalsts;
}
public String getMemo() {
	return memo;
}

public void setMemo(String memo) {
	this.memo = memo;
}
public String getAddr() {
	return addr;
}

public void setAddr(String addr) {
	this.addr = addr;
}
public String getReportby() {
	return reportby;
}

public void setReportby(String reportby) {
	this.reportby = reportby;
}
public Collection<Investigation> getMultiinvstigation() {
	return multiinvstigation;
}

public void setMultiinvstigation(Collection<Investigation> multiinvstigation) {
	this.multiinvstigation = multiinvstigation;
}
public Collection<Investigation> getRemarklist() {
	return remarklist;
}

public void setRemarklist(Collection<Investigation> remarklist) {
	this.remarklist = remarklist;
}
private String addr;
private String memo;
private String reportby;

private Collection<Investigation> multiinvstigation;
private Collection<Investigation> remarklist;
private double zeromrp,fivemrp,twelvemrp,eightteenmrp, twentyeightmrp,totalnmrp;
public double getZeromrp() {
	return zeromrp;
}

public void setZeromrp(double zeromrp) {
	this.zeromrp = zeromrp;
}

public double getFivemrp() {
	return fivemrp;
}

public void setFivemrp(double fivemrp) {
	this.fivemrp = fivemrp;
}

public double getTwelvemrp() {
	return twelvemrp;
}

public void setTwelvemrp(double twelvemrp) {
	this.twelvemrp = twelvemrp;
}

public double getEightteenmrp() {
	return eightteenmrp;
}

public void setEightteenmrp(double eightteenmrp) {
	this.eightteenmrp = eightteenmrp;
}

public double getTwentyeightmrp() {
	return twentyeightmrp;
}

public void setTwentyeightmrp(double twentyeightmrp) {
	this.twentyeightmrp = twentyeightmrp;
}

public double getTotalnmrp() {
	return totalnmrp;
}

public void setTotalnmrp(double totalnmrp) {
	this.totalnmrp = totalnmrp;
}

public String getTemprature() {
	return temprature;
}

public void setTemprature(String temprature) {
	this.temprature = temprature;
}
public String getSpo2() {
	return spo2;
}

public void setSpo2(String spo2) {
	this.spo2 = spo2;
}
public String getTotalstr() {
	return totalstr;
}

public void setTotalstr(String totalstr) {
	this.totalstr = totalstr;
}
public String getBsa() {
	return bsa;
}

public void setBsa(String bsa) {
	this.bsa = bsa;
}
public boolean isDaycare() {
	return daycare;
}

public void setDaycare(boolean daycare) {
	this.daycare = daycare;
}
public ArrayList<Diagnosis> getDiagnosislist() {
	return diagnosislist;
}

public void setDiagnosislist(ArrayList<Diagnosis> diagnosislist) {
	this.diagnosislist = diagnosislist;
}
public int getLastclinicalnotesid() {
	return lastclinicalnotesid;
}

public void setLastclinicalnotesid(int lastclinicalnotesid) {
	this.lastclinicalnotesid = lastclinicalnotesid;
}
public String getClinicaalnotetext() {
	return clinicaalnotetext;
}

public void setClinicaalnotetext(String clinicaalnotetext) {
	this.clinicaalnotetext = clinicaalnotetext;
}
public String getHiddenval() {
	return hiddenval;
}

public void setHiddenval(String hiddenval) {
	this.hiddenval = hiddenval;
}
public boolean isHidecolumn() {
	return hidecolumn;
}

public void setHidecolumn(boolean hidecolumn) {
	this.hidecolumn = hidecolumn;
}
public ArrayList<Bed> getWardlist() {
	return wardlist;
}

public void setWardlist(ArrayList<Bed> wardlist) {
	this.wardlist = wardlist;
}
public boolean isBghsts() {
	return bghsts;
}

public void setBghsts(boolean bghsts) {
	this.bghsts = bghsts;
}
public String getImpresssiontext() {
	return impresssiontext;
}

public void setImpresssiontext(String impresssiontext) {
	this.impresssiontext = impresssiontext;
}
public String getCommentwriteupappr() {
	return commentwriteupappr;
}

public void setCommentwriteupappr(String commentwriteupappr) {
	this.commentwriteupappr = commentwriteupappr;
}
private String temprature;
private String spo2;
private String bsa;
private String totalstr;
private boolean daycare;
private ArrayList<Diagnosis> diagnosislist;
private int lastclinicalnotesid;
private String clinicaalnotetext;
private String impresssiontext;
private String commentwriteupappr;
private String finaldiagnosis;

public String getFinaldiagnosis() {
	return finaldiagnosis;
}

public void setFinaldiagnosis(String finaldiagnosis) {
	this.finaldiagnosis = finaldiagnosis;
}
public int getIsbookapmtfollowup() {
	return isbookapmtfollowup;
}

public void setIsbookapmtfollowup(int isbookapmtfollowup) {
	this.isbookapmtfollowup = isbookapmtfollowup;
}
public String getRequested_uerid() {
	return requested_uerid;
}

public void setRequested_uerid(String requested_uerid) {
	this.requested_uerid = requested_uerid;
}
public String getWardid() {
	return wardid;
}

public void setWardid(String wardid) {
	this.wardid = wardid;
}
public ArrayList<Bed> getBedlist() {
	return bedlist;
}

public void setBedlist(ArrayList<Bed> bedlist) {
	this.bedlist = bedlist;
}
public ArrayList<DietaryDetails> getTemplatelist() {
	return templatelist;
}

public void setTemplatelist(ArrayList<DietaryDetails> templatelist) {
	this.templatelist = templatelist;
}
public ArrayList<DietaryDetails> getDietfeedlist() {
	return dietfeedlist;
}

public void setDietfeedlist(ArrayList<DietaryDetails> dietfeedlist) {
	this.dietfeedlist = dietfeedlist;
}

public ArrayList<Dietary> getDietarycategoryList() {
	return dietarycategoryList;
}

public void setDietarycategoryList(ArrayList<Dietary> dietarycategoryList) {
	this.dietarycategoryList = dietarycategoryList;
}
public ArrayList<DietaryDetails> getDietplanlist() {
	return dietplanlist;
}

public void setDietplanlist(ArrayList<DietaryDetails> dietplanlist) {
	this.dietplanlist = dietplanlist;
}
public ArrayList<Master> getVitalList() {
	return vitalList;
}

public void setVitalList(ArrayList<Master> vitalList) {
	this.vitalList = vitalList;
}
public ArrayList<Master> getNursingcategorylist() {
	return nursingcategorylist;
}

public void setNursingcategorylist(ArrayList<Master> nursingcategorylist) {
	this.nursingcategorylist = nursingcategorylist;
}
public String getLmpd() {
	return lmpd;
}

public void setLmpd(String lmpd) {
	this.lmpd = lmpd;
}
public ArrayList<Emr> getOpdemrloglist() {
	return opdemrloglist;
}

public void setOpdemrloglist(ArrayList<Emr> opdemrloglist) {
	this.opdemrloglist = opdemrloglist;
}

public ArrayList<Emr> getIpdemrloglist() {
	return ipdemrloglist;
}

public void setIpdemrloglist(ArrayList<Emr> ipdemrloglist) {
	this.ipdemrloglist = ipdemrloglist;
}
public String getEmripdid() {
	return emripdid;
}

public void setEmripdid(String emripdid) {
	this.emripdid = emripdid;
}
public ArrayList<Emr> getConsultationNoteListInv() {
	return consultationNoteListInv;
}

public void setConsultationNoteListInv(ArrayList<Emr> consultationNoteListInv) {
	this.consultationNoteListInv = consultationNoteListInv;
}
public ArrayList<Emr> getConsultationNoteListPrisc() {
	return consultationNoteListPrisc;
}

public void setConsultationNoteListPrisc(ArrayList<Emr> consultationNoteListPrisc) {
	this.consultationNoteListPrisc = consultationNoteListPrisc;
}
public ArrayList<Client> getVitallist() {
	return vitallist;
}

public void setVitallist(ArrayList<Client> vitallist) {
	this.vitallist = vitallist;
}
public String getIsfromdeptopd() {
	return isfromdeptopd;
}

public void setIsfromdeptopd(String isfromdeptopd) {
	this.isfromdeptopd = isfromdeptopd;
}
public ArrayList<Emr> getSecsecondarylist() {
	return secsecondarylist;
}

public void setSecsecondarylist(ArrayList<Emr> secsecondarylist) {
	this.secsecondarylist = secsecondarylist;
}
public ArrayList<Master> getOdmrprocedurelist() {
	return odmrprocedurelist;
}

public void setOdmrprocedurelist(ArrayList<Master> odmrprocedurelist) {
	this.odmrprocedurelist = odmrprocedurelist;
}
public String getEnrollcode() {
	return enrollcode;
}

public void setEnrollcode(String enrollcode) {
	this.enrollcode = enrollcode;
}
public int getStatusenrollcode() {
	return statusenrollcode;
}

public void setStatusenrollcode(int statusenrollcode) {
	this.statusenrollcode = statusenrollcode;
}
private int isbookapmtfollowup;
private String requested_uerid;
private String wardid;
private ArrayList<Bed> bedlist;
private ArrayList<DietaryDetails> templatelist;
private ArrayList<DietaryDetails> dietfeedlist;
private ArrayList<DietaryDetails> dietplanlist;
private ArrayList<Dietary>  dietarycategoryList;
private ArrayList<Master> vitalList;
private ArrayList<Master> nursingcategorylist;
private String lmpd;
private ArrayList<Emr> opdemrloglist;
private ArrayList<Emr> ipdemrloglist;
private ArrayList<Client> vitallist;
private String tptype;

private ArrayList<Master>deptlist;
private ArrayList<Master>procedurelist;
private ArrayList<SittingFollowup>sittinglist;


public ArrayList<Master> getDeptlist() {
	return deptlist;
}

public void setDeptlist(ArrayList<Master> deptlist) {
	this.deptlist = deptlist;
}

public ArrayList<Master> getProcedurelist() {
	return procedurelist;
}

public void setProcedurelist(ArrayList<Master> procedurelist) {
	this.procedurelist = procedurelist;
}

public ArrayList<SittingFollowup> getSittinglist() {
	return sittinglist;
}

public void setSittinglist(ArrayList<SittingFollowup> sittinglist) {
	this.sittinglist = sittinglist;
}

public String getTptype() {
	return tptype;
}

public void setTptype(String tptype) {
	this.tptype = tptype;
}
private String regino;
public String getRegino() {
	return regino;
}

public void setRegino(String regino) {
	this.regino = regino;
}
ArrayList<Pacs>pacsdataList;
public ArrayList<Pacs> getPacsdataList() {
	return pacsdataList;
}

public void setPacsdataList(ArrayList<Pacs> pacsdataList) {
	this.pacsdataList = pacsdataList;
}
private String mobile;
public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

private ArrayList<Investigation> thirdPartyTypeNameList;
public ArrayList<Investigation> getThirdPartyTypeNameList() {
	return thirdPartyTypeNameList;
}

public void setThirdPartyTypeNameList(ArrayList<Investigation> thirdPartyTypeNameList) {
	this.thirdPartyTypeNameList = thirdPartyTypeNameList;
}

private ArrayList<Investigation> wardnamelist;
public ArrayList<Investigation> getWardnamelist() {
	return wardnamelist;
}

public void setWardnamelist(ArrayList<Investigation> wardnamelist) {
	this.wardnamelist = wardnamelist;
}
private String investnameid;
public String getInvestnameid() {
	return investnameid;
}

public void setInvestnameid(String investnameid) {
	this.investnameid = investnameid;
}
}
