<%@taglib uri="/struts-tags" prefix="s"%>
<script src="master/js/investigationname.js"></script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add NursingCare Intervention</h4>
								</div>
							</div>

<s:form action="saveNursingCareIntervention" id="master_form" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed"  width="100%" >
								<thead>
									<tr>
										<!-- <th align="center">Delete</th> -->
											
									    <th align="center">Select Planning</th>
										<th align="center">Intervention Name</th>							
									</tr>
								</thead>
								<tbody>
									<tr>
										<!-- <td><INPUT type="checkbox" name="chk" title="Delete row" /></TD> -->										
									
										<td><s:select list="nursingplanninglist" listKey="id" listValue="nursing_planning" name="nursing_planning" title="select planning type"
										cssClass="form-control"  > </s:select>
										
										</td>									
									 <td><input type="text" name="nursing_intervention" placeholder="enter intervention name" id="nursing_intervention" class="form-control showToolTip filedname" data-toggle="tooltip"/></td> 	
										
																		
										
									</tr>
								</tbody>
							</table>
						</div>
			
			</div> 
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="saveNursingCareIntervention" />
			<s:reset cssClass="btn btn-primary" />
			<a href="NursingCareIntervention" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>


  