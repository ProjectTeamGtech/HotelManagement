<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<style>
.lok{
padding: 10px;
margin-left: 15px;
background-color: #ebefef;

}
</style>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>

<script>


 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "DepartmentChargesWiseRevenue",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

	$("#fromDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
});

</script>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	
		<div class="print-visible hidden-md hidden-lg letterheadhgt hidden-print" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				<!--  <link href="common/css/printpreview.css" rel="stylesheet" /> -->
		<%@ include file="/accounts/pages/letterhead.jsp" %>
		</div>
		</div>
	</div>
	<div class="row details">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<h4>Department Charges Wise Revenue Report</h4>
	</div>		
	</div>
	
	<div class='row'>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print ">
	<s:form action='departmentChargesRevenuerptReport'>
	<div class='form-inline'>
	   <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 ">
			<div class="form-group" >
				<s:textfield readonly="true" name="FromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
		</div>	
	  <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 ">	
			<div class="form-group" >
				<s:textfield readonly="true" name="ToDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			</div>
	  <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<div class="form-group" >
				<s:select name="chargeType" theme="simple" id="chargeType" list="chargeTypeList" listKey="typeName" listValue="typeName"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="0" headerValue="Select Charge Type"  ></s:select>
			</div>
			</div>
			<div class="form-group" >
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
	</div>		
	</s:form>		
	</div>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<table class="my-table xlstable" style="width:100%">
	<h1><b><s:property value="Department" /></b></h1>
	<h1>Total Revenue:<b><s:property value="Totalrevenue" /></b></h1>
	<tr>
	<th>Sr. No</th>
	<th>Count</th>
	<th>Service</th>
	<th>Code</th>
	<th>Amount</th>
	<th>Revenue</th>
	
	</tr>
	<%int i=1; %>
	<s:iterator value='DeptWiseCountList'>
	<tr>
	<td><%=i++ %></td>
	<td><s:property value='Count'/></td>
	<td><s:property value='AppointmentType'/></td>
	<td><s:property value='Code'/></td>
	<td><s:property value='Charge'/></td>
	<td><s:property value='Revenue'/></td>
	
	</tr>
	</s:iterator>
	</table>
	</div>	
	</div>
	
</div>
