
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
  <title>
  <%if(loginfo.isPayrollaccess()){ %>
  Payroll
  <%}else{ %>
  MY HR
  <%} %>
  </title>
  <link rel="shortcut icon" type="image/x-icon" href="payrollnew/assets/images/favicon.ico">
  <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/fullcalendar.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/select2.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/bootstrap-datetimepicker.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/plugins/morris/morris.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/style.css">
  
<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script src="common/Bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>

<script src="common/js/jquery.js" type="text/javascript"></script>
<script src="common/js/jquery.alerts.js" type="text/javascript"></script>
<link href="common/css/jquery.alerts.css" rel="stylesheet"
	type="text/css" media="screen" />
<script src="common/js/jquery/additional-validation-methods.js"
	type="text/javascript"></script>
	
<script src="common/js/jquery/jquery.validate.js" type="text/javascript"></script>


<script type="text/javascript" src="common/js/fullscreen.js"></script>

<script type="text/javascript" src="common/js/masterValidators.js"></script>

<script type="text/javascript" src="common/js/jquery/bootstrap-growl-master/bootstrap-growl.min.js"></script>
<script src="common/Bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
	<!-- <link href="/SomeProject/static/bundle-bundle_bootstrap_head.css" type="text/css" rel="stylesheet" media="screen, projection">
<link rel="stylesheet" type="text/css" href="print.css" media="print" /> -->
<style type="text/css">
.hidden{
display: none;
}

