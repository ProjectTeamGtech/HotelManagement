package com.apm.Payroll.web.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.apm.Master.eu.entity.Master;
import com.apm.Payroll.eu.entity.Attendance;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.Registration.eu.entity.Clinic;

public class PayrollForm {
	private String selectedmonth;
	private String empname;
	private String leavetype;
	private String leavests;
	private String totalleave;
	private String advance;
	private String loan;
	private String hours;
	private String otreason;
	private String fromtime;
	private String totime;
	private String time;
	private String otstatus;
	 private String workinghourday;
	private ArrayList<Attendance> mobileattendancemaster;
	private ArrayList<Payroll> documentlist;
	public String getFromtime() {
		return fromtime;
	}

	public void setFromtime(String fromtime) {
		this.fromtime = fromtime;
	}

	public String getTotime() {
		return totime;
	}

	public void setTotime(String totime) {
		this.totime = totime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAdvance() {
		return advance;
	}

	public void setAdvance(String advance) {
		this.advance = advance;
	}

	public String getLoan() {
		return loan;
	}

	public void setLoan(String loan) {
		this.loan = loan;
	}

	private String remainingleave;
	private  ArrayList<Attendance> attendancemaster;
	private ArrayList<Payroll> qualificationlist;
	public String getTotalleave() {
		return totalleave;
	}

	public void setTotalleave(String totalleave) {
		this.totalleave = totalleave;
	}

	public String getRemainingleave() {
		return remainingleave;
	}

	public void setRemainingleave(String remainingleave) {
		this.remainingleave = remainingleave;
	}

	public String getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public String getLeavests() {
		return leavests;
	}

	public void setLeavests(String leavests) {
		this.leavests = leavests;
	}

	private int id;
	private String name;
	private String address;
	private String days;
	private String message;
	private String selectedstatus;
	private String leave;
	private String pincode;
	private String mobile;
	private String empnamesearch;
	private String department;
	private ArrayList<Employee> departmentlist;
	private double totallownces;
	private double totdeduction;
	private double totbasic;
	private double tothra;
	private double totda;
	private double totmed;
	private double totconvey;
	private double totperdev;
	private double totot;
	private double totpf;
	private double totesi;
	private double totleave;
	private double totprof;
	private double tottds;
	private double totadvance;
	private double totloan;
	public double getTotadvance() {
		return totadvance;
	}

	public void setTotadvance(double totadvance) {
		this.totadvance = totadvance;
	}

	public double getTotloan() {
		return totloan;
	}

	public void setTotloan(double totloan) {
		this.totloan = totloan;
	}

	private double totgrosspay;
	private double totnetpay;
	private String weekleave;
	private String hideweekleave;
	public double getTotgrosspay() {
		return totgrosspay;
	}

	public void setTotgrosspay(double totgrosspay) {
		this.totgrosspay = totgrosspay;
	}

	public double getTotnetpay() {
		return totnetpay;
	}

	public void setTotnetpay(double totnetpay) {
		this.totnetpay = totnetpay;
	}

	public double getTottds() {
		return tottds;
	}

	public void setTottds(double tottds) {
		this.tottds = tottds;
	}

	public double getTotallownces() {
		return totallownces;
	}

	public void setTotallownces(double totallownces) {
		this.totallownces = totallownces;
	}

	public double getTotdeduction() {
		return totdeduction;
	}

	public void setTotdeduction(double totdeduction) {
		this.totdeduction = totdeduction;
	}

	public double getTotbasic() {
		return totbasic;
	}

	public void setTotbasic(double totbasic) {
		this.totbasic = totbasic;
	}

	public double getTothra() {
		return tothra;
	}

	public void setTothra(double tothra) {
		this.tothra = tothra;
	}

	public double getTotda() {
		return totda;
	}

	public void setTotda(double totda) {
		this.totda = totda;
	}

	public double getTotmed() {
		return totmed;
	}

	public void setTotmed(double totmed) {
		this.totmed = totmed;
	}

	public double getTotconvey() {
		return totconvey;
	}

	public void setTotconvey(double totconvey) {
		this.totconvey = totconvey;
	}

	public double getTotperdev() {
		return totperdev;
	}

	public void setTotperdev(double totperdev) {
		this.totperdev = totperdev;
	}

	public double getTotot() {
		return totot;
	}

	public void setTotot(double totot) {
		this.totot = totot;
	}

	public double getTotpf() {
		return totpf;
	}

	public void setTotpf(double totpf) {
		this.totpf = totpf;
	}

	public double getTotesi() {
		return totesi;
	}

	public void setTotesi(double totesi) {
		this.totesi = totesi;
	}

	public double getTotleave() {
		return totleave;
	}

	public void setTotleave(double totleave) {
		this.totleave = totleave;
	}

	public double getTotprof() {
		return totprof;
	}

	public void setTotprof(double totprof) {
		this.totprof = totprof;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	ArrayList<Employee>attendencelist;
	public ArrayList<Employee> getAttendencelist() {
		return attendencelist;
	}

	public void setAttendencelist(ArrayList<Employee> attendencelist) {
		this.attendencelist = attendencelist;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	ArrayList<Salary>salarydetaillist;
	public ArrayList<Salary> getSalarydetaillist() {
		return salarydetaillist;
	}

	public void setSalarydetaillist(ArrayList<Salary> salarydetaillist) {
		this.salarydetaillist = salarydetaillist;
	}

	private String requestname;
	private String requestcontact;
	
	private String requestdepartment;
	
	public String getRequestname() {
		return requestname;
	}

	public void setRequestname(String requestname) {
		this.requestname = requestname;
	}

	public String getRequestcontact() {
		return requestcontact;
	}

	public void setRequestcontact(String requestcontact) {
		this.requestcontact = requestcontact;
	}

	public String getRequestdepartment() {
		return requestdepartment;
	}

	public void setRequestdepartment(String requestdepartment) {
		this.requestdepartment = requestdepartment;
	}

	private String branch;
	private String city;
	private String state;
	private String country;
	private String phone1;
	private String phone2;
	private String email;
	private int totalRecords;
	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	private String pagerecords;


	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	private String empleave_type;
	public String getEmpleave_type() {
		return empleave_type;
	}

	public void setEmpleave_type(String empleave_type) {
		this.empleave_type = empleave_type;
	}

	// company details
	private String company_name;
	private String searchText;
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	private ArrayList<Master> statelist;
	private ArrayList<Master> citylist;
	public ArrayList<Master> getStatelist() {
		return statelist;
	}

	public void setStatelist(ArrayList<Master> statelist) {
		this.statelist = statelist;
	}

	public ArrayList<Master> getCitylist() {
		return citylist;
	}

	public void setCitylist(ArrayList<Master> citylist) {
		this.citylist = citylist;
	}

	private String date_format;
	private String itmonth;
	private String tinno;
	private String pan_no;
	private String esi_no;
	private String pf_no;
	private String hourly_type;
	private String fixed_hour;
	private String no_hours;
	private String ot_status;
	private String permissions;
	private String permi_penalty;
	private String no_permission;
	ArrayList<Payroll>printleavelist;
	private String clinicName;
	private String clinicOwner;
	private String clinicemail;
	private String clinicaddress;
	private String clinicity;
	private String websiteUrl;
	private String landLine;
	private ArrayList<Clinic> locationAdressList;
	private String clinicLogo;
	private String owner_qualification;
	// deduction

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
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

	public String getClinicemail() {
		return clinicemail;
	}

	public void setClinicemail(String clinicemail) {
		this.clinicemail = clinicemail;
	}

	public String getClinicaddress() {
		return clinicaddress;
	}

	public void setClinicaddress(String clinicaddress) {
		this.clinicaddress = clinicaddress;
	}

	public String getClinicity() {
		return clinicity;
	}

	public void setClinicity(String clinicity) {
		this.clinicity = clinicity;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}

	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}

	public ArrayList<Payroll> getPrintleavelist() {
		return printleavelist;
	}

	public void setPrintleavelist(ArrayList<Payroll> printleavelist) {
		this.printleavelist = printleavelist;
	}

	private String deduction;
	private String deduction_type;
	private String emp_contribution;
	private String comp_contribution;

	private ArrayList<Payroll> deductionList;

	private String dept_name;
	private String overtime_status;
	private String rate;

	// tax

	String fromamount, toamount, gender, tax;
	ArrayList<Payroll> taxlist;

	// Bank
	String bank;
	String bank_account;
	String ifsccode;
	String emp_id;
	private String bankaddress;
	
	public String getBankaddress() {
		return bankaddress;
	}

	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}

	String userid;
	String password;
	
	
	
	
	ArrayList<Payroll> banklist;

	ArrayList<Payroll> departmentList;
	ArrayList<Employee> deptlist;
	public ArrayList<Employee> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(ArrayList<Employee> deptlist) {
		this.deptlist = deptlist;
	}

	ArrayList<Payroll> designationList;

	ArrayList<Payroll> branchesList;

	// loan setting
	// loan
	String amount;
	String installments;

	ArrayList<Payroll> loanlist;
	HashSet<String> emp_list;

	// leave
	private String leaveno;
	private String requestdate;
	private String requestuser;
	private String tbodyleaveid;
	private String userdatetime;
	private String receivedbyid;
	private String parentid23;
	private String hospitaltitlediv3;
	private String noteTextBox;
	
	public String getLeaveno() {
		return leaveno;
	}

	public void setLeaveno(String leaveno) {
		this.leaveno = leaveno;
	}

	public String getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}

