<%@taglib uri="/struts-tags" prefix="s"%>


<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
 <script type="text/javascript" src="tools/js/sendLetter.js"></script> 

<script>
	
	        bkLib.onDomLoaded(function() {
	           
	        	 new nicEditor().panelInstance('emailBodyLetter');
	        	 $('.nicEdit-panelContain').parent().width('auto');
	        	 $('.nicEdit-panelContain').parent().next().width('auto');
	        	 
	        	 $('.nicEdit-main').width('99%');
	        	 $('.nicEdit-main').height('900px');
	        	
	        	 //document.getElementById('templateName').disabled = 'disabled';
	        	 document.getElementById("user").disabled = 'disabled';
	        	
	      });
	        
	        //document.getElementById('saveId').style.display = '';
	        
</script>
<html>
<body>

<div class="">
							<div class="">
							<div class="">
			<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-10 col-md-10" style="padding-left:0px;padding-right:0px;">
			<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Print Letter</h4>
									</div>
								</div>
			</div>
			<div class="col-lg-1 col-md-1"></div>
			</div>			
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<div class="row">
	<div class="col-lg-12">
		<s:hidden name="message" id="message"></s:hidden>
			<s:if test="hasActionMessages()">
				<script>
					var msg = " " + document.getElementById('message').value;
					showGrowl('', msg, 'success', 'fa fa-check');
				</script>
			</s:if>
	</div>
</div>
<div class="row">
         <div class="col-lg-12 col-md-12">
         <div class="">
         <div class="col-lg-1 col-md-1"></div>
         <div class="col-lg-10 col-md-10" style="border: 1px solid #efefef;">
         <div class="panel-body">
		  <div class="col-lg-2">
          </div>
      <%--     <div class="row" id="usern" style="display: none;">         
         <div class="col-lg-2">
				<label>Select User</label>
		 </div>
		 <div class="col-lg-6">
				<s:select id="userNameletter" name="userName"  
				list="{'Client','Practitioner','ThirdParty','GP'}" cssClass="form-control  showToolTip chosen" 
				value="userName" ></s:select>
				<label  id = "unameError" class="text-danger"></label>	
		</div>		 
		</div> --%> 
		<br>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		 <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3" >
		    <label for="exampleInputEmail1">Patient Name</label><br>
		    	<s:textfield name="user" id="userletter" readonly="true" cssClass="form-control" onclick="showPopUpLetter()" data-toggle="modal" data-target="#userSearch" cssStyle="width:90%;"/>
				<label  id = "userError" class="text-danger"></label>	
				<s:hidden name="id" id="id"></s:hidden>
				<s:hidden name="user" id="userletter"></s:hidden>
		  </div>
		  <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		    <label for="exampleInputEmail1">Select Template</label><br>
		    	<s:if test="templateNameList.size!=0">
				<s:select id="templateName" name="templateName" listKey="id"
				headerValue="Select" headerKey="Select" listValue="templateName" list="templateNameList" 
				value="templateName" cssClass="form-control  showToolTip chosen-select" onchange="showTemplateDetailsLetter(this.value)"></s:select>
				</s:if>
				<label  id = "tnameError" class="text-danger"></label>	
		  </div>
		 
		   <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		    <label for="exampleInputEmail1">Email ID</label><br>
		    	<s:textfield name="email" id="gpLetterEmail" cssClass="form-control showToolTip" title="Enter EmailId"
								data-toggle="tooltip" placeholder="Enter EmailId" cssStyle="width:90%;"/>
			<label  id = "emailError" class="text-danger"></label>	
		  </div>
		  <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		    <label for="exampleInputEmail1">Cc</label><br>
		    	<s:textfield theme="simple" id = "gpLetterccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" cssStyle="width:90%;"/>
			<label  id = "ccError" class="text-danger"></label>	
		  </div>
		  </div>
		  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		  <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		    <label for="exampleInputEmail1">Subject</label><br>
		    	<input type="text" name= "subject" id = "gpLettersubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject" Style="width:90%;">
			<label  id = "subjectError" class="text-danger"></label>	
		  </div>
		  <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		  <br>
		  <input type="button" class='btn btn-primary' value="Email Template Master" onclick="openSamePopup('EmailTemplate')">
		  </div>
		  
		  </div>
		<%--  <div class="col-lg-2">
          </div>
		<div class="row">		
		 <div class="col-lg-2">
			<label> Date:</label>
			</div>
			 <div class="col-lg-6">
					<s:textfield theme="simple" id="dateTimeLetter"	name="dateTime"
					cssClass="form-control showToolTip" data-toggle="tooltip" title="Select Date" placeholder="Select Date"/>
		</div>		  
          </div>
           --%>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		    <label for="exampleInputEmail1">Body</label>
		    <div style="height: 500px; overflow: scroll;">
		    	<s:textarea class="form-control showToolTip textarea"  data-toggle="tooltip" title="Write content" placeholder="Write content" 
					name="body" id="emailBodyLetter" cols="40" rows="2" />
					</div>
		  </div>
		  </div>
		</div></div>
         </div>
			<div class="form-group">
		<div class="col-lg-6" style="margin-left: 94px;margin-top: 13px;">
		<button type="button" id="saveId" class="btn btn-primary"  onclick="sendEmailAsPdfTemplate();">Send Mail</button>
		<button type="button" class="btn btn-primary"  onclick="return createPdf()">Print</button>
		<!-- <button type="button" id="saveId" class="btn btn-primary" onclick="return createPrintLetterPdf()">Save as pdf</button>
		 --><!-- <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button> -->
   </div>
  
     <div class="col-lg-2">
    	<div id="pdfFileIdLetter">
			</div>
		</div>	
		<div class="col-lg-1 ">
			<div id="sendmail">
			</div></div>
			<div class="col-lg-1">
			<div id="printId">
			</div></div><div class="col-lg-2"></div>
    </div>
 </div>   
 <br>
    </div>
    
    <br>
    <!-- Modal User Search-->

<div class="modal fade" id="userSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">User Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/tools/pages/allSendLetter.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div> 
	
	
	<!-- Modal Email-->
	
<div class="modal fade" id="sendEmailLetterPopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
					<div class="row">
					<div class="col-lg-1 col-md-1">	
					</div>
				
					</div>
						<%-- <div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id = "gpLetterEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "gpLetterccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "gpLettersubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="gpLetterEmailBody" ></textarea>
						</div>
						<div class="form-group" id="pdfMailId">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary" onclick="sendPdfLetterMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
											

											
										</div>
									</div>
								</div>
							</div>
						</div>



	<br/>
	
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
    
    
    
    function showTemplateDetailsLetterww(id){
    	var url = "loademailtemplateEmailTemplate?selectedid="+id;
 	   
    	if (window.XMLHttpRequest) {
    		req = new XMLHttpRequest();
    	}
    	else if (window.ActiveXObject) {
    		isIE = true;
    		req = new ActiveXObject("Microsoft.XMLHTTP");
    	}   
                   
        req.onreadystatechange = showTemplateDetailsLetterReq;
        req.open("GET", url, true); 
                  
        req.send(null);
    	
    }
    
    function showTemplateDetailsLetterReq(){
    	if (req.readyState == 4) {
    		if (req.status == 200) {
    			 var str=req.responseText;	
    			 
    			 nicEditors.findEditor( "emailBodyLetter" ).setContent(str);
    		}
    	}
    }
    
  </script>
</body>
</html>
 