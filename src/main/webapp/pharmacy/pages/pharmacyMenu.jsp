<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@page import="com.apm.Pharmacy.eu.bi.PharmacyDAO"%>
<%@page import="com.apm.Pharmacy.web.action.PharmacyAction"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YUVICARE</title>
<%@ taglib prefix="s" uri="/struts-tags"  %>

<link rel="icon" href="common/BootstrapNew/img/favicon.ico">
<link href="common/BootstrapNew/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="common/Font-Awesome-master/css/font-awesome.min.css" rel="stylesheet" />
<style>
.appWrapper.aside-fixed #sidebar {
    position: absolute;
    top: 33px !important;
    bottom: 0;
    min-height: auto !important;
}

.counter {
    background-color: #e05d6f;
    color: #fff;
    position: absolute;
    margin-top: -14px;
    padding: 0px 4px 0px 3px;
    border-radius: 50%;
    text-align: center;
}
.badge {
    display: inline-block;
    min-width: 10px;
    padding: 3px 5px;
    font-size: 11px;
    font-weight: bold;
    color: #fff;
    line-height: 1;
    vertical-align: baseline;
    white-space: nowrap;
    text-align: center;
    background-color: #777;
    border-radius: 10px;
    /* right: 67px; */
}
.bg-lightred {
    background-color: #e05d6f !important;
    color: white !important;
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

</head>


<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>

<body class="appWrapper hz-menu">

<div id="preloader">
  <div id="status">&nbsp;</div>
</div>

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
                            <div id="sidebarNav" class="panel-collapse collapse in" role="tabpanel">
                                <div class="panel-body"> 
                                
                                    <!-- ===================================================
			                             ================ NAVIGATION Content ===============
			                             =================================================== -->
                                <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>
		   						
                                    <ul id="navigation">
                                    	<li><a href="onlinerequestpharPharmacy" title="Main Dashboard"><i class="fa fa-users" aria-hidden="true"></i> <span>Online Dash.</span></a></li>
                                    	<li><a href="Pharmacy" title="Main Dashboard"><i class="fa fa-users" aria-hidden="true"></i> <span>Dashboard</span></a></li>
                                       	<li><a href="salepriscPharmacy" title="Sale Product"><i class="fa fa-medkit" aria-hidden="true"></i> <span>Sales</span></a></li>
                                       	<%-- <li class=""><a href="returnmultimedicinePharmacy" title="Sale Return"><i class="fa fa-reply" aria-hidden="true"></i> <span>Multi Return</span></a></li> --%> 
                                        <li class=""><a href="returnipdmedicinePharmacy" title="Sale Return"><i class="fa fa-reply" aria-hidden="true"></i> <span>Sales Return</span></a></li> 
                                        <li><a href="#" title="Return List" onclick="openBlankPopup('returnmedicinedashPharmacy')"> <i class="fa fa-reply" aria-hidden="true"></i> <span>Nurse Return</span></a></li>
                                        <li class="hidden">
                                            <a href="#"><i class="fa fa-book"></i> <span>Books</span></a>
                                            <ul>
                                                <li><a href="#"><i class="fa fa-caret-right"></i> Sale Book</a></li>
                                                <li><a href="#"><i class="fa fa-caret-right"></i> Purchase Book</a></li>
                                            </ul>
                                        </li> 
                                        <li>
                                               <a href="#"><i class="fa fa-bar-chart-o"></i> <span>Daily Reports</span></a>
                                                <ul>
                                                    <!-- <li class="hidden">
                                                        <a href="#"><i class="fa fa-caret-right"></i> Sale Report</a>
                                                        <ul>
                                                        	<li><a href="reportpriscPharmacy"><i class="fa fa-caret-right"></i> Daily Summary</a></li>
                                                        	<li class="hidden"><a href="#"><i class="fa fa-caret-right"></i> Sale Day Book</a></li>
                                                            <li class="hidden"><a href="#"><i class="fa fa-caret-right"></i> M.R. Summary</a></li>
                                                            <li><a href="#"><i class="fa fa-caret-right"></i> Supplier Summary</a></li>
                                                            
                                                        </ul>
                                                    </li>
                                                    <li class="hidden">
                                                        <a href="#"><i class="fa fa-caret-right"></i> Purchase Report</a>
                                                        <ul>
                                                            <li><a href="#"><i class="fa fa-caret-right"></i> Daily Summary</a></li>
                                                            <li><a href="#"><i class="fa fa-caret-right"></i> Supplier Summary</a></li>
                                                        </ul>
                                                    </li> -->
                                                    	
                                                    	<li><a href="reportpriscPharmacy"><i class="fa fa-caret-right"></i> Daily Sale Report</a></li>
                                                   <li><a href="#" onclick="openBlankPopup('dailysalereportPharmacyAjax')"><i class="fa fa-caret-right"></i> Daily Sale New Report</a></li> 
                                                  <!--   <li class="hidden"><a href="supplierledgerreportPharmacy"><i class="fa fa-caret-right"></i> Purchase Report</a></li>
                                                    <li class="hidden"><a href="vatreportPharmacy"><i class="fa fa-caret-right"></i> VAT Report</a></li> -->
	                                                <li><a href="doctorreportPharmacy"><i class="fa fa-caret-right"></i> Doctor Report</a></li>
	                                                <li><a href="doctorreportdatewisePharmacy"><i class="fa fa-caret-right"></i> Date Wise Doctor Report</a></li>
	                                                <li><a href="userreportPharmacy"><i class="fa fa-caret-right"></i> Users Report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('productwisereturnreportPharmacy')"><i class="fa fa-caret-right"></i>Product Wise Return Report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('pharmacysalereportPharmacy')"><i class="fa fa-caret-right"></i>Pharmacy Payment Report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('newdrreportPharmacy')"><i class="fa fa-caret-right"></i>Productwise Doctor Report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('newmedicinedailycountPharmacy')"><i class="fa fa-caret-right"></i>Productwise Count Report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('newgstReportPharmacy')"><i class="fa fa-caret-right"></i>GST report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('hsnwisegstPharmacy')"><i class="fa fa-caret-right"></i>HSN Wise GST report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('pharmacycreditrptReport')"><i class="fa fa-caret-right"></i>Pharmacy Credit Report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('priscagainstsalereportPharmacyAjax')"><i class="fa fa-caret-right"></i>Prescription Against Sale Report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('cancelpharmacybillreportProduct')"><i class="fa fa-caret-right"></i>Pharmacy Cancel Bill Report</a></li>
	                                                <li><a href="#" onclick="openBlankPopup('editpharmacybillreportProduct')"><i class="fa fa-caret-right"></i>Pharmacy Edit Bill Report</a></li>
	                                               <!--  <li class="hidden"><a href="expiryreportPharmacy"><i class="fa fa-caret-right"></i> Expiry Product Report</a></li>
	                                                <li class="hidden"><a href="discpatientreportPharmacy"><i class="fa fa-caret-right"></i> Discharge Patient Report</a></li>
	                                                <li class="hidden"><a href="#" onclick="openPopup('MisChart')"><i class="fa fa-caret-right"></i> MIS Report</a></li> -->
                                                </ul>
                                            </li>
	                                      <%--   <li class="hidden"><a href="#" onclick="openPopup('listProduct')" title="Stock Status"><i class="fa fa-list-ol" aria-hidden="true"></i> <span>Stock Status</span></a></li>
	                                        <li class="hidden"><a href="plistPharmacy" title="Sale List"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> <span>Sale List</span> <span class="badge bg-lightred hidden"><s:property value="requestedCount"/></span></a></li> --%>
	                                        <%if(loginInfo.isLedger() || loginInfo.getSuperadminid()==1) {%>
	                                       
	                                         <li oncontextmenu="return false;" class="hidden"><a href="#" onclick="openSamePopup('listProduct')" title="Inventory"><i class="fa fa-archive" aria-hidden="true"></i> <span>Inventory</span></a></li>
	                                        <%} %>
	                                       
	                                       <%--  <li><a href="#" onclick="openPopup('medicinebarcodePharmacy')" title="Product Barcode"><i class="fa fa-barcode" aria-hidden="true"></i> <span>Barcode</span></a></li> --%>
	                                      <li class="hidden"><a href="#" onclick="openSamePopup('requestTransferPharmacy')" title="Requisition Dashboard"><i class="fa fa-map-signs" aria-hidden="true"></i><span>Requisition</span> <span class="counter hidden"><s:property value="counter"/></span></a></li>
	                                      <%-- <li><a href="#" onclick="openPopup('transferdashboardProduct')" title="Indent"><i class="fa fa-cube" aria-hidden="true"></i> <span>Indent</span></a></li> --%>
	                                     	<li oncontextmenu="return false;" class="hidden"><a href="transferdashboardProduct" title="Indent"><i class="fa fa-cube" aria-hidden="true"></i> <span>Indent</span></a></li>
	                                     	<li><a href="tempmedicinelistPharmacy" title="Product List"><i class="fa fa-plus-square" aria-hidden="true"></i> <span>Temp Product</span></a></li>
	                                     	<li><a href="#" onclick="openBlankPopup('phardiscountPharmacy')"  > <span>Discount Dashboard</span></a></li>
	                                     	<li><a href="#" onclick="openBlankPopup('tokenrequestdashboardPharmacy')" title="Profile Setting">Token Dashboard</a></li>
	                                      <% if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2){ %>
    	                                    <li><a href="pharmacysettingPharmacy" title="Profile Setting"><i class="fa fa-cog" aria-hidden="true"></i> <span>Setting</span></a></li>
	                                      <%} %>
	                                       <% if(loginInfo.getUserType()==4){ %>
    	                                   <!--  <li><a href="#" onclick="openPharmacyUserProfile()"><i class="fa fa-user"></i>Profile</a></li> -->
	                                      <%} %>
	                                       
	                                      
	                                      </ul>
                                    <!--/ NAVIGATION Content -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </aside>
            
            <!-- My Profile Popup -->
<div id="myProfile" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">My Profile - Secure your account</h4>
      </div>
      <div class="modal-body">
        <div class="col-lg-12 col-xs-12 col-md-12">
        	<div class="col-lg-3 col-md-3">
        		<img src="cicon/user_profile.jpg" class="img-responsive" style="margin-right: auto;"></img>
        	</div>
        	<div class="col-lg-9 col-md-9" style="padding: 0px;">
        		
                                                            <div class="row">
																<input type="hidden" id="user_id">
																<input type="hidden" id="newotp">
																<!-- <input type="hidden" id="user_oldpwd"> -->
                                                                <div class="form-group col-sm-4">
                                                                    <label for="fullname">Full Name</label>
                                                                    <input type="text" class="form-control" id="user_fullname" readonly="" style="background-color: rgba(239, 239, 239, 0.48);">
                                                                </div>


                                                                <div class="form-group col-sm-4">
                                                                    <label for="username">User ID</label>
                                                                    <input type="text" class="form-control" id="user_userid" readonly="" style="background-color: rgba(239, 239, 239, 0.48);">
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                    <label for="username">Department</label>
                                                                    <input type="text" class="form-control" id="user_jobtitle" value="Pharmacist" readonly="" style="background-color: rgba(239, 239, 239, 0.48);">
                                                                </div>
                                                                

                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group col-sm-4">
                                                                    <label for="username">State</label>
                                                                    <input type="text" class="form-control"  id="user_state" value="Chattisgadh" readonly="" style="background-color: rgba(239, 239, 239, 0.48);">
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                	 <label for="username">Location</label>
                                                                    <input type="text" class="form-control" id="user_location" value="OPD Pharmacy" readonly="" style="background-color: rgba(239, 239, 239, 0.48);">
                                                                </div>
																  <div class="form-group col-sm-4" id="phonediv">
                                                                    <label for="fullname">Mobile No</label>
                                                                    <input type="text" class="form-control"  id="user_mobile" maxlength="10" placeholder="Mobile No">
                                                                </div>
                                                            </div>
                                                           
                                                            <div class="row">
																<div class="form-group col-sm-4">
                                                                	 <label for="old-password">Old Password</label>
                                                                   <input type="password" class="form-control" id="user_oldpassword" placeholder="Password">
                                                                  
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                    <label for="new-password">New Password</label>
                                                                    <input type="password" class="form-control" id="user_newpassword" placeholder="New Password">
                                                                </div>
                                                                <div class="form-group col-sm-4">
                                                                    <label for="new-password-repeat">New Password Repeat</label>
                                                                    <input type="password" class="form-control" id="user_confirmpassword" placeholder="Repeat Password">
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                           		<div class="form-group col-sm-4">
                                                                    <label class="checkbox checkbox-custom-alt checkbox-custom-sm m-0" style="color: #cc6161;">
			                                                            <input type="checkbox" id="enable-show"><i></i> Show Passwords
			                                                        </label>
                                                                </div>
                                                            	
                                                            </div>
                                                          
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="updatePharmacyUser()">Save</button>
      </div>
    </div>
  </div>
</div>


<!-- Modal -->
<div id="otpmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Enter OTP</h4>
      </div>
      <div class="modal-body">
        	<input type="text" class="form-control" id="otp" placeholder="Enter OTP" style="background-color: beige;"/>
      </div>
      <div class="modal-footer">
        <input type="button" class="btn btn-danger" onclick="confirmotp()" data-dismiss="modal" value="Confirm">
      </div>
    </div>

  </div>
</div>
            <script>


//Place this plugin snippet into another file in your applicationb
(function ($) {
    $.toggleShowPassword = function (options) {
        var settings = $.extend({
            field: "#password",
            control: "#toggle_show_password",
        }, options);

        var control = $(settings.control);
        var field = $(settings.field)

        control.bind('click', function () {
            if (control.is(':checked')) {
                field.attr('type', 'text');
            } else {
                field.attr('type', 'password');
            }
        })
    };
}(jQuery));

//Here how to call above plugin from everywhere in your application document body
$.toggleShowPassword({
    field: '#password-input',
    control: '#enable-show'
});
$.toggleShowPassword({
    field: '#password-input1',
    control: '#enable-show'
});
$.toggleShowPassword({
    field: '#password-input2',
    control: '#enable-show'
});
</script>
          
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