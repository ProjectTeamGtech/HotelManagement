<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<html lang="en">
<head>
<title>Title</title>
<meta charset="utf-8"/>

<link href="common/css/960.css" rel="stylesheet" type="text/css"/>
<link href="common/css/coolMenu.css" rel="stylesheet" type="text/css" media="screen"/>
<script type="text/javascript" src="common/js/modernizr-1.6.min.js"></script>





</head>
<body>

	<div class="container_16 .suffix_1">
		<div class="grid_16">
		
		
		
		<ul id="coolMenu">
		   <li><a href="Default">Home</a></li>
			<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
			<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
				
				<li><a href="Logout">Logout</a></li>
					<%if(loginInfo.getUserType() == 1){ %>
						<li><a href="ClinicRegistration">Clinic</a></li>	
						
					<%} %>
					<%if(loginInfo.getUserType() == 2){ %>
						<li><a href="#">Setup</a>
							<ul class="noJS">
								<li><a href="DiaryMangent">Diary Management</a></li>
								<li><a href="UserProfile">Practitioner</a></li>
								<li><a href="PracticeManager">Practice Manager</a></li>
								<!--<li><a href="NotAvailableSlot">Not Available Slot</a></li>
								--><li><a href="AppointmentType">Appointment Type</a></li>
								
							</ul>
						</li>
						<li><a href="inputAccounts">Accounts</a></li>
						<li><a href="NotAvailableSlot">Appointment</a></li>
						<li><a href="#">Report</a>
							<ul class="noJS">
								<li><a href="ApmtDiaryReport">Appointments Diary List Report</a></li>
								<li><a href="walkInAndPreBookedApmtDiaryReport">Walk In and Pre-Booked Appointment</a></li>
							</ul>
						</li>	
						<li><a href="ClinicRegistration">Clinic</a></li>			
					<%} %>
					<%if(loginInfo.getUserType() == 3){ %>
						<li><a href="PracticeManager">Practice Manager</a></li>		
					<%} %>
					<%if(loginInfo.getUserType() == 4){ %>
						<li><a href="UserProfile">Practitioner</a></li>		
					<%} %>
					<%if(loginInfo.getUserType() == 5){ %>
						<li><a href="Appointment">Appointment</a></li>		
					<%} %>
				
				
				</s:if>	
			
			<s:else>
				<li><a href="inputLogin">Login</a></li>
				<li><a href="inputClinicRegistration">Clinic Registration</a></li>
			</s:else>
		</div>
	</div>


<script type="text/javascript" src="common/js/scripts.js"></script>
</body>
</html>