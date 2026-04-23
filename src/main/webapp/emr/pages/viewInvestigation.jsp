<!DOCTYPE html>

<%@page import="com.apm.DiaryManagement.eu.entity.Breadcrumbs"%>
<%@page import="com.apm.Emr.eu.entity.Investigation"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<style>
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
</style>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<meta name="description" content="">
<meta name="author" content="Dashboard">

<link rel="icon" href="assets/img/favicon.ico">
<title>YUVICARE</title>
<!-- Bootstrap core CSS -->
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>




<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script> 
<script type="text/javascript" src="emr/js/addInvestigation.js"></script> 
<script type="text/javascript" src="emr/js/viewinvestigation.js"></script> 
 <script type="text/javascript" src="diarymanagement/js/sendsms.js"></script>
  <script type="text/javascript" src="diarymanagement/js/sendApmtAttachnment.js"></script>
   <script type="text/javascript" src="common/js/pagination.js"></script>
   <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
   <script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"> </script>
	   <script type="text/javascript" src="tools/js/sendLetter.js"></script> 
	    <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
	    <script type="text/javascript" src="diarymanagement/js/newinvestigationclient.js"></script>
	  <%--   <script type="text/javascript" src="ipd/js/admissionform.js"></script> --%>
<%-- <script type="text/javascript" src="ipd/js/addcharge.js"></script> --%>
<SCRIPT type="text/javascript">

 function updateApprove() {
	 	setremark();
 		document.getElementById("approved").value="1";
 		document.getElementById("commentwriteupappr").value= nicEditors.findEditor( "commentwriteup" ).getContent();
 		document.getElementById("commentwriteupappr1").value= nicEditors.findEditor( "commentwriteup" ).getContent();
 		document.getElementById("upidreport").submit();
 		
 }
  function updateApprove1() {
	  setremark();
 		document.getElementById("approved1").value="1";
 		
 		document.getElementById("upload").submit();
 		
 }
 
 

</SCRIPT>


<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('templatesec');
	        	 $('.nicEdit-panelContain').parent().width('98%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 
	        	// $('.nicEdit-main').height('480px');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],minHeight : 70}).panelInstance('comment');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],minHeight : 70}).panelInstance('commentwriteup');
                 	     
 });

</script>

<div class="row" style="color: black">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	</div>
</div>
		
<style>
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    border-bottom: 1px solid rgba(243, 241, 241, 0.63) !important;
}
.martop10{
margin-top:10px;
}.invest7{
    width: 8%;
}
.invest5{
    width: 5%;
}
.invest8{
width: 8%;
}
.invest4{
width: 3%;
}
.invest3{
width: 3%;
}
.invest21{
 width: 18%;

}
.invest24{
    width: 7% !important;
}
.invest10{
    width: 10%;
}
.invest9{
width: 15%;
}
.mainheader {
    background-color: #43b9be !important;
}
.attached{
    text-align: left;
    font-size: 12px;
    font-weight: bold;
    color: #0514DE;
}
.indopborder {
	border: 2px dotted #A5A5C7 !important;
    width: 135% !important;
    color: #9C9C9C !important;
    text-align: left;
    vertical-align: middle !important;
    padding: 5px 5px 5px 5px !important;
}

.tooltip {
    position: relative;
    display: inline-block;
    opacity: 1;
    z-index: 0;
}

.tooltip .tooltiptext {
    visibility: hidden;
    width: 180px;
    text-align: left;
    padding: 0px 0;
    background-color:#555;
    color:#fff;
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 50%;
    margin-left: -60px;
    opacity: 0;
    transition: opacity 1s;
}
.imflag{
 display: inline-block;
  width: 13px;
}


.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 10%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1 !important;
}
.newstate{
	background-color: #ec0a0a;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
        width: 100%;
}



.collectstate{
	background-color: #cece2e;
	text-transform: uppercase;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-align: center;
    border-radius: 6px;
        width: 100%;
}


.completedstate{
	background-color: green;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-transform: uppercase;
    text-align: center;
    border-radius: 6px;
        width: 100%;
}

.approvedstate{
	background-color: #5d3504;
    color: #fff;
    padding: 1px 3px 2px 3px;
    text-align: center;
    text-transform: uppercase;
    border-radius: 6px;
        width: 100%;
}

.fontset{
    font-size: 9px;
}

.blink_me {
  animation: blinker 1s linear infinite;
  color:#0066ff;
}

@keyframes blinker {  
  50% { opacity: 0; }
}
.fa-2x {
    font-size: 13px;
}


/* .table-bordered>thead>tr>th, .table-bordered>thead>tr>td {
background-color:#328e7b !important;
} */

.table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
    background-color: #f0fffe !important;
}

.table-striped>tbody>tr:nth-child(odd)>td, .table-striped>tbody>tr:nth-child(odd)>th {
    background-color: #ddf3f2 !important;
}



#header {
	background-color: #43b9be;
}

.texthead {
	font-size: 15px;
}

.appWrapper.header-fixed #header {
	position: fixed !important;
	height: auto !important;
}

#vvch {
	max-width: 88%;
	float: right;
}

.sssw {
	max-width: 12%;
	float: left;
	position: absolute;
}

#menutt {
	height: auto;
	min-height:100vh;
	
	
	
	/* overflow: hidden; */
	/* background: #43b9be;
 */	background:rgb(206 236 242); */
	box-shadow: 2px 0 32px rgba(0, 0, 0, 0.05);
	-webkit-transition: all 0.3s ease 0s;
	transition: all 0.3s ease 0s;
}



.headerVch {
	width: 100%;
}

body{
font-family: 'Open Sans', sans-serif !important;

}




.sidenav {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  padding-left:0px;
  overflow-x: hidden;
  transition: 0.5s;
 
}

.sidenavx  a{
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
.navbrcontainer {
	width: 100%;
	padding: 0px 0px 0px 16px;
	text-align: left;
}

.navbrcontainer li {
	list-style-type: none;
	padding-top: 20px;
}

.navbrcontainer ul {
	padding: 0px;
}

li-x {
	list-style: none;
}

.li-x .head {
	cursor: pointer;
	font-size: 14px;
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
padding: 10px;
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
<style>
/******css for header [pikachu] ******/
.manascommheader{
  	padding: 6px;
    font-size: 20px;
    text-transform: uppercase;
    display: flex;
    border-bottom: 2px solid #10606f;
    background: #43b9be;
}
.manascommheader h4,h3{
	font-weight: bold;
	font-size: 17px;
	font-family: 'Open Sans', sans-serif !important;
	color: white;
}
.manascommheader img{
	margin-right: 6px !important;
}

.manastableheader {
    background-color: #95d2de !important;
    color: black !important;
}
/****** ******/
</style>
</head>

<script>

function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "investigationdashboard",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}


$(document).ready(function(){
	 $('#newloaderPopup').modal('show');

    var today = new Date();
	var date = today.getDate();
	var maonth = today.getMonth();
	var year = today.getFullYear(); 
	
	 $( "#dob" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 minDate : '30/12/1880',
		 yearRange: yearrange,
		 maxDate: today,
		 changeMonth: true,
	     changeYear: true
		 
   });
   
	 $("#datw").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
	 
	 $("#outexpdsate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : 0,
			changeMonth : true,
			changeYear : true

		});
	 
   
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
   $('input[type=text]').addClass('notranslate');
   $('input[type=textarea]').addClass('notranslate');
   $('.chosen-container').addClass('notranslate');
   $('.form-control').addClass('notranslate');
   $('form-group').addClass('notranslate');
   
   
});	




bkLib.onDomLoaded(function() {
   
	// new nicEditor().panelInstance('emailBodyLetter');
	 new nicEditor({maxHeight : 2500}).panelInstance('emailBodyLetter');
	
	 
	 $('.nicEdit-panelContain').parent().width('100%');
	 $('.nicEdit-panelContain').parent().next().width('98%');
	 
	 $('.nicEdit-main').width('98%');
	 $('.nicEdit-main').hieght('50px');
	 //document.getElementById("user").disabled = 'disabled';
	
});
</script>

<script>
var patientId = 0;
var diaryuserId = 0;
var editcondition_id = 0;
var editAppointId = 0;

</script>

<script>
function setremark(){
	 var remark =   nicEditors.findEditor( "comment" ).getContent();
	 document.getElementById("comment").value=remark;
	 
	 var commentwriteup =   nicEditors.findEditor( "commentwriteup" ).getContent();
	 document.getElementById("commentwriteup").value=commentwriteup;
}

$(window).on('load', function() {
	$('#newloaderPopup').modal('hide');
});

window.onload = function () {
	 currencySign = '<%=Constants.getCurrency(loginfo)%>'; 
	 <%-- document.cookie = "googtrans=/en/<%=loginfo.getRegional_lang()%>"; --%>
	var c=document.getElementById("isindivisual").value;
	var clid=document.getElementById("indivisualclientid").value;
	var clname=document.getElementById("indivisualclientname").value;
	
	var age=document.getElementById("indage").value;
	var gend=document.getElementById("indgender").value;
	var dr=document.getElementById("doctorname1").value;;
	if(c=="1"){
		
		dr=dr.replace(" ", "/")
		
		setPatientName(clname,clid,"",age,gend,dr);
	}
}
/* $(document).ready(function(){
	document.addEventListener("contextmenu", function(e){
	e.preventDefault();
	}, false);
}); */

document.addEventListener('readystatechange', event => {
	document.cookie = "googtrans=/en/<%=loginfo.getRegional_lang()%>";
});

function openNav() {
	  document.getElementById("mySidenav").style.width = "170px";
	}

	function closeNav() {
	  document.getElementById("mySidenav").style.width = "0";
	}
</script>
<body >
<div style="display: none;" id="google_translate_element"></div>

