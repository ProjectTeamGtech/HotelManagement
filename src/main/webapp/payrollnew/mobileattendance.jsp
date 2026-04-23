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
	 <style type="text/css">
	 .shubh{
	display:table-cell;
    text-align:center;
	 }
	 .rTable {
   display: table;
   width: 100%;
}
.rTableRow {
   display: table-row;
}
.rTableHeading {
   display: table-header-group;
   background-color: #ddd;
}
.rTableCell, .rTableHead {
   display: table-cell;
   padding: 3px 10px;
   border: 1px solid #999999;
}
.rTableHeading {
   display: table-header-group;
   background-color: #ddd;
   font-weight: bold;
}
.rTableFoot {
   display: table-footer-group;
   font-weight: bold;
   background-color: #ddd;
}
.rTableBody {
   display: table-row-group;
}
	 </style>
    </head>
    <script type="text/javascript">
    window.onload = function() {
    	var year = document.getElementById("selectyr").value;
    	document.getElementById("year").value = year;
    }
    </script>
    <body>
			<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
                <div class="content container-fluid">
					<div class="row">
						<div class="col-sm-8">
							<h4 class="page-title">Mobile Attendance</h4>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<div class="card punch-status">
								<div class="card-body">
									<h5 class="card-title">Timesheet <small class="text-muted"><s:property value="date"/></small></h5>
									<div class="punch-det">
									<s:if test="status==1">
									<h6>Check In at</h6>
									<p><s:property value="indatetime"/></p>
									</s:if>
									<s:elseif test="status==2">
									<h6>Check Out at</h6>
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
									<s:textarea name="remarkintime"  cols="35" rows="3" id="remarkintime" style="margin-left: 10px;" placeholder="Check In Remark"></s:textarea><br><br>
										<button type="button" class="btn btn-primary punch-btn" id="checkin" onclick="getLocation(1,'checkin')">Check In</button>
										
										</s:if>
										<s:elseif test="status==1">
										<s:textarea name="remarkouttime" style="margin-left: 10px;" placeholder="Check Out Remark" cols="35" rows="3" id="remarkouttime"></s:textarea><br><br>
										<button type="button" class="btn btn-primary punch-btn" id="checkout" onclick="getLocation(2,'checkout')">Check Out</button>
										</s:elseif>
										<s:elseif test="status==2">
										<label>Last Check In Time</label><br>
										<button type="button" class="btn btn-primary punch-btn" onclick="addmorecheckin()">More Check IN</button>
										</s:elseif>
										<s:else>
										</s:else>
										
									</div>
						</div>	
						</div>	
						</div>
						</div>	
					<!-- Search Filter -->
					<s:form action="mobileattdashboardAttendance">
					<div class="col-sm-12">
					<div class="row filter-row">
					
					<s:hidden name="year" id="selectyr" />
					
						<div class="col-sm-3">  
							<div class="form-group form-focus">
								<div class="cal-icon">
									<input type="text" name="date" class="form-control floating datetimepicker" value="<s:property value="date"/>">
								</div>
								<label class="focus-label">Date</label>
							</div>
						</div><br>
						<%if(loginInfo.isPayrollaccess()){%>
						<div class="col-sm-3">  
							<div class="form-group form-focus">
								<div class="" >
									<input type="text" name="empnamesearch" class="form-control floating ">
								</div>
								<label class="focus-label">Employee Name</label>
							</div>
						</div><br>
						<%} %>
						<%-- <div class="col-sm-3"> 
							<div class="form-group form-focus select-focus hidden">
								<s:select cssClass="select floating"
									list="#{'0':'Jan', '1':'Feb', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
									theme="simple" id="month" name="month" style="width: 43%" />
								<label class="focus-label">Select Month</label>
							</div>
						</div> --%>
						<%-- <div class="col-sm-3"> 
							<div class="form-group form-focus select-focus hidden">
								<select name="year"
									id="year" class="mdb-select md-form form-control " style="width: 78%;height: 48px;">
									<%
										for (int k = 1980; k <= 2020; k++) {
									%>
									<option value="<%=k%>"><%=k%></option>
									<%
										}
									%>
								</select>
								<label class="focus-label">Select Year</label>
							</div>
						</div> --%>
						<div class="col-sm-3">  
						<input type="submit" class="btn btn-success btn-block" value="Search"/>  
						</div>   
						
                    </div>
                    </div>
                    </s:form>  
					<!-- /Search Filter -->
					<div class="row">
                    <div class="col-md-12">
                        
							<div class="table-responsive">
								<%-- 
								<div class="rTable"">
								<div class="rTableRow">
								<% int i = 0;%>
								 <div class=" rTableHead">#</div>
								<div class=" rTableHead">Name</div>
								<div class=" rTableHead">Date</div>
								<div class=" rTableHead">Check In</div>
								<div class=" rTableHead">Check Out</div>
								<div class=" rTableHead">Check In Time Remark</div>
								<div class=" rTableHead">Check Out Time Remark</div>
								<div class=" rTableHead">Total Hour</div>
								<div class=" rTableHead">Check In LongLat</div>
								<div class=" rTableHead">Check Out LongLat</div>
								
								</div>
								
								<s:iterator value="attendanceList">
								<div class="rTableRow">
								<div class="rTableCell"><%= ++i%></div>
								<div class="rTableCell"><s:property value="Emp_name"/></div>
								<div class="rTableCell"><s:property value="date"/></div>
								<div class="rTableCell"><s:property value="indatetime"/></div>
								<div class="rTableCell"><s:property value="outdatetime"/></div>
								<div class="rTableCell"><s:property value="remarkintime"/></div>
								<div class="rTableCell"><s:property value="remarkouttime"/></div>
								<div class="rTableCell"><s:property value="total_hour"/></div>
								<div class="rTableCell"><a href="#" onclick="openBlankPopup('https://www.google.com/maps/place/<s:property value="inlonglat"/>')"><s:property value="inlonglat"/></a></div>
								<div class="rTableCell"><a href="#"  onclick="openBlankPopup('https://www.google.com/maps/place/<s:property value="outlonglat"/>')"><s:property value="outlonglat"/></a></div>
								</div>
								</s:iterator> --%>
								 <table class="table table-striped custom-table mb-0">
									<thead>
									<% int i = 0;%>
										<tr>
											<th>#</th>
											<th>Name </th>
											<th>Date </th>
											<th>Check In</th>
											<th>Check Out</th>
											<th>Check In Time Remark</th>
											<th>Check Out Time Remark</th>
											<th>Total Hour</th>
											<th>Check In LongLat</th>
											<th>Check Out LongLat</th>
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
											<td>
											<%if(loginInfo.isPayrollaccess()){ %>
											<input type="text" name="outdatetime" value="<s:property value="outdatetime" />" onchange="changepunchtime(this.value,<s:property value="id"/>,'outdatetime')">
											<%}else{%>
											<s:property value="outdatetime"/>
											<% }%>
											</td>
											<td><s:property value="remarkintime"/></td>
											<td><s:property value="remarkouttime"/></td>
											<td><s:property value="total_hour"/></td>
											<td><a href="#" onclick="openBlankPopup('https://www.google.com/maps/place/<s:property value="inlonglat"/>')"><s:property value="inlonglat"/></a></td>
											<td><a href="#"  onclick="openBlankPopup('https://www.google.com/maps/place/<s:property value="outlonglat"/>')"><s:property value="outlonglat"/></a></td>
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
		 <script src="https://maps.google.com/maps/api/js?sensor=false"></script>
</body>
</html>