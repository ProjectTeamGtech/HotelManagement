<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>



<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>
 
	$(document).ready(function() {

		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true
		});
	});
	function printcancelInvoiceReportExcel() {

        $(".tablecancelinvoice").table2excel({
					exclude: ".noExl",
					name: "Stock Valuation Report",
					filename: "stockValuationreport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
	
</script>


								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Stock Valuation Report</h4>

									</div>
								</div>
								<s:form action="stockvaluationReport" theme="simple">
								<div class="col-lg-12 col-md-12 col-xs-12 topback2">
										<div class="form-inline">
											<div class="form-group" style="width:25%;">
												<s:select list="locationlist" theme="simple" name="location" cssStyle="width:30%" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" >
										     	</s:select> 
											</div>
											<div class="form-group">
												<s:select cssClass="form-control" list="#{'0':'Sort By Material Name', '1':'Sort By Stock'}" name="filteroforder" />
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right">
												<a href="#"  onclick="printcancelInvoiceReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
											<div class="form-group pull-right">
												<a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
			<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
	<h5 class="hidden-lg hidden-md visible-print"><span class="text-uppercase"><b>Stock Valuation Report</b> &nbsp;  &nbsp;</span></h5>
				<table class="my-table tablecancelinvoice"  style="width: 100%;font-size: 8px"  >
						<thead>
							<tr>
							   	<th style="width: 4%; !important">Sr. no</th>
								<th style="widht: 10% !important">Catalogue ID</th>
						 		<th style="widht: 30% !important">Material Name</th>
						 		<th style="widht: 10% !important">Stock</th>
						 		<th style="widht: 10% !important">Value</th>
						 		<th style="widht: 10% !important">Average Cost</th>
						 		<th style="widht: 10% !important">Last GRN Date</th>
							</tr>
						</thead>
					<tbody>
						<%int i=0; %>
						<s:iterator value="stockvaluationlist">
					   		<tr>
					     		<td><%=++i %></td>
					     		<td><s:property value="catalogueid"/></td>
								<td><s:property value="prod_name"/></td>
								<td><s:property value="totalstock"/></td>
								<td><s:property value="totalpurchaseprice"/></td>
								<td><s:property value="avgcost"/></td>
								<td><s:property value="lastgrndate"/></td>
							</tr>
					   </s:iterator>
					</tbody>
			 </table>
		</div>

<%-- <table class="my-table tablecancelinvoice" id = "cancelinvoiceReportTable " style="width: 100%;font-size: 8px;display:none;">    
            <thead>
							<tr>
							   	<th>Sr. no</th>
								<th>UID</th>
						 		<th>Patient Name</th>
						 		<th>Admission Date</th>
						 		<th>Amount</th>
							</tr>
						</thead>
            		<tbody>
						<% i=0; %>
						<s:iterator value="opdipdconversionrevenuelist">
					   		<tr>
					     		<td><%=++i %></td>
					     		<td><s:property value="abrivationid"/></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="admissiondate"/></td>
								<td><s:property value="debitAmount"/></td>
							</tr>
					   </s:iterator>
					</tbody>
</table> --%>

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

<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>



	