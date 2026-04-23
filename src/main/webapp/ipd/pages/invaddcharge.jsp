<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>
<%LoginInfo loginInfoinvadd= LoginHelper.getLoginInfo(request); %>
<script type="text/javascript">
    
    $(document).ready(function() {

		$("#date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		
		
	});
    
</script>



<style>
hr {
    margin-top: 5px !important;
    margin-bottom: 5px !important;
    border: 0;
    border-top: 1px solid #eee;
}


</style>





<div class="row">
	<div class="col-lg-12" id="durationdiv">
		 <span id="compPractName"></span> <span> 
			 <s:if test="%{#locationList != 'null'}" >
					<s:select cssClass="form-control" cssStyle="width: 110px; 
					padding: 0px;margin-top: -6px;" id="complocationid3" name="locationid" list="locationList" listKey="locationid" listValue="location" headerKey="0" theme="simple" headerValue="All" />
			</s:if>
		</span>
		<span><s:textfield cssClass="form-control" placeholder="Date" name="date" id="date" cssStyle="width: 15%;" /></span>
		<span id="patientpkgspan">
			<select name="ipdpackage" id="ipdpackage" class="form-control" style="width: 130px;">
				<option value="0">Select Package</option>
			</select>
		</span>
	</div>
</div>


<div class="row">
	<div class="col-lg-12">
		<s:hidden id="markAppointment" name="markAppointment" value="1" />
		<input type="hidden" id="clientId" name="clientId"> <input
			type="hidden" name="practitionerName" id="practitionerName" />
		<s:hidden name="practitionerId" id="practitionerId" />
		<input type="hidden" id="totalcharge" name="totalcharge" value="0" />
		<s:hidden name="payBuy" id="payBuy" />
	</div>
</div>
<div style="display: none;">
<div class="row">
	<div class="col-lg-12" id="tmntesode">
			
	</div>
</div>

<!-- <div class="row">
	<div class="col-lg-12" id="chargediv">
		<label>The charge has been created To unnati first appointment
			10$</label>
	</div>
</div> -->

<hr>

<div class="row" id="apmtchargelabel">
	<div class="col-lg-12">
		<label> Appointment Charges: </label>
		<%-- <span id="self">(Self)</span> <span
			id="tp">(Third Party : <span id="tpname"></span>)
		</span> --%>
		
	</div>
</div>


<div class="row" id="chargefordiv">
	<div class="col-lg-4 col-md-4">
	Charge For :
	</div>
	<div class="col-lg-8 col-md-8">
	<input  type="text" name="selectedApmtType"
					id="selectedApmtType" readonly="readonly"
					class="form-control showToolTip" title="Appointment Type"
					placeholder="Appointment Type">
	</div>
		 
</div>
<br>


<div class="row" id="costdiv">
	<div class="col-lg-4 col-md-4">
	Cost :
	</div>
	<div class="col-lg-8 col-md-8">
	
</div>
		 
</div>
<br>
<div class="row" id="payeediv">
	<div class="col-lg-4 col-md-4">
	Payee :
	</div>
	<div class="col-lg-8 col-md-8" id = "payee">
		
	</div>
		 
</div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-4">
	Charge To :
	</div>
	<div class="col-lg-8 col-md-8" id = "chargeTo1">
		
	</div>
		 
</div>
<br>
<div class="row" id="tpnotesdiv">
	<div class="col-lg-4 col-md-4">
	 Notes :
	</div>
	<div class="col-lg-8 col-md-8" id = "payee">
		<textarea name="tpnotes" id="tpnotes" readonly="readonly" rows="3" cols="40"></textarea>
	</div>
		 
</div>

<hr>



<!-- <div class="row" id="authcodetr">
	<div class="col-lg-4 col-md-5">Auth.Code</div>
	<div class="col-lg-8 col-md-7">
		<input type="text" class="form-control showToolTip" name="authcode"
			id="authcode" size="10" readonly="readonly" title="Auth Code"
			placeholder="Auth Code">
	</div>
</div>
</br> -->
<div class="row">
	<div class="col-lg-12">
		<label> Additional Charges: </label>
		
		
	</div>
</div>

<div id="invchargeinfodata" style="display: none;"></div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-4">
	Payee :
	</div>
	<div class="col-lg-8 col-md-8">
		<input type="radio" id="payBuy" name="payBuy" value="0"
			checked="checked" 
			style="display: inline; float: left;padding: 3px;"> <label for="payBuy" style="display: inline; float: left;padding: 3px;">Self</label> <input type="radio"
			id="payBuy1" name="payBuy" value="1"
			
			style="display: inline; float: left;padding: 3px;"><label for="payBuy"
			style="display: inline; float: left;padding: 3px;">Third Party</label>
	</div>
		 
</div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-4">
	Charge Type :
	</div>
	<!--<div class="col-lg-8 col-md-8" id="">
			<s:select name="masterchargetype" id="masterchargetype" list="masterChageTypeList" listKey="name" listValue="name"
			headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen"
			onchange="filterIpdCharges(this.value)">
				
			</s:select>
	</div>
		 
--></div>
<br>



<div class="row">
	<div class="col-lg-4 col-md-4">
	Charges :
	</div>
	<!--<div class="col-lg-8 col-md-8" id="additionalChargeAjax">
			<s:select name="chargeTYpe" id="chargeTYpe" list="additionalChargeList" listKey="id" listValue="name"
			headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen"
			onchange="setAdditionalChargeAjax1(this.value)">
				
			</s:select>
	</div>
		 
--></div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-4">
	Cost :
	</div>
	<div class="col-lg-8 col-md-8" id="chargeajax">
	
</div>
</div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-4">
	Quantity :
	</div>
	<div class="col-lg-8 col-md-8" id = "chargeTo2">
		
	</div>
		 
</div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-5">
		<!--<input id="addchargebtn" type="button" value="Add Charge"
					onclick="setChargeAmount()" disabled="disabled"
					class="btn btn-primary">
	--></div>
	
</div>

<hr />


</br>

<div class="row">
	<div class="col-lg-4 col-md-4">Total Charges:</div>
	<div class="col-lg-8 col-md-8" id="chargeTotalajax">
		<input type="text" class="form-control showToolTip" id="chargeTotal3"
			name="chargeTotal" value="0.00" disabled="disabled"
			 />
	</div>
</div>
</br>
<div class="row text-info">
	<div class="col-lg-4 col-md-4">Note :</div>
	<div class="col-lg-8 col-md-8">Click on Add Charge button to
		show charges and add new charge.</div>
</div>
<br />
<div class="row text-info">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id=""
				class="table table-hover table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th>Id</th>
						<th>Appointment Type</th>
						<th>Charge</th>
						<th>Delete</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
</br>

</div>


<div class="row">

<div class="col-lg-12">
<label>Add Charges &nbsp;&nbsp; <small>( Account Note:-)</small></label>
<table class="table table-bordered" cellspacing="0" width="100%">
										<thead>
											<tr>
																			
												<th class="padby">Paid By</th>
												<th class="chartype">Charge Type</th>
												<th style="width: 31%;">Charge Name</th>
												<th class="text-center">Qty</th>
												<th class="text-right" style="width: 9%;">Unit Price</th>
												<th class="amount text-right">Amount</th>

											</tr>
										</thead>
										<tbody>
												<tr>
													<td>
														<input type="radio" id="payBuy" name="payBuy" value="0" checked="checked" style="display: inline; float: left;padding: 3px;"> <label for="payBuy" style="display: inline; padding: 3px;">Self</label><hr>
														<input type="radio" id="payBuy1" name="payBuy" value="1" style="display: inline; float: left;padding: 3px;"><label for="payBuy" style="display: inline; float: left;padding: 3px;">TP</label>
													</td>
													<td>
														<div >
														<s:select theme="simple" name="masterchargetype" id="masterchargetype" list="masterChageTypeList" listKey="name" listValue="name"
															headerKey="0" headerValue="Select Charge Type" cssClass="form-control showToolTip chosen-select"
															onchange="filterIpdCharges(this.value)"/>
														</div>
														
														<s:hidden id="isindisharecharge" value="0"></s:hidden>
														<div id="visitingconsltantdiv">
															<s:hidden id="consultantdr" value="0"></s:hidden>
														</div>
													</td>
													<td>
														<div id="additionalChargeAjax">
														<!--<s:select theme="simple" name="chargeTYpe" id="chargeTYpe" list="additionalChargeList" listKey="id" listValue="name"
														headerKey="0" headerValue="Select Charge Name" cssClass="form-control showToolTip chosen-select"
														onchange="setAdditionalChargeAjax1(this.value)"/>
														-->
														<select name="chargeTYpe" id="chargeTYpe" class="form-control">
															<option value="0">Select Charge Name</option>
														</select>
														
														</div>
														<%if(!loginInfoinvadd.isAdd_manual_charge()) {%>
														<input type="text" onchange="calcmanualcharge()"  name="mannualcharge"  id="mannualcharge" class="form-control" style="margin-top:3px;" readonly="readonly">
														<%}else{ %>
														<input type="text" onchange="calcmanualcharge()"  name="mannualcharge"  id="mannualcharge" class="form-control" style="margin-top:3px;">
														<%} %>
														
													</td>
													
													<td><input onchange="calcamount()" style="width: 66px;text-align: center;" type="text" id="quantity" name="quantity" 
														maxlength="3" value="1" class="form-control"></td>
													<td>
													<div  id="chargeajax">
														<input  type="text" onchange="calcmanualcharge()" onkeypress="return isNumberKey(event,this);" style="text-align:center;" id="charge" name="charge" class="form-control" />
													</div>
													<input type="hidden" name='addChargeType' id='addChargeType'>
													<input type="hidden" name="hdncharge" id="hdncharge">
													</td>
													<td id="amount" class="text-right"></td>
													<input type="hidden" name="packageid3" id="packageid">
												</tr>
										</tbody>
									</table>
									<div class="row" style="margin-top: 10px;">
									<div class="col-lg-12 col-md-12">
									<input id="addchargebtn" type="button" value="Add Charge" onclick="getInventoryProductStock()"  class="btn btn-primary" style="float:right;">
									</div>
									</div>
</div>
</div>

<div class="row">
<div class="col-lg-12">
<label>View All Charges</label>
<table class="table table-bordered" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th style="width: 11%">Date</th>
												<th class="padby" style="width: 11%">Paid By</th>
												<th class="chartype">Charge Type</th>
												<th>Charge Name</th>
												<th class="text-center">Qty</th>
												<th class="text-right" hidden="true">Unit Price</th>
												<th class="amount text-right">Amount</th>
												<th style="width: 2%;"></th>

											</tr>
										</thead>
										<tbody id="cashDesk31">
											
										</tbody>
									</table>
									
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

