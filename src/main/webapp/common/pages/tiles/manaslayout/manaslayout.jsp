<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--Mobile first-->
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<title>Yuvicare</title>
</head>
 <link rel="shortcut icon" href=" manaslogin/images/Manas_Yuvicare_Circle Favicon.png">
  <link rel="stylesheet" href="manaslogin/css/bootstrap.min.css">
  <link rel="stylesheet" href="manaslogin/css/styles.css">
  <link rel="stylesheet" href="manaslogin/css/fontawesome-all.css">
  <link href="manaslogin/css/aos.css" rel="stylesheet">
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
  
   <script src="manaslogin/js/jquery.min.js"></script>
    <script src="manaslogin/js/popper.min.js"></script>
    <script src="manaslogin/js/bootstrap.min.js"></script>
    <script src="manaslogin/js/aos.js"></script>
    <script src="manaslogin/js/main.js"></script>
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