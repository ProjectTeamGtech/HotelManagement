<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<meta name="description" content="">
<meta name="author" content="Dashboard">


<link rel="icon" href="ipd/img/favicon.ico">
<title>YUVICARE</title>

<!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="_assets/newtheme/css/main.css">
  <link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="ipd/js/ipdbedmaster.js"></script>

<style>
.tab-wizard .nav-tabs > li > a:before {
    top: 0px !important;
    right: -18px !important;
    z-index: 6 !important;
}
.panel {
    margin-bottom: 0px;}
    .col-md-offset-3 {
    margin-left: 2%;
}
.martop10{
margin-top:10px;
}
.tab-wizard .tab-content .pager.wizard {
    margin: 17px 0 !important;
}

</style>

</head>
<body>
<div class="col-lg-3 col-md-3"></div>
<div class="col-lg-6 col-md-6 col-xs-12">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Bed Master</h4>
									</div>
								</div>
								<div class=" ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 1px solid #ddd;padding: 0px;">
									 <!-- page content -->
                    <div class="">

                        <div id="rootwizard" class="tab-container tab-wizard">
                            <ul class="nav nav-tabs nav-justified">
                                <li><a href="#tab1" data-toggle="tab">Add Ward <span class="badge badge-default pull-right wizard-step">1</span></a></li>
                                <li><a href="#tab2" data-toggle="tab">Add Section<span class="badge badge-default pull-right wizard-step">2</span></a></li>
                                <li><a href="#tab3" data-toggle="tab">Add Bed<span class="badge badge-default pull-right wizard-step">3</span></a></li>
                                <li><a href="#tab4" data-toggle="tab">Add Equipments<span class="badge badge-default pull-right wizard-step">4</span></a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane" id="tab1">

                                    <form name="step1" role="form">

                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label for="username">Existing Ward: </label>
                                                <s:select list="wardlist" name="wardlist"  cssClass="form-control showToolTip chosen"  class="form-control" listKey="id" listValue="wardname"></s:select>
                                            </div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">

                                           

                                            <div class="form-group col-md-6">
                                                <label for="username">Add New Ward: </label>
                                               <input id="newward" name="wardname" maxlength="100" type="text" class="form-control" placeholder="Enter New Ward Name" />
                                            </div>
                                            <div class="form-group col-md-6"></div>
                                           
                                        </div>
                                        <div class="row">
                                           <div class="form-group col-md-6">
                                              <label for="username">Procedure: </label>
                                                Plus <input type="radio" name="proc" value="1" />   Minus <input type="radio" name="proc"  value="2"/>
                                                
                                                <input type="number" id="procedure" name="procedure" class="form-control"  placeholder="Enter Procedure" />
                                                    
                                            </div>
                                           <div class="form-group col-md-6"></div>
                                        </div>
                                        
                                        <div class="row">


                                            <div class="form-group col-md-6">
                                               <button class="btn btn-primary nextBtn pull-right" type="button"  onclick="saveWardandContinue()">Save & Next</button>
                                            </div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane" id="tab2">
                                    <form name="step2" role="form">
										<div class="row">
                                           

                                            <div class="form-group col-md-6" id="step2">
																<label class="control-label">Select Existing
																	Ward</label> <s:select list="wardlist" name="wardlist"  cssClass="form-control"  class="form-control" listKey="id" listValue="wardname">
																	</s:select>
															</div>
                                           <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">

                                            <div class="form-group col-md-6">
                                                <label for="username">Add New Section: </label>
                                               <input maxlength="200" type="text"  id="newsection" class="form-control" placeholder="Enter Section Name" />
                                            </div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                               <button class="btn btn-primary nextBtn pull-right" type="button" onclick="saveSectionandContinue()" >Save & Next</button>
                                            </div>
                                           <div class="form-group col-md-6"></div>
                                        </div>
                                    </form>

                                </div>
                                <div class="tab-pane" id="tab3">

                                    <form name="step3" role="form" novalidate>

                                        <div class="row">
                                            

                                            <div class="form-group col-md-6" id="stepp2">
																<label class="control-label">Select Existing
																	Ward</label> 
																	<s:select list="wardlist" name="wardlist"  cssClass="form-control" listKey="id" listValue="wardname" id="fward">
																	</s:select>
															</div>
                                           <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">

                                          

                                            <div class="form-group col-md-6" id="step3">
																<label class="control-label">Select Existing
																	Section</label> 
																	<s:select list="sectionlist" name="sectionlist"  cssClass="form-control" listKey="id" id="fsection" listValue="sectionname">
																	</s:select>
															</div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">

                                            <div class="form-group col-md-6">
																<label class="control-label">Add Bed Name</label> <input
																	maxlength="200" type="text" name="newbed" id="bedname"
																	class="form-control" placeholder="Enter Bed Name" />
															</div>
                                           <div class="form-group col-md-6"></div>
                                        </div>
                                        
                                        
                                         <div class="row">

                                            <div class="form-group col-md-6">
																<label class="control-label">Type</label> <br>
															
																<s:select list="#{'0':'IPD','1':'Casulty','2':'Daycare'}" name='casualty' id='casualty' cssClass="form-control" ></s:select>
															</div>
                                           <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">

                                         
                                            <div class="form-group col-md-6">
                                               <button class="btn btn-primary nextBtn pull-right" onclick="saveBedandContinue()" type="button">Save & Next</button>
                                            </div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                    </form>

                                </div>
                                <div class="tab-pane" id="tab4">

                                    <form name="step4" role="form" novalidate>
