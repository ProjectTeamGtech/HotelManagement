package com.apm.Appointment.eu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Branch implements Serializable {

	
	/** id of user (generated automatically) */

	private int id;		
    private String userId;	
	private String password;
	private String branchName;
	private String description;
	private int userType;
	
	private String checkMailSend;
	
	
	public String getCheckMailSend() {
		return checkMailSend;
	}

	public void setCheckMailSend(String checkMailSend) {
		this.checkMailSend = checkMailSend;
	}

	/** Email Id of user */
	private String email;
	
	/** user's address */
	private String address;
           
	/** Country of user */
	private String country;
	
	/** State of user (selected from drop down) */
	private String state;
	
	/** State of user (typed in text box) */
	private String stateText;
	
	/** City of user (selected from drop down) */
	private String city;
	
	/** City of user (typed in text box) */
	private String cityText;
	
	/** Mobile number of user */
	private String mobileNo;
	
	/** Landline number of user */
	private String landLine;
	
	private String pincode;
	
	private String companyName;
	
	
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Branch(){
		
	}
	
	public Branch(int id,String branchName){
		this.id = id;
		this.branchName = branchName;
	}
	
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateText() {
		return stateText;
	}

	public void setStateText(String stateText) {
		this.stateText = stateText;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityText() {
		return cityText;
	}

	public void setCityText(String cityText) {
		this.cityText = cityText;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	 
	

	


	


	

	
  
    
}
