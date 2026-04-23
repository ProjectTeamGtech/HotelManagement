<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>

<script type="text/javascript">
		        
	        bkLib.onDomLoaded(function() {
		           
	        	 new nicEditor().panelInstance('headerNote');
	        	 $('.nicEdit-panelContain').parent().width('800px');
	        	 $('.nicEdit-panelContain').parent().next().width('800px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('40px');
	        	 
	        	/*  new nicEditor().panelInstance('body1Note');
	        	 $('.nicEdit-panelContain').parent().width('800px');
	        	 $('.nicEdit-panelContain').parent().next().width('800px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px'); */
	        	 
	        	 new nicEditor().panelInstance('body2Note');
	        	 $('.nicEdit-panelContain').parent().width('800px');
	        	 $('.nicEdit-panelContain').parent().next().width('800px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px');
	        	 
	        	/*  new nicEditor().panelInstance('footerNote');
	        	 $('.nicEdit-panelContain').parent().width('800px');
	        	 $('.nicEdit-panelContain').parent().next().width('800px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px'); */
	      });
</script>


<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="EmailTemplate">All SMS Template List</a></li>
			<li class="active">Add SMS Templates</li>
		</ol>
	</div>
</div>


<s:form action="updateSMSTemplate" id="emailTemplate_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
	
		<div class="panel panel-primary" style="width: 850px;height: 500px;">
			<div class="panel-body">
			    <s:hidden name="id"><s:property value="id"/></s:hidden>
				<label>Template Name</label>
				<s:textfield id = "templateName" name = "templateName" theme="simple" cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Template Name"  placeholder="Enter Template Name"/>
				<label  id = "tnameError" class="text-danger"></label>	
				<br>
				<label>Text</label><label class="text-danger">*</label>
				<textarea rows="5" cols="10" id="text" name="text" 
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter SMS Body" placeholder="Enter SMS Body" maxlength="160" 
					 ><s:property value="text"/></textarea>
				<label  id = "headerError" class="text-danger"></label>	
				<br>	
				<!-- <label style="display: none;">Email Body1</label><label class="text-danger">*</label>
				<textarea style="display: none;" rows="10" cols="10" id="body1Note" name="body1Note"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Email Body1" placeholder="Enter Email Body1" ></textarea> -->
				<!--  <label style="display: none;"  id = "body1Error" class="text-danger"></label>	-->
					<!-- <br> -->
					<!-- <br> -->
				<!-- <label style="display: none;">Email Footer</label><label class="text-danger">*</label>
				<textarea style="display: none;" rows="6" cols="10" id="footerNote" name="footerNote"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Email Footer" placeholder="Enter Email Footer" ></textarea> -->
				<label style="display: none;"  id = "footerError" class="text-danger"></label>	
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Update" onclick="return saveValidation()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="SMSTemplate" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>


<br><br>
