package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Payroll.eu.bi.AttendanceDAO;
import com.apm.Payroll.eu.bi.PayrollDepartmentDAO;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.bi.PayrollMasterDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCAttendanceDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollDepartment;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollEmployeeDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollMasterDAO;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.Payroll.web.form.PayrollForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sun.mail.handlers.message_rfc822;

public class PayrollMasterAction extends BaseAction implements ModelDriven<PayrollForm>, Preparable {

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	PayrollForm payrollForm = new PayrollForm();

	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession(false);
	Pagination pagination = new Pagination(25, 1);

	public PayrollForm getModel() {
		// TODO Auto-generated method stub
		return payrollForm;
	}

	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public String loan() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);

			ArrayList<Payroll> loanlist = payrollMasterDAO.getAllLoanlist("");

			ArrayList<Employee> employeelist = payrollEmployeeDAO.getAllEmployees(null, null, null, null,loginInfo);
			payrollForm.setEmployeelist(employeelist);
			payrollForm.setLoanlist(loanlist);

			ArrayList<Employee> branchlist = payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return "loan";
	}

	public String addloan() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			Payroll payroll = new Payroll();
			payroll.setName(payrollForm.getName());
			payroll.setBranch(payrollForm.getBranch());
			payroll.setAmount(payrollForm.getAmount());
			payroll.setDate_format(payrollForm.getDate_format());
			payroll.setInstallments(payrollForm.getInstallments());
			payroll.setDeduction(payrollForm.getDeduction());

			int result = payrollMasterDAO.addLoan(payroll);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return loan();
	}

	public String editloan() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			String id = request.getParameter("selectedid");

			Payroll payroll = payrollMasterDAO.getLoan(id);

			String data = payroll.getId() + " " + payroll.getName() + " " + payroll.getBranch() + " "
					+ payroll.getAmount() + " " + payroll.getDate_format() + " " + payroll.getInstallments() + " "
					+ payroll.getDeduction();

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public String updateloan() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			Payroll payroll = new Payroll();
			payroll.setName(payrollForm.getName());
			payroll.setBranch(payrollForm.getBranch());
			payroll.setAmount(payrollForm.getAmount());
			payroll.setDate_format(payrollForm.getDate_format());
			payroll.setInstallments(payrollForm.getInstallments());
			payroll.setDeduction(payrollForm.getDeduction());
			payroll.setId(payrollForm.getId());

			int res = payrollMasterDAO.updateLoan(payroll);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			connection.close();
		}

		return loan();
	}

	public String holidays() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Employee> branchlist = payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);
			ArrayList<Payroll> holidayList = payrollMasterDAO.holidayReport();
			payrollForm.setHolidayList(holidayList);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "holiday";
	}

	public String leave() throws Exception {

		if (!verifyLogin(request)) {
			return "login";

		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);

			String branchid = payrollForm.getBranch();
			String userid = loginInfo.getUserId();
			ArrayList<Payroll> leaveList = payrollMasterDAO.getleaveList(branchid, pagination, null, "1", userid,
					loginInfo, "", "", "", "", "");

			payrollForm.setLeaveList(leaveList);

			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);

			ArrayList<Employee> employeelist = payrollEmployeeDAO.getAllEmployees(null, null, null, null,loginInfo);
			payrollForm.setEmployeelist(employeelist);

			ArrayList<Employee> branchlist = payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);

			if (branchid != null && branchid != "") {
				payrollForm.setBranch(branchid);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "leave";
	}

	/*
	 * public String addleave() throws Exception{
	 * 
	 * if(!verifyLogin(request)){
	 * 
	 * return "login"; } Connection connection=null; try {
	 * 
	 * connection=Connection_provider.getconnection(); PayrollMasterDAO
	 * payrollMasterDAO=new JDBCPayrollMasterDAO(connection); Payroll
	 * payroll=new Payroll(); payroll.setBranch(payrollForm.getBranch());
	 * payroll.setName(payrollForm.getName());
	 * payroll.setShort_name(payrollForm.getShort_name());
	 * payroll.setLeave_type(payrollForm.getLeave_type());
	 * payroll.setNo_days(payrollForm.getNo_days());
	 * payroll.setEncashable(payrollForm.getEncashable());
	 * payroll.setCarryover(payrollForm.getCarryover());
	 * 
	 * 
	 * int result=payrollMasterDAO.addLeave(payroll);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); } finally { connection.close(); }
	 * 
	 * 
	 * return leave(); }
	 */

	public String editleave() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			String id = request.getParameter("selectedid");
			Payroll payroll = payrollMasterDAO.getLeave(id);

			String data = payroll.getShort_name() + "/" + payroll.getLeave_type() + "/" + payroll.getNo_days() + "/"
					+ payroll.getEncashable() + "/" + payroll.getCarryover() + "/" + payroll.getEmp_id() + "/"
					+ payroll.getBranch() + "/" + payroll.getId();

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

	public String updateleave() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);

			Payroll payroll = new Payroll();
			String id=request.getParameter("upleaveid");
			payroll.setId(payrollForm.getId());
			payroll.setName(payrollForm.getName());
			payroll.setFromdate(payrollForm.getFromdate());
			payroll.setTodate(payrollForm.getTodate());
			payroll.setLeave_type(payrollForm.getLeave_type());
			payroll.setDays(payrollForm.getDays());
			payroll.setLeave_reason(payrollForm.getLeave_reason());
			
			int result = payrollMasterDAO.updateLeave(payroll);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return leave();
	}

	public String savemonthsalary() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			Salary salary = new Salary();
			PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
			String status = request.getParameter("status");
			salary.setSalaryId(request.getParameter("salaryId"));
			String months[] = { "Jan", "Feb", "March", "April", "May", "June", "July", "August", "September", "October",
					"November", "December" };
			String month = months[Integer.parseInt(request.getParameter("month"))];
			String workDays = request.getParameter("days");
			String netpay = request.getParameter("netpay");
			String year = request.getParameter("year");
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			
			salary.setEmp_id(payrollForm.getEmp_id());

			salary.setYear(year);
			salary.setNetpay(netpay);
			salary.setMonth(month);
			salary.setWorkeddays(workDays);
			boolean checkExist = payrollMasterDAO.checkExistRecord(salary);
			if (!checkExist) {
				int result = payrollMasterDAO.finalizeSalaryMonth(salary, 1);
				Salary salary2 = payrollMasterDAO.getSalaryDetails(salary.getEmp_id());
				double allownces = Double.parseDouble(salary2.getMedical_allowance())
						+ Double.parseDouble(salary2.getDa_on_ta()) + Double.parseDouble(salary2.getSpecial_pay())
						+ Double.parseDouble(salary2.getPersonal_pay())
						+ Double.parseDouble(salary2.getTransport_allowance()) + Double.parseDouble(salary2.getHra())
						+ Double.parseDouble(salary2.getDa()) + Double.parseDouble(salary2.getNpa())
						+ Double.parseDouble(salary2.getConveyance()) + Double.parseDouble(salary2.getWashing())
						+ Double.parseDouble(salary2.getPerdevallow()) + Double.parseDouble(salary2.getVehiclepass());
				salary2.setAllowances(String.valueOf(allownces));
				double deduction = Double.parseDouble(salary2.getEmp_pf()) + Double.parseDouble(salary2.getEmp_esi())
						+ Double.parseDouble(salary2.getOther_deduction())
						+ Double.parseDouble(salary2.getPrefessional_tax()) + Double.parseDouble(salary2.getTds());
				salary2.setDeductions(String.valueOf(deduction));
				double grosspay = Double.parseDouble(salary2.getBasic()) + allownces;
				salary2.setNetpay(String.valueOf(netpay));
				salary2.setGross_pay(String.valueOf(grosspay));
				int res = payrollMasterDAO.payrollInsertFinalSalary(salary, salary2);
			} else {
				int result = payrollMasterDAO.updateFinalStatus(salary, "processed", "apm_payroll_final_salary");
				int result1 = payrollMasterDAO.updateFinalStatus(salary, "processed", "apm_payroll_salary_monthly");
			}
			// }else{
			// if(!checkExist){
			// int result= payrollMasterDAO.finalizeSalaryMonth(salary,0);
			// Salary
			// salary2=payrollMasterDAO.getSalaryDetails(salary.getEmp_id());
			// int
			// res=payrollMasterDAO.payrollInsertFinalSalary(salary,salary2);
			// }else{
			// int res=payrollMasterDAO.updateSalaryMonth(salary);
			// }
			// }

			session.setAttribute("mymonth", month);
			session.setAttribute("emp_id", salary.getEmp_id());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirectsalary";
	}

	public String salary() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			String branchid = payrollForm.getBranch();
			String newmonth = "";
			String year = "";
			String department = payrollForm.getDepartment();
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
			String month = (String) session.getAttribute("filtermonth");
			if (month == null) {
				month = payrollForm.getMonth();
			} else {
				session.removeAttribute("filtermonth");
			}

			String year1 = (String) session.getAttribute("filteryear");
			if (year1 == null) {
				year1 = payrollForm.getYear();
			} else {
				session.removeAttribute("filteryear");
			}
			String status = (String) session.getAttribute("filterstatus");
			if (status == null) {
				status = payrollForm.getStatus();
			} else {

				session.removeAttribute("filterstatus");
			}
			// ArrayList<Salary> salaryList=payrollMasterDAO.getAllSalaryList();
			Date date = calendar.getTime();
			if (year1 == null) {
				year = sdf1.format(date);
			} else {
				year = year1;
			}
			if (month == null) {
				month = sdf2.format(date);
				newmonth = String.valueOf(Integer.parseInt(month) - 1);
			} else {
				newmonth = String.valueOf(Integer.parseInt(month));

			}
			if (newmonth.equals("0")) {
				month = "January" + "-" + year;
			} else if (newmonth.equals("1")) {
				month = "February" + "-" + year;
			} else if (newmonth.equals("2")) {
				month = "March" + "-" + year;
			} else if (newmonth.equals("3")) {
				month = "April" + "-" + year;
			} else if (newmonth.equals("4")) {
				month = "May" + "-" + year;
			} else if (newmonth.equals("5")) {
				month = "June" + "-" + year;
			} else if (newmonth.equals("6")) {
				month = "July" + "-" + year;
			} else if (newmonth.equals("7")) {
				month = "August" + "-" + year;
			} else if (newmonth.equals("8")) {
				month = "September" + "-" + year;
			} else if (newmonth.equals("9")) {
				month = "October" + "-" + year;
			} else if (newmonth.equals("10")) {
				month = "November" + "-" + year;
			} else if (newmonth.equals("11")) {
				month = "December" + "-" + year;
			}

			Date date1 = Calendar.getInstance(Locale.getDefault()).getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
			/* String month1=dateFormat.format(date1); */
			int count = payrollMasterDAO.manageSalaryDetailscount(month, branchid, status, pagination);
			pagination.setPreperties(count);
			ArrayList<Salary> salaryList = payrollMasterDAO.manageSalaryDetails(month, branchid, status, pagination,loginInfo,department,newmonth);
			for (Salary salary : salaryList) {
				if (salary.getBasic() != null) {
					if (salary.getStatus().equals("0")) {
						salary.setSelectedstatus("WIP");
					} else if (salary.getStatus().equals("1")) {
						salary.setSelectedstatus("Processed");
					} else {
						salary.setSelectedstatus("Paid");
					}
				}
			}

			payrollForm.setTotalRecords(count);
			pagination.setTotal_records(salaryList.size());
			payrollForm.setTotalRecords(count);
			payrollForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			if(status==null){
				status="0";
			}
			payrollForm.setStatus(status);
			payrollForm.setSalaryList(salaryList);
			payrollForm.setMonth(newmonth);
			payrollForm.setSelectedyear(year);
			// payrollForm.setYear(year);

			payrollForm.setSelectedmonth(newmonth);
			Date date2 = Calendar.getInstance(Locale.getDefault()).getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date2);

			payrollForm.setDate(today);

			ArrayList<Employee> branchlist = payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);

			if (branchid != null && branchid != "") {
				payrollForm.setBranch(branchid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "salary";
	}

	public String salary1()throws Exception{


		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			String branchid = payrollForm.getBranch();
			String newmonth = "";
			String year = "";
			String department = payrollForm.getDepartment();
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
			String month = (String) session.getAttribute("filtermonth");
			if (month == null) {
				month = payrollForm.getMonth();
			} else {
				session.removeAttribute("filtermonth");
			}

			String year1 = (String) session.getAttribute("filteryear");
			if (year1 == null) {
				year1 = payrollForm.getYear();
			} else {
				session.removeAttribute("filteryear");
			}
			String status = (String) session.getAttribute("filterstatus");
			if (status == null) {
				status = payrollForm.getStatus();
			} else {

				session.removeAttribute("filterstatus");
			}
			// ArrayList<Salary> salaryList=payrollMasterDAO.getAllSalaryList();
			Date date = calendar.getTime();
			if (year1 == null) {
				year = sdf1.format(date);
			} else {
				year = year1;
			}
			if (month == null) {
				month = sdf2.format(date);
				newmonth = String.valueOf(Integer.parseInt(month) - 1);
			} else {
				newmonth = String.valueOf(Integer.parseInt(month));

			}
			if (newmonth.equals("0")) {
				month = "Jan" + "-" + year;
			} else if (newmonth.equals("1")) {
				month = "Feb" + "-" + year;
			} else if (newmonth.equals("2")) {
				month = "March" + "-" + year;
			} else if (newmonth.equals("3")) {
				month = "April" + "-" + year;
			} else if (newmonth.equals("4")) {
				month = "May" + "-" + year;
			} else if (newmonth.equals("5")) {
				month = "June" + "-" + year;
			} else if (newmonth.equals("6")) {
				month = "July" + "-" + year;
			} else if (newmonth.equals("7")) {
				month = "August" + "-" + year;
			} else if (newmonth.equals("8")) {
				month = "September" + "-" + year;
			} else if (newmonth.equals("9")) {
				month = "October" + "-" + year;
			} else if (newmonth.equals("10")) {
				month = "November" + "-" + year;
			} else if (newmonth.equals("11")) {
				month = "December" + "-" + year;
			}

			   String sal_date=attendanceDAO.getSalaryDatebyid();
			  String  sal_date1=sal_date+"/"+newmonth+"/"+year;
			   sal_date=sal_date1+","+"00:00:00"+" "+"PM";
			   String new_sal_date=DateTimeUtils.calnewdate(sal_date, 30);
			   
			Date date1 = Calendar.getInstance(Locale.getDefault()).getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
			/* String month1=dateFormat.format(date1); */
			int count = payrollMasterDAO.manageSalaryDetailscount(month, branchid, status, pagination);
			pagination.setPreperties(count);
			ArrayList<Salary> salaryList = payrollMasterDAO.manageSalaryDetails1(month, branchid, status, pagination,loginInfo,department,newmonth,sal_date1,new_sal_date);
			for (Salary salary : salaryList) {
				if (salary.getBasic() != null) {
					if (salary.getStatus().equals("0")) {
						salary.setSelectedstatus("WIP");
					} else if (salary.getStatus().equals("1")) {
						salary.setSelectedstatus("Processed");
					} else {
						salary.setSelectedstatus("Paid");
					}
				}
			}

			payrollForm.setTotalRecords(count);
			pagination.setTotal_records(salaryList.size());
			payrollForm.setTotalRecords(count);
			payrollForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			if(status==null){
				status="0";
			}
			payrollForm.setStatus(status);
			payrollForm.setSalaryList(salaryList);
			payrollForm.setMonth(newmonth);
			payrollForm.setSelectedyear(year);
			// payrollForm.setYear(year);

			payrollForm.setSelectedmonth(newmonth);
			Date date2 = Calendar.getInstance(Locale.getDefault()).getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date2);

			payrollForm.setDate(today);

			ArrayList<Employee> branchlist = payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);

			if (branchid != null && branchid != "") {
				payrollForm.setBranch(branchid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "salary";
	
	}
	public String updateholiday() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			String selectedid = request.getParameter("selectedid");
			String date = request.getParameter("date");
			String event = request.getParameter("event");

			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			int result = payrollMasterDAO.updateHoliday(selectedid, DateTimeUtils.getCommencingDatePayroll(date), event);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return holidays();
	}

	public String addholiday() throws Exception {

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			String date = DateTimeUtils.getCommencingDatePayroll(payrollForm.getDate());
			String event = payrollForm.getEvent();

			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			int result = payrollMasterDAO.addHoliday(date, event);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return holidays();
	}

	public String listholidays() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollMasterDAO masterDAO = new JDBCPayrollMasterDAO(connection);
			String id = request.getParameter("id");
			// ArrayList<Payroll> holidayList=masterDAO.holidayReport();
			ArrayList<Payroll> holidayList = masterDAO.holidaylist(id);
			StringBuffer buffer = new StringBuffer();

			for (Payroll payroll : holidayList) {

				buffer.append(payroll.getId() + "~" + DateTimeUtils.getCommencingDatePayroll(payroll.getDate()) + "~" + payroll.getEvent());

			}

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public String finalizesalary() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			ArrayList<Employee> branchlist = payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);
			int monthindex =0;
			String status = payrollForm.getStatus();
			Date date = Calendar.getInstance(Locale.getDefault()).getTime();
			SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
			String dates[] = format.format(date).split("-");
			String paysalslip = request.getParameter("paysalslip");
			for (Salary salary : payrollForm.getCollectionsal()) {
				if (payrollForm.getSelectedmonth() == null) {
					salary.setMonth(dates[0]);
				} else {
				 monthindex = Integer.parseInt(payrollForm.getSelectedmonth());
					String monThArr[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
							"October", "November", "December" };

					String month = monThArr[monthindex];
					salary.setMonth(month);
				}
				Salary salary2 = new Salary();
				if (payrollForm.getYear() == null) {
					salary.setYear(dates[1]);
				} else if (payrollForm.getYear().equals("")) {
					salary.setYear(dates[1]);
				} else {
					salary.setYear(payrollForm.getYear());
				}
				if (payrollForm.getLeave() == null) {
					salary.setLeave("0");
				} else if (payrollForm.getLeave().equals("")) {
					salary.setLeave("0");
				} else {
					salary.setLeave(payrollForm.getLeave());
				}
				int idays=1;
				double leaveamt=0;
				double leavedaysamt=0;
				String weekoff=payrollMasterDAO.getweekdays();
				int sunday=0;
				if(weekoff==null){
					weekoff="0";
				}
				if(weekoff.equals("")){
					weekoff="0";
				}
				if(weekoff!=null){
				String temp1[]=weekoff.split(",");
				sunday=getSundays(Integer.parseInt(salary.getYear()),  monthindex,temp1);
				}
				Calendar mycal1 = new GregorianCalendar(Integer.parseInt(salary.getYear()), monthindex, idays);
				int daysInMonth = mycal1.getActualMaximum(Calendar.DAY_OF_MONTH);
				int actualworkingdays=daysInMonth-sunday;
				salary2 = payrollMasterDAO.getSalaryDetails(salary.getEmp_id());
				if (salary.isIselect()) {
					if (!paysalslip.equals("1")) {
						boolean checkExist = payrollMasterDAO.checkExistRecord(salary);
						if (!checkExist) {

							int result = payrollMasterDAO.finalizeSalaryMonth(salary, 0);

							
							double allownces = Double.parseDouble(salary2.getMedical_allowance())
									+ Double.parseDouble(salary2.getDa_on_ta())
									+ Double.parseDouble(salary2.getSpecial_pay())
									+ Double.parseDouble(salary2.getPersonal_pay())
									+ Double.parseDouble(salary2.getTransport_allowance())
									+ Double.parseDouble(salary2.getHra()) + Double.parseDouble(salary2.getDa())
									+ Double.parseDouble(salary2.getNpa()) + Double.parseDouble(salary2.getConveyance())
									+ Double.parseDouble(salary2.getWashing())
									+ Double.parseDouble(salary2.getPerdevallow())
									+ Double.parseDouble(salary2.getVehiclepass());
							salary2.setAllowances(String.valueOf(allownces));
							double deduction = Double.parseDouble(salary2.getEmp_pf())
									+ Double.parseDouble(salary2.getEmp_esi())
									+ Double.parseDouble(salary2.getOther_deduction())
									+ Double.parseDouble(salary2.getPrefessional_tax())
									+ Double.parseDouble(salary2.getTds());
							salary2.setDeductions(String.valueOf(deduction));
							double grosspay = Double.parseDouble(salary2.getBasic()) + allownces;
							Employee employee=payrollEmployeeDAO.getAllDetailsEmployee(salary.getEmp_id());
							double netpay = grosspay - deduction;
							double perdayamt = netpay / actualworkingdays;
							double onehouramt=netpay/(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()));
							perdayamt=Math.round(perdayamt*100.0)/100.0;
							double workingdays = payrollMasterDAO.getWorkingDays(salary.getMonth()+"-"+salary.getYear(),salary.getEmp_id());
							double workingmin=payrollMasterDAO.getWorkingMinutes(salary.getMonth()+"-"+salary.getYear(),salary.getEmp_id());
//							int leavedays = payrollMasterDAO.getLeaveDays(salary.getMonth()+"-"+salary.getYear(),salary.getEmp_id());
//							netpay=workingdays*onehouramt;
							
	                        //double leave=(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()))-workingdays;
							
							//netpay=netpay-leaveamt;
							double leave=(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()))-(workingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()));
							//leaveamt=leave*onehouramt;
							netpay=(workingmin/60)*onehouramt;
							//netpay=netpay-leaveamt;
							String mon="";
							monthindex=monthindex+1;
							if(monthindex !=10 && monthindex !=11 && monthindex !=12 ) {
								 mon=0+""+Integer.toString(monthindex);
							}
							String mont_yr=salary.getYear()+"/"+mon;
							
							
							  String sal_date=attendanceDAO.getSalaryDatebyid();
							  String  sal_date1=sal_date+"/"+monthindex+"/"+salary.getYear();
							   sal_date=sal_date1+","+"00:00:00"+" "+"PM";
							   String new_sal_date=DateTimeUtils.calnewdate(sal_date, 30);
							
							
							double leave1=payrollEmployeeDAO.getPayroll_leave(salary.getEmp_id(),mont_yr);
							double otamount=0;
							double leavamt1=0;
							double actualworkinghour=(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()));
							if(employee.getOtallow().equals("1")){
								if(workingdays>actualworkinghour){
									otamount=(workingdays-actualworkinghour)*onehouramt;
									grosspay=grosspay+otamount;
									netpay=netpay+otamount;
								}
							}
							if(leave>0){
								leaveamt=leave*onehouramt;
							}
							
							if(leave1>0) {
								leavamt1=leave1*perdayamt;
								netpay=netpay+leavamt1;
							}