<s:hidden name="priscdate" id="invsdate"/>
<s:hidden name="priscdateandtime" id="invstdateandtime"/>
<s:hidden name="isindivisual" id="isindivisual"/>
<s:hidden name="indivisualclientid" id="indivisualclientid"/>
<s:hidden name="indivisualclientname" id="indivisualclientname"/>
<s:hidden name="doctorname" id="doctorname1"/>
<s:hidden name="indage" id="indage"/>
<s:hidden name="indgender" id="indgender"/>

	<!-- **********************************************************************************************************************************************************
     MAIN CONTENT
     *********************************************************************************************************************************************************** -->
	<!--main content start-->
	
	<!--  SIDE MENU-->
		<div   id="mySidenav" class="sidenav">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<section id='menutt' >
			
			<div class="logo" style="border:1px solid #343e5033">
				<img src="manasopd/assets/images/Manas_Yuvicare_Logo.svg"
				style="height: 56px; width: 100px;">
			</div>
			<div class='navbrcontainer'>
				<ul>
			<li class='li-x' style="list-style: none;"><span class='head'
			data-toggle="collapse" data-target=".rpts"> <i
				class="fa fa-random"></i> Reports</span>
			<ul class='rpts collapse out'>
				
						
						
										<li><a class="dropdown-item" onclick="openPopup('investoutsourceReport')" href="#"><i class="fa fa-caret-right"></i>Out Source Report</a></li>
                                    	<li><a class="dropdown-item" onclick="openPopup('LabreportSummary')" href="#"><i class="fa fa-caret-right"></i>Lab report</a></li>
                                    	<li><a class="dropdown-item" onclick="openPopup('investigationCountSummary')" href="#"><i class="fa fa-caret-right"></i>Investigation count report</a></li>
                                    	<li><a class="dropdown-item" onclick="openPopup('invstpackagerptSummary')" href="#"><i class="fa fa-caret-right"></i>Investigation Package report</a></li>
                                    	<li><a class="dropdown-item" onclick="openPopup('investigationtypewisereportnewInvestigation')" href="#"><i class="fa fa-caret-right"></i>Investigation Wise Count Report</a></li>
                                        <li><a class="dropdown-item" onclick="openPopup('report/pages/investigationrevenuereport.jsp')" href="#"><i class="fa fa-caret-right"></i>Investigation Revenue Report</a></li>
                                        <li><a class="dropdown-item" onclick="openPopup('getinvesrevenueNcountInvestigation')" href="#"><i class="fa fa-caret-right"></i>Investigation Revenue And Count Report(Name Wise)</a></li>
                                        <li><a class="dropdown-item" onclick="openPopup('ipdopdrevenueinvstrevnueSummary')" href="#"><i class="fa fa-caret-right"></i>Investigation IPD/OPD Report</a></li>
                                       <li> <a class="dropdown-item" onclick="openPopup('investigationtatReport')" href="#"><i class="fa fa-caret-right"></i>Investigation TAT Report</a></li>
                                        <li><a class="dropdown-item" onclick="openPopup('invstabsvalreportSummary')" href="#"><i class="fa fa-caret-right"></i>Investigation Critical Value Report</a></li>
				
			</ul>
			</li>
			</ul>
			</div>
			
			
		</section>
		</div>
		<section>

			<!-- page start-->
						<div class="">
							<div class="row <!-- details mainheader --> ">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
								 <span style="cursor:pointer;color:gray" onclick="openNav()" style="padding-right: 5px;">&#9776; </span>
									<img src="manasmaindashboard/images/Investigation)Icon.svg" style="filter: brightness(5);" class="img-responsive prescripiconcircle" ><h4>Investigation Dashboard </h4>
								</div>
								
								</div>
							</div>
						<%-- 	
							<div class="hidden-print">
								<ul class="breadcrumb">
									<%ArrayList<Breadcrumbs> indentflowlist = (ArrayList<Breadcrumbs>) session.getAttribute("indentflowlist"); %>
									<%for (Breadcrumbs breadcrumbs : indentflowlist) { %>
										<%if(breadcrumbs.isIscurrent()){ %>
											<li><%=breadcrumbs.getName()%></li>
										<%}else{ %>
											<%if(breadcrumbs.getOn()){ %>
												<li><a href="<%=breadcrumbs.getUrllink()%>"><%=breadcrumbs.getName()%></a></li>
											<%}else{ %>
												<li><%=breadcrumbs.getName()%></li>
											<%} %>
										<%} %>
										
									<%} %>
								</ul>
							</div>
							 --%>
							
							<div class="row ">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<a style="display: none;" href="#" onclick="opencPopup('getPatientRecordEmr')" class="btn btn-primary marleft14"
										data-toggle="modal" data-target="#myModal"> Create
										Investigation </a> 
										<div class="col-lg-12 col-md-12 paddingnil">
										<div class="col-lg-12 col-md-12 topback2">
										<s:form id="searchfrm" theme="simple" action="Investigation?IpdpatientType=0" cssClass="form-inline search notranslate">
										   <%-- <s:hidden name="IpdpatientType" value="0"/> --%>
											<div class="form-group">
										    	<s:textfield id="searchText" name="searchText" cssClass="form-control" placeholder="Search UHID / patient name" style="width:100%;"/>
										  	</div>
											
											<div class="form-group" style="width: 7%;">
												<s:textfield id="fromdate" name="fromdate" cssClass="form-control" placeholder="From Date" cssStyle="width:100%;"/>
											</div>
											<div class="form-group" style="width: 7%;">
												<s:textfield id="todate" name="todate" cssClass="form-control" placeholder="To Date" cssStyle="width:100%;"/>
											</div>
											<div class="form-group">
												<s:select headerKey="0" headerValue="Select All" cssClass="form-control" list="#{'1':'Collect', '2':'Collected', '3':'Completed' , '4':'Approved'}" name="filter_status" />
												<%-- <select class="form-control">
													<option value="Select All">Select All</option>
													<option value="New">New</option>
													<option value="Collected">Collected</option>
													<option value="Completed">Completed</option>
													<option value="Approved">Approved</option>
												</select> --%>
											</div>
											<div class="form-group">
												<s:select headerKey="0" headerValue="Select All" cssClass="form-control" list="#{'IPD':'IPD', 'OPD':'OPD', 'URGENT':'URGENT'}" name="filter_ward" />
												<%-- <select class="form-control">
													<option value="Select All">Select All</option>
													<option value="IPD">IPD</option>
													<option value="OPD">OPD</option>
												</select> --%>
											</div>
											 <div class="form-group" style="width: 10%;">
												<s:select name="invsttype" id="invest" cssClass="form-control chosen-select" 
                                              list="invsTypeList" listKey="id" listValue="name" 
                                              headerKey="0" headerValue="Select Investigation Type" cssStyle="width:100%;"/>
											</div>
											<div class="form-group" style="width: 10%;">
												<s:select name="invstsecid" id="invstsecid" cssClass="form-control chosen-select" 
                                              list="allSectionList" listKey="id" listValue="name" 
                                              headerKey="0" headerValue="Select Section" cssStyle="width:100%;"/>
											</div>
											<div class="form-group" style="width: 10%;">
												<s:select name="outsource" id="outsource" cssClass="form-control chosen-select" 
                                             	 list="outsourcelist" listKey="id" listValue="name" 
                                             	 headerKey="0" headerValue="Out Source" cssStyle="width:100%;"/>
											</div>
											<div class="form-group" style="width: 10%;">
											<s:select headerKey="" headerValue="Select Delete/Active" cssStyle="width:90%" cssClass="form-control" list="#{'0':'Active', '1':'Deleted'}" name="isdeleted" />
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Go</button>
										  		<a href="#" onclick="refreshhere()" class="btn btn-primary" title="Refresh"><i class="fa fa-refresh"></i></a>
											</div>
											<div class="form-group">
												<s:textfield name="client" id="client" cssClass="form-control" onclick="showPopUp()" placeholder="Select Patient" style="width: 100%;"/>
											</div>
											
										
										<div class="form-group" style="width: 12%;">
										  <%LoginInfo l1 = LoginHelper.getLoginInfo(request); %>
                                               <% if(l1.getUserType()==2){%>
												  <s:select id="jobtitleid" name="jobtitle" list="jobTitleList"
													title="Select Job title" 
													cssClass="form-control showToolTip chosen-select" headerKey="0" headerValue="Select Job Title" data-toggle="tooltip" style="width:100%;"/>
												<%}else{ %>
													<s:select id="jobtitleid" name="jobtitle" list="jobTitleList"
													title="Select Job title"  disabled="true"
													cssClass="form-control showToolTip chosen-select" headerKey="0" headerValue="Select Job Title" data-toggle="tooltip" style="width:100%;"/>
												<% }%>
											</div>
											<div class="form-group">
											<button  class="btn btn-primary"  onclick="openPopup('LabreportSummary')" href="#" >View Report</button>
											</div>
											<div class="form-group">
												<a href="#"  onclick="printDiv('page_printer2')" title="Print Page"><i class="fa fa-print" aria-hidden="true"></i></a>
												<!-- <input type="button" onclick="printDiv2('page_printer2')" class="btn btn-primary savebigbtn hidden-print"  value="PRINT"/> -->
											</div>
											<div class="form-group" style="">
											<div class="form-inline" style="float: right;margin-top: 5px;">
											<!-- <input type="button" value ="Set TP" onclick="openPopup('thirdpartysettingsInvestigation')" class="btn btn-danger"> -->
											</div>
											</div>
											<div class="form-group" style="">
											<a href="#" onclick="printExcel()"  ><i class="fa fa-file-excel-o"></i></a>
											</div>
											<div class="form-group" style="float: right;">
												<div class="form-inline" style="float: right;margin-top: 5px;">
												
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#ec0a0a"></i> New
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#cece2e"></i> Collected
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:green"></i> Completed
											    </label>
											  </div>&nbsp;|
											  <div class="checkbox">
											    <label>
											      <i class="fa fa-square" aria-hidden="true" style="color:#5d3504"></i> Approved
											    </label>
											  </div>
											</div>
											</div>
											
										 <!-- <input type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPatientDiv" value="Add New Patient"> -->
										   <!--  <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#investigationpopup">Create New Investigation</button> -->
										  
										</s:form>
										</div>
										</div>
										<div class="scrolltable" >
										<s:hidden type="hidden" name="investigation_approve" id="investigation_approve"/>
										<input  type="hidden" id="emaorwhatsup" name="emaorwhatsup" />
											<table class="table table-bordered table-striped table-responsive table-hover " style="text-transform: uppercase;" >
											<thead>
												<tr class="headings">
												<th class="manastableheader">Sr.</th>
												<th class="manastableheader">Req. by</th>
													<th style="width: 9%;" class="manastableheader">Requested</th>
													<th style="width: 17%;" class="manastableheader">I.ID | Req. ID | UHID</th>
													<!-- <th style="width: 10%;">Requested <br>Collected</th>
													<th style="width: 10%;">Completed <br>Approved</th> -->
													<th style="width: 25%;" class="manastableheader">Patient Details</th>
													<%if(loginfo.isAyushman()) {%>
													<th style="width: 25%;" class="manastableheader">Address</th>
													<%} %>
													<th style="width: 29%;" class="manastableheader">Investigation</th>
													<th style="width: 10%;" class="manastableheader">Out Source</th>
													<th class="hidden-print manastableheader"></th>
													<th style="width: 29%;" class="hidden-print manastableheader">Print Request</th>
		                                          	<th style="text-align: center;width: 5%;" class="manastableheader">Report</th>
		                                          	<th style="width:6%;" class="hidden-print manastableheader">Payment</th>
		                                          	<!-- <th></th> -->
		                                          	<th class="hidden-print manastableheader"></th>
		                                          	<th class="hidden-print manastableheader"></th>
		                                          	<th class="hidden-print manastableheader"></th>
		                                          	<th class="hidden-print manastableheader"></th>
		                                          	<th class="hidden-print manastableheader"></th>
		                                          	<th class="hidden-print manastableheader"></th>
		                                          	<th class="hidden-print manastableheader"></th>
		                                          	<%if(loginfo.getIskunal()==1){ %>
		                                          		<th class="hidden-print manastableheader"><i class='fa fa-edit'></i></th>
		                                          	<%} %>
		                                          	<%-- <%if(loginfo.getClinicUserid().equals("aureus")){ %> --%>
		                                          		<!-- <th class="hidden-print manastableheader"><i class='fa fa-edit'></i></th> -->
		                                          	<%-- <%} %> --%><th class="hidden-print manastableheader"></th>
		                                          	<%if(loginfo.getJobTitle().equals("Admin")){ %>
		                                          		<th class="hidden-print manastableheader" title="Admin Delete"><i class='fa fa-trash'></i></th>
		                                          	<%} %>
												</tr>
											</thead>
											<%int w=1; %>
											<tbody>
											<%String bgcolor = ""; %>
												<s:iterator value="investigationList">
												
												<%-- <s:if test="collectstatus==1 && upstatus==0">
												<% bgcolor = "rgba(72, 204, 184, 0.28);"; %>
												</s:if>
												<s:elseif test="status==1">
												<% bgcolor = "rgba(0, 128, 0, 0.25);"; %>
												</s:elseif>
												
												<s:elseif test="upstatus==1">
													<% bgcolor = "rgba(229, 217, 129, 0.46);"; %>
												</s:elseif>
												<s:else>
													<% bgcolor = "rgba(255, 163, 163, 0.43);"; %>
												</s:else> --%>
													<tr style="cursor: pointer; background-color: <%=bgcolor %>" id="<s:property value="id" />" class="even pointer" ondblclick="setEmrSelectedRows(<s:property value="id" />,<s:property value="clientId" />)">
														<td><%=w %><%w=w+1; %></td>
														<td><s:property value="userid"/></td>
														<td style="color: green;"> <s:property value="date" /></td>
														<td class=" "><s:property value="id" /> | <s:property value="invreq"/> | <s:property value="abrivationid"/></td>
														<td class=" ">
														<div class="tooltip">
															<s:if test="outp==1">
															<img src="emr/img/medicineflag_new_small.png" class="img-responsive imflag"></img>
														</s:if>
														<s:elseif test="ipdid==0">
														  <img src="emr/img/medicineflag_opd_small.png" class="img-responsive imflag"></img> 
														</s:elseif>
														<s:elseif test="daycare">
																<span class="blink_me">
																	DC
														  		</span>
														</s:elseif>
														<s:else>
															<s:if test="urgentward==1">
																<span class="blink_me">
																	<img src="emr/img/mediflag_ipd_small.png" class="img-responsive imflag"></img>
														  		</span>
															</s:if>
															<s:else>
															<span>
																<img src="emr/img/mediflag_ipd_small.png" class="img-responsive imflag"></img>
														  	</span>
															</s:else>
															
														</s:else>
														<s:if test="deleted==0">
															<s:if test="urgentward==1">
																<span style="color:#ff0000;">
																 <s:property value="fullname" /> | <s:if test="ipdid!=0"><s:property value="wardname" /> / <s:property value="bedname" /> <span style="color: palevioletred;"><s:if test="validnote==1"><i class="fa fa-envelope faa-shake animated"></i></s:if></span></s:if>
														  	</span>
															</s:if>
															<s:else>
																<span>
																<s:property value="fullname" />  <s:if test="ipdid!=0"><s:property value="wardname" /> / <s:property value="bedname" /> <i class="fa fa-sticky-note-o" aria-hidden="true"></i></s:if>
														  	</span>
															</s:else>
														</s:if>
														<s:else>
															<span style="text-decoration: line-through;cursor: default;">
																<s:property value="fullname" /> | <s:if test="ipdid!=0"><s:property value="wardname" /> / <s:property value="bedname" /></s:if>
														  	</span>
														</s:else>
														  <s:if test="validnote==1">
														  	<span class="tooltiptext">
														  		<ul style="list-style: none;margin: 0px;padding: 2px 4px 2px 4px;">
														  			<li><s:property value="advoice" /></li>
														  		</ul>
														  		
														  	</span>
														  </s:if>
														</div>
														 </td>
														 <%if(loginfo.isAyushman()) {%>
														 <td><s:property value="address"/></td>
														 <%} %>
														<td>
														<div class="tooltip"> 
															<s:if test="deleted==0">
																<span style="font-size: 9px;"><s:property value="invsttype" /></span>
															</s:if>
															<s:else>
																<span style="text-decoration: line-through;cursor: default;font-size: 9px;"><s:property value="invsttype" /></span>
															</s:else>
														  <span class="tooltiptext">
														  	<ul style="list-style: none;margin: 0px;padding: 2px 4px 2px 4px;">
														  		<s:if test="collectstatus==1"><li>Collected:<span style="float:right;"><s:property value="collect_date" /></span></li></s:if>
														  		<s:if test="upstatus==1"><li>Completed:<span style="float:right;"><s:property value="update_date" /></span></li></s:if>
														  		<s:if test="status==1"><li>Approved:<span style="float:right;"><s:property value="complete_date" /></span></li></s:if>
														  	</ul>
														  </span>
														</div> 
														<span ><s:if test="outsrcExpDate!=''"><br><b>  Expected Date : <s:property value="outsrcExpDate" /></b></s:if></span>
														</td>
														
														<!-- Outsource code 19 April 2018  -->
														<td>
															<s:if test="outsource==0">
																<select onchange="saveOutsourceInvestigation(this.value,<s:property value="id"/>)" class="form-control chosen-select" >
																<option value="0">Select Outsource</option>
																<s:iterator value="outSourceList">
																	<s:if test="status==1">
																		<option value="<s:property value="id"/>" selected="selected"><s:property value="name"/></option>
																	</s:if>
																	<s:else>
																		<option value="<s:property value="id"/>"><s:property value="name"/></option>
																	</s:else>
																</s:iterator>
																</select>
															</s:if>
															<s:else>
																<select disabled="disabled" onchange="saveOutsourceInvestigation(this.value,<s:property value="id"/>)" class="form-control chosen-select" >
																<option value="0">Select Outsource</option>
																<s:iterator value="outSourceList">
																	<s:if test="status==1">
																		<option value="<s:property value="id"/>" selected="selected"><s:property value="name"/></option>
																	</s:if>
																	<s:else>
																		<option value="<s:property value="id"/>"><s:property value="name"/></option>
																	</s:else>
																</s:iterator>
																</select>															
															</s:else>
														</td>
														<!--  11 June 2018  -->
												        <td class="hidden-print" >
												        	<s:if test="deleted==0">
												        		<s:if test="outsource==0">
												        		</s:if>
												        		<s:else>
												        			<a href="#" onclick="openOutSourcePopUp(<s:property value="outsource"/>,<s:property value="id"/>)"><i class="fa fa-outdent fa-2x" aria-hidden="true" style="padding-top: 5px;"></i></a>
												        		</s:else>
												        	</s:if>
												        	<s:else>
												        		
												        	</s:else>
												        </td>
														<td><a href="#" style="display:none;" onclick="editwriteup(<s:property value="id"/>,0,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>')"><i class="fa fa-eye" ></i></a> <a href="#" onclick="openPopup('printInvestigation?selectedid=<s:property value="id"/>&rpt=<s:property value="invstreporttype"/>&sectionid=<s:property value="sectionid"/>&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>&invreq=<s:property value="invreq"/>&paction=r&actprn=1')"  title="Print Report"><i onclick="myFunction(this, '#6719ff')" class="fa fa-print fa-2x" style="color: #555;padding-left: 22px;"></i></a></td>
														
														<%-- <td class=" "><s:property value="invstreporttype" /></td> --%>
														
														
														<%if(loginfo.getUserType()==14){ %>
														    <s:if test="deleted==0">
														   		 <s:if test="status==0">
														      		<td style="cursor: pointer;" class="hidden-print"><a class="fontset" href="cstatusInvestigation?status=1&id=<s:property value="id"/>">NOT APPROVED</a></td>
														    	</s:if> 
														    	<s:else>
														       		<td class="approvedstate hidden-print" style="cursor: pointer;"><a class="fontset" href="cstatusInvestigation?status=0&id=<s:property value="id"/>">APPROVED</a></td>
														   		</s:else>
														    </s:if>
														    <s:else>
														    	<s:if test="status==0">
														      		<td style="text-decoration: line-through;cursor: pointer;" class="hidden-print">NOT APPROVED</td>
														    	</s:if> 
														    	<s:else>
														       		<td class="approvedstate hidden-print" style="text-decoration: line-through;cursor: pointer;">APPROVED</td>
														    	</s:else>
														    </s:else>
														    
														<%} else { %>
															<s:if test="deleted==0">
																<s:if test="collectstatus==1 && upstatus==0">
														      		<s:if test="invstreporttype=='Numerical'">
                                                     			  		<td class="hidden-print"><button class="fontset collectstate" onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')">Collected</button></td>
                                                     		  		</s:if>
                                                     		  		<s:elseif test="invstreporttype=='Hybreed'">
                                                     			  		<td class="hidden-print"><button class="fontset collectstate" onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')">Collected</button></td>
                                                     		  		</s:elseif>
                                                     	      		<s:else>
                                                     					<td class="hidden-print"><button class="fontset collectstate" onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')">Collected</button></td>
                                                     	      		</s:else>
														    		</s:if>
														    	<s:elseif test="status==1">
														      		
														      		<s:if test="invstreporttype=='Numerical'">
														    	    	<td class="hidden-print"><button onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')" class="fontset approvedstate">Approved</button></td>
														    	    </s:if>
														    	    
														    	    <s:elseif test="invstreporttype=='Hybreed'">
														    	    	<td class="hidden-print"><button onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')" class="fontset approvedstate">Approved</button></td>
														    	    </s:elseif>
														    	    <s:else>
														    	    <%if(loginfo.isAyushman()) {%>
                                                     				   <td class="hidden-print"><a href="templateinvInvestigation?selectedid=<s:property value="id"/>&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>&approved=1"><button class="fontset approvedstate" >Approved</button></a></td>
                                                     				
                                                     				<%}else{ %>
																		<td class="hidden-print"><button onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')" class="fontset approvedstate">Approved</button></td>														    	    
														    	    <%} %>
														    	    </s:else>
														      		
														    	</s:elseif>
														    	<s:elseif test="upstatus==0">
														        	<s:if test="invstreporttype=='Numerical'">
														        		<!-- <td style="text-transform: uppercase;"><center>New</center></td> -->
														        		<td class="hidden-print">
														        			<%if(loginfo.isInvst_collect()){ %>
															        			<%if(loginfo.isInvst_inv_apr()){ %>
																        			<s:if test="checkChargeRaised==0">
														        						<button class="fontset newstate" onclick="collectErrorForAddCharge()">Collect</button>
																					</s:if>
																					<s:else>
																						<button class="fontset newstate" onclick="collectData(<s:property value="id"/>,<s:property value="checkChargeRaised"/>)">Collect</button>
																					</s:else>
																				<%}else{ %>
																					<button class="fontset newstate" onclick="collectData(<s:property value="id"/>,<s:property value="checkChargeRaised"/>)">Collect</button>
																				<%} %>											       			 	
												       			 			<%}else{ %>
												       			 				Collect
												       			 			<%} %>
												       			 		</td>
                                                     			  			<!--<td><a href="#" onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')"   title="Update Report"><center>New</center></a></td>
                                                     		  					--></s:if>
                                                     	      		<s:else>
                                                     					<!--<td><a href="#" onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')"   title="Update Report"><center>New</center></a></td>
                                                     	      					-->
                                                     	      			<!-- <td style="text-transform: uppercase;"><center>New</center></td> -->
                                                     	       			<td class="hidden-print">
                                                     	       			<%if(loginfo.isInvst_collect()){ %>
                                                     	       				<%if(loginfo.isInvst_inv_apr()){ %>
															        			<s:if test="checkChargeRaised==0">
													        						<button class="fontset newstate" onclick="collectErrorForAddCharge()">Collect</button>
																				</s:if>
																				<s:else>
																					<button class="fontset newstate" onclick="collectData(<s:property value="id"/>,<s:property value="checkChargeRaised"/>)">Collect</button>
																				</s:else>
																			<%}else{ %>
												        						<button class="fontset newstate" onclick="collectData(<s:property value="id"/>,<s:property value="checkChargeRaised"/>)">Collect</button>
												       			 			<%} %>												       			 		
												       			 		<%}else{ %>
												       			 			Collect
												       			 		<%} %>
												       			 		</td>
                                                     	      		</s:else>
														      		</s:elseif>
														   			<s:else>
														        	<s:if test="invstreporttype=='Numerical'">
                                                     					<td class="hidden-print"><button class="fontset completedstate" onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',1,'<s:property value="update_date" />','<s:property value="specialization"/>')">Completed</button></td>
                                                     				</s:if>
                                                     				<s:elseif test="invstreporttype=='Hybreed'">
                                                     					<td class="hidden-print"><button class="fontset completedstate" onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',1,'<s:property value="update_date" />','<s:property value="specialization"/>')">Completed</button></td>
                                                     				</s:elseif>
                                                     				
                                                     				
                                                     				<s:else>
                                                     				<%if(loginfo.isAyushman()) {%>
                                                     				   <td class="hidden-print"><a href="templateinvInvestigation?selectedid=<s:property value="id"/>&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>&approved=0"><button class="fontset completedstate" <%-- onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',1,'<s:property value="update_date" />','<s:property value="specialization"/>')" --%>>Completed</button></a></td>
                                                     				
                                                     				<%}else{ %>
                                                     					<td class="hidden-print"><button class="fontset completedstate"  onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',1,'<s:property value="update_date" />','<s:property value="specialization"/>')" >Completed</button></td>
                                                     				<%} %>
                                                     				</s:else>
														   		</s:else>
															</s:if>
														    <s:else>
														    		<s:if test="collectstatus==1 && upstatus==0">
														      		<s:if test="invstreporttype=='Numerical'">
                                                     			  		<td class="hidden-print" style="text-decoration: line-through;cursor: default;"><button class="fontset collectstate" ><span style="text-decoration: line-through;cursor: default;">Collected</span></button></td>
                                                     		  		</s:if>
                                                     		  		<s:elseif test="invstreporttype=='Hybreed'">
                                                     			  		<td class="hidden-print" style="text-decoration: line-through;cursor: default;"><button class="fontset collectstate" ><span style="text-decoration: line-through;cursor: default;">Collected</span></button></td>
                                                     		  		</s:elseif>
                                                     	      		<s:else>
                                                     					<td class="hidden-print" ><button class="fontset collectstate"><span style="text-decoration: line-through;cursor: default;">Collected</span></button></td>
                                                     	      		</s:else>
														    		</s:if>
														    	<s:elseif test="status==1">
														    	  <s:if test="invstreporttype=='Numerical'">
														    	    	<td class="hidden-print"><button onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')" class="fontset approvedstate">Approved</button></td>
														    	    </s:if>
														    	     <s:if test="invstreporttype=='Hybreed'">
														    	    	<td class="hidden-print"><button onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')" class="fontset approvedstate">Approved</button></td>
														    	    </s:if>
														    	    <s:else>
														    	    	
																		<td class="hidden-print"><button onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')" class="fontset approvedstate">Approved</button></td>														    	    
														    	
														    	    </s:else>
														    	</s:elseif>
														    	<s:elseif test="upstatus==0">
														        	<s:if test="invstreporttype=='Numerical'">
														        		<!-- <td style="text-transform: uppercase;"><center>New</center></td> -->
														        		<td class="hidden-print" >
												        					<button class="fontset newstate"><span style="text-decoration: line-through;cursor: default;">Collect</span></button>
												       			 		</td>
                                                     			  			<!--<td><a href="#" onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')"   title="Update Report"><center>New</center></a></td>
                                                     		  					--></s:if>
                                                     	      		<s:else>
                                                     					<!--<td><a href="#" onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')"   title="Update Report"><center>New</center></a></td>
                                                     	      					-->
                                                     	      			<!-- <td style="text-transform: uppercase;"><center>New</center></td> -->
                                                     	       			<td class="hidden-print" >
												        					<button class="fontset newstate" ><span style="text-decoration: line-through;cursor: default;">Collect</span></button>
												       			 		</td>
                                                     	      		</s:else>
														      		</s:elseif>
														   			<s:else>
														        	<s:if test="invstreporttype=='Numerical'">
                                                     					<td class="hidden-print"><button class="fontset completedstate"><span style="text-decoration: line-through;cursor: default;">Completed</span></button></td>
                                                     				</s:if>
                                                     				<s:else>
                                                     					<td class="hidden-print"><button class="fontset completedstate" ><span style="text-decoration: line-through;cursor: default;">Completed</span></button></td>
                                                     				</s:else>
														   		</s:else>
														    
														    </s:else>
														<%} %>
														
														
														<td class="hidden-print">
															<s:if test="deleted==0">
																	<s:if test="checkChargeRaised==0">
																<a href="#" onclick="showAddchargePopupinv(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>','<s:property value="fullname"/>','<s:property value="whopay"/>',<s:property value="invreq"/>,'<s:property value="wardname"/>','<s:property value="selftp"/>')" style="color: #555;font-weight: bold;" title="Add Charges" id="addchargebtn" class="hidedd<s:property value="invreq"/>"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
															</s:if>
															<s:else>
																<s:if test="checkInvoiceCreated==0">
																	<s:form action="Accounts" id="accountchargefrm1" target="formtarget">
																		<s:hidden name="clientId" id="accountChargeClientid1"/>
																		<s:hidden name="thirdParty" id="accountchargethirdparty1"/>
																		<s:hidden name="transactionType" id="accountchargetransactionType1"/>
																		<s:hidden name="location" id="accountsLocationid1"/>
																		<s:hidden name="client" id="accountclientid1"/>
																		<s:hidden name = "payby" id ="accountpayby1"></s:hidden>
																		<a href="#" onclick="redirectToCreateCharge1(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>','<s:property value="fullname"/>','<s:property value="whopay"/>')" style="color: #555;font-weight: bold;">Charge Added</a>
																	</s:form>
																</s:if>
																<s:else>
																	<s:form action="ProcessingAccount" id="accountpaymentfrm2" method="post" 
								              							 target="formtarget">
								              							
																		<s:hidden name="clientId" id="accountpaymentClientid2"/>
																		<s:hidden name="thirdParty" id="accountpaymentthirdparty2"/>
																		<s:hidden name="transactionType" id="accountpaymenttransactionType2"/>
																		<s:hidden name="location" id="accountspaymentLocationid2"/>
																		<s:hidden name="client" id="accountpaymentclientid2"/>
																		<s:hidden name="fromDate" id="accountspaymentfromDateid2"/>
																		<s:hidden name="toDate" id="accountspaymenttoDateid2"/>
																		<s:hidden name = "payby" id ="accountPaymentPayby2"></s:hidden>
																		<%if(loginfo.isParihar()){ %>
																		<s:if test="balance>0">
																	   <a href="#" onclick="redirectToRecordPayment2(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>','<s:property value="fullname"/>','<s:property value="whopay"/>')" style="color: #555;font-weight: bold;">Balance(<s:property value="balance"/>)</a>

																		</s:if><s:else>
																		<a href="#" onclick="redirectToRecordPayment2(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>','<s:property value="fullname"/>','<s:property value="whopay"/>')" style="color: #555;font-weight: bold;">Paid</a>
																	</s:else><%} else{ %>
															        <a href="#" onclick="redirectToRecordPayment2(<s:property value="id"/>,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>','<s:property value="fullname"/>','<s:property value="whopay"/>')" style="color: #555;font-weight: bold;">Paid</a>
																	
																	<%} %>
																	</s:form>
																</s:else>
																</s:else>
															</s:if>
															<s:else>
																	<s:if test="checkChargeRaised==0">
																			<span style="text-decoration: line-through;cursor: default;">Add Charges</span>
																	</s:if>
																	<s:else>
																	<s:if test="checkInvoiceCreated==0">
																			<span style="text-decoration: line-through;cursor: default;">Charge Added</span>
																	</s:if>
																	<s:else>
																			<span style="text-decoration: line-through;cursor: default;">Paid</span>
																	</s:else>
															</s:else>		
															</s:else>
														</td>
														
														
												        <%-- <td>
												        <s:if test="upstatus==0"><s:if test="collectstatus!=1"> <a href="#" onclick="collectData(<s:property value="id"/>)" style="background-color: darkcyan;color: #fff;padding: 1px 3px 2px 3px;">Collect</a></s:if></s:if>
												        </td> --%>
												        
												        <td class="hidden-print">
												        	<s:if test="deleted==0">
												        		<span><s:if test="invstreporttype=='Numerical'">
																	<a href="#" style="display:none;" onclick="showupdatepopup(<s:property value="id"/>,0,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>')"><i class="fa fa-eye" ></i></a> <a href="#" onclick="openBlankPopup('printInvestigation?selectedid=<s:property value="id"/>&rpt=<s:property value="invstreporttype"/>&sectionid=<s:property value="sectionid"/>&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>&invreq=<s:property value="invreq"/>&paction=p')"  title="Print Report"><i onclick="myFunction(this, '#6719ff')" class="fa fa-print fa-2x" style="color: #555;"></i></a>
	                                                     		</s:if>
	                                                     		<s:else>
	                                                     			<a href="#" style="display:none;" onclick="editwriteup(<s:property value="id"/>,0,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>')"><i class="fa fa-eye" ></i></a> <a href="#" onclick="openPopup('printInvestigation?selectedid=<s:property value="id"/>&rpt=<s:property value="invstreporttype"/>&sectionid=<s:property value="sectionid"/>&fromdate=<s:property value="fromdate"/>&todate=<s:property value="todate"/>&invreq=<s:property value="invreq"/>&paction=p')"  title="Print Report"><i onclick="myFunction(this, '#6719ff')" class="fa fa-print fa-2x" style="color: #555;"></i></a> 
	                                                     		</s:else> 
	                                                     		</span>
												        	</s:if>
												        	<s:else>
												        		<%-- <span><s:if test="invstreporttype=='Numerical'">
																<i class="fa fa-eye" ></i></a> <center><i class="fa fa-print" style="color: #555;"></i></center>
	                                                     		<td class="hidden"><a href="#" onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>')"   title="Update Report"><center><i class="fa fa-pencil-square-o" ></i></center></a></td>
	                                                     		</s:if>
	                                                     		<s:else>
	                                                     			<i class="fa fa-eye" ></i><center><i class="fa fa-print" style="color: #555;"></i></center> 
	                                                     			<td class="hidden"><a href="#" onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>')"   title="Update Report"><center><i class="fa fa-pencil-square-o" ></i></center></a></td>
	                                                     		</s:else> 
	                                                     		</span> --%>
	                                                     		<span><s:if test="invstreporttype=='Numerical'">
																	<i onclick="myFunction(this, '#6719ff')" class="fa fa-print fa-2x" style="color: #555;"></i>
	                                                     		</s:if>
	                                                     		<s:else>
	                                                     			<i onclick="myFunction(this, '#6719ff')" class="fa fa-print fa-2x" style="color: #555;"></i> 
	                                                     		</s:else> 
	                                                     		</span>
												        	</s:else>
												        </td>
												        <td class="hidden-print">
												        	<s:if test="deleted==0">
												        		<span class="" onclick="sendinvstigationLetter('<s:property value="fullname"/>',<s:property value="clientId"/>)"><a href="#"><i class="fa fa-envelope-o fa-2x" style="color: #555;"></i></a></span>
												        	</s:if>
												        	<s:else>
																<span class=""><i class="fa fa-envelope-o fa-2x" style="color: #555;"></i></span>
												        	</s:else>
												        </td>
												        <td class="hidden-print">
												         <s:if test="deleted==0">
												        	<span><a href="#" onclick="setsmsuser(<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="fullname"/>','<s:property value="practitionerName"/>','<s:property value="practitionerMob"/>')"><i class="fa fa-commenting fa-2x" aria-hidden="true" style="color: #555;"></i></a></span>
												        </s:if>
												        <s:else>
															<span><i class="fa fa-commenting fa-2x" aria-hidden="true" style="color: #555;"></i></span>
												        </s:else>
												        </td>
												        <td class="hidden-print">
												        <s:if test="deleted==0">
												        	<span><a href="#" style="text-transform: uppercase;" onclick="uploadfilepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')"   title="Update Report"><i class="fa fa-cloud-upload fa-2x" style="color: #555;" aria-hidden="true"></i></a></span>
												        </s:if>
												        <s:else>
															<span><i class="fa fa-cloud-upload fa-2x" style="color: #555;" aria-hidden="true"></i></span>
												        </s:else>
												        </td>
												        <td class="hidden-print"><span><s:if test="isAttachment==1"><i class="fa fa-paperclip fa-2x"></i></s:if><s:else></s:else><span></td>
												        <td style="text-align: center;" class="hidden-print">
														<s:if test="upstatus==0">
														   <%-- <a href="deleteInvestigation?selectedid=<s:property value="id"/>" onclick="return cnfmdelete()"><i class="fa fa-times text-danger"></i></a> --%>
															<s:if test="collectstatus==0">
																<s:if test="deleted==0">
																	<%-- <a href="#" onclick="cancelIvestigation(<s:property value="id"/>)"><i class="fa fa-times fa-2x text-danger" aria-hidden="true"></i></a> --%>
																	<a href="#" onclick="cancelIvestigation1(<s:property value="id"/>,'<s:property value="invreq"/>','<s:property value="abrivationid"/>','<s:property value="fullname" />','<s:property value="invsttype" />')"><i class="fa fa-times fa-2x text-danger" aria-hidden="true"></i></a>
																</s:if>
															</s:if>
														</s:if>
												        </td> 
												        
												        <td><a href="#" title="Resent to elab" onclick="eresend(<s:property value="clientId"/>,<s:property value="invreq"/>)">R<a></a></td>
													
													<%if(loginfo.getIskunal()==1){ %>
		                                          		<th class="hidden-print"><a href="#" onclick="opendtchngepopup('<s:property value="id" />','<s:property value="date" />')"><i class='fa fa-edit'></i></a></th>
		                                          	<%} %>
		                                          
		                                          	<th class="hidden-print"><a href="#" title="Multiple Investigation"  onclick="openPopup('multiinvsthandleInvestigation?clientid=<s:property value="clientId"/>')">M</a></th>
		                                         
		                                         <%if(loginfo.getJobTitle().equals("Admin")){ %>
		                                         <td style="text-align: center;">
		                                         	<s:if test="collectstatus!=0">
													<s:if test="deleted==0">
		                                          		<a href="#" onclick="cancelIvestigation1(<s:property value="id"/>,'<s:property value="invreq"/>','<s:property value="abrivationid"/>','<s:property value="fullname" />','<s:property value="invsttype" />')"><i class="fa fa-times fa-2x text-danger" aria-hidden="true"></i></a>
		                                          	</s:if>
		                                          </s:if>
		                                          </td>
		                                          <%} %>
		                                         
													</tr>
													
													
													
												</s:iterator>

											</tbody>
										</table>
										</div>
										
										
										<!-- For print -->
										<div class="scrolltable hidden"  id="page_printer2">
										<table class="table table-bordered table-striped table-responsive table-hover xlstable" style="text-transform: uppercase;" >
											<thead>
												<tr class="headings">
												<td style="width: 9%;">sr no</td>
													<td style="width: 9%;">Requested</td>
													<td style="width: 18%;">I.ID | Req. ID | UHID</td>
													<td style="width: 30%;">Patient Details</td>
													<td style="width: 29%;">Investigation</td>
														<td style="width: 29%;">Status</td>
														<td style="width: 29%;">Address</td>
												</tr>
											</thead>
											<tbody>
											<% int i=0;%>
												<s:iterator value="investigationList">
												<s:if test="deleted==0">
													<tr><td><%=++i %></td>
														<td > <s:property value="date" /></td>
														<td ><s:property value="id" /> | <s:property value="invreq"/> | <s:property value="abrivationid"/></td>
														<td>
															<s:property value="fullname" /> | <s:if test="ipdid!=0"><s:property value="wardname" /> / <s:property value="bedname" /></s:if>
														 </td>
														<td>
															<s:property value="invsttype" />
														</td>
													
														<%if(loginfo.getUserType()==14){ %>
														    <s:if test="deleted==0">
														   		 <s:if test="status==0">
														      		<td style="cursor: pointer;" class=""><a class="fontset" href="cstatusInvestigation?status=1&id=<s:property value="id"/>">NOT APPROVED</a></td>
														    	</s:if> 
														    	<s:else>
														       		<td class="approvedstate " style="cursor: pointer;"><a class="fontset" href="cstatusInvestigation?status=0&id=<s:property value="id"/>">APPROVED</a></td>
														   		</s:else>
														    </s:if>
														    <s:else>
														    	<s:if test="status==0">
														      		<td style="text-decoration: line-through;cursor: pointer;" class="">NOT APPROVED</td>
														    	</s:if> 
														    	<s:else>
														       		<td class="approvedstate " style="text-decoration: line-through;cursor: pointer;">APPROVED</td>
														    	</s:else>
														    </s:else>
														    
														<%} else { %>
															<s:if test="deleted==0">
																<s:if test="collectstatus==1 && upstatus==0">
														      		<s:if test="invstreporttype=='Numerical'">
                                                     			  		<td class="">Collected</td>
                                                     		  		</s:if>
                                                     	      		<s:else>
                                                     					<td class="">Collected</td>
                                                     	      		</s:else>
														    		</s:if>
														    	<s:elseif test="status==1">
														      		
														      		<s:if test="invstreporttype=='Numerical'">
														    	    	<td class="">Approved</td>
														    	    </s:if>
														    	    <s:else>
																		<td class="">Approved</td>														    	    
														    	    </s:else>
														      		
														    	</s:elseif>
														    	<s:elseif test="upstatus==0">
														        	<s:if test="invstreporttype=='Numerical'">
														        		<!-- <td style="text-transform: uppercase;"><center>New</center></td> -->
														        		<td class="">
												        					Collect
												       			 		</td>
                                                     			  			<!--<td><a href="#" onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')"   title="Update Report"><center>New</center></a></td>
                                                     		  					--></s:if>
                                                     	      		<s:else>
                                                     					<!--<td><a href="#" onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')"   title="Update Report"><center>New</center></a></td>
                                                     	      					-->
                                                     	      			<!-- <td style="text-transform: uppercase;"><center>New</center></td> -->
                                                     	       			<td class="">
												        					Collect
												       			 		</td>
                                                     	      		</s:else>
														      		</s:elseif>
														   			<s:else>
														        	<s:if test="invstreporttype=='Numerical'">
                                                     				<td>	Completed</td>
                                                     				</s:if>
                                                     				<s:else>
                                                     					<td class="">Completed</td>
                                                     				</s:else>
														   		</s:else>
															</s:if>
														    <s:else>
														    		<s:if test="collectstatus==1 && upstatus==0">
														      		<s:if test="invstreporttype=='Numerical'">
                                                     			  		<td class="" style="text-decoration: line-through;cursor: default;">Collected</td>
                                                     		  		</s:if>
                                                     	      		<s:else>
                                                     					<td class="" >Collected</td>
                                                     	      		</s:else>
														    		</s:if>
														    	<s:elseif test="status==1">
														    	  <s:if test="invstreporttype=='Numerical'">
														    	    	<td class="">Approved</td>
														    	    </s:if>
														    	    <s:else>
																		<td class="">Approved</td>														    	    
														    	    </s:else>
														    	</s:elseif>
														    	<s:elseif test="upstatus==0">
														        	<s:if test="invstreporttype=='Numerical'">
														        		<!-- <td style="text-transform: uppercase;"><center>New</center></td> -->
														        		<td class="" >
												        					Collect
												       			 		</td>
                                                     			  			<!--<td><a href="#" onclick="showupdatepopup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,0,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')"   title="Update Report"><center>New</center></a></td>
                                                     		  					--></s:if>
                                                     	      		<s:else>
                                                     					<!--<td><a href="#" onclick="editwriteup(<s:property value="id"/>,1,<s:property value="clientId"/>,<s:property value="prectionerid"/>,'<s:property value="practitionerName"/>',0,'<s:property value="update_date" />','<s:property value="specialization"/>')"   title="Update Report"><center>New</center></a></td>
                                                     	      					-->
                                                     	      			<!-- <td style="text-transform: uppercase;"><center>New</center></td> -->
                                                     	       			<td class="" >
												        					Collect
												       			 		</td>
                                                     	      		</s:else>
														      		</s:elseif>
														   			<s:else>
														        	<s:if test="invstreporttype=='Numerical'">
                                                     					<td class="">Completed</td>
                                                     				</s:if>
                                                     				<s:else>
                                                     					<td class="">Completed</td>
                                                     				</s:else>
														   		</s:else>
														    
														    </s:else>
														<%} %>
														
													 <td > <s:property value="address" /></td>
													</tr>
													</s:if>
												</s:iterator>

											</tbody>
										</table>
										</div>
										
								</div>
							</div>
						</div>
					
					
				
				
				<s:form action="Investigation" name="paginationForm" id="paginationForm"
	theme="simple">
    <s:hidden name="fromdate"></s:hidden>
    <s:hidden name="todate"></s:hidden>
    <s:hidden name="invstsecid"></s:hidden>
    <s:hidden name="invsttype"></s:hidden>
    <s:hidden name="filter_status"></s:hidden>
    <s:hidden name="filter_ward"></s:hidden>
    <s:hidden name="searchText"></s:hidden>
    <s:hidden name="outsource"></s:hidden>
	<div class="col-lg-12 col-xs-12" style="padding:0px;margin-top:15px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>

