<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%-- <script type="text/javascript" src="diarymanagement/js/completeApmt.js"></script> --%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.ThirdParties.eu.entity.OutstandingReport"%>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script type="text/javascript"
	src="thirdParties/js/outstandingReport.js"></script>
	
<script type="text/javascript" src="common/js/pagination.js"></script>

<script type="text/javascript">
	$(function() {
		$(document).tooltip();
	});

	$(function() {
		$("#exceedLmtActionPopUp").dialog({
			autoOpen : false,
			resizable : true,
			height : 235,
			width : 200,
			modal : true,
			buttons : {

				Cancel : function() {
					$(this).dialog("close");
				}

			}
		});

	});
</script>

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
	
</script>

<script type="text/javascript">
	 /* bkLib.onDomLoaded(function() { nicEditors.editors.push(
	        new nicEditor().panelInstance(
	                document.getElementById('emailBody')
	                
	            )
	        ); });   */
	        
	        
	        bkLib.onDomLoaded(function() {
	           
	        	 new nicEditor().panelInstance('emailBody');
	        	 $('.nicEdit-panelContain').parent().width('500px');
	        	 $('.nicEdit-panelContain').parent().next().width('500px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px');
	      });
</script>
<script>
	$(function() {
		$("#exceedLmtActionPopUp").dialog({
			autoOpen : false,
			resizable : true,
			height : 235,
			width : 200,
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
			height : 460,
			width : 460,
			modal : true,
			buttons : {
				"Send" : function() {
					sendSaveEmail();

				},
				Cancel : function() {
					$(this).dialog("close");
				}

			}
		});

		$("#sendSMSPopUp").dialog({
			autoOpen : false,
			resizable : true,
			height : 350,
			width : 460,
			modal : true,
			buttons : {
				"Send" : function() {
					sendSaveSMS();

				},
				Cancel : function() {
					$(this).dialog("close");
				}

			}
		});

		$("#makeCallPopUp").dialog({
			autoOpen : false,
			resizable : true,
			height : 350,
			width : 460,
			modal : true,
			buttons : {
				"Save" : function() {
					saveCommunication();

				},
				Cancel : function() {
					$(this).dialog("close");
				}

			}
		});

	});
</script>

<style>
.details {
    background: #339966 !important;
}
</style>
<div class="">
							<div class="">
								
								<div class=" ">
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


<div class="row">
<s:form action="showReportOutstandingReport" id = "outstanding_report_frm" theme="simple">
<div class="col-lg-12 col-md-12 topback2">

<div class="form-inline">
	<div class="form-group">
		<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple"></s:textfield>
	</div>
	<div class="form-group" style="color:#000;">
	To
	</div>
	<div class="form-group">
		<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple"></s:textfield>
	</div>
	<div class="form-group">
		<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
	</div>
	
	<div class="form-group">
		<s:select name="payby"
						list="#{'0':'All','Client':'Self','Third Party':'Third Party'}"
						cssClass="form-control" onchange="showDetails(this.value)"></s:select>
	</div>
	<div class="form-group" style="display: none;" id="showtpdivid">
		<s:select id="thirdParty" name="thirdParty" listKey="id" onchange="showDetails(this.value)"
				listValue="thirdParty"  headerKey="0" headerValue="All"
				list="thirdPartyList" cssClass="form-control showToolTip chosen"></s:select>
	</div>
	<div class="form-group" style="display: none;" id="showclientdivid">
	<s:hidden name="clientId" id = "clientId" ></s:hidden>
		<div style="display: inline-flex;">
			<s:textfield  name="client" id="client" readonly="true"
				  cssClass="form-control" onclick="showPopUp()" onchange="showDetails(this.value)" placeholder="Search patient"></s:textfield> <span
				class="input-group-btn">
			<s:submit value="Go!" cssClass="btn btn-primary"></s:submit>	</span>
		</div>
	</div>
</div>
		
	
</div>
</s:form>	
</div>


