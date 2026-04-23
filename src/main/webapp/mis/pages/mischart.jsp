
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

	MisChartForm misChartForm = (MisChartForm)session.getAttribute("misChartForm");
	String action = (String)session.getAttribute("graphaction");
	
%>

<!DOCTYPE html>
<html lang="en"> 

    <head>

        <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, maximum-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="Dashboard">

 		

        <!-- ============================================
        ================= Stylesheets ===================
        ============================================= -->
        <!-- vendor css files -->
        <link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="mis/assets/css/vendor/animate.css">
        
    
        
       
        <!-- project main css files -->
        <link rel="stylesheet" href="mis/assets/css/main.css">
          <link rel="stylesheet" href="_assets/newtheme/css/mbile.css">
        <!--/ stylesheets -->

 
  	
        <!-- ==========================================
        ================= Modernizr ===================
        =========================================== -->
        <script src="mis/assets/js/vendor/modernizr/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <!--/ modernizr -->
        
        <script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
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

    <body class="appWrapper  hz-menu">
    <div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<s:include value="/accounts/pages/letterhead.jsp"></s:include>
			</div>
		</div>
	</div>
        <div id="wrap" class="animsition">
                                              		
			
                  
        <div class="row details mainheader hidden-print">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-1 col-md-1 col-sm-1 hidden-xs oneseticonleft">
									<img src="dashboardicon/mis.png" class="img-responsive prescripiconcircle">
								</div>
								<div class="col-lg-11 col-md-11 col-sm-11 col-xs-12 hidden-xs titlestleftiocn">
								<h4>MIS</h4>
								</div>
									</div>
									<s:include value="/mis/pages/misheader.jsp"></s:include>
										</div>
							
										<div class="col-lg-2 col-md-2 hidden-xs">
												<div class="text-right hidden">
											  		 <a href="#" onclick="openPopup('miswidgetDiaryMangent')" class="btn btn-default marrigh10se" title="Personalize Widgets">Personalize Widget</a>
											  	</div>
										</div></div>
										<div class="col-lg-12 col-md-12 paddingnil" >
										<div class="col-lg-12 col-md-12 topback2">
										<div class="col-lg-9 col-md-9 xs-12 hidden-print" style="margin-top: 30px">
										<s:form class="form-inline" action="MisChart" id="mischartfrm" theme="simple" style="display:inline-flex;">
										
										     <s:hidden name="action" id="action"></s:hidden>
										     <s:hidden name="graphName" id="graphName"></s:hidden> 
											  <%if(loginInfo.getJobTitle().equals("Admin")){%>
		                                  			<div class="form-group setdorp">
			                                  			<s:select name="invoicecategory" id="invoicecategory"  
														list="#{'2':'All','0':'Primary','1':'Secondary' }"
														cssClass="form-control"></s:select>
		                                  			</div>
		                                  		<%} %>
											  <div class="form-group setdorp">
											     <s:textfield readonly="true" name="fromDate" id="fromDate" cssClass="form-control" theme="simple"></s:textfield>
											  </div>
											   <div class="form-group totext">
											     To
											  </div>
											  <div class="form-group setdorp">
											    <s:textfield readonly="true" name="toDate" id="toDate" cssClass="form-control" theme="simple"></s:textfield>
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
											 <s:submit value="Go" theme="simple" cssClass="btn btn-primary marrigh10se" onclick="return submitmis()"></s:submit>
											  <a href="#" onclick="goreferesh()" class="btn btn-primary marrigh10se" title="Refresh"><i class="fa fa-refresh"></i></a>
											</s:form>
										</div>
										</div>
										</div>
								
        <div id="" class="">
            <section id="content">
                <div class="page page-charts martomi" style="margin-top: -12px!important;">
                    <!-- row -->
                    <div class="row hidden">
                        <!-- col -->
                        
                         <s:hidden name="action" id="actionchart"></s:hidden>
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
                        <div class="col-md-12 padnil">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 10px">
                                <div class="">
	
	
	
	
	
	
	<div class="">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bhoechie-tab-container">
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 bhoechie-tab-menu" style="margin-top: -9px">
            
<!--   Mobile Menu       --> 

<p>
  <a  onClick="showHideDiv('divMsg')" class="btn btn-primary displaynoneweb mismenubarmobile" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
<span class="pull-left">MIS Menu    </span>  <span class="pull-right"><i class="fa fa-bars"></i></span>
  </a>
 
</p>
<div class="collapse" id="collapseExample">
  <div class="card card-body">
   



  <div class="card card-body displaynoneweb">
  
 
 <script type="text/javascript">
			function showHideDiv(ele) {
				var srcElement = document.getElementById(ele);
				if (srcElement != null) {
					if (srcElement.style.display == "block") {
						srcElement.style.display = 'none';
					}
					else {
						srcElement.style.display = 'block';
					}
					return false;
				}
			}
		</script>
 
   
            
 
</div>         
 
  </div>
</div>
 
 
 
<!--  web Menu -->
  <div id="divMsg"  class="card card-body displaynonemobile">
  
 
            
            <div  class="setscrollleft">
            	<div class="list-group" style="height:auto;">
            	<a href="#" class="list-group-item text-center bgste" onclick="dosubmit('dailysummary')"  id="dailysummary" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Daily Summary
                </a>
                <a href="#" onclick="dosubmit('yearly')"  class="list-group-item text-center bgste" id="yearly" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Yearly Statistics
                </a>
                <!-- <a href="#" class="list-group-item text-center" onclick="dosubmit('monthly')" id="monthly"  style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp; Monthly KPI
                </a> -->
                 <!-- <a href="#" class="list-group-item text-center" onclick="dosubmit('monthly')" id="monthly"  style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Monthly Statistics
                </a> -->
                 <a href="#"  class="list-group-item text-center bgste" onclick="dosubmit('opd')" id="opd" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;OPD Patients
                </a>
                <a href="#"  class="list-group-item text-center" onclick="dosubmit('ipd')" id="ipd" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;IPD Patients
                </a>
                <a href="#" class="list-group-item text-center bgste" onclick="dosubmit('bedstatus')"  id="bedstatus"  style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Bed Status
                </a>
                <a href="#" class="list-group-item text-center" onclick="dosubmit('prescriptionsummary')"  id="prescriptionsummary" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Prescription
                </a>
                <a href="#" class="list-group-item text-center bgste" onclick="dosubmit('pharmacysummary')"  id="pharmacysummary" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Pharmacy 
                </a>
                <a href="#" class="list-group-item text-center" onclick="dosubmit('procurementsummary')"  id="procurementsummary" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Procurement 
                </a>
                <a href="#" class="list-group-item text-center bgste" onclick="dosubmit('indentsummary')"  id="indentsummary" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Indent 
                </a>
                <a href="#" class="list-group-item text-center" onclick="dosubmit('investigation')"  id="investigation" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Investigation
                </a>
                
                <a href="#"  id="invoice" class="list-group-item text-center" onclick="dosubmit('invoice')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Invoice & Billing
                </a>
                 <a href="#"  id="paymentmode" class="list-group-item text-center bgste" onclick="dosubmit('paymentmode')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Payment Mode
                </a>
                 <a href="#"  id="advref" class="list-group-item text-center" onclick="dosubmit('advref')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Advance & Refund
                </a>
                 <a href="#"  id="accountinfo" class="list-group-item text-center bgste" onclick="dosubmit('accountinfo')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Finance Summary
                </a>
                 <a href="#"  id="clinicalview" class="list-group-item text-center" onclick="dosubmit('clinicalview')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Clinical View
                </a>
                  <!-- <a href="#"  id="expense" class="list-group-item text-center bgste" onclick="dosubmit('expense')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Expense Summary
                </a> -->
                  <a href="#"  id="patientview" class="list-group-item text-center" onclick="dosubmit('patientview')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Patient View
                </a>
                 <a href="#"  id="patientviewpackage" class="list-group-item text-center bgste" onclick="dosubmit('patientviewpackage')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;Patient View By Package
                </a>
                 <a href="#"  id="kpireports" class="list-group-item text-center hidden" onclick="dosubmit('kpireports')" style="height:33.33%;">
                  <i class="fa fa-angle-right" aria-hidden="true"></i>&nbsp;&nbsp;KPI Report
                </a>
              </div>
            </div>
            
 
