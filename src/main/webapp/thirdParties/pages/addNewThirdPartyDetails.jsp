<%@taglib uri="/struts-tags" prefix="s"%>
				
				<div class="row">
						<div class="col-lg-4">
							<label>Select Third Party Type</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-8">	
							<s:if test="%{#thirdPartyTypeList != 'null'}">
								<s:select id="thirdPartyType1" name="thirdPartyType1"
										list="thirdPartyTypeList" listKey="id" listValue="type"
										headerKey="0" theme="simple" headerValue="Select"
										title="Select Third Party Type" cssClass="form-control showToolTip"
								data-toggle="tooltip"  />
							</s:if>
							<label id="thirdPartyTypeError1" class="text-danger"></label>
						</div>
					</div>
					
					
						
					<div class="row">
						<div class="col-lg-4">
							<label>Third Party Name</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-8">
							<s:textfield id="thirdPartyCompanyName"
									name="thirdPartyCompanyName" theme="simple"
									title="Enter Company Name."
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter comany Name." />
							<label id="thirdPartyCompanyNameError"
								class="text-danger"></label>
						</div>
					</div>
						
					<div class="row">
						<div class="col-lg-4">	
							<label> Email</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-8">	
							<s:textfield id="compnyEmail" name="compnyEmail"
									theme="simple" title="Enter Company Email"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Company Email" />
							<label id="compnyEmailError" class="text-danger"></label>
						</div>
					</div>
						
					<div class="row">
						<div class="col-lg-4">		
						<label>Contact No.</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-4">	
							<s:textfield id="compnyTelephone" name="compnyTelephone"
									theme="simple" title="Enter Contact No."
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Contact No." />
						<label id="compnyTelephoneError" class="text-danger"></label>
						</div>
					</div>
					
						<label><b>Account Setting</b></label>
					<div class="row">
						<div class="col-lg-4">	
							<label>Credit Limit</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-8">	
							<s:textfield name="outInvoiceLimit" id="outInvoiceLimit"
									theme="simple" title="Enter 
									Outstanding Invoice Limit."
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Outstanding Invoice Limit." />
							<label id="outInvoiceLimitError" class="text-danger"></label>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-4">	
							<label>Credit Reminder Limit</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-8">	
							<s:textfield name="accountWarningLimit"
									id="accountWarningLimit" theme="simple" title="Enter Credit Warning Limit."
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Credit Warning Limit" />
						<label id="accountWarningLimitError" class="text-danger"></label>
					</div>
					</div>
					
					<div class="row">
						<div class="col-lg-4">	
							<label>DNA Limit in %</label><label class="text-danger">*</label>
						</div>
						<div class="col-lg-8">	
							<s:textfield name="dnaLimit" id="dnaLimit"
									theme="simple" title="Enter DNA Limi."
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter DNA Limi"/>
							<label id="dnaLimitError" class="text-danger"></label>
						</div>
					</div>