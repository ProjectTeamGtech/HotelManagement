package com.apm.Inventory.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Inventory.web.form.ProductForm;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InventoryProductMasterAction extends BaseAction implements Preparable,ModelDriven<ProductForm>{
	
	ProductForm productForm=new ProductForm();
    HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(10,1);
	String mastername;
	
	
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ProductForm getModel() {
		// TODO Auto-generated method stub
		return productForm;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	public String categories()throws Exception {
		
	   if(!verifyLogin(request)){
		   return "login";
	   }
		
	   Connection connection=null;
	   
	   try {
		   
		   connection=Connection_provider.getconnection();
		   InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		   
		   int count=inventoryProductDAO.countAllCategories();
		   pagination.setPreperties(count);
		   ArrayList<Product> categoryList=inventoryProductDAO.getAllCategories(pagination);
		   pagination.setPage_records(categoryList.size());
		   productForm.setTotalRecords(count);
		   productForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		   
		   productForm.setCategoryList(categoryList);
		    
		  
		   mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			productForm.setMastername(mastername);
		   
		   
	   } catch (Exception e) {

		   e.printStackTrace();
	   }
	   finally {
		   connection.close();
	   }
	  
	   return "categories";
	}
	
	
	
	
	public String addcategories() throws Exception{
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		    
			ArrayList<Product> categoryList=inventoryProductDAO.getAllCateoryandSub();
			productForm.setCategoryList(categoryList);
	   	
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
		
		
		return "addcategories";
	}
	
	
	
	
	
	
	
	
	public String addcategory() throws Exception{
		Connection connection=null;
		if(!verifyLogin(request)){
			return "login";
		}
		else{
			try {
				connection=Connection_provider.getconnection();
				InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
				
			    
				ArrayList<Product> awrehouseList=inventoryProductDAO.getWarehouse();
				productForm.setWarehouseListNew(awrehouseList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				connection.close();
			}
			
		}
		
	
		return "addcategory";
	}
	
	
	public String savecategory() throws Exception{ 
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			Product product=new Product();
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			
			String a=productForm.getWarehouseids();
			product.setWarehouse_id(productForm.getWarehouseids());
			
		    int res=inventoryProductDAO.addCategory(product);
			
		    
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
		

	    return categories();
	}
	
	public String editcategory() throws Exception {
		 
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);	
		    String selectedid=request.getParameter("selectedid");
		    Product productobj=inventoryProductDAO.getCategoryWarehouseById(selectedid);
		    
		    ArrayList<Product> awrehouseList=inventoryProductDAO.getWarehouse();
		
			
		    
		    
		    
           if(productobj.getWarehouse_id()!=null){
				
				String titles[]=productobj.getWarehouse_id().split(",");
	           	for(String str:titles){
	           		if(str.equals("0")){
	           			continue;
	           		} else {
	           			int index=Integer.parseInt(str);
	           			int oid=index-1;
	           			awrehouseList.get(oid).setChecked(true);
	           		}
	           	}
			}
		    
         	productForm.setWarehouseListNew(awrehouseList);
			productForm.setName(productobj.getName());
			productForm.setDescription(productobj.getDescription());
			productForm.setId(productobj.getId());
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	   return "editcategory";	
	}
	
	
	public String updatecategory() throws Exception{
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			Product product=new Product();
			String a=productForm.getName();
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			product.setId(productForm.getId());
			
			
			product.setWarehouse_id(productForm.getWarehouseids());
			
		    int res=inventoryProductDAO.updateCategory(product);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();	
		}
		
		
	   return categories();
	}
	
	public String deletecategory() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection); 
			String id=request.getParameter("selectedid");
			int res=productDAO.deleteCategory(id);
			
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return categories();
	}
	
	
	
	public String deletecategoryajax() throws Exception {
		
        if(!verifyLogin(request)){
        	return "login";
        }
        
        Connection connection=null;
        
        try {
			
        	connection=Connection_provider.getconnection();
        	InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
        	
        	String categoryid=request.getParameter("category");
        	int result=inventoryProductDAO.deleteCategoryandSubcategory(categoryid);
        	
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
	
	public String editcategoryajax() throws Exception {
		
		 if(!verifyLogin(request)){
	        	return "login";
	        }
	        
	        Connection connection=null;
	        
	        try {
				
	        	connection=Connection_provider.getconnection();
	        	InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
	        	
	        	String id=request.getParameter("id");
	        	String category=request.getParameter("category");
	        	
	        	StringBuffer buffer=new StringBuffer();
	        	buffer.append("<h4><spam><input type='text' style='color:black' id='b"+id+"' value='"+category+"' ></spam>");
	        	buffer.append("<button onclick='updatecategory("+id+")' class='btn btn-primary btn-xs pull-right editMainmenu'>Update</button>");
	        	buffer.append("<button onclick='reloadthis()' class='btn btn-primary btn-xs pull-right btndeleteMainmenu marr5'>Cancel</button>");
	        	buffer.append("</h4>");
	        	
	        	
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
	
	
	public String updatecategoryajax() throws Exception {
		
		
		if(!verifyLogin(request)){
        	return "login";
        }
        
        Connection connection=null;
        
        try {
			
        	connection=Connection_provider.getconnection();
        	InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
        	
        	String id=request.getParameter("id");
        	String category=request.getParameter("category");
        	
        	Product product=new Product();
        	product.setId(Integer.parseInt(id));
        	product.setName(category);
        	
            int result=inventoryProductDAO.updateCategory(product);
                	
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
	
	
	public String listsubcategories() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
	
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			int count=inventoryProductDAO.getTotalSubCategoryCount();
			pagination.setPreperties(count);
			ArrayList<Product> subcategoryList=inventoryProductDAO.getAllSubCategoryList(pagination);
			
			 pagination.setPage_records(subcategoryList.size());
			 productForm.setTotalRecords(count);
			 productForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			   
			 productForm.setSubcategoryList(subcategoryList);
			    
			  
			   mastername = request.getParameter("selectedid");
				if (mastername != null) {

					session.setAttribute("mastername", mastername);
				} else {
					mastername = (String) session.getAttribute("mastername");
				}
				productForm.setMastername(mastername);
	        		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		
		
		return "subcategories";
	}
	
	public String editsubcategory() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);	
			String id=request.getParameter("id");
		    Product product=inventoryProductDAO.getSubCategory(id);
		    
		    productForm.setCategory_id(product.getCategory_id());
			productForm.setName(product.getName());
			productForm.setDescription(product.getDescription());
			productForm.setId(product.getId());
			productForm.setChecked(product.isChecked());
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
	
		return "editsubcategory";
	}
	
	public String addsubcategory() throws Exception {
		
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "addsubcategory";
		
	}
	
	
	public String savesubcategory() throws Exception {
		
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			Product product=new Product();
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			product.setCategory_id(productForm.getCategory_id());
			
		    int res=inventoryProductDAO.addSubCategory(product);
			
		    
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
		
	
		return listsubcategories();
	}
	
	
public String updatesubcategory() throws Exception{
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			Product product=new Product();
			product.setId(productForm.getId());
			product.setCategory_id(productForm.getCategory_id());
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			
			int result=inventoryProductDAO.updateSubCategory(product);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();	
		}
	
	   return listsubcategories();
	}

public String deletesubcategory() throws Exception {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection); 
		String id=request.getParameter("id");
		int res=productDAO.deleteSubCategory(id);
		
	} catch (Exception e) {

	   e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return listsubcategories();
	
}

   
	
    public String listbrand() throws Exception{
    	
    	if(!verifyLogin(request)){
			return "login";
		}
	
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			int count=inventoryProductDAO.getTotalBrandCount();
			pagination.setPreperties(count);
			ArrayList<Product> brandnameList=inventoryProductDAO.getAllBrandList(pagination);
			
			 pagination.setPage_records(brandnameList.size());
			 productForm.setTotalRecords(count);
			 productForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			   
			 productForm.setBrandnameList(brandnameList);
			    
			  
			   mastername = request.getParameter("selectedid");
				if (mastername != null) {

					session.setAttribute("mastername", mastername);
				} else {
					mastername = (String) session.getAttribute("mastername");
				}
				productForm.setMastername(mastername);
	        		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
    	
    	
    	return "listbrand";
    }
  
    public String addbrand() {
    	
    	if(!verifyLogin(request)){
    		return "login";
    	}
    	
    	return "addbrand";
    }
    
     public String savebrand() throws Exception {
		
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			Product product=new Product();
			product.setName(productForm.getName());
			product.setDescription(productForm.getDescription());
			product.setCategory_id(productForm.getCategory_id());
			product.setSubcategory_id(productForm.getSubcategory_id());
			product.setVendor(productForm.getVendor());
			
		    int res=inventoryProductDAO.addBrandname(product);
			
		    
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
	
		return listbrand();
	}
    
    
    public String editbrand() throws Exception {
    	
    	if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
		    InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);	
			String id=request.getParameter("id");
		    Product product=inventoryProductDAO.getBrandName(id);
		    
		    productForm.setCategory_id(product.getCategory_id());
		    productForm.setSubcategory_id(product.getSubcategory_id());
			productForm.setName(product.getName());
			productForm.setDescription(product.getDescription());
			productForm.setId(product.getId());
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
    	
    	return "editbrand";
    }
    
    

    public String getsubcategory() throws Exception {
    	
    	
    	if(!verifyLogin(request)){
    		return "login";
    	}
    	
    	Connection connection=null;
    	
    	try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection);
			String category_id=productForm.getCategory();
			
			ArrayList<Product> subcategoryList=productDAO.getSubCategoryList(category_id);
			productForm.setSubcategoryList(subcategoryList);
			productForm.setCategory_id(category_id);
			
    		
		} catch (Exception e) {

		  e.printStackTrace();
		}
		finally {
			connection.close();
		}
    	
    	return "addbrand";
    }
    
    
	
       public String updatebrand() throws Exception{
    		
    		Connection connection=null;
    		
    		try {
    			connection=Connection_provider.getconnection();
    			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
    			Product product=new Product();
    			product.setId(productForm.getId());
    			product.setCategory_id(productForm.getCategory_id());
    			product.setSubcategory_id(productForm.getSubcategory_id());
    			product.setName(productForm.getName());
    			product.setDescription(productForm.getDescription());
    			
    			int result=inventoryProductDAO.updateBrandName(product);
    			
    		} catch (Exception e) {

    		   e.printStackTrace();
    		}
    		finally{
    			connection.close();	
    		}
    	
    	   return listbrand();
    	} 
 
   

       public String deletebrand() throws Exception {
       	
       	Connection connection=null;
       	try {
       		connection=Connection_provider.getconnection();
       		InventoryProductDAO productDAO=new JDBCInventoryProductDAO(connection); 
       		String id=request.getParameter("id");
       		int res=productDAO.deleteBrandname(id);
       		
       	} catch (Exception e) {

       	   e.printStackTrace();
       	}
       	finally{
       		connection.close();
       	}
       	return listbrand();
       	
       }

    
      public String saveadd() throws Exception {
    	  
        Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
    		Product product=new Product();
    		String category=request.getParameter("category");
    		product.setName(category);
			int result=inventoryProductDAO.addCategory(product);
			
    		
			 
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
       
    
   public String savesub() throws Exception {
	
	Connection connection=null;   
	   
	try {
		connection=Connection_provider.getconnection();
		InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
		
		String categoryid=request.getParameter("categoryid");
		String subcategory=request.getParameter("subcategory");
		
		Product product=new Product();
		product.setCategory_id(categoryid);
		product.setName(subcategory);
		
		int res=inventoryProductDAO.addSubCategory(product);
		
		
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
      
   
   public String updatesubcategoryajax() throws Exception{
	   
	   Connection connection=null;
   	   try {
   		   	connection=Connection_provider.getconnection();
   			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
   			Product product=new Product();
   			String id=request.getParameter("id");
   			String subcategory=request.getParameter("subcategory");
   			product.setId(Integer.parseInt(id));
   			product.setName(subcategory);
   			
			int result=inventoryProductDAO.updateSubCategoryOnly(product);
			
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
   
   
      
   public String editsubcategoryajax() throws Exception {
	   
	     
	   Connection connection=null;   
	   
		try {
			connection=Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			
			String id=request.getParameter("id");
			String subcategory=request.getParameter("subcategory");
			
			StringBuffer buffer=new StringBuffer();
        	buffer.append("<h4><spam><input type='text' style='color:black' id='ss"+id+"' value='"+subcategory+"' ></spam>");
        	buffer.append("<button onclick='updatesubcategory("+id+")' class='btn btn-primary btn-xs pull-right editMainmenu'>Update</button>");
        	buffer.append("<button onclick='reloadthis()' class='btn btn-primary btn-xs pull-right btndeleteMainmenu marr5'>Cancel</button>");
        	buffer.append("</h4>");
        	
			
						
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
   
   
   public String deletesubcategoryajax()throws Exception{
	   
	   if(!verifyLogin(request)){
       	return "login";
       }
       
       Connection connection=null;
       
       try {
			
       	connection=Connection_provider.getconnection();
       	InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
       	
       	String subcategoryid=request.getParameter("subcategory");
       	int result=inventoryProductDAO.deleteSubCategory(subcategoryid);
       	
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
   
   
	public void prepare() throws Exception {

		
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Product> categoryList=inventoryProductDAO.getAllCategories(null);
			productForm.setCategoryList(categoryList); 
			
			ArrayList<Product> subcategoryList=inventoryProductDAO.getAllSubCategoryList(null);
			productForm.setSubcategoryList(subcategoryList); 
			
			ArrayList<Vendor> vendorList=inventoryVendorDAO.getAllVendorList("0");
			productForm.setVendorList(vendorList);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			productForm.setMasterlist(masterlist);
			
			String categoryid=(String)session.getAttribute("category");
			if(categoryid==null){
				categoryid="1";
			}
			
			ArrayList<Product> brandnameList= inventoryProductDAO.getAllBrandListByCategory(categoryid);
			productForm.setBrandnameList(brandnameList);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
	
	
}