<div class="">
<s:form action="paymentOutstandingReport" id="recpaymentfrm">
<div >
	<table id="results"
		class="table table-bordered table-condensed">
		<thead>
			<tr>
				
				 				<th>Name</th>
                              <th> Credit Warning Limit</th>
                              <th>Credit Duration</th>
                              <th>Invoice Raise (Total Charges)</th>
                              <th>Payment Received (Advance Amount)</th>
                              <th>Refund</th>
                              <th>Self Credit</th>
                              <th>Outstanding Invoice</th>
                              <th>View Account</th>
                              <th>Send SMS/Email</th>
                              <th> Action</th>
                              <!-- <th>Show</th> -->
			</tr>
		</thead>
		<tbody>
		  <% String color=""; %>
		
			<s:if test="outstandingReportList.size!=0">
			
				<s:iterator value="outstandingReportList" status="rowstatus"> 

					<s:if test="unpaidAmout<10000">
					   <% color= "#00ff00";  %>
					</s:if>
					<s:elseif test="unpaidAmout>50000">
					     <% color= "red";  %>
					</s:elseif>
					<s:else>
					 <% color= "yellow";  %>
					</s:else>	



					<s:if test="unpaidAmout>=creditWarningLimit">
						<tr class="" style="background-color: <%=color %>" id='<s:property value = "thirdPartyId"/>'
							title='<s:property value="notes"/>'>

							
						
							<s:if test="payby == 'Client'">
								<td><s:property value="thirdPartyName" />
								  <a href="javascript: void(0);" onclick="showNewClientInvoiceDiv('self<s:property value="thirdPartyId"/>');"><i class="fa fa-arrow-down"></i></a>
	  							
								</td>
								<td>NA</td>

								<td>NA</td>
								
							</s:if>
							<s:else>
								<td><s:property value="thirdPartyName" />
									<a href="javascript: void(0);" onclick="showNewClientDiv('tp<s:property value="thirdPartyId"/>');"><i class="fa fa-arrow-down"></i></a>
															
								</td>
								<td><%=Constants.getCurrency(loginfo)%><s:property value="creditWarningLimitx" />
								
								</td>
	
								<td><s:property value="creditReminderDuration" /></td>
							</s:else>
							
							<td><%=Constants.getCurrency(loginfo)%><s:property value="debitx" />
							(<%=Constants.getCurrency(loginfo)%><s:property value="selfcharge" />)
							</td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="paidAmountx" />
							(<%=Constants.getCurrency(loginfo)%><s:property value="advance" />)
							</td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="refundAmt1" /></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="selfcredit" /></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="unpaidAmoutx" /></td>
							<%-- 									<td><input type="submit" value = "Action" onclick = "showActionPopUp('<s:property value = "thirdPartyId"/>','<s:property value = "thirdPartEmail"/>','<s:property value = "thirdPartyContactno"/>')"></td>
 --%>
							<s:hidden value="%{thirdPartyId}" name="id"></s:hidden>
							<s:url action="getActionListOutstandingReport" id="action">
								<s:param name="id" value="%{thirdPartyId}"></s:param>
								<s:param name="name" value="%{thirdPartyName}"></s:param>
								<s:param name="email" value="%{thirdPartEmail}"></s:param>
								<s:param name="contactno" value="%{thirdPartyContactno}"></s:param>

							</s:url>
							<td><a href="#" onclick="openPopup('Statement?clientId=<s:property value="thirdPartyId"/>')">View Account</a></td>
							<td><!-- <a href="#" style="cursor: pointer;" data-toggle="modal" data-target="#exceedLmtActionPopUp2">Contact</a> -->
								<a href="#" style="cursor: pointer;" data-toggle="modal" data-target="#exceedLmtActionPopUp2" onclick="sendsmsormailpopup(<s:property value="thirdPartyId"/>)">Contact</a>
							</td>
							<td><s:a href="%{action}" cssStyle="text-decoration: none;">Action</s:a></td>
							<%-- <td><a href="#" onclick="showClients(<s:property value = "thirdPartyId"/>)">Details</a></td> --%>
						</tr>
						
						
						<s:if test="payby=='Client'">
						
								<s:if test="clientInvoiceList.size!=0">
													<tr id="self<s:property value="thirdPartyId"/>" style="display: none;" >
														<td colspan="12">
															<table class="table table-hover table-condensed table-bordered table-striped " >
																<thead>
																	<tr class="bg-info">
																		
																		 <th><input type="checkbox" name="selfch<s:property value="thirdPartyId"/>" id="selfch<s:property value="thirdPartyId"/>" onclick="selftblechkbox('selfch<s:property value="thirdPartyId"/>','<s:property value="clientInvoiceList.size"/>')"/> Invoice No</th>
																		 <th>Date</th> 
																		 <th>Debit</th>
																		 <th>Credit</th>
																		 <th>Balance</th> 
																		 <th>Payment</th> 
																		
																	</tr>
																</thead>
																<%int c=0; %>
																	<s:iterator value="clientInvoiceList">
																		<tr>
																			<td><input onclick="selfeachinvoice(this.id,this.value)" type="checkbox" name="selfch<s:property value="invoiceid"/>" id="<%=c %>selfch<s:property value="thirdPartyId"/>" value="<s:property value="invoiceid"/>"/> 0000<s:property value="invoiceid"/></td>
																			<td><s:property value="commencing"/></td>
																			<td><%=Constants.getCurrency(loginfo)%><s:property value="debitAmountx"/></td>
																			<td><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx"/></td>
																			<td><%=Constants.getCurrency(loginfo)%><s:property value="balancex"/></td>
																			<td><input id="txt<s:property value="invoiceid"/>" size="8" maxlength="8" type="text" name="<s:property value="invoiceid"/>" value="<s:property value="balancex"/>" onchange="checkNumericOnly(this.value,<s:property value="balancex"/>,this.id)"></td> 
																		</tr>
																	<%c++; %>
																	
																	</s:iterator>
																<tbody>
																
																</tbody>
															</table>
														
														</td>
													</tr>
												
												</s:if>
						
						
						</s:if>
						
						
						<s:if test="clientList.size!=0">
							<tr id="tp<s:property value="thirdPartyId"/>" style="display: none;">
								<td colspan="7">
									<table class="table table-hover table-condensed table-bordered table-striped ">
										<thead>
										<tr class="bg-info">
											<th>Name</th>
											 <th>Debit</th> 
											 <th>Credit</th> 
											 <th>Balance</th>
											
										</tr>
										</thead>
										<s:iterator value="clientList">
											<tbody>
												<s:if test="balanceSum!=0">
												<tr>
													<td><s:property value="title"/> <s:property value="firstName"/> <s:property value="lastName"/>
														<a href="javascript: void(0);" onclick="showNewClientInvoiceDiv('tpclient<s:property value="id"/>');"><i class="fa fa-arrow-down"></i></a>
													</td>
													<td><s:property value="debitSum"/></td>
													<td><s:property value="creditSum"/></td>
													<td><s:property value="balanceSum"/></td>
												</tr>
											</s:if>
												<%int t=0; %>
												<s:if test="invoiceList.size!=0">
													<tr id="tpclient<s:property value="id"/>" style="display: none;">
														<td colspan="12">
															<table class="table table-hover table-condensed table-bordered table-striped ">
																<thead>
																	<tr class="bg-info">
																		
																		 <th><input type="checkbox" name="tpclientch<s:property value="id"/>" id="tpclientch<s:property value="id"/>" onclick="tptblcheckbox('tpclientch<s:property value="id"/>','<s:property value="invoiceList.size"/>')">Invoice No</th>
																		 <th>Date</th> 
																		 <th>Debit</th>
																		 <th>Credit</th>
																		 <th>Balance</th> 
																		 <th>Payment</th>
																		
																	</tr>
																</thead>
																	<s:iterator value="invoiceList">
																		<tr>
																			<td><input type="checkbox" name="<%=t %>tpclientch<s:property value="clientid"/>" id="<%=t %>tpclientch<s:property value="clientid"/>" value="<s:property value="invoiceid"/>" onclick="tpeachinvoice(this.id,this.value)">0000<s:property value="invoiceid"/></td>
																			<td><s:property value="commencing"/></td>
																			<td><%=Constants.getCurrency(loginfo)%><s:property value="debitAmountx"/></td>
																			<td><%=Constants.getCurrency(loginfo)%><s:property value="creditTotalx"/></td>
																			<td><%=Constants.getCurrency(loginfo)%><s:property value="balancex"/></td>
																			<td><input id="txt<s:property value="invoiceid"/>" size="8" maxlength="8" type="text" name="<s:property value="invoiceid"/>" value="<s:property value="balancex"/>" onchange="checkNumericOnly(this.value,<s:property value="balancex"/>,this.id)"></td>
																		</tr>
																	
																		<%t++; %>
																	</s:iterator>
																<tbody>
																
																</tbody>
															</table>
														
														</td>
													</tr>
												
												</s:if>
											</tbody>
									
									</s:iterator>
									</table>
								
								</td>
							</tr>
							
						</s:if>
						
						
					</s:if>
					<s:else>
						<tr style="background-color: <%=color %>">
							<s:if test="payby == 'Client'">
							<td><s:property value="thirdPartyName" />
								<a href="javascript: void(0);" onclick="showClientInvoiceDiv('hiddenClientsInvoiceDiv<s:property value="thirdPartyId"/>','<s:property value="thirdPartyId"/>');"><i class="fa fa-arrow-down"></i></a>
														
							</td>
							</s:if>
							<s:else>
							<td><s:property value="thirdPartyName" />
								<a href="javascript: void(0);" onclick="showClientDiv('hiddenClientsDiv<s:property value="thirdPartyId"/>','<s:property value="thirdPartyId"/>');"><i class="fa fa-arrow-down"></i></a>
														
							</td>
							</s:else>
							<td><%=Constants.getCurrency(loginfo)%><s:property
									value="creditWarningLimitx" /></td>
							<td><s:property value="creditReminderDuration" /></td>

							<td><%=Constants.getCurrency(loginfo)%><s:property value="debitx" />
							(<%=Constants.getCurrency(loginfo)%><s:property value="selfcharge" />)
							</td>		
							<td><%=Constants.getCurrency(loginfo)%><s:property value="paidAmountx" />
							(<%=Constants.getCurrency(loginfo)%><s:property value="advance" />)
							</td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="refundAmt1" /></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="selfcredit" /></td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="unpaidAmoutx" /></td>
							<td><s:a href="%{action}" cssStyle="text-decoration: none;">Action</s:a></td>
