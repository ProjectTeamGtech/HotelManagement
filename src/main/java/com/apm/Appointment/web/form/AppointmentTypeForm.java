package com.apm.Appointment.web.form;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Report.eu.entity.SummaryReport;


public class AppointmentTypeForm{
	public String getSessionno() {
		return sessionno;
	}

	public void setSessionno(String sessionno) {
		this.sessionno = sessionno;
	}

	private int id;
	
	private String name;
	
	private String description;
	
	private String category;
	
	private String duration;
	
	private String color;
	
	private String location;
	private String chargeType;
	private String message;
	
	private String reportField;
	
	private String searchText = "";
	private String basecharge="0";
	
	
	private String hdnnewledger;
	
	
	private ArrayList<Accounts>thirdPartyList;
	
	private String payby;
	
	private String mastername;
	
	ArrayList<Master>newChargeTypeList;
	
	private String ledgername;
	private String servicename = "0";
	ArrayList<Master>ledgerList;
	ArrayList<Master>ledgerserviceList;
	
	private String hdnledgerserviceid;
	private String dbselectedservices;
	ArrayList<Accounts>ledgerreport;
	
	private String hdnnewahead;
	private String hdnnewobal;
	
	private String aheadname;
	private String aheadservicename;
	ArrayList<Master>aheadNameList;
	ArrayList<Master>aheadserviceList;
	
	
	private String hdnaheadserviceid;
	private String dbselectedaheadservices;
	
	
	private String aheadnametxt = "";
	
	private String hdnhowpaid;  
	
	private String hdnsearchtext = "";
	
	private String actype = "";
	
	private String fromDate = "";
	private String toDate = "";
	ArrayList<Accounts>ocreptData;
	
	
	private String debitx;
	private String creditx;
	
	ArrayList<Master>bankNameList;
	private String bnkname = "0";
	
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
	 private String address;
	
	 private ArrayList<Accounts>sdebtorlist;
	 private ArrayList<Accounts>crdtorlist;
	 
	 ArrayList<Accounts>screditorList;
	 ArrayList<Master>debitaheadList;
	 
	 private String cctotal;
	  private String ddtotal;
	  
	  private String aheadname22;

	private String shareablecharge="0";
	  
	  private String opbal;
	  
	  private String code;
	  ArrayList<Master>clinicstafflist;
	  private String clinic_name_id;
	  private String sessionno;
	  
	  public String getClinic_name_id() {
			return clinic_name_id;
		}

		public void setClinic_name_id(String clinic_name_id) {
			this.clinic_name_id = clinic_name_id;
		}
	 
	 public ArrayList<Master> getClinicstafflist() {
		return clinicstafflist;
	}

