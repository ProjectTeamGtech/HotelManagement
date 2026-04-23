<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>


<script>

 function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Stock report",
					filename: "stockReportBackdate",
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

										<h4>Stock Report(Back Date)</h4>

									</div>
									<br>
									<p></p>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top:10px">
								<s:form action="newstockreport1Summary" theme="simple" >
								<div class="form-inline hidden-print">
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
			</div>
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
								</div>
								</s:form>
								<div class="text-right">
								<h4>Total :<b><s:property value="totalamt"/></b></h4>
								</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="my-table xlstable" style="width: 100%">
								<thead>
								<tr>
								<th>Sr no.</th>
								<th>Date</th>
								<th>Product Name</th>
								<th>Purchase Price</th>
								<th>Qty</th>
								
								</tr>
								</thead>
								<tbody>
								<%int x=0; %>
								<s:iterator value="productlist">
								<tr>
								<td><%=(++x) %></td>
								<td><s:property value="date"/></td>
								<td><s:property value="name"/></td>
								<td><s:property value="purchase_price"/></td>
								<td><s:property value="quantity"/></td>
								
								
								</tr>
								</s:iterator>
								</tbody>
								</table>
								</div>

</div>
</div>