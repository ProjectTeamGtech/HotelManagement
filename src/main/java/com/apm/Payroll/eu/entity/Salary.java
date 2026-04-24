package com.apm.Payroll.eu.entity;

import java.util.ArrayList;

public class Salary {
	
	int id;
	String emp_id;
	String branch_id;
	String com_id;
	String dept_id;
	private String days;
	private String salaryTotal;
	private String salaryId;
	private String department;
	private String calnetpay;
	private String carryforward;
	private String finalstatus;
	private String selectedstatus;
	private String ot;
	private String datejoin;
	private String empcode;
	private String email;
	private String pfno;
	private String esicno;
	private String advance;
	public String getPfno() {
		return pfno;
	}
	public void setPfno(String pfno) {
		this.pfno = pfno;
	}
	public String getEsicno() {
		return esicno;
	}
	public void setEsicno(String esicno) {
		this.esicno = esicno;
	}
	private String totallownces;
	private String totdeduction;
	private String totbasic;
	private String tothra;
	private String totda;
	private String totmed;
	private String totconvey;
	private String totperdev;
	private String totot;
	private String totpf;
	private String totesi;
	private String totleave;
	private String totprof;
	private String totgrosspay;
	private String totnetpay;
	private String tottds;
	private String totloan;
	private String totadvance;
	public String getTotloan() {
		return totloan;
	}
	public void setTotloan(String totloan) {
		this.totloan = totloan;
	}
	public String getTotadvance() {
		return totadvance;
	}
	public void setTotadvance(String totadvance) {
		this.totadvance = totadvance;
	}
	private double perdayamount;
	public void setCarryforward(String carryforward) {
		this.carryforward = carryforward;
	}
	public String getFinalstatus() {
		return finalstatus;
	}
	public void setFinalstatus(String finalstatus) {
		this.finalstatus = finalstatus;
	}
	public String getOt() {
		return ot;
	}
	public void setOt(String ot) {
		this.ot = ot;
	}
	public String getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getSalaryTotal() {
		return salaryTotal;
	}
	public void setSalaryTotal(String salaryTotal) {
		this.salaryTotal = salaryTotal;
	}
	private String fixedsalary;
	public String getFixedsalary() {
		return fixedsalary;
	}
	public void setFixedsalary(String fixedsalary) {
		this.fixedsalary = fixedsalary;
	}
	String emp_name;
	String designation;
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getTotallownces() {
		return totallownces;
	}
	public void setTotallownces(String totallownces) {
		this.totallownces = totallownces;
	}
	public String getTotdeduction() {
		return totdeduction;
	}
	public void setTotdeduction(String totdeduction) {
		this.totdeduction = totdeduction;
	}
	public String getTotbasic() {
		return totbasic;
	}
	public void setTotbasic(String totbasic) {
		this.totbasic = totbasic;
	}
	public String getTothra() {
		return tothra;
	}
	public void setTothra(String tothra) {
		this.tothra = tothra;
	}
	public String getTotda() {
		return totda;
	}
	public void setTotda(String totda) {
		this.totda = totda;
	}
	public String getTotmed() {
		return totmed;
	}
	public void setTotmed(String totmed) {
		this.totmed = totmed;
	}
	public String getTotconvey() {
		return totconvey;
	}
	public void setTotconvey(String totconvey) {
		this.totconvey = totconvey;
	}
	public String getTotperdev() {
		return totperdev;
	}
	public void setTotperdev(String totperdev) {
		this.totperdev = totperdev;
	}
	public String getTotot() {
		return totot;
	}
	public void setTotot(String totot) {
		this.totot = totot;
	}
	public String getTotpf() {
		return totpf;
	}
	public void setTotpf(String totpf) {
		this.totpf = totpf;
	}
	public String getTotesi() {
		return totesi;
	}
	public void setTotesi(String totesi) {
		this.totesi = totesi;
	}
	public String getTotleave() {
		return totleave;
	}
	public void setTotleave(String totleave) {
		this.totleave = totleave;
	}
	public String getTotprof() {
		return totprof;
	}
	public void setTotprof(String totprof) {
		this.totprof = totprof;
	}
	public String getTotgrosspay() {
		return totgrosspay;
	}
	public void setTotgrosspay(String totgrosspay) {
		this.totgrosspay = totgrosspay;
	}
	public String getTotnetpay() {
		return totnetpay;
	}
	public void setTotnetpay(String totnetpay) {
		this.totnetpay = totnetpay;
	}
	public String getCarryforward() {
		return carryforward;
	}
	String date;
	double total_deduction;
	public double getTotal_deduction() {
		return total_deduction;
	}
	public void setTotal_deduction(double total_deduction) {
		this.total_deduction = total_deduction;
	}
	// for Deduction
	String washing;
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
	String vehiclepass;
	
	
	
