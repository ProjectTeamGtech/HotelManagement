<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

      <link rel="icon" href="common/BootstrapNew/img/favicon.ico">
    <title>YUVICARE</title>

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
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
            <a href="dashboard.html" class="logo"><img class="logoimg" src="WebContent/common/img/logo.png" /></a>
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

                  <p class="centered"><a href="dashboard.html"><img src="WebContent/common/img/ui-sam.jpg" class="img-thumbnail" width="60"></a></p>

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
              <h3 class="heading3"><i class="fa fa-angle-right"></i> Calendar</h3>
              <div class="row mt">
                  <div class="col-lg-12">
                      <div class="panel">
                          <div class="panel-body">
                              <div class="row">
                                  <div class="col-lg-2">
                                      <div class="input-group ">
                                          <span class="input-group-addon " id="basic-addon1"><i class="fa fa-calendar"></i></span>
                                          <input type="text" class="form-control" placeholder="26/12/2014" aria-describedby="basic-addon1">
                                      </div><!-- /input-group -->
                                  </div>
                                  <div class="col-lg-2">
                                      <select class="form-control">
                                          <option value="0">Location</option>
                                          <option value="0">All</option>
                                          <option value="1">Birmingham</option>
                                          <option value="4">Coventry</option>
                                          <option value="2">Nuneaton</option>
                                          <option value="5">Tamworth</option>
                                          <option value="3">Warwick</option>
                                      </select>
                                  </div>
                                  <div class="col-lg-1">
                                      <a href="addnewclient.html" class="btn btn-primary"><i class="fa fa-search"></i> Privew</a>
                                  </div>
                                  <!--<div class="col-lg-4 ">
                                      <div id="sb-search" class="sb-search">
                                          <form>
                                              <input class="sb-search-input" placeholder="search " type="text" value="" name="search" id="search">
                                              <input class="sb-search-submit" type="submit" value="">
                                              <span class="sb-icon-search"></span>
                                          </form>
                                      </div>
                                  </div>-->
                                  <div class="col-lg-3 pull-right ">
                                      <select class="form-control">
                                          <option>Showing All (8 of 8 Records)</option>
                                          <option value="0">5</option>
                                          <option value="1">10</option>
                                          <option value="4">15</option>
                                          <option value="2">20</option>
                                      </select>
                                  </div>
                                 
                              </div>
                          </div>
                      </div><!-- /form-panel -->
                  </div><!-- /col-lg-12 -->
              </div>
              <div class="row mt">
                  <aside class="col-lg-12 mt">
                      <section class="panel">
                          <div class="panel-body">
                              <div id="calendar" class="has-toolbar"></div>
                          </div>
                      </section>
                  </aside>
              </div>
          </section>
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

      <!--<script src="assets/js/ExpandingSearchBar/ExpandingSearchBar/js/uisearch.js"></script>
      <script src="assets/js/ExpandingSearchBar/ExpandingSearchBar/js/classie.js"></script>
      <script src="assets/js/ExpandingSearchBar/ExpandingSearchBar/js/uisearch.js"></script>-->

      <!--script for this page-->
      <script type="text/javascript">
          $(document).ready(function () {
              var unique_id = $.gritter.add({
                  // (string | mandatory) the heading of the notification
                  title: 'Welcome to Advance Practice Management',
                  // (string | mandatory) the text inside the notification
                  text: 'Advance Practice Management is Online Practice Management for Medical Facility.',
                  // (string | optional) the image to display on the left
                  image: 'assets/img/ui-sam.jpg',
                  // (bool | optional) if you want it to fade out on its own or just sit there
                  sticky: true,
                  // (int | optional) the time you want it to be alive for before fading out
                  time: '',
                  // (string | optional) the class name you want to apply to that specific message
                  class_name: 'my-sticky-class'
              });
              return false;
          });
      </script>

      <script>

          $(document).ready(function () {
              var currentLangCode = 'en';

              // build the language selector's options
              $.each($.fullCalendar.langs, function (langCode) {
                  $('#lang-selector').append(
                      $('<option/>')
                          .attr('value', langCode)
                          .prop('selected', langCode == currentLangCode)
                          .text(langCode)
                  );
              });

              // rerender the calendar when the selected option changes
              $('#lang-selector').on('change', function () {
                  if (this.value) {
                      currentLangCode = this.value;
                      $('#calendar').fullCalendar('destroy');
                      renderCalendar();
                  }
              });

              function renderCalendar() {
                  $('#calendar').fullCalendar({
                      header: {
                          left: 'prev,next today',
                          center: 'title',
                          right: 'month,agendaWeek,agendaDay'
                      },
                      defaultDate: '2014-11-12',
                      lang: currentLangCode,
                      buttonIcons: false, // show the prev/next text
                      weekNumbers: true,
                      editable: true,
                      eventLimit: true, // allow "more" link when too many events
                      events: [
                          {
                              title: 'All Day Event',
                              start: '2014-11-01'
                          },
                          {
                              title: 'Long Event',
                              start: '2014-11-07',
                              end: '2014-11-10'
                          },
                          {
                              id: 999,
                              title: 'Repeating Event',
                              start: '2014-11-09T16:00:00'
                          },
                          {
                              id: 999,
                              title: 'Repeating Event',
                              start: '2014-11-16T16:00:00'
                          },
                          {
                              title: 'Conference',
                              start: '2014-11-11',
                              end: '2014-11-13'
                          },
                          {
                              title: 'Meeting',
                              start: '2014-11-12T10:30:00',
                              end: '2014-11-12T12:30:00'
                          },
                          {
                              title: 'Lunch',
                              start: '2014-11-12T12:00:00'
                          },
                          {
                              title: 'Meeting',
                              start: '2014-11-12T14:30:00'
                          },
                          {
                              title: 'Happy Hour',
                              start: '2014-11-12T17:30:00'
                          },
                          {
                              title: 'Dinner',
                              start: '2014-11-12T20:00:00'
                          },
                          {
                              title: 'Birthday Party',
                              start: '2014-11-13T07:00:00'
                          },
                          {
                              title: 'Click for Google',
                              url: 'http://google.com/',
                              start: '2014-11-28'
                          }
                      ]
                  });
              }

              renderCalendar();
          });
      </script>
  
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

      <script>
          new UISearch(document.getElementById('sb-search'));
      </script>
</body>
</html>

<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)" >
			<%@ include file="/application.jsp" %>
			
		</s:if>
