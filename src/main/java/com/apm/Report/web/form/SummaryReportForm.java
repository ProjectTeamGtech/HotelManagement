package com.apm.Report.web.form;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.ThirdParties.eu.entity.ThirdParty;

public class SummaryReportForm {
	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}
	private String allttl;
	private  ArrayList<Master> doctorlist;
	private int id;
private double totalReceived;
private ArrayList<CompleteAppointment> autorefundlist;
private ArrayList<Accounts> ipdRefundList;
private ArrayList<Accounts> opdRefundList;
private ArrayList<Accounts> invoiceList;
private String howpaid;
private String paymentStatus;
private String amountgreaterfilter;
private String debitamttotal;
private String balanceamttotal;
private String search,invoiceid;
private Double totalBillAmt;
private Double totalCash;
private Double totalUpi;
private Double advanceAmt;
private Double totalRefundAmt;
private Double totalPaidAmt;
private Double calcdebittotal;
private ArrayList<NotAvailableSlot> tpNameList;
private ArrayList<Client> chargeTypeList;
private String chargeType;
private String patientcategory;
private Double Chargedisc;
private Double billdisc;
private ArrayList<Accounts>invDetailedrevenue; 


public ArrayList<Accounts> getInvDetailedrevenue() {
	return invDetailedrevenue;
}

public void setInvDetailedrevenue(ArrayList<Accounts> invDetailedrevenue) {
	this.invDetailedrevenue = invDetailedrevenue;
}

public Double getChargedisc() {
	return Chargedisc;
}

public void setChargedisc(Double chargedisc) {
	Chargedisc = chargedisc;
}

public Double getBilldisc() {
	return billdisc;
}

public void setBilldisc(Double billdisc) {
	this.billdisc = billdisc;
}

public String getChargeType() {
	return chargeType;
}

public void setChargeType(String chargeType) {
	this.chargeType = chargeType;
}

public String getPatientcategory() {
	return patientcategory;
}

public void setPatientcategory(String patientcategory) {
	this.patientcategory = patientcategory;
}

public ArrayList<Client> getChargeTypeList() {
	return chargeTypeList;
}

public void setChargeTypeList(ArrayList<Client> chargeTypeList) {
	this.chargeTypeList = chargeTypeList;
}

public ArrayList<NotAvailableSlot> getTpNameList() {
	return tpNameList;
}

public void setTpNameList(ArrayList<NotAvailableSlot> tpNameList) {
	this.tpNameList = tpNameList;
}

public String getAdmissionordischarge() {
	return admissionordischarge;
}

public void setAdmissionordischarge(String admissionordischarge) {
	this.admissionordischarge = admissionordischarge;
}
private String admissionordischarge;

public Double getCalcdebittotal() {
	return calcdebittotal;
}

public void setCalcdebittotal(Double calcdebittotal) {
	this.calcdebittotal = calcdebittotal;
}

public Double getTotalPaidAmt() {
	return totalPaidAmt;
}

public void setTotalPaidAmt(Double totalPaidAmt) {
	this.totalPaidAmt = totalPaidAmt;
}

public Double getTotalRefundAmt() {
	return totalRefundAmt;
}

public void setTotalRefundAmt(Double totalRefundAmt) {
	this.totalRefundAmt = totalRefundAmt;
}

public Double getAdvanceAmt() {
	return advanceAmt;
}

public void setAdvanceAmt(Double advanceAmt) {
	this.advanceAmt = advanceAmt;
}

public Double getTotalCash() {
	return totalCash;
}

public void setTotalCash(Double totalCash) {
	this.totalCash = totalCash;
}

public Double getTotalUpi() {
	return totalUpi;
}

public void setTotalUpi(Double totalUpi) {
	this.totalUpi = totalUpi;
}
public Double getTotalBillAmt() {
	return totalBillAmt;
}

public void setTotalBillAmt(Double totalBillAmt) {
	this.totalBillAmt = totalBillAmt;
}

public Double getTotalShareAmt() {
	return totalShareAmt;
}

public void setTotalShareAmt(Double totalShareAmt) {
	this.totalShareAmt = totalShareAmt;
}
private Double totalShareAmt;

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

	public String getAmountgreaterfilter() {
	return amountgreaterfilter;
}

public void setAmountgreaterfilter(String amountgreaterfilter) {
	this.amountgreaterfilter = amountgreaterfilter;
}

	public String getPaymentStatus() {
	return paymentStatus;
}

public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
}

	public String getHowpaid() {
	return howpaid;
}

public void setHowpaid(String howpaid) {
	this.howpaid = howpaid;
}

	public ArrayList<Accounts> getInvoiceList() {
	return invoiceList;
}

