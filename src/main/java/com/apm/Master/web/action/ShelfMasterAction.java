package com.apm.Master.web.action;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.ShelfMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCShelfMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.ShelfMaster;
import com.apm.Master.web.form.ShelfMasterForm;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ShelfMasterAction extends BaseAction implements ModelDriven<ShelfMasterForm>,Preparable {

	ShelfMasterForm shelfMasterForm = new ShelfMasterForm();
	String mastername;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	public ShelfMasterForm getModel() {
		return shelfMasterForm;
	}

	
	public String execute() throws Exception {
		Connection connection= null;
		try {
			connection = Connection_provider.getconnection();
			ShelfMasterDAO shelfMasterDAO = new JDBCShelfMasterDAO(connection);
			ArrayList<ShelfMaster> shelflist =shelfMasterDAO.getallShelf();
			shelfMasterForm.setShelflist(shelflist);
			
			mastername = request.getParameter("selectedid");
			
			if(mastername!=null){
				session.setAttribute("mastername", mastername);
			}else{
				mastername =(String) session.getAttribute("mastername");	
			}
			shelfMasterForm.setMastername(mastername);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "shelf";
	}
	
	public String add(){
		return "addshelf";
	}

	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			ShelfMasterDAO shelfMasterDAO = new JDBCShelfMasterDAO(connection);
			ShelfMaster shelfMaster = new ShelfMaster();
			shelfMaster.setName(shelfMasterForm.getName());
			shelfMaster.setDepartmentid(shelfMasterForm.getDepartmentid());
			int result = shelfMasterDAO.addshelfDB(shelfMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "saveshelf";
	}
	
	public String edit(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			ShelfMasterDAO shelfMasterDAO = new JDBCShelfMasterDAO(connection);
			ShelfMaster shelfMaster = new ShelfMaster();
			shelfMaster.setId(shelfMasterForm.getId());
			ShelfMaster shelfMaster2 = shelfMasterDAO.getshelfinfo(shelfMaster);
			shelfMasterForm.setId(shelfMaster2.getId());
			shelfMasterForm.setName(shelfMaster2.getName());
			shelfMasterForm.setDepartmentid(shelfMaster2.getDepartmentid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editshelf";
	}
	
	public String update() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			ShelfMasterDAO shelfMasterDAO = new JDBCShelfMasterDAO(connection);
			ShelfMaster shelfMaster = new ShelfMaster();
			shelfMaster.setId(shelfMasterForm.getId());
			shelfMaster.setName(shelfMasterForm.getName());
			shelfMaster.setDepartmentid(shelfMasterForm.getDepartmentid());
			int result = shelfMasterDAO.updateshelfDB(shelfMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "updateshelf";
	}

	
	public String delete(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			ShelfMasterDAO shelfMasterDAO = new JDBCShelfMasterDAO(connection);
			ShelfMaster shelfMaster= new ShelfMaster();
			shelfMaster.setId(shelfMasterForm.getId());
			int result = shelfMasterDAO.deleteshelfDB(shelfMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deletedshelf";
	}
	
	public void prepare() throws Exception {
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			shelfMasterForm.setMasterlist(masterlist);
			
			mastername = (String) session.getAttribute("mastername");
			shelfMasterForm.setMastername(mastername);
			
			ArrayList<Master> locationlist = masterDAO.getAllLocation(null);
			shelfMasterForm.setLocationlist(locationlist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
	}
	
	 public String checkshelfalreadypresent() throws Exception{
		 if (!verifyLogin(request)) {

				return "login";
			}
		 Connection connection = null;
		 try {
			connection = Connection_provider.getconnection();
			ShelfMasterDAO shelfMasterDAO = new JDBCShelfMasterDAO(connection);
			String name = request.getParameter("name");
			String departmentid = request.getParameter("departmentid");
			String id = request.getParameter("id");
			String val = request.getParameter("val");
			int res =  shelfMasterDAO.checkAlreadyShelfPresent(name,departmentid,id,val);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+res+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		 return null;
	 }
	 
	
	

}
