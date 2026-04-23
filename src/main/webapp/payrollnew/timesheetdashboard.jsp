<%@page import="com.apm.common.utils.DateTimeUtils"%>
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
        <title>TimeSheet Dashboard - HRMS admin template</title>
		  <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="payroll/js/payrollvalidatation.js"></SCRIPT>
    <script type="text/javascript" src="common/js/pagination.js"></script>
    </head>
    <%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
      <SCRIPT type="text/javascript">
  
     window.onload=function(){
     	/* 	
     		document.getElementById("leaveadmin").className="active";	 */
    	 recordedtime(0,0);
     };
    
  </SCRIPT>
    <body>
			
				<!-- Page Content -->
                <div class="content container-fluid">
				
					<!-- Page Title -->
					<div class="row">
						<div class="col-sm-8 col-6">
							<h4 class="page-title">TimeSheet Dashboard</h4>
						</div>
						<div class="col-sm-4 col-6 text-right m-b-30">
							<a href="#" class="btn add-btn" onclick="openSamePopup('createtimesheetAttendance')"><i class="fa fa-plus"></i> Create Timesheet</a>
							<!-- <a href="#" class="btn btn-success btn-block" style="width: 46%" onclick="approvedleaveAll()">Approve All</a> -->
						</div>
					</div>
					<!-- /Page Title -->
					
					<!-- Search Filter -->
					<s:form action="timesheetdashboardAttendance">
					<div class="row filter-row">
					   
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<div class="form-group form-focus">
								<div class="cal-icon">
									<s:textfield  name="fromdate" id="fromdate"
					cssClass="form-control floating datetimepicker" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
								</div>
								<label class="focus-label">From</label>
							</div>
						</div>
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<div class="form-group form-focus">
								<div class="cal-icon">
									<s:textfield  name="todate" id="todate"
					cssClass="form-control floating datetimepicker" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
								</div>
								<label class="focus-label">To</label>
							</div>
						</div>
					   <div class="col-sm-3 col-md-3 col-lg-3 col-xl-2 col-6">  
							<!-- <a href="#" class="btn btn-success btn-block"> Search </a>   -->
							<%-- <s:submit value="Search" cssClass="btn btn-success btn-block"/> --%>
							<input type="submit" value="Search" class="btn btn-success btn-block">
					   </div>     
                    </div>
                    </s:form>
					<!-- /Search Filter -->
					
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table table-striped custom-table mb-0 datatable">
									<thead>
						<tr>
							<%int i=1; %>
							<!-- <th style="width: 5%;"><input type="checkbox" onclick="selectallapproveleave(this)"></th> -->
							<th style="width: 3%;">Sr No.</th> 
							<th style="text-align: center;">Name</th>
							<th style="text-align: center;">From Date</th>
							<th style="text-align: center;">To Date</th>
							<th style="text-align: center;">Requested Date</th>
							<th style="text-align: center;">APP/REJ Date</th>
							<th style="text-align: center;">APP/REJ By</th>
							<th style="text-align: center;">Status</th>

						</tr>
					</thead>
					<tbody>
						<s:iterator value="attendanceList">
						<tr>
						<td><%=i %></td>
						<td style="text-align: center;"><s:property value="emp_name"/></td>
						<td style="text-align: center;"><s:property value="fromdate"/></td>
						<td style="text-align: center;"><s:property value="todate"/></td>
						<td style="text-align: center;"><s:property value="date"/></td>
						<td></td>
						<td></td>
						<td style="text-align: center;">
						
						<%if(loginfo.isPayrollaccess()){ %>
						<s:if test="status==2">
						<a href="#" class="btn add-btn" onclick="submittedtimesheet(<s:property value="id"/>)">Submitted</a>
						</s:if>
						<s:elseif test="status==3">
						<a href="#" class="btn add-btn" onclick="submittedtimesheet(<s:property value="id"/>)">Approved</a>
						</s:elseif>
						<s:elseif test="status==4">
						<a href="#" class="btn add-btn" onclick="submittedtimesheet(<s:property value="id"/>)">Rejected</a>
						</s:elseif>
						<%}else{ %>
						<s:if test="status==1">
						<a href="saveasdrafttimesheetAttendance?id=<s:property value="id"/>" class="btn add-btn" >Draft</a>
						</s:if>
						<s:elseif test="status==2">
						<a href="#" class="btn add-btn" onclick="submittedtimesheet(<s:property value="id"/>)">Submitted</a>
						</s:elseif>
						<s:elseif test="status==3">
						<a href="#" class="btn add-btn" onclick="submittedtimesheet(<s:property value="id"/>)">Approved</a>
						</s:elseif>
						<s:elseif test="status==4">
						<a href="#" class="btn add-btn" onclick="submittedtimesheet(<s:property value="id"/>)">Rejected</a>
						</s:elseif>
						<%} %>
						</td>
						</tr>
							<%i++; %>
						</s:iterator>
					</tbody>
								</table>
							</div>
						</div>
					</div>
                </div>
				<!-- /Page Content -->
				
				<s:form action="leaverequestPayrollDashBoard" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
							     <s:hidden name="empname"></s:hidden>
							     <s:hidden name="leavetype"></s:hidden>
							       <s:hidden name="leavests"></s:hidden>
							       <s:hidden name="fromdate"></s:hidden>
							       <s:hidden name="todate"></s:hidden>
								<div class="row" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<s:include value="/common/pages/pagination.jsp"></s:include>
								</div>
							</s:form>
				
				<div id="add_leave" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-xl" role="document">
						<div class="modal-content" style="width: auto;">
							<div class="modal-header">
								<h5 class="modal-title">Time Sheet</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
							<s:form action="timesheetsavedraftAttendance" id="timesheetsavedraft">
							<div class="row">
							<div class="col-sm-6 col-md-3 col-lg-3 col-xl-6 col-12">  
							<div class="form-group form-focus">
								<label>Employee Name: </label>&emsp;
								<span id="empname"></span>
								</div>
								</div>
								<div class="col-sm-6 col-md-3 col-lg-3 col-xl-6 col-12">  
							<div class="form-group form-focus">
								<label>Employee Remark: </label>&emsp;
								<span id="remark"></span>
								</div>
								</div>
								<div class="col-sm-6 col-md-3 col-lg-3 col-xl-6 col-12">  
							<div class="form-group form-focus">
								<label>Status: </label>&emsp;
								<span id="sttst"></span>
								</div>
								</div>
								
								<div class="col-sm-6 col-md-3 col-lg-3 col-xl-6 col-12">  
							<div class="form-group form-focus">
								<label>Approve By: </label>&emsp;
								<span id="appby"></span>
								</div>
								</div>
								<div class="col-sm-6 col-md-3 col-lg-3 col-xl-6 col-12">  
							<div class="form-group form-focus">
								<label>Approve Remark: </label>&emsp;
								<span id="apprm"></span>
								</div>
								</div>
								
								</div>
								<div class="row">
					<div class="col-md-3">
							<div class="stats-info" style="height: 33px;background-color: #5babf9;">
					 <h6 style="margin-top: -9px;color: white;"> Total Work Hour/Week :<span id="workhour"></span></h6>
					 </div>
					</div>
						<div class="col-md-3">
							<div class="stats-info" style="height: 33px;background-color: #5babf9;">
					 <h6 style="margin-top: -9px;color: white;"> Recorded Hour/Week :<span id="recordhour"><s:property	value="total_hour"/></span></h6>
					 </div>
					</div>
					</div>
					<s:hidden id="hiderechr" name="total_hour" value="0"/>
								<table class="table table-striped custom-table mb-0" id="tabledaat">
								
								
								
								</table><br><br>
								
								
								<%String sts=DateTimeUtils.isNull((String)session.getAttribute("dataforts")); %>
							<%if(loginfo.isPayrollaccess()){ %>
							<div class="col-lg-6 col-md-6 text-left">
								<label style="margin-left: 800px">Remark: </label>
								<s:textarea name="notes" cssStyle="margin-left: 700px" cols="35" rows="3" id="notes"/>
								</div>
							<div class="row" style="margin-left: 10px">
               				 <div class="form-inline">
               				 <div class="form-group">  
					   		<input type="button" onclick="saveasdraft(5)" value="Approve" class="btn btn-success btn-block">
					   
			 				</div>&nbsp;&nbsp;&nbsp;
			 				<div class="form-group">
			 				<input type="button" value="Reject" onclick="saveasdraft(6)" class="btn btn-danger btn-block">
			 				</div>
			 				</div>
					  </div> 
					  <%} %>
								</s:form>
							</div>
							 	 	 	
						</div>
					</div>
				</div>
				<!-- /Add Leave Modal -->
				<!-- Edit Leave Modal -->
				<div id="edit_leave" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Edit Leave</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-group">
										<label>Leave Type <span class="text-danger">*</span></label>
										<select class="select">
											<option>Select Leave Type</option>
											<option>Casual Leave 12 Days</option>
										</select>
									</div>
									<div class="form-group">
										<label>From <span class="text-danger">*</span></label>
										<div class="cal-icon">
											<input class="form-control datetimepicker" value="01-01-2019" type="text">
										</div>
									</div>
									<div class="form-group">
										<label>To <span class="text-danger">*</span></label>
										<div class="cal-icon">
											<input class="form-control datetimepicker" value="01-01-2019" type="text">
										</div>
									</div>
									<div class="form-group">
										<label>Number of days <span class="text-danger">*</span></label>
										<input class="form-control" readonly type="text" value="2">
									</div>
									<div class="form-group">
										<label>Remaining Leaves <span class="text-danger">*</span></label>
										<input class="form-control" readonly value="12" type="text">
									</div>
									<div class="form-group">
										<label>Leave Reason <span class="text-danger">*</span></label>
										<textarea rows="4" class="form-control">Going to hospital</textarea>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Save</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Edit Leave Modal -->

				<!-- Approve Leave Modal -->
				<div class="modal custom-modal fade" id="approve_leave" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-header">
									<h3>Leave Approve</h3>
									<p>Are you sure want to approve for this leave?</p>
								</div>
								<div class="modal-btn delete-action">
									<div class="row">
										<div class="col-6">
											<a href="javascript:void(0);" class="btn btn-primary continue-btn">Approve</a>
										</div>
										<div class="col-6">
											<a href="javascript:void(0);" data-dismiss="modal" class="btn btn-primary cancel-btn">Decline</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /Approve Leave Modal -->
				
				<!-- Delete Leave Modal -->
				<div class="modal custom-modal fade" id="delete_approve" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-header">
									<h3>Delete Leave</h3>
									<p>Are you sure want to delete this leave?</p>
								</div>
								<div class="modal-btn delete-action">
									<div class="row">
										<div class="col-6">
											<a href="javascript:void(0);" class="btn btn-primary continue-btn">Delete</a>
										</div>
										<div class="col-6">
											<a href="javascript:void(0);" data-dismiss="modal" class="btn btn-primary cancel-btn">Cancel</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /Delete Leave Modal -->
