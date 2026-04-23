<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@taglib uri="/struts-tags" prefix="s"%>

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
					name: "Payment Report",
					filename: "paymentReport",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});

   }


	$(document).ready(function() {
		
		$("#fromDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDate").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true
		});
	});
	
	
	function setSorting(column,order){
		if(order=='asc'){
			order = 'desc';
		}else{
			order = 'asc';
		}
		document.getElementById('orderby').value = column;
		document.getElementById('order').value = order;
		document.getElementById('paymentreportfrm').submit();
	}
	
	function setActionForAll(){
		
		document.getElementById('orderby').value = 'date';
		document.getElementById('paymentreportfrm').submit();
	}
	
	  bkLib.onDomLoaded(function() {
	        
	      	 new nicEditor().panelInstance('paymentReportEmailBody');
	      	 $('.nicEdit-panelContain').parent().width('500px');
	      	 $('.nicEdit-panelContain').parent().next().width('500px');
	      	 
	      	 $('.nicEdit-main').width('100%');
	      	 $('.nicEdit-main').height('80px');
	    });


	
	
</script>




 <style>
 .thui{
 height: 25px;
 text-align: center;
 font-weight: bold;
 color: white;
 }
 .thui td{
font-size: 15px;
 }
 .ll td{
 text-align: center;
 }
 .ll th{
 text-align: center;
 }
 @MEDIA print {
	.logoste1{
	width: 70% !important;
	}
}
</style>
 					
						
						
						
						<div class="reportprint">
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
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Payment Report Summary</h4>
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											<s:form action="ipdsmallpaymentReportChargesRpt" id="paymentreportfrm" theme="simple">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>

	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline">
			
			<div class="form-group">
				<%if(loginfo.getJobTitle().equals("Admin")){%>
				<s:select name="invoicecategory" id="invoicecategory" 
					list="#{'2':'All Category','0':'Primary','1':'Secondary' }"
					cssClass="form-control"></s:select>
				<%} %>
			</div>
			<div class="form-group">
				<s:select name="payby" id="payby" 
				list="#{'0':'All Paid By','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control" ></s:select>
			</div>
			<div class="form-group">
				<%-- <s:select name="howpaid" id="howpaid" 
				list="#{'0':'All Payment','BACS':'BACS','Cheque':'Cheque','C/Card':'C/Card','Cash':'Cash','D/D':'D/D','Other':'Other','S/O':'S/O','prepayment':'Pre-Payment'}"
				cssClass="form-control" ></s:select> --%>
				<s:select name="howpaid" id="howpaid" 
				list="#{'0':'Show All Payment','Cash':'Cash','Cheque':'Cheque','NEFT':'NEFT','D/Card':'D/Card','UPI':'UPI'}"
				cssClass="form-control" ></s:select>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
			</div>
			<%if(loginfo.isAyushman() || loginfo.isParihar()){%>
			<div class="form-group" style="width:7%;">
                                    	 <div class="form-inline hhmm">
                                   <label for="exampleInputName2">HH:MM</label><br>
									  <div class="form-group">
									    <s:select  name="fromhour" id="fromhour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select  name="fromminute" id="fromminute" list="minuteList" cssClass="form-control mmwidthmang" headerKey="0" headerValue="Select"/>
									     
									  </div>
									</div>
                                    </div>
                                    <%} %>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
			</div>
			<%if(loginfo.isAyushman() ||  loginfo.isParihar()){%>
			<div class="form-group" style="width:7%;">
                                    	 <div class="form-inline hhmm">
                                   <label for="exampleInputName2">HH:MM</label><br>
									  <div class="form-group">
									    <s:select  name="hour" id="hour" list="hourList" cssClass="form-control" headerKey="00" headerValue="00"/>
									  </div>
									  <div class="form-group hidden-xs">
									    :
									  </div>
									  <div class="form-group">
									     <s:select  name="minute" id="minute" list="minuteList" cssClass="form-control mmwidthmang" headerKey="0" headerValue="Select"/>
									     
									  </div>
									</div>
                                    </div>
                                    <%} %>
			<%-- <div class="form-group" >
				<s:select name="searchuserid" id="userid" 
				list="userProfileList"  listKey="userid" listValue="userid" headerKey="0" headerValue="Select All/User" 
				cssClass="form-control chosen-select" ></s:select>
			</div> --%>
			<div class="form-group">
				<%if(loginfo.getJobTitle().equals("Admin")||loginfo.isPaymentReport() || loginfo.getJobTitle().equals("Reception")){%>
				<s:select list="userProfileList" theme="simple" name="userid" listKey="userid" listValue="userid" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" ></s:select>
			<%}else{ %>
				<s:select disabled="true" list="userProfileList" theme="simple" name="userid" listKey="userid" listValue="userid" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" ></s:select>
			<%} %>
			</div>
			<%-- <div class="form-group hidden">
					<s:select list="invoiceTypeLis" theme="simple" name="selectedmodality" listKey="id" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="Select Invoice Type" ></s:select>
			</div> --%>
			
			<div class="form-group">
				<input type="button" value="Go"  class="btn btn-primary" onclick="setActionForAll()">
				<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
				<a type="button" title="Save as PDF" onclick="return saveAsPdfPaymentReport();" class="btn btn-primary"><i class="fa fa-file-pdf-o"></i></a>
				<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
			</div>
		</div>
	
		</div>
		
		<div class="row hidden-print">
		
		
	 	<div class="col-lg-1 col-md-1" style="display: none" id = "previewPaymentRpt">
		<!-- 	<a href="liveData/chargesReport/PaymentReport.pdf" class="btn btn-primary" target = "blank">Preview</a> -->
			<input style="margin-top: 21px;" type="button" value="Send PDF in Mail" onclick="return openSendMailPaymentRptPopup();" class="btn btn-primary">
		</div>	 
		</div>
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 topback2" style="text-align: center;padding: 4px 0px 9px 0px;background-color: rgba(217, 237, 247, ">
		<h3 class="text-center hidden-lg hidden-md hidden-sm visible-print" style="border-bottom: 4px double #ddd;line-height: 38px;margin:0px;"></h3>
		
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" style="border-right: 1px solid #ddd;">
				<h4 ><span class="titsetas">Discount Requested Pending:-</span> <span><%=Constants.getCurrency(loginfo) %><s:property value="requestdiscamt"/></span></h4>
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" style="border-right: 1px solid #ddd;">
				<h4 ><span class="titsetas">Discount Approved Pending:-</span> <span><%=Constants.getCurrency(loginfo) %><s:property value="approvediscamt"/></span></h4>
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" style="border-right: 1px solid #ddd;">
				<h4 style="margin-top:0px;margin-bottom:0px;color: green;"><span class="titsetas">Cash Collected :-</span> 
				<span><%=Constants.getCurrency(loginfo) %><s:property value="totalcashcollect"/></span>
				</h4>
			</div>
			
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
				<h4 style="margin-top:0px;margin-bottom:0px;color: green;"><span class="titsetas">Total :-<!-- <a href="#" data-toggle="collapse" data-target="#detailview"><i class="fa fa-arrow-down" style="color: chocolate;" aria-hidden="true"></i></a> --></span> 
				<span><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx"/></span>
				- <span><%=Constants.getCurrency(loginfo) %><s:property value="totalref"/></span>
				= <span><%=Constants.getCurrency(loginfo) %><s:property value="calcdebittotal"/></span>
				</h4>
			</div>
		
		</div>
		
		<!-- <div id="detailview" class="collapse"> -->
		<div>
			<div class="col-lg-12 col-md-12 col-xs-12 topback2" style="padding: 0px 0px 9px 0px;">
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
				<p style="margin: 0px;">
						<span>Cash </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="cashtotal"/></span>
					</p>
				</div>
				
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
					<%-- <p style="margin: 0px;">
						<span>D/Card </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="crtotal"/></span>
					</p> --%>
					<p style="margin: 0px;">
						<%-- <span>C/Card </span> --%>
						<span>Card </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="drtotal"/></span>
					</p>
				</div>
				
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
					<p style="margin: 0px;">
						<span>Cheque </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="cheqtotal"/></span>
					</p>
				</div>
				
			
				
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
					<p style="margin: 0px;">
						<span>NEFT </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="nefttotal"/></span>
					</p>
					
				</div>
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-right: 1px solid #ddd;">
					<p style="margin: 0px;">
						<span>UPI </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="upitotal"/></span>
					</p>
					
				</div>
				
			<%-- 	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<p style="margin: 0px;">
						<span>Other </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="othertotal"/></span>
					</p>
					<s:iterator value="advrefpaywiselist">
						<p style="margin: 0px;"><span><s:property value="paymentmode"/> </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx"/></span></p>
					</s:iterator>
					<p style="margin: 0px;"><span>Ref</span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="totalref"/></span></p>
				</div>
				 --%>
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-left: 1px solid #ddd;">
				<s:iterator value="advrefpaywiselist">
						<p style="margin: 0px;"><span><s:property value="paymentmode"/> </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="payAmountx"/></span></p>
					</s:iterator>
					<p style="margin: 0px;"><span>Refund</span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="totalref"/></span></p>
					</div>
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="border-left: 1px solid #ddd;">
					<p style="margin: 0px;"><span>Advance</span>
					<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="advreftotal"/></span></p>
					<%-- <p style="margin: 0px;">
						<span>Cash </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="cashtotal"/></span>
					</p>
					<p style="margin: 0px;">
						<span>Cheque </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="cheqtotal"/></span>
					</p>
					
					<p style="margin: 0px;">
						<span>D/Card </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="crtotal"/></span>
					</p>
					<p style="margin: 0px;">
						<span>C/Card </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="drtotal"/></span>
					</p>
					<p style="margin: 0px;">
						<span>NEFT </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="nefttotal"/></span>
					</p>
					<p style="margin: 0px;">
						<span>Other </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="othertotal"/></span>
					</p>
					<p style="margin: 0px;">
						<span>Refund </span>
						<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="totalref"/></span>
					</p> --%>
					<% if(loginfo.isAyushman()) {%>
					<p style="margin: 0px;"><span>Discount</span>
					<span style="float: right;"><%=Constants.getCurrency(loginfo)%><s:property value="Totaldiscamt"/></span></p>
					<%} %>
				</div><br>
				
			</div>
		</div> 
		
		
	
				<table id='smallpt' class="my-table xlstable" style="width: 100%" >				   
				  
				<s:iterator value="IpdmainsmallPaymentReportList">
				  <thead>
				      	<tr class='thui'  style="background-color: orange;height: 25px;"><td colspan="22" style="text-align: center;"><b><s:property value="Invoicetype"/></b></td></tr>
				     <s:iterator value="IpdsmallPaymentinnerlist">
						<td><h4><b><s:property value="Masterchargetype" /></b></h4></td>			
					
						<tr style="background: #6699cc;color: white;">
							<td>Receipt No.</td>
							<td>Invoice No.</td>
							<td>Type</td> 
							 
							<td>User</td>  
							
							<td>UHID</td>
							<td>Patient Name</td>
							<td>Patient Type</td>
							
							<td>Date <a href="#" onclick="setSorting('date','<s:property value="order"/>')"><i class="fa fa-sort hidden-print"></i></a></td>
							<td>Payment Mode <a href="#" onclick="setSorting('paymode','<s:property value="order"/>')"><i class="fa fa-sort hidden-print"></i></a></td>
							<td>Amount Paid</td>
						    <% if(loginfo.isAyushman() || loginfo.isMatrusevasang() || loginfo.isBalgopal()) {%>
							<td>Service Name</td>
							<td>department Name</td>
							<td>session/total session</td>
							<%} %>
							<!-- <td>Userid</td> -->
							
						</tr>
						<%String line=""; %>
					
					<tbody>
					<s:if test="IpdsmallPaymentReportListCollection.size!=0">
					
					<s:iterator value="IpdsmallPaymentReportListCollection" >					
						<%-- <s:if test="paymentList.size!=0"> --%>
						
						
							
							<%-- <s:if test="cancelsts=='1'">
							<% line="text-decoration: line-through;"; %>
							</s:if> --%>
							
								<tr >
									<td><s:property value="newsr" /><%if(loginfo.isSeq_no_gen()&&(loginfo.getClinicUserid().equals("aureus")||loginfo.getClinicUserid().equals("nelson"))){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									
									<%-- <s:if test="whoPay=='Client'">
										<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="invoicee" /> (Self)</td>
										</s:if>
										<s:else>
										<td><s:property value="invoicee" /> (Self)</td>
										</s:else>
									</s:if>
									<s:else>
										<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="invoicee" /> (Third Party)</td>
										</s:if>
										<s:else>
										<td><s:property value="invoicee" /> (Third Party)</td>
										</s:else>
									</s:else> --%>
									
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"> <%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="invoiceid"/> <%} %></td>
									</s:if>
									<s:else>
										<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/><%}else{%><s:property value="invoiceid"/> <%} %></td>
									</s:else>
									<td><s:property value="invoicenameid"/></td>
									<td><s:property value="userid"/></td>
									<td><s:property value="abrivationid"/></td>
									<s:if test="cancelsts==1">
										<td style="text-decoration: line-through;"><s:property value="ClientName" />&nbsp;</td>
									</s:if>
									<s:else>
										<td><s:property value="ClientName" />&nbsp;</td>
									</s:else>
									
									
									<s:if test="whoPay=='Client'">
										<s:if test="cancelsts==1">
											<td style="text-decoration: line-through;">Self</td>
										</s:if>
										<s:else>
											<td>Self</td>
										</s:else>
									</s:if>
									<s:else>
										<s:if test="cancelsts==1">
											<td style="text-decoration: line-through;">TP</td>
										</s:if>
										<s:else>
											<td>TP</td>
										</s:else>
									</s:else>
									
									<s:if test="cancelsts==1">
									<td style="text-decoration: line-through;"><s:property value="date" /></td>
									</s:if>
									<s:else>
									<td><s:property value="date" /></td>
									</s:else>
									<td><s:property value="paymentmode" /><s:if test="paymentNote!=''"> (<s:property value="paymentNote"/>)</s:if></td>
									<s:if test="cancelsts==1">
									<td style="text-decoration: line-through;"><%=Constants.getCurrency(loginfo) %><s:property value="amountx" /> [<s:property value="Invoiceid"/>]</td>
									</s:if>
									<s:else>
									<td><%=Constants.getCurrency(loginfo) %><s:property value="amountx" /></td>
									</s:else>
									<% if(loginfo.isAyushman() || loginfo.isMatrusevasang() || loginfo.isBalgopal()) {%>
									<td><s:property value="AppointmentType" /></td>
									<td><s:property value="Chargename" /></td>
									<td><s:property value="session" />/<s:property value="Totalsession" /></td>
									<%} %>
									
									
									<%-- <td><s:property value="userid" /></td> --%>
									
									</tr>
							</s:iterator>				
						 </s:if>
						 <tr style="background-color: #009cd2;color: white;"><td colspan="22" style="text-align: center;"><b>Total Amount : <%=Constants.getCurrency(loginfo)%><s:property value="amount"/></b></td></tr>
					</s:iterator>
			     </thead>
			 </s:iterator>
		 </tbody>				
	</table>	
						
								<!-- Advance List -->
							<%-- <s:if test="advanceInvoiceList.size!=0">
							<tr class='thui' style="background-color: orange;color: white;height: 25px;">
								<td colspan="12" style="text-align: center;">Advance Payment</td>
							</tr>
							<s:iterator value="advanceInvoiceList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td><s:property  value="advno" /></td>
									<td><s:property  value="bghseqId" /></td>
									<td><!--InvoiceId  --></td><td>Advance</td><td><s:property  value="userid" /></td><td><s:property  value="abrivationid" /></td>
									<s:if test="cancelsts==1">
									<td><s:property value="clientName" />(<s:property value="receiptid" />)</td>
									</s:if>
									<s:else>
									<td><s:property value="clientName" /></td>
									</s:else>							
									<td><s:property value="payby" /></td>				
									<td><s:property value="date" /></td>
									<td><s:property value="paymentmode" /><s:if test="remark!=''">(<s:property value='remark'/>)</s:if></td>
									<td><%=Constants.getCurrency(loginfo) %><s:property value="charges" />
									</td>
									<td></td>
									<td><s:property value="userid" /></td>
									
								</tr>
							</s:iterator>
								<tr style="background-color: #009cd2;color: white;"><td colspan="12" style="text-align: center;"><b>Total Amount : <%=Constants.getCurrency(loginfo)%><s:property value="advreftotal"/></b></td></tr>
					
						</s:if> --%>
						
						
								<!-- Advance List -->
							<%-- <s:if test="cancelledInvoiceShifts.size!=0">
							<tr class='thui' style="background-color: orange;color: white;height: 25px;">
								<td colspan="12" style="text-align: center;">Cancelled Invoice Advance Payment</td>
							</tr>
							<s:iterator value="cancelledInvoiceShifts" status="rowstatus">
								<tr id="<s:property  value="id" />">
									<td><s:property  value="advno" /></td>
									<td><s:property  value="id" /></td>
									<td><!--InvoiceId  --></td><td>Advance</td><td><s:property  value="userid" /></td><td><s:property  value="abrivationid" /></td>
									<s:if test="cancelsts==1">
									<td><s:property value="clientName" />(<s:property value="receiptid" />)</td>
									</s:if>
									<s:else>
									<td><s:property value="clientName" /></td>
									</s:else>							
									<td><s:property value="payby" /></td>				
									<td><s:property value="date" /></td>
									<td><s:property value="paymentmode" /><s:if test="remark!=''">(<s:property value='remark'/>)</s:if></td>
									<td><%=Constants.getCurrency(loginfo) %><s:property value="charges" />
									</td>
									<td></td>
									<td><s:property value="userid" /></td>
									
								</tr>
							</s:iterator>
								<tr style="background-color: #009cd2;color: white;"><td colspan="12" style="text-align: center;"><b>Total Amount : <%=Constants.getCurrency(loginfo)%><s:property value="cancelInvShiftTotal"/></b></td></tr>
					
						</s:if> --%>
						<%-- 	<%if(!loginfo.isBalgopal()){ %>
							<s:if test="refundPaymentList.size!=0">
							<tr class='thui'  style="background-color: orange;">
								<td colspan="12">Refund Payment</td>
							</tr>
							<s:iterator value="refundPaymentList" status="rowstatus">
								<tr id="<s:property  value="id" />">
								<td><s:property  value="id" /></td>
								<td></td>
								<td>Refund</td>
								<td><s:property value="userid"/></td>
									<td><s:property  value="abrivationid" /> </td>
									<td><s:property value="clientName" /></td>	
									<td><s:property value="payby" /></td>
									<td><s:property value="date" /></td>	
									<td><s:property value="paymentmode" /></td>
									<td><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx" /></td>
									<td></td>
									<td><s:property value="userid"/></td>
								</tr>
									
									

							</s:iterator>
							<tr style="background-color: #009cd2;color: white;"><td colspan="12" style="text-align: center;"><b>Total Amount : <%=Constants.getCurrency(loginfo)%><s:property value="ammount"/></b></td></tr>
					
						</s:if>
						<%}else{ %> --%>
						<%-- <s:if test="opdRefundList.size!=0">
							<tr style="background-color: orange;" class='thui' >
								<td colspan="12">OPD Refund Payment</td>
							</tr>
							<s:iterator value="opdRefundList" status="rowstatus">
								<tr id="<s:property  value="id" />">
								<td><%if(loginfo.isBalgopal() || loginfo.isSeq_no_gen()){ %>(<s:property  value="bghseqId"  />)<%} %><s:property  value="id" /><%if(loginfo.isSeq_no_gen()){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									<td><s:property value="refid"/></td>
									<td></td>
									<td>Opd Refund</td>
									<td><s:property value="userid" /></td>
									<td><s:property value="abrivationid" /></td>
									<td><s:property value="clientName" /></td>	
									
									<td><s:property value="payby" /></td>
									<td><s:property value="date" /></td>
									
									
									<td><s:property value="clientName" /> (Self)</td>	
								
									
									<td><s:property value="Paymentmode" /></td>
									
									<td ><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx" /></td>
									<td></td>
									<td></td>
									<!--<td><s:property value="userid"/></td>
									
								--></tr>
									
									

							</s:iterator>
							<tr style="background-color: #009cd2;color: white;"><td colspan="12" style="text-align: center;"><b>Total Amount : <%=Constants.getCurrency(loginfo)%><s:property value="opdref"/></b></td></tr>
					
						</s:if> --%>
						
						<!-- IPD REFUND -->
						<%-- <s:if test="ipdRefundList.size!=0">
							<tr class='thui'  style="background-color: orange;">
								<td colspan="14">IPD Refund Payment</td>
							</tr>
							<s:iterator value="ipdRefundList" status="rowstatus">
								<tr id="<s:property  value="id" />">
								<td><%if(loginfo.isBalgopal() || loginfo.isSeq_no_gen()){ %>(<s:property  value="bghseqId"  />)<%} %><s:property  value="id" /><%if(loginfo.isSeq_no_gen()){%>(<s:property value='physical_payment_id'/>)<%} %></td>
									<td><s:property value="refid"/></td>
									<td></td>
									<td>Ipd Refund</td>
									<td><s:property value="userid" /></td>
									<td><s:property value="abrivationid" /></td>
									<td><s:property value="clientName" /></td>	
									<td><s:property value="payby" /></td>
									
									<td><s:property value="date" /></td>
									
									
									<td><s:property value="clientName" /> (Self)</td>	
								
									
									<td><s:property value="Paymentmode" /></td>
									
									
									<td ><%=Constants.getCurrency(loginfo) %><s:property value="debitTotalx" /></td>
									<td></td>
									
									<!--<td><s:property value="userid"/></td>
									
								--></tr>
									
									

							</s:iterator>
							<tr style="background-color: #009cd2;color: white;"><td colspan="12" style="text-align: center;"><b>Total Amount : <%=Constants.getCurrency(loginfo)%><s:property value="ipdref"/></b></td></tr>
					
						</s:if> --%>
						
						
							<%-- <%} %> --%>
						
						
					</tbody>
					
							<%-- <tr style="background-color: rgba(239, 239, 239, 0.26);color: green;">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>Total</td>
								<td></td>
								<td style="font-weight: 750;"><%=Constants.getCurrency(loginfo) %><s:property value="total"/></td>
								
								
							</tr> --%>
							
							
		<%-- <s:if test="selectedmodality==2||selectedmodality==''||selectedmodality==0">					
						<s:if test="creditBalanceReportList.size>0">
			<tr  class='thui' >
				<td colspan="12" style="background-color: #f57272;color: white;font-weight: 800;">Credit Amount</td>
				</tr>
				<tr style="background-color: #2B1B7E;color: white;">
				<td style="text-align: center;">Invoice No</td>
				<td style="text-align: center;">Invoice Date</td>
				<td style="text-align: center;">Patient</td>
				<td style="text-align: right;">Debit</td>
				<td style="text-align: right;">Credit</td>
				<td style="text-align: right;">Discount</td>
				<td style="text-align: right;">Balance</td>
				<td style="text-align: center;"></td>
				<td style="text-align: center;">Payee</td>
				<td style="text-align: center;">User Id</td>
				
				<td style="text-align: center;">Status</td>
				<td ></td>
				
				</tr>
				<s:iterator value="creditBalanceReportList" status="rowstatus">
				<tr>
					<td style="text-align: center;"><s:property value="invoiceid"/>(<s:property value="invoicetype"/>)</td>
					<td style="text-align: center;"><s:property value="invdate"/></td>
					<td style="text-align: center;"><s:property value="clientName"/></td>
					<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="debitAmount" /></td>
					<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="creditAmount" /></td>
					<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="discount"/>
					<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="balance" /></td> 
					<td style="text-align: center;"></td>	
				<td style="text-align: center;"><s:if test="payby=='Client'">Self</s:if><s:else>Third party</s:else></td> 	
					<td style="text-align: center;"><s:property value="userid"/></td>
				<td >Unpaid</td>
				<td ></td>
				
								</tr>
				</s:iterator>
				<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td style="text-align: right;font-weight: bold;">Total</td>
				<td style="text-align: right;font-weight: bold;color: green;"><s:property value="totalcredit"/></td>
				<td></td>
				<td></td>
				<td ></td>
				<td ></td>
				<td ></td>
				</tr>
				</s:if>
				
					<s:if test="creditInvoiceReportList.size>0">
			<tr  class='thui' >
				<td colspan="12" style="background-color: #f57272;color: white;font-weight: 800;">Credit Invoices</td>
				</tr>
				<tr style="background-color: #2B1B7E;color: white;">
				<td style="text-align: center;">Invoice No</td>
				<td style="text-align: center;">Invoice Date</td>
				<td style="text-align: center;">Patient</td>
				<td style="text-align: right;">Debit</td>
				<td style="text-align: right;">Credit</td>
				<td style="text-align: right;">Discount</td>
				<td style="text-align: right;">Balance</td>
				<td style="text-align: center;"></td>
				<td style="text-align: center;">Payee</td>
				<td style="text-align: center;">User Id</td>
				
				<td style="text-align: center;">Status</td>
				<td ></td>
				
				</tr>
				<s:iterator value="creditInvoiceReportList" status="rowstatus">
				<tr>
					<td style="text-align: center;"><s:property value="bghseqId"/><s:property value="invoiceid"/>(<s:property value="invoicetype"/>)</td>
					<td style="text-align: center;"><s:property value="invdate"/></td>
					<td style="text-align: center;"><s:property value="clientName"/></td>
					<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="debitAmount" /></td>
					<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="creditAmount" /></td>
					<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="discount"/>
					<td style="text-align: right;"><%=Constants.getCurrency(loginfo) %><s:property value="balance" /></td> 
					<td style="text-align: center;"></td>	
				<td style="text-align: center;"><s:if test="payby=='Client'">Self</s:if><s:else>Third party</s:else></td> 	
					<td style="text-align: center;"><s:property value="userid"/></td>
				<td >Unpaid</td>
				<td ></td>
				
								</tr>
				</s:iterator>
				<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td style="text-align: right;font-weight: bold;">Total</td>
				<td style="text-align: right;font-weight: bold;color: green;"><s:property value="totalcr"/></td>
				<td></td>
				<td></td>
				<td ></td>
				<td ></td>
				<td ></td>
				</tr>
				</s:if>
				</s:if> --%>
				
				<%-- </s:iterator>
				</table> --%>
				
			<%-- 	<s:if test="selectedmodality==2||selectedmodality==''||selectedmodality==0">
				<%if(loginfo.isBalgopal() || loginfo.isAyushman()){ %>
				<s:if test="invoiceList.size!=0">
							<table class="my-table xlstable ll" style="width: 100%">
					<col width="5%">
					<col width="5%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					
						<col width="7%">
					<thead>
					<tr  class='thui' >
				<td colspan="13" style="background-color: #10b5c5;color: white;font-weight: 800;">Zero Payment Inovice List</td>
				</tr>
						<tr >
							
							<th>Invoice No.</th>	
							<th >Invoice Date </th>
							<th>UHID</th>
							<th>Patient</th>
									<th >Debit</th>
							<th >Credit</th>
							<th >Discount</th>
							<th >Balance</th>
							<s:if test="payby=='Client'">
							<th>Payee </th>
							</s:if>
							<s:else>
								<th>Payee </th>
							</s:else>
							<th>Userid</th>
							<th>Status</th>
							<th>Service Name</th>
					
							
						</tr>
					</thead>
					<tbody>
						<s:if test="invoiceList.size!=0">
							<s:iterator value="invoiceList" status="rowstatus">
								<tr >
									<td><%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/> <%}else{%> <s:property value="id" /><%} %></td>
							
									<td>  <s:property value="date" /></td>
										<td><s:property value="id" /><a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a></td>
										
									<td><s:property value="abrivationid" /></td>	 
									<td><s:property value="clientName" /></td>		
										<td><%=Constants.getCurrency(loginfo) %><s:property value="debitAmountx" /></td>
									<td ><%=Constants.getCurrency(loginfo) %><s:property value="creditTotalx" /></td>
									<td >(<%=Constants.getCurrency(loginfo) %><s:property value="discAmmount"/>) <s:property value="discount" />%</td>
									<td ><%=Constants.getCurrency(loginfo) %><s:property value="balancex" /></td> 
									
									<td>
										<s:property value="payby" /> (<s:property value="payeeName"/>)
										<s:if test="payby=='Client'">
											Self
										</s:if>
										<s:else>
											Third Party
										</s:else>
									</td>
										<td><s:property value="userid"/>  </td>
									<s:if test="balance==0">
										<td>Paid  </td>
									</s:if>

									<s:else>
										
										<td>Not Paid</td>

									</s:else>
									<% if(loginfo.isAyushman() || loginfo.isMatrusevasang()) {%>
									<td><s:property value="AppointmentType" /></td>
									<%} %>
									
								
								<tr id="hiddenDetailsDiv<s:property value="id"/>"
									style="display: none" aria-hidden="true">
									<td colspan="7" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr>

							</s:iterator>
						</s:if>
					</tbody>

				</table>
						</s:if>
	<%} %>
	</s:if>	 --%>	
	<h5>
	Printed By: <s:property value="userid"/></h5>
	<h5>Print On: </h5>

</s:form>

<!-- Modal Email-->
<div class="modal fade" id="sendEmailPaymentRptPopup" tabindex="-1" role="dialog"
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
							<s:textfield theme="simple" id = "paymentReportEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "paymentReportccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "paymentReportSubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="paymentReportEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="pdfPaymentReportMailId" style="display: none;">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary"  onclick="sendPaymentReportMail();">Send</button>
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
 
 

<script>
     $(document).ready(function() {
    var table = $('#smallpt').DataTable( {
        lengthChange: false,
        buttons: [ 'excel', 'colvis','print' ]
    } );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
} );
    </script>



	<script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.print.js"></script>
	



	

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