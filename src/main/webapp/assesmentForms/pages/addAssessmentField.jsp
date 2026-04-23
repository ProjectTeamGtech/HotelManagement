<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="assesmentForms/js/assessment.js"></script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Add Assessment Field</h4>

									</div>
								</div>
								<br>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
										<s:form action="saveAssesmentMasterForms" id="assessment_form" theme="simple">
										<div class="col-lg-3 col-md-3 col-xs-12 col-sm-3"></div>
<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
<div class="panel panel-primary ">
			<div class="panel-body">
			<%-- <span style="color:red">Note : Assessment Field Name do not allow any special character</span><br> --%>			
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-8">						
						<div class="">
							
							<table class="table table-bordered" id = "assessmentTable">
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Assessment Field Name</th>													
									</tr>
								</thead>
								<tbody>
									<tr>
										<TD><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<TD>1</TD>										
										<td><input type="text" name="assessment[0].filedname" maxlength="50" placeholder="Enter field name only 50 character"
											class="form-control showToolTip filedname" data-toggle="tooltip"></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
								<a onclick="addRow('assessmentTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteRow('assessmentTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
			<%-- 	<label>Assessment Field Name</label><label class="text-danger">*</label>
				<s:textfield id="filedname" name="filedname"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter filedname" placeholder="Enter filedname"/> --%>
					</div>
			</div>
		</div>
		
	</div>
	
</div>

<div class="row">
		<div class="col-lg-12 col-md-12" style="text-align: right;">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return validateAF();"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="AssesmentMasterForms" class="btn btn-primary">Back</a>
		</div>
	</div>

</div>

<div class="col-lg-3 col-md-3 col-xs-12 col-sm-3"></div>
	<s:token></s:token>
</s:form>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>





