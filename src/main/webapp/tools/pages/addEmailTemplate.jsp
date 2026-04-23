<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>

<script type="text/javascript">
		        
	        bkLib.onDomLoaded(function() {
		           
	        	 new nicEditor().panelInstance('headerNote');
	        	 $('.nicEdit-panelContain').parent().width('90%');
	        	 $('.nicEdit-panelContain').parent().next().width('100%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('40px');
	        	 
	        	/*  new nicEditor().panelInstance('body1Note');
	        	 $('.nicEdit-panelContain').parent().width('800px');
	        	 $('.nicEdit-panelContain').parent().next().width('800px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('80px'); */
	        	 
	        	 new nicEditor({maxHeight : 650}).panelInstance('body2Note');
	        	 $('.nicEdit-panelContain').parent().width('100%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
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
<div class="col-lg-12 col-md-12">
			<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-10 col-md-10" style="padding-left:0px;padding-right:0px;">
			<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<h4>Add Email Templates</h4>
									</div>
								</div>
			</div>
			<div class="col-lg-1 col-md-1"></div>
			</div>
</div>


<s:form action="saveEmailTemplate" id="emailTemplate_form" theme="simple">
<div class="row">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<div class="col-lg-1 col-md-1"></div>
	<div class="col-lg-10 col-md-10" style="border: 1px solid #efefef;">
	<div class="">
			<div class="panel-body">
				<label>Select User</label><br>
				<s:select id="userName" name="userName" list="{'Select','Client','Practitioner','ThirdParty','GP'}" 
				cssClass="form-control  showToolTip " value="userName" style="width:35%;"></s:select>
				<label  id = "unameError" class="text-danger"></label>	
				<br>
				<label>Template Name</label>
				<s:textfield id = "templateName" name = "templateName" theme="simple" cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Template Name"  placeholder="Enter Template Name"/>
				<label  id = "tnameError" class="text-danger"></label>	
				<br>
				<label>Email Header</label><label class="text-danger">*</label>
				<textarea readonly="readonly" rows="3" cols="10" id="headerNote" name="headerNote" 
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Email Header" placeholder="Enter Email Header" ></textarea>
				<label  id = "headerError" class="text-danger"></label>	
				<br>	
				<!-- <label style="display: none;">Email Body1</label><label class="text-danger">*</label>
				<textarea style="display: none;" rows="10" cols="10" id="body1Note" name="body1Note"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Email Body1" placeholder="Enter Email Body1" ></textarea> -->
				<label style="display: none;"  id = "body1Error" class="text-danger"></label>	
					<!-- <br> -->
				<label>Email Body</label><label class="text-danger">*</label>
				<textarea style="height:650px;" rows="10" cols="10" id="body2Note" name="body2Note"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Email body2" placeholder="Enter Email body2" ></textarea>
				<label  id = "body2Error" class="text-danger"></label>	
					<!-- <br> -->
				<!-- <label style="display: none;">Email Footer</label><label class="text-danger">*</label>
				<textarea style="display: none;" rows="6" cols="10" id="footerNote" name="footerNote"
					class="showToolTip form-control" data-toggle="tooltip"
					title="Enter Email Footer" placeholder="Enter Email Footer" ></textarea> -->
				<label style="display: none;"  id = "footerError" class="text-danger"></label>	
			</div>
		</div>
	</div>
	</div>
	<div class="col-lg-1 col-md-1"></div>
	
</div>
<div class="col-lg-12 col-md-12" style="padding-right: 0px;padding-bottom: 15px;">	
					<div class="col-lg-1 col-md-1"></div>
					<div class="col-lg-10 col-md-10" style="padding-right: 0px;margin-top: 15px;">
						<div class="text-right">
							<s:submit cssClass="btn btn-primary" value="Save" onclick="return saveValidation()"/>
							<s:reset cssClass="btn btn-primary" />
							<a href="backEmailTemplate" class="btn btn-primary">Back</a>
						</div>
					</div>
					<div class="col-lg-1 col-md-1"></div>
					</div>
	<s:token></s:token>
</s:form>


<br><br>
