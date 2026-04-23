<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript"
	src="thirdParties/js/addThirdPartyValidaton.js"></script>



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
							<s:select id="popuptype" name="type" list="thirdPartyTypeList"
								listKey="id" listValue="type" headerValue="Select TP Type"
								headerKey="0" cssClass="form-control showToolTip chosen"
								data-toggle="tooltip" onchange="setPopupTypeName(this.value)" />
							<label  id = "popuptpError" class="text-danger"></label>	
						</div>
						
						<div class="col-lg-3 col-md-3">
								<label>Third Party Name</label><label>
								<span class="text-danger">*</span></label>			
								<select id = "popuptypeName" name = "typeName"  class="form-control showToolTip chosen" data-toggle="tooltip" 
										theme="simple" onchange="popupdisableFiled(this.value)"/>
									
										<option value="0">Select TP Name</option>
								<select>
								<label  id = "popuptpnameError" class="text-danger"></label>	
						</div>
						
						<!--  <div class="col-lg-3 col-md-3">
							<label>Third Party Name</label><label><span
								class="text-danger">*</span></label>

							<s:select cssClass="form-control showToolTip chosen"
								data-toggle="tooltip" id="typeName1" name="typeName"
								list="thirdPartyTypeNameList" listKey="id"
								listValue="thirdPartyCompanyName" headerKey="0" theme="simple"
								headerValue="Select TP Name" onchange="disableFiled(this.value)"/>


						</div>-->
						<div class="col-lg-3 col-md-3">
							<label>Registered TP Name</label><label><span
								class="text-danger">*</span></label>
							<s:textfield id="ppcompanyName" name="companyName" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter New InsuranceCo/Surgery Name"
								title="Enter New InsuranceCo/Surgery Name" />
						</div>

					</div>
					<div class="row">
						<div class="col-lg-3 col-md-3"></div>
						<div class="col-lg-3 col-md-3">
							<label>Direct Telephone No.</label>
							<s:textfield id="pptelephoneLine" name="telephoneLine"
								theme="simple" cssClass="form-control showToolTip"
								data-toggle="tooltip" placeholder="Direct Telephone No."
								title="Direct Telephone No."
								onchange="checTelePhoneLineValidation(this.value)" />
								<label id = "telephoneLineError" class="text-danger"></label>
								


						</div>
						<div class="col-lg-3 col-md-3">
							<label>Email</label>
							<s:textfield id="ppcompnyEmail" name="compnyEmail" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Email" title="Enter Email"
								onchange="checkComapnyEmailValidation(this.value)" />
										 			<label id = "compnyEmailError" class="text-danger"></label>
								
								
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3 col-md-3">
							<label>First Line of Address</label>
							<s:textfield id="pptpaddress" name="address" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Address" title="Enter Address" />
						</div>
						<div class="col-lg-3 col-md-3">
							<label>Town/City</label>
							<s:textfield id="pptptown" name="town" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Town" title="Enter Town" />
						</div>
						<div class="col-lg-3 col-md-3">

							<label>Post Code</label>
							<s:textfield id="pptppostcode" name="postcode" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Post Code" title="Enter Post Code" onblur="initialCap(this);"/>
						</div>
						<div class="col-lg-3 col-md-3">
							<label>Country</label>
							
					<s:if test="%{#countryList != 'null'}" >
		   			<s:select id="pptpcountry" name="country" list="countryList" headerKey="0" headerValue="Select Country" 
		   			labelposition="left" title="Select your country from list" theme="simple" cssClass="form-control showToolTip"
					data-toggle="tooltip"  />
   	   			</s:if>	
						</div>

					</div>
					<br>
					<div class="panel panel-primary" style="display: none;" id = "popupgpcontactdiv">

						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<label>Contact Details</label>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 col-md-3">
									<label>Contact/GP Name</label><label><span
										class="text-danger">*</span></label>
									<s:textfield id="ppgpname" name="gpname" title="Enter GP Name"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter GP Name" />
								</div>

								<div class="col-lg-3 col-md-3">
									<label>Work Phone No.</label>
									<s:textfield id="ppworkphno" name="workphno"
										title="Enter work ph no" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter work ph no" />
								</div>



								<div class="col-lg-3 col-md-3">
									<label>Email</label>
									<s:textfield id="ppgpemailid" name="gpemailid"
										title="Enter Email" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter Email" />
								</div>

								<div class="col-lg-3 col-md-3">
									<label>Fax</label>
									<s:textfield id="ppgpfax" name="gpfax" title="Enter Fax"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Fax" />
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6">

									<label>Notes</label>

									<s:textarea id="ppnotes" name="notes" title="Enter Notes"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter notes"></s:textarea>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-12 col-md-12" id = "popupaccountSetting">
		<div class="row" style="padding-left: 15px;padding-right: 15px;">
			<div class="col-lg-12 bg-grey">
				<label>Account Setting</label>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-body">
				<div class="form-group">
		
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Agreed Credit Limit:</label>
				</div>
				<div class="col-lg-4 col-md-4">	
		 			<s:textfield name = "outInvoiceLimit" id = "ppoutInvoiceLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Credit Limit"/>
				</div>
				<div class="col-lg-2 col-md-2">	
					<label>Credit Reminder Limit:</label>
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield name = "accountWarningLimit" id = "ppaccountWarningLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Credit Reminder limit"/>
				</div>
				
			</div><br>
			<div class="row">
				<div class="col-lg-2 col-md-2">
					<label>Agreed Credit Duration:</label>	
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield name = "creditDuration" id = "ppcreditDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in Days" title="Enter Agreed Duration In Days"/>
				</div>
				<div class="col-lg-2 col-md-2">
					<label>Credit Reminder Duration:</label>
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield name = "creditReminderDuration" id = "ppcreditReminderDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in Days" title="Enter Credit Reminder Duration In Days"/>
				</div>
			</div><br>
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>DNA Limit in %:</label>
				</div>
				<div class="col-lg-4 col-md-4">	
		 			<s:textfield name = "dnaLimit" id = "ppdnaLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "00%" title="Enter DNA Limit"/>
		 		</div>
		 	</div>
		</div>
					<br>
					<div class="panel panel-primary">

						<div class="panel-body">
							<div class="row">
									<div class="col-lg-6 col-md-6">
										<label>Agreed Charges for DNA Appointments</label>
									
									</div>
									<div class="col-lg-6 col-md-6">
									<label>Agreed Charges for Completed Appointments</label>
									</div>
							</div>
							<div class="row">
									<div class="col-lg-2 col-md-2">
										<label>Initial Appointment</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield name = "dnaInitialApmt" id = "ppdnaInitialApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Initail Apmt charges"/>
									</div>
									<div class="col-lg-2 col-md-2"></div>
									<div class="col-lg-2 col-md-2">
										<label>Initial Appointment</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield name = "compltInitialApmt" id = "ppcompltInitialApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Initail Apmt charges"/>
									</div>
									
							</div><br>
							<div class="row">
									<div class="col-lg-2 col-md-2">
										<label>Follow-up Appointment</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield name = "dnafollowupApmt" id = "ppdnafollowupApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter FollowUP Apmt charges"/>
									</div>
									<div class="col-lg-2 col-md-2"></div>
									<div class="col-lg-2 col-md-2">
										<label>Follow-up Appointment</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield name = "compltfollowupApmt" id = "ppcompltfollowupApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter FollowUP Apmt charges"/>
									</div>
									
							</div><br>
							<div class="row">
									<div class="col-lg-2 col-md-2">
										<label>Final Appointment</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield name = "dnafinalApmt" id = "ppdnafinalApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Final Apmt charges"/>
									</div>
									<div class="col-lg-2 col-md-2"></div>
									<div class="col-lg-2 col-md-2">
										<label>Final Appointment</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield name = "compltfinalApmt" id = "ppcompltfinalApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Final Apmt charges"/>
									</div>
									
							</div><br>
							<div class="row">
									<div class="col-lg-2 col-md-2">
										<label>Maintenance</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield name = "dnamaintenanceApmt" id = "ppdnamaintenanceApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Maintence charges"/>
									</div>
									<div class="col-lg-2 col-md-2"></div>
									<div class="col-lg-2 col-md-2">
										<label>Maintenance</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield name = "compltmaintenanceApmt" id = "ppcompltmaintenanceApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Maintence charges"/>
									</div>
									
							</div><br>
							

						</div>
					</div>
				</div>
				
			</div>
					
			
		</div>
		<br><br>
