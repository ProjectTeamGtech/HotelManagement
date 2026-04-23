<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<style>
	.loc{
		background-color: #6699cc;
		color: white;
	}
	@media print {
		.loc{
			background-color: #6699cc  !important;
			color: white;
		}
	}
</style>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

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

        $(".vv").table2excel({
					exclude: ".noExl",
					name: "Payment List",
					filename: "paymentreport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
	
</script>
<div class="">
	

								<div class="row details hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Payment Report</h4>

									</div>
								</div>
								<s:form action="paymentreportSummary" theme="simple">
								<div class="col-lg-12 col-md-12 col-xs-12 topback2 hidden-print">
										<div class="form-inline">
											<div class="form-group" style="width:7%;">
												<s:textfield  name="fromDate" id="fromdate" cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:7%;">
												<s:textfield name="toDate" id="todate" cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
											</div>
											<div class="form-group" style="width:-6%;">
												<s:select  cssClass="form-control chosen-select"  list="invoicetypelist" listKey="id" listValue="name" name="itype" headerKey="0" headerValue="All (Invoice Type)"/>
											</div>
											<div class="form-group">
				                              <s:select name="howpaid" id="howpaid" 
				                                 list="#{'0':'Show All Payment','Cash':'Cash','Cheque':'Cheque','NEFT':'NEFT','D/Card':'D/Card','Upi':'Upi'}"
				                                  cssClass="form-control" ></s:select>
			                                 </div>
											<%-- <div class="form-group">
												<s:select list="doctorlist" cssClass="form-control chosen-select" name="practitionerId" id="practitionerId" listKey="id" listValue="name" headerKey="0" headerValue="Select Practitioner"></s:select>
											</div>
											<div class="form-group">
												<s:select list="clientlist" cssClass="form-control chosen-select" name="clientId" id="clientId" listKey="id" listValue="fullname" headerKey="0" headerValue="Select Client"></s:select>
											</div> --%>
											<!-- <div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
											</div> -->
											<div class="form-group" > 
												<button type="submit" class="btn btn-primary">Go</button>
											</div>
											<div class="form-group pull-right">
												
												<a href="#"  onclick="printcancelInvoiceReportExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
											</div>
											<div class="form-group pull-right">
												<a href="#" class="btn btn-warning" onclick="printpage()">Print</a>
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
								<div style="height: 10px" class="print-visible hidden-md hidden-lg"></div>
								<div class="row details print-visible hidden-md hidden-lg">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Payment Report</h4>

									</div>
								</div>
								<div style="padding-top: 10px;" class="print-visible hidden-md hidden-lg"></div>
				<table class="my-table tablexls vv"  style="width: 100%;font-size: 8px">
							<thead>
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<h2><s:property value="Paymentmode"/></h2>:<h2><s:property value="totalReceived"/></h2>
							</div>
							<tr class="loc">
							   
								<td>Sr. no</td>
								<td>Invoice No/Receipt NO</td>
								<td>Date</td>
								<td>UID</td>
								<td>IPD No.</td>
						 		<td>Patient Name</td>
						 		<td>Payment</td>
								<td>Paymode</td>
								<td>Userid</td>
								<td>Type</td>
								
							</tr>
					</thead>
					 <tfoot style="background-color: rgba(245, 245, 245, 0.64);color: green;" class='hidden'>
					                                	<td></td>
					                                	<td>Total</td>
					                                	<td></td>
					                                	<td></td>
					                                	<td></td>
					                                	<td ><s:property value="totalReceived"/></td>
					                                	<td></td>
					                                	<td></td>
					                                	<td></td>
					                                	
					                                	<%-- <td class="hidden"></td>
					                                	<td class="hidden"></td>
					                                	<td class="hidden"></td>
					                                	<td></td>
					                                	
					                                	<td class="text-right"><s:property value="totalReceived"/></td>
					                                	<td></td>
					                                	<td></td> --%>
					                                	
					                                </tfoot>
					<tbody>
						<%int i=0; %>
						<s:iterator value="paymentreportlist">
					   		<tr>
					     		<td><%=++i %></td>
								<s:if test="invoiceid==0">
								<td><s:property value="bghseqId"/></td>
								</s:if>
								<s:else>
								<td>
								<%if(loginfo.isBalgopal() || loginfo.isSeq_no_gen()){ %>
									<s:property value="bghseqId"/>
								<%}else{ %>
									<%-- <s:property value="invoiceid"/>/<s:property value="bghseqId"/> --%>
									<s:property value="bghseqId"/>
								<%} %>
								</td>
								</s:else>
								
								<td><s:property value="date"/></td>
								<td><s:property value="abrivationid"/></td>
								<td><s:property value="ipdid"/></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="amountx"/></td>
								<td><s:property value="paymentmode"/></td>
								<td><s:property value="userid"/></td>
								<td>
									<%-- <s:if test="itype==1">
										OPD
									</s:if>
									<s:elseif test="itype==2">
										IPD
									</s:elseif>
									<s:elseif test="itype==5">
										ADVANCE
									</s:elseif>
									<s:elseif test="itype==6">
										VACCINATION
									</s:elseif>
									<s:else>
										INVESTIGATION
									</s:else> --%>
									<s:property value="invoicetype"/>
								</td>
								
					     	</tr>
					   </s:iterator>
					   <tr style="color: green"><td></td><td>Total :</td><td></td><td></td><td></td><td colspan="4"><s:property value="totalReceived"/></td></tr>
					</tbody>
			 </table>
		</div>
</div>
<table class="my-table tablecancelinvoice" id = "cancelinvoiceReportTable " style="width: 100%;font-size: 8px;display:none;">    
            <thead>
		    <tr>
							   
								<th>Sr. no</th>
								<th>Id</th>
								<th>Charge Invoiceid</th>
								<th>Date</th>
								<th>UID</th>
						 		<th>Patient Name</th>
						 		<th>Payment</th>
								<th>Paymode</th>
								<th>Userid</th>
								<th>Type</th>
								
							</tr>
		    </thead>
             <tbody>
             
            <% i=0; %>
            <s:iterator value="paymentreportlist">
					   		<tr>
					     		<td><%=++i %></td>
								<td><s:property value="id"/></td>
								<td><s:property value="invoiceid"/></td>
								<td><s:property value="date"/></td>
								<td><s:property value="abrivationid"/></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="amountx"/></td>
								<td><s:property value="paymentmode"/></td>
								<td><s:property value="userid"/></td>
								<td>
									<s:if test="itype==1">
										OPD
									</s:if>
									<s:elseif test="itype==2">
										IPD
									</s:elseif>
									<s:else>
										INVESTIGATION
									</s:else>
								</td>
								
					     	</tr>
					   </s:iterator>
</table>

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



	