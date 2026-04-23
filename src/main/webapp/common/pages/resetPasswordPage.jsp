<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/resetPassword.js"></script>

<ol class="breadcrumb">
	<li><a href="#"><i class="fa fa-home"></i> Home</a></li>
	<li class="active">Reset Your Password</li>
</ol>


<div class="panel panel-primary">
		<div class="panel-body">
<div class="row">
	<div class="col-lg-12 col-md-12">
	<p><label>You can change or reset your password.</label></p>
	
	</div>
	

</div>

<s:form action = "modifyResetPassword" theme="simple" id = "modifyResetPassword">

<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>Email</label><label class="text-danger reqd-info">*</label>
							<s:textfield id="emailId" name="emailId" theme="simple"
								
								cssClass="form-control showToolTip" data-toggle="tooltip"
								onchange="checkUserIdExist(this.value)"
								placeholder="Enter Email Id" />
						</div>
						<div class="form-group">
							<label>Password</label><label class="text-danger reqd-info">*</label>
							<s:password id="password" name="password" theme="simple"
								title="password must contain atleast 1 numeric character,1 lowercase letter,1 uppercase letter,1 special character and minimum 8 characters"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Password" />

						</div>
						<div class="form-group">
							<label>Confirmed Password</label><label
								class="text-danger reqd-info">*</label>
							<s:password id="confirmPassword" name="confirmPassword"
								key="label.confirmPassword" labelposition="left" required="true"
								title="Re-enter above password"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Confirmed Password" />
						</div>
						
					</div>
				</div>
				<input type="button" class="btn btn-primary" onclick="return validResetPswd()" value="Update">
</s:form>	
</div>
</div>	