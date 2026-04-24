package com.apm.Dietary.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.Dietary.eu.entity.Dietary;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;

public class DietaryDetailsForm {
	
	private String patientid;
	private String dietician_incharge;
	 public String getDietician_incharge() {
		return dietician_incharge;
	}
	public void setDietician_incharge(String dietician_incharge) {
		this.dietician_incharge = dietician_incharge;
	}
	ArrayList<Bed>wardlistname;
	public ArrayList<Bed> getWardlistname() {
		return wardlistname;
	}
	public void setWardlistname(ArrayList<Bed> wardlistname) {
		this.wardlistname = wardlistname;
	}
	private String remark;
	private String diaetplan;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDiaetplan() {
		return diaetplan;
	}
	public void setDiaetplan(String diaetplan) {
		this.diaetplan = diaetplan;
	}
	private String patient_id;
	private String diettimeshift;
	public String getDiettimeshift() {
		return diettimeshift;
	}
	public void setDiettimeshift(String diettimeshift) {
		this.diettimeshift = diettimeshift;
	}
	private Collection<DietaryDetails> dietdata;
	ArrayList<Bed>showgeneraldietplanlist;
	ArrayList<Bed>generaldietplanlist;
	public ArrayList<Bed> getGeneraldietplanlist() {
		return generaldietplanlist;
	}
	public void setGeneraldietplanlist(ArrayList<Bed> generaldietplanlist) {
		this.generaldietplanlist = generaldietplanlist;
	}
	public ArrayList<Bed> getShowgeneraldietplanlist() {
		return showgeneraldietplanlist;
	}
	public void setShowgeneraldietplanlist(ArrayList<Bed> showgeneraldietplanlist) {
		this.showgeneraldietplanlist = showgeneraldietplanlist;
	}
	private String childid;
	
	private String pid;
	public String getDiettime() {
		return diettime;
	}
	public void setDiettime(String diettime) {
		this.diettime = diettime;
	}
	private String diettime;
	private String lastmodifieddate;
	private double total;
	private double totalProtein;
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getTotalProtein() {
		return totalProtein;
	}
	public void setTotalProtein(double totalProtein) {
		this.totalProtein = totalProtein;
	}
	public double getTotalCalories() {
		return totalCalories;
	}
	public void setTotalCalories(double totalCalories) {
		this.totalCalories = totalCalories;
	}
	private double totalCalories;
	private String clinicName;
	private String clinicOwner;
	private String owner_qualification;
	private String landLine;
	private String clinicemail;
	private String websiteUrl;
	private String clinicLogo;
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
	public String getOwner_qualification() {
		return owner_qualification;
	}
	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}
	public String getLandLine() {
		return landLine;
	}
	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}
	public String getClinicemail() {
		return clinicemail;
	}
	public void setClinicemail(String clinicemail) {
		this.clinicemail = clinicemail;
	}
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	public String getClinicLogo() {
		return clinicLogo;
	}
	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}
	private String age;
	
	private String gender;
	
	private String consultant;
	
	private String admissiondate;
	
	private String givendiet;
	
	private String totaldiet;
	
	private String searchtext;
	
	private String fromdate;
	
	private String todate;
	
	private String wardnameid;
	
	ArrayList<Bed> wardlist;
	
	private String status;
	
	private ArrayList<DietaryDetails> cafeusername;
	
	private ArrayList<DietaryDetails> viewdietplan;
	
	private ArrayList<DietaryDetails> dietservelist;

	private ArrayList<DietaryDetails> diethistorylist;
	
	private int parentid;
	
	private int parentidc;
	
	private String lastmodified;
	
	private String bedname;
	
	private String wardname;
	
	private String clientname;
	
	private String ipdid;
	
	private String clientid;
	
	private ArrayList<Bed> bedlist; 
	
	private String caloriesid;
	
	private String calories;
	
	private String dietplan;
	
	private String category;
	
	private String subcategory;
	
	private String subcategoryid;
	
	private String feed;
	
	private String duration;
	
	private int id;
	
	private String categoryid;
	
	private String name;
	
	private String energy;
	
	private String protein;
	
	ArrayList<DietaryDetails> DietarycaloriesList;
	
	ArrayList<DietaryDetails> listdietarydetails; 
	
	ArrayList<DietaryDetails> DietarycategorydetailsList;
	
	ArrayList<Master> masterlist;
	
	ArrayList<Dietary> dietarycategoryList; 
	
	private String mastername;
	
	private ArrayList<DietaryDetails> dietplanlist;
	private ArrayList<DietaryDetails> dietfeedlist;
	private ArrayList<DietaryDetails> templatelist;
	
	public ArrayList<DietaryDetails> getTemplatelist() {
		return templatelist;
	}
	public void setTemplatelist(ArrayList<DietaryDetails> templatelist) {
		this.templatelist = templatelist;
	}
	public ArrayList<DietaryDetails> getDietplanlist() {
		return dietplanlist;
	}
	public void setDietplanlist(ArrayList<DietaryDetails> dietplanlist) {
		this.dietplanlist = dietplanlist;
	}
	public ArrayList<DietaryDetails> getDietfeedlist() {
		return dietfeedlist;
	}
	public void setDietfeedlist(ArrayList<DietaryDetails> dietfeedlist) {
		this.dietfeedlist = dietfeedlist;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
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
	public String getEnergy() {
		return energy;
	}
	public void setEnergy(String energy) {
		this.energy = energy;
	}
	public String getProtein() {
		return protein;
	}
	public void setProtein(String protein) {
		this.protein = protein;
	}
	public ArrayList<DietaryDetails> getListdietarydetails() {
		return listdietarydetails;
	}
	public void setListdietarydetails(ArrayList<DietaryDetails> listdietarydetails) {
		this.listdietarydetails = listdietarydetails;
	}
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}
	public ArrayList<Dietary> getDietarycategoryList() {
		return dietarycategoryList;
	}
	public void setDietarycategoryList(ArrayList<Dietary> dietarycategoryList) {
		this.dietarycategoryList = dietarycategoryList;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
	public String getDietplan() {
		return dietplan;
	}
	public void setDietplan(String dietplan) {
		this.dietplan = dietplan;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feed) {
		this.feed = feed;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public ArrayList<DietaryDetails> getDietarycaloriesList() {
		return DietarycaloriesList;
	}
	public void setDietarycaloriesList(ArrayList<DietaryDetails> dietarycaloriesList) {
		DietarycaloriesList = dietarycaloriesList;
	}
	
	public ArrayList<DietaryDetails> getDietarycategorydetailsList() {
		return DietarycategorydetailsList;
	}
	public void setDietarycategorydetailsList(
			ArrayList<DietaryDetails> dietarycategorydetailsList) {
		DietarycategorydetailsList = dietarycategorydetailsList;
	}
	public ArrayList<Bed> getBedlist() {
		return bedlist;
	}
	public void setBedlist(ArrayList<Bed> bedlist) {
		this.bedlist = bedlist;
	}
	public String getSubcategoryid() {
		return subcategoryid;
	}
	public void setSubcategoryid(String subcategoryid) {
		this.subcategoryid = subcategoryid;
	}
	public String getCaloriesid() {
		return caloriesid;
	}
	public void setCaloriesid(String caloriesid) {
		this.caloriesid = caloriesid;
	}
	public String getLastmodified() {
		return lastmodified;
	}
	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}
	public String getBedname() {
		return bedname;
	}
	public void setBedname(String bedname) {
		this.bedname = bedname;
	}
	public String getWardname() {
		return wardname;
	}
	public void setWardname(String wardname) {
		this.wardname = wardname;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public ArrayList<DietaryDetails> getDietservelist() {
		return dietservelist;
	}
	public void setDietservelist(ArrayList<DietaryDetails> dietservelist) {
		this.dietservelist = dietservelist;
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
	public ArrayList<DietaryDetails> getViewdietplan() {
		return viewdietplan;
	}
	public void setViewdietplan(ArrayList<DietaryDetails> viewdietplan) {
		this.viewdietplan = viewdietplan;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public int getParentidc() {
		return parentidc;
	}
	public void setParentidc(int parentidc) {
		this.parentidc = parentidc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<DietaryDetails> getCafeusername() {
		return cafeusername;
	}
	public void setCafeusername(ArrayList<DietaryDetails> cafeusername) {
		this.cafeusername = cafeusername;
	}
	public ArrayList<Bed> getWardlist() {
		return wardlist;
	}
	public void setWardlist(ArrayList<Bed> wardlist) {
		this.wardlist = wardlist;
	}
	public String getSearchtext() {
		return searchtext;
	}
	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
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
	public String getWardnameid() {
		return wardnameid;
	}
	public void setWardnameid(String wardnameid) {
		this.wardnameid = wardnameid;
	}
	public String getGivendiet() {
		return givendiet;
	}
	public void setGivendiet(String givendiet) {
		this.givendiet = givendiet;
	}
	public String getTotaldiet() {
		return totaldiet;
	}
	public void setTotaldiet(String totaldiet) {
		this.totaldiet = totaldiet;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getConsultant() {
		return consultant;
	}
	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}
	public String getAdmissiondate() {
		return admissiondate;
	}
	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}
	public String getLastmodifieddate() {
		return lastmodifieddate;
	}
	public void setLastmodifieddate(String lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}
	public ArrayList<DietaryDetails> getDiethistorylist() {
		return diethistorylist;
	}
	public void setDiethistorylist(ArrayList<DietaryDetails> diethistorylist) {
		this.diethistorylist = diethistorylist;
	}
	public Collection<DietaryDetails> getDietdata() {
		return dietdata;
	}
	public void setDietdata(Collection<DietaryDetails> dietdata) {
		this.dietdata = dietdata;
	}
	public String getChildid() {
		return childid;
	}
	public void setChildid(String childid) {
		this.childid = childid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAllward() {
		return allward;
	}
	public void setAllward(String allward) {
		this.allward = allward;
	}
	private ArrayList<Clinic> locationAdressList;
	private String address;
	private String date;
	private String allward;
	private  int count=0,fulldiet=0,softdiet=0,liquiddiet=0,diabetic=0,rtfeed=0,renal=0,Hepatic=0,clearliquid=0,semisolid=0,blended=0,nbm=0;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getFulldiet() {
		return fulldiet;
	}
	public void setFulldiet(int fulldiet) {
		this.fulldiet = fulldiet;
	}
	public int getSoftdiet() {
		return softdiet;
	}
	public void setSoftdiet(int softdiet) {
		this.softdiet = softdiet;
	}
	public int getLiquiddiet() {
		return liquiddiet;
	}
	public void setLiquiddiet(int liquiddiet) {
		this.liquiddiet = liquiddiet;
	}
	public int getDiabetic() {
		return diabetic;
	}
	public void setDiabetic(int diabetic) {
		this.diabetic = diabetic;
	}
	public int getRtfeed() {
		return rtfeed;
	}
	public void setRtfeed(int rtfeed) {
		this.rtfeed = rtfeed;
	}
	public int getRenal() {
		return renal;
	}
	public void setRenal(int renal) {
		this.renal = renal;
	}
	public int getHepatic() {
		return Hepatic;
	}
	public void setHepatic(int hepatic) {
		Hepatic = hepatic;
	}
	public int getClearliquid() {
		return clearliquid;
	}
	public void setClearliquid(int clearliquid) {
		this.clearliquid = clearliquid;
	}
	public int getSemisolid() {
		return semisolid;
	}
	public void setSemisolid(int semisolid) {
		this.semisolid = semisolid;
	}
	public int getBlended() {
		return blended;
	}
	public void setBlended(int blended) {
		this.blended = blended;
	}
	public int getNbm() {
		return nbm;
	}
	public void setNbm(int nbm) {
		this.nbm = nbm;
	}
public String getPrintnew() {
		return printnew;
	}
	public void setPrintnew(String printnew) {
		this.printnew = printnew;
	}
public String getDqty() {
		return dqty;
	}
	public void setDqty(String dqty) {
		this.dqty = dqty;
	}
public String getSodium() {
		return sodium;
	}
	public void setSodium(String sodium) {
		this.sodium = sodium;
	}
public String getPotassium() {
		return potassium;
	}
	public void setPotassium(String potassium) {
		this.potassium = potassium;
	}
private	String printnew; 
private String dqty,sodium,potassium;
}
