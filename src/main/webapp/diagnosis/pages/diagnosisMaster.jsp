<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<link rel="icon" href="ipd/img/favicon.ico">
<title>Advance Practice Management</title>


<script type="text/javascript" src="diagnosis/js/diagnosis.js"></script>
<!-- Bootstrap core CSS -->


	

<style>
.panel {
    margin-bottom: 0px;}
    .col-md-offset-3 {
    margin-left: 2%;
}
.martop10{
 margin-top:10px;
}
</style>

</head>

<body>
	<section id="container">
		<!-- ***************************************a*******************************************************************************************************************
     MAIN CONTENT
     *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section >
			<section>

				<!-- page start-->
				<div class="">
					<aside>
						<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Diagnosis Master</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">


										<div class="container martop10">


											<div class="stepwizard col-md-3">
												<div class="stepwizard-row setup-panel">
													<div class="stepwizard-step">
														<a href="#step-1" type="button"
															class="btn btn-primary btn-circle">1</a>
														<p>Add Diagnosis Name</p>
													</div>
													<div class="stepwizard-step">
														<a href="#step-2" type="button"
															class="btn btn-default btn-circle" disabled="disabled">2</a>
														<p>Add Diagnosis Problem</p>
													</div>
													<div class="stepwizard-step">
														<a href="#step-3" type="button"
															class="btn btn-default btn-circle" disabled="disabled">3</a>
														<p>Add Diagnosis Intervention</p>
													</div>
													
												</div>
											</div>

											<s:form action="listdiagnosisDiagnosis" id="diagnosisForm" theme="simple">
												<div class="row setup-content" id="step-1">
													<div class="col-xs-6 col-md-offset-3">
														<div class="col-md-12">
															<h3>Step 1</h3>
															<div class="form-group">
																<label class="control-label">Existing Diagnosis</label> 
																 <s:select list="diagnosislist" name="diagnosislist" id="diagnosisname" cssClass="form-control showToolTip chosen-select"  class="form-control" listKey="id" listValue="name">
																</s:select>
																
										                        
															</div>
															<div class="form-group">
																<label class="control-label">Add New Diagnosis</label> <input id="name" name="name"
																	maxlength="100" type="text" 
																	class="form-control" placeholder="Enter New Diagnosis Name" />
															</div>


															<button class="btn btn-primary nextBtn btn-lg pull-right"
																type="button" onclick="saveDiagnosisandContinue()">Save & Next</button>
														</div>
													</div>
												</div>
												<div class="row setup-content" id="step-2">
													<div class="col-xs-6 col-md-offset-3">
														<div class="col-md-12">
															<h3>Step 2</h3>
															<div class="form-group" id="step2">
																<label class="control-label">Select Existing
																	Diagnosis Name</label> <select class="form-control chosen-select" required="required">
																	<option>All</option>
																</select>
															</div>
															<div class="form-group" id="step3">
																<label class="control-label">Select Existing
																	Diagnosis Problem</label> <select class="form-control chosen-select"
																	required="required">
																	<option>All</option>
																</select>
															</div>
															<div class="form-group">
																<label class="control-label">Add New Diagnosis Problem</label> <input
																	maxlength="200" type="text"  id="problem_name" name="problem_name"
																	class="form-control" placeholder="Enter Diagnosis Problem" />
															</div>
															<button class="btn btn-primary nextBtn btn-lg pull-right"
																type="button" onclick="saveProblemandContinue()" >Save & Next</button>
														</div>
													</div>
												</div>
												<div class="row setup-content" id="step-3">
													<div class="col-xs-6 col-md-offset-3">
														<div class="col-md-12">
															<h3>Step 3</h3>
															<div class="form-group" id="stepp2">
																<label class="control-label">Select Existing
																	Diagnosis Name</label> <select class="form-control chosen-select" required="required">
																	<option>All</option>
																	<option>Genral Ward I</option>
																	<option>Genral Ward II</option>
																	<option>Genral Ward III</option>
																	<option>Private Ward</option>
																	<option>ICU Ward</option>
																</select>
															</div>
															<div class="form-group" id="stepp3">
																<label class="control-label">Select Existing
																	Diagnosis Problem</label> <select class="form-control chosen-select"
																	required="required">
																	<option>All</option>
																	<option>Room 01</option>
																	<option>Room 02</option>
																	<option>Room 03</option>
																	<option>Room 04</option>
																	<option>Room 05</option>
																</select>
															</div>
															<div class="form-group">
																<label class="control-label">Add Intervention Name</label> <input
																	maxlength="200" type="text" name="intervention" id="intervention"
																	class="form-control" placeholder="Enter Intervention Name" />
															</div>
															<a href="listdiagnosisDiagnosis" class="btn btn-primary nextBtn btn-lg pull-right" onclick="saveintervention()"
																type="button">Submit</a>
														</div>
													</div>
												</div>
											</s:form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</aside>
				</div>
				<!-- page end-->
			</section>
		</section>
		<!-- /MAIN CONTENT -->
		<!--main content end-->

	</section>
	<!-- js placed at the end of the document so the pages load faster -->
	<%-- <script src="ipd/js/priscription/jquery-2.1.1.min.js"></script>

	<script src="ipd/js/bootstrap.min.js"></script>

	<script src="ipd/js/priscription/bootstrap-select.js"></script> --%>

	<!--script for this page-->

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


	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var navListItems = $('div.setup-panel div a'), allWells = $('.setup-content'), allNextBtn = $('.nextBtn');

							allWells.hide();

							navListItems
									.click(function(e) {
										e.preventDefault();
										var $target = $($(this).attr('href')), $item = $(this);

										if (!$item.hasClass('disabled')) {
											navListItems.removeClass(
													'btn-primary').addClass(
													'btn-default');
											$item.addClass('btn-primary');
											allWells.hide();
											$target.show();
											$target.find('input:eq(0)').focus();
										}
									});
							allNextBtn
									.click(function() {
										var curStep = $(this).closest(
												".setup-content"), curStepBtn = curStep
												.attr("id"), nextStepWizard = $(
												'div.setup-panel div a[href="#'
														+ curStepBtn + '"]')
												.parent().next().children("a"), curInputs = curStep
												.find("input[type='text'],input[type='url']"), isValid = true;

										$(".form-group").removeClass(
												"has-error");
										for (var i = 0; i < curInputs.length; i++) {
											if (!curInputs[i].validity.valid) {
												isValid = false;
												$(curInputs[i]).closest(
														".form-group")
														.addClass("has-error");
											}
										}

										if (isValid)
											nextStepWizard.removeAttr(
													'disabled')
													.trigger('click');
									});

							$('div.setup-panel div a.btn-primary').trigger(
									'click');
						});
	</script>

</body>
</html>
