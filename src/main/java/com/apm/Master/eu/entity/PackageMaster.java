package com.apm.Master.eu.entity;

import java.util.ArrayList;
import java.util.Collection;


public class PackageMaster {
	private int child_id;
	private int id;
	private String name;
	private String amount;
	private String chargeid;
	private String chargename;
	private String percentage;
	private String cal_amount;
	private String package_name;
	private String package_amount;
	private String chargetype;
	private String packagetype;
	private String days, description;
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
	private ArrayList<PackageMaster> childpackagelist;
	private String ischargetype;
public String getIschargetype() {
		return ischargetype;
	}
	public void setIschargetype(String ischargetype) {
		this.ischargetype = ischargetype;
	}
public ArrayList<PackageMaster> getChildpackagelist() {
		return childpackagelist;
	}
	public void setChildpackagelist(ArrayList<PackageMaster> childpackagelist) {
		this.childpackagelist = childpackagelist;
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
public String getChargeid() {
	return chargeid;
}
public void setChargeid(String chargeid) {
	this.chargeid = chargeid;
}
public String getChargename() {
	return chargename;
}
public void setChargename(String chargename) {
	this.chargename = chargename;
}
public String getPercentage() {
	return percentage;
}
public void setPercentage(String percentage) {
	this.percentage = percentage;
}
public String getCal_amount() {
	return cal_amount;
}
public void setCal_amount(String cal_amount) {
	this.cal_amount = cal_amount;
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
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public int getChild_id() {
	return child_id;
}
public void setChild_id(int child_id) {
	this.child_id = child_id;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public boolean isTp() {
	return tp;
}
public void setTp(boolean tp) {
	this.tp = tp;
}
public Collection<PackageMaster> getPkglist() {
	return pkglist;
}
public void setPkglist(Collection<PackageMaster> pkglist) {
	this.pkglist = pkglist;
}
private String code;
private boolean tp;
private Collection<PackageMaster> pkglist;
}