	public String getRequestuser() {
		return requestuser;
	}

	public void setRequestuser(String requestuser) {
		this.requestuser = requestuser;
	}

	public String getTbodyleaveid() {
		return tbodyleaveid;
	}

	public void setTbodyleaveid(String tbodyleaveid) {
		this.tbodyleaveid = tbodyleaveid;
	}

	public String getUserdatetime() {
		return userdatetime;
	}

	public void setUserdatetime(String userdatetime) {
		this.userdatetime = userdatetime;
	}

	public String getReceivedbyid() {
		return receivedbyid;
	}

	public void setReceivedbyid(String receivedbyid) {
		this.receivedbyid = receivedbyid;
	}

	public String getParentid23() {
		return parentid23;
	}

	public void setParentid23(String parentid23) {
		this.parentid23 = parentid23;
	}

	public String getHospitaltitlediv3() {
		return hospitaltitlediv3;
	}

	public void setHospitaltitlediv3(String hospitaltitlediv3) {
		this.hospitaltitlediv3 = hospitaltitlediv3;
	}

	public String getNoteTextBox() {
		return noteTextBox;
	}

	public void setNoteTextBox(String noteTextBox) {
		this.noteTextBox = noteTextBox;
	}

	String short_name;
	String leave_type;
	String no_days;
	String encashable;
	String carryover;
	private String fromdate;
	private String todate;
	private String leave_reason;
	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	private String requesteddate;
	private String approveddate;
	private String approvedby;
	public String getLeave_reason() {
		return leave_reason;
	}

