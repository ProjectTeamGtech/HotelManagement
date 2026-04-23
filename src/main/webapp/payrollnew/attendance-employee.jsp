<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Attendance - HRMS admin template</title>
		
	 <SCRIPT type="text/javascript" src="payroll/js/payrollvalidatation.js"></SCRIPT>
    </head>
    <script type="text/javascript">
    window.onload = function() {
    	var year = document.getElementById("selectyr").value;
    	document.getElementById("year").value = year;
    }
    </script>
			<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
                <div class="content container-fluid">
					<div class="row">
						<div class="col-sm-8">
							<h4 class="page-title">Check In - Check Out</h4>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<div class="card punch-status">
								<div class="card-body">
									<h5 class="card-title">Timesheet <small class="text-muted"><s:property value="date"/></small></h5>
									 <div class="row">
									  <div class="col-sm-4">
								        	<h4>BackDate Attendance</h4>
								        	<div class="" style="width:50%">
									<input type="text" name="punchdate" id="punchdate" class=" floating datetimepicker">
									</div>
									</div>
									
								  <div class="col-sm-3">
								 <h4>Punch In</h4>
								  <div class="" >
									   <s:select  name="fromhour" id="fromhour" list="hourList" cssClass=" mmwidthmang" headerKey="00" headerValue="00" /> :
									 <s:select  name="fromminute" id="fromminute" list="minuteList" cssClass=" mmwidthmang" headerKey="00" headerValue="Select"/>
									    
									  </div>
									
									
								 </div>
								 
								 
								  <div class="col-sm-3">
								 <h4>Punch Out</h4>
								  <div class="" >
									   <s:select  name="tohour" id="tohour" list="hourList" cssClass=" mmwidthmang" headerKey="00" headerValue="00" /> :
									 <s:select  name="tominute" id="tominute" list="minuteList" cssClass=" mmwidthmang" headerKey="00" headerValue="Select"/>
									    
									  </div>
									
									
								 </div>
								 <div class="col-sm-1">
								 <input type="button" class="btn btn-success btn-block" value="Go" onclick="savechekinout()"/>  
								 </div>
									</div>
								
                                   
                                    
                                    
									 
									  
									 <br>
									
                                    
								
									<div class="punch-det">
									<s:if test="status==1">
									<h6>Punch In at</h6>
									<p><s:property value="indatetime"/></p>
									</s:if>
									<s:elseif test="status==2">
									<h6>Punch Out at</h6>
									<p><s:property value="outdatetime"/></p>
									</s:elseif>	
										<s:else>
										</s:else>
									</div>
									<div class="punch-info">
										<div class="punch-hours">
											<span><s:property value="totalhour"/></span>
										</div>
									</div>
									<div class="punch-btn-section">
									<s:if test="status==0">
									<s:textarea name="remarkintime"  cols="35" rows="3" id="remarkintime" style="margin-left: 10px;" placeholder="Punch In Remark"></s:textarea><br><br>
										<button type="button" class="btn btn-primary punch-btn" onclick="insertpunchin(1)">Punch In</button>
										
										</s:if>
										<s:elseif test="status==1">
										<s:textarea name="remarkouttime" style="margin-left: 10px;" placeholder="Punch Out Remark" cols="35" rows="3" id="remarkouttime"></s:textarea><br><br>
										<button type="button" class="btn btn-primary punch-btn" onclick="insertpunchin(2)">Punch Out</button>
										
										</s:elseif>
										<s:else>
										</s:else>
										
									</div>
									<!-- <div class="statistics">
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
									</div> -->
								</div>
							</div>
						</div>
						<%-- <div class="col-md-4">
							<div class="card att-statistics">
								<div class="card-body">
									<h5 class="card-title">Statistics</h5>
									<div class="stats-list">
										<div class="stats-info">
											<p>Today <strong>3.45 <small>/ 8 hrs</small></strong></p>
											<div class="progress">
												<div class="progress-bar bg-primary" role="progressbar" style="width: 31%" aria-valuenow="31" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
										<div class="stats-info">
											<p>This Week <strong>28 <small>/ 40 hrs</small></strong></p>
											<div class="progress">
												<div class="progress-bar bg-warning" role="progressbar" style="width: 31%" aria-valuenow="31" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
										<div class="stats-info">
											<p>This Month <strong>90 <small>/ 160 hrs</small></strong></p>
											<div class="progress">
												<div class="progress-bar bg-success" role="progressbar" style="width: 62%" aria-valuenow="62" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
										<div class="stats-info">
											<p>Remaining <strong>90 <small>/ 160 hrs</small></strong></p>
											<div class="progress">
												<div class="progress-bar bg-danger" role="progressbar" style="width: 62%" aria-valuenow="62" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
										<div class="stats-info">
											<p>Overtime <strong>4</strong></p>
											<div class="progress">
												<div class="progress-bar bg-info" role="progressbar" style="width: 22%" aria-valuenow="22" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="card recent-activity">
								<div class="card-body">
									<h5 class="card-title">Today Activity</h5>
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
 --%>
					<!-- Search Filter -->
					<s:form action="dayemployeeAttendance">
					<div class="col-sm-12">
					<div class="row filter-row">
					
					<s:hidden name="year" id="selectyr" />
					
						<div class="col-sm-3">  
							<div class="form-group form-focus">
								<div class="cal-icon" style="width: 120%">
									<input type="text" name="date" class="form-control floating datetimepicker">
								</div>
								<label class="focus-label">Date</label>
							</div>
						</div><br>
						<%if(loginInfo.isPayrollaccess()){%>
						<div class="col-sm-3">  
							<div class="form-group form-focus">
								<div class="" style="width: 120%">
									<input type="text" name="empnamesearch" class="form-control floating ">
								</div>
								<label class="focus-label">Employee Name</label>
							</div>
						</div><br>
						<%} %>
						<div class="col-sm-3"> 
							<div class="form-group form-focus select-focus">
								<s:select cssClass="select floating"
									list="#{'0':'Jan', '1':'Feb', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
									theme="simple" id="month" name="month" style="width: 43%" />
								<label class="focus-label">Select Month</label>
							</div>
						</div>
						<div class="col-sm-3"> 
							<div class="form-group form-focus select-focus">
								<select name="year"
									id="year" class="mdb-select md-form form-control " style="width: 78%;height: 48px;">
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
						<div class="col-sm-3">  
						<input type="submit" class="btn btn-success btn-block" value="Search"/>  
						</div>   
						
                    </div>
                    </div>
                    </s:form>  
					<!-- /Search Filter -->
					<div class="col-lg-12">
                    <div class="row">
                        
							<div class="table-responsive">
								<table class="table table-striped custom-table mb-0">
									<thead>
									<% int i = 0;%>
										<tr>
											<th>#</th>
											<th>Name </th>
											<th>Date </th>
											<th>Punch In</th>
											<th>Punch Out</th>
											<th>In Time Remark</th>
											<th>Out Time Remark</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="attendanceList">
										<tr>
											<td><%= ++i%></td>
											<td><s:property value="Emp_name"/></td>
											<td><s:property value="date"/></td>
											<td>
											<%if(loginInfo.isPayrollaccess()){ %>
											<input type="text" name="indatetime" value="<s:property value="indatetime"/>" onchange="changepunchtime(this.value,<s:property value="id"/>,'indatetime')">
											<%}else{%>
											<s:property value="indatetime"/>
											<% }%>
											</td>
											<td><%if(loginInfo.isPayrollaccess()){ %>
											<input type="text" name="outdatetime" value="<s:property value="outdatetime" />" onchange="changepunchtime(this.value,<s:property value="id"/>,'outdatetime')">
											<%}else{%>
											<s:property value="outdatetime"/>
											<% }%>
											</td>
											<td><s:property value="remarkintime"/></td>
											<td><s:property value="remarkouttime"/></td>
										</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
                        </div>
                    </div>
                </div>
				<!-- /Page Content -->
				
		
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
		<script src="assets/js/app.js"></script>
		
</html>