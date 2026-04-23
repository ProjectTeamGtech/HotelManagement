<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>



<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Update Charge</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
	<span class="error"><s:actionerror id="server_side_error" /></span>
<s:form action="updateAppointmentType" id="appointType_form"
	theme="simple">
<s:hidden name="id" id="editapmtid"/>

<table class="table table-bordered">
					<thead>
						<tr>
							<th style="width: 15%;"><label class="red">*</label><label>Charge Type</label></th>
							<th style="width: 11%;"><label class="red">*</label><label>Charge Type Name</label></th>
							<th><label>Description</label></th>
							<th><label>Category</label></th>
							<th style="width: 11%;"><label class="red">*</label><label>Charge Amount</label></th>
							<th><label>Base Charge</label></th>
							<th><label>Code</label></th>
							<th style="width: 7%;"><label>Send Report</label></th>
							<th style="width: 7%;"><label>Is shareable</label></th>
							<th><label class="red">*</label><label>Duration</label></th>
							<th><label>Location</label></th>
							<th style="width: 10%;"></th>
						</tr>
					</thead>
							<tbody>
							<tr>
								<td>
									<s:select name="chargeType" id="chargeType"
								list="newChargeTypeList" listKey="name" listValue="name"
								headerValue="Select Charge Type" headerKey="0" theme="simple"
								onchange="changeDiv()" cssClass="form-control showToolTip chosen-select"
								data-toggle="tooltip" title="Select Charge">
							</s:select><br>
							<label id="chargeTypeError" name="chargeTypeError"
								class="text-danger"></label>
								</td>
								<td>
									<s:textfield id="name" name="name" readonly="false"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								title="Enter Name" placeholder="Enter Name"  onkeyup="initialCap(this)"  /> <!-- maxlength = "60" remove @manoj sir -->
								<small class="text-danger"></small><br>
								<label id = "nameError" class="text-danger"></label>	
								</td>
								<td>
									<s:textarea id="description" name="description"
								cssClass="showToolTip form-control" data-toggle="tooltip"
								title="Enter Description" placeholder="Enter Description"
								rows="3" />
								</td>
								<td>
									<s:textfield id="category" name="category" title="Enter Category"
								cssClass="showToolTip form-control" data-toggle="tooltip"
								placeholder="Enter Category" />
								</td>
								<td>
									<s:textfield id="charges" name="charges"
								cssClass="showToolTip form-control" data-toggle="tooltip"
								title="Enter Charge Amount" placeholder="Enter Charge Amount" /><br>
								<label id="chargesError"  class="text-danger"></label>
								</td>
								<td>
									<s:textfield id="basecharge" name="basecharge"
								cssClass="showToolTip form-control" data-toggle="tooltip"
								title="Enter Base Charge" placeholder="Enter Base Charge " />
								</td>
								<td>
									<s:textfield id="code" name="code"
									cssClass="showToolTip form-control" data-toggle="tooltip"
									title="Enter Code" placeholder="Enter Code" />
									<label id="codeError" class="text-danger"></label>	
								</td>
								<td><s:checkbox name="reportField" id="reportField"  /></td>
								<td><s:checkbox name="shareablecharge" id="shareablecharge"  /></td>
								<td>
									<s:if test="%{#apmtDurationList != 'null'}">
									<s:select id="duration" name="duration" list="apmtDurationList"
										headerKey="0" headerValue="00:00" theme="simple"
										cssClass="showToolTip form-control" data-toggle="tooltip"
										title="Select Duration" />
								</s:if><br>
								<label id="durationError" name="durationError" class="text-danger"></label>
								</td>
								<td>
									<s:if test="%{#locationList != 'null'}">
									<s:select id="location" name="location" list="locationList" headerKey="0" headerValue="All"
										listKey="location" listValue="location" theme="simple"
										cssClass="showToolTip form-control" data-toggle="tooltip"
										title="Select Location" />
								</s:if>
								</td>
								<td>
									<%-- <s:submit cssClass="btn btn-primary" value="Update" onclick="return validEntry()" /> --%>
									<a onclick="validEntry()" class="btn btn-primary">Save </a>
									<a href="backAppointmentType" class="btn btn-primary">Back</a>
								</td>
							</tr>
					</tbody>
				</table>
		<div class="row" id="hiddenDiv">
			<div class="col-lg-3 col-md-2"></div>
			<div class="col-lg-6 col-md-8"></div>
			<div class="col-lg-3 col-md-2"></div>
		</div>
	<s:token></s:token>
</s:form>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>
  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>

