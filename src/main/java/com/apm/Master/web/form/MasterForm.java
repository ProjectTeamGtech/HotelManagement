package com.apm.Master.web.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Book;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.entity.EmailTemplate;

public class MasterForm {
	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getDeclarationtitle() {
		return declarationtitle;
	}

	public void setDeclarationtitle(String declarationtitle) {
		this.declarationtitle = declarationtitle;
	}

	public ArrayList<Master> getDeclarationlist() {
		return declarationlist;
	}

	public void setDeclarationlist(ArrayList<Master> declarationlist) {
		this.declarationlist = declarationlist;
	}

	public ArrayList<Master> getPayrolltemplatelist() {
		return payrolltemplatelist;
	}

	public void setPayrolltemplatelist(ArrayList<Master> payrolltemplatelist) {
		this.payrolltemplatelist = payrolltemplatelist;
	}
	ArrayList<State> statelist;
	ArrayList<Master> termConditionList;
	private Collection<Master> subtask_name;
	/*private Collection<Master> subtask_name;*/
	ArrayList<Master>nursingdiagnosislist;
	 private String planningid;
	 private String intervention_name;
	 private ArrayList<Master> sharablechargelist;
	 private  ArrayList<UserProfile> visitingConsultDoctors;
	 private String sms_type;
	 private String sms_itype;
	 private String sms_typename;
	 private String sms_itypename;
	 private ArrayList<Master>invsTypeList;
	 private ArrayList<Investigation> outsourcevendorlist;
	 private Collection<Master> outsourcerate;
	 private String vendor;
	 private String invsttype;
	 private String showdeptlist;
	 private ArrayList<ThirdParty> thirdPartyDetailsList;
	 private ArrayList<ThirdParty> thirdPartyTypeList;
	 private ArrayList<ThirdParty> tpnameList;
	 private ArrayList<Master> masterChargeList;
	 private String workorderterms;
	 private String empid;
	 private String clinicName;
	 private String clinicOwner,clinicOwner1;
	 private String clinicemail;
	 private String clinicLogo;
	 public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
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

	public String getClinicOwner1() {
		return clinicOwner1;
	}

