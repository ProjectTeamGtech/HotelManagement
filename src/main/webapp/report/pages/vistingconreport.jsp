<%@taglib uri="/struts-tags" prefix="s"%>
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
					filename: "vistingconsultantLsit",
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
	 document.addEventListener("contextmenu", function(e){
			e.preventDefault();
			}, false); 
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

										<h4>Visiting Consultants Report </h4>

									</div>

</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden-print" style="padding: 10px;">
<s:form action="vistingconreportSummary" theme="simple" >
<div class="form-inline">
			<div class="form-group" style="width:8%;">
			<label>From Date :</label>
					<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:90%;" placeholder="from date"></s:textfield>
			</div>
			
			<div class="form-group" style="width:8%;">
			<label>To Date :</label>
					<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:90%;" placeholder="to date"></s:textfield>
			</div>
			
			
			
			<div class="form-group" style="width:15%;">
			<label>Consultants :</label>
				<s:select list="userConList" name='diaryUser' listKey="id" listValue="fullname" cssClass="form-control chosen-select" theme="simple" style="width:90%;" headerKey="" headerValue="Select Consultant"></s:select>
			</div>
			
			
			<div class="form-group" style="width:15%;">
			<br>
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			
</div>			
</s:form>
</div>
	
	
	
	<table class="my-table xlstable" style="width:100%">
	<tr  bgcolor="#3c6ea0" style="color:white;">
	<td>Sr No.</td>
	<td>Name</td>
	<td>Ward / Bed</td>
	
	<td>Practitioner</td>
	<td> Date/Time</td>
	<td>Share</td>
		<td>TDS</td>
	<td>Fees</td>
	<td>Payment</td>
	<td>Paid Amount</td>
	<td>Status</td>
	
	</tr>
	<%int i=1; %>
	<s:iterator value="visitingConList">
	<tr style="border: 1px gray solid;">
	<td><%=i %><%i++; %></td>
	<td><s:property value='clientname'/></td>
	<td><s:property value='wardname'/></td>
	<td><s:property value='practitionername'/></td>
	<td><s:property value='date'/> / <s:property value='time'/></td>
	<td><s:property value='sharePercent'/> %</td>
	<td><s:property value='tds'/></td>
	<td><s:property value='fees'/></td>
	
	<td><s:if test="payment==1">Paid</s:if><s:else>UnPaid</s:else></td>
	<td><s:property value='paid_amount'/>   </td>
	<td><s:if test="status==1">Visited</s:if><s:else>Planned</s:else></td>
	
	
	</tr>
	</s:iterator>
	</table>
	
	
</div>

<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    </script>