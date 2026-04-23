<!doctype html>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Emr.eu.entity.*"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<style>
.ui-datepicker {
	width: 18% !important;
}

.manasnewipdimg {
	display: flex;
	align-items: center;
	justify-content: flex-end;
}

.doc-preciption p {
	font-size: 13px;
}

.doc-header span {
	font-size: 13px;
}

.slimScrollBar {
	background: #15536ebf !important;
	opacity: 1.5 !important;
}

.btn-primary {
	background-color: #15536E !important;
	border-radius: 0.75rem;
}

html {
	height: 48px !important;
}

.goog-te-banner-frame.skiptranslate {
	display: none !important;
}

body {
	top: 0px !important;
	min-height: 0px !important;
}

.doc-line a {
	font-size: 12px !important;
}
</style>
<html class="no-js" lang="en">
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Manas - Yuvicare MIS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<style>
#alldataprisctable {
	margin-left: 53px !important;
}

#allinvesttable {
	margin-left: 53px !important;
}

.topsave {
	float: right;
	margin-top: -2px;
	margin-left: 880px;
	margin-top: -3px;
}

.col-1 {
	padding-left: 0px;
	padding-right: 0px;
}

li span {
	font-size: 12px;
	color: black;
	vertical-align: middle;
}

li h5 {
	font-size: 12px;
	color: #818181;
	vertical-align: middle;
	display: inline;
}

@media only screen and (min-width:1024px) and (max-width:1500px) {
	li h5 {
		font-size: 8px;
		color: black;
		vertical-align: middle;
		display: inline;
	}
}

@media only screen and (min-width:1520px) and (max-width:1900px) {
	li h5 {
		font-size: 10px;
	}
}

@media only screen and (min-width:768px) and (max-width:1000px) {
	li h5 {
		font-size: 10px;
	}
}

a {
	color: #15536E;
}

a :hover {
	color: #15536E;
}

.btn-danger-1 {
	border: none;
	background-color: #e0faff00;
	padding: 7px 8px;
	font-size: 14px;
}

table td, th, tr {
	border: none;
	text-align: left;
	padding: 10px 16px;
}

table tr th, table tr td {
	border-top: none !important;
}

th {
	background-color: #E0FAFF;
	font-size: 13px;
	font-weight: 600;
	color: #15536E;
}

tr {
	font-size: 13px;
	font-weight: 400;
	color: #15536E;
}

@media only screen and (max-width:600px) {
	table td, th, tr {
		padding: 10px 10px;
	}
	th {
		font-size: 11px;
	}
	.doc-preciption {
		width: 100% !important;
	}
}

.fa-2x {
	font-size: 1.3em !important;
	float: left !important;
}

.ui-datepicker-month {
	color: black !important;
}

.ui-datepicker-year {
	color: black !important;
}

.divwidth {
	width: 100%;
}

.btn-new:hover {
	border-radius: 12px !important;
}

@media only screen and (min-width:360px) and (max-width:640px) {
	.doc-preciption {
		width: 100% !important;
	}
	.selectptbtn {
		padding-right: 87%;
	}
	.toodat {
		margin-top: -12px !important;
	}
}
<%-- 
<%if (!loginInfo.getRegional_lang().equals("en")) {%> .afterconv {
	margin-top:3px!important;
	
}

<%}%> --%>
#priscriptionpopup .modal-lg {
	min-width: 95% !important;
}

h3 {
	color: black;
}

div {
	font-family: 'Open Sans', sans-serif;
	font-size: 15px;
	color: #444;
	margin-bottom: 0;
}

.doc-info h5 {
	font-size: 12px !important;
}
</style>



<!-- Our main JS file -->

<script type="text/javascript" src="emr/js/jquery.filtertable.min.js"></script>

<script type="text/javascript" src="emr/js/consultationnote.js"></script>

<script type="text/javascript" src="emr/js/emrNew.js"></script>

<script type="text/javascript" src="diarymanagement/js/finder.js"></script>

<script type="text/javascript" src="emr/js/emrAppointment.js"></script>

<script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>

<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<!-- jQuery File Upload Dependencies -->
<%-- <script src="common/assets/js/jquery.ui.widget.js"></script>
<script src="common/assets/js/jquery.iframe-transport.js"></script>  --%>
<script src="common/assets/js/jquery.fileupload.js"></script>


<!-- JavaScript Includes -->
<script src="common/assets/js/jquery.knob.js"></script>
<script src="common/assets/js/script.js"></script>

<!-- js placed at the end of the document so the pages load faster -->
<script src="toggale/js/slimScroll/jquery.slimscroll.min.js"></script>

<script src="toggale/js/smartmenus-0.9.7/jquery.smartmenus.min.js"></script>

<script src="toggale/js/jquery-ui-1.9.2.custom.min.js"></script>

<script class="include" type="text/javascript"
	src="toggale/js/jquery.dcjqaccordion.2.7.js"></script>

<script src="toggale/js/common-scripts.js"></script>


<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>


<%--     <script src="emr/js/jquery.cookie.js"></script>
	<script src="emr/js/jquery.treeview.js"></script> --%>


<%-- <script type="text/javascript">
        $(function () {
            $("#tree").treeview({
                collapsed: true,
                animated: "fast",
                control: "#sidetreecontrol",
                prerendered: true,
                persist: "location"
            });
        })

    </script> --%>
<link href="emr/plugin/side-slider.css" rel="stylesheet" type="text/css"
	media="screen">

<script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
}


$( window ).unload(function() {
	document.cookie = "googtrans=/en/en";
	
});
</script>

<script type="text/javascript"
	src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

<script type="text/javascript">
/* window.onload = function(){
	
	
	
	
	
} */
document.addEventListener('readystatechange', event => {
	document.cookie = "googtrans=/en/<%=loginInfo.getRegional_lang()%>";
});
document.onreadystatechange = function() { 
    if (document.readyState == "complete") { 
        document.querySelector("#preloader").style.visibility = "hidden"; 
    } 
}; 
$(document).ready(function(){
    imageAdder();
    imageAdderEdit();
});


	        bkLib.onDomLoaded(function() {
	        /* 	imageAdder();
	        	imageAdderEdit(); */
	        	
	        	//new nicEditor().panelInstance('consNote');
	        	
	        	//new nicEditor({fullPanel : true}).panelInstance('consNote');

	        	 new nicEditor({maxHeight : 450}).panelInstance('consNote');
	        	 new nicEditor({maxHeight : 450}).panelInstance('editconsNote');
	        	 
	        	// new nicEditor().panelInstance('editconsNote');
	        	  $('.nicEdit-panelContain').parent().width('100%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 $('.nicEdit-main').width('98%'); 
	        	//$('.nicEdit-main').height('150px'); 
	        	 
	        	
	        
	      }
	        );
	        
	        function openConsultationNote(apmtid){
	            $('#treatment_details').modal( "hide" );
	        	clearConsultationNoteEditor();
	        	var data = document.getElementById('hdn'+apmtid).value;
	        	var temp = data.split('#');
	        	document.getElementById('hdntrtmentspan').innerHTML = temp[0];
	        	document.getElementById('hdnapmtspan').innerHTML = temp[1];
	        	document.getElementById('apmtId').value = apmtid;
	        //	$('#addConsultationNote').modal( "show" );
	        }

	        $(document).ready(function() {
	        	$("#fromDate").datepicker({

	        		dateFormat : 'dd-mm-yy',
	        		yearRange: yearrange,
	        		minDate : '30-12-1880',
	        		changeMonth : true,
	        		changeYear : true

	        	});

	        	$("#toDate").datepicker({

	        		dateFormat : 'dd-mm-yy',
	        		yearRange: yearrange,
	        		minDate : '30-12-1880',
	        		changeMonth : true,
	        		changeYear : true
	        	});
	        	
	        	$("#sittingDate").datepicker({

	    			dateFormat : 'dd-mm-yy',
	    			yearRange: yearrange,
	    			minDate : '30-12-1880',
	    			changeMonth : true,
	    			changeYear : true

	    		});
	        	
	        	$("#fllowupdays").datepicker({

	        		dateFormat : 'dd-mm-yy',
	        		yearRange: yearrange,
	        		minDate : '30-12-1880',
	        		changeMonth : true,
	        		changeYear : true
	        	});
	        	
	        	
	        	$("#ed_sittingDate").datepicker({

	    			dateFormat : 'dd-mm-yy',
	    			yearRange: yearrange,
	    			minDate : '30-12-1880',
	    			changeMonth : true,
	    			changeYear : true

	    		});
	        	
	        	$("#sittingBackDate").datepicker({

	    			dateFormat : 'dd-mm-yy',
	    			yearRange: yearrange,
	    			minDate : '30-12-1880',
	    			changeMonth : true,
	    			changeYear : true

	    		});
	        	
	        	$("#edit_sittingBackDate").datepicker({

	    			dateFormat : 'dd-mm-yy',
	    			yearRange: yearrange,
	    			minDate : '30-12-1880',
	    			changeMonth : true,
	    			changeYear : true

	    		});
	        	
	        	
	        });  
	        $(function() {
	    	    $(".datepicker").datepicker();
	    	    $('.ui-datepicker').addClass('notranslate');
	    	}); 
</script>

<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('otnotes');
	        	 $('.nicEdit-panelContain').parent().width('98%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('480px');
	      });

</script>



<!--jquery dependencies-->
<link href="emr/css/dropdownuse/jquery-ui.css" rel="stylesheet" />
<script src="emr/css/dropdownuse/jquery-ui.js"></script>

<!--pqSelect dependencies-->
<link href="emr/css/dropdownuse/pqselect.dev.css" rel="stylesheet" />
<script src="emr/css/dropdownuse/pqselect.dev.js"></script>
<script src="emr/js/jquery.sieve.js"></script>
<script src="popupdialog/dialog/js/jquery.ui.datepicker.js"></script>





