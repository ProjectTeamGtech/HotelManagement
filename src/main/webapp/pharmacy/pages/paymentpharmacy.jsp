<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="accounts/js/accounts.js"></script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script type="text/javascript" src="accounts/js/chargeAccountProcessing.js"></script>


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

<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				
				
			%>							       
		<s:form action="paycashPharmacy" theme="simple" id="paymentProcessingfrm">
			<s:hidden name="client" id="client" />
			<s:hidden name="clientId" id="clientId"/>
			<s:hidden name="payby" id="payby"/>
			<s:hidden name="invoiceid" id="invoiceid"/>
			<%-- <s:hidden name="crdAmount" id="crdAmount"/> --%>
			<s:hidden name="creditNote" id="creditNotes"/>
			
		<div class="row">
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
			<input type="text" id="invoiceDate" name="invoiceDate" class="form-control"  value="<%=temp[0] %>" ></input></div>
			</div>
	
			
			<%-- <div style="font-weight: bold; font-size: 12px;">Invoice can be raise as below</div>
			<br>
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-hover table-condensed table-bordered">
					<thead>
				<tr>
					<th>Invoice No.</th>
					<th>To</th>
					<th>No. of Charges</th>
					<th>Total</th>
					<th>Location</th>
					
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>0000<s:property value="invoiceid"/></td>
					<s:if test="payby == 'Client'">
						<td>Client</td>
					</s:if>
					<s:else>
						<td>Third Party</td>
					</s:else>
					<td><s:property value="numberOfChages"/></td>
					<td><%=Constants.getCurrency(loginfo)%><s:property value="debitAmt"/></td>
					<td></td>
				</tr>
				</tbody>
			</table>
			</div>
			</div>
			</div> --%>
			<br>
			<div style="font-weight: bold; font-size: 12px;">Record Payment for below Invoice 0000<s:property value="invoiceid"/></div>
			
				<br><%int count=1;%>
					
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div >
						<table class="table table-bordered" id="assementtable" style="width:50% !important;">
					<thead>
					<tr>
						<th>Sr.No.</th>
						<th>Charge No</th>
						<th>Amount</th>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="assesmentList">
						<tr>
							<td><%=count%></td>
							
							<td>0000<s:property value="invoiceNo"/>
									<a href="javascript: void(0);" onclick="showInnerSubDiv('hiddenDetailsDiv<s:property value="invoiceNo"/>','<s:property value="invoiceNo"/>');"><img style="margin-left: 20%;" width="20" height="15" align="middle" src="common/images/Arrows-Down-icon.png"/></a>
							
							</td>
							<td><%=Constants.getCurrency(loginfo)%><s:property value="totalAmountx"/></td>
						</tr>
						<tr id = "hiddenDetailsDiv<s:property value="invoiceNo"/>" style="display: none">
							<td colspan="3" id = "hiddenDetailsDiv1<s:property value="invoiceNo"/>"> </td>
					</tr>
					<%count++;%>
					</s:iterator>
					<tr>
						<td colspan="2"><b>Total</b></td>
						<td><%=Constants.getCurrency(loginfo)%><s:property value="debitAmt"/></td>
					</tr>
				</tbody>
				</table>
				</div>
				</div>
				</div>
			
			<br>
			<div style="font-weight: bold; font-size: 12px;">Pay this Invoice ( Debit: &nbsp;&nbsp;&nbsp;<%=Constants.getCurrency(loginfo) %><s:property value="debitAmt"/>, &nbsp;&nbsp;&nbsp; Credit: &nbsp;&nbsp;&nbsp;<%=Constants.getCurrency(loginfo) %><s:property value="creditCharge"/>)</div>
			<hr/>
			<br>
			
		<input type="hidden" name="hdntotal" id="hdntotal" value="<s:property value="debitAmt"/>" />
		<s:hidden id="hiddenbalence" name="balanceAmt"/>	
		<s:hidden id="prepaymntamntid" name="balanceAmount"/>
	<div class="row">
		<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">
			<label>Payment Mode</label><label class="text-danger">*</label> 
		</div>
		
		<div class="col-lg-1 col-md-1 col-xs-2 col-sm-1">
			:
		</div>
		
		<div class="col-lg-2 col-md-2 ccol-xs-5 col-sm-2">
			
			
				
				<select name="howpaid" id="howpaid" class="form-control" onchange="totalCashDesk(this.value,'<s:property value="clientId"/>');">
					<option value="0">Select</option>
					<option value="BACS">BACS</option>
					<option value="Cheque">Cheque</option>
					<option value="C/Card">C/Card</option>
					<option value="Cash">Cash</option>
					<option value="D/Card">D/Card</option>
					<option value="D/D">D/D</option>
					<option value="Other">Other</option>
					<option value="S/O">S/O</option>
					<option value="<%=Constants.PREPYMENT %>"> Credit Balance <%=Constants.getCurrency(loginfo) %><s:property value="balanceAmount"/></option>
				</select>
				
				
				<%-- <label>Payment Amount <%=Constants.getCurrency(loginfo)%></label>
				 <s:textfield name="payAmount" id="totalamount"  value="%{balancex}"  cssClass="form-control" readonly="true"/>
				<s:hidden id = "totalamt" name="balance"></s:hidden>
				<s:if test="payby == 'Client'">
					<label>Discount in % (if any)</label>
					<s:if test="discount > 0">
					<s:textfield name="discount" disabled="true" theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
					
					</s:if>
					<s:else>
					<s:textfield name="discount" theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
					
					</s:else>
				</s:if>
				
				<label>Payment Received<%=Constants.getCurrency(loginfo) %></label>
				<s:textfield name="amount" id="payAmount" value="%{balancex}"
				cssClass="form-control" />
				<s:hidden id="ccdamt" name="ccdamt"></s:hidden> --%>
				</div>
			</div>
			
			<br>
			
			<div class="row">
				<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">	
					<label>Balance to Pay <%=Constants.getCurrency(loginfo)%></label>
				</div>
				<div class="col-lg-1 col-md-1 col-xs-2 col-sm-1">
					:	
				</div>
				<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">
					 <s:textfield name="payAmount" id="totalamount"  value="%{balancex}"  cssClass="form-control" readonly="true"/>
					 <s:hidden id = "totalamt" name="balance"></s:hidden>
				</div>	
			</div>	
			
			<br>
			
				<div class="row">
					<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">	
						<label>Discount in % (if any)</label>
					</div>
					<div class="col-lg-1 col-md-1 col-xs-2 col-sm-1">
						:
					</div>
					<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">	
						<s:if test="discount > 0">
							<s:textfield name="discount" disabled="true" theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
						</s:if>
						<s:else>
							<s:textfield name="discount" theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
						</s:else>
					</div>
			
				</div>
				
				<br>
			
			
			
			<div class="row">
				<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">	
					<label>Payment Received <%=Constants.getCurrency(loginfo) %></label>
				</div>
				<div class="col-lg-1 col-md-1 col-xs-2 col-sm-1">	
					:
				</div>
				<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">	
					<s:textfield name="amount" id="payAmount" value="%{balancex}"
					cssClass="form-control" />
					
					<s:hidden id="ccdamt" name="ccdamt"></s:hidden>
				
				</div>
			
			</div>
			
			<br>
			
			<div class="row">
	
				<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">	
					<label>Credit Balance <%=Constants.getCurrency(loginfo) %></label>
				</div>
				<div class="col-lg-1 col-md-1 col-xs-2 col-sm-1">	
					:
				</div>
				
				<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">	
					<s:textfield name="crdAmount" id="crdAmount" cssClass="form-control" placeholder="0.00"/>
				</div>
			
			</div>
			
			<br>
			
			<div class="row">
				<div class="col-lg-2 col-md-2 col-xs-5 col-sm-2">	
					<label>Payment Note : </label>
				</div>
				<div class="col-lg-1 col-md-1 col-xs-2 col-sm-1">
					:	
				</div>
				<div class="col-lg-6 col-md-6 col-xs-5 col-sm-2">
					<s:textarea name="paymentNote" id="paymentNote" rows="4" cols="50"/>
				</div>	
			</div>		
			
			</br>
			<div class="col-lg-6 col-md-6">
				<input type="button" class="btn btn-primary" value="Record Payment" onclick="saveInvoiceCashDesk()" style="float: right;margin-right: -16px;">
			</div>
			<div class="col-lg-6 col-md-6"></div>
				
			<s:token></s:token>
				
			</s:form>
											

											
										</div>
									</div>
								</div>
							</div>
						</div>


	



	
	
		
			
			
	
	
	
	
		<!-- Modal Email-->
<div class="modal fade" id="creditnotepopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Credit Ammount</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div class="row">
					<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">	
					</div>
				
					</div>
						<div class="form-group">
							<label>The paid amount (currency to be added to <span id="crdpaidamt"></span>)  is greater than balaence (Currency <span id="crdbalamt"></span>) amount. The remaining amount (Currency <span id="crdremainamt"></span>) will store to clients credit account</label>
							
						</div>
						
						
						<div class="form-group">
							<label>Credit Note:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="10" cols="60"
								title="Enter Body" name="creditnote"  id="creditnote" ></textarea>
						</div>
						
						
						
						<div class="form-group">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveCreditAmt();">OK</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


			
