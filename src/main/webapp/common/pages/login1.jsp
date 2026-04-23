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

   		<link href="_assets/newtheme/css/responsive.css" rel="stylesheet">
   
    <!-- Custom styles for this template -->
   
   <!--  <link href="_assets/css/style-responsive.css" rel="stylesheet">  -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
   
<script type="text/javascript" src="common/js/login.js"></script> 

<script>

function sethi(ob){
	ob.value='';
	alert('Paste');
}
window.onload = function () {
	
	 var alen = history.length; 
	   history.go(-alen); 
	   localStorage.clear();
	   sessionStorage.clear();
	   
	   
	   var res = document.cookie;
       var multiple = res.split(";");
       for(var i = 0; i < multiple.length; i++) {
          var key = multiple[i].split("=");
          document.cookie = key[0]+" =; expires = Thu, 01 Jan 1970 00:00:00 UTC";
       }
}
</script>
<style>
/*-- Index-Page-Styling --*/
.container {
    width: 100%;
    margin: 0 auto;
}

.content-left, .content-right {
    width: 50%;
    float: left;
}

.content-left {
    position: relative;
}



.content-left p {
    position: absolute;
    top: 40%;
    left: 48%;
    -ms-transform: translate(-40%, -40%);
    transform: translate(-40%, -40%);
    color: #FFF;
    font-family: 'Montserrat', sans-serif;
    font-size: 30px;
    line-height: 45px;
    width: 80%;
}

.content-left a.more {
    position: absolute;
    top: 60%;
    left: 32.7%;
    -ms-transform: translate(-32.7%, -60%);
    transform: translate(-50%, -60%);
    padding: 13px 40px;
    border-radius: 3px;
    font-size: 16px;
    font-family: 'Montserrat', sans-serif;
    font-weight: bold;
    /*--w3layouts--*/
    /*--agileits--*/
    color: #2a2344;
    background-color: #FFF;
}




#wrapper {
    width: 100%;
    position: relative;
}

#wrapper h2 {
    font-size: 30px;
    margin-bottom: 30px;
}
#register, #login{
       position: absolute;
    padding: 70px;
    background: #efefef;
    height: 674px;
    width: 100%;
}
.p-15 {
    padding: 62px !important;
    box-shadow: 7px 11px 18px 2px;
}
.martop8{
margin-top:8px;
font-weight: bold;
}




</style>
  </head>
  <body>
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
<span class="error" style="text-align: center;"><s:actionerror id="server_side_error" /></span>
	  <div id="login-page">
	  <div class="w3layouts agileits">
		<div class="content-left w3layouts agileits hidden-xs hidden-sm">
			<img src="dashboard/images/background1.jpg" alt="image" class="img-responsive hisbackimg">
			<p>Welcome To Healthcare Information System, A Smart System That Manage Your Hospital On Single Click.</p>
		</div>
		<div class="content-right w3layouts agileits">
			<section>
				<div id="container_demo">
					<a class="hiddenanchor w3layouts agileits" id="tologin"></a>
					<a class="hiddenanchor w3layouts agileits" id="toregister"></a>
					<div id="wrapper">
						<div id="register" class="animate w3layouts agileits form">
						<section id="content">
	  <div class="container p-15 bg-white text-center shadowbox">
                    <h2 class="text-light" style="color: #6699cc;">Please Login</h2>
					<form action="Login" id="login_form" method="post" class="mt-20">
					<input type="hidden" name="actionType" value="0"/>
						<div class="selectdb centered hidden">
			                  <p>Select Database</p>
			                  <button class="btn btn-primary" type="button"><input type="radio" id="dbType" name="dbType" value="0" checked="checked"/> 
									<label for="payBuy"></label> <i class="fa fa-database"></i> Live DB
									</button>
			                  <button class="btn btn-primary" type="button"><input type="radio" id="payBuy1" name="dbType" value="1">
			                  <label for="payBuy"></label><i class="fa fa-database"></i> Demo DB</button>
			              </div>
                        <div class="form-group text-left">
                        <label for="payBuy" class="martop8">User ID</label>
                            <input type="text" id="userId" name="userId" class="form-control underline-input" placeholder="User ID">
                        </div>
                        <div class="form-group text-left">
                         <label for="payBuy" class="martop8">Password</label>
                            <input type="password" id="password" name="password" placeholder="Password" class="form-control underline-input" onpaste='return false'>
                        </div>
                        <div class="form-group text-left mt-20">
                        	<input class="btn btn-primary mr-5" type="submit" value="Log in" />
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
						
						
						
							
						</div>
						
					</div>
				</div>
			</section>
		</div>
		<div class="clear"></div>

	</div>
	  
	  	


	  	
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





