<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

     <link rel="icon" href="common/BootstrapNew/img/favicon.ico">
    <title>Advance Practice Management</title>

      <link href="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/lib/cupertino/jquery-ui.min.css" rel="stylesheet" />
    <!-- Bootstrap core CSS -->
      <link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
     
      <!--external css-->
      <link href="common/BootstrapNew/font-awesome/css/font-awesome.css" rel="stylesheet" />
      <link href="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/fullcalendar.min.css" rel="stylesheet" />
      <link href="common/BootstrapNew/gritter/css/jquery.gritter.css" rel="stylesheet" />
      <link href="common/BootstrapNew/smartmenus-0.9.7/css/sm-clean/sm-clean.css" rel="stylesheet" />
      <link href="common/BootstrapNew/smartmenus-0.9.7/css/sm-core-css.css" rel="stylesheet" />
      <!--<link href="assets/js/ExpandingSearchBar/ExpandingSearchBar/css/default.css" rel="stylesheet" />
      <link href="assets/js/ExpandingSearchBar/ExpandingSearchBar/css/component.css" rel="stylesheet" />-->

      <!-- Custom styles for this template -->
      <link href="common/BootstrapNew/css/style.css" rel="stylesheet" />
      <link href="common/BootstrapNew/css/style-responsive.css" rel="stylesheet" />
      
    <!--    <link href="common/BootstrapNew/css/main.css" rel="stylesheet" /> -->
    
     <link href="common/BootstrapNew/Sticky-Sidebar-Navigation-Menu-with-jQuery-Vertical-Navigation/css/app.css" rel="stylesheet" />
    
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
      <style>
          .borderdesing {
              text-align: center;
              font-weight: bold;
              font-size:12px;
          }
          .thumbnail {
              width: 100px !important;
              height: 105px !important;
              padding: 0px !important;
              margin-bottom: -10px !important;
              background-color: rgba(255, 255, 255, 0) !important;
              border: none !important;
          }
              .thumbnail .caption {
                  padding: 0px !important;
                  color: #fff !important;
              }
          .content-panel {
              padding-top: 0px !important;
          }
          .txtcolor {
              color: #fff !important;
          }
      </style>
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

                  <p class="centered"><a href="dashboard.html"><img src="assets/img/ui-sam.jpg" class="img-thumbnail" width="60"></a></p>

                  <li class="sidebar-search">
                      <form class="form-inline" role="form">
                          <div class="input-group">
                              <input type="text" class="form-control" id="s" placeholder="Search...">
                              <!-- Search button -->
                              <span class="input-group-btn">
                                  <button class="btn btn-primary" type="submit"><i class="fa fa-search"></i></button>
                              </span>
                          </div>
                      </form>
                  </li>

                  <li class="sub-menu">
                      <a href="dashboard.html">
                          <i class="fa fa-dashboard"></i>
                          <span>Appointment Dashboard</span>
                      </a>
                      <ul class="sub">
                          <li><a href="finder.html">Finder</a></li>
                      </ul>
                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-user"></i>
                          <span>Clients</span>
                      </a>
                      <ul class="sub">
                          <li><a href="viewclients.html">View Client</a></li>
                          <li><a href="addnewclient.html">Add New Clients</a></li>
                          <li><a href="printclientrecord.html">Print Client record</a></li>
                          <li><a href="treatmentepisode.html">View Treatment Episode</a></li>
                          <li><a href="viewclientlog.html">View Client Log</a></li>
                          <li><a href="viewmedicalrecord.html">View Medical record (EMR)</a></li>
                      </ul>
                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;">
                          
                          <span>Accounts</span>
                      </a>
                      <ul class="sub">
                          <li><a href="viewaccount.html">Veiw Account</a></li>
                          <li><a href="pendingcharges.html">Pending Charges</a></li>
                          <li><a href="progresscharges.html">Create Invoice/ Progress Charges</a></li>
                          <li><a href="recordpayment.html">Record payment</a></li>
                          <li><a href="trackoutinvoice.html">Track Outstanding Invoce</a></li>
                          <li><a href="cashdesk.html">Cash Desk</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-book"></i>
                          <span>Third-Parties</span>
                      </a>
                      <ul class="sub">
                          <li><a href="thirdparty.html">View Third Party</a></li>
                          <li><a href="addnewthirdparty.html">Add New Third Party</a></li>
                          <li><a href="viewgp.html">View GP</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-cogs"></i>
                          <span>Tools</span>
                      </a>
                      <ul class="sub">
                          <li><a href="managedairy.html">Manage Dairy</a></li>
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Manage Clinic</a>
                              <ul class="sub">
                                  <li><a href=""><i class="fa fa-angle-right"></i> Clinic Profile</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Clinic Location</a></li>
                              </ul>
                          </li>
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Users</a>
                              <ul class="sub">
                                  <li><a href=""><i class="fa fa-angle-right"></i> View User</a></li>
                              </ul>
                          </li>
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Setup Clinic Data</a>
                              <ul class="sub">
                                  <li><a href=""><i class="fa fa-angle-right"></i> Set Appoinment Charges</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Set Occupation</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Set Job Title</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Set Referred By</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Set TP Type</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Set Client Declaration</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Set Specializationn</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Set Notification & Reminders</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Traetment Type</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Source Of Intro</a></li>
                              </ul>
                          </li>
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Assesment Form</a>
                              <ul class="sub">
                                  <li><a href=""><i class="fa fa-angle-right"></i> Initial-Neck</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Final-Head</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Report</a></li>
                              </ul>
                          </li>
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Manage Email</a>
                              <ul class="sub">
                                  <li><a href=""><i class="fa fa-angle-right"></i> View Email Template</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Configure Client Email</a></li>
                              </ul>
                          </li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class="fa fa-comments-o"></i>
                          <span>Communications</span>
                      </a>
                      <ul class="sub">
                          <li><a href="email.html">Send Email</a></li>
                          <li><a href="livechats.html">LiveChat</a></li>
                          <li><a href="printletter.html">Printletter</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class=" fa fa-bar-chart-o"></i>
                          <span>Reports</span>
                      </a>
                      <ul class="sub">
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Accounts</a>
                              <ul class="sub">
                                  <li><a href=""><i class="fa fa-angle-right"></i> Practinoiner Commision Report</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Charges Report</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Invoice Report</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Payment Report</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Aged Debtors Report</a></li>
                              </ul>
                          </li>
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Clinical</a>
                              <ul class="sub">
                                  <li><a href=""><i class="fa fa-angle-right"></i> Treatment Episode List</a></li>
                              </ul>
                          </li>
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Client</a>
                              <ul class="sub">
                                  <li><a href=""><i class="fa fa-angle-right"></i> Client List</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Current Treatment with No Future Apmts</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> No Appointment Activity Record</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> DNA with No Future Appoinment</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> No Activity Record</a></li>
                              </ul>
                          </li>
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Summery</a>
                              <ul class="sub">
                                  <li><a href=""><i class="fa fa-angle-right"></i> DNA Analysis</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Appoinments Kepts vs DNA</a></li>
                                  <li><a href=""><i class="fa fa-angle-right"></i> Treatments States by Reffral</a></li>
                              </ul>
                          </li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;">
                          <i class=" fa fa-file-text-o"></i>
                          <span>Assesment Forms</span>
                      </a>
                      <ul class="sub">
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> Form Builder</a>
                              <ul class="sub">
                                  <li><a href="addeditfeild.html"><i class="fa fa-angle-right"></i> Add/Edit Feild</a></li>
                                  <li><a href="createnewform.html"><i class="fa fa-angle-right"></i> Create New From</a></li>
                              </ul>
                          </li>
                          <li>
                              <a href="javascript:;"><i class="fa fa-angle-double-right"></i> View Form</a>
                              <ul class="sub">
                                  <li><a href="familyform.html"><i class="fa fa-angle-right"></i> Family Form</a></li>
                              </ul>
                          </li>
                          <li><a href="vieweditform.html">View / Edit Forms</a></li>
                          <li><a href="">Physiotherapy Assesment Form</a></li>
                          <li><a href="listaddimages.html">List / Add Images</a></li>
                          <li><a href="consultationnote.html">Consultation Note</a></li>
                      </ul>
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
          <section class="wrapper">
              <h3 class="heading3"><a href="">Clients</a> <i class="fa fa-angle-right"></i> Client Panel</h3>
              <div class="row mt">
                  <div class="col-md-12">
                      <div class="content-panel">
                          <table class="table table-striped table-advance table-hover">
                              <div class="panel-body" style="background: none repeat scroll 0% 0% rgb(156, 156, 156);">
                                  <div class="row">
                                      <div class="col-lg-4">
                                          <div class="row">
                                              <div class="col-lg-12">
                                                  <div class="input-group">
                                                      <input type="text" class="form-control" placeholder="Search..">
                                                      <span class="input-group-btn">
                                                          <button class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
                                                      </span>
                                                  </div><!-- /input-group -->
                                              </div>
                                          </div><br />
                                          <div class="row">
                                              <div class="col-lg-6">
                                                  <select class="form-control">
                                                      <option>All</option>
                                                      <option>Mr. Alex Xender</option>
                                                      <option>Mr.Alok Sir</option>
                                                      <option>Mr.Rushi Sir</option>
                                                      <option>Mr.Abhinav Sir</option>
                                                  </select>
                                              </div>
                                              <div class="col-lg-6">
                                                  <select class="form-control">
                                                      <option>Select practioner</option>
                                                      <option>Mr. Alex Xender</option>
                                                      <option>Mr.Alok Sir</option>
                                                      <option>Mr.Rushi Sir</option>
                                                      <option>Mr.Abhinav Sir</option>
                                                  </select>
                                              </div>
                                          </div><br />
                                          <div class="row">
                                              <div class="col-lg-6">
                                                  <input type="checkbox" /> <span style="color:#fff;">Show All</span>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-lg-8">
                                          <div class="col-lg-12 borderdesing">
                                              <div class="row">
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/Add_client.png" alt="Add Clients">
                                                          <div class="caption">
                                                              <p>Add Cleints</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/email.png" alt="Email">
                                                          <div class="caption">
                                                              <p>Email/Letter</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/print.png" alt="Email">
                                                          <div class="caption">
                                                              <p>Print Record</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/emr.png" alt="Email">
                                                          <div class="caption">
                                                              <p>EMR</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/treatment.png" alt="Email">
                                                          <div class="caption">
                                                              <p>Treatment</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/appointment.png" alt="Email">
                                                          <div class="caption">
                                                              <p>Appointment</p>
                                                          </div>
                                                      </div>
                                                  </div>

                                              </div>
                                              <div class="row">
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/log.png" alt="log">
                                                          <div class="caption">
                                                              <p>Log</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/record_payment.png" alt="record_payment">
                                                          <div class="caption">
                                                              <p>Record Payment</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/assesments_form.png" alt="assesments_form">
                                                          <div class="caption">
                                                              <p>Assesments Form</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/cash_desk.png" alt="cash_desk">
                                                          <div class="caption">
                                                              <p>Cash Desk</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/raise_credit.png" alt="raise_credit">
                                                          <div class="caption">
                                                              <p>Raise Credit/ Debit Note</p>
                                                          </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-sm-6 col-md-2">
                                                      <div class="thumbnail">
                                                          <img src="assets/img/client_panel/view_ccount.png" alt="view_Account">
                                                          <div class="caption">
                                                              <p>View Account</p>
                                                          </div>
                                                      </div>
                                                  </div>

                                              </div>
                                          </div>
                                      </div>
                                  </div>
                              </div><br />

                              <div class="panel" style="background-color: #9C9C9C;">
                                  <div class="panel-heading txtcolor">Clients Search Result (Sort By)</div>
                                 
                                      <thead>
                                          <tr>
                                              <th><i class="fa fa-user"></i> id</th>
                                              <th><i class="fa fa-user"></i> Name Hide</th>
                                              <th class="hidden-phone"><i class="fa fa-mobile-phone"></i> Mobile No</th>
                                              <th><i class="fa fa-envelope"></i> Email</th>
                                              <th><i class="fa fa-map-marker"></i> Post Code</th>
                                              <th><i class="fa fa-calendar"></i> DOB</th>
                                              <th><i class="fa fa-user"></i> Old Client ID</th>
                                              <th><i class="fa fa-file"></i> Note</th>
                                              <th><i class="fa fa-calendar"></i> Last Modified</th>
                                              <th><i class=" fa fa-edit"></i> Status</th>
                                              <th></th>
                                          </tr>
                                      </thead>
                                      <tbody>
                                          <tr>
                                              <td>00005283</td>
                                              <td>Mr.Alex Hope</td>
                                              <td>07756485265</td>
                                              <td>a.hope@warwick.ac.uk</td>
                                              <td>CV31 2EQ</td>
                                              <td>10/02/1992</td>
                                              <td></td>
                                              <td><button class="btn btn-primary btn-xs"><i class="fa fa-file-o fa-2x"></i></button></td>
                                              <td>2014-12-10 17:25:08</td>
                                              <td>
                                                  <div class="text-center">
                                                      <input type="checkbox" checked="" data-toggle="switch" />
                                                  </div>
                                              </td>
                                              <td>
                                                  <button class="btn btn-primary btn-xs"><i class="fa fa-pencil fa-2x"></i></button>
                                                  <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o fa-2x "></i></button>
                                              </td>
                                          </tr>
                                          <tr>
                                              <td>00005283</td>
                                              <td>Mr.Alex Hope</td>
                                              <td>07756485265</td>
                                              <td>a.hope@warwick.ac.uk</td>
                                              <td>CV31 2EQ</td>
                                              <td>10/02/1992</td>
                                              <td></td>
                                              <td><button class="btn btn-primary btn-xs"><i class="fa fa-file-o fa-2x"></i></button></td>
                                              <td>2014-12-10 17:25:08</td>
                                              <td>
                                                  <div class="text-center">
                                                      <input type="checkbox" checked="" data-toggle="switch" />
                                                  </div>
                                              </td>
                                              <td>
                                                  <button class="btn btn-primary btn-xs"><i class="fa fa-pencil fa-2x"></i></button>
                                                  <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o fa-2x "></i></button>
                                              </td>
                                          </tr>
                                          <tr>
                                              <td>00005283</td>
                                              <td>Mr.Alex Hope</td>
                                              <td>07756485265</td>
                                              <td>a.hope@warwick.ac.uk</td>
                                              <td>CV31 2EQ</td>
                                              <td>10/02/1992</td>
                                              <td></td>
                                              <td><button class="btn btn-primary btn-xs"><i class="fa fa-file-o fa-2x"></i></button></td>
                                              <td>2014-12-10 17:25:08</td>
                                              <td>
                                                  <div class="text-center">
                                                      <input type="checkbox" checked="" data-toggle="switch" />
                                                  </div>
                                              </td>
                                              <td>
                                                  <button class="btn btn-primary btn-xs"><i class="fa fa-pencil fa-2x"></i></button>
                                                  <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o fa-2x "></i></button>
                                              </td>
                                          </tr>
                                          <tr>
                                              <td>00005283</td>
                                              <td>Mr.Alex Hope</td>
                                              <td>07756485265</td>
                                              <td>a.hope@warwick.ac.uk</td>
                                              <td>CV31 2EQ</td>
                                              <td>10/02/1992</td>
                                              <td></td>
                                              <td><button class="btn btn-primary btn-xs"><i class="fa fa-file-o fa-2x"></i></button></td>
                                              <td>2014-12-10 17:25:08</td>
                                              <td>
                                                  <div class="text-center">
                                                      <input type="checkbox" checked="" data-toggle="switch" />
                                                  </div>
                                              </td>
                                              <td>
                                                  <button class="btn btn-primary btn-xs"><i class="fa fa-pencil fa-2x"></i></button>
                                                  <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o fa-2x "></i></button>
                                              </td>
                                          </tr>
                                          <tr>
                                              <td>00005283</td>
                                              <td>Mr.Alex Hope</td>
                                              <td>07756485265</td>
                                              <td>a.hope@warwick.ac.uk</td>
                                              <td>CV31 2EQ</td>
                                              <td>10/02/1992</td>
                                              <td></td>
                                              <td><button class="btn btn-primary btn-xs"><i class="fa fa-file-o fa-2x"></i></button></td>
                                              <td>2014-12-10 17:25:08</td>
                                              <td>
                                                  <div class="text-center">
                                                      <input type="checkbox" checked="" data-toggle="switch" />
                                                  </div>
                                              </td>
                                              <td>
                                                  <button class="btn btn-primary btn-xs"><i class="fa fa-pencil fa-2x"></i></button>
                                                  <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o fa-2x "></i></button>
                                              </td>
                                          </tr>
                                          <tr>
                                              <td>00005283</td>
                                              <td>Mr.Alex Hope</td>
                                              <td>07756485265</td>
                                              <td>a.hope@warwick.ac.uk</td>
                                              <td>CV31 2EQ</td>
                                              <td>10/02/1992</td>
                                              <td></td>
                                              <td><button class="btn btn-primary btn-xs"><i class="fa fa-file-o fa-2x"></i></button></td>
                                              <td>2014-12-10 17:25:08</td>
                                              <td>
                                                  <div class="text-center">
                                                      <input type="checkbox" checked="" data-toggle="switch" />
                                                  </div>
                                              </td>
                                              <td>
                                                  <button class="btn btn-primary btn-xs"><i class="fa fa-pencil fa-2x"></i></button>
                                                  <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o fa-2x "></i></button>
                                              </td>
                                          </tr>
                                          <tr>
                                              <td>00005283</td>
                                              <td>Mr.Alex Hope</td>
                                              <td>07756485265</td>
                                              <td>a.hope@warwick.ac.uk</td>
                                              <td>CV31 2EQ</td>
                                              <td>10/02/1992</td>
                                              <td></td>
                                              <td><button class="btn btn-primary btn-xs"><i class="fa fa-file-o fa-2x"></i></button></td>
                                              <td>2014-12-10 17:25:08</td>
                                              <td>
                                                  <div class="text-center">
                                                      <input type="checkbox" checked="" data-toggle="switch" />
                                                  </div>
                                              </td>
                                              <td>
                                                  <button class="btn btn-primary btn-xs"><i class="fa fa-pencil fa-2x"></i></button>
                                                  <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o fa-2x "></i></button>
                                              </td>
                                          </tr>
                                      </tbody>
                                 
                              </div>
                     
                      </table>
                  </div><!-- /content-panel --><br />
                  <nav>
                      <ul class="pagination pull-right">
                          <li class="bg-primary disabled"><a href="#" aria-label="First"><span aria-hidden="true"><< First</span></a></li>
                          <li class="bg-primary disabled"><a href="#" aria-label="Previuos"><span aria-hidden="true">< Previous</span></a></li>
                          <li class=" active bg-primary"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                          <li class="bg-primary"><a href="#">2 <span class="sr-only"></span></a></li>
                          <li class="bg-primary"><a href="#">3 <span class="sr-only"></span></a></li>
                          <li class="bg-primary"><a href="#">4 <span class="sr-only"></span></a></li>
                          <li class="bg-primary"><a href="#">5 <span class="sr-only"></span></a></li>
                          <li class="bg-primary"><a aria-label="Next" href="#"><span aria-hidden="true">> Next</span></a></li>
                          <li class="bg-primary"><a aria-label="Last" href="#"><span aria-hidden="true">>> Last</span></a></li>
                      </ul>
                  </nav>
              </div><!-- /col-md-12 -->
              </div><!-- /row -->

          </section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->
      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2014 - @cbstech
              <a href="dashboard.html#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>
      <!-- js placed at the end of the document so the pages load faster -->
     <script src="common/BootstrapNew/js/jquery.js"></script> 
      <script src="common/BootstrapNew/js/jquery-ui-1.9.2.custom.min.js"></script>
      <script src="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/lib/moment.min.js"></script>
      <script src="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/fullcalendar.min.js"></script>
      <script src="common/BootstrapNew/bootstrap/js/bootstrap.min.js"></script>
      <script class="include" type="text/javascript" src="common/BootstrapNew/js/jquery.dcjqaccordion.2.7.js"></script>
      <script src="common/BootstrapNew/js/jquery.scrollTo.min.js"></script>
      <script src="common/BootstrapNew/js/jquery.nicescroll.js"></script>

      <!--common script for all pages-->
      <script src="common/BootstrapNew/js/common-scripts.js"></script>
      <script type="text/javascript" src="common/BootstrapNew/gritter/js/jquery.gritter.js"></script>
      <script src="common/BootstrapNew/smartmenus-0.9.7/jquery.smartmenus.min.js"></script>
     
      <script src="common/BootstrapNew/Sticky-Sidebar-Navigation-Menu-with-jQuery-Vertical-Navigation/js/jquery.ssd-vertical-navigation.min.js"></script>
      <script src="common/BootstrapNew/Sticky-Sidebar-Navigation-Menu-with-jQuery-Vertical-Navigation/js/app.js"></script>

      <!--custom checkbox & radio-->

    <!--   <script type="text/javascript" src="assets/js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
      <script type="text/javascript" src="assets/js/bootstrap-daterangepicker/date.js"></script>
      <script type="text/javascript" src="assets/js/bootstrap-daterangepicker/daterangepicker.js"></script>
      <script type="text/javascript" src="assets/js/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
      <script src="assets/js/form-component.js"></script>
      <script src="assets/js/smartmenus-0.9.7/jquery.smartmenus.min.js"></script> -->


    
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

<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)" >
			<%@ include file="/application.jsp" %>
			
		</s:if>
