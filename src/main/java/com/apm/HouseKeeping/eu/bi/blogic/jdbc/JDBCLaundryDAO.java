package com.apm.HouseKeeping.eu.bi.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.HouseKeeping.eu.bi.LaundryDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;

public class JDBCLaundryDAO implements LaundryDAO {

	   Connection connection;
	   
	   public JDBCLaundryDAO(Connection connection) {
		   
		   this.connection=connection;
	   }


	   public ArrayList<Product> getAllLundryList(String product_id,String staff_id,String date,Pagination pagination) {
		
		   ArrayList<Product> list=new ArrayList<Product>();
		   try {

			     InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			     UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			     InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			     
			     
			     String sql="";
					
					if(product_id!=null && staff_id==null && date==null){
						sql="SELECT id, product_id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags from his_laundry where product_id="+product_id+"";
					} else if(product_id!=null && staff_id!=null && date==null){
						sql="SELECT id, product_id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags from his_laundry where product_id="+product_id+" and staff_id="+staff_id+"";
					}
					else if(product_id!=null && staff_id!=null && date!=null){
						sql="SELECT id, product_id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags from his_laundry where product_id="+product_id+" and staff_id="+staff_id+" and allot_date='"+date+"'";
					}
					else if(product_id==null && staff_id==null && date!=null){
						sql="SELECT id, product_id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags from his_laundry where allot_date='"+date+"'";
					}
					else if(product_id==null && staff_id!=null && date!=null){
						sql="SELECT id, product_id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags from his_laundry where allot_date='"+date+"' and staff_id="+staff_id+"";
					}
					else if(product_id==null && staff_id!=null && date==null){
						sql="SELECT id, product_id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags from his_laundry where staff_id="+staff_id+"";
					}
					else{
						
						sql="SELECT id, product_id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags from his_laundry";
					} 
			     
				 sql=pagination.getSQLQuery(sql);	
			     PreparedStatement ps=connection.prepareStatement(sql);
			     ResultSet rs=ps.executeQuery();
			     
			     while(rs.next()){
			    	  
			            Product product=new Product();
			            product.setId(rs.getInt(1));
			            product.setProduct_id(rs.getString(2));
			            product.setSubcategory_id(rs.getString(3));
			            product.setCategory_id(rs.getString(4));
			            product.setStaffid(rs.getString(5));
			            product.setInhouse(rs.getString(6));
			            product.setVendor_id(rs.getString(7));
			            product.setAllot_date(rs.getString(8));
			            product.setCollect_by(rs.getString(9));
			            product.setCollected_date(rs.getString(10));
			            product.setQuantity(rs.getString(11));
			            product.setProcessing(rs.getString(12));
			            product.setNotes(rs.getString(13));
			            product.setLastmodified(rs.getString(14));
			            product.setRemains(rs.getString(15));
			            product.setStatus(rs.getString(16));
			            
			            Product product2=inventoryProductDAO.getProduct(rs.getString(2));
			            product.setProduct_name(product2.getProduct_name());
			            String name=userProfileDAO.getName(product.getStaffid());
			            product.setStaffname(name);
			            String collect_by=userProfileDAO.getName(product.getCollect_by());
			            product.setCollect_by(collect_by);
			            
			            Vendor vendor=inventoryVendorDAO.getVendor(product.getVendor_id());
			            product.setVendor(vendor.getName());
			            product.setEmail(vendor.getEmail());
			            product.setContact(vendor.getMobile_pri());
			            product.setStock(product2.getStock());
			            list.add(product);
			            
			     }
			     
			   
		   } catch (Exception e) {

		       e.printStackTrace();
		   }
		   
		   return list;
	}


