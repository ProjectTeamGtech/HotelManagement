<%@ taglib uri="/struts-tags" prefix="s"%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Emr.eu.entity.*"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>


<%
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>


<!-- The main CSS file -->
<!--external css-->
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<link href="toggale/js/smartmenus-0.9.7/css/sm-clean/sm-clean.css"
	rel="stylesheet" />
<link href="toggale/js/smartmenus-0.9.7/css/sm-core-css.css"
	rel="stylesheet" />


<link rel="stylesheet" href="_assets/newtheme/css/main.css">

<!-- Custom styles for this template -->
<link href="toggale/css/style.css" rel="stylesheet">
<!-- <link href="toggale/css/style-responsive.css" rel="stylesheet">  -->
<link href="common/assets/css/style.css" rel="stylesheet" />
<link href="common/owlcarousel/owl-carousel/owl.carousel.css"
	rel="stylesheet" />
<!--   <link href="emr/css/jquery.treeview.css" rel="stylesheet" /> -->

<!-- MY Resizable Plugin css and js -->
<link rel="stylesheet"
	href="common/Font-Awesome-master/css/font-awesome.min.css">
<!-- Theme -->
<link href="_assets/plugin/reziseplugin/main.css" rel="stylesheet"
	type="text/css" />
<link href="_assets/plugin/reziseplugin/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="_assets/plugin/reziseplugin/responsive.css" rel="stylesheet"
	type="text/css" />
<link href="_assets/plugin/reziseplugin/icons.css" rel="stylesheet"
	type="text/css" />
<link href="_assets/plugin/bottomemenu/wheelmenu.css" rel="stylesheet"
	type="text/css" />
<link href="_assets/plugin/bottomemenu/menuboticon.css" rel="stylesheet"
	type="text/css" />


<link href="emr/plugin/side-slider.css" rel="stylesheet" type="text/css"
	media="screen">

<!-- Sweet Alert This is what you need -->


<script type="text/javascript"
	src="_assets/plugin/reziseplugin/breakpoints.js"></script>
<script type="text/javascript"
	src="_assets/plugin/reziseplugin/respond.min.js"></script>
<!-- Polyfill for min/max-width CSS3 Media Queries (only for IE8) -->
<script type="text/javascript"
	src="_assets/plugin/reziseplugin/jquery.cookie.min.js"></script>
<script type="text/javascript"
	src="_assets/plugin/reziseplugin/jquery.slimscroll.min.js"></script>
<script type="text/javascript"
	src="_assets/plugin/reziseplugin/jquery.slimscroll.horizontal.min.js"></script>
<!-- App -->
<script type="text/javascript" src="_assets/plugin/reziseplugin/app.js"></script>
<script type="text/javascript"
	src="_assets/plugin/reziseplugin/plugins.js"></script>
<script type="text/javascript"
	src="_assets/plugin/reziseplugin/plugins.form-components.js"></script>
<script type="text/javascript"
	src="_assets/plugin/bottomemenu/jquery.wheelmenu.js"></script>

<script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>

<script>
        $(document).ready(function () {
        
      
        
            "use strict";

            App.init(); // Init layout and core plugins
            Plugins.init(); // Init all plugins
            FormComponents.init(); // Init all form-specific plugins
        });
    </script>



<style>
.tables_ui th, td {
	text-align: left;
	padding: 2px 4px;
	border: 1px solid;
}

<!--
td {
	text-align: center !important;
}

th {
	text-align: center !important;
}

#upload {
	background-color: #fff;
	padding: 0px;
	border-radius: 0px;
}

.radio, .checkbox {
	position: relative;
	display: block;
	min-height: 14px;
	margin-top: 2px;
	margin-bottom: 1px;
}

.topbd {
	background-color: #43b9be;
	padding: 6px 0px 8px 8px;
	width: 100%;
	color: #fff;
}

.navbar .navbar-nav {
	width: 100% !important;
}

.clientnamefon {
	font-size: 12px;
}

.set {
	margin-top: -15px;
	margin-left: 18px;
}

.fbackwhi {
	background-color: #fff !important;
}

.emricons {
	margin-right: -29px;
}

.ionemrse {
	display: inline-flex;
	float: right;
}

.navbar {
	background: #43b9be;
	min-height: 40px !important;
	border-bottom: none !important;
}

.page-title span {
	display: block;
	font-size: 12px;
	color: #888888;
	font-weight: normal;
	line-height: 16px;
}

.setnotfor {
	padding-left: 0px;
	padding-right: 0px;
}

.tofilter {
	padding-left: 0px;
	padding-right: 0px;
	background-color: #efefef;
	padding: 4px;
	margin-top: -5px;
	height: 34px;
}

.mt {
	margin-top: -11px !important;
	padding-top: 20px;
	/* background-color: #43b9be;*/
}

.panel .panel-heading .panel-title>a {
	display: block;
	cursor: pointer;
	font-size: 14px;
	padding: 0;
}

.updatbtnset {
	background-color: #555;
	color: #fff;
	padding: 4px 10px 7px 10px;
	border-radius: 21px;
	font-size: 15px;
}

.menusetleft {
	float: left;
	padding-top: 0px;
	background-color: #6699CC;
	color: #fff;
	display: inline-flex;
	border-top: 1px solid #fff;
	border-left: 1px solid #fff;
}

.setplubtn {
	padding-left: 0px;
	margin-top: 6px;
	margin-left: 2px;
}

#dropset {
	display: none;
}

.panel-group {
	margin-bottom: 5px;
}

.topsave {
	float: right;
	margin-top: -2px;
	margin-right: 20px;
	background-color: #555;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 3px 7px;
	text-decoration: none;
	line-height: 25px;
}

li a:hover {
	background-color: rgb(66, 74, 93);
	color: #fff !important;
	line-height: 25px;
}

.spanslash {
	padding-top: 8px;
}

.emrtitle {
	padding-left: 0px;
	padding-right: 0px;
	margin-top: 7px;
	margin-left: 23px;
	height: 50px;
}

.marmin8 {
	margin-left: -8px;
	margin-top: 17px;
}
/* md */
@media ( min-width : 992px) and (max-width: 1199px) {
	li a {
		padding: 3px 12px !important;
	}
	.mt {
		padding-top: 35px !important;
	}
}

/* sm */
@media ( min-width : 768px) and (max-width: 991px) {
	li a {
		padding: 3px 12px !important;
	}
	.mt {
		margin-top: 0px !important;
		padding-top: 35px !important;
	}
	.emrtitle {
		padding-left: 0px;
		padding-right: 0px;
		margin-top: 7px;
		margin-left: 0px;
		height: 57px;
	}
	.marmin8 {
		margin-left: -8px;
		margin-top: -7px;
	}
}

.panel-title {
	font-size: 13px;
	font-weight: 600;
	padding: 6px;
}

.micimg {
	width: 5%;
	float: right;
}

@media only screen and (max-width: 479px) {
	.emrtitle {
		padding-left: 0px;
		padding-right: 0px;
		margin-top: 0px !important;
		margin-left: 0px !important;
	}
	.marmin8 {
		margin-left: -16px;
		width: 110%;
	}
	li a {
		padding: 3px 4px !important;
	}
	.mt {
		margin-top: -20px !important;
		padding-top: 20px !important;
		/* background-color: #43b9be; */
	}
	.row1 {
		margin-left: 0px !important;
		margin-right: 0px !important;
	}
	.widthtd {
		width: 10% !important;
	}
}
/* filter table specific styling */
td.alt {
	background-color: #ffc;
	background-color: rgba(255, 255, 0, 0.2);
}

-->
body {
	background: #fff;
}

.oneseticonleft {
	padding-right: 0px;
	padding-left: 0px;
	margin-left: -11px;
	padding-top: 2px;
	padding-bottom: 3px;
}

ul li {
	list-style: none;
	border-bottom: 1px solid rgba(222, 222, 222, 0.68);
	padding: 2px 0px 2px 5px;
	font-size: 11px;
	text-transform: uppercase;
}

.assesheight {
	padding: 0px;
	border: 1px solid #ddd;
	min-height: 570px;
}

.panel-heading {
	padding: 3px 5px;
}

.social {
	text-align: center;
}

.social .link {
	display: inline-block;
	vertical-align: middle;
	position: relative;
	width: 30px;
	height: 30px;
	border-radius: 50%;
	background-clip: content-box;
	padding: 0px;
	transition: .5s;
	color: #D7D0BE;
	margin-left: 0px;
	margin-right: 0px;
	font-size: 18px;
	line-height: 30px;
}

.social .link.iconset {
	background-color: #6699cc;
	color: white;
}

.heiige {
	background-color: #55555530;
	padding: 4px 4px 4px 4px;
	margin-bottom: 5px;
}
#ui-datepicker-div {    width: 340px;
}

.ui-datepicker-title{
color: black;
}
</style>


<!-- Our main JS file -->

<script type="text/javascript" src="emr/js/jquery.filtertable.min.js"></script>

<script type="text/javascript" src="emr/js/consultationnote.js"></script>

<script type="text/javascript" src="emr/js/emrNew.js"></script>

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
<script type="text/javascript">
window.onload = function(){
	
	imageAdder();
	imageAdderEdit();

	
	
	
}
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



