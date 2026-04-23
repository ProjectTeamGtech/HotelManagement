<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
    
<html lang="en">
    <head>
        <meta charset="utf-8">
        <script type="text/javascript" src="common/js/pagination.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
       <%--  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> --%>
<script src="https://rawgit.com/unconditional/jquery-table2excel/master/src/jquery.table2excel.js"></script>
<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<link rel="stylesheet" type="text/css" href="/minipaint/styles.print.css" media="print">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.14.2/extensions/print/bootstrap-table-print.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.14.2/extensions/print/bootstrap-table-print.min.js"></script>
       
        <title>Employees - Account Report</title>
    </head>
    <script type="text/javascript">
window.onload = function() {
	var year = document.getElementById("selectyr").value;
	document.getElementById("year").value = year;
	document.getElementById("rept").className="active";	
		document.getElementById("report").className="subdrop";	
		document.getElementById("repta").className="active";	
}
</script>
<script type="text/javascript">
function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Salary Report",
					filename: "Account Report",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}
function exportF(elem) {
	  var table = document.getElementById("example");
	  var html = table.outerHTML;
	  var url = 'data:application/vnd.ms-excel,' + escape(html); // Set your html table into url 
	  elem.setAttribute("href", url);
	  elem.setAttribute("download", "EmployeeAccountRpt.xls"); // Choose the file name
	  return false;
	}
</script>
 
