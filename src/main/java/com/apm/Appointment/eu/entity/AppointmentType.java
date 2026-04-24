package com.apm.Appointment.eu.entity;

import java.util.ArrayList;

public class AppointmentType {
	public String getSessionno() {
		return sessionno;
	}

	public void setSessionno(String sessionno) {
		this.sessionno = sessionno;
	}

	private String myid;
	private int id;
	
	private String name;
	
	private String description;
	
	private String category;
	
	private String duration;
	
	private String color;
	
	private String basecharge="0";
	private String charges;
	
	private String chargesx;
	
	private String tptype;
	
	private String tpName;
	private String stdcharge="0";
	private String onoff="0";
	private String hours="0";
	private String ratio="0";
	
	private String chargeid;
	private String tpid;
	private String wardid;
	private String mrp;
	private String code;
	private boolean ch;
	private String shareablecharge="0";
	private String wardname;
	private String discountShare;
	private double originalAmt;
	private String actualAmount;
	private double discountAmt;
	private String invest_type;
	private String clinic_name_id;
	private String sessionno;
	

	public String getClinic_name_id() {
		return clinic_name_id;
	}

	public void setClinic_name_id(String clinic_name_id) {
		this.clinic_name_id = clinic_name_id;
	}

	public double getDiscountAmt() {
		return discountAmt;
	}

	public String getInvest_type() {
		return invest_type;
	}

	public void setInvest_type(String invest_type) {
		this.invest_type = invest_type;
	}

	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	public double getOriginalAmt() {
		return originalAmt;
	}

	public void setOriginalAmt(double originalAmt) {
		this.originalAmt = originalAmt;
	}

	public String getDiscountShare() {
		return discountShare;
	}

	public void setDiscountShare(String discountShare) {
		this.discountShare = discountShare;
	}

	public String getShareablecharge() {
		return shareablecharge;
	}

	public void setShareablecharge(String shareablecharge) {
		this.shareablecharge = shareablecharge;
	}

	public boolean isCh() {
		return ch;
	}

	public void setCh(boolean ch) {
		this.ch = ch;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTptype() {
		return tptype;
	}

	public void setTptype(String tptype) {
		this.tptype = tptype;
	}

	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public String getChargesx() {
		return chargesx;
	}

	public void setChargesx(String chargesx) {
		this.chargesx = chargesx;
	}

	private String location;
	private String chargeType;
	
	private String reportField;
	
	public String getReportField() {
		return reportField;
	}

	public void setReportField(String reportField) {
		this.reportField = reportField;
	}


	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getChargeid() {
		return chargeid;
	}

	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}

	public String getTpid() {
		return tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getStdcharge() {
		return stdcharge;
	}

	public void setStdcharge(String stdcharge) {
		this.stdcharge = stdcharge;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getBasecharge() {
		return basecharge;
	}

	public void setBasecharge(String basecharge) {
		this.basecharge = basecharge;
	}

	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	public String getWardname() {
		return wardname;
	}

	public void setWardname(String wardname) {
		this.wardname = wardname;
	}
public int getNoneditamt() {
		return noneditamt;
	}

	public void setNoneditamt(int noneditamt) {
		this.noneditamt = noneditamt;
	}

public String getColumnchargename() {
		return columnchargename;
	}

	public void setColumnchargename(String columnchargename) {
		this.columnchargename = columnchargename;
	}

private int noneditamt;
private String columnchargename;
}