<section>
	<s:form action="getPatientRecordEmr" id="getPatientRecord">

		<input type="hidden" id="isfromemrdashb" value="1">
		<s:hidden name="action" id="hdnaction" />
		<s:hidden name="caldate" id="caldate" />
		<s:hidden id="conditionsApmt" name="conditionsApmt"></s:hidden>
		<s:hidden id="hdncondapmtId" name="apmtId" />
		<s:hidden id="treatmentEpisodeid" name="treatmentEpisodeid" />
		<s:hidden id="opdchkcondition" name="opdchkcondition" />
		<s:hidden id="diaryUser1" name="diaryUser"></s:hidden>
		<div class="row mainheader">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding: 0px;">
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5"
					style="display: -webkit-inline-box; padding-right: 0px;">
					<img src="dashboardicon/electrocardiogram.png"
						class="img-responsive prescripiconcircle">&nbsp;&nbsp;
					<h5>
						EMR - <span id="doctoremrid"><s:property
								value="emrDoctorName" /></span>&nbsp; <i class="fa fa-angle-right"
							aria-hidden="true"></i> <span id="clientemrid"><s:property
								value="emrClientName" /></span>&nbsp; <i class="fa fa-angle-right"
							aria-hidden="true"></i> <span id="conditionemrid"><s:property
								value="emrConditionName" /> </span>&nbsp;&nbsp;<a href="#"
							data-toggle="modal" data-target="#lok" style="color: yellow;"><i
							class="fa fa-pencil"></i></a>
					</h5>
				</div>
				<div class="col-lg-7 col-md-7 col-sm-7 col-xs-7"
					style="padding-left: 0px; text-align: right; color: rgba(232, 232, 232, 0.69);">
					<p style="margin: 8px 0px 0px 0px; font-size: 13px;">
						<%if(loginInfo.isEmr_docs()){ %>
						<span><a href="#" href="#" data-toggle="modal"
							data-target="#document_details"
							style="color: rgba(232, 232, 232, 0.69);">Doc's</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<% }else{%>
						<span><a href="#" href="#"
							style="color: rgba(232, 232, 232, 0.69);">Doc's</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<%} %>
						<span><a
							href="emrdocsEmr?clientId=<s:property value="clientname"/>&diaryUser=<s:property value="diaryUser"/>&condition=<s:property value="condition"/>"
							style="color: rgba(232, 232, 232, 0.69);">Doc's page</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<%-- <%if(loginInfo.isEmr_history()){ %>
											<span><a href="#" href="#" data-toggle="modal" data-target="#medical_records" style="color:rgba(232, 232, 232, 0.69);;">History</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
										 <% }else{%>
											<span><a href="#" href="#" style="color:rgba(232, 232, 232, 0.69);;">History</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
										<%} %> --%>


						<span><a href="#"
							onclick="clincalnoteselements()"
							style="color: rgba(232, 232, 232, 0.69);">Clinical Notes Elements</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
								
						<%if(loginInfo.isEmr_medicine()){ %>
						<%-- <span><a href="#" data-toggle="modal" data-target="#presscription_details" onchange="getEmrClientAllPriscriptionData()" style="color:rgba(232, 232, 232, 0.69);">Medicine</a></span>&nbsp;&nbsp;|&nbsp;&nbsp; --%>
						<span><a href="#"
							onclick="getEmrClientAllPriscriptionData()"
							style="color: rgba(232, 232, 232, 0.69);">Medicine</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<% }else{%>
						<span><a href="#" style="color: rgba(232, 232, 232, 0.69);">Medicine</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<%} %>
						<%if(loginInfo.isEmr_medicine()){ %>
						<%-- <span><a href="#" data-toggle="modal" data-target="#investigation_details" style="color:rgba(232, 232, 232, 0.69);">Investigation</a></span>&nbsp;&nbsp;|&nbsp;&nbsp; --%>
						<span><a href="#" onclick="getEmrinvestigationViewList()"
							style="color: rgba(232, 232, 232, 0.69);">Investigation</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<% }else{%>
						<span><a href="#" style="color: rgba(232, 232, 232, 0.69);">Investigation</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<%} %>
						<span><a href="#"
							onclick="opencPopup('Pacs?clientid=<s:property value="clientname"/>')"
							style="color: rgba(232, 232, 232, 0.69);">PAC's</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<span><a href="#" data-toggle="modal"
							data-target="#patient_media"
							style="color: rgba(232, 232, 232, 0.69);">Media</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
						<span><a href="#"
							onclick="openPopup('treatmentsheetIpdDashboard?clientid=<s:property value="clientname"/>&ipdid=0')"
							style="color: rgba(232, 232, 232, 0.69);">Treatment Record</a></span>

						&nbsp;&nbsp;|&nbsp;&nbsp; <span><a href="#"
							data-toggle="modal" data-target=#
							style="color: rgba(232, 232, 232, 0.69);">Education</a></span>


					</p>
				</div>
			</div>
		</div>
	</s:form>
	<s:form action="getPatientRecordEmr">
		<div class="row">
			<section>
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-6 col-md-6 col-sm-6"
						style="padding-left: 0px; padding-right: 0px;">
						<h4 class="heading3 marto202" style="display: inline-flex;">
							<span
								class="hidden-xs hidden-sm clinetinf textlft  clientnamefon">
								Notes for: </span>&nbsp;<span id="clientDetailsDiv"
								Class="clinetinf textlft  clientnamefon"><b><s:property
										value="client" /></b> <s:property value="clientData" /></span>
						</h4>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4"
						style="padding-left: 0px; padding-right: 0px; padding-top: 5px">
						<div class="form-inline">


							<div class="form-group">
								<s:textfield readonly="true" name="fromDate" id="fromDate"
									cssClass="form-control" theme="simple" style="width:50%;color:black;"
									placeholder="from date"></s:textfield>
							</div>-
								
							<div class="form-group" style="margin-left: -78px;">-
								<s:textfield readonly="true" name="toDate" id="toDate"
									cssClass="form-control" theme="simple" style="width:50%;color:black;"
									placeholder="to date"></s:textfield>
							</div>
							<s:hidden name="diaryUser"/>
							<s:hidden name="clientname"/>
							<div class="form-group"style="margin-left: -72px;">
								<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
							</div>
						</div>
					</div>

					<div class="col-lg-2 col-md-2 col-sm-2"
						style="padding-right: 0px; padding-left: 0px;">
						<h4 style="margin-top: 3px; margin-bottom: 3px;"
							class="martopset9 text-right">
							<span class="social"> <a href="#" class="link iconset"
								onclick="showsharepopup()" title="share EMR"><i
									class="fa fa-share-alt" aria-hidden="true"></i></a> <a href="#"
								class="link iconset" onclick="showconfidentialpopup()"
								title="Make Confidential"><i class="fa fa-lock"
									aria-hidden="true"></i></a> <a href="#"
								onclick="openEmrPopup('printconsnoteEmr?clientid=<s:property value="clientname"/>&amptid=<s:property value="apmtId"/>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=1');"
								class="link iconset" title="Print"><i class="fa fa-print"
									aria-hidden="true"></i></a> <%if(!loginInfo.getLoginType().equals("shareemr")){ %>
								<%if(loginInfo.isEmr_update()){ %> <s:if test="diaryUser!=0">
									<a href="#" style="background-color: #555; color: #fff;"
										data-toggle="modal" data-target="#addConsultationNote"
										class="updatbtnset" title="Update Notes"
										onclick="openAddConsultationPopup()">Update Note</a>
								</s:if> <% }%> <% }%>
							</span>




						</h4>
					</div>
				</div>
			</section>
		</div>
	</s:form>
	<div class="row">
		<div class="col-lg-12 col-md-12">
			<div class="panel panel-primary" style="height: 570px;">

				<div class="panel-body hesighsetnew" style="margin-top: -5px;">
					<%
										ArrayList<Emr>consultationList  = new ArrayList<Emr>();
										if(session.getAttribute("consultationNoteList")!=null){
											consultationList = (ArrayList<Emr>)session.getAttribute("consultationNoteList");
										}
										
									%>
					<%
										for(Emr emr:consultationList ) {
									%>
					<div class="row"
						style="background-color: rgba(239, 239, 239, 0.54); padding: 3px 0px 3px 0px;">
						<div class="col-lg-12 col-md-12 col-xs-12">
							<b style="width: 100%; display: inline-flex;"> <%=emr.getLastModified()%>
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
							</b>
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

		<div class="sideslider" id="sideslider" style="margin-left: -265px;">
			<div class="sideslider-tab">Forms</div>

			<div id="sideslider-smartbutton">
				<input type="text" class="live-search-box form-control"
					placeholder="search here"
					style="width: 100%; text-transform: uppercase; font-size: 11px !important;" />
				<ul class="commentlist heightsetd">
					<li style="cursor: pointer;"
						onclick="openBlankPopup('gynicassesmentformCommonnew?clientid=<s:property value="clientname"/>')">
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
				<div class="sideclear"></div>
			</div>


			<div class="sideslider-close sideslider-close_en hidden">Close&nbsp;</div>

		</div>
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



					<!--<s:if test="%{#conditionList != 'null'}">
							<s:select cssClass="form-control showToolTip chosen-select"
								id="condition" name="condition" list="conditionList"
								listKey="id" listValue="treatmentType" headerKey="0"
								theme="simple" headerValue="Select condition"
								onchange="getPatientRecord()" />
						</s:if>-->
					<!--<select Class="form-control showToolTip chosen-select" id="project"  onchange="loadchangesemr()" tabindex="1">
						<option value="0">Select EMR</option>
			            <option value="Treatment Details">Treatment Details</option>
			            <option value="Documents">Documents</option>
			            <option value="Medical Records">Medical Records</option>
			            <option value="Prescription">Prescription</option>
			            <option value="Investigation">Investigation</option>
			            <option value="Patient Media">Patient Media</option>
			          </select>-->
				</div>
				<!--<div class="col-lg-1 col-md-1 col-sm-2 col-xs-12 clinname hidden">
					<div class="ionemrse">
					 <span id="shareids" class="emricons"><a href="#" onclick="showsharepopup()" title="Share"><img src="cicon/share.png" class="img-responsive" style="width:40%;"/></a></span>
					 <span id="confidentialid" class="emricons"><a href="#" onclick="showconfidentialpopup()" title="Make Confidential"><img src="cicon/unlock.png" class="img-responsive" style="width:40%;"/></a></span>
					</div>
					-->
			</div>
			<%--    <div class="col-lg-3">
                          	<label style="font-weight: bold;">Treatment Episode : </label>
                        <s:if test="%{#treatmentEpisodeList != 'null'}" >
                           	<s:select cssClass="form-control showToolTip chosen"  id="treatmentEpisodeid" name="treatmentEpisodeid" list="treatmentEpisodeList" listKey="id" listValue="treatmentEpisodeName" headerKey="0" theme="simple" headerValue="Select TreatmentEpisode" />
						</s:if>
                        </div> --%>
			<%--  <div class="col-lg-3">
                            <select class="form-control" name="Select Document Type">
                                <option selected="selected">Select Assesment Form</option>
                                <option>Social History</option>
                                <option>Prescription</option>
                                <option>Others</option>
                            </select>
                        </div> --%>

		</div>
	</div>



	<!-- /Top Left Menu -->




</section>


<div role="tabpanel">



	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane" id="AD" style="display: none;">
			<!--  <table class="table  table-responsive"> -->
			<table width="100%" cellpadding="0" cellspacing="0"
				class="timer-table" id="allusertable" style="table-layout: fixed;">
				<thead>
					<tr>
						<!--   <th>Mon 26 June 2015</th>
                                      <th>Tue 27 June 2015</th>
                                      <th>Wen 28 June 2015</th>
                                      <th>Thu 29 June 2015</th>
                                      <th>Fri 30 June 2015</th> -->

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
													<div class="row panel-heading" role="tab" id="headingOne">
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





															<%-- <sx:tree id="books" label="Books" title="test" >

<sx:treenode label="Programing books" title="test">

<sx:treenode label="Java" title="test">

<sx:treenode id="Thread-Books" label="Core-Java" >

<sx:treenode id="Thread-Books" label="Core-Java Essentials" />

<sx:treenode id="Thread-Books" label="Head first Java" />

<sx:treenode id="Thread-Books" label="Multi-threading" />

<sx:treenode id="Thread-Books" label="Networking" />

</sx:treenode>

<sx:treenode label="J2EE" title="test1">

<sx:treenode id="Thread-Books" label="JSP in Action" />

<sx:treenode id="Thread-Books" label="Core-JSP-Servlet" />

<sx:treenode id="Thread-Books" label="Advance JSP-Servlet" />

</sx:treenode>

</sx:treenode>

</sx:treenode>

<sx:treenode label="Technical" >

<sx:treenode label="Science books"></sx:treenode>

</sx:treenode>

</sx:tree> --%>

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
																	<!--<s:if test="appointmnetList.size!=0">
														<s:iterator value="appointmnetList" status="rowstatus">
															<input type="hidden" id="hdn<s:property value = "id"/>"
																name="hdn<s:property value = "id"/>"
																value="<s:property value = "treatmentEpisodeName"/>#<s:property value = "apmttypetext"/> - <s:property value ="commencing"/>#<s:property value="trtmentStatus"/>#<s:property value="dischrgeOutcomes"/>#<s:property value="dischargeStatus"/>#<s:property value="dischargedate"/>#<s:property value="hour"/>#<s:property value="minute"/>">
															<s:if test="chkConsultationNote == 1">
																<s:if test="treatmentApmtCount == 1 && apmtCount == 1">
																	<div class="row" style="line-height: 8px;">
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

																</s:if>
																<s:else>
																	<div class="row" style="line-height: 8px;">
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
																	<div class="row" style="line-height: 8px;">
																		<div class="col-md-1 col-xs-1 roadai" style="margin-left: 20px;">
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
																	</div>					</s:if>
																	
																<s:else>
																	<div class="row" style="line-height: 8px;">
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

												-->
																</s:iterator>
															</s:if>

														</div>
													</div>
												</div>
												<div class="panel panel-default">
													<div class="row panel-heading" role="tab" id="headingTwo">
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
																				<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
																				<a href="javascript:void(0)" data-toggle="modal"
																					data-target="#uploaddoc"
																					onclick="editDocuments('<s:property value = "id"/>','<s:property value = "doctType"/>','<s:property value = "description"/>','<s:property value = "fileName"/>')"
																					title="Edit"><i class="fa fa-edit"></i></a>
																				<% }else{%>
																				<i class="fa fa-edit"></i>
																				<%} %>
																			</div>

																			<div
																				class="col-lg-1 col-md-2 col-xs-2 deletne paddate">
																				<s:form theme="simple" id="deleteDocuments_%{id}"
																					action="deleteDocumentsEmr">
																					<s:hidden name="deleteDoctId" value="%{id}"
																						id="deleteDoctId" />
																					<s:hidden id="clientname" value="%{clientname}"
																						name="clientname"></s:hidden>
																					<s:hidden id="diaryUser" value="%{diaryUser}"
																						name="diaryUser"></s:hidden>
																					<s:hidden id="condition" value="%{condition}"
																						name="condition"></s:hidden>

																					<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
																					<a href="#"
																						onclick="deleteDocuments('<s:property value = "id"/>')"
																						title="Delete" class="text-danger"><i
																						class="fa fa-trash-o"></i></a>
																					<% }else{%>
																					<i class="fa fa-trash-o"></i>
																					<%} %>
																				</s:form>
																			</div>
																			<div class="col-lg-2 col-md-3 col-xs-3 assesdelete">
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
																			<!--<p class="font11"></p>
																-->
																			<p>

																				<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
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
																				<% }else{%>
																				<a href="#" class="font11"><s:property
																						value="admissiondate" /> </a>
																				<% }%>
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
																				<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
																				<a href="#"
																					onclick="openEmrPopup('dischargeCommonnew?selectedid=<s:property value = "id"/>&clientid=<s:property value="clientid"/>')"
																					class="font11"><s:property
																						value="admissiondate" /> </a>
																				<%}else{ %>
																				<a href="#" class="font11"><s:property
																						value="admissiondate" /> </a>
																				<% }%>
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
																					onclick="return confirmedDelete()" title="Delete"
																					class="text-danger"><i class="fa fa-trash-o"></i></a>
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
																						<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
																						<a href="#"
																							onclick="openEmrPopup('editListAssessmentForm?id=<s:property value = "id"/>&actionType=2&formtype=<s:property value="formtype"/>')"
																							class="font11"> <s:property
																								value="templateName" />
																						</a>
																						<%}else{ %>
																						<a href="#" class="font11"> <s:property
																								value="templateName" />
																						</a>
																						<%} %>
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
													<div class="row panel-heading" role="tab" id="headingThree">
														<div class="col-lg-9 col-md-9 col-sm-9">
															<h4 class="panel-title">
																<a class="collapsed" data-toggle="collapse"
																	data-parent="#accordion" href="#collapseThree"
																	aria-expanded="false" aria-controls="collapseThree">
																	<i class="fa fa-chevron-down hidden-sm hidden-xs"></i>
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
													<div id="collapseThree" class="panel-collapse collapse "
														role="tabpanel" aria-labelledby="headingThree"
														style="height: 40px">
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
																					<div class="col-lg-3 col-md-2 col-xs-2 editdoc">
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
																						</s:form>
																					</div>
																					<div class="col-lg-2 col-md-2 col-xs-2 marlft17">
																						<p class="font11">
																							<s:property value="medicalRecordType" />
																						</p>
																					</div>
																					<div class="col-lg-6 col-md-6 col-xs-6">
																						<p class="font11" style="margin-bottom: -2px;">
																							<s:property value="lastModified" />
																						</p>
																						<p class="font11">
																							<s:property value="description" />
																							<a href="javascript:void(0)" data-toggle="modal"
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
													<div class="row panel-heading" role="tab" id="headingfour">
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
													<div id="collapsefour" class="panel-collapse collapse "
														role="tabpanel" aria-labelledby="headingfour"
														style="height: 40px">
														<div class="panel-body sidebar" id=""></div>
													</div>
												</div>

												<div class="panel panel-default">
													<div class="row panel-heading" role="tab" id="headingfive">
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
													<div id="collapsefive" class="panel-collapse collapse "
														role="tabpanel" aria-labelledby="headingfive"
														style="height: 40px">
														<div class="panel-body sidebar" id=""></div>
													</div>
												</div>

												<div class="panel panel-default">
													<div class="row panel-heading" role="tab" id="headingsix">
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
																class="fa fa-video-camera" aria-hidden="true"></i></a> <a
																href="" class="pull-right hidden-xs" data-toggle="modal"
																onclick="setEditor()" data-target="#editQuickChartPopup"
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
																<s:iterator value="imageDataList" status="rowstatus">
																	<div class="thumbimg">
																		<img class="scrollimg img-responsive"
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
																<s:iterator value="videoList" status="rowstatus">
																	<div class="thumbimg">
																		<img class="scrollimg img-responsive"
																			src="_assets/img/player.jpg"
																			onclick="openVideoClipPopup('<s:property value = "videoFileName"/>','<s:property value = "id"/>')" />
																		<%-- <s:form theme="simple" action="deleteClientImageEmr" id = "deleteVideo">
													<s:hidden name = "clientDataId" value="%{id}" id = "clientDataId1"></s:hidden>
													<s:hidden id = "clientname" value="%{clientname}" name = "clientname"></s:hidden>
                 									<s:hidden id = "diaryUser" value="%{diaryUser}" name = "diaryUser"></s:hidden>
                 									<s:hidden id = "condition" value="%{condition}" name = "condition"></s:hidden>
													 <i class="fa fa-times delete" onclick = "deleteClientImage()"></i>	
													</s:form> --%>

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
<div class="modal fade" id="addConsultationNote" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-lg" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					Update Notes : <b><span id="hdntrtmentspan" class="hidden"></span></b>
					| Date <b><span id="hdnapmtspan"> <s:property
								value="commencing" />
					</span> </b>
					<button onclick="saveAddConsultationNote()" title="Save"
						type="button" class="btn btn-primary topsave">Save</button>
				</h4>
			</div>
			<s:form action="addConsultationNoteEmr" method="POST" theme="simple"
				id="addconsultationFrm">
				<s:hidden name="hindiconsnote" id="hindiconsnote"
					value="दिन मे एक बार" />
				<s:hidden name='isbookapmtfollowup' id='isbookapmtfollowup'></s:hidden>
				<s:hidden name="finaldiagnosis" id="finaldiagnosis" />
				<div class="modal-body">

					<div id="updatepop" class="">
						<div class="">
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12 paddingnil">
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
										<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
										<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>


										<!--<input type="button" value="SOAP" onclick="setSOS('consNote')" class="btnman btn-new">
								<input type="button" value="Initial" onclick="setInitial('consNote')" class="btnman btn-new">
								<input type="button" value="Problem" onclick="setProblem('consNote')" class="btnman btn-new">
								<input type="button" value="Examination" onclick="setExamination('consNote')" class="btnman btn-new">
								<input type="button" value="Assessment" onclick="setAssessment('consNote')" class="btnman btn-new">
								<input type="button" value="Treatment" onclick="setTreatment('consNote')" class="btnman btn-new">
								<input type="button" value="Plan" onclick="setPlan('consNote')" class="btnman btn-new">
								
								-->
										<!-- <input type="button" value="Date/Time" onclick="setDateTime('consNote')" class="btnman btn-new"> -->

										<!-- 	<input
								type="button" value="Symbols" onclick="openSymbolPopup()"
								class="btn1 btn-new"> -->


									</div>
									<!--<div class="form-group" style="margin-top: -15px;" >
											<img src="cicon/mic_off.png" class="img-responsive micimgemr" onclick="startConverting1(this)" title="Microphone" id="changer"></img>
										
									  </div>
								-->
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

								<div class="panel-group managewidhe" id="accordion"
									role="tablist" aria-multiselectable="true">




									<div class="panel panel-default">
										<div class="recordsnotesn" role="tab" id="">
											<h4 class="panel-title">
												<span> <a class="recordsnotesfont" role="button"
													href="" data-toggle="modal" data-target="#commonvoiceover">
														&nbsp;Record Notes </a> <a data-toggle="modal"
													data-target="#commonvoiceover" href="#"><i
														class="fa fa-microphone  recordsnotesmicroicon"></i></a>

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
												</a> <span> <%if (!loginInfo.getJobTitle().equals("Pathlab")) {%>

														<a href="#"><i onclick="showpriscription()"
															title="Add Prescription" style="color: white;"
															class="fa fa-plus-square fa-2x aadpres"></i></a> <%} %>


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
												</a> <span> <%if (!loginInfo.getJobTitle().equals("Medical Store")) {%>
														<a href="#"><i onclick="showInvestigation()"
															style="color: white;" title="Add Investigation"
															class="fa fa-plus-square fa-2x aadpres"></i></a> <%} %>
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
														<small style="color: #fff;" class="hidden">(Drag &
															Drop)</small>
												</a>
												</span>

											</h4>
										</div>
										<div id="addcon2" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingTwo">
											<div class="panel-body" style="padding: 5px !important;">
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




									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingfive">
											<h4 class="panel-title">
												<span> <a style="color: #fff !important;"
													class="collapsed" role="button" data-toggle="collapse"
													data-parent="#accordion" href="#addcon5"
													aria-expanded="false" aria-controls="addcon5"> <i
														class="fa fa-angle-down" aria-hidden="true"></i>&nbsp;Templates
														& Forms
												</a> <a href="#" type="button" class="btn btn-sm btn-primary"
													style="margin-left: 5px;" data-toggle="modal"
													data-target="#selectothertemlate"><i
														class="fa fa-align-justify" aria-hidden="true"></i></a> <span>
														<a href="#"
														onclick="opencPopup('OtherTemplate?selectedid=15')"><i
															style="color: white;" title="Add Template"
															class="fa fa-plus-square fa-2x aadpres"></i></a>
												</span>
												</span>

											</h4>
										</div>
										<div id="addcon5" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingfive">
											<div class="panel-body scrollirf">
												<s:if test="otherTemplateList.size>0">
													<span style="font-weight: bold">Template : </span>
													<span class="pull-right" style="padding-right: 3px;"><a
														href="#"
														onclick="setOtherTemplateBySpeciality('',<s:property value="diaryUser"/>,'add')"><i
															class="fa fa-refresh"></i></a></span>
													<% int aot=1;%>
													<table width="100%" class="filtertab" id="templatetableid">
														<s:iterator value="otherTemplateList">
															<tr>
																<td><%=aot %>.</td>
																<td><a href="#"
																	onclick="setselectedtemplatedata('<s:property value="title"/>','<s:property value="id"/>')"><s:property
																			value="title" /></a></td>

															</tr>
															<%aot++; %>
														</s:iterator>
													</table>

												</s:if>
											</div>
										</div>
									</div>

									<div class="panel panel-default hidden">
										<div class="panel-heading" role="tab" id="headingsix">
											<h4 class="panel-title">
												<a class="collapsed" role="button" data-toggle="collapse"
													data-parent="#accordion" href="#addcon6"
													aria-expanded="false" aria-controls="addcon6">
													Discharge Patient </a>
											</h4>
										</div>
										<div id="addcon6" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingsix">
											<div class="panel-body">
												<div style="margin-bottom: 5px !important;">
													<span>Outcome</span>
													<s:select cssClass="form-control" name="dischrgeOutcomes"
														id="dischrgeOutcomes" list="dischargeOutcomeList"
														listKey="id" listValue="name" headerKey="0"
														headerValue="Select Outcome" />
												</div>
												<div style="margin-bottom: 5px !important;">
													<span>Status</span>
													<s:select cssClass="form-control" name="dischargeStatus"
														id="dischargeStatus" list="dischargeStatusList"
														listKey="id" listValue="name" headerKey="0"
														headerValue="Select Status" />
												</div>

												<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

													<div class="form-group">

														<div class="row">
															<div class="col-lg-6 col-md-6 volvo">
																<label for="exampleInputEmail1">Date</label>
																<s:textfield cssClass="form-control" id="dischargedate"
																	name="dischargedate" />
															</div>
															<div class="col-lg-3 col-md-3">
																<label for="exampleInputEmail1">HH</label>
																<s:select cssStyle="width:42px;" name="hour" id="hour"
																	list="hourList" cssClass="form-control" headerKey="0"
																	headerValue="Select" />
															</div>
															<div class="col-lg-3 col-md-3">
																<label for="exampleInputEmail1">MM</label>
																<s:select cssStyle="width:42px;" name="minute"
																	id="minute" list="minuteList" cssClass="form-control"
																	headerKey="0" headerValue="Select" />
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
														<button onclick="saveOnlyConsultationNote()" type="button"
															class="btn btn-primary discharight">Discharge</button>

													</div>

												</div>
											</div>
										</div>
									</div>

								</div>

								<div style="margin-top: 5px; border: 2px solid #6699CC;">
									<div id="paragraphs" style="padding: 5px;">
										<!--   <a href="#" class="btn btn-sm btn-success" style="float: right;background-color: #555;" onclick="showopdcontxtoneditor('consNote')">Add</a><br> -->
										<!-- <a href="#" type="button" class="btn btn-info" onclick="dispDIv()" >+</a>  -->

										<div class="heiige">
											<input type="text" class="form-control"
												placeholder="Search diagnosis here" id="newdiagnosis"
												onkeyup="searchdiagnosisEmrJSON(this.value)"
												style="width: 80%;" /> <a href="#" type="button"
												class="btn btn-default hidden" onclick="dispDIv()"
												style="width: 100%; margin-bottom: 5px;">Add New
												Diagnosis</a> <a href="#" class="btn btn-sm btn-success"
												style="float: right; background-color: #555;"
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
															placeholder="ICD Code" id="icdcode" style="width: 100%;">
													</div>
													<div class="form-group" style="width: 10%;">
														<a href="#" class="btn-success btn-sm btn"
															onclick="addnewCOndition1()"><i class="fa fa-plus"></i></a>
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
													<!--  <tr id="dispid" class="hidden">
                                          <td>
                                              <input type="text" class="form-control" placeholder="Enter New Diagnosis" id="newcondition" style="border: 1px solid #ddd;margin-top: 8px;width: 49%;">
                                              <input type="text" class="form-control" placeholder="Enter ICD Code" id="icdcode" style="border: 1px solid #ddd;margin-top: 8px;width: 50%;">
                                          	  <input type="button" onclick="addnewCOndition1()" class="btn btn-sm btn-info" style="margin-top: 8px;" value="Add New" />
                                          </td>
                                          
                                        </tr>   -->
													<%--  <s:iterator value="treatmentTypeList">
					                 <tr>
									  <td>
										<input class="concase" type="checkbox" onclick="showopdcontxtoneditornew(this.value,this.checked)"  id="chh<s:property value="id"/>" 
										name="ch<s:property value="id"/>" value="<s:property value="id"/>">
										<input class="concase" type="checkbox" onclick="showopdcontxtoneditornew(<s:property value="id"/>)"  id="chh<s:property value="id"/>" 
										name="ch<s:property value="id"/>" value="<s:property value="id"/>">
										<span id='ccck<s:property value="id"/>' ><s:property value="treatmentType"/></span><br> 
										<span style="cursor: pointer;" onclick="showopdcontxtoneditornew('<s:property value="treatmentType"/>")><s:property value="treatmentType"/></span><br>
										<td>
										</tr>
									</s:iterator> --%>

												</tbody>
											</table>
										</div>



									</div>


								</div>


								<div></div>

							</div>

						</div>


						<!-- <div class="col-lg-12">
							<div class="col-lg-2 col-md-2 manasawee">
								<button style="width: 100px;" type="submit"
									class="btn btn-primary">Save</button>
							</div>
						</div> -->
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
				&nbsp;&nbsp;&nbsp;<b> Follow Up After: <input type="number"
					class="form-control" style="width: 7%"
					placeholder="add followup days" name="" id="fllowupdays"
					onchange="givefollowuptoemr()"> Days
				</b>
				<label>&nbsp;&nbsp;&nbsp;&nbsp; Book Follow Up Apmt
					&nbsp;&nbsp;</label>
				<input type="checkbox" name='bkapmtipd' id='bkapmtipd'>

				<div class="modal-footer">
					<button onclick="saveAddConsultationNote()" title="Save"
						type="button" class="btn btn-primary" id='savenotesemrbtn'>Save</button>
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
				<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
					<s:form action="saveotnotesNotAvailableSlot" theme="simple">
						<s:hidden name="id" id="id" />
						<div class="">


							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
								style="padding: 0px;">
								<div class="">
									<div class="col-lg-12 col-md-12">
										<div class="form-group">
											<img src="cicon/mic_off.png" class="img-responsive micimg"
												onclick="startConverting1(this)" title="Microphone"
												id="changer"></img>

										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
										style="margin-top: 10px;">
										<!--
									     <textarea name="notes" cols="" rows="10" tabindex="119" class="form-control foemse" title=""></textarea>
									    -->
										<s:textarea style="height: 250px;" rows="10" cols="8"
											id="otnotes" name="otnotes"
											cssClass="showToolTip form-control" data-toggle="tooltip"
											title="Enter Other Template Text"
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
<s:form action="updateImageDateOfClientEmr" id="updateImageDateOfClient"
	theme="simple">
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
				<div class="modal-body" style="height: 550px; overflow-y: scroll;">
					<button type="button" class="btn btn-primary"
						onclick="updateImageData();">Update Image</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					<%@ include file="/minipaint/editor.jsp"%>
				</div>





				<!--    <div class="modal-footer">
               
                	<button type="submit" class="btn btn-default" onclick="updateImageData()">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    
                </div> -->
			</div>
		</div>
	</div>
