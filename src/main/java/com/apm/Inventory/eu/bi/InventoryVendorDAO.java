package com.apm.Inventory.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.Pagination;

public interface InventoryVendorDAO {

	ArrayList<Vendor> getAllVendorListFromBrand(String brandid);

	ArrayList<Vendor> getAllVendorList(String location);
	ArrayList<Vendor> getAllVendorList(Pagination pagination,String location, String searchtext);
	int getVendorlistCount(String location, String searchtext); 

	int addVendor(Vendor vendor);

	Vendor getVendor(String id);

	int updateVendor(Vendor vendor);

	int deleteVendor(String id);

	ArrayList<Product> getBrandList(String id);

	int addUpdateBrand(String brand);

	int updateVendorbrand(int result, String vendorid);

	int removeBrandFormVendor(String vendorid,String brandid);
	public String getBrandNameFromId(String bid);

	int updateBrand(String brandid, String brand);
	
	ArrayList<Master> getAllStateList();
	ArrayList<Master> getAllCityList();
	ArrayList<Master> getAllCityListByState(String stateid);
	int updateVendorAjax(Vendor vendor);
	String getStateName(String id);
	String getCityName(String id);

	ArrayList<Product> getVendorProducts(String vendorid);

	ArrayList<Master> getAllStateListByCity(String city);
	ArrayList<Master> getAllCityListByStatefrsup(String stateid);

	int updateProductByVendor(String vendorid, String productid);

	int checkgstno(String gstno);

	int checkgstnoforedit(String gstno, String vendorid);

	ArrayList<Master> getAllStateListByCity1(String city);

	ArrayList<Client> getAllChargeListByPlan(String planid);

	int updateSupplierCode(int res, String vendocode);
}
