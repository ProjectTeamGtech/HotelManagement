package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Payroll.eu.bi.BranchDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCBranchDAO;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.web.form.PayrollForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BranchAction extends BaseAction implements ModelDriven<PayrollForm>,Preparable{

	
	PayrollForm payrollForm=new PayrollForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	
	
	public PayrollForm getModel() {
		// TODO Auto-generated method stub
		return payrollForm;
	}

	
	public String add() throws Exception {
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		try {
			
			 connection=Connection_provider.getconnection();
			 BranchDAO branchDAO=new JDBCBranchDAO(connection);
			 Payroll payroll=new Payroll();
			 payroll.setBranch(payrollForm.getBranch());
			 payroll.setAddress(payrollForm.getAddress());
			 payroll.setCity(payrollForm.getCity());
			 payroll.setState(payrollForm.getState());
			 payroll.setCountry(payrollForm.getCountry());
			 payroll.setPhone1(payrollForm.getPhone1());
			 payroll.setPhone2(payrollForm.getPhone2());
			 payroll.setEmail(payrollForm.getEmail());
			 int i=branchDAO.addBranch(payroll); 
			 
			 
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return SUCCESS;
	}
	
	
	public String edit() throws Exception{
		
		if(!verifyLogin(request)) {
			return "login";
		}
		Connection connection=null;
		try {
			
			 String id=request.getParameter("selectedid");
			 connection=Connection_provider.getconnection();
			 BranchDAO branchDAO=new JDBCBranchDAO(connection);
			 Payroll payroll=branchDAO.getBranch(id);
			 
			 String str=payroll.getId()+"/"+payroll.getBranch()+"/"+payroll.getAddress()+"/"+payroll.getCity()+"/"+payroll.getState()+"/"+payroll.getCountry()+"/"+payroll.getPhone1()+"/"+payroll.getPhone2()+"/"+payroll.getEmail();
			 
			 
			 response.setContentType("text/html");
		     response.setHeader("Cache-Control", "no-cache");
			 response.getWriter().write(str);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	public String update() throws Exception{
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			BranchDAO branchDAO=new JDBCBranchDAO(connection);
			Payroll payroll=new Payroll();
			payroll.setId(payrollForm.getId());
			payroll.setBranch(payrollForm.getBranch());
			payroll.setAddress(payrollForm.getAddress());
			payroll.setCity(payrollForm.getCity());
			payroll.setState(payrollForm.getState());
			payroll.setCountry(payrollForm.getCountry());
			payroll.setPhone1(payrollForm.getPhone1());
			payroll.setPhone2(payrollForm.getPhone2());
			payroll.setEmail(payrollForm.getEmail());
			int res=branchDAO.updateBranch(payroll);
			
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
	    finally {
	    	connection.close();
	    }
		
		
		return SUCCESS;
	}
	
	
	
	
	
	
	@Override
	public String execute() throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			BranchDAO branchDAO=new JDBCBranchDAO(connection);
			
	        ArrayList<Payroll> branchesList=branchDAO.getAllBranchList();		
			payrollForm.setBranchesList(branchesList);
	        
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
			
		}
	
		return "branch";
	}
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
