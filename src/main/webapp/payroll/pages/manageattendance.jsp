<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->


<%@ taglib prefix="s" uri="/struts-tags"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>YUVICARE</title>
<link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<SCRIPT type="text/javascript" src="payroll/js/allowances.js"></SCRIPT>
<SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
<SCRIPT type="text/javascript">
	function updateWeekSheet() {
		document.getElementById("monthid").value = document
				.getElementById("month").value;
		document.getElementById("attendanceform").submit();

	}

	function selectyear(val) {
		var newyearid = document.getElementById("newyearid").value = val;
		document.getElementById("selectmonth").submit();
	}
	function selectmonth(val) {
		var newmonthid = document.getElementById("newmonthid").value = val;
		document.getElementById("selectmonth").submit();
	}
	function dateSubmit() {

		var branchid = document.getElementById("branch").value;
		document.getElementById("branch_id").value = branchid;
		document.dateform.submit();
	}

	function setSortBranch(id) {

		document.getElementById("b1").value = id;
		document.branchform.submit();
	}
</SCRIPT>
<SCRIPT type="text/javascript">
	
</script>
<script type="text/javascript">
	$(document).ready(function() {

		$(".datepc").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1980',
			changeMonth : true,
			changeYear : true

		});
	});
</script>


<SCRIPT type="text/javascript">
	window.onload = function() {
		var year = document.getElementById("selectyr").value;
		document.getElementById("year").value = year;
		/* var fromdate=document.getElementById("fromdate").value;
		var count=Number(document.getElementById("count").value);
		if(fromdate!=""){
		  
		     for(var i=0;i<count;i++){
		      
		          var date=document.getElementById("dates"+i+"").value;
		          if(date!=null || date!=""){
		             
		                       document.getElementById("dates"+i+"").value=fromdate;
		          }
		          
		     }       
		               
		    
		} */

		calculateTotalHour();
	};
</SCRIPT>


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
}

