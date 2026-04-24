package com.apm.Bloodbank.web.form;

import java.util.ArrayList;

import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Bloodbank.eu.entity.Bloodbank;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Location;

public class BloodbankForm {


	private int id;
	private String blood_group;
	private String no_bags;
	private String name;
	private String age;
	private String address;
	private String gender;
	private String last_donation_date;
	private String phone;
	private String email;
	private String status;
	private String blood_group_id;
	private String expiry_date;
	private String ipdid;
	private String fromdate;
	private String todate;
	private String clientid;
	private String from;
	private String pagerecords;
	private int totalRecords;
	private ArrayList<Bloodbank> bloodgroupList;
	private ArrayList<Bloodbank> blooddonorsList;
	private ArrayList<Bloodbank> requestedPatientList;
	ArrayList<Bloodbank> donorPatientList;
	private String weight, dob, city, donor_state;
    private String searchText;
    private  ArrayList<DiaryManagement> eventList;
	
	private String date;
	private String month;
	
	private String qty;
	private String requestfrom;
	private String reqid;
	private String commencing;
	private String time;
	private String mobile;
	private String message;
	private String users;
	private String banks;
	private ArrayList<Bloodbank> bloodBankList; 
	private String bloodbank_component;
	private String bloodbank_idnattested;
	private String bloodbank_leuco_depleted;
	private ArrayList<Location>locationList;
	private ArrayList<AppointmentType>additionalChargeList;
	private ArrayList<Master>masterChageTypeList;
	
	
	public ArrayList<AppointmentType> getAdditionalChargeList() {
		return additionalChargeList;
	}
	public void setAdditionalChargeList(ArrayList<AppointmentType> additionalChargeList) {
		this.additionalChargeList = additionalChargeList;
	}
	public ArrayList<Master> getMasterChageTypeList() {
		return masterChageTypeList;
	}
	public void setMasterChageTypeList(ArrayList<Master> masterChageTypeList) {
		this.masterChageTypeList = masterChageTypeList;
	}
	public ArrayList<Location> getLocationList() {
		return locationList;
	}
	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}
	public String getBloodbank_idnattested() {
		return bloodbank_idnattested;
	}
	public void setBloodbank_idnattested(String bloodbank_idnattested) {
		this.bloodbank_idnattested = bloodbank_idnattested;
	}
	public String getBloodbank_leuco_depleted() {
		return bloodbank_leuco_depleted;
	}
	public void setBloodbank_leuco_depleted(String bloodbank_leuco_depleted) {
		this.bloodbank_leuco_depleted = bloodbank_leuco_depleted;
	}
	public String getBloodbank_component() {
		return bloodbank_component;
	}
	public void setBloodbank_component(String bloodbank_component) {
		this.bloodbank_component = bloodbank_component;
	}
	public ArrayList<Bloodbank> getBloodBankList() {
		return bloodBankList;
	}
	public void setBloodBankList(ArrayList<Bloodbank> bloodBankList) {
		this.bloodBankList = bloodBankList;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getRequestfrom() {
		return requestfrom;
	}
	public void setRequestfrom(String requestfrom) {
		this.requestfrom = requestfrom;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public String getCommencing() {
		return commencing;
	}
	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}	
	
	
	

	
	public String getBlood_group_id() {
		return blood_group_id;
	}
	public void setBlood_group_id(String blood_group_id) {
		this.blood_group_id = blood_group_id;
	}
	public ArrayList<Bloodbank> getBloodgroupList() {
		return bloodgroupList;
	}
	public void setBloodgroupList(ArrayList<Bloodbank> bloodgroupList) {
		this.bloodgroupList = bloodgroupList;
	}
	public ArrayList<Bloodbank> getBlooddonorsList() {
		return blooddonorsList;
	}
	public void setBlooddonorsList(ArrayList<Bloodbank> blooddonorsList) {
		this.blooddonorsList = blooddonorsList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlood_group() {
		return blood_group;
	}
	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}
	public String getNo_bags() {
		return no_bags;
	}
	public void setNo_bags(String no_bags) {
		this.no_bags = no_bags;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLast_donation_date() {
		return last_donation_date;
	}
	public void setLast_donation_date(String last_donation_date) {
		this.last_donation_date = last_donation_date;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDonor_state() {
		return donor_state;
	}
	public void setDonor_state(String donor_state) {
		this.donor_state = donor_state;
	}
	public ArrayList<Bloodbank> getDonorPatientList() {
		return donorPatientList;
	}
	public void setDonorPatientList(ArrayList<Bloodbank> donorPatientList) {
		this.donorPatientList = donorPatientList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public ArrayList<DiaryManagement> getEventList() {
		return eventList;
	}
	public void setEventList(ArrayList<DiaryManagement> eventList) {
		this.eventList = eventList;
	}
	public String getIpdid() {
		return ipdid;
	}
	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public ArrayList<Bloodbank> getRequestedPatientList() {
		return requestedPatientList;
	}
	public void setRequestedPatientList(ArrayList<Bloodbank> requestedPatientList) {
		this.requestedPatientList = requestedPatientList;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public String getBanks() {
		return banks;
	}
	public void setBanks(String banks) {
		this.banks = banks;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getWardbed() {
		return wardbed;
	}
	public void setWardbed(String wardbed) {
		this.wardbed = wardbed;
	}
	public String getAgegender() {
		return agegender;
	}
	public void setAgegender(String agegender) {
		this.agegender = agegender;
	}
	public String getPractitionername() {
		return practitionername;
	}
	public void setPractitionername(String practitionername) {
		this.practitionername = practitionername;
	}
	private String clientname;
	private String wardbed;
	private String agegender;
	private String practitionername;
	
	
}
