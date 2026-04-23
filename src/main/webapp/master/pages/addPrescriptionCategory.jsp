<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="master/js/prescriptioncategory.js"></script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Prescription Category</h4>
								</div>
							</div>

<s:form action="savePrescriptioncategory" id="prescription_form" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">						
						<div class="table-responsive">
							
							<table class="table table-striped table-hover table-condensed" id = "presciptionTable">
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Name</th>
										<th align="center">Description</th>													
									</tr>
								</thead>
								<tbody>
									<tr>
										<TD><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<TD>1</TD>										
										<td><input type="text" name="prescription_category[0].name" maxlength="50" placeholder="Enter Name"
											class="form-control showToolTip filedname" data-toggle="tooltip"></td>
										<td><input type="text" name="prescription_category[0].description" maxlength="50" placeholder="Enter Description"
											class="form-control showToolTip" data-toggle="tooltip"></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
								<a onclick="addRow('presciptionTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteRow('presciptionTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
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
			<a href="backPrescriptioncategory" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
