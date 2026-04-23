<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="master/js/investigationType.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Outsource Investigation Charge</h4>
								</div>
							</div>

<s:form action="saveoutsourcerateMaster" id="saveoutsourcerate_form" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">						
						<div class="table-responsive">
						<div>
							<s:select name="vendor" id="vendor" cssClass="form-control chosen-select" 
                                              list="outsourcevendorlist" listKey="id" listValue="name"  
                                              headerKey="0" headerValue="Select Outsource Lab" cssStyle="width: 20%;margin-left: 526px;"/>
                                              </div><br>
							<table class="table table-striped table-hover table-condensed" id = "outsourcerateTable">
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center" style="width: 53%">Investigation Name</th>
										<th align="center" style="width: 25%">Charge</th>		
									</tr>
								</thead>
								<tbody>
									<tr>
										<TD><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<TD>1</TD>		
										<td>
											<s:select name="outsourcerate[0].invsttype" id="invest" cssClass="form-control chosen-select" 
                                              list="invsTypeList" listKey="id" listValue="name"  
                                              headerKey="0" headerValue="Select Investigation Type" cssStyle="width:50%;"/>
											</td>
										<td><input type="text" name="outsourcerate[0].charge" maxlength="50" placeholder="Enter Charge"
											class="form-control showToolTip filedname" data-toggle="tooltip" style="width: 31%"></td>
								</tbody>
							</table>
						</div>
						<div class="form-group">
								<a onclick="addOutRow('outsourcerateTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteOutRow('outsourcerateTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
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
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return validateAF();"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="backInvestigationMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
