package com.apm.ThirdParties.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Location;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;

public class ThirdPartyForm {
	private int id;
	
	
	private String mastername;
	
	private String dnaInitialCharge = "";
	
	private String completedInitialCharge = "";
	
	private String dnaFollowupCharge = "";
	private String fromdate;
	private String todate;
	private String completedFollowupCharge = "";
	
	private ArrayList<Client> condtitionList;
	private String dnaFinalCharge = "";
	
	private String completedFinalCharge = "";
	private String shortname;
	private String wardid;
	private String payee;
	private String area;
	private String unit="0";
	private boolean maintp,enrollcode,campArea;
	
	
	
	private String dnaMaintnanceCharge = "";
	
	private String completedMaintnanceCharge = "";
	private ArrayList<Bed> wardlist;
	
	//offset
	
	private String chargeType;
	private boolean initialOffsetdna;
	private String tpid;
	
	private boolean followupOffsetdna;
	
	private boolean finalOffsetdna;
	
	private boolean maintnanceOffsetdna;
	private ArrayList<Master> invsTypeList;
	private String invstype="0";
	private int ipdShare;
	private int surgeonShare;
	private int opdShare;
	
	private ArrayList<Master> masterlist;
	private ArrayList<TreatmentEpisode> sourceOfReferralList;
	
	private String isFromPG;
	private String patientType;
	
	private String validityFrom,validityTo,validityStatus,payementType;
	private ArrayList<Master> stateList;
	private ArrayList<Master> cityList;
	
	private String city,state;
	private String frompayercreation;
	
	
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
	public String getFrompayercreation() {
		return frompayercreation;
	}
	public void setFrompayercreation(String frompayercreation) {
		this.frompayercreation = frompayercreation;
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
	public ArrayList<Master> getStateList() {
		return stateList;
	}
	public void setStateList(ArrayList<Master> stateList) {
		this.stateList = stateList;
	}
	public ArrayList<Master> getCityList() {
		return cityList;
	}
	public void setCityList(ArrayList<Master> cityList) {
		this.cityList = cityList;
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
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getIsFromPG() {
		return isFromPG;
	}
	public void setIsFromPG(String isFromPG) {
		this.isFromPG = isFromPG;
	}
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}
	
	
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}


	private int thirdPartyId;
	private String thirdPartyName;
	private String description;
	private String type;
	private String name = "";
	private String name1 = "";
	private String gpaddress;
	private String secondLineAddress;
	private String salutation;
	private String title="";
	private String department;
	private String searchName;
	private String telephoneLine;
	private String email;
	private String emailCc;
	private String notes;
	private String companyDetails;
	private String companyName = "";
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
	private int clinicId;
	private String message;
	private String creditDuration;
	private ArrayList<ThirdParty> thirdPartyList;
	private ArrayList<Client> thirdPartyTypeNameList;
	private String thirdPartyCompanyName;
	private String dnaInitialApmt;
	private String dnafollowupApmt;
	private String dnafinalApmt;
	private String dnamaintenanceApmt;

	private String compltInitialApmt = "";
	private String compltfollowupApmt = "";
	private String compltfinalApmt  = "";
	private String compltmaintenanceApmt = "";
	
	//duration
	private String compltInitialApmtDuration = "";
	private String compltfollowupApmtDuration = "";
	private String compltfinalApmtDuration  = "";
	private String compltmaintenanceApmtDuration = "";
	
	
	private String gpname;
	private String workphno;
	private String gpemailid;
	private String gpfax;
	private String searchText = "";
	
	//add new ds/gp
	private String dstype;
	private String dsname;
	private String dstelephoneLine;
	private String dscompnyEmail;
	private String dsaddress;
	private String dstown;
	private String dspostcode;

	private String dscountry;
	private String dsworkphno;
	private String dsgpemailid;
	private String dsgpfax;
	private String dsnotes;
	
	
	//appointment type name
	private String compltInitialApmtName = "";
	private String compltfollowupApmtName = "";
	private String compltfinalApmtName = "";
	private String compltmaintenanceApmtName = "";
	
	private ArrayList<String> apmtDurationList;
	
	private String duration;
	
	private ArrayList<ThirdParty> tpnameList;
	
	private ArrayList<EmailTemplate> templateNameList;
	private ArrayList<ThirdParty> surgeryCompanyNameList;
	
