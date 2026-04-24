package com.apm.Payroll.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface PayrollEmployeeDAO {

	ArrayList<Employee> getAllEmployees(String searchText, Pagination pagination, String dept, String searchname, LoginInfo loginInfo);
	/*ArrayList<Employee> getAllEmployees(String branchid);*/
	public String getCompanyName(String comp_id);
	public String getBranchName(String branchid);
	public String getDepartmentName(String dept_id);
	ArrayList<Employee> getAllCompanies();
	ArrayList<Employee> getAllBranches();
	ArrayList<Employee> getAllDepartments();
	int addEmployee(Employee employee);
	Employee getEmployee(String id);
	int updateEmployee(Employee employee);
	int addToSalary(int result);
	int gettotalempcount(String searchText, String dept, String searchname);
	ArrayList<Employee> getAllAttendence(String emp_id, String filter_status);
	ArrayList<Employee> getseachedname(String searchtxt);
	Employee getAllDetailsEmployee(String emp_id);
	int updatebasicsal(Employee employee, String table);
	String getEmployeeName(String empid);
	int updatePersonalEmployee(Employee employee);
	ArrayList<Master> getAllDesignation(String deptid);
	int updateBankEmployee(Employee employee);
	String getEmployeeUserId(String emp_id);
	int updateUanPfEmployee(Employee employee);
	int updateleaveEmployee(Employee employee);
	Employee getallDetailsEmployeeByUserId(String userid);
	String getEmployeeEmpid(String userid);
	int savePayrollFileSubmissionData(String datetime, String uploaded_userid, String filesubmission_category,
			String filesubremark, String fileName, String filecontenttype, String empid, String userid);
	ArrayList<Employee> getPayrollFileSubmissionList(String emp_id);
	Employee getFileSubmissionData(String id);
	ArrayList<Employee> getAlldocumenttype();
	int updateWorkinghrEmployee(Employee employee);
	int updateactiveStatus(String emp_id, String flag);
	ArrayList<Employee> getAlltemplateList();
	String getTemplatetextbyid(String tempId);
	double getPayroll_leave(String emp_id, String mont_yr);
}