<body>

	<div id="preloader">
		<div class="loader"></div>
	</div>


	<div class="page-container sbar_collapsed">
		<!-- sidebar menu area start -->
		<div class="sidebar-menu">
			<div class="sidebar-header" style="padding-top: 4px;">
				<div class="logo">
					<%-- <img src="liveData/clinicLogo/<s:property value="userImageFileName"/>" alt="logo" style="height: 56px;width: 89px;"> --%>
				</div>
			</div>
			<div class="main-menu">
				<div class="menu-inner">
					<nav>
						<ul class="metismenu" id="menu">
							<li><a href="MainDashBoard"><img
									src="manasmaindashboard/images/Home.png"><span>Home</span></a>
							</li>
							<li style="display: none;"><a href=""><img
									src="manasmaindashboard/images/Appointments.png"><span>Appointments
								</span></a></li>
							<%
								if (loginInfo.isManageclient()) {
							%>
							<li><a href="#" onclick="opencPopup('manageClient')"> <img
									src="manasmaindashboard/images/Patients.png"> <span>Patients</span>
							</a></li>
							<%
								}
							%>
							<%
								if (loginInfo.isFullFinance()) {
							%>
							<li><a href="#"
								onclick="opencPopup('ExpenceManagement?action=0')"> <img
									src="manasmaindashboard/images/Accounts.png"> <span>Accounts</span>
							</a></li>
							<%
								}
							%>
							<%
								if (loginInfo.isPharmacy()) {
							%>
							<li><a href="#"
								onclick="openSamePopup('onlinerequestpharPharmacy')"> <img
									src="manasmaindashboard/images/Pharmacy.png"> <span>Pharmacy</span>
							</a></li>
							<%
								}
							%>
							<%
								if (loginInfo.isInventory()) {
							%>
							<li><a href="#"
								onclick="openSamePopup('listProduct?isfromcathlab=0')"> <img
									src="manasmaindashboard/images/Inventory.png"></i> <span>Inventory</span>
							</a></li>
							<%
								}
							%>
							<%
								if (loginInfo.isManageopd()) {
							%>
							<li><a href="#"
								onclick="opencPopup('calNotAvailableSlot?actionType=newopd&doctor=<%=loginInfo.getId()%>')">
									<img src="manasmaindashboard/images/My opd_icon.png"> <span>My
										OPD</span>
							</a></li>
							<%
								}
							%>
							<%
								if (loginInfo.isManageipd()) {
							%>
							<%
								if (loginInfo.isDirect_ipd()) {
							%>
							<li><a href="#" data-toggle="modal" class="colortog"
								data-target="#wards"> <img
									src="manasmaindashboard/images/IPD_Icon.png"> <span>IPD</span>
							</a></li>
							<%
								} else {
							%>
							<li><a href="#"
								onclick="opencPopup('IpdDashboard?action=0')"> <img
									src="manasmaindashboard/images/IPD_Icon.png"> <span>IPD</span>
							</a></li>
							<%
								}
							%>
							<%
								}
							%>
							<%
								if (loginInfo.isMisaccess()) {
							%>
							<%
								if (loginInfo.isManagemis()) {
							%>
							<li><a href="#" onclick="opencPopup('MisChart')"> <img
									src="manasmaindashboard/images/MIS_Icon.png"> <span>MIS</span>
							</a></li>
							<%
								}
							%>
							<%
								}
							%>
							<%
								if (loginInfo.isManageemr()) {
							%>
							<li><a href="#" onclick="opencPopup('getPatientRecordEmr')">
									<img src="manasmaindashboard/images/EMR_Icon.png"> <span>EMR</span>
							</a></li>
							<%
								}
							%>
							<li style="display: none;"><a href=""><img
									src="manasmaindashboard/images/Chat Room.png"> <span>Chat
										Room </span></a></li>
							<li style="display: none;"><a href=""><img
									src="manasmaindashboard/images/Calendar.png"> <span>Calendar
								</span></a></li>
							<li><a href="#" onclick="openWin()"><img
									src="manasmaindashboard/images/Help Center.png"> <span>Help
										Center </span></a></li>
							<%
								if (loginInfo.getUserType() == 2) {
							%>
							<li><a href=""> <img
									src="manasmaindashboard/images/Settings.png"> <span>Settings</span>
							</a>
								<ul class="collapse">
									<li><a href="#"
										onclick="openPopup('profileClinicRegistration')">Profile</a></li>
									<%
										if (loginInfo.getSuperadminid() == 1 || loginInfo.getUserType() == 2) {
									%>
									<li><a href="#" onclick="openPopup('UserProfile')">Manage
											User</a></li>
									<%
										}
									%>
									<%
										if (loginInfo.getSuperadminid() == 1 || loginInfo.getUserType() == 2) {
									%>
									<li><a href="#" onclick="openPopup('DiaryMangent')">Manage
											Diary</a></li>
									<%
										}
									%>
									<!-- <li><a href="">some text</a></li> -->
								</ul></li>
							<%
								}
							%>
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
					<div class="col-md-8 col-sm-8 clearfix">
						<div class="nav-btn pull-left hidden">
							<span></span> <span></span> <span></span>
						</div>
						<div class="search-box pull-left ">
							<img src="manasemr/assets/images/Manas_Yuvicare_Logo.svg"
								class="mob">
							<form action="#" style="display: none; margin-left: 15px;">
								<i class="ti-search"></i> <input type="text" name="search"
									placeholder="Search patients, appointments, help or anything else "
									required>

							</form>
							<img src="manasemr/assets/images/Add Patient Icon.svg"
								class="head-img hidden">
							<h3
								style="font-size: 12px; display: none; margin-left: 10px; color: #15536E;">Add
								A Patient</h3>
							<img src="manasemr/assets/images/Book Appointment Icon.svg"
								class="head-img hidden">
							<h3
								style="font-size: 12px; display: none; margin-left: 15px; color: #15536E;">Book
								An Appointment</h3>
						</div>
						<div style="padding-left: 100px;">
							<h1>EMR Dashboard</h1>
						</div>
					</div>

					<!-- profile info & task notification -->
					<div class="col-md-4 col-sm-4 clearfix hidden">
						<ul class="notification-area pull-right">

							<li><img src="manasemr/assets/images/Chat Icon.svg"></li>
							<li class="dropdown"><i class="ti-bell dropdown-toggle"
								data-toggle="dropdown"> </i>
								<div class="dropdown-menu bell-notify-box notify-box">
									<span class="notify-title">You have 3 new notifications
										<a href="#">view all</a>
									</span>
									<div class="nofity-list">

										<a href="#" class="notify-item">
											<div class="notify-thumb">
												<i class="ti-comments-smiley btn-info"></i>
											</div>
											<div class="notify-text">
												<p>New Commetns On Post</p>
												<span>30 Seconds ago</span>
											</div>
										</a>
									</div>
								</div></li>
							<l i>
							<div class="dropdown show" style="margin-left: 0px;">
								<a class="btn btn-danger dropdown-toggle" href="#" role="button"
									id="dropdownMenuLink" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> Welcome,Admin <i
									class="fa fa-angle-down"></i>
								</a>

								<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
									<a class="dropdown-item" href="#">Action</a> <a
										class="dropdown-item" href="#">Another action</a> <a
										class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							</li>
							<li><img
								src="manasemr/assets/images/Profile Picture_Placeholder_Circle.svg"></li>
						</ul>
					</div>
				</div>
			</div>
			<s:form action="getPatientRecordEmr" id="getPatientRecord">
				<input type="hidden" id="isfromemrdashb" value="1">
				<s:hidden name="action" id="hdnaction" />
				<s:hidden name="caldate" id="caldate" />
				<s:hidden id="conditionsApmt" name="conditionsApmt"></s:hidden>
				<s:hidden id="hdncondapmtId" name="apmtId" />
				<s:hidden id="treatmentEpisodeid" name="treatmentEpisodeid" />
				<s:hidden id="opdchkcondition" name="opdchkcondition" />
				<s:hidden id="diaryUser1" name="diaryUser"></s:hidden>
			</s:form>

			<%-- <s:hidden id="foripddashboardpriscription"/> --%>
			<div class="main-content-inner theme-bg">
				<div class="container-fluid emr-styles ">

					<div class="row mis-head" style="padding-top: 10px;">
						<div class="col-lg-4 col-md-12 col-xs-12 col-12 ">
							<div class="row doc-info" style="margin: 0">
								<div class="form-inline">
									<div class="form-group divwidth">
										<h3 style="font-size: 13px">
											<s:property value="client" />
										</h3>
										<input type="hidden" name="patientname" id="patientname"
											value="<s:property	value="client" />">
									</div>
									<div class="form-group divwidth">
										<h5>
											UHID :
											<s:property value="abrivationid" />
											|Age/Gender :
											<s:property value="ageandgender" />
										</h5>
									</div>
									<!--  <div class="form-group divwidth">
                            <h5></h5>
                            </div> -->
									<div class="form-group divwidth">
										<h5>
											<s:property value="clientData" />
											<s:property value="town" />
											,
											<s:property value="county" />
											,
											<s:property value="country" />
											-
											<s:property value="postcode" />
										</h5>
									</div>
									<!--  <div class="form-group divwidth">
                            <h5></h5>
                            </div> -->
									<s:if test="mobNo!=''">
										<div class="form-group divwidth">
											<h5>
												Mobile No. :
												<s:property value="mobNo" />
											</h5>
										</div>
									</s:if>
									<div class="form-group divwidth">
										<h5>
											Diagnosis :
											<s:property value="finaldiagnosis" />
										</h5>
									</div>
									<%
										if (loginInfo.isLmh()) {
									%>
									<%-- <div class="form-group divwidth" >
                            <h5>Department : <s:property	value="diciplineName" /></h5>
                            </div> --%>
									<%
										}
									%>
									<%
										if (!loginInfo.isLmh()) {
									%>
									<div class="form-group divwidth">
										<h3 style="font-size: 13px !important;">
											Primary Doctor:
											<s:property value="emrDoctorName" />
											<input type="hidden" id="emrDoctorName"
												value="<s:property	value="emrDoctorName" />">
										</h3>
									</div>
									<%
										}
									%>
									<%
										if (loginInfo.isLmh()) {
									%>
									<%--  <s:if test="secsecondarylist.size!=0">
	                            <div class="form-group divwidth" >
	                            <h3 style="font-size: 13px !important;">Secondary Doctor :</h3>
	                            <h3 style="font-size: 13px !important;">
	                            <s:iterator value="secsecondarylist">
	                            	<s:property value="secondarydr"/>,
	                            </s:iterator>
	                            </h3>
	                            </div>
	                           </s:if>  --%>
									<%
										}
									%>
								</div>
								<%-- <h3><s:property	value="client" /></h3><br>
                                <h5>
                                <input type="button" class="form-control btn"  onclick="showAllPatientPopUp();" value="Select Patient" style="width:auto;background-color: #4eb6c2 !important;color: white;font-size: medium;font-weight: 700;margin-left: 12px;margin-top: 6px;"><br><br>
                                </h5>
                                <h5>UHID : <s:property value="abrivationid" /></h5><br>
                                <h5>Age/Gender : <s:property value="ageandgender" /></h5><br>
                                <h5><s:property value="clientData" /></h5><br>
                                <h5><s:property	value="emrDoctorName" /></h5><br> --%>

								<%--  <div class="hidden">
                                <h4><span id="doctoremrid"><s:property
								value="emrDoctorName" /></span></h4>
                                <img src="manasemr/assets/images/Dropdown_Username.svg" style="margin-left: 10px;">
                                <h4 ><span id="clientemrid"><s:property
								value="emrClientName" /></span></h4>
                                <img src="manasemr/assets/images/Dropdown_Username.svg" style="margin-left: 10px;" >
                                <h4 ><span id="conditionemrid"><s:property
								value="emrConditionName" /> </span></h4>
                                <%if(!loginInfo.isPatientemr()){ %>
                                <a href="#"
							data-toggle="modal" data-target="#lok" style="color: yellow;"><img src="manasemr/assets/images/Icon metro-pencil.svg" style="margin-left: 15px;"></a>
                            <%} %>
                             </div> --%>
							</div>

						</div>

						<div class="col-lg-8 col-md-12 col-xs-12 col-12">
							<%
								if (!loginInfo.isPatientemr()) {
							%>
							<div class="row mobile">
								<ul class="hidden-down list-inline">

									<li class="doc-line">
										<%
											if (loginInfo.isEmr_docs()) {
										%> <span><a href="#"
											href="#" data-toggle="modal" class="btn btn-primary"
											style="color: white;" data-target="#document_details">Documents</a></span>
										<%
											} else {
										%> <span><a href="#" href="#">Document</a></span>
										<%
											}
										%>

									</li>
									<li class="doc-line"><span><a href="#"
											class="btn btn-primary" style="color: white;"
											onclick="openBlankPopup('emrdocsEmr?clientId=<s:property value="clientname"/>&diaryUser=<s:property value="diaryUser"/>&condition=<s:property value="condition"/>')">Document
												page</a></span></li>
									<li class="doc-line"><span><a href="#"
											class="btn btn-primary" style="color: white;"
											onclick="getPatientVital(<s:property value="clientname"/>)">Vitals</a></span>

									</li>
									<li class="doc-line"><span><a href="#"
											class="btn btn-primary" style="color: white;"
											onclick="clincalnoteselements()">Clinical Notes</a></span></li>
									<li class="doc-line">
										<%
											if (loginInfo.isEmr_medicine()) {
										%> <span><a href="#"
											class="btn btn-primary" style="color: white;"
											onclick="getEmrClientAllPriscriptionData()">Medicine</a></span> <%
 	} else {
 %>
										<span><a href="#" class="btn btn-primary"
											style="color: white;">Medicine</a></span> <%
 	}
 %>

									</li>
									<li class="doc-line">
										<%
											if (loginInfo.isEmr_medicine()) {
										%> <span><a href="#"
											class="btn btn-primary" style="color: white;"
											onclick="getEmrinvestigationViewList()">Investigation</a></span> <%
 	} else {
 %>
										<span><a href="#" class="btn btn-primary"
											style="color: white;">Investigation</a></span> <%
 	}
 %>

									</li>
									<li class="doc-line"><span><a href="#"
											class="btn btn-primary" style="color: white;"
											onclick="getPacsPopup(<s:property value="clientname"/>,'<s:property value="abrivationid"/>')">PAC's</a></span>

									</li>
									<li class="doc-line"><a href="#" data-toggle="modal"
										class="btn btn-primary" style="color: white;"
										data-target="#patient_media">Media</a></span></li>
									<li class="doc-line"><span><a href="#"
											class="btn btn-primary" style="color: white;"
											onclick="openBlankPopup('treatmentsheetIpdDashboard?clientid=<s:property value="clientname"/>&ipdid=0')">Treatment
												Record</a></span> <!-- <img src="manasemr/assets/images/Dividers.svg"> -->
									</li>
									<li class="doc-line"><span><a href="#"
											class="btn btn-primary" style="color: white;"
											onclick="historyConsulatationForm(<s:property value="clientname"/>,<s:property value="apmtId"/>)">OPD History</a></span> <!-- <img src="manasemr/assets/images/Dividers.svg"> -->
									</li>
									<%
										if (loginInfo.isLmh()) {
									%>
									<li class="doc-line"><a href="#" onclick="showprocedure()"
										style="color: white;" class="btn btn-primary">Procedure</a></li>

									<li class="doc-line"><a href="#"
										onclick="viewsitting(<s:property value="clientname"/>)"
										style="color: white;" class="btn btn-primary">View Sitting</a>
									</li>
                                   <s:if test="Opdid==1">
                                     
									  <li class="doc-line" style="background-color: orange;"><a href="#"
										onclick="addsitting(<s:property value="clientname"/>,<s:property value="Deptopd_id"/>)"
										  class="btn btn-primary"  style="color: white;">Add Sitting</a>

									</li>
									</s:if>
										<s:else>
										  <li class="doc-line"><a href="#"
										    onclick="addsitting(<s:property value="clientname"/>,<s:property value="Deptopd_id"/>)"
										     style="color: white;" class="btn btn-primary">Add Sitting</a>
										   </li>
									  </s:else>
										
										 
									
									<%-- <li class="doc-line"><a href="#"onclick="addsitting(<s:property value="clientname"/>)"
										style="color: white;" class="btn btn-primary">Add Sitting</a>

									</li>
									 --%>
									
									


									<%
										}
									%>
								</ul>
							</div>
							<%
								}
							%>
							<s:form action="getPatientRecordEmr" method="POST"
								id="datefilterfrm">
								<div class="row " style="margin-top: 30px;">
									<div class="col-md-4 col-sm-5 col-xs-12 form-inline wid-frm">
										<div class="form-group selectptmob">


											<input type="button" style="padding-right: 100%"
												class=" btn btn-primary selectptbtn"
												onclick="showAllPatientPopUp();" value="Select Patient"><br>

										</div>
										<div class="form-group notranslate afterconv hidden"
											style="margin-left: 50px; margin-top: -12px">

											<s:textfield name="fromDate" id="fromDate"
												cssClass="form-control " theme="simple" style="color:black;"
												placeholder="from date"></s:textfield>

										</div>

										<div class="form-group notranslate afterconv hidden"
											style="margin-top: -12px;">
											<s:textfield name="toDate" id="toDate"
												cssClass="form-control toodat" theme="simple"
												style="color:black;margin-left: 10px;" placeholder="to date"></s:textfield>
											<input type="hidden" name="clientname" id="popupclientid">
										</div>
									</div>
									<div class="col-md-4 col-xs-12 col-sm-5 def-btn">
										<div class="align-left">
											<!-- <a href="#" onclick="document.getElementById('datefilterfrm').submit()" class="btn btn-new btn-info">Search</a> -->
											<!-- <input type="submit" class="btn btn-warning" value="Serach"> -->
										</div>
										<div class="align-left">
											<%
												if (!loginInfo.getLoginType().equals("shareemr")) {
											%>
											<%
												if (loginInfo.isEmr_update()) {
											%>
											<s:if test="diaryUser!=0">
												<a href="#" data-toggle="modal" data-target="#insertvital"
													class="btn btn-new btn-info" title="New Vital">New
													Vital</a>
											</s:if>
											<%
												}
											%>
											<%
												}
											%>
										</div>
										<div class="align-left">
											<%
												if (!loginInfo.getLoginType().equals("shareemr")) {
											%>
											<%
												if (loginInfo.isEmr_update()) {
											%>
											<s:if test="diaryUser!=0">
												<a href="#" data-toggle="modal"
													data-target="#addConsultationNote"
													class="btn btn-new btn-info" title="Update Notes"
													onclick="openAddConsultationPopup()">Update Note</a>
											</s:if>
											<%
												}
											%>
											<%
												}
											%>
										</div>
									</div>
									<%
										if (!loginInfo.isPatientemr()) {
									%>
									<div class="col-md-2 col-sm-2 icon-btn">
										<a href="#" class="link iconset" onclick="showsharepopup()"
											title="Share EMR"><img
											src="manasemr/assets/images/Icon feather-share.svg"
											class="edpr-img"></a> <a href="#" class="link iconset"
											onclick="showconfidentialpopup()" title="Make Confidential"><img
											src="manasemr/assets/images/Icon feather-lock.svg"
											class="edpr-img-2"></a> <a href="#"
											onclick="openEmrPopup('printconsnoteEmr?clientid=<s:property value="clientname"/>&amptid=<s:property value="apmtId"/>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=1');"
											class="link iconset" title="Print"><img
											src="manasemr/assets/images/Icon open-print.svg"
											class="edpr-img-2"></a>
									</div>
									<%
										}
									%>
								</div>
								<%
									if (loginInfo.isLmh()) {
								%>
								<s:select
									onchange="document.getElementById('datefilterfrm').submit()"
									list="disciplineList" name='Department' listKey="id"
									listValue="discipline" headerKey=""
									headerValue="All Department" cssStyle="width:35%"
									cssClass="form-control "></s:select>
								<%
									} else {
								%>
								<select name='Department' id="id">
									<option value="">Select Department</option>
								</select>
								<%
									}
								%>
							</s:form>
						</div>

					</div>

					<br>
					<div class="row">
						<div class="col-lg-12 col-md-12">
							<div class="panel panel-primary">

								<div class="panel-body hesighsetnew">
									<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 lft-bx-emr">
										<ul class="list-group list-group-flush notranslate setheight"
											style="height: 218px;">
											<s:if test="opdemrloglist.size!=0">
												<s:iterator value="opdemrloglist">
													<li class="list-group-item"><a href="#"
														onclick="logwisedata('<s:property value="commencing"/>')"><s:property
																value="commencing" /> <s:if test="heading=='ipd'">
   				(IPD) - <s:property value="Newabrivationid" />
															</s:if> <s:elseif test="heading=='opd'">
   				(OPD) - <s:property value="Newabrivationid" />
															</s:elseif> <s:else>
															</s:else> </a></li>
												</s:iterator>
											</s:if>
											<!--  <li class="list-group-item"><a href="">1st Visit Details</a></li>
  <li class="list-group-item">2nd Visit Details</li>
  <li class="list-group-item">3rd Visit Details</li>
  <li class="list-group-item">4th Visit Details</li>
  <li class="list-group-item">5th Visit Details</li> -->
										</ul>
									</div>
									<div
										class="col-lg-9 col-md-9 col-sm-12 col-xs-12 rgt-bx-emr notranslate">

										<%
											ArrayList<Client> vitallist = new ArrayList<Client>();
											if (request.getAttribute("vitallist") != null) {
												vitallist = (ArrayList<Client>) request.getAttribute("vitallist");
											}
										%>
										<%
											if (vitallist.size() > 0) {
										%>



										<%
											for (Client client : vitallist) {
										%>
										<div class="card doc-preciption" style="width: 63%">
											<div class="doc-header">
												<p>
													<span>Vital</span>
												</p>
												<img src="manasemr/assets/images/Dividers.svg"
													style="margin-left: 15px;">
												<p style="margin-left: 15px;">
													<span><%=client.getDate()%></span>
												</p>


											</div>
										</div>
										<table>
											<tr>
												<td>Height</td>
												<td>Weight</td>
												<td>BMI</td>
												<td>Pulse</td>
												<td>Sys-BP / Dia-BP</td>
												<td>Temperature</td>
												<td>SPO2</td>
											</tr>
											<tr>
												<td><%=client.getHeight()%></td>
												<td><%=client.getWeight()%></td>
												<td><%=client.getBmi()%></td>
												<td><%=client.getPulse()%></td>
												<td><%=client.getSysbp()%>/<%=client.getDiabp()%></td>
												<td><%=client.getTemprature()%></td>
												<td><%=client.getSpo()%></td>
											</tr>
										</table>
										<%
											}
										%>


										<%
											}
										%>
										<%
											ArrayList<Emr> consultationList = new ArrayList<Emr>();
											if (session.getAttribute("consultationNoteList") != null) {
												consultationList = (ArrayList<Emr>) session.getAttribute("consultationNoteList");
											}
										%>
										<%
											for (Emr emr : consultationList) {
										%>
										<div class="row" style="padding: 3px 0px 3px 0px;">
											<div class="card doc-preciption" style="width: 63%">
												<div class="doc-header">
													<%
														if (loginInfo.isLmh()) {
													%>
													<p>
														<span> <%=emr.getDepartment()%></span>
													</p>
													<br>
													<%
														}
													%>
													<p>
														Primary Doctor :<span> <%=emr.getPractitionerName() + " " + emr.getHeading()%></span>
													</p>
													<img src="manasemr/assets/images/Dividers.svg"
														style="margin-left: 15px;">
													<p style="margin-left: 15px"><%=emr.getLastModified()%></p>
													<%
														if (loginInfo.isLmh()) {
													%>
													<%
														if (emr.getSecondarylist().size() > 0) {
													%>
													<br>
													<p>
														Secondary Doctor : <span> <%
 	for (Emr secondary : emr.getSecondarylist()) {
 %>
															<%=secondary.getSecondarydr()%>, <%
																}
															%>
														</span>
													</p>
													<%
														}
													%>
													<%
														}
													%>

													<form id="deleteConsultationNote_<%=emr.getId()%>"
														action="deleteConsultationNoteEmr">
														<input type="hidden" name="consulatation_note_id"
															value="<%=emr.getId()%>">
														<s:hidden id="clientname" value="%{clientname}"
															name="clientname"></s:hidden>
														<s:hidden id="diaryUser" value="%{diaryUser}"
															name="diaryUser"></s:hidden>
														<s:hidden id="condition" value="%{condition}"
															name="condition"></s:hidden>
														<s:hidden id="delapmtId" name="apmtId" />
														<%-- <%if(!loginInfo.getLoginType().equals("shareemr")){ %>
									<%if(loginInfo.isEmr_delete()){ %>
									<a href="#" onclick="deleteContNote('<%=emr.getId()%>')"
										title="Delete" class="text-danger sweetbtn"><img src="manasemr/assets/images/Icon metro-bin.svg" class="doc-edit" style="margin-right: 0px;margin-top: -20px"></a>
									<% }%>
									<% }%> --%>
													</form>


													<%
														if (loginInfo.isEmr_print()) {
													%>
													<a href="javascript:void(0)"
														onclick="openEmrPopup('printconsnoteEmr?clientid=<s:property value="clientname"/>&amptid=<%=emr.getAppointmentid()%>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=3&editid=<%=emr.getId()%>')"
														title="Print"><img
														src="manasemr/assets/images/Icon open-print.svg"
														class="doc-edit"
														style="margin-top: -20px; margin-right: 30px"></a>
													<%
														}
													%>


													<%
														if (!loginInfo.getLoginType().equals("shareemr")) {
													%>
													<%
														if (loginInfo.isEmr_edit()) {
													%>
													<a href="javascript:void(0)"
														onclick="editConsultationNote(<%=emr.getId()%>,<%=emr.getAppointmentid()%>)"
														title="Edit"><img
														src="manasemr/assets/images/Icon metro-pencil.svg"
														class="doc-edit"
														style="margin-top: -20px; margin-right: 60px"></a>
													<%
														}
													%>
													<%
														}
													%>



												</div>
												<%-- <b style="width: 100%; display: inline-flex;"> <%=emr.getLastModified()%>
								/ <%=emr.getPractitionerName() + " " + emr.getHeading()%>
								&nbsp;&nbsp; &nbsp;&nbsp; <%if(!loginInfo.getLoginType().equals("shareemr")){ %>
								<%if(loginInfo.isEmr_edit()){ %> <a href="javascript:void(0)"
								onclick="editConsultationNote(<%=emr.getId()%>,<%=emr.getAppointmentid()%>)"
								title="Edit"><i class="fa fa-pencil"></i></a> <% }%> <% }%>
								&nbsp;&nbsp; | &nbsp;&nbsp; <%if(loginInfo.isEmr_print()){ %> <a
								href="javascript:void(0)"
								onclick="openEmrPopup('printconsnoteEmr?clientid=<s:property value="clientname"/>&amptid=<s:property value="apmtId"/>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=3&editid=<%=emr.getId() %>')"
								title="Print"><i class="fa fa-print"></i></a> <%} %> &nbsp;&nbsp;
								| &nbsp;&nbsp;
								<form id="deleteConsultationNote_<%=emr.getId()%>"
									action="deleteConsultationNoteEmr">
									<input type="hidden" name="consulatation_note_id"
										value="<%=emr.getId()%>">
									<s:hidden id="clientname" value="%{clientname}"
										name="clientname"></s:hidden>
									<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
									<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
									<s:hidden id="delapmtId" name="apmtId" />
									<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
									<%if(loginInfo.isEmr_delete()){ %>
									<a href="#" onclick="deleteContNote('<%=emr.getId()%>')"
										title="Delete" class="text-danger sweetbtn"><i
										class="fa fa-trash-o"></i></a>
									<% }%>
									<% }%>
								</form>
							</b> --%>
											</div>

										</div>

										<!-- <p>Client given massage.</p> -->
										<p style="font-size: 12px"><%=emr.getDescription()%></p>

										<hr>

										<%
											}
										%>





										<%
											ArrayList<Emr> consultationNoteListInv = new ArrayList<Emr>();
											if (request.getAttribute("consultationNoteListInv") != null) {
												consultationNoteListInv = (ArrayList<Emr>) request.getAttribute("consultationNoteListInv");
											}
										%>

										<%
											for (Emr emr : consultationNoteListInv) {
										%>
										<div class="row" style="padding: 3px 0px 3px 0px;">
											<div class="card doc-preciption" style="width: 63%">
												<div class="doc-header">
													<%
														if (loginInfo.isLmh()) {
													%>
													<p>
														<span> <%=emr.getDepartment()%></span>
													</p>
													<br>
													<%
														}
													%>
													<p>
														Primary Doctor :<span> <%=emr.getPractitionerName() + " " + emr.getHeading()%></span>
													</p>
													<img src="manasemr/assets/images/Dividers.svg"
														style="margin-left: 15px;">
													<p style="margin-left: 15px"><%=emr.getLastModified()%></p>
													<%
														if (loginInfo.isLmh()) {
													%>
													<%
														if (emr.getSecondarylist().size() > 0) {
													%>
													<br>
													<p>
														Secondary Doctor : <span> <%
 	for (Emr secondary : emr.getSecondarylist()) {
 %>
															<%=secondary.getSecondarydr()%>, <%
																}
															%>
														</span>
													</p>
													<%
														}
													%>
													<%
														}
													%>


													<%-- <form id="deleteConsultationNote_<%=emr.getId()%>"
									action="deleteConsultationNoteEmr">
									<input type="hidden" name="consulatation_note_id"
										value="<%=emr.getId()%>">
									<s:hidden id="clientname" value="%{clientname}"
										name="clientname"></s:hidden>
									<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
									<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
									<s:hidden id="delapmtId" name="apmtId" />
									<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
									<%if(loginInfo.isEmr_delete()){ %>
									<a href="#" onclick="deleteContNote('<%=emr.getId()%>')"
										title="Delete" class="text-danger sweetbtn"><img src="manasemr/assets/images/Icon metro-bin.svg" class="doc-edit" style="margin-right: 0px;margin-top: -20px"></a>
									<% }%>
									<% }%>
								</form> --%>


													<%
														if (loginInfo.isEmr_print()) {
													%>
													<a href="javascript:void(0)"
														onclick="openEmrPopup('printconsnoteinvestigationEmr?clientid=<s:property value="clientname"/>&amptid=<%=emr.getAppointmentid()%>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=3&editid=<%=emr.getId()%>')"
														title="Print"><img
														src="manasemr/assets/images/Icon open-print.svg"
														class="doc-edit"></a>
													<%
														}
													%>


													<%--  <%if(!loginInfo.getLoginType().equals("shareemr")){ %>
								<%if(loginInfo.isEmr_edit()){ %> <a href="javascript:void(0)"
								onclick="editConsultationNote(<%=emr.getId()%>,<%=emr.getAppointmentid()%>)"
								title="Edit"><img src="manasemr/assets/images/Icon metro-pencil.svg" class="doc-edit" style="margin-top: -20px;margin-right: 60px"></a> <% }%> <% }%>
                              
                               --%>

												</div>
											</div>

										</div>

										<!-- <p>Client given massage.</p> -->
										<p><%=emr.getDescription()%></p>

										<hr>

										<%
											}
										%>



										<%
											ArrayList<Emr> consultationNoteListPrisc = new ArrayList<Emr>();
											if (request.getAttribute("consultationNoteListPrisc") != null) {
												consultationNoteListPrisc = (ArrayList<Emr>) request.getAttribute("consultationNoteListPrisc");
											}
										%>

										<%
											for (Emr emr : consultationNoteListPrisc) {
										%>
										<div class="row" style="padding: 3px 0px 3px 0px;">
											<div class="card doc-preciption" style="width: 63%">
												<div class="doc-header">
													<%
														if (loginInfo.isLmh()) {
													%>
													<p>
														<span> <%=emr.getDepartment()%></span>
													</p>
													<br>
													<%
														}
													%>
													<p>
														Primary Doctor :<span> <%=emr.getPractitionerName() + " " + emr.getHeading()%></span>
													</p>
													<img src="manasemr/assets/images/Dividers.svg"
														style="margin-left: 15px;">
													<p style="margin-left: 15px"><%=emr.getLastModified()%></p>
													<%
														if (loginInfo.isLmh()) {
													%>
													<%
														if (emr.getSecondarylist().size() > 0) {
													%>
													<br>
													<p>
														Secondary Doctor : <span> <%
 	for (Emr secondary : emr.getSecondarylist()) {
 %>
															<%=secondary.getSecondarydr()%>, <%
																}
															%>
														</span>
													</p>
													<%
														}
													%>
													<%
														}
													%>
													<%-- <form id="deleteConsultationNote_<%=emr.getId()%>"
									action="deleteConsultationNoteEmr">
									<input type="hidden" name="consulatation_note_id"
										value="<%=emr.getId()%>">
									<s:hidden id="clientname" value="%{clientname}"
										name="clientname"></s:hidden>
									<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
									<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
									<s:hidden id="delapmtId" name="apmtId" />
									<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
									<%if(loginInfo.isEmr_delete()){ %>
									<a href="#" onclick="deleteContNote('<%=emr.getId()%>')"
										title="Delete" class="text-danger sweetbtn"><img src="manasemr/assets/images/Icon metro-bin.svg" class="doc-edit" style="margin-right: 0px;margin-top: -20px"></a>
									<% }%>
									<% }%>
								</form>
                               --%>

													<%
														if (loginInfo.isEmr_print()) {
													%>
													<a href="javascript:void(0)"
														onclick="openEmrPopup('printconsnotepriscriptionEmr?clientid=<s:property value="clientname"/>&amptid=<%=emr.getAppointmentid()%>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=3&editid=<%=emr.getId()%>')"
														title="Print"><img
														src="manasemr/assets/images/Icon open-print.svg"
														class="doc-edit"></a>
													<%
														}
													%>


													<%-- <%if(!loginInfo.getLoginType().equals("shareemr")){ %>
								<%if(loginInfo.isEmr_edit()){ %> <a href="javascript:void(0)"
								onclick="editConsultationNote(<%=emr.getId()%>,<%=emr.getAppointmentid()%>)"
								title="Edit"><img src="manasemr/assets/images/Icon metro-pencil.svg" class="doc-edit" style="margin-top: -20px;margin-right: 60px"></a> <% }%> <% }%>
                               --%>


												</div>
											</div>

										</div>

										<!-- <p>Client given massage.</p> -->
										<p><%=emr.getDescription()%></p>

										<hr>

										<%
											}
										%>
									</div>

								</div>
							</div>
						</div>
						<%
							if (!loginInfo.isPatientemr()) {
						%>
						<div class="sideslider notranslate" id="sideslider">
							<div class="sideslider-tab">Forms</div>
							<div id="sideslider-smartbutton">
								<input type="text" class="live-search-box form-control"
									placeholder="search here"
									style="width: 100%; text-transform: uppercase; font-size: 11px !important;" />

								<div style="height: 300px; overflow: auto;">
									<ul class="commentlist heightsetd">
										<li style="cursor: pointer;"
											onclick="openBlankPopup('gynicassesmentformCommonnew?clientid=<s:property value="clientname"/>&practid=<s:property value="diaryUser"/>')">
											Obstretics and Gynaecology Assessment Form</li>
										<li style="cursor: pointer;"
											onclick="openBlankPopup('clinicalnotesProblemListing?clientid=<s:property value="clientname"/>')">
											Clinical Notes</li>
										<s:iterator value="assesmenttemplateNameList">
											<li style="cursor: pointer;"
												onclick="opencPopup('addTemplateDetailsAssesmentForms?templateId=<s:property value="id"/>&formtype=<s:property value="formtype"/>&clientid=<s:property value="clientname"/>')">
												<s:property value="templateName" />
											</li>
										</s:iterator>
									</ul>
								</div>
								<div class="sideclear"></div>
							</div>


							<div class="sideslider-close sideslider-close_en hidden">Close&nbsp;</div>

						</div>
						<%
							}
						%>
					</div>

					<script>
jQuery(document).ready(function($){

	$('.commentlist li').each(function(){
	$(this).attr('data-search-term', $(this).text().toLowerCase());
	});

	$('.live-search-box').on('keyup', function(){

	var searchTerm = $(this).val().toLowerCase();

	    $('.commentlist li').each(function(){

	        if ($(this).filter('[data-search-term *= ' + searchTerm + ']').length > 0 || searchTerm.length < 1) {
	            $(this).show();
	        } else {
	            $(this).hide();
	        }

	    });

	});

	});
</script>
					<script src="emr/plugin/jquery.side-slider.js"></script>
                    <script src="diarymanagement/js/dayUsers.js"></script>
					<script type="text/javascript">
    $(document).ready(function(){
        $('#sideslider').sideSlider();

    });
