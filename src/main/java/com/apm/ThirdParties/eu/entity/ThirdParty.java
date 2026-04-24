package com.apm.ThirdParties.eu.entity;

import java.util.ArrayList;

import com.apm.ThirdParties.web.form.DynamicAppointment;

public class ThirdParty {
	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public String getChargeid() {
		return chargeid;
	}

	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}

	public String getChargetype() {
		return chargetype;
	}

	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}

	private int id;
	
	private String dnaInitialCharge = "";
	
	private String completedInitialCharge = "";
	
	private String dnaFollowupCharge = "";
	private String shortname;
	private String completedFollowupCharge = "";
	private String spendlimit;
	private String dnaFinalCharge = "";
	private String abrivationid;
	private String completedFinalCharge = "";
	private String clientid;
	private int rstatus=0;
	private String ref_date;
	private int ipdid;
	private boolean maintp,enrollcode,campArea;
	private String commencing;
	private String authcode;
	private String dnaMaintnanceCharge = "";
	private int submitted=0;
	private int completed=0;
	private int approved=0;
	
	private String completedMaintnanceCharge = "";
	
	private int ipdShare;
	private String fullname;
	private int surgeonShare;
	private int opdShare;
	
	private String area;
	private String unit="0";
	private String ipdopd="0";
	
	//offset
	
	private boolean initialOffsetdna;
	
	private boolean followupOffsetdna;
	
	private boolean finalOffsetdna;
	
	private boolean maintnanceOffsetdna;
	
	private boolean offset;
	
	private String tpTypeName = "";
	
	
	private boolean dnaForAll;
	private String agegender;
	
	private String chargecolumnname;
	
	private String patientType;
	
	private String validityFrom,validityTo,validityStatus,payementType;
	
	private String masterCharge,chargeName,chargeAmount,code,ids;
	
	private String city,state;
	private String chargetype,chargeid,charges;
	
	
	public boolean isCampArea() {
		return campArea;
	}

	public void setCampArea(boolean campArea) {
		this.campArea = campArea;
	}

	public boolean isEnrollcode() {
		return enrollcode;
	}

	public void setEnrollcode(boolean enrollcode) {
		this.enrollcode = enrollcode;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMasterCharge() {
		return masterCharge;
	}

	public void setMasterCharge(String masterCharge) {
		this.masterCharge = masterCharge;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(String validityFrom) {
		this.validityFrom = validityFrom;
	}

	public String getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(String validityTo) {
		this.validityTo = validityTo;
	}

	public String getValidityStatus() {
		return validityStatus;
	}

	public void setValidityStatus(String validityStatus) {
		this.validityStatus = validityStatus;
	}

	public String getPayementType() {
		return payementType;
	}

	public void setPayementType(String payementType) {
		this.payementType = payementType;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getChargecolumnname() {
		return chargecolumnname;
	}

	public void setChargecolumnname(String chargecolumnname) {
		this.chargecolumnname = chargecolumnname;
	}

	public boolean isDnaForAll() {
		return dnaForAll;
	}

	public void setDnaForAll(boolean dnaForAll) {
		this.dnaForAll = dnaForAll;
	}

	public String getTpTypeName() {
		return tpTypeName;
	}

	public void setTpTypeName(String tpTypeName) {
		this.tpTypeName = tpTypeName;
	}

	public boolean isOffset() {
		return offset;
	}

	public void setOffset(boolean offset) {
		this.offset = offset;
	}

	private int thirdPartyId;
	private String thirdPartyName;
	private String description;
	private String type;
	private String name;
	private String salutation;
	private String title;
	private String department;
	private String gpaddress;

	private String searchName;
	private String telephoneLine;
	private String email;
	private String emailCc;
	private String notes;
	private String companyDetails;
	private String companyName;
	private String address;
	private String town;
	private String country;
	private String postcode;
	private String compnyTelephone;
	private String fax;
	private String web;
	private String referenceNo;
	private String compnyEmail;
	private String warningMsg;
	private String accountsNotes;
	private boolean accountMustBeInCredit;
	private String accountCreditLimit;
	private String accountWarningLimit;
	private String defaultApmtBookingConfmTemp;
	private String defaultApmtDnaTemp;
	private String confContactEmail;
	private String dnaContactEmail;
	private String dnaLimit;
	private String outInvoiceLimit;
	private String bedname;
	private int clinicId;
	private String creditDuration;
	private String creditReminderDuration;
	private String gptown;
	private String dnaInitialApmt;
	private String dnafollowupApmt;
	private String dnafinalApmt;
	private String dnamaintenanceApmt;
    private String usedsession,refenddate;
	private String compltInitialApmt;
	private String compltfollowupApmt;
	private String compltfinalApmt;
	private String compltmaintenanceApmt;

	private String county;

	// duration
	private String compltInitialApmtDuration = "";
	private String compltfollowupApmtDuration = "";
	private String compltfinalApmtDuration = "";
	private String compltmaintenanceApmtDuration = "";

	private String typeName;
	private String gpname;
	private String workphno;
	private String gpemailid;
	private String gpfax;

	// appointment name
	private String initialAppointmentName = "";
	private String followupAppointmentName = "";
	private String finalAppointmentName = "";
	private String maintnanceAppointmentName = "";

	private String tpnameid;

	// add new ds/gp
	private String dstype;
	private String dsname;
	private String dstelephoneLine;
	private String dscompnyEmail;
	private String dsaddress;
	private String dstown;
	private String dspostcode;
	private String dsgpname;
	private String dscountry;
	private String dsworkphno;
	private String dsgpemailid;
	private String dsgpfax;
	private String dsnotes;

	private String tpAccountSettingNotes;
	
	//dynamic fields of apmt
	private String dnaName;
	private String dnaCharge;
	private String apmtName;
	private String apmtCharge;
	private String apmtDuaration;
	private String secondLineAddress ="";
	private String newipdabbr;
	
	
	
	
	
	
	
	
	public String getNewipdabbr() {
		return newipdabbr;
	}

	public void setNewipdabbr(String newipdabbr) {
		this.newipdabbr = newipdabbr;
	}

	public boolean isInitialOffsetdna() {
		return initialOffsetdna;
	}

	public void setInitialOffsetdna(boolean initialOffsetdna) {
		this.initialOffsetdna = initialOffsetdna;
	}

	public boolean isFollowupOffsetdna() {
		return followupOffsetdna;
	}

	public void setFollowupOffsetdna(boolean followupOffsetdna) {
		this.followupOffsetdna = followupOffsetdna;
	}

	public boolean isFinalOffsetdna() {
		return finalOffsetdna;
	}

	public void setFinalOffsetdna(boolean finalOffsetdna) {
		this.finalOffsetdna = finalOffsetdna;
	}

	public boolean isMaintnanceOffsetdna() {
		return maintnanceOffsetdna;
	}

	public void setMaintnanceOffsetdna(boolean maintnanceOffsetdna) {
		this.maintnanceOffsetdna = maintnanceOffsetdna;
	}

	public String getDnaInitialCharge() {
		return dnaInitialCharge;
	}

	public void setDnaInitialCharge(String dnaInitialCharge) {
		this.dnaInitialCharge = dnaInitialCharge;
	}

	public String getCompletedInitialCharge() {
		return completedInitialCharge;
	}

	public void setCompletedInitialCharge(String completedInitialCharge) {
		this.completedInitialCharge = completedInitialCharge;
	}

	public String getDnaFollowupCharge() {
		return dnaFollowupCharge;
	}

	public void setDnaFollowupCharge(String dnaFollowupCharge) {
		this.dnaFollowupCharge = dnaFollowupCharge;
	}

	public String getCompletedFollowupCharge() {
		return completedFollowupCharge;
	}

	public void setCompletedFollowupCharge(String completedFollowupCharge) {
		this.completedFollowupCharge = completedFollowupCharge;
	}

	public String getDnaFinalCharge() {
		return dnaFinalCharge;
	}

	public void setDnaFinalCharge(String dnaFinalCharge) {
		this.dnaFinalCharge = dnaFinalCharge;
	}

	public String getCompletedFinalCharge() {
		return completedFinalCharge;
	}

	public void setCompletedFinalCharge(String completedFinalCharge) {
		this.completedFinalCharge = completedFinalCharge;
	}

	public String getDnaMaintnanceCharge() {
		return dnaMaintnanceCharge;
	}

	public void setDnaMaintnanceCharge(String dnaMaintnanceCharge) {
		this.dnaMaintnanceCharge = dnaMaintnanceCharge;
	}

	public String getCompletedMaintnanceCharge() {
		return completedMaintnanceCharge;
	}

	public void setCompletedMaintnanceCharge(String completedMaintnanceCharge) {
		this.completedMaintnanceCharge = completedMaintnanceCharge;
	}

	public String getSecondLineAddress() {
		return secondLineAddress;
	}

	public void setSecondLineAddress(String secondLineAddress) {
		this.secondLineAddress = secondLineAddress;
	}

	public String getDnaName() {
		return dnaName;
	}

	public void setDnaName(String dnaName) {
		this.dnaName = dnaName;
	}

	private ArrayList<DynamicAppointment> dynamicApmtTypeList;

	public ArrayList<DynamicAppointment> getDynamicApmtTypeList() {
		return dynamicApmtTypeList;
	}

	public void setDynamicApmtTypeList(
			ArrayList<DynamicAppointment> dynamicApmtTypeList) {
		this.dynamicApmtTypeList = dynamicApmtTypeList;
	}

	public String getDnaCharge() {
		return dnaCharge;
	}

	public void setDnaCharge(String dnaCharge) {
		this.dnaCharge = dnaCharge;
	}

	public String getApmtName() {
		return apmtName;
	}

	public void setApmtName(String apmtName) {
		this.apmtName = apmtName;
	}

	public String getApmtCharge() {
		return apmtCharge;
	}

	public void setApmtCharge(String apmtCharge) {
		this.apmtCharge = apmtCharge;
	}

	public String getApmtDuaration() {
		return apmtDuaration;
	}

	public void setApmtDuaration(String apmtDuaration) {
		this.apmtDuaration = apmtDuaration;
	}

	public String getGpaddress() {
		return gpaddress;
	}

	public void setGpaddress(String gpaddress) {
		this.gpaddress = gpaddress;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTpAccountSettingNotes() {
		return tpAccountSettingNotes;
	}

	public void setTpAccountSettingNotes(String tpAccountSettingNotes) {
		this.tpAccountSettingNotes = tpAccountSettingNotes;
	}

	public String getDsgpname() {
		return dsgpname;
	}

	public void setDsgpname(String dsgpname) {
		this.dsgpname = dsgpname;
	}

	public String getDstype() {
		return dstype;
	}

	public void setDstype(String dstype) {
		this.dstype = dstype;
	}

	public String getDsname() {
		return dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	public String getDstelephoneLine() {
		return dstelephoneLine;
	}

	public void setDstelephoneLine(String dstelephoneLine) {
		this.dstelephoneLine = dstelephoneLine;
	}

	public String getDscompnyEmail() {
		return dscompnyEmail;
	}

	public void setDscompnyEmail(String dscompnyEmail) {
		this.dscompnyEmail = dscompnyEmail;
	}

	public String getDsaddress() {
		return dsaddress;
	}

	public void setDsaddress(String dsaddress) {
		this.dsaddress = dsaddress;
	}

	public String getDstown() {
		return dstown;
	}

	public void setDstown(String dstown) {
		this.dstown = dstown;
	}

	public String getDspostcode() {
		return dspostcode;
	}

	public void setDspostcode(String dspostcode) {
		this.dspostcode = dspostcode;
	}

	public String getDscountry() {
		return dscountry;
	}

	public void setDscountry(String dscountry) {
		this.dscountry = dscountry;
	}

	public String getDsworkphno() {
		return dsworkphno;
	}

	public void setDsworkphno(String dsworkphno) {
		this.dsworkphno = dsworkphno;
	}

	public String getDsgpemailid() {
		return dsgpemailid;
	}

	public void setDsgpemailid(String dsgpemailid) {
		this.dsgpemailid = dsgpemailid;
	}

	public String getDsgpfax() {
		return dsgpfax;
	}

	public void setDsgpfax(String dsgpfax) {
		this.dsgpfax = dsgpfax;
	}

	public String getDsnotes() {
		return dsnotes;
	}

	public void setDsnotes(String dsnotes) {
		this.dsnotes = dsnotes;
	}

	public String getTpnameid() {
		return tpnameid;
	}

	public void setTpnameid(String tpnameid) {
		this.tpnameid = tpnameid;
	}

	public String getInitialAppointmentName() {
		return initialAppointmentName;
	}

	public void setInitialAppointmentName(String initialAppointmentName) {
		this.initialAppointmentName = initialAppointmentName;
	}

	public String getFollowupAppointmentName() {
		return followupAppointmentName;
	}

	public void setFollowupAppointmentName(String followupAppointmentName) {
		this.followupAppointmentName = followupAppointmentName;
	}

	public String getFinalAppointmentName() {
		return finalAppointmentName;
	}

	public void setFinalAppointmentName(String finalAppointmentName) {
		this.finalAppointmentName = finalAppointmentName;
	}

	public String getMaintnanceAppointmentName() {
		return maintnanceAppointmentName;
	}

	public void setMaintnanceAppointmentName(String maintnanceAppointmentName) {
		this.maintnanceAppointmentName = maintnanceAppointmentName;
	}

	public String getCompltInitialApmtDuration() {
		return compltInitialApmtDuration;
	}

	public void setCompltInitialApmtDuration(String compltInitialApmtDuration) {
		this.compltInitialApmtDuration = compltInitialApmtDuration;
	}

	public String getCompltfollowupApmtDuration() {
		return compltfollowupApmtDuration;
	}

	public void setCompltfollowupApmtDuration(String compltfollowupApmtDuration) {
		this.compltfollowupApmtDuration = compltfollowupApmtDuration;
	}

	public String getCompltfinalApmtDuration() {
		return compltfinalApmtDuration;
	}

	public void setCompltfinalApmtDuration(String compltfinalApmtDuration) {
		this.compltfinalApmtDuration = compltfinalApmtDuration;
	}

	public String getCompltmaintenanceApmtDuration() {
		return compltmaintenanceApmtDuration;
	}

	public void setCompltmaintenanceApmtDuration(
			String compltmaintenanceApmtDuration) {
		this.compltmaintenanceApmtDuration = compltmaintenanceApmtDuration;
	}

	public String getGpname() {
		return gpname;
	}

	public void setGpname(String gpname) {
		this.gpname = gpname;
	}

	public String getWorkphno() {
		return workphno;
	}

	public void setWorkphno(String workphno) {
		this.workphno = workphno;
	}

	public String getGpemailid() {
		return gpemailid;
	}

	public void setGpemailid(String gpemailid) {
		this.gpemailid = gpemailid;
	}

	public String getGpfax() {
		return gpfax;
	}

	public void setGpfax(String gpfax) {
		this.gpfax = gpfax;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDnaInitialApmt() {
		return dnaInitialApmt;
	}

	public void setDnaInitialApmt(String dnaInitialApmt) {
		this.dnaInitialApmt = dnaInitialApmt;
	}

	public String getDnafollowupApmt() {
		return dnafollowupApmt;
	}

	public void setDnafollowupApmt(String dnafollowupApmt) {
		this.dnafollowupApmt = dnafollowupApmt;
	}

	public String getDnafinalApmt() {
		return dnafinalApmt;
	}

	public void setDnafinalApmt(String dnafinalApmt) {
		this.dnafinalApmt = dnafinalApmt;
	}

	public String getDnamaintenanceApmt() {
		return dnamaintenanceApmt;
	}

	public void setDnamaintenanceApmt(String dnamaintenanceApmt) {
		this.dnamaintenanceApmt = dnamaintenanceApmt;
	}

	public String getCompltInitialApmt() {
		return compltInitialApmt;
	}

	public void setCompltInitialApmt(String compltInitialApmt) {
		this.compltInitialApmt = compltInitialApmt;
	}

	public String getCompltfollowupApmt() {
		return compltfollowupApmt;
	}

	public void setCompltfollowupApmt(String compltfollowupApmt) {
		this.compltfollowupApmt = compltfollowupApmt;
	}

	public String getCompltfinalApmt() {
		return compltfinalApmt;
	}

	public void setCompltfinalApmt(String compltfinalApmt) {
		this.compltfinalApmt = compltfinalApmt;
	}

	public String getCompltmaintenanceApmt() {
		return compltmaintenanceApmt;
	}

	public void setCompltmaintenanceApmt(String compltmaintenanceApmt) {
		this.compltmaintenanceApmt = compltmaintenanceApmt;
	}

	public String getGptown() {
		return gptown;
	}

	public void setGptown(String gptown) {
		this.gptown = gptown;
	}

	public String getCreditDuration() {
		return creditDuration;
	}

	public void setCreditDuration(String creditDuration) {
		this.creditDuration = creditDuration;
	}

	public String getCreditReminderDuration() {
		return creditReminderDuration;
	}

	public void setCreditReminderDuration(String creditReminderDuration) {
		this.creditReminderDuration = creditReminderDuration;
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	public String getDnaLimit() {
		return dnaLimit;
	}

	public void setDnaLimit(String dnaLimit) {
		this.dnaLimit = dnaLimit;
	}

	public String getOutInvoiceLimit() {
		return outInvoiceLimit;
	}

	public void setOutInvoiceLimit(String outInvoiceLimit) {
		this.outInvoiceLimit = outInvoiceLimit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getThirdPartyId() {
		return thirdPartyId;
	}

	public void setThirdPartyId(int thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}

	public String getThirdPartyName() {
		return thirdPartyName;
	}

	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getTelephoneLine() {
		return telephoneLine;
	}

	public void setTelephoneLine(String telephoneLine) {
		this.telephoneLine = telephoneLine;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCompanyDetails() {
		return companyDetails;
	}

	public void setCompanyDetails(String companyDetails) {
		this.companyDetails = companyDetails;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCompnyTelephone() {
		return compnyTelephone;
	}

	public void setCompnyTelephone(String compnyTelephone) {
		this.compnyTelephone = compnyTelephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getCompnyEmail() {
		return compnyEmail;
	}

	public void setCompnyEmail(String compnyEmail) {
		this.compnyEmail = compnyEmail;
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getAccountsNotes() {
		return accountsNotes;
	}

	public void setAccountsNotes(String accountsNotes) {
		this.accountsNotes = accountsNotes;
	}

	public String getAccountCreditLimit() {
		return accountCreditLimit;
	}

	public void setAccountCreditLimit(String accountCreditLimit) {
		this.accountCreditLimit = accountCreditLimit;
	}

	public String getAccountWarningLimit() {
		return accountWarningLimit;
	}

	public void setAccountWarningLimit(String accountWarningLimit) {
		this.accountWarningLimit = accountWarningLimit;
	}

	public String getDefaultApmtBookingConfmTemp() {
		return defaultApmtBookingConfmTemp;
	}

	public void setDefaultApmtBookingConfmTemp(
			String defaultApmtBookingConfmTemp) {
		this.defaultApmtBookingConfmTemp = defaultApmtBookingConfmTemp;
	}

	public String getDefaultApmtDnaTemp() {
		return defaultApmtDnaTemp;
	}

	public void setDefaultApmtDnaTemp(String defaultApmtDnaTemp) {
		this.defaultApmtDnaTemp = defaultApmtDnaTemp;
	}

	public String getConfContactEmail() {
		return confContactEmail;
	}

	public void setConfContactEmail(String confContactEmail) {
		this.confContactEmail = confContactEmail;
	}

	public String getDnaContactEmail() {
		return dnaContactEmail;
	}

	public void setDnaContactEmail(String dnaContactEmail) {
		this.dnaContactEmail = dnaContactEmail;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isAccountMustBeInCredit() {
		return accountMustBeInCredit;
	}

	public void setAccountMustBeInCredit(boolean accountMustBeInCredit) {
		this.accountMustBeInCredit = accountMustBeInCredit;
	}

	public int getIpdShare() {
		return ipdShare;
	}

	public void setIpdShare(int ipdShare) {
		this.ipdShare = ipdShare;
	}

	public int getSurgeonShare() {
		return surgeonShare;
	}

	public void setSurgeonShare(int surgeonShare) {
		this.surgeonShare = surgeonShare;
	}

	public int getOpdShare() {
		return opdShare;
	}

	public void setOpdShare(int opdShare) {
		this.opdShare = opdShare;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public boolean isMaintp() {
		return maintp;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getRef_date() {
		return ref_date;
	}

	public void setRef_date(String ref_date) {
		this.ref_date = ref_date;
	}

	public String getSpendlimit() {
		return spendlimit;
	}

	public void setSpendlimit(String spendlimit) {
		this.spendlimit = spendlimit;
	}

	public void setMaintp(boolean maintp) {
		this.maintp = maintp;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getUsedsession() {
		return usedsession;
	}

	public void setUsedsession(String usedsession) {
		this.usedsession = usedsession;
	}

	public String getRefenddate() {
		return refenddate;
	}

	public void setRefenddate(String refenddate) {
		this.refenddate = refenddate;
	}

	public String getBedname() {
		return bedname;
	}

	public void setBedname(String bedname) {
		this.bedname = bedname;
	}

	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAgegender() {
		return agegender;
	}

	public void setAgegender(String agegender) {
		this.agegender = agegender;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getIpdopd() {
		return ipdopd;
	}

	public void setIpdopd(String ipdopd) {
		this.ipdopd = ipdopd;
	}

	public int getIpdid() {
		return ipdid;
	}

	public void setIpdid(int ipdid) {
		this.ipdid = ipdid;
	}

	public int getSubmitted() {
		return submitted;
	}

	public void setSubmitted(int submitted) {
		this.submitted = submitted;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getRstatus() {
		return rstatus;
	}

	public void setRstatus(int rstatus) {
		this.rstatus = rstatus;
	}
	
	
}