	public void setLeave_reason(String leave_reason) {
		this.leave_reason = leave_reason;
	}

	public String getRequesteddate() {
		return requesteddate;
	}

	public void setRequesteddate(String requesteddate) {
		this.requesteddate = requesteddate;
	}

	public String getApproveddate() {
		return approveddate;
	}

	public void setApproveddate(String approveddate) {
		this.approveddate = approveddate;
	}

	public String getApprovedby() {
		return approvedby;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}

	ArrayList<Employee> branchlist;

	ArrayList<Payroll> incrementlist;
	ArrayList<Employee> employeelist;

	ArrayList<Salary> salaryList;
	ArrayList<Payroll> holidayList;

	Collection<Salary> collectionsal;

	String date;
	String event;
	
	
	String month;
	String year;
	String status;
	private String selectedyear;
	
	
	

	ArrayList<Payroll> leaveList;

	public Collection<Salary> getCollectionsal() {
		return collectionsal;
	}

	public void setCollectionsal(Collection<Salary> collectionsal) {
		this.collectionsal = collectionsal;
	}

	public ArrayList<Payroll> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(ArrayList<Payroll> leaveList) {
		this.leaveList = leaveList;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getLeave_type() {
		return leave_type;
	}

	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}

	public String getNo_days() {
		return no_days;
	}

