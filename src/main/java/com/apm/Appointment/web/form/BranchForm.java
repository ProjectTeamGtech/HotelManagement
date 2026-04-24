package com.apm.Appointment.web.form;

import java.util.ArrayList;

import com.apm.Appointment.eu.entity.Branch;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Master.eu.entity.Master;

public class BranchForm {
	
	private int id;
	
	private String userId;
	
	private String password;
	
	private String branchName;
	
	private int clientid;
	private String description;
	
	private ArrayList<Branch> branchList;
	
	private ArrayList<String> initialList;
	
	private ArrayList<Client> condtitionList;
	private ArrayList<Master>disciplineList;
	
	/** Email Id of user */
	private String email;
	private String otptext;
	
	
	/** user's address */
	private String address;
           
	/** Country of user */
	private String country;
	
	/** Country list to be populated for drop down country list */
	private ArrayList<String> countryList;

	/** State of user (selected from drop down) */
	private String state;
	
	/** State of user (typed in text box) */
	private String stateText;
	
	/** State list to be populated for drop down state list */
	private ArrayList<String> stateList;

	/** City of user (selected from drop down) */
	private String city;
	
	/** City of user (typed in text box) */
	private String cityText;
	
	/** City list to be populated for drop down city list */
	private ArrayList<String> cityList;
	
	/** Mobile number of user */
	private String mobileNo;
	
	/** Landline number of user */
	private String landLine;
	
	private String pinCode;
	
	private String companyName;
	
	private String dbType;
	
	private String checkMailSend;
	
	private String actiontype="0";
	
	
	
	
	public String getActiontype() {
		return actiontype;
	}



	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}



	public String getCheckMailSend() {
		return checkMailSend;
	}



	public void setCheckMailSend(String checkMailSend) {
		this.checkMailSend = checkMailSend;
	}



	public String getDbType() {
		return dbType;
	}



	public void setDbType(String dbType) {
		this.dbType = dbType;
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



	public ArrayList<String> getCountryList() {
		return countryList;
	}



	public void setCountryList(ArrayList<String> countryList) {
		this.countryList = countryList;
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



	public ArrayList<String> getStateList() {
		return stateList;
	}



	public void setStateList(ArrayList<String> stateList) {
		this.stateList = stateList;
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



	public ArrayList<String> getCityList() {
		return cityList;
	}



	public void setCityList(ArrayList<String> cityList) {
		this.cityList = cityList;
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





	public String getPinCode() {
		return pinCode;
	}



	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}



	public int getId() {
		return id;
	}

	

	public ArrayList<Branch> getBranchList() {
		return branchList;
	}



	public void setBranchList(ArrayList<Branch> branchList) {
		this.branchList = branchList;
	}



	public void setId(int id) {
		this.id = id;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public int getClientid() {
		return clientid;
	}



	public void setClientid(int clientid) {
		this.clientid = clientid;
	}



	public ArrayList<String> getInitialList() {
		return initialList;
	}



	public void setInitialList(ArrayList<String> initialList) {
		this.initialList = initialList;
	}



	public ArrayList<Client> getCondtitionList() {
		return condtitionList;
	}



	public void setCondtitionList(ArrayList<Client> condtitionList) {
		this.condtitionList = condtitionList;
	}



	public String getOtptext() {
		return otptext;
	}



	public void setOtptext(String otptext) {
		this.otptext = otptext;
	}



	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}



	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}
	
	
	
}