	public void setClinicstafflist(ArrayList<Master> clinicstafflist) {
		this.clinicstafflist = clinicstafflist;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpbal() {
		return opbal;
	}

	public void setOpbal(String opbal) {
		this.opbal = opbal;
	}

	public String getShareablecharge() {
		return shareablecharge;
	}

	public void setShareablecharge(String shareablecharge) {
		this.shareablecharge = shareablecharge;
	}

	public String getAheadname22() {
		return aheadname22;
	}

	public void setAheadname22(String aheadname22) {
		this.aheadname22 = aheadname22;
	}

	public String getCctotal() {
		return cctotal;
	}

	public void setCctotal(String cctotal) {
		this.cctotal = cctotal;
	}

	public String getDdtotal() {
		return ddtotal;
	}

	public void setDdtotal(String ddtotal) {
		this.ddtotal = ddtotal;
	}

	public String getLtype() {
		return ltype;
	}

	public void setLtype(String ltype) {
		this.ltype = ltype;
	}

	ArrayList<Master>creditaheadList;
	 
	 private String ltype;
	 
	 
	
	
	public ArrayList<Master> getCreditaheadList() {
		return creditaheadList;
	}

	public void setCreditaheadList(ArrayList<Master> creditaheadList) {
		this.creditaheadList = creditaheadList;
	}

	public ArrayList<Master> getDebitaheadList() {
		return debitaheadList;
	}

	public void setDebitaheadList(ArrayList<Master> debitaheadList) {
		this.debitaheadList = debitaheadList;
	}

	public ArrayList<Accounts> getScreditorList() {
		return screditorList;
	}

	public void setScreditorList(ArrayList<Accounts> screditorList) {
		this.screditorList = screditorList;
	}

	public ArrayList<Accounts> getSdebtorlist() {
		return sdebtorlist;
	}

	public void setSdebtorlist(ArrayList<Accounts> sdebtorlist) {
		this.sdebtorlist = sdebtorlist;
	}

	public ArrayList<Accounts> getCrdtorlist() {
		return crdtorlist;
	}

	public void setCrdtorlist(ArrayList<Accounts> crdtorlist) {
		this.crdtorlist = crdtorlist;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Master> getBankNameList() {
		return bankNameList;
	}

	public void setBankNameList(ArrayList<Master> bankNameList) {
		this.bankNameList = bankNameList;
	}

	public String getBnkname() {
		return bnkname;
	}

	public void setBnkname(String bnkname) {
		this.bnkname = bnkname;
	}

	public String getDebitx() {
		return debitx;
	}

	public void setDebitx(String debitx) {
		this.debitx = debitx;
	}

	public String getCreditx() {
		return creditx;
	}

	public void setCreditx(String creditx) {
		this.creditx = creditx;
	}

	public ArrayList<Accounts> getOcreptData() {
		return ocreptData;
	}

	public void setOcreptData(ArrayList<Accounts> ocreptData) {
		this.ocreptData = ocreptData;
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

	public String getActype() {
		return actype;
	}

	public void setActype(String actype) {
		this.actype = actype;
	}

	public String getHdnsearchtext() {
		return hdnsearchtext;
	}

	public void setHdnsearchtext(String hdnsearchtext) {
		this.hdnsearchtext = hdnsearchtext;
	}

	public String getHdnhowpaid() {
		return hdnhowpaid;
	}

	public void setHdnhowpaid(String hdnhowpaid) {
		this.hdnhowpaid = hdnhowpaid;
	}

	public String getAheadnametxt() {
		return aheadnametxt;
	}

	public void setAheadnametxt(String aheadnametxt) {
		this.aheadnametxt = aheadnametxt;
	}

	public String getHdnaheadserviceid() {
		return hdnaheadserviceid;
	}

	public void setHdnaheadserviceid(String hdnaheadserviceid) {
		this.hdnaheadserviceid = hdnaheadserviceid;
	}

	public String getDbselectedaheadservices() {
		return dbselectedaheadservices;
	}

	public void setDbselectedaheadservices(String dbselectedaheadservices) {
		this.dbselectedaheadservices = dbselectedaheadservices;
	}

	public String getAheadname() {
		return aheadname;
	}

	public void setAheadname(String aheadname) {
		this.aheadname = aheadname;
	}

	public String getAheadservicename() {
		return aheadservicename;
	}

	public void setAheadservicename(String aheadservicename) {
		this.aheadservicename = aheadservicename;
	}

	public ArrayList<Master> getAheadNameList() {
		return aheadNameList;
	}

	public void setAheadNameList(ArrayList<Master> aheadNameList) {
		this.aheadNameList = aheadNameList;
	}

	public ArrayList<Master> getAheadserviceList() {
		return aheadserviceList;
	}

	public void setAheadserviceList(ArrayList<Master> aheadserviceList) {
		this.aheadserviceList = aheadserviceList;
	}

	public String getHdnnewahead() {
		return hdnnewahead;
	}

	public void setHdnnewahead(String hdnnewahead) {
		this.hdnnewahead = hdnnewahead;
	}

	public String getHdnnewobal() {
		return hdnnewobal;
	}

	public void setHdnnewobal(String hdnnewobal) {
		this.hdnnewobal = hdnnewobal;
	}

	public ArrayList<Accounts> getLedgerreport() {
		return ledgerreport;
	}

	public void setLedgerreport(ArrayList<Accounts> ledgerreport) {
		this.ledgerreport = ledgerreport;
	}

	public String getHdnnewledger() {
		return hdnnewledger;
	}

	public void setHdnnewledger(String hdnnewledger) {
		this.hdnnewledger = hdnnewledger;
	}

	public String getDbselectedservices() {
		return dbselectedservices;
	}

	public void setDbselectedservices(String dbselectedservices) {
		this.dbselectedservices = dbselectedservices;
	}

	public String getHdnledgerserviceid() {
		return hdnledgerserviceid;
	}

	public void setHdnledgerserviceid(String hdnledgerserviceid) {
		this.hdnledgerserviceid = hdnledgerserviceid;
	}

	public String getLedgername() {
		return ledgername;
	}

	public void setLedgername(String ledgername) {
		this.ledgername = ledgername;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public ArrayList<Master> getLedgerList() {
		return ledgerList;
	}

	public void setLedgerList(ArrayList<Master> ledgerList) {
		this.ledgerList = ledgerList;
	}

	public ArrayList<Master> getLedgerserviceList() {
		return ledgerserviceList;
	}

	public void setLedgerserviceList(ArrayList<Master> ledgerserviceList) {
		this.ledgerserviceList = ledgerserviceList;
	}

	public ArrayList<Master> getNewChargeTypeList() {
		return newChargeTypeList;
	}

	public void setNewChargeTypeList(ArrayList<Master> newChargeTypeList) {
		this.newChargeTypeList = newChargeTypeList;
	}

	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	private String thirdParty;
	
	
	
	
	
	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public ArrayList<Accounts> getThirdPartyList() {
		return thirdPartyList;
	}

	public void setThirdPartyList(ArrayList<Accounts> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getReportField() {
		return reportField;
	}

	public void setReportField(String reportField) {
		this.reportField = reportField;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private ArrayList<Master> masterlist ;
	
	
	
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}

	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}

	private ArrayList<AppointmentType>appointmentTypeList;
	
	private ArrayList<AppointmentType>colorList;
	
	private ArrayList<String> apmtDurationList;
	
	private ArrayList<Location>locationList;
	
	public ArrayList<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}

	private String charges = "";
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

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ArrayList<AppointmentType> getAppointmentTypeList() {
		return appointmentTypeList;
	}

	public void setAppointmentTypeList(
			ArrayList<AppointmentType> appointmentTypeList) {
		this.appointmentTypeList = appointmentTypeList;
	}

	public ArrayList<AppointmentType> getColorList() {
		return colorList;
	}

	public void setColorList(ArrayList<AppointmentType> colorList) {
		this.colorList = colorList;
	}

	public ArrayList<String> getApmtDurationList() {
		return apmtDurationList;
	}

	public void setApmtDurationList(ArrayList<String> apmtDurationList) {
		this.apmtDurationList = apmtDurationList;
	}

	public String getBasecharge() {
		return basecharge;
	}

	public void setBasecharge(String basecharge) {
		this.basecharge = basecharge;
	}
	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLedgerreportname() {
		return ledgerreportname;
	}

	public void setLedgerreportname(String ledgerreportname) {
		this.ledgerreportname = ledgerreportname;
	}

	public String getViewaccess() {
		return viewaccess;
	}

	public void setViewaccess(String viewaccess) {
		this.viewaccess = viewaccess;
	}

	public ArrayList<SummaryReport> getWardlist() {
		return wardlist;
	}

	public void setWardlist(ArrayList<SummaryReport> wardlist) {
		this.wardlist = wardlist;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	private String userid; 
	private String ledgerreportname;
	private String viewaccess;
	private ArrayList<SummaryReport> wardlist;
	private String ward;
	private String orderby;
	private String order;
}