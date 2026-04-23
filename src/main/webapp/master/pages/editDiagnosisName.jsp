<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="DiagnosisNameMaster">All Diagnosis Name List</a></li>
			<li class="active">Update Diagnosis Name</li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<s:form action="updateDiagnosisNameMaster" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
				<label>Diagnosis Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Diagnosis Name" placeholder="Enter Diagnosis Name" onkeyup="initialCap(this)"/>
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
			<a href="DiagnosisNameMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
