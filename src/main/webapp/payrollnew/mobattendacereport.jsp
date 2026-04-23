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
<script src="https://rawgit.com/unconditional/jquery-table2excel/master/src/jquery.table2excel.js"></script>
<script type="text/javascript" src="accounts/js/printpreview.js"></script>
<link rel="stylesheet" type="text/css" href="/minipaint/styles.print.css" media="print">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.14.2/extensions/print/bootstrap-table-print.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.14.2/extensions/print/bootstrap-table-print.min.js"></script>
      <SCRIPT type="text/javascript" src="payroll/js/payrollvalidatation.js"></SCRIPT>  
        <title>Employees - Mobile Attendance Report</title>
    </head>
    <script type="text/javascript">
window.onload = function() {
	var year = document.getElementById("selectyr").value;
	document.getElementById("year").value = year;
	document.getElementById("rept").className="active";	
		document.getElementById("report").className="subdrop";	
}
</script>
<script type="text/javascript">
function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Salary Report",
					filename: "SalaryReport",
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
	  elem.setAttribute("download", "EmployeeSalRpt.xls"); // Choose the file name
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
					<div class="print-visible hidden-md hidden-lg letterheadhgt" >
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
									<link href="common/css/printpreview.css" rel="stylesheet"/>
									<%@ include file="/accounts/pages/letterhead.jsp" %>
								</div>
						</div>
					</div>
			</div>
					<!-- Page Title -->
					<div class="row">
						<div class="col">
							<h4 class="page-title">Employee Mobile Attendance Report</h4>
						</div>
						
					</div>
					<!-- /Page Title -->
					
					<!-- Search Filter -->

		<form action="mobileattendancereportReportPayroll">
			<s:hidden name="year" id="selectyr" />
			<div class="form-inline">

				<div class="form-group form-focus">
					<input type="text" class="form-control floating d-print-none" name="searchText">
					<label class="focus-label d-print-none">Employee ID</label>
				</div>&ensp;

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
					
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive zoom23" id="test">
							<h1 class="d-none d-print-block" style="text-align: center;"><span class="text-uppercase"><b>Employee Salary Report (<s:property value="selectedmonth"/>-<s:property value="year"/>)</b> &nbsp;  &nbsp;</span></h1>
							
								<table class="table table-striped custom-table datatable xlstable tablebord zoom23" id="example">
									<thead>
										<tr style="border-bottom: 1pt solid black !important;">
											<th >Name</th>
											<th >Employee ID</th>
											<th >In Date/Time</th>
											<th >Out Date/Time</th>
											<th >Total Time</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="mobileattendancemaster">
										<tr>
											<td><s:property value="emp_name"/><i class="fa fa-arrow-down d-print-none"  onclick="showhidechargedetails(<s:property value="id"/>)"></i></td>
											<td><s:property value="emp_id"/></td>
											<td><s:property value="indatetime"/></td>
											<td><s:property value="outdatetime"/></td>
											<td><s:property value="totalhour"/></td>
										
										</tr>
										<tr id="mobatte<s:property value="id"/>" style="display: none; ">
										<td colspan="4">
															<table class="table  table-bordered  ">
																<thead>
																	<tr class="bg-info">
																		<th>In Date/time</th>
																		<th>Out Date/time</th>
																		<th class="text-right">In long/Lat</th>
																		<th class="text-right">In Remark</th>
																		<th class="text-right">Out long/Lat</th>
																		<th class="text-right">Out Remark</th>
																		<th >Total Time</th>
																	</tr>
																	</thead>
																	<tbody>
																	<s:iterator value="allcheckinlist">
																	<tr>
																	<td><s:property value="indatetime"/></td>
																	<td><s:property value="outdatetime"/></td>
																	<td><a href="#" onclick="openBlankPopup('https://www.google.com/maps/place/<s:property value="inlonglat"/>')"><s:property value="inlonglat"/></a></td>
																	<td><s:property value="Remarkintime"/></td>
																	<td><a href="#" onclick="openBlankPopup('https://www.google.com/maps/place/<s:property value="outlonglat"/>')"><s:property value="outlonglat"/></a></td>
																	<td><s:property value="Remarkouttime"/></td>
																	<td><s:property value="totalhour"/></td>
																	
																	
																</tr>
																</s:iterator>
																</tbody>
																</table>
																</td>
																</tr>
												
												</s:iterator>
																</tbody>
																<tfoot>
																</tfoot>
								</table>
							</div>
						</div>
					</div>
                </div>
				<!-- /Page Content -->
				
				
				 <s:form action="employeesalaryrptReportPayroll" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
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
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script> 
</html>
