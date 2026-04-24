package com.apm.Payroll.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Inventory.eu.bi.IndentDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCIndentDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.Payroll.eu.bi.BranchDAO;
import com.apm.Payroll.eu.bi.PayrollDepartmentDAO;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.bi.PayrollMasterDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCBranchDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollDepartment;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollEmployeeDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollMasterDAO;
import com.apm.Payroll.eu.entity.Employee;
import com.apm.Payroll.eu.entity.Payroll;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.Payroll.web.form.PayrollForm;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PayrollDashboardAction extends BaseAction implements ModelDriven<PayrollForm>,Preparable {

	PayrollForm payrollForm=new PayrollForm();
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	Pagination pagination=new Pagination(25,1);
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request); 
	

	public PayrollForm getModel() {

		return payrollForm;
	}

	
	
	public String execute() throws Exception {

		if(!verifyLogin(request)) {
			return "login";
		}
		
		return super.execute();
	}
	
	
	public String loginpayroll(){
		
		
		return "loginpayroll";
	}
	
	public String managecompany()throws Exception {
		
		Connection connection=null;
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		try {
			connection=Connection_provider.getconnection();
			PayrollDepartmentDAO departmentDAO=new JDBCPayrollDepartment(connection);
							
			if(loginInfo.getUserType()!=2){
			 
				
				 return "loginpayroll";
				
			} 
			Payroll payroll=departmentDAO.getCompanyDetails("1");
			Payroll payroll2=departmentDAO.getCompanyDetailsUser(loginInfo.getClinicid());
//		   payrollForm.setCompany_name(payroll.getCompany_name());
//		    payrollForm.setDate_format(payroll.getDate_format());
//		    payrollForm.setItmonth(payroll.getItmonth());
//		    payrollForm.setTinno(payroll.getTinno());
//		    payrollForm.setPan_no(payroll.getPan_no());
//		    payrollForm.setEsi_no(payroll.getEsi_no()); 
//		    payrollForm.setPf_no(payroll.getPf_no());
//		    payrollForm.setHourly_type(payroll.getHourly_type());
//		    payrollForm.setFixed_hour(payroll.getFixed_hour());
		    payrollForm.setNo_hours(payroll.getNo_hours());
//		    payrollForm.setOt_status(payroll.getOt_status());
//		    payrollForm.setPermissions(payroll.getPermissions());
//		    payrollForm.setPermi_penalty(payroll.getPermi_penalty());
//		    payrollForm.setNo_permission(payroll.getNo_permission());
			//clinicname, country, state, city, address, pincode, mobile, landline, email, website
			payrollForm.setCompany_name(payroll2.getCompany_name());
			payrollForm.setCountry(payroll2.getCountry());
			payrollForm.setState(payroll2.getState());
			payrollForm.setCity(payroll2.getCity());
			payrollForm.setAddress(payroll2.getAddress());
			payrollForm.setPincode(payroll2.getPincode());
			payrollForm.setMobile(payroll2.getMobile());
			payrollForm.setLandLine(payroll2.getLandline());
			payrollForm.setEmail(payroll2.getEmail());
			payrollForm.setWebsiteUrl(payroll2.getWebsite());
			payrollForm.setId(payroll.getId());
		} catch (Exception e) {

		  e.printStackTrace();
		}
		finally{
			connection.close();
		}
	
		
	    return "managecompany";	
	}
	
	
	public String update()throws Exception {
		
	    Connection connection=null;	
		
		try {
			
			connection=Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO=new JDBCPayrollDepartment(connection);
			
			Payroll payroll=new Payroll();
//			payroll.setId(payrollForm.getId());
//			payroll.setCompany_name(payrollForm.getCompany_name());
//			payroll.setDate_format(payrollForm.getDate_format());
//			payroll.setItmonth(payrollForm.getItmonth());
//			payroll.setTinno(payrollForm.getTinno());
//			payroll.setPan_no(payrollForm.getPan_no());
//			payroll.setEsi_no(payrollForm.getEsi_no());
//			payroll.setPf_no(payrollForm.getPf_no());
//			payroll.setHourly_type(payrollForm.getHourly_type());
//			payroll.setFixed_hour(payrollForm.getFixed_hour());
			payroll.setNo_hours(payrollForm.getNo_hours());
//			payroll.setOt_status(payrollForm.getOt_status());
//			payroll.setPermissions(payrollForm.getPermissions());
//			payroll.setPermi_penalty(payrollForm.getPermi_penalty());
//			payroll.setNo_permission(payrollForm.getNo_permission());
			
			
			int result=payrollDepartmentDAO.updateCompany(payroll);
			
			
			
		} catch (Exception e) {

		   e.printStackTrace(); 
		}
		finally{
			connection.close();
		}
	
		return managecompany();
	}
	
	
	public String edit() throws Exception{
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			PayrollDepartmentDAO departmentDAO=new JDBCPayrollDepartment(connection);
			String id=request.getParameter("selectedid");
			Payroll payroll=departmentDAO.getCompanyDetails(id);
			String data=payroll.getId()+"#"+payroll.getCompany_name()+"#"+payroll.getDate_format()+"#"+payroll.getItmonth()+"#"+payroll.getTinno()+"#"+payroll.getPan_no()+"#"+payroll.getEsi_no()+"#"+payroll.getPf_no()+"#"+payroll.getHourly_type()+"#"+payroll.getFixed_hour()+"#"+payroll.getNo_hours()+"#"+payroll.getOt_status()+"#"+payroll.getPermissions()+"#"+payroll.getPermi_penalty()+"#"+payroll.getNo_permission();
			
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
	
		return null;
	}
	
	
	
	public String managebranches() throws Exception {
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
        Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			BranchDAO branchDAO=new JDBCBranchDAO(connection);
			
	        ArrayList<Payroll> branchesList=branchDAO.getAllBranchList();		
			payrollForm.setBranchesList(branchesList);
	        
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
			
		}
		
		return "managebranches";
	}
	
    public String managedepartment() throws Exception {
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		
		
		return "managedepartment";
	}
	
		
    public String leaverequest() throws Exception {
    	Connection connection = null;
    	String status=request.getParameter("status");
		if(status==null)
		{
			status="";
		}
    	try {
    		connection=Connection_provider.getconnection();
    		String branchid=payrollForm.getBranch();
    		 String userid=loginInfo.getUserId();
			PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
			
			String fromDate = payrollForm.getFromdate();
			String toDate = payrollForm.getTodate();	
			String empname=payrollForm.getEmpname();
			String leavests=payrollForm.getLeavests();
			String leavetype=payrollForm.getLeavetype();
			if(leavests==null){
				leavests="";
			}
			if(leavetype==null){
				leavetype="";
			}
			if(empname==null){
				empname="";
			}
			if (fromDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				 cal.add(Calendar.DATE, -15);
				fromDate = dateFormat.format(cal.getTime());
				
				fromDate=DateTimeUtils.getCommencingDate(fromDate);
			} else {

				if (fromDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Calendar cal = Calendar.getInstance();
					 cal.add(Calendar.DATE, -15);
					fromDate = dateFormat.format(cal.getTime());
					fromDate=DateTimeUtils.getCommencingDate(fromDate);
				} else {
					fromDate = DateTimeUtils.getCommencingDate(fromDate);
				}
			}

			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate=DateTimeUtils.getCommencingDate(toDate);
			} else {
				if (toDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Calendar cal = Calendar.getInstance();
					toDate = dateFormat.format(cal.getTime());
					toDate=DateTimeUtils.getCommencingDate(toDate);
				} else {
					toDate = DateTimeUtils.getCommencingDate(toDate);
				}
			}
				int count = payrollMasterDAO.getTotalLeaveCount(branchid, empname, "0", userid, loginInfo,status,fromDate,toDate,leavests,leavetype);
				pagination.setPreperties(count);
				ArrayList<Payroll> leaveList=payrollMasterDAO.getleaveList(branchid, pagination, empname, "0", userid, loginInfo,status,fromDate,toDate,leavests,leavetype);
    		if(status.equals("1")){
				Employee employee=employeeDAO.getAllDetailsEmployee(employeeDAO.getEmployeeEmpid(loginInfo.getUserId()));
				payrollForm.setWorkinghourday(employee.getWorkinghourday());
    		}
    		pagination.setPreperties(count);
    		 pagination.setPage_records(leaveList.size());
    		 payrollForm.setTotalRecords(count);
    		 payrollForm.setPagerecords(String.valueOf(pagination.getPage_records()));
    		payrollForm.setLeaveList(leaveList);
    		payrollForm.setEmpname(empname);
    		payrollForm.setLeavetype(leavetype);
    		payrollForm.setLeavests(leavests);
    		fromDate = DateTimeUtils.changeDateFormattoPicker(fromDate);
    		toDate = DateTimeUtils.changeDateFormattoPicker(toDate);
    		payrollForm.setFromdate(fromDate);
    		payrollForm.setTodate(toDate);
    		if(status.equals("1")){
    			Employee employee=employeeDAO.getallDetailsEmployeeByUserId(userid);
    			payrollForm.setTotalleave(DateTimeUtils.numberCheck(employee.getCasualleave()));
    			payrollForm.setRemainingleave(DateTimeUtils.numberCheck(employee.getRemain_leave()));
    		}
    		payrollForm.setStatus(status);
    		//letter head
    		Clinic clinic = new Clinic();
    		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
    		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
    		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
    		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
    		payrollForm.setClinicName(clinic.getClinicName());
    		payrollForm.setClinicOwner(clinic.getClinicOwner());
    		payrollForm.setOwner_qualification(clinic.getOwner_qualification());
    		payrollForm.setLocationAdressList(locationAdressList);
    		payrollForm.setAddress(clinic.getAddress());
    		payrollForm.setLandLine(clinic.getLandLine());
    		payrollForm.setClinicemail(clinic.getEmail());
    		payrollForm.setWebsiteUrl(clinic.getWebsiteUrl());
    		payrollForm.setClinicLogo(clinic.getUserImageFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	if(status.equals("")){
    		return "empadminleave";
    	}else{
		return "empleave";
    	}
    	
    }
    public Pagination getPagination() {
		return pagination;
	}



	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}



	public String saveleave() throws Exception{
  	  Connection connection=null; 
  	 try {
  	  connection=Connection_provider.getconnection();
  	 
  	PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
  	 PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
  	 Payroll payroll  = new Payroll();
  	  
  	  String expected_date= request.getParameter("expected_date");
  	  String userid=loginInfo.getUserId();
  	  UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
  	  String name=userProfileDAO.getUserNameFromUserid(userid);
  	  
  	  String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
  	  String time=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
  	  
  	payroll.setDate(date);
  	payroll.setName(name);
  	payroll.setTime(time);
  	payroll.setUserid(userid);
  	  /*product.setLocation(location);*/
  	 
  	  
  	  /*int parentid= inventoryProductDAO.saveRequestIndent(payroll);*/
  	  
  	  	String allleaveid = request.getParameter("allleaveid");
  	 
  	  	String days = request.getParameter("days");
  		String leave_reason = request.getParameter("leave_reason");
  		  
  		payroll.setLeave_type(payrollForm.getLeave_type());
  		payroll.setLeave_reason(payrollForm.getLeave_reason());
  		payroll.setFromdate(DateTimeUtils.getCommencingDatePayroll(payrollForm.getFromdate()));
  		payroll.setTodate(DateTimeUtils.getCommencingDatePayroll(payrollForm.getTodate()));
  		payroll.setRequestdate(date);
  		payroll.setDays(payrollForm.getDays());
  		payroll.setEmp_id(employeeDAO.getEmployeeEmpid(userid));
  		payroll.setDate(date);
  		payroll.setNo_hours(payrollForm.getHours());
  		
  		String month[]=payroll.getFromdate().split("/");
  		
  		String mont_yr=month[0]+"/"+month[1];
  		payroll.setMonth(mont_yr);
  		int res=payrollMasterDAO.saveRequestLeave(payroll);
  		
  		ArrayList<Payroll>printleavelist = payrollMasterDAO.getleaveDetailsPrint(""+res);
  		
  		Employee employee=employeeDAO.getAllDetailsEmployee(payroll.getEmp_id());
  		ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
  		String to =payrollMasterDAO.getdepartmentEmail(employee.getDepartment());
		String cc=employee.getEmail();
		int size=printleavelist.size();
		String leave=printleavelist.get(size-1).getLeave_type();
		String leavereason="";
		if(leave.equals("1")){
			leavereason="Casual Leave";
		}else if (leave.equals("2")) {
			leavereason="Medical Leave";
		}else{
			leavereason="Loss of Pay Leave";
		}
		String subject="Mail Regarding "+leavereason+"";
		String notes="";
		
		
		
		int selectedid = loginInfo.getId();
		ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
		com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
		
		StringBuffer buffer = new StringBuffer();
		/*buffer.append("Respected Sir/Mam,<br>");
		buffer.append("I am "+employee.getName()+" writing to you to let you know that  I will not be able to come to the office ");
		buffer.append("from <strong>"+DateTimeUtils.getCommencingDatePayroll(printleavelist.get(size-1).getFromdate())+"</strong> ");
		buffer.append(" to <strong>"+DateTimeUtils.getCommencingDatePayroll(printleavelist.get(size-1).getTodate())+"</strong>.<br> ");
		buffer.append("I will not be able to come to the office <strong>"+printleavelist.get(size-1).getDays()+"</strong> Days.<br>");
		buffer.append("<strong>Reason: </strong> "+printleavelist.get(size-1).getLeave_reason()+"<br>");
		buffer.append("I will be grateful to you for considering my leave application.<br>");
		buffer.append("Thanking You.<br>");
		buffer.append("Sincerely,<br>");*/
		
		buffer.append("Leave request from "+employee.getName().toUpperCase()+" as below: <br><br>");
		buffer.append("Status: REQUESTED <br>");
		buffer.append("From: "+DateTimeUtils.getCommencingDatePayroll(printleavelist.get(size-1).getFromdate())+" to "+DateTimeUtils.getCommencingDatePayroll(printleavelist.get(size-1).getTodate())+" <br>" );
		buffer.append("Duration: "+printleavelist.get(size-1).getDays()+" Days <br>");
		buffer.append("Reason: "+printleavelist.get(size-1).getLeave_reason()+ " <br>");
		notes = buffer.toString();
		/*EmbeddedImageEmailUtil.sendMailFromSupport(to, cc, subject, notes);*/
		EmailLetterLog emailLetterLog=new EmailLetterLog();
		EmbeddedImageEmailUtil.sendMailPayroll(connection, to, cc, subject, notes, loginInfo,emailLetterLog);
  	  } catch (Exception e) {
  	      e.printStackTrace();
  	  }
  	  finally {
  	   connection.close();
  	  }
  	   return "leaveemp";
  	  }
    public String addnewleave() throws Exception  {
  	  Connection connection=null;
  	  try {
  	   connection = Connection_provider.getconnection();
  		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
  	  	 
  	  	 /*Payroll payroll  = new Payroll();*/
  	   String leave_type=request.getParameter("leave_type");
  	   String leavefromdate=request.getParameter("leavefromdate");
  	   String leavetodate=request.getParameter("leavetodate");
  	 String leave_reason=request.getParameter("leave_reason");
  	String days=request.getParameter("days");
  	 
  	   String userid = loginInfo.getUserId();
  	 
  	   
  	   StringBuffer buffer=new StringBuffer();
  	   
  	   
  	 
 
  		   
  		   buffer.append("<tr>");
  		   buffer.append("<td>"+leave_type+" <input type='hidden' class='dclass' value='"+leave_type+"' /></td>");
  		 buffer.append("<td>"+leave_reason+" <input type='hidden' class='dclass' value='"+leave_reason+"' /></td>");
  		  /* buffer.append("<td><textarea class='form-control' name='reasonleave' rows='2' placeholder='Enter Reason' id='reasonleave'></textarea></td>");*/
  		   buffer.append("<td>"+leavefromdate+" <input type='hidden' class='dclass' value='"+leavefromdate+"' /></td>");
  		   buffer.append("<td>"+leavetodate+" <input type='hidden' class='dclass' value='"+leavetodate+"' /></td>");
  		  /* buffer.append("<td>"+leave_type+" <input type='hidden' class='dclass' value='"+leave_type+"' /></td>");*/
  		 buffer.append("<td>"+days+" <input type='hidden' class='dclass' value='"+days+"' /></td>");
  		   buffer.append("</tr>");
  	   
  	    response.setContentType("text/html");
  	    response.setHeader("Cache-Control", "no-cache");
  	    response.getWriter().write(""+buffer.toString()+""); 
  	  
  	 } catch (Exception e) {
  	  e.printStackTrace();
  	 }
  	 finally {
  	  connection.close();
  	 }
  	   
  	   return null;
  	  }
  	  public String showleaverequest() throws Exception{
  		Connection connection=null;
  		try {
  			 connection = Connection_provider.getconnection();
  	  		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
  	  		String id = request.getParameter("id");
  	  		String userid = loginInfo.getUserId();
  	  				
  	  		Payroll payroll = payrollMasterDAO.getleaveDetails(id);
  	  	/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
  	  	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance(); 
		String todate = dateFormat.format(cal.getTime());
		StringBuilder builder = new StringBuilder();
		
		/*builder.append(""+payroll.getLeaveno()+"");
		builder.append("~");*/
		
		builder.append(""+payroll.getRequestdate()+"");
		builder.append("~");
		
		builder.append(""+payroll.getName()+"");
		builder.append("~");
		builder.append(""+payroll.getUserid()+"");
		builder.append("~");
		builder.append("<tr>");
		
	
		builder.append("<td>"+payroll.getLeave_type()+"</td>");
		builder.append("<td>"+payroll.getLeave_reason()+"</td>");
		builder.append("<td>"+DateTimeUtils.getCommencingDatePayroll(payroll.getFromdate())+"</td>");
		builder.append("<td>"+DateTimeUtils.getCommencingDatePayroll(payroll.getTodate())+"</td>");
		builder.append("<td>"+payroll.getDays()+"</td>");
		
		builder.append("</tr>");
		builder.append("~");
		builder.append(""+payroll.getId()+"");
		
		builder.append("~");
		builder.append("<input type='button'  class='btn btn-success' value='APPROVED' onclick='updateapproveleave("+id+")'>");
		builder.append("<input type='button'  class='btn btn-danger' value='REJECT' onclick='rejectleave("+id+")'>");
		
		
		
		builder.append("~");
		builder.append(""+payroll.getNotes()+"");
		
		
		builder.append("~");
		builder.append(""+payroll.getRequestname()+"");
		
		builder.append("~");
		builder.append(""+payroll.getRequestcontact()+"");
		
		builder.append("~");
		builder.append(""+payroll.getRequestdepartment()+"");
		
		builder.append("~");
		builder.append(""+payroll.getDays()+"");
		
	
		
		/*builder.append("~");
		builder.append(""+payroll.getRequestaddress()+"");*/
		
		 response.setContentType("text/html");
	  	    response.setHeader("Cache-Control", "no-cache");
	  	    response.getWriter().write(""+builder.toString()+""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
  		  
  		  
		return null;
  		  
  	  }
  	public String updateapproveleave()throws Exception{
		 Connection connection = null;
		  try {
			  connection = Connection_provider.getconnection();
	  	  		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
	  	  		PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
	  	  		Employee employee=new Employee();
			String id= request.getParameter("empleave_id");
			String aprove = "1";
			String userid = loginInfo.getUserId();
			
			String requserid= request.getParameter("requserid");
			String isgroupdiscount = request.getParameter("isgroupdiscount");
			/*StringBuilder builder = new StringBuilder();
			builder.append("~");*/
			if(isgroupdiscount==null){
				isgroupdiscount="0";
			}
			Payroll payroll = payrollMasterDAO.getleaveDetails(id);
			if(isgroupdiscount.equals("0")){
				String notes= request.getParameter("notes");
				int result = payrollMasterDAO.updateAproveLeaveLog(id,"1",aprove,userid,notes);
				employee=payrollEmployeeDAO.getallDetailsEmployeeByUserId(payroll.getUserid());
				if(!employee.getCasualleave().equals("")){
					if(!employee.getCasualleave().equals("0")){
						if(!employee.getRemain_leave().equals("")){
							if(!employee.getRemain_leave().equals("0")){
								
								if(payroll.getLeaveno().equals("1")){
									int res=payrollMasterDAO.updateLeavedays(payroll,employee);
									}
							}
						}
					}
				}
			   }else{
				   for (String newid : id.split(",")) {
					   if(newid.equals("0")){
						   continue;
					   }
					   String notes= request.getParameter("approve_notes");
					   int result = payrollMasterDAO.updateAproveLeaveLog(newid,"1",aprove,userid,notes);
					   payroll = payrollMasterDAO.getleaveDetails(newid);
						employee=payrollEmployeeDAO.getallDetailsEmployeeByUserId(payroll.getUserid());
						if(!employee.getCasualleave().equals("")){
							if(!employee.getCasualleave().equals("0")){
								if(!employee.getRemain_leave().equals("")){
									if(!employee.getRemain_leave().equals("0")){
										
										if(payroll.getLeaveno().equals("1")){
											int res=payrollMasterDAO.updateLeavedays(payroll,employee);
											}
									}
								}
							}
						}
				   }
				   
			   }
			Employee employee1=payrollEmployeeDAO.getAllDetailsEmployee(employee.getEmp_id());
	  		ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
			String to=employee1.getEmail();
			String leavereason="";
			String subject="Mail Regarding "+payroll.getLeave_type()+"";
			String notes="";
			
			
			
			int selectedid = loginInfo.getId();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("Respected Sir/Mam,<br>");
			/*buffer.append("I am "+employee.getName()+" writing to you to let you know that  I will not be able to come to the office ");
			buffer.append("from <strong>"+DateTimeUtils.getCommencingDatePayroll(payroll.getFromdate())+"</strong> ");
			buffer.append(" to <strong>"+DateTimeUtils.getCommencingDatePayroll(payroll.getTodate())+"</strong>.<br> ");
			buffer.append("I will not be able to come to the office <strong>"+payroll.getDays()+"</strong> Days.<br>");
			buffer.append("<strong>Reason: </strong> "+payroll.getLeave_reason()+"<br>");
			buffer.append("I will be grateful to you for considering my leave application.<br>");*/
			buffer.append("I would like to inform you that your leave application that dates from <strong>"+DateTimeUtils.getCommencingDatePayroll(payroll.getFromdate())+"</strong> ");
			buffer.append("to <strong>"+DateTimeUtils.getCommencingDatePayroll(payroll.getTodate())+"</strong> ("+payroll.getDays()+" days) has been approved. <br>");
			buffer.append("For the Reason:- "+payroll.getLeave_reason()+"<br> ");
			buffer.append("I have acknowledged the schedule of work distribution among your subordinates that you planned to manage work during the period of your leave <br>");
			buffer.append("Approved Remark:- "+payroll.getComment()+" <br><br>");
			buffer.append("Thanking You.<br>");
			buffer.append("Sincerely,<br>");
			buffer.append(""+employee.getName().toUpperCase()+"");
			notes = buffer.toString();
			/*EmbeddedImageEmailUtil.sendMailFromSupport(to, cc, subject, notes);*/
			EmailLetterLog emailLetterLog=new EmailLetterLog();
			EmbeddedImageEmailUtil.sendMailPayroll(connection, to, "", subject, notes, loginInfo,emailLetterLog);
			/*result =pharmacyDAO.updateNotesofParent(id,notes);*/
			
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		 finally {
			 connection.close();
		 }
		 
		 return "leave";
	 }
  	public String rejectleave()throws Exception{
		 Connection connection = null;
		  try {
			connection = Connection_provider.getconnection();
	  	  	PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
	  	  	PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
	  	  	Payroll payroll = new Payroll();
			String id= request.getParameter("id");
			String notes = request.getParameter("notes");
			String reject = request.getParameter("reject");
			String userid = loginInfo.getUserId();
			int result = payrollMasterDAO.updateRejectLeave(id,"2",notes,reject,userid);
			Payroll payroll1 = payrollMasterDAO.getleaveDetails(id);
			String empid=payrollEmployeeDAO.getEmployeeEmpid(payroll1.getUserid());
			Employee employee1=payrollEmployeeDAO.getAllDetailsEmployee(empid);
	  		ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
			String to=employee1.getEmail();
			String leavereason="";
			String subject="Mail Regarding "+payroll1.getLeave_type()+"";
			String notess="";
			
			
			
			int selectedid = loginInfo.getId();
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("Respected Sir/Mam,<br>");
			/*buffer.append("I am "+employee.getName()+" writing to you to let you know that  I will not be able to come to the office ");
			buffer.append("from <strong>"+DateTimeUtils.getCommencingDatePayroll(payroll.getFromdate())+"</strong> ");
			buffer.append(" to <strong>"+DateTimeUtils.getCommencingDatePayroll(payroll.getTodate())+"</strong>.<br> ");
			buffer.append("I will not be able to come to the office <strong>"+payroll.getDays()+"</strong> Days.<br>");
			buffer.append("<strong>Reason: </strong> "+payroll.getLeave_reason()+"<br>");
			buffer.append("I will be grateful to you for considering my leave application.<br>");*/
			buffer.append("I would like to inform you that your leave application that dates from <strong>"+DateTimeUtils.getCommencingDatePayroll(payroll1.getFromdate())+"</strong> ");
			buffer.append("to <strong>"+DateTimeUtils.getCommencingDatePayroll(payroll1.getTodate())+"</strong> ("+payroll1.getDays()+" days) has been Rejected. <br>");
			buffer.append("For the Reason:- "+payroll1.getLeave_reason()+"<br> ");
			buffer.append("Remark:- "+payroll1.getNotes()+" <br>");
			buffer.append("Thanking You.<br>");
			buffer.append("Sincerely,<br>");
			buffer.append(""+employee1.getName().toUpperCase()+"");
			notess = buffer.toString();
			/*EmbeddedImageEmailUtil.sendMailFromSupport(to, cc, subject, notes);*/
			EmailLetterLog emailLetterLog=new EmailLetterLog();
			EmbeddedImageEmailUtil.sendMailPayroll(connection, to, "", subject, notess, loginInfo,emailLetterLog);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+""); 
			
		 } catch (Exception e) {
			e.printStackTrace();
		}
		  return null;
	 }
  	public String leavePrint()throws Exception{
  		String id = request.getParameter("id"); 
  		
  		
  		Connection connection = null;
  		try {
  			
  			connection = Connection_provider.getconnection();
  	  		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
  	  	String userid = loginInfo.getUserId();
  	  	/*Payroll payroll = payrollMasterDAO.getleaveDetails(id);*/
  	  	ArrayList<Payroll>printleavelist = payrollMasterDAO.getleaveDetailsPrint(id);
  	  	
  	  	payrollForm.setPrintleavelist(printleavelist);
  	  	
  	  	
  	  Payroll payroll2 =payrollMasterDAO.getuserDetails(userid);
		String requestname =payroll2.getFirstName()+ " " +payroll2.getLastName();
		payrollForm.setRequestname(requestname);
		payrollForm.setRequestcontact(payroll2.getMobile());
		payrollForm.setRequestdepartment(payroll2.getJobtitle());
  	  	
  		//letter head
		Clinic clinic = new Clinic();
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		payrollForm.setClinicName(clinic.getClinicName());
		payrollForm.setClinicOwner(clinic.getClinicOwner());
		payrollForm.setOwner_qualification(clinic.getOwner_qualification());
		payrollForm.setLocationAdressList(locationAdressList);
		payrollForm.setAddress(clinic.getAddress());
		payrollForm.setLandLine(clinic.getLandLine());
		payrollForm.setClinicemail(clinic.getEmail());
		payrollForm.setWebsiteUrl(clinic.getWebsiteUrl());
		payrollForm.setClinicLogo(clinic.getUserImageFileName());
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return "leavePrint";
  	}
  	
  	
  	
  	// Approve leave by HR
  	
  	
	public String updateHrapproveleave()throws Exception{
		 Connection connection = null;
		  try {
			  connection = Connection_provider.getconnection();
	  	  		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
	  	  	Payroll payroll = new Payroll();
			String id= request.getParameter("empleave_id");
			String hraprove = "3";
			String userid = loginInfo.getUserId();
			String notes= request.getParameter("notes");
			
			/*StringBuilder builder = new StringBuilder();
			builder.append("~");*/
			
			int result = payrollMasterDAO.updateAproveHrLeaveLog(id,"2",hraprove,userid,notes);
			/*result =pharmacyDAO.updateNotesofParent(id,notes);*/
			
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		 finally {
			 connection.close();
		 }
		 
		 return "leave";
	 }
  	
  	
  	
  	
  	
  	public String showHrleaverequest() throws Exception{
  		Connection connection=null;
  		try {
  			 connection = Connection_provider.getconnection();
  	  		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
  	  		String id = request.getParameter("id");
  	  		String userid = loginInfo.getUserId();
  	  				
  	  		Payroll payroll = payrollMasterDAO.getleaveDetails(id);
  	  	/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
  	  	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance(); 
		String todate = dateFormat.format(cal.getTime());
		StringBuilder builder = new StringBuilder();
		
		/*builder.append(""+payroll.getLeaveno()+"");
		builder.append("~");*/
		
		builder.append(""+payroll.getRequestdate()+"");
		builder.append("~");
		
		builder.append(""+payroll.getName()+"");
		builder.append("~");
		
		builder.append("<tr>");
		
	
		builder.append("<td>"+payroll.getLeave_type()+"</td>");
		builder.append("<td>"+payroll.getLeave_reason()+"</td>");
		builder.append("<td>"+payroll.getFromdate()+"</td>");
		builder.append("<td>"+payroll.getTodate()+"</td>");
		
		
		
		builder.append("</tr>");
		builder.append("~");
		builder.append(""+payroll.getId()+"");
		
		builder.append("~");
		builder.append("<input type='button'  class='btn btn-success' value='APPROVED BY HR' onclick='updateHrapproveleave("+id+")'>");
		builder.append("<input type='button'  class='btn btn-danger' value='REJECT' onclick='rejectleave("+id+")'>");
		
		
		
		builder.append("~");
		builder.append(""+payroll.getNotes()+"");
		
		
		builder.append("~");
		builder.append(""+payroll.getRequestname()+"");
		
		builder.append("~");
		builder.append(""+payroll.getRequestcontact()+"");
		
		builder.append("~");
		builder.append(""+payroll.getRequestdepartment()+"");
		
		builder.append("~");
		builder.append(""+payroll.getDays()+"");
		
	
		
		/*builder.append("~");
		builder.append(""+payroll.getRequestaddress()+"");*/
		
		 response.setContentType("text/html");
	  	    response.setHeader("Cache-Control", "no-cache");
	  	    response.getWriter().write(""+builder.toString()+""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
  		  
  		  
		return null;
  		  
  	  }
  	
  	
  	
  	
	public void prepare() throws Exception {
		Connection connection=null;
  		try {
  			 connection = Connection_provider.getconnection();
	InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
	 ArrayList<Master> stateList= vendorDAO.getAllStateList();
		ArrayList<Master> cityList= vendorDAO.getAllCityList();
		payrollForm.setStatelist(stateList);
		payrollForm.setCitylist(cityList);
} catch (Exception e) {
	e.printStackTrace();
}
		 
		
	}

	public String weekdays(){
		Connection connection=null;
  		try {
  			 connection = Connection_provider.getconnection();
  			PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
  			String result=payrollMasterDAO.getweekdays();
  			payrollForm.setHideweekleave(result);
  		}catch (Exception e) {
  			e.printStackTrace();
  		}
		return "weekleave";
	}
	public String updateweekdays(){
		Connection connection=null;
  		try {
  			String weekday=payrollForm.getWeekleave();
  			
  			weekday=weekday.replaceAll("\\s+","");

  			 connection = Connection_provider.getconnection();
  			PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
  			int result=payrollMasterDAO.updateweekdays(weekday);
  		}catch (Exception e) {
  			e.printStackTrace();
  		}
		return "updateweekleave";
	}
	
	public String geteditleave() {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
			String id=request.getParameter("id");
			Payroll payroll = payrollMasterDAO.getleaveDetails(id);
			StringBuffer buffer = new StringBuffer();

			buffer.append(payroll.getLeaveno()+ "~" + DateTimeUtils.getCommencingDatePayroll(payroll.getFromdate()) + "~" + DateTimeUtils.getCommencingDatePayroll(payroll.getTodate()) + "~"
					+ payroll.getDays() + "~" + payroll.getLeave_reason()+ "~" + payroll.getId()+ "~" + payroll.getNo_hours());

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
public String deleteleave() {
		
		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
			String id=request.getParameter("id");
			int result = payrollDepartmentDAO.deleteLeave(id);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("0");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null; 
		}


public String updateleave() throws Exception {

	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;

	try {

		connection = Connection_provider.getconnection();
		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);

		Payroll payroll = new Payroll();
		String id=request.getParameter("upleaveid");
		payroll.setId(Integer.parseInt(id));
//		payroll.setName(payrollForm.getName());
		payroll.setFromdate(DateTimeUtils.getCommencingDatePayroll(payrollForm.getFromdate()));
		payroll.setTodate(DateTimeUtils.getCommencingDatePayroll(payrollForm.getTodate()));
		payroll.setLeave_type(payrollForm.getLeave_type());
		payroll.setDays(payrollForm.getDays());
		payroll.setNo_hours(payrollForm.getHours());
		payroll.setLeave_reason(payrollForm.getLeave_reason());
		
		int result = payrollMasterDAO.updateLeave(payroll);

	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}

	 return "leaveemp";
}
 public String payrollotdashboard() throws Exception{
	 
	 Connection connection = null;
 	try {
 			connection=Connection_provider.getconnection();
 		 	String userid=loginInfo.getUserId();
			PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
			PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
			String fromDate = payrollForm.getFromdate();
			String toDate = payrollForm.getTodate();	
			String otstatus=payrollForm.getOtstatus();
			String empname=payrollForm.getEmpname();
			if(otstatus==null){
				otstatus="";
			}
			if (fromDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				fromDate = dateFormat.format(cal.getTime());
				fromDate=DateTimeUtils.getCommencingDate(fromDate);
			} else {

				if (fromDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Calendar cal = Calendar.getInstance();
					fromDate = dateFormat.format(cal.getTime());
					fromDate=DateTimeUtils.getCommencingDate(fromDate);
				} else {
					fromDate = DateTimeUtils.getCommencingDate(fromDate);
				}
			}

			if (toDate == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				toDate = dateFormat.format(cal.getTime());
				toDate=DateTimeUtils.getCommencingDate(toDate);
			} else {
				if (toDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Calendar cal = Calendar.getInstance();
					toDate = dateFormat.format(cal.getTime());
					toDate=DateTimeUtils.getCommencingDate(toDate);
				} else {
					toDate = DateTimeUtils.getCommencingDate(toDate);
				}
			}
				int count = payrollMasterDAO.getTotalOTlist(userid, loginInfo,fromDate,toDate,otstatus,empname);
				ArrayList<Payroll> otList=payrollMasterDAO.getOTList( pagination,userid, loginInfo,fromDate,toDate,otstatus,empname);
				pagination.setPreperties(count);
 		pagination.setPreperties(count);
 		 pagination.setPage_records(otList.size());
 		 payrollForm.setTotalRecords(count);
 		 payrollForm.setPagerecords(String.valueOf(pagination.getPage_records()));
 		payrollForm.setLeaveList(otList);
 		fromDate = DateTimeUtils.changeDateFormattoPicker(fromDate);
 		toDate = DateTimeUtils.changeDateFormattoPicker(toDate);
 		payrollForm.setFromdate(fromDate);
 		payrollForm.setTodate(toDate);
 		//letter head
 		Clinic clinic = new Clinic();
 		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
 		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
 		ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
 		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
 		payrollForm.setClinicName(clinic.getClinicName());
 		payrollForm.setClinicOwner(clinic.getClinicOwner());
 		payrollForm.setOwner_qualification(clinic.getOwner_qualification());
 		payrollForm.setLocationAdressList(locationAdressList);
 		payrollForm.setAddress(clinic.getAddress());
 		payrollForm.setLandLine(clinic.getLandLine());
 		payrollForm.setClinicemail(clinic.getEmail());
 		payrollForm.setWebsiteUrl(clinic.getWebsiteUrl());
 		payrollForm.setClinicLogo(clinic.getUserImageFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}
 	return "payrollotdashboard";
 }
 
 
	public String saveot() throws Exception{
	  	  Connection connection=null; 
	  	 try {
	  		 connection=Connection_provider.getconnection();
	  	 
	  	  	PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
	  	  	PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
	  	  	Payroll payroll  = new Payroll();
	  	  
	  	  	String userid=loginInfo.getUserId();
	  	  	UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
	  	  	String name=userProfileDAO.getUserNameFromUserid(userid);
	  	  
	  	  	String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
//	  	  	String time=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
		  	payroll.setDate(date);
		  	payroll.setName(name);
		  	payroll.setUserid(userid);
	  		payroll.setOtreason(payrollForm.getOtreason());
	  		payroll.setFromtime(payrollForm.getFromtime());
	  		payroll.setTotime(payrollForm.getTotime());
	  		payroll.setRequestdate(date);
	  		payroll.setTime(payrollForm.getTime());
	  		payroll.setEmp_id(employeeDAO.getEmployeeEmpid(userid));
	  		payroll.setDate(date);
	  		  int res=payrollMasterDAO.saveRequestOT(payroll);
	  	
	  	  } catch (Exception e) {
	  	      e.printStackTrace();
	  	  }
	  	   return "otemp";
	  	  }
	public String showotrequest() throws Exception{
		Connection connection=null;
  		try {
  			 connection = Connection_provider.getconnection();
  	  		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
  	  		String id = request.getParameter("id");
  	  		String userid = loginInfo.getUserId();
  	  		String sts=request.getParameter("sts");	
  	  		Payroll payroll = payrollMasterDAO.getotDetails(id);
  	  	/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
  	  	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance(); 
		String todate = dateFormat.format(cal.getTime());
		StringBuilder builder = new StringBuilder();
		
		/*builder.append(""+payroll.getLeaveno()+"");
		builder.append("~");*/
		
		builder.append(""+payroll.getRequestdate()+"");
		builder.append("~");
		
		builder.append(""+payroll.getRequestname()+"");
		builder.append("~");
		builder.append(""+payroll.getRequestuser()+"");
		builder.append("~");
		builder.append("<tr>");
		
	
		builder.append("<td>"+payroll.getRequestremark()+"</td>");
		builder.append("<td>"+payroll.getFromtime()+"</td>");
		builder.append("<td>"+payroll.getTotime()+"</td>");
		builder.append("<td>"+payroll.getTime()+"</td>");
		
		builder.append("</tr>");
		builder.append("~");
		builder.append(""+payroll.getId()+"");
		
		builder.append("~");
		if(sts.equals("1")){
			builder.append("<input type='button'  class='btn btn-success' value='APPROVE BY HR' onclick='updateapproveot("+id+",1)'>");
		}else{
			builder.append("<input type='button'  class='btn btn-success' value='APPROVE BY HOD' onclick='updateapproveot("+id+",0)'>");
		}
		if(sts.equals("1")){
		builder.append("<input type='button'  class='btn btn-danger' value='REJECT' onclick='rejectot("+id+",1)'>");
		}else{
			builder.append("<input type='button'  class='btn btn-danger' value='REJECT' onclick='rejectot("+id+",0)'>");
		}
		
		
		builder.append("~");
		if(sts.equals("1")){
			builder.append(""+payroll.getApproveremark2()+"");
		}else{
			builder.append(""+payroll.getApproveremark1()+"");
		}
		
		builder.append("~");
		builder.append(""+payroll.getRequestname()+"");
		
		builder.append("~");
		builder.append(""+payroll.getRequestcontact()+"");
		
		builder.append("~");
		builder.append(""+payroll.getRequestdepartment()+"");
		
		builder.append("~");
		builder.append(""+payroll.getTime()+"");
		
		builder.append("~");
		builder.append(""+sts+"");
		
		/*builder.append("~");
		builder.append(""+payroll.getRequestaddress()+"");*/
		
		 response.setContentType("text/html");
	  	    response.setHeader("Cache-Control", "no-cache");
	  	    response.getWriter().write(""+builder.toString()+""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
  		  return null;
	}
	public String updateapproveot() throws SQLException   {
		 Connection connection = null;
		  try {
			  connection = Connection_provider.getconnection();
	  	  	PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
	  	  	PayrollEmployeeDAO payrollEmployeeDAO=new JDBCPayrollEmployeeDAO(connection);
	  	  	Employee employee=new Employee();
			String id= request.getParameter("empot_id");
			String sts= request.getParameter("sts");
			String aprove = "1";
			String userid = loginInfo.getUserId();
			String notes= request.getParameter("notes");
			String requserid= request.getParameter("requserid");
			Payroll payroll = payrollMasterDAO.getotDetails(id);
			payroll.setId(Integer.parseInt(id));
			payroll.setApproveby1(userid);
			payroll.setApproveremark1(notes);
			employee=payrollEmployeeDAO.getallDetailsEmployeeByUserId(payroll.getUserid());
			int res=payrollMasterDAO.updateot(payroll,employee,sts);
			
			
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		 finally {
			 connection.close();
		 }
		 
		 return "otemp";
	}
	
public String rejectot()throws Exception{
	 Connection connection = null;
	  try {
		connection = Connection_provider.getconnection();
 	  	PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
 	  	Payroll payroll = new Payroll();
		String id= request.getParameter("id");
		String notes = request.getParameter("notes");
		String reject = request.getParameter("reject");
		String sts = request.getParameter("sts");
		String userid = loginInfo.getUserId();
		int result = payrollMasterDAO.updateRejectOt(id,notes,reject,userid,sts);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+result+""); 
		
	  } catch (Exception e) {
		e.printStackTrace();
	  }
	  return null;
	}

public String geteditot() {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		PayrollMasterDAO payrollMasterDAO=new JDBCPayrollMasterDAO(connection);
		String id=request.getParameter("id");
		Payroll payroll = payrollMasterDAO.getotDetails(id);
		StringBuffer buffer = new StringBuffer();

		buffer.append(payroll.getFromtime()+ "~" + payroll.getTotime()+ "~" + payroll.getTime() + "~"
				+ payroll.getRequestremark() + "~" + payroll.getId());

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String updateot() throws Exception {

	if (!verifyLogin(request)) {

		return "login";
	}
	Connection connection = null;

	try {

		connection = Connection_provider.getconnection();
		PayrollMasterDAO payrollMasterDAO = new JDBCPayrollMasterDAO(connection);

		Payroll payroll = new Payroll();
		String id=request.getParameter("upleaveid");
		payroll.setId(Integer.parseInt(id));
//		payroll.setName(payrollForm.getName());
		payroll.setFromtime(payrollForm.getFromtime());;
		payroll.setTotime(payrollForm.getTotime());;
		payroll.setTime(payrollForm.getTime());
		payroll.setOtreason(payrollForm.getOtreason());
		
		
		int result = payrollMasterDAO.updateOT(payroll);

	} catch (Exception e) {

		e.printStackTrace();
	} finally {
		connection.close();
	}

	 return "otemp";
}


public String deleteot() {
	
	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();
		PayrollDepartmentDAO payrollDepartmentDAO = new JDBCPayrollDepartment(connection);
		String id=request.getParameter("id");
		int result = payrollDepartmentDAO.deleteOT(id);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("0");

	} catch (Exception e) {

		e.printStackTrace();
	}
	return null; 
	}
}