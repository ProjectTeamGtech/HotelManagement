<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="inventory/js/addvendor.js"></script>
<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>
<title>Insert title here</title>
<%LoginInfo login = LoginHelper.getLoginInfo(request); %>
<SCRIPT type="text/javascript">

     window.onload= function(){
    	var typeid=document.getElementById("puredob").value;
    	getAgendDays2(typeid);
    	
     }
	
     
 </script>
  <SCRIPT type="text/javascript" >
       $(document).ready(function() {
		var puretoday = new Date();
		 $( "#puredob" ).datepicker({
				 
				 dateFormat:'dd/mm/yy',
				 minDate : '30/12/1880',
				 yearRange: yearrange,
				 maxDate: puretoday,
				 changeMonth: true,
			     changeYear: true
				 
					 
			 });
		   
	});
       </script>
 <script src="common/assets/js/jquery.fileupload.js"></script>


<script src="common/assets/js/jquery.knob.js"></script> 
<script src="common/assets/js/script.js"></script> 
   
	<script src="emr/plugin/jquery.cookie.js"></script>
	<script src="emr/plugin/jquery.treeview.js"></script>

   <style>

.btn:focus, .btn:active, button:focus, button:active {
  outline: none !important;
  box-shadow: none !important;
}

#image-gallery .modal-footer{
  display: block;
}

.thumb{
  margin-top: 15px;
  margin-bottom: 15px;
}

  </style> 

</head>
<body>
 <div class="col-lg-1 col-md-1"></div>
 		<input type="hidden" id="whopay">
		<div class="col-md-10 col-lg-10 col-xs-12 col-sm-12">
			<div class="">
			
				<div class="row details">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<h4>Update Patient Details / <s:property value = "title" /> <s:property value="firstName"/> <s:property value="lastName"/></h4>
					</div>
				</div>
				
				<div class="">
				<s:form id="upload" method="post" action="upopddocEmr"
							enctype="multipart/form-data" theme="simple">
					<div class="row">
						
						<s:hidden name="id" id="updteclientid"></s:hidden>
						
						
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 mt-10" style="padding-bottom: 10px;">	
						<div class="col-lg-3 col-md-12 col-xs-12 col-sm-12">
						</div>
						<div class="col-lg-3 col-md-6 col-xs-12 col-sm-12">
							<label>Document Type:</label>
							<%if(!login.isClientprof()){ %>
							<s:select cssClass="form-control" list="#{'Adhaar':'Adhaar', 'Pan Card':'Pan Card', 'Driving License':'Driving License'}" id="docType" name="docType" />
							<%}else{ %>
							<!-- <input type="text" name="docType" id="docType" readonly="readonly"> -->
							<s:select cssClass="form-control" list="#{'Adhaar':'Adhaar', 'Pan Card':'Pan Card', 'Driving License':'Driving License'}" id="docType" name="docType" disabled="true"/>
							<%} %>
						</div>
						
						<s:if test="docImg==null">
						</s:if>
						<s:else>
							<div class="col-lg-3 col-md-6 col-xs-12 col-sm-12">
									<label>Uploaded Patient's Identity Document</label>
									<a class="thumbnail" href="#" data-image-id="" data-toggle="modal" data-title=""
		                   			data-image="liveData/document/<s:property value="docImg"/>"
		                  			data-target="#image-gallery">
		                    		<img class="img-thumbnail" style="height: 50px; width: 50px"
		                         		src="liveData/document/<s:property value="docImg"/>"
		                         		alt="Another alt text">
		               				</a>
							</div>	
						</s:else>
						
						<s:if test="profileImg==null">
						</s:if>
						<s:else>
							<div class="col-lg-3 col-md-6 col-xs-12 col-sm-12">
								<label>Uploaded Patient's Profile Photo</label>
								<a class="thumbnail" href="#" data-image-id="" data-toggle="modal" data-title=""
	                  			data-image="liveData/document/<s:property value="profileImg"/>"
	                 			data-target="#image-gallery">
	                   			<img class="img-thumbnail" style="height: 50px; width: 50px"
	                        		src="liveData/document/<s:property value="profileImg"/>"
	                        		alt="">
	              				</a>
              				</div>
						</s:else>
						
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-bottom: 10px;">	
						<div class="col-lg-1 col-md-1 col-xs-3 col-sm-1">
						</div>
						<input type="hidden" id='docimg'>
						<s:hidden name='hiddenval' id='profileimg'></s:hidden>
						<%if(!login.isClientprof()){ %>
						<div class="col-lg-5 col-md-5 col-xs-12 col-sm-5">
							
							<label>Patient's Identity Document</label>
								<div id="drop" style="background-color: #efefef;">
									Document  <a>Upload</a>
									<s:file name="fileUpload" theme="simple"  accept="image/*" required="true" onchange="getf(this.value,'docimg')">
									</s:file>
								</div>
								<span id="docimg1"></span>
							
						</div>
						
						<div class="col-lg-5 col-md-5 col-xs-12 col-sm-5">
						
						<label>Patient's Profile Photo</label>
							<span id="filename" style="color: white"></span>
							<div id="drop" style="background-color: #efefef;">
								Photo <a>Upload</a>
								<s:file name="files" theme="simple" id='' accept="image/*" required="true" onchange="getf(this.value,'profileimg')">
								</s:file>
							</div>
							<span id="profileimg1"></span>
							
						</div>
						<%} %>
						<div class="col-lg-1 col-md-1 col-xs-3 col-sm-1">
						</div>
						
						
						
						
						<!-- <div class="col-lg-3">
							<ul class="popmodals123">
								The file uploads will be shown here
							</ul>
						</div> -->
						
						
						</div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 20px;background-color: lavender;">
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
							<h5><b>Details of Patient's Parent / Guardian:</b></h5> 
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-4">
								<label>Patient's Parent / Guardian Name:<span class="text-danger" id="parentguardiannamespan">*</span></label> 
							<%if(!login.isClientprof()){ %>
								<s:textfield id="relativename" name="relativename"   cssClass="form-control showToolTip"></s:textfield>
							<%}else{ %>
								<s:textfield id="relativename" name="relativename"   cssClass="form-control showToolTip" readonly="true"></s:textfield>
							<%} %>
							</div>
							
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-4">
								<label>Parent / Guardian Contact Number:<span class="text-danger" id="parentguardiancontactspan">*</span></label>
							<%if(!login.isClientprof()){ %>	
								<s:textfield id="relativecontact" name="relativeno"   cssClass="form-control showToolTip" ></s:textfield>
							<%}else{ %>
							<s:textfield id="relativecontact" name="relativeno"   cssClass="form-control showToolTip" readonly="true"></s:textfield>
							<%} %>
							</div>
							
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-4">
								<label>Relationship with Patient's:<span class="text-danger" id="parentguardianrelationspan">*</span></label>
								<%if(!login.isClientprof()){ %>
								<s:select headerKey="" id='relativerelation' headerValue="Select Relation" cssClass="form-control" 
								list="#{'Mother':'Mother', 'Father':'Father' ,'Son':'Son', 'Daughter':'Daughter', 'Brother':'Brother', 'Sister':'Sister', 'Uncle':'Uncle','Aunt':'Aunt','Cousin':'Cousin','Husband':'Husband','Wife':'Wife','Nephew':'Nephew','Niece':'Niece','Grandson':'Grandson','Grand daughter':'Grand daughter','Grand Father':'Grand Father','Grand Mother':'Grand Mother','Other':'Other'}" 
								name="relation" />
								<%}else{ %>
								<s:select headerKey="" id='relativerelation' headerValue="Select Relation" cssClass="form-control" 
								list="#{'Mother':'Mother', 'Father':'Father' ,'Son':'Son', 'Daughter':'Daughter', 'Brother':'Brother', 'Sister':'Sister', 'Uncle':'Uncle','Aunt':'Aunt','Cousin':'Cousin','Husband':'Husband','Wife':'Wife','Nephew':'Nephew','Niece':'Niece','Grandson':'Grandson','Grand daughter':'Grand daughter','Grand Father':'Grand Father','Grand Mother':'Grand Mother','Other':'Other'}" 
								name="relation" disabled="true"/>
								<%} %>
							</div>
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style=" padding:10px; padding-top: 20px;background-color: lavender;">
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 hidden-xs hidden-sm">
							
							</div>
							<s:if test="relativeImg==null">
							</s:if>
							<s:else>
								<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
									<label>Uploaded Parent / Guardian Identity Document</label>
									<a class="thumbnail" href="#" data-image-id="" data-toggle="modal" data-title=""
		                   			data-image="liveData/document/<s:property value="relativeImg"/>"
		                  			data-target="#image-gallery">
		                    		<img class="img-thumbnail" style="height: 50px; width: 50px"
		                         		src="liveData/document/<s:property value="relativeImg"/>"
		                         		alt="">
		               				</a>
								</div>
							</s:else>
							
							
							<%if(!login.isClientprof()){ %>
							<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
								<input type="hidden" id='relativedocimg'>
								<label>Parent / Guardian Identity Document:</label><label><span class="text-danger" id="relativeidentiyspan">*</span></label>
								<div id="drop" style="background-color: #efefef;">
									Document  <a>Upload</a>
									<s:file name="relativefiles" theme="simple"  accept="image/*" required="true" onchange="getf(this.value,'relativedocimg')">
									</s:file>
								</div>
								<span id="relativedocimg1"></span>
							</div>
							<%} %>
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
							</div>
						</div>
						
						
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 mt-10">
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
								<label>Email ID:</label>
							</div>
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
								<s:textfield id="pureemail" name="email" cssClass="form-control showToolTip"></s:textfield>
							</div>
						</div>
						<br>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 mt-10">
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
								<label>Salutation:</label>
							</div>
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
								<s:select id="title123" name="title"  list="initialList" title="Select" theme="simple" cssClass="form-control" headerValue="Select" headerKey="0" cssStyle="width:100%;"/>
								<%-- <s:select disabled="true" id="title123" name="title"  list="initialList" title="Select" theme="simple" cssClass="form-control" headerValue="Select" headerKey="0" cssStyle="width:100%;"/> --%>
							</div>
						</div><br>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 mt-10">
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
								<label>Patient's First / Given Name:<span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
							<%if(!login.isClientprof()){ %>	
								<s:textfield id="purefname" name="firstName" cssClass="form-control showToolTip" ></s:textfield>
							<%}else{ %>
							<s:textfield id="purefname" name="firstName" cssClass="form-control showToolTip" readonly="true"></s:textfield>
							<%} %>
							</div>
						</div>
						 
						<br>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 mt-10">
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
								<label>Patient's Last / Family Name:<span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
							<%if(!login.isClientprof()){ %>
								<s:textfield id="purelname" name="lastName" cssClass="form-control showToolTip" ></s:textfield>
							<%}else{ %>
							<s:textfield id="purelname" name="lastName" cssClass="form-control showToolTip" readonly="true"></s:textfield>
							<%} %>
							</div>
						</div><br>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 mt-10">
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
								<label>Gender</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
							<%if(!login.isClientprof()){ %>
								<s:select id="gender123" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip "
														data-toggle="tooltip" title="Select Gender"  headerKey="0" headerValue="Select"/>
							<%}else{ %>
							<s:select id="gender123" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip "
														data-toggle="tooltip" title="Select Gender"  headerKey="0" headerValue="Select" disabled="true"/>
							<%} %>
							</div>
						</div>
							
						<br>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 mt-10">
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
								<label>Patient's Mobile Number:</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
								<s:textfield id="puremob"  name="mobNo" cssClass="form-control showToolTip" onchange="checkexistingmobno()"></s:textfield>
							</div>
						</div>
						<br>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 mt-10">
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-3">
								<label>Patient's Date of Birth:</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-3">
								<s:textfield id="puredob" name="dob" readonly="true"  cssClass="form-control showToolTip" onchange="getAgendDays2(this.value)"></s:textfield>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-1">
								<label>Age:</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-12 col-sm-1">
								<input type="text" id="pureage" name="pureage"
									class="form-control showToolTip"
									data-toggle="tooltip" onchange="allnumeric5(this.value)"  readonly="readonly"/>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-4" id='dobe'>
							</div>
						</div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
								<label>Patient's Address:</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
								<s:textfield id="address"  name="address" cssClass="form-control showToolTip" ></s:textfield>
							</div>
						</div>
						
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 mt-10" style="padding-top: 10px;">
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 hidden-sm hidden-xs">
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-4">
								<label>Country</label><label><span class="text-danger">*</span></label>
								<s:select list="#{'India' : 'India'} " cssClass='form-control chosen-select' ></s:select>
							</div>
							
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-4">
								<label>State:</label><label><span class="text-danger">*</span></label>
								<span id='statediv2'>
									<s:select list="statelist" listKey="name" name="county" listValue="name" cssClass='form-control chosen-select' id='state' onchange="getCitiesajax2(this.value)"></s:select>
								</span>
							</div>
							
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-4">
								<label>City / District:</label><label><span class="text-danger">*</span></label>
								<span id='citydiv2'>
									<s:select list="citylist" listKey="city" listValue="city" cssClass='form-control chosen-select' name="town" id='city' onchange="getStateAjaxnew2(this.value)"></s:select>
								</span>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 hidden-xs hidden-sm">
							</div>
							
							
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-6">
								<label>Village / Town:</label><label><span class="text-danger">*</span></label>
								<s:textfield id="town_village"  name="town_village" cssClass="form-control showToolTip" ></s:textfield>
							</div>
							
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-6">
								<label>Postal Code (Zip / Pin):</label>
								<s:textfield id="pincode"  name="pincode" maxlength="6" cssClass="form-control showToolTip" ></s:textfield>
							</div>
						</div>
						
						
						
						
						
						
						
						<input type="hidden" id="puresevadobyear">
						<input type="hidden" id="puresevadobmonth">
						<input type="hidden" id="puresevadobdays">
						
						<div class="modal-footer" style="text-align: center !important;padding-top: 10px ">
						<button type="button" class="btn btn-primary mt-10"
							onclick="confirmupdatepuresevapatient()">Submit</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
						</div>
					</div>
					</s:form>
				</div>
			</div>
		</div>
		
		  <div class="modal fade" id="image-gallery" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content" style="height: 600px;">
                    <div class="modal-header">
                        <%-- <h4 class="modal-title" id="image-gallery-title">Preview</h4>
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span>
                        </button> --%>
                        <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
						</button>
                    	<h4 class="modal-title hidden" id="image-gallery-title">Preview</h4>
                    	<h4 class="modal-title" >Preview</h4>
                    </div>
                    <div class="modal-body" >
                        <img style="height: 530px;" id="image-gallery-image" class="img-responsive col-md-12" src="">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary float-left" id="show-previous-image"><i class="fa fa-arrow-left"></i>
                        </button>

                        <button type="button" id="show-next-image" class="btn btn-secondary float-right"><i class="fa fa-arrow-right"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>   
    
		
		<script type="text/javascript">
	let modalId = $('#image-gallery');

	$(document)
	  .ready(function () {

	    loadGallery(true, 'a.thumbnail');

	    //This function disables buttons when needed
	    function disableButtons(counter_max, counter_current) {
	      $('#show-previous-image, #show-next-image')
	        .show();
	      if (counter_max === counter_current) {
	        $('#show-next-image')
	          .hide();
	      } else if (counter_current === 1) {
	        $('#show-previous-image')
	          .hide();
	      }
	    }

	    /**
	     *
	     * @param setIDs        Sets IDs when DOM is loaded. If using a PHP counter, set to false.
	     * @param setClickAttr  Sets the attribute for the click handler.
	     */

	    function loadGallery(setIDs, setClickAttr) {
	      let current_image,
	        selector,
	        counter = 0;

	      $('#show-next-image, #show-previous-image')
	        .click(function () {
	          if ($(this)
	            .attr('id') === 'show-previous-image') {
	            current_image--;
	          } else {
	            current_image++;
	          }

	          selector = $('[data-image-id="' + current_image + '"]');
	          updateGallery(selector);
	        });

	      function updateGallery(selector) {
	        let $sel = selector;
	        current_image = $sel.data('image-id');
	        $('#image-gallery-title')
	          .text($sel.data('title'));
	        $('#image-gallery-image')
	          .attr('src', $sel.data('image'));
	        disableButtons(counter, $sel.data('image-id'));
	      }

	      if (setIDs == true) {
	        $('[data-image-id]')
	          .each(function () {
	            counter++;
	            $(this)
	              .attr('data-image-id', counter);
	          });
	      }
	      $(setClickAttr)
	        .on('click', function () {
	          updateGallery($(this));
	        });
	    }
	  });

	// build key actions
	$(document)
	  .keydown(function (e) {
	    switch (e.which) {
	      case 37: // left
	        if ((modalId.data('bs.modal') || {})._isShown && $('#show-previous-image').is(":visible")) {
	          $('#show-previous-image')
	            .click();
	        }
	        break;

	      case 39: // right
	        if ((modalId.data('bs.modal') || {})._isShown && $('#show-next-image').is(":visible")) {
	          $('#show-next-image')
	            .click();
	        }
	        break;

	      default:
	        return; // exit this handler for other keys
	    }
	    e.preventDefault(); // prevent the default action (scroll / move caret)
	  });

	
	</script>

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
</body>
</html>