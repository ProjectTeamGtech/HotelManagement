<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="_assets/newtheme/css/main.css">
  <link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrap.min.css">

  <link rel="stylesheet" href="pharmacy/searchexport/dataTables.bootstrap.css">
  <link rel="stylesheet" href="pharmacy/searchexport/buttons.bootstrap.css">
		
  
  
  
  <link rel="stylesheet" href="emr/plugin/jquery.treeview.css" />

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
	<script src="emr/plugin/jquery.cookie.js"></script>
	<script src="emr/plugin/jquery.treeview.js"></script>

	<script type="text/javascript" src="emr/plugin/demo.js"></script>
	
	<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

  <style>
.tab-wizard .nav-tabs > li > a:before {
    top: 0px !important;
    right: -18px !important;
    z-index: 6 !important;
}
.tab-wizard .nav-tabs > li:before {
    background-color: rgba(22, 160, 133, 0.84);
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
ul, ol {
    list-style: none;
    padding: 0px;
}
.headset{
	font-size: 14px;
    color: brown;
    font-weight: bold;
    text-transform: uppercase;
}
label {
    font-weight: normal; 
}
.checkbox-custom-alt input:checked + i:before {
    background-color: aquamarine;
}
.checkbox-custom, .checkbox-custom-alt {
    padding-left: 27px;
}
.checkbox-custom-alt > i {
    margin-top: -2px;
    margin-right: 4px;
    margin-left: -26px;
}
.micimg{
	float: left;
    width: 7%;
}
.savebigbtn {
    width: 100%;
    height: 61px !important;
    font-size: 20px;
    background-color: #43b9be !important;
    margin-bottom: 15px;
    line-height: 40px;
}
.tab-wizard .tab-content .tab-pane {
    margin-top: 0px;
}
</style>


</head>

<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('otnotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('otnotes1');
	        	 $('.nicEdit-panelContain').parent().width('100%');
	        	 $('.nicEdit-panelContain').parent().next().width('100%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });

</script>
<body>

<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4><i class="fa fa-heartbeat" aria-hidden="true"></i> EMR</h4>
									</div>
								</div>
								<div class=" ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="border: 1px solid #ddd;padding: 0px;">
									 <!-- page content -->
                    <div class="">

                        <div id="rootwizard" class="tab-container tab-wizard">
                            <ul class="nav nav-tabs nav-justified">
                                <li><a href="#tab1" data-toggle="tab">Diagnosis (ICD-10, Problems, Treatment)<span class="badge badge-default pull-right wizard-step">1</span></a></li>
                                <li><a href="#tab2" data-toggle="tab">Assessment<span class="badge badge-default pull-right wizard-step">2</span></a></li>
                                <li><a href="#tab3" data-toggle="tab">Prescription<span class="badge badge-default pull-right wizard-step">3</span></a></li>
                                <li><a href="#tab4" data-toggle="tab">Investigation<span class="badge badge-default pull-right wizard-step">4</span></a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane" id="tab1">
                                    <form name="step1" role="form">
                                        <div class="row">
                                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
	                                            	<h4 class="headset">Step 1</h4>
	                                            	<article>
										               <input id="search" name="search" class="form-control" placeholder="Select Diagnosis" type="text" data-list=".default_list" autocomplete="off">
										               <div class="scrolltable">
										               	<ul class="vertical default_list" id="">
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all1"><i></i> All</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> CKD 5D</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> HYPERTENSION</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> DIABETES MELLITUS</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> HYPERNATREMIA Sodium Excess/Water Deficit  </label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> BENIGN PROSTATIC HYPERPLASIA (BPH)/OBSTRUCTIVE UROPATHY</label></li>
									                    </ul>
										               </div>
										             </article>
                                            	</div>
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
                                            		<h4 class="headset">Step 2</h4>
                                            		<article>
										               <input id="search1" name="search1" class="form-control" placeholder="Select Problems" type="text" data-list=".default_list" autocomplete="off">
										               <div class="scrolltable">
											             <ul class="vertical default_list" id="">
		                                            		<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all1"><i></i> All</label></li>
		                                            		<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> History of intermittent or sustained elevation of diastolic or systolic blood pressure;palpitations</label></li>
		                                            		<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Visual disturbances diplopia, blurred vision</label></li>
		                                            		<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Edema (may be generalized or dependent)</label></li>
		                                            		<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> History of intermittent or sustained elevation of diastolic or systolic blood pressure;palpitations</label></li>
		                                            		<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> Anxiety, depression, euphoria, or chronic anger</label></li>
		                                            	</ul>
										               </div>
										             </article>
                                            	</div>
                                            	<div id="main" class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
                                            		<h4 class="headset">Step 3</h4>
                                            		<div id="treecontrol">
														<a title="Collapse the entire tree below" href="#" style="color: grey;"><img src="emr/plugin/minus.gif" /> Collapse All</a>
														<a title="Expand the entire tree below" href="#" style="color: grey;"><img src="emr/plugin/plus.gif" /> Expand All</a>
													</div>
													 <div class="scrolltable">
													<ul id="black" class="treeview-black">
														<li>
															<span style="color: lightgray;">HYPERTENSION</span>
															<ul>
																<li>
																	<span style="color: rgb(160, 159, 159);">Edema (may be generalized or dependent)</span>
																	<ul>
																		<li>Diuretics, I/O monitoring, Weight monitoring</li>
																	</ul>
																</li>
																<li>
																	<span style="color: rgb(160, 159, 159);">Weakness, fatigue, sedentary lifestyle</span>
																	<ul>
																		<li>Regular exercises, Diet counselling, monitor calories intake</li>
																	</ul>
																</li>
															</ul>
														</li>
													</ul>
													</div>
												</div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane" id="tab2">
                                    <form name="step2" role="form">
										<div class="row">
                                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
	                                            	<h4 class="headset">Assessment</h4>
	                                            	<article>
										               <input id="search" name="search" class="form-control" placeholder="Select Form" type="text" data-list=".default_list" autocomplete="off">
										               <div class="scrolltable">
										               	<ul class="vertical default_list" id="">
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all1"><i></i> All</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> DISCHARGE SUMMARY</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> HD ROUND NOTE</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> PATIENT VISIT REPORT</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> IPD ROUND NOTE</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> 	INVESTIGATION CHART</label></li>
									                    </ul>
										               </div>
										             </article>
                                            	</div>
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
                                            		
                                            	</div>
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
                                            		
												</div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane" id="tab3">
                                    <form name="step3" role="form">
                                        <div class="row">
                                           <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
	                                            	<h4 class="headset">Prescription &nbsp;&nbsp; <a href="#"><i class="fa fa-plus-square" aria-hidden="true"></i></a></h4>
	                                            	<article>
										               <input id="search" name="search" class="form-control" placeholder="Select Prescription" type="text" data-list=".default_list" autocomplete="off">
										               <div class="scrolltable">
										               	<ul class="vertical default_list" id="">
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all1"><i></i> All</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> DISCHARGE SUMMARY</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> HD ROUND NOTE</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> PATIENT VISIT REPORT</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> IPD ROUND NOTE</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> 	INVESTIGATION CHART</label></li>
									                    </ul>
										               </div>
										             </article>
                                            	</div>
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
                                            		
                                            	</div>
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
                                            		
												</div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane" id="tab4">
                                    <form name="step4" role="form">
										<div class="row">
                                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
	                                            	<h4 class="headset">Investigation &nbsp;&nbsp; <a href="#"><i class="fa fa-plus-square" aria-hidden="true"></i></a></h4>
	                                            	<article>
										               <input id="search" name="search" class="form-control" placeholder="Select Investigation" type="text" data-list=".default_list" autocomplete="off">
										               <div class="scrolltable">
										               	<ul class="vertical default_list" id="">
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all1"><i></i> All</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> DISCHARGE SUMMARY</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> HD ROUND NOTE</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> PATIENT VISIT REPORT</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> IPD ROUND NOTE</label></li>
									                       <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox"><i></i> 	INVESTIGATION CHART</label></li>
									                    </ul>
										               </div>
										             </article>
                                            	</div>
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
                                            		
                                            	</div>
                                            	<div class="col-lg-4 col-md-4 col-xs-6 col-sm-6">
                                            		
												</div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /page content -->
                    
                    
									</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;margin-top: 5px;">
									<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9" style="padding: 0px;">
										<h4 class="headset" style="color: green;">PREVIEW &nbsp;&nbsp; <a href="#" data-toggle="modal" data-target="#commonvoiceover" style="color:#d9534f;"><i class="fa fa-microphone" aria-hidden="true"></i></a></h4>
										<textarea style="height: 250px;" rows="10" cols="8" id="otnotes" name="otnotes" cssClass="showToolTip form-control" ></textarea>
									</div>
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" style="margin-top: 31px;padding-right: 0px;">
										<a href="#" class="btn btn-primary savebigbtn">Save & Print</a>
									</div>
									
                        		</div>
                        		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right" style="padding: 0px;margin-top: 5px;">
									
                        		</div>
							</div>
							
							
							
							<!-- Voice Popup Modal -->
<div id="commonvoiceover" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Record Notes</h4>
      </div>
      <div class="modal-body">
        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
        	<s:form action="saveotnotesNotAvailableSlot" theme="simple">
			<s:hidden name="id" id="id"/>
	<div class="">
		
		
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									<div class="">
									<div class="col-lg-12 col-md-12">
										<div class="form-group">
											<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting1(this)" title="Microphone" id="changer"></img>
										
									  </div>
									</div>
									    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;"><!--
									     <textarea name="notes" cols="" rows="10" tabindex="119" class="form-control foemse" title=""></textarea>
									    -->
									    	<textarea style="height: 250px;" rows="10" cols="8" id="otnotes1" name="otnotes1"
											cssClass="showToolTip form-control" data-toggle="tooltip"
											title="Enter Other Template Text" placeholder="Enter Other Template Text" ></textarea>
									    </div>
									    
									   
									  </div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden-print" style="margin-top: 10px;">
									<div class="form-group">
									    <input type="button" class="btn btn-primary hidden" value="Print">
									   <input type="button" onclick="setVoiceoverText()" class="btn btn-primary" value="Save">
									  </div>
								</div>
		
		</div>



								
								
								
								
								


</s:form>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary hidden">Save</button>
      </div>
    </div>

  </div>
</div>
							
							
							
							
					    <!-- JS -->
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.hideseek.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/rainbow-custom.min.js"></script>
  <script type="text/javascript" src="inventory/js/searchtext/javascripts/vendor/jquery.anchor.js"></script>
  <script src="inventory/js/searchtext/javascripts/initializers.js"></script>
  <!-- JS ends -->
					
				<script type="text/javascript" src="common/slimScroll/jquery.slimscroll.min.js"></script>	
					<script>
    	$(function() {
	    $('.scrolltable').slimScroll({
	   		height : '200px',
	   		railVisible: true,
			alwaysVisible: true
	  });
	
	 });
    </script>		
    
    <script type="text/javascript">
		var r = document.getElementById('result');
</script>




</body>
</html>