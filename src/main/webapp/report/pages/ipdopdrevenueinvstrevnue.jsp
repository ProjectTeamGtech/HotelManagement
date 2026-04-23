<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script>


 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "ipdopdinvestigationReport",
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

										<h4>IPD/OPD Investigation Report </h4>

									</div>

</div>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
	<s:form action="ipdopdrevenueinvstrevnueSummary" theme="simple" id = "invoicerportfrm">	
		<div class="form-inline">
		 <div class="form-group" style="width:15%;">
		<label>Search</label>
				<s:textfield  name="search" id="search"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Search by Name/UHID"></s:textfield>
			</div> 
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
			<label>Type</label><br>
				<s:select name="type" id="type" 
				list="#{'':'All','ipd':'IPD','opd':'OPD'}"
				cssClass="form-control chosen-select" ></s:select>
			</div> 
			<div class="form-group" >
			<label>Paid/Unpaid</label><br>
				<s:select name="itype" id="itype" 
				list="#{'':'All','paid':'Paid','unpaid':'Unpaid'}"
				cssClass="form-control chosen-select" ></s:select>
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


<table class="my-table xlstable" style="width:100%" id='example'>
								<tr bgcolor="#3c6ea0" style="color:white;">
								<td>Sr.</td>
								<td>UHID</td>
								<td>Patient</td>
								<td>Practitioner</td>
								<td>Invoice Type</td>
								<td>Investigation</td>
								<td> Date</td>
								<td>Invoice Id</td>
								<td>Charge</td>
								<td>Discount( Rs. )</td>
								<td>Paid/Unpaid</td>
								<td>Amount</td>
								
								</tr>
								<%int i=1; %>
								<s:iterator value="reportlist">
								<tr>
								<td><%=i%><%i++; %></td>
								<td><s:property value="abrivationid"/></td>
								
								<td><s:property value="Clientname"/></td>
								<td><s:property value="practitionerName"/></td>
								<td>
								<s:if test="Ipdid==0">
								OPD
								</s:if>
								<s:else>
								IPD
								</s:else>
								</td>
								<td><s:property value="ApptName"/></td>
								<td><s:property value="date"/></td>
								<td><s:property value="invoiceid"/></td>
								<td><s:property value="charge"/></td>
								<td><s:property value="discamt"/></td>
								<td>
								<s:if test="totalinvstamount==0">
								Unpaid
								</s:if>
								<s:else>
								Paid
								</s:else>								
								</td>
								<td><s:property value="totalinvstamount"/></td>
								
								</tr>
								</s:iterator>
</table>								




</div>
</div>


	<script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
	


<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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