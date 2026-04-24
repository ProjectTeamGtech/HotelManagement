package com.apm.Master.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Registration.eu.entity.Clinic;

public class PackageMasterForm {
	private int child_id;
	private String parentid;
	private String mastername;
	private ArrayList<Master> masterlist;
	private ArrayList<PackageMaster> packagelist;
	ArrayList<Master> masterChageTypeList;
	private Collection<PackageMaster> packagedata;
	private String package_name;
	private String package_amount;
	private String searchText;
	private String days;
	private String description;
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
	 private String pkgtypetp; 
	 private String paymentmode;
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
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	private String chargeid;
	private String pagerecords;
	private String totalRecords;
	private String chargetype;
	private String packagetype;
	private ArrayList<AppointmentType> additionalChargesList;
	public String getPackagetype() {
		return packagetype;
	}
	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}
	public String getChargetype() {
		return chargetype;
	}
	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}
	public String getChargeid() {
		return chargeid;
	}
	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getPackage_amount() {
		return package_amount;
	}
	public void setPackage_amount(String package_amount) {
		this.package_amount = package_amount;
	}
	public Collection<PackageMaster> getPackagedata() {
		return packagedata;
	}
	
	private Collection<PackageMaster> packagetpdata;
	public void setPackagedata(Collection<PackageMaster> packagedata) {
		this.packagedata = packagedata;
	}
	public ArrayList<PackageMaster> getPackagelist() {
		return packagelist;
	}
	public void setPackagelist(ArrayList<PackageMaster> packagelist) {
		this.packagelist = packagelist;
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
	public ArrayList<Master> getMasterChageTypeList() {
		return masterChageTypeList;
	}
	public void setMasterChageTypeList(ArrayList<Master> masterChageTypeList) {
		this.masterChageTypeList = masterChageTypeList;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public int getChild_id() {
		return child_id;
	}
	public void setChild_id(int child_id) {
		this.child_id = child_id;
	}
	public String getPagerecords() {
		return pagerecords;
	}
	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Collection<PackageMaster> getPackagetpdata() {
		return packagetpdata;
	}
	public void setPackagetpdata(Collection<PackageMaster> packagetpdata) {
		this.packagetpdata = packagetpdata;
	}
	
	
	public ArrayList<Master> getAllmsterlistcharges() {
		return allmsterlistcharges;
	}
	public void setAllmsterlistcharges(ArrayList<Master> allmsterlistcharges) {
		this.allmsterlistcharges = allmsterlistcharges;
	}

	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public ArrayList<AppointmentType> getAdditionalChargesList() {
		return additionalChargesList;
	}
	public void setAdditionalChargesList(ArrayList<AppointmentType> additionalChargesList) {
		this.additionalChargesList = additionalChargesList;
	}

	public String getPkgtypetp() {
		return pkgtypetp;
	}
	public void setPkgtypetp(String pkgtypetp) {
		this.pkgtypetp = pkgtypetp;
	}

	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	private ArrayList<Master> allmsterlistcharges;
	private String clientid;
}
