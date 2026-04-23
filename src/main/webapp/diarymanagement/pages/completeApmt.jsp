<%@taglib uri="/struts-tags" prefix="s"%>

<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>


<div class="row">
	<div class="col-lg-12" id="durationdiv">
		 <span id="compPractName"> </span> <span> 
			 <s:if test="%{#locationList != 'null'}" >
					<s:select  cssClass="form-control" cssStyle="width: 110px; 
					padding: 0px;margin-top: -6px;" id="complocationid" name="locationid" list="locationList" listKey="locationid" listValue="location" headerKey="0" theme="simple" headerValue="All" />
			</s:if>
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

<div class="row" style="display: none;">
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
<div id="" style="display: none;">
<div class="row" id="apmtchargelabel">
	<div class="col-lg-12">
		<label> Appointment Charges: </label>
		<%-- <span id="self">(Self)</span> <span
			id="tp">(Third Party : <span id="tpname"></span>)
		</span> --%>
		
	</div>
</div>

<div class="row" id="chargefordiv">
	<div class="col-lg-2 col-md-2">
	Charge For :
	</div>
	<div class="col-lg-10 col-md-10">
	<input  type="text" name="selectedApmtType"
					id="selectedApmtType" readonly="readonly"
					class="form-control showToolTip" title="Appointment Type"
					placeholder="Appointment Type">
	</div>
		 
</div>
<br>


<div class="row" id="costdiv">
	<div class="col-lg-2 col-md-2">
	Cost :
	</div>
	<div class="col-lg-10 col-md-10">
	<input type="text" class="form-control showToolTip"
					id="appointmentcharge" name="appointmentcharge"
					title="Appointment Charge" placeholder="Appointment Charge"
					 readonly="readonly" />
</div>
		 
</div>
<br>
<div class="row" id="payeediv">
	<div class="col-lg-2 col-md-2">
	Payee :
	</div>
	<div class="col-lg-10 col-md-10" id = "payee">
		
	</div>
		 
</div>
<br>

<div class="row">
	<div class="col-lg-2 col-md-2">
	Charge To :
	</div>
	<div class="col-lg-10 col-md-10" id = "chargeTo1">
		
	</div>
		 
</div>
<br>
<div class="row" id="tpnotesdiv">
	<div class="col-lg-2 col-md-2">
	 Notes :
	</div>
	<div class="col-lg-10 col-md-10" id = "payee">
		<textarea name="tpnotes" id="tpnotes" readonly="readonly" rows="3" cols="40"></textarea>
	</div>
		 
</div>
</div>



<div class="row">

<div class="col-lg-12">
<label>Add Charges &nbsp;&nbsp; <small>( Account Note:-)</small></label>
<table class="table table-bordered" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th class="padby">Paid By</th>
												<th class="chartype">Charge Type</th>
												<th>Charge Name</th>
												<th>Qty</th>
												<th>Unit Price</th>
												<th class="amount">Amount</th>

											</tr>
										</thead>


										<tbody>
												<tr>

													<td>
														<input type="radio" id="payBuy" name="payBuy" value="0" checked="checked" style="display: inline; float: left;padding: 3px;"> <label for="payBuy" style="display: inline; float: left;padding: 3px;">Self</label> 
														<input type="radio" id="payBuy1" name="payBuy" value="1" style="display: inline; float: left;padding: 3px;"><label for="payBuy" style="display: inline; float: left;padding: 3px;">Third Party</label>
													</td>
													<td>
														<div >
														<s:select theme="simple" name="masterchargetype" id="masterchargetype" list="masterChageTypeList" listKey="name" listValue="name"
															headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen"
															onchange="filterCharges(this.value)"/>
														</div>
														<s:hidden id="isindisharecharge" value="0"></s:hidden>
														<div id="visitingconsltantdiv">
															<s:hidden id="consultantdr" value="0"></s:hidden>
														</div>
													</td>
													<td>
														<div id="additionalChargeAjax">
														<select class="form-control showToolTip" name="addChargeType" id="addChargeType" title="Charge Name"><option value="0">Charge Name</option></select>
														</div>
														<input type="text" onchange="calcmanualcharge()"  name="mannualcharge"  id="mannualcharge" class="form-control" style="margin-top:3px;">
													</td>
														<td><input onchange="calcamount()" style="width: 66px;text-align: center;" type="text" id="quantity" name="quantity" 
														maxlength="3" value="1" class="form-control"></td>
													<td>
													<div  id="chargeajax">
														<input  type="text" onchange="calcmanualcharge()" onkeypress="return isNumberKey(event,this);" style="text-align:center;" id="charge" name="charge" class="form-control" />
													</div>
													
													<input type="hidden" name="hdncharge" id="hdncharge">
													</td>
													<td id="amount" class="text-right"></td>
												</tr>
											
												
											
										</tbody>
									</table>
									<div class="row">
									<div class="col-lg-12 col-md-12">
										<input id="addchargebtn" type="button" value="Add Charge"
													onclick="getInventoryProductStock()" disabled="disabled"
													class="btn btn-primary top5">
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
											<th class="padby">Date</th>
												<th class="padby">Paid By</th>
												<th class="chartype">Charge Type</th>
												<th>Charge Name</th>
												<th class="text-center">Qty</th>
												<th class="text-right">Unit Price</th>
												<th class="amount text-right">Amount</th>
												<th style="width: 2%;"></th>

											</tr>
										</thead>


										<tbody id="cashDesk"><!--
												<tr>
													<td>Self</td>
													<td>Bed Charges</td>
													<td>ICU Bed</td>
													<td class="text-center">2</td>
													<td class="text-right">Rs.2500</td>
													<td class="text-right">Rs.5000</td>
													<td><a href=""><i class="fa fa-times text-danger"></i></a></td>
												</tr>
												<tr>
													<td>TP</td>
													<td>Medicine</td>
													<td>Insulin</td>
													<td class="text-center">1</td>
													<td class="text-right">Rs.999</td>
													<td class="text-right">Rs.999</td>
													<td><a href=""><i class="fa fa-times text-danger"></i></a></td>
												</tr>
												<tr style="background-color: #efefef;">
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td class="text-right"><b>Total:</b></td>
													<td class="text-right"><b>Rs.5999</b></td>
													<td></td>
												</tr>
											
												
											
										--></tbody>
									</table>
									
</div>
</div>




<!-- <div class="row" id="authcodetr">
	<div class="col-lg-4 col-md-5">Auth.Code</div>
	<div class="col-lg-8 col-md-7">
		<input type="text" class="form-control showToolTip" name="authcode"
			id="authcode" size="10" readonly="readonly" title="Auth Code"
			placeholder="Auth Code">
	</div>
</div>
</br> -->







<div style="display: none;">
<div class="row">
	<div class="col-lg-4 col-md-4">
	Charge To :
	</div>
	<div class="col-lg-8 col-md-8" id = "chargeTo2">
		
	</div>
		 
</div>
<br>



<hr />


</br>

<div class="row">
	<div class="col-lg-4 col-md-4">Total Charges:</div>
	<div class="col-lg-8 col-md-8" id="chargeTotalajax">
		<input type="text" class="form-control showToolTip" id="chargeTotal"
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

</div>
<br />
<!--<div class="row text-info">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="cashDesk"
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



-->