<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Edit Reference</h4>
								</div>
							</div>
							

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>
<s:form action="updateReference" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
				<label>Source of Reference</label><label class="text-danger">*</label>
				<s:textfield id="reference" name="reference"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Source of Reference" placeholder="Enter Source of Reference" onkeyup="initialCap(this)"/>
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
			<a onclick="history.back(1);" class="btn btn-primary">Cancel</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>

