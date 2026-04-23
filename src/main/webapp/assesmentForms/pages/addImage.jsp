<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="assesmentForms/js/addassementImage.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="ImportImageForAssessment">All Image List</a></li>
			<li class="active">Add Image</li>
		</ol>
	</div>
</div>

<s:form action="addImportImageForAssessment" id="assessment_form" enctype="multipart/form-data" method="post" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">			
				<div class="row">
					<div class="col-lg-6">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "assessmentTable">
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Image Name</th>	
										<th align="center">Upload Image</th>													
									</tr>
								</thead>
								<tbody>
									<tr>
										<TD><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<TD>1</TD>										
										<!-- <td><input type="text" name="assessment[0].imagename"
											class="form-control showToolTip imagename" data-toggle="tooltip"></td> -->
										<!-- <td><input id="file" name = "assessment[0].file" type="file" /></td> -->
										<td><input type="text" name="imagename"
											class="form-control showToolTip imagename" data-toggle="tooltip"></td>
										<td><input  name = "fileUpload" type="file" class="fileUpload"/></td>	
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
	<div class="col-lg-3 col-md-2"></div>
</div>




	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return validate();"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="ImportImageForAssessment" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
