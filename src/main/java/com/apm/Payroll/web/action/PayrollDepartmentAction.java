package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Payroll.eu.bi.PayrollDepartmentDAO;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollDepartment;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollEmployeeDAO;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.web.form.PayrollForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PayrollDepartmentAction extends BaseAction implements Preparable, ModelDriven<PayrollForm> {

	PayrollForm payrollForm = new PayrollForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);

	@Override
	public String execute() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			ArrayList<Payroll> departmentList = payrollDepartmentDAO.getAllDepartmentList();
			payrollForm.setDepartmentList(departmentList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return super.execute();
	}

	public String add() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			Payroll payroll = new Payroll();
			payroll.setDept_name(payrollForm.getDept_name());
			payroll.setEmail(DateTimeUtils.isNull(payrollForm.getEmail()));
			payroll.setOvertime_status(payrollForm.getOvertime_status());
			payroll.setRate(payrollForm.getRate());
			int result = payrollDepartmentDAO.addDepartment(payroll);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return "save";
	}

	public String edit() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();

			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			String id = request.getParameter("selectedid");
			Payroll payroll = payrollDepartmentDAO.getDepartment(id);

			String data = payroll.getId() + "/" + payroll.getDept_name() + "/" + payroll.getOvertime_status() + "/"
					+ payroll.getRate()+ "/"+payroll.getEmail();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			connection.close();
		}

		return null;
	}

	public String update() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO departmentDAO = new JDBCPayrollDepartment(connection);
			Payroll payroll = new Payroll();
			payroll.setId(payrollForm.getId());
			payroll.setDept_name(payrollForm.getDept_name());
			payroll.setOvertime_status(payrollForm.getOvertime_status());
			payroll.setRate(payrollForm.getRate());
			payroll.setEmail(DateTimeUtils.isNull(payrollForm.getEmail()));
			int res = departmentDAO.updateDepartment(payroll);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "save";
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public PayrollForm getModel() {
		// TODO Auto-generated method stub
		return payrollForm;
	}

	public String showdesignation() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Employee> departmentlist = payrollEmployeeDAO.getAllDepartments();
			payrollForm.setDeptlist(departmentlist);
			ArrayList<Payroll> designationList = payrollDepartmentDAO.getAllDesignation();
			payrollForm.setDesignationList(designationList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return "showdesignation";
	}

	public String adddesignation() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			Payroll payroll = new Payroll();
			payroll.setDept_name(payrollForm.getDept_name());
			payroll.setName(payrollForm.getName());
			int result = payrollDepartmentDAO.addDesignation(payroll);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return "savedesignation";
	}

	public String editdesigntn() {
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();

			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			String id = request.getParameter("selectedid");
			Payroll payroll = payrollDepartmentDAO.getDesignation(id);

			String data = payroll.getId() + "/" + payroll.getName()+ "/"+payroll.getDept_name();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);

		} catch (Exception e) {
			e.printStackTrace();

		} 

		return null;
	}
	public String updatedesigntn() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO departmentDAO = new JDBCPayrollDepartment(connection);
			Payroll payroll = new Payroll();
			payroll.setId(payrollForm.getId());
			payroll.setDept_name(payrollForm.getDept_name());
			payroll.setName(payrollForm.getName());
			int res = departmentDAO.updateDesignation(payroll);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "updatedesignation";
	}
	public String deletedeparment() {
		
	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();
		PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
		String id=request.getParameter("id");
		int result = payrollDepartmentDAO.deleteDepartment(id);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("0");

	} catch (Exception e) {

		e.printStackTrace();
	}
	return null; 
	}
	
	public String deletedesigntn() {
		
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			String id=request.getParameter("id");
			int result = payrollDepartmentDAO.deleteDesignation(id);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("0");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null; 
		}
	
	
	public String showqualification() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			ArrayList<Payroll> qualificationlist = payrollDepartmentDAO.getAllQualification();
			payrollForm.setQualificationlist(qualificationlist);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return "showqualification";
	}
	
	
	public String addqualification() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			Payroll payroll = new Payroll();
			payroll.setName(payrollForm.getName());
			int result = payrollDepartmentDAO.addQualification(payroll);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return "savequalification";
	}
public String editqualification() throws Exception{
	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();

		PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
		String id = request.getParameter("selectedid");
		Payroll payroll = payrollDepartmentDAO.getQualifications(id);

		String data = id + "/" + payroll.getName();
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(data);

	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		connection.close();
	}

	return null;
}
public String updatequalification() throws Exception {

	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();
		PayrollDepartmentDAO departmentDAO = new JDBCPayrollDepartment(connection);
		Payroll payroll = new Payroll();
		payroll.setId(payrollForm.getId());
		payroll.setName(payrollForm.getName());

		int res = departmentDAO.updateQualification(payroll);

	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}

	return "savequalification";
}

public String deletequalification() {
	
	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();
		PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
		String id=request.getParameter("id");
		int result = payrollDepartmentDAO.deleteQualification(id);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("0");

	} catch (Exception e) {

		e.printStackTrace();
	}
	return null; 
	}
	}
