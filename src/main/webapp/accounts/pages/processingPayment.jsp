<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="accounts/js/accounts.js"></script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>

<script type="text/javascript" src="accounts/js/chargeAccountProcessing.js"></script>

<style>
.topback2 {
    background-color: #f5f5f5;
    padding: 10px 10px 10px 10px;
}
.selftlefpaymenu{
	background-color: #fff;
    padding-left: 15px;
    padding-top: 0px;
    padding-bottom: 10px;
    min-height: 580px;
    border-left: 1px solid #ddd;
}
</style>

<script>


$(window).on('load', function() {
	$('#newloaderPopup').modal('hide');
});
	$(document).ready(function() {
		 $('#newloaderPopup').modal('show');
		 $("#invoiceDate").datepicker({

				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true

			});
	});
</script>

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
		<s:form action="payAmtProcessingAccount" theme="simple" id="paymentProcessingfrm">
		<s:hidden name="ledgerservicestr" id="ledgerservicestr"/>
		<s:hidden name="clraradv" id="clraradv"/>
			<s:hidden name="client" id="client" />
			<s:hidden name="clientId" id="clientId"/>
			<s:hidden name="payby" id="payby"/>
			<s:hidden name="invoiceid" id="invoiceid"/>
			<%-- <s:hidden name="crdAmount" id="crdAmount"/> --%>
			<s:hidden name="creditNote" id="creditNotes"/>
			
			<s:hidden name="hdnmorehowpaid" id="hdnmorehowpaid"/>
			<s:hidden name="hdnmorepaudamount" id="hdnmorepaudamount"/>
			<s:hidden name="hdnbnkname" id="hdnbnkname"/>
			
			
			<s:hidden name="refpayMode" id="refpayMode"/>
			
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2">
			  <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
				<div class="form-group" style="margin-bottom:0px;">
					<input type="text" id="invoiceDate" name="invoiceDate" class="form-control"  value="<%=temp[0] %>" style="width: 40%;" readonly="true"></input></div>
				</div>
				
			  <div class="col-lg-10 col-md-10 col-xs-12 col-sm-12">	
				<div class="form-group" style="margin-bottom:0px;">
				 <h4 style="color: red;">*Note : <b><s:property value="Notes"/></b></h4>
				 
					</div>
				</div>
			</div>
			
			
			
			<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
				<div class="col-lg-7 col-xs-7 col-md-7" style="padding: 0px;">
				<h4 style="color: #16a085;">Record Payment for below Invoice <b><s:property value="invoiceid"/></b></h4>
				<span style="float: right;margin-top: -24px;margin-right: 15px;font-weight: bold;">Debit- <%=Constants.getCurrency(loginfo) %><s:property value="debitAmt"/>&nbsp; | &nbsp;Credit- <%=Constants.getCurrency(loginfo) %><s:property value="creditCharge"/></span>
				<%int count=1;%>
				<div>
						<table class="table table-bordered" id="assementtable">
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
							
							<td><s:property value="invoiceNo"/>
									<a href="javascript: void(0);" onclick="showInnerSubDiv('hiddenDetailsDiv<s:property value="invoiceNo"/>','<s:property value="invoiceNo"/>');"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
							
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
						<td style="color: green;"><%=Constants.getCurrency(loginfo)%><s:property value="debitAmt"/></td>
					</tr>
				</tbody>
				</table>
				<input type="hidden" name="hdndiscdebit" id="hdndiscdebit" value="<s:property value="debitAmt"/>"/>
			
				</div>
				<div class="col-lg-8 col-md-12 col-xs-12" style="padding: 0px;">
						<table class="table table-bordered" >
						<thead>
							<tr>
							<th>Services</th>
							<th>Amount</th>
							</tr>
							</thead>
							
								<tbody>
							<s:iterator value="ledgerservicesList">
								<tr>
									<td> <input type="checkbox" name="lservicechid" class="lservicechidcase"
									id="<s:property value="id"/>"
									value="<s:property value="charge"/>" checked="checked"
									onclick="calcledgerrecamt('<s:property value="charge"/>','<s:property value="id"/>');">
									<s:property value="name" />
									</td>
									
								<td><%=Constants.getCurrency(loginfo)%><s:property value="charge" /></td>

								</tr>
							</s:iterator>
							</tbody>
						</table>
				</div>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-5 selftlefpaymenu">
				<input type="hidden" name="hdntotal" id="hdntotal" value="<s:property value="debitAmt"/>" />
		<s:hidden id="hiddenbalence" name="balanceAmt"/>	
		<s:hidden id="prepaymntamntid" name="balanceAmount"/>
				
					<h4 style="color: #16a085;">Payment Details</h4>
	
					<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">  
		      <div class="form-group hidden">
			    <label for="exampleInputEmail1">Invoice Type</label>
			    <s:select name="invcetype" id="invcetype" list="invoiceTypeLis" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Invoice Type"/>
			  </div>
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1">Balance to Pay <%=Constants.getCurrency(loginfo)%></label>
					    <s:textfield name="payAmount" id="totalamount"  value="%{balancex}"  cssClass="form-control" readonly="true"/>
					 <s:hidden id = "totalamt" name="balance"></s:hidden>
					  </div>
				</div>
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<div class="form-group">
		    <label for="exampleInputEmail1">Payment Mode</label>
		    <select name="howpaid" id="howpaid" class="form-control" onchange="getchno(this.value,<s:property value="DebitAmt" />,<s:property value="balanceAmount"/>)">
				<option value="0">Select</option>
				<option value="Cash">Cash</option>
				<option value="Cheque">Cheque</option>
				<option value="NEFT">NEFT</option>
				<!--<option value="BACS">BACS</option>-->
				<!-- <option value="C/Card">Credit Card</option> -->
				<option value="D/Card">Card</option>
				<option value="UPI">UPI</option>
				
				<!--<option value="S/O">S/O</option>
				--><s:if test="creditDebitCharge==0">
					<option value="<%=Constants.PREPYMENT %>"> Credit Balance <%=Constants.getCurrency(loginfo) %><s:property value="balanceAmount"/></option>
				</s:if>
			</select> 
		  </div>
		  
		     
		  
			</div>
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12" >
			<label for="exampleInputEmail1">Select Bank</label>
				 <s:select name="bnkname" id="bnkname" list="bankNameList"
		  cssClass="form-control" listKey="id" listValue="name"
		  headerKey="0" headerValue="Select Bank"
		  />
			</div>
			
			<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<div class="form-group hidden" id="shcheck" >
							<s:textfield cssClass="form-control" name="chequeno" placeholder="Enter Cheque Number" title="Enter Cheque Number"></s:textfield> 
							<s:textfield cssClass="form-control" name="bankname" placeholder="Enter Bank Name" title="Enter Bank Name"></s:textfield>
		     </div>
			</div>
			
		</div>
	</div>
	
	<div class="row" id="morepaymntdivid" style="display: none;">
	
		<label for="exampleInputEmail1"> More Payment</label>
		<div><a href="#" onclick="showmorepaymentpopup(<s:property value="debitAmt"/>,<s:property value="balanceAmount"/>)"> + </a></div>
	</div>
	
	
	<div class="row">
		<%if(loginfo.isShowdiscount()){ %>
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="background-color:rgba(245, 245, 245, 0.69);padding: 10px 0px 0px 0px;">
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Discount Type </label>
					    <s:if test="discount>0 ">
					    	<s:select disabled="true" list="#{'0':'Percent','1':'Rupees'}" theme="simple" name="disctype" id="disctype" cssClass="form-control" onchange="checkdisclimit()"/>
					   		<s:hidden name="disctype"></s:hidden>
					   	</s:if>
					   	<s:else>
					   		<s:select  list="#{'0':'Percent','1':'Rupees'}" theme="simple" name="disctype" id="disctype" cssClass="form-control" onchange="checkdisclimit()"/>
					   	</s:else>
					  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Amount</label>
					    <% if(loginfo.getUserType()==2){%>
							<s:if test="discount>0 ">
								<s:textfield name="discount" readonly="true" theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
							</s:if>
							<s:else>
								<s:textfield name="discount"  theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
							</s:else>
							
						<%}else{ %>
						
							<s:textfield name="discount" readonly="true" theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
						<% }%>
					  </div>
				</div>
		</div>
		<%}else{ %>
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12 " style="background-color:rgba(245, 245, 245, 0.69);padding: 10px 0px 0px 0px;">
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12 hidden">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Discount Type </label>
					    <s:if test="discount>0 ">
					    	<s:select disabled="true" list="#{'0':'Percent','1':'Rupees'}" theme="simple" name="disctype" id="disctype" cssClass="form-control" onchange="checkdisclimit()"/>
					   		<s:hidden name="disctype"></s:hidden>
					   	</s:if>
					   	<s:else>
					   		<s:select  list="#{'0':'Percent','1':'Rupees'}" theme="simple" name="disctype" id="disctype" cssClass="form-control" onchange="checkdisclimit()"/>
					   	</s:else>
					  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12 hidden">
					<div class="form-group">
					    <label for="exampleInputEmail1" style="color: #d9534f;">Amount</label>
					    <% if(loginfo.getUserType()==2){%>
							<s:if test="discount>0 ">
								<s:textfield name="discount" readonly="true" theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
							</s:if>
							<s:else>
								<s:textfield name="discount"  theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
							</s:else>
							
						<%}else{ %>
						
							<s:textfield name="discount"  theme="simple" id="discount" value="%{discount}" cssClass="form-control" onchange="setAmountDue()"/>
						<% }%>
					  </div>
				</div>
		</div>
		<%} %>
	
	</div>
	
	
	<div class="row">
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
				    <label for="exampleInputEmail1">Payment Received <%=Constants.getCurrency(loginfo) %></label>
				   		<s:textfield name="amount" id="payAmount" value="%{balancex}"
					cssClass="form-control" />
					
					<s:hidden id="ccdamt" name="ccdamt"></s:hidden>
				  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					<div class="form-group">
					    <label for="exampleInputEmail1">Credit Balance <%=Constants.getCurrency(loginfo) %></label>
					   		<s:textfield name="crdAmount" id="crdAmount" cssClass="form-control" placeholder="0.00"/>
					  </div>
				
				</div>
				<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
					
				
				</div>
		</div>
	</div>
	
	
	<div class="row">
		<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="padding: 5px 0px 0px 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-right: 0px;">
					<div class="form-group">
					    <label for="exampleInputEmail1">Payment Note</label>
					   		<s:textarea name="paymentNote" id="paymentNote" rows="2" style="width: 100%;" cssClass="form-control"/>
					  </div>
				</div>
				
		</div>
		<s:if test="statusrequestdiscamt==true">
			<div class="col-lg-12 col-sm-12 col-xs-12 col-sm-12" style="padding: 5px 0px 0px 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-right: 0px;">
						 <div class="form-group">
						    <label for="exampleInputEmail1" style="color: #d9534f;">Note:</label>
						   	<label style="color: #d9534f;">Discount request of amount Rs.<s:property value="discountamt"/>  is pending.</label>
						  </div>
					</div>
			</div>
		</s:if>
		
	</div>
	
	
	<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		   <input type="hidden" id="adv" value="<s:property value="balanceAmount"/>">
		    <input type="hidden" id="debt" value="<s:property value="DebitAmt" />">
			<input type="button" class="btn btn-primary pull-right" value="Record Payment" onclick="saveInvoiceCashDesk()">
		</div>
	</div>
	<% String clinicid=loginfo.getClinicid1(); %>
	<input  type="hidden" id="clinicid" value="<%=clinicid%>">

	<s:token></s:token>
				
			</s:form>
	
	
		  
		  
		  
			
	</div>
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

