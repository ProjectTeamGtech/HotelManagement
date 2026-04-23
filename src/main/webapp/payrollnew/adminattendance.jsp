<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
	 <SCRIPT type="text/javascript" src="payroll/js/payrollvalidatation.js"></SCRIPT>
<script type="text/javascript">
window.onload = function() {
	var year = document.getElementById("selectyr").value;
	document.getElementById("year").value = year;
	/* var ids="0";
	$('.pacc').each(function() { 
			 	var daysmonth=document.getElementById("dayscount").value;
				var actualdays=document.getElementById("days"+ids).value;	
				if(actualdays>daysmonth){
					jAlert('error', "Please Enter days Accor!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				}
			    ids=ids+""+days; 
			  ids++;
			     document.getElementById(""+ids)=	 	
		});  */
		
}
</script>
<script type="text/javascript">
$(document).ready(function() {

 	/* $(".datepc").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});  */
	 var d = new Date();
	var strDate =  d.getDate()+ "/" + (d.getMonth()+1) + "/" + d.getFullYear();
	$('.datepc').each(function() { 
		this.value=strDate;
	}); 
});

/* function validatedays(val){
	var totalmonthday=document.getElementById("totalmonthday").value;
	var workwithleave=document.getElementById("withleave"+val).value;
	var actualdays=document.getElementById("days"+val).value;
	if(actualdays<0){
		jAlert('error', "Please Enter Correct Days!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		document.getElementById("days"+val).value='0';
	}
	/* else if(workwithleave<actualdays){
		jAlert('error', "Please Enter days According to Total Days!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
				removeAlertCss();
			}, 3000);
		document.getElementById("days"+val).value='0';
	} */
} */
	</script>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <script type="text/javascript" src="common/js/pagination.js"></script>
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
  <%String readonly=""; %>
  
 
        <title>Attendance - HRMS admin template</title>
    </head>
    <body>
    <%if(!loginfo.isPayrollaccess()){ 
    readonly="readonly";
    
    }%>
			<!-- /Sidebar -->
			
			<!-- Page Wrapper -->
                <div class="content container-fluid">
					<div class="row">
						<div class="col-sm-8">
							<h4 class="page-title">Attendance</h4>
						</div>
					</div>
					<%
				int i = 0;
			%>
					<!-- Search Filter -->
					<s:form action="Attendance" id="selectmonth">
					<div class="row filter-row">
					<%if(loginfo.isPayrollaccess()){ %>
						<div class="col-sm-3">  
							<div class="form-group form-focus">
								<s:textfield name="empnamesearch" cssClass="form-control" theme="simple"/>
								<label class="focus-label">Employee Name</label>
							</div>
						</div> 
							<%} %>
						<s:hidden name="year" id="selectyr" />
						<div class="col-sm-2"> 
							<div class="form-group form-focus select-focus">
									<s:select cssClass="select floating"
									list="#{'0':'Jan', '1':'Feb', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
									theme="simple" id="month" name="month" style="width: 43%" />
								<label class="focus-label">Select Month</label>
							</div>
						</div>
						<div class="col-sm-2"> 
							<div class="form-group form-focus select-focus">
								<select name="year"
									id="year" class="mdb-select md-form form-control " style="width: 65%;height: 48px;">
									<%
										for (int k = 1980; k <= 2100; k++) {
									%>
									<option value="<%=k%>"><%=k%></option>
									<%
										}
									%>
								</select>
								<label class="focus-label">Select Year</label>
							</div>
						</div>
						<div class="col-sm-2">  
							<input type="submit" class="btn btn-success btn-block" value="Go"> 
						</div>     
						
                    </div>
                    </s:form>
					<!-- /Search Filter -->
					
                    <div class="row">
                        <div class="col-lg-12">
							<div class="table-responsive">
							<s:form id="attendanceform" theme="simple" action="updateAttendance">
					<s:hidden name="month" id="monthid"></s:hidden>
					 
							<s:hidden name="year" id="selectyr" />
							
								<table class="table table-striped custom-table table-nowrap mb-0">
									<thead>
										<tr>
											<th class="text-center">Sr No.</th>
											<th class="text-center">Employee</th>
											<!-- <th>Date</th> -->
											<th class="text-center">Month Days</th>
											<th class="text-center">Week Off</th>
											<th class="text-center">Leaves</th>
											<th class="text-center">Working Days(Hour)</th>
											<th class="text-center">Worked Days</th>
											<th class="text-center">Leave Days</th>
										</tr>
									</thead>
									<tbody>
										
						<%
							i = 0;
								int index = 0;
						%>
						<s:iterator value="attendanceList">
							<tr>
							<input type="hidden" id="dayscount" value="<s:property value='daysmonth'/>">
							<input type="hidden" id="totalmonthday" value="<s:property value='totalmonthday'/>">
								
								<td class="text-center"><%=(++index)%></td>
								<td class="text-center"><s:property value="emp_name" /></td>
								<td  class="hidden"><input type="hidden" id="emp_id<%=i%>" value="<s:property value='emp_id'/>"></td>
								<input type="hidden"  value="<%=i%>" class="pacc">
								<td class="hidden"><input size="6" value="<s:property value='date'/>"
									maxlength="10" type="text" name="attendance[<%=i%>].date"
									id="dates<%=i%>" class="datepc form-control datetimepicker" style="width: 110%" readonly="readonly"/></td>
								<td style="text-align: center"><s:property value='totalmonthday'/></td>
								<td style="text-align: center"><s:property value='weekoff'/></td>
								<td style="text-align: center"><s:property value='leaveday'/></td>
								<%-- <td style="text-align: center"><s:property value='daysmonth'/></td> --%>
								 <td style="text-align: center"><s:property value='workwithleave'/>&nbsp;(<s:property value="Workingdayhour"/>)</td>
								
								<td class="text-center"><input size="6" value="<s:property value='days'/>"
								
									maxlength="10" type="text" name="attendance[<%=i%>].days"
									id="days<%=i%>" onchange="validatedays(<%=i%>)" class="form-control" <%=readonly %>/></td>
								
									<%-- <td><input size="6" value="<s:property value='leaveday'/>" maxlength="10" type="text" name="leaveday"
									id="leaveday"  class="form-control" readonly="readonly"/></td> --%>
									
								<td><input size="6" type="text" name="attendance[<%=i%>].leaveday"
								value="<s:property value="leaveday"/>" id="leaveday<%=i%>"  class="form-control"  /></td>	
									
								<td hidden="hidden"><input size="6"
									value="<s:property value='salaryTotal'/>" maxlength="10"
									type="hidden" name="attendance[<%=i%>].salaryTotal"
									id="salaryTotal<%=i%>" class="form-control"/></td>
								
                                
							</tr>
							<input type="hidden" name="attendance[<%=i%>].leaveday"
								value="<s:property value="leaveday"/>" id="leaveday<%=i%>" />
							<input type="hidden" name="attendance[<%=i%>].basicsalary"
								value="<s:property value="basicsalary"/>" id="basicsalary<%=i%>" />
							<input type="hidden" name="attendance[<%=i%>].month"
								value="<s:property value="month"/>" />
							<input type="hidden" name="attendance[<%=i%>].branch_id"
								value="<s:property value="branch_id"/>" />
							<input type="hidden" name="attendance[<%=i%>].emp_id" id="attendance[<%=i%>].emp_id"
								value="<s:property value="emp_id"/>" />
								<input type="hidden" id="withleave<%=i%>" value="<s:property value='workwithleave'/>">
							<%
								i++;
							%>
						</s:iterator>
									</tbody>
								</table>
								<%if(loginfo.isPayrollaccess()){ %>
								<div>
					 <a href="#" onclick="updateattendance()" class="btn btn-success btn-block" style="width: 11%; margin-left: 410px; margin-top: 20px;">Update</a>
			</div>
			<%} %>
			<input type="hidden" id="count" value="<%=i%>">
							</s:form>
							</div>
                        </div>
                    </div>
                    <s:form action="Attendance" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
							     <s:hidden name="year"></s:hidden>
							     <s:hidden name="month"></s:hidden>
							     <s:hidden name="empnamesearch"></s:hidden>
								<div class="row" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<s:include value="/common/pages/pagination.jsp"></s:include>
								</div>
							</s:form>
				<!-- /Page Content -->
				
				<!-- Attendance Modal -->
				<div class="modal custom-modal fade" id="attendance_info" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Attendance Info</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-md-6">
										<div class="card punch-status">
											<div class="card-body">
												<h5 class="card-title">Timesheet <small class="text-muted">11 Mar 2019</small></h5>
												<div class="punch-det">
													<h6>Punch In at</h6>
													<p>Wed, 11th Mar 2019 10.00 AM</p>
												</div>
												<div class="punch-info">
													<div class="punch-hours">
														<span>3.45 hrs</span>
													</div>
												</div>
												<div class="punch-det">
													<h6>Punch Out at</h6>
													<p>Wed, 20th Feb 2019 9.00 PM</p>
												</div>
												<div class="statistics">
													<div class="row">
														<div class="col-md-6 col-6 text-center">
															<div class="stats-box">
																<p>Break</p>
																<h6>1.21 hrs</h6>
															</div>
														</div>
														<div class="col-md-6 col-6 text-center">
															<div class="stats-box">
																<p>Overtime</p>
																<h6>3 hrs</h6>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="card recent-activity">
											<div class="card-body">
												<h5 class="card-title">Activity</h5>
												<ul class="res-activity-list">
													<li>
														<p class="mb-0">Punch In at</p>
														<p class="res-activity-time">
															<i class="fa fa-clock-o"></i>
															10.00 AM.
														</p>
													</li>
													<li>
														<p class="mb-0">Punch Out at</p>
														<p class="res-activity-time">
															<i class="fa fa-clock-o"></i>
															11.00 AM.
														</p>
													</li>
													<li>
														<p class="mb-0">Punch In at</p>
														<p class="res-activity-time">
															<i class="fa fa-clock-o"></i>
															11.15 AM.
														</p>
													</li>
													<li>
														<p class="mb-0">Punch Out at</p>
														<p class="res-activity-time">
															<i class="fa fa-clock-o"></i>
															1.30 PM.
														</p>
													</li>
													<li>
														<p class="mb-0">Punch In at</p>
														<p class="res-activity-time">
															<i class="fa fa-clock-o"></i>
															2.00 PM.
														</p>
													</li>
													<li>
														<p class="mb-0">Punch Out at</p>
														<p class="res-activity-time">
															<i class="fa fa-clock-o"></i>
															7.30 PM.
														</p>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
				
				<!-- /Attendance Modal -->
				
		
		<!-- Sidebar Overlay -->
		<div class="sidebar-overlay" data-reff=""></div>
		
		<!-- jQuery -->
        <script src="assets/js/jquery-3.2.1.min.js"></script>
		
		<!-- Bootstrap Core JS -->
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
		
		<!-- Slimscroll JS -->
		<script src="assets/js/jquery.slimscroll.min.js"></script>
		
		<!-- Select2 JS -->
		<script src="assets/js/select2.min.js"></script>
		
		<!-- Datetimepicker JS -->
		<script src="assets/js/moment.min.js"></script>
		<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
		
		<!-- Custom JS -->
		
    </body>
    <!-- Loader -->
			<div id="loader-wrapper">
				<div id="loader">
					<div class="loader-ellips">
					  <span class="loader-ellips__dot"></span>
					  <span class="loader-ellips__dot"></span>
					  <span class="loader-ellips__dot"></span>
					  <span class="loader-ellips__dot"></span>
					</div>
				</div>
			</div>
			<script type="text/javascript">
			function updateattendance(){
				$('#loader-wrapper').modal( "show" );
				 document.getElementById("attendanceform").submit();
			}
			</script>
</html>
