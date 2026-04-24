package com.apm.DiaryManagement.web.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.eu.entity.Tdcode;
import com.apm.DiaryManagement.web.common.Month;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.entity.MisReport;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.utils.DateTimeUtils;


public class NotAvailableSlotForm {
	public ArrayList<Diagnosis> getDiagnosislist() {
		return diagnosislist;
	}

	public void setDiagnosislist(ArrayList<Diagnosis> diagnosislist) {
		this.diagnosislist = diagnosislist;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public ArrayList<Master> getDepartmentlist() {
		return departmentlist;
	}

	public void setDepartmentlist(ArrayList<Master> departmentlist) {
		this.departmentlist = departmentlist;
	}

	public int getArvnew() {
		return arvnew;
	}

	public void setArvnew(int arvnew) {
		this.arvnew = arvnew;
	}

	public int getArvold() {
		return arvold;
	}

	public void setArvold(int arvold) {
		this.arvold = arvold;
	}

	public int getNewpatient() {
		return newpatient;
	}

	public void setNewpatient(int newpatient) {
		this.newpatient = newpatient;
	}

	public int getOldpatient() {
		return oldpatient;
	}

	public void setOldpatient(int oldpatient) {
		this.oldpatient = oldpatient;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public String getDatecolor() {
		return datecolor;
	}

	public void setDatecolor(String datecolor) {
		this.datecolor = datecolor;
	}

	public ArrayList<NotAvailableSlot> getMedicinelistvet() {
		return medicinelistvet;
	}

	public void setMedicinelistvet(ArrayList<NotAvailableSlot> medicinelistvet) {
		this.medicinelistvet = medicinelistvet;
	}

	public ArrayList<NotAvailableSlot> getDepartmentIPDPreviewList() {
		return departmentIPDPreviewList;
	}

	public void setDepartmentIPDPreviewList(ArrayList<NotAvailableSlot> departmentIPDPreviewList) {
		this.departmentIPDPreviewList = departmentIPDPreviewList;
	}

	public String getTokenno() {
		return tokenno;
	}

	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}

	public String getReqdatetime() {
		return reqdatetime;
	}

	public void setReqdatetime(String reqdatetime) {
		this.reqdatetime = reqdatetime;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public ArrayList<NotAvailableSlot> getDeptwisegenderlist() {
		return deptwisegenderlist;
	}

	public void setDeptwisegenderlist(ArrayList<NotAvailableSlot> deptwisegenderlist) {
		this.deptwisegenderlist = deptwisegenderlist;
	}
	private String withpayment;
	private String referto;
	private String withoutpayment;
	private NotAvailableSlot notAvailableSlot;
	private String duration;
	private String oriuhid;
	private String orderby;
	private String patienttype;
	private String selectedids;
	private String selectdepartment;
	private String userdepartment;
	private ArrayList<Master> citylist;
	private ArrayList<String>hourList;
    private ArrayList<NotAvailableSlot> shedulestaffreportlist;
    private ArrayList<NotAvailableSlot> vaccinationlist;
    private ArrayList<Master> birthPlaceList;
    private ArrayList<DiaryManagement> secondaryuserList;
    private String appointmentTypeTest;
    private String secondarydoc;
    private int isfromlmhreg;
    private String patientcategory;
    private int totalopdcount;
    private ArrayList<Client> fakepatientlist;
    private ArrayList<NotAvailableSlot> departmentOPDList;
    private ArrayList<SittingFollowup> sittinglist;
    private ArrayList<NotAvailableSlot> dailyrepot;
    private ArrayList<NotAvailableSlot> referedpatientlist;
    private ArrayList<Master> referhospitallist;
    private ArrayList<Master> patientStatusList;
    private int isAccnotPresent;
    private int isFromProDashboard;
    private ArrayList<Master> offerList;
    private int offerCount;
    private String disciplineName;
    private String allergynotes;
	private String charges;
    private String reqdatetime;
    private String tokenno;
    private String datecolor;
    private ArrayList<Master> physioipdhospitallist;
    private ArrayList<NotAvailableSlot>medicinelistvet;
    private String sittingbackdate;
    private String odmrcount;
    private String town;
    private String referfrom;
    public String getReferfrom() {
		return referfrom;
	}

	public void setReferfrom(String referfrom) {
		this.referfrom = referfrom;
	}
	public String getOdmrcount() {
		return odmrcount;
	}

	public void setOdmrcount(String odmrcount) {
		this.odmrcount = odmrcount;
	}

	public String getSittingbackdate() {
		return sittingbackdate;
	}

	public void setSittingbackdate(String sittingbackdate) {
		this.sittingbackdate = sittingbackdate;
	}

	public ArrayList<Master> getPhysioipdhospitallist() {
		return physioipdhospitallist;
	}

	public void setPhysioipdhospitallist(ArrayList<Master> physioipdhospitallist) {
		this.physioipdhospitallist = physioipdhospitallist;
	}

	public String getAllergynotes() {
		return allergynotes;
	}

	public void setAllergynotes(String allergynotes) {
		this.allergynotes = allergynotes;
	}

	public String getDisciplineName() {
		return disciplineName;
	}

	public void setDisciplineName(String disciplineName) {
		this.disciplineName = disciplineName;
	}

	public int getOfferCount() {
		return offerCount;
	}

	public void setOfferCount(int offerCount) {
		this.offerCount = offerCount;
	}

	public ArrayList<Master> getOfferList() {
		return offerList;
	}

	public void setOfferList(ArrayList<Master> offerList) {
		this.offerList = offerList;
	}

	public int getIsFromProDashboard() {
		return isFromProDashboard;
	}

	public void setIsFromProDashboard(int isFromProDashboard) {
		this.isFromProDashboard = isFromProDashboard;
	}

	public int getIsAccnotPresent() {
		return isAccnotPresent;
	}

	public void setIsAccnotPresent(int isAccnotPresent) {
		this.isAccnotPresent = isAccnotPresent;
	}

	public ArrayList<Master> getPatientStatusList() {
		return patientStatusList;
	}

	public void setPatientStatusList(ArrayList<Master> patientStatusList) {
		this.patientStatusList = patientStatusList;
	}

	public ArrayList<Master> getReferhospitallist() {
		return referhospitallist;
	}

	public void setReferhospitallist(ArrayList<Master> referhospitallist) {
		this.referhospitallist = referhospitallist;
	}

	public ArrayList<NotAvailableSlot> getReferedpatientlist() {
		return referedpatientlist;
	}

	public void setReferedpatientlist(ArrayList<NotAvailableSlot> referedpatientlist) {
		this.referedpatientlist = referedpatientlist;
	}

	public ArrayList<NotAvailableSlot> getDailyrepot() {
		return dailyrepot;
	}

	public void setDailyrepot(ArrayList<NotAvailableSlot> dailyrepot) {
		this.dailyrepot = dailyrepot;
	}
	private ArrayList<NotAvailableSlot>departmentOPDPreviewList;
	private ArrayList<NotAvailableSlot>departmentIPDPreviewList;
    private ArrayList<NotAvailableSlot> oldfakepatientlist;
    private String oldpselectedid;
    private ArrayList<NotAvailableSlot> list;
    private String name,bloodgroup,fathername,parentname,parentaddress,mobileno,institudename,institudeaddress,caste,education;
    private String subcaste,cet,physicsmax,physicsmark,physicsper,chemistrymax,chemistrymark,chemistryper,biologymax,biologymark,biologyper,englishmark,englishmax,englishper,totalmax,totalmark,totalper;
    private String searchText="";
    private String patientAgeType;
    private ArrayList<Master> deptWiseCountList;
    private int finalNewPatientCount=0;
    private int finalOldPatientCount=0;
    private int finalTotalPatientCount=0;
	private ArrayList<Master> referDepartList;
	private String drId;
	private String currentdate;
	private ArrayList<NotAvailableSlot>departmentIPDList;
	private ArrayList<Master> departmentlist;
    private int arvnew,arvold,newpatient,oldpatient,totalcount;



	
	 public ArrayList<NotAvailableSlot> getDepartmentIPDList() {
		return departmentIPDList;
	}

	public void setDepartmentIPDList(ArrayList<NotAvailableSlot> departmentIPDList) {
		this.departmentIPDList = departmentIPDList;
	}

	public String getCurrentdate() {
		return currentdate;
	}

	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}

	public String getDrId() {
		return drId;
	}

	public void setDrId(String drId) {
		this.drId = drId;
	}

	public ArrayList<SittingFollowup> getSittinglist() {
			return sittinglist;
		}

	 public void setSittinglist(ArrayList<SittingFollowup> sittinglist) {
			this.sittinglist = sittinglist;
		}
	
	
	public ArrayList<Master> getReferDepartList() {
		return referDepartList;
	}

	public void setReferDepartList(ArrayList<Master> referDepartList) {
		this.referDepartList = referDepartList;
	}

	public ArrayList<Master> getDeptWiseCountList() {
		return deptWiseCountList;
	}

	public void setDeptWiseCountList(ArrayList<Master> deptWiseCountList) {
		this.deptWiseCountList = deptWiseCountList;
	}

	public int getFinalNewPatientCount() {
		return finalNewPatientCount;
	}

	public void setFinalNewPatientCount(int finalNewPatientCount) {
		this.finalNewPatientCount = finalNewPatientCount;
	}

	public int getFinalOldPatientCount() {
		return finalOldPatientCount;
	}

	public void setFinalOldPatientCount(int finalOldPatientCount) {
		this.finalOldPatientCount = finalOldPatientCount;
	}

	public int getFinalTotalPatientCount() {
		return finalTotalPatientCount;
	}

	public void setFinalTotalPatientCount(int finalTotalPatientCount) {
		this.finalTotalPatientCount = finalTotalPatientCount;
	}

	public String getPatientAgeType() {
		return patientAgeType;
	}

	public void setPatientAgeType(String patientAgeType) {
		this.patientAgeType = patientAgeType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSubcaste() {
		return subcaste;
	}

	public void setSubcaste(String subcaste) {
		this.subcaste = subcaste;
	}

	public String getCet() {
		return cet;
	}

	public void setCet(String cet) {
		this.cet = cet;
	}

	public String getPhysicsmax() {
		return physicsmax;
	}

	public void setPhysicsmax(String physicsmax) {
		this.physicsmax = physicsmax;
	}

	public String getPhysicsmark() {
		return physicsmark;
	}

	public void setPhysicsmark(String physicsmark) {
		this.physicsmark = physicsmark;
	}

	public String getPhysicsper() {
		return physicsper;
	}

	public void setPhysicsper(String physicsper) {
		this.physicsper = physicsper;
	}

	public String getChemistrymax() {
		return chemistrymax;
	}

	public void setChemistrymax(String chemistrymax) {
		this.chemistrymax = chemistrymax;
	}

	public String getChemistrymark() {
		return chemistrymark;
	}

	public void setChemistrymark(String chemistrymark) {
		this.chemistrymark = chemistrymark;
	}

	public String getChemistryper() {
		return chemistryper;
	}

	public void setChemistryper(String chemistryper) {
		this.chemistryper = chemistryper;
	}

	public String getBiologymax() {
		return biologymax;
	}

	public void setBiologymax(String biologymax) {
		this.biologymax = biologymax;
	}

	public String getBiologymark() {
		return biologymark;
	}

	public void setBiologymark(String biologymark) {
		this.biologymark = biologymark;
	}

	public String getBiologyper() {
		return biologyper;
	}

	public void setBiologyper(String biologyper) {
		this.biologyper = biologyper;
	}

	public String getEnglishmark() {
		return englishmark;
	}

	public void setEnglishmark(String englishmark) {
		this.englishmark = englishmark;
	}

	public String getEnglishmax() {
		return englishmax;
	}

	public void setEnglishmax(String englishmax) {
		this.englishmax = englishmax;
	}

	public String getEnglishper() {
		return englishper;
	}

	public void setEnglishper(String englishper) {
		this.englishper = englishper;
	}

	public String getTotalmax() {
		return totalmax;
	}

	public void setTotalmax(String totalmax) {
		this.totalmax = totalmax;
	}

	public String getTotalmark() {
		return totalmark;
	}

	public void setTotalmark(String totalmark) {
		this.totalmark = totalmark;
	}

	public String getTotalper() {
		return totalper;
	}

	public void setTotalper(String totalper) {
		this.totalper = totalper;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getParentaddress() {
		return parentaddress;
	}

	public void setParentaddress(String parentaddress) {
		this.parentaddress = parentaddress;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getInstitudename() {
		return institudename;
	}

	public void setInstitudename(String institudename) {
		this.institudename = institudename;
	}

	public String getInstitudeaddress() {
		return institudeaddress;
	}

	public void setInstitudeaddress(String institudeaddress) {
		this.institudeaddress = institudeaddress;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getIsfromlmhreg() {
		return isfromlmhreg;
	}

	public void setIsfromlmhreg(int isfromlmhreg) {
		this.isfromlmhreg = isfromlmhreg;
	}

	public ArrayList<Master> getBirthPlaceList() {
		return birthPlaceList;
	}

	public void setBirthPlaceList(ArrayList<Master> birthPlaceList) {
		this.birthPlaceList = birthPlaceList;
	}

	public ArrayList<NotAvailableSlot> getShedulestaffreportlist() {
		return shedulestaffreportlist;
	}

	public void setShedulestaffreportlist(ArrayList<NotAvailableSlot> shedulestaffreportlist) {
		this.shedulestaffreportlist = shedulestaffreportlist;
	}
	private ArrayList<String>minuteList;
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
	private ArrayList<Master> statelist;
	private ArrayList<Master> requestlocationlist;
	private String requestlocationid;
	private int id;
   ArrayList<MisReport> opdappointmenttype;
	 public ArrayList<Master> getRequestlocationlist() {
	return requestlocationlist;
}

public void setRequestlocationlist(ArrayList<Master> requestlocationlist) {
	this.requestlocationlist = requestlocationlist;
}

public String getRequestlocationid() {
	return requestlocationid;
}

public void setRequestlocationid(String requestlocationid) {
	this.requestlocationid = requestlocationid;
}

	public ArrayList<MisReport> getOpdappointmenttype() {
	  return opdappointmenttype;
	 }

	 public void setOpdappointmenttype(ArrayList<MisReport> opdappointmenttype) {
	  this.opdappointmenttype = opdappointmenttype;
	 }
	private String diaryUser;
	
	private String dayWeekName = "";
	private String registerno="";
	private ArrayList<TreatmentType> treatmentTypeList;
	private String city;
	private String abrivationid="0";
	private String fromDate="";
	private String clinicPhone;
	private String bsa;
	private String userQualification;
	private String otsurgeonname;
	private ArrayList<UserProfile>surgeonlist;
	
	private ArrayList<Master> priscUnitList;
	private ArrayList<Master> specializationTemplateList;
	private ArrayList<String> visitingtimeList;
	private String ipdid;
	private ArrayList<String> tempstringlist;
	private String tempaddmorecount;
	ArrayList<Master>otdepartmentList;
	private String otdepartment;
	private String newotnotes;
	
	ArrayList<Master>schedulerlist;
	private String profileimg;
	private int surgeonid;
	private String useregno;
	 
	private ArrayList<Master> vitalMasterIOList;
	private ArrayList<Master> vitalMasterIVList;
	private ArrayList<Master> vitalMasterEquipmentList;
	private ArrayList<String> iotimeList;
	public ArrayList<Master> getBankNameList() {
		return bankNameList;
	}
	private ArrayList<Priscription> medicinetimelist;
	public ArrayList<Priscription> getMedicinetimelist() {
		return medicinetimelist;
	}

	public void setMedicinetimelist(ArrayList<Priscription> medicinetimelist) {
		this.medicinetimelist = medicinetimelist;
	}

	public void setBankNameList(ArrayList<Master> bankNameList) {
		this.bankNameList = bankNameList;
	}
	private ArrayList<String> ivtimeList;
	private ArrayList<String> eqtimeList;
	private ArrayList<Master> vitalMasterList;
	
	ArrayList<Master>bankNameList;
	
	  private String bcg, opv0, hep_b1, dtwp1, ipv1, hep_b2, hib1, rotavirus1, pcv1, dtwp2, ipv2, hib2, rotavirus2, pcv2, dtwp3, ipv3;
    private String hib3, rotavirus3, pcv3, opv1, hepb3, opv2, mmr1, typhoid_conjugate, vaccine;
    private String hepa1, mmr2, varicella1, pcvbooster, dtwpb1dtapb1, ipvb1, hibb1, hepa2, boosteroftyphoid, conjugatevaccine, dtwpb2dtapb2, tdaptd, hpv;
    private String bcgdt, opv0dt, hep_b1dt, dtwp1dt, ipv1dt, hep_b2dt, hib1dt, rotavirus1dt, pcv1dt, dtwp2dt, ipv2dt, hib2dt, rotavirus2dt, pcv2dt, dtwp3dt, ipv3dt;
    private String hib3dt, rotavirus3dt, pcv3dt, opv1dt, hepb3dt, opv2dt, mmr1dt, typhoid_conjugatedt, vaccinedt;
    private String hepa1dt, mmr2dt, varicella1dt, pcvboosterdt, dtwpb1dtapb1dt, ipvb1dt, hibb1dt, hepa2dt, boosteroftyphoiddt, conjugatevaccinedt, dtwpb2dtapb2dt, tdaptddt, hpvdt;
    private String remark1,remark2,remark3,remark4,remark5,remark6,remark7,remark8,remark9,remark10,remark11,remark12,remark13,remark14,remark15;
    private String dob;
    private String fullname;
    private ArrayList<Master> warehouseList;
    
    ArrayList<Master>nimaidoselist;
	ArrayList<Master>nimaiqtylist;
	ArrayList<Master>nimairemarklist;
	
	
    
	
	
	 public ArrayList<Master> getNimaidoselist() {
		return nimaidoselist;
	}

	public void setNimaidoselist(ArrayList<Master> nimaidoselist) {
		this.nimaidoselist = nimaidoselist;
	}

	public ArrayList<Master> getNimaiqtylist() {
		return nimaiqtylist;
	}

	public void setNimaiqtylist(ArrayList<Master> nimaiqtylist) {
		this.nimaiqtylist = nimaiqtylist;
	}

	public ArrayList<Master> getNimairemarklist() {
		return nimairemarklist;
	}

	public void setNimairemarklist(ArrayList<Master> nimairemarklist) {
		this.nimairemarklist = nimairemarklist;
	}

	public ArrayList<Master> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(ArrayList<Master> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBcg() {
			return bcg;
		}

		public void setBcg(String bcg) {
			this.bcg = bcg;
		}

		public String getOpv0() {
			return opv0;
		}

		public void setOpv0(String opv0) {
			this.opv0 = opv0;
		}

		public String getHep_b1() {
			return hep_b1;
		}

		public void setHep_b1(String hep_b1) {
			this.hep_b1 = hep_b1;
		}

		public String getDtwp1() {
			return dtwp1;
		}

		public void setDtwp1(String dtwp1) {
			this.dtwp1 = dtwp1;
		}

		public String getIpv1() {
			return ipv1;
		}

		public void setIpv1(String ipv1) {
			this.ipv1 = ipv1;
		}

		public String getHep_b2() {
			return hep_b2;
		}

		public void setHep_b2(String hep_b2) {
			this.hep_b2 = hep_b2;
		}

		public String getHib1() {
			return hib1;
		}

		public void setHib1(String hib1) {
			this.hib1 = hib1;
		}

		public String getRotavirus1() {
			return rotavirus1;
		}

		public void setRotavirus1(String rotavirus1) {
			this.rotavirus1 = rotavirus1;
		}

		public String getPcv1() {
			return pcv1;
		}

		public void setPcv1(String pcv1) {
			this.pcv1 = pcv1;
		}

		public String getDtwp2() {
			return dtwp2;
		}

		public void setDtwp2(String dtwp2) {
			this.dtwp2 = dtwp2;
		}

		public String getIpv2() {
			return ipv2;
		}

		public void setIpv2(String ipv2) {
			this.ipv2 = ipv2;
		}

		public String getHib2() {
			return hib2;
		}

		public void setHib2(String hib2) {
			this.hib2 = hib2;
		}

		public String getRotavirus2() {
			return rotavirus2;
		}

		public void setRotavirus2(String rotavirus2) {
			this.rotavirus2 = rotavirus2;
		}

		public String getPcv2() {
			return pcv2;
		}

		public void setPcv2(String pcv2) {
			this.pcv2 = pcv2;
		}

		public String getDtwp3() {
			return dtwp3;
		}

		public void setDtwp3(String dtwp3) {
			this.dtwp3 = dtwp3;
		}

		public String getIpv3() {
			return ipv3;
		}

		public void setIpv3(String ipv3) {
			this.ipv3 = ipv3;
		}

		public String getHib3() {
			return hib3;
		}

		public void setHib3(String hib3) {
			this.hib3 = hib3;
		}

		public String getRotavirus3() {
			return rotavirus3;
		}

		public void setRotavirus3(String rotavirus3) {
			this.rotavirus3 = rotavirus3;
		}

		public String getPcv3() {
			return pcv3;
		}

		public void setPcv3(String pcv3) {
			this.pcv3 = pcv3;
		}

		public String getOpv1() {
			return opv1;
		}

		public void setOpv1(String opv1) {
			this.opv1 = opv1;
		}

		public String getHepb3() {
			return hepb3;
		}

		public void setHepb3(String hepb3) {
			this.hepb3 = hepb3;
		}

		public String getOpv2() {
			return opv2;
		}

		public void setOpv2(String opv2) {
			this.opv2 = opv2;
		}

		public String getMmr1() {
			return mmr1;
		}

		public void setMmr1(String mmr1) {
			this.mmr1 = mmr1;
		}

		public String getTyphoid_conjugate() {
			return typhoid_conjugate;
		}

		public void setTyphoid_conjugate(String typhoid_conjugate) {
			this.typhoid_conjugate = typhoid_conjugate;
		}

		public String getVaccine() {
			return vaccine;
		}

		public void setVaccine(String vaccine) {
			this.vaccine = vaccine;
		}

		public String getHepa1() {
			return hepa1;
		}

		public void setHepa1(String hepa1) {
			this.hepa1 = hepa1;
		}

		public String getMmr2() {
			return mmr2;
		}

		public void setMmr2(String mmr2) {
			this.mmr2 = mmr2;
		}

		public String getVaricella1() {
			return varicella1;
		}

		public void setVaricella1(String varicella1) {
			this.varicella1 = varicella1;
		}

		public String getPcvbooster() {
			return pcvbooster;
		}

		public void setPcvbooster(String pcvbooster) {
			this.pcvbooster = pcvbooster;
		}

		public String getDtwpb1dtapb1() {
			return dtwpb1dtapb1;
		}

		public void setDtwpb1dtapb1(String dtwpb1dtapb1) {
			this.dtwpb1dtapb1 = dtwpb1dtapb1;
		}

		public String getIpvb1() {
			return ipvb1;
		}

		public void setIpvb1(String ipvb1) {
			this.ipvb1 = ipvb1;
		}

		public String getHibb1() {
			return hibb1;
		}

		public void setHibb1(String hibb1) {
			this.hibb1 = hibb1;
		}

		public String getHepa2() {
			return hepa2;
		}

		public void setHepa2(String hepa2) {
			this.hepa2 = hepa2;
		}

		public String getBoosteroftyphoid() {
			return boosteroftyphoid;
		}

		public void setBoosteroftyphoid(String boosteroftyphoid) {
			this.boosteroftyphoid = boosteroftyphoid;
		}

		public String getConjugatevaccine() {
			return conjugatevaccine;
		}

		public void setConjugatevaccine(String conjugatevaccine) {
			this.conjugatevaccine = conjugatevaccine;
		}

		public String getDtwpb2dtapb2() {
			return dtwpb2dtapb2;
		}

		public void setDtwpb2dtapb2(String dtwpb2dtapb2) {
			this.dtwpb2dtapb2 = dtwpb2dtapb2;
		}

		public String getTdaptd() {
			return tdaptd;
		}

		public void setTdaptd(String tdaptd) {
			this.tdaptd = tdaptd;
		}

		public String getHpv() {
			return hpv;
		}

		public void setHpv(String hpv) {
			this.hpv = hpv;
		}

		public String getBcgdt() {
			return bcgdt;
		}

		public void setBcgdt(String bcgdt) {
			this.bcgdt = bcgdt;
		}

		public String getOpv0dt() {
			return opv0dt;
		}

		public void setOpv0dt(String opv0dt) {
			this.opv0dt = opv0dt;
		}

		public String getHep_b1dt() {
			return hep_b1dt;
		}

		public void setHep_b1dt(String hep_b1dt) {
			this.hep_b1dt = hep_b1dt;
		}

		public String getDtwp1dt() {
			return dtwp1dt;
		}

		public void setDtwp1dt(String dtwp1dt) {
			this.dtwp1dt = dtwp1dt;
		}

		public String getIpv1dt() {
			return ipv1dt;
		}

		public void setIpv1dt(String ipv1dt) {
			this.ipv1dt = ipv1dt;
		}

		public String getHep_b2dt() {
			return hep_b2dt;
		}

		public void setHep_b2dt(String hep_b2dt) {
			this.hep_b2dt = hep_b2dt;
		}

		public String getHib1dt() {
			return hib1dt;
		}

		public void setHib1dt(String hib1dt) {
			this.hib1dt = hib1dt;
		}

		public String getRotavirus1dt() {
			return rotavirus1dt;
		}

		public void setRotavirus1dt(String rotavirus1dt) {
			this.rotavirus1dt = rotavirus1dt;
		}

		public String getPcv1dt() {
			return pcv1dt;
		}

		public void setPcv1dt(String pcv1dt) {
			this.pcv1dt = pcv1dt;
		}

		public String getDtwp2dt() {
			return dtwp2dt;
		}

		public void setDtwp2dt(String dtwp2dt) {
			this.dtwp2dt = dtwp2dt;
		}

		public String getIpv2dt() {
			return ipv2dt;
		}

		public void setIpv2dt(String ipv2dt) {
			this.ipv2dt = ipv2dt;
		}

		public String getHib2dt() {
			return hib2dt;
		}

		public void setHib2dt(String hib2dt) {
			this.hib2dt = hib2dt;
		}

		public String getRotavirus2dt() {
			return rotavirus2dt;
		}

		public void setRotavirus2dt(String rotavirus2dt) {
			this.rotavirus2dt = rotavirus2dt;
		}

		public String getPcv2dt() {
			return pcv2dt;
		}

		public void setPcv2dt(String pcv2dt) {
			this.pcv2dt = pcv2dt;
		}

		public String getDtwp3dt() {
			return dtwp3dt;
		}

		public void setDtwp3dt(String dtwp3dt) {
			this.dtwp3dt = dtwp3dt;
		}

		public String getIpv3dt() {
			return ipv3dt;
		}

		public void setIpv3dt(String ipv3dt) {
			this.ipv3dt = ipv3dt;
		}

		public String getHib3dt() {
			return hib3dt;
		}

		public void setHib3dt(String hib3dt) {
			this.hib3dt = hib3dt;
		}

		public String getRotavirus3dt() {
			return rotavirus3dt;
		}

		public void setRotavirus3dt(String rotavirus3dt) {
			this.rotavirus3dt = rotavirus3dt;
		}

		public String getPcv3dt() {
			return pcv3dt;
		}

		public void setPcv3dt(String pcv3dt) {
			this.pcv3dt = pcv3dt;
		}

		public String getOpv1dt() {
			return opv1dt;
		}

		public void setOpv1dt(String opv1dt) {
			this.opv1dt = opv1dt;
		}

		public String getHepb3dt() {
			return hepb3dt;
		}

		public void setHepb3dt(String hepb3dt) {
			this.hepb3dt = hepb3dt;
		}

		public String getOpv2dt() {
			return opv2dt;
		}

		public void setOpv2dt(String opv2dt) {
			this.opv2dt = opv2dt;
		}

		public String getMmr1dt() {
			return mmr1dt;
		}

		public void setMmr1dt(String mmr1dt) {
			this.mmr1dt = mmr1dt;
		}

		public String getTyphoid_conjugatedt() {
			return typhoid_conjugatedt;
		}

		public void setTyphoid_conjugatedt(String typhoid_conjugatedt) {
			this.typhoid_conjugatedt = typhoid_conjugatedt;
		}

		public String getVaccinedt() {
			return vaccinedt;
		}

		public void setVaccinedt(String vaccinedt) {
			this.vaccinedt = vaccinedt;
		}

		public String getHepa1dt() {
			return hepa1dt;
		}

		public void setHepa1dt(String hepa1dt) {
			this.hepa1dt = hepa1dt;
		}

		public String getMmr2dt() {
			return mmr2dt;
		}

		public void setMmr2dt(String mmr2dt) {
			this.mmr2dt = mmr2dt;
		}

		public String getVaricella1dt() {
			return varicella1dt;
		}

		public void setVaricella1dt(String varicella1dt) {
			this.varicella1dt = varicella1dt;
		}

		public String getPcvboosterdt() {
			return pcvboosterdt;
		}

		public void setPcvboosterdt(String pcvboosterdt) {
			this.pcvboosterdt = pcvboosterdt;
		}

		public String getDtwpb1dtapb1dt() {
			return dtwpb1dtapb1dt;
		}

		public void setDtwpb1dtapb1dt(String dtwpb1dtapb1dt) {
			this.dtwpb1dtapb1dt = dtwpb1dtapb1dt;
		}

		public String getIpvb1dt() {
			return ipvb1dt;
		}

		public void setIpvb1dt(String ipvb1dt) {
			this.ipvb1dt = ipvb1dt;
		}

		public String getHibb1dt() {
			return hibb1dt;
		}

		public void setHibb1dt(String hibb1dt) {
			this.hibb1dt = hibb1dt;
		}

		public String getHepa2dt() {
			return hepa2dt;
		}

		public void setHepa2dt(String hepa2dt) {
			this.hepa2dt = hepa2dt;
		}

		public String getBoosteroftyphoiddt() {
			return boosteroftyphoiddt;
		}

		public void setBoosteroftyphoiddt(String boosteroftyphoiddt) {
			this.boosteroftyphoiddt = boosteroftyphoiddt;
		}

		public String getConjugatevaccinedt() {
			return conjugatevaccinedt;
		}

		public void setConjugatevaccinedt(String conjugatevaccinedt) {
			this.conjugatevaccinedt = conjugatevaccinedt;
		}

		public String getDtwpb2dtapb2dt() {
			return dtwpb2dtapb2dt;
		}

		public void setDtwpb2dtapb2dt(String dtwpb2dtapb2dt) {
			this.dtwpb2dtapb2dt = dtwpb2dtapb2dt;
		}

		public String getTdaptddt() {
			return tdaptddt;
		}

		public void setTdaptddt(String tdaptddt) {
			this.tdaptddt = tdaptddt;
		}

		public String getHpvdt() {
			return hpvdt;
		}

		public void setHpvdt(String hpvdt) {
			this.hpvdt = hpvdt;
		}

		public String getRemark1() {
			return remark1;
		}

		public void setRemark1(String remark1) {
			this.remark1 = remark1;
		}

		public String getRemark2() {
			return remark2;
		}

		public void setRemark2(String remark2) {
			this.remark2 = remark2;
		}

		public String getRemark3() {
			return remark3;
		}

		public void setRemark3(String remark3) {
			this.remark3 = remark3;
		}

		public String getRemark4() {
			return remark4;
		}

		public void setRemark4(String remark4) {
			this.remark4 = remark4;
		}

		public String getRemark5() {
			return remark5;
		}

		public void setRemark5(String remark5) {
			this.remark5 = remark5;
		}

		public String getRemark6() {
			return remark6;
		}

		public void setRemark6(String remark6) {
			this.remark6 = remark6;
		}

		public String getRemark7() {
			return remark7;
		}

		public void setRemark7(String remark7) {
			this.remark7 = remark7;
		}

		public String getRemark8() {
			return remark8;
		}

		public void setRemark8(String remark8) {
			this.remark8 = remark8;
		}

		public String getRemark9() {
			return remark9;
		}

		public void setRemark9(String remark9) {
			this.remark9 = remark9;
		}

		public String getRemark10() {
			return remark10;
		}

		public void setRemark10(String remark10) {
			this.remark10 = remark10;
		}

		public String getRemark11() {
			return remark11;
		}

		public void setRemark11(String remark11) {
			this.remark11 = remark11;
		}

		public String getRemark12() {
			return remark12;
		}

		public void setRemark12(String remark12) {
			this.remark12 = remark12;
		}

		public String getRemark13() {
			return remark13;
		}

		public void setRemark13(String remark13) {
			this.remark13 = remark13;
		}

		public String getRemark14() {
			return remark14;
		}

		public void setRemark14(String remark14) {
			this.remark14 = remark14;
		}

		public String getRemark15() {
			return remark15;
		}

		public void setRemark15(String remark15) {
			this.remark15 = remark15;
		}

	public ArrayList<Master> getVitalMasterList() {
		return vitalMasterList;
	}

	public void setVitalMasterList(ArrayList<Master> vitalMasterList) {
		this.vitalMasterList = vitalMasterList;
	}

	public String getUseregno() {
		return useregno;
	}

	public void setUseregno(String useregno) {
		this.useregno = useregno;
	}

	public int getSurgeonid() {
		return surgeonid;
	}

	public void setSurgeonid(int surgeonid) {
		this.surgeonid = surgeonid;
	}

	public String getProfileimg() {
		return profileimg;
	}

	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
	}

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
	public String getNewotnotes() {
		return newotnotes;
	}

	public void setNewotnotes(String newotnotes) {
		this.newotnotes = newotnotes;
	}

	public ArrayList<Master> getOtdepartmentList() {
		return otdepartmentList;
	}

	public void setOtdepartmentList(ArrayList<Master> otdepartmentList) {
		this.otdepartmentList = otdepartmentList;
	}

	public String getOtdepartment() {
		return otdepartment;
	}

	public void setOtdepartment(String otdepartment) {
		this.otdepartment = otdepartment;
	}
	private String viewtype;
	
	public String getViewtype() {
		return viewtype;
	}

	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}

	public String getTempaddmorecount() {
		return tempaddmorecount;
	}

	public void setTempaddmorecount(String tempaddmorecount) {
		this.tempaddmorecount = tempaddmorecount;
	}

	public ArrayList<String> getTempstringlist() {
		return tempstringlist;
	}

	public void setTempstringlist(ArrayList<String> tempstringlist) {
		this.tempstringlist = tempstringlist;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public ArrayList<String> getVisitingtimeList() {
		return visitingtimeList;
	}

	public void setVisitingtimeList(ArrayList<String> visitingtimeList) {
		this.visitingtimeList = visitingtimeList;
	}

	public ArrayList<UserProfile> getSurgeonlist() {
		return surgeonlist;
	}

	public void setSurgeonlist(ArrayList<UserProfile> surgeonlist) {
		this.surgeonlist = surgeonlist;
	}

	public String getOtsurgeonname() {
		return otsurgeonname;
	}

	public void setOtsurgeonname(String otsurgeonname) {
		this.otsurgeonname = otsurgeonname;
	}

	ArrayList<Client> anesthesiaList;
	
	private String printedBy;
	
	ArrayList<Master>medicineList;
	
	ArrayList<Priscription>followupApmtList;
	
	private int followupapmtsize;
	
	private ArrayList<Master>masterChageTypeList;
	private ArrayList<Priscription> templateNameList;
	
	private String masterchargetype;
	
	ArrayList<UserProfile>staffList;
	
	private Collection<Diagnosis> diagnosises;
	
	ArrayList<Priscription>parentPriscList;
	ArrayList<Master>dosagenoteList;
	ArrayList<Master>invsTypeList;
	ArrayList<Master>invstReportTypeList;
	ArrayList<Master>invstUnitList;
	ArrayList<Master>cbcIdList;
	ArrayList<String> jobTitleList;
	private String jobtitle;
	 
	ArrayList<Master>jobGroupList;
	private String jobgroup;
	private String selectedjobgroup = "5";
	
	ArrayList<Master>invoiceTypeLis;
	ArrayList<AppointmentType> additionalChargeList;
	
	ArrayList<Master>pkgsList;
	private String invpkg;
	 private String re_unaided_d;
	    private String 	re_unaided_n;
	    private String 	re_withglass_d;
	    private String 	re_withglass_n;
	    private String 	re_gtph_d;
	    private String re_gtph_n;
	    private String le_unaided_d;
	    private String le_unaided_n;
	    private String le_withglass_d;
	    private String le_withglass_n;
	    private String le_gtph_d;
	    private String le_gtph_n;
	    private String air_r;
	    private String air_l;
	    private String perkins_r;
	    private String perkins_l;
	    private String appl_r;
	    private String appl_l;
	    private String keratometry1;
	    private String keratometry2;
	    private String keratometry3;
	    private String keratometry4;
	    private String keratometry5;
	    private String keratometry6;
	    private String keratometry7;
	    private String keratometry8;
		private String re_usingglass_s;
	    private String re_usingglass_c;
	    private String re_usingglass_a;
	    private String re_usingglass_va;
	    private String re_usingglass_nv;
	    private String re_usingglass_add;
		private String re_ar_s;
		private String re_ar_c;
		private String re_ar_a;
		private String re_ar_va;
		private String re_ar_nv;
		private String re_ar_add;
		private String re_ace_s;
		private String re_ace_c;
		private String re_ace_a;
		private String re_ace_va;
		private String re_ace_nv;
		private String re_ace_add;
		private String le_usingglass_s;
		private String le_usingglass_c;
		private String le_usingglass_a;
		private String le_usingglass_va;
		private String le_usingglass_nv;
		private String le_usingglass_add;
		private String le_ar_s;
		private String le_ar_c;
		private String le_ar_a;
		private String le_ar_va;
		private String le_ar_nv;
		private String le_ar_add;
		private String le_ace_s;
		private String le_ace_c;
		private String le_ace_a;
		private String le_ace_va;
		private String le_ace_nv;
		private String le_ace_add;
		private String lens_left1;
		private String lens_left2;
		private String diagnosisarea;
		private String lens_right1;
		private String lens_right2;
		private String followup;
	    private String userid;
	    private String datetime;
	
	
	public String getRe_unaided_d() {
			return re_unaided_d;
		}

		public void setRe_unaided_d(String re_unaided_d) {
			this.re_unaided_d = re_unaided_d;
		}

		public String getRe_unaided_n() {
			return re_unaided_n;
		}

		public void setRe_unaided_n(String re_unaided_n) {
			this.re_unaided_n = re_unaided_n;
		}

		public String getRe_withglass_d() {
			return re_withglass_d;
		}

		public void setRe_withglass_d(String re_withglass_d) {
			this.re_withglass_d = re_withglass_d;
		}

		public String getRe_withglass_n() {
			return re_withglass_n;
		}

		public void setRe_withglass_n(String re_withglass_n) {
			this.re_withglass_n = re_withglass_n;
		}

		public String getRe_gtph_d() {
			return re_gtph_d;
		}

		public void setRe_gtph_d(String re_gtph_d) {
			this.re_gtph_d = re_gtph_d;
		}

		public String getRe_gtph_n() {
			return re_gtph_n;
		}

		public void setRe_gtph_n(String re_gtph_n) {
			this.re_gtph_n = re_gtph_n;
		}

		public String getLe_unaided_d() {
			return le_unaided_d;
		}

		public void setLe_unaided_d(String le_unaided_d) {
			this.le_unaided_d = le_unaided_d;
		}

		public String getLe_unaided_n() {
			return le_unaided_n;
		}

		public void setLe_unaided_n(String le_unaided_n) {
			this.le_unaided_n = le_unaided_n;
		}

		public String getLe_withglass_d() {
			return le_withglass_d;
		}

		public void setLe_withglass_d(String le_withglass_d) {
			this.le_withglass_d = le_withglass_d;
		}

		public String getLe_withglass_n() {
			return le_withglass_n;
		}

		public void setLe_withglass_n(String le_withglass_n) {
			this.le_withglass_n = le_withglass_n;
		}

		public String getLe_gtph_d() {
			return le_gtph_d;
		}

		public void setLe_gtph_d(String le_gtph_d) {
			this.le_gtph_d = le_gtph_d;
		}

		public String getLe_gtph_n() {
			return le_gtph_n;
		}

		public void setLe_gtph_n(String le_gtph_n) {
			this.le_gtph_n = le_gtph_n;
		}

		public String getAir_r() {
			return air_r;
		}

		public void setAir_r(String air_r) {
			this.air_r = air_r;
		}

		public String getAir_l() {
			return air_l;
		}

		public void setAir_l(String air_l) {
			this.air_l = air_l;
		}

		public String getPerkins_r() {
			return perkins_r;
		}

		public void setPerkins_r(String perkins_r) {
			this.perkins_r = perkins_r;
		}

		public String getPerkins_l() {
			return perkins_l;
		}

		public void setPerkins_l(String perkins_l) {
			this.perkins_l = perkins_l;
		}

		public String getAppl_r() {
			return appl_r;
		}

		public void setAppl_r(String appl_r) {
			this.appl_r = appl_r;
		}

		public String getAppl_l() {
			return appl_l;
		}

		public void setAppl_l(String appl_l) {
			this.appl_l = appl_l;
		}

		public String getKeratometry1() {
			return keratometry1;
		}

		public void setKeratometry1(String keratometry1) {
			this.keratometry1 = keratometry1;
		}

		public String getKeratometry2() {
			return keratometry2;
		}

		public void setKeratometry2(String keratometry2) {
			this.keratometry2 = keratometry2;
		}

		public String getKeratometry3() {
			return keratometry3;
		}

		public void setKeratometry3(String keratometry3) {
			this.keratometry3 = keratometry3;
		}

		public String getKeratometry4() {
			return keratometry4;
		}

		public void setKeratometry4(String keratometry4) {
			this.keratometry4 = keratometry4;
		}

		public String getKeratometry5() {
			return keratometry5;
		}

		public void setKeratometry5(String keratometry5) {
			this.keratometry5 = keratometry5;
		}

		public String getKeratometry6() {
			return keratometry6;
		}

		public void setKeratometry6(String keratometry6) {
			this.keratometry6 = keratometry6;
		}

		public String getKeratometry7() {
			return keratometry7;
		}

		public void setKeratometry7(String keratometry7) {
			this.keratometry7 = keratometry7;
		}

		public String getKeratometry8() {
			return keratometry8;
		}

		public void setKeratometry8(String keratometry8) {
			this.keratometry8 = keratometry8;
		}

		public String getRe_usingglass_s() {
			return re_usingglass_s;
		}

		public void setRe_usingglass_s(String re_usingglass_s) {
			this.re_usingglass_s = re_usingglass_s;
		}

		public String getRe_usingglass_c() {
			return re_usingglass_c;
		}

		public void setRe_usingglass_c(String re_usingglass_c) {
			this.re_usingglass_c = re_usingglass_c;
		}

		public String getRe_usingglass_a() {
			return re_usingglass_a;
		}

		public void setRe_usingglass_a(String re_usingglass_a) {
			this.re_usingglass_a = re_usingglass_a;
		}

		public String getRe_usingglass_va() {
			return re_usingglass_va;
		}

		public void setRe_usingglass_va(String re_usingglass_va) {
			this.re_usingglass_va = re_usingglass_va;
		}

		public String getRe_usingglass_nv() {
			return re_usingglass_nv;
		}

		public void setRe_usingglass_nv(String re_usingglass_nv) {
			this.re_usingglass_nv = re_usingglass_nv;
		}

		public String getRe_usingglass_add() {
			return re_usingglass_add;
		}

		public void setRe_usingglass_add(String re_usingglass_add) {
			this.re_usingglass_add = re_usingglass_add;
		}

		public String getRe_ar_s() {
			return re_ar_s;
		}

		public void setRe_ar_s(String re_ar_s) {
			this.re_ar_s = re_ar_s;
		}

		public String getRe_ar_c() {
			return re_ar_c;
		}

		public void setRe_ar_c(String re_ar_c) {
			this.re_ar_c = re_ar_c;
		}

		public String getRe_ar_a() {
			return re_ar_a;
		}

		public void setRe_ar_a(String re_ar_a) {
			this.re_ar_a = re_ar_a;
		}

		public String getRe_ar_va() {
			return re_ar_va;
		}

		public void setRe_ar_va(String re_ar_va) {
			this.re_ar_va = re_ar_va;
		}

		public String getRe_ar_nv() {
			return re_ar_nv;
		}

		public void setRe_ar_nv(String re_ar_nv) {
			this.re_ar_nv = re_ar_nv;
		}

		public String getRe_ar_add() {
			return re_ar_add;
		}

		public void setRe_ar_add(String re_ar_add) {
			this.re_ar_add = re_ar_add;
		}

		public String getRe_ace_s() {
			return re_ace_s;
		}

		public void setRe_ace_s(String re_ace_s) {
			this.re_ace_s = re_ace_s;
		}

		public String getRe_ace_c() {
			return re_ace_c;
		}

		public void setRe_ace_c(String re_ace_c) {
			this.re_ace_c = re_ace_c;
		}

		public String getRe_ace_a() {
			return re_ace_a;
		}

		public void setRe_ace_a(String re_ace_a) {
			this.re_ace_a = re_ace_a;
		}

		public String getRe_ace_va() {
			return re_ace_va;
		}

		public void setRe_ace_va(String re_ace_va) {
			this.re_ace_va = re_ace_va;
		}

		public String getRe_ace_nv() {
			return re_ace_nv;
		}

		public void setRe_ace_nv(String re_ace_nv) {
			this.re_ace_nv = re_ace_nv;
		}

		public String getRe_ace_add() {
			return re_ace_add;
		}

		public void setRe_ace_add(String re_ace_add) {
			this.re_ace_add = re_ace_add;
		}

		public String getLe_usingglass_s() {
			return le_usingglass_s;
		}

		public void setLe_usingglass_s(String le_usingglass_s) {
			this.le_usingglass_s = le_usingglass_s;
		}

		public String getLe_usingglass_c() {
			return le_usingglass_c;
		}

		public void setLe_usingglass_c(String le_usingglass_c) {
			this.le_usingglass_c = le_usingglass_c;
		}

		public String getLe_usingglass_a() {
			return le_usingglass_a;
		}

		public void setLe_usingglass_a(String le_usingglass_a) {
			this.le_usingglass_a = le_usingglass_a;
		}

		public String getLe_usingglass_va() {
			return le_usingglass_va;
		}

		public void setLe_usingglass_va(String le_usingglass_va) {
			this.le_usingglass_va = le_usingglass_va;
		}

		public String getLe_usingglass_nv() {
			return le_usingglass_nv;
		}

		public void setLe_usingglass_nv(String le_usingglass_nv) {
			this.le_usingglass_nv = le_usingglass_nv;
		}

		public String getLe_usingglass_add() {
			return le_usingglass_add;
		}

		public void setLe_usingglass_add(String le_usingglass_add) {
			this.le_usingglass_add = le_usingglass_add;
		}

		public String getLe_ar_s() {
			return le_ar_s;
		}

		public void setLe_ar_s(String le_ar_s) {
			this.le_ar_s = le_ar_s;
		}

		public String getLe_ar_c() {
			return le_ar_c;
		}

		public void setLe_ar_c(String le_ar_c) {
			this.le_ar_c = le_ar_c;
		}

		public String getLe_ar_a() {
			return le_ar_a;
		}

		public void setLe_ar_a(String le_ar_a) {
			this.le_ar_a = le_ar_a;
		}

		public String getLe_ar_va() {
			return le_ar_va;
		}

		public void setLe_ar_va(String le_ar_va) {
			this.le_ar_va = le_ar_va;
		}

		public String getLe_ar_nv() {
			return le_ar_nv;
		}

		public void setLe_ar_nv(String le_ar_nv) {
			this.le_ar_nv = le_ar_nv;
		}

		public String getLe_ar_add() {
			return le_ar_add;
		}

		public void setLe_ar_add(String le_ar_add) {
			this.le_ar_add = le_ar_add;
		}

		public String getLe_ace_s() {
			return le_ace_s;
		}

		public void setLe_ace_s(String le_ace_s) {
			this.le_ace_s = le_ace_s;
		}

		public String getLe_ace_c() {
			return le_ace_c;
		}

		public void setLe_ace_c(String le_ace_c) {
			this.le_ace_c = le_ace_c;
		}

		public String getLe_ace_a() {
			return le_ace_a;
		}

		public void setLe_ace_a(String le_ace_a) {
			this.le_ace_a = le_ace_a;
		}

		public String getLe_ace_va() {
			return le_ace_va;
		}

		public void setLe_ace_va(String le_ace_va) {
			this.le_ace_va = le_ace_va;
		}

		public String getLe_ace_nv() {
			return le_ace_nv;
		}

		public void setLe_ace_nv(String le_ace_nv) {
			this.le_ace_nv = le_ace_nv;
		}

		public String getLe_ace_add() {
			return le_ace_add;
		}

		public void setLe_ace_add(String le_ace_add) {
			this.le_ace_add = le_ace_add;
		}

		public String getLens_left1() {
			return lens_left1;
		}

		public void setLens_left1(String lens_left1) {
			this.lens_left1 = lens_left1;
		}

		public String getLens_left2() {
			return lens_left2;
		}

		public void setLens_left2(String lens_left2) {
			this.lens_left2 = lens_left2;
		}

		public String getDiagnosisarea() {
			return diagnosisarea;
		}

		public void setDiagnosisarea(String diagnosisarea) {
			this.diagnosisarea = diagnosisarea;
		}

		public String getLens_right1() {
			return lens_right1;
		}

		public void setLens_right1(String lens_right1) {
			this.lens_right1 = lens_right1;
		}

		public String getLens_right2() {
			return lens_right2;
		}

		public void setLens_right2(String lens_right2) {
			this.lens_right2 = lens_right2;
		}

		public String getFollowup() {
			return followup;
		}

		public void setFollowup(String followup) {
			this.followup = followup;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getDatetime() {
			return datetime;
		}

		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}

	public ArrayList<Client> getAnesthesiaList() {
		return anesthesiaList;
	}

	public void setAnesthesiaList(ArrayList<Client> anesthesiaList) {
		this.anesthesiaList = anesthesiaList;
	}

	public ArrayList<Master> getPkgsList() {
		return pkgsList;
	}

	public void setPkgsList(ArrayList<Master> pkgsList) {
		this.pkgsList = pkgsList;
	}

	public String getInvpkg() {
		return invpkg;
	}

	public void setInvpkg(String invpkg) {
		this.invpkg = invpkg;
	}

	public ArrayList<AppointmentType> getAdditionalChargeList() {
		return additionalChargeList;
	}

	public void setAdditionalChargeList(ArrayList<AppointmentType> additionalChargeList) {
		this.additionalChargeList = additionalChargeList;
	}

	private String invcetype;
	
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
	    private String agegender;
	
	    private String height;
		private String weight;
		private String bmi;
		private String pulse;
		private String sysbp;
		private String diabp;
		
		ArrayList<Master>procedureList;
		ArrayList<Master> otherTemplateList;
		
		//ot variables
		private String otplan;
		private String procedure;
		private String surgeon;
		private String anesthesia;
		private String ipdno;
		private String wardid;
		
		private String asistantdoclist;
		private String otnotes;
		
		private int blockot;
		private String wardbed;
		private String operation;
	    private String admitdate;
	    private ArrayList<DiaryManagement> doctorList;
	    private String blankletterhead;
	    ArrayList<Master>procedure_List;
	    
	    public String getBlankletterhead() {
			return blankletterhead;
		}

		public void setBlankletterhead(String blankletterhead) {
			this.blankletterhead = blankletterhead;
		}

		public ArrayList<Master> getProcedure_List() {
			return procedure_List;
		}

		public void setProcedure_List(ArrayList<Master> procedure_List) {
			this.procedure_List = procedure_List;
		}

		public ArrayList<DiaryManagement> getDoctorList() {
			return doctorList;
		}

		public void setDoctorList(ArrayList<DiaryManagement> doctorList) {
			this.doctorList = doctorList;
		}

		public String getImageData() {
			return imageData;
		}

		public void setImageData(String imageData) {
			this.imageData = imageData;
		}

		private String timeofincision;
	    private String ansintime;
	    
	    ArrayList<Master>assetList;
	    ArrayList<Priscription>oteqtemplateNameList;
	    
	    ArrayList<Master>invSectionList;
	    ArrayList<Assessment> importImageList;
	    private String imageData;
	    private String opdid;
	    private String clinicLogo;
	public String getClinicLogo() {
			return clinicLogo;
		}

		public void setClinicLogo(String clinicLogo) {
			this.clinicLogo = clinicLogo;
		}

	public String getOpdid() {
			return opdid;
		}

		public void setOpdid(String opdid) {
			this.opdid = opdid;
		}

	public ArrayList<Assessment> getImportImageList() {
			return importImageList;
		}

		public void setImportImageList(ArrayList<Assessment> importImageList) {
			this.importImageList = importImageList;
		}

	public ArrayList<Master> getInvSectionList() {
			return invSectionList;
		}

		public void setInvSectionList(ArrayList<Master> invSectionList) {
			this.invSectionList = invSectionList;
		}

	public ArrayList<Master> getAssetList() {
			return assetList;
		}

		public void setAssetList(ArrayList<Master> assetList) {
			this.assetList = assetList;
		}

	public String getTimeofincision() {
			return timeofincision;
		}

		public void setTimeofincision(String timeofincision) {
			this.timeofincision = timeofincision;
		}

		public String getAnsintime() {
			return ansintime;
		}

		public void setAnsintime(String ansintime) {
			this.ansintime = ansintime;
		}

	public String getAdmitdate() {
			return admitdate;
		}

		public void setAdmitdate(String admitdate) {
			this.admitdate = admitdate;
		}

	public String getOperation() {
			return operation;
		}

		public void setOperation(String operation) {
			this.operation = operation;
		}

	public String getOtplan() {
			return otplan;
		}

		public void setOtplan(String otplan) {
			this.otplan = otplan;
		}

		public String getProcedure() {
			return procedure;
		}

		public void setProcedure(String procedure) {
			this.procedure = procedure;
		}

		public String getSurgeon() {
			return surgeon;
		}

		public void setSurgeon(String surgeon) {
			this.surgeon = surgeon;
		}

		public String getAnesthesia() {
			return anesthesia;
		}

		public void setAnesthesia(String anesthesia) {
			this.anesthesia = anesthesia;
		}

		public String getIpdno() {
			return ipdno;
		}

		public void setIpdno(String ipdno) {
			this.ipdno = ipdno;
		}

		public String getWardid() {
			return wardid;
		}

		public void setWardid(String wardid) {
			this.wardid = wardid;
		}

		public String getAsistantdoclist() {
			return asistantdoclist;
		}

		public void setAsistantdoclist(String asistantdoclist) {
			this.asistantdoclist = asistantdoclist;
		}

		public int getBlockot() {
			return blockot;
		}

		public void setBlockot(int blockot) {
			this.blockot = blockot;
		}

		public String getWardbed() {
			return wardbed;
		}

		public void setWardbed(String wardbed) {
			this.wardbed = wardbed;
		}

	public ArrayList<Master> getProcedureList() {
			return procedureList;
		}

		public void setProcedureList(ArrayList<Master> procedureList) {
			this.procedureList = procedureList;
		}

	public String getHeight() {
			return height;
		}

		public void setHeight(String height) {
			this.height = height;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
		}

		public String getBmi() {
			return bmi;
		}

		public void setBmi(String bmi) {
			this.bmi = bmi;
		}

		public String getPulse() {
			return pulse;
		}

		public void setPulse(String pulse) {
			this.pulse = pulse;
		}

		public String getSysbp() {
			return sysbp;
		}

		public void setSysbp(String sysbp) {
			this.sysbp = sysbp;
		}

		public String getDiabp() {
			return diabp;
		}

		public void setDiabp(String diabp) {
			this.diabp = diabp;
		}

	public String getAgegender() {
			return agegender;
		}

		public void setAgegender(String agegender) {
			this.agegender = agegender;
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

	public ArrayList<Master> getInvoiceTypeLis() {
		return invoiceTypeLis;
	}

	public void setInvoiceTypeLis(ArrayList<Master> invoiceTypeLis) {
		this.invoiceTypeLis = invoiceTypeLis;
	}

	public String getInvcetype() {
		return invcetype;
	}

	public void setInvcetype(String invcetype) {
		this.invcetype = invcetype;
	}

	public ArrayList<Master> getJobGroupList() {
		return jobGroupList;
	}

	public void setJobGroupList(ArrayList<Master> jobGroupList) {
		this.jobGroupList = jobGroupList;
	}

	public String getJobgroup() {
		return jobgroup;
	}

	public void setJobgroup(String jobgroup) {
		this.jobgroup = jobgroup;
	}

	public ArrayList<Priscription> getParentPriscList() {
		return parentPriscList;
	}

	public void setParentPriscList(ArrayList<Priscription> parentPriscList) {
		this.parentPriscList = parentPriscList;
	}

	public ArrayList<Master> getDosagenoteList() {
		return dosagenoteList;
	}

	public void setDosagenoteList(ArrayList<Master> dosagenoteList) {
		this.dosagenoteList = dosagenoteList;
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

	public ArrayList<Master> getCbcIdList() {
		return cbcIdList;
	}

	public void setCbcIdList(ArrayList<Master> cbcIdList) {
		this.cbcIdList = cbcIdList;
	}

	public ArrayList<String> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(ArrayList<String> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}

	public Collection<Diagnosis> getDiagnosises() {
		return diagnosises;
	}

	public void setDiagnosises(Collection<Diagnosis> diagnosises) {
		this.diagnosises = diagnosises;
	}

	public ArrayList<UserProfile> getStaffList() {
		return staffList;
	}

	public void setStaffList(ArrayList<UserProfile> staffList) {
		this.staffList = staffList;
	}

	public String getMasterchargetype() {
		return masterchargetype;
	}

	public void setMasterchargetype(String masterchargetype) {
		this.masterchargetype = masterchargetype;
	}

	public ArrayList<Master> getMasterChageTypeList() {
		return masterChageTypeList;
	}

	public void setMasterChageTypeList(ArrayList<Master> masterChageTypeList) {
		this.masterChageTypeList = masterChageTypeList;
	}

	public int getFollowupapmtsize() {
		return followupapmtsize;
	}

	public void setFollowupapmtsize(int followupapmtsize) {
		this.followupapmtsize = followupapmtsize;
	}

	public ArrayList<Priscription> getFollowupApmtList() {
		return followupApmtList;
	}

	public void setFollowupApmtList(ArrayList<Priscription> followupApmtList) {
		this.followupApmtList = followupApmtList;
	}

	public ArrayList<Master> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(ArrayList<Master> medicineList) {
		this.medicineList = medicineList;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public ArrayList<TreatmentType> getTreatmentTypeList() {
		return treatmentTypeList;
	}

	public void setTreatmentTypeList(ArrayList<TreatmentType> treatmentTypeList) {
		this.treatmentTypeList = treatmentTypeList;
	}

	String currentDate = DateTimeUtils.getDateinSimpleStringFormate(new Date());
	String dateTemp[] = currentDate.split(" ");
	String temp[] =  dateTemp[0].split("-");

	private String country;
	
	ArrayList<Master>dosageList ;
	
	
	
	
	public ArrayList<Master> getDosageList() {
		return dosageList;
	}

	public void setDosageList(ArrayList<Master> dosageList) {
		this.dosageList = dosageList;
	}

	public String getDayWeekName() {
		return dayWeekName;
	}

	public void setDayWeekName(String dayWeekName) {
		this.dayWeekName = dayWeekName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private String commencing = temp[0] + "/" + temp[1] + "/" + temp[2];
	
	private ArrayList<DiaryManagement>userList;
	private ArrayList<DiaryManagement>vaccuserList;
	private ArrayList<String>startTimeList;
	
	private ArrayList<String>endTimeList;
	
	private ArrayList<String> apmtDurationList;
	
	private ArrayList<String> apmtBlockDurationList;
	ArrayList<Client> condtitionList;
	ArrayList<Client> diagnosisList;
	ArrayList<Diagnosis> diagnosislist;
	ArrayList<Master>disciplineList;
	
	private String diciplineName;
	
	private String message = "";
	
	private String dischrgeOutcomes;
	
	private ArrayList<Discharge>dischargeOutcomeList;
	
	private String dischargeStatus;
	
	private ArrayList<Discharge>dischargeStatusList;
	
	private boolean chkDischarge;
	
	private String locationName = "";
	
	private ArrayList<EmailTemplate>smsTemplateList;
	
	private String priscdate;
	
	private ArrayList<Master>mdicinecategoryList;
	
	private String mdicinecategory;
	
	private String priscdateandtime;
	
	
	
	
	
	public String getPriscdateandtime() {
		return priscdateandtime;
	}

	public void setPriscdateandtime(String priscdateandtime) {
		this.priscdateandtime = priscdateandtime;
	}

	public ArrayList<Master> getMdicinecategoryList() {
		return mdicinecategoryList;
	}

	public void setMdicinecategoryList(ArrayList<Master> mdicinecategoryList) {
		this.mdicinecategoryList = mdicinecategoryList;
	}

	public String getMdicinecategory() {
		return mdicinecategory;
	}

	public void setMdicinecategory(String mdicinecategory) {
		this.mdicinecategory = mdicinecategory;
	}

	public String getPriscdate() {
		return priscdate;
	}

	public void setPriscdate(String priscdate) {
		this.priscdate = priscdate;
	}

	public ArrayList<EmailTemplate> getSmsTemplateList() {
		return smsTemplateList;
	}

	public void setSmsTemplateList(ArrayList<EmailTemplate> smsTemplateList) {
		this.smsTemplateList = smsTemplateList;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getDischrgeOutcomes() {
		return dischrgeOutcomes;
	}

	public void setDischrgeOutcomes(String dischrgeOutcomes) {
		this.dischrgeOutcomes = dischrgeOutcomes;
	}

	public ArrayList<Discharge> getDischargeOutcomeList() {
		return dischargeOutcomeList;
	}

	public void setDischargeOutcomeList(ArrayList<Discharge> dischargeOutcomeList) {
		this.dischargeOutcomeList = dischargeOutcomeList;
	}

	public String getDischargeStatus() {
		return dischargeStatus;
	}

	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	public ArrayList<Discharge> getDischargeStatusList() {
		return dischargeStatusList;
	}

	public void setDischargeStatusList(ArrayList<Discharge> dischargeStatusList) {
		this.dischargeStatusList = dischargeStatusList;
	}

	public boolean isChkDischarge() {
		return chkDischarge;
	}

	public void setChkDischarge(boolean chkDischarge) {
		this.chkDischarge = chkDischarge;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDiciplineName() {
		return diciplineName;
	}

	public void setDiciplineName(String diciplineName) {
		this.diciplineName = diciplineName;
	}

	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public ArrayList<Client> getCondtitionList() {
		return condtitionList;
	}

	public void setCondtitionList(ArrayList<Client> condtitionList) {
		this.condtitionList = condtitionList;
	}

	private ArrayList<AppointmentType>appointmentTypeList;
	ArrayList<Client> gpList;
	private String gpname;
	
	public String getGpname() {
		return gpname;
	}

	public void setGpname(String gpname) {
		this.gpname = gpname;
	}

	public ArrayList<Client> getGpList() {
		return gpList;
	}

	public void setGpList(ArrayList<Client> gpList) {
		this.gpList = gpList;
	}

	private String actionType;
	
	private String sTime;
	private String endTime;
	private String apmtDuration;
	private ArrayList<Location>locationList;
	private String location;
	private String dept;
	private String room;
	private String client;
	private String apmtType;
	private String notes;
	private int apmtSlotId;
	private String user;
	private String date;
	private int diaryUserId;
	private int slotId;
    private String conditionid;
    
    
    
    
	
	//block attributes
	
	private String blockslotId;
	private String blockdiaryUserId;
	private String blockuser;
	private String blocklocation;
	private String blockroom;
	private String blockdate;
	private String blocksTime;
	private String blockendTime;
	private String blockapmtDuration;
	private String reasonforblock;
	private String status;
	private String blocknotes;
	
	private String practitonerName;
	private String toDate;
	private String caldate = "";
	
	
	private String dashclientId;
	private String mobile;
	private String clientId = "";
	
	private String cancelApmtNote = "";
	
	private ArrayList<NotAvailableSlot>finderList;
	
	
	
	private String printCommencing = "";
	
	private String printLocation = "";
	
	private String printDiaryserid = "";
	
	
	ArrayList<Master>mdicneTypeList;
	
	private ArrayList<NotAvailableSlot>fakeconsultdeptlist;
	
	
	
	public ArrayList<NotAvailableSlot> getFakeconsultdeptlist() {
		return fakeconsultdeptlist;
	}

	public void setFakeconsultdeptlist(ArrayList<NotAvailableSlot> fakeconsultdeptlist) {
		this.fakeconsultdeptlist = fakeconsultdeptlist;
	}

	public String getConditionid() {
		return conditionid;
	}

	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}

	public ArrayList<Master> getMdicneTypeList() {
		return mdicneTypeList;
	}

	public void setMdicneTypeList(ArrayList<Master> mdicneTypeList) {
		this.mdicneTypeList = mdicneTypeList;
	}

	public String getCancelApmtNote() {
		return cancelApmtNote;
	}

	public void setCancelApmtNote(String cancelApmtNote) {
		this.cancelApmtNote = cancelApmtNote;
	}

	public String getPrintDiaryserid() {
		return printDiaryserid;
	}

	public void setPrintDiaryserid(String printDiaryserid) {
		this.printDiaryserid = printDiaryserid;
	}

	private ArrayList<String>weekNameList;
	ArrayList<ThirdParty> tpnameList;
	public ArrayList<ThirdParty> getTpnameList() {
		return tpnameList;
	}

	public void setTpnameList(ArrayList<ThirdParty> tpnameList) {
		this.tpnameList = tpnameList;
	}

	private String weekName;
	
	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public String getSelectedLocation() {
		return selectedLocation;
	}

	public void setSelectedLocation(String selectedLocation) {
		this.selectedLocation = selectedLocation;
	}

	private String blockWeekName;
	
	
	private String locationid;
	
	private String selectedLocation;
	
	
	private String checkMailSend;
	
	private String previewdiaryuser;
	
	
	
	public String getPreviewdiaryuser() {
		return previewdiaryuser;
	}

	public void setPreviewdiaryuser(String previewdiaryuser) {
		this.previewdiaryuser = previewdiaryuser;
	}

	public String getPrintCommencing() {
		return printCommencing;
	}

	public void setPrintCommencing(String printCommencing) {
		this.printCommencing = printCommencing;
	}

	public String getPrintLocation() {
		return printLocation;
	}

	public void setPrintLocation(String printLocation) {
		this.printLocation = printLocation;
	}

	public String getCheckMailSend() {
		return checkMailSend;
	}

	public void setCheckMailSend(String checkMailSend) {
		this.checkMailSend = checkMailSend;
	}

	public ArrayList<String> getWeekNameList() {
		return weekNameList;
	}

	public void setWeekNameList(ArrayList<String> weekNameList) {
		this.weekNameList = weekNameList;
	}

	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public String getBlockWeekName() {
		return blockWeekName;
	}

	public void setBlockWeekName(String blockWeekName) {
		this.blockWeekName = blockWeekName;
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

	private String pagerecords;
	private int totalRecords;
	
	
	public ArrayList<NotAvailableSlot> getFinderList() {
		return finderList;
	}

	public void setFinderList(ArrayList<NotAvailableSlot> finderList) {
		this.finderList = finderList;
	}

	public String getDashclientId() {
		return dashclientId;
	}

	public void setDashclientId(String dashclientId) {
		this.dashclientId = dashclientId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCaldate() {
		return caldate;
	}

	public void setCaldate(String caldate) {
		this.caldate = caldate;
	}

	private ArrayList<Client> clientOccupationList;
	private ArrayList<Client> refrenceList;
	private ArrayList<String> initialList;
	ArrayList<Client> sourceOfIntroList;
	private ArrayList<String> countryList;
	private ArrayList<Client> surgeryList;
	private ArrayList<Client> thirdPartyTypeList;
	public ArrayList<Client> getThirdPartyTypeList() {
		return thirdPartyTypeList;
	}

	public void setThirdPartyTypeList(ArrayList<Client> thirdPartyTypeList) {
		this.thirdPartyTypeList = thirdPartyTypeList;
	}

	private ArrayList<Client> thirdPartyTypeNameList;
	ArrayList<TreatmentEpisode> sourceOfReferralList;

	
	
	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public ArrayList<TreatmentEpisode> getSourceOfReferralList() {
		return sourceOfReferralList;
	}

	public void setSourceOfReferralList(
			ArrayList<TreatmentEpisode> sourceOfReferralList) {
		this.sourceOfReferralList = sourceOfReferralList;
	}

	private ArrayList<NotAvailableSlot>practitionerApmtList;
	public ArrayList<NotAvailableSlot> getPractitionerApmtList() {
		return practitionerApmtList;
	}

	public void setPractitionerApmtList(
			ArrayList<NotAvailableSlot> practitionerApmtList) {
		this.practitionerApmtList = practitionerApmtList;
	}

	public String getBlocknotes() {
		return blocknotes;
	}

	public void setBlocknotes(String blocknotes) {
		this.blocknotes = blocknotes;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public int getDiaryUserId() {
		return diaryUserId;
	}

	public void setDiaryUserId(int diaryUserId) {
		this.diaryUserId = diaryUserId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getApmtSlotId() {
		return apmtSlotId;
	}

	public void setApmtSlotId(int apmtSlotId) {
		this.apmtSlotId = apmtSlotId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String[] getDateTemp() {
		return dateTemp;
	}

	public void setDateTemp(String[] dateTemp) {
		this.dateTemp = dateTemp;
	}

	public String[] getTemp() {
		return temp;
	}

	public void setTemp(String[] temp) {
		this.temp = temp;
	}

	public ArrayList<String> getStartTimeList() {
		return startTimeList;
	}

	public void setStartTimeList(ArrayList<String> startTimeList) {
		this.startTimeList = startTimeList;
	}

	public ArrayList<String> getEndTimeList() {
		return endTimeList;
	}

	public void setEndTimeList(ArrayList<String> endTimeList) {
		this.endTimeList = endTimeList;
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

	public String getSTime() {
		return sTime;
	}

	public void setSTime(String time) {
		sTime = time;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getApmtDuration() {
		return apmtDuration;
	}

	public void setApmtDuration(String apmtDuration) {
		this.apmtDuration = apmtDuration;
	}

	public ArrayList<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getApmtType() {
		return apmtType;
	}

	public void setApmtType(String apmtType) {
		this.apmtType = apmtType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getBlockslotId() {
		return blockslotId;
	}

	public void setBlockslotId(String blockslotId) {
		this.blockslotId = blockslotId;
	}

	public String getBlockdiaryUserId() {
		return blockdiaryUserId;
	}

	public void setBlockdiaryUserId(String blockdiaryUserId) {
		this.blockdiaryUserId = blockdiaryUserId;
	}

	public String getBlockuser() {
		return blockuser;
	}

	public void setBlockuser(String blockuser) {
		this.blockuser = blockuser;
	}

	public String getBlocklocation() {
		return blocklocation;
	}

	public void setBlocklocation(String blocklocation) {
		this.blocklocation = blocklocation;
	}

	public String getBlockroom() {
		return blockroom;
	}

	public void setBlockroom(String blockroom) {
		this.blockroom = blockroom;
	}

	public String getBlockdate() {
		return blockdate;
	}

	public void setBlockdate(String blockdate) {
		this.blockdate = blockdate;
	}

	public String getBlocksTime() {
		return blocksTime;
	}

	public void setBlocksTime(String blocksTime) {
		this.blocksTime = blocksTime;
	}

	public String getBlockendTime() {
		return blockendTime;
	}

	public void setBlockendTime(String blockendTime) {
		this.blockendTime = blockendTime;
	}

	public String getBlockapmtDuration() {
		return blockapmtDuration;
	}

	public void setBlockapmtDuration(String blockapmtDuration) {
		this.blockapmtDuration = blockapmtDuration;
	}

	public String getReasonforblock() {
		return reasonforblock;
	}

	public void setReasonforblock(String reasonforblock) {
		this.reasonforblock = reasonforblock;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<AppointmentType> getAppointmentTypeList() {
		return appointmentTypeList;
	}

	public void setAppointmentTypeList(
			ArrayList<AppointmentType> appointmentTypeList) {
		this.appointmentTypeList = appointmentTypeList;
	}

	public ArrayList<String> getApmtBlockDurationList() {
		return apmtBlockDurationList;
	}

	public void setApmtBlockDurationList(ArrayList<String> apmtBlockDurationList) {
		this.apmtBlockDurationList = apmtBlockDurationList;
	}

	public String getPractitonerName() {
		return practitonerName;
	}

	public void setPractitonerName(String practitonerName) {
		this.practitonerName = practitonerName;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public ArrayList<Client> getClientOccupationList() {
		return clientOccupationList;
	}

	public void setClientOccupationList(ArrayList<Client> clientOccupationList) {
		this.clientOccupationList = clientOccupationList;
	}

	public ArrayList<Client> getRefrenceList() {
		return refrenceList;
	}

	public void setRefrenceList(ArrayList<Client> refrenceList) {
		this.refrenceList = refrenceList;
	}

	public ArrayList<String> getInitialList() {
		return initialList;
	}

	public void setInitialList(ArrayList<String> initialList) {
		this.initialList = initialList;
	}

	public ArrayList<Client> getSourceOfIntroList() {
		return sourceOfIntroList;
	}

	public void setSourceOfIntroList(ArrayList<Client> sourceOfIntroList) {
		this.sourceOfIntroList = sourceOfIntroList;
	}

	public ArrayList<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<String> countryList) {
		this.countryList = countryList;
	}

	public ArrayList<Client> getSurgeryList() {
		return surgeryList;
	}

	public void setSurgeryList(ArrayList<Client> surgeryList) {
		this.surgeryList = surgeryList;
	}

	public ArrayList<Client> getThirdPartyTypeNameList() {
		return thirdPartyTypeNameList;
	}

	public void setThirdPartyTypeNameList(ArrayList<Client> thirdPartyTypeNameList) {
		this.thirdPartyTypeNameList = thirdPartyTypeNameList;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getSelectedjobgroup() {
		return selectedjobgroup;
	}

	public void setSelectedjobgroup(String selectedjobgroup) {
		this.selectedjobgroup = selectedjobgroup;
	}

	public ArrayList<Priscription> getTemplateNameList() {
		return templateNameList;
	}

	public void setTemplateNameList(ArrayList<Priscription> templateNameList) {
		this.templateNameList = templateNameList;
	}

	public ArrayList<Master> getOtherTemplateList() {
		return otherTemplateList;
	}

	public void setOtherTemplateList(ArrayList<Master> otherTemplateList) {
		this.otherTemplateList = otherTemplateList;
	}

	public String getOtnotes() {
		return otnotes;
	}

	public void setOtnotes(String otnotes) {
		this.otnotes = otnotes;
	}

	public ArrayList<Priscription> getOteqtemplateNameList() {
		return oteqtemplateNameList;
	}

	public void setOteqtemplateNameList(ArrayList<Priscription> oteqtemplateNameList) {
		this.oteqtemplateNameList = oteqtemplateNameList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ArrayList<Client> getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(ArrayList<Client> diagnosisList) {
		this.diagnosisList = diagnosisList;
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

	public void setCitylist(ArrayList<Master> citylist) {
		this.citylist = citylist;
	}

	public String getClinicPhone() {
		return clinicPhone;
	}

	public void setClinicPhone(String clinicPhone) {
		this.clinicPhone = clinicPhone;
	}

	public String getRegisterno() {
		return registerno;
	}

	public void setRegisterno(String registerno) {
		this.registerno = registerno;
	}

	public String getWithpayment() {
		return withpayment;
	}

	public void setWithpayment(String withpayment) {
		this.withpayment = withpayment;
	}

	public String getWithoutpayment() {
		return withoutpayment;
	}

	public void setWithoutpayment(String withoutpayment) {
		this.withoutpayment = withoutpayment;
	}

	public String getPrintedBy() {
		return printedBy;
	}

	public void setPrintedBy(String printedBy) {
		this.printedBy = printedBy;
	}

	public String getUserQualification() {
		return userQualification;
	}

	public void setUserQualification(String userQualification) {
		this.userQualification = userQualification;
	}

	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}

	public ArrayList<Master> getSpecializationTemplateList() {
		return specializationTemplateList;
	}

	public void setSpecializationTemplateList(ArrayList<Master> specializationTemplateList) {
		this.specializationTemplateList = specializationTemplateList;
	}

	public ArrayList<Master> getPriscUnitList() {
		return priscUnitList;
	}

	public void setPriscUnitList(ArrayList<Master> priscUnitList) {
		this.priscUnitList = priscUnitList;
	}

	public ArrayList<Master> getVitalMasterIOList() {
		return vitalMasterIOList;
	}

	public void setVitalMasterIOList(ArrayList<Master> vitalMasterIOList) {
		this.vitalMasterIOList = vitalMasterIOList;
	}

	public ArrayList<Master> getVitalMasterIVList() {
		return vitalMasterIVList;
	}

	public void setVitalMasterIVList(ArrayList<Master> vitalMasterIVList) {
		this.vitalMasterIVList = vitalMasterIVList;
	}

	public ArrayList<Master> getVitalMasterEquipmentList() {
		return vitalMasterEquipmentList;
	}

	public void setVitalMasterEquipmentList(ArrayList<Master> vitalMasterEquipmentList) {
		this.vitalMasterEquipmentList = vitalMasterEquipmentList;
	}

	public ArrayList<String> getIotimeList() {
		return iotimeList;
	}

	public void setIotimeList(ArrayList<String> iotimeList) {
		this.iotimeList = iotimeList;
	}

	public ArrayList<String> getIvtimeList() {
		return ivtimeList;
	}

	public void setIvtimeList(ArrayList<String> ivtimeList) {
		this.ivtimeList = ivtimeList;
	}

	public ArrayList<String> getEqtimeList() {
		return eqtimeList;
	}

	public void setEqtimeList(ArrayList<String> eqtimeList) {
		this.eqtimeList = eqtimeList;
	}

    public ArrayList<Master> getVacinlist() {
		return vacinlist;
	}

	public void setVacinlist(ArrayList<Master> vacinlist) {
		this.vacinlist = vacinlist;
	}
	public int getVacine_type() {
		return vacine_type;
	}

	public void setVacine_type(int vacine_type) {
		this.vacine_type = vacine_type;
	}
	public ArrayList<Bed> getWardlist() {
		return wardlist;
	}
private String lmpdate;
	public void setWardlist(ArrayList<Bed> wardlist) {
		this.wardlist = wardlist;
	}
	public String getLmpdate() {
		return lmpdate;
	}

	public void setLmpdate(String lmpdate) {
		this.lmpdate = lmpdate;
	}
	
	
	
	
	
	public String getTempr() {
		return tempr;
	}

	public void setTempr(String tempr) {
		this.tempr = tempr;
	}

	public String getSpo2() {
		return spo2;
	}

	public void setSpo2(String spo2) {
		this.spo2 = spo2;
	}
	
	
	public String getNewipdabbr() {
		return newipdabbr;
	}

	public void setNewipdabbr(String newipdabbr) {
		this.newipdabbr = newipdabbr;
	}
	public String getFinalDiagnosis() {
		return finalDiagnosis;
	}

	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}
	public String getBsa() {
		return bsa;
	}

	public void setBsa(String bsa) {
		this.bsa = bsa;
	}
	public boolean isBookedstatus() {
		return bookedstatus;
	}

	public void setBookedstatus(boolean bookedstatus) {
		this.bookedstatus = bookedstatus;
	}
	public Client getPatientBmi() {
		return patientBmi;
	}

	public void setPatientBmi(Client patientBmi) {
		this.patientBmi = patientBmi;
	}
	public String getOriuhid() {
		return oriuhid;
	}

	public void setOriuhid(String oriuhid) {
		this.oriuhid = oriuhid;
	}
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public String getAppointmentTypeTest() {
		return appointmentTypeTest;
	}

	public void setAppointmentTypeTest(String appointmentTypeTest) {
		this.appointmentTypeTest = appointmentTypeTest;
	}
	public ArrayList<NotAvailableSlot> getVaccinationlist() {
		return vaccinationlist;
	}

	public void setVaccinationlist(ArrayList<NotAvailableSlot> vaccinationlist) {
		this.vaccinationlist = vaccinationlist;
	}
	public ArrayList<DiaryManagement> getVaccuserList() {
		return vaccuserList;
	}

	public void setVaccuserList(ArrayList<DiaryManagement> vaccuserList) {
		this.vaccuserList = vaccuserList;
	}
	public String getVaccineType() {
		return vaccineType;
	}

	public void setVaccineType(String vaccineType) {
		this.vaccineType = vaccineType;
	}
	public ArrayList<Master> getVaccineMasterList() {
		return vaccineMasterList;
	}

	public void setVaccineMasterList(ArrayList<Master> vaccineMasterList) {
		this.vaccineMasterList = vaccineMasterList;
	}
	public String getVaccineMasterId() {
		return vaccineMasterId;
	}

	public void setVaccineMasterId(String vaccineMasterId) {
		this.vaccineMasterId = vaccineMasterId;
	}
	public ArrayList<NotAvailableSlot> getDepartmentOPDList() {
		return departmentOPDList;
	}

	public void setDepartmentOPDList(ArrayList<NotAvailableSlot> departmentOPDList) {
		this.departmentOPDList = departmentOPDList;
	}
	public String getSelectdepartment() {
		return selectdepartment;
	}

	public void setSelectdepartment(String selectdepartment) {
		this.selectdepartment = selectdepartment;
	}
	public String getPatientcategory() {
		return patientcategory;
	}

	public void setPatientcategory(String patientcategory) {
		this.patientcategory = patientcategory;
	}
	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getPatienttype() {
		return patienttype;
	}

	public void setPatienttype(String patienttype) {
		this.patienttype = patienttype;
	}
	public ArrayList<NotAvailableSlot> getDepartmentOPDPreviewList() {
		return departmentOPDPreviewList;
	}

	public void setDepartmentOPDPreviewList(ArrayList<NotAvailableSlot> departmentOPDPreviewList) {
		this.departmentOPDPreviewList = departmentOPDPreviewList;
	}
	public int getTotalopdcount() {
		return totalopdcount;
	}

	public void setTotalopdcount(int totalopdcount) {
		this.totalopdcount = totalopdcount;
	}
	public ArrayList<Client> getFakepatientlist() {
		return fakepatientlist;
	}

	public void setFakepatientlist(ArrayList<Client> fakepatientlist) {
		this.fakepatientlist = fakepatientlist;
	}
	public String getSelectedids() {
		return selectedids;
	}

	public void setSelectedids(String selectedids) {
		this.selectedids = selectedids;
	}
	public String getUserdepartment() {
		return userdepartment;
	}

	public void setUserdepartment(String userdepartment) {
		this.userdepartment = userdepartment;
	}
	public ArrayList<NotAvailableSlot> getOldfakepatientlist() {
		return oldfakepatientlist;
	}

	public void setOldfakepatientlist(ArrayList<NotAvailableSlot> oldfakepatientlist) {
		this.oldfakepatientlist = oldfakepatientlist;
	}
	public String getOldpselectedid() {
		return oldpselectedid;
	}

	public void setOldpselectedid(String oldpselectedid) {
		this.oldpselectedid = oldpselectedid;
	}
	public ArrayList<DiaryManagement> getSecondaryuserList() {
		return secondaryuserList;
	}

	public void setSecondaryuserList(ArrayList<DiaryManagement> secondaryuserList) {
		this.secondaryuserList = secondaryuserList;
	}
	public String getSecondarydoc() {
		return secondarydoc;
	}

	public void setSecondarydoc(String secondarydoc) {
		this.secondarydoc = secondarydoc;
	}
	public String getReferto() {
		return referto;
	}

	public void setReferto(String referto) {
		this.referto = referto;
	}
	public ArrayList<NotAvailableSlot> getList() {
		return list;
	}

	public void setList(ArrayList<NotAvailableSlot> list) {
		this.list = list;
	}
	public NotAvailableSlot getNotAvailableSlot() {
		return notAvailableSlot;
	}

	public void setNotAvailableSlot(NotAvailableSlot notAvailableSlot) {
		this.notAvailableSlot = notAvailableSlot;
	}
	private ArrayList<Master> vacinlist;
	private int vacine_type;
	private ArrayList<Bed> wardlist;
	private String tempr;
	private String spo2;
	private String newipdabbr;
	private String finalDiagnosis;
	private boolean bookedstatus;
	private  Client patientBmi;
	private Appointment appointment;
	private String vaccineType,vaccineMasterId;
	private ArrayList<Master> vaccineMasterList;
	private ArrayList<NotAvailableSlot> additionalChargeListNew;
	
	public ArrayList<NotAvailableSlot> getAdditionalChargeListNew() {
		return additionalChargeListNew;
	}

	public void setAdditionalChargeListNew(ArrayList<NotAvailableSlot> additionalChargeListNew) {
		this.additionalChargeListNew = additionalChargeListNew;
	}
	private String title;
	private String firstname;
	private String lastname;
	private String mobNo;
	private String year;
	private String state;
	private String gender;
	private String followupDate;
	private String followupReason;
	private ArrayList<NotAvailableSlot> tpNameList;
	private String department;
	private String sitting;
	private String followupdate;
	
	
	
	private String remark;
	private boolean sittingFollowup;
	private String user_id;
    private String date_time;
    private String procedure_list;
    private ArrayList<NotAvailableSlot>deptwisegenderlist;
    public String getProcedure_list() {
		return procedure_list;
	}

	public void setProcedure_list(String procedure_list) {
		this.procedure_list = procedure_list;
	}
	private String all_procedure;
    
    ArrayList<Master>procedurelist;
    
    
    public String getAll_procedure() {
		return all_procedure;
	}

	public void setAll_procedure(String all_procedure) {
		this.all_procedure = all_procedure;
	}

	public ArrayList<Master> getProcedurelist() {
		return procedurelist;
	}

	public void setProcedurelist(ArrayList<Master> procedurelist) {
		this.procedurelist = procedurelist;
	}
    
    public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSitting() {
		return sitting;
	}

	public void setSitting(String sitting) {
		this.sitting = sitting;
	}

	public String getFollowupdate() {
		return followupdate;
	}

	public void setFollowupdate(String followupdate) {
		this.followupdate = followupdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	


	public boolean isSittingFollowup() {
		return sittingFollowup;
	}

	public void setSittingFollowup(boolean sittingFollowup) {
		this.sittingFollowup = sittingFollowup;
	}

	public ArrayList<NotAvailableSlot> getTpNameList() {
		return tpNameList;
	}

	public void setTpNameList(ArrayList<NotAvailableSlot> tpNameList) {
		this.tpNameList = tpNameList;
	}

	public String getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(String followupDate) {
		this.followupDate = followupDate;
	}

	public String getFollowupReason() {
		return followupReason;
	}

	public void setFollowupReason(String followupReason) {
		this.followupReason = followupReason;
	}

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

	

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private ArrayList<NotAvailableSlot>patienthistorylist;
	public ArrayList<NotAvailableSlot> getPatienthistorylist() {
		return patienthistorylist;
	}

	public void setPatienthistorylist(ArrayList<NotAvailableSlot> patienthistorylist) {
		this.patienthistorylist = patienthistorylist;
	}
}
