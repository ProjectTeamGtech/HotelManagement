<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript"
	src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="thirdParties/js/gp.js"></script>


<div class="col-lg-12 col-md-12">


<div class="row">

	
	<div class="col-lg-6 col-md-6">
								 <label>Surgery Name</label><label><span class="text-danger">*</span></label>
								  <s:select id="gptypeNamepopup"
									name="surgeryName"
									list="surgeryList" listKey="id" listValue="gptypeName" headerValue="Select Surgery Name"
									headerKey="0"  cssClass="form-control showToolTip chosen"
									data-toggle="tooltip" onchange="setGPDataList(this.value)" />
								<%-- <s:select cssClass="form-control showToolTip chosen"
									data-toggle="tooltip"  id="gptypeNamepopup"
									name="surgeryName" list="surgeryList" listKey="id"
									listValue="thirdPartyCompanyName" headerKey="0" theme="simple"
									headerValue="Select Surgery Name"  onchange="setGPDataList(this.value)"/> --%>
								
							</div>
							<%-- <div class="col-lg-6 col-md-6">
								<label>Third Party Name</label><label><span class="text-danger">*</span></label>

								<s:select cssClass="form-control showToolTip"
									data-toggle="tooltip"  id="gptypeNamepopup"
									name="typeName" list="thirdPartyTypeNameList" listKey="id"
									listValue="thirdPartyCompanyName" headerKey="0" theme="simple"
									headerValue="Select TP Name"  />
									
								
							</div> --%>
</div>


<br>

<div class="row">
	<div class="col-lg-6 col-md-6">
			<label>Contact/GP Name</label><label><span class="text-danger">*</span></label>
			<s:textfield id="gpnameid" name="gpname"
			title="Enter GP Name"
			cssClass="form-control showToolTip" data-toggle="tooltip"
			placeholder="Enter GP Name" />
	</div>
	
		<div class="col-lg-6 col-md-6">
			<label>Work Phone Number</label>
			<s:textfield id="workphno" name="workphno"
			title="Enter work ph no"
			cssClass="form-control showToolTip" data-toggle="tooltip"
			placeholder="Enter work ph no" />
	</div>
</div>


<br>

<div class="row">
	<div class="col-lg-6 col-md-6">
			<label>Email</label>
			<s:textfield id="gpemailid" name="gpemailid"
			title="Enter Email"
			cssClass="form-control showToolTip" data-toggle="tooltip"
			placeholder="Enter Email" />
	</div>
	
		<div class="col-lg-6 col-md-6">
			<label>Fax</label>
			<s:textfield id="gpfax" name="gpfax"
			title="Enter Fax"
			cssClass="form-control showToolTip" data-toggle="tooltip"
			placeholder="Enter Fax" />
	</div>
</div>


<br>

<div class="row">
	<div class="col-lg-6 col-md-6">
			<label>Note</label>
			<s:textarea id="gpNote" name="gpNote"
			title="Enter Note"
			cssClass="form-control showToolTip" data-toggle="tooltip"
			placeholder="Enter Note" />
	</div>
	
		
</div>

</div>


