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
					filename: "report",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }
  

/* $(document).ready(function() {

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
 */
</script>



<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-bottom: 50px">
			<%-- <div class="print-visible hidden-md hidden-lg" style="height: 135px;">
			<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
			<link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
			</div>
			</div>
 --%>			
			<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>IPD THIRD PARTY REPORT </h4>

									</div>

			</div>	
			
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 30px;">
		<div class='hidden-print' style="text-align: right;padding-bottom: 5px;">	
		<a type="button" class="btn btn-success"  title="Print" onclick="printpage()">Print  <i class="fa fa-print"></i></a>
		<!-- <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-success">XLS  <i class="fa fa-file-excel-o"></i></a>
		 --><br>
		</div>
			<table class="my-table " style="width:100%">
				<tr bgcolor="#3c6ea0" style="color:white;border-top: 1px solid !important">
				
				<td style="width: 2%;">Sr.</td>
				<td style="width: 15%;">Patient Name</td>
				<td style="width: 5%;">IP. NO.</td>
				<td style="width: 15%;">Admit Date</td>
				<td style="width: 10%;">Age</td>
				<td style="width: 15%;">Ward/Bed</td>
				
				<td style="width: 12%;">Consultant</td>
				<td style="width: 2%;">Admit Days</td>
				<td style="width: 7%;">Self Charge </td>
				<td style="width: 7%;">TP Charge</td>
				<td style="width: 7%;">Advance</td>
				<%int i=0; %>
				</tr>
				</table>	
				<s:iterator value="reportlist">
				<div style="background-color: #3c6ea0;color: white"><b style="margin-left: 10px;"><s:property value="tpName"/></b></div>
				<table class="my-table xlstable" style="width:100%">
				<s:iterator  value="otlist">				
				
				<tr style="border: 1px solid;">
				
				<td style="width: 2%;"><%=i+1 %></td>
				<td style="width: 15%;"><s:property value='patientName'/></td>
				<td style="width: 5%;"><s:property value='ipdseqno'/></td>
				<td style="width: 15%;"><s:property value='admissiondate'/></td>
				<td style="width: 10%;"><s:property value='age1'/></td>
				<td style="width: 15%;"><s:property value='wardname'/>/<s:property value='bedname'/></td>
				<%-- <td style="width: 8%;">BED-<s:property value='bedname'/></td> --%>
				<td style="width: 12%;"><s:property value='username'/></td>
				<td style="width: 2%;"><s:property value='admitdays'/></td>
				<td style="width: 7%;"><s:property value='selfcharge'/></td>
				<td style="width: 7%;"><s:property value='tpcharge'/></td>
				<td style="width: 7%;"><s:property value='advance'/></td>
				<%i++; %>
				</tr>
				</s:iterator>
				</table>
				</s:iterator>
			
			</div>
</div>