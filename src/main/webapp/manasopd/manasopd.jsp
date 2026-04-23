<!doctype html>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>

<style>

.manasnewipdimg{

    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
    }

html{
height: 48px !important;
}
.goog-te-banner-frame.skiptranslate {
    display: none !important;
    } 
body {
    top: 0px !important;
    min-height: 0px !important; 
    }
    .main-content-inner {
    padding: 0 2px 50px;
}

.fa-plus{
	vertical-align: unset ;
}

::-webkit-scrollbar { 
  width: 0px!important;
} 
</style>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Manas - Yuvicare Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <link rel="shortcut icon" href=" manaslogin/images/Manas_Yuvicare_Circle Favicon.png">
</head>

<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />

<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>
<script type="text/javascript" src="report/js/report.js"></script>
<%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>
    


<script type="text/javascript" src="diarymanagement/js/dayUsers.js"></script>
<script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script>
<script type="text/javascript">







document.addEventListener('readystatechange', event => {
	document.cookie = "googtrans=/en/<%=loginInfo.getRegional_lang()%>";
	
	document.getElementById("newloaderPopup").className="";
	  document.getElementById("newloaderPopup").className="modal fade";
	  $('#newloaderPopup').css('display','none');
	  
	  $('body').removeClass('modal-open');
});
/* 
 $(window).on('load', function() {
	 document.getElementById("newloaderPopup").className="";
	  document.getElementById("newloaderPopup").className="modal fade";
	  $('#newloaderPopup').css('display','none');
	  
	  $('body').removeClass('modal-open');
    
});  */

  $(document).ready(function() {
	 // $('#newloaderPopup').modal('show');
	  isnewopd=1;
  <%String tableid="example";%>
		<%if(loginInfo.getUserType()==5){
			tableid="";
		%>
		 	$( "#commencing" ).datepicker({
		 		 
		 		 dateFormat:'dd/mm/yy',
		 		 yearRange: yearrange,
		 		 minDate : '+1d',
		 		 changeMonth: true,
		 		 changeYear: true	 
		 	});
		actionType=5;
		document.getElementById("closesidebar").className="hidden";
		document.getElementById("actiontype").value = "5";
		puresevaclientname='<%=loginInfo.getFullname()%>';
		 puresevaclientid=<%=loginInfo.getPuresevaclientid()%>;
		var uhid=0;
		  var uhid=document.getElementById("mobhid").value;
		 document.getElementById("oriuhid").value=document.getElementById("mobhid").value; 
		 var dept='<%=loginInfo.getDept()%>';
		if(uhid==0){ 
		$( "#confirmuhid" ).modal( "show" );
		 } 
			 
		
		<%}else{%>
		
		if(document.getElementById("atppopup2")){
			document.getElementById("atppopup2").style.display="none";
		}
		
		
		$( "#commencing" ).datepicker({
			 
			 dateFormat:'dd/mm/yy',
			 yearRange: yearrange,
			 minDate : '30/12/1880',
			 changeMonth: true,
			 changeYear: true	 
		});
		<%}%>
		<%if(loginInfo.getTokenstatus()==1){%>
		tokendisplay();
		<%}else{%>
		showdisplaynewopd();
            <%}%>
		
		$( "#initialdob" ).datepicker({
			 
			 dateFormat:'dd/mm/yy',
			 yearRange: yearrange,
			 minDate : '30/12/1880',
			 changeMonth: true,
			 changeYear: true,
			 maxDate: new Date(new Date().setDate(todayDate))
		 });
		
		/* document.getElementById("newloaderPopup").className="";
		  document.getElementById("newloaderPopup").className="modal fade";
		  $('#newloaderPopup').css('display','none');
		  
		  $('body').removeClass('modal-open'); */
		
	});
/*   document.onreadystatechange = function(){
	     if(document.readyState === 'complete'){
	  document.getElementById("ui-datepicker-div").className="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all notranslate";
  }
  } */
  $(function() {
	    $(".datepicker").datepicker();
	    $('.ui-datepicker').addClass('notranslate');
	});
</script>

<style>
.btn{
border-radius: 0.75rem !important;
}
.btn-primary{
    right: 21px !important;
    top: 8px !important;
    font-size: 14px !important;
    color: #fff !important;
    background-color: #15536E !important;
    border-color: #15536E !important;
    border-radius: 0.75rem !important;
    padding-top: 1px !important;
}

.liwidth{
	width: 19%;
    font-size: 16px;
    text-align: center;
}
.ui-datepicker .ui-widget-content {
    background: #999 none;
}
    .col-1{
        padding-left: 0px;
        padding-right: 0px;
    }
    li span{
   
        font-size:12px;
        color: black;
        vertical-align: middle;
    }
    li h5{
   
   font-size:12px;
   color:#818181;
   vertical-align: middle;
   display: inline;
}
.padding-table-columns th
{
    padding:0 5px 0 0; /* Only right padding*/
}
th{
padding: 10px;
}
td{
padding: 10px;
}
table {
	font-size: 13px;
}
.fa-2x{
font-size: 18px;
}
#ui-datepicker-div{
background-color: #ceecf2 !important;
}
@media only screen and (min-width:1024px) and (max-width:1500px){
li h5{
    font-size: 8px;
color: black;
vertical-align: middle;
display: inline;
}
}
@media only screen and (min-width:1520px) and (max-width:1900px){
    li h5{
        font-size: 10px;
    }
}
@media only screen and (min-width:768px) and (max-width:1000px){
     li h5{
        font-size: 10px;
    }
}
    a{
        color: #15536E;
    }
    a :hover{
        color:#15536E;
    }
    @media only screen and (min-width:1900px){
        .col-lg-11{
            max-width: 84%;
        }
    }
.modal-dialog{
height: 0;

}
.tablecls{
  border-collapse: separate;
  border-spacing: 6px;
}
.tdavail{
	color: white;
    background-color: rgb(53, 160, 173);
    height: 45px !important;
	font-family: "Times New Roman", Times, serif;
	font-size: 19px;
	font-weight: 600;
	width: 150px;
}
.tdbooked{
	color: white;
    background-color: dimgrey;
    height: 45px !important;
	font-family: "Times New Roman", Times, serif;
	font-size: 19px;
	font-weight: 600;
}


.labelhigh{
    background-color: black;
    color: white;
    height: 20px;
    font-size: 14px;
    
}
td{
text-align: center !important;
}
th{
text-align: center !important;
}
 .modal-title{
 font-size: 13px !important;
    padding-top: 0 !important;
 } 
 
 
 @media only screen and (max-width:600px){
#logid{
    margin-left: 35px !important;
    margin-top: 34px !important;
}
#commencing{
width: 31%!important;
}
}
@media only screen and (max-width:320px){
#logid{
    margin-left: 35px !important;
    margin-top: 34px !important;
}
#commencing{
width: 31%!important;
}
}
 #logid{
 margin-left: 330px;
 }
 .in{
 margin-left: 24px!important;
 }
 button.close{
 margin-top: -26px !important;
 }
 
 .blur {
    box-shadow: 0px 0px 20px 20px rgba(255,255,255,1);
    text-shadow: 0px 0px 10px rgba(51, 51, 51, 0.9);
    transform: scale(0.9);
    opacity: 0.6;
}

.btn-primary{
font-size-adjust: 13px!importnat;
}

#priscriptionpopup .modal-lg{
min-width: 95% !important;
}

body { padding-right: 0 !important ;
min-width: 0 !important;
}
<%if(!loginInfo.getRegional_lang().equals("en")){%>
.aftertrans{
margin-left: 143px !important;
}
<%}%>
.michhgt{
margin-top: 7px !important;
    margin-left: 242px;
}
</style>





<body style="font-family: 'Open Sans', sans-serif;margin: 0;padding: 0 !important" onload="setTimeout('seeet();',2000);">
<div style="display: none;" id="google_translate_element"></div>

   <!--  <div id="preloader">
        <div class="loader"></div>
    </div> -->
<%String drids=(String)session.getAttribute("drids"); %>
		<input type="hidden" id="drids" value="<%=drids%>">
<%String uhid=(String)session.getAttribute("mobuseruid");
String hidemobileuser="";
if(loginInfo.getUserType()!=5){
	hidemobileuser="";
}


String hidepatientsearch="";
if(loginInfo.getLoginType().equals("pureseva")){
	hidepatientsearch="hidden";
}
%>

