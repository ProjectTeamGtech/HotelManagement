<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Discharge Outcome</h4>
								</div>
							</div>
<div class="row hidden">
	<div class="col-lg-12" align="center">
		<ol class="breadcrumb">
	        <li class="active">Select Master<s:select 	list="#{'0':'All','Client':'Self','Third Party':'Third Party'}"></s:select></li>	</br></br>
			<li><a href="/APM/DischargeOutcome">All Discharge Outcome List</a></li>
			<li class="active">Add Discharge Outcome</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>
<s:form action="saveDischargeOutcome" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				<label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name" onkeyup="initialCap(this)"/>
					<label>Description</label><label class="text-danger">*</label>
				<s:textfield id="description" name="description"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Description" placeholder="Enter Description" onkeyup="initialCap(this)"/>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" />
			<s:reset cssClass="btn btn-primary" />
			<a href="DischargeOutcome" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