//							leave=leave-leavedays;
							/*if(leave!=0){
								leaveamt=leave*perdayamt;
								netpay=netpay-leaveamt;
							}*/
							salary2.setNetpay(String.valueOf(Math.round(netpay*100.0)/100.0));
							salary2.setGross_pay(String.valueOf(Math.round(grosspay*100.0)/100.0));
							salary2.setPerdayamount(perdayamt);
							salary.setLeave(String.valueOf(leaveamt));
							salary.setOt(""+(Math.round(otamount*100.0)/100.0));
							int res = payrollMasterDAO.payrollInsertFinalSalary(salary, salary2);
						} else {
							payrollForm.setMessage("Employee Salary Already Processed");
							addActionMessage("Employee Salary Already Processed");
						}
					} else {
						boolean checkExist = payrollMasterDAO.checkExistRecord(salary);
						if (checkExist) {
							int up = payrollMasterDAO.updatepayrollstatus(salary);
							Salary salary3 = new Salary();
							salary3 = payrollMasterDAO.getfinalReportData(salary.getEmp_id(), salary.getMonth(),
									salary.getYear(), "2", "apm_payroll_final_salary", pagination);
							int res = payrollMasterDAO.payrollInsertFinalSalaryRpt(salary, salary3);
						}
					}
				}
			}

			ArrayList<Salary> salaryList = payrollMasterDAO.getAllSalaryList("", 0);

			payrollForm.setSalaryList(salaryList);

			Date date1 = Calendar.getInstance(Locale.getDefault()).getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date1);
			String yr = "";
			if (payrollForm.getYear() == null) {
				yr = dates[1];
			} else if (payrollForm.getYear().equals("")) {
				yr = dates[1];
			} else {
				yr = payrollForm.getYear();
			}
			payrollForm.setDate(today);
			payrollForm.setMonth(payrollForm.getSelectedmonth());
			payrollForm.setSelectedyear(yr);
			session.setAttribute("filtermonth", payrollForm.getMonth());
			session.setAttribute("filteryear", payrollForm.getSelectedyear());
			if (status == null) {
				status = "0";
			}
			if (status.equals("")) {
				status = "0";
			}
			payrollForm.setStatus(status);
			session.setAttribute("filterstatus", payrollForm.getStatus());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();

		}

		return "finalizesal";
	}

	public String salarydetails() throws Exception {
		Connection connection = null;
		try {
			String month = payrollForm.getMonth();
			String year = payrollForm.getYear();
			Date date = Calendar.getInstance(Locale.getDefault()).getTime();
			SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
			SimpleDateFormat format1 = new SimpleDateFormat("MM-yyyy");
			String todaynummonth = format1.format(date).split("-")[0];
			connection = Connection_provider.getconnection();
			String todaymonth = format.format(date).split("-")[0];
			String todayyear = format.format(date).split("-")[1];
			String nummonth = "";
			if (month == null) {
				nummonth = todaymonth;
				month = String.valueOf(Integer.parseInt(todaynummonth) - 1);
				;
			}
			if (year == null) {
				year = String.valueOf(Integer.parseInt(todayyear));
				;
			}
			int countmonth = Integer.parseInt(month) + 1;
			Calendar calendar = Calendar.getInstance();
			if (nummonth.equals("")) {
				int monthindex = Integer.parseInt(month);
				String monThArr[] = { "Jan", "Feb", "March", "April", "May", "June", "July", "August", "September",
						"October", "November", "December" };

				nummonth = monThArr[monthindex];
			}
			int year1 = Integer.parseInt(year);
			int month1 = countmonth - 1;
			int date1 = 1;
			// We have a new date of 2017-02-01
			calendar.set(year1, month1, date1);

			int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);

			ArrayList<Salary> salarydetaillist = payrollMasterDAO.getAllSalaryDetails(year, nummonth);
			payrollForm.setSalarydetaillist(salarydetaillist);
			payrollForm.setSelectedyear(year);
			payrollForm.setMonth(month);
			payrollForm.setSelectedmonth(month);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "salarydetails";

	}

	public void prepare() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Employee> branchlist = payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);
			ArrayList<Employee> departmentlist = payrollEmployeeDAO.getAllDepartments();
			payrollForm.setDepartmentlist(departmentlist);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String getallownces() {
		Connection connection = null;

		String empid = request.getParameter("empid");
		String nummonth = request.getParameter("month");
		String year = request.getParameter("year");
		int monthindex = Integer.parseInt(nummonth);
		String monThArr[] = { "Jan", "Feb", "March", "April", "May", "June", "July", "August", "September", "October",
				"November", "December" };

		String month = monThArr[monthindex];
		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			Salary salary = payrollMasterDAO.getfinalReportData(empid, month, year, "", "apm_payroll_final_salary",
					pagination);

			PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);
			String empname = employeeDAO.getEmployeeName(empid);
			StringBuffer buffer = new StringBuffer();

			buffer.append(salary.getBasic() + "~" + salary.getMedical_allowance() + "~" + salary.getHra() + "~"
					+ salary.getDa() + "~" + salary.getConveyance() + "~" + salary.getPerdevallow() + "~" + empid + "~"
					+ salary.getEmp_pf() + "~" + salary.getEmp_esi() + "~" + salary.getLeave() + "~"
					+ salary.getPrefessional_tax() + "~" + salary.getTds() + "~" + salary.getNetpay() + "~" + empname
					+ "~" + salary.getOt()+ "~" + salary.getAdvance()+ "~" + salary.getLoan());

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String updateallownces() {
		Connection connection = null;

		String empid = request.getParameter("empid");
		String nummonth = request.getParameter("month");
		String year = request.getParameter("year");
		String colname = request.getParameter("colname");
		String value = request.getParameter("value");
		int monthindex = Integer.parseInt(nummonth);
		String monThArr[] = { "Jan", "Feb", "March", "April", "May", "June", "July", "August", "September", "October",
				"November", "December" };

		String month = monThArr[monthindex];
		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);

			int updateval = payrollMasterDAO.updatecolumnallow(empid, month, year, colname, value);
			if (updateval > 0) {
				Salary salary = payrollMasterDAO.getfinalReportData(empid, month, year, "", "apm_payroll_final_salary",
						pagination);
				double allownces = Double.parseDouble(salary.getMedical_allowance())
						+ Double.parseDouble(salary.getHra()) + Double.parseDouble(salary.getDa())
						+ Double.parseDouble(salary.getConveyance()) + Double.parseDouble(salary.getPerdevallow())
						+ Double.parseDouble(salary.getOt())+ Double.parseDouble(salary.getAdvance());
				double grosspay = Double.parseDouble(salary.getBasic()) + allownces;
				double deduction = Double.parseDouble(salary.getEmp_pf()) + Double.parseDouble(salary.getEmp_esi())
						+ Double.parseDouble(salary.getOther_deduction()) + Double.parseDouble(salary.getLeave())
						+ Double.parseDouble(salary.getPrefessional_tax()) + Double.parseDouble(salary.getTds())+ Double.parseDouble(salary.getLoan());
				double netpay = grosspay - deduction;
				int updateval1 = payrollMasterDAO.updategrossallow(empid, month, year, allownces, grosspay, netpay,
						deduction);
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String getdeduction() {
		Connection connection = null;

		String empid = request.getParameter("empid");
		String nummonth = request.getParameter("month");
		String year = request.getParameter("year");
		int monthindex = Integer.parseInt(nummonth);
		String monThArr[] = { "Jan", "Feb", "March", "April", "May", "June", "July", "August", "September", "October",
				"November", "December" };

		String month = monThArr[monthindex];
		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			Salary salary = payrollMasterDAO.getfinalReportData(empid, month, year, "", "apm_payroll_final_salary",
					pagination);

			StringBuffer buffer = new StringBuffer();

			buffer.append(salary.getEmp_pf() + "~" + salary.getEmp_esi() + "~" + salary.getOther_deduction() + "~"
					+ salary.getLeave() + "~" + salary.getPrefessional_tax() + "~" + salary.getTds() + "~" + empid + "~"
					+ month + "~" + year);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String updatededuction() {
		Connection connection = null;

		String empid = request.getParameter("empid");
		String nummonth = request.getParameter("month");
		String year = request.getParameter("year");
		String colname = request.getParameter("colname");
		String value = request.getParameter("value");
		int monthindex = Integer.parseInt(nummonth);
		String monThArr[] = { "Jan", "Feb", "March", "April", "May", "June", "July", "August", "September", "October",
				"November", "December" };

		String month = monThArr[monthindex];
		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);

			int updateval = payrollMasterDAO.updatecolumnallow(empid, month, year, colname, value);
			if (updateval > 0) {
				Salary salary = payrollMasterDAO.getfinalReportData(empid, month, year, "", "apm_payroll_final_salary",
						pagination);
				double allownces = Double.parseDouble(salary.getMedical_allowance())
						+ Double.parseDouble(salary.getHra()) + Double.parseDouble(salary.getDa())
						+ Double.parseDouble(salary.getConveyance()) + Double.parseDouble(salary.getPerdevallow())
						+ Double.parseDouble(salary.getOt())+ Double.parseDouble(salary.getAdvance());
				double grosspay = Double.parseDouble(salary.getBasic()) + allownces;
				double deduction = Double.parseDouble(salary.getEmp_pf()) + Double.parseDouble(salary.getEmp_esi())
						+ Double.parseDouble(salary.getLeave()) + Double.parseDouble(salary.getPrefessional_tax())
						+ Double.parseDouble(salary.getTds())+ Double.parseDouble(salary.getLoan());
				double netpay = grosspay - deduction;
				int updateval1 = payrollMasterDAO.updategrossallow(empid, month, year, allownces, grosspay, netpay,
						deduction);
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public String editprocesssalary() throws Exception {
		try {
			String month = (String) session.getAttribute("filtermonth");
			if (month == null) {
				month = payrollForm.getMonth();
			} else {
				session.removeAttribute("filtermonth");
			}

			String year1 = (String) session.getAttribute("filteryear");
			if (year1 == null) {
				year1 = payrollForm.getYear();
			} else {
				session.removeAttribute("filteryear");
			}
			String status = (String) session.getAttribute("filterstatus");
			if (status == null) {
				status = payrollForm.getStatus();
			} else {

				session.removeAttribute("filterstatus");
			}
			session.setAttribute("filtermonth", month);
			session.setAttribute("filteryear", year1);
			session.setAttribute("filterstatus", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return "editprocesssalary";
	}
	
	
	
	public String deleteholidays() {
		
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			String id=request.getParameter("id");
			int result = payrollDepartmentDAO.deleteHoliday(id);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("0");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null; 
		}
public String showdocumentlist() throws SQLException{
	if (!verifyLogin(request)) {
		return "login";
	}

	Connection connection = null;

	try {

		connection = Connection_provider.getconnection();
		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
		ArrayList<Payroll> documentlist = payrollMasterDAO.getAlldocumentlist();
		payrollForm.setDocumentlist(documentlist);

	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();

	}

	return "showdocumentlist";
}
public String adddocumentlist() throws Exception {

	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();
		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
		Payroll payroll = new Payroll();
		payroll.setName(payrollForm.getName());
		int result = payrollMasterDAO.adddocumentlist(payroll);

	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();

	}

	return "savedocumentlist";
}
public String editdocumentlist() throws Exception{
	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();

		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
		String id = request.getParameter("selectedid");
		Payroll payroll = payrollMasterDAO.getdocumentlist(id);

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
public String updatedocumentlist() throws Exception {

	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();
		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
		Payroll payroll = new Payroll();
		payroll.setId(payrollForm.getId());
		payroll.setName(payrollForm.getName());

		int res = payrollMasterDAO.updatedocumentlist(payroll);

	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}

	return "updatedocumentlist";
}

public int getSundays(int month,int year, String[] temp1) 
{ 
int count=0; 
Calendar calendar=Calendar.getInstance(); 
calendar.set(year, month,1); 
int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 

for(int i=1;i<=days;i++) 
{ 

calendar.set(year, month, i); 
int day=calendar.get(Calendar.DAY_OF_WEEK); 
for (int j = 0; j < temp1.length; j++) {
	int  string = Integer.parseInt(temp1[j]);
	if(day==string) {
		count++ ; 
	}
}


} 
return count; 
} 

public String finalizesalary1() throws Exception {

	if (!verifyLogin(request)) {
		return "login";
	}

	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();
		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
		PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
		AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
		ArrayList<Employee> branchlist = payrollEmployeeDAO.getAllBranches();
		payrollForm.setBranchlist(branchlist);
		int monthindex =0;
		String status =request.getParameter("status");
		String emp_id=request.getParameter("empid");
		String payslip=request.getParameter("payslip");
		Date date = Calendar.getInstance(Locale.getDefault()).getTime();
		SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
		String dates[] = format.format(date).split("-");
		//String paysalslip = request.getParameter("paysalslip");
		
		String month=request.getParameter("month");
		String year=request.getParameter("year");
		
		 monthindex = Integer.parseInt(month);
			String monThArr[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
					"October", "November", "December" };

			 month = monThArr[monthindex];
			
	
		
		Salary salary=payrollMasterDAO.getfinalReportData(emp_id, month, year,status,"apm_payroll_final_salary",pagination);
		salary.setEmp_id(emp_id);
		salary.setMonth(month);
		salary.setYear(year);
		Salary salary2= new Salary();
		int idays=1;
		double leaveamt=0;
		double leavedaysamt=0;
		String weekoff=payrollMasterDAO.getweekdays();
		int sunday=0;
		if(weekoff==null){
			weekoff="0";
		}
		if(weekoff.equals("")){
			weekoff="0";
		}
		if(weekoff!=null){
		String temp1[]=weekoff.split(",");
		sunday=getSundays(Integer.parseInt(year),  monthindex,temp1);
		}
		Calendar mycal1 = new GregorianCalendar(Integer.parseInt(year), monthindex, idays);
		int daysInMonth = mycal1.getActualMaximum(Calendar.DAY_OF_MONTH);
		int actualworkingdays=daysInMonth-sunday;
		salary2 = payrollMasterDAO.getSalaryDetails(salary.getEmp_id());
		
		if (!payslip.equals("1")) {
			boolean checkExist = payrollMasterDAO.checkExistRecord(salary);
			if (!checkExist) {

				int result = payrollMasterDAO.finalizeSalaryMonth(salary, 0);

				
				double allownces = Double.parseDouble(salary2.getMedical_allowance())
						+ Double.parseDouble(salary2.getDa_on_ta())
						+ Double.parseDouble(salary2.getSpecial_pay())
						+ Double.parseDouble(salary2.getPersonal_pay())
						+ Double.parseDouble(salary2.getTransport_allowance())
						+ Double.parseDouble(salary2.getHra()) + Double.parseDouble(salary2.getDa())
						+ Double.parseDouble(salary2.getNpa()) + Double.parseDouble(salary2.getConveyance())
						+ Double.parseDouble(salary2.getWashing())
						+ Double.parseDouble(salary2.getPerdevallow())
						+ Double.parseDouble(salary2.getVehiclepass());
				salary2.setAllowances(String.valueOf(allownces));
				double deduction = Double.parseDouble(salary2.getEmp_pf())
						+ Double.parseDouble(salary2.getEmp_esi())
						+ Double.parseDouble(salary2.getOther_deduction())
						+ Double.parseDouble(salary2.getPrefessional_tax())
						+ Double.parseDouble(salary2.getTds());
				salary2.setDeductions(String.valueOf(deduction));
				double grosspay = Double.parseDouble(salary2.getBasic()) + allownces;
				Employee employee=payrollEmployeeDAO.getAllDetailsEmployee(salary.getEmp_id());
				double netpay = grosspay - deduction;
				double perdayamt = netpay / actualworkingdays;
				double onehouramt=netpay/(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()));
				perdayamt=Math.round(perdayamt*100.0)/100.0;
				double workingdays = payrollMasterDAO.getWorkingDays(salary.getMonth()+"-"+salary.getYear(),salary.getEmp_id());
				double workingmin=payrollMasterDAO.getWorkingMinutes(salary.getMonth()+"-"+salary.getYear(),salary.getEmp_id());
//				int leavedays = payrollMasterDAO.getLeaveDays(salary.getMonth()+"-"+salary.getYear(),salary.getEmp_id());
//				netpay=workingdays*onehouramt;
				
                //double leave=(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()))-workingdays;
				
				//netpay=netpay-leaveamt;
				double leave=(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()))-(workingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()));
				//leaveamt=leave*onehouramt;
				netpay=(workingmin/60)*onehouramt;
				//netpay=netpay-leaveamt;
				String mon="";
				String mont_yr="";
				monthindex=monthindex+1;
				if(monthindex !=10 && monthindex !=11 && monthindex !=12 ) {
					 mon=0+""+Integer.toString(monthindex);
					 mont_yr=salary.getYear()+"/"+mon;
				}else {
					 mont_yr=salary.getYear()+"/"+Integer.toString(monthindex);
				}
			
				
				
				  String sal_date=attendanceDAO.getSalaryDatebyid();
				  String  sal_date1=sal_date+"/"+monthindex+"/"+salary.getYear();
				   sal_date=sal_date1+","+"00:00:00"+" "+"PM";
				   String new_sal_date=DateTimeUtils.calnewdate(sal_date, 30);
				
				
				double leave1=payrollEmployeeDAO.getPayroll_leave(salary.getEmp_id(),mont_yr);
				double otamount=0;
				double leavamt1=0;
				double actualworkinghour=(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()));
				if(employee.getOtallow().equals("1")){
					if(workingdays>actualworkinghour){
						otamount=(workingdays-actualworkinghour)*onehouramt;
						grosspay=grosspay+otamount;
						netpay=netpay+otamount;
					}
				}
				if(leave>0){
					leaveamt=leave*onehouramt;
				}
				
				if(leave1>0) {
					leavamt1=leave1*perdayamt;
					netpay=netpay+leavamt1;
				}
//				leave=leave-leavedays;
				/*if(leave!=0){
					leaveamt=leave*perdayamt;
					netpay=netpay-leaveamt;
				}*/
				salary2.setNetpay(String.valueOf(Math.round(netpay*100.0)/100.0));
				salary2.setGross_pay(String.valueOf(Math.round(grosspay*100.0)/100.0));
				salary2.setPerdayamount(perdayamt);
				salary.setLeave(String.valueOf(leaveamt));
				salary.setOt(""+(Math.round(otamount*100.0)/100.0));
				int res = payrollMasterDAO.payrollInsertFinalSalary(salary, salary2);
			} else {
				payrollForm.setMessage("Employee Salary Already Processed");
				addActionMessage("Employee Salary Already Processed");
			}
		} else {
			boolean checkExist = payrollMasterDAO.checkExistRecord(salary);
			if (checkExist) {
				int up = payrollMasterDAO.updatepayrollstatus(salary);
				Salary salary3 = new Salary();
				salary3 = payrollMasterDAO.getfinalReportData(salary.getEmp_id(), salary.getMonth(),
						salary.getYear(), "2", "apm_payroll_final_salary", pagination);
				int res = payrollMasterDAO.payrollInsertFinalSalaryRpt(salary, salary3);
			}
		}
	

		ArrayList<Salary> salaryList = payrollMasterDAO.getAllSalaryList("", 0);

		payrollForm.setSalaryList(salaryList);

		Date date1 = Calendar.getInstance(Locale.getDefault()).getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date1);
		String yr = "";
		if (payrollForm.getYear() == null) {
			yr = dates[1];
		} else if (payrollForm.getYear().equals("")) {
			yr = dates[1];
		} else {
			yr = payrollForm.getYear();
		}
		payrollForm.setDate(today);
		payrollForm.setMonth(payrollForm.getSelectedmonth());
		payrollForm.setSelectedyear(yr);
		session.setAttribute("filtermonth", payrollForm.getMonth());
		session.setAttribute("filteryear", payrollForm.getSelectedyear());
		if (status == null) {
			status = "0";
		}
		if (status.equals("")) {
			status = "0";
		}
		payrollForm.setStatus(status);
		session.setAttribute("filterstatus", payrollForm.getStatus());
	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();

	}

	return "finalizesal";
}
}