</script>

					<!-- Top Navigation Bar -->
					<div class="container-fluid hidden">



						<!-- Top Left Menu -->


						<div class="">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 emrtitle">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 paddniltopase"
									style="margin-top: 5px;">
									<p style="color: #fff;"></p>
								</div>

								<div
									class="col-lg-6 col-md-6 col-sm-6 col-xs-6 marmin8 paddniltopase">



								</div>
							</div>

						</div>
					</div>



					<!-- /Top Left Menu -->




					</section>


					<div role="tabpanel">



						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane" id="AD"
								style="display: none;">
								<!--  <table class="table  table-responsive"> -->
								<table width="100%" cellpadding="0" cellspacing="0"
									class="timer-table" id="allusertable"
									style="table-layout: fixed;">
									<thead>
										<tr>

											<th style="background-color: #DFD8D4"></th>
											<th id="wn0">Monday</th>
											<th id="wn1">Tuesday</th>
											<th id="wn2">Wednesday</th>
											<th id="wn3">Thursday</th>
											<th id="wn4">Friday</th>
											<th id="wn5">Saturday</th>
											<th id="wn6">Sunday</th>


										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>

								<%--  <%@ include file="/emr/pages/emrAppointment.jsp" %> --%>

							</div>

							<div id="container">

								<div id="sidebar" class="sidebar-fixed hidden">
									<div id="sidebar-content">
										<!--=== Navigation ===-->
										<ul id="nav">
											<div role="tabpanel" class="tab-pane" id="EMR">
												<!--sidebar start-->
												<section class="sidebarheight">
													<div id="sidebar" class="nav-collapse ">
														<!-- sidebar menu start-->
														<ul class="sidebar-menu" id="nav-accordion">
															<div class="panel-body" style="margin-top: 9px;">
																<div class="panel-group" id="accordion" role="tablist"
																	aria-multiselectable="true">
																	<div class="panel panel-default">
																		<div class="row panel-heading" role="tab"
																			id="headingOne">
																			<div class="col-lg-9 col-md-9 col-sm-9">
																				<h4 class="panel-title">
																					<a data-toggle="collapse" data-parent="#accordion"
																						href="#collapseOne" aria-expanded="true"
																						aria-controls="collapseOne"><i
																						class="fa fa-chevron-down hidden-sm hidden-xs"></i>
																						Treatment Details </a>
																				</h4>
																			</div>
																			<div class="col-lg-3 col-md-3 col-sm-3"></div>

																		</div>



																		<div id="collapseOne" class="panel-collapse collapse"
																			role="tabpanel" aria-labelledby="headingOne"
																			style="height: 50px">
																			<div class="panel-body sidebar">





																				<s:if test="treatmentEpisodeList.size!=0">
																					<s:iterator value="treatmentEpisodeList"
																						status="rowstatus">

																						<p class="tbp">
																							<s:property value="usedSession" />
																							session
																							<s:property value="treatmentEpisodeName" />
																							<s:if test="trtmentStatus==0">
														 		(Ongoing)
														 	</s:if>
																							<s:else>
														 		(Discharged)
														 	</s:else>
																						</p>
																					</s:iterator>
																				</s:if>

																			</div>
																		</div>
																	</div>
																	<div class="panel panel-default">
																		<div class="row panel-heading" role="tab"
																			id="headingTwo">
																			<div class="col-lg-9 col-md-9 col-sm-9">
																				<h4 class="panel-title">
																					<a class="collapsed" data-toggle="collapse"
																						data-parent="#accordion" href="#collapseTwo"
																						aria-expanded="false" aria-controls="collapseTwo"><i
																						class="fa fa-chevron-down hidden-sm hidden-xs"></i>
																						Documents &nbsp;&nbsp;<a href="#"
																						onclick="showdocfilter()" data-toggle="modal"
																						data-target="#filter"><i class="fa fa-filter"
																							aria-hidden="true" style="color: orange;"></i></a> </a>
																				</h4>
																			</div>
																			<div class="col-lg-3 col-md-3 col-sm-3">
																				<a href="#" class="pull-right hidden-xs"
																					data-toggle="modal" data-target="#uploaddoc"
																					onclick="clearUplaodDocumentPopup()" Title="Upload"
																					style="color: #fff;"><i class="fa fa-upload"></i></a>
																			</div>

																		</div>
																		<div id="collapseTwo" class="panel-collapse collapse"
																			role="tabpanel" aria-labelledby="headingTwo"
																			style="height: 50px">
																			<div class="panel-body sidebar">

																				<s:if test="docList.size!=0">
																					<%
																						int count = 1;
																					%>
																					<s:iterator value="docList" status="rowstatus">
																						<div class="row">
																							<div class="col-lg-12 col-md-12 col-xs-12">

																								<div class="col-lg-3 col-md-2 col-xs-2 editdoc"
																									style="padding-left: 0px;">
																									<%=count%>.&nbsp;
																									<%
																										if (!loginInfo.getLoginType().equals("shareemr")) {
																									%>
																									<a href="javascript:void(0)"
																										data-toggle="modal" data-target="#uploaddoc"
																										onclick="editDocuments('<s:property value = "id"/>','<s:property value = "doctType"/>','<s:property value = "description"/>','<s:property value = "fileName"/>')"
																										title="Edit"><i class="fa fa-edit"></i></a>
																									<%
																										} else {
																									%>
																									<i class="fa fa-edit"></i>
																									<%
																										}
																									%>
																								</div>

																								<div
																									class="col-lg-1 col-md-2 col-xs-2 deletne paddate">
																									<s:form theme="simple"
																										id="deleteDocuments_%{id}"
																										action="deleteDocumentsEmr">
																										<s:hidden name="deleteDoctId" value="%{id}"
																											id="deleteDoctId" />
																										<s:hidden id="clientname"
																											value="%{clientname}" name="clientname"></s:hidden>
																										<s:hidden id="diaryUser" value="%{diaryUser}"
																											name="diaryUser"></s:hidden>
																										<s:hidden id="condition" value="%{condition}"
																											name="condition"></s:hidden>

																										<%
																											if (!loginInfo.getLoginType().equals("shareemr")) {
																										%>
																										<a href="#"
																											onclick="deleteDocuments('<s:property value = "id"/>')"
																											title="Delete" class="text-danger"><i
																											class="fa fa-trash-o"></i></a>
																										<%
																											} else {
																										%>
																										<i class="fa fa-trash-o"></i>
																										<%
																											}
																										%>
																									</s:form>
																								</div>
																								<div
																									class="col-lg-2 col-md-3 col-xs-3 assesdelete">
																									<p class="font11">
																										<s:property value="doctType" />
																									</p>
																								</div>
																								<%-- <div class="col-lg-1 col-md-1 col-xs-1" style="padding-left: 0px;">
																<s:form theme="simple" id = "downloadDocuments" action="downloadDocumentsEmr"> 
									                                	<s:hidden name = "deleteDoctId" value="%{id}" id = "deleteDoctId"/>
									                                	<s:hidden id = "clientname" value="%{clientname}" name = "clientname"></s:hidden>
									                 					<s:hidden id = "diaryUser" value="%{diaryUser}" name = "diaryUser"></s:hidden>
									                 					<s:hidden id = "condition" value="%{condition}" name = "condition"></s:hidden>
															<a href="#" onclick="downloadDocuments()" title = "Download"><i class="fa fa-download"></i></a> 
																	</s:form>
																<a
																	href="downloadDocEmr?filename=<s:property value="fileName"/>&id=<s:property value="id"/>"><i
																	class="fa fa-download"></i></a>
															</div> --%>
																								<div class="col-lg-6 col-md-6 col-xs-4 marlft21">
																									<p class="marraig"
																										style="margin-bottom: -2px; font-size: 11px;">
																										<s:property value="lastModified" />
																									</p>


																									<%
																										count++;
																									%>
																									<a
																										href="downloadDocEmr?filename=<s:property value="fileName"/>&id=<s:property value="id"/>"
																										title="Download" class="font11"> <s:if
																											test="invstid>0">
																											<s:property value="invstFoleName" />
																										</s:if> <s:else>
																											<s:property value="fileName" />
																										</s:else>
																									</a>
																								</div>
																								<div class="col-lg-12 col-md-12 col-xs-12">
																									<p class="docuto">
																										<s:property value="description" />
																										&nbsp;<a href="javascript:void(0)"
																											data-toggle="modal" data-target="#uploaddoc"
																											onclick="editDocuments('<s:property value = "id"/>','<s:property value = "doctType"/>','<s:property value = "description"/>','<s:property value = "fileName"/>')"
																											title="read more">more</a>
																									</p>
																								</div>




																							</div>


																						</div>

																					</s:iterator>

																				</s:if>




																				<!-- admission forms -->
																				<s:if test="addmissionList.size>0">
																					<%
																						int ipdcount = 1;
																					%>
																					<s:iterator value="addmissionList">
																						<div class="row">
																							<div class="col-lg-2 col-md-2 col-xs-2">
																								<%=ipdcount%>.&nbsp; <a href="#"
																									onclick="openEmrPopup('printCommonnew?selectedid=<s:property value="id"/>')"
																									title="Print" class="assesprint"><i
																									class="fa fa-print"></i></a>
																							</div>
																							<div class="col-lg-1 col-md-2 col-xs-2">
																								<a href="#" title="Delete" class="text-danger"><i
																									class="fa fa-trash-o"></i></a>
																							</div>
																							<div class="col-lg-2 col-md-2 col-xs-2">
																								<a href=""><i class="fa fa-line-chart"
																									aria-hidden="true"></i></a>
																							</div>
																							<div class="col-lg-6 col-md-6 col-xs-6">
																								<p>

																									<%
																										if (!loginInfo.getLoginType().equals("shareemr")) {
																									%>
																									<s:if test="casualtyipd==0">
																										<a href="#"
																											onclick="openEmrPopup('editCommonnew?selectedid=<s:property value = "id"/>&action=0')"
																											class="font11"><s:property
																												value="admissiondate" /> </a>
																									</s:if>
																									<s:else>
																										<a href="#"
																											onclick="openEmrPopup('editCommonnew?selectedid=<s:property value = "id"/>&action=0')"
																											class="font11"><s:property
																												value="admissiondate" /> </a>
																									</s:else>
																									<%
																										} else {
																									%>
																									<a href="#" class="font11"><s:property
																											value="admissiondate" /> </a>
																									<%
																										}
																									%>
																								</p>
																							</div>
																							<div class="col-lg-12 col-md-12 col-xs-12 set">
																								<s:if test="casualtyipd==0">
																									<p class="font11">IPD Form</p>
																								</s:if>
																								<s:else>
																									<p class="font11">Casualty Form</p>
																								</s:else>
																							</div>

																						</div>

																						<%
																							ipdcount++;
																						%>
																					</s:iterator>
																				</s:if>


																				<!-- Discharge forms -->

																				<s:if test="ipdsdischargeList.size>0">
																					<%
																						int disipdcount = 1;
																					%>
																					<s:iterator value="ipdsdischargeList">
																						<div class="row">
																							<div class="col-lg-2 col-md-2 col-xs-2">
																								<%=disipdcount%>.&nbsp;<a href="#"
																									onclick="openEmrPopup('printdischargeCommonnew?selectedid=<s:property value="id"/>')"
																									title="Print" class="assesprint"><i
																									class="fa fa-print"></i></a>
																							</div>
																							<div class="col-lg-1 col-md-2 col-xs-2">
																								<a href="#" title="Delete" class="text-danger"><i
																									class="fa fa-trash-o"></i></a>
																							</div>
																							<div class="col-lg-2 col-md-2 col-xs-2">
																								<a href=""><i class="fa fa-line-chart"
																									aria-hidden="true"></i></a>
																							</div>

																							<div class="col-lg-6 col-md-6 col-xs-6">
																								<!--<p class="font11"></p>
																-->
																								<p>
																									<%
																										if (!loginInfo.getLoginType().equals("shareemr")) {
																									%>
																									<a href="#"
																										onclick="openEmrPopup('dischargeCommonnew?selectedid=<s:property value = "id"/>&clientid=<s:property value="clientid"/>')"
																										class="font11"><s:property
																											value="admissiondate" /> </a>
																									<%
																										} else {
																									%>
																									<a href="#" class="font11"><s:property
																											value="admissiondate" /> </a>
																									<%
																										}
																									%>
																								</p>
																							</div>
																							<div class="col-lg-12 col-md-12 col-xs-12 set">
																								<p class="font11">Discharge Form</p>
																							</div>

																						</div>

																						<%
																							disipdcount++;
																						%>
																					</s:iterator>
																				</s:if>



																				<s:if test="assessmentFormsList.size!=0">
																					<%
																						int count = 1;
																					%>
																					<s:iterator value="assessmentFormsList"
																						status="rowstatus">

																						<s:if test="type == 1">
																							<div class="row">
																								<div class="col-lg-2 col-md-2 col-xs-2">
																									<%=count%>.&nbsp;<a href="#"
																										onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2&action=print&formtype=<s:property value="formtype"/>')"
																										title="Print" class="assesprint"><i
																										class="fa fa-print"></i></a>
																								</div>
																								<div class="col-lg-1 col-md-2 col-xs-2">
																									<a href="#" title="Delete" class="text-danger"><i
																										class="fa fa-trash-o"></i></a>
																								</div>

																								<div class="col-lg-2 col-md-2 col-xs-2">
																									<a href="#"
																										onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2&action=dtr&formtype=<s:property value="formtype"/>')">
																										<i class="fa fa-line-chart" aria-hidden="true"></i>
																									</a>
																								</div>
																								<div class="col-lg-6 col-md-6 col-xs-6">
																									<p class="font11">
																										<s:property value="lastmodified" />
																									</p>

																								</div>
																								<div class="col-lg-12 col-md-12 colxs-12">
																									<div class="col-lg-6 col-md-6 col-xs-6">
																										<p class="font11">Assesment Form</p>
																									</div>
																									<div class="col-lg-6 col-md-6 col-xs-6">
																										<p>
																											<a href="#"
																												onclick="openEmrPopup('getDetailsAssessmentTemplate?id=<s:property value = "id"/>')"
																												class="font11"> <s:property
																													value="templateName" />
																											</a>
																										</p>
																									</div>
																								</div>
																							</div>

																						</s:if>
																						<s:else>
																							<div class="row">
																								<div class="col-lg-2 col-md-2 col-xs-2">
																									<%=count%>.&nbsp;<a href="#"
																										onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=1&action=print&formtype=<s:property value="formtype"/>')"
																										title="Print" class="assesprint"><i
																										class="fa fa-print"></i></a>
																								</div>
																								<div class="col-lg-1 col-md-2 col-xs-2">

																									<a
																										href="delassesmentEmr?selectedid=<s:property value="id"/>&diaryUser=<s:property value="diaryUser"/>&clientname=<s:property value="clientname"/>&condition=<s:property value="condition"/>"
																										onclick="return confirmedDelete()"
																										title="Delete" class="text-danger"><i
																										class="fa fa-trash-o"></i></a>
																								</div>
																								<div class="col-lg-2 col-md-2 col-xs-2">
																									<a href="#"
																										onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2&action=dtr&formtype=<s:property value="formtype"/>')">
																										<i class="fa fa-line-chart" aria-hidden="true"></i>
																									</a>
																								</div>
																								<div class="col-lg-6 col-md-6 col-xs-6">
																									<p class="font11" style="margin-bottom: -2px;">
																										<s:property value="lastmodified" />
																									</p>


																								</div>
																								<div class="col-lg-12 col-md-12 colxs-12">
																									<div class="col-lg-6 col-md-6 col-xs-6">
																										<p class="font11">Assesment Form</p>
																									</div>
																									<div class="col-lg-6 col-md-6 col-xs-6">
																										<p>
																											<%
																												if (!loginInfo.getLoginType().equals("shareemr")) {
																											%>
																											<a href="#"
																												onclick="openEmrPopup('editListAssessmentForm?id=<s:property value = "id"/>&actionType=2&formtype=<s:property value="formtype"/>')"
																												class="font11"> <s:property
																													value="templateName" />
																											</a>
																											<%
																												} else {
																											%>
																											<a href="#" class="font11"> <s:property
																													value="templateName" />
																											</a>
																											<%
																												}
																											%>
																										</p>
																									</div>
																								</div>


																							</div>

																						</s:else>


																						<%
																							count++;
																						%>
																					</s:iterator>

																				</s:if>
																			</div>
																		</div>
																	</div>

																	<div class="panel panel-default">
																		<div class="row panel-heading" role="tab"
																			id="headingThree">
																			<div class="col-lg-9 col-md-9 col-sm-9">
																				<h4 class="panel-title">
																					<a class="collapsed" data-toggle="collapse"
																						data-parent="#accordion" href="#collapseThree"
																						aria-expanded="false"
																						aria-controls="collapseThree"> <i
																						class="fa fa-chevron-down hidden-sm hidden-xs"></i>
																						Medical Records


																					</a>
																				</h4>
																			</div>
																			<div class="col-lg-3 col-md-3 col-sm-3">
																				<a href="" class="pull-right hidden-xs"
																					data-toggle="modal" data-target="#addmedicalrecord"
																					Title="Add Record" style="color: #fff;"><i
																					class="fa fa-plus"></i></a>
																			</div>

																		</div>
																		<div id="collapseThree"
																			class="panel-collapse collapse " role="tabpanel"
																			aria-labelledby="headingThree" style="height: 40px">
																			<div class="panel-body sidebar">

																				<s:if test="medicalRecordsTypeList.size!=0">
																					<s:iterator value="medicalRecordsTypeList"
																						status="rowstatus">

																						<s:if test="medicalRecordsList.size!=0">
																							<%
																								int count = 1;
																							%>
																							<s:iterator value="medicalRecordsList"
																								status="rowstatus">

																								<div class="row">
																									<div class="">
																										<div
																											class="col-lg-3 col-md-2 col-xs-2 editdoc">
																											<%=count%>.&nbsp;<a href="javascript:void(0)"
																												data-toggle="modal"
																												data-target="#editmedicalrecord"
																												onclick="editMedicalRecords('<s:property value = "id"/>','<s:property value = "medicalRecordType"/>','<s:property value = "description"/>')"
																												title="Edit"><i class="fa fa-edit"></i></a>

																										</div>
																										<div
																											class="col-lg-1 col-md-2 col-xs-2 medicaldelet">
																											<s:form theme="simple"
																												id="deleteMedicalRecord_%{id}"
																												action="deleteMedicalRecordEmr">
																												<s:hidden name="deleteMedicalId"
																													value="%{id}" id="deleteMedicalId" />
																												<s:hidden id="clientname"
																													value="%{clientname}" name="clientname"></s:hidden>
																												<s:hidden id="diaryUser"
																													value="%{diaryUser}" name="diaryUser"></s:hidden>
																												<s:hidden id="condition"
																													value="%{condition}" name="condition"></s:hidden>
																												<s:hidden id="deletemediclapmtId"
																													name="apmtId" />




																												<a href="#"
																													onclick="deleteMedicalRecord('<s:property value = "id"/>')"
																													title="Delete" class="text-danger"><i
																													class="fa fa-trash-o"></i></a>
																											</s:form>
																										</div>
																										<div
																											class="col-lg-2 col-md-2 col-xs-2 marlft17">
																											<p class="font11">
																												<s:property value="medicalRecordType" />
																											</p>
																										</div>
																										<div class="col-lg-6 col-md-6 col-xs-6">
																											<p class="font11"
																												style="margin-bottom: -2px;">
																												<s:property value="lastModified" />
																											</p>
																											<p class="font11">
																												<s:property value="description" />
																												<a href="javascript:void(0)"
																													data-toggle="modal"
																													data-target="#editmedicalrecord"
																													onclick="editMedicalRecords('<s:property value = "id"/>','<s:property value = "medicalRecordType"/>','<s:property value = "description"/>')"
																													title="read more">&nbsp;more</a>
																											</p>
																										</div>


																									</div>
																								</div>

																								<%
																									count++;
																								%>

																							</s:iterator>
																						</s:if>

																					</s:iterator>
																				</s:if>


																			</div>
																		</div>
																	</div>

																	<div class="panel panel-default">
																		<div class="row panel-heading" role="tab"
																			id="headingfour">
																			<div class="col-lg-9 col-md-9 col-sm-9">
																				<h4 class="panel-title">
																					<a class="collapsed" data-toggle="collapse"
																						data-parent="#accordion" href="#collapsefour"
																						aria-expanded="false" aria-controls="collapsefive">
																						<i class="fa fa-chevron-down hidden-sm hidden-xs"></i>
																						Prescription <!-- <a href="#" onclick="showpriscription()"
												class="pull-right" data-toggle="modal"
												><i class="fa fa-plus"></i>
													Add</a> -->
																					</a>
																				</h4>
																			</div>
																			<div class="col-lg-3 col-md-3 col-sm-3"></div>

																		</div>
																		<div id="collapsefour"
																			class="panel-collapse collapse " role="tabpanel"
																			aria-labelledby="headingfour" style="height: 40px">
																			<div class="panel-body sidebar" id=""></div>
																		</div>
																	</div>

																	<div class="panel panel-default">
																		<div class="row panel-heading" role="tab"
																			id="headingfive">
																			<div class="col-lg-9 col-md-9 col-sm-9">
																				<h4 class="panel-title">
																					<a class="collapsed" data-toggle="collapse"
																						data-parent="#accordion" href="#collapsefive"
																						aria-expanded="false" aria-controls="collapsesix">
																						<i class="fa fa-chevron-down hidden-sm hidden-xs"></i>
																						Investigation <!-- <a href="#" onclick="showInvestigation()"
												class="pull-right" data-toggle="modal"
												><i class="fa fa-plus"></i>
													Add</a> -->
																					</a>
																				</h4>
																			</div>
																			<div class="col-lg-3 col-md-3 col-sm-3"></div>

																		</div>
																		<div id="collapsefive"
																			class="panel-collapse collapse " role="tabpanel"
																			aria-labelledby="headingfive" style="height: 40px">
																			<div class="panel-body sidebar" id=""></div>
																		</div>
																	</div>

																	<div class="panel panel-default">
																		<div class="row panel-heading" role="tab"
																			id="headingsix">
																			<div class="col-lg-9 col-md-9 col-sm-9">
																				<h4 class="panel-title">
																					<a class="collapsed" data-toggle="collapse"
																						data-parent="#accordion" href="#collapsesix"
																						aria-expanded="false" aria-controls="collapseFour">
																						<i class="fa fa-chevron-down hidden-sm hidden-xs"></i>
																						Patient Media

																					</a>
																				</h4>
																			</div>
																			<div class="col-lg-3 col-sm-3 col-md-3">
																				<a href="" class="pull-right hidden-xs"
																					data-toggle="modal" onclick="uploadVideoPopup()"
																					data-target="#" title="Upload New Video Clip"
																					Title="Upload New Video Clip" style="color: #fff;"><i
																					class="fa fa-video-camera" aria-hidden="true"></i></a>
																				<a href="" class="pull-right hidden-xs"
																					data-toggle="modal" onclick="setEditor()"
																					data-target="#editQuickChartPopup"
																					title="Upload Chart" Title="Upload Image"
																					style="color: #fff;"><i class="fa fa-picture-o"
																					aria-hidden="true"></i>&nbsp;&nbsp;</a>

																			</div>

																		</div>
																		<div id="collapsesix" class="panel-collapse collapse "
																			role="tabpanel" aria-labelledby="headingsix">
																			<div class="panel-body sidebar"
																				style="height: 140px !important;">
																				<div id="owl-careers" class="row ">
																					<s:iterator value="imageDataList"
																						status="rowstatus">
																						<div class="thumbimg">
																							<img class="scrollimg img-responsive"
																								src="_assets/img/body01.JPG"
																								onclick="setImageInPopup('<s:property value = "imageData"/>','<s:property value = "id"/>')" />
																							<s:form theme="simple"
																								action="deleteClientImageEmr"
																								id="deleteClientImage">
																								<s:hidden name="clientDataId" value="%{id}"
																									id="clientDataId1"></s:hidden>
																								<s:hidden id="clientname" value="%{clientname}"
																									name="clientname"></s:hidden>
																								<s:hidden id="diaryUser" value="%{diaryUser}"
																									name="diaryUser"></s:hidden>
																								<s:hidden id="condition" value="%{condition}"
																									name="condition"></s:hidden>
																								<s:hidden id="delimageapmtId" name="apmtId" />
																								<i class="fa fa-times delete"
																									onclick="deleteClientImage()"></i>
																							</s:form>

																						</div>
																					</s:iterator>
																					<s:iterator value="videoList" status="rowstatus">
																						<div class="thumbimg">
																							<img class="scrollimg img-responsive"
																								src="_assets/img/player.jpg"
																								onclick="openVideoClipPopup('<s:property value = "videoFileName"/>','<s:property value = "id"/>')" />

																						</div>
																					</s:iterator>
																				</div>

																			</div>
																		</div>
																	</div>




																</div>
															</div>

														</ul>
														<!-- sidebar menu end-->
													</div>
												</section>
												<!--sidebar end-->
											</div>
										</ul>
									</div>
									<div id="divider" class="resizeable"></div>
								</div>





							</div>



						</div>

					</div>





					<!--Add Consultation Note Model-->
					<div class="modal fade notranslate" id="addConsultationNote"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
						aria-hidden="true" data-keyboard="false" data-backdrop="static"
						style="overflow: scroll !important;">
						<div class="modal-dialog modal-lg" style="width: 85%;">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										Update Notes : <b><span id="hdntrtmentspan" class="hidden"></span></b>
										| Date <b><span id="hdnapmtspan"> <s:property
													value="commencing" />
										</span> </b>
										<button onclick="saveAddConsultationNote()" title="Save"
											type="button" class="btn mybtn topsave"
											style="margin-left: 813px;">Save</button>
									</h4>
								</div>
								<s:form action="addConsultationNoteEmr" method="POST"
									theme="simple" id="addconsultationFrm">
									<s:hidden name="hindiconsnote" id="hindiconsnote"
										value="दिन मे एक बार" />
									<s:hidden name='isbookapmtfollowup' id='isbookapmtfollowup'></s:hidden>
									<s:hidden name="finaldiagnosis" id="finaldiagnosis" />
									<s:hidden name="emripdid" id="emripdid" />
									<div class="modal-body">

										<div id="updatepop" class="">
											<div class="">
												<div
													class="col-lg-9 col-md-9 col-xs-12 col-sm-12 paddingnil">
													<div class="heigsetas">
														<div class="col-lg-12 col-md-12 topbarback hidden">
															<div class="col-lg-3 col-md-3 hidden"
																style="padding-left: 0px;">
																<div class="form-group">
																	<select class="form-control showToolTip chosen-select"
																		onchange="changefun(this.value)"
																		headerValue="Select Heading">
																		<option value="SAOP">SAOP</option>
																		<option value="Initial">Initial</option>
																		<option value="Problem">Problem</option>
																		<option value="Examination">Examination</option>
																		<option value="Assessment">Assessment</option>
																		<option value="Assessment">Treatment</option>
																		<option value="Plan">Plan</option>
																	</select>
																</div>
															</div>
															<div class="col-lg-5 col-md-5 hidden"
																style="padding-left: 0px; padding-right: 0px;">
																<div class="form-group">
																	<input type="hidden" id="consCondition">
																</div>
															</div>
															<div class="col-lg-3 col-md-3 setplubtn hidden">

																<a type="button" onclick="setAllDiagnosis('consNote')"
																	href="#" class="btnman btn-new"><i
																	class="fa fa-plus-square fa-2x"></i></a>
															</div>




															<s:hidden id="clientname" value="%{clientname}"
																name="clientname"></s:hidden>
															<s:hidden id="diaryUser" value="%{diaryUser}"
																name="diaryUser"></s:hidden>
															<s:hidden id="condition" value="%{condition}"
																name="condition"></s:hidden>




														</div>
														<s:textarea cssStyle="height:450px;"
															cssClass="form-control showToolTip"
															placeholder="Enter Note For Todays Appointment"
															data-toggle="tooltip" rows="20" cols="30" name="consNote"
															id="consNote" onmouseup="showOffset()"></s:textarea>
														<s:hidden id="apmtId" name="apmtId" />
														<div>
															<div class="col-lg-12 col-md-12 saveicset">




																<div class="col-lg-3 col-md-3 col-xs-6 hidden"
																	id="dischargeclientdiv">
																	<a href="#" onclick="toggleDischarge()">Discharge
																		Patient</a>
																</div>


															</div>

														</div>
													</div>

												</div>



												<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 aadedf">

													<div class="panel-group managewidhe" style=""
														id="accordion" role="tablist" aria-multiselectable="true">




														<div class="panel panel-default"
															style="height: 41px; padding-top: 11px; font-size: medium; background-color: #4eb6c2;">
															<div class="recordsnotesn" role="tab" id="">
																<h4 class="panel-title">
																	<span> <a class="recordsnotesfont" role="button"
																		style="color: white; font-weight: 800;" href=""
																		data-toggle="modal" data-target="#commonvoiceover">
																			&nbsp;Record Notes </a> <a data-toggle="modal"
																		data-target="#commonvoiceover" href="#"><i
																			class="fa fa-microphone  recordsnotesmicroicon fa-2x"
																			style="color: white; margin-right: 16px;"></i></a>

																	</span> </span>

																</h4>
															</div>

														</div>




														<div class="panel panel-default">
															<div class="panel-heading" role="tab" id="headingOne">
																<h4 class="panel-title">
																	<span> <a role="button" data-toggle="collapse"
																		data-parent="#accordion" href="#addcon1"
																		aria-expanded="true" aria-controls="addcon1"
																		style="color: #fff;"> <i class="fa fa-angle-down"
																			aria-hidden="true"></i>&nbsp;Medicine
																	</a> <span> <%
 	if (!loginInfo.getJobTitle().equals("Pathlab")) {
 %>

																			<a href="#"><i onclick="showpriscription()"
																				title="Add Prescription" style="color: white;"
																				class="fa fa-plus-square fa-2x aadpres"></i></a> <%
 	}
 %>


																	</span>

																	</span>

																</h4>
															</div>
															<div id="addcon1" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingOne">
																<div class="panel-body">
																	<div id="alldataprisctable" class="row rowblank">


																		<!-- </div> -->

																		<!--  <tbody id="alldataprisctable">
                                        <tr>
                                            <td>6/11/2015</td>
                                            <td>Rosavel 10</td>
                                           

                                        </tr>
                                      


                                    </tbody> -->

																	</div>
																</div>
															</div>
														</div>


														<div class="panel panel-default">
															<div class="panel-heading" role="tab" id="headingfour">
																<h4 class="panel-title">
																	<span> <a style="color: #fff !important;"
																		class="collapsed" role="button" data-toggle="collapse"
																		data-parent="#accordion" href="#addcon4"
																		aria-expanded="false" aria-controls="addcon4"> <i
																			class="fa fa-angle-down" aria-hidden="true"></i>&nbsp;Investigation
																	</a> <span> <%
 	if (!loginInfo.getJobTitle().equals("Medical Store")) {
 %>
																			<a href="#"><i onclick="showInvestigation()"
																				style="color: white;" title="Add Investigation"
																				class="fa fa-plus-square fa-2x aadpres"></i></a> <%
 	}
 %>
																	</span>
																	</span>

																</h4>
															</div>
															<div id="addcon4" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingfour">
																<div class="panel-body">
																	<div id="allinvesttable" class="row rowblank"></div>
																</div>
															</div>
														</div>


														<div class="panel panel-default">
															<div class="panel-heading" role="tab" id="headingTwo">
																<h4 class="panel-title">
																	<span> <a class="collapsed" role="button"
																		style="color: #fff !important;" data-toggle="collapse"
																		data-parent="#accordion" href="#addcon2"
																		aria-expanded="false" aria-controls="addcon2"> <i
																			class="fa fa-angle-down" aria-hidden="true"></i>&nbsp;Symbols
																			<small style="color: #fff;" class="hidden">(Drag
																				& Drop)</small>
																	</a>
																	</span>

																</h4>
															</div>
															<div id="addcon2" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingTwo">
																<div class="panel-body" style="padding: 5px !important;">
																	<img src="emr/img/downarow.png" name="image1" /> <img
																		src="emr/img/leftarow.png" name="image2" /> <img
																		src="emr/img/linea-arrows-10_e000(0)_48.png"
																		name="image3" /> <img
																		src="emr/img/linea-arrows-10_e013(1)_48.png"
																		name="image4" /> <img src="emr/img/linedownarow.png"
																		name="image5" /> <img
																		src="emr/img/linedownarowright.png" name="image6" />
																	<img src="emr/img/linedownbreakleft.png" name="image7" />
																	<img src="emr/img/linedownbreakright.png" name="image8" />
																	<img src="emr/img/lineupdownarow.png" name="image9" />
																	<img src="emr/img/rightarow.png" name="image10" /> <img
																		src="emr/img/southpoll.png" name="image11" /> <img
																		src="emr/img/uparow.png" name="image12" />
																</div>
															</div>
														</div>




														<div class="panel panel-default">
															<div class="panel-heading" role="tab" id="headingfive">
																<h4 class="panel-title">
																	<span> <a style="color: #fff !important;"
																		class="collapsed" role="button" data-toggle="collapse"
																		data-parent="#accordion" href="#addcon5"
																		aria-expanded="false" aria-controls="addcon5"> <i
																			class="fa fa-angle-down" aria-hidden="true"></i>&nbsp;Templates
																			& Forms
																	</a> <a href="#" type="button"
																		class="btn btn-sm btn-primary"
																		style="margin-left: 1px; width: 52px;"
																		data-toggle="modal" data-target="#selectothertemlate"><i
																			class="fa fa-align-justify" aria-hidden="true"></i></a> <span>
																			<a href="#"
																			onclick="opencPopup('OtherTemplate?selectedid=15')"><i
																				style="color: white; margin-top: 5px;"
																				title="Add Template"
																				class="fa fa-plus-square fa-2x aadpres"></i></a>
																	</span>
																	</span>

																</h4>
															</div>
															<div id="addcon5" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingfive">
																<div class="panel-body scrollirf">
																	<%-- <s:if test="otherTemplateList.size>0"> --%>
																		<span style="font-weight: bold">Template : </span>
																		<span class="pull-right" style="padding-right: 3px;"><a
																			href="#"
																			onclick="setOtherTemplateBySpeciality('',<s:property value="diaryUser"/>,'add')"><i
																				class="fa fa-refresh"></i></a></span>
																		<%
																			int aot = 1;
																		%>
																		<table width="100%" class="filtertab"
																			id="templatetableid">
																			<s:iterator value="otherTemplateList">
																				<tr>
																					<td><%=aot%>.</td>
																					<td><a href="#"
																						onclick="setselectedtemplatedata('<s:property value="title"/>','<s:property value="id"/>')"><s:property
																								value="title" /></a></td>

																				</tr>
																				<%
																					aot++;
																				%>
																			</s:iterator>
																		</table>

																	<%-- </s:if> --%>
																</div>
															</div>
														</div>

														<div class="panel panel-default hidden">
															<div class="panel-heading" role="tab" id="headingsix">
																<h4 class="panel-title">
																	<a class="collapsed" role="button"
																		data-toggle="collapse" data-parent="#accordion"
																		href="#addcon6" aria-expanded="false"
																		aria-controls="addcon6"> Discharge Patient </a>
																</h4>
															</div>
															<div id="addcon6" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingsix">
																<div class="panel-body">
																	<div style="margin-bottom: 5px !important;">
																		<span>Outcome</span>
																		<s:select cssClass="form-control"
																			name="dischrgeOutcomes" id="dischrgeOutcomes"
																			list="dischargeOutcomeList" listKey="id"
																			listValue="name" headerKey="0"
																			headerValue="Select Outcome" />
																	</div>
																	<div style="margin-bottom: 5px !important;">
																		<span>Status</span>
																		<s:select cssClass="form-control"
																			name="dischargeStatus" id="dischargeStatus"
																			list="dischargeStatusList" listKey="id"
																			listValue="name" headerKey="0"
																			headerValue="Select Status" />
																	</div>

																	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

																		<div class="form-group">

																			<div class="row">
																				<div class="col-lg-6 col-md-6 volvo">
																					<label for="exampleInputEmail1">Date</label>
																					<s:textfield cssClass="form-control"
																						id="dischargedate" name="dischargedate" />
																				</div>
																				<div class="col-lg-3 col-md-3">
																					<label for="exampleInputEmail1">HH</label>
																					<s:select cssStyle="width:42px;" name="hour"
																						id="hour" list="hourList" cssClass="form-control"
																						headerKey="0" headerValue="Select" />
																				</div>
																				<div class="col-lg-3 col-md-3">
																					<label for="exampleInputEmail1">MM</label>
																					<s:select cssStyle="width:42px;" name="minute"
																						id="minute" list="minuteList"
																						cssClass="form-control" headerKey="0"
																						headerValue="Select" />
																				</div>
																			</div>



																			<%-- <s:textfield type="email" cssClass="form-control" id="admissiontime" name="admissiontime" /> --%>

																		</div>



																	</div>
																	<div>

																		<div class="col-lg-4 col-md-4 col-xs-4 ditrxas">
																			<label>Discharge:</label>
																		</div>
																		<div class="col-lg-2 col-md-2 col-xs-2">
																			<s:checkbox cssStyle="margin-right:110px;"
																				name="chkDischarge" id="chkDischarge" />
																			<div id="addconbtnsdiv"></div>


																		</div>
																		<div class="col-lg-6 col-md-6 col-xs-6">
																			<button onclick="saveOnlyConsultationNote()"
																				type="button" class="btn btn-primary discharight">Discharge</button>

																		</div>

																	</div>
																</div>
															</div>
														</div>

													</div>

													<div style="margin-top: 5px; border: 2px solid #6699CC;">
														<div id="paragraphs" style="padding: 5px;">

															<div class="heiige">
																<input type="text" class="form-control"
																	placeholder="Search diagnosis here" id="newdiagnosis"
																	onkeyup="searchdiagnosisEmrJSON(this.value)"
																	style="width: 100%;" /> <br> <a href="#"
																	type="button" class="btn btn-default hidden"
																	onclick="dispDIv()"
																	style="width: 100%; margin-bottom: 5px;">Add New
																	Diagnosis</a> <a href="#" class="btn btn-sm btn-info"
																	style="margin-left: 60px; padding-top: 2px; margin-top: -25px; width: 49%; padding-left: 10px;"
																	onclick="savediagnosisfast()">Save</a><br>
																<div id="dispid" class="hidden">
																	<div class="form-inline">
																		<div class="form-group" style="width: 55%;">
																			<input type="text" class="form-control"
																				placeholder="Enter Diagnosis" id="newcondition"
																				style="width: 100%;">
																		</div>
																		<div class="form-group" style="width: 32%;">
																			<input type="text" class="form-control"
																				placeholder="ICD Code" id="icdcode"
																				style="width: 100%;">
																		</div>
																		<div class="form-group" style="width: 10%;">
																			<a href="#" class="btn-success btn-sm btn"
																				onclick="addnewCOndition1()"><i
																				class="fa fa-plus"></i></a>
																		</div>
																	</div>

																</div>
															</div>
															<div class="form-inline">
																<s:hidden name="odconditionstr" id="odconditionstr" />
																<table class="table table-responsive"
																	style="width: 100%; border: none;">
																	<tbody
																		style='height: 275px; display: block; overflow: scroll; width: 100% !important;'
																		id="conditiontbody">

																	</tbody>
																</table>
															</div>



														</div>


													</div>


													<div></div>

												</div>

											</div>


										</div>


										<!-- <b>Insert Follow Up : <input type="number" class="form-control" style="width:10%" placeholder="add followup days" name="" id=""> In Days</b> -->
										<div class="row row2" style="display: none;">

											<br>
											<div id="toggleDischargediv">
												<div class="col-lg-12 col-xs-12 wellbot">
													<div class="row row2">
														<div class="col-lg-12 col-xs-12">
															<div class="col-lg-2 col-md-2 col-xs-4">
																<label>Outcome</label>
															</div>
															<div class="col-lg-1 col-md-1 col-xs-1 marleftr">:</div>
															<div class="col-lg-3 col-md-3 col-xs-7 marlft54"></div>
														</div>
														<br> <br>
														<div class="row row2">
															<div class="col-lg-12 col-xs-12">
																<div class="col-lg-2 col-md-2 col-xs-4">
																	<label>Discharge Status</label>
																</div>
																<div class="col-lg-1 col-md-1 col-xs-1 marleftr">:</div>
																<div class="col-lg-3 col-md-3 col-xs-7 marlft54"></div>
															</div>

														</div>

													</div>
													<br>
													<div class="row row2"></div>
												</div>







											</div>


										</div>



									</div>

									<b style="font-size: large; margin-left: 8px"> Follow Up
										After: <input type="text" class="form-control"
										style="width: 14%; display: inline;"
										placeholder="add followup days" name="" id="fllowupdays"
										onchange="givefollowuptoemr()" autocomplete="off">
										Days
									</b>
									<label>&nbsp;&nbsp;&nbsp;&nbsp; Book Follow Up Apmt
										&nbsp;&nbsp;</label>
									<input type="checkbox" onclick="followUpStatusChange(this.checked)" name='bkapmtipd' id='bkapmtipd'>

									<div class="modal-footer">
										<button onclick="saveAddConsultationNote()" title="Save"
											type="button" class="btn btn-info" id='savenotesemrbtn'>Save</button>
										<button class="hidden" title="Print"
											onclick="openEmrPopup('printconsnoteEmr?clientid=<s:property value="clientname"/>&amptid=<s:property value="apmtId"/>&diaryuserid=<s:property value="diaryUser"/>&amptid=<s:property value="apmtId"/>&conditionid=<s:property value="condition"/>&action=0');"
											type="button" class="btn btn-primary">
											<i class="fa fa-print fa-2x"></i>
										</button>

									</div>
								</s:form>

							</div>
						</div>
					</div>


					<!-- Voice Popup Modal -->
					<div id="commonvoiceover" class="modal fade" role="dialog"
						style="background-color: rgba(0, 0, 0, 0.54);">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Record Notes</h4>
								</div>
								<div class="modal-body">
									<div class="col-lg-12 col-md-12 col-xs-12"
										style="padding: 0px;">
										<s:form action="saveotnotesNotAvailableSlot" theme="simple">
											<s:hidden name="id" id="id" />
											<div class="">


												<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
													style="padding: 0px;">
													<div class="">
														<div class="col-lg-12 col-md-12">
															<div class="form-group">
																<img src="cicon/mic_off.png"
																	class="img-responsive micimg"
																	onclick="startConverting1(this)" title="Microphone"
																	id="changer"></img>

															</div>
														</div>
														<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
															style="margin-top: 10px;">
															<!--
									    -->
															<s:textarea style="height: 250px;" rows="10" cols="8"
																id="otnotes" name="otnotes"
																cssClass="showToolTip form-control"
																data-toggle="tooltip" title="Enter Other Template Text"
																placeholder="Enter Other Template Text"></s:textarea>
														</div>


													</div>
												</div>

												<div
													class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-right hidden-print"
													style="margin-top: 10px;">
													<div class="form-group">
														<input type="button" class="btn btn-primary hidden"
															value="Print"> <input type="button"
															onclick="setVoiceoverText()" class="btn btn-primary"
															value="Save">
													</div>
												</div>

											</div>










										</s:form>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary hidden">Save</button>
								</div>
							</div>

						</div>
					</div>





					<!--Edit Quick Chart Image-->
					<s:form action="updateImageDateOfClientEmr"
						id="updateImageDateOfClient" theme="simple">
						<s:hidden id="clientImageDataId" name="clientImageDataId"></s:hidden>
						<s:hidden id="clientImageData" name="clientImageData"></s:hidden>
						<s:hidden id="clientname" value="%{clientname}" name="clientname"></s:hidden>
						<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
						<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
						<s:hidden id="updateimageapmtId" name="apmtId" />
						<div class="modal fade" id="editQuickChartPopup" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
							style="z-index: 9999;">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header bg-primary">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">Edit Image</h4>
									</div>
									<div class="modal-body"
										style="height: 550px; overflow-y: scroll;">
										<button type="button" class="btn btn-primary"
											onclick="updateImageData();">Update Image</button>
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">Close</button>
										<%-- <%@ include file="/minipaint/editor.jsp"%> --%>
										<jsp:include page="/minipaint/editor.jsp" /> 
									</div>




								</div>
							</div>
						</div>
					</s:form>



					<!--Symbol Popup Model-->
					<div class="modal fade" id="symbolPopup" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">
										<i class="fa fa-plus"></i>Copy & Paste Symbol
									</h4>
								</div>

								<div class="modal-body">
									<div class="row">
										<!--  <div id= "imagebrowser" onclick="imageAdder();" class="col-lg-6 col-md-6"> -->
										<div id="imagebrowser" class="col-lg-6 col-md-6">
											<img src="emr/img/downarow.png" name="image1" /> <img
												src="emr/img/leftarow.png" name="image2" /> <img
												src="emr/img/linea-arrows-10_e000(0)_48.png" name="image3" />
											<img src="emr/img/linea-arrows-10_e013(1)_48.png"
												name="image4" /> <img src="emr/img/linedownarow.png"
												name="image5" /> <img src="emr/img/linedownarowright.png"
												name="image6" /> <img src="emr/img/linedownbreakleft.png"
												name="image7" /> <img src="emr/img/linedownbreakright.png"
												name="image8" /> <img src="emr/img/lineupdownarow.png"
												name="image9" /> <img src="emr/img/rightarow.png"
												name="image10" /> <img src="emr/img/southpoll.png"
												name="image11" /> <img src="emr/img/uparow.png"
												name="image12" />
										</div>
									</div>
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>

								</div>
							</div>
						</div>
					</div>


					<!--Edit Consultation Note Model-->
					<div class="modal fade notranslate" id="editConsultationNote"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
						aria-hidden="true" data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog modal-lg" style="width: 85%;">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										Update Notes : <span id="ehdntrtmentspan"></span> | Date <span
											id="ehdnapmtspan"></span>
									</h4>
									<button onclick="updatesaveConsultationNote()" type="button"
										class="btn btn-info topsave btn-deft">Save</button>
								</div>
								<s:form action="editConsultationNoteEmr" method="post"
									theme="simple" id="editconsultationFrm">

									<div class="modal-body">
										<div class="row row1">


											<div class="marlft37">
												<div
													class="col-lg-9 col-md-9 col-xs-12 col-sm-12 paddingnil ">

													<div class=>
														<div
															class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topbarback heiauto">
															<div class="col-lg-3 col-md-3 hidden"
																style="padding-left: 0px;">
																<div class="form-group">
																	<select class="form-control showToolTip chosen-select"
																		onchange="" headerValue="Select Heading">
																		<option value="SAOP">SAOP</option>
																		<option value="Initial">Initial</option>
																		<option value="Problem">Problem</option>
																		<option value="Examination">Examination</option>
																		<option value="Assessment">Assessment</option>
																		<option value="Assessment">Treatment</option>
																		<option value="Plan">Plan</option>
																	</select>
																</div>

															</div>
															<div class="col-lg-5 col-md-5 paddxs hidden"
																style="padding-left: 0px; padding-right: 0px;">
																<div class="form-group">
																	<input type="hidden" id="editconsCondition">

																	<s:hidden id="clientname" value="%{clientname}"
																		name="clientname"></s:hidden>
																	<s:hidden id="diaryUser" value="%{diaryUser}"
																		name="diaryUser"></s:hidden>
																	<s:hidden id="condition" value="%{condition}"
																		name="condition"></s:hidden>
																	<s:hidden name="apmtId" value="%{apmtId}"
																		id="hdneditapmtid" />
																</div>
															</div>
															<div class="col-lg-3 col-md-3 setplubtn hidden">
																<a type="button"
																	onclick="setAllDiagnosis('editconsNote')" href="#"
																	class="btnman btn-new"><i class="fa fa-plus-square"></i></a>
															</div>
														</div>
														<input type="button" value="SOAP"
															onclick="setSOS('editconsNote')"
															class="btnman btn-new hidden"> <input
															type="button" value="Initial"
															onclick="setInitial('editconsNote')"
															class="btnman btn-new hidden"> <input
															type="button" value="Problem"
															onclick="setProblem('editconsNote')"
															class="btnman btn-new hidden"> <input
															type="button" value="Examination"
															onclick="setExamination('editconsNote')"
															class="btnman btn-new hidden"> <input
															type="button" value="Assessment"
															onclick="setAssessment('editconsNote')"
															class="btnman btn-new hidden"> <input
															type="button" value="Treatment"
															onclick="setTreatment('editconsNote')"
															class="btnman btn-new hidden"> <input
															type="button" value="Plan"
															onclick="setPlan('editconsNote')"
															class="btnman btn-new hidden"> <a type="button"
															onclick="setAllDiagnosis('editconsNote')" href="#"
															class="btnman btn-new hidden"><i
															class="fa fa-plus-square"></i></a>



														<s:textarea cssStyle="height:450px;"
															cssClass="form-control showToolTip"
															placeholder="Enter Note For Todays Appointment"
															data-toggle="tooltip" rows="20" cols="30" name="consNote"
															id="editconsNote"></s:textarea>
														<input type="hidden" id="hiddenidconsultnote"
															name="consulatation_note_id" onmouseup="showOffset()">
														<div>
															<div class="col-lg-12 col-md-12 saveicset">



																<div class="col-lg-3 col-md-3 col-xs-6 hidden"
																	id="dischargeclientdiv">
																	<a href="#" onclick="toggleDischarge()">Discharge
																		Client</a>
																</div>


															</div>

														</div>
													</div>

												</div>

												<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 aadedf">

													<div class="panel-group managewidhe" style="width: 311px"
														id="accordion" role="tablist" aria-multiselectable="true">
														<div class="panel panel-default">
															<div class="panel-heading" role="tab" id="headingOne"
																style="background-color: #4eb6c2;">
																<h4 class="panel-title">
																	<span> <a class="templateformeditcolor"
																		role="button" data-toggle="collapse"
																		data-parent="#accordion" href="#editcon1"
																		aria-expanded="true" style="color: white;"
																		aria-controls="editcon1"> Medicine </a>
																	</span>
																	<%
																		if (!loginInfo.getJobTitle().equals("Pathlab")) {
																	%>
																	<a href="#"><i
																		onclick="showeditpriscriptionpopup()"
																		title="Add Prescription"
																		class="fa fa-plus-square fa-2x aadpres"></i></a>
																	<%
																		}
																	%>
																</h4>
															</div>
															<div id="editcon1" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingOne">
																<div class="panel-body">
																	<div id="editalldataprisctable" class="row rowblank">
																	</div>
																</div>
															</div>
														</div>








														<div class="panel panel-default">
															<div class="panel-heading" role="tab" id="headingThree"
																style="background-color: #4eb6c2;">
																<h4 class="panel-title">
																	<span> <a
																		class="collapsed templateformeditcolor" role="button"
																		data-toggle="collapse" data-parent="#accordion"
																		href="#editcon3" style="color: white;"
																		aria-expanded="false" aria-controls="editcon3">
																			Investigation </a>
																	</span>
																	<%
																		if (!loginInfo.getJobTitle().equals("Medical Store")) {
																	%>
																	<a href="#"><i
																		onclick="showEditInvestigationPopup()"
																		title="Add Investigation"
																		class="fa fa-plus-square fa-2x aadpres"></i></a>
																	<%
																		}
																	%>
																</h4>
															</div>
															<div id="editcon3" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingThree"
																style="background-color: #4eb6c2;">
																<div class="panel-body">
																	<div id="alleditinvesttable" class="row rowblank"></div>

																</div>
															</div>
														</div>

														<div class="panel panel-default">
															<div class="panel-heading" role="tab" id="headingTwo"
																style="background-color: #4eb6c2;">
																<h4 class="panel-title">
																	<a class="collapsed templateformeditcolor"
																		role="button" data-toggle="collapse"
																		data-parent="#accordion" href="#editcon2"
																		aria-expanded="false" aria-controls="editcon2">
																		Symbols <small style="color: #fff;">(Drag &
																			Drop)</small>
																	</a>
																</h4>
															</div>
															<div id="editcon2" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingTwo"
																style="background-color: #4eb6c2;">
																<div class="panel-body" style="padding: 5px !important;">
																	<img src="emr/img/downarow.png" name="image1" /> <img
																		src="emr/img/leftarow.png" name="image2" /> <img
																		src="emr/img/linea-arrows-10_e000(0)_48.png"
																		name="image3" /> <img
																		src="emr/img/linea-arrows-10_e013(1)_48.png"
																		name="image4" /> <img src="emr/img/linedownarow.png"
																		name="image5" /> <img
																		src="emr/img/linedownarowright.png" name="image6" />
																	<img src="emr/img/linedownbreakleft.png" name="image7" />
																	<img src="emr/img/linedownbreakright.png" name="image8" />
																	<img src="emr/img/lineupdownarow.png" name="image9" />
																	<img src="emr/img/rightarow.png" name="image10" /> <img
																		src="emr/img/southpoll.png" name="image11" /> <img
																		src="emr/img/uparow.png" name="image12" />
																</div>
															</div>
														</div>

														<div class="panel panel-default">
															<div class="panel-heading" role="tab" id="headingfour"
																style="background-color: #4eb6c2;">
																<h4 class="panel-title">
																	<a class="collapsed templateformeditcolor"
																		role="button" data-toggle="collapse"
																		data-parent="#accordion" href="#editcon4"
																		aria-expanded="false" aria-controls="editcon3">
																		Templates & Forms </a> <a href="#" type="button"
																		class="btn btn-sm btn-primary templateformedit"
																		style="margin-left: 5px;" data-toggle="modal"
																		data-target="#editselectothertemlate"><i
																		class="fa fa-align-justify" aria-hidden="true"></i></a>
																</h4>
															</div>
															<div id="editcon4" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingfour">
																<div class="panel-body">
																	<%-- <s:if test="otherTemplateList.size>0"> --%>
																		<span style="font-weight: bold">Template : </span>



																		<%
																			int ot = 1;
																		%>
																		<table width="100%" id="edittemplatetableid">
																			<s:iterator value="otherTemplateList">
																				<tr>
																					<td><%=ot%>.</td>
																					<td><a href="#"
																						onclick="setselectedtemplatedata('<s:property value="title"/>','<s:property value="other_template_text"/>')"><s:property
																								value="title" /></a></td>

																				</tr>
																				<%
																					ot++;
																				%>
																			</s:iterator>
																		</table>

																	<%-- </s:if> --%>
																</div>
															</div>
														</div>


														<div class="panel panel-default hidden">
															<div class="panel-heading" role="tab" id="headingfive">
																<h4 class="panel-title">
																	<a class="collapsed" role="button"
																		data-toggle="collapse" data-parent="#accordion"
																		href="#editcon5" aria-expanded="false"
																		aria-controls="editcon5"> Discharge Client </a>
																</h4>
															</div>
															<div id="editcon5" class="panel-collapse collapse"
																role="tabpanel" aria-labelledby="headingfive">
																<div class="panel-body">
																	<div style="margin-bottom: 5px !important;">
																		<span>Outcome</s> <s:select cssClass="form-control"
																				name="dischrgeOutcomes" id="eddischrgeOutcomes"
																				list="dischargeOutcomeList" listKey="id"
																				listValue="name" headerKey="0"
																				headerValue="Select Outcomes" />
																	</div>
																	<div style="margin-bottom: 5px !important;">
																		<span>Status</span>
																		<s:select cssClass="form-control"
																			name="dischargeStatus" id="eddischargeStatus"
																			list="dischargeStatusList" listKey="id"
																			listValue="name" headerKey="0"
																			headerValue="Select Status" />
																	</div>
																	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

																		<div class="form-group">

																			<div class="row">
																				<div class="col-lg-6 col-md-6 volvo">
																					<label for="exampleInputEmail1">Date</label>
																					<s:textfield cssClass="form-control"
																						id="editdischargedate" name="dischargedate" />
																				</div>
																				<div class="col-lg-3 col-md-3">
																					<label for="exampleInputEmail1">HH</label>
																					<s:select cssStyle="width:42px;" name="hour"
																						id="edithour" list="hourList"
																						cssClass="form-control" headerKey="0"
																						headerValue="Select"></s:select>
																				</div>
																				<div class="col-lg-3 col-md-3">
																					<label for="exampleInputEmail1">MM</label>
																					<s:select cssStyle="width:42px;" name="minute"
																						id="editminute" list="minuteList"
																						cssClass="form-control" headerKey="0"
																						headerValue="Select" />
																				</div>
																			</div>




																		</div>



																	</div>
																	<div>

																		<div class="col-lg-3 col-md-3 col-xs-3 ditrxas">
																			<label>Discharge:</label>
																		</div>
																		<div class="col-lg-2 col-md-2 col-xs-2">
																			<s:checkbox cssStyle="margin-right:110px;"
																				theme="simple" name="chkDischarge"
																				id="edchkDischarge" />
																			<div id="addconbtnsdiv"></div>


																		</div>
																		<div class="col-lg-7 col-md-7 col-xs-7">
																			<button onclick="editSaveOnlyConsultationNote()"
																				type="button" class="btn btn-primary discharight">Discharge</button>

																		</div>

																	</div>
																</div>
															</div>
														</div>


														<div style="margin-top: 5px; border: 2px solid #6699CC;">
															<div id="paragraphs1" style="padding: 5px;">
																<a href="#" class="btn btn-sm mybtn"
																	style="float: right; margin-left: 208px; padding-top: 2px;"
																	onclick="savediagnosisfasteditEmrJSON()">Save</a>
																<div class="form-inline">
																	<input type="text" class="form-control"
																		id="newdiagnosisedit"
																		placeholder="Search diagnosis here"
																		onkeyup="searchdiagnosiseditEmrJSON(this.value)" />
																</div>
																<s:hidden name="odconditionstr" id="eodconditionstr" />
																<table class="table table-responsive"
																	style="width: 100%; border: none;">

																	<tbody id="conditiontbody2"
																		style='height: 275px; display: block; overflow: scroll; width: 100% !important;'>
																	</tbody>
																</table>

															</div>

														</div>

													</div>


												</div>

											</div>

										</div>




										<div class="row row2 hidden">
											<div class="col-lg-8 discharge " id="">

												<div class="col-lg-3 col-md-3 col-xs-6">
													<button onclick="updatesaveConsultationNote()"
														type="button" class="btn btn-primary"
														style="width: 122px;">Save</button>
												</div>

												<div class="col-lg-3 col-md-3 col-xs-6"
													id="eddischargeclientdiv">
													<a href="#" onclick="edtoggleDischarge()">Discharge
														Client</a>
												</div>
												<div class="col-lg-3 col-md-3 col-xs-6">
													<button
														onclick="openEmrPopup('printconsnoteEmr?clientid=<s:property value="clientname"/>&amptid=<s:property value="apmtId"/>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=0');"
														type="button" style="width: 122px;"
														class="btn btn-primary">Print</button>
												</div>
											</div>
											<br>
											<div style="display: none;" id="edtoggleDischargediv">
												<div class="col-lg-12 col-xs-12 wellbot">
													<div class="row row2">
														<div class="col-lg-12 col-xs-12">
															<div class="col-lg-2 col-md-2 col-xs-4">
																<label>Outcome</label>
															</div>
															<div class="col-lg-1 col-md-1 col-xs-1 marleftr">:</div>
															<div class="col-lg-3 col-md-3 col-xs-7 marlft54"></div>
														</div>
														<br> <br>
														<div class="row row2">
															<div class="col-lg-12 col-xs-12">
																<div class="col-lg-2 col-md-2 col-xs-4">
																	<label></label>
																</div>
																<div class="col-lg-1 col-md-1 col-xs-1 marleftr">:</div>
																<div class="col-lg-3 col-md-3 col-xs-7 marlft54"></div>
															</div>

														</div>

													</div>
													<br>
													<div class="row row2">
														<div class="col-lg-12 col-xs-12">
															<div class="col-lg-2 col-md-2 col-xs-4">
																<label>Discahrge</label>
															</div>
															<div class="col-lg-1 col-md-1 col-xs-1 marleftr">:</div>
															<div class="col-lg-3 col-md-2 col-xs-7 marlft54">

																<div id="edaddconbtnsdiv">
																	<input type="button" style="width: 100px;" value="Save"
																		class="btn btn-primary"
																		onclick="editSaveOnlyConsultationNote()">
																</div>
															</div>
														</div>



													</div>
												</div>
											</div>
										</div>


									</div>

									<b style="font-size: large; margin-left: 8px;"> Follow Up
										After: <input type="text" class="form-control"
										style="width: 14%; display: inline;"
										placeholder="add followup days" name="" id="fllowupdays"
										onchange="givefollowuptoemr()" autocomplete="off">
										Days
									</b>
									<label>&nbsp;&nbsp;&nbsp;&nbsp; Book Follow Up Apmt
										&nbsp;&nbsp;</label>
									<input type="checkbox" name='bkapmtipd' id='bkapmtipd'>


									<div class="modal-footer">
										<button onclick="updatesaveConsultationNote()" type="button"
											class="btn mybtn"
											style="margin-top: -45px; margin-left: -96px;">Save</button>
										<button class="hidden" title="Print"
											onclick="openEmrPopup('printconsnoteEmr?clientid=<s:property value="clientname"/>&amptid=<s:property value="apmtId"/>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=0');"
											type="button" class="btn btn-primary">
											<i class="fa fa-print fa-2x"></i>
										</button>
									</div>
								</s:form>
							</div>
						</div>
					</div>
					<!--Symbol Popup Model-->
					<div class="modal fade" id="editsymbolPopup" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										<i class="fa fa-plus"></i>Copy & Paste Symbol
									</h4>
								</div>

								<div class="modal-body">
									<div class="row">
										<!-- <div id="imagebrowseredit" onclick="imageAdderEdit();" class="col-lg-6 col-md-6"> -->
										<div id="imagebrowseredit" class="col-lg-6 col-md-6">
											<img src="emr/img/downarow.png" name="image1" /> <img
												src="emr/img/leftarow.png" name="image2" /> <img
												src="emr/img/linea-arrows-10_e000(0)_48.png" name="image3" />
											<img src="emr/img/linea-arrows-10_e013(1)_48.png"
												name="image4" /> <img src="emr/img/linedownarow.png"
												name="image5" /> <img src="emr/img/linedownarowright.png"
												name="image6" /> <img src="emr/img/linedownbreakleft.png"
												name="image7" /> <img src="emr/img/linedownbreakright.png"
												name="image8" /> <img src="emr/img/lineupdownarow.png"
												name="image9" /> <img src="emr/img/rightarow.png"
												name="image10" /> <img src="emr/img/southpoll.png"
												name="image11" /> <img src="emr/img/uparow.png"
												name="image12" />
										</div>
									</div>
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>

								</div>
							</div>
						</div>
					</div>





					<!--Upload Model-->
					<div class="modal fade" id="uploaddoc" style="z-index: 9999"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
						aria-hidden="true">
						<div class="modal-dialog modal-md">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="uploadDocTitle">Upload New
										Document</h4>
								</div>

								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12 col-md-12 col-xs-12">
											<s:form id="upload" method="post" action="uploadDocumentsEmr"
												enctype="multipart/form-data" theme="simple">
												<s:hidden name="isvideo" id="isvideo"></s:hidden>
												<s:hidden name="clientname"></s:hidden>

												<div class="form-group">
													<s:select cssClass="form-control fbackwhi" headerKey="0"
														headerValue="Select Type" name="doctType" id="doctType"
														onchange="setemruploaddocAjax(this.value,'doctype')"
														list="{'GP Doc','TP Doc','Medical Record','Consultant Report','Assessment Report','Investigation','Admission Form','Discharge Form','Nursing','Others'}"
														cssStyle="margin-bottom: 10px !important;"></s:select>
												</div>

												<div class="form-group">
													<s:textarea cssClass="form-control fbackwhi"
														onblur="setemruploaddocAjax(this.value,'disc')"
														placeholder="Enter Document Note" rows="3"
														name="documentDesc" id="documentDesc"></s:textarea>
												</div>


												<span id="filename" style="color: white"></span>


												<div id="drop" style="background-color: #efefef;">
													Drop Here <a>Browse</a>
													<s:file name="files" theme="simple">
													</s:file>
												</div>

												<ul class="popmodals123">
													<!-- The file uploads will be shown here -->
												</ul>

											</s:form>
										</div>

									</div>




								</div>
								<div class="modal-footer">
									<s:form action="addDocumentsEmr" method="post" theme="simple">
										<s:hidden id="clientname" value="%{clientname}"
											name="clientname"></s:hidden>
										<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
										<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
										<s:hidden id="editDoctId" name="editDoctId"></s:hidden>
										<s:hidden id="docapmtId" name="apmtId" />
										<s:hidden id="ipdopdemr" value="0" name="ipdopdemr"></s:hidden>
										<!-- <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button> -->

										<button type="submit" class="btn btn-primary start">Save</button>
									</s:form>

								</div>
							</div>
						</div>
					</div>
					<!--/Upload Model-->


					<!-- add prescription Modal -->
					<div class="modal fade notranslate" id="priscriptionpopup"
						tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup"
						aria-hidden="true" data-keyboard="false" data-backdrop="static"
						style="background-color: rgba(0, 0, 0, 0.72); overflow: scroll !important;">
						<div class="modal-dialog modal-lg">
							<div class="modal-content" style="">
								<div class="modal-header bg-primary">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h5 class="modal-title" id="">
										Create Prescription For <b class="pname"
											id="priscmyModalLabel">NAME: </b>
									</h5>
								</div>
								<div class="modal-body">




									<s:include value="/diarymanagement/pages/addpriscription.jsp"></s:include>
								</div>
								<div class="modal-footer">

									<button type="button" class="btn btn-primary hidden"
										onclick="saveTemplae(0)">Save Template</button>
									<button type="button" class="btn btn-primary"
										onclick="addEmrPrisc()" id="prescs_save_btn">Save</button>
									<%-- <%if(loginInfo.isPrisc_savenprint()){ %>
						 <button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)" id="prescs_save_print_btn">Save & Print</button>
					<%} %> --%>
									<button type="button" class="btn btn-primary hidden"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>


					<!-- edit mdcine name popup -->
					<div class="modal fade" id="edtmdcinenamepopupid" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Edit Medicine
										Name</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-3">
											<label>Medicine Name</label>
										</div>
										<div class="col-lg-9">

											<input type="text" id="priscmdcineedit"
												name="priscmdcineedit" class="form-control showToolTip"
												onblur="setPuresevaExistingClientData()"
												placeholder="Enter Medicine Name"
												title="Enter Medicine Name" data-toggle="tooltip " />
										</div>
									</div>
									<br>

									<%
										if (loginInfo.getOutoprisc() == 1) {
									%>
									<div class="row">
										<div class="col-lg-3">
											<label>Search Medicine</label>
										</div>
										<div class="col-lg-9">

											<s:select cssClass="form-control showToolTip chosen-select"
												name="mdicinenamesrch" id="mdicinenamesrch"
												onchange="getsrchdmdicinename(this.value)"
												list="medicineList" tabindex="1" listKey="id"
												listValue="genericname" headerKey="0"
												headerValue="Select Medicine">
											</s:select>
										</div>
									</div>
									<br>
									<%
										}
									%>


								</div>
								<div class="modal-footer">

									<button type="button" class="btn btn-primary"
										onclick="updatemdcinename();" data-dismiss="modal">Save</button>
									<!-- <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button> -->
								</div>
							</div>
						</div>
					</div>


					<!-- add invesgtigation Modal -->
					<div class="modal fade notranslate" id="investigationpopup"
						tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup"
						aria-hidden="true" data-keyboard="false" data-backdrop="static"
						style="background-color: rgba(0, 0, 0, 0.72); overflow: scroll !important;">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header bg-primary">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h5 class="modal-title" id="">
										Create Investigation For <b class="pname"
											id="invstcmyModalLabel">NAME: </b>
									</h5>
								</div>
								<div class="modal-body" style="padding: 0px;">


									<%-- <%@ include file="/emr/pages/addInvestigation.jsp"%> --%>
									<jsp:include page="/emr/pages/addInvestigation.jsp" /> 

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary"
										onclick="insertInvestigation(0)">Save</button>
									<%-- <%if(loginInfo.isInvest_savenprint()){ %>
						 <button type="button" class="btn btn-primary"
						onclick="saveIpdInvestigation(1)">Save & Print</button> 
						<%} %> --%>
									<button type="button" class="btn btn-primary hidden"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>




					<!--Add Medical Record Model-->
					<div class="modal fade" id="addmedicalrecord" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
						style="z-index: 9999">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Add Medical
										Record</h4>
								</div>
								<s:form id="addMedicalRecords" action="addMedicalRecordEmr"
									theme="simple">
									<div class="modal-body" style="min-height: 450px;">
										<s:hidden id="clientname" value="%{clientname}"
											name="clientname"></s:hidden>
										<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
										<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
										<s:hidden id="addmediclapmtId" name="apmtId" />
										<div class="">
											<table class="table" id="medicalHistoryTable">

												<tbody>
													<tr>

														<td><s:select
																id="medicalHistory[0].medicalRecordType"
																name="medicalHistory[0].medicalRecordType"
																list="medicalRecordTypeList" listKey="medicalRecordType"
																listValue="medicalRecordType"
																cssClass="form-control showToolTip" theme="simple"
																headerKey="0" headerValue="Select Type"
																onchange="setNew(this.value)" /> <input type="text"
															name="medicalHistory[0].medicalRecordTypeOther"
															id="medicalHistory[0].medicalRecordTypeOther"
															class="form-control showToolTip medicalRecordTypeOther"
															data-toggle="tooltip" placeholder="Enter New Type"
															style="display: none"
															onchange="insertMedicalRecordType(this.value)"></td>

														<td><textarea class="form-control" rows="3"
																type="text" name="medicalHistory[0].medicalHistoryNotes"
																id="medicalHistory[0].medicalHistoryNotes"
																class="form-control showToolTip medicalHistoryNotes"
																data-toggle="tooltip" placeholder="Enter Document Note"></textarea>
														</td>

													</tr>
												</tbody>
											</table>
										</div>


										<div class="row" style="margin-left: 1px;">
											<div class="col-lg-12">
												<button type="button" class="btn btn-primary"
													onclick="addMoreMedicalRecords('medicalHistoryTable')">
													<i class="fa fa-plus"></i> Add More..
												</button>

											</div>
										</div>


									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary"
											data-dismiss="modal" onclick="saveMedicalRecords()">
											<i class="fa fa-save"></i> Save
										</button>
									</div>
								</s:form>
							</div>
						</div>
					</div>

					<!--Edit Medical Record Model-->
					<div class="modal fade" id="editmedicalrecord" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
						style="z-index: 999999">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Edit Medical
										Record</h4>
								</div>
								<s:form id="editMedicalRecords" action="editMedicalRecordEmr"
									theme="simple">
									<div class="modal-body" style="min-height: 450px;">
										<s:hidden id="clientname" value="%{clientname}"
											name="clientname"></s:hidden>
										<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
										<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
										<s:hidden id="editmediclapmtId" name="apmtId" />

										<div class="">
											<table class="table" id="medicalHistoryTable">

												<tbody>
													<tr>

														<td><s:select id="medicalRecordType"
																name="medicalRecordType" list="medicalRecordTypeList"
																listKey="medicalRecordType"
																listValue="medicalRecordType"
																cssClass="form-control showToolTip" theme="simple"
																headerKey="0" headerValue="Select Type" /></td>

														<td><input type="text" name="medicalHistoryNotes"
															id="medicalHistoryNotes" class="form-control showToolTip"
															data-toggle="tooltip" placeholder="Enter Document Note"></td>
														<s:hidden name="medicalRecordId" id="medicalRecordId" />
													</tr>
												</tbody>
											</table>
										</div>




									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default hidden"
											data-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary"
											data-dismiss="modal" onclick="updateMedicalRecords()">
											<i class="fa fa-save"></i> Save
										</button>
									</div>
								</s:form>
							</div>
						</div>
					</div>




					<!--Play Video Model-->
					<div class="modal fade" id="videoClipPlay" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										<i class="fa fa-plus"></i>Play Video Clip
									</h4>
								</div>

								<div class="modal-body">


									<video id='videoPlayer' width="500" height="450">
										<source id='mp4Source' src="movie.mp4" type="video/mp4">
										<source id='oggSource' src="movie.ogg" type="video/ogg">
										Your browser does not support the video tag.
									</video>
								</div>

								<div class="modal-footer">

									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>



					<script src="common/chosen_v1.1.0/chosen.jquery.js"
						type="text/javascript"></script>
					<script src="common/chosen_v1.1.0/docsupport/prism.js"
						type="text/javascript" charset="utf-8"></script>
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

					<!--/Add Medical Record Model-->



					<!--Share Model-->

					<div class="modal fade" id="sharepopuo" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header bg-primary">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Share EMR</h4>
								</div>
								<div class="modal-body">
									<div class="col-lg-12 col-md-12">

										<div>
											<div class="form-group">
												<label for="exampleInputEmail1">You Want to Share</label> <input
													type="text" id="pureemail" name="pureemail"
													class="form-control"
													onblur="setPuresevaExistingClientData()"
													placeholder="Enter Email" data-toggle="tooltip " />
											</div>
											<div class="form-group">
												<label for="exampleInputPassword1">First Name</label> <input
													type="text" id="purefname" name="purefname"
													class="form-control" placeholder="Enter First Name"
													data-toggle="tooltip" />
											</div>
											<div class="form-group">
												<label for="exampleInputPassword1">Last Name</label> <input
													type="text" id="purelname" name="purelname"
													class="form-control" placeholder="Enter Last name"
													data-toggle="tooltip" />
											</div>
											<div class="form-group">
												<label for="exampleInputPassword1">Mobile Number</label> <input
													type="text" id="puremob" name="puremob"
													class="form-control" placeholder="Enter Mobile Number"
													data-toggle="tooltip" />
											</div>



										</div>


									</div>


									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											onclick="savesharedata()">Save</button>
										<button type="button" class="btn btn-primary hidden"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
					</div>


					<!--Filter Model-->
					<s:form action="getPatientRecordEmr" theme="simple">
						<div class="modal fade" id="docfilterpopup" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-md">
								<div class="modal-content modelmd">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="">Filter Document</h4>
									</div>

									<div class="modal-body">



										<s:select cssClass="form-control fbackwhi" headerKey="0"
											headerValue="All" name="filterdoctType" id="filterdoctType"
											onchange="setemruploaddocAjax(this.value,'doctype')"
											list="{'GP Doc','TP Doc','Medical Record','Consultant Report','Assessment Report','Investigation','Admission Form','Discharge Form','Others'}"
											cssStyle="margin-bottom: 10px !important;"></s:select>
										<br />






									</div>
									<div class="modal-footer">

										<s:hidden id="fclientname" value="%{clientname}"
											name="clientname"></s:hidden>
										<s:hidden id="fdiaryUser" value="%{diaryUser}"
											name="diaryUser"></s:hidden>
										<s:hidden id="fcondition" value="%{condition}"
											name="condition"></s:hidden>
										<s:hidden id="feditDoctId" name="editDoctId"></s:hidden>
										<s:hidden id="fdocapmtId" name="apmtId" />
										<button type="button" class="btn btn-primary"
											data-dismiss="modal">Close</button>

										<button type="submit" class="btn btn-primary start">
											<i class="glyphicon glyphicon-upload"></i> <span>GO</span>
										</button>


									</div>
								</div>
							</div>
						</div>
					</s:form>


					<!--shareduser Model-->
					<s:form action="linkEmr" id="" theme="simple">

						<s:hidden id="fclientname" value="%{clientname}" name="clientname"></s:hidden>
						<s:hidden id="fdiaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
						<s:hidden id="fcondition" value="%{condition}" name="condition"></s:hidden>
						<s:hidden id="feditDoctId" name="editDoctId"></s:hidden>
						<s:hidden id="fdocapmtId" name="apmtId" />
						<div class="modal fade" id="shareduserpopup" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
							data-keyboard="false" data-backdrop="static">
							<div class="modal-dialog modal-sm">
								<div class="modal-content modelmd">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											style="display: none;" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="">Shared EMR</h4>
									</div>

									<div class="modal-body">



										<div class="row">
											<div class="col-lg-3">
												<label>Enter Mob:</label>
											</div>
											<div class="col-lg-9">

												<input type="text" id="shmob" name="shmob"
													class="form-control showToolTip"
													placeholder="Enter mobile number"
													title="Enter mobile number" data-toggle="tooltip" />
											</div>
										</div>
										<br />






									</div>
									<div class="modal-footer">



										<button onclick="checkSharedUser()" type="button"
											class="btn btn-primary start">
											<i class="glyphicon glyphicon-upload"></i> <span>GO</span>
										</button>


									</div>
								</div>
							</div>
						</div>
					</s:form>


					<!--shareduser OTP-->
					<s:form action="linkEmr" id="shuserfrm" theme="simple">
						<s:hidden name="sharedmob" id="sharedmob" />
						<s:hidden id="fclientname" value="%{clientname}" name="clientname"></s:hidden>
						<s:hidden id="fdiaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
						<s:hidden id="fcondition" value="%{condition}" name="condition"></s:hidden>
						<s:hidden id="feditDoctId" name="editDoctId"></s:hidden>
						<s:hidden id="fdocapmtId" name="apmtId" />
						<div class="modal fade" id="otpemroppup" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
							data-keyboard="false" data-backdrop="static">
							<div class="modal-dialog modal-md">
								<div class="modal-content modelmd">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											style="display: none;" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="">Enter OTP sent on your
											mobile</h4>
									</div>

									<div class="modal-body">



										<div class="row">
											<div class="col-lg-3">
												<label>Enter OTP:</label>
											</div>
											<div class="col-lg-9">

												<input type="text" id="emrotp" name="emrotp"
													class="form-control showToolTip" placeholder="Enter OTP"
													title="Enter mobile OTP" data-toggle="tooltip" />
											</div>
										</div>
										<br />






									</div>
									<div class="modal-footer">



										<button onclick="checkemrotp()" type="button"
											class="btn btn-primary start">
											<i class="glyphicon glyphicon-upload"></i> <span>GO</span>
										</button>


									</div>
								</div>
							</div>
						</div>
					</s:form>


					<!--Confidential Model-->
					<div class="modal fade" id="confidentialpopup" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="">Confidential EMR</h4>
								</div>

								<div class="modal-body">

									<div class="col-lg-12 col-md-12">
										<div>
											<div class="form-group">
												<label for="exampleInputEmail1">Enter Password</label> <input
													type="text" id="confpassd" name="confpassd"
													class="form-control" placeholder="Enter Password"
													data-toggle="tooltip" />
											</div>

										</div>
									</div>



								</div>
								<div class="modal-footer">



									<button onclick="saveConfidentialPassword()" type="button"
										class="btn btn-primary start">
										<i class="glyphicon glyphicon-upload"></i> <span>Save</span>
									</button>


								</div>
							</div>
						</div>
					</div>


					<!--Confpass Model-->
					<s:form action="confsvdEmr" id="conffrm" theme="simple">
						<s:hidden name="confdentialpass" id="confdentialpass" />
						<s:hidden name="checkconfidential" id="checkconfidential" />
						<s:hidden id="fclientname" value="%{clientname}" name="clientname"></s:hidden>
						<s:hidden id="fdiaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
						<s:hidden id="fcondition" value="%{condition}" name="condition"></s:hidden>
						<s:hidden id="feditDoctId" name="editDoctId"></s:hidden>
						<s:hidden id="fdocapmtId" name="apmtId" />
						<div class="modal fade" id="cofpasspopup" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
							data-keyboard="false" data-backdrop="static"
							style="background-color: #555;">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											style="display: none;" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="">Enter Password</h4>
									</div>

									<div class="modal-body">

										<div class="col-lg-12 col-md-12">
											<div>
												<div class="form-group">
													<label for="exampleInputEmail1">Enter Password</label> <input
														type="password" id="confvalpassd" name="confvalpassd"
														class="form-control" placeholder="Enter Password"
														title="Enter Password" data-toggle="tooltip" />
												</div>

											</div>
										</div>

									</div>
									<div class="modal-footer">
										<button onclick="checkConfidentialPassword()" type="button"
											class="btn btn-primary start">
											<span>GO</span>
										</button>


									</div>
								</div>
							</div>
						</div>
					</s:form>

					<!-- Treatment Details Modal -->
					<div class="modal fade modal-draggable" id="treatment_details"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Treatment
										Details</h4>
								</div>
								<div class="modal-body popmodals">

									<s:if test="treatmentEpisodeList.size!=0">
										<s:iterator value="treatmentEpisodeList" status="rowstatus">

											<p class="tbp">
												<s:property value="usedSession" />
												session
												<s:property value="treatmentEpisodeName" />
												<s:if test="trtmentStatus==0">
														 		(Ongoing)
														 	</s:if>
												<s:else>
														 		(Discharged)
														 	</s:else>
											</p>
											<s:if test="appointmnetList.size!=0">
												<s:iterator value="appointmnetList" status="rowstatus">
													<input type="hidden" id="hdn<s:property value = "id"/>"
														name="hdn<s:property value = "id"/>"
														value="<s:property value = "treatmentEpisodeName"/>#<s:property value = "apmttypetext"/> - <s:property value ="commencing"/>#<s:property value="trtmentStatus"/>#<s:property value="dischrgeOutcomes"/>#<s:property value="dischargeStatus"/>#<s:property value="dischargedate"/>#<s:property value="hour"/>#<s:property value="minute"/>">
													<s:if test="chkConsultationNote == 1">
														<s:if test="treatmentApmtCount == 1 && apmtCount == 1">
															<div class="row hidden" style="line-height: 8px;">
																<div class="col-lg-1 col-md-1 col-xs-1 roadai"
																	style="margin-left: 20px;">

																	<input type="radio" name="apmtChk" checked="checked"
																		id="<s:property value = "id"/>"
																		onclick="filterConsultation('<s:property value = "id"/>')">
																	<s:form action="filterConsultationEmr"
																		id="filterConsultation_%{id}">
																		<s:hidden id="id" value="%{id}" name="appointmentid"></s:hidden>
																		<s:hidden id="clientname" value="%{clientname}"
																			name="clientname"></s:hidden>
																		<s:hidden id="diaryUser" value="%{diaryUserId}"
																			name="diaryUser"></s:hidden>
																		<s:hidden id="condition" value="%{condition}"
																			name="condition"></s:hidden>
																	</s:form>
																</div>
																<div class="col-lg-8 col-md-8 col-xs-10">
																	<p>
																		<a href="#" data-target="#addConsultationNote"
																			onclick="openConsultationNote('<s:property value = "id"/>')"
																			style="color: green; font-size: 9px; margin-left: -12px;">
																			<s:property value="apmttypetext" /> - <s:property
																				value="commencing" />
																		</a>
																	</p>

																</div>
																<div class="col-lg-2 col-md-2 col-xs-2">
																	<s:form theme="simple"
																		action="deleteAllConsultationNoteEmr"
																		id="deleteAllConsultationNote_%{id}">
																		<s:hidden id="id" value="%{id}" name="appointmentid"></s:hidden>
																		<s:hidden id="clientname" value="%{clientname}"
																			name="clientname"></s:hidden>
																		<s:hidden id="diaryUser" value="%{diaryUserId}"
																			name="diaryUser"></s:hidden>
																		<s:hidden id="condition" value="%{condition}"
																			name="condition"></s:hidden>
																		<s:hidden id="delallapmtId" name="apmtId"
																			value="%{id}" />

																	</s:form>
																</div>

															</div>

														</s:if>
														<s:else>
															<div class="row hidden" style="line-height: 8px;">
																<div class="col-lg-1 col-md-1 col-xs-1 roadai"
																	style="margin-left: 20px;">
																	<input type="radio" name="apmtChk"
																		id="<s:property value = "id"/>"
																		onclick="filterConsultation('<s:property value = "id"/>')">
																	<s:form action="filterConsultationEmr"
																		id="filterConsultation_%{id}">
																		<s:hidden id="id" value="%{id}" name="appointmentid"></s:hidden>
																		<s:hidden id="clientname" value="%{clientname}"
																			name="clientname"></s:hidden>
																		<s:hidden id="diaryUser" value="%{diaryUserId}"
																			name="diaryUser"></s:hidden>
																		<s:hidden id="condition" value="%{condition}"
																			name="condition"></s:hidden>
																	</s:form>
																</div>
																<div class="col-lg-8 col-md-8 col-xs-10">
																	<p>
																		<a href="#" data-toggle="modal"
																			data-target="#addConsultationNote"
																			onclick="openConsultationNote('<s:property value = "id"/>')"
																			style="color: green; font-size: 9px; margin-left: -12px;">
																			<s:property value="apmttypetext" /> - <s:property
																				value="commencing" />
																		</a>
																	</p>

																</div>
																<div class="col-lg-2 col-md-2 col-xs-2">
																	<s:form theme="simple"
																		action="deleteAllConsultationNoteEmr"
																		id="deleteAllConsultationNote_%{id}">
																		<s:hidden id="id" value="%{id}" name="appointmentid"></s:hidden>
																		<s:hidden id="clientname" value="%{clientname}"
																			name="clientname"></s:hidden>
																		<s:hidden id="diaryUser" value="%{diaryUserId}"
																			name="diaryUser"></s:hidden>
																		<s:hidden id="condition" value="%{condition}"
																			name="condition"></s:hidden>
																		<s:hidden id="delallapmtId" name="apmtId"
																			value="%{id}" />

																	</s:form>
																</div>
															</div>
														</s:else>


													</s:if>
													<s:else>
														<s:if test="treatmentApmtCount == 1 && apmtCount == 1">
															<div class="row hidden" style="line-height: 8px;">
																<div class="col-md-1 col-xs-1 roadai"
																	style="margin-left: 20px;">
																	<input type="radio" name="apmtChk"
																		id="<s:property value = "id"/>" checked="checked"
																		onclick="filterConsultation('<s:property value = "id"/>')">
																	<s:form action="filterConsultationEmr"
																		id="filterConsultation_%{id}">
																		<s:hidden id="id" value="%{id}" name="appointmentid"></s:hidden>
																		<s:hidden id="clientname" value="%{clientname}"
																			name="clientname"></s:hidden>
																		<s:hidden id="diaryUser" value="%{diaryUserId}"
																			name="diaryUser"></s:hidden>
																		<s:hidden id="condition" value="%{condition}"
																			name="condition"></s:hidden>
																	</s:form>
																</div>
																<div class="col-md-10 col-xs-10">
																	<p>
																		<a href="#" data-toggle="modal"
																			data-target="#addConsultationNote"
																			onclick="openConsultationNote('<s:property value = "id"/>')"
																			style="color: red; font-size: 9px; margin-left: -12px;">
																			<s:property value="apmttypetext" /> - <s:property
																				value="commencing" />
																		</a>
																	</p>

																</div>
															</div>


														</s:if>
														<s:else>
															<div class="row hidden" style="line-height: 8px;">
																<div class="col-md-1 col-xs-1 roadai"
																	style="margin-left: 20px;">
																	<input type="radio" name="apmtChk"
																		id="<s:property value = "id"/>"
																		onclick="filterConsultation('<s:property value = "id"/>')">
																	<s:form action="filterConsultationEmr"
																		id="filterConsultation_%{id}">
																		<s:hidden id="id" value="%{id}" name="appointmentid"></s:hidden>
																		<s:hidden id="clientname" value="%{clientname}"
																			name="clientname"></s:hidden>
																		<s:hidden id="diaryUser" value="%{diaryUserId}"
																			name="diaryUser"></s:hidden>
																		<s:hidden id="condition" value="%{condition}"
																			name="condition"></s:hidden>
																	</s:form>
																</div>
																<div class="col-md-10 col-xs-10">
																	<p>
																		<a href="#" data-toggle="modal"
																			data-target="#addConsultationNote"
																			onclick="openConsultationNote('<s:property value = "id"/>')"
																			style="color: red; font-size: 9px; margin-left: -12px;">
																			<s:property value="apmttypetext" /> - <s:property
																				value="commencing" />
																		</a>
																	</p>

																</div>
															</div>

														</s:else>
													</s:else>

												</s:iterator>
											</s:if>

										</s:iterator>
									</s:if>


								</div>
								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>


					<!-- Documents Details Modal -->
					<div class="modal fade modal-draggable" id="document_details"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										Documents Repository <a href="#" class="hidden"
											onclick="showdocfilter()" data-toggle="modal"
											data-target="#filter"><i class="fa fa-filter"
											aria-hidden="true" style="color: orange;"></i></a>

									</h4>
								</div>
								<div class="modal-body popmodals">
									<div class="col-lg-12 col-md-12 tofilter">
										<div class="col-lg-6 col-md-6" style="padding-left: 0px;">
											<form class="form-inline">
												<div class="form-group">
													<s:select cssClass="form-control chosen-select"
														headerKey="0" headerValue="Select Type" name="doctType"
														id="searchdoc" onchange="filtertype(this.value)"
														list="{'GP Doc','TP Doc','Medical Record','Consultant Report','Assessment Report','Investigation','Admission Form','Discharge Form','Nursing','All','Optional Form','Declaration Form','OT Form','Gynic Form'}"
														cssStyle="margin-bottom: 10px !important;"></s:select>
												</div>
												<!--
			  <button type="submit" class="btn btn-default hidden">Search</button>
			-->
											</form>
										</div>
										<div class=" col-lg-6 col-md-6" style="padding-right: 0px;">
											<a href="#" type="button" class="btn btn-primary hidden-xs"
												data-toggle="modal" data-target="#uploaddoc"
												onclick="clearUplaodDocumentPopup()" Title="Upload"
												style="float: right">Upload</a>
										</div>
									</div>
									<div>
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>No</th>

													<th>Document Type/Name</th>
													<th class="text-center">Date</th>
													<th class="text-center">Print/View</th>
													<th class="text-center">Edit/Delete</th>
												</tr>
											</thead>
											<%
												int count = 1;
											%>
											<tbody id="docslist" class="table table-bordered">

												<s:iterator value="docList" status="rowstatus">

													<tr>
														<th scope="row"><%=count%>.</th>

														<td>
															<p class="font11">
																<s:property value="doctType" />
																/
																<s:property value="fileName" />
															</p>
														</td>
														<td>
															<p class="marraig">
																<%-- <a href="#"	class="font11"><s:property value="lastModified" /> </a> --%>
																<a
																	href="downloadDocEmr?filename=<s:property value="fileName"/>&id=<s:property value="id"/>"
																	title="Download" class="font11"> <s:property
																		value="lastModified" />
																</a>
															</p>



														</td>
														<td class="text-center">
															<p class="docuto">
																<s:property value="description" />
																&nbsp; <a class="thumbnail" href="#" data-image-id="" onclick="openBlankPopup('liveData/document/<s:property value="fileName"/>')"
																	data-toggle="modal" data-title=""
																	data-image="liveData/document/<s:property value="fileName"/>"
																	data-target=""> <img
																	class="img-thumbnail" style="height: 50px; width: 50px"
																	src="liveData/document/<s:property value="fileName"/>"
																	alt="Another alt text">
																</a>

															</p>
														</td>

														<td class="text-center">&nbsp;&nbsp; <s:form
																theme="simple" id="deleteDocuments_%{id}"
																action="deleteDocumentsEmr">
																<s:hidden name="deleteDoctId" value="%{id}"
																	id="deleteDoctId" />
																<s:hidden id="clientname" value="%{clientname}"
																	name="clientname"></s:hidden>
																<s:hidden id="diaryUser" value="%{diaryUser}"
																	name="diaryUser"></s:hidden>
																<s:hidden id="condition" value="%{condition}"
																	name="condition"></s:hidden>

																<%
																	if (!loginInfo.getLoginType().equals("shareemr")) {
																%>
																<a href="#"
																	onclick="deleteDocuments('<s:property value = "id"/>')"
																	title="Delete" class=""><i class="fa fa-trash-o"></i></a>
																<%
																	} else {
																%>
																<i class="fa fa-trash-o"></i>
																<%
																	}
																%>
															</s:form>
														</td>
													</tr>
													<%
														count++;
													%>
												</s:iterator>

											</tbody>

											<tbody id="admissionlist" class="table table-bordered">



												<!-- admission forms -->

												<s:iterator value="addmissionList">

													<tr>
														<th scope="row"><%=count%>.</th>

														<td><s:if test="casualtyipd==0">
																<p class="font11">IPD Form</p>
															</s:if> <s:elseif test="casualtyipd==2">
																<p class="font11">Daycare Form</p>
															</s:elseif> <s:else>
																<p class="font11">Casualty Form</p>
															</s:else></td>
														<td>
															<p>

																<%
																	if (!loginInfo.getLoginType().equals("shareemr")) {
																%>
																<s:if test="casualtyipd==0">
																	<a href="#"
																		onclick="openEmrPopup('editCommonnew?selectedid=<s:property value = "id"/>&action=0')"
																		class="font11"><s:property value="admissiondate" />
																	</a>
																</s:if>
																<s:elseif test="casualtyipd==2">
																	<a href="#"
																		onclick="openEmrPopup('editCommonnew?selectedid=<s:property value = "id"/>&action=2')"
																		class="font11"><s:property value="admissiondate" />
																	</a>
																</s:elseif>
																<s:else>
																	<a href="#"
																		onclick="openEmrPopup('editCommonnew?selectedid=<s:property value = "id"/>&action=1')"
																		class="font11"><s:property value="admissiondate" />
																	</a>
																</s:else>

																<%
																	} else {
																%>
																<a href="#" class="font11"><s:property
																		value="admissiondate" /> </a>
																<%
																	}
																%>
															</p>



														</td>
														<!-- <td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td> -->
														<td class="text-center"><a href="#"
															onclick="openEmrPopup('printCommonnew?selectedid=<s:property value="id"/>')"
															title="Print" class="assesprint"><i
																class="fa fa-print"></i></a></td>
														<td class="text-center"><a href="#" title="Edit"
															class=""
															onclick="openEmrPopup('editCommonnew?selectedid=<s:property value = "id"/>&action=1')">
																<i class="fa fa-edit"></i>
														</a></td>
													</tr>
													<%
														count++;
													%>
												</s:iterator>
											</tbody>

											<!-- Gynic Form List by jitu -->

											<tbody id="gynicFormList" class="table table-bordered">

												<%
													if (loginInfo.isVermanh()) {
												%>
												<s:iterator value="gynicFormList">

													<tr>
														<th scope="row"><%=count%></th>

														<td>
															<p>
																<a href="#"
																	onclick="openEmrPopup('editnewgynicformCommonnew?selectedid=<s:property value = "id"/>&action=0')"
																	class="font11"><s:property value="commencing" /> </a>
															</p>


														</td>
														<td>Gynaecology Form</td>

														<!-- <td></td> -->
														<td class="text-center"><a href="#"
															onclick="openEmrPopup('printnewgynicformCommonnew?selectedid=<s:property value="id"/>')"
															title="Print" class="assesprint"><i
																class="fa fa-print"></i></a></td>
														<td class="text-center"></td>
													</tr>
													<%
														count++;
													%>
												</s:iterator>
												<%
													} else {
												%>
												<s:iterator value="gynicFormList">

													<tr>
														<th scope="row"><%=count%></th>

														<td>
															<p>

																<%
																	if (!loginInfo.getLoginType().equals("shareemr")) {
																%>
																<a href="#"
																	onclick="openEmrPopup('editgynicformIpd?selectedid=<s:property value = "id"/>&action=0')"
																	class="font11"><s:property value="commencing" /> </a>
																<%
																	} else {
																%>
																<a href="#"
																	onclick="openEmrPopup('editgynicformIpd?selectedid=<s:property value = "id"/>&action=0')"
																	class="font11"><s:property value="commencing" /> </a>
																<%
																	}
																%>
															</p>


														</td>
														<td><s:if test="formtype==1">
																<p class="font11">Obstetrics Form</p>

															</s:if> <s:elseif test="formtype==2">
																<p class="font11">Gynaecology Form</p>
															</s:elseif> <s:else>
																<p class="font11">Infertility Form</p>
															</s:else></td>

														<td class="text-center"><a href="#"
															onclick="openEmrPopup('printgynicformIpd?selectedid=<s:property value="id"/>')"
															title="Print" class="assesprint"><i
																class="fa fa-print"></i></a></td>
														<td class="text-center"><a href="#" title="Delete"
															class="text-danger"><i class="fa fa-trash-o"></i></a></td>
													</tr>
													<%
														count++;
													%>
												</s:iterator>
												<%
													}
												%>

											</tbody>



											<tbody id="dischargelist" class="table table-bordered">



												<!-- Discharge forms -->

												<s:iterator value="ipdsdischargeList">

													<tr>
														<th scope="row"><%=count%>.</th>

														<td>
															<p class="font11">Discharge Form</p>
														</td>
														<td>
															<p>
																<%
																	if (!loginInfo.getLoginType().equals("shareemr")) {
																%>
																<a href="#"
																	onclick="openEmrPopup('dischargeCommonnew?selectedid=<s:property value = "id"/>&clientid=<s:property value="clientid"/>')"
																	class="font11"><s:property value="admissiondate" />
																</a>
																<%
																	} else {
																%>
																<a href="#" class="font11"><s:property
																		value="admissiondate" /> </a>
																<%
																	}
																%>
															</p>

														</td>
														<!-- <td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td> -->
														<td class="text-center"><a href="#"
															onclick="openEmrPopup('printdischargeCommonnew?selectedid=<s:property value="id"/>')"
															title="Print" class="assesprint"><i
																class="fa fa-print"></i></a></td>
														<td class="text-center"><a href="#" title="Delete"
															class="text-danger">Can't Delete</a></td>
													</tr>
													<%
														count++;
													%>
												</s:iterator>
											</tbody>
											<tbody id="assessmentlist" class="table table-bordered">



												<!-- Assesment forms -->

												<s:iterator value="assessmentFormsList" status="rowstatus">
													<s:if test="type==1">
														<tr>
															<th scope="row"><%=count%>.</th>

															<td>
																<p class="font11">Assesment Form</p>
															</td>
															<td>
																<p class="font11">
																	<s:property value="lastmodified" />
																</p>

															</td>
															<td class="text-center"><a href="#"
																onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2&action=print&formtype=<s:property value="formtype"/>')"
																title="Print" class="assesprint"><i
																	class="fa fa-print"></i></a></td>

															<td class="text-center"><a href="#" title="Delete"
																class="text-danger"><i class="fa fa-trash-o"></i></a> <a
																href="#"
																onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2&action=dtr&formtype=<s:property value="formtype"/>')">
																	<i class="fa fa-edit" aria-hidden="true"></i>
															</a></td>
														</tr>
													</s:if>

													<s:else>
														<tr>
															<th scope="row"><%=count%>.</th>

															<td>
																<p class="font11">Assesment Form</p>
															</td>
															<%-- <td>
											<p>
												<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
												<a href="#"
													onclick="openEmrPopup('editListAssessmentForm?id=<s:property value = "id"/>&actionType=2&formtype=<s:property value="formtype"/>')"
													class="font11"> <s:property value="templateName" />
												</a>
												<%}else{ %>
												<a href="#" class="font11"> <s:property
														value="templateName" />
												</a>
												<%} %>
											</p>
										</td> --%>
															<td>
																<p class="font11">
																	<s:property value="lastmodified" />
																</p>

															</td>

															<td class="text-center"><a href="#"
																onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=1&action=print&formtype=<s:property value="formtype"/>')"
																title="Print" class="assesprint"><i
																	class="fa fa-print"></i></a></td>

															<%-- <td class="text-center"><a
											href="delassesmentEmr?selectedid=<s:property value="id"/>&diaryUser=<s:property value="diaryUser"/>&clientname=<s:property value="clientname"/>&condition=<s:property value="condition"/>"
											onclick="return confirmedDelete()" title="Delete"
											class="text-danger"><i class="fa fa-trash-o"></i></a></td> --%>
															<td><a href="#"
																onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2&action=dtr&formtype=<s:property value="formtype"/>')">
																	<i class="fa fa-edit" aria-hidden="true"></i>
															</a></td>
														</tr>
													</s:else>


													<%
														count++;
													%>
												</s:iterator>




											</tbody>

											<tbody id="optionformlist" class="table table-bordered">
												<!-- Discharge forms -->
												<s:iterator value="optionformlist">
													<tr>
														<th scope="row"><%=count%>.</th>

														<td>
															<p class="font11">Optional Form</p>
														</td>
														<td>
															<p>
																<a href="#"
																	onclick="openEmrPopup('editoptionformdetailsNotAvailableSlot?id=<s:property value="id"/>')"
																	class="font11"><s:property value="datetime" /> </a>
															</p>
														</td>
														<!-- <td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td> -->
														<td class="text-center"><a href="#"
															onclick="openEmrPopup('printoptionformNotAvailableSlot?id=<s:property value="id"/>')"
															title="Print" class="assesprint"><i
																class="fa fa-print"></i></a></td>
														<td class="text-center"><a href="#"
															onclick="openEmrPopup('editoptionformdetailsNotAvailableSlot?id=<s:property value="id"/>')"
															class="font11"><i class="fa fa-edit"></i></a></td>
													</tr>
													<%
														count++;
													%>
												</s:iterator>
											</tbody>

											<tbody id="declarationformlist" class="table table-bordered">
												<!-- Discharge forms -->
												<s:iterator value="declarationformlist">
													<tr>
														<th scope="row"><%=count%>.</th>

														<td>
															<p class="font11">Declaration Form</p>
														</td>
														<td>
															<p>
																<a href="#"
																	onclick="openEmrPopup('editdeclarationformdetailsClient?id=<s:property value="id"/>&clientId=<s:property value="clientId"/>')"
																	class="font11"><s:property value="lastModified" />
																</a>
															</p>
														</td>
														<!-- <td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td> -->
														<td class="text-center"><a href="#"
															onclick="openEmrPopup('printdeclarationformdetailsClient?id=<s:property value="id"/>&clientId=<s:property value="clientId"/>')"
															title="Print" class="assesprint"><i
																class="fa fa-print"></i></a></td>
														<td class="text-center"><a href="#"
															onclick="openEmrPopup('editdeclarationformdetailsClient?id=<s:property value="id"/>&clientId=<s:property value="clientId"/>')"
															class="font11"><i class="fa fa-edit"></i></a></td>
													</tr>
													<%
														count++;
													%>
												</s:iterator>
											</tbody>

											<tbody id="otformlistid" class="table table-bordered">
												<!-- Discharge forms -->
												<s:iterator value="otformlist">
													<tr>
														<th scope="row"><%=count%>.</th>

														<td>
															<p class="font11">OT Notes Form</p>
														</td>
														<td>
															<p>
																<a href="#"
																	onclick="openEmrPopup('otnotesNotAvailableSlot?apmtid=<s:property value="id"/>')"
																	class="font11"><s:property value="commencing" /> </a>
															</p>
														</td>
														<!-- <td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td> -->
														<td class="text-center"><a href="#"
															onclick="openEmrPopup('printotnotesNotAvailableSlot?id=<s:property value="id"/>')"
															title="Print" class="assesprint"><i
																class="fa fa-print"></i></a></td>
														<td class="text-center"><a href="#"
															onclick="openEmrPopup('otnotesNotAvailableSlot?apmtid=<s:property value="id"/>')"
															class="font11"><i class="fa fa-edit"></i> </a></td>
													</tr>
													<%
														count++;
													%>
												</s:iterator>
											</tbody>

										</table>
									</div>



								</div>
								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>




					<!-- Medical Records Modal -->
					<div class="modal fade modal-draggable" id="medical_records"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">History</h4>
								</div>
								<div class="modal-body popmodals">

									<div class="col-lg-12 col-md-12 tofilter">
										<div class="col-lg-6 col-md-6 hidden-xs hidden-sm"
											style="padding-left: 0px;">
											<form class="form-inline">
												<div class="form-group">
													<input type="email" class="form-control"
														id="exampleInputEmail2" placeholder="Search Documents">
												</div>
												<button type="submit" class="btn btn-default">Search</button>
											</form>
										</div>
										<div class=" col-lg-6 col-md-6" style="padding-right: 0px;">
											<a href="#" type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#addmedicalrecord"
												onclick="clearUplaodDocumentPopup()" Title="Add Record"
												style="float: right">Add Record</a>
										</div>
									</div>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>Sr.no</th>
												<th style="width: 12%;">Type</th>
												<th style="width: 15%;">Modified Date</th>
												<th>Document Note</th>
												<th>Edit</th>
												<th>Delete</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="medicalRecordsTypeList.size!=0">
												<s:iterator value="medicalRecordsTypeList"
													status="rowstatus">

													<s:if test="medicalRecordsList.size!=0">
														<%
															count = 1;
														%>
														<s:iterator value="medicalRecordsList" status="rowstatus">
															<tr>
																<th scope="row"><%=count%>.</th>
																<td>
																	<p class="font11">
																		<s:property value="medicalRecordType" />
																	</p>
																</td>
																<td>
																	<p class="font11" style="margin-bottom: -2px;">
																		<s:property value="lastModified" />
																	</p>
																</td>
																<td>
																	<p class="font11">
																		<s:property value="description" />
																		<a href="javascript:void(0)" data-toggle="modal"
																			data-target="#editmedicalrecord"
																			onclick="editMedicalRecords('<s:property value = "id"/>','<s:property value = "medicalRecordType"/>','<s:property value = "description"/>')"
																			title="read more">&nbsp;more</a>
																	</p>
																</td>
																<td class="text-center"><a
																	href="javascript:void(0)" data-toggle="modal"
																	data-target="#editmedicalrecord"
																	onclick="editMedicalRecords('<s:property value = "id"/>','<s:property value = "medicalRecordType"/>','<s:property value = "description"/>')"
																	title="Edit"><i class="fa fa-edit"></i></a></td>
																<td class="text-center"><s:form theme="simple"
																		id="deleteMedicalRecord_%{id}"
																		action="deleteMedicalRecordEmr">
																		<s:hidden name="deleteMedicalId" value="%{id}"
																			id="deleteMedicalId" />
																		<s:hidden id="clientname" value="%{clientname}"
																			name="clientname"></s:hidden>
																		<s:hidden id="diaryUser" value="%{diaryUser}"
																			name="diaryUser"></s:hidden>
																		<s:hidden id="condition" value="%{condition}"
																			name="condition"></s:hidden>
																		<s:hidden id="deletemediclapmtId" name="apmtId" />
																		<a href="#"
																			onclick="deleteMedicalRecord('<s:property value = "id"/>')"
																			title="Delete" class="text-danger"><i
																			class="fa fa-trash-o"></i></a>
																	</s:form></td>
															</tr>

															<%
																count++;
															%>

														</s:iterator>
													</s:if>

												</s:iterator>
											</s:if>
										</tbody>
									</table>







								</div>
								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>

					<!-- Others From Clinical notes -->
					<div class="modal fade modal-draggable" id="clinicalass"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="mma">Diet, Vital and Nursing
										Care</h4>
								</div>
								<div class="modal-body popmodals">

									<div class="panel-body" id="clinicaln"></div>


								</div>
								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>




					<!-- Prsicription Details Modal -->
					<div class="modal fade modal-draggable" id="presscription_details"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Medicine</h4>
								</div>
								<div class="modal-body popmodals">

									<div class="panel-body" id="emrprecrdiv"></div>


								</div>
								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>




					<!-- Dropdown Modal edit -->
					<s:form action="getPatientRecordEmr" id="saveemrfrm">
						<div class="modal fade modal-draggable" id="lok" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog modal-sm" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">Search</h4>
									</div>
									<div class="modal-body">
										<div class="">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
												style="margin-bottom: 15px;">
												<lable>Select User</lable>
												<%
													if (loginInfo.getUserType() == 4) {
												%>
												<s:if test="%{#userList != 'null'}">
													<s:select cssClass="form-control showToolTip chosen-select"
														id="diaryUser" name="diaryUser" list="userList"
														listKey="id" listValue="diaryUser" headerKey="0"
														theme="simple" headerValue="Select User"
														onchange="setClientsajax(this.value)" />
												</s:if>
												<%
													} else {
												%>

												<s:if test="%{#userList != 'null'}">
													<s:select cssClass="form-control showToolTip chosen-select"
														id="diaryUser" name="diaryUser" list="userList"
														listKey="id" listValue="diaryUser" headerKey="0"
														theme="simple" headerValue="Select User"
														onchange="setClientsajax(this.value)" />
												</s:if>
												<%
													}
												%>
											</div>
										</div>
										<div class="">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
												style="margin-bottom: 15px;">
												<lable>Select Patient</lable>
												<div id="emrclientdataajaxdiv">
													<s:if test="%{#clientList != 'null'}">
														<s:select
															cssClass="form-control showToolTip chosen-select disabled"
															id="clientname" name="clientname" list="clientList"
															listKey="id" listValue="clientName" headerKey="0"
															theme="simple" headerValue="Select Patient"
															onchange="setCondition(this.value)" />
													</s:if>
												</div>

											</div>
										</div>
										<div class="">
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
												style="margin-bottom: 15px;">
												<lable>Select Speciality</lable>
												<div id="conditionajaxdiv">
													<s:if test="%{#conditionList != 'null'}">
														<s:select
															cssClass="form-control showToolTip chosen-select"
															id="condition" name="condition" list="conditionList"
															listKey="id" listValue="treatmentType" headerKey="0"
															theme="simple" headerValue="Select Speciality"
															onchange="getPatientRecord()" />
													</s:if>
												</div>
											</div>

										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default hidden"
											data-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary hidden">Save
											changes</button>
									</div>
								</div>
							</div>
						</div>

					</s:form>

					<!-- Investigation Record Modal -->
					<div class="modal fade modal-draggable" id="investigation_details"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Investigation</h4>
								</div>
								<div class="modal-body popmodals">

									<div class="table-responsive">
										<div class="panel-body" id="emrinvstdiv"></div>
									</div>






									<div class="modal-footer hidden">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary">Save
											changes</button>
									</div>
								</div>
							</div>
						</div>
					</div>


					<!-- Patient Medial Modal -->
					<div class="modal fade modal-draggable" id="patient_media"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content" style="height: 500px">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Media</h4>
								</div>
								<div class="modal-body popmodals">

									<div class="col-lg-12 col-md-12 tofilter">
										<div class="col-lg-6 col-md-6 hidden-sm hidden-xs"
											style="padding-left: 0px;">
											<form class="form-inline">
												<div class="form-group">
													<input type="email" class="form-control"
														id="exampleInputEmail2" placeholder="Search Documents">
												</div>
												<button type="submit" class="btn btn-default">Search</button>
											</form>
										</div>
										<div class=" col-lg-6 col-md-6" style="padding-right: 0px;">
											<a href="" type="button" class="btn btn-primary"
												data-toggle="modal" onclick="uploadVideoPopup()"
												data-target="#" title="Upload New Video Clip"
												Title="Upload New Video Clip" style="float: right;">Upload
												Video</a> <a href="" type="button" class="btn btn-primary"
												data-toggle="modal" onclick="setEditor()"
												data-target="#editQuickChartPopup" title="Upload Chart"
												Title="Upload Image"
												style="float: right; margin-right: 5px;">Upload Image</a>
										</div>
									</div>
									<div>
										<div class="col-lg-12 col-md-12"
											style="padding-left: 0px; padding-right: 0px;">

											<s:iterator value="imageDataList" status="rowstatus">
												<div class="col-lg-2 col-md-2 col-xs-4"
													style="padding-left: 0px; padding-right: 0px; margin-top: 8px;">
													<img class="scrollimg img-responsive img-thumbnail"
														src="_assets/img/body01.JPG"
														onclick="setImageInPopup('<s:property value = "imageData"/>','<s:property value = "id"/>')" />
													<s:form theme="simple" action="deleteClientImageEmr"
														id="deleteClientImage">
														<s:hidden name="clientDataId" value="%{id}"
															id="clientDataId1"></s:hidden>
														<s:hidden id="clientname" value="%{clientname}"
															name="clientname"></s:hidden>
														<s:hidden id="diaryUser" value="%{diaryUser}"
															name="diaryUser"></s:hidden>
														<s:hidden id="condition" value="%{condition}"
															name="condition"></s:hidden>
														<s:hidden id="delimageapmtId" name="apmtId" />
														<i class="fa fa-times delete"
															onclick="deleteClientImage()"></i>
													</s:form>

												</div>
											</s:iterator>


										</div>

										<div class="col-lg-12 col-md-12"
											style="padding-left: 0px; padding-right: 0px;">

											<s:iterator value="videoList" status="rowstatus">
												<div class="col-lg-2 col-md-2"
													style="padding-left: 0px; padding-right: 0px; margin-top: 8px;">
													<img class="scrollimg img-responsive img-thumbnail"
														src="_assets/img/player.jpg"
														onclick="openVideoClipPopup('<s:property value = "videoFileName"/>','<s:property value = "id"/>')" />

												</div>
											</s:iterator>


										</div>


									</div>


								</div>
								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>


					<div class="main hidden">
						<a href="#wheel3" class="wheel-button nw"> <span>+</span>
						</a>
						<ul id="wheel3" data-angle="NW" class="wheel">
							<li class="item"><span id="confidentialid"><a
									href="#" onclick="showconfidentialpopup()"
									title="Make Confidential"><img src="cicon/unlock.png"
										class="img-responsive" style="width: 100%;" /></a></span></li>
							<li class="item"><span id="shareids"><a href="#"
									onclick="showsharepopup()" title="Share"><img
										src="cicon/share.png" class="img-responsive"
										style="width: 100%;" /></a></span></li>
						</ul>
					</div>



					<!--  -->
					<div id="selectothertemlate" class="modal fade" role="dialog"
						aria-labelledby="myModalLabel" tabindex="-1" aria-hidden="true"
						style="background-color: rgba(0, 0, 0, 0.32941176470588235);">
						<div class="modal-dialog modal-sm">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Select Specialization</h4>
								</div>
								<div class="modal-body">

									<s:select list="specializationTemplateList"
										onchange="setOtherTemplateBySpeciality(this.value,'','add')"
										listKey="id" listValue="name"
										cssClass="form-control chosen-select" headerKey="0"
										headerValue="Select Specilization"></s:select>

								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default hidden"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>




					<!-- Consultant -->
					<div id="selectothertemlate" class="modal fade" role="dialog"
						aria-labelledby="myModalLabel" tabindex="-1" aria-hidden="true"
						style="background-color: rgba(0, 0, 0, 0.32941176470588235);">
						<div class="modal-dialog modal-sm">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Select Specialization</h4>
								</div>
								<div class="modal-body">

									<s:select list="specializationTemplateList"
										onchange="setOtherTemplateBySpeciality(this.value,'','add')"
										listKey="id" listValue="name"
										cssClass="form-control chosen-select" headerKey="0"
										headerValue="Select Specilization"></s:select>

								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default hidden"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
					<!-- Modal All Client Search Div -->
					<div class="modal fade popoverpop" id="clientSearchDiv"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
						aria-hidden="true" style="z-index: 10005">
						<div class="modal-dialog modal-lg asd">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										style="margin-top: -7px !important">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title" id=""><%=loginInfo.getPatientname_field()%>
										Search
									</h4>
								</div>
								<div class="modal-body">
									<jsp:include page="/diarymanagement/pages/allClient.jsp"></jsp:include>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary hidden"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Consultant -->
					<div id="editselectothertemlate" class="modal fade" role="dialog"
						aria-labelledby="myModalLabel" tabindex="-1" aria-hidden="true"
						style="background-color: rgba(0, 0, 0, 0.32941176470588235);">
						<div class="modal-dialog modal-sm">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Select Specialization</h4>
								</div>
								<div class="modal-body">

									<s:select list="specializationTemplateList"
										onchange="setOtherTemplateBySpeciality(this.value,'','edit')"
										listKey="id" listValue="name"
										cssClass="form-control chosen-select" headerKey="0"
										headerValue="Select Specilization"></s:select>

								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default hidden"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
					<!-- Insert New Vital -->
					<div class="modal fade modal-draggable" id="insertvital"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h3 class="modal-title" id="myModalLabel">New Vital</h3>
								</div>
								<div class="modal-body" id="">
									<div class="col-lg-12 col-md-12" id="">
										<div class="row">
											<div class="form-group">
												<label>Height</label>
												<s:textfield cssClass="form-control"
													onchange="calulatebmi()" id="height" />
											</div>
											<div class="form-group">
												<label>Weight</label>
												<s:textfield cssClass="form-control"
													onchange="calulatebmi()" id="weight" />
											</div>
											<div class="form-group">
												<label>BMI</label>
												<s:textfield cssClass="form-control" id="bmi" />
											</div>
											<div class="form-group">
												<label>Pulse</label>
												<s:textfield cssClass="form-control" id="pulse" />
											</div>
											<div class="form-group">
												<label>Sys-BP</label>
												<s:textfield cssClass="form-control" id="sysbp" />
											</div>
											<div class="form-group">
												<label>Dia-BP</label>
												<s:textfield cssClass="form-control" id="diabp" />
											</div>
											<div class="form-group">
												<label>Temprature</label>
												<s:textfield cssClass="form-control" id="tempr" />
											</div>
											<div class="form-group">
												<label>Spo2</label>
												<s:textfield cssClass="form-control" id="spo2" />
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer ">
									<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
									<button type="button" class="btn btn-primary"
										onclick="savevitals(<s:property value="clientname"/>,<s:property value="apmtId"/>)">Save
										Vital</button>
								</div>
							</div>
						</div>
					</div>
				</div>


				<!-- Vital Details -->
				<div class="modal fade modal-draggable" id="vital_details"
					tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog modal-md" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h3 class="modal-title" id="myModalLabel">Vital</h3>
							</div>
							<div class="modal-body popmodals" id="newdiv">
								<div class="print-visible hidden-md hidden-lg letterheadhgt"
									style="height: 135px;">
									<div id="newpost"
										class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
											style="padding-left: 0px; padding-right: 0px;">
											<link href="common/css/printpreview.css" rel="stylesheet" />
											<%-- <%@ include file="/accounts/pages/letterhead.jsp"%> --%>
											<jsp:include page="/accounts/pages/letterhead.jsp" /> 
										</div>
									</div>
								</div>
								<span id="clindname" style="font-size: 23px; font-weight: 800;"></span>
								<span style="padding-left: 660px;"><a href="#"
									class="btn btn-warning hidden-print"
									onclick="printDiv('newdiv')" style="margin-top: 15px;">Print</a></span>
								<div class="table-responsive">
									<table id="allvital" class="tablecls">

									</table>
								</div>






								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="modal fade" id="image-gallery" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content" style="height: 600px;">
							<div class="modal-header">
								<%-- <h4 class="modal-title" id="image-gallery-title">Preview</h4>
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span>
                        </button> --%>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title hidden" id="image-gallery-title">Preview</h4>
								<h4 class="modal-title">Preview</h4>
							</div>
							<div class="modal-body">
								<img style="height: 530px;" id="image-gallery-image"
									class="img-responsive col-md-12" src="">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary float-left"
									id="show-previous-image">
									<i class="fa fa-arrow-left"></i>
								</button>

								<button type="button" id="show-next-image"
									class="btn btn-secondary float-right">
									<i class="fa fa-arrow-right"></i>
								</button>
							</div>
						</div>
					</div>
				</div>

				<!--end POPup  -->


				<!-- Pacs Details -->
				<div class="modal fade modal-draggable" id="pacs_details"
					tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog modal-md" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h3 class="modal-title" id="myModalLabel">PAC's</h3>
							</div>
							<div class="modal-body popmodals">
								<span id="pacsclientname"
									style="font-size: 23px; font-weight: 800;"></span>
								<div class="table-responsive">
										<table class="table table-bordered" width="100%">
											<thead>
												<tr class="tableback">
													<th class="pdate manastableheader" style="text-align: center;">Sr.No</th>
													<th class="pnew manastableheader" style="text-align: center;">File</th>
													<th class="pnew manastableheader" style="text-align: center;">UHID</th>
													<!-- <th class="pnamewidth manastableheader">Modality</th>
													<th class="page manastableheader">Invest ID</th>
													<th class="pdate manastableheader">Patient Name</th>
													<th class="pnew manastableheader">Study Date</th> -->
													<th class="pnew manastableheader" style="text-align: center;">Recieved On</th>
													
													<th class="pnew manastableheader" style="text-align: center;">Print Report</th>
												
													<!-- <th class="pview manastableheader" >Status</th>
													<th class="pview manastableheader">View</th> -->
												</tr>
											</thead>
											<tbody id="pacsdetails">
											<%int cnt = 1; %>
											<s:iterator value="pacsdataList">
												<tr id="<s:property value="id"/>">
													<td><%=cnt %></td>
													
													<td>
														<%--  <a href="#" id="myAnchor<s:property value="id"/>" onclick="openPacsPopup('http://<%=loginInfo.getPacsip() %>:8080/webpacs/viewPacs?mpid=<s:property value="multipacsid"/>')" >
														<a href="#" id="myAnchor<s:property value="id"/>" src="liveData/webpacs/pacsdata/37/P3.img" alt="Another alt text">" >
														<s:property value="filename"/>
														</a> --%>
														
														<a href="#" onclick="openPacsPopup('http://<%=loginInfo.getPacsip() %>:8080/webpacs/pacsdata/<s:property value = "multipacsid" />/<s:property value="filename" />')" id="myAnchor3"><s:property value="filename" /> </a>
													</td>
													<td><s:property value="abrivationid"/></td>
													<%-- <td><s:property value="modality"/></td>
													<td><s:property value="pid"/></td>
													<td><s:property value="pname"/></td> --%>
													<%-- <td><s:property value="studydate"/></td> --%>
													<td><s:property value="recievedon"/></td>
													<%-- <td> 
														<s:if test="fstatus==1">
															Updated
														</s:if>
														<s:else>
															Not Updated
														</s:else>
													</td> --%>
													<%-- <td>
														<a href="#" onclick="openEmrPopup('pacsreportEmr?imgid=<s:property value="pid"/>&action=p')" class="font11" title="View Report" >
															<i class="fa fa-object-ungroup" aria-hidden="true"></i>
														</a>
													</td> --%>
												</tr>
												<%cnt++; %>
												</s:iterator>
											</tbody>
										</table>
								</div>
								<!-- end Pacs Details -->






								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--  Loader-->
				<div class="modal fade"
					style="background: rgba(255, 255, 255, 0.93); z-index: 1500000"
					id="loaderPopup" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true"
					data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<div class="">
							<div class="modal-body text-center">
								<img src="common/images/hourglass1.gif"
									class="img-responsive middlelogo"
									style="margin-left: auto; margin-right: auto;"></img>

							</div>
						</div>
					</div>
				</div>
				<!-- End Loader -->

				<!--Add Charge  -->

				<!-- add ipd / opd investigation charge -->
				<div class="modal fade popoverpop notranslate"
					id="addchargepopupipdopdinv" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true"
					style="z-index: 10010">
					<div class="modal-dialog modal-md">
						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal"
									style="margin-top: -7px !important">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="completeAmtTitle">
									Add Charges for <span id="addinvnewchargehead"></span>
								</h4>
							</div>
							<div class="modal-body">

								<jsp:include page="/manasemr/pages/addchargesemr.jsp" />
							</div>

							<s:form action="estimateCharges" theme="simple" id="estimatefrm"
								target="formtarget">
								<s:hidden name="clientId" id="estimateclientid" />
								<s:hidden name="actionType" value="0" />
							</s:form>

							<div class="modal-footer">



								<button type="button" class="btn btn-primary"
									onclick="createipdopdChargeAndUpdateAccountEmr('Charge')">Create
									Charge</button>

								<button type="button" class="btn btn-primary hidden"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>

				<!--End Add Charge  -->


