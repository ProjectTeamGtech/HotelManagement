<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Yuvicare</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/png"
	href="newMnsIpd/assets/images/Manas_Yuvicare_Circle Favicon.png">
 <!--  <link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap"
	rel="stylesheet">   -->

<!-- <link rel="stylesheet" href="newMnsIpd/assets/css/open_Sans.css"> -->
<link rel="stylesheet" href="newMnsIpd/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="newMnsIpd/assets/css/font-awesome.min.css">
<link rel="stylesheet" href="newMnsIpd/assets/css/themify-icons.css">
<link rel="stylesheet" href="newMnsIpd/assets/css/metisMenu.css">
<link rel="stylesheet" href="newMnsIpd/assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="newMnsIpd/assets/css/slicknav.min.css">
<!-- amchart css -->
<!-- Speed -->
<!-- <link rel="stylesheet"
	href="https://www.amcharts.com/lib/3/plugins/export/export.css"
	type="text/css" media="all" /> -->
<!-- others css -->

<link rel="stylesheet" href="newMnsIpd/assets/css/typography.css">
<link rel="stylesheet" href="newMnsIpd/assets/css/default-css.css">
<link rel="stylesheet" href="newMnsIpd/assets/css/styles.css">

<link rel="stylesheet" href="newMnsIpd/assets/css/responsive.css">




<link href="common/css/Style.css" rel="stylesheet" type="text/css" />
<link href="common/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="common/css/responsive.css" rel="stylesheet" type="text/css" />

<script src="common/js/jquery.js" type="text/javascript"></script>
<script src="common/js/jquery.alerts.js" type="text/javascript"></script>
<link href="common/css/jquery.alerts.css" rel="stylesheet"
	type="text/css" media="screen" />

<link href="common/Bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css" />
<link href="common/Bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="common/chosen_v1.1.0/chosen.css" rel="stylesheet"
	type="text/css" />

<!-- <link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet"> -->

<link href="common/Font-Awesome-master/css/font-awesome.min.css"
	rel="stylesheet">
<!-- modernizr css -->

<script type="text/javascript" src="common/js/fullscreen.js"></script>
<link href="manasmaindashboard/css/oldbootstrapcode.css"
	rel="stylesheet" type="text/css" />

<script src="manasopd/assets/js/vendor/jquery-2.2.4.min.js"></script>
<!-- Speed  -->
<%-- <script type="text/javascript" src="js/script.js"></script> --%>
<!-- Speed  -->
<%-- <script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script> --%>
<!-- bootstrap 4 js -->
<script src="manasopd/assets/js/popper.min.js"></script>
<script src="manasopd/assets/js/bootstrap.min.js"></script>
<script src="manasopd/assets/js/owl.carousel.min.js"></script>
<script src="manasopd/assets/js/metisMenu.min.js"></script>
<%-- <script src="manasopd/assets/js/jquery.slimscroll.min.js"></script> --%>
<script src="manasopd/assets/js/jquery.slicknav.min.js"></script>


<script src="newMnsIpd/assets/js/vendor/modernizr-2.8.3.min.js"></script>


<script src="common/js/jquery/additional-validation-methods.js"
	type="text/javascript"></script>

<script src="common/js/jquery/jquery.validate.js" type="text/javascript"></script>


<script type="text/javascript" src="common/js/fullscreen.js"></script>

<script type="text/javascript" src="common/js/masterValidators.js"></script>

<script type="text/javascript"
	src="common/js/jquery/bootstrap-growl-master/bootstrap-growl.min.js"></script>


<script src="common/Bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>




<script src="popupdialog/dialog/js/jquery-1.10.2.js"
	type="text/javascript"></script>
<script src="popupdialog/dialog/js/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="popupdialog/dialog/js/jquery.ui.core.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.widget.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.datepicker.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.dialog.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.draggable.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.droppable.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.sortable.js"></script>

<script src="popupdialog/dialog/js/jquery.ui.mouse.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.button.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.position.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.tooltip.js"></script>
<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>



<!-- notifications -->
<!-- <link rel="stylesheet" type="text/css"
	href="dist/styles/jquery.stickynotif.min.css" /> -->
	<link rel="stylesheet" type="text/css"
	href="_assets/newtheme/css/main.css" />

