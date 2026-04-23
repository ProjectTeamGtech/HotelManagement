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
					name: "Payment List",
					filename: "No's_of_admission_and_discharge",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
	
</script>
<div class="">
	<div class="print-visible hidden-md hidden-lg printableArea letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>

							<div class="row details ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 printableArea">

										<h4>No's Of Admission/Discharge</h4>

									</div>
								</div>
								<s:form action="nosofadmdiscReport" theme="simple">
									
								<div class="col-lg-12 col-md-12 col-xs-12 topback2">
										<div class="form-inline">
											<div class="form-group hidden-print" style="width:7%;">
												 <s:select cssClass="form-control" list="#{'2014':'2014','2015':'2015','2016':'2016','2017':'2017', '2018':'2018', '2019':'2019', '2020':'2020', '2021':'2021','2022':'2022', '2023':'2023', '2024':'2024', '2025':'2025', '2026':'2026', '2027':'2027', '2028':'2028'}" name="toDate" />
											</div> 
											<div class="form-group hidden-print">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right hidden-print">
												<a href="#"  onclick="printcancelInvoiceReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
											<div class="form-group pull-right hidden-print">
												<a href="#" class="btn btn-warning" onclick="printpage()">Print</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
				<table class="my-table tablexls"  style="width: 100%;font-size: 8px"  >
						<thead>
                                                       	 <tr style="background-color: #a2c4e6; font-weight: bold;">
                                                       	 		<td style="font-weight: bold;">#</td>
                                                                <td style="font-weight: bold;">January</td>
                                                                <td style="font-weight: bold;">February</td>
                                                                <td style="font-weight: bold;">March</td>
                                                                <td style="font-weight: bold;">April</td>
                                                                <td style="font-weight: bold;">May</td>
                                                                <td style="font-weight: bold;">June</td>
                                                                <td style="font-weight: bold;">July</td>
                                                                <td style="font-weight: bold;">August</td>
                                                                <td style="font-weight: bold;">September</td>
                                                                <td style="font-weight: bold;">October</td>
                                                                <td style="font-weight: bold;">November</td>
                                                                <td style="font-weight: bold;">December</td>
                                                        </tr>
                                                </thead>
					<tbody>
						<%int i=0; %>
						<s:iterator value="nosofadmissiondischarge">
					   		<tr>
					   			<td>Admission</td>
								<td><s:property value="jan"/></td>
								<td><s:property value="feb"/></td>
								<td><s:property value="mar"/></td>
								<td><s:property value="apr"/></td>
								<td><s:property value="may"/></td>
								<td><s:property value="jun"/></td>
								<td><s:property value="jul"/></td>
								<td><s:property value="aug"/></td>
								<td><s:property value="sep"/></td>
								<td><s:property value="oct"/></td>
								<td><s:property value="nov"/></td>
								<td><s:property value="dec"/></td>
							</tr>
							<tr>
								<td>Discharge</td>
								<td><s:property value="jan1"/></td>
								<td><s:property value="feb1"/></td>
								<td><s:property value="march1"/></td>
								<td><s:property value="april1"/></td>
								<td><s:property value="may1"/></td>
								<td><s:property value="june1"/></td>
								<td><s:property value="jully1"/></td>
								<td><s:property value="aug1"/></td>
								<td><s:property value="sept1"/></td>
								<td><s:property value="oct1"/></td>
								<td><s:property value="nov1"/></td>
								<td><s:property value="dec1"/></td>
							</tr>
							
					   </s:iterator>
					  
					</tbody>
			 </table>
		</div>
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



	