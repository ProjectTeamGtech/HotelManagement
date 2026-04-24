package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Payroll.eu.bi.AttendanceDAO;
import com.apm.Payroll.eu.bi.BranchDAO;
import com.apm.Payroll.eu.bi.PayrollDepartmentDAO;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.bi.PayrollMasterDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCAttendanceDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCBranchDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollDepartment;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollEmployeeDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollMasterDAO;
import com.apm.Payroll.eu.entity.Attendance;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.Payroll.web.form.SalaryForm;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PayrollIncrementAction extends BaseAction implements ModelDriven<SalaryForm>, Preparable {
	Pagination pagination=new Pagination(25,1);
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	SalaryForm salaryForm = new SalaryForm();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	public SalaryForm getModel() {
		// TODO Auto-generated method stub
		return salaryForm;
	}

	@Override
	public String execute() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			String branchid = salaryForm.getBranch_id();
			String searchtext = salaryForm.getSearchtext();
			if(searchtext!=null){
				if(searchtext.equals("")){
					searchtext=null;
				}
			}

			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			ArrayList<Salary> incrementlist = payrollMasterDAO.getIncrementList(branchid,searchtext,pagination);

			salaryForm.setIncrementlist(incrementlist);

			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);

			ArrayList<Employee> branchlist = payrollEmployeeDAO.getAllBranches();
			salaryForm.setBranchlist(branchlist);

			if (branchid != null || branchid != "") {
				salaryForm.setBranch(branchid);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return super.execute();
	}

	public String edit() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);

			String emp_id = request.getParameter("selectedid");

			Employee employee = employeeDAO.getEmployee(emp_id);

			Salary salary = payrollMasterDAO.getSalaryDetails(emp_id);

			salaryForm.setId(salary.getId());
			salaryForm.setEmp_id(emp_id);
			salaryForm.setEmp_name(employee.getName());

			Salary masterSalary = payrollMasterDAO.getMasterSalary(emp_id,pagination);

			String branch_id = salary.getBranch_id();
			salaryForm.setBranch_id(employeeDAO.getBranchName(branch_id));

			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String incre_date = sdf.format(date);
			salaryForm.setIncre_date(incre_date);
			salaryForm.setBasic(masterSalary.getBasic());
			salaryForm.setAllowances(masterSalary.getAllowances());
			salaryForm.setDa_on_ta(masterSalary.getDa_on_ta());
			salaryForm.setSpecial_pay(masterSalary.getSpecial_pay());
			salaryForm.setPersonal_pay(masterSalary.getPersonal_pay());
			salaryForm.setTransport_allowance(masterSalary.getTransport_allowance());
			salaryForm.setHra(masterSalary.getHra());
			salaryForm.setDa(masterSalary.getDa());
			salaryForm.setNpa(masterSalary.getNpa());
			salaryForm.setEmp_pf(masterSalary.getEmp_pf());
			salaryForm.setEmp_esi(masterSalary.getEmp_esi());
			salaryForm.setComp_pf(masterSalary.getComp_pf());
			salaryForm.setComp_esi(masterSalary.getComp_esi());
			salaryForm.setTaxable(masterSalary.getTaxable());
			salaryForm.setOtherincome(salary.getOtherincome());
			salaryForm.setNetpay(salary.getNetpay());
			salaryForm.setGross_pay(salary.getGross_pay());
			salaryForm.setConveyance(masterSalary.getConveyance());
			salaryForm.setWashing(masterSalary.getWashing());
			salaryForm.setPerdevallow(masterSalary.getPerdevallow());
			salaryForm.setVehiclepass(masterSalary.getVehiclepass());
			salaryForm.setFixedsalary(salary.getFixedsalary());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "edit";
	}

	public String update() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = Calendar.getInstance(Locale.getDefault()).getTime();
			String today = sdf.format(date);

			Salary salary = new Salary();
			salary.setId(salaryForm.getId());
			salary.setEmp_id(salaryForm.getEmp_id());
			salary.setDate(salaryForm.getIncre_date());
			salary.setBasic(salaryForm.getBasic());
			salary.setAllowances(salaryForm.getAllowances());
			salary.setDa_on_ta(salaryForm.getDa_on_ta());
			salary.setSpecial_pay(salaryForm.getSpecial_pay());
			salary.setPersonal_pay(salaryForm.getPersonal_pay());
			salary.setTransport_allowance(salaryForm.getTransport_allowance());
			salary.setHra(salaryForm.getHra());
			salary.setDa(salaryForm.getDa());
			salary.setNpa(salaryForm.getNpa());
			salary.setEmp_pf(salaryForm.getEmp_pf());
			salary.setEmp_esi(salaryForm.getEmp_esi());
			salary.setComp_pf(salaryForm.getComp_pf());
			salary.setComp_esi(salaryForm.getComp_esi());
			salary.setTaxable(salaryForm.getTaxable());
			salary.setOtherincome(salaryForm.getOtherincome());
			salary.setNetpay(salaryForm.getNetpay());
			salary.setGross_pay(salaryForm.getGross_pay());
			salary.setConveyance(salaryForm.getConveyance());
			salary.setWashing(salaryForm.getWashing());
			salary.setPerdevallow(salaryForm.getPerdevallow());
			salary.setVehiclepass(salaryForm.getVehiclepass());
			salary.setFixedsalary(salaryForm.getFixedsalary());
			int result = payrollMasterDAO.updateSalary(salary,"apm_payroll_salary_master");
			int res = payrollMasterDAO.updateSalary(salary,"apm_payroll_salary");
		} catch (Exception e) {

			e.printStackTrace();
		}

		return execute();
	}

	public String editsal() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
			AttendanceDAO attendanceDAO = new JDBCAttendanceDAO(connection);

			String emp_id = request.getParameter("emp_id");
			String year = request.getParameter("year");
			String selectedmonth = request.getParameter("month");
			Salary salary = payrollMasterDAO.getSalaryDetails(emp_id);

			Employee employee = payrollEmployeeDAO.getEmployee(emp_id);
            Salary salarymaster=payrollMasterDAO.getconvey(emp_id);
			
			salaryForm.setEmp_id(emp_id);
			salaryForm.setEmp_name(employee.getName());
			salaryForm.setEmp_pf(salary.getEmp_pf());
			salaryForm.setEmp_esi(salary.getEmp_esi());
			salaryForm.setBranch_id(payrollEmployeeDAO.getBranchName(employee.getBranch()));

			salaryForm.setAllowances(salary.getAllowances());
			salaryForm.setSalaryTotal(salary.getSalaryTotal());
			double salaryTotal = Double.parseDouble(salary.getSalaryTotal());
			salaryForm.setBasic(salary.getBasic());
			salaryForm.setNetpay(salary.getNetpay());
			/*
			 * salaryForm.setNetpay(attendanceDAO.totalSalaryForAttendence(
			 * emp_id));
			 */
			salaryForm.setId(salary.getId());
			salaryForm.setOther_deduction(salary.getOther_deduction());
			salaryForm.setDeductions(salary.getDeductions());
			salaryForm.setSalaryId(String.valueOf(salary.getId()));
			/*
			 * salaryForm.setGross_pay(attendanceDAO.totalSalaryForAttendence(
			 * emp_id));
			 */
			salaryForm.setGross_pay(salary.getGross_pay());
			salaryForm.setFixedsalary(salary.getFixedsalary());
			double fixedsalary = Double.parseDouble(salary.getFixedsalary());
			double hra = 0;
			if (fixedsalary < 10000) {
				hra = salaryTotal * 10 / 100;
			} else if (fixedsalary >= 10000 && fixedsalary < 15000) {
				hra = salaryTotal * 20 / 100;
			} else if (fixedsalary >= 15000 && fixedsalary < 20000) {
				hra = salaryTotal * 40 / 100;
			} else {
				hra = salaryTotal * 50 / 100;
			}
			hra = Math.round(hra * 100.0) / 100.0;
			salaryForm.setHra(String.valueOf(hra));
