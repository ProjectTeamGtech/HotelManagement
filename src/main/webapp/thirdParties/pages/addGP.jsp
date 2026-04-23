<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="thirdParties/js/gp.js"></script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Add GP</h4>

									</div>
								</div>
								<br>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<s:form action="saveGP" id="gp_form" theme="simple">		
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="">
		<div class="">			
			<div class="row">
				<div class="col-lg-12">
					<div class="form-group">
					<label>Doctor Surgery</label><label><span class="text-danger">*</span></label>
					<s:if test="%{#tpCompanyList != 'null'}" >
						<s:select id="thirdPartyId"  name="thirdPartyId"	list="tpCompanyList" listKey="id" 
						listValue="thirdPartyCompanyName" headerValue="Select Doctor Surgery" headerKey="0"  theme="simple"
						cssClass="form-control showToolTip chosen"	data-toggle="tooltip"/>	
						<label  id = "tnameError" class="text-danger"></label>	
					</s:if>							
					</div>
				</div>	
			</div>
		</div>
				<div class="panel-body">
					<label>Contact/GP Name</label><label><span class="text-danger">*</span></label>
					<s:textfield id="name" name="name"	title="Enter GP Name"
					cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter GP Name" />
					
					
				</div>
				<div class="panel-body">
					<label>Work Phone Number</label>
					<s:textfield id="workphno" name="workphno"	title="Enter work ph no" 
					cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter work ph no" />
					
				</div>
				<div class="panel-body">
					<label>Email</label>
					<s:textfield id="emailid" name="emailid" title="Enter Email"
					cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Email" />
						
				</div>
				<div class="panel-body">
					<label>Fax</label>
					<s:textfield id="fax" name="fax" title="Enter Fax"
					cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Fax" />
					
				</div>
				<div class="panel-body">
						<label>Note</label>
						<s:textarea id="description" name="description"	title="Enter Note"
						cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Note" />
						
				</div>	
				</div>	
			</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	
	
		<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return saveValidation()" />
			<!--<s:reset cssClass="btn btn-primary" />  -->
			<a href="backGP" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>	
</s:form>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>

			
			

		
			
			
			
