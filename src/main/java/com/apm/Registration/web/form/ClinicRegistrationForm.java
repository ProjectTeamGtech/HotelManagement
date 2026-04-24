package com.apm.Registration.web.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.Clinic;


public class ClinicRegistrationForm {
	
	private boolean smsrelativeDischarge;
	private boolean smsdoctorBedchange;
	private boolean smscheckrelativebedchange;
	private boolean smscheckdoctor;
	private boolean smspatientCompleted;
	private boolean smspatientApproved,opd_payamnt_sms,ipd_payment_sms,sms_cancel_appointment;
	private ArrayList<Master>jobGroupList;
	
	private String discount_show;
	
	private String hospname;
	private String regional_lang;
	private boolean auto_generic_name;
	private String default_phar_location;
	private String opdfollowup;
	private ArrayList<Master> locationListPharmacy;
	private String default_phar_location_name;
	private boolean prisc_print;
	private boolean direct_prisc;
	private boolean invest_savenprint;
	private boolean prisc_savenprint;
	private String month_filter;
	private String year_filter;
	private ArrayList<Clinic> smscountmonthlylist;
	private String fullname;
	private int grn_to_prisc_location;
	
	public int getGrn_to_prisc_location() {
		return grn_to_prisc_location;
	}

	public void setGrn_to_prisc_location(int grn_to_prisc_location) {
		this.grn_to_prisc_location = grn_to_prisc_location;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public ArrayList<Clinic> getSmscountmonthlylist() {
		return smscountmonthlylist;
	}

	public void setSmscountmonthlylist(ArrayList<Clinic> smscountmonthlylist) {
		this.smscountmonthlylist = smscountmonthlylist;
	}

	public String getMonth_filter() {
		return month_filter;
	}

	public void setMonth_filter(String month_filter) {
		this.month_filter = month_filter;
	}

	public String getYear_filter() {
		return year_filter;
	}

	public void setYear_filter(String year_filter) {
		this.year_filter = year_filter;
	}

	public boolean isInvest_savenprint() {
		return invest_savenprint;
	}

	public void setInvest_savenprint(boolean invest_savenprint) {
		this.invest_savenprint = invest_savenprint;
	}

	public boolean isPrisc_savenprint() {
		return prisc_savenprint;
	}

	public void setPrisc_savenprint(boolean prisc_savenprint) {
		this.prisc_savenprint = prisc_savenprint;
	}

	public boolean isDirect_prisc() {
		return direct_prisc;
	}

	public void setDirect_prisc(boolean direct_prisc) {
		this.direct_prisc = direct_prisc;
	}

	public boolean isPrisc_print() {
		return prisc_print;
	}

	public void setPrisc_print(boolean prisc_print) {
		this.prisc_print = prisc_print;
	}

	public String getDefault_phar_location_name() {
		return default_phar_location_name;
	}

	public void setDefault_phar_location_name(String default_phar_location_name) {
		this.default_phar_location_name = default_phar_location_name;
	}

	public String getDefault_phar_location() {
		return default_phar_location;
	}

	public ArrayList<Master> getLocationListPharmacy() {
		return locationListPharmacy;
	}

	public void setLocationListPharmacy(ArrayList<Master> locationListPharmacy) {
		this.locationListPharmacy = locationListPharmacy;
	}

	public String isDefault_phar_location() {
		return default_phar_location;
	}

	public void setDefault_phar_location(String default_phar_location) {
		this.default_phar_location = default_phar_location;
	}

	public boolean isAuto_generic_name() {
		return auto_generic_name;
	}

	public void setAuto_generic_name(boolean auto_generic_name) {
		this.auto_generic_name = auto_generic_name;
	}

	public String getHospname() {
		return hospname;
	}

	public void setHospname(String hospname) {
		this.hospname = hospname;
	}

	public String getDiscount_show() {
		return discount_show;
	}

	public void setDiscount_show(String discount_show) {
		this.discount_show = discount_show;
	}

	public ArrayList<Master> getJobGroupList() {
		return jobGroupList;
	}

	public void setJobGroupList(ArrayList<Master> jobGroupList) {
		this.jobGroupList = jobGroupList;
	}

	public boolean isSmspatientCompleted() {
		return smspatientCompleted;
	}

	public void setSmspatientCompleted(boolean smspatientCompleted) {
		this.smspatientCompleted = smspatientCompleted;
	}

	public boolean isSmspatientApproved() {
		return smspatientApproved;
	}

	public void setSmspatientApproved(boolean smspatientApproved) {
		this.smspatientApproved = smspatientApproved;
	}


	private String invoice_date;
	private String roleid;
	private String moduleid;
	private String job_title;
	private ArrayList<Clinic> jobtitlelist;
	private ArrayList<Clinic> accessmodulelist;
	private String name;
	private ArrayList<Clinic> jobTitleList;
	
	private String withpayment;
	public boolean isSmsrelativeDischarge() {
		return smsrelativeDischarge;
	}

	public void setSmsrelativeDischarge(boolean smsrelativeDischarge) {
		this.smsrelativeDischarge = smsrelativeDischarge;
	}

	public boolean isSmsdoctorBedchange() {
		return smsdoctorBedchange;
	}

	public void setSmsdoctorBedchange(boolean smsdoctorBedchange) {
		this.smsdoctorBedchange = smsdoctorBedchange;
	}

	public boolean isSmscheckrelativebedchange() {
		return smscheckrelativebedchange;
	}

	public void setSmscheckrelativebedchange(boolean smscheckrelativebedchange) {
		this.smscheckrelativebedchange = smscheckrelativebedchange;
	}

	public boolean isSmscheckdoctor() {
		return smscheckdoctor;
	}

	public void setSmscheckdoctor(boolean smscheckdoctor) {
		this.smscheckdoctor = smscheckdoctor;
	}


	private String withoutpayment;
	private String message;
	private String websiteUrl;
	private String owner_qualification;
	private String loc_direction;
	private File userImage;
	private String userImageFileName;
	private String userImageContentType;
	
	private boolean smscheck;
	private String status;
	
	private int checkMailId;
	
	private ArrayList<Master> specilizationList;
	private ArrayList<Master> ipdformfieldList;
	private String selectedUserid;
	
	private String updateSaveLocation;
	
	private String refLocation;
	
	private String sTime;
	private boolean smsPayment;
	private String setupstdcharge="0";
	ArrayList<String>startTimeList;
	
	private String endTime;
	
	private ArrayList<String>endTimeList;
	private ArrayList<String> advanceTimeList;
	
	private ArrayList<Master>countrycodeList;
	
	private String countrycode;
	private String excess;
   
	private String advanceTime;

	
	private String advancerenge;
	private String ipdregcharge;
	private boolean ctp;
	private int smscount;
	private boolean cself;
	
	
	private String ipdregtype="";
	
	
	
	
	public String getSTime() {
		return sTime;
	}

	public void setSTime(String time) {
		sTime = time;
	}

	public String getIpdregtype() {
		return ipdregtype;
	}

	public void setIpdregtype(String ipdregtype) {
		this.ipdregtype = ipdregtype;
	}

	public boolean isIOS() {
		return iOS;
	}

	public void setIOS(boolean ios) {
		iOS = ios;
	}

	public String getIpdregcharge() {
		return ipdregcharge;
	}

	public void setIpdregcharge(String ipdregcharge) {
		this.ipdregcharge = ipdregcharge;
	}

	public boolean isCtp() {
		return ctp;
	}

	public void setCtp(boolean ctp) {
		this.ctp = ctp;
	}

	public boolean isCself() {
		return cself;
	}

	public void setCself(boolean cself) {
		this.cself = cself;
	}

	public String getAdvancerenge() {
		return advancerenge;
	}

	public void setAdvancerenge(String advancerenge) {
		this.advancerenge = advancerenge;
	}

	public String getAdvanceTime() {
		return advanceTime;
	}

	public void setAdvanceTime(String advanceTime) {
		this.advanceTime = advanceTime;
	}

	public ArrayList<String> getAdvanceTimeList() {
		return advanceTimeList;
	}

	public void setAdvanceTimeList(ArrayList<String> advanceTimeList) {
		this.advanceTimeList = advanceTimeList;
	}

	public String getExcess() {
		return excess;
	}

	public void setExcess(String excess) {
		this.excess = excess;
	}

	public ArrayList<Master> getCountrycodeList() {
		return countrycodeList;
	}

	public void setCountrycodeList(ArrayList<Master> countrycodeList) {
		this.countrycodeList = countrycodeList;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public ArrayList<String> getStartTimeList() {
		return startTimeList;
	}

	public void setStartTimeList(ArrayList<String> startTimeList) {
		this.startTimeList = startTimeList;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public ArrayList<String> getEndTimeList() {
		return endTimeList;
	}

	public void setEndTimeList(ArrayList<String> endTimeList) {
		this.endTimeList = endTimeList;
	}

	public String getRefLocation() {
		return refLocation;
	}

	public void setRefLocation(String refLocation) {
		this.refLocation = refLocation;
	}

	public String getUpdateSaveLocation() {
		return updateSaveLocation;
	}

	public void setUpdateSaveLocation(String updateSaveLocation) {
		this.updateSaveLocation = updateSaveLocation;
	}

	public String getSelectedUserid() {
		return selectedUserid;
	}

	public void setSelectedUserid(String selectedUserid) {
		this.selectedUserid = selectedUserid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCheckMailId() {
		return checkMailId;
	}

	public void setCheckMailId(int checkMailId) {
		this.checkMailId = checkMailId;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getLoc_direction() {
		return loc_direction;
	}

	public void setLoc_direction(String loc_direction) {
		this.loc_direction = loc_direction;
	}

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	ArrayList<Clinic>clinicList;
	ArrayList<Clinic>cliniclocationList;
	ArrayList<Clinic>colorList;
	public ArrayList<Clinic> getColorList() {
		return colorList;
	}

	public void setColorList(ArrayList<Clinic> colorList) {
		this.colorList = colorList;
	}

	public ArrayList<Clinic> getCliniclocationList() {
		return cliniclocationList;
	}

	public void setCliniclocationList(ArrayList<Clinic> cliniclocationList) {
		this.cliniclocationList = cliniclocationList;
	}

	public ArrayList<Clinic> getClinicList() {
		return clinicList;
	}

	public void setClinicList(
			ArrayList<Clinic> clinicList) {
		this.clinicList = clinicList;
	}
	
	
	private int id;
	
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


	private String uuserType;
	
	private String firstname;
	
	private String lastname;
	
	private String userId;
	
	private String password;
	
	private String confirmPassword;
	
	private String[] pkgsubscription; 
	
	private String[] funSubscription;
	
	private boolean isactive;
	
	private boolean standalone;
	
	private boolean hostedDB;
	
	private boolean onlineSingleDevice;
	
	private boolean onlineMultiDevice;
	
	private boolean DiaryManagement;
	
	private boolean AppointmentBooking;
	
	private boolean BasicFinance;
	
	private boolean fullFinance;
	
	private boolean medicalRecord;
	
	private boolean clinicResourceMngment;
	
	private boolean clinicPayrollMngment;
	
	private boolean communication;
	
	private boolean report;
	
	private boolean assessmentForms;
	
	private boolean desktop;
		
	private boolean iOS;
	
	private boolean mobile;
	
	private boolean tablet;
	
	public boolean isCommunication() {
		return communication;
	}

	public void setCommunication(boolean communication) {
		this.communication = communication;
	}

	public boolean isReport() {
		return report;
	}

	public void setReport(boolean report) {
		this.report = report;
	}

	public boolean isAssessmentForms() {
		return assessmentForms;
	}

	public void setAssessmentForms(boolean assessmentForms) {
		this.assessmentForms = assessmentForms;
	}

	public boolean isDesktop() {
		return desktop;
	}

	public void setDesktop(boolean desktop) {
		this.desktop = desktop;
	}



	public boolean isiOS() {
		return iOS;
	}

	public void setiOS(boolean iOS) {
		this.iOS = iOS;
	}

	

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public boolean isTablet() {
		return tablet;
	}

	public void setTablet(boolean tablet) {
		this.tablet = tablet;
	}

	ArrayList<String> departmentList;

	private String searchText = "";
	
	private String clinicName;
	
	private String clinicOwner;
	
	private String email;
	
	private String mobileNo;
	
	private String landLine;
	
	private String country;
	
	private String city;
	
	private String address;
	
	private String pinCode;
	
	private int locationid;
	
	private String colorName;
	
	private String locationname;
	private String contactNo;
	
	private String checkLocation;
	
	private String addressType;
	
	private String emailId;
	
	
	public String getAddressType() {
		return addressType;
	}


	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getCheckLocation() {
		return checkLocation;
	}


	public void setCheckLocation(String checkLocation) {
		this.checkLocation = checkLocation;
	}
	
	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	private Collection<Location> location;
	
	
	public String[] getFunSubscription() {
		return funSubscription;
	}

	public void setFunSubscription(String[] funSubscription) {
		this.funSubscription = funSubscription;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String[] getPkgsubscription() {
		return pkgsubscription;
	}

	public void setPkgsubscription(String[] pkgsubscription) {
		this.pkgsubscription = pkgsubscription;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Collection<Location> getLocation() {
		return location;
	}

	public void setLocation(Collection<Location> location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public boolean isStandalone() {
		return standalone;
	}

	public void setStandalone(boolean standalone) {
		this.standalone = standalone;
	}

	public boolean isHostedDB() {
		return hostedDB;
	}

	public void setHostedDB(boolean hostedDB) {
		this.hostedDB = hostedDB;
	}

	public boolean isOnlineSingleDevice() {
		return onlineSingleDevice;
	}

	public void setOnlineSingleDevice(boolean onlineSingleDevice) {
		this.onlineSingleDevice = onlineSingleDevice;
	}

	public boolean isOnlineMultiDevice() {
		return onlineMultiDevice;
	}

	public void setOnlineMultiDevice(boolean onlineMultiDevice) {
		this.onlineMultiDevice = onlineMultiDevice;
	}

	public boolean isDiaryManagement() {
		return DiaryManagement;
	}

	public void setDiaryManagement(boolean diaryManagement) {
		DiaryManagement = diaryManagement;
	}

	public boolean isAppointmentBooking() {
		return AppointmentBooking;
	}

	public void setAppointmentBooking(boolean appointmentBooking) {
		AppointmentBooking = appointmentBooking;
	}

	public boolean isBasicFinance() {
		return BasicFinance;
	}

	public void setBasicFinance(boolean basicFinance) {
		BasicFinance = basicFinance;
	}

	public boolean isFullFinance() {
		return fullFinance;
	}

	public void setFullFinance(boolean fullFinance) {
		this.fullFinance = fullFinance;
	}

	public boolean isMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(boolean medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public boolean isClinicResourceMngment() {
		return clinicResourceMngment;
	}

	public void setClinicResourceMngment(boolean clinicResourceMngment) {
		this.clinicResourceMngment = clinicResourceMngment;
	}

	public boolean isClinicPayrollMngment() {
		return clinicPayrollMngment;
	}

	public void setClinicPayrollMngment(boolean clinicPayrollMngment) {
		this.clinicPayrollMngment = clinicPayrollMngment;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getUuserType() {
		return uuserType;
	}

	public void setUuserType(String uuserType) {
		this.uuserType = uuserType;
	}


	/*private String emailConfigureId;
	
	private String emailUserName;
	
	private String emailPassword;
	
	private String emailConfirmPassword;
	
	private String emailHostName;
	
	public String getEmailConfigureId() {
		return emailConfigureId;
	}

	public void setEmailConfigureId(String emailConfigureId) {
		this.emailConfigureId = emailConfigureId;
	}

	public String getEmailUserName() {
		return emailUserName;
	}

	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailConfirmPassword() {
		return emailConfirmPassword;
	}

	public void setEmailConfirmPassword(String emailConfirmPassword) {
		this.emailConfirmPassword = emailConfirmPassword;
	}

	public String getEmailHostName() {
		return emailHostName;
	}

	public void setEmailHostName(String emailHostName) {
		this.emailHostName = emailHostName;
	}
*/

	
	private int dnaCharges;
	
	private ArrayList<Clinic> dnaChargeList;

	public ArrayList<Clinic> getDnaChargeList() {
		return dnaChargeList;
	}

	public void setDnaChargeList(ArrayList<Clinic> dnaChargeList) {
		this.dnaChargeList = dnaChargeList;
	}

	public int getDnaCharges() {
		return dnaCharges;
	}

	public void setDnaCharges(int dnaCharges) {
		this.dnaCharges = dnaCharges;
	}

	public int getSmscount() {
		return smscount;
	}

	public void setSmscount(int smscount) {
		this.smscount = smscount;
	}

	public boolean isSmscheck() {
		return smscheck;
	}

	public void setSmscheck(boolean smscheck) {
		this.smscheck = smscheck;
	}

	public boolean isSmsPayment() {
		return smsPayment;
	}

	public void setSmsPayment(boolean smsPayment) {
		this.smsPayment = smsPayment;
	}

	public String getSetupstdcharge() {
		return setupstdcharge;
	}

	public void setSetupstdcharge(String setupstdcharge) {
		this.setupstdcharge = setupstdcharge;
	}

	public ArrayList<String> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(ArrayList<String> departmentList) {
		this.departmentList = departmentList;
	}

	public ArrayList<Master> getSpecilizationList() {
		return specilizationList;
	}

	public void setSpecilizationList(ArrayList<Master> specilizationList) {
		this.specilizationList = specilizationList;
	}

	public ArrayList<Master> getIpdformfieldList() {
		return ipdformfieldList;
	}

	public void setIpdformfieldList(ArrayList<Master> ipdformfieldList) {
		this.ipdformfieldList = ipdformfieldList;
	}

	public String getWithpayment() {
		return withpayment;
	}

	public void setWithpayment(String withpayment) {
		this.withpayment = withpayment;
	}

	public String getWithoutpayment() {
		return withoutpayment;
	}

	public void setWithoutpayment(String withoutpayment) {
		this.withoutpayment = withoutpayment;
	}

	public ArrayList<Clinic> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(ArrayList<Clinic> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public ArrayList<Clinic> getJobtitlelist() {
		return jobtitlelist;
	}

	public void setJobtitlelist(ArrayList<Clinic> jobtitlelist) {
		this.jobtitlelist = jobtitlelist;
	}

	public ArrayList<Clinic> getAccessmodulelist() {
		return accessmodulelist;
	}

	public void setAccessmodulelist(ArrayList<Clinic> accessmodulelist) {
		this.accessmodulelist = accessmodulelist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}

public String getInvestigation_show() {
		return investigation_show;
	}

	public void setInvestigation_show(String investigation_show) {
		this.investigation_show = investigation_show;
	}


public boolean isImmusms() {
		return immusms;
	}

	public void setImmusms(boolean immusms) {
		this.immusms = immusms;
	}


public boolean isBdaysms() {
		return bdaysms;
	}

	public void setBdaysms(boolean bdaysms) {
		this.bdaysms = bdaysms;
	}


public boolean isSmsVisitingConslt() {
		return smsVisitingConslt;
	}

	public void setSmsVisitingConslt(boolean smsVisitingConslt) {
		this.smsVisitingConslt = smsVisitingConslt;
	}


public boolean isShow_wardname() {
		return show_wardname;
	}

	public void setShow_wardname(boolean show_wardname) {
		this.show_wardname = show_wardname;
	}


public boolean isShow_unpost() {
		return show_unpost;
	}

	public void setShow_unpost(boolean show_unpost) {
		this.show_unpost = show_unpost;
	}


public boolean isSms_on_newadm() {
		return sms_on_newadm;
	}

	public void setSms_on_newadm(boolean sms_on_newadm) {
		this.sms_on_newadm = sms_on_newadm;
	}

	public boolean isSms_on_bedchange() {
		return sms_on_bedchange;
	}

	public void setSms_on_bedchange(boolean sms_on_bedchange) {
		this.sms_on_bedchange = sms_on_bedchange;
	}


public boolean isShow_new_ipd_id() {
		return show_new_ipd_id;
	}

	public void setShow_new_ipd_id(boolean show_new_ipd_id) {
		this.show_new_ipd_id = show_new_ipd_id;
	}


public boolean isHidelettinvst() {
		return hidelettinvst;
	}

	public void setHidelettinvst(boolean hidelettinvst) {
		this.hidelettinvst = hidelettinvst;
	}

	public boolean isHidelettemr() {
		return hidelettemr;
	}

	public void setHidelettemr(boolean hidelettemr) {
		this.hidelettemr = hidelettemr;
	}

	public boolean isHidelettbillinv() {
		return hidelettbillinv;
	}

	public void setHidelettbillinv(boolean hidelettbillinv) {
		this.hidelettbillinv = hidelettbillinv;
	}


public String getLoginexmsg() {
		return loginexmsg;
	}

	public void setLoginexmsg(String loginexmsg) {
		this.loginexmsg = loginexmsg;
	}


public String getOpdfollowup() {
		return opdfollowup;
	}

	public void setOpdfollowup(String opdfollowup) {
		this.opdfollowup = opdfollowup;
	}


public boolean isOpd_payamnt_sms() {
		return opd_payamnt_sms;
	}

	public void setOpd_payamnt_sms(boolean opd_payamnt_sms) {
		this.opd_payamnt_sms = opd_payamnt_sms;
	}


public boolean isIpd_payment_sms() {
		return ipd_payment_sms;
	}

	public void setIpd_payment_sms(boolean ipd_payment_sms) {
		this.ipd_payment_sms = ipd_payment_sms;
	}


public boolean isSms_cancel_appointment() {
		return sms_cancel_appointment;
	}

	public void setSms_cancel_appointment(boolean sms_cancel_appointment) {
		this.sms_cancel_appointment = sms_cancel_appointment;
	}


public String getRegional_lang() {
		return regional_lang;
	}

	public void setRegional_lang(String regional_lang) {
		this.regional_lang = regional_lang;
	}


private String investigation_show;
private boolean bdaysms;
private boolean immusms;
private boolean smsVisitingConslt;
private boolean show_wardname;
private boolean show_unpost;
private boolean sms_on_newadm;
private boolean sms_on_bedchange;
private boolean show_new_ipd_id;
private boolean hidelettinvst;
private boolean hidelettemr;
private boolean hidelettbillinv;
private String loginexmsg;
}
