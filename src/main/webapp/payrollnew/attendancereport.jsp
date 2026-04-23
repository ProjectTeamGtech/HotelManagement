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
       
        <title>Employees - Attendance Report</title>
    </head>
    <script type="text/javascript">
window.onload = function() {
	var year = document.getElementById("selectyr").value;
	document.getElementById("year").value = year;
	document.getElementById("reptm").className="active";	
	document.getElementById("report").className="subdrop";	
}
</script>
<script type="text/javascript">
function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Salary Report",
					filename: "AttendanceReport",
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
	  elem.setAttribute("download", "MonthwiseAttendance.xls"); // Choose the file name
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
							<h4 class="page-title">Month Wise Attendance Report</h4>
						</div>
						
					</div>
					<!-- /Page Title -->
					
					<!-- Search Filter -->
<%int dayinmonth=(Integer) session.getAttribute("monhday"); %>
		<form action="attendancemonthReportPayroll">
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

				<div class="form-group form-focus select-focus d-print-none" style="width: 13%">
					<s:select cssClass="select floating"
						list="#{'0':'Jan', '1':'Feb', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
						theme="simple" id="month" name="month" style="width: 43%" />
					<label class="focus-label">Select Month</label>
				</div>&ensp;
				
				<div class="form-group " style="width: 13%">
					<select name="year" id="year" class="mdb-select md-form form-control d-print-none " style="width: 65%;height: 48px;">
						<%
							for (int k = 1980; k <= 2100; k++) {
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
					
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive zoom23" id="test">
							<h1 class="d-none d-print-block" style="text-align: center;"><span class="text-uppercase"><b>Month Wise Attendance Report</b> &nbsp;  &nbsp;</span></h1>
							
								<table class="table table-striped custom-table datatable xlstable tablebord zoom23" id="example">
									<thead>
										<tr style="border-bottom: 1pt solid black !important;">
											<th >Name</th>
											<th >Employee ID</th>
											<%for(int i=1;i<=dayinmonth;i++){ %>
											<th><%=i %></th>
											<%}%>
											
										</tr>
									</thead>
									<tbody>
									<s:iterator value="attendancemaster">
										<tr>
											<td>
											<s:property value="emp_name"/>
											</td>
											<td>
											<s:property value="emp_id"/>
											</td>
											<s:iterator value="subattendancelist">
											<s:if test="status==1">
											<td><i class="fa fa-times "></i></td>
											</s:if>
											<s:elseif test="status==2">
											<td><i class="fa fa-check text-success"></i></td>
											</s:elseif>
											<s:else>
											<td></td>
											</s:else>
											</s:iterator>
										</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</div>
                </div>
				<!-- /Page Content -->
				
				
				 <s:form action="attendancemonthReportPayroll" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
							     <s:hidden name="year"></s:hidden>
							     <s:hidden name="month"></s:hidden>
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
</html>
