<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/login.js"></script>

<ol class="breadcrumb">
	<li><a href="#"><i class="fa fa-home"></i> Home</a></li>
	<li class="active">Login</li>
</ol>



<script type="text/javascript">

	
			
</script>


<span class="error"><s:actionerror id="server_side_error" /></span>
<s:form action="Login" id="login_form" theme="simple">
	<div class="panel panel-primary">
		<div class="panel-body">
			<h4>Login to your APM account</h4>
			<br />
			<%-- <span class="text-danger">Note:Fields marked with asterisk(*) are required.</span> --%>
			
			<div class="row">
				<div class="col-lg-4 col-md-5">
					<b>Select Database</b>
				</div>
				<div class="col-lg-8 col-md-7">
					<input type="radio" id="dbType" name="dbType" value="0"
						checked="checked" 
						style="display: inline; float: left; padding: 3px;"> 
						<label for="payBuy" style="display: inline; float: left;padding: 3px;">Live DB</label> 
						
						<input type="radio"
						id="payBuy1" name="dbType" value="1"
						
						style="display: inline; float: left;padding: 3px;"><label for="payBuy"
						style="display: inline; float: left;padding: 3px;">Demo DB</label>
				</div>
			</div>
			
			
			
			<div class="form-group">
				<label>User Id:</label>
				<s:textfield cssClass="form-control showToolTip" id="userId"
					name="userId" title="Enter user id" data-toggle="tooltip"
					data-placement="top" placeholder="Enter Your User Id" />

			</div>
			<div class="form-group">
				<label>Password:</label>
				<s:password id="password" name="password" title="Enter Password"
					cssClass="form-control showToolTip" data-toggle="tooltip"
					data-placement="top" placeholder="Enter Password" />

			</div>
			<div class="form-group">
				<a href="inputResetPassword" target="null">Forgot/Reset Password</a>

			</div>
			<div class="row">
				<div class="col-lg-2 col-md-3 col-sm-12 col-xs-12">
					<input type="button" onclick="loginSubmit()" cssClass="btn btn-primary btn-lg" />
				</div>
				<div class="visible-sm visible-xs"
					style="width: 100%; padding: 10px 0; float: left"></div>
				<div class="col-lg-2 col-md-3 col-sm-12 col-xs-12">
					<s:reset cssClass="btn btn-primary btn-lg" />
				</div>
			</div>
		</div>
	</div>










</s:form>