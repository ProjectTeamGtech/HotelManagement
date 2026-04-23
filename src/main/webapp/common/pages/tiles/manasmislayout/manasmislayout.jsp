<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
    <title>Manas - Yuvicare MIS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/png" href="manasmis/images/Manas_Yuvicare_Circle Favicon.png">
    <link rel="stylesheet" href="manasmis/css/bootstrap.min.css">
    <link rel="stylesheet" href="manasmis/css/font-awesome.min.css">
    <link rel="stylesheet" href="manasmis/css/themify-icons.css">
    <link rel="stylesheet" href="manasmis/css/metisMenu.css">
    <link rel="stylesheet" href="manasmis/css/owl.carousel.min.css">
    <link rel="stylesheet" href="manasmis/css/slicknav.min.css">
    <!-- amchart css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- others css -->
    <link rel="stylesheet" href="manasmis/css/typography.css">
    <link rel="stylesheet" href="manasmis/css/default-css.css">
    <link rel="stylesheet" href="manasmis/css/styles.css">
  
    <link rel="stylesheet" href="manasmis/css/responsive.css">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <!-- modernizr css -->
    <script src="manasmis/js/vendor/modernizr-2.8.3.min.js"></script>
    
    
    <script src="popupdialog/dialog/js/jquery-1.10.2.js"
	type="text/javascript"></script>
	<!-- <script src="manasmis/js/vendor/jquery-2.2.4.min.js"></script> -->
	<script src="common/BootstrapNew/js/bootstrap.min.js" type="text/javascript"></script> 
	<script src="common/Bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
	<link rel="stylesheet" href="manasopd/assets/css/bootstrap.min.css">
	  <script type="text/javascript" src="common/js/fullscreen.js"></script>
	  <link href="manasmaindashboard/css/oldbootstrapcode.css" rel="stylesheet"
		type="text/css" />
</head>

<style>
    .col-1{
        padding-left: 0px;
        padding-right: 0px;
    }
    li span{
   
        font-size:12px;
        color: black;
        vertical-align: middle;
    }
    li h5{
   
   font-size:12px;
   color:#818181;
   vertical-align: middle;
   display: inline;
}
@media only screen and (min-width:1024px) and (max-width:1500px){
li h5{
    font-size: 8px;
color: black;
vertical-align: middle;
display: inline;
}
}
@media only screen and (min-width:1520px) and (max-width:1900px){
    li h5{
        font-size: 10px;
    }
}
@media only screen and (min-width:768px) and (max-width:1000px){
     li h5{
        font-size: 10px;
    }
}
    a{
        color: #15536E;
    }
    a :hover{
        color:#15536E;
    }
    .highcharts-figure, .highcharts-data-table table {
    min-width: 480px; 
    max-width: 800px;
    margin: 1em auto;
  }
  @media only screen and (max-width:600px){
    .highcharts-figure, .highcharts-data-table table {
    min-width: 300px; 
    max-width: 800px;
    }
    .btn-danger-1 {
        border: none;
        background-color: #e0faff00;
        padding: 7px 8px;
        font-size: 14px;
    }
  }
  @media only screen and (min-width:1024px) and (max-width:1200px){
    .highcharts-figure, .highcharts-data-table table {
    min-width: 400px; 
    max-width: 800px;
    }
  }
  @media only screen and (min-width:1900px){
    .highcharts-figure, .highcharts-data-table table {
    min-width: 700px; 
    max-width: 800px;
    }
  }
  #container {
    height: 300px;
  }
  #container-1 {
    height: 300px;
  }
  
  .highcharts-data-table table {
      font-family: Verdana, sans-serif;
      border-collapse: collapse;
      border: 1px solid #EBEBEB;
      margin: 10px auto;
      text-align: center;
      width: 100%;
      max-width: 500px;
  }
  .highcharts-data-table caption {
    padding: 1em 0;
    font-size: 1.2em;
    color: #555;
  }
  .highcharts-data-table th {
      font-weight: 600;
    padding: 0.5em;
  }
  .highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
    padding: 0.5em;
  }
  .highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even) {
    background: #f8f8f8;
  }
  .highcharts-data-table tr:hover {
    background: #f1f7ff;
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
			<div class="col-lg-12 hidden-print" id="masterbodydiv" style="margin-top: -1px;"></div>
			<div class="col-lg-12" style="padding-left: 0px; padding-right: 0px; margin-top: -5px;">
				<!-- Reder body here -->
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
</body>
<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>