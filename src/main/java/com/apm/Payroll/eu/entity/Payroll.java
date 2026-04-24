package com.apm.Payroll.eu.entity;

public class Payroll {

	private int id;
	private String name;
	private String address;
	private String branch;
	private String city;
	private String state;
	private String country;
	private String phone1;
	private String phone2;
	private String email;
	private String pincode;
	private String landline;
	private String website;
	private String fromtime;
	private String totime;
	private String otreason;
	private String requestremark;
	private String approveby1;
	private String approveremark1;
	private String approveby2;
	private String approveremark2;
	private String approvedate1,approvedate2;
	public String getApprovedate1() {
		return approvedate1;
	}

	public void setApprovedate1(String approvedate1) {
		this.approvedate1 = approvedate1;
	}

	public String getApprovedate2() {
		return approvedate2;
	}

	public void setApprovedate2(String approvedate2) {
		this.approvedate2 = approvedate2;
	}

	public String getRequestremark() {
		return requestremark;
	}

	public void setRequestremark(String requestremark) {
		this.requestremark = requestremark;
	}

	public String getApproveby1() {
		return approveby1;
	}

	public void setApproveby1(String approveby1) {
		this.approveby1 = approveby1;
	}

	public String getApproveremark1() {
		return approveremark1;
	}

	public void setApproveremark1(String approveremark1) {
		this.approveremark1 = approveremark1;
	}

	public String getApproveby2() {
		return approveby2;
	}

	public void setApproveby2(String approveby2) {
		this.approveby2 = approveby2;
	}

	public String getApproveremark2() {
		return approveremark2;
	}

	public void setApproveremark2(String approveremark2) {
		this.approveremark2 = approveremark2;
	}

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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	private String empleave_type;
	public String getEmpleave_type() {
		return empleave_type;
	}

	public void setEmpleave_type(String empleave_type) {
		this.empleave_type = empleave_type;
	}

	private String firstName="";
	private String lastName="";
	private String jobtitle;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
private String mobile;
	public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}
private String requestaddress;
	public String getRequestaddress() {
	return requestaddress;
}

public void setRequestaddress(String requestaddress) {
	this.requestaddress = requestaddress;
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



	private String dept_name;
	private String overtime_status;
	private String rate;
    private String sheduled;
    private String notes;
    private String lastmaintenance;
	private String equipment_id;
	private String due_date;
	private String remainder_on;
	private String frequency;
	private String brand;	
	private String comment;	
	private String time;	
	private String empleave_id;
	public String getEmpleave_id() {
		return empleave_id;
	}

	public void setEmpleave_id(String empleave_id) {
		this.empleave_id = empleave_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private String bankaddress;
	public String getBankaddress() {
		return bankaddress;
	}

	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}

	// company details
	private String company_name;
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

	// deduction

	private String deduction;
	private String deduction_type;
	private String emp_contribution;
	private String comp_contribution;

	// tax

	String fromamount, toamount, gender, tax;

	// Bank
	String bank;
	String bank_account;
	String ifsccode;
	String emp_id;

	// loan
	String amount;
	String installments;

	// leave

	String short_name;
	String leave_type;
	String no_days;
	String encashable;
	String carryover;
	private String leaveno;
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

	private String requestdate;
	private String requestuser;
	private String tbodyleaveid;
	private String userdatetime;
	private String receivedbyid;
	private String parentid23;
	private String hospitaltitlediv3;
	private String noteTextBox;
	private String days;
public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

private String leave_reason;
private String requesteddate;
private String approveddate;
private String approvedby;
private String fromdate;
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

private String todate;
	
	
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

	String date;
	String event;
	String month;
	String year;
	String status;

	
	String userid;
	String password;
	

	
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

	public String getSheduled() {
		return sheduled;
	}

	public void setSheduled(String sheduled) {
		this.sheduled = sheduled;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getLastmaintenance() {
		return lastmaintenance;
	}

	public void setLastmaintenance(String lastmaintenance) {
		this.lastmaintenance = lastmaintenance;
	}

	public String getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getRemainder_on() {
		return remainder_on;
	}

	public void setRemainder_on(String remainder_on) {
		this.remainder_on = remainder_on;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOtreason() {
		return otreason;
	}

	public void setOtreason(String otreason) {
		this.otreason = otreason;
	}

}
