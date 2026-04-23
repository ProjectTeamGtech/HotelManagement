<!doctype html>
<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<html class="no-js" lang="">
<!--<![endif]-->


<%@ taglib prefix="s" uri="/struts-tags"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>YUVICARE</title>
<link rel="icon" type="image/ico" href="assets/images/favicon.ico" />

<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="pharmacy/searchexport/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="pharmacy/searchexport/buttons.bootstrap.css">



<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>

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

.vertical {
	list-style: none;
	padding: 7px;
	border: 1px solid #efefef;
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
<script>
window.onload = function () {
 
     document.addEventListener("contextmenu", function(e){
		e.preventDefault();
		}, false); 
 
};
</script>



<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>

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
						<li><%=breadcrumbs.getName()%></li>
						<%
							} else {
						%>
						<%
							if (breadcrumbs.getOn()) {
						%>
						<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
						<%
							} else {
						%>
						<li><%=breadcrumbs.getName()%></li>
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
					<div class="col-md-6">
						<div class="form-inline">
							<s:form action="manageInventory" theme="simple">
								<div class="form-group">
									<span class="text-uppercase"><b>Supplier List</b> &nbsp;
										- &nbsp;</span>
								</div>
								<div class="form-group">
									<s:textfield id="searchText" name="searchtext"
										cssClass="form-control" placeholder="Search by supplier name,code" />
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-primary">Go</button>
								</div>
								<div class="form-group">
									<a type="button" id="btnxls" title="Save As XLS"
										onclick="printExcel()" class="btn btn-primary"><i
										class="fa fa-file-excel-o"></i></a>

									<button type="button" class="btn btn-warning pull-right"
										onclick="printDiv('page_printer')" style="margin-right: 5px;">Print</button>
								</div>

							</s:form>

						</div>
					</div>

					<div class="col-md-6 text-right">
						<%
							if (loginInfo.isSupplier_add() || loginInfo.getUserType() == 2) {
						%>
						<a href="#" class="btn btn-primary" type="button"
							onclick="checklocationforsupplieradd()">Add Supplier</a>
						<%
							}
						%>
					</div>
				</div>
				<div class="">
					<div id="page_printer">
						<table class="table my-table xlstable table-bordered"
							style="width: 100%;">
							<thead>
								<tr>
									<th width="width: 2%;">Sr.No</th>
									<th>GST No</th>
									<th>Supplier Name</th>
									<th>Supplier Code</th>
									<th style="width: 40%;">Address</th>
									<th>State</th>
									<th>City</th>
									<th>Email</th>
									<th class="hidden">Phone No</th>
									<th>Mobile No</th>
									<th class="hidden">Product Name</th>
									<th>Credit days</th>
									<!--  <th>Delivery Time</th> -->
									<th class="hidden-print">Edit</th>
									<!--  <th class="hidden-print">Delete</th> -->
								</tr>
							</thead>

							<tbody>
								<%
									int srno = 0;
								%>
								<s:iterator value="vendorList">
									<tr id="<s:property value="id"/>">
										<td><%=(++srno)%></td>
										<td><s:property value="tinno" /></td>
										<td><s:property value="name" /></td>
										<td><s:property value="Suppliercode" /></td>
										<td><s:property value="address" /></td>
										<td><s:property value="stateName" /></td>
										<td><s:property value="city" /></td>
										<td><s:property value="email" /></td>
										<td class="hidden"><s:property value="phone1" /></td>
										<td><s:property value="mobile_pri" /></td>
										<s:if test="brand_name==null">
											<td class="hidden"><a
												href="viewvendorInventory?id=<s:property value="id"/>">Add
													Product</a></td>
										</s:if>
										<s:else>
											<td class="hidden"><a
												href="viewvendorInventory?id=<s:property value="id"/>">View
													Product</a></td>
										</s:else>
										<td><s:property value="creditdays" /></td>
										<%-- <td><s:property value="min_delivery_time"/></td> --%>
										<%
											if (loginInfo.isSupplier_add() || loginInfo.getUserType() == 2) {
										%>
										<td class="hidden-print"><a href="#"
											onclick="editvendor(<s:property value="id"/>)"><i
												class="fa fa-edit"></i></a></td>
										<%--  <td class="hidden-print"><a href="deleteInventory?selectedid=<s:property value="id"/>" onclick="return confirmDelete()"><i class="fa fa-trash"></i></a></td> --%>
										<%
											} else {
										%>
										<td class="hidden-print"></td>
										<%
											}
										%>
									</tr>
								</s:iterator>
							</tbody>

						</table>
						<input type="hidden" name="grnnoedit" id="grnnoedit">
					</div>
					<br>
					<s:form action="manageInventory" name="paginationForm"
						id="paginationForm" theme="simple">
						<s:hidden name="searchtext"></s:hidden>
						<div class="">
							<div class="col-lg-4 col-md-4 text-left" style="padding: 0px;">
								Total:<label class="text-info"><s:property
										value="totalRecords" /> Records </label>
							</div>
							<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
						</div>
					</s:form>



				</div>
			</div>



		</div>

	</section>
	<!--/ CONTENT -->





	<!-- Add Supplier -->
	<div id="addsupplier" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<s:form theme="simple" action="savevendorInventory" id="saveform">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Supplier</h4>
					</div>
					<div class="modal-body">
						<div>

							<s:hidden name="index" value="1"></s:hidden>
							<s:hidden name="productlist" id="productlist"></s:hidden>
							<s:hidden name="id" id="id" value="0"></s:hidden>
							<div class="col-lg-12 col-md-12 col-xs-12"
								style="padding: 0px; padding-top: 15px; background-color: #efefef;">
								<div class="col-lg-4 col-md-4">
									<div class="form-group">
										<label for="email">Supplier Name</label> <input type="text"
											class="form-control" name="name" id="name"
											required="required">
									</div>
								</div>
								<div class="col-lg-2 col-md-2">
									<div class="form-group">
										<label for="email">Email</label> <input type="text"
											class="form-control" name="email" id="email">
									</div>
								</div>
								<div class="col-lg-2 col-md-2">
									<div class="form-group">
										<label for="email">Phone No</label> <input type="text"
											class="form-control" name="mobile_pri" id="phone">
									</div>
								</div>
								<div class="col-lg-2 col-md-2">
									<div class="form-group">
										<label for="email">Delivery Time</label> <select
											class="form-control chosen" name="min_delivery_time"
											id="min_delivery">
											<option value="0">Select Day</option>
											<option value="1 Day">1 Day</option>
											<option value="2 Day">2 Day</option>
											<option value="3 Day">3 Day</option>
											<option value="4 Day">4 Day</option>
											<option value="5 Day">5 Day</option>
											<option value="6 Day">6 Day</option>
											<option value="7 Day">7 Day</option>
											<option value="8 Day">8 Day</option>
											<option value="9 Day">9 Day</option>
											<option value="10 Day">10 Day</option>
											<option value="11 Day">11 Day</option>
											<option value="12 Day">12 Day</option>
											<option value="13 Day">13 Day</option>
											<option value="14 Day">14 Day</option>
											<option value="15 Day">15 Day</option>
											<option value="16 Day">16 Day</option>
											<option value="17 Day">17 Day</option>
											<option value="18 Day">18 Day</option>
											<option value="19 Day">19 Day</option>
											<option value="20 Day">20 Day</option>

										</select>
									</div>
								</div>
								<div class="col-lg-2 col-md-2">
									<div class="form-group">
										<label for="email">Credit Days</label> <input type="number"
											class="form-control" name="creditdays" id="creditdays">
									</div>

								</div>

								<div class="col-lg-12 col-md-12 col-xs-12"
									style="padding: 0px; background-color: #efefef; margin-bottom: 15px;">
									<div class="col-lg-4 col-md-4">
										<div class="form-group">
											<label for="email">Address</label> <input type="text"
												class="form-control" name="address" id="address"
												style="width: 160%" required="required">
										</div>
									</div>
									<div class="col-lg-2 col-md-2" style="margin-left: 164px;">
										<div class="form-group" id="statediv">
											<label for="email">State</label>
											<s:select list="stateList"
												cssClass="form-control chosen-select "
												onchange="getCities(this.value)" name="state" id="state"
												listKey="id" listValue="name" headerKey="0"
												headerValue="Select State" />

										</div>
									</div>
									<div class="col-lg-2 col-md-2">
										<div class="form-group" id="citydiv">
											<label for="email">City</label>
											<s:select list="cityList" listKey="id" listValue="city"
												id="city" cssClass="form-control chosen-select"
												headerKey="0" headerValue="Select City" name="city"
												onchange="getStateAjaxnewVendor(this.value)" />

										</div>
									</div>
									<div class="col-lg-2 col-md-2">
										<div class="form-group">
											<label for="email">Post Code</label> <input type="text"
												class="form-control" name="postcode" id="postcode">
										</div>
									</div>

								</div>
								<div class="col-lg-12 col-md-12 col-xs-12"
									style="padding: 0px; background-color: #efefef; margin-bottom: 15px;">
									<div class="col-lg-3 col-md-3">
										<label for="email">GST No</label><span style="color: red">*</span>
										<input type="text" class="form-control" name="tinno"
											id="tinno" required="required">
									</div>
									<div class="col-lg-3 col-md-3">
										<label for="email">Drug License</label><input type="text"
											class="form-control" name="drug" id="drug"
											required="required">
									</div>
							<%
								if (!loginInfo.isKalmegha()) {
							%>
									<div class="col-lg-3 col-md-3">
										<label for="email">Supplier Code</label><span
											style="color: red">*</span> <input type="text"
											class="form-control" name="suppliercode" id="suppliercode"
											required="required">
									</div>
					
						<%
					      } else {
						%>
						<div class="col-lg-3 col-md-3 hidden">
										<label for="email">Supplier Code</label><span
											style="color: red">*</span> <input type="text"
											value="0" class="form-control" name="suppliercode" id="suppliercode"
											required="required">
									</div>
						
						<%
							}
						%>
						      </div>
							</div>
							<!-- lokesh -->
							<label>Bank Details</label>
							<div class="col-lg-12 col-md-12 col-xs-12"
								style="padding: 0px; background-color: #efefef; margin-bottom: 15px;">
								<div class="col-lg-4 col-md-4">
									<div class="form-group">
										<label for="email">Bank Name</label> <input type="text"
											class="form-control" name="bankname" id="bankname">
									</div>
								</div>
								<div class="col-lg-2 col-md-2">

									<label for="email">Branch</label> <input type="text"
										class="form-control" name="bankbranch" id="bankbranch">

								</div>
								<div class="col-lg-2 col-md-2">
									<label for="email">IFSC code</label> <input type="text"
										class="form-control" name="ifsccode" id="ifsccode">
								</div>
								<div class="col-lg-2 col-md-2">
									<div class="form-group">
										<label for="email">Account No</label> <input type="text"
											class="form-control" name="accountno" id="accountno">
									</div>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
								<div class="col-lg-6 col-md-6 " style="padding-top: 15px;">

									<div class="col-lg-12 col-md-12 col-sm-12 hidden"
										style="padding: 0px;">
										<p>Bank Details</p>
										<div class="col-mg-6 col-lg-6" style="padding: 0px;">
											<div class="form-group">
												<label for="email">Bank Name</label> <input type="text"
													class="form-control">
											</div>
											<div class="form-group">
												<label for="email">IFSC Code</label> <input type="text"
													class="form-control">
											</div>
											<div class="form-group">
												<label for="email">Account No</label> <input type="text"
													class="form-control">
											</div>
										</div>
										<div class="col-mg-6 col-lg-6"></div>



									</div>

								</div>
								<div class="col-lg-6 col-md-6 hidden" style="padding-top: 15px;">
									<form class="form-inline">
										<p>Product Allocated</p>
										<div class="six columns">
											<article>
												<input id="search" name="search" class="form-control"
													placeholder="search here" type="text"
													data-list=".default_list" autocomplete="off">
												<div id="scrolltable">
													<ul class="vertical default_list" id="allprod">
														<li><input type="checkbox" onclick="selectAll(this)" />
															Select All</li>

														<s:iterator value="medicineList">

															<li><input class="case" type="checkbox"
																value="<s:property value="id"/>" /> <s:property
																	value="product_name" /></li>
														</s:iterator>

													</ul>
												</div>

											</article>
										</div>

										<!-- Adding Comment For Testing -->


										<!-- By Abhi New Comment -->
										<!-- Adding New Comment ByJitu -->
										<!--1. By Abhi New Comment -->
										<!--2. By Abhi New Comment -->

										<!-- By Jitu New Comment -->

										<button type="submit" class="btn btn-default hidden">
											<i class="fa fa-plus" aria-hidden="true"></i>
										</button>
									</form>
								</div>
				</s:form>
			</div>

		</div>


	</div>
	<div class="modal-footer">
		<!-- <button type="button" onclick="return isvalidVendor()" class="btn btn-primary" style="margin-top: 15px;">Save</button> -->
		<a href="#" onclick="return isvalidVendor()" class="btn btn-primary"
			style="margin-top: 15px;" id="savevend">Save</a>
		<button type="button" class="btn btn-default hidden"
			data-dismiss="modal">Close</button>
	</div>
	</div>

	</div>
	</div>


	<!--Edit Modal-->
	<!-- Modal -->
	<div class="modal fade" id="addvendor" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add Manufacture</h4>
				</div>
				<div class="modal-body row">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-4 control-label">Name<span
									class="red">*</span> :
								</label>
								<div class="col-sm-8">
									<input type="email" class="form-control" id="inputEmail3">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-4 control-label">Address
									:</label>
								<div class="col-sm-8">
									<textarea class="form-control" rows="3"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="inputEmail3" class="col-sm-4 control-label">Email
									ID<span class="red">*</span> :
								</label>
								<div class="col-sm-8">
									<input type="email" class="form-control" id="inputEmail3">
								</div>
							</div>

							<div class="form-group">
								<label for="inputEmail3" class="col-sm-4 control-label">Mobile
									(Primary)<span class="red">*</span> :
								</label>
								<div class="col-sm-8">
									<input type="email" class="form-control" id="inputEmail3">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-4 control-label">Mobile
									(Secondory) :</label>
								<div class="col-sm-8">
									<input type="email" class="form-control" id="inputEmail3">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-4 control-label">Min
									Delivery Time<span class="red">*</span> :
								</label>
								<div class="col-sm-8">
									<input type="email" class="form-control" id="inputEmail3">
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">Add/Edit</button>
				</div>
			</div>
		</div>
	</div>



	<form name='myvenform' method='post'>

		<input type="hidden" name="name"> <input type="hidden"
			name="address"> <input type="hidden" name="email"> <input
			type="hidden" name="phone1"> <input type="hidden"
			name="mobile_pri"> <input type="hidden"
			name="min_delivery_time">
	</form>


	<form name='reloadform' method='post'></form>


	<!-- JS -->
	<script type="text/javascript"
		src="inventory/js/searchtext/javascripts/vendor/jquery.hideseek.min.js"></script>
	<script type="text/javascript"
		src="inventory/js/searchtext/javascripts/vendor/rainbow-custom.min.js"></script>
	<script type="text/javascript"
		src="inventory/js/searchtext/javascripts/vendor/jquery.anchor.js"></script>
	<script src="inventory/js/searchtext/javascripts/initializers.js"></script>
	<!-- JS ends -->

	<script type="text/javascript" src="inventory/js/addvendor.js"></script>

	<script>
    	$(function() {
	    $('#scrolltable').slimScroll({
	   		height : '200px',
	   		railVisible: true,
			alwaysVisible: true
	  });
	
	 });
    </script>
	<script type="text/javascript">
function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Lab report",
					filename: "Supplier List",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}
</script>
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
</body>
</html>