<!-- Sitting List -->

				<div class="modal fade modal-draggable" id="sitting_details"
					tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog modal-md" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h3 class="modal-title" id="myModalLabel">Sitting</h3>
							</div>

							<div class="modal-body popmodals" id="newdiv">
								<span id="clindname" style="font-size: 23px; font-weight: 800;"></span>
								<%-- 				   <span style="padding-left: 660px;"><a href="#" class="btn btn-warning hidden-print" onclick="printDiv('newdiv')" style="margin-top: 15px;">print</a></span> 
 --%>
								<div class="table-responsive">
									<table id="allvital" class="tablecls">
										<thead>
											<tr>
												<th class="text-center">Serial No</th>
												<th class="text-center" hidden>ID</th>
												<th class="text-center">Department Name</th>
												<th class="text-center">Sitting</th>
												<th class="text-center" hidden>Followup</th>
												<th class="text-center">Followup Date</th>
												<th class="text-center">Remark</th>
												<th class="text-center">Userid</th>
												<th class="text-center">Date_time</th>
												<th class="text-center">Procedure MasterName</th>
												<th class="text-center">Procedure Name</th>
												<th class="text-center">Sitting Num</th>
												<th class="text-center">Edit</th>
												<th class="text-center">Delete</th>
											</tr>
										</thead>

										<tbody id="carttbody">

										</tbody>
									</table>
								</div>

								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--  End -->


