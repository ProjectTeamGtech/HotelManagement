package com.apm.Inventory.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Breadcrumbs;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.bi.InventoryCatalogueDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryCatalogueDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Inventory.web.form.VendorForm;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCPrescriptionMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InventoryVendorAction extends BaseAction implements ModelDriven<VendorForm>,Preparable {

	VendorForm vendorForm=new VendorForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	String mastername;
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(25,1);
	
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	public VendorForm getModel() {
		// TODO Auto-generated method stub
		return vendorForm;
	}

	
	@Override
	public String execute() throws Exception {

		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			
			String location= (String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			
			int totalcount=vendorDAO.getVendorlistCount(location,null);
			pagination.setPreperties(totalcount);
			ArrayList<Vendor> vendorList=vendorDAO.getAllVendorList(pagination,location,null);
			pagination.setPage_records(vendorList.size());
			vendorForm.setTotalRecords(totalcount);
			vendorForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			vendorForm.setVendorList(vendorList);
			
            session.setAttribute("pagination", pagination);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			vendorForm.setMastername(mastername);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
			
		return "vendor";
	}
	
	
	public String addcategories() throws Exception{
		
	
		try {
			
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
		}
		
		
		return "addcategories";
	}
	
	public String getstateajax() throws Exception {
		
		Connection connection=null;
		try {
			connection= Connection_provider.getconnection();
			String city = request.getParameter("city");
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			//ArrayList<Master> listCity=vendorDAO.getAllCityListByState(city);
			ArrayList<Master> listStList= vendorDAO.getAllStateListByCity(city);
			
			StringBuffer buffer=new StringBuffer();
			if(!loginInfo.isLmh()){
				buffer.append("<label></label>");
			}
			buffer.append("<select class='form-control showToolTip chosen-select' name='county' id='county'  >");
			 for(Master master:listStList){
				 buffer.append("<option value='"+master.getState()+"'>"+master.getState()+"</option>");
			 }
			buffer.append("</select>");
			//buffer.append("<label id ='townError' class='text-danger'></label>");
			/*if(loginInfo.isLmh()){
				buffer.append("<label  class='text-danger'></label>");
			}*/
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
	
	
	public String addvendor() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		};
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
			ArrayList<Product> brandnameList=productDAO.getAllBrandList(null);
			vendorForm.setBrandnameList(brandnameList);
			
			
		} catch (Exception e) {

           e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return "addvendor";
	}
	
	
	
	
	public String savevendor() throws Exception{
		
		if(!verifyLogin(request)) {
			return "login";
		}
	    
        Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			
			Vendor vendor=new Vendor();
			vendor.setName(vendorForm.getName());
			vendor.setAddress(vendorForm.getAddress());
			vendor.setEmail(vendorForm.getEmail());
			vendor.setBrand_name(vendorForm.getBrand_name());
			vendor.setMobile_pri(vendorForm.getMobile_pri());
			vendor.setMobile_sec(vendorForm.getMobile_sec());
			vendor.setPhone1(vendorForm.getPhone1());
			vendor.setPhone2(vendorForm.getPhone2());
			vendor.setTinno(vendorForm.getTinno());
			vendor.setCity(vendorForm.getCity());
			vendor.setState(vendorForm.getState());
			vendor.setPostcode(vendorForm.getPostcode());
			String creditdays=vendorForm.getCreditdays();
			//lokesh
			vendor.setBankname(request.getParameter("bankname"));
			vendor.setIfsc(request.getParameter("ifsccode"));
			vendor.setAccountno(request.getParameter("accountno"));
			vendor.setBranch(request.getParameter("bankbranch"));
			vendor.setDrug(request.getParameter("drug"));
			vendor.setSuppliercode(request.getParameter("suppliercode"));
			if(creditdays==null){
				creditdays="0";
			}
			if(creditdays.equals("")){
				creditdays="0";
			}
			vendor.setCreditdays(creditdays);
			vendor.setMin_delivery_time(vendorForm.getMin_delivery_time());
			String productlist= request.getParameter("productlist");
			vendor.setProductlist(productlist);
			//location wise add product by @k@sh
			String location=(String)session.getAttribute("location");
			   if(location==null){
			    location="0";
			   }
			vendor.setLocation(location);
			int isfrombloodbank =0;
			if(session.getAttribute("isfromcathlab")!=null){
				String isfromcathlab =  (String)session.getAttribute("isfromcathlab");
				if(isfromcathlab.equals("2")){
					isfrombloodbank = 1;
				}
			}
			vendor.setIsfrombloodbank(isfrombloodbank);
			int res=vendorDAO.addVendor(vendor);
			if(loginInfo.isKalmegha()) {
			String firstlet=vendor.getName().substring(0, 1);
			String vendocode=firstlet.toUpperCase()+"00"+res;
			int re=vendorDAO.updateSupplierCode(res,vendocode);
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
        

		if(vendorForm.getIndex()==null){
			
			return "save";
		}
		else {
			
			return "listvendor";
		}
		
		
	}
	
	
	public String editvendor() throws Exception {
		
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			String id=request.getParameter("selectedid");
			Vendor vendor=vendorDAO.getVendor(id);
			vendorForm.setId(vendor.getId());
			vendorForm.setName(vendor.getName());
			vendorForm.setAddress(vendor.getAddress());
			vendorForm.setEmail(vendor.getEmail());
			vendorForm.setBrand_name(vendor.getBrand_name());
			vendorForm.setMobile_pri(vendor.getMobile_pri());
			vendorForm.setMobile_sec(vendor.getMobile_sec());
			vendorForm.setPhone1(vendor.getPhone1());
			vendorForm.setPhone2(vendor.getPhone2());
			vendorForm.setMin_delivery_time(vendor.getMin_delivery_time());
			vendorForm.setDrug(vendor.getDrug());
			
			
			
		  	
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return "editvendor";
	}
	
	
	public String updatevendor() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			
			Vendor vendor=new Vendor();
			vendor.setId(vendorForm.getId());
			vendor.setName(vendorForm.getName());
			vendor.setAddress(vendorForm.getAddress());
			vendor.setEmail(vendorForm.getEmail());
			vendor.setBrand_name(vendorForm.getBrand_name());
			vendor.setMobile_pri(vendorForm.getMobile_pri());
			vendor.setMobile_sec(vendorForm.getMobile_sec());
			vendor.setPhone1(vendorForm.getPhone1());
			vendor.setPhone2(vendorForm.getPhone2());
			vendor.setMin_delivery_time(vendorForm.getMin_delivery_time());		
			vendor.setTinno(vendorForm.getTinno());
			vendor.setCity(vendorForm.getCity());
			vendor.setState(vendorForm.getState());
			vendor.setPostcode(vendorForm.getPostcode());
			vendor.setSuppliercode(vendorForm.getSuppliercode());
			vendor.setProductlist(request.getParameter("productlist"));
			String creditdays=vendorForm.getCreditdays();
			vendor.setBankname(request.getParameter("bankname"));
			vendor.setIfsc(request.getParameter("ifsccode"));
			vendor.setAccountno(request.getParameter("accountno"));
			vendor.setBranch(request.getParameter("bankbranch"));
			vendor.setDrug(request.getParameter("drug"));
			
			if(creditdays==null){
				creditdays="0";
			}
			if(creditdays.equals("")){
				creditdays="0";
			}
			vendor.setCreditdays(creditdays);
			
			int result=vendorDAO.updateVendor(vendor);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
        if(vendorForm.getIndex()==null){
			
			return "save";
		}
		else {
			
			return "listvendor";
		}		
	}
	
	
	public String deletevendor() throws Exception {
		
		if(!verifyLogin(request)){
			
			return "login";
		}
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection); 
			
			String id=request.getParameter("selectedid");
			int result=vendorDAO.deleteVendor(id);   
		
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
			connection.close(); 
		}
		
		return "save";
	}
	
	
	public String getvendor() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection); 
			String id=request.getParameter("selectedid");
			Vendor vendor=vendorDAO.getVendor(id);
			
		    String data=vendor.getId()+"$"+vendor.getName()+"$"+vendor.getAddress()+"$"+vendor.getEmail()+"$"+vendor.getBrand_name()+"$"+vendor.getMobile_pri()+"$"+vendor.getMobile_sec()+"$"+vendor.getPhone1()+"$"+vendor.getPhone2()+"$"+vendor.getMin_delivery_time();
			
		    
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);
		    
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
	}
	
	
