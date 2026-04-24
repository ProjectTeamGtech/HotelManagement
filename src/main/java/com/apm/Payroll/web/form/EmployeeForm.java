package com.apm.Payroll.web.form;

import java.io.File;
import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;

public class EmployeeForm {

	public String getGrossal() {
		return grossal;
	}

	public void setGrossal(String grossal) {
		this.grossal = grossal;
	}

	public ArrayList<Employee> getTemplatelist() {
		return templatelist;
	}

	public void setTemplatelist(ArrayList<Employee> templatelist) {
		this.templatelist = templatelist;
	}
	int id;
	String initial;
	private String otallow;
	private ArrayList<Employee> documentlist;
	public ArrayList<Employee> getDocumentlist() {
		return documentlist;
	}

	public void setDocumentlist(ArrayList<Employee> documentlist) {
		this.documentlist = documentlist;
	}

	public String getInitial() {
		return initial;
	}

	public String getFixedsal() {
		return fixedsal;
	}

	public void setFixedsal(String fixedsal) {
		this.fixedsal = fixedsal;
	}

	public String getBasicsalper() {
		return basicsalper;
	}

	public void setBasicsalper(String basicsalper) {
		this.basicsalper = basicsalper;
	}
	private String subuploadfilesContentType;
	private String subuploadfilesFileName;
	private File subuploadfiles;
	private String filesubmission_category;
	private String filesubremark;
	private String workinghourday;
	private String workingdayweek;
	private String totworkinghourweek;
	
	public String getWorkinghourday() {
		return workinghourday;
	}

	public void setWorkinghourday(String workinghourday) {
		this.workinghourday = workinghourday;
	}

	public String getWorkingdayweek() {
		return workingdayweek;
	}

	public void setWorkingdayweek(String workingdayweek) {
		this.workingdayweek = workingdayweek;
	}

	public String getTotworkinghourweek() {
		return totworkinghourweek;
	}

	public void setTotworkinghourweek(String totworkinghourweek) {
		this.totworkinghourweek = totworkinghourweek;
	}
	private ArrayList<Employee> filesubmmissionlist;
	public String getSubuploadfilesContentType() {
		return subuploadfilesContentType;
	}

	public void setSubuploadfilesContentType(String subuploadfilesContentType) {
		this.subuploadfilesContentType = subuploadfilesContentType;
	}

	public String getSubuploadfilesFileName() {
		return subuploadfilesFileName;
	}

	public void setSubuploadfilesFileName(String subuploadfilesFileName) {
		this.subuploadfilesFileName = subuploadfilesFileName;
	}

	public File getSubuploadfiles() {
		return subuploadfiles;
	}

	public void setSubuploadfiles(File subuploadfiles) {
		this.subuploadfiles = subuploadfiles;
	}

	public String getFilesubmission_category() {
		return filesubmission_category;
	}

	public void setFilesubmission_category(String filesubmission_category) {
		this.filesubmission_category = filesubmission_category;
	}

	public String getFilesubremark() {
		return filesubremark;
	}

	public void setFilesubremark(String filesubremark) {
		this.filesubremark = filesubremark;
	}
	private String remain_leave;
	private String agency;
	private String casualleave;
	private String pagerecords;
	private String userid;
	private int totalRecords;
	private String empnamesearch;
	private String daper;
	private String hraper;
	private String tdsper;
	private String employid;
	private String fixedsal;
	private String basicsalper;
	private String uanno;
	private String pfno;
	private String esicno;
	public String getUanno() {
		return uanno;
	}

	public void setUanno(String uanno) {
		this.uanno = uanno;
	}

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

	public String getDaper() {
		return daper;
	}

	public void setDaper(String daper) {
		this.daper = daper;
	}

	public String getHraper() {
		return hraper;
	}

	public void setHraper(String hraper) {
		this.hraper = hraper;
	}

	public String getTdsper() {
		return tdsper;
	}

