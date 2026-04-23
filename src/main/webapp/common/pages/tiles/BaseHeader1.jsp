<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
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
        <link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="_assets/newtheme/css/vendor/animate.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="_assets/newtheme/js/vendor/animsition/css/animsition.min.css">


        <!-- project main css files -->
        <link rel="stylesheet" href="_assets/newtheme/css/main.css">
        <!--/ stylesheets -->

        <!-- ==========================================
        ================= Modernizr ===================
        =========================================== -->
        <script src="_assets/newtheme/js/vendor/modernizr/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <!--/ modernizr -->
  
    <style>
            #header {
			    background-color: #43b9be;
			    width: 101%;
			}
           
            .calennw {
			    margin-top: 25px !important;
			    margin-right: -5px !important;
			    margin-left: 22px !important;
			}
        </style>
</head>

  <body>
  
  
   <div >
            <!-- ===============================================
            ================= HEADER Content ===================
            ================================================ -->
            <section id="header">
                <header class="clearfix">
                    <!-- Left-side navigation -->
                    <!-- <ul class="nav-left pull-left list-unstyled list-inline">
                        <li class="sidebar-collapse divided-right">
                            <a href="#" class="collapse-sidebar">
                                <i class="fa fa-outdent"></i>
                            </a>
                        </li>
                    </ul> -->
                    <!-- Left-side navigation end -->


                    <!-- Right-side navigation -->
                    <%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
                    <ul class="nav-right pull-right list-inline">
                    <s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
                        <li class="nav-profile">
                            <a href="#">
                                <span class=""><%=loginInfo.getClinicName() %></span>
                            </a>
                        </li>
                        </s:if>
                        
						<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
						<% if(loginInfo.getUserType()==2){%>
                       <%--  <li class="dropdown nav-profile">

                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <span>Admin <i class="fa fa-angle-down"></i></span>
                            </a>

                            <ul class="dropdown-menu animated littleFadeInRight" role="menu">
                                <li>
                                    <a href="Logout">
                                        <i class="fa fa-sign-out"></i>Logout
                                    </a>
                                </li>

                            </ul>

                        </li> --%>
                         <%}else{ %>
                         <li class="dropdown nav-profile">

                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <span>Hi,Client <i class="fa fa-angle-down"></i></span>
                            </a>

                            <ul class="dropdown-menu animated littleFadeInRight" role="menu">
                             <%if(!loginInfo.getLoginType().equals("mob")){ %>
                                <li>
                                    <a href="Logout">
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
                        
                       <%--  <s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
		                    
		                         <li style="display:none;"><a class="logout tooltips" data-placement="bottom" data-original-title="Lock Screen" href="lock_screen.jsp"><i class="fa fa-desktop"></i></a></li>
		                    
                		</s:if> --%>

                        <!-- <li class="toggle-right-sidebar">
                            <a href="#">
                                <i class="fa fa-cog"></i>
                            </a>
                        </li> -->
                    </ul>
                    <!-- Right-side navigation end -->
                </header>

            </section>
            <!--/ HEADER Content  -->
        </div>
  