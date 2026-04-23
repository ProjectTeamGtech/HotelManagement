
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
<!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />
<script src="_assets/newtheme/js/ui.js" type="text/javascript"></script>
<!-- New theme 30 01 2018 -->
<!--IE Compatibility modes-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="common/css/Style.css" rel="stylesheet" type="text/css" />
<link href="common/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="common/css/responsive.css" rel="stylesheet" type="text/css" />

<script src="common/js/jquery.js" type="text/javascript"></script>
<script src="common/js/jquery.alerts.js" type="text/javascript"></script>
<link href="common/css/jquery.alerts.css" rel="stylesheet"
	type="text/css" media="screen" />

<link href="common/Bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css" />
<link href="common/Bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet"
	type="text/css" />	

<!-- <link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet"> -->
	
<link href="common/Font-Awesome-master/css/font-awesome.min.css" rel="stylesheet">

<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
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

<!-- notifications -->
<link rel="stylesheet" type="text/css"
	href="dist/styles/jquery.stickynotif.min.css" />
	
	<script src="dist/jquery.stickynotif.min.js"></script>
<script src="common/blockui-master/jquery.blockUI.js" type="text/javascript"></script>
	

<script type="text/javascript">
<%String apmuserlist = (String)session.getAttribute("apmuserlist");%>
apmuserlist = '<%=apmuserlist%>'
//alert(apmuserlist)
	
</script>

 


<link rel="shortcut icon" href=" manaslogin/images/Manas_Yuvicare_Circle Favicon.png">

<title>YUVICARE</title>
<style type="text/css">
</style>
</head>
<body style="overflow-x:hidden">


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

	<!-- <div class="width-full text-center pull-left hidden-print" style="color: #fff; padding: 15px 0; background: black">
		Footer Goes here
		@CBS Tech
	</div> -->
	 
</body>
<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>

