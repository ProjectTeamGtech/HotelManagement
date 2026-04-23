<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="thirdParties/js/thirdpartyType.js"></script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Edit Third Party Type</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error"/></span>
	</div>
</div>

<s:form action="updateThirdPartyType" id="thirdPartyType_form" theme="simple">
<s:hidden name = "id" id ="id"></s:hidden>

<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="">
			<div class="panel-body">
			<div class="form-group">
				<label>Patient Type</label><label class="text-danger">*</label>
				<s:select list="#{'0':'Self','1':'Third Party'}" name="patientType" cssClass="form-control"></s:select>
					
			</div>	
			<div class="form-group">
				<label>Name</label><label class="text-danger">*</label>
				<s:textfield id="type" name="type"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Type" placeholder="Enter Type" onkeyup="initialCap(this)" />
					
			</div>	
			<div class="form-group">
				<label>Description</label>
				<s:textfield id="description" name="description"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Description" placeholder="Enter Description" />
					
			</div>	
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>




	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Update" />
			<s:reset cssClass="btn btn-primary" />
			<a href="backThirdPartyType" class="btn btn-primary">Back</a>
			
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>


											

											
										</div>
									</div>
								</div>
							</div>
						</div>