<input type="hidden" id="langbyusr" value="<%=loginInfo.getRegional_lang()%>">
<input type="hidden" id="mobhid" value="<%=uhid%>"/>
<input type="hidden" name="slotId" id="slotId" />
<s:hidden id="actiontype"/>
<s:hidden id="priscsavemr" value="1"></s:hidden>
    <div class="page-container sbar_collapsed">
        <!-- sidebar menu area start -->
        <div class="sidebar-menu">
            <div class="sidebar-header" style="padding-top: 4px;">
                <div class="logo">
                    <img src="liveData/clinicLogo/<s:property value="clinicLogo"/>" class="hidden" alt="logo" style="height: 56px;width: 89px;">
                     <img src="manasopd/assets/images/Manas_Yuvicare_Logo.svg" style="height: 56px;width: 89px;">
                </div>
            </div>
            <div class="main-menu">
                <div class="menu-inner">
                    <nav>
                        <ul class="metismenu" id="menu">
                            <%if((!loginInfo.isCommon_db_clinic())){ %>
	                            <li >
	                                <a href="MainDashBoard" ><img src="manasmaindashboard/images/Home.png"><span style="margin-left: 25px !important;font-size: 13px;">Home</span></a>
	                            </li>
                            <%} %>
                            
                            <% if(loginInfo.isManageclient() && (!loginInfo.isCommon_db_clinic())){%>
	                            <li>
	                                <a href="#" onclick="openBlankPopup('manageClient')">
	                                	<img src="manasmaindashboard/images/Patients.png">
	                                	<span style="margin-left: 14px !important;font-size: 13px;">Patients</span>
	                                </a>
	                            </li>
                            <%} %>
                          
	                            <li>
	                                <a href="#" onclick="openPopup('feedbackFormClient')">
	                                	<img src="cicon/feedback.png" style="width: 26%">
	                                	<span>Feedback</span>
	                                </a>
	                            </li>
                         
                            <%-- <%if(loginInfo.isPharmacy()){ %>
	                            <li>
	                                <a href="#" onclick="openSamePopup('onlinerequestpharPharmacy')">
		                                <img src="manasmaindashboard/images/Pharmacy.png">
		                                <span>Pharmacy</span>
	                                </a>    
	                            </li>
                            <%} %>
                            <% if(loginInfo.isInventory()){%>
	                            <li>
	                                <a href="#" onclick="openSamePopup('listProduct?isfromcathlab=0')">
	                                	<img src="manasmaindashboard/images/Inventory.png"></i>
	                                    <span>Inventory</span>
	                                </a>
	                            </li>
                            <%} %> --%>
                            <% if(loginInfo.isManageipd() && (!loginInfo.isCommon_db_clinic())){%>
	                        	 <%if(loginInfo.isDirect_ipd()){ %>  
	                        	 	<li>
	                        	 		<a href="#" data-toggle="modal" class="colortog" data-target="#wards">
	                        	 			<img src="manasmaindashboard/images/IPD_Icon.png"> 
	                        	 			<span style="margin-left: 12px !important;font-size: 13px;">IPD</span>
	                        	 		</a>
	                        	 	</li>
	                        	 <%}else{ %>
	                        	 	<li>
	                        	 		<a href="#" onclick="opencPopup('IpdDashboard?action=0')">
	                        	 			<img src="manasmaindashboard/images/IPD_Icon.png"> 
	                        	 			<span style="margin-left: 12px !important;font-size: 13px;">IPD</span>
	                        	 		</a>
	                        	 	</li>
	                             <%} %> 
		                    <%} %>
                            <%if(loginInfo.isMisaccess() && (!loginInfo.isCommon_db_clinic())) {%>
                        		<% if(loginInfo.isManagemis()){%>
		                            <li>
		                                <a href="#" onclick="opencPopup('MisChart')">
			                                <img src="manasmaindashboard/images/MIS_Icon.png"> 
			                                <span style="margin-left: 18px !important;font-size: 13px;">MIS</span>
		                                </a>
		                            </li>
                             	<%} %> 
		                    <%} %>
		                     <%
												if (loginInfo.isReport() == true) {
											%>
		                    <li>
	                                <a href="" >
	                                		<img src="manasopd/assets/images/Reportss_Icon.svg" style="filter: invert(1);"> <span style="margin-left: 15px !important;font-size: 13px;">Report</span>
	                                </a>
	                                <ul class="collapse">
                                        <li><a href="#" onclick="openPopup('ChargesRpt')"><i class="fa fa-caret-right"></i> Charges Report</a></li>
                                                            <li><a href="#" onclick="openPopup('invoiceReportChargesRpt')"><i class="fa fa-caret-right"></i> Invoice Report</a></li>
                                                            <li><a href="#" onclick="openPopup('paymentReportChargesRpt')"><i class="fa fa-caret-right"></i> Payment Report Big</a></li>
                                                            <li><a href="#" onclick="openPopup('smallpaymentReportChargesRpt')"><i class="fa fa-caret-right"></i> Payment Report Small</a></li>
                                                            <li><a href="#" onclick="openPopup('PendingPaymentReports')"><i class="fa fa-caret-right"></i> Add Debtors Report</a></li>
                                                            <li><a href="#" onclick="openPopup('invoiceCharges')"><i class="fa fa-caret-right"></i> CA Reports</a></li>
                                                            <li><a href="#" onclick="openPopup('auditorChargesRpt')"><i class="fa fa-caret-right"></i> Auditors Report</a></li>
                                                            <li><a href="#" onclick="openPopup('Doctorsharereport')"><i class="fa fa-caret-right"></i> Doctor Share Report</a></li>
                                                            <li><a href="#" onclick="openPopup('userwisepaymentChargesRpt')"><i class="fa fa-caret-right"></i> User Wise Payment Report</a></li>
                                                            <li><a href="#" onclick="openPopup('ipdSummary')"><i class="fa fa-caret-right"></i> IPD Daily Report</a></li>
                                                            <li><a href="#" onclick="openPopup('ipdopdRefundReportSummary')"><i class="fa fa-caret-right"></i>OPD/IPD Cancel/Refund Report</a></li>
                                                            <li><a href="#" onclick="openPopup('ipdBillRegisterSummary')"><i class="fa fa-caret-right"></i> IPD Bill Register</a></li>
                                                            <li><a href="#" onclick="openPopup('serviceRegisterDetailsSummary')"><i class="fa fa-caret-right"></i> Service Register Details</a></li>
                                                            <li><a href="#" onclick="openPopup('departmentWiseAnalysisReportSummary')"><i class="fa fa-caret-right"></i>Department Wise Analysis Report</a></li>
                                                         
                                        <!-- <li><a href="">some text</a></li> -->
                                    </ul>
	                            </li>
	                            
	                            <%} %>
                            <li style="display:none;">
                                <a href="" ><img src="manasmaindashboard/images/Chat Room.png"> <span>Chat Room
                                        </span></a>  
                            </li>
                            <li style="display:none;">
                                <a href="" ><img src="manasmaindashboard/images/Calendar.png"> <span>Calendar
                                        </span></a>
                                
                            </li>
                            <li>
                                <a href="#" onclick="openWin()"><img src="manasmaindashboard/images/Help Center.png"> <span style="margin-left: 16px !important;font-size: 13px;">Help Center
                                        </span></a>
                                
                            </li>
                            <% if(loginInfo.getUserType()==2) {%>
	                            <li>
	                                <a href="" >
	                                		<img src="manasmaindashboard/images/Settings.png"> <span style="margin-left: 25px !important;font-size: 13px;">Settings</span>
	                                </a>
	                                <ul class="collapse">
                                        <li><a href="#" onclick="openPopup('profileClinicRegistration')">Profile</a></li>
                                        <%if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2){ %>
                                        	<li><a href="#" onclick="openPopup('UserProfile')">Manage User</a></li>
                                        <%} %>
                                        <%if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2){ %>
                                        	<li><a href="#" onclick="openPopup('DiaryMangent')">Manage Diary</a></li>
                                        <%} %>
                                        <!-- <li><a href="">some text</a></li> -->
                                    </ul>
	                            </li>
	                        <%} %>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <!-- sidebar menu area end -->
        <!-- main content area start -->
        <div class="main-content">
            <!-- header area start -->
           <div class="header-area">
                <div class="row align-items-center">
                    <!-- nav and search button -->
                <!-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"> -->
                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 clearfix" align="center">
                        <div class="nav-btn " id="closesidebar">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="search-box pull-left ">
                            <img src="manasopd/assets/images/Manas_Yuvicare_Logo.svg" class="mob hidden">
                            <img src="liveData/clinicLogo/<s:property value="clinicLogo"/>" class="hidden" alt="" style="max-width: 26%;" >
                        <div class="hidden">
                            <form action="#" style="display: inline;margin-left: 15px;">
                                <i class="ti-search"></i>
                                <input type="text" name="search" placeholder="Search patients, appointments, help or anything else " required>
                                
                            </form>
                            <img src="manasopd/assets/images/Add Patient Icon.svg" class="head-img">
                            <h3 style="font-size: 12px;display: inline;
                            margin-left: 10px;color: #15536E;">Add A Patient</h3>
                                  <img src="manasopd/assets/images/Book Appointment Icon.svg" class="head-img">
                                  <h3 style="font-size: 12px;display: inline;
                                  margin-left: 15px;color: #15536E;">Book An Appointment</h3>
                        </div>
                       
                        </div>
                         <div class="hidden" style="text-align: left;margin-left: 146px;">
                        	<h4><%=loginInfo.getClinicName() %></h4>
                        </div>
                        <div style="text-align: left;" class="hidden">
                        	<img src="liveData/clinicLogo/<s:property value="clinicLogo"/>" style="max-width: 8%;" alt="" class="mob">
                        	<h6 style="margin-left: 125px;margin-top: -41px;font-size: 16px;" id="hosname"><%=loginInfo.getClinicName() %></h6>
                        </div>
                        <div style="margin-top: 10px;">
                        	 <h2>OPD Dashboard</h2>
                        </div>
                    </div>
                      <%--  <%if(loginInfo.getUserType()==5){ %> --%>
                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 clearfix" align="center">
                        <ul class="notification-area ">
                            
                            <li style="display: none;"><img src="manasmaindashboard/images/Chat Icon.svg"></li>
                            <li class="dropdown" style="display: none;">
                                <i class="ti-bell dropdown-toggle" data-toggle="dropdown">
                                </i>
                                <div class="dropdown-menu bell-notify-box notify-box">
                                    <span class="notify-title">You have 3 new notifications <a href="#">view all</a></span>
                                    <div class="nofity-list">
                                        
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-comments-smiley btn-info"></i></div>
                                            <div class="notify-text">
                                                <p>New Commetns On Post</p>
                                                <span>30 Seconds ago</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </li>
                            <li>
                            <%String loghide=""; %>
                            <%if(loginInfo.getLoginType().equals("mob")){
                           			loghide="hidden";
                           		 }%>
                            	<div class="dropdown show <%=loghide %>" style="margin-left: 0px;padding-bottom: 10px;">
                                <a class="btn btn-danger dropdown-toggle" style="color: #43b9be!important;font-size: 14px!important" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <% if(loginInfo.getSuperadminid()==1){%>
                                    	Welcome, <%=loginInfo.getFirstName()%> <i class="fa fa-angle-down"></i>
                                    <%}else{ %>
                                    	Welcome, <%=loginInfo.getFirstName()%> <i class="fa fa-angle-down"></i>
                                    <% }%>
                                    
                                </a>
                              
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink" style="min-width: 5rem !important">
                                	<% if(loginInfo.getUserType()==2 || loginInfo.getUserType()==4){%>
                                		<a class="dropdown-item" href="#" onclick="openSamePopup('Logout')"><i class="fa fa-sign-out" ></i>&nbsp;Logout</a>
                                	<%}else{ %>
                                	<a href="#" class="dropdown-item" onclick="openSamll('puresevaprofileClient?id=<%=loginInfo.getPuresevaclientid()%>')">
                                        <i class="fa fa-user"></i>Profile
                                    </a>
                                		<%if(!loginInfo.getLoginType().equals("mob")){ %>
                                			<a class="dropdown-item" href="#" onclick="openSamePopup('Logout')"><i class="fa fa-sign-out"></i>&nbsp;Logout</a>
                                		<% }%>
                                	<% }%>
	                                  <!-- <a class="dropdown-item" href="#">Something else here</a> -->
                                </div>
                               
                              </div>
                             </li>
                            
                            <li><img src="manasmaindashboard/images/Profile Picture_Placeholder_Circle.svg" width="50" height="50"></li>
                          
                        </ul>
                    </div>
                   <!--  </div> -->
                   <%--  <%} %> --%>
                  <%--   <%if(loginInfo.getUserType()==5){ %>
                    <div class="" style="margin-left: 120px;font-size: medium;">
                                       Welcome, <%=loginInfo.getFirstName() %>&emsp;
                    <% if(loginInfo.getUserType()==2){%>
                                		<a class="" href="Logout"><i class="fa fa-sign-out"></i>&nbsp;Logout</a>
                                	<%}else{ %>
                                		<a class="" href="#" onclick="opencPopup('settingMainDashBoard')"><i class="fa fa-user"></i>&nbsp;Profile</a>&nbsp;&nbsp;
                                		<%if(!loginInfo.getLoginType().equals("mob")){ %>
                                			<a class="" href="Logout"><i class="fa fa-sign-out"></i>&nbsp;Logout</a>
                                		<% }%>
                                	<% }%>
                                	</div>
                                	<%} %> --%>
                </div>
            </div>
            <div class="main-content-inner">
          
                <!-- <div class="container-fluid" style="background-image: url('manasopd/assets/images/Union 12.svg'); background-repeat: no-repeat, repeat;"> -->
                <div class="container-fluid" style="background-image: url('manasopd/assets/images/Union 12.svg'); ">
                    <div class="row"  >
                        <div class="col-lg-12" style="padding-top: 15px;" >
                       
                            <div class="row">
							<%if(loginInfo.getUserType()==5) {%>
								<jsp:include page="/manasopd/manasopdpatient.jsp"/>
							<%}else{ %>
								<jsp:include page="/manasopd/manasopduser.jsp"/>
							<%} %>                          
                             <%if(loginInfo.getUserType()==5){ %>
			
			
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=hidepatientsearch %>" style="margin-top: 10px;">
			<strong><span  style="font-size: 18px;"><%=loginInfo.getFullname() %></span></strong>&emsp;
			</div>
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=hidepatientsearch %>" style="margin-top: 10px;">
			<label>Appointment Log</label>
			</div>
			<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 5%;margin-left: 3%;">
			<input onclick="showdisplaynewopd(0)" type="button" value="History" class="btn btn-primary"/>
			<input onclick="showdisplaynewopd(1)" type="button" value="Today" class="btn btn-primary"/>
			<input onclick="showdisplaynewopd(2)" type="button" value="Future" class="btn btn-primary"/>
			</div>
			
			<%} %>
			<%if(loginInfo.getTokenstatus()==1){ %>
			<br><br>
			<div style="text-align: center;padding-top: 40px">
			<div>
			<h2 id="headdr"></h2>
			</div>
			</div>
			<%} %>
                            <div class="hidden" style="padding-top: 42px;width: 100%">
                               
                                  <ul class="nav nav-pills" style="background-color: #e0faff;
                                  border-radius: 15px;margin-left: 15px;height: 32px;">
                                    <li class="nav-item liwidth">
                                      <a class="nav-link active" data-toggle="pill" href="#All Status" role="tab" aria-controls="pills-flamingo" aria-selected="true">All Status</a>
                                    </li>
                                    <li class="nav-item liwidth">
                                      <a class="nav-link" data-toggle="pill" href="#Upcoming Appointment" role="tab" aria-controls="pills-cuckoo" aria-selected="false">Upcoming Appointment</a>
                                    </li>
                                    <li class="nav-item liwidth">
                                      <a class="nav-link" data-toggle="pill" href="#Under Diagnostics" role="tab" aria-controls="pills-ostrich" aria-selected="false">Under Diagnostics</a>
                                    </li>
                                    <li class="nav-item liwidth">
                                        <a class="nav-link" data-toggle="pill" href="#Completed" role="tab" aria-controls="pills-ostrich" aria-selected="false">Completed</a>
                                      </li>
                                      <li class="nav-item liwidth">
                                        <a class="nav-link" data-toggle="pill" href="#Non Attendees" role="tab" aria-controls="pills-ostrich" aria-selected="false">Non Attendees</a>
                                      </li>
                                    
                                  </ul>
                                  
                            </div>
                            <br>
                            <div class="row hidden">
                                <div class="col" style="margin-left: 5%;
                                margin-top: 80px;    margin-right: 13%;">
                                <p style="display: inline;">Patients for Today</p>
                                <p style="float:right; display: inline;cursor: pointer;">Show 5 items <i class="fa fa-angle-down"></i></p>
                            </div>
                            </div>
                          
                            
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
                                <!-- <div class="col-lg-11"> -->
                                  <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 <%=hidepatientsearch %>" style="padding-top: 20px;">
                                  		<label style="font-size: 15px">Quick Search</label>
	                                <input id="myInput" type="text" placeholder="Search Patient" onkeyup="myFunction()" class="form-control">
	                                
                                </div>
                                
                                 <div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 <%=hidepatientsearch %>" style="padding-top: 20px;">
                                 	<label style="font-size: 15px">Appointment Order:</label>
	                                <select onchange="showdisplaynewopd(this.value)" class="form-control chosen-select" name="orderappt" id="orderappt">
									<option value="4">Descending</option>
									<option value="3">Ascending</option>
	                                </select>
                                 </div> 
                                 
                                <br>
                                 <%if(loginInfo.isLmh()){ %>
                                  <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12" style="padding-top: 20px;">
                                 	<label style="font-size: 15px">Patient Category:</label>
	                                <s:select onchange="lmhcategorynewopd()" list="#{'General':'General','Camp':'Camp','Student':'Student','Kamal Nath':'Kamal Nath','Employee':'Employee','BPL':'BPL'}" id="patientcategory" headerKey="" headerValue="Select Category" name="patientcategory" cssClass="form-control showToolTip chosen-select"></s:select>
	                               </div> 
                                 <%} %>  
                               <br>
                                  <div class="col-lg-5 col-md-5 col-xs-12 col-sm-12 <%=hidepatientsearch %>" style="padding-top: 20px;">
                                  <%if(!loginInfo.isLmh()){ %>
	                                <label style=";font-size: 15px;font-weight: 700;padding-left: 0px;">Total Incomplete Appointment :</label>
	                                <label style="font-size: 15px;font-weight: 700;" id="undoneappt"></label>
	                                <%} %>
	                               </div> 
                                 </div>
                                 
                                 
                                <div style=" overflow-x: auto;max-width: 100%;padding-top: 20px;">
                                <table class="table-responsive" id="" border="1" style="width: 100%;border-collapse: collapse;font-family: 'Open Sans', sans-serif;">
					<thead style="background-color: #def5ff">
					<%if(loginInfo.getUserType()!=5){ %>
						<tr>
						   <%if(loginInfo.isSaimed()){ %>
								<th style="width: 5%">Serial No.</th>
								<th style="width: 9%">UHID</th>
								<th style="width: 10%;"><%=loginInfo.getPatientname_field() %> Name</th>
								<th style="width: 5%">Token No.</th>
								<%if(loginInfo.isOpd_telemed() && !loginInfo.isParihar()){ %>
								<th></th>
							     <%} %>
								<th>Age/Gender</th>
								<th style="width: 5%">OPD ID</th>
								<th style="width: 8%">Booking Date/Time</th>
								<th style="width: 9%">Appointment Date/Time</th>
								<th style="width: 10%">Booked By</th>
								<th style="width: 6%">Duration</th>
								<th style="width: 11%">Appointment Name</th>
								<th style="width: 5%">Patient</th>
								
								
							<%} %>
							
							
					     <%if(!loginInfo.isSaimed()){ %>
							<th style="width: 3%">Serial No.</th>
							<th style="width: 5%">OPD ID</th>
							<th style="width: 8%">Booking Date/Time</th>
							<th style="width: 8%">Appointment Date/Time</th>
							<th style="width: 6%">Duration</th>
							<th style="width: 7%">Booked By</th>
							<th style="width: 8%">UHID</th>
							<th style="width: 9%;"><%=loginInfo.getPatientname_field() %> Name</th>
							<th style="width: 10%;">Age/Gender</th>
							<%if(loginInfo.isOpd_telemed()){ %>
								<th></th>
							<%} %>
							
							<th style="width: 2%">Appointment Name</th>
							<%if(loginInfo.isSimpliclinic()){ %>
							<th></th>
							<%} %>
							<%if(loginInfo.getClinicid1().equals("raiprachi")) {%>
							<th style="width: 2%">Label</th>
							
							<%} %>
							<%if(loginInfo.isKalmegha()){ %>
							<th>Consultation Note</th>
							<%} %>
							
							<%if(!loginInfo.isLmh()){ %>
							<th style="width: 5%">Invoice Print</th>
							<%} %>
					    	<%if(loginInfo.getClinicid1().equals("raiprachi")) {%>
							
							<th>Consultation Note</th>
							<%} %>
							<%if(loginInfo.isLmh()){ %>
								<th style="width: 5%">Patient Category</th>
								<th style="width: 2%">Referred From</th>
								<th style="width: 2%">Refer To</th>
								<th style="width: 5%"></th>
						        <th style="width: 5%"></th>
							<%} %>
						<%} %>
							<%if(loginInfo.isSaimed()){ %>
								<th>Consultation Note</th>
								<%if(loginInfo.isParihar()) {%>
								<th style="width: 5%">Invoice Print</th>
								<%} else{%>
								 <th style="width: 5%">Payments</th>	
							<%} }%>
							
						</tr>
					<%}else{ %>
						<tr>
						
					
							<th style="width:2%; !important;">Sr.</th>
							<th style="width:6%; !important;">Booking Date/Time</th>
							<th style="width:6%; !important;">Appointment Date/Time</th>
							<th style="width:6%; !important;text-align: center;">Doctor Name</th>
							<th style="width:2%; !important;">Payment</th>
							<th style="width:2%; !important;">Video Call</th>
							<th style="width:2%; !important;">Remark</th>
							<th style="width:2%; !important;">Attachment</th>
							<th style="width:2%; !important;">Cancel</th>
							<th style="width:3%; !important;">Payment Status</th>
							<th style="width:3%; !important;">Status</th>
							<!-- <th style="width:8%;"></th> -->
							
							
							
						</tr>
						
						<%} %>
					</thead>
					<tbody id="newopdbodyid">
					
					</tbody>
				</table>
				</div>
                                    <!-- <div class="row opd-card-upcoming" >
                                        <div class="col-lg-3 col-md-3 vl-1 ml-img">
                                           
                                            <h3 >Long Discussion</h3>
                                            <li style="font-size: 130%;color:#ea9b47;margin-left: 20px;margin-top: 5px; "><h5 >Upcoming Appointment</h5></li>
                                        </div>
                                        <div class="col-lg-3 col-md-3 vl ml-img">
                                            <img src="assets/images/Date Icon.svg" >
                                            <h2 > 02 May 2020</h2><br>
                                            <img src="assets/images/TIme Icon.svg">
                                            <h2 > 10 am to 12 pm</h2>
                                        </div>
                                        <div class="col-lg-2 col-md-2 vl ml-img" >
                                            <img src="assets/images/Profile Icon.svg" style="margin-top: 0px;">
                                         </div>
                                         <div class="col-lg-4 col-md-4 ml-img" style="padding-top: 15px;">   
                                            <h1>Shreya Sameer Garg </h2><br>
                                            <img src="assets/images/Patient Unique Number.svg" style="margin-left:10px ;
                                            margin-top: 10px;">
                                            <h4 > 023 030 304</h2>
                                            <i class="fa fa-angle-right opd-i"></i>
                                        </div>
                                    </div>
                                    <div class="row opd-card-under" >
                                        <div class="col-lg-3 col-md-3 vl-2 ml-img">
                                           
                                            <h3 >Long Discussion</h3>
                                            <li style="font-size: 130%;color:#4770ea;margin-left: 20px;margin-top: 5px; "><h5 >Under Diagnostics</h5></li>
                                        </div>
                                        <div class="col-lg-3 col-md-3 vl ml-img">
                                            <img src="assets/images/Date Icon.svg" >
                                            <h2 > 02 May 2020</h2><br>
                                            <img src="assets/images/TIme Icon.svg">
                                            <h2 > 10 am to 12 pm</h2>
                                        </div>
                                        <div class="col-lg-2 col-md-2 vl ml-img" >
                                            <img src="assets/images/Profile Icon.svg" style="margin-top: 0px;">
                                         </div>
                                         <div class="col-lg-4 col-md-4 ml-img" style="padding-top: 15px;">   
                                            <h1>Vikram Singh Rajput </h1><br>
                                            <img src="assets/images/Patient Unique Number.svg" style="margin-left:10px ;
                                            margin-top: 10px;" >
                                            <h4 > 023 030 304</h4>
                                            <i class="fa fa-angle-right opd-i"></i>
                                        </div>
                                    </div>
                                    <div class="row opd-card-completed" >
                                        <div class="col-lg-3 col-md-3 vl-3 ml-img">
                                           
                                            <h3 >Medium Discussion</h3>
                                            <li style="font-size: 130%;color:#08a227;margin-left: 20px;margin-top: 5px; "><h5 >Completed</h5></li>
                                        </div>
                                        <div class="col-lg-3 col-md-3 vl ml-img">
                                            <img src="assets/images/Date Icon.svg" >
                                            <h2 > 02 May 2020</h2><br>
                                            <img src="assets/images/TIme Icon.svg">
                                            <h2 > 10 am to 12 pm</h2>
                                        </div>
                                        <div class="col-lg-2 col-md-2 vl ml-img" >
                                            <img src="assets/images/Profile Icon.svg" style="margin-top: 0px;">
                                         </div>
                                         <div class="col-lg-4 col-md-4 ml-img" style="padding-top: 15px;">   
                                            <h1 >Shrish Chattoupadhyay </h1><br>
                                            <img src="assets/images/Patient Unique Number.svg" style="margin-left:10px ;
                                            margin-top: 10px;">
                                            <h4 > 023 030 304</h4>
                                            <i class="fa fa-angle-right opd-i"></i>
                                        </div>
                                    </div>
                                    <div class="row opd-card-completed" >
                                        <div class="col-lg-3 col-md-3 vl-3 ml-img">
                                           
                                            <h3 >Short Discussion</h3>
                                            <li style="font-size: 130%;color:#08a227;margin-left: 20px;margin-top: 5px; "><h5 >Completed</h5></li>
                                        </div>
                                        <div class="col-lg-3 col-md-3 vl ml-img">
                                            <img src="assets/images/Date Icon.svg" >
                                            <h2> 02 May 2020</h2><br>
                                            <img src="assets/images/TIme Icon.svg">
                                            <h2 > 10 am to 12 pm</h2>
                                        </div>
                                        <div class="col-lg-2 col-md-2 vl ml-img " >
                                            <img src="assets/images/Profile Icon.svg" style="margin-top: 0px;">
                                         </div>
                                         <div class="col-lg-4 col-md-4 ml-img" style="padding-top: 15px;">   
                                            <h1 >Kuleep Kumar Baniya </h1><br>
                                            <img src="assets/images/Patient Unique Number.svg" style="margin-left:10px ;
                                            margin-top: 10px;">
                                            <h4 > 023 030 304</h4>
                                            <i class="fa fa-angle-right opd-i"></i>
                                        </div>
                                    </div>
                                    <div class="row opd-card-non-attendencs" >
                                        <div class="col-lg-3 col-md-3 vl-4 ml-img">
                                           
                                            <h3 >No Discussion</h3>
                                            <li style="font-size: 130%;color:#f70606;margin-left: 20px; margin-top: 5px;"><h5 >Non Attendee</h5></li>
                                        </div>
                                        <div class="col-lg-3 col-md-3 vl ml-img">
                                            <img src="assets/images/Date Icon.svg" >
                                            <h2 > 02 May 2020</h2><br>
                                            <img src="assets/images/TIme Icon.svg">
                                            <h2 > 10 am to 12 pm</h2>
                                        </div>
                                        <div class="col-lg-2 col-md-2 vl ml-img" >
                                            <img src="assets/images/Profile Icon.svg" style="margin-top: 0px;">
                                         </div>
                                         <div class="col-lg-4 col-md-4 ml-img" style="padding-top: 15px;">   
                                            <h1>Parag Shashikant Deshmukh </h1><br>
                                            <img src="assets/images/Patient Unique Number.svg" style="margin-left:10px ;
                                            margin-top: 10px;">
                                            <h4 > 023 030 304</h4>
                                            <i class="fa fa-angle-right opd-i"></i>
                                        </div>
                                    </div> -->
        
                              <!--   </div> -->
        
                            </div>
                        </div>

                        <div class="col-lg-4 hidden"  style="background-color: #fff;
                        padding-top: 70px;">
                       
                              <div class="row time-slot">
                                <h2>Time Slot</h2>
                                
                              </div>
                              <div class="row time-slot-1">
                                  <div class="col-2 time-slot-card-1">
                                  <hr>
                                  <span>8.00 am</span>
                                  </div>
                                  <div class="col-10">
                                        <div class="card time-slot-card">
                                            
                                               <h4> 8.00 am to 8.20 am</h4>
                                            
                                        </div>
                                    </div>
                              </div>
                              <div class="row time-slot-1">
                                <div class="col-2 time-slot-card-1">
                                <hr>
                                <span>9.00 am</span>
                                </div>
                                <div class="col-10">
                                      <div class="card time-slot-card">
                                          
                                             <h4> 9.00 am to 9.30 am</h4>
                                          
                                      </div>
                                      <div class="card time-slot-card">
                                          
                                        <h4> 9.40 am to 9.50 am</h4>
                                     
                                 </div>
                                  </div>
                            </div>
                            <div class="row time-slot-1">
                                <div class="col-2 time-slot-card-1">
                                <hr>
                                <span>10.00 am</span>
                                </div>
                                <div class="col-10">
                                      <div class="card time-slot-card-3">
                                          
                                             <h4> 10.00 am to 10.50 am</h4>
                                          
                                      </div>
                                      
                                  </div>
                            </div>
                            <div class="row time-slot-1">
                                <div class="col-2 time-slot-card-1">
                                <hr>
                                <span>11.00 am</span>
                                </div>
                                <div class="col-10">
                                      <div class="card time-slot-card-3">
                                          
                                             <h4> 11.00 am to 11.40 am</h4>
                                          
                                      </div>
                                      
                                  </div>
                            </div>
                            <div class="row time-slot-1">
                                <div class="col-2 time-slot-card-1">
                                <hr>
                                <span>12.00 am</span>
                                </div>
                                <div class="col-10">
                                      <div class="card time-slot-card-4">
                                          
                                             <h4> 12.00 am to 10.20 am</h4>
                                          
                                      </div>
                                      <div class="card time-slot-card-4">
                                          
                                        <h4> 12.40 am to 1.00 pm</h4>
                                     
                                 </div>
                                  </div>
                            </div>
                            <div class="row time-slot-1">
                                <div class="col-2 time-slot-card-1">
                                <hr>
                                <span>1.00 pm</span>
                                </div>
                                <div class="col-10">
                                      <div class="card time-slot-card-4">
                                          
                                             <h4> 1.00 pm to 1.30 pm</h4>
                                          
                                      </div>
                                      
                                  </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container-fluid">
                    

                </div>
              
        </div>
        </div>
        <!-- main content area end -->
     	<jsp:include page="/popupdialog/dialog/newcommonpopup.jsp"/>
    </div>
    
    <!-- page container area end -->
   
   <!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93)" id="newloaderPopup" aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->
 
