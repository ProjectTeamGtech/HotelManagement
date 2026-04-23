<%@taglib uri="/struts-tags" prefix="s"%>
<script src="master/js/investigationname.js"></script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Investigation Name</h4>
								</div>
							</div>

<s:form action="saveInvestigationNameMaster" id="investigation_name_form" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "investigationnameTable" width="100%" >
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Investigation Type</th>
										<th align="center">Name</th>			
										<th align="center">Specimen</th>
										<!-- <th align="center">Investigation Group</th>	 -->
										<th align="center">Unit</th>
										<th align="center">Normal Value</th>	
										<th align="center">Charge</th>									
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<td>1</td>
										<td><s:select list="invsTypeList" listKey="id" listValue="name" name="investigation_name[0].id" title="select investigation type"
										cssClass="form-control"  onchange = "setinvestigationtype(this.value)"> </s:select>
										
										</td>									
										<td><input type="text" name="investigation_name[0].name" placeholder="enter name" id="name" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td><input type="text" name="investigation_name[0].specimen" placeholder="enter specimen" id="specimen" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td style="display: none;"><input type="text" name="investigation_name[0].report_type" placeholder="enter reporttype" id="reporttype" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="investigation_name[0].unit" placeholder="enter unit" id="unit" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="investigation_name[0].normal_value" placeholder="enter normal value" id="normal_value" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td><input type="text" name="investigation_name[0].charge" placeholder="enter charge" id="charge" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
								<a onclick="addRow('investigationnameTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteRow('investigationnameTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
			<%-- 	<label>Assessment Field Name</label><label class="text-danger">*</label>
				<s:textfield id="filedname" name="filedname"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter filedname" placeholder="Enter filedname"/> --%>
					</div>
			</div>
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<%-- <s:submit cssClass="btn btn-primary" value="Save" onclick="saveInvestigationNameMaster" /> --%>
			<button class="btn btn-primary" id="invstname" onclick="addinvstname()">Save</button>
			<s:reset cssClass="btn btn-primary" />
			<a href="backInvestigationNameMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
