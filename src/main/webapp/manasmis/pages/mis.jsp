
<%@page import="com.apm.Report.eu.entity.MisReport"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Mis.web.form.MisChartForm"%>
<%@page import="com.apm.Expence.eu.entity.Expence"%>


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script>

<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request);%>
<%String ipdper = (String) session.getAttribute("ipdper");
			String opdper = (String) session.getAttribute("opdper");
			String otper = (String) session.getAttribute("otper");

			Client client1 = (Client) session.getAttribute("allgraph");

			if (ipdper == null) {
				ipdper = "0";
			}
			if (otper == null) {
				otper = "0";
			}
			if (opdper == null) {
				opdper = "0";
			}
			if (client1 == null) {
				client1 = new Client();
			}

			String graphName = (String) session.getAttribute("graphName");

			MisChartForm misChartForm = (MisChartForm) session.getAttribute("misChartForm");
			String action = (String) session.getAttribute("graphaction");%>
<head>
<meta charset="utf-8">
 <meta http-equiv="x-ua-compatible" content="ie=edge">
<link href="manasmaindashboard/css/oldbootstrapcode.css" rel="stylesheet"
		type="text/css" />
<script src="popupdialog/dialog/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>
<script src="mis/js/mischart.js"></script>
<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>
</head>
<%-- <script>
    $(document).ready(function() {
        	 $("#fromDate").datepicker({

     			dateFormat : 'dd-mm-yy',
     			minDate : '30-12-1880',
     			changeMonth : true,
     			changeYear : true

     		});

        
        $("#toDate").datepicker({

     			dateFormat : 'dd-mm-yy',
     			minDate : '30-12-1880',
     			changeMonth : true,
     			changeYear : true

        });

	});

</script> --%>

<style type="text/css">
.ui-datepicker {
	background-color: #ceecf2 !important;
	line-height: 33px !important;
}

