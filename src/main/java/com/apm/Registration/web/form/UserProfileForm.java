package com.apm.Registration.web.form;

import java.io.File;
import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Currency;
import com.apm.Registration.eu.entity.UserProfile;

public class UserProfileForm {

public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}


private boolean add_medicine;
	
	ArrayList<UserProfile>userProfileList;
	private int userwiseaceess;
	private boolean token_display;
	private String city;
	private String hospitalname;
	public String getClinic_userid() {
		return clinic_userid;
	}

	public void setClinic_userid(String clinic_userid) {
		this.clinic_userid = clinic_userid;
	}


	private String clinic_userid;
	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getUserwiseaceess() {
		return userwiseaceess;
	}

	public void setUserwiseaceess(int userwiseaceess) {
		this.userwiseaceess = userwiseaceess;
	}

	public ArrayList<UserProfile> getUserProfileList() {
		return userProfileList;
	}

	public void setUserProfileList(
			ArrayList<UserProfile> userProfileList) {
		this.userProfileList = userProfileList;
	}

	
	private int id;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private String instruction1;
	private String instruction2;
	private String instruction3;
	private String instruction4;
	private String jobtitless;
	private String empid;

	private int userType;
	
	private int changefre;

	private String userId;
	
	private String password;
	
	private boolean usercheck;
	
	private boolean appointmentdiary;
	
	private int compressionrate;
	
	private boolean isavailableonline;
	
	private boolean loginsystem;
	
	private boolean apmremote;
	
	private boolean onlinebooking;

	private String address;
	
	private boolean hasDiary;
	private boolean secondary_doc;
	
	private String selectedClinicid;
	
	private String selectedUserid;
	
	ArrayList<Master>disciplineList;
	
	private String practitonerType;
	private boolean visitingdoctor;
	
	ArrayList<Master>practitonerTypeList;
	
	private String chargeType;
	
	private ArrayList<Currency>chargeSignList;
	
	private ArrayList<Master>countrycodeList;
	
	private String countrycode;
	
	ArrayList<Master>jobGroupList;
	
	ArrayList<Master>doctorList;
	
	private String doctor;
	
	private String jobgroup;
	
	private String jobtitles;
	private String genptn;
	private String tpaptn;
	private String wcl;
	private String isro;
	private String ntpc;
	
	private int ipdCharge;
	private int surgeonCharge;
	
	ArrayList<Master>wardList;
	private boolean ot;
	private boolean casualty;
	private boolean pharmacy;
	private boolean mrd;
	private boolean marketing;
	private boolean voice_recording;
	private boolean packs;
	 private boolean investigation_chart;
	 private boolean sheduler;
	 private boolean housekeeping;
	 private boolean dietery;
	 private boolean cafeteria;
	 private boolean packages;
	 private boolean ambulance;
	 private boolean bank_deposite;
	 private boolean account_reconcilation; 
	 private boolean opdid;
		private boolean ipdid;
		private boolean emr;
		private boolean pacs;
		private boolean discharge;
		private boolean medicine;
		private boolean investigation;
		private boolean bloodbank;
		private boolean accounts;
		private boolean payroll;
		private boolean expenses;
		private boolean inventory;
		private boolean mis;
		private boolean consultants;
		private boolean patient;
		private boolean appointmentfinder;
		private boolean setting;
		private ArrayList<Clinic> opdlist;
		private ArrayList<Clinic> ipdlist;
		private ArrayList<Clinic> accountlist;
		private ArrayList<Clinic> emrlist;
		private ArrayList<Clinic> clientlist;
		private boolean indent;
		private ArrayList<Clinic> investigationrolelist;
		private boolean daily_opd;
		private boolean indent_approve;
		private boolean cash_desk;
		private ArrayList<Clinic> indentrolelist;
		private String locationname;
		private String location;
		private boolean refund_dashboard;
		private boolean edit_paypo;
		private boolean adjustmentaccess;
		private boolean supplier_add;
		private boolean direct_refund_disc;
		private boolean ref_dis_pay;
		private boolean prisc_new_req_access;
		private boolean additional_disc;
		private boolean cancel_invoice;
		private boolean setup_master;
		private String commercial;
		private String Consultatantshare;
		ArrayList<Master>clinicuseridlist;
		ArrayList<Master>clinicstafflist;
		private String Clinic_name;;
		private String resetpassword;
		private String userImageFileName;
		private String userImageContentType;
		private File userPhoto;
		private File userImage;
		private String profileimg="";
		
		public String getProfileimg() {
			return profileimg;
		}

		public void setProfileimg(String profileimg) {
			this.profileimg = profileimg;
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


		public File getUserPhoto() {
			return userPhoto;
		}

		public void setUserPhoto(File userPhoto) {
			this.userPhoto = userPhoto;
		}

		public String getResetpassword() {
			return resetpassword;
		}

		public void setResetpassword(String resetpassword) {
			this.resetpassword = resetpassword;
		}


		public String getClinic_name() {
			return Clinic_name;
		}

		public void setClinic_name(String clinic_name) {
			Clinic_name = clinic_name;
		}

		public ArrayList<Master> getClinicstafflist() {
			return clinicstafflist;
		}

		public void setClinicstafflist(ArrayList<Master> clinicstafflist) {
			this.clinicstafflist = clinicstafflist;
		}

		public ArrayList<Master> getClinicuseridlist() {
			return clinicuseridlist;
		}

		public void setClinicuseridlist(ArrayList<Master> clinicuseridlist) {
			this.clinicuseridlist = clinicuseridlist;
		}

		public String getCommercial() {
			return commercial;
		}

		public void setCommercial(String commercial) {
			this.commercial = commercial;
		}

		public String getConsultatantshare() {
			return Consultatantshare;
		}

		public void setConsultatantshare(String consultatantshare) {
			Consultatantshare = consultatantshare;
		}

		public boolean isSetup_master() {
			return setup_master;
		}

		public void setSetup_master(boolean setup_master) {
			this.setup_master = setup_master;
		}

		public boolean isCancel_invoice() {
			return cancel_invoice;
		}

		public void setCancel_invoice(boolean cancel_invoice) {
			this.cancel_invoice = cancel_invoice;
		}

		public boolean isAdditional_disc() {
			return additional_disc;
		}

		public void setAdditional_disc(boolean additional_disc) {
			this.additional_disc = additional_disc;
		}

		public boolean isPrisc_new_req_access() {
			return prisc_new_req_access;
		}

		public void setPrisc_new_req_access(boolean prisc_new_req_access) {
			this.prisc_new_req_access = prisc_new_req_access;
		}

		public boolean isRef_dis_pay() {
			return ref_dis_pay;
		}

		public void setRef_dis_pay(boolean ref_dis_pay) {
			this.ref_dis_pay = ref_dis_pay;
		}

		public boolean isDirect_refund_disc() {
			return direct_refund_disc;
		}

		public void setDirect_refund_disc(boolean direct_refund_disc) {
			this.direct_refund_disc = direct_refund_disc;
		}

		public boolean isSupplier_add() {
			return supplier_add;
		}

		public void setSupplier_add(boolean supplier_add) {
			this.supplier_add = supplier_add;
		}

		public boolean isAdjustmentaccess() {
			return adjustmentaccess;
		}

		public void setAdjustmentaccess(boolean adjustmentaccess) {
			this.adjustmentaccess = adjustmentaccess;
		}

		public boolean isEdit_paypo() {
			return edit_paypo;
		}

		public void setEdit_paypo(boolean edit_paypo) {
			this.edit_paypo = edit_paypo;
		}
		public boolean isRefund_dashboard() {
			return refund_dashboard;
		}

		public void setRefund_dashboard(boolean refund_dashboard) {
			this.refund_dashboard = refund_dashboard;
		}


		private ArrayList<Master> indentloclist;
		public String getLocationname() {
			return locationname;
		}

		public void setLocationname(String locationname) {
			this.locationname = locationname;
		}

		public ArrayList<Master> getIndentloclist() {
			return indentloclist;
		}

		public void setIndentloclist(ArrayList<Master> indentloclist) {
			this.indentloclist = indentloclist;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public ArrayList<Clinic> getIndentrolelist() {
			return indentrolelist;
		}

		public void setIndentrolelist(ArrayList<Clinic> indentrolelist) {
			this.indentrolelist = indentrolelist;
		}

		public boolean isIndent_approve() {
			return indent_approve;
		}

		public void setIndent_approve(boolean indent_approve) {
			this.indent_approve = indent_approve;
		}

		public boolean isCash_desk() {
			return cash_desk;
		}

		public void setCash_desk(boolean cash_desk) {
			this.cash_desk = cash_desk;
		}

		public boolean isDaily_opd() {
			return daily_opd;
		}

		public void setDaily_opd(boolean daily_opd) {
			this.daily_opd = daily_opd;
		}

		public ArrayList<Clinic> getInvestigationrolelist() {
			return investigationrolelist;
		}

		public void setInvestigationrolelist(ArrayList<Clinic> investigationrolelist) {
			this.investigationrolelist = investigationrolelist;
		}

	public boolean isIndent() {
			return indent;
		}

		public void setIndent(boolean indent) {
			this.indent = indent;
		}

	public ArrayList<Clinic> getOpdlist() {
			return opdlist;
		}

		public void setOpdlist(ArrayList<Clinic> opdlist) {
			this.opdlist = opdlist;
		}

		public ArrayList<Clinic> getIpdlist() {
			return ipdlist;
		}

		public void setIpdlist(ArrayList<Clinic> ipdlist) {
			this.ipdlist = ipdlist;
		}

		public ArrayList<Clinic> getAccountlist() {
			return accountlist;
		}

		public void setAccountlist(ArrayList<Clinic> accountlist) {
			this.accountlist = accountlist;
		}

		public ArrayList<Clinic> getEmrlist() {
			return emrlist;
		}

		public void setEmrlist(ArrayList<Clinic> emrlist) {
			this.emrlist = emrlist;
		}

		public ArrayList<Clinic> getClientlist() {
			return clientlist;
		}

		public void setClientlist(ArrayList<Clinic> clientlist) {
			this.clientlist = clientlist;
		}

	public boolean isOt() {
			return ot;
		}

		public void setOt(boolean ot) {
			this.ot = ot;
		}

		public boolean isCasualty() {
			return casualty;
		}

		public void setCasualty(boolean casualty) {
			this.casualty = casualty;
		}

		public boolean isPharmacy() {
			return pharmacy;
		}

		public void setPharmacy(boolean pharmacy) {
			this.pharmacy = pharmacy;
		}

		public boolean isMrd() {
			return mrd;
		}

		public void setMrd(boolean mrd) {
			this.mrd = mrd;
		}

		public boolean isMarketing() {
			return marketing;
		}

		public void setMarketing(boolean marketing) {
			this.marketing = marketing;
		}

		public boolean isVoice_recording() {
			return voice_recording;
		}

		public void setVoice_recording(boolean voice_recording) {
			this.voice_recording = voice_recording;
		}

		public boolean isPacks() {
			return packs;
		}

		public void setPacks(boolean packs) {
			this.packs = packs;
		}

		public boolean isInvestigation_chart() {
			return investigation_chart;
		}

		public void setInvestigation_chart(boolean investigation_chart) {
			this.investigation_chart = investigation_chart;
		}

		public boolean isSheduler() {
			return sheduler;
		}

		public void setSheduler(boolean sheduler) {
			this.sheduler = sheduler;
		}

		public boolean isHousekeeping() {
			return housekeeping;
		}

		public void setHousekeeping(boolean housekeeping) {
			this.housekeeping = housekeeping;
		}

		public boolean isDietery() {
			return dietery;
		}

		public void setDietery(boolean dietery) {
			this.dietery = dietery;
		}

		public boolean isCafeteria() {
			return cafeteria;
		}

		public void setCafeteria(boolean cafeteria) {
			this.cafeteria = cafeteria;
		}

		public boolean isPackages() {
			return packages;
		}

		public void setPackages(boolean packages) {
			this.packages = packages;
		}

		public boolean isAmbulance() {
			return ambulance;
		}

		public void setAmbulance(boolean ambulance) {
			this.ambulance = ambulance;
		}

		public boolean isBank_deposite() {
			return bank_deposite;
		}

		public void setBank_deposite(boolean bank_deposite) {
			this.bank_deposite = bank_deposite;
		}

		public boolean isAccount_reconcilation() {
			return account_reconcilation;
		}

		public void setAccount_reconcilation(boolean account_reconcilation) {
			this.account_reconcilation = account_reconcilation;
		}

		public boolean isOpdid() {
			return opdid;
		}

		public void setOpdid(boolean opdid) {
			this.opdid = opdid;
		}

		public boolean isIpdid() {
			return ipdid;
		}

		public void setIpdid(boolean ipdid) {
			this.ipdid = ipdid;
		}

		public boolean isEmr() {
			return emr;
		}

		public void setEmr(boolean emr) {
			this.emr = emr;
		}

		public boolean isPacs() {
			return pacs;
		}

		public void setPacs(boolean pacs) {
			this.pacs = pacs;
		}

		public boolean isDischarge() {
			return discharge;
		}

		public void setDischarge(boolean discharge) {
			this.discharge = discharge;
		}

		public boolean isMedicine() {
			return medicine;
		}

		public void setMedicine(boolean medicine) {
			this.medicine = medicine;
		}

		public boolean isInvestigation() {
			return investigation;
		}

		public void setInvestigation(boolean investigation) {
			this.investigation = investigation;
		}

		public boolean isBloodbank() {
			return bloodbank;
		}

		public void setBloodbank(boolean bloodbank) {
			this.bloodbank = bloodbank;
		}

		public boolean isAccounts() {
			return accounts;
		}

		public void setAccounts(boolean accounts) {
			this.accounts = accounts;
		}

		public boolean isPayroll() {
			return payroll;
		}

		public void setPayroll(boolean payroll) {
			this.payroll = payroll;
		}

		public boolean isExpenses() {
			return expenses;
		}

		public void setExpenses(boolean expenses) {
			this.expenses = expenses;
		}

		public boolean isInventory() {
			return inventory;
		}

		public void setInventory(boolean inventory) {
			this.inventory = inventory;
		}

		public boolean isMis() {
			return mis;
		}

		public void setMis(boolean mis) {
			this.mis = mis;
		}

		public boolean isConsultants() {
			return consultants;
		}

		public void setConsultants(boolean consultants) {
			this.consultants = consultants;
		}

		public boolean isPatient() {
			return patient;
		}

		public void setPatient(boolean patient) {
			this.patient = patient;
		}

		public boolean isAppointmentfinder() {
			return appointmentfinder;
		}

		public void setAppointmentfinder(boolean appointmentfinder) {
			this.appointmentfinder = appointmentfinder;
		}

		public boolean isSetting() {
			return setting;
		}

		public void setSetting(boolean setting) {
			this.setting = setting;
		}

	public ArrayList<Master> getWardList() {
		return wardList;
	}

	public void setWardList(ArrayList<Master> wardList) {
		this.wardList = wardList;
	}

	public int getIpdCharge() {
		return ipdCharge;
	}

	public void setIpdCharge(int ipdCharge) {
		this.ipdCharge = ipdCharge;
	}

	public int getSurgeonCharge() {
		return surgeonCharge;
	}

	public void setSurgeonCharge(int surgeonCharge) {
		this.surgeonCharge = surgeonCharge;
	}

	public String getJobtitles() {
		return jobtitles;
	}

	public void setJobtitles(String jobtitles) {
		this.jobtitles = jobtitles;
	}

	public String getJobgroup() {
		return jobgroup;
	}

	public void setJobgroup(String jobgroup) {
		this.jobgroup = jobgroup;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public ArrayList<Master> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(ArrayList<Master> doctorList) {
		this.doctorList = doctorList;
	}

	public ArrayList<Master> getJobGroupList() {
		return jobGroupList;
	}

	public void setJobGroupList(ArrayList<Master> jobGroupList) {
		this.jobGroupList = jobGroupList;
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

	public ArrayList<Currency> getChargeSignList() {
		return chargeSignList;
	}

	public void setChargeSignList(ArrayList<Currency> chargeSignList) {
		this.chargeSignList = chargeSignList;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getPractitonerType() {
		return practitonerType;
	}

	public void setPractitonerType(String practitonerType) {
		this.practitonerType = practitonerType;
	}

	public ArrayList<Master> getPractitonerTypeList() {
		return practitonerTypeList;
	}

	public void setPractitonerTypeList(ArrayList<Master> practitonerTypeList) {
		this.practitonerTypeList = practitonerTypeList;
	}

	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public String getSelectedClinicid() {
		return selectedClinicid;
	}

	public void setSelectedClinicid(String selectedClinicid) {
		this.selectedClinicid = selectedClinicid;
	}

	public String getSelectedUserid() {
		return selectedUserid;
	}

	public void setSelectedUserid(String selectedUserid) {
		this.selectedUserid = selectedUserid;
	}

	public boolean isHasDiary() {
		return hasDiary;
	}

	public void setHasDiary(boolean hasDiary) {
		this.hasDiary = hasDiary;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private ArrayList<String> initialList;
	private ArrayList<String> jobTitleList;
	private ArrayList<Master> jobTitleLists;
	
	private String pagerecords;
	private int totalRecords;
	
	private String otherJobTitle;
	
	private ArrayList<UserProfile> allUserList;
	
	public ArrayList<UserProfile> getAllUserList() {
		return allUserList;
	}

	public void setAllUserList(ArrayList<UserProfile> allUserList) {
		this.allUserList = allUserList;
	}

	public String getOtherJobTitle() {
		return otherJobTitle;
	}

	public void setOtherJobTitle(String otherJobTitle) {
		this.otherJobTitle = otherJobTitle;
	}

	public ArrayList<String> getInitialList() {
		return initialList;
	}

	public void setInitialList(ArrayList<String> initialList) {
		this.initialList = initialList;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}


		
		
	public int getChangefre() {
		return changefre;
	}

	public void setChangefre(int changefre) {
		this.changefre = changefre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoginsystem() {
		return loginsystem;
	}

	public void setLoginsystem(boolean loginsystem) {
		this.loginsystem = loginsystem;
	}

	public boolean isApmremote() {
		return apmremote;
	}

	public void setApmremote(boolean apmremote) {
		this.apmremote = apmremote;
	}

	public boolean isOnlinebooking() {
		return onlinebooking;
	}

	public void setOnlinebooking(boolean onlinebooking) {
		this.onlinebooking = onlinebooking;
	}

	public String getLastchanged() {
		return lastchanged;
	}

	public void setLastchanged(String lastchanged) {
		this.lastchanged = lastchanged;
	}

	public String getAppointmentbookingcontem() {
		return appointmentbookingcontem;
	}

	public void setAppointmentbookingcontem(String appointmentbookingcontem) {
		this.appointmentbookingcontem = appointmentbookingcontem;
	}

	public String getAppointmentbookingdnatem() {
		return appointmentbookingdnatem;
	}

	public void setAppointmentbookingdnatem(String appointmentbookingdnatem) {
		this.appointmentbookingdnatem = appointmentbookingdnatem;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	private String lastchanged;
	
	private String fullname="";
	
	private String firstname="";
	
	private String lastname="";
	
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


	private String diarycolor;
	
	private String diarycolumnposition;
	
	private String initial;
	
	private String jobtitle;
	
	private String discription;
	
	private String appointmentbookingcontem;
	
	private String appointmentbookingdnatem;
	
	private String email;
	
	private String mobile;
	
	private String registerno;
	
	private String usergroup;	
	
	private String onlinename;
	
	private String diciplineName;
	
	private ArrayList<String>diciplineList;
	
	private ArrayList<String>diarycolorList;
	
	private ArrayList<String>abctList;
	
	private String qualification;
	ArrayList<Master>labnameList;
	private String labname;
	
	public ArrayList<Master> getLabnameList() {
		return labnameList;
	}

	public void setLabnameList(ArrayList<Master> labnameList) {
		this.labnameList = labnameList;
	}

	public String getLabname() {
		return labname;
	}

	public void setLabname(String labname) {
		this.labname = labname;
	}


	private String wardid;
	
	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public ArrayList<String> getDiarycolorList() {
		return diarycolorList;
	}

	public void setDiarycolorList(ArrayList<String> diarycolorList) {
		this.diarycolorList = diarycolorList;
	}


	private ArrayList<String>adtList;
	
	private String searchText = "";
		
	
	public String getSearchText() {
		return searchText;
	}

	public ArrayList<String> getAbctList() {
		return abctList;
	}

	public void setAbctList(ArrayList<String> abctList) {
		this.abctList = abctList;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public ArrayList<String> getDiciplineList() {
		return diciplineList;
	}

	public void setDiciplineList(ArrayList<String> diciplineList) {
		this.diciplineList = diciplineList;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

	public boolean isAppointmentdiary() {
		return appointmentdiary;
	}

	public void setAppointmentdiary(boolean appointmentdiary) {
		this.appointmentdiary = appointmentdiary;
	}


	
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isUsercheck() {
		return usercheck;
	}

	public void setUsercheck(boolean usercheck) {
		this.usercheck = usercheck;
	}

	public int getCompressionrate()
	{
		return compressionrate;
	}
	
	public void setCompressionrate(int compressionrate)
	{
		this.compressionrate=compressionrate;		
	}
	
		
	public boolean isIsavailableonline() {
		return isavailableonline;
	}

	public void setIsavailableonline(boolean isavailableonline) {
		this.isavailableonline = isavailableonline;
	}

	public 	String getFullname()
	{
		return fullname;
	}
	
	public void setFullname(String fullname)
	{
		this.fullname=fullname;		
	}
	
	
	public 	String getDiarycolor()
	{
		return diarycolor;
	}
	
	public void setDiarycolor(String diarycolor)
	{
		this.diarycolor=diarycolor;		
	}
	
	public String getDiarycolumnposition()
	{
		return diarycolumnposition;
	}
	
	public void setDiarycolumnposition(String diarycolumnposition)
	{
		this.diarycolumnposition=diarycolumnposition;		
	}
	
	public String getInitial()
	{
		return initial;
	}
	
	public void setInitial(String initial)
	{
		this.initial=initial;
	}
	
	public String getJobtitle()
	{
		return jobtitle;
	}
	
	public void setJobtitle(String jobtitle)
	{
		this.jobtitle=jobtitle;
	}
	
	public String getDiscription()
	{
		return discription;
	}
	
	public void setDiscription(String discription)
	{
		this.discription=discription;
	}
	
	public String getRegisterno()
	{
		return registerno;
	}
	
	public void setRegisterno(String registerno)
	{
		this.registerno=registerno;
	}
	
	public String getUsergroup()
	{
		return usergroup;
	}
	
	public void setUsergroup(String usergroup)
	{
		this.usergroup=usergroup;
	}
	
	public String getOnlinename() {
		return onlinename;
	}

	public void setOnlinename(String onlinename) {
		this.onlinename = onlinename;
	}

	public String getDiciplineName() {
		return diciplineName;
	}

	public void setDiciplineName(String diciplineName) {
		this.diciplineName = diciplineName;
	}

	public ArrayList<String> getAdtList() {
		return adtList;
	}

	public void setAdtList(ArrayList<String> adtList) {
		this.adtList = adtList;
	}

	public ArrayList<String> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(ArrayList<String> jobTitleList) {
		this.jobTitleList = jobTitleList;
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
	
	private int dnaCharge;
	
	private int compAppCharge;
	
	public int getDnaCharge() {
		return dnaCharge;
	}

	public void setDnaCharge(int dnaCharge) {
		this.dnaCharge = dnaCharge;
	}

	public int getCompAppCharge() {
		return compAppCharge;
	}

	public void setCompAppCharge(int compAppCharge) {
		this.compAppCharge = compAppCharge;
	}

	public ArrayList<Master> getJobTitleLists() {
		return jobTitleLists;
	}

	public void setJobTitleLists(ArrayList<Master> jobTitleLists) {
		this.jobTitleLists = jobTitleLists;
	}

	public String getGenptn() {
		return genptn;
	}

	public void setGenptn(String genptn) {
		this.genptn = genptn;
	}

	public String getTpaptn() {
		return tpaptn;
	}

	public void setTpaptn(String tpaptn) {
		this.tpaptn = tpaptn;
	}

	public String getWcl() {
		return wcl;
	}

	public void setWcl(String wcl) {
		this.wcl = wcl;
	}

	public String getIsro() {
		return isro;
	}

	public void setIsro(String isro) {
		this.isro = isro;
	}

	public String getNtpc() {
		return ntpc;
	}

	public void setNtpc(String ntpc) {
		this.ntpc = ntpc;
	}

	public boolean isVisitingdoctor() {
		return visitingdoctor;
	}

	public void setVisitingdoctor(boolean visitingdoctor) {
		this.visitingdoctor = visitingdoctor;
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

public ArrayList<Clinic> getMislist() {
		return mislist;
	}

	public void setMislist(ArrayList<Clinic> mislist) {
		this.mislist = mislist;
	}


public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}


public boolean isShow_master() {
		return show_master;
	}

	public void setShow_master(boolean show_master) {
		this.show_master = show_master;
	}


public boolean isToken_display() {
		return token_display;
	}

	public void setToken_display(boolean token_display) {
		this.token_display = token_display;
	}


public boolean isAdd_medicine() {
		return add_medicine;
	}

	public void setAdd_medicine(boolean add_medicine) {
		this.add_medicine = add_medicine;
	}


public boolean isPharm_print_backdate() {
		return pharm_print_backdate;
	}

	public void setPharm_print_backdate(boolean pharm_print_backdate) {
		this.pharm_print_backdate = pharm_print_backdate;
	}


public boolean isStock_log() {
		return stock_log;
	}

	public void setStock_log(boolean stock_log) {
		this.stock_log = stock_log;
	}


public boolean isModify_disc() {
		return modify_disc;
	}

	public void setModify_disc(boolean modify_disc) {
		this.modify_disc = modify_disc;
	}


public boolean isAdd_manual_charge() {
		return add_manual_charge;
	}

	public void setAdd_manual_charge(boolean add_manual_charge) {
		this.add_manual_charge = add_manual_charge;
	}


public boolean isUpdate_investigation_charge() {
		return update_investigation_charge;
	}

	public void setUpdate_investigation_charge(boolean update_investigation_charge) {
		this.update_investigation_charge = update_investigation_charge;
	}


public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}


public String getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(String licenceId) {
		this.licenceId = licenceId;
	}


public UserProfile getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserProfile userDetails) {
		this.userDetails = userDetails;
	}


public String getJobtitless() {
		return jobtitless;
	}

	public void setJobtitless(String jobtitless) {
		this.jobtitless = jobtitless;
	}


public boolean isSecondary_doc() {
		return secondary_doc;
	}

	public void setSecondary_doc(boolean secondary_doc) {
		this.secondary_doc = secondary_doc;
	}


private ArrayList<Clinic> mislist;
private String dob;	
	private boolean show_master;
private boolean	stock_log,pharm_print_backdate;
private boolean modify_disc;
private boolean add_manual_charge;
private boolean update_investigation_charge;
private String licenceId;
private UserProfile userDetails;
private String loginstatus;
ArrayList<UserProfile> loginlist;


public ArrayList<UserProfile> getLoginlist() {
	return loginlist;
}

public void setLoginlist(ArrayList<UserProfile> loginlist) {
	this.loginlist = loginlist;
}

public String getLoginstatus() {
	return loginstatus;
}

public void setLoginstatus(String loginstatus) {
	this.loginstatus = loginstatus;
}

}
