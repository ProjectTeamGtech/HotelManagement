<!doctype html>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<html class="no-js" lang="">
<!--<![endif]-->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>YUVICARE</title>
<link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<SCRIPT type="text/javascript" src="inventory/js/procurement.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="inventory/js/indentproduct.js"></SCRIPT>
<script type="text/javascript" src="common/js/pagination.js"></script>


<SCRIPT type="text/javascript">
   
   		window.onload= function(){
   		
   				$("#expiry").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
  		};
    </SCRIPT>


<SCRIPT type="text/javascript">
       $(document).ready(function() {

		$("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
	});
    
    </SCRIPT>
<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<%
	if (loginInfo.getUserType() == 2 || loginInfo.isApprove_po() || loginInfo.getJobTitle().equals("Admin")) {
%>

<%
	}
%>

<%
	String hideDiscount = "hidden";
%>
<%
	if (loginInfo.isLmh_po_discount()) {
		hideDiscount = "";
	}
%>
<style>
.padright {
	padding-left: 40px;
}

.table.table {
	color: RGBA(85, 85, 85, 0.85);
	background-color: #fff;
}

.comtitle {
	font-size: 13px;
	background: rgb(102, 153, 204) none repeat scroll 0% 0% !important;
	color: rgb(255, 255, 255);
}

.marbot25 {
	margin-bottom: 25px;
}

.editcompany {
	float: right;
	font-size: 17px;
	color: #fff;
}

.borright {
	border-right: 1px dashed rgb(192, 192, 192);
}

.buildinglogo {
	width: 60%;
	margin-top: 30px;
}

#sidebar .panel-group .panel>.panel-heading+.panel-collapse>.panel-body
	{
	border-top: 0;
	min-height: auto !important;
}

.miheight {
	min-height: auto !important;
}

.my-table th {
	background-color: #424A5D;
	color: #fff !important;
	border-bottom: 1px solid #DFD8D4;
	border-right: 1px solid #DFD8D4;
	border-top: 1px solid #DFD8D4;
	padding: 3px 3px 4px 5px;
	text-align: left;
	font-weight: bold;
	font-size: 11px;
	background-size: 100% 100%;
}

.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th,
	.table>thead>tr>td, .table>thead>tr>th {
	padding: 1px 7px 1px 7px !important;
}

.sidebar-xs #header .branding>a {
	background-position: 6px 10px;
	width: 100% !important;
	font-size: 21px;
	padding: 0px 1px 2px 15px;
	text-align: center;
	color: #fff;
}

.sidebar-xs #header .branding>a>span {
	display: inline-block;
}

.sidebar-xs #header .branding {
	width: 100%;
	padding-top: 7px;
	text-align: center;
}

.theight {
	height: 21px;
}
</style>


<style>
.topheadbaxck {
	background-color: rgb(239, 239, 239);
	padding: 8px 0px;
}

.red {
	color: red;
}

.addcatego {
	float: right;
	margin-top: -40px;
	margin-right: 30px;
}

.baksetp {
	background-color: #616f77;
	color: #fff;
	padding: 0px 5px 0px 5px;
	width: 93px;
	position: absolute;
	text-align: center;
}

.baksetp1 {
	background-color: #16a085;
	color: #fff;
	padding: 0px 5px 0px 5px;
	width: 93px;
	position: absolute;
	text-align: center;
}

.baksetp2 {
	background-color: #daae71;
	color: #fff;
	padding: 0px 5px 0px 5px;
	width: 93px;
	position: absolute;
	text-align: center;
}

.hest {
	height: 20px !important;
}

@media print {
	.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
		.table>tbody>tr>td, .table>tfoot>tr>td {
		padding: 2px 2px 2px 2px !important;
		line-height: 1.42857143;
		vertical-align: top;
		border-top: 1px solid #ddd;
		font-weight: normal;
		font-size: 9px !important;
		border-right: none !important;
		border-left: none !important;
	}
	label {
		font-size: 9px !important;
	}
	p {
		margin: 0 0 2.5px !important;
		font-size: 9px !important;
	}
	.form-group {
		margin-bottom: 0px !important;
	}
	.titleset {
		margin: 0px;
		color: #6699cc !important;
		border-bottom: 1px dashed #efefef;
		font-size: 15px;
		line-height: 20px;
	}
	.table>thead>tr>th {
		vertical-align: bottom;
		border-bottom: transparent !important;
		background-color: #4E7894 !important;
		color: #fff !important;
	}
	h5, .h5 {
		font-size: 9px !important;
	}
}

h5, .h5 {
	font-size: 11px;
}

h5, .h5 {
	margin-top: 4px;
	margin-bottom: 0px;
}

.tocolor {
	font-size: 14px;
	font-weight: bold;
	color: green;
}

