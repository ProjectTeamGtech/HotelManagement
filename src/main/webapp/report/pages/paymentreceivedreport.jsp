<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Payment received report",
					filename: "paymentrecivedrpt",
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



<div class="">
<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
<div class="row details">
<h4>Payment Received Report</h4>			
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

							

	<div class="row ">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		
<s:form action="paymentreceivedreportChargesRpt" theme="simple" id = "invoicerportfrm">
<div class="col-lg-12 col-md-12 topback2 hidden-print">
<div class="form-inline">
<label style="color:black">from Date</label>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
<label style="color:black">To Date	</label>		<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="To date"></s:textfield>
			</div>
			
				&nbsp;&nbsp;&nbsp;
<div class="form-group"  style="width: 6%">
				<s:select name="invcetype" id="invcetype"
					list="#{'':'Show All ','IPD':'IPD','Advance':'Advance'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			&nbsp;&nbsp;&nbsp;
<div class="form-group"  style="width: 6%">
				<s:select name="howpaid" id="howpaid"
					list="#{'':'Show All Payment','Cash':'Cash','Cheque':'Cheque','NEFT':'NEFT','D/Card':'D/Card'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="form-group"  style="width: 7%">
			<s:select list="userProfileList" theme="simple" name="userid" listKey="userid" listValue="userid" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" ></s:select>
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
				<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
				</div>
</div>
</div>
</s:form>	

<%-- <h4>Refund Total:<b><s:property value="totalrefund"/></b></h4> --%>
		<h4>Advance Total:<b><s:property value="totaladvnce"/></b></h4>
		<h4>IPD Total:<b><s:property value="totalipd"/></b></h4>	
		<table id="tbl" class="my-table xlstable" style="width: 100%">
		<tr style="background-color:#8cb2d8">
			<td>Sr no</td>
			<td>Transaction id</td>
			<td>Date</td>
			<td>Type</td>
			<td>Patient Name</td>
			
			<td>Payment Mode</td>
			<td>Amount</td>
			<td>User Name</td>
		</tr>
		<%int i=0; %>
		<s:iterator value="paymentrecivedList">
		<tr>
		<td><%=++i %></td>
		<td><s:property value="id"/></td>
		<td><s:property value="date"/></td>
		<td><s:property value="invoicetype"/></td>
		<td><s:property value="ClientName"/></td>
		
		<td><s:property value="paymentmode"/></td>
		<td><s:property value="amount"/></td>
		<td><s:property value="username"/></td>
		</tr>
		</s:iterator>
		<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td><b><s:property value="totalamount"/></b></td>
		<td></td>
		
		</tr>
		</table>
		
		</div>
	</div>								
</div>
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