	public void setTdsper(String tdsper) {
		this.tdsper = tdsper;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	ArrayList<String> initialList;

	public ArrayList<String> getInitialList() {
		return initialList;
	}

	public void setInitialList(ArrayList<String> initialList) {
		this.initialList = initialList;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	String searchText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	String empcode;
	String emp_id;
	String name;
	String company;
	String branch;
	String branch_id;
	String department;
	String dept_id;
	String designation;
	String date_join;
	String qualification;
	String password;
	String status;
	String comp_id;
	String dob;

	String currentaddress;

	String permanentaddress;

	String contact;

	String panno;

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCurrentaddress() {
		return currentaddress;
	}

	public void setCurrentaddress(String currentaddress) {
		this.currentaddress = currentaddress;
	}

	public String getPermanentaddress() {
		return permanentaddress;
	}

	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPanno() {
		return panno;
	}

	public void setPanno(String panno) {
		this.panno = panno;
	}

	private ArrayList<Employee> employeelist;
	private ArrayList<Employee> branchlist;
	private ArrayList<Employee> departmentlist;
	private ArrayList<Employee> companylist;
	private ArrayList<Payroll> designationlist;

	public String getComp_id() {
		return comp_id;
	}

	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}

	public ArrayList<Employee> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(ArrayList<Employee> branchlist) {
		this.branchlist = branchlist;
	}

	public ArrayList<Employee> getDepartmentlist() {
		return departmentlist;
	}

	public void setDepartmentlist(ArrayList<Employee> departmentlist) {
		this.departmentlist = departmentlist;
	}

	public ArrayList<Employee> getCompanylist() {
		return companylist;
	}

	public void setCompanylist(ArrayList<Employee> companylist) {
		this.companylist = companylist;
	}

	public ArrayList<Employee> getEmployeelist() {
		return employeelist;
	}

	public void setEmployeelist(ArrayList<Employee> employeelist) {
		this.employeelist = employeelist;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDate_join() {
		return date_join;
	}

	public void setDate_join(String date_join) {
		this.date_join = date_join;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAdhno() {
		return adhno;
	}

	public void setAdhno(String adhno) {
		this.adhno = adhno;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private String adhno;
	private String gender;
	private String postcode;
	private String maritalsts;
	private String county;
	private String town;
	private String mobNo;
	private String email;
	private String workhour;

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMaritalsts() {
		return maritalsts;
	}

	public void setMaritalsts(String maritalsts) {
		this.maritalsts = maritalsts;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String firstname;
	private String lastname;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public ArrayList<Payroll> getDesignationlist() {
		return designationlist;
	}

	public void setDesignationlist(ArrayList<Payroll> designationlist) {
		this.designationlist = designationlist;
	}

	public String getBasicsal() {
		return basicsal;
	}

	public void setBasicsal(String basicsal) {
		this.basicsal = basicsal;
	}

	private String basicsal;

	private String vehiclepass;

	private String com_id;
	private String emp_name;
	private String date;
	private String basic;
	private String allowances;
	private String da_on_ta;
	private String special_pay;
	private String personal_pay;
	private String transport_allowance;
	private String hra;
	private String da;
	private String npa;
	private String emp_pf;
	private String emp_esi;
	private String comp_pf;
	private String comp_esi;
	private String taxable;
	private String otherincome;
	private String netpay;
	private String medical_allownces;
	private String perdevallow;

	public String getLeaves() {
		return leaves;
	}

	public void setLeaves(String leaves) {
		this.leaves = leaves;
	}

	public String getProftax() {
		return proftax;
	}

	public void setProftax(String proftax) {
		this.proftax = proftax;
	}

	public String getLabourwel() {
		return labourwel;
	}

	public void setLabourwel(String labourwel) {
		this.labourwel = labourwel;
	}

	private String leaves;
	private String proftax;
	private String labourwel;
	private String tds;
	private String netsal;
	private String grossal;
	private String nationality;

	public String getMedical_allownces() {
		return medical_allownces;
	}

	public void setMedical_allownces(String medical_allownces) {
		this.medical_allownces = medical_allownces;
	}

	public String getPerdevallow() {
		return perdevallow;
	}

	public void setPerdevallow(String perdevallow) {
		this.perdevallow = perdevallow;
	}

	private String conveyance;

	private String incre_date;

	public String getVehiclepass() {
		return vehiclepass;
	}

	public void setVehiclepass(String vehiclepass) {
		this.vehiclepass = vehiclepass;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
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

	public String getConveyance() {
		return conveyance;
	}

	public void setConveyance(String conveyance) {
		this.conveyance = conveyance;
	}

	public String getIncre_date() {
		return incre_date;
	}

	public void setIncre_date(String incre_date) {
		this.incre_date = incre_date;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getNetsal() {
		return netsal;
	}

	public void setNetsal(String netsal) {
		this.netsal = netsal;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmpnamesearch() {
		return empnamesearch;
	}

	public void setEmpnamesearch(String empnamesearch) {
		this.empnamesearch = empnamesearch;
	}

	private String account_no;
	private String bank_name;
	private String ifsc_code;
	private String bank_branch;

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getBank_branch() {
		return bank_branch;
	}

	public void setBank_branch(String bank_branch) {
		this.bank_branch = bank_branch;
	}

	public String getWorkhour() {
		return workhour;
	}

	public void setWorkhour(String workhour) {
		this.workhour = workhour;
	}

	public String getEmployid() {
		return employid;
	}

	public void setEmployid(String employid) {
		this.employid = employid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCasualleave() {
		return casualleave;
	}

	public void setCasualleave(String casualleave) {
		this.casualleave = casualleave;
	}

	public String getRemain_leave() {
		return remain_leave;
	}

	public void setRemain_leave(String remain_leave) {
		this.remain_leave = remain_leave;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public ArrayList<Employee> getFilesubmmissionlist() {
		return filesubmmissionlist;
	}

	public void setFilesubmmissionlist(ArrayList<Employee> filesubmmissionlist) {
		this.filesubmmissionlist = filesubmmissionlist;
	}

	public String getOtallow() {
		return otallow;
	}

	public void setOtallow(String otallow) {
		this.otallow = otallow;
	}
	private ArrayList<Employee> templatelist;
}
