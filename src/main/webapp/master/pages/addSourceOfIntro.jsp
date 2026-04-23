<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="master/js/sourceofintro.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>


<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add SourceOfIntro</h4>
								</div>
							</div>

<s:form action="saveSourceOfIntro" id="sourceOfIntro_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				<label>SourceOfIntro</label><label class="text-danger">*</label>
				<s:textfield id="sourceName" name="sourceName"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter SourceOfIntro" placeholder="Enter SourceOfIntro" onkeyup="initialCap(this)"/>
					
			</div>
			<div class="panel-body">
				<label>Description</label><label class="text-danger">*</label>
				<s:textarea id="description" name="description"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter description" placeholder="Enter description" />
					
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
			<a href="backSourceOfIntro" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
