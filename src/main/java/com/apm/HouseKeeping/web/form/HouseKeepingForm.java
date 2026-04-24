package com.apm.HouseKeeping.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Master.eu.entity.Master;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Registration.eu.entity.UserProfile;

public class HouseKeepingForm {

	private int id;
	private String name;
	private String category_id;
	private String description;
	private String subcategory_id;
	private String brand_id;
	
	//
	private ArrayList<Vendor> vendorList;

	private Collection<Product> procurements;
	ArrayList<UserProfile> userstaffList;

	private String vendor_id;
	ArrayList<Product> categoryList;
	ArrayList<Master> masterlist;
	private String mastername;
	private String equipment_id;
	private String due_date;
	private String remainder_on;
	private String frequency;

	private String fromdate;
	private String todate;
	private String pagerecords;
	private int totalRecords;
	ArrayList<Product> subcategoryList;
	ArrayList<Product> brandnameList;

	private Collection<Product> products;

	ArrayList<Product> productList;
	
   private ArrayList<Product> machines;
	private ArrayList<Product> serviceproviderList;
	
	private String category;
	private String subcategory;
	private String brand;
	private String machineid;
	
	private String staff_id;
	private String date;
    private String product_id;
	private String product_code;
	private String product_typeid;
	private String product_name;
	private String mrp;
	private String purchase_price;
	private String sale_price;
	private String purchase_discount;
	private String sale_discount;
	private String weight;
	private String unit;
	private String vendor;

	private String stock;
	private String min_delivery_time;
    private ArrayList<Product> linenList;
	private String quantity;

	private ArrayList<Product> laundryList;
	
	
	public ArrayList<Product> getLaundryList() {
		return laundryList;
	}

	public void setLaundryList(ArrayList<Product> laundryList) {
		this.laundryList = laundryList;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public ArrayList<UserProfile> getUserstaffList() {
		return userstaffList;
	}

	public void setUserstaffList(ArrayList<UserProfile> userstaffList) {
		this.userstaffList = userstaffList;
	}

	public Collection<Product> getProcurements() {
		return procurements;
	}

	public void setProcurements(Collection<Product> procurements) {
		this.procurements = procurements;
	}

	public String getMin_delivery_time() {
		return min_delivery_time;
	}

	public void setMin_delivery_time(String min_delivery_time) {
		this.min_delivery_time = min_delivery_time;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_typeid() {
		return product_typeid;
	}

	public void setProduct_typeid(String product_typeid) {
		this.product_typeid = product_typeid;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(String purchase_price) {
		this.purchase_price = purchase_price;
	}

	public String getSale_price() {
		return sale_price;
	}

	public void setSale_price(String sale_price) {
		this.sale_price = sale_price;
	}

	public String getPurchase_discount() {
		return purchase_discount;
	}

	public void setPurchase_discount(String purchase_discount) {
		this.purchase_discount = purchase_discount;
	}

	public String getSale_discount() {
		return sale_discount;
	}

	public void setSale_discount(String sale_discount) {
		this.sale_discount = sale_discount;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public String getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}

	public ArrayList<Product> getBrandnameList() {
		return brandnameList;
	}

	public void setBrandnameList(ArrayList<Product> brandnameList) {
		this.brandnameList = brandnameList;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public ArrayList<Product> getSubcategoryList() {
		return subcategoryList;
	}

	public void setSubcategoryList(ArrayList<Product> subcategoryList) {
		this.subcategoryList = subcategoryList;
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

	public ArrayList<Product> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Product> categoryList) {
		this.categoryList = categoryList;
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

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubcategory_id() {
		return subcategory_id;
	}

	public void setSubcategory_id(String subcategory_id) {
		this.subcategory_id = subcategory_id;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public ArrayList<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorList(ArrayList<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	public ArrayList<Product> getLinenList() {
		return linenList;
	}

	public void setLinenList(ArrayList<Product> linenList) {
		this.linenList = linenList;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMachineid() {
		return machineid;
	}

	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}

	public ArrayList<Product> getServiceproviderList() {
		return serviceproviderList;
	}

	public void setServiceproviderList(ArrayList<Product> serviceproviderList) {
		this.serviceproviderList = serviceproviderList;
	}

	public ArrayList<Product> getMachines() {
		return machines;
	}

	public void setMachines(ArrayList<Product> machines) {
		this.machines = machines;
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

	public String getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getRemainder_on() {
		return remainder_on;
	}

	public void setRemainder_on(String remainder_on) {
		this.remainder_on = remainder_on;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

}
