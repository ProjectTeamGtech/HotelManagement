

<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--Mobile first-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!--IE Compatibility modes-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>YUVICARE</title>
<link rel="shortcut icon" href=" manaslogin/images/Manas_Yuvicare_Circle Favicon.png">

<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="common/BootstrapNew/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link href="common/css/Style.css" rel="stylesheet" type="text/css" />
<link href="common/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="common/css/responsive.css" rel="stylesheet" type="text/css" />


<link href="common/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />


<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />
<link href="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/lib/cupertino/jquery-ui.min.css" rel="stylesheet" />

<!-- vendor css files -->
<link rel="stylesheet" href="_assets/newtheme/css/vendor/animate.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="_assets/newtheme/js/vendor/animsition/css/animsition.min.css"> -->
<!-- project main css files -->

<link rel="stylesheet" type="text/css" href="dist/styles/jquery.stickynotif.min.css" />
        
<!--/ stylesheets -->
<!-- <script src="_assets/newtheme/js/vendor/modernizr/modernizr-2.8.3-respond-1.4.2.min.js"></script> -->
<script src="common/js/jquery.js" type="text/javascript"></script>

<script src="popupdialog/dialog/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="popupdialog/dialog/js/jquery-ui.min.js" type="text/javascript"></script> 
<script src="common/BootstrapNew/js/bootstrap.min.js" type="text/javascript"></script> 

<script src="popupdialog/dialog/js/jquery.ui.core.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.widget.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.datepicker.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.dialog.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.draggable.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.droppable.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.sortable.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.mouse.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.button.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.position.js"></script>
<!-- <script src="popupdialog/dialog/js/jquery.ui.tooltip.js"></script> -->
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
<script src="common/js/jquery/additional-validation-methods.js" type="text/javascript"></script>
<script src="common/js/jquery/jquery.validate.js" type="text/javascript"></script>
<script src="common/js/jquery.alerts.js" type="text/javascript"></script>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masterValidators.js"></script>
<script type="text/javascript" src="common/js/jquery/bootstrap-growl-master/bootstrap-growl.min.js"></script>
<!-- <script src="common/BootstrapNew/bootstrap/js/jquery.scrollTo.min.js"></script> -->
<!-- <script src="common/BootstrapNew/js/jquery.nicescroll.js"></script> -->

<script src="dist/jquery.stickynotif.min.js"></script>
<script src="common/blockui-master/jquery.blockUI.js" type="text/javascript"></script>
<script src="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/lib/moment.min.js"></script>
<script src="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/fullcalendar.min.js"></script>
<script src="_assets/newtheme/js/vendor/jRespond/jRespond.min.js"></script>
<script src="_assets/newtheme/js/vendor/sparkline/jquery.sparkline.min.js"></script>
<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<script src="_assets/newtheme/js/vendor/animsition/js/jquery.animsition.min.js"></script>
<script src="_assets/newtheme/js/main.js"></script> 
        
<!--/ modernizr -->
<style>
    #header {
        background-color: #43b9be;
            }
    .texthead {
           font-size: 15px;
            }
</style>

</head>
<body style="overflow-x:hidden">


	<div class="container-fluid">
		<div class="row">
			<!-- Render Menu Partial Here -->
			<tiles:insertAttribute name="header" />
		</div>
	</div>
	<div class="container-fluid" id="masterbodycontainer">
		<div class="row">
			<div class = "col-lg-12 hidden-print" id="masterbodydiv">
			
			</div>
			<div class="col-lg-12">
				<!-- Reder body here -->
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>


	<!-- <div class="width-full text-center pull-left hidden-print" style="color: #fff; padding: 15px 0; background: black">
		Footer Goes here
		@CBS Tech
	</div> -->
	 
	 
	 
	 <!-- My Profile Popup -->
<div id="myProfile" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">My Profile - Secure your account</h4>
      </div>
      <div class="modal-body">
        <div class="col-lg-12 col-xs-12 col-md-12">
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
                                                                    <s:textfield cssClass="form-control" id="user_fullname" name="fullname" readonly="" cssStyle="background-color: rgba(239, 239, 239, 0.48);" />
                                                                </div>


                                                                <div class="form-group col-sm-4">
                                                                    <label for="username">User ID</label>
                                                                     <s:textfield cssClass="form-control"  id="user_userid" name="userid" readonly="" cssStyle="background-color: rgba(239, 239, 239, 0.48);" />
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                    <label for="username">Department</label>
                                                                    <s:textfield cssClass="form-control" name="jobtitle" id="user_jobtitle"  cssStyle="background-color: rgba(239, 239, 239, 0.48);" />
                                                                </div>
                                                                

                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group col-sm-4">
                                                                    <label for="username">State</label>
                                                                     <s:textfield cssClass="form-control" name="state"  id="user_state"  cssStyle="background-color: rgba(239, 239, 239, 0.48);" />
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                	 <label for="username">Location</label>
                                                                    <input type="text" class="form-control" id="user_location" readonly="" style="background-color: rgba(239, 239, 239, 0.48);">
                                                                </div>
																  <div class="form-group col-sm-4" id="phonediv">
                                                                    <label for="fullname">Mobile No</label>
                                                                   <s:textfield cssClass="form-control" name="mobile"  id="user_mobile" maxlength="10" placeholder="Mobile No" />
                                                                </div>
                                                            </div>
                                                           
                                                            <div class="row">
																<div class="form-group col-sm-4">
                                                                	 <label for="old-password">Old Password</label>
                                                                   <input type="password" class="form-control" id="user_oldpassword" placeholder="Password">
                                                                  
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                    <label for="new-password">New Password</label>
                                                                    <input type="password" class="form-control" id="user_newpassword" placeholder="New Password">
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                    <label for="new-password-repeat">New Password Repeat</label>
                                                                    <input type="password" class="form-control" id="user_confirmpassword" placeholder="Repeat Password">
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                           		<div class="form-group col-sm-4">
                                                                    <label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0" style="color: #cc6161;">
			                                                            <input type="checkbox" id="enable-show"><i></i> Show Passwords
			                                                        </label>
                                                                </div>
                                                            	
                                                            </div>
                                                          
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="updatePharmacyUser()">Save</button>
      </div>
    </div>
  </div>
</div>
	 
	 
	 
	 
	 
</body>
<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>

