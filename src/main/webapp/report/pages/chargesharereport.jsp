<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script type="text/javascript" src="accounts/js/viewstatement.js"></script>

<style>
	.loc{
		background-color: #6699cc;
		color: white;
	}
</style>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>
 
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
	
	function printcancelInvoiceReportExcel() {

        $(".vv").table2excel({
					exclude: ".noExl",
					name: "Payment List",
					filename: "sharepaymentreport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
	
	
</script>


								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Charge Share Report</h4>

									</div>
								</div>
								<s:form action="chargesharereportSummary" theme="simple">
								<div class="col-lg-12 col-md-12 col-xs-12 topback2">
										<div class="form-inline">
											<div class="form-group" style="width:7%;">
												<s:textfield readonly="true" name="fromDate" id="fromDate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
												<s:textfield readonly="true" name="toDate" id="toDate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group">
												<s:select list="doctorlist" cssClass="form-control chosen-select" name="practitionerId" id="practitionerId" listKey="id" listValue="name" headerKey="0" headerValue="Select Practitioner"></s:select>
											</div>
											<div class="form-group">
												<s:select list="clientlist" cssClass="form-control chosen-select" name="clientId" id="clientId" listKey="id" listValue="fullname" headerKey="0" headerValue="Select Client"></s:select>
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right">
												<a href="#" class="btn btn-warning" onclick="printDiv('printableArea')">Print</a>
											</div>
											<div class="form-group pull-right">
												
												<a href="#"  onclick="printcancelInvoiceReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
										</div>
										
								</div>
								</s:form>
								
			<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;" id="printableArea">
							<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 90px;">
									<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
											 <link href="common/css/printpreview.css" rel="stylesheet" />
										<%@ include file="/accounts/pages/letterhead.jsp" %>
										</div>
									</div>
								</div>
								
								<div class="row details print-visible hidden-md hidden-lg">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Charge Share Report</h4>

									</div>
								</div>
				<table class="my-table tablexls vv"  style="width: 100%;font-size: 8px"  >
							<thead>
							<tr class="loc">
								<td>Sr. no</td>
								<td>ID</td>
								<td>Practitioner Name</td>
								<td>Patient Name</td>
								<td>Date</td>
						 		<td>Commission Type</td>
						 		<td>Amount</td>
						 		<td>All Or Indivisual</td>
								<td class="hidden-print">Edit</td>
							</tr>
					</thead>
					<tbody>
						<%int i=0; %>
						<s:iterator value="chargesharelist">
					   		<tr>
					     		<td><%=++i %></td>
								<td><s:property value="id"/></td>
								<td><s:property value="practitionerName"/></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="date"/></td>
						 		<td><s:property value="commission_type"/></td>
						 		<td><s:property value="amountx"/></td>
						 		<td>
						 			<s:if test="all_or_indivisual==1">
						 				Single Charges
						 			</s:if>
						 			<s:else>
						 				All Charges
						 			</s:else>
						 		</td>
						 		<td class="hidden-print"><!-- <a href="#" style="color:#3c763d" data-toggle="modal" data-target="#sharecharge" title="Share"><i class="fa fa-share-alt" aria-hidden="true" style="color: green;"></i></a> -->
						 			<a href="#" style="color:#3c763d" onclick="shareChargeToDrEdit(<s:property value="id"/>,<s:property value="TotalAmountx"/>,'<s:property value="commission_type"/>',<s:property value="amountx"/>,<s:property value="practitionerId"/>,'<s:property value="remark"/>')" title="Share"><i class="fa fa-pencil" aria-hidden="true" style="color: green;"></i></a>
						 		</td>
					     	</tr>
					   </s:iterator>
					</tbody>
			 </table>
		</div>

<!-- Share Charge with Doctors -->
<div id="sharecharge" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Share with Doctor</h4>
      </div>
      <div class="modal-body">
      <div class="col-lg-12 col-md-12 col-xs-12">
              <s:hidden id="shareid"></s:hidden>
              
               <div class="form-group" style="margin: 0px;color: green;font-size: 15px;">
              		<span><label>Total:</label> Rs.<span id="share_chargetotal">0.00</span></span>
            	</div>
              
                <div class="form-group">
              		<label for="email">Select Doctor</label>
		              <%-- <select class="form-control" name="doct" id="">
		              		<option value="0">Select</option>
		              		<option value="Dr.Abc">Dr.Abc</option>
			                <option value="Dr.Xyz">Dr.Xyz</option>
		              </select> --%>
		              <s:select list="doctorlist" cssClass="form-control chosen-select" listKey="id" listValue="name" id="share_doctorid" theme="simple" headerKey="0" headerValue="Select"></s:select>
            </div>
               
               </div>
               
               
               
               <div class="col-lg-12 col-md-12 col-xs-12" style="background-color: rgba(239, 239, 239, 0.33);    padding: 7px 0px 0px 0px;">
               <div class="col-lg-6 col-md-6 col-xs-6">
                <div class="form-group">
              		<label for="email" style="color:red;">Discount</label>
		              <select class="form-control" name="disctype" id="disctype" onchange="changeShareDiscountType()">
		              		<option value="Percent">Percent</option>
			                <option value="Rupees">Rupees</option>
		              </select>
            </div>
               </div>
               <div class="col-lg-6 col-md-6 col-xs-6" style="margin-top: 22px;">
	                 <input type="number" id="share_amount" onblur="calculateShareAmount(this.value)" class="form-control"  />
               </div>
               </div>
               <div class="col-lg-12 col-md-12 col-xs-12" style="margin: 0px;color: green;font-size: 15px;">
               <div class="form-group" style="margin: 0px;">
              		<span><label>Commission:</label> Rs.<span id="share_calamount">0</span></span>
            	</div>
               </div>
               
               
               <div class="col-lg-12 col-md-12 col-xs-12 hidden" style="border-bottom: 1px solid #ddd;margin-bottom: 15px;">
               		<p style="font-size:15px;margin: 3px 15px 3px 15px;color: green;">Total To Paid <span style="float: right;"> Rs.00.00</span></p>
               </div>
               
           <div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: 15px;">
            <div class="form-group">
              <label for="email">Remark</label>
              <textarea type="text" rows="3" id="sharing_remark" class="form-control" placeholder="write remark"></textarea>
            </div>
         </div>
               
             </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary"  onclick="editShareChargeToDr()">Save</button>
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

<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>



	