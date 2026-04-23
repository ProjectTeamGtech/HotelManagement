<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript"
	src="thirdParties/js/addThirdPartyValidaton.js"></script>
<div class="row" id = "tpbreadcrum">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Third Parties</a></li>
			<li><a href="/APM/ThirdParty">All Third Party List</a></li>
			<li class="active">Add New Third Part</li>
		</ol>
	</div>
</div>

<s:form action="updateDoctorSurgeryThirdParty" theme="simple">

	<div class="col-lg-12 col-md-12">
		<div class="row" style="padding-left: 15px;padding-right: 15px;">
			<div class="col-lg-12 bg-grey">
				<label>TP/GP Name/Address</label>
			</div>
		</div>
	
		<div class="panel panel-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="row">
						<div class="col-lg-3 col-md-3">
							<label>Third Party Type</label><label><span
								class="text-danger">*</span></label>
							<s:select id="type1" name="type" list="thirdPartyTypeList"
								listKey="id" listValue="type" headerValue="Select TP Type"
								headerKey="0" cssClass="form-control showToolTip chosen"
								data-toggle="tooltip" onchange="setTypeName(this.value)" />
								<label  id = "tpTypeError" class="text-danger"></label>	
						</div>
						
						<div class="col-lg-3 col-md-3">
								<s:hidden name = "typeName"></s:hidden>
						</div>
						
						
						<div class="col-lg-3 col-md-3">
							<label>Third Party Name(New)</label><label><span
								class="text-danger">*</span></label>
							<s:textfield id="companyName" name="companyName" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter New InsuranceCo/Surgery Name"
								title="Enter New InsuranceCo/Surgery Name" />
						</div>

					</div>
					<div class="row">
						<div class="col-lg-3 col-md-3"></div>
						<div class="col-lg-3 col-md-3">
							<label>Direct Telephone No.</label>
							<s:textfield id="telephoneLine" name="telephoneLine"
								theme="simple" cssClass="form-control showToolTip"
								data-toggle="tooltip" placeholder="Direct Telephone No."
								title="Direct Telephone No."
								onchange="checTelePhoneLineValidation(this.value)" />
								<label id = "telephoneLineError" class="text-danger"></label>
								


						</div>
						<div class="col-lg-3 col-md-3">
							<label>Email</label>
							<s:textfield id="compnyEmail" name="compnyEmail" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Email" title="Enter Email"
								onchange="checkComapnyEmailValidation(this.value)" />
										 			<label id = "compnyEmailError" class="text-danger"></label>
								
								
						</div>
						<div class="col-lg-3 col-md-3">
							<label>Fax</label>
							<s:textfield id="compnyFax" name="compnyFax" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Fax" title="Enter Fax"
								 />
										 			
								
								
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3 col-md-3">
							<label>First Line of Address</label>
							<s:textfield id="tpaddress" name="address" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Address" title="Enter Address" />
						</div>
						<div class="col-lg-3 col-md-3">
							<label>Town/City</label>
							<s:textfield id="tptown" name="town" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Town" title="Enter Town" />
						</div>
						<div class="col-lg-3 col-md-3">

							<label>Post Code</label>
							<s:textfield id="tppostcode" name="postcode" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Post Code" title="Enter Post Code" onblur="initialCap(this);"/>
						</div>
						<div class="col-lg-3 col-md-3">
							<label>Country</label>
							
								<s:if test="%{#countryList != 'null'}" >
					   			<s:select id="tpcountry" name="country" list="countryList" headerKey="0" headerValue="Select Country" 
					   			labelposition="left" title="Select your country from list" theme="simple" cssClass="form-control showToolTip"
								data-toggle="tooltip"  />
			   	   			</s:if>	
						</div>

					</div>
					<br>
					<div class="row">
						<div class="col-lg-6 col-md-6">

							<label>TP Note</label>
							<s:textarea cols="60" rows="4" id="tpAccountSettingNotes" name="tpAccountSettingNotes" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter TP Note" title="Enter TP Note" />
						</div>
					</div>
					<br>
					</div>
					</div>
					</div>
					</div>
	<s:submit value="Update" cssClass="btn btn-primary"></s:submit>				
</s:form>
					