<style>
.tablebord { 
    border-collapse: collapse; 
}
table, th, td {
  border: 1px solid black !important;
}

 @media print
{


.zoomprint{
zoom:50%;
}
}
</style>
    <body>
					
				<!-- Page Content -->
                <div class="content container-fluid zoomprint" id="page_printer" >
				<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
					<!-- Page Title -->
					<div class="row">
						<div class="col">
							<h4 class="page-title">Employee Account Report</h4>
						</div>
						
					</div>
					<!-- /Page Title -->
					
					<!-- Search Filter -->

		<form action="accountreportReportPayroll">
			<s:hidden name="year" id="selectyr" />
			<div class="form-inline">

				<div class="form-group form-focus">
					<input type="text" class="form-control floating d-print-none" name="searchText">
					<label class="focus-label d-print-none">Employee ID</label>
				</div>&ensp;

				<div class="form-group form-focus">
					<input type="text" class="form-control floating d-print-none"
						name="empnamesearch"> <label class="focus-label d-print-none">Employee
						Name</label>
				</div>&ensp;
				<div class="form-group form-focus select-focus d-print-none">
					<s:select name="department" id="department" list="departmentlist"
						listKey="dept_id" listValue="department"
						cssClass="select floating"
						headerValue="Select Department" headerKey="0">
					</s:select>
				</div>&ensp;

				<div class="form-group form-focus select-focus d-print-none" style="width: 13%">
					<s:select cssClass="select floating"
						list="#{'0':'Jan', '1':'Feb', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
						theme="simple" id="month" name="month" style="width: 43%" />
					<label class="focus-label">Select Month</label>
				</div>&ensp;
				
				<div class="form-group " style="width: 13%">
					<select name="year" id="year" class="mdb-select md-form form-control d-print-none " style="width: 65%;height: 48px;">
						<%
							for (int k = 1980; k <= 2020; k++) {
						%>
						<option value="<%=k%>"><%=k%></option>
						<%
							}
						%>
					</select> 
			<!-- 		<label class="focus-label">Select Year</label>  -->
				</div> &ensp;
				
				<div class="form-group">
					<input type="submit" class="btn btn-success btn-block d-print-none"
						value="Search">
				</div>&ensp;&ensp;&ensp;
				 <a href="#"  onclick="exportF(this)" title="Download CSV file" style="line-height: 26px;" class="d-print-none"><i class="fa fa-download"></i></a> &ensp;&ensp;&ensp;
					<a type="button" title="Print" onclick="printDiv('test')" class="d-print-none"><i class="fa fa-print"></i></a>
			<!-- <a id="downloadLink" onclick="exportF(this)">Export to excel</a> -->
			</div>

		</form>
		<br>

		<!-- /Search Filter -->
					<%int i=1; %>
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive zoom23" id="test">
							<h1 class="d-none d-print-block" style="text-align: center;"><span class="text-uppercase"><b>Employee Salary Report (<s:property value="selectedmonth"/>-<s:property value="year"/>)</b> &nbsp;  &nbsp;</span></h1>
							
								<table class="table table-striped custom-table datatable xlstable tablebord zoom23" id="example">
									<thead>
										<tr style="border-bottom: 1pt solid black !important;">
											<th >Sr No.</th>
											<th >Name</th>
											<th >Employee ID</th>
											<th >PF No.</th>
											<th >ESIC No.</th>
											<!-- <th >Department</th>
											<th >Role</th>
											<th>Basic Salary</th>
											<th>HRA</th>
											<th>DA</th>
											<th>Conveyance</th>
											<th>Other Allowances</th>
											<th>Medical Allowances</th>
											<th>Over Time</th> -->
											<th>Gross Salary</th>
											<th>TDS</th>
											<th>PF</th>
											<th>ESI</th>
											<!-- <th>Leave</th> -->
											<th>Prof. Tax</th>
											<th>Net Salary</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="salaryList">
									<s:hidden name="emp_id"/>
										<tr>
										<td><%=i++ %></td>
										<td>
											<s:property value="emp_name"/>
												<%-- <h2 class="table-avatar">
													<a href="payrollprofilePayrollEmployee?empid=<s:property value="emp_id"/>" class="avatar"><img alt="" src="assets/img/profiles/avatar-02.jpg"></a>
													<a href="payrollprofilePayrollEmployee?empid=<s:property value="emp_id"/>"><s:property value="name"/><span></span></a>
												</h2> --%>
											</td>
											<td><s:property value="empcode"/></td>
											<td><s:property value="pfno"/></td>
											<td><s:property value="esicno"/></td>
											<%-- <td><s:property value="department"/></td>
											<td><s:property value="designation"/></td>
											<td><s:property value="basic"/></td>
											<td><s:property value="hra"/></td>
											<td><s:property value="da"/></td>
											<td><s:property value="conveyance"/></td>
											<td><s:property value="perdevallow"/></td>
											<td><s:property value="medical_allowance"/></td>
											<td><s:property value="ot"/></td> --%>
											<td><s:property value="gross_pay"/></td>
											<td><s:property value="tds"/></td>
											<td><s:property value="emp_pf"/></td>
											<td><s:property value="emp_esi"/></td>
											<%-- <td><s:property value="leave"/></td> --%>
											<td><s:property value="prefessional_tax"/></td>
											<td><s:property value="netpay"/></td>
										</tr>
										</s:iterator>
										<tr>
										
											
											<td></td>
											<td></td>
											<td>Total</td>
											<td></td> 
											<td></td>
											<%-- <td><s:property value="totbasic"/></td> --%>
											<%-- <td><s:property value="tothra"/></td>
											<td><s:property value="totda"/></td>
											<td><s:property value="totconvey"/></td>
											<td><s:property value="totperdev"/></td>
											<td><s:property value="totmed"/></td>
											<td><s:property value="totot"/></td> --%>
											<td><s:property value="totgrosspay"/></td>
											<td><s:property value="tottds"/></td>
											<td><s:property value="totpf"/></td>
											<td><s:property value="totesi"/></td>
											<%-- <td><s:property value="totleave"/></td> --%>
											<td><s:property value="totprof"/></td>
											<td><s:property value="totnetpay"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
                </div>
				<!-- /Page Content -->
				
				
				 <s:form action="accountreportReportPayroll" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
							     <s:hidden name="year"></s:hidden>
							     <s:hidden name="month"></s:hidden>
							     <s:hidden name="department"></s:hidden>
							       <s:hidden name="empnamesearch"></s:hidden>
							      <s:hidden name="searchText"></s:hidden>
								<div class="row" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<s:include value="/common/pages/pagination.jsp"></s:include>
								</div>
							</s:form>
				<script>
     $(document).ready(function() {
    var table = $('#example').DataTable( {
        lengthChange: false,
        buttons: [ 'excel', 'colvis' ]
    } );
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
} );
    </script>
		
		<%-- <!-- Sidebar Overlay -->
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
		<script src="payrollnew/assets/js/moment.min.js"></script>
		<script src="payrollnew/assets/js/bootstrap-datetimepicker.min.js"></script>
		
		<!-- Datatable JS -->
		<script src="payrollnew/assets/js/jquery.dataTables.min.js"></script>
		<script src="payrollnew/assets/js/dataTables.bootstrap4.min.js"></script> 
		--%>
		<!-- Custom JS -->
	<%-- <script src="payrollnew/assets/js/app.js"></script>  --%>
		<%--  <s:form action="employeesalaryrptReportPayroll" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
							     <s:hidden name="searchText"></s:hidden>
							     <s:hidden name="empnamesearch"></s:hidden>
							      <s:hidden name="department"></s:hidden>
							       <s:hidden name="month"></s:hidden>
							        <s:hidden name="year"></s:hidden>
							      
								<div class="row" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<s:include value="/common/pages/pagination.jsp"></s:include>
								</div>
							</s:form> --%>
    </body>
    <script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	
	</script>
<%-- <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script> --%>
	<%--  <script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.print.js"></script>  --%>
</html>