<!-- Speed  -->
<%-- <script src="dist/jquery.stickynotif.min.js"></script> --%>
<script src="common/blockui-master/jquery.blockUI.js"
	type="text/javascript"></script>

</head>
<style>
.col-1 {
	padding-left: 0px;
	padding-right: 0px;
}

li span {
	font-size: 12px;
	color: black;
	vertical-align: middle;
}

li h5 {
	font-size: 12px;
	color: #818181;
	vertical-align: middle;
	display: inline;
}

.modal-dialog {
	height: 0 !important;
}

@media only screen and (min-width:1024px) and (max-width:1500px) {
	li h5 {
		font-size: 8px;
		color: black;
		vertical-align: middle;
		display: inline;
	}
}

@media only screen and (min-width:1520px) and (max-width:1900px) {
	li h5 {
		font-size: 10px;
	}
}

@media only screen and (min-width:768px) and (max-width:1000px) {
	li h5 {
		font-size: 10px;
	}
}

a {
	color: #15536E;
}

a :hover {
	color: #15536E;
}

hr {
	margin-top: 0rem;
	margin-bottom: 0rem;
	border: 0;
	border-top-color: currentcolor;
	border-top-style: none;
	border-top-width: 0px;
	border-top: 2px solid #D3D3D3;
}

.mybtn {
	/* position: absolute !important; */
	font-size: 14px !important;
	color: #fff !important;
	background-color: #15536E !important;
	border-color: #15536E !important;
	border-radius: 1.25rem
}

.mybtn-orange{
	/* position: absolute !important; */
	font-size: 14px !important;
	color: #fff !important;
	background-color: #fe9b00 !important;
	border-color: #fe9b00 !important;
	border-radius: 1.25rem
}

.card-isolation hover {
	background: blue;
}

.modal-title {
	margin: 0;
	line-height: 1.42857143;
	font-size: 13px;
	padding-top: 0px !important;
}

.fontpup {
	font-size: 11px !important;
	line-height: 14px;
}

.manasnewipdimg {
	width: 16% !important;
	height: 23px !important;
	float: right !important;
}

.bookdebedcss {
    background-color: #ebedf1 !important;
    color: #fff !important;
    padding: 5px 5px 5px 5px !important;
    margin-bottom: 6px;
}
.modal-header{
background: #4eb6c2 none repeat scroll 0% 0% !important;
}

</style>
<body>
	<%
		LoginInfo loginInfo111 = LoginHelper.getLoginInfo(request);
	%>

	<div class="page-container sbar_collapsed">
		<!-- sidebar menu area start -->
		<div class="sidebar-menu">
			<div class="sidebar-header" style="padding-top: 4px;">
				<div class="logo">
					<img src="liveData/clinicLogo/<s:property value="clinicLogo"/>" class="hidden" alt="logo" style="height: 86px;width: 100%;">
					<img src="newMnsIpd/assets/images/Manas_Yuvicare_Logo.svg" style="height: 70px;width: 100%;">
				</div>
			</div>
			<div class="main-menu">
				<div class="">
					<nav>
					
					 <ul class="nav" role="">
                        	<s:if test="mainaction=='marketingdashboard'">
                        		<li role="presentation" class="active" ><a href="#dashboard" aria-controls="dashboard" role="tab" data-toggle="tab" aria-expanded="true">Dashboard</a></li>
                           	</s:if>
                        	<s:else>
                        		<li role="presentation"  ><a href="#dashboard" aria-controls="dashboard" role="tab" data-toggle="tab" aria-expanded="true">Dashboard</a></li>
                            </s:else>
                        	
                        	<s:if test="mainaction=='marketing_connect'">
                        		<li role="presentation" class="active"><a href="#email" aria-controls="email" role="tab" data-toggle="tab" aria-expanded="true">Connect</a></li>
                            </s:if>
                        	<s:else>
                        		<li role="presentation"><a href="#email" aria-controls="email" role="tab" data-toggle="tab" aria-expanded="true">Connect</a></li>
                            </s:else>
                            
                        	<s:if test="mainaction=='marketing_history'">
                        		<li role="presentation" class="active"><a href="#history" aria-controls="history" role="tab" data-toggle="tab" aria-expanded="false"> History</a></li>
                            </s:if>
                        	<s:else>
                        		<li role="presentation"><a href="#history" aria-controls="history" role="tab" data-toggle="tab" aria-expanded="false"> History</a></li>
                            </s:else>
                        	<li role="presentation" class="hidden"><a href="#bde" aria-controls="bde" role="tab" data-toggle="tab" aria-expanded="false"> BDE</a></li>
                            <li role="presentation"><a href="#"  role="tab" onclick="openPopup('followupdashboardClient')" >Follow Up</a></li>
                            <li role="presentation"><a href="#"  role="tab" onclick="openPopup('diagnosisdashboardInitialDischarge')" >Diagnosis Dashboard</a></li>
                            <li role="presentation"><a href="#"  role="tab" onclick="openPopup('addCompleteInfoforAfitechRegistration')" >Add</a></li>
                            <li role="presentation"><a href="#"  role="tab" onclick="openPopup('manageClient')" >Show Record</a></li>
                               <li role="presentation"><a href="#"  role="tab" onclick="openPopup('targetDiaryMangent')" >Show Target</a></li>
                                <li role="presentation"><a href="#"  role="tab" onclick="openPopup('targetDiaryMangent')" >Show Test</a></li>
                           
                        	<s:form class="form-inline" action="marketingDiaryMangent" id="mischartfrm" theme="simple" style="display:inline-flex;">
                            	<s:hidden name="action" id="action"></s:hidden>
                            	<s:hidden name="mainaction" value="marketingdashboard"></s:hidden>
                        	</s:form>
                        </ul>