<!-- Modal -->
<div id="morepaymentmodelpopupid" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add More Payment</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" name="selectedid" id="pro_id">
      		<div class="form-group" style="margin-bottom: 0px;">
	      		  <label for="exampleInputEmail1">Payment Mode</label>
		    <select name="morehowpaid" id="morehowpaid" class="form-control" ">
				<option value="0">Select</option>
				<option value="Cash">Cash</option>
				<option value="Cheque">Cheque</option>
				<option value="NEFT">NEFT</option>
				<!--<option value="BACS">BACS</option>-->
				<!-- <option value="C/Card">Credit Card</option> -->
				<option value="D/Card">Card</option>
				<option value="UPI">UPI</option>
				<option value="Other">Other</option>
				
			</select> 
      		</div>
      		<div class="form-group" style="margin-bottom: 0px;">
	      		  <label for="exampleInputEmail1">Select Bank</label>
	      		    <s:select name="morepaybank" id="morepaybank" list="bankNameList"
		  cssClass="form-control" listKey="id" listValue="name"
		  headerKey="0" headerValue="Select Bank"
		  />
	      	</div>
      		<div class="form-group" style="margin-bottom: 0px;">
	      		  <label for="exampleInputEmail1">Payment Received <%=Constants.getCurrency(loginfo) %></label>
				   		<input type="number" name="moreamount" id="morepayAmount" 
					class="form-control" value="0" />
      		</div>
      </div>
      <div class="modal-footer">
         <input type="button" onclick="setmorepaymenttoform()" class="btn btn-danger" value="Ok">
      </div>
    </div>
  </div>
</div>



<div id="refmodeppup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Please Confirm The Payment Mode </h4>
      </div>
      <div class="modal-body">
      <label>Select Payment Mode For Refund</label>
    	   <select name="refhowpaid" id="refhowpaid" class="form-control" ">	
				<option value="Cash">Cash</option>
				<option value="Cheque">Cheque</option>
				<option value="NEFT">NEFT</option>
				<option value="D/Card">Card</option>
				<option value="UPI">UPI</option>
			</select> 			
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-info " onclick="goforrefundprepay(1)" data-dismiss="modal" value="Ok">
         <input type="button" class="btn btn-success  close" onclick=""  style="font-size: inherit;"  data-dismiss="modal" value="Cancel">
      </div>
    </div>

  </div>
</div>



	<div id="finalconfirmpopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Please Confirm Selected Information </h4>
      </div>
      <div class="modal-body">
    	<span>  Payment Mode:<h4 id="finalconfirmPaymode"></h4></span>
    	<span> 	Invoice Type:<h4 id="finalconfirmInvtype"></h4> </span>
    	<span> 	Amount:<h4 id="finalconfirmAmount"></h4> </span>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-info" onclick="submitFinalConfirmNew()" data-dismiss="modal" value="Ok">
        
         <input type="button" class="btn btn-success  close" onclick=""  style="font-size: inherit;"  data-dismiss="modal" value="Cancel">
      </div>
    </div>

  </div>
</div>		

<!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);z-index: 99" id="newloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->

