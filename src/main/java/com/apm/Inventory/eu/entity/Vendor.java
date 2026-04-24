package com.apm.Inventory.eu.entity;

public class Vendor {
    private String vendoremail;
	private int id;
	private String name;
	private String address;
	private String email;
	private String brand_name;
	private String mobile_pri;
	private String mobile_sec;
	private String phone1;
	private String phone2;
	private String min_delivery_time;
	private String vendorlist;
	
	private String tinno;
	private String city;
	private String state;
	private String data;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getSuppliercode() {
		return suppliercode;
	}
	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}

	private String cityName;
	private String stateName;
	private String postcode;
	private String location;
	private String grnpovendorid;
	private int isfrombloodbank;
	private String vendorGstNo;
	private double podiscamt;
	private String suppliercode;
	
	public double getPodiscamt() {
		return podiscamt;
	}
	public void setPodiscamt(double podiscamt) {
		this.podiscamt = podiscamt;
	}
	public String getVendorGstNo() {
		return vendorGstNo;
	}
	public void setVendorGstNo(String vendorGstNo) {
		this.vendorGstNo = vendorGstNo;
	}
	public int getIsfrombloodbank() {
		return isfrombloodbank;
	}
	public void setIsfrombloodbank(int isfrombloodbank) {
		this.isfrombloodbank = isfrombloodbank;
	}
	public String getGrnpovendorid() {
		return grnpovendorid;
	}
	public void setGrnpovendorid(String grnpovendorid) {
		this.grnpovendorid = grnpovendorid;
	}
	public String getVendoremail() {
		return vendoremail;
	}
	public void setVendoremail(String vendoremail) {
		this.vendoremail = vendoremail;
	}

	
	public String getTinno() {
		return tinno;
	}
	public void setTinno(String tinno) {
		this.tinno = tinno;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getMobile_pri() {
		return mobile_pri;
	}
	public void setMobile_pri(String mobile_pri) {
		this.mobile_pri = mobile_pri;
	}
	public String getMobile_sec() {
		return mobile_sec;
	}
	public void setMobile_sec(String mobile_sec) {
		this.mobile_sec = mobile_sec;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getMin_delivery_time() {
		return min_delivery_time;
	}
	public void setMin_delivery_time(String min_delivery_time) {
		this.min_delivery_time = min_delivery_time;
	}
	public String getVendorlist() {
		return vendorlist;
	}
	public void setVendorlist(String vendorlist) {
		this.vendorlist = vendorlist;
	}
	
	private String productlist;

	public String getProductlist() {
		return productlist;
	}
	public void setProductlist(String productlist) {
		this.productlist = productlist;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getCreditdays() {
		return creditdays;
	}
	public void setCreditdays(String creditdays) {
		this.creditdays = creditdays;
	}
	private String bankname,ifsc,accountno,branch;
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}

	private String creditdays;
	private String drug;
}
