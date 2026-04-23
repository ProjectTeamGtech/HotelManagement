<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="java.util.Date"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="accounts/js/chargeAccountProcessing.js"></script>
<script type="text/javascript" src="report/js/chargesReport.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script>


    function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Payment Receipt Report",
					filename: "Payment_Receipt_Report",
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
<style>
        
        .akashclass{
background-color: #6699cc !important; 
color: white;
}
</style>


						<div class="">
							<div class="">
							<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 96px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Payment Receipt Report</h4>

									</div>
								</div>
								<br>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<s:form action="paymentreciptreportSummary" id="invoicerportfrm" theme="simple">

<s:hidden name="hdnprimaryinvoice" id="hdnprimaryinvoice"/>

<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline">
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" palceholder="to date" style="width:100%;"></s:textfield>
			</div>
			<div class="form-group">
				<s:select name="howpaid" id="howpaid" 
				list="#{'0':'Show All Payment','Cash':'Cash','Cheque':'Cheque','NEFT':'NEFT','D/Card':'D/Card'}"
				cssClass="form-control" ></s:select>
			</div>
			<div class="form-group">
            	<s:select list="invoicetypelist" cssClass="form-control chosen-select" name="itype" id="itype" listKey="id" listValue="name" headerKey="0" headerValue="Select invoive Type"></s:select>
            </div>
			<div class="form-group">
				<s:select  name="paymentStatus" id="paymentStatus" 
				list="#{'0':'Show All Payment','Paid':'Paid','Not Paid':'Not Paid'}"
				cssClass="form-control chosen-select" ></s:select>
			</div>
			<div class="form-group">
				<s:select name="amountgreaterfilter" id="amountgreaterfilter" 
				list="#{'0':'Show All','200000':'amount>200000'}"
				cssClass="form-control" ></s:select>
			</div>
			<div class="form-group">
				<input type="submit" value="Go"  class="btn btn-primary">
				<a type="button" class="btn btn-primary" title="Print" onclick="printinvreport()"><i class="fa fa-print"></i></a>
				
				<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
			</div>
		</div>
	</div>
		
	<div class="row hidden-print">
		<div class="col-lg-1 col-md-1" style="display: none" id = "previewInvoiceRpt">
		</div>
	</div>
			
		
		
		
				<table class="my-table xlstable" style="width: 100%">
				<div class="col-lg-12 col-md-12 ">
				   <div class="col-lg-4 col-md-4">
				      <h2>Total balance:<b><s:property value="Balanceamttotal" /></b></h2>
				   </div>
				   
				   <div class="col-lg-4 col-md-4 hidden">
				      <h2>Total paid:<b><s:property value="TotalPaidAmt" /></b></h2>
				   </div>
				   
				   <div class="col-lg-4 col-md-4">
				      <h2>Total :<b><s:property value="Totalamt" /></b>-
				      <b><s:property value="Totalrefundamount" /></b>=<b><s:property value="Calcdebittotal" /></b></h2>
				   </div>
				   <div class="col-lg-4 col-md-4 hidden">
				      <h2>Total Invoice Amt:<b><s:property value="Invstamount" /></b></h2>
				   </div>
				   
				   <div class="col-lg-4 col-md-4">
				      <h2>Total Discount Amt:<b><s:property value="Totaldiscountammount" /></b></h2>
				   </div>
				
				  <div class="col-lg-4 col-md-4">
				      <h2>Cash:<b><s:property value="TotalCash" /></b></h2>
				   </div>
				   
				   <div class="col-lg-4 col-md-4">
				      <h2>Advance:<b><s:property value="AdvanceAmt" /></b></h2>
				   </div>
				   
				   <div class="col-lg-4 col-md-4">
				      <h2>Refund:<b><s:property value="Totalrefundamount" /></b></h2>
				   </div>
				   
				   <div class="col-lg-4 col-md-4">
				      <h2>Upi:<b><s:property value="TotalUpi" /></b></h2>
				   </div>
				</div>
					<col width="10%">
					<col width="7%">
					<col width="15%">
					<col width="30%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<thead>
						<tr class="akashclass">
							<td id="datesortdid">
								
							Invoice Date </td>
							<td>Invoice</td>
							<td>Patient</td>
							<td>Payee </td>
							<td>Status</td>
							<td style="text-align: right;">Debit</td>
							<td style="text-align: right;">Credit</td>
							<td style="text-align: right;">Discount</td>
							<td style="text-align: right;">Balance</td>
							
						</tr>
					</thead>
					<tbody>
						<s:if test="invoiceList.size!=0">
							<s:iterator value="invoiceList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td>  <s:property value="date" /></td>
									<td>
										<%if(loginfo.isSeq_no_gen()){%>
											<a href="#" onclick="openBlankPopup('viewInvoiceCharges?invoiceid=<s:property value="id" />&action=show&discount=<s:property value="newdiscount" />&payby=<s:property value="payby" />&billsummary=1&paymentreciptreport=1&reportclintid=<s:property value="clientid" />')"  ><s:property value="bghseqId"/> </a>
										<%}else{%> 
											<a href="#" onclick="openBlankPopup('viewInvoiceCharges?invoiceid=<s:property value="id" />&action=show&discount=<s:property value="newdiscount" />&payby=<s:property value="payby" />&billsummary=1&paymentreciptreport=1&reportclintid=<s:property value="clientid" />')"  ><s:property value="id" /> </a>
										<%} %>
									</td>
								
									<td><s:property value="clientName" /></td>		
									<td><s:property value="payby" /> (<s:property value="payeeName"/>)</td>
									<s:if test="balance==0">
										<td>Paid</td>
									</s:if>

									<s:else>
										
										<td>Not Paid</td>

									</s:else>
									
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="debitAmountx" /></td>
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="creditTotalx" /></td>
									<td style="text-align: right;">(<%=Constants.getCurrency(loginfo) %><s:property value="discAmmount"/>) <s:property value="discount" />%</td>
									<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="balancex" /></td> 
									</tr>
								<s:if test="prepaymentList.size!=0">
									<s:iterator value="prepaymentList">
										<tr>
											<td ></td>
											<td><s:property value="date"/></td>
											<s:if test="advref==0">
												<td>Receipt - (R.<s:property value="id"/>)(<s:property value="physical_payment_id"/>)</td>
											</s:if>
											<s:else>
												<td>Receipt - (RF.<s:property value="id"/>)(<s:property value="physical_payment_id"/>)</td>
											</s:else>
											<%-- <td><s:property value="note"/></td> --%>
											<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
											
											<s:if test="advref==0">
												<td colspan="2">Advance Receipt</td>
											</s:if>
											<s:else>
												<td colspan="2">Refund Receipt</td>
											</s:else>
											<td colspan="4"><s:property value="paymentmode"/></td>
										</tr>
									</s:iterator>
								</s:if>
								
								<s:if test="refundList.size!=0">
									<s:iterator value="refundList">
									<tr>
										<td></td>
										<td><s:property value="date"/></td>
										<td>Receipt - (RF.<s:property value="invoicee"/>)</td>
										<!-- <td></td> -->
										<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
										<td>Refund Receipt</td>
										<td colspan="4"></td>
									</tr>
									</s:iterator>
								</s:if>
								
								<s:iterator value="transactionList">
									<s:if test="paymentmode!='prepayment'">
										<tr>
											<td></td>
											<td><s:property value="fromDate"/></td>
											<td>Receipt - (R.<s:property value="id"/>)(<s:property value="physical_payment_id"/>)</td>
											<%-- <td><s:property value="paymentmode"/></td> --%>
											<td class="text-right"><%=Constants.getCurrency(loginfo)%><s:property value="amountx"/></td>
											<td colspan="2">Payment</td>
											<td colspan="4"><s:property value="paymentmode"/></td>
										</tr>
									</s:if>
								</s:iterator>
								<%-- <tr id="hiddenDetailsDiv<s:property value="id"/>"
									style="display: none" aria-hidden="true">
									<td colspan="7" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr> --%>

							</s:iterator>
						</s:if>
					</tbody>

				</table>
	
</s:form>

<!-- Modal Email-->
<div class="modal fade" id="sendEmailInvoiceRptPopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
					<div class="row">
					<div class="col-lg-1 col-md-1">	
					</div>
				
					</div>
						<div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id = "invoiceReportEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "invoiceReportccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "invoiceReportSubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="invoiceReportEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="pdfInvoiceReportMailId" style="display: none;">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary"  onclick="sendInvoiceReportMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>		
											

											
										</div>
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