</s:form> 
			<!-- page end-->
		</section>
	
	<!-- /MAIN CONTENT -->
	<!--main content end-->

	
	
	
	
	 <!-- Update Numerical Investigation -->
	 
	 <s:form action="updaterportInvestigation" id="upidreport" theme="simple" cssClass="notranslate">
	  <s:hidden name="fromdate"></s:hidden>
	  <input  type="hidden" id="approved" name="approved" />
        <s:hidden name="todate"></s:hidden>
         <s:hidden name="searchText" ></s:hidden>
      <div class="modal fade" id="investedit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog modal-lg" role="document">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h5 class="modal-title" id="myModalLabel">Update Report Investigation For <b class="pname" id="ninvstlableemodel">NAME: </b></h5>
                  </div>
                  <div class="modal-body" id="pop-height">
                  
                   <div class="row hidden">
                         <link href="common/css/printpreview.css" rel="stylesheet" />
						<%@ include file="/accounts/pages/letterhead.jsp" %>
                      </div>

                     
                      <div class=" ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: #f5f5f5;padding: 5px 0px 0px 5px;">
                              <form>
                                 
                                  <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 marleft9">
                                      <div class="form-group">
                                          <b>I.ID</b>
                                         <p id="numericalinvstrefid">AS152325</p>
                                      </div>
                                  </div>
                                   <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 marleft9">
                                      <div class="form-group">
                                          <b>Req. Id</b>
                                         <p id="reqiid">AS152325</p>
                                      </div>
                                  </div>
								<div class="col-lg-2 col-md-2 col-xs-4 col-sm-4">
                                    <div class="form-group">
                                        <b> Requested Date </b>
                                        <p style="margin:0px;"><span id="ndatetimeid"></span></p>
                                    </div>
                                </div>
                                  <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Doctor Incharge</b>
                                          <p><span id="npriscdoctornameid"></span> (<span id="deptid"></span>)</p>
                                      </div>
                                  </div>
                                <div class="col-lg-2 col-md-2 col-xs-4 col-sm-4">
                                    <div class="form-group">
                                        <b>Completed Date</b>
                                        <p style="margin:0px;"><span id="cmpdate"></span></p>
                                    </div>
                                </div>
                                  <div class="col-lg-2 col-md-2 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Select Values</b>
                                          <s:select onchange="updatecbc(this.value)" name="cbcrefid" id="cbcrefid" list="cbcIdList"
                                          listKey="id" listValue="cbcid" cssClass="form-control showToolTip chosen"
                                          headerKey="0" headerValue="Select CBC"/>
                                      </div>
                                  </div>
                              </form>
                          </div>
                      </div>

                      <div class="row">

                          <!--<button class="btn btn-primary Addtext"><i class="fa fa-plus"></i></button>-->
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">

                              <h3 class="" id="invrportname" style="margin: 5px 0px 5px 0px;text-align: center;color: #a94442;">Investigation Report</h3>
                              <table class="table table-bordered" cellspacing="0" width="100%">
                                 
                                  <thead>
                                      <tr class="tableback">

                                          <th class="invest27">Investigation</th>
                                          <th class="invest23">Test Name</th>
                                          <th class="invest8">Obs. Value</th>
                                          <th class="invest9"></th>
                                          <th class="invest9">Normal Value</th>
                                          <th class="invest6 hidden">Unit</th>
                                          

                                      </tr>
                                  </thead>
                                  <tbody id="rporteditinvsttable">
                                    
                                  </tbody>
                              </table>
                              
                              <div class="col-lg-12 col-md-12 col-xs-12">
                              	<div class="col-lg-2 col-md-2 col-xs-2 form-group">
                              	
								  <label for="comment">Remark:</label></div>
								  	
								  	 <div class="col-lg-3 col-md-3 col-xs-3 form-group"> 
								  <s:select cssClass="form-control chosen-select" list="Invst_templt_list"  listKey="id"  listValue="name" onchange="setInvestigationtemplate(this.value)" headerKey="0" headerValue="Select Template">
								    </s:select></div>
								    <div class="col-lg-3 col-md-3 col-xs-3 form-group"> 
								  				<input type="text" name="investigationtempname" class="form-control setbackcolor" placeholder="Enter template name">  </div>
						        
								  <s:hidden name="commentwriteupappr" id="commentwriteupappr"></s:hidden>
								     <%--   <% ArrayList<Master> masterInvstTypeList1 = (ArrayList<Master>)session.getAttribute("masterInvstTypeList"); %>
                          <% if(masterInvstTypeList1!=null){ Master mast= masterInvstTypeList1.get(0);
                            Investigation inv= mast.getSelectedInvstList().get(0); %> 
                            <%if(mast.getName()!=null){ %>
                               <%if(loginInfo1.getClinicUserid().equals("aureus")&&(mast.getName().equals("COMPLETE BLOOD COUNT ( CBC ) ")||mast.getName().equals("COMPLETE BLOOD COUNT ( CBC ) (Dr. Lalit Raut)"))){%> 
                               
                               <% if(mast.getName().equals("COMPLETE BLOOD COUNT ( CBC ) ")){%>
                            	<br><div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                            	
                           	 <p><b>PERIPHERAL SMEAR:</b></p>
                           	 <p><b>RBC's:</b> Preorminnantly Normocytic </p>
                           	 <p>Normochromic</p>
                           	 <p><b>WBC's</b>- As mentioned Below</p>
                           	 <p><b>Platletes </b>- Adequate</p>
                           </div>
                          <%  }} }%>  --%>
								  <textarea class="form-control" rows="5" id="comment" name="comment" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
								  
								</div>
                              </div>

                          </div>
                         

                      </div>
                 
                  <div class="modal-footer">
                    <!--   <a href ="investigation_print.html" target="_blank" type="button" class="btn btn-primary"><i class="fa fa-print"></i> Print</a> -->
                    <%-- <%if(loginfo.isInvestigation_approve()){ %>
                    	<input type="button" id="btnapproved" onclick="updateApprove()" class="btn btn-success" value="Approved">
                    <%} %> --%>
                    <input type="button" id="btnapproved" onclick="updateApprove()" class="btn btn-success" value="Approved">
                    <input type="submit" class="btn btn-primary" value="Save" onclick="setremark()">
                  </div>
                  </div>
              </div>
          </div>
   
	</s:form>
	
	
	
	
	 <s:form action="updaterportInvestigation" id="upidreport1" theme="simple" cssClass="notranslate">
	  <s:hidden name="fromdate"></s:hidden>
	  <input  type="hidden" id="approved1" name="approved1" />
        <s:hidden name="todate"></s:hidden>
         <s:hidden name="searchText" ></s:hidden>
      <div class="modal fade" id="investedit1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog modal-lg" role="document">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h5 class="modal-title" id="myModalLabel">Update Report Investigation For <b class="pname" id="ninvstlableemodel">NAME: </b></h5>
                  </div>
                  <div class="modal-body" id="pop-height">
                  
                   <div class="row hidden">
                         <link href="common/css/printpreview.css" rel="stylesheet" />
						<%@ include file="/accounts/pages/letterhead.jsp" %>
                      </div>

                     
                      <div class=" ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="background-color: #f5f5f5;padding: 5px 0px 0px 5px;">
                              <form>
                                 
                                  <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 marleft9">
                                      <div class="form-group">
                                          <b>I.ID</b>
                                         <p id="numericalinvstrefid">AS152325</p>
                                      </div>
                                  </div>
                                   <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 marleft9">
                                      <div class="form-group">
                                          <b>Req. Id</b>
                                         <p id="reqiid1">AS152325</p>
                                      </div>
                                  </div>
								<div class="col-lg-2 col-md-2 col-xs-4 col-sm-4">
                                    <div class="form-group">
                                        <b> Requested Date </b>
                                        <p style="margin:0px;"><span id="ndatetimeid"></span></p>
                                    </div>
                                </div>
                                  <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Doctor Incharge</b>
                                          <p><span id="npriscdoctornameid"></span> (<span id="deptid"></span>)</p>
                                      </div>
                                  </div>
                                <div class="col-lg-2 col-md-2 col-xs-4 col-sm-4">
                                    <div class="form-group">
                                        <b>Completed Date</b>
                                        <p style="margin:0px;"><span id="cmpdate"></span></p>
                                    </div>
                                </div>
                                  <div class="col-lg-2 col-md-2 col-xs-4 col-sm-4">
                                      <div class="form-group">
                                          <b>Select Values</b>
                                          <s:select onchange="updatecbc(this.value)" name="cbcrefid1" id="cbcrefid1" list="cbcIdList"
                                          listKey="id" listValue="cbcid" cssClass="form-control showToolTip chosen"
                                          headerKey="0" headerValue="Select CBC"/>
                                      </div>
                                  </div>
                              </form>
                          </div>
                      </div>

                      <div class="row">

                          <!--<button class="btn btn-primary Addtext"><i class="fa fa-plus"></i></button>-->
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">

                              <h3 class="" id="invrportname1" style="margin: 5px 0px 5px 0px;text-align: center;color: #a94442;">Investigation Report</h3>
                              <table class="table table-bordered" cellspacing="0" width="100%">
                                 
                                  <thead>
                                      <tr class="tableback">

                                          <th class="invest27">Investigation</th>
                                          <th class="invest23">Test Name</th>
                                          <th class="invest8">1/40</th>
                                          <th class="invest9">1/80</th>
                                          <th class="invest9">1/160</th>
                                          <th class="invest6">1/320</th>
                                          

                                      </tr>
                                  </thead>
                                  <tbody id="rporteditinvsttable1">
                                    
                                  </tbody>
                              </table>
                              <div class="col-lg-12 col-md-12 col-xs-12">
                              	<div class="form-group">
								  <label for="comment">Remark:</label>
								  
								  <s:hidden name="commentwriteupappr1" id="commentwriteupappr1"></s:hidden>
								     <%--   <% ArrayList<Master> masterInvstTypeList1 = (ArrayList<Master>)session.getAttribute("masterInvstTypeList"); %>
                          <% if(masterInvstTypeList1!=null){ Master mast= masterInvstTypeList1.get(0);
                            Investigation inv= mast.getSelectedInvstList().get(0); %> 
                            <%if(mast.getName()!=null){ %>
                               <%if(loginInfo1.getClinicUserid().equals("aureus")&&(mast.getName().equals("COMPLETE BLOOD COUNT ( CBC ) ")||mast.getName().equals("COMPLETE BLOOD COUNT ( CBC ) (Dr. Lalit Raut)"))){%> 
                               
                               <% if(mast.getName().equals("COMPLETE BLOOD COUNT ( CBC ) ")){%>
                            	<br><div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
                            	
                           	 <p><b>PERIPHERAL SMEAR:</b></p>
                           	 <p><b>RBC's:</b> Preorminnantly Normocytic </p>
                           	 <p>Normochromic</p>
                           	 <p><b>WBC's</b>- As mentioned Below</p>
                           	 <p><b>Platletes </b>- Adequate</p>
                           </div>
                          <%  }} }%>  --%>
								  <textarea class="form-control" rows="5" id="comment1" name="comment1" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
								  
								</div>
                              </div>

                          </div>
                         

                      </div>
                  </div>
                  <div class="modal-footer">
                    <!--   <a href ="investigation_print.html" target="_blank" type="button" class="btn btn-primary"><i class="fa fa-print"></i> Print</a> -->
                    <%-- <%if(loginfo.isInvestigation_approve()){ %>
                    	<input type="button" id="btnapproved" onclick="updateApprove()" class="btn btn-success" value="Approved">
                    <%} %> --%>
                    <input type="button" id="btnapproved2" onclick="updateApprove()" class="btn btn-success" value="Approved">
                    <input type="submit" class="btn btn-primary" value="Save" onclick="setremark()">
                  </div>
              </div>
          </div>
      </div>
	</s:form>
	
	
	
	<form action="Investigation"  name="refreshform" method="post">
	</form>
	
	
	
	
	    <!-- Update Write-up Investigation -->
	 <s:form theme="simple" id="writeupfrm" cssClass="notranslate" action="savefindingsInvestigation">
      <div class="modal fade modal-draggable" id="investwrite" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <s:hidden name="hdnselectedid" id="hdnselectedid"></s:hidden>
	  <s:hidden name="fromdate" id="fromdate3"></s:hidden>
	  <s:hidden name="todate" id="todate3"></s:hidden>
	  <s:hidden name="searchText" id="searchText3"></s:hidden>
	  <s:hidden name="approved1" id="approved1"></s:hidden>
        
          <div class="modal-dialog modal-lg" role="document">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h5 class="modal-title" id="myModalLabel" style="color:#fff;">Update Report Investigation For <b class="pname" id="winvstlableemodel">NAME: </b></h5>
                  </div>
                 <!--  <div class="modal-body" style="height: 500px;overflow: scroll;"> -->
                  <div class="modal-body">
                  <div class="row hidden">
                        <link href="common/css/printpreview.css" rel="stylesheet" />
						<%@ include file="/accounts/pages/letterhead.jsp" %>
                      </div>

                      <div class="row ">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                              <form>
                                  
                                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                                  		<div class="col-lg-1 col-md-1 col-xs-2 col-sm-2">
                                  	  <div class="form-group">
                                        <b>I.ID</b>
                                         <p><span id="writupinvstrefno"> 237 </span></p>
	                                    </div>
	                                </div>
	                                <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
                                  	  <div class="form-group">
                                        <b>Req. ID</b>
                                         <p><span id="wreqiid"> 237 </span></p>
	                                    </div>
	                                </div>
	                                <div class="col-lg-2 col-md-2 col-xs-4 col-sm-4">
                                  	  <div class="form-group">
                                        <b>Patient Name</b>
                                         <p><span id="wrname"> 237 </span></p>
	                                    </div>
	                                </div>
	                                <div class="col-lg-1 col-md-1 col-xs-4 col-sm-4 hidden">
                                  	  <div class="form-group">
                                        <b>Age / Sex</b>
                                         <p><span id="wrage"> 237 </span> / <span id="wrgender"></span></p>
	                                    </div>
	                                </div>
	                                <div class="col-lg-2 col-md-2 col-xs-4 col-sm-4">
                                  	  <div class="form-group">
                                        <b>Sample Recived</b>
                                         <p><span id="wdatetimeid"> 237 </span></p>
	                                    </div>
	                                </div>
	                                <div class="col-lg-2 col-md-2 col-xs-3 col-sm-3">
                                  	  <div class="form-group">
                                        <b>Req. Doctor</b>
                                         <p><span id="wpriscdoctornameid"> 237 </span> (<span id="deptid1"> 237 </span>)</p>
	                                    </div>
	                                </div>
	                                <div class="col-lg-4 col-md-4 col-xs-5 col-sm-5">
                                  	  <div class="form-group" id="templatediv" style="display: none;" >
                                          <b>Select Template</b>
                                          <select class="form-control chosen-select">
                                          	<option>Select Template</option>
                                          </select>
                                         
                                      </div>
	                                </div>
                                      
                                  </div> 
                                 
                                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
                                     
                                     
                                      <div class="col-lg-3 col-md-3 col-xs-4 col-sm-4">
                                  	  <div class="form-group">
                                        <b>Completed Date</b>
                                        <p style="margin:0px;"><span id="cmpdate1"></span></p>
                                    </div>
                                </div>
                                  </div>
                                  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
                                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="margin-left:-15px;">
                                         <%--  <p><strong>Sample Drawn Time</strong>: 11:00 AM</p> --%>
                                      </div>
                                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                         <%--  <p><strong>Received Time</strong>: 11:07 AM</p> --%>
                                      </div>
                                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                          
                                      </div>
                                  </div>
                              </form>
                          </div>
                      </div>

                      <div class="row">
                          <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">


                              <h3 class="wirteptext text-center" style="margin-top: 0px;" id="htestname">Investigation Report</h3>
                              <table class="table table-bordered" cellspacing="0" width="100%">
                                  <thead>
                                      <tr class="tableback">

                                          <th class="writewid">Investigation</th>
                                          <th class="writewid">Test Name</th>
                                          <th class="writewid40">Findings</th>
                                         <!--  <th class="writewid">Biological Ref. Range</th> -->
                                          <th class="">Method</th>

                                      </tr>
                                  </thead>
                                  <tbody id="writeuprporteditinvsttable">
                                    
                                  </tbody>	
                              </table>
							

								 <div class="col-lg-12 col-md-12 col-xs-12" id="divothertemplate" style="display: none;" >
                              	<div class="form-group">
                              		<s:textarea style="height: 250px;" rows="10" cols="8" id="templatesec" name="templatesec"
											cssClass="showToolTip form-control" data-toggle="tooltip"
											title="Enter Other Template Text" placeholder="Enter Other Template Text" ></s:textarea>
                              	</div>
                              </div>
                               <div class="col-lg-12 col-md-12 col-xs-12">	
							<div class="form-group">
								  <label for="comment">Impression:</label>	</div>
								  	 <div class="col-lg-3 col-md-3 col-xs-3 form-group"> 
								  <s:select cssClass="form-control chosen-select" list="Invst_templt_list"  listKey="id"  listValue="name" onchange="setInvestigationtemplate1(this.value)" headerKey="0" headerValue="Select Template">
								    </s:select></div>
								    <div class="col-lg-3 col-md-3 col-xs-3 form-group"> 
								  				<input type="text" name="investigationtempname" class="form-control setbackcolor" placeholder="Enter template name">  </div>
						        
	 							 <textarea class="form-control" rows="5" id="commentwriteup" name="commentwriteup" style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
								  
							
                              </div>
                              

                          </div>
                          
                          
                           <s:form theme="simple" id="form7" action="savetextfindingsInvestigation" >
                           
                                     <s:hidden name="finding" id="tfinding"></s:hidden>
                                     <s:hidden name="selectedid" id="tselectedid"></s:hidden>
                                     <s:hidden name="fromdate" id="fromdate1"></s:hidden>
                                     <s:hidden name="todate" id="todate1"></s:hidden>
                                     <s:hidden name="searchText" id="searchText21"></s:hidden>
                                     <s:hidden name="impresssiontext" id="impresssiontext"></s:hidden>
                                     
                           
                           </s:form>
                          
                          
                      </div>
                  </div>
                  <div class="modal-footer">
                      <!-- <a href="investigation_printwriteup.html" target="_blank" type="button" class="btn btn-primary"><i class="fa fa-print"></i> Print</a> -->
                      	<!-- 	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="text-align:left;">
                      			Attached File: <div id="attachdfilediv" class="attached"></div>
                      		</div>
		                  <div class="" >
			                      	<div class="col-lg-2 col-md-2" style="text-align: left;">
										 <button id="Button1" class="btn btn-warning" type="button" onclick = "AddFileUpload()"><i class="fa fa-paperclip"></i> Attach Files</button>
									</div>
							</div>
							<br>
								  <div class="">
								  
									<input type="hidden" id="fileUpload" name = "fileUploadd" />
					 				 <div id = "drop" class="col-lg-6 col-md-6" style="padding-top: 5px;">
						      			  FileUpload Controls will be added here
						      			  <div id="upload"></div>
						      			  <div id = "draftAttachments"></div>
		    						</div>
		   					</div> -->
		   					<input type="button" id="btnapproved1" onclick="updateApprove()" class="btn btn-primary btncr" value="Approved">
	                      	 <input type="button" class="btn btn-primary btncr" value="Save" onclick="saveFindings();">
                  </div>
              </div>
          </div>
      </div>
</s:form>
	
	<!-- Send sms Details Modal -->
	 <div class="modal fade" id="semdsmspopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Send SMS</h4>
				</div>
				<div class="modal-body">
						
						
					
				         <div class="">
				         	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
				         		<div class="form-group">
									<label> Practitioner</label>
									<s:textfield name="smsuserName" id="smsuserNameletter" readonly="true" cssClass="form-control"  value="Client"  />
									<label  id = "smsunameError" class="text-danger"></label>	
								</div>
						 	</div>
						 	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
								<div class="form-group">
									<label> Mobile No</label>
									<s:textfield name="smsmobile" id="smsmobile"  cssClass="form-control"   />
									<label  id = "smsmobileError" class="text-danger"></label>
								</div>
						 	</div>
						 	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
						 		<div class="form-group">
									<label> Select Template</label>
									<s:select onchange="getsmsInvsttemplatetxt(this.value)" cssClass="form-control showToolTip chosen" name="smstmlate" id="smstmlate" list="smsTemplateList" listKey="id" listValue="templateName" headerKey="0" headerValue="Select Template"/>
									<label  id = "smstmplateError" class="text-danger"></label>		
								</div>
						 	</div>
						 	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="form-group">
									<label>Enter Text</label>
									<s:textarea placeholder="Max 160 character" maxlength="160" cols="60" rows="6" name="smstxt" id="smstxt"  cssClass="form-control"   />
								</div>
							</div>
						</div>  
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary martop10"
						onclick="sendsms()">Send</button>

					<button type="button" class="btn btn-primary martop10 hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
   
	
	
	
	
		<!-- Send Letter Details Modal -->
		<s:form id="upload" method="post" action="uploadSendMailInstantMsg"
					enctype="multipart/form-data" theme="simple">

	<div class="modal fade" id="sendLetterPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			
     
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Send Email/Letter</h4>
				</div>
				<div class="modal-body" id="sendsms">
						<div class="row">
         <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
         <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
				<label> User</label>
		 </div>
		 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
				<%-- <s:select id="userNameletter" name="userName"  
				list="{'Client'}" cssClass="form-control  showToolTip chosen" 
				value="userName" onchange="setNameLetter(this.value)"></s:select>
				<label  id = "unameError" class="text-danger"></label>	 --%>
				<s:textfield name="userName" id="userNameletter" readonly="true" cssClass="form-control"  value="Client"  />
				<label  id = "unameError" class="text-danger"></label>	
		</div>
		</div>
		
		
		
		 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
         <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
				<label>Select Template</label>
		</div>
			 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
				<select id = "templateName" name = "templateName" onchange="showTemplateDetails(this.id)" class="form-control showToolTip chosen" data-toggle="tooltip">
						<option value="0">Select Template Name</option>
				</select>					
			</div>
		</div>
		
					
	
		<s:hidden name="id" id="id"></s:hidden>
		<s:hidden name="user" id="userletter"></s:hidden>
		<%-- <div class="col-lg-12">
		 <div class="col-lg-3">
			<label>User Name</label>
		</div>
		 <div class="col-lg-8">
			<s:textfield name="user" id="userletter" readonly="true" cssClass="form-control" />
				     <label  id = "userError" class="text-danger"></label>	
		<s:hidden name="id" id="id"></s:hidden>
		<s:hidden name="user" id="userletter"></s:hidden>
		</div>
		</div> --%>
		
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		 <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			<label>Email ID</label>
		</div>
		 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
			<s:textfield name="email" id="gpLetterEmail" cssClass="form-control showToolTip" title="Enter EmailId"
								data-toggle="tooltip" placeholder="Enter EmailId"/>
			<label  id = "emailError" class="text-danger"></label>	
		</div>
		</div>
		
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
		 <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			<label>Cc</label>
		</div>
		 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
			<s:textfield theme="simple" id = "gpLetterccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />			
		</div>
		</div>
		
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
		 <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			<label>Subject</label>
		</div>
		 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
			<input type="text" name= "subject" id = "gpLettersubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">			
		</div>
		</div>
		
			<!-- <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
				<label>Attachments:</label>
				<input type="hidden" id="fileUpload" name = "fileUploadd" />
 				 <div id = "drop">
 					
      			  FileUpload Controls will be added here
      			  <div id="upload"></div>
      			  <div id = "draftAttachments"></div>
    			</div>
    			
    			<br/>
 				  <input id="Button1" class="btn btn-default"  type="button" value="Attach Files" onclick = "AddFileUpload()" />
    			
   			</div> -->
		
       <br/>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			 <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
				<label>Body:</label>
				</div>
				<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
				<s:textarea cssStyle="height:2500px;" class="form-control showToolTip textarea"  data-toggle="tooltip" title="Write content" placeholder="Write content" name="body" id="emailBodyLetter" />
			</div>
			</div>
			
			
			   			
		</div>
		
      </div>
						
			<div class="modal-footer">
			<div class="row">
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="pdfFileIdLetter">
			</div></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="sendmail">
			</div></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="printId">
			</div></div>
		<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
		<!-- <button type="button" id="saveId" class="btn btn-primary" onclick="return createPdf()">Save as pdf</button> -->
		<button type="button" id="saveId" class="btn btn-primary"  onClick="fileUpload1(this.form,'sendLetterEmailInstantMsg','upload'); return false;">Send Mail</button>
		<button type="button" class="btn btn-primary"  onclick="return createPdf()">Print</button>
		<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
      </div></div></div>
     
    </div>
  </div>
</div> 

</s:form>
   


<!-- Add New Patient -->
	<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog" style="z-index: 1051"
		aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static"  >
		<div class="modal-dialog modal-lg">			
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
				</div>
				<div class="modal-body" id="" style="height: 500px">
					<%@ include file="/diarymanagement/pages/addnewInvestigationClient.jsp" %>
					
				</div>
				 <div class="modal-footer">
					 <button type="button" id='invstibtn' class="btn btn-primary" onclick="return savePatient()">Save</button>
					<button type="button" class="btn btn-primary hidden" data-dismiss="modal" >Close</button>
				</div> 
			</div>
		</div>
	</div>
	
	
	
	<!-- Add Investigation Popup -->
	
		<!-- add invesgtigation Modal -->
		<input type="hidden" id="invchargeinfodata"/>
	 <div class="modal fade" id="investigationpopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Create Investigation For <b class="pname" id="invstcmyModalLabel">NAME: </b></h5>
				</div>
				<div class="modal-body" id="investi">
						
						
					<%@ include file="/emr/pages/addInvestigation.jsp" %>
				    
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id='investigationsavebtn'
						onclick="saveViewInvestigation()">Save</button>
						
						<!-- <button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)">Save & Print</button> -->

					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	
		<input type="hidden" id="addchargehead"/>
		<div class="modal fade" id="addchargepopupinv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Add Charges for <span id="addinvnewchargehead"></span></h4>
				</div>
				<div class="modal-body">
				
					<%-- <%@ include file="/ipd/pages/addcharges.jsp"%> --%>
					<jsp:include  page="/ipd/pages/invaddcharge.jsp" />
				</div>
				
				<s:form action="estimateCharges" theme="simple" id="estimatefrm" target="formtarget">
					<s:hidden name="clientId" id="estimateclientid"/>
					<s:hidden name="actionType" value="0"/>
				</s:form>
				
				<div class="modal-footer">
				<%if(loginfo.isCash_desk() || loginfo.getUserType()==2){ %>
				<button type="button" class="btn btn-primary" 
							onclick="createChargeAndUpdateAccount('cash')">Cash Desk</button>
				<%} %>
					<button type="button" class="btn btn-primary" 
							onclick="createestimate()">View
							Estimate</button>
				
				
					<button type="button" class="btn btn-primary" 
						onclick="createChargeAndUpdateAccount('Charge')">Create
						Charge</button>
					
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<s:form action="invstcashAdditional" id="cashdeskfrm1" > 
									
			<s:hidden name="clientId" id="cashClientid"/>
			<s:hidden name="thirdParty" id="cashthirdparty"/>
			<s:hidden name="location" id="cashLocationid"/>
			<s:hidden name="client" id="cashclientname"/>
			<s:hidden name = "payby" id ="cashPayby"></s:hidden>
			<s:hidden name="creditDebitCharge" id="creditDebitCharge" value="0"/>
			<s:hidden name="appointmentid" id="cashAppointmentid"/>
			<s:hidden name="invoiceid" id="cashinvoiceid"/>
			
			
	
	
		
    </s:form>
	
	
	 <s:form id="upload" action="savewriteupInvestigation" theme="simple" enctype="multipart/form-data" >
	<div id="uploadfile" class="modal fade" role="dialog">
									  <div class="modal-dialog modal-sm">
									    <!-- Modal content-->
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal">&times;</button>
									        <h4 class="modal-title">Upload File</h4>
									      </div>
									      <div class="modal-body">
									       
											<s:hidden name="selectedinvstid" id="selectedinvstid"/>
									        <div class="col-lg-12 col-md-12" style="background-color: #efefef;padding: 18px;margin-top: -13px;">
									        	<div class="col-lg-5 col-md-5 col-xs-12 col-sm-12" style="text-align:left;">
					                      			Attached File: <div id="attachdfilediv" class="attached"></div>
					                      		</div>
					                      		<div class="col-lg-7 col-md-7" style="float:right;margin-top: -4px;">
														 <button id="Button1" class="btn btn-warning" type="button" onclick = "AddFileUpload1()"><i class="fa fa-paperclip"></i> Attach Files</button>
													</div>
									        </div>
												<br>
												<br>
												<br>
											  <div class="">
												<input type="hidden" id="fileUpload" name = "fileUploadd" />
												  <div id = "drop" class="col-lg-12 col-md-12" style="padding-top: 5px;">
									      			  <!--FileUpload Controls will be added here -->
									      			  <div id="upload"></div>
									      			  <div id = "draftAttachments"></div>
					    						</div>
		   									</div>
	                      					 <input type="submit" class="btn btn-primary btncr hidden" value="Save" >
											
									      </div>
									      <div class="modal-footer">
									       <input type="submit"  class="btn btn-primary pull-right" value="Save">
											
									      </div>
									    </div>
									
									  </div>
									</div>
   </s:form>
   
   <!-- Modal -->
<div class="modal fade" id="clientSearchDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/ipd/pages/ipdPatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
   
<!-- Modal -->
<div id="investigationcancelmodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">
	<form action="deleteInvestigation">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Are You Sure To Cancel?</h4>
      </div>
      <div class="modal-body">
      		<input type="hidden" name="selectedid" id="pro_id">
      		<div class="form-group" style="margin-bottom: 0px;">
	      		<label>Reg. ID :</label>
	      		<span id="cancelsnhid"></span>
      		</div>
      		<div class="form-group" style="margin-bottom: 0px;">
	      		<label>Patient Name :</label>
	      		<span id="cancelpatientname"></span>
      		</div>
      		<div class="form-group" style="margin-bottom: 0px;">
	      		<label>Investigation Type :</label>
	      		<span id="cancelinvestitype"></span>
      		</div>
      		<div class="form-group">
	      		<label>Cancel Reason</label>
	      		<textarea rows="3"  class="form-control" id="delete_reason" name="delete_reason" placeholder="Write Reason" style="background-color: beige;"></textarea>
      		</div>
      </div>
      <div class="modal-footer">
      <s:hidden name="searchText"/>
        <input type="submit" class="btn btn-danger" value="Ok">
      </div>
    </div>
	</form>
  </div>
</div>

<!-- Modal -->
<div id="invesoutsourcemodel" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">
	<!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Outsource Details</h4>
      </div>
      <div class="modal-body">
      		<s:hidden id="outsourceidos"></s:hidden>
      		<s:hidden id="inparentidos"></s:hidden>
      		
      		<div class="form-group" style="margin-bottom: 0px;" id="expdsatediv">
      		<label>Expected Date : </label>
      		<input type="text" class='form-control' id='outexpdsate' placeholder="Expected Date" readonly="readonly" style="width: 50%">
      		
      		</div>
      		<br>
      		<div class="form-group" style="margin-bottom: 0px;" id="outsourcediv">
	      		<label>OutSourced to : </label>
	      		<label></label>
      		</div>
      		<div class="clearfix" style="height: 10px;"></div>
      		<div class="form-group" style="margin-bottom: 0px;" id="outsourcehandoverdiv">
	      		<label>Sample Handover to : </label>
	      		<input type="text" class="form-control" placeholder="Sample Handover To">
	      	</div>
	      	<div class="clearfix" style="height: 10px;"></div>
      		<div class="form-group" style="margin-bottom: 0px;" id="outsourceisretunddiv">
	      		<label>Report Received : </label>
	      		<select id='isreturnOS' >
	      			<option value="0">No</option>
	      			<option value="1">Yes</option>
	      		</select>
	      	</div>
	      	<div class="clearfix" style="height: 10px;"></div>
      		<div class="form-group hidden" id="outsourcereturnuserdiv" >
	      		<label>Is Return From</label>
	      	</div>
	      	
      </div>
      <div class="modal-footer" id="outsourcebtndiv">
        	<a href="#" class="btn btn-danger" onclick="saveOutsourceDetails()">Save</a>
      </div>
    </div>
  </div>
</div>


   <!-- Modal -->
<div class="modal fade" id="invdtchnge" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
  <div class="modal-dialog modal-lg">
    <div class="modal-content" style="margin-left: 350px !important;margin-right: 350px !important;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="invdtchnge">Date Change Investigation</h4>
      </div>
      <div class="modal-body">
      <div class='form-inline'>
      <label>Date :</label>
        <input type="text" placeholder="change date" class='form-control' name='datw' id='datw' style="width: 30%">
        <label>Time :</label>
         <input type="text" class='form-control' name='datw1' id='datw1' style="width: 20%" placeholder="HH">
        
      </div>  
      <div class='form-inline'>
      <br>
        <input type="button" class='btn btn-success' value="Update" onclick='updatedateInvetstDate()'> 
        <input type="hidden" name='hiddt' id='hiddt'>
        </div> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
   

	 <!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);z-index: 99" id="newloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->

