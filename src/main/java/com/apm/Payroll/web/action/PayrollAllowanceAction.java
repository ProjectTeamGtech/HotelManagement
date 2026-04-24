package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Payroll.eu.bi.AllowanceDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCAllowanceDAO;
import com.apm.Payroll.eu.entity.Allowance;
import com.apm.Payroll.web.form.AllowanceForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PayrollAllowanceAction extends BaseAction implements ModelDriven<AllowanceForm>,Preparable{

	AllowanceForm allowanceForm=new AllowanceForm();

	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	
	
	public AllowanceForm getModel() {
		// TODO Auto-generated method stub
		return allowanceForm;
	}

	
	@Override
	public String execute() throws Exception {

		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			AllowanceDAO allowanceDAO=new JDBCAllowanceDAO(connection);
			ArrayList<Allowance> listallowances=allowanceDAO.getAllAllowancesList();
            allowanceForm.setListallowances(listallowances);			
			
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
		   AllowanceDAO allowanceDAO=new JDBCAllowanceDAO(connection);
		   Allowance allowance=new Allowance();
		   allowance.setAllowance_name(allowanceForm.getAllowance_name());
		   allowance.setAllowanceType(allowanceForm.getAllowanceType());
		   allowance.setValue(allowanceForm.getValue());
		   
		   int result=allowanceDAO.addAllowance(allowance);
		   
	  } catch (Exception e) {

	   e.printStackTrace();
	  }
	  finally{
		  connection.close();
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
        	String id=request.getParameter("selectedid");
        	AllowanceDAO allowanceDAO=new JDBCAllowanceDAO(connection);
        	Allowance allowance=allowanceDAO.getAllowance(id);
        
        	String data=allowance.getId()+"/"+allowance.getAllowance_name()+"/"+allowance.getAllowanceType()+"/"+allowance.getValue();
        	
        	response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);
        	
		} catch (Exception e) {

		   e.printStackTrace();
		}
        finally {
        	connection.close();
        }
       
		return null;
	}
   
   
	public String update() throws Exception {
		
		if(!verifyLogin(request)) {
			return "login";
		}
		Connection connection=null;
		
		try {
		    
			   connection=Connection_provider.getconnection();
			   AllowanceDAO allowanceDAO=new JDBCAllowanceDAO(connection);
			   Allowance allowance=new Allowance();
			   allowance.setId(allowanceForm.getId());
			   allowance.setAllowance_name(allowanceForm.getAllowance_name());
			   allowance.setAllowanceType(allowanceForm.getAllowanceType());
			   allowance.setValue(allowanceForm.getValue());
			   
			   int result=allowanceDAO.updateAllowance(allowance);
			   
			 
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
