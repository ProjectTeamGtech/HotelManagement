<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.AssesmentForms.eu.entity.Assessment"%>
<%@page import="java.util.ArrayList"%>
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

<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
 		

<!-- Bootstrap core CSS -->

<!--external css-->

<!-- <link href="common/BootstrapNew/fullcalendar-2.2.3/fullcalendar-2.2.3/fullcalendar.min.css" rel="stylesheet" />
<link href="common/BootstrapNew/gritter/css/jquery.gritter.css" rel="stylesheet" /> -->

<!-- <link href="common/BootstrapNew/smartmenus-0.9.7/css/sm-clean/sm-clean.css" rel="stylesheet" />
<link href="common/BootstrapNew/smartmenus-0.9.7/css/sm-core-css.css" rel="stylesheet" />
<link href="common/BootstrapNew/Sticky-Sidebar-Navigation-Menu-with-jQuery-Vertical-Navigation/css/app.css" rel="stylesheet" /> -->
<style>
            #header {
                background-color: #339966;
            }
            .texthead {
                font-size: 15px;
            }
            .setingcolr{
            	color:#95a2a9; 
            }
           body {
  overflow: hidden;
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
@media (max-width: 736px){
.bookbedboxirf {
    margin-left: -23px !important;
}
 .navnavtabstabsdarkleftmarg {
    margin-left: 1px;
}

.searchbookbedjsp {
    width: 42%;
}
	
}

        </style>
</head>


<body>
<div id="preloader">
  <div id="status">&nbsp;</div>
</div>
<!-- <div style="height: 1000px;background-color: red">
</div>
 -->




				<!-- ================================================
                ================= SIDEBAR Content ===================
                ================================================= -->
                <aside id="sidebar" class="miheight hidden-xs">
                    <div id="sidebar-wrap">
                        <div class="panel-group slim-scroll" role="tablist">
                            <div class="panel panel-default">
                            <div class="panel-heading" role="tab">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#sidebarNav">
                                        Menu Bar <i class="fa fa-angle-up"></i>
                                    </a>
                                </h4>
                            </div>
                                <div id="sidebarNav" class="panel-collapse collapse out" role="tabpanel">
                                    <div class="panel-body">
                                        <!-- ===================================================
                                        ================= NAVIGATION Content ===================
                                        ==================================================== -->
                                        <%
											LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
										%>
                                         <s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
                                        <ul id="navigation">
                                        <!-- CALENDAR-->
										<%-- 	<script>
												$('.datepicker').datepicker({
							
												});
							
												$(document).on("change", "#datepicker", function() {
													//  alert($(this).val())
													//alert(actionType);
													var date = $(this).val();
													var temp = date.split('-');
													var rdate = temp[2] + '/' + temp[1] + '/' + temp[0];
							
													if (actionType == 1) {
														getcaldate(rdate);
													}
							
													if (actionType == 0) {
							
														getcaldate(rdate);
														document.getElementById('weekfrm').submit();
							
													}
							
													if (actionType == 2) {
							
														getcaldate(rdate);
							
														document.getElementById('dayfrm').submit();
							
													}
							
												})
											</script> --%>
						
											<div class="hidden hidden-xs hidden-sm">
												<div id="datepicker"></div>
											</div>
							
							
											<!-- / calendar -->
                                            <%-- <li><a href="#" title="Dashbaord"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li> --%>
                                            <%-- <%
												 if (loginInfo.isAppointmentBooking() == true
															) { 
											%>
                                            <li><a href="#" onclick="openPopup('inputFinder')" title="Appointment Finder"><i class="fa fa-search"></i> <span>Finder</span></a></li>
                                            <%
												 }
											%> --%>
											<%
												if (loginInfo.isManageclient()) {
											%>
                                            <%-- <li><a href="#" onclick="openPopup('manageClient')" title="Patients"><i class="fa fa-user"></i> <span>Patients </span></a></li> --%>
                                            <%
												 }
											%>
											<%
												if (loginInfo.isMedicalRecord()) {
											%>
                                            <%-- <li><a href="#" onclick="opencPopup('getPatientRecordEmr')" title="EMR"><i class="fa fa-medkit"></i> <span>EMR</span></a></li> --%>
                                            <%
												 }
											%>
											
											
											
											<%if (loginInfo.isManageipd()) {%>
											<%-- <li>
                                                <a href="#" title="IPD"><i class="fa fa-bed"></i> IPD</a>
                                                <ul>
                                                    <!-- <li><a href="#" onclick="openIpdPopup('IpdDashboard')"><i class="fa fa-caret-right"></i> IPD Dashboard</a></li> -->
                                                    <li><a href="#" onclick="openPopup('Bed')"><i class="fa fa-caret-right"></i> Bed Master</a></li>
                                                    <li><a href="#" onclick="openPopup('redirectbookbedBed')"><i class="fa fa-caret-right"></i> Bed Status</a></li>
                                                    <li><a href="#" onclick="openPopup('Diagnosis')"><i class="fa fa-caret-right"></i> Manage Diagnosis</a></li>
                                                </ul>
                                            </li>
                                            <li class="hidden">
                                                <a href="#" title="Expenses"><i class="fa fa-plane"></i> <span>Expenses</span></a>
                                                <ul>
                                                    <li><a href="#" onclick="openPopup('ExpenceManagement')"><i class="fa fa-caret-right"></i> Add/Edit Expenses </a></li>
                                                    <li><a href="#" onclick="openPopup('viewreportExpenceManagement')"><i class="fa fa-caret-right"></i> Expenses Reports</a></li>
                                                </ul>
                                            </li> --%>
											<%} %>
											
											<%-- <% if (loginInfo.isFullFinance()) {%>
													<li>
		                                                <a href="#" title="Accounts"><i class="fa fa-calculator"></i> Accounts</a>
		                                                <ul>
		                                                    <li><a href="#" onclick="openPopup('Statement')"><i class="fa fa-caret-right"></i> View Account</a></li>
		                                                    <li><a href="#" onclick="openPopup('pendingChargesCompleteApmt')"><i class="fa fa-caret-right"></i> Pending Charges</a></li>
		                                                    <li><a href="#" onclick="openPopup('Accounts')"><i class="fa fa-caret-right"></i> Create Invoice</a></li>
		                                                    <li><a href="#" onclick="openPopup('ProcessingAccount')"><i class="fa fa-caret-right"></i> Record Payment</a></li>
		                                                    <li><a href="#" onclick="openPopup('OutstandingReport')"><i class="fa fa-caret-right"></i> Track Outstanding Invoices</a></li><!--
		                                                    <li><a href="#" onclick="openPopup('inputAdditional')"><i class="fa fa-caret-right"></i> Raise Credit / Debit Note</a></li>
		                                                    --><li><a href="#" onclick="openPopup('creditListAdditional')"><i class="fa fa-caret-right"></i> View Credit Account</a></li>
		                                                     <li><a href="#" onclick="openPopup('debitAdditional')"><i class="fa fa-caret-right"></i> Add Charges</a></li>
		                                                     <li><a href="#" onclick="openPopup('creditAdditional')"><i class="fa fa-caret-right"></i> Advance & Refund</a></li>
		                                                </ul>
		                                            </li>
											<%} %> --%>
                                            
                                            
                                            <li class="hidden"><a href="#" onclick="openPopup('http://www.sim.escapeq.com')" title="SIM"><i class="fa fa-desktop"></i> <span>SIM</span></a></li>
                                            
                                            
                                            <%if (loginInfo.isManageprisc()) {%>
												<%-- <li><a href="#" title="Prescription"><i class="fa fa-file-text-o"></i> Prescription</a>
                                                <ul>
                                                    <li>
                                                    <%if (loginInfo.getJobTitle().equals("Medical Store")) {%>
                                                    <a href="Prescription">
                                                    <%} else{%>
                                                    <a href="#" onclick="openPopup('Prescription')">
                                                    <%} %>
                                                    <i class="fa fa-caret-right"></i> View / Add / Update Prescription</a></li>
                                                </ul>
                                            </li> --%>
											<%} %>
												
											<%if (loginInfo.isManageinvst()) {%>
											<%-- 	 <li><a href="#" title="Investigation"><i class="fa fa-flask"></i> Investigation</a>
                                                <ul>
                                                    <li>
                                                    <%if (loginInfo.getJobTitle().equals("Pathlab")) {%>
														<a href="Investigation" >
													<%} else{%>
                                                    <a href="#" onclick="openPopup('Investigation')">
                                                    <%} %>
                                                    <i class="fa fa-caret-right"></i> View / Add / Update Investigation</a></li>
                                                </ul>
                                            </li>
 --%>											<%} %>
											
											 <%
												if (loginInfo.isReport() == true) {
											%>
											<li>
                                                <a href="#" title="Transaction"><i class="fa fa-bar-chart-o"></i> Transaction</a>
                                                <ul>
                                                	<li><a href="#" onclick="opencPopup('ExpenceManagement')"><i class="fa fa-caret-right"></i> Manage Voucher</a></li>
                                                	<!-- <li><a href="#" onclick="openPopup('inputMis')"><i class="fa fa-caret-right"></i> MIS Report</a></li> -->
                                                    
                                                    <li>
                                                        <a href="#"><i class="fa fa-caret-right"></i> Create Voucher</a>
                                                        <ul>
                                                            <!-- <li><a href="#" onclick="openPopup('PractitionerListCommission?action=0')"><i class="fa fa-caret-right"></i> Practitioners Sharing Report</a></li> -->
   <!--                                                           <li>
                                                            	<a href="#"><i class="fa fa-caret-right"></i> Practitioners Share Report</a>
                                                                <ul>
                                                                   <li><a href="#" onclick="openPopup('PractitionerListCommission?action=0')"><i class="fa fa-caret-right"></i> Practitioners Share Report</a></li>
                                                                   <li><a href="#" onclick="openPopup('OPDPractitionerListCommission')"><i class="fa fa-caret-right"></i>OPD Practitioners Share Report</a></li>
                                                                </ul>
                                                            	
                                                            </li> -->
                                                            <li><a href="#" onclick="showselectedvouchrpopup('Payment')"><i class="fa fa-caret-right"></i> Payment</a></li>
                                                            <li><a href="#" onclick="showselectedvouchrpopup('Receipt')"><i class="fa fa-caret-right"></i> Receipt</a></li>
                                                            <li><a href="#" onclick="showselectedvouchrpopup('Contra')"><i class="fa fa-caret-right"></i> Contra</a></li>
                                                            <li><a href="#" onclick="showselectedvouchrpopup('Journal')"><i class="fa fa-caret-right"></i> Journal</a></li>
                                                             <li><a href="#" onclick="showselectedvouchrpopup('Opening')"><i class="fa fa-caret-right"></i> Opening</a></li>
                                                             <li><a href="#" onclick="showselectedvouchrpopup('Purchase')"><i class="fa fa-caret-right"></i> Purchase</a></li>
                                                            
                                                          
                                                        </ul>
                                                    </li>
                                                    <!-- <li>
                                                        <a href="#"><i class="fa fa-caret-right"></i> Clinical</a>
                                                        <ul>
                                                            <li><a href="#" onclick="openPopup('treatmentEpisodeListClinical')"><i class="fa fa-caret-right"></i> Treatment Episode List</a></li>
                                                            <li><a href="#" onclick="openPopup('Conditionreport')"><i class="fa fa-caret-right"></i> Patient Condition List</a></li>
                                                        </ul>
                                                    </li> -->
                                                  
                                             
                                                </ul>
                                            </li>
							
											<%
												}
											%>
                                            <li>
                                                <a href="#" title="Banking"><i class="fa fa-users"></i>Banking</a>
                                                <ul>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Bank Reconciliation</a></li>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Account Transfer</a></li>
                                                    
                                                   
                                                </ul>
                                            </li>
                                            
                                            
                                                <li>
                                                <a href="#" title="Ledger"><i class="fa fa-users"></i>Ledger</a>
                                                <ul>
                                                    <li class="hidden"><a href="#" onclick="openPopup('viewledrportAppointmentType')"><i class="fa fa-caret-right"></i> View Ledger</a></li>
                                                    <li><a href="#" onclick="openPopup('ledgAppointmentType')"><i class="fa fa-caret-right"></i> Manage Ledger</a></li>
                                                    <!-- <li><a href="#" onclick="openPopup('ocrptsAppointmentType')"><i class="fa fa-caret-right"></i> Opening & Closing Report</a></li> -->
                                                    <li><a href="#" onclick="openPopup('aheadAppointmentType')"><i class="fa fa-caret-right"></i> Manage Ledger Head</a></li>
                                                 </ul>
                                            </li>
                                            
                                              <li>
                                                <a href="#" title="Reports"><i class="fa fa-users"></i>Reports</a>
                                                <ul>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Unpaid Account</a></li>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Sales Payment</a></li>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Incoming Stmt</a></li>
                                                    <li><a href="#" onclick="openPopup('tbAppointmentType')"><i class="fa fa-caret-right"></i>Trial Balance</a></li>
                                                 
                                                    <!--  <li><a href="#" onclick="openPopup('balancesheethorizontalAppointmentType')"><i class="fa fa-caret-right"></i> View Ledger</a></li> -->
              
               <!--                                      <li><a href="#" onclick="openPopup('balancesheetverticalAppointmentType')"><i class="fa fa-caret-right"></i> Manage Ledger</a></li>
                                                    <li><a href="#" onclick="openPopup('profitlosshorizontalAppointmentType')"><i class="fa fa-caret-right"></i> Opening & Closing Report</a></li>
                                                    <li><a href="#" onclick="openPopup('profitlossverticalAppointmentType')"><i class="fa fa-caret-right"></i> Manage Ledger Head</a></li> -->
                                                   
                                                   
                                                </ul>
                                            </li>
                                            
                                            
                                             <li>
                                                <a href="#" title="Analysis"><i class="fa fa-users"></i>Analysis</a>
                                                <ul>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Cash Flow</a></li>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Funds Flow</a></li>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i> Ratio Analysis</a></li>
                                                    <li><a href="#" ><i class="fa fa-caret-right"></i>Trend Analysis</a></li>
                                                    
                                                   
                                                </ul>
                                            </li>
                                           
											
											
											<%
												if (loginInfo.isAssessmentForms() == true) {
											%>
											 <li class="hidden">
                                                <a href="#" title="Assesment Form"><i class="fa fa-file-o"></i> <span>Assesment Form</span></a>
                                                <ul>
                                                    <li><a href="#" onclick="openPopup('showtemplateAssesmentForms')"><i class="fa fa-caret-right"></i>View / Edit Template</a></li>
                                                    <li>
                                                        <a href="#"><i class="fa fa-caret-right"></i> Form Builder</a>
                                                        <ul>
                                                            <li><a href="#" onclick="openPopup('AssesmentMasterForms')"><i class="fa fa-caret-right"></i> Add/ Edit Feild</a></li>
                                                            <li><a href="#" onclick="openPopup('inputAssesmentForms')"><i class="fa fa-caret-right"></i> Create New Form</a></li>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </li>     
											<%
												}
													
											%>
											
						                      <%
													if (loginInfo.getUserType() == 3) {
												%>
												<li><a href="PracticeManager" title="Practice Manager"><i class="fa fa-user-secret"></i> Practice Manager</a></li>
												<%
													}
												%>                
                                            
                                        </ul>
                                        <!--/ NAVIGATION Content -->
                                        
                                        </s:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </aside>
                <!--/ SIDEBAR Content -->
     
  				<aside id="rightbar" class="hidden">

                    <div role="tabpanel">
                        <!-- Tab panes -->
                        <div class="tab-content1">
                            <div role="tabpanel" class="tab-pane active" id="settings">
                                <h6><strong class="setingcolr">Settings</strong> </h6>

                                <ul class="settings">
                                <li>
                                
                                               
                                                <ul>
                                                
							
													<%if (loginInfo.isDiaryManagement() == true) {%>
                                                    <li><a href="#" onclick="openPopup('DiaryMangent')" class="settingcolr"><i class="fa fa-caret-right"></i> Manage Diary</a></li>
                                                    <%
														}
													%>	
						
                                                    <li><a href="#" onclick="openPopup('UserProfile')" class="settingcolr"><i class="fa fa-caret-right"></i> Manage User</a></li>
                                                    <%if (loginInfo.isManagemaster()) {%>	
                                                    <li><a href="#" onclick="openPopup('bedlistmasterBed?selectedid=22')" class="settingcolr"><i class="fa fa-caret-right"></i> Setup Master Data</a></li>
                                                    <%} %>	
                                                    <li><a href="#" onclick="openPopup('changePasswordPageResetPassword')" class="settingcolr"><i class="fa fa-caret-right"></i> Change Account Password</a></li>
                                                    <%if (loginInfo.isManageclinic()) {%>
													
                                                            <li><a href="#" onclick="openPopup('profileClinicRegistration')" class="settingcolr"><i class="fa fa-caret-right"></i> Clinic Profile</a></li>
                                                            <li><a href="#" onclick="openPopup('locationClinicRegistration')" class="settingcolr"><i class="fa fa-caret-right"></i> Clinic Location</a></li>
                                                        
													<%
														}
													%>
                                                   
                                                   <%
														if (loginInfo.isCommunication() == true) {
													%>
													
                                                            <li><a href="#" onclick="openPopup('EmailTemplate')" class="settingcolr"><i class="fa fa-caret-right"></i> View Email Template</a></li>
                                                            <li><a href="#" onclick="openPopup('checkEmailSendEmailTemplate')" class="settingcolr"><i class="fa fa-caret-right"></i> Configure Clinic Email</a></li>
                                                            <li><a href="#" onclick="openPopup('SMSTemplate')" class="settingcolr"><i class="fa fa-caret-right"></i> View SMS Template</a></li>
                                                       
													<%
														}
													%>
                                                    
                                                    
                                                    
                                                    
                                                </ul>
                                            </li>
                                            <li>
                                        <div class="form-group">
                                            <label class="col-xs-8 control-label">IPD Alarm</label>
                                            <div class="col-xs-4 control-label">
                                                <div class="onoffswitch greensea">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline" checked="">
                                                    <label class="onoffswitch-label" for="show-offline">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        
                                    </li>
                                     <li>
                                        <div class="form-group">
                                            <label class="col-xs-8 control-label">IPD Email Sending</label>
                                            <div class="col-xs-4 control-label">
                                                <div class="onoffswitch greensea">
                                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-fullname">
                                                    <label class="onoffswitch-label" for="show-fullname">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                    </div>

                </aside>
	<%--  <script>
          new UISearch(document.getElementById('sb-search'));
      </script> --%>
      
      <script>
      	$(window).on('load', function() { // makes sure the whole site is loaded 
		  $('#status').fadeOut(); // will first fade out the loading animation 
		  $('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website. 
		  $('body').delay(350).css({'overflow':'visible'});
		})
      </script>
</body>
</html>


