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

<SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
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
	padding: 0px 7px 0px 7px !important;
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

p {
	margin: 7px 0 0px;
}

.panel-default>.panel-heading {
	color: #fff !important;
	background-color: #f5f5f5;
	border-color: #ddd;
	background: #6699CC !important;
}
</style>
<SCRIPT type="text/javascript">
  
     window.onload=function(){
    	 var month=document.getElementById("selectedmonth").value;
    	 changedays(month);
     };
    
  </SCRIPT>

</head>


<body id="his" class="appWrapper sidebar-xs-forced">


	<div class="container-fluid" style="text-align: center;">
		<a>
			<h3>
				<strong>Monthly Salary</strong>
			</h3>
		</a>
		<h4>
		<strong><s:property value="Monthandyear"/></strong>
	</h4>
	</div>
	
	<div class="page page-sidebar-xs-layout">

		<div class="pageheader">
			<s:form cssClass="form-horizontal" theme="simple"
				action="savemonthsalaryPayrollMaster" method="post" id="savemonthsalaryfrm">
				<div class="col-lg-12 col-md-12 col-sm-12 marbot25">
					<div class="panel panel-default">
						<div class="panel-heading">Month Salary Details</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-xs-6">
										<!-- <div class="form-group" style="width: 17%;margin-left: 16px;">
											<label for="exampleInputEmail1">Month</label>
 -->
											<s:hidden name="salaryId" />
											<s:hidden name="status" id="status"/>
											<s:hidden name="days" />
											<s:hidden name="netpay" />
											<s:hidden name="year" id="selectyear"/>
											<s:hidden name="month" id="selectedmonth" />
											<%-- <s:select cssClass="form-control"
												list="#{'0':'Jan', '1':'Feb', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
												name="month" theme="simple" id="month"
												onchange="changedays(this.value)" /> --%>

										<!-- </div> -->
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-5 control-label"><b>Branch
												Name :</b></label>
										<div class="col-sm-7">
											<p>
												<s:property value="branch_id" />
											</p>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-5 control-label">Name
											:</label>
										<div class="col-sm-7">
											<p>
												<s:property value="emp_name" />
											</p>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-5 control-label">Total
											Days :</label>
										<div class="col-sm-7">
											<p id="totaldays">
												<s:property value="totaldays" />
											</p>
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-5 control-label">Worked
											Days :</label>
										<div class="col-sm-7">
											<p id="workeddays">
												<s:property value="days" />
											</p>
										</div>
									</div>
									<%-- <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Total Sundays :</label>
                                                <div class="col-sm-7">
                                                    <p><s:property value="no_sundays"/></p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Unpaid Leaves :</label>
                                                <div class="col-sm-7">
                                                    <p><s:property value="unpaidleaves"/></p>
                                                </div>
                                            </div> --%>
									<%--  <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Paid Leaves :</label>
                                                <div class="col-sm-7">
                                                   <p><s:property value="paidleaves"/></p> 
                                                </div>
                                            </div> --%>
									<!--   <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Permissions :</label>
                                                <div class="col-sm-7">
                                                    <p>0</p>
                                                </div>
                                            </div> -->
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-5 control-label">OT
											hrs :</label>
										<div class="col-sm-7">
											<p>0</p>
										</div>
									</div>

									<div class="form-group">
										<label for="inputEmail3" class="col-sm-5 control-label">Basic
											:</label>
										<div class="col-sm-7">
											<p>
												<s:textfield name="salaryTotal" id="salaryTotal" />
											</p>
										</div>
									</div>
									<%--     <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Current Basic :</label>
                                                <div class="col-sm-7">
                                                    <p><s:textfield name="basic" id="basic" /></p>
                                                </div>
                                            </div> --%>


									<%--    <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Salary In Bank :</label>
                                                <div class="col-sm-7">
                                                   <p><s:textfield name="salary_bank" id="salary_bank"/></p> 
                                                </div>
                                            </div> --%>

								</div>
								<div class="col-lg-6 col-md-6 col-xs-6">
									<form class="form-horizontal">
										<%--  <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Salary Month :</label>
                                                <div class="col-sm-7">
                                                    <p><s:property value="monthyear"/></p>
                                                </div>
                                            </div> --%>

										




										<%--   <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Holidays :</label>
                                                <div class="col-sm-7">
                                                    <p><s:property value="holidays"/></p>
                                                </div>
                                            </div> --%>
										<%--  <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">IT Month :</label>
                                                <div class="col-sm-7">
                                                    <p><s:property value="currentMonth"/></p>
                                                </div>
                                            </div> --%>

										<%--  <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Leaves :</label>
                                                <div class="col-sm-7">
                                                    <p><s:property value="total_leaves"/></p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">OT Salary :</label>
                                                <div class="col-sm-7">
                                                    <p>0</p>
                                                </div>
                                            </div> --%>

										<div class="form-group">
											<label for="inputEmail3" class="col-sm-5 control-label">Allowances
												:</label>
											<div class="col-sm-7">
												<p>
													<s:textfield name="allowances" id="allowances" />
													<a href="" data-toggle="modal" data-target="#Allowances"><i
														class="fa fa-edit"></i></a>
												</p>
											</div>
										</div>
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-5 control-label">Deductions
												:</label>
											<div class="col-sm-7">
												<p>
													<s:textfield name="deductions" id="deductions" />
													<a href="" data-toggle="modal" data-target="#Deduction"><i
														class="fa fa-edit"></i></a>
												</p>
											</div>
										</div>
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-5 control-label">Other
												Income :</label>
											<div class="col-sm-7">
												<p>
													<s:property value="otherincome" />
												</p>
											</div>
										</div>
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-5 control-label"><b>NetPay
													:</b></label>
											<div class="col-sm-7">
												<p id="netpay">
													<s:property value="netpay" />
												</p>
											</div>
										</div>
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-5 control-label"><b>Gross
													Salary :</b></label>
											<div class="col-sm-7">
												<p id="gross_pay">
													<s:property value="gross_pay" />
												</p>
											</div>
										</div>
										<%-- <div class="form-group">
                                                <label for="inputEmail3" class="col-sm-5 control-label">Encashable Income :</label>
                                                <div class="col-sm-7">
                                                    <p><s:textfield name="encashable" id="encashable" /></p>
                                                </div>
                                            </div> --%>


									</form>
								</div>

								<s:hidden id="emp_id" name="emp_id"></s:hidden>
								<div class="col-lg-12 col-md-12">
									<div
										style="text-align: right; ">
										<!-- <button type="submit" class="btn btn-primary">Finalize Salary</button> -->
										<%-- <input type="button" class="" value="Update" onclick="updatemonthsalary(<s:property value='emp_id'/>)"> --%>
										<a href="#" onclick="updateMonth()" class="btn btn-primary">Update</a>
									</div>
								</div>

								<!-- <div class="col-lg-12 col-md-12">
									<div style="text-align: right;">
										<button type="submit" style="padding-left: 20px;"
											class="btn btn-primary">Final</button>
											<td style="padding-left: 20px;"class="btn btn-primary"><a href="savefinalsalaryPayrollMaster">Final</a></td>
									</div> -->
								</div>
