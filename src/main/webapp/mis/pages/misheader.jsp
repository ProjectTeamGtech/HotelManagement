<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="com.apm.Report.eu.entity.MisReport"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Mis.web.form.MisChartForm"%>
<%@page import="com.apm.Expence.eu.entity.Expence"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
.hide{

    display: none;
}

.topback2 {
    background-color: #f5f5f5;
    padding: 0px 0px 0px 0px !important; 
}
.title {
   margin-left: 0px;
   font-size: 14px !important; 
}
.details {
    background: none !important; 
        margin-bottom: 0px !important; 
}
#header .nav-right > li, #header .nav-left > li {
     margin-top: 0px !important;  
    vertical-align: top;
}

.pageheader .page-bar .page-toolbar .btn {
    padding: 5px 10px;
}
#header {
    background-color: #43b9be !important;
}

    .mainheader {
    background-color: #43b9be !important;
}
.setdorp{
	width:13%;
    margin-right: 10px;
    margin-top: 6px;
    margin-bottom: 6px;
}
.totext{
	    margin-right: 10px;
	    color:#000;
	    margin-top: 6px;
    margin-bottom: 6px;
}
.marrigh10se{
 margin-right: 10px;
 margin-top: 6px;
    margin-bottom: 6px;
}
.hz-menu #navigation > li.dropdown > ul {
    left: 0px !important; 
}
    #navigation > li {
    margin: 0px 0px 0px 0px !important; 
}
.hz-menu #navigation > li > a {
    left: 0px !important; 
        padding: 0px !important;
}
.hz-menu #navigation > li.dropdown > a > i:last-of-type:before {
    content: "" !important;
}
.lisetroprt{
	background-color: #424A5D;
    padding: 2px 20px 2px 20px;
}
/*  bhoechie tab */
div.bhoechie-tab-container{
  
  background-color: #ffffff;
  padding: 0 !important;
  border-radius: 0px;
  -moz-border-radius: 0px;
  margin-top: -8px;
  margin-left: 0px;
  background-clip: padding-box;
  opacity: 0.97;
  filter: alpha(opacity=97);
}
div.bhoechie-tab-menu{
  padding-right: 0;
  padding-left: 0;
  padding-bottom: 0;
}
div.bhoechie-tab-menu div.list-group{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a{
  margin-bottom: -1;
  text-align: left;
  font-size: 13px;
}
div.bhoechie-tab-menu div.list-group>a .glyphicon,
div.bhoechie-tab-menu div.list-group>a .fa {
  color: #5A55A3;
}
div.bhoechie-tab-menu div.list-group>a:first-child{
  border-top-right-radius: 0;
  -moz-border-top-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a:last-child{
  border-bottom-right-radius: 0;
  -moz-border-bottom-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a.active,
div.bhoechie-tab-menu div.list-group>a.active .glyphicon,
div.bhoechie-tab-menu div.list-group>a.active .fa{
  background-color: #424A5D !important;
  background-image: #5A55A3;
  color: #ffffff;
}
div.bhoechie-tab-menu div.list-group>a.active:after{
  content: '';
  position: absolute;
  left: 100%;
  top: 50%;
  margin-top: -13px;
  border-left: 0;
  border-bottom: 13px solid transparent;
  border-top: 13px solid transparent;
  border-left: 10px solid #424A5D !important;
}
.list-group-item.active, .list-group-item.active:hover, .list-group-item.active:focus {
    z-index: 2;
    color: #fff;
    background-color: #424A5D !important;
    border-color: #424A5D !important;
}
div.bhoechie-tab-content{
  background-color: #ffffff;
  /* border: 1px solid #eeeeee; */
  padding-left: 0px;
  padding-top: 0px;
}

div.bhoechie-tab div.bhoechie-tab-content:not(.active){
  display: none;
}
.list-group-item.active, .list-group-item.active:hover, .list-group-item.active:focus {
    text-shadow: none !important;
    background-image: none !important;
    background-image: none !important;
    background-image: none !important;
    background-repeat: repeat-x;
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff7ecefd', endColorstr='#ff58c0fc', GradientType=0);
    border-color: #58c0fc;
}
.list-group-item:first-child {
    border-top-right-radius: 0px !important;
    border-top-left-radius: 0px !important;
}
.backsetrcolo{
	background-color: #6699CC;
    padding: 9px 15px 0px 15px;
    text-align: right;
    min-height: 42px;
    color: #fff;
}
.paddinillll{
	padding-right:0px;
	padding-left:0px;
}
.list-group-item:last-child {
    margin-bottom: 0;
    border-bottom-right-radius: 0px !important;
    border-bottom-left-radius: 0px !important;
}
.title {
   color: #555 !important;
   font-weight: bold !important;
}
.progress-list .details .title {
    line-height: 15px !important;
}
.progress.progress-xs {
    height: 30px !important;
}
.progress-list .status {
    display: inline-block;
    font-size: 12px !important;
    padding: 6px;
}
.standard-section-data-label {
    font-size: 12pt;
    font-weight: bold;
    color: green;
    margin: 5px;
}
.imbot10a {
    margin-bottom: 10px;
    margin-top: 10px !important;
}
.table.table-no-border > thead > tr td, .table.table-no-border > thead > tr th, .table.table-no-border > tbody > tr td, .table.table-no-border > tbody > tr th, .table.table-no-border > tfoot > tr td, .table.table-no-border > tfoot > tr th {
    border: 0;
    font-size: 14px !important;
    color: #424A5D !important;
}
.minheatset{
	min-height: 530px;
}



/* md */ @media (min-width:992px) and (max-width:1199px){
.responsivemng{
	width:450px !important;
}
.responsivecircle{
	width:280px !important;
}
.responsivecircle1{
	width:540px !important;
}
}

.list-group-item {
    padding: 4px 10px !important;
}
.bgste{
	background-color: rgba(245, 245, 245, 0.31) !important;
}
.panel-default>.panel-heading {
    background-color: transparent;
    color: #000;
    padding: 0px !important;
    border: transparent;
}
.panel .panel-heading .panel-title > a {
    display: block;
    cursor: pointer;
    font-size: 13px;
    padding: 2px 15px;
    border: none !important;
    margin-top: 5px;
}
.panel-body {
    padding: 0px 0px 0px 15px;
}
.panel {
    border: none !important;
}
.inlisettext{
	line-height: 23px;
    color: #000;
    font-size: 14px;
}
.panel-group .panel+.panel {
     margin-top: 0px;
}
.minsetheight{
	padding: 15px;background-color: rgba(245, 245, 245, 0.32);min-height: 400px;
}
</style>
</head>
<% LoginInfo loginInfo1 = LoginHelper.getLoginInfo(request); %>
<body>
<div class="col-lg-12 col-md-12 paddingnil topback2">
										<div class="col-lg-12 col-md-12 topback2">
										<div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -15px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown">
                                             <a href="#" title="Billing Reports"><i class="fa"></i> Billing</a>
                                                        <ul>
                                                        <li>
                                                        <%if(loginInfo1.getUserType()==2 ||loginInfo1.isCharges()){ %>  <li><a href="#" onclick="openPopup('ChargesRpt')"><i class="fa fa-caret-right"></i>Charges Report</a></li>
                                                        <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isInvoice()){ %> <li><a href="#" onclick="openPopup('invoiceReportChargesRpt')"><i class="fa fa-caret-right"></i>Invoice Report</a></li>
                                                        <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isPayment_report_detailed()){ %> <li><a href="#" onclick="openPopup('paymentReportChargesRpt')"><i class="fa fa-caret-right"></i>Payment Report Detailed</a></li>
                                                              <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isPayment_report_small()){ %> <li><a href="#" onclick="openPopup('smallpaymentReportChargesRpt')"><i class="fa fa-caret-right"></i>Payment Report Summary</a></li>
                                                        <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isCharges_share()){ %> <li><a href="#" onclick="openPopup('chargesharereportSummary')"><i class="fa fa-caret-right"></i>Charge Share Report</a></li>
                                                        <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isBilling()){ %>  <li><a href="#" onclick="openPopup('billingreportSummary')"><i class="fa fa-caret-right"></i>Billing Report</a></li>
                                                             <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isDiscount()){ %>  <li><a href="#" onclick="openPopup('discountreportSummary')"><i class="fa fa-caret-right"></i>Discount Report</a></li>
                                                              <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isCancel_invoice()){ %> <li><a href="#" onclick="openPopup('cancelinvoicereportSummary')"><i class="fa fa-caret-right"></i>Cancel Invoice Report</a></li>
                                                       	   <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isPayment()){ %>	<li><a href="#" onclick="openPopup('paymentreportSummary')"><i class="fa fa-caret-right"></i>Payment Report</a></li>
                                                              <%}%><li class="hidden"><a onclick="openPopup('paymentreceivedreportChargesRpt')" href="#"><i class="fa fa-caret-right"></i>Payment received report</a></li>
                                                       		<li><a onclick="openPopup('ipdopdRefundReportSummary')" href="#"><i class="fa fa-caret-right"></i>Refund report</a></li>
                                                       		<!-- <li><a href="#" onclick="openPopup('newchargesharereportSummary')"><i class="fa fa-caret-right"></i>Charge Share Report </a></li> -->
                                                       		<li><a href="#" onclick="openPopup('payment_report_combinnedChargesRpt')"><i class="fa fa-caret-right"></i>Payment Report Combined</a></li>
                                                       		<li><a href="#" onclick="openPopup('paymentreciptreportSummary')"><i class="fa fa-caret-right"></i>Payment Receipt Report  <img src="dashboardicon/newdiet.gif"></img></a></li>
                                                        </li>
                                                        </ul>
                                                        </div>
											<div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -36px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px;">
                                             <a href="#" title="Account Reports"><i class=""></i> Account</a>
                                              <ul>
                                                	<li class="dropdown submenu"><a href="#"><i class="fa fa-caret-right"></i>Practitioners Share Report<i class="fa fa-plus"></i></a>
                                                	<ul>
                                                	<li>
                                                <%if(loginInfo1.getUserType()==2 ||loginInfo1.isPractioner_share()){ %>    <li><a href="#" onclick="openPopup('PractitionerListCommission?action=0')"><i class="fa fa-caret-right"></i>Practitioners Share Report</a></li>
                                                                 <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isOpd_practioner_share()){ %>  <li><a href="#" onclick="openPopup('OPDPractitionerListCommission')"><i class="fa fa-caret-right"></i>OPD Practitioners Share Report</a></li>
                                                               <% } %> 
                                                               <% if(loginInfo1.getUserType()==2 ||loginInfo1.isOpd_practioner_share()){ %>  <li><a href="#" onclick="openPopup('practshareopdnewSummary')"><i class="fa fa-caret-right"></i>Deptwise OPD Practitioners Share Report Patient Count</a></li>
                                                               <% } %>
                                                               <li><a onclick="openPopup('wardwiseavgstaySummary')" href="#"><i class="fa fa-caret-right"></i>Ward Wise Bed Occupancy & Revenue Report</a></li>
                                                	</li>
                                                	</ul>
                                                	
                                                	</li>
                                                               <%if(loginInfo1.getUserType()==2 ||loginInfo1.isUserwise_payment()){ %>  <li><a href="#" onclick="openPopup('userwisepaymentreportSummary')"><i class="fa fa-caret-right"></i>User Wise Payment Report</a></li>
                                                               <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isAdd_debtors()){ %><li><a href="#" onclick="openPopup('PendingPaymentReports')"><i class="fa fa-caret-right"></i>Add Debtors Report</a></li>
                                                             <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isCa()){ %>  <li><a href="#" onclick="openPopup('invoiceCharges')"><i class="fa fa-caret-right"></i>CA Reports</a></li>
                                                              <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isAuditors()){ %> <li><a href="#" onclick="openPopup('auditorChargesRpt')"><i class="fa fa-caret-right"></i>Auditors Report</a></li>
                                                             <% }%> <li><a href="#" onclick="openPopup('Doctorsharereport')"><i class="fa fa-caret-right"></i>Doctor Share Report</a></li>
                                              <% if(loginInfo1.getUserType()==2 ||loginInfo1.isDeptwise_analysis()){ %>    <li><a href="#" onclick="openPopup('departmentWiseAnalysisReportSummary')"><i class="fa fa-caret-right"></i>Department Wise Analysis Report</a></li>
                                                              
                                                             
                                                   	   <%}  %>	<li><a href="#" onclick="openPopup('opdipdconversionrevenueSummary')"><i class="fa fa-caret-right"></i>Patients OPD to IPD Report</a></li>
                                                       		<li><a onclick="openPopup('vistingconreportSummary')" href="#"><i class="fa fa-caret-right"></i>Visiting Consultation Report</a></li>
                                                       		<li><a onclick="openPopup('newDetailedChargesRpt')" href="#"><i class="fa fa-caret-right"></i>Charges Report Detailed</a></li>
                                                       	<%if(loginInfo1.isPcsAdmin()){ %>	
                                                       		<li><a onclick="openPopup('pcsgstreportSummary')" href="#"><i class="fa fa-caret-right"></i>GST Charges Report </a></li>
                                                       	<%} %>	
                                                       		
                                              </ul>
                                                </li>
                                                </ul>
                                                </div>
                                                 <div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -24px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px">
                                             <a href="#" title="Investigation Reports"><i class="fa"></i> Investigation</a>
                                              <ul>
                                                        		<li><a onclick="openPopup('investoutsourceReport')" href="#"><i class="fa fa-caret-right"></i>Out Source Report</a></li>
                                                        		<li><a onclick="openPopup('LabreportSummary')" href="#"><i class="fa fa-caret-right"></i>Lab report</a></li>
                                                        		<li><a onclick="openPopup('investigationCountSummary')" href="#"><i class="fa fa-caret-right"></i>Investigation count report</a></li>
                                                        		<li><a onclick="openPopup('invstpackagerptSummary')" href="#"><i class="fa fa-caret-right"></i>Investigation Package report</a></li>
                                                        		<!-- <li><a onclick="openPopup('investigationtypewisereportInvestigation')" href="#"><i class="fa fa-caret-right"></i> Investigation Count report</a></li> -->
                                                            	<li><a onclick="openPopup('investigationtypewisereportnewInvestigation')" href="#"><i class="fa fa-caret-right"></i>Investigation Wise Count Report</a></li>
                                                            	<li><a onclick="openPopup('report/pages/investigationrevenuereport.jsp')" href="#"><i class="fa fa-caret-right"></i>Investigation Revenue Report</a></li>
                                                            	<li><a onclick="openPopup('getinvesrevenueNcountInvestigation')" href="#"><i class="fa fa-caret-right"></i>Investigation Revenue And Count Report(Name Wise)</a></li>
                                                            	<li><a onclick="openPopup('ipdopdrevenueinvstrevnueSummary')" href="#"><i class="fa fa-caret-right"></i>Investigation IPD/OPD Report</a></li>
                                                            	<li><a onclick="openPopup('investigationtatReport')" href="#"><i class="fa fa-caret-right"></i>Investigation TAT Report</a></li>
                                                            	<li><a onclick="openPopup('invstabsvalreportSummary')" href="#"><i class="fa fa-caret-right"></i>Investigation Critical Value Report</a></li>
                                                            </ul>
                                                      	</li>
                                                      	</ul></div>
                                                      	<div class="col-lg-1 col-md-1 hidden-xs">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px">
                                             <a href="#" title=" Pharmacy Reports"><i class="fa"></i> Pharmacy</a>
                                              <ul>
                                              <li>
                                              <%if(loginInfo1.getUserType()==2 ||loginInfo1.isPayment_recive()){%>   <li><a onclick="openPopup('paymentreceivereportProduct')" href="#"><i class="fa fa-caret-right"></i>Payment Receive  Report</a>
                                              <%}if(loginInfo1.getUserType()==2 ||loginInfo1.isSales_report()){%>         <li><a onclick="openPopup('salereportProduct')" href="#"><i class="fa fa-caret-right"></i>Sale Report</a></li>
                                              <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isExpiry_medicine_report()){%>        	<li><a onclick="openPopup('expirymedicineProduct')" href="#"><i class="fa fa-caret-right"></i>Expiry Product Report</a></li>
                                              <% }%>
                                              <li><a onclick="openPopup('itemwisereportProduct')" href="#"><i class="fa fa-caret-right"></i>Item Wise Sale Report</a></li>
                                              <li><a href="patientconsumptionreportProduct"><i class="fa fa-caret-right"></i>Patient/User Consumption Report</a></li>
                                              <li><a onclick="openPopup('payableagingReport')" href="#"><i class="fa fa-caret-right"></i>Payable Aging Report</a></li>
                                              <li><a onclick="openPopup('stockreportReport?isfromcathlab=0')" href="#"><i class="fa fa-caret-right"></i>Stock Report</a></li>
                                              </li>
                                              </ul>
                                              </li>
                                              </ul>
                                              </div>
                                              
                                              <div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -13px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px">
                                                        <a href="#" title="Inventory Report"><i class="fa"></i> Inventory</a>
                                                        <ul>
                                                        	<%if(loginInfo1.isReport()){ %>
                                                        	<li class="dropdown submenu">
		                                            			<a href="#"><i class="fa fa-caret-right"></i> Accounts</a>
		                                                        <ul>
		                                                            <li><a href="#" onclick="openBlankPopup('salereportProduct')"><i class="fa fa-caret-right"></i> Sale Report</a></li>
		                                                            <li><a href="#" onclick="openBlankPopup('paymentreceivereportProduct')"><i class="fa fa-caret-right"></i> Payment Receive  Report</a></li>
		                                                            <li><a href="" onclick="openBlankPopup('itemwisereportProduct')"><i class="fa fa-caret-right"></i>Item Wise Sale Report</a></li>
		                                                            <li><a href="" onclick="openBlankPopup('productwisereportProduct')"><i class="fa fa-caret-right"></i>Product Wise Sale Report</a></li>
		                                                            <li><a href="" onclick="openBlankPopup('cataloguewisesalereportProduct')"><i class="fa fa-caret-right"></i>Catalogue Wise Sale Report</a></li>
		                                                			<li><a href="" onclick="openBlankPopup('vatreportProduct')"><i class="fa fa-caret-right"></i> GST Report</a></li>
		                                                			<li><a href="" onclick="openBlankPopup('inventorygstreportProduct')"><i class="fa fa-caret-right"></i> GRN GST REPORT</a></li>
		                                                            <li><a href="" onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=0&ismonthlyreport=1')"><i class="fa fa-caret-right"></i> Inventory Opening Closing</a></li>
		                                                			<li><a href="" onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=0&ismonthlyreport=0')"><i class="fa fa-caret-right"></i> Detailed Inventory Opening Closing</a></li>
		                                                			<%if(loginInfo1.isBalgopal()){ %>
		                                                				<li><a href="" onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=1')"><i class="fa fa-caret-right"></i> Inventory Opening Closing By Catalogue</a></li> 
		                                                			<%}else{ %>
		                                                				<li><a href="" onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=0')"><i class="fa fa-caret-right"></i> Inventory Opening Closing By Catalogue</a></li> 
		                                                			<%} %>
		                                                			<li><a href="" onclick="openBlankPopup('supplierpaymentreportProduct')"><i class="fa fa-caret-right"></i> Supplier Payment Report</a></li>
		                                                        </ul>
                                            				</li>
                                            				<li class="dropdown submenu">
		                                            			<a href="#"><i class="fa fa-caret-right"></i> Inventory</a>
		                                                        <ul>
		                                                           	<li><a href="#" onclick="openBlankPopup('itemwisestockreportProduct')"><i class="fa fa-caret-right"></i> Item Wise Stock Report</a></li>
				                                                   	<li><a href="#" onclick="openBlankPopup('bincardreportProduct')"><i class="fa fa-caret-right"></i> Bin Card Old Report</a></li> 
				                                                   	<li><a href="#" onclick="openBlankPopup('bincardreportnewProduct')"><i class="fa fa-caret-right"></i>Bin Card New Report</a></li>
				                                                  	<!--  <li><a href="supplierledgerreportProduct"><i class="fa fa-caret-right"></i> Purchase Report</a></li> -->
				                                                   	<li><a href="#" onclick="openBlankPopup('expirymedicineProduct')"><i class="fa fa-caret-right"></i> Expiry Product Report</a></li>
		                                                		  	<li><a href="#" onclick="openBlankPopup('productReceivedReportProduct')"><i class="fa fa-caret-right"></i> GRN Report</a></li>
		                                                		  	<li><a href="#" onclick="openBlankPopup('consumptionreportProduct')"><i class="fa fa-caret-right"></i> Consumption Report</a></li>
		                                                		  	<li><a href="#" onclick="openBlankPopup('patientconsumptionreportProduct?isfromcathlab=0')"><i class="fa fa-caret-right"></i>Patient/User Consumption Report</a></li>
		                                                		  	<li><a href="#" onclick="openBlankPopup('transferreportProduct')"><i class="fa fa-caret-right"></i> Indent Statement Report</a></li>
		                                                        	<li><a href="#" onclick="openBlankPopup('indentreportProduct')"><i class="fa fa-caret-right"></i> Indent Report</a></li>
		                                                        	<li><a href="#" onclick="openBlankPopup('indentnewreportProduct')"><i class="fa fa-caret-right"></i> Indent New Report</a></li>
		                                                        	<li><a onclick="openBlankPopup('departmaterialissueReport')" href="#"><i class="fa fa-caret-right"></i> Department Wise Material Issue Report</a></li>
		                                                        	<li><a onclick="openBlankPopup('departmaterialsummaryReport')" href="#"><i class="fa fa-caret-right"></i> Department Wise Material Summary Report</a></li>
		                                                        	<li><a onclick="openBlankPopup('payableagingReport')" href="#"><i class="fa fa-caret-right"></i> Payable Aging Report</a></li>
		                                                        	<li><a onclick="openBlankPopup('nonmovingitemReport')" href="#"><i class="fa fa-caret-right"></i> Non Moving Item Report</a></li>
		                                                        	<li><a onclick="openBlankPopup('stockreportReport?isfromcathlab=0')" href="#"><i class="fa fa-caret-right"></i> Stock Report</a></li>
		                                                        	<li><a onclick="openBlankPopup('userwisematerialissueReport')" href="#"><i class="fa fa-caret-right"></i> User Wise Material Issue Report</a></li>
		                                                        	<li><a onclick="openBlankPopup('itemwisepurchaseReport')" href="#"><i class="fa fa-caret-right"></i>Item Wise Purchase Report</a></li>
		                                                        	<li><a href="#" onclick="openBlankPopup('detailgrnreportProcurement?isfromcathlab=0')"><i class="fa fa-caret-right"></i>Details GRN Report</a></li>
		                                                        	<li><a onclick="openBlankPopup('adjustmentreportReport')" href="#"><i class="fa fa-caret-right"></i>Adjustment Report</a></li>
		                                                        	<li><a onclick="openBlankPopup('editgrnlogreportProduct')" href="#"><i class="fa fa-caret-right"></i>GRN Edit After Stock Transfer Report</a></li>
		                                                        	
		                                                        </ul>
                                            				</li>
                                            				<%} %>
                                                             <%-- <% if(loginInfo1.getUserType()==2 ||loginInfo1.isInventory_opening()){%>        <li><a onclick="openPopup('inventoryOpeningClosingProduct?isfromcathlab=0&ismonthlyreport=1')" href="#"><i class="fa fa-caret-right"></i> Inventory Opening Closing</a></li>
                                                	     	 <%} %>
                                                		     <%  if(loginInfo1.getUserType()==2 ||loginInfo1.isItemwise_stock()){%>    	<li><a onclick="openPopup('itemwisestockreportProduct')" href="#"><i class="fa fa-caret-right"></i>Item Wise Stock Report</a></li>
		                                                   	 <% } if(loginInfo1.getUserType()==2 ||loginInfo1.isPurchase_report()){%>    	<li><a onclick="openPopup('supplierledgerreportProduct')" href="#"><i class="fa fa-caret-right"></i>Purchase Report</a></li>
                                                		     <% } if(loginInfo1.getUserType()==2 ||loginInfo1.isGrn()){%>   	<li><a onclick="openPopup('productReceivedReportProduct?isfromcathlab=0')" href="#" ><i class="fa fa-caret-right"></i>GRN Report</a></li>
                                                	  		 <% } if(loginInfo1.getUserType()==2 ||loginInfo1.isIndent_statement()){%>  	  	<li><a onclick="openPopup('transferreportProduct')" href="#"><i class="fa fa-caret-right"></i>Indent Statement Report</a></li>
                                        					 <%} %>		 --%>		
                                        					
                                        					<!-- <li><a onclick="openPopup('stockvaluationReport')" href="#"><i class="fa fa-caret-right"></i>Stock Valuation Report</a></li>
                                                        	<li><a onclick="openPopup('departmaterialissueReport')" href="#"><i class="fa fa-caret-right"></i>Department Wise Material Issue Report</a></li>
                                                        	<li><a onclick="openPopup('nonmovingitemReport')" href="#"><i class="fa fa-caret-right"></i>Non Moving Item Report</a></li>
                                                        	<li><a onclick="openPopup('userwisematerialissueReport')" href="#"><i class="fa fa-caret-right"></i>User Wise Material Issue Report</a></li>
                                                        	<li><a onclick="openPopup('itemwisepurchaseReport')" href="#"><i class="fa fa-caret-right"></i>Item Wise Purchase Report</a></li>
                                                        	<li><a onclick="openPopup('newstockreport1Summary')" href="#"><i class="fa fa-caret-right"></i>New Stock Report</a></li>
                                                        	<li><a href="detailgrnreportProcurement?isfromcathlab=0"><i class="fa fa-caret-right"></i>Details GRN Report</a></li>
                                                        	 -->
                                                        </ul>
                                                    </li>
                                                    </ul>
                                                    </div>
                                                     <div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -18px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px">
                                             <a href="#" title="Cathlab Reports"><i class="fa"></i> Cathlab</a>
                                              <ul>
                                                        		<li><a href="patientconsumptionreportProduct?isfromcathlab=1"><i class="fa fa-caret-right"></i>Consumption Report</a></li>
                                                        	<li><a onclick="openPopup('stockreportReport?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i>Stock Report</a></li>
                                                        	<li><a href="detailgrnreportProcurement?isfromcathlab=1"><i class="fa fa-caret-right"></i>Details GRN Report</a></li>
                                                        	<li><a href="inventoryOpeningClosingProduct?isfromcathlab=1"><i class="fa fa-caret-right"></i>Cathlab Opening Closing</a></li>
                                                        </ul>
                                                      	</li>
                                                      	</ul>
                                                      	</div>
                                                      	<div class="col-lg-1 col-md-1 hidden-xs"style="margin-left: -26px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px">
                                             <a href="#" title="Patient Reports"><i class="fa fa-bar-chart-o"></i> Patient</a>
                                              <ul>
                                                	 <% if(loginInfo1.getUserType()==2 ||loginInfo1.isPatientlist()){%>        <li><a href="#" onclick="openPopup('clientListClientRpt')"><i class="fa fa-caret-right"></i>Patient List</a></li>
                                                           <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isCurrent_track_with_no_future_ampts()){%>    <li><a href="#" onclick="openPopup('currentTreatmentNoFutureApmtsClientRpt')"><i class="fa fa-caret-right"></i>Current Treat With No Future Apmts</a></li>
                                                            <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isNo_appointment_activity_record()){%>   <li><a href="#" onclick="openPopup('noApptActivityRecordClientRpt')"><i class="fa fa-caret-right"></i>No Appointment Activity Record</a></li>
                                                            <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isDna_with_no_future_appointment()){%>   <li><a href="#" onclick="openPopup('DNANoFutureAppClientRpt')"><i class="fa fa-caret-right"></i>DNA With No Future Appointment</a></li>
                                                            <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isNo_activity_record()){%>   <li><a href="#" onclick="openPopup('noActivityRecordClientRpt')"><i class="fa fa-caret-right"></i>No Activity Record</a></li>
                                                       <%} %>
                                                       <li><a href="#" onclick="openPopup('opdtatreportReport')"><i class="fa fa-caret-right"></i>OPD TAT Report</a></li>
                                                        <li><a href="#" onclick="openPopup('birthplacereportSummary')"><i class="fa fa-caret-right"></i>BirthPlace Report</a></li>
                                                	</ul>
                                   
                                              </li>
                                                	</ul>
                                                	</div>
                                         <div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -30px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px">
                                             <a href="#" title="IPD Reports"><i class="fa fa-bar-chart-o"></i> IPD</a>
                                              <ul>
                                              <li>
                                              <%if(loginInfo1.getUserType()==2 ||loginInfo1.isIpd_daily_report()){%>        <li><a href="#" onclick="openPopup('ipdSummary')"><i class="fa fa-caret-right"></i>IPD Daily Account Report</a></li>
                                             <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isIpd_monthly_report()){%>       <li><a href="#" onclick="openPopup('ipdmonthlyreportSummary')"><i class="fa fa-caret-right"></i>IPD Monthly Occupancy Report</a></li>
                                               <%}if(loginInfo1.getUserType()==2 ||loginInfo1.isIpd_daily_discharge()){%>       <li><a href="#" onclick="openPopup('ipddailyadddisSummary')"><i class="fa fa-caret-right"></i> IPD Daily Admission Discharge</a></li>
                                               <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isCurrent_patient_report()){%>      <li><a href="#" onclick="openPopup(' currentpatientsSummary')"><i class="fa fa-caret-right"></i>IPD Current Patient Report</a></li>
                                               <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isOutcome_discharge()){%>  <li><a href="#" onclick="openPopup('odreportSummary')"><i class="fa fa-caret-right"></i>Discharge Outcome Report</a></li>
                                               <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isDeathreport()){%>             <li><a href="#" onclick="openPopup(' deathreportSummary')"><i class="fa fa-caret-right"></i>Death Report</a></li>
                                               <%} if(loginInfo1.getUserType()==2 ||loginInfo1.isMlc()){ %>     <li><a href="#" onclick="openPopup('ismlcreportSummary')"><i class="fa fa-caret-right"></i>MLC Report</a></li>
                                                        
                                               <%} %>
                                               <%if(loginInfo1.getClinicUserid().equals("nelson")){ %>
                                                <li><a href="#" onclick="openPopup('totalrevenueSummary?isnelson=1&yearly=1')"><i class="fa fa-caret-right"></i>Nelson Total Revenue Report</a></li>
                                                  <li><a href="#" onclick="openPopup('deptwise_otSummary')"><i class="fa fa-caret-right"></i>Dept Wise OT Revenue Report</a></li>
                                                  	<li><a onclick="openPopup('departmentwiserevenueReport')" href="#"><i class="fa fa-caret-right"></i>Department Wise Revenue Report</a></li>
                                               <%} %>
                                              
                                               
                                                
                                                <li><a href="#" onclick="openPopup('totalrevenueSummary')"><i class="fa fa-caret-right"></i>Total Revenue Report</a></li>
                                                 <li><a href="#" onclick="openPopup('wardwiserevenuereptSummary')"><i class="fa fa-caret-right"></i>Ward Wise Revenue Report</a></li>
                                                <%if(loginInfo1.getIskunal()==1){ %>
                                                 <li><a href="#" onclick="openPopup('ipdpatienttpreportSummary')"><i class="fa fa-caret-right"></i>IPD THIRD PARTY Report</a></li>
                                                  <li><a href="#" onclick="openPopup('tppackagereportSummary')"><i class="fa fa-caret-right"></i> Applied Package Report</a></li>
												<%} %>                                              
												 <li><a href="#" onclick="openPopup('revenuematrixReport')"><i class="fa fa-caret-right"></i>Revenue Matrix</a></li>
												 <li><a href="#" onclick="openPopup('nosofadmdiscReport')"><i class="fa fa-caret-right"></i>No's of Admission & Discharge</a></li>
												 <li><a onclick="openPopup('departmentwiserevenueReport')" href="#"><i class="fa fa-caret-right"></i>Department Wise Revenue Report</a></li>
                                              </li>
                                              </ul>
                                              </li>
                                              </ul></div>
                                                	<div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -48px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px">
                                             <a href="#" title="Summary Reports"><i class="fa fa-bar-chart-o"></i> Summary</a>
                                              <ul>
                                                	<% if(loginInfo1.getUserType()==2 ||loginInfo1.isDna_analysiis()){%>         <li><a href="#" onclick="openPopup('Summary')"><i class="fa fa-caret-right"></i>DNA Analysis Report</a></li>
                                                                  <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isAppointment_kept_vs_dna()){%>     <li><a href="#" onclick="openPopup('appDNAKeptSummary')"><i class="fa fa-caret-right"></i>Appointment Kept vs DNA</a></li>
                                                               <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isTreatment_state_by_refferal()){%>        <li><a href="#" onclick="openPopup('treatmentReferralSummary')"><i class="fa fa-caret-right"></i>Treatment State By Referral</a></li>
                                            <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isReturning_patients()){%>    <li><a href="#" onclick="openPopup('returningptsSummary')"><i class="fa fa-caret-right"></i>Returning Patients</a></li>
                                                             <!-- <li><a href="#" onclick="openPopup('KPI')"><i class="fa fa-caret-right"></i> KPI Report </a></li> -->
                                                           
                                                         <%--  <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isBed_occupancy_report()){%> --%>             <li><a href="#" onclick="openPopup('bedoccupancyreportSummary')"><i class="fa fa-caret-right"></i>Bed Occupancy Report</a></li>
                                                                  <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isReffered_by()){%>     <li><a href="#" onclick="openPopup('refferedbyreportSummary')"><i class="fa fa-caret-right"></i>Reffered By Report</a></li>
                                                                <% } %>
                                                                    <li><a href="#" onclick="openPopup('showpatientsreportSummary')"><i class="fa fa-caret-right"></i>Dept report</a></li>
                                                                      <li><a href="#" onclick="openPopup('deptwiseavgcountSummary')"><i class="fa fa-caret-right"></i>Dept Wise Monthly Count report</a></li>
                                                                       <li><a href="#" onclick="openPopup('newIpdCurrentPatientSummary')"><i class="fa fa-caret-right"></i>Current IPD Patients</a></li>
                                                        
                                                           
                                                           <li class="dropdown submenu"><a href="#"><i class="fa fa-caret-right"></i>Daily Report<i class="fa fa-plus"></i></a>
                                                           
                                                                <ul>
                                                                   <% if(loginInfo1.getUserType()==2 ||loginInfo1.isReport_outstandng()){%>   <li><a href="#" onclick="openPopup('rptoutstandingDailyReport')"><i class="fa fa-caret-right"></i>Report Outstanding</a></li>
                                                                     <% } if(loginInfo1.getUserType()==2 ||loginInfo1.isNow_patients()){%>     <li><a href="#" onclick="openPopup('newptsDailyReport')"><i class="fa fa-caret-right"></i>New Patients</a></li>
                                                                        <% } if(loginInfo1.getUserType()==2 ||loginInfo1.isTotal_patients_seen()){%>     <li><a href="#" onclick="openPopup('totalptsseenDailyReport')"><i class="fa fa-caret-right"></i>Total Patients Seen</a></li>
                                                                         <% } if(loginInfo1.getUserType()==2 ||loginInfo1.isDna_outstanding_action()){%>    <li><a href="#" onclick="openPopup('dnaotsreportDailyReport')"><i class="fa fa-caret-right"></i>DNA Outstanding Action</a></li>
                                                              <%} %> 
                                                              
                                                               </ul></li>
                                                              
                                                            </li>
                                                            
                                                            </ul>
                                                	
                                                	</div>
                               <div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -16px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px">
                                             <a href="#" title="Clinical Reports"><i class="fa fa-bar-chart-o"></i> Clinical</a>
                                              <ul>
                                                	 <% if(loginInfo1.getUserType()==2 ||loginInfo1.isTreatment_episode_list()){%>  <li><a href="#" onclick="openPopup('treatmentEpisodeListClinical')"><i class="fa fa-caret-right"></i>Treatment Episode List</a></li>
                                                         <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isPatient_condition_list()){%>   <li><a href="#" onclick="openPopup('Conditionreport')"><i class="fa fa-caret-right"></i>Patient Condition List</a></li>
                                                            <% }if(loginInfo1.getUserType()==2 ||loginInfo1.isDtr_report()){%>    <li><a href="#" onclick="openPopup('DtrReportFormClinical')"><i class="fa fa-caret-right"></i>DTR Report</a></li>
                                                      <%} %> 
                                             </ul>
                                   
                                             </li>
                                            </ul>
                                       </div>
                                       <div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -29px;">
										<ul id="navigation">
                                            <li class="lisetroprt  dropdown" style="padding-top: 4px">
                                             <a href="#" title="OT Reports"><i class="fa fa-bar-chart-o"></i> OT</a>
                                              <ul>
                                                	<li><a href="#" onclick="openPopup('otreportReport')"><i class="fa fa-caret-right"></i>OT Report</a></li>
                                                      
                                             </ul>
                                   
                                             </li>
                                            </ul>
                                       </div>
                                         <div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: -51px;">
										<ul id="navigation">
										<li class="lisetroprt"style="padding-top: 4px">
                       
                                               <% if(loginInfo1.getUserType()==2 ||loginInfo1.isKpi_dashboard()){%>   
                                                
                                              <a href="#" onclick="openPopup('kpidashboardKPI')" style="width: 96px" title="KPI Dashboard ">KPI Dashboard </a>
                                                	</li>
                                                	 <%} %> 
                                                	</ul>
                                                	</div>
                                                
                                <div class="col-lg-1 col-md-1 hidden-xs" style="margin-left: 28px;">
										<ul id="navigation" >
                                            <li class="lisetroprt  dropdown" style="width: 262px;padding-top: 4px">
                                            <a href="#" title="" style="color: #424a5d!important;"><i class="fa"></i>.</a>
                                            </li></ul>
                                            </div>
								</div>
										</div>
</body>
</html>