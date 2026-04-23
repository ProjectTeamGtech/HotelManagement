<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YUVICARE</title>

<link rel="icon" href="common/BootstrapNew/img/favicon.ico">
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

</head>
<body>

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
                            <div id="sidebarNav" class="panel-collapse collapse in" role="tabpanel">
                                <div class="panel-body">
                                    
                                    <!-- ===================================================
                            ================= NAVIGATION Content ===================
                            ==================================================== -->
                                    <ul id="navigation">
                                        <li><a href="Bankdeposite" title="Main Dashboard"><i class="fa fa-tachometer" aria-hidden="true"></i> <span>Main Dashboard</span></a></li>
                                        <li><a href="chequeBankdeposite" title="Cheque Details"><i class="fa fa-inr" aria-hidden="true"></i> <span>Cheque Details</span></a></li>
                                    </ul>
                                    <!--/ NAVIGATION Content -->


                                </div>
                            </div>
                        </div>
                    </div>

                </div>
               
            </aside>

</body>
</html>