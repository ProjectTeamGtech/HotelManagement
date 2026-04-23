<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

      <link rel="icon" href="common/BootstrapNew/img/favicon.ico">
    <title>YUVICARE</title>

 		<!-- ============================================
        ================= Stylesheets ===================
        ============================================= -->
        <!-- vendor css files -->
          
	      <link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	      <link rel="stylesheet" href="_assets/newtheme/css/vendor/animate.css">
	      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        



        <!-- project main css files -->
        <link rel="stylesheet" href="_assets/newtheme/css/main.css">
        <!--/ stylesheets -->

        <!-- ==========================================
        ================= Modernizr ===================
        =========================================== -->
       
        <script src="_assets/newtheme/js/vendor/modernizr/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <!--/ modernizr -->
        

			<script type="text/javascript" src="diarymanagement/js/diarymanagement.js"></script>

            
     
      
      
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  
   
  <style>
            #header {
                background-color: #43b9be;
            }
           #header .nav-right > li.nav-profile, #header .nav-left > li.nav-profile {
			    height: 39px !important;
			}
			.dropdown-menu {
			    width: 100% !important;
			    padding: 0px 0 !important;
			    margin: -2px -5px 0 !important;
			}
			.dropdown-menu .divider {
			    height: 1px;
			    margin: 0px 0;
			}
			.form-control:hover:not(:disabled) {
			    border-color: #b1bac0;
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
                       <!--  <li class="sidebar-collapse divided-right">
                            <a href="#" class="collapse-sidebar">
                                <i class="fa fa-outdent"></i>
                            </a>
                        </li> -->
                        <s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
                        <li class="dropdown divided-right settings">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                YUVICARE
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
                       
                    </ul>
                    <!-- Left-side navigation end -->


                    <!-- Right-side navigation -->
                    <ul class="nav-right pull-right list-inline">
                    <%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
                        
                    <s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
                        <li class="nav-profile divided-right">
	                        
	                       
                            <a href="#">
                                <span class=""> <img src="liveData/clinicLogo/<s:property value="userImageFileName"/>" height="20" width="60" style="display:none;"> &nbsp; <%=loginInfo.getClinicName() %></span>
                            </a>
                        </li>
                        </s:if>
						<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
						<% if(loginInfo.getUserType()==2){%>
                        <li class="dropdown nav-profile">

                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <span>Admin <i class="fa fa-angle-down"></i></span>
                            </a>

                            <ul class="dropdown-menu animated littleFadeInRight" role="menu">
                              <!--   <li>
                               			 <a href="#" onclick="opencPopup('settingMainDashBoard')" >
                                        <i class="fa fa-user"></i>Profile
                                    </a>   
                                    </li> -->
                                
								<!--  <li>
                                     <a href="#" data-toggle="modal" data-target="#myProfile">
                                        <i class="fa fa-user"></i>Profile
                                    </a>                                   
                                    <a href="#" onclick="openPharmacyUserProfile()">
                                        <i class="fa fa-user"></i>Profile
                                    </a>
                                </li>
                                  -->
                                  <li class="divider"></li>
                                <li>
                                    <a href="Logout">
                                        <i class="fa fa-sign-out"></i>Logout
                                    </a>
                                </li>
                            </ul>

                        </li>
                         <%}else{ %>
                         <li class="dropdown nav-profile">

                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <span>Hi, <%=loginInfo.getFirstName()%> <i class="fa fa-angle-down"></i></span>
                            </a>
                            
                              <script>
                            	function showLogOut(){
                            		document.getElementById('logoutfrm').submit();
                            	}
                            </script>
                            
                            <s:form id="logoutfrm" theme="simple" action="Logout"></s:form>

                            <ul class="dropdown-menu animated littleFadeInRight" role="menu">
                              <li>
                                     <a href="#" onclick="opencPopup('settingMainDashBoard')" >
                                        <i class="fa fa-user"></i>Profile
                                    </a>                                   
                                   
                                </li>
                                <li class="divider"></li> 
                                
                             <%if(!loginInfo.getLoginType().equals("mob")){ %>
                                <li>
                                     <a href="#" onclick="showLogOut()">
                                        <i class="fa fa-sign-out"></i>Logout
                                    </a>
                                </li>
							 <% }%>
                            </ul>
                        </li>
                        <% }%>
                        </s:if>
                        <s:else>
                        		<li class="hidden">
                                    <a href="inputLogin">
                                        <i class="fa fa-sign-in"></i> Login
                                    </a>
                                </li>
                        </s:else>
                        
                        <s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
		                    
		                         <li style="display:none;"><a class="logout tooltips" data-placement="bottom" data-original-title="Lock Screen" href="lock_screen.jsp"><i class="fa fa-desktop"></i></a></li>
		                    
                		</s:if>

                        <li class="toggle-right-sidebar hidden">
                            <a href="#">
                                <i class="fa fa-cog fa-spin"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- Right-side navigation end -->
                </header>

            </section>
            <!--/ HEADER Content  -->
        </div>
        <!--/ Application Content -->
 		<script src="_assets/newtheme/js/vendor/jquery/jquery-1.11.2.min.js"></script> 
     	<script src="common/js/fullscreen.js"></script>
     	
     	
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
   
   
   

<!-- Modal -->
<div id="otpmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Enter OTP</h4>
      </div>
      <div class="modal-body">
        	<input type="text" class="form-control" id="otp" placeholder="Enter OTP" style="background-color: beige;"/>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="confirmotp()" data-dismiss="modal" value="Confirm">
      </div>
    </div>

  </div>
</div>



