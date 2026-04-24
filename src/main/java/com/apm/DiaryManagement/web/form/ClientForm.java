package com.apm.DiaryManagement.web.form;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.GpData;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;

/**
 * @author Windows User
 *
 */
public class ClientForm{
	public String getClinicOwner1() {
		return clinicOwner1;
	}
	public void setClinicOwner1(String clinicOwner1) {
		this.clinicOwner1 = clinicOwner1;
	}
	private String adhno;
	private String tpmemb;
	private String pstatus;
	private String opdoption;
	private String enrollcode;
	private int statusenrollcode;
	private ArrayList<Client> planlist;
	private ArrayList<Client>chargelist;
	private ArrayList<Client>dailyremarklist;
	public ArrayList<Client> getDailyremarklist() {
		return dailyremarklist;
	}
	public void setDailyremarklist(ArrayList<Client> dailyremarklist) {
		this.dailyremarklist = dailyremarklist;
	}
	public ArrayList<Client> getChargelist() {
		return chargelist;
	}
	public void setChargelist(ArrayList<Client> chargelist) {
		this.chargelist = chargelist;
	}
	public ArrayList<Client> getPlanlist() {
		return planlist;
	}
	public void setPlanlist(ArrayList<Client> planlist) {
		this.planlist = planlist;
	}
	private ArrayList<Master> statelist;
	private ArrayList<Master> citylist;
	private ArrayList<Master>referhospitallist;
	public ArrayList<Master> getReferhospitallist() {
		return referhospitallist;
	}
	public void setReferhospitallist(ArrayList<Master> referhospitallist) {
		this.referhospitallist = referhospitallist;
	}
	private ArrayList<Clinic> allhospital;
	private int id;
	private String firstName="";
	private String lastName="";
	private String middleName="";
	private String title="";
	private String mobNo;
	private String email;
	private String hourls;
	private String minutels;
	private String relativeImg;
	private String registrationcharge;
	private String consultationcharge;
	private String othercharge;
	private String discounttype;
	 private int regchargeapplied;
	private String paymenttype;
	private int isfromaddpatientlmh;
	private String patientcategory;
	private ArrayList<Master> departmentList;
	private String fromprodasboard;
	private int isCampArea;
	private String alternate_mobno ;
	private String patientoccc;
	private String patientincm;
	private String patienthusocc;
	private String patienthusincome;
	private String patientEductn;
	private String pathusbEductn;
	private String religion;
	private String cast;
	private String physioipd;
	private ArrayList<Master> camplocationList;
	private ArrayList<Master>ClientTypeList;
	private String clientType;
	private String deptcampArea ;
	private ArrayList<NotAvailableSlot> tpNameList;
	private int days;
	private String checkindate;
	public String getCheckindate() {
		return checkindate;
	}

