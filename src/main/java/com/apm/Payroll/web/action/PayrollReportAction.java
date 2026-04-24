package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.text.DateFormat;
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
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.bi.PayrollMasterDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCAttendanceDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollEmployeeDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollMasterDAO;
import com.apm.Payroll.eu.entity.Attendance;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.Payroll.web.form.PayrollForm;
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

public class PayrollReportAction extends BaseAction implements ModelDriven<PayrollForm>,Preparable {

	PayrollForm payrollForm=new PayrollForm();
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	Pagination pagination = new Pagination(25, 1);
	public PayrollForm getModel() {
		return payrollForm;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	} 
	
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public String login() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
	      	PayrollMasterDAO masterDAO=new JDBCPayrollMasterDAO(connection);	
	      	
			
	        String username=payrollForm.getUserid();
	        String password=payrollForm.getPassword();
		      
		    if(username!=null && username!=""){
		    	
		    	if(password!=null && password!=""){
		    		
		    		int result=masterDAO.getLoginEmployee(username,password);
		   
		    		if(result>0){
		    		     
		                session.setAttribute("emp_id", result);
		                ArrayList<Salary> salaryList=masterDAO.getAllSalaryList("",result);
		    			payrollForm.setSalaryList(salaryList);
		    			return "salary";
		    			 
		    		}
		    		else {
		    			
		    			addActionError(getText("Username or Password Invalid"));
		    			return "loginpayroll";
		    		}
		    		
		    	}
		    	
		    }
	        
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
	