<div class="modal fade" id="confirmuhid" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-md" style="width: 350px;">
		<div class="modal-content">
			<div class="modal-header">
				
				<h4 class="modal-title">Registration/Login</h4>
				<%-- <button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button> --%>
			</div>
			<div class="modal-body">
				<div class="row">
				   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<input type="hidden" name="cancelinv" value="1">
						<input type="hidden" id="diffyear"> 
						<div class="form-group" id="rtb">
						<!-- &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						<input type="radio" id="newpatient" name="patient" value="new" onclick="showinstruct(this.value)">New Patient&emsp;&emsp;&emsp; &nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						<input type="radio" id="oldpatient" name="patient" value="old" onclick="showinstruct(this.value)">Old Patient
						 -->
						&emsp;&emsp;<input id="newpatient" type="radio" name="patient" value="new" onclick="showdob(this.value)">
    <label for="newpatient" class="font-14"><span></span>New Patient</label>&emsp;&emsp;&emsp;&emsp;

    <input id="oldpatient" type="radio" name="patient" value="old" onclick="showdob(this.value)">
    <label for="oldpatient" class="font-14"><span></span>Existing Patient</label>
						</div>
						<div class="row hidden" id="cdob" style="margin-left: 9px">
						<label class="font-11">Enter Date of Birth <span class="text-danger">*</span></label>
						<%-- <s:textfield readonly="true" name="initialdob" id="initialdob"
					cssClass="form-control" theme="simple" style="width:100%;" ></s:textfield> --%>
					<input type="text" id="initialdob" name="initialdob"
									class="form-control showToolTip font-12"
									  readonly="readonly"
									data-toggle="tooltip" onchange="getAgendDays4(this.value)" style="width: 23%"/>&emsp;&emsp;
									
				   <label class="font-11">Age<span class="text-danger">*</span></label>
									<input type="text" name="age" value="0" id="pureage" class="form-control font-12" 
									onchange="allnumeric4(this.value)" style="width: 16%">
									&emsp;<strong class="hidden">(<label id="majorminor" style="font-weight: 700;font-size: 13px;"></label>)</strong>
						</div><br>
						 <div class="form-group" hidden="hidden" >
						 	<div id="new" class="hidden" >
								<label class="font-13"><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMAP etc.)</b></label>								 
								<label class="font-13"><span class="text-danger"><b>1. Compulsory - ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label class="font-13"><b>2. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label class="font-13"><b>3. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="agenew" class="hidden">
								<label class="font-13"><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMAP etc.)</b></label>								 
								<label class="font-13"><span class="text-danger"><b>1. Compulsory - ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label class="font-13"><span class="text-danger"><b>2. Compulsory -  Parent's or Guardian ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label class="font-13"><b>3. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label class="font-13"><b>4. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="new1" class="hidden">
								<label class="font-13"><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMAP etc.)</b></label>
								<label class="font-13"><span class="text-danger"><b>1. Compulsory -  Parent's or Guardian ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>								 
								<label class="font-13"><b>2. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label class="font-13"><b>3. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="old" class="hidden">
								<label class="font-13"><b>Please keep following ready for Login- (Image Format in JPEG, PNG, BITMAP etc.)</b></label>								 
								<label class="font-13"><b>1. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label class="font-13"><b>2. Optional - Recent Photograph</b></label>
							</div>
						</div>
						
						<div class="form-group hidden" style="padding-bottom: 10px;" id="mobdiv">
							<label class="font-13">Enter Mobile No. <span class="text-danger">*</span></label>
							<input type="text" class="form-control" maxlength="10"   id="entermob"/>
							
						</div>
 
						 
						
						<div class="form-group" style="text-align: center;">
						<button type="button" class="btn btn-success" onclick="checkuhidexistornot()" id="confirmmob" style="visibility:hidden;padding: 3px 22px;">Login</button>
						<button type="button" class="btn btn-primary" onclick="confirmpopupreg()" id="newregpatient" style="visibility:hidden;margin-left: -71px;">Next</button>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="availablepatient" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				
				<h4 class="modal-title">Available Patient Name With This Mobile Number</h4>
		<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
				<table class="my-table xlstable" style="width: 100%">
				<thead>
				  <tr>
				  	<th>UHID</th>
				    <th>Name</th>
				  </tr>
				  </thead>
				  <tbody id="avalablept">
				  </tbody>
				  </table>					
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="checkaval" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="overflow: auto;">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				 
				<h4 class="modal-title" style="text-align: center;font-size: large !important;font-weight: 700;">Available Slots<br>Doctor Name : <span id="drnamewithqul"></span>&emsp;&emsp;Date : <span id="selecteddiarydate"></span></h4>
			<button type="button" class="close" data-dismiss="modal" style="margin-top: -51px !important;">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row" style="/*margin-left: 112px;*/">
					<div class="col-lg-12">
						<input type="hidden" name="cancelinv" value="1">
						<div class="form-group">
							<!-- <ul id="TimingSlot" class=""></ul> -->
							<table id="avalslot" class="tablecls">
							
							</table>
							
						</div>
 
						 
						
						<div class="form-group">
							<!-- <button type="button" class="btn btn-primary" onclick="confirm()" id="confirmmob" style="float: left;" disabled="disabled">Confirm</button> -->
						<!-- <button type="button" class="btn btn-primary" onclick="departmentconfirm()" id="newregpatient" style="float: right;" >OK</button> -->
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>
<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	












