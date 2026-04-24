package com.apm.Registration.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Registration.eu.bi.PracticeManagerDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCParcticeManagerDAO;
import com.apm.Registration.eu.entity.PracticeManager;
import com.apm.Registration.web.form.PracticeManagerForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;



public class PracticeManagerAction extends BaseAction implements ModelDriven<PracticeManagerForm> {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	PracticeManagerForm practiceManagerForm = new PracticeManagerForm();
	
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		//show practice manager
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PracticeManagerDAO practiceManagerDAO = new JDBCParcticeManagerDAO(connection);
			
			//start coding
			ArrayList<PracticeManager>practiceManagerList = practiceManagerDAO.getPracticeManagerList();
			
			practiceManagerForm.setPracticeManagerList(practiceManagerList);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
	 
		return SUCCESS;
	}
	
	
	public String save() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		//save practice manager
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PracticeManagerDAO practiceManagerDAO = new JDBCParcticeManagerDAO(connection);
			
			//start coding
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "save";
	}
	
	
	public String edit() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PracticeManagerDAO practiceManagerDAO = new JDBCParcticeManagerDAO(connection);
			
			//start coding
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "edit";
	}
	
	public String delete() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			PracticeManagerDAO practiceManagerDAO = new JDBCParcticeManagerDAO(connection);
			
			//start coding
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "delete";
	}
	
	
	public PracticeManagerForm getModel() {
		
		return practiceManagerForm;
	}

}