		return "salary";
	}
	
	
	
	public String holiday() throws Exception {
		
	
		if(!verifyLogin(request)){
			return "login";
		}
	
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
			ArrayList<Payroll> holidayList=payrollMasterDAO.holidayReport();
			payrollForm.setHolidayList(holidayList);
			
			PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Employee> branchlist=payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
	
		return "holiday";
	}
	
	public String loan() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			String branchid=payrollForm.getBranch();
			
			connection=Connection_provider.getconnection();
			PayrollMasterDAO masterDAO=new JDBCPayrollMasterDAO(connection);
			ArrayList<Payroll> loanlist=masterDAO.getAllLoanlist(branchid);
			payrollForm.setLoanlist(loanlist);
			
			PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Employee> branchlist=payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);
			
			payrollForm.setBranch(branchid);

		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "loan";
	}
	
	
	public String salary() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Employee> branchlist=payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);
			
			String branchid=payrollForm.getBranch();
			
			PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
			ArrayList<Salary> salaryList=payrollMasterDAO.getAllSalaryList(branchid,0);
			payrollForm.setSalaryList(salaryList);
			
			payrollForm.setBranch(branchid);
			
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		
		return "salary";
	}
	
	
	public String employee() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			
			String branchid=payrollForm.getBranch();
			String searchText = payrollForm.getSearchText();
			PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Employee> employeelist=payrollEmployeeDAO.getAllEmployees(searchText,null,null,null,loginInfo);
			payrollForm.setEmployeelist(employeelist);
			
			
			ArrayList<Employee> branchlist=payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);
			payrollForm.setBranch(branchid);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "employee";
	}
	
	public String attendance() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String emp_id=request.getParameter("emp_id");
			String filter_status = request.getParameter("filter_status");
			PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
			Calendar calendar=Calendar.getInstance(Locale.getDefault());
		     SimpleDateFormat format=new SimpleDateFormat("YYYY");
		     String year=format.format(calendar.getTime());
		     String monThArr[]={"Jan","Feb","March","April","May","June","July","August","September","October","November","December"};
		     int mo=calendar.get(Calendar.MONTH);
		     String month = monThArr[mo];
		     if(filter_status!=null){
		    	 filter_status=filter_status+"-"+year;
		     }else{
		    	 filter_status=month +"-"+year; 
		     }
			ArrayList<Employee> branchlist=payrollEmployeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);
			ArrayList<Employee>attendencelist = payrollEmployeeDAO.getAllAttendence(emp_id,filter_status);
			payrollForm.setAttendencelist(attendencelist);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "attendance";
	}
	
	public String allowancededuction() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PayrollMasterDAO masterDAO=new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
			String branchid=payrollForm.getBranch();
			
			ArrayList<Salary> salaryList=masterDAO.getAllSalaryList(branchid,0);
			payrollForm.setSalaryList(salaryList);		
			
			payrollForm.setBranch(branchid);
			
			ArrayList<Employee> branchlist=employeeDAO.getAllBranches();
			payrollForm.setBranchlist(branchlist);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "allowancededuction";
	}
	
	public void prepare() throws Exception {
		
	}

	
	public String empholiday() throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			String emp_id=request.getParameter("emp_id");
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
			
	   return "empholiday";	
	}
	
	public String employeesalaryrpt(){
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Salary> salaryList=new ArrayList<Salary>();
			PayrollMasterDAO masterDAO=new JDBCPayrollMasterDAO(connection);
		
			ArrayList<Employee> departmentlist = payrollEmployeeDAO.getAllDepartments();
			payrollForm.setDepartmentlist(departmentlist);
			
			String month="";
			Calendar calendar=Calendar.getInstance(Locale.getDefault());
			SimpleDateFormat sdf=new SimpleDateFormat("MM");
			Date date=calendar.getTime();
			Calendar cal=Calendar.getInstance(Locale.getDefault());
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2=new SimpleDateFormat("MM");
			Date date1=cal.getTime();
			String year=sdf1.format(date1);
			String todaymonth=sdf2.format(date1);
			String newmonth=payrollForm.getMonth();
			String empid=payrollForm.getSearchText();
			String empname=payrollForm.getEmpnamesearch();
			String dept=payrollForm.getDepartment();
			String year1=request.getParameter("year");
			if(year1==null){
				 year=sdf1.format(date1);
			}else{
				 year=year1;
			}
			if(newmonth == null){
				month=sdf.format(date);
				newmonth=String.valueOf(Integer.parseInt(todaymonth)-1);
			}else{
				month=String.valueOf(Integer.parseInt(newmonth)-1);
			}
			 int monthindex= Integer.parseInt(newmonth);
			    String monThArr[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
			    
				 month= monThArr[monthindex];
					int count= masterDAO.getReportDatacount(empid,empname,month,year,dept);
					pagination.setPreperties(count);
				 salaryList =masterDAO.getReportData(empid,empname,month,year,dept,pagination);
			payrollForm.setSalaryList(salaryList);
			
			payrollForm.setTotalRecords(count);
			pagination.setTotal_records(salaryList.size());
			payrollForm.setTotalRecords(count);
			payrollForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			int size = salaryList.size();
			double totbasic=0;
			double tothra=0;
			double totda=0;
			double totmed=0;
			double totconvey=0;
			double totperdev=0;
			double totot=0;
			double totpf=0;
			double totesi=0;
			double totleave=0;
			double totprof=0;
			double tottds=0;
			double totgrosspay=0;
			double totnetpay=0;
			if (size > 0) {
				totbasic = Double.parseDouble(salaryList.get(size - 1).getTotbasic());
				tothra = Double.parseDouble(salaryList.get(size - 1).getTothra());
				totda = Double.parseDouble(salaryList.get(size - 1).getTotda());
				totmed = Double.parseDouble(salaryList.get(size - 1).getTotmed());
				totconvey = Double.parseDouble(salaryList.get(size - 1).getTotconvey());
				totperdev = Double.parseDouble(salaryList.get(size - 1).getTotperdev());
				totot = Double.parseDouble(salaryList.get(size - 1).getTotot());
				totpf = Double.parseDouble(salaryList.get(size - 1).getTotpf());
				totesi = Double.parseDouble(salaryList.get(size - 1).getTotesi());
				totleave = Double.parseDouble(salaryList.get(size - 1).getTotleave());
				totprof = Double.parseDouble(salaryList.get(size - 1).getTotprof());
				tottds = Double.parseDouble(salaryList.get(size - 1).getTottds());
				totgrosspay = Double.parseDouble(salaryList.get(size - 1).getTotgrosspay());
				totnetpay = Double.parseDouble(salaryList.get(size - 1).getTotnetpay());
				payrollForm.setTotbasic(totbasic);
				payrollForm.setTothra(tothra);
				payrollForm.setTotda(totda);
				payrollForm.setTotmed(totmed);
				payrollForm.setTotconvey(totconvey);
				payrollForm.setTotperdev(totperdev);
				payrollForm.setTotot(totot);
				payrollForm.setTotpf(totpf);
				payrollForm.setTotesi(totesi);
				payrollForm.setTotleave(totleave);
				payrollForm.setTotprof(totprof);
				payrollForm.setTottds(tottds);
				payrollForm.setTotgrosspay(totgrosspay);
				payrollForm.setTotnetpay(totnetpay);
				payrollForm.setTotadvance(Double.parseDouble(salaryList.get(size - 1).getTotadvance()));
				payrollForm.setTotloan(Double.parseDouble(salaryList.get(size - 1).getTotloan()));
			} 
			payrollForm.setMonth(newmonth);
			payrollForm.setYear(year);
			payrollForm.setSelectedmonth(month);
			payrollForm.setEmpnamesearch(empname);
			payrollForm.setEmp_id(empid);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			payrollForm.setClinicName(clinic.getClinicName());
			payrollForm.setClinicOwner(clinic.getClinicOwner());
			payrollForm.setOwner_qualification(clinic.getOwner_qualification());
			payrollForm.setLocationAdressList(locationAdressList);
			payrollForm.setAddress(clinic.getAddress());
			payrollForm.setLandLine(clinic.getLandLine());
			payrollForm.setClinicemail(clinic.getEmail());
			payrollForm.setWebsiteUrl(clinic.getWebsiteUrl());
			payrollForm.setClinicLogo(clinic.getUserImageFileName());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "employeesalrpt";
	}
	
	public String attendancemonth(){
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String month="";
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			Calendar calendar=Calendar.getInstance(Locale.getDefault());
			SimpleDateFormat sdf=new SimpleDateFormat("MM");
			Date date=calendar.getTime();
			Calendar cal=Calendar.getInstance(Locale.getDefault());
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2=new SimpleDateFormat("MM");
			Date date1=cal.getTime();
			String year=sdf1.format(date1);
			String todaymonth=sdf2.format(date1);
			String newmonth=payrollForm.getMonth();
			String empid=payrollForm.getSearchText();
			String empname=payrollForm.getEmpnamesearch();
			String year1=request.getParameter("year");
			if(year1==null){
				 year=sdf1.format(date1);
			}else{
				year=year1;
			}
			if(newmonth == null){
				month=sdf.format(date);
				newmonth=String.valueOf(Integer.parseInt(todaymonth)-1);
			}else{
				month=String.valueOf(Integer.parseInt(newmonth)-1);
			}
			 int monthindex= Integer.parseInt(newmonth);
			    String monThArr[]={"Jan","Feb","March","April","May","June","July","August","September","October","November","December"};
			    
				 month= monThArr[monthindex];
				 
				 	int iyear = Integer.parseInt(year);
					int imonth = Integer.parseInt(newmonth);
					int idays = 1;
					Calendar mycal = new GregorianCalendar(iyear, imonth, idays);
					// Get the number of days in that month
					int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
					Calendar cal5 = Calendar.getInstance();
					int dayOfMonth = cal5.get(Calendar.DAY_OF_MONTH);
					String tmp=String.valueOf(Integer.parseInt(newmonth)+1);
					int count=attendanceDAO.getMonthWiseReportCount(empid,empname,tmp,year,daysInMonth,dayOfMonth);
					pagination.setPreperties(count);
				 ArrayList<Attendance> attendancemaster=attendanceDAO.getMonthWiseReport(empid,empname,tmp,year,daysInMonth,dayOfMonth,pagination);
					
				 payrollForm.setAttendancemaster(attendancemaster);
				 payrollForm.setDays(String.valueOf(daysInMonth));
				 session.setAttribute("monhday", daysInMonth);
				 payrollForm.setMonth(newmonth);
					payrollForm.setYear(year);
					payrollForm.setEmpnamesearch(empname);
					payrollForm.setEmp_id(empid);
					payrollForm.setSelectedmonth(month);
					payrollForm.setTotalRecords(count);
					pagination.setTotal_records(attendancemaster.size());
					payrollForm.setTotalRecords(count);
					payrollForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "attendancereport";
	}
	
	public  String accountreport() {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
			ArrayList<Salary> salaryList=new ArrayList<Salary>();
			PayrollMasterDAO masterDAO=new JDBCPayrollMasterDAO(connection);
		
			ArrayList<Employee> departmentlist = payrollEmployeeDAO.getAllDepartments();
			payrollForm.setDepartmentlist(departmentlist);
			
			String month="";
			Calendar calendar=Calendar.getInstance(Locale.getDefault());
			SimpleDateFormat sdf=new SimpleDateFormat("MM");
			Date date=calendar.getTime();
			Calendar cal=Calendar.getInstance(Locale.getDefault());
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf2=new SimpleDateFormat("MM");
			Date date1=cal.getTime();
			String year=sdf1.format(date1);
			String todaymonth=sdf2.format(date1);
			String newmonth=payrollForm.getMonth();
			String empid=payrollForm.getSearchText();
			String empname=payrollForm.getEmpnamesearch();
			String dept=payrollForm.getDepartment();
			String year1=request.getParameter("year");
			if(year1==null){
				 year=sdf1.format(date1);
			}else{
				year=year1;
			}
			if(newmonth == null){
				month=sdf.format(date);
				newmonth=String.valueOf(Integer.parseInt(todaymonth)-1);
			}else{
				month=String.valueOf(Integer.parseInt(newmonth)-1);
			}
			 int monthindex= Integer.parseInt(newmonth);
			    String monThArr[]={"Jan","Feb","March","April","May","June","July","August","September","October","November","December"};
			    
				 month= monThArr[monthindex];
					int count= masterDAO.getReportDatacount(empid,empname,month,year,dept);
					pagination.setPreperties(count);
				 salaryList =masterDAO.getReportData(empid,empname,month,year,dept,pagination);
			payrollForm.setSalaryList(salaryList);
			
			payrollForm.setTotalRecords(count);
			pagination.setTotal_records(salaryList.size());
			payrollForm.setTotalRecords(count);
			payrollForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
			int size = salaryList.size();
			double totbasic=0;
			double tothra=0;
			double totda=0;
			double totmed=0;
			double totconvey=0;
			double totperdev=0;
			double totot=0;
			double totpf=0;
			double totesi=0;
			double totleave=0;
			double totprof=0;
			double tottds=0;
			double totgrosspay=0;
			double totnetpay=0;
			if (size > 0) {
				totbasic = Double.parseDouble(salaryList.get(size - 1).getTotbasic());
				tothra = Double.parseDouble(salaryList.get(size - 1).getTothra());
				totda = Double.parseDouble(salaryList.get(size - 1).getTotda());
				totmed = Double.parseDouble(salaryList.get(size - 1).getTotmed());
				totconvey = Double.parseDouble(salaryList.get(size - 1).getTotconvey());
				totperdev = Double.parseDouble(salaryList.get(size - 1).getTotperdev());
				totot = Double.parseDouble(salaryList.get(size - 1).getTotot());
				totpf = Double.parseDouble(salaryList.get(size - 1).getTotpf());
				totesi =Double.parseDouble(salaryList.get(size - 1).getTotesi());
				totleave = Double.parseDouble(salaryList.get(size - 1).getTotleave());
				totprof = Double.parseDouble(salaryList.get(size - 1).getTotprof());
				tottds = Double.parseDouble(salaryList.get(size - 1).getTottds());
				totgrosspay = Double.parseDouble(salaryList.get(size - 1).getTotgrosspay());
				totnetpay =Double.parseDouble( salaryList.get(size - 1).getTotnetpay());
				payrollForm.setTotbasic(totbasic);
				payrollForm.setTothra(tothra);
				payrollForm.setTotda(totda);
				payrollForm.setTotmed(totmed);
				payrollForm.setTotconvey(totconvey);
				payrollForm.setTotperdev(totperdev);
				payrollForm.setTotot(totot);
				payrollForm.setTotpf(totpf);
				payrollForm.setTotesi(totesi);
				payrollForm.setTotleave(totleave);
				payrollForm.setTotprof(totprof);
				payrollForm.setTottds(tottds);
				payrollForm.setTotgrosspay(totgrosspay);
				payrollForm.setTotnetpay(totnetpay);
				
			} 
			payrollForm.setMonth(newmonth);
			payrollForm.setYear(year);
			payrollForm.setSelectedmonth(month);
			payrollForm.setEmpnamesearch(empname);
			payrollForm.setEmp_id(empid);
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			payrollForm.setClinicName(clinic.getClinicName());
			payrollForm.setClinicOwner(clinic.getClinicOwner());
			payrollForm.setOwner_qualification(clinic.getOwner_qualification());
			payrollForm.setLocationAdressList(locationAdressList);
			payrollForm.setAddress(clinic.getAddress());
			payrollForm.setLandLine(clinic.getLandLine());
			payrollForm.setClinicemail(clinic.getEmail());
			payrollForm.setWebsiteUrl(clinic.getWebsiteUrl());
			payrollForm.setClinicLogo(clinic.getUserImageFileName());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "accountreport";
	}
	public String mobileattendancereport(){
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String fromDate = payrollForm.getFromdate();
			String toDate = payrollForm.getTodate();
			String empsearch=payrollForm.getSearchText();
			AttendanceDAO attendanceDAO=new JDBCAttendanceDAO(connection);
			if (fromDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
			} else {

				if (fromDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Calendar cal = Calendar.getInstance();
					fromDate = dateFormat.format(cal.getTime());
					fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
				} else {
					fromDate = DateTimeUtils.getCommencingDatePayroll(fromDate);
				}
			}

			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
			} else {
				if (toDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Calendar cal = Calendar.getInstance();
					toDate = dateFormat.format(cal.getTime());
					toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
				} else {
					toDate = DateTimeUtils.getCommencingDatePayroll(toDate);
				}
			}
//					int count=attendanceDAO.getMonthWiseReportCount(fromDate,toDate,empsearch);
//					pagination.setPreperties(count);
				 ArrayList<Attendance> mobileattendancemaster=attendanceDAO.getmobileattendance(empsearch,fromDate, toDate,pagination);
//					
				 payrollForm.setMobileattendancemaster(mobileattendancemaster);
//				 payrollForm.setDays(String.valueOf(daysInMonth));
//				 session.setAttribute("monhday", daysInMonth);
//					payrollForm.setTotalRecords(count);
				 payrollForm.setFromdate(DateTimeUtils.getCommencingDatePayroll(fromDate));
				 payrollForm.setTodate(DateTimeUtils.getCommencingDatePayroll(toDate));
				 payrollForm.setSearchText(empsearch);
//					payrollForm.setTotalRecords(count);
					payrollForm.setPagerecords(String.valueOf(pagination.getTotal_records()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "mobileattendancereport";
	}
}
