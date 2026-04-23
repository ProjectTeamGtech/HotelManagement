<!doctype html>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<html class="no-js" lang="en">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%> 

     <head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Manas - Yuvicare Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <link rel="shortcut icon" href=" manaslogin/images/Manas_Yuvicare_Circle Favicon.png">
</head>
<style>
    .col-1{
        padding-left: 0px;
        padding-right: 0px;
    }
    @media only screen and (max-width:600px){
    .notification-area li {
    margin: 0 10px;
}
    }
    @media only screen and (max-width:600px){
    .search-box input {
        width: 325px;
}
 @media only screen and (max-width:600px){
    #hosname {
        margin-top: -29px !important;
}
.newlokesh:hover {
    box-shadow: 4px 4px 4px 4px green !important;
}
 .colortog{
 font-size: 14 px !important;
 font-weight: bold;}
}
.mybtn{
    right: 21px !important;
    top: 8px !important;
    font-size: 14px !important;
    color: #fff !important;
    background-color: #15536E !important;
    border-color: #15536E !important;
    border-radius: 1.25rem !important;
    padding-top: 1px !important;
}
</style>
<body>
<%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>

    
    <div id="preloader">
        <div class="loader"></div>
    </div>
    <!-- preloader area end -->
    <!-- page container area start -->
    <div class="page-container">
        <!-- sidebar menu area start -->
        <div class="sidebar-menu">
            <div class="sidebar-header" style="padding-top: 4px;">
                <div class="logo">
                    <img src="liveData/clinicLogo/<s:property value="userImageFileName"/>" class="hidden" alt="logo" style="height: 56px;width: 89px;">
                    <img src="manasmaindashboard/images/Manas_Yuvicare_Logo.svg" style="height: 56px;width: 89px;">
                   <%--  <%if(loginInfo.isWardhazp()){ %>
                    <br><br>
                    <img src="cicon/wardhapf.jpeg" style="    height: 135px;    width: 115px;    margin-left: -15px;    max-width: 130%;">
                    
                    <%} %> --%>
                </div>
            </div>
            <%-- <%if(loginInfo.isWardhazp()){ %>
<span style="font-size: 12px;margin-left: 17px;">मृणाल  हेमंत माटे (लांबट)</span> 
				<span style="font-size: 12px;margin-left: 53px;">सभापती </span> <br>
				<span style="font-size: 12px;margin-left: 17px;">शिक्षण ,क्रीडा ,व आरोग्य </span><br><span style="font-size: 12px;margin-left: 17px;">समिती जिल्हा परिषद  वर्धा  </span>
				<br><span style="font-size: 10px;margin-left: 13px;">मो.9890906064/7507058999</span>
				<br><span style="font-size: 8.5px;margin-left: 0px;">mrunalmate.eduhealthzp@gmail.com</span> 
				  <%} %>       --%>     
            <div class="main-menu">
                <div class="menu-inner">
                    <nav>
                        <ul class="metismenu" id="menu">
                            
                            <li class="hidden">
                                <a href="onlinebookapptClient" ><img src="manasmaindashboard/images/Home.png"><span>Book</span></a>
                            </li>
                            <li >
                                <a href="MainDashBoard" ><img src="manasmaindashboard/images/Home.png"><span>Home</span></a>
                            </li>
                            <li style="display: none;">
                                <a href="" ><img src="manasmaindashboard/images/Appointments.png"><span>Appointments
                                    </span></a>
                            </li>
                            <% if(loginInfo.isManageclient()){%>
	                            <li style="display: none;">
	                                <a href="#" onclick="opencPopup('manageClient')">
	                                	<img src="manasmaindashboard/images/Patients.png">
	                                	<span>Patients</span>
	                                </a>
	                            </li>
                            <%} %>
                            <% if(loginInfo.isFullFinance()){%>
	                            <li style="display: none;">
	                                <a href="#" onclick="opencPopup('ExpenceManagement?action=0')">
	                                	<img src="manasmaindashboard/images/Accounts.png">
	                                	<span>Accounts</span>
	                                </a>
	                            </li>
                            <%} %>
                            <%if(loginInfo.isPharmacy()){ %>
	                            <li style="display: none;">
	                                <a href="#" onclick="openSamePopup('onlinerequestpharPharmacy')">
		                                <img src="manasmaindashboard/images/Pharmacy.png">
		                                <span>Pharmacy</span>
	                                </a>    
	                            </li>
                            <%} %>
                            <% if(loginInfo.isInventory()){%>
	                            <li style="display: none;">
	                                <a href="#" onclick="openSamePopup('listProduct?isfromcathlab=0')">
	                                	<img src="manasmaindashboard/images/Inventory.png"></i>
	                                    <span>Inventory</span>
	                                </a>
	                            </li>
                            <%} %>
                            <% if(loginInfo.isManageopd()){%>
                            	<li style="display: none;">
                            		<a href="#" onclick="opencPopup('calNotAvailableSlot?actionType=newopd&doctor=<%=loginInfo.getId()%>')">
                            			<img src="manasmaindashboard/images/My opd_icon.png"> 
                            			<span>OPD</span>
                            		</a>
                            	</li>
                            <%} %>
                            <% if(loginInfo.isManageipd()){%>
	                        	 <%if(loginInfo.isDirect_ipd()){ %>  
	                        	 	<li style="display: none;">
	                        	 		<a href="#" data-toggle="modal" class="colortog" data-target="#wards">
	                        	 			<img src="manasmaindashboard/images/IPD_Icon.png"> 
	                        	 			<span>IPD</span>
	                        	 		</a>
	                        	 	</li>
	                        	 <%}else{ %>
	                        	 	<li style="display: none;">
	                        	 		<a href="#" onclick="opencPopup('IpdDashboard?action=0')">
	                        	 			<img src="manasmaindashboard/images/IPD_Icon.png"> 
	                        	 			<span>IPD</span>
	                        	 		</a>
	                        	 	</li>
	                             <%} %> 
		                    <%} %>
                            <%if(loginInfo.isMisaccess()) {%>
                        		<% if(loginInfo.isManagemis()){%>
		                            <li style="display: none;">
		                                <a href="#" onclick="opencPopup('MisChart')">
			                                <img src="manasmaindashboard/images/MIS_Icon.png"> 
			                                <span>MIS</span>
		                                </a>
		                            </li>
                             	<%} %> 
		                    <%} %>
		                    <% if(loginInfo.isManageemr()){%>
	                            <li style="display: none;">
	                                <a href="#" onclick="opencPopup('getPatientRecordEmr')">
	                                	<img src="manasmaindashboard/images/EMR_Icon.png">
	                                    <span>EMR</span>
	                                </a>
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
	                                <a href="#" onclick="openWin()"><img src="manasmaindashboard/images/Help Center.png"> <span>Help Center
	                                        </span></a>
	                        </li>
	                        <% if(loginInfo.getHospital_language().equals("en")) {%>
	                            <li>
	                            	<a href="#" data-toggle="modal" data-target="#changelanguage" ><img src="manasmaindashboard/images/Chat Room.png"> <span>Language</span></a>
	                            </li>    
                            <%} %>
                            <% if(loginInfo.getUserType()==2) {%>
	                            <li>
	                                <a href="" >
	                                		<img src="manasmaindashboard/images/Settings.png"> <span>Settings</span>
	                                </a>
	                                <ul class="collapse">
                                        <li><a href="#" onclick="openPopup('profileClinicRegistration')">Profile</a></li>
                                        <%if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2){ %>
                                        	<li><a href="#" onclick="openPopup('UserProfile')">Manage User</a></li>
                                        <%} %>
                                        <%if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2){ %>
                                        	<li><a href="#" onclick="openPopup('DiaryMangent')">Manage Diary</a></li>
                                        <%} %>
                                        <%if(loginInfo.getSuperadminid()==1){ %>
                                        	<li><a href="#" onclick="opencPopup('widgetsDiaryMangent')">Edit Widgets</a></li>
                                         <%} %>
                                        <!-- <li><a href="">some text</a></li> -->
                                         <li><a href="#" id="bckup" onclick="takehospitalbk()">Hospital Backup</a></li>
                                         <li><a href="#" id="" onclick="downloadjsp()">Download Backup</a></li>
                                         <%if(loginInfo.getSuperadminid()==1){ %>
                                        	<li><a href="missinguseridsaveMainDashBoard" >Missing Userid Save</a></li>
                                         <%} %>
                                         <%if((loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || loginInfo.getJobTitle().equals("Admin"))){ %>
                                        	<li><a href="#" onclick="openPopup('displayUserProfile')" >Change Login Status</a></li>
                                         <%} %>
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
                    <div class="col-md-8 col-sm-6 col-xs-12  clearfix">
                        <div class="nav-btn pull-left">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="search-box pull-left ">
                        	<img src="liveData/clinicLogo/<s:property value="userImageFileName"/>" style="max-width: 26%;" alt="" class="mob hidden">
                            <img src="manasmaindashboard/images/Manas_Yuvicare_Logo.svg" class="mob hidden">
                            <form action="#" style="display: inline;margin-left: 15px;display: none;">
                                <i class="ti-search" style="display: none;"></i>
                                <input type="text" name="search" style="display: none;" placeholder="Search patients, appointments, help or anything else " required>
                                
                            </form>
                            <img src="manasmaindashboard/images/Add Patient Icon.svg" style="display: none;" class="head-img">
                            <h3 style="font-size: 12px;display: none;
                            margin-left: 10px;color: #15536E;">Add A Patient</h3>
                                  <img src="manasmaindashboard/images/Book Appointment Icon.svg" style="display: none;" class="head-img">
                                  <h3 style="font-size: 12px;display: none;
                                  margin-left: 15px;color: #15536E;">Book An Appointment</h3>
                        </div>
                        <div class="" style="display: inline-flex;">
                       		<img src="liveData/clinicLogo/<s:property value="userImageFileName"/>" style="max-width: 10%;" alt="" class="mob">
                        	&nbsp;&nbsp;
                        	<h6 id="hosname" style="margin-top: 15px;"><%=loginInfo.getClinicName() %></h6>
	                    </div>
	                    
                     
                    </div>
                    <!-- profile info & task notification -->
                    <%int checknotificationstatus=0; %>
                    <s:if test="indentstaus==1">
                    	<%checknotificationstatus=1; %>
                    </s:if>
                    <s:if test="discountstatus==1">
                    	<%checknotificationstatus=1; %>
                    </s:if>
                    <s:if test="refundstatus==1">
                    	<%checknotificationstatus=1; %>
                    </s:if>
                    <div class="col-md-4 col-sm-6 col-xs-12 clearfix">
                        <ul class="notification-area pull-right">
                            
                            <li style="display: none;"><img src="manasmaindashboard/images/Chat Icon.svg"></li>
                            <li class="dropdown" >
                            	<%if(checknotificationstatus==1){ %>
                            		<i class="fa fa-bell dropdown-toggle" aria-hidden="true" style="color: RED;" data-toggle="dropdown"></i>
                            	<%}else{ %>
                            		<%-- <i class="ti-bell dropdown-toggle" style="color: #43b9be;" data-toggle="dropdown"></i> --%>
                            		<i class="fa fa-bell dropdown-toggle" aria-hidden="true" style="color: #43b9be;;" data-toggle="dropdown"></i>
                            	<%} %>
                                <!-- <i class="ti-bell dropdown-toggle" data-toggle="dropdown"></i> -->
                                <div class="dropdown-menu bell-notify-box notify-box">
                                    <%-- <span class="notify-title">You have 3 new notifications <a href="#">view all</a></span>
                                    <div class="nofity-list">
                                        <a href="#" class="notify-item">
                                            <div class="notify-thumb"><i class="ti-comments-smiley btn-info"></i></div>
                                            <div class="notify-text">
                                                <p>New Commetns On Post</p>
                                                <span>30 Seconds ago</span>
                                            </div>
                                        </a>
                                    </div> --%>
                                    <span class="notify-title">Notifications</span>
                                    <div class="nofity-list">
                                    	<s:if test="indentstaus==1">
	                                        <a href="#" class="notify-item" onclick="openSamePopup('transferdashboardProduct')">
	                                            <div class="notify-thumb"><i class="fa fa-bell" aria-hidden="true"></i></div>
	                                            <div class="notify-text">
	                                            	<p>Indent Request</p>
	                                               <%--  <span><img src="dashboardicon/newdiet.gif"></img></span> --%>
	                                            </div>
	                                        </a>
                                        </s:if>
                                        <s:if test="discountstatus==1">
                                        	<s:if test="noti_dis_reqtd_count>0">
                                        		<a href="#" class="notify-item" onclick="opencPopup('discountrequestdashboardProcessingAccount?countdata=1')">
		                                            <div class="notify-thumb"><i class="fa fa-bell" aria-hidden="true"></i></div>
		                                            <div class="notify-text">
		                                                <p>Discount Request <span style="color: red;font-size: larger;">(<s:property value="noti_dis_reqtd_count"/>)</span></p>
		                                               <%--  <span><img src="dashboardicon/newdiet.gif"></img></span> --%>
		                                            </div>
	                                        	</a>
                                        	</s:if>
                                        	<s:if test="noti_dis_apprd_count>0">
                                        		<a href="#" class="notify-item" onclick="opencPopup('discountrequestdashboardProcessingAccount?countdata=2')">
		                                            <div class="notify-thumb"><i class="fa fa-bell" aria-hidden="true"></i></div>
		                                            <div class="notify-text">
		                                                <p>Discount Request Approved <span style="color: red;font-size: larger;">(<s:property value="noti_dis_apprd_count"/>)</span></p>
		                                               <%--  <span><img src="dashboardicon/newdiet.gif"></img></span> --%>
		                                            </div>
	                                        	</a>
                                        	</s:if>
	                                    </s:if>
                                        <s:if test="refundstatus==1">
                                        	<s:if test="noti_ref_appr_count>0">
                                        		<a href="#" class="notify-item" onclick="opencPopup('refundrequestdashboardAdditional?countdata=0')">
		                                            <div class="notify-thumb"><i class="fa fa-bell" aria-hidden="true"></i></div>
		                                            <div class="notify-text">
		                                                <p>Refund Request <span style="color: red;font-size: larger;">(<s:property value="noti_ref_appr_count"/>)</span></p>
		                                               <%--  <span><img src="dashboardicon/newdiet.gif"></img></span> --%>
		                                            </div>
	                                        	</a>
                                        	</s:if>
                                        	<s:if test="noti_ref_reqtd_count>0">
                                        		<a href="#" class="notify-item" onclick="opencPopup('refundrequestdashboardAdditional')">
		                                            <div class="notify-thumb"><i class="fa fa-bell" aria-hidden="true"></i></div>
		                                            <div class="notify-text">
		                                                <p>Refund Request Approved <span style="color: red;font-size: larger;">(<s:property value="noti_ref_reqtd_count"/>)</span></p>
		                                               <%--  <span><img src="dashboardicon/newdiet.gif"></img></span> --%>
		                                            </div>
	                                        	</a>
                                        	</s:if>
	                                        
                                        </s:if>
                                        
                                        <div style="margin-left: 10%;">
                                        <div class="notify-thumb"><i class="fa fa-whatsapp" aria-hidden="true"></i></div>
                                         <div class="notify-text">
		                                                <p>Whatsapp Messages <span style="color: red;font-size: larger;">(<%=loginInfo.getWsmscount() %>)</span></p>
		                                              
		                                            </div>
		                                            <hr>
		                                 <div class="notify-thumb"><i class="fa fa-commenting" aria-hidden="true"></i></div>
                                         <div class="notify-text">
		                                                <p id=>Text Messages <span style="color: red;font-size: larger;">(<%=loginInfo.getSmscount() %>)</span></p>
		                                              
		                                            </div>
		                                </div>
		                                
                                    </div>
                                </div>
                            </li>
                            <li>
                            	<div class="dropdown show" style="margin-left: 0px;">
                                <a class="btn btn-danger dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <% if(loginInfo.getSuperadminid()==1){%>
                                    	Welcome, <%=loginInfo.getFirstName()%> <i class="fa fa-angle-down"></i>
                                    <%}else{ %>
                                    	Hi, <%=loginInfo.getFirstName()%> <i class="fa fa-angle-down"></i>
                                    <% }%>
                                    
                                </a>
                              
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink" style="min-width: 5rem !important">
                                	<% if(loginInfo.getSuperadminid()==1){%>
                                		 <a class="dropdown-item" href="logoutwithsessionLogout"><i class="fa fa-sign-out"></i>&nbsp;Logout With Session</a> 
                                		<a class="dropdown-item" href="Logout"><i class="fa fa-sign-out"></i>&nbsp;Logout</a>
                                	<%}else{ %>
                                	<!--  <a class="dropdown-item" href="logoutwithsessionLogout"><i class="fa fa-sign-out"></i>&nbsp;Logout With Session</a> -->
                                		<a class="dropdown-item" href="#" onclick="opencPopup('settingMainDashBoard')"><i class="fa fa-user"></i>&nbsp;Profile</a>
                                		<%if(!loginInfo.getLoginType().equals("mob")){ %>
                                			<%if(loginInfo.isLmh()){ %>
                                			<a class="dropdown-item" href="logoutwithsessionLogout"><i class="fa fa-sign-out"></i>&nbsp;Logout</a>
                                			
                                			<%}else{ %>
                                			
                                			<a class="dropdown-item" href="Logout"><i class="fa fa-sign-out"></i>&nbsp;Logout</a>
                                		
                                		<%} %>
                                		<% }%>
                                	<% }%>
	                                  <!-- <a class="dropdown-item" href="#">Something else here</a> -->
                                </div>
                               
                              </div>
                             </li>
                            
                            <li  style="display: none;"><img src="manasmaindashboard/images/Profile Picture_Placeholder_Circle.svg"></li>
                          
                        </ul>
                    </div>
                </div>
            </div>
          
            <div class="main-content-inner">
                
                <%if(loginInfo.isMaindash_graph()){ %>
                <div class="sales-report-area sales-style-two" style="display: ;">
                
                    <div class="container-fluid" style="background-color: #CEECF2;">
                    <p style="color: #15536E;font-weight: 600;padding: 20px 0px;font-size: 20px;">IPD (Hospital Inpatient Care)</p>
                    <div class="row" style="padding-bottom: 20px;">
                        
                        <div class="col-xl-3 col-ml-3 col-md-6 mt-2">
                             <div class="card mb-3" style="max-width: 540px;border-radius: 15px;">
                                 <div class="row no-gutters">
                                   <div class="col-md-6 card-1">
                                     <img src="manasmaindashboard/images/ICON_New IPD Admission.svg"  alt="...">
                                     <h1 style="display: inline;"><s:property value="ipd_admission"/></h1>
                                   </div>
                                   <div class="col-md-6">
                                     <div class="card-body">
                                       <p style="color: #15536E;font-weight: 600;">New IPD Admissions</p>      
                                     </div>
                                   </div>
                                 </div>
                               </div>
                        </div>
                        <div class="col-xl-3 col-ml-3 col-md-6 mt-2">
                            <div class="card mb-3" style="max-width: 540px;border-radius: 15px;">
                                <div class="row no-gutters">
                                  <div class="col-md-6 card-1">
                                    <img src="manasmaindashboard/images/Icon_In-House Patients.svg"  alt="...">
                                    <h1 style="display: inline;"><s:property value="ipd_inhouse"/></h1>
                                  </div>
                                  <div class="col-md-6">
                                    <div class="card-body ">
                                      <p style="color: #15536E;font-weight: 600;">In-House Patients</p>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </div>
                        <div class="col-xl-3 col-ml-3 col-md-6  mt-2">
                             <div class="card mb-3" style="max-width: 540px;border-radius: 15px;">
                                <div class="row no-gutters">
                                  <div class="col-md-6 card-1">
                                    <img src="manasmaindashboard/images/Icon_Discharge Initiaated.svg"  alt="...">
                                    <h1 style="display: inline;"><s:property value="ipd_discharge"/></h1>
                                  </div>
                                  <div class="col-md-6">
                                    <div class="card-body ">
                                      <p style="color: #15536E;font-weight: 600;">Discharge Patients</p>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </div>
                        <div class="col-xl-3 col-ml-3 col-md-6 mt-2">
                            <div class="card mb-3" style="max-width: 540px;border-radius: 15px;">
                                <div class="row no-gutters">
                                  <div class="col-md-6 card-1">
                                    <img src="manasmaindashboard/images/Icon_Vacant BEds.svg"  alt="...">
                                    <h1 style="display: inline;"><s:property value="ipd_vacantbed"/></h1>
                                  </div>
                                  <div class="col-md-6">
                                    <div class="card-body ">
                                      <p style="color: #15536E;font-weight: 600;">Vacant Beds</p>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>
                        </div>
                    </div>
                </div>
            </div>
                <!-- sales report area start -->
                <div class="container-fluid" style="display: ;">
                    <div class="col" style="padding-top: 30px;">
                        <h3 style="margin-left: -25px;font-size:20px">OPD (Outpatient Department)</h3>
                    </div>
                    <div class="row">
                        <!-- seo fact area start -->
                        <div class="col-lg-9">
                            <div class="row">
                                <div class="col-md-4 mt-5 mb-3" style="padding-left: 0px !important;">
                                    <div class="card mb-3" style="max-width: 540px;border-radius: 15px;box-shadow: 3px 4px 20px 10px #aaaaaa47;">
                                        <div class="row no-gutters">
                                          <div class="col-md-5 card-2" style="box-shadow: 1px 1px 1px 1px #8080802b;">
                                            <img src="manasmaindashboard/images/Patientets Registered_Icon.svg"  alt="...">
                                            <h1 style="display: inline;"><s:property value="opd_booked"/></h1>
                                            <h2 >Patients Registered</h2>
                                          </div>
                                          <div class="col-md-7" style="text-align: center;">
                                            <canvas id="foo"  class="gauge-1"></canvas>
                                          </div>
                                        </div>
                                      </div>
                                </div>
                                <div class="col-md-4 mt-5 mb-3" style="padding-left: 0px !important;">
                                    <div class="card mb-3" style="max-width: 540px;border-radius: 15px;box-shadow: 3px 4px 20px 10px #aaaaaa47;">
                                        <div class="row no-gutters">
                                          <div class="col-md-5 card-2" style="box-shadow: 1px 1px 1px 1px #8080802b;">
                                            <img src="manasmaindashboard/images/Icon_Total OPD_.svg"  alt="...">
                                            <h1 style="display: inline;"><s:property value="opd_completed"/></h1>
                                            <h2 >Total OPD Completed</h2>
                                          </div>
                                          <div class="col-md-7" style="text-align: center;">
                                            <canvas id="foo-1" class="gauge-1"></canvas>
                                          </div>
                                        </div>
                                      </div>
                                </div>
                                <div class="col-md-4 mt-5 mb-3" style="padding-left: 0px !important;">
                                    <div class="card mb-3" style="max-width: 540px;border-radius: 15px;box-shadow: 3px 4px 20px 10px #aaaaaa47;">
                                        <div class="row no-gutters">
                                          <div class="col-md-5 card-2" style="box-shadow: 1px 1px 1px 1px #8080802b;">
                                            <img src="manasmaindashboard/images/Total DNA_Icon.svg"  alt="...">
                                            <h1 style="display: inline;"><s:property value="opd_dna"/></h1>
                                            <h2 >Total Count: OPD DNA</h2>
                                          </div>
                                          <div class="col-md-7" style="text-align: center;">
                                            <canvas id="foo-2" class="gauge-1"></canvas>
                                          </div>
                                        </div>
                                      </div>
                                </div>
                                <div class="col-md-6 mb-3 mb-lg-0 card-3" >
                                    <div class="row " style="padding-top: 30px;">
                                        <div class="col-3 data" style="max-width: 18%;">
                                            <h1>Patients Visit:<br><span style="font-weight: 600;color: #15536E;font-size: 16px;margin-left: 0px;">Age-Wise</span></h1>
                                        </div>
                                        <div class="col-0.5 mob-1" style="margin-left: -6px;">
                                            <img src="manasmaindashboard/images/Upto 20 years_Icon.svg" >
                                            
                                        </div>
                                        <div class="col-1.5 data" style="margin-left: -6px;">
                                            <h1><s:property value="upto20"/>%</h1>
                                            <span>out of total patients</span>
                                            <hr style="width: 90%;
                                            margin-left: 7px;margin-top:0rem;margin-bottom:0rem">
                                            <h3 >Upto 20 Years </h3>

                                        </div>
                                        <!-- <div class="col-0.5 mob-2" style="margin-left: 10px;"> -->
                                        <div class="col-0.5 mob-2" >
                                            <img src="manasmaindashboard/images/21-40 Years_Icon.svg">
                                            
                                        </div>
                                        <div class="col-1.5 data" style="margin-left: -6px;">
                                            <h1 ><s:property value="upto40"/>%</h1>
                                            <span>out of total patients</span>
                                            <hr style="width: 90%;margin-left: 7px;margin-top:0rem;margin-bottom:0rem">
                                            <h3 >21-40 Years </h3>

                                        </div>
                                        <div class="col-0.5 mob-3" >
                                            <img src="manasmaindashboard/images/41-60 years_Icon.svg" >
                                            
                                        </div>
                                        <div class="col-1.5 data" style="margin-left: -6px;">
                                            <h1 ><s:property value="upto60"/>%</h1>
                                            <span >out of total patients</span>
                                            <hr style="width: 90%;margin-left: 7px;margin-top:0rem;margin-bottom:0rem">
                                            <h3 >41-60 Years </h3>
											
                                        </div>
                                        <!-- <div class="col-0.5 mob-4" style="margin-left: 10px;"> -->
                                        <div class="col-0.5 mob-4">
                                            <img src="manasmaindashboard/images/Above 60 years_Icon.svg">
                                            
                                        </div>
                                        <div class="col-1.5 data" style="margin-left: -6px;">
                                            <h1 ><s:property value="above60"/>%</h1>
                                            <span >out of total patients</span>
                                            <hr style="width: 90%;
                                            margin-left: 7px;margin-top:0rem;margin-bottom:0rem">
                                            <h3 >60+ Years </h3>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 card-4">
                                    <div class="row " style="padding-top: 25px;padding-bottom: 20px;">
                                    <div class="col-3 patients-visit">
                                        <h1>Patients Visit:<br><span style="font-weight: 600;font-size:16px;color: #15536E;">Gender-Wise</span></h1>
                                    </div>
                                    <div class="col-1 ">
                                        <img src="manasmaindashboard/images/Male Icon.svg">
                                    </div>
                                    <div class="col-3 card-5">
                                        <h1 ><s:property value="total_male"/>%</h1>
                                            <p>out of total patients<br>
                                            were found to be male</p>
                                    </div>
                                    <div class="col-1">
                                        <img src="manasmaindashboard/images/Female Icon.svg">
                                    </div>
                                    <div class="col-3 card-5">
                                        <h1 style="color:#15bfa5" ><s:property value="total_female"/>%</h1>
                                            <p >out of total patients<br>
                                            were found to be male</p>
                                    </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- seo fact area end -->
                        <!-- Social Campain area start -->
                        <div class="col-lg-3">
                            <figure class="highcharts-figure">
                                <div id="container"></div>
                               
                              </figure>
                              
                        </div>
                <!-- row area end -->
                 
               </div>
               </div>
               <%} %>
                <!-- row area start-->
                <%if(!loginInfo.getWarningmsg().equals("")){ %>
                	<p style="margin-left: -19px;">
                		<marquee bgcolor="#ceecf2" behavior="alternate" scrolldelay="100" scrollamount="3"style="font-size: 21px;font-weight: bold;"><%=loginInfo.getWarningmsg()%></marquee>
                	</p>
               	<%} %> 
                <div class="container-fluid respo-class">
                    <div class="row mt-10">
                        <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                          
