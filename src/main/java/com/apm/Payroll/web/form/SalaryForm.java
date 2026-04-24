package com.apm.Payroll.web.form;

import java.util.ArrayList;

import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.Registration.eu.entity.Clinic;

public class SalaryForm {
	
	public String getDate_join() {
		return date_join;
	}
	public void setDate_join(String date_join) {
		this.date_join = date_join;
	}
	int id;
	String emp_id;
	String branch_id;
	private double days;
	private String month;
	private String leavehour;
	private String othour;
	private String actualnetpay;
	private String date_join;
	public String getLeavehour() {
		return leavehour;
	}
	public void setLeavehour(String leavehour) {
		this.leavehour = leavehour;
	}
	public String getOthour() {
		return othour;
	}
	public void setOthour(String othour) {
		this.othour = othour;
	}
	private String salaryId;
	private String year;
	private String empcode;
	private String totearn;
	private String totalworkinghour;
	private String totded;
	private String actualworkingday;
	ArrayList<Clinic> locationAdressList;
	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}
	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}
	public String getTotearn() {
		return totearn;
	}
	public void setTotearn(String totearn) {
		this.totearn = totearn;
	}
	public String getTotded() {
		return totded;
	}
	public void setTotded(String totded) {
		this.totded = totded;
	}
	String searchtext;
	
	public String getSearchtext() {
		return searchtext;
	}
	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}
	public String getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}
	public double getDays() {
		return days;
	}
	public void setDays(double days) {
		this.days = days;
	}
	// For Deduction
	String washing;
	private String fixedsalary;
	public String getFixedsalary() {
		return fixedsalary;
	}
	public void setFixedsalary(String fixedsalary) {
		this.fixedsalary = fixedsalary;
	}
	String conveyance;
	public String getWashing() {
		return washing;
	}
	public void setWashing(String washing) {
		this.washing = washing;
	}
	public String getConveyance() {
		return conveyance;
	}
	public void setConveyance(String conveyance) {
		this.conveyance = conveyance;
	}
	
	public String getVehiclepass() {
		return vehiclepass;
	}
	public void setVehiclepass(String vehiclepass) {
		this.vehiclepass = vehiclepass;
	}
	String perdevallow;
	public String getPerdevallow() {
		return perdevallow;
	}
	public void setPerdevallow(String perdevallow) {
		this.perdevallow = perdevallow;
	}
	
	//for logo
	private String clinicName;
	private String clinicOwner;
	private String owner_qualification;
	private String landLine;
	private String clinicemail;
	private String websiteUrl;
	private String clinicLogo;
	private String ot;
	private String advance;
	public String getOt() {
		return ot;
	}
	public void setOt(String ot) {
		this.ot = ot;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getClinicOwner() {
		return clinicOwner;
	}
	public void setClinicOwner(String clinicOwner) {
		this.clinicOwner = clinicOwner;
	}
	public String getOwner_qualification() {
		return owner_qualification;
	}
	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}
	public String getLandLine() {
		return landLine;
	}
	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}
	public String getClinicemail() {
		return clinicemail;
	}
	public void setClinicemail(String clinicemail) {
		this.clinicemail = clinicemail;
	}
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	public String getClinicLogo() {
		return clinicLogo;
	}
	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}
	
	
	String vehiclepass;
	
	String com_id;
	String dept_id;
	String emp_name;
	String date;
	String basic;
	String allowances;
	String da_on_ta;
	String special_pay;
	String personal_pay;
	String transport_allowance;
	String hra;
	String da;
	String npa;
	String emp_pf;
	String emp_esi;
	String comp_pf;
	String comp_esi;
	String taxable;
	String otherincome;
	String netpay;
	private String salaryTotal;
	public String getSalaryTotal() {
		return salaryTotal;
	}
	public void setSalaryTotal(String salaryTotal) {
		this.salaryTotal = salaryTotal;
	}
	String totaldays;
	String workingdays;
	String total_leaves;
	
	String no_sundays;
	String currentMonth;
	String monthyear;
	String monthandyear;
	
	
	
	
	
	
	
	
	public String getMonthandyear() {
		return monthandyear;
	}
	public void setMonthandyear(String monthandyear) {
		this.monthandyear = monthandyear;
	}
	ArrayList<Salary> incrementlist;
	
	
	String gross_pay;
	String incre_date;
	String deductions;
	
	String encashable;
	
	
	
	
	
	String salary_bank;
	
	String fixed_deduction;
	String leave;
	String canteen;
	String other_deduction;
	
	
	String tds;
	String other_income;
	
	
	String loan;
	String medical_allowance;
	

	ArrayList<Employee> branchlist;
	

	String prefessional_tax;
	
	
	
	// company profile
	String company_name;
	String company_address;
	String comp_phone;
	String comp_email;
	String comp_fax;
	String comp_website;
	String emp_role;
	String holidays;
	
	
	
	
	String workeddays;
	
	
	String paidleaves;
	String unpaidleaves;
	String branch;
	
	
	
	
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPaidleaves() {
		return paidleaves;
	}
	public void setPaidleaves(String paidleaves) {
		this.paidleaves = paidleaves;
	}
	public String getUnpaidleaves() {
		return unpaidleaves;
	}
	public void setUnpaidleaves(String unpaidleaves) {
		this.unpaidleaves = unpaidleaves;
	}
	public String getHolidays() {
		return holidays;
	}
	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	public String getWorkeddays() {
		return workeddays;
	}
	public void setWorkeddays(String workeddays) {
		this.workeddays = workeddays;
	}
	public String getEmp_role() {
		return emp_role;
	}
	public void setEmp_role(String emp_role) {
		this.emp_role = emp_role;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getComp_phone() {
		return comp_phone;
	}
	public void setComp_phone(String comp_phone) {
		this.comp_phone = comp_phone;
	}
	public String getComp_email() {
		return comp_email;
	}
	public void setComp_email(String comp_email) {
		this.comp_email = comp_email;
	}
	public String getComp_fax() {
		return comp_fax;
	}
	public void setComp_fax(String comp_fax) {
		this.comp_fax = comp_fax;
	}
	public String getComp_website() {
		return comp_website;
	}
	public void setComp_website(String comp_website) {
		this.comp_website = comp_website;
	}
	public String getPrefessional_tax() {
		return prefessional_tax;
	}
	public void setPrefessional_tax(String prefessional_tax) {
		this.prefessional_tax = prefessional_tax;
	}
	public String getOther_deduction() {
		return other_deduction;
	}
	public void setOther_deduction(String other_deduction) {
		this.other_deduction = other_deduction;
	}
	public String getOther_income() {
		return other_income;
	}
	public void setOther_income(String other_income) {
		this.other_income = other_income;
	}
	public String getSalary_bank() {
		return salary_bank;
	}
	public void setSalary_bank(String salary_bank) {
		this.salary_bank = salary_bank;
	}
	public ArrayList<Employee> getBranchlist() {
		return branchlist;
	}
	public void setBranchlist(ArrayList<Employee> branchlist) {
		this.branchlist = branchlist;
	}
	public String getGross_pay() {
		return gross_pay;
	}
	public void setGross_pay(String gross_pay) {
		this.gross_pay = gross_pay;
	}
	public String getIncre_date() {
		return incre_date;
	}
	public void setIncre_date(String incre_date) {
		this.incre_date = incre_date;
	}
	public ArrayList<Salary> getIncrementlist() {
		return incrementlist;
	}
	public void setIncrementlist(ArrayList<Salary> incrementlist) {
		this.incrementlist = incrementlist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBasic() {
		return basic;
	}
	public void setBasic(String basic) {
		this.basic = basic;
	}
	public String getAllowances() {
		return allowances;
	}
	public void setAllowances(String allowances) {
		this.allowances = allowances;
	}
	public String getDa_on_ta() {
		return da_on_ta;
	}
	public void setDa_on_ta(String da_on_ta) {
		this.da_on_ta = da_on_ta;
	}
	public String getSpecial_pay() {
		return special_pay;
	}
	public void setSpecial_pay(String special_pay) {
		this.special_pay = special_pay;
	}
	public String getPersonal_pay() {
		return personal_pay;
	}
	public void setPersonal_pay(String personal_pay) {
		this.personal_pay = personal_pay;
	}
	public String getTransport_allowance() {
		return transport_allowance;
	}
	public void setTransport_allowance(String transport_allowance) {
		this.transport_allowance = transport_allowance;
	}
	public String getHra() {
		return hra;
	}
	public void setHra(String hra) {
		this.hra = hra;
	}
	public String getDa() {
		return da;
	}
	public void setDa(String da) {
		this.da = da;
	}
	public String getNpa() {
		return npa;
	}
	public void setNpa(String npa) {
		this.npa = npa;
	}
	public String getEmp_pf() {
		return emp_pf;
	}
	public void setEmp_pf(String emp_pf) {
		this.emp_pf = emp_pf;
	}
	public String getEmp_esi() {
		return emp_esi;
	}
	public void setEmp_esi(String emp_esi) {
		this.emp_esi = emp_esi;
	}
	public String getComp_pf() {
		return comp_pf;
	}
	public void setComp_pf(String comp_pf) {
		this.comp_pf = comp_pf;
	}
	public String getComp_esi() {
		return comp_esi;
	}
	public void setComp_esi(String comp_esi) {
		this.comp_esi = comp_esi;
	}
	public String getTaxable() {
		return taxable;
	}
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}
	public String getOtherincome() {
		return otherincome;
	}
	public void setOtherincome(String otherincome) {
		this.otherincome = otherincome;
	}
	public String getNetpay() {
		return netpay;
	}
	public void setNetpay(String netpay) {
		this.netpay = netpay;
	}
	public String getTotaldays() {
		return totaldays;
	}
	public void setTotaldays(String totaldays) {
		this.totaldays = totaldays;
	}
	public String getWorkingdays() {
		return workingdays;
	}
	public void setWorkingdays(String workingdays) {
		this.workingdays = workingdays;
	}
	public String getTotal_leaves() {
		return total_leaves;
	}
	public void setTotal_leaves(String total_leaves) {
		this.total_leaves = total_leaves;
	}
	public String getLoan() {
		return loan;
	}
	public void setLoan(String loan) {
		this.loan = loan;
	}
	public String getNo_sundays() {
		return no_sundays;
	}
	public void setNo_sundays(String no_sundays) {
		this.no_sundays = no_sundays;
	}
	public String getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}
	public String getMonthyear() {
		return monthyear;
	}
	public void setMonthyear(String monthyear) {
		this.monthyear = monthyear;
	}
	public String getMedical_allowance() {
		return medical_allowance;
	}
	public void setMedical_allowance(String medical_allowance) {
		this.medical_allowance = medical_allowance;
	}
	public String getDeductions() {
		return deductions;
	}
	public void setDeductions(String deductions) {
		this.deductions = deductions;
	}
	public String getEncashable() {
		return encashable;
	}
	public void setEncashable(String encashable) {
		this.encashable = encashable;
	}
	public String getFixed_deduction() {
		return fixed_deduction;
	}
	public void setFixed_deduction(String fixed_deduction) {
		this.fixed_deduction = fixed_deduction;
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}
	public String getCanteen() {
		return canteen;
	}
	public void setCanteen(String canteen) {
		this.canteen = canteen;
	}
	public String getTds() {
		return tds;
	}
	public void setTds(String tds) {
		this.tds = tds;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getEmpcode() {
		return empcode;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public String getAdvance() {
		return advance;
	}
	public void setAdvance(String advance) {
		this.advance = advance;
	}
	public String getActualworkingday() {
		return actualworkingday;
	}
	public void setActualworkingday(String actualworkingday) {
		this.actualworkingday = actualworkingday;
	}
	public String getTotalworkinghour() {
		return totalworkinghour;
	}
	public void setTotalworkinghour(String totalworkinghour) {
		this.totalworkinghour = totalworkinghour;
	}
	public String getActualnetpay() {
		return actualnetpay;
	}
	public void setActualnetpay(String actualnetpay) {
		this.actualnetpay = actualnetpay;
	}
	
	
	

}
