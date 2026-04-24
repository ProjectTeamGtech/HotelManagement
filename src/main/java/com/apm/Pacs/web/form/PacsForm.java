package com.apm.Pacs.web.form;

import java.io.File;
import java.util.ArrayList;

import com.apm.Emr.eu.entity.Emr;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Master.eu.entity.Master;
import com.apm.Pacs.eu.entity.Pacs;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;


public class PacsForm {
    
	private int id;
	
	private File[] fileUpload;

	private File[] files;
	
	private String[] fileUploadFileName;

	private String[] filesFileName;
	
	ArrayList<Master>invstList;
	
	private String invstid;
	
	private String selectedinvstid;
	
	private String fromDate = "";
	private String toDate = "";
	
	ArrayList<Master>modalityList;
	
	private String selectedmodality;
	
	private String searchText = "";
	
	private String searchInvstid = "";
	
	ArrayList<Pacs>pacsdataList;
	
	private String otnotes;
	
	private String clientname;
	
	private String clientid;
	
	ArrayList<Pacs>folderList;
	
	private String other_template_text;
	
	ArrayList<Pacs>templateList;
	
	private String multipacsid;
	
	
	private String title;
	private String template_text;
	 public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTemplate_text() {
		return template_text;
	}

	public void setTemplate_text(String template_text) {
		this.template_text = template_text;
	}

	public String getInvs_section_id() {
		return invs_section_id;
	}

	public void setInvs_section_id(String invs_section_id) {
		this.invs_section_id = invs_section_id;
	}
	private String invs_section_id="0";
	public String getMultipacsid() {
		return multipacsid;
	}

	public void setMultipacsid(String multipacsid) {
		this.multipacsid = multipacsid;
	}

	public ArrayList<Pacs> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(ArrayList<Pacs> templateList) {
		this.templateList = templateList;
	}

	public String getOther_template_text() {
		return other_template_text;
	}

	public void setOther_template_text(String other_template_text) {
		this.other_template_text = other_template_text;
	}

	public String getOtnotes() {
		return otnotes;
	}

	public void setOtnotes(String otnotes) {
		this.otnotes = otnotes;
	}

	public ArrayList<Pacs> getPacsdataList() {
		return pacsdataList;
	}

	public void setPacsdataList(ArrayList<Pacs> pacsdataList) {
		this.pacsdataList = pacsdataList;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSelectedmodality() {
		return selectedmodality;
	}

	public void setSelectedmodality(String selectedmodality) {
		this.selectedmodality = selectedmodality;
	}

	public ArrayList<Master> getModalityList() {
		return modalityList;
	}

	public void setModalityList(ArrayList<Master> modalityList) {
		this.modalityList = modalityList;
	}

	public String getInvstid() {
		return invstid;
	}

	public void setInvstid(String invstid) {
		this.invstid = invstid;
	}

	public ArrayList<Master> getInvstList() {
		return invstList;
	}

	public void setInvstList(ArrayList<Master> invstList) {
		this.invstList = invstList;
	}

	public File[] getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File[] fileUpload) {
		this.fileUpload = fileUpload;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String[] fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String getSelectedinvstid() {
		return selectedinvstid;
	}

	public void setSelectedinvstid(String selectedinvstid) {
		this.selectedinvstid = selectedinvstid;
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

	public String getSearchInvstid() {
		return searchInvstid;
	}

	public void setSearchInvstid(String searchInvstid) {
		this.searchInvstid = searchInvstid;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public ArrayList<Pacs> getFolderList() {
		return folderList;
	}

	public void setFolderList(ArrayList<Pacs> folderList) {
		this.folderList = folderList;
	}
	private String abrivationid;


	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}
	
	private ArrayList<Emr> docList;
	private String invsttype;
    private String jobtitle;
	private String requested_uerid;
	private String investtemp;
	private ArrayList<Investigation>selectedInvstList;
	private String reportType;
	private String ipdid;
	private String wardname;
	private String bedname;
	private boolean daycare;
	private String tpid="0";
	private String thirdPartyCompanyName;
	private String tptype;
	 private String fullname;
	 private String ageandgender;
	 private String clientId;
	 private String dateTime;
	 private String gender;
	 private String update_date="";
	 private String complete_date="";
	 private String collect_date;
	 private String remark;
	 private String practitionerName;
	 private String department;
	 private String practDepartment;
	 private String diaryUser = "";
	 private String qualification;
	private String specialization="0";
	private String pathLabuser;
	private String clinicName;
	private String clinicOwner;
	private String clinicemail;
	private String clinicaddress;
	private String clinicity;
	private String websiteUrl;
	private String landLine;
	private String owner_qualification;
	private ArrayList<Clinic> locationAdressList;
	private String address;
	private String clinicLogo;
	private String userid;
	private String mobile;
	ArrayList<Master>masterInvstTypeList;
	private String sectionName;
	private String invreq;
	private String labname;
	private String creport;
	private String labuserid;
	private String approve_status;
	private String printbr;
	private String reqspecialization="0";
	private String reqdiaryUser = "";
	private String reqqualification;
	private String userImageFileName;
	private ArrayList<UserProfile> allInvsUserList;
	private boolean bghsts;
	ArrayList<Master>departmentInvList;
	
	public ArrayList<UserProfile> getAllInvsUserList() {
		return allInvsUserList;
	}

	public void setAllInvsUserList(ArrayList<UserProfile> allInvsUserList) {
		this.allInvsUserList = allInvsUserList;
	}

	public boolean isBghsts() {
		return bghsts;
	}

	public void setBghsts(boolean bghsts) {
		this.bghsts = bghsts;
	}

	public ArrayList<Master> getDepartmentInvList() {
		return departmentInvList;
	}

	public void setDepartmentInvList(ArrayList<Master> departmentInvList) {
		this.departmentInvList = departmentInvList;
	}

	public String getLabuserid() {
		return labuserid;
	}

	public void setLabuserid(String labuserid) {
		this.labuserid = labuserid;
	}

	public String getApprove_status() {
		return approve_status;
	}

	public void setApprove_status(String approve_status) {
		this.approve_status = approve_status;
	}

	public String getPrintbr() {
		return printbr;
	}

	public void setPrintbr(String printbr) {
		this.printbr = printbr;
	}

	public String getReqspecialization() {
		return reqspecialization;
	}

	public void setReqspecialization(String reqspecialization) {
		this.reqspecialization = reqspecialization;
	}

	public String getReqdiaryUser() {
		return reqdiaryUser;
	}

	public void setReqdiaryUser(String reqdiaryUser) {
		this.reqdiaryUser = reqdiaryUser;
	}

	public String getReqqualification() {
		return reqqualification;
	}

	public void setReqqualification(String reqqualification) {
		this.reqqualification = reqqualification;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public ArrayList<Master> getMasterInvstTypeList() {
		return masterInvstTypeList;
	}

	public void setMasterInvstTypeList(ArrayList<Master> masterInvstTypeList) {
		this.masterInvstTypeList = masterInvstTypeList;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getInvreq() {
		return invreq;
	}

	public void setInvreq(String invreq) {
		this.invreq = invreq;
	}

	public String getLabname() {
		return labname;
	}

	public void setLabname(String labname) {
		this.labname = labname;
	}

	public String getCreport() {
		return creport;
	}

	public void setCreport(String creport) {
		this.creport = creport;
	}

	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}

	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
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

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPractDepartment() {
		return practDepartment;
	}

	public void setPractDepartment(String practDepartment) {
		this.practDepartment = practDepartment;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getPathLabuser() {
		return pathLabuser;
	}

	public void setPathLabuser(String pathLabuser) {
		this.pathLabuser = pathLabuser;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAgeandgender() {
		return ageandgender;
	}

	public void setAgeandgender(String ageandgender) {
		this.ageandgender = ageandgender;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getComplete_date() {
		return complete_date;
	}

	public void setComplete_date(String complete_date) {
		this.complete_date = complete_date;
	}

	public String getCollect_date() {
		return collect_date;
	}

	public void setCollect_date(String collect_date) {
		this.collect_date = collect_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getThirdPartyCompanyName() {
		return thirdPartyCompanyName;
	}

	public void setThirdPartyCompanyName(String thirdPartyCompanyName) {
		this.thirdPartyCompanyName = thirdPartyCompanyName;
	}

	public String getTptype() {
		return tptype;
	}

	public void setTptype(String tptype) {
		this.tptype = tptype;
	}

	public boolean isDaycare() {
		return daycare;
	}

	public void setDaycare(boolean daycare) {
		this.daycare = daycare;
	}

	public String getTpid() {
		return tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public String getWardname() {
		return wardname;
	}

	public void setWardname(String wardname) {
		this.wardname = wardname;
	}

	public String getBedname() {
		return bedname;
	}

	public void setBedname(String bedname) {
		this.bedname = bedname;
	}

	public ArrayList<Investigation> getSelectedInvstList() {
		return selectedInvstList;
	}

	public void setSelectedInvstList(ArrayList<Investigation> selectedInvstList) {
		this.selectedInvstList = selectedInvstList;
	}

	public String getInvesttemp() {
		return investtemp;
	}

	public void setInvesttemp(String investtemp) {
		this.investtemp = investtemp;
	}

	public String getRequested_uerid() {
		return requested_uerid;
	}

	public void setRequested_uerid(String requested_uerid) {
		this.requested_uerid = requested_uerid;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getInvsttype() {
		return invsttype;
	}

	public void setInvsttype(String invsttype) {
		this.invsttype = invsttype;
	}

	public ArrayList<Emr> getDocList() {
		return docList;
	}

	public void setDocList(ArrayList<Emr> docList) {
		this.docList = docList;
	}
	private String dicid;


	public String getDicid() {
		return dicid;
	}

	public void setDicid(String dicid) {
		this.dicid = dicid;
	}
}
