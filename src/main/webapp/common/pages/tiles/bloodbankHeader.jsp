<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">

      <link rel="icon" href="common/BootstrapNew/img/favicon.ico">
    <title>YUVICARE</title>
<!-- New theme 30 01 2018 -->
<link href="_assets/newtheme/css/mbile.css" rel="stylesheet" type="text/css" />
<script src="_assets/newtheme/js/ui.js" type="text/javascript"></script>
<!-- New theme 30 01 2018 -->
 		<!-- ============================================
        ================= Stylesheets ===================
        ============================================= -->
        <!-- vendor css files -->
          <link href="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/lib/cupertino/jquery-ui.min.css" rel="stylesheet" />
	      <link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	      <link href="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/fullcalendar.min.css" rel="stylesheet" />
	      <link href="common/BootstrapNew/gritter/css/jquery.gritter.css" rel="stylesheet" />
	      <link rel="stylesheet" href="_assets/newtheme/css/vendor/animate.css">
	      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- <link rel="stylesheet" href="_assets/newtheme/js/vendor/animsition/css/animsition.min.css"> -->


        <!-- project main css files -->
        <link rel="stylesheet" href="_assets/newtheme/css/main.css">
        <!--/ stylesheets -->

        <!-- ==========================================
        ================= Modernizr ===================
        =========================================== -->
        <script src="_assets/newtheme/js/vendor/modernizr/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <!--/ modernizr -->



    

     
      
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  <%--  <script src="common/BootstrapNew/bootstrap/js/bootstrap.min.js"></script> --%>
   
  <style>
            #header {
                background-color: #43b9be;
            }
            .texthead {
                font-size: 15px;
            }
        </style>
   
  </head>

  <body>
  
  
  
   <!-- ====================================================
        ================= Application Content ===================
        ===================================================== -->
        <div id="wrap" class="animsition">
            <!-- ===============================================
            ================= HEADER Content ===================
            ================================================ -->
            <section id="header">
                <header class="clearfix">
                    <!-- Left-side navigation -->
                    <ul class="nav-left pull-left list-unstyled list-inline">
                       
                        <%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
                        <s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
                        <li class="dropdown divided-right settings hidden">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-map-signs"></i>
                            </a>
                            <ul class="dropdown-menu with-arrow animated littleFadeInUp" role="menu">
                                <li>
                                    <a href="#" onclick="opencPopup('abtusDefault')">About Us</a>
                                </li>
                                <li>
                                    <a href="#">care@escapeq.com</a>
                                </li>
                            </ul>
                        </li>
                        </s:if>
                       <!--  <li class="divided-right">
                            <img src="dashboardicon/blood-donation.png" class="img-responsive" style="width: 30px;margin-top: 7px;margin-bottom: 7px;">
                            <img src="manasmaindashboard/images/Blood Bank.png" style="filter: brightness(5);width: 55%;margin-top: 8px;margin-left: 7px;">
                        </li>
                        <li class="divided-right" style="font-size: 15px;color: #fff;padding-top: 10px;">
                            Blood Bank
                        </li>  -->
                         
                    </ul>
	                <div class="row details">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
							 	<img src="manasmaindashboard/images/Blood Bank.png" style="filter: brightness(5);margin-left: 7px;">
							 	<h4>Blood Bank</h4>
							</div>
						</div>
					</div>
                    <!-- Right-side navigation end -->
                </header>
            </section>
            <!--/ HEADER Content  -->
        </div>
        <!--/ Application Content -->
 		<%--  <script src="_assets/newtheme/js/vendor/jquery/jquery-1.11.2.min.js"></script>
        
     	<script src="_assets/newtheme/js/vendor/bootstrap/bootstrap.min.js"></script>
       	<script src="_assets/newtheme/js/vendor/jRespond/jRespond.min.js"></script>

        <script src="_assets/newtheme/js/vendor/sparkline/jquery.sparkline.min.js"></script>

        <script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
      
        <script src="_assets/newtheme/js/vendor/animsition/js/jquery.animsition.min.js"></script> --%>
      
       <%--  <script src="_assets/newtheme/js/main.js"></script> --%>
 <!-- <div class="modal fade" id="app" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                <img src="aboutus/app3.png" class="img-responsive"/>
                <a href="http://www.myapm.co.uk:8080/MyApm.apk"><img src="aboutus/androidapp.png" class="img-responsive apppop"/></a>
                </div>
            </div>
        </div>
    </div> -->
   
