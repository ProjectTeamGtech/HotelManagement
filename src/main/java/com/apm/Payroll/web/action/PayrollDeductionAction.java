package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Payroll.eu.bi.DeductionDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCDeductionDAO;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.web.form.PayrollForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PayrollDeductionAction extends BaseAction implements
		ModelDriven<PayrollForm>, Preparable {

	PayrollForm payrollForm = new PayrollForm();

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);

	public PayrollForm getModel() {
		// TODO Auto-generated method stub
		return payrollForm;
	}

	@Override
	public String execute() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			DeductionDAO deductionDAO = new JDBCDeductionDAO(connection);
			ArrayList<Payroll> deductionList = deductionDAO.getDeductionList();
			payrollForm.setDeductionList(deductionList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return super.execute();
	}

	public String add() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;
		try {

			connection=Connection_provider.getconnection();
			DeductionDAO deductionDAO=new JDBCDeductionDAO(connection);
			Payroll payroll=new Payroll();
			payroll.setDeduction(payrollForm.getDeduction());
			payroll.setDeduction_type(payrollForm.getDeduction_type());
			payroll.setEmp_contribution(payrollForm.getEmp_contribution());
            payroll.setComp_contribution(payrollForm.getComp_contribution());
			int res=deductionDAO.addDeduction(payroll);
			
			
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
	   	  DeductionDAO deductionDAO=new JDBCDeductionDAO(connection);
	   	  String id=request.getParameter("selectedid");
          Payroll payroll=deductionDAO.getDeduction(id);
	   	  
		  String data=payroll.getId()+"/"+payroll.getDeduction()+"/"+payroll.getDeduction_type()+"/"+payroll.getEmp_contribution(); 
          
		  response.setContentType("text/html");
		  response.setHeader("Cache-Control", "no-cache");
		  response.getWriter().write(data);
			
          
	  } catch (Exception e) {

	   e.printStackTrace();
	  }
	  finally {
		  connection.close() ;
	  }
	 	
	  return null;	
   }
	
	
   public String update() throws Exception{
	
	   
	   if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;
		try {

			connection=Connection_provider.getconnection();
			DeductionDAO deductionDAO=new JDBCDeductionDAO(connection);

			Payroll payroll=new Payroll();
			payroll.setId(payrollForm.getId());
			payroll.setDeduction(payrollForm.getDeduction());
			payroll.setDeduction_type(payrollForm.getDeduction_type());
			payroll.setEmp_contribution(payrollForm.getEmp_contribution());
           payroll.setComp_contribution(payrollForm.getComp_contribution());
			int res=deductionDAO.updateDeduction(payroll);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			connection.close();
		}

		return "save";   
  
   }
	
	
	
	

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

}
