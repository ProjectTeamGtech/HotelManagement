<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="registration/js/checkMailSend.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li>Manage Email</li>
			<li class="active">Configure Clinic email</li>
		</ol>
	</div>
</div>



<div class="panel panel-primary">
<div class="panel-body">
<s:form action="saveEmailConfigureEmailTemplate" id="emailConfigurefrm" theme="simple" method="post" enctype="multipart/form-data">

<div class="row">
	<div class="col-lg-12">	
		<div class="form-group">			
		<div class="row">
		<div class="col-lg-3"></div>	
		<div class="col-lg-3">
			<label>Email Configure User Name</label>
			</div>
			<div class="col-lg-2">
				<s:textfield name="emailUserName" id="emailUserName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Configure Email UserName" title="Enter Configure Email User Name"/>
			</div>
		</div>
		<br/>
		
		<div class="row">
		<div class="col-lg-3"></div>
			<div class="col-lg-3">
		<label>Email Configure Password</label>
			</div>
			<div class="col-lg-2">
				<s:password id="emailPassword" name="emailPassword" theme="simple" title="Minimum 6 characters, make it hard to guess"
					cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Email Configure Password" />
			</div>
		</div>
		<br/>
		
		<div class="row">
		<div class="col-lg-3"></div>
			<div class="col-lg-3">
			<label>Email Configure Confirm Password</label>
			</div>
			<div class="col-lg-2">
				<s:password id="emailConfirmPassword" name="emailConfirmPassword" theme="simple" title="Minimum 6 characters, make it hard to guess"
					cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Email Configure Confirm Password" />
			</div>
		</div>
		<br/>
		
		<div class="row">
		<div class="col-lg-3"></div>
			<div class="col-lg-3">
			<label>Email Configure Host Name</label>
			</div>
			<div class="col-lg-2">
				<s:textfield name="emailHostName" id="emailHostName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Configure Email Host Name" title="Enter Configure Email Host Name"/>
			</div>
		</div>
		</div>
	</div>
</div>

<br/>

<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return saveValidation()"/>					
			<s:reset cssClass="btn btn-primary" />		
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>

</s:form>

</div>
</div>