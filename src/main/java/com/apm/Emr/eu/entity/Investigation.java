package com.apm.Emr.eu.entity;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Master.eu.entity.Master;

public class Investigation {
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getFinding1() {
		return finding1;
	}
	public void setFinding1(String finding1) {
		this.finding1 = finding1;
	}
	public String getFinding2() {
		return finding2;
	}
	public void setFinding2(String finding2) {
		this.finding2 = finding2;
	}
	public String getFinding3() {
		return finding3;
	}
	public void setFinding3(String finding3) {
		this.finding3 = finding3;
	}
	public String getFinding4() {
		return finding4;
	}
	public void setFinding4(String finding4) {
		this.finding4 = finding4;
	}
	private String deleted;
	private String invstcode;
	public int indx;
	private String opdid,created_date;
	private String finding1,finding2,finding3,finding4;
	private String balance;
	private double debit,discount,payment;
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getOpdid() {
		return opdid;
	}
	public void setOpdid(String opdid) {
		this.opdid = opdid;
	}
	private String selftp;
	
	private String clientname;
	private int count;
	
	private String department;
	
	private String signUser;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSelftp() {
		return selftp;
	}
	public void setSelftp(String selftp) {
		this.selftp = selftp;
	}
	public int getIndx() {
		return indx;
	}
	public void setIndx(int indx) {
		this.indx = indx;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	private String invsttype;
	private String invstgroup;
	private String invstname;
	private String specimen;
	private String invstreporttype;
	private String invstUnit;
	private String invpkg;
	
	private String machineInvList;
	private String mchinereqidlist;
	private int pkgid;
	private String mobile;
	private String outsource;
	private String outsourcedate;
	private String outsourceuserid;
	private String handoverto;
	private String isreturnOS;
	private String isreturndate;
	private String isreturnuserid;
	private String handoverfrom;
	private String handovertoDT;
	private String handovertouser;
	private String ammount;
	private String critical_value;
	private String value_cf;
	private boolean signuser_access;
	private String address;
	
	public String isAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isSignuser_access() {
		return signuser_access;
	}
	public void setSignuser_access(boolean signuser_access) {
		this.signuser_access = signuser_access;
	}
	public String getValue_cf() {
		return value_cf;
	}
	public void setValue_cf(String value_cf) {
		this.value_cf = value_cf;
	}
	public String getCritical_value() {
		return critical_value;
	}
	public void setCritical_value(String critical_value) {
		this.critical_value = critical_value;
	}
	public String getAmmount() {
		return ammount;
	}
	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}
	public String getHandovertoDT() {
		return handovertoDT;
	}
	public void setHandovertoDT(String handovertoDT) {
		this.handovertoDT = handovertoDT;
	}
	public String getHandovertouser() {
		return handovertouser;
	}
	public void setHandovertouser(String handovertouser) {
		this.handovertouser = handovertouser;
	}
	public String getOutsourcedate() {
		return outsourcedate;
	}
	public void setOutsourcedate(String outsourcedate) {
		this.outsourcedate = outsourcedate;
	}
	public String getOutsourceuserid() {
		return outsourceuserid;
	}
	public void setOutsourceuserid(String outsourceuserid) {
		this.outsourceuserid = outsourceuserid;
	}
	public String getHandoverto() {
		return handoverto;
	}
	public void setHandoverto(String handoverto) {
		this.handoverto = handoverto;
	}
	public String getIsreturnOS() {
		return isreturnOS;
	}
	public void setIsreturnOS(String isreturnOS) {
		this.isreturnOS = isreturnOS;
	}
	public String getIsreturndate() {
		return isreturndate;
	}
	public void setIsreturndate(String isreturndate) {
		this.isreturndate = isreturndate;
	}
	public String getIsreturnuserid() {
		return isreturnuserid;
	}
	public void setIsreturnuserid(String isreturnuserid) {
		this.isreturnuserid = isreturnuserid;
	}
	public String getHandoverfrom() {
		return handoverfrom;
	}
	public void setHandoverfrom(String handoverfrom) {
		this.handoverfrom = handoverfrom;
	}
	public String getOutsource() {
		return outsource;
	}
	public void setOutsource(String outsource) {
		this.outsource = outsource;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getPkgid() {
		return pkgid;
	}
	public void setPkgid(int pkgid) {
		this.pkgid = pkgid;
	}
	public String getMchinereqidlist() {
		return mchinereqidlist;
	}
	public void setMchinereqidlist(String mchinereqidlist) {
		this.mchinereqidlist = mchinereqidlist;
	}
	public String getMachineInvList() {
		return machineInvList;
	}
	public void setMachineInvList(String machineInvList) {
		this.machineInvList = machineInvList;
	}
	public String getInvpkg() {
		return invpkg;
	}
	public void setInvpkg(String invpkg) {
		this.invpkg = invpkg;
	}
	private String clientId;
	private String patientId;
	private int charginvoiceid;
	private boolean roundcharge;
	private String fromdate;
	private String todate;
	private String update_date="";
	
	private String outsourceid;
	
	private int new_invistigation;
	private int new_collected;
	private int new_completed;
	private int new_aproved;
	private String investigation_status;
	private String investigation_approve;
	private String approved_userid;
	
	private String name;
	private ArrayList<Investigation> outSourceList;
	
	
	public ArrayList<Investigation> getOutSourceList() {
		return outSourceList;
	}
	public void setOutSourceList(ArrayList<Investigation> outSourceList) {
		this.outSourceList = outSourceList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOutsourceid() {
		return outsourceid;
	}
	public void setOutsourceid(String outsourceid) {
		this.outsourceid = outsourceid;
	}
	public String getApproved_userid() {
		return approved_userid;
	}
	public void setApproved_userid(String approved_userid) {
		this.approved_userid = approved_userid;
	}
	public String getInvestigation_approve() {
		return investigation_approve;
	}
	public void setInvestigation_approve(String investigation_approve) {
		this.investigation_approve = investigation_approve;
	}
	public int getDeleted_investigation() {
		return deleted_investigation;
	}
	public void setDeleted_investigation(int deleted_investigation) {
		this.deleted_investigation = deleted_investigation;
	}
	private int deleted_investigation;
	public String getInvestigation_status() {
		return investigation_status;
	}
	public void setInvestigation_status(String investigation_status) {
		this.investigation_status = investigation_status;
	}
	public int getNew_invistigation() {
		return new_invistigation;
	}
	public void setNew_invistigation(int new_invistigation) {
		this.new_invistigation = new_invistigation;
	}
	public int getNew_collected() {
		return new_collected;
	}
	public void setNew_collected(int new_collected) {
		this.new_collected = new_collected;
	}
	public int getNew_completed() {
		return new_completed;
	}
	public void setNew_completed(int new_completed) {
		this.new_completed = new_completed;
	}
	public int getNew_aproved() {
		return new_aproved;
	}
	public void setNew_aproved(int new_aproved) {
		this.new_aproved = new_aproved;
	}
	public String getInvreq() {
		return invreq;
	}
	public void setInvreq(String invreq) {
		this.invreq = invreq;
	}
	private String remark;
	private String invreq;
	
	private String complete_date="";
	
	private String  conditionid;
	
	private String specialization="";
	private String collectstatus="0";
	private String collect_date="";;
	private String reporttype="";
	private String prectionerid;
	private int bold;
	private String status="0";
	private int pacs;

	private String prepay;
	private String postpay;
	private String otherpay;
	private String invstautoid;
	private String invstgroupid;
	
	private String english;
	private String regional;
	private String hindi;
	
	private int id;
	
	private String date;
	
	private String advoice;
	
	
	 private String fullname;
	 private String ageandgender;
	 private String abrivationid;
	 
	 private String normvalue;
	 
	 private String obsvalue;
	 
	 private String findings;
	 
	 private String biorefrange;
	 
	 private String methods;
	 
	 private String practitionerName;
	 
	 private String practitionerMob;
	 
	 private String bedname;
	 
	 private String wardname;
	 
	 private String ipdid;
	 
	 private String isAttachment;
	 
	 private String upstatus;
	 
	 private String updatedby;
	 
	private String flag; 
	
	private ArrayList<Emr> docList;
	
	private String jobtitle;
	
	private String whopay;
	
	private String invsttypeid;
	
	private String charge;
	
	private int checkChargeRaised;
	
	private int checkInvoiceCreated;
	
	private String userid;
	
	private String sectionid;
	private String urgentward;
	
	private String validnote;
	public String getValidnote() {
		return validnote;
	}
	public void setValidnote(String validnote) {
		this.validnote = validnote;
	}
	public String getUrgentward() {
		return urgentward;
	}
	public void setUrgentward(String urgentward) {
		this.urgentward = urgentward;
	}
	public String getSectionid() {
		return sectionid;
	}
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getCheckInvoiceCreated() {
		return checkInvoiceCreated;
	}
	public void setCheckInvoiceCreated(int checkInvoiceCreated) {
		this.checkInvoiceCreated = checkInvoiceCreated;
	}
	public int getCheckChargeRaised() {
		return checkChargeRaised;
	}
	public void setCheckChargeRaised(int checkChargeRaised) {
		this.checkChargeRaised = checkChargeRaised;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getInvstgroupid() {
		return invstgroupid;
	}
	public void setInvstgroupid(String invstgroupid) {
		this.invstgroupid = invstgroupid;
	}
	public String getInvsttypeid() {
		return invsttypeid;
	}
	public void setInvsttypeid(String invsttypeid) {
		this.invsttypeid = invsttypeid;
	}
	public String getWhopay() {
		return whopay;
	}
	public void setWhopay(String whopay) {
		this.whopay = whopay;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public ArrayList<Emr> getDocList() {
		return docList;
	}
	public void setDocList(ArrayList<Emr> docList) {
		this.docList = docList;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public String getUpstatus() {
		return upstatus;
	}
	public void setUpstatus(String upstatus) {
		this.upstatus = upstatus;
	}
	public String getIsAttachment() {
		return isAttachment;
	}
	public void setIsAttachment(String isAttachment) {
		this.isAttachment = isAttachment;
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
	public String getIpdid() {
		return ipdid;
	}
	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}
	public String getPractitionerMob() {
		return practitionerMob;
	}
	public void setPractitionerMob(String practitionerMob) {
		this.practitionerMob = practitionerMob;
	}
	public String getPractitionerName() {
		return practitionerName;
	}
	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}
	public String getFindings() {
		return findings;
	}
	public void setFindings(String findings) {
		this.findings = findings;
	}
	public String getBiorefrange() {
		return biorefrange;
	}
	public void setBiorefrange(String biorefrange) {
		this.biorefrange = biorefrange;
	}
	public String getMethods() {
		return methods;
	}
	public void setMethods(String methods) {
		this.methods = methods;
	}
	public String getObsvalue() {
		return obsvalue;
	}
	public void setObsvalue(String obsvalue) {
		this.obsvalue = obsvalue;
	}
	public String getNormvalue() {
		return normvalue;
	}
	public void setNormvalue(String normvalue) {
		this.normvalue = normvalue;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAgeandgender() {
		return ageandgender;
	}
	public void setAgeandgender(String ageandgender) {
		this.ageandgender = ageandgender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAdvoice() {
		return advoice;
	}
	public void setAdvoice(String advoice) {
		this.advoice = advoice;
	}
	public String getPrectionerid() {
		return prectionerid;
	}
	public void setPrectionerid(String prectionerid) {
		this.prectionerid = prectionerid;
	}
	public String getPrepay() {
		return prepay;
	}
	public void setPrepay(String prepay) {
		this.prepay = prepay;
	}
	public String getPostpay() {
		return postpay;
	}
	public void setPostpay(String postpay) {
		this.postpay = postpay;
	}
	public String getOtherpay() {
		return otherpay;
	}
	public void setOtherpay(String otherpay) {
		this.otherpay = otherpay;
	}
	public String getInvstautoid() {
		return invstautoid;
	}
	public void setInvstautoid(String invstautoid) {
		this.invstautoid = invstautoid;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	public String getHindi() {
		return hindi;
	}
	public void setHindi(String hindi) {
		this.hindi = hindi;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getConditionid() {
		return conditionid;
	}
	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}
	public String getInvstcode() {
		return invstcode;
	}
	public void setInvstcode(String invstcode) {
		this.invstcode = invstcode;
	}
	public String getInvsttype() {
		return invsttype;
	}
	public void setInvsttype(String invsttype) {
		this.invsttype = invsttype;
	}
	public String getInvstgroup() {
		return invstgroup;
	}
	public void setInvstgroup(String invstgroup) {
		this.invstgroup = invstgroup;
	}
	public String getInvstname() {
		return invstname;
	}
	public void setInvstname(String invstname) {
		this.invstname = invstname;
	}
	public String getSpecimen() {
		return specimen;
	}
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}
	public String getInvstreporttype() {
		return invstreporttype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setInvstreporttype(String invstreporttype) {
		this.invstreporttype = invstreporttype;
	}
	public String getInvstUnit() {
		return invstUnit;
	}
	public void setInvstUnit(String invstUnit) {
		this.invstUnit = invstUnit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCharginvoiceid() {
		return charginvoiceid;
	}
	public void setCharginvoiceid(int charginvoiceid) {
		this.charginvoiceid = charginvoiceid;
	}
	public int getPacs() {
		return pacs;
	}
	public void setPacs(int pacs) {
		this.pacs = pacs;
	}
	public boolean isRoundcharge() {
		return roundcharge;
	}
	public void setRoundcharge(boolean roundcharge) {
		this.roundcharge = roundcharge;
	}
	public int getBold() {
		return bold;
	}
	public void setBold(int bold) {
		this.bold = bold;
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
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getCollectstatus() {
		return collectstatus;
	}
	public void setCollectstatus(String collectstatus) {
		this.collectstatus = collectstatus;
	}
	public String getCollect_date() {
		return collect_date;
	}
	public void setCollect_date(String collect_date) {
		this.collect_date = collect_date;
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
public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
public int getTotalcharge() {
		return totalcharge;
	}
	public void setTotalcharge(int totalcharge) {
		this.totalcharge = totalcharge;
	}
public int getSharingtype() {
		return sharingtype;
	}
	public void setSharingtype(int sharingtype) {
		this.sharingtype = sharingtype;
	}
public String getUseridnew() {
		return useridnew;
	}
	public void setUseridnew(String useridnew) {
		this.useridnew = useridnew;
	}
public String getKunalobsval() {
		return kunalobsval;
	}
	public void setKunalobsval(String kunalobsval) {
		this.kunalobsval = kunalobsval;
	}
public ArrayList<Investigation> getFindinglist() {
		return findinglist;
	}
	public void setFindinglist(ArrayList<Investigation> findinglist) {
		this.findinglist = findinglist;
	}
public ArrayList<Master> getTemplatelist() {
		return templatelist;
	}
	public void setTemplatelist(ArrayList<Master> templatelist) {
		this.templatelist = templatelist;
	}
public boolean isDaycare() {
		return daycare;
	}
	public void setDaycare(boolean daycare) {
		this.daycare = daycare;
	}
public String getRequested_userid() {
		return requested_userid;
	}
	public void setRequested_userid(String requested_userid) {
		this.requested_userid = requested_userid;
	}
public Client getClientDetalis() {
		return clientDetalis;
	}
	public void setClientDetalis(Client clientDetalis) {
		this.clientDetalis = clientDetalis;
	}
public String getSignUser() {
		return signUser;
	}
	public void setSignUser(String signUser) {
		this.signUser = signUser;
	}
public String getOutsrcExpDate() {
		return outsrcExpDate;
	}
	public void setOutsrcExpDate(String outsrcExpDate) {
		this.outsrcExpDate = outsrcExpDate;
	}
private int parentid;
private String packagename;
private int totalcharge;
private int sharingtype;
private String useridnew;
private String kunalobsval;
private ArrayList<Investigation> findinglist;
private ArrayList<Master> templatelist;
private boolean daycare; 
private String requested_userid;
private Client clientDetalis;
private String outsrcExpDate;
private String thirdparty;

public String getThirdparty() {
	return thirdparty;
}
public void setThirdparty(String thirdparty) {
	this.thirdparty = thirdparty;
}
private String chargename;
private String code;
private String amount;
private String shortname;

public String getShortname() {
	return shortname;
}
public void setShortname(String shortname) {
	this.shortname = shortname;
}
public String getChargename() {
	return chargename;
}
public void setChargename(String chargename) {
	this.chargename = chargename;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
private String investnameid;

public String getInvestnameid() {
	return investnameid;
}
public void setInvestnameid(String investnameid) {
	this.investnameid = investnameid;
}
}
