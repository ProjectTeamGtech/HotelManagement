<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YUVICARE</title>

<link rel="icon" href="common/BootstrapNew/img/favicon.ico">
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="common/Font-Awesome-master/css/font-awesome.min.css" rel="stylesheet" />

<style>
.appWrapper.aside-fixed #sidebar {
    position: absolute;
    top: 33px;
    bottom: 0;
    min-height: auto !important;
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
</style>
 <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
 
 <script type="text/javascript">
 	function openmultieditdashboard() {
 		var testlocation= false;
 		if(document.getElementById("location")){
 			var loctioid = document.getElementById("location").value;
 			if(loctioid==''){
 				testlocation= true;
 				
 			}else if(loctioid=='0'){
 				testlocation= true;
 				
 			}
 		}
 		if(testlocation){
 			jAlert('error', "Please select location from top!", 'Error Dialog');
 			setTimeout(function() {
 				$("#popup_container").remove();
 				removeAlertCss();
 			}, alertmsgduration); 
 		}else{
 			openSamePopup('multipleadjusteditdashboardProduct');
 		}
	}
 </script>
 
</head>
<body class="appWrapper hz-menu">

<div id="preloader">
  <div id="status">&nbsp;</div>
</div>
<% String category=(String)session.getAttribute("category"); 
  if(category==null){
	   category="1";
  }

%>


