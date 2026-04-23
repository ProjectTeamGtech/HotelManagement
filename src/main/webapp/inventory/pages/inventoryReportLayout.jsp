
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--Mobile first-->
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">

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

<script src="common/js/jquery.js" type="text/javascript"></script>
<script src="common/js/jquery.alerts.js" type="text/javascript"></script>
<link href="common/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />


<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />
<link href="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/lib/cupertino/jquery-ui.min.css" rel="stylesheet" />

<!-- vendor css files -->
<link rel="stylesheet" href="_assets/newtheme/css/vendor/animate.css">
<link rel="stylesheet" href="_assets/newtheme/css/vendor/font-awesome.min.css">
<!-- <link rel="stylesheet" href="_assets/newtheme/js/vendor/animsition/css/animsition.min.css"> -->
<!-- project main css files -->

<link rel="stylesheet" type="text/css" href="dist/styles/jquery.stickynotif.min.css" />
<link rel="stylesheet" type="text/css" href="_assets/newtheme/js/vendor/preloader/css/Circleloader.css" />


     
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
    
 

<!-- 
    <script src="_assets/newtheme/js/vendor/preloader/js/Circleloader.js"></script>
 	

--><!--/ modernizr -->
<style>
   /*  #header {
        background-color: #339966;
            }
    .texthead {
           font-size: 15px;
            } */
#header {
	background-color: #43b9be;
}
.texthead {
    font-size: 15px;
}
.table.table {
    margin-top: 0 !important;
    margin-bottom: 0 !important;
    color: RGBA(85, 85, 85, 0.85);
    border: 1px solid #efefef;
}

.appWrapper.header-fixed #header {
	position: fixed !important;
	height: auto !important;
}

#vvch {
	max-width: 88%;
	float: right;
}

.sssw {
	max-width: 12%;
	float: left;
	position: absolute;
}

#menutt {
	height: auto;
	min-height:100vh;
	
	
	
	/* overflow: hidden; */
	/* background: #43b9be;
 */	background:rgb(206 236 242); */
	box-shadow: 2px 0 32px rgba(0, 0, 0, 0.05);
	-webkit-transition: all 0.3s ease 0s;
	transition: all 0.3s ease 0s;
}



.headerVch {
	width: 100%;
}

body{
font-family: 'Open Sans', sans-serif !important;

}




.sidenav {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  padding-left:0px;
  overflow-x: hidden;
  transition: 0.5s;
 
}

.sidenavx  a{
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>

</head>
<%-- <body id="" >

<div id="demo-content">
<!--<div id="loader-wrapper">
            <div id="loader"></div>
            <div class="loader-section section-left"></div>
            <div class="loader-section section-right"></div>
        </div>
--><div id="">
  <section id="">
  	<tiles:insertAttribute name="header" />
  </section>
	
	<div id="masterbodycontainer">
	<!-- 	<div class="row"> -->
			<div class = "col-lg-12 hidden-print" style="padding-top:0px;" id="">
			
			</div>
      			<!--sidebar start-->
       			<div id="" class="hidden">
           			<tiles:insertAttribute name="menu" />
           			
           			
				</div>
				
				<section id="">
                     <tiles:insertAttribute name="body" />
         		 </section>
			
		<!-- </div> -->
	</div>


	 </div>
	</div>
</body> --%>

<body>

<!--  HEADER-->
	<section class='headerVch hidden-print'>
		<tiles:insertAttribute name="header" />
	</section>
	
	
<!--  DOWN SIDE-->	
	<div id='maincnt'>
	

		<!--  SIDE MENU-->
		<div   id="mySidenav" class="sidenav">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<section id='menutt' >
			<tiles:insertAttribute name="menu" />
		</section>
		</div>
		
		
		<!-- MAIN BODY-->
		<section  class=''>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
			<tiles:insertAttribute name="body" />
			</div>
		</section>
		
		
		
	</div>
</body>

<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>

<script>

</script>


<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