	public int addLinen(Product product) {

		int result=0;
		try {
			
			String sql="insert into his_laundry (product_id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, product.getProduct_id());
			ps.setString(2, "1");
			ps.setString(3, "1");
			ps.setString(4, product.getStaffid());
			ps.setString(5, product.getInhouse());
			ps.setString(6, product.getVendor_id());
			ps.setString(7, product.getAllot_date());
			ps.setString(8, product.getCollect_by());
			ps.setString(9, product.getCollected_date());
			ps.setString(10, product.getQuantity());
			ps.setString(11, product.getProcessing());
			ps.setString(12, product.getNotes());
			ps.setString(13, product.getLastmodified());
			ps.setString(14, product.getRemains());
			ps.setString(15, "0");
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}


	public Product getLinen(String id) {

		Product product=new Product();
		try {

			String sql="SELECT product_id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags from his_laundry where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				   product.setProduct_id(rs.getString(1));
				   product.setSubcategory_id(rs.getString(2));
				   product.setCategory_id(rs.getString(3));
				   product.setStaffid(rs.getString(4));
				   product.setInhouse(rs.getString(5));
				   product.setVendor_id(rs.getString(6));
				   product.setAllot_date(rs.getString(7));
				   product.setCollect_by(rs.getString(8));
				   product.setCollected_date(rs.getString(9));
				   product.setQuantity(rs.getString(10));
				   product.setProcessing(rs.getString(11));
				   product.setNotes(rs.getString(12));
				   product.setLastmodified(rs.getString(13));
				   product.setRemains(rs.getString(14));
				   product.setStatus(rs.getString(15));
				   
				   
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return product;
	}


	public int updateLinen(Product product) {

		int result=0;
		
		try {
			String sql="update his_laundry set collect_by=?, collected_date=?, notes=?, flags=?,processing=?,remains=? where id="+product.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, product.getCollect_by());
			ps.setString(2, product.getCollected_date());
			ps.setString(3, product.getNotes());
			ps.setString(4, product.getStatus());
			ps.setString(5, product.getProcessing());
			ps.setString(6, product.getRemains());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}


	public int getReaminsQty(String pid) {
   
        int result=-1;
		try {
			
			  String sql="select remains from his_laundry where product_id="+pid+"";
			  PreparedStatement ps=connection.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			
			  while(rs.next()){
				   
				  result=rs.getInt(1);
			  }
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}


	public int getAllLaundryCount(String product_id, String staff_id,
			String date) {
		
		String sql="";
		
		if(product_id!=null && staff_id==null && date==null){
			sql="SELECT count(*) from his_laundry where product_id="+product_id+"";
		} else if(product_id!=null && staff_id!=null && date==null){
			sql="SELECT count(*) from his_laundry where product_id="+product_id+" and staff_id="+staff_id+"";
		}
		else if(product_id!=null && staff_id!=null && date!=null){
			sql="SELECT count(*) from his_laundry where product_id="+product_id+" and staff_id="+staff_id+" and allot_date='"+date+"'";
		}
		else if(product_id==null && staff_id==null && date!=null){
			sql="SELECT count(*) from his_laundry where allot_date='"+date+"'";
		}
		else if(product_id==null && staff_id!=null && date!=null){
			sql="SELECT count(*) from his_laundry where allot_date='"+date+"' and staff_id="+staff_id+"";
		}
		else if(product_id==null && staff_id!=null && date==null){
			sql="SELECT count(*) from his_laundry where staff_id="+staff_id+"";
		}
		else{
			
			sql="SELECT count(*) from his_laundry";
			
		} 

		int result=0;
		try {
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 result=rs.getInt(1);
			}
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Product> getAllMachines(String fromdate,String todate,Pagination pagination) {

		ArrayList<Product> list=new ArrayList<Product>();
        InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
		String sql="";
		
		try {
			if(fromdate!=null && todate!=null){
				sql="SELECT id, equipment_id, brand, vendor_id, frequency, due_date, reminder_on,machine_name from apm_machine where due_date between '"+fromdate+"' and '"+todate+"'";
			}
			else {
			   sql="SELECT id, equipment_id, brand, vendor_id, frequency, due_date, reminder_on,machine_name from apm_machine";
		    }
			
			 sql=pagination.getSQLQuery(sql);
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				 
				  Product product=new Product();
				  product.setId(rs.getInt(1));
				  product.setEquipment_id(rs.getString(2));
				  product.setBrand(rs.getString(3));
				  product.setVendor_id(rs.getString(4));			  
				  product.setFrequency(rs.getString(5));
				  product.setDue_date(rs.getString(6));
				  product.setRemainder_on(rs.getString(7));
				  product.setMachine_name(rs.getString(8));
				  
				  product.setEquipment(getEquipmentFromId(product.getEquipment_id()));
				  
				  Vendor vendor=inventoryVendorDAO.getVendor(product.getVendor_id());
				  product.setName(vendor.getName());
				  product.setContact(vendor.getPhone1());
				  product.setEmail(vendor.getEmail());
				  
				  list.add(product);
			 }
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}


	public int getMachineCount(String fromdate, String todate) {

     int result=0;		 
     String sql="";
		
		try {
			if(fromdate!=null && todate!=null){
				sql="SELECT count(*) from apm_machine where due_date between '"+fromdate+"' and '"+todate+"'";
			}
			else {
			   sql="SELECT count(*) from apm_machine";
		    }
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 result=rs.getInt(1);
			}
	  }
	  catch (Exception e) {

	      e.printStackTrace();
	  }

    return result;	   
  }
	
	
	
	public ArrayList<Product> getAllMachines(){
		
		ArrayList<Product> list=new ArrayList<Product>();
		   InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
		try {
			String sql="SELECT id, equipment_id, brand, vendor_id, frequency, due_date, reminder_on,machine_name from apm_machine";
			
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				 
				  Product product=new Product();
				  product.setId(rs.getInt(1));
				  product.setEquipment_id(rs.getString(2));
				  product.setBrand(rs.getString(3));
				  product.setVendor_id(rs.getString(4));			  
				  product.setFrequency(rs.getString(5));
				  product.setDue_date(rs.getString(6));
				  product.setRemainder_on(rs.getString(7));
				  product.setMachine_name(rs.getString(8));
				  
				  product.setEquipment(getEquipmentFromId(product.getEquipment_id()));
				  
				  Vendor vendor=inventoryVendorDAO.getVendor(product.getVendor_id());
				  product.setName(vendor.getName());
				  product.setContact(vendor.getPhone1());
				  product.setEmail(vendor.getEmail());
				  
				  list.add(product);
			 }
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
      return list;		
		
	}


	public int addMachine(Product product) {

		int result=0;
		
		try {
			String sql="insert into apm_machine (equipment_id, brand, vendor_id, frequency, due_date,reminder_on, machine_name) values (?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, product.getEquipment_id());
			ps.setString(2, product.getBrand());
			ps.setString(3, product.getVendor_id());
			ps.setString(4, product.getFrequency());
			ps.setString(5, product.getDue_date());
			ps.setString(6, product.getRemainder_on());
			ps.setString(7, product.getMachine_name());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}


	public Product getMachine(String id) {

		Product product=new Product(); 
		
		try {
             String sql="SELECT id, equipment_id, brand, vendor_id, frequency, due_date, reminder_on, machine_name from apm_machine where id="+id+"";
             PreparedStatement ps=connection.prepareStatement(sql);
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
            	 
            	    product.setId(rs.getInt(1));
            	    product.setEquipment_id(rs.getString(2));
            	    product.setBrand(rs.getString(3));
            	    product.setVendor_id(rs.getString(4));
            	    product.setFrequency(rs.getString(5));
            	    product.setDue_date(rs.getString(6));
            	    product.setRemainder_on(rs.getString(7));
            	    product.setMachine_name(rs.getString(8));
            	  
             }
			 
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return product;
	}


	public int updateMachine(Product product) {

		int result=0;
		try {
			String sql="UPDATE apm_machine set equipment_id=?, brand=?, vendor_id=?, frequency=?, due_date=?, reminder_on=?, machine_name=? where id="+product.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, product.getEquipment_id());
			ps.setString(2, product.getBrand());
			ps.setString(3, product.getVendor_id());
			ps.setString(4, product.getFrequency());
			ps.setString(5, product.getDue_date());
			ps.setString(6, product.getRemainder_on());
			ps.setString(7, product.getMachine_name());
             result=ps.executeUpdate();              			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}


	public Product getLinenProduct(String product_id) {

		Product product=null;
		try {

			String sql="SELECT id, prod_sub_category, prod_category, staff_id, inhouse, vendor_id, allot_date, collect_by, collected_date, quntity, processing, notes, lastmodified,remains,flags from his_laundry where product_id="+product_id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				   product=new Product();
				   product.setId(rs.getInt(1));
				   product.setSubcategory_id(rs.getString(2));
				   product.setCategory_id(rs.getString(3));
				   product.setStaffid(rs.getString(4));
				   product.setInhouse(rs.getString(5));
				   product.setVendor_id(rs.getString(6));
				   product.setAllot_date(rs.getString(7));
				   product.setCollect_by(rs.getString(8));
				   product.setCollected_date(rs.getString(9));
				   product.setQuantity(rs.getString(10));
				   product.setProcessing(rs.getString(11));
				   product.setNotes(rs.getString(12));
				   product.setLastmodified(rs.getString(13));
				   product.setRemains(rs.getString(14));
				   product.setStatus(rs.getString(15));
				   product.setProduct_id(product_id);
				   
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return product;
	}


	public int updateqtyLinen(Product product) {
		
		int result=0;
		
		try {
			String sql="update his_laundry set flags=?,processing=?,remains=?,quantity=? where id="+product.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, product.getStatus());
			ps.setString(2, product.getProcessing());
			ps.setString(3, product.getRemains());
			ps.setString(4, product.getQuantity());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
		
	}


	public ArrayList<Product> getEquipmentTypeList() {
		
		ArrayList<Product> list=new ArrayList<Product>();
		try {
			
			String sql="SELECT id,name,note from apm_equipment_type";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				Product product=new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setNotes(rs.getString(3));
				list.add(product);
				
			}
			
		} catch (Exception e) {

		    e.printStackTrace();
		}
		
		return list;
	}


	public String getEquipmentFromId(String eqid) {

		String str="";
		try {
			
			String sql="select name from apm_equipment_type where id="+eqid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
					str=rs.getString(1);
				
			}
			 
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return str;
	}


	public ArrayList<Product> getServiceHistory(String machineid) {
 
		ArrayList<Product> list=new ArrayList<Product>();
		try {
			
			String sql="SELECT id, machine_id, service_date, notes, status from apm_machine_service where machine_id='"+machineid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
		 
				   Product product=new Product();		
				   product.setId(rs.getInt(1));
				   product.setMachine_id(rs.getString(2));
				   product.setService_date(rs.getString(3));
				   product.setNotes(rs.getString(4));
				   product.setStatus(rs.getString(5));
				   list.add(product);
				
			}
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}


	public int saveMachineServiceHistory(Product product) {

		
		int result=0;
		try {
			String sql="insert into apm_machine_service (machine_id, service_date, notes, status) values (?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
		    ps.setString(1, product.getMachine_id());
		    ps.setString(2, product.getService_date());
		    ps.setString(3, product.getNotes());
		    ps.setString(4, product.getStatus());
	     		
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}	
	
}