<script type="text/javascript">
    window.setInterval(ut, 1000);

function ut() {
  var d = new Date();
  document.getElementById("time").innerHTML = d.toLocaleTimeString();
  var datetime = d.toLocaleDateString().split("/");
  var datetime_month=datetime[0];
  if(datetime[0]<10){
	  datetime_month= "0"+datetime_month;
  }
  var datetime1 = datetime[1]+"/"+datetime_month+"/"+datetime[2];
  document.getElementById("date").innerHTML = datetime1;
}
</script>
                        </div>
                        <div class="col-lg-9 col-md-6 col-sm-6 col-xs-12"><p class="text-danger pull-right hidden"><i class="fa fa-clock-o text-info" aria-hidden="true"></i> 352 Days Remaining </p></div>
                        <div class="col-lg-11 col-md-12 col-sm-12 col-xs-12 mt-10">
                         <s:if test="%{eventList.size>0}">   
                        <div class="d-flex justify-content-between align-items-center breaking-news bg-white">
                			<div class="d-flex flex-row flex-grow-1 flex-fill justify-content-center bg-info py-2 text-white px-1 news"><span class="d-flex align-items-center">&nbsp;Events</span></div>
                			<marquee class="news-scroll" style="font-size: larger;" scrolldelay="100" scrollamount="5" behavior="scroll" direction="left" onmouseover="this.stop();" onmouseout="this.start();"> 
                				<%-- <a href="#">Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </a> <span class="dot"></span> <a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut </a> <span class="dot"></span> <a href="#">Duis aute irure dolor in reprehenderit in voluptate velit esse </a> --%> 
                				<s:iterator value="eventList">
                					<small class="text-thin">
                						<i class="fa fa-calendar"></i> <s:property value="fromdate"/>, &nbsp;
                						<i class="fa fa-clock-o"></i> <s:property value="time"/>,
                						<s:if test="place!=''">
                							Venue: <s:property value="place"/>,
                						</s:if>
                						<a href="#"><s:property value="eventname"/></a> 
                						<s:property value="description"/>
                						
                					</small>
                					
                					<span class="dot"></span> 
                				</s:iterator>
                			</marquee>
            			</div>
            			</s:if>

                        </div>
                    </div>
                    <div class="row">
                    <div class="col-5">
                        <h3 style="margin-left: -9px;font-size:1rem;color: #15536E;">&nbsp;&nbsp;Departmental Overview</h3>
                        
                    </div> 
                    <div class="col-1">
					   </div>
					   
                        <div class="col-6">
                        <div id="div1" style="max-width: 100%;float: right;">
					    <span id="time"></span>
					    <span id="date"></span>
					  </div>  
					  </div>
					   
                    </div>
                   
                    <div class="row dashboard-dash-design" style="padding-top: 0px;">
                   				 
	                            <%if(loginInfo.isRegistrationdash()){ %>
	                            <div class="col-2 card-small-1" onclick="opencPopup('addCompleteInfoRegistration')" style="cursor: pointer;">
	                      			<a href="#">
	                                	<p style="display: inline;">Registration</p>
	                                	<img src="manasmaindashboard/images/My opd_icon.svg" class="im-1">
	                                </a>
	                            </div>
	                            <%} %>
	                              <%if(loginInfo.isLmh()){ %>
	                            <div class="col-2 card-small-1" onclick="opencPopup('departmentopdFinder')" style="cursor: pointer;">
	                      			<a href="#">
	                                	<p style="display: inline;">Department OPD</p>
	                                	<img src="manasmaindashboard/images/My opd_icon.svg" class="im-1">
	                                </a>
	                            </div>
	                            <%} %> 
	                        <% if(loginInfo.isManageopd()){%>
	                          	<%-- <div class="col-2 card-small-1" onclick="opencPopup('calNotAvailableSlot?actionType=newopd&doctor=<%=loginInfo.getId()%>')" style="cursor: pointer;"> --%>
	                      		<div class="col-2 card-small-1" onclick="opencPopup('calSlotAvailable?actionType=newopd&doctor=<%=loginInfo.getId()%>')" style="cursor: pointer;">
	                      			<a href="#">
	                                	<p style="display: inline;">OPD</p>
	                                	<img src="manasmaindashboard/images/My opd_icon.svg" class="im-1">
	                                </a>
	                            </div>
	                        <%} %>
	                        <% if(loginInfo.isManageipd()){%>
	                        	 <%if(loginInfo.isDirect_ipd()){ %>  
	                        	 	<div class="col-2 card-small-1" style="cursor: pointer;" data-toggle="modal" data-target="#wards">
	                        	 		 <a href="#"  class="colortog" >
	                                		<p style="display: inline;">IPD</p>
	                                		<img src="manasmaindashboard/images/IPD_Icon.svg" class="im-2" >
	                                	 </a>
	                            	</div>
	                        	 <%}else{ %>
	                        	 	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('IpdDashboard?action=0')">
	                                	<a href="#" >
	                                		<p style="display: inline;">IPD</p>
	                                		<img src="manasmaindashboard/images/IPD_Icon.svg" class="im-2" >
	                                	</a>
	                                </div>
	                        	 <%} %> 
		                    <%} %>
	                        
                             <% if(loginInfo.isOt()){%>
	                            <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('otdbBookAppointmentAjax')" >
	                            	<a href="#" >
	                            	<%if(loginInfo.isSimpliclinic()) {%>
	                            	<p style="display: inline;">PROCEDURE</p>
	                            	<%}else{ %>
		                                <p style="display: inline;">OT</p>
		                                <%} %>
		                                <img src="manasmaindashboard/images/OT_Icon.svg" class="im-3">
	                                </a>
	                            </div>
                             <%} %>
                             
                            <%if(loginInfo.isCasualty()){ %>
	                            <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('IpdDashboard?action=1')">
	                            	 <a href="#"  >
	                            	 	<p style="display: inline;">Casualty</p>
	                                	<img src="manasmaindashboard/images/My opd_icon.svg" class="im-4">
	                            	 </a>
	                            </div>
                            <%} %>
                            
                            <% if(loginInfo.isManageemr()){%>
                             <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('getPatientRecordConsultationNote')">
	                            <!-- <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('getPatientRecordEmr')"> -->
	                            	<a href="#" >
	                                	<p style="display: inline;">EMR</p>
	                                	<img src="manasmaindashboard/images/EMR_Icon.svg" class="im-5">
	                                </a>
	                            </div>
                            <%} %>
                            <%if(loginInfo.isPacks() || loginInfo.getSuperadminid()==1){ %>
                            	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('Pacs?clientid=0')">
                            		<a href="#" >
                            			<p style="display: inline;">PAC's</p>
	                                	<img src="manasmaindashboard/images/PAC's_Icon.svg" class="im-6">
                            		</a>
	                            </div>
                            <%} %>
                            <%if(loginInfo.isDischarge()){ %>
	                            <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('InitialDischarge')">
	                            	<a href="#" >
		                                <p style="display: inline;">Discharge</p>
		                                <img src="manasmaindashboard/images/Discharge_Icon.svg" class="im-7">
	                            	</a>
	                            </div>
                            <%} %>
                            <% if(loginInfo.isManageprisc()){%>
                                <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('Prescription')">
                                	<a href="#" >
                                		<p style="display: inline;">Prescription</p>
	                                	<img src="manasmaindashboard/images/Prescription_Icon.svg" class="im-8">
                                	</a>
	                      		</div>
							<%} %>
							
								<%if(loginInfo.isSale_pharmacy()){ %>
									<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('salepriscPharmacy')">
                           				<a href="#" >
		                                    <p style="display: inline;">Sale Pharmacy</p>
		                                    <img src="manasmaindashboard/images/Pharmacy_Icon.svg" class="im-9">
		                                </a>
                                	</div>
								<%} %>
                           		<%if(loginInfo.isPharmacy()){ %>
                           			<!-- <div class="col-1.5 card-small" >
                           				<a href="#" onclick="openSamePopup('salepriscPharmacy')">
		                                    <p style="display: inline;">Sale Pharmacy</p>
		                                    <img src="manasmaindashboard/images/Pharmacy_Icon.svg" class="im-9">
		                                </a>
                                	</div> -->
                                	
                                	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('onlinerequestpharPharmacy')">
                                		<a href="#" >
		                                    <p style="display: inline;">Pharmacy</p>
		                                    <img src="manasmaindashboard/images/Pharmacy_Icon.svg" class="im-9">
		                                </a>
                                	</div>
                                	<!-- <div class="col-2 card-small-1" >
                           				<a href="#" onclick="openSamePopup('salepriscPharmacy')">
		                                    <p style="display: inline;">Sale Pharmacy</p>
		                                    <img src="manasmaindashboard/images/Pharmacy_Icon.svg" class="im-9">
		                                </a>
                                	</div> -->
                           		 <%} %>
                              	<% if(loginInfo.isInventory()){%>
	                                <div class="col-2 card-small-1" style="cursor: pointer;" onclick="openSamePopup('listProduct?isfromcathlab=0')">
	                                	<a href="#" >
		                                    <p style="display: inline;">Inventory</p>
		                                    <img src="manasmaindashboard/images/Inventory_Icon.svg" class="im-10">
		                                </a>
	                                </div>
                                <%} %>
                                <%if(loginInfo.isCathlab()){ %>
	                                <div class="col-2 card-small-1" style="cursor: pointer;" onclick="openSamePopup('listProduct?isfromcathlab=1')">
	                                	<a href="#" >
		                                    <p style="display: inline;">CathLab</p>
		                                    <img src="manasmaindashboard/images/CathLab_Icon.svg" class="im-11">
		                                </a>
	                                </div>
                                 <%} %>
                                 <%if(loginInfo.isIndent()){ %>
                                 	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="openSamePopup('transferdashboardProduct')">
                                 		<a href="#" >
		                                    <p style="display: inline;">Indent</p>
		                                    <img src="manasmaindashboard/images/Indent_Icon.svg" class="im-12">
		                                </a>
                                	</div>
                                 <%} %>
                                 
								<% if(loginInfo.isManageinvst()){%>
	                                <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('Investigation')">
	                                	<a href="#" >
		                                    <p style="display: inline;">Investigation</p>
		                                    <img src="manasmaindashboard/images/Investigation)Icon.svg"  class="im-13">
		                                </a>
	                                </div>
                                <%} %>
                        
                                
                                <% if(loginInfo.isTpa()){%>
	                                <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('Tpa')">
	                                	<a href="#" >
		                                    <p style="display: inline;">TPA</p>
		                                    <img src="manasmaindashboard/images/TPA_Icon.svg" class="im-14">
		                                </a>
	                                </div>
                                <%} %>
                                <% if(loginInfo.isInvestigation_chart()){%>
	                                <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('templateInvestigation')">
	                                	<a href="#" >
		                                    <p style="display: inline;">Analytics</p>
		                                    <img src="manasmaindashboard/images/Analytics_Icon.svg" class="im-15">
		                                </a>
	                                </div>
	                            <%} %>
	                            <% if(loginInfo.isPayroll()){%>
	                            		<%if(loginInfo.isPayrollaccess()) {%>
	                            			<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('PayrollEmployee?status=1')">
		                            			<a href="#" >
			                            			<p style="display: inline;">Payroll</p>
			                                    	<img src="manasmaindashboard/images/Payrol_Icon.svg" class="im-16">
			                                    </a>
		                                    </div>
	                            		<%}else{ %>
	                            			<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('PayrollEmployee?status=0')">
		                            			<a href="#" >
			                            			<p style="display: inline;">Payroll</p>
			                                    	<img src="manasmaindashboard/images/Payrol_Icon.svg" class="im-16">
			                                    </a>
		                                    </div>
	                            		<%} %>
	                            <%} %>
                                
    
                                <% if(loginInfo.isExpences()){%>
                                  <div class="col-2 card-small-1 hidden" style="cursor: pointer;" onclick="opencPopup('ExpenceManagement?action=0')">
                                  		<a href="#" >
	                                        <p style="display: inline;">Voucher</p>
	                                        <img src="manasmaindashboard/images/Voucher_Icon.svg" class="im-17">
                                    	</a>
                                    </div>
                                <%} %>
                                <% if(loginInfo.isFullFinance()){%>
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('ExpenceManagement?action=0')">
                                    	<a href="#" >
	                                        <p style="display: inline;">Accounts</p>
	                                        <img src="manasmaindashboard/images/Accounts_Icon.svg" class="im-18">
                                        </a>
                                    </div>
                                <%} %>
                                <%if(loginInfo.isMisaccess()) {%>
                        			<% if(loginInfo.isManagemis()){%>
                        				<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('MisChart')">
	                        				<a href="#" >
			                                        <p style="display: inline;">MIS</p>
			                                        <img src="manasmaindashboard/images/MIS_Icon.svg" class="im-19">
			                                </a>
		                                </div>
                                    <%} %>
                                <%} %>
                                <% if(loginInfo.isManageclient()){%>
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('manageClient')">
                                    	<a href="#" >
	                                        <p style="display: inline;">Patients</p>
	                                        <img src="manasmaindashboard/images/Patientes_Icon.svg" class="im-20">
	                                    </a>
                                    </div>
                                <%} %>
                                <%if(loginInfo.isMrd()){ %>
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('Mrd')">
                                    	<a href="#" >
	                                        <p style="display: inline;">MRD</p>
	                                        <img src="manasmaindashboard/images/MRD_Icon.svg" class="im-19">
	                                    </a>
                                    </div>
                                <%} %>
                                
                               
                                <%if(loginInfo.isDietery()){ %>
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('dietaryDietarydetails')">
                                    	<a href="#" >
	                                        <p style="display: inline;">Dietary</p>
	                                        <img src="manasmaindashboard/images/Dietery_Icon.svg"  class="im-23">
	                                    </a>
                                    </div>
                                <%} %>
                                
                                 <% if(loginInfo.getSuperadminid()==1 || loginInfo.isRefund()) {%>  
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('refundrequestdashboardAdditional')">
                                    	<a href="#" >
	                                        <p style="display: inline;">Refund</p>
	                                        <img src="manasmaindashboard/images/Refund_Icon.svg" class="im-24">
	                                    </a>
                                    </div>
                                <%} %>
        
                                
        
                                    <% if(loginInfo.getSuperadminid()==1 ||loginInfo.isDiscount()) {%>  
                                      <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('discountrequestdashboardProcessingAccount')">
                                      		<a href="#" >
                                            	<p style="display: inline;">Discount</p>
                                            	<img src="manasmaindashboard/images/Discount_Icon.svg" class="im-25">                                            
                                        	</a>
                                        </div>
                                    <%} %> 
                                    <% if(loginInfo.isNabh_quality()){%>
                                        <div class="col-2 card-small-1" style="cursor: pointer;" onclick="openSamePopup('Nabh')">
                                        	<a href="#" >
	                                            <p style="display: inline;">NABH</p>
	                                            <img src="manasmaindashboard/images/NABH_Icon.svg"  class="im-36">
	                                        </a>
                                        </div>
                                    <%} %>
                                    
                                    <%if(loginInfo.isMarketing()){ %>
                                        <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('marketingDiaryMangent')">
                                        	<a href="#" >
	                                            <p style="display: inline;">CRM</p>
	                                            <img src="manasmaindashboard/images/CRM_Icon.svg" class="im-28">
                                            </a>
                                        </div>
                                    <%} %>
                                    
                                     <% if(loginInfo.isDaycare()){%>
                                        <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('IpdDashboard?action=2')">
                                        	<a href="#" class="" >
	                                            <p style="display: inline;">Day Care</p>
	                                            <img src="manasmaindashboard/images/DayCAre_Icon.svg" class="im-37">
	                                        </a>
                                        </div>
                                    <%} %>
                                    
                                    <% if(loginInfo.isPackages()){%>
		                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('packagelistPackageMaster')">
		                                    	<a href="#" >
			                                        <p style="display: inline;">Packages</p>
			                                        <img src="manasmaindashboard/images/Packages.png" class="im-38" >
			                                    </a>
		                                    </div>
		                                <%} %>
                                
                                
                                    <%if(loginInfo.isCommunication()){ %>
                                        <div class="col-2 card-small-1" style="cursor: pointer;" onclick="openSamePopup('viewvisitingconsultIpdAjax')">
                                        	<a href="#" >
                                        		<p style="display: inline;">Consultants</p>
                                            	<img src="manasmaindashboard/images/Consultants_Icon.svg" class="im-39" >
                                        	</a>
                                        </div>  
                                        	
                                	<%} %>
                                	
                                    
                                     <%if(loginInfo.isBloodbak()){ %>
                                 	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="openSamePopup('requestbloodBloodbank')">
                                 		<a href="#" >
		                                    <p style="display: inline;">Blood Bank</p>
		                                    <img src="manasmaindashboard/images/Blood Bank.png"  class="im-40">
		                                </a>
                                	</div>
                                 <%} %>
                                 
                               
                                <%if(loginInfo.isSheduler()){ %>
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('staffNotAvailableSlot')">
                                    	<a href="#" >
	                                        <p style="display: inline;">Scheduler</p>
	                                        <img src="manasmaindashboard/images/Scheduler.png" class="im-38">
	                                    </a>
                                    </div>
                                <%} %>
                                
                                <% if(loginInfo.isVaccination()) {%>
                                         <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('vaccinationdashboardFinder')">
                                        	<a href="#" class=""  >
	                                            <p style="display: inline;">Vaccination</p>
	                                            <img src="manasmaindashboard/images/Vaccination_Icon.svg" class="im-31">
                                            </a>
                                        </div>
                                    <%} %>
                                
                                 <% if(loginInfo.isSjivh()) {%>
                                         <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('dewormingdashboardFinder')">
                                        	<a href="#" class=""  >
	                                            <p style="display: inline;">Deworming</p>
	                                            <img src="manasmaindashboard/images/Vaccination_Icon.svg" class="im-31">
                                            </a>
                                        </div>
                                    <%} %>
                                 <%if(loginInfo.isAmbulance() || loginInfo.getJobTitle().equals("DRIVER")){ %>
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('Duty')">
                                    	<a href="#" >
	                                        <p style="display: inline;">Ambulance</p>
	                                        <img src="manasmaindashboard/images/Ambulance.png"  class="im-41">
	                                    </a>
                                    </div>
                                <%} %>
                                
                                 <%if(loginInfo.isCafeteria()){ %>
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('Cafeteria')">
                                    	<a href="#" >
	                                        <p style="display: inline;">Cafeteria</p>
	                                        <img src="manasmaindashboard/images/Cafeteria.png"  class="im-41">
	                                    </a>
                                    </div>
                                <%} %>
                                
                                <% if(loginInfo.isHousekeeping()) { %>
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('laundryHousekeepingdashboard')">
                                    	<a href="#" >
	                                        <p style="display: inline;">Housekeeping</p>
	                                        <img src="manasmaindashboard/images/Housekeeping_Icon.svg"   class="im-42">
	                                    </a>
                                    </div>
                                <%} %>
                                
                                 <%if(loginInfo.isBank_deposite()){ %>
                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('Bankdeposite')">
                                    	<a href="#" >
	                                        <p style="display: inline;font-size: 14px;">Bank Deposit</p>
	                                        <img src="manasmaindashboard/images/Bank Deposit.png"  class="im-43" >
	                                    </a>
                                    </div>
                                <%} %>
                                    
                                    <%if(loginInfo.isVoice_recording()){ %>
                                        <div class="col-2 card-small-1" style="cursor: pointer;"  onclick="openPopup('recordingDiaryMangent')">
                                        	<a href="#">
	                                            <p style="display: inline;font-size: 14px;">Voice Recorder</p>
	                                            <img src="manasmaindashboard/images/Voice Recorder_Icon.svg"  class="im-44">
	                                        </a>
                                        </div>
                                    <%} %>
                                     <%if(loginInfo.isEmergency_lbl()){ %>
                                        <div class="col-2 card-small-1" style="cursor: pointer;" onclick="openPopup('emergencylabelDiaryMangent')">
                                        	<a href="#" >
	                                            <p style="display: inline;font-size: 14px;">Emergency Label</p>
	                                            <img src="manasmaindashboard/images/Emergency Label_02.png"  >
	                                        </a>
                                        </div>
                                    <%} %>
                                   
                                    
                                    <%if(loginInfo.isVideo_training()){ %>
                                        <div class="col-2 card-small-1" style="cursor: pointer;" onclick="openSamePopup('hisvideotutorialNabh')">
                                            <a href="#" >
	                                            <p style="display: inline;font-size: 14px;">Video Training</p>
	                                            <img src="manasmaindashboard/images/Video Training_Icon.svg" >
	                                        </a>
                                        </div>
                                     <%} %>
                                  		
		                                <% if(loginInfo.isToken_display()){%>
		                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('dsplayBookAppointmentAjax')">
		                                    	<a href="#" >
			                                        <p style="display: inline;font-size: 14px;">Token Display</p>
			                                        <img src="manasmaindashboard/images/Token Display_Icon.svg" >
			                                    </a>
		                                    </div>
		                                <%} %>
		                                
		                                <% if(loginInfo.isMedicine_barcode()){%>
		                                    <div class="col-2 card-small-1" >
		                                    	<a href="http://localhost:8080/YUVICARE/medicinebarcodePharmacy?isfromdashbaord=1&isbalgopal=<%=loginInfo.isBalgopal()%>&country=<%=loginInfo.getCountry()%>" target="_blank">
			                                        <p style="display: inline;font-size: 14px;">Product Barcode</p>
			                                        <img src="manasmaindashboard/images/Product Barcode.png"  >
			                                    </a>
		                                    </div>
		                                <%} %>
		                                
		                                
                                
                                
                                	
                                	<% if(loginInfo.isApmtfinder()){%>
	                                    <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('inputFinder')">
	                                    	<a href="#" >
		                                        <p style="display: inline;font-size: 14px;">Appointment Finder</p>
		                                        <img src="manasmaindashboard/images/Appointment_Icon.svg" >
		                                    </a>
	                                    </div>  
                                	<%} %>
                                	
                                	<% if(loginInfo.getSuperadminid()==1 ||loginInfo.isSetup_master()) {%>
		                                  	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('showallbookBook?selectedid=62')">
		                                        <a href="#" >
			                                        <p style="display: inline;font-size: 14px;">Setup Master</p>
			                                        <img src="manasmaindashboard/images/Setup Master_Icon.svg"  >
			                                    </a>
		                                    </div>
		                                <%} %>
		                                
		                                <% if(loginInfo.isDelete_invoice_history() || loginInfo.isDelete_invoice()) {%>
		                                  	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('allinvoicelistStatement')">
		                                        <a href="#" >
			                                        <p style="display: inline;font-size: 14px;">Delete Invoice</p>
			                                        <img src="manasmaindashboard/images/Invoice Delete.svg"  style="max-width: 23%">
			                                    </a>
		                                    </div>
		                                <%} %>
		                                
		                                  <% if(loginInfo.isLmh()) {%> 
		                                  	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('listadmissionFinder')">
		                                        <a href="#" >
			                                        <p style="display: inline;font-size: 14px;">Admission</p>
			                                        <img src="manasmaindashboard/images/collegeadmission.png"  style="max-width: 20%">
			                                    </a>
		                                    </div>
		                                <%} %> 
		                                <%--  <% if(!loginInfo.isSmallClinic() || loginInfo.getSuperadminid()==1) {%> 
		                                  	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('referedpatientreportFinder')">
		                                        <a href="#" >
			                                        <p style="display: inline;font-size: 14px;">PRO</p>
			                                        <img src="manasmaindashboard/images/proicon.png"  style="max-width: 20%">
			                                    </a>
		                                    </div>
		                                <%} %> commneted @manoj sir said --%>
		                                
		                                <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('referedpatientreportFinder')">
		                                        <a href="#" >
			                                        <p style="display: inline;font-size: 14px;">PRO</p>
			                                        <img src="manasmaindashboard/images/proicon.png"  style="max-width: 20%">
			                                    </a>
		                                    </div>
		                                <% if((!loginInfo.isAmbulance() || loginInfo.getSuperadminid()==1) && loginInfo.getJobTitle().equals("Admin")) {%> 
		                                  	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('ambulancereportDuty')">
		                                        <a href="#" >
			                                        <p style="display: inline;font-size: 14px;">Ambulance</p>
			                                        <img src="manasmaindashboard/images/Ambulance.png"  style="max-width: 20%">
			                                    </a>
		                                    </div>
		                                <%} %>
		                                
		                                 <% if(loginInfo.isBams1()) {%> 
		                                  	<div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('punchkarmareportDuty')">
		                                        <a href="#" >
			                                        <p style="display: inline;font-size: 14px;">Punchkarma</p>
			                                        <img src="manasmaindashboard/images/Ambulance.png"  style="max-width: 20%">
			                                    </a>
		                                    </div>
		                                 <%} %>
		                                
		                                
		                                 <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('physioipdFinder')">
		                                        <a href="#" >
			                                        <p style="display: inline;font-size: 14px;">Physio Ipd</p>
			                                        <img src="manasmaindashboard/images/Ambulance.png"  style="max-width: 20%">
			                                    </a>
		                                    </div>
		                               <div class="col-2 card-small-1" style="cursor: pointer;" onclick="opencPopup('approvedlimitqtyProduct')">
		                                        <a href="#" >
			                                        <p style="display: inline;font-size: 14px;">Approve Limit</p>
			                                        <img src="manasmaindashboard/images/Ambulance.png"  style="max-width: 20%">
			                                    </a>
		                                    </div>     
		                             
                     
                    </div>
                    </div>
                </div>
        </div>
        <!-- main content area end -->
        <!-- footer area start-->
        <footer>
            <div class="footer-area">
                
            </div>
        </footer>
        <!-- footer area end-->
    </div>
    <!-- page container area end -->
    
