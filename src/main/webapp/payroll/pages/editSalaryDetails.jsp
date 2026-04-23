<!doctype html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>YUVICARE</title>

<SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>

<SCRIPT type="text/javascript">
    
       function updatesalary(){
       
            document.salaryform.action="updatePayrollincrement";
            document.salaryform.submit();
       }
    </SCRIPT>


<style>
.padright {
	padding-left: 40px;
	
}

.table.table {
	color: RGBA(100, 100, 100, 0.85);
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
	border-right: 2px dashed rgb(192, 192, 192);
	border-style:groove;
}

.buildinglogo {
	width: 60%;
	margin-top: 30px;
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

.table>tbody>tr>td,.table>tbody>tr>th,.table>tfoot>tr>td,.table>tfoot>tr>th,.table>thead>tr>td,.table>thead>tr>th
	{
	padding: 5px 7px 7px 7px !important;
}

.tanew {
	/* width: 20%; */
	width: 40%;
}

.sidebar-xs #header .branding>a {
	background-position: 6px 10px;
	width: 100% !important;
	font-size: 21px;
	padding: 0px 1px 2px 15px;
	text-align: center;
	color: pink;
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

.panel-default>.panel-heading {
	color: #fff !important;
	background-color: #f5f5f5;
	border-color: #ddd;
	background: #6699CC !important;
}
</style>

<script type="text/javascript">
window.onload=function(){
	setSalaryData();
}
</script>

</head>





<body id="his" class="appWrapper sidebar-xs-forced"  onload="checkForFixed()" style="background-color: pink">

<div class="container-fluid" style="background-color: #339966;"><a>
<h3 style="color: white;text-align: center;"><strong>Salary</strong></h3>
</a></div>

<!-- <div class="page page-sidebar-xs-layout"> -->
<div>
<div class="pageheader">
<!-- <div class="col-lg-12 col-md-12 col-sm-12 marbot25"> -->
<div class="panel panel-default">
<div class="panel-body">
<table width="100%">
	<tbody>
		<tr>
		 <!-- cellspacing="0" cellpadding="0" valign="top" align="right" class="table" -->
			<td width="100%"  align="center">
			<div>
            <s:form name="salaryform" theme="simple">
            `
            <s:hidden name="id"></s:hidden>
            <s:hidden name="emp_id"></s:hidden>
            <div>
            <label>Salary Type : -</label> &emsp;&emsp;&emsp;
            <input type="radio" name='fixedemp'  value="fixedemp" id="fixedemp">Fixed &emsp;&emsp;&emsp;
            <input type="radio" name='fixedemp'  value="unfixed" id="ii">Unfixed
            
            </div>
            
            
			<table width="90%" border="0" align="center" valign="top" class="table">
			
					<tbody style="text-align: right;">
						<%-- <tr>
							<td class="borright tanew"><b>Branch Name :</b></td>
							<td><s:property value="branch_id" /></td>
						</tr> --%>
						<tr>
							<td class="borright tanew">Name :</td>
							<td><strong><s:property value="emp_name" /></strong></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">Date :</td>
							<td><s:textfield name="incre_date" /></td>
						</tr>
						
						<tr>
							<td class="borright tanew">Fixed Salary :</td>
							<td><s:textfield name="fixedsalary" onchange="checkForFixed()" id="fixedsalary"/></td>
							
							<td><s:textfield name="perfixedsalary"  placeholder="%" onchange="checkForFixed()" id="perfixedsalary"/></td>
						</tr>
						
						
						<tr>
							<td class="borright tanew">Basic :</td>
							<td><s:textfield name="basic" onkeyup="checkForFixed()" id="basic"/></td>
							
							<td><s:textfield name="perbasic"  placeholder="%" onkeyup="setSalaryData()" id="perbasic"/></td>
						</tr>
						<tr >
							<td class="borright tanew">HRA ( fixed)</td>
							<td><s:textfield name="hra" id="hra" onkeyup="checkForFixed()"/></td>
						</tr>
						
							<tr>
							<td class="borright tanew">Conveyance</td>
							<td><s:textfield name="conveyance" id="conveyance" onkeyup="checkForFixed()"/></td>
						</tr>
							<tr>
							<td class="borright tanew">Washing</td>
							<td><s:textfield name="washing" id="washing" onkeyup="checkForFixed()"/></td>
						</tr>
							<tr>
							<td class="borright tanew">Per Dev. Allow</td>
							<td><s:textfield name="perdevallow" id="perdevallow" onkeyup="checkForFixed()"/></td>
						</tr>
							<tr>
							<td class="borright tanew">Vehicle Pass</td>
							<td><s:textfield name="vehiclepass" id="vehiclepass" onkeyup="checkForFixed()"/></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">Medical Allowance</td>
							<td><s:textfield name="medical_allowance" id="medical_allowance" onkeyup="checkForFixed()" /></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">Allowances</td>
							<td><s:textfield name="allowances" id="allowances" onkeyup="checkForFixed()"/></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">TRANSPORT ALLOWANCE ( fixed)</td>
							<td><s:textfield name="transport_allowance" id="transport_allowance" onkeyup="checkForFixed()"/></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">Other Income</td>
							<td><s:textfield name="otherincome" id="otherincome" onkeyup="checkForFixed()" /></td>
							</tr>
							<tr>
							<td class="borright tanew">DA on TA ( fixed)</td>
							<td><s:textfield name="da_on_ta" id="da_on_ta" onkeyup="checkForFixed()"/></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">SPECIAL PAY ( fixed)</td>
							<td><s:textfield name="special_pay" id="special_pay" onkeyup="checkForFixed()" /></td>
						</tr>
						<tr>
							<td class="borright tanew">PERSONAL PAY ( fixed)</td>
							<td><s:textfield name="personal_pay" id="personal_pay" onkeyup="checkForFixed()"/></td>
						</tr>
						
						
						<tr class="alt_tr">
							<td class="borright tanew">DA ( fixed)</td>
							<td><s:textfield name="da" id="da" onkeyup="checkForFixed()" /></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">NPA ( fixed)</td>
							<td><s:textfield name="npa" id="npa" onkeyup="checkForFixed()" /></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">Employee's PF (12.00%)</td>
							<td><s:textfield name="emp_pf" id="emp_pf" onkeyup="checkForFixed()"/></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">Employee's ESI (2.00%)</td>
							<td><s:textfield name="emp_esi" id="emp_esi" onkeyup="checkForFixed()"/></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">Company's PF(12.00%)</td>
							<td><s:textfield name="comp_pf" id="comp_pf" onkeyup="checkForFixed()" /></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">Company's ESI(5.00%)</td>
							<td><s:textfield name="comp_esi" id="comp_esi" onkeyup="checkForFixed()" /></td>
						</tr>
						<tr class="alt_tr">
							<td class="borright tanew">Income Tax</td>
							<td><s:textfield name="taxable" id="taxable" onkeyup="checkForFixed()" /></td>
						</tr>
						
						
							<%-- <tr>
							<td class="borright tanew">DA on TA ( fixed)</td>
							<td><s:textfield name="da_on_ta" id="da_on_ta" onkeyup="setSalaryData()"/></td>
						</tr> --%>

                        
					</tbody>
					
			
			</table>
            </s:form>

			</div>
			</td>

		</tr>
	</tbody>
</table>

<div style="text-align: center;"><input type="button" class="btn btn-primary" value="Update" style="background-color: #339966"
	onclick="updatesalary()" /></div>
</div>
</div>
<!-- </div> -->
</div>

</div>

</section>
<!--/ CONTENT -->


</body>
</html>