</div>
</div>
</div>

							</div>
			</s:form>
		</div>
	</div>
	

	<!--/ CONTENT -->








	<!--Edit Modal-->
	<!-- Allowances Modal -->
	<div class="modal fade" id="Allowances" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Allowances</h4>
				</div>
				<div class="modal-body row">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<s:form theme="simple" name="editfp">
							<table width="100%">
								<tbody>
									<tr>
										<td width="60%" valign="top" align="center">
											<div>

												<table width="90%" border="0" cellspacing="0"
													cellpadding="0" valign="top" align="right" class="table">
													<tbody>
														<tr>
															<td class="borright">Medical Allowance (fixed)</td>
															<td><s:textfield name="medical_allowance"
																	id="medical_allowance" /></td>
														</tr>
														<tr>
															<td class="borright">DA on TA</td>
															<td><s:textfield name="da_on_ta" id="da_on_ta" /></td>
														</tr>
														<tr>
															<td class="borright">SPECIAL PAY</td>
															<td><s:textfield name="special_pay" id="special_pay" /></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">PERSONAL PAY</td>
															<td><s:textfield name="personal_pay"
																	id="personal_pay" /></td>
														</tr>
														<tr>
															<td class="borright">TRANSPORT ALLOWANCE</td>
															<td><s:textfield name="transport_allowance"
																	id="transport_allowance" /></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">HRA (fixed)</td>
															<td><s:textfield name="hra" id="hra" /></td>
														</tr>
														<tr>
															<td class="borright">DA (fixed)</td>
															<td><s:textfield name="da" id="da" /></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">NPA ( fixed)</td>
															<td><s:textfield name="npa" id="npa" /></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Conveyance</td>
															<td><s:textfield name="conveyance" id="conveyance" /></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Washing</td>
															<td><s:textfield name="washing" id="washing" /></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Per. Dev Allow</td>
															<td><s:textfield name="perdevallow" id="perdevallow" /></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Vehicle Pass</td>
															<td><s:textfield name="vehiclepass" id="vehiclepass" /></td>
														</tr>
													</tbody>
												</table>

											</div>
										</td>

									</tr>
								</tbody>
							</table>
						</s:form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="modifyAllowances()"
						class="btn btn-primary">Ok</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Deduction Modal -->
	<div class="modal fade" id="Deduction" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Deduction</h4>
				</div>
				<div class="modal-body row">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<table width="100%">
							<tbody>
								<tr>
									<td width="60%" valign="top" align="center">
										<div>
											<s:form theme="simple" name="deductionform">
												<table width="90%" border="0" cellspacing="0"
													cellpadding="0" valign="top" align="right" class="table">
													<tbody>
														<tr>
															<td class="borright">Employee's PF (12.00%)</td>
															<td><s:textfield name="emp_pf" id="emp_pf" /></td>
														</tr>
														<tr>
															<td class="borright">Employee's ESI (2.00%)</td>
															<td><s:textfield name="emp_esi" id="emp_esi" /></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Other Deduction</td>
															<td><s:textfield name="other_deduction"
																	id="other_deduction" /></td>
														</tr>
														<tr>
															<td class="borright">leave</td>
															<td><s:textfield name="leave" id="leave" /></td>
														</tr>
														<tr class="alt_tr">
															<td class="borright">Professional Tax</td>
															<td><s:textfield name="prefessional_tax"
																	id="prefessional_tax" /></td>
														</tr>
														<tr>
															<td class="borright">TDS</td>
															<td><s:textfield name="tds" id="tds" /></td>
														</tr>

														<s:hidden name="loan" id="loan"></s:hidden>
													</tbody>
												</table>
											</s:form>
										</div>
									</td>

								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="modifyDeductions()"
						class="btn btn-primary">Ok</button>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
function updateMonth() {
	document.getElementById("status").value=1;
	document.getElementById('savemonthsalaryfrm').submit();
}


</script>





</body>
</html>