<div id="wards" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content" style="">
      <div class="modal-header">
        <h4 class="modal-title"> Select Ward For Ipd </h4>
      	<button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
   <%--  <div class="form-group"  style="width: 100%">
				<s:select name="wardid"  style="width: 100%" list="wardlist" listKey="id" listValue="wardname"  multiple="" cssClass="form-control showToolTip chosen-select" headerKey="" headerValue="All Wards" id="newwardid" ></s:select>
			</div> --%>
			
			<div class="row" style="display: none;">
				
				<a href="#" onclick="openIpd('0')" data-dismiss="modal" class="btn btn-info col-3" style="text-align: center;padding-bottom: 10px;">All Wards</a>
				<div class="col-0.5" style="padding-bottom: 10px"></div>
				<s:iterator value="wardlist">
					<a href="#" onclick="openIpd('<s:property value="id"/>')" data-dismiss="modal" class="btn btn-info col-3" style="text-align: center;padding-bottom: 10px;"><s:property value="wardname"/></a>
					<div class="col-0.5" style="padding-bottom: 10px"></div>
				</s:iterator> 
			</div>
			
			<div class="col-lg-12 col-md-12 col-sm-12" >
				<div class="col-lg-3 col-md-3 col-sm-3" onclick="openIpd('0')" data-dismiss="modal" style="margin: 3px;border: 3px solid #ddd;border-radius: 4px;padding: 15px;width: 140px;background-color: #4eb6c2;">
					<p align="center" style="color: white">All Wards</p>
				</div>
				<s:iterator value="wardlist">
					<div class="col-lg-3 col-md-3 col-sm-3"  onclick="openIpd('<s:property value="id"/>')" data-dismiss="modal" style="margin: 3px;border: 3px solid #ddd;border-radius: 4px;padding: 15px;width: 140px;background-color: #4eb6c2;">
						<p align="center" style="color: white"><s:property value="wardname"/></p>
					</div>
				</s:iterator>
      		</div>
			
			
      <div class="modal-footer">
       <!--  <input type="button" class="btn btn-danger" onclick="openIpd()" data-dismiss="modal" value="Ok"> -->
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        
      </div>
    </div>

  </div>