<!--Leave Rquest Modal -->
	<div id="emp_leave" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						Leave Request ID :-
						<s:property value="leaveid" />
						<%-- &nbsp;|&nbsp; Requested Date :-
						<s:property value="requestdate" />
						&nbsp;|&nbsp; Location :-
						<s:property value="location" />
						/
						<s:property value="userid" /> --%>
					</h4>
				</div>
				<s:form theme="simple" action="saveleavePayrollDashBoard"
					id="leaveform">
					<div class="modal-body">
						<div class="">
							<div class="col-lg-12 col-xs-12 col-md-12"
								style="background-color: rgba(22, 160, 133, 0.07); padding-top: 9px;">
								<input type="hidden" name="allleaveid" id="allleaveid">
								<div class="col-lg-2 col-md-2 col-xs-2">
									<div class="form-group">
										<label>Select Leave Type</label> <select
											class="form-control chosen-select" id="leave_type"
											name="leave_type">
											<option value="annual">Annual</option>
											<option value="sick">Sick</option>
											<option value="monthly">Monthly</option>
											<option value="testing">Testing</option>
										</select>
										<%-- <s:select list="warehouseList" id="warehouse_id"
											name="warehouse_id" listKey="id" listValue="name"
											headerKey="0" headerValue="Select"
											onchange="setProductofStore(this.value)"
											cssClass="form-control chosen-select"></s:select> --%>
									</div>
								</div>

								<div class="col-lg-2 col-md-2 col-xs-2">
									<div class="form-group">
										<label>Reason Of Leave</label> <input type="text"
											name="leave_reason" id="leave_reason" class="form-control"
											placeholder="Enter Leave">

									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-2">
									<div class="form-group">
										<label>From Date</label> <input type="text" name="fromdate"
											id="leavefromdate" onchange="getdiffirencedays()"
											class="form-control" placeholder="Select Date">

									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-2">
									<div class="form-group">
										<label>To Date</label> <input type="text" name="todate"
											id="leavetodate" onchange="getdiffirencedays()"
											class="form-control" placeholder="Select Date">

									</div>
								</div>

								<div class="col-lg-2 col-md-2 col-xs-2">
									<div class="form-group">
										<label>Days</label> <input type="text" name="days" id="days"
											class="form-control" placeholder="Leave Days">

									</div>
								</div>


								<div class="col-lg-1 col-md-1 col-xs-2">
									<div class="form-group" style="margin-top: 22px;">
										<a href="#" class="btn btn-success"
											onclick="addnewLeave('leavetable')">Add</a>
									</div>
								</div>
							</div>
						</div>

						<div class="">
							<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
								<table
									class="table my-table xlstable table-striped table-bordered"
									id="leavetable" style="width: 100%;">
									<thead>
										<tr>
											<!-- <th style="width: 1%;">Sr.no</th> -->
											<th style="width: 18%;">Leave Type</th>
											<th style="width: 18%;">Reason Of Leave</th>
											<th style="width: 10%;">From Date</th>
											<th style="width: 3%;">To Date</th>

											<th style="width: 3%;">Days</th>
										</tr>
									</thead>
									<tbody id="leavebody">
										<tr></tr>
									</tbody>
								</table>

							</div>
						</div>
					</div>

				</s:form>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="saveLeav()"
						style="margin-top: 20px;">Request</button>
				</div>
			</div>

		</div>
	</div>






	<!-- Cart Modal -->
	<div id="proveleave" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->

			<div class="modal-content">
				<s:form action="proveleavePayrollDashBoard" theme="simple"
					id="leaverequestform">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Leave Request Note</h4>
					</div>

					<div class="modal-body">
						<div id="page_printer5">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style="padding: 0px;">
								<%--      	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="hospitaltitlediv">
									        	  		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
					 	<link href="common/css/printpreview.css" rel="stylesheet" />
						<%@ include file="/accounts/pages/letterhead.jsp" %>
					</div>
									        	  		<!-- <h3><b>SHREE NARAYANA HOSPITAL</b></h3>
									        			<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj Mandi,</h5>
									        			<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
									        			<h5>Phone: 0771-3001234,35,36</h5> -->
									        		</div> --%>

								<div class=" letterheadhgt"
									style="border-bottom: 2px solid #6699cc; padding-top: 36px; padding-bottom: 15px; height: 135px;">
									<div id="newpost"
										class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: -52px">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
											style="padding-left: 0px; padding-right: 0px;">
											<link href="common/css/printpreview.css" rel="stylesheet" />
											<%-- <%@ include file="/accounts/pages/letterhead.jsp" %> --%>
											<jsp:include page="/accounts/pages/letterhead.jsp"></jsp:include>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
									<h4>
										<b>Leave Request Note</b>
									</h4>
								</div>
								<s:hidden name="empleave_id" id="empleave_id"></s:hidden>
								<s:hidden name="leaveid" id="leaveid"></s:hidden>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
									style="padding: 0px; margin-bottom: 15px; text-transform: uppercase;">
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										<%-- <p style="margin: 0 0 2.5px;"><b>leave Number : </b><span id="leaveno"></span></p> --%>

										
										<p style="margin: 0 0 2.5px;">
											<b>Name : </b><span id="userid"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Requsted User : </b><span id="requestname"></span>
										</p>
										<s:hidden name="requserid" id="requserid"/>
										<p style="margin: 0 0 2.5px;">
											<b>Requsted Date : </b><span id="requestdate"></span>
										</p>
										<%-- <p style="margin: 0 0 2.5px;"><b>Address : </b><span id="requestaddress"></span></p> --%>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										<%-- <p style="margin: 0 0 2.5px;"><b>Issue Date : </b><span id="issuedate">02-07-2017 18:45</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>To : </b><span id="tolocation">ESIC Pharmacy</span></p> --%>
										<%-- <p style="margin: 0 0 2.5px;"><b>Request Date : </b><span id="requestdate">02-07-17 19:46</span></p> --%>

									</div>
								</div>
								<div></div>
							</div>
							<div>
								<table class="table" style="width: 100%;">
									<thead>
										<tr>

											<th style="width: 9%;">Leave Type</th>
											<th style="width: 20%;">Leave Reason</th>
											<th style="width: 20%;">From Date</th>
											<th style="width: 10%;">To Date</th>
											<th style="width: 10%;">Days</th>

											<!--    <th style="width: 9%;">Issue Qty</th>
            <th style="width: 10%;text-align: right;">Unit Rate</th>
            <th style="width: 10%;text-align: right;">Amount Rs</th>
            <th style="width: 9%;text-align: right;">MRP</th>
            <th style="width: 23%;text-align: right;">MRP Amount</th> -->
										</tr>
									</thead>
									<tbody id="tbodyleaveid">

									</tbody>
								</table>
							</div>
							<div class="col-lg-12 col-md-12 col-xs-12"
								style="padding: 0px; margin-top: 30px;">
								<!-- 	<div class="col-lg-6 col-md-6 col-xs-6">
        		 <p style="margin: 0px;" id="username">Palli R</p>
        		<p style="margin: 0px;" id="userdatetime"></p> 
        		<p>Approved By</p>
        	</div> -->
								<div class="col-lg-6 col-md-6 col-xs-6 text-right">
									<!-- <p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p class="hidden" style="margin: 0px;">Ajay Air</p> -->
									<!-- <p>Received By</p> -->
									<div>
										<s:textarea id="notes" cssClass="form-control" name="notes"
											theme="simple" placeholder="Write Note"></s:textarea>
									</div>

								</div>
							</div>

						</div>

					</div>

					<!-- <div class="modal-footer" id="btndiv">
						<input type="submit" class="btn btn-primary"
							value="Check Availability">
						<input type="button" onclick="printDiv('page_printer');" class="btn btn-primary" value="Print">
					</div> -->

					<div class="modal-footer" id="adarsh">
						<!-- <input type="button" onclick="printDiv2('page_printer5');" class="btn btn-primary" value="Print"> -->
					</div>
				</s:form>
			</div>

		</div>
	</div>




	<!-- Prove Leave by HR  -->


	<!-- Cart Modal -->
	<div id="proveHrleave" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->

			<div class="modal-content">
				<s:form action="proveHrleavePayrollDashBoard" theme="simple"
					id="leaverequestHrform">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Leave Request Note</h4>
					</div>

					<div class="modal-body">
						<div id="page_printer5">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style="padding: 0px;">
								<%--      	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="border-bottom: 1px solid #ddd;" id="hospitaltitlediv">
									        	  		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
					 	<link href="common/css/printpreview.css" rel="stylesheet" />
						<%@ include file="/accounts/pages/letterhead.jsp" %>
					</div>
									        	  		<!-- <h3><b>SHREE NARAYANA HOSPITAL</b></h3>
									        			<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj Mandi,</h5>
									        			<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
									        			<h5>Phone: 0771-3001234,35,36</h5> -->
									        		</div> --%>

								<div class=" letterheadhgt"
									style="border-bottom: 2px solid #6699cc; padding-top: 36px; padding-bottom: 15px; height: 135px;">
									<div id="newpost"
										class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
											style="padding-left: 0px; padding-right: 0px;">
											<link href="common/css/printpreview.css" rel="stylesheet" />
											<%-- <%@ include file="/accounts/pages/letterhead.jsp" %> --%>
											<jsp:include page="/accounts/pages/letterhead.jsp"></jsp:include>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
									<h4>
										<b>Leave Request Note</b>
									</h4>
								</div>
								<s:hidden name="empleave_id" id="empleaveid"></s:hidden>
								<s:hidden name="leaveid" id="leaveid"></s:hidden>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
									style="padding: 0px; margin-bottom: 15px; text-transform: uppercase;">
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										<%-- <p style="margin: 0 0 2.5px;"><b>leave Number : </b><span id="leaveno"></span></p> --%>

										<p style="margin: 0 0 2.5px;">
											<b>Requsted User : </b><span id="hruserid"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Requsted Date : </b><span id="hrdate"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Name : </b><span id="hrrequestname"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Contact : </b><span id="hrrequestcontact"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Department : </b><span id="hrrequestdepartment"></span>
										</p>
										<%-- <p style="margin: 0 0 2.5px;"><b>Address : </b><span id="requestaddress"></span></p> --%>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										<%-- <p style="margin: 0 0 2.5px;"><b>Issue Date : </b><span id="issuedate">02-07-2017 18:45</span></p>
										        		<p style="margin: 0 0 2.5px;"><b>To : </b><span id="tolocation">ESIC Pharmacy</span></p> --%>
										<%-- <p style="margin: 0 0 2.5px;"><b>Request Date : </b><span id="requestdate">02-07-17 19:46</span></p> --%>

									</div>
								</div>
								<div></div>
							</div>
							<div>
								<table class="table" style="width: 100%;">
									<thead>
										<tr>

											<th style="width: 9%;">Leave Type</th>
											<th style="width: 20%;">Leave Reason</th>
											<th style="width: 20%;">From Date</th>
											<th style="width: 10%;">To Date</th>
											<th style="width: 10%;">Days</th>

											<!--    <th style="width: 9%;">Issue Qty</th>
            <th style="width: 10%;text-align: right;">Unit Rate</th>
            <th style="width: 10%;text-align: right;">Amount Rs</th>
            <th style="width: 9%;text-align: right;">MRP</th>
            <th style="width: 23%;text-align: right;">MRP Amount</th> -->
										</tr>
									</thead>
									<tbody id="tbodyHrleaveid">

									</tbody>
								</table>
							</div>
							<div class="col-lg-12 col-md-12 col-xs-12"
								style="padding: 0px; margin-top: 30px;">
								<!-- 	<div class="col-lg-6 col-md-6 col-xs-6">
        		 <p style="margin: 0px;" id="username">Palli R</p>
        		<p style="margin: 0px;" id="userdatetime"></p> 
        		<p>Approved By</p>
        	</div> -->
								<div class="col-lg-6 col-md-6 col-xs-6 text-right">
									<!-- <p class="hidden" style="margin: 0px;">Ajay Air</p>
        		<p class="hidden" style="margin: 0px;">Ajay Air</p> -->
									<!-- <p>Received By</p> -->
									<div>
										<s:textarea id="notes" cssClass="form-control" name="notes"
											theme="simple" placeholder="Write Note"></s:textarea>
									</div>

								</div>
							</div>

						</div>

					</div>

					<!-- <div class="modal-footer" id="btndiv">
						<input type="submit" class="btn btn-primary"
							value="Check Availability">
						<input type="button" onclick="printDiv('page_printer');" class="btn btn-primary" value="Print">
					</div> -->

					<div class="modal-footer" id="adarsh1">
						<!-- <input type="button" onclick="printDiv2('page_printer5');" class="btn btn-primary" value="Print"> -->
					</div>
				</s:form>
			</div>

		</div>
	</div>







	


	<!-- Check Availability Modal -->
	<div id="checkavailabilitymodel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">DEPARTMENT REQUEST NOTE</h4>
				</div>

				<div class="modal-body">
					<div id="page_printer">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
							style="padding: 0px;">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center"
								style="border-bottom: 1px solid #ddd;" id="hospitaltitlediv5">
								<h3>
									<b>SHREE NARAYANA HOSPITAL</b>
								</h3>
								<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj
									Mandi,</h5>
								<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
								<h5>Phone: 0771-3001234,35,36</h5>
							</div>
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
								<h4>
									<b>DEPARTMENT REQUEST NOTE</b>
								</h4>
								<s:hidden name="parentid2" id="parentid5"></s:hidden>
							</div>
						</div>
						<div>
							<table class="table" style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 4%;">Sr.no</th>
										<th style="width: 10%;">Product ID</th>
										<th style="width: 20%;">Product Name</th>
										<th style="width: 9%;">Requested Quantity</th>
									</tr>
								</thead>
								<tbody id="reqtbody">
								</tbody>
							</table>

							<table class="table" style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 4%;"></th>
										<th style="width: 10%;">Product ID</th>
										<th style="width: 20%;">Product Name</th>
										<th style="width: 10%;">HSNO NO</th>
										<th style="width: 10%;">Expiry Date</th>
										<th style="width: 10%;">Batch No</th>
										<th style="width: 20%;">Available Quantity</th>
									</tr>
								</thead>
								<tbody id="checkavailtbody">
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer" id="btndiv5">
					<input type="button" class="btn btn-primary"
						onclick="addToTransferTemp()" value="Add to Transfer"> <input
						type="button" class="btn btn-primary" value="Create PO">
				</div>
			</div>
		</div>
	</div>


	<!-- Transfer Modal -->
	<div id="transfermodel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<s:form action="transferrequestedproductdataProduct" theme="simple"
					id="transferproductform">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Transfer Stock</h4>
					</div>
					<div class="modal-body">
						<div class="">
							<table class="table table-striped table-bordered"
								style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 4%;">Sr.no</th>
										<th style="width: 10%;">Product ID</th>
										<th style="width: 20%;">Product Name</th>
										<th style="width: 9%;">Requested Quantity</th>
									</tr>
								</thead>
								<tbody id="reqtbody2">
								</tbody>
							</table>
							<br>
							<label>TRANSFER STOCK LIST</label>
							<table
								class="table my-table xlstable table-striped table-bordered"
								id="tabletrcount" style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 4%;">Sr.no</th>
										<th style="width: 9%;">HSN Code</th>
										<th style="width: 26%;">Product Name</th>
										<th style="width: 10%;">Batch No</th>
										<th style="width: 10%;">Exp Date</th>
										<th style="width: 10%;">From Location</th>
										<th style="width: 9%;">Current Stock</th>
										<th style="width: 13%;">Requested Location</th>
										<th style="width: 10%;">Transfer Qty</th>
										<th style=""></th>
									</tr>
								</thead>
								<tbody id="transfertbody">

								</tbody>
							</table>
							<input type="hidden" name="tcount" id="tcount">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="confirmTransfer()">Transfer</button>
					</div>
				</s:form>
			</div>
		</div>
	</div>

	<!-- last cart after check avilability model -->
	<div id="lastcart" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">DEPARTMENT REQUEST NOTE</h4>
				</div>

				<div class="modal-body">
					<div id="page_printer2">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
							style="padding: 0px;">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center"
								style="border-bottom: 1px solid #ddd;" id="lasthospitalnamediv">
								<h3>
									<b>SHREE NARAYANA HOSPITAL</b>
								</h3>
								<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near ganj
									Mandi,</h5>
								<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
								<h5>Phone: 0771-3001234,35,36</h5>
							</div>
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
								<h4>
									<b>DEPARTMENT REQUEST NOTE</b>
								</h4>
							</div>
							<s:hidden name="parentid2" id="lastparentid"></s:hidden>
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
								style="padding: 0px; margin-bottom: 15px; text-transform: uppercase;">
								<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									<p style="margin: 0 0 2.5px;">
										<b>Issue Number : </b><span id="lastissueno"></span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b>Requested From : </b><span id="lastfromlocation">Medical
											Store</span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b>Indent Number : </b><span id="lastindentno"></span>
									</p>
								</div>
								<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
									<p style="margin: 0 0 2.5px;">
										<b>Issue Date : </b><span id="lastissuedate"></span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b> </b><span id="tolocation3"></span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b>Request Date : </b><span id="lastrequestdate">02-07-17
											19:46</span>
									</p>
									<p style="margin: 0 0 2.5px;">
										<b>Request User : </b><span id="lastrequestuser"> 
											 </span>
									</p>
								</div>
							</div>
							<div></div>
						</div>
						<div>
							<table class="table" style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 4%;">Sr.no</th>
										<th style="width: 10%;">Product ID</th>
										<th style="width: 20%;">Product Name</th>
										<th style="width: 9%;">Avail Qty</th>
										<th style="width: 9%;">Req Qty</th>
										<th style="width: 15%;">Expected Date</th>
										<th style="width: 20%;">Business Reason</th>
									</tr>
								</thead>
								<tbody id="lastreqtbody">
								</tbody>
							</table>
							<table class="table" style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 4%;">Sr.no</th>
										<th style="width: 9%;">HSN Code</th>
										<th style="width: 20%;">Product Name</th>
										<th style="width: 8%;">Batch No</th>
										<th style="width: 8%;">Exp Date</th>
										<th style="width: 7%;">Issue Qty</th>
										<th style="width: 7%; text-align: right;">Unit Rate</th>
										<th style="width: 9%; text-align: right;">Amount</th>
										<th style="width: 8%; text-align: right;">MRP</th>
										<th style="width: 9%; text-align: right;">MRP Amount</th>
										<th style="width: 31%;">Location</th>
									</tr>
								</thead>
								<tfoot id="lasttfoot">
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td style="text-align: right;">Rs.0.00</td>
										<td style="text-align: right;">Rs.0.00</td>
										<td style="text-align: right;">Rs.0.00</td>
										<td style="text-align: right;">Rs.0.00</td>
										<td></td>
									</tr>
								</tfoot>
								<tbody id="lasttbodyid">

								</tbody>
							</table>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12"
							style="padding: 0px; margin-top: 30px;">
							<div class="col-lg-6 col-md-6 col-xs-6">
								<p>Issued By</p>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-6 text-right">
								<p>Received By</p>
							</div>
						</div>

					</div>

				</div>
				<div class="modal-footer" id="lastbuttondiv"></div>
			</div>

		</div>
	</div>

