<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
	<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>

 .table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
    background-color: #c7e7e8;
}
</style>

<script type="text/javascript">
	
var todayDate = new Date().getDate();

 $(document).ready(function() {
	
	$("#currentDate").datepicker({
		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
	
	$("#toDate").datepicker({
		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
	
});
 
 function printExcel() {

		$(".xlstable").table2excel({
			exclude : ".noExl",
			name : "Refered Department Count Report",
			filename : "Refered Department Count",
			fileext : ".xls",
			exclude_img : true,
			exclude_links : true,
			exclude_inputs : true
		});
	}
</script>


	<body>
		<div class="">
		  <div
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-lg hidden-md visible-print">
			<div class="row letterheadhgt" style="height: 135px;">
				<div id="newpost"
					class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
					<link href="common/css/printpreview.css" rel="stylesheet" />
					<%@ include file="/accounts/pages/letterhead.jsp"%>
				</div>
			</div>
		</div>
					<div class="row" style="padding: 0">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
							 	<h4>Department Refer Count Report</h4>
							 	  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md visible-print"><h4>(<s:property value="FromDate" /> To <s:property value="ToDate" />)</h4></div>
							</div>
						</div>
					</div>
					
				<div class="row hidden-print" style="padding-top: 15px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<!--  -->
						<form action="referdepartmentcountPatientdata" id="mainform">
							<div class="col-md-9 col-lg-9 col-xs-12 col-sm-12">
								<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12">
								     <label>Select FromDate</label>
									<s:textfield name="fromDate" id="currentDate" readonly="true" cssClass="form-control"></s:textfield>
								</div>
								<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12">
								    <label>Select ToDate</label>
									<s:textfield name="toDate" id="toDate" readonly="true" cssClass="form-control"></s:textfield>
								</div>
								
								<div hidden class="col-md-2 col-lg-2 col-xs-12 col-sm-12">
										<label>Select Department</label>
										<s:select list="disciplineList" id="dept" name='dept' listKey="id" listValue="discipline" headerKey="0" headerValue="Select Department" onchange="setdept(this.value)"  cssClass="form-control chosen-select"></s:select>
									</div>
								<div class="col-md-3 col-lg-3 col-xs-12 col-sm-12">
									<!-- <button type="submit" class="btn btn-primary">Go</button> -->
									<br> <input type="submit" value="Go"
									class="btn btn-primary active" /> <a type="button"
									class="btn btn-primary" title="Print" onclick="printpage()"><i
									class="fa fa-print"></i></a> <a type="button" id="btnxls"
									title="Download Excel" onclick="printExcel()"
									class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
								</div>
							</div>
						</form>
					</div>	
					
					
					
				</div>				
		</div>
		
		
		<div class="col-lg-8 col-md-8 col-xs-12 col-sm-12 topback2">
						<table class="table xlstable table-condensed table-bordered" style="border: black 1px solid;">
							<thead>
								<tr>
									<td>Department</td>
									<td>Count</td><td>Refered From Department</td>
								</tr>
							</thead>
							<tbody>
								
									<%-- <s:if test="totalPatientCount>0"> --%>
										<tr>
										  <s:iterator value="deptWiseCountList">
										    <tr>
										    <td><s:property value="discipline"/></td>
										    <s:if test="Referdeptlist.size!=0 ">
										     <s:iterator value="Referdeptlist">
										          <td><s:property value="count"/></td><td>(<s:property value="Department"/>)</td>
						                    </s:iterator>
											  </s:if>
											  <s:else>
											  
											  <td>0</td>
											  </s:else>
											</tr>
										</s:iterator>
										</tr>
									<%-- </s:if> --%>
								
							</tbody>
							<%-- <thead>
								<tr>
									<td>Total Patient</td>
									<td><s:property value="finalNewPatientCount"/></td>
									<td><s:property value="finalOldPatientCount"/></td>
									<td><s:property value="finalTotalPatientCount"/></td>
								</tr>
							</thead> --%>
						</table>
					</div>		
