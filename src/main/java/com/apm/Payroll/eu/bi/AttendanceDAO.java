package com.apm.Payroll.eu.bi;

import java.util.ArrayList;

import com.apm.Payroll.eu.entity.Attendance;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface AttendanceDAO {

	int addOrUpdateWorkSheet(Attendance attendance);

	ArrayList<Attendance> getAllAttendanceList(String month, int weekno,String branchid, Pagination pagination, String searchemp, LoginInfo loginInfo, String numdate, int daysInMonth);
    int getTotalHours(String month,String emp_id);

	String getSalaryForAttendence(String emp_id);

	String totalSalaryForAttendence(String emp_id);

	int getAllAttendanceListcount(String month, int weekno, String branchid, String searchemp, LoginInfo loginInfo, String numdate, int daysInMonth);

	Attendance getListOfAttendance(String today, String userid, String empid);

	int insertPunchIn(String empid, String userid, String intime, String date, int status, String remark, String month_yr);

	boolean checktodaysentry(String today, LoginInfo loginInfo);

	int updatePunching(String empid, String userid, String intime, String date1, int status, String columnname, String remarkcol, String remark, String difftime, int timin, String month_yr);
	
	String differenceOftwoTimes(String dateStart,String dateStop);

	ArrayList<Attendance> getAllAttendanceListOfSelf(String attdate, String newmonth, String year, LoginInfo loginInfo, String empname);

	ArrayList<Attendance> getMonthWiseReport(String empid, String empname, String month, String year, int daysInMonth, int daysofMonth, Pagination pagination);

	int getMonthWiseReportCount(String empid, String empname, String tmp, String year, int daysInMonth, int dayOfMonth);

	int changepunchtime(String id, String val, String colname, Attendance attendance);

	Attendance getDayDetails(String id);

	boolean checktodayChecksentry(String date1, LoginInfo loginInfo);

	int inserCheckIn(String empid, String userid, String intime, String date1, int i, String remark);

	int getChildAttendanceid(String date1, String empid, String userid);

	int updateCheckin(String empid, String userid, String intime, String date1, String status, String string, String string2,
			String remark, int res, int parentid, String longlat);

	int insertChildCheckIn(String empid, String userid, String intime, String date1, int i, String remark, int parentid, String longlat);

	int getParentId(String date1, LoginInfo loginInfo, String empid);

	ArrayList<Attendance> getAllAttendanceListOfemp(String attdate, String newmonth, String year, LoginInfo loginInfo,
			String empname, String inpdate);

	Attendance getListOfMobileAttendance(String today, String userid, String empid);

	int updateCheckInStatus(int parentid, int sts);

	String getoutdatetime(int childid);

	int updateCheckout(String empid, String userid, String intime, String date1, String status, String string,
			String string2, String remark, int childid, int parentid, String longlat, String totalhour);

	ArrayList<Attendance> getmobileattendance(String empsearch, String fromDate, String toDate, Pagination pagination);

	Attendance getcheckInDetails(int childid, int parentid, String date1);

	String getintimefortotal(String empid, String userid, String date1);

	Attendance getpayroll_daytoday_attendance(String empid, String strDate);

	int insertIntopayroll_parent_request_timesheet(Attendance attendance1);

	int insertintopayroll_request_timesheet(Attendance attendance);

	ArrayList<Attendance> getrequesttimesheetdashboard(String fromDate, String toDate, String userid, LoginInfo loginInfo);

	Attendance getpayroll_child_request_attendance(String empid, String strDate, String parentid);

	Attendance getparentRequestTimesheet(String parentid);

	int updatepayroll_request_timesheet(Attendance attendance);

	int updatepayrollparentstatus(String parentid, String status);

	int updremarknduserid(String parentid, String notes, String colname, String totalhour);

	int updremarknduseriddate(String parentid, String notes, String colname, String userId, String date, String totalhour);

	int getleavestatus(String fromDate, String toDate, String empid);

	boolean getholidaystatus(String strDate);

	int insertBackdatepunch(String empid, String userid, String intime, int i, String indatetime,
			String outdatetime, String remark, String difftime, int timin, String month_yr);

	String getSalaryDatebyid();

	double getSumoftime(String sal_date, String new_sal_date, String empid);

	int insertDaysinworksheet(String new_sal_date, double totaldays, String userid, String empid, String month, double totalmin);

	Attendance getAttendanceOfMonth(Attendance attendance1, String month, int i, String string);
	
	
}
