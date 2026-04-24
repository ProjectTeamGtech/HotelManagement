package com.apm.Inventory.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Master.eu.bi.CityMasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCCityMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCInventoryVendorDAO implements InventoryVendorDAO {

	Connection connection;
	
	public JDBCInventoryVendorDAO(Connection connection) {
       
	     this.connection=connection;
	}

	public ArrayList<Vendor> getAllVendorList(Pagination pagination,String location, String searchtext) {

		ArrayList<Vendor> list=new ArrayList<Vendor>();
		
		try {
   		
	        String sql="";		
	        boolean flag= false;
	        StringBuffer buffer = new StringBuffer();
//			if(!location.equals("0")){
//				buffer.append("select id, name, address, email, brandname, mobile_pri, mobile_sec, phone1, phone2, min_delivery_time,tinno,state,city,postcode, creditdays from inventory_vendor where location='"+location+"' ");
//				 flag=true;
//			} else {
//				 buffer.append("select id, name, address, email, brandname, mobile_pri, mobile_sec, phone1, phone2, min_delivery_time,tinno,state,city,postcode, creditdays from inventory_vendor ");
//			}
//			
	        buffer.append("select id, name, address, email, brandname, mobile_pri, mobile_sec, phone1, phone2, min_delivery_time,tinno,state,city,postcode, creditdays,suppliercode from inventory_vendor ");
			buffer.append("where name!='' ");
			if(!location.equals("0")){
				buffer.append("and location='"+location+"'  ");
			}
			if(searchtext!=null){
					buffer.append("and name like ('"+searchtext+"%') or suppliercode like ('"+searchtext+"%') ");
			}
			buffer.append("order by id desc ");
			
			sql=pagination.getSQLQuery(buffer.toString());
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				Vendor vendor=new Vendor();
				vendor.setId(rs.getInt(1));
				vendor.setName(rs.getString(2));
				vendor.setAddress(rs.getString(3));
				vendor.setEmail(rs.getString(4));
				vendor.setBrand_name(rs.getString(5));
				vendor.setMobile_pri(rs.getString(6));
				vendor.setMobile_sec(rs.getString(7));
				vendor.setPhone1(rs.getString(8));
				vendor.setPhone2(rs.getString(9));
				vendor.setMin_delivery_time(rs.getString(10));
				vendor.setTinno(rs.getString(11));
				//  03 oct 2017 city and state name instead of id
				vendor.setState(rs.getString(12));
				vendor.setCity(getCityName(rs.getString(13)));
				vendor.setPostcode(rs.getString(14));
				vendor.setCreditdays(rs.getString(15));
				vendor.setSuppliercode(rs.getString(16));
				vendor.setStateName(getStateName(rs.getString(12)));
				list.add(vendor);
			}
			
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}


	public ArrayList<Vendor> getAllVendorList(String location) {

		ArrayList<Vendor> list=new ArrayList<Vendor>();
		
		try {
		
			String sql="";
			if(!location.equals("0") ){
				sql="select id, name, address, email, brandname, mobile_pri, mobile_sec, phone1, phone2, min_delivery_time,tinno,state,city,postcode,suppliercode from inventory_vendor where location='"+location+"' ";
			} else {
				sql="select id, name, address, email, brandname, mobile_pri, mobile_sec, phone1, phone2, min_delivery_time,tinno,state,city,postcode,suppliercode from inventory_vendor";
			}
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				Vendor vendor=new Vendor();
				vendor.setId(rs.getInt(1));
				vendor.setName(rs.getString(2));
				vendor.setAddress(rs.getString(3));
				vendor.setEmail(rs.getString(4));
				vendor.setBrand_name(rs.getString(5));
				vendor.setMobile_pri(rs.getString(6));
				vendor.setMobile_sec(rs.getString(7));
				vendor.setPhone1(rs.getString(8));
				vendor.setPhone2(rs.getString(9));
				vendor.setMin_delivery_time(rs.getString(10));
				vendor.setTinno(rs.getString(11));
				vendor.setState(getStateName(rs.getString(12)));
				vendor.setCity(getCityName(rs.getString(13)));
				vendor.setPostcode(rs.getString(14));
				vendor.setGrnpovendorid(""+rs.getInt(1));
				vendor.setSuppliercode(rs.getString(15));
				String data=vendor.getName();
				if(!DateTimeUtils.isNull(vendor.getSuppliercode()).equals("")){
					data = data +" - "+vendor.getSuppliercode();
				}
				vendor.setData(data);
				list.add(vendor);
				
			}
			
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return list;
	}

	
	
	public int getVendorlistCount(String location, String searchtext) {

		int result=0;
		try {
			 String sql="";		
		        boolean flag= false;
		        StringBuffer buffer = new StringBuffer();
				if(!location.equals("0")){
					buffer.append("select count(*) from inventory_vendor where location='"+location+"' ");
					 flag=true;
				} else {
					 buffer.append("select count(*) from inventory_vendor ");
				}
				
				if(searchtext!=null){
					if(flag){
						buffer.append("and name like ('"+searchtext+"%')");
					}else{
						buffer.append("where name like ('"+searchtext+"%')");
					}
				}
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				result=rs.getInt(1);				
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		
		return result;
	}

	public int addVendor(Vendor vendor) {
 
        int result=0;
        
        
        try {
			
        	String sql="insert into inventory_vendor (name, address, email, brandname, mobile_pri, mobile_sec, phone1, phone2, min_delivery_time,tinno,state,city,postcode,products,location,creditdays,bankname,ifsc,accountno,branch,drug_license,isfrombloodbank,suppliercode) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        	PreparedStatement ps=connection.prepareStatement(sql);
        	ps.setString(1, vendor.getName());
        	ps.setString(2, vendor.getAddress());
        	ps.setString(3, vendor.getEmail());
        	ps.setString(4, vendor.getBrand_name());
        	ps.setString(5, vendor.getMobile_pri());
        	ps.setString(6, vendor.getMobile_sec());
        	ps.setString(7, vendor.getPhone1());
        	ps.setString(8, vendor.getPhone2());
        	ps.setString(9, vendor.getMin_delivery_time());
        	ps.setString(10, vendor.getTinno());
        	ps.setString(11, vendor.getState());
        	ps.setString(12, vendor.getCity());
        	ps.setString(13, vendor.getPostcode());
        	ps.setString(14, vendor.getProductlist());
        	ps.setString(15, vendor.getLocation());
        	ps.setString(16, vendor.getCreditdays());
        	//lokesh
        	ps.setString(17, vendor.getBankname());
        	ps.setString(18, vendor.getIfsc());
        	ps.setString(19, vendor.getAccountno());
        	ps.setString(20, vendor.getBranch());
        	//  14/11/2018 add drug_license
        	ps.setString(21, vendor.getDrug());
        	ps.setString(22, ""+vendor.getIsfrombloodbank());
        	ps.setString(23, vendor.getSuppliercode());
        	result=ps.executeUpdate();
        	if(result>0){
				
				 ResultSet rs=ps.getGeneratedKeys();
				 while(rs.next()){
					 
					  result=rs.getInt(1);
				 }
			}
		} catch (Exception e) {

		    e.printStackTrace();
		}
		
		
		return result;
	}

	public Vendor getVendor(String id) {

		Vendor vendor=new Vendor();
		
		try {
			
			String sql="select name, address, email, brandname, mobile_pri, mobile_sec, phone1, phone2, "
					+ "min_delivery_time,tinno,state,city,postcode,products, creditdays,bankname,ifsc,"
					+ "accountno,branch,drug_license,GST_no,suppliercode from inventory_vendor where id='"+id+"'";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
            	
            	vendor.setName(rs.getString(1));
				vendor.setAddress(rs.getString(2));
				vendor.setEmail(rs.getString(3));
				vendor.setBrand_name(rs.getString(4));
				vendor.setMobile_pri(rs.getString(5));
				vendor.setMobile_sec(rs.getString(6));
				vendor.setPhone1(rs.getString(7));
				vendor.setPhone2(rs.getString(8));
				vendor.setMin_delivery_time(rs.getString(9));
				vendor.setTinno(rs.getString(10));
				vendor.setState(rs.getString(11));
				vendor.setCity(rs.getString(12));
				vendor.setPostcode(rs.getString(13));
				vendor.setProductlist(rs.getString(14));
				vendor.setId(Integer.parseInt(id));
				vendor.setStateName(getStateName(vendor.getState()));
				vendor.setCityName(getCityName(vendor.getCity()));
				vendor.setCreditdays(rs.getString(15));
				//lokesh
				if(rs.getString(16)!=null){
					vendor.setBankname(rs.getString(16));
				}else{
					vendor.setBankname("");	
				}
				if(rs.getString(17)!=null){
					vendor.setIfsc(rs.getString(17));
				}else{
					vendor.setIfsc("");	
				}
            	if(rs.getString(18)!=null){
            		vendor.setAccountno(rs.getString(18));
            	}else{
            		vendor.setAccountno("");	
				}
            	if(rs.getString(19)!=null){
            		vendor.setBranch(rs.getString(19));	
            	}else{
            		vendor.setBranch("");	
				}
            	//  15/11/2018
            	String drug_lice=rs.getString(20);
            	if(rs.getString(20)==null){
            		drug_lice="";
            	}else{
            		vendor.setDrug(drug_lice);
            	}
            	vendor.setVendorGstNo(DateTimeUtils.isNull(rs.getString(11)));
            	vendor.setSuppliercode(DateTimeUtils.isNull(rs.getString(22)));
            	
            	/*bankname,ifsc,accountno,branch*/
            }
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return vendor;
	}

	public int updateVendor(Vendor vendor) {

		int result=0;
		
		try {
			
			String sql="update inventory_vendor set name=?, address=?, email=?, brandname=?, mobile_pri=?, mobile_sec=?, phone1=?, phone2=?, min_delivery_time=?,tinno=?,state=?,city=?,postcode=?,products=?, creditdays=?,bankname=?,ifsc=?,accountno=?,branch=?,drug_license=?,suppliercode=?  where id="+vendor.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, vendor.getName());
        	ps.setString(2, vendor.getAddress());
        	ps.setString(3, vendor.getEmail());
        	ps.setString(4, vendor.getBrand_name());
        	ps.setString(5, vendor.getMobile_pri());
        	ps.setString(6, vendor.getMobile_sec());
        	ps.setString(7, vendor.getPhone1());
        	ps.setString(8, vendor.getPhone2());
        	ps.setString(9, vendor.getMin_delivery_time());
        	ps.setString(10, vendor.getTinno());
        	ps.setString(11, vendor.getState());
        	ps.setString(12, vendor.getCity());
        	ps.setString(13, vendor.getPostcode());
        	ps.setString(14, vendor.getProductlist());
        	ps.setString(15, vendor.getCreditdays());
        	//lokesh
        	ps.setString(16, vendor.getBankname());
        	ps.setString(17, vendor.getIfsc());
        	ps.setString(18, vendor.getAccountno());
        	ps.setString(19, vendor.getBranch());
        	ps.setString(20, vendor.getDrug());
        	ps.setString(21, vendor.getSuppliercode());
        	result=ps.executeUpdate();
        	
		} catch (Exception e) {
          e.printStackTrace();
          
		}
		
		return result;
	}
	
	public int updateVendorAjax(Vendor vendor) {

		int result=0;
		
		try {
			
			String sql="update inventory_vendor set name=?, address=?, email=?, mobile_pri=?, phone1=?, min_delivery_time=? where id="+vendor.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, vendor.getName());
        	ps.setString(2, vendor.getAddress());
        	ps.setString(3, vendor.getEmail());
        	ps.setString(4, vendor.getMobile_pri());
        	ps.setString(5, vendor.getPhone1());
        	ps.setString(6, vendor.getMin_delivery_time());
        	
        	result=ps.executeUpdate();
			
		} catch (Exception e) {
          e.printStackTrace();
          
		}
		
		return result;
	}
	

	public int deleteVendor(String id) {

	    int result=0;
		
	    try {
			String sql="delete from inventory_vendor where id="+id+"";
		    PreparedStatement ps=connection.prepareStatement(sql);
	    	
		    result=ps.executeUpdate();
		    
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		return result;
	}

	public ArrayList<Product> getBrandList(String id) {

		ArrayList<Product> list=new ArrayList<Product>();
		
		try {
		
			String sql="select name,brandname from inventory_vendor where id="+id+"";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
				
			
			while(rs.next()){
			
				
				String name=rs.getString(1);
				String brands=rs.getString(2);
				
				for(String brandid:brands.split(",")){ 

				   Product product=new Product();
					
				   if(!brandid.equals("0")) {
				      	
			            String brand_name=getBrandNameFromId(brandid);
			            product.setBrand(brand_name);
			            product.setId(Integer.parseInt(brandid));
			            product.setName(name);
			            list.add(product);
				   }
				}	
			}
			
		
		} catch (Exception e) {

		   e.printStackTrace();
		}		
		return list;
	}
	
	
	public String getBrandNameFromId(String bid) {
		
		String name="";
		try {
			
			String sql="select name from inventory_brandname where id="+bid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				 name=rs.getString(1);
			} 
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return name;
	}

	
	public int addUpdateBrand(String brand) {

		int result=0;
		boolean flag=false;
		try {
			
			String sql="select id from inventory_brandname where name='"+brand+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				
				result=rs.getInt(1);
				flag=true;
			} else {
				flag=false;
			}
            				
			if(flag){
				
				 return result;
				// update
			} else {
				//insert
				
				String sql1="insert into inventory_brandname (name) values ('"+brand+"')";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				
				int res=ps1.executeUpdate();
				
				ResultSet set=ps1.getGeneratedKeys();
				
				while(set.next()) {
					
					res=set.getInt(1);
				}
				return res;
				
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return result;
	}

	
	public int updateVendorbrand(int result, String vendorid) {

		int res=0;
		String data="";
		String out="";
		
		try {
			
			String sql="select brandname from inventory_vendor where id="+vendorid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
		    while(rs.next()) {
		    	
		    	data=rs.getString(1);
		    }
		    
		    if(data==null){
		    	
		    	out=out+"0,"+result;
		    	
		    } else if(data=="") {
		    	out=out+"0,"+result;
		    }else if(data.length()<3) {
		    	out=out+"0,"+result;
		    } else {
		    	
		    	out=data+","+result;
		    }
		    
			String sql2="update inventory_vendor set brandname='"+out+"' where id="+vendorid+"";
			PreparedStatement statement=connection.prepareStatement(sql2);
			
			res=statement.executeUpdate();
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return res;
	}

	
	public int removeBrandFormVendor(String vendorid,String brandid) {

		int result=0;
		
		try {
			
			String sql="select brandname from inventory_vendor where id="+vendorid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			String data="";
			while(rs.next()){
				
				data=rs.getString(1);				
			}
			
            		
			int index=data.indexOf(brandid);
//			System.out.println(index);
			String newtemp=data.replace(brandid,"0");
			
			String sql3="update inventory_vendor set brandname='"+newtemp+"' where id="+vendorid+"";
			PreparedStatement psPreparedStatement=connection.prepareStatement(sql3);
			
			result=psPreparedStatement.executeUpdate();
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return result;
	}

	
	public int updateBrand(String brandid, String brand) {

		int result=0;
		
		try {
		  String sql="update inventory_brandname set name=? where id="+brandid+"";	
		  PreparedStatement ps=connection.prepareStatement(sql);
		  
		  ps.setString(1, brand);
		
		  result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}	
		
		return result;
	}

	public ArrayList<Vendor> getAllVendorListFromBrand(String brandid) {

		ArrayList<Vendor> list=new ArrayList<Vendor>();
		String result="0";
		try {
			String sql="SELECT vendorid from inventory_brandname where id="+brandid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				result=rs.getString(1);
			}
				
			for(String str:result.split(",")){
				 
				 if(str.equals("0")) {
					  continue;
				 }
				 Vendor vendor=getVendor(str);
				 list.add(vendor); 	
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Master> getAllCityList() {

		ArrayList<Master> list=new ArrayList<Master>();
		try {
			
			String sql="SELECT id,stateid,city from apm_city";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				  Master master=new Master();
				  master.setId(rs.getInt(1));
				  master.setState(getStateNamefromId(rs.getString(2)));
				  master.setCity(rs.getString(3));
				  list.add(master);
			}
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Master> getAllCityListByState(String stateid) {

		ArrayList<Master> list=new ArrayList<Master>();
		try {
			String statename = getStateIDfromName(stateid);
			String sql="SELECT id,city from apm_city where stateid='"+statename+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				Master master=new Master();
				master.setId(rs.getInt(1));
				master.setCity(rs.getString(2));
				master.setStateid(stateid);
				list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}



private String getStateIDfromName(String statename) {
		
		String result="";
		try {
			String sql="SELECT id from apm_state where name='"+statename+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			 
			while(rs.next()) {
			   result=rs.getString(1);	
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}



	public ArrayList<Master> getAllStateList() {

		ArrayList<Master> list=new ArrayList<Master>();
		try {
			
			String sql="select id,name from apm_state";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				Master master=new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2)); 
				list.add(master);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	private String getStateNamefromId(String stateid) {
		
		String result="";
		try {
			String sql="SELECT name from apm_state where id="+stateid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			 
			while(rs.next()) {
			   result=rs.getString(1);	
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public String getCityName(String id) {

		String result="";
		try {
			String sql="select city from apm_city where id='"+id+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				  result=rs.getString(1); 
			}
			  
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public String getStateName(String id) {
		String result="";
		try {
			String sql="select name from apm_state where id='"+id+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				  result=rs.getString(1); 
			}
			  
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Product> getVendorProducts(String vendorid) {

		ArrayList<Product> list=new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
		String result="0";
		try {
			String sql="SELECT products from inventory_vendor where id="+vendorid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				    result=rs.getString(1);
				
			}
			
			for(String str: result.split(",")){
				  
				    int id=Integer.parseInt(str);
				    if(id>0){
				    	Product product=new Product();
				    	Product master=inventoryProductDAO.getProductCatalogueDetails(str);  
				    	product.setId(master.getId());
				    	product.setProduct_name(master.getProduct_name()+" ("+master.getGenericname()+") ");
				    	product.setGenericname(master.getGenericname());
				    	list.add(product);
				    	
				    }
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public ArrayList<Master> getAllStateListByCity(String city) {

		ArrayList<Master> list=new ArrayList<Master>();
		try {
			String sql="SELECT id,stateid from apm_city where city='"+city+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				Master master=new Master();
				master.setId(rs.getInt(1));
				master.setStateid(rs.getString(2));
				master.setCity(city);
				master.setState(getStateNamefromId(rs.getString(2)));
				list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	public ArrayList<Master> getAllCityListByStatefrsup(String stateid) {

		ArrayList<Master> list=new ArrayList<Master>();
		try {
			//String statename = getStateIDfromName(stateid);
			String sql="SELECT id,city from apm_city where stateid='"+stateid+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				Master master=new Master();
				master.setId(rs.getInt(1));
				master.setCity(rs.getString(2));
				master.setStateid(stateid);
				list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public int updateProductByVendor(String vendorid, String productid) {

		int result=0;
		try {
			
			Vendor vendor=getVendor(vendorid);
			String temp=vendor.getProductlist()+","+productid;
			String sql="update inventory_vendor set products='"+temp+"' where id="+vendorid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int checkgstno(String gstno) {
	int result=0;
	try {
		String sql="select * from inventory_vendor where tinno='"+gstno+"' ";
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

	public int checkgstnoforedit(String gstno, String vendorid) {
		int result=0;
		try {
			String sql="select id from inventory_vendor where id!="+vendorid+" and tinno='"+gstno+"' ";
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

	public ArrayList<Master> getAllStateListByCity1(String city) {
		ArrayList<Master> list=new ArrayList<Master>();
		try {
			StringBuffer buffer=new StringBuffer();
			if(city.equals("0")){
				buffer.append("SELECT id,stateid from apm_city group by stateid ");
			}else{
			buffer.append("SELECT id,stateid from apm_city ");
			
			buffer.append("where city='"+city+"' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				Master master=new Master();
				master.setId(rs.getInt(1));
				master.setStateid(rs.getString(2));
				master.setCity(city);
				master.setState(getStateNamefromId(rs.getString(2)));
				list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	
	}

	@Override
	public ArrayList<Client> getAllChargeListByPlan(String planid) {
		ArrayList<Client> list=new ArrayList<Client>();
		try {
			String sql="SELECT charges from apm_appointment_type where planid='"+planid+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				Client client=new Client();
				client.setCharge(rs.getDouble(1));
				list.add(client);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int updateSupplierCode(int res, String vendocode) {
		int result=0;
		try {
			
			
			String sql="update inventory_vendor set suppliercode='"+vendocode+"' where id="+res+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
		
	}

	
	
	
	
	
	
}