<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
	
	<script type="text/javascript">
	
		$(".modal-draggable .modal-dialog").draggable({
			handle : ".modal-header"
		});
		$('#investi').slimScroll({
					   		height : '450px',
					   		railVisible: true,
							alwaysVisible: true
					  });
					   $('#sendsms').slimScroll({
					   		height : '450px',
					   		railVisible: true,
							alwaysVisible: true
					  });
					   $('#newpaa').slimScroll({
					   		height : '450px',
					   		railVisible: true,
							alwaysVisible: true
					  });
	</script>


<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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
function myFunction(elmnt,clr) {
    elmnt.style.color = clr;
}
</script>

<script type="text/javascript" src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<script>
				             $(function() {
								  $('.scrolltable').slimScroll({
								   		height : '515px',
								   		railVisible: true,
										alwaysVisible: true
								  });
								 
				 				});
 				 
             </script>
 
 <script>
    function printDiv2(divID) {
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
	
	<script>
	function printDiv(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	
	
	function opendtchngepopup(id ,date){
		var dt=date.split(' ');
		var tt=dt[1].split(':');
		document.getElementById('datw').value=dt[0];
		document.getElementById('hiddt').value=id;
		document.getElementById('datw1').value=dt[1];

		$('#invdtchnge').modal( "show" );
		
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



 	