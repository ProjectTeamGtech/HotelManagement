<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>

<%@page import="com.apm.main.common.constants.Constants"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Create Charge</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
														
										
										
<span class="error"><s:actionerror id="server_side_error" /></span>
<s:form action="saveAppointmentType" id="appointType_form" theme="simple">

<table class="table table-bordered" id="appointmenttypeTable">
					<thead>
						<tr>
						<!-- <th align="center">Delete</th> -->
										<!-- <th align="center">#</th> -->
							<th style="width: 15%;"><label class="red">*</label><label>Charge Type</label></th>
							<th style="width: 11%;"><label class="red">*</label><label>Charge Name</label></th>
							<th><label>Description</label></th>
							<th><label>Category</label></th>
							<th><label class="red">*</label><label>Charge Amount</label></th>
							<th><label>Base Charges</label></th>
							<th><label>Code</label></th>
							<th style="width: 7%;"><label>Send Report</label></th>
							<th style="width: 7%;"><label>Is shareable</label></th>
						    <th style="width: 7%;"><label>Session</label></th>	
							<th style="width: 15%;"><label>Select Clinic Name</label></th>
							<th style="width: 9%;"></th>
						</tr>
					</thead>
						<tbody>
							<tr>
							<!-- <td><INPUT type="checkbox" name="chk" title="Delete row" /></TD> -->										
								<!-- <td>1</td> -->
								<td>
								<%if(loginInfo.isBams1()){ %>
								  <s:select name="chargeType" id="chargeType" 
										list="newChargeTypeList" listKey="id" listValue="name"
										headerValue="Select Charge Type" headerKey="0" theme="simple"
										onchange="changeDiv()" cssClass="form-control showToolTip chosen-select"
										data-toggle="tooltip" title="Select Charge">
									</s:select>
								<%}else{ %>
									<s:select name="chargeType" id="chargeType" 
										list="newChargeTypeList" listKey="name" listValue="name"
										headerValue="Select Charge Type" headerKey="0" theme="simple"
										onchange="changeDiv()" cssClass="form-control showToolTip chosen-select"
										data-toggle="tooltip" title="Select Charge">
									</s:select>
									<%}%><br>
									<label id="chargeTypeError" name="chargeTypeError"
										class="text-danger"></label>
								</td>
								<td>
									<s:textfield id="name" name="name"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									title="Enter Name" placeholder="Enter Name" />
									<small class="text-danger"></small><br>
									<label id = "nameError" class="text-danger" onkeyup="initialCap(this)"></label>
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
									title="Enter Charge Amount" placeholder="Charge Amount " /><br>
									<label id="chargesError" class="text-danger"></label>	
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
	                     <% if(loginInfo.getClinicid1().equals("manasclinic") ) {%>
								<td>
			                            <s:select name="Clinic_name_id" list="Clinicstafflist"
						                   headerKey="0" headerValue="Select Clinic Name" listKey="id" listValue="Clinic_name"
						                     cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" id="Clinic_name" title="Select Clinic Name"/>
								</td>
								<%} %>
								<td>
									<s:textfield id="sessionno" name="sessionno"
									cssClass="showToolTip form-control" data-toggle="tooltip"
									title="Enter Session" placeholder="Enter Session" value="0" />
									<label id="codeError" class="text-danger"></label>	
								</td>
								<td>
									<%-- <s:submit cssClass="btn btn-primary" value="Save"  /> --%>
									<a onclick="validEntry()" class="btn btn-primary">Save </a>
									<!-- <a href="backAppointmentType" class="btn btn-primary">Back</a> -->
									<!-- <div class="form-group">
								<a onclick="addRow('appointmenttypeTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								</div> -->
								</td>
							</tr> 
							
							<%-- <tr>
							
										<!-- <td><INPUT type="checkbox" name="chk" title="Delete row" /></TD> -->										
										<td>1</td>
										<td><s:select list="newChargeTypeList" listKey="name" listValue="name" name="chargeTypeCollection[0].chargeType" title="select charge type"
										cssClass="form-control showToolTip chosen-select"  onchange = "changeDiv(this.value)"> </s:select>
										
										</td>									
										<td><input type="text" name="chargeTypeCollection[0].name" placeholder="enter name" maxlength = "30" id="name" class="form-control showToolTip filedname" data-toggle="tooltip"/><small class="text-danger">Max 30 Character Allowed </small><br></td>	
										<td><input type="text" name="chargeTypeCollection[0].description" placeholder="enter description" id="description" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="chargeTypeCollection[0].category" placeholder="enter category" id="category" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="chargeTypeCollection[0].charges" placeholder="enter charges" id="charges" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="chargeTypeCollection[0].basecharge" placeholder="enter basecharge" id="basecharge" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td><s:checkbox name="reportField" id="reportField"  /></td>
										<!-- <td><input type="text" name="chargeType[0].charge" placeholder="enter charge" id="charge" class="form-control showToolTip filedname" data-toggle="tooltip"/></td> -->
										<td>
									<s:submit cssClass="btn btn-primary" value="Save" onclick="return validEntry()" />
									<!-- <a href="backAppointmentType" class="btn btn-primary">Back</a> -->
								
								</td>
									</tr> --%>
							
							
							
							
					</tbody>
				</table>
				
					<div class="form-group hidden">
								<a onclick="addappointmenttypeRow('appointmenttypeTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								</div>
	<div id="hiddenDiv" style="display: none;">
		<div class="row">
			<div class="col-lg-3 col-md-2"></div>
			<div class="col-lg-6 col-md-8">
				<div class="panel panel-primary">
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<label>Duration</label><label class="red">*</label>
								<s:if test="%{#apmtDurationList != 'null'}">
									<s:select id="duration" name="duration" list="apmtDurationList"
										headerKey="0" headerValue="00:00" theme="simple"
										cssClass="showToolTip form-control" data-toggle="tooltip"
										title="Select Duration" />
								</s:if>
								<label id="durationError" 
									class="text-danger"></label>
							</div>
						</div>

						<%-- <div class="row">
							<div class="col-lg-12">
								<label>Color</label><label class="text-danger">*</label>
								<s:textfield id="color" name="color"
									cssClass="showToolTip form-control" data-toggle="tooltip"
									title="Select Color" placeholder="Select Color" />
							</div>
						</div> --%>
						<br />
						<div class="row">
							<div class="col-lg-12">
								<label>Location</label>
								<s:if test="%{#locationList != 'null'}">
									<s:select id="location" name="location" list="locationList" headerKey="0" headerValue="All"
										listKey="location" listValue="location" theme="simple"
										cssClass="showToolTip form-control" data-toggle="tooltip"
										title="Select Location" />
								</s:if>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-2"></div>
		</div>
	</div>
	
	<s:token></s:token>
</s:form>
										</div>
									</div>
								</div>
							</div>
						</div>
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
