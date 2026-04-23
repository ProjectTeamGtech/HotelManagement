<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="java.util.Date"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script type="text/javascript"
	src="accounts/js/chargeAccountProcessing.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>


<script>
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
	$(function() {
		$("#clientSearchDiv").dialog({
			autoOpen : false,
			resizable : true,
			height : 500,
			width : 650,
			modal : true,
			buttons : {

				Cancel : function() {
					$(this).dialog("close");
				}
			}
		});
		$("#transactionPopup").dialog({
			autoOpen : false,
			resizable : true,
			height : 500,
			width : 650,
			modal : true,
			buttons : {

				Cancel : function() {
					$(this).dialog("close");
				}
			}
		});

		$("#sendEmailPopUp").dialog({
			autoOpen : false,
			resizable : true,
			height : 480,
			width : 560,
			modal : true,
			buttons : {
				"Send" : function() {
					//sendSaveEmail();
					sendPdfMail();

				},
				Cancel : function() {
					$(this).dialog("close");
				}

			}
		});

	});
	
	//document.getElementById('mask').style.display = 'none';

	window.onload = function() {

		var id = document.getElementById('hdnSelectedID').value;
		$(document.getElementById(id)).css('background-color',
				'rgb(243, 215, 242)');

	}
	
	
</script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Hospital Revenue</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	</div>
</div>




<s:form action="hosprevnueProcessingAccount" id="category_form" theme="simple">
	<s:hidden name="hdnSelectedID" id="hdnSelectedID" />
	<div class="col-lg-12 col-md-12 topback2 ">
		<div class="col-lg-2 col-md-2">
			<label>Invoice Type:</label>
			<s:select name="status"
				list="#{'1':'Posted','2':'Closed'}"
				cssClass="form-control"></s:select>
		</div>
		
		<div class="col-lg-2 col-md-2">
			<label>From Date:</label>
			
			<div class="input-group ">
               
               <s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" aria-describedby="basic-addon1"></s:textfield>
             </div>	
		</div>
		<div class="col-lg-2 col-md-2">
			<label>To Date:</label>
			<div class="input-group ">
               
               <s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" aria-describedby="basic-addon1"></s:textfield>
            </div>	
		</div>
		<div class="col-lg-2 col-md-2">
			<label> </label><br />
			<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
		</div>
	</div>
	
	
	<br />
	
	<div class="row">
		<div class="col-lg-12">
		<div class="col-lg-4">
		
		<table class="table-bordered table-hover table-condensed width-full" style="font-weight: bold;font-size: 16px;">
					<tbody>
						<tr>
							<td>Invoice Total :</td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="debitTotalx"/></td>
						</tr>
						<tr>
							<td>Payment Total :</td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx"/></td>
						</tr>
						
						<tr>
							<td>Refund Total :</td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="refundtotalx"/></td>
						</tr>
						
						
					</tbody>
				</table>
			
		</div>
		
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
		<%-- 	<s:actionmessage cssClass="messageDiv" /> --%>
			<div>
				<table class="table-bordered table-hover table-condensed width-full">
					<thead>
						<tr>
						
							<th title="Sort by Date" class="text-center sortable <s:if test="#attr.pagination.sortColumn.equals('date')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
							<a href="#" onclick="fnPagination(6,'date');" style="color:white;"> Date</a></th>
							 <th> Transaction</th>
                              <th> Payee</th>
                              <th> Status</th>
                              <th class="text-right"> Charges</th>
                              <th class="text-right"> Payments</th>
                              <th class="text-right"> Discount</th>
                              <th class="text-right"> Balance</th>
                              
                            
						</tr>
					</thead>
					<tbody>
					
					<%String bgcolor = "rgba(255, 191, 0, 0.56);"; %>
					
						<s:if test="chargeProcessingList.size!=0">
							<s:iterator value="chargeProcessingList" status="rowstatus">
							
							<s:if test="balancex=='0.00'">
								<% bgcolor = "rgba(89, 178, 16, 0.22);"; %>
							</s:if>
							<s:else>
								<% bgcolor = "rgba(255, 191, 0, 0.56);"; %>
							</s:else>
								<tr style="background-color: <%=bgcolor %>" id="<s:property  value="id" />">
									<td><s:property value="date" /></td>
									<s:url action="viewInvoiceCharges" id="viewInvoiceCharges">
										<s:param name="invoiceid" value="%{id}"/>
										<s:param name="action" value="%{'show'}"/>
										<s:param name="discount" value="%{discount}"/>
										<s:param name="payby" value="%{payby}"/>
									</s:url>
									<s:if test="deliverstatus==1">
										<td><s:a href="%{viewInvoiceCharges}"><img src="common/images/Letter-P-blue-icon.png"> Invoice (<s:property value="id" />)</s:a> <a
											href="javascript: void(0);" 
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a>
										</td>
									</s:if>
									<s:elseif test="deliverstatus==2">
										<td><s:a href="%{viewInvoiceCharges}">Invoice <img src="common/images/Letter-E-blue-icon.png"> <br> (<s:property value="id" />)</s:a> <a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a>
										</td>
									</s:elseif>
									<s:else>
										<td><s:a href="%{viewInvoiceCharges}">Invoice <br> (<s:property value="id" />)</s:a> <a
										href="javascript: void(0);"
										onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
											class="fa fa-arrow-down"></i></a>
										</td>
									</s:else>
									<s:if test = "payby == 'Third Party'">
									<td>TP (<s:property value="insuranceCompany" />)</td>
									</s:if>
									<s:else>
									<td><s:property value="payby" /></td>
									</s:else>
									<s:if test="deleted==1">
									 <td>Canceled (<s:property value="notes"/>)</td>
									</s:if>
									
									<td>
									<s:if test="balance == 0">
										Paid |
									</s:if>
									<s:else>
										<s:url action="paymentProcessingAccount"
											id="paymentProcessingAccount">
											<s:param name="payby" value="%{payby}" />
											<s:param name="numberOfChages" value="%{numberOfChages}" />
											<s:param name="creditCharge" value="%{creditCharge}" />
											<s:param name="invoiceid" value="%{id}" />
											<s:param name="debitamt" value="%{debitAmount}" />
											<s:param name="balance" value="%{balance}" />
											<s:param name="discount" value="%{discount}" />
											<s:param name="clientId" value="%{clientId}" />
										</s:url>
										<%-- <s:a href="%{paymentProcessingAccount}">Record Payment </s:a>| --%>

									</s:else>
									<s:if test="creditCharge > 0">
									<a href="#" data-toggle="modal" data-target="#transactionPopup2"
										onclick="showTransactionPopup('<s:property value ="id"/>','<s:property value ="clientId"/>','<s:property value="payby"/>')">View Payments 
											</a>| <a href="#" onclick="showrefundtranspopup('<s:property value ="id"/>')">View Refund</a>
									</s:if>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%> <s:property value="debitAmountx" /></td>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%> <s:property value="creditCharge" /></td>
									
									<td class="text-right">
										<s:if test="disctype==0">
										   <s:property value="discountx" />%
										</s:if>
										<s:else>
											<%=Constants.getCurrency(loginfo)%><s:property value="discamt" />
										</s:else>
									</td>
									<td class="text-right"><%=Constants.getCurrency(loginfo)%> <s:property value="balancex" /></td>
									
								<tr id="hiddenDetailsDiv<s:property value="id"/>"
									style="display: none" aria-hidden="true">
									<td colspan="7" id="hiddenDetailsDiv1<s:property value="id"/>">
									</td>
								</tr>

							</s:iterator>
						</s:if>
					</tbody>

				</table>
			</div>
		</div>
	</div>
</s:form>

<br />







<%-- <s:if test="chargeProcessingList!=null">
	<s:form action="moveProcessingAccount" name="paginationForm"
		id="paginationForm" theme="simple">
		<div class="col-lg-12 col-md-12" style="padding: 0px;">
			<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
				Total:<b><s:property value="totalRecords" />
				</b>
			</div>
			<%@ include file="/common/pages/pagination.jsp"%>
		</div>
		
	</s:form>
</s:if>
 --%>

<table width="50" cellpadding="0" cellspacing="0" align="center">
	<col width="10%">
	<col width="10%">
	<col width="10%">

	<tr>


		<td></td>


	</tr>


</table>
<%-- <div class="row">
	<div class="col-lg-12 text-right">
		<s:form action="printProcessingAccount" target="blank" theme="simple">
			<s:hidden name="clientId" value="%{clientId}" />
			<s:hidden name="client" value="%{client}" />
			<s:hidden name="payby" value="%{payby}" />
			<s:hidden name="fromDate" value="%{fromDate}" />
			<s:hidden name="toDate" value="%{toDate}" />
			<s:hidden name="transactionType" value="%{transactionType}" />
			<input type="submit" class="btn btn-primary" value=" Print ">
			<a class="btn btn-primary" data-toggle="modal"
				data-target="#sendMail2"><i class="fa fa-envelope-o"></i> Send
				Mail</a>
		</s:form>

	</div>
</div> --%>
<br><br>


<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Client Search</h4>
			</div>
			<div class="modal-body">
				<%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>



