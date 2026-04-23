<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="assesmentForms/js/assessment.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="AssesmentMasterForms">All Assessment Field List</a></li>
			<li class="active">Add Assessment Sub Heading </li>
		</ol>
	</div>
</div>

<s:form action="saveSubHeadingAssesmentMasterForms" id="assessment_form" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">			
				<div class="row">
					<div class="col-lg-6">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "subheadTable">
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Assessment Sub Heading Name</th>													
									</tr>
								</thead>
								<tbody>
									<tr>
										<TD><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<TD>1</TD>										
										<td><input type="text" name="subhead[0].headingName"
											class="form-control showToolTip headingName" data-toggle="tooltip"></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
								<a onclick="addRowSubHead('subheadTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteRowSubHead('subheadTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
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
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return validateSH();"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="backSubHeadAssesmentMasterForms" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
