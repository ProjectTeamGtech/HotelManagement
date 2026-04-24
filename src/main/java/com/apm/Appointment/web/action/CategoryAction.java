package com.apm.Appointment.web.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.common.utils.Pagination;
import com.apm.Appointment.eu.bi.BranchDAO;
import com.apm.Appointment.eu.bi.CategoryDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;

import com.apm.Appointment.eu.blogic.jdbc.JDBCCategoryDAO;
import com.apm.Appointment.eu.entity.Branch;
import com.apm.Appointment.eu.entity.Category;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.Appointment.web.form.CategoryForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends BaseAction implements ModelDriven<CategoryForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	CategoryForm categoryForm = new CategoryForm();
	Connection connection = null;
	private Pagination pagination = new Pagination(5, 1);

	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		String selectedBranch = "";
		try{
			
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			
			if(session.getAttribute("selectedBranch")!=null){
				selectedBranch = (String)session.getAttribute("selectedBranch");
//				System.out.println(selectedBranch);
			}
			
			
					//pagination
					int totalCount = categoryDAO.getCategoryCount(categoryForm.getSearchText(),loginInfo.getUserId());
					pagination.setPreperties(totalCount);
					ArrayList<Category>categoryList = categoryDAO.getCategoryList(pagination,categoryForm.getSearchText(),loginInfo.getUserId());
					categoryForm.setCategoryList(categoryList);
					pagination.setPage_records(categoryList.size());
					
					categoryForm.setTotalRecords(totalCount);
				
					categoryForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
	
	
		return SUCCESS;
	}
	
	
	public void getadminDetails(){
		
		//set branchlist
		
			session.setAttribute("selectedBranch", "0");
			
			
			ArrayList<Branch>populateBranchList = new ArrayList<Branch>();
			
		
		
	}
	
	
	/*public String save(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		try{
			String selectedBranch = "";
			if(session.getAttribute("selectedBranch")!=null){
				selectedBranch = (String)session.getAttribute("selectedBranch");
				System.out.println(selectedBranch);
			}
			
			connection = Connection_provider.getconnection();
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			Category category = new Category();
			
			category.setCategoryName(categoryForm.getCategoryName());
			category.setDescription(categoryForm.getDescription());
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
			if(loginInfo.getUserType()==1){
				if(selectedBranch.equals("0")){
					selectedBranch = "administrator";
				}
				System.out.println("admin");
				int result = categoryDAO.saveCategory(category,selectedBranch);
			}else{
				int result = categoryDAO.saveCategory(category,loginInfo.getUserId());
				System.out.println("admin not");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		return "save";
	}*/
	
	public String edit() throws SQLException
	{
		if(!verifyLogin(request)){
			return "login";
		}
		
		try{
		Connection connection = Connection_provider.getconnection();

		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
		int selectedid= Integer.parseInt(request.getParameter("selectedid"));
		
		CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
		
		Category category=categoryDAO.getCategory(selectedid);
		
		categoryForm.setId(category.getId());
		categoryForm.setCategoryName(category.getCategoryName());
		categoryForm.setDescription(category.getDescription());
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "edit";
		
	}
	
	public String update() throws SQLException
	{
		if(!verifyLogin(request)){
			return "login";
		}
		try{
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			Category category = new Category();
			int selectedid = categoryForm.getId();
			
			Connection connection = Connection_provider.getconnection();

			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			
			
			category.setCategoryName(categoryForm.getCategoryName());
			category.setDescription(categoryForm.getDescription());
			
			
			int result=categoryDAO.updateCategory(category,selectedid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "update";
	}
	
public String delete() throws SQLException{
	if(!verifyLogin(request)){
		return "login";
	}
		
		try{
			Connection connection = Connection_provider.getconnection();
			
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.ServletActionContext.HTTP_REQUEST);
			int selectedid= Integer.parseInt(request.getParameter("selectedid"));
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			
			
			int deleteProduct = categoryDAO.deleteProduct(selectedid);
			int deleteSubCategory = categoryDAO.deleteSubCategory(selectedid);
			int result = categoryDAO.deleteCategory(selectedid);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "delete";
	}
	
	public String product() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection(); 
			int categoryID = Integer.parseInt(request.getParameter("categoryid"));
			String productName = request.getParameter("productName");
			CategoryDAO categoryDAO = new JDBCCategoryDAO(connection);
			
			boolean isProductExist = categoryDAO.isProductExist(categoryID,productName);
			
			if(isProductExist){
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("<font color = 'red'>This product is allready exist for this category!!</font>"); 
				
			}else{
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("go"); 
			}
			
//			System.out.println(categoryID);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
		
	}

	public CategoryForm getModel() {
	
		return categoryForm;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