<!--Add Sitting Followup List -->

<%-- <div id="showsitting" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
     <div class="modal-content">
          <div class="modal-header">
         	  <button type="button" class="close" data-dismiss="modal">&times;</button>
          	   <h4 class="modal-title">Sitting <label id="patientnm"></label></h4>
        </div>
       <div class="modal-body">
      	   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      	
      	   <input type="hidden" id="emrPageId" value="1">
      	     <input type="hidden" id="dept_opdid"> 
      	     <s:hidden id="sitting_Clientid"></s:hidden>
      	     <input type="hidden" id="sitting_appointmentid"> 
      	
      	     <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<label>Department List </label><label class="text-danger">*</label>
	      			<s:select list="disciplineList" name='department' id='dept' listKey="id"  listValue="discipline" headerKey="" headerValue="All Department" onchange="setDepartmentsitting(this.value)" cssClass="form-control chosen-select" ></s:select>
      			</div>
      		</div>
      <!-- 	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"> -->
      
              <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                    <div class="form-group" id="proddiv" >
        				 <label>Select Sitting</label>
        				 <select name="sitting_id" id="sitting_id" class="form-control chosen-select">
						 <option value="0">Select</option> 
						</select>
        			</div>
        	   </div>
      		
      		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                    <div class="form-group" id="mas_div" >
        				 <label>Procedure MasterList</label>
        				 <select name="master_id" id="master_id" class="form-control chosen-select">
						 <option value="0">Select</option> 
						</select>
        			</div>
        	</div>
      		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                    <div class="form-group" id="pro_dure" >
        				 <label>Select Procedure</label>
        				 <select name="procedure_id" id="procedure_id" class="form-control chosen-select">
						 <option value="0">Select</option>
						</select>
        			</div>
        	 </div>
      
      	  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<label>Followup Date : </label>
	      			<s:textfield readonly="true" name="date" id="sittingDate" placeholder="select Date"
					cssClass="form-control" theme="simple" />
				</div>
          </div>
      		     <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      		        <div class="form-group">
      		            <label>Remark</label>
						<s:textarea name="remark" id="referremark" cssClass="form-control setreferscroll"  rows="4" cols="5"/>
				   </div>
      		     </div>
      		</div>
       </div>
       
          <div class="modal-footer">
             
     	                 <button type="button" class="btn btn-primary"
							onclick="showsittingfollowuplist()" style="margin-top: 15px;">Save</button>
		    
       </div>
     </div>
   </div>
