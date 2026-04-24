package com.apm.Payroll.eu.bi;

import java.util.ArrayList;
import java.util.HashSet;

import com.apm.Payroll.eu.entity.Allowance;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface PayrollMasterDAO {

	ArrayList<Payroll> getAllLoanlist(String branchid);
	int addLoan(Payroll payroll);
	HashSet<String> getAllEmployees();
	Payroll getLoan(String id);
	int updateLoan(Payroll payroll);
	ArrayList<Payroll> getleaveList(String branchid,Pagination pagination, String searchText,String Flag,String userid,LoginInfo loginInfo, String status, String fromDate, String toDate, String leavests, String leavetype);
	/*int addLeave(Payroll payroll);*/
	ArrayList<Salary> getIncrementList(String branchid, String searchtext, Pagination pagination);
	public Salary getSalaryDetails(String emp_id);
	int updateSalary(Salary salary, String tablename);
	ArrayList<Salary> getAllSalaryList(String branchid,int emp_id);
	Payroll getLeave(String id);
	int updateLeave(Payroll payroll);
	public double getDaysForSalary(String emp_id,String month);
	Payroll getLeaveFormEmpID(String emp_id);
	Payroll getLoanFromEmpID(String emp_id);
	int updateDeductionSalary(Salary salary);
	int updateAllowanceSalary(Salary salary);
	
	ArrayList<Payroll> holidayReport();
	int updateHoliday(String selectedid, String date, String event);
	int addHoliday(String date, String event);
	int getHolidaysinMonth(String datefirst,String datelast);
	int finalizeSalaryMonth(Salary salary, int status);
	
	
	Salary getMasterSalary(String emp_id,Pagination pagination);
	ArrayList<Salary> manageSalaryDetails(String month,String branchid, String status, Pagination pagination, LoginInfo loginInfo, String department, String newmonth);
    Allowance getAllowanceMaster(String type);
    Payroll getDeductionMaster(String type);
	int updateSalaryMasterSalary(Salary salary);
	int getLoginEmployee(String username, String password);
	
	int saveRequestLeave(Payroll payroll);
	Payroll getleaveDetails(String id);
	/*int updateAproveTransferLog(String id, String status, String aprove, String userid, String notes);*/
	int updateAproveLeaveLog(String id, String status, String aprove, String userid, String notes);
	int updateRejectLeave(String id, String status, String notes, String reject, String userid);
	int getTotalLeaveCount(String branchid, String empname, String string, String userid, LoginInfo loginInfo, String status, String fromDate, String toDate, String leavests, String leavetype);
	ArrayList<Payroll> getleaveDetailsPrint(String id);
	ArrayList<Salary> getAllSalaryDetails(String fromdate, String todate);
	public Salary getSalaryForAttendence(String emp_id);
	String totalSalaryForSlip(String emp_id);
	public String monthforSalarySlip(String emp_id);
	Salary getchangeworkdays(String emp_id, String str);
	/*int savemonthsalary(String month, String workDays, String netpay);*/
	public Salary getconvey(String emp_id);
	public Payroll getuserDetails(String id);
	int updateAproveHrLeaveLog(String id, String string, String hraprove, String userid, String notes);
	boolean checkExistRecord(Salary salary);
	int updateSalaryMonth(Salary salary);
	int updateFinalStatus(Salary salary, String Status, String tablename);
	int payrollInsertFinalSalary(Salary salary, Salary salary2);
	Salary getfinalReportData(String emp_id, String month, String year,String status, String tablename,Pagination pagination);
	int updatefinalizeSalary(Salary salary);
	int updatefinalsalarydeduction(Salary salary);
	int updatepayrollstatus(Salary salary);
	int updatecolumnallow(String empid, String month, String year, String colname, String value);
	int updategrossallow(String empid, String month, String year, double allownces, double grosspay, double netpay, double deduction);
	boolean checkExistRecordWithParam(String emp_id, String monthname, String year1);
	int payrollInsertFinalSalaryRpt(Salary salary, Salary salary2);
	boolean checkAttendanceDone(Salary salary, String month);
	ArrayList<Salary> getReportData(String empid, String empname, String month, String year, String dept, Pagination pagination);
	int getReportDatacount(String empid, String empname, String month, String year, String dept);
	int manageSalaryDetailscount(String month, String branchid, String status, Pagination pagination);
	String getweekdays();
	int updateweekdays(String weekday);
	ArrayList<Payroll> holidaylist(String id);
	double getWorkingDays(String string, String emp_id);
	int updateLeavedays(Payroll payroll, Employee employee);
	int getLeaveDays(String string, String emp_id);
	ArrayList<Payroll> getOTList(Pagination pagination, String userid, LoginInfo loginInfo, String fromDate,
			String toDate, String otstatus, String empname);
	int saveRequestOT(Payroll payroll);
	Payroll getotDetails(String id);
	int updateot(Payroll payroll, Employee employee, String sts);
	int updateRejectOt(String id, String notes, String reject, String userid, String sts);
	int getTotalOTlist(String userid, LoginInfo loginInfo, String fromDate, String toDate, String otstatus,
			String empname);
	String getdepartmentEmail(String department);
	int updateOT(Payroll payroll);
	ArrayList<Payroll> getAlldocumentlist();
	int adddocumentlist(Payroll payroll);
	Payroll getdocumentlist(String id);
	int updatedocumentlist(Payroll payroll);
	double getWorkingMinutes(String month, String emp_id);
	ArrayList<Salary> manageSalaryDetails1(String month, String branchid, String status, Pagination pagination,
			LoginInfo loginInfo, String department, String newmonth, String sal_date1, String new_sal_date);
}