<!--pure seva client details popup  -->
		<%
			LoginInfo info = LoginHelper.getLoginInfo(request);
		%>
		<div class="modal fade" id="puresevaclientdetailsdiv" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			data-keyboard="false" data-backdrop="static" style="margin-top: 0px;overflow-y:scroll ">
			<div class="modal-dialog modal-xl" style="width: 95%">
				<div class="modal-content">
					<div class="modal-header">
						
						<h4 class="modal-title" id="myModalLabel">Please confirm your
							details</h4>
							<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
					</div>
					<s:form id="upload" method="post" action="upopddocEmr"
							enctype="multipart/form-data" theme="simple">
					<div class="modal-body">
						<div class="col-lg-12" style="padding-bottom: 10px;background-color: lavenderblush;" hidden="hidden">
							<div id="registrationguide1" class="hidden" style="text-align: center;">
								<label><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMEP etc.)</b></label>								 
								<label><span class="text-danger"><b>1. Compulsory - ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label><b>2. Optional - Medical Record (Previous Report's, Prescription)</b></label>	
								<label><b>3. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="registrationguide2" class="hidden" style="text-align: center;">
								<label><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMEP etc.)</b></label>									 
								<label><span class="text-danger"><b>1. Compulsory - ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label><span class="text-danger"><b>2. Compulsory -  Parent's or Guardian ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>	
								<label><b>3. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label><b>4. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="registrationguide3" class="hidden" style="text-align: center;">
								<label><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMEP etc.)</b></label>	
								<label><span class="text-danger"><b>1. Compulsory -  Parent's or Guardian ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label> 
								<label><b>2. Optional - Medical Record (Previous Report's, Prescription)</b></label>	
								<label><b>3. Optional - Recent Photograph</b></label>
							</div>
						</div>
						<div class="col-lg-12 hidden" style="padding-bottom: 10px;">
						<div class="col-lg-3">
								
						</div>
						<div class="col-lg-3">
						<label><b>Document Type:</b></label>
							<select class='form-control' id='docType'>
								<option value="Adhaar">Adhaar</option>
								<option value="Pan Card">Pan Card</option>
								<option value="Driving License">Driving License</option>
							</select>
						</div>	
						
						<div class="col-lg-3">
						<input type="hidden" id='docimg'>
						<label><b>Patient's Identity Document</b></label><label><span class="text-danger" id="patientidentiyspan" hidden="hidden">*</span></label>
							<div id="drop" style="background-color: #efefef;">
								Document  <a>Upload</a>
								<s:file name="fileUpload" theme="simple"  accept="image/*" required="true" onchange="getf(this.value,'docimg')">
								</s:file>
							</div>
							<span id="docimg1"></span>
						</div>
						
						<div class="col-lg-3">
						<s:hidden name='hiddenval' id='profileimg'></s:hidden>
						<label><b>Patient's Profile Photo</b></label>
							<span id="filename" style="color: white"></span>
							<div id="drop" style="background-color: #efefef;">
								Photo <a>Upload</a>
								<s:file name="files" theme="simple" id='' accept="image/*" required="true" onchange="getf(this.value,'profileimg')">
								</s:file>
							</div>
							<span id="profileimg1"></span>
						</div>
						
						
						
						
						<!-- <div class="col-lg-3">
							<ul class="popmodals123">
								The file uploads will be shown here
							</ul>
						</div> -->
						
						
						</div>
						
						<%-- <div class="col-lg-12 hidden" style="padding-top: 20px;background-color: lavender;" id="relativedetailssdiv">
							<div class="col-lg-3">
								<h5><b>Details of Patient's Parent / Guardian: </b></h5> 
							</div>
							<div class="col-lg-3">
								<label><b>Patient's Parent / Guardian Name:</b></label><label><span class="text-danger" id="parentguardiannamespan">*</span></label> 
								<input type="text" id='relativename123' class='form-control' >
							</div>
							
							<div class="col-lg-3">
								<label><b>Parent / Guardian Contact Number:</b></label><label><span class="text-danger" id="parentguardiancontactspan">*</span></label>
								<input type="number" maxlength="10" id='relativecontact123' class='form-control' >
							</div>
							
							<div class="col-lg-3">
								<label><b>Relationship with Patient's:</b></label><label><span class="text-danger" id="parentguardianrelationspan">*</span></label>
								<select id='relativerelation123' class='form-control'>
									<option value="">Select Relation</option>
									<option value="Mother">Mother</option>
									<option value="Father">Father</option>
									<option value="Son">Son</option>
									<option value="Daughter">Daughter</option>
									<option value="Brother">Brother</option>
									<option value="Sister">Sister</option>
									<option value="Uncle">Uncle</option>
									<option value="Aunt">Aunt</option>
									<option value="Cousin">Cousin</option>
									<option value="Husband">Husband</option>
									<option value="Wife">Wife</option>
									<option value="Nephew">Nephew</option>
									<option value="Niece">Niece</option>
									<option value="Grandson">Grandson</option>
									<option value="Grand daughter">Grand daughter</option>
									<option value="Grand Father">Grand Father</option>
									<option value="Grand Mother">Grand Mother</option>
									<option value="Other">Other</option>
								</select>
							</div>
							
							
						</div>
						 --%>
						
						<br>
						
						
						<div class="hidden" id="hiddenpuresevareg">
						<input type="hidden" id="puresevadobyear">
						<input type="hidden" id="puresevadobmonth">
						<input type="hidden" id="puresevadobdays">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label style="color: orange;">Aadhar Number</label> <input
										type="text" id="adhno" name="adhno" class="form-control"
										placeholder="Enter <%=loginInfo.getPatientname_field()%> Aadhar No"
										style="border-color: chocolate;" maxlength="12"> <label
										id="adhnoError" class="text-danger"></label>
								</div>
								</div>
						<div class="col-lg-12 hidden">
							<div class="col-lg-3">
								<label><b>Email ID:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9">
							
							<input type="hidden" name="puruhid" id="puruhid" value="<%=loginInfo.getUhid() %>">

								<input type="text" id="email" name="email"
									class="form-control showToolTip"
									onblur=""
									data-toggle="tooltip " value="<%=info.getEmail()%>" />
							</div>
						</div>
					
					  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 ">
									<label>Initial</label><label><span
										class="text-danger">*</span></label>
									
									<s:select id="title123" name="title"
									onchange="setGender123(this.value)" list="initialList"
									title="Select" theme="simple" cssClass="form-control"
									headerValue="Select" headerKey="0" />
								
								</div>
								<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 " >
									<label>First Name</label><label><span
										class="text-danger">*</span></label>
											<s:textfield id="purefname" name="purefname"
												title="Enter First Name" theme="simple"
												cssClass="form-control showToolTip" list="pdl_firstname"
												onkeyup="getpatientfordatalist(this.value,'pdl_firstname','firstname')"
												placeholder="Enter First Name"
												onblur="initialFirstCap(this);"
												autocomplete="off" />
											<datalist id="pdl_firstname">
											</datalist>
										<label id="fnameError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 ">
									<label>Middle Name</label>
									<s:textfield id="middleName" name="middleName"
										title="Enter Middle Name" theme="simple"
										cssClass="form-control showToolTip" list="pdl_middlename"
										onkeyup="getpatientfordatalist(this.value,'pdl_middlename','middlename')"
										placeholder="Enter Middle Name"
										onblur="initialFirstCap(this);" autocomplete="off" />
									<datalist id="pdl_middlename">
									</datalist>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Last Name</label><label><span
										class="text-danger">*</span></label>
									<s:textfield id="purelname" name="purelname"
										title="Enter Last Name" theme="simple"
										cssClass="form-control showToolTip" list="pdl_lastName"
										onkeyup="getpatientfordatalist(this.value,'pdl_lastName','surname')"
										placeholder="Enter Last Name" onblur="initialFirstCap(this);"
										autocomplete="off" />
									<datalist id="pdl_lastName">
									</datalist>
									<label id="lnameError" class="text-danger"></label>
								</div>
								
							</div>

						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
							
							<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
								<label>Date of Birth:</label><label><span class="text-danger">*</span></label>
							
								<input type="text" id="puredob" name="puredob"
									class="form-control showToolTip"
									  readonly="readonly"
									data-toggle="tooltip" value="<%=info.getDob()%>"  onchange="getAgendDays2(this.value)"/>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label><b>Age:</b></label><label><span class="text-danger">*</span></label>
							
								
								<input type="text" id="pureage1" name="pureage"
									class="form-control showToolTip"
									data-toggle="tooltip" onchange="allnumeric5(this.value)"/>
							</div>
							 <div class="col-lg-3 hidden" id='dobe' style="font-size: 13px;font-weight: 700;padding-top: 25px;">
							</div> 
					
								
									<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 ">
										<label>Gender</label> <label><span class="text-danger">*</span></label>
										<s:select id="gender123" name="gender123"
											list="{'Male','Female','Other'}" headerKey="0"
											headerValue="Select" theme="simple"
											cssClass="form-control showToolTip" />
										<label id="genderError" class="text-danger"></label>
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 ">
										<label>Marital Status</label>
										<s:select id="maritalsts" name="maritalsts"
											list="{'Single','Married','Divorced','Separated'}"
											headerKey="0" headerValue="Select" theme="simple"
											cssClass="form-control showToolTip" />
									</div>
								
								
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 hidden">
										<!--<label>DOB</label><label><span class="text-danger">*</span></label>			
									<s:textfield id = "dob" name="dob" cssClass="form-control showToolTip" readonly = "true"
									data-toggle="tooltip" title="Select DOB" theme="simple" placeholder = "Select DOB"></s:textfield>
									
					          	-->
										<label id="dobError" class="text-danger"></label>
										<s:hidden id="dob" name="dob"></s:hidden>
								</div>
								
							</div>
						
						<%-- <div class="col-lg-12">
							<div class="col-lg-3">
								<label><b>Patient's Mobile Number:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9">

								<input type="number" id="puremob" name="puremob"
									class="form-control showToolTip"
									 
									data-toggle="tooltip" maxlength="10" value="<%=info.getMob()%>"/>
							</div>
						</div>
						<br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label><b>Patient's Date of Birth:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-3">
								<input type="text" id="puredob" name="puredob"
									class="form-control showToolTip"
									  readonly="readonly"
									data-toggle="tooltip" value="<%=info.getDob()%>"  onchange="getAgendDays2(this.value)"/>
							</div>
							<div class="col-lg-1">
								<label><b>Age:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-1">
								
								<input type="text" id="pureage1" name="pureage"
									class="form-control showToolTip"
									data-toggle="tooltip" onchange="allnumeric5(this.value)"/>
							</div>
							<div class="col-lg-3" id='dobe' style="font-size: 13px;font-weight: 700;padding-top: 5px;">
							</div>
						</div> --%>
						
				
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
									<label>Patient's Address</label> <label><span class="text-danger">*</span></label>
									<s:textfield id="address123" name="address123" title="Enter Address"
										theme="simple" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter Address"
										onkeyup="allFirstInitCap(this.id);" />
									<label id="addressError" class="text-danger" autocomplete="off"></label>
									
								</div>	
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
							
							
							
							<div class="col-lg-3">
							<label><b>Country:</b></label><label><span class="text-danger">*</span></label>
							<s:select list="#{'India' : 'India'} " cssClass='form-control chosen-select' ></s:select>
							</div>
							
							
							<div class="col-lg-3">
							<label><b>State:</b></label><label><span class="text-danger">*</span></label>
							<span id='statediv2'>
								<%-- <s:select list="statelist" listKey="name" listValue="name" cssClass='form-control chosen-select' id='state123' onchange="getCitiesajax2(this.value)"></s:select> --%>
								<s:select list="statelist" listKey="name" listValue="name" cssClass='form-control chosen-select' id='state123' onchange="getCitiesajax2(this.value)" headerKey="0" headerValue="Select State" ></s:select>
							</span>
							</div>
							
							<div class="col-lg-3">
							<label><b>City / District:</b></label><label><span class="text-danger">*</span></label>
							<span id='citydiv2'>
								<%-- <s:select list="citylist" listKey="city" listValue="city" cssClass='form-control chosen-select' id='city123' onchange="getStateAjaxnew2(this.value)"></s:select> --%>
								<s:select list="citylist" listKey="city" listValue="city" cssClass='form-control chosen-select' id='city123' onchange="getStateAjaxnew2(this.value)" headerKey="0" headerValue="Select City" ></s:select>
							</span>
							</div>
							
							<input type="hidden" id="uploadhideul">
							
							
						</div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Mobile No</label>
									<s:textfield id="puremob" name="puremob" title="Enter Mobile No"
										theme="simple" cssClass="form-control showToolTip"
										list="pdl_mobNo"
										onkeyup="getpatientfordatalist(this.value,'pdl_mobNo','mobno')"
										data-toggle="tooltip" placeholder="Enter Mobile No"
										onchange="checkMobileValidation(this.value)" maxlength="10"/>
									<datalist id="pdl_mobNo">
									</datalist>
									<label id="mobNoError1" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Email</label>
									<s:textfield id="pureemail" name="pureemail" title="Enter Email"
										theme="simple" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter Email"
										onchange="checkEmailValidation(this.value)" />
									<label id="emailError1" class="text-danger"></label>
								</div>
								
							</div>
						
					</div>
					<div class="hidden">
						 <div class="col-lg-12 " style="padding-top: 20px;background-color: lavender;" id="relativedetailsdocdiv">
							<div class="col-lg-3">
								
							</div>
							<div class="col-lg-3 hidden">
								<input type="hidden" id='relativedocimg'>
								<label><b>Parent / Guardian Identity Document:</b></label><label><span class="text-danger" id="relativeidentiyspan" hidden="hidden">*</span></label>
								<div id="drop" style="background-color: #efefef;">
									Document  <a>Upload</a>
									<s:file name="relativefiles" theme="simple"  accept="image/*" required="true" onchange="getf(this.value,'relativedocimg')">
									</s:file>
								</div>
								<span id="relativedocimg1"></span>
							</div>
							
							<div class="col-lg-3">
								
							</div>
							
							<div class="col-lg-3">
								
							</div>
							
						</div>	
						</div>
						<div class="col-lg-12 hidden" style="padding-top: 10px;">
						
							<div class="col-lg-3">
							</div>
							
							<div class="col-lg-3">
							<label><b>Village / Town:</b></label><label><span class="text-danger">*</span></label>
								<input type="text" id='town123' class='form-control'>
							</div>
							
							
							<div class="col-lg-3">
								<label><b>Postal Code (Zip / Pin):</b></label>
								<input type="number" id='pincode123' maxlength="6" class='form-control'>
							</div>
							
						</div>
						<div class="hidden">
						<div class="col-lg-12 " style="padding-top: 20px;background-color: lavender;" id="relativedetailssdiv">
							<div class="col-lg-3">
								<h5><b>Details of Patient's Parent / Guardian: </b></h5> 
							</div>
							<div class="col-lg-3">
								<label><b>Patient's Parent / Guardian Name:</b></label><label><span class="text-danger" id="parentguardiannamespan">*</span></label> 
								<input type="text" id='relativename123' class='form-control' >
							</div>
							
							<div class="col-lg-3">
								<label><b>Parent / Guardian Contact Number:</b></label><label><span class="text-danger" id="parentguardiancontactspan">*</span></label>
								<input type="number" maxlength="10" id='relativecontact123' class='form-control' >
							</div>
							
							<div class="col-lg-3">
								<label><b>Relationship with Patient's:</b></label><label><span class="text-danger" id="parentguardianrelationspan">*</span></label>
								<select id='relativerelation123' class='form-control'>
									<option value="">Select Relation</option>
									<option value="Mother">Mother</option>
									<option value="Father">Father</option>
									<option value="Son">Son</option>
									<option value="Daughter">Daughter</option>
									<option value="Brother">Brother</option>
									<option value="Sister">Sister</option>
									<option value="Uncle">Uncle</option>
									<option value="Aunt">Aunt</option>
									<option value="Cousin">Cousin</option>
									<option value="Husband">Husband</option>
									<option value="Wife">Wife</option>
									<option value="Nephew">Nephew</option>
									<option value="Niece">Niece</option>
									<option value="Grandson">Grandson</option>
									<option value="Grand daughter">Grand daughter</option>
									<option value="Grand Father">Grand Father</option>
									<option value="Grand Mother">Grand Mother</option>
									<option value="Other">Other</option>
								</select>
							</div>
							
							</div>
						</div>
					</div>
					</s:form>
					<div class="modal-footer" style="text-align: center !important;padding-top: 20px ">
						<button type="button" style="margin-top: 20px;" class="btn btn-primary"
							onclick="confirm1()">Submit</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>





<!--confirm video request  -->
<div class="modal fade" id="confirmviddr" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" style="margin-top: -7px !important;">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Do you want to schedule your Video Call</h4>
			</div>
            <div class="modal-body">
            <input type="hidden" id="selectedcid">
            <input type="hidden" id="selectedappid">
            <strong><label></label></strong>
            <div id="doctornotes" style="display: none;">
            <textarea rows="5" cols="40" id="drrejectrem"></textarea>
            </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="setvideobydr()" id="yesbtnid">Yes Schedule</button>
                <button type="button" class="btn btn-default" onclick="confirmnotes()" id="nobtnid">No,Remark</button>
            </div>
        </div>
    </div>
</div>




<div class="modal fade" id="remarkbyhosp" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" style="margin-top: -7px !important;">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Remark</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<strong><label id="remm1"></label></strong><br>
						<strong><label id="remm2"></label></strong><br>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="showdrprofile" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" style="margin-top: -7px!important">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Dr Profile</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<strong>Name: <label id="drname"></label></strong><br>
						<strong>Qualification: <label id="drqual"></label></strong><br>
						<strong>Speciality: <label id="despec"></label></strong><br>
						<strong>Registration Number: <label id="drreg"></label></strong><br>
						<strong>Phone Number: <label id="drphone"></label></strong><br>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>


<!--Scan Code  -->
		<div class="modal fade" id="scanandpay" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" style="margin-top: -9px !important;">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Scan Pay</h4>
			</div>
			<div class="modal-body">
			<s:form theme="simple" action="resetProcessingAccount" id="resetForm">
				<div class="row">
					<div class="col-lg-12">
						<input type="hidden" name="cancelinv" value="1">
						<div class="form-group">
							<img alt="" src="cicon/upiqr.png" class="img-responsive">
						</div>
						 <div class="form-group" style="text-align: center">
						<strong>Pay Amount: &nbsp;<label id="scanamount"></label></strong>
						</div>
						
						
					</div>
					</div>
					</s:form>
				</div>
				
			</div>
		</div>
	</div>
<!--Change Department Code  -->
	<!--Change Department Code  -->
		<div class="modal fade" id="changedeoartment" tabindex="-1" role="dialog" style="line-height: 25px"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-md">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" style="margin-top: 0px !important;">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Refer To</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
						<label>Previous Department:</label><span id="predept"></span>
						<br>
						<label> Department List</label>
						<div id="depatylist"style="height: 109px;overflow: auto">
						
						</div>
						</div>
						<!-- <p style="color: red;">Note: For Multiple Select Press CTRL Button And Select Department </p> -->
						<input type="hidden" id="referselected">
						<div class="form-group ">
						<label>Remark</label>
						<s:textarea name="referremark" id="referremark" cssClass="form-control setreferscroll"  rows="5" cols="5"/>
						</div>
						 <div class="form-group" style="text-align: center">
						<a href="#" class="btn btn-primary" onclick="referdept();" id="refrbtn" style="margin-left: -41px;margin-top: 20px;">Submit</a>
						</div>
						
					</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>

<!--Loader  -->

<!-- 
 <div class="overlay" id="yuviloader" style="z-index: 9999999">
  <div class="spinner"></div>
  <div class="label">Loading</div>
</div>  -->

<!--Loader End  -->
<input type="hidden" id="oriuhid"/>
<input type="hidden" id="oriemail"/>

<input type="hidden" id="clinicid" value="<%=loginInfo.getClinicUserid()%>"/>
 <input type="hidden" id="linkaddress" value="<%=loginInfo.getLinkaddress()%>"/>
   
   
   <%if(loginInfo.getActionType().equals("doctorday")){ %>
   <script type="text/javascript">
   showdisplaynewopd();
   </script>
   <%} %>
   
   
   
   
   
   <script src="common/assets/js/jquery.fileupload.js"></script>
   
   
    <!-- jquery latest version -->
    <!-- bootstrap 4 js -->
    <script src="manasopd/assets/js/popper.min.js"></script> 
     <script src="manasopd/assets/js/bootstrap.min.js"></script> 
     <script src="manasopd/assets/js/owl.carousel.min.js"></script>
    <script src="manasopd/assets/js/metisMenu.min.js"></script>
    <script src="manasopd/assets/js/jquery.slimscroll.min.js"></script>
    <script src="manasopd/assets/js/jquery.slicknav.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script> 
  
    <!-- others plugins -->
    <script src="manasopd/assets/js/plugins.js"></script>
    <script src="manasopd/assets/js/scripts.js"></script>
    <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
	  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
			  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
			  <script type="text/javascript">
			    var config = {
			      '.chosen-select'           : {},
			      '.chosen-select-deselect'  : {allow_single_deselect:true},
			      '.chosen-select-no-single' : {disable_search_threshold:10},
			      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
			      '.chosen-select-width'     : {width:"100%"}
			    }
			    for (var selector in config) {
			      $(selector).chosen(config[selector]);
			    }
			  </script>
    <script>
    $(function() {
		$('.setreferscroll').slimScroll({
			height : '115px',
			width : '100%',
			railVisible : true,
			alwaysVisible : true
		});
	});
    </script>
         <script>
    
    document.onreadystatechange = function(){
        if (document.readyState === "complete") {
        /* 	$( "#preloader" ).modal( "hide" ); */
        	
        	
        	
        	/* document.getElementById("yuviloader").style.display="none"; */
        	
        	/* element.style.setProperty('display', 'none', 'important'); */
        	/* $("#opdloaderPopup").removeClass("show"); */
        	 /*  $( "#opdloaderPopup" ).modal( "hide" ); */
        }
    }   

    function bootstrapplugin(){
    	/*  table = $('#example').DataTable( {
             lengthChange: false,
             buttons: [ 'colvis' ]
         	
         } );
    	    table.buttons().container()
    	        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
    	   
    */ 	
    }

    </script>
    
    <script>
    function myFunction() {
    	  
    	var $rows = $('#newopdbodyid tr');
    	$('#myInput').keyup(function() {
    	  var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase().split(' ');

    	  $rows.hide().filter(function() {
    	    var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
    	    var matchesSearch = true;
    	    $(val).each(function(index, value) {
    	      matchesSearch = (!matchesSearch) ? false : ~text.indexOf(value);
    	    });
    	    return matchesSearch;
    	  }).show();
    	});

    	}
    
    
</script>
     
     
     <script type="text/javascript">
function showpopupfornewopd3(){
	alert("Hi");
}

</script>


<script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
}


$( window ).unload(function() {
	document.cookie = "googtrans=/en/en";
	
});
</script>

<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
</body>

</html>