	public void setNo_days(String no_days) {
		this.no_days = no_days;
	}

	public String getEncashable() {
		return encashable;
	}

	public void setEncashable(String encashable) {
		this.encashable = encashable;
	}

	public String getCarryover() {
		return carryover;
	}

	public void setCarryover(String carryover) {
		this.carryover = carryover;
	}

	public HashSet<String> getEmp_list() {
		return emp_list;
	}

	public void setEmp_list(HashSet<String> emp_list) {
		this.emp_list = emp_list;
	}

	public ArrayList<Payroll> getLoanlist() {
		return loanlist;
	}

	public void setLoanlist(ArrayList<Payroll> loanlist) {
		this.loanlist = loanlist;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInstallments() {
		return installments;
	}

	public void setInstallments(String installments) {
		this.installments = installments;
	}

	public ArrayList<Payroll> getBranchesList() {
		return branchesList;
	}

	public void setBranchesList(ArrayList<Payroll> branchesList) {
		this.branchesList = branchesList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getOvertime_status() {
		return overtime_status;
	}

	public void setOvertime_status(String overtime_status) {
		this.overtime_status = overtime_status;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public ArrayList<Payroll> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(ArrayList<Payroll> departmentList) {
		this.departmentList = departmentList;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDate_format() {
		return date_format;
	}

	public void setDate_format(String date_format) {
		this.date_format = date_format;
	}

	public String getItmonth() {
		return itmonth;
	}

	public void setItmonth(String itmonth) {
		this.itmonth = itmonth;
	}

	public String getTinno() {
		return tinno;
	}

	public void setTinno(String tinno) {
		this.tinno = tinno;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getEsi_no() {
		return esi_no;
	}

	public void setEsi_no(String esi_no) {
		this.esi_no = esi_no;
	}

	public String getPf_no() {
		return pf_no;
	}

	public void setPf_no(String pf_no) {
		this.pf_no = pf_no;
	}

	public String getHourly_type() {
		return hourly_type;
	}

	public void setHourly_type(String hourly_type) {
		this.hourly_type = hourly_type;
	}

	public String getFixed_hour() {
		return fixed_hour;
	}

	public void setFixed_hour(String fixed_hour) {
		this.fixed_hour = fixed_hour;
	}

	public String getNo_hours() {
		return no_hours;
	}

	public void setNo_hours(String no_hours) {
		this.no_hours = no_hours;
	}

	public String getOt_status() {
		return ot_status;
	}

	public void setOt_status(String ot_status) {
		this.ot_status = ot_status;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getPermi_penalty() {
		return permi_penalty;
	}

	public void setPermi_penalty(String permi_penalty) {
		this.permi_penalty = permi_penalty;
	}

	public String getNo_permission() {
		return no_permission;
	}

	public void setNo_permission(String no_permission) {
		this.no_permission = no_permission;
	}

	public String getDeduction() {
		return deduction;
	}

	public void setDeduction(String deduction) {
		this.deduction = deduction;
	}

	public String getDeduction_type() {
		return deduction_type;
	}

	public void setDeduction_type(String deduction_type) {
		this.deduction_type = deduction_type;
	}

	public String getEmp_contribution() {
		return emp_contribution;
	}

	public void setEmp_contribution(String emp_contribution) {
		this.emp_contribution = emp_contribution;
	}

	public String getComp_contribution() {
		return comp_contribution;
	}

	public void setComp_contribution(String comp_contribution) {
		this.comp_contribution = comp_contribution;
	}

	public ArrayList<Payroll> getDeductionList() {
		return deductionList;
	}

	public void setDeductionList(ArrayList<Payroll> deductionList) {
		this.deductionList = deductionList;
	}

	public String getFromamount() {
		return fromamount;
	}

	public void setFromamount(String fromamount) {
		this.fromamount = fromamount;
	}

	public String getToamount() {
		return toamount;
	}

	public void setToamount(String toamount) {
		this.toamount = toamount;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public ArrayList<Payroll> getTaxlist() {
		return taxlist;
	}

	public void setTaxlist(ArrayList<Payroll> taxlist) {
		this.taxlist = taxlist;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public ArrayList<Payroll> getBanklist() {
		return banklist;
	}

	public void setBanklist(ArrayList<Payroll> banklist) {
		this.banklist = banklist;
	}

	public ArrayList<Employee> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(ArrayList<Employee> branchlist) {
		this.branchlist = branchlist;
	}

	public ArrayList<Payroll> getIncrementlist() {
		return incrementlist;
	}

	public void setIncrementlist(ArrayList<Payroll> incrementlist) {
		this.incrementlist = incrementlist;
	}

	public ArrayList<Employee> getEmployeelist() {
		return employeelist;
	}

	public void setEmployeelist(ArrayList<Employee> employeelist) {
		this.employeelist = employeelist;
	}

	public ArrayList<Salary> getSalaryList() {
		return salaryList;
	}

	public void setSalaryList(ArrayList<Salary> salaryList) {
		this.salaryList = salaryList;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public ArrayList<Payroll> getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(ArrayList<Payroll> holidayList) {
		this.holidayList = holidayList;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSelectedyear() {
		return selectedyear;
	}

	public void setSelectedyear(String selectedyear) {
		this.selectedyear = selectedyear;
	}

	public String getSelectedmonth() {
		return selectedmonth;
	}

	public void setSelectedmonth(String selectedmonth) {
		this.selectedmonth = selectedmonth;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLeave() {
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public String getSelectedstatus() {
		return selectedstatus;
	}

	public void setSelectedstatus(String selectedstatus) {
		this.selectedstatus = selectedstatus;
	}

	public ArrayList<Payroll> getDesignationList() {
		return designationList;
	}

	public void setDesignationList(ArrayList<Payroll> designationList) {
		this.designationList = designationList;
	}

	public ArrayList<Employee> getDepartmentlist() {
		return departmentlist;
	}

	public void setDepartmentlist(ArrayList<Employee> departmentlist) {
		this.departmentlist = departmentlist;
	}

	public String getEmpnamesearch() {
		return empnamesearch;
	}

	public void setEmpnamesearch(String empnamesearch) {
		this.empnamesearch = empnamesearch;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getWeekleave() {
		return weekleave;
	}

	public void setWeekleave(String weekleave) {
		this.weekleave = weekleave;
	}

	public String getHideweekleave() {
		return hideweekleave;
	}

	public void setHideweekleave(String hideweekleave) {
		this.hideweekleave = hideweekleave;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public ArrayList<Attendance> getAttendancemaster() {
		return attendancemaster;
	}

	public void setAttendancemaster(ArrayList<Attendance> attendancemaster) {
		this.attendancemaster = attendancemaster;
	}

	public ArrayList<Payroll> getQualificationlist() {
		return qualificationlist;
	}

	public void setQualificationlist(ArrayList<Payroll> qualificationlist) {
		this.qualificationlist = qualificationlist;
	}

	public String getOtreason() {
		return otreason;
	}

	public void setOtreason(String otreason) {
		this.otreason = otreason;
	}

	public String getOtstatus() {
		return otstatus;
	}

	public void setOtstatus(String otstatus) {
		this.otstatus = otstatus;
	}

	public ArrayList<Payroll> getDocumentlist() {
		return documentlist;
	}

	public void setDocumentlist(ArrayList<Payroll> documentlist) {
		this.documentlist = documentlist;
	}

	public ArrayList<Attendance> getMobileattendancemaster() {
		return mobileattendancemaster;
	}

	public void setMobileattendancemaster(ArrayList<Attendance> mobileattendancemaster) {
		this.mobileattendancemaster = mobileattendancemaster;
	}

	public String getWorkinghourday() {
		return workinghourday;
	}

	public void setWorkinghourday(String workinghourday) {
		this.workinghourday = workinghourday;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}


}
