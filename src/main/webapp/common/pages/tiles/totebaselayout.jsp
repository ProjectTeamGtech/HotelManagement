
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
  <title>
  <%if(loginfo.isPayrollaccess()){ %>
  Payroll
  <%}else{ %>
  MY HR
  <%} %>
  </title>
  <!-- <link rel="shortcut icon" type="image/x-icon" href="payrollnew/assets/images/favicon.ico">
  <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/fullcalendar.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/select2.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/bootstrap-datetimepicker.min.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/plugins/morris/morris.css">
  <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/style.css"> -->
  
<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script src="common/Bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>

<script src="common/js/jquery.js" type="text/javascript"></script>
<script src="common/js/jquery.alerts.js" type="text/javascript"></script>
<!-- <link href="common/css/jquery.alerts.css" rel="stylesheet"
	type="text/css" media="screen" /> -->
<script src="common/js/jquery/additional-validation-methods.js"
	type="text/javascript"></script>
	
<script src="common/js/jquery/jquery.validate.js" type="text/javascript"></script>


<script type="text/javascript" src="common/js/fullscreen.js"></script>

<script type="text/javascript" src="common/js/masterValidators.js"></script>

<script type="text/javascript" src="common/js/jquery/bootstrap-growl-master/bootstrap-growl.min.js"></script>
<script src="common/Bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
	<!-- <link href="/SomeProject/static/bundle-bundle_bootstrap_head.css" type="text/css" rel="stylesheet" media="screen, projection">
<link rel="stylesheet" type="text/css" href="print.css" media="print" /> -->
<style type="text/css">
.hidden{
display: none;
}

</style>
</head>
  <body>
  
    <div class="main-wrapper ">
 
       <div class="header1">
       	<tiles:insertAttribute name="header" />
       </div>
         <div class="page-wrapper" style="font-size: 20px;font-weight: bolder;"> 
	     <div class="content container-fluid">
	    <tiles:insertAttribute name="body" />
	   </div>
       </div>
       <div class="sidebar1" id="">
       
         <div class="sidebar-inner ">
					<div id="" class="sidebar-menu">
						
  							
  	<!-- <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 "  >
  	 <img class="img-responsive logoste1" src="liveData/clinicLogo/NABH.png" width="100" height="100"/> 
	
	</div> -->
					</div>
					
                </div>
     
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2" style="margin-top: 650px; ">
	   <div class="content container-fluid">
	    <tiles:insertAttribute name="footer" />
	  
       </div>
       </div>
       </div>
        
     
    </div>
    
    <div class="sidebar-overlay noprint" data-reff="#sidebar"></div>
   <!--  <script type="text/javascript" src="payrollnew/assets/js/jquery-3.2.1.min.js"></script> -->
   <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
   <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
    <script type="text/javascript" src="payrollnew/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/jquery.slimscroll.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/select2.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/moment.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/plugins/morris/morris.min.js"></script>
    <script type="text/javascript" src="payrollnew/assets/js/app.js"></script>
    
   <!--  <script type="text/javascript">
    function changesubdrop(val) {
    	 var element = document.getElementById(""+val);
    	   element.classList.remove("subdrop");
    	/* document.getElementById(""+val).className="subdrop"; */
   document.getElementById(""+val).className += " subdrop";
    }
    </script> -->
  </body>
<!--   <link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" /> -->
</html>