<%-- 							<td><a href="#" onclick="showClients(<s:property value = "thirdPartyId"/>)">Details</a></td>
 --%>							
						</tr>
					</s:else>
					<s:if test="payby == 'Client'">
					<tr id = "hiddenClientsInvoiceDiv<s:property value="thirdPartyId"/>" style="display: none">
								<td colspan="12" id = "hiddenClientsInvoiceDiv1<s:property value="thirdPartyId"/>"> </td>
					</tr> 
					
					</s:if> 
					<s:else>
					<tr id = "hiddenClientsDiv<s:property value="thirdPartyId"/>" style="display: none">
								<td colspan="12" id = "hiddenClientsDiv1<s:property value="thirdPartyId"/>"> </td>
								
					</tr>
					</s:else>
					
					
				</s:iterator>
			</s:if>
		</tbody>
	</table>
	<s:else>
		<span style="color:red;">There is no Record found!!</span>
	</s:else>
</div>


<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;margin-top: 15px;">
<div class="form-inline">
	<div class="form-group">
		<select name="howpaid" id="howpaid" class="form-control">
				<option value="0">Select Payment Mode</option>
				<!-- <option value="BACS">BACS</option> -->
				<option value="Cheque">Cheque</option>
				<option value="C/Card">C/Card</option>
				<option value="Cash">Cash</option>
				<option value="D/Card">D/Card</option>
				<option value="D/D">D/D</option>
				<option value="Other">Other</option>
				<!-- <option value="S/O">S/O</option> -->
			</select> 
	</div>
	<s:hidden name="payby"/>
		<s:hidden name="thirdParty" id="recThirdpartyid" />
		<s:hidden name="clientId" />
	<div class="form-group">
		<input type="button" class="btn btn-primary" value=" Record Payment " onclick="reordPayment()">
	</div>