public String getcities() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String stateid= request.getParameter("state");
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> listCity = vendorDAO.getAllCityListByStatefrsup(stateid);
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("<label for='email'>City</label>");
			buffer.append("<select class='form-control chosen-select' name='city' id='city'  >");
			 buffer.append("<option value='0'>Select City </option>");
			 for(Master master:listCity){
				  
				 buffer.append("<option value='"+master.getId()+"'>"+master.getCity()+"</option>");
				 
			 }
			buffer.append("</select>");
			
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

	
	public String manage() throws Exception{
		
		Connection connection=null;
		try {
			
			ArrayList<Breadcrumbs> indentflowlist = new ArrayList<Breadcrumbs>();
			ArrayList<Breadcrumbs> indentflowlisttemp = new ArrayList<Breadcrumbs>();
			if (session.getAttribute("indentflowlist") != null) {
				indentflowlisttemp = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
			}
			boolean isavilablemodule= false;
			int modulecount =0;
			for (Breadcrumbs breadcrumbs : indentflowlisttemp) {
				breadcrumbs.setIscurrent(false);
				breadcrumbs.setSqno(modulecount);
				modulecount++;
				if(breadcrumbs.getName().equals("Supplier's")){
					isavilablemodule =true;
					breadcrumbs.setIscurrent(true);
					indentflowlist.add(breadcrumbs);
					break;
				}else{
					indentflowlist.add(breadcrumbs);
				}
			}
			if(!isavilablemodule){
				Breadcrumbs breadcrumbs = new Breadcrumbs();
				breadcrumbs.setName("Supplier's");
				breadcrumbs.setOn(true);
				breadcrumbs.setSqno(modulecount);
				breadcrumbs.setUrllink("manageInventory");
				breadcrumbs.setIscurrent(true);
				breadcrumbs.setShowingname("Supplier's");
				indentflowlist.add(breadcrumbs);
			}
			session.setAttribute("indentflowlist",indentflowlist);
			
            connection=Connection_provider.getconnection();
            InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			InventoryProductDAO inventoryProductDAO= new JDBCInventoryProductDAO(connection);
			String categoryid=(String) session.getAttribute("category");
            if(categoryid==null){
            	 
            	categoryid="2";
            }
            String location=(String) session.getAttribute("location");
            if(location==null){
            	 
            	location="0";
            }
            String searchtext = vendorForm.getSearchtext();
            if(searchtext!=null){
            	if(searchtext.equals("")){
            		searchtext=null;
            	}
            }
            //commented 12-03-2020( ) because its not used any more
            //ArrayList<Product> medicineList= inventoryProductDAO.getMedicineListforVendor(location);
            ArrayList<Product> medicineList= new ArrayList<Product>();
			vendorForm.setMedicineList(medicineList);
			
			int totalcount=inventoryVendorDAO.getVendorlistCount(location,searchtext);
			pagination.setPreperties(totalcount);
			ArrayList<Vendor> vendorList=inventoryVendorDAO.getAllVendorList(pagination,location,searchtext);
			pagination.setPage_records(vendorList.size());
			vendorForm.setTotalRecords(totalcount);
			vendorForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			vendorForm.setVendorList(vendorList);
			
			ArrayList<Master> stateList= inventoryVendorDAO.getAllStateList();
			ArrayList<Master> cityList= inventoryVendorDAO.getAllCityList();
			vendorForm.setStateList(stateList);
			vendorForm.setCityList(cityList);
			vendorForm.setCategory_id(categoryid);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	    finally {
	    	connection.close();
	    }
		return "manage";
	}
	
	
	public String viewvendor() throws Exception {
		
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			String id=request.getParameter("id");
			ArrayList<Product> brandnameList=inventoryVendorDAO.getBrandList(id);
			vendorForm.setBrandnameList(brandnameList);
			Vendor supplier=inventoryVendorDAO.getVendor(id);		
			vendorForm.setName(supplier.getName());
			vendorForm.setId(supplier.getId());
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return "viewvendor";
	}
	
	
public String getcitiesajax() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String stateid= request.getParameter("state");
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> listCity=vendorDAO.getAllCityListByState(stateid);
			
			StringBuffer buffer=new StringBuffer();
			if(!loginInfo.isLmh()){
				buffer.append("<label>City</label><span class='text-danger'>*</span>");
			}
			buffer.append("<select class='form-control showToolTip chosen' name='town' id='town'  >");
			buffer.append("<option value='0'>Select City </option>");
			for(Master master:listCity){
				 buffer.append("<option value='"+master.getCity()+"'>"+master.getCity()+"</option>");
			}
			buffer.append("</select>");
			buffer.append("<label id ='townError' class='text-danger'></label>");
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


	
	public String addbrand()throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection(); 
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			String vendorid=request.getParameter("vendor");
			String brand=request.getParameter("brand");
			
			int result=inventoryVendorDAO.addUpdateBrand(brand);
			int res=inventoryVendorDAO.updateVendorbrand(result,vendorid);
			
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+vendorid+"");
			
	
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	
	
	
	
	public String saveajaxvendor() throws Exception{
		
		Connection connection=null;
		
		
		try {
			
			connection=Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			
			Vendor vendor=new Vendor();
			vendor.setName(vendorForm.getName());
			vendor.setAddress(vendorForm.getAddress());
			vendor.setEmail(vendorForm.getEmail());
			vendor.setPhone1(vendorForm.getPhone1());
			vendor.setMobile_pri(vendorForm.getMobile_pri());
			vendor.setMin_delivery_time(vendorForm.getMin_delivery_time());
			
			int res=inventoryVendorDAO.addVendor(vendor);
			
			String location=(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			
			ArrayList<Vendor> vendorList=inventoryVendorDAO.getAllVendorList(location);	
			vendorForm.setVendorList(vendorList);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "manage";
	}
	
	
	public String deletebrand() throws Exception {
		
		
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			String vendorid=request.getParameter("vendorid");
			String brandid=request.getParameter("brandid");
			int res=inventoryVendorDAO.removeBrandFormVendor(vendorid,brandid);
			
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+vendorid+"");
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		
		return null;
	}
	
	public String editbrand()throws Exception {
		
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection); 
			
			String brandid=request.getParameter("brandid");
			String vendorid=request.getParameter("vendorid");
			String row=request.getParameter("row");
			
			String brand=inventoryVendorDAO.getBrandNameFromId(brandid);
			Vendor vendor=inventoryVendorDAO.getVendor(vendorid);
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("<td>"+row+"</td>");
			buffer.append("<td>"+vendor.getName()+"</td>");
			buffer.append("<td><input type='text' id='b"+brandid+"' value='"+brand+"' ></td>");
			buffer.append("<td><a href='#' onclick='savebrand("+brandid+")'><i class='fa fa-check'></i></a>");
			buffer.append("&nbsp;&nbsp;<a href='#' onclick='reloadthis()'><i class='fa fa-times'></i></a></td>");
			
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString()); 
           
		} 
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return null;
	}
	
	
	public String updatebrand() throws Exception {
		
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			
			String brand=request.getParameter("brand");
			String brandid=request.getParameter("brandid");
			
			int result=inventoryVendorDAO.updateBrand(brandid,brand);
			
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
			
		} catch (Exception e) {

		  e.printStackTrace(); 
		}
		finally{
			connection.close();
		}
		
	   return null;	
	}
	
	
	
	public String editvendorajax()throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			InventoryCatalogueDAO catalogueDAO =new JDBCInventoryCatalogueDAO(connection);
			String location=(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			//commented 12-03-2020( ) doesn't need that
			//ArrayList<Product> list= catalogueDAO.getAllProductList(location);
			ArrayList<Product> list= new ArrayList<Product>();
			String id=request.getParameter("id");
			Vendor vendor=inventoryVendorDAO.getVendor(id);
			
			StringBuffer buffer=new StringBuffer();
			
			buffer.append(vendor.getTinno()+"~"+vendor.getName()+"~"+vendor.getEmail()+"~"+vendor.getMobile_pri()+"~"+vendor.getMin_delivery_time());
			buffer.append("~"+vendor.getAddress()+"~"+vendor.getState()+"~"+vendor.getCity()+"~"+vendor.getPostcode()+"~"+vendor.getSuppliercode());
			
		    buffer.append("~");
		    
		    buffer.append("<li><input type='checkbox' onclick='selectAll(this)'/> Select All </li>");
		    if(vendor.getProductlist()==null){
		    	vendor.setProductlist("");
		    }
		    
		    for(Product product: list){
		    	  boolean flag=false;
		    	  for(String str: vendor.getProductlist().split(",")){
		    		   int td=Integer.parseInt(str);
		    		  	if(td==product.getId()){
		    		  		flag=true;
		    		  		break;
		    		  	}
		    	  }
		    	  
		    	  if(flag){
		    		  buffer.append("<li><input class='case' type='checkbox' value='"+product.getId()+"' checked='checked' /> "+product.getProduct_name()+" </li>");
		    	  } else {
		    		  buffer.append("<li><input class='case' type='checkbox' value='"+product.getId()+"' /> "+product.getProduct_name()+" </li>");
		    	  }
		    }
		    String creditdays= vendor.getCreditdays();
		    if(creditdays.equals("0")){
		    	creditdays="";
		    }
		    buffer.append("~");
		    buffer.append(creditdays);
		    buffer.append("~"+DateTimeUtils.isNull(vendor.getBankname())+"~"+DateTimeUtils.isNull(vendor.getBranch())+"~"+DateTimeUtils.isNull(vendor.getIfsc())+"~"+DateTimeUtils.isNull(vendor.getAccountno())+"~"+DateTimeUtils.isNull(vendor.getDrug())+"~");
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString()); 
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		finally{
			connection.close();
			
		}
		
		return null;
	}
	
	
	public String delete() throws Exception {
		
         Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			String id=request.getParameter("selectedid");
			int res=inventoryVendorDAO.deleteVendor(id);
			String location=(String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			
			/*ArrayList<Vendor> vendorList=inventoryVendorDAO.getAllVendorList(location);	
			vendorForm.setVendorList(vendorList);*/
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{ 
			connection.close();
		}
		
		return "manage";
	}
	
	
	public String updatevendorajax() throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			String mobile=request.getParameter("mobile");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			String delivery=request.getParameter("delivery");
			
			Vendor vendor=new Vendor();
			vendor.setId(Integer.parseInt(id));
			vendor.setName(name);
			vendor.setAddress(address);
			vendor.setMobile_pri(mobile);
			vendor.setPhone1(phone);
			vendor.setEmail(email);
			vendor.setMin_delivery_time(delivery);
			
			
			int result=inventoryVendorDAO.updateVendorAjax(vendor);
			
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""); 
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return null;
	}
	
	
public String getstateajaxnew() throws Exception {
		
		Connection connection=null;
		try {
			connection= Connection_provider.getconnection();
			String city = request.getParameter("city");
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			//ArrayList<Master> listCity=vendorDAO.getAllCityListByState(city);
			ArrayList<Master> listStList= vendorDAO.getAllStateListByCity(city);
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("<label>State</label>");
			buffer.append("<select class='form-control showToolTip' name='state' id='state'  >");
			 for(Master master:listStList){
				 buffer.append("<option value='"+master.getState()+"'>"+master.getState()+"</option>");
			 }
			buffer.append("</select>");
			buffer.append("<label id ='stateError' class='text-danger'></label>");
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(listStList.get(listStList.size()-1).getState());
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	public void prepare() throws Exception {
 
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO= new JDBCInventoryVendorDAO(connection);
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			vendorForm.setMasterlist(masterlist);
			
			String categoryid=(String)session.getAttribute("category");
			if(categoryid==null){
				 categoryid="2";
			}
			ArrayList<Master> locationListPharmacy= pharmacyDAO.getAllLocation();
			vendorForm.setLocationListPharmacy(locationListPharmacy);
			 
			String location= (String) session.getAttribute("location");
			if(location==null){
				location="0";
			}
			vendorForm.setLocation(location);
			
			
			 ArrayList<Product> brandnameList= inventoryProductDAO.getAllBrandListByCategory(categoryid);
			 vendorForm.setBrandnameList(brandnameList);
			
			 /*ArrayList<Product> productList= inventoryProductDAO.geProductList(categoryid);
			 vendorForm.setProductList(productList);*/
			 
			 ArrayList<Vendor> vendorList= inventoryVendorDAO.getAllVendorList(location);
			 vendorForm.setVendorList(vendorList);
			 
			 ArrayList<Product> categoryList=inventoryProductDAO.getAllCategories(null);
			 vendorForm.setCategoryList(categoryList);
				
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
//  14/11/2018 Check gstno exist.
public String checkgstno() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String gstno= request.getParameter("gstno");
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			int res=vendorDAO.checkgstno(gstno);
			if(res!=0){
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("1"); 
			}else{
				response.setContentType("text/html");
			    response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("0"); 
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}

public String checkgstnoforedit() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		String gstno= request.getParameter("gstno");
		String vendorid= request.getParameter("id");
		InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
		int res=vendorDAO.checkgstnoforedit(gstno,vendorid);
		if(res!=0){
		response.setContentType("text/html");
	    response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("1"); 
		}else{
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("0"); 
		}
	} catch (Exception e) {

		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return null;
}
public String getstateajaxt() throws Exception {
	
	Connection connection=null;
	try {
		connection= Connection_provider.getconnection();
		String city = request.getParameter("city");
		InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
		//ArrayList<Master> listCity=vendorDAO.getAllCityListByState(city);
		ArrayList<Master> listStList= vendorDAO.getAllStateListByCity1(city);
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("<label>State</label>");
		buffer.append("<select class='form-control showToolTip' name='state' id='state'  >");
		 for(Master master:listStList){
			 buffer.append("<option value='"+master.getState()+"'>"+master.getState()+"</option>");
		 }
		buffer.append("</select>");
		//buffer.append("<label id ='townError' class='text-danger'></label>");
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

public String getnewstateajax() throws Exception {
	
	Connection connection=null;
	try {
		connection= Connection_provider.getconnection();
		String city = request.getParameter("city");
		InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
		//ArrayList<Master> listCity=vendorDAO.getAllCityListByState(city);
		ArrayList<Master> listStList= vendorDAO.getAllStateListByCity1(city);
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("<label>State</label>");
		buffer.append("<select class='form-control showToolTip' name='state' id='state'  >");
		 for(Master master:listStList){
			 buffer.append("<option value='"+master.getStateid()+"'>"+master.getState()+"</option>");
		 }
		buffer.append("</select>");
		//buffer.append("<label id ='townError' class='text-danger'></label>");
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






public String getDaysajax() throws Exception {
	
	Connection connection=null;
	try {
		connection= Connection_provider.getconnection();
		String planid = request.getParameter("planid");
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		
		ArrayList<Client>plandayslist=clientDAO.getallPlandayslistbyid(planid);
		
		
		StringBuffer buffer=new StringBuffer();
		if(!loginInfo.isLmh()){
			buffer.append("<label>Plan</label>");
		}
		buffer.append("<label>Total Days</label><label class='text-danger'>*</label>");
		buffer.append("<select class='form-control showToolTip chosen-select' name='planday' id='planday' >");

		 for(Client client:plandayslist){
			 buffer.append("<option value='"+client.getDays()+"'>"+client.getDays()+"</option>");
		 }
		buffer.append("</select>");
		//buffer.append("<label id ='townError' class='text-danger'></label>");
		/*if(loginInfo.isLmh()){
			buffer.append("<label  class='text-danger'></label>");
		}*/
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

}