	private ArrayList<ThirdParty> tpCompanyNameList;
	
	private String tpAccountSettingNotes;
	
	private ArrayList<Master> newChargeTypeList;
	
	private ArrayList<AppointmentType> appointmentTypeList;
	
	private Collection<DynamicAppointment> dynamicApmt = null;
	ArrayList<DynamicAppointment> dynamicApmtTypeList;
	private int danyamiclistpresent = 0;
	
	private boolean dnaForAll;
	
	private String compnyFax = "";
	
	private Collection<AppointmentType> tplist;
	
	
	

	
	public Collection<AppointmentType> getTplist() {
		return tplist;
	}
	public void setTplist(Collection<AppointmentType> tplist) {
		this.tplist = tplist;
	}
	public String getCompnyFax() {
		return compnyFax;
	}
	public void setCompnyFax(String compnyFax) {
		this.compnyFax = compnyFax;
	}
	public boolean isDnaForAll() {
		return dnaForAll;
	}
	public void setDnaForAll(boolean dnaForAll) {
		this.dnaForAll = dnaForAll;
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
	public int getDanyamiclistpresent() {
		return danyamiclistpresent;
	}
	public void setDanyamiclistpresent(int danyamiclistpresent) {
		this.danyamiclistpresent = danyamiclistpresent;
	}
	public ArrayList<DynamicAppointment> getDynamicApmtTypeList() {
		return dynamicApmtTypeList;
	}
	public void setDynamicApmtTypeList(
			ArrayList<DynamicAppointment> dynamicApmtTypeList) {
		this.dynamicApmtTypeList = dynamicApmtTypeList;
	}
	public Collection<DynamicAppointment> getDynamicApmt() {
		return dynamicApmt;
	}
	public void setDynamicApmt(Collection<DynamicAppointment> dynamicApmt) {
		this.dynamicApmt = dynamicApmt;
	}
	public String getTpAccountSettingNotes() {
		return tpAccountSettingNotes;
	}
	public void setTpAccountSettingNotes(String tpAccountSettingNotes) {
		this.tpAccountSettingNotes = tpAccountSettingNotes;
	}
	public String getGpaddress() {
		return gpaddress;
	}
	public void setGpaddress(String gpaddress) {
		this.gpaddress = gpaddress;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	
	public String getCompltInitialApmtName() {
		return compltInitialApmtName;
	}
	public void setCompltInitialApmtName(String compltInitialApmtName) {
		this.compltInitialApmtName = compltInitialApmtName;
	}
	public String getCompltfollowupApmtName() {
		return compltfollowupApmtName;
	}
	public void setCompltfollowupApmtName(String compltfollowupApmtName) {
		this.compltfollowupApmtName = compltfollowupApmtName;
	}
	public String getCompltfinalApmtName() {
		return compltfinalApmtName;
	}
	public void setCompltfinalApmtName(String compltfinalApmtName) {
		this.compltfinalApmtName = compltfinalApmtName;
	}
	public String getCompltmaintenanceApmtName() {
		return compltmaintenanceApmtName;
	}
	public void setCompltmaintenanceApmtName(String compltmaintenanceApmtName) {
		this.compltmaintenanceApmtName = compltmaintenanceApmtName;
	}
	public ArrayList<ThirdParty> getTpCompanyNameList() {
		return tpCompanyNameList;
	}
	public void setTpCompanyNameList(ArrayList<ThirdParty> tpCompanyNameList) {
		this.tpCompanyNameList = tpCompanyNameList;
	}
	public ArrayList<ThirdParty> getSurgeryCompanyNameList() {
		return surgeryCompanyNameList;
	}
	public void setSurgeryCompanyNameList(
			ArrayList<ThirdParty> surgeryCompanyNameList) {
		this.surgeryCompanyNameList = surgeryCompanyNameList;
	}
	
	
	public ArrayList<ThirdParty> getTpnameList() {
		return tpnameList;
	}
	public void setTpnameList(ArrayList<ThirdParty> tpnameList) {
		this.tpnameList = tpnameList;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public ArrayList<String> getApmtDurationList() {
		return apmtDurationList;
	}
	public void setApmtDurationList(ArrayList<String> apmtDurationList) {
		this.apmtDurationList = apmtDurationList;
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
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	private ArrayList<String> countryList;
	public ArrayList<String> getCountryList() {
		return countryList;
	}
	public void setCountryList(ArrayList<String> countryList) {
		this.countryList = countryList;
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
	private String typeName;
	
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
	public String getThirdPartyCompanyName() {
		return thirdPartyCompanyName;
	}
	public void setThirdPartyCompanyName(String thirdPartyCompanyName) {
		this.thirdPartyCompanyName = thirdPartyCompanyName;
	}
	public ArrayList<Client> getThirdPartyTypeNameList() {
		return thirdPartyTypeNameList;
	}
	public void setThirdPartyTypeNameList(ArrayList<Client> thirdPartyTypeNameList) {
		this.thirdPartyTypeNameList = thirdPartyTypeNameList;
	}
	public ArrayList<ThirdParty> getThirdPartyList() {
		return thirdPartyList;
	}
	public void setThirdPartyList(ArrayList<ThirdParty> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}
	public String getGptown() {
		return gptown;
	}
	public void setGptown(String gptown) {
		this.gptown = gptown;
	}
	private String gptown;
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
	private String creditReminderDuration;
	//List
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	ArrayList<ThirdParty>thirdPartyTypeList;
	ArrayList<ThirdParty>thirdPartyDetailList;
	
	private String pagerecords;
	private String page_records;
	public String getPage_records() {
		return page_records;
	}
	public void setPage_records(String page_records) {
		this.page_records = page_records;
	}


	private int totalRecords;
	
	public ArrayList<ThirdParty> getThirdPartyDetailList() {
		return thirdPartyDetailList;
	}
	public void setThirdPartyDetailList(ArrayList<ThirdParty> thirdPartyDetailList) {
		this.thirdPartyDetailList = thirdPartyDetailList;
	}
	public ArrayList<ThirdParty> getThirdPartyTypeList() {
		return thirdPartyTypeList;
	}
	public void setThirdPartyTypeList(ArrayList<ThirdParty> thirdPartyTypeList) {
		this.thirdPartyTypeList = thirdPartyTypeList;
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
	public void setDefaultApmtBookingConfmTemp(String defaultApmtBookingConfmTemp) {
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
	public ArrayList<Master> getNewChargeTypeList() {
		return newChargeTypeList;
	}
	public void setNewChargeTypeList(ArrayList<Master> newChargeTypeList) {
		this.newChargeTypeList = newChargeTypeList;
	}
	public ArrayList<AppointmentType> getAppointmentTypeList() {
		return appointmentTypeList;
	}
	public void setAppointmentTypeList(
			ArrayList<AppointmentType> appointmentTypeList) {
		this.appointmentTypeList = appointmentTypeList;
	}
	public String getTpid() {
		return tpid;
	}
	public void setTpid(String tpid) {
		this.tpid = tpid;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public ArrayList<Bed> getWardlist() {
		return wardlist;
	}
	public void setWardlist(ArrayList<Bed> wardlist) {
		this.wardlist = wardlist;
	}
	public String getWardid() {
		return wardid;
	}
	public void setWardid(String wardid) {
		this.wardid = wardid;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public ArrayList<Master> getInvsTypeList() {
		return invsTypeList;
	}
	public void setInvsTypeList(ArrayList<Master> invsTypeList) {
		this.invsTypeList = invsTypeList;
	}
	public String getInvstype() {
		return invstype;
	}
	public void setInvstype(String invstype) {
		this.invstype = invstype;
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
	public void setMaintp(boolean maintp) {
		this.maintp = maintp;
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
	public ArrayList<EmailTemplate> getTemplateNameList() {
		return templateNameList;
	}
	public void setTemplateNameList(ArrayList<EmailTemplate> templateNameList) {
		this.templateNameList = templateNameList;
	}
	public ArrayList<Client> getCondtitionList() {
		return condtitionList;
	}
	public void setCondtitionList(ArrayList<Client> condtitionList) {
		this.condtitionList = condtitionList;
	}
	public ArrayList<TreatmentEpisode> getSourceOfReferralList() {
		return sourceOfReferralList;
	}
	public void setSourceOfReferralList(ArrayList<TreatmentEpisode> sourceOfReferralList) {
		this.sourceOfReferralList = sourceOfReferralList;
	}
	
	
	
	
	
}
