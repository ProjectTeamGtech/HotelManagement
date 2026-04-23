
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
<head>


 <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta name="description" content="Responsive Admin Template" />
    <meta name="author" content="RedstarHospital" />
    <title>VIDEO TUTORIAL</title>
	<link rel="shortcut icon" href="nabh/img/tutoriallogo.png" />
    <!-- google font -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=all" rel="stylesheet" type="text/css" />
    
	<!-- icons -->
    <link href="nabh/js/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="nabh/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    
	<!--bootstrap -->
    <link href="nabh/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="nabh/js/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
    
    <!-- Theme Styles -->
    <link href="nabh/css/theme_style.css" rel="stylesheet" id="rt_style_components" type="text/css" />
    <link href="nabh/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <link href="nabh/css/style.css" rel="stylesheet" type="text/css" />
    <link href="nabh/css/responsive.css" rel="stylesheet" type="text/css" />
     <link href="nabh/js/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
      <link href="nabh/js/select2/css/select2.css" rel="stylesheet" type="text/css" />
    
   <script src="nabh/js/jquery.min.js" type="text/javascript"></script>
    <script src="nabh/js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="nabh/js/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="nabh/js/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
    <script src="nabh/js/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
    <script src="nabh/js/counterup/jquery.counterup.min.js" type="text/javascript"></script>
    <script src="nabh/js/jquery.slimscroll.js"></script>
    <script src="nabh/js/app.js" type="text/javascript"></script>
    <script src="nabh/js/layout.js" type="text/javascript"></script>
    <script src="nabh/js/chart-js/Chart.bundle.js" type="text/javascript"></script>
    <script src="nabh/js/chart-js/utils.js" type="text/javascript"></script>
    <script src="nabh/js/chart-js/home-data.js" type="text/javascript"></script>
   <script src="nabh/js/newfafaicon.js" type="text/javascript"></script>
   

      <script src="nabh/js/ui.js"></script>
 <script src="nabh/js/select2/js/select2.js"></script>
 <script src="nabh/js/select2/js/select2-init.js"></script>
 
  <script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<script>
				             $(function() {
								  $('.scrollfixeddown').slimScroll({
								   		height : '625px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
				             
				             $(function() {
								  $('.scrollfixeddown1').slimScroll({
								   		height : '550px',
								   		railVisible: true,
										alwaysVisible: true
								  });
				 				});
 				 
             </script>
 
 
<body class="page-header-fixed sidemenu-closed-hidelogo page-container-bg-solid page-content-white page-md" style="overflow-y: hidden;">
    <div class="page-wrapper">

 
 
  <section id="header">
  	<tiles:insertAttribute name="header" />
  </section>
	
	 
     <div class="clearfix"> </div>
        <!-- start page container -->
            <div class="page-container">
			 
      			<!--sidebar start-->
       			<div id="controls">
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
 
</body>
<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>



<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
