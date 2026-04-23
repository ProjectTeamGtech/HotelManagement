<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<script type="text/javascript" src="accounts/js/accounts.js"></script>
<script type="text/javascript" src="accounts/js/additionalCharges.js"></script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>
window.onload = function () {
	
	currencySign = '<%=Constants.getCurrency(loginfo)%>';	
}
</script>



<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Raise Additional Charge</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<s:form action="saveAdditional" theme="simple">
<div class="row">
	
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<div class="">
			<div class="">
			
			<div class="col-lg-12 col-md-12 topback2">
				<div class= "col-lg-1 col-md-1 col-xs-12 col-sm-2">
					<label>Create</label>
				</div>
				<div class= "col-lg-3 col-md-3 col-xs-3 col-sm-3">
					<input type="radio" id="creditCharge" name="creditDebitCharge" value="1" onclick="setresetaction()" > <label>Credit Charge</label> <br>
					<input type="radio"	id="debitCharge" name="creditDebitCharge" value="0" checked="checked" onclick="setresetaction()"> <label>Debit Charge</label>
				</div>
				<label id = "creditDebitChargeError" class="text-danger"></label>
			</div>
			
			
	
	<div class="row ">
		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 marboth">
			<label>Select Client</label>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 marboth">
			<s:textfield name="client" id="client"  readonly="true" cssClass="form-control" onclick="showPopUpDetails()" data-toggle="modal" data-target="#clientSearch"/>
			<s:hidden name="clientId" id="clientId"></s:hidden>
			<label id = "clientError" class="text-danger"></label>			
		</div>
	</div>	
	
	
	<div class="row">
		<div class="col-lg-2 col-md-2 col-xs-6 col-sm-6">
			<label>Which Account To Update?</label>
		</div>
		<div class= "col-lg-3 col-md-3 col-xs-3 col-sm-3" >
			<input type="radio" id="payBuy1" name="payby" value="0" checked="checked"> <label>Client Account : </label> <br>
			<label id="clientDetailsId"></label>
		</div>
		<div class= "col-lg-3 col-md-3 col-xs-3 col-sm-3" >
			<input type="radio"	id="payBuy2" name="payby" value="1"> <label>Third Party Account : </label> <br>
			<label id="tpDetailsId"></label>
		</div>
		<label id = "paybyError" class="text-danger"></label>
	</div>
	
	<hr/>
	
	
	<div class="row marboth">
		<div class="col-lg-2 col-md-2 col-xs-3 col-sm-3">
			<label>Select Location</label>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
			<%-- <s:select id="location" name="location" listKey="id" theme="simple"
				headerValue="All" headerKey="All" listValue="location"
				list="locationList" value="location" cssClass="form-control"></s:select>
				<label id = "locError" class="text-danger"></label> --%>
				
					<s:if test="%{#locationList != 'null'}">
									<s:select name="location" id="location" list="locationList" headerKey="0" headerValue="Select Location"
										listKey="id" listValue="location" theme="simple"
										cssClass="form-control" />
								</s:if>
									<label id = "locError" class="text-danger"></label>
		</div>
	</div>	
	
	
</div>
</div>
		
<div class="">
	<div class="">
	<table class="table table-bordered" cellspacing="0" style="width:70% !important;">
										<thead>
											<tr>
												<th class="cre1">Paid By</th>
												<th class="cre2">Charge Type</th>
												<th class="cre3">Charge Name</th>
												<th class="text-center">Qty</th>
												<th class="text-right">Unit Price</th>
												<th class="amount text-right">Amount</th>

											</tr>
										</thead>


										<tbody>
												<tr>

													<td>
														<label class="selftext">Self</label>
													</td>
													<td>
														<select class="form-control">
														  <option value="Hardcoded">Hardcoded</option>
														  <option value="Hardcoded">Hardcoded</option>
														  <option value="Hardcoded">Hardcoded</option>
														  <option value="Hardcoded">Hardcoded</option>
														</select>
													</td>
													<td>
													<s:select name="apmtType" id="apmtType" list="additionalChargesList" listKey="id" listValue="name" headerKey="0" headerValue="Select Additional Charge" cssClass="form-control showToolTip chosen" title="Select Appointment Type" theme ="simple" onchange="disabledText()"></s:select>
														<label id = "apmtTypeError" class="text-danger"></label>
													</td>
													<td><input style="width: 66px;text-align: center;" type="text" id="quantity" name="quantity" maxlength="2" value="1" class="form-control"></td>
													<td>
													<div id="chargeajax">
														<input type="text" id="charge" name="charge" class="form-control" disabled="disabled">
													</div>
													
													</td>
													<td>
														<s:textfield name="chargeAmount" id="chargeAmount"  cssClass="form-control" placeholder = "Enter Amount"/>
														<label id = "chargeAmountError" class="text-danger"></label>
													</td>
												</tr>
											
												
											
										</tbody>
									</table>
	
	
	