b, strong {
	font-weight: 900;
}
</style>

<style>
.topheadbaxck {
	background-color: rgb(239, 239, 239);
	padding: 8px 0px;
}

.red {
	color: red;
}

.setborba {
	background-color: #efefef !important;
	padding-top: 5px !important;
}

.dropdown-menu>.active>a, .dropdown-menu>.active>a:hover, .dropdown-menu>.active>a:focus
	{
	background-image: linear-gradient(to bottom, #777 0, #777 100%)
		!important;
}

.dropdown-menu {
	padding: 0px 0 !important;
	margin: 0px 0 0 !important;
}

ul.dt-button-collection.dropdown-menu>* {
	-webkit-column-break-inside: avoid;
	break-inside: avoid;
	border-bottom: 1px solid rgba(0, 0, 0, 0.5) !important;
}

b, strong {
	font-weight: 900;
}
</style>
<style>
ul.breadcrumb {
	list-style: none;
	background-color: #eee;
}

ul.breadcrumb li {
	display: inline;
	font-size: 15px;
}

ul.breadcrumb li+li:before {
	color: black;
	content: ">\00a0";
}

ul.breadcrumb li a {
	color: #0275d8;
	text-decoration: none;
}

ul.breadcrumb li a:hover {
	color: #01447e;
	text-decoration: underline;
}

ul, ol {
	margin-top: 0 !important;
	margin-bottom: 0px !important;
}

.breadcrumb {
	padding: 0px 0px !important;
	margin-bottom: 0px !important;
}
</style>
</head>



<body id="his" class="appWrapper sidebar-xs-forced">


	<section id="">

		<div class="">

			<div class="">
				<div class="hidden-print">
					<ul class="breadcrumb">
						&nbsp;
						<%
							ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist");
						%>
						<%
							for (Breadcrumbs breadcrumbs : indentflowlist) {
						%>
						<%
							if (breadcrumbs.isIscurrent()) {
						%>
						<li><%=breadcrumbs.getShowingname()%></li>
						<%
							} else {
						%>
						<%
							if (breadcrumbs.getOn()) {
						%>
						<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getShowingname()%></a></li>
						<%
							} else {
						%>
						<li><%=breadcrumbs.getShowingname()%></li>
						<%
							}
						%>
						<%
							}
						%>

						<%
							}
						%>
					</ul>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
					<s:form theme="simple" action="amcCmcDashboardProcurement"
						method="post" id="procurementformtag">
						<s:hidden name="ispodashboard"></s:hidden>
						<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
							<div class="col-lg-2 col-md-2 col-sm-2"
								style="margin-left: -16px"></div>
							<div class="col-lg-10 col-md-10 col-sm-10"
								style="padding-left: 400px">
								<div class="form-inline"></div>
							</div>
						</div>

						<div class="col-md-12" style="padding: 0px;">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="form-inline">
									<div class="form-group">
										<span class="text-uppercase"><b>&nbsp;&nbsp;&nbsp;&nbsp;AMC/CMC
												DASHBOARD</b> &nbsp; - &nbsp;</span>
									</div>
									<div class="form-group">
										<div class="">
											<s:select style="width:25%" list="vendorList" id="vendorlist"
												name="vendor_id" listKey="id" listValue="data" headerKey=""
												headerValue="Select Vendor" cssClass=" chosen-select"></s:select>
										</div>
									</div>
									<div class="form-group hidden" style="width: 18%;">
										<s:textfield name="voucherno" cssClass="form-control"
											placeholder="Search AMC No"></s:textfield>
									</div>
									<div class="form-group" style="width: 12%;">
										<s:textfield id="fromdate" readonly="true" name="fromdate"
											cssClass="form-control" placeholder="From Date"
											cssStyle="width:100%;" />
									</div>
									<div class="form-group" style="width: 12%;">
										<s:textfield name="todate" id="todate" readonly="true"
											cssClass="form-control" placeholder="To Date"
											cssStyle="width:100%;" />
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-primary">Go</button>
										<a type="button" title="Save As XLS"
											onclick="downloadexcelprint()" class="btn btn-primary"><i
											class="fa fa-file-excel-o"></i></a>
										<%
											if (loginInfo.isLmh()) {
										%>
										<a class="btn btn-primary" href="#" onclick="openGrnWithPo(2)">AMC/CMC</a>
										<%
											}
										%>
									</div>
								</div>

							</div>

						</div>
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<div class="col-lg-2 col-xs-2 col-md-2" style="padding: 0px;">
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2" style="padding: 0px;">
							</div>
						</div>
					</s:form>
				</div>

			</div>

			<div class="">
				<table class="table my-table xlstable table-striped table-bordered"
					id="tablesort" style="width: 100%;">
					<thead>

						<tr>
							<th style="width: 3%;">No</th>
							<th style="width: 6%;">AMC/CMC No</th>
							<th style="width: 9%;">AMC/CMC Date</th>
							<th style="width: 5%;">User</th>
							<th style="width: 20%;">Supplier Name</th>
							<th style="width: 8%;">Location</th>
							<th style="width: 9%;text-align: center;">Action</th>
							<th class="" style="width: 3%;text-align: center;">Print</th>
							<%if(loginInfo.getUserType()==2){ %>
                                    	<th  style="width: 3%;text-align: center;"></th>
                                    <%} %>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 0;
						%>
						<s:iterator value="procurementList">

							<tr>
								<td><%=(++i)%></td>
								<td><s:property value="poSequence" /></td>
								<td><s:property value="grndate" /></td>
								<td><s:property value="userid" /></td>
								<td><s:property value="vendor" /> <s:if test="deleted==1">
										<font color="red">(Cancelled)</font>
									</s:if></td>

								<td><s:property value="locationname" /></td>
								<%String bgColor="#daae71"; %>
								<s:if test="iscancel==1">
									<%bgColor="#ea0000"; %>
								</s:if>
								
								<td style="text-align: center;background-color:<%=bgColor%> ;">
									<!-- lokesh --> 
									<s:if test="iscancel==1">
										<a style="color: #fff; padding: 0px 5px 0px 5px; width: 93px; text-align: center;"
											href="#">Cancelled
										</a>
									</s:if> 
									<s:elseif test="confirm==0">
										<a
											onclick="openConfirmPopup(<s:property value="procurementid"/>)"
											style="color: #fff;" href="#">Edit</a>
									</s:elseif> 
									<s:elseif test="gudreceipt==1">
										<a class="baksetp1" style="color: #fff;" href="#"
											onclick="openPopup('grnprintProcurement?id=<s:property value="id"/>')">
											Completed</a>
									</s:elseif> 
									<s:elseif test="gudreceipt==0 && ispocomplete==0">
										<a class="baksetp" style="color: #fff;" href="#"
											onclick="openSamePopup('addtopoProcurement?procurementid=<s:property value="procurementid"/>')">Goods
											Receipt</a>
									</s:elseif> 
									<s:elseif test="gudreceipt==0 && ispocomplete==1">
										<a class="baksetp" style="color: #fff;" href="#"
											onclick="openSamePopup('addtopoProcurement?procurementid=<s:property value="procurementid"/>')">Pending</a>
									</s:elseif> 
									<s:elseif test="gudreceipt==0 && ispocomplete==2">
										<a class="baksetp" style="color: #fff;"
											onclick="openPopup('printconfirmProcurement?id=<s:property value="procurementid"/>')"
											href="#"> <s:if test="workorder==1">
                                    	    		WO CLOSED
                                    	    	</s:if> <s:else>
                                    	    		PO CLOSED
                                    	    	</s:else>

										</a>
									</s:elseif>
								</td>
								<td class="" style="text-align: center;"><s:if test="confirm==1 && gudreceipt==0">
										<%-- <a href="#" onclick="openPopup('printconfirmProcurement?id=<s:property value="procurementid"/>')" onclick="openPrintPopup(<s:property value="procurementid"/>)" ><i class="fa fa-print"></i></a> --%>
										<a href="#"
											onclick="openPopup('printconfirmProcurement?id=<s:property value="procurementid"/>')"><i
											class="fa fa-print" style="padding-left: 13px;"></i></a>
									</s:if> <s:elseif test="confirm==1 && gudreceipt==1 && grnno!=0">
										<a href="#"
											onclick="openPopup('printconfirmProcurement?id=<s:property value="procurementid"/>')"><i
											class="fa fa-print" style="padding-left: 13px;"></i></a>
									</s:elseif> <s:elseif test="confirm==0">
										<a href="#"
											onclick="openPopup('printconfirmProcurement?id=<s:property value="procurementid"/>')"><i
											class="fa fa-print" style="padding-left: 13px;"></i></a>
									</s:elseif></td>
								<%if(loginInfo.getUserType()==2){ %>
                                    	<td style="text-align: center;">
                                    		<s:if test="isgrneditvendor==0">
	                                    		<s:if test="paymentdonestatus==0">
		                                    		<a href="#" onclick="changegrnvendor(<s:property value="procurementid"/>,<s:property value="vendor_id"/>,'<s:property value="vendor"/>','<s:property value="voucherno"/>')"> 
												 		<i class="fa fa-refresh fa-spin" aria-hidden="true"></i>
															<span class="sr-only">Change Vendor</span>
												 	</a>
	                                    		</s:if>
                                    		</s:if>
                                    	</td>
                                    <%} %>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<br /> <br />
			</div>

			<s:form action="amcCmcDashboardProcurement" name="paginationForm"
				id="paginationForm" theme="simple">
				<s:hidden name="voucherno"></s:hidden>
				<s:hidden name="fromdate"></s:hidden>
				<s:hidden name="todate"></s:hidden>
				<s:hidden name="iswithpo"></s:hidden>
				<s:hidden name="vendor_id"></s:hidden>
				<s:hidden name="ispodashboard"></s:hidden>
				<div class="col-lg-12">
					<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
						Total:<label class="text-info"><s:property
								value="totalRecords" /> Records</label>
					</div>
					<%@ include file="/common/pages/pagination.jsp"%>
				</div>
			</s:form>

		</div>



		</div>

	</section>

	<!--/ CONTENT -->



	<!--  Confirm popup-->
	<div id="cnfirm" class="modal fade" role="dialog"
		style="z-index: 10000 !important;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Confirm Popup</h4>
				</div>
				<div class="modal-body">
					<h4>Are you sure you want to send email to supplier?</h4>
					<div class='form-inline'>
						<div class='form-group' style="padding-left: 37px;">
							<input type="button" class="btn btn-primary" value="Yes"
								onclick="sureSub(true)">
						</div>
						<div class='form-group' style="padding-left: 129px;">
							<input type="button" class="btn btn-danger" value="No"
								data-dismiss="modal" onclick="sureSub(false)">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




	<!-- Create PO Modal -->
	<div id="cpo" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Create Purchase Order</h4>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
						<s:form action="savenewpoProcurement" theme="simple" method="post"
							id="poform">
							<s:hidden name="ispodashboard"></s:hidden>
							<table class="table my-table xlstable table-bordered"
								style="width: 100%;" id="mynewtab">
								<thead>
									<tr>
										<th style="width: 45%;">Product Name</th>
										<th>Quantity</th>

									</tr>
								</thead>
								<tbody>
									<tr>
										<td><s:select list="poproductList"
												cssClass="form-control chosen-select"
												name="procurements[0].product_name" id="mainproduct"
												listKey="id" listValue="product_name" headerKey="0"
												headerValue="Select Product"></s:select></td>
										<td><input class="form-control" onkeyup=""
											name="procurements[0].qty" id="txtQty1" type="text"></td>
										<td></td>
									</tr>

								</tbody>

							</table>
						</s:form>
						<div class="col-lg-12 col-xs-12 hidden"
							style="padding: 0px; margin-top: 15px;">
							<div class="col-lg-12 col-md-12 text-right">

								<p>
									Total : Rs.<label id="lblPOTotal">00.00</label>
								</p>
							</div>
						</div>
						<div class="col-lg-12 col-xs-12"
							style="padding: 0px; margin-top: 15px;">
							<div class="col-lg-6 col-md-6 text-left">
								<input value="Add more" onclick="addnewpo('mynewtab')"
									class="btn btn-primary" type="button">
							</div>
							<div class="col-lg-6 col-md-6 text-right" style="padding: 0px;">
								<input value="Create PO" onclick="submitPo()"
									class="btn btn-primary pull-right" type="button">
							</div>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default hidden"
						data-dismiss="modal">Create PO</button>
				</div>
			</div>

		</div>
	</div>




	<div class="modal fade" role="dialog" id="confirmprod">
		<div class="modal-dialog modal-lg" style="width: 80% !important;">

			<!-- Modal content-->
			<div class="modal-content"
				style="margin-left: -100px; margin-right: -100px !important">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						AMC/CMC For :- <span id="vendorname"></span> &nbsp;|&nbsp;
						AMC/CMC No: <span id="pono" class="hidden"></span> <span
							id="poSequence"></span> &nbsp;|&nbsp; AMC/CMC Date: <span
							id="podate"></span>
					</h4>
					<input type="hidden" id="vendorid" value="0" />
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-xs-12 col-md-12 col-lg-12"
						style="padding: 0px;">
						<div class=""
							style="background-color: rgba(239, 239, 239, 0.42); padding: 9px; border: 1px dashed #ddd;">
							<table class="table table-striped table-bordered"
								style="width: 100%;">

								<tbody>
									<tr>
										<td style="border-top: none; width: 15%;"
											id="newwarehosuedivaprovepo"><s:select theme="simple"
												list="warehouseList" name="warehouse" id="warehouse"
												listKey="id" listValue="name" headerKey="0"
												headerValue="Select Store"
												cssClass="form-control chosen-select"
												onchange="setProductofStoreInPO(this.value)"></s:select></td>
										<td id="categorydiv" class="hidden"
											style="border-top: none; width: 15%;"><select
											class="form-control">
												<option>Select Category</option>
										</select></td>
										<td id="subdiv" class="hidden"
											style="border-top: none; width: 15%;"><select
											class="form-control">
												<option>Select SubCategory</option>
										</select></td>
										<td id="proddiv" style="border-top: none; width: 15%;"><label
											style='color: black !important;'>Product</label> <select
											name="" class="form-control chosen-select">
												<option value="0">Select</option>
										</select></td>
										<td style="border-top: none;"><label
											style='color: black !important;'>Rate</label> <input
											type="number" onchange="calculateWithPoDisc(this.value)"
											class="form-control" id="rate" placeholder="Rate"></td>
										<td style="border-top: none;"><label
											style='color: black !important;'>Discount</label> <input
											type="number" onchange="calculateWithPoDisc()"
											class="form-control" id="discount"
											placeholder="Discount in %"></td>
										<td style="border-top: none;"><label
											style='color: black !important;'>Quantity</label> <input
											type="number" onchange="calculateWithPoDisc()"
											class="form-control" id="qty" placeholder="Qty"></td>
										<td style="border-top: none;"><label
											style='color: black !important;'>Total</label> <input
											type="number" readonly="readonly" class="form-control"
											id="xtotal" placeholder="Total"></td>
										<td style="border-top: none;" id="addnewbtndiv"><br>
											<a href="#" onclick="reqnewGrn()" id="addpopnew"
											class="btn btn-success">Add</a></td>
									</tr>
								</tbody>
							</table>
						</div>


						<s:form action="confirmpoProcurement" theme="simple" id="procform"
							method="post">
							<input type="hidden" name="isAmcCMCDash" value="1">
							<s:hidden name="voucherno"></s:hidden>
							<s:hidden name="fromdate"></s:hidden>
							<s:hidden name="todate"></s:hidden>
							<s:hidden name="iswithpo"></s:hidden>
							<s:hidden name="vendor_id"></s:hidden>
							<s:hidden name="ispodashboard"></s:hidden>

							<s:hidden name="ispodashboard"></s:hidden>
							<s:hidden name="proc_id" id="proc_id"></s:hidden>
							<s:hidden name="mailcheck" id="mailcheck"></s:hidden>
							<s:hidden name="venderemail" id="venderemail"></s:hidden>
							<s:hidden name="saveconfirmpo" id="saveconfirmpo" value="0"></s:hidden>
							<table class="table my-table xlstable table-bordered"
								id="prodTable" style="width: 100%;">
								<thead>
									<tr>
									<tr>
										<th style="width: 3%;">Sr No</th>
										<th style="width: 20%;">Product Name</th>
										<th style="width: 7%;">Product Code</th>
										<th style="width: 5%;">GST</th>
										<th style="width: 5%;">Quantity</th>
										<th style="width: 7%;">Rate</th>
										<th style="width: 7%;">Amount</th>
										<th style="width: 7%;">GST Amt</th>
										<th style="width: 5%;">Discount (In %)</th>
										<th style="width: 8%;">Net Amount</th>
										<th style="width: 5%;">Old Stock</th>
										<th style="width: 6%;">Old price</th>
										<th style="width: 7%;">Consumed</th>
										<th style="width: 2%;"></th>
									</tr>
									</tr>
								</thead>

								<tbody id="innerData">

								</tbody>

							</table>

							<table style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 3%;"></th>
										<th style="width: 20%;"></th>
										<th style="width: 7%;"></th>
										<th style="width: 5%;"></th>
										<th style="width: 5%;"></th>
										<th style="width: 7%;"></th>
										<th style="width: 7%;"></th>
										<th style="width: 7%;"></th>
										<th style="width: 5%;"></th>
										<th style="width: 8%;"></th>
										<th style="width: 5%;"></th>
										<th style="width: 6%;"></th>
										<th style="width: 7%;"></th>
										<th style="width: 2%;"></th>
									</tr>
								</thead>
								<tbody id="tfootconfirmpo">

								</tbody>

							</table>

							<div class="form-group" style="margin-top: 20px;">
								<label for="comment">Remark:</label>
								<textarea class="form-control" rows="3" id="remark"
									name="remark"
									style="background-color: rgba(255, 248, 220, 0.41);"></textarea>
							</div>
						</s:form>

					</div>
				</div>
				<div class="modal-footer">
					<input value="Update" onclick="saveCOnfiirm()" id="saveconfimpobtn"
						class="btn btn-success" type="button" style="margin-top: 15px;">
					<input value="Cancel AMC/CMC" id="cancelconfimpobtn" onclick="cancelPO('<s:property value="procurementid"/>')" class="btn btn-danger" type="button" style="margin-top: 15px;">
					<button type="button" class="btn btn-default hidden"
						data-dismiss="modal">Create PO</button>
				</div>
			</div>

		</div>
	</div>



	<div class="modal fade" role="dialog" id="printprod">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Print Request Purchase Order</h4>
				</div>
				<div class="modal-body">
					<div id="page_printer65">
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">

							<div
								class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center hidden"
								style="border-bottom: 1px solid #ddd; margin-bottom: 10px; padding-bottom: 10px;"
								id="letterHead">
								<h4>SHREE NARAYANA PHARMACY</h4>
								<h6 style="margin: 0px;">SHREE NARAYANA HOSPITAL (A unit of
									Health-Tech Chattisgarh Pvt.Ltd), NEAR GANJ MANDI, BEHIND
									SECTOR-5, DEVENDRA NAGAR, PANDRI, RAIPUR-492001 CHHATTISGARH</h6>
								<h6 style="margin: 0px;">Website:http://www.snh.org.in,
									Email:info@snh.org.in, Contact : 0771-3001234/35/36</h6>
							</div>

							<p class="">
								Supplier Name :- <span id="vendorname1"></span>
							</p>
							<table class="table table-bordered" style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 7%;">Sr No</th>
										<th style="width: 45%;">Product Name</th>
										<th style="width: 10%;">Quantity</th>
										<th style="width: 12%;">Rate</th>
										<th style="width: 10%;">Discount</th>
										<th style="width: 10%;">GST</th>
									</tr>
								</thead>
								<tbody id="innerData1">

								</tbody>
							</table>

							<div class="form-group" style="margin-top: 20px;">
								<label for="comment">Remark: <span id="printremark"></span>
								</label style="float: right;"><label for="comment">Sign</label>
							</div>

						</div>
					</div>

				</div>
				<div class="modal-footer">
					<input value="PRINT" class="btn btn-warning" type="button"
						onclick="printDiv('page_printer65');" style="margin-top: 15px;">
					<button type="button" class="btn btn-default hidden"
						data-dismiss="modal">Create PO</button>
				</div>
			</div>

		</div>
	</div>







	<!-- View Product Modal -->
	<div id="viewproduct" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Good Receipt</h4>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-md-12 col-xs-12"
						style="padding-bottom: 15px;">


						<div id="page_printer">
							<div class="text-center hidden-lg hidden-md visible-print"
								style="border-bottom: 1px solid #ddd; margin-bottom: 15px;">
								<h2>SHREE NARAYANA PHARMACY</h2>
								<p style="margin: 0px;">SHREE NARAYANA HOSPITAL (A unit of
									Health-Tech Chattisgarh Pvt.Ltd), NEAR GANJ MANDI, BEHIND
									SECTOR-5, DEVENDRA NAGAR, PANDRI, RAIPUR-492001 CHHATTISGARH</p>
								<p>Website:http://www.snh.org.in, Email:info@snh.org.in,
									Contact : 0771-3001234/35/36</p>
							</div>
							<p class="hidden" style="color: #d9534f;">
								Create Purchase Order (Date:
								<s:property value="date" />
								)
							</p>
							<table class="table my-table xlstable table-bordered hidden"
								style="width: 100%;">
								<thead>
									<tr>

										<th>Product Name</th>
										<th class="hidden">Brand Name</th>
										<th>Supplier Name</th>
										<th>Qty Ordered</th>
										<th>Pack</th>
										<th>Product Type</th>

										<!--<th>Edit</th>
                                    <th>Delete</th>
                                -->
									</tr>
								</thead>
								<tbody id="orderdata">
									<tr>
										<td>Paracitamol</td>
										<td>Cipla</td>
										<td>ABD Pharma pvt.ltd</td>
										<td>20</td>
										<!--<td><a href="#" onclick="editstock(1)"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="deletestockProduct?selectedid=1" onclick="return cnfmdelete()"><i class="fa fa-trash text-danger"></i></a></td>
                                -->
									</tr>

								</tbody>
							</table>

							<s:form action="updateprocurementProcurement" id="poform1">
								<s:hidden name="ispodashboard"></s:hidden>
								<p id="pheadData">
									<b>Supplier Name:</b><span>abc</span> &nbsp; | &nbsp; <b>Voucher
										No:</b><span>123</span> &nbsp; | &nbsp; <b>Voucher Date:</b><span>123</span>
									&nbsp; | &nbsp; <b>Purchase Date:</b><span>123</span>
								</p>
								<table class="table" style="width: 100%;">
									<thead>
										<tr>
											<th>Sr</th>
											<th>Product Name</th>
											<th>HSN No</th>
											<th>Batch No</th>
											<th style="width: 9%;">Expiry Date</th>
											<th>Mfg</th>
											<th>MRP</th>
											<th>Pack</th>
											<th>Product Type</th>
											<th style="width: 9%;">Sale <small>(pu)</small></th>
											<th>GST</th>
											<th>Rate</th>
											<th>Rec.Qty</th>
											<th style="width: 9%;">Shelf No</th>

											<!--<th>Edit</th>
                                    <th>Delete</th>
                                -->
										</tr>
									</thead>
									<tbody id="receiveddata">
										<!--<tr>
                                    <td>1</td>
                                    <td>27/03/2017</td>
                                    <td>V12546</td>
                                    <td>1 / Dfc52</td>
                                    <td>05/2017</td>
                                    <td>6</td>
                                    <td>24/03/2017</td>
                                    <td>Rs.200</td>
                                    <td>Rs.210</td>
                                    <td>Rs.10</td>
                                    <td>18</td>
                                    <td>Block 2</td>
                                    <td><a href="#" onclick="editstock(1)"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="deletestockProduct?selectedid=1" onclick="return cnfmdelete()"><i class="fa fa-trash text-danger"></i></a></td>
                                </tr>
                                -->
										<tr>
											<td>1</td>
											<td><s:property value="date" /></td>
											<td><input type="text" class="form-control"
												required="required" name="voucherno"></td>
											<td>1 / <input type="text" class="form-control"
												required="required" name="batch_no" style="width: 70%;">
											</td>
											<td><input type="text" class="form-control"
												name="expiry_date" id="expiry_date" required="required"
												placeholder="MM/YYYY"></td>
											<td><input type="text" value="0" class="form-control"
												name="vat" required="required"></td>
											<td><s:textfield theme="simple" cssClass="form-control"
													name="purchase_date" id="purchase_date" required="required" /></td>
											<td><input type="text" value="0" class="form-control"
												id="purchase_price" name="purchase_price"
												required="required"></td>
											<td class=""><input type="text"
												onchange="shoeunitprice(this.value)" class="form-control"
												id="mrp" name="mrp" required="required"></td>
											<td><input type="text" class="form-control"
												id="sale_price" name="sale_price" required="required"></td>
											<td><input type="text" class="form-control"
												name="received_qty" required="required"></td>
											<td><input type="text" class="form-control"
												required="required"></td>
											<td>
												<!--<select name="shelf" class="form-control chosen-select">
										    <option value="0">Select Shelf</option>
										    <option value="Block 1">Block 1</option>
										    <option value="Block 2">Block 2</option>
										</select>
                                    --> <s:select theme="simple"
													name="shelf" id="shelf" list="cellList" listKey="name"
													listValue="name" headerKey="0" headerValue="Select Shelf"
													cssClass="form-control chosen-select" />
											</td>
											<!--<td><a href="#" onclick="editstock(1)"><i class="fa fa-edit"></i></a></td>
                                    <td><a href="deletestockProduct?selectedid=1" onclick="return cnfmdelete()"><i class="fa fa-trash text-danger"></i></a></td>
                                -->
										</tr>
									</tbody>
								</table>
								<div class="col-lg-12 col-md-12 col-xs-12"
									style="text-align: right; padding: 0px;">

									<div class="col-lg-10 col-md-10 col-xs-9"
										style="text-transform: uppercase;">
										<h5>Subtotal :</h5>
										<h5>Discount :</h5>
										<h5>CGST :</h5>
										<h5>SGST :</h5>
										<h5>IGST :</h5>
										<h5>SURCHARGE :</h5>
										<h5>DEBIT :</h5>
										<h5>CREDIT :</h5>
										<h5>
											<b>NET Payble Amount :</b>
										</h5>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-3" id="accountDiv">
										<h5>
											Rs.<span>00.00</span>
										</h5>
										<h5>
											Rs.<span>00.00</span>
										</h5>
										<h5>
											Rs.<span id="">00.00</span>
										</h5>
										<h5>
											<b> Rs.<span id="">00.00</span></b>
										</h5>
									</div>


								</div>


							</s:form>
							<div class="col-lg-12 col-md-12 col-xs-12"
								style="padding: 0px; padding-bottom: 15px;">
								<table class="table my-table xlstable table-bordered hidden"
									id="tableInner" style="width: 38%;">
									<thead>
										<tr>
											<td>Product Name</td>
											<td>Batch No</td>
											<td>Expiry Date</td>
											<td>Qty</td>
										</tr>
									</thead>
									<tbody id="innerTBody">
										<!--<tr>
		              <td>Saridon 500</td>
		              <td>AD-120</td>
		              <td>31-04-2017</td>
		              <td>12</td>
		             </tr>
		            -->
										<tr></tr>
									</tbody>
								</table>

							</div>
						</div>









					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="printDiv('page_printer');">Print</button>
					<!--<button type="button" onclick="submitPO()" class="btn btn-primary">Create</button>
			      -->
				</div>
			</div>
		</div>
	</div>
	<!-- Notification Modal -->
	<div id="notificationpopup" class="modal fade" role="dialog"
		style="height: 100%">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div id="notificationform">
					<s:hidden name="ispodashboard"></s:hidden>
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Notification</h4>
					</div>
					<div class="modal-body">
						<div class="">
							<table
								class="table my-table xlstable table-striped table-bordered"
								id="notificationtrcount" style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 3%;">No</th>
										<th style="width: 6%;">PO.No/Old</th>
										<th style="width: 9%;">PO Date</th>
										<th style="width: 15%;">Supplier Name</th>
										<th style="width: 8%;">Location</th>
									</tr>
								</thead>
								<tbody id="notificationbody">

								</tbody>
							</table>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>





	<!-- Modal -->
	<div id="proccancelmodel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<form action="deletepoProcurement" id="cancelpopopupform">
				<s:hidden name="ispodashboard"></s:hidden>
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Are You Sure To Cancel?</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" name="id" id="pro_id">
						<textarea rows="3" class="form-control" id="delete_reason"
							name="delete_reason" placeholder="Cancel Reason"
							style="background-color: beige;"></textarea>
					</div>
					<div class="modal-footer">
						<!--  <input type="submit" class="btn btn-danger"  value="Okay"> -->
						<a class="btn btn-danger" onclick="deletecompltedpo()">Okay</a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- Modal -->
	<div id="proccompletepomodel" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<form action="completependingpoProcurement" id="completepoform">
				<s:hidden name="ispodashboard"></s:hidden>
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Are You Sure To Complete Pending Po?</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" name="id" id="com_pending_pro_id">
						<textarea rows="3" class="form-control" id="completependingpo"
							name="completependingpo" placeholder="Complete Po Reason"
							style="background-color: beige;"></textarea>
					</div>
					<div class="modal-footer">
						<a class="btn btn-danger" onclick="validateReasonofPOComplete()">OK</a>
						<!-- <input type="submit" class="btn btn-danger" onclick="validateReasonofPOComplete()"  value="OK"> -->
					</div>
				</div>
			</form>
		</div>
	</div>

	<div id="changevendorpopup" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm" style="width: 40%;">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Change Vendor</h4>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-md-12 col-xs-12"
						style="padding: 0px; border-bottom: 1px solid #ddd; margin-bottom: 15px;">
						<div class="col-lg-4 col-md-4 col-xs-4" style="margin-top: 22px;">
							<p style="font-size: 15px; color: green;">Supplier Name:</p>
						</div>
						<div class="col-lg-8 col-md-8 col-xs-8" style="margin-top: 22px;">
							<p style="font-size: 15px; color: green;"
								id="changepopup_vendor_div"></p>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12"
						style="padding: 0px; margin-top: 10px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group">
								<label for="email">Select Supplier:</label>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-xs-8">
							<div class="form-group">
								<input type="hidden" id="changepopup_procurementid"
									class="form-control" /> <input type="hidden"
									id="changepopup_vendorid" class="form-control" /> <input
									type="hidden" id="changepopup_voucherno" class="form-control" />
								<s:select style="width:25%" list="vendorList"
									id="changepopup_vendorlist" name="vendor_id" listKey="id"
									listValue="name" headerKey="0" headerValue="Select Vendor"
									cssClass=" chosen-select"></s:select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-danger" id="changevendorbtnid"
							onclick="savechangevendor()" value="Change">
					</div>
				</div>

			</div>
		</div>
	</div>


	<form action="Procurement" name="testform"></form>
	<SCRIPT type="text/javascript">
  
     function calTotal(qty) {
     
       var price= document.getElementById("purchase_price").innerHTML;
        
       var total=parseFloat(price)*qty; 
        
       document.getElementById("total").innerText=total;
     }
  
  </SCRIPT>

	<script>
    function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title></title></head><body>" + divElements + "</body>";

    //window.print();
    //document.body.innerHTML = oldPage;

    //Print Page
    setTimeout(function () {
        print_page();
    }, 2000);

    function print_page() {
        window.print();
    }

    //Restore orignal HTML
    setTimeout(function () {
        restore_page();
    }, 3000);

    function restore_page() {
        document.body.innerHTML = oldPage;
    }
    
    
    
}
    
    
    
    
 	
	</script>
	<script type="text/javascript"
		src="assesmentForms/js/jquery.table2excel.js"> </script>
	<SCRIPT type="text/javascript">
 
    function downloadexcelprint() {
        $(".tablestock").table2excel({
					exclude: ".noExl",
					name: "Procurment",
					filename: "Procurment",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
         }
 
</SCRIPT>

</body>
</html>