</s:form>

<!--Edit Quick Chart Image-->
<%--    <div class="modal fade" id="editQuickChartPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" style="width: 990px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><i class="fa fa-plus"></i>Edit Image</h4>
                </div>
                
                <div class="modal-body">
               
                <div class="row">
                <div class="col-lg-12">
				<%@ include file="/minipaint/editor.jsp"%>
				</div>	
				
                </div>
               	  
                </div>
                
                <div class="modal-footer">
               
                	<button type="submit" class="btn btn-default" onclick="updateImageData()">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    
                </div>
            </div>
        </div>
    </div> --%>


<!--Symbol Popup Model-->
<div class="modal fade" id="symbolPopup" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
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
							src="emr/img/linea-arrows-10_e000(0)_48.png" name="image3" /> <img
							src="emr/img/linea-arrows-10_e013(1)_48.png" name="image4" /> <img
							src="emr/img/linedownarow.png" name="image5" /> <img
							src="emr/img/linedownarowright.png" name="image6" /> <img
							src="emr/img/linedownbreakleft.png" name="image7" /> <img
							src="emr/img/linedownbreakright.png" name="image8" /> <img
							src="emr/img/lineupdownarow.png" name="image9" /> <img
							src="emr/img/rightarow.png" name="image10" /> <img
							src="emr/img/southpoll.png" name="image11" /> <img
							src="emr/img/uparow.png" name="image12" />
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
		</div>
	</div>
