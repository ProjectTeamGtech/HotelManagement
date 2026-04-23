<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

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
					filename: "Ward_Wise_Revenue_Report",
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

										<h4> Ward Wise Bed Occupancy & Revenue Report</h4>

									</div>

</div>	

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<s:form action="wardwiseavgstaySummary" theme="simple" >
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="form-inline">
			<div class="form-group">
				
		
			
			
			<div class="form-group" style="width:20%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:20%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			
			<div class="form-group" style="width:30%;">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
			
			<div class="form-group" style="width:20%;">
			<b>Bed Ocuupancy</b> =Total No. Of Patient Present in Ward <b>/</b> Total No of Beds in Ward *<b> Adjustment Factor</b>
			</div>
		</div>
	</div>
	</div>
	</s:form>
</div>							
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="my-table xlstable" style="width:100%">
								<tr bgcolor="#3c6ea0" style="color:white;">
								<td>Sr.</td>
								
								
								<td> Ward Name</td>
								
								
								<td>Charges</td>
								<td>Patients</td>
								<td>Bed Occupancy %</td>
								<td>Avg Stay</td>
								<td>No. Of Beds</td>
								
								</tr>
								<%int i=0; %>
								<s:iterator value="reportlist">
								<s:if test="avgipd!=0.0">
								<tr>
								<td><%=++i %></td>
								
								
								<td><s:property value="wardname"/></td>
								
								
								
								
								
								
								<td><s:property value="charge"/></td>
								
								<td><s:property value="total"/></td>
								
								<td><%-- <s:property value="patientcount"/> --%><s:property value="avgipd"/> %</td>
								
								<td><s:property value="bedoccupancy"/><!--Avg length of stay  --> days</td>
								
								
								<td><s:property value="count"/></td>
								
								
								  
								
										
								</tr>
								</s:if>
								</s:iterator>
								</table>
</div>								




</div>


<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
