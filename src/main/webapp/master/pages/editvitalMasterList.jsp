<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="Vitalmaster">All Vital Master List</a></li>
			<li class="active">Edit Vital Master</li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error"/></span>
	</div>
</div>

<s:form action="updateVitalmaster" id="master_form" theme="simple" enctype="multipart/form-data">
 <s:hidden name="id"></s:hidden>
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				
			    <label>Select Vital Type</label><label class="text-danger">*</label>
			    <s:select theme="simple" list="vitalTypeList" listKey="id" listValue="name" name="vital_type" cssClass="showToolTip form-control"></s:select>
			    <label>Name </label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name" />	
					  <label>Unit </label><label class="text-danger">*</label>
				<s:textfield id="unit" name="unit"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Unit" />	
					
				 <label>Min Value </label><label class="text-danger">*</label>
				<s:textfield id="min_value_m" name="min_value_m"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Max Value" placeholder="Enter Min Value" />	
					
				 <label>Max Value </label><label class="text-danger">*</label>
				<s:textfield id="max_value_m" name="max_value_m"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Max Value" placeholder="Enter Max Value" />	
							
					
				<label>Select Image (Only .png)</label><label class="text-danger">*</label>	
				<s:file name="userFile" id="userFile" cssStyle="width: 100%;"/>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>




	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Update" />
			<s:reset cssClass="btn btn-primary" />
			<a href="Vitalmaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>