</div>  

<!-- Edit Sitting -->				
<div id="editsitting" class="modal fade" role="dialog">

				<div class="modal-dialog modal-sm">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Sitting</h4>
							</div>
							
							<div class="modal-body">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

									 <s:hidden id="sittingClientid"></s:hidden>
      	                             <input type="hidden" id="opdid">
									
									<s:hidden id="id"></s:hidden>
									
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group">
											<label>Department List </label><label class="text-danger">*</label>
											<s:select list="disciplineList" name='department' id='edit_dept'
												listKey="id" listValue="discipline" headerKey=""
												headerValue="All Department"
												onchange="setDepartmentsitting(this.value)"
												cssClass="form-control chosen-select"></s:select>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group" id="ed_div">
											<label>Select Sitting</label> <select name="sitting_id"
												id="edit_sittings" class="form-control chosen-select">
												<option value="0">Select</option>
											</select>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									    <div class="form-group" id="mased_div" >
        				                   <label>Procedure MasterList</label>
        				                      <select name="master_id" id="edit_master_id" class="form-control chosen-select">
						                      <option value="0">Select</option> 
						                     </select>
        								</div>
									
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group" id="pro_durediv">
											<label>Select Procedure</label> <select name="procedure_id"
												id="edit_procedure" class="form-control chosen-select">
												<option value="0">Select</option>
											</select>
										</div>
									</div>


                                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group">
											<label>Followup Date : </label>
											<s:textfield readonly="true" name="date" id="ed_sittingDate"
												placeholder="select Date" cssClass="form-control"
												theme="simple" />
										</div>
									</div>
									
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group">
											<label>Remark</label>
											<s:textarea name="remark" id="edit_remark"
												cssClass="form-control setreferscroll" rows="4" cols="5" />
										</div>
									</div>
									
								</div>
							</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									onclick="updatesittingfollowup()" style="margin-top: 15px;">Update</button>
							</div>
						</div>
					</div>
				</div> --%>
				
				
