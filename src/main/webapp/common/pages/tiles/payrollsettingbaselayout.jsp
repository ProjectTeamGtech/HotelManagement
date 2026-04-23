
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
  <title>Payroll</title>
  <link rel="shortcut icon" type="image/x-icon" href="payrollnew/assets/images/favicon.ico">
  <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/fullcalendar.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/dataTables.bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/select2.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/bootstrap-datetimepicker.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/plugins/morris/morris.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/style.css">
<link rel="stylesheet" type="text/css" href="print.css" media="print" />
<script src="common/Bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="jalert.js"></script>

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
	<link href="/SomeProject/static/bundle-bundle_bootstrap_head.css" type="text/css" rel="stylesheet" media="screen, projection">
<link rel="stylesheet" type="text/css" href="print.css" media="print" />
</head>

  <body>
    <div class="main-wrapper ">
       <div class="header">
       	<tiles:insertAttribute name="header" />
       </div>

<div class="sidebar" id="sidebar">
                <div class="sidebar-inner slimscroll">
					<div class="sidebar-menu">
						<ul>
							<li> 
								<a href="#" onclick="opencPopup('PayrollEmployee?status=1')"><i class="la la-home"></i> <span>Back to Home</span></a>
							</li>
							<li class="menu-title">Settings</li>
							<li class="active"> 
								<a href="settings.html"><i class="la la-building"></i> <span>Company Settings</span></a>
							</li>
							<!-- <li> 
								<a href="localization.html"><i class="la la-clock-o"></i> <span>Localization</span></a>
							</li>
							<li> 
								<a href="theme-settings.html"><i class="la la-photo"></i> <span>Theme Settings</span></a>
							</li>
							<li> 
								<a href="roles-permissions.html"><i class="la la-key"></i> <span>Roles & Permissions</span></a>
							</li>
							<li> 
								<a href="email-settings.html"><i class="la la-at"></i> <span>Email Settings</span></a>
							</li>
							<li> 
								<a href="invoice-settings.html"><i class="la la-pencil-square"></i> <span>Invoice Settings</span></a>
							</li> -->
							<li> 
								<a href="salary-settings.html"><i class="la la-money"></i> <span>Salary Settings</span></a>
							</li>
							<!-- <li> 
								<a href="notifications-settings.html"><i class="la la-globe"></i> <span>Notifications</span></a>
							</li>
							<li> 
								<a href="change-password.html"><i class="la la-lock"></i> <span>Change Password</span></a>
							</li> -->
							<li> 
								<a href="#" onclick="opencPopup('weekdaysPayrollDashBoard')"><i class="la la-cogs"></i> <span>Leave Type</span></a>
							</li>
						</ul>
					</div>
                </div>
            </div>
			<!-- Sidebar -->

       <div class="page-wrapper"> 
	   <div class="content container-fluid">
	    <tiles:insertAttribute name="body" />
	   </div>
       </div>
    </div>
    <div class="sidebar-overlay noprint" data-reff="#sidebar"></div>
    <script type="text/javascript" src="payrollnew/assets/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/jquery.slimscroll.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/select2.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/moment.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/plugins/morris/morris.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/plugins/raphael/raphael-min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/app.js"></script>
  </body>
  <link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>
