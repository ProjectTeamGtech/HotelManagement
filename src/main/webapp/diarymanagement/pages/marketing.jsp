
<%@page import="com.apm.DiaryManagement.web.form.DiaryManagementForm"%>
<%@page import="com.apm.Report.eu.entity.MisReport"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>


<!doctype html>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Mis.web.form.MisChartForm"%>
<%@page import="com.apm.Expence.eu.entity.Expence"%>

<% LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
<%
    String ipdper=(String)session.getAttribute("ipdper");
	String opdper=(String)session.getAttribute("opdper");
	String otper=(String)session.getAttribute("otper");
	
	Client client1=(Client)session.getAttribute("allgraph");
	
	if(ipdper==null){
		ipdper="0";
	}
	if(otper==null){
		otper="0";
	}
	if(opdper==null){
		opdper="0";
	}
	if(client1==null){
		client1=new Client();
	}
	
	String graphName = (String)session.getAttribute("graphName"); 

	DiaryManagementForm misChartForm = (DiaryManagementForm)session.getAttribute("misChartForm");
	String action = (String)session.getAttribute("graphaction");
	
%>


<html class="no-js" lang=""> 

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>YUVICARE</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">

 		

        <!-- ============================================
        ================= Stylesheets ===================
        ============================================= -->
        <!-- vendor css files -->
        <link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrap.min.css">
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
         <script type="text/javascript" src="diarymanagement/js/marketing.js"></script>
        <SCRIPT type="text/javascript">
        
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
         function printExpenseExcel() {

          $(".xlxexpensetable").table2excel({
					exclude: ".noExl",
					name: "Expense Report List",
					filename: "expenseReport",
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
        </SCRIPT>
<SCRIPT type="text/javascript" >
       $(document).ready(function() {
			$("#dobdate").datepicker({
				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true
			});
		});
       $(document).ready(function() {
			$("#medafterdate").datepicker({
				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true
			});
		});
       $(document).ready(function() {
			$("#fromDatenew").datepicker({
				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true
			});
		});
       $(document).ready(function() {
			$("#toDatenew").datepicker({
				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true
			});
		});
</SCRIPT>

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
    background-color: #339966 !important;
}

    .mainheader {
    background-color: #339966 !important;
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
    min-height: 29px;
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
<style>

.checkbox {
    position: relative;
    display: block;
    min-height: 13px;
    margin-top: 0px;
    margin-bottom: 0px;
}
.bgreen{
	    background-color: rgba(22, 160, 133, 0.12);
    padding: 10px 15px 15px 15px;
    min-height: 443px;
}
.list-group.no-border .list-group-item {
    border-width: 1px 0;
    border-left: 3px solid;
}
.list-group-item {
    position: relative;
    display: block;
    padding: 10px 11px;
    margin-bottom: -1px;
    background-color: #fff;
    border: 1px solid #ddd;
}
.rightborder{
	    
	    min-height: 450px;
}
.dateclass{
	    width: 10%;
    height: 19px;
    margin-left: 7px;
    background-color: rgba(245, 245, 245, 0.71) !important;
    height: 21px;
    margin-top: 4px;
}
.selectclass{
	width: 15%;
    height: 21px;
    margin-left: 7px;
    background-color: rgba(245, 245, 245, 0.71) !important;
    padding: 0px;
    margin-top: 4px;
}
.liclasse{
    display: inline-flex;
    width: 100%;
    border-bottom: 1px solid rgba(221, 221, 221, 0.22);
    line-height: 28px;
}
.setback5{
	    
    min-height: 450px;
        padding-top: 10px;
}
.checkbox-custom-alt > i {
    width: 16px;
    height: 16px;
    background-color: transparent;
    border: 2px solid #dfdfdf;
    margin-right: 0px;
    margin-left: -18px;
}
.checkbox-custom-alt input:checked + i:before {
    top: 1px;
    left: 1px;
    width: auto;
    height: auto;
    background-color: transparent;
    opacity: 1;
}
.bg-lightred {
    background-color: #e05d6f !important;
    color: white !important;
}
.title {
    display: inline;
    float: left;
    margin-left: 0px;
    color: black;
    font-size: 12px;
}
.setresult{
	    text-align: right;
    /* margin-top: -21px; */
    background-color: whitesmoke;
    padding: 4px 10px 2px 0px;
    border-top: 1px solid #ddd;
    color: green;
}
.floatrightdate{
	float: right;
    display: inline-flex;
    padding: 7px 5px 0px 0px;
}
.form-control {
    background-color: #fff;
}
.setshowbtn{
	width: 100%;
    font-size: 18px;
    height: 39px !important;
    line-height: 29px;
    margin-top: 3px;
}
.martomi {
    margin-top: -7px !important;
}
.titlepadright{
	margin-top: 3px;
    padding-right: 8px;
}
.maintite {
    border-bottom: 1px solid #ddd;
    line-height: 22px;
    color: brown;
}
</style>
    </head>

    <body class="appWrapper  hz-menu">
        <div id="wrap" class="animsition">
        					<div class="row details mainheader">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<img src="manasmaindashboard/images/CRM_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
										 	<h4>Marketing Dashboard</h4>
										</div>
										<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
											<img src="dashboardicon/marketing.png" class="img-responsive prescripiconcircle">
											<img src="manasmaindashboard/images/CRM_Icon.svg" style="filter: brightness(5);width: 21%;margin-top: 8px;margin-left: 7px;">
										</div>
										<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
											<h4>Marketing</h4>
										</div> -->
									</div>
									<div class="col-lg-12 col-md-12 hidden paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										
										<div class="col-lg-9 col-md-9 hidden">
										<s:form class="form-inline" action="MisChart"  theme="simple" style="display:inline-flex;">
										
										     
										     <s:hidden name="graphName" id="graphName"></s:hidden> 
											  <%if(loginInfo.getJobTitle().equals("Admin")){%>
		                                  			<div class="form-group setdorp">
			                                  			<s:select name="invoicecategory" id="invoicecategory"  
														list="#{'2':'All','0':'Primary','1':'Secondary' }"
														cssClass="form-control"></s:select>
		                                  			</div>
		                                  		<%} %>
											  <div class="form-group setdorp">
											     <s:textfield readonly="true" cssClass="form-control" theme="simple"></s:textfield>
											  </div>
											   <div class="form-group totext">
											     To
											  </div>
											  <div class="form-group setdorp">
											    <s:textfield readonly="true"  cssClass="form-control" theme="simple"></s:textfield>
											  </div>
											  <div class="form-group setdorp">
											    <%-- <select class="form-control">
											    	<option value="Select all">Select all</option>
											    	<option value="IPD">IPD</option>
											    	<option value="OPD">OPD</option>
											    </select> --%>
											    <s:select headerKey="0" headerValue="Select All" cssClass="form-control" list="#{'0':'Select All','IPD':'IPD', 'OPD':'OPD'}" name="filter_ward" />
											  </div>
											  <s:if test="isKPI==1">
											  <div class="form-group setdorp">
													<s:select headerKey="0" headerValue="Select KPI Area" cssClass="form-control" list="kpiarealist" listKey="id" listValue="name" name="kpiarea_filter" />
				                   				</div>
				                   				<div class="form-group setdorp">
													 <s:select cssClass="form-control" list="#{'2015-2016':'2015-2016', '2016-2017':'2016-2017', '2017-2018':'2017-2018', '2018-2019':'2018-2019', '2019-2020':'2019-2020'}" id="year_filter" name="year_filter" />
				                   				</div>
				                   				<div class="form-group setdorp">
				                   					
													<s:select cssClass="form-control" list="#{'01':'January', '02':'February', '03':'March', '04':'April', '05':'May','06':'June', '07':'July', '08':'August', '09':'September', '10':'October', '11':'November', '12':'December'}" id="month_filter" name="month_filter" />
				                   				</div>
				                   			</s:if>
											  <s:if test="pharmacy_location==1">
											  <div class="form-group setdorp">
											    <s:select headerKey="0" headerValue="Select All" cssClass="form-control" list="locationListPharmacy" listKey="id" listValue="name" name="filter_location" />
											  </div>
											  </s:if>
											 <s:submit value="Go" theme="simple" cssClass="btn btn-primary marrigh10se"></s:submit>
											  <a href="#" onclick="goreferesh()" class="btn btn-primary marrigh10se" title="Refresh"><i class="fa fa-refresh"></i></a>
											</s:form>
										</div>
										
										</div>
										</div>
								</div>
								
								
								<div role="tabpanel" style="padding: 0px !important;">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
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
                        
                        
                        

                        <!-- Tab panes -->
                        <div class="tab-content">
                        
                        	<s:if test="mainaction=='marketingdashboard'">
                        		<div role="tabpanel" class="tab-pane active" id="dashboard" style="padding: 0px !important;">
                        	</s:if>
                        	<s:else>
                        		<div role="tabpanel" class="tab-pane" id="dashboard" style="padding: 0px !important;">
                        	</s:else>
                        
                        	<div class="tile-body">
                        	<s:form  action="marketingDiaryMangent"  theme="simple" >
                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px;background-color: rgba(239, 239, 239, 0.35);">
                        	<div class="col-lg-1 col-md-1" style="padding: 0px;">
                        			<span style="color: rgba(0, 0, 0, 0.5);">Date Filter:</span>
                        	</div>
                        	<div class="col-lg-1 col-md-1" style="padding: 0px;">
                        		<div class="form-group" style="margin-bottom: 5px;">
                            			<s:textfield readonly="true" cssClass="form-control" theme="simple" name="fromDate" id="fromDate"></s:textfield>
                            		</div>
                        	</div>
                        	<div class="col-lg-1 col-md-1" style="padding-right: 0px;">
                        		<div class="from-group" style="margin-bottom: 5px;">
                        			 <s:textfield readonly="true"  cssClass="form-control" theme="simple" name="toDate" id="toDate"></s:textfield>
	                        		</div>
                        	</div>
                        	<div class="col-lg-1 col-md-1">
                        		<div class="from-group" style="margin-bottom: 5px;">
	                        			<!-- <a href="#" class="btn btn-primary">Go</a> -->
	                        			<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
	                        		</div>
                        	</div>
                            	
                            </div>
                            </s:form>
                        	
                              <div id="" class="">
            <section>
                <div class="page page-charts martomi">
                  	<div class="row">
	
	
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bhoechie-tab-container">
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 bhoechie-tab-menu" >
            <div class="setscrollleft">
            	<div class="list-group" style="height:auto;">
                 <a href="#"  id="paymentmode" class="list-group-item text-center bgste" onclick="dosubmit('paymentmode')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Payment Mode
                </a>
                 <a href="#"  id="patientview" class="list-group-item text-center" onclick="dosubmit('patientview')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Client View
                </a>
                 <a href="#"  id="patientviewpackage" class="list-group-item text-center bgste" onclick="dosubmit('patientviewpackage')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Client View By Package
                </a>
              </div>
            </div>
              
            </div>
            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 bhoechie-tab">
            <div class="bhoechie-tab-content" id="paymentmodet">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                     <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/payments.png" class="img-responsive" />
                                                    </div>
                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total: <%=Constants.getCurrency(loginInfo)%> <s:property value="totalPayment"/> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div5">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                                <tr>
                                                                    <td>1) By Cash</td>
                                                                    <td><%=Constants.getCurrency(loginInfo)%> <s:property value="cashpayment"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>2) By Cheques</td>
                                                                   <td><%=Constants.getCurrency(loginInfo)%> <s:property value="chequepayment"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>3) By Credit/Debit cards</td>
                                                                   <td><%=Constants.getCurrency(loginInfo)%> <s:property value="cardPayment"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>4) By Other</td>
                                                                   <td><%=Constants.getCurrency(loginInfo)%> <s:property value="otherPayment"/></td>
                                                                </tr>
                                    
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="paymentmode2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
									  
								  <div id="paymentmodeg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
             	 <div class="bhoechie-tab-content" id="patientviewt">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                      <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/map.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total: <s:property value="totalPatient"/> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div9">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                            	<s:iterator value="patientbylocation">
                                                                <tr>
                                                                    <td><s:property value="town"/></td>
                                                                    <td><s:property value="id"/></td>
                                                                </tr>
                                                                </s:iterator>
                                                               
                                                                <tr class="hidden">
                                                                    <td><B>Count of Total OPD Patient</B></td>
                                                                    <td><s:property value="totalOpdPatient"/></td>
                                                                </tr>


                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="patientview2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
          <div id="patientviewg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
										                    <%for(Client client : misChartForm.getPatientbylocation()){%>
										            
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
										                    <%for(Client client : misChartForm.getPatientbylocation()){%>
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
               <div class="bhoechie-tab-content" id="patientviewpackaget">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                      <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/Healthcheck.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total: <s:property value="totalPatient"/> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div9">
                                                        <table class="table table-no-border m-0">
                                                        	<thead>
                                                        		<tr>
                                                        			<th style="color: #fff !important;">Company name</th>
                                                        			<th style="color: #fff !important;">Patient</th>
                                                        			<th style="color: #fff !important;text-align: right;">Payment</th>
                                                        		</tr>
                                                        	</thead>
                                                            <tbody>
                                                            	<s:iterator value="patientviewbypackage">
                                                                 <tr class="">
                                                                    <td><s:property value="packagename"/></td>
                                                                    <td><s:property value="patientno"/></td>
                                                                    <td style="text-align: right;">Rs.<s:property value="totalpayment"/></td>
                                                                </tr>
                                                                
                                                                </s:iterator>
                                                               
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="patientviewpackage2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
          <div id="patientviewpackageg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
											            data: [<%for(Accounts accClient : misChartForm.getPatientviewbypackage()){%>
											            
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
            </section>
            
            </div>     
                            </div>
                         </div>
                        
                        	<s:if test="mainaction=='marketing_connect'">
                        		<div role="tabpanel" class="tab-pane active" id="email" style="padding: 0px !important;">
                        	</s:if>
                        	<s:else>
                        		<div role="tabpanel" class="tab-pane" id="email" style="padding: 0px !important;">
                        	</s:else>
                        	
                            
                               <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									<div class="">
									
									<div class="col-lg-2 col-md-2 rightborder hidden" style="padding-right:0px;">
										<div class="setovehe">
									  	<table class="table table-hover table-bordered"> 
									  	<thead> 
									  		<tr> 
									  			<th>Department</th> 
									  		</tr> 
									  	</thead>
									  		<tbody class="depart1"> 
									  			<tr> 
									  				<th>
									  					<label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all1"><i></i> All</label>
									  				</th> 
									  			</tr> 
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i> Accounts</label>
									  				</th>
									  				
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i> Administration</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i> Anaesthesia</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox"><i></i> Assistant Surgeon</label>
									  				</th>
									  			</tr>
									  			
												
									  			
									  		</tbody> 
									  	</table>
									  	</div>
									</div>
									<div class="col-lg-2 col-md-2 rightborder hidden" style="padding:0px;">
										<div class="setovehe">
									  	<table class="table table-hover table-bordered"> 
									  	<thead> 
									  		<tr> 
									  			<th>Designation</th> 
									  		</tr> 
									  	</thead>
									  		<tbody class="desig1"> 
									  			<tr> 
									  				<th>
									  					<label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all2"><i></i> All</label>
									  				</th> 
									  			</tr> 
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select2"><input type="checkbox"><i></i> Accountant</label>
									  				</th>
									  				
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select2"><input type="checkbox"><i></i> Accountant Assistant</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select2"><input type="checkbox"><i></i> Admin Assistant</label>
									  				</th>
									  			</tr>
									  			
												
									  			
									  		</tbody> 
									  	</table>
									  	</div>
									</div>
									<div class="col-lg-8 col-md-8 rightborder" style="padding:0px;">
									
									<div class="form-inline">
									  <div class="form-group">
									  	<label class="checkbox checkbox-custom-alt"><input type="radio" class="rclass" name="msgsendtype" onclick="changesendtype(this.value)" value="sms" id="smstype" checked><i></i> SMS</label> 
									  	<label class="checkbox checkbox-custom-alt"><input type="radio" class="rclass" name="msgsendtype" onclick="changesendtype(this.value)" value="email" id="emailtype"><i></i> Email</label>
									  	<span><a href="#" data-toggle="modal" data-target="#ireq">+Add</a></span>
									  </div>
									</div>
									
									
<div id="ireq" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Unit</h4>
        
      </div>
      
      
      
<div class="modal-body">
      
  <div class="row">
	
	   <div class="col-lg-6 col-md-8">
		 <div class="panel panel-primary">
			<div class="panel-body">
			
			    <label>SMTP</label><label class="text-danger">*</label>
				<s:textfield id="smtp" name="smtp"
				     
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter SMTP " placeholder="Enter SMTP"/>
					
					
			</div>
			
			<div class="panel-body">
			
			    <label>Email</label><label class="text-danger">*</label>
				<s:textfield id="smtpemail" name="smtpemail"
				     
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Email " placeholder="Enter Email"/>
					
					
			</div>
			
			<div class="panel-body">
			
			    <label>Password</label><label class="text-danger">*</label>
				<s:textfield id="password" name="password"
				     
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Password " placeholder="Enter Password"/>
					
					
			</div>
		</div>
	</div>
	
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
	
		<div class="col-lg-5 col-md-4"></div>
		
		<div class="col-lg-6 col-md-8">
			
		</div>
		<div class="col-lg-3 col-md-2"></div>
		
	</div>
	
	<s:token></s:token>
	
</div>

                       <%-- <div class="modal-footer">
                                 <s:submit cssClass="btn btn-primary" value="Save"/>  --%>
        
            <div class="modal-footer">
                     <button type="button" class="btn btn-primary"
							   onclick="updatesmtp()" style="margin-top: 30px;">Save</button>
		    </div>
         
		
        </div>
     </div>
 </div>
									<div class="Col-lg-12 col-md-12 col-xs-12 col-sm-12 setrules" style="padding: 0px;padding-top: 10px;">
										<span style="color: rgba(0, 0, 0, 0.5);">Marketing Filter:</span>
										<ul style="list-style: none;padding: 0px;">
											<li class="liclasse "><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="15" id="type_15"><i></i></label> List of employee</li>
											<li class="liclasse "><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="16" id="type_16"><i></i></label> List of Client<s:select list="ClientTypeList" listKey="clientType" name="clientType" listValue="clientType" headerKey="0" headerValue="Select" id="clientTypeid" cssClass="form-control selectclass"></s:select> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="1" id="type_11"><i></i></label> List Client or Employee Having DOB <s:textfield id="dobdate" readonly="true" cssClass="form-control dateclass" placeholder="Date"/> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="2" id="type_12"><i></i>List of Client Relatives Where OPD Notes <b>Date</b> or IPD Discharge <b>Date</b> is before</label> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="3" id="type_13"><i></i></label> List of Client from IPD Discharge who has been prescribed <s:select cssClass="form-control selectclass" name="mdicinename" id="mdicinename" list="medicineList" listKey="id" listValue="genericname" headerKey="0" headerValue="Select Product"></s:select>&nbsp; after date <s:textfield id="medafterdate" cssClass="form-control dateclass" placeholder="Date"/>&nbsp; price is more than </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="4" id="type_14"><i></i> </label> List of Client where Diagnosis CKD and Investigation Type <s:select list="investigationamelist" listKey="name" listValue="name" headerKey="0" headerValue="Select" id="invtname" cssClass="form-control selectclass"></s:select> &nbsp;with value <input type="number" id="greaterthanid" class="form-control dateclass" placeholder=">">&nbsp; or <input type="number" id="lessthanid" class="form-control dateclass" placeholder="<"> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="5" id="type_15"><i></i></label>List of Client where TP <s:select list="thirdPartyList" listKey="id" listValue="thirdParty" headerKey="0" headerValue="Select" id="thirdpartynameid" cssClass="form-control selectclass"></s:select> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="6" id="type_16"><i></i> List of Client with Policy is Expiring by <b>Date</b></label> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="7" id="type_17"><i></i> List of Client who has been discharge from hospital by <b>Date</b></label> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="8" id="type_18"><i></i> List of Client with followup Appointment to be schedule by <b>Date</b></label> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="9" id="type_19"><i></i> List of Client Account balance is more then <b>Rs 1111</b></label> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="10" id="type_20"><i></i></label>List of Client whos profile is missing with <select class="form-control selectclass" id="mobileoremail"><option value="0">Select</option><option value="1">MobileNo</option><option value="2">EmailAddress</option><option value="3">Both</option></select></li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="11" id="type_21"><i></i> </label> List of Client with Reference <s:select list="refrenceList" listKey="reference" listValue="reference" headerKey="0" headerValue="Select" id="refferedby" cssClass="form-control selectclass"></s:select></li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="12" id="type_22"><i></i> List of Client where package <b>XXX</b></label> </li>
											<li class="liclasse  hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="13" id="type_23"><i></i> List of Client where department <b>GGG</b></label> </li>
											<li class="liclasse hidden"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="14" id="type_24"><i></i></label> List of employee with role <s:select list="jobGroupList" listKey="jobTitle" listValue="jobTitle" headerKey="0" headerValue="Select" id="jobtitleid" cssClass="form-control selectclass"></s:select> or Department <s:select list="disciplineList" listKey="id" listValue="discipline" headerKey="0" headerValue="Select" id="specialityid" cssClass="form-control selectclass"></s:select> </li>
											<!--
											<li class="liclasse"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="15" id="type_25"><i></i></label>List Patient with Follow up advised in last <b>xxx<b> days with no future appointment</li>
											<li class="liclasse"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="16" id="type_26"><i></i></label> List patient who has been prescribed high value medication in last 30 date and price is more than <b>xxx<b></li>
											<li class="liclasse"><label class="checkbox checkbox-custom-alt"><input type="radio" name="type" class="akashdclass" value="17" id="type_27"><i></i></label> List patient with dna count days  </li>
											  -->
										</ul>
									
									</div>
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: rgba(239, 239, 239, 0.46);padding: 5px 10px 3px 10px;box-shadow: 0px 2px 9px #222;">
										<div class="form-inline">
											<div class="form-group hidden" style="float: left;margin-bottom:0px;display: inline-flex;">
												<div class="" style="padding-left:0px;padding-right:0px;">
												 	<label for="exampleInputFile" style="margin-bottom: 0px;">Import Contact</label>
											    	<input type="file" name="userFile" value="">
											    	<!-- <p class="help-block">File should be in .XLS, .CSV</p> -->
												</div>
											
												<div class="" style="padding-left:0px;padding-right:0px;">
													<div class="form-group">
														<button type="submit" class="btn btn-primary" style="margin-top: 19px;">Import</button>
													</div>
												</div>
											</div>
											
											<div class="form-group hidden" style="float: right;margin-bottom:-27px;">
												<a href="#" class="btn btn-primary setshowbtn" onclick="showAllContact()">Show</a>
										  	</div>
										</div>
										<div class="form-group" >
												<a href="#" class="btn btn-primary setshowbtn" onclick="showAllContact()">Show</a>
										 </div>
									</div>
									
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
									<div class="stableset">
									  
									  	<table class="table table-hover table-bordered"> 
									  	<thead> 
									  		<tr> 
									  			<th style="width:30%;"><label class='checkbox checkbox-custom-alt m-0 mt-5'><input type='checkbox' onclick='selectAllMarketingChecked(this)' id='select-all3'><i></i> Name</label></th> 
									  			<th style="width:15%;">Mobile</th> 
									  			<th style="width:25%;">Email</th> 
									  			<th style="width:25%;">Address</th> 
									  			<th style="width:20%;">Client Type</th>
									  			
									  		</tr> 
									  	</thead>
									  		<tbody id="tbodyallname"> 
									  			<!-- <tr> 
									  				<th>
									  					<label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all3"><i></i> All</label>
									  				</th> 
									  			</tr> 
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3"><input type="checkbox"><i></i> Mr.Abhinav Parmar</label>
									  				</th>
									  			</tr>
									  			<tr>
									  				<th>
									  					<label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select3"><input type="checkbox"><i></i> Mr.Jitendra Binzade</label>
									  				</th>
									  			</tr> -->
									  			
									  		</tbody> 
									  	</table>
									  	
									  </div>
									</div>
									
									
									
									<div class="col-lg-12 col-md-12 col-xs-12 hidden" style="padding:0px;margin-top:15px;">
										<div class="form-group">
									    <label for="exampleInputEmail1">Selected Emails</label>
									    <textarea name="numbers" cols="" rows="3" class="form-control"></textarea>
									  </div>
									</div>
									
									  
									  
									</div>
									<div class="col-lg-4 col-md-4 setback5">
									
									<div class="form-inline hidden">
										<div class="form-group">
											<s:select list="#{'Employee List':'Employee List', 'Patient List':'Patient List'}" onchange="selectMainList(this)" name="selectmainlist" id="selectmainlist" cssClass="form-control showToolTip" theme="simple" headerKey="0" headerValue="Select List" cssStyle="width: 100%;"></s:select>
										</div>
									</div>
									  
									
									<div class="form-group" style="width: 15%;">
	                            		
	                            	</div>
									
									
									 <div class="form-group" style="margin-top: 15px;">
									   <%--  <select name="smsTemplate" class="form-control">
									    	<option value="1">Select Template</option>
										    <option value="2">Birthday Wishes</option>
										    <option value="3">Reminder for Referral</option>
										    <option value="4">Advance reminder</option>
										    <option value="8">Happy Diwali</option>
										</select> --%>
										<s:select list="smstemlatelist" listKey="id" onchange="selectSmSTemplate(this.value)" listValue="name" theme="simple" headerKey="0" headerValue="Select Template" id="smstemplate" cssClass="form-control"></s:select>
									  </div>
									  <div class="form-group" style="margin-top: 15px;">
									    <input type="text" name="template" value="" class="form-control" id="smtemplatename" placeholder="Enter Template Name">
									  </div>
									   <div class="form-group hidden" id="mobilenohiddiv" style="margin-top: 15px;">
             								<input type="text" name="allmobnos" id="allmobnos" class="form-control" placeholder="Mobile Number">
           								</div>
           								<div class="form-group hidden" id="emailidhiddiv" style="margin-top: 15px;">
             								<input type="text" name="allemailids" id="allemailids" class="form-control" placeholder="Email Id">
           								</div>
									  <div class="form-group hidden" id="subjecthiddendiv" style="margin-top: 15px;">
									    <input type="text" name="subject" id="subject" class="form-control" placeholder="Enter Subject">
									  </div>
									  <div class="form-group" style="margin-top: 15px;">
									  	<textarea name="smstxt" cols="" id="smstxt" rows="10" class="form-control"></textarea>
									  </div>
									  <button type="button" onclick="sendMessageToAll()" class="btn btn-primary pull-right" style="height: 45px !important;font-size: 16px;">Send</button>
									  <button type="button" onclick="sendMsgAndTemplate()" class="btn btn-primary pull-right" style="height: 45px !important;font-size: 16px;margin-right: 15px;">Save & Send</button>
									</div>
									</div>
									</div>
                            </div>

                            <s:if test="mainaction=='marketing_history'">
                            	<div role="tabpanel" class="tab-pane active" id="history" style="padding:0px;">
                        	</s:if>
                        	<s:else>
                        		<div role="tabpanel" class="tab-pane" id="history" style="padding:0px;">
                        	</s:else>
                            
                            
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px;background-color: rgba(239, 239, 239, 0.35);">
                            	
                            		<%-- <div class="form-group">
                            			<select name="smsTemplate" class="form-control">
										    <option value="2">Employee List</option>
										    <option value="3">Patient List</option>
										</select>
                            		</div> --%>
                            		<s:form  action="marketingDiaryMangent"  theme="simple" >
                            			<s:hidden name="action" id="action"></s:hidden>
                            			<s:hidden name="mainaction" value="marketing_history"></s:hidden>
                        			<div class="form-inline">
	                            		<div class="form-group">
	                            			<span style="color: rgba(0, 0, 0, 0.5);">History Filter:</span>
	                            		</div>
	                            		<div class="form-group">
	                            			<%-- <select name="history_typefilter" class="form-control">
											    <option value="email">Email</option>
											    <option value="sms">SMS</option>
											</select> --%>
											<s:select list="#{'email':'Email','sms':'SMS'}" name="history_typefilter" cssClass="form-control" ></s:select>
	                            		</div>
	                            		<div class="form-group" >
	                            			<s:textfield readonly="true" cssClass="form-control" cssStyle="width:82px;" placeholder="From Date" id="fromDatenew" name="fromDate"></s:textfield>
	                            		</div>
	                            		<div class="form-group" >
	                            			<s:textfield readonly="true" cssClass="form-control" cssStyle="width:82px;" placeholder="To Date" id="toDatenew" name="toDate"></s:textfield>
	                            		</div>
	                            		<div class="form-group" >
	                            			<!-- <a href="#" class="btn btn-primary">Go</a> -->
	                            			<input type="submit" class="btn btn-primary" value="Go">
	                            		</div>
                            		</div>
                            		</s:form>
                            	
                            </div>
                            
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
                            	<table class="table table-repsonive">
                            	<thead>
                            		<tr>
                            			<th>Sr.No</th>
                            			<th>Date / Time</th>
                            			<th>Mode</th>
                            			<th>Message</th>
                            			<th>To</th>
                            		</tr>
                            	</thead>
                            		<tbody>
                            		<%int x=1; %>
                            			<s:iterator value="marketinghistorylist">
                            			<tr>
                            				<td><%=x%></td>
                            				<td><s:property value="date"/></td>
                            				<td>
                            					<s:if test="sendtype=='sms'">
                            						<span class="label bg-lightred">SMS</span>
                            					</s:if>
                            					<s:else>
                            						<span class="label bg-lightred">Email</span>
                            					</s:else>
                            					
                            				</td>
                            				<td><s:property value="message"/></td>
                            				<td>
                            					<s:if test="sendtype=='sms'">
                            						<s:property value="allmobno"/>
                            					</s:if>
                            					<s:else>
                            						<s:property value="allmailid"/>
                            					</s:else>
                            				</td>
                            				<%x++; %>
                            			</tr>
                            			</s:iterator>
                            		</tbody>
                            	</table>
                            </div>
                            
                            
                            <div class="tbox tbox-sm hidden">
                        <!-- left side -->
                       


                        <!-- right side -->
                        <div class="tcol">
                            <!-- right side header -->
                            <div class="p-15 bg-white b-b">

                               
                                <div class="btn-toolbar">
                                    <div class="btn-group mr-10">
                                        <label class="checkbox checkbox-custom-alt m-0 mt-5"><input type="checkbox" id="select-all"><i></i> Select All</label>
                                    </div>
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-sm br-2"><i class="fa fa-refresh"></i></button>
                                    </div>
                                    <div class="btn-group">
                                        <button class="btn btn-default btn-sm br-2"><i class="fa fa-trash"></i></button>
                                    </div>
                                   
                                </div>

                            </div>
                            <!-- /right side header -->







                            <!-- right side body -->
                            <div>

                                <!-- mails -->
                                <ul class="list-group no-radius no-border" id="mails-list">

                                    <!-- mail in mails -->
                                    <li class="list-group-item b-primary">

                                        <div class="media">
                                            <div class="pull-left">
                                                <div class="controls">
                                                    <label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i></label>
                                                </div>
                                            </div>
                                            <div class="media-body">
                                                <div class="media-heading m-0">
                                                    <a href="#" class="mr-5">Eu deserunt cillum</a>
                                                    <span class="label bg-lightred">SMS</span>
                                                    <span class="pull-right text-sm text-muted">
                                                      <span class="hidden-xs">16:54, 24.11.2014</span>
                                                      <i class="fa fa-paperclip ml-5"></i>
                                                    </span>
                                                </div>
                                                <small>Commodo sunt minim incididunt ipsum dolore veniam nulla Lorem reprehenderit officia. Ut esse cillum quis deserunt.</small>
                                            </div>
                                        </div>

                                    </li>

                                    <!-- mail in mails -->
                                    <li class="list-group-item b-default">

                                        <div class="media">
                                            <div class="pull-left">
                                                  <div class="controls">
                                                    <label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i></label>
                                                </div>
                                            </div>
                                            <div class="media-body">
                                                <div class="media-heading m-0">
                                                    <a href="#" class="mr-5">Et est aliqua</a>
                                                    <span class="label bg-lightred">Email</span>
                                                    <span class="pull-right text-sm text-muted">
                                                      <span class="hidden-xs">15:29, 24.11.2014</span>
                                                    </span>
                                                </div>
                                                <small>Sit nulla sint ex exercitation culpa anim aliquip. Incididunt magna magna aute adipisicing incididunt ullamco sint velit quis irure cillum.</small>
                                            </div>
                                        </div>

                                    </li>

                                    <!-- mail in mails -->
                                    <li class="list-group-item b-greensea">

                                        <div class="media">
                                            <div class="pull-left">
                                                  <div class="controls">
                                                    <label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i></label>
                                                </div>
                                            </div>
                                            <div class="media-body">
                                                <div class="media-heading m-0">
                                                    <a href="#" class="mr-5">Fugiat irure id</a>
                                                    <span class="label bg-lightred">SMS</span>
                                                    <span class="pull-right text-sm text-muted">
                                                      <span class="hidden-xs">14:18, 24.11.2014</span>
                                                    </span>
                                                </div>
                                                <small>Cillum pariatur ex ut Lorem deserunt laborum sunt duis. Et minim exercitation consequat ex sint dolore.</small>
                                            </div>
                                        </div>

                                    </li>

                                    <!-- mail in mails -->
                                    <li class="list-group-item b-orange">

                                        <div class="media">
                                            <div class="pull-left">
                                                 <div class="controls">
                                                    <label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i></label>
                                                </div>
                                            </div>
                                            <div class="media-body">
                                                <div class="media-heading m-0">
                                                    <a href="#" class="mr-5">Ea velit labore</a>
                                                    <span class="label bg-lightred">SMS</span>
                                                    <span class="pull-right text-sm text-muted">
                                                      <span class="hidden-xs">14:05, 24.11.2014</span>
                                                      <i class="fa fa-paperclip ml-5"></i>
                                                    </span>
                                                </div>
                                                <small>Nulla velit commodo ea elit aute fugiat. Laboris id tempor cupidatat ullamco irure labore ad consectetur elit laborum laborum eiusmod fugiat.</small>
                                            </div>
                                        </div>

                                    </li>

                                    <!-- mail in mails -->
                                    <li class="list-group-item b-primary">

                                        <div class="media">
                                            <div class="pull-left">
                                                  <div class="controls">
                                                    <label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select"><input type="checkbox"><i></i></label>
                                                </div>
                                            </div>
                                            <div class="media-body">
                                                <div class="media-heading m-0">
                                                    <a href="#" class="mr-5">Laborum ullamco culpa</a>
                                                    <span class="label bg-lightred">Email</span>
                                                    <span class="pull-right text-sm text-muted">
                                                      <span class="hidden-xs">12:56, 24.11.2014</span>
                                                    </span>
                                                </div>
                                                <small>Voluptate proident ullamco labore veniam non velit. Ad ut laborum qui fugiat esse est incididunt cillum.</small>
                                            </div>
                                        </div>

                                    </li>

                                   
                                   

                                   

                                </ul>
                                <!-- / mails -->

                            </div>
                            <!-- /right side body -->

                        </div>
                        <!-- /right side -->

                    </div>
                            <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                            	<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left: 0px;margin-top: 15px;">
                            		
                            	</div>
                            	<div class="col-lg-10 col-xs-10 col-md-10"></div>
                            </div>
                            <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                            	
                            </div>
                            </div>
                            
                            <s:if test="mainaction==''">
                        	</s:if>
                        	<s:else>
                        	</s:else>
                            <div role="tabpanel" class="tab-pane" id="bde" style="padding:0px;">
                            	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
									
										
										<div class="col-lg-8" style="padding-left:0px;border-right: 1px solid #efefef;min-height: 500px;">
											<table class="table my-table xlstable table-bordered" style="width: 100%;">
                                        <thead>
                                            <tr>
                                                <th>Agent Code</th>
                                                <th>Agent Name</th>
                                                <th>Designation</th>
                                                <th style="width: 40%;">Location</th>
                                                <th>City</th>
                                                <th>Mobile No</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <s:iterator value="blooddonorsList">
                                            <tr>
                                                <td>01</td>
                                                <td>Abhinav Parmar</td>
                                                <td>Technician</td>
                                                <td>20,Pandey Layout, Khamla</td>
                                                <td>Nagpur</td>
                                                <td>+91-8465456521</td>
                                                <td><a href="#" onclick=""><i class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;<a href="" onclick="" ><i class="fa fa-trash text-danger"></i></a></td>
                                                
                                            </tr>
                                            </s:iterator>
                                        </tbody>
                                    </table>
										</div>
										
										
										<div class="col-lg-4">
											<h5 class="maintite"><b>AGENT DETAIL</b></h5>
											<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-2 col-md-2 col-sm-2" style="padding-left: 0px;">
												<div class="form-group">
													<lable>Code</lable>
													<input type="text" class="form-control">
												</div>
												</div>
												<div class="col-lg-6 col-md-6 col-sm-6" style="padding-right: 0px;">
												<div class="form-group">
													<lable>Agent Name</lable>
													<input type="text" class="form-control">
												</div>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4" style="padding-right: 0px;">
												<div class="form-group">
													<lable>Designation</lable>
													<select class="form-control" name="donor_state">
													    <option value="Technician">Technician</option>
													    <option value="Computer Oppretor">Computer Oppretor</option>
												    </select>
												</div>
												</div>
												
												
											</div>
											
											<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-9 col-md-9 col-sm-9" style="padding-left: 0px;">
											<div class="form-group">
													<lable>Location</lable>
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0px;">
											<div class="form-group">
													<lable>City</lable>
													<select class="form-control" name="donor_state">
													    <option value="Nagpur">Nagpur</option>
													    <option value="Raipur">Raipur</option>
												    </select>
												</div>
											</div>
											
											</div>
											
											<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
											<div class="col-lg-6 col-md-6 col-sm-6" style="padding-left: 0px;">
												<div class="form-group">
													<lable>Mobile No</lable>
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-6" style="padding-right: 0px;">
												<div class="form-group">
													<lable>Email ID</lable>
													<input type="text" class="form-control">
												</div>
											</div>
											</div>
											
											<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;text-align: right;">
												<a href="#" class="btn btn-primary">Add / Update</a>
											</div>
										</div>
								</div>
                            
                            </div>
                            
                            
                        </div>

                    </div>
								
								
								
								
								
        
            
            
            <!--
        // Application Content 


          opd patient report      
   

			-->
			<table class="my-table tablexls" id = "opdPatientTable " style="width: 100%;font-size: 8px;display:none;">    
            <thead>
		    <tr>
		     <th>Date</th>
		     <th>Name</th>
		     <th>Payee</th>
		     <th>Practitioner</th>
		     <th>Appointment Type</th>
		     <th>Status</th>
		    </tr>
		    </thead>
             <tbody>
             <s:iterator value="opdPatientReport">
             
                <tr>
                <td><s:property value="dob"/></td>
                <td><s:property value="clientName"/></td>
                <td><s:property value="whopay"/></td>
                <td><s:property value="diaryUser"/></td>
                <td><s:property value="apmttypetext"/></td>
                <td><s:property value="apmtStatus"/></td>
                </tr>
             </s:iterator> 
             </tbody> 
 			</table><!--


          Ipd patient Report 

            --><table class="my-table tableipdxls" id = "ipdPatientTable " style="width: 100%;font-size: 8px;display:none;">    
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
             <%int i=0; %>
             <s:iterator value="ipdPatientReport">
               
                <tr>
                <td><%=(++i) %></td>
                <td><s:property value="doanew"/></td>
               <td><s:property value="abrivationid"/></td>
               <td><s:property value="wardbedname"/></td>
                <td><s:property value="clientname"/></td>
                <td><s:property value="whopay"/></td>
                <td><s:property value="practitionername"/></td>
                <td><s:property value="refferedby"/></td>
                <td><s:property value="conditionname"/></td>
                <td><s:property value="dodnew"/></td>
                <td><s:property value="totalDays"/></td>
                <td><s:property value="status"/></td>
                <td><s:property value="mlcno"/></td>
                <td><s:property value="mlcrefdoctor"/></td>
                </tr>
             </s:iterator> 
             </tbody> 
 			</table><!--


          Bed Status 
         --><table class="my-table xlsbedtable" id = "bedStatustable " style="width: 100%;font-size: 8px;display:none;">
         
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
         <table class="my-table xlsprescription" id = "prescriptiontable " style="width: 100%;font-size: 8px;display:none;">
         
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


        <!--  Daily Summary -->  

         <table class="my-table tabledaily" id = "dailyReportTable " style="width: 100%;font-size: 8px;display:none;">    
            <thead>
		    <tr>
		     <th>Sr No</th>
		     <th>Client Name</th>
		     <th>Practitioner</th>
		     <th>Location</th>
		     <th>Status</th>
		    </tr>
		    </thead>
             <tbody>
             <%i=0; %>
             <s:iterator value="dailySummaryList">
             
                <tr>
                <td><%=++i %></td>
                <td><s:property value="clientName"/></td>
                <td><s:property value="diaryUser"/></td>
                <td><s:property value="address"/></td>
                <td><s:property value="clinicalNote"/></td>
                </tr>
             </s:iterator>
             <s:iterator value="dailySummaryList1">
                <tr>
                <td><%=++i %></td>
                <td><s:property value="clientName"/></td>
                <td><s:property value="diaryUser"/></td>
                <td><s:property value="address"/></td>
                <td><s:property value="clinicalNote"/></td>
                </tr>
             </s:iterator>
              
             </tbody> 
 			</table><!--


         Invoic Report 

        --><!--

  Advanced and Refunds 


--><table class="my-table xlxadvref" id = "advtable " style="width: 100%;font-size: 8px;display:none;">    
            <thead>
		    <tr>
		 <!--     <th>Sr No</th> -->
		     <th>Date</th>
		     <th>Client Name</th>
		     <th>Location</th>
		     <th>Advanced</th>
		      <th>Refund</th>
		       <th>RefundId</th>
		    </tr>
		    </thead>
             <tbody>
       
             <s:iterator value="advancedRefundList">
             
                <tr>
               <%--  <td><%=++i %></td> --%>
                <td><s:property value="date"/></td>
                <td><s:property value="clientName"/></td>
                <td><s:property value="address"/></td>
                <td><s:property value="amount"/></td>
                 <td><s:property value="advref"/></td>
                 <td><s:property value="refinvoiceid"/></td>
                </tr>   
                </s:iterator>           
             </tbody> 
 			</table><!--




           Account Info- 





         --><table class="my-table xlxacctable" id = "opdPatientTable " style="width: 100%;font-size: 8px;display:none;">    
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
             <%i=0; %>
             <s:iterator value="accountinfo">
             
                <tr>
                <td><%=(++i) %></td>
                <td><s:property value="date"/></td>
                <td><s:property value="clientName"/></td>
                <td><s:property value="paymentmode"/></td>
                <td><s:property value="payAmount"/></td>
                 <td><s:property value="deliverstatus"/></td>
                </tr>
             </s:iterator> 
             </tbody> 
 			</table>
 			
 			
 			
 			
           <table class="my-table xlxclinictbl" id = "clinicTable " style="width: 100%;font-size: 8px;display:none;">    
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
             <%i=0; %>
             <s:iterator value="clinicalViewList">
             
                <tr>
                <td><%=(++i) %></td>
                <td><s:property value="clientName"/></td>
                <td><s:property value="address"/></td>
                <td><s:property value="town"/></td>
                <td><s:property value="treatmentType"/></td>
                </tr>
             </s:iterator> 
      
             </tbody> 
 			</table>    			
 			
 			
 			
 			  <table class="my-table xlxexpensetable" id = "clinicTable " style="width: 100%;font-size: 8px;display:none;">    
            <thead>
		   <tr>
		     <th>Sr No</th>
		     <th>Date</th>
		     <th>Currency</th>
		     <th>Amount</th>
		     <th>Paid by</th>
		     <th>Merchant</th>
		     <th>Category</th>		     
		      <th>Comments</th>
		    </tr>
		    </thead>
             <tbody>
             <%i=0; %>
             <s:iterator value="expenseListReport">
             
                <tr>
                <td><%=(++i) %></td>
                <td><s:property value="caldate"/></td>
                <td><s:property value="currency"/></td>
                <td><s:property value="amount"/></td>
                <td><s:property value="paidby"/></td>
                <td><s:property value="merchant"/></td>
                <td><s:property value="category"/></td>
                <td><s:property value="comments"/></td>
                </tr>
             </s:iterator> 
               <tr>
                 <td></td>
                 <td></td>
                 <td>Total</td>
                 <td><s:property value="totalExpence"/></td>
               </tr>
             </tbody> 
 			</table>

	<!--Pharmacy Daily Sale Report @ -->
	<table class="my-table xlspharmacysaletable" style="width: 100%;font-size: 8px;display:none;">
         
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
		
	<!--Pharmacy Daily Sale Report @ -->
	<table class="my-table xlsinvestigationdetailstable" style="width: 100%;font-size: 8px;display:none;">
         
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
													<td><s:if test="investigation_status==1">New</s:if>
														<s:elseif test="investigation_status==2">Collected</s:elseif>
														<s:elseif test="investigation_status==3">Completed</s:elseif>
														<s:elseif test="investigation_status==4">Aproved</s:elseif>
														<s:elseif test="investigation_status==5">Cancel</s:elseif>
													</td>
												</tr>
											</s:iterator>
										</tbody>
         </table>   			
		
		
        
         
         
         <!--Add Manually entry Modal -->
		<div id="addmanually" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-sm">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Add Data Manually</h4>
		      </div>
		      <div class="modal-body" style="min-height: 250px;">
		       <div class="col-lg-12 col-xs-12">
		       	<div class="col-lg-6 col-md-6 col-xs-6" style="padding-left: 0px;">
		       		<div class="form-group">
		       		<label>From Date</label>
		       		<input class="form-control" type="text"/>
		       	</div>
		       	</div>
		       	<div class="col-lg-6 col-md-6 col-xs-6" style="padding-right: 0px;">
		       		<div class="form-group">
		       		<label>To Date</label>
		       		<input class="form-control" type="text"/>
		       	</div>
		       	</div>
		       	
		       </div>
		       <div class="col-lg-12 col-xs-12">
		       	<div class="col-lg-6 col-md-6 col-xs-6" style="padding-left: 0px;">
		       		<div class="form-group">
		       		<label>Apx.Time</label>
		       		<input class="form-control" type="text"/>
		       	</div>
		       	</div>
		       	<div class="col-lg-6 col-md-6 col-xs-6" style="padding-right: 0px;">
		       		<div class="form-group">
		       		<label>Apx.Time</label>
		       		<input class="form-control" type="text"/>
		       	</div>
		       	</div>
		       	
		       </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
         
         
         
         
         
         
         
         
</body><!--


         ============================================
        ============== Vendor JavaScripts ===============
        ============================================= 
      
		
        --><script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
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

        <script src="mis/assets/js/vendor/flot/jquery.flot.min.js"></script>
        <script src="mis/assets/js/vendor/flot-tooltip/jquery.flot.tooltip.min.js"></script>
        
        <script src="mis/assets/js/vendor/flot/jquery.flot.resize.min.js"></script>
        <script src="mis/assets/js/vendor/flot/jquery.flot.orderBars.js"></script>
        <script src="mis/assets/js/vendor/flot/jquery.flot.stack.min.js"></script>
        <script src="mis/assets/js/vendor/flot/jquery.flot.pie.min.js"></script>
        <script src="mis/assets/js/vendor/flot-spline/jquery.flot.spline.min.js"></script>
        <script src="mis/assets/js/vendor/easypiechart/jquery.easypiechart.min.js"></script>
        <script src="mis/assets/js/vendor/countTo/jquery.countTo.js"></script>
        <!--/ vendor javascripts -->



        <!-- ============================================
        ============== Custom JavaScripts ===============
        ============================================= -->
        <script src="mis/assets/js/main.js"></script>
        <!--/ custom javascripts -->


		<script src="mis/js/mischart.js"></script>
		<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
		<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>


        <script type="text/javascript">
            // Popup window code
            function newPopup(url) {
                popupWindow = window.open(
                    url, 'popUpWindow', 'width=auto,resizable=yes,scrollbars=yes,toolbar=yes,status=yes')
            }
            $(function () {
                $('#inner-content-div').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                $('#inner-content-div2').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                $('#inner-content-div3').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                $('#inner-content-div4').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                $('#inner-content-div5').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                $('#inner-content-div6').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                $('#inner-content-div7').slimScroll({
                    height: '330px'
                });
                $('#inner-content-div8').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                $('#inner-content-div9').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                 $('#inner-content-div45').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                 $('#inner-content-div46').slimScroll({
                    height: '330px',
                    railVisible: true,
					alwaysVisible: true
                });
                 $('.setscrollleft').slimScroll({
                     height: '545px',
                     railVisible: true,
 					alwaysVisible: true
                 });
                 $('.depart1').slimScroll({
				   		height : '425px',
				   		railVisible: true,
						alwaysVisible: true
				  });
				  $('.desig1').slimScroll({
				   		height : '425px',
				   		railVisible: true,
						alwaysVisible: true
				  });
				   $('#name1123').slimScroll({
				   		height : '265px',
				   		railVisible: true,
						alwaysVisible: true
				  });
				   $('.dashbrightset').slimScroll({
				   		height : '375px',
				   		railVisible: true,
						alwaysVisible: true
				  });
				   $('.setrules').slimScroll({
				   		height : '235px',
				   		railVisible: true,
						alwaysVisible: true
				  });
				   $('.stableset').slimScroll({
				   		height : '280px',
				   		railVisible: true,
						alwaysVisible: true
				  });
            });
        </script>


 
 <script>
 $( document ).ready(function() {

	 $( "#collapsebutton" ).hide();

	  $( "#expandbutton" ).click(function() {
	  $('div.panel-collapse').addClass('in').css("height", "");
	  $( "#expandbutton" ).hide();
	  $( "#collapsebutton" ).show();
	  });

	  $( "#collapsebutton" ).click(function() {
	  $('div.panel-collapse').removeClass('in');
	  $( "#expandbutton" ).show();
	  $( "#collapsebutton" ).hide();
	  });

	  $( "div.panel a" ).click(function() {
	    $('div.panel-collapse').each(function( index ) {
	      if($( this ).hasClass('in') ){
	      $( "#expandbutton" ).show();
	      $( "#collapsebutton" ).hide();
	      }
	    });
	  });

	});
 
 </script>
 
 <script>
//Created by Abhi for KPI print Div
 function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}
 </script>
</html>

