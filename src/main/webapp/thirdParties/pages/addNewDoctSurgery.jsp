<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript"
	src="thirdParties/js/addThirdPartyValidaton.js"></script>


	<div class="col-lg-12 col-md-12">
		
	
		<div class="panel panel-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="row">
						<div class="col-lg-3 col-md-3">
							<label>Third Party Type</label><label><span
								class="text-danger">*</span></label>
							<s:select id="dstype" name="dstype" list="thirdPartyTypeList"
								listKey="id" listValue="type" 
								 cssClass="form-control showToolTip chosen"
								data-toggle="tooltip" />
								<label  id = "dstypeError" class="text-danger"></label>	
						</div>
						
						<%-- <div class="col-lg-3 col-md-3">
								<label>Third Party Name</label><label>
								<span class="text-danger">*</span></label>			
								
								<s:select id="typeName1" name="typeName" list="tpnameList"
								listKey="id" listValue="typeName" headerValue="Select TP Name"
								headerKey="0" cssClass="form-control showToolTip chosen"
								data-toggle="tooltip" onchange="disableFiled(this.value)" />
						</div> --%>
						
						
						<div class="col-lg-3 col-md-3">
							<label>Doctor Surgery Name</label><label><span
								class="text-danger">*</span></label>
							<s:textfield id="dsname" name="dsname" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter New Surgery Name"
								title="Enter New Surgery Name" />
						</div>
						<div class="col-lg-3 col-md-3">
							<label>Direct Telephone No.</label>
							<s:textfield id="dstelephoneLine" name="dstelephoneLine"
								theme="simple" cssClass="form-control showToolTip"
								data-toggle="tooltip" placeholder="Direct Telephone No."
								title="Direct Telephone No."
								onchange="checTelePhoneLineValidation(this.value)" />
								<label id = "telephoneLineError" class="text-danger"></label>
								


						</div>
						<div class="col-lg-3 col-md-3">
							<label>Email</label>
							<s:textfield id="dscompnyEmail" name="dscompnyEmail" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Email" title="Enter Email"
								onchange="checkComapnyEmailValidation(this.value)" />
										 			<label id = "compnyEmailError" class="text-danger"></label>
								
								
						</div>

					</div>
				
					<div class="row">
						<div class="col-lg-3 col-md-3">
							<label>First Line of Address</label>
							<s:textfield id="dsaddress" name="dsaddress" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Address" title="Enter Address" />
						</div>
						<div class="col-lg-3 col-md-3">
							<label>Town/City</label>
							<s:textfield id="dstown" name="dstown" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Town" title="Enter Town" />
						</div>
						<div class="col-lg-3 col-md-3">

							<label>Post Code</label>
							<s:textfield id="dspostcode" name="dspostcode" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Post Code" title="Enter Post Code" onblur="initialCap(this);"/>
						</div>
						<div class="col-lg-3 col-md-3">
							<label>Country</label>
							
					<s:if test="%{#countryList != 'null'}" >
		   			<s:select id="dscountry" name="dspostcode" list="countryList" headerKey="0" headerValue="Select Country" 
		   			labelposition="left" title="Select your country from list" theme="simple" cssClass="form-control showToolTip"
					data-toggle="tooltip"  />
   	   			</s:if>	
						</div>

					</div>
					<br>
					<div class="panel panel-primary">

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
									<s:textfield id="dsgpname" name="dsgpname" title="Enter GP Name"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter GP Name" />
								</div>

								<div class="col-lg-3 col-md-3">
									<label>Work Phone No.</label>
									<s:textfield id="dsworkphno" name="dsworkphno"
										title="Enter work ph no" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter work ph no" />
								</div>



								<div class="col-lg-3 col-md-3">
									<label>Email</label>
									<s:textfield id="dsgpemailid" name="dsgpemailid"
										title="Enter Email" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter Email" />
								</div>

								<div class="col-lg-3 col-md-3">
									<label>Fax</label>
									<s:textfield id="dsgpfax" name="dsgpfax" title="Enter Fax"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Fax" />
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6">

									<label>Notes</label>

									<s:textarea id="dsnotes" name="dsnotes" title="Enter Notes"
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

		