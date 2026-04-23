<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="diarymanagement/js/completeApmt.js"></script>

<div class="row">
	<div class="col-lg-12" id="durationdiv">
		
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

<div class="row">
	<div class="col-lg-12">
		<label> Appointment Charges: </label>
		<%-- <span id="self">(Self)</span> <span
			id="tp">(Third Party : <span id="tpname"></span>)
		</span> --%>
		
	</div>
</div>


<div class="row">
	<div class="col-lg-4 col-md-4">
	Charge For :
	</div>
	<div class="col-lg-8 col-md-8">
	<input  type="text" name="selectedApmtType"
					id="selectedApmtType" readonly="readonly"
					class="form-control showToolTip" t itle="Appointment Type"
					placeholder="Appointment Type">
	</div>
		 
</div>
<br>


<div class="row">
	<div class="col-lg-4 col-md-4">
	Cost :
	</div>
	<div class="col-lg-8 col-md-8">
	<input type="text" class="form-control showToolTip"
					id="appointmentcharge" name="appointmentcharge"
					title="Appointment Charge" placeholder="Appointment Charge"
					 readonly="readonly" />
</div>
		 
</div>
<br>
<div class="row">
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
<br>
<div class="row">
	<div class="col-lg-4 col-md-4">
	Charge For :
	</div>
	<div class="col-lg-8 col-md-8" id="additionalChargeAjax">
	<select class="form-control showToolTip" name="addChargeType"
					id="addChargeType" title="Select Additional Charge Type"><option
						value="0">Select Additional Charge Type</option></select>
	</div>
		 
</div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-4">
	Cost :
	</div>
	<div class="col-lg-8 col-md-8" id="chargeajax">
	<input type="text" id="charge" name="charge" 
					class="form-control" disabled="disabled"
					 />
</div>
</div>
<br>
<div class="row">
	<div class="col-lg-4 col-md-4">
	Payee :
	</div>
	<div class="col-lg-8 col-md-8">
		<input type="radio" id="payBuy" name="payBuy" value="0"
			checked="checked" onclick="setAdditionalChargeTypeAjax1('Client')"
			style="display: inline; float: left;padding: 3px;"> <label for="payBuy" style="display: inline; float: left;padding: 3px;">Self</label> <input type="radio"
			id="payBuy1" name="payBuy" value="1"
			onclick="setAdditionalChargeTypeAjax1('Third Party')"
			style="display: inline; float: left;padding: 3px;"><label for="payBuy"
			style="display: inline; float: left;padding: 3px;">Third Party</label>
	</div>
		 
</div>
<br>		 
<div class="row">
	<div class="col-lg-4 col-md-4">
	Charge To :
	</div>
	<div class="col-lg-8 col-md-8" id = "chargeTo2">
		
	</div>
		 
</div>
<br>

<div class="row">
	<div class="col-lg-4 col-md-5">
		<input id="addchargebtn" type="button" value="Add Charge"
					onclick="setChargeAmount1()" disabled="disabled"
					class="btn btn-primary">
	</div>
	
</div>

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
<br />
<div class="row text-info">
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