</div>
</div>

<div id="changelanguage" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content" style="">
      <div class="modal-header">
        <h4 class="modal-title"> Select Language </h4>
      	<button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
      		<div class="col-lg-12 col-md-12 col-sm-12" >
      			Your Language:	<%if(loginInfo.getRegional_lang().equals("en")){ %>
      								English
      							<%}else if(loginInfo.getRegional_lang().equals("gu")){ %>
      								Gujarati
      							<%}else if(loginInfo.getRegional_lang().equals("hi")){ %>
      								Hindi
      							<%}else if(loginInfo.getRegional_lang().equals("mr")){ %>
      								Marathi
      							<%} %>
      		</div>
  			<div class="col-lg-12 col-md-12 col-sm-12" >				
					<div class="col-lg-3 col-md-3 col-sm-3"  onclick="changeuserlanguage('en')" data-dismiss="modal" style="margin: 3px;border: 3px solid #ddd;border-radius: 4px;padding: 15px;width: 140px;background-color: #4eb6c2;cursor: pointer;">
						<p align="center" style="color: white">English</p>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3"  onclick="changeuserlanguage('gu')" data-dismiss="modal" style="margin: 3px;border: 3px solid #ddd;border-radius: 4px;padding: 15px;width: 140px;background-color: #4eb6c2;cursor: pointer;">
						<p align="center" style="color: white">Gujarati</p>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3"  onclick="changeuserlanguage('hi')" data-dismiss="modal" style="margin: 3px;border: 3px solid #ddd;border-radius: 4px;padding: 15px;width: 140px;background-color: #4eb6c2;cursor: pointer;">
						<p align="center" style="color: white">Hindi</p>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3"  onclick="changeuserlanguage('mr')" data-dismiss="modal" style="margin: 3px;border: 3px solid #ddd;border-radius: 4px;padding: 15px;width: 140px;background-color: #4eb6c2;cursor: pointer;">
						<p align="center" style="color: white">Marathi</p>
					</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" >
				<span style="color:red;">Note*:</span><span>Please logout and then login after select language.</span>
			</div>
			<div class="modal-footer">
		    	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		    </div>
    </div>

  </div>
