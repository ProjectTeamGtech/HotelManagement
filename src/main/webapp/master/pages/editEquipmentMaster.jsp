<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>


<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Equipment</h4>
								</div>
							</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<s:form action="updateequipmentmasterBed" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
				<label>Ward Name</label><label class="text-danger">*</label>
				
				<s:select disabled="true" list="wardlist" listKey="id" listValue="wardname" name="wardid"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Select Wardname" placeholder="Select Wardname" onkeyup="initialCap(this)"/>
			   <label>Section Name</label><label class="text-danger">*</label>
				
				<s:select disabled="true" list="sectionlist" listKey="id" listValue="sectionname" name="sectionid"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Select Section" placeholder="Select Wardname" onkeyup="initialCap(this)"/>  							
			   <label>Bed Name</label><label class="text-danger">*</label>
			  <s:select disabled="true" list="bedlist" listKey="id" listValue="bedname" name="bedid"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Select Bed" placeholder="Select Bed" onkeyup="initialCap(this)"/>
				   <label>Equipment Name</label><label class="text-danger">*</label>
				   
				 <s:textfield name="equipmentname" id="equipmentname" cssClass="showToolTip form-control" data-toggle="tooltip"></s:textfield>  	  			
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary"  value="Update"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="equipmentlistmasterBed" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
