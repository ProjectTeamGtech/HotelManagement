<!doctype html>
<html class="no-js" lang=""> 



    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>YUVICARE</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">




        <!-- ============================================
        ================= Stylesheets ===================
        ============================================= -->
        <!-- vendor css files -->
        <link rel="stylesheet" href="mis/assets/css/vendor/animate.css">
        <link rel="stylesheet" href="mis/assets/css/vendor/font-awesome.min.css">
        <link rel="stylesheet" href="mis/assets/js/vendor/animsition/css/animsition.min.css">
        <link rel="stylesheet" href="mis/assets/js/vendor/morris/morris.css">
        <link rel="stylesheet" href="mis/assets/js/vendor/rickshaw/rickshaw.min.css">
        <link rel="stylesheet" href="mis/assets/js/vendor/daterangepicker/daterangepicker-bs3.css">
        <link rel="stylesheet" href="mis/assets/js/vendor/datetimepicker/css/bootstrap-datetimepicker.min.css">
       
       
       
       


        <!-- project main css files -->
        <link rel="stylesheet" href="mis/assets/css/main.css">
        <!--/ stylesheets -->



        <!-- ==========================================
        ================= Modernizr ===================
        =========================================== -->
        <script src="mis/assets/js/vendor/modernizr/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <!--/ modernizr -->




    </head>





    <body id="minovate" class="appWrapper">

        <!-- ====================================================
        ================= Application Content ===================
        ===================================================== -->
        <div id="wrap" class="animsition">

            <!-- ===============================================
            ================= HEADER Content ===================
            ================================================ -->
            <section id="header">
                <header class="clearfix">

                    <!-- Branding -->
                    <div class="branding">
                        <a class="brand">
                            <span><strong>MIS</strong></span>
                        </a>
                    </div>
                    <!-- Branding end -->
                    <!-- Right-side navigation -->
                    <ul class="nav-right pull-right list-inline">
                        <li>
                            <div class="pageheader">
                                <div class="page-bar">
                                    <div class="page-toolbar">
                                        <a role="button" tabindex="0" class="btn btn-lightred no-border pickDate">
                                            <i class="fa fa-calendar"></i>&nbsp;&nbsp;<span></span>&nbsp;&nbsp;<i class="fa fa-angle-down"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <!-- Right-side navigation end -->
                </header>

            </section>
            <!--/ HEADER Content  -->


            <!-- ====================================================
            ================= CONTENT ===============================
            ===================================================== -->
            <section id="content">

               

                <div class="page page-charts">
                    <!-- row -->
                    <div class="row hidden">
                        <!-- col -->
                        <div class="col-md-12">

                            <!-- tile -->
                            <section class="tile">

                                <!-- tile header -->
                                <div class="tile-header dvd dvd-btm">
                                    <h1 class="custom-font"><strong>Morris.js </strong>Charts</h1>
                                    <ul class="controls">
                                        <li class="dropdown">

                                            <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                <i class="fa fa-cog"></i>
                                                <i class="fa fa-spinner fa-spin"></i>
                                            </a>

                                            <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-toggle">
                                                        <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                        <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-refresh">
                                                        <i class="fa fa-refresh"></i> Refresh
                                                    </a>
                                                </li>
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-fullscreen">
                                                        <i class="fa fa-expand"></i> Fullscreen
                                                    </a>
                                                </li>
                                            </ul>

                                        </li>
                                        <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                    </ul>
                                </div>
                                <!-- /tile header -->

                                <!-- tile body -->
                                <div class="tile-body">

                                    <div class="row">

                                        <div class="col-md-3">

                                            <h4 class="custom-font"><strong>Line</strong> chart</h4>
                                            <div id="line-example" style="height: 250px;"></div>

                                        </div>

                                        <div class="col-md-3">

                                            <h4 class="custom-font"><strong>Line Area</strong> chart</h4>
                                            <div id="line-area-example" style="height: 250px;"></div>


                                        </div>

                                        <div class="col-md-3">

                                            <h4 class="custom-font"><strong>Bar</strong> chart</h4>
                                            <div id="bar-example" style="height: 250px;"></div>

                                        </div>

                                        <div class="col-md-3">

                                            <h4 class="custom-font"><strong>Donut</strong> chart</h4>
                                            <div id="donut-example" style="height: 250px;"></div>

                                        </div>

                                    </div>

                                </div>
                                <!-- /tile body -->

                            </section>
                            <!-- /tile -->

                            <!-- tile -->
                            <section class="tile">

                                <!-- tile header -->
                                <div class="tile-header dvd dvd-btm">
                                    <h1 class="custom-font"><strong>Sparkline </strong>Charts</h1>
                                    <ul class="controls">
                                        <li class="dropdown">

                                            <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                <i class="fa fa-cog"></i>
                                                <i class="fa fa-spinner fa-spin"></i>
                                            </a>

                                            <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-toggle">
                                                        <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                        <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-refresh">
                                                        <i class="fa fa-refresh"></i> Refresh
                                                    </a>
                                                </li>
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-fullscreen">
                                                        <i class="fa fa-expand"></i> Fullscreen
                                                    </a>
                                                </li>
                                            </ul>

                                        </li>
                                        <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                    </ul>
                                </div>
                                <!-- /tile header -->

                                <!-- tile body -->
                                <div class="tile-body">

                                    <div class="row">

                                        <div class="col-md-4">

                                            <h4 class="custom-font"><strong>Line</strong> chart</h4>
                                            <span id="sparkline01"></span>

                                        </div>

                                        <div class="col-md-4">

                                            <h4 class="custom-font"><strong>Bar</strong> chart</h4>
                                            <span id="sparkline02" data-values="5,6,7,2,1,-4,-2,4,6,8" data-type="bar" data-height="250px"></span>

                                        </div>

                                        <div class="col-md-4 text-center">

                                            <h4 class="custom-font text-left"><strong>Pie</strong> chart</h4>
                                            <span id="sparkline03"></span>

                                        </div>

                                    </div>

                                </div>
                                <!-- /tile body -->

                            </section>
                            <!-- /tile -->

                            <!-- tile -->
                            <section class="tile">

                                <!-- tile header -->
                                <div class="tile-header dvd dvd-btm">
                                    <h1 class="custom-font"><strong>Easy Pie </strong>Charts</h1>
                                    <ul class="controls">
                                        <li class="dropdown">

                                            <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                <i class="fa fa-cog"></i>
                                                <i class="fa fa-spinner fa-spin"></i>
                                            </a>

                                            <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-toggle">
                                                        <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                        <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-refresh">
                                                        <i class="fa fa-refresh"></i> Refresh
                                                    </a>
                                                </li>
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-fullscreen">
                                                        <i class="fa fa-expand"></i> Fullscreen
                                                    </a>
                                                </li>
                                            </ul>

                                        </li>
                                        <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                    </ul>
                                </div>
                                <!-- /tile header -->

                                <!-- tile body -->
                                <div class="tile-body">

                                    <!-- row -->
                                    <div class="row">
                                        <!-- col -->
                                        <div class="col-md-3 text-center">
                                            <div class="easypiechart animate" data-percent="30" data-size="180" data-bar-color="#1693A5" data-line-cap="round" data-line-width="5" style="width:180px;">
                                                <div class="pie-percent custom-font" style="line-height: 170px;"><span>30</span></div>
                                            </div>
                                        </div>
                                        <!-- /col
                                        <!-- col -->
                                        <div class="col-md-3 text-center">
                                            <div class="easypiechart animate" data-percent="45" data-size="180" data-scale-color="false" data-bar-color="#A40778" data-line-cap="round" data-line-width="5" style="width:180px;">
                                                <div class="pie-percent custom-font" style="line-height: 170px;"><span>45</span></div>
                                            </div>
                                        </div>
                                        <!-- /col -->
                                        <!-- col -->
                                        <div class="col-md-3 text-center">
                                            <div class="easypiechart animate" data-percent="8" data-size="220" data-bar-color="#e05d6f" data-line-cap="butt" data-line-width="10" style="width:220px;">
                                                <div class="pie-percent custom-font" style="line-height: 210px;"><span>8</span></div>
                                            </div>
                                        </div>
                                        <!-- /col -->
                                        <!-- col -->
                                        <div class="col-md-3 text-center">
                                            <div class="easypiechart animate" data-percent="24" data-size="220" data-line-cap="round" data-line-width="10" data-scale-color="false" data-bar-color="#5cb85c" style="width:220px;">
                                                <div class="pie-percent custom-font" style="line-height: 210px;"><span>24</span></div>
                                            </div>
                                        </div>
                                        <!-- /col -->
                                    </div>
                                    <!-- /row -->

                                </div>
                                <!-- /tile body -->

                            </section>
                            <!-- /tile -->

                            <!-- tile -->
                            <section class="tile">

                                <!-- tile header -->
                                <div class="tile-header dvd dvd-btm">
                                    <h1 class="custom-font"><strong>Gauge.js </strong>Charts</h1>
                                    <ul class="controls">
                                        <li class="dropdown">

                                            <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                <i class="fa fa-cog"></i>
                                                <i class="fa fa-spinner fa-spin"></i>
                                            </a>

                                            <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-toggle">
                                                        <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                        <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-refresh">
                                                        <i class="fa fa-refresh"></i> Refresh
                                                    </a>
                                                </li>
                                                <li>
                                                    <a role="button" tabindex="0" class="tile-fullscreen">
                                                        <i class="fa fa-expand"></i> Fullscreen
                                                    </a>
                                                </li>
                                            </ul>

                                        </li>
                                        <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                    </ul>
                                </div>
                                <!-- /tile header -->

                                <!-- tile body -->
                                <div class="tile-body">

                                    <!-- row -->
                                    <div class="row">
                                        <!-- col -->
                                        <div class="col-md-3 text-center">
                                            <canvas id="gauge1"
                                                    style="width: 220px; height: 110px; ">
                                            </canvas>
                                        </div>
                                        <!-- /col -->
                                        <!-- col -->
                                        <div class="col-md-3 text-center">
                                            <canvas id="gauge2"
                                                    style="width: 220px; height: 110px; ">
                                            </canvas>
                                        </div>
                                        <!-- /col -->
                                        <!-- col -->
                                        <div class="col-md-3 text-center">
                                            <canvas id="gauge3"
                                                    style="width: 220px; height: 110px; ">
                                            </canvas>
                                        </div>
                                        <!-- /col -->
                                        <!-- col -->
                                        <div class="col-md-3 text-center">
                                            <canvas id="gauge4"
                                                    style="width: 220px; height: 110px; ">
                                            </canvas>
                                        </div>
                                        <!-- /col -->
                                    </div>
                                    <!-- /row -->

                                </div>
                                <!-- /tile body -->

                            </section>
                            <!-- /tile -->

                        </div>
                        <!-- /col -->
                    </div>
                    <!-- /row -->

                    <!-- row -->
                    <div class="row">
                        <!-- col -->
                        <div class="col-md-8 padnil">
                            <div class="row">
                                <div class="col-lg-12 col-md-12">
                                    <div class="col-lg-4 col-md-4 paddright">
                                        <!-- dashboard -->
                                        <section class="tile">

                                            <!-- tile header -->
                                            <div class="tile-header dvd dvd-btm">
                                                <h1 class="custom-font"><strong>OPD </strong>Patients</h1>
                                                <ul class="controls">
                                                    <li class="dropdown">

                                                        <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                            <i class="fa fa-cog"></i>
                                                            <i class="fa fa-spinner fa-spin"></i>
                                                        </a>

                                                        <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-toggle">
                                                                    <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                                    <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-refresh">
                                                                    <i class="fa fa-refresh"></i> Refresh
                                                                </a>
                                                            </li>
                                                           
                                                        </ul>

                                                    </li>
                                                    <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                                </ul>
                                            </div>
                                            <!-- /tile header -->
                                            <!-- tile widget -->
                                            <div class="tile-widget">
                                                <div class="item">
                                                    <img src="mis/assets/images/OPD_2.png" class="img-responsive" />
                                                    <div class="overlay"></div>
                                                    <a href="JavaScript:newPopup('chart.html');"><div class="button"></div></a>
                                                  
                                                </div>
                                            </div>
                                            <!-- /tile widget -->
                                            <!-- tile body -->
                                            <div class="tile-body p-0">
                                                <div class="panel-group icon-plus" id="accordion" role="tablist" aria-multiselectable="true">

                                                    <div class="panel panel-default panel-transparent">
                                                        <div class="panel-heading" role="tab" id="headingOne">
                                                            <h4 class="panel-title">
                                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                                                    <span><i class="fa fa-minus text-sm mr-5"></i> <strong>Appointments</strong></span>
                                                                    <span class="badge pull-right bg-lightred">3</span>
                                                                </a>
                                                            </h4>
                                                        </div>
                                                        <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                                            <div class="panel-body">
                                                                <table class="table table-no-border m-0">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>1</td>
                                                                            <td><a href="" data-toggle="modal" data-target="#myModal2">Booked</a></td>
                                                                            <td>15</td>
                                                                            <td><i class="fa fa-caret-up text-success"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>2</td>
                                                                            <td><a href="">Completed</a></td>
                                                                            <td>10</td>
                                                                            <td><i class="fa fa-caret-up text-success"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>3</td>
                                                                            <td><a href="">DNA</a></td>
                                                                            <td>5</td>
                                                                            <td><i class="fa fa-caret-down text-danger"></i></td>
                                                                        </tr>

                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /tile body -->


                                        </section>
                                        <!-- /dashboard -->
                                    </div>
                                    <div class="col-lg-4 col-md-4 paddright">
                                        <!-- dashboard -->
                                        <section class="tile">

                                            <!-- tile header -->
                                            <div class="tile-header dvd dvd-btm">
                                                <h1 class="custom-font"><strong>IPD </strong>Patients</h1>
                                                <ul class="controls">
                                                    <li class="dropdown">

                                                        <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                            <i class="fa fa-cog"></i>
                                                            <i class="fa fa-spinner fa-spin"></i>
                                                        </a>

                                                        <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-toggle">
                                                                    <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                                    <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-refresh">
                                                                    <i class="fa fa-refresh"></i> Refresh
                                                                </a>
                                                            </li>
                                                           
                                                        </ul>

                                                    </li>
                                                    <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                                </ul>
                                            </div>
                                            <!-- /tile header -->
                                            <!-- tile widget -->
                                            <div class="tile-widget">
                                                <div class="item">
                                                    <img src="mis/assets/images/IPD_2.png" class="img-responsive" />
                                                    <div class="overlay"></div>
                                                    <div class="button"></div>
                                                </div>
                                            </div>
                                            <!-- /tile widget -->
                                            <!-- tile body -->
                                            <div class="tile-body p-0">
                                                <div class="panel-group icon-plus" id="accordion" role="tablist" aria-multiselectable="true">

                                                    <div class="panel panel-default panel-transparent">
                                                        <div class="panel-heading" role="tab" id="headingTwo">
                                                            <h4 class="panel-title">
                                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                                    <span><i class="fa fa-minus text-sm mr-5"></i> <strong>Count</strong></span>
                                                                    <span class="badge pull-right bg-lightred">3</span>
                                                                </a>
                                                            </h4>
                                                        </div>
                                                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                                            <div class="panel-body">
                                                                <table class="table table-no-border m-0">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>1</td>
                                                                            <td><a href="">New Admission</a></td>
                                                                            <td>200</td>
                                                                            <td><i class="fa fa-caret-up text-success"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>2</td>
                                                                            <td><a href="">Inhouse Patient</a></td>
                                                                            <td>150</td>
                                                                            <td><i class="fa fa-caret-up text-danger"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>3</td>
                                                                            <td><a href="">Discharge Patient</a></td>
                                                                            <td>50</td>
                                                                            <td><i class="fa fa-caret-down text-success"></i></td>
                                                                        </tr>

                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /tile body -->

                                        </section>
                                        <!-- /dashboard -->
                                    </div>
                                    <div class="col-lg-4 col-md-4 paddright">
                                        <!-- dashboard -->
                                        <section class="tile">

                                            <!-- tile header -->
                                            <div class="tile-header dvd dvd-btm">
                                                <h1 class="custom-font"><strong>Bed </strong>Status</h1>
                                                <ul class="controls">
                                                    <li class="dropdown">

                                                        <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                            <i class="fa fa-cog"></i>
                                                            <i class="fa fa-spinner fa-spin"></i>
                                                        </a>

                                                        <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-toggle">
                                                                    <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                                    <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-refresh">
                                                                    <i class="fa fa-refresh"></i> Refresh
                                                                </a>
                                                            </li>
                                                           
                                                        </ul>

                                                    </li>
                                                    <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                                </ul>
                                            </div>
                                            <!-- /tile header -->
                                            <!-- tile widget -->
                                            <div class="tile-widget">
                                                <div class="item">
                                                    <img src="mis/assets/images/bed_2.png" class="img-responsive" />
                                                    <div class="overlay"></div>
                                                    <div class="button"></div>
                                                </div>
                                                
                                            </div>
                                            <!-- /tile widget -->
                                            <!-- tile body -->
                                            <div class="tile-body p-0">
                                                <div class="panel-group icon-plus" id="accordion" role="tablist" aria-multiselectable="true">

                                                    <div class="panel panel-default panel-transparent">
                                                        <div class="panel-heading" role="tab" id="headingThree">
                                                            <h4 class="panel-title">
                                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                                    <span><i class="fa fa-minus text-sm mr-5"></i> <strong>Status</strong> </span>
                                                                    <span class="badge pull-right bg-lightred">4</span>
                                                                </a>
                                                            </h4>
                                                        </div>
                                                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                                            <div class="panel-body">
                                                                <table class="table table-no-border m-0">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>1</td>
                                                                            <td><a href="">Total Beds</a></td>
                                                                            <td>200</td>
                                                                            <td><i class="fa fa-caret-up text-success"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>2</td>
                                                                            <td><a href="">Under Maintenance</a></td>
                                                                            <td>25</td>
                                                                            <td><i class="fa fa-caret-up text-danger"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>3</td>
                                                                            <td><a href="">Occupied</a></td>
                                                                            <td>150</td>
                                                                            <td><i class="fa fa-caret-down text-success"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>4</td>
                                                                            <td><a href="">Available</a></td>
                                                                            <td>25</td>
                                                                            <td><i class="fa fa-caret-up text-success"></i></td>
                                                                        </tr>

                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /tile body -->

                                        </section>
                                        <!-- /dashboard -->
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12">
                                    <div class="col-lg-4 col-md-4 paddright">
                                       
                                        <!-- dashboard -->
                                        <section class="tile">

                                            <!-- tile header -->
                                            <div class="tile-header dvd dvd-btm">
                                                <h1 class="custom-font"><strong>Invoice </strong>& Billing</h1>
                                                <ul class="controls">
                                                    <li class="dropdown">

                                                        <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                            <i class="fa fa-cog"></i>
                                                            <i class="fa fa-spinner fa-spin"></i>
                                                        </a>

                                                        <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-toggle">
                                                                    <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                                    <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-refresh">
                                                                    <i class="fa fa-refresh"></i> Refresh
                                                                </a>
                                                            </li>
                                                           
                                                        </ul>

                                                    </li>
                                                    <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                                </ul>
                                            </div>
                                            <!-- /tile header -->
                                            <!-- tile widget -->
                                            <div class="tile-widget">
                                                <div class="item">
                                                    <img src="mis/assets/images/invoice.png" class="img-responsive" />
                                                    <div class="overlay"></div>
                                                    <div class="button"></div>
                                                </div>
                                            </div>
                                            <!-- /tile widget -->
                                            <!-- tile body -->
                                            <div class="tile-body p-0">
                                                <div class="panel-group icon-plus" id="accordion" role="tablist" aria-multiselectable="true">

                                                    <div class="panel panel-default panel-transparent">
                                                        <div class="panel-heading" role="tab" id="headingfour">
                                                            <h4 class="panel-title">
                                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapsefour" aria-expanded="false" aria-controls="collapsefour">
                                                                    <span><i class="fa fa-minus text-sm mr-5"></i> <strong>Total</strong></span>
                                                                    <span class="badge pull-right bg-lightred">3</span>
                                                                </a>
                                                            </h4>
                                                        </div>
                                                        <div id="collapsefour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingfour">
                                                            <div class="panel-body">
                                                                <table class="table table-no-border m-0">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>1</td>
                                                                            <td><a href="">Charges Added</a></td>
                                                                            <td>150</td>
                                                                            <td><i class="fa fa-caret-up text-success"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>2</td>
                                                                            <td><a href="">Invoices Genrated</a></td>
                                                                            <td>10</td>
                                                                            <td><i class="fa fa-caret-up text-success"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>3</td>
                                                                            <td><a href="">Payments Received</a></td>
                                                                            <td>50</td>
                                                                            <td><i class="fa fa-caret-down text-danger"></i></td>
                                                                        </tr>

                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /tile body -->


                                        </section>
                                        <!-- /dashboard -->
                                    </div>
                                    <div class="col-lg-4 col-md-4 paddright">
                                        <!-- dashboard -->
                                        <section class="tile">

                                            <!-- tile header -->
                                            <div class="tile-header dvd dvd-btm">
                                                <h1 class="custom-font"><strong>Payments </strong>Mode</h1>
                                                <ul class="controls">
                                                    <li class="dropdown">

                                                        <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                            <i class="fa fa-cog"></i>
                                                            <i class="fa fa-spinner fa-spin"></i>
                                                        </a>

                                                        <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-toggle">
                                                                    <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                                    <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-refresh">
                                                                    <i class="fa fa-refresh"></i> Refresh
                                                                </a>
                                                            </li>
                                                          
                                                        </ul>

                                                    </li>
                                                    <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                                </ul>
                                            </div>
                                            <!-- /tile header -->
                                            <!-- tile widget -->
                                            <div class="tile-widget">
                                                <div class="item">
                                                    <img src="mis/assets/images/pay.png" class="img-responsive" />
                                                    <div class="overlay"></div>
                                                    <div class="button"></div>
                                                </div>
                                               
                                            </div>
                                            <!-- /tile widget -->
                                            <!-- tile body -->
                                            <div class="tile-body p-0">
                                                <div class="panel-group icon-plus" id="accordion" role="tablist" aria-multiselectable="true">

                                                    <div class="panel panel-default panel-transparent">
                                                        <div class="panel-heading" role="tab" id="headingFive">
                                                            <h4 class="panel-title">
                                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                                                    <span><i class="fa fa-minus text-sm mr-5"></i> <strong>Payments</strong></span>
                                                                    <span class="badge pull-right bg-lightred">3</span>
                                                                </a>
                                                            </h4>
                                                        </div>
                                                        <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                                                            <div class="panel-body">
                                                                <table class="table table-no-border m-0">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>1</td>
                                                                            <td><a href="">By Cash</a></td>
                                                                            <td>200</td>
                                                                            <td><i class="fa fa-caret-up text-success"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>2</td>
                                                                            <td><a href="">By Cheques</a></td>
                                                                            <td>150</td>
                                                                            <td><i class="fa fa-caret-up text-danger"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>3</td>
                                                                            <td><a href="">By Credit/Debit cards</a></td>
                                                                            <td>50</td>
                                                                            <td><i class="fa fa-caret-down text-success"></i></td>
                                                                        </tr>

                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /tile body -->

                                        </section>
                                        <!-- /dashboard -->
                                    </div>
                                    <div class="col-lg-4 col-md-4 paddright">
                                        <!-- dashboard -->
                                        <section class="tile">

                                            <!-- tile header -->
                                            <div class="tile-header dvd dvd-btm">
                                                <h1 class="custom-font"><strong>Expense </strong>Summary</h1>
                                                <ul class="controls">
                                                    <li class="dropdown">

                                                        <a role="button" tabindex="0" class="dropdown-toggle settings" data-toggle="dropdown">
                                                            <i class="fa fa-cog"></i>
                                                            <i class="fa fa-spinner fa-spin"></i>
                                                        </a>

                                                        <ul class="dropdown-menu pull-right with-arrow animated littleFadeInUp">
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-toggle">
                                                                    <span class="minimize"><i class="fa fa-angle-down"></i>&nbsp;&nbsp;&nbsp;Minimize</span>
                                                                    <span class="expand"><i class="fa fa-angle-up"></i>&nbsp;&nbsp;&nbsp;Expand</span>
                                                                </a>
                                                            </li>
                                                            <li>
                                                                <a role="button" tabindex="0" class="tile-refresh">
                                                                    <i class="fa fa-refresh"></i> Refresh
                                                                </a>
                                                            </li>
                                                            
                                                        </ul>

                                                    </li>
                                                    <li class="remove"><a role="button" tabindex="0" class="tile-close"><i class="fa fa-times"></i></a></li>
                                                </ul>
                                            </div>
                                            <!-- /tile header -->
                                            <!-- tile widget -->
                                            <div class="tile-widget">
                                                <div class="item">
                                                    <img src="mis/assets/images/expense2.png" class="img-responsive" />
                                                    <div class="overlay"></div>
                                                    <div class="button"></div>
                                                </div>
                                               
                                            </div>
                                            <!-- /tile widget -->
                                            <!-- tile body -->
                                            <div class="tile-body p-0">
                                                <div class="panel-group icon-plus" id="accordion" role="tablist" aria-multiselectable="true">

                                                    <div class="panel panel-default panel-transparent">
                                                        <div class="panel-heading" role="tab" id="headingSix">
                                                            <h4 class="panel-title">
                                                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                                                    <span><i class="fa fa-minus text-sm mr-5"></i> <strong>Total By</strong> </span>
                                                                    <span class="badge pull-right bg-lightred">2</span>
                                                                </a>
                                                            </h4>
                                                        </div>
                                                        <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                                                            <div class="panel-body">
                                                                <table class="table table-no-border m-0">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>1</td>
                                                                            <td><a href="">Category</a></td>
                                                                            <td>200</td>
                                                                            <td><i class="fa fa-caret-up text-success"></i></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>2</td>
                                                                            <td><a href="">Payment Mode</a></td>
                                                                            <td>25</td>
                                                                            <td><i class="fa fa-caret-up text-danger"></i></td>
                                                                        </tr>


                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /tile body -->

                                        </section>
                                        <!-- /dashboard -->
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- /col -->

                        <!-- col -->
                        <div class="col-md-4">
                            <!-- tile body -->
                            <div class="tile-body">
                                <!-- row -->
                                <div>
                                    <!-- col -->
                                    <div class="col-md-12 col-sm-12 mothlyback">
                                        <h4 class="underline custom-font"><strong>Monthly</strong> Statistics</h4>
                                        <div class="progress-list">
                                            <div class="details">
                                                <div class="title">Total Admissions </div>
                                                <div class="description">month wise</div>
                                            </div>
                                            <div class="status pull-right">
                                                <span>40</span>%
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="progress-xs not-rounded progress">
                                                <div class="progress-bar progress-bar-dutch" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                                    <span class="sr-only">40%</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="progress-list">
                                            <div class="details">
                                                <div class="title">Bed Occupancy </div>
                                                <div class="description">month wise</div>
                                            </div>
                                            <div class="status pull-right">
                                                <span>38</span>%
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="progress-xs not-rounded progress">
                                                <div class="progress-bar progress-bar-greensea" role="progressbar" aria-valuenow="38" aria-valuemin="0" aria-valuemax="100" style="width: 38%">
                                                    <span class="sr-only">38%</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="progress-list">
                                            <div class="details">
                                                <div class="title">Revenue</div>
                                                <div class="description">(Invoiced/Receivable)</div>
                                            </div>
                                            <div class="status pull-right">
                                                <span>12</span>%
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="progress-xs not-rounded progress">
                                                <div class="progress-bar progress-bar-lightred" role="progressbar" aria-valuenow="12" aria-valuemin="0" aria-valuemax="100" style="width: 12%">
                                                    <span class="sr-only">12%</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="progress-list">
                                            <div class="details">
                                                <div class="title">Collection</div>
                                                <div class="description">(Payments Received)</div>
                                            </div>
                                            <div class="status pull-right">
                                                <span>7</span>%
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="progress-xs not-rounded progress">
                                                <div class="progress-bar progress-bar-blue" role="progressbar" aria-valuenow="7" aria-valuemin="0" aria-valuemax="100" style="width: 7%">
                                                    <span class="sr-only">7%</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /col -->


                                </div>
                                <!-- /row -->
                            </div>
                            <!-- /tile body -->
                        </div>
                        <!-- /col -->
                    </div>
                    <!-- /row -->

                </div>


                
            </section>
            <!--/ CONTENT -->






        </div>
        <!--/ Application Content -->


       




        <!-- ============================================
        ============== Vendor JavaScripts ===============
        ============================================= -->
       
        <script>window.jQuery || document.write('<script src="assets/js/vendor/jquery/jquery-1.11.2.min.js"><\/script>')</script>

        

        <script src="mis/assets/js/vendor/jRespond/jRespond.min.js"></script>

        <script src="mis/assets/js/vendor/sparkline/jquery.sparkline.min.js"></script>

        <script src="mis/assets/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>

        <script src="mis/assets/js/vendor/animsition/js/jquery.animsition.min.js"></script>

        <script src="mis/assets/js/vendor/screenfull/screenfull.min.js"></script>
       

        <script src="mis/assets/js/vendor/raphael/raphael-min.js"></script>
        <script src="mis/assets/js/vendor/d3/d3.v2.js"></script>
        <script src="mis/assets/js/vendor/d3/d3.min.js"></script>
        <script src="mis/assets/js/vendor/d3/d3.layout.min.js"></script>
        <script src="mis/assets/js/vendor/rickshaw/rickshaw.min.js"></script>

        <script src="mis/assets/js/vendor/daterangepicker/moment.min.js"></script>
        <script src="mis/assets/js/vendor/daterangepicker/daterangepicker.js"></script>
        <script src="mis/assets/js/vendor/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
        <script src="mis/assets/js/vendor/morris/morris.min.js"></script>

        <script src="mis/assets/js/vendor/easypiechart/jquery.easypiechart.min.js"></script>

        <script src="mis/assets/js/vendor/countTo/jquery.countTo.js"></script>
        <!--/ vendor javascripts -->





        <!-- ============================================
        ============== Custom JavaScripts ===============
        ============================================= -->
        <script src="mis/assets/js/main.js"></script>
        <!--/ custom javascripts -->






        <!-- ===============================================
        ============== Page Specific Scripts ===============
        ================================================ -->
        <script>
            $('#appointments-carousel').owlCarousel({
                autoPlay: 5000,
                stopOnHover: true,
                slideSpeed: 300,
                paginationSpeed: 400,
                navigation: true,
                navigationText: ['<i class=\'fa fa-chevron-left\'></i>', '<i class=\'fa fa-chevron-right\'></i>'],
                singleItem: true
            });
            //* Initialize owl carousels
        </script>
        <!--/ Page Specific Scripts -->

        <script type="text/javascript">
            // Popup window code
            function newPopup(url) {
                popupWindow = window.open(
                    url, 'popUpWindow', 'width=auto,resizable=yes,scrollbars=yes,toolbar=yes,status=yes')
            }
        </script>

    </body>
</html>