<aside id="sidebar" class="miheight">
                <div id="sidebar-wrap">
                    <div class="panel-group slim-scroll" role="tablist">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#sidebarNav">
                                        Navigation <i class="fa fa-angle-up"></i>
                                    </a>
                                </h4>
                            </div>
                            <div id="sidebarNav" class="panel-collapse collapse in dropdown" role="tabpanel">
                                <div class="panel-body"> 
                                    <!-- ===================================================
                            ================= NAVIGATION Content ===================
                            ==================================================== -->
                            	<%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
		   						<%int isbloodbank=0; %>
                                    <ul id="navigation" oncontextmenu="return false;">
                                     	<%-- <%if(category.equals("2")){ %>
                                         	<!--<li class=""><a href="#" onclick="openPopup('addPrescriptiondetails')" title="Master"><i class="fa fa-book" aria-hidden="true"></i> <span> Product Master</span></a></li> 
                                        --><%} %> --%>
                                        	<li><a href="manageInventory" title="Supplier's List"><i class="fa fa-users"></i> <span>Supplier's</span></a></li>
                                       <%if(session.getAttribute("isfromcathlab")!=null){ %>
						  					<%  String isfromcathlab =  (String)session.getAttribute("isfromcathlab"); %>
						  					<% if(isfromcathlab.equals("1")){%>
						  							<%isbloodbank=1; %>
         											<li class=""><a href="listProduct?isfromcathlab=1" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a></li>
           								 	<%}else if(isfromcathlab.equals("2")){ %>
           								 			<%isbloodbank=2; %>
           								 	<%}else{ %>
						  						<s:if test="isfromcathlab==1">
						  							<%isbloodbank=1; %>
            							 			<li class=""><a href="listProduct?isfromcathlab=1" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a></li>
            							 		</s:if>
            							 		<s:elseif test="isfromcathlab==2">
            							 			<%isbloodbank=2; %>
            							 		</s:elseif>
           								 		<s:else>
           								 			<%isbloodbank=0; %>
           								 			<li class=""><a href="listProduct?isfromcathlab=0" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a></li>
           								 		</s:else>	
						  					<%} %>
						  				<%}else{ %>
						  					 <s:if test="isfromcathlab==1">
						  					 	<%isbloodbank=1; %>
            							 		<li class=""><a href="listProduct?isfromcathlab=1" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a></li>
            							 	</s:if>
            							 	<s:elseif test="isfromcathlab==2">
            							 		<%isbloodbank=2; %>
            							 	</s:elseif>
           								 	<s:else>
           								 		<%isbloodbank=0; %>
           								 		<li class=""><a href="listProduct?isfromcathlab=0" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a></li>
           								 	</s:else>
						  				<%} %>
                                       
                                       <%if(isbloodbank==2){ %>
									   		<li><a href="requestbloodBloodbank" title="Dashboard"><i class="fa fa-caret-square-o-right" aria-hidden="true"></i> <span>Dashboard</span></a></li>
									   		<li class=""><a href="listProduct?isfromcathlab=2" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a></li>
									   		<li><a href="Procurement" title="Procurement"><i class="fa fa-inr" aria-hidden="true"></i> <span>Procurement</span></a></li>
									   		<li><a href="PoPayment" title="Account"><i class="fa fa-bar-chart" aria-hidden="true"></i> <span>Account</span></a></li>
									   		<li class="">
	                                            	<a href="#"><i class="fa fa-reply" aria-hidden="true"></i>Product Return</a>
	                                                <ul>
	                                                    <li><a href="returnproductdashboardProduct"><i class="fa fa-caret-right"></i> Dashboard</a></li>
	                                                    <li><a href="returnqueProduct"><i class="fa fa-caret-right"></i> Return Que</a></li>
	                                                </ul>
	                                        </li>
	                                        <li class="">
	                                            <a href="#"><i class="fa fa-reply" aria-hidden="true"></i>Catalogue</a>
	                                            <ul>
	                                                <li><a href="Catalogue"><i class="fa fa-caret-right"></i> View Catalogue</a></li>
	                                                <li class="hidden"><a href="manageCatalogue"><i class="fa fa-caret-right"></i> Manage Catalogue</a></li>
	                                            </ul>
	                                        </li>
									   <%}else{ %>   
									   		<li><a href="Procurement" title="Procurement"><i class="fa fa-inr" aria-hidden="true"></i> <span>Procurement</span></a></li>
                                        	<li><a href="PoPayment" title="Account"><i class="fa fa-bar-chart" aria-hidden="true"></i> <span>Account</span></a></li>
                                        	<%if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2) {%>
                                        		<li>
                                            	<a href="#"><i class="fa fa-bar-chart" aria-hidden="true"></i> <span>Report's</span></a>
                                            	<ul>
                                            		<li>
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
                                                			<%if(loginInfo.isBalgopal()){ %>
                                                				<li><a href="" onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=1')"><i class="fa fa-caret-right"></i> Inventory Opening Closing By Catalogue</a></li> 
                                                			<%}else{ %>
                                                				<li><a href="" onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=0')"><i class="fa fa-caret-right"></i> Inventory Opening Closing By Catalogue</a></li> 
                                                			<%} %>
                                                			<li><a href="" onclick="openBlankPopup('supplierpaymentreportProduct')"><i class="fa fa-caret-right"></i> Supplier Payment Report</a></li>
                                                        </ul>
                                            		</li>
                                            		<li>
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
                                            		<li>
                                            			 <a href="#"><i class="fa fa-caret-right"></i> Admin</a>
                                                        <ul>
                                                           <li><a href="#" onclick="openBlankPopup('updeletereportProduct')"><i class="fa fa-caret-right"></i> Update/Cancelled Report</a></li>
                                                           <li><a href="#" onclick="openBlankPopup('usersaccessreportProduct')"><i class="fa fa-caret-right"></i> Users Access Report</a></li>
                                                           <li><a href="#" onclick="openBlankPopup('profitlossreportProduct')"><i class="fa fa-caret-right"></i> Profit and Loss Report</a></li>
                                                        </ul>
                                            		</li>
                                            		<li>
                                            			 <a href="#"><i class="fa fa-caret-right"></i> Cath Lab</a>
                                                        <ul>
                                                           	<li><a onclick="openBlankPopup('patientconsumptionreportProduct?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i>Consumption Report</a></li>
                                                        	<li><a onclick="openBlankPopup('stockreportReport?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i> Stock Report</a></li>
                                                        	<li><a onclick="openBlankPopup('detailgrnreportProcurement?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i>Details GRN Report</a></li>
                                                        	<li><a onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=1&ismonthlyreport=0')" href="#"><i class="fa fa-caret-right"></i> Cathlab Opening Closing</a></li>
                                                        </ul>
                                            		</li>
                                            		
                                                	
                                                	
                                                	
                                                	<!-- <li class="hidden"><a href="productReturnReportProduct"><i class="fa fa-caret-right"></i> Product Return Report</a></li>
                                                	<li class="hidden"><a href="expencereportProduct"><i class="fa fa-caret-right"></i> Expensis Report</a></li> -->
                                                	
                                                	
                                                	
                                                	
                                           		</ul>
                                        		</li>
                                       <%}else{ %>
                                       	<s:if test="inventory_transfer==1">
                                         	<li>
                                            	<a href="#"><i class="fa fa-bar-chart" aria-hidden="true"></i> <span>Report's</span></a>
                                            	<ul>
                                            		<li>
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
                                                			<%if(loginInfo.isBalgopal()){ %>
                                                				<li><a href="" onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=1')"><i class="fa fa-caret-right"></i> Inventory Opening Closing By Catalogue</a></li> 
                                                			<%}else{ %>
                                                				<li><a href="" onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=0')"><i class="fa fa-caret-right"></i> Inventory Opening Closing By Catalogue</a></li> 
                                                			<%} %>
                                                			<li><a href="" onclick="openBlankPopup('supplierpaymentreportProduct')"><i class="fa fa-caret-right"></i> Supplier Payment Report</a></li>
                                                        </ul>
                                            		</li>
                                            		<li>
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
                                            		<li>
                                            			 <a href="#"><i class="fa fa-caret-right"></i> Admin</a>
                                                        <ul>
                                                           <li><a href="#" onclick="openBlankPopup('updeletereportProduct')"><i class="fa fa-caret-right"></i> Update/Cancelled Report</a></li>
                                                           <li><a href="#" onclick="openBlankPopup('usersaccessreportProduct')"><i class="fa fa-caret-right"></i> Users Access Report</a></li>
                                                           <li><a href="#" onclick="openBlankPopup('profitlossreportProduct')"><i class="fa fa-caret-right"></i> Profit and Loss Report</a></li>
                                                        </ul>
                                            		</li>
                                            		<li>
                                            			 <a href="#"><i class="fa fa-caret-right"></i> Cath Lab</a>
                                                        <ul>
                                                           	<li><a onclick="openBlankPopup('patientconsumptionreportProduct?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i>Consumption Report</a></li>
                                                        	<li><a onclick="openBlankPopup('stockreportReport?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i> Stock Report</a></li>
                                                        	<li><a onclick="openBlankPopup('detailgrnreportProcurement?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i>Details GRN Report</a></li>
                                                        	<li><a onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=1&ismonthlyreport=0')" href="#"><i class="fa fa-caret-right"></i> Cathlab Opening Closing</a></li>
                                                        </ul>
                                            		</li>
                                            		<!-- <li class="hidden"><a href="productReturnReportProduct"><i class="fa fa-caret-right"></i> Product Return Report</a></li>
                                                	<li class="hidden"><a href="expencereportProduct"><i class="fa fa-caret-right"></i> Expensis Report</a></li> -->
                                                </ul>
                                        	</li>
                                        </s:if>
                                       <%} %>
                                             <li class="">
	                                            	<a href="#"><i class="fa fa-reply" aria-hidden="true"></i>Product Return</a>
	                                                <ul>
	                                                    <li><a href="returnproductdashboardProduct"><i class="fa fa-caret-right"></i> Dashboard</a></li>
	                                                    <li><a href="returnqueProduct"><i class="fa fa-caret-right"></i> Return Que</a></li>
	                                                </ul>
	                                          </li>
	                                        <li class="">
	                                            <a href="#"><i class="fa fa-reply" aria-hidden="true"></i>Catalogue</a>
	                                            <ul>
	                                                <li><a href="Catalogue"><i class="fa fa-caret-right"></i> View Catalogue</a></li>
	                                                <li class="hidden"><a href="manageCatalogue"><i class="fa fa-caret-right"></i> Manage Catalogue</a></li>
	                                            </ul>
	                                        </li>
	                                        <%if(loginInfo.isAccount() || loginInfo.getSuperadminid()==1) {%>
	                                        	<li class="hidden"><a href="profitlossProduct" title="Profit & Loss"><i class="fa fa-balance-scale" aria-hidden="true"></i> <span>Profit & Loss</span></a></li>
	                                       	<%} %>
	                                      	<li oncontextmenu="return false;"><a href="transferdashboardProduct" title="Indent"><i class="fa fa-cube" aria-hidden="true"></i> <span>Indent</span></a></li>
	                                   	 	<%if(loginInfo.isPharmacy()){ %>
	                                   	 		<li><a href="#"  onclick="openBlankPopup('tempmedicinelistPharmacy')"  title="Product List"><i class="fa fa-plus-square" aria-hidden="true"></i> <span>Temp Product</span></a></li>
	                                     	<%} %>
	                                   	 	<li><a href="#" onclick="openSamePopup('bomkitdashboardProduct')" title="Bom Kit Dashboard"><i class="fa fa-plus-square" aria-hidden="true"></i> <span>BOM Kit</span></a></li>
	                                   	 	<li><a href="#" onclick="openPopup('medicinebarcodePharmacy')" title="Product Barcode"><i class="fa fa-barcode" aria-hidden="true"></i> <span>Barcode</span></a></li>
	                                   	 	
	                                   	 	<%-- <li><a href="#" onclick="openPopup('agreementdashboardProcurement')" title="Agreement Dashboard"><i class="fa fa-file-text-o" aria-hidden="true"></i> <span>Agreement</span></a></li> --%> 
										<%} %>   
										<li><a href="#" onclick="openmultieditdashboard()" title="Multiple Adjust Edit Dashboard"><i class="fa fa-plus-square" aria-hidden="true"></i> <span>Adjust Edit</span></a></li>                             
                                        
                                    </ul>
                                    <!--/ NAVIGATION Content -->
                                </div>
                                
                                
                            </div>
                        </div>
                    </div>

                </div>
               
                 

            </aside>

    
    <!--Preloader -->
	<script>
      	$(window).on('load', function() { // makes sure the whole site is loaded 
		  $('#status').fadeOut(); // will first fade out the loading animation 
		  $('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website. 
		  $('body').delay(350).css({'overflow':'visible'});
		})
      </script>
    

</body>
</html>