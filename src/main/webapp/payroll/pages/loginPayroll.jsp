 <%@taglib uri="/struts-tags" prefix="s"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <link rel="icon" href="_assets/img/favicon.ico">
    <title>YUVICARE</title>
    
    <!-- Custom styles for this template -->
   
   <!--  <link href="_assets/css/style-responsive.css" rel="stylesheet">  -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
   
<script type="text/javascript" src="common/js/login.js"></script> 
  </head>

  <body>

      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
<span class="error" style="text-align: center;"><s:actionerror id="server_side_error" /></span>
	  <div id="login-page">
	  	<div class="container">
	<section id="content">
	  <div class="container w-420 p-15 bg-white mt-40 text-center">
                    <h2 class="text-light text-greensea">Log In</h2>
					<form action="loginReportPayroll" id="login_form" method="post" class="mt-20">
						<div class="selectdb centered hidden">
			                  <p>Select Database</p>
			                  <button class="btn btn-primary" type="button"><input type="radio" id="dbType" name="dbType" value="0" checked="checked"/> 
									<label for="payBuy"></label> <i class="fa fa-database"></i> Live DB
									</button>
			                  <button class="btn btn-primary" type="button"><input type="radio" id="payBuy1" name="dbType" value="1">
			                  <label for="payBuy"></label><i class="fa fa-database"></i> Demo DB</button>
			              </div>
                        <div class="form-group">
                            <input type="text" id="userid" name="userid" class="form-control underline-input" placeholder="User ID">
                        </div>

                        <div class="form-group">
                            <input type="password" id="password" name="password" placeholder="Password" class="form-control underline-input">
                        </div>

                        <div class="form-group text-left mt-20">
                        	<input class="btn btn-greensea b-0 br-2 mr-5" type="submit" value="Log in" />
                            <label class="checkbox checkbox-custom-alt checkbox-custom-sm inline-block">
                                <input type="checkbox"><i></i> Remember me
                            </label>
                            <a target='null' href="inputResetPassword" class="pull-right mt-10">Forgot Password?</a>
                        </div>
                        <!-- Modal -->
		          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Forgot Password ?</h4>
		                      </div>
		                      <div class="modal-body">
                                  <p>Enter the username you use to sign in to your APM Account.<br />
                                    APM will send instructions on how to reset your password to the email address associated with your account.<br />
                                    The instructions will be valid for one day. Username/Email</p>
		                          
		                          <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
		                          <button class="btn btn-theme" type="button">Submit</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->

                    </form>

                </div>
	
	</section><!-- content -->
</div><!-- container -->
	  	
	  </div>

       

       

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
   <%--  <script type="text/javascript" src="_assets/js/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch("_assets/img/login-bg.jpg", {speed: 500});
    </script> --%>

    <%--   <script>
          $(document).ready(function () {
              toggleFullScreen();
          });
          // mozfullscreenerror event handler
          function errorHandler() {
              alert('mozfullscreenerror');
          }
          document.documentElement.addEventListener('mozfullscreenerror', errorHandler, false);

          // toggle full screen
          function toggleFullScreen() {
              if (!document.fullscreenElement &&    // alternative standard method
                  !document.mozFullScreenElement && !document.webkitFullscreenElement) {  // current working methods
                  if (document.documentElement.requestFullscreen) {
                      document.documentElement.requestFullscreen();
                  } else if (document.documentElement.mozRequestFullScreen) {
                      document.documentElement.mozRequestFullScreen();
                  } else if (document.documentElement.webkitRequestFullscreen) {
                      document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
                  }
              } else {
                  if (document.cancelFullScreen) {
                      document.cancelFullScreen();
                  } else if (document.mozCancelFullScreen) {
                      document.mozCancelFullScreen();
                  } else if (document.webkitCancelFullScreen) {
                      document.webkitCancelFullScreen();
                  }
              }
          }

          // keydown event handler
          document.addEventListener('keydown', function (e) {
              if (e.keyCode == 13 || e.keyCode == 70) { // F or Enter key
                  toggleFullScreen();
              }
          }, false);
      </script>
 --%>
  </body>
</html>





