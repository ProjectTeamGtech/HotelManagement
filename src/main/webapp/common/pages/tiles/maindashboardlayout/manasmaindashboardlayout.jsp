<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--Mobile first-->
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<title>Yuvicare</title>
</head>
  	 <link rel="shortcut icon" type="image/png" href="manasemr/assets/images/Manas_Yuvicare_Circle Favicon.png">
    <link rel="stylesheet" href="manasmaindashboard/css/bootstrap.min.css">
    <link rel="stylesheet" href="manasmaindashboard/css/font-awesome.min.css">
    <link rel="stylesheet" href="manasmaindashboard/css/themify-icons.css">
    <link rel="stylesheet" href="manasmaindashboard/css/metisMenu.css">
    <link rel="stylesheet" href="manasmaindashboard/css/owl.carousel.min.css">
    <link rel="stylesheet" href="manasmaindashboard/css/slicknav.min.css">
    <!-- amchart css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- others css -->
    <link rel="stylesheet" href="manasmaindashboard/css/typography.css">
    <link rel="stylesheet" href="manasmaindashboard/css/default-css.css">
    <link rel="stylesheet" href="manasmaindashboard/css/styles.css">
    <link rel="stylesheet" href="manasmaindashboard/css/responsive.css">
   
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <!-- modernizr css -->
    <script src="manasmaindashboard/js/vendor/modernizr-2.8.3.min.js"></script><link rel="shortcut icon" type="image/png" href="manasmaindashboard/images/icon/favicon.ico">
    <link rel="stylesheet" href="manasmaindashboard/css/bootstrap.min.css">
    <link rel="stylesheet" href="manasmaindashboard/css/font-awesome.min.css">
    <link rel="stylesheet" href="manasmaindashboard/css/themify-icons.css">
    <link rel="stylesheet" href="manasmaindashboard/css/metisMenu.css">
    <link rel="stylesheet" href="manasmaindashboard/css/owl.carousel.min.css">
    <link rel="stylesheet" href="manasmaindashboard/css/slicknav.min.css">
    <!-- amchart css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- others css -->
    <link rel="stylesheet" href="manasmaindashboard/css/typography.css">
    <link rel="stylesheet" href="manasmaindashboard/css/default-css.css">
    <link rel="stylesheet" href="manasmaindashboard/css/styles.css">
    <link rel="stylesheet" href="manasmaindashboard/css/responsive.css">
    <script type="text/javascript" src="common/js/fullscreen.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <!-- modernizr css -->
    <script src="manasmaindashboard/js/vendor/modernizr-2.8.3.min.js"></script>
    <script src="diarymanagement/js/diarymanagement.js"></script>
    
    <!-- From maindashboard layout js and css  -->
    <script src="common/js/jquery.js" type="text/javascript"></script>
	<script src="common/js/jquery.alerts.js" type="text/javascript"></script>
	<link href="common/css/jquery.alerts.css" rel="stylesheet"
		type="text/css" media="screen" />
		
	<!-- <link href="common/Bootstrap/css/bootstrap-theme.min.css"
		rel="stylesheet" type="text/css" />
	<link href="common/Bootstrap/css/bootstrap.min.css" rel="stylesheet"
		type="text/css" />  -->
	
	<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet"
		type="text/css" />	
		<script src="popupdialog/dialog/js/jquery-1.10.2.js"
	type="text/javascript"></script>
	<script src="popupdialog/dialog/js/jquery-ui.min.js"
		type="text/javascript"></script>
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
	<script src="popupdialog/dialog/js/jquery.ui.tooltip.js"></script>
	<script src="common/chosen_v1.1.0/chosen.jquery.js"
		type="text/javascript"></script>
		<script src="common/Bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
	<script src="common/js/jquery/additional-validation-methods.js"
		type="text/javascript"></script>
	<script src="common/js/jquery/jquery.validate.js" type="text/javascript"></script>
	<script type="text/javascript" src="common/js/fullscreen.js"></script>
	<script type="text/javascript" src="common/js/masterValidators.js"></script>
	<script type="text/javascript" src="common/js/jquery/bootstrap-growl-master/bootstrap-growl.min.js"></script>
   
   <link href="manasmaindashboard/css/oldbootstrapcode.css" rel="stylesheet"
		type="text/css" />

<%-- <body class="page-header-fixed sidemenu-closed-hidelogo page-container-bg-solid page-content-white page-md">
    <div class="page-wrapper">
	  <section id="header">
	  	<tiles:insertAttribute name="header" />
	  </section>
	  <div class="clearfix"> </div>
      <div class="page-container">
			<!--sidebar start-->
       		<div id="controls" >
           		<tiles:insertAttribute name="menu" />
           	</div>
			<section id="content">
            	<tiles:insertAttribute name="body" />
         	</section>
	 </div>
     <section id="content">
     	<tiles:insertAttribute name="footer" />
     </section>
	</div>
</body> --%>
<style>
.hidden{
display: none !important;
}
</style>
<body>
<div class="container-fluid">
		<div class="row">
			<!-- Render Menu Partial Here -->
			<tiles:insertAttribute name="menu" />
		</div>
	</div>
	
	<div class="container-fluid" id="masterbodycontainer">
		<div class="row">
			<div class = "col-lg-12 hidden-print" id="masterbodydiv" style="margin-top: -1px;">
			
			</div>
			<div class="col-lg-12" style="padding-left:0px;padding-right:0px;margin-top:-5px;">
				<!-- Reder body here -->
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	
	
	</body>
	<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
	</html>