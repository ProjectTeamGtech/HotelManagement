<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update Nursing Details</h4>
								</div>
							</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>
<s:form action="updateNursingdetails" id="investigation_name_form" theme="simple">
<div class="row">
	<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "prescriptiondetailsTable" width="100%" >
								<thead>
									<tr>
										<th align="center">Nursing Category</th>
										<th align="center">Taskname</th>			
										<th align="center">Frequency</th>	
										<th align="center">Notes</th>	
									</tr>
									 <s:hidden name="id"/>
								</thead>
								<tbody>
									<tr>
									   
										<td><s:select list="nursingcategorylist" listKey="id" listValue="name" name="nursingtype_id" title="select nursing category"
										       cssClass="form-control showToolTip" > </s:select>
										</td>									
										<td><s:textfield name="taskname" placeholder="enter taskname" id="taskname" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><s:textfield name="frequency" placeholder="enter frequency" id="frequency" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><s:textfield name="notes" placeholder="enter notes" id="notes" cssClass="form-control showToolTip filedname" data-toggle="tooltip"/></td>

									</tr>
								</tbody>
							</table>
						</div>
						
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary"  value="Update" />
			<s:reset cssClass="btn btn-primary" />
			<a href="Nursingdetails" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
  </div>
</div>	
</s:form>
