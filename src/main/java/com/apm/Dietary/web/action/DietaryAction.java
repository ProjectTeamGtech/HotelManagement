package com.apm.Dietary.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Dietary.eu.bi.DietaryDAO;
import com.apm.Dietary.eu.blogic.jdbc.JDBCDietaryDAO;
import com.apm.Dietary.eu.entity.Dietary;
import com.apm.Dietary.web.form.DietaryForm;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DietaryAction extends BaseAction implements ModelDriven<DietaryForm>,Preparable {
	DietaryForm dietaryForm = new DietaryForm();
	String mastername;
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	public DietaryForm getModel() {
		// TODO Auto-generated method stub
		return dietaryForm;
	}
	
	public String execute() throws Exception { 
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			DietaryDAO dietaryDAO=new JDBCDietaryDAO(connection);
			ArrayList<Dietary> listdietary = dietaryDAO.viewDietaryCategory();
			dietaryForm.setListdietary(listdietary);
			
			mastername=request.getParameter("selectedid");
			
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			} else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			
			dietaryForm.setMastername(mastername);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return SUCCESS;
	}

	public String add(){
		return "add";
	}
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
			Dietary dietary = new Dietary();
			//dietary.setId(dietaryForm.getId());
			dietary.setName(dietaryForm.getName());
			dietary.setDescription(dietaryForm.getDescription());
			int result =dietaryDAO.addDietaryCategory(dietary);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "save";
	}

	public String edit() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
			//int id = Integer.parseInt(request.getParameter("id"));
			Dietary dietary = new Dietary();
			dietary.setId(dietaryForm.getId());
			Dietary dietary2 = dietaryDAO.getinfoCategory(dietaryForm.getId());
			dietaryForm.setName(dietary2.getName());
			dietaryForm.setDescription(dietary2.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "edit";
	}
	
	public String update() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
			Dietary dietary = new Dietary();
			dietary.setId(dietaryForm.getId());
			dietary.setName(dietaryForm.getName());
			dietary.setDescription(dietaryForm.getDescription());
			int result =dietaryDAO.updateDietaryCategory(dietary);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "update";
	}

	public String del() throws Exception{
		Connection connection = null;;
		try {
			connection = Connection_provider.getconnection();
			DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
			//int id = Integer.parseInt(request.getParameter("id"));
			Dietary dietary = new Dietary();
			dietary.setId(dietaryForm.getId());
			int i = dietary.getId();
//			System.out.println(dietary.getId());
			int result = dietaryDAO.deleteInfoCategory(dietary);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "delete";
	}
	
	
	public void prepare() throws Exception {	
		Connection connection = null;
		
		try { 
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			dietaryForm.setMasterlist(masterlist);
			
			mastername=(String)session.getAttribute("mastername");
			
			dietaryForm.setMastername(mastername);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
	

}