public void setInvoiceList(ArrayList<Accounts> invoiceList) {
	this.invoiceList = invoiceList;
}

	public ArrayList<Accounts> getIpdRefundList() {
	return ipdRefundList;
}

public void setIpdRefundList(ArrayList<Accounts> ipdRefundList) {
	this.ipdRefundList = ipdRefundList;
}

public ArrayList<Accounts> getOpdRefundList() {
	return opdRefundList;
}

public void setOpdRefundList(ArrayList<Accounts> opdRefundList) {
	this.opdRefundList = opdRefundList;
}

	public ArrayList<CompleteAppointment> getAutorefundlist() {
	return autorefundlist;
}

public void setAutorefundlist(ArrayList<CompleteAppointment> autorefundlist) {
	this.autorefundlist = autorefundlist;
}

	public double getTotalReceived() {
	return totalReceived;
}
private String month;
public void setTotalReceived(double totalReceived) {
	this.totalReceived = totalReceived;
}
	
private double totalbillamount=0.0,totalrecammount=0.0,totaldiscountammount=0.0;
public double getTotalbillamount() {
	return totalbillamount;
}

public void setTotalbillamount(double totalbillamount) {
	this.totalbillamount = totalbillamount;
}

public double getTotalrecammount() {
	return totalrecammount;
}

public void setTotalrecammount(double totalrecammount) {
	this.totalrecammount = totalrecammount;
}

public double getTotaldiscountammount() {
	return totaldiscountammount;
}

