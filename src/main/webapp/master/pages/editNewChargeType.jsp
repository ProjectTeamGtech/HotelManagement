<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="/APM/NewChargeType">All NewCharge Type List</a></li>
			<li class="active">Update New Charge Type</li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<s:form action="updateNewChargeType" id="form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
				<label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name" onkeyup="initialCap(this)"/>
					<label>Description</label><label class="text-danger">*</label>
				<s:textfield id="description" name="description"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Description" placeholder="Enter Description" onkeyup="initialCap(this)"/>
					<label>Procedure</label><label class="text-danger">*</label>
					<s:checkbox name="procedure" id="procedure"></s:checkbox>
					<div>
					<label>Is Consultant Mandatory</label><label class="text-danger">*</label>
					<s:checkbox name="consultant_compulsay" id="consultant_compulsay"></s:checkbox>
					</div>
					<%-- <br>
					<label>Ward</label><label class="text-danger"></label>
					<s:select list="wardList" id="wardnameid" name="wardid"
						listKey="id" listValue="wardname" headerKey="0"
						headerValue="Select Ward" cssClass="form-control">
					</s:select>
 --%>			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary"  value="Update"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="backNewChargeType" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>

 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>				