</div>

</div>

	</s:form>
	
	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;margin-top: 10px;text-align: right;">
	<s:form action="createlistOutstandingReport" id="outstandingrportfrm" target="formtargetoutstanding">
				<s:hidden name="payby" id="payby"/>
				<s:hidden name="thirdParty" id="topThirdpartyid" />
				<s:hidden name="clientId" />

				<div class="form-group hidden-print">
							<input type="button" class="btn btn-primary" value="Create Invoice List" onclick="createList()">
				
					</div>			
			
				
				<br>
		</s:form>
	</div>
	
		
	
	</div>
		
	
	





		
<div class="row">
			<div class="col-lg-12">
			</div>
</div>

<%-- <s:if test="outstandingReportList!=null">
	<s:form action="OutstandingReport" name="paginationForm"
		id="paginationForm" theme="simple">
		<div class="row">
			<div class="col-lg-12">
				<div class="col-lg-4 col-md-4 text-right">
					Showing all <b>(<s:property value="pagerecords" /> of <s:property
							value="totalRecords" /> Records) 
				</div>
				<%@ include file="/common/pages/pagination.jsp"%>
			</div>
		</div>
	</s:form>
</s:if> --%>

<div id="exceedLmtActionPopUp" title="Select Action"
	style="display: none">
	<input type="hidden" id="thirdPartyId" name="thirdPartyId"> <input
		type="hidden" id="thirdPartyContactNo" name="thirdPartyContactNo">
	<input type="hidden" id="thirdPartyEmail" name="thirdPartyEmail">

	<table style="width: 100%">

		<tr>
			<td><input type="button" id="email" class="mdbtn"
				value="  Send Email  " onclick="sendEmail()"
				style="text-align: center;"></td>
		</tr>
		<tr>
			<td><input type="button" id="sms" class="mdbtn"
				value="  Send SMS  " onclick="sendSMS()" style="text-align: center;">
			</td>
		</tr>
		<tr>
			<td><input type="button" id="call" class="mdbtn"
				value="  Make Call  " onclick="makeCall()"
				style="text-align: center;"></td>
		</tr>
	</table>
