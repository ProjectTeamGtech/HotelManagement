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
	/*  var refundinvoiceid = document.getElementById('clientrefundinvoiceid').value;
	if(refundinvoiceid=='0'){
		
	}else{
		document.getElementById('newinvoiceid').value = refundinvoiceid;
		//checkinvoiceidandsetdate(refundinvoiceid);
	}  */
	
}
</script>

<script type="text/javascript">
$(window).on('load', function() {
	$('#dashboardloaderPopup').modal('hide');
});
		
	  
    $(document).ready(function() {
    	$('#dashboardloaderPopup').modal('show');
		$("#date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
	});
    
    function submitAdvance(){
    	if(document.getElementById("advancebtndiv").innerHTML!=""){
    		document.getElementById("casdeskAdditional").submit();
    		document.getElementById("advancebtndiv").innerHTML="";
    	}
    }
    
</script>


<style>
	.leftbox{
		background-color: #efefef;
	    padding: 10px 15px 0px 15px;
	    border: 1px solid #ddd;
	    box-shadow: 3px 3px 4px #ddd;
        margin-bottom: 15px;
	}
</style>


<div class="">
							<div class="" style="border: 1px dotted #ddd;">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Advance & Refund</h4>
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<s:form action="saveAdditional" theme="simple">
<s:hidden name="refundinvoiceid" id="clientrefundinvoiceid"></s:hidden>
<s:if test="approvedrefund==0">
	<s:hidden id="isfromrefundprocess" value="1"></s:hidden>
</s:if>
<div class="row">
	
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<div class="">
			<div class="">
			
			<div class="col-lg-12 col-md-12 topback2" style="display: none;">
				<div class= "col-lg-1 col-md-1 col-xs-12 col-sm-2">
					<label>Create</label>
				</div>
				<div class= "col-lg-3 col-md-3 col-xs-3 col-sm-3">
					<input type="radio" id="creditCharge" name="creditDebitCharge" value="1" checked="checked" onclick="setresetaction()" > <label>Credit Charge</label> <br>
					<input type="radio"	id="debitCharge" name="creditDebitCharge" value="0"  onclick="setresetaction()"> <label>Debit Charge</label>
				</div>
				<label id = "creditDebitChargeError" class="text-danger"></label>
			</div>
	<div class="col-lg-12 col-md-12" style="border-bottom: 1px solid #ddd;margin-bottom: 11px;padding-top: 12px;background-color: rgba(239, 239, 239, 0.3);">
		<div class="col-xs-3" style="padding-left: 0px;">
			<div class="form-group">
				<label>Select <%=loginfo.getPatientname_field() %></label>
			<s:textfield name="client" id="client"  readonly="true" cssClass="form-control" onclick="showPopUpDetails()" data-toggle="modal" data-target="#clientSearch"/>
			<s:hidden name="clientId" id="clientId"></s:hidden>
			<label id = "clientError" class="text-danger"></label>
			</div>
			
		</div>
		<div class="col-xs-2">
			<div class="form-group">
				<label>Department</label>
			<s:if test="%{#locationList != 'null'}">
									<s:select name="location" id="location" list="locationList" headerKey="0" headerValue="Select Location"
										listKey="id" listValue="location" theme="simple"
										cssClass="form-control chosen-select" />
								</s:if>
									<label id = "locError" class="text-danger"></label>
			</div>
			
		</div>
	
		
		<div class= "col-lg-3 col-md-3 col-xs-4 col-sm-4" >
			<input style="display: none;" type="radio" id="payBuy1" name="payby" value="0" checked="checked"> <label><%=loginfo.getPatientname_field() %> Account : </label> <br>
			<p id="clientDetailsId"></p>
		</div>
		<div class= "col-lg-3 col-md-3 col-xs-4 col-sm-4" >
			<input style="display: none;" type="radio"	id="payBuy2" name="payby" value="1"> <label>Third Party Account : </label> <br>
			<p id="tpDetailsId"></p>
		</div>
		<div class= "col-lg-3 col-md-3 col-xs-4 col-sm-4" >
			 <label>Date : </label> <br>
			<p ><s:textfield name="date" id="date" cssClass="form-control" placeholder="Select  Date" /></p>
		</div>
		<label id = "paybyError" class="text-danger"></label>
	</div>
	
	
	
	
</div>
</div>


<div class="col-lg-12 col-xs-12 col-sm-12" style="padding-right: 0px;">
	<div class="col-xs-3 col-md-3 leftbox">
			<div class="form-group">
			
				<input type="radio" id="payBuy" name="payBuy" value="0" checked="checked" style="display: inline; float: left;padding: 3px;"> <label for="payBuy" style="display: inline; float: left;padding: 3px;">Self</label> 
				<input type="radio" id="payBuy1" name="payBuy" value="1" style="display: none; float: left;padding: 3px;"><label for="payBuy" style="display: none; float: left;padding: 3px;">Third Party</label>
			</div>
			<div class="form-group">
					<s:select theme="simple" name="masterchargetype" id="masterchargetype" list="masterChageTypeList" listKey="name" listValue="name"
															headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen-select"
															onchange="filterCharges(this.value)"/>
			</div>
			
			<div class="form-group">
				<input type="text" onchange="calcmanualcharge()"  name="mannualcharge"  id="mannualcharge" class="form-control" placeholder="Enter Charge Name">
			</div>
			<div class="form-group">
				<div class="col-xs-12" style="padding: 0px;margin-bottom: 15px;">
					<div class="col-xs-4" style="padding-left: 0px;">
						<input onchange="calcamount()" style="text-align: center;" type="text" id="quantity" name="quantity" maxlength="3" value="1" class="form-control">
					</div>
					<div class="col-xs-4" style="padding-right: 0px;">
						<div  id="chargeajax">
							<input  type="text" onchange="calcmanualcharge()" onkeypress="return isNumberKey(event,this);"  id="charge" name="charge" class="form-control" placeholder="Enter Rs."/>
						</div>
						<input type="hidden" name="hdncharge" id="hdncharge">
					</div>
					<div class="col-xs-4" style="padding-right: 0px;">
						<p id="amount" style="text-align:right;"></p>
					</div>
				</div>
			</div>
				<% String clinicid=loginfo.getClinicid1(); %>
		    <input type="hidden" id="hiddenclientid" value="<%=clinicid%>">
			<input type="hidden" id="diaryUsersss" name="diaryUsersss" value="1">
			<input type="hidden" id="sessionno" value="0">
			<div class="form-group" id="addrefundbtnhide">
				<s:if test="approvedrefund==0">
					<input id="addchargebtn" type="button" value="Add" onclick="setChargeAmount()" disabled="disabled" class="btn btn-primary" style="width:100%;">
				</s:if>
				<s:else>
					
				</s:else>
				
			</div>
			<label id = "recerror" class="text-danger"></label>
			
		</div>
		
		<div class="col-xs-9 col-md-9">
				
									<div class="col-lg-12 col-xs-12" style="padding: 0px;">
										<div class="col-lg-3 col-xs-3">
											<h5 style="color: chocolate;">View All Charges</h5>
										</div>
										<div class="col-lg-4 col-xs-4">
											<div class="form-inline">
												<div class="form-group">
													<label>Invoice:</label>
												</div>
												<div class="form-group">
												<%-- <s:if test="checkinvoice=='true'">
													<input type="number" name="manualinvoiceid"   id="newinvoiceid" class="form-control" placeholder="Enter Invoice Id">
												</s:if>
												<s:else>
													<input type="number" name="manualinvoiceid" readonly="readonly"   id="newinvoiceid" class="form-control" placeholder="Enter Invoice Id">
												</s:else> --%>
												<s:if test="approvedrefund==1">
													<s:textfield  name="manualinvoiceid" readonly="true"  id="newinvoiceid" cssClass="form-control" placeholder="Enter Invoice Id"></s:textfield>
												</s:if>
												<s:else>
													<s:textfield  name="manualinvoiceid" id="newinvoiceid" cssClass="form-control" onchange="checkinvoiceidandsetdate(this.value)" placeholder="Enter Invoice Id"></s:textfield>
												</s:else>
												</div>
												
												
												
											</div>
										</div>
										
										<div class="col-lg-5 col-xs-5">
											<div class="form-inline">
												<div class="form-group">
													<label>Refund Note:</label>
												</div>
												<div class="form-group">
													<!-- <input type="text" id="refundnote" name="refundnote" style="width:100%;" class="form-control" placeholder="Enter Refund Note"> -->
													<!-- <textarea rows="2" cols="40" id="refundnote" name="refundnote" class="form-control" placeholder="Enter Refund Note"></textarea> -->
													
													<s:if test="approvedrefund==1">
													<s:textarea rows="2" cols="40" id="refundnote" readonly="true" name="refundnote" cssClass="form-control" placeholder="Enter Refund Note"></s:textarea>
												</s:if>
												<s:else>
													<s:textarea rows="2" cols="40" id="refundnote" name="refundnote" cssClass="form-control" placeholder="Enter Refund Note"></s:textarea>
												</s:else>
												</div>
												
												
											</div>
										</div>
										
										<table class="table table-bordered" cellspacing="0">
										<thead>
											<tr>
											<th style="width: 10%">Date</th>
											<th style="width: 11%">Paid By</th>
												<th style="width: 23%;">Charge Type</th>
												<!--<th>Charge Name</th>
												--><th style="width: 33%;">Charge Name</th>
												<th class="text-center" style="width: 8%">Qty</th>
												<th class="text-right" style="width: 9%;">Unit Price</th>
												<th class="amount text-right">Amount</th>
												<th style="width: 2%;"></th>

											</tr>
										</thead>


										<tbody id="cashDesk">
											<s:if test="approvedrefund==1">
												<%int i=0; %>
												<s:iterator value="clientChargeListDetail">
													<tr>
													<td>
														<s:if test="payBuy==0">
															Self
														</s:if>
														<s:else>
															Third party
														</s:else>
													</td>
													<td>
														<s:property value="masterchargetype"/>
													</td>
													<td>
														<s:property value="apmtType"/>
													</td>
													<td>
														<s:property value="quantity"/>
													</td>
													<td>
														<s:property value="charges"/>
													</td>
													<td>
														<s:property value="calCharge"/>
													</td>
													
														<%if(i==0){ %>
															<td><img src='common/images/delete.gif'></img></td>
														<%}else{ %>
															<%-- <td onclick ="confirmedDelete1(<s:property value="id"/>)"><img src='common/images/delete.gif'></img></td> --%>
															<td><img src='common/images/delete.gif'></img></td>
														<%} %>
													</tr>	
													<%i++; %>
												</s:iterator>
												
												<tr style='background-color: #efefef;'>
												<th colspan='5' style='font-size: 13px;font-weight: bold;'>Total</th>
												<th style='font-size: 13px;font-weight: bold;' colspan='5'><s:property value="chargeTotalx"/></th>
												</tr>
												
												<input class='form-control' type ="hidden" id ="hiddenTotal" name ="hiddenTotal" value ="<s:property value="chargeTotalx"/>">
											</s:if>
												
											
										</tbody>
									</table>
									</div>
			</div>
		
		

</div>



		
<div class="">
	<div class="">
	<table class="table table-bordered widthdebicha hidden" cellspacing="0">
										<thead>
											<tr>
												<th class="padby">Paid By</th>
												<th style="width: 30%;">Charge Type</th>
												<!--<th>Charge Name</th>
												--><th style="width: 40%;">Manual Charge</th>
												<th class="text-center">Qty</th>
												<th class="text-right" style="width: 9%;">Unit Price</th>
												<th class="amount text-right">Amount</th>

											</tr>
										</thead>


										<tbody>
												<tr>

													<td>
														
													</td>
													<td>
														<div >
														<s:select theme="simple" name="masterchargetype" id="masterchargetype" list="masterChageTypeList" listKey="name" listValue="name"
															headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen-select"
															onchange="filterCharges(this.value)"/>
														</div>
													</td>
													<td style="display: none;">
														<div id="additionalChargeAjax">
														<s:select theme="simple" name="apmtType" id="chargeTYpe" list="additionalChargesList" listKey="id" listValue="name"
														headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen"
														onchange="setAdditionalChargeAjax1(this.value)"/>
														</div>
													</td>
													<td>
													<input type="text" onchange="calcmanualcharge()"  name="mannualcharge"  id="mannualcharge"
														 class="form-control">
													</td>
													<td>
													<div id="chargeajaxqty">
														<input onchange="calcamount()" style="width: 66px;text-align: center;" type="text" id="quantity" name="quantity" 
														maxlength="2" value="1" class="form-control">
													</div>
													</td>
													<td>
													<div  id="chargeajax">
													<s:if test="approvedrefund==0">
														<input  type="text" onchange="calcmanualcharge()" onkeypress="return isNumberKey(event,this);" style="text-align:center;" id="charge" name="charge" class="form-control" />
													</s:if>
													<s:else>
														<input readonly="readonly"  type="text" onchange="calcmanualcharge()" onkeypress="return isNumberKey(event,this);" style="text-align:center;" id="charge" name="charge" class="form-control" />
													</s:else>
													</div>
													
													<input type="hidden" name="hdncharge" id="hdncharge">
													
													</td>
													<td id="amount" class="text-right"></td>
												</tr>
											
												
											
										</tbody>
									</table>
									
									<div class="row addchabtn hidden" style="margin-top: 10px;" >
									<div class="col-lg-12 col-md-12">
										
										<input id="addchargebtn" type="button" value="Add" onclick="setChargeAmount()" disabled="disabled" class="btn btn-primary" style="float:right;">
									</div>
									</div>
			
	
	






<div class="row hidden">

<div class="col-lg-12 hidden">
<label>View All Charges</label>
<table class="table table-bordered widthdebicha" cellspacing="0">
										<thead>
											<tr>
											<th class="padby">Paid By</th>
												<th style="width: 30%;">Charge Type</th>
												<!--<th>Charge Name</th>
												--><th style="width: 40%;">Charge Name</th>
												<th class="text-center">Qty</th>
												<th class="text-right" style="width: 9%;">Unit Price</th>
												<th class="amount text-right">Amount</th>
												<th style="width: 2%;"></th>

											</tr>
										</thead>


										<tbody id="cashDesk">
											
										</tbody>
									</table>
									
</div>
</div>



<div class="" >
<div class="">
<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="display: none;">	

<s:submit id="createchargebtn"  value="Create Charge" cssClass="btn btn-primary"  theme="simple" ></s:submit>

</s:form>
</div>


<div class="col-lg-12 col-xs-12 col-md-12 creditcharpadingtop">
				<s:if test="approvedrefund==0">
					<s:form action="casdeskAdditional" id = "casdeskAdditional">
						<s:hidden name="advref" id="advref" value="0" />
						<s:hidden name="clientId" id="hdncdclientid" />
						<s:hidden name="client" id="hdncdclient" />
						<s:hidden name="apmtType" id="hdncdapmttype"/>
						<s:hidden name = "location" id="hdncdloc"></s:hidden>
						<s:hidden name= "payby" id="hdncdpayby" ></s:hidden>
						<s:hidden name= "date" id="hdncddate" ></s:hidden>
						<s:hidden name="Refundrequestid"></s:hidden>
						<s:hidden name="hdndocid" id="hdndocid"></s:hidden>
						<s:hidden name="sessioncount" id="sessioncount"></s:hidden>
						<s:hidden name = "creditDebitCharge" id="casdeskAdditional_creditDebitCharge" value="1"></s:hidden>
						<s:hidden name="invoicetype" value="5"></s:hidden>
						<div id="advancebtndiv">
							<a class="btn btn-primary" onclick="submitAdvance()" style="float: right;margin-left: 15px;">Advance</a>
						</div>
					</s:form>
				</s:if>
				<s:if test="approvedrefund==0">
					<s:form action="refundrequestAdditional" id = "refundrequestfrm">
						<s:hidden name="advref" id="advref" value="1" />
						<s:hidden name="clientId" id="hdnciclientid"/>
						<s:hidden name="client" id="hdncliclient" />
						<s:hidden name="apmtType" id="hdnciapmttype"/>
						<s:hidden name = "location" id="hdnciloc"></s:hidden>
						<s:hidden name= "payby" id="hdncipayby" ></s:hidden>
						<s:hidden name= "date" id="hdncidate" ></s:hidden>
						<s:hidden name="manualinvoiceid" id="manualinvoiceid"/>
						<s:hidden name="refundnote" id="refundnotes"/>
						<s:hidden name="Refundrequestid"></s:hidden>
						<s:hidden name="hdncashdocid" id="hdncashdocid"></s:hidden>
						<s:hidden name = "creditDebitCharge" id="casdeskAdditional_creditDebitCharge" value="1"></s:hidden>
						<div id="refundbtndiv">
							<a class="btn btn-primary" onclick="checkadvanceorrefund()" style="float: right;margin-left: 15px;">Request Refund</a>
						</div>
					</s:form>
				</s:if>
				<s:else>
					<s:form action="casdeskAdditional" id = "refundfrm">
						<s:hidden name="advref" id="advref" value="1" />
						<s:hidden name="clientId" id="hdnciclientid"/>
						<s:hidden name="client" id="hdncliclient" />
						<s:hidden name="apmtType" id="hdnciapmttype"/>
						<s:hidden name = "location" id="hdnciloc"></s:hidden>
						<s:hidden name= "payby" id="hdncipayby" ></s:hidden>
						<s:hidden name= "date" id="hdncidate" ></s:hidden>
						<s:hidden name="manualinvoiceid" id="manualinvoiceid"/>
						<s:hidden name="refundnote" id="refundnotes"/>
						<s:hidden name="Refundrequestid"></s:hidden>
						<s:hidden name = "creditDebitCharge" id="casdeskAdditional_creditDebitCharge" value="1"></s:hidden>
						<%if(loginfo.isRefund()){ %>
							<input type="button"  class="btn btn-primary" value="Refund " onclick="checkAdvance()" style="float: right;margin-left: 15px;">
						<%} %>
					</s:form>
				</s:else>
</div>


<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="display: none;">	
				<s:form action="createInvoiceAdditional" id = "createInvoiceAdditional">
					<s:hidden name="clientId" id="hdnciclientid"/>
					<s:hidden name="client" id="hdncliclient" />
					<s:hidden name="apmtType" id="hdnciapmttype"/>
					<s:hidden name = "location" id="hdnciloc"></s:hidden>
					<s:hidden name= "payby" id="hdncipayby" ></s:hidden>
					
					<input type="submit" id="createinvoicebtn" class="btn btn-primary" value=" Create Invoice ">
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
        <h4 class="modal-title" id="myModalLabel"><%=loginfo.getPatientname_field() %> Search</h4>
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
											
<!-- Loader -->
	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>
	<!--End loader  -->
											
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
