<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet"
	type="text/css" />

<style>
.padright {
	padding-left: 40px;
}

.table.table {
	color: RGBA(85, 85, 85, 0.85);
	background-color: #fff;
}

.comtitle {
	font-size: 13px;
	background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
	color: rgb(255, 255, 255);
}

.marbot25 {
	margin-bottom: 25px;
}

.editcompany {
	float: right;
	font-size: 17px;
	color: #fff;
}

.borright {
	border-right: 1px dashed rgb(192, 192, 192);
}

.buildinglogo {
	width: 60%;
	margin-top: 30px;
}

#sidebar .panel-group .panel>.panel-heading+.panel-collapse>.panel-body
	{
	border-top: 0;
	min-height: auto !important;
}

.miheight {
	min-height: auto !important;
}

.my-table th {
	background-color: #424A5D;
	color: #fff !important;
	border-bottom: 1px solid #DFD8D4;
	border-right: 1px solid #DFD8D4;
	border-top: 1px solid #DFD8D4;
	padding: 3px 3px 4px 5px;
	text-align: left;
	font-weight: bold;
	font-size: 11px;
	background-size: 100% 100%;
}

.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th,
	.table>thead>tr>td, .table>thead>tr>th {
	padding: 1px 7px 1px 7px !important;
}

.sidebar-xs #header .branding>a {
	background-position: 6px 10px;
	width: 100% !important;
	font-size: 21px;
	padding: 0px 1px 2px 15px;
	text-align: center;
	color: #fff;
}

.sidebar-xs #header .branding>a>span {
	display: inline-block;
}

.sidebar-xs #header .branding {
	width: 100%;
	padding-top: 7px;
	text-align: center;
}

.theight {
	height: 21px;
}
</style>

<style>
.topheadbaxck {
	background-color: rgb(239, 239, 239);
	padding: 8px 0px;
}

.red {
	color: red;
}

.addcatego {
	float: right;
	margin-top: -40px;
	margin-right: 30px;
}

.sort {
	width: 15%;
	padding-top: 2px;
}

.setborba {
	background-color: #efefef !important;
	padding-top: 5px !important;
}

.dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus
	{
	background-image: linear-gradient(to bottom, #777 0, #777 100%)
		!important;
}

.dropdown-menu {
	padding: 0px 0 !important;
	margin: 0px 0 0 !important;
}

ul.dt-button-collection.dropdown-menu>* {
	-webkit-column-break-inside: avoid;
	break-inside: avoid;
	border-bottom: 1px solid rgba(0, 0, 0, 0.5) !important;
}

@media print {
	body {
		font-size: 9px !important;
	}
	.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
		.table>tbody>tr>td, .table>tfoot>tr>td {
		padding: 2px 2px 2px 2px !important;
		line-height: 1.42857143;
		vertical-align: top;
		border-top: 1px solid #ddd;
		font-weight: normal;
		font-size: 9px !important;
		border-right: none !important;
		border-left: none !important;
	}
	.settopbac {
		background-color: #ddd !important;
	}
	.totalbor {
		background-color: #f5f5f5 !important;
	}
	.print_special {
		border: none !important;
	}
	label {
		font-size: 9px !important;
	}
	p {
		margin: 0 0 2.5px !important;
		font-size: 9px !important;
	}
	.table>thead>tr>th {
		vertical-align: bottom;
		border-bottom: transparent;
		background-color: #ccc !important;
		color: #000 !important;
		font-size: 9px !important;
	}
}
</style>
<SCRIPT type="text/javascript">
       $(document).ready(function() {

		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#todate").datepicker({
			
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#expected_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#leavetodate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#leavefromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
	});
    
    </SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
<script type="text/javascript" src="common/js/pagination.js"></script>

</head>
<body>

	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		<%
			LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		%>
		<div class="row details mainheader">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
					<img src="dashboardicon/indent_request.png"
						class="img-responsive prescripiconcircle">
				</div>
				<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
					<h4>
						<b>Leave Dashboard </b>
					</h4>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
			<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
				<div class="col-md-12">
					<div class="form-inline">

						<%-- 	<s:form action="transferdashboardProduct" theme="simple"> --%>
						<s:form action="leaverequestPayrollDashBoard" theme="simple">
							<div class="form-group">
								<s:textfield id="searchText" name="searchtext"
									cssClass="form-control" placeholder="Search by User" />
							</div>

							<div class="form-group" style="width: 7%;">
								<s:textfield id="fromdate" name="fromdate"
									cssClass="form-control" placeholder="From Date"
									style="width:100%;" />
							</div>

							<div class="form-group" style="width: 7%;">
								<s:textfield name="todate" id="todate" cssClass="form-control"
									placeholder="To Date" style="width:100%;" />
							</div>
							<div class="form-group">
								<%-- 	<s:select headerKey="10" headerValue="Select Status" cssClass="form-control" list="#{'0':'Request', '1':'Approved', '2':'Rejected'" name="filter_status" />		 --%>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">Go</button>
								<a href="#" class="btn btn-warning" data-toggle="modal"
									data-target="#emp_leave">New</a>
							</div>
							<div class="form-inline"
								style="float: right; margin-top: 5px; text-transform: uppercase;">
								<div class="checkbox">
									<label> <i class="fa fa-square" aria-hidden="true"
										style="color: #ffa3a3"></i> Request
									</label>
								</div>
								&nbsp;|
								<div class="checkbox">
									<label> <i class="fa fa-square" aria-hidden="true"
										style="color: rgb(72, 204, 184);"></i> Approved
									</label>
								</div>
								&nbsp;|
								
							</div>
							<input type="button" onclick="printReport()"
								value="Download Excel" class="btn btn-warning hidden" />
						</s:form>
					</div>

				</div>
			</div>
			<div class="">
				<table class="table table-responsive"
					style="width: 100%; text-transform: uppercase;">
					<thead>
						<tr>
							<!-- <th style="width: 3%;">ID</th> -->
							<th style="text-align: center; width: 9%;">Name</th>
							<th style="text-align: center; width: 13%;">Leave Type</th>
							<th style="text-align: center; width: 13%;">Reason Of Leave</th>

							<th style="text-align: center; width: 8%;">From Date</th>
							<th style="text-align: center; width: 10%;">To Date</th>
							<th style="text-align: center; width: 8%;">Days</th>
							<th style="text-align: center; width: 10%;">Requested Date</th>
							<th style="text-align: center; width: 10%;">Approved Date</th>
							<th style="text-align: center; width: 10%;">Approved By</th>
							<th style="text-align: center; width: 7%;">Status</th>
							<th style="text-align: center; width: 8%;">Action</th>
							<th style="text-align: center; width: 1%;">Print</th>

						</tr>
					</thead>
					<tbody>
						<s:iterator value="leaveList">
							<tr>
								<%-- <td><s:property value="id"/><s:hidden id="id" name="id"></s:hidden></td> --%>
								<td style="text-align: center;"><s:property value="name" /></td>
								<td style="text-align: center;"><s:property
										value="leave_type" /></td>
								<td style="text-align: center;"><s:property
										value="leave_reason" /></td>
								<td style="text-align: center;"><s:property
										value="fromdate" /></td>
								<td style="text-align: center;"><s:property value="todate" /></td>
								<td style="text-align: center;"><s:property value="days" /></td>
								<td style="text-align: center;"><s:property value="date" /></td>
								<td style="text-align: center;"><s:property
										value="approveddate" /></td>
								<td style="text-align: center;"><s:property value="userid" /></td>
								<td style="text-align: center;"><s:if test="status==0">
              					   			Requested
              					   		</s:if> <s:elseif test="status==1">
              					   			APPROVED</s:elseif> <s:elseif test="status==3">
              					   			APPROVED BY HR</s:elseif> <s:elseif test="status==2">
              					   			REJECTED
              					   		</s:elseif></td>
								<s:if test="status==0">
									<td style="text-align: center; background-color: orange;"><a
										href="#" style="color: #fff;"
										<%-- onclick="openPopup('proveleavePayrollDashBoard?id=<s:property value="id" />&status=0')">Requested</a></td> --%>
												onclick="proveleave(<s:property value="id"/>,0)">Request</a></td>

								</s:if>
								<s:elseif test="status==1">
									<td style="text-align: center; background-color: green;"><a
										href="#" style="color: #fff;"
										onclick="proveHrleave(<s:property value="id"/>,1)">Approved
											By HOD</a></td>
									<!-- (<s:property value="id"/>,1)">Approved</a></td> -->
								</s:elseif>

								<s:elseif test="status==3">
									<td style="text-align: center; background-color: green;"><a
										href="#" style="color: #fff;"(<s:property value="id"/>,3)">Approved
											By HR</a></td>
								</s:elseif>


								<s:else>
									<td style="text-align: center; background-color: red;"><a
										href="#" style="color: #fff;"
										onclick="proveleave(<s:property value="id"/>)">REJECTED</a></td>

								</s:else>
								<%--  <td
												style="text-align: center; background-color: rgba(138, 109, 59, 0.83);"><a
												href="#" style="color: #fff;"
										
												
												onclick="openPopup('proveleavePayrollDashBoard?id=<s:property value="id" />&status=0')">Requested</a></td>
												onclick="proveleave(<s:property value="id"/>,0)">Request</a></td> --%>
								<td style="text-align: center;"><a href="#"
									onclick="openPopup('leavePrintPayrollDashBoard?id=<s:property value="id" />&status=1&mainstatus=4&printbeforeapprove=0')"><i
										class="fa fa-print"></i></a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>

		<s:form action="PayrollDashBoard" name="paginationForm"
			id="paginationForm" theme="simple">

			<s:hidden name="fromdate"></s:hidden>
			<s:hidden name="todate"></s:hidden>
			<s:hidden name="searchtext"></s:hidden>
			<s:hidden name="filter_status"></s:hidden>
			<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
				<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
					Total:<label class="text-info"><s:property
							value="totalRecords" /> Records </label>
				</div>
				<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
			</div>
		</s:form>
	</div>

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

								<div class=""
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
								<s:hidden name="empleave_id" id="empleave_id"></s:hidden>
								<s:hidden name="leaveid" id="leaveid"></s:hidden>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
									style="padding: 0px; margin-bottom: 15px; text-transform: uppercase;">
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										<%-- <p style="margin: 0 0 2.5px;"><b>leave Number : </b><span id="leaveno"></span></p> --%>

										<p style="margin: 0 0 2.5px;">
											<b>Requsted User : </b><span id="userid"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Requsted Date : </b><span id="requestdate"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Name : </b><span id="requestname"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Contact : </b><span id="requestcontact"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Department : </b><span id="requestdepartment"></span>
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

								<div class=""
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

	<!-- Modal -->
	<div id="deletemodel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Are You Sure To Cancel?</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="parent_id">
					<textarea rows="3" class="form-control" id="delete_reason"
						placeholder="Cancel Reason" style="background-color: beige;"></textarea>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-danger"
						onclick="deleteIndent1()" data-dismiss="modal" value="Ok">
				</div>
			</div>

		</div>
	</div>


	<script src="common/chosen_v1.1.0/chosen.jquery.js"
		type="text/javascript"></script>
	<script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>

	<script>
    function printDiv2(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    //window.print();
    //document.body.innerHTML = oldPage;

    //Print Page
    setTimeout(function () {
        print_page();
    }, 2000);

    function print_page() {
        window.print();
    }

    //Restore orignal HTML
    setTimeout(function () {
        restore_page();
    }, 3000);

    function restore_page() {
        document.body.innerHTML = oldPage;
    }
}
	</script>




</body>
</html>