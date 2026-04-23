<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="/APM/JobTitle">All Job Title List</a></li>
			<li class="active">Add Job Title</li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error"/></span>
	</div>
</div>

<s:form action="saveJobTitle" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				
			    <label>Select Job Group</label><label class="text-danger">*</label>
			    <s:select list="jobtitlegropulist" listKey="id" listValue="name" name="jobtitlegroup_id" cssClass="showToolTip form-control"></s:select>
			    <label>Job Title</label><label class="text-danger">*</label>
				<s:textfield id="jobTitle" name="jobTitle"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Job Title" placeholder="Enter Job Title" onkeyup="initialCap(this)"/>		
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
			<a href="backJobTitle" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>



