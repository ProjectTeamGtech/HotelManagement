<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>


<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update Investigation Name</h4>
								</div>
							</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>
<s:form action="updateInvestigationNameMaster" id="investigation_name_form" theme="simple">
<div class="row">
	<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "investigationnameTable" width="100%" >
								<thead>
									<tr>
										<th align="center">Investigation Type</th>
										<th align="center">Name</th>			
										<th align="center">Specimen</th>
									<!-- 	<th align="center">Investigation Group</th>	 -->
										<th align="center">Unit</th>
										<th align="center">Normal Value</th>
										<th align="center">Charge</th>			
																
									</tr>
									 <s:hidden name="id"/> 
								</thead>
								<tbody>
									<tr>
									   
										<td><s:select name="investigation_type_id" id="investigation_type_id" list="invsTypeList" listKey="id" listValue="name"  title="select investigation type"
										cssClass="form-control"  onchange = "setinvestigationtype(this.value)"> </s:select>
										</td>									
										<td><s:textfield name="name" placeholder="enter name" id="name" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td><s:textfield name="specimen" placeholder="enter specimen" id="specimen" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td style="display: none;"><s:textfield name="report_type" placeholder="enter investigation group" id="reporttype" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><s:textfield name="unit" placeholder="enter unit" id="unit" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><s:textfield name="normal_value" placeholder="enter normal value" id="normal_value" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td><s:textfield name="charge" placeholder="enter charge" id="charge" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary"  value="Update" />
			<s:reset cssClass="btn btn-primary" />
			<a href="backInvestigationNameMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
		</div>
 	</div>
 </div>
 
</s:form>