<div class="row">
                                            

                                            <div class="form-group col-md-6" id="newwardlist">
																<label class="control-label">Select Existing
																	Ward</label> 
																	<s:select list="wardlist" name="wardlist"  cssClass="form-control" listKey="id" listValue="wardname" id="fward">
																	</s:select>
																</select>
															</div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">

                                            <div class="form-group col-md-6" id="newsectionlist">
																<label class="control-label">Select Existing
																	Section</label>
																	<s:select list="sectionlist" name="sectionlist"  cssClass="form-control" listKey="id" listValue="sectionname" id="fsection">
																	</s:select>
															</div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">

                                            <div class="form-group col-md-6" id="step4">
																<label class="control-label">Select Existing Bed</label>
																<s:select list="bedlist" name="bedlist"  cssClass="form-control" listKey="id" listValue="bedname" id="fbed">
																	</s:select>
															</div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">

                                            <div class="form-group col-md-6">
																<label class="control-label">Add New Equipment
																	Name</label> <input maxlength="200" type="text" id="equipmentname"
																	required="required" class="form-control"
																	placeholder="Enter Equipment Name" />
															</div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                        <div class="row">

                                            <div class="form-group col-md-6">
                                               <a href="redirectbookbedBed"
																class="btn btn-success pull-right" type="submit" onclick="saveEquipmentandSubmit()" >Save
																& Submit</a>
                                            </div>
                                            <div class="form-group col-md-6"></div>
                                        </div>
                                        

                                    </form>

                                </div>
                                
                                
                            </div>
                        </div>

                    </div>
                    <!-- /page content -->
									</div>
								</div>
							</div>
						</div>