<div id="approvedmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content" style="width: 125%">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        
        <h4 class="modal-title">Are You Sure To Approve?</h4>
      </div>
      <div class="modal-body">
      <form action="updateapproveleavePayrollDashBoard" method="post" id="updateleavefrm">
      <input type="hidden" name="invoiceId" id="approveinvoiceid">
      <s:hidden name="fromdate"></s:hidden>
      <s:hidden name="todate"></s:hidden>
      <s:hidden name="filter_status"></s:hidden>
       <s:hidden name="countdata"></s:hidden>
       <s:hidden id="isgroupdiscount" name="isgroupdiscount"></s:hidden>
      		<input type="hidden" id="approveddiscount_id" name="id">
      		<s:hidden name="empleave_id" id="allleave_id"></s:hidden>
        	<textarea rows="3"  class="form-control" id="approve_notes" name="approve_notes" placeholder="Approve Note" style="background-color: beige;"></textarea>
     </form>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger"  onclick="approvedLeaveinvoice()"  value="Ok">
      </div>
    </div>

  </div>
</div>


<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
	<!-- Cart Modal -->
	<div id="incart" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<s:form action="checkmedicineavabilityPharmacy" id="deptrequestform"
					theme="simple">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">DEPARTMENT REQUEST NOTE</h4>
					</div>

					<div class="modal-body">
						<div id="page_printer4">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style="padding: 0px;">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center"
									style="border-bottom: 1px solid #ddd;" id="inhospitaltitlediv">
									<h3>
										<b>SHREE NARAYANA HOSPITAL</b>
									</h3>
									<h5>(A Unit of Healthtech Chhattisgarh Pvt. Ltd), Near
										ganj Mandi,</h5>
									<h5>Behind Sector - 5, Devendra nagar, Pandri, Raipur,</h5>
									<h5>Phone: 0771-3001234,35,36</h5>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
									<h4>
										<b>DEPARTMENT REQUEST NOTE</b>
									</h4>
								</div>
								<s:hidden name="parentid" id="inparentid"></s:hidden>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
									style="padding: 0px; margin-bottom: 15px; text-transform: uppercase;">
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										<p style="margin: 0 0 2.5px;">
											<b>Issue Number : </b><span id="inissueno"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Requested From : </b><span id="infromlocation">Medical
												Store</span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Indent Number : </b><span id="inindentno"></span>
										</p>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										<p style="margin: 0 0 2.5px;">
											<b>Issue Date : </b><span id="inissuedate">02-07-2017
												18:45</span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b> </b><span id="intolocation">ESIC Pharmacy</span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Request Date : </b><span id="inrequestdate">02-07-17
												19:46</span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Request User : </b><span id="inrequestuser"> 
												 </span>
										</p>
									</div>
								</div>
								<div></div>
							</div>
							<div>
								<table class="table" id="mytable" style="width: 100%;">
									<thead>
										<tr>
											<th style="width: 4%;">Sr.no</th>
											<th style="width: 7%;">Product ID</th>
											<th style="width: 20%;">Product Name</th>
											<th style="width: 7%;">MRP</th>
											<th class="hidden" style="width: 10%;">Sale Price</th>
											<th style="width: 7%;">Avail Qty</th>
											<th style="width: 7%;">Req Qty</th>
											<th style="width: 25%;">Business Reason</th>
											<th style="width: 1%;" class="hidden-print"></th>
										</tr>
									</thead>
									<tbody id="intbodyid">

									</tbody>
								</table>
							</div>
							<div class="col-lg-12 col-md-12 col-xs-12"
								style="padding: 0px; margin-top: 30px;">
								<div class="col-lg-8 col-md-8 col-xs-6">
									<p style="margin: 0px;" id="inusername"></p>
									<p style="margin: 0px;" id="inuserdatetime"></p>
									<p>Issued By</p>
								</div>
								<div class="col-lg-4 col-md-4 col-xs-6 text-right">
									<p class="hidden" style="margin: 0px;">Ajay Air</p>
									<p class="hidden" style="margin: 0px;">Ajay Air</p>
									<p id="receivedbyid">Received By</p>
									<div id="noteTextBox">
										<p>Admin Note:</p>
										<p id="inadminnote"></p>
									</div>

								</div>
							</div>

						</div>

					</div>
					<div class="modal-footer">
						<input type="button" onclick="printDiv2('page_printer4')"
							class="btn btn-warning hidden-print" value='PRINT'>
					</div>
				</s:form>
			</div>

		</div>
	</div>
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
		
		<!-- Datatable JS -->
		<script src="assets/js/jquery.dataTables.min.js"></script>
		<script src="assets/js/dataTables.bootstrap4.min.js"></script>
		
		<!-- Datetimepicker JS -->
		<script src="assets/js/moment.min.js"></script>
		<script src="assets/js/bootstrap-datetimepicker.min.js"></script>

		<!-- Custom JS -->
		<script src="assets/js/app.js"></script>
		
    </body>
</html>