</div>


<!--Edit Consultation Note Model-->
<div class="modal fade" id="editConsultationNote" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-keyboard="false" data-backdrop="static">
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
					<button onclick="updatesaveConsultationNote()" type="button"
						class="btn btn-primary topsave">Save</button>
				</h4>
			</div>
			<s:form action="editConsultationNoteEmr" method="post" theme="simple"
				id="editconsultationFrm">

				<div class="modal-body">
					<div class="row row1">


						<div class="marlft37">
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12 paddingnil ">

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
												<s:hidden name="apmtId" value="%{apmtId}" id="hdneditapmtid" />
											</div>
										</div>
										<div class="col-lg-3 col-md-3 setplubtn hidden">
											<a type="button" onclick="setAllDiagnosis('editconsNote')"
												href="#" class="btnman btn-new"><i
												class="fa fa-plus-square"></i></a>
										</div>
									</div>
									<input type="button" value="SOAP"
										onclick="setSOS('editconsNote')" class="btnman btn-new hidden">
									<input type="button" value="Initial"
										onclick="setInitial('editconsNote')"
										class="btnman btn-new hidden"> <input type="button"
										value="Problem" onclick="setProblem('editconsNote')"
										class="btnman btn-new hidden"> <input type="button"
										value="Examination" onclick="setExamination('editconsNote')"
										class="btnman btn-new hidden"> <input type="button"
										value="Assessment" onclick="setAssessment('editconsNote')"
										class="btnman btn-new hidden"> <input type="button"
										value="Treatment" onclick="setTreatment('editconsNote')"
										class="btnman btn-new hidden"> <input type="button"
										value="Plan" onclick="setPlan('editconsNote')"
										class="btnman btn-new hidden"> <a type="button"
										onclick="setAllDiagnosis('editconsNote')" href="#"
										class="btnman btn-new hidden"><i class="fa fa-plus-square"></i></a>
									<!-- <input
					 	type="button" value="Date/Time"
						onclick="setDateTime('editconsNote')" class="btnman btn-new">&nbsp;&nbsp;&nbsp; -->



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
												<a href="#" onclick="toggleDischarge()">Discharge Client</a>
											</div>


										</div>

									</div>
								</div>

							</div>

							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 aadedf">

								<div class="panel-group managewidhe" id="accordion"
									role="tablist" aria-multiselectable="true">
									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingOne">
											<h4 class="panel-title">
												<span> <a class="templateformeditcolor" role="button"
													data-toggle="collapse" data-parent="#accordion"
													href="#editcon1" aria-expanded="true"
													aria-controls="editcon1"> Medicine </a>
												</span>
												<%if (!loginInfo.getJobTitle().equals("Pathlab")) {%>
												<a href="#"><i onclick="showeditpriscriptionpopup()"
													title="Add Prescription"
													class="fa fa-plus-square fa-2x aadpres"
													style="margin-top: -12px;"></i></a>
												<% }%>
											</h4>
										</div>
										<div id="editcon1" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingOne">
											<div class="panel-body">
												<div id="editalldataprisctable" class="row rowblank">

													<%--  </div>
						        <div class="row">
						        <span><p class="pdatpree">26/11/2015 <a href="" title="View Prescription" class="editpricon"><i class="fa fa-eye"></i></a> <a href="" title="Edit Prescription" class="editpricon"><i class="fa fa-edit"></i></a></p> </span>
						        </div> --%>
													<!-- <table class="table table-bordered" cellspacing="0" width="100%">
                                    <thead>
                                        <tr class="tableback">
                                            <th>Date</th>
                                            <th>Drug Name</th>
                                            <th>Dose</th>
                                            <th>Frequency</th>
                                            <th>Days</th>
                                          

                                        </tr>
                                    </thead>
                                    <tbody id="editalldataprisctable">
                                        <tr>
                                            <td>6/11/2015</td>
                                            <td>Rosavel 10</td>
                                            <td>10 mg</td>
                                            <td>1-1</td>
                                            <td>10</td>

                                        </tr>
                                      


                                    </tbody>
                                </table> -->
												</div>
											</div>
										</div>
									</div>








									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingThree">
											<h4 class="panel-title">
												<span> <a class="collapsed templateformeditcolor"
													role="button" data-toggle="collapse"
													data-parent="#accordion" href="#editcon3"
													aria-expanded="false" aria-controls="editcon3">
														Investigation </a>
												</span>
												<%if (!loginInfo.getJobTitle().equals("Medical Store")) {%>
												<a href="#"><i onclick="showEditInvestigationPopup()"
													title="Add Investigation"
													class="fa fa-plus-square fa-2x aadpres"
													style="margin-top: -12px;"></i></a>
												<%} %>
											</h4>
										</div>
										<div id="editcon3" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingThree">
											<div class="panel-body">
												<div id="alleditinvesttable" class="row rowblank"></div>

											</div>
										</div>
									</div>

									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingTwo">
											<h4 class="panel-title">
												<a class="collapsed templateformeditcolor" role="button"
													data-toggle="collapse" data-parent="#accordion"
													href="#editcon2" aria-expanded="false"
													aria-controls="editcon2"> Symbols <small
													style="color: #fff;">(Drag & Drop)</small>
												</a>
											</h4>
										</div>
										<div id="editcon2" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingTwo">
											<div class="panel-body" style="padding: 5px !important;">
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

									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingfour">
											<h4 class="panel-title">
												<a class="collapsed templateformeditcolor" role="button"
													data-toggle="collapse" data-parent="#accordion"
													href="#editcon4" aria-expanded="false"
													aria-controls="editcon3"> Templates & Forms </a> <a
													href="#" type="button"
													class="btn btn-sm btn-primary templateformedit"
													style="margin-left: 5px;" data-toggle="modal"
													data-target="#editselectothertemlate"><i
													class="fa fa-align-justify" aria-hidden="true"></i></a>
											</h4>
										</div>
										<div id="editcon4" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingfour">
											<div class="panel-body">
												<s:if test="otherTemplateList.size>0">
													<span style="font-weight: bold">Template : </span>



													<% int ot=1;%>
													<table width="100%" id="edittemplatetableid">
														<s:iterator value="otherTemplateList">
															<tr>
																<td><%=ot %>.</td>
																<td><a href="#"
																	onclick="setselectedtemplatedata('<s:property value="title"/>','<s:property value="other_template_text"/>')"><s:property
																			value="title" /></a></td>

															</tr>
															<%ot++; %>
														</s:iterator>
													</table>

												</s:if>
											</div>
										</div>
									</div>


									<div class="panel panel-default hidden">
										<div class="panel-heading" role="tab" id="headingfive">
											<h4 class="panel-title">
												<a class="collapsed" role="button" data-toggle="collapse"
													data-parent="#accordion" href="#editcon5"
													aria-expanded="false" aria-controls="editcon5">
													Discharge Client </a>
											</h4>
										</div>
										<div id="editcon5" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingfive">
											<div class="panel-body">
												<div style="margin-bottom: 5px !important;">
													<span>Outcome</s> <s:select cssClass="form-control"
															name="dischrgeOutcomes" id="eddischrgeOutcomes"
															list="dischargeOutcomeList" listKey="id" listValue="name"
															headerKey="0" headerValue="Select Outcomes" />
												</div>
												<div style="margin-bottom: 5px !important;">
													<span>Status</span>
													<s:select cssClass="form-control" name="dischargeStatus"
														id="eddischargeStatus" list="dischargeStatusList"
														listKey="id" listValue="name" headerKey="0"
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
																	id="edithour" list="hourList" cssClass="form-control"
																	headerKey="0" headerValue="Select"></s:select>
															</div>
															<div class="col-lg-3 col-md-3">
																<label for="exampleInputEmail1">MM</label>
																<s:select cssStyle="width:42px;" name="minute"
																	id="editminute" list="minuteList"
																	cssClass="form-control" headerKey="0"
																	headerValue="Select" />
															</div>
														</div>



														<%-- <s:textfield type="email" cssClass="form-control" id="admissiontime" name="admissiontime" /> --%>

													</div>



												</div>
												<div>

													<div class="col-lg-3 col-md-3 col-xs-3 ditrxas">
														<label>Discharge:</label>
													</div>
													<div class="col-lg-2 col-md-2 col-xs-2">
														<s:checkbox cssStyle="margin-right:110px;" theme="simple"
															name="chkDischarge" id="edchkDischarge" />
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
											<a href="#" class="btn btn-sm btn-success"
												style="float: right; background-color: #555;"
												onclick="savediagnosisfasteditEmrJSON()">Save</a>
											<div class="form-inline">
												<input type="text" class="form-control"
													id="newdiagnosisedit" placeholder="Search diagnosis here"
													onkeyup="searchdiagnosiseditEmrJSON(this.value)" />
											</div>
											<s:hidden name="odconditionstr" id="eodconditionstr" />
											<table class="table table-responsive"
												style="width: 100%; border: none;">

												<tbody id="conditiontbody2"
													style='height: 275px; display: block; overflow: scroll; width: 100% !important;'>
													<!--  <tr id="dispid" class="hidden">
		                                          <td>
		                                              <input type="text" class="form-control" placeholder="Enter New Diagnosis" id="newcondition" style="border: 1px solid #ddd;margin-top: 8px;width: 49%;">
		                                              <input type="text" class="form-control" placeholder="Enter ICD Code" id="icdcode" style="border: 1px solid #ddd;margin-top: 8px;width: 50%;">
		                                          	  <input type="button" onclick="addnewCOndition1()" class="btn btn-sm btn-info" style="margin-top: 8px;" value="Add New" />
		                                          </td>
		                                        </tr>   -->
													<%--  <s:iterator value="treatmentTypeList">
							                 <tr>
											  <td>
												<input class="concase" type="checkbox" onclick="showopdcontxtoneditornew(this.value,this.checked)"  id="chh<s:property value="id"/>" 
												name="ch<s:property value="id"/>" value="<s:property value="id"/>">
												<input class="concase" type="checkbox" onclick="showopdcontxtoneditornew(<s:property value="id"/>)"  id="chh<s:property value="id"/>" 
												name="ch<s:property value="id"/>" value="<s:property value="id"/>">
												<span id='ccck<s:property value="id"/>' ><s:property value="treatmentType"/></span><br> 
												<span style="cursor: pointer;" onclick="showopdcontxtoneditornew('<s:property value="treatmentType"/>")><s:property value="treatmentType"/></span><br>
												<td>
												</tr>
											</s:iterator> --%>
												</tbody>
											</table>

										</div>

									</div>

								</div>


							</div>

						</div>

						<!-- <div class="col-lg-12">
				<div class="col-lg-2 col-md-2 manasawee">
								<button style="width: 100px;" type="submit"
									class="btn btn-primary">Save</button>
							</div>
				</div> -->
					</div>




					<div class="row row2 hidden">
						<div class="col-lg-8 discharge " id="">

							<div class="col-lg-3 col-md-3 col-xs-6">
								<button onclick="updatesaveConsultationNote()" type="button"
									class="btn btn-primary" style="width: 122px;">Save</button>
							</div>

							<div class="col-lg-3 col-md-3 col-xs-6" id="eddischargeclientdiv">
								<a href="#" onclick="edtoggleDischarge()">Discharge Client</a>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-6">
								<button
									onclick="openEmrPopup('printconsnoteEmr?clientid=<s:property value="clientname"/>&amptid=<s:property value="apmtId"/>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=0');"
									type="button" style="width: 122px;" class="btn btn-primary">Print</button>
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
												<!-- 
						<button style="width: 100px;" type="button"
							class="btn btn-primary" data-dismiss="modal">Close</button> -->
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
				&nbsp;&nbsp;&nbsp;<b> Follow Up After: <input type="number"
					class="form-control" style="width: 7%"
					placeholder="add followup days" name="" id="fllowupdays1"
					onchange="givefollowuptoemr1()"> Days
				</b>

				<div class="modal-footer">
					<button onclick="updatesaveConsultationNote()" type="button"
						class="btn btn-primary">Save</button>
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
<div class="modal fade" id="editsymbolPopup" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
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
							src="emr/img/linea-arrows-10_e000(0)_48.png" name="image3" /> <img
							src="emr/img/linea-arrows-10_e013(1)_48.png" name="image4" /> <img
							src="emr/img/linedownarow.png" name="image5" /> <img
							src="emr/img/linedownarowright.png" name="image6" /> <img
							src="emr/img/linedownbreakleft.png" name="image7" /> <img
							src="emr/img/linedownbreakright.png" name="image8" /> <img
							src="emr/img/lineupdownarow.png" name="image9" /> <img
							src="emr/img/rightarow.png" name="image10" /> <img
							src="emr/img/southpoll.png" name="image11" /> <img
							src="emr/img/uparow.png" name="image12" />
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

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
				<h4 class="modal-title" id="uploadDocTitle">Upload New Document</h4>
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
									placeholder="Enter Document Note" rows="3" name="documentDesc"
									id="documentDesc"></s:textarea>
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
					<s:hidden id="clientname" value="%{clientname}" name="clientname"></s:hidden>
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
<div class="modal fade" id="priscriptionpopup" tabindex="-1"
	role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true"
	data-keyboard="false" data-backdrop="static"
	style="background-color: rgba(0, 0, 0, 0.72);">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"
			style="margin-left: -100px; margin-right: -100px;">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h5 class="modal-title" id="">
					Create Prescription For <b class="pname" id="priscmyModalLabel">NAME:
					</b>
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
				<h4 class="modal-title" id="myModalLabel">Edit Medicine Name</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-3">
						<label>Medicine Name</label>
					</div>
					<div class="col-lg-9">

						<input type="text" id="priscmdcineedit" name="priscmdcineedit"
							class="form-control showToolTip"
							onblur="setPuresevaExistingClientData()"
							placeholder="Enter Medicine Name" title="Enter Medicine Name"
							data-toggle="tooltip " />
					</div>
				</div>
				<br>

				<%if(loginInfo.getOutoprisc()==1){ %>
				<div class="row">
					<div class="col-lg-3">
						<label>Search Medicine</label>
					</div>
					<div class="col-lg-9">

						<s:select cssClass="form-control showToolTip chosen-select"
							name="mdicinenamesrch" id="mdicinenamesrch"
							onchange="getsrchdmdicinename(this.value)" list="medicineList"
							tabindex="1" listKey="id" listValue="genericname" headerKey="0"
							headerValue="Select Medicine">
						</s:select>
					</div>
				</div>
				<br>
				<%} %>


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
<div class="modal fade" id="investigationpopup" tabindex="-1"
	role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true"
	data-keyboard="false" data-backdrop="static"
	style="background-color: rgba(0, 0, 0, 0.72);">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h5 class="modal-title" id="">
					Create Investigation For <b class="pname" id="invstcmyModalLabel">NAME:
					</b>
				</h5>
			</div>
			<div class="modal-body" style="padding: 0px;">


				<%@ include file="/emr/pages/addInvestigation.jsp"%>


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
				<h4 class="modal-title" id="myModalLabel">Add Medical Record</h4>
			</div>
			<s:form id="addMedicalRecords" action="addMedicalRecordEmr"
				theme="simple">
				<div class="modal-body" style="min-height: 450px;">
					<s:hidden id="clientname" value="%{clientname}" name="clientname"></s:hidden>
					<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
					<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
					<s:hidden id="addmediclapmtId" name="apmtId" />
					<div class="">
						<table class="table" id="medicalHistoryTable">

							<tbody>
								<tr>

									<td><s:select id="medicalHistory[0].medicalRecordType"
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

									<td><textarea class="form-control" rows="3" type="text"
											name="medicalHistory[0].medicalHistoryNotes"
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
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="saveMedicalRecords()">
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
				<h4 class="modal-title" id="myModalLabel">Edit Medical Record</h4>
			</div>
			<s:form id="editMedicalRecords" action="editMedicalRecordEmr"
				theme="simple">
				<div class="modal-body" style="min-height: 450px;">
					<s:hidden id="clientname" value="%{clientname}" name="clientname"></s:hidden>
					<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
					<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
					<s:hidden id="editmediclapmtId" name="apmtId" />

					<div class="">
						<table class="table" id="medicalHistoryTable">

							<tbody>
								<tr>

									<td><s:select id="medicalRecordType"
											name="medicalRecordType" list="medicalRecordTypeList"
											listKey="medicalRecordType" listValue="medicalRecordType"
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
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="updateMedicalRecords()">
						<i class="fa fa-save"></i> Save
					</button>
				</div>
			</s:form>
		</div>
	</div>
