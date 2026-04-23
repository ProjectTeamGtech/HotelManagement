<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script>


 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "TP Pkg applied Report",
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

										<h4>Third Party Applied Package Report </h4>

									</div>

</div>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
	<s:form action="tppackagereportSummary" theme="simple" id = "invoicerportfrm">	
		<div class="form-inline">
			<div class="form-group" style="width:8%;">
			<label>From Date</label>
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			
			<div class="form-group" style="width:8%;">
			<label>To Date</label>
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			
			<div class="form-group" >
			<br>
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
		</div>
	</s:form>	
	</div>


<table class="my-table xlstable" style="width:100%">
								<tr bgcolor="#3c6ea0" style="color:white;">
								<td>Sr.</td>
								
								<td>Patient</td>
								<td>Practitioner</td>
								<td>Package</td>
								<td> FromDate</td>
								<td>ToDate</td>
								<td>Invoice id</td>
								<td>Amount</td>
								<td>Duration</td>
								
								</tr>
								<%int i=0; %>
								<s:iterator value="reportlist">
								<tr>
								<td><%=++i %></td>
								
								<td><s:property value="Clientname"/></td>
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="ipdpkg"/></td>
								<td><s:property value="fromDate"/></td>
								<td><s:property value="toDate"/></td>
								<td><s:property value="invoiceid"/></td>
								<td><s:property value="totalipdamount"/></td>
								<td><s:property value="admitdays"/></td>
								
								</tr>
								</s:iterator>
</table>								




</div>
</div>