<div id="showsitting" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         	<button type="button" class="close" data-dismiss="modal">&times;</button>
          	<h4 class="modal-title">Sitting<label id="patientnm"></label></h4>
        </div>
  <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      	
      	     <s:hidden id="sitting_Clientid"></s:hidden>
      	     <input type="hidden" id="dept_opdid">
             <input type="hidden" id="sitting_appointmentid">
              <input type="hidden" id="emrPageId" value="1">       	
      	 
      		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group" id="sittingDeptDiv">
	      			<label>Department List </label><label class="text-danger">*</label>
	      			<s:select list="disciplineList" name='department' id='dept' listKey="id"  listValue="discipline" headerKey="" headerValue="All Department" onchange="setDepartmentsitting(this.value)" cssClass="form-control chosen-select" ></s:select>
      			</div>
      		</div>
      <!-- 	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"> -->
      
              <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                    <div class="form-group" id="proddiv" >
        				 <label>Select Sitting</label>
        				 <select name="sitting_id" id="sitting_id" class="form-control chosen-select">
						 <option value="0">Select</option> 
						</select>
        			</div>
        	   </div>
      
            <%--  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<label>Procedure MasterList </label><label class="text-danger">*</label>
	      			<s:select list="procedurelist" name='all_procedure' id='all_procedure' listKey="id"  listValue="name" headerKey="" headerValue="All Procedure" onchange="setProceduremasterlist(this.value)" cssClass="form-control chosen-select" ></s:select>
      			</div>
      		</div> --%>  
      		
      		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                    <div class="form-group" id="mas_div" >
        				 <label>Select Procedure Master</label>
        				 <select name="master_id" id="master_id" class="form-control chosen-select">
						 <option value="0">Select</option> 
						</select>
        			</div>
        	   </div>
      		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                    <div class="form-group" id="pro_dure" >
        				 <label>Select Procedure</label>
        				 <select name="procedure_id" id="procedure_id" class="form-control chosen-select">
						 <option value="0">Select</option>
						</select>
        			</div>
        	 </div>
      		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                <div class="form-group" id="sitting_num" >
        				 <label>Sitting No</label><label class="text-danger">*</label>
				              <input type="number" id="sittingno" name="sittingno"
				                Class="showToolTip form-control" data-toggle="tooltip"
					            title="Enter Sitting No " placeholder="Enter Sitting No"/>
        	    </div>
        	 </div>
        	 
        <%if(loginInfo.isLmh()){ %>	 
        	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<label>Sitting Date : </label>
	      			<s:textfield readonly="true" name="sittingbackdate" id="sittingBackDate" placeholder="Select Date"
					cssClass="form-control" theme="simple" />
				</div>
         </div>
       <%}else{ %>
       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
      			<div class="form-group">
	      			<label>Sitting Date : </label>
	      			<s:textfield readonly="true" name="sittingbackdate" id="sittingBackDate" placeholder="Select Date"
					cssClass="form-control" theme="simple" />
				</div>
         </div>
       <%} %>	
      	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<label>Followup Date : </label>
	      			<s:textfield readonly="true" name="date" id="sittingDate" placeholder="Select Date"
					cssClass="form-control" theme="simple" />
				</div>
         </div>
      		     <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      		        <div class="form-group">
      		            <label>Remark</label>
						<s:textarea name="remark" id="referremark" cssClass="form-control setreferscroll"  rows="4" cols="5"/>
				   </div>
      		     </div>
      		</div>
     </div>
     <div class="modal-footer">
     	<button type="button" class="btn btn-primary"
							onclick="showsittingfollowuplist(<%=loginInfo.isPhysio() %>)" style="margin-top: 15px;">Save</button>
       </div>
     </div>
   </div>
