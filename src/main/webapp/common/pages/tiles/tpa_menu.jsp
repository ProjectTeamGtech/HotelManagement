<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YUVICARE</title>

<link rel="icon" href="common/BootstrapNew/img/favicon.ico">
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="common/Font-Awesome-master/css/font-awesome.min.css" rel="stylesheet" />

<style>
.appWrapper.aside-fixed #sidebar {
    position: absolute;
    top: 33px;
    bottom: 0;
    min-height: auto !important;
}

/* Preloader */
#preloader {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #fff;
  /* change if the mask should have another color then white */
  z-index: 99;
  /* makes sure it stays on top */
}

#status {
  width: 200px;
  height: 200px;
  position: absolute;
  left: 50%;
  /* centers the loading animation horizontally one the screen */
  top: 50%;
  /* centers the loading animation vertically one the screen */
  background-image: url(common/images/hourglass1.gif);
  /* path to your loading animation */
  background-repeat: no-repeat;
  background-position: center;
  margin: -100px 0 0 -100px;
  /* is width and height divided by two */
}
</style>
 <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
</head>
<body class="appWrapper hz-menu">

<div id="preloader">
  <div id="status">&nbsp;</div>
</div>
<% String category=(String)session.getAttribute("category"); 
  if(category==null){
	   category="1";
  }

%>


<aside id="sidebar" class="miheight">
                <div id="sidebar-wrap">
                    <div class="panel-group slim-scroll" role="tablist">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#sidebarNav">
                                        Navigation <i class="fa fa-angle-up"></i>
                                    </a>
                                </h4>
                            </div>
                            <div id="sidebarNav" class="panel-collapse collapse in dropdown" role="tabpanel">
                                <div class="panel-body"> 
                                <div class="col-lg-12 col-md-12 hidden-xs">
                                    <!-- ===================================================
                            ================= NAVIGATION Content ===================
                            ==================================================== -->
                            	<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
		   						<div class="col-lg-1 col-md-1 hidden-xs">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown">
                                             <li style="width: 106% !important;"><a href="#" onclick="opencPopup('Tpa')" title="Main Dashboard"><span>TPA Dashboard</span></a></li>
                                              </li>
                                              </ul>
                                              </div>
                                    <ul id="navigation">
                                    
                                     	<div class="col-lg-1 col-md-1 hidden-xs">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="width: 95%;">
                                            <a href="#" title="Third Parties"><i class="fa fa-users" aria-hidden="true"></i>  Third Parties</a>
                                              <ul>
                                              <li>
                                                
                                                <ul>
                                                    <li><a href="#" onclick="openPopup('showListThirdParty')"><i class="fa fa-caret-right"></i> View Third Party</a></li>
                                                    <li><a href="#" onclick="openPopup('addThirdParty')"><i class="fa fa-caret-right"></i> Add New Third Party</a></li>
                                                    <li><a href="#" onclick="openPopup('GP')"><i class="fa fa-caret-right"></i> View GP</a></li>
                                                    <li><a href="#" onclick="openPopup('allocationOutstandingReport')"><i class="fa fa-caret-right"></i>Allocation Receipt List</a></li>
                                                    <li><a href="#" onclick="openPopup('TPFollower')"><i class="fa fa-caret-right"></i>Third Party Follower</a></li>
                                                </ul>
                                            </li>

                                              </ul>
                                             </li>
                                              </ul>
                                              </div>
                                              </ul>
                                              <ul id="navigation">
                                    
                                     	
                                              </ul>
                                             
                                              </div>
                                              
                                    <!--/ NAVIGATION Content -->
                                    </div>
                                </div></div></div></div>
        
            </aside>

    
    <!--Preloader -->
	<script>
      	$(window).on('load', function() { // makes sure the whole site is loaded 
		  $('#status').fadeOut(); // will first fade out the loading animation 
		  $('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website. 
		  $('body').delay(350).css({'overflow':'visible'});
		})
      </script>
    

</body>
</html>