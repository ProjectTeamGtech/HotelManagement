<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<script type="text/javascript">
		        
	        bkLib.onDomLoaded(function() {
		           
	        	 new nicEditor().panelInstance('body');
	        	 $('.nicEdit-panelContain').parent().width('800px');
	        	 $('.nicEdit-panelContain').parent().next().width('800px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('840px');
	        	 
	        	 document.getElementById('templateName').disabled = 'disabled';
	        	 
	        	
	      });
	     
	       
	        
	        
</script>



<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">			
			<li class="active"> Email Sending</li>
		</ol>
	</div>
</div>

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

<s:form action="previewTPReferal"  theme="simple" id="category_form"> 

<div class="row">
	<div class="col-lg-3 col-md-3">
		<label>Client</label>
		<s:textfield name="client" id="client" readonly="true" cssClass="form-control" 
				     onclick="showPopUp2()" data-toggle="modal" data-target="#clientSearch"/>
		<s:hidden name="clientId" id="clientId"></s:hidden>
	</div>
	<div class="col-lg-3 col-md-3">
			<label>Show Template Name</label>
			<s:select id="templateName" name="templateName" listKey="templateName"
				headerValue="All" headerKey="All" listValue="templateName" list="templateNameList" 
				value="templateName" cssClass="form-control" onchange="showTemplateDetails(this.id)"></s:select>
				<label  id = "tnameError" class="text-danger"></label>	
			<s:hidden name="templateName" id="templateName"></s:hidden>
	</div>	
	<div class="col-lg-3 col-md-3">
			<label class="width-full" style="font-weight: normal">.</label> 
			<input type="submit" value="Preview" onclick="return preview()" class="btn btn-primary">
			<br><br>	
	</div>	
</div>
</s:form>
<br><br>

<!-- Modal Client Search-->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/tools/pages/allTemplatePatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal Email-->

<div class="col-lg-9">
<div class="panel panel-primary">
	<div class="panel-body">
			<div class="modal-header">
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
					<s:form action="emailTemplateTPReferal" id="emailTemplate_form" theme="simple">
						<div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id = "thirdPartEmail" name = "email"
								cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver"
								title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "ccEmail" name = "ccEmail"
								cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "subject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject"
								placeholder="Enter Subject">
						</div>
						
						<div class="form-group">
							<label>Body:</label>						
						
							<div class="panel-body">							
								
								<textarea rows="20" cols="10" id="body" name="body"
									class="showToolTip form-control" data-toggle="tooltip"
									title="Enter Email body" placeholder="Enter Email body" ></textarea>								
								
							</div>
											
						</div>
					
						<div class="form-group">
						<s:submit cssClass="btn btn-primary" value="Send" />
						<!--<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="sendTemplateMail();">Send</button>
						  --><button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</br></br>