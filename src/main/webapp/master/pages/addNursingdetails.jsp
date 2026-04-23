<%@taglib uri="/struts-tags" prefix="s"%>
<script src="master/js/nursingdetails.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="Nursingdetails">All Nursing Details List</a></li>
			<li class="active">Add Nursing Details</li>
		</ol>
	</div>
</div>

<s:form action="saveNursingdetails" id="nursingdetailsform" theme="simple" onsubmit="return savenur()">
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "nursingdetailsTable" width="100%" >
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Nursing Category</th>
										<th align="center">Task Name</th>			
										<th align="center">Frequency</th>
										<th align="center">Notes</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<td>1</td>
										<td><s:select list="nursingcategorylist" listKey="id" listValue="name" name="nursing_details[0].nursingtype_id" title="select nursing category"
										cssClass="form-control showToolTip" > </s:select>
										
										</td>									
										<td><input type="text" name="nursing_details[0].taskname" placeholder="enter taskname" id="taskname" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td><input type="text" name="nursing_details[0].frequency" placeholder="enter frequency" id="frequency" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="nursing_details[0].notes" placeholder="enter notes" id="notes" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
								<a onclick="addRow('nursingdetailsTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteRow('nursingdetailsTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
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
			<s:submit cssClass="btn btn-primary" value="Save"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="Nursingdetails" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>

<script type="text/javascript">
$(document).ready(function() {
	$( "#baselayout1loaderPopup" ).modal( "hide" );
});


function savenur(){
	
	/* $( "#baselayout1loaderPopup" ).modal( "show" ); */
	alert('Saved');
	$( "#baselayout1loaderPopup" ).modal( "show" );
	return true;
} 

</script>