.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu:hover>.dropdown-menu {
	display: block;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #ffffff;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}
</style>




<body>

	<input type="hidden" id="ismanasmis" value="1">
	<div class="page-container" style="padding-left: 0px;">
		<!-- sidebar menu area start -->
		<div class="sidebar-menu sbar_collapsed" style="display: none;">
			<div class="sidebar-header">
				<div class="logo">
					<a href="index.html"><img
						src="manasmis/images/Hospital Profile Picture_Left.png" alt="logo"></a>
				</div>
			</div>
			<div class="main-menu">
				<div class="menu-inner">
					<nav>
						<ul class="metismenu" id="menu">
							<li><a href="index.html"><img
									src="manasmis/images/Home.png"><span>Home</span></a></li>
							<li><a href=""><img
									src="manasmis/images/Appointments.png"><span>Appointments
								</span></a></li>
							<li><a href=""><img src="manasmis/images/Patients.png"><span>Patients</span></a>
								<ul class="collapse">
									<li><a href="">some text</a></li>
									<li><a href="">some text</a></li>
								</ul></li>
							<li><a href=""><img src="manasmis/images/Accounts.png"><span>Accounts</span></a>
							</li>
							<li><a href=""><img src="manasmis/images/Pharmacy.png"><span>Pharmacy</span></a>
							</li>
							<li><a href=""><img src="manasmis/images/Inventory.png"></i>
									<span>Inventory</span></a></li>
							<li><a href="my_opd.html"><img
									src="manasmis/images/My opd_icon.png"> <span>My
										OPD</span></a></li>
							<li><a href=""><img src="manasmis/images/IPD_Icon.png">
									<span>IPD</span></a></li>
							<li><a href=""><img src="manasmis/images/MIS_Icon.png">
									<span>MIS</span></a></li>
							<li><a href=""><img src="manasmis/images/EMR_Icon.png">
									<span>EMR</span></a></li>
							<li><a href=""><img src="manasmis/images/Chat Room.png">
									<span>Chat Room </span></a></li>
							<li><a href=""><img src="manasmis/images/Calendar.png">
									<span>Calendar </span></a></li>
							<li><a href=""><img
									src="manasmis/images/Help Center.png"> <span>Help
										Center </span></a></li>
							<li><a href=""><img src="manasmis/images/Settings.png">
									<span>Settings </span></a></li>
						</ul>
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
					<div class="col-md-8 col-sm-8 clearfix">
						<div class="nav-btn pull-left" style="display: none;">
							<span></span> <span></span> <span></span>
						</div>
						<div class="search-box pull-left ">
							<img src="manasmis/images/Manas_Yuvicare_Logo.svg" class="mob">
						</div>

					</div>
					<!-- profile info & task notification -->
					<div class="col-md-4 col-sm-4 clearfix">
						<ul class="notification-area pull-right" style="display: none;">

							<li><img src="manasmis/images/Chat Icon.svg"></li>
							<li class="dropdown"><i class="ti-bell dropdown-toggle"
								data-toggle="dropdown"> </i>
								<div class="dropdown-menu bell-notify-box notify-box">
									<span class="notify-title">You have 3 new notifications
										<a href="#">view all</a>
									</span>
									<div class="nofity-list">

										<a href="#" class="notify-item">
											<div class="notify-thumb">
												<i class="ti-comments-smiley btn-info"></i>
											</div>
											<div class="notify-text">
												<p>New Commetns On Post</p>
												<span>30 Seconds ago</span>
											</div>
										</a>
									</div>
								</div></li>
							<l i>
							<div class="dropdown show" style="margin-left: 0px;">
								<a class="btn btn-danger dropdown-toggle" href="#" role="button"
									id="dropdownMenuLink" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> Welcome,Admin <i
									class="fa fa-angle-down"></i>
								</a>

								<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
									<a class="dropdown-item" href="#">Action</a> <a
										class="dropdown-item" href="#">Another action</a> <a
										class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							</li>
							<li><img
								src="manasmis/images/Profile Picture_Placeholder_Circle.svg"></li>
						</ul>
					</div>
				</div>
			</div>

			<div class="main-content-inner">
				<div class="container-fluid"
					style="background-image: url('manasmis/images/Union 12.svg'); background-repeat: no-repeat, repeat;">
					<div class="row mis-head" style="padding-top: 40px;">
						<ul class="hidden-down">
							<li class="line">
								<%
									if (loginInfo.getUserType() == 2 || loginInfo.isKpi_dashboard()) {
								%>
								<a href="#" onclick="openPopup('kpidashboardKPI')" class="kp"
								title="KPI Dashboard ">KPI Dashboard </a> <%
 	}
 %> <img
								src="manasmis/images/Dividers.svg" style="margin-top: 4px;">
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Billing</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isCharges()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('ChargesRpt')">Charges Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvoice()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('invoiceReportChargesRpt')">Invoice
											Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPayment_report_detailed()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('paymentReportChargesRpt')">Payment
											Report Detailed</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPayment_report_small()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('smallpaymentReportChargesRpt')">Payment
											Report Summary</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isCharges_share()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('chargesharereportSummary')">Charge
											Share Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isBilling()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('billingreportSummary')">Billing
											Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDiscount()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('discountreportSummary')">Discount
											Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isCancel_invoice()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('cancelinvoicereportSummary')">Cancel
											Invoice Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPayment()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('paymentreportSummary')">Payment
											Report</a>
										<%
											}
										%>
										<!-- <a class="dropdown-item hidden" onclick="openPopup('paymentreceivedreportChargesRpt')" href="#">Payment received report</a> -->
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isRefund_report()) {
										%>
										<a class="dropdown-item"
											onclick="openPopup('ipdopdRefundReportSummary')" href="#">Refund
											report</a>
											<%} %>
											<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPayment_combined_report()) {
										  %>
											 <a class="dropdown-item" href="#"
											onclick="openPopup('payment_report_combinnedChargesRpt')">Payment
											Report Combined</a>
											<%} %>
											<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPayment_receipt_report()) {
										  %>
											 <a class="dropdown-item" href="#"
											onclick="openPopup('paymentreciptreportSummary')">Payment
											Receipt Report </a> 
											<%} %>
											<%
                                              if (loginInfo.isPhysio()) {
                                            %>
											
											<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDeptwise_payment_report()) {
										    %>
											<a class="dropdown-item"
											onclick="openPopup('paymentagainstinvoicefornkpphysioChargesRpt')"
											href="#">Department Wise Payment Report<img
											src="dashboardicon/newdiet.gif"></img></a>
											<%
                                              }} else {
                                            %>
                                              
                                            <%
											if (loginInfo.getUserType() == 2 || loginInfo.isDeptwise_payment_report()) {
										    %>
											<a class="dropdown-item"
											onclick="openPopup('paymentagainstinvoice1ChargesRpt')"
											href="#">Department Wise Payment Report<img
											src="dashboardicon/newdiet.gif"></img></a>
											
											<%
											}}
										    %>
										    
											<%
											if (loginInfo.isLmh()) {
										    %>
											<a class="dropdown-item"
											onclick="openPopup('departmentwisesummaryrevenueReport')" href="#">Department Wise Payment Summary Report</a>
											
											<a class="dropdown-item"
											onclick="openPopup('departmentwiserevenuecountReport')" href="#">Department Wise Revenue Count Report</a>
											
											<a class="dropdown-item"
											onclick="openPopup('ChargesRevenuerptReport')" href="#">Charges Revenue Report</a>
											
											<a class="dropdown-item"
											onclick="openPopup('DepartmentWiseDiscountReportSummary')" href="#">Department Wise Discount Report</a>
											<%} %> 
									</div>
								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Accounts</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isUserwise_payment()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('userwisepaymentreportSummary')">User
											Wise Payment Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isAdd_debtors()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('PendingPaymentReports')">Add Debtors
											Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isCa()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('invoiceCharges')">CA Reports</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isAuditors()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('auditorChargesRpt')">Auditors Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDoctor_share_report()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('Doctorsharereport')">Doctor Share
											Report</a><%} %>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDeptwise_analysis()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('departmentWiseAnalysisReportSummary')">Department
											Wise Analysis Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isOpd_ipd_report()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('opdipdconversionrevenueSummary')">Patients
											OPD to IPD Report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isVisiting_consultation_report()) {
										%>
									    <a class="dropdown-item"
											onclick="openPopup('vistingconreportSummary')" href="#">Visiting
											Consultation Report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isCharges_detaile_report()) {
										%>
									    <a class="dropdown-item"
											onclick="openPopup('newDetailedChargesRpt')" href="#">Charges
											Report Detailed</a>
									    <%} %>		
										<%
											if (loginInfo.isPcsAdmin()) {
										%>
										<a class="dropdown-item"
											onclick="openPopup('pcsgstreportSummary')" href="#">GST
											Charges Report </a>
										<%
											}
										%>
                                       <%
											if (loginInfo.getUserType() == 2 || loginInfo.isPractitioner_share_report()) {
										%>
										<a href="#"
											class="btn btn-danger-1 dropdown-toggle dropdown-item"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">Practitioners Share Report</a>
											<%} %>
										<div class="dropdown-menu">
											<%
												if (loginInfo.getUserType() == 2 || loginInfo.isPractioner_share()) {
											%>
											<a class="dropdown-item" href="#"
												onclick="openPopup('PractitionerListCommission?action=0')">Practitioners
												Share Report</a>
											<%
												}
											%>
											<%
												if (loginInfo.getUserType() == 2 || loginInfo.isOpd_practioner_share()) {
											%>
											<a class="dropdown-item" href="#"
												onclick="openPopup('OPDPractitionerListCommission')">OPD
												Practitioners Share Report</a>
											<%
												}
											%>
											<%
												if (loginInfo.getUserType() == 2 || loginInfo.isOpd_practioner_share()) {
											%>
											<a href="#" class="dropdown-item"
												onclick="openPopup('practshareopdnewSummary')">Deptwise
												OPD Practitioners Share Report Patient Count</a>
											<%
												}
											%>
											<a class="dropdown-item"
												onclick="openPopup('wardwiseavgstaySummary')" href="#">Ward
												Wise Bed Occupancy & Revenue Report</a>
										</div>
									</div>



								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Investigation</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
									   <%
											if (loginInfo.getUserType() == 2 || loginInfo.isOut_source_report()) {
										%>
										    <a class="dropdown-item"
											onclick="openPopup('investoutsourceReport')" href="#">Out
											Source Report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isLab_report()) {
										%>
											 <a class="dropdown-item"
											onclick="openPopup('LabreportSummary')" href="#">Lab
											report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvestigation_count_report()) {
										%>
											 <a class="dropdown-item"
											onclick="openPopup('investigationCountSummary')" href="#">Investigation
											count report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvestigation_package_report()) {
										%>
										    <a class="dropdown-item"
											onclick="openPopup('invstpackagerptSummary')" href="#">Investigation
											Package report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvstwise_count_report()) {
										%>
										    <a class="dropdown-item"
											onclick="openPopup('investigationtypewisereportnewInvestigation')"
											href="#">Investigation Wise Count Report</a> 
										<%} %>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvestigation_revenue_report()) {
										%>	
											<a class="dropdown-item"
											onclick="openPopup('report/pages/investigationrevenuereport.jsp')"
											href="#">Investigation Revenue Report</a>
										<%} %>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvst_revcount_namewise()) {
										%>	
										    <a class="dropdown-item"
											onclick="openPopup('getinvesrevenueNcountInvestigation')"
											href="#">Investigation Revenue And Count Report(Name
											Wise)</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvst_opdipd_report()) {
										%>
											 <a class="dropdown-item"
											onclick="openPopup('ipdopdrevenueinvstrevnueSummary')"
											href="#">Investigation IPD/OPD Report</a>
									    <%} %>		
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvst_tat_report()) {
										%>
											 <a class="dropdown-item"
											onclick="openPopup('investigationtatReport')" href="#">Investigation
											TAT Report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvst_criticalval_report()) {
										%>
											 <a class="dropdown-item"
											onclick="openPopup('invstabsvalreportSummary')" href="#">Investigation
											Critical Value Report</a>
										<%} %>
										
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInvst_opdipd_report()) {
										%>
											 <a class="dropdown-item"
											onclick="openPopup('investigationdetailrevenueSummary')"
											href="#">Investigation Detailed Revenue Report</a>
									    <%} %>	

									</div>
								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Pharmacy</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPayment_recive()) {
										%>
										<a class="dropdown-item"
											onclick="openPopup('paymentreceivereportProduct')" href="#">Payment
											Receive Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isSales_report()) {
										%>
										<a class="dropdown-item"
											onclick="openPopup('salereportProduct')" href="#">Sale
											Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isExpiry_medicine_report()) {
										%>
										<a class="dropdown-item"
											onclick="openPopup('expirymedicineProduct')" href="#">Expiry
											Product Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isItem_wise_sale_report()) {
										%>
										<a class="dropdown-item"
											onclick="openPopup('itemwisereportProduct')" href="#">Item
											Wise Sale Report</a> 
										<%} %>	
											<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPatient_consumption_report()) {
										 %>
											<a class="dropdown-item"
											href="patientconsumptionreportProduct">Patient/User
											Consumption Report</a> 
										<%} %>	
											<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPayable_aging_report()) {
										%>
											<a class="dropdown-item"
											onclick="openPopup('payableagingReport')" href="#">Payable
											Aging Report</a> 
											<%} %>
											<%
											if (loginInfo.getUserType() == 2 || loginInfo.isStock_report()) {
										%>
											<a class="dropdown-item"
											onclick="openPopup('stockreportReport?isfromcathlab=0')"
											href="#">Stock Report</a>
                                         <%} %>
                                         <%
											if (loginInfo.getUserType() == 2 || loginInfo.isItem_wise_sale_report()) {
										%>
										<a class="dropdown-item"
											onclick="openPopup('medicinesalePharmacy')" href="#">Medicine
											Wise Sale Report</a> 
										<%} %>	
									</div>
								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Inventory</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
										<%
											if (loginInfo.isReport()) {
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInventory_sale_report()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openBlankPopup('salereportProduct')">Sale Report</a><%} %>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPayment_receive_report()) {
										%>	
										<a href="#" class="dropdown-item"
											onclick="openBlankPopup('paymentreceivereportProduct')">Payment
											Receive Report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInventory_itemwise_sale()) {
										%>		
											 <a href="#" class="dropdown-item"
											onclick="openBlankPopup('itemwisereportProduct')">Item
											Wise Sale Report</a> 
										<%} %>	
									    <%
											if (loginInfo.getUserType() == 2 || loginInfo.isProductwise_sale_report()) {
										%>		
											<a href="#" class="dropdown-item"
											onclick="openBlankPopup('productwisereportProduct')">Product
											Wise Sale Report</a>
										<%} %>	
										 <%
											if (loginInfo.getUserType() == 2 || loginInfo.isCataloguewise_sale_report()) {
										%>		
											 <a href="#" class="dropdown-item"
											onclick="openBlankPopup('cataloguewisesalereportProduct')">Catalogue
											Wise Sale Report</a>
										<%} %>	
										 <%
											if (loginInfo.getUserType() == 2 || loginInfo.isGst_report()) {
										%>		
											 <a href="#" class="dropdown-item"
											onclick="openBlankPopup('vatreportProduct')">GST Report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isGrn_gst_report()) {
										%>	
											 <a
											href="#" class="dropdown-item"
											onclick="openBlankPopup('inventorygstreportProduct')">GRN
											GST REPORT</a> 
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInventory_openclose_report()) {
										%>		
											
											<a  href="#" class="dropdown-item "
											onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=0&ismonthlyreport=1')">Inventory
											Opening Closing</a>
										<%} %>	
										
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDetailed_inventory_openclose_report()) {
										%>		
											 <a  href="#" class="dropdown-item"
											onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=0&ismonthlyreport=0')">Detailed
											Inventory Opening Closing</a>
											
										<%} %>	
										<%
											if (loginInfo.isBalgopal()) {
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInventory_openclose_catalogue()) {
										%>	
										<a href="#" class="dropdown-item"
											onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=1')">Inventory
											Opening Closing By Catalogue</a>
										<%
											}} else {
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInventory_openclose_catalogue()) {
										%>	
										<a href="#" class="dropdown-item"
											onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=0')">Inventory
											Opening Closing By Catalogue</a>
										<%
											}}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isSupplier_payment_report()) {
										%>	
										<a href="#" class="dropdown-item"
											onclick="openBlankPopup('supplierpaymentreportProduct')">Supplier
											Payment Report</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isInventory_report()) {
										%>		
											 <a href="#"
											class="btn btn-danger-1 dropdown-toggle dropdown-item"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">Inventory</a>
										<%} %>	
										<div class="dropdown-menu">
											<a href="#" class="dropdown-item"
												onclick="openBlankPopup('itemwisestockreportProduct')">Item
												Wise Stock Report</a> <a href="#" class="dropdown-item hidden"
												onclick="openBlankPopup('bincardreportProduct')">Bin
												Card Old Report</a> <a href="#" class="dropdown-item"
												onclick="openBlankPopup('bincardreportnewProduct')">Bin
												Card Report</a> <a href="#" class="dropdown-item"
												onclick="openBlankPopup('expirymedicineProduct')">Expiry
												Product Report</a> <a href="#" class="dropdown-item"
												onclick="openBlankPopup('productReceivedReportProduct')">GRN
												Report</a> <a href="#" class="dropdown-item"
												onclick="openBlankPopup('consumptionreportProduct')">Consumption
												Report</a> <a href="#" class="dropdown-item"
												onclick="openBlankPopup('patientconsumptionreportProduct?isfromcathlab=0')">Patient/User
												Consumption Report</a> <a href="#" class="dropdown-item"
												onclick="openBlankPopup('transferreportProduct')">Indent
												Statement Report</a> <a href="#" class="dropdown-item"
												onclick="openBlankPopup('indentreportProduct')">Indent
												Report</a> <a href="#" class="dropdown-item"
												onclick="openBlankPopup('indentnewreportProduct')">Indent
												New Report</a> <a class="dropdown-item"
												onclick="openBlankPopup('departmaterialissueReport')"
												href="#">Department Wise Material Issue Report</a> <a
												class="dropdown-item"
												onclick="openBlankPopup('departmaterialsummaryReport')"
												href="#">Department Wise Material Summary Report</a> <a
												class="dropdown-item"
												onclick="openBlankPopup('payableagingReport')" href="#">Payable
												Aging Report</a> <a class="dropdown-item"
												onclick="openBlankPopup('nonmovingitemReport')" href="#">Non
												Moving Item Report</a> <a class="dropdown-item"
												onclick="openBlankPopup('stockreportReport?isfromcathlab=0')"
												href="#">Stock Report</a> <a class="dropdown-item"
												onclick="openBlankPopup('userwisematerialissueReport')"
												href="#">User Wise Material Issue Report</a> <a
												class="dropdown-item"
												onclick="openBlankPopup('itemwisepurchaseReport')" href="#">Item
												Wise Purchase Report</a> <a class="dropdown-item" href="#"
												onclick="openBlankPopup('detailgrnreportProcurement?isfromcathlab=0')">Details
												GRN Report</a> <a class="dropdown-item"
												onclick="openBlankPopup('adjustmentreportReport')" href="#">Adjustment
												Report</a> <a class="dropdown-item"
												onclick="openBlankPopup('editgrnlogreportProduct')" href="#">GRN
												Edit After Stock Transfer Report</a>

										</div>
										<%
											}
										%>
									</div>
								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">CathLab</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
									     <%
											if (loginInfo.getUserType() == 2 || loginInfo.isConsumption_report()) {
										 %>
										<a class="dropdown-item"
											href="patientconsumptionreportProduct?isfromcathlab=1">Consumption
											Report</a> 
											<%} %>
											<%
											if (loginInfo.getUserType() == 2 || loginInfo.isCathlab_stock_report()) {
										    %>
											<a class="dropdown-item"
											onclick="openPopup('stockreportReport?isfromcathlab=1')"
											href="#">Stock Report</a> 
											<%} %>
											<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDetail_grn_report()) {
										    %>
											<a class="dropdown-item"
											href="detailgrnreportProcurement?isfromcathlab=1">Details
											GRN Report</a>
											<%} %>
											<%
											if (loginInfo.getUserType() == 2 || loginInfo.isCathlab_opening_closing()) {
										    %>
											 <a class="dropdown-item"
											href="inventoryOpeningClosingProduct?isfromcathlab=1">Cathlab
											Opening Closing</a>
											<%} %>
									</div>
								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Patients</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isPatientlist()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('clientListClientRpt')">Patient List</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isCurrent_track_with_no_future_ampts()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('currentTreatmentNoFutureApmtsClientRpt')">Current
											Treat With No Future Apmts</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isNo_appointment_activity_record()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('noApptActivityRecordClientRpt')">No
											Appointment Activity Record</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDna_with_no_future_appointment()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('DNANoFutureAppClientRpt')">DNA With
											No Future Appointment</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isNo_activity_record()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('noActivityRecordClientRpt')">No
											Activity Record</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isOpd_tat_report()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('opdtatreportReport')">OPD TAT Report</a><%} %>
									   <%
											if (loginInfo.getUserType() == 2 || loginInfo.isBirthplace_report()) {
										%>		
										<a class="dropdown-item" href="#"
											onclick="openPopup('birthplacereportSummary')">BirthPlace
											Report</a><%} %>
											
										<a class="dropdown-item" href="#"
											onclick="openPopup('smslogReportClientRpt')">SMS_Log</a>	
									</div>
								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">IPD</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isIpd_daily_report()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('ipdSummary')">IPD Daily Account
											Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isIpd_monthly_report()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('ipdmonthlyreportSummary')">IPD
											Monthly Occupancy Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isIpd_daily_discharge()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('ipddailyadddisSummary')">IPD Daily
											Admission Discharge</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isCurrent_patient_report()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup(' currentpatientsSummary')">IPD
											Current Patient Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isOutcome_discharge()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('odreportSummary')">Discharge Outcome
											Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDeathreport()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup(' deathreportSummary')">Death Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isMlc()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('ismlcreportSummary')">MLC Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getClinicUserid().equals("nelson")) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('totalrevenueSummary?isnelson=1&yearly=1')">Nelson
											Total Revenue Report</a> <a href="#" class="dropdown-item"
											onclick="openPopup('deptwise_otSummary')">Dept Wise OT
											Revenue Report</a> <a class="dropdown-item"
											onclick="openPopup('departmentwiserevenueReport')" href="#">Department
											Wise Revenue Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isTotal_revenue_report()) {
										%>
										
										<a href="#" class="dropdown-item"
											onclick="openPopup('totalrevenueSummary')">Total Revenue
											Report</a>
											<%} %>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isWard_wise_rev_report()) {
										%>
											 <a href="#" class="dropdown-item"
											onclick="openPopup('wardwiserevenuereptSummary')">Ward
											Wise Revenue Report</a>
											
										<%} %>	
										<%
											if (loginInfo.getIskunal() == 1) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('ipdpatienttpreportSummary')">IPD
											THIRD PARTY Report</a> <a href="#" class="dropdown-item"
											onclick="openPopup('tppackagereportSummary')">Applied
											Package Report</a>
										<%
											}
										%>
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isRevenue_matrix()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('revenuematrixReport')">Revenue Matrix</a>
										<%} %>	
									    <%
											if (loginInfo.getUserType() == 2 || loginInfo.isAdmission_discharge()) {
										%>		
										<a href="#" class="dropdown-item"
											onclick="openPopup('nosofadmdiscReport')">No's of
											Admission & Discharge</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDeptwise_revenue_report()) {
										%>	
											 <a class="dropdown-item"
											onclick="openPopup('departmentwiserevenueReport')" href="#">Department
											Wise Revenue Report</a> 
											
											<%} %>
											
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isConsultation_report()) {
										%>	
												
											<a class="dropdown-item"
											onclick="openPopup('consultationReport')" href="#">Consultation
											Report</a>
											<%} %>
											
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isBed_occupancy_per_day()) {
										%>
											<a href="#" class="dropdown-item"
											onclick="openPopup('bedOccupancyrptReport')">Bed Occupancy Per Day</a>
										<%} %>	
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isRender_charges_report()) {
										%>
											<a href="#" class="dropdown-item"
											onclick="openPopup('renderChargesrptReport')">Render Charges Report</a>
                                        <%} %>
									</div>
								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Summary</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isDna_analysiis()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('Summary')">DNA Analysis Report</a>
										<%
											}
											if (loginInfo.getUserType() == 2 || loginInfo.isAppointment_kept_vs_dna()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('appDNAKeptSummary')">Appointment Kept
											vs DNA</a>
										<%
											}
											if (loginInfo.getUserType() == 2 || loginInfo.isTreatment_state_by_refferal()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('treatmentReferralSummary')">Treatment
											State By Referral</a>
										<%
											}
											if (loginInfo.getUserType() == 2 || loginInfo.isReturning_patients()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('returningptsSummary')">Returning
											Patients</a>
										<%
											}
											if (loginInfo.getUserType() == 2 || loginInfo.isReffered_by()) {
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('refferedbyreportSummary')">Reffered
											By Report</a>
										<%
											}
										%>
										<a class="dropdown-item" href="#"
											onclick="openPopup('showpatientsreportSummary')">Dept
											report</a> <a class="dropdown-item" href="#"
											onclick="openPopup('deptwiseavgcountSummary')">Dept Wise
											Monthly Count report</a> <a class="dropdown-item" href="#"
											onclick="openPopup('newIpdCurrentPatientSummary')">Current
											IPD Patients</a> <a href="#"
											class="btn btn-danger-1 dropdown-toggle dropdown-item"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">Daily Report</a>
										<div class="dropdown-menu">
											<%
												if (loginInfo.getUserType() == 2 || loginInfo.isReport_outstandng()) {
											%>
											<a href="#" class="dropdown-item"
												onclick="openPopup('rptoutstandingDailyReport')">Report
												Outstanding</a>
											<%
												}
												if (loginInfo.getUserType() == 2 || loginInfo.isNow_patients()) {
											%>
											<a href="#" class="dropdown-item"
												onclick="openPopup('newptsDailyReport')">New Patients</a>
											<%
												}
												if (loginInfo.getUserType() == 2 || loginInfo.isTotal_patients_seen()) {
											%>
											<a href="#" class="dropdown-item"
												onclick="openPopup('totalptsseenDailyReport')">Total
												Patients Seen</a>
											<%
												}
												if (loginInfo.getUserType() == 2 || loginInfo.isDna_outstanding_action()) {
											%>
											<a href="#" class="dropdown-item"
												onclick="openPopup('dnaotsreportDailyReport')">DNA
												Outstanding Action</a>
											<%
												}
											%>
										</div>

									</div>


								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">Clinical</button>
									<img src="manasmis/images/Dividers.svg">
									<div class="dropdown-menu">
										<%
											if (loginInfo.getUserType() == 2 || loginInfo.isTreatment_episode_list()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('treatmentEpisodeListClinical')">Treatment
											Episode List</a>
										<%
											}
											if (loginInfo.getUserType() == 2 || loginInfo.isPatient_condition_list()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('Conditionreport')">Patient Condition
											List</a>
										<%
											}
											if (loginInfo.getUserType() == 2 || loginInfo.isDtr_report()) {
										%>
										<a href="#" class="dropdown-item"
											onclick="openPopup('DtrReportFormClinical')">DTR Report</a>
										<%
											}
										%>
									</div>
								</div>
							</li>
							<li class="line">
								<div class="btn-group">
									<button type="button" class="btn btn-danger-1 dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">OT</button>
									<div class="dropdown-menu">
										<a href="#" class="dropdown-item"
											onclick="openPopup('otreportReport')">OT Report</a>
									</div>
								</div>
							</li>
						</ul>
					</div>

					<div class="row btn-align">
						<form class="" action="MisChart" id="mischartfrm"
							style="display: inline-flex;">
							<s:hidden name="action" id="action"></s:hidden>
							<s:hidden name="graphName" id="graphName"></s:hidden>
							<%
								if (loginInfo.getJobTitle().equals("Admin")) {
							%>
							<div class="form-group setdorp" style="padding-left: 20px;">
								<s:select name="invoicecategory" id="invoicecategory"
									list="#{'2':'All','0':'Primary','1':'Secondary' }"
									cssClass="form-control" cssStyle="background-color: #e0faff;"></s:select>
							</div>
							<%
								}
							%>
							<div class="form-group col-lg-4 col-md-4 col-xs-12 col-sm-12"
								style="padding-left: 10px; height: 39px">
								<s:textfield readonly="true" name="fromDate" id="fromDate"
									cssClass="form-control" theme="simple"
									cssStyle="background-color: #e0faff;height: 39px;"></s:textfield>
							</div>
							<div class="form-group"
								style="padding-left: 10px; padding-top: 10px;">To</div>
							<div
								class="col-lg-4 col-md-4 col-xs-12 col-sm-12 form-group setdorp"
								style="padding-left: 10px; height: 39px">
								<s:textfield readonly="true" name="toDate" id="toDate"
									cssClass="form-control" theme="simple"
									cssStyle="background-color: #e0faff;height: 39px;"></s:textfield>
							</div>
							<div
								class="col-lg-3 col-md-3 col-xs-12 col-sm-12 form-group setdorp"
								style="padding-left: 10px;">
								<s:select 
									cssClass="form-control"
									list="#{'0':'Select All','IPD':'IPD', 'OPD':'OPD'}"
									name="filter_ward" cssStyle="background-color: #e0faff;" />
							</div>
							<s:if test="isKPI==1">
								<div class="form-group setdorp" style="padding-left: 10px;">
									<s:select headerKey="0" headerValue="Select KPI Area"
										cssClass="form-control" list="kpiarealist" listKey="id"
										listValue="name" name="kpiarea_filter"
										cssStyle="background-color: #e0faff;" />
								</div>
								<div class="form-group setdorp" style="padding-left: 10px;">
									<s:select cssClass="form-control"
										list="#{'2015-2016':'2015-2016', '2016-2017':'2016-2017', '2017-2018':'2017-2018', '2018-2019':'2018-2019', '2019-2020':'2019-2020'}"
										id="year_filter" name="year_filter"
										cssStyle="background-color: #e0faff;" />
								</div>
								<div class="form-group setdorp" style="padding-left: 10px;">
									<s:select cssClass="form-control"
										list="#{'01':'January', '02':'February', '03':'March', '04':'April', '05':'May','06':'June', '07':'July', '08':'August', '09':'September', '10':'October', '11':'November', '12':'December'}"
										id="month_filter" name="month_filter"
										cssStyle="background-color: #e0faff;" />
								</div>
							</s:if>
							<s:if test="pharmacy_location==1">
								<div class="form-group setdorp" style="padding-left: 10px;">
									<s:select headerKey="0" cssStyle="background-color: #e0faff;"
										headerValue="Select All" cssClass="form-control"
										list="locationListPharmacy" listKey="id" listValue="name"
										name="filter_location" />
								</div>
							</s:if>
							<div
								class="col-lg-2 col-md-2 col-xs-12 col-sm-12 form-group setdorp"
								style="padding-left: 10px;">
								<s:submit value="Go" theme="simple"
									style="padding-left: 10px;height: 39px;"
									cssClass="btn btn-primary marrigh10se"
									onclick="return submitmis()"></s:submit>
							</div>
						</form>
					</div>

					<div class="row" style="margin-top: 30px;">
						<div class="col-lg-2  col-12 mb-3 ">
							<div class="card-mis">
								<ul class="nav nav-pills flex-column" id="myTab" role="tablist">
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('dailysummary')" id="dailysummary"
										data-toggle="tab" href="#" role="tab"
										aria-controls="Daily-Summary" aria-selected="false">Daily
											Summary</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('yearly')" id="yearly" data-toggle="tab"
										href="#" role="tab" aria-controls="Yearly-Statistics"
										aria-selected="true">Yearly Statistics</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('opd')" id="opd" data-toggle="tab" href="#"
										role="tab" aria-controls="OPD-Patients" aria-selected="false">OPD
											Patients</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('ipd')" id="ipd" data-toggle="tab" href="#"
										role="tab" aria-controls="IPD-Pateints" aria-selected="true">IPD
											Patients</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('bedstatus')" id="bedstatus"
										data-toggle="tab" href="#" role="tab"
										aria-controls="Bed-Status" aria-selected="false">Bed
											Status</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('prescriptionsummary')"
										id="prescriptionsummary" data-toggle="tab" href="#" role="tab"
										aria-controls="prescription" aria-selected="false">Prescription</a>
									</li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('pharmacysummary')" id="pharmacysummary"
										data-toggle="tab" href="#" role="tab" aria-controls="Pharmacy"
										aria-selected="true">Pharmacy</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('procurementsummary')"
										id="procurementsummary" data-toggle="tab" href="#" role="tab"
										aria-controls="Procurement" aria-selected="false">Procurement</a>
									</li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('indentsummary')" id="indentsummary"
										data-toggle="tab" href="#" role="tab" aria-controls="Indent"
										aria-selected="false">Indent</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('investigation')" id="investigation"
										data-toggle="tab" href="#" role="tab"
										aria-controls="Investigation" aria-selected="true">Investigation</a>
									</li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('invoice')" id="invoice" data-toggle="tab"
										href="#" role="tab" aria-controls="Invoice"
										aria-selected="false">Invoice & Billing</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('paymentmode')" id="paymentmode"
										data-toggle="tab" href="#" role="tab" aria-controls="Payment"
										aria-selected="false">Payment Mode</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('advref')" id="advref" data-toggle="tab"
										href="#" role="tab" aria-controls="Advance"
										aria-selected="false">Advance & Refund</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('accountinfo')" id="accountinfo"
										data-toggle="tab" href="#" role="tab" aria-controls="Finicial"
										aria-selected="false">Financial Summary</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('clinicalview')" id="clinicalview"
										data-toggle="tab" href="#" role="tab" aria-controls="Clinical"
										aria-selected="false">Clinical View</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('patientview')" id="patientview"
										data-toggle="tab" href="#" role="tab" aria-controls="Patient"
										aria-selected="false">Patient View</a></li>
									<li class="nav-item"><a class="nav-link-2"
										onclick="dosubmit('patientviewpackage')"
										id="patientviewpackage" data-toggle="tab" href="#" role="tab"
										aria-controls="Package" aria-selected="false">Patient View
											By Package</a></li>
								</ul>
							</div>
						</div>
						<!-- /.col-md-4 -->
						<div class="col-lg-10 col-md-12">
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade " id="yearlyt" role="tabpanel"
									aria-labelledby="Yearly-Statistics-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-12 head-summary">
														<h4>Yearly Statistics</h4>
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="" style="height: 680px; margin: 0 auto">
																<div id="yearlyg"
																	style="height: 650px; margin: 0 auto; width: 100%;"></div>
															</figure>
														</div>
														<script type="text/javascript">
                   	   
								                   	 $(function () {
								                   	    $('#yearlyg').highcharts({
								                   	        chart: {
								                   	            type: 'column'
								                   	        },
								                   	        title: {
								                   	            text: 'Yearly Statistics'
								                   	        },
								                   	       
								                   	        xAxis: {
								                   	            categories: [
								                   	                'Jan',
								                   	                'Feb',
								                   	                'Mar',
								                   	                'Apr',
								                   	                'May',
								                   	                'Jun',
								                   	                'Jul',
								                   	                'Aug',
								                   	                'Sep',
								                   	                'Oct',
								                   	                'Nov',
								                   	                'Dec'
								                   	            ],
								                   	            crosshair: true
								                   	        },
								                   	        yAxis: {
								                   	            min: 0,
								                   	            title: {
								                   	                text: 'Patient No'
								                   	            }
								                   	        },
								                   	        tooltip: {
								                   	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
								                   	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
								                   	                '<td style="padding:0"><b>{point.y:1f}</b></td></tr>',
								                   	            footerFormat: '</table>',
								                   	            shared: true,
								                   	            useHTML: true
								                   	        },
								                   	        plotOptions: {
								                   	            column: {
								                   	                pointPadding: 0.2,
								                   	                borderWidth: 0
								                   	            }
								                   	        },
								                   	        series: [{
								                   	            name: 'OPD Booked',
								                   	            data: [<%=client1.getOpdjan()%>, <%=client1.getOpdfeb()%>, <%=client1.getOpdmar()%>,<%=client1.getOpdapr()%>, <%=client1.getOpdmay()%>, <%=client1.getOpdjune()%>, <%=client1.getOpdjuly()%>, <%=client1.getOpdaug()%>, <%=client1.getOpdsep()%>, <%=client1.getOpdoct()%>, <%=client1.getOpdnov()%>, <%=client1.getOpddec()%>]
								
								                   	        }, {
								                 	             name: 'OPD Completed',
								                    	           data: [<%=client1.getOpdcomjan()%>, <%=client1.getOpdcomfeb()%>, <%=client1.getOpdcommar()%>,<%=client1.getOpdcomapr()%>, <%=client1.getOpdcommay()%>, <%=client1.getOpdcomjune()%>, <%=client1.getOpdcomjuly()%>, <%=client1.getOpdcomaug()%>, <%=client1.getOpdcomsep()%>, <%=client1.getOpdcomoct()%>, <%=client1.getOpdcomnov()%>, <%=client1.getOpdcomdec()%>]
								
								                      	    }, {
								                        	        name: 'OPD DNA',
								                        	      data: [<%=client1.getOpddnajan()%>, <%=client1.getOpddnafeb()%>, <%=client1.getOpddnamar()%>,<%=client1.getOpddnaapr()%>, <%=client1.getOpddnamay()%>, <%=client1.getOpddnajune()%>, <%=client1.getOpddnajuly()%>, <%=client1.getOpddnaaug()%>, <%=client1.getOpddnasep()%>, <%=client1.getOpddnaoct()%>, <%=client1.getOpddnanov()%>, <%=client1.getOpddnadec()%>]
								                          	}, {
								                   	        	name: 'IPD',
								                   	            data: [<%=client1.getIpdjan()%>, <%=client1.getIpdfeb()%>, <%=client1.getIpdmar()%>, <%=client1.getIpdapr()%>, <%=client1.getIpdmay()%>, <%=client1.getIpdjune()%>,<%=client1.getIpdjuly()%>, <%=client1.getIpdaug()%>, <%=client1.getIpdsep()%>, <%=client1.getIpdoct()%>, <%=client1.getIpdnov()%>, <%=client1.getIpddec()%>]
								                   	           
								
								                   	        }, {
								                   	             name: 'OT',
								                   	            data: [<%=client1.getOtjan()%>, <%=client1.getOtfeb()%>, <%=client1.getOtmar()%>, <%=client1.getOtapr()%>, <%=client1.getOtmay()%>, <%=client1.getOtjune()%>,<%=client1.getOtjuly()%>, <%=client1.getOtaug()%>, <%=client1.getOtsep()%>, <%=client1.getOtoct()%>, <%=client1.getOtnov()%>, <%=client1.getOtdec()%>]
								
								                   	        }
								                   	        ]
								                   	    });
								                   	 });
                   	   
                   	  							 </script>
													</div>
												</div>
											</div>
										</div>
									</div>


								</div>

								<div class="tab-pane fade show" id="dailysummaryt"
									role="tabpanel" aria-labelledby="Daily-Summary-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>
															Daily Summary (
															<s:property value="fromDate" />
															to
															<s:property value="toDate" />
															)
														</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer;" onclick="printDailyExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer;"></p>
														<div class="row dyn-height-1 scrollable-element-1"
															style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="newregpatient" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Reg. Patient</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalopdseen" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total OPD Booked</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalopdcompleted" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total OPD Completed</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalopddna" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total OPD-DNA</h3>
																</div>
															</div>

															<s:iterator value="opdappointmenttype">
																<s:if test="result==0">
																</s:if>
																<s:else>
																	<div class="card-summary"
																		style="height: 100px; width: 45%;">
																		<p></p>
																		<h1 style="padding-top: 10px;">
																			<s:property value="result" />
																		</h1>
																		<div class="card-ft-1">
																			<h3>
																				<s:property value="name" />
																			</h3>
																		</div>
																	</div>
																</s:else>
															</s:iterator>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="ipdnewadmission" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total IPD Admission</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="inhousepatients" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Patients In-house Today</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalmlcaddmission" />
																</h1>
																<div class="card-ft-1">
																	<h3>MLC Admission</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="dischargepatients" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Discharged</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totaldeath" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Death</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalDAMA" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total DAMA</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="otPatientCount" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total OT</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalInvestigation" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Investigation</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalpathlab" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Pathlab</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalmaicrobiology" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Microbiology</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalendoscopy" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Endoscopy</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalcardiology" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Cardiology</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalctinvest" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total CT SCAN</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalmricount" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total MRI</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalxraycount" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total XRAY</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalsonographycount" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total USG</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalcardiologycount" />
																</h1>
																<div class="card-ft-1">
																	<h3>NON INVASIVE CARDIOLOGY</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="chargeaddedd" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total IPD & OPD Charges Added</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="paymentrecieved" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total IPD & OPD Payment</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="noofcasuality" />
																</h1>
																<div class="card-ft-1">
																	<h3>No. of Casuality</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="noofdaycare" />
																</h1>
																<div class="card-ft-1">
																	<h3>No. of Daycare</h3>
																</div>
															</div>


														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Summary Charts</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="row" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="dailysummaryg"></div>

															</figure>
														</div>
														<div class="row" style="display: none;">
															<figure class="highcharts-figure">
																<div id="container-1"></div>

															</figure>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="opdt" role="tabpanel"
									aria-labelledby="OPD-Pateints-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>OPD Patients</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printExcel()">Download Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="bookedAppointment" />
																</h1>
																<div class="card-ft-1">
																	<h3>Booked</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalCompleted" />
																</h1>
																<div class="card-ft-1">
																	<h3>Completed</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totaldna" />
																</h1>
																<div class="card-ft-1">
																	<h3>DNA</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="notCompleted" />
																</h1>
																<div class="card-ft-1">
																	<h3>Not Completed</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="otPatientCount" />
																</h1>
																<div class="card-ft-1">
																	<h3>OT</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalcancel" />
																</h1>
																<div class="card-ft-1">
																	<h3>Cancelled</h3>
																</div>
															</div>

														</div>
													</div>
		<div class="row " style="padding-top: 15px;">
		    	<div class="col-lg-6 col-md-12 col-12 head-summary">
					<div class="col-lg-8 col-md-8 col-xs-12 col-sm-12 topback2">
						<table class="table table-hover table-condensed table-bordered" style="border: black 1px solid;">
							<thead>
								<tr>
								    <td>Sr. No.</td>
								    <td>Doctor Name </td>
									<td>Booked </td>
									<td style="text-align: center;">Completed</td>
									<td style="text-align: center;">DNA</td>
									<td style="text-align: center;">Not Completed</td>
									<td style="text-align: center;">OT</td>
									<td style="text-align: center;">Cancelled</td>
									<td style="text-align: center;">Total Rev</td>
									
									
								</tr>
							</thead>
						<tbody>
							<%int n=1; %>
								<s:iterator value="UserList">
									
										<tr>
										    <td><%=n %></td>
										    <td style="text-align: center;"><a href="#" onclick="openPopup('getAllPrintDataNotAvailableSlot?docid=<s:property value="id"/>&action=dashboard')"><s:property value="DiaryUser" /></a></td>
											<td style="text-align: center;"><s:property value="DocwisebookedAppointment" /></td>
											<td style="text-align: center;"><s:property value="DocwisetotalCompleted" /></td>
											<td style="text-align: center;"><s:property value="Docwisetotaldna" /></td>
											<td style="text-align: center;"><s:property value="Docwisenotcompleted" /></td>
											<td style="text-align: center;"><s:property value="DocwiseotPatientCount" /></td>
											<td style="text-align: center;"><s:property value="Docwisetotalcancel" /></td>
											<td style="text-align: center;"><s:property value="Totalrev" /></td>
											
											
											
											
										</tr>
								<%n++; %>
								</s:iterator>
							</tbody>
							
						</table>
					</div>
				</div>
		   </div>
												</div>
											</div>
										</div>
									</div>
							  </div>
								<div class="tab-pane fade show " id="ipdt" role="tabpanel"
									aria-labelledby="IPD-Pateints-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>IPD Patients</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printIpdExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printIpdExcel()">Download Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="newadmission" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Admissions</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="inhousepatients" />
																</h1>
																<div class="card-ft-1">
																	<h3>Patients In-house Today</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="dischargepatients" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Discharged</h3>
																</div>
															</div>
														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
							   <div class="row " style="padding-top: 15px;">
					
					
					<div class="col-lg-8 col-md-8 col-xs-12 col-sm-12 topback2">
						<table class="table table-hover table-condensed table-bordered" style="border: black 1px solid;">
							<thead>
								<tr>
								    <td>Sr. No.</td>
									<td>Ward Name</td>
									<td style="text-align: center;">Admission</td>
									<td style="text-align: center;">Discharge</td>
									<td style="text-align: center;">InHouse Patient</td>
									
								</tr>
							</thead>
						<tbody>
							<%int j=1; %>
								<s:iterator value="Wardwisereport">
									
										<tr>
										    <td><%=j %></td>
											<td><s:property value="Wardname"/></td>
											<td style="text-align: center;"><s:property value="Wardwiseadmissioncount"/></td>
											<td style="text-align: center;"><s:property value="Wardwisedischargecount"/></td>
											<td style="text-align: center;"><s:property value="Wardwiseinhousecount"/></td>
											
										</tr>
								<%j++; %>
								</s:iterator>
							</tbody>
							
						</table>
					</div>
					
					
				</div>
          </div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="bedstatust" role="tabpanel"
									aria-labelledby="Bed-Status-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Bed Status</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printBedStatusExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printBedStatusExcel()">Download Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalbeds" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Beds Today</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="availablebed" />
																</h1>
																<div class="card-ft-1">
																	<h3>Available</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="occupiedbed" />
																</h1>
																<div class="card-ft-1">
																	<h3>Occupied</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="dischargepatients" />
																</h1>
																<div class="card-ft-1">
																	<h3>To be Discharge</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="undermaintnancebed" />
																</h1>
																<div class="card-ft-1">
																	<h3>Under Maintenance</h3>
																</div>
															</div>
														</div>
													</div>
													  <div class="row " style="padding-top: 15px;">
					
					
					<div class="col-lg-8 col-md-8 col-xs-12 col-sm-12 topback2">
						<table class="table table-hover table-condensed table-bordered" style="border: black 1px solid;">
							<thead>
								<tr>
								    <td>Sr. No.</td>
									<td>Ward Name</td>
									<td style="text-align: center;">Occupied</td>
									<td style="text-align: center;">Available</td>
									
									
								</tr>
							</thead>
						<tbody>
							<%int k=1; %>
								<s:iterator value="Wardwisereport">
									
										<tr>
										    <td><%=k %></td>
											<td><s:property value="Wardname"/></td>
											<td style="text-align: center;"><s:property value="Wardwiseoccupied"/></td>
											<td style="text-align: center;"><s:property value="Wardwiseavailablebed"/></td>
											
											
											
										</tr>
								<%j++; %>
								</s:iterator>
							</tbody>
							
						</table>
					</div>
					
					
				</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade show " id="prescriptionsummaryt"
									role="tabpanel" aria-labelledby="prescription-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Prescription</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printPrescriptionExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printPrescriptionExcel()">Download Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalprescription" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Prescription</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="notdelivered" />
																</h1>
																<div class="card-ft-1">
																	<h3>Requested</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="delivered" />
																</h1>
																<div class="card-ft-1">
																	<h3>Delivered</h3>
																</div>
															</div>

														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="prescriptionsummaryg"
																	style="height: 500px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
											        $(function () {
											         // Create the chart
											         $('#prescriptionsummaryg').highcharts({
					                   			         
					                   			                 chart: {
															            type: 'column'
															        },
															        title: {
															            text: 'Prescription'
															        }, 	
															        xAxis: {
															            type: 'category'
															        },
															        yAxis: {
															            title: {
															                text: 'Prescription'
															            }
															
															        },
															        legend: {
															            enabled: false
															        },
															        plotOptions: {
															            series: {
															                borderWidth: 0,
															                dataLabels: {
															                    enabled: true,
															                    format: '{point.y}'
															                }
															            }
															        },
															
															        tooltip: {
															            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
															            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
															        },
															
															        series: [{
															            name: 'Prescription',
															            colorByPoint: true,
															            data: [{
															                name: 'Requested',
															                y: <%=misChartForm.getNotdelivered()%>,
															                drilldown: 'Requested'
															            }, {
															                name: 'Delivered',
															                y: <%=misChartForm.getDelivered()%>,
															                drilldown: 'Delivered'
															            }
															            ]
															        }],
															        drilldown: {
															            series: [{
															                name: 'Requested',
															                id: 'Requested',
															                data: [
															                    [
															                        'v11.0',
															                        24.13
															                    ],
															                    [
															                        'v8.0',
															                        17.2
															                    ],
															                    [
															                        'v9.0',
															                        8.11
															                    ]
															                ]
															            }, {
															                name: 'Delivered',
															                id: 'Delivered',
															                data: [
															                    [
															                        'v40.0',
															                        5
															                    ],
															                    [
															                        'v41.0',
															                        4.32
															                    ],
															                    [
															                        'v42.0',
															                        3.68
															                    ],
															                    [
															                        'v39.0',
															                        2.96
															                    ],
															                    [
															                        'v36.0',
															                        2.53
															                    ],
															                    
															                    [
															                        'v35.0',
															                        0.85
															                    ],
															                    [
															                        'v38.0',
															                        0.6
															                    ],
															                    [
															                        'v30.0',
															                        0.14
															                    ]
															                ]
															            } 
															            ]
															        }
					                   			      
					                   			       });
					    							});   
					                   			   </SCRIPT>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="pharmacysummaryt" role="tabpanel"
									aria-labelledby="Pharmacy-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Pharmacy</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printPharmacyDailySaleExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printPharmacyDailySaleExcel()">Download
															Excel</p>
														<div class="row dyn-height-1 scrollable-element-1"
															style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalpayment" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Received</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalcredit" />
																</h1>
																<div class="card-ft-1">
																	<h3>Credit</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="creditReturn" />
																</h1>
																<div class="card-ft-1">
																	<h3>Credit Return</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="hospital" />
																</h1>
																<div class="card-ft-1">
																	<h3>Hospital</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="hospitalReturn" />
																</h1>
																<div class="card-ft-1">
																	<h3>Hospital Return</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="todaycash" />
																</h1>
																<div class="card-ft-1">
																	<h3>Cash</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="todaycard" />
																</h1>
																<div class="card-ft-1">
																	<h3>Card</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="chequepayments" />
																</h1>
																<div class="card-ft-1">
																	<h3>Cheque</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="neftpayment" />
																</h1>
																<div class="card-ft-1">
																	<h3>NEFT/RTGS</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalpayment" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Received</h3>
																</div>
															</div>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="todayReturn" />
																</h1>
																<div class="card-ft-1">
																	<h3>Cash Return</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="todaydisc" />
																</h1>
																<div class="card-ft-1">
																	<h3>Discount</h3>
																</div>
															</div>

														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="pharmacysummaryg"
																	style="height: 500px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
												        $(function () {
												         // Create the chart
												         $('#pharmacysummaryg').highcharts({
												         
												         			chart: {
														            type: 'column'
														        },
														        title: {
														            text: 'Pharmacy'
														        }, 	
														        xAxis: {
														            type: 'category'
														        },
														        yAxis: {
														            title: {
														                text: 'Pharmacy'
														            }
														
														        },
														        legend: {
														            enabled: false
														        },
														        plotOptions: {
														            series: {
														                borderWidth: 0,
														                dataLabels: {
														                    enabled: true,
														                    format: '{point.y}'
														                }
														            }
														        },
														
														        tooltip: {
														            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
														            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
														        },
														        series: [{
														            name: 'Pharmacy Summary',
														            colorByPoint: true,
														            data: [{
														                name: 'Cash',
														                y: <%=misChartForm.getTodaycash()%>,
														                drilldown: 'Cash'
														            }, {
														                name: 'Card',
														                y: <%=misChartForm.getTodaycard()%>,
														                drilldown: 'Card'
														            }, {
														                name: 'Cheque',
														                y: <%=misChartForm.getChequepayments()%>,
														                drilldown: 'Cheque'
														            },{
														                name: 'NEFT/RTGS',
														                y: <%=misChartForm.getNeftpayment()%>,
														                drilldown: 'NEFT/RTGS'
														            }
														            
														            ]
														        }],
														        drilldown: {
														            series: [{
														                name: 'Cash',
														                id: 'Cash',
														                data: [
														                    [
														                        'v11.0',
														                        24.13
														                    ],
														                    [
														                        'v8.0',
														                        17.2
														                    ],
														                    [
														                        'v9.0',
														                        8.11
														                    ]
														                ]
														            }, {
														                name: 'Card',
														                id: 'Card',
														                data: [
														                    [
														                        'v40.0',
														                        5
														                    ],
														                    [
														                        'v41.0',
														                        4.32
														                    ],
														                    [
														                        'v42.0',
														                        3.68
														                    ],
														                    [
														                        'v39.0',
														                        2.96
														                    ],
														                    [
														                        'v36.0',
														                        2.53
														                    ],
														                    
														                    [
														                        'v35.0',
														                        0.85
														                    ],
														                    [
														                        'v38.0',
														                        0.6
														                    ],
														                    [
														                        'v30.0',
														                        0.14
														                    ]
														                ]
														            },{
														                name: 'Cheque',
														                id: 'Cheque',
														                data: [
														                    [
														                        'v40.0',
														                        5
														                    ],
														                    [
														                        'v41.0',
														                        4.32
														                    ],
														                    [
														                        'v42.0',
														                        3.68
														                    ],
														                    [
														                        'v39.0',
														                        2.96
														                    ],
														                    [
														                        'v36.0',
														                        2.53
														                    ],
														                    
														                    [
														                        'v35.0',
														                        0.85
														                    ],
														                    [
														                        'v38.0',
														                        0.6
														                    ],
														                    [
														                        'v30.0',
														                        0.14
														                    ]
														                ]
														            }, {
														                name: 'NEFT/RTGS',
														                id: 'NEFT/RTGS',
														                data: [
														                    [
														                        'v35',
														                        2.76
														                    ],
														                    [
														                        'v36',
														                        2.32
														                    ],
														                    [
														                        'v37',
														                        2.31
														                    ],
														                    [
														                        'v34',
														                        1.27
														                    ],
														                    [
														                        'v32',
														                        0.15
														                    ]
														                ]
														            }
														            ]
														        }       
												         
												         });
												         });
				                   			        </SCRIPT>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="procurementsummaryt"
									role="tabpanel" aria-labelledby="Procurement-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Procurement</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printProcurmentExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printProcurmentExcel()">Download Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="grnwithpo" />
																</h1>
																<div class="card-ft-1">
																	<h3>No of PO</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="grnwithoutpo" />
																</h1>
																<div class="card-ft-1">
																	<h3>No of GRN without PO</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalgrn" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Procurements</h3>
																</div>
															</div>
														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="procurementsummaryg"
																	style="height: 500px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
											        $(function () {
											         // Create the chart
											         $('#procurementsummaryg').highcharts({
					                   			         
					                   			                 chart: {
															            type: 'column'
															        },
															        title: {
															            text: 'Procurement'
															        }, 	
															        xAxis: {
															            type: 'category'
															        },
															        yAxis: {
															            title: {
															                text: 'Procurement'
															            }
															
															        },
															        legend: {
															            enabled: false
															        },
															        plotOptions: {
															            series: {
															                borderWidth: 0,
															                dataLabels: {
															                    enabled: true,
															                    format: '{point.y}'
															                }
															            }
															        },
															
															        tooltip: {
															            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
															            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
															        },
															
															        series: [{
															            name: 'Procurement',
															            colorByPoint: true,
															            data: [{
															                name: 'No of PO',
															                y: <%=misChartForm.getGrnwithpo()%>,
															                drilldown: 'No of PO'
															            }, {
															                name: 'No of GRN without PO',
															                y: <%=misChartForm.getGrnwithoutpo()%>,
															                drilldown: 'No of GRN without PO'
															            }
															            ]
															        }],
															        drilldown: {
															            series: [{
															                name: 'No of PO',
															                id: 'No of PO',
															                data: [
															                    [
															                        'v11.0',
															                        24.13
															                    ],
															                    [
															                        'v8.0',
															                        17.2
															                    ],
															                    [
															                        'v9.0',
															                        8.11
															                    ]
															                ]
															            }, {
															                name: 'No of GRN without PO',
															                id: 'No of GRN without PO',
															                data: [
															                    [
															                        'v40.0',
															                        5
															                    ],
															                    [
															                        'v41.0',
															                        4.32
															                    ],
															                    [
															                        'v42.0',
															                        3.68
															                    ],
															                    [
															                        'v39.0',
															                        2.96
															                    ],
															                    [
															                        'v36.0',
															                        2.53
															                    ],
															                    
															                    [
															                        'v35.0',
															                        0.85
															                    ],
															                    [
															                        'v38.0',
															                        0.6
															                    ],
															                    [
															                        'v30.0',
															                        0.14
															                    ]
															                ]
															            } 
															            ]
															        }
					                   			      
					                   			       });
					    							});   
					                   			   </SCRIPT>

													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
								<div class="tab-pane fade show " id="indentsummaryt"
									role="tabpanel" aria-labelledby="Indent-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Indent</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printIndentExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printIndentExcel()">Download Excel</p>
														<div class="row dyn-height-1 scrollable-element-1"
															style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="direct_transfer" />
																</h1>
																<div class="card-ft-1">
																	<h3>Direct Transfer</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="request" />
																</h1>
																<div class="card-ft-1">
																	<h3>Request</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="rejected" />
																</h1>
																<div class="card-ft-1">
																	<h3>Rejected</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="pending" />
																</h1>
																<div class="card-ft-1">
																	<h3>Pending</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="pocreated" />
																</h1>
																<div class="card-ft-1">
																	<h3>PO Created</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="transfer" />
																</h1>
																<div class="card-ft-1">
																	<h3>Transferred</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="approved" />
																</h1>
																<div class="card-ft-1">
																	<h3>Approved</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="received" />
																</h1>
																<div class="card-ft-1">
																	<h3>Received</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="readytotransfer" />
																</h1>
																<div class="card-ft-1">
																	<h3>Ready To Transfer</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalindent" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total Indents</h3>
																</div>
															</div>
														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="indentsummaryg"
																	style="height: 500px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
													        $(function () {
													         // Create the chart
													         $('#indentsummaryg').highcharts({
													         
													         			chart: {
															            type: 'column'
															        },
															        title: {
															            text: 'Indent'
															        }, 	
															        xAxis: {
															            type: 'category'
															        },
															        yAxis: {
															            title: {
															                text: 'Indent'
															            }
															
															        },
															        legend: {
															            enabled: false
															        },
															        plotOptions: {
															            series: {
															                borderWidth: 0,
															                dataLabels: {
															                    enabled: true,
															                    format: '{point.y}'
															                }
															            }
															        },
															
															        tooltip: {
															            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
															            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
															        },
															        series: [{
															            name: 'Indent',
															            colorByPoint: true,
															            data: [{
															                name: 'Direct Transfer',
															                y: <%=misChartForm.getDirect_transfer()%>,
															                drilldown: 'Direct Transfer'
															            }, {
															                name: 'Request',
															                y: <%=misChartForm.getRequest()%>,
															                drilldown: 'Request'
															            }, {
															                name: 'Rejected',
															                y: <%=misChartForm.getRejected()%>,
															                drilldown: 'Rejected'
															            },{
															                name: 'Pending',
															                y: <%=misChartForm.getPending()%>,
															                drilldown: 'Pending'
															            },{
															                name: 'PO Created',
															                y: <%=misChartForm.getPocreated()%>,
															                drilldown: 'PO Created'
															            },{
															                name: 'Transferred',
															                y: <%=misChartForm.getTransfer()%>,
															                drilldown: 'Transferred'
															            },{
															                name: 'Approved',
															                y: <%=misChartForm.getApproved()%>,
															                drilldown: 'Approved'
															            },{
															                name: 'Received',
															                y: <%=misChartForm.getReceived()%>,
															                drilldown: 'Received'
															            },{
															                name: 'Ready To Transfer',
															                y: <%=misChartForm.getReadytotransfer()%>,
															                drilldown: 'Ready To Transfer'
															            }
															            
															            ]
															        }],
															        drilldown: {
															            series: [{
															                name: 'New',
															                id: 'New',
															                data: [
															                    [
															                        'v11.0',
															                        24.13
															                    ],
															                    [
															                        'v8.0',
															                        17.2
															                    ],
															                    [
															                        'v9.0',
															                        8.11
															                    ]
															                ]
															            }, {
															                name: 'Collected',
															                id: 'Collected',
															                data: [
															                    [
															                        'v40.0',
															                        5
															                    ],
															                    [
															                        'v41.0',
															                        4.32
															                    ],
															                    [
															                        'v42.0',
															                        3.68
															                    ],
															                    [
															                        'v39.0',
															                        2.96
															                    ],
															                    [
															                        'v36.0',
															                        2.53
															                    ],
															                    
															                    [
															                        'v35.0',
															                        0.85
															                    ],
															                    [
															                        'v38.0',
															                        0.6
															                    ],
															                    [
															                        'v30.0',
															                        0.14
															                    ]
															                ]
															            },{
															                name: 'Completed',
															                id: 'Completed',
															                data: [
															                    [
															                        'v40.0',
															                        5
															                    ],
															                    [
															                        'v41.0',
															                        4.32
															                    ],
															                    [
															                        'v42.0',
															                        3.68
															                    ],
															                    [
															                        'v39.0',
															                        2.96
															                    ],
															                    [
															                        'v36.0',
															                        2.53
															                    ],
															                    
															                    [
															                        'v35.0',
															                        0.85
															                    ],
															                    [
															                        'v38.0',
															                        0.6
															                    ],
															                    [
															                        'v30.0',
															                        0.14
															                    ]
															                ]
															            }, {
															                name: 'Approved',
															                id: 'Approved',
															                data: [
															                    [
															                        'v35',
															                        2.76
															                    ],
															                    [
															                        'v36',
															                        2.32
															                    ],
															                    [
															                        'v37',
															                        2.31
															                    ],
															                    [
															                        'v34',
															                        1.27
															                    ],
															                    [
															                        'v32',
															                        0.15
															                    ]
															                ]
															            },{
															                name: 'Cancelled',
															                id: 'Cancelled',
															                data: [
															                    [
															                        'v40.0',
															                        5
															                    ],
															                    [
															                        'v41.0',
															                        4.32
															                    ],
															                    [
															                        'v42.0',
															                        3.68
															                    ],
															                    [
															                        'v39.0',
															                        2.96
															                    ],
															                    [
															                        'v36.0',
															                        2.53
															                    ],
															                    
															                    [
															                        'v35.0',
															                        0.85
															                    ],
															                    [
															                        'v38.0',
															                        0.6
															                    ],
															                    [
															                        'v30.0',
															                        0.14
															                    ]
															                ]
															            }
															            ]
															        }       
													         
													         });
													         });
					                   			        </SCRIPT>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="investigationt" role="tabpanel"
									aria-labelledby="Investigation-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Investigation</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printInvestigationDetailsExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printInvestigationDetailsExcel()">Download
															Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="new_invistigation" />
																</h1>
																<div class="card-ft-1">
																	<h3>New</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="new_collected" />
																</h1>
																<div class="card-ft-1">
																	<h3>Collected</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="new_completed" />
																</h1>
																<div class="card-ft-1">
																	<h3>Completed</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="new_aproved" />
																</h1>
																<div class="card-ft-1">
																	<h3>Approved</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="deleted_investigation" />
																</h1>
																<div class="card-ft-1">
																	<h3>Cancelled</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalInvestigation" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<a href="#" onclick="openPopup('investigationdetailrevenueSummary')"><s:property value="Totalinvrevenue" /></a>
																</h1>
																<div class="card-ft-1">
																	<h3>Total Revenue</h3>
																</div>
															</div>
														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="investigationg"
																	style="height: 500px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
													        $(function () {
													         // Create the chart
													         $('#investigationg').highcharts({
													         
													         			chart: {
															            type: 'column'
															        },
															        title: {
															            text: 'Investigation'
															        }, 	
															        xAxis: {
															            type: 'category'
															        },
															        yAxis: {
															            title: {
															                text: 'Investigation'
															            }
															
															        },
															        legend: {
															            enabled: false
															        },
															        plotOptions: {
															            series: {
															                borderWidth: 0,
															                dataLabels: {
															                    enabled: true,
															                    format: '{point.y}'
															                }
															            }
															        },
															
															        tooltip: {
															            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
															            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
															        },
															        series: [{
															            name: 'Pharmacy Summary',
															            colorByPoint: true,
															            data: [{
															                name: 'New',
															                y: <%=misChartForm.getNew_invistigation()%>,
															                drilldown: 'New'
															            }, {
															                name: 'Collected',
															                y: <%=misChartForm.getNew_collected()%>,
															                drilldown: 'Collected'
															            }, {
															                name: 'Completed',
															                y: <%=misChartForm.getNew_completed()%>,
															                drilldown: 'Completed'
															            },{
															                name: 'Approved',
															                y: <%=misChartForm.getNew_aproved()%>,
															                drilldown: 'Approved'
															            },{
															                name: 'Cancelled',
															                y: <%=misChartForm.getDeleted_investigation()%>,
															                drilldown: 'Cancelled'
															            }
															            
															            ]
															        }],
															        drilldown: {
															            series: [{
															                name: 'New',
															                id: 'New',
															                data: [
															                    [
															                        'v11.0',
															                        24.13
															                    ],
															                    [
															                        'v8.0',
															                        17.2
															                    ],
															                    [
															                        'v9.0',
															                        8.11
															                    ]
															                ]
															            }, {
															                name: 'Collected',
															                id: 'Collected',
															                data: [
															                    [
															                        'v40.0',
															                        5
															                    ],
															                    [
															                        'v41.0',
															                        4.32
															                    ],
															                    [
															                        'v42.0',
															                        3.68
															                    ],
															                    [
															                        'v39.0',
															                        2.96
															                    ],
															                    [
															                        'v36.0',
															                        2.53
															                    ],
															                    
															                    [
															                        'v35.0',
															                        0.85
															                    ],
															                    [
															                        'v38.0',
															                        0.6
															                    ],
															                    [
															                        'v30.0',
															                        0.14
															                    ]
															                ]
															            },{
															                name: 'Completed',
															                id: 'Completed',
															                data: [
															                    [
															                        'v40.0',
															                        5
															                    ],
															                    [
															                        'v41.0',
															                        4.32
															                    ],
															                    [
															                        'v42.0',
															                        3.68
															                    ],
															                    [
															                        'v39.0',
															                        2.96
															                    ],
															                    [
															                        'v36.0',
															                        2.53
															                    ],
															                    
															                    [
															                        'v35.0',
															                        0.85
															                    ],
															                    [
															                        'v38.0',
															                        0.6
															                    ],
															                    [
															                        'v30.0',
															                        0.14
															                    ]
															                ]
															            }, {
															                name: 'Approved',
															                id: 'Approved',
															                data: [
															                    [
															                        'v35',
															                        2.76
															                    ],
															                    [
															                        'v36',
															                        2.32
															                    ],
															                    [
															                        'v37',
															                        2.31
															                    ],
															                    [
															                        'v34',
															                        1.27
															                    ],
															                    [
															                        'v32',
															                        0.15
															                    ]
															                ]
															            },{
															                name: 'Cancelled',
															                id: 'Cancelled',
															                data: [
															                    [
															                        'v40.0',
															                        5
															                    ],
															                    [
															                        'v41.0',
															                        4.32
															                    ],
															                    [
															                        'v42.0',
															                        3.68
															                    ],
															                    [
															                        'v39.0',
															                        2.96
															                    ],
															                    [
															                        'v36.0',
															                        2.53
															                    ],
															                    
															                    [
															                        'v35.0',
															                        0.85
															                    ],
															                    [
															                        'v38.0',
															                        0.6
															                    ],
															                    [
															                        'v30.0',
															                        0.14
															                    ]
															                ]
															            }
															            ]
															        }       
													         
													         });
													         });
					                   			        </SCRIPT>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="invoicet" role="tabpanel"
									aria-labelledby="Invoice-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Invoice</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printInvoiceExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printInvoiceExcel()">Download Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="paymentrecieved" />
																</h1>
																<div class="card-ft-1">
																	<h3>Payment Received</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="chargeaddedd" />
																</h1>
																<div class="card-ft-1">
																	<h3>Charges Added</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="invoicegenerated" />
																</h1>
																<div class="card-ft-1">
																	<h3>Invoices Generated</h3>
																</div>
															</div>

														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="invoiceg" style="height: 500px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
													        $(function () {
													         // Create the chart
													         $('#invoiceg').highcharts({	     
						                   			             
																		                 chart: {
																		            type: 'column'
																		        },
																		        title: {
																		            text: 'Invoice'
																		        }, 	
																		        xAxis: {
																		            type: 'category'
																		        },
																		        yAxis: {
																		            title: {
																		                text: 'Invoice'
																		            }
																		
																		        },
																		        legend: {
																		            enabled: false
																		        },
																		        plotOptions: {
																		            series: {
																				                borderWidth: 0,
																		                dataLabels: {
																		                    enabled: true,
																		                    format: 'Rs.{point.y}'
																		                }
																		            }
																		        },
																		
																		        tooltip: {
																		            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
																		            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>Rs.{point.y}</b> of total<br/>'
																	        },
																	
																	        series: [{
																	            name: 'Invoice',
																	            colorByPoint: true,
																	            data: [{
																	                name: 'Charges Added',
																	                y: <%=misChartForm.getChargeaddedd()%>,
																	                drilldown: 'Charges Added'
																	            }, {
																	                name: 'Invoices Generated',
																	                y: <%=misChartForm.getInvoicegenerated()%>,
																	                drilldown: 'Invoices Generated'
																	            }, {
																	                name: 'Payment Received',
																	                y: <%=misChartForm.getPaymentrecieved()%>,
																	                drilldown: 'Payment Received'
																	            }
																	            ]
																	        }],
																	        drilldown: {
																	            series: [{
																	                name: 'Charges Added',
																	                id: 'Charges Added',
																	                data: [
																	                    [
																	                        'v11.0',
																	                        24.13
																	                    ],
																	                    [
																	                        'v8.0',
																	                        17.2
																	                    ],
																	                    [
																	                        'v9.0',
																	                        8.11
																	                    ]
																	                ]
																	            }, {
																	                name: 'Invoices Generated',
																	                id: 'Invoices Generated',
																	                data: [
																	                    [
																	                        'v40.0',
																	                        5
																	                    ],
																	                    [
																	                        'v41.0',
																	                        4.32
																	                    ],
																	                    [
																	                        'v42.0',
																	                        3.68
																	                    ],
																	                    [
																	                        'v39.0',
																	                        2.96
																	                    ],
																	                    [
																	                        'v36.0',
																	                        2.53
																	                    ],
																	                    
																	                    [
																	                        'v35.0',
																	                        0.85
																	                    ],
																	                    [
																	                        'v38.0',
																	                        0.6
																	                    ],
																	                    [
																	                        'v30.0',
																	                        0.14
																	                    ]
																	                ]
																	            }, {
																	                name: 'Payment Received',
																	                id: 'Payment Received',
																	                data: [
																	                    [
																	                        'v35',
																	                        2.76
																	                    ],
																	                    [
																	                        'v36',
																	                        2.32
																	                    ],
																	                    [
																	                        'v37',
																		                        2.31
													
															                    ],
													
															                    [
													
															                        'v34',
													
															                        1.27
													
															                    ],
													
															                    [
													
															                        'v32',
													
															                        0.15
													
															                    ]
													
															                ]
													
															            }
													
															            ]
													
						
															        }           
											                   			
						                   			           });
						                   			        });
						                   			        
						                   			       </script>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="paymentmodet" role="tabpanel"
									aria-labelledby="Payment-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Payment</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right; display: none;">Download
															Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="cashpayment" />
																</h1>
																<div class="card-ft-1">
																	<h3>By Cash</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="chequepayment" />
																</h1>
																<div class="card-ft-1">
																	<h3>By Cheques</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="cardPayment" />
																</h1>
																<div class="card-ft-1">
																	<h3>By Credit/Debit cards</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="chargeaddedd" />
																</h1>
																<div class="card-ft-1">
																	<h3>Charges Added</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="otherPayment" />
																</h1>
																<div class="card-ft-1">
																	<h3>By Other</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="totalPayment" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="Upipayment" />
																</h1>
																<div class="card-ft-1">
																	<h3>By Upi</h3>
																</div>
															</div>


														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="paymentmodeg"
																	style="height: 500px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
														        $(function () {
														         // Create the chart
														         $('#paymentmodeg').highcharts({
														         
														          		    chart: {
															            type: 'column'
															        },
															        title: {
															            text: 'Payment Mode'
														        }, 	
														        xAxis: {
														            type: 'category'
														        },
														        yAxis: {
														            title: {
														                text: 'Payment Mode'
														            }
														
														        },
														        legend: {
														            enabled: false
														        },
														        plotOptions: {
														            series: {
														                borderWidth: 0,
														                dataLabels: {
														                    enabled: true,
														                    format: 'Rs.{point.y}'
														                }
														            }
														        },
														
														        tooltip: {
														            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
														            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
														        },
														
														        series: [{
														            name: 'Payment Mode',
														            colorByPoint: true,
														            data: [{
														                name: 'By Cash',
														                y: <%=misChartForm.getCashpayment()%>,
														                drilldown: 'By Cash'
														            }, {
														                name: 'By Cheques',
														                y: <%=misChartForm.getChequepayment()%>,
														                drilldown: 'By Cheques'
														            }, {
														                name: 'By Credit/Debit Cards',
														                y: <%=misChartForm.getCardPayment()%>,
														                drilldown: 'By Credit/Debit Cards'
														            }, {
														                name: 'By Other',
														                y: <%=misChartForm.getOtherPayment()%>,
														                drilldown: 'By Other'
														            }
														            ]
														        }],
														        drilldown: {
														            series: [{
														                name: 'By Cash',
													                id: 'By Cash',
													                data: [
													                    [
													                        'v11.0',
													                        24.13
													                    ],
													                    [
													                        'v8.0',
													                        17.2
													                    ],
													                    [
													                        'v9.0',
													                        8.11
													                    ]
													                ]
												            }, {
												                name: 'By Cheques',
												                id: 'By Cheques',
												                data: [
												                    [
												                        'v40.0',
												                        5
												                    ],
												                    [
												                        'v41.0',
												                        4.32
												                    ],
												                    [
												                        'v42.0',
												                        3.68
												                    ],
												                    [
												                        'v39.0',
												                        2.96
												                    ],
												                    [
												                        'v36.0',
												                        2.53
												                    ],
												                    
												                    [
												                        'v35.0',
												                        0.85
												                    ],
												                    [
												                        'v38.0',
												                        0.6
												                    ],
												                    [
												                        'v30.0',
												                        0.14
												                    ]
												                ]
												            }, {
												                name: 'By Credit/Debit Cards',
												                id: 'By Credit/Debit Cards',
												                data: [
												                    [
												                        'v35',
												                        2.76
												                    ],
												                    [
												                        'v36',
												                        2.32
												                    ],
												                    [
												                        'v37',
												                        2.31
												                    ],
												                    [
												                        'v34',
												                        1.27
												                    ],
												                    [
												                        'v32',
												                        0.15
												                    ]
												                ]
												            } , {
												                name: 'By Other',
												                id: 'By Other',
												                data: [
												                    [
												                        'v35',
												                        2.76
												                    ],
												                    [
												                        'v36',
												                        2.32
												                    ],
												                    [
												                        'v37',
												                        2.31
												                    ],
												                    [
												                        'v34',
												                        1.27
												                    ],
												                    [
												                        'v32',
												                        0.15
												                    ]
												                ]
												            }
												            ]
												        }
														         	
														      });
														    });  
													     </script>

													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
								<div class="tab-pane fade" id="advreft" role="tabpanel"
									aria-labelledby="Advance-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>Advance & Refund</h4>
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printAdvRefExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printAdvRefExcel()">Download Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="advanced" />
																</h1>
																<div class="card-ft-1">
																	<h3>Advance Payment</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="refund" />
																</h1>
																<div class="card-ft-1">
																	<h3>Refund Given</h3>
																</div>
															</div>


														</div>
													</div>
		<%-- 											<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="advrefg" style="height: 600px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
												        $(function () {
												         // Create the chart
												         $('#advrefg').highcharts({	
														         chart: {
																				            type: 'column'
																				        },
																				        title: {
																				            text: 'Advance and Refund'
																				        }, 	
																				        xAxis: {
																				            type: 'category'
																				        },
																				        yAxis: {
																				            title: {
																				                text: 'Advance and Refund'
																				            }
																				
																				        },
																				        legend: {
																				            enabled: false
																			        },
																			        plotOptions: {
																			            series: {
																			                borderWidth: 0,
																			                dataLabels: {
																			                    enabled: true,
																		                    format: 'Rs.{point.y}'
																		                }
																		            }
																		        },
																		
																		        tooltip: {
																		            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
																		            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>Rs.{point.y}</b> of total<br/>'
																		        },
																		
																		        series: [{
																		            name: 'Advance and Refund',
																		            colorByPoint: true,
																		            data: [{
																		                name: 'Advance Payment',
																		                y: <%=misChartForm.getAdvanced()%>,
																		                drilldown: 'Advance Payment'
																		            }, {
																		                name: 'Refund Given',
																		                y: <%=misChartForm.getRefund()%>,
																		                drilldown: 'Refund Given'
																	            }
																	            ]
																	        }],
																	        drilldown: {
																	            series: [{
																	                name: 'Advance Payment',
																	                id: 'Advance Payment',
																	                data: [
																	                    [
																                        'v11.0',
																                        24.13
																                    ],
																                    [
																                        'v8.0',
															                        17.2
															                    ],
															                    [
															                        'v9.0',
															                        8.11
															                    ]
															                ]
															            }, {
															                name: 'Refund Given',
															                id: 'Refund Given',
															                data: [
															                    [
															                        'v40.0',
															                        5
															                    ],
															                    [
															                        'v41.0',
															                        4.32
															                    ],
															                    [
															                        'v42.0',
															                        3.68
															                    ],
															                    [
															                        'v39.0',
															                        2.96
															                    ],
															                    [
															                        'v36.0',
															                        2.53
															                    ],
															                   
															                    [
															                        'v30.0',
															                        0.14
															                    ]
															                ]
															            }
													            ]
													        }
														         		  
														         
												         });
												        });
												        </SCRIPT>

													</div> --%>
													  <div class="row " style="padding-top: 15px;">
					
					
					<div class="col-lg-8 col-md-8 col-xs-12 col-sm-12 topback2">
						<table class="table table-hover table-condensed table-bordered" style="border: black 1px solid;">
							<thead>
								<tr>
								    <td>Sr. No.</td>
									<td>Ward Name</td>
									<td style="text-align: center;">Advance</td>
									<td style="text-align: center;">Refund</td>
									
									
								</tr>
							</thead>
						<tbody>
							<%int l=1; %>
								<s:iterator value="Wardwisereport">
									
										<tr>
										    <td><%=l %></td>
											<td><s:property value="Wardname"/></td>
											<td style="text-align: center;"><s:property value="Advance"/></td>
											<td style="text-align: center;"><s:property value="Refund"/></td>
											
											
											
										</tr>
								<%l++; %>
								</s:iterator>
							</tbody>
							
						</table>
					</div>
					
					
				</div>
												</div>
											</div>
										</div>
									</div>

								</div>
								<div class="tab-pane fade" id="accountinfot" role="tabpanel"
									aria-labelledby="Finicial-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printAccExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printAccExcel()">Download Excel</p>
														<div class="row" style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="paymentrecieved" />
																</h1>
																<div class="card-ft-1">
																	<h3>Payment Received</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="advanced" />
																</h1>
																<div class="card-ft-1">
																	<h3>Advanced Amount</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="refund" />
																</h1>
																<div class="card-ft-1">
																	<h3>Refund Amount</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="expenseTotal" />
																</h1>
																<div class="card-ft-1">
																	<h3>Expense Amount</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;"><%=Constants.getCurrency(loginInfo)%>
																	<s:property value="totalPayAll" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total</h3>
																</div>
															</div>


														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="accountinfog"
																	style="height: 600px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
								        $(function () {
								         // Create the chart
								         $('#accountinfog').highcharts({
								         
								                  
								                   chart: {
															            type: 'column'
															        },
															        title: {
														            text: 'Account Summary'
												        }, 	
												        xAxis: {
												            type: 'category'
												        },
												        yAxis: {
												            title: {
												                text: 'Account Summary'
												            }
												
												        },
											        legend: {
											            enabled: false
											        },
											        plotOptions: {
											            series: {
											                borderWidth: 0,
											                dataLabels: {
											                    enabled: true,
											                    format: 'Rs.{point.y}'
											                }
											            }
										        },
										
									        tooltip: {
									            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
									            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>Rs.{point.y}</b> of total<br/>'
									        },
									
									        series: [{
									            name: 'Account Info',
									            colorByPoint: true,
									            data: [{
									                name: 'Payment Received',
									                y: <%=misChartForm.getPaymentrecieved()%>,
									                drilldown: 'Payment Received'
									            }, {
									                name: 'Advanced Amount',
									                y: <%=misChartForm.getAdvanced()%>,
									                drilldown: 'Advanced Amount'
									            }, {
									                name: 'Refund Amount',
									                y: <%=misChartForm.getRefund()%>,
									                drilldown: 'Refund Amount'
									            }
									            , {
									                name: 'Expense Amount',
									                y: <%=misChartForm.getExpenseTotal()%>,
									                drilldown: 'Expense Amount'
									            }
									            ]
									        }],
									        drilldown: {
									            series: [{
									                name: 'Payment Received',
									                id: 'Payment Received',
									                data: [
									                    [
									                        'v11.0',
									                        24.13
									                    ],
									                    [
									                        'v8.0',
									                        17.2
									                    ],
									                    [
									                        'v9.0',
									                        8.11
									                    ]
									                ]
									            }, {
									                name: 'Advnaced Amount',
									                id: 'Advanced Amount',
									                data: [
									                    [
									                        'v40.0',
									                        5
									                    ],
									                    [
									                        'v41.0',
									                        4.32
									                    ],
									                    [
									                        'v42.0',
									                        3.68
									                    ],
									                    [
									                        'v39.0',
									                        2.96
									                    ],
									                    [
									                        'v36.0',
									                        2.53
									                    ],
									                    
									                    [
									                        'v35.0',
									                        0.85
									                    ],
									                    [
									                        'v38.0',
									                        0.6
									                    ],
									                    [
									                        'v30.0',
									                        0.14
									                    ]
									                ]
									            }, {
									                name: 'Refund Amount',
									                id: 'Refund Amount',
									                data: [
									                    [
									                        'v35',
									                        2.76
									                    ],
									                    [
									                        'v36',
									                        2.32
									                    ],
									                    [
									                        'v37',
									                        2.31
									                    ],
									                    [
									                        'v34',
									                        1.27
									                    ],
									                    [
									                        'v32',
								                        0.15
								                    ]
								                ]
								            } , {
									                name: 'Expense Amount',
									                id: 'Expense Amount',
									                data: [
									                    [
									                        'v35',
									                        2.76
									                    ],
									                    [
									                        'v36',
									                        2.32
									                    ],
									                    [
									                        'v37',
									                        2.31
									                    ],
									                    [
									                        'v34',
									                        1.27
									                    ],
									                    [
									                        'v32',
								                        0.15
								                    ]
								                ]
								            }
							            ]
							        }
								                  
								         
								         });
								         });
								         </script>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="clinicalviewt" role="tabpanel"
									aria-labelledby="Clinical-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printClinicExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printClinicExcel()">Download Excel</p>
														<div class="row dyn-height-1 scrollable-element-1"
															style="margin-top: 40px;">
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="paymentrecieved" />
																</h1>
																<div class="card-ft-1">
																	<h3>Patient by Department</h3>
																</div>
															</div>
															<s:iterator value="patientbycondition">
																<div class="card-summary"
																	style="height: 100px; width: 45%;">
																	<p></p>
																	<h1 style="padding-top: 10px;">
																		<s:property value="id" />
																	</h1>
																	<div class="card-ft-1">
																		<h3>
																			<s:property value="treatmentType" />
																		</h3>
																	</div>
																</div>
															</s:iterator>

															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="requestedprescription" />
																</h1>
																<div class="card-ft-1">
																	<h3>No. of Priscription Requested</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="requestedInvestigation" />
																</h1>
																<div class="card-ft-1">
																	<h3>No. of Investigation Requested</h3>
																</div>
															</div>
															<div class="card-summary"
																style="height: 100px; width: 45%;">
																<p></p>
																<h1 style="padding-top: 10px;">
																	<s:property value="totalClinicalView" />
																</h1>
																<div class="card-ft-1">
																	<h3>Total</h3>
																</div>
															</div>


														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="clinicalviewg"
																	style="height: 600px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">									
										  $(function () {
									         // Create the chart
									         $('#clinicalviewg').highcharts({
									         
									                  chart: {
																            type: 'column'
																        },
															        title: {
															            text: 'Clinical View'
															        }, 	
															        xAxis: {
															            type: 'category'
															        },
															        yAxis: {
															            title: {
															                text: 'Clinical View'
															            }
															
															        },
														        legend: {
														            enabled: false
														        },
													        plotOptions: {
													            series: {
													                borderWidth: 0,
													                dataLabels: {
													                    enabled: true,
													                    format: '{point.y}'
													                }
													            }
													        },
													
												        tooltip: {
												            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
												            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
												        },
											
										        series: [{
										            name: 'Clinical View',
										            colorByPoint: true,
										            data: [
										                <%for (Client client : misChartForm.getPatientbycondition()) {%>
											            
									            		{
									                			name: '<%=client.getTreatmentType()%>',
									                			y: <%=client.getId()%>,
									                			drilldown: '<%=client.getTreatmentType()%>'
									            		},
									            	   <%}%>
									             {
									                name: 'No. of Prescription Requested',
									                y: <%=misChartForm.getRequestedprescription()%>,
									                drilldown: 'No. of Priscription Requested'
									            }, {
									                name: 'No. of Investigation Requested',
									                y: <%=misChartForm.getRequestedInvestigation()%>,
									                drilldown: 'No. of Investigation Requested'
									            }
									            ]
									        }],
									        drilldown: {
									            series: [
									                    <%for (Client client : misChartForm.getPatientbycondition()) {%>
									                    {
									                			name: '<%=client.getTreatmentType()%>',
									                			id: '<%=client.getTreatmentType()%>',
									                			data: [
									                    				[
									                        				'v11.0',
									                        				24.13
									                    				],
									                    				[
									                        				'v8.0',
									                        				17.2
									                    				],
									                    				[
									                        				'v9.0',
									                        				8.11
									                    				]
									                				 ]
									                   },
									                <%}%>
									              {
									                name: 'Count of Priscription Requested',
									                id: 'Count of Priscription Requested',
									                data: [
									                    [
									                        'v40.0',
									                        5
									                    ],
									                    [
									                        'v41.0',
									                        4.32
									                    ],
									                    [
								                        'v42.0',
								                        3.68
								                    ],
								                    [
								                        'v39.0',
								                        2.96
								                    ],
								                    [
								                        'v36.0',
								                        2.53
								                    ],
								                    
								                    [
								                        'v35.0',
								                        0.85
								                    ],
								                    [
								                        'v38.0',
								                        0.6
								                    ],
								                    [
								                        'v30.0',
								                        0.14
								                    ]
								                ]
								            }, {
								                name: 'Count of Investigation Requested',
								                id: 'Count of Investigation Requested',
								                data: [
								                    [
								                        'v35',
								                        2.76
								                    ],
							                    [
							                        'v36',
							                        2.32
							                    ],
							                    [
							                        'v37',
							                        2.31
							                    ],
							                    [
							                        'v34',
							                        1.27
							                    ],
							                    [
							                        'v32',
							                        0.15
							                    ]
							                ]
							            }
							            ]
							        }
									         
									         });
									         });
									 		
									 </script>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="patientviewt" role="tabpanel"
									aria-labelledby="Patient-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%;"
															onclick="printPatientwExcel()">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right;"
															onclick="printPatientwExcel()">Download Excel</p>
														<div class="row dyn-height-1 scrollable-element-1"
															style="margin-top: 40px;">

															<s:iterator value="patientbylocation">
																<div class="card-summary"
																	style="height: 100px; width: 45%;">
																	<p></p>
																	<h1 style="padding-top: 10px;">
																		<s:property value="id" />
																	</h1>
																	<div class="card-ft-1">
																		<h3>
																			<s:property value="town" />
																		</h3>
																	</div>
																</div>
															</s:iterator>

														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="patientviewg"
																	style="height: 600px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
								        $(function () {
								         // Create the chart
								         $('#patientviewg').highcharts({   
                   			           
                   			                        chart: {
										            type: 'column'
										        },
										        title: {
										            text: 'Patient View'
										        }, 	
										        xAxis: {
										            type: 'category'
										        },
										        yAxis: {
										            title: {
										                text: 'Patient View'
										            }
										
										        },
										        legend: {
										            enabled: false
										        },
										        plotOptions: {
										            series: {
										                borderWidth: 0,
										                dataLabels: {
										                    enabled: true,
										                    format: '{point.y}'
										                }
										            }
										        },
										
										        tooltip: {
										            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
										            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
										        },
										
										        series: [{
										            name: 'Patient View',
										            colorByPoint: true,
										            data: [
										                    <%for (Client client : misChartForm.getPatientbylocation()) {%>
										            
										            		{
										                			name: '<%=client.getTown()%>',
										                			y: <%=client.getId()%>,
										                			drilldown: '<%=client.getTown()%>'
										            		},
										            	   <%}%>
										           
										            ]
										        }],
										        drilldown: {
										            series: [
										                    <%for (Client client : misChartForm.getPatientbylocation()) {%>
										                    {
										                			name: '<%=client.getTown()%>',
										                			id: '<%=client.getTown()%>',
										                			data: [
										                    				[
										                        				'v11.0',
										                        				24.13
										                    				],
										                    				[
										                        				'v8.0',
										                        				17.2
										                    				],
										                    				[
										                        				'v9.0',
										                        				8.11
										                    				]
										                				 ]
										                   },
										                <%}%>
										              
										            ]
										        }
										                   			                       
										                   			             
                   			 
                   			             });
                   			            });
                   			            
                   			            </script>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="patientviewpackaget"
									role="tabpanel" aria-labelledby="Package-tab">
									<div class="container-fluid">
										<div class="row">
											<div class="card-mis-1">
												<div class="row">
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<img src="manasmis/images/Excel Logo.svg"
															style="cursor: pointer; float: right; margin-left: 0%; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; float: right; display: none;">Download
															Excel</p>
														<div class="row dyn-height-1 scrollable-element-1"
															style="margin-top: 40px;">

															<table class="table table-no-border m-0">
																<thead>
																	<tr>
																		<th>Company name</th>
																		<th>Patient</th>
																		<th>Payment</th>
																	</tr>
																</thead>
																<tbody>
																	<s:iterator value="patientviewbypackage">
																		<tr class="">
																			<td><s:property value="packagename" /></td>
																			<td><s:property value="patientno" /></td>
																			<td style="text-align: right;">Rs.<s:property
																					value="totalpayment" /></td>
																		</tr>

																	</s:iterator>

																</tbody>
															</table>

														</div>
													</div>
													<div class="col-lg-6 col-md-12 col-12 head-summary">
														<h4>View Chart</h4>
														<img src="manasmis/images/Download Logo.svg"
															style="cursor: pointer; display: none;">
														<p
															style="font-size: 10px; display: inline; cursor: pointer; display: none;">Download
															Graphs</p>
														<div class="" style="margin-top: 10px;">
															<figure class="highcharts-figure">
																<div id="patientviewpackageg"
																	style="height: 600px; margin: 0 auto"></div>
															</figure>
														</div>
														<SCRIPT type="text/javascript">
							        $(function () {
							         // Create the chart
							         $('#patientviewpackageg').highcharts({	     
                   			             
												                 chart: {
												            type: 'column'
												        },
												        title: {
												            text: 'Patient View Package'
												        }, 	
												        xAxis: {
												            type: 'category'
												        },
												        yAxis: {
												            title: {
												                text: 'Patient View Package'
												            }
												
												        },
												        legend: {
												            enabled: false
												        },
												        plotOptions: {
												            series: {
														                borderWidth: 0,
												                dataLabels: {
												                    enabled: true,
												                    format: '{point.y}'
												                }
												            }
												        },
												
												        tooltip: {
												            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
												            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
											        },
											
											        series: [{
											            name: 'Patient View Package',
											            colorByPoint: true,
											            data: [<%for (Accounts accClient : misChartForm.getPatientviewbypackage()) {%>
											            
									            		{
									                			name: '<%=accClient.getPackagename()%>',
									                			y: <%=accClient.getPatientno()%>,
									                			drilldown: '<%=accClient.getPackagename()%>'
									            		},
									            	   <%}%>
											            ]
											        }],
											        drilldown: {
											            series: [{
											                name: 'Charges Added',
											                id: 'Charges Added',
											                data: [
											                    [
											                        'v11.0',
											                        24.13
											                    ],
											                    [
											                        'v8.0',
											                        17.2
											                    ],
											                    [
											                        'v9.0',
											                        8.11
											                    ]
											                ]
											            }, {
											                name: 'Invoices Generated',
											                id: 'Invoices Generated',
											                data: [
											                    [
											                        'v40.0',
											                        5
											                    ],
											                    [
											                        'v41.0',
											                        4.32
											                    ],
											                    [
											                        'v42.0',
											                        3.68
											                    ],
											                    [
											                        'v39.0',
											                        2.96
											                    ],
											                    [
											                        'v36.0',
											                        2.53
											                    ],
											                    
											                    [
											                        'v35.0',
											                        0.85
											                    ],
											                    [
											                        'v38.0',
											                        0.6
											                    ],
											                    [
											                        'v30.0',
											                        0.14
											                    ]
											                ]
											            }
							
									            ]
							
									        }           
					                   			
                   			           });
                   			        });
                   			        
                   			       </script>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /.col-md-8 -->


					</div>

				</div>
			</div>
			<!-- main content area end -->

		</div>
	</div>
	<!-- page container area end -->


	<!-- Excel download data -->

	<!-- Daily summary -->

	<table class="my-table tabledaily" id="dailyReportTable "
		style="width: 100%; font-size: 8px; display: none;">
		<thead>
			<tr>
				<th>Daily Summary</th>
				<th>(<s:property value="fromDate" /> to <s:property
						value="toDate" />)
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><b>Total OPD Booked</b></td>
				<td><s:property value="totalopdseen" /></td>
			</tr>
			<tr>
				<td><b>Total OPD Completed</b></td>
				<td><s:property value="totalopdcompleted" /></td>
			</tr>
			<tr>
				<td><b>Total OPD-DNA</b></td>
				<td><s:property value="totalopddna" /></td>
			</tr>

			<s:iterator value="opdappointmenttype">
				<s:if test="result==0">
				</s:if>
				<s:else>
					<tr>
						<td><s:property value="name" /></td>
						<td><s:property value="result" /></td>
					</tr>
				</s:else>
			</s:iterator>

			<tr>
				<td><b>Total IPD Admission</b></td>
				<td><s:property value="ipdnewadmission" /></td>
			<tr>

				<td>Total Patients In-house Today</td>
				<td><s:property value="inhousepatients" /></td>
			</tr>

			<tr>
				<td>MLC Admission</td>
				<td><s:property value="totalmlcaddmission" /></td>
			</tr>

			<tr>
				<td>Total Discharged</td>
				<td><s:property value="dischargepatients" /></td>
			</tr>

			<tr>
				<td>Total Death</td>
				<td><s:property value="totaldeath" /></td>
			</tr>

			<tr>
				<td>Total DAMA</td>
				<td><s:property value="totalDAMA" /></td>
			</tr>

			<tr>
				<td><b>Total OT </b></td>
				<td><s:property value="otPatientCount" /></td>
			</tr>
			<!-- <tr>
                            <td>-Total HAEMODIALYSIS</td>
                             <td></td>
                        </tr>
                        <tr>
                            <td>-Total CATH LAB </td>
                             <td></td>
                        </tr> -->

			<tr>
				<td><b>Total Investigation</b></td>
				<td><s:property value="totalInvestigation" /></td>
			</tr>
			<!-- pathlab/radiology/maicrobiology/endoscopy/cardiology -->

			<tr>
				<td>Total Pathlab</td>
				<td><s:property value="totalpathlab" /></td>
			</tr>

			<%-- <tr>
                            <td>-Total Radiology</td>
                             <td><s:property value="totalradiology"/></td>
                        </tr> --%>

			<tr>
				<td>Total Microbiology</td>
				<td><s:property value="totalmaicrobiology" /></td>
			</tr>

			<tr>
				<td>Total Endoscopy</td>
				<td><s:property value="totalendoscopy" /></td>
			</tr>

			<tr>
				<td>Total Cardiology</td>
				<td><s:property value="totalcardiology" /></td>
			</tr>


			<tr>
				<td>Total CT SCAN</td>
				<td><s:property value="totalctinvest" /></td>
			</tr>

			<tr>
				<td>Total MRI</td>
				<td><s:property value="totalmricount" /></td>
			</tr>

			<tr>
				<td>Total XRAY</td>
				<td><s:property value="totalxraycount" /></td>
			</tr>

			<tr>
				<td>Total USG</td>
				<td><s:property value="totalsonographycount" /></td>
			</tr>

			<tr>
				<td>NON INVASIVE CARDIOLOGY</td>
				<td><s:property value="totalcardiologycount" /></td>
			</tr>

			<!-- <tr>
                            <td>-Total TMT</td>
                             <td></td>
                        </tr> -->


			<tr>
				<td>Total IPD & OPD Charges Added</td>
				<td><s:property value="chargeaddedd" /></td>
			</tr>

			<tr>
				<td>Total IPD & OPD Payment</td>
				<td><s:property value="paymentrecieved" /></td>
			</tr>

		</tbody>
	</table>

	<!-- OPD Patients -->

	<table class="my-table tablexls" id="opdPatientTable "
		style="width: 100%; font-size: 8px; display: none;">
		<thead>
			<tr>
				<th>Date</th>
				<th>Invoiceid</th>
				<th>Name</th>
				<th>Payee</th>
				<th>Practitioner</th>
				<th>Appointment Type</th>
				<th>Status</th>
				<th>UHID</th>
				<th>Mobile</th>
				<th>Charges</th>
				<th>Amount</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="opdPatientReport">

				<tr>
					<td><s:property value="dob" /></td>
					<td><s:property value="invstid" /></td>
					<td><s:property value="clientName" /></td>
					<td><s:property value="whopay" /></td>
					<td><s:property value="diaryUser" /></td>
					<td><s:property value="apmttypetext" /></td>
					<td><s:property value="apmtStatus" /></td>
					<td><s:property value="abrivationid" /></td>
					<td><s:property value="mobNo" /></td>
					<td><s:property value="charges" /></td>
					<td><s:property value="apmtcharges" /></td>


				</tr>
			</s:iterator>
			<s:iterator value="opdPatientCancelReport">

				<tr>
					<td><s:property value="dob" /></td>
					<td><s:property value="invstid" /></td>
					<td><s:property value="clientName" /></td>
					<td><s:property value="whopay" /></td>
					<td><s:property value="diaryUser" /></td>
					<td><s:property value="apmttypetext" /></td>
					<td><s:property value="apmtStatus" /></td>
					<td><s:property value="abrivationid" /></td>
					<td><s:property value="mobNo" /></td>
					<td><s:property value="charges" /></td>
					<td><s:property value="apmtcharges" /></td>

				</tr>
			</s:iterator>
		</tbody>
	</table>
	<!--


          Ipd patient Report 

            -->
	<table class="my-table tableipdxls" id="ipdPatientTable "
		style="width: 100%; font-size: 8px; display: none;">
		<thead>
			<tr>
				<th>Sr No</th>
				<th>Date Of Admission</th>
				<th>UHID</th>
				<th>Ward/Bed</th>
				<th>Patient Name</th>
				<th>Payee</th>
				<th>Admiting Doctor</th>
				<th>Refered Doctor</th>
				<th>Diagnosis</th>
				<th>Date Of Discharged</th>
				<th>Total Days</th>
				<th>Status</th>
				<th>MLC No</th>
				<th>MLC Refered Doctor</th>

			</tr>
		</thead>
		<tbody>
			<%
				int i = 0;
			%>
			<s:iterator value="ipdPatientReport">

				<tr>
					<td><%=(++i)%></td>

					<td><s:property value="doanew" /></td>
					<td><s:property value="abrivationid" /></td>
					<td><s:property value="wardbedname" /></td>
					<td><s:property value="clientname" /></td>
					<td><s:property value="whopay" /></td>
					<td><s:property value="practitionername" /></td>
					<td><s:property value="refferedby" /></td>
					<td><s:property value="conditionname" /></td>
					<td><s:property value="dodnew" /></td>
					<td><s:property value="totalDays" /></td>
					<td><s:property value="status" /></td>
					<td><s:property value="mlcno" /></td>
					<td><s:property value="mlcrefdoctor" /></td>

				</tr>
			</s:iterator>
		</tbody>
	</table>

	<!-- Bed Status -->
	<table class="my-table xlsbedtable" id="bedStatustable "
		style="width: 100%; font-size: 8px; display: none;">

		<thead>
			<tr>
				<th>UHID</th>

				<th>Patient Name</th>
				<th>Admission Doctor</th>
				<th>Consulting Doctor</th>
				<th>Admission Date</th>
				<th>Ward</th>
				<th>Bed Name</th>
				<th>Payee</th>
				<th>City</th>
				<!--  <th>User ID</th>  -->
				<th>MLC NO</th>
				<!-- <th>Status</th> -->

			</tr>
		</thead>
		<tbody>
			<s:iterator value="bedlist">
				<tr>
					<td><s:property value="abrivationid" /></td>

					<td><s:property value="clientname" /></td>
					<td><s:property value="practitionername" /></td>
					<td><s:property value="secndryconsult" /></td>
					<td><s:property value="admissiondate" /></td>
					<td><s:property value="wardname" /></td>
					<%-- 	<td><s:property value="sectionname" /></td> --%>
					<td><s:property value="bedname" /></td>
					<td><s:property value="town" /></td>
					<td><s:property value="whopay" /></td>

					<%-- <td><s:property value="setUserid" /></td> --%>
					<td><s:property value="mlcno" /></td>
					<%-- <td>
														<s:if test="status==1">
															Booked
														</s:if>
														<s:else>
															Available
														</s:else>
													<td> --%>

				</tr>
			</s:iterator>
		</tbody>

	</table>


	<!-- Prescription Status -->
	<table class="my-table xlsprescription" id="prescriptiontable "
		style="width: 100%; font-size: 8px; display: none;">

		<thead>
			<tr>

				<th>UHID</th>
				<th>Patient Name</th>
				<th>Age/Gender</th>
				<th>Ward/Bed</th>
				<th>Date</th>
				<th>Note</th>
				<th>Status</th>
				<th>Delivery Date</th>
				<th>Avg Time</th>


			</tr>
		</thead>
		<tbody>

			<s:iterator value="priscriptionReport">
				<tr>
					<td><s:property value="abrivationid" /></td>
					<td><s:property value="fullname" /></td>
					<td><s:property value="ageandgender" /></td>
					<td><s:property value="Wardbed" /></td>
					<td><s:property value="lastmodified" /></td>
					<td><s:property value="dosenotes" /></td>
					<td><s:property value="dstatus" /></td>
					<td><s:property value="deliverydate" /></td>
					<td><s:property value="averagetime" /></td>
				</tr>
			</s:iterator>
		</tbody>

	</table>
	<!--Pharmacy Daily Sale Report @ -->
	<table class="my-table xlspharmacysaletable"
		style="width: 100%; font-size: 8px; display: none;">

		<thead>
			<tr>
				<th>Credit</th>
				<th>Credit Return</th>
				<th>Hospital</th>
				<th>Hospital Return</th>
				<th>Cash</th>
				<th>Card</th>
				<th>Cheque</th>
				<th>NEFT/RTGS</th>
				<th>Total Received</th>
				<th>Cash Return</th>
				<th>Discount</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pharmacydailysalelist">
				<tr>
					<td><s:property value="totalcredit" /></td>
					<td><s:property value="creditReturn" /></td>
					<td><s:property value="hospital" /></td>
					<td><s:property value="hospitalReturn" /></td>
					<td><s:property value="todaycash" /></td>
					<td><s:property value="todaycard" /></td>
					<td><s:property value="chequepayments" /></td>
					<td><s:property value="neftpayment" /></td>
					<td><s:property value="totalpayment" /></td>
					<td><s:property value="todayReturn" /></td>
					<td><s:property value="todaydisc" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<!--Procument Report @ -->
	<table class="my-table xlsprocurmenttable"
		style="width: 100%; font-size: 8px; display: none;">

		<thead>
			<tr>
				<th>No of GRN with PO</th>
				<th>No of GRN with PO</th>
				<th>Total GRN</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%=misChartForm.getGrnwithoutpo()%></td>
				<td><%=misChartForm.getGrnwithpo()%></td>
				<td><%=misChartForm.getTotalgrn()%></td>
			</tr>

		</tbody>
	</table>
	<!--Indent Report @ -->
	<table class="my-table xlsindenttable"
		style="width: 100%; font-size: 8px; display: none;">

		<thead>
			<tr>
				<th>Direct Transfer</th>
				<th>Request</th>
				<th>Approved</th>
				<th>Rejected</th>
				<th>Delivered</th>
				<th>Received</th>
				<th>Po Created</th>
				<th>Pending</th>
				<th>Ready To Transfer</th>
				<th>Total Indent</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%=misChartForm.getDirect_transfer()%></td>
				<td><%=misChartForm.getRequest()%></td>
				<td><%=misChartForm.getApproved()%></td>
				<td><%=misChartForm.getRejected()%></td>
				<td><%=misChartForm.getTransfer()%></td>
				<td><%=misChartForm.getReceived()%></td>
				<td><%=misChartForm.getPocreated()%></td>
				<td><%=misChartForm.getPending()%></td>
				<td><%=misChartForm.getReadytotransfer()%></td>
				<td><%=misChartForm.getTotalindent()%></td>
			</tr>

		</tbody>
	</table>
	<!--Pharmacy Daily Sale Report @ -->
	<table class="my-table xlsinvestigationdetailstable"
		style="width: 100%; font-size: 8px; display: none;">

		<thead>
			<tr>
				<th>Reg. ID</th>
				<th>Patient Name</th>
				<th>Doctor</th>
				<th>Date</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="investigationlist">
				<tr>
					<td><s:property value="abrivationid" /></td>
					<td><s:property value="fullname" /></td>
					<td><s:property value="practitionerName" /></td>
					<td><s:property value="date" /></td>
					<td><s:if test="investigation_status==1">New</s:if> <s:elseif
							test="investigation_status==2">Collected</s:elseif> <s:elseif
							test="investigation_status==3">Completed</s:elseif> <s:elseif
							test="investigation_status==4">Aproved</s:elseif> <s:elseif
							test="investigation_status==5">Cancel</s:elseif></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<!--


         Invoic Report 

        -->
	<table class="my-table xlstinvoicesable"
		style="width: 100%; font-size: 8px; display: none;">
		<col width="7%">
		<col width="7%">
		<col width="15%">
		<col width="30%">
		<col width="7%">
		<col width="7%">
		<col width="7%">
		<col width="7%">
		<col width="7%">
		<thead>
			<tr>
				<th id="datesortdid">Invoice Date</th>
				<th>Invoice</th>
				<th>Client</th>

				<th>Payee</th>
				<th>Status</th>
				<th style="text-align: right;">Debit</th>
				<th style="text-align: right;">Credit</th>
				<th style="text-align: right;">Discount</th>
				<th style="text-align: right;">Balance</th>

			</tr>
		</thead>
		<tbody>
			<s:if test="invoiceList.size!=0">
				<s:iterator value="invoiceList" status="rowstatus">
					<tr id="<s:property  value="id" />">
						<td><s:property value="date" /></td>
						<td><s:property value="id" /></td>
						<%-- 	<td><s:property value="id" /><a
											href="javascript: void(0);"
											onclick="showInnerDiv('hiddenDetailsDiv<s:property value="id"/>','<s:property value="id"/>');"><i
												class="fa fa-arrow-down"></i></a></td>
										 --%>
						<td><s:property value="clientName" /></td>
						<td><s:property value="payby" /> (<s:property
								value="payeeName" />)</td>
						<s:if test="balance==0">
							<td>Paid</td>
						</s:if>

						<s:else>

							<td>Not Paid</td>
						</s:else>

						<td style="text-align: right;"><%=Constants.getCurrency(loginInfo)%><s:property
								value="debitAmountx" /></td>
						<td style="text-align: right;"><%=Constants.getCurrency(loginInfo)%><s:property
								value="creditTotalx" /></td>
						<td style="text-align: right;">(<%=Constants.getCurrency(loginInfo)%><s:property
								value="discAmmount" />) <s:property value="discount" />%
						</td>
						<td style="text-align: right;"><%=Constants.getCurrency(loginInfo)%><s:property
								value="balancex" /></td>
					</tr>

				</s:iterator>
			</s:if>
		</tbody>
	</table>

	<table class="my-table xlxadvref" id="advtable "
		style="width: 100%; font-size: 8px; display: none;">
		<thead>
			<tr>
				<!--     <th>Sr No</th> -->
				<th>Date</th>
				<th>Client Name</th>
				<th>Location</th>
				<th>Advanced</th>
				<th>Refund</th>
				<th>Prepayment</th>
				<th>RefundId</th>
				<th>UHID</th>
			</tr>
		</thead>
		<tbody>

			<s:iterator value="advancedRefundList">

				<tr>
					<%--  <td><%=++i %></td> --%>
					<td><s:property value="date" /></td>
					<td><s:property value="clientName" /></td>
					<td><s:property value="address" /></td>
					<td><s:property value="amount" /></td>

					<td><s:if test="paymentmode=='prepayment'">0.0</s:if>
						<s:else>
							<s:property value="advref" />
						</s:else></td>
					<td><s:if test="paymentmode=='prepayment'">
							<s:property value="advref" />
						</s:if>
						<s:else>0.0</s:else></td>
					<td><s:property value="refinvoiceid" /></td>
					<td><s:property value="abrivationid" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

	<!--
           Account Info- 
         -->
	<table class="my-table xlxacctable" id="opdPatientTable "
		style="width: 100%; font-size: 8px; display: none;">
		<thead>
			<tr>
				<th>Sr No</th>
				<th>Date</th>
				<th>Client Name</th>
				<th>Payment Mode</th>
				<th>Amount</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<%
				i = 0;
			%>
			<s:iterator value="accountinfo">

				<tr>
					<td><%=(++i)%></td>
					<td><s:property value="date" /></td>
					<td><s:property value="clientName" /></td>
					<td><s:property value="paymentmode" /></td>
					<td><s:property value="payAmount" /></td>
					<td><s:property value="deliverstatus" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

	<table class="my-table xlxclinictbl" id="clinicTable "
		style="width: 100%; font-size: 8px; display: none;">
		<thead>
			<tr>
				<th>Sr No</th>
				<th>Client Name</th>
				<th>Address</th>
				<th>Town</th>
				<th>Diagnosis Name</th>
			</tr>
		</thead>
		<tbody>
			<%
				i = 0;
			%>
			<s:iterator value="clinicalViewList">

				<tr>
					<td><%=(++i)%></td>
					<td><s:property value="clientName" /></td>
					<td><s:property value="address" /></td>
					<td><s:property value="town" /></td>
					<td><s:property value="treatmentType" /></td>
				</tr>
			</s:iterator>

		</tbody>
	</table>

	<table class="table table-no-border m-0" id="patientwt"
		style="display: none;">
		<tbody>
			<s:iterator value="patientbylocation">
				<tr>
					<td class="print-visible hidden-md hidden-lg"><s:property
							value="state" /></td>
					<td><s:property value="town" /></td>
					<td><s:property value="id" /></td>
				</tr>
			</s:iterator>

			<tr class="hidden">
				<td><B>Count of Total OPD Patient</B></td>
				<td><s:property value="totalOpdPatient" /></td>
			</tr>


		</tbody>
	</table>

	<!-- jquery latest version -->
	<%--  <script src="manasmis/js/vendor/jquery-2.2.4.min.js"></script> --%>
	<!-- bootstrap 4 js -->
	<script src="manasmis/js/popper.min.js"></script>
	<script src="manasmis/js/bootstrap.min.js"></script>
	<script src="manasmis/js/owl.carousel.min.js"></script>
	<script src="manasmis/js/metisMenu.min.js"></script>
	<script src="manasmis/js/jquery.slimscroll.min.js"></script>
	<script src="manasmis/js/jquery.slicknav.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
	<!-- start highcharts js -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/data.js"></script>
	<script src="https://code.highcharts.com/modules/drilldown.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>
	<script src="https://code.highcharts.com/modules/accessibility.js"></script>
	<!-- start zingchart js -->
	<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
	<script>
    zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
    ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"];
    </script>
	<!-- start amchart js -->
	<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
	<script src="https://www.amcharts.com/lib/3/serial.js"></script>
	<script
		src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
	<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
	<script src="manasmis/js/line-chart.js"></script>
	<script src="manasmis/js/bar-chart.js"></script>
	<!-- others plugins -->
	<script src="manasmis/js/plugins.js"></script>
	<script src="manasmis/js/scripts.js"></script>
	<script>
      Highcharts.setOptions({
    colors: ['#0a467c', '#50c4ca', '#27b7ff', '#0fd7da','#CEECF2']
});
      Highcharts.chart('container-1', {
  chart: {
    type: 'column'
  },
  title: {
    text: 'Weekly Performance Statistics for May'
  },
  xAxis: {
    categories: [
      'Week 01',
      'Week 02',
      'Week 03',
      'Week 04',
      
    ],
    crosshair: true
  },
  yAxis: {
    min: 0,
    title: {
      text: ''
    }
  },
  tooltip: {
    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
      '<td style="padding:0"><b>{point.y:.1f} number</b></td></tr>',
    footerFormat: '</table>',
    shared: true,
    useHTML: true
  },
  plotOptions: {
    column: {
      pointPadding: 0.2,
      borderWidth: 0
    }
  },
  series: [{
    name: 'OPD Booked',
    data: [49, 71, 106, 129]

  }, {
    name: 'OPD Completed',
    data: [83, 78, 98, 93]

  }, {
    name: 'IPD',
    data: [48, 38, 39, 41]

  }, {
    name: 'OT',
    data: [42, 33, 34, 39]

  },{
    name: 'OPD DNA',
    data: [12, 13, 14, 19]

  }]
});
    </script>
	<script>
      Highcharts.setOptions({
    colors: ['#FE9B00']
	});
     Highcharts.chart('container', {
  chart: {
    type: 'column'
  },
  title: {
    text: 'Daily Summary'
  },
  
  xAxis: {
    type: 'category',
    labels: {
      rotation: -45,
      style: {
        fontSize: '13px',
        fontFamily: 'Open Sans, sans-serif'
      }
    }
  },
  yAxis: {
    min: 0,
    title: {
      text: ''
    }
  },
  legend: {
    enabled: false
  },
  tooltip: {
    pointFormat: '<b>{point.y:.1f} </b>'
  },
  series: [{
    name: 'Population',
    data: [
      ['Total OPD', <%=misChartForm.getTotalopdseen()%>],
      ['Total IPD New Admission', <%=misChartForm.getIpdnewadmission()%>],
      ['Total OT', <%=misChartForm.getOtPatientCount()%>],
    ],
    dataLabels: {
      enabled: true,
      rotation: -90,
      color: '#FFFFFF',
      align: 'right',
      format: '{point.y:.1f}', // one decimal
      y: 10, // 10 pixels down from the top
      style: {
        fontSize: '13px',
        fontFamily: 'Open Sans, sans-serif'
      }
    }
  }]
});
   </script>

	<SCRIPT type="text/javascript">
								        $(function () {
								         // Create the chart
								         $('#dailysummaryg').highcharts({
								         
								         			chart: {
										            type: 'column'
										        },
										        title: {
										            text: 'DailySummary'
										        }, 	
										        xAxis: {
										            type: 'category'
										        },
										        yAxis: {
										            title: {
										                text: 'DailySummary'
										            }
										
										        },
										        legend: {
										            enabled: false
										        },
										        plotOptions: {
										            series: {
										                borderWidth: 0,
										                dataLabels: {
										                    enabled: true,
										                    format: '{point.y}'
										                }
										            }
										        },
										
										        tooltip: {
										            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
										            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> of total<br/>'
										        },
										
										        series: [{
										            name: 'Daily Summary',
										            colorByPoint: true,
										            data: [{
										                name: 'Total OPD',
										                y: <%=misChartForm.getTotalopdseen()%>,
										                drilldown: 'Total OPD'
										            }, {
										                name: 'Total IPD New Admission',
										                y: <%=misChartForm.getIpdnewadmission()%>,
										                drilldown: 'Total IPD New Admission'
										            }, {
										                name: 'Total OT',
										                y: <%=misChartForm.getOtPatientCount()%>,
										                drilldown: 'Total OT'
										            }
										            ]
										        }],
										        drilldown: {
										            series: [{
										                name: 'Total OPD',
										                id: 'Total OPD',
										                data: [
										                    [
										                        'v11.0',
										                        24.13
										                    ],
										                    [
										                        'v8.0',
										                        17.2
										                    ],
										                    [
										                        'v9.0',
										                        8.11
										                    ]
										                ]
										            }, {
										                name: 'Total IPD New Admission',
										                id: 'Total IPD New Admission',
										                data: [
										                    [
										                        'v40.0',
										                        5
										                    ],
										                    [
										                        'v41.0',
										                        4.32
										                    ],
										                    [
										                        'v42.0',
										                        3.68
										                    ],
										                    [
										                        'v39.0',
										                        2.96
										                    ],
										                    [
										                        'v36.0',
										                        2.53
										                    ],
										                    
										                    [
										                        'v35.0',
										                        0.85
										                    ],
										                    [
										                        'v38.0',
										                        0.6
										                    ],
										                    [
										                        'v30.0',
										                        0.14
										                    ]
										                ]
										            }, {
										                name: 'Total OT',
										                id: 'Total OT',
										                data: [
										                    [
										                        'v35',
										                        2.76
										                    ],
										                    [
										                        'v36',
										                        2.32
										                    ],
										                    [
										                        'v37',
										                        2.31
										                    ],
										                    [
										                        'v34',
										                        1.27
										                    ],
										                    [
										                        'v32',
										                        0.15
										                    ]
										                ]
										            }
										            ]
										        }       
								         
								         });
								         });
                   			        </SCRIPT>
	<script type="text/javascript">
                   			 	function printDailyExcel() {
                   				    $(".tabledaily").table2excel({
                   						exclude: ".noExl",
                   						name: "Daily Summary List",
                   						filename: "dailySummaryList",
                   						fileext: ".xls",
                   						exclude_img: true,
                   						exclude_links: true,
                   						exclude_inputs: true
                   					});
                   		     }
                   			function printExcel() {

                   		        $(".tablexls").table2excel({
                   							exclude: ".noExl",
                   							name: "Opd Patients List",
                   							filename: "opdPatientList",
                   							fileext: ".xls",
                   							exclude_img: true,
                   							exclude_links: true,
                   							exclude_inputs: true
                   						});
                   					}
                   			
                   			function printIpdExcel() {

                   		        $(".tableipdxls").table2excel({
                   							exclude: ".noExl",
                   							name: "Ipd Patients List",
                   							filename: "ipdPatientList",
                   							fileext: ".xls",
                   							exclude_img: true,
                   							exclude_links: true,
                   							exclude_inputs: true
                   						});
                   		         }
                   			function printBedStatusExcel() {

                   		        $(".xlsbedtable").table2excel({
                   							exclude: ".noExl",
                   							name: "Bed Status List",
                   							filename: "bedStatusList",
                   							fileext: ".xls",
                   							exclude_img: true,
                   							exclude_links: true,
                   							exclude_inputs: true
                   						});
                   		         }
                   		       function printPrescriptionExcel() {

                   		           $(".xlsprescription").table2excel({
                   		 					exclude: ".noExl",
                   		 					name: "Priscription List",
                   		 					filename: "PrescriptionList",
                   		 					fileext: ".xls",
                   		 					exclude_img: true,
                   		 					exclude_links: true,
                   		 					exclude_inputs: true
                   		 				});
                   		            }
                   		       function printPharmacyDailySaleExcel() {

                   		           $(".xlspharmacysaletable").table2excel({
                   		 					exclude: ".noExl",
                   		 					name: "Pharmacy Sale List",
                   		 					filename: "pharmacydailysalelist",
                   		 					fileext: ".xls",
                   		 					exclude_img: true,
                   		 					exclude_links: true,
                   		 					exclude_inputs: true
                   		 				});
                   		            }
                   		       function printProcurmentExcel() {

                   		           $(".xlsprocurmenttable").table2excel({
                   		 					exclude: ".noExl",
                   		 					name: "Procurment List",
                   		 					filename: "procurmentList",
                   		 					fileext: ".xls",
                   		 					exclude_img: true,
                   		 					exclude_links: true,
                   		 					exclude_inputs: true
                   		 				});
                   		            }
                   		       
                   		       function printIndentExcel() {

                   		           $(".xlsindenttable").table2excel({
                   		 					exclude: ".noExl",
                   		 					name: "Indent List",
                   		 					filename: "indentList",
                   		 					fileext: ".xls",
                   		 					exclude_img: true,
                   		 					exclude_links: true,
                   		 					exclude_inputs: true
                   		 				});
                   		            }
                   		       function printInvestigationDetailsExcel() {

                   		           $(".xlsinvestigationdetailstable").table2excel({
                   		 					exclude: ".noExl",
                   		 					name: "Investigation Details List",
                   		 					filename: "InvestigationDetailsList",
                   		 					fileext: ".xls",
                   		 					exclude_img: true,
                   		 					exclude_links: true,
                   		 					exclude_inputs: true
                   		 				});
                   		            }
                   		       
                   		       function printInvoiceExcel() {

                   		           $(".xlstinvoicesable").table2excel({
                   		 					exclude: ".noExl",
                   		 					name: "Invoice and Billing List",
                   		 					filename: "invoiceBillingReport",
                   		 					fileext: ".xls",
                   		 					exclude_img: true,
                   		 					exclude_links: true,
                   		 					exclude_inputs: true
                   		 				});
                   		            }
                   		       function printAdvRefExcel() {

                   		           $(".xlxadvref").table2excel({
                   		 					exclude: ".noExl",
                   		 					name: "Advanced or Refund List",
                   		 					filename: "advRefReport",
                   		 					fileext: ".xls",
                   		 					exclude_img: true,
                   		 					exclude_links: true,
                   		 					exclude_inputs: true
                   		 				});
                   		            }
                   		       
                   		       function printPatientwExcel(){
                   		      	 
                   		      	 $("#patientwt").table2excel({
                   		      		
                   		      		 exclude: ".noExl",
                   							name: "Patientw List",
                   							filename: "Patientw Data",
                   							fileext: ".xls",
                   							exclude_img: true,
                   							exclude_links: true,
                   							exclude_inputs: true
                   		      	 });
                   		      	 
                   		       }
                   		       function printClinicExcel() {

                   		           $(".xlxclinictbl").table2excel({
                   		 					exclude: ".noExl",
                   		 					name: "Clinical Info List",
                   		 					filename: "clinicReport",
                   		 					fileext: ".xls",
                   		 					exclude_img: true,
                   		 					exclude_links: true,
                   		 					exclude_inputs: true
                   		 			});
                   		         }
                   		       function printAccExcel() {

                   		           $(".xlxacctable").table2excel({
                   		 					exclude: ".noExl",
                   		 					name: "Account Info List",
                   		 					filename: "accInfoReport",
                   		 					fileext: ".xls",
                   		 					exclude_img: true,
                   		 					exclude_links: true,
                   		 					exclude_inputs: true
                   		 			});
                   		         }
                   		       
                   			        </script>

</body>

</html>
