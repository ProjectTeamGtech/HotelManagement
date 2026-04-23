<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>  
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>  
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<script>
function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "paymentReportcombinned",
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
<style>
	.loc{
		background-color: #6699cc;
		color: white;
	}
	@media print {
		.loc{
			background-color: #6699cc  !important;
			color: white;
		}
	}
</style>
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

										<h4>Payment Report Combined</h4>

									</div>

</div>
<form action="payment_report_combinnedChargesRpt" method="post">
	<div class="form-inline hidden-print" style="margin: 10px;">
					<div class="form-group hidden-print" style="width:8%;">		
						<label>From Date</label>
						<s:textfield readonly="true" name="fromDate" id="fromDate"
						cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
					</div>				
	
					<div class="form-group hidden-print" style="width:8%;">
					<label>To Date</label>
						<s:textfield readonly="true" name="toDate" id="toDate"
						cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
					</div>
					<div class="form-group" style="width:20%;">		
					
						<br><s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
						<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
						<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
					</div>
	</div>
</form>

<div style="padding: 10px;" class='print-visible hidden-md hidden-lg'><h3><label>From Date:</label> <s:property value="fromDate"/><label style="margin-left: 50px;">To Date:</label> <s:property value="toDate"/></h3></div>


<table class="my-table xlstable" style="width:100%;border:1px solid #DFD8D4 !important;">
<tr bgcolor="#3c6ea0" style="color:white;" class="loc">
<td >Sr. No.</td>
<td >Opening / Closing Invoice</td>
<td style="width: 10%"> Name</td>
<td >Cash</td>
<td  >Card</td>
<td  >NEFT</td>
<td  >Cheque</td>
<td  >UPI</td>
<td  >Total</td>
<td  >Net Advance</td>
</tr>
<%int i=1; %>
<s:iterator value="list">
<s:if test="department=='Total'||department=='Refund Total'">
<tr style="background-color: #c9d6f3">
<td><%=i %><%i++; %></td>
<td><s:property value="fseqno"/></td>
<td><s:property value="department"/></td>
<td><s:property value="Cashtotal"/></td>
<td><s:property value="Drtotal"/></td>
<td><s:property value="Nefttotal"/></td>
<td><s:property value="Cheqtotal"/></td>
<td><s:property value="upitotal"/></td>
<td><s:property value="totalamt"/></td>
<td><s:property value="Netadvance"/></td>
</tr>
</s:if>
<s:elseif test="department=='Final Total'">
	<tr style="background-color: #f2f3c9">
<td><%=i %><%i++; %></td>
<td><s:property value="fseqno"/></td>
<td><s:property value="department"/></td>
<td><s:property value="Cashtotal"/></td>
<td><s:property value="Drtotal"/></td>
<td><s:property value="Nefttotal"/></td>
<td><s:property value="Cheqtotal"/></td>
<td><s:property value="upitotal"/></td>
<td><s:property value="totalamt"/></td>
<td><s:property value="Netadvance"/></td>
</tr>
</s:elseif>
<s:else>
<tr>
<td><%=i %><%i++; %></td>
<td><s:property value="fseqno"/></td>
<td><s:property value="department"/></td>
<td><s:property value="Cashtotal"/></td>
<td><s:property value="Drtotal"/></td>
<td><s:property value="Nefttotal"/></td>
<td><s:property value="Cheqtotal"/></td>
<td><s:property value="upitotal"/></td>
<td><s:property value="totalamt"/></td>
<td><s:property value="Netadvance"/></td>
</tr>
</s:else>
</s:iterator>
</table>
</div>