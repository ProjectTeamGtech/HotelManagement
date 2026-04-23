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
$(window).on('load', function() {
	$('#dashboardloaderPopup').modal('hide');
});
	$(document).ready(function() {
		 $('#dashboardloaderPopup').modal('show');
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
 <%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>

<style>
a{
color: #6b1d10 !important;
}
</style>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Record Payment</h4>

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




<s:form action="ProcessingAccount" id="category_form" theme="simple">
	<s:hidden name="hdnSelectedID" id="hdnSelectedID" />
	<div class="col-lg-12 col-md-12 topback2 ">
		<div class="col-lg-2 col-md-2">
			<label><%=loginInfo.getPatientname_field() %>:</label>
			
			<div class="input-group ">
                 
                <%-- <s:textfield name="client" id="client"  readonly="true"
				cssClass="form-control" onclick="showPopUp()" data-toggle="modal" data-target="#clientSearch" aria-describedby="basic-addon1"/> --%>
				<s:textfield name="client" id="client"  readonly="true"
				cssClass="form-control" />
				<s:hidden name="clientId" id="clientId"></s:hidden>
               </div>	
			
		</div>
		<div class="col-lg-2 col-md-2">
			<label>Show Payed By:</label>
			<s:select name="payby"
				list="#{'All':'All','Client':'Self','Third Party':'Third Party'}"
				cssClass="form-control"></s:select>
		</div>
		<div class="col-lg-2 col-md-2">
			<label>Show Transaction:</label>
			<s:select name="transactionType"
				list="#{'All':'All','Pending Payment':'Pending Payment','Payments':'Payments'}"
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
			<s:submit value="Go" theme="simple" cssClass="btn btn-primary" onclick="shownewpopup()"></s:submit> &nbsp;&emsp;
			<input type="button" value="Back" onclick="window.history.back(-1)" class="btn btn-primary" /> 
		</div>
	</div>
	
	
	<s:if test="clientId!=0">
	<div class="row">
		<div class="col-lg-6 col-md-6" style="margin-top: 15px;">
			

				<b><%=loginInfo.getPatientname_field() %> Details:</b>
				<br> UHID:<s:property value="abrivationid" />/<s:property value="clientId" /><br>
				<s:property value="initial" /><s:property value="firstname" /> <s:property value="lastname" /><br>
				<s:property value="address" />
				<s:property value="postcode" /><br>
				<s:property value="mobno" /><br>
				<s:property value="email" />

		</div>
		<div class="col-lg-6 col-md-6" style="margin-top: 15px;">
			<b>Third Party Details</b><br />
			<s:property value="insuranceCompany" /><br>
			<s:property value="thirdPartyAddress" /><br>
			<s:property value="thirdPartyPostcode" /><br>
			<s:property value="thirdPartyContacttno" /><br>
			<s:property value="thirdPartyemail" />
			
		</div>
	</div>
	</s:if>
	<br />

	<div class="row">
		<div class="col-lg-12">
		<%-- 	<s:actionmessage cssClass="messageDiv" /> --%>
			<div>
				<table class="table-bordered table-hover table-condensed width-full">
					<thead>
						<tr>
						
							<th title="Sort by Date" class="text-center sortable">Date </th>
							 <th> Transaction</th>
                              <th> Payee</th>
                              <th> Status</th>
                              <th>Bill Summary</th>
                              <th class="text-right"> Charges</th>
                              <th class="text-right"> Payments</th>
                              <th class="text-right"> Discount</th>
                              <th class="text-right"> Balance</th>
                              
                              <th class="text-right"> Closed</th>
                              <th class="text-right"> Post</th>
							<!-- <th><i class="fa fa-search-plus"></i> View</th>
							<th>Modify</th> -->
						</tr>
					</thead>
					<tbody>
					
					<%String bgcolor = "rgba(255 159 15 / 75%);"; %>
					
						<s:if test="chargeProcessingList.size!=0">
							<s:iterator value="chargeProcessingList" status="rowstatus">
							
							<s:if test="balancex=='0.00'">
								<% bgcolor = "rgba(79 216 197 / 55%);"; %>
							</s:if>
							<s:else>
								<% bgcolor = "rgba(255 159 15 / 75%);"; %>
							</s:else>
								<tr style="background-color: <%=bgcolor %>" id="<s:property  value="id" />">
									<td><s:property value="date" /></td>
									<s:url action="viewInvoiceCharges" id="viewInvoiceCharges" >
										<s:param name="invoiceid" value="%{id}"/>
										<s:param name="action" value="%{'show'}"/>
										<s:param name="discount" value="%{discount}"/>
										<s:param name="payby" value="%{payby}"/>
									</s:url>
									<s:if test="deliverstatus==1">
										<td><s:a href="%{viewInvoiceCharges}" ><img src="common/images/Letter-P-blue-icon.png"> Invoice  <%if(loginfo.isSeq_no_gen()){%><s:property value="ipdopdseq"/>
											<span class="hidden-print">(Ref Id:<s:property value="id"/>)</span><%}else{%>(<s:property value="id" />)<%} %></s:a> <a
											href="javascript: void(0);" 
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a>
										</td>
									</s:if>
									<s:elseif test="deliverstatus==2">
										<td><s:a href="%{viewInvoiceCharges}" >Invoice <img src="common/images/Letter-E-blue-icon.png"> <br> (<s:property value="id" />)</s:a> <a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a>
										</td>
									</s:elseif>
									<s:else>
										<td><s:a href="%{viewInvoiceCharges}" >Invoice <br> (<s:property value="id" />)</s:a> <a
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
									<s:else>
									<td>
									
									<s:if test="ipost==0">
									   <s:if test="balance == 0">
										Paid |
										
											<%-- <a href="#"  onclick="recordpymnt('<s:property value="payby"/>','<s:property value="numberOfChages"/>','<s:property value="creditCharge"/>','<s:property value="id"/>','<s:property value="debitAmount"/>','<s:property value="balance"/>','<s:property value="discount"/>','<s:property value="clientId"/>','<s:property value="balancex" />')">Record Payment</a>| --%>  
										<%-- <s:a href="%{paymentProcessingAccount}">Record Payment </s:a>| --%>
										<%if(loginfo.isCancel_invoice_new() || loginfo.getSuperadminid()==1){ %>
										<s:if test="!refundstatus">
										<a href="#" onclick="cancelInvoice(<s:property value="clientId"/>,<s:property value="id"/>)">Cancel</a> |
										</s:if>
										
										<%} %>
									</s:if>
									<s:else>
										<a href="#"  onclick="recordpymnt('<s:property value="payby"/>','<s:property value="numberOfChages"/>','<s:property value="creditCharge"/>','<s:property value="id"/>','<s:property value="debitAmount"/>','<s:property value="balance"/>','<s:property value="discount"/>','<s:property value="clientId"/>','<s:property value="balancex" />')">Record Payment</a>|  
										<%-- <s:a href="%{paymentProcessingAccount}">Record Payment </s:a>| --%>
										<%if(loginfo.isCancel_invoice_new() || loginfo.getSuperadminid()==1){ %>
										<s:if test="!refundstatus">
										<a href="#" onclick="cancelInvoice(<s:property value="clientId"/>,<s:property value="id"/>)">Cancel</a> |
										</s:if>
									<%} %>
									</s:else>
									<s:if test="creditCharge<0 || itype==1 || itype==3 || itype==6"  >
									<%if(loginfo.isInvoicemodify() || loginfo.getAcaccess().equals("1")){ %>
										<a href="#" data-toggle="modal" data-target="#transactionPopup2"
										onclick="showTransactionPopup('<s:property value ="id"/>','<s:property value ="clientId"/>','<s:property value="payby"/>')">View Payments 
											</a>| 
									<%} %>
									
									</s:if>
									<s:else>
									<%if(loginfo.isInvoicemodify() || loginfo.getAcaccess().equals("1")){ %>
									<a href="#" data-toggle="modal" data-target="#transactionPopup2"
										onclick="showTransactionPopup('<s:property value ="id"/>','<s:property value ="clientId"/>','<s:property value="payby"/>')">View Payments 
											</a>| 
											<%} %>
									</s:else>
									<s:if test="creditCharge==0">
											<s:url action="modifysProcessingAccount"
											id="modifysProcessingAccount">
											<s:param name="payby" value="%{payby}" />
											<s:param name="numberOfChages1" value="%{numberOfChages}" />
											<s:param name="creditCharge1" value="%{creditCharge}" />
											<s:param name="invoiceid1" value="%{id}" />
											<s:param name="debitamt1" value="%{debitAmount}" />
											<s:param name="balance1" value="%{balance}" />
											<s:param name="discount1" value="%{discount}" />
											<s:param name="clientId" value="%{clientId}" />
										</s:url>
										
										<s:url action="resetProcessingAccount"
											id="resetProcessingAccount">
											<s:param name="clientId" value="%{clientId}" />
											<s:param name="invoiceid" value="%{id}" />
										</s:url>
										
										<% if(loginfo.getSuperadminid()==1 || loginfo.getAcaccess().equals("1")){%>
										<s:if test="creditCharge<=0">
										<s:if test="!discstatus1">
										<s:a href="%{modifysProcessingAccount}">Modify</s:a> | 
										</s:if>
										</s:if>
										<s:else>
										<%if(loginfo.isInvoicemodify()){ %>
										<s:if test="!discstatus1">
										<s:a href="%{modifysProcessingAccount}">Modify</s:a> | 
										</s:if>
											<%} %>
										</s:else>
									<%-- 	<s:if test="disc_request==0">
											<a href="#" onclick="requestfordiscount(<s:property value="id"/>)">Request Disc.</a>
											<a href="#" onclick="showrequestdiscountpopup(<s:property value="id"/>,<s:property value="debitAmountx" />)">Request Disc.</a>
										</s:if>
										<s:elseif test="disc_request==1">
											Discount Requested
										</s:elseif>
										<s:elseif test="disc_request==2">
											<a href="#" onclick="showdiscountpopup(<s:property value="id"/>)">Modify Disc.</a>
										</s:elseif>
										<s:elseif test="disc_request==3">
											Applied Disc.
										</s:elseif> --%>
										<%} %>
										<%if(loginfo.isDirect_refund_disc()){ %>
											<s:if test="disc_request==3">
												Applied Disc.
											</s:if>
											<s:else>
												<%if(loginfo.isModify_disc()){ %>
												    <s:if test="creditCharge<0 || itype==1 || itype==3 || itype==6"  >
													<a href="#" onclick="showdiscountpopupdirect(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />,<s:property value="debitAmountx" />)">Modify Disc.</a>
													</s:if>
													<s:else>
													<%if(loginfo.isInvoicemodify()){ %>
													<a href="#" onclick="showdiscountpopupdirect(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />,<s:property value="debitAmountx" />)">Modify Disc.</a>
														<%} %>
													</s:else>
												<%} %>
											</s:else>
										<%}else{ %>
											<s:if test="disc_request==0">
												<s:if test="!discstatus1">
													<%-- <a href="#" onclick="requestfordiscount(<s:property value="id"/>)">Request Disc.</a> --%>
													<a href="#" onclick="showrequestdiscountpopup(<s:property value="id"/>,<s:property value="debitAmountx" />,<s:property value="practitionerId" />)">Request Disc.</a>
												</s:if>
											</s:if>
											<s:elseif test="disc_request==1">
												Discount Requested
											</s:elseif>
											<s:elseif test="disc_request==2">
												<%if(loginfo.isModify_disc()){ %>
													<a href="#" onclick="showdiscountpopup(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />)">Modify Disc.</a>
												<%} %>
											</s:elseif>
											<s:elseif test="disc_request==3">
												Applied Disc.
											</s:elseif>
										<%} %>
										
										
									</s:if>
									<s:hidden name="disc_request" id="disc_request"/>
									<s:else>
									
									    <s:url action="resetProcessingAccount"
											id="resetProcessingAccount">
											<s:param name="clientId" value="%{clientId}" />
											<s:param name="invoiceid" value="%{id}" />
										</s:url>
										<% if(loginfo.getSuperadminid()==1 || loginfo.getAcaccess().equals("1")){%>
										
											 <s:url action="modifysProcessingAccount"
											id="modifysProcessingAccounts">
											<s:param name="payby" value="%{payby}" />
											<s:param name="numberOfChages1" value="%{numberOfChages}" />
											<s:param name="creditCharge1" value="%{creditCharge}" />
											<s:param name="invoiceid1" value="%{id}" />
											<s:param name="debitamt1" value="%{debitAmount}" />
											<s:param name="balance1" value="%{balance}" />
											<s:param name="discount1" value="%{discount}" />
											<s:param name="clientId" value="%{clientId}" />
										</s:url>  
										
										
										<!-- hello -->
										
										 <%-- <a href="#"  onclick="modifyinvdisc('<s:property value="payby"/>','<s:property value="numberOfChages"/>','<s:property value="creditCharge"/>','<s:property value="id"/>','<s:property value="debitAmount"/>','<s:property value="balance"/>','<s:property value="discount"/>','<s:property value="clientId"/>')">Modify</a>| --%> 
										<%-- <s:if test="balance != 0"> --%>
										<s:if test="creditCharge<=0">
											<s:if test="!discstatus1">
												<s:a href="%{modifysProcessingAccounts}">Modify</s:a> |
											</s:if>
										</s:if>
										<s:else>
											<%if(loginfo.isInvoicemodify()){ %>
												<s:if test="!discstatus1">
													<s:a href="%{modifysProcessingAccounts}">Modify</s:a> |
												</s:if>
											<%} %>
										</s:else>
										<%-- </s:if> --%>
										<%-- <s:a href="%{modifysProcessingAccounts}" onclick="modifff('%{modifysProcessingAccounts}')">Modify</s:a> | --%>
										<%-- <a href="#" onclick="cancelInvoice(<s:property value="clientId"/>,<s:property value="id"/>)">Cancel</a> | --%>
										
										<%} %>
										<%if(loginfo.isDirect_refund_disc()){ %>
										<s:if test="!discstatus1">
											<s:if test="disc_request==3">
												Applied Disc.
											</s:if>
											<s:else>
												<%if(loginfo.isModify_disc()){ %>
													<%-- <s:if test="creditCharge<0 || itype==1 || itype==3 || itype==6"  >
														<a href="#" onclick="showdiscountpopupdirect(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />,<s:property value="debitAmountx" />)">Modify Disc.</a>
													</s:if>
													<s:else>
														<%if(loginfo.isInvoicemodify()){ %>
															<a href="#" onclick="showdiscountpopupdirect(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />,<s:property value="debitAmountx" />)">Modify Disc.</a>
														<%} %>
													</s:else> --%>
													<s:if test="creditCharge<0">
														<a href="#" onclick="showdiscountpopupdirect(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />,<s:property value="debitAmountx" />)">Modify Disc.</a>
													</s:if> 
												<%} %>
											</s:else>
										</s:if>
										<s:else>
											
										</s:else>
											
										<%-- 	| <a href="#" onclick="openBlankPopup('creditAdditional?clientId=<s:property value="clientId"/>&invoiceid=<s:property value="id"/>')">Refund</a>
											| <a href="#" onclick="openBlankPopup('refundrequestdashboardAdditional?searchtext=<s:property value="clientId"/>')">Refund Dashboard</a> --%>
										<%}else{ %>
											<s:if test="disc_request==0">
												<%-- <a href="#" onclick="requestfordiscount(<s:property value="id"/>)">Request Disc.</a> --%>
												<s:if test="creditCharge<0 || itype==1 || itype==3 || itype==6"  >
													<s:if test="!discstatus1">
														<a href="#" onclick="showrequestdiscountpopup(<s:property value="id"/>,<s:property value="debitAmountx" />,<s:property value="practitionerId" />)">Request Disc.</a> 
													</s:if>
												</s:if>
												<s:else>
													<%if(loginfo.isInvoicemodify()){ %>
														<s:if test="!discstatus1">
															<a href="#" onclick="showrequestdiscountpopup(<s:property value="id"/>,<s:property value="debitAmountx" />,<s:property value="practitionerId" />)">Request Disc.</a>
														</s:if> 
													<%} %>
												</s:else>
											</s:if>
											<s:elseif test="disc_request==1">
												Discount Requested 
											</s:elseif>
											<s:elseif test="disc_request==2">
												<%if(loginfo.isModify_disc()){ %>
												<s:if test="creditCharge<0 || itype==1 || itype==3 || itype==6">
													<a href="#" onclick="showdiscountpopup(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />)">Modify Disc.</a>
												</s:if>
												<s:else>
												<%if(loginfo.isInvoicemodify()){ %>
												<a href="#" onclick="showdiscountpopup(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />)">Modify Disc.</a>
												<%} %>
												</s:else>
												<%} %>
											</s:elseif>
											<s:elseif test="disc_request==3">
												Applied Disc.
											</s:elseif>
										<%} %>
										
										
									</s:else>
									
									<s:if test="creditCharge<0 || itype==1 || itype==3 || itype==6">
										 	| <a href="#" onclick="openBlankPopup('creditAdditional?clientId=<s:property value="clientId"/>&invoiceid=<s:property value="id"/>')">Refund</a>
											| <a href="#" onclick="openBlankPopup('refundrequestdashboardAdditional?searchtext=<s:property value="clientId"/>')">Refund Dashboard</a> 
									
									</s:if>
										<s:else>
										<%if(loginfo.isInvoicemodify()){ %>
										| <a href="#" onclick="openBlankPopup('creditAdditional?clientId=<s:property value="clientId"/>&invoiceid=<s:property value="id"/>')">Refund</a>
											| <a href="#" onclick="openBlankPopup('refundrequestdashboardAdditional?searchtext=<s:property value="clientId"/>')">Refund Dashboard</a> 
										<%} %>
										</s:else>
										<%if(loginfo.isAdditional_disc() || loginfo.getSuperadminid()==1){ %>
											<s:if test="disc_request==3">
												<s:if test="additionaldiscallow==0">
												<%if(loginfo.isInvoicemodify()){ %>
												    	| <a href="#" onclick="showadditionaldiscountpopup(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />,<s:property value="debitAmountx" />,<s:property value="disctype" />,<s:property value="discountx" />,<s:property value="discamt" />)">Additional Disc.</a>
												<%}else{ %>
													<s:if test="creditCharge<0 || itype==1 || itype==3 || itype==6">
														| <a href="#" onclick="showadditionaldiscountpopup(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />,<s:property value="debitAmountx" />,<s:property value="disctype" />,<s:property value="discountx" />,<s:property value="discamt" />)">Additional Disc.</a>
													</s:if>
													<s:elseif test="creditCharge==0 && itype==2">
														| <a href="#" onclick="showadditionaldiscountpopup(<s:property value="id"/>,<s:property value="creditCharge" />,<s:property value="balancex" />,<s:property value="debitAmountx" />,<s:property value="disctype" />,<s:property value="discountx" />,<s:property value="discamt" />)">Additional Disc.</a>
													</s:elseif>
												<%}%>
												</s:if>
											</s:if>
										<%} %>
									</s:if>
									<s:else>
										<span style="color:brown;">Invoice has posted (Can't Modify)</span>
									</s:else>
									
									</td>
									</s:else>
									<td>
										 <s:url action="viewInvoiceCharges" id="viewbillsummaryCharges" >
											<s:param name="invoiceid" value="%{id}"/>
											<s:param name="action" value="%{'show'}"/>
											<s:param name="discount" value="%{discount}"/>
											<s:param name="payby" value="%{payby}"/>
											<s:param name="billsummary" value="%{'1'}"/>
											<%-- <s:a href="%{viewbillsummaryCharges}" >Bill Summary</s:a> --%>
										</s:url>
										<%-- <s:a href="#" onclick="openBlankPopup('viewInvoiceCharges?invoiceid=<s:property value="id" />&discount=<s:property value="discount" />&payby=<s:property value="payby" />&billsummary=1')" >Bill Summary</s:a> --%>
										<s:a href="%{viewbillsummaryCharges}" >Bill Summary</s:a>
									</td>
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
									
									<td class="text-right">
										<s:if test="iclosed==0">
											<a href="#" onclick="closewithpopup(<s:property value="id"/>)">Close</a>
										</s:if>
										<s:else>
											Closed
										</s:else>
									</td>
									
									<td class="text-right">
										<s:if test="ipost==0">
											<a href="#" onclick="postwithpopup(<s:property value="id"/>)">Post</a>
										</s:if>
										<s:else>
										<!--lokesh  -->
										 <%if(loginInfo.getJobTitle().equals("Admin")) {%> 
										 
											<a href="ipostToPostProcessingAccount?id=<s:property value="id"/>">Posted</a>
											
											<%}else if(loginInfo.getClinicid1().equals("raibhattar") || loginInfo.getClinicid1().equals("raiprachi")){ 
											if(loginInfo.getJobTitle().equals("Reception")){
											%>
											<a href="ipostToPostProcessingAccount?id=<s:property value="id"/>">Posted</a>
											<%}}else{ %>
											Posted
											<% }%>
										</s:else>
									</td>
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







<s:if test="chargeProcessingList!=null">
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
<form action="modifysProcessingAccount" id="modifysform">
										
										<s:hidden name="payby"  id="mod_payby"/>
										<s:hidden name="numberOfChages1"  id="mod_numberOfChages"/>
										<s:hidden name="creditCharge1"  id="mod_creditCharge"/>
										<s:hidden name="invoiceid1"  id="mod_id"/>
										<s:hidden name="debitamt1"   id="mod_debitAmount"/>
										<s:hidden name="balance1"  id="mod_balance"/>
										<s:hidden name="discount1"  id="mod_discount"/>
										<s:hidden name="clientId" id="mod_clientId"/>
										</form>
										
										
										
				<form action="paymentProcessingAccount" id="recordpymtform" >
										
										<s:hidden name="payby"  id="pay_payby"/>
										<s:hidden name="numberOfChages"  id="pay_numberOfChages"/>
										<s:hidden name="creditCharge"  id="pay_creditCharge"/>
										<s:hidden name="invoiceid"  id="pay_id"/>
										<s:hidden name="debitamt"   id="pay_debitAmount"/>
										<s:hidden name="balance"  id="pay_balance"/>
										<s:hidden name="discount"  id="pay_discount"/>
										<s:hidden name="clientId" id="pay_clientId"/>
										<s:hidden name="balancex" id="pay_balancex"/>
										</form>						

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
						<input type="hidden" name="cancelinv" value="1">
						<div class="form-group">
							<label>Notes <small>(Reason for canceling invoice)</small></label>
							<textarea class="form-control" name="notes" rows="4" id="notestxt"></textarea>
						</div>
 
						   <s:hidden name="clientId" id="rclientId" />
						   <s:hidden name="invoiceid" id="rinvoiceid" /> 
						
						<div class="form-group">
							<button type="submit" class="btn btn-primary" onclick="return validatenote();" style="float: right;">Submit</button>
						
						</div>
						
					</div>
					</s:form>
				</div>
				
			</div>
		</div>
	</div>
</div>



<s:form action="modpaymentProcessingAccount">
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
			<div class="modal-body">
			<!-- <table class="table table-responsive">
					<thead>
						<tr>
							<th>Cheque Type</th>
							<th>Cheque Date</th>
							<th>Cheque Issued</th>
							<th>Status</th>
						</tr>
					</thead>
						<tbody>
							<tr>
								<td>Cross Cheque</td>
								<td>21/06/2017</td>
								<td>Jatin</td>
								<td></td>
							</tr>
						</tbody>
					</table> -->
			</div>
			<div class="modal-footer">
				<%if(loginfo.getSuperadminid()==1 || loginfo.getAcaccess().equals("1")){ %>
					<button type="submit" class="btn btn-primary" >Update</button>
				<%} %>
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
</s:form>											

											
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
			 	<lable>After Discount</lable>
				<s:textfield  id="afterdiscountinvoiceamount1" cssClass="form-control" readonly="true" />
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
					<s:textfield name="requestdiscval" id="requestdiscval" cssClass="form-control" onchange="calculateafterDiscount(this.value)" onkeypress="return isNumberKey(event,this);" />
			</div>
		</div>
		<div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
			 	<lable>After Discount</lable>
				<s:textfield  id="afterdiscountinvoiceamount" cssClass="form-control" readonly="true" />
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

<div class="modal fade modal-draggable" id="directmodifyydiscpopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modify Discount</h4>
      </div>
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Invoice Amount:</lable>
				 <s:hidden id="directrequestdiscinvoiceamtval"></s:hidden>
				 <s:hidden id="additionaldiscbalance"></s:hidden>
				 <s:hidden id="additionaldiscpayment"></s:hidden>
					<span id="directrequestdiscinvoiceamt"></span>
			</div>
		</div>
		 <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Practitioner Name:</lable>
				<s:select list="discountgivenuserlist" listKey="id" listValue="practitionerName" cssClass="form-control chosen-select" headerKey="0" headerValue="Select User" id="directdiscountgivenuserid" ></s:select>
			</div>
		</div>
        <div class="">
        	<s:hidden id="directreqdiscinvoiceid"></s:hidden>
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Select Discount Type</lable>
					<s:select  list="#{'0':'%','1':'Rs.'}"  onchange="directchangedisctype()" theme="simple" name="requestdisctype" id="directrequestdisctype" cssClass="form-control" />
			</div>
		</div>
					
        <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Enter Amount</lable>
					<s:textfield  id="directrequestdiscval" cssClass="form-control" onkeypress="return isNumberKey(event,this);" />
			</div>
		</div>
		<div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 	<lable>Discount Notes</lable>
					<textarea rows="3"  class="form-control" id="direct_discount_reason" placeholder="Note"></textarea>
			</div>
		</div>
       <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 	<lable>Notes:</lable>
					<span> If new discount apply then previous discount set to 0.</span>
			</div>
		</div>
      </div>
      <div class="modal-footer" id="reqrefbtndiv">
        <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary " onclick="validateDirectDiscountRefund()">Modify Discount</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade modal-draggable" id="directrequestrefundpopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
				 <span id="directrequestrefundinvoiceamt"></span>
			</div>
		</div>
					
        <div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Refund Amount</lable>
				<s:textfield   cssClass="form-control" id="directrefundamount" readonly="true" onkeypress="return isNumberKey(event,this);" />
			</div>
		</div>
		<div class="">
        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
				 <lable>Refund Notes</lable>
				 <textarea rows="3"  class="form-control" id="direct_refund_reason" placeholder="Note"></textarea>
			</div>
		</div>
       
      </div>
      <div class="modal-footer" id="refreqdisdiv">
        <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary " onclick="directrefundrequestafterdisc()">Request Refund</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
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