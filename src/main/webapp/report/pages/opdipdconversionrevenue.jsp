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

        $(".tablexls").table2excel({
					exclude: ".noExl",
					name: "Patient OPD to IPD Report",
					filename: "Patient_OPD_to_IPD_Report",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
	
</script>


								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Patient OPD to IPD Report</h4>

									</div>
								</div>
								<s:form action="opdipdconversionrevenueSummary" theme="simple">
								<div class="col-lg-12 col-md-12 col-xs-12 topback2">
										<div class="form-inline">
											<div class="form-group" style="width:7%;">
												<s:textfield  name="fromDate" id="fromdate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
												<s:textfield name="toDate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
										
											<div class="form-group">
													<div class="clearfix"></div>
												<a class="btn btn-primary" href="#"  onclick="printcancelInvoiceReportExcel()" title="Download CSV file" ><i class="fa fa-download"></i> Download .XLS</a>
											</div>
											<div class="form-group">
												<a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a>
											</div>
											
											
											
											
												<div title="(Conversion IPD Revenue/Net Hospital Revenue)*100  " class="form-group mischartconversionratio pull-right">
											<div class="miscounttextnum"><s:property value="totaldebitAmountPer"/>%</div>  <div class="clearfix"></div> <span class="miscounttext">Conversion Ratio</span>
											</div>
											
											<div title="Conversion IPD Revenue" class="form-group mischartconversionipdrevenu pull-right">
											<div class="miscounttextnum"><i class="fa fa-rupee"></i> <s:property value="totaldebitAmount"/></div> <div class="clearfix"></div>  <span class="miscounttext">Conversion Revenue</span> 
											</div>
											
										
											
											
											<div class="form-group mischartnethospitalrevenu pull-right">
										<div class="miscounttextnum"><i class="fa fa-rupee"></i> <s:property value="netipddebitcount"/></div> <div class="clearfix"></div> 	<div class="miscounttext">Net Revenue</div>
											</div>
											
											<div class="form-group mischarttotalcountboxa pull-right">
											<div class="miscounttextnum"> <s:property value="count"/></div>  <div class="clearfix"></div>  <span class="miscounttext">Patient Count</span>
											</div>
											
											
											
											<div class="form-group hidden">
												Total Count:<s:property value="count"/>
												&nbsp;&nbsp;Net Hospital Revenue:<s:property value="netipddebitcount"/>
												&nbsp;&nbsp;Conversion IPD Revenue:<s:property value="totaldebitAmount"/>
												&nbsp;&nbsp;Formula:(Conversion IPD Revenue/Net Hospital Revenue)*100=<s:property value="totaldebitAmountPer"/>%
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
				<table class="my-table tablexls"  style="width: 100%;font-size: 8px"  >
						<thead>
							<tr>
							   	<th>Sr. no</th>
								<th>UID</th>
								<th>IPD ID</th>
						 		<th>Patient Name</th>
						 		<th>Admission Date</th>
						 		<th>Discharge Date</th>
						 		<th>Amount</th>
						 		
							</tr>
						</thead>
					<tbody>
						<%int i=0; %>
						<s:iterator value="opdipdconversionrevenuelist">
					   		<tr>
					     		<td><%=++i %></td>
					     		<td><s:property value="abrivationid"/></td>
					     		<td><s:property value="ipdopdseq"/></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="admissiondate"/></td>
								<td><s:property value="dischargedate"/></td>
								
								<td><s:property value="debitAmount"/></td>
								
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



	