</div>
            
            </div>
            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12 bhoechie-tab">
             	<div class="bhoechie-tab-content" id="yearlyt">
                   <div class="row">
                   	<div class="col-lg-12 col-md-12 paddinillll">
                   	<div class="col-lg-8 col-md-8 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   	<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<span>Yearly Statistics</span>
                   			</div>
                   			</div>
                   			
                   	<div id="container" class="responsivecircle1" style="height: 500px; margin: 0 auto">
                   	
                   	   <script type="text/javascript">
                   	   
                   	 $(function () {
                   	    $('#container').highcharts({
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
                   	<div class="col-lg-4 col-md-4 paddinillll">
                   	<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<span>Total for <s:property value="currentMonth"/></span>
                   			</div>
                   			</div>
                   	<div id="browser-usage" class="responsivecircle" style="height: 500px"></div>
                   	</div>
                   	</div>
                   </div>
                </div>
                   <div class="bhoechie-tab-content" id="monthlyt">
                   <div class="row">
                   	<div class="col-lg-12 col-md-12 paddinillll">
                   	<div class="col-lg-6 col-md-6 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   	<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   
                   			<div class="backsetrcololefttext">
                   			<span>Monthly Summary </span>
                   			<span class="pull-right"><a class="printmonthlykpi" href=""><i class="fa fa-print"></i></a> </span>
                   			</div>
                   			</div>
                   				<div class="">
                   	<div class="col-lg-12 col-md-12" style="margin-top: 15px;">
                                        <div class="progress-list hidden">
                                            <div class="details">
                                                <div class="title">Total Admissions </div>
                                                <div class="description">month wise</div>
                                            </div>
                                            <div class="status pull-right">
                                                <span><s:property value="montwiseAdmission"/></span>%
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="progress-xs not-rounded progress">
                                                <div class="progress-bar progress-bar-dutch" role="progressbar" aria-valuenow="<s:property value="montwiseAdmission"/>" aria-valuemin="0" aria-valuemax="100" style="width: <s:property value="montwiseAdmission"/>%">
                                                    <span class="sr-only"><s:property value="montwiseAdmission"/>%</span>
                                                </div>
                                            </div>
                                        </div>
                                        
                                       
                                       	<div class="form-group mischartnethospitalrevenu pull-left widthi marginlefti" title="">
				<div class="miscounttextnum">0</div> <div class="clearfix"></div> 	<div class="miscounttext">Gross OPD revenue</div>
			</div>
			
			   
		                    
                                        
                                    
                                 
			<div class="form-group mischarttotalcountboxa pull-left  widthi marginlefti" title="">
				<div class="miscounttextnum">0</div>  <div class="clearfix"></div>  <div class="miscounttext">Gross IPD revenues</div>
			</div>
			
				
			<div title="" class="form-group mischartnethospitalrevenu pull-left widthi marginlefti">
				<div class="miscounttextnum">0</div>  <div class="clearfix"></div> <div class="miscounttext">Net OPD revenues</div>
			</div>
                   
			
			<div title="" class="form-group mischarttotalcountboxa pull-left  widthi marginlefti">
				<div class="miscounttextnum">0</div>  <div class="clearfix"></div> <div class="miscounttext">Net IPD revenues</div>
			</div>
			
			<div title="" class="form-group mischartnethospitalrevenu pull-left widthi marginlefti">
				<div class="miscounttextnum">1</div>  <div class="clearfix"></div> <div class="miscounttext">Number Of in patients</div>
			</div>
			
			 <div title="Conversion IPD Revenue" class="form-group mischarttotalcountboxa pull-left  widthi marginlefti">
				<div class="miscounttextnum"> 0</div> <div class="clearfix"></div>  <div class="miscounttext">Number of out patients</div> 
			</div>    
			
			<div class="form-group mischartconversionratio pull-left widthi marginlefti" title="">
					<div class="miscounttextnum">0</div>  <div class="clearfix"></div>  <div class="miscounttext">Net Investigation revenue</div>
			</div>         
			
			
                                     <div class="form-group mischarttotalcountboxa pull-left  widthi marginlefti" name"IPD" title="Bed Occupancy rate=(Inpatient Days of Care) / (Bed Days Available) x 100">
				<div class="miscounttextnum">0.0/0.0 = %</div>  <div class="clearfix"></div>  <div class="miscounttext">Bed Occupancy </div>
			</div>
			
			
			
                                        
                       
                                   
                                    
          
			
			
			
			
		
			<div title="" class="form-group mischartconversionratio pull-left widthi marginlefti">
				<div class="miscounttextnum"> 0</div> <div class="clearfix"></div>  <div class="miscounttext">Gross Investigation revenue</div> 
			</div>
			
			
                                             
			<div class="form-group mischarttotalcountboxa pull-left  widthi marginlefti" name="IPD" title="Average Length of stay :(TOTAL INPATIENT DAYS OF STAY IN HOSPITAL / TOTAL ADMISSIONS) = AVERAGE LENGTH OF STAY (IN DAYS)">
				<div class="miscounttextnum">0.0/1 = 0.0 days</div> <div class="clearfix"></div> 	<div class="miscounttext">Average Length of stay </div>
			</div>                     
                                        
                                        
                                        
                                        
                                    </div>
                   	</div>
                   
                   	</div>
                   	<div class="col-lg-6 col-md-6 paddinillll" id="yearly2">
                   	<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<div class="form-inline">
											
											<div class="form-group" >
												 <select name="fromDate" id="ipdmonthlyreportSummary_fromDate" class="form-control misslectwhit">
    <option value="01">January</option>
    <option value="02">February</option>
    <option value="03">March</option>
    <option value="04">April</option>
    <option value="05" selected="selected">May</option>
    <option value="06">June</option>
    <option value="07">July</option>
    <option value="08">August</option>
    <option value="09">September</option>
    <option value="10">October</option>
    <option value="11">November</option>
    <option value="12">December</option>


</select>
											</div> 
											<div class="form-group">
												 <select name="toDate" id="ipdmonthlyreportSummary_toDate" class="form-control misslectwhit">
    <option value="2014">2014</option>
    <option value="2015">2015</option>
    <option value="2016">2016</option>
    <option value="2017">2017</option>
    <option value="2018" selected="selected">2018</option>
    <option value="2019">2019</option>
    <option value="2020">2020</option>
    <option value="2021">2021</option>
    <option value="2022">2022</option>
    <option value="2023">2023</option>
    <option value="2024">2024</option>
    <option value="2025">2025</option>
    <option value="2026">2026</option>
    <option value="2027">2027</option>
    <option value="2028">2028</option>


</select>
											</div> 
											
											<div class="form-group">
												<button type="submit" class="btn btn-primary misgomonthlykpi">Go</button>
												<!-- <a href="#" class="btn btn-primary" onclick="submitmainform()">Go</a> -->
											</div>
											 
										</div>
                   			</div>
                   			
                   			</div>
                   			<table class="table table-no-border m-0" style="width: 100%;font-size: 8px">
							<!-- <thead>
							<tr>
								<th></th>
								<th></th>
							</tr>
					</thead> -->
					<tbody>
					
					
					
					
						
					
					
					
					
					
					
					<tr class="hidden">
							<td><b>Bed Occupancy</b></td>
							<td>Bed Occupancy rate=(Inpatient Days of Care) / (Bed Days Available) x 100</td>
							<td>0.0/0.0 = %</td>
						</tr>
						<tr class="hidden">
							<td><b>Average Length of stay</b></td>
							<td>Average Length of stay :(TOTAL INPATIENT DAYS OF STAY IN HOSPITAL  / TOTAL ADMISSIONS) = AVERAGE LENGTH OF STAY (IN DAYS)</td>
							<td>0.0/1 = 0.0 days</td>
						</tr>
					
						
						<tr class="hidden">
							<td style=""><b>Number of out patients</b></td>
							<td></td>
							<td>0</td>
						</tr>
						
						<tr class="hidden">
							<td><b>Number Of in patients</b></td>
							<td></td>
							<td>1</td>
						</tr>
						<tr class="hidden">
							<td><b>Gross IPD revenues</b></td>
							<td></td>
							<td>0</td>
						</tr>
						<tr class="hidden">
							<td><b>Gross OPD revenue</b></td>
							<td></td>
							<td>0</td>
						</tr>
						<tr class="hidden">
							<td><b>Gross Investigation revenue</b></td>
							<td></td>
							<td>0</td>
						</tr>
						
						<tr class="hidden">
							<td><b>Net IPD revenues</b></td>
							<td></td>
							<td>0</td>
						</tr>
						<tr class="hidden">
							<td><b>Net OPD revenue</b></td>
							<td></td>
							<td>0</td>
						</tr>
						<tr class="hidden">
							<td><b>Net Investigation revenue</b></td>
							<td></td>
							<td>0</td>
						</tr>
						
						<tr class="monthlykpitr">
						<td colspan="3"><b>Department/Specialty wise revenues (top ten specialization and others)</b></td>
						</tr>
						
							<tr class="monthlykpitr">
							<td>General &amp; Laprascopic Surgery</td>
							<td></td>
							<td>796000</td>
							</tr>
						
							<tr class="monthlykpitr">
							<td>Internal Medicine</td>
							<td></td>
							<td>413000</td>
							</tr>
						
							<tr class="monthlykpitr">
							<td>Urology &amp; Uro-Surgery</td>
							<td></td>
							<td>226000</td>
							</tr>
						
							<tr class="monthlykpitr">
							<td>E.N.T.</td>
							<td></td>
							<td>137500</td>
							</tr>
						
							<tr class="monthlykpitr">
							<td>Orthopaedics</td>
							<td></td>
							<td>86500</td>
							</tr>
						
							<tr class="monthlykpitr">
							<td>Obstetrics &amp; Gynaecology</td>
							<td></td>
							<td>56000</td>
							</tr>
						
							<tr class="monthlykpitr">
							<td>Cardiology</td>
							<td></td>
							<td>47000</td>
							</tr>
						
							<tr class="monthlykpitr">
							<td>Medico Legal Consultant</td>
							<td></td>
							<td>41500</td>
							</tr>
						
							<tr class="monthlykpitr">
							<td>Nephrology</td>
							<td></td>
							<td>25000</td>
							</tr>
						
						
						<tr class="monthlykpitr">
						<td colspan="2"><b>Total No. of cases of surgeries conducted, with the break up of various types/classes of surgeries</b></td>
						<td>0</td>
						</tr>
						
						
					</tbody>
			 </table>
                   			
                   			
                   	</div>
                   
                   	</div>
                   </div>
                </div>
               
                <div class="bhoechie-tab-content" id="opdt">
                   <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   				<a style="color:#fff;" href="#" onclick="printExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                    <div class="col-md-5">
                                                            <img src="mis/assets/images/icon/OPD_2.png" class="img-responsive" />
                                                    </div>
                                                    <div class="col-md-7">
                                                        <!--<p class="standard-section-data-label">OutPatient: <s:property value="totalOpdPatient"/> </p>
                                --></div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                               
                                                                <tr>

                                                                    <td>1) Booked</td>
                                                                    <td><s:property value="bookedAppointment"/></td>
                                                                </tr>
                                                                <tr>

                                                                    <td>2) Completed</td>
                                                                    <td><s:property value="totalCompleted"/></td>
                                                                </tr>
                                                                <tr>

                                                                    <td>3) DNA</td>
                                                                    <td><s:property value="totaldna"/></td>
                                                                </tr>
																
																<tr>

                                                                    <td>4) Not Completed</td>
                                                                    <td><s:property value="notCompleted"/></td>
                                                                </tr>
                                                                <tr>

                                                                    <td>5) OT</td>
                                                                    <td><s:property value="otPatientCount"/></td>
                                                                </tr>
																 <tr>

                                                                    <td>6) Cancelled</td>
                                                                    <td><s:property value="totalcancel"/></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 col-sm-7 col-xs-12 paddinillll" id="opd2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			
                   			
                   			
                   			     <div id="opdg" class="responsivemng" style="height: 500px;margin: 0px auto;"></div>		
										     <script>
										   $(function () {
										    // Create the chart
										    $('#opdg').highcharts({
										       chart: {
										            type: 'column'
										        },
										        title: {
										            text: 'OPD Patients'
										        }, 	
										        xAxis: {
										            type: 'category'
										        },
										        yAxis: {
										            title: {
										                text: 'OPD Patients'
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
										            name: 'OPD',
										            colorByPoint: true,
										            data: [{
										                name: 'Booked',
										                y: <%=misChartForm.getBookedAppointment()%>,
										                drilldown: 'Booked'
										            }, {
										                name: 'Completed',
										                y: <%=misChartForm.getTotalCompleted()%>,
										                drilldown: 'Completed'
										            }, {
										                name: 'DNA',
										                y: <%=misChartForm.getTotaldna()%>,
										                drilldown: 'DNA'
										            },
										            {
										                name: 'Not Completed',
										                y: <%=misChartForm.getNotCompleted()%>,
										                drilldown: 'NOT Completed'
										            },{
										                name: 'OT',
										                y: <%=misChartForm.getOtPatientCount()%>,
										                drilldown: 'OT'
										             }
										            ]
										        }],
										        drilldown: {
										            series: [{
										                name: 'Booked',
										                id: 'Booked',
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
										                name: 'DNA',
										                id: 'DNA',
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
										            },
										            {
										                name: 'Not Completed',
										                id: 'Not Completed',
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
										                name: 'OT',
										                id: 'OT',
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
                <div class="bhoechie-tab-content" id="ipdt">
                   <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   				<a href="#" style="color:#fff;" onclick="printIpdExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                    <div class="col-md-5">
                                                            <img src="mis/assets/images/icon/IPD_2.png" class="img-responsive" />
                                                    </div>
                                                    <div class="col-md-7">
                                                        <!--<p class="standard-section-data-label">OutPatient: <s:property value="totalOpdPatient"/> </p>
                                --></div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div2">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                                <tr>

                                                                    <td>1) Total Admissions</td>
                                                                    <td><s:property value="newadmission"/></td>
                                                                </tr>
                                                                <tr>

                                                                    <td>2) Patients In-house Today</td>
                                                                    <td><s:property value="inhousepatients"/></td>
                                                                </tr>
                                                                <tr>

                                                                    <td>3) Total Discharged</td>
                                                                     <td><s:property value="dischargepatients"/></td>
                                                                </tr>
                                                                <%-- <tr>

                                                                    <td>4) New Admission Today</td>
                                                                     <td><s:property value="newaddmissiontoday"/></td>
                                                                </tr> --%>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="ipd2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			        <div class="backsetrcolo">
                   				       <span>View Chart</span>
                   			       </div>
                   			    </div>
                   			          <div id="ipdg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
									        <SCRIPT type="text/javascript">
									        $(function () {
									         // Create the chart
									         $('#ipdg').highcharts({
									             
									                 chart: {
									            type: 'column'
									        },
									        title: {
									            text: 'IPD Patients'
									        }, 	
									        xAxis: {
									            type: 'category'
									        },
									        yAxis: {
									            title: {
									                text: 'IPD Patients'
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
									            name: 'IPD',
									            colorByPoint: true,
									            data: [{
									                name: 'New Admission',
									                y: <%=misChartForm.getNewadmission()%>,
									                drilldown: 'New Admission'
									            }, {
									                name: 'Inhouse Patients',
								                y: <%=misChartForm.getInhousepatients()%>,
								                drilldown: 'Inhouse Patients'
								            }, {
								                name: 'Discharge Patients',
								                y: <%=misChartForm.getDischargepatients()%>,
								                drilldown: 'Discharge Patients'
								            }, {
								                name: 'Admission Today',
								                y: <%=misChartForm.getNewaddmissiontoday()%>,
								                drilldown: 'Admission Today'
								            }
								            ]
								        }],
								        drilldown: {
								            series: [{
								                name: 'New Admission',
								                id: 'New Admission',
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
								                name: 'Inhouse Patients',
								                id: 'Inhouse Patients',
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
								                name: 'Discharge Patients',
								                id: 'Discharge Patients',
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
                <div class="bhoechie-tab-content" id="bedstatust">
                   <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printBedStatusExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                    <div class="col-md-5">
                                                            <img src="mis/assets/images/icon/bed.png" class="img-responsive" />
                                                    </div>
                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total Beds Today: <s:property value="totalbeds"/> </p>
                                </div>
                                                </div>
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div3">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                               
                                                                 <tr>
                                                                    <td>1) Available</td>
                                                                    <td><s:property value="availablebed"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>2) Occupied</td>
                                                                    <td><s:property value="occupiedbed"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>3) To be Discharge</td>
                                                                    <td><s:property value="dischargepatients"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>4) Under Maintenance</td>
                                                                    <td><s:property value="undermaintnancebed"/></td>
                                                                </tr>
                                                               
                                                              

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="bedstatus2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			     
                   			     <div id="bedstatusg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
        					  <SCRIPT type="text/javascript"><!--
						        $(function () {
						         // Create the chart
						         $('#bedstatusg').highcharts({
                   			         
                   			                 chart: {
										            type: 'column'
										        },
										        title: {
										            text: 'Bed Status'
										        }, 	
										        xAxis: {
										            type: 'category'
										        },
										        yAxis: {
										            title: {
										                text: 'Bed Status'
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
										            name: 'Bed Status',
										            colorByPoint: true,
										            data: [{
										                name: 'Available',
										                y: <%=misChartForm.getAvailablebed()%>,
										                drilldown: 'Available'
										            },{
										                name: 'Occupied',
										                y: <%=misChartForm.getOccupiedbed()%>,
										                drilldown: 'Occupied'
										            },{
										                name: 'Discharged Today',
										                y: <%=misChartForm.getDischargepatients()%>,
										                drilldown: 'Discharged Today'
										            },{
										                name: 'Under Maintenance',
										                y: <%=misChartForm.getUndermaintnancebed()%>,
										                drilldown: 'Under Maintenance'
										            }
										            ]
										        }],
										        drilldown: {
										            series: [{
										                name: 'Total Beds',
										                id: 'Total Beds',
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
										                name: 'Under Maintenance',
										                id: 'Under Maintenance',
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
										                name: 'Occupied',
										                id: 'Occupied',
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
										                name: 'Available',
										                id: 'Available',
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
                
                
                <div class="bhoechie-tab-content" id="prescriptionsummaryt">
                   <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printPrescriptionExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                    <div class="col-md-5">
                                                            <img src="mis/assets/images/icon/prescription.png" class="img-responsive" />
                                                    </div>
                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total Prescription: <s:property value="totalprescription"/></p>
                                </div>
                                                </div>
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div3">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                               
                                                                 <tr>
                                                                    <td>1) Requested</td>
                                                                    <td><s:property value="notdelivered"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>2) Delivered</td>
                                                                    <td><s:property value="delivered"/></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="prescriptionsummary2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			     
                   			     <div id="prescriptionsummaryg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
        					  <SCRIPT type="text/javascript"><!--
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
                
                <div class="bhoechie-tab-content" id="pharmacysummaryt" >
                  <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printPharmacyDailySaleExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                    <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/expense.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total Received: <s:property value="totalpayment" /> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div7">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                                <tr>
                                                                    <td>Credit</td>
                                                                    <td><s:property value="totalcredit" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Credit Return</td>
                                                                    <td><s:property value="creditReturn" /></td>
                                                                </tr>
																 <tr>
                                                                    <td>Hospital  </td>
                                                                     <td><s:property value="hospital" /></td>
                                                                </tr>
                                                                <tr style="border-bottom: 1px solid #ddd;">
                                                                    <td>Hospital Return </td>
                                                                     <td><s:property value="hospitalReturn" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Cash </td>
                                                                     <td><s:property value="todaycash" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Card </td>
                                                                     <td><s:property value="todaycard" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Cheque </td>
                                                                     <td><s:property value="chequepayments" /></td>
                                                                </tr>
                                                                <tr style="border-bottom: 1px solid #ddd;">
                                                                    <td>NEFT/RTGS </td>
                                                                     <td><s:property value="neftpayment" /></td>
                                                                </tr>
                                                                <tr style="border-bottom: 1px solid #ddd;">
                                                                    <td>Total Received </td>
                                                                     <td><s:property value="totalpayment" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Cash Return </td>
                                                                     <td><s:property value="todayReturn" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Discount </td>
                                                                     <td><s:property value="todaydisc" /></td>
                                                                </tr>
																
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="pharmacysummary2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			         <div id="pharmacysummaryg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
                
                
                <div class="bhoechie-tab-content" id="procurementsummaryt">
                   <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printProcurmentExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                    <div class="col-md-5">
                                                            <img src="mis/assets/images/icon/procurement.png" class="img-responsive" />
                                                    </div>
                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total Procurements: <s:property value="totalgrn"/> </p>
                                </div>
                                                </div>
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div3">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                               
                                                                 <tr>
                                                                    <td>1) No of PO</td>
                                                                    <td><s:property value="grnwithpo"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>2) No of GRN without PO</td>
                                                                    <td><s:property value="grnwithoutpo"/></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="procurementsummary2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			     
                   			     <div id="procurementsummaryg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
                
                <div class="bhoechie-tab-content" id="indentsummaryt">
                   <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printIndentExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                    <div class="col-md-5">
                                                            <img src="mis/assets/images/icon/indent.png" class="img-responsive" />
                                                    </div>
                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total Indents: <s:property value="totalindent"/> </p>
                                </div>
                                                </div>
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div3">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                               <!-- '0':'Request', '1':'Approved', '2':'Rejected', '3':'Delivered' , '4':'Received', '5':'PO Created', '6':'Pending','7':'Ready To Transfer','8':'Direct Transfer','9':'Return' -->
                                                                 <tr>
                                                                    <td>1) Direct Transfer</td>
                                                                    <td><s:property value="direct_transfer"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>2) Request</td>
                                                                    <td><s:property value="request"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>3) Rejected</td>
                                                                    <td><s:property value="rejected"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>4) Pending</td>
                                                                    <td><s:property value="pending"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>5) PO Created</td>
                                                                    <td><s:property value="pocreated"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>6) Transferred</td>
                                                                    <td><s:property value="transfer"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>7) Approved</td>
                                                                    <td><s:property value="approved"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>8) Received</td>
                                                                    <td><s:property value="received"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>9) Ready To Transfer</td>
                                                                    <td><s:property value="readytotransfer"/></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="indentsummary2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			     
                   			     <div id="indentsummaryg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
                
                
                <div class="bhoechie-tab-content" id="investigationt" >
                  <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printInvestigationDetailsExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                    <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/investigation.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total: <s:property value="totalInvestigation"/> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div7">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                                <tr>
                                                                    <td>1) New</td>
                                                                    <td><s:property value="new_invistigation"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>2) Collected</td>
                                                                    <td><s:property value="new_collected"/></td>
                                                                </tr>
																 <tr>
                                                                    <td>3) Completed  </td>
                                                                     <td><s:property value="new_completed"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>4) Approved </td>
                                                                     <td><s:property value="new_aproved"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>5) Cancelled </td>
                                                                     <td><s:property value="deleted_investigation"/></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="investigation2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			         <div id="investigationg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
                
                
                <div class="bhoechie-tab-content" id="dailysummaryt" >
                  <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printDailyExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                    <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/daily.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                       <%--  <p class="standard-section-data-label">Total: <s:property value="totalipdopdpatient"/> </p> --%>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div7">
                                                        <table class="table table-no-border m-0">
                                                            <%-- <tbody>
                                                                <tr>
                                                                    <td>1) Total OPD</td>
                                                                    <td><s:property value="totalopdseen"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>2) Total IPD Admission</td>
                                                                    <td><s:property value="ipdnewadmission"/></td>
                                                                </tr>
																 <tr>
                                                                    <td>3) Total OT </td>
                                                                     <td><s:property value="otPatientCount"/></td>
                                                                </tr>
																
                                                            </tbody> --%>
                                                            <tbody>
                                                             <tr>
                                                                    <td><b>-Total Reg. Patient</b></td>
                                                                    <td><s:property value="newregpatient"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td><b>-Total OPD Booked</b></td>
                                                                    <td><s:property value="totalopdseen"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td><b>-Total OPD Completed</b></td>
                                                                    <td><s:property value="totalopdcompleted"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td><b>-Total OPD-DNA</b></td>
                                                                    <td><s:property value="totalopddna"/></td>
                                                                </tr>
                                                                
                                                               <s:iterator value="opdappointmenttype">
                                                                <s:if test="result==0">
                                                                </s:if>
                                                                <s:else>
                                                                <tr>
                                                                	<td> -<s:property value="name"/></td>
                                                                    <td><s:property value="result"/></td>
                                                                </tr>
                                                                </s:else>
                                                               </s:iterator>
                                                                
                                                                <tr>
                                                                    <td><b>-Total IPD Admission</b></td>
                                                                    <td><s:property value="ipdnewadmission"/></td>
                                                                <tr>

                                                                    <td>-Total Patients In-house Today</td>
                                                                    <td><s:property value="inhousepatients"/></td>
                                                                </tr>
                                                                
                                                                 <tr>
                                                                    <td>-MLC Admission</td>
                                                                    <td><s:property value="totalmlcaddmission"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>-Total Discharged</td>
                                                                     <td><s:property value="dischargepatients"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>-Total Death</td>
                                                                     <td><s:property value="totaldeath"/></td>
                                                                </tr>
                                                                
                                                                  <tr>
                                                                    <td>-Total DAMA</td>
                                                                     <td><s:property value="totalDAMA"/></td>
                                                                </tr>
                                                                
                                                                 <tr>
                                                                    <td><b>-Total OT </b></td>
                                                                     <td><s:property value="otPatientCount"/></td>
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
                                                                   <td><b>-Total Investigation</b></td>
                                                                     <td><s:property value="totalInvestigation"/></td>
                                                                </tr>
                                                                <!-- pathlab/radiology/maicrobiology/endoscopy/cardiology -->
                                                                
                                                                <tr>
                                                                    <td>-Total Pathlab</td>
                                                                     <td><s:property value="totalpathlab"/></td>
                                                                </tr>
                                                                
                                                                <%-- <tr>
                                                                    <td>-Total Radiology</td>
                                                                     <td><s:property value="totalradiology"/></td>
                                                                </tr> --%>
                                                                
                                                                <tr>
                                                                    <td>-Total Microbiology</td>
                                                                     <td><s:property value="totalmaicrobiology"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>-Total Endoscopy</td>
                                                                     <td><s:property value="totalendoscopy"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>-Total Cardiology</td>
                                                                     <td><s:property value="totalcardiology"/></td>
                                                                </tr>
                                                                
                                                               
                                                                <tr>
                                                                    <td>-Total CT SCAN</td>
                                                                     <td><s:property value="totalctinvest"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>-Total MRI</td>
                                                                     <td><s:property value="totalmricount"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>-Total XRAY</td>
                                                                     <td><s:property value="totalxraycount"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>-Total USG</td>
                                                                     <td><s:property value="totalsonographycount"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>-NON INVASIVE CARDIOLOGY</td>
                                                                     <td><s:property value="totalcardiologycount"/></td>
                                                                </tr>
                                                                
                                                                <!-- <tr>
                                                                    <td>-Total TMT</td>
                                                                     <td></td>
                                                                </tr> -->
                                                                
                                                              
                                                                <tr>
                                                                  <td>-Total IPD & OPD Charges Added</td>
                                                                     <td><s:property value="chargeaddedd"/></td>
                                                                </tr>

                                                                 <tr>
                                                                   <td>-Total IPD & OPD Payment</td>
                                                                     <td><s:property value="paymentrecieved"/></td>
                                                                </tr>