//			salaryForm.setConveyance(salary.getConveyance());
//			double conveyance =Double.parseDouble(salary.getConveyance());
//			salaryForm.setWashing(salary.getWashing()); 
//			double washing=Double.parseDouble(salary.getWashing());
//			 conveyance=conveyance*days/totalDays; 

//			 double perdevallow =
//			  fixedsalary-(salaryTotal+hra+conveyance+washing);
//			  perdevallow=Math.round(perdevallow * 100.0) / 100.0;
//			 salaryForm.setPerdevallow(String.valueOf(perdevallow));
//			
			
//			 salaryForm.setDa_on_ta(salary.getDa_on_ta());
//			 salaryForm.setSpecial_pay(salary.getSpecial_pay());
//			 salaryForm.setPersonal_pay(salary.getPersonal_pay());
//			 salaryForm.setTransport_allowance(salary.getTransport_allowance() );
//			 salaryForm.setHra(salary.getHra());
//			 salaryForm.setDa(salary.getDa());
//			 salaryForm.setNpa(salary.getNpa());
			 

			salaryForm.setComp_pf(salary.getComp_pf());
			salaryForm.setComp_esi(salary.getComp_esi());
			salaryForm.setTaxable(salary.getTaxable());
			salaryForm.setOtherincome(salary.getOtherincome());
			salaryForm.setPrefessional_tax(salary.getPrefessional_tax());
			salaryForm.setMedical_allowance(salary.getMedical_allowance());
			salaryForm.setTds(salary.getTds());

			salaryForm.setOtherincome(salary.getOtherincome());

			salaryForm.setVehiclepass(salary.getVehiclepass());
			Payroll payroll = payrollMasterDAO.getLeaveFormEmpID(salary.getEmp_id());
			int unpaidleaves = 0, paidleaves = 0;

			if (payroll == null) {
				unpaidleaves = 0;
			} else {

				if (payroll.getNo_days() == null) {
					unpaidleaves = 0;
				} else if (payroll.getNo_days().equals("")) {
					unpaidleaves = 0;
				} else {
					unpaidleaves = Integer.parseInt(payroll.getNo_days());
				}
			}

			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			/*
			 * SimpleDateFormat format=new SimpleDateFormat("MMM-YYYY"); String
			 * month=format.format(calendar.getTime());
			 */
			String month = payrollMasterDAO.monthforSalarySlip(emp_id);

			/*
			 * int totalDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			 */

			String[] temp = month.split("-");
			String newmonth=selectedmonth;
			String monthyear="";
			int iyear = Integer.parseInt(temp[1]);
			int imonth = calendar.FEBRUARY;
			int idays = 1;
			if (newmonth.equals("0")) {
				monthyear="Jan"+ "-" + year;
			} else if (newmonth.equals("1")) {
				monthyear="Feb"+ "-" + year;
			} else if (newmonth.equals("2")) {
				monthyear="March"+ "-" + year;
			} else if (newmonth.equals("3")) {
				monthyear="April"+ "-" + year;
			} else if (newmonth.equals("4")) {
				monthyear="May"+ "-" + year;
			} else if (newmonth.equals("5")) {
				monthyear="June"+ "-" + year;
			} else if (newmonth.equals("6")) {
				monthyear="July"+ "-" + year;
			} else if (newmonth.equals("7")) {
				monthyear="August"+ "-" + year;
			} else if (newmonth.equals("8")) {
				monthyear="September"+ "-" + year;
			} else if (newmonth.equals("9")) {
				monthyear="October"+ "-" + year;
			} else if (newmonth.equals("10")) {
				monthyear="November"+ "-" + year;
			} else if (newmonth.equals("11")) {
				monthyear="December"+ "-" + year;
			}
			Calendar mycal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(selectedmonth), idays);

			// Get the number of days in that month
			int totalDays = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date date1 = calendar.getTime();
			String datefrom = dateFormat.format(date1);

			calendar.set(Calendar.DAY_OF_MONTH, totalDays);
			Date date2 = calendar.getTime();
			String dateto = dateFormat.format(date2);

			paidleaves = payrollMasterDAO.getHolidaysinMonth(datefrom, dateto);

			//
			/*
			 * int total_hours=attendanceDAO.getTotalHours(month,
			 * salary.getEmp_id()); float numberofdayswork=total_hours/8;
			 */
			double days = payrollMasterDAO.getDaysForSalary(emp_id,"");
			salaryForm.setDays(days);
			double conveyance = Double.parseDouble(salarymaster.getConveyance());
			conveyance = Math.round(conveyance * days / totalDays);
			salaryForm.setConveyance(String.valueOf(conveyance));

			double washing = Double.parseDouble(salarymaster.getWashing());
			washing = Math.round(washing * days / totalDays);
			salaryForm.setWashing(String.valueOf(washing));
			double perdevallow = fixedsalary - (salaryTotal + hra + conveyance + washing);
			perdevallow = Math.round(perdevallow * 100.0) / 100.0;
			salaryForm.setPerdevallow(String.valueOf(perdevallow));

			/* salaryForm.setWorkeddays(String.valueOf(numberofdayswork)); */
			salaryForm.setTotaldays(String.valueOf(totalDays));
			salaryForm.setUnpaidleaves(String.valueOf(unpaidleaves));
			salaryForm.setPaidleaves(String.valueOf(paidleaves));
			salaryForm.setHolidays(String.valueOf(paidleaves));
			salaryForm.setCurrentMonth("December");
			salaryForm.setMonthyear(month);
			salaryForm.setMonth(selectedmonth);
			salaryForm.setYear(year);
			salaryForm.setMonthandyear(monthyear);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return "editsal";
	}

	public int getNumberOfSundays() {

		int count = 0;

		try {

			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);

			calendar.set(year, month, 1);

			int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			for (int i = 0; i <= days; i++) {

				calendar.set(year, month, i);
				int day = calendar.get(Calendar.DAY_OF_WEEK);
				if (day == 1) {

					count++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public String editdeduction() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			PayrollMasterDAO masterDAO = new JDBCPayrollMasterDAO(connection);
			String emp_id = request.getParameter("emp_id");
			String basic = request.getParameter("salaryTotal");
			String emp_pf = request.getParameter("emp_pf");
			String emp_esi = request.getParameter("emp_esi");
			String other_deduction = request.getParameter("other_deduction");
			String leave = request.getParameter("leave");
			String prefessional_tax = request.getParameter("prefessional_tax");
			String tds = request.getParameter("tds");
			String deductions = request.getParameter("deductions");
			String allowances = request.getParameter("allowances");
			String gross_pay = request.getParameter("gross_pay");
			String netpay = request.getParameter("netpay");

			Date date = Calendar.getInstance(Locale.getDefault()).getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dates = sdf.format(date);

			Salary salary = new Salary();
			salary.setEmp_id(emp_id);
			salary.setBasic(basic);
			salary.setEmp_pf(emp_pf);
			salary.setEmp_esi(emp_esi);
			salary.setOther_deduction(other_deduction);
			salary.setLeave(leave);
			salary.setPrefessional_tax(prefessional_tax);
			salary.setTds(tds);
			salary.setDeductions(deductions);
			salary.setAllowances(allowances);
			salary.setGross_pay(gross_pay);
			salary.setNetpay(netpay);
			salary.setDate(dates);
			salary.setYear(request.getParameter("year"));
			String nummonth=request.getParameter("month");
			int monthindex= Integer.parseInt(nummonth);
		    String monThArr[]={"Jan","Feb","March","April","May","June","July","August","September","October","November","December"};
		    
		    String month= monThArr[monthindex];
			salary.setMonth(month);
			int result = masterDAO.updateDeductionSalary(salary);
			int up=masterDAO.updatefinalsalarydeduction(salary);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {
			connection.close();
		}

		return null;
	}

	public String editallowances() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO masterDAO = new JDBCPayrollMasterDAO(connection);
			Salary salary = new Salary();

			Date date = Calendar.getInstance(Locale.getDefault()).getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dates = sdf.format(date);
			salary.setEmp_id(request.getParameter("emp_id"));
			salary.setBasic(request.getParameter("salaryTotal"));
			salary.setMedical_allowance(request.getParameter("medical_allowance"));
			salary.setDa_on_ta(request.getParameter("da_on_ta"));
			salary.setSpecial_pay(request.getParameter("special_pay"));
			salary.setPersonal_pay(request.getParameter("personal_pay"));
			salary.setTransport_allowance(request.getParameter("transport_allowance"));
			salary.setHra(request.getParameter("hra"));
			salary.setDa(request.getParameter("da"));
			salary.setNpa(request.getParameter("npa"));
			salary.setAllowances(request.getParameter("allowances"));
			salary.setDeductions(request.getParameter("deductions"));
			salary.setGross_pay(request.getParameter("gross_pay"));
			salary.setNetpay(request.getParameter("netpay"));
			// ALLOWNACES
			salary.setConveyance(request.getParameter("conveyance"));
			salary.setWashing(request.getParameter("washing"));
			salary.setPerdevallow(request.getParameter("perdevallow"));
			salary.setYear(request.getParameter("year"));
			String nummonth=request.getParameter("month");
			int monthindex= Integer.parseInt(nummonth);
		    String monThArr[]={"Jan","Feb","March","April","May","June","July","August","September","October","November","December"};
		    
		    String month= monThArr[monthindex];
			salary.setMonth(month);
		    salary.setDate(dates);
		    int up=masterDAO.updatefinalizeSalary(salary);
			int result = masterDAO.updateAllowanceSalary(salary);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public String payslip() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			BranchDAO branchDAO = new JDBCBranchDAO(connection);
			String emp_id = request.getParameter("emp_id");
			if(emp_id==null){
				 emp_id= (String) session.getAttribute("emp_id");
			}
			String month = request.getParameter("month");
			if(month==null){
				month=(String) session.getAttribute("mymonth");
			}
			String year = request.getParameter("year");
			int monthindex= Integer.parseInt(month);
		    String monThArr[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
		    
		    month= monThArr[monthindex];
		    Employee employee = payrollEmployeeDAO.getAllDetailsEmployee(emp_id);
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
			sunday=getSundays(Integer.parseInt(year), monthindex, temp1);
			}
			int idays=1;
			Calendar mycal1 = new GregorianCalendar(Integer.parseInt(year), monthindex, idays);
			int daysInMonth = mycal1.getActualMaximum(Calendar.DAY_OF_MONTH);
			int actualworkingdays=daysInMonth-sunday;
			double totalworkinghour=actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday());
			salaryForm.setTotaldays(""+daysInMonth);
		    
		    salaryForm.setTotalworkinghour(""+totalworkinghour);
			Salary salary1 = payrollMasterDAO.getSalaryDetails(emp_id);
			salaryForm.setActualnetpay(salary1.getNetpay());
		    double workingdays = payrollMasterDAO.getWorkingDays(month+"-"+year,emp_id);
//		    double actualworkinghour=workingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday());
		    salaryForm.setActualworkingday(""+workingdays);
		    double othour=0;
		    double leave=(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()))-workingdays;
		    if(employee.getOtallow().equals("1")){
		    	double actualworkinghour=(actualworkingdays*DateTimeUtils.convertToDouble(employee.getWorkinghourday()));
				if(workingdays>actualworkinghour){
					othour=workingdays-actualworkinghour;
					salaryForm.setOthour(""+othour);
				}
			}
		    if(leave>0){
				salaryForm.setLeavehour(""+leave);
			}
			Salary salary=payrollMasterDAO.getfinalReportData(emp_id,month,year,"","apm_payroll_final_salary_rpt",pagination);
			
			Payroll payroll = branchDAO.getBranch(salary.getBranch_id());
			Payroll company = payrollDepartmentDAO.getCompanyDetails("1");

			Payroll payroll2 = payrollMasterDAO.getLeaveFormEmpID(salary.getEmp_id());
			int leavedays = 0;
			if (payroll2 == null) {
				leavedays = 0;
			} else {

				if (payroll2.getNo_days() == null) {
					leavedays = 0;
				} else if (payroll2.getNo_days().equals("")) {
					leavedays = 0;
				} else {
					leavedays = Integer.parseInt(payroll2.getNo_days());
				}
			}

			Payroll payroll3 = payrollMasterDAO.getLoanFromEmpID(salary.getEmp_id());

			int loan = 0;

			if (payroll3 != null) {

				if (payroll3.getAmount() != null) {

					if (!payroll3.getAmount().equals("")) {

						loan = Integer.parseInt(payroll3.getAmount());
					}

				}

			}

			salaryForm.setLoan(String.valueOf(loan));

			/*
			 * if(salary.getBasic()==null){
			 * 
			 * salary.setBasic("0"); } if(salary.getBasic().equals("")){
			 * 
			 * salary.setBasic("0"); }
			 */
			if (salary.getSalaryTotal() == null) {

				salary.setSalaryTotal("0");
			}
			if (salary.getSalaryTotal().equals("")) {

				salary.setSalaryTotal("0");
			}

			double salaryTotal = Double.parseDouble(salary.getSalaryTotal());

			double perday = salaryTotal / 30;
			double leavecount = leavedays * perday;

			salaryForm.setCompany_name(company.getCompany_name());
			salaryForm.setCompany_address(payroll.getAddress() + " " + payroll.getCity());
			salaryForm.setComp_email(payroll.getEmail());
			salaryForm.setComp_phone(payroll.getPhone1());
			salaryForm.setComp_fax(payroll.getPhone2());
			salaryForm.setComp_website("www.escapeq.com");
			DecimalFormat df = new DecimalFormat("0.00");
			/*
			 * Date date=Calendar.getInstance(Locale.getDefault()).getTime();
			 * SimpleDateFormat sdf=new SimpleDateFormat("MMM-YYYY"); String
			 * datefor=sdf.format(date);
			 */
			//String datefor = payrollMasterDAO.monthforSalarySlip(emp_id);
			double totearn=Double.parseDouble(salary.getDa())+Double.parseDouble(salary.getHra())+Double.parseDouble(salary.getPerdevallow())+Double.parseDouble(salary.getConveyance())+Double.parseDouble(salary.getMedical_allowance())+Double.parseDouble(salary.getBasic())+Double.parseDouble(salary.getOt())+Double.parseDouble(salary.getAdvance());
			double totded=Double.parseDouble(salary.getTds())+Double.parseDouble(salary.getEmp_pf())+Double.parseDouble(salary.getEmp_esi())+Double.parseDouble(salary.getLeave())+Double.parseDouble(salary.getPrefessional_tax())+Double.parseDouble(salary.getLoan());
			salaryForm.setDate(month);
			salaryForm.setEmp_name(employee.getName());
			salaryForm.setEmp_id(emp_id);
			salaryForm.setEmp_role(employee.getDesignation());
			salaryForm.setSalaryTotal(salary.getSalaryTotal());
			salaryForm.setBasic(df.format(Double.parseDouble(salary.getBasic())));
			salaryForm.setHra(df.format(Double.parseDouble(salary.getHra())));
			salaryForm.setMedical_allowance(df.format(Double.parseDouble(salary.getMedical_allowance())));
			salaryForm.setConveyance(df.format(Double.parseDouble(salary.getConveyance())));
			salaryForm.setWashing(salary.getWashing());
			salaryForm.setVehiclepass(salary.getVehiclepass());
			salaryForm.setEmp_pf(df.format(Double.parseDouble(salary.getEmp_pf())));
			salaryForm.setGross_pay(df.format(Double.parseDouble(salary.getGross_pay())));
			salaryForm.setLoan(String.valueOf(loan));
			salaryForm.setPrefessional_tax(df.format(Double.parseDouble(salary.getPrefessional_tax())));
			salaryForm.setLeave(String.valueOf(df.format(leavecount)));
			salaryForm.setTds(df.format(Double.parseDouble(salary.getTds())));
			salaryForm.setOther_deduction(salary.getOther_deduction());
			salaryForm.setDeductions(df.format(Double.parseDouble(salary.getDeductions())));
			salaryForm.setNetpay(df.format(Double.parseDouble(salary.getNetpay())));
			//salaryForm.setNetpay(df.format(Double.parseDouble(salary.getCalnetpay())));
			salaryForm.setNo_sundays(String.valueOf(df.format(leavedays)));
			salaryForm.setPerdevallow(df.format(Double.parseDouble(salary.getPerdevallow())));
			salaryForm.setComp_pf(salary.getComp_pf());
			salaryForm.setEmp_esi(df.format(Double.parseDouble(salary.getEmp_esi())));
			salaryForm.setTotearn(df.format(totearn));
			salaryForm.setTotded(df.format(totded));
			salaryForm.setEmpcode(employee.getEmpcode());
			salaryForm.setDa(df.format(Double.parseDouble(salary.getDa())));
			salaryForm.setTotal_leaves(df.format(Double.parseDouble(salary.getLeave())));
			salaryForm.setOt(df.format(Double.parseDouble(salary.getOt())));
			salaryForm.setAdvance(df.format(Double.parseDouble(salary.getAdvance())));
			salaryForm.setLoan(df.format(Double.parseDouble(salary.getLoan())));
			// For Letterhead
			/*Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			salaryForm.setClinicName(clinic.getClinicName());
			salaryForm.setClinicOwner(clinic.getClinicOwner());
			salaryForm.setOwner_qualification(clinic.getOwner_qualification());
			salaryForm.setLandLine(clinic.getLandLine());
			salaryForm.setClinicemail(clinic.getEmail());
			salaryForm.setWebsiteUrl(clinic.getWebsiteUrl());
			salaryForm.setClinicLogo(clinic.getUserImageFileName());*/
			AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			salaryForm.setClinicName(clinic.getClinicName());
			
			salaryForm.setClinicOwner(clinic.getClinicOwner());
			salaryForm.setOwner_qualification(clinic.getOwner_qualification());
			salaryForm.setLocationAdressList(locationAdressList);
			//accountsForm.setAddress(clinic.getAddress());
			salaryForm.setLandLine(clinic.getLandLine());
			salaryForm.setClinicemail(clinic.getEmail());
			salaryForm.setWebsiteUrl(clinic.getWebsiteUrl());
			salaryForm.setLocationAdressList(locationAdressList);
			
			//set balgopal address
			Clinic ccbg =  locationAdressList.get(0);
			session.setAttribute("balgopaladdress", ccbg.getAddress());
			session.setAttribute("balgopalcname", ccbg.getClinicName());
			salaryForm.setClinicLogo(clinic.getUserImageFileName());
			salaryForm.setMonthandyear(month+"-"+year);
            salaryForm.setDate_join(employee.getDate_join());
            
            Attendance attendance1=new Attendance();
            attendance1.setEmp_id(emp_id);
            Attendance attendance=attendanceDAO.getAttendanceOfMonth(attendance1, month+"-"+year, 0,"");
            salaryForm.setDays(Double.parseDouble(attendance.getDays()));
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			connection.close();
		}

		return "payslip";
	}

	public String changeddays(){
		Connection connection =null;
		try {
			connection = Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
			
			int month = Integer.parseInt(request.getParameter("month"));
			String emp_id= request.getParameter("id");
			String mon[]={"Jan","Feb","March","April","May","June","July","August","September","October","November","December"};
			Calendar calendar = Calendar.getInstance();
			int year=calendar.get(Calendar.YEAR);
			String str=mon[month]+"-"+year;
			Salary salary = payrollMasterDAO.getchangeworkdays(emp_id,str);
			StringBuffer buffer= new StringBuffer();
			buffer.append(salary.getDays()+"~"+salary.getSalaryTotal());
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	public void prepare() throws Exception {

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
}
