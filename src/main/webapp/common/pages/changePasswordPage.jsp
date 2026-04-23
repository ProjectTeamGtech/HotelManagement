<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="common/js/resetPassword.js"></script>

<div class="">
							<div class="">
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<s:form action="updateuserpwdResetPassword" theme="simple" id = "changePaswdResetPassword">
<div class="row">
<input type="hidden" id="newotp">
<div class="col-lg-2 col-md-2"></div>
<div class="col-lg-8 col-md-8">
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Change Password</h4>

									</div>
								</div>
								
								
								<div class="col-lg-12 col-xs-12 col-md-12" style=" padding-top:15px;border: 1px solid #ddd;">
        	<div class="col-lg-3 col-md-3">
        		<img src="cicon/user_profile.jpg" class="img-responsive" style="margin-right: auto;"></img>
        	</div>
        	<div class="col-lg-9 col-md-9" style="padding: 0px;">
        		
                                                            <div class="row">
																<input type="hidden" id="user_id">
																<input type="hidden" id="newotp">
																<!-- <input type="hidden" id="user_oldpwd"> -->
                                                                <div class="form-group col-sm-4">
                                                                    <label for="fullname">Full Name</label>
                                                                    <s:textfield cssClass="form-control" id="user_fullname" name="fullname" readonly="true" cssStyle="background-color: rgba(239, 239, 239, 0.48);" />
                                                                </div>


                                                                <div class="form-group col-sm-4">
                                                                    <label for="username">User ID</label>
                                                                     <s:textfield cssClass="form-control"  id="user_userid" name="userid" readonly="true" cssStyle="background-color: rgba(239, 239, 239, 0.48);" />
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                    <label for="username">Department</label>
                                                                    <s:textfield cssClass="form-control" name="jobtitle" id="user_jobtitle"  readonly="true"  cssStyle="background-color: rgba(239, 239, 239, 0.48);" />
                                                                </div>
                                                                

                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group col-sm-4">
                                                                    <label for="username">State</label>
                                                                     <s:textfield cssClass="form-control" name="state"  id="user_state" readonly="true"   cssStyle="background-color: rgba(239, 239, 239, 0.48);" />
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                	 <label for="username">Location</label>
                                                                	 <s:textfield cssClass="form-control" name="location"  id="user_location" readonly="true"   cssStyle="background-color: rgba(239, 239, 239, 0.48);" />
                                                                </div>
																  <div class="form-group col-sm-4">
                                                                   <label>Mobile Number</label><label
																		class="text-danger reqd-info">*</label>
																	<s:if test="mobile!=''">
																		<s:textfield id="mobile" name="mobile" readonly="true"
																		key="label.mobile" labelposition="left" required="true"
																		title="enter mobile"
																		cssClass="form-control showToolTip" data-toggle="tooltip"
																		placeholder="enter mobile" />
																		
																	</s:if>
																	<s:else>
																		<s:textfield id="mobile" name="mobile"
																		key="label.mobile" labelposition="left" required="true"
																		title="enter mobile"
																		cssClass="form-control showToolTip" data-toggle="tooltip"
																		placeholder="enter mobile" />
																	</s:else>	
																	
                                                                </div>
                                                            </div>
                                                           
                                                            <div class="row">
                                                            <div class="form-group col-sm-4">
																<label>Old Password</label>
																
																<s:if test="errpassword==null">
																   <label name="errpassword" id="errpassword" class="text-danger reqd-info">*</label>
																</s:if>
																<s:else>
																  <label name="errpassword" id="errpassword" class="text-danger reqd-info"><s:property value="errpassword"/></label>
																</s:else>
																<s:if test="oldPassword==null">	
																<input type="password" class="form-control showToolTip" id="oldPassword"
																	name="oldPassword" title="Enter Old Password"
																	data-toggle="tooltip" placeholder="Enter Old Password" onchange="checkThisOldPassword(this.value)"/>
															   </s:if>
															   <s:else>
															      <input type="password" class="form-control showToolTip" id="oldPassword"
																	name="oldPassword" title="Enter Old Password" value="<s:property value="oldPassword"/>"
																	data-toggle="tooltip" placeholder="Enter Old Password" onchange="checkThisOldPassword(this.value)"/>
															   </s:else>
															 	<label id = "oldPasswordError" class="text-danger"></label>		
															</div>
																
                                                                <div class="form-group col-sm-4">
                                                                   <label>New Password</label><label class="text-danger reqd-info">*</label>
																	<s:password id="password" name="password" theme="simple"
																		title="password must contain atleast 1 numeric character,1 lowercase letter,1 uppercase letter,1 special character and minimum 8 characters"
																		cssClass="form-control showToolTip" data-toggle="tooltip"
																		placeholder="Enter New Password" />
																		
																		<label id = "passwordError" class="text-danger"></label>	
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                   <label>Confirmed Password</label><label
																	class="text-danger reqd-info">*</label>
																	<s:password id="confirmPassword" name="confirmPassword"
																	key="label.confirmPassword" labelposition="left" required="true"
																	title="Password and Confirm Password does not match"
																	cssClass="form-control showToolTip" data-toggle="tooltip"
																	placeholder="Confirmed Password" />
																	
																	<label id = "confirmPasswordError" class="text-danger"></label>		
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                           		<div class="form-group col-sm-4">
                                                                    <label class="" style="color: #cc6161;">
			                                                            <input type="checkbox" id="enable-show"><i></i> Show Passwords
			                                                        </label>
                                                                </div>
                                                                <div class="form-group col-sm-8 text-right">
                                                                	<input type="button" class="btn btn-primary" value="Update" onclick="return validChangePassword()">
                                                                </div>
                                                            	
                                                            </div>
                                                          
        	</div>
        	
        	
        </div>
        
								
								
								
								
				
				
				
				
			</div>
			<div class="col-lg-2 col-md-2"></div>
</div>

			
		</s:form>
											

											
										</div>
									</div>
								</div>
							</div>
							
							<s:form action="checkcheckThisOldPswdResetPassword" id="chform">
							
							 <s:hidden name="oldPassword" id="oldPasswordid"></s:hidden>
							
							</s:form>
							
							
						</div>
						
						
						
						
			<!-- Modal --><!-- Remark chnage password -->
<div id="otpmodel" class="modal fade" role="dialog" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog modal-sm">

    Modal content
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Enter OTP</h4>
      </div>
      <div class="modal-body">
        	<input type="text" class="form-control" id="otp" placeholder="Enter OTP" style="background-color: beige;"/>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="confirmotp()" data-dismiss="modal" value="Confirm">
      </div>
    </div>

  </div>
</div>			


<script>


//Place this plugin snippet into another file in your applicationb
(function ($) {
    $.toggleShowPassword = function (options) {
        var settings = $.extend({
            field: "#password",
            control: "#toggle_show_password",
        }, options);

        var control = $(settings.control);
        var field = $(settings.field)

        control.bind('click', function () {
            if (control.is(':checked')) {
                field.attr('type', 'text');
            } else {
                field.attr('type', 'password');
            }
        })
    };
}(jQuery));

//Here how to call above plugin from everywhere in your application document body
$.toggleShowPassword({
    field: '#oldPassword',
    control: '#enable-show'
});
$.toggleShowPassword({
    field: '#password',
    control: '#enable-show'
});
$.toggleShowPassword({
    field: '#confirmPassword',
    control: '#enable-show'
});
</script>


