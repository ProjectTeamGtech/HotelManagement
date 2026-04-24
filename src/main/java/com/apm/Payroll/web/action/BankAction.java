package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Payroll.eu.bi.BankDAO;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.bi.PayrollMasterDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCBankDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollEmployeeDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollMasterDAO;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.web.form.PayrollForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BankAction extends BaseAction implements ModelDriven<PayrollForm>,Preparable{

	PayrollForm payrollForm=new PayrollForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request); 
	
	public PayrollForm getModel() {


		return payrollForm;
	}

	@Override
	public String execute() throws Exception {
 
		 if(!verifyLogin(request)){ 
			 return "login";
		 }
		
		 Connection connection=null;
		 
		 try {
			
			 connection=Connection_provider.getconnection();
			 BankDAO bankDAO=new JDBCBankDAO(connection);
			
			 ArrayList<Payroll> banklist=bankDAO.getAllBankList();
			 payrollForm.setBanklist(banklist);
		
			 
			 PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
			 ArrayList<Employee> employeelist=payrollEmployeeDAO.getAllEmployees(null,null,null,null,loginInfo);
		     payrollForm.setEmployeelist(employeelist);
			 
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return super.execute();
	}
	
	
	public String add() throws Exception {
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			BankDAO bankDAO=new JDBCBankDAO(connection);
            Payroll payroll=new Payroll();
            payroll.setEmp_id(payrollForm.getName());
            payroll.setBank(payrollForm.getBank());
            payroll.setBankaddress(payrollForm.getBankaddress());
            payroll.setBank_account(payrollForm.getBank_account());
            payroll.setIfsccode(payrollForm.getIfsccode());
            
            int result=bankDAO.addBank(payroll);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			
		}
		
		return "save";
	}
	
	public String edit() throws Exception {
		
		if(!verifyLogin(request)) {
			
			return "login";
		}
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			BankDAO bankDAO=new JDBCBankDAO(connection);
			String id=request.getParameter("selectedid");
			Payroll payroll=bankDAO.getBankData(id);
			
			String data=payroll.getId()+"/"+payroll.getBank()+"/"+payroll.getBank_account()+"/"+payroll.getIfsccode()+"/"+payroll.getEmp_id();
			
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
			BankDAO bankDAO=new JDBCBankDAO(connection);
			
			Payroll payroll=new Payroll();
			payroll.setId(payrollForm.getId());
            payroll.setBank(payrollForm.getBank());
            payroll.setBank_account(payrollForm.getBank_account());
            payroll.setIfsccode(payrollForm.getIfsccode());
            payroll.setEmp_id(payrollForm.getName());
            payroll.setBankaddress(payrollForm.getBankaddress());
            int result=bankDAO.updateBank(payroll);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "save";
	}
	
	

	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
