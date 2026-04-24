package com.apm.HouseKeeping.web.action;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.apm.HouseKeeping.eu.bi.LaundryDAO;
import com.apm.HouseKeeping.eu.bi.blogic.jdbc.JDBCLaundryDAO;
import com.apm.HouseKeeping.web.form.HouseKeepingForm;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class HousekeepingDashBoardAction extends BaseAction implements ModelDriven<HouseKeepingForm>,Preparable{

	HouseKeepingForm houseKeepingForm=new HouseKeepingForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	Pagination pagination=new Pagination(20,1);
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public HouseKeepingForm getModel() {
		return houseKeepingForm;
	}

	@Override
	public String execute() throws Exception {

		return super.execute();
	}
	
	
	public String laundry() throws Exception{
	
		  if(!verifyLogin(request)){
	        	
	        	return "login";
	        }
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			
			String product_id=houseKeepingForm.getProduct_id();
			String staff_id=houseKeepingForm.getStaff_id();
			String date=houseKeepingForm.getDate();
			
			if(product_id!=null){
				
				if(product_id.equals("0")){
					product_id=null;
				}
				else if(product_id.equals("")){
					product_id=null;
				}
				
			}
           if(staff_id!=null){
				
				if(staff_id.equals("0")){
					staff_id=null;
				}
				else if(staff_id.equals("")){
					staff_id=null;
				}
			}
          
           if(date!=null){

        	    if(date.equals("")){
					date=null;
				}
			}
			
			
			int count=laundryDAO.getAllLaundryCount(product_id,staff_id,date);
			
			pagination.setPreperties(count);
			houseKeepingForm.setTotalRecords(count);
			ArrayList<UserProfile> userstaffList=userProfileDAO.getAllUsers("5");
			houseKeepingForm.setUserstaffList(userstaffList);
			//for house keeping 56
			ArrayList<Product> linenList= inventoryProductDAO.geProductList("0","56");
			houseKeepingForm.setLinenList(linenList);
			
			ArrayList<Product> laundryList=laundryDAO.getAllLundryList(product_id,staff_id,date,pagination); 
			houseKeepingForm.setLaundryList(laundryList);
			pagination.setPage_records(laundryList.size());
			houseKeepingForm.setPagerecords(String.valueOf(pagination.getPage_records())); 
		
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
	  	
		return "laundry";
	}
	
	
	public String addlaundry() throws Exception {
		
		
		Connection connection = null;
		try {
            connection=Connection_provider.getconnection();
            InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
            UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection); 
            InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
            
            ArrayList<Product> linenList= inventoryProductDAO.geProductList("0", "56");
            
        	String location=(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
				
			buffer.append("<td>");
			buffer.append("<select class='form-control' id='product_id"+index+"' onchange='getqty(this.value,"+index+")'>");
			
			buffer.append("<option value='0'>Select linen</option>");
			for(Product product:linenList){
				  buffer.append("<option value='"+product.getId()+"'>"+product.getProduct_name()+"</option>");    
			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td id='tdq"+index+"' style='display:inline-flex'><input type='text' name='quantity' id='qty"+index+"' class='form-control' /></td>");
			
			buffer.append("<td>");
			buffer.append("<select class='form-control' onchange='setYesNo(this.value,"+index+")' id='inhouse"+index+"'>");
			buffer.append("<option value='0'>Select</option>");
			buffer.append("<option value='Yes'>Yes</option>");
			buffer.append("<option value='No'>No</option>");
			buffer.append("</select>");
			buffer.append("</td>");
          
			ArrayList<Vendor> vendorList=inventoryVendorDAO.getAllVendorList(location);
			buffer.append("<td>");  
			buffer.append("<select class='form-control' id='vendor"+index+"'>");
			buffer.append("<option value='0'>Select Supplier</option>");
			
			for(Vendor vendor:vendorList){
				 buffer.append("<option value='"+vendor.getId()+"'>"+vendor.getName()+"</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<select class='form-control' id='satff"+index+"'>");
			buffer.append("<option value='0'>Select Staff</option>");
			ArrayList<UserProfile> userstaffList=userProfileDAO.getAllUsers("5");
			for(UserProfile userProfile:userstaffList){
				buffer.append("<option value='"+userProfile.getId()+"'>"+userProfile.getFullname()+"</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			//buffer.append("<td><input type='text' class='form-control' name='allot_date' id='allot_date"+index+"' placeholder='Alloted Date'/></td>");
		/*	buffer.append("<td>");
			buffer.append("<select class='form-control' name='collect_by' id='collect_by"+index+"' disabled='disabled'>");
			buffer.append("<option value='0'>Select Staff</option>");
			for(UserProfile userProfile:userstaffList){
				buffer.append("<option value='"+userProfile.getId()+"'>"+userProfile.getFullname()+"</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");*/
			
			buffer.append("<td><input type='text' class='form-control' name='collected_date' id='collected_date"+index+"' disabled='disabled' placeholder='Collection Date'/></td>");
			
			buffer.append("<td></td>");
			buffer.append("<td><input type='text' class='form-control' name='notes' id='notes"+index+"' placeholder='Enter Notes'/></td>");
			buffer.append("<td>Open</td>");
            buffer.append("<td><a href='#' onclick='saveLinen("+index+")' id='save"+index+"' class='btn btn-primary'>Save</a></td>");			
			buffer.append("</tr>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
	      		connection.close();
		}

		return null;
	
	}
	
	
	public String maintenance() throws Exception{
		
		return "maintenance";
	} 
	
	public String housekeepingserviceprovider() throws Exception{
	
        if(!verifyLogin(request)){
        	
        	return "login";
        }
        
        Connection connection=null;
        try {
              connection=Connection_provider.getconnection();
              LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
        	  pagination=new Pagination(20,1);
        	  String fromdate=houseKeepingForm.getFromdate();
        	  String todate=houseKeepingForm.getTodate();
        	  
        	  
        	  if(fromdate!=null){
        		  
        		  if(fromdate.equals("")){
        			  fromdate=null;
        		  }
        	  }
              if(todate!=null){
        		  
        		  if(todate.equals("")){
        			  todate=null;
        		  }
        	  }
        	  
        	  int count=laundryDAO.getMachineCount(fromdate,todate);
        	  pagination.setPreperties(count);
        	  houseKeepingForm.setTotalRecords(count);
        	  
              ArrayList<Product> serviceproviderList=laundryDAO.getAllMachines(fromdate,todate, pagination);
              houseKeepingForm.setServiceproviderList(serviceproviderList);
        	  pagination.setPage_records(serviceproviderList.size());
        	  houseKeepingForm.setPagerecords(String.valueOf(pagination.getPage_records()));
              
        	  
        	  
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return "housekeepingserviceprovider";
	}
    public String asset() throws Exception{

    	 
    	if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		
		try {
			pagination=new Pagination(20,1);
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			String product_id=houseKeepingForm.getProduct_id();
			if(product_id!=null){
				
				if(product_id.equals("0")){
					  product_id=null; 
				}
			}
			int count=inventoryProductDAO.getAssetsCount(product_id);
            pagination.setPreperties(count);
            houseKeepingForm.setTotalRecords(count);
			ArrayList<Product> productList=inventoryProductDAO.getAllProducts("1",product_id,pagination);
			houseKeepingForm.setProductList(productList);
			pagination.setPage_records(productList.size());
			houseKeepingForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
			houseKeepingForm.setCategory_id("1");
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
    	
		return "asset";
	}
    
    
    
    
    public String savelinen() throws Exception{
    	
    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
    		LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
    		
    		String pid=request.getParameter("prodid");
    		String qty=request.getParameter("qty");
    		String staff=request.getParameter("staff");
    		String inhouse=request.getParameter("inhouse");
    		String vendor=request.getParameter("vendor");
    		String allot_date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		//String collect_by=request.getParameter("collect_by");
    		//String collected_date=request.getParameter("collected_date");
    		String notes=request.getParameter("notes");
    		
    		
    		allot_date=DateTimeUtils.getCommencingDate1(allot_date);
    		
    		
    		Product product=new Product();
    		product.setProduct_id(pid);
    		product.setQuantity(qty);
    		product.setStaffid(staff);
    		product.setInhouse(inhouse);
    		product.setVendor_id(vendor);
    		product.setAllot_date(allot_date);
    		product.setCollect_by("processing");
    		product.setCollected_date("processing");
    		product.setNotes(notes);
    		
    		if(qty=="" || qty==null){
    			qty="0";
    		}
    		
    		Product masterProduct=inventoryProductDAO.getProduct(pid);
    		int nowqty=Integer.parseInt(qty);
    		
    		int totrem=laundryDAO.getReaminsQty(pid);
    		
    		if(totrem==-1){
    			  
    			  totrem=Integer.parseInt(masterProduct.getStock());
    		}
    		
    		
            int rem=totrem-nowqty;
            product.setProcessing(qty);
            product.setStock(masterProduct.getStock());
            product.setRemains(String.valueOf(rem));
    		
    		String lastmodified=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		product.setLastmodified(lastmodified);
    		int result=laundryDAO.addLinen(product);
    		
    		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
 
    		
    		
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
			connection.close();
		}
    	
    	return null;
    }
    
    
    public String getmobvendor() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
    		String id=request.getParameter("vendorid");
            Vendor vendor=inventoryVendorDAO.getVendor(id);   
            response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+vendor.getMobile_pri()+"");
    		
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
			connection.close();
		}
		
    	return null;
    	
    }
    
    public String getqty() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
    		connection=Connection_provider.getconnection();
    		String pid=request.getParameter("id");
    		LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
    		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
            int remains=laundryDAO.getReaminsQty(pid); 
    		
            if(remains==-1){
            	 
            	Product product=inventoryProductDAO.getProduct(pid);
            	remains=Integer.parseInt(product.getStock());
            }
            
            response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+remains+"");
		} catch (Exception e) {

			e.printStackTrace();
		}
    	finally {
			connection.close();
		}
    
    	return null;
    }
	
    
    public String editlinen() throws Exception{
    
 
    		Connection connection = null;
    		try {
    			connection=Connection_provider.getconnection();
    			String id=request.getParameter("id");			
    			LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
                InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
                UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection); 
                InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
                
                
                Product proddb=laundryDAO.getLinen(id);
                Product masterprod=inventoryProductDAO.getProduct(proddb.getProduct_id());
                
                ArrayList<Product> linenList= inventoryProductDAO.geProductList("", "56");
                ArrayList<UserProfile> userstaffList=userProfileDAO.getAllUsers("5");
    			
                String name=userProfileDAO.getName(proddb.getStaffid());
                Vendor vendor=inventoryVendorDAO.getVendor(proddb.getVendor_id());
                
    			StringBuffer buffer = new StringBuffer();
    				
    			buffer.append("<td>"+masterprod.getProduct_name()+"</td>");
    			buffer.append("<td>"+proddb.getRemains()+" / "+proddb.getProcessing()+"</td>");
    			buffer.append("<td>"+proddb.getInhouse()+"</td>");
    			
    			buffer.append("<td>"+vendor.getName()+"/"+vendor.getMobile_pri()+"/"+vendor.getEmail()+"</td>");  
    			buffer.append("<td>");
    			buffer.append("<select class='form-control' name='collect_by' id='collect_by"+id+"'>");
    			buffer.append("<option value='0'>Select Staff</option>");
    			for(UserProfile userProfile:userstaffList){
    				
    					buffer.append("<option value='"+userProfile.getId()+"'>"+userProfile.getFullname()+"</option>");
    			}
    			buffer.append("</select>");
    			buffer.append("</td>");
    			buffer.append("<td><input type='text' class='form-control' name='collected_date' id='collected_date"+id+"' placeholder='Collection Date'/></td>");
    			buffer.append("<td><input type='text' class='form-control' name='collect_linen' id='collect_linen"+id+"' value='"+proddb.getProcessing()+"' placeholder='Collect Linen'/></td>");
    			buffer.append("<td><input type='text' class='form-control' value='"+proddb.getNotes()+"' name='notes' id='notes"+id+"' placeholder='Enter Notes'/></td>");
    			buffer.append("<td>Open</td>");
                buffer.append("<td><a href='#' onclick='updateLinen("+id+")' class='btn btn-primary'>Update</a></td>");			
    			
    			response.setContentType("text/html");
    			response.setHeader("Cache-Control", "no-cache");
    			response.getWriter().write(buffer.toString());

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		finally {
    	      		connection.close();
    		}

    		return null;
    
    }
    
    
    public String editlinenqty() throws Exception{
    	
    	
    	Connection connection = null;
		try {
			connection=Connection_provider.getconnection();
			String id=request.getParameter("id");			
			LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
            InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
            UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection); 
            InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
            
            
            Product proddb=laundryDAO.getLinen(id);
            Product masterprod=inventoryProductDAO.getProduct(proddb.getProduct_id());
            
            String name=userProfileDAO.getName(proddb.getCollect_by());
            Vendor vendor=inventoryVendorDAO.getVendor(proddb.getVendor_id());
            
			StringBuffer buffer = new StringBuffer();
				
			buffer.append("<td>"+masterprod.getProduct_name()+"</td>");
			buffer.append("<td>"+proddb.getRemains()+" / "+proddb.getProcessing()+"</td>");
			buffer.append("<td>"+proddb.getInhouse()+"</td>");
			
			buffer.append("<td>"+vendor.getName()+"/"+vendor.getMobile_pri()+"/"+vendor.getEmail()+"</td>");  
			buffer.append("<td> "+name+"");
			buffer.append("</td>");
			String[] datetime=proddb.getCollected_date().split(" ");
			buffer.append("<td>"+datetime[0]+"/"+datetime[1]+"</td>");
			buffer.append("<td><input type='text' class='form-control' name='collect_linen' id='collect_linen"+id+"' value='"+proddb.getProcessing()+"' placeholder='Collect Linen'/></td>");
			buffer.append("<td>"+proddb.getNotes()+"</td>");
			buffer.append("<td>Completed</td>");
            buffer.append("<td><a href='#' onclick='updateqtyLinen("+id+")' class='btn btn-primary'>Save</a></td>");			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
	      		connection.close();
		}

    	return null;
    }
    
    
    public String updatelinen() throws Exception {
    	
    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
    		String id=request.getParameter("id");
    		String collect_by=request.getParameter("collect_by");
    		String collected_date=request.getParameter("collected_date");
    		String notes=request.getParameter("notes");
    		String collect_linen=request.getParameter("collect_linen");
    		
    		if(collect_linen!=null){
    			
    			if(collect_linen.equals("")){
    				collect_linen="0";
    			}
    		}else {
    			collect_linen="0";
    		}
    		
    		Product dbproduct=laundryDAO.getLinen(id); 
    		
    	    if(dbproduct.getProcessing()==null){
    	    	
    	    	       dbproduct.setProcessing("0");
    	    }
            int processing=Integer.parseInt(collect_linen);
            
            if(dbproduct.getRemains()==null){
            	
            	dbproduct.setRemains("0");
            }
            
            int remains=Integer.parseInt(dbproduct.getRemains());
            
            remains=remains+processing;
    		Product product=new Product();
    		product.setId(Integer.parseInt(id));
    		product.setCollect_by(collect_by);
    		product.setCollected_date(collected_date);
    		product.setNotes(notes);
    		
    		int nowonprocess=Integer.parseInt(dbproduct.getProcessing())-processing;
    		if(nowonprocess==0){
         		product.setStatus("1");
    		} else {
    			product.setStatus("0");
    		}
    		product.setProcessing(""+nowonprocess+"");
    		product.setRemains(String.valueOf(remains));
    		String lastmodified=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		product.setLastmodified(lastmodified);
    		
    		int result=laundryDAO.updateLinen(product);
    		
    		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

    		
    		
		} catch (Exception e) {

		   e.printStackTrace();
		}finally {
			connection.close();
		}
    	
    	return null;
    }
    
 
 public String updateqtylinen() throws Exception{
    	
    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
    		String id=request.getParameter("id");
    		String collect_linen=request.getParameter("collect_linen");
    		
    		if(collect_linen!=null){
    			
    			if(collect_linen.equals("")){
    				collect_linen="0";
    			}
    		}else {
    			collect_linen="0";
    		}
    		
    		Product dbproduct=laundryDAO.getLinen(id); 
    		
    	    if(dbproduct.getProcessing()==null){
    	    	
    	    	       dbproduct.setProcessing("0");
    	    }
            int processing=Integer.parseInt(collect_linen);
            
            if(dbproduct.getRemains()==null){
            	
            	dbproduct.setRemains("0");
            }
            
            int remains=Integer.parseInt(dbproduct.getRemains());
            
            remains=remains-processing;
    		Product product=new Product();
    		product.setId(Integer.parseInt(id));
    		product.setQuantity(String.valueOf(collect_linen));
    		
    		if(remains==0){
         		product.setStatus("1");
    		} else {
    			product.setStatus("0");
    		}
    		product.setProcessing(""+processing+"");
    		product.setRemains(String.valueOf(remains));
    		String lastmodified=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		product.setLastmodified(lastmodified);
    		
    		int result=laundryDAO.updateqtyLinen(product);
    		
    		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

    		
    		
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
			connection.close();
		}
    	return null;
    }
    
    
    public String addmachine() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
			connection=Connection_provider.getconnection();
			LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Product> equipmentList=laundryDAO.getEquipmentTypeList();
			String location=(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			
			ArrayList<Vendor> vendroList=inventoryVendorDAO.getAllVendorList(location);
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='equipment' id='equipment"+index+"' >");
			buffer.append("<option value='0'>Select Equipment Type</option>");
			for(Product product:equipmentList){
				  
				buffer.append("<option value='"+product.getId()+"'>"+product.getName()+"</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td><input type='text' class='form-control' name='brand' id='brand"+index+"' placeholder='enter brand name'/></td>");	
			buffer.append("<td><input type='text' class='form-control' name='machine' id='machine"+index+"' placeholder='enter equipment name'/></td>");
			
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='vendor' id='vendor"+index+"' >");
			buffer.append("<option value='0'>Select Service Provider</option>");
			for(Vendor vendor:vendroList){
				  
				buffer.append("<option value='"+vendor.getId()+"'>"+vendor.getName()+"</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='frequency' id='frequency"+index+"' >");
			buffer.append("<option value='0'>Select Frequency</option>");
			buffer.append(" <option>Select</option><option>Monthly</option><option>Quarterly</option><option>Half Yearly</option><option>Yearly</option>");
			buffer.append("</select>");
			buffer.append("</td>");
			
			buffer.append("<td><input type='text' class='form-control' name='due_date' id='due_date"+index+"' placeholder='enter due date'/></td>");
			buffer.append("<td><input type='text' class='form-control' name='remainder_on' id='remainder_on"+index+"' placeholder='enter remainder date'/></td>");
			buffer.append("<td>action</td>");
			buffer.append("<td><a href='#' onclick='saveMachine("+index+")' id='save"+index+"' class='btn btn-primary'>Save</a></td>");	
            buffer.append("</tr>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
    		
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
			connection.close();
		}
    	return null;
    }
    
    
    public String savemachine() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
    		connection=Connection_provider.getconnection();
    		LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
    		String equipment=request.getParameter("equipment");
    		String brand=request.getParameter("brand");
    		String machine=request.getParameter("machine");
    		String vendor=request.getParameter("vendor");
    		String frequency=request.getParameter("frequency");
    		String due_date=request.getParameter("due_date");
    		String remainder_on=request.getParameter("remainder_on");
    		
    		
    		due_date=DateTimeUtils.getCommencingDate1(due_date);
    		remainder_on=DateTimeUtils.getCommencingDate1(remainder_on);
    		
    		Product product=new Product();
    		product.setEquipment_id(equipment);
    		product.setBrand(brand);
    		product.setMachine_name(machine);
    		product.setVendor_id(vendor);
    		product.setFrequency(frequency);
    		product.setDue_date(due_date);
    		product.setRemainder_on(remainder_on);
    		
    		int result=laundryDAO.addMachine(product);
    
    		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
			
    		
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
			connection.close();
		}

		return null; 
    }
    
    
    public String editmachine() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
    		
    		connection=Connection_provider.getconnection();
			LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			String location=(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			
			ArrayList<Product> equipmentList=laundryDAO.getEquipmentTypeList();
			ArrayList<Vendor> vendroList=inventoryVendorDAO.getAllVendorList(location);
			String id =request.getParameter("id");
			
			Product productdb=laundryDAO.getMachine(id);
			int eqid=Integer.parseInt(productdb.getEquipment_id());
			int vid=Integer.parseInt(productdb.getVendor_id());
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='equipment' id='equipment"+id+"' >");
			buffer.append("<option value='0'>Select Equipment Type</option>");
			for(Product product:equipmentList){
				if(product.getId()==eqid){    
				  buffer.append("<option value='"+product.getId()+"' selected='selected'>"+product.getName()+"</option>");
				}else {
					buffer.append("<option value='"+product.getId()+"'>"+product.getName()+"</option>");
				}
			
			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td><input type='text' class='form-control' value='"+productdb.getBrand()+"' name='brand' id='brand"+id+"' placeholder='enter brand name'/></td>");	
			buffer.append("<td><input type='text' class='form-control' value='"+productdb.getMachine_name()+"' name='machine' id='machine"+id+"' placeholder='enter equipment name'/></td>");
			
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='vendor' id='vendor"+id+"' >");
			buffer.append("<option value='0'>Select Service Provider</option>");
			for(Vendor vendor:vendroList){
				if(vendor.getId()==vid){
					buffer.append("<option value='"+vendor.getId()+"' selected='selected'>"+vendor.getName()+"</option>");
				} else { 
				   buffer.append("<option value='"+vendor.getId()+"'>"+vendor.getName()+"</option>");
				}
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<select class='form-control' name='frequency' id='frequency"+id+"' >");
			buffer.append("<option value='0'>Select Frequency</option>");
			
			if(productdb.getFrequency().equals("Monthly")){
				buffer.append(" <option>Select</option><option selected='selected'>Monthly</option><option>Quarterly</option><option>Half Yearly</option><option>Yearly</option>");
			} else if(productdb.getFrequency().equals("Quarterly")) {
				buffer.append(" <option>Select</option><option>Monthly</option><option selected='selected'>Quarterly</option><option>Half Yearly</option><option>Yearly</option>");
			} else if(productdb.getFrequency().equals("Half Yearly")){
				buffer.append(" <option>Select</option><option>Monthly</option><option>Quarterly</option><option selected='selected'>Half Yearly</option><option>Yearly</option>");
			} else if(productdb.getFrequency().equals("Yearly")){
				buffer.append(" <option>Select</option><option>Monthly</option><option>Quarterly</option><option>Half Yearly</option><option selected='selected'>Yearly</option>");
				
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			String duedate=DateTimeUtils.getCommencingDate1(productdb.getDue_date());
			String reaminddate=DateTimeUtils.getCommencingDate1(productdb.getRemainder_on());
			buffer.append("<td><input type='text' class='form-control' value='"+duedate+"' name='due_date' id='due_date"+id+"' placeholder='enter due date'/></td>");
			buffer.append("<td><input type='text' class='form-control' value='"+reaminddate+"' name='remainder_on' id='remainder_on"+id+"' placeholder='enter remainder date'/></td>");
			buffer.append("<td>action</td>");
			
			buffer.append("<td><a href='#' onclick='updateMachine("+id+")' id='save"+id+"' class='btn btn-primary'>Save</a></td>");
            
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
			 
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
			connection.close();
		}
    	
    	return null;
    }
    
    
    public String updatemachine() throws Exception{
    	Connection connection=null;
    	try {
    		
    		connection=Connection_provider.getconnection();
    		LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
    		String equipment=request.getParameter("equipment");
    		String brand=request.getParameter("brand");
    		String machine=request.getParameter("machine");
    		String vendor=request.getParameter("vendor");
    		String frequency=request.getParameter("frequency");
    		String due_date=request.getParameter("due_date");
    		String remainder_on=request.getParameter("remainder_on");

    		String id=request.getParameter("id");
    		
    		Product product=new Product();
    		product.setEquipment_id(equipment);
    		product.setBrand(brand);
    		product.setMachine_name(machine);
    		product.setVendor_id(vendor);
    		product.setFrequency(frequency);
    		product.setDue_date(due_date);
    		product.setRemainder_on(remainder_on);
    		product.setId(Integer.parseInt(id));
			
    		int result=laundryDAO.updateMachine(product);
    		

    		  response.setContentType("text/html");
		      response.setHeader("Cache-Control", "no-cache");
			  response.getWriter().write("");
    		
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
			connection.close();
		}
    	return null;
    }
    

    public String getservicehistory() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
    		int i=0;
    		connection=Connection_provider.getconnection();
    		LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
    		String id=request.getParameter("id");
    		
    		ArrayList<Product> list=laundryDAO.getServiceHistory(id);
    		StringBuffer buffer=new StringBuffer();
    		
    		for(Product product:list){
    		     buffer.append("<tr>");	
    			 buffer.append("<td>"+product.getService_date()+"</td>");
    			 buffer.append("<td>"+product.getNotes()+"</td>");
    			 buffer.append("<td>"+product.getStatus()+"</td>");
    			 buffer.append("<td><a href='#'><i class='fa fa-edit' class='disabled'></i></a></td>");
    			 buffer.append("</tr>");
    			 i++;
    		}
    		buffer.append("<tr>");
    		buffer.append("<td>1</td>");
    		buffer.append("<td><input type='text' class='form-control' placeholder='enter complain'></td>");
    		buffer.append("<td><input type='text' class='form-control' id='service_date"+i+"' placeholder='enter complain date'></td>");
    		buffer.append("<td><input type='text' class='form-control' id='notes"+i+"' placeholder='enter notes'></td>");
    		buffer.append("<td><input type='text' class='form-control' placeholder='enter resolve date'></td>");
    		buffer.append("<td>");
    		buffer.append("<select class='form-control' id='status"+i+"'>");
    		 buffer.append("<option value='Open'>Open</option>");
    		 buffer.append("<option value='Resolve'>Resolve</option>");
    		buffer.append("</select>");
    		buffer.append("</td>");
    		buffer.append("<td><a href='#' onclick=''><i class='fa fa-envelope-o' aria-hidden='true'></i></a></td>");
    		buffer.append("<td><a href='#' onclick='savemachineservice("+i+")'><i class='fa fa-save'></i></a></td>");
    		buffer.append("</tr>");
		    buffer.append("~"+i+"~"+id+"");
    		response.setContentType("text/html");
		      response.setHeader("Cache-Control", "no-cache");
			  response.getWriter().write(""+buffer.toString()+"");
    		
		} catch (Exception e) {

		  e.printStackTrace();
		}
    	finally {
			connection.close();
		}
    	
    	
    	return null;
    }
    
    
    
    public String savemachineservice() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
			connection=Connection_provider.getconnection();
			LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
			//service_date"+service_date+"&notes="+notes+"&status="+status+"";
			String service_date=request.getParameter("service_date");
			String notes=request.getParameter("notes");
			String status=request.getParameter("status");
			String machine_id=request.getParameter("machine_id");
			Product product=new Product();
			product.setMachine_id(machine_id);
			product.setService_date(service_date);
			product.setNotes(notes);
			product.setStatus(status);
			
			int result=laundryDAO.saveMachineServiceHistory(product);
			
		
			response.setContentType("text/html");
		      response.setHeader("Cache-Control", "no-cache");
			  response.getWriter().write("");
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
			connection.close();
		}
    	
    	return null;
    }
    
    
	public void prepare() throws Exception {

		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			LaundryDAO laundryDAO=new JDBCLaundryDAO(connection);
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			ArrayList<Product> machines=laundryDAO.getAllMachines();
			houseKeepingForm.setMachines(machines);
			ArrayList<Product> assetList=inventoryProductDAO.getAllProducts("1");
			houseKeepingForm.setLinenList(assetList);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
	}
	
	
	
    
    
}