</style>
</head>
  <body>
    <div class="main-wrapper ">
       <div class="header">
       	<tiles:insertAttribute name="header" />
       </div>
       <div class="sidebar noprint" id="sidebar">
         <div class="sidebar-inner slimscroll">
					<div id="sidebar-menu" class="sidebar-menu">
						<ul>
							<!-- <li> 
								<a href="payrollnew/index.html"><i class="la la-dashboard"></i> <span>Dashboard</span></a>
							</li> -->
							<li class="submenu">
								<a href="#" class="" id="empmenu" onclick="changesubdrop('empmenu')"><i class="la la-user" ></i> <span> Employees</span> <span class="menu-arrow"></span></a>
								<ul style="display: none;">
								<%if(!loginfo.isPayrollaccess()) {%>
								<li><a href="#" onclick="opencPopup('PayrollEmployee?status=0')" id="allemp">Profile</a></li>
								<%} %>
								<%if(loginfo.isPayrollaccess()|| loginfo.getUserType()==2){ %>
									<li><a href="#" onclick="opencPopup('PayrollEmployee?status=1')" id="allemp">All Employees</a></li>
									<li><a href="#" onclick="opencPopup('leaverequestPayrollDashBoard')" id="leaveadmin">Leaves (Admin)</a></li>
									<%} %>
									<%-- <%if(!loginfo.isPayrollaccess()) {%> --%>
								
									<li><a href="#" onclick="opencPopup('leaverequestPayrollDashBoard?status=1')" id="leaveemp">Leaves (Employee)</a></li>
									<%-- <%} %> --%>
									<!-- <li><a href="#" onclick="opencPopup('payrollnew/leave-settings.html')">Leave Settings</a></li> -->
									
									
									 
									 <li><a href="#" onclick="opencPopup('payrollotdashboardPayrollDashBoard')">OT Dashboard </a></li> 
									 <%if(loginfo.isPayrollaccess()|| loginfo.getUserType()==2){ %>
									  <li><a href="#" onclick="openPopup('generateDeclarationOtherTemplate')">Generate Document</a></li> 
									 <%}else{ %>
									   <li><a href="#" onclick="openPopup('getApmtDeclarationOtherTemplate')">Documents</a></li> 
									 <%} %>
									</ul>
									 </li>
									 <li class="submenu">
									 <a href="#" class="" id="timesheet" onclick="changesubdrop('timesheet')"><i class="la la-user" ></i> <span> Timesheet</span> <span class="menu-arrow"></span></a>
								<ul style="display: none;">
								<li><a href="#" onclick="opencPopup('Attendance')" id="attendanceadm">Monthly Attendance Summary</a></li>
								<li><a href="#" onclick="opencPopup('dayemployeeAttendance')">Check In - Check Out </a></li> 
								<li><a href="#" onclick="opencPopup('mobileattdashboardAttendance')">Mobile Attendance </a></li> 
								<li><a href="#" onclick="opencPopup('timesheetdashboardAttendance')">Timesheet Dashboard </a></li> 
								</ul>
							</li>
									 <%if(loginfo.isPayrollaccess()|| loginfo.getUserType()==2){ %>
									 <li class="submenu">
									 <a href="#" class="" id="paymaster" onclick="changesubdrop('paymaster')"><i class="la la-user" ></i> <span> Payroll Master</span> <span class="menu-arrow"></span></a>
								<ul style="display: none;">
									
									<li><a href="#" onclick="opencPopup('holidaysPayrollMaster')" id="holiday">Holidays</a></li>
									<li><a href="#" onclick="opencPopup('PayrollDepartment')" id="department">Departments</a></li>
									<li><a href="#" onclick="opencPopup('showdesignationPayrollDepartment')" id="design">Designations</a></li>
									<li><a href="#" onclick="opencPopup('showqualificationPayrollDepartment')" id="qualification">Qualification</a></li>
									<li><a href="#" onclick="opencPopup('showdocumentlistPayrollMaster')" id="document">Document Type List</a></li>
								    <li><a href="#" onclick="openPopup('addOtherTemplate?action=1')" id="document">Add New Template</a></li>
								<!-- 	<li><a href="#" onclick="opencPopup('payrollnew/timesheet.html')">Timesheet</a></li>
									<li><a href="#" onclick="opencPopup('payrollnew/promotion.html')">Promotion</a></li>
									<li><a href="#" onclick="opencPopup('payrollnew/resignation.html')">Resignation</a></li>
									<li><a href="#" onclick="opencPopup('payrollnew/termination.html')">Termination</a></li>
									<li><a href="#" onclick="opencPopup('payrollnew/overtime.html')">Overtime</a></li> -->
								</ul>
							</li>
							<%} %>
							<!-- <li> 
								<a href="payrollnew/clients.html"><i class="la la-users"></i> <span>Clients</span></a>
							</li>
							<li> 
								<a href="payrollnew/projects.html"><i class="la la-rocket"></i> <span>Projects</span></a>
							</li>
							<li> 
								<a href="payrollnew/tasks.html"><i class="la la-tasks"></i> <span>Tasks</span></a>
							</li>
							<li class="submenu">
								<a href="#"><i class="la la-phone"></i> <span> Calls</span> <span class="menu-arrow"></span></a>
								<ul style="display: none;">
									<li><a href="payrollnew/voice-call.html">Voice Call</a></li>
									<li><a href="payrollnew/video-call.html">Video Call</a></li>
									<li><a href="payrollnew/outgoing-call.html">Outgoing Call</a></li>
									<li><a href="payrollnew/incoming-call.html">Incoming Call</a></li>
								</ul>
							</li>
							<li> 
								<a href="payrollnew/contacts.html"><i class="la la-book"></i> <span>Contacts</span></a>
							</li>
							<li> 
								<a href="payrollnew/leads.html"><i class="la la-user-secret"></i> <span>Leads</span></a>
							</li>
							<li class="submenu">
								<a href="#"><i class="la la-files-o"></i> <span> Accounts </span> <span class="menu-arrow"></span></a>
								<ul style="display: none;">
									<li><a href="payrollnew/estimates.html">Estimates</a></li>
									<li><a href="payrollnew/invoices.html">Invoices</a></li>
									<li><a href="payrollnew/payments.html">Payments</a></li>
									<li><a href="payrollnew/expenses.html">Expenses</a></li>
									<li><a href="payrollnew/provident-fund.html">Provident Fund</a></li>
									<li><a href="payrollnew/taxes.html">Taxes</a></li>
								</ul>
							</li> -->
							<li class="submenu">
								<a href="#" id="payroll" onclick="changesubdrop('payroll')"><i class="la la-money"></i> 
								<span><%if(loginfo.isPayrollaccess()) {%>
								 Payroll
								  <%}else{ %>
								  My PaySlip
								  <%} %></span> <span class="menu-arrow"></span></a>
								<ul style="display: none;">
								<%if(loginfo.isPayrollaccess()|| loginfo.getUserType()==2){ %>
									<li><a href="#" onclick="opencPopup('salaryPayrollMaster')" id="empsalary">Employee Salary</a></li>
									<%}else{ %>
									<li><a href="#" onclick="opencPopup('salaryPayrollMaster')" id="empsalary">Pay Slip</a></li>
									<%} %>
									<!-- <li><a href="payrollnew/salary-view.html"> Payslip </a></li>
									<li><a href="payrollnew/payroll-items.html"> Payroll Items </a></li> -->
								</ul>
							</li>
							<%if(loginfo.isPayrollaccess() || loginfo.getUserType()==2){ %>
							<li class="submenu">
								<a href="#" id="report" onclick="changesubdrop('report')"><i class="la la-pie-chart"></i> <span> Reports </span> <span class="menu-arrow"></span></a>
								<ul style="display: none;">
								<li><a href="#" onclick="opencPopup('employeesalaryrptReportPayroll')" id="rept">Employee Salary Report</a></li>
								<li><a href="#" onclick="opencPopup('attendancemonthReportPayroll')" id="reptm">Month Wise Attendance Report</a></li>
								<li><a href="#" onclick="opencPopup('accountreportReportPayroll')" id="repta">Account Report</a></li>
								<li><a href="#" onclick="opencPopup('mobileattendancereportReportPayroll')" id="repta">Mobile Attendance Report</a></li>
									<!-- <li><a href="#" onclick="opencPopup('salaryPayrollMaster')" id="rept">Employee Salary</a></li>
									
									<li><a href="payrollnew/salary-view.html"> Payslip </a></li>
									<li><a href="payrollnew/payroll-items.html"> Payroll Items </a></li> -->
								</ul>
							</li>
							 <li> 
								<a href="#" onclick="opencPopup('managecompanyPayrollDashBoard')"><i class="la la-cog"></i> <span>Settings</span></a>
							</li>
							<%} %>
							<!--
							<li class="submenu">
								<a href="#"><i class="la la-columns"></i> <span> Pages </span> <span class="menu-arrow"></span></a>
								<ul style="display: none;">
									<li><a href="payrollnew/login.html"> Login </a></li>
									<li><a href="payrollnew/register.html"> Register </a></li>
									<li><a href="payrollnew/forgot-password.html"> Forgot Password </a></li>
									<li><a href="payrollnew/otp.html"> OTP </a></li>
									<li><a href="payrollnew/lock-screen.html"> Lock Screen </a></li>
									<li><a href="payrollnew/profile.html"> Employee Profile </a></li>
									<li><a href="payrollnew/client-profile.html"> Client Profile </a></li>
									<li><a href="payrollnew/error-404.html">404 Error </a></li>
									<li><a href="payrollnew/error-500.html">500 Error </a></li>
									<li><a href="payrollnew/blank-page.html"> Blank Page </a></li>
								</ul>
							</li>
							<li> 
								<a href="payrollnew/components.html"><i class="la la-puzzle-piece"></i> <span>Components</span></a>
							</li>
							<li class="submenu">
								<a href="javascript:void(0);"><i class="la la-share-alt"></i> <span>Multi Level</span> <span class="menu-arrow"></span></a>
								<ul style="display: none;">
									<li class="submenu">
										<a href="javascript:void(0);"> <span>Level 1</span> <span class="menu-arrow"></span></a>
										<ul style="display: none;">
											<li><a href="javascript:void(0);"><span>Level 2</span></a></li>
											<li class="submenu">
												<a href="javascript:void(0);"> <span> Level 2</span> <span class="menu-arrow"></span></a>
												<ul style="display: none;">
													<li><a href="javascript:void(0);">Level 3</a></li>
													<li><a href="javascript:void(0);">Level 3</a></li>
												</ul>
											</li>
											<li><a href="javascript:void(0);"> <span>Level 2</span></a></li>
										</ul>
									</li>
									<li>
										<a href="javascript:void(0);"> <span>Level 1</span></a>
									</li>
								</ul>
							</li> -->
						</ul>
					</div>
                </div>
     
       
       </div>
       <div class="page-wrapper"> 
	   <div class="content container-fluid">
	    <tiles:insertAttribute name="body" />
	   </div>
       </div>
    </div>
    <div class="sidebar-overlay noprint" data-reff="#sidebar"></div>
   <!--  <script type="text/javascript" src="payrollnew/assets/js/jquery-3.2.1.min.js"></script> -->
   <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
   <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
    <script type="text/javascript" src="payrollnew/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/jquery.slimscroll.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/select2.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/moment.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/plugins/morris/morris.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/app.js"></script>
    
   <!--  <script type="text/javascript">
    function changesubdrop(val) {
    	 var element = document.getElementById(""+val);
    	   element.classList.remove("subdrop");
    	/* document.getElementById(""+val).className="subdrop"; */
   document.getElementById(""+val).className += " subdrop";
    }
    </script> -->
  </body>
  <link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>
