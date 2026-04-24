package com.apm.Registration.eu.entity;

public class Location {
	
	private String locationid;
	
	private String country;
	
	private String  city;
	
	private String address;
	
	private String pinCode;
	
	private String location;
	
	private String colorName;
	
	private String locationname;
	private String contactNo;
	
	private String checkLocation;
	
	private String addressType;
	
	private String emailId;
	
	
	public String getAddressType() {
		return addressType;
	}


	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getCheckLocation() {
		return checkLocation;
	}


	public void setCheckLocation(String checkLocation) {
		this.checkLocation = checkLocation;
	}


	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCountry() {
		return country;
	}

	public String getLocationid() {
		return locationid;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

}
