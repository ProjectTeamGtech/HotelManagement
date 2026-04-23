<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/vaidateWarehouse.js"></script>
<div class="row">
	<div class="col-lg-12" align="center">
		<ol class="breadcrumb">
			<li><a href="OtherTemplate">Update Terms Condition </a></li>

		</ol>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<script>
	bkLib.onDomLoaded(function() {

		//new nicEditor().panelInstance('declarationNotes');
		new nicEditor({
			maxHeight : 250
		}).panelInstance('template_text');
		$('.nicEdit-panelContain').parent().width('600px');
		$('.nicEdit-panelContain').parent().next().width('600px');

		$('.nicEdit-main').width('100%');
		// $('.nicEdit-main').height('480px');
	});
</script>
<s:form action="updateTermsConditionMaster" id="master_form"
	theme="simple" onsubmit="return validateInvSection()">
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-body">


					<label>Terms Condition Name</label><label class="text-danger">*</label>
					<s:textfield id="name" name="name"
						cssClass="showToolTip form-control" data-toggle="tooltip"
						title="Enter Terms Condition Name"
						placeholder="Enter Terms Condition" />

					<label>Condition Use For</label><label class="text-danger">*</label>
					<select name="workorderterms" class="form-control">
						<s:if test="workorderterms==1">
							<option value="0">Purchase Order</option>
							<option value="1" selected="selected">Work Order</option>
						</s:if>
						<s:else>
							<option value="0" selected="selected">Purchase Order</option>
							<option value="1" >Work Order</option>
						</s:else>
					</select>

					<s:hidden id="id" name="id" />
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
			<a href="TermsConditionMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>



<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script src="common/chosen_v1.1.0/docsupport/prism.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var config = {
		'.chosen-select' : {},
		'.chosen-select-deselect' : {
			allow_single_deselect : true
		},
		'.chosen-select-no-single' : {
			disable_search_threshold : 10
		},
		'.chosen-select-no-results' : {
			no_results_text : 'Oops, nothing found!'
		},
		'.chosen-select-width' : {
			width : "100%"
		}
	}
	for ( var selector in config) {
		$(selector).chosen(config[selector]);
	}
</script>