<%-- 						<ul class="metismenu" id="menu">
							<li >
                                <a href="index.html" ><img src="newMnsIpd/assets/images/Home.png"><span>Home</span></a>
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/Appointments.png"><span>Appointments
                                    </span></a>
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/Patients.png"><span>Patients</span></a>
                                <ul class="collapse">
                                    <li><a href="">some text</a></li>
                                    <li><a href="">some text</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/Accounts.png"><span>Accounts</span></a>
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/Pharmacy.png"><span>Pharmacy</span></a>    
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/Inventory.png"></i>
                                    <span>Inventory</span></a>
                            </li>
                            <li><a href="my_opd.html"><img src="newMnsIpd/assets/images/My opd_icon.png"> <span>My OPD</span></a></li>
                            <li><a href=""><img src="newMnsIpd/assets/images/IPD_Icon.png"> <span>IPD</span></a></li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/MIS_Icon.png"> <span>MIS</span></a>
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/EMR_Icon.png">
                                    <span>EMR</span></a>
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/Chat Room.png"> <span>Chat Room
                                        </span></a>  
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/Calendar.png"> <span>Calendar
                                        </span></a>
                                
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/Help Center.png"> <span>Help Center
                                        </span></a>
                                
                            </li>
                            <li>
                                <a href="" ><img src="newMnsIpd/assets/images/Settings.png"> <span>Settings
                                        </span></a>
                                
                            </li>

							<%
								if (loginInfo111.isManageclient()) {
							%>
							<li><a href="#" onclick="openPopup('manageClient')"
								title="Patients"><i class="fa fa-user"></i> <span>Patients
								</span></a></li>
							<%
								}
							%>
							
							<%
								if (loginInfo111.isKalmegha()) {
							%>
							<li><a href="#" onclick="openPopup('ipdreportCommonnew')"
								title="Patients"><i class="fa fa-user"></i> <span>Daily IPD Report
								</span></a></li>
							<%
								}
							%>
							
							<%
								if (loginInfo111.isMatrusevasang()) {
							%>
							<li><a href="#" onclick="opencPopup('ipdfakeConsultreportCommonnew')"
								title="Patients"><i class="fa fa-user"></i> <span>Daily IPD Report
								</span></a></li>
							<%
								}
							%>
							
							<li><a href="#" onclick="opencPopup('ipdfakeConsultreportCommonnew')"
								title="Patients"><i class="fa fa-user"></i> <span>Daily IPD Report
								</span></a></li>
							
							
							
							<li><a href="#" onclick="opencPopup('ipdfakeConsultreportCommonnew')"
								title="Patients"><i class="fa fa-user"></i> <span>Daily IPD Report
								</span></a>
							</li>
							<%
								if (loginInfo111.isMedicalRecord()) {
							%>
							<li><a href="#" onclick="opencPopup('getPatientRecordEmr')"
								title="EMR"><i class="fa fa-medkit"></i> <span>EMR</span></a></li>
							<%
								}
							%>

							<%
								if (loginInfo111.isManageipd()) {
							%>
							<li><a href="#" title="IPD"><i class="fa fa-bed"></i>
									IPD</a>
								<ul class="collapse">
									<%
										if (loginInfo111.getSuperadminid() == 1) {
									%>
									<li><a href="#" onclick="openPopup('Bed')"><i
											class="fa fa-caret-right"></i> Bed Master</a></li>
									<%
										}
									%>
									<li><a href="#" onclick="openPopup('redirectbookbedBed')"><i
											class="fa fa-caret-right"></i> Bed Status</a></li>
									<li><a href="#" onclick="openPopup('Diagnosis')"><i
											class="fa fa-caret-right"></i> Manage Diagnosis</a></li>
								</ul></li>
							<li class="hidden"><a href="#" title="Expenses"><i
									class="fa fa-plane"></i> <span>Expenses</span></a>
								<ul>
									<li><a href="#" onclick="openPopup('ExpenceManagement')"><i
											class="fa fa-caret-right"></i> Add/Edit Expenses </a></li>
									<li><a href="#"
										onclick="openPopup('viewreportExpenceManagement')"><i
											class="fa fa-caret-right"></i> Expenses Reports</a></li>
								</ul></li>
							<%
								}
							%>
							
							
							<li><a href="#" onclick="openPopup('currentpatientsSummary')"
								title="Patients"><span>IPD Current Patient Report
								</span></a></li>

							<li class="hidden"><a href="#"
								onclick="openPopup('http://www.sim.escapeq.com')" title="SIM"><i
									class="fa fa-desktop"></i> <span>SIM</span></a></li>

                            <li><a href="#" onclick="openPopup('patienttransactionrptIpdDashboard')"
								title="Patients"><span>Patient Daily Transaction Summary
								</span></a></li>
							<%
								if (loginInfo111.isManageprisc()) {
							%>
							<li class="hidden"><a href="#" title="Prescription"><i
									class="fa fa-file-text-o"></i> Prescription</a>
								<ul>
									<li>
										<%
											if (loginInfo111.getJobTitle().equals("Medical Store")) {
										%> 
											<a href="Prescription">
										<%
 											} else {
 										%> 
 											<a href="#" onclick="openPopup('Prescription')"> 
 										<%
 											}
										%> 
											<i class="fa fa-caret-right"></i> View / Add / Update
												Prescription
										</a>
									</li>
								</ul>
							</li>
							<%
								}
							%>

							<%
								if (loginInfo111.isManageinvst()) {
							%>
							<li class="hidden"><a href="#" title="Investigation"><i
									class="fa fa-flask"></i> Investigation</a>
								<ul>
									<li>
										<%
											if (loginInfo111.getJobTitle().equals("Pathlab")) {
										%> <a href="Investigation"> 
										<%
									 		} else {
									 	%> 
									 		<a href="#" onclick="openPopup('Investigation')"> 
									 	<%
									 		}
									 	%> 
									 		<i class="fa fa-caret-right"></i> View / Add / Update
												Investigation
										</a>
									</li>
								</ul>
							</li>
							<%
								}
							%>

							<%
								if (loginInfo111.isReport() == true) {
							%>
							<li class="hidden"><a href="#" title="Reports"><i
									class="fa fa-bar-chart-o"></i> Reports</a>
								<ul>
									<li><a href="#" onclick="openPopup('MisChart')"><i
											class="fa fa-caret-right"></i> MIS Report</a></li>
									<!-- <li><a href="#" onclick="openPopup('inputMis')"><i class="fa fa-caret-right"></i> MIS Report</a></li> -->

									<li><a href="#"><i class="fa fa-caret-right"></i>
											Accounts</a>
										<ul>
											<!-- <li><a href="#" onclick="openPopup('PractitionerListCommission?action=0')"><i class="fa fa-caret-right"></i> Practitioners Sharing Report</a></li> -->
											<li><a href="#"><i class="fa fa-caret-right"></i>
													Practitioners Share Report</a>
												<ul>
													<li><a href="#"
														onclick="openPopup('PractitionerListCommission?action=0')"><i
															class="fa fa-caret-right"></i> Practitioners Share Report</a></li>
													<li><a href="#"
														onclick="openPopup('OPDPractitionerListCommission')"><i
															class="fa fa-caret-right"></i>OPD Practitioners Share
															Report</a></li>
												</ul></li>
											<li><a href="#" onclick="openPopup('ChargesRpt')"><i
													class="fa fa-caret-right"></i> Charges Report</a></li>
											<li><a href="#"
												onclick="openPopup('invoiceReportChargesRpt')"><i
													class="fa fa-caret-right"></i> Invoice Report</a></li>
											<li><a href="#"
												onclick="openPopup('paymentReportChargesRpt')"><i
													class="fa fa-caret-right"></i> Payment Report Big</a></li>
											<li><a href="#"
												onclick="openPopup('smallpaymentReportChargesRpt')"><i
													class="fa fa-caret-right"></i> Payment Report Small</a></li>
											<li><a href="#"
												onclick="openPopup('PendingPaymentReports')"><i
													class="fa fa-caret-right"></i> Add Debtors Report</a></li>
											<li><a href="#" onclick="openPopup('invoiceCharges')"><i
													class="fa fa-caret-right"></i> CA Reports</a></li>
											<li><a href="#" onclick="openPopup('auditorChargesRpt')"><i
													class="fa fa-caret-right"></i> Auditors Report</a></li>
											<li><a href="#" onclick="openPopup('Doctorsharereport')"><i
													class="fa fa-caret-right"></i> Doctor Share Report</a></li>
											<li><a href="#"
												onclick="openPopup('userwisepaymentChargesRpt')"><i
													class="fa fa-caret-right"></i> User Wise Payment Report</a></li>
											<li><a href="#" onclick="openPopup('ipdSummary')"><i
													class="fa fa-caret-right"></i> IPD Daily Report</a></li>
											<li><a href="#"
												onclick="openPopup('ipdopdRefundReportSummary')"><i
													class="fa fa-caret-right"></i>OPD/IPD Cancel/Refund Report</a></li>
											<li><a href="#"
												onclick="openPopup('ipdBillRegisterSummary')"><i
													class="fa fa-caret-right"></i> IPD Bill Register</a></li>
											<li><a href="#"
												onclick="openPopup('serviceRegisterDetailsSummary')"><i
													class="fa fa-caret-right"></i> Service Register Details</a></li>
											<li><a href="#"
												onclick="openPopup('departmentWiseAnalysisReportSummary')"><i
													class="fa fa-caret-right"></i>Department Wise Analysis
													Report</a></li>
											<li><a href="#"
												onclick="openPopup('chargesharereportSummary')"><i
													class="fa fa-caret-right"></i>Charge Share Report</a></li>
											<li><a href="#"
												onclick="openPopup('billingreportSummary')"><i
													class="fa fa-caret-right"></i>Billing Report</a></li>
											<li><a href="#"
												onclick="openPopup('discountreportSummary')"><i
													class="fa fa-caret-right"></i>Discount Report</a></li>
											<li><a href="#"
												onclick="openPopup('cancelinvoicereportSummary')"><i
													class="fa fa-caret-right"></i>Cancel Invoice Report</a></li>
											<li><a href="#"
												onclick="openPopup('ipdmonthlyreportSummary')"><i
													class="fa fa-caret-right"></i>IPD Monthly Report</a></li>

											<li><a href="#"
												onclick="openPopup('hosprevnueProcessingAccount')"><i
													class="fa fa-caret-right"></i>Hospital Revenue</a></li>
											<li><a onclick="openPopup('newDetailedChargesRpt')"
												href="#"><i class="fa fa-caret-right"></i>Charges Report
													Detailed</a></li>
										</ul></li>
									<li><a href="#"><i class="fa fa-caret-right"></i>
											Clinical</a>
										<ul>
											<li><a href="#"
												onclick="openPopup('treatmentEpisodeListClinical')"><i
													class="fa fa-caret-right"></i> Treatment Episode List</a></li>
											<li><a href="#" onclick="openPopup('Conditionreport')"><i
													class="fa fa-caret-right"></i> Patient Condition List</a></li>
										</ul></li>
									<li><a href="#"><i class="fa fa-caret-right"></i>
											Patient</a>
										<ul>
											<li><a href="#"
												onclick="openPopup('clientListClientRpt')"><i
													class="fa fa-caret-right"></i> Patient List</a></li>
											<li><a href="#"
												onclick="openPopup('currentTreatmentNoFutureApmtsClientRpt')"><i
													class="fa fa-caret-right"></i> Current Treat With No Future
													Apmts</a></li>
											<li><a href="#"
												onclick="openPopup('noApptActivityRecordClientRpt')"><i
													class="fa fa-caret-right"></i> No Appointment Activity
													Record</a></li>
											<li><a href="#"
												onclick="openPopup('DNANoFutureAppClientRpt')"><i
													class="fa fa-caret-right"></i> DNA With No Future
													Appointment</a></li>
											<li><a href="#"
												onclick="openPopup('noActivityRecordClientRpt')"><i
													class="fa fa-caret-right"></i> No Activity Record</a></li>
										</ul></li>
									<li><a href="#"><i class="fa fa-caret-right"></i>
											Summary</a>
										<ul>
											<li><a href="#" onclick="openPopup('Summary')"><i
													class="fa fa-caret-right"></i> DNA Analysis Report</a></li>
											<li><a href="#" onclick="openPopup('appDNAKeptSummary')"><i
													class="fa fa-caret-right"></i> Appointment Kept vs DNA</a></li>
											<li><a href="#"
												onclick="openPopup('treatmentReferralSummary')"><i
													class="fa fa-caret-right"></i> Treatment State By Referral</a></li>
											<li><a href="#"
												onclick="openPopup('returningptsSummary')"><i
													class="fa fa-caret-right"></i> Returning Patients</a></li>
											<li><a href="#" onclick="openPopup('odreportSummary')"><i
													class="fa fa-caret-right"></i> Outcome Discharge / Report</a></li>
											<li><a href="#" onclick="openPopup('KPI')"><i
													class="fa fa-caret-right"></i> KPI Report </a></li>

											<li><a href="#"><i class="fa fa-caret-right"></i>
													Daily Report</a>
												<ul>
													<li><a href="#"
														onclick="openPopup('rptoutstandingDailyReport')"><i
															class="fa fa-caret-right"></i> Report Outstanding</a></li>
													<li><a href="#"
														onclick="openPopup('newptsDailyReport')"><i
															class="fa fa-caret-right"></i> New Patients</a></li>
													<li><a href="#"
														onclick="openPopup('totalptsseenDailyReport')"><i
															class="fa fa-caret-right"></i> Total Patients Seen</a></li>
													<li><a href="#"
														onclick="openPopup('dnaotsreportDailyReport')"><i
															class="fa fa-caret-right"></i> DNA Outstanding Action</a></li>
												</ul></li>

										</ul></li>

								</ul></li>

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
								if (loginInfo111.isAssessmentForms() == true) {
							%>
							<li class="hidden"><a href="#" title="Assesment Form"><i
									class="fa fa-file-o"></i> <span>Assesment Form</span></a>
								<ul>
									<li><a href="#"
										onclick="openPopup('showtemplateAssesmentForms')"><i
											class="fa fa-caret-right"></i>View / Edit Template</a></li>
									<li><a href="#"><i class="fa fa-caret-right"></i> Form
											Builder</a>
										<ul>
											<li><a href="#"
												onclick="openPopup('AssesmentMasterForms')"><i
													class="fa fa-caret-right"></i> Add/ Edit Feild</a></li>
											<li><a href="#"
												onclick="openPopup('inputAssesmentForms')"><i
													class="fa fa-caret-right"></i> Create New Form</a></li>
										</ul></li>
								</ul></li>
							<%
								}
							%>

							<%
								if (loginInfo111.getUserType() == 3) {
							%>
							<li class="hidden"><a href="PracticeManager" title="Practice Manager"><i
									class="fa fa-user-secret"></i> Practice Manager</a></li>
							<%
								}
							%>

						</ul> --%>
						
					</nav>
				</div>
			</div>
		</div>
		<!-- sidebar menu area end -->
		<!-- main content area start -->
		<div class="main-content">
			<!-- header area start -->
			<div class="header-area">
				<div class="row align-items-center">
					<!-- nav and search button -->
					<div class="col-md-12 col-sm-12 clearfix">
						<div class="nav-btn pull-left">
							<span></span> <span></span> <span></span>
						</div>
						<div class="search-box pull-left ">
							<img src="newMnsIpd/assets/images/Manas_Yuvicare_Logo.svg"
								class="mob hidden">
								
							<img src="liveData/clinicLogo/<s:property value="clinicLogo"/>" class="hidden" style="max-width: 21%;" alt="" >
							<s:if test="casualtyipd==0">
								<h3>IPD Dashboard</h3>
							</s:if>
							<s:elseif test="casualtyipd==1">
								<h3>Casualty Dashboard</h3>
							</s:elseif>
							<s:elseif test="casualtyipd==2">
								<h3>Day Care Dashboard</h3>
							</s:elseif>
							<!-- <h3>IPD / Casualty / Day Care Dashboard</h3> -->
							<form action="#" style="display: none; margin-left: 15px;">
								<i class="ti-search"></i> <input type="text" name="search"
									placeholder="Search patients, appointments, help or anything else "
									required>

							</form>
							<img src="newMnsIpd/assets/images/Add Patient Icon.svg"
								style="display: none;" class="head-img">
							<h3
								style="font-size: 12px; display: none; margin-left: 10px; color: #15536E;">Add
								A Patient</h3>
							<img src="newMnsIpd/assets/images/Book Appointment Icon.svg"
								style="display: none;" class="head-img">
							<h3
								style="font-size: 12px; display: none; margin-left: 15px; color: #15536E;">Book
								An Appointment</h3>
								
							<!-- <div style="text-align: left;">
								<h3>IPD / Casualty / Day Care Dashboard </h3>
							</div> -->
						</div>
						
	<%-- 					<div class="notification-area pull-right">
							<ul
								class="nav nav-tabs tabs-dark navnavtabstabsdarkleftmarg topone"
								role="tablist">
								<li class="hidden"><a href="#" onclick="loadcommonbell()"
									title="Notification"><i class="fa fa-bell"
										style="color: #424a5d;"></i></a></li>
								<li
									style="cursor: default; border-right: 1px solid rgba(0, 0, 0, 0.1); text-align: center; cursor: pointer;"
									class="bedcount hidden" title="Discharge Initiated"
									onclick="openIpdPopup('InitialDischarge?filter=1&maintype=1')"><span><span
										class="dischargedisplaynone">Initiated</span> <br>
									<span
										style="color: #e05d6f; font-size: 16px; font-weight: bold;"><s:property
												value="totolintitaldischarge" /></span></span></li>
								<li
									style="cursor: default; border-right: 1px solid rgba(0, 0, 0, 0.1); text-align: center; cursor: pointer;"
									class="bedcount hidden" title="Discharged Patient"
									onclick="openIpdPopup('InitialDischarge?filter=6&maintype=2')"><span><span
										class="dischargedisplaynone">Discharged</span><br>
									<span
										style="color: #e05d6f; font-size: 16px; font-weight: bold;"><s:property
												value="totaldischarge" /></span></span></li>
								<li
									style="cursor: default; border-right: 1px solid rgba(0, 0, 0, 0.1); text-align: center;"
									class="bedcount hidden" title="Inhouse Patient"
									onclick="openPopup(' currentpatientsSummary')"><span><span
										class="dischargedisplaynone">Inhouse Patient </span><br>
									<span
										style="color: #e05d6f; font-size: 16px; font-weight: bold;"><s:property
												value="totalbookedbed" /></span></span></li>
								<li style="cursor: default; text-align: center;"
									class="bedcount hidden"><span title="Total Bed"><span
										class="dischargedisplaynone">Total Beds</span> <br> <span
										style="color: #e05d6f; font-size: 16px; font-weight: bold;"><s:property
												value="totalbed" /></span></span></li>
								<li class="hidden"><a href="#" style="color: #fff;"
									onclick="printIpdExcel()" title="Download CSV file"
									style="line-height: 26px;"><i class="fa fa-download"></i>
										Download Excel</a></li>
							</ul>
							
							<div class="form-inline" style="float: right;margin-top: 5px;text-transform: uppercase;">
								  <div class="checkbox">
								    <label>
								      <i class="fa fa-square" aria-hidden="true" style="color:#43b9be"></i> Vacant beds
								    </label>
								  </div>|
								  <div class="checkbox">
								    <label>
								      <i class="fa fa-square" aria-hidden="true" style="color:#b1abab;"></i> Inactive beds
								    </label>
								  </div>|
								  <div class="checkbox">
								    <label>
								      <i class="fa fa-square" aria-hidden="true" style="color:#FE9B00;"></i> Filled Beds
								    </label>
								  </div>|
								  <div class="checkbox">
								    <label>
								      <i class="fa fa-square" aria-hidden="true" style="color:#ea371a"></i> MLC CASE
								    </label>
								  </div>|
								  <div class="checkbox">
								    <label>
								      <i class="fa fa-square" aria-hidden="true" style="color:#0E93FF;"></i> Excess Amount
								    </label>
								  </div>|
								  <div class="checkbox">
								    <label>
								      <i class="fa fa-square" aria-hidden="true" style="color:#1abf26;"></i> TP Patients
								    </label>
								  </div>|
								  <div class="checkbox">
								    <label>
								      <i class="fa fa-square" aria-hidden="true" style="color:#a14fa7;"></i> AYB Patients
								    </label>
								  </div>|
								   <div class="checkbox">
								    <label>
								      <i class="fa fa-square" aria-hidden="true" style="color:#0000ff;"></i> BSKY
								    </label>
								  </div>
								  
							</div>

						</div>
 --%>
					</div>
					<!-- profile info & task notification -->
					<!-- <div class="col-md-4 col-sm-4 clearfix" >
                        <ul class="notification-area pull-right" style="display: none;">
                            
                            <li><img src="newMnsIpd/assets/images/Chat Icon.svg"></li>
                            <li class="dropdown">
                                <i class="ti-bell dropdown-toggle" data-toggle="dropdown">
                                </i>
                                <div class="dropdown-menu bell-notify-box notify-box">
                                    <span class="notify-title">You have 3 new notifications <a href="#">view all</a></span>
                                    <div class="nofity-list">
                                        
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-comments-smiley btn-info"></i></div>
                                            <div class="notify-text">
                                                <p>New Commetns On Post</p>
                                                <span>30 Seconds ago</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </li>
                            <li><div class="dropdown show" style="margin-left: 0px;">
                                <a class="btn btn-danger dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Welcome,Admin <i class="fa fa-angle-down"></i>
                                </a>
                              
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                  <a class="dropdown-item" href="#">Action</a>
                                  <a class="dropdown-item" href="#">Another action</a>
                                  <a class="dropdown-item" href="#">Something else here</a>
                                </div>
                              </div>
                            </li>
                            <li><img src="newMnsIpd/assets/images/Profile Picture_Placeholder_Circle.svg"></li>
                          
                        </ul>
                        
                    </div> -->

				</div>
			</div>





			<!--Body  -->

			<tiles:insertAttribute name="body" />



			<!-- main content area end -->

		</div>
		
		
		</div>
		<!-- page container area end -->

		<!-- jquery latest version -->
		<!--  <script src="newMnsIpd/assets/js/vendor/jquery-2.2.4.min.js"></script> -->
		<!-- bootstrap 4 js -->
		<script src="newMnsIpd/assets/js/popper.min.js"></script>
		<script src="newMnsIpd/assets/js/bootstrap.min.js"></script>
		<script src="newMnsIpd/assets/js/owl.carousel.min.js"></script>
		<script src="newMnsIpd/assets/js/metisMenu.min.js"></script>
		<%-- <script src="newMnsIpd/assets/js/jquery.slimscroll.min.js"></script> --%>
		<script src="newMnsIpd/assets/js/jquery.slicknav.min.js"></script>
		<!-- Speed  -->
		<%-- <script
			src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script> --%>

		<!-- others plugins -->
		<script src="newMnsIpd/assets/js/plugins.js"></script>
		<script src="newMnsIpd/assets/js/scripts.js"></script>
		<script>
			$(document).ready(function() {

				$(".datepicker").datepicker({
					prevText : '<i class="fa fa-fw fa-angle-left"></i>',
					nextText : '<i class="fa fa-fw fa-angle-right"></i>'
				});
			});
		</script>
		
		
		
		<div class="modal fade" style="background: rgba(255, 255, 255, 0.93) !important;" id="baselayout1loaderPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	 </div>
</body>
<link href="common/css/yuvicommon.css" rel="stylesheet" type="text/css" />
</html>
