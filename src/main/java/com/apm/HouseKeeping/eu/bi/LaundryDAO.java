package com.apm.HouseKeeping.eu.bi;

import java.util.ArrayList;

import com.apm.Inventory.eu.entity.Product;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.common.utils.Pagination;

public interface LaundryDAO {

	ArrayList<Product> getAllLundryList(String product_id,String staff_id,String date,Pagination pagination);
	int addLinen(Product product);
	Product getLinen(String id);
	int updateLinen(Product product);
	int getReaminsQty(String pid);
	int getAllLaundryCount(String product_id,String staff_id,String date);
	
	ArrayList<Product> getAllMachines(String fromdate,String todate,Pagination pagination);
	int getMachineCount(String fromdate, String todate);
	ArrayList<Product> getAllMachines();
	int addMachine(Product product);
	Product getMachine(String id);
	int updateMachine(Product product);
	
	Product getLinenProduct(String product_id);
	int updateqtyLinen(Product product);
	ArrayList<Product> getEquipmentTypeList();
	String getEquipmentFromId(String eqid);
	
	ArrayList<Product>  getServiceHistory(String machineid);
	int saveMachineServiceHistory(Product product);
	
	
}