.miheight {
	min-height: 650px;
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
	padding: 5px 7px 7px 7px !important;
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

.padtop {
	padding-top: 12px;
}
</style>
</head>





<body id="his" class="appWrapper sidebar-xs-forced">

	<div id="wrap" class="animsition">
		<div id="controls">

			<%
				int i = 0;
			%>
			<div class="container-fluid" style="background-color: #efefef;">
				<a>
					<h3 style="color: #339966; text-align: center;">
						<strong>Attendence Sheet</strong>
					</h3>
				</a>
			</div>

			<s:form action="Attendance" id="selectmonth">
				<div class="page page-sidebar-xs-layout">
					<div class="pageheader">
						<!-- <div class="col-lg-12 col-md-12 col-sm-12 marbot25">
                    <div class="col-lg-12 col-md-12 col-sm-12" style="background-color: rgb(242, 242, 242);">
                    <div class="col-lg-2 col-md-2 col-xs-2"> -->
						<form class="padtop">
							<%-- <div class="form-group">
						    <label for="exampleInputEmail1">Branch Name</label>
						    <s:select name="branch" id="branch" list="branchlist" listKey="branch_id" onchange="setSortBranch(this.value)" listValue="branch" cssClass="selectstyle form-control"></s:select>
						  </div> --%>

						</form>
					</div>
					<%--  <div class="col-lg-2 col-md-2 col-xs-2">
                    <form class="padtop">
						  <div class="form-group">
						    <label for="exampleInputEmail1">Week Status</label>
						    <p>Week No <s:property value="weekno"/></p>
						  </div>
						</form>
                    </div> --%>
					<s:hidden name="year" id="selectyr" />
					<div class="col-lg-4 col-md-4 col-xs-4">
						<form class="padtop" action="sortAttendance" name="dateform">
							<!-- <div class="form-group">
						    
						    <div class="row"> -->
							<%--  <div class=col-lg-6>
						    	<label for="exampleInputEmail1">From Date</label>		
						    	<s:textfield name="fromdate" id="fromdate" cssClass="form-control"  />
						    </div>
						    <div class=col-lg-6>
						    	<label for="exampleInputEmail1">To Date</label>		
						    	<s:textfield name="todate" id="todate" cssClass="form-control" />
						    </div> --%>
							<s:hidden name="branch_id" id="branch_id"></s:hidden>
							<!--  </div>
						  </div> -->
						</form>



						<%--  <div class="col-lg-2 col-md-2 col-xs-2">
                    <form class="padtop">
						  <div class="form-group">
						    <label for="exampleInputEmail1">Month</label>
						    <p><s:property value="month"/></p>
						  </div>
						</form>
                    </div>  --%>

						<div class="form-inline">
							<div class="form-group">
								<label for="exampleInputEmail1">Month</label>


								<s:select cssClass="form-control"
									list="#{'0':'Jan', '1':'Feb', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
									theme="simple" id="month" name="month" style="width: 43%" />
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Year</label> <select name="year"
									id="year" class="form-control">
									<%
										for (int k = 1980; k <= 2100; k++) {
									%>
									<option value="<%=k%>"><%=k%></option>
									<%
										}
									%>
								</select>
							</div>
							&emsp;
							<div class="form-group">
								<input type="submit" value="Go" class="btn btn-primary"
									onclick="dateSubmit()" style="background-color: #339966">

							</div>
							<!--  ="Attendance?month=" -->
						</div>


						<div class="form-group"></div>
					</div>
				</div>
			</s:form>
			<br>
			<table class="table my-table xlstable table-bordered"
				style="width: 100%;">
				<s:form id="attendanceform" theme="simple" action="updateAttendance">
					<s:hidden name="month" id="monthid"></s:hidden>
					<thead>
						<tr>
							<th>Sr. No.</th>
							<th>Emp. Name</th>
							<!--  <th>Reason</th> -->
							<th>Date</th>
							<th>Present Days</th>
							<th>Leave Days</th>
							<!--  <th>Total Salary</th>
                                    <th>Notes</th> -->

							<!-- <th>Tues</th>
                                    <th>Wed</th>
                                    <th>Thur</th>
                                    <th>Fri</th>
                                    <th>Sat</th>
                                    <th>Sun</th>
                                    <th>Total hrs</th>
                                    <th>Notes</th> -->
						</tr>
					</thead>
					<tbody>
						<%
							i = 0;
								int index = 0;
						%>
						<s:iterator value="attendanceList">
							<tr>
								<td><%=(++index)%></td>
								<td><s:property value="emp_name" /></td>
								<%-- <td>
                                        <select name="att1234565" name="attendance[<%=i%>].reason">
                                            <option selected="selected" value="Present">Present</option>
                                            <option value="Leave">Leave</option>
                                          <!--   <option value="Task">Task</option> -->
                                        </select>
                                    </td> --%>
								<td><input size="6" value="<s:property value='date'/>"
									maxlength="10" type="text" name="attendance[<%=i%>].date"
									id="dates<%=i%>" class="datepc" /></td>

								<td><input size="6" value="<s:property value='days'/>"
									maxlength="10" type="text" name="attendance[<%=i%>].days"
									id="days<%=i%>" onchange="calculateTotalHour()" /></td>
								<td><input size="6"
									value="<s:property value='salaryTotal'/>" maxlength="10"
									type="hidden" name="attendance[<%=i%>].salaryTotal"
									id="salaryTotal<%=i%>" /></td>
								<%--   <td><input size="8" value="<s:property value="notes"/>" maxlength="15" name="attendance[<%=i%>].notes" type="text" class=""></td> --%>
								<%--  <td>
                                        <input size="1" value="<s:property value='monday'/>" maxlength="5" type="text" name="attendance[<%=i%>].monday" id="monday<%=i%>" onkeyup="calculateTotalHour()" class="theight" />
                                    </td>
                                    <td>
                                        <input size="1" value="<s:property value="tuesday"/>" maxlength="5" name="attendance[<%=i%>].tuesday" id="tuesday<%=i%>" type="text" onkeyup="calculateTotalHour()" class="theight">
                                     
                                    </td>
                                    <td>
                                        <input size="1" value="<s:property value="wednesday"/>" maxlength="5" name="attendance[<%=i%>].wednesday" id="wednesday<%=i%>" type="text" onkeyup="calculateTotalHour()" class="theight">
                                    </td>
                                    <td>
                                        <input size="1" value="<s:property value="thursday"/>" maxlength="5" name="attendance[<%=i%>].thursday" id="thursday<%=i%>" type="text" onkeyup="calculateTotalHour()" class="theight">
                                   
                                    </td>
                                    <td><input size="1" value="<s:property value="friday"/>" maxlength="5" name="attendance[<%=i%>].friday" id="friday<%=i%>" type="text" onkeyup="calculateTotalHour()" class="theight"></td>
                                     <td><input size="1" value="<s:property value="saturday"/>" maxlength="5" name="attendance[<%=i%>].saturday" id="saturday<%=i%>" type="text" onkeyup="calculateTotalHour()" class="theight"></td>
                                      <td><input size="1" value="<s:property value="sunday"/>" maxlength="5" name="attendance[<%=i%>].sunday" id="sunday<%=i%>" onkeyup="calculateTotalHour()" type="text" class="theight"></td>
                                       <td><input size="3" value="<s:property value="total_hour"/>" maxlength="5" name="attendance[<%=i%>].total_hour" id="total_hour<%=i%>" type="text" class="theight"></td>
                                     <td><input size="8" value="<s:property value="notes"/>" maxlength="15" name="attendance[<%=i%>].notes" type="text" class=""></td><!--
                                    <td><a href="#"><i class="fa fa-edit"></i></a></td>

                                --></tr>
                                  <input type="hidden" name="attendance[<%=i%>].weekno" value="<s:property value="weekno"/>" /> --%>
							</tr>
							<input type="hidden" name="attendance[<%=i%>].basicsalary"
								value="<s:property value="basicsalary"/>" id="basicsalary<%=i%>" />
							<input type="hidden" name="attendance[<%=i%>].month"
								value="<s:property value="month"/>" />
							<input type="hidden" name="attendance[<%=i%>].branch_id"
								value="<s:property value="branch_id"/>" />
							<input type="hidden" name="attendance[<%=i%>].emp_id"
								value="<s:property value="emp_id"/>" />
							<%
								i++;
							%>
						</s:iterator>
					</tbody>
				</s:form>
			</table>
			<br />
			<div style="text-align: right;">
				<a href="#" type="button" class="btn btn-primary"
					style="background-color: #339966;" onclick="updateWeekSheet()">Update</a>
			</div>
			<input type="hidden" id="count" value="<%=i%>">
		</div>



	</div>

	<form action="Attendance" name="branchform">
		<input type="hidden" name="branch" id="b1">

	</form>

	</div>

	</section>
	<!--/ CONTENT -->






	</div>


	<!--Edit Modal-->
	<!-- Modal -->
	<div class="modal fade" id="adddeduc" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Deduction Details</h4>
				</div>
				<div class="modal-body row">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-5 control-label">Name</label>
								<div class="col-sm-7">
									<input type="email" class="form-control" id="inputEmail3">
									<small>(Enter PF or ESI or others)</small>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-5 control-label">Deduction
									Type</label>
								<div class="col-sm-7">
									<select name="otstatus" class="selectstyle form-control">
										<option value="Percentage">Percentage</option>
										<option value="Fixed">Fixed</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-5 control-label">Amount</label>
								<div class="col-sm-7">
									<input type="email" class="form-control" id="inputEmail3">
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Add/Edit</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
