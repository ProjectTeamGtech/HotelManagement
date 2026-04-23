<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
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
<script type="text/javascript" src="pharmacy/js/pharmacy.js"></script>
 <%
									LoginInfo loginInfo_pharmacymenu = LoginHelper.getLoginInfo(request);
		   						%>
<div class="logo" style="padding-top: 0px;margin-left: 10px;"><!-- border:1px solid #343e5033; -->
		<img src="manasopd/assets/images/Manas_Yuvicare_Logo.svg"
			style="height: 56px; width: 100px;">
	</div>
<div class='navbrcontainer'>
	
	<ul class="notranslate">
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="onlinerequestpharPharmacy" title="Main Dashboard"><i class="fa fa-users" aria-hidden="true"></i> <span>Requested Prescription</span></a>
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="Pharmacy" title="Main Dashboard"><i class="fa fa-users" aria-hidden="true"></i> <span>Dashboard</span></a>
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="salepriscPharmacy" title="Sale Product"><i class="fa fa-medkit" aria-hidden="true"></i> <span>Sales</span></a>
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="returnipdmedicinePharmacy" title="Sale Return"><i class="fa fa-reply" aria-hidden="true"></i> <span>Sales Return</span></a>
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="#" title="Return List" onclick="openBlankPopup('returnmedicinedashPharmacy')"> <i class="fa fa-reply" aria-hidden="true"></i> <span>Nurse Return</span></a>
			</span>
		</li>
		
		<li class='li-x' style="list-style: none;">
			<span class='head' data-toggle="collapse" data-target=".dailyreports"> 
				<i class="fa fa-random"></i> Daily Reports</span>
			<ul class='dailyreports collapse out'>
				<li>
					<a href="reportpriscPharmacy"><i class="fa fa-caret-right"></i> Daily Sale Report</a>
				</li>
				<li>
					<a href="#" onclick="openBlankPopup('dailysalereportPharmacyAjax')"><i class="fa fa-caret-right"></i> Daily Sale New Report</a>
				</li>
				<li><a href="doctorreportPharmacy"><i class="fa fa-caret-right"></i> Doctor Report</a></li>
				<li><a href="doctorreportdatewisePharmacy"><i class="fa fa-caret-right"></i> Date Wise Doctor Report</a></li>
	            <li><a href="userreportPharmacy"><i class="fa fa-caret-right"></i> Users Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('productwisereturnreportPharmacy')"><i class="fa fa-caret-right"></i>Product Wise Return Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('pharmacysalereportPharmacy?action=0')"><i class="fa fa-caret-right"></i>Pharmacy Payment Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('newdrreportPharmacy')"><i class="fa fa-caret-right"></i>Productwise Doctor Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('newmedicinedailycountPharmacy')"><i class="fa fa-caret-right"></i>Productwise Count Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('newgstReportPharmacy')"><i class="fa fa-caret-right"></i>GST report</a></li>
	            <li><a href="#" onclick="openBlankPopup('hsnwisegstPharmacy')"><i class="fa fa-caret-right"></i>HSN Wise GST report</a></li>
	            <li><a href="#" onclick="openBlankPopup('pharmacycreditrptReport')"><i class="fa fa-caret-right"></i>Pharmacy Credit Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('priscagainstsalereportPharmacyAjax')"><i class="fa fa-caret-right"></i>Prescription Against Sale Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('cancelpharmacybillreportProduct')"><i class="fa fa-caret-right"></i>Pharmacy Cancel Bill Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('editpharmacybillreportProduct')"><i class="fa fa-caret-right"></i>Pharmacy Edit Bill Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('categorywisereportReport')"><i class="fa fa-caret-right"></i>Pharmacy category wise Report</a></li>
	            <li><a href="#" onclick="openBlankPopup('pharmacysalereportPharmacy?action=1')"><i class="fa fa-caret-right"></i>Pharmacy Payment Summary Report</a></li>
	            
	            
	        </ul>
		</li>
		
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="#" onclick="openBlankPopup('tempmedicinelistPharmacy')" title="Product List"><span><i class="fa fa-pencil"></i>Temporary Product</span></a>
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="#" onclick="openBlankPopup('phardiscountPharmacy')"  ><i class="fa fa-percent"></i> <span>Discount Dashboard</span></a>
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="#" onclick="openBlankPopup('tokenrequestdashboardPharmacy')" title="Token Dashboard"><i class="fa fa-money" aria-hidden="true"></i>Token Dashboard</a>
			</span>
		</li>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="#" onclick="openBlankPopup('patientdashboardPharmacy')" title="Patient"><i class="fa fa-user" aria-hidden="true"></i>&nbsp;Patient</a>
			</span>
		</li>
		<% if(loginInfo_pharmacymenu.getSuperadminid()==1 || loginInfo_pharmacymenu.getUserType()==2){ %>
		<li class='li-x' style="list-style: none;">
			<span class='head'>
				<a href="pharmacysettingPharmacy?isfrompharmacy=1" title="Profile Setting"><i class="fa fa-cog" aria-hidden="true"></i> <span>Setting</span></a>
			</span>
		</li>
		<%} %>
		
	</ul>


</div>

<!--Preloader -->
	<script>
      	$(window).on('load', function() { // makes sure the whole site is loaded 
		  $('#status').fadeOut(); // will first fade out the loading animation 
		  $('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website. 
		  $('body').delay(350).css({'overflow':'visible'});
		})
      </script>
      
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