	public String getPerdevallow() {
		return perdevallow;
	}
	public void setPerdevallow(String perdevallow) {
		this.perdevallow = perdevallow;
	}
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
	String bank_account;
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	String gross_pay;
	String incre_date;
	
	String totaldays;
	String workingdays;
	String total_leaves;
	
	String holidays;
	
	
	
	String loan;
	
	String workeddays;
	
	
	
	String deductions;
	ArrayList<Salary>salarydetaillist;
	
	
	public ArrayList<Salary> getSalarydetaillist() {
		return salarydetaillist;
	}
	public void setSalarydetaillist(ArrayList<Salary> salarydetaillist) {
		this.salarydetaillist = salarydetaillist;
	}
	String medical_allowance;
	

	String encashable;
	
	String fixed_deduction;
	String leave;
	String canteen;
	String salary_bank;
	String tds;
	String other_income;
	
	String other_deduction;
	String prefessional_tax;
	
	
	
	
	
	// company profile
	String company_name;
	String company_address;
	String comp_phone;
	String comp_email;
	String comp_fax;
	String comp_website;
	String emp_role;
	
	
	String month;
	String year;
	String status;
	
	
	//
	String paidleaves;
	String unpaidleaves;
	
	
	
	
	
	
	
	
	
	
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getTds() {
		return tds;
	}
	public void setTds(String tds) {
		this.tds = tds;
	}
	public String getSalary_bank() {
		return salary_bank;
	}
	public void setSalary_bank(String salary_bank) {
		this.salary_bank = salary_bank;
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
	public String getEncashable() {
		return encashable;
	}
	public void setEncashable(String encashable) {
		this.encashable = encashable;
	}
	public String getDeductions() {
		return deductions;
	}
	public void setDeductions(String deductions) {
		this.deductions = deductions;
	}
	public String getMedical_allowance() {
		return medical_allowance;
	}
	public void setMedical_allowance(String medical_allowance) {
		this.medical_allowance = medical_allowance;
	}
	public String getLoan() {
		return loan;
	}
	public void setLoan(String loan) {
		this.loan = loan;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCalnetpay() {
		return calnetpay;
	}
	public void setCalnetpay(String calnetpay) {
		this.calnetpay = calnetpay;
	}
	
	public boolean isIselect() {
		return iselect;
	}
	public void setIselect(boolean iselect) {
		this.iselect = iselect;
	}
	public String getSelectedstatus() {
		return selectedstatus;
	}
	public void setSelectedstatus(String selectedstatus) {
		this.selectedstatus = selectedstatus;
	}
	public boolean isChkobj() {
		return chkobj;
	}
	public void setChkobj(boolean chkobj) {
		this.chkobj = chkobj;
	}
	public String getDatejoin() {
		return datejoin;
	}
	public void setDatejoin(String datejoin) {
		this.datejoin = datejoin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmpcode() {
		return empcode;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public double getPerdayamount() {
		return perdayamount;
	}
	public void setPerdayamount(double perdayamount) {
		this.perdayamount = perdayamount;
	}
	public String getTottds() {
		return tottds;
	}
	public void setTottds(String tottds) {
		this.tottds = tottds;
	}
	public String getAdvance() {
		return advance;
	}
	public void setAdvance(String advance) {
		this.advance = advance;
	}
	private boolean iselect;
	private boolean chkobj;
	

}