public void setTotaldiscountammount(double totaldiscountammount) {
	this.totaldiscountammount = totaldiscountammount;
}
private String commencing;
	
	private String location;
	
	
	private String clientname;
	
	private String aptmtypeId;
	
	private String clientId;
	
	private String dna;
	
	private String dnareason;
	
	private String charge;
	
	private String apmttypetext;
	
	private int appointmentId;
	
	private String mobno;
	
	private String email;
	
	private String address;
	
	private String town;
	
	private String country;
	
	private String fromDate = "";
	
	private String toDate = "";
	
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
	
	private String ref_name;
	
	private String ref_contact;
	
	private String ref_letter;
	
	private String thirdParty;
	
	private String payby;
	
	private String gpid;
	
	private String diaryUser = "";
	
	private String condition;
	
	private ArrayList<Accounts>thirdPartyList;
	
	private ArrayList<Accounts>gpList;
	
	private ArrayList<DiaryManagement>userLists;
	
	private ArrayList<TreatmentType> treatmentTypeList;
	
	private ArrayList<Client>sourceOfIntroList ;
	
	private String sourceOfIntro;
	
	private ArrayList<Accounts>locationList;
	
	private String order = "asc"; 
	
	private String orderby = "";
	
	private ArrayList<SummaryReport>returningPtsList;
	
	private ArrayList<Discharge>dischargeOutcomeList;
	
	private ArrayList<Discharge>dischargeStatusList;
	
	private String dischargeStatus;
	
	private String dischrgeOutcomes;
	
	private String discharge = "0";
	
	private ArrayList<SummaryReport>odReportList;
	
	private String ipdopd = "IPD";
	
	private boolean selecctall;
	
	private ArrayList<Accounts> chargesharelist;
	
	private ArrayList<Client> clientlist;
	
	private String balancelimit;
	
	private String presentipd="1";
	
	private ArrayList<Accounts> bilingreportlist;
	private ArrayList<Accounts> discountreportlist;
	private ArrayList<Accounts> cancelinvoicereportlist;
	private String  totalopdamount,totalipdamount,totalinvstamount;
	private String  opdamount,ipdamount,invstamount;
	private int totalopdseen;
	private int ipdnewadmission;
	private String ipddebitcount;
	private String opddebitcount;
	private String investigationdebitcount;
	private ArrayList<Accounts> chargereportlist;
	private ArrayList<NotAvailableSlot> otprocedurechargelist;
	private int totalprocedure;
	private String clinicName;
	 private String clinicOwner;
	 private String clinicemail;
	 private String clinicaddress;
	 private String clinicity;
	 private String websiteUrl;
	 private String landLine;
	 private ArrayList<Clinic> locationAdressList;
	 private String owner_qualification;
	 private String clinicLogo;
	 private String netipddebitcount;
	 private String netopddebitcount;
	 private String netinvestigationdebitcount;
	
	 private ArrayList<Accounts> dailyIPDAdmsDischList;
	private ArrayList<Accounts> paymentreportlist;
	private String itype;
	private ArrayList<Master> invoicetypelist;
	
	private ArrayList<SummaryReport> deathReportList;
	private ArrayList<SummaryReport> currentPatientsList;
	private int days;
	private String ward;
	private ArrayList<SummaryReport> wardlist;
	private double totalpatient=0;
	private double totalbed=0;
	private String totalbedoccupancy;
	private double averagestay;
	private ArrayList<SummaryReport> bedoccupancylist;
	private ArrayList<SummaryReport> newbedoccupancylist;
	private ArrayList<Accounts> opdipdconversionrevenuelist;
	private int count;
	private String totaldebitAmount;
	private String totaldebitAmountPer;
	private String totalitypeamount;
	private String medicinedebitcount;
	private String netmedicinedebitcount;
	
	 public String getMedicinedebitcount() {
		return medicinedebitcount;
	}

	public void setMedicinedebitcount(String medicinedebitcount) {
		this.medicinedebitcount = medicinedebitcount;
	}

	public String getNetmedicinedebitcount() {
		return netmedicinedebitcount;
	}

	public void setNetmedicinedebitcount(String netmedicinedebitcount) {
		this.netmedicinedebitcount = netmedicinedebitcount;
	}

	public String getTotalitypeamount() {
		return totalitypeamount;
	}

	public void setTotalitypeamount(String totalitypeamount) {
		this.totalitypeamount = totalitypeamount;
	}

	public String getTotalopdamount() {
		return totalopdamount;
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

	public String getTotaldebitAmountPer() {
		return totaldebitAmountPer;
	}

	public void setTotaldebitAmountPer(String totaldebitAmountPer) {
		this.totaldebitAmountPer = totaldebitAmountPer;
	}

	public String getTotaldebitAmount() {
		return totaldebitAmount;
	}

	public void setTotaldebitAmount(String totaldebitAmount) {
		this.totaldebitAmount = totaldebitAmount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<Accounts> getOpdipdconversionrevenuelist() {
		return opdipdconversionrevenuelist;
	}

	public void setOpdipdconversionrevenuelist(ArrayList<Accounts> opdipdconversionrevenuelist) {
		this.opdipdconversionrevenuelist = opdipdconversionrevenuelist;
	}

	public ArrayList<SummaryReport> getNewbedoccupancylist() {
		return newbedoccupancylist;
	}

	public void setNewbedoccupancylist(ArrayList<SummaryReport> newbedoccupancylist) {
		this.newbedoccupancylist = newbedoccupancylist;
	}

	public ArrayList<SummaryReport> getBedoccupancylist() {
		return bedoccupancylist;
	}

	public void setBedoccupancylist(ArrayList<SummaryReport> bedoccupancylist) {
		this.bedoccupancylist = bedoccupancylist;
	}

	public double getAveragestay() {
		return averagestay;
	}

	public void setAveragestay(double averagestay) {
		this.averagestay = averagestay;
	}

	public double getTotalpatient() {
		return totalpatient;
	}

	public void setTotalpatient(double totalpatient) {
		this.totalpatient = totalpatient;
	}

	public double getTotalbed() {
		return totalbed;
	}

	public void setTotalbed(double totalbed) {
		this.totalbed = totalbed;
	}

	public String getTotalbedoccupancy() {
		return totalbedoccupancy;
	}

	public void setTotalbedoccupancy(String totalbedoccupancy) {
		this.totalbedoccupancy = totalbedoccupancy;
	}

	public ArrayList<SummaryReport> getDeathReportList() {
		return deathReportList;
	}

	public void setDeathReportList(ArrayList<SummaryReport> deathReportList) {
		this.deathReportList = deathReportList;
	}

	public ArrayList<SummaryReport> getCurrentPatientsList() {
		return currentPatientsList;
	}

	public void setCurrentPatientsList(ArrayList<SummaryReport> currentPatientsList) {
		this.currentPatientsList = currentPatientsList;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public ArrayList<SummaryReport> getWardlist() {
		return wardlist;
	}

	public void setWardlist(ArrayList<SummaryReport> wardlist) {
		this.wardlist = wardlist;
	}

	public ArrayList<Master> getInvoicetypelist() {
		return invoicetypelist;
	}

	public void setInvoicetypelist(ArrayList<Master> invoicetypelist) {
		this.invoicetypelist = invoicetypelist;
	}

	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}

	public ArrayList<Accounts> getPaymentreportlist() {
		return paymentreportlist;
	}

	public void setPaymentreportlist(ArrayList<Accounts> paymentreportlist) {
		this.paymentreportlist = paymentreportlist;
	}

	public ArrayList<Accounts> getDailyIPDAdmsDischList() {
		return dailyIPDAdmsDischList;
	}

	public void setDailyIPDAdmsDischList(ArrayList<Accounts> dailyIPDAdmsDischList) {
		this.dailyIPDAdmsDischList = dailyIPDAdmsDischList;
	}

	public String getNetipddebitcount() {
		return netipddebitcount;
	}

	public void setNetipddebitcount(String netipddebitcount) {
		this.netipddebitcount = netipddebitcount;
	}

	public String getNetopddebitcount() {
		return netopddebitcount;
	}

	public void setNetopddebitcount(String netopddebitcount) {
		this.netopddebitcount = netopddebitcount;
	}

	public String getNetinvestigationdebitcount() {
		return netinvestigationdebitcount;
	}

	public void setNetinvestigationdebitcount(String netinvestigationdebitcount) {
		this.netinvestigationdebitcount = netinvestigationdebitcount;
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

	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}

	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}

	public int getTotalprocedure() {
		return totalprocedure;
	}

	public void setTotalprocedure(int totalprocedure) {
		this.totalprocedure = totalprocedure;
	}

	public ArrayList<NotAvailableSlot> getOtprocedurechargelist() {
		return otprocedurechargelist;
	}

	public void setOtprocedurechargelist(ArrayList<NotAvailableSlot> otprocedurechargelist) {
		this.otprocedurechargelist = otprocedurechargelist;
	}

	public ArrayList<Accounts> getChargereportlist() {
		return chargereportlist;
	}

	public void setChargereportlist(ArrayList<Accounts> chargereportlist) {
		this.chargereportlist = chargereportlist;
	}

	public int getTotalopdseen() {
		return totalopdseen;
	}

	public void setTotalopdseen(int totalopdseen) {
		this.totalopdseen = totalopdseen;
	}

	public int getIpdnewadmission() {
		return ipdnewadmission;
	}

	public void setIpdnewadmission(int ipdnewadmission) {
		this.ipdnewadmission = ipdnewadmission;
	}



	public String getIpddebitcount() {
		return ipddebitcount;
	}

	public void setIpddebitcount(String ipddebitcount) {
		this.ipddebitcount = ipddebitcount;
	}

	public String getOpddebitcount() {
		return opddebitcount;
	}

	public void setOpddebitcount(String opddebitcount) {
		this.opddebitcount = opddebitcount;
	}

	public String getInvestigationdebitcount() {
		return investigationdebitcount;
	}

	public void setInvestigationdebitcount(String investigationdebitcount) {
		this.investigationdebitcount = investigationdebitcount;
	}

	public ArrayList<Accounts> getCancelinvoicereportlist() {
		return cancelinvoicereportlist;
	}

	public void setCancelinvoicereportlist(ArrayList<Accounts> cancelinvoicereportlist) {
		this.cancelinvoicereportlist = cancelinvoicereportlist;
	}

	public ArrayList<Accounts> getDiscountreportlist() {
		return discountreportlist;
	}

	public void setDiscountreportlist(ArrayList<Accounts> discountreportlist) {
		this.discountreportlist = discountreportlist;
	}

	public ArrayList<Accounts> getBilingreportlist() {
		return bilingreportlist;
	}

	public void setBilingreportlist(ArrayList<Accounts> bilingreportlist) {
		this.bilingreportlist = bilingreportlist;
	}

	public String getPresentipd() {
		return presentipd;
	}

	public void setPresentipd(String presentipd) {
		this.presentipd = presentipd;
	}

	public String getBalancelimit() {
		return balancelimit;
	}

	public void setBalancelimit(String balancelimit) {
		this.balancelimit = balancelimit;
	}

	public ArrayList<Client> getClientlist() {
		return clientlist;
	}

	public void setClientlist(ArrayList<Client> clientlist) {
		this.clientlist = clientlist;
	}

	public ArrayList<Accounts> getChargesharelist() {
		return chargesharelist;
	}

	public void setChargesharelist(ArrayList<Accounts> chargesharelist) {
		this.chargesharelist = chargesharelist;
	}

	public ArrayList<Master> getDoctorlist() {
		return doctorlist;
	}

	public void setDoctorlist(ArrayList<Master> doctorlist) {
		this.doctorlist = doctorlist;
	}
	
	
	public boolean isSelecctall() {
		return selecctall;
	}

	public void setSelecctall(boolean selecctall) {
		this.selecctall = selecctall;
	}

	public String getIpdopd() {
		return ipdopd;
	}

	public void setIpdopd(String ipdopd) {
		this.ipdopd = ipdopd;
	}

	public ArrayList<SummaryReport> getOdReportList() {
		return odReportList;
	}

	public void setOdReportList(ArrayList<SummaryReport> odReportList) {
		this.odReportList = odReportList;
	}

	public ArrayList<Discharge> getDischargeOutcomeList() {
		return dischargeOutcomeList;
	}

	public void setDischargeOutcomeList(ArrayList<Discharge> dischargeOutcomeList) {
		this.dischargeOutcomeList = dischargeOutcomeList;
	}

	public ArrayList<Discharge> getDischargeStatusList() {
		return dischargeStatusList;
	}

	public void setDischargeStatusList(ArrayList<Discharge> dischargeStatusList) {
		this.dischargeStatusList = dischargeStatusList;
	}

	public String getDischargeStatus() {
		return dischargeStatus;
	}

	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	public String getDischrgeOutcomes() {
		return dischrgeOutcomes;
	}

	public void setDischrgeOutcomes(String dischrgeOutcomes) {
		this.dischrgeOutcomes = dischrgeOutcomes;
	}

	public String getDischarge() {
		return discharge;
	}

	public void setDischarge(String discharge) {
		this.discharge = discharge;
	}

	public ArrayList<SummaryReport> getReturningPtsList() {
		return returningPtsList;
	}

	public void setReturningPtsList(ArrayList<SummaryReport> returningPtsList) {
		this.returningPtsList = returningPtsList;
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

	public ArrayList<Accounts> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Accounts> locationList) {
		this.locationList = locationList;
	}

	public ArrayList<Client> getSourceOfIntroList() {
		return sourceOfIntroList;
	}

	public void setSourceOfIntroList(ArrayList<Client> sourceOfIntroList) {
		this.sourceOfIntroList = sourceOfIntroList;
	}

	public String getSourceOfIntro() {
		return sourceOfIntro;
	}

	public void setSourceOfIntro(String sourceOfIntro) {
		this.sourceOfIntro = sourceOfIntro;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public String getGpid() {
		return gpid;
	}

	public void setGpid(String gpid) {
		this.gpid = gpid;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public ArrayList<Accounts> getThirdPartyList() {
		return thirdPartyList;
	}

	public void setThirdPartyList(ArrayList<Accounts> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}

	public ArrayList<Accounts> getGpList() {
		return gpList;
	}

	public void setGpList(ArrayList<Accounts> gpList) {
		this.gpList = gpList;
	}

	public ArrayList<DiaryManagement> getUserLists() {
		return userLists;
	}

	public void setUserLists(ArrayList<DiaryManagement> userLists) {
		this.userLists = userLists;
	}

	public ArrayList<TreatmentType> getTreatmentTypeList() {
		return treatmentTypeList;
	}

	public void setTreatmentTypeList(ArrayList<TreatmentType> treatmentTypeList) {
		this.treatmentTypeList = treatmentTypeList;
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
	
	ArrayList<SummaryReport> referalList;
	
	public ArrayList<SummaryReport> getReferalList() {
		return referalList;
	}

	public void setReferalList(ArrayList<SummaryReport> referalList) {
		this.referalList = referalList;
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
	
	ArrayList<SummaryReport> appkeptDNAList;
	
	public ArrayList<SummaryReport> getAppkeptDNAList() {
		return appkeptDNAList;
	}

	public void setAppkeptDNAList(ArrayList<SummaryReport> appkeptDNAList) {
		this.appkeptDNAList = appkeptDNAList;
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
	
	ArrayList<SummaryReport> DNAAnalysisReport;
	
	ArrayList<DiaryManagement> userList;
	
	ArrayList<SummaryReport> treatmentReferralList;
	
	public ArrayList<SummaryReport> getTreatmentReferralList() {
		return treatmentReferralList;
	}

	public void setTreatmentReferralList(
			ArrayList<SummaryReport> treatmentReferralList) {
		this.treatmentReferralList = treatmentReferralList;
	}

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	
	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
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
	
	

	public ArrayList<SummaryReport> getDNAAnalysisReport() {
		return DNAAnalysisReport;
	}

	public void setDNAAnalysisReport(ArrayList<SummaryReport> dNAAnalysisReport) {
		DNAAnalysisReport = dNAAnalysisReport;
	}
public double getGrossDeathRate() {
		return grossDeathRate;
	}

	public void setGrossDeathRate(double grossDeathRate) {
		this.grossDeathRate = grossDeathRate;
	}
public double getNetdeathrate() {
		return netdeathrate;
	}

	public void setNetdeathrate(double netdeathrate) {
		this.netdeathrate = netdeathrate;
	}
public int getDeath48count() {
		return death48count;
	}

	public void setDeath48count(int death48count) {
		this.death48count = death48count;
	}
public ArrayList<SummaryReport> getUserwisepayementlist() {
		return userwisepayementlist;
	}

	public void setUserwisepayementlist(ArrayList<SummaryReport> userwisepayementlist) {
		this.userwisepayementlist = userwisepayementlist;
	}
public ArrayList<SummaryReport> getUserwisepaymentlist() {
		return userwisepaymentlist;
	}

	public void setUserwisepaymentlist(ArrayList<SummaryReport> userwisepaymentlist) {
		this.userwisepaymentlist = userwisepaymentlist;
	}
public ArrayList<SummaryReport> getMlclist() {
		return mlclist;
	}

	public void setMlclist(ArrayList<SummaryReport> mlclist) {
		this.mlclist = mlclist;
	}
private double netdeathrate;
private double grossDeathRate;
private int death48count;
private ArrayList<SummaryReport> userwisepayementlist;
private ArrayList<SummaryReport> userwisepaymentlist;
private ArrayList<SummaryReport> mlclist; 
private double ttcard,ttcash,ttcheque,ttneft,ttother,ttupi;
private double adttcard,adttcash,adttcheque,adttneft,adttother,adttupi;
private double rettcard,rettcash,rettcheque,rettneft,rettother,rettupi;
public double getTtupi() {
	return ttupi;
}

public void setTtupi(double ttupi) {
	this.ttupi = ttupi;
}

public double getAdttupi() {
	return adttupi;
}

public void setAdttupi(double adttupi) {
	this.adttupi = adttupi;
}

public double getRettupi() {
	return rettupi;
}

public void setRettupi(double rettupi) {
	this.rettupi = rettupi;
}

public double getTupi() {
	return tupi;
}

public void setTupi(double tupi) {
	this.tupi = tupi;
}
private double tcard,tcash,tcheque,tneft,tother,tnettotal,tupi;
public double getTcard() {
	return tcard;
}

public void setTcard(double tcard) {
	this.tcard = tcard;
}

public double getTcash() {
	return tcash;
}

public void setTcash(double tcash) {
	this.tcash = tcash;
}

public double getTcheque() {
	return tcheque;
}

public void setTcheque(double tcheque) {
	this.tcheque = tcheque;
}

public double getTneft() {
	return tneft;
}

public void setTneft(double tneft) {
	this.tneft = tneft;
}

public double getTother() {
	return tother;
}

public void setTother(double tother) {
	this.tother = tother;
}

public double getTtcard() {
	return ttcard;
}

public void setTtcard(double ttcard) {
	this.ttcard = ttcard;
}

public double getTtcash() {
	return ttcash;
}

public void setTtcash(double ttcash) {
	this.ttcash = ttcash;
}

public double getTtcheque() {
	return ttcheque;
}

public void setTtcheque(double ttcheque) {
	this.ttcheque = ttcheque;
}

public double getTtneft() {
	return ttneft;
}

public void setTtneft(double ttneft) {
	this.ttneft = ttneft;
}

public double getTtother() {
	return ttother;
}

public void setTtother(double ttother) {
	this.ttother = ttother;
}

public double getAdttcard() {
	return adttcard;
}

public void setAdttcard(double adttcard) {
	this.adttcard = adttcard;
}

public double getAdttcash() {
	return adttcash;
}

public void setAdttcash(double adttcash) {
	this.adttcash = adttcash;
}

public double getAdttcheque() {
	return adttcheque;
}

public void setAdttcheque(double adttcheque) {
	this.adttcheque = adttcheque;
}

public double getAdttneft() {
	return adttneft;
}

public void setAdttneft(double adttneft) {
	this.adttneft = adttneft;
}

public double getAdttother() {
	return adttother;
}

public void setAdttother(double adttother) {
	this.adttother = adttother;
}

public double getRettcard() {
	return rettcard;
}

public void setRettcard(double rettcard) {
	this.rettcard = rettcard;
}

public double getRettcash() {
	return rettcash;
}

public void setRettcash(double rettcash) {
	this.rettcash = rettcash;
}

public double getRettcheque() {
	return rettcheque;
}

public void setRettcheque(double rettcheque) {
	this.rettcheque = rettcheque;
}

public double getRettneft() {
	return rettneft;
}

public void setRettneft(double rettneft) {
	this.rettneft = rettneft;
}

public double getRettother() {
	return rettother;
}

public void setRettother(double rettother) {
	this.rettother = rettother;
}

public double getTnettotal() {
	return tnettotal;
}

public void setTnettotal(double tnettotal) {
	this.tnettotal = tnettotal;
}
public String getReffedby() {
	return reffedby;
}

public void setReffedby(String reffedby) {
	this.reffedby = reffedby;
}
public ArrayList<SummaryReport> getRefferedbylist() {
	return refferedbylist;
}

public void setRefferedbylist(ArrayList<SummaryReport> refferedbylist) {
	this.refferedbylist = refferedbylist;
}
public ArrayList<String> getUniquelist() {
	return uniquelist;
}

public void setUniquelist(ArrayList<String> uniquelist) {
	this.uniquelist = uniquelist;
}
private String reffedby;
private ArrayList<SummaryReport> refferedbylist;
private ArrayList<String> uniquelist;
private double gtrec, gtref, gtadv;

private int todaysadm;
private int todaysdis;

public double getGtrec() {
	return gtrec;
}

public void setGtrec(double gtrec) {
	this.gtrec = gtrec;
}

public double getGtref() {
	return gtref;
}

public void setGtref(double gtref) {
	this.gtref = gtref;
}

public double getGtadv() {
	return gtadv;
}

public void setGtadv(double gtadv) {
	this.gtadv = gtadv;
}
public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}
public int getTodaysadm() {
	return todaysadm;
}

public void setTodaysadm(int todaysadm) {
	this.todaysadm = todaysadm;
}
public int getTodaysdis() {
	return todaysdis;
}

public void setTodaysdis(int todaysdis) {
	this.todaysdis = todaysdis;
}
public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}
public String getDept() {
	return dept;
}

public void setDept(String dept) {
	this.dept = dept;
}
public ArrayList<SummaryReport> getPatientlist() {
	return patientlist;
}

public void setPatientlist(ArrayList<SummaryReport> patientlist) {
	this.patientlist = patientlist;
}
public ArrayList<SummaryReport> getDeptlist() {
	return deptlist;
}

public void setDeptlist(ArrayList<SummaryReport> deptlist) {
	this.deptlist = deptlist;
}
public ArrayList<SummaryReport> getLocationlist() {
	return locationlist;
}

public void setLocationlist(ArrayList<SummaryReport> locationlist) {
	this.locationlist = locationlist;
}
public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}
public String getMonth() {
	return month;
}

public void setMonth(String month) {
	this.month = month;
}
public ArrayList<SummaryReport> getAvg_list() {
	return avg_list;
}

public void setAvg_list(ArrayList<SummaryReport> avg_list) {
	this.avg_list = avg_list;
}
public String getIssecondery() {
	return issecondery;
}

public void setIssecondery(String issecondery) {
	this.issecondery = issecondery;
}
private String username;
private String type;  
private String dept; 
private ArrayList<SummaryReport> patientlist;
private ArrayList<SummaryReport> deptlist;
private ArrayList<SummaryReport> locationlist;
private String city; 
private ArrayList<SummaryReport> avg_list;
private String issecondery;
private String  testname, abovebelow, obsvalue;
private ArrayList<Investigation> investigationlist;
public String getTestname() {
	return testname;
}

public void setTestname(String testname) {
	this.testname = testname;
}

public String getAbovebelow() {
	return abovebelow;
}

public void setAbovebelow(String abovebelow) {
	this.abovebelow = abovebelow;
}

public String getObsvalue() {
	return obsvalue;
}

public void setObsvalue(String obsvalue) {
	this.obsvalue = obsvalue;
}


public ArrayList<Investigation> getInvestigationlist() {
	return investigationlist;
}

public void setInvestigationlist(ArrayList<Investigation> investigationlist) {
	this.investigationlist = investigationlist;
}
public ArrayList<Master> getInestigationnamelist() {
	return inestigationnamelist;
}

public void setInestigationnamelist(ArrayList<Master> inestigationnamelist) {
	this.inestigationnamelist = inestigationnamelist;
}
public String getAdm() {
	return adm;
}
private Double totalot=0.0,totalopd=0.0,totalipd=0.0;
public Double getTotalot() {
	return totalot;
}

public void setTotalot(Double totalot) {
	this.totalot = totalot;
}

public Double getTotalopd() {
	return totalopd;
}

public void setTotalopd(Double totalopd) {
	this.totalopd = totalopd;
}

public Double getTotalipd() {
	return totalipd;
}

public void setTotalipd(Double totalipd) {
	this.totalipd = totalipd;
}

public void setAdm(String adm) {
	this.adm = adm;
}
public String getYear() {
	return year;
}

public void setYear(String year) {
	this.year = year;
}
public String getPkgid() {
	return pkgid;
}

public void setPkgid(String pkgid) {
	this.pkgid = pkgid;
}
public ArrayList<PackageMaster> getPkgmasterlist() {
	return pkgmasterlist;
}

public void setPkgmasterlist(ArrayList<PackageMaster> pkgmasterlist) {
	this.pkgmasterlist = pkgmasterlist;
}
public ArrayList<Product> getProductlist() {
	return productlist;
}

public void setProductlist(ArrayList<Product> productlist) {
	this.productlist = productlist;
}

public double getTotalamt() {
	return totalamt;
}

public void setTotalamt(double totalamt) {
	this.totalamt = totalamt;
}
public ArrayList<SummaryReport> getReportlist() {
	return reportlist;
}

public void setReportlist(ArrayList<SummaryReport> reportlist) {
	this.reportlist = reportlist;
}
public String getOrderby1() {
	return orderby1;
}

public void setOrderby1(String orderby1) {
	this.orderby1 = orderby1;
}
public double getTotalrefundamount() {
	return totalrefundamount;
}

public void setTotalrefundamount(double totalrefundamount) {
	this.totalrefundamount = totalrefundamount;
}

public ArrayList<CompleteAppointment> getParentrefundrequestlist() {
	return parentrefundrequestlist;
}

public void setParentrefundrequestlist(ArrayList<CompleteAppointment> parentrefundrequestlist) {
	this.parentrefundrequestlist = parentrefundrequestlist;
}



public String getIpdopdwise() {
	return ipdopdwise;
}

public void setIpdopdwise(String ipdopdwise) {
	this.ipdopdwise = ipdopdwise;
}
private String year;

private ArrayList<Master> inestigationnamelist;
private String adm;
private String pkgid;
private ArrayList<PackageMaster> pkgmasterlist;
private ArrayList<Product> productlist;
private double totalamt;
private ArrayList<SummaryReport> reportlist;
private String orderby1;
private double totalrefundamount;
private ArrayList<CompleteAppointment> parentrefundrequestlist;
private String ipdopdwise;

private double totalcredit,creditReturn,todaycard,todaycash,chequepayments,neftpayment,todayreturn,todaydisc,totalpayment;

public double getTotalcredit() {
	return totalcredit;
}

public void setTotalcredit(double totalcredit) {
	this.totalcredit = totalcredit;
}

public double getCreditReturn() {
	return creditReturn;
}

public void setCreditReturn(double creditReturn) {
	this.creditReturn = creditReturn;
}

public double getTodaycard() {
	return todaycard;
}

public void setTodaycard(double todaycard) {
	this.todaycard = todaycard;
}

public double getTodaycash() {
	return todaycash;
}

public void setTodaycash(double todaycash) {
	this.todaycash = todaycash;
}

public double getChequepayments() {
	return chequepayments;
}

public void setChequepayments(double chequepayments) {
	this.chequepayments = chequepayments;
}

public double getNeftpayment() {
	return neftpayment;
}

public void setNeftpayment(double neftpayment) {
	this.neftpayment = neftpayment;
}

public double getTodayreturn() {
	return todayreturn;
}

public void setTodayreturn(double todayreturn) {
	this.todayreturn = todayreturn;
}

public double getTodaydisc() {
	return todaydisc;
}

public void setTodaydisc(double todaydisc) {
	this.todaydisc = todaydisc;
}

public double getTotalpayment() {
	return totalpayment;
}

public void setTotalpayment(double totalpayment) {
	this.totalpayment = totalpayment;
}
public String getYearly() {
	return yearly;
}

public void setYearly(String yearly) {
	this.yearly = yearly;
}
public ArrayList<SummaryReportForm> getTotalrevenuelist() {
	return totalrevenuelist;
}

public void setTotalrevenuelist(ArrayList<SummaryReportForm> totalrevenuelist) {
	this.totalrevenuelist = totalrevenuelist;
}

public String getPaymentmode() {
	return paymentmode;
}

public void setPaymentmode(String paymentmode) {
	this.paymentmode = paymentmode;
}
public int getIsmlc() {
	return ismlc;
}

public void setIsmlc(int ismlc) {
	this.ismlc = ismlc;
}
public ArrayList<Accounts> getDiscountchargereportlist() {
	return discountchargereportlist;
}

public void setDiscountchargereportlist(ArrayList<Accounts> discountchargereportlist) {
	this.discountchargereportlist = discountchargereportlist;
}
public String getYr() {
	return yr;
}

public void setYr(String yr) {
	this.yr = yr;
}
public String getMnth() {
	return mnth;
}

public void setMnth(String mnth) {
	this.mnth = mnth;
}
public String getAllttl() {
	return allttl;
}

public void setAllttl(String allttl) {
	this.allttl = allttl;
}
public ArrayList<Bed> getVisitingConList() {
	return visitingConList;
}

public void setVisitingConList(ArrayList<Bed> visitingConList) {
	this.visitingConList = visitingConList;
}
public ArrayList<UserProfile> getUserConList() {
	return userConList;
}

public void setUserConList(ArrayList<UserProfile> userConList) {
	this.userConList = userConList;
}
public String getT1() {
	return t1;
}

public void setT1(String t1) {
	this.t1 = t1;
}
public String getT2() {
	return t2;
}

public void setT2(String t2) {
	this.t2 = t2;
}
public String getT3() {
	return t3;
}

public void setT3(String t3) {
	this.t3 = t3;
}
public String getSearch() {
	return search;
}

public void setSearch(String search) {
	this.search = search;
}
private String yearly;
private ArrayList<SummaryReportForm> totalrevenuelist;
private ArrayList<Accounts> discountchargereportlist;
private String paymentmode;
private String yr,mnth;
private int ismlc;
private ArrayList<Bed> visitingConList;
private ArrayList<UserProfile> userConList;
private String t1,t2,t3;
}

