<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<script type="text/javascript" src="ipd/js/addcharges.js"> </script>
<script type="text/javascript" src="emr/js/emr.js"></script>
<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "invstcountrpt",
					filename: "invstcountrpt",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

$(document).ready(function() {

	$("#fromDate").datepicker({

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

</script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		
							<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Investigation Counts</h4>

									</div>

</div>	
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="investigationtypewisereportInvestigation" theme="simple" id = "invoicerportfrm">

	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			<div class="form-group">
				
			<%-- <div class="form-group">
				<s:select name="practitionerName" list="userList" listKey="diaryUser" listValue="diaryUser" cssClass="form-control chosen-select" headerKey="" headerValue="All Practitioner" theme="simple" onchange = "setActionForAll()" ></s:select>
			</div> --%>
			
			
			
			<div class="form-group" style="width:20%;">
				<s:textfield readonly="true" name="fromdate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:20%;">
				<s:textfield readonly="true" name="todate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
		</div>
	</div>
	</div>
	</s:form>
</div>							
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="my-table xlstable" style="width:100%">
								<thead>
								<tr>
								<th>Sr.</th>
								<th>Investigation Type</th>
								<th>Total Investigation</th>
								<th>New</th>
								<th>Deleted</th>
								<th>Approved</th>
								<th>Collected</th>
								<th>Completed</th>
								</tr>
								</thead>
								<%int i=0; %>
								<s:iterator value="investigationList">
								<tbody>
								<tr>
								<td><%=++i %></td>
								
								<td><s:property value="invsttype"/></td>
								<td><s:property value="count"/></td>
								<td><s:property value="new_invistigation"/></td>
								<td><s:property value="deleted_investigation"/></td>
								<td><s:property value="new_aproved"/></td>
								<td><s:property value="new_collected"/></td>
								<td><s:property value="new_completed"/></td>
								</tr>
								</tbody>
								</s:iterator>
								</table>
</div>								




</div>