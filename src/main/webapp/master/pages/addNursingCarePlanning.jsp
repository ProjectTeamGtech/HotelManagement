<%@taglib uri="/struts-tags" prefix="s"%>
<script src="master/js/investigationname.js"></script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add NursingCare Plan</h4>
								</div>
							</div>

<s:form action="saveNursingCarePlanning" id="master_form" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "subtaskTable" width="100%" >
								<thead>
									<tr>
										<!-- <th align="center">Delete</th> -->
											
									    <th align="center">Select Diagnosis</th>
										<th align="center">Plann Name</th>							
									</tr>
								</thead>
								<tbody>
									<tr>
										<!-- <td><INPUT type="checkbox" name="chk" title="Delete row" /></TD> -->										
									
										<td><s:select list="nursingdiagnosislist" listKey="id" listValue="nursing_diagnosis" name="nursing_diagnosis" title="select diagnosis type"
										cssClass="form-control"  > </s:select>
										
										</td>									
									 <td><input type="text" name="nursing_planning" placeholder="enter planningname" id="nursing_planning" class="form-control showToolTip filedname" data-toggle="tooltip"/></td> 	
										
																		
										
									</tr>
								</tbody>
							</table>
						</div>
			<%-- 			<div class="form-group">
								<a onclick="addsubtaskRow('subtaskTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<!-- <a onclick="deleteRow('subtaskTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a> -->
								
								
				<label>Assessment Field Name</label><label class="text-danger">*</label>
				<s:textfield id="filedname" name="filedname"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter filedname" placeholder="Enter filedname"/>
					</div> --%>
			</div> 
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="saveNursingCarePlanning" />
			<s:reset cssClass="btn btn-primary" />
			<a href="NursingCarePlanning" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>


  