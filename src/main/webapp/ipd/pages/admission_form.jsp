<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

      <link rel="icon" href="assets/img/favicon.ico">
    <title>Advance Practice Management</title>
      <link href="assets/js/fullcalendar-2.2.3/fullcalendar-2.2.3/lib/cupertino/jquery-ui.min.css" rel="stylesheet" />
    <!-- Bootstrap core CSS -->

      <link href="assets/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
      <!--external css-->
      <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
      <link href="assets/js/smartmenus-0.9.7/css/sm-clean/sm-clean.css" rel="stylesheet" />
      <link href="assets/js/smartmenus-0.9.7/css/sm-core-css.css" rel="stylesheet" />

      <!-- Custom styles for this template -->
      <link href="assets/css/style.css" rel="stylesheet">
      <link href="assets/css/style-responsive.css" rel="stylesheet">
    
      <link href="assets/css/priscription/bootstrap-3.3.1-dist/dist/css/bootstrap.min.css" rel="stylesheet" />
      <link href="assets/css/priscription/Notification.css" rel="stylesheet" />
      <link href="assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
          <div class="sidebar-toggle-box">
              <div class="fa fa-bars tooltips" data-placement="bottom" data-original-title="Sidebar"></div>
          </div>
          <!--logo start-->
          <a href="dashboard.html" class="logo"><img class="logoimg" src="assets/img/logo.png" /></a>
          <!--logo end-->
          <div class="top-menu">
              <ul class="nav pull-right top-menu">
                  <li>
                      <a href="#" id="toggleFullScreen" class="logout tooltips" data-placement="bottom" data-original-title="Full Screen" onclick="toggleFullScreen();"><i class="glyphicon glyphicon-fullscreen"></i></a>
                  </li>
              </ul>
              <ul class="nav pull-right top-menu">
                  <li><a class="logout tooltips" data-placement="bottom" data-original-title="Lock Screen" href="lock_screen.html"><i class="fa fa-desktop"></i></a></li>
              </ul>
              <ul id="main-menu" class="sm sm-clean nav pull-right top-menu">
                  <li>
                      <a class="logout" href="#">
                          <i class="fa fa-user"></i> Henry
                      </a>
                      <ul class="smul">
                          <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
                          <li><a href="index.html"><i class="fa fa-unlock-alt"></i> Logout</a></li>
                      </ul>
                  </li>
              </ul>
          </div>
      </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar" class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
                  <li class="sub-menu">

                      <img src="assets/img/calender.png" class="admiscalen" />
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-dashboard"></i>
                          <span>IPD Dashboard</span>
                      </a>
                      <ul class="sub">
                          <li><a href="Bed_Master.html">Master Setup</a></li>
                          <li><a href="Bed_Status.html">View Bed Status</a></li>

                      </ul>
                  </li>

                  <li class="sub-menu">
                      <a href="Admission_Form.html">
                          <i class="fa fa-file-text-o"></i>
                          <span>Admission Form</span>
                      </a>

                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-medkit"></i>
                          <span>Drugs</span>
                      </a>
                      <ul class="sub">
                          <li><a href="View_Drugs.html">Veiw Drugs</a></li>
                          <li><a href="Add_Drugs.html">Add Drugs</a></li>
                          <li><a href="priscription.html" target="_blank">Priscription</a></li>

                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-bar-chart-o"></i>
                          <span>Inventary Management</span>
                      </a>
                      <ul class="sub">
                          <li><a href="">Create Inventary</a></li>
                          <li><a href="">View / Update Inventary</a></li>

                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="#">
                          <i class="fa fa-calendar"></i>
                          <span>Expensis Management</span>
                      </a>

                  </li>

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      <!-- **********************************************************************************************************************************************************
     MAIN CONTENT
     *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapperfixed">
             
              <!-- page start-->
              <div class="row mt">
                  <aside>
                      <div class="panel panel-primary topbane6">
                          <div class="panel-body">
                              <div class="row details">
                                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

                                      <h4>Admission Details</h4>

                                  </div>
                              </div>
                              <div class="row ">
                                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 typedose">
                                      <form>
                                          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                              <div class="form-group">
                                                  <label for="exampleInputEmail1">Patient Bar Code</label>
                                                  <input type="email" class="form-control" id="exampleInputEmail1">
                                              </div>
                                          </div>
                                          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                              <div class="form-group">
                                                  <label for="exampleInputEmail1">Admission Code</label>
                                                  <input type="email" class="form-control" id="exampleInputEmail1">
                                              </div>
                                          </div>
                                          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                              <div class="form-group">
                                                  <label for="exampleInputEmail1">Admission Date</label>
                                                  <input type="email" class="form-control" id="exampleInputEmail1">
                                              </div>
                                          </div>
                                          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                              <div class="form-group">
                                                  <label for="exampleInputEmail1">Admission Time</label>
                                                  <input type="email" class="form-control" id="exampleInputEmail1">
                                              </div>
                                          </div>
                                          <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                                              <div class="form-group">
                                                  <label for="exampleInputEmail1">Admission IP</label>
                                                  <input type="email" class="form-control" id="exampleInputEmail1">
                                              </div>
                                          </div>
                                      </form>
                                  </div>
                              </div>

                              <div class="row">
                                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
                                      <img src="assets/img/admissionform.png" class="admissionlogo" />
                                  </div>
                                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 adformback">
                                      <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                          <form class="form-horizontal">
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Age</label>
                                                  <div class="col-xs-3">
                                                      <input type="email" class="form-control" id="inputEmail" placeholder="Years">
                                                  </div>
                                                  <div class="col-xs-3">
                                                      <input type="email" class="form-control" id="inputEmail" placeholder="Months">
                                                  </div>
                                                  <div class="col-xs-3">
                                                      <input type="email" class="form-control" id="inputEmail" placeholder="Days">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Type</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Admitting Consultant</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Secondary Consultant</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Location</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">MLC no.</label>
                                                  <div class="col-xs-5">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                                  <div class="col-xs-4">
                                                      <button class="btn btn-primary" disabled>MLC Details</button>
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Referred From</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>

                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Ward</label>
                                                  <div class="col-xs-5">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Bed no.</label>
                                                  <div class="col-xs-5">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                                  <a href="Book_bed.html"><img src="assets/img/bedbtn.png" class="img-responsive bednosele" /></a>
                                              </div>
                                              
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Admission Details</label>
                                                  <div class="col-xs-9">
                                                      <textarea class="form-control" rows="3"></textarea>
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Sugested Treatment</label>
                                                  <div class="col-xs-9">
                                                      <textarea class="form-control" rows="3"></textarea>
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Insurance</label>
                                                  <div class="col-xs-5">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                                  <div class="col-xs-4">
                                                      <button class="btn btn-primary">Insurance Details</button>
                                                  </div>
                                              </div>
                                          </form>
                                      </div>
                                      <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
                                          <form class="form-horizontal">
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Status</label>
                                                  <div class="col-xs-4">
                                                      <input type="email" class="form-control" id="inputEmail" placeholder="Years">
                                                  </div>

                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Class</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Referred By</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Admission For</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Reimbursement</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Hospital/Clinic</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Package</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="row">
                                                  <div class="col-lg-12 col-md-12">
                                                      <b class="diagtitle">Diagnosis</b>
                                                  </div>

                                              </div>
                                              <div class="form-group daignosis formbormar">
                                                  <label for="inputEmail" class="control-label col-xs-3">Primary</label>
                                                  <div class="col-xs-6">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                                  <div class="col-xs-2 scbtn">
                                                      <button class="btn btn-primary">S</button>
                                                  </div>
                                                  <div class="col-xs-2 scbtn">
                                                      <button class="btn btn-primary">C</button>
                                                  </div>
                                              </div>
                                              <div class="form-group daignosis formbormar">
                                                  <label for="inputEmail" class="control-label col-xs-3">Secondary</label>
                                                  <div class="col-xs-6">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                                  <div class="col-xs-2 scbtn">
                                                      <button class="btn btn-primary">S</button>
                                                  </div>
                                                  <div class="col-xs-2 scbtn">
                                                      <button class="btn btn-primary">C</button>
                                                  </div>
                                              </div>
                                              <div class="form-group daignosis formbormar">
                                                  <label for="inputEmail" class="control-label col-xs-3">Admission Diagnosis</label>
                                                  <div class="col-xs-9">
                                                      <textarea class="form-control" rows="3"></textarea>
                                                  </div>
                                              </div>
                                              <div class="form-group martop10only">
                                                  <label for="inputEmail" class="control-label col-xs-3">Suggested Opr.</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Special Notes/Remarks</label>
                                                  <div class="col-xs-9">
                                                      <textarea class="form-control" rows="3"></textarea>
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label for="inputEmail" class="control-label col-xs-3">Location</label>
                                                  <div class="col-xs-9">
                                                      <input type="email" class="form-control" id="inputEmail">
                                                  </div>
                                              </div>
                                          </form>
                                      </div>

                                  </div>
                                  <div class="row">
                                      <div class="col-lg-12 col-md-12">
                                          <a href="Book_bed.html" class="btn btn-primary savebtn">SAVE</a>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </aside>
              </div>
              <!-- page end-->
          </section>
      </section><!-- /MAIN CONTENT -->
      <!--main content end-->
     
  </section>


      <!-- Booked BedModal -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header bg-primary">
                      <button type="button" class="close" data-dismiss="modal" onclick="movetosetCommonAction()">
                          <span aria-hidden="true">×</span><span class="sr-only">Close</span>
                      </button>
                      <h4 class="modal-title" id="myModalLabel">Day-To-Day Admin</h4>
                  </div>
                  <div class="modal-body">
                      <div id="modifyClient1" style="font-size: 16px; color: rgb(61, 61, 61); margin-bottom: 6px;"><a href="#" onclick="openClientPrintPopup(41)"> praful G</a> <a href="#" title="Edit Client Record" onclick="openEditClientPrintPopup(41)"><img src="assets/img/edit.png"></a> <a href="#" title="Log" onclick="openClientLogPopup(41)"><img src="assets/img/log.png"></a>  <a href="#" title="EMR" onclick="redircttoNewEmr(41,12,5)"><img src="assets/img/emr.png"></a>  <a href="#" title="Assessment Form" onclick="openAssesmentFormPopup(41,12,501)"><img src="assets/img/asmnt.png"></a></div>


                      
                      <div class="row manaboxwi">
                          <div class="col-lg-4">
                              <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 images_1_of_3 bedspace bookbed popupbed">
                                  <div class=" listimg_1_of_2">
                                      <img src="assets/img/bed_new.png" class="bedic" /> <h2 class="bedno">005</h2>
                                  </div>
                                  <div class="text">
                                      <div class="row">
                                          <div class="col-lg-3 col-md-3 col-xs-12">
                                              <img src="assets/img/male.png" class="male" />
                                          </div>
                                          <div class="col-lg-9 col-md-9 col-xs-12 martop10icon cleintname">
                                              Mr.Suraj Singh
                                          </div>
                                      </div>

                                      <div class="row">
                                          <div class="col-lg-6 col-md-6 col-xs-12">
                                              <div class="col-lg-9 col-md-9 col-xs-12 ageleft">
                                                  <img src="assets/img/age.png" class="setcino" />
                                              </div>
                                              <div class="col-lg-3 col-md-3 col-xs-12 martop10icon calentext">
                                                  54
                                              </div>
                                          </div>
                                          <div class="col-lg-6 col-md-6 col-xs-12">
                                              <div class="col-lg-9 col-md-9 col-xs-12 dateleft">
                                                  <img src="assets/img/addate.png" class="setcino calen" />
                                              </div>
                                              <div class="col-lg-3 col-md-3 col-xs-12 martop10icon calentext">
                                                  31/10/205
                                              </div>
                                          </div>


                                      </div>
                                      <div class="row">
                                          <div class="col-lg-3 col-md-3 col-xs-12">
                                              <img src="assets/img/address.png" class="setcino" />
                                          </div>
                                          <div class="col-lg-9 col-md-9 col-xs-12 martop10icon">
                                              Yawatmal
                                          </div>
                                      </div>
                                      <div class="row">
                                          <div class="col-lg-3 col-md-3 col-xs-12">
                                              <img src="assets/img/doctor.png" class="setcino" />
                                          </div>
                                          <div class="col-lg-9 col-md-9 col-xs-12 martop10icon">
                                              Dr.Dhananjay
                                          </div>
                                      </div>
                                      <div class="row martop10only">
                                          <div class="col-lg-8 col-md-8 col-xs-8">
                                              Addmisson ID:
                                          </div>
                                          <div class="col-lg-4 col-md-4 col-xs-4 patintid">
                                              5232
                                          </div>
                                      </div>
                                      <div class="row fontad">
                                          <div class="col-lg-8 col-md-8 col-xs-8">
                                              Patient ID:
                                          </div>
                                          <div class="col-lg-4 col-md-4 col-xs-4 patintid">
                                              1211243
                                          </div>
                                      </div>
                                      <div class="row fontad">
                                          <div class="col-lg-8 col-md-8 col-xs-8">
                                              Patient Code:
                                          </div>
                                          <div class="col-lg-4 col-md-4 col-xs-4 patintid">
                                              2014/07/843
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>
                          <div class="col-lg-8">
                              <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                                  <a href="admissionform.html">
                                      <div class="thumbnail">
                                          <img src="assets/img/modify.png" alt="..." style="width: 64px; height: 64px;">
                                          <div class="caption"><p align="center" class="fontpup">Edit</p></div>
                                      </div>
                                  </a>
                                  
                              </div>

                              <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                                  <a href="discharge.html">
                                      <div class="thumbnail">
                                          <img src="assets/img/modify.png" alt="..." style="width: 64px; height: 64px;">
                                          <div class="caption"><p align="center" class="fontpup">Discharge</p></div>
                                      </div>
                                  </a>
                              </div>

                              <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                                 <a href="priscription.html">
                                     <div class="thumbnail">
                                         <img src="assets/img/modify.png" alt="..." style="width: 64px; height: 64px;">
                                         <div class="caption"><p align="center" class="fontpup">Create Prescription</p></div>
                                     </div>
                                 </a>
                              </div>
                              <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                                  <a href="#">
                                      <div class="thumbnail">
                                          <img src="assets/img/modify.png" alt="..." style="width: 64px; height: 64px;">
                                          <div class="caption"><p align="center" class="fontpup">Consultation Note</p></div>
                                      </div>
                                  </a>
                              </div>
                              <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                                  <a href="#">
                                      <div class="thumbnail">
                                          <img src="assets/img/modify.png" alt="..." style="width: 64px; height: 64px;">
                                          <div class="caption"><p align="center" class="fontpup">Add Charges</p></div>
                                      </div>
                                  </a>
                              </div>
                              
                          </div>
                         
                         

                         
                      </div>


                   



                  </div>

                  <div class="modal-footer">
                      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="movetosetCommonAction()">Close</button>
                  </div>
              </div>
          </div>
      </div>

      <!-- js placed at the end of the document so the pages load faster -->
      <script src="assets/js/jquery.js"></script>
      <script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>
      <script src="assets/js/bootstrap/js/bootstrap.min.js"></script>
      <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
      <script src="assets/js/jquery.scrollTo.min.js"></script>
      <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
      <script src="assets/js/smartmenus-0.9.7/jquery.smartmenus.min.js"></script>


      <!--common script for all pages-->
      <script src="assets/js/common-scripts.js"></script>

      <!--script for this page-->
      
      <script>
          $(document).ready(function () {
              toggleFullScreen();
          });
          // mozfullscreenerror event handler
          function errorHandler() {
              alert('mozfullscreenerror');
          }
          document.documentElement.addEventListener('mozfullscreenerror', errorHandler, false);

          // toggle full screen
          function toggleFullScreen() {
              if (!document.fullscreenElement &&    // alternative standard method
                  !document.mozFullScreenElement && !document.webkitFullscreenElement) {  // current working methods
                  if (document.documentElement.requestFullscreen) {
                      document.documentElement.requestFullscreen();
                  } else if (document.documentElement.mozRequestFullScreen) {
                      document.documentElement.mozRequestFullScreen();
                  } else if (document.documentElement.webkitRequestFullscreen) {
                      document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
                  }
              } else {
                  if (document.cancelFullScreen) {
                      document.cancelFullScreen();
                  } else if (document.mozCancelFullScreen) {
                      document.mozCancelFullScreen();
                  } else if (document.webkitCancelFullScreen) {
                      document.webkitCancelFullScreen();
                  }
              }
          }

          // keydown event handler
          document.addEventListener('keydown', function (e) {
              if (e.keyCode == 13 || e.keyCode == 70) { // F or Enter key
                  toggleFullScreen();
              }
          }, false);
      </script>
  
      <script type="text/javascript">
          $(function () {
              $('#main-menu').smartmenus({
                  subMenusSubOffsetX: 1,
                  subMenusSubOffsetY: -8
              });
          });
      </script>

</body>
</html>