</div>


<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/thirdParties/pages/topPatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>	



<div class="modal fade" id="clientListPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel2">Client</h4>
      </div>
      <div class="modal-body">      
			<div class="table-responsive" id="patientList">

	  </div>
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






<div class="modal fade" id="exceedLmtActionPopUp2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Select An Action</h4>
      </div>
      <div class="modal-body"> 
      <s:hidden id="thirdPartyId" name = "thirdPartyId"/>     
      <s:hidden id="newclientid"/>
	<s:hidden id = "thirdPartyName" name = "thirdPartyName"/>
		<div class="row">
			<div class="col-lg-12">
				<!-- <a class="btn btn-primary width-full"  data-toggle="modal" data-target="#sendEmailPopUp2"><i class="fa fa-message"></i> Send Email</a> -->
				<a class="btn btn-primary width-full" data-toggle="modal"  onclick="opensendemailpopup()" data-target="#sendEmailPopUp2"><i class="fa fa-message"></i> Send Email</a>
			</div>
		</div>   
		<br/>   
		<div class="row">
			<div class="col-lg-12">
			<a class="btn btn-primary width-full" data-toggle="modal"  onclick="opensendsmspopup()" data-target="#sendSMSPopUp2"><i class="fa fa-message"></i>Send SMS</a>
				<!-- <a class="btn btn-primary width-full" data-toggle="modal" data-target="#sendSMSPopUp2"><i class="fa fa-message"></i> Send SMS</a> -->
			</div>
		</div>   
		<br/>   
		<div class="row hidden">
			<div class="col-lg-12">
				<a class="btn btn-primary width-full" data-toggle="modal" data-target="#makeCallPopUp2"><i class="fa fa-phone"></i> Make a Call</a>
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>



<div class="modal fade" id="sendEmailPopUp2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel2">Send Email</h4>
      </div>
      <div class="modal-body">      
		<div class="row">
			<div class="col-lg-12">
				<label>To:</label>
				<s:textfield theme="simple" id="thirdPartEmail"
					name="thirdPartEmail"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Email" placeholder="Enter Email Id"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Cc:</label>
				<s:textfield theme="simple" id="ccEmail"
					name="ccEmail"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Cc" placeholder="Enter Cc"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Subject:</label>
				<s:textfield theme="simple" id="subject"
					name="subject"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter subject" placeholder="Enter subject"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Body:</label>
				<textarea class="form-control showToolTip" data-toggle="tooltip" title="Write content" placeholder="Write content" name="emailBody" id="emailBody" cols="40" rows="3"></textarea>
			</div>
		</div>   
      </div>
      <div class="modal-footer">
      
      	 <button type="button" class="btn btn-primary"  onclick="sendSaveEmail()">Send</button>
      	<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>



<div class="modal fade" id="sendSMSPopUp2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel3">Send SMS</h4>
      </div>
      <div class="modal-body">      
		<div class="row">
			<div class="col-lg-12">
				<label>Contact No:</label>
				<s:textfield theme="simple" id="thirdPartyContactno"
					name="thirdPartyContactno"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Phone/Mobile No" placeholder="Enter Phone/Mobile No"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Note:</label>
				<textarea name="smsNote" id="smsNote" rows="3" class="form-control showToolTip" data-toggle="tooltip" title="Enter Message" placeholder="Enter Message"></textarea>
			</div>			
		</div>   
      </div>
      <div class="modal-footer">
      	      	 <button type="button" class="btn btn-primary"  onclick="sendSaveSMS()">Send</button>
      	
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="makeCallPopUp2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel3">Send SMS</h4>
      </div>
      <div class="modal-body">      
		<div class="row">
			<div class="col-lg-12">
				<label>Contact No:</label>
				<s:textfield theme="simple" id="thirdPartyContactno"
					name="thirdPartyContactno"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Enter Phone/Mobile No" placeholder="Enter Phone/Mobile No"/>
			</div>
			<br/>
			<div class="col-lg-12">
				<label>Note:</label>
				<textarea name="callNote" id="callNote" rows="3" class="form-control showToolTip" data-toggle="tooltip" title="Enter Message" placeholder="Enter Message"></textarea>
			</div>			
		</div>   
      </div>
      <div class="modal-footer">
      
      <button type="button" class="btn btn-primary"  onclick="saveCommunication()">Save</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>





