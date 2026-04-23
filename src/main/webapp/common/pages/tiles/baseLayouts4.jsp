
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--Mobile first-->
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />
<script src="_assets/newtheme/js/ui.js" type="text/javascript"></script>
<!-- New theme 30 01 2018 -->
<!--IE Compatibility modes-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="common/css/Style.css" rel="stylesheet" type="text/css" />
<link href="common/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="common/css/responsive.css" rel="stylesheet" type="text/css" />
<link href="common/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />
<link href="common/Bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<link href="common/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet" type="text/css" />
<link href="common/Font-Awesome-master/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="dist/styles/jquery.stickynotif.min.css" />

<script src="common/js/jquery.js" type="text/javascript"></script>
<script src="common/js/jquery.alerts.js" type="text/javascript"></script>
<script src="popupdialog/dialog/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="popupdialog/dialog/js/jquery-ui.min.js" type="text/javascript"></script>
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
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
	
<script src="common/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>


<script src="common/js/jquery/additional-validation-methods.js" type="text/javascript"></script>
	
<script src="common/js/jquery/jquery.validate.js" type="text/javascript"></script>


<script type="text/javascript" src="common/js/fullscreen.js"></script>

<script type="text/javascript" src="common/js/masterValidators.js"></script>

<script type="text/javascript" src="common/js/jquery/bootstrap-growl-master/bootstrap-growl.min.js"></script>
<script src="common/BootstrapNew/js/jquery.scrollTo.min.js"></script>
      <script src="common/BootstrapNew/js/jquery.nicescroll.js"></script>
<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<!-- notifications -->

	
<script src="dist/jquery.stickynotif.min.js"></script>
<script src="common/blockui-master/jquery.blockUI.js" type="text/javascript"></script>
	
	



 


<link rel="shortcut icon" href=" manaslogin/images/Manas_Yuvicare_Circle Favicon.png">

<title>YUVICARE</title>
<style type="text/css">
#main-content{
margin-right: -11px !important;
    margin-left: -26px !important;
}

</style>
</head>
<body>


	<div class="container-fluid">
		<div class="row">
			<!-- Render Menu Partial Here -->
			<tiles:insertAttribute name="header" />
		</div>
	</div>
	<div class="container-fluid" id="masterbodycontainer">
	<!-- 	<div class="row"> -->
			<div class = "col-lg-12 hidden-print" style="padding-top:0px;" id="masterbodydiv">
			
			</div>
			
				<!-- Reder body here -->
				
				   <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
    <%--   <aside>
          <div id="sidebar" class="nav-collapse " style="overflow-y:scroll ">
              <!-- sidebar menu start-->
              <ul id="leftNavigation" >
					 <tiles:insertAttribute name="menu" />
			</ul>
              <!-- sidebar menu end-->
          </div>
      </aside> --%>
      <!--sidebar end-->
				
				
				<section id="main-content">
          <section class="wrapper">
              <div class="row mt">
                  <aside class="col-lg-12 mt">
                             <tiles:insertAttribute name="body" />
                  </aside>
              </div>
          </section>
          </section>
				
				
				
				
				
			
		<!-- </div> -->
	</div>

<!-- 
	<div class="width-full text-center pull-left hidden-print" style="color: #fff; padding: 15px 0; background: black">
		Footer Goes here
		@CBS Tech
	</div> -->
	 
</body>
<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>