</div>




<!--Play Video Model-->
<div class="modal fade" id="videoClipPlay" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
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

				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
	aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false"
	data-backdrop="static">
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
								type="text" id="pureemail" name="pureemail" class="form-control"
								onblur="setPuresevaExistingClientData()"
								placeholder="Enter Email" data-toggle="tooltip " />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">First Name</label> <input
								type="text" id="purefname" name="purefname" class="form-control"
								placeholder="Enter First Name" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Last Name</label> <input
								type="text" id="purelname" name="purelname" class="form-control"
								placeholder="Enter Last name" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Mobile Number</label> <input
								type="text" id="puremob" name="puremob" class="form-control"
								placeholder="Enter Mobile Number" data-toggle="tooltip" />
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
	<div class="modal fade" id="docfilterpopup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
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

					<s:hidden id="fclientname" value="%{clientname}" name="clientname"></s:hidden>
					<s:hidden id="fdiaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
					<s:hidden id="fcondition" value="%{condition}" name="condition"></s:hidden>
					<s:hidden id="feditDoctId" name="editDoctId"></s:hidden>
					<s:hidden id="fdocapmtId" name="apmtId" />
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>

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
								placeholder="Enter mobile number" title="Enter mobile number"
								data-toggle="tooltip" />
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
	<div class="modal fade" id="otpemroppup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-md">
			<div class="modal-content modelmd">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="display: none;" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="">Enter OTP sent on your mobile</h4>
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
								type="text" id="confpassd" name="confpassd" class="form-control"
								placeholder="Enter Password" data-toggle="tooltip" />
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
	<div class="modal fade" id="cofpasspopup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
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
				<h4 class="modal-title" id="myModalLabel">Treatment Details</h4>
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
												<s:form theme="simple" action="deleteAllConsultationNoteEmr"
													id="deleteAllConsultationNote_%{id}">
													<s:hidden id="id" value="%{id}" name="appointmentid"></s:hidden>
													<s:hidden id="clientname" value="%{clientname}"
														name="clientname"></s:hidden>
													<s:hidden id="diaryUser" value="%{diaryUserId}"
														name="diaryUser"></s:hidden>
													<s:hidden id="condition" value="%{condition}"
														name="condition"></s:hidden>
													<s:hidden id="delallapmtId" name="apmtId" value="%{id}" />

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
												<s:form theme="simple" action="deleteAllConsultationNoteEmr"
													id="deleteAllConsultationNote_%{id}">
													<s:hidden id="id" value="%{id}" name="appointmentid"></s:hidden>
													<s:hidden id="clientname" value="%{clientname}"
														name="clientname"></s:hidden>
													<s:hidden id="diaryUser" value="%{diaryUserId}"
														name="diaryUser"></s:hidden>
													<s:hidden id="condition" value="%{condition}"
														name="condition"></s:hidden>
													<s:hidden id="delallapmtId" name="apmtId" value="%{id}" />

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
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
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
								<s:select cssClass="form-control chosen-select" headerKey="0"
									headerValue="Select Type" name="doctType" id="searchdoc"
									onchange="filtertype(this.value)"
									list="{'GP Doc','TP Doc','Medical Record','Consultant Report','Assessment Report','Investigation','Admission Form','Discharge Form','Nursing','All','Optional Form','Declaration Form','OT Form'}"
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
								<th>Date</th>
								<th>Document Type</th>

								<th>File Name</th>
								<th>Document Name/Note</th>
								<th>Actions</th>
							</tr>
						</thead>
						<% int count = 1;
												%>
						<tbody id="docslist" class="table table-bordered">

							<s:iterator value="docList" status="rowstatus">

								<tr>
									<th scope="row"><%=count%>.</th>
									<td>
										<p class="marraig"
											style="margin-bottom: -2px; font-size: 11px;">
											<s:property value="lastModified" />
										</p>



									</td>
									<td>
										<p class="font11">
											<s:property value="doctType" />
										</p>
									</td>

									<td>
										<%
																	count++;
																%> <a
										href="downloadDocEmr?filename=<s:property value="fileName"/>&id=<s:property value="id"/>"
										title="Download" class="font11"> <s:if test="invstid>0">
												<s:property value="invstFoleName" />
											</s:if> <s:else>
												<s:property value="fileName" />
											</s:else>
									</a>
									</td>
									<td>
										<p class="docuto">
											<s:property value="description" />
											&nbsp;<a href="javascript:void(0)" data-toggle="modal"
												data-target="#uploaddoc"
												onclick="editDocuments('<s:property value = "id"/>','<s:property value = "doctType"/>','<s:property value = "description"/>','<s:property value = "fileName"/>')"
												title="read more">more</a>
										</p>
									</td>

									<td class="">
										<%if(!loginInfo.getLoginType().equals("shareemr")){ %> <a
										href="javascript:void(0)" data-toggle="modal"
										data-target="#uploaddoc"
										onclick="editDocuments('<s:property value = "id"/>','<s:property value = "doctType"/>','<s:property value = "description"/>','<s:property value = "fileName"/>')"
										title="Edit"><i class="fa fa-edit"></i></a> <% }else{%> <i
										class="fa fa-edit"></i> <%} %>&nbsp;&nbsp; <s:form
											theme="simple" id="deleteDocuments_%{id}"
											action="deleteDocumentsEmr">
											<s:hidden name="deleteDoctId" value="%{id}" id="deleteDoctId" />
											<s:hidden id="clientname" value="%{clientname}"
												name="clientname"></s:hidden>
											<s:hidden id="diaryUser" value="%{diaryUser}"
												name="diaryUser"></s:hidden>
											<s:hidden id="condition" value="%{condition}"
												name="condition"></s:hidden>

											<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
											<a href="#"
												onclick="deleteDocuments('<s:property value = "id"/>')"
												title="Delete" class="text-danger"><i
												class="fa fa-trash-o"></i></a>
											<% }else{%>
											<i class="fa fa-trash-o"></i>
											<%} %>
										</s:form>
									</td>
								</tr>
							</s:iterator>

						</tbody>

						<tbody id="admissionlist" class="table table-bordered">



							<!-- admission forms -->

							<%
												int ipdcount = 1;
												%>
							<s:iterator value="addmissionList">

								<tr>
									<th scope="row"><%=ipdcount%>.</th>

									<td><s:if test="casualtyipd==0">
											<p class="font11">IPD Form</p>
										</s:if> <s:if test="casualtyipd==2">
											<p class="font11">Daycare Form</p>
										</s:if> <s:else>
											<p class="font11">Casualty Form</p>
										</s:else></td>
									<td>
										<p>

											<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
											<s:if test="casualtyipd==0">
												<a href="#"
													onclick="openEmrPopup('editCommonnew?selectedid=<s:property value = "id"/>&action=0')"
													class="font11"><s:property value="admissiondate" /> </a>
											</s:if>
											<s:if test="casualtyipd==2">
												<a href="#"
													onclick="openEmrPopup('editCommonnew?selectedid=<s:property value = "id"/>&action=2')"
													class="font11"><s:property value="admissiondate" /> </a>
											</s:if>
											<s:else>
												<a href="#"
													onclick="openEmrPopup('editCommonnew?selectedid=<s:property value = "id"/>&action=1')"
													class="font11"><s:property value="admissiondate" /> </a>
											</s:else>
											<% }else{%>
											<a href="#" class="font11"><s:property
													value="admissiondate" /> </a>
											<% }%>
										</p>



									</td>
									<td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td>
									<td class="text-center"><a href="#"
										onclick="openEmrPopup('printCommonnew?selectedid=<s:property value="id"/>')"
										title="Print" class="assesprint"><i class="fa fa-print"></i></a>
									</td>
									<td class="text-center"><a href="#" title="Delete"
										class="text-danger">Can't Delete</a></td>
								</tr>
								<%
															ipdcount++;
														%>
							</s:iterator>
						</tbody>

						<!-- Gynic Form List by jitu -->

						<tbody id="gynicFormList" class="table table-bordered">

							<s:iterator value="gynicFormList">

								<tr>
									<th scope="row">1</th>

									<td>
										<p>

											<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
											<a href="#"
												onclick="openEmrPopup('editgynicformIpd?selectedid=<s:property value = "id"/>&action=0')"
												class="font11"><s:property value="commencing" /> </a>
											<% }else{%>
											<a href="#"
												onclick="openEmrPopup('editgynicformIpd?selectedid=<s:property value = "id"/>&action=0')"
												class="font11"><s:property value="commencing" /> </a>
											<% }%>
										</p>


									</td>
									<td><s:if test="formtype==1">
											<p class="font11">Obstetrics Form</p>

										</s:if> <s:elseif test="formtype==2">
											<p class="font11">Gynaecology Form</p>
										</s:elseif> <s:else>
											<p class="font11">Infertility Form</p>
										</s:else></td>

									<td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td>
									<td class="text-center"><a href="#"
										onclick="openEmrPopup('printgynicformIpd?selectedid=<s:property value="id"/>')"
										title="Print" class="assesprint"><i class="fa fa-print"></i></a>
									</td>
									<td class="text-center"><a href="#" title="Delete"
										class="text-danger"><i class="fa fa-trash-o"></i></a></td>
								</tr>
							</s:iterator>
						</tbody>



						<tbody id="dischargelist" class="table table-bordered">



							<!-- Discharge forms -->

							<%
													int disipdcount = 1;
												%>
							<s:iterator value="ipdsdischargeList">

								<tr>
									<th scope="row"><%=disipdcount%>.</th>

									<td>
										<p class="font11">Discharge Form</p>
									</td>
									<td>
										<p>
											<%if(!loginInfo.getLoginType().equals("shareemr")){ %>
											<a href="#"
												onclick="openEmrPopup('dischargeCommonnew?selectedid=<s:property value = "id"/>&clientid=<s:property value="clientid"/>')"
												class="font11"><s:property value="admissiondate" /> </a>
											<%}else{ %>
											<a href="#" class="font11"><s:property
													value="admissiondate" /> </a>
											<% }%>
										</p>

									</td>
									<td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td>
									<td class="text-center"><a href="#"
										onclick="openEmrPopup('printdischargeCommonnew?selectedid=<s:property value="id"/>')"
										title="Print" class="assesprint"><i class="fa fa-print"></i></a>
									</td>
									<td class="text-center"><a href="#" title="Delete"
										class="text-danger">Can't Delete</a></td>
								</tr>
								<%
														disipdcount++;
														%>
							</s:iterator>
						</tbody>
						<tbody id="assessmentlist" class="table table-bordered">



							<!-- Assesment forms -->

							<%
											 count = 1;
												%>
							<s:iterator value="assessmentFormsList" status="rowstatus">
								<s:if test="type==1">
									<tr>
										<th scope="row"><%=count%>.</th>

										<td>
											<p class="font11">Assesment Form</p>
										</td>
										<td>
											<p>
												<a href="#"
													onclick="openEmrPopup('getDetailsAssessmentTemplate?id=<s:property value = "id"/>')"
													class="font11"> <s:property value="templateName" />
												</a>
											</p>
										</td>
										<td>
											<p class="font11">
												<s:property value="lastmodified" />
											</p>

										</td>
										<td><a href="#"
											onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2&action=dtr&formtype=<s:property value="formtype"/>')">
												<i class="fa fa-line-chart" aria-hidden="true"></i>
										</a></td>
										<td class="text-center"><a href="#"
											onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2&action=print&formtype=<s:property value="formtype"/>')"
											title="Print" class="assesprint"><i class="fa fa-print"></i></a>
										</td>

										<td class="text-center"><a href="#" title="Delete"
											class="text-danger"><i class="fa fa-trash-o"></i></a></td>
									</tr>
								</s:if>

								<s:else>
									<tr>
										<th scope="row"><%=count%>.</th>

										<td>
											<p class="font11">Assesment Form</p>
										</td>
										<td>
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
										</td>
										<td>
											<p class="font11">
												<s:property value="lastmodified" />
											</p>

										</td>
										<td><a href="#"
											onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2&action=dtr&formtype=<s:property value="formtype"/>')">
												<i class="fa fa-line-chart" aria-hidden="true"></i>
										</a></td>
										<td class="text-center"><a href="#"
											onclick="openEmrPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=1&action=print&formtype=<s:property value="formtype"/>')"
											title="Print" class="assesprint"><i class="fa fa-print"></i></a>
										</td>

										<td class="text-center"><a
											href="delassesmentEmr?selectedid=<s:property value="id"/>&diaryUser=<s:property value="diaryUser"/>&clientname=<s:property value="clientname"/>&condition=<s:property value="condition"/>"
											onclick="return confirmedDelete()" title="Delete"
											class="text-danger"><i class="fa fa-trash-o"></i></a></td>
									</tr>
								</s:else>


								<%
														count++;
													%>
							</s:iterator>




						</tbody>

						<tbody id="optionformlist" class="table table-bordered">
							<!-- Discharge forms -->
							<%
													int optionalformcount = 1;
												%>
							<s:iterator value="optionformlist">
								<tr>
									<th scope="row"><%=optionalformcount%>.</th>

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
									<td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td>
									<td class="text-center"><a href="#"
										onclick="openEmrPopup('printoptionformNotAvailableSlot?id=<s:property value="id"/>')"
										title="Print" class="assesprint"><i class="fa fa-print"></i></a>
									</td>
									<td class="text-center"><a href="#" title="Delete"
										class="text-danger"><i class="fa fa-trash-o"></i></a></td>
								</tr>
								<%
												   		optionalformcount++;
														%>
							</s:iterator>
						</tbody>

						<tbody id="declarationformlist" class="table table-bordered">
							<!-- Discharge forms -->
							<%
													int declarationformcount = 1;
												%>
							<s:iterator value="declarationformlist">
								<tr>
									<th scope="row"><%=declarationformcount%>.</th>

									<td>
										<p class="font11">Declaration Form</p>
									</td>
									<td>
										<p>
											<a href="#"
												onclick="openEmrPopup('editdeclarationformdetailsClient?id=<s:property value="id"/>&clientId=<s:property value="clientId"/>')"
												class="font11"><s:property value="lastModified" /> </a>
										</p>
									</td>
									<td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td>
									<td class="text-center"><a href="#"
										onclick="openEmrPopup('printdeclarationformdetailsClient?id=<s:property value="id"/>&clientId=<s:property value="clientId"/>')"
										title="Print" class="assesprint"><i class="fa fa-print"></i></a>
									</td>
									<td class="text-center"><a href="#" title="Delete"
										class="text-danger"><i class="fa fa-trash-o"></i></a></td>
								</tr>
								<%
												   		declarationformcount++;
														%>
							</s:iterator>
						</tbody>

						<tbody id="otformlistid" class="table table-bordered">
							<!-- Discharge forms -->
							<%
													int otformlistcount = 1;
												%>
							<s:iterator value="otformlist">
								<tr>
									<th scope="row"><%=otformlistcount%>.</th>

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
									<td><a href=""><i class="fa fa-line-chart"
											aria-hidden="true"></i></a></td>
									<td class="text-center"><a href="#"
										onclick="openEmrPopup('printotnotesNotAvailableSlot?id=<s:property value="id"/>')"
										title="Print" class="assesprint"><i class="fa fa-print"></i></a>
									</td>
									<td class="text-center">
										<!-- <a href="#" title="Delete" class="text-danger"><i class="fa fa-trash-o"></i></a> -->
									</td>
								</tr>
								<%
												   		otformlistcount++;
														%>
							</s:iterator>
						</tbody>

					</table>
				</div>


				
			</div>
			<div class="modal-footer hidden">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
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
								<input type="email" class="form-control" id="exampleInputEmail2"
									placeholder="Search Documents">
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
							<s:iterator value="medicalRecordsTypeList" status="rowstatus">

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
											<td class="text-center"><a href="javascript:void(0)"
												data-toggle="modal" data-target="#editmedicalrecord"
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
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
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
				<h4 class="modal-title" id="mma">Diet, Vital and Nursing Care</h4>
			</div>
			<div class="modal-body popmodals">

				<div class="panel-body" id="clinicaln"></div>


			</div>
			<div class="modal-footer hidden">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
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
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
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
							if(loginInfo.getUserType() == 4){
						%>
							<s:if test="%{#userList != 'null'}">
								<s:select cssClass="form-control showToolTip chosen-select"
									id="diaryUser" name="diaryUser" list="userList" listKey="id"
									listValue="diaryUser" headerKey="0" theme="simple"
									headerValue="Select User" onchange="setClientsajax(this.value)" />
							</s:if>
							<%
							}else{
						%>

							<s:if test="%{#userList != 'null'}">
								<s:select cssClass="form-control showToolTip chosen-select"
									id="diaryUser" name="diaryUser" list="userList" listKey="id"
									listValue="diaryUser" headerKey="0" theme="simple"
									headerValue="Select User" onchange="setClientsajax(this.value)" />
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
									<s:select cssClass="form-control showToolTip chosen-select"
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
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- Patient Medial Modal -->
<div class="modal fade modal-draggable" id="patient_media" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-md" role="document">
		<div class="modal-content">
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
								<input type="email" class="form-control" id="exampleInputEmail2"
									placeholder="Search Documents">
							</div>
							<button type="submit" class="btn btn-default">Search</button>
						</form>
					</div>
					<div class=" col-lg-6 col-md-6" style="padding-right: 0px;">
						<a href="" type="button" class="btn btn-primary"
							data-toggle="modal" onclick="uploadVideoPopup()" data-target="#"
							title="Upload New Video Clip" Title="Upload New Video Clip"
							style="float: right;">Upload Video</a> <a href="" type="button"
							class="btn btn-primary" data-toggle="modal" onclick="setEditor()"
							data-target="#editQuickChartPopup" title="Upload Chart"
							Title="Upload Image" style="float: right; margin-right: 5px;">Upload
							Image</a>
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
									<s:hidden name="clientDataId" value="%{id}" id="clientDataId1"></s:hidden>
									<s:hidden id="clientname" value="%{clientname}"
										name="clientname"></s:hidden>
									<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
									<s:hidden id="condition" value="%{condition}" name="condition"></s:hidden>
									<s:hidden id="delimageapmtId" name="apmtId" />
									<i class="fa fa-times delete" onclick="deleteClientImage()"></i>
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
								<%-- <s:form theme="simple" action="deleteClientImageEmr" id = "deleteVideo">
													<s:hidden name = "clientDataId" value="%{id}" id = "clientDataId1"></s:hidden>
													<s:hidden id = "clientname" value="%{clientname}" name = "clientname"></s:hidden>
                 									<s:hidden id = "diaryUser" value="%{diaryUser}" name = "diaryUser"></s:hidden>
                 									<s:hidden id = "condition" value="%{condition}" name = "condition"></s:hidden>
													 <i class="fa fa-times delete" onclick = "deleteClientImage()"></i>	
													</s:form> --%>

							</div>
						</s:iterator>


					</div>


				</div>


			</div>
			<div class="modal-footer hidden">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>


<div class="main hidden">
	<a href="#wheel3" class="wheel-button nw"> <span>+</span>
	</a>
	<ul id="wheel3" data-angle="NW" class="wheel">
		<li class="item"><span id="confidentialid"><a href="#"
				onclick="showconfidentialpopup()" title="Make Confidential"><img
					src="cicon/unlock.png" class="img-responsive" style="width: 100%;" /></a></span></li>
		<li class="item"><span id="shareids"><a href="#"
				onclick="showsharepopup()" title="Share"><img
					src="cicon/share.png" class="img-responsive" style="width: 100%;" /></a></span></li>
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
					listKey="id" listValue="name" cssClass="form-control chosen-select"
					headerKey="0" headerValue="Select Specilization"></s:select>

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
					listKey="id" listValue="name" cssClass="form-control chosen-select"
					headerKey="0" headerValue="Select Specilization"></s:select>

			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default hidden"
				data-dismiss="modal">Close</button>
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
					listKey="id" listValue="name" cssClass="form-control chosen-select"
					headerKey="0" headerValue="Select Specilization"></s:select>

			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default hidden"
				data-dismiss="modal">Close</button>
		</div>
	</div>
</div>

<script type="text/javascript">
$(".modal-draggable .modal-dialog").draggable({
        handle: ".modal-header"
    });
          $(function () {
              $('#main-menu').smartmenus({
                  subMenusSubOffsetX: 1,
                  subMenusSubOffsetY: -8
              });
          });
          
      </script>
<script>
	  $(document).ready(function(){
			$(".wheel-button").wheelmenu({
        trigger: "hover",
        animation: "fly",
        animationSpeed: "fast"
      });
		});
		
	</script>

<script><!--
          $(function () {
        	  
        	  $('.popmodals').slimScroll({
                  height: '450px',
                  railVisible: true,
				  alwaysVisible: true
              });
               $('.popmodals').slimScroll({
                  height: '450px',
                  railVisible: true,
				  alwaysVisible: true
              });
               $('.popmodals123').slimScroll({
                   height: '255px',
                   railVisible: true,
 				  alwaysVisible: true
               });
               $('.hesighsetnew').slimScroll({
                   height: '568px',
                   railVisible: true,
 				  alwaysVisible: true
               });
               $('.heightsetd').slimScroll({
                   height: '200px',
                   railVisible: true,
 				  alwaysVisible: true
               });
               $('.heigsetas').slimScroll({
                   height: '495px',
                   railVisible: true,
                   size: '20px',
 				  alwaysVisible: true
               });
              
               
               
          });
          $(function () {
              $('[data-toggle="tooltip"]').tooltip()
          })
          
           $(document).ready(function () {
           
           	<%if(loginInfo.getLoginType().equals("pc")){%>
           		if(document.getElementById('checkconfidential').value==1){
           		if(document.getElementById('confdentialpass').value==0){
           	  	  $('#cofpasspopup').modal( "show" );
           	  }
           	  }
           	<%}%>
           
           	  <%if(loginInfo.getLoginType().equals("shareemr")){%>
           	  if(document.getElementById('sharedmob').value==0){
           	  	$('#shareduserpopup').modal( "show" );
           	  }
           	  
        	document.getElementById('clientname').disabled=true;
        	document.getElementById('diaryUser').disabled=true;
        	document.getElementById('condition').disabled=true;
        	
        	document.getElementById('shareids').style.display = 'none';
        	
        <%}%>
           
             
              
              <%//String radioId = (String)session.getAttribute("radioId");%>
      		
      		//document.getElementById(<%//radioId%>).checked = true;

          });

      -->
      
      
      function changefun(val){
      
         if(val=="SAOP"){
            setSOS('consNote');
         } else if(val=="Initial"){
         setInitial('consNote');
         } else if(val=="Problem"){
         setProblem('consNote');
         } else if(val=="Examination"){
         setExamination('consNote');
         } else if(val=="Assessment"){
         setAssessment('consNote');
         } else if(val=="Treatment"){
         setTreatment('consNote');
         } else if(val=="Plan"){
         setPlan('consNote');
         }
      }
      
		/*Modal Dropdown Choosen Call*/						
      $('#editdrop').on('shown.bs.modal', function () {
	  $('.chosen-select', this).chosen();
	});
	
	/*Modal Dropdown Choosen Call*/						
      $('#document_details').on('shown.bs.modal', function () {
	  $('.chosen-select', this).chosen();
	});

/*Modal Dropdown Choosen Call*/						
      $('#editConsultationNote').on('shown.bs.modal', function () {
	  $('.chosen-select', this).chosen();
	});
      
      
      </script>

<script type="text/javascript">
          /*  $(function() {
            
             
                //initialize the pqSelect widget.
                $("#opdCondition").pqSelect({
                    multiplePlaceholder: 'Select Regions',
                    checkbox: true //adds checkbox to options    
                }).on("change", function(evt) {
                    var val = $(this).val();
                    document.getElementById('odconditionstr').value = val;
                    
                   // alert(val);
                }).pqSelect('open');
            });*/
        </script>
<script><!--
		$(function () {
		    $('#scrollemr').slimScroll({
		        height: '288px',
		         railVisible: true,
				alwaysVisible: true
		    });
		     $('#scrollemr1').slimScroll({
		        height: '288px',
		         railVisible: true,
				alwaysVisible: true
		    });
		     
			$('#investipopup').slimScroll({
		      
		  });
		});

</script>




<script>
    $(document).ready(function() {
        $('.filtertab').filterTable(); // apply filterTable to all tables on this page
    });
    </script>

<script type="text/javascript">

var r = document.getElementById('result');

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
    startConverting();
   }
   else{
   //var textvalue=document.getElementById("otnotes").value;
  // localStorage.setItem("xx",textvalue);
   location.reload();
   }
   
   

       
         
     }
     function editemrpop() {
    	 
    	 $('#readmittpopup').modal( "show" );
	}
    
</script>
<%-- <script>
function selectdia(){
total=0;
$('.concase').each(function() {
	 if(this.checked){
    var i=this.value;
    total=total+","+this.value;
	 }
	 document.getElementById("finaldiagnosis")=total;
});
}
</script> --%>


