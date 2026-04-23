<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="registration/js/clinic.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<%LoginInfo logininfo = LoginHelper.getLoginInfo(request); %>

<script type="text/javascript">
	 /* bkLib.onDomLoaded(function() { nicEditors.editors.push(
	        new nicEditor().panelInstance(
	                document.getElementById('emailBody')
	                
	            )
	        ); });   */
	        
	        
	        bkLib.onDomLoaded(function() {
	           
	        	 new nicEditor().panelInstance('emailBody');
	        	 $('.nicEdit-panelContain').parent().width('500px');
	        	 $('.nicEdit-panelContain').parent().next().width('500px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px');
	      });
	        
	    
	    	
	       
	    	
	    	
</script>

		<div class="">
			<div class="panel-body">
			<div class="col-lg-12 col-md-12">
			<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-10 col-md-10" style="padding-left:0px;padding-right:0px;">
			<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Edit Location</h4>
									</div>
								</div>
			</div>
			<div class="col-lg-1 col-md-1"></div>
			</div>
				
		<s:form action="updateSaveLocationClinicRegistration" theme="simple" method="post" enctype="multipart/form-data"> 
			<s:hidden name = "locationid"></s:hidden>
				<div class="col-lg-12">
				<div class="col-lg-1 col-md-1 col-sm-1"></div>
				<div class="col-lg-10 col-md-10 col-sm-10" style="border: 1px solid #efefef;padding-top: 15px;">
				<div class="col-lg-6 col-md-6">
					<div class="form-group">
					   <label for="exampleInputEmail1">City Name</label>
					   <s:textfield name="city" id="city" cssClass="form-control showToolTip locationname" data-toggle="tooltip"/>
				  	</div>
				  	<div class="form-group">
					   <label for="exampleInputEmail1">Address Type</label>
					   <s:select list="{'Registered','Branch'}" name="addressType" id="addressType" headerKey="0" headerValue="Select" cssClass="form-control showToolTip address" data-toggle="tooltip" onchange="setEditCheckLocation();" ></s:select>
				  	</div>
				  	<div class="form-group">
					   <label for="exampleInputEmail1">Post Code</label>
					   <s:textfield name="pinCode" id="pinCode" cssClass="form-control showToolTip pinCode" data-toggle="tooltip" />
				  	</div>
				  	<div class="form-group">
					   <label for="exampleInputEmail1">Email Id</label>
					   <s:textfield name="emailId" id="emailId" cssClass="form-control showToolTip emailId" data-toggle="tooltip" />
				  	</div>
				  	<div class="form-group">
					   <label for="exampleInputEmail1">Contact No.</label>
					   <s:textfield name="contactNo" id="contactNo" cssClass="form-control showToolTip contactNo" data-toggle="tooltip" />
				  	</div>
				  	<s:hidden name = "userImageFileName"></s:hidden>
					<s:if test="userImageFileName!=null">
					<div class="form-group">
					   <label for="exampleInputEmail1">Map</label>
					   <img src="liveData/locationMap/<s:property value="userImageFileName"/>">
				  	</div>
					</s:if>
					<div class="form-group">
					   <label for="exampleInputEmail1">Upload Map</label>
					   <s:file name="userImage"/>	
				  	</div>
				  	<div class="form-group">
					   <label for="exampleInputEmail1">Check Location Has to Set</label>
					   <s:checkbox name="checkLocation" id="checkLocation"  title="In all Communications"/>	
				  	</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<div class="form-group">
					   <label for="exampleInputEmail1">Location Reference</label>
					   <s:textfield name="locationname" id="locationname" cssClass="form-control showToolTip locationname" data-toggle="tooltip"/>
				  	</div>
				  	<div class="form-group">
					   <label for="exampleInputEmail1">Hospital Name</label>
					   <s:textfield name="hospname" id="hospname" cssClass="form-control showToolTip Hospital Name" data-toggle="tooltip"/>
				  	</div>
				  	<div class="form-group">
					   <label for="exampleInputEmail1">Address</label>
					   <s:textarea name="address" id="address" cssClass="form-control showToolTip address" data-toggle="tooltip"  rows="5"  ></s:textarea>
				  	</div>
				  	<div class="form-group">
					   <label for="exampleInputEmail1">Color</label>
					   <s:textfield name="colorName" id="colorName" cssClass="form-control showToolTip colorName" data-toggle="tooltip"/>
				  	</div>
				  	<div class="form-group">
					   <label for="exampleInputEmail1">Location Direction</label>
					   <s:hidden name="loc_direction" id="loc_direction"/>
								<textarea class="form-control showToolTip" data-toggle="tooltip" rows="6" cols="60"
								title="Enter Body" name="emailBody"  id="emailBody" ><s:property value="loc_direction"/></textarea>
				  	</div>
				</div>
				
				</div>
				<div class="col-lg-1 col-md-1 col-sm-1"></div>
						<%-- <div class="form-group">
							<label>Country:</label>
							<s:textfield name="country" cssClass="form-control showToolTip country" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>City :</label>
							<s:textfield name="city" cssClass="form-control showToolTip country" data-toggle="tooltip" />
							
						</div> --%>
					<div class="col-lg-12 col-md-12" style="padding-right: 0px;">	
					<div class="col-lg-1 col-md-1"></div>
					<div class="col-lg-10 col-md-10" style="padding-right: 0px;margin-top: 15px;">
						<div class="text-right">
						 <s:submit value="Update" cssClass="btn btn-primary" onclick="return editValidate();"></s:submit> 
							
						</div>
					</div>
					<div class="col-lg-1 col-md-1"></div>
						
					</div>
					</div>
				</s:form>
				
		</div>
				</div>
				
				
				
				<script>

google.load("elements", "1", {
      packages: "transliteration"
    });

function onLoad() {
  var options = {
      sourceLanguage:
          google.elements.transliteration.LanguageCode.ENGLISH,
      destinationLanguage:
          [google.elements.transliteration.LanguageCode.HINDI],
      shortcutKey: 'ctrl+g',
      transliterationEnabled: true
  };
  var options1 = {
	      sourceLanguage:
	          google.elements.transliteration.LanguageCode.ENGLISH,
	      destinationLanguage:
	          [google.elements.transliteration.LanguageCode.MARATHI],
	      shortcutKey: 'ctrl+g',
	      transliterationEnabled: true
	  };

  // Create an instance on TransliterationControl with the required
  // options.
  var control =
      new google.elements.transliteration.TransliterationControl(options);
  var control1 =
      new google.elements.transliteration.TransliterationControl(options1);

  // Enable transliteration in the textbox with id
  // 'transliterateTextarea'.
  
  <%if(logininfo.isBalgopal()){%>
  control.makeTransliteratable(['address']);
  control1.makeTransliteratable(['hospname']);
  <%}%>
}
google.setOnLoadCallback(onLoad);
</script>
				
	