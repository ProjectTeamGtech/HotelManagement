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
                background-color: #43b9be;
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


<body >
<div id="preloader">
  <div id="status">&nbsp;</div>
</div>

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
                                            <li><a href="#" onclick="openPopup('manageClient')" title="Patients"><i class="fa fa-user"></i> <span>Patients </span></a></li>
                                            <%
												 }
											%>
											<%
												if (loginInfo.isMedicalRecord()) {
											%>
                                            <li><a href="#" onclick="opencPopup('getPatientRecordEmr')" title="EMR"><i class="fa fa-medkit"></i> <span>EMR</span></a></li>
                                            <%
												 }
											%>
											
											
											
											<%if (loginInfo.isManageipd()) {%>
											<li>
                                                <a href="#" title="IPD"><i class="fa fa-bed"></i> IPD</a>
                                                <ul>
                                                    <!-- <li><a href="#" onclick="openIpdPopup('IpdDashboard')"><i class="fa fa-caret-right"></i> IPD Dashboard</a></li> -->
                                                    <%if(loginInfo.getSuperadminid()==1){ %>
                                                    	<li><a href="#" onclick="openPopup('Bed')"><i class="fa fa-caret-right"></i> Bed Master</a></li>
                                                    <% }%>
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
                                            </li>
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
												<li><a href="#" title="Prescription"><i class="fa fa-file-text-o"></i> Prescription</a>
                                                <ul>
                                                    <li>
                                                    <%if (loginInfo.getJobTitle().equals("Medical Store")) {%>
                                                    <a href="Prescription">
                                                    <%} else{%>
                                                    <a href="#" onclick="openPopup('Prescription')">
                                                    <%} %>
                                                    <i class="fa fa-caret-right"></i> View / Add / Update Prescription</a></li>
                                                </ul>
                                            </li>
											<%} %>
												
											<%if (loginInfo.isManageinvst()) {%>
												 <li><a href="#" title="Investigation"><i class="fa fa-flask"></i> Investigation</a>
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
											<%} %>
											
											 <%
												if (loginInfo.isReport() == true) {
											%>
											<li>
                                                <a href="#" title="Reports"><i class="fa fa-bar-chart-o"></i> Reports</a>
                                                <ul>
                                                	<li><a href="#" onclick="openPopup('MisChart')"><i class="fa fa-caret-right"></i> MIS Report</a></li>
                                                	<!-- <li><a href="#" onclick="openPopup('inputMis')"><i class="fa fa-caret-right"></i> MIS Report</a></li> -->
                                                    
                                                    <li>
                                                        <a href="#"><i class="fa fa-caret-right"></i> Accounts</a>
                                                        <ul>
                                                            <!-- <li><a href="#" onclick="openPopup('PractitionerListCommission?action=0')"><i class="fa fa-caret-right"></i> Practitioners Sharing Report</a></li> -->
                                                             <li>
                                                            	<a href="#"><i class="fa fa-caret-right"></i> Practitioners Share Report</a>
                                                                <ul>
                                                                   <li><a href="#" onclick="openPopup('PractitionerListCommission?action=0')"><i class="fa fa-caret-right"></i> Practitioners Share Report</a></li>
                                                                   <li><a href="#" onclick="openPopup('OPDPractitionerListCommission')"><i class="fa fa-caret-right"></i>OPD Practitioners Share Report</a></li>
                                                                </ul>
                                                            	
                                                            </li>
                                                            <li><a href="#" onclick="openPopup('ChargesRpt')"><i class="fa fa-caret-right"></i> Charges Report</a></li>
                                                            <li><a href="#" onclick="openPopup('invoiceReportChargesRpt')"><i class="fa fa-caret-right"></i> Invoice Report</a></li>
                                                            <li><a href="#" onclick="openPopup('paymentReportChargesRpt')"><i class="fa fa-caret-right"></i> Payment Report Big</a></li>
                                                            <li><a href="#" onclick="openPopup('smallpaymentReportChargesRpt')"><i class="fa fa-caret-right"></i> Payment Report Small</a></li>
                                                            <li><a href="#" onclick="openPopup('PendingPaymentReports')"><i class="fa fa-caret-right"></i> Add Debtors Report</a></li>
                                                            <li><a href="#" onclick="openPopup('invoiceCharges')"><i class="fa fa-caret-right"></i> CA Reports</a></li>
                                                            <li><a href="#" onclick="openPopup('auditorChargesRpt')"><i class="fa fa-caret-right"></i> Auditors Report</a></li>
                                                            <li><a href="#" onclick="openPopup('Doctorsharereport')"><i class="fa fa-caret-right"></i> Doctor Share Report</a></li>
                                                            <li><a href="#" onclick="openPopup('userwisepaymentChargesRpt')"><i class="fa fa-caret-right"></i> User Wise Payment Report</a></li>
                                                            <li><a href="#" onclick="openPopup('ipdSummary')"><i class="fa fa-caret-right"></i> IPD Daily Report</a></li>
                                                            <li><a href="#" onclick="openPopup('ipdopdRefundReportSummary')"><i class="fa fa-caret-right"></i>OPD/IPD Cancel/Refund Report</a></li>
                                                            <li><a href="#" onclick="openPopup('ipdBillRegisterSummary')"><i class="fa fa-caret-right"></i> IPD Bill Register</a></li>
                                                            <li><a href="#" onclick="openPopup('serviceRegisterDetailsSummary')"><i class="fa fa-caret-right"></i> Service Register Details</a></li>
                                                            <li><a href="#" onclick="openPopup('departmentWiseAnalysisReportSummary')"><i class="fa fa-caret-right"></i>Department Wise Analysis Report</a></li>
                                                            <li><a href="#" onclick="openPopup('chargesharereportSummary')"><i class="fa fa-caret-right"></i>Charge Share Report</a></li>
                                                            <li><a href="#" onclick="openPopup('billingreportSummary')"><i class="fa fa-caret-right"></i>Billing Report</a></li>
                                                            <li><a href="#" onclick="openPopup('discountreportSummary')"><i class="fa fa-caret-right"></i>Discount Report</a></li>
                                                             <li><a href="#" onclick="openPopup('cancelinvoicereportSummary')"><i class="fa fa-caret-right"></i>Cancel Invoice Report</a></li>
                                                        	<li><a href="#" onclick="openPopup('ipdmonthlyreportSummary')"><i class="fa fa-caret-right"></i>IPD Monthly Report</a></li>
                                                        	
                                                        	<li><a href="#" onclick="openPopup('hosprevnueProcessingAccount')"><i class="fa fa-caret-right"></i>Hospital Revenue</a></li>
                                                       		<li>	<a onclick="openPopup('newDetailedChargesRpt')" href="#"><i class="fa fa-caret-right"></i>Charges Report Detailed</a></li>
                                                        </ul>
                                                    </li>
                                                    <li>
                                                        <a href="#"><i class="fa fa-caret-right"></i> Clinical</a>
                                                        <ul>
                                                            <li><a href="#" onclick="openPopup('treatmentEpisodeListClinical')"><i class="fa fa-caret-right"></i> Treatment Episode List</a></li>
                                                            <li><a href="#" onclick="openPopup('Conditionreport')"><i class="fa fa-caret-right"></i> Patient Condition List</a></li>
                                                        </ul>
                                                    </li>
                                                    <li>
                                                        <a href="#"><i class="fa fa-caret-right"></i> Patient</a>
                                                        <ul>
                                                            <li><a href="#" onclick="openPopup('clientListClientRpt')"><i class="fa fa-caret-right"></i> Patient List</a></li>
                                                            <li><a href="#" onclick="openPopup('currentTreatmentNoFutureApmtsClientRpt')"><i class="fa fa-caret-right"></i> Current Treat With No Future Apmts</a></li>
                                                            <li><a href="#" onclick="openPopup('noApptActivityRecordClientRpt')"><i class="fa fa-caret-right"></i> No Appointment Activity Record</a></li>
                                                            <li><a href="#" onclick="openPopup('DNANoFutureAppClientRpt')"><i class="fa fa-caret-right"></i> DNA With No Future Appointment</a></li>
                                                            <li><a href="#" onclick="openPopup('noActivityRecordClientRpt')"><i class="fa fa-caret-right"></i> No Activity Record</a></li>
                                                        </ul>
                                                    </li>
                                                    <li>
                                                        <a href="#"><i class="fa fa-caret-right"></i> Summary</a>
                                                        <ul>
                                                            <li><a href="#" onclick="openPopup('Summary')"><i class="fa fa-caret-right"></i> DNA Analysis Report</a></li>
                                                            <li><a href="#" onclick="openPopup('appDNAKeptSummary')"><i class="fa fa-caret-right"></i> Appointment Kept vs DNA</a></li>
                                                            <li><a href="#" onclick="openPopup('treatmentReferralSummary')"><i class="fa fa-caret-right"></i> Treatment State By Referral</a></li>
                                                            <li><a href="#" onclick="openPopup('returningptsSummary')"><i class="fa fa-caret-right"></i> Returning Patients</a></li>
                                                            <li><a href="#" onclick="openPopup('odreportSummary')"><i class="fa fa-caret-right"></i> Outcome Discharge / Report</a></li>
                                                             <li><a href="#" onclick="openPopup('KPI')"><i class="fa fa-caret-right"></i> KPI Report </a></li>
                                                             
                                                            <li><a href="#"><i class="fa fa-caret-right"></i> Daily Report</a>
                                                                <ul>
                                                                    <li><a href="#" onclick="openPopup('rptoutstandingDailyReport')"><i class="fa fa-caret-right"></i> Report Outstanding</a></li>
                                                                    <li><a href="#" onclick="openPopup('newptsDailyReport')"><i class="fa fa-caret-right"></i> New Patients</a></li>
                                                                    <li><a href="#" onclick="openPopup('totalptsseenDailyReport')"><i class="fa fa-caret-right"></i> Total Patients Seen</a></li>
                                                                    <li><a href="#" onclick="openPopup('dnaotsreportDailyReport')"><i class="fa fa-caret-right"></i> DNA Outstanding Action</a></li>
                                                                </ul>
                                                            </li>
                                                             
                                                        </ul>
                                                    </li>
                                                    
                                                </ul>
                                            </li>
							
											<%
												}
											%>
											
                                         <!--    <li>
                                                <a href="#" title="Third Parties"><i class="fa fa-users"></i> Third Parties</a>
                                                <ul>
                                                    <li><a href="#" onclick="openPopup('showListThirdParty')"><i class="fa fa-caret-right"></i> View Third Party</a></li>
                                                    <li><a href="#" onclick="openPopup('addThirdParty')"><i class="fa fa-caret-right"></i> Add New Third Party</a></li>
                                                    <li><a href="#" onclick="openPopup('GP')"><i class="fa fa-caret-right"></i> View GP</a></li>
                                                    <li><a href="#" onclick="openPopup('allocationOutstandingReport')"><i class="fa fa-caret-right"></i>Allocation Receipt List</a></li>
                                                    <li><a href="#" onclick="openPopup('TPFollower')"><i class="fa fa-caret-right"></i>Third Party Follower</a></li>
                                                </ul>
                                            </li> -->
                                            
                                           
											
											
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


