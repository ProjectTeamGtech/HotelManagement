<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
.navbrcontainer {
	width: 100%;
	padding: 0px 0px 0px 16px;
	text-align: left;
}

.navbrcontainer li {
	list-style-type: none;
	padding-top: 10% !important;
}

.navbrcontainer ul {
	padding: 0px;
}

li-x {
	list-style: none;
}

.li-x .head {
	cursor: pointer;
	font-size: 12px;
	font-weight: bold;
	color: #16303c;
	text-transform: capitalize;
}
.head i{
padding-right: 15px;
}
.li-x .out {
	padding-left: 10px;
	font-size: 11px;
}

a {
	color: #185c63;
}
.logo{
padding-top: 10px;
}




/* width */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  background: #f1f1f1; 
}
 
/* Handle */
::-webkit-scrollbar-thumb {
  background: #a5c6d6; 
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #555; 
}
</style>
<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "200px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
</script>
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
<link rel="icon" href="common/BootstrapNew/img/favicon.ico">
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="common/Font-Awesome-master/css/font-awesome.min.css" rel="stylesheet" />
 <% String category=(String)session.getAttribute("category"); 
  if(category==null){
	   category="1";
  }

%>
<%
	LoginInfo loginInfo_inventorymenu = LoginHelper.getLoginInfo(request);
%>
<div class="logo" style="padding-top: 0px;margin-left: 10px;"><!-- border:1px solid #343e5033; -->
		<img src="manasopd/assets/images/Manas_Yuvicare_Logo.svg"
			style="height: 56px; width: 100px;">
	</div>
<div class='navbrcontainer'>
	<%int isbloodbank=0; %>
	<ul>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="manageInventory" style="color: #16303c;" title="Supplier's List"><i class="fa fa-users"></i> <span>Supplier's</span></a>
			</span>
		</li>
		<%if(session.getAttribute("isfromcathlab")!=null){ %>
			<%String isfromcathlab =  (String)session.getAttribute("isfromcathlab"); %>
			<% if(isfromcathlab.equals("1")){%>
				<%isbloodbank=1; %>
					<li class='li-x' style="list-style: none;">
						<span class='head'>
							<a href="listProduct?isfromcathlab=1" style="color: #16303c;" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a>
						</span>
					</li>
 				<%}else if(isfromcathlab.equals("2")){ %>
 					<%isbloodbank=2; %>
 				<%}else{ %>
					<s:if test="isfromcathlab==1">
						<%isbloodbank=1; %>
  						<li class='li-x' style="list-style: none;">
							<span class='head'>
								<a href="listProduct?isfromcathlab=1" style="color: #16303c;" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a>
							</span>
						</li>
  					</s:if>
  					<s:elseif test="isfromcathlab==2">
  						<%isbloodbank=2; %>
  					</s:elseif>
 					<s:else>
 						<%isbloodbank=0; %>
 						<li class='li-x' style="list-style: none;">
							<span class='head'>
								<a href="listProduct?isfromcathlab=0" style="color: #16303c;" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a>
							</span>
						</li>
 					</s:else>	
			<%} %>
		<%}else{ %>
			 <s:if test="isfromcathlab==1">
			 	<%isbloodbank=1; %>
  				<li class='li-x' style="list-style: none;">
					<span class='head'>
						<a href="listProduct?isfromcathlab=1" style="color: #16303c;" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a>
					</span>
				</li>
  			</s:if>
  			<s:elseif test="isfromcathlab==2">
  				<%isbloodbank=2; %>
  			</s:elseif>
 			<s:else>
 				<%isbloodbank=0; %>
 				<li class='li-x' style="list-style: none;">
					<span class='head'>
						<a href="listProduct?isfromcathlab=0" style="color: #16303c;" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a>
					</span>
				</li>
 			</s:else>
		<%} %>
		<%if(isbloodbank==2){ %>
			<li class='li-x' style="list-style: none;">
				<span class='head'>
					<a href="manageInventory" style="color: #16303c;" title="Supplier's List"><i class="fa fa-users"></i> <span>Supplier's</span></a>
				</span>
			</li>
			<li class='li-x' style="list-style: none;">
				<span class='head'>
					<a href="requestbloodBloodbank" style="color: #16303c;" title="Dashboard"><i class="fa fa-caret-square-o-right" aria-hidden="true"></i> <span>Dashboard</span></a>
				</span>
			</li>
			<li class='li-x' style="list-style: none;">
				<span class='head'>
					<a href="listProduct?isfromcathlab=2" style="color: #16303c;" title="Stock Status"><i class="fa fa-home" aria-hidden="true"></i> <span>Stock Status</span></a>
				</span>
			</li>
			<li class='li-x' style="list-style: none;">
				<span class='head'>
					<a href="Procurement" style="color: #16303c;" title="Procurement"><i class="fa fa-inr" aria-hidden="true"></i> <span>Procurement</span></a>
				</span>
			</li>
			<li class='li-x' style="list-style: none;">
				<span class='head'>
					<a href="PoPayment"  style="color: #16303c;" title="Account"><i class="fa fa-bar-chart" aria-hidden="true"></i> <span>Account</span></a>
				</span>
			</li>
			<li class='li-x' style="list-style: none;">
				<span class='head' data-toggle="collapse" data-target=".productreturnss"> 
				<i class="fa fa-reply"></i>Product Return</span>
				<ul class='productreturnss collapse out'>
					<li>
						<a style="color: #16303c;" href="returnproductdashboardProduct"><i class="fa fa-caret-right"></i> Dashboard</a>
					</li>
					<li>
						<a style="color: #16303c;" href="returnqueProduct"><i class="fa fa-caret-right"></i> Return Que</a>
					</li>
				</ul>
			</li>
			<li class='li-x' style="list-style: none;">
				<span class='head'>
					<a style="color: #16303c;" href="Catalogue"><i class="fa fa-reply"></i> Catalogue</a>
				</span>
			</li>
		<%}else{ %>
			<li class='li-x' style="list-style: none;">
				<span class='head'>
					<a style="color: #16303c;" href="Procurement" title="Procurement"><i class="fa fa-inr" aria-hidden="true"></i> <span>Procurement</span></a>
				</span>
			</li>
			<%if(loginInfo_inventorymenu.isLmh()){ %>
				<li class='li-x' style="list-style: none;">
					<span class='head'>
						<a style="color: #16303c;" href="amcCmcDashboardProcurement" title="AMC/CMC"><i class="fa fa-inr" aria-hidden="true"></i> <span>AMC/CMC</span></a>
					</span>
				</li>
			<%} %>
			<li class='li-x' style="list-style: none;">
					<span class='head'>
						<a style="color: #16303c;" href="gatepassdashboardProduct" title="Gate Pass"><i class="fa fa-inr" aria-hidden="true"></i> <span>Gate Pass</span></a>
					</span>
				</li>
			<li class='li-x' style="list-style: none;">
				<span class='head'>
					<a style="color: #16303c;" href="PoPayment" title="Account"><i class="fa fa-bar-chart" aria-hidden="true"></i> <span>Account</span></a>
				</span>
			</li>
			<li class='li-x' style="list-style: none;">
				<span class='head' data-toggle="collapse" data-target=".productreturnss"> 
				<i class="fa fa-reply"></i>Product Return</span>
				<ul class='productreturnss collapse out'>
					<li>
						<a style="color: #16303c;" href="returnproductdashboardProduct"><i class="fa fa-caret-right"></i> Dashboard</a>
					</li>
					<li>
						<a style="color: #16303c;" href="returnqueProduct"><i class="fa fa-caret-right"></i> Return Que</a>
					</li>
				</ul>
			</li>
			<%boolean reportaccess=false; %>
			<s:if test="inventory_transfer==1">
				<%reportaccess=true; %>
			</s:if>
			<%if(loginInfo_inventorymenu.getSuperadminid()==1 
				|| loginInfo_inventorymenu.getUserType()==2 
				|| reportaccess
				|| loginInfo_inventorymenu.isInventory_report_access()) {%>
				<li class='li-x' style="list-style: none;">
					<span class='head' data-toggle="collapse" data-target=".dailyreports"> 
					<i class="fa fa-file" aria-hidden="true"></i>Report's</span>
					<ul class='dailyreports collapse out'>
						<li class='li-x' style="list-style: none;">
							<span class='head' data-toggle="collapse" data-target=".accountsreports"> 
							<i class="fa fa-file-text" aria-hidden="true"></i>Accounts</span>
							<ul class='accountsreports collapse out'>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('salereportProduct')"><i class="fa fa-caret-right"></i> Sale Report</a>
								</li>
							<%if(!loginInfo_inventorymenu.isBalgopal()){ %>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('paymentreceivereportProduct')"><i class="fa fa-caret-right"></i> Payment Receive  Report</a>
								</li>
							<%}else{ %>
							    <li class="hidden">
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('paymentreceivereportProduct')"><i class="fa fa-caret-right"></i> Payment Receive  Report</a>
								</li>
							<%} %>
							
								<li>
									<a style="color: #16303c;" href="" onclick="openBlankPopup('itemwisereportProduct')"><i class="fa fa-caret-right"></i>Item Wise Sale Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="" onclick="openBlankPopup('productwisereportProduct')"><i class="fa fa-caret-right"></i>Product Wise Sale Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="" onclick="openBlankPopup('cataloguewisesalereportProduct')"><i class="fa fa-caret-right"></i>Catalogue Wise Sale Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="" onclick="openBlankPopup('vatreportProduct')"><i class="fa fa-caret-right"></i> GST Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="" onclick="openBlankPopup('inventorygstreportProduct')"><i class="fa fa-caret-right"></i> GRN GST REPORT</a>
								</li>
								<%if(loginInfo_inventorymenu.isBalgopal()){ %>
								<li>
									<a style="color: #16303c;" href="" onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=0&ismonthlyreport=1')"><i class="fa fa-caret-right"></i> Inventory Opening Closing</a>
								</li>
								<li>
									<a style="color: #16303c;" href="" onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=0&ismonthlyreport=0')"><i class="fa fa-caret-right"></i> Detailed Inventory Opening Closing</a>
								</li>
								<%}else{ %>
								 <li class="hidden">
									<a style="color: #16303c;" href="" onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=0&ismonthlyreport=1')"><i class="fa fa-caret-right"></i> Inventory Opening Closing</a>
								</li>
								<li class="hidden">
									<a style="color: #16303c;" href="" onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=0&ismonthlyreport=0')"><i class="fa fa-caret-right"></i> Detailed Inventory Opening Closing</a>
								</li>
							    <%} %>
								
								<%if(loginInfo_inventorymenu.isBalgopal()){ %>
									<li>
										<a style="color: #16303c;" href="" onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=1')"><i class="fa fa-caret-right"></i> Inventory Opening Closing By Catalogue</a>
									</li>
								<%}else{ %>
									<li>
										<a style="color: #16303c;" href="" onclick="openBlankPopup('inventoryOpeningClosingBycatalogueProduct?iscalculationbase=0')"><i class="fa fa-caret-right"></i> Inventory Opening Closing By Catalogue</a>
									</li>
								<%} %>
								<li>
									<a style="color: #16303c;" href="" onclick="openBlankPopup('supplierpaymentreportProduct')"><i class="fa fa-caret-right"></i> Supplier Payment Report</a>
								</li>
								<li class="hidden">
									<a style="color: #16303c;" href="" onclick="openBlankPopup('openingClosingByBatchProduct')"><i class="fa fa-caret-right"></i> Opening Closing By Batch Report</a>
								</li>
							</ul>
						</li>	
						<li class='li-x' style="list-style: none;">
							<span class='head' data-toggle="collapse" data-target=".inventoryreports"> 
							<i class="fa fa-file-text" aria-hidden="true"></i>Inventory</span>
							<ul class='inventoryreports collapse out'>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('itemwisestockreportProduct')"><i class="fa fa-caret-right"></i> Item Wise Stock Report</a>
								</li>
								<li class="hidden">
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('bincardreportProduct')"><i class="fa fa-caret-right"></i> Bin Card Old Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('bincardreportnewProduct')"><i class="fa fa-caret-right"></i>Bin Card Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('expirymedicineProduct')"><i class="fa fa-caret-right"></i> Expiry Product Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('productReceivedReportProduct')"><i class="fa fa-caret-right"></i> GRN Report</a>
								</li>
								<li class="hidden">
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('consumptionreportProduct')"><i class="fa fa-caret-right"></i> Consumption Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('patientconsumptionreportProduct?isfromcathlab=0')"><i class="fa fa-caret-right"></i>Consumption Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('transferreportProduct')"><i class="fa fa-caret-right"></i> Indent Statement Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('indentreportProduct')"><i class="fa fa-caret-right"></i> Indent Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('indentnewreportProduct')"><i class="fa fa-caret-right"></i> Indent New Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('departmaterialissueReport')" href="#"><i class="fa fa-caret-right"></i> Department Wise Material Issue Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('departmaterialsummaryReport')" href="#"><i class="fa fa-caret-right"></i> Department Wise Material Summary Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('payableagingReport')" href="#"><i class="fa fa-caret-right"></i> Payable Aging Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('nonmovingitemReport')" href="#"><i class="fa fa-caret-right"></i> Non Moving Item Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('stockreportReport?isfromcathlab=0')" href="#"><i class="fa fa-caret-right"></i> Stock Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('userwisematerialissueReport')" href="#"><i class="fa fa-caret-right"></i> User Wise Material Issue Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('itemwisepurchaseReport')" href="#"><i class="fa fa-caret-right"></i>Item Wise Purchase Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('detailgrnreportProcurement?isfromcathlab=0')"><i class="fa fa-caret-right"></i>Details GRN Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('adjustmentreportReport')" href="#"><i class="fa fa-caret-right"></i>Adjustment Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('editgrnlogreportProduct')" href="#"><i class="fa fa-caret-right"></i>GRN Edit After Stock Transfer Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('pendingPOProductProcurement')" href="#"><i class="fa fa-caret-right"></i>Pending Purchase Order Product Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('vendorratereportProcurement')" href="#"><i class="fa fa-caret-right"></i>Vendor Rate Report</a>
								</li>
								
							</ul>
						</li>	
						<li class='li-x' style="list-style: none;">
							<span class='head' data-toggle="collapse" data-target=".adminreports"> 
							<i class="fa fa-file-text" aria-hidden="true"></i>Admin</span>
							<ul class='adminreports collapse out'>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('updeletereportProduct')"><i class="fa fa-caret-right"></i> Update/Cancelled Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('usersaccessreportProduct')"><i class="fa fa-caret-right"></i> Users Access Report</a>
								</li>
								<li>
									<a style="color: #16303c;" href="#" onclick="openBlankPopup('profitlossreportProduct')"><i class="fa fa-caret-right"></i> Profit and Loss Report</a>
								</li>
							</ul>
						</li>	
						<li class='li-x' style="list-style: none;">
							<span class='head' data-toggle="collapse" data-target=".catlabreports"> 
							<i class="fa fa-file-text" aria-hidden="true"></i>Cath Lab</span>
							<ul class='catlabreports collapse out'>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('patientconsumptionreportProduct?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i>Consumption Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('stockreportReport?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i> Stock Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('detailgrnreportProcurement?isfromcathlab=1')" href="#"><i class="fa fa-caret-right"></i>Details GRN Report</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('inventoryOpeningClosingProduct?isfromcathlab=1&ismonthlyreport=0')" href="#"><i class="fa fa-caret-right"></i> Cathlab Opening Closing</a>
								</li>
							</ul>
						</li>	
						<li class='li-x' style="list-style: none;">
							<span class='head' data-toggle="collapse" data-target=".jobworkreports"> 
							<i class="fa fa-file-text" aria-hidden="true"></i>Job Work Report</span>
							<ul class='jobworkreports collapse out'>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('jobworkissuereportProduct?filter_status=0')" href="#"><i class="fa fa-caret-right"></i>Job Work Issue Register</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('jobworkissuereportProduct?filter_status=1')" href="#"><i class="fa fa-caret-right"></i>Job Work Receipt Register</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('jobworkissuereportProduct?filter_status=2')" href="#"><i class="fa fa-caret-right"></i>Job Work Pending Register</a>
								</li>
								<li>
									<a style="color: #16303c;" onclick="openBlankPopup('jobworkissuereportProduct?filter_status=3')" href="#"><i class="fa fa-caret-right"></i>Job Work Cancelled Register</a>
								</li>
							</ul>
						</li>	
					</ul>
				</li>	
			<%} %>
			<li class='li-x' style="list-style: none;">
				<span class='head'>
					<a style="color: #16303c;" href="Catalogue"><i class="fa fa-th-list" aria-hidden="true"></i> Catalogue</a>
				</span>
			</li>
			<li class='li-x' style="list-style: none;" oncontextmenu="return false;">
				<span class='head'>
					<a style="color: #16303c;" href="transferdashboardProduct" title="Indent"><i class="fa fa-cart-plus" aria-hidden="true"></i> <span>Indent</span></a>
				</span>
			</li>
			<%if(loginInfo_inventorymenu.isPharmacy()){ %>
				<li class='li-x' style="list-style: none;" oncontextmenu="return false;">
					<span class='head'>
						<a style="color: #16303c;" href="#"  onclick="openBlankPopup('tempmedicinelistPharmacy')"  title="Product List"><i class="fa fa-folder-open" aria-hidden="true"></i> <span>Temp Product</span></a>
					</span>
				</li>
			<%} %>
			<% if(isbloodbank==1){%>
			<li class='li-x' style="list-style: none;" oncontextmenu="return false;">
				<span class='head'>
					<a style="color: #16303c;" href="#" onclick="openSamePopup('bomkitdashboardProduct')" title="Bom Kit Dashboard"><i class="fa fa-plus-square" aria-hidden="true"></i> <span>BOM Kit</span></a>
				</span>
			</li>
			<%} %>
			<li class='li-x' style="list-style: none;" oncontextmenu="return false;">
				<span class='head'>
					<a style="color: #16303c;" href="#" onclick="openPopup('medicinebarcodePharmacy')" title="Product Barcode"><i class="fa fa-barcode" style="filter: contrast(100.5);" aria-hidden="true"></i> <span>Barcode</span></a>
				</span>
			</li>
		<%} %>
		<li class='li-x' style="list-style: none;" oncontextmenu="return false;">
			<span class='head'>
				<a style="color: #16303c;" href="#" onclick="openmultieditdashboard()" title="Multiple Adjust Edit Dashboard"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> <span>Adjust Edit</span></a>
			</span>
		</li>
		<li class='li-x' style="list-style: none;" oncontextmenu="return false;">
			<span class='head'>
				<a style="color: #16303c;" href="#" onclick="openSamePopup('jobworkdashboardProduct')" title="Job Work Dashboard"><i class="fa fa-briefcase" aria-hidden="true"></i> <span>Job Work</span></a>
			</span>
		</li>
		<% if(loginInfo_inventorymenu.getSuperadminid()==1 || loginInfo_inventorymenu.getUserType()==2 || loginInfo_inventorymenu.isLmh()){ %>
			<li class='li-x' style="list-style: none;" oncontextmenu="return false;">
				<span class='head'>
					<a style="color: #16303c;" href="pharmacysettingPharmacy?isfrompharmacy=0" title="Profile Setting"><i class="fa fa-cog" aria-hidden="true"></i> <span>Setting</span></a>
				</span>
			</li>
		<%} %>
		
	</ul>


</div>

<script>
      	$(window).on('load', function() { // makes sure the whole site is loaded 
		  $('#status').fadeOut(); // will first fade out the loading animation 
		  $('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website. 
		  $('body').delay(350).css({'overflow':'visible'});
		})
      </script>

