package com.apm.Report.eu.entity;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;

public class SummaryReport implements Comparable<SummaryReport>{
	public double getNewcount() {
		return newcount;
	}

	public void setNewcount(double newcount) {
		this.newcount = newcount;
	}

	public double getOldcount() {
		return oldcount;
	}

	public void setOldcount(double oldcount) {
		this.oldcount = oldcount;
	}
	private String abrivationid;
	private String duration;
	private int id;
	private String emergencycontno;
	private int appointmentId;
	private int cancel;
	private String admissioncount;
	private String dischargecount;
	private String asistantdoctor;
	private String debitamttotal;
	private String balanceamttotal;
	private String ipdabrivationid;
	private String mothername;
	private String Invoiceids;
	private String masterchargetype;
	private String referedshareAmt;
	private double shareAmt;
	
	public double getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(double shareAmt) {
		this.shareAmt = shareAmt;
	}
	ArrayList<SummaryReport>monthtotallist;
	
	public String getReferedshareAmt() {
		return referedshareAmt;
	}

	public void setReferedshareAmt(String referedshareAmt) {
		this.referedshareAmt = referedshareAmt;
	}
	
	public ArrayList<SummaryReport> getMonthtotallist() {
		return monthtotallist;
	}

	public void setMonthtotallist(ArrayList<SummaryReport> monthtotallist) {
		this.monthtotallist = monthtotallist;
	}

	public String getMasterchargetype() {
		return masterchargetype;
	}

	public void setMasterchargetype(String masterchargetype) {
		this.masterchargetype = masterchargetype;
	}

	public String getInvoiceids() {
		return Invoiceids;
	}

	public void setInvoiceids(String invoiceids) {
		Invoiceids = invoiceids;
	}

	public String getMothername() {
		return mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
	}

	public String getIpdabrivationid() {
		return ipdabrivationid;
	}

	public void setIpdabrivationid(String ipdabrivationid) {
		this.ipdabrivationid = ipdabrivationid;
	}

	public String getDebitamttotal() {
		return debitamttotal;
	}

	public void setDebitamttotal(String debitamttotal) {
		this.debitamttotal = debitamttotal;
	}

	public String getBalanceamttotal() {
		return balanceamttotal;
	}

	public void setBalanceamttotal(String balanceamttotal) {
		this.balanceamttotal = balanceamttotal;
	}

	public String getAdmissioncount() {
		return admissioncount;
	}

	public void setAdmissioncount(String admissioncount) {
		this.admissioncount = admissioncount;
	}

	public String getDischargecount() {
		return dischargecount;
	}

	public void setDischargecount(String dischargecount) {
		this.dischargecount = dischargecount;
	}

	public int getAppointmentId() {
		return appointmentId;
	}
private String ismlc;

	public String getAbrivationid() {
		return abrivationid;
	}
private boolean isdead;
	private String  opdamount,ipdamount,invstamount;
	private String  totalopdamount,totalipdamount,totalinvstamount;
	private String totalitypeamount;
	private String mlcno;
	private String wtonbirth;
	
	
	public String getWtonbirth() {
		return wtonbirth;
	}

	public void setWtonbirth(String wtonbirth) {
		this.wtonbirth = wtonbirth;
	}

	public String getTotalitypeamount() {
		return totalitypeamount;
	}

	public void setTotalitypeamount(String totalitypeamount) {
		this.totalitypeamount = totalitypeamount;
	}

	public String getOpdamount() {
		return opdamount;
	}

	public void setOpdamount(String opdamount) {
		this.opdamount = opdamount;
	}

	public String getIpdamount() {
		return ipdamount;
	}

	public void setIpdamount(String ipdamount) {
		this.ipdamount = ipdamount;
	}

	public String getInvstamount() {
		return invstamount;
	}

	public void setInvstamount(String invstamount) {
		this.invstamount = invstamount;
	}

	public String getTotalopdamount() {
		return totalopdamount;
	}

	public void setTotalopdamount(String totalopdamount) {
		this.totalopdamount = totalopdamount;
	}

	public String getTotalipdamount() {
		return totalipdamount;
	}

	public void setTotalipdamount(String totalipdamount) {
		this.totalipdamount = totalipdamount;
	}

	public String getTotalinvstamount() {
		return totalinvstamount;
	}

	public void setTotalinvstamount(String totalinvstamount) {
		this.totalinvstamount = totalinvstamount;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	private String commencing;
	
	private String location;
	
	private String clientname;
	
	private String aptmtypeId;
	private String ipdid;
	
	private String clientId;
	
	private String dna;
	private int age;
	private String agegender;
	
	public String getAgegender() {
		return agegender;
	}

	public void setAgegender(String agegender) {
		this.agegender = agegender;
	}
	private String dnareason;
	
	private String condition1;
	private String condition2;
	private String condition3;
	private String type;
	
	private String charge;
	
	private String apmttypetext;
	
	private String mobno;
	
	private String email;
	
	private String address;
	
	private String town;
	
	private String country;
	
	private String fromDate;
	
	private String toDate;
	
	private String practitionerId;
	
	private String practitionerName;
	
	private int totalApp;
	
	private int totalDNA;
	
	private int totalComplete;
	
	private int totalIncomplete;
	
	private String referal;
	
	private String treatmentId;
	
	private String treatmentName;
	
	private String ref_date;
	
	private String ref_source;
	
	private String sec_consultant;
	
	private String ref_name;
	
	private String ref_contact;
	
	private String ref_letter;
	
	private String apptName;
	
	private String tpName;
	
	private String payby;
	
	private String clinicname;
	
	private String referedby;
	
	private String totalptsseen;
	
	private String gpName;
	
	private String condition;
	
	private String count;
	
	private String sourceofintro;
	
	private String tmentstartdate;
	
	private String discharge;
	
	private String outcomes;
	
	private String dschargeStatus;
	
	private String totalPts;
	
	private String admissiondate;
	
	private String dischargedate;
	
	private ArrayList<SummaryReport> sec_consultantlist;
	private ArrayList<SummaryReport> mlclist;
	private String gender;
	private String clientage;
	private String wardbed;
	private String refferedby;
	private String selfcharge;
	private String tpcharge;
	private String advance;
	private String cashreceived;
	private String selefbalance;
	private String selfcredit;
	private String time;
	private String wardname; 
	private String wardid;
	private String diagnosis;
	private long admitdays;
	private String date;
	private String totalpatient="0";
	private String totalbed="0";
	private int total;
	private String jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec;
	
	private String totaldiscount;
	
	
	public String getTotaldiscount() {
		return totaldiscount;
	}

	public void setTotaldiscount(String totaldiscount) {
		this.totaldiscount = totaldiscount;
	}

	public String getJan() {
		return jan;
	}

	public void setJan(String jan) {
		this.jan = jan;
	}

	public String getFeb() {
		return feb;
	}

	public void setFeb(String feb) {
		this.feb = feb;
	}

	public String getMar() {
		return mar;
	}

	public void setMar(String mar) {
		this.mar = mar;
	}

	public String getApr() {
		return apr;
	}

	public void setApr(String apr) {
		this.apr = apr;
	}

	public String getMay() {
		return may;
	}

	public void setMay(String may) {
		this.may = may;
	}

	public String getJun() {
		return jun;
	}

	public void setJun(String jun) {
		this.jun = jun;
	}

	public String getJul() {
		return jul;
	}

	public void setJul(String jul) {
		this.jul = jul;
	}

	public String getAug() {
		return aug;
	}

	public void setAug(String aug) {
		this.aug = aug;
	}

	public String getSep() {
		return sep;
	}

	public void setSep(String sep) {
		this.sep = sep;
	}

	public String getOct() {
		return oct;
	}

	public void setOct(String oct) {
		this.oct = oct;
	}

	public String getNov() {
		return nov;
	}

	public void setNov(String nov) {
		this.nov = nov;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getTotalpatient() {
		return totalpatient;
	}

	public void setTotalpatient(String totalpatient) {
		this.totalpatient = totalpatient;
	}

	public String getTotalbed() {
		return totalbed;
	}

	public void setTotalbed(String totalbed) {
		this.totalbed = totalbed;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private String department;
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getAdmitdays() {
		return admitdays;
	}

	public void setAdmitdays(long admitdays) {
		this.admitdays = admitdays;
	}

	public String getWardname() {
		return wardname;
	}

	public void setWardname(String wardname) {
		this.wardname = wardname;
	}

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSelfcredit() {
		return selfcredit;
	}

	public void setSelfcredit(String selfcredit) {
		this.selfcredit = selfcredit;
	}

	public String getSelefbalance() {
		return selefbalance;
	}

	public void setSelefbalance(String selefbalance) {
		this.selefbalance = selefbalance;
	}

	public String getCashreceived() {
		return cashreceived;
	}

	public void setCashreceived(String cashreceived) {
		this.cashreceived = cashreceived;
	}

	public String getAdvance() {
		return advance;
	}

	public void setAdvance(String advance) {
		this.advance = advance;
	}

	public String getSelfcharge() {
		return selfcharge;
	}

	public void setSelfcharge(String selfcharge) {
		this.selfcharge = selfcharge;
	}

	public String getTpcharge() {
		return tpcharge;
	}

	public void setTpcharge(String tpcharge) {
		this.tpcharge = tpcharge;
	}

	public String getWardbed() {
		return wardbed;
	}

	public void setWardbed(String wardbed) {
		this.wardbed = wardbed;
	}

	public String getRefferedby() {
		return refferedby;
	}

	public void setRefferedby(String refferedby) {
		this.refferedby = refferedby;
	}

	public String getClientage() {
		return clientage;
	}

	public void setClientage(String clientage) {
		this.clientage = clientage;
	}

	public String getAdmissiondate() {
		return admissiondate;
	}

	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}

	public String getDischargedate() {
		return dischargedate;
	}

	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
	}

	public String getTotalPts() {
		return totalPts;
	}

	public void setTotalPts(String totalPts) {
		this.totalPts = totalPts;
	}

	public String getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(String outcomes) {
		this.outcomes = outcomes;
	}

	public String getDschargeStatus() {
		return dschargeStatus;
	}

	public void setDschargeStatus(String dschargeStatus) {
		this.dschargeStatus = dschargeStatus;
	}

	public String getDischarge() {
		return discharge;
	}

	public void setDischarge(String discharge) {
		this.discharge = discharge;
	}

	public String getTmentstartdate() {
		return tmentstartdate;
	}

	public void setTmentstartdate(String tmentstartdate) {
		this.tmentstartdate = tmentstartdate;
	}

	public String getSourceofintro() {
		return sourceofintro;
	}

	public void setSourceofintro(String sourceofintro) {
		this.sourceofintro = sourceofintro;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getGpName() {
		return gpName;
	}

	public void setGpName(String gpName) {
		this.gpName = gpName;
	}

	public String getTotalptsseen() {
		return totalptsseen;
	}

	public void setTotalptsseen(String totalptsseen) {
		this.totalptsseen = totalptsseen;
	}

	public String getReferedby() {
		return referedby;
	}

	public void setReferedby(String referedby) {
		this.referedby = referedby;
	}

	public String getClinicname() {
		return clinicname;
	}

	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public String getApptName() {
		return apptName;
	}

	public void setApptName(String apptName) {
		this.apptName = apptName;
	}

	public String getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public String getRef_date() {
		return ref_date;
	}

	public void setRef_date(String ref_date) {
		this.ref_date = ref_date;
	}

	public String getRef_source() {
		return ref_source;
	}

	public void setRef_source(String ref_source) {
		this.ref_source = ref_source;
	}

	public String getRef_name() {
		return ref_name;
	}

	public void setRef_name(String ref_name) {
		this.ref_name = ref_name;
	}

	public String getRef_contact() {
		return ref_contact;
	}

	public void setRef_contact(String ref_contact) {
		this.ref_contact = ref_contact;
	}

	public String getRef_letter() {
		return ref_letter;
	}

	public void setRef_letter(String ref_letter) {
		this.ref_letter = ref_letter;
	}

	public String getReferal() {
		return referal;
	}

	public void setReferal(String referal) {
		this.referal = referal;
	}

	public int getTotalApp() {
		return totalApp;
	}

	public void setTotalApp(int totalApp) {
		this.totalApp = totalApp;
	}

	public int getTotalDNA() {
		return totalDNA;
	}

	public void setTotalDNA(int totalDNA) {
		this.totalDNA = totalDNA;
	}

	public int getTotalComplete() {
		return totalComplete;
	}

	public void setTotalComplete(int totalComplete) {
		this.totalComplete = totalComplete;
	}

	public int getTotalIncomplete() {
		return totalIncomplete;
	}

	public void setTotalIncomplete(int totalIncomplete) {
		this.totalIncomplete = totalIncomplete;
	}

	public String getPractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(String practitionerId) {
		this.practitionerId = practitionerId;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
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

	
	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getAptmtypeId() {
		return aptmtypeId;
	}

	public void setAptmtypeId(String aptmtypeId) {
		this.aptmtypeId = aptmtypeId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public String getDnareason() {
		return dnareason;
	}

	public void setDnareason(String dnareason) {
		this.dnareason = dnareason;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getApmttypetext() {
		return apmttypetext;
	}

	public void setApmttypetext(String apmttypetext) {
		this.apmttypetext = apmttypetext;
	}

	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}

	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}

	public String getCondition3() {
		return condition3;
	}

	public void setCondition3(String condition3) {
		this.condition3 = condition3;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getDeath48count() {
		return death48count;
	}

	public void setDeath48count(int death48count) {
		this.death48count = death48count;
	}
	private double totalpayment;
	 private double totaladvance;
	 private double totalrefund;
	 private double nettotal;
	 private double totalneftpayment;
	 private String username;
	 private String userid;
	 private double  totalotherpayment;
	 private double  totalupipayment;
	 private int death48count;

	public double getTotalpayment() {
		return totalpayment;
	}

	public void setTotalpayment(double totalpayment) {
		this.totalpayment = totalpayment;
	}

	public double getTotaladvance() {
		return totaladvance;
	}

	public void setTotaladvance(double totaladvance) {
		this.totaladvance = totaladvance;
	}

	public double getTotalrefund() {
		return totalrefund;
	}

	public void setTotalrefund(double totalrefund) {
		this.totalrefund = totalrefund;
	}

	public double getNettotal() {
		return nettotal;
	}

	public void setNettotal(double nettotal) {
		this.nettotal = nettotal;
	}

	public double getTotalneftpayment() {
		return totalneftpayment;
	}

	public void setTotalneftpayment(double totalneftpayment) {
		this.totalneftpayment = totalneftpayment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public double getTotalotherpayment() {
		return totalotherpayment;
	}

	public void setTotalotherpayment(double totalotherpayment) {
		this.totalotherpayment = totalotherpayment;
	}
	
	 
	 public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public String getPaymentbycash() {
		return paymentbycash;
	}

	public void setPaymentbycash(String paymentbycash) {
		this.paymentbycash = paymentbycash;
	}

	public String getPaymentbyCheque() {
		return paymentbyCheque;
	}

	public void setPaymentbyCheque(String paymentbyCheque) {
		this.paymentbyCheque = paymentbyCheque;
	}

	public String getPaymentbyCCard() {
		return paymentbyCCard;
	}

	public void setPaymentbyCCard(String paymentbyCCard) {
		this.paymentbyCCard = paymentbyCCard;
	}

	public String getPaymentbyDCard() {
		return paymentbyDCard;
	}

	public void setPaymentbyDCard(String paymentbyDCard) {
		this.paymentbyDCard = paymentbyDCard;
	}

	public String getPaymentbyNEFT() {
		return paymentbyNEFT;
	}

	public void setPaymentbyNEFT(String paymentbyNEFT) {
		this.paymentbyNEFT = paymentbyNEFT;
	}

	public String getPaymentbyOther() {
		return paymentbyOther;
	}

	public void setPaymentbyOther(String paymentbyOther) {
		this.paymentbyOther = paymentbyOther;
	}

	public String getAdvancebycash() {
		return advancebycash;
	}

	public void setAdvancebycash(String advancebycash) {
		this.advancebycash = advancebycash;
	}

	public String getAdvancebyCheque() {
		return advancebyCheque;
	}

	public void setAdvancebyCheque(String advancebyCheque) {
		this.advancebyCheque = advancebyCheque;
	}

	public String getAdvancebyCCard() {
		return advancebyCCard;
	}

	public void setAdvancebyCCard(String advancebyCCard) {
		this.advancebyCCard = advancebyCCard;
	}

	public String getAdvancebyDCard() {
		return advancebyDCard;
	}

	public void setAdvancebyDCard(String advancebyDCard) {
		this.advancebyDCard = advancebyDCard;
	}

	public String getAdvancebyNEFT() {
		return advancebyNEFT;
	}

	public void setAdvancebyNEFT(String advancebyNEFT) {
		this.advancebyNEFT = advancebyNEFT;
	}

	public String getAdvancebyOther() {
		return advancebyOther;
	}

	public void setAdvancebyOther(String advancebyOther) {
		this.advancebyOther = advancebyOther;
	}

	public String getRefundbycash() {
		return refundbycash;
	}

	public void setRefundbycash(String refundbycash) {
		this.refundbycash = refundbycash;
	}

	public String getRefundbyCheque() {
		return refundbyCheque;
	}

	public void setRefundbyCheque(String refundbyCheque) {
		this.refundbyCheque = refundbyCheque;
	}

	public String getRefundbyCCard() {
		return refundbyCCard;
	}

	public void setRefundbyCCard(String refundbyCCard) {
		this.refundbyCCard = refundbyCCard;
	}

	public String getRefundbyDCard() {
		return refundbyDCard;
	}

	public void setRefundbyDCard(String refundbyDCard) {
		this.refundbyDCard = refundbyDCard;
	}

	public String getRefundbyNEFT() {
		return refundbyNEFT;
	}

	public void setRefundbyNEFT(String refundbyNEFT) {
		this.refundbyNEFT = refundbyNEFT;
	}

	public String getRefundbyOther() {
		return refundbyOther;
	}

	public void setRefundbyOther(String refundbyOther) {
		this.refundbyOther = refundbyOther;
	}

	public double getTotalcashpayment() {
		return totalcashpayment;
	}

	public void setTotalcashpayment(double totalcashpayment) {
		this.totalcashpayment = totalcashpayment;
	}

	public double getTotalchequepayment() {
		return totalchequepayment;
	}

	public void setTotalchequepayment(double totalchequepayment) {
		this.totalchequepayment = totalchequepayment;
	}

	public double getTotalccardpayment() {
		return totalccardpayment;
	}

	public void setTotalccardpayment(double totalccardpayment) {
		this.totalccardpayment = totalccardpayment;
	}

	public double getTotaldcardpayment() {
		return totaldcardpayment;
	}

	public void setTotaldcardpayment(double totaldcardpayment) {
		this.totaldcardpayment = totaldcardpayment;
	}

	public String getDiscipline_name() {
		return discipline_name;
	}

	public void setDiscipline_name(String discipline_name) {
		this.discipline_name = discipline_name;
	}

	public String getDiscipline_id() {
		return discipline_id;
	}

	public void setDiscipline_id(String discipline_id) {
		this.discipline_id = discipline_id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAge1() {
		return age1;
	}

	public void setAge1(String age1) {
		this.age1 = age1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<SummaryReport> getLocationlist() {
		return locationlist;
	}

	public void setLocationlist(ArrayList<SummaryReport> locationlist) {
		this.locationlist = locationlist;
	}

	private String payment;
	 
	 private String refund;
	 private String paymentbycash;
	 private String paymentbyCheque;
	 private String paymentbyCCard;
	 private String paymentbyDCard;
	 private String paymentbyNEFT;
	 private String paymentbyOther;
	 private String paymentbyUPI;
	 
	 private String advancebycash;
	 private String advancebyCheque; 
	 private String advancebyCCard;
	 private String advancebyDCard;
	 private String advancebyNEFT;
	 private String advancebyOther;
	 private String advancebyUPI;
	 
	 private String refundbycash;
	 private String refundbyCheque;
	 private String refundbyCCard;
	 private String refundbyDCard;
	 private String refundbyNEFT;
	 private String refundbyOther;
	 private String refundbyUPI;

	 public String getPaymentbyUPI() {
		return paymentbyUPI;
	}

	public void setPaymentbyUPI(String paymentbyUPI) {
		this.paymentbyUPI = paymentbyUPI;
	}

	public String getAdvancebyUPI() {
		return advancebyUPI;
	}

	public void setAdvancebyUPI(String advancebyUPI) {
		this.advancebyUPI = advancebyUPI;
	}

	public String getRefundbyUPI() {
		return refundbyUPI;
	}

	public void setRefundbyUPI(String refundbyUPI) {
		this.refundbyUPI = refundbyUPI;
	}
	private double totalcashpayment;
	 private double totalchequepayment;
	 private double totalccardpayment;
	 private double totaldcardpayment;
	 private String discipline_id;
	 private String discipline_name;
	 private String patientName;
	 
	 private String dob;
	 private String state;
	 private String city;
	private String age1;
	 private ArrayList<SummaryReport> locationlist;
	private double avgopd,avgipd,avgot,newcount,oldcount;

	public double getAvgopd() {
		return avgopd;
	}

	public void setAvgopd(double avgopd) {
		this.avgopd = avgopd;
	}

	public double getAvgipd() {
		return avgipd;
	}

	public void setAvgipd(double avgipd) {
		this.avgipd = avgipd;
	}

	public double getAvgot() {
		return avgot;
	}

	public void setAvgot(double avgot) {
		this.avgot = avgot;
	}
public String getProcedurename() {
		return procedurename;
	}

	public void setProcedurename(String procedurename) {
		this.procedurename = procedurename;
	}

public ArrayList<SummaryReport > getOtlist() {
		return otlist;
	}

	public void setOtlist(ArrayList<SummaryReport > otlist) {
		this.otlist = otlist;
	}

public String getSec_consultant() {
		return sec_consultant;
	}

	public void setSec_consultant(String sec_consultant) {
		this.sec_consultant = sec_consultant;
	}

public ArrayList<SummaryReport> getSec_consultantlist() {
		return sec_consultantlist;
	}

	public void setSec_consultantlist(ArrayList<SummaryReport> sec_consultantlist) {
		this.sec_consultantlist = sec_consultantlist;
	}

public ArrayList<SummaryReport> getMlclist() {
		return mlclist;
	}

	public void setMlclist(ArrayList<SummaryReport> mlclist) {
		this.mlclist = mlclist;
	}

public String getIsmlc() {
		return ismlc;
	}

	public int getJan1() {
	return jan1;
}

public void setJan1(int jan1) {
	this.jan1 = jan1;
}

public int getFeb1() {
	return feb1;
}

public void setFeb1(int feb1) {
	this.feb1 = feb1;
}

public int getMarch1() {
	return march1;
}

public void setMarch1(int march1) {
	this.march1 = march1;
}

public int getApril1() {
	return april1;
}

public void setApril1(int april1) {
	this.april1 = april1;
}

public int getMay1() {
	return may1;
}

public void setMay1(int may1) {
	this.may1 = may1;
}

public int getJune1() {
	return june1;
}

public void setJune1(int june1) {
	this.june1 = june1;
}

public int getJully1() {
	return jully1;
}

public void setJully1(int jully1) {
	this.jully1 = jully1;
}

public int getAug1() {
	return aug1;
}

public void setAug1(int aug1) {
	this.aug1 = aug1;
}

public int getSept1() {
	return sept1;
}

public void setSept1(int sept1) {
	this.sept1 = sept1;
}

public int getOct1() {
	return oct1;
}

public void setOct1(int oct1) {
	this.oct1 = oct1;
}

public int getNov1() {
	return nov1;
}

public void setNov1(int nov1) {
	this.nov1 = nov1;
}

public int getDec1() {
	return dec1;
}

public void setDec1(int dec1) {
	this.dec1 = dec1;
}

	public void setIsmlc(String ismlc) {
		this.ismlc = ismlc;
	}
public boolean isIsdead() {
		return isdead;
	}

	public void setIsdead(boolean isdead) {
		this.isdead = isdead;
	}
public String getEmergencycontno() {
		return emergencycontno;
	}

	public void setEmergencycontno(String emergencycontno) {
		this.emergencycontno = emergencycontno;
	}
public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
public int getPatientcount() {
		return patientcount;
	}

	public void setPatientcount(int patientcount) {
		this.patientcount = patientcount;
	}
	
	
public String getIpdseqno() {
		return ipdseqno;
	}

	public void setIpdseqno(String ipdseqno) {
		this.ipdseqno = ipdseqno;
	}
public String getBedoccupancy() {
		return bedoccupancy;
	}

	public void setBedoccupancy(String bedoccupancy) {
		this.bedoccupancy = bedoccupancy;
	}
public String getWhopay() {
		return whopay;
	}

	public void setWhopay(String whopay) {
		this.whopay = whopay;
	}
public String getBedname() {
		return bedname;
	}

	public void setBedname(String bedname) {
		this.bedname = bedname;
	}
public double getTotalcharge() {
		return totalcharge;
	}

	public void setTotalcharge(double totalcharge) {
		this.totalcharge = totalcharge;
	}
	
public String getNewipdabbr() {
		return newipdabbr;
	}

	public void setNewipdabbr(String newipdabbr) {
		this.newipdabbr = newipdabbr;
	}
public ArrayList<SummaryReport> getReportlist() {
		return reportlist;
	}

	public void setReportlist(ArrayList<SummaryReport> reportlist) {
		this.reportlist = reportlist;
	}
public int getTpid() {
		return tpid;
	}

	public void setTpid(int tpid) {
		this.tpid = tpid;
	}
public String getIpdpkg() {
		return ipdpkg;
	}

	public void setIpdpkg(String ipdpkg) {
		this.ipdpkg = ipdpkg;
	}
public int getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}
public double getDiscamt() {
		return discamt;
	}

	public void setDiscamt(double discamt) {
		this.discamt = discamt;
	}
public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
public String getMlcno() {
		return mlcno;
	}

	public void setMlcno(String mlcno) {
		this.mlcno = mlcno;
	}
private String  procedurename;
private ArrayList<SummaryReport > otlist;
private int jan1=0,feb1=0,march1=0,april1=0,may1=0,june1=0,jully1=0,aug1=0,sept1=0,oct1=0,nov1=0,dec1=0;
private int percent;
private int patientcount;
private String ipdseqno;
private String bedoccupancy;
private String whopay;
private String bedname;
private double totalcharge;
private String newipdabbr;
private ArrayList<SummaryReport> reportlist;
private int tpid;
private String ipdpkg;
private int invoiceid;
private double discamt;
private String diaryusername;
private String category;
private String procedures;
private String surgeon;
private String anesthesia;
private Integer invoiceid1;


public Integer getInvoiceid1() {
	return invoiceid1;
}

public void setInvoiceid1(Integer invoiceid1) {
	this.invoiceid1 = invoiceid1;
}

public String getDiaryusername() {
	return diaryusername;
}

public void setDiaryusername(String diaryusername) {
	this.diaryusername = diaryusername;
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

public String getDuration() {
	return duration;
}

public void setDuration(String duration) {
	this.duration = duration;
}
private String jancash,febcash,marcash,aprcash,maycash,juncash,julcash,augcash,sepcash,octcash,novcash,deccash;
private String jandcard,febdcard,mardcard,aprdcard,maydcard,jundcard,juldcard,augdcard,sepdcard,octdcard,novdcard,decdcard;
private String janneft,febneft,marneft,aprneft,mayneft,junneft,julneft,augneft,sepneft,octneft,novneft,decneft;
private String jancheque,febcheque,marcheque,aprcheque,maycheque,juncheque,julcheque,augcheque,sepcheque,octcheque,novcheque,deccheque;
private String janprepay,febprepay,marprepay,aprprepay,mayprepay,junprepay,julprepay,augprepay,sepprepay,octprepay,novprepay,decprepay;
public String getJancash() {
	return jancash;
}

public void setJancash(String jancash) {
	this.jancash = jancash;
}

public String getFebcash() {
	return febcash;
}

public void setFebcash(String febcash) {
	this.febcash = febcash;
}

public String getMarcash() {
	return marcash;
}

public void setMarcash(String marcash) {
	this.marcash = marcash;
}

public String getAprcash() {
	return aprcash;
}

public void setAprcash(String aprcash) {
	this.aprcash = aprcash;
}

public String getMaycash() {
	return maycash;
}

public void setMaycash(String maycash) {
	this.maycash = maycash;
}

public String getJuncash() {
	return juncash;
}

public void setJuncash(String juncash) {
	this.juncash = juncash;
}

public String getJulcash() {
	return julcash;
}

public void setJulcash(String julcash) {
	this.julcash = julcash;
}

public String getAugcash() {
	return augcash;
}

public void setAugcash(String augcash) {
	this.augcash = augcash;
}

public String getSepcash() {
	return sepcash;
}

public void setSepcash(String sepcash) {
	this.sepcash = sepcash;
}

public String getOctcash() {
	return octcash;
}

public void setOctcash(String octcash) {
	this.octcash = octcash;
}

public String getNovcash() {
	return novcash;
}

public void setNovcash(String novcash) {
	this.novcash = novcash;
}

public String getDeccash() {
	return deccash;
}

public void setDeccash(String deccash) {
	this.deccash = deccash;
}

public String getJandcard() {
	return jandcard;
}

public void setJandcard(String jandcard) {
	this.jandcard = jandcard;
}

public String getFebdcard() {
	return febdcard;
}

public void setFebdcard(String febdcard) {
	this.febdcard = febdcard;
}

public String getMardcard() {
	return mardcard;
}

public void setMardcard(String mardcard) {
	this.mardcard = mardcard;
}

public String getAprdcard() {
	return aprdcard;
}

public void setAprdcard(String aprdcard) {
	this.aprdcard = aprdcard;
}

public String getMaydcard() {
	return maydcard;
}

public void setMaydcard(String maydcard) {
	this.maydcard = maydcard;
}

public String getJundcard() {
	return jundcard;
}

public void setJundcard(String jundcard) {
	this.jundcard = jundcard;
}

public String getJuldcard() {
	return juldcard;
}

public void setJuldcard(String juldcard) {
	this.juldcard = juldcard;
}

public String getAugdcard() {
	return augdcard;
}

public void setAugdcard(String augdcard) {
	this.augdcard = augdcard;
}

public String getSepdcard() {
	return sepdcard;
}

public void setSepdcard(String sepdcard) {
	this.sepdcard = sepdcard;
}

public String getOctdcard() {
	return octdcard;
}

public void setOctdcard(String octdcard) {
	this.octdcard = octdcard;
}

public String getNovdcard() {
	return novdcard;
}

public void setNovdcard(String novdcard) {
	this.novdcard = novdcard;
}

public String getDecdcard() {
	return decdcard;
}

public void setDecdcard(String decdcard) {
	this.decdcard = decdcard;
}

public String getJanneft() {
	return janneft;
}

public void setJanneft(String janneft) {
	this.janneft = janneft;
}

public String getFebneft() {
	return febneft;
}

public void setFebneft(String febneft) {
	this.febneft = febneft;
}

public String getMarneft() {
	return marneft;
}

public void setMarneft(String marneft) {
	this.marneft = marneft;
}

public String getAprneft() {
	return aprneft;
}

public void setAprneft(String aprneft) {
	this.aprneft = aprneft;
}

public String getMayneft() {
	return mayneft;
}

public void setMayneft(String mayneft) {
	this.mayneft = mayneft;
}

public String getJunneft() {
	return junneft;
}

public void setJunneft(String junneft) {
	this.junneft = junneft;
}

public String getJulneft() {
	return julneft;
}

public void setJulneft(String julneft) {
	this.julneft = julneft;
}

public String getAugneft() {
	return augneft;
}

public void setAugneft(String augneft) {
	this.augneft = augneft;
}

public String getSepneft() {
	return sepneft;
}

public void setSepneft(String sepneft) {
	this.sepneft = sepneft;
}

public String getOctneft() {
	return octneft;
}

public void setOctneft(String octneft) {
	this.octneft = octneft;
}

public String getNovneft() {
	return novneft;
}

public void setNovneft(String novneft) {
	this.novneft = novneft;
}

public String getDecneft() {
	return decneft;
}

public void setDecneft(String decneft) {
	this.decneft = decneft;
}

public String getJancheque() {
	return jancheque;
}

public void setJancheque(String jancheque) {
	this.jancheque = jancheque;
}

public String getFebcheque() {
	return febcheque;
}

public void setFebcheque(String febcheque) {
	this.febcheque = febcheque;
}

public String getMarcheque() {
	return marcheque;
}

public void setMarcheque(String marcheque) {
	this.marcheque = marcheque;
}

public String getAprcheque() {
	return aprcheque;
}

public void setAprcheque(String aprcheque) {
	this.aprcheque = aprcheque;
}

public String getMaycheque() {
	return maycheque;
}

public void setMaycheque(String maycheque) {
	this.maycheque = maycheque;
}

public String getJuncheque() {
	return juncheque;
}

public void setJuncheque(String juncheque) {
	this.juncheque = juncheque;
}

public String getJulcheque() {
	return julcheque;
}

public void setJulcheque(String julcheque) {
	this.julcheque = julcheque;
}

public String getAugcheque() {
	return augcheque;
}

public void setAugcheque(String augcheque) {
	this.augcheque = augcheque;
}

public String getSepcheque() {
	return sepcheque;
}

public void setSepcheque(String sepcheque) {
	this.sepcheque = sepcheque;
}

public String getOctcheque() {
	return octcheque;
}

public void setOctcheque(String octcheque) {
	this.octcheque = octcheque;
}

public String getNovcheque() {
	return novcheque;
}

public void setNovcheque(String novcheque) {
	this.novcheque = novcheque;
}

public String getDeccheque() {
	return deccheque;
}

public void setDeccheque(String deccheque) {
	this.deccheque = deccheque;
}

public String getJanprepay() {
	return janprepay;
}

public void setJanprepay(String janprepay) {
	this.janprepay = janprepay;
}

public String getFebprepay() {
	return febprepay;
}

public void setFebprepay(String febprepay) {
	this.febprepay = febprepay;
}

public String getMarprepay() {
	return marprepay;
}

public void setMarprepay(String marprepay) {
	this.marprepay = marprepay;
}

public String getAprprepay() {
	return aprprepay;
}

public void setAprprepay(String aprprepay) {
	this.aprprepay = aprprepay;
}

public String getMayprepay() {
	return mayprepay;
}

public void setMayprepay(String mayprepay) {
	this.mayprepay = mayprepay;
}

public String getJunprepay() {
	return junprepay;
}

public void setJunprepay(String junprepay) {
	this.junprepay = junprepay;
}

public String getJulprepay() {
	return julprepay;
}

public void setJulprepay(String julprepay) {
	this.julprepay = julprepay;
}

public String getAugprepay() {
	return augprepay;
}

public void setAugprepay(String augprepay) {
	this.augprepay = augprepay;
}

public String getSepprepay() {
	return sepprepay;
}

public void setSepprepay(String sepprepay) {
	this.sepprepay = sepprepay;
}

public String getOctprepay() {
	return octprepay;
}

public void setOctprepay(String octprepay) {
	this.octprepay = octprepay;
}

public String getNovprepay() {
	return novprepay;
}

public void setNovprepay(String novprepay) {
	this.novprepay = novprepay;
}

public String getDecprepay() {
	return decprepay;
}

public void setDecprepay(String decprepay) {
	this.decprepay = decprepay;
}
public String getFathername() {
	return fathername;
}

public void setFathername(String fathername) {
	this.fathername = fathername;
}
public String getAsistantdoctor() {
	return asistantdoctor;
}

public void setAsistantdoctor(String asistantdoctor) {
	this.asistantdoctor = asistantdoctor;
}
public String getBirthplace() {
	return birthplace;
}

public void setBirthplace(String birthplace) {
	this.birthplace = birthplace;
}
public double getTotalupipayment() {
	return totalupipayment;
}

public void setTotalupipayment(double totalupipayment) {
	this.totalupipayment = totalupipayment;
}
private String 	fathername,birthplace;

@Override
public int compareTo(SummaryReport o) {
	return this.getInvoiceid1().compareTo(o.getInvoiceid1());
}
}