<!-- Modal Email-->
<div class="modal fade" id="sendMail2" tabindex="-1" role="dialog"
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
						<div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id="email" name="email"
								cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver"
								title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id="ccEmail" name="ccEmail"
								cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name="subject"
								id="subject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject"
								placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip"
								title="Enter Body" name="emailBody" id="emailBody" style="width: 100%"
								></textarea>
						</div>
						<div class="form-group">
						
							<s:property value="filename"/><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger"></i></a> &nbsp; <input type="checkbox"
								name="ispdf" id="ispdf" checked="checked">
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal Cancel Invoice-->
<div class="modal fade" id="resetInvoice" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Cancel Invoice</h4>
			</div>
			<div class="modal-body">
			<s:form theme="simple" action="resetProcessingAccount" id="resetForm">
				<div class="row">
					<div class="col-lg-12">
						
						<div class="form-group">
							<label>Notes <small>(Reason for canceling invoice)</small></label>
							<textarea class="form-control" name="notes" rows="4"></textarea>
						</div>
 
						   <s:hidden name="clientId" id="rclientId" />
						   <s:hidden name="invoiceid" id="rinvoiceid" /> 
						
						<div class="form-group">
							<button type="submit" class="btn btn-primary" style="float: right;">Submit</button>
						
						</div>
						
					</div>
					</s:form>
				</div>
				
			</div>
		</div>
	</div>
</div>

<div class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel2" aria-hidden="true"
	id="transactionPopup2">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Patient Transactions</h4>
			</div>
			<div class="modal-body" id="transactionList1">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel2" aria-hidden="true"
	id="refundtransactions">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Refund Transactions</h4>
			</div>
			
			<div class="modal-body">
			 <table class="table table-responsive">
					<thead>
						<tr>
							<th>transaction ID</th>
							<th>Date</th>
							<th>Payment Mode</th>
							<th>Amount</th>
						</tr>
					</thead>
						<tbody id="refunddatassss">
						</tbody>
					</table> 
			</div>
			<div class="modal-footer">
				
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
						
						





  <!-- Dropdown Modal -->
<s:form action="editdiscProcessingAccount" id="saveemrfrm" theme="simple">
<div class="modal fade modal-draggable" id="editdiscdrop" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Update Discount</h4>
      </div>
      <div class="modal-body">
      	<%-- <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;" class="hidden">
				 <lable>Practitioner Name:</lable>
				<s:select list="discountgivenuserlist" disabled="true" listKey="id" listValue="practitionerName" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" id="discountuserid" ></s:select>
			</div>
		</div> --%>
        <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Select Discount Type</lable>
					<s:select  list="#{'0':'%','1':'Rs.'}" disabled="true" theme="simple" name="disctype" id="disctype" cssClass="form-control" />
			</div>
		</div>
					
        <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Enter Amount</lable>
					<s:textfield name="discval" id="discval" readonly="true" cssClass="form-control" onkeypress="return isNumberKey(event,this);" />
			</div>
		</div>
		<div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Discount Notes</lable>
				<textarea rows="3"  class="form-control" readonly="true" id="discount_notes" placeholder="Note"></textarea>
			</div>
		</div>
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary " onclick="applyaproveddisccount()">Save changes</button>
      </div>
    </div>
  </div>
</div> 

</s:form>

<div class="modal fade modal-draggable" id="requestdiscpopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Request Discount</h4>
      </div>
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Invoice Amount:</lable>
					<span id="requestdiscinvoiceamt"></span>
			</div>
		</div>
		 <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Practitioner Name:</lable>
				<s:select list="discountgivenuserlist" listKey="id" listValue="practitionerName" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" id="discountgivenuserid" ></s:select>
			</div>
		</div>
        <div class="">
        	<s:hidden id="reqdiscinvoiceid"></s:hidden>
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Select Discount Type</lable>
					<s:select  list="#{'0':'%','1':'Rs.'}"  onchange="changedisctype()" theme="simple" name="requestdisctype" id="requestdisctype" cssClass="form-control" />
			</div>
		</div>
					
        <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Enter Amount</lable>
					<s:textfield name="requestdiscval" id="requestdiscval" cssClass="form-control" onkeypress="return isNumberKey(event,this);" />
			</div>
		</div>
		<div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Discount Notes</lable>
					<textarea rows="3"  class="form-control" id="discount_reason" placeholder="Note"></textarea>
			</div>
		</div>
       
      </div>
      <div class="modal-footer" id="reqrefbtndiv">
        <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary " onclick="requestfordiscount()">Request Discount</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade modal-draggable" id="requestrefundpopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Request Refund</h4>
      </div>
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Invoice Amount:</lable>
				 <span id="requestrefundinvoiceamt"></span>
			</div>
		</div>
					
        <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Refund Amount</lable>
				<s:textfield   cssClass="form-control" id="refundamount" readonly="true" onkeypress="return isNumberKey(event,this);" />
			</div>
		</div>
		<div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Refund Notes</lable>
				 <textarea rows="3"  class="form-control" id="refund_reason" placeholder="Note"></textarea>
			</div>
		</div>
       
      </div>
      <div class="modal-footer" id="refreqdisdiv">
        <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary " onclick="refundrequestafterdisc()">Request Refund</button>
      </div>
    </div>
  </div>
</div>



<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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