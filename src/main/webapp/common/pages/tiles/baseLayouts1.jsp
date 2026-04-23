
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
<!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />
<script src="_assets/newtheme/js/ui.js" type="text/javascript"></script>
<!-- New theme 30 01 2018 -->
<link href="common/css/Style.css" rel="stylesheet" type="text/css" />
<link href="common/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="common/css/responsive.css" rel="stylesheet" type="text/css" />
 

<script src="common/js/jquery.js" type="text/javascript"></script>
<script src="common/js/jquery.alerts.js" type="text/javascript"></script>
<link href="common/css/jquery.alerts.css" rel="stylesheet"
	type="text/css" media="screen" />

<link href="common/BootstrapNew/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css" />
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet"
	type="text/css" />	
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
<!-- <link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet"> -->
	
<link href="common/Font-Awesome-master/css/font-awesome.min.css" rel="stylesheet">


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
	

<script type="text/javascript">
<%String apmuserlist = (String)session.getAttribute("apmuserlist");%>
apmuserlist = '<%=apmuserlist%>'
//alert(apmuserlist)
	
</script>

 


<!-- <link rel="shortcut icon" href=" manaslogin/images/Manas_Yuvicare_Circle Favicon.png"> -->

<title>YUVICARE</title>
<style type="text/css">





.manascommheader{
  padding: 6px;
    font-size: 20px;
    text-transform: uppercase;
    display: flex;
    border-bottom: 2px solid #5fa2af;
    background: #ecfcff;
}

.manascommheader h4,h3{
font-weight: bold;
font-size: 17px;
font-family: 'Open Sans', sans-serif !important;
color: #266d71;
}
.manascommheader img{
margin-right: 6px !important;
}
</style>
</head>
<body style="height:500px !important;" >
	<%-- <div class="container-fluid">
		<div class="row">
			<!-- Render Menu Partial Here -->
			<tiles:insertAttribute name="menu" />
		</div>
	</div> --%>
	<!-- <div class="container-fluid" id="masterbodycontainer"> -->
	<div class="container-fluid" id="masterbodycontainer">
		<div class="row">
			
			<!-- <div class="col-lg-12 paddniltopase"> -->
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " >
				<!-- Reder body here -->
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>


	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93) !important;" id="baselayout1loaderPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	 </div>
</body>
<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>


	</div>	
