<%@taglib uri="/struts-tags" prefix="s"%>
<label>Third Party Details Information</label>

					<div class="row">
						<div class="col-lg-4 col-md-4">
							<label>TP (Insurance Co/Group)</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-8 col-md-8">
							<s:select id="ctpthirdPartyType" name="thirdPartyType"
								list="thirdPartyTypeList" listKey="id" listValue="type"
								headerKey="0" headerValue="Select Type"
								onchange="setCompanyName(this.value)" theme="simple"
								title="Select Third Party Type"
								cssClass="form-control showToolTip chosen" data-toggle="tooltip" />
							<label id="thirdPartyTypeError" class="text-danger"></label>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-4 col-md-4">
							<label>TP Name</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-8 col-md-8">
						<select id = "ctpName" name = "companyName"  class="form-control showToolTip chosen" data-toggle="tooltip" 
										theme="simple" />									
								<option value="0">Select Company Name</option>
						<select>
							<!--  <s:select id="ctpName" name="companyName"
								list="thirdPartyTypeNameList" listKey="id"
								listValue="thirdPartyCompanyName" headerKey="0" theme="simple"
								title="Select Company Name" cssClass="form-control showToolTip chosen"
								data-toggle="tooltip" headerValue="Select 3rd Party Name" />-->
							<label id="companyNameError" class="text-danger"></label>
						</div>
					<!-- 	<div class="col-lg-2 col-md-2" style="display: none;">
							
							<a href="#" onclick="addNewThirdParty()" class="btn btn-primary" > <i class="fa fa-plus"></i> New TP </a>
								
						</div> -->
					</div>

					<div class="row">
						<div class="col-lg-4 col-md-4">
							<label>Policy No.</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-8 col-md-8">
							<s:textfield name="thirdPartyPolicyNo" id="thirdPartyPolicyNo"
								theme="simple" title="Enter Policy No."
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Policy No." />
							<label id="thirdPartyPolicyNoError" class="text-danger"></label>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-4 col-md-4">
							<label>Policy Expiry Date</label>
						</div>
						<div class="col-lg-8 col-md-8">
							<s:textfield name="thirdPartyExpiryDate"
								id="thirdPartyExpiryDate" theme="simple"
								title="Enter Expiry Date" cssClass="form-control showToolTip"
								data-toggle="tooltip" placeholder="Enter Expiry Date" />
							<label id="thirdPartyExpiryDateError" class="text-danger"></label>
						</div>
					</div>
					<div class="row">
					<div class="col-lg-4 col-md-4">
						<label>Policy Excess</label>
					</div>
						<div class="col-lg-8 col-md-8">		
								<s:textfield name="tppolicyExcess" id="tppolicyExcess" 
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder = "Enter Value"/>								
							</div>
							</div>