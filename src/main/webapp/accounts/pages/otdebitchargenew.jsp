<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="accounts/js/accounts.js"></script>
<script type="text/javascript" src="accounts/js/additionalCharges.js"></script>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>
window.onload = function () {
	
	currencySign = '<%=Constants.getCurrency(loginfo)%>';	
}
</script>

<style>
hr {
    margin-top: 5px;
    margin-bottom: 5px !important;
    border: 0;
    border-top: 1px solid #eee;
}
.backsectable{
	    background-color: cadetblue !important;
}
.totalmar{
	    margin-top: 10px;
    margin-bottom: 15px;
}
.totlaborder{
    padding-right: 0px;
    margin-top: -25px;
    border-top: 1px solid #868686;
}
input[type="checkbox"] {
    margin: 2px 0 0 !important;
}


</style>
<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Add Charge</h4>
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
			
			<div class="col-lg-12 col-md-12 topback2" style="display: none;">
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
		<div class="col-lg-1 col-md-2 col-xs-1 col-sm-1 marboth" style="padding-right: 0px;text-align: right;">
			<label>Select Client:</label>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 marboth">
			<s:textfield name="client" id="client"  readonly="true" cssClass="form-control" onclick="showPopUpDetails()" data-toggle="modal" data-target="#clientSearch"/>
			<s:hidden name="clientId" id="clientId"></s:hidden>
			<label id = "clientError" class="text-danger"></label>			
		</div>
	</div>	
	
	
	<div class="row">
		<div class="col-lg-1 col-md-2 col-xs-6 col-sm-6 ">
			<label class="hidden">Which Account To Update?</label>
		</div>
		<div class= "col-lg-3 col-md-3 col-xs-3 col-sm-3" >
			<input style="display: none;" type="radio" id="payBuy1" name="payby" value="0" checked="checked"> <label>Patient Account : </label> <br>
			<label id="clientDetailsId"></label>
		</div>
		<div class= "col-lg-3 col-md-3 col-xs-3 col-sm-3" >
			<input style="display: none;" type="radio"	id="payBuy2" name="payby" value="1"> <label>Third Party Account : </label> <br>
			<label id="tpDetailsId"></label>
		</div>
		<label id = "paybyError" class="text-danger"></label>
	</div>
	
	<hr/>
	
	<div class="row marboth">
		<div class="col-lg-1 col-md-2 col-xs-3 col-sm-3" style="padding-right: 0px;text-align: right;">
			<label>Department:</label>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<%-- <s:select id="location" name="location" listKey="id" theme="simple"
				headerValue="All" headerKey="All" listValue="location"
				list="locationList" value="location" cssClass="form-control"></s:select>
				<label id = "locError" class="text-danger"></label> --%>
				
					<s:if test="%{#locationList != 'null'}">
									<s:select name="location" id="location" list="locationList" headerKey="0" headerValue="Select Location"
										listKey="id" listValue="location" theme="simple"
										cssClass="form-control chosen-select" />
								</s:if>
									<label id = "locError" class="text-danger"></label>
		</div>
	</div>	
	
</div>
</div>
		
<div class="">
	<div class="">
	
	
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
				<table class="table table-bordered"> 
			   		<thead> 
			   			<tr> 
			   				<th>Paid By</th> 
			   				<th>Charge Type</th> 
			   				<th>Charge Name</th>
			   			</tr> 
			   		</thead> 
			   		<tbody> 
			   			<tr> 
			   				<td>
			   					<input type="radio" id="payBuy" name="payBuy" value="0" checked="checked" style="display: inline; float: left;padding: 3px;"> <label for="payBuy" style="display: inline;padding: 3px;">Self</label><hr>
								<input type="radio" id="payBuy1" name="payBuy" value="1" style="display: inline; float: left;padding: 3px;"><label for="payBuy" style="display: inline; float: left;padding: 3px;">TP</label>
			   				</td> 
			   				<td>
			   					<div >
									<s:select theme="simple" name="masterchargetype" id="masterchargetype" list="masterChageTypeList" listKey="name" listValue="name"
										headerKey="0" headerValue="Select Charge Type" cssClass="form-control showToolTip chosen-select"
										onchange="filterCharges(this.value)"/>
								</div>
			   				</td> 
			   				<td style="padding-right: 0px !important;">
			   				<div style="height:150px;overflow:auto;">
			   					<table class="table table-bordered" style="margin-bottom: 6px;"> 
							   		<thead> 
							   			<tr> 
							   				<th class="backsectable">#</th> 
							   				<th class="backsectable"><input type="checkbox"></th> 
							   				<th class="backsectable" style="width: 66%;">Subcategory</th> 
							   				<th class="backsectable" style="width: 14%;">Qty</th> 
							   				<th class="backsectable" style="width: 14%;text-align:right">Fees</th> 
							   			</tr> 
							   		</thead> 
							   		<tbody> 
							   			<tr> 
							   				<th scope="row">1</th> 
							   				<td><input type="checkbox"></td> 
							   				<td>OPD NEW</td> 
							   				<td><input style="text-align: center;" type="text" name="quantity" maxlength="2" value="1" class="form-control"></td> 
							   				<td class="text-right">Rs.200</td> 
							   			</tr>
							   			<tr> 
							   				<th scope="row">2</th> 
							   				<td><input type="checkbox"></td> 
							   				<td>OPD OLD</td> 
							   				<td><input style="text-align: center;" type="text" name="quantity" maxlength="2" value="1" class="form-control"></td> 
							   				<td class="text-right">Rs.150</td> 
							   			</tr>
							   			<tr> 
							   				<th scope="row">3</th> 
							   				<td><input type="checkbox"></td> 
							   				<td>HDD</td> 
							   				<td><input style="text-align: center;" type="text" name="quantity" maxlength="2" value="1" class="form-control"></td> 
							   				<td class="text-right">Rs.175</td> 
							   			</tr>
							   			<tr> 
							   				<th scope="row">4</th> 
							   				<td><input type="checkbox"></td> 
							   				<td>HDD</td> 
							   				<td><input style="text-align: center;" type="text" name="quantity" maxlength="3" value="1" class="form-control"></td> 
							   				<td class="text-right">Rs.175</td> 
							   			</tr>
							   			
							   		</tbody> 
							   	</table>
							  
			   				</div>
			   					
							   	<table class="table table-bordered" style="background-color: #efefef;">
							   		<tbody> 
							   			<tr> 
							   				<td style="width: 84%;border-top: none !important;padding: 10px !important;"><input type="text"class="form-control" placeholder="Manual Charge"></td> 
							   				<td style="border-top: none !important;padding: 10px !important;"><input style="text-align: right" type="text" name="Fees" placeholder="Rs." class="form-control"></td> 
							   			</tr>
							   		</tbody> 
							   	</table>
			   				</td> 
			   			</tr>
			   		</tbody> 
			   	</table>
			   	
			   	
			   	
			   	
			   	
			   	<div class="col-lg-12 col-md-12 col-xs-12 text-right totlaborder">
			   		<h3 class="totalmar">Total : Rs.525</h3>
			   		<input id="addchargebtn" type="button" value="Add Charge" onclick="getInventoryProductStock()" disabled="disabled" class="btn btn-primary">
			   	</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
		
		</div>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			<label id = "recerror" class="text-danger"></label>
		</div>
	</div>
	</div>	
	
	
	
	
	
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				   	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
				   	<h4>View All Charges</h4>
						<table class="table table-bordered" cellspacing="0">
										<thead>
											<tr>
											<th style="width: 9%;">Paid By</th>
												<th class="chartype" style="width: 30%;">Charge Type</th>
												<th style="width: 40%;">Charge Name</th>
												<th class="text-center" style="width: 9%;">Qty</th>
												<th class="text-right" style="width: 9%;">Unit Price</th>
												<th class="amount text-right" style="width: 7%;">Amount</th>
												<th style="width: 2%;"></th>

											</tr>
										</thead>
										<tbody id="cashDesk"></tbody>
									</table>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
					
					</div>
		</div>
	</div>
	
			   	
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
					<div class="col-lg-2 col-md-2 col-xs-12 col-sm-2" style="padding-left: 0px;">
						<s:submit id="createchargebtn"  value="Create Charge" cssClass="btn btn-primary"  theme="simple" ></s:submit>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-12 col-sm-2" style="padding-left: 0px;">	
							<s:form action="createInvoiceAdditional" id = "createInvoiceAdditional">
								<s:hidden name="clientId" id="hdnciclientid"/>
								<s:hidden name="client" id="hdncliclient" />
								<s:hidden name="apmtType" id="hdnciapmttype"/>
								<s:hidden name = "location" id="hdnciloc"></s:hidden>
								<s:hidden name= "payby" id="hdncipayby" ></s:hidden>
								
								<input type="submit" id="createinvoicebtn" class="btn btn-primary" value=" Create Invoice ">
							</s:form>
					</div> 
					<div class="col-lg-2 col-md-2 col-xs-12 col-sm-2" style="padding-left: 0px;">	
							<s:form action="casdeskAdditional" id = "casdeskAdditional">
								
								<s:hidden name="clientId" id="hdncdclientid" />
								<s:hidden name="client" id="hdncdclient" />
								<s:hidden name="apmtType" id="hdncdapmttype"/>
								<s:hidden name = "location" id="hdncdloc"></s:hidden>
								<s:hidden name= "payby" id="hdncdpayby" ></s:hidden>
								<s:hidden name = "creditDebitCharge" id="casdeskAdditional_creditDebitCharge"></s:hidden>
								<input type="submit"  class="btn btn-primary" value=" Cash Desk " >
							</s:form>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-12 col-sm-2" style="padding-left: 0px;">
						<s:form action="estimateCharges" theme="simple" id="estimatefrm" target="formtarget">
								<s:hidden name="clientId" id="estimateclientid"/>
								<s:hidden name="actionType" value="0"/>
									<button type="button" class="btn btn-primary" 
										onclick="createdebitestimate()">View Estimate</button>
							</s:form>
					</div>
					
					
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
					
				</div>
			</div>
		</div>	   	
	
	
	
	
	<table class="table table-bordered widthdebicha hidden" cellspacing="0">
										<thead>
											<tr>
												<th style="width: 9%;">Paid By</th>
												<th class="chartype" style="width: 30%;">Charge Type</th>
												<th style="width: 40%;">Charge Name</th>
												<th class="text-center">Qty</th>
												<th class="text-right" style="width: 10%;">Unit Price</th>
												<th class="amount text-right" style="width: 7%;">Amount</th>
											</tr>
										</thead>


										<tbody>
												<tr>

													<td>
														
													</td>
													<td>
														
													</td>
													<td>
														<div id="additionalChargeAjax" style="margin-bottom: 2px;">
														<s:select theme="simple" name="apmtType" id="chargeTYpe" list="additionalChargesList" listKey="id" listValue="name"
														headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen-select"
														onchange="setAdditionalChargeAjax1(this.value)"/>
														</div>
														<input type="text" onchange="calcmanualcharge()"  name="mannualcharge"  id="mannualcharge"
														 class="form-control" placeholder="Manual Charge">
													</td>
													<td><input onchange="calcamount()" style="width: 66px;text-align: center;" type="text" id="quantity" name="quantity" 
														maxlength="2" value="1" class="form-control"></td>
													<td>
													<div  id="chargeajax">
														<input  type="text" onchange="calcmanualcharge()" onkeypress="return isNumberKey(event,this);" style="text-align:right;" id="charge" name="charge" class="form-control" />
													</div>
													
													<input type="hidden" name="hdncharge" id="hdncharge">
													
													</td>
													<td id="amount" class="text-right"></td>
												</tr>
											
												
											
										</tbody>
									</table>
									





</s:form>
</div>
 
		
		
		
		

</div>


		
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
  
  