<div class="row">
<div class="col-lg-4 col-md-4"></div>
<div class="col-lg-4 col-md-4">
<label class="text-center creormanu"> OR </label>
</div>
<div class="col-lg-4 col-md-4"></div>
</div>

<div class="row">
<div class="col-lg-6 col-md-6">
<table class="table table-bordered" cellspacing="0" style="width:100% !important;">
										<thead>
											<tr>
												<th>Other (Manual)</th>
												<th class="amount">Amount</th>

											</tr>
										</thead>
										<tbody>
										<tr>
										<td>
											<s:textfield name="manualTypeName" id="manualTypeName"  cssClass="form-control" placeholder = "Enter Type Name"/>
											<label id = "manualTypeNameError" class="text-danger"></label>	
										</td>
										<td>
											<s:textfield name="manualCharge" id="manualCharge"  cssClass="form-control" placeholder = "Enter Amount"/>
											<label id = "manualChargeError" class="text-danger"></label>
										</td>
										</tr>
										
										</tbody>
										</table>
										</div>
										<div class="col-lg-6 col-md-6">
											<input type="button" value="Add" onclick="addCharge()" class="btn btn-primary"> 
										</div>
										</div>

<br>
<div class="col-lg-12 col-md-12 row viachar">
<label>View All Charges</label>
<table class="table table-bordered" cellspacing="0" style="width:100% !important;">
										<thead>
											<tr>
												<th class="cre1">Paid By</th>
												<th class="cre2">Payment</th>
												<th class="cre3">Charge Name</th>
												<th class="text-center">Qty</th>
												<th class="text-right">Unit Price</th>
												<th class="amount text-right">Amount</th>
												<th style="width: 2%;"></th>

											</tr>
											
										</thead>
										<tbody>
										<td>Self</td>
										<td>CREDIT</td>
										<td>CALCIUM</td>
										<td class="text-center">1</td>
										<td class="text-right">135</td>
										<td class="text-right">135</td>
										<td><i class="fa fa-times text-danger"></i></td>
										<tr><th colspan="5"><b class="selftext">Total Charges</b></th> <th colspan="5" class="text-right" id="chargeTotalajax"><input type="text" class="form-control showToolTip" id="chargeTotal" name="chargeTotal" value="0.00" disabled="disabled"/></th> </tr>
										</tbody>
									</table>
									
</div>

<label id = "recerror" class="text-danger"></label>

<div class="row text-info">
	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
		<div >
		
			<table id="cashDesk"
				class="table table-bordered">
				<thead>
					<tr>
						<th>No</th>
						<th>Charge Details</th>
						<th>Amount</th>
						<th>Charge Type</th>
						<th>To</th>
						<th>Delete</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<div class="row" >
<div class="col-lg-8 col-md-8">
<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">	

<s:submit id="createchargebtn"  value="Create Charge" cssClass="btn btn-primary"  theme="simple" onclick="return setLocValidate()"></s:submit>

</s:form>
</div>
<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">	
				<s:form action="casdeskAdditional" id = "casdeskAdditional">
					
					<s:hidden name="clientId" id="hdncdclientid" />
					<s:hidden name="client" id="hdncdclient" />
					<s:hidden name="apmtType" id="hdncdapmttype"/>
					<s:hidden name = "location" id="hdncdloc"></s:hidden>
					<s:hidden name= "payby" id="hdncdpayby" ></s:hidden>
					<s:hidden name = "creditDebitCharge" id="casdeskAdditional_creditDebitCharge"></s:hidden>
					<input type="submit"  class="btn btn-primary" value=" Cash Desk " onclick="return setLocValidate()">
				</s:form>
		</div>
<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">	
				<s:form action="createInvoiceAdditional" id = "createInvoiceAdditional">
					<s:hidden name="clientId" id="hdnciclientid"/>
					<s:hidden name="client" id="hdncliclient" />
					<s:hidden name="apmtType" id="hdnciapmttype"/>
					<s:hidden name = "location" id="hdnciloc"></s:hidden>
					<s:hidden name= "payby" id="hdncipayby" ></s:hidden>
					
					<input type="submit" id="createinvoicebtn" class="btn btn-primary" value=" Create Invoice " onclick="return setLocValidate()">
				</s:form>
		</div>  

</div>



		
		  
		<br><br><br>
		
</div>
<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/accounts/pages/allClientDetails.jsp"%>
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



