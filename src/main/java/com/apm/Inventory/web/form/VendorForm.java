package com.apm.Inventory.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Master.eu.entity.Master;

public class VendorForm {

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
	private String index;
	private String category_id;
	
	private ArrayList<Priscription> piscriptionList;
	private ArrayList<Product> categoryList;
	ArrayList<Master> locationListPharmacy;
	private String location="0";
	private ArrayList<Product> medicineList;
	private String tinno;
	public String getSuppliercode() {
		return suppliercode;
	}
	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}


	private String city;
	private String state;
	private String postcode;
	private String suppliercode;
	ArrayList<Master> stateList;
	ArrayList<Master> cityList;
	
	Collection<Vendor> vendors;
	
	ArrayList<Vendor> vendorList;
	
	private String pagerecords;
	private int totalRecords;
	
    private String mastername;
	
    
    ArrayList<Master> masterlist;
    
    ArrayList<Product> brandnameList;
    
    
    ArrayList<Product> productList;
    private String searchtext;
    
	public String getSearchtext() {
		return searchtext;
	}
	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}
	public ArrayList<Product> getProductList() {
		return productList;
	}
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	public Collection<Vendor> getVendors() {
		return vendors;
	}
	public void setVendors(Collection<Vendor> vendors) {
		this.vendors = vendors;
	}
	public ArrayList<Product> getBrandnameList() {
		return brandnameList;
	}
	public void setBrandnameList(ArrayList<Product> brandnameList) {
		this.brandnameList = brandnameList;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
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
	public ArrayList<Vendor> getVendorList() {
		return vendorList;
	}
	public void setVendorList(ArrayList<Vendor> vendorList) {
		this.vendorList = vendorList;
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
	public ArrayList<Master> getStateList() {
		return stateList;
	}
	public void setStateList(ArrayList<Master> stateList) {
		this.stateList = stateList;
	}
	public ArrayList<Master> getCityList() {
		return cityList;
	}
	public void setCityList(ArrayList<Master> cityList) {
		this.cityList = cityList;
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
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public ArrayList<Priscription> getPiscriptionList() {
		return piscriptionList;
	}
	public void setPiscriptionList(ArrayList<Priscription> piscriptionList) {
		this.piscriptionList = piscriptionList;
	}
	public ArrayList<Product> getMedicineList() {
		return medicineList;
	}
	public void setMedicineList(ArrayList<Product> medicineList) {
		this.medicineList = medicineList;
	}
	public ArrayList<Master> getLocationListPharmacy() {
		return locationListPharmacy;
	}
	public void setLocationListPharmacy(ArrayList<Master> locationListPharmacy) {
		this.locationListPharmacy = locationListPharmacy;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ArrayList<Product> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(ArrayList<Product> categoryList) {
		this.categoryList = categoryList;
	}
	
	
	public String getCreditdays() {
		return creditdays;
	}
	public void setCreditdays(String creditdays) {
		this.creditdays = creditdays;
	}


	private String creditdays;
	private String drug;

	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	
	
}
