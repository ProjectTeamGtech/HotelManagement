<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="treatmentEpisode/js/addTreatmentEpisode.js"></script>
	<input type = "hidden" id = "tempclientname">
	<input type = "hidden" id = "tempclientid">
		 <div class="row setopd hidden">
			<div class="col-lg-1 col-md-1">
				 <input type="radio" name="ipdopd" id="opd"  value="0"> OPD
			</div>
			<div class="col-lg-1 col-md-1">
				 <input type="radio" name="ipdopd" id="ipd" checked="checked" value="1"> IPD
			</div>
		</div> 
	<div class="row">
	<div class="col-lg-12 col-md-12">
	<div class="col-lg-6 col-md-6">
	<div class="row hidden">
		<div class="col-lg-5 col-md-5">
		<label style="float:right;">Daignosis <span class="text-danger">*</span></label>		
		</div>
		<div class="col-lg-7 col-md-7">						
			<s:select id="treatmentType1" name = "treatmentType" list="condtitionList" headerValue="Select Condition" headerKey="0" listKey="id" listValue="treatmentType" cssClass="form-control showToolTip chosen"
									data-toggle="tooltip" theme="simple" onchange="updateClientCondition(this.value)"></s:select>
			<label id = "treatmentTypeError" class="text-danger"></label>
		</div>
		</div>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Treatment Episode<span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-7 col-md-7"> <!-- onchange="checkTreatAlreadyExit()" -->
				<s:textfield name="treatmentEpisodeName" id="treatmentEpisodeName" theme="simple"  title="Enter Treatment Episode Name" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Treatment Episode Name"/>
				<label name = "treatmentNameError" id = "treatmentNameError" class="text-danger"></label>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Referred From Date</label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield name="referalDate" id="referalDate"  theme="simple"  title="Enter Referal Date" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Referral Date"/>
					<label name = "referalDateError" id = "referalDateError" class="text-danger"></label>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Referred End Date</label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield name="referalendDate" id="referalendDate"  theme="simple"  title="Enter Referal Date" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Referral Date"/>
				<label name = "referalendDateError" id = "referalendDateError" class="text-danger"></label>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Referral Name</label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield name="referralName" id="referralName" theme="simple"  title="Enter Referral Name" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Referral Name"></s:textfield>

			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Referral By</label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:if test="%{#sourceOfReferralList != 'null'}" >
 						<s:select list="sourceOfReferralList" name = "referralSource" id = "referralSource" theme="simple" headerKey="0" headerValue="Select Referral Source" listValue="referralSource" listKey="referralSource" title="Select Referral Source" cssClass="form-control showToolTip"
									data-toggle="tooltip"></s:select>	
 				</s:if>
			</div>
		</div>	
		<br>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Employee Name <span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield name="empname" id="empname"  theme="simple" title="Enter Employee Name" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Employee Name"/>
									
			</div>
		</div>	
	</div>
	<div class="col-lg-6 col-md-6">
	<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Who will Pay?</label>
			</div>
			<div class="col-lg-7 col-md-7">
					<input type="radio" name="payby" id="payby1"  value="Client" onclick="setPayBy(this.value)">Patient(Self)
				<input type="radio" name="payby" id="payby"  value="Third Party" onclick="setPayBy(this.value)">Third Party

			</div>
		</div>	
		<br>	
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Invoice To <span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-5 col-md-5" id= "invoiceeTd">
				<s:textfield name="invoicee" id="invoicee" theme="simple" title="Enter Invoice" cssClass="form-control showToolTip"
									data-toggle="tooltip"/>
				
				<label id = "invoiceeError" class="text-danger"></label>
									
			</div>
			<div class="col-lg-2 col-md-2 sletp">
				<input type="button" id = "selectTpbtn" value="Select TP" onclick="addThirdPartyName()"  class="btn btn-primary">
			</div>								
									
		</div>
		<div class="row" id="policynodiv">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Policy No <span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield name="trtmentPolicyNo" id="trtmentPolicyNo"  theme="simple" disabled="true" title="Enter Authorization Code" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Policy No"/>
				
						<br>			
			</div>
			
		</div>
		<br>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Authorization Code <span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield name="authorisationCode" id="authorisationCode"  theme="simple" disabled="true" title="Enter Authorization Code" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Authorization Code"/>
				<label name = "authorisationCodeError" id = "authorisationCodeError" class="text-danger"></label>
									
			</div>
		</div>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Request Amount</label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield name="spendLimit" id="spendLimit"  theme="simple" title="Enter Spend Limit" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Amount"/>
<!-- 				<label name = "spendLimitError" id = "spendLimitError" style="color: red"></label>
 -->									
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Approved Amount</label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield name="approvedLimit" id="approvedLimit"  theme="simple" title="Enter Approve Amount" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Approved Amount"/>
<!-- 				<label name = "spendLimitError" id = "spendLimitError" style="color: red"></label>
 -->									
			</div>
		</div>
		
		
			
		<br>	
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Allow Consultation <span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield onchange="checkconsultationLinit(this.value)" name="consultationLimit" id="consultationLimit"  theme="simple" title="Enter No. of Consultation Limit" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter No. of Consultation"/>
				<label name = "consultationLimitError" id = "consultationLimitError" class="text-danger"></label>
									
			</div>
		</div>	
		
		<%-- <div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Employee Name <span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-7 col-md-7">
				<s:textfield name="empname" id="empname"  theme="simple" title="Enter Employee Name" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Employee Name"/>
									
			</div>
		</div> --%>	
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label style="float:right;">Urgent <span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-2 col-md-2">
				<input type="checkbox" id="urgent" name="urgent">
									
			</div>
			<div class="col-lg-5 col-md-5">
				
			</div>
		</div>
		
		
						
	</div>
	</div>
	</div>
	
		<%-- </br>						
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label>Referral Contact:</label>
			</div>
			<div class="col-lg-7 col-md-7">
				<select name="referralContact" id="referralContact" title=" Select Referral Contact" class="form-control showToolTip"
									data-toggle="tooltip"> 
					<option value="0">Select Referral Contact:</option>
					</select>
			</div>
		</div>	
		</br>						
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<label>Referral Letter: </label>
			</div>
			<div class="col-lg-7 col-md-7">
					<s:select list="{'Consultant Referral Letter','GP Referral Letter','Self Referral Letter'}"  name = "referralLetter" id = "referralLetter" theme="simple" headerValue="Select Referral Letter" title="Select Referral Letter" cssClass="form-control showToolTip"
									data-toggle="tooltip"></s:select>	

			</div>
		</div>	 --%>				 			
								
	