	public void setClinicOwner1(String clinicOwner1) {
		this.clinicOwner1 = clinicOwner1;
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

	public String getOwner_qualification1() {
		return owner_qualification1;
	}

	public void setOwner_qualification1(String owner_qualification1) {
		this.owner_qualification1 = owner_qualification1;
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
	private String clinicaddress;
	 private String clinicity;
	 private String websiteUrl;
	 private String landLine;
	 private String owner_qualification,owner_qualification1;
	 private ArrayList<Clinic> locationAdressList;
	 private String address;
	public String getWorkorderterms() {
		return workorderterms;
	}

	public void setWorkorderterms(String workorderterms) {
		this.workorderterms = workorderterms;
	}

	public ArrayList<Master> getMasterChargeList() {
		return masterChargeList;
	}

	public void setMasterChargeList(ArrayList<Master> masterChargeList) {
		this.masterChargeList = masterChargeList;
	}

	public ArrayList<ThirdParty> getTpnameList() {
		return tpnameList;
	}

	public void setTpnameList(ArrayList<ThirdParty> tpnameList) {
		this.tpnameList = tpnameList;
	}

	public ArrayList<ThirdParty> getThirdPartyTypeList() {
		return thirdPartyTypeList;
	}

	public void setThirdPartyTypeList(ArrayList<ThirdParty> thirdPartyTypeList) {
		this.thirdPartyTypeList = thirdPartyTypeList;
	}

	public ArrayList<ThirdParty> getThirdPartyDetailsList() {
		return thirdPartyDetailsList;
	}

	public void setThirdPartyDetailsList(ArrayList<ThirdParty> thirdPartyDetailsList) {
		this.thirdPartyDetailsList = thirdPartyDetailsList;
	}

	public String getSms_typename() {
		return sms_typename;
	}

	public void setSms_typename(String sms_typename) {
		this.sms_typename = sms_typename;
	}

	public String getSms_itypename() {
		return sms_itypename;
	}

	public void setSms_itypename(String sms_itypename) {
		this.sms_itypename = sms_itypename;
	}

	public String getSms_type() {
		return sms_type;
	}

	public void setSms_type(String sms_type) {
		this.sms_type = sms_type;
	}

	public String getSms_itype() {
		return sms_itype;
	}

	public void setSms_itype(String sms_itype) {
		this.sms_itype = sms_itype;
	}
	private ArrayList<Master> smsitypelist;
	private ArrayList<Master> smstypelist;
	 public ArrayList<Master> getSmsitypelist() {
		return smsitypelist;
	}

	public void setSmsitypelist(ArrayList<Master> smsitypelist) {
		this.smsitypelist = smsitypelist;
	}

	public ArrayList<Master> getSmstypelist() {
		return smstypelist;
	}

	public void setSmstypelist(ArrayList<Master> smstypelist) {
		this.smstypelist = smstypelist;
	}

	public ArrayList<UserProfile> getVisitingConsultDoctors() {
		return visitingConsultDoctors;
	}

	public void setVisitingConsultDoctors(ArrayList<UserProfile> visitingConsultDoctors) {
		this.visitingConsultDoctors = visitingConsultDoctors;
	}

	public ArrayList<Master> getSharablechargelist() {
		return sharablechargelist;
	}

	public void setSharablechargelist(ArrayList<Master> sharablechargelist) {
		this.sharablechargelist = sharablechargelist;
	}

	public String getPlanningid() {
	  return planningid;
	 }

	 public void setPlanningid(String planningid) {
	  this.planningid = planningid;
	 }

	 public String getIntervention_name() {
	  return intervention_name;
	 }

	 public void setIntervention_name(String intervention_name) {
	  this.intervention_name = intervention_name;
	 }
	 private String nursing_diagnosis;
	 private String nursing_planning;
	 private String diagnosisname;
	 
	 ArrayList<Master>nursinginterventionlist;
	 private String nursing_intervention;
	 
	 public String getNursing_intervention() {
		return nursing_intervention;
	}

	public void setNursing_intervention(String nursing_intervention) {
		this.nursing_intervention = nursing_intervention;
	}

	public ArrayList<Master> getNursinginterventionlist() {
	  return nursinginterventionlist;
	 }

	 public void setNursinginterventionlist(ArrayList<Master> nursinginterventionlist) {
	  this.nursinginterventionlist = nursinginterventionlist;
	 }

	 public String getDiagnosisname() {
	  return diagnosisname;
	 }

	 public void setDiagnosisname(String diagnosisname) {
	  this.diagnosisname = diagnosisname;
	 }

	 public String getNursing_planning() {
	  return nursing_planning;
	 }

	 public void setNursing_planning(String nursing_planning) {
	  this.nursing_planning = nursing_planning;
	 }
	 ArrayList<Master>nursingplanninglist;
	 public ArrayList<Master> getNursingplanninglist() {
	  return nursingplanninglist;
	 }

	 public void setNursingplanninglist(ArrayList<Master> nursingplanninglist) {
	  this.nursingplanninglist = nursingplanninglist;
	 }

	 public String getNursing_diagnosis() {
	  return nursing_diagnosis;
	 }

	 public void setNursing_diagnosis(String nursing_diagnosis) {
	  this.nursing_diagnosis = nursing_diagnosis;
	 }

	 public ArrayList<Master> getNursingdiagnosislist() {
	  return nursingdiagnosislist;
	 }

	 public void setNursingdiagnosislist(ArrayList<Master> nursingdiagnosislist) {
	  this.nursingdiagnosislist = nursingdiagnosislist;
	 }
	 // Adarsh
	private String searchTextIPD;
	public Collection<Master> getSubtask_name() {
		return subtask_name;
	}
	
	public void setSubtask_name(Collection<Master> subtask_name) {
		this.subtask_name = subtask_name;
	}
	ArrayList<UserProfile> practionerlist;

	public ArrayList<UserProfile> getPractionerlist() {
		return practionerlist;
	}

	public void setPractionerlist(ArrayList<UserProfile> practionerlist) {
		this.practionerlist = practionerlist;
	}
	private String date;
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	ArrayList<String> newjobTitleList;
	public ArrayList<String> getNewjobTitleList() {
		return newjobTitleList;
	}

	public void setNewjobTitleList(ArrayList<String> newjobTitleList) {
		this.newjobTitleList = newjobTitleList;
	}
	ArrayList<Master>schedulerlist ;
	public ArrayList<Master> getSchedulerlist() {
		return schedulerlist;
	}

	public void setSchedulerlist(ArrayList<Master> schedulerlist) {
		this.schedulerlist = schedulerlist;
	}
	ArrayList<Master>schedulersubtasklist;
	public ArrayList<Master> getSchedulersubtasklist() {
		return schedulersubtasklist;
	}

	public void setSchedulersubtasklist(ArrayList<Master> schedulersubtasklist) {
		this.schedulersubtasklist = schedulersubtasklist;
	}
	private String area;
	private String floor;
	private String room_no;
	private String searchText;
	private int id;
	private String visitingConsultant;
	private String qualification;
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getVisitingConsultant() {
		return visitingConsultant;
	}

	public void setVisitingConsultant(String visitingConsultant) {
		this.visitingConsultant = visitingConsultant;
	}

	private String mobileNo;
	private String email;
	private String surgeon;
	private String anesthesiologist;
	private String fees;
	private String referred;
	private String mlc;
	private ArrayList<Master> genericnamelist;
	
	
	public ArrayList<Master> getGenericnamelist() {
		return genericnamelist;
	}

	public void setGenericnamelist(ArrayList<Master> genericnamelist) {
		this.genericnamelist = genericnamelist;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}

	public String getAnesthesiologist() {
		return anesthesiologist;
	}

	public void setAnesthesiologist(String anesthesiologist) {
		this.anesthesiologist = anesthesiologist;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getReferred() {
		return referred;
	}

	public void setReferred(String referred) {
		this.referred = referred;
	}

	public String getMlc() {
		return mlc;
	}

	public void setMlc(String mlc) {
		this.mlc = mlc;
	}

	private String parentid;
	private String subtask;
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getSubtask() {
		return subtask;
	}

	public void setSubtask(String subtask) {
		this.subtask = subtask;
	}
	private String occupation;
	private String jobTitle;
	private String pagerecords;
	private int totalRecords;
	
	private String reference;
	private ArrayList<Master> vitalMasterList;
	private ArrayList<Master> vitalTypeList;
	ArrayList<Master> occupationList;
	ArrayList<Master> jobTitleList;
	ArrayList<Master> referenceList;
	private String message;

	private String specialization;
	private String description;
	ArrayList<Master> specilizationList;
	private String datetime;
	private String name;
	private String masters;
	ArrayList<Master> ipdformfieldList;
	private String discipline_id;
	private int tpid;
	
	 private java.io.File fileName;
	 private String smsTemplate;
	 private String smstxt;
	 private String subject;
	 private ArrayList<ThirdParty> tpmainList;
	 private ArrayList<ThirdParty> tpsubList;
	 
	 ArrayList<Master> ipd_templateList;
	 ArrayList<Master> ipd_template_names;
	 private ArrayList<Master> listContacts;
	 private String vital_type;
		private String vital_type_name;
	 private String numbers;
	 private ArrayList<Master> newChargeTypeList;
	 private String fields;
	
	private ArrayList<Master> godownList;
	 private ArrayList<EmailTemplate> smsTemplateList;
	 ArrayList<Master> ipdformsettingList;
	 private File userFile;
	 private String userFileFileName;
	 private String userFileContentType;
	 private ArrayList<UserProfile> userProfileList;
	
	private String mastername;
	private String jobtitlegroup;
	private String jobtitlegroup_id;
	private ArrayList<Master> jobtitlegropulist;
	
	private boolean procedure;
	
	ArrayList<Master> specializationList;
	ArrayList<Master> notAvailableReasonList;
	ArrayList<Master> dischargeStatusList;

	private ArrayList<Master> masterlist;

	private String title;
	private String other_template_text;
    private String template_text;
	
	private String template_nameid;
	ArrayList<Master> expececategorylist;
	
	private String wardid;
	private String charge;
	
	private ArrayList<Master> standardChargesList;
	private ArrayList<Bed> wardList;
	private String text;
	
	private Collection<Master> standardcharges;
	private ArrayList<Master> masterChageTypeList;
	private String payby;
	
	ArrayList<String> paybyList;
	private ArrayList<Master> nabhsubcatagorylist;
	private ArrayList<Master> nabhcatagorylist;
	private String catagoryid;
	private ArrayList<Master> arealist;
	private String subcatagoryid;
	private ArrayList<String> departmentList;
	private String department;
	private ArrayList<Master> prisctemplatelist;
	public ArrayList<Master> getPrisctemplatelist() {
		return prisctemplatelist;
	}

	public void setPrisctemplatelist(ArrayList<Master> prisctemplatelist) {
		this.prisctemplatelist = prisctemplatelist;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ArrayList<String> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(ArrayList<String> departmentList) {
		this.departmentList = departmentList;
	}

	public String getSubcatagoryid() {
		return subcatagoryid;
	}

	public void setSubcatagoryid(String subcatagoryid) {
		this.subcatagoryid = subcatagoryid;
	}

	public ArrayList<Master> getArealist() {
		return arealist;
	}

	public void setArealist(ArrayList<Master> arealist) {
		this.arealist = arealist;
	}

	public String getCatagoryid() {
		return catagoryid;
	}

	public void setCatagoryid(String catagoryid) {
		this.catagoryid = catagoryid;
	}

	public ArrayList<Master> getNabhcatagorylist() {
		return nabhcatagorylist;
	}

	public void setNabhcatagorylist(ArrayList<Master> nabhcatagorylist) {
		this.nabhcatagorylist = nabhcatagorylist;
	}

	public ArrayList<Master> getNabhsubcatagorylist() {
		return nabhsubcatagorylist;
	}

	public void setNabhsubcatagorylist(ArrayList<Master> nabhsubcatagorylist) {
		this.nabhsubcatagorylist = nabhsubcatagorylist;
	}

	public ArrayList<String> getPaybyList() {
		return paybyList;
	}

	public void setPaybyList(ArrayList<String> paybyList) {
		this.paybyList = paybyList;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public ArrayList<Master> getMasterChageTypeList() {
		return masterChageTypeList;
	}

	public void setMasterChageTypeList(ArrayList<Master> masterChageTypeList) {
		this.masterChageTypeList = masterChageTypeList;
	}

	public Collection<Master> getStandardcharges() {
		return standardcharges;
	}

	public void setStandardcharges(Collection<Master> standardcharges) {
		this.standardcharges = standardcharges;
	}

	public ArrayList<Bed> getWardList() {
		return wardList;
	}

	public void setWardList(ArrayList<Bed> wardList) {
		this.wardList = wardList;
	}

	public ArrayList<Master> getStandardChargesList() {
		return standardChargesList;
	}

	public void setStandardChargesList(ArrayList<Master> standardChargesList) {
		this.standardChargesList = standardChargesList;
	}

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public ArrayList<Master> getExpececategorylist() {
		return expececategorylist;
	}

	public void setExpececategorylist(ArrayList<Master> expececategorylist) {
		this.expececategorylist = expececategorylist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOther_template_text() {
		return other_template_text;
	}

	public void setOther_template_text(String other_template_text) {
		this.other_template_text = other_template_text;
	}

	ArrayList<Master> new_chargetye_list;

	private ArrayList<Master> otherTemplateList;

	public ArrayList<Master> getOtherTemplateList() {
		return otherTemplateList;
	}

	public void setOtherTemplateList(ArrayList<Master> otherTemplateList) {
		this.otherTemplateList = otherTemplateList;
	}

	public ArrayList<Master> getNew_chargetye_list() {
		return new_chargetye_list;
	}

	public void setNew_chargetye_list(ArrayList<Master> new_chargetye_list) {
		this.new_chargetye_list = new_chargetye_list;
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

	public String getMasters() {
		return masters;
	}

	public void setMasters(String masters) {
		this.masters = masters;
	}

	public ArrayList<Master> getDischargeStatusList() {
		return dischargeStatusList;
	}

	public void setDischargeStatusList(ArrayList<Master> dischargeStatusList) {
		this.dischargeStatusList = dischargeStatusList;
	}

	ArrayList<Master> dischargeOutcomeList;

	public ArrayList<Master> getDischargeOutcomeList() {
		return dischargeOutcomeList;
	}

	public void setDischargeOutcomeList(ArrayList<Master> dischargeOutcomeList) {
		this.dischargeOutcomeList = dischargeOutcomeList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String discipline;

	ArrayList<Master> disciplineList;

	ArrayList<Master> nursingcategorylist;
	Collection<Master> nursing_category;
	
	
	private String taskname;
    private String notes;
    private String frequency;

    Collection<Master> nursing_details;
    private String nursingtype_id;
    
    
    private String clientid;
    private String ipdid;
    private String conditionid;
    private String practitionerid;
    
    
    
   private  ArrayList<Master> nursingdetails;
   
   
   
   
	public ArrayList<Master> getNursingdetails() {
	return nursingdetails;
}

public void setNursingdetails(ArrayList<Master> nursingdetails) {
	this.nursingdetails = nursingdetails;
}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Collection<Master> getNursing_category() {
		return nursing_category;
	}

	public void setNursing_category(Collection<Master> nursing_category) {
		this.nursing_category = nursing_category;
	}

	public ArrayList<Master> getNursingcategorylist() {
		return nursingcategorylist;
	}

	public void setNursingcategorylist(ArrayList<Master> nursingcategorylist) {
		this.nursingcategorylist = nursingcategorylist;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public ArrayList<Master> getSpecializationList() {
		return specializationList;
	}

	public void setSpecializationList(ArrayList<Master> specializationList) {
		this.specializationList = specializationList;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDatetime() {
		return datetime;
	}

	public ArrayList<Master> getNotAvailableReasonList() {
		return notAvailableReasonList;
	}

	public void setNotAvailableReasonList(
			ArrayList<Master> notAvailableReasonList) {
		this.notAvailableReasonList = notAvailableReasonList;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<Master> getReferenceList() {
		return referenceList;
	}

	public void setReferenceList(ArrayList<Master> referenceList) {
		this.referenceList = referenceList;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

	public ArrayList<Master> getOccupationList() {
		return occupationList;
	}

	public void setOccupationList(ArrayList<Master> occupationList) {
		this.occupationList = occupationList;
	}

	public ArrayList<Master> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(ArrayList<Master> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}

	public Collection<Master> getNursing_details() {
		return nursing_details;
	}

	public void setNursing_details(Collection<Master> nursing_details) {
		this.nursing_details = nursing_details;
	}

	public String getNursingtype_id() {
		return nursingtype_id;
	}

	public void setNursingtype_id(String nursingtype_id) {
		this.nursingtype_id = nursingtype_id;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public String getConditionid() {
		return conditionid;
	}

	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}

	public String getPractitionerid() {
		return practitionerid;
	}

	public void setPractitionerid(String practitionerid) {
		this.practitionerid = practitionerid;
	}

	public String getDiscipline_id() {
		return discipline_id;
	}

	public void setDiscipline_id(String discipline_id) {
		this.discipline_id = discipline_id;
	}

	public String getJobtitlegroup() {
		return jobtitlegroup;
	}

	public void setJobtitlegroup(String jobtitlegroup) {
		this.jobtitlegroup = jobtitlegroup;
	}

	public String getJobtitlegroup_id() {
		return jobtitlegroup_id;
	}

	public void setJobtitlegroup_id(String jobtitlegroup_id) {
		this.jobtitlegroup_id = jobtitlegroup_id;
	}

	public ArrayList<Master> getJobtitlegropulist() {
		return jobtitlegropulist;
	}

	public void setJobtitlegropulist(ArrayList<Master> jobtitlegropulist) {
		this.jobtitlegropulist = jobtitlegropulist;
	}

	public ArrayList<Master> getGodownList() {
		return godownList;
	}

	public void setGodownList(ArrayList<Master> godownList) {
		this.godownList = godownList;
	}

	public String getSmsTemplate() {
		return smsTemplate;
	}

	public void setSmsTemplate(String smsTemplate) {
		this.smsTemplate = smsTemplate;
	}

	public ArrayList<EmailTemplate> getSmsTemplateList() {
		return smsTemplateList;
	}

	public void setSmsTemplateList(ArrayList<EmailTemplate> smsTemplateList) {
		this.smsTemplateList = smsTemplateList;
	}

	public java.io.File getFileName() {
		return fileName;
	}

	public void setFileName(java.io.File fileName) {
		this.fileName = fileName;
	}

	public String getSmstxt() {
		return smstxt;
	}

	public void setSmstxt(String smstxt) {
		this.smstxt = smstxt;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public String getUserFileFileName() {
		return userFileFileName;
	}

	public void setUserFileFileName(String userFileFileName) {
		this.userFileFileName = userFileFileName;
	}

	public String getUserFileContentType() {
		return userFileContentType;
	}

	public void setUserFileContentType(String userFileContentType) {
		this.userFileContentType = userFileContentType;
	}

	public ArrayList<Master> getListContacts() {
		return listContacts;
	}

	public void setListContacts(ArrayList<Master> listContacts) {
		this.listContacts = listContacts;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public ArrayList<Master> getIpd_templateList() {
		return ipd_templateList;
	}

	public void setIpd_templateList(ArrayList<Master> ipd_templateList) {
		this.ipd_templateList = ipd_templateList;
	}

	public ArrayList<Master> getIpd_template_names() {
		return ipd_template_names;
	}

	public void setIpd_template_names(ArrayList<Master> ipd_template_names) {
		this.ipd_template_names = ipd_template_names;
	}

	public String getTemplate_text() {
		return template_text;
	}

	public void setTemplate_text(String template_text) {
		this.template_text = template_text;
	}

	public String getTemplate_nameid() {
		return template_nameid;
	}

	public void setTemplate_nameid(String template_nameid) {
		this.template_nameid = template_nameid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<Master> getNewChargeTypeList() {
		return newChargeTypeList;
	}

	public void setNewChargeTypeList(ArrayList<Master> newChargeTypeList) {
		this.newChargeTypeList = newChargeTypeList;
	}

	public ArrayList<UserProfile> getUserProfileList() {
		return userProfileList;
	}

	public void setUserProfileList(ArrayList<UserProfile> userProfileList) {
		this.userProfileList = userProfileList;
	}

	public boolean isProcedure() {
		return procedure;
	}

	public void setProcedure(boolean procedure) {
		this.procedure = procedure;
	}

	public ArrayList<ThirdParty> getTpmainList() {
		return tpmainList;
	}

	public void setTpmainList(ArrayList<ThirdParty> tpmainList) {
		this.tpmainList = tpmainList;
	}

	public ArrayList<ThirdParty> getTpsubList() {
		return tpsubList;
	}

	public void setTpsubList(ArrayList<ThirdParty> tpsubList) {
		this.tpsubList = tpsubList;
	}

	public int getTpid() {
		return tpid;
	}

	public void setTpid(int tpid) {
		this.tpid = tpid;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public ArrayList<Master> getIpdformfieldList() {
		return ipdformfieldList;
	}

	public void setIpdformfieldList(ArrayList<Master> ipdformfieldList) {
		this.ipdformfieldList = ipdformfieldList;
	}

	public ArrayList<Master> getSpecilizationList() {
		return specilizationList;
	}

	public void setSpecilizationList(ArrayList<Master> specilizationList) {
		this.specilizationList = specilizationList;
	}

	public ArrayList<Master> getIpdformsettingList() {
		return ipdformsettingList;
	}

	public void setIpdformsettingList(ArrayList<Master> ipdformsettingList) {
		this.ipdformsettingList = ipdformsettingList;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom_no() {
		return room_no;
	}

	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}

	public String getSearchTextIPD() {
		return searchTextIPD;
	}

	public void setSearchTextIPD(String searchTextIPD) {
		this.searchTextIPD = searchTextIPD;
	}
	public ArrayList<State> getStatelist() {
		return statelist;
	}

	public void setStatelist(ArrayList<State> statelist) {
		this.statelist = statelist;
	}

	public ArrayList<Master> getTermConditionList() {
		return termConditionList;
	}

	public void setTermConditionList(ArrayList<Master> termConditionList) {
		this.termConditionList = termConditionList;
	}

	public ArrayList<Master> getVitalMasterList() {
		return vitalMasterList;
	}

	public void setVitalMasterList(ArrayList<Master> vitalMasterList) {
		this.vitalMasterList = vitalMasterList;
	}

	public ArrayList<Master> getVitalTypeList() {
		return vitalTypeList;
	}

	public void setVitalTypeList(ArrayList<Master> vitalTypeList) {
		this.vitalTypeList = vitalTypeList;
	}

	public String getVital_type() {
		return vital_type;
	}

	public void setVital_type(String vital_type) {
		this.vital_type = vital_type;
	}

	public String getVital_type_name() {
		return vital_type_name;
	}

	public void setVital_type_name(String vital_type_name) {
		this.vital_type_name = vital_type_name;
	}
	
	private int book_chapter_id;
	private int book_chapter_parentid;
	private String book_chapter_name;
	private String book_chapter_link;
	private String book_chapter_discription;
	public int getBook_chapter_id() {
		return book_chapter_id;
	}

	public void setBook_chapter_id(int book_chapter_id) {
		this.book_chapter_id = book_chapter_id;
	}

	public int getBook_chapter_parentid() {
		return book_chapter_parentid;
	}

	public void setBook_chapter_parentid(int book_chapter_parentid) {
		this.book_chapter_parentid = book_chapter_parentid;
	}

	public String getBook_chapter_name() {
		return book_chapter_name;
	}

	public void setBook_chapter_name(String book_chapter_name) {
		this.book_chapter_name = book_chapter_name;
	}

	public String getBook_chapter_link() {
		return book_chapter_link;
	}

	public void setBook_chapter_link(String book_chapter_link) {
		this.book_chapter_link = book_chapter_link;
	}

	public String getBook_chapter_discription() {
		return book_chapter_discription;
	}

	public void setBook_chapter_discription(String book_chapter_discription) {
		this.book_chapter_discription = book_chapter_discription;
	}
	public ArrayList<Master> getBookChapterlist() {
		return bookChapterlist;
	}

	public void setBookChapterlist(ArrayList<Master> bookChapterlist) {
		this.bookChapterlist = bookChapterlist;
	}
	public ArrayList<Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(ArrayList<Book> booklist) {
		this.booklist = booklist;
	}
	public ArrayList<Master> getOutsourcelist() {
		return outsourcelist;
	}

	public void setOutsourcelist(ArrayList<Master> outsourcelist) {
		this.outsourcelist = outsourcelist;
	}
	public int getOutsource_id() {
		return outsource_id;
	}

	public void setOutsource_id(int outsource_id) {
		this.outsource_id = outsource_id;
	}
	public String getOutsource_name() {
		return outsource_name;
	}

	public void setOutsource_name(String outsource_name) {
		this.outsource_name = outsource_name;
	}
	
	public ArrayList<Master> getOutsourceDatalist() {
		return outsourceDatalist;
	}

	public void setOutsourceDatalist(ArrayList<Master> outsourceDatalist) {
		this.outsourceDatalist = outsourceDatalist;
	}
	private ArrayList<Master> bookChapterlist;
	private ArrayList<Book> booklist;
	private ArrayList<Master> outsourcelist;
	private int outsource_id;
	private String outsource_name;
	private ArrayList<Master> outsourceDatalist; 
	private int outsource_data_id;
	private String outsource_data_name;

	public int getOutsource_data_id() {
		return outsource_data_id;
	}

	public void setOutsource_data_id(int outsource_data_id) {
		this.outsource_data_id = outsource_data_id;
	}

	public String getOutsource_data_name() {
		return outsource_data_name;
	}

	public void setOutsource_data_name(String outsource_data_name) {
		this.outsource_data_name = outsource_data_name;
	}

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}

	public String getSharing_type() {
		return sharing_type;
	}

	public void setSharing_type(String sharing_type) {
		this.sharing_type = sharing_type;
	}

	public String getInvestigstion_id() {
		return investigstion_id;
	}

	public void setInvestigstion_id(String investigstion_id) {
		this.investigstion_id = investigstion_id;
	}

	public String getInvestigation_name() {
		return investigation_name;
	}

	public void setInvestigation_name(String investigation_name) {
		this.investigation_name = investigation_name;
	}

	public ArrayList<Master> getInvestigationlist() {
		return investigationlist;
	}

	public void setInvestigationlist(ArrayList<Master> investigationlist) {
		this.investigationlist = investigationlist;
	}


	public ArrayList<Priscription> getTemplatelist() {
		return templatelist;
	}

	public void setTemplatelist(ArrayList<Priscription> templatelist) {
		this.templatelist = templatelist;
	}
	public String getShowall() {
		return showall;
	}

	public void setShowall(String showall) {
		this.showall = showall;
	}
	public String getTreatmenttype() {
		return treatmenttype;
	}

	public void setTreatmenttype(String treatmenttype) {
		this.treatmenttype = treatmenttype;
	}
	public ArrayList<Client> getFeedbacklist() {
		return feedbacklist;
	}

	public void setFeedbacklist(ArrayList<Client> feedbacklist) {
		this.feedbacklist = feedbacklist;
	}
	public String getIsipd() {
		return isipd;
	}

	public void setIsipd(String isipd) {
		this.isipd = isipd;
	}
	public String getIsopd() {
		return isopd;
	}

	public void setIsopd(String isopd) {
		this.isopd = isopd;
	}
	private String ammount ;
	private String sharing_type;
	private String investigstion_id;
	private String investigation_name;
	private ArrayList<Master> investigationlist;
	private ArrayList<Priscription>templatelist ;
	private String showall;
	private String treatmenttype;
	private ArrayList<Client> feedbacklist;
	private String isipd,isopd;
	
	

	//lokesh  8/1/18
			private String vacinname,vacine_dependson,vacine_iscompulsary,vacine_excludes,vacine_parent,vacine_info;

			public String getVacinname() {
				return vacinname;
			}

			public void setVacinname(String vacinname) {
				this.vacinname = vacinname;
			}

			public String getVacine_dependson() {
				return vacine_dependson;
			}

			public void setVacine_dependson(String vacine_dependson) {
				this.vacine_dependson = vacine_dependson;
			}

			public String getVacine_iscompulsary() {
				return vacine_iscompulsary;
			}

			public void setVacine_iscompulsary(String vacine_iscompulsary) {
				this.vacine_iscompulsary = vacine_iscompulsary;
			}

			public String getVacine_excludes() {
				return vacine_excludes;
			}

			public void setVacine_excludes(String vacine_excludes) {
				this.vacine_excludes = vacine_excludes;
			}

			public String getVacine_parent() {
				return vacine_parent;
			}

			public void setVacine_parent(String vacine_parent) {
				this.vacine_parent = vacine_parent;
			}

			public String getVacine_info() {
				return vacine_info;
			}

			public void setVacine_info(String vacine_info) {
				this.vacine_info = vacine_info;
			}
	public ArrayList<Master> getVacinationlist() {
				return vacinationlist;
			}

			public void setVacinationlist(ArrayList<Master> vacinationlist) {
				this.vacinationlist = vacinationlist;
			}
	public ArrayList<Master> getParentvacinationlist() {
				return parentvacinationlist;
			}

			public void setParentvacinationlist(ArrayList<Master> parentvacinationlist) {
				this.parentvacinationlist = parentvacinationlist;
			}
	public String getScheduleon() {
				return scheduleon;
			}

			public void setScheduleon(String scheduleon) {
				this.scheduleon = scheduleon;
			}
	private ArrayList<Master> vacinationlist;
	private ArrayList<Master> parentvacinationlist;
	private String scheduleon;
	private boolean sun,mon,tue,thu,wed,fri,sat;


	public boolean isSun() {
		return sun;
	}

	public void setSun(boolean sun) {
		this.sun = sun;
	}

	public boolean isMon() {
		return mon;
	}

	public void setMon(boolean mon) {
		this.mon = mon;
	}

	public boolean isTue() {
		return tue;
	}

	public void setTue(boolean tue) {
		this.tue = tue;
	}

	public boolean isThu() {
		return thu;
	}

	public void setThu(boolean thu) {
		this.thu = thu;
	}

	public boolean isWed() {
		return wed;
	}

	public void setWed(boolean wed) {
		this.wed = wed;
	}

	public boolean isFri() {
		return fri;
	}

	public void setFri(boolean fri) {
		this.fri = fri;
	}

	public boolean isSat() {
		return sat;
	}

	public void setSat(boolean sat) {
		this.sat = sat;
	}
public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
public String getVacine_parent_new() {
		return vacine_parent_new;
	}

	public void setVacine_parent_new(String vacine_parent_new) {
		this.vacine_parent_new = vacine_parent_new;
	}
public ArrayList<Master> getClinicalnoteslist() {
		return clinicalnoteslist;
	}

	public void setClinicalnoteslist(ArrayList<Master> clinicalnoteslist) {
		this.clinicalnoteslist = clinicalnoteslist;
	}
public ArrayList<Master> getClinicalproblemlist() {
		return clinicalproblemlist;
	}

	public void setClinicalproblemlist(ArrayList<Master> clinicalproblemlist) {
		this.clinicalproblemlist = clinicalproblemlist;
	}
public ArrayList<Master> getClinicalintervationlist() {
		return clinicalintervationlist;
	}

	public void setClinicalintervationlist(ArrayList<Master> clinicalintervationlist) {
		this.clinicalintervationlist = clinicalintervationlist;
	}
private String duration;
private int type;
private String vacine_parent_new;
private ArrayList<Master> clinicalnoteslist;
private ArrayList<Master> clinicalproblemlist;
private ArrayList<Master> clinicalintervationlist;
private String remark;
private String remarkhindi;
private String remarkmarathi;


public String getRemark() {
	return remark;
}

public void setRemark(String remark) {
	this.remark = remark;
}

public String getRemarkhindi() {
	return remarkhindi;
}

public void setRemarkhindi(String remarkhindi) {
	this.remarkhindi = remarkhindi;
}

public String getRemarkmarathi() {
	return remarkmarathi;
}

public void setRemarkmarathi(String remarkmarathi) {
	this.remarkmarathi = remarkmarathi;
}
public ArrayList<Master> getList() {
	return list;
}

public void setList(ArrayList<Master> list) {
	this.list = list;
}
public boolean isConsultant_compulsay() {
	return consultant_compulsay;
}

public void setConsultant_compulsay(boolean consultant_compulsay) {
	this.consultant_compulsay = consultant_compulsay;
}
public String getPercent() {
	return percent;
}

public void setPercent(String percent) {
	this.percent = percent;
}
private ArrayList<Master> list;
private boolean consultant_compulsay; 
private String percent;
private boolean taxtype1,taxtype3,taxtype2;


public boolean isTaxtype1() {
	return taxtype1;
}

public void setTaxtype1(boolean taxtype1) {
	this.taxtype1 = taxtype1;
}

public boolean isTaxtype3() {
	return taxtype3;
}

public void setTaxtype3(boolean taxtype3) {
	this.taxtype3 = taxtype3;
}

public boolean isTaxtype2() {
	return taxtype2;
}

public void setTaxtype2(boolean taxtype2) {
	this.taxtype2 = taxtype2;
}

public String getDependsonscheedule() {
	return dependsonscheedule;
}

public void setDependsonscheedule(String dependsonscheedule) {
	this.dependsonscheedule = dependsonscheedule;
}
public int getGendervaccine() {
	return gendervaccine;
}

public void setGendervaccine(int gendervaccine) {
	this.gendervaccine = gendervaccine;
}
public ArrayList<Investigation> getOutsourcevendorlist() {
	return outsourcevendorlist;
}

public void setOutsourcevendorlist(ArrayList<Investigation> outsourcevendorlist) {
	this.outsourcevendorlist = outsourcevendorlist;
}
public ArrayList<Master> getInvsTypeList() {
	return invsTypeList;
}

public void setInvsTypeList(ArrayList<Master> invsTypeList) {
	this.invsTypeList = invsTypeList;
}
public Collection<Master> getOutsourcerate() {
	return outsourcerate;
}

public void setOutsourcerate(Collection<Master> outsourcerate) {
	this.outsourcerate = outsourcerate;
}
public String getVendor() {
	return vendor;
}

public void setVendor(String vendor) {
	this.vendor = vendor;
}
public String getInvsttype() {
	return invsttype;
}

public void setInvsttype(String invsttype) {
	this.invsttype = invsttype;
}
public String getUnit() {
	return unit;
}

public void setUnit(String unit) {
	this.unit = unit;
}
public ArrayList<UserProfile> getSignatureList() {
	return signatureList;
}

public void setSignatureList(ArrayList<UserProfile> signatureList) {
	this.signatureList = signatureList;
}
public String getSubuploadfilesContentType() {
	return subuploadfilesContentType;
}

public void setSubuploadfilesContentType(String subuploadfilesContentType) {
	this.subuploadfilesContentType = subuploadfilesContentType;
}
public String getSubuploadfilesFileName() {
	return subuploadfilesFileName;
}

public void setSubuploadfilesFileName(String subuploadfilesFileName) {
	this.subuploadfilesFileName = subuploadfilesFileName;
}
public File getSubuploadfiles() {
	return subuploadfiles;
}

public void setSubuploadfiles(File subuploadfiles) {
	this.subuploadfiles = subuploadfiles;
}
public String getMin_value_m() {
	return min_value_m;
}

public void setMin_value_m(String min_value_m) {
	this.min_value_m = min_value_m;
}
public String getMax_value_m() {
	return max_value_m;
}

public void setMax_value_m(String max_value_m) {
	this.max_value_m = max_value_m;
}
public String getShowdeptlist() {
	return showdeptlist;
}

public void setShowdeptlist(String showdeptlist) {
	this.showdeptlist = showdeptlist;
}
private String dependsonscheedule;
private int gendervaccine;
private String unit;
private ArrayList<UserProfile> signatureList;

private String subuploadfilesContentType;
private String subuploadfilesFileName;
private File subuploadfiles;
private String min_value_m="0";
private String max_value_m="0";
private ArrayList<Master>payrolltemplatelist;
private ArrayList<Master>declarationlist;
private String declarationtitle;
}