<div class="col-lg-3 col-md-3"></div>


	<!-- Booked BedModal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal"
						onclick="movetosetCommonAction()">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Day-To-Day Admin</h4>
				</div>
				<div class="modal-body">
					<div id="modifyClient1"
						style="font-size: 16px; color: rgb(61, 61, 61); margin-bottom: 6px;">
						<a href="#" onclick="openClientPrintPopup(41)"> praful G</a> <a
							href="#" title="Edit Client Record"
							onclick="openEditClientPrintPopup(41)"><img
							src="ipd/img/edit.png"></a> <a href="#" title="Log"
							onclick="openClientLogPopup(41)"><img
							src="ipd/img/log.png"></a> <a href="#" title="EMR"
							onclick="redircttoNewEmr(41,12,5)"><img
							src="ipd/img/emr.png"></a> <a href="#"
							title="Assessment Form"
							onclick="openAssesmentFormPopup(41,12,501)"><img
							src="ipd/img/asmnt.png"></a>
					</div>



					<div class="row manaboxwi">
						<div class="col-lg-4">
							<div
								class="col-lg-2 col-md-2 col-sm-12 col-xs-12 images_1_of_3 bedspace bookbed popupbed">
								<div class=" listimg_1_of_2">
									<img src="ipd/img/bed_new.png" class="bedic" />
									<h2 class="bedno">005</h2>
								</div>
								<div class="text">
									<div class="row">
										<div class="col-lg-3 col-md-3 col-xs-12">
											<img src="ipd/img/male.png" class="male" />
										</div>
										<div
											class="col-lg-9 col-md-9 col-xs-12 martop10icon cleintname">
											Mr.Suraj Singh</div>
									</div>

									<div class="row">
										<div class="col-lg-6 col-md-6 col-xs-12">
											<div class="col-lg-9 col-md-9 col-xs-12 ageleft">
												<img src="ipd/img/age.png" class="setcino" />
											</div>
											<div
												class="col-lg-3 col-md-3 col-xs-12 martop10icon calentext">
												54</div>
										</div>
										<div class="col-lg-6 col-md-6 col-xs-12">
											<div class="col-lg-9 col-md-9 col-xs-12 dateleft">
												<img src="ipd/img/addate.png" class="setcino calen" />
											</div>
											<div
												class="col-lg-3 col-md-3 col-xs-12 martop10icon calentext">
												31/10/205</div>
										</div>


									</div>
									<div class="row">
										<div class="col-lg-3 col-md-3 col-xs-12">
											<img src="ipd/img/address.png" class="setcino" />
										</div>
										<div class="col-lg-9 col-md-9 col-xs-12 martop10icon">
											Yawatmal</div>
									</div>
									<div class="row">
										<div class="col-lg-3 col-md-3 col-xs-12">
											<img src="ipd/img/doctor.png" class="setcino" />
										</div>
										<div class="col-lg-9 col-md-9 col-xs-12 martop10icon">
											Dr.Dhananjay</div>
									</div>
									<div class="row martop10only">
										<div class="col-lg-8 col-md-8 col-xs-8">Addmisson ID:</div>
										<div class="col-lg-4 col-md-4 col-xs-4 patintid">5232</div>
									</div>
									<div class="row fontad">
										<div class="col-lg-8 col-md-8 col-xs-8">Patient ID:</div>
										<div class="col-lg-4 col-md-4 col-xs-4 patintid">
											1211243</div>
									</div>
									<div class="row fontad">
										<div class="col-lg-8 col-md-8 col-xs-8">Patient Code:</div>
										<div class="col-lg-4 col-md-4 col-xs-4 patintid">
											2014/07/843</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-8">
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
								<a href="admissionform.html">
									<div class="thumbnail">
										<img src="ipd/img/modify.png" alt="..."
											style="width: 64px; height: 64px;">
										<div class="caption">
											<p align="center" class="fontpup">Edit</p>
										</div>
									</div>
								</a>

							</div>

							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
								<a href="discharge.html">
									<div class="thumbnail">
										<img src="ipd/img/modify.png" alt="..."
											style="width: 64px; height: 64px;">
										<div class="caption">
											<p align="center" class="fontpup">Discharge</p>
										</div>
									</div>
								</a>
							</div>

							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
								<a href="priscription.html">
									<div class="thumbnail">
										<img src="ipd/img/modify.png" alt="..."
											style="width: 64px; height: 64px;">
										<div class="caption">
											<p align="center" class="fontpup">Create Prescription</p>
										</div>
									</div>
								</a>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
								<a href="#">
									<div class="thumbnail">
										<img src="ipd/img/modify.png" alt="..."
											style="width: 64px; height: 64px;">
										<div class="caption">
											<p align="center" class="fontpup">Consultation Note</p>
										</div>
									</div>
								</a>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
								<a href="#">
									<div class="thumbnail">
										<img src="ipd/img/modify.png" alt="..."
											style="width: 64px; height: 64px;">
										<div class="caption">
											<p align="center" class="fontpup">Add Charges</p>
										</div>
									</div>
								</a>
							</div>

						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="movetosetCommonAction()">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- js placed at the end of the document so the pages load faster -->
	<%-- <script src="ipd/js/priscription/jquery-2.1.1.min.js"></script>

	<script src="ipd/js/bootstrap.min.js"></script>

	<script src="ipd/js/priscription/bootstrap-select.js"></script> --%>

	<!--script for this page-->

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
