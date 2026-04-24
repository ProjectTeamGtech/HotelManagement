package com.apm.DiaryManagement.web.form;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.UserProfile;

public class MisDashboardForm {
	
	private String clinicname;
	
	public String getClinicname() {
		return clinicname;
	}

	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}

	private int id;
	private String date;
	
	private String time1;
	 private String ampm;
	
	 private String fullname;
	 private String userid;
	 private String mobile;
	 private String department;
	 private String state;
	 private String location;
	 private int refundstatus;
	 private int discountstatus;
	 private int indentstaus=0;
	 
	 private String ipd_admission,ipd_discharge,ipd_inhouse,ipd_vacantbed;
	 private String opd_booked,opd_completed,opd_dna,total_male,total_female,upto20;
	 private String upto40,upto60,above60;
	 
	 private String userkeyid,noti_dis_apprd_ids,noti_dis_reqtd_ids,noti_ref_appr_ids,noti_ref_reqtd_ids;
	 private int noti_dis_apprd_count,noti_dis_reqtd_count,noti_ref_appr_count,noti_ref_reqtd_count;
	 
	public String getUserkeyid() {
		return userkeyid;
	}

	public void setUserkeyid(String userkeyid) {
		this.userkeyid = userkeyid;
	}

	public String getNoti_ref_appr_ids() {
		return noti_ref_appr_ids;
	}

	public void setNoti_ref_appr_ids(String noti_ref_appr_ids) {
		this.noti_ref_appr_ids = noti_ref_appr_ids;
	}

	public String getNoti_ref_reqtd_ids() {
		return noti_ref_reqtd_ids;
	}

	public void setNoti_ref_reqtd_ids(String noti_ref_reqtd_ids) {
		this.noti_ref_reqtd_ids = noti_ref_reqtd_ids;
	}

	public int getNoti_ref_appr_count() {
		return noti_ref_appr_count;
	}

	public void setNoti_ref_appr_count(int noti_ref_appr_count) {
		this.noti_ref_appr_count = noti_ref_appr_count;
	}

	public int getNoti_ref_reqtd_count() {
		return noti_ref_reqtd_count;
	}

	public void setNoti_ref_reqtd_count(int noti_ref_reqtd_count) {
		this.noti_ref_reqtd_count = noti_ref_reqtd_count;
	}

	public String getNoti_dis_apprd_ids() {
		return noti_dis_apprd_ids;
	}

	public void setNoti_dis_apprd_ids(String noti_dis_apprd_ids) {
		this.noti_dis_apprd_ids = noti_dis_apprd_ids;
	}

	public String getNoti_dis_reqtd_ids() {
		return noti_dis_reqtd_ids;
	}

	public void setNoti_dis_reqtd_ids(String noti_dis_reqtd_ids) {
		this.noti_dis_reqtd_ids = noti_dis_reqtd_ids;
	}

	public int getNoti_dis_apprd_count() {
		return noti_dis_apprd_count;
	}

	public void setNoti_dis_apprd_count(int noti_dis_apprd_count) {
		this.noti_dis_apprd_count = noti_dis_apprd_count;
	}

	public int getNoti_dis_reqtd_count() {
		return noti_dis_reqtd_count;
	}

	public void setNoti_dis_reqtd_count(int noti_dis_reqtd_count) {
		this.noti_dis_reqtd_count = noti_dis_reqtd_count;
	}

	public String getIpd_admission() {
		return ipd_admission;
	}

	public void setIpd_admission(String ipd_admission) {
		this.ipd_admission = ipd_admission;
	}

	public String getIpd_discharge() {
		return ipd_discharge;
	}

	public void setIpd_discharge(String ipd_discharge) {
		this.ipd_discharge = ipd_discharge;
	}

	public String getIpd_inhouse() {
		return ipd_inhouse;
	}

	public void setIpd_inhouse(String ipd_inhouse) {
		this.ipd_inhouse = ipd_inhouse;
	}

	public String getIpd_vacantbed() {
		return ipd_vacantbed;
	}

	public void setIpd_vacantbed(String ipd_vacantbed) {
		this.ipd_vacantbed = ipd_vacantbed;
	}

	public String getOpd_booked() {
		return opd_booked;
	}

	public void setOpd_booked(String opd_booked) {
		this.opd_booked = opd_booked;
	}

	public String getOpd_completed() {
		return opd_completed;
	}

	public void setOpd_completed(String opd_completed) {
		this.opd_completed = opd_completed;
	}

	public String getOpd_dna() {
		return opd_dna;
	}

	public void setOpd_dna(String opd_dna) {
		this.opd_dna = opd_dna;
	}

	public String getTotal_male() {
		return total_male;
	}

	public void setTotal_male(String total_male) {
		this.total_male = total_male;
	}

	public String getTotal_female() {
		return total_female;
	}

	public void setTotal_female(String total_female) {
		this.total_female = total_female;
	}

	public String getUpto20() {
		return upto20;
	}

	public void setUpto20(String upto20) {
		this.upto20 = upto20;
	}

	public String getUpto40() {
		return upto40;
	}

	public void setUpto40(String upto40) {
		this.upto40 = upto40;
	}

	public String getUpto60() {
		return upto60;
	}

	public void setUpto60(String upto60) {
		this.upto60 = upto60;
	}

	public String getAbove60() {
		return above60;
	}

	public void setAbove60(String above60) {
		this.above60 = above60;
	}

	public int getIndentstaus() {
		return indentstaus;
	}

	public void setIndentstaus(int indentstaus) {
		this.indentstaus = indentstaus;
	}

	public int getDiscountstatus() {
		return discountstatus;
	}

	public void setDiscountstatus(int discountstatus) {
		this.discountstatus = discountstatus;
	}

	public int getRefundstatus() {
		return refundstatus;
	}

	public void setRefundstatus(int refundstatus) {
		this.refundstatus = refundstatus;
	}

	private String month;

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
	
	private String fromdate;
	private String todate;
	private String eventname;
	private String description;
	
    private String jobtitle;	
	private String time;
	private String place;
	
	private String pagerecords;
	private int totalRecords;
	private String mastername;
	private String userImageFileName;
	
	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	private ArrayList<Master> masterlist;
	private ArrayList<DiaryManagement> jobtitleList;
	
	private ArrayList<String> timelist;
		
	private ArrayList<DiaryManagement> eventList;
	

	public ArrayList<DiaryManagement> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<DiaryManagement> eventList) {
		this.eventList = eventList;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public ArrayList<DiaryManagement> getJobtitleList() {
		return jobtitleList;
	}

	public void setJobtitleList(ArrayList<DiaryManagement> jobtitleList) {
		this.jobtitleList = jobtitleList;
	}

	public ArrayList<String> getTimelist() {
		return timelist;
	}

	public void setTimelist(ArrayList<String> timelist) {
		this.timelist = timelist;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getAmpm() {
		return ampm;
	}

	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
private String city;
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	

	public ArrayList<UserProfile> getBdaylist() {
		return bdaylist;
	}

	public void setBdaylist(ArrayList<UserProfile> bdaylist) {
		this.bdaylist = bdaylist;
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

	public int getExpirytime() {
		return expirytime;
	}

	public void setExpirytime(int expirytime) {
		this.expirytime = expirytime;
	}

	private ArrayList<UserProfile> bdaylist;
	
	private ArrayList<Bed> wardlist;

	
	private String wardid;
	private int expirytime=16;
}
