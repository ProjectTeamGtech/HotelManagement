<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="diarymanagement/js/completeApmt.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>
<script type="text/javascript" src="diarymanagement/js/completeApmt.js"></script>
<script type="text/javascript" src="diarymanagement/js/editCompleteApmt.js"></script>

<div class="row">
	<div class="col-lg-12" id="durationdiv1">
		 <span id="editcompPractName"> </span> <span> 
			 <s:if test="%{#locationList != 'null'}" >
					<s:select  cssClass="form-control" cssStyle="width: 110px; 
					padding: 0px;margin-top: -6px;" id="editcomplocationid" name="locationid" list="locationList" listKey="locationid" listValue="location" headerKey="0" theme="simple" headerValue="All" />
			</s:if>
		</span>
	</div>
</div>


<div class="row">
	<div class="col-lg-12">
			<s:hidden id="markAppointment1" name="markAppointment" value="1" />
		<input type="hidden" id="clientId1" name="clientId"> 
		<input type="hidden" id="clientname1" name="clientname1">
		<input type="hidden" name="practitionerName" id="practitionerName1" />
		<s:hidden name="practitionerId" id="practitionerId1" />
		<input type="hidden" id="totalcharge1" name="totalcharge" value="0" />
		<input type="hidden" id = "editAppointId1" name = "editAppointId">
	</div>
</div>

<div class="row" style="display: none;">
	<div class="col-lg-12" id="tmntesode1">
			
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
					id="selectedApmtType1" readonly="readonly"
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
					id="appointmentcharge1" name="appointmentcharge"
					title="Appointment Charge" placeholder="Appointment Charge"
					 readonly="readonly" />
</div>
		 
</div>
<br>
<div class="row" id="payeediv">
	<div class="col-lg-2 col-md-2">
	Payee :
	</div>
	<div class="col-lg-10 col-md-10" id = "payee1">
		
	</div>
		 
</div>
<br>

<div class="row">
	<div class="col-lg-2 col-md-2">
	Charge To :
	</div>
	<div class="col-lg-10 col-md-10" id = "chargeTo11">
		
	</div>
		 
</div>
<br>
<div class="row" id="tpnotesdivid">
	<div class="col-lg-2 col-md-2">
	 Notes :
	</div>
	<div class="col-lg-10 col-md-10" id = "payee">
		<textarea name="tpnotes" id="tpnotesid" readonly="readonly" rows="3" cols="40"></textarea>
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
														<input type="radio" id="payBuyc" name="payBuy" value="0" checked="checked" style="display: inline; float: left;padding: 3px;"> <label for="payBuyc" style="display: inline; float: left;padding: 3px;">Self</label> 
														<input type="radio" id="payBuy1c" name="payBuy" value="1" style="display: inline; float: left;padding: 3px;"><label for="payBuy1c" style="display: inline; float: left;padding: 3px;">Third Party</label>
													</td>
													<td>
														<div >
														<s:select theme="simple" name="masterchargetype" id="masterchargetype1" list="masterChageTypeList" listKey="name" listValue="name"
															headerKey="0" headerValue="Select Additional Charge Type" cssClass="form-control showToolTip chosen"
															onchange="editfilterCharges(this.value)"/>
														</div>
													</td>
													<td>
														<div id="additionaldropdown">
														<select class="form-control showToolTip" name="addChargeType" id="addChargeType1" title="Charge Name"><option value="0">Charge Name</option></select>
														</div>
														<input type="text" onchange="calcmanualcharge1()"  name="mannualcharge"  id="mannualcharge1" class="form-control" style="margin-top:3px;">
													</td>
														<td><input onchange="calcamount1()" style="width: 66px;text-align: center;" type="text" id="quantity1" name="quantity" 
														maxlength="3" value="1" class="form-control"></td>
													<td>
													<div  id="chargeajax">
														<input  type="text" onchange="calcmanualcharge1()" onkeypress="return isNumberKey(event,this);" style="text-align:center;" id="charge1" name="charge" class="form-control" />
													</div>
													
													<input type="hidden" name="hdncharge" id="hdncharge1">
													</td>
													<td id="amount1" class="text-right"></td>
												</tr>
											
												
											
										</tbody>
									</table>
									<div class="row">
									<div class="col-lg-12 col-md-12">
										<input id="addCompletechargeBtn" type="button" value="Add Charge"
													onclick="getInventoryProductStock1()" disabled="disabled"
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
												<th class="padby">Paid By</th>
												<th class="chartype">Charge Type</th>
												<th>Charge Name</th>
												<th class="text-center">Qty</th>
												<th class="text-right">Unit Price</th>
												<th class="amount text-right">Amount</th>
												<th style="width: 2%;"></th>

											</tr>
										</thead>


										<tbody id="cashDesk1">
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
											
												
											
										</tbody>
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
	<div class="col-lg-8 col-md-8" id = "chargeTo22">
		
	</div>
		 
</div>
<br>



<hr />


</br>

<div class="row">
	<div class="col-lg-4 col-md-4">Total Charges:</div>
	<div class="col-lg-8 col-md-8" id="chargeTotalajax">
		<input type="text" class="form-control showToolTip" id="chargeTotal1"
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