<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
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
	<s:form action="investigationdetailrevenueSummary" theme="simple" id = "invoicerportfrm">	
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

<h4 ><span class="titsetas">Total Revenue:-</span> <span><%=Constants.getCurrency(loginfo) %><s:property value="Totalamt"/></span></h4>
<table class="my-table xlstable" style="width:100%" id='example'>
								<tr bgcolor="#3c6ea0" style="color:white;">
								<td>Receipt No.</td>
							 <td>Invoice No.</td>
							<td>Type</td> 
							 
							<td>User</td>  
							
							<td>UHID</td>
							<td>Patient Name</td>
							<td>Patient Type</td>
							
							<td>Date <a href="#" onclick="setSorting('date','<s:property value="order"/>')"><i class="fa fa-sort hidden-print"></i></a></td>
							<td>Payment Mode <a href="#" onclick="setSorting('paymode','<s:property value="order"/>')"><i class="fa fa-sort hidden-print"></i></a></td>
							<td>Amount Paid</td>
							<td>Service name</td>
							<td>Discount</td>
							<td>Balance</td>
							
								
								</tr>
								<%int i=1; %>
								<s:iterator value="InvDetailedrevenue">
								<tr>
								<td><s:property value="newsr" /><%if(loginfo.isSeq_no_gen()&&(loginfo.getClinicUserid().equals("aureus")||loginfo.getClinicUserid().equals("nelson"))){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									
									<%-- <s:if test="whoPay=='Client'">
										<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="invoicee" /> (Self)</td>
										</s:if>
										<s:else>
										<td><s:property value="invoicee" /> (Self)</td>
										</s:else>
									</s:if>
									<s:else>
										<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="invoicee" /> (Third Party)</td>
										</s:if>
										<s:else>
										<td><s:property value="invoicee" /> (Third Party)</td>
										</s:else>
									</s:else> --%>
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"> <%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="invoiceid"/> <%} %></td>
									</s:if>
									<s:else>
										<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="invoiceid"/> <%} %></td>
									</s:else>
									<td><s:property value="invoicenameid"/></td>
									<td><s:property value="userid"/></td>
									<td><s:property value="abrivationid"/></td>
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="ClientName" />&nbsp;</td>
									</s:if>
									<s:else>
										<td><s:property value="ClientName" />&nbsp;</td>
									</s:else>
									
									
									<s:if test="whoPay=='Client'">
										<s:if test="cancelsts==1">
											<td style="text-decoration: line-through;">Self</td>
										</s:if>
										<s:else>
											<td>Self</td>
										</s:else>
									</s:if>
									<s:else>
										<s:if test="cancelsts==1">
											<td style="text-decoration: line-through;">TP</td>
										</s:if>
										<s:else>
											<td>TP</td>
										</s:else>
									</s:else>
									
									<s:if test="cancelsts==1">
									<td style="text-decoration: line-through;"><s:property value="date" /></td>
									</s:if>
									<s:else>
									<td><s:property value="date" /></td>
									</s:else>
									<td><s:property value="paymentmode" /><s:if test="paymentNote!=''"> (<s:property value="paymentNote"/>)</s:if></td>
									<s:if test="cancelsts==1">
									<td style="text-decoration: line-through;"><%=Constants.getCurrency(loginfo) %><s:property value="amountx" /> [<s:property value="Invoiceid"/>]</td>
									</s:if>
									<s:else>
									<td><%=Constants.getCurrency(loginfo) %><s:property value="amountx" /></td>
									</s:else>
									<% if(loginfo.isAyushman() || loginfo.isMatrusevasang()) {%>
									<td><s:property value="AppointmentType" /></td>
									<td><s:property value="Discamt" /></td>
									<td><s:property value="Balance" /></td>
									<%} %>
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