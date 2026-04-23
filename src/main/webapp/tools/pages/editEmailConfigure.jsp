<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="registration/js/checkMailSend.js"></script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Edit Email Setup</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<script type="text/javascript">

$(document).ready(function() {
	
	<%String emailpwd = (String)session.getAttribute("emailpwd");%>
	var emailpwddata = '<%=emailpwd%>'; 
	
	document.getElementById('emailPassword').value = emailpwddata;
	document.getElementById('emailConfirmPassword').value = emailpwddata;
	
});
</script>



<div class="">
<div class="">
<br>
<s:form action="updateEmailConfigureEmailTemplate" id="emailConfigurefrm" theme="simple" method="post" enctype="multipart/form-data">

<div class="row">
	<div class="col-lg-12">	
		<div class="form-group">			
		<div class="row">
	
		<div class="col-lg-3">
			<label>Enter Email Address to send email from</label>
			</div>
			<div class="col-lg-2">
				<s:textfield name="emailUserName" id="emailUserName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Configure Email UserName" title="Enter Configure Email User Name"/>
			</div>
		</div>
		<br/>
		<s:hidden name="emailConfigureId" id="emailConfigureId"/> 
		 <div class="row">
		
			<div class="col-lg-3">
		<label>Email Password</label>
			</div>
			<div class="col-lg-2">
				<s:password id="emailPassword" name="emailPassword" theme="simple" title="Minimum 6 characters, make it hard to guess"
					cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Email Configure Password" />
			</div>
		</div>
		<br/>
		
		<div class="row">
		
			<div class="col-lg-3">
			<label>Confirm Email Password</label>
			</div>
			<div class="col-lg-2">
				<s:password id="emailConfirmPassword" name="emailConfirmPassword" theme="simple" title="Minimum 6 characters, make it hard to guess"
					cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Email Configure Confirm Password" />
			</div>
		</div>
		<br/>
		
		<div class="row">
		
			<div class="col-lg-3">
			<label>Email Hosting Server</label>
			</div>
			<div class="col-lg-2">
				<s:textfield name="emailHostName" id="emailHostName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Configure Email Host Name" title="Enter Configure Email Host Name"/>
			</div>
		</div>
		</div>
	</div>
</div>



 <div class="row">
				
			<div class="col-lg-3">		 	
				
				<label>Set Auto Reminder: in Hrs (24/ 48/72 hrs) </label>		
			</div>
			<div class="col-lg-2" >
			 <s:select name="adDuration"
				list="#{'1':'24 Hour','2':'48 Hour','3':'72 Hour','4':'96 Hour','5':'120 Hour'}"
				cssClass="form-control" headerKey="0" headerValue="Select Duration" ></s:select>
			</div>
		</div>
		



<br>

<div class="row">
	
		<div class="col-lg-9 col-md-9" style="float: right;">
			<s:submit cssClass="btn btn-primary" value="Update" onclick="return saveValidation()"/>					
			<s:reset cssClass="btn btn-primary" />		
		</div>
		
	</div>

</s:form>

</div>
</div>
											

											
										</div>
									</div>
								</div>
							</div>
						</div>





