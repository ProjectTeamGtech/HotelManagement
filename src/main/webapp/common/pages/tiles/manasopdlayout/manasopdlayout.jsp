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
<script src="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/lib/moment.min.js"></script>
    <!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />
<script src="_assets/newtheme/js/ui.js" type="text/javascript"></script>
<!-- New theme 30 01 2018 -->
<!--IE Compatibility modes-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- New theme 30 01 2018 -->
<script src="common/js/jquery.js" type="text/javascript"></script>
<script src="common/js/jquery.alerts1.js" type="text/javascript"></script>



<link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />
<script src="_assets/newtheme/js/ui.js" type="text/javascript"></script>
<!-- New theme 30 01 2018 -->
<link href="common/css/Style.css" rel="stylesheet" type="text/css" />
<link href="common/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="common/css/responsive.css" rel="stylesheet" type="text/css" />
<link href="common/BootstrapNew/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css" />
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet"
	type="text/css" />	

<!-- <link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet"> -->
	
<link href="common/Font-Awesome-master/css/font-awesome.min.css" rel="stylesheet">


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
	
<script src="common/BootstrapNew/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>


<script src="common/js/jquery/additional-validation-methods.js"
	type="text/javascript"></script>
	
<script src="common/js/jquery/jquery.validate.js" type="text/javascript"></script>


<script type="text/javascript" src="common/js/fullscreen.js"></script>

<script type="text/javascript" src="common/js/masterValidators.js"></script>

<script type="text/javascript" src="common/js/jquery/bootstrap-growl-master/bootstrap-growl.min.js"></script>

<!-- notifications -->
<link rel="stylesheet" type="text/css"
	href="dist/styles/jquery.stickynotif.min.css" />
	
	<script src="dist/jquery.stickynotif.min.js"></script>
<script src="common/blockui-master/jquery.blockUI.js" type="text/javascript"></script>
	
    <link rel="stylesheet" href="manasopd/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="manasopd/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="manasopd/assets/css/themify-icons.css">
    <link rel="stylesheet" href="manasopd/assets/css/metisMenu.css">
    <link rel="stylesheet" href="manasopd/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="manasopd/assets/css/slicknav.min.css">
    <!-- amchart css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- others css -->
    <link rel="stylesheet" href="manasopd/assets/css/typography.css">
    <link rel="stylesheet" href="manasopd/assets/css/default-css.css">
    <link rel="stylesheet" href="manasopd/assets/css/styles.css">
  
    <link rel="stylesheet" href="manasopd/assets/css/responsive.css">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <!-- modernizr css -->
    <script src="manasemr/assets/js/vendor/modernizr-2.8.3.min.js"></script>
    
    
    
    
        <!-- jquery latest version -->
    <script src="manasemr/assets/js/vendor/jquery-2.2.4.min.js"></script>
    
    <!-- bootstrap 4 js -->
    <script src="manasemr/assets/js/popper.min.js"></script>
    <!-- <script src="manasemr/assets/js/bootstrap.min.js"></script> -->
    <script src="manasemr/assets/js/owl.carousel.min.js"></script>
    <script src="manasemr/assets/js/metisMenu.min.js"></script>
    <script src="manasemr/assets/js/jquery.slimscroll.min.js"></script>
    <script src="manasemr/assets/js/jquery.slicknav.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
    <!-- start highcharts js -->
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
    <!-- start zingchart js -->
    <script src="https://cdn.zingchart.com/zingchart.min.js"></script>
    <script>
    zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
    ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"];
    </script>
    <!-- start amchart js -->
    <script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
    <script src="https://www.amcharts.com/lib/3/serial.js"></script>
    <script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
    <script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
    <script src="manasemr/assets/js/line-chart.js"></script>
    <script src="manasemr/assets/js/bar-chart.js"></script>
    <!-- others plugins -->
    <script src="manasemr/assets/js/plugins.js"></script>
    <script src="manasemr/assets/js/scripts.js"></script>
  <script src="bootbox/bootbox.js"></script>
  
    <script>
        $(document).ready(function() {

$(".datepicker").datepicker({
  prevText: '<i class="fa fa-fw fa-angle-left"></i>',
  nextText: '<i class="fa fa-fw fa-angle-right"></i>'
});
});

    </script>    
   <link href="manasmaindashboard/css/oldbootstrapcode.css" rel="stylesheet"
		type="text/css" />
		
<style>
.hidden{
display: none !important;
}
.bootbox-body{
font-size: 14px !important;
text-align: center;
}
.bootbox{
	margin: 195px !important;,	
}
.text-danger{
color: red;
}
</style>

    
<body>
		<div class="row">
			<!-- Render Menu Partial Here -->
			<tiles:insertAttribute name="menu" />
		</div>
	
	<div class="container-fluid" id="">
		<div class="row">

			<div class="col-lg-12" style="padding-left:0px;padding-right:0px;margin-top:-5px;">
				<!-- Reder body here -->
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	
	
	
	
	</body>
	<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
	</html>