</div>  

<!-- Edit Sitting --> 
<div id="editsitting" class="modal fade" role="dialog">

				<div class="modal-dialog modal-sm">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Sitting</h4>
							</div>
							
							<div class="modal-body">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

									 <s:hidden id="sittingClientid"></s:hidden>
      	                             <input type="hidden" id="opdid">
									<input type="hidden" id="sitting_appointmentid">
									<s:hidden id="id"></s:hidden>
									
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group" id="edit_sittingDeptDiv">
											<label>Department List </label><label class="text-danger">*</label>
											<s:select list="disciplineList" name='department' id='edit_dept'
												listKey="id" listValue="discipline" headerKey=""
												headerValue="All Department"
												onchange="setDepartmentsitting(this.value)"
												cssClass="form-control chosen-select"></s:select>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group" id="ed_div">
											<label>Select Sitting</label> <select name="sitting_id"
												id="edit_sittings" class="form-control chosen-select">
												<option value="0">Select</option>
											</select>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									    <div class="form-group" id="mased_div" >
        				                   <label>Procedure MasterList</label>
        				                      <select name="master_id" id="edit_master_id" class="form-control chosen-select">
						                      <option value="0">Select</option> 
						                     </select>
        								</div>
									
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group" id="pro_durediv">
											<label>Select Procedure</label> <select name="procedure_id"
												id="edit_procedure" class="form-control chosen-select">
												<option value="0">Select</option>
											</select>
										</div>
									</div>
									
                         <%if(loginInfo.isLmh()){ %>	 
        	                   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			                     <div class="form-group">
	      			                    <label>Sitting Date : </label>
	      			                     <s:textfield readonly="true" name="sittingbackdate" id="edit_sittingBackDate" placeholder="Select Date"
					                        cssClass="form-control" theme="simple" />
				                    </div>
                              </div>
                        <%}else{ %>
                             <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
      			                   <div class="form-group">
	      			                  <label>Sitting Date : </label>
	      			                      <s:textfield readonly="true" name="sittingbackdate" id="edit_sittingBackDate" placeholder="Select Date"
					                        cssClass="form-control" theme="simple" />
				                    </div>
                              </div>
                       <%} %>	  
                                   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                                        <div class="form-group" id="edit_num" >
        				                     <label>Sitting No</label><label class="text-danger">*</label>
				                                <s:textfield id="si_no" name="sittingno" cssClass="form-control" 
					                             title="Enter Sitting No " placeholder="Enter Sitting No"/>
        	                            </div>
        	                        </div>

                                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group">
											<label>Followup Date : </label>
											<s:textfield readonly="true" name="date" id="ed_sittingDate"
												placeholder="select Date" cssClass="form-control"
												theme="simple" />
										</div>
									</div>
									
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group">
											<label>Remark</label>
											<s:textarea name="remark" id="edit_remark"
												cssClass="form-control setreferscroll" rows="4" cols="5" />
										</div>
									</div>
									
								</div>
							</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									onclick="updatesittingfollowup()" style="margin-top: 15px;">Update</button>
							</div>
						</div>
					</div>
				</div>
				
		<!-- List Procedure -->

				<div class="modal fade modal-draggable" id="procedure_details"
					tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog modal-md" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h3 class="modal-title" id="myModalLabel">Procedure</h3>
							</div>
							<div class="modal-body popmodals" id="newdiv">
								<span id="clindname" style="font-size: 23px; font-weight: 800;"></span>
								<span style="padding-left: 660px;"><a href="#"
									class="btn btn-warning hidden-print"
									onclick="printDiv('newdiv')" style="margin-top: 15px;">Print</a></span>
								<div class="table-responsive">
									<table id="allvital" class="tablecls">

									</table>
								</div>

								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- End List Procedure -->

			</div>
		</div>

		<!-- main content area end -->

	</div>
	<!-- page container area end -->
<div class="modal fade modal-draggable" id="consultdetails"
						tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close" style="margin-top: -1px !important;">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Consultation History</h4>
								</div>
								<div class="modal-body popmodals">

									<div class="panel-body" id="consult"></div>


								</div>
								<div class="modal-footer hidden">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>

</body>
<script type="text/javascript">
	let modalId = $('#image-gallery');

	$(document)
	   .ready(function () {

	    loadGallery(true, 'a.thumbnail');

	    //This function disables buttons when needed
	    function disableButtons(counter_max, counter_current) {
	      $('#show-previous-image, #show-next-image')
	        .show();
	      if (counter_max === counter_current) {
	        $('#show-next-image')
	          .hide();
	      } else if (counter_current === 1) {
	        $('#show-previous-image')
	          .hide();
	      }
	    }

	    /**
	     *
	     * @param setIDs        Sets IDs when DOM is loaded. If using a PHP counter, set to false.
	     * @param setClickAttr  Sets the attribute for the click handler.
	     */

	    function loadGallery(setIDs, setClickAttr) {
	      let current_image,
	        selector,
	        counter = 0;

	      $('#show-next-image, #show-previous-image')
	        .click(function () {
	          if ($(this)
	            .attr('id') === 'show-previous-image') {
	            current_image--;
	          } else {
	            current_image++;
	          }

	          selector = $('[data-image-id="' + current_image + '"]');
	          updateGallery(selector);
	        });

	      function updateGallery(selector) {
	        let $sel = selector;
	        current_image = $sel.data('image-id');
	        $('#image-gallery-title')
	          .text($sel.data('title'));
	        $('#image-gallery-image')
	          .attr('src', $sel.data('image'));
	        disableButtons(counter, $sel.data('image-id'));
	      }

	      if (setIDs == true) {
	        $('[data-image-id]')
	          .each(function () {
	            counter++;
	            $(this)
	              .attr('data-image-id', counter);
	          });
	      }
	      $(setClickAttr)
	        .on('click', function () {
	          updateGallery($(this));
	        });
	    }
	  });

	// build key actions
	$(document)
	  .keydown(function (e) {
	    switch (e.which) {
	      case 37: // left
	        if ((modalId.data('bs.modal') || {})._isShown && $('#show-previous-image').is(":visible")) {
	          $('#show-previous-image')
	            .click();
	        }
	        break;

	      case 39: // right
	        if ((modalId.data('bs.modal') || {})._isShown && $('#show-next-image').is(":visible")) {
	          $('#show-next-image')
	            .click();
	        }
	        break;

	      default:
	        return; // exit this handler for other keys
	    }
	    e.preventDefault(); // prevent the default action (scroll / move caret)
	  });

	
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
	
    window.print();
    document.body.innerHTML = oldPage;
    window.location.reload();
    //detailsview
}
    
    $(function() {
		$('.setheight').slimScroll({
			height : 'auto',
			railVisible : true,
			alwaysVisible : true
		});
	});
	</script>
<script>
      function startConverting1(element) {

		   var abc=element.src.split('/');	
		   
		     var right = "cicon/mic_off.png";
		         var left = "cicon/mic.png";
		         element.src = element.bln ? right : left;
		         element.bln = !element.bln;
		         
		       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
		   if(abc[5]=="mic_off.png")
		   {
		    startConvertingVoiceRecord();
		   }
		   else{
		   //var textvalue=document.getElementById("otnotes").value;
		  // localStorage.setItem("xx",textvalue);
		   
		   }
		     }
    
	
	</script>
<script>
  function startConvertingVoiceRecord(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts  + interimtranscripts ;
		
		//var con = nicEditors.findEditor('adharsearch').getContent() + vtxt;
	//	nicEditors.findEditor('adharsearch').setContent(vtxt);
		nicEditors.findEditor('otnotes').setContent(vtxt);
		
		
		
	};

}
  
  </script>
</html>