</div>
</div>
    
    
    <!-- jquery latest version -->
    <script src="manasmaindashboard/js/vendor/jquery-2.2.4.min.js"></script>
    <!-- bootstrap 4 js -->
    <script src="manasmaindashboard/js/popper.min.js"></script>
    <script src="manasmaindashboard/js/bootstrap.min.js"></script>
    <script src="manasmaindashboard/js/owl.carousel.min.js"></script>
    <script src="manasmaindashboard/js/metisMenu.min.js"></script>
    <script src="manasmaindashboard/js/jquery.slimscroll.min.js"></script>
    <script src="manasmaindashboard/js/jquery.slicknav.min.js"></script>
    <script src="manasmaindashboard/js/gauge.min.js"></script>

    <!-- start chart js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
    
   
    <script src="manasmaindashboard/js/pie-chart.js"></script>
    <!-- others plugins -->
    <script src="manasmaindashboard/js/plugins.js"></script>
    <script src="manasmaindashboard/js/scripts.js"></script>
    <script src="manasmaindashboard/js/highcharts.js"></script>
<script src="manasmaindashboard/js/variable-pie.js"></script>
<script src="manasmaindashboard/js/exporting.js"></script>
<script src="manasmaindashboard/js/export-data.js"></script>
<script src="manasmaindashboard/js/accessibility.js"></script>
<script>
    Highcharts.setOptions({
    colors: ['#0a467c', '#50c4ca', '#27b7ff', '#0fd7da', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
});

    Highcharts.chart('container', {
  chart: {
    type: 'variablepie'
  },
  title: {
    text: 'These Weeks Top 4 Cases '
  },
  tooltip: {
   
    headerFormat: '',
    pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' 
      
  },
  series: [{
    minPointSize: 10,
    innerSize: '20%',
    zMin: 0,
    name: 'countries',
    data: [{
      name: 'Fever',
      y: 505370,
      z: 42
    }, {
      name: 'Viral',
      y: 551500,
      z: 118.7
    }, {
      name: 'Cold',
      y: 312685,
      z: 124.6
    }, {
      name: 'Diabetes',
      y: 78867,
      z: 137.5
    },  ]
  }]
});

</script>
<script>
    var opts = {
  angle: 0, // The span of the gauge arc
  lineWidth: 0.3, // The line thickness
  radiusScale: .75, // Relative radius
  pointer: {
    length: 0.6, // // Relative to gauge radius
    strokeWidth: 0.053, // The thickness
    color: '#000000' // Fill color
  },
  limitMax: false,     // If false, max value increases automatically if value > maxValue
  limitMin: false,     // If true, the min value of the gauge will be fixed
  colorStart: '#6FADCF',   // Colors
  colorStop: '#43b9be',    // just experiment with them
  strokeColor: '#E0E0E0',  // to see which ones work best for you
  generateGradient: true,
  highDpiSupport: true,     // High resolution support
  
};
var target = document.getElementById('foo'); // your canvas element
var gauge = new Gauge(target).setOptions(opts); // create sexy gauge!
gauge.maxValue = 60; // set max gauge value
gauge.setMinValue(0);  // Prefer setter over gauge.minValue = 0
gauge.animationSpeed = 48; // set animation speed (32 is default value)
gauge.set(32); // set actual value
</script>
<script>
    var opts = {
  angle: 0, // The span of the gauge arc
  lineWidth: 0.3, // The line thickness
  radiusScale: .75, // Relative radius
  pointer: {
    length: 0.6, // // Relative to gauge radius
    strokeWidth: 0.053, // The thickness
    color: '#000000' // Fill color
  },
  limitMax: false,     // If false, max value increases automatically if value > maxValue
  limitMin: false,     // If true, the min value of the gauge will be fixed
  colorStart: '#6FADCF',   // Colors
  colorStop: '#43b9be',    // just experiment with them
  strokeColor: '#E0E0E0',  // to see which ones work best for you
  generateGradient: true,
  highDpiSupport: true,     // High resolution support
  
};
var target = document.getElementById('foo-1'); // your canvas element
var gauge = new Gauge(target).setOptions(opts); // create sexy gauge!
gauge.maxValue = 60; // set max gauge value
gauge.setMinValue(0);  // Prefer setter over gauge.minValue = 0
gauge.animationSpeed = 48; // set animation speed (32 is default value)
gauge.set(55); // set actual value
</script>
<script>
    var opts = {
  angle: 0, // The span of the gauge arc
  lineWidth: 0.3, // The line thickness
  radiusScale: .75, // Relative radius
  pointer: {
    length: 0.6, // // Relative to gauge radius
    strokeWidth: 0.053, // The thickness
    color: '#000000' // Fill color
  },
  limitMax: false,     // If false, max value increases automatically if value > maxValue
  limitMin: false,     // If true, the min value of the gauge will be fixed
  colorStart: '#6FADCF',   // Colors
  colorStop: '#43b9be',    // just experiment with them
  strokeColor: '#E0E0E0',  // to see which ones work best for you
  generateGradient: true,
  highDpiSupport: true,     // High resolution support
  
};
var target = document.getElementById('foo-2'); // your canvas element
var gauge = new Gauge(target).setOptions(opts); // create sexy gauge!
gauge.maxValue = 60; // set max gauge value
gauge.setMinValue(0);  // Prefer setter over gauge.minValue = 0
gauge.animationSpeed = 48; // set animation speed (32 is default value)
gauge.set(07); // set actual value
</script>

 <script>
					var myWindow;
					function openWin() {
					    //myWindow = window.open("letsTalkDiaryMangent", "", "width=450, height=600");
					     myWindow = window.open("letsTalkDiaryMangent", "", "width=800, height=600, addressbar=no,");
					}
			</script>
</body>


</html>
