package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Payroll.eu.bi.PayrollTaxDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollTaxDAO;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.web.form.PayrollForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PayrollTaxAction extends BaseAction implements ModelDriven<PayrollForm>,Preparable{

	PayrollForm payrollForm=new PayrollForm();
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	
	public PayrollForm getModel() {
		// TODO Auto-generated method stub
		return payrollForm;
	}


	
	@Override
	public String execute() throws Exception {

		if(!verifyLogin(request)) {
			
			return "login";
		}
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			PayrollTaxDAO payrollTaxDAO=new JDBCPayrollTaxDAO(connection);
			ArrayList<Payroll> taxlist=payrollTaxDAO.getAllTaxlist();
			
			payrollForm.setTaxlist(taxlist);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return super.execute();
	}
	
	
	public String add() throws Exception{
		
		if(!verifyLogin(request)) {
		
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			PayrollTaxDAO payrollTaxDAO=new JDBCPayrollTaxDAO(connection);
			Payroll payroll=new Payroll();
			payroll.setFromamount(payrollForm.getFromamount());
			payroll.setToamount(payrollForm.getToamount());
			payroll.setGender(payrollForm.getGender());
			payroll.setTax(payrollForm.getTax());
			
			int result=payrollTaxDAO.addTax(payroll);
			
	
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	
		return "save";
	}
	
	public String edit() throws Exception{
		
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PayrollTaxDAO payrollTaxDAO=new JDBCPayrollTaxDAO(connection);
			
		    String id=request.getParameter("selectedid"); 	
			Payroll payroll=payrollTaxDAO.getTax(id);
		    
		    String data=payroll.getId()+"/"+payroll.getFromamount()+"/"+payroll.getToamount()+"/"+payroll.getGender()+"/"+payroll.getTax();
		   
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
	
	
   public String update() throws Exception{
		
		if(!verifyLogin(request)) {
		
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			PayrollTaxDAO payrollTaxDAO=new JDBCPayrollTaxDAO(connection);
			Payroll payroll=new Payroll();
			payroll.setId(payrollForm.getId());
			payroll.setFromamount(payrollForm.getFromamount());
			payroll.setToamount(payrollForm.getToamount());
			payroll.setGender(payrollForm.getGender());
			payroll.setTax(payrollForm.getTax());
			
			int result=payrollTaxDAO.updateTax(payroll);
			
	
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	
		return "save";
	}
	
	
	
	
	
     public void prepare() throws Exception {
		
	 }

	
	
	
}
