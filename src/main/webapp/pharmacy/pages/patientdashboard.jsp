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
				
				<div class="col-lg-12 col-md-12 col-sm-12 topheadbaxck">
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="form-inline">
							<s:form action="patientdashboardPharmacy" theme="simple">
								<div class="form-group">
									<span class="text-uppercase"><b>Patient Dashboard</b> &nbsp;
										- &nbsp;</span>
								</div>
								<div class="form-group">
									<s:textfield id="searchText" name="searchText"
										cssClass="form-control" placeholder="Search by patient name" />
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-primary">Go</button>
								</div>
								<div class="form-group">
									<a type="button" id="btnxls" title="Save As XLS"
										onclick="printExcel()" class="btn btn-primary hidden"><i
										class="fa fa-file-excel-o"></i></a>

									<button type="button" class="btn btn-warning pull-right hidden"
										onclick="printDiv('page_printer')" style="margin-right: 5px;">Print</button>
								</div>

							</s:form>

						</div>
					</div>
					
				</div>
				<div class="">
					<div id="page_printer">
						<table class="table my-table xlstable table-bordered"
							style="width: 100%;">
							<thead>
								<tr>
									<th>Sr.No</th>
									<th>Patient Name</th>
									<th>Abrivation</th>
									<th>Address</th>
									<th>Mobile</th>
									<th>Practitioner Name</th>
									<%if(loginInfo.isPhar_patient_edit() || loginInfo.getUserType()==2){ %>
										<th class="hidden-print" style="text-align: center;">Edit</th>
									<%} %>
								</tr>
							</thead>

							<tbody>
								<%
									int srno = 0;
								%>
								<s:iterator value="patientlist">
									<tr>
										<td><%=(++srno)%></td>
										<td><s:property value="fullname" /></td>
										<td><s:property value="abrivationid" /></td>
										<td><s:property value="address" /></td>
										<td><s:property value="mobNo" /></td>
										<td><s:property value="diaryUser" /></td>
										<%if(loginInfo.isPhar_patient_edit() || loginInfo.getUserType()==2){ %>
											<td class="hidden-print" style="text-align: center;">
												<a href="#" style="color: #d9534f;" onclick="editpharmacypatientdashboard(<s:property value="id" />)"> <i class="fa fa-edit" aria-hidden="true" style="color: #d9534f;"></i></a>
											</td>
										<%} %>
										
									</tr>
								</s:iterator>
							</tbody>

						</table>
						
					</div>
					<br>
					<s:form action="patientdashboardPharmacy" name="paginationForm"
						id="paginationForm" theme="simple">
						<s:hidden name="searchText"></s:hidden>
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
	
<div class="modal fade" id="editPatientDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="background-color: rgba(0, 0, 0, 0.61);">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close hidden" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Edit Patient</h4>
      </div>
      <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12">
      		<div class="form-group">
      			<label>Patient Full Name</label>
      			<input type="text" id="editfullname" class="form-control">
      			<input type="hidden" id="editpclinetid">
      		</div>
      		<div class="form-group">
      			<label>Mobile No</label>
      			<input type="number" id="editmobile" class="form-control" maxlength="10">
      		</div>
      		<div class="form-group">
      			<label>Address</label>
      			 <textarea class="form-control" rows="5" id="editaddress" ></textarea>
      		</div>
      		<div class="form-group">
      			<label>Doctor Name</label>
      			<!-- <input type="text" name="addoctor" id="addoctor" class="form-control"> -->
      			<s:select list="pharmacydrlist" listKey="id" id="editdoctor" listValue="name" headerKey="0" headerValue="Select Doctor" cssClass="form-control chosen-select"></s:select>
      		</div>
      		
      		
      	</div>
      
      <div class="modal-footer">
     	 <button type="button" id="addpharpatientbtnid" class="btn btn-success" onclick="updatePharmaClientDashboard()" >Update</button>
      </div>
    </div>
  </div>
</div>
</div>

	</section>
	
	
	
	<!--/ CONTENT -->

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
