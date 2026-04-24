package com.apm.DiaryManagement.web.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.web.common.Month;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;

public class DiaryManagementForm {
	
	//new terms added for personal widget
	private boolean ot;
	private boolean casualty;
	private boolean pharmacy;
	private boolean mrd;
	private boolean marketing;
	private boolean voice_recording;
	private boolean refund;
	private boolean discount;
	private boolean vaccination;
	private boolean video_training;
	private boolean setup_master;
	private boolean sale_pharmacy;
	private boolean delete_invoice;
	private ArrayList<Master>ClientTypeList;
	private String[] fileUploadFileName;
	private File[] fileUpload;
	public String[] getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String[] fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public File[] getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File[] fileUpload) {
		this.fileUpload = fileUpload;
	}

	public ArrayList<Master> getClientTypeList() {
		return ClientTypeList;
	}

	public void setClientTypeList(ArrayList<Master> clientTypeList) {
		ClientTypeList = clientTypeList;
	}
	public boolean isDelete_invoice() {
		return delete_invoice;
	}

	public void setDelete_invoice(boolean delete_invoice) {
		this.delete_invoice = delete_invoice;
	}

	public boolean isRefund() {
		return refund;
	}

	public void setRefund(boolean refund) {
		this.refund = refund;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public boolean isVaccination() {
		return vaccination;
	}

	public void setVaccination(boolean vaccination) {
		this.vaccination = vaccination;
	}

	public boolean isVideo_training() {
		return video_training;
	}

	public void setVideo_training(boolean video_training) {
		this.video_training = video_training;
	}

	public boolean isSetup_master() {
		return setup_master;
	}

	public void setSetup_master(boolean setup_master) {
		this.setup_master = setup_master;
	}

	public boolean isSale_pharmacy() {
		return sale_pharmacy;
	}

	public void setSale_pharmacy(boolean sale_pharmacy) {
		this.sale_pharmacy = sale_pharmacy;
	}
	ArrayList<Month>monthList;
	
	private ArrayList<Month>monthtdList;
	
	Calendar now = Calendar.getInstance(); 
	
	private String diaryYear = Integer.toString(now.get(Calendar.YEAR));
	
	private String actionType;
	
	private String sTime;
	
	ArrayList<String>startTimeList;
	
	private String endTime;
	
	private ArrayList<String>endTimeList;
	
	private String apmtDuration;
	
	private ArrayList<String> apmtDurationList;
	
	private  String diaryUser;
	
	//diary slot data
	

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
	
	private String commencing;
	
	private String selectedDiaryUser;
	
	private String location;
	
	private String room;
	
	private String description;
	
	private boolean onlineBooking;
	
	private ArrayList<Location>locationList;
	
	private ArrayList<DiaryManagement>userList;
	
	private ArrayList<DiaryManagement>tdUserList;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private String searchText = "";
	
	private boolean fullCalander;
	
	private ArrayList<String>weekNameList;
	
	private String weekName;
	
	private int prevPosition;
	
	private int curPosition;
	
	private String action = "";
	private String mainaction="";
	
	ArrayList<String> jobTitleList;
	private String jobtitle = "";
	ArrayList<Master>wardList;
	
	
	
	private boolean opd;
	private boolean registrationdash;
	private boolean ipd;
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
	
	
	private String fromDate = "";
	private String toDate = "";
	private String order = "desc"; 
	
	private String orderby = "";
	ArrayList<UserProfile>rotaList;
	ArrayList<DiaryManagement>rotaCommencingList;
	private ArrayList<MisReport> smstemlatelist;
	private String type;
	private String filter_ward;
	private String year_filter;
	private String month_filter;
	private String kpiarea_filter;
	private String filter_location;
	private String invoicecategory;
	private long otherPayment;
	private String totalpayment;
	private int totalPayment;
	private int cashpayment;
	private int chequepayment;
	private int cardPayment;
	private long expenseTotal;
	private int totalOpdPatient;
	private String paymodecash;
	private String paymodecheque;
	private int totalPatient;
	ArrayList<Accounts> accountinfo;
	ArrayList<Accounts> accountinfo1;
	ArrayList<Client> patientbylocation;
	private ArrayList<Client>patientbycondition;
	private int requestedprescription;
	private int requestedInvestigation;
	private int chargeaddedd;
	private int invoicegenerated;
	private int paymentrecieved;
	private ArrayList<Accounts> invoiceList;
	private ArrayList<Accounts> thirdPartyList;
	private ArrayList<Client> refrenceList;
	private ArrayList<Master>jobGroupList;
	private ArrayList<Master>disciplineList;
	private ArrayList<Master> investigationamelist;
	private ArrayList<Report> marketinghistorylist;
	private ArrayList<Master>medicineList;
	
	private boolean indent;
	private boolean tpa;
	private boolean nabh_quality;
	private boolean doctor_opd;
	private String emrgency_title,emrgency_data;
	private boolean medicine_barcode;
	private String history_typefilter;
	private String templateName ;
	private String email;
	private String ccEmail;
	private String subject;
	private String body;
	private String Clientid;
	
	public String getClientid() {
		return Clientid;
	}

	public void setClientid(String clientid) {
		Clientid = clientid;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getHistory_typefilter() {
		return history_typefilter;
	}

	public void setHistory_typefilter(String history_typefilter) {
		this.history_typefilter = history_typefilter;
	}

	public String getMainaction() {
		return mainaction;
	}

	public void setMainaction(String mainaction) {
		this.mainaction = mainaction;
	}

	public boolean isMedicine_barcode() {
		return medicine_barcode;
	}

	public void setMedicine_barcode(boolean medicine_barcode) {
		this.medicine_barcode = medicine_barcode;
	}

	public String getEmrgency_title() {
		return emrgency_title;
	}

	public void setEmrgency_title(String emrgency_title) {
		this.emrgency_title = emrgency_title;
	}

	public String getEmrgency_data() {
		return emrgency_data;
	}

	public void setEmrgency_data(String emrgency_data) {
		this.emrgency_data = emrgency_data;
	}

	public boolean isDoctor_opd() {
		return doctor_opd;
	}

	public void setDoctor_opd(boolean doctor_opd) {
		this.doctor_opd = doctor_opd;
	}

	public boolean isTpa() {
		return tpa;
	}

	public void setTpa(boolean tpa) {
		this.tpa = tpa;
	}

	public boolean isNabh_quality() {
		return nabh_quality;
	}

	public void setNabh_quality(boolean nabh_quality) {
		this.nabh_quality = nabh_quality;
	}

	public boolean isIndent() {
		return indent;
	}

	public void setIndent(boolean indent) {
		this.indent = indent;
	}

	public ArrayList<Master> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(ArrayList<Master> medicineList) {
		this.medicineList = medicineList;
	}

	public ArrayList<Report> getMarketinghistorylist() {
		return marketinghistorylist;
	}

	public void setMarketinghistorylist(ArrayList<Report> marketinghistorylist) {
		this.marketinghistorylist = marketinghistorylist;
	}

	public ArrayList<Master> getInvestigationamelist() {
		return investigationamelist;
	}

	public void setInvestigationamelist(ArrayList<Master> investigationamelist) {
		this.investigationamelist = investigationamelist;
	}

	public ArrayList<Master> getJobGroupList() {
		return jobGroupList;
	}

	public void setJobGroupList(ArrayList<Master> jobGroupList) {
		this.jobGroupList = jobGroupList;
	}

	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public ArrayList<Client> getRefrenceList() {
		return refrenceList;
	}

	public void setRefrenceList(ArrayList<Client> refrenceList) {
		this.refrenceList = refrenceList;
	}

	public ArrayList<Accounts> getThirdPartyList() {
		return thirdPartyList;
	}

	public void setThirdPartyList(ArrayList<Accounts> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}

	public ArrayList<Accounts> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Accounts> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public int getChargeaddedd() {
		return chargeaddedd;
	}

	public void setChargeaddedd(int chargeaddedd) {
		this.chargeaddedd = chargeaddedd;
	}

	public int getInvoicegenerated() {
		return invoicegenerated;
	}

	public void setInvoicegenerated(int invoicegenerated) {
		this.invoicegenerated = invoicegenerated;
	}

	public int getPaymentrecieved() {
		return paymentrecieved;
	}

	public void setPaymentrecieved(int paymentrecieved) {
		this.paymentrecieved = paymentrecieved;
	}

	public ArrayList<Client> getPatientbycondition() {
		return patientbycondition;
	}

	public void setPatientbycondition(ArrayList<Client> patientbycondition) {
		this.patientbycondition = patientbycondition;
	}

	public int getRequestedprescription() {
		return requestedprescription;
	}

	public void setRequestedprescription(int requestedprescription) {
		this.requestedprescription = requestedprescription;
	}

	public int getRequestedInvestigation() {
		return requestedInvestigation;
	}

	public void setRequestedInvestigation(int requestedInvestigation) {
		this.requestedInvestigation = requestedInvestigation;
	}

	public ArrayList<Accounts> getPatientviewbypackage() {
		return patientviewbypackage;
	}

	public void setPatientviewbypackage(ArrayList<Accounts> patientviewbypackage) {
		this.patientviewbypackage = patientviewbypackage;
	}
	private ArrayList<Accounts> patientviewbypackage;
	

	public ArrayList<Accounts> getAccountinfo() {
		return accountinfo;
	}

	public void setAccountinfo(ArrayList<Accounts> accountinfo) {
		this.accountinfo = accountinfo;
	}

	public ArrayList<Accounts> getAccountinfo1() {
		return accountinfo1;
	}

	public void setAccountinfo1(ArrayList<Accounts> accountinfo1) {
		this.accountinfo1 = accountinfo1;
	}

	public ArrayList<Client> getPatientbylocation() {
		return patientbylocation;
	}

	public void setPatientbylocation(ArrayList<Client> patientbylocation) {
		this.patientbylocation = patientbylocation;
	}

	public int getTotalPatient() {
		return totalPatient;
	}

	public void setTotalPatient(int totalPatient) {
		this.totalPatient = totalPatient;
	}

	public String getPaymodecash() {
		return paymodecash;
	}

	public void setPaymodecash(String paymodecash) {
		this.paymodecash = paymodecash;
	}

	public String getPaymodecheque() {
		return paymodecheque;
	}

	public void setPaymodecheque(String paymodecheque) {
		this.paymodecheque = paymodecheque;
	}

	public int getTotalOpdPatient() {
		return totalOpdPatient;
	}

	public void setTotalOpdPatient(int totalOpdPatient) {
		this.totalOpdPatient = totalOpdPatient;
	}

	public long getExpenseTotal() {
		return expenseTotal;
	}

	public void setExpenseTotal(long expenseTotal) {
		this.expenseTotal = expenseTotal;
	}

	public long getOtherPayment() {
		return otherPayment;
	}

	public void setOtherPayment(long otherPayment) {
		this.otherPayment = otherPayment;
	}

	public String getTotalpayment() {
		return totalpayment;
	}

	public void setTotalpayment(String totalpayment) {
		this.totalpayment = totalpayment;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	public int getCashpayment() {
		return cashpayment;
	}

	public void setCashpayment(int cashpayment) {
		this.cashpayment = cashpayment;
	}

	public int getChequepayment() {
		return chequepayment;
	}

	public void setChequepayment(int chequepayment) {
		this.chequepayment = chequepayment;
	}

	public int getCardPayment() {
		return cardPayment;
	}

	public void setCardPayment(int cardPayment) {
		this.cardPayment = cardPayment;
	}

	public String getInvoicecategory() {
		return invoicecategory;
	}

	public void setInvoicecategory(String invoicecategory) {
		this.invoicecategory = invoicecategory;
	}

	public String getFilter_location() {
		return filter_location;
	}

	public void setFilter_location(String filter_location) {
		this.filter_location = filter_location;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFilter_ward() {
		return filter_ward;
	}

	public void setFilter_ward(String filter_ward) {
		this.filter_ward = filter_ward;
	}

	public String getYear_filter() {
		return year_filter;
	}

	public void setYear_filter(String year_filter) {
		this.year_filter = year_filter;
	}

	public String getMonth_filter() {
		return month_filter;
	}

	public void setMonth_filter(String month_filter) {
		this.month_filter = month_filter;
	}

	public String getKpiarea_filter() {
		return kpiarea_filter;
	}

	public void setKpiarea_filter(String kpiarea_filter) {
		this.kpiarea_filter = kpiarea_filter;
	}

	public ArrayList<MisReport> getSmstemlatelist() {
		return smstemlatelist;
	}

	public void setSmstemlatelist(ArrayList<MisReport> smstemlatelist) {
		this.smstemlatelist = smstemlatelist;
	}

	public ArrayList<DiaryManagement> getRotaCommencingList() {
		return rotaCommencingList;
	}

	public void setRotaCommencingList(ArrayList<DiaryManagement> rotaCommencingList) {
		this.rotaCommencingList = rotaCommencingList;
	}

	public ArrayList<UserProfile> getRotaList() {
		return rotaList;
	}

	public void setRotaList(ArrayList<UserProfile> rotaList) {
		this.rotaList = rotaList;
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

	public Calendar getNow() {
		return now;
	}

	public void setNow(Calendar now) {
		this.now = now;
	}

	public boolean isOpd() {
		return opd;
	}

	public void setOpd(boolean opd) {
		this.opd = opd;
	}

	public boolean isIpd() {
		return ipd;
	}

	public void setIpd(boolean ipd) {
		this.ipd = ipd;
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

	public ArrayList<String> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(ArrayList<String> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPrevPosition() {
		return prevPosition;
	}

	public void setPrevPosition(int prevPosition) {
		this.prevPosition = prevPosition;
	}

	public int getCurPosition() {
		return curPosition;
	}

	public void setCurPosition(int curPosition) {
		this.curPosition = curPosition;
	}

	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public ArrayList<String> getWeekNameList() {
		return weekNameList;
	}

	public void setWeekNameList(ArrayList<String> weekNameList) {
		this.weekNameList = weekNameList;
	}

	public boolean isFullCalander() {
		return fullCalander;
	}

	public void setFullCalander(boolean fullCalander) {
		this.fullCalander = fullCalander;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
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

	public ArrayList<DiaryManagement> getTdUserList() {
		return tdUserList;
	}

	public void setTdUserList(ArrayList<DiaryManagement> tdUserList) {
		this.tdUserList = tdUserList;
	}

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	public ArrayList<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getSelectedDiaryUser() {
		return selectedDiaryUser;
	}

	public void setSelectedDiaryUser(String selectedDiaryUser) {
		this.selectedDiaryUser = selectedDiaryUser;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOnlineBooking() {
		return onlineBooking;
	}

	public void setOnlineBooking(boolean onlineBooking) {
		this.onlineBooking = onlineBooking;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getApmtDuration() {
		return apmtDuration;
	}

	public void setApmtDuration(String apmtDuration) {
		this.apmtDuration = apmtDuration;
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

	public ArrayList<Month> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<Month> monthList) {
		this.monthList = monthList;
	}

	


	

	public String getSTime() {
		return sTime;
	}

	public void setSTime(String time) {
		sTime = time;
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

	public String getDiaryYear() {
		return diaryYear;
	}

	public void setDiaryYear(String diaryYear) {
		this.diaryYear = diaryYear;
	}

	public ArrayList<Month> getMonthtdList() {
		return monthtdList;
	}

	public void setMonthtdList(ArrayList<Month> monthtdList) {
		this.monthtdList = monthtdList;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public ArrayList<Master> getWardList() {
		return wardList;
	}

	public void setWardList(ArrayList<Master> wardList) {
		this.wardList = wardList;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
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
	private String clinicname;
	private String userid;

		public String getClinicname() {
		return clinicname;
	}

	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

public boolean isCathlab() {
		return cathlab;
	}

	public void setCathlab(boolean cathlab) {
		this.cathlab = cathlab;
	}
public ArrayList<Master> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList<Master> moduleList) {
		this.moduleList = moduleList;
	}
private boolean cathlab;	
private boolean myhr;
private boolean daycare;
private boolean emergency_lbl,token_display;

public boolean isMyhr() {
	return myhr;
}

public void setMyhr(boolean myhr) {
	this.myhr = myhr;
}

public boolean isDaycare() {
	return daycare;
}

public void setDaycare(boolean daycare) {
	this.daycare = daycare;
}

public boolean isEmergency_lbl() {
	return emergency_lbl;
}

public void setEmergency_lbl(boolean emergency_lbl) {
	this.emergency_lbl = emergency_lbl;
}
public boolean isToken_display() {
	return token_display;
}

public void setToken_display(boolean token_display) {
	this.token_display = token_display;
}
public boolean isRegistrationdash() {
	return registrationdash;
}

public void setRegistrationdash(boolean registrationdash) {
	this.registrationdash = registrationdash;
}
private ArrayList<Master> moduleList;
}