	public void setCheckindate(String checkindate) {
		this.checkindate = patientcategory;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public ArrayList<NotAvailableSlot> getTpNameList() {
		return tpNameList;
	}
	public void setTpNameList(ArrayList<NotAvailableSlot> tpNameList) {
		this.tpNameList = tpNameList;
	}
	public String getDeptcampArea() {
		return deptcampArea;
	}
	public void setDeptcampArea(String deptcampArea) {
		this.deptcampArea = deptcampArea;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public ArrayList<Master> getClientTypeList() {
		return ClientTypeList;
	}
	public void setClientTypeList(ArrayList<Master> clientTypeList) {
		ClientTypeList = clientTypeList;
	}
	public ArrayList<Master> getCamplocationList() {
		return camplocationList;
	}
	public void setCamplocationList(ArrayList<Master> camplocationList) {
		this.camplocationList = camplocationList;
	}
	public String getPatientoccc() {
		return patientoccc;
	}
	public void setPatientoccc(String patientoccc) {
		this.patientoccc = patientoccc;
	}
	public String getPhysioipd() {
		return physioipd;
	}
	public void setPhysioipd(String physioipd) {
		this.physioipd = physioipd;
	}
	public String getPatientincm() {
		return patientincm;
	}
	public void setPatientincm(String patientincm) {
		this.patientincm = patientincm;
	}
	public String getPatienthusocc() {
		return patienthusocc;
	}
	public void setPatienthusocc(String patienthusocc) {
		this.patienthusocc = patienthusocc;
	}
	public String getPatienthusincome() {
		return patienthusincome;
	}
	public void setPatienthusincome(String patienthusincome) {
		this.patienthusincome = patienthusincome;
	}
	public String getPatientEductn() {
		return patientEductn;
	}
	public void setPatientEductn(String patientEductn) {
		this.patientEductn = patientEductn;
	}
	public String getPathusbEductn() {
		return pathusbEductn;
	}
	public void setPathusbEductn(String pathusbEductn) {
		this.pathusbEductn = pathusbEductn;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getAlternate_mobno() {
		return alternate_mobno;
	}
	public void setAlternate_mobno(String alternate_mobno) {
		this.alternate_mobno = alternate_mobno;
	}
	public int getIsCampArea() {
		return isCampArea;
	}
	public void setIsCampArea(int isCampArea) {
		this.isCampArea = isCampArea;
	}
	public String getFromprodasboard() {
		return fromprodasboard;
	}
	public void setFromprodasboard(String fromprodasboard) {
		this.fromprodasboard = fromprodasboard;
	}
	public String getPatientcategory() {
		return patientcategory;
	}
	public void setPatientcategory(String patientcategory) {
		this.patientcategory = patientcategory;
	}
	public int getIsfromaddpatientlmh() {
		return isfromaddpatientlmh;
	}
	public void setIsfromaddpatientlmh(int isfromaddpatientlmh) {
		this.isfromaddpatientlmh = isfromaddpatientlmh;
	}
	public String getRegistrationcharge() {
		return registrationcharge;
	}
	public void setRegistrationcharge(String registrationcharge) {
		this.registrationcharge = registrationcharge;
	}
	public String getConsultationcharge() {
		return consultationcharge;
	}
	public void setConsultationcharge(String consultationcharge) {
		this.consultationcharge = consultationcharge;
	}
	public String getOthercharge() {
		return othercharge;
	}
	public void setOthercharge(String othercharge) {
		this.othercharge = othercharge;
	}
	public String getDiscounttype() {
		return discounttype;
	}
	public void setDiscounttype(String discounttype) {
		this.discounttype = discounttype;
	}
	public String getDiscountvalue() {
		return discountvalue;
	}
	public void setDiscountvalue(String discountvalue) {
		this.discountvalue = discountvalue;
	}
	public String getNetamount() {
		return netamount;
	}
	public void setNetamount(String netamount) {
		this.netamount = netamount;
	}
	private String discountvalue;
	private String netamount;
	
	public String getRelativeImg() {
		return relativeImg;
	}
	public void setRelativeImg(String relativeImg) {
		this.relativeImg = relativeImg;
	}
	public String getHourls() {
		return hourls;
	}
	public void setHourls(String hourls) {
		this.hourls = hourls;
	}
	public String getMinutels() {
		return minutels;
	}
	public void setMinutels(String minutels) {
		this.minutels = minutels;
	}
	private String gender;
	private String dob;
	private String town;
	private String country;
	private String age;
	private String address;
	private String sourceOfIntro;
	private String reference;
	private String postCode;
	private String doctorsurgery;
	private String otherSourceOfIntro="";
	private String otherCondition="";
	private String secondLineaddress;
	private String sourceOfIntroName;
	private ArrayList<String> apmtDurationList;
	private ArrayList<TreatmentEpisode> sourceOfReferralList;
	
	private boolean showAll;
	
	private String gpAddress = "";
	
	private String gpTown = "";
	
	private String gpCounty = "";
	
	private String gpPostCode = "";
	
	
	private String tpAddress = "";
	
	private String tpTown = "";
	
	private String tpCounty = "";
	
	private String tpPostCode = "";
	
	private String thirdpartyName = "";
	
	private String nhsNumber;
	
	ArrayList<Master>declerationTitleList;
	
	private String dectitle;
	
	private String abrivationid;
	
	 
		private String clinicOwner,clinicOwner1;
		private String clinicemail;
		private String clinicaddress;
		private String clinicity;
		private String websiteUrl;
		private String landLine;
		private String owner_qualification;
		private ArrayList<Clinic> locationAdressList;
		
	
	private String clientaddress;
	
	
	private File userImage;
	private String userImageFileName;
	private String userImageContentType;
	
	private String profileimg = "";
	private String clientimg;
	 private String patientIdAbrivation;
	 private String qualification;
	 private String practid;
	 private String ipdid;
	 private String declarationid;
	 private String clinicLogo;
	 private ArrayList<String>startTimeList;
	 private String fullname;
	 
	 private String relation;
	 private String relativeno;
	 private String relativename;
	 private String pincode;
	 private String docType;
	 private String profileImg;
	 private String docImg;
	private String town_village;
	
	 
	 
	public String getTown_village() {
		return town_village;
	}
	public void setTown_village(String town_village) {
		this.town_village = town_village;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getRelativeno() {
		return relativeno;
	}
	public void setRelativeno(String relativeno) {
		this.relativeno = relativeno;
	}
	public String getRelativename() {
		return relativename;
	}
	public void setRelativename(String relativename) {
		this.relativename = relativename;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getDocImg() {
		return docImg;
	}
	public void setDocImg(String docImg) {
		this.docImg = docImg;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
		public ArrayList<String> getStartTimeList() {
		return startTimeList;
	}
	public void setStartTimeList(ArrayList<String> startTimeList) {
		this.startTimeList = startTimeList;
	}
		public String getClinicLogo() {
		return clinicLogo;
	}
	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}
		public String getDeclarationid() {
		return declarationid;
	}
	public void setDeclarationid(String declarationid) {
		this.declarationid = declarationid;
	}
		public String getIpdid() {
		return ipdid;
	}
	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}
		public String getPractid() {
		return practid;
	}
	public void setPractid(String practid) {
		this.practid = practid;
	}
		public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
		public String getPatientIdAbrivation() {
			return patientIdAbrivation;
		}
		public void setPatientIdAbrivation(String patientIdAbrivation) {
			this.patientIdAbrivation = patientIdAbrivation;
		}
	
	
	public String getClientimg() {
		return clientimg;
	}
	public void setClientimg(String clientimg) {
		this.clientimg = clientimg;
	}
	public String getProfileimg() {
		return profileimg;
	}
	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
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
	public String getClientaddress() {
		return clientaddress;
	}
	public void setClientaddress(String clientaddress) {
		this.clientaddress = clientaddress;
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
		public ArrayList<Clinic> getLocationAdressList() {
			return locationAdressList;
		}
		public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
			this.locationAdressList = locationAdressList;
		}
	public String getDectitle() {
		return dectitle;
	}
	public void setDectitle(String dectitle) {
		this.dectitle = dectitle;
	}
	public String getThirdpartyName() {
		return thirdpartyName;
	}
	public void setThirdpartyName(String thirdpartyName) {
		this.thirdpartyName = thirdpartyName;
	}
	public ArrayList<Master> getDeclerationTitleList() {
		return declerationTitleList;
	}
	public void setDeclerationTitleList(ArrayList<Master> declerationTitleList) {
		this.declerationTitleList = declerationTitleList;
	}
	public String getNhsNumber() {
		return nhsNumber;
	}
	public void setNhsNumber(String nhsNumber) {
		this.nhsNumber = nhsNumber;
	}
	public String getTpAddress() {
		return tpAddress;
	}
	public void setTpAddress(String tpAddress) {
		this.tpAddress = tpAddress;
	}
	public String getTpTown() {
		return tpTown;
	}
	public void setTpTown(String tpTown) {
		this.tpTown = tpTown;
	}
	public String getTpCounty() {
		return tpCounty;
	}
	public void setTpCounty(String tpCounty) {
		this.tpCounty = tpCounty;
	}
	public String getTpPostCode() {
		return tpPostCode;
	}
	public void setTpPostCode(String tpPostCode) {
		this.tpPostCode = tpPostCode;
	}
	public String getGpAddress() {
		return gpAddress;
	}
	public void setGpAddress(String gpAddress) {
		this.gpAddress = gpAddress;
	}
	public String getGpTown() {
		return gpTown;
	}
	public void setGpTown(String gpTown) {
		this.gpTown = gpTown;
	}
	public String getGpCounty() {
		return gpCounty;
	}
	public void setGpCounty(String gpCounty) {
		this.gpCounty = gpCounty;
	}
	public String getGpPostCode() {
		return gpPostCode;
	}
	public void setGpPostCode(String gpPostCode) {
		this.gpPostCode = gpPostCode;
	}
	public boolean isShowAll() {
		return showAll;
	}
	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}
	public ArrayList<TreatmentEpisode> getSourceOfReferralList() {
		return sourceOfReferralList;
	}
	public void setSourceOfReferralList(
			ArrayList<TreatmentEpisode> sourceOfReferralList) {
		this.sourceOfReferralList = sourceOfReferralList;
	}
	ArrayList<ThirdParty> tpnameList;
	public String getSecondLineaddress() {
		return secondLineaddress;
	}
	public void setSecondLineaddress(String secondLineaddress) {
		this.secondLineaddress = secondLineaddress;
	}
	public String getSourceOfIntroName() {
		return sourceOfIntroName;
	}
	public void setSourceOfIntroName(String sourceOfIntroName) {
		this.sourceOfIntroName = sourceOfIntroName;
	}
	
	

	
	public ArrayList<String> getApmtDurationList() {
		return apmtDurationList;
	}
	public void setApmtDurationList(ArrayList<String> apmtDurationList) {
		this.apmtDurationList = apmtDurationList;
	}
	public ArrayList<ThirdParty> getTpnameList() {
		return tpnameList;
	}
	public void setTpnameList(ArrayList<ThirdParty> tpnameList) {
		this.tpnameList = tpnameList;
	}
	public String getOtherCondition() {
		return otherCondition;
	}
	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}
	public String getOtherSourceOfIntro() {
		return otherSourceOfIntro;
	}
	public void setOtherSourceOfIntro(String otherSourceOfIntro) {
		this.otherSourceOfIntro = otherSourceOfIntro;
	}
	public String getDoctorsurgery() {
		return doctorsurgery;
	}
	public void setDoctorsurgery(String doctorsurgery) {
		this.doctorsurgery = doctorsurgery;
	}
	private ArrayList<Client> allPatientList;
	public ArrayList<Client> getAllEmployeeList() {
		return allEmployeeList;
	}
	public void setAllEmployeeList(ArrayList<Client> allEmployeeList) {
		this.allEmployeeList = allEmployeeList;
	}
	private ArrayList<String> countryList;
	private ArrayList<String> occupationList;
	private ArrayList<Client> condtitionList;
	private ArrayList<Client> allEmployeeList;
	public ArrayList<Client> getCondtitionList() {
		return condtitionList;
	}
	public void setCondtitionList(ArrayList<Client> condtitionList) {
		this.condtitionList = condtitionList;
	}
	private String clientId;
	private String client;
	private String declarationNotes;
	private String declarationTitle;
	private String practitionerName;
	private String clinicName;
	private String note;
	private String oldclientId;
	private String diaryUser = "";
	private String gpname;
	private String treatmentType;
	private String policyExcess;
	
	private String lastModified;
	
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getPolicyExcess() {
		return policyExcess;
	}
	public void setPolicyExcess(String policyExcess) {
		this.policyExcess = policyExcess;
	}
	private ArrayList<GpData>gpDataList;
	
	
	
	public ArrayList<GpData> getGpDataList() {
		return gpDataList;
	}
	public void setGpDataList(ArrayList<GpData> gpDataList) {
		this.gpDataList = gpDataList;
	}
	public String getReferedDate() {
		return referedDate;
	}
	public void setReferedDate(String referedDate) {
		this.referedDate = referedDate;
	}
	private String referedDate;
	public String getTreatmentType() {
		return treatmentType;
	}
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}
	private String employerName;
	private String dateTime;
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	ArrayList<Client> gpList;
	public ArrayList<Client> getGpList() {
		return gpList;
	}
	public void setGpList(ArrayList<Client> gpList) {
		this.gpList = gpList;
	}
	public String getGpname() {
		
		return gpname;
	}
	public void setGpname(String gpname) {
		this.gpname = gpname;
	}
	private ArrayList<DiaryManagement>userList;

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}
	public String getDiaryUser() {
		return diaryUser;
	}
	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}
	public String getOldclientId() {
		return oldclientId;
	}
	public void setOldclientId(String oldclientId) {
		this.oldclientId = oldclientId;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getPractitionerName() {
		return practitionerName;
	}
	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}
	public String getDeclarationTitle() {
		return declarationTitle;
	}
	public void setDeclarationTitle(String declarationTitle) {
		this.declarationTitle = declarationTitle;
	}
	public String getDeclarationNotes() {
		return declarationNotes;
	}
	public void setDeclarationNotes(String declarationNotes) {
		this.declarationNotes = declarationNotes;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	//Third PArty Attributes
	private String type;
	private String typeName;
	private String thirdPartyType;
	private String thirdPartyTypeName;
	private String thirdPartyCompanyName;
	private ArrayList<Client> thirdPartyTypeList;
	private ArrayList<Client> thirdPartyTypeNameList;
	
	private ArrayList<Client>surgeryList;
	private String surgeryName;
	private String gptypeName;
	
	
	
	
	public String getGptypeName() {
		return gptypeName;
	}
	public void setGptypeName(String gptypeName) {
		this.gptypeName = gptypeName;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	public ArrayList<Client> getSurgeryList() {
		return surgeryList;
	}
	public void setSurgeryList(ArrayList<Client> surgeryList) {
		this.surgeryList = surgeryList;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	//21st march
	private String occupation;
	private String expiryDate;
	private String defaultNotes;
	private String accountsNotes;
	private String criticalInfo;
	
	//28th march
	private String whopay;
	private String policyAuthorzCode;
	private String policyNo;
	private ArrayList<Client> clientOccupationList;
	private ArrayList<Client> refrenceList;
	private String otherRef = "";
	private String otherOccupation = "";
	private ArrayList<String> initialList;
	private String searchText = "";
	
	private String knownAs;
	private String county;
	private String homeNo;
	private String workNo;
	private String emailCc;
	private String prefContactMode;
	private String emergencyContName;
	private String emergencyContNo;
	private String patientType;
	ArrayList<Client> sourceOfIntroList;
	
	private String pagerecords;
	private int totalRecords;
	private String message;
	private String status = "";
	private String gpid;
	
	private String clientNote = "";
	
	private String accountNote = "";
	
	private String clinicalNote = "";
	
	
	
	
	
	
	
	public String getClientNote() {
		return clientNote;
	}
	public void setClientNote(String clientNote) {
		this.clientNote = clientNote;
	}
	public String getAccountNote() {
		return accountNote;
	}
	public void setAccountNote(String accountNote) {
		this.accountNote = accountNote;
	}
	public String getClinicalNote() {
		return clinicalNote;
	}
	public void setClinicalNote(String clinicalNote) {
		this.clinicalNote = clinicalNote;
	}
	public String getGpid() {
		return gpid;
	}
	public void setGpid(String gpid) {
		this.gpid = gpid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public ArrayList<Client> getSourceOfIntroList() {
		return sourceOfIntroList;
	}
	public void setSourceOfIntroList(ArrayList<Client> sourceOfIntroList) {
		this.sourceOfIntroList = sourceOfIntroList;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getOtherOccupation() {
		return otherOccupation;
	}
	public void setOtherOccupation(String otherOccupation) {
		this.otherOccupation = otherOccupation;
	}
	public String getOtherRef() {
		return otherRef;
	}
	public void setOtherRef(String otherRef) {
		this.otherRef = otherRef;
	}
	public String getWhopay() {
		return whopay;
	}
	public void setWhopay(String whopay) {
		this.whopay = whopay;
	}
	public String getPolicyAuthorzCode() {
		return policyAuthorzCode;
	}
	public void setPolicyAuthorzCode(String policyAuthorzCode) {
		this.policyAuthorzCode = policyAuthorzCode;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public ArrayList<String> getOccupationList() {
		return occupationList;
	}
	public void setOccupationList(ArrayList<String> occupationList) {
		this.occupationList = occupationList;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getDefaultNotes() {
		return defaultNotes;
	}
	public void setDefaultNotes(String defaultNotes) {
		this.defaultNotes = defaultNotes;
	}
	public String getAccountsNotes() {
		return accountsNotes;
	}
	public void setAccountsNotes(String accountsNotes) {
		this.accountsNotes = accountsNotes;
	}
	public String getCriticalInfo() {
		return criticalInfo;
	}
	public void setCriticalInfo(String criticalInfo) {
		this.criticalInfo = criticalInfo;
	}
	public ArrayList<Client> getAllPatientList() {
		return allPatientList;
	}
	public void setAllPatientList(ArrayList<Client> allPatientList) {
		this.allPatientList = allPatientList;
	}
	public ArrayList<String> getCountryList() {
		return countryList;
	}
	public void setCountryList(ArrayList<String> countryList) {
		this.countryList = countryList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSourceOfIntro() {
		return sourceOfIntro;
	}
	public void setSourceOfIntro(String sourceOfIntro) {
		this.sourceOfIntro = sourceOfIntro;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public ArrayList<Client> getThirdPartyTypeList() {
		return thirdPartyTypeList;
	}
	public void setThirdPartyTypeList(ArrayList<Client> thirdPartyTypeList) {
		this.thirdPartyTypeList = thirdPartyTypeList;
	}
	public ArrayList<Client> getThirdPartyTypeNameList() {
		return thirdPartyTypeNameList;
	}
	public void setThirdPartyTypeNameList(ArrayList<Client> thirdPartyTypeNameList) {
		this.thirdPartyTypeNameList = thirdPartyTypeNameList;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getThirdPartyType() {
		return thirdPartyType;
	}
	public void setThirdPartyType(String thirdPartyType) {
		this.thirdPartyType = thirdPartyType;
	}
	public String getThirdPartyTypeName() {
		return thirdPartyTypeName;
	}
	public void setThirdPartyTypeName(String thirdPartyTypeName) {
		this.thirdPartyTypeName = thirdPartyTypeName;
	}
	public String getThirdPartyCompanyName() {
		return thirdPartyCompanyName;
	}
	public void setThirdPartyCompanyName(String thirdPartyCompanyName) {
		this.thirdPartyCompanyName = thirdPartyCompanyName;
	}
	
	public ArrayList<Client> getRefrenceList() {
		return refrenceList;
	}
	public void setRefrenceList(ArrayList<Client> refrenceList) {
		this.refrenceList = refrenceList;
	}
	public ArrayList<Client> getClientOccupationList() {
		return clientOccupationList;
	}
	public void setClientOccupationList(ArrayList<Client> clientOccupationList) {
		this.clientOccupationList = clientOccupationList;
	}
	public ArrayList<String> getInitialList() {
		return initialList;
	}
	public void setInitialList(ArrayList<String> initialList) {
		this.initialList = initialList;
	}
	public String getKnownAs() {
		return knownAs;
	}
	public void setKnownAs(String knownAs) {
		this.knownAs = knownAs;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getHomeNo() {
		return homeNo;
	}
	public void setHomeNo(String homeNo) {
		this.homeNo = homeNo;
	}
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	public String getEmailCc() {
		return emailCc;
	}
	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}
	public String getPrefContactMode() {
		return prefContactMode;
	}
	public void setPrefContactMode(String prefContactMode) {
		this.prefContactMode = prefContactMode;
	}
	public String getEmergencyContName() {
		return emergencyContName;
	}
	public void setEmergencyContName(String emergencyContName) {
		this.emergencyContName = emergencyContName;
	}
	public String getEmergencyContNo() {
		return emergencyContNo;
	}
	public void setEmergencyContNo(String emergencyContNo) {
		this.emergencyContNo = emergencyContNo;
	}
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public ArrayList<Master> getStatelist() {
		return statelist;
	}
	public void setStatelist(ArrayList<Master> statelist) {
		this.statelist = statelist;
	}
	public ArrayList<Master> getCitylist() {
		return citylist;
	}
	public void setCitylist(ArrayList<Master> cityList) {
		this.citylist = cityList;
	}
	public String getAdhno() {
		return adhno;
	}
	public void setAdhno(String adhno) {
		this.adhno = adhno;
	}
	public String getTpmemb() {
		return tpmemb;
	}
	public void setTpmemb(String tpmemb) {
		this.tpmemb = tpmemb;
	}
	public String getAbrivationid() {
		return abrivationid;
	}
	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}
	public String getTreatmenttype() {
		return treatmenttype;
	}
	public void setTreatmenttype(String treatmenttype) {
		this.treatmenttype = treatmenttype;
	}
	public ArrayList<Client> getPatientlist() {
		return patientlist;
	}
	public void setPatientlist(ArrayList<Client> patientlist) {
		this.patientlist = patientlist;
	}
	public ArrayList<Client> getFeedbacklist() {
		return feedbacklist;
	}
	public void setFeedbacklist(ArrayList<Client> feedbacklist) {
		this.feedbacklist = feedbacklist;
	}
	public String getFeedbackids() {
		return feedbackids;
	}
	public void setFeedbackids(String feedbackids) {
		this.feedbackids = feedbackids;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public ArrayList<Client> getFeedbackbypatient() {
		return feedbackbypatient;
	}
	public void setFeedbackbypatient(ArrayList<Client> feedbackbypatient) {
		this.feedbackbypatient = feedbackbypatient;
	}
	public String getManualfeedback() {
		return manualfeedback;
	}
	public void setManualfeedback(String manualfeedback) {
		this.manualfeedback = manualfeedback;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	private String treatmenttype;
	private ArrayList<Client> patientlist;
private	ArrayList<Client> feedbacklist;
private String feedbackids;
private String patient;
private ArrayList<Client>feedbackbypatient;
private String manualfeedback;
private String date;
private String fromdate;
private String todate;
private ArrayList<Master>invsTypeList;
private ArrayList<Master>invstReportTypeList;
private ArrayList<Master>invstUnitList;
private ArrayList<Master>invSectionList;
private ArrayList<Master>disciplineList;
private ArrayList<Master>pkgsList;
private ArrayList<String> jobTitleList;

public ArrayList<Master> getInvSectionList() {
	return invSectionList;
}
public void setInvSectionList(ArrayList<Master> invSectionList) {
	this.invSectionList = invSectionList;
}
public ArrayList<Master> getDisciplineList() {
	return disciplineList;
}
public void setDisciplineList(ArrayList<Master> disciplineList) {
	this.disciplineList = disciplineList;
}
public ArrayList<Master> getPkgsList() {
	return pkgsList;
}
public void setPkgsList(ArrayList<Master> pkgsList) {
	this.pkgsList = pkgsList;
}
public ArrayList<Master> getInvsTypeList() {
	return invsTypeList;
}
public void setInvsTypeList(ArrayList<Master> invsTypeList) {
	this.invsTypeList = invsTypeList;
}
public ArrayList<Master> getInvstReportTypeList() {
	return invstReportTypeList;
}
public void setInvstReportTypeList(ArrayList<Master> invstReportTypeList) {
	this.invstReportTypeList = invstReportTypeList;
}
public ArrayList<Master> getInvstUnitList() {
	return invstUnitList;
}
public void setInvstUnitList(ArrayList<Master> invstUnitList) {
	this.invstUnitList = invstUnitList;
}
public ArrayList<String> getJobTitleList() {
	return jobTitleList;
}
public void setJobTitleList(ArrayList<String> jobTitleList) {
	this.jobTitleList = jobTitleList;
}
public boolean isHospitalborn() {
	return hospitalborn;
}
public void setHospitalborn(boolean hospitalborn) {
	this.hospitalborn = hospitalborn;
}
private boolean hospitalborn;

private String compname;
private String neisno;
private String designationbytp;
private String relationvbytpe;
private String claimbytp;
private String unitstation;
private String colliery;
private String areabytp;


public String getCompname() {
	return compname;
}
public void setCompname(String compname) {
	this.compname = compname;
}
public String getNeisno() {
	return neisno;
}
public void setNeisno(String neisno) {
	this.neisno = neisno;
}
public String getDesignationbytp() {
	return designationbytp;
}
public void setDesignationbytp(String designationbytp) {
	this.designationbytp = designationbytp;
}
public String getRelationvbytpe() {
	return relationvbytpe;
}
public void setRelationvbytpe(String relationvbytpe) {
	this.relationvbytpe = relationvbytpe;
}
public String getClaimbytp() {
	return claimbytp;
}
public void setClaimbytp(String claimbytp) {
	this.claimbytp = claimbytp;
}
public String getUnitstation() {
	return unitstation;
}
public void setUnitstation(String unitstation) {
	this.unitstation = unitstation;
}
public String getColliery() {
	return colliery;
}
public void setColliery(String colliery) {
	this.colliery = colliery;
}
public String getAreabytp() {
	return areabytp;
}
public void setAreabytp(String areabytp) {
	this.areabytp = areabytp;
}

public String getTptypenamestatus() {
	return tptypenamestatus;
}
public void setTptypenamestatus(String tptypenamestatus) {
	this.tptypenamestatus = tptypenamestatus;
}
private String tptypenamestatus;




public String getPolicyholder() {
	return policyholder;
}
public void setPolicyholder(String policyholder) {
	this.policyholder = policyholder;
}
public ArrayList<String> getHourList() {
	return hourList;
}
public void setHourList(ArrayList<String> hourList) {
	this.hourList = hourList;
}
public ArrayList<String> getMinuteList() {
	return minuteList;
}
public void setMinuteList(ArrayList<String> minuteList) {
	this.minuteList = minuteList;
}
public String getDrqualification() {
	return drqualification;
}
public void setDrqualification(String drqualification) {
	this.drqualification = drqualification;
}


public String getAddmissiondate() {
	return addmissiondate;
}
public void setAddmissiondate(String addmissiondate) {
	this.addmissiondate = addmissiondate;
}
public String getDischargedate() {
	return dischargedate;
}
public void setDischargedate(String dischargedate) {
	this.dischargedate = dischargedate;
}
public String getMaritalsts() {
	return maritalsts;
}
public void setMaritalsts(String maritalsts) {
	this.maritalsts = maritalsts;
}
private String policyholder;
private ArrayList<String> hourList;
private ArrayList<String> minuteList;
private String drqualification;
private String addmissiondate;
private String dischargedate;
private String maritalsts;
private String mothername,fathername,birthplace;
private String campArea;


public String getCampArea() {
	return campArea;
}
public void setCampArea(String campArea) {
	this.campArea = campArea;
}
public String getMothername() {
	return mothername;
}
public void setMothername(String mothername) {
	this.mothername = mothername;
}
public String getFathername() {
	return fathername;
}
public void setFathername(String fathername) {
	this.fathername = fathername;
}
public String getBirthplace() {
	return birthplace;
}
public void setBirthplace(String birthplace) {
	this.birthplace = birthplace;
}
public ArrayList<Master> getDocuList() {
	return docuList;
}
public void setDocuList(ArrayList<Master> docuList) {
	this.docuList = docuList;
}
public String getDocument_name() {
	return document_name;
}
public void setDocument_name(String document_name) {
	this.document_name = document_name;
}
public String getDocumentValue() {
	return documentValue;
}
public void setDocumentValue(String documentValue) {
	this.documentValue = documentValue;
}
public int getRatingper() {
	return ratingper;
}
public void setRatingper(int ratingper) {
	this.ratingper = ratingper;
}
public String getApmtId() {
	return apmtId;
}
public void setApmtId(String apmtId) {
	this.apmtId = apmtId;
}
public Client getClientData() {
	return clientData;
}
public void setClientData(Client clientData) {
	this.clientData = clientData;
}
public ArrayList<Clinic> getAllhospital() {
	return allhospital;
}
public void setAllhospital(ArrayList<Clinic> allhospital) {
	this.allhospital = allhospital;
}
public ArrayList<Master> getBirthPlaceList() {
	return birthPlaceList;
}
public void setBirthPlaceList(ArrayList<Master> birthPlaceList) {
	this.birthPlaceList = birthPlaceList;
}
public String getPaymenttype() {
	return paymenttype;
}
public void setPaymenttype(String paymenttype) {
	this.paymenttype = paymenttype;
}
public int getRegchargeapplied() {
	return regchargeapplied;
}
public void setRegchargeapplied(int regchargeapplied) {
	this.regchargeapplied = regchargeapplied;
}
public ArrayList<Master> getDepartmentList() {
	return departmentList;
}
public void setDepartmentList(ArrayList<Master> departmentList) {
	this.departmentList = departmentList;
}
public String getOpdoption() {
	return opdoption;
}
public void setOpdoption(String opdoption) {
	this.opdoption = opdoption;
}
public String getPstatus() {
	return pstatus;
}
public void setPstatus(String pstatus) {
	this.pstatus = pstatus;
}
public String getEnrollcode() {
	return enrollcode;
}
public void setEnrollcode(String enrollcode) {
	this.enrollcode = enrollcode;
}
public int getStatusenrollcode() {
	return statusenrollcode;
}
public void setStatusenrollcode(int statusenrollcode) {
	this.statusenrollcode = statusenrollcode;
}
private String document_name,documentValue;
private ArrayList<Master> docuList;
private int ratingper;
private String apmtId;
private Client clientData; 
private ArrayList<Master> birthPlaceList;
private String clientName;
public String getClientName() {
	return clientName;
}
public void setClientName(String clientName) {
	this.clientName = clientName;
}
private int planid;
private String plan;
public String getPlan() {
	return plan;
}
public void setPlan(String plan) {
	this.plan = plan;
}
public int getPlanid() {
	return planid;
}
public void setPlanid(int planid) {
	this.planid = planid;
}
private int activeplanid;
public int getActiveplanid() {
	return activeplanid;
}
public void setActiveplanid(int activeplanid) {
	this.activeplanid = activeplanid;
}
private String activeplan;
public String getActiveplan() {
	return activeplan;
}
public void setActiveplan(String activeplan) {
	this.activeplan = activeplan;
}

private int regularpatient;
public int getRegularpatient() {
	return regularpatient;
}
public void setRegularpatient(int regularpatient) {
	this.regularpatient = regularpatient;
}

private String regno="23/24";
public String getRegno() {
	return regno;
}
public void setRegno(String regno) {
	this.regno = regno;
}

private ArrayList<Client> plandayslist;
public ArrayList<Client> getPlandayslist() {
	return plandayslist;
}
public void setPlandayslist(ArrayList<Client> plandayslist) {
	this.plandayslist = plandayslist;
}
}


