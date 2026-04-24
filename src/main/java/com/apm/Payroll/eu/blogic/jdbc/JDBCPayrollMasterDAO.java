package com.apm.Payroll.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;


import com.apm.Inventory.eu.entity.Product;
import com.apm.Master.eu.entity.Master;
import com.apm.Payroll.eu.bi.AttendanceDAO;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.bi.PayrollMasterDAO;
import com.apm.Payroll.eu.entity.Allowance;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.Payroll.web.action.PayrollAllowanceAction;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCPayrollMasterDAO implements PayrollMasterDAO {

	Connection connection;
	private int numberofdayswork;

	public JDBCPayrollMasterDAO(Connection connection) {

		this.connection = connection;
	}

	public ArrayList<Payroll> getAllLoanlist(String branchid) {

		ArrayList<Payroll> list = new ArrayList<Payroll>();
		PayrollEmployeeDAO payrollEmployeeDAO = new JDBCPayrollEmployeeDAO(connection);
		String sql = "";
		try {

			if (branchid != null && branchid != "") {
				sql = "select id, emp_id, branch_id, amount, date, installments, deducton from apm_payroll_loan where branch_id="
						+ branchid + "";
			} else {
				sql = "select id, emp_id, branch_id, amount, date, installments, deducton from apm_payroll_loan";
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Payroll payroll = new Payroll();
				payroll.setId(rs.getInt(1));

				String emp_id = rs.getString(2);
				Employee employee = payrollEmployeeDAO.getEmployee(emp_id);
				payroll.setEmp_id(emp_id);
				payroll.setName(employee.getName());

				String branch_name = payrollEmployeeDAO.getBranchName(rs.getString(3));
				payroll.setBranch(branch_name);
				payroll.setAmount(rs.getString(4));
				payroll.setDate_format(rs.getString(5));
				payroll.setInstallments(rs.getString(6));
				payroll.setDeduction(rs.getString(7));
				list.add(payroll);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int addLoan(Payroll payroll) {

		int res = 0;

		try {
			String sql = "insert into apm_payroll_loan (emp_id, branch_id, amount, date, installments, deducton) values (?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, payroll.getName());
			ps.setString(2, payroll.getBranch());
			ps.setString(3, payroll.getAmount());
			ps.setString(4, payroll.getDate_format());
			ps.setString(5, payroll.getInstallments());
			ps.setString(6, payroll.getDeduction());

			res = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public HashSet<String> getAllEmployees() {

		HashSet<String> list = new HashSet<String>();

		try {

			String sql = "select emp_name from apm_payroll_loan";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString(1);
				list.add(name);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Payroll getLoan(String id) {

		Payroll payroll = new Payroll();

		try {

			String sql = "select emp_id, branch_id, amount, date, installments, deducton from apm_payroll_loan where id="
					+ id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				payroll.setName(rs.getString(1));
				payroll.setBranch(rs.getString(2));
				payroll.setAmount(rs.getString(3));
				payroll.setDate_format(rs.getString(4));
				payroll.setInstallments(rs.getString(5));
				payroll.setDeduction(rs.getString(6));
				payroll.setId(Integer.parseInt(id));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return payroll;
	}

	public int updateLoan(Payroll payroll) {

		int result = 0;

		try {

			String sql = "update apm_payroll_loan set emp_id=?, branch_id=?, amount=?, date=?, installments=?, deducton=? where id="
					+ payroll.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, payroll.getName());
			ps.setString(2, payroll.getBranch());
			ps.setString(3, payroll.getAmount());
			ps.setString(4, payroll.getDate_format());
			ps.setString(5, payroll.getInstallments());
			ps.setString(6, payroll.getDeduction());

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Payroll> getleaveList(String branchid, Pagination pagination, String empname, String Flag,
			String userid, LoginInfo loginInfo,String status,String fromDate, String toDate, String leavests, String leavetype) {

		String sql = "";
		ArrayList<Payroll> list = new ArrayList<Payroll>();

		PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);
		StringBuffer buffer = new StringBuffer();
		try {
			if (status.equals("")) {
				buffer.append(
						"Select id, name, leave_type, leave_reason, fromdate, todate, requesteddate, approveddate, approvedby, userid, status,days,comment from payroll_leave ");
				buffer.append("where requesteddate between '"+fromDate+"' and '"+toDate+"' ");
				if(!empname.equals("")){
					buffer.append("and name like '%"+empname+"%' ");
				}
				if(!leavests.equals("")){
					if(leavests.equals("1")){
					buffer.append("and status='1' and status='2' ");
				}else{
					buffer.append("and status='"+leavests+"' ");
				}
				} 
				if(!leavetype.equals("")){
					buffer.append("and leave_type='"+leavetype+"'");
				}
					
			} else {
				buffer.append(
						"Select id, name, leave_type, leave_reason, fromdate, todate, requesteddate, approveddate, approvedby, userid, status,days,comment from payroll_leave ");
				buffer.append("where userid='" + userid + "'");
				if (Flag.equals("1")) {
					buffer.append(" and status=3 ");
					
				}
				
			}
			buffer.append(" order by status asc , requesteddate desc");
			
			sql=buffer.toString();
			 if(pagination!=null)
				{
					sql=pagination.getSQLQuery(sql);
				}
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Payroll payroll = new Payroll();
				payroll.setId(rs.getInt(1));
				payroll.setName(rs.getString(2));
				payroll.setLeave_type(rs.getString(3));
				payroll.setLeave_reason(rs.getString(4));
				payroll.setFromdate(DateTimeUtils.getCommencingDatePayroll(rs.getString(5)));
				payroll.setTodate(DateTimeUtils.getCommencingDatePayroll(rs.getString(6)));
				/*payroll.setDate(rs.getString(7));*/
				String date = rs.getString(7);
				date = DateTimeUtils.getCommencingDate1(date);
				payroll.setDate(date);
				/*payroll.setApproveddate(rs.getString(8));*/
				String approveddate =rs.getString(8);
				if(approveddate!=null){
					approveddate=approveddate.split(" ")[0];
				}
				approveddate = DateTimeUtils.getCommencingDate1(approveddate);
				payroll.setApproveddate(approveddate);
				payroll.setApprovedby(rs.getString(9));
				payroll.setUserid(rs.getString(10));
				payroll.setStatus(rs.getString(11));
				payroll.setDays(rs.getString(12));
				payroll.setComment(rs.getString(13));
				list.add(payroll);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;
	}
	/*
	 * public ArrayList<Payroll> getleaveList(String branchid, ) { // TODO
	 * Auto-generated method stub return null; }
	 */

	/*
	 * public int addLeave(Payroll payroll) {
	 * 
	 * int result=0;
	 * 
	 * try {
	 * 
	 * String
	 * sql="insert into apm_payroll_leave (short_name, type, no_of_days, encashable, carryover,emp_id,branch_id) values (?,?,?,?,?,?,?)"
	 * ; PreparedStatement ps=connection.prepareStatement(sql); ps.setString(1,
	 * payroll.getShort_name()); ps.setString(2, payroll.getLeave_type());
	 * ps.setString(3, payroll.getNo_days()); ps.setString(4,
	 * payroll.getEncashable()); ps.setString(5, payroll.getCarryover());
	 * ps.setString(6, payroll.getName()); ps.setString(7, payroll.getBranch());
	 * 
	 * result=ps.executeUpdate();
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return result; }
	 */
	public int saveRequestLeave(Payroll payroll) {

		int result = 0;
		int res=0;
		try {
			String sql = "insert into payroll_leave (name,leave_type, leave_reason, fromdate, todate,requesteddate,days,userid,empid,hours,month) values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, payroll.getName());
			ps.setString(2, payroll.getLeave_type());

			ps.setString(3, payroll.getLeave_reason());
			ps.setString(4, payroll.getFromdate());
			ps.setString(5, payroll.getTodate());
			ps.setString(6, payroll.getRequestdate());
            ps.setString(7, payroll.getDays());
            ps.setString(8, payroll.getUserid());
            ps.setString(9, payroll.getEmp_id());
            ps.setString(10, payroll.getNo_hours());
            ps.setString(11, payroll.getMonth());
			result = ps.executeUpdate();
			if (result == 1) {
				ResultSet resultSet = ps.getGeneratedKeys();
				if (resultSet.next()) {
					res = resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Payroll getleaveDetails() {

		return null;
	}

	
	
	// for salary dashboard
	public ArrayList<Salary> getIncrementList(String branchid,String searchtext,Pagination pagination) {

		ArrayList<Salary> list = new ArrayList<Salary>();
		String sql = "";

		try {

			/*if (searchtext != null) {
				sql = "select empid,name from apm_payroll_employee where name like ('%"+searchtext+"%')order by name asc";
			} else {
				sql = "select empid,name from apm_payroll_employee order by name asc";
			}*/
			StringBuffer buffer = new StringBuffer();
			buffer.append("select empid,name,dept_name from apm_payroll_employee ");
            buffer.append("inner join apm_payroll_department on id=dept_id ");
            if(searchtext != null){
            	buffer.append("where name like ('%"+searchtext+"%') ");
            	buffer.append("or dept_name like ('%"+searchtext+"%') ");
            }
            buffer.append("order by name asc");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Salary salary = new Salary();
				Salary salary2 = getMasterSalary(rs.getString(1),pagination);
				salary.setEmp_name(rs.getString(2));
				/*salary.setEmp_id(salary2.getEmp_id());*/
				salary.setEmp_id(rs.getString(1));
				salary.setDepartment(rs.getString(3));
				salary.setBasic(salary2.getBasic());
				salary.setIncre_date(salary2.getDate());
				list.add(salary);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	public Salary getSalaryDetails(String emp_id) {

		Salary salary = new Salary();
		try {

			String sql = "select id,date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, taxable, other_income, net_pay, gross_sal, incre_date, branch_id,other_deduction,deductions,tds,professional_tax,medical_allowance,conveyance, washing, perdevallow, vehiclepass, fixedsalary from apm_payroll_salary where emp_id="
					+ emp_id + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				salary.setId(rs.getInt(1));
				salary.setDate(rs.getString(2));
				
				
				String salaryTotal = totalSalaryForSlip(emp_id);
				salary.setSalaryTotal(salaryTotal);
				
				salary.setBasic(nullwithzero(rs.getString(3)));
				salary.setAllowances(rs.getString(4));
				
				salary.setDa_on_ta(nullwithzero(rs.getString(5)));
				salary.setSpecial_pay(nullwithzero(rs.getString(6)));
				salary.setPersonal_pay(nullwithzero(rs.getString(7)));
				salary.setTransport_allowance(nullwithzero(rs.getString(8)));
				salary.setHra(nullwithzero(rs.getString(9)));
				salary.setDa(nullwithzero(rs.getString(10)));
				salary.setNpa(nullwithzero(rs.getString(11)));
				salary.setEmp_pf(nullwithzero(rs.getString(12)));
				salary.setEmp_esi(nullwithzero(rs.getString(13)));
				salary.setComp_pf(nullwithzero(rs.getString(14)));
				salary.setComp_esi(nullwithzero(rs.getString(15)));
				salary.setTaxable(nullwithzero(rs.getString(16)));
				salary.setOtherincome(nullwithzero(rs.getString(17)));
				salary.setNetpay(nullwithzero(rs.getString(18)));
				salary.setGross_pay(nullwithzero(rs.getString(19)));
				salary.setIncre_date(rs.getString(20));
				salary.setBranch_id(rs.getString(21));
				salary.setEmp_id(emp_id);
				salary.setOther_deduction(nullwithzero(rs.getString(22)));
				salary.setDeductions(nullwithzero(rs.getString(23)));
				salary.setTds(nullwithzero(rs.getString(24)));
				salary.setPrefessional_tax(nullwithzero(rs.getString(25)));
				salary.setMedical_allowance(nullwithzero(rs.getString(26)));
			
				salary.setConveyance(nullwithzero(rs.getString(27)));
				salary.setWashing(nullwithzero(rs.getString(28)));
				salary.setFixedsalary(rs.getString(31));
				salary.setPerdevallow(nullwithzero(rs.getString(29)));
				salary.setVehiclepass(nullwithzero(rs.getString(30)));
  
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return salary;
	}

	private String nullwithzero(String param) {
		if(param==null){
			param="0";
		}else if(param.equals("")){
			param="0";
		}
		return param;
	}

	public int updateSalary(Salary salary,String tablename) {

		int result = 0;
		try {

			String sql = "update "+tablename+" set basic=?, allowances=?, da_on_ta=?, special_pay=?, personal_pay=?, tarnsport_allowance=?, hra=?, da=?, npa=?, emp_pf=?, emp_esi=?, comp_pf=?, comp_esi=?, taxable=?, other_income=?, net_pay=?, gross_sal=?, incre_date=?,other_deduction=?,deductions=?,tds=?,professional_tax=?,medical_allowance=?,conveyance=?,washing=?,perdevallow=?,vehiclepass=?,fixedsalary=? where emp_id="
					+ salary.getEmp_id() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, salary.getBasic());
			ps.setString(2, salary.getAllowances());
			ps.setString(3, salary.getDa_on_ta());
			ps.setString(4, salary.getSpecial_pay());
			ps.setString(5, salary.getPersonal_pay());
			ps.setString(6, salary.getTransport_allowance());
			ps.setString(7, salary.getHra());
			ps.setString(8, salary.getDa());
			ps.setString(9, salary.getNpa());
			ps.setString(10, salary.getEmp_pf());
			ps.setString(11, salary.getEmp_esi());
			ps.setString(12, salary.getComp_pf());
			ps.setString(13, salary.getComp_esi());
			ps.setString(14, salary.getTaxable());
			ps.setString(15, salary.getOtherincome());
			ps.setString(16, salary.getNetpay());
			ps.setString(17, salary.getGross_pay());
			ps.setString(18, salary.getIncre_date());
			ps.setString(19, salary.getOther_deduction());
			ps.setString(20, salary.getDeductions());
			ps.setString(21, salary.getTds());
			ps.setString(22, salary.getPrefessional_tax());
			ps.setString(23, salary.getMedical_allowance());
			ps.setString(24, salary.getConveyance());
			ps.setString(25, salary.getWashing());
			ps.setString(26, salary.getPerdevallow());
			ps.setString(27, salary.getVehiclepass());
			ps.setString(28, salary.getFixedsalary());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Salary> getAllSalaryList(String branchid, int emp_id) {

		ArrayList<Salary> list = new ArrayList<Salary>();

		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		int total_days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);
		AttendanceDAO attendanceDAO = new JDBCAttendanceDAO(connection);

		String sql = "";
		try {

			if (branchid != null && branchid != "") {
				sql = "select id, emp_id, date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, taxable, other_income, net_pay, gross_sal, incre_date, branch_id, other_deduction, deductions, tds, professional_tax,conveyance, washing, perdevallow, vehiclepass, fixedsalary from apm_payroll_salary where branch_id="
						+ branchid + "";

			} else if (emp_id == 0 && (branchid == null || branchid == "")) {
				sql = "select id, emp_id, date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, taxable, other_income, net_pay, gross_sal, incre_date, branch_id, other_deduction, deductions, tds, professional_tax,conveyance, washing, perdevallow, vehiclepass, fixedsalary from apm_payroll_salary";
			} else {
				sql = "select id, emp_id, date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, taxable, other_income, net_pay, gross_sal, incre_date, branch_id, other_deduction, deductions, tds, professional_tax,conveyance, washing, perdevallow, vehiclepass, fixedsalary from apm_payroll_salary where emp_id="
						+ emp_id + "";
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			int leavedays = 0;
			int work_days = 0;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Salary salary = new Salary();
				salary.setId(rs.getInt(1));
				salary.setEmp_id(rs.getString(2));
				salary.setDate(rs.getString(3));
				salary.setBasic(rs.getString(4));
				salary.setAllowances(rs.getString(5));
				salary.setDa_on_ta(rs.getString(6));
				salary.setSpecial_pay(rs.getString(7));
				salary.setPersonal_pay(rs.getString(8));
				salary.setTransport_allowance(rs.getString(9));
				salary.setHra(rs.getString(10));
				salary.setDa(rs.getString(11));
				salary.setNpa(rs.getString(12));
				salary.setEmp_pf(rs.getString(13));
				salary.setEmp_esi(rs.getString(14));
				salary.setComp_pf(rs.getString(15));
				salary.setComp_esi(rs.getString(16));
				salary.setTaxable(rs.getString(17));
				salary.setOtherincome(rs.getString(18));
				salary.setNetpay(rs.getString(19));
				salary.setGross_pay(rs.getString(20));
				salary.setIncre_date(rs.getString(21));
				salary.setBranch_id(rs.getString(22));
				salary.setOther_deduction(rs.getString(23));
				salary.setDeductions(rs.getString(24));
				salary.setTds(rs.getString(25));
				salary.setPrefessional_tax(rs.getString(26));
salary.setConveyance(rs.getString(27));
salary.setWashing(rs.getString(28));
salary.setPerdevallow(rs.getString(29));
salary.setVehiclepass(rs.getString(30));
salary.setFixedsalary(rs.getString(31));
				Payroll payroll = getLeaveFormEmpID(salary.getEmp_id());

				if (payroll == null) {
					leavedays = 0;
				} else {

					if (payroll.getNo_days() == null) {
						leavedays = 0;
					} else if (payroll.getNo_days().equals("")) {
						leavedays = 0;
					} else {
						leavedays = Integer.parseInt(payroll.getNo_days());
					}
				}

				salary.setTotaldays(String.valueOf(total_days));

				Date date = Calendar.getInstance(Locale.getDefault()).getTime();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
				String month = dateFormat.format(date);

				int total_hrs = attendanceDAO.getTotalHours(month, salary.getEmp_id());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				calendar.set(Calendar.DAY_OF_MONTH, 1);

				Date date1 = calendar.getTime();
				String datefirst = format.format(date1);

				calendar.set(Calendar.DAY_OF_MONTH, days);
				Date date2 = calendar.getTime();
				String datelast = format.format(date2);

				int holidaysinmonth = getHolidaysinMonth(datefirst, datelast);

				// calculation

				int numberofdayswork = total_hrs / 8;
				int basic = Integer.parseInt(salary.getBasic());
				int rate = basic / 30;
				int origialsal = numberofdayswork * rate;
				salary.setBasic(String.valueOf(origialsal));

				int hra = 0, da = 0, da_hra = 0, npa = 0, personal = 0;
				int special_pay = 0, transport_alo = 0;

				int other_income = 0;

				if (salary.getHra() != null) {

					if (!salary.getHra().equals("")) {
						hra = Integer.parseInt(salary.getHra());
					}
				}
				if (salary.getDa() != null) {

					if (!salary.getDa().equals("")) {
						da = Integer.parseInt(salary.getDa());
					}
				}

				if (salary.getDa_on_ta() != null) {
					if (!salary.getDa_on_ta().equals("")) {
						da_hra = Integer.parseInt(salary.getDa_on_ta());
					}

				}
				if (salary.getNpa() != null) {
					if (!salary.getNpa().equals("")) {
						npa = Integer.parseInt(salary.getNpa());
					}

				}
				if (salary.getPersonal_pay() != null) {

					if (!salary.getPersonal_pay().equals("")) {
						personal = Integer.parseInt(salary.getPersonal_pay());
					}

				}
				if (salary.getSpecial_pay() != null) {

					if (!salary.getSpecial_pay().equals("")) {
						special_pay = Integer.parseInt(salary.getSpecial_pay());
					}

				}
				if (salary.getTransport_allowance() != null) {

					if (!salary.getTransport_allowance().equals("")) {
						transport_alo = Integer.parseInt(salary.getTransport_allowance());
					}
				}

				if (salary.getOtherincome() != null) {

					if (!salary.getOtherincome().equals("")) {
						other_income = Integer.parseInt(salary.getOtherincome());
					}
				}

				int tot_allowances = hra + da + da_hra + npa + personal + special_pay + transport_alo + other_income;
				salary.setAllowances(String.valueOf(tot_allowances));

				int grosspay = tot_allowances + origialsal;
				salary.setGross_pay(String.valueOf(grosspay));

				int emp_pf = 0, emp_esi = 0, pt = 0, tds = 0, other_deduction = 0;

				if (salary.getEmp_pf() != null) {

					if (!salary.getEmp_pf().equals("")) {

						emp_pf = Integer.parseInt(salary.getEmp_pf());
					}

				}
				if (salary.getEmp_esi() != null) {

					if (!salary.getEmp_esi().equals("")) {
						emp_esi = Integer.parseInt(salary.getEmp_esi());
					}

				}
				if (salary.getPrefessional_tax() != null) {

					if (!salary.getPrefessional_tax().equals("")) {

						pt = Integer.parseInt(salary.getPrefessional_tax());
					}

				}

				if (salary.getTds() != null) {
					if (!salary.getTds().equals("")) {

						tds = Integer.parseInt(salary.getTds());
					}
				}
				if (salary.getOther_deduction() != null) {

					if (!salary.getOther_deduction().equals("")) {
						other_deduction = Integer.parseInt(salary.getOther_deduction());
					}

				}

				int deductions = emp_esi + emp_pf + pt + tds + other_deduction;
				salary.setDeductions(String.valueOf(deductions));

				int netpay = grosspay - deductions;
				salary.setNetpay(String.valueOf(netpay));

				work_days = total_days - getNumberOfSundays() - holidaysinmonth;
				salary.setWorkingdays(String.valueOf(work_days));
				salary.setTotal_leaves(String.valueOf(leavedays));

				salary.setHolidays(String.valueOf(holidaysinmonth));
				salary.setWorkeddays(String.valueOf(numberofdayswork));

				Employee employee = employeeDAO.getEmployee(salary.getEmp_id());
				salary.setEmp_name(employee.getName());

				salary.setDeductions(rs.getString(10));
				Payroll loan = getLoanFromEmpID(salary.getEmp_id());
				salary.setLoan(loan.getAmount());

				// updateSalary(salary);

				list.add(salary);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Payroll getLeave(String id) {

		Payroll payroll = new Payroll();

		try {

			String sql = "select short_name, type, no_of_days, encashable, carryover, emp_id, branch_id from apm_payroll_leave where id="
					+ id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				payroll.setShort_name(rs.getString(1));
				payroll.setLeave_type(rs.getString(2));
				payroll.setNo_days(rs.getString(3));
				payroll.setEncashable(rs.getString(4));
				payroll.setCarryover(rs.getString(5));
				payroll.setEmp_id(rs.getString(6));
				payroll.setBranch(rs.getString(7));
				payroll.setId(Integer.parseInt(id));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return payroll;
	}

	public int updateLeave(Payroll payroll) {

		int result = 0;

		try {

			String sql = "update payroll_leave set  leave_type=?, leave_reason=?, fromdate=?, todate=?, days=? ,hours=? where id="
					+ payroll.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setString(1, payroll.getName());
			ps.setString(1, payroll.getLeave_type());
			ps.setString(2, payroll.getLeave_reason());
			ps.setString(3, payroll.getFromdate());
			ps.setString(4, payroll.getTodate());
			ps.setString(5, payroll.getDays());
			ps.setString(6, payroll.getNo_hours());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Payroll getLeaveFormEmpID(String emp_id) {

		Payroll payroll = new Payroll();

		try {

			String sql = "select id, short_name, type, no_of_days, encashable, carryover, branch_id, status from apm_payroll_leave where emp_id="
					+ emp_id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				payroll.setId(rs.getInt(1));
				payroll.setShort_name(rs.getString(2));
				payroll.setLeave_type(rs.getString(3));
				payroll.setNo_days(rs.getString(4));
				payroll.setEncashable(rs.getString(5));
				payroll.setCarryover(rs.getString(6));
				payroll.setBranch(rs.getString(7));
				payroll.setState(rs.getString(8));
				payroll.setEmp_id(emp_id);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return payroll;
	}

	public Payroll getLoanFromEmpID(String emp_id) {

		Payroll payroll = new Payroll();

		try {

			String sql = "select id,branch_id, amount, date, installments, deducton from apm_payroll_loan where emp_id="
					+ emp_id + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				payroll.setId(rs.getInt(1));
				payroll.setBranch(rs.getString(2));
				payroll.setAmount(rs.getString(3));
				payroll.setDate_format(rs.getString(4));
				payroll.setInstallments(rs.getString(5));
				payroll.setDeduction(rs.getString(6));
				payroll.setEmp_id(emp_id);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return payroll;
	}

	public int updateDeductionSalary(Salary salary) {

		int result = 0;
		try {

			String sql = "update apm_payroll_salary set basic=?,allowances=?,emp_pf=?,emp_esi=?,net_pay=?,gross_sal=?,incre_date=?,other_deduction=?,deductions=?,taxable=?,tds=?,professional_tax=? where emp_id="
					+ salary.getEmp_id() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, salary.getBasic());
			ps.setString(2, salary.getAllowances());
			ps.setString(3, salary.getEmp_pf());
			ps.setString(4, salary.getEmp_esi());
			ps.setString(5, salary.getNetpay());
			ps.setString(6, salary.getGross_pay());
			ps.setString(7, salary.getDate());
			ps.setString(8, salary.getOther_deduction());
			ps.setString(9, salary.getDeductions());
			ps.setString(10, salary.getTaxable());
			ps.setString(11, salary.getTds());
			ps.setString(12, salary.getPrefessional_tax());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateAllowanceSalary(Salary salary) {

		int result = 0;

		try {

			String sql = "update apm_payroll_salary set basic=?,allowances=?, da_on_ta=?, special_pay=?, personal_pay=?, tarnsport_allowance=?, hra=?, da=?, npa=?,deductions=?,gross_sal=?,net_pay=?,incre_date=?, conveyance=?, washing=?, perdevallow=? where emp_id="
					+ salary.getEmp_id() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, salary.getBasic());
			ps.setString(2, salary.getAllowances());
			ps.setString(3, salary.getDa_on_ta());
			ps.setString(4, salary.getSpecial_pay());
			ps.setString(5, salary.getPersonal_pay());
			ps.setString(6, salary.getTransport_allowance());
			ps.setString(7, salary.getHra());
			ps.setString(8, salary.getDa());
			ps.setString(9, salary.getNpa());
			ps.setString(10, salary.getDeductions());
			ps.setString(11, salary.getGross_pay());
			ps.setString(12, salary.getNetpay());
			ps.setString(13, salary.getDate());
			ps.setString(14, salary.getConveyance());
			ps.setString(15, salary.getWashing());
			ps.setString(16, salary.getPerdevallow());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
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

	public ArrayList<Payroll> holidayReport() {

		ArrayList<Payroll> list = new ArrayList<Payroll>();

		try {
			String sql = "select * from apm_payroll_holiday";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Payroll payroll = new Payroll();
				payroll.setId(rs.getInt("id"));
				payroll.setDate(DateTimeUtils.getCommencingDatePayroll(rs.getString("date")));
				payroll.setEvent(rs.getString("event"));
				list.add(payroll);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int updateHoliday(String selectedid, String date, String event) {

		int result = 0;

		try {

			String sql = "update apm_payroll_holiday set date=?,event=? where id='" + selectedid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, event);

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int addHoliday(String date, String event) {

		int result = 0;

		try {

			String sql = "insert into apm_payroll_holiday (date,event) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, event);

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int getHolidaysinMonth(String datefirst, String datelast) {

		int result = 0;

		try {

			String sql = "select count(*) from apm_payroll_holiday where date between '" + datefirst + "' and '"
					+ datelast + "'";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				result = rs.getInt(1);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int finalizeSalaryMonth(Salary salary,int status) {

		int result = 0;
		PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);
		try {
			String sql = "insert into apm_payroll_salary_monthly (emp_id, sal_id, year, month, workdays, totaldays, holidays, deductions, salary, status, carryforward,finalstatus,userid) values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, salary.getEmp_id());
			ps.setString(2, salary.getSalaryId());
			ps.setString(3, salary.getYear());
			ps.setString(4, salary.getMonth());
			ps.setString(5, salary.getWorkeddays());
			ps.setString(6, salary.getTotaldays());
			ps.setString(7, salary.getHolidays());
			ps.setString(8, salary.getDeductions());
			ps.setString(9, salary.getCalnetpay());
			ps.setString(10, "Paid");
			ps.setString(11, "No");
			ps.setInt(12, status);
			String userid=employeeDAO.getEmployeeUserId(salary.getEmp_id());
			ps.setString(13, userid);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public ArrayList<Salary> manageSalaryDetails(String month, String branchid,String status,Pagination pagination,LoginInfo loginInfo,String department,String newmonth) {
		DecimalFormat df = new DecimalFormat("0.00");
		ArrayList<Salary> list = new ArrayList<Salary>();
		AttendanceDAO attendanceDAO = new JDBCAttendanceDAO(connection);
		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
		PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);
		String monthname=month.split("-")[0];
		String year1=month.split("-")[1];
		String sql = "";
		if(status==null || status.equals("0")){
			status="";
		}
		
		if(department==null || department.equals("0")){
			department="";
		}
		String newstatus=status;
		if(newstatus.equals("1")){
			newstatus="Processed";
		}
		if(newstatus.equals("2")){
			newstatus="Paid";
		}
		if(!loginInfo.isPayrollaccess()){
			newstatus="Paid";
			status="2";
		}
		StringBuffer buffer =new StringBuffer();
		try {

			buffer.append("select apm_payroll_salary_master.emp_id, apm_payroll_salary_master.basic,apm_payroll_salary_master.net_pay from apm_payroll_salary_master ");
			buffer.append("inner join apm_payroll_weeksheet on apm_payroll_weeksheet.emp_id=apm_payroll_salary_master.emp_id ");
			buffer.append("inner join apm_payroll_employee on apm_payroll_salary_master.emp_id=apm_payroll_employee.empid ");
			if(!newstatus.equals("")){
			buffer.append("inner join apm_payroll_final_salary on apm_payroll_final_salary.emp_id=apm_payroll_salary_master.emp_id ");
			}
			buffer.append("where apm_payroll_weeksheet.month='"+month+"' and days>0  ");
			if(!newstatus.equals("")){
				buffer.append("and apm_payroll_final_salary.month='"+monthname+"' and apm_payroll_final_salary.year='"+year1+"' ");
			buffer.append("and apm_payroll_final_salary.finalstatus='"+newstatus+"' ");
			}
			if(!loginInfo.isPayrollaccess()){
				buffer.append(" and apm_payroll_final_salary.userid='"+loginInfo.getUserId()+"'");
			}
			if(!department.equals("")){
				buffer.append(" and apm_payroll_employee.dept_id='"+department+"' ");
			}
			
			buffer.append(" group by apm_payroll_weeksheet.emp_id ");
//				sql = "select emp_id, basic from apm_payroll_salary_master";
			sql=buffer.toString();
			 if(pagination!=null)
				{
					sql=pagination.getSQLQuery(sql);
				}
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Salary salary = new Salary();
				salary.setEmp_id(rs.getString(1));
				Salary master=new Salary();
				boolean checkAttend=payrollMasterDAO.checkAttendanceDone(salary,month);
				
				if(status=="" || status.equals("0")){
					boolean checkExist=payrollMasterDAO.checkExistRecordWithParam(salary.getEmp_id(),monthname,year1);
					if(!checkExist){
				 master = getMasterSalary(salary.getEmp_id(),pagination);
					}else{
					// master=getfinalReportData(salary.getEmp_id(), monthname, year1,status,"apm_payroll_final_salary");
						
						master=getfinalReportData(salary.getEmp_id(), monthname, year1,status,"apm_payroll_final_salary",pagination);
				}
				}
				//else{
				 //master=getfinalReportData(salary.getEmp_id(), monthname, year1,status,"apm_payroll_final_salary",pagination);
				//}
				salary.setStatus(master.getStatus());
				if(master.isChkobj() && checkAttend){
				Employee employee = employeeDAO.getEmployee(salary.getEmp_id());
				salary.setEmp_name(employee.getName());
				salary.setDesignation(employee.getDesignation());
				salary.setDatejoin(employee.getDate_join());
				salary.setEmail(employee.getEmail());
				salary.setEmpcode(employee.getEmpcode());
				salary.setNetpay(master.getNetpay());
				Salary sal = payrollMasterDAO.getSalaryDetails(salary.getEmp_id());
				salary.setId(sal.getId());
				if(master.getBasic()!=null){
				int perhourrate = Integer.parseInt(master.getBasic()) / 30;
				}
               /* salary.setDays(days);*/
				/*int total_hours = attendanceDAO.getTotalHours(month, salary.getEmp_id());
				float numberofdayswork = total_hours / 8;*/
				double numdays = getDaysForSalary(salary.getEmp_id(),month);
				numberofdayswork = (int) numdays;

				/*String salaryTotal = attendanceDAO.totalSalaryForAttendence(salary.getEmp_id(),month);
				salary.setSalaryTotal(salaryTotal);*/
				double basic=0;
//				String basic1 =salary.getBasic();
				if(master.getBasic()!=null){
				 basic = Double.parseDouble(master.getBasic());
				}else{
					basic=0;
				}
				salary.setBasic(String.valueOf(basic));
//				salary.setBasic(String.valueOf(master.getBasic())); // origianl sal
				
	
				// DA on TA

				Allowance da_on_ta = getAllowanceMaster("DA ON TA");
				String daonta = "";

				if (da_on_ta.getAllowanceType() != null) {

					if (!da_on_ta.getAllowanceType().equals("")) {

						String fixeorrate = da_on_ta.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(da_on_ta.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							daonta = String.valueOf(amount);
						} else {

							daonta = da_on_ta.getValue();
						}

					} else {
						daonta = "0";
					}
				} else {

					daonta = "0";
				}
				if(daonta.equals("0")){
					salary.setDa_on_ta((master.getDa_on_ta()));
				}else{
				salary.setDa_on_ta(daonta);
				}
				// for special pay

				Allowance special_pay = getAllowanceMaster("SPECIAL PAY");
				String specialpay = "";

				if (special_pay.getAllowanceType() != null) {

					if (!special_pay.getAllowanceType().equals("")) {

						String fixeorrate = special_pay.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(special_pay.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							specialpay = String.valueOf(amount);
						} else {

							specialpay = special_pay.getValue();
						}

					} else {
						specialpay = "0";
					}
				} else {

					specialpay = "0";
				}
				if(special_pay.equals("0")){
					salary.setSpecial_pay(master.getSpecial_pay());
				}else{
					
				
				salary.setSpecial_pay(specialpay);
				}
				// personal pay

				Allowance personal_pay = getAllowanceMaster("PERSONAL PAY");
				String personalpay = "";

				if (personal_pay.getAllowanceType() != null) {

					if (!personal_pay.getAllowanceType().equals("")) {

						String fixeorrate = personal_pay.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(personal_pay.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							personalpay = String.valueOf(amount);
						} else {

							personalpay = personal_pay.getValue();
						}

					} else {
						personalpay = "0";
					}
				} else {

					personalpay = "0";
				}
				if(personalpay.equals("0")){
					salary.setPersonal_pay(master.getPersonal_pay());
				}else{
				salary.setPersonal_pay(personalpay);
				}
				// Transport Allowance

				Allowance transport_allowance = getAllowanceMaster("TRANSPORT ALLOWANCE");
				String transportallowance = "";

				if (transport_allowance.getAllowanceType() != null) {

					if (!transport_allowance.getAllowanceType().equals("")) {

						String fixeorrate = transport_allowance.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(transport_allowance.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							transportallowance = String.valueOf(amount);
						} else {

							transportallowance = transport_allowance.getValue();
						}

					} else {
						transportallowance = "0";
					}
				} else {

					transportallowance = "0";
				}
				if(transport_allowance.equals("0")){
					salary.setTransport_allowance(master.getTransport_allowance());
				}else{
				salary.setTransport_allowance(transportallowance);
				}
				// hra

				Allowance hra = getAllowanceMaster("HRA");
				String HRA = "";

				if (hra.getAllowanceType() != null) {

					if (!hra.getAllowanceType().equals("")) {

						String fixeorrate = hra.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(hra.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							HRA = String.valueOf(amount);
						} else {

							HRA = hra.getValue();
						}

					} else {
						HRA = "0";
					}
				} else {

					HRA = "0";
				}
				if(HRA.equals("0")){
					salary.setHra(master.getHra());
				}else {
					salary.setHra(HRA);	
				}
				

				// da

				Allowance da = getAllowanceMaster("DA");
				String DA = "";

				if (da.getAllowanceType() != null) {

					if (!da.getAllowanceType().equals("")) {

						String fixeorrate = da.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(da.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							DA = String.valueOf(amount);
						} else {

							DA = da.getValue();
						}

					} else {
						DA = "0";
					}
				} else {

					DA = "0";
				}
				if(DA.equals("0")){
					salary.setDa(master.getDa());	
				}else{
				salary.setDa(DA);
				}
				// npa

				Allowance npa = getAllowanceMaster("NPA");
				String NPA = "";

				if (npa.getAllowanceType() != null) {

					if (!npa.getAllowanceType().equals("")) {

						String fixeorrate = npa.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(npa.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							NPA = String.valueOf(amount);
						} else {

							NPA = npa.getValue();
						}

					} else {
						NPA = "0";
					}
				} else {

					NPA = "0";
				}
				if(npa.equals("0")){
					salary.setNpa(master.getNpa());
				}else{
				salary.setNpa(NPA);
				}
				// Medical Allowance

				Allowance mediacal_allowance = getAllowanceMaster("MEDICAL AllOWANCE");
				String mediacalallowance = "";

				if (mediacal_allowance.getAllowanceType() != null) {

					if (!mediacal_allowance.getAllowanceType().equals("")) {

						String fixeorrate = mediacal_allowance.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(mediacal_allowance.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							mediacalallowance = String.valueOf(amount);
						} else {

							mediacalallowance = mediacal_allowance.getValue();
						}

					} else {
						mediacalallowance = "0";
					}
				} else {

					mediacalallowance = "0";
				}
				if(mediacalallowance.equals("0")){
					salary.setMedical_allowance(master.getMedical_allowance());
				}else{
				salary.setMedical_allowance(mediacalallowance);
				}
				// deductions
				// emp_pf

				Payroll emp_pf = getDeductionMaster("EMP PF");
				String emppf = "";

				if (emp_pf.getDeduction_type() != null) {

					if (!emp_pf.getDeduction_type().equals("")) {

						String fixeorrate = emp_pf.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(emp_pf.getEmp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							emppf = String.valueOf(amount);
						} else {

							emppf = emp_pf.getEmp_contribution();
						}

					} else {
						emppf = "0";
					}
				} else {

					emppf = "0";
				}
				if(emppf.equals("0")){
					salary.setEmp_pf(master.getEmp_pf());
				}else{
				salary.setEmp_pf(emppf);
				}
				// emp_esi

				Payroll emp_esi = getDeductionMaster("EMP ESI");
				String empesi = "";

				if (emp_esi.getDeduction_type() != null) {

					if (!emp_esi.getDeduction_type().equals("")) {

						String fixeorrate = emp_esi.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(emp_esi.getEmp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							empesi = String.valueOf(amount);
						} else {

							empesi = emp_esi.getEmp_contribution();
						}

					} else {
						empesi = "0";
					}
				} else {

					empesi = "0";
				}
				if(empesi.equals("0")){
					salary.setEmp_esi(master.getEmp_esi());
				}else{
				salary.setEmp_esi(empesi);
				}
				// comp_pf

				Payroll comp_pf = getDeductionMaster("COMPANY PF");
				String comppf = "";

				if (comp_pf.getDeduction_type() != null) {

					if (!comp_pf.getDeduction_type().equals("")) {

						String fixeorrate = comp_pf.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(comp_pf.getComp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							comppf = String.valueOf(amount);
						} else {

							comppf = comp_pf.getComp_contribution();
						}

					} else {
						comppf = "0";
					}
				} else {

					comppf = "0";
				}
				if(comppf.equals("0")){
					salary.setComp_pf(master.getComp_pf());
				}else{
				salary.setComp_pf(comppf);
				}
				// comp esi

				Payroll comp_esi = getDeductionMaster("COMPANY ESI");
				String compesi = "";

				if (comp_esi.getDeduction_type() != null) {

					if (!comp_esi.getDeduction_type().equals("")) {

						String fixeorrate = comp_esi.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(comp_esi.getComp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							compesi = String.valueOf(amount);
						} else {

							compesi = comp_esi.getComp_contribution();
						}

					} else {
						compesi = "0";
					}
				} else {

					compesi = "0";
				}
				if(compesi.equals("0")){
					salary.setComp_esi(master.getComp_esi());
				}else{
				salary.setComp_esi(compesi);
				}
				// Professional Tax

				Payroll pt = getDeductionMaster("PROFESSIONAL TAX");
				String PT = "";

				if (pt.getDeduction_type() != null) {

					if (!pt.getDeduction_type().equals("")) {

						String fixeorrate = pt.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(pt.getEmp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							PT = String.valueOf(amount);
						} else {

							PT = pt.getEmp_contribution();
						}

					} else {
						PT = "0";
					}
				} else {

					PT = "0";
				}
				if(PT.equals("0")){
					salary.setPrefessional_tax(master.getPrefessional_tax());
				}else{
				salary.setPrefessional_tax(PT);
				}
				//conveyance

				Payroll cy = getDeductionMaster("conveyance");
				String CY = "";

				if (cy.getDeduction_type() != null) {

					if (!cy.getDeduction_type().equals("")) {

						String fixeorrate = cy.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(pt.getEmp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							PT = String.valueOf(amount);
						} else {

							CY = cy.getEmp_contribution();
						}

					} else {
						CY = "0";
					}
				} else {

					CY = "0";
				}
				if(CY.equals("0")){
					salary.setConveyance(master.getConveyance());
				}else{
					salary.setConveyance(CY);
				}
				
				
				
				
//				if (sal.getConveyance()!=null) {
//
//					if (!sal.getConveyance().equals("")) {
//						salary.setConveyance(salary.getConveyance());
//
//						} else {
//
//							salary.setConveyance("0");
//						}
//
//					} else {
//						salary.setConveyance("0");
//					}
//
//				if(salary.getConveyance().equals("0")){
//					salary.setConveyance(master.getConveyance());
//
//				}
				//leaves
				if (sal.getLeave()!=null) {

					if (!sal.getLeave().equals("")) {
						salary.setLeave(sal.getConveyance());

						} else {

							salary.setLeave("0");
						}

					} else {
						salary.setLeave("0");
					}

				if(salary.getLeave().equals("0")){
					salary.setLeave(master.getLeave());

				}
				// other income

				if (sal.getOtherincome() != null) {

					if (!sal.getOtherincome().equals("")) {
						salary.setOtherincome(sal.getOtherincome());
					} else {
						salary.setOtherincome("0");
					}
				} else {
					salary.setOtherincome("0");
				}
				if(salary.getOtherincome().equals("0")){
					salary.setOtherincome(master.getOtherincome());
				}
				// other deductions

				if (sal.getOther_deduction() != null) {

					if (!sal.getOther_deduction().equals("")) {
						salary.setOther_deduction(sal.getOther_deduction());
					} else {
						salary.setOther_deduction("0");
					}
				} else {
					salary.setOther_deduction("0");
				}
				if(salary.getOther_deduction().equals("0")){
					salary.setOther_deduction(master.getOther_deduction());
				}
				if (sal.getTds() != null) {

					if (!sal.getTds().equals("")) {
						salary.setTds(sal.getTds());
					} else {
						salary.setTds("0");
					}
				} else {
					salary.setTds("0");
				}
				if(salary.getTds().equals("0")){
					salary.setTds(master.getTds());
				}
				if(master.getLoan()==null){
					
				}else if(master.getLoan().equals("")){
					master.setLoan("0");
				}else{
					salary.setLoan(master.getLoan());	
				}
				// deductions

				int emp_pff = Integer.parseInt(nullwithzero(salary.getEmp_pf()));
				int emp_esii = Integer.parseInt(nullwithzero(salary.getEmp_esi()));
				int cmp_pff = Integer.parseInt(nullwithzero(salary.getComp_pf()));
				int cmp_esii = Integer.parseInt(nullwithzero(salary.getComp_esi()));
				int ptt = Integer.parseInt(nullwithzero(salary.getPrefessional_tax()));
				int otherr = Integer.parseInt(nullwithzero(salary.getOther_deduction()));
				double tdss = Double.parseDouble(nullwithzero(salary.getTds()));
				double leave = Double.parseDouble((nullwithzero(salary.getLeave())));
				double loan = Double.parseDouble((nullwithzero(salary.getLoan())));
				double tota = emp_pff + emp_esii + cmp_pff + cmp_esii + ptt + otherr + tdss+leave+loan;
				salary.setDeductions(String.valueOf(tota));
				if(master.getPerdevallow()==null){
					
				}else if(master.getPerdevallow().equals("")){
					master.setPerdevallow("0");
				}else{
					salary.setPerdevallow(master.getPerdevallow());	
				}
				if(master.getOt()==null){
					
				}else if(master.getOt().equals("")){
					master.setOt("0");
				}else{
					salary.setOt(master.getOt());	
				}
				
				
				if(master.getAdvance()==null){
									
				}else if(master.getAdvance().equals("")){
							master.setAdvance("0");
				}else{
									salary.setAdvance(master.getAdvance());	
				}
				
				
				
				// Allowances

				double daontaa = Double.parseDouble(nullwithzero(salary.getDa_on_ta()));
				double special = Double.parseDouble(nullwithzero(salary.getSpecial_pay()));
				int perosnal = Integer.parseInt(nullwithzero(salary.getPersonal_pay()));
				int transport = Integer.parseInt(nullwithzero(salary.getTransport_allowance()));
				double hraa = Double.parseDouble(nullwithzero(salary.getHra()));
				double daa = Double.parseDouble(nullwithzero(salary.getDa()));
				int npaa = Integer.parseInt(nullwithzero(salary.getNpa()));
				int otherrr = Integer.parseInt(nullwithzero(salary.getOtherincome()));
				double convey=Double.parseDouble(nullwithzero(salary.getConveyance()));
				double perdevallow=Double.parseDouble(nullwithzero(salary.getPerdevallow()));
				double medical=Double.parseDouble(nullwithzero(salary.getMedical_allowance()));
				double ot = Double.parseDouble(nullwithzero(salary.getOt()));
				double advance = Double.parseDouble(nullwithzero(salary.getAdvance()));
				
				String mon="";
				String mont_yr="";
				int monthindex=Integer.parseInt(newmonth)+1;
				if(monthindex !=10 && monthindex !=11 && monthindex !=12 ) {
					 mon=0+""+Integer.toString(monthindex);
					 mont_yr=month.split("-")[1]+"/"+mon;
				}else {
					 mont_yr=month.split("-")[1]+"/"+Integer.toString(monthindex);
				}
				
				double leave1=employeeDAO.getPayroll_leave(salary.getEmp_id(),mont_yr);
				
				
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
				//sunday=getSundays(Integer.parseInt(salary.getYear()),  newmonth,temp1);
				}
				Calendar mycal1 = new GregorianCalendar(Integer.parseInt(year1), Integer.parseInt(newmonth), idays);
				int daysInMonth = mycal1.getActualMaximum(Calendar.DAY_OF_MONTH);
				int actualworkingdays=daysInMonth-sunday;
				
				double allowances = daontaa + special + perosnal + transport + hraa + daa + npaa + otherrr+convey+perdevallow+medical+ot+advance;
				salary.setAllowances(String.valueOf(allowances));
				
				allowances = Double.parseDouble(salary.getAllowances());
				double gross = allowances + basic;
				salary.setGross_pay(String.valueOf(gross));

				// net pay

				gross = Double.parseDouble(salary.getGross_pay());
				double deductions = Double.parseDouble(salary.getDeductions());
				double net = gross - deductions;
				double perdayamt = rs.getDouble(3)/ actualworkingdays;
				
				 leaveamt=leave1*perdayamt;
				 net=net+leaveamt;
				//salary.setCalnetpay(master.getCalnetpay());
				salary.setCalnetpay(String.valueOf(df.format(net)));

			/*	Payroll loan = getLoanFromEmpID(salary.getEmp_id());*/
				/*if (loan.getAmount() != null) {

					if (!loan.getAmount().equals("")) {
						salary.setLoan(loan.getAmount());
					} else {
						salary.setLoan("0");
					}
				} else {
					salary.setLoan("0");
				}*/

				Payroll payroll = getLeaveFormEmpID(salary.getEmp_id());
				int paidleaves = 0, totaldays = 0, unpaidleaves = 0;
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
				String dates[] = month.split("-");

				Date date4 = new SimpleDateFormat("MMM").parse(month);// put
																		// your
																		// month
																		// name
																		// here
				Calendar cal = Calendar.getInstance();
				cal.setTime(date4);
				int monthIndex = cal.get(Calendar.MONTH);

				int year = Integer.parseInt(dates[1]);
				calendar.set(year, monthIndex, 1);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = calendar.getTime();
				String fromdate = dateFormat.format(date);

				int numDaysinMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				calendar.set(Calendar.DAY_OF_MONTH, numDaysinMonth);
				Date date2 = calendar.getTime();
				String todate = dateFormat.format(date2);

				int holidays = getHolidaysinMonth(fromdate, todate);
				salary.setUnpaidleaves(String.valueOf(unpaidleaves));
				salary.setPaidleaves(String.valueOf(holidays));
				salary.setTotaldays(String.valueOf(numDaysinMonth));
				salary.setWorkeddays(String.valueOf(numberofdayswork));
				/*updateSalary(salary);*/
		
				list.add(salary);
				}
			}
				
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Allowance getAllowanceMaster(String type) {

		Allowance allowance = new Allowance();

		try {

			String sql = "select allowance_type,value from apm_payroll_allowance where name='" + type + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				allowance.setAllowanceType(rs.getString(1));
				allowance.setValue(rs.getString(2));
				allowance.setAllowance_name(type);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return allowance;
	}

	public Payroll getDeductionMaster(String type) {

		Payroll payroll = new Payroll();

		try {

			String sql = "select deduction_type,emp_contribution,comp_contribution from apm_payroll_deduction where deduction='"
					+ type + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				payroll.setDeduction_type(rs.getString(1));
				payroll.setEmp_contribution(rs.getString(2));
				payroll.setComp_contribution(rs.getString(3));
				payroll.setDeduction(type);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return payroll;
	}

	public int updateSalaryMasterSalary(Salary salary) {

		int result = 0;

		try {

			/*String sql = "update apm_payroll_salary_master set basic=?,date=? where emp_id=" + salary.getEmp_id() + "";*/
			String sql="update apm_payroll_salary_master set  basic=?, date=?, allowances=?, da_on_ta=?, special_pay=?, personal_pay=?, tarnsport_allowance=?, hra=?, da=?,"
					+ " npa=?, emp_pf=?, emp_esi=?, comp_pf=?, comp_esi=?, taxable=?,conveyance=?, washing=?, perdevallow=?, vehiclepass=? other_income=?, net_pay=?, "
					+ "gross_sal=?, other_deduction=?, deductions=?, tds=?, professional_tax=?, medical_allowance=?,fixedsalary=? where "
					+ "emp_id=" + salary.getEmp_id() + "";;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, salary.getBasic());
			ps.setString(2, salary.getDate());
			ps.setString(3, salary.getAllowances());
			ps.setString(4, salary.getDa_on_ta());
			ps.setString(5, salary.getSpecial_pay());
			ps.setString(6, salary.getPersonal_pay());
			ps.setString(7, salary.getTransport_allowance());
			ps.setString(8, salary.getHra());
			ps.setString(9, salary.getDa());
			ps.setString(10, salary.getNpa());
			ps.setString(11, salary.getEmp_pf());
			ps.setString(12, salary.getEmp_esi());
			ps.setString(13, salary.getComp_pf());
			ps.setString(14, salary.getComp_esi());
			ps.setString(15, salary.getTaxable());
			ps.setString(16, salary.getConveyance());
			ps.setString(17, salary.getWashing());
			ps.setString(18, salary.getPerdevallow());
			ps.setString(19, salary.getVehiclepass());
			ps.setString(20, salary.getOtherincome());
			ps.setString(21, salary.getNetpay());
			ps.setString(22, salary.getGross_pay());
			ps.setString(23, salary.getOther_deduction());
			ps.setString(24, salary.getDeductions());
			ps.setString(25, salary.getTds());
			ps.setString(26, salary.getPrefessional_tax());
			ps.setString(27, salary.getMedical_allowance());
			ps.setString(28, salary.getFixedsalary());
			result = ps.executeUpdate();
		
			
//, other_income, net_pay, gross_sal, other_deduction, deductions, tds, professional_tax, medical_allowance, fixedsalary
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Salary getMasterSalary(String emp_id, Pagination pagination) {

		Salary salary = new Salary();

		try {
			/*String sql = "select id,basic,date from apm_payroll_salary_master where emp_id='" + emp_id + "'";*/
			String sql ="select id, basic, date, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, taxable, conveyance, washing, perdevallow, net_pay,professional_tax,medical_allowance from apm_payroll_salary_master where emp_id='" + emp_id + "'";;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			salary.setChkobj(false);
			while (rs.next()) {

				salary.setId(rs.getInt(1));
				salary.setBasic(rs.getString(2));
				salary.setDate(rs.getString(3));
				salary.setAllowances(rs.getString(4));
				salary.setDa_on_ta(rs.getString(5));
				salary.setSpecial_pay(rs.getString(6));
				salary.setPersonal_pay(rs.getString(7));
				salary.setTransport_allowance(rs.getString(8));
				salary.setHra(rs.getString(9));
				salary.setDa(rs.getString(10));
				salary.setEmp_pf(rs.getString(12));
				salary.setNpa(rs.getString(11));
				salary.setEmp_esi(rs.getString(13));
				salary.setComp_pf(rs.getString(14));
				salary.setComp_esi(rs.getString(15));
	            salary.setTaxable(rs.getString(16));
	            salary.setConveyance(rs.getString(17));
				salary.setWashing(rs.getString(18));
				salary.setPerdevallow(rs.getString(19));
				salary.setEmp_id(emp_id);
				salary.setNetpay(rs.getString(20));
				salary.setPrefessional_tax(rs.getString(21));
				salary.setMedical_allowance(rs.getString(22));
				salary.setChkobj(true);
				salary.setStatus("WIP");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return salary;
	}

	public int getLoginEmployee(String username, String password) {

		int result = 0;
		try {

			String sql = "select empid,name,password from apm_payroll_employee";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String user = rs.getString(2);
				String pass = rs.getString(3);

				if (user != null && pass != null) {

					if (username.equals(user) && password.equals(pass)) {

						result = rs.getInt(1);
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	/*
	 * public int addLeave(Payroll payroll) { // TODO Auto-generated method stub
	 * return 0; }
	 */
	public Payroll getleaveDetails(String id) {
		Payroll payroll = new Payroll();
		try {
			String sql = "select id, name, leave_type, leave_reason, fromdate, todate, requesteddate, approveddate, approvedby, userid, status,comment,days,hours from payroll_leave where id ="
					+ id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				payroll.setId(rs.getInt(1));
				payroll.setName(rs.getString(2));
				if(rs.getString(3).equals("1")){
					payroll.setLeave_type("Casual Leave");
				}
				else if(rs.getString(3).equals("2")){
					payroll.setLeave_type("Medical Leave");
				}else{
					payroll.setLeave_type("Loss Of Pay");
				}
				payroll.setLeaveno(rs.getString(3));
				payroll.setLeave_reason(rs.getString(4));
				payroll.setFromdate(rs.getString(5));
				payroll.setTodate(rs.getString(6));
				payroll.setRequestdate(DateTimeUtils.changeDateFormattoPicker(rs.getString(7)));
				payroll.setApproveddate(rs.getString(8));
				payroll.setApprovedby(rs.getString(9));
				payroll.setUserid(rs.getString(10));
				payroll.setStatus(rs.getString(11));
				payroll.setNotes(DateTimeUtils.isNull(rs.getString(12)));
				payroll.setComment(DateTimeUtils.isNull(rs.getString(12)));
				payroll.setDays(rs.getString(13));
				PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
				Payroll payroll2 =payrollMasterDAO.getuserDetails(rs.getString(2));
				String requestname =payroll2.getFirstName()+ " " +payroll2.getLastName();
				payroll.setRequestname(requestname);
				payroll.setRequestcontact(payroll2.getMobile());
				payroll.setRequestdepartment(payroll2.getJobtitle());
				payroll.setNo_hours(DateTimeUtils.numberCheck(rs.getString(14)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payroll;
	}

	public Payroll getuserDetails(String userid){
		Payroll payroll2 = new Payroll();
		try {
			String sql ="select id, firstname, lastname, jobtitle, mobile from apm_user where userid= '"+ userid +"'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				payroll2.setId(rs.getInt(1));
				payroll2.setFirstName(rs.getString(2));
				payroll2.setLastName(rs.getString(3));
				
				payroll2.setJobtitle(rs.getString(4));
				payroll2.setMobile(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return payroll2;
		
	}
	
	public int updateAproveLeaveLog(String id, String status, String aprove, String userid, String notes) {
		int result = 0;

		try {
			Payroll payroll = new Payroll();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String currentdate = dateFormat.format(calendar.getTime());

			String sql = "";
			sql = "update payroll_leave set status=1, approveddate='" + currentdate + "',approvedby='" + userid
					+ "',comment='" + notes + "' where id=" + id + " ";

			PreparedStatement ps = connection.prepareStatement(sql);
			/*
			 * ps.setString(1,payroll.getApproveddate()); ps.setString(2,
			 * payroll.getUserid()); ps.setString(3, payroll.getNotes());
			 */
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	
	
	public int updateRejectLeave(String id, String status, String notes, String reject, String userid) {
		int result = 0;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String currentdate = dateFormat.format(calendar.getTime());

			String sql = "";

			sql = "update payroll_leave set status=" + status + ", comment='" + notes + "', approveddate='"
					+ currentdate + "',approvedby='" + userid + "' where id=" + id + " ";

			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int getTotalLeaveCount(String branchid,  String empname, String Flag,
			String userid, LoginInfo loginInfo,String status,String fromDate, String toDate, String leavests, String leavetype) {
		int res = 0;
		try {
			StringBuffer buffer=new StringBuffer();
				if (status.equals("")) {
					buffer.append(
							"Select count(*) from payroll_leave ");
					buffer.append("where requesteddate between '"+fromDate+"' and '"+toDate+"' ");
					if(!empname.equals("")){
						buffer.append("and name like '%"+empname+"%' ");
					}
					if(!leavests.equals("")){
						if(leavests.equals("1")){
						buffer.append("and status='1' and status='2' ");
					}else{
						buffer.append("and status='"+leavests+"' ");
					}
					} 
					if(!leavetype.equals("")){
						buffer.append("and leave_type='"+leavetype+"'");
					}
						
				} else {
					buffer.append(
							"Select count(*) from payroll_leave ");
					buffer.append("where userid='" + userid + "'");
					if (Flag.equals("1")) {
						buffer.append(" and status=3 ");
						
					}
					
				}
				buffer.append(" order by status asc ");
				PreparedStatement ps = connection.prepareStatement(buffer.toString());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					res=rs.getInt(1);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;

	}

	public ArrayList<Payroll> getleaveDetailsPrint(String id) {
		ArrayList<Payroll> printleavelist = new ArrayList<Payroll>();
		try {
			String sql = "Select id, name, leave_type, leave_reason, fromdate, todate, requesteddate, approveddate, approvedby, userid, status,days from payroll_leave where id="
					+ id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Payroll payroll = new Payroll();
				payroll.setId(rs.getInt(1));
				payroll.setName(rs.getString(2));
				payroll.setLeave_type(rs.getString(3));
				payroll.setLeave_reason(rs.getString(4));
				payroll.setFromdate(rs.getString(5));
				payroll.setTodate(rs.getString(6));
				/*payroll.setDate(rs.getString(7));*/
				String date = rs.getString(7);
				/*if(date!=null){
					date = date.split(" ");
				}*/
				date = DateTimeUtils.getCommencingDate1(date);
				payroll.setDate(date);
				/*payroll.setApproveddate(rs.getString(8));*/
				String approveddate =rs.getString(8);
				if(approveddate!=null){
					approveddate=approveddate.split(" ")[0];
				}
				approveddate = DateTimeUtils.getCommencingDate1(approveddate);
				payroll.setApproveddate(approveddate);
				payroll.setApprovedby(rs.getString(9));
				payroll.setUserid(rs.getString(10));
				payroll.setStatus(rs.getString(11));
				payroll.setDays(rs.getString(12));
				
				PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
				Payroll payroll2 =payrollMasterDAO.getuserDetails(rs.getString(2));
				String requestname =payroll2.getFirstName()+ " " +payroll2.getLastName();
				payroll.setRequestname(requestname);
				payroll.setRequestcontact(payroll2.getMobile());
				payroll.setRequestdepartment(payroll2.getJobtitle());
				
				
				printleavelist.add(payroll);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return printleavelist;
	}

	public ArrayList<Salary> getAllSalaryDetails(String year, String month) {
		ArrayList<Salary> list = new ArrayList<Salary>();
		PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);
		try {
		/*	String sql = "select emp_id, date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, taxable, other_income, net_pay, gross_sal, incre_date, branch_id, other_deduction, deductions, tds, professional_tax from apm_payroll_salary ";*/
		StringBuffer buffer = new StringBuffer();
//			buffer.append("select apm_payroll_salary.id, apm_payroll_salary.emp_id, date, basic, allowances, da_on_ta, special_pay, personal_pay, ");
//			buffer.append("tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, taxable, other_income, net_pay, gross_sal, incre_date, ");
//			buffer.append("branch_id, other_deduction, deductions, tds, professional_tax, conveyance, washing, perdevallow, vehiclepass, apm_payroll_bank.account_no,apm_payroll_employee.name, ");
//			buffer.append("apm_payroll_employee.designation ");
//			buffer.append("from apm_payroll_salary left join apm_payroll_bank on apm_payroll_bank.emp_id = apm_payroll_salary.emp_id ");
//			buffer.append("inner join apm_payroll_employee on apm_payroll_employee.empid = apm_payroll_salary.emp_id ");
//			buffer.append("and date between '"+fromdate+"' and '"+todate+"' ");
		buffer.append("select apm_payroll_final_salary_rpt.id,apm_payroll_final_salary_rpt.emp_id, apm_payroll_final_salary_rpt.date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, ");
		buffer.append("taxable, other_income, net_pay, gross_sal, incre_date,branch_id, other_deduction, deductions, tds, professional_tax, ");
		buffer.append("conveyance, washing, perdevallow, vehiclepass,apm_payroll_bank.account_no,apm_payroll_employee.name,apm_payroll_employee.designation ");
		buffer.append("from apm_payroll_final_salary_rpt left join apm_payroll_bank on apm_payroll_bank.emp_id = apm_payroll_final_salary_rpt.emp_id ");
		buffer.append("inner join apm_payroll_employee on apm_payroll_employee.empid = apm_payroll_final_salary_rpt.emp_id ");
		buffer.append("and month='"+month+"' and  year='"+year+"' ");
	
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Salary salary = new Salary();
				/*salary.setId(rs.getInt(1));*/
				salary.setEmp_id(rs.getString(2));
				salary.setDate(rs.getString(3));
				salary.setBasic(rs.getString(4));
				salary.setAllowances(rs.getString(5));
				salary.setDa_on_ta(rs.getString(6));
				salary.setSpecial_pay(rs.getString(7));
				salary.setPersonal_pay(rs.getString(8));
				salary.setTransport_allowance(rs.getString(9));
				salary.setHra(rs.getString(10));
				/*salary.setDa(rs.getString(11));
				salary.setNpa(rs.getString(12));*/
				salary.setEmp_pf(rs.getString(13));
				/*salary.setEmp_esi(rs.getString(14));
				salary.setComp_pf(rs.getString(15));
				salary.setComp_esi(rs.getString(16));*/
				salary.setTaxable(rs.getString(17));
				salary.setOtherincome(rs.getString(18));
				salary.setNetpay(rs.getString(19));
				salary.setGross_pay(rs.getString(20));
				/*salary.setIncre_date(rs.getString(21));
				salary.setBranch_id(rs.getString(22));*/
				if(rs.getString(24)==null){
					salary.setDeductions("0");
				}else{
					salary.setDeductions(rs.getString(24));
				}
				if(rs.getString(23)==null){
					salary.setOther_deduction("0");
				}else{
					salary.setOther_deduction(rs.getString(23));
				}
				if(rs.getString(13)==null){
					salary.setEmp_pf("0");
				}else{
					salary.setEmp_pf(rs.getString(13));
				}
				double total_deduction = Double.parseDouble( salary.getEmp_pf()) + Double.parseDouble(salary.getDeductions()) + Double.parseDouble(salary.getOther_deduction());
				salary.setTotal_deduction(Math.round(total_deduction));
				
				salary.setTds(rs.getString(25));
				salary.setPrefessional_tax(rs.getString(26));
				salary.setConveyance(rs.getString(27));
				salary.setWashing(rs.getString(28));
				salary.setPerdevallow(rs.getString(29));
				salary.setVehiclepass(rs.getString(30));
				salary.setBank_account(rs.getString(31));
				salary.setEmp_name(rs.getString(32));
				salary.setDesignation(rs.getString(33));
				
				
				list.add(salary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public Salary getSalaryForAttendence(String emp_id) {

		Salary salary = new Salary();

		try {
			String sql = "select id,basic from apm_payroll_salary_master where emp_id='" + emp_id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				salary.setId(rs.getInt(1));
				salary.setBasic(rs.getString(2));
				/*salary.setDate(rs.getString(3));*/
				salary.setEmp_id(emp_id);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return salary;
	}
	public double getDaysForSalary(String emp_id, String month){
		double result=0;
		StringBuffer buffer=new StringBuffer();
		try {
			buffer.append("select days from apm_payroll_weeksheet where emp_id='"+ emp_id +"' ");
			if(!month.equals("")){
			buffer.append("and month='"+month+"' ");
			}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				/*salary.setId(rs.getInt(1));*/
				result=rs.getDouble(1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
public String totalSalaryForSlip(String emp_id){
		
		String result="";
		try {
			String sql="select totalsalary from apm_payroll_weeksheet where emp_id='" + emp_id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

			result = rs.getString(1);
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
public String monthforSalarySlip(String emp_id){
	
	String result="";
	try {
		String sql="select month from apm_payroll_weeksheet where emp_id='" + emp_id + "'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			result = rs.getString(1);
			

		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	
}
public Salary getchangeworkdays(String emp_id, String month) {
	Salary salary = new Salary();
	try {
		String sql = "select days,totalsalary from apm_payroll_weeksheet where emp_id='" + emp_id + "' and month='"+month+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			salary.setDays(rs.getString(1));
			salary.setSalaryTotal(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return salary;
}
/*public int savemonthsalary() {
	int result=0;
	try {
		String sql ="insert into apm_payroll_salary_monthly(emp_id, sal_id, year, month, workdays, ot, totaldays, holidays, deductions, salary) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}
*/
/*public int savemonthsalary(Salary salary) {
	int result=0;
	try {
		String sql ="insert into apm_payroll_salary_monthly(emp_id, sal_id, year, month, workdays, totaldays, holidays, deductions, salary) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, salary.getEmp_id());
		ps.setString(2, salary.getSalaryId());
		ps.setString(3, salary.getYear());
		ps.setString(4, salary.getMonth());
		ps.setString(5, salary.getDays());
		ps.setString(6, salary.getTotaldays());
		ps.setString(7, salary.getHolidays());
		ps.setString(8, salary.getDeductions());
		ps.setString(9, salary.getNetpay());
		res
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}*/
 public Salary getconvey(String emp_id){
	 Salary salary = new Salary();
	 try {
		String sql = "select conveyance, washing from apm_payroll_salary_master where emp_id='"+ emp_id +"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			if(rs.getDouble(1)!=0){
			salary.setConveyance(rs.getString(1));
			}else{
				salary.setConveyance("0");
			}
			if(rs.getDouble(2)!=0){
			salary.setWashing(rs.getString(2));
			}else{
				salary.setWashing("0");
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	 
	return salary;
	 
 }
 
 public int updateAproveHrLeaveLog(String id, String status, String hraprove, String userid, String notes) {
	 int result = 0;

		try {
			Payroll payroll = new Payroll();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String currentdate = dateFormat.format(calendar.getTime());

			String sql = "";
			sql = "update payroll_leave set status=3, approveddate='" + currentdate + "',approvedby='" + userid
					+ "',comment='" + notes + "' where id=" + id + " ";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			/*
			 * ps.setString(1,payroll.getApproveddate()); ps.setString(2,
			 * payroll.getUserid()); ps.setString(3, payroll.getNotes());
			 */
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

public boolean checkExistRecord(Salary salary) {
	boolean flag=false;
	try {
		String sql = "select * from apm_payroll_final_salary where emp_id='" + salary.getEmp_id() + "' and month='"+salary.getMonth()+"' and year='"+salary.getYear()+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			flag=true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return flag;
}

public int updateSalaryMonth(Salary salary) {
	int result = 0;

	try {
		String sql = "update apm_payroll_salary_monthly set  workdays=?, totaldays=?, holidays=?, deductions=?, salary=?, status=?, carryforward=?,finalstatus=? where emp_id='"+salary.getEmp_id()+"' and month='"+salary.getMonth()+"' and year='"+salary.getYear()+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, salary.getWorkeddays());
		ps.setString(2, salary.getTotaldays());
		ps.setString(3, salary.getHolidays());
		ps.setString(4, salary.getDeductions());
		ps.setString(5, salary.getNetpay());
		ps.setString(6, "Paid");
		ps.setString(7, "No");
		ps.setInt(8, 0);

		result = ps.executeUpdate();

	} catch (Exception e) {

		e.printStackTrace();

	}

	return result;
}

public int updateFinalStatus(Salary salary, String Status,String tablename) {
	int result = 0;

	try {
		String sql = "update "+tablename+" set finalstatus=? where emp_id='"+salary.getEmp_id()+"' and month='"+salary.getMonth()+"' and year='"+salary.getYear()+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);

		
		ps.setString(1, Status);

		result = ps.executeUpdate();

	} catch (Exception e) {

		e.printStackTrace();

	}

	return result;
}

public int payrollInsertFinalSalary(Salary salary, Salary salary2) {
	int res=0;
	PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
	String sal_date=DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
	try {
		String sql = "insert into apm_payroll_final_salary (emp_id, date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, "
				+ "emp_esi, comp_pf, comp_esi, taxable, other_income, net_pay, gross_sal, incre_date, other_deduction, deductions, tds, professional_tax, medical_allowance, "
				+ "branch_id, conveyance, washing, perdevallow, vehiclepass, fixedsalary, sal_id, year, month, workdays, totaldays, holidays,  salary, status, "
				+ "carryforward,finalstatus,leaves,userid,perdayamt,ot) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, salary2.getEmp_id());
		ps.setString(2,sal_date);
		ps.setString(3, salary2.getBasic());
		ps.setString(4, salary2.getAllowances());
		ps.setString(5, salary2.getDa_on_ta());
		ps.setString(6, salary2.getSpecial_pay());
		ps.setString(7, salary2.getPersonal_pay());
		ps.setString(8, salary2.getTransport_allowance());
		ps.setString(9, salary2.getHra());
		ps.setString(10, salary2.getDa());
		ps.setString(11, salary2.getNpa());
		ps.setString(12, salary2.getEmp_pf());
		ps.setString(13, salary2.getEmp_esi());
		ps.setString(14, salary2.getComp_pf());
		ps.setString(15, salary2.getComp_esi());
		ps.setString(16, salary2.getTaxable());
		ps.setString(17, salary2.getOther_income());
		ps.setString(18, salary2.getNetpay());
		ps.setString(19, salary2.getGross_pay());
		ps.setString(20, salary2.getIncre_date());
		
//		 other_deduction, deductions, tds, professional_tax, medical_allowance, "
//					+ "branch_id, conveyance, washing, perdevallow, vehiclepass, fixedsalary2, sal_id, year, month, workdays, totaldays, holidays, deductions, salary2, status, "
//					+ "carryforward,finalstatus
		ps.setString(21, salary2.getOther_deduction());
		ps.setString(22, salary2.getDeductions());
		ps.setString(23, salary2.getTds());
		ps.setString(24, salary2.getPrefessional_tax());
		ps.setString(25, salary2.getMedical_allowance());
		ps.setString(26, salary2.getBranch_id());
		ps.setString(27, salary2.getConveyance());
		ps.setString(28, salary2.getWashing());
		ps.setString(29, salary2.getPerdevallow());
		ps.setString(30, salary2.getVehiclepass());
		ps.setString(31, salary2.getFixedsalary());
		ps.setString(32, salary.getSalaryId());
		ps.setString(33, salary.getYear());
		ps.setString(34, salary.getMonth());
		ps.setString(35, salary.getWorkeddays());
		ps.setString(36, salary.getTotaldays());
		ps.setString(37, salary.getHolidays());
		ps.setString(38,  salary2.getNetpay());
		ps.setString(39, "Unpaid");
		ps.setString(40, "No");
		ps.setString(41, "processed");
		ps.setString(42, salary.getLeave());
		String userid=employeeDAO.getEmployeeUserId(salary2.getEmp_id());
		ps.setString(43, userid);
		ps.setDouble(44, salary2.getPerdayamount());
		ps.setString(45, salary.getOt());
		res = ps.executeUpdate();

	} catch (Exception e) {

		e.printStackTrace();

	}

	return res;
}

public Salary getfinalReportData(String emp_id,String month,String year, String status,String tablename, Pagination pagination) {
	Salary salary = new Salary();
	try {

//		String sql = "select id,date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, "
//				+ "taxable, other_income, net_pay, gross_sal, incre_date, branch_id,other_deduction,deductions,tds,professional_tax,medical_allowance,conveyance,"
//				+ " washing, perdevallo7w, vehiclepass, fixedsalary from apm_payroll_salary where emp_id="
//				+ emp_id + "";
		StringBuffer buffer=new StringBuffer();
		String sql="";
		buffer.append("select id, date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, ");
		buffer.append("taxable, other_income, net_pay, gross_sal, incre_date,branch_id, other_deduction, deductions, tds, professional_tax, medical_allowance,  conveyance, ");
		buffer.append("washing, perdevallow, vehiclepass, fixedsalary, ");
		buffer.append("sal_id, year, month, workdays, ot, totaldays, holidays, salary, status, carryforward, finalstatus,leaves,perdayamt,advance,loan from "+tablename+" where emp_id='"+emp_id+"' and month='"+month+"' and year='"+year+"' ");
		if(status.equals("1")){
			status="processed";
		}
		else if(status.equals("2")){
			status="paid";
		}
		
		if(!status.equals("")){
		buffer.append("and finalstatus='"+status+"'");
		}
		PreparedStatement ps = connection.prepareStatement(buffer.toString());

		ResultSet rs = ps.executeQuery();
		salary.setChkobj(false);
		while (rs.next()) {

			salary.setId(rs.getInt(1));
			salary.setDate(rs.getString(2));
			
			
			String salaryTotal = totalSalaryForSlip(emp_id);
			salary.setSalaryTotal(salaryTotal);
			
			salary.setBasic(rs.getString(3));
			salary.setAllowances(rs.getString(4));
			
			salary.setDa_on_ta(nullwithzero(rs.getString(5)));
			salary.setSpecial_pay(nullwithzero(rs.getString(6)));
			salary.setPersonal_pay(nullwithzero(rs.getString(7)));
			salary.setTransport_allowance(nullwithzero(rs.getString(8)));
			salary.setHra(nullwithzero(rs.getString(9)));
			salary.setDa(nullwithzero(rs.getString(10)));
			salary.setNpa(nullwithzero(rs.getString(11)));
			salary.setEmp_pf(nullwithzero(rs.getString(12)));
			salary.setEmp_esi(nullwithzero(rs.getString(13)));
			salary.setComp_pf(nullwithzero(rs.getString(14)));
			salary.setComp_esi(nullwithzero(rs.getString(15)));
			salary.setTaxable(nullwithzero(rs.getString(16)));
			salary.setOtherincome(nullwithzero(rs.getString(17)));
			salary.setNetpay(nullwithzero(rs.getString(18)));
			salary.setGross_pay(nullwithzero(rs.getString(19)));
			salary.setIncre_date(rs.getString(20));
			salary.setBranch_id(rs.getString(21));
			salary.setEmp_id(emp_id);
			salary.setOther_deduction(nullwithzero(rs.getString(22)));
			salary.setDeductions(nullwithzero(rs.getString(23)));
			salary.setTds(nullwithzero(rs.getString(24)));
			salary.setPrefessional_tax(nullwithzero(rs.getString(25)));
			salary.setMedical_allowance(nullwithzero(rs.getString(26)));
		
			salary.setConveyance(nullwithzero(rs.getString(27)));
			salary.setWashing(nullwithzero(rs.getString(28)));
			salary.setFixedsalary(rs.getString(31));
			salary.setPerdevallow(nullwithzero(rs.getString(29)));
			salary.setVehiclepass(nullwithzero(rs.getString(30)));
//			sal_id, year, month, workdays, ot, totaldays, holidays, salary, status, carryforward, finalstatus
			salary.setSalaryId(nullwithzero(rs.getString(32)));
			salary.setYear(rs.getString(33));
			salary.setMonth(rs.getString(34));
			salary.setWorkeddays(rs.getString(35));
			salary.setOt(nullwithzero(rs.getString(36)));
			salary.setTotaldays(rs.getString(37));
			salary.setHolidays(rs.getString(38));
			salary.setCalnetpay(rs.getString(39));
			salary.setStatus(rs.getString(40));
			salary.setCarryforward(rs.getString(41));
			salary.setFinalstatus(rs.getString(42));
			salary.setLeave(nullwithzero(rs.getString(43)));
			salary.setPerdayamount(rs.getDouble(44));
			salary.setAdvance(nullwithzero(rs.getString(45)));
			salary.setLoan(nullwithzero(rs.getString(46)));
			salary.setChkobj(true);
		}

	} catch (Exception e) {

		e.printStackTrace();
	}

	return salary;
}

public int updatefinalizeSalary(Salary salary) {
	int result = 0;

	try {

		String sql = "update apm_payroll_final_salary set basic=?,allowances=?, da_on_ta=?, special_pay=?, personal_pay=?, tarnsport_allowance=?, hra=?, da=?, npa=?,deductions=?,gross_sal=?,net_pay=?,incre_date=?, conveyance=?, washing=?, perdevallow=? where emp_id="
				+ salary.getEmp_id() + " and month='"+salary.getMonth()+"' and year='"+salary.getYear()+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, salary.getBasic());
		ps.setString(2, salary.getAllowances());
		ps.setString(3, salary.getDa_on_ta());
		ps.setString(4, salary.getSpecial_pay());
		ps.setString(5, salary.getPersonal_pay());
		ps.setString(6, salary.getTransport_allowance());
		ps.setString(7, salary.getHra());
		ps.setString(8, salary.getDa());
		ps.setString(9, salary.getNpa());
		ps.setString(10, salary.getDeductions());
		ps.setString(11, salary.getGross_pay());
		ps.setString(12, salary.getNetpay());
		ps.setString(13, salary.getDate());
		ps.setString(14, salary.getConveyance());
		ps.setString(15, salary.getWashing());
		ps.setString(16, salary.getPerdevallow());

		result = ps.executeUpdate();

	} catch (Exception e) {

		e.printStackTrace();
	}

	return result;
}

public int updatefinalsalarydeduction(Salary salary) {
	int result = 0;
	try {

		String sql = "update apm_payroll_final_salary set basic=?,allowances=?,emp_pf=?,emp_esi=?,net_pay=?,gross_sal=?,incre_date=?,other_deduction=?,deductions=?,taxable=?,tds=?,professional_tax=? where emp_id="
				+ salary.getEmp_id() + " and month='"+salary.getMonth()+"' and year='"+salary.getYear()+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, salary.getBasic());
		ps.setString(2, salary.getAllowances());
		ps.setString(3, salary.getEmp_pf());
		ps.setString(4, salary.getEmp_esi());
		ps.setString(5, salary.getNetpay());
		ps.setString(6, salary.getGross_pay());
		ps.setString(7, salary.getDate());
		ps.setString(8, salary.getOther_deduction());
		ps.setString(9, salary.getDeductions());
		ps.setString(10, salary.getTaxable());
		ps.setString(11, salary.getTds());
		ps.setString(12, salary.getPrefessional_tax());

		result = ps.executeUpdate();

	} catch (Exception e) {

		e.printStackTrace();
	}

	return result;
}

public int updatepayrollstatus(Salary salary) {
	int result = 0;

	try {
		String sql = "update apm_payroll_final_salary set  finalstatus=?, status=?  where emp_id='"+salary.getEmp_id()+"' and month='"+salary.getMonth()+"' and year='"+salary.getYear()+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, "Paid");
        ps.setString(2, "Paid");
		result = ps.executeUpdate();

	} catch (Exception e) {

		e.printStackTrace();

	}

	return result;
}

public int updatecolumnallow(String empid, String month, String year, String colname, String value) {
	int result = 0;

	try {
		String sql = "update apm_payroll_final_salary set  "+colname+"=? where emp_id='"+empid+"' and month='"+month+"' and year='"+year+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, value);
		result = ps.executeUpdate();
			
	} catch (Exception e) {

		e.printStackTrace();

	}

	return result;
}

public int updategrossallow(String empid, String month, String year, double allownces, double grosspay,double netpay,double deduction) {
	int result = 0;

	try {
		String sql = "update apm_payroll_final_salary set allowances=?,gross_sal=?,net_pay=?,deductions=? where emp_id='"+empid+"' and month='"+month+"' and year='"+year+"' ";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setDouble(1, allownces);
		ps.setDouble(2, grosspay);
		ps.setDouble(3, netpay);
		ps.setDouble(4, deduction);
		result = ps.executeUpdate();
			
	} catch (Exception e) {

		e.printStackTrace();

	}

	return result;
}

public boolean checkExistRecordWithParam(String emp_id, String monthname, String year1) {
	boolean flag=false;
	try {
		String sql = "select * from apm_payroll_final_salary where emp_id='" + emp_id + "' and month='"+monthname+"' and year='"+year1+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			flag=true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return flag;
}

public int payrollInsertFinalSalaryRpt(Salary salary, Salary salary2) {
	int res=0;
	PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
	try {
		String sql = "insert into apm_payroll_final_salary_rpt (emp_id, date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, "
				+ "emp_esi, comp_pf, comp_esi, taxable, other_income, net_pay, gross_sal, incre_date, other_deduction, deductions, tds, professional_tax, medical_allowance, "
				+ "branch_id, conveyance, washing, perdevallow, vehiclepass, fixedsalary, sal_id, year, month, workdays, totaldays, holidays,  salary, status, "
				+ "carryforward,finalstatus,leaves,ot,userid,perdayamt,loan,advance) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, salary2.getEmp_id());
		ps.setString(2, salary2.getDate());
		ps.setString(3, salary2.getBasic());
		ps.setString(4, salary2.getAllowances());
		ps.setString(5, salary2.getDa_on_ta());
		ps.setString(6, salary2.getSpecial_pay());
		ps.setString(7, salary2.getPersonal_pay());
		ps.setString(8, salary2.getTransport_allowance());
		ps.setString(9, salary2.getHra());
		ps.setString(10, salary2.getDa());
		ps.setString(11, salary2.getNpa());
		ps.setString(12, salary2.getEmp_pf());
		ps.setString(13, salary2.getEmp_esi());
		ps.setString(14, salary2.getComp_pf());
		ps.setString(15, salary2.getComp_esi());
		ps.setString(16, salary2.getTaxable());
		ps.setString(17, salary2.getOther_income());
		ps.setString(18, salary2.getNetpay());
		ps.setString(19, salary2.getGross_pay());
		ps.setString(20, salary2.getIncre_date());
		
//		 other_deduction, deductions, tds, professional_tax, medical_allowance, "
//					+ "branch_id, conveyance, washing, perdevallow, vehiclepass, fixedsalary2, sal_id, year, month, workdays, totaldays, holidays, deductions, salary2, status, "
//					+ "carryforward,finalstatus
		ps.setString(21, salary2.getOther_deduction());
		ps.setString(22, salary2.getDeductions());
		ps.setString(23, salary2.getTds());
		ps.setString(24, salary2.getPrefessional_tax());
		ps.setString(25, salary2.getMedical_allowance());
		ps.setString(26, salary2.getBranch_id());
		ps.setString(27, salary2.getConveyance());
		ps.setString(28, salary2.getWashing());
		ps.setString(29, salary2.getPerdevallow());
		ps.setString(30, salary2.getVehiclepass());
		ps.setString(31, salary2.getFixedsalary());
		ps.setString(32, salary.getSalaryId());
		ps.setString(33, salary.getYear());
		ps.setString(34, salary.getMonth());
		ps.setString(35, salary.getWorkeddays());
		ps.setString(36, salary.getTotaldays());
		ps.setString(37, salary.getHolidays());
		ps.setString(38, salary.getCalnetpay());
		ps.setString(39, "Paid");
		ps.setString(40, "No");
		ps.setString(41, "Paid");
		ps.setString(42, salary2.getLeave());
		ps.setString(43, salary2.getOt());
		ps.setString(44, employeeDAO.getEmployeeUserId(salary2.getEmp_id()));
		ps.setDouble(45, salary2.getPerdayamount());
		ps.setString(46, salary2.getLoan());
		ps.setString(47, salary2.getAdvance());
		res = ps.executeUpdate();

	} catch (Exception e) {

		e.printStackTrace();

	}

	return res;
}

public boolean checkAttendanceDone(Salary salary, String month) {
	boolean flag=false;
	try {
		String sql = "select * from apm_payroll_weeksheet where emp_id='" + salary.getEmp_id() + "' and month='"+month+"' and days>0";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			flag=true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return flag;
}

public ArrayList<Salary> getReportData(String empid, String empname, String month, String year, String dept,Pagination pagination) {
	
	ArrayList<Salary> list=new ArrayList<Salary>();
	PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
	double totbasic=0;
	double tothra=0;
	double totda=0;
	double totmed=0;
	double totconvey=0;
	double totperdev=0;
	double totot=0;
	double totpf=0;
	double totadvance=0;
	double totloan=0;
	double totesi=0;
	double totleave=0;
	double totprof=0;
	double tottds=0;
	double totgrosspay=0;
	double totnetpay=0;
	if(dept==null){
		dept="0";
	}if(dept.equals("")){
		dept="0";
	}
	try {
		DecimalFormat df = new DecimalFormat("0.00");
		StringBuffer buffer=new StringBuffer();
		String sql="";
		buffer.append("select id, date, basic, allowances, da_on_ta, special_pay, personal_pay, tarnsport_allowance, hra, da, npa, emp_pf, emp_esi, comp_pf, comp_esi, ");
		buffer.append("taxable, other_income, net_pay, gross_sal, incre_date,branch_id, other_deduction, deductions, tds, professional_tax, medical_allowance,  conveyance, ");
		buffer.append("washing, perdevallow, vehiclepass, fixedsalary, ");
		buffer.append("sal_id, year, month, workdays, ot, totaldays, holidays, salary,apm_payroll_final_salary_rpt.status, carryforward, finalstatus,leaves,emp_id, ");
		buffer.append("name,empcode,dept_id, designation,pfno,esicno,loan,advance from apm_payroll_final_salary_rpt ");
		buffer.append("inner join apm_payroll_employee on apm_payroll_employee.empid=apm_payroll_final_salary_rpt.emp_id ");
		buffer.append(" where  finalstatus='paid' ");
		buffer.append(" and  month='"+month+"' and year='"+year+"' ");
		if(empid!=null){
			if(!empid.equals("")){
				buffer.append(" and apm_payroll_employee.empcode='"+empid+"'" );
			}
		}
		if(empname!=null){
			if(!empname.equals("")){
				buffer.append(" and apm_payroll_employee.name like '%"+empname+"%'" );
			}
		}
		if(!dept.equals("0")){
				buffer.append(" and apm_payroll_employee.dept_id='"+dept+"' ");
			}
		 sql=buffer.toString();			  
		   if(pagination!=null)
			{
				sql=pagination.getSQLQuery(sql);
			}
		PreparedStatement ps = connection.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Salary salary = new Salary();
			salary.setId(rs.getInt(1));
			salary.setDate(rs.getString(2));
			/*Salary empsal=getEmployeeDetails(rs.getString(44));*/

			salary.setBasic(df.format(rs.getDouble(3)));
			salary.setAllowances(df.format(rs.getDouble(4)));
			
			salary.setDa_on_ta(nullwithzero(rs.getString(5)));
			salary.setSpecial_pay(nullwithzero(rs.getString(6)));
			salary.setPersonal_pay(nullwithzero(rs.getString(7)));
			salary.setTransport_allowance(nullwithzero(rs.getString(8)));
			salary.setHra(nullwithzero(df.format(rs.getDouble(9))));
			salary.setDa(nullwithzero(df.format(rs.getDouble(10))));
			salary.setNpa(nullwithzero(rs.getString(11)));
			salary.setEmp_pf(nullwithzero(df.format(rs.getDouble(12))));
			salary.setEmp_esi(nullwithzero(df.format(rs.getDouble(13))));
			salary.setComp_pf(nullwithzero(rs.getString(14)));
			salary.setComp_esi(nullwithzero(rs.getString(15)));
			salary.setTaxable(nullwithzero(rs.getString(16)));
			salary.setOtherincome(nullwithzero(rs.getString(17)));
			salary.setIncre_date(rs.getString(20));
			salary.setBranch_id(rs.getString(21));
			salary.setEmp_id(empid);
			salary.setOther_deduction(nullwithzero(rs.getString(22)));
			salary.setDeductions(nullwithzero(rs.getString(23)));
			salary.setTds(nullwithzero(df.format(rs.getDouble(24))));
			salary.setPrefessional_tax(nullwithzero(df.format(rs.getDouble(25))));
			salary.setMedical_allowance(nullwithzero(df.format(rs.getDouble(26))));
		
			salary.setConveyance(nullwithzero(df.format(rs.getDouble(27))));
			salary.setWashing(nullwithzero(rs.getString(28)));
			salary.setFixedsalary(df.format(rs.getDouble(31)));
			salary.setPerdevallow(nullwithzero(df.format(rs.getDouble(29))));
			salary.setVehiclepass(nullwithzero(rs.getString(30)));
//			sal_id, year, month, workdays, ot, totaldays, holidays, salary, status, carryforward, finalstatus
			salary.setSalaryId(nullwithzero(rs.getString(32)));
			salary.setYear(rs.getString(33));
			salary.setMonth(rs.getString(34));
			salary.setWorkeddays(rs.getString(35));
			salary.setOt(nullwithzero(df.format(rs.getDouble(36))));
			salary.setTotaldays(rs.getString(37));
			salary.setHolidays(rs.getString(38));
			salary.setCalnetpay(df.format(rs.getDouble(39)));
			salary.setStatus(rs.getString(40));
			salary.setCarryforward(rs.getString(41));
			salary.setFinalstatus(rs.getString(42));
			salary.setLeave(nullwithzero(df.format(rs.getDouble(43))));
			salary.setEmp_id(rs.getString(44));
			salary.setEmp_name(rs.getString(45));
			salary.setEmpcode(rs.getString(46));
			salary.setDepartment(employeeDAO.getDepartmentName(rs.getString(47)));
			salary.setDesignation(rs.getString(48));
			salary.setPfno(rs.getString(49));
			salary.setEsicno(rs.getString(50));
			salary.setLoan(nullwithzero(df.format(Double.parseDouble(rs.getString(51)))));
			salary.setAdvance(nullwithzero(df.format(Double.parseDouble(rs.getString(52)))));
			
			//total allownaces 
			
			totbasic=totbasic+Double.parseDouble(salary.getBasic());
			tothra=tothra+Double.parseDouble(salary.getHra());
			totda=totda+Double.parseDouble(salary.getDa());
			totmed=totmed+Double.parseDouble(salary.getMedical_allowance());
			totconvey=totconvey+Double.parseDouble(salary.getConveyance());
			totperdev=totperdev+Double.parseDouble(salary.getPerdevallow());
			totot=totot+Double.parseDouble(salary.getOt());
			totadvance=totadvance+Double.parseDouble(salary.getAdvance());
			salary.setTotbasic(df.format(totbasic));
			salary.setTothra(df.format(tothra));
			salary.setTotda(df.format(totda));
			salary.setTotmed(df.format(totmed));
			salary.setTotconvey(df.format(totconvey));
			salary.setTotperdev(df.format(totperdev));
			salary.setTotot(df.format(totot));
			salary.setTotadvance(df.format(totadvance));
			//total deduction
			
			
			totpf=totpf+Double.parseDouble(salary.getEmp_pf());
			totesi=totesi+Double.parseDouble(salary.getEmp_esi());
			totleave=totleave+Double.parseDouble(salary.getLeave());
			totprof=totprof+Double.parseDouble(salary.getPrefessional_tax());
			tottds=tottds+Double.parseDouble(salary.getTds());
			totloan=totloan+Double.parseDouble(salary.getLoan());
			salary.setTotpf(df.format(totpf));
			salary.setTotesi(df.format(totesi));
			salary.setTotleave(df.format(totleave));
			salary.setTotprof(df.format(totprof));
			salary.setTottds(df.format(tottds));
			salary.setTotloan(df.format(totloan));
			
			double allownces=Double.parseDouble(salary.getMedical_allowance())+Double.parseDouble(salary.getHra())+Double.parseDouble(salary.getDa())+Double.parseDouble(salary.getConveyance())+Double.parseDouble(salary.getPerdevallow())+Double.parseDouble(salary.getOt())+Double.parseDouble(salary.getAdvance());
			double grosspay=Double.parseDouble(salary.getBasic())+allownces;
			double deduction=Double.parseDouble(salary.getEmp_pf())+Double.parseDouble(salary.getEmp_esi())+Double.parseDouble(salary.getOther_deduction())+Double.parseDouble(salary.getLeave())+Double.parseDouble(salary.getPrefessional_tax())+Double.parseDouble(salary.getTds())+Double.parseDouble(salary.getLoan());
			double netpay=grosspay-deduction;
			totgrosspay=totgrosspay+grosspay;
			totnetpay=totnetpay+netpay;
			salary.setTotallownces(df.format(allownces));
			salary.setTotdeduction(df.format(deduction));
			salary.setGross_pay(String.valueOf((df.format(grosspay))));
			//salary.setNetpay(String.valueOf((df.format((netpay)))));
			salary.setNetpay(String.valueOf(df.format(rs.getDouble(39))));
			salary.setTotgrosspay(df.format(totgrosspay));
			salary.setTotnetpay(df.format(totnetpay));
			list.add(salary);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}

	return list;
}

private Salary getEmployeeDetails(String empid) {
	Salary salary=new Salary();
	PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
	try {
		String sql = "select name,dept_id, designation,empcode from apm_payroll_employee where empid='"+empid+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			salary.setEmp_name(rs.getString(1));
			salary.setDepartment(employeeDAO.getDepartmentName(rs.getString(2)));
			salary.setDesignation(rs.getString(3));
			salary.setEmpcode(rs.getString(4));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return salary;
}

private String getempname(String empid) {
	String res="";
	try {
		String sql = "select name from apm_payroll_employee where empid='"+empid+"'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
		res=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return res;
 

}

	public int getReportDatacount(String empid, String empname, String month, String year, String dept) {
		
		int res=0;
		if (dept == null) {
			dept = "0";
		}
		if (dept.equals("")) {
			dept = "0";
		}
		try {

			StringBuffer buffer = new StringBuffer();
			String sql = "";
			buffer.append("select count(*) from apm_payroll_final_salary_rpt ");
			buffer.append(
					"inner join apm_payroll_employee on apm_payroll_employee.empid=apm_payroll_final_salary_rpt.emp_id ");
			buffer.append(" where  finalstatus='paid' ");
			buffer.append(" and  month='" + month + "' and year='" + year + "' ");
			if (empid != null) {
				if (!empid.equals("")) {
					buffer.append(" and apm_payroll_employee.empcode='" + empid + "'");
				}
			}
			if (empname != null) {
				if (!empname.equals("")) {
					buffer.append(" and apm_payroll_employee.name like '%" + empname + "%'");
				}
			}
			if (!dept.equals("0")) {
				buffer.append(" and apm_payroll_employee.dept_id='" + dept + "' ");
			}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int manageSalaryDetailscount(String month, String branchid, String status,Pagination pagination) {
		AttendanceDAO attendanceDAO = new JDBCAttendanceDAO(connection);
		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
		PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);
		String monthname=month.split("-")[0];
		String year1=month.split("-")[1];
		String sql = "";
		if(status==null || status.equals("0")){
			status="";
		}
		int res=0;
		String newstatus=status;
		if(newstatus.equals("1")){
			newstatus="Processed";
		}
		if(newstatus.equals("2")){
			newstatus="Paid";
		}
		StringBuffer buffer =new StringBuffer();
		try {
			
			buffer.append("select apm_payroll_salary_master.emp_id from apm_payroll_salary_master ");
			buffer.append("inner join apm_payroll_weeksheet on apm_payroll_weeksheet.emp_id=apm_payroll_salary_master.emp_id ");
			if(!newstatus.equals("")){
			buffer.append("inner join apm_payroll_final_salary on apm_payroll_final_salary.emp_id=apm_payroll_salary_master.emp_id ");
			}
			buffer.append("where apm_payroll_weeksheet.month='"+month+"' and days>0  ");
		
			if(!newstatus.equals("")){
				buffer.append("and apm_payroll_final_salary.month='"+monthname+"' and apm_payroll_final_salary.year='"+year1+"' ");
			buffer.append("and apm_payroll_final_salary.finalstatus='"+newstatus+"'");
			}
//				sql = "select emp_id, basic from apm_payroll_salary_master";
				
			PreparedStatement ps = connection.prepareStatement(buffer.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Salary salary = new Salary();
				salary.setEmp_id(rs.getString(1));
				Salary master=new Salary();
				boolean checkAttend=payrollMasterDAO.checkAttendanceDone(salary,month);
				
				if(status=="" || status.equals("0")){
					boolean checkExist=payrollMasterDAO.checkExistRecordWithParam(salary.getEmp_id(),monthname,year1);
					if(!checkExist){
				 master = getMasterSalary(salary.getEmp_id(),pagination);
					}
//				 else{
//						 master=getfinalReportData(salary.getEmp_id(), monthname, year1,status,"apm_payroll_final_salary");
//					}
				}else{
				 master=getfinalReportData(salary.getEmp_id(), monthname, year1,status,"apm_payroll_final_salary",pagination);
				}
				salary.setStatus(status);
				if(master.isChkobj() && checkAttend){
					res=res+1;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getweekdays() {
		String res="";
		try {
			String sql = "select weekleave from apm_payroll_company where id=1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			res=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public int updateweekdays(String weekday) {
		int result = 0;

		try {
			String sql = "update apm_payroll_company set weekleave=? where id=1 ";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, weekday);
			result = ps.executeUpdate();
				
		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public ArrayList<Payroll> holidaylist(String id) {
		ArrayList<Payroll> list = new ArrayList<Payroll>();

		try {
			String sql = "select date,event from apm_payroll_holiday where id="+id+" ";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Payroll payroll = new Payroll();
				payroll.setId(Integer.parseInt(id));
				payroll.setDate(rs.getString("date"));
				payroll.setEvent(rs.getString("event"));
				list.add(payroll);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public double getWorkingDays(String month, String emp_id) {
		double res=0;
		try {
			String sql = "select days from apm_payroll_weeksheet where month='"+month+"' and emp_id='"+emp_id+"'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public int updateLeavedays(Payroll payroll,Employee employee) {
		int result = 0;

		try {
			String sql = "update apm_payroll_employee set remain_leave=? where userid='"+payroll.getUserid()+"' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			int days=Integer.parseInt(employee.getRemain_leave())- Integer.parseInt(payroll.getDays());
			ps.setInt(1, days);
			
			if(days>0){
				result = ps.executeUpdate();
			}
			
				
		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public int getLeaveDays(String month, String emp_id) {
		int res=0;
		try {
			String sql = "select leaves from apm_payroll_weeksheet where month='"+month+"' and emp_id='"+emp_id+"'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public ArrayList<Payroll> getOTList(Pagination pagination, String userid, LoginInfo loginInfo, String fromDate,
			String toDate, String otstatus,String empname) {

		String sql = "";
		ArrayList<Payroll> list = new ArrayList<Payroll>();

		PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);
		StringBuffer buffer = new StringBuffer();
		try {
				buffer.append("Select id, payroll_ot_request.empid, fromtime, totime, date, req_user, remark, payroll_ot_request.status, approveby1, approveremark1, approveby2, approveremark2,totaltime from payroll_ot_request ");
				buffer.append("left join apm_payroll_employee on apm_payroll_employee.empid=payroll_ot_request.empid ");
				buffer.append("where date between '"+fromDate+"' and '"+toDate+"' ");
				if(!otstatus.equals("")){
					if(!otstatus.equals("0")){
						buffer.append(" and payroll_ot_request.status="+otstatus+"");
					}
				}
				if(empname!=null){
					if(!empname.equals("")){
						buffer.append(" and apm_payroll_employee.name like '%"+empname+"%'");
					}
				}
				buffer.append(" order by id desc ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Payroll payroll = new Payroll();
				payroll.setId(rs.getInt(1));
				payroll.setEmp_id(rs.getString(2));
				payroll.setName(employeeDAO.getEmployeeName(rs.getString(2)));
				String hh=rs.getString(3).split(":")[0];
				String mm=rs.getString(3).split(":")[1];
				int hourOfDay=Integer.parseInt(hh);
				int minute=Integer.parseInt(mm);
				String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");	 
				String hh1=rs.getString(4).split(":")[0];
				String mm1=rs.getString(4).split(":")[1];
				int hourOfDay1=Integer.parseInt(hh1);
				int minute1=Integer.parseInt(mm1);
				String apmpm1 =  ((hourOfDay1 > 12) ? hourOfDay1 % 12 : hourOfDay1) + ":" + (minute1 < 10 ? ("0" + minute1) : minute1) + " " + ((hourOfDay1 >= 12) ? "PM" : "AM");
				payroll.setFromtime(apmpm);
				payroll.setTotime(apmpm1);
				payroll.setDate(DateTimeUtils.getCommencingDate1(rs.getString(5)));
				payroll.setRequestuser(rs.getString(6));
				payroll.setRequestremark(rs.getString(7));
				payroll.setStatus(rs.getString(8));
				payroll.setApproveby1(rs.getString(9));
				payroll.setApproveremark1(rs.getString(10));
				payroll.setApproveby2(rs.getString(11));
				payroll.setApproveremark2(rs.getString(12));
				payroll.setTime(rs.getString(13));
				list.add(payroll);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;
	}

	public int saveRequestOT(Payroll payroll) {

		int result = 0;
		try {
			String sql = "insert into payroll_ot_request (empid, fromtime, totime, date, req_user, remark, status,totaltime) values (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, payroll.getEmp_id());
			ps.setString(2, payroll.getFromtime());
			ps.setString(3, payroll.getTotime());
			ps.setString(4, payroll.getRequestdate());
			ps.setString(5, payroll.getUserid());
			ps.setString(6, payroll.getOtreason());
            ps.setString(7, "0");
            ps.setString(8, payroll.getTime());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Payroll getotDetails(String id) {

		Payroll payroll = new Payroll();
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		try {
			String sql = "select id, empid, fromtime, totime, date, req_user, remark, status, approveby1, approveremark1, approveby2, approveremark2, totaltime,approvedate1,approvedate2 from payroll_ot_request where id ="
					+ id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				payroll.setId(rs.getInt(1));
				payroll.setEmp_id(rs.getString(2));
				payroll.setFromtime(rs.getString(3));
				payroll.setTotime(rs.getString(4));
				payroll.setRequestdate(DateTimeUtils.changeDateFormattoPicker(rs.getString(5)));
				payroll.setRequestuser(rs.getString(6));
				payroll.setRequestremark(rs.getString(7));
				payroll.setStatus(rs.getString(8));
				payroll.setApproveby1(rs.getString(9));
				payroll.setApproveremark1(DateTimeUtils.isNull(rs.getString(10)));
				payroll.setApproveby2(rs.getString(11));
				payroll.setApproveremark2(DateTimeUtils.isNull(rs.getString(12)));
				payroll.setTime(rs.getString(13));
				payroll.setApprovedate1(rs.getString(14));
				payroll.setApprovedate2(rs.getString(15));
				PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
				String requestname =employeeDAO.getEmployeeName(rs.getString(2));
				payroll.setRequestname(requestname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payroll;
	
	}

	public int updateot(Payroll payroll, Employee employee,String sts) {
		int result = 0;

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String currentdate = dateFormat.format(calendar.getTime());
			String sql ="";
			if(sts.equals("1")){
				sql = "update payroll_ot_request set status=?,approveby2=?, approveremark2=?,approvedate2=? where id='"+payroll.getId()+"' ";
			}else{
				sql = "update payroll_ot_request set status=?,approveby1=?, approveremark1=?,approvedate1=? where id='"+payroll.getId()+"' ";
			}
			 
			PreparedStatement ps = connection.prepareStatement(sql);
			if(sts.equals("1")){
				ps.setInt(1, 2);
			}else{
				ps.setInt(1, 1);
			}
			ps.setString(2, payroll.getApproveby1());
			ps.setString(3, payroll.getApproveremark1());
			ps.setString(4, currentdate);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public int updateRejectOt(String id, String notes, String reject, String userid,String sts) {
		int result = 0;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			String currentdate = dateFormat.format(calendar.getTime());

			String sql = "";
			if(sts.equals("1")){
				sql = "update payroll_ot_request set status=" + reject + ", approveremark2='" + notes + "', approvedate2='"
						+ currentdate + "',approveby2='" + userid + "' where id=" + id + " ";
			}else{
				sql = "update payroll_ot_request set status=" + reject + ", approveremark1='" + notes + "', approvedate1='"
						+ currentdate + "',approveby1='" + userid + "' where id=" + id + " ";
			}
			

			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int getTotalOTlist(String userid, LoginInfo loginInfo, String fromDate, String toDate, String otstatus,
			String empname) {
		String sql = "";
		int res=0; 
		StringBuffer buffer = new StringBuffer();
		try {
				buffer.append("Select count(*) from payroll_ot_request ");
				buffer.append("left join apm_payroll_employee on apm_payroll_employee.empid=payroll_ot_request.empid ");
				buffer.append("where date between '"+fromDate+"' and '"+toDate+"' ");
				if(!otstatus.equals("")){
					if(!otstatus.equals("0")){
						buffer.append(" and payroll_ot_request.status="+otstatus+"");
					}
				}
				if(empname!=null){
					if(!empname.equals("")){
						buffer.append(" and apm_payroll_employee.name like '%"+empname+"%'");
					}
				}
				buffer.append(" order by id desc ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
		return res;
	}

	public String getdepartmentEmail(String department) {
		String res="";
		try {
			String sql = "select email from apm_payroll_department where id="+department+"";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			res=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public int updateOT(Payroll payroll) {
		int result = 0;

		try {

			String sql = "update payroll_ot_request set  fromtime=?, totime=?,remark=?,totaltime=? where id="
					+ payroll.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, payroll.getFromtime());
			ps.setString(2, payroll.getTotime());
			ps.setString(3, payroll.getOtreason());
			ps.setString(4, payroll.getTime());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Payroll> getAlldocumentlist() {
		ArrayList<Payroll> list=new ArrayList<Payroll>();
		
		
		try {
			String sql="select id,name from payroll_document_list";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
            	
            	Payroll payroll=new Payroll();
            	payroll.setId(rs.getInt(1));
            	payroll.setName(DateTimeUtils.isNull(rs.getString(2)));
            	list.add(payroll);
            	
            }
            
			
		} catch (Exception e) {

		   e.printStackTrace();
		}

		
		return list;
	}

	public int adddocumentlist(Payroll payroll) {
int result=0;
		
		try {
		
			String sql="insert into payroll_document_list (name) values (?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getName());
			result=ps.executeUpdate();

		} catch (Exception e) {

		   e.printStackTrace();
		}
		return result;
	}

	public Payroll getdocumentlist(String id) {
Payroll payroll=new Payroll();
		
		try {
			String sql="select name from payroll_document_list where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				payroll.setName(rs.getString(1));
			}
			
		} catch (Exception e) {

		    e.printStackTrace();
		}
		
		return payroll;
	}

	public int updatedocumentlist(Payroll payroll) {
int res=0;
		
		try {
			String sql="update payroll_document_list set name=? where id="+payroll.getId()+""; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, payroll.getName());
			
			res=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return res;
	}

	@Override
	public double getWorkingMinutes(String month, String emp_id) {
		double res=0;
		try {
			String sql = "select totalmin from apm_payroll_weeksheet where month='"+month+"' and emp_id='"+emp_id+"'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public ArrayList<Salary> manageSalaryDetails1(String month, String branchid, String status, Pagination pagination,
			LoginInfo loginInfo, String department, String newmonth, String sal_date1, String new_sal_date) {
		DecimalFormat df = new DecimalFormat("0.00");
		ArrayList<Salary> list = new ArrayList<Salary>();
		AttendanceDAO attendanceDAO = new JDBCAttendanceDAO(connection);
		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);
		PayrollEmployeeDAO employeeDAO = new JDBCPayrollEmployeeDAO(connection);
		String monthname=month.split("-")[0];
		String year1=month.split("-")[1];
		String sql = "";
		if(status==null || status.equals("0")){
			status="";
		}
		
		if(department==null || department.equals("0")){
			department="";
		}
		String newstatus=status;
		if(newstatus.equals("1")){
			newstatus="Processed";
		}
		if(newstatus.equals("2")){
			newstatus="Paid";
		}
		if(!loginInfo.isPayrollaccess()){
			newstatus="Paid";
			status="2";
		}
		StringBuffer buffer =new StringBuffer();
		try {

			buffer.append("select apm_payroll_salary_master.emp_id, apm_payroll_salary_master.basic,apm_payroll_salary_master.net_pay from apm_payroll_salary_master ");
			buffer.append("inner join apm_payroll_weeksheet on apm_payroll_weeksheet.emp_id=apm_payroll_salary_master.emp_id ");
			buffer.append("inner join apm_payroll_employee on apm_payroll_salary_master.emp_id=apm_payroll_employee.empid ");
			if(!newstatus.equals("")){
			buffer.append("inner join apm_payroll_final_salary on apm_payroll_final_salary.emp_id=apm_payroll_salary_master.emp_id ");
			}
			buffer.append("where apm_payroll_weeksheet.month='"+month+"' and days>0  ");
			if(!newstatus.equals("")){
				buffer.append("and apm_payroll_final_salary.month='"+monthname+"' and apm_payroll_final_salary.year='"+year1+"' ");
			buffer.append("and apm_payroll_final_salary.finalstatus='"+newstatus+"' ");
			}
			if(!loginInfo.isPayrollaccess()){
				buffer.append(" and apm_payroll_final_salary.userid='"+loginInfo.getUserId()+"'");
			}
			if(!department.equals("")){
				buffer.append(" and apm_payroll_employee.dept_id='"+department+"' ");
			}
			
			buffer.append(" group by apm_payroll_weeksheet.emp_id ");
//				sql = "select emp_id, basic from apm_payroll_salary_master";
			sql=buffer.toString();
			 if(pagination!=null)
				{
					sql=pagination.getSQLQuery(sql);
				}
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Salary salary = new Salary();
				salary.setEmp_id(rs.getString(1));
				Salary master=new Salary();
				boolean checkAttend=payrollMasterDAO.checkAttendanceDone(salary,month);
				
				 double totalmin=attendanceDAO.getSumoftime(sal_date1,new_sal_date,rs.getString(1));
				if(status=="" || status.equals("0")){
					boolean checkExist=payrollMasterDAO.checkExistRecordWithParam(salary.getEmp_id(),monthname,year1);
					if(!checkExist){
				 master = getMasterSalary(salary.getEmp_id(),pagination);
					}
//				 else{
//						 master=getfinalReportData(salary.getEmp_id(), monthname, year1,status,"apm_payroll_final_salary");
//					}
				}else{
				 master=getfinalReportData(salary.getEmp_id(), monthname, year1,status,"apm_payroll_final_salary",pagination);
				}
				salary.setStatus(status);
				if(master.isChkobj() && checkAttend){
				Employee employee = employeeDAO.getEmployee(salary.getEmp_id());
				salary.setEmp_name(employee.getName());
				salary.setDesignation(employee.getDesignation());
				salary.setDatejoin(employee.getDate_join());
				salary.setEmail(employee.getEmail());
				salary.setEmpcode(employee.getEmpcode());
				salary.setNetpay(master.getNetpay());
				Salary sal = payrollMasterDAO.getSalaryDetails(salary.getEmp_id());
				salary.setId(sal.getId());
				if(master.getBasic()!=null){
				int perhourrate = Integer.parseInt(master.getBasic()) / 30;
				}
               /* salary.setDays(days);*/
				/*int total_hours = attendanceDAO.getTotalHours(month, salary.getEmp_id());
				float numberofdayswork = total_hours / 8;*/
				double numdays = getDaysForSalary(salary.getEmp_id(),month);
				numberofdayswork = (int) numdays;

				/*String salaryTotal = attendanceDAO.totalSalaryForAttendence(salary.getEmp_id(),month);
				salary.setSalaryTotal(salaryTotal);*/
				double basic=0;
//				String basic1 =salary.getBasic();
				if(master.getBasic()!=null){
				 basic = Double.parseDouble(master.getBasic());
				}else{
					basic=0;
				}
				salary.setBasic(String.valueOf(basic));
//				salary.setBasic(String.valueOf(master.getBasic())); // origianl sal
				
	
				// DA on TA

				Allowance da_on_ta = getAllowanceMaster("DA ON TA");
				String daonta = "";

				if (da_on_ta.getAllowanceType() != null) {

					if (!da_on_ta.getAllowanceType().equals("")) {

						String fixeorrate = da_on_ta.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(da_on_ta.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							daonta = String.valueOf(amount);
						} else {

							daonta = da_on_ta.getValue();
						}

					} else {
						daonta = "0";
					}
				} else {

					daonta = "0";
				}
				if(daonta.equals("0")){
					salary.setDa_on_ta((master.getDa_on_ta()));
				}else{
				salary.setDa_on_ta(daonta);
				}
				// for special pay

				Allowance special_pay = getAllowanceMaster("SPECIAL PAY");
				String specialpay = "";

				if (special_pay.getAllowanceType() != null) {

					if (!special_pay.getAllowanceType().equals("")) {

						String fixeorrate = special_pay.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(special_pay.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							specialpay = String.valueOf(amount);
						} else {

							specialpay = special_pay.getValue();
						}

					} else {
						specialpay = "0";
					}
				} else {

					specialpay = "0";
				}
				if(special_pay.equals("0")){
					salary.setSpecial_pay(master.getSpecial_pay());
				}else{
					
				
				salary.setSpecial_pay(specialpay);
				}
				// personal pay

				Allowance personal_pay = getAllowanceMaster("PERSONAL PAY");
				String personalpay = "";

				if (personal_pay.getAllowanceType() != null) {

					if (!personal_pay.getAllowanceType().equals("")) {

						String fixeorrate = personal_pay.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(personal_pay.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							personalpay = String.valueOf(amount);
						} else {

							personalpay = personal_pay.getValue();
						}

					} else {
						personalpay = "0";
					}
				} else {

					personalpay = "0";
				}
				if(personalpay.equals("0")){
					salary.setPersonal_pay(master.getPersonal_pay());
				}else{
				salary.setPersonal_pay(personalpay);
				}
				// Transport Allowance

				Allowance transport_allowance = getAllowanceMaster("TRANSPORT ALLOWANCE");
				String transportallowance = "";

				if (transport_allowance.getAllowanceType() != null) {

					if (!transport_allowance.getAllowanceType().equals("")) {

						String fixeorrate = transport_allowance.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(transport_allowance.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							transportallowance = String.valueOf(amount);
						} else {

							transportallowance = transport_allowance.getValue();
						}

					} else {
						transportallowance = "0";
					}
				} else {

					transportallowance = "0";
				}
				if(transport_allowance.equals("0")){
					salary.setTransport_allowance(master.getTransport_allowance());
				}else{
				salary.setTransport_allowance(transportallowance);
				}
				// hra

				Allowance hra = getAllowanceMaster("HRA");
				String HRA = "";

				if (hra.getAllowanceType() != null) {

					if (!hra.getAllowanceType().equals("")) {

						String fixeorrate = hra.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(hra.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							HRA = String.valueOf(amount);
						} else {

							HRA = hra.getValue();
						}

					} else {
						HRA = "0";
					}
				} else {

					HRA = "0";
				}
				if(HRA.equals("0")){
					salary.setHra(master.getHra());
				}else {
					salary.setHra(HRA);	
				}
				

				// da

				Allowance da = getAllowanceMaster("DA");
				String DA = "";

				if (da.getAllowanceType() != null) {

					if (!da.getAllowanceType().equals("")) {

						String fixeorrate = da.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(da.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							DA = String.valueOf(amount);
						} else {

							DA = da.getValue();
						}

					} else {
						DA = "0";
					}
				} else {

					DA = "0";
				}
				if(DA.equals("0")){
					salary.setDa(master.getDa());	
				}else{
				salary.setDa(DA);
				}
				// npa

				Allowance npa = getAllowanceMaster("NPA");
				String NPA = "";

				if (npa.getAllowanceType() != null) {

					if (!npa.getAllowanceType().equals("")) {

						String fixeorrate = npa.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(npa.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							NPA = String.valueOf(amount);
						} else {

							NPA = npa.getValue();
						}

					} else {
						NPA = "0";
					}
				} else {

					NPA = "0";
				}
				if(npa.equals("0")){
					salary.setNpa(master.getNpa());
				}else{
				salary.setNpa(NPA);
				}
				// Medical Allowance

				Allowance mediacal_allowance = getAllowanceMaster("MEDICAL AllOWANCE");
				String mediacalallowance = "";

				if (mediacal_allowance.getAllowanceType() != null) {

					if (!mediacal_allowance.getAllowanceType().equals("")) {

						String fixeorrate = mediacal_allowance.getAllowanceType();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(mediacal_allowance.getValue());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							mediacalallowance = String.valueOf(amount);
						} else {

							mediacalallowance = mediacal_allowance.getValue();
						}

					} else {
						mediacalallowance = "0";
					}
				} else {

					mediacalallowance = "0";
				}
				if(mediacalallowance.equals("0")){
					salary.setMedical_allowance(master.getMedical_allowance());
				}else{
				salary.setMedical_allowance(mediacalallowance);
				}
				// deductions
				// emp_pf

				Payroll emp_pf = getDeductionMaster("EMP PF");
				String emppf = "";

				if (emp_pf.getDeduction_type() != null) {

					if (!emp_pf.getDeduction_type().equals("")) {

						String fixeorrate = emp_pf.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(emp_pf.getEmp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							emppf = String.valueOf(amount);
						} else {

							emppf = emp_pf.getEmp_contribution();
						}

					} else {
						emppf = "0";
					}
				} else {

					emppf = "0";
				}
				if(emppf.equals("0")){
					salary.setEmp_pf(master.getEmp_pf());
				}else{
				salary.setEmp_pf(emppf);
				}
				// emp_esi

				Payroll emp_esi = getDeductionMaster("EMP ESI");
				String empesi = "";

				if (emp_esi.getDeduction_type() != null) {

					if (!emp_esi.getDeduction_type().equals("")) {

						String fixeorrate = emp_esi.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(emp_esi.getEmp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							empesi = String.valueOf(amount);
						} else {

							empesi = emp_esi.getEmp_contribution();
						}

					} else {
						empesi = "0";
					}
				} else {

					empesi = "0";
				}
				if(empesi.equals("0")){
					salary.setEmp_esi(master.getEmp_esi());
				}else{
				salary.setEmp_esi(empesi);
				}
				// comp_pf

				Payroll comp_pf = getDeductionMaster("COMPANY PF");
				String comppf = "";

				if (comp_pf.getDeduction_type() != null) {

					if (!comp_pf.getDeduction_type().equals("")) {

						String fixeorrate = comp_pf.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(comp_pf.getComp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							comppf = String.valueOf(amount);
						} else {

							comppf = comp_pf.getComp_contribution();
						}

					} else {
						comppf = "0";
					}
				} else {

					comppf = "0";
				}
				if(comppf.equals("0")){
					salary.setComp_pf(master.getComp_pf());
				}else{
				salary.setComp_pf(comppf);
				}
				// comp esi

				Payroll comp_esi = getDeductionMaster("COMPANY ESI");
				String compesi = "";

				if (comp_esi.getDeduction_type() != null) {

					if (!comp_esi.getDeduction_type().equals("")) {

						String fixeorrate = comp_esi.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(comp_esi.getComp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							compesi = String.valueOf(amount);
						} else {

							compesi = comp_esi.getComp_contribution();
						}

					} else {
						compesi = "0";
					}
				} else {

					compesi = "0";
				}
				if(compesi.equals("0")){
					salary.setComp_esi(master.getComp_esi());
				}else{
				salary.setComp_esi(compesi);
				}
				// Professional Tax

				Payroll pt = getDeductionMaster("PROFESSIONAL TAX");
				String PT = "";

				if (pt.getDeduction_type() != null) {

					if (!pt.getDeduction_type().equals("")) {

						String fixeorrate = pt.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(pt.getEmp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							PT = String.valueOf(amount);
						} else {

							PT = pt.getEmp_contribution();
						}

					} else {
						PT = "0";
					}
				} else {

					PT = "0";
				}
				if(PT.equals("0")){
					salary.setPrefessional_tax(master.getPrefessional_tax());
				}else{
				salary.setPrefessional_tax(PT);
				}
				//conveyance

				Payroll cy = getDeductionMaster("conveyance");
				String CY = "";

				if (cy.getDeduction_type() != null) {

					if (!cy.getDeduction_type().equals("")) {

						String fixeorrate = cy.getDeduction_type();
						if (fixeorrate.equalsIgnoreCase("Percentage")) {

							int rate = Integer.parseInt(pt.getEmp_contribution());
							int amount = Integer.parseInt(salary.getBasic()) * rate / 100;
							PT = String.valueOf(amount);
						} else {

							CY = cy.getEmp_contribution();
						}

					} else {
						CY = "0";
					}
				} else {

					CY = "0";
				}
				if(CY.equals("0")){
					salary.setConveyance(master.getConveyance());
				}else{
					salary.setConveyance(CY);
				}
				
				
				
				
//				if (sal.getConveyance()!=null) {
//
//					if (!sal.getConveyance().equals("")) {
//						salary.setConveyance(salary.getConveyance());
//
//						} else {
//
//							salary.setConveyance("0");
//						}
//
//					} else {
//						salary.setConveyance("0");
//					}
//
//				if(salary.getConveyance().equals("0")){
//					salary.setConveyance(master.getConveyance());
//
//				}
				//leaves
				if (sal.getLeave()!=null) {

					if (!sal.getLeave().equals("")) {
						salary.setLeave(sal.getConveyance());

						} else {

							salary.setLeave("0");
						}

					} else {
						salary.setLeave("0");
					}

				if(salary.getLeave().equals("0")){
					salary.setLeave(master.getLeave());

				}
				// other income

				if (sal.getOtherincome() != null) {

					if (!sal.getOtherincome().equals("")) {
						salary.setOtherincome(sal.getOtherincome());
					} else {
						salary.setOtherincome("0");
					}
				} else {
					salary.setOtherincome("0");
				}
				if(salary.getOtherincome().equals("0")){
					salary.setOtherincome(master.getOtherincome());
				}
				// other deductions

				if (sal.getOther_deduction() != null) {

					if (!sal.getOther_deduction().equals("")) {
						salary.setOther_deduction(sal.getOther_deduction());
					} else {
						salary.setOther_deduction("0");
					}
				} else {
					salary.setOther_deduction("0");
				}
				if(salary.getOther_deduction().equals("0")){
					salary.setOther_deduction(master.getOther_deduction());
				}
				if (sal.getTds() != null) {

					if (!sal.getTds().equals("")) {
						salary.setTds(sal.getTds());
					} else {
						salary.setTds("0");
					}
				} else {
					salary.setTds("0");
				}
				if(salary.getTds().equals("0")){
					salary.setTds(master.getTds());
				}
				if(master.getLoan()==null){
					
				}else if(master.getLoan().equals("")){
					master.setLoan("0");
				}else{
					salary.setLoan(master.getLoan());	
				}
				// deductions

				int emp_pff = Integer.parseInt(nullwithzero(salary.getEmp_pf()));
				int emp_esii = Integer.parseInt(nullwithzero(salary.getEmp_esi()));
				int cmp_pff = Integer.parseInt(nullwithzero(salary.getComp_pf()));
				int cmp_esii = Integer.parseInt(nullwithzero(salary.getComp_esi()));
				int ptt = Integer.parseInt(nullwithzero(salary.getPrefessional_tax()));
				int otherr = Integer.parseInt(nullwithzero(salary.getOther_deduction()));
				double tdss = Double.parseDouble(nullwithzero(salary.getTds()));
				double leave = Double.parseDouble((nullwithzero(salary.getLeave())));
				double loan = Double.parseDouble((nullwithzero(salary.getLoan())));
				double tota = emp_pff + emp_esii + cmp_pff + cmp_esii + ptt + otherr + tdss+leave+loan;
				salary.setDeductions(String.valueOf(tota));
				if(master.getPerdevallow()==null){
					
				}else if(master.getPerdevallow().equals("")){
					master.setPerdevallow("0");
				}else{
					salary.setPerdevallow(master.getPerdevallow());	
				}
				if(master.getOt()==null){
					
				}else if(master.getOt().equals("")){
					master.setOt("0");
				}else{
					salary.setOt(master.getOt());	
				}
				
				
				if(master.getAdvance()==null){
									
				}else if(master.getAdvance().equals("")){
							master.setAdvance("0");
				}else{
									salary.setAdvance(master.getAdvance());	
				}
				
				
				
				// Allowances

				double daontaa = Double.parseDouble(nullwithzero(salary.getDa_on_ta()));
				double special = Double.parseDouble(nullwithzero(salary.getSpecial_pay()));
				int perosnal = Integer.parseInt(nullwithzero(salary.getPersonal_pay()));
				int transport = Integer.parseInt(nullwithzero(salary.getTransport_allowance()));
				double hraa = Double.parseDouble(nullwithzero(salary.getHra()));
				double daa = Double.parseDouble(nullwithzero(salary.getDa()));
				int npaa = Integer.parseInt(nullwithzero(salary.getNpa()));
				int otherrr = Integer.parseInt(nullwithzero(salary.getOtherincome()));
				double convey=Double.parseDouble(nullwithzero(salary.getConveyance()));
				double perdevallow=Double.parseDouble(nullwithzero(salary.getPerdevallow()));
				double medical=Double.parseDouble(nullwithzero(salary.getMedical_allowance()));
				double ot = Double.parseDouble(nullwithzero(salary.getOt()));
				double advance = Double.parseDouble(nullwithzero(salary.getAdvance()));
				double leave1=employeeDAO.getPayroll_leave(salary.getEmp_id(),"");
				
				
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
				//sunday=getSundays(Integer.parseInt(salary.getYear()),  newmonth,temp1);
				}
				Calendar mycal1 = new GregorianCalendar(Integer.parseInt(year1), Integer.parseInt(newmonth), idays);
				int daysInMonth = mycal1.getActualMaximum(Calendar.DAY_OF_MONTH);
				int actualworkingdays=daysInMonth-sunday;
				
				double allowances = daontaa + special + perosnal + transport + hraa + daa + npaa + otherrr+convey+perdevallow+medical+ot+advance;
				salary.setAllowances(String.valueOf(allowances));
				
				allowances = Double.parseDouble(salary.getAllowances());
				double gross = allowances + basic;
				salary.setGross_pay(String.valueOf(gross));

				// net pay

				gross = Double.parseDouble(salary.getGross_pay());
				double deductions = Double.parseDouble(salary.getDeductions());
				double net = gross - deductions;
				double perdayamt = rs.getDouble(3)/ actualworkingdays;
				
				 leaveamt=leave1*perdayamt;
				 net=net+leaveamt;
				salary.setCalnetpay(String.valueOf(df.format(net)));

			/*	Payroll loan = getLoanFromEmpID(salary.getEmp_id());*/
				/*if (loan.getAmount() != null) {

					if (!loan.getAmount().equals("")) {
						salary.setLoan(loan.getAmount());
					} else {
						salary.setLoan("0");
					}
				} else {
					salary.setLoan("0");
				}*/

				Payroll payroll = getLeaveFormEmpID(salary.getEmp_id());
				int paidleaves = 0, totaldays = 0, unpaidleaves = 0;
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
				String dates[] = month.split("-");

				Date date4 = new SimpleDateFormat("MMM").parse(month);// put
																		// your
																		// month
																		// name
																		// here
				Calendar cal = Calendar.getInstance();
				cal.setTime(date4);
				int monthIndex = cal.get(Calendar.MONTH);

				int year = Integer.parseInt(dates[1]);
				calendar.set(year, monthIndex, 1);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = calendar.getTime();
				String fromdate = dateFormat.format(date);

				int numDaysinMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				calendar.set(Calendar.DAY_OF_MONTH, numDaysinMonth);
				Date date2 = calendar.getTime();
				String todate = dateFormat.format(date2);

				int holidays = getHolidaysinMonth(fromdate, todate);
				salary.setUnpaidleaves(String.valueOf(unpaidleaves));
				salary.setPaidleaves(String.valueOf(holidays));
				salary.setTotaldays(String.valueOf(numDaysinMonth));
				salary.setWorkeddays(String.valueOf(numberofdayswork));
				/*updateSalary(salary);*/
		
				list.add(salary);
				}
			}
				
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
}