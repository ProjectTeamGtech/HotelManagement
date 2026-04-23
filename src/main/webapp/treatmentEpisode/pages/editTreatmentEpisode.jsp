<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript"
	src="treatmentEpisode/js/addTreatmentEpisode.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li>Clients</li>
			<li>Treatment Episode</li>
			<li class="active">Edit Treatment Episode Details</li>
		</ol>
	</div>
</div>

<s:form action="updateSaveTreatmentEpisode" theme="simple">
	<s:hidden name="client" id="client" theme="simple" />
	<s:hidden id="id" name="id"></s:hidden>
	<s:hidden name="patientId" id="patientId"></s:hidden>
<s:hidden name = "clientId" id = "clientId"></s:hidden>				
		<div class="row">		
			<div class="col-lg-2 col-md-2">
				<label>Practitioner Responsible</label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:select id="diaryUser" name="diaryUser" list="userList"
					listKey="id" listValue="diaryUser" headerKey="0" theme="simple"
					headerValue="Select User" cssClass="form-control" />
			</div>
			
			<div class="col-lg-2 col-md-2">
				<label>Condition</label><label><span class="text-danger">*</span></label>			
			</div>
				<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">	
				<s:select id = "treatmentType2" name = "treatmentType" list="condtitionList" headerValue="Select Condition" headerKey="0" listKey="id" listValue="treatmentType" cssClass="form-control showToolTip chosen"
									data-toggle="tooltip" theme="simple"></s:select>
				<label id = "treatmentTypeError" class="text-danger"></label>							
		</div>
		</div>
		
		<br />
		
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Treatment Start Date</label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:textfield id="treatmentStartDate" cssClass="form-control"
					name="treatmentStartDate" />
			</div>
		
			<div class="col-lg-2 col-md-2">
				<label>Treatment Episode Name </label><label><span class="text-danger">*</span></label>		
			</div>
				<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:textfield name="treatmentEpisodeName" id="treatmentEpisodeName"
					size="40" cssClass="form-control" />
				<label name="treatmentNameError" id="treatmentNameError"
					class="text-danger"></label>
			</div>
		</div>
		<hr />

		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Referral Date </label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:textfield name="referalDate" id="referalDate"
					cssClass="form-control" />
			</div>
		
			<div class="col-lg-2 col-md-2">
				<label>Referred Name </label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:textfield name="referralName" id="referralName" cssClass="form-control"></s:textfield>
			</div>
		</div>
		<br />
		
		  <div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Referral By</label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:select cssClass="form-control" list="sourceOfReferralList"
					name="referralSource" id="referralSource" theme="simple"
					headerKey="id" headerValue="Select Referral Source"
					listValue="referralSource" listKey="referralSource"></s:select>
			</div>
		</div>
		<br />
	<hr />
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label class="text-info">Who will Pay for this Treatment</label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">			
				<s:radio name="payby" id="payby"  list="{'Client','Third Party'}"
					 onclick="setPayBy(this.value)"></s:radio>
					 
				<s:textfield cssClass="select ui-widget-content ui-corner-all"
					name="thirdPartyName" id="thirdPartyName" cssStyle="display:none;" />
				<label name="thirdPartyNameError" id="thirdPartyNameError"
					class="text-danger"></label>
			</div>
		</div>
		<br />
		
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Invoice To</label><label class="text-danger reqd-info">*</label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:textfield name="invoicee" id="invoicee" cssClass="form-control" />
				<label id="invoiceeError" class="text-danger"></label>
			</div>
			<div class="col-lg-2 col-md-2">
				<label>Authorization Code</label><label class="text-danger reqd-info">*</label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">
			<s:if test="payby == 'Third Party'">
			<s:textfield name="authorisationCode" id="authorisationCode"
					cssClass="form-control" />
			</s:if>
			<s:else>
			<s:textfield name="authorisationCode" id="authorisationCode"
					cssClass="form-control" disabled="true"/>
			</s:else>
				
				<label id="authorisationCodeError"
					class="text-danger"></label>
			</div>
		</div>
		<br />
		
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Spend Limit </label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">	
				<s:textfield name="spendLimit" id="spendLimit"
					cssClass="form-control" />
				<!-- <label name="spendLimitError" id="spendLimitError"
					class="text-danger"></label> -->
			</div>		
			<div class="col-lg-2 col-md-2">
				<label>No. of Consultation Limit </label><label class="text-danger reqd-info">*</label>
			</div>
			<div class="col-lg-1 col-md-1">
				<label>:</label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:textfield name="consultationLimit" id="consultationLimit"
					cssClass="form-control" onchange="checkConsultatinLimit(this.value)" />
				<label name="consultationLimitError" id="consultationLimitError"
					class="text-danger"></label>
			</div>
		</div>
		<br />
		
		<hr />

		<div class="row">
			<div class="col-lg-1 col-md-2 col-sm-6">
				<input type="submit" value="Update"
					onclick="return updateTreatment();" class="btn btn-primary">
			</div>
			<div class="col-lg-1 col-md-2 col-sm-6">
				<input type="button" value=" Cancel " onclick="history.back(1);"
					class="btn btn-primary">
			</div>
		</div>
		<br/>

</s:form>
