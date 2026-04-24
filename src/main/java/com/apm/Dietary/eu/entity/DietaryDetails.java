package com.apm.Dietary.eu.entity;

import java.util.ArrayList;
import java.util.Collection;

public class DietaryDetails {

	private String age;
	
	private String bedid;
	
	private String wardid;
	
	private String templatename;
	private double total;
	private double totalProtein;
	private double totalCalories;
	
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
	private Collection<DietaryDetails> dietdata;
	
	private String lastmodifieddate;
	
	private String childid;
	
	private String pid;
	
	private String patientid;
	
	private String givendiet;
	
	private String totaldiet;
	
	private ArrayList<DietaryDetails> dteamdietplanList;
	
	private ArrayList<DietaryDetails> viewteammember;
	
	private String cafeteamname;
	
	private String cafename;
	
	private ArrayList<DietaryDetails> cafeusername;
	
	
	private String status;
	
	private String parentid;
	
	
	private String dietstatus;
	
	private int parentidc;
	
	private String ipdid;
	
	private String clientid;
	
	private String lastmodified;
	
	private String bedname;
	
	private String wardname;
	
	private String clientname;

	private ArrayList<DietaryDetails> viewdietplan;
	
	private String calories;
	
	private String caloriesid;
		
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
	private String description;
	private String feedname;
	private String dietplanname;
	private String executed;
	private String userid;
	private String datetime;
	private ArrayList<DietaryDetails> dietgivenlist;
	public ArrayList<DietaryDetails> getDietgivenlist() {
		return dietgivenlist;
	}
	public void setDietgivenlist(ArrayList<DietaryDetails> dietgivenlist) {
		this.dietgivenlist = dietgivenlist;
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
	public String getExecuted() {
		return executed;
	}
	public void setExecuted(String executed) {
		this.executed = executed;
	}
	public String getDietplanname() {
		return dietplanname;
	}
	public void setDietplanname(String dietplanname) {
		this.dietplanname = dietplanname;
	}
	public String getFeedname() {
		return feedname;
	}
	public void setFeedname(String feedname) {
		this.feedname = feedname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public int getParentidc() {
		return parentidc;
	}
	public void setParentidc(int parentidc) {
		this.parentidc = parentidc;
	}
	public ArrayList<DietaryDetails> getViewdietplan() {
		return viewdietplan;
	}
	public void setViewdietplan(ArrayList<DietaryDetails> viewdietplan) {
		this.viewdietplan = viewdietplan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCafename() {
		return cafename;
	}
	public void setCafename(String cafename) {
		this.cafename = cafename;
	}
	public ArrayList<DietaryDetails> getCafeusername() {
		return cafeusername;
	}
	public void setCafeusername(ArrayList<DietaryDetails> cafeusername) {
		this.cafeusername = cafeusername;
	}
	public String getCafeteamname() {
		return cafeteamname;
	}
	public void setCafeteamname(String cafeteamname) {
		this.cafeteamname = cafeteamname;
	}
	public ArrayList<DietaryDetails> getViewteammember() {
		return viewteammember;
	}
	public void setViewteammember(ArrayList<DietaryDetails> viewteammember) {
		this.viewteammember = viewteammember;
	}
	public String getDietstatus() {
		return dietstatus;
	}
	public void setDietstatus(String dietstatus) {
		this.dietstatus = dietstatus;
	}
	public ArrayList<DietaryDetails> getDteamdietplanList() {
		return dteamdietplanList;
	}
	public void setDteamdietplanList(ArrayList<DietaryDetails> dteamdietplanList) {
		this.dteamdietplanList = dteamdietplanList;
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
	public String getLastmodifieddate() {
		return lastmodifieddate;
	}
	public void setLastmodifieddate(String lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}
	public String getChildid() {
		return childid;
	}
	public void setChildid(String childid) {
		this.childid = childid;
	}
	public Collection<DietaryDetails> getDietdata() {
		return dietdata;
	}
	public void setDietdata(Collection<DietaryDetails> dietdata) {
		this.dietdata = dietdata;
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
	public String getTemplatename() {
		return templatename;
	}
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	public String getBedid() {
		return bedid;
	}
	public void setBedid(String bedid) {
		this.bedid = bedid;
	}
	public String getWardid() {
		return wardid;
	}
	public void setWardid(String wardid) {
		this.wardid = wardid;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public double getSodium() {
		return sodium;
	}
	public void setSodium(double sodium) {
		this.sodium = sodium;
	}
	public double getPotassium() {
		return potassium;
	}
	public void setPotassium(double potassium) {
		this.potassium = potassium;
	}
	public int getDqty() {
		return dqty;
	}
	public void setDqty(int dqty) {
		this.dqty = dqty;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private double sodium,potassium;
	private int dqty;
	private String remark;
}