<tr>
                                                                  <td>-No. of Casuality</td>
                                                                     <td><s:property value="noofcasuality"/></td>
                                                                </tr>
                                                                <tr>
                                                                  <td>-No. of Daycare</td>
                                                                     <td><s:property value="noofdaycare"/></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="dailysummary2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			         <div id="dailysummaryg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
                   			</div>
                   		</div>
                   </div>
                </div>
                <div class="bhoechie-tab-content" id="invoicet">
                   <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printInvoiceExcel()" title="Download CSV file" style="line-height: 26px;"><i class="fa fa-download"></i> Download Excel</a>
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                     <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/invoice.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Rs. <s:property value="paymentrecieved"/> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div4">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                            <tr>
                                                                    <td>1) Payment Received</td>
                                                                     <td><%=Constants.getCurrency(loginInfo)%> <s:property value="paymentrecieved"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>2) Charges Added</td>
                                                                    <td><%=Constants.getCurrency(loginInfo)%> <s:property value="chargeaddedd"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>3) Invoices Generated</td>
                                                                    <td><%=Constants.getCurrency(loginInfo)%> <s:property value="invoicegenerated"/></td>
                                                                </tr>
                                                                

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="invoice2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			        
                   					<div id="invoiceg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
                <div class="bhoechie-tab-content" id="paymentmodet">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
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
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
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
                <div class="bhoechie-tab-content" id="advreft">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printAdvRefExcel()" title="Download CSV file"><i class="fa fa-download"></i> Download Excel</a>
                   			
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                     <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/advance.png" class="img-responsive" />
                                                    </div>
                                                    <div class="col-md-7">
                                                        
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div45">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                                <tr>
                                                                    <td>1) Advance Payment</td>
                                                                    <td><%=Constants.getCurrency(loginInfo)%> <s:property value="advanced"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>2) Refund Given</td>
                                                                   <td><%=Constants.getCurrency(loginInfo)%> <s:property value="refund"/></td>
                                                                </tr>
                                                                
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="advref2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
										                   			
								<div id="advrefg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
										                   			
										                   			
										                   			
										                   			   
                   			</div>
                   		</div>
                   </div>
                </div>
                <div class="bhoechie-tab-content" id="accountinfot">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printAccExcel()" title="Download CSV file"><i class="fa fa-download"></i> Download Excel</a>
                   			
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                      <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/accountinfo.png" class="img-responsive" />
                                                    </div>
                                                      
                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total: <%=Constants.getCurrency(loginInfo)%> <s:property value="totalPayAll"/> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div46">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                                <tr>
                                                                    <td>1) Payment Received</td>
                                                                    <td><%=Constants.getCurrency(loginInfo)%> <s:property value="paymentrecieved"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>2) Advanced Amount</td>
                                                                   <td><%=Constants.getCurrency(loginInfo)%> <s:property value="advanced"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>3) Refund Amount</td>
                                                                   <td><%=Constants.getCurrency(loginInfo)%> <s:property value="refund"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>4) Expense Amount</td>
                                                                   <td><%=Constants.getCurrency(loginInfo)%> <s:property value="expenseTotal"/></td>
                                                                </tr> 
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="accountinfo2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
									  	
									  	<div id="accountinfog" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
                <div class="bhoechie-tab-content" id="clinicalviewt">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printClinicExcel()" title="Download CSV file"><i class="fa fa-download"></i> Download Excel</a>
                   			
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                      <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/clinic.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total: <s:property value="totalClinicalView"/> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div8">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                            	<tr>
                                                            		<td>Patient by Department</td>
                                                            	</tr>
                                                            	 <s:iterator value="patientbycondition">
                                                            	 
                                                                <tr>
                                                                    <td><s:property value="treatmentType"/></td>
                                                                    <td><s:property value="id"/></td>
                                                                </tr>
                                                                </s:iterator>
                                                               
                                                                <tr>
                                                                    <td>No. of Priscription Requested</td>
                                                                   <td><s:property value="requestedprescription"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>No. of Investigation Requested</td>
                                                                   <td><s:property value="requestedInvestigation"/></td>
                                                                </tr>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="clinicalview2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>

									 <div id="clinicalviewg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
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
										                <%for(Client client : misChartForm.getPatientbycondition()){%>
											            
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
									                    <%for(Client client : misChartForm.getPatientbycondition()){%>
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
                <div class="bhoechie-tab-content" id="expenset">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printExpenseExcel()" title="Download CSV file"><i class="fa fa-download"></i> Download Excel</a>
                   			
                   			</div>
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                                      <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/expense.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total: <%=Constants.getCurrency(loginInfo)%> <s:property value="totalExpence"/> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div8">
                                                        <table class="table table-no-border m-0">
                                                            <tbody>
                                                             <s:iterator value="expenseList">
                                                                <tr>
                                                                    <td><s:property value="category"/></td>
                                                                    <td><%=Constants.getCurrency(loginInfo)%> <s:property value="count"/></td>
                                                                </tr>
                                                                </s:iterator>
                                                               


                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                   			</div>
                   			<div class="col-lg-7 col-md-7 paddinillll" id="expense2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
                   			<div class="backsetrcolo">
                   				<span>View Chart</span>
                   			</div>
                   			</div>
                   			 		<div id="expenseg" class="responsivemng" style="height: 500px; margin: 0 auto"></div>
								        <SCRIPT type="text/javascript">
								        $(function () {
								         // Create the chart
								         $('#expenseg').highcharts({
								         
								                    chart: {
													      type: 'column'
																				        },
																			        title: {
																			            text: 'Expense'
																			        }, 	
																			        xAxis: {
																			            type: 'category'
																			        },
																			        yAxis: {
																			            title: {
																			                text: 'Expense'
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
														            name: 'Expense Summary',
														            colorByPoint: true,
														            data: [
														                    
													               <%for(Expence expence:misChartForm.getExpenseList()) {%>
 													             {
													                name: '<%=expence.getCategory()%>',
													                y: <%=expence.getCount()%>,
													                drilldown: '<%=expence.getCategory()%>'
													             },
													            <%}%>
													              
													            ]
													        }],
													        drilldown: {
													            series: [
													                    <%for(Expence expence : misChartForm.getExpenseList()){%>
													                    {
													                			name: '<%=expence.getCategory()%>',
													                			id: '<%=expence.getCategory()%>',
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
                <div class="bhoechie-tab-content" id="patientviewt">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="backsetrcolo">
                   			<a href="#" style="color:#fff;" onclick="printPatientwExcel()" title="Download CSV file"><i class="fa fa-download"></i> Download Excel</a>&nbsp;
                   			<a href="#" style="color:#fff;" onclick="printpage()" title="Download pdf file"><i class="fa fa-print"></i> Print </a>
                   			
                   			</div>
                   			
                   			</div>
                                                <div class="col-lg-12 col-md-12 imbot10a">
                                              <div class="col-md-5 hidden-print">
                                                        <img src="mis/assets/images/icon/map.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total: <s:property value="totalPatient"/> </p>
                                                    </div>
                                                </div>
                                              
                                                <div class="col-lg-12 col-md-12 some-content-related-div" style="padding: 8px;">
                                                    <div id="inner-content-div9">
                                                    
                                                        <table class="table table-no-border m-0" id="patientwt">
                                                            <tbody>
                                                            	<s:iterator value="patientbylocation">
                                                                <tr>
                                                                  <td class="print-visible hidden-md hidden-lg"><s:property value="state"/></td>
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
                   			<div class="col-lg-7 col-md-7 paddinillll hidden-print" id="patientview2">
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
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
          
          <!-- printviewbypackage @ruchi -->
          <div class="bhoechie-tab-content" id="patientviewpackaget">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="col-lg-5 col-md-5 col-xs-12 paddinillll minheatset" style="border-right: 1px dashed darkgrey;">
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
                   				<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs paddinillll">
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
                
                
                <!-- Design By Abhi 07-10-2017-->
                <div class="bhoechie-tab-content" id="kpireportst">
                <div class="row">
                   		<div class="col-lg-12 col-md-12 paddinillll">
                   			<div class="tile-widget">
                   			<div class="col-lg-12 col-md-12 col-sm-12 paddinillll">
                   			<div class="form-inline backsetrcolo text-left">
                   				<div class="form-group text-uppercase">
                   					<b>KPI Report </b>
                   				</div>
                   				
                   				
                   				<div style="float: right;">
                   					<!-- <a href="#" class="btn btn-default" title="print" onclick="printDiv('printableArea')"><i class="fa fa-print"></i></a> -->
                   					<div id="collapsebutton" class="nodisp expandcollapse btn btn-small btn-default no-print"><i class="glyphicon glyphicon-minus"></i> Collapse All</div>
    								<div id="expandbutton" class="disp expandcollapse btn btn-small btn-default no-print"><i class="glyphicon glyphicon-plus"></i> Expand All</div>
                   				</div>
                   			</div>
                   			</div>
                                                <%-- <div class="col-lg-12 col-md-12 imbot10a">
                                                      <div class="col-md-5">
                                                        <img src="mis/assets/images/icon/kpi.png" class="img-responsive" />
                                                    </div>

                                                    <div class="col-md-7">
                                                        <p class="standard-section-data-label">Total: <s:property value="totalPatient"/> </p>
                                                    </div>
                                                </div> --%>
                                              
                                                <div id="printableArea" class="col-lg-12 col-md-12 some-content-related-div" style="padding: 0px;">
                                                    <div id="inner-content-div9">
													    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin-bottom: 0px;">
													    <%int x=1; %>
													      <s:iterator value="kpilist">
													      <div class="panel panel-default">
													      	<div class="col-lg-12 col-md-12 col-xs-12" style="border-bottom: 1px solid rgba(221, 221, 221, 0.18);padding: 3px 0px 3px 0px;">
													      		<div class="panel-heading" role="tab" id="heading<%=x%>">
													          <h4 class="panel-title">
													          <div class="col-lg-10 col-md-10 col-sm-10">
													          	<a class="inlisettext text-uppercase" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse<%=x%>" aria-expanded="true" aria-controls="collapse<%=x%>">
													           <%=x%> <s:property value="kpiarea"/> - <s:property value="kpiindicator"/>
													          </a>
													          </div>
													          <div class="col-lg-2 col-md-2 col-sm-2 text-right">
													          	<div class="form-inline">
													          	<div class="form-group">
													          		<span style="color: green;" data-toggle="tooltip" data-placement="left" data-original-title="demo 10-10-2017 01:11:03"></span>
													          	</div>
													          </div>
													          </div>
													          </h4>
													        </div>
													      	</div>
													        
													        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
													        	<div id="collapse<%=x%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading<%=x%>">
													          <div class="panel-body">
													          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
													          	<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
													          		<div id="indicator<%=x%>"></div>
													          	</div>
													          	<div class="col-lg-4 col-md-4 col-sm-4 text-center minsetheight">
													          	<div style="background-color: #efefef;padding: 7px 0px 7px 0px;border:1px dashed #ddd;">
													          		<label>KPI Formula</label>
													          	<div class="form-group" style="margin-bottom: 0px;">
													          		<small><s:property value="formula_desc"/></small>
													          	</div>
													          	</div>
													          	
													          	<br>
													          	<label>Enter values for KPI</label>
													          	<div class="form-inline">
													          	
													          	<%-- <s:iterator value="no_of_inputList">
													          		<div class="form-group" style="width:17%;">
													          			<input type="hidden" class="akash<s:property value="kpiid"/>" value="<%=y%>"/>
													          			<input type="number" name="input<%=y%><s:property value="kpiid"/>" id="input<%=y%><s:property value="kpiid"/>" class="form-control" placeholder="input <%=y%>" style="width: 100%;">
													          		</div>
													          		<%y++;%>
													          	</s:iterator> --%>
													          	<%int y=1; %>
													          	<s:iterator value="noofinputlist">
													          		<div class="form-group" style="width:18%;">
													          			<input type="hidden" class="akash<s:property value="kpiid"/>" value="<%=y%>"/>
													          			<input type="number" name="input<%=y%><s:property value="kpiid"/>" id="input<%=y%><s:property value="kpiid"/>" class="form-control" value="<s:property value="inputData"/>" placeholder="input<%=y%>" style="width: 100%;">
													          		</div>
													          		<%y++;%>
													          	</s:iterator>
													          </div>
													          <div class="form-group" style="margin-top: 10px;margin-bottom: 0px;">
													          	<a href="#" onclick="calculateKPI(<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>)" class="btn btn-success">Calculate</a>
													          </div>
													          <br>
													         	<div class="form-inline">
													          		<div class="form-group" style="width:18%;">
													          			<input type="number" placeholder="KPI Result" name="result<s:property value="kpiid"/>" id="result<s:property value="kpiid"/>" value="<s:property value="result"/>" class="form-control" style="width: 100%;">
													          		</div>
													           <div class="form-group" style="width:18%;">
													          			<input type="number" placeholder="Target" name="target<s:property value="kpiid"/>" id="target<s:property value="kpiid"/>" value="<s:property value="target"/>"  class="form-control" style="width: 100%;">
													          		</div>
													          </div>
													         
													          <div class="form-group" style="margin-top: 10px;margin-bottom: 0px;">
													          		<s:if test="kpi_dataid==0">
													          			<a href="#" class="btn btn-primary" onclick="saveKPIResult(<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>,'<s:property value="kpi_year"/>','<s:property value="kpi_month"/>',<s:property value="kpi_dataid"/>)">Save</a>
													          		</s:if>
													          		<s:else>
													          			<a href="#" class="btn btn-primary" onclick="saveKPIResult(<s:property value="kpiid"/>,<s:property value="kpiindicatorid"/>,'<s:property value="kpi_year"/>','<s:property value="kpi_month"/>',<s:property value="kpi_dataid"/>)">Edit</a>
													          		</s:else>
													          </div>
													            
													          </div>
													        </div>
													        </div>
													        </div>
													      </div>
													      </div>
													      <%x++; %>
													     </s:iterator>
													      <%-- <div class="panel panel-default">
													      <div class="col-lg-12 col-md-12 col-xs-12" style="border-bottom: 1px solid rgba(221, 221, 221, 0.18);padding: 3px 0px 3px 0px;">
													        <div class="panel-heading" role="tab" id="headingTwo">
													          <h4 class="panel-title">
													         <div class="col-lg-10 col-md-10 col-sm-10">
													          <a  class="collapsed inlisettext" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
													           2. Nursing - Time for Initial assessment Emergency patients
													          </a>
													          </div>
													          <div class="col-lg-2 col-md-2 col-sm-2 text-right">
													          	<div class="form-inline">
													          	<div class="form-group">
													          		<small>No Data</small>
													          	</div>
													          </div>
													          </div>
													          
													          </h4>
													        </div>
													        </div>
													        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
													        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
													          <div class="panel-body">
													            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
													          	<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
													          		<div id=""></div>
													          	</div>
													          	<div class="col-lg-4 col-md-4 col-sm-4 text-right minsetheight">
													          	<label>Enter values for KPI</label>
													          	<div class="form-inline">
													          	<div class="form-group" style="width:17%;">
													          		<input type="text" name="" class="form-control" placeholder="value 1" style="width: 100%;">
													          	</div>
													          	<div class="form-group" style="width:17%;">
													          		<input type="text" name="" class="form-control" placeholder="value 2" style="width: 100%;">
													          	</div>
													          	<div class="form-group">
													          		<a href="#" class="btn btn-primary">save</a>
													          	</div>
													          </div>
													            
													          </div>
													        </div>
													          </div>
													        </div>
													        </div>
													      </div>
													      
													      <div class="panel panel-default">
													      <div class="col-lg-12 col-md-12 col-xs-12" style="border-bottom: 1px solid rgba(221, 221, 221, 0.18);padding: 3px 0px 3px 0px;">
													        <div class="panel-heading" role="tab" id="headingThree">
													          <h4 class="panel-title">
													          <div class="col-lg-10 col-md-10 col-sm-10">
													          <a class="collapsed inlisettext" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
													           3. Medical Admin - Percentage of cases wherein care plan with desired outcomes is documented & counter-signed by the clinician
													          </a>
													          </div>
													          <div class="col-lg-2 col-md-2 col-sm-2 text-right">
													          	<div class="form-inline">
													          	<div class="form-group">
													          		<span style="color: red;" data-toggle="tooltip" data-placement="left" data-original-title="demo 10-10-2017 04:11:03">25%</span>
													          	</div>
													          </div>
													          </div>
													         
													          </h4>
													        </div>
													        </div>
													        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
													        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
													          <div class="panel-body">
													            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
													          	<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
													          		<div id="indicator2"></div>
													          	</div>
													          	<div class="col-lg-4 col-md-4 col-sm-4 text-right minsetheight">
													          	<label>Enter values for KPI</label>
													          	<div class="form-inline">
													          	<div class="form-group" style="width:17%;">
													          		<input type="text" name="" class="form-control" placeholder="value 1" style="width: 100%;">
													          	</div>
													          	<div class="form-group" style="width:17%;">
													          		<input type="text" name="" class="form-control" placeholder="value 2" style="width: 100%;">
													          	</div>
													          	<div class="form-group">
													          		<a href="#" class="btn btn-primary">save</a>
													          	</div>
													          </div>
													            
													          </div>
													        </div>
													          </div>
													        </div>
													        </div>
													      </div>
													      
													      <div class="panel panel-default">
													      	<div class="col-lg-12 col-md-12 col-xs-12" style="border-bottom: 1px solid rgba(221, 221, 221, 0.18);padding: 3px 0px 3px 0px;">
													        <div class="panel-heading" role="tab" id="headingFour">
													          <h4 class="panel-title">
													           <div class="col-lg-10 col-md-10 col-sm-10">
													          <a class="collapsed inlisettext" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
													           4. Nursing - Percentage cases where in screening for nutritional needs has been done
													          </a>
													          </div>
													          <div class="col-lg-2 col-md-2 col-sm-2 text-right">
													          	<div class="form-inline">
													          	<div class="form-group">
													          		<span style="color: green;" data-toggle="tooltip" data-placement="left" data-original-title="demo 10-10-2017 05:11:03">55%</span>
													          	</div>
													          </div>
													          </div>
													          </h4>
													        </div>
													        </div>
													        <div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
													        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
													          <div class="panel-body">
													           <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
													          	<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
													          		<div id="indicator3"></div>
													          	</div>
													          	<div class="col-lg-4 col-md-4 col-sm-4 text-right minsetheight">
													          	<label>Enter values for KPI</label>
													          	<div class="form-inline">
													          	<div class="form-group" style="width:17%;">
													          		<input type="text" name="" class="form-control" placeholder="value 1" style="width: 100%;">
													          	</div>
													          	<div class="form-group" style="width:17%;">
													          		<input type="text" name="" class="form-control" placeholder="value 2" style="width: 100%;">
													          	</div>
													          	<div class="form-group">
													          		<a href="#" class="btn btn-primary">save</a>
													          	</div>
													          </div>
													            
													          </div>
													        </div>
													          </div>
													        </div>
													        </div>
													       </div> --%>
													       
													    </div>
                                                    </div>
                                                </div>
                                                
                                            </div>
                   		</div>
                   </div>
                </div>
           
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
            </div>
        </div>
  </div>
</div>
                                </div>
                                </div>
                            </div>
                        </div><!--
                         /col 
                    --></div><!--
                     /row 

                --></div>

            </section><!--
            / CONTENT 

        --></div><!--
        // Application Content 


          opd patient report      
   

			-->
			<table class="my-table tablexls" id = "opdPatientTable " style="width: 100%;font-size: 8px;display:none;">    
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
                <td><s:property value="dob"/></td>
                 <td><s:property value="invstid"/></td>
                <td><s:property value="clientName"/></td>
                <td><s:property value="whopay"/></td>
                <td><s:property value="diaryUser"/></td>
                <td><s:property value="apmttypetext"/></td>
                <td><s:property value="apmtStatus"/></td>
                <td><s:property value="abrivationid"/></td>
                <td><s:property value="mobNo"/></td>
                <td><s:property value="charges"/></td>
                <td><s:property value="apmtcharges"/></td>
               
                
                </tr>
             </s:iterator> 
             <s:iterator value="opdPatientCancelReport">
             
                <tr>
                <td><s:property value="dob"/></td>
                 <td><s:property value="invstid"/></td>
                <td><s:property value="clientName"/></td>
                <td><s:property value="whopay"/></td>
                <td><s:property value="diaryUser"/></td>
                <td><s:property value="apmttypetext"/></td>
                <td><s:property value="apmtStatus"/></td>
                <td><s:property value="abrivationid"/></td>
                <td><s:property value="mobNo"/></td>
                 <td><s:property value="charges"/></td>
                  <td><s:property value="apmtcharges"/></td>
                
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
		     <th> Daily Summary</th>
		     <th>(<s:property value="fromDate"/> to <s:property value="toDate"/>)</th>
		    </tr>
		    </thead>
				<tbody>
                                                                <tr>
                                                                    <td><b>Total OPD Booked</b></td>
                                                                    <td><s:property value="totalopdseen"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td><b>Total OPD Completed</b></td>
                                                                    <td><s:property value="totalopdcompleted"/></td>
                                                                </tr>
                                                                 <tr>
                                                                    <td><b>Total OPD-DNA</b></td>
                                                                    <td><s:property value="totalopddna"/></td>
                                                                </tr>
                                                                
                                                               <s:iterator value="opdappointmenttype">
                                                                <s:if test="result==0">
                                                                </s:if>
                                                                <s:else>
                                                                <tr>
                                                                	<td> <s:property value="name"/></td>
                                                                    <td><s:property value="result"/></td>
                                                                </tr>
                                                                </s:else>
                                                               </s:iterator>
                                                                
                                                                <tr>
                                                                    <td><b>Total IPD Admission</b></td>
                                                                    <td><s:property value="ipdnewadmission"/></td>
                                                                <tr>

                                                                    <td>Total Patients In-house Today</td>
                                                                    <td><s:property value="inhousepatients"/></td>
                                                                </tr>
                                                                
                                                                 <tr>
                                                                    <td>MLC Admission</td>
                                                                    <td><s:property value="totalmlcaddmission"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>Total Discharged</td>
                                                                     <td><s:property value="dischargepatients"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>Total Death</td>
                                                                     <td><s:property value="totaldeath"/></td>
                                                                </tr>
                                                                
                                                                  <tr>
                                                                    <td>Total DAMA</td>
                                                                     <td><s:property value="totalDAMA"/></td>
                                                                </tr>
                                                                
                                                                 <tr>
                                                                    <td><b>Total OT </b></td>
                                                                     <td><s:property value="otPatientCount"/></td>
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
                                                                     <td><s:property value="totalInvestigation"/></td>
                                                                </tr>
                                                                <!-- pathlab/radiology/maicrobiology/endoscopy/cardiology -->
                                                                
                                                                <tr>
                                                                    <td>Total Pathlab</td>
                                                                     <td><s:property value="totalpathlab"/></td>
                                                                </tr>
                                                                
                                                                <%-- <tr>
                                                                    <td>-Total Radiology</td>
                                                                     <td><s:property value="totalradiology"/></td>
                                                                </tr> --%>
                                                                
                                                                <tr>
                                                                    <td>Total Microbiology</td>
                                                                     <td><s:property value="totalmaicrobiology"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>Total Endoscopy</td>
                                                                     <td><s:property value="totalendoscopy"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>Total Cardiology</td>
                                                                     <td><s:property value="totalcardiology"/></td>
                                                                </tr>
                                                                
                                                               
                                                                <tr>
                                                                    <td>Total CT SCAN</td>
                                                                     <td><s:property value="totalctinvest"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>Total MRI</td>
                                                                     <td><s:property value="totalmricount"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>Total XRAY</td>
                                                                     <td><s:property value="totalxraycount"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>Total USG</td>
                                                                     <td><s:property value="totalsonographycount"/></td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>NON INVASIVE CARDIOLOGY</td>
                                                                     <td><s:property value="totalcardiologycount"/></td>
                                                                </tr>
                                                                
                                                                <!-- <tr>
                                                                    <td>-Total TMT</td>
                                                                     <td></td>
                                                                </tr> -->
                                                                
                                                              
                                                                <tr>
                                                                  <td>Total IPD & OPD Charges Added</td>
                                                                     <td><s:property value="chargeaddedd"/></td>
                                                                </tr>

                                                                 <tr>
                                                                  <td>Total IPD & OPD Payment</td>
                                                                     <td><s:property value="paymentrecieved"/></td>
                                                                </tr>

                                                            </tbody>
 			</table>

         <%-- <table class="my-table tabledaily" id = "dailyReportTable " style="width: 100%;font-size: 8px;display:none;">    
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
              
                <%i=0; %>
               <tr>
                	<td><b>Total OPD</b></td>
                    <td></td>
                	<td></td>
                	<td></td>
                	<td><s:property value="totalopdseen"/></td>
	           </tr>
               <s:iterator value="opdappointmenttype">
	                <tr>
	                	<td><s:property value="name"/></td>
	                	<td></td>
	                	<td></td>
	                	<td></td>
	                	<td><s:property value="result"/></td>
	                </tr>
	                <s:iterator value="opdappointmenttypelist">
		                <tr>
		                	<td><%=++i %></td>
		                	<td><s:property value="clientname"/></td>
		                	<td><s:property value="practname"/></td>
		                	<td></td>
		                	<td></td>
		                </tr>
	               	</s:iterator>
	               	<tr>
	                	<td></td>
	                	<td></td>
	                	<td></td>
	                	<td></td>
	                	<td></td>
	                </tr>
             	</s:iterator>
               
               <tr>
                	<td><b>Total IPD Admission</b></td>
                    <td></td>
                	<td></td>
                	<td></td>
                	<td><s:property value="ipdnewadmission"/></td>
	           </tr>
               <%i=0; %>
               <s:iterator value="ipdnewaddmissionlist">
	                <tr>
	                	<td><%=++i %></td>
	                	<td><s:property value="clientname"/></td>
	                	<td><s:property value="practname"/></td>
	                	<td></td>
	                	<td></td>
	                </tr>
	          </s:iterator>
          		<tr>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </tr>
                
                <tr>
                	<td><b>Total Patients In-house Today</b></td>
                    <td></td>
                	<td></td>
                	<td></td>
                	<td><s:property value="inhousepatients"/></td>
	           </tr>
                
                <s:iterator value="ipdOldPatientList">
	                <tr>
	                	<td><%=++i %></td>
	                	<td><s:property value="clientname"/></td>
	                	<td><s:property value="practname"/></td>
	                	<td></td>
	                	<td></td>
	                </tr>
	          </s:iterator>
          		<tr>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </tr>
               
               
             </tbody> 
 			</table> --%><!--


         Invoic Report 

        --><table class="my-table xlstinvoicesable" style="width: 100%;font-size: 8px;display:none;">
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
									<td><s:property value="payby" /> (<s:property value="payeeName"/>)</td>
									<s:if test="balance==0">
										<td>Paid</td>
									</s:if>

									<s:else>
										
										<td>Not Paid</td>
									</s:else>
									
									<td style="text-align: right;"><%=Constants.getCurrency(loginInfo) %><s:property value="debitAmountx" /></td>
									<td style="text-align: right;"><%=Constants.getCurrency(loginInfo) %><s:property value="creditTotalx" /></td>
									<td style="text-align: right;">(<%=Constants.getCurrency(loginInfo) %><s:property value="discAmmount"/>) <s:property value="discount" />%</td>
									<td style="text-align: right;"><%=Constants.getCurrency(loginInfo) %><s:property value="balancex" /></td> 
								</tr>

							</s:iterator>
						</s:if>
					</tbody>
                  </table><!--

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
		      <th>Prepayment</th>
		       <th>RefundId</th>
		       <th>UHID</th>
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
                
                 <td><s:if test="paymentmode=='prepayment'">0.0</s:if><s:else><s:property value="advref"/></s:else></td>
                 <td><s:if test="paymentmode=='prepayment'"><s:property value="advref"/></s:if><s:else>0.0</s:else></td>
                 <td><s:property value="refinvoiceid"/></td>
                 <td><s:property value="abrivationid"/></td>
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
		
		<!--Procument Report @ -->
		<table class="my-table xlsprocurmenttable" style="width: 100%;font-size: 8px;display:none;">
         
           							 <thead>
											<tr>
												<th>No of GRN with PO</th>
												<th>No of GRN with PO</th>
												<th>Total GRN</th> 
											</tr>
										</thead>
										<tbody>
												<tr>
												 	<td><%=misChartForm.getGrnwithoutpo() %></td>
                                                    <td><%=misChartForm.getGrnwithpo() %></td>
													<td><%=misChartForm.getTotalgrn() %></td>
												</tr>
											
										</tbody>
         </table>   			
         <!--Indent Report @ -->
		<table class="my-table xlsindenttable" style="width: 100%;font-size: 8px;display:none;">
         
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
												 	<td><%=misChartForm.getDirect_transfer() %></td>
                                                    <td><%=misChartForm.getRequest() %></td>
													<td><%=misChartForm.getApproved() %></td>
													<td><%=misChartForm.getRejected() %></td>
                                                    <td><%=misChartForm.getTransfer() %></td>
													<td><%=misChartForm.getReceived() %></td>
													<td><%=misChartForm.getPocreated() %></td>
                                                    <td><%=misChartForm.getPending() %></td>
													<td><%=misChartForm.getReadytotransfer() %></td>
													<td><%=misChartForm.getTotalindent() %></td>
												</tr>
											
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
         
         
         
         
         
         
         
         
<!--


         ============================================
        ============== Vendor JavaScripts ===============
        ============================================= 
      
		
        --><script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
        <script src="mis/assets/js/vendor/jRespond/jRespond.min.js"></script>
        <script src="mis/assets/js/vendor/sparkline/jquery.sparkline.min.js"></script>
        <script src="mis/assets/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
        <script src="mis/assets/js/vendor/animsition/js/jquery.animsition.min.js"></script>
       

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
            });
        </script>

<script>
            $(window).load(function(){
                // Initialize Statistics chart
                var data = [{
                    data: [[1,15],[2,40],[3,35],[4,39],[5,42],[6,50],[7,46],[8,49],[9,59],[10,60],[11,58],[12,74]],
                    label: 'IPD',
                   
                    bars: {
                        show: true,
                        barWidth: 0.6,
                        lineWidth: 0,
                        fill: 0
                    }
                }, {
                    data: [[1,50],[2,80],[3,90],[4,85],[5,99],[6,125],[7,114],[8,96],[9,130],[10,145],[11,139],[12,160]],
                    label: 'OPD',
                    bars: {
                        show: true,
                        barWidth: 0.6,
                        lineWidth: 0,
                        fillColor: { colors: [{ opacity: 0.3 }, { opacity: 0.8}] }
                    }
                }];
                var options = {
                    colors: ['#e05d6f','#61c8b8'],
                    series: {
                        shadowSize: 0
                    },
                    legend: {
                        backgroundOpacity: 0,
                        margin: -7,
                        position: 'ne',
                        noColumns: 2
                    },
                    xaxis: {
                        tickLength: 0,
                        font: {
                            color: '#fff'
                        },
                        position: 'bottom',
                        ticks: [
                            [ 1, 'JAN' ], [ 2, 'FEB' ], [ 3, 'MAR' ], [ 4, 'APR' ], [ 5, 'MAY' ], [ 6, 'JUN' ], [ 7, 'JUL' ], [ 8, 'AUG' ], [ 9, 'SEP' ], [ 10, 'OCT' ], [ 11, 'NOV' ], [ 12, 'DEC' ]
                        ]
                    },
                    yaxis: {
                        tickLength: 0,
                        font: {
                            color: '#fff'
                        }
                    },
                    grid: {
                        borderWidth: {
                            top: 0,
                            right: 0,
                            bottom: 1,
                            left: 1
                        },
                        borderColor: 'rgba(255,255,255,.3)',
                        margin:0,
                        minBorderMargin:0,
                        labelMargin:20,
                        hoverable: true,
                        clickable: true,
                        mouseActiveRadius:6
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: '%s: %y',
                        defaultTheme: false,
                        shifts: {
                            x: 0,
                            y: 20
                        }
                    }
                };

                var plot = $.plot($("#statistics-chart"), data, options);

                $(window).resize(function() {
                    // redraw the graph in the correctly sized div
                    plot.resize();
                    plot.setupGrid();
                    plot.draw();
                });
                // * Initialize Statistics chart


                // Initialize owl carousels
                $('#todo-carousel, #feed-carousel, #notes-carousel').owlCarousel({
                    autoPlay: 5000,
                    stopOnHover: true,
                    slideSpeed : 300,
                    paginationSpeed : 400,
                    singleItem : true,
                    responsive: true
                });

                //* Initialize owl carousels
            });
             //Initialize morris chart
                Morris.Donut({
                    element: 'browser-usage',
                    data: [
                        {label: 'OPD', value: <%=opdper%>, color: '#00A3D8'},
                        {label: 'IPD', value: <%=ipdper%>, color: '#FFC100'},
                        {label: 'OT', value: <%=otper%>, color: '#D9544F'}
                        //{label: 'Internet Explorer', value: 10, color: '#ffc100'},
                       // {label: 'Other', value: 25, color: '#1693A5'}
                    ],
                    resize: true
                });
                //*Initialize morris chart
               
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
       
</body>

</html>

