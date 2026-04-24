package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.apm.Master.eu.bi.MasterDAO;

import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.LocationMasterForm;
import com.apm.Master.web.form.MasterForm;
import com.apm.Master.web.form.ProductTypeMasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ProductTypeMasterAction extends BaseAction implements ModelDriven<ProductTypeMasterForm>,Preparable{

	ProductTypeMasterForm productTypeMasterForm = new ProductTypeMasterForm();
	String mastername;
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	 Pagination pagination=new Pagination(10,1);
	
	 
	 public Pagination getPagination() {
			return pagination;
		}

		public void setPagination(Pagination pagination) {
			this.pagination = pagination;
		}

	public ProductTypeMasterForm getModel() {
		return productTypeMasterForm;
	}
	
	public String execute() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			int count=masterDAO.getProductTypeMasterCount();
			pagination.setPreperties(count);
			String searchText = productTypeMasterForm.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Master> productypelist = masterDAO.getAllproductypelist(searchText,pagination);
			productTypeMasterForm.setProductypelist(productypelist);
			pagination.setPage_records(productypelist.size());
			productTypeMasterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			productTypeMasterForm.setTotalRecords(String.valueOf(count));
			mastername=request.getParameter("selectedid");
			
			if(mastername!=null){
				 session.setAttribute("mastername", mastername);
			} else {
				mastername=(String)session.getAttribute("mastername");
			}
			productTypeMasterForm.setMastername(mastername);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "producttypemaster";
	}

	public String add(){
		return "addproducttypemaster";	
	}
	
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setName(productTypeMasterForm.getName());
			int result = masterDAO.addproductypelist(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "saveproducttypemaster";
	}
	

	
	public String edit(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setId(productTypeMasterForm.getId());
			Master master2 = masterDAO.getproducttypemasterinfo(master);
			
			productTypeMasterForm.setId(master2.getId());
			productTypeMasterForm.setName(master2.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editproducttypemaster";
	}
	
	public String update() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setId(productTypeMasterForm.getId());
			master.setName(productTypeMasterForm.getName());
			int result = masterDAO.updateproducttypemaster(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "updateproducttypemaster";
	}

	
	public String delete(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setId(productTypeMasterForm.getId());
			int i = master.getId();
			int result = masterDAO.deleteproducttypemaster(master);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deletedproducttypemaster";
	}
	


	public void prepare() throws Exception {
		Connection connection = null;
		
		try { 
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			productTypeMasterForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			productTypeMasterForm.setMastername(mastername);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
}
