<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	Bed ipd = (Bed) session.getAttribute("bed");

	if (ipd == null) {

		ipd = new Bed();
	}
%>
 <%
 String kunalacces="";
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
 if(loginInfo.getIskunal()==1){
	 kunalacces="hidden";
 }
String kunalneg="";
if(loginInfo.getIskunal()==0){
	kunalneg="hidden";
}
 
 %>
 <s:hidden name="jsonipdid" id='jsonipdid'></s:hidden>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admission Summary Form</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>
<link href="_assets/newtheme/css/main.css" rel="stylesheet" />
<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
<link href="_assets/css/priscription/hospitalresponsive.css"
	rel="stylesheet" />
<link href="_assets/css/priscription/dischargeform.css" rel="stylesheet" />
<script type="text/javascript" src="ipd/js/admissionform.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>


<script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="ipd/js/dischargeform.js"></script>

<script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>


<script>

<%-- <script>

setInterval(function(){ 
 submitaddmissionFor();
 
  }, 1000*5);
 

</script> --%>
</script>
<style>
.blink_me {
  animation: blinker 1s linear infinite;
}

@keyframes blinker {
  50% {
    opacity: 0;
  }
}
#diag tr>td{
border-bottom: 0px solid gray !important;
}

.setbackcolor {
	background-color: beige;
}

::placeholder {
	color: green;
}

.micimg {
	float: right !important;
	width: 3% !important;
	margin-top: 1px !important;
}

.adformback {
	border: 1px solid;
	padding: 10px 0px 0px;
	margin-top: 0px;
	width: 98%;
	margin-left: 9px;
}

.minheaighsys {
	height: 38px;
}

.form-horizontal .control-label {
	padding-top: 7px;
	margin-bottom: 0px;
	text-align: right;
	font-size: 12px;
}

.marbot15 {
	margin-bottom: 15px;
}

.martop15 {
	margin-top: 15px;
}

.diagtitle {
	background-color: #000;
	color: #FFF;
	padding: 10px;
	font-weight: normal;
	padding-top: 12px !important;
}

.bednosele {
	width: 10%;
	margin-top: -40px;
}

.dischargebtn {
	width: 99px !important;
	float: right !important;
	margin-right: 0px !important;
	margin-bottom: 20px !important;
}

.bodertitright {
	border-top: 2px solid #c0c0c0;
	border-right: 2px solid #c0c0c0;
	border-bottom: 2px solid #c0c0c0;
	padding: 8px 0px 8px 0px;
}

.bodertitleft {
	border-top: 2px solid #c0c0c0;
	border-left: 2px solid #c0c0c0;
	border-bottom: 2px solid #c0c0c0;
	padding: 8px 0px 8px 0px;
}

.textareaheight {
	height: 50px !important;;
}

.paddtop {
	padding: 0px 0px 14px 2px;
}

.widthtabhedth1 {
	width: 22%;
}

.widthtabhedth2 {
	width: 7%;
}

.backgrey {
	background-color: rgba(149, 222, 91, 0.19);
}

.mar0 {
	margin-top: 23px;
	margin-left: 15px;
}

.bordertopgray {
	border-top: 1px solid #c7c7c7;
	border-bottom: 1px solid #c7c7c7;
}

.panel-primary {
	border-color: #339966;
}

.pnameback {
	background-color: #f5f5f5;
	margin-top: -7px;
	padding-top: 10px;
}

.admissionbackgreen {
	background-color: #339966;
	color: #fff;
	width: 223px;
}

.form-inline .form-control {
	display: inline-block;
	vertical-align: middle;
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
	text-align: justify;
	padding: 6px 9px;
	background-color: #555;
	color: #fff;
	position: absolute;
	z-index: 1;
	bottom: 125%;
	left: 50%;
	margin-left: -60px;
	opacity: 0;
	transition: opacity 1s;
}

.tooltip .tooltiptext::after {
	content: "";
	position: absolute;
	top: 100%;
	left: 10%;
	margin-left: 37px;
	border-width: 5px;
	border-style: solid;
	border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
	visibility: visible;
	opacity: 1 !important;
}
/* width */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px grey; 
  border-radius: 10px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
  background: black; 
  border-radius: 5px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #7DA8D4; 
}
</style>
</head>

<script type="text/javascript">
<!--
    
    
    $(document).ready(function() {

	$("#dischargedate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#followupdate1").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});
	
	
	});
	
	        
	        bkLib.onDomLoaded(function() {
		           
	        	
	        	// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('admissiondetails');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('hospitalcourse');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('discadvnotes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('discharge_default');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('operation_notes');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('finddis');
				 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('treatmentgiven');
	        /* 	 
	        	  */
	        });
	        
	        
	   
	        
	        
-->
bkLib.onDomLoaded(function() {
	   if(document.getElementById("tttr")){
    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 50}).panelInstance('tttr'); 
	   }
	   if(document.getElementById("investigation")){   
    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('investigation');
	   } if(document.getElementById("emercontdetail")){
			new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('emercontdetail');
		}
		
		 /*  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('discadvnotes');
		 */// paditric 
		 
		 if(document.getElementById("chiefcomplains1")){ new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('chiefcomplains1'); }
		 if(document.getElementById("developmenthist")){   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('developmenthist');}
		 if(document.getElementById("birthhist")){ new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('birthhist');}
		 if(document.getElementById("emmunizationhist")){ new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('emmunizationhist');}
		 if(document.getElementById("diethist")){new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('diethist');}
		 if(document.getElementById("pasthistorylok")){new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('pasthistorylok');}
		 if(document.getElementById("familyhistlok")){new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('familyhistlok');}
/* 	     if(document.getElementById("surgicalnoteslok")){new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('surgicalnoteslok');} */;
if(document.getElementById("earlierinvestlok")){ new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('earlierinvestlok');}
if(document.getElementById("suggestedtrtmentlok")){   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('suggestedtrtmentlok');}
if(document.getElementById("personalhistlok")){ new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('personalhistlok');}
if(document.getElementById("onexaminationlok")){ new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('onexaminationlok');}
    
	    if(document.getElementById('alergies')){ 
	    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('alergies');
	    }
	    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('death_note');
	   if(document.getElementById('presentillness')){
		   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('presentillness');   
	   } 
	   if(document.getElementById('kunal_manual_medicine_text')){ 
	    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 100}).panelInstance('kunal_manual_medicine_text');
	   }
	   if(document.getElementById('kunalfinaldiagnosis')){ 
	    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 100}).panelInstance('kunalfinaldiagnosis');
	   } 
	   if(document.getElementById('surgicalnotes')){ new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('surgicalnotes');
	   }



	/* new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('treatmenthistory'); */
    /* new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('treatmentgiven'); */
	
     if(document.getElementById('addmissionfor')){
    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('addmissionfor');
     }
     if(document.getElementById('earlyinvs2')){
    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('earlyinvs2');
    
     }
 
   /*  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('treatmentgiven1');
     */
});


window.onload = function() {
	document.getElementById("defaultOpen").click();
    $('.classD').each(function() { 
		if(this.checked==true){
			selected=selected+","+this.value;
		}
		
    }); 
    
   };

   function openBlock(evt, Name) {
		  var i, tabcontent, tablinks;
		  if(Name=='ADMISSION'){
			  getAdmissionDivDataOfDischargeForm();
		  }else  if(Name=='HISTORY'){
			  getHistoryDivDataOfDischargeForm();
		  }
		  
		  else  if(Name=='COURSE'){
			  getHospitalCourseDivDataOfDischargeForm();
		  }
		  else  if(Name=='SURGICAL'){
			  getSurgicalNotewsDivData();
		  }
		  else  if(Name=='TG'){
			  getTearmentGivenDivDataOfDischargeForm();
		  }
		  else  if(Name=='INVESTIGATIONS'){
			  getInvestigationDivDataOfDischargeForm();
		  }

		  else  if(Name=='MY1'){
			  getOTHERDivDataOfDischargeForm();
		  }

		  else  if(Name=='EME'){
			  getEmergencyDetailsDivDataOfDischargeForm();
		  }
		  else  if(Name=='DEATH'){
			  getCauseOfDeathDivDataOfDischargeForm();
		  }
		  tabcontent = document.getElementsByClassName("tabcontent");
		  for (i = 0; i < tabcontent.length; i++) {
		    tabcontent[i].style.display = "none";
		  }
		  tablinks = document.getElementsByClassName("tablinks");
		  for (i = 0; i < tablinks.length; i++) {
		    tablinks[i].className = tablinks[i].className.replace(" active", "");
		  }
		  document.getElementById(Name).style.display = "block";
		  evt.currentTarget.className += " active";
		  funchange(Name);
		}

	document.getElementById("defaultOpen").click();	
		

</script>

<script>

function triggerTempdata(){
setInterval(function(){ 
 submitaddmissionFor1();
 
  }, 1000*5);
}

</script>

<%
	String hstry = "";
	String sysreview = "";
	String obstretic_history = "";
	String menstrual_history = "";
	String substance_history = "";
	String pediatric_histry="";
%>

<s:if test="history==true">
</s:if>
<s:else>
	<%
		hstry = "hidden";
	%>
</s:else>
<s:if test="paediatrichist==true">
</s:if>
<s:else>
<%pediatric_histry="hidden"; %>
</s:else>
<s:if test="issystemicreview==true">

</s:if>
<s:else>
	<%
		sysreview = "hidden";
	%>
</s:else>

<s:if test="obstretic_history==true">

</s:if>
<s:else>
	<%
		obstretic_history = "hidden";
	%>
</s:else>

<s:if test="menstrual_history==true">

</s:if>
<s:else>
	<%
		menstrual_history = "hidden";
	%>
</s:else>

<s:if test="substance_history==true">

</s:if>
<s:else>
	<%
		substance_history = "hidden";
	%>
</s:else>



<%
String jsondiagnosis="0";
if(loginInfo.isJson_diagnosis()){
	jsondiagnosis="1";
}
%>


<body>
	<%-- <s:form action="updatedischargeCommonnew" onsubmit="return isValidForm()"
		theme="simple"> --%>
		<s:hidden name="treatmentepisodeid" id="treatmentepisodeid" />
		<s:hidden name="practitionerid" id="practitionerid" />
		<s:hidden name="id" id="idipd"/>
		<s:hidden name="priscid"/>
		<s:hidden name="invstids"/>
		<s:hidden name="rmonotesids"/>
		<style>
h3, .h3 {
	margin-top: 9px !important;
	margin-bottom: 9px !important;
}

.textprimegreen {
	background-color: #339966;
	color: #fff;
}

.textprime {
	background-color: rgba(102, 153, 204, 0.85) !important;
	color: #fff;
}

.secconbtn {
	width: 100%;
	background-color: #f5f5f5;
	color: #222222 !important;
	text-align: left;
	font-size: 12px;
	height: 24px !important;
}

.sebaclcons {
	background-color: rgb(246, 246, 246) !important;
	text-shadow: none;
	color: #222 !important;
	text-align: left;
	font-size: 12px;
}

.table>thead>tr>th {
	background-color: rgba(221, 221, 221, 0.85);
	color: #222;
}

.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
}

h4, .h4, h5, .h5, h6, .h6 {
	margin-top: 3.5px;
	margin-bottom: 3.5px;
}

.fa-2x {
	font-size: 16px;
	cursor: pointer;
}
* {box-sizing: border-box}
body {font-family: "Lato", sans-serif;}


/* Style the tab */
.tab {
  float: left;
  padding:0px;
  background-color: #f1f1f1;
  height: 423px !important;
   
}

/* Style the buttons inside the tab */
.tab button {
  display: block;
   background-color: inherit; 
  color: black;
  padding: 22px 16px;
  width: 100%;
 /*  border: none; */
  outline: none;
  text-align: left;
  cursor: pointer;
  transition: 0.3s;
  font-size: 13px;
  border-color: #e7e7e7;
  height: auto;
}
/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current "tab button" class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  float: left;
  padding: 0px 12px;
  
  
  border-left: none;
      height: 416px !important;
    overflow-y: scroll;
    overflow-x: hidden;
}


.lkclass{
width: 50%;
}
.lkclass th{
text-align: center !important;
}

.lkclass td{
text-align: center !important;
}
</style>

<!--Imp ids For json   -->
<input type="hidden" name='jsondiagnosis' id='jsondiagnosis' value="<%=jsondiagnosis%>">
<input type="hidden"  name='diagnosisipdid' id='diagnosisipdid' value="0" >
<s:hidden name='admn_summdiv' id='admn_summdiv'/>
<s:hidden name='histrydiv' id='histrydiv'/>
<s:hidden name='surgical_notesdiv' id='surgical_notesdiv'/>
<s:hidden name='hospital_coursediv' id='hospital_coursediv'/>
<s:hidden name='treatmnt_givendiv' id='treatmnt_givendiv'/>
<s:hidden name='invst_div' id='invst_div'/>
<s:hidden name='otherdiv' id='otherdiv'/>
<s:hidden name='emergency_detdiv' id='emergency_detdiv'/>


		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 textprimegreen hidden">
				<div class="col-lg-6 col-xs-6 col-md-6 text-left"
					style="padding-left: 0px;">
					<!--<h3>Hospital Name</h3>
		-->
		
		<s:hidden name='newadmndate' id='newadmndate'></s:hidden>
		
		<s:hidden name="clientid" id="clientid"></s:hidden>
			<s:hidden name="ipdseqno" id="ipdseqno"></s:hidden>
					<h3>
						<s:property value="clinicName"  />
					</h3>
				</div>
				<div class="col-lg-6 col-xs-6 col-md-6 text-right"
					style="padding-right: 0px;">
					<h3>Patient Discharge Form</h3>
				</div>
			</div>
			<input type="hidden" id="invstlistid" />

			<div class="col-lg-12 col-md-12 col-xs-12 hidden"
				style="padding-top: 5px; padding-bottom: 5px;">
				<span style="color: brown;">IMPORTANT: Please completed all
					required field <font color="red">*</font>
				</span>
				
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
			<div class="col-lg-6 col-xs-6 col-md-6 textprime disp">
				<h5>PERSONAL AND ADMINISTRATION DETAILS      
				<span style="color: brown;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				IMPORTANT: Please completed all
					required field <font color="red">*</font>
				</span>
				 </h5> 
				
			</div>
			<div class="col-lg-6 col-xs-12 col-md-6 textprime contentt blink_me" style="text-align: right" >
				   <h4 >Print Discharge Form &nbsp;&nbsp;   <a  onclick="openPopup('printdischargeCommonnew?selectedid=<s:property value='jsonipdid'/>&clientid=')" href="#" style="color: white;"><i class='fa fa-print'  ></i></a></h4>
			</div>	
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12"
				style="padding: 0px; background-color: rgba(245, 245, 245, 0.95);">
				<div class="col-lg-2 col-xs-12 col-md-2"
					style="background-color: rgba(91, 192, 222, 0.16); padding-top: 15px;">
					<div class="form-group">
						<label for="exampleInputEmail1" onclick="">Patient Name</label><br>
						<p>
							<s:property value="client" />
							<br>
							<s:property value="address" />
							<br>
							<s:property value="contact" />
						</p>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="control-label">Primary
							Consultant</label>
						<p>
							<s:property value="doctor_name" />
							<br>
							<s:property value="discipline" />
							<br>
							<%-- <s:property value="doctor_phone"/> --%>
						</p>
						<input type="hidden" name="department"
							value="<s:property value="discipline"/>">
					</div>
				</div>
				<div class="col-lg-10 col-md-10 col-xs-12 col-sm-9"
					style="padding-top: 15px;">
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="exampleInputEmail1">Age/Sex</label>
							<p>
								<s:property value="agegender" />
							</p>
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="exampleInputEmail1">UHID</label>
							<p>
								<s:property value="abrivationid" />
							</p>
							<%-- <p><s:property value="patientIdAbrivation"/></p> --%>
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="exampleInputEmail1">Admission ID</label>
							<%-- <p><s:property value="id"/>/<s:property value="num_admission"/></p> --%>
							<p id="ipdseqno">
								<% if(loginInfo.getIpd_abbr_access()==1){%>
									<s:property value="newipdabbr" />
								<%}else{ %>
									<s:property value="ipdseqno" />
									<%} %>
								<%-- /<s:property value="num_admission"/> --%>
							</p>
						</div>

					</div>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="exampleInputEmail1">Admission Date</label>
							<p id="admissiondate">
								<s:property value="admissiondate" />
							</p>
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="exampleInputEmail1">Payee</label>
							<p>
								<s:property value="whopay" />
							</p>
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="exampleInputEmail1">Referred From</label>
							<%
								if (ipd.getRefferedby() == null) {
										ipd.setRefferedby("");
									}
							%>


							<%
								if (ipd.getRefferedby().equals("")) {
							%>
							<p>N/A</p>
							<%
								} else {
							%>
							<p><%=ipd.getRefferedby()%></p>
							<%
								}
							%>
						</div>
					</div>
					<s:if test="secndryconsult!=''">
						<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
							<div class="form-group">
								<label for="inputEmail" class="control-label">Associate
									Consultants</label>
								<s:iterator value="allConsultantList">
									<p class="help-block">
										<s:property />
									</p>
								</s:iterator>

							</div>
						</div>
					</s:if>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">

						<%if(loginInfo.isDischarge_new()){ %>
						<div class="form-group">
							<label for="inputEmail" class="control-label ">Bed Log</label>
							<s:iterator value="bedLogList">
								<s:if test="status==1">
									<p>
										<s:property value="wardname" />
										/
										<s:property value="bedname" />
									</p>
								</s:if>
								<s:else>
									<p>
										<s:property value="wardname" />
										/
										<s:property value="bedname" />
									</p>
								</s:else>

							</s:iterator>

							<!--<p> <s:property value="wardid" /> / <s:property value="bedid"/></p>
								  -->
						</div>
						<%} %>
					</div>
					
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="inputEmail" class="control-label"
								style="color: crimson;">Discharge Date</label>
							<div class="form-group">
								<s:textfield cssClass="form-control" id="dischargedate"
									name="dischargedate" cssStyle="width: 100%;"
									onchange=" checkDates()" />
							</div>
						</div>

					</div>
<%-- <s:hidden id="admi_disc_ipd_prisclist"></s:hidden> --%>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="exampleInputEmail1" style="color: crimson;">Discharge
								Time</label>
							<div class="form-inline">
								<div class="form-group" style="width: 35%;">
									<s:select name="hour" id="edithour" list="hourList"
										cssClass="form-control wiid" headerKey="0" headerValue="Select HH"
										style="width:100%;" />
								</div>
								<div class="form-group" style="width: 35%;">
									<s:select name="minute" id="editminute" list="minuteList"
										cssClass="form-control wiid" headerKey="0" headerValue="Select MM"
										style="width:100%;" />
								</div>
							</div>
						</div>
					</div>
					
					<% String aureusaccess="";
					if(loginInfo.getClinicUserid().equals("aureus")){
						aureusaccess="hidden";
					} %>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group <%=aureusaccess%>">
							<label for="exampleInputEmail1" style="color: crimson;">Discharge
								Outcome</label>
							<s:select cssClass="form-control" name="dischrgeOutcomes"
								id="eddischrgeOutcomes" list="dischargeOutcomeList" listKey="id"
								listValue="name" headerKey="0" headerValue="Select Outcomes" />
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group">
							<label for="exampleInputEmail1" style="color: crimson;">Discharge
								Status</label>
							<s:select cssClass="form-control" name="dischargeStatus"
								id="eddischargeStatus" onchange="checkdeathstatus(this.value)"
								list="dischargeStatusList" listKey="id" listValue="name"
								headerKey="0" headerValue="Select Status" />
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" id='islamadama' style="margin: 10px;">
                       <!--  <div class="form-group" id="deptDiv">
								   <a href="" class="pull-right hidden-xs" data-toggle="modal" data-target="#miic" title="Voice Recorder" Title="Voice Recorder" style="color:#fff;"><i class="btn btn-sm btn-primary" aria-hidden="true">Voice Recorder</i></a>
								  </div> -->
					<div class='' >			  
					<label><b id='islamadamatext'>Reason For LAMA/DAMA:</b></label>
						<s:textarea cssClass="form-control" rows="3" maxlength="" name="lamadamareason" id="tttr"></s:textarea>
                    </div>
                     </div>  
				</div>



			</div>

<div class="col-lg-2 col-md-2 col-sm-2 tab disp">
   <button class="tablinks" onclick="openBlock(event, 'DIAGNOSIS')" id="defaultOpen">DIAGNOSIS</button>
   <button class="tablinks" id="btn2" onclick="openBlock(event, 'ADMISSION')">ADMISSION SUMMARY</button>
   <button class="tablinks" id="btn3" onclick="openBlock(event, 'HISTORY')">HISTORY</button>
   
   <s:if test="dischargeStatus==3">
    <button class="hidden" id="btn4" onclick="openBlock(event, 'SURGICAL')">SURGICAL NOTES</button>
    <button class="tablinks" id="btn5" onclick="openBlock(event, 'DEATH')">CAUSE OF DEATH</button>
    
   </s:if>
   <s:if test="dischargeStatus!=3">
    <button class="tablinks" id="btn4" onclick="openBlock(event, 'SURGICAL')">SURGICAL NOTES</button>
    <button class="hidden" id="btn5" onclick="openBlock(event, 'DEATH')">CAUSE OF DEATH</button>
   </s:if>
   <button class="tablinks" id="btn6" onclick="openBlock(event, 'COURSE')">HOSPITAL COURSE</button>
   <button class="tablinks" id="btn7" onclick="openBlock(event, 'TG')">TREATMENT GIVEN</button>
   <button class="tablinks" id="btn8" onclick="openBlock(event, 'INVESTIGATIONS')">INVESTIGATIONS</button>
   <s:if test="dischargeStatus==3">
   <button class="hidden" id="btn9" onclick="openBlock(event, 'MY1')">OTHER</button>
   </s:if>
   <s:if test="dischargeStatus!=3">
   <button class="tablinks" id="btn9" onclick="openBlock(event, 'MY1')">OTHER</button>
   </s:if>
   <button class="tablinks" id="btn10" onclick="openBlock(event, 'EME')">EMERGENCY DETAILS</button>
</div>
<select class='col-xs-12 col-sm-12 disp1' style="padding: 11px 10px;margin-top: 10px;margin-bottom: 10px;" id='dropdown1'onchange="openBlock(event, this.value)">
<option id="defaultOpen" value='DIAGNOSIS'>DIAGNOSIS</option>
<option value='ADMISSION'>ADMISSION SUMMARY</option>
<option value='HISTORY'>HISTORY</option>
<s:if test="dischargeStatus==3">
    <option class="hidden" value='SURGICAL'>SURGICAL NOTES</option>
    <option value='DEATH'>CAUSE OF DEATH</option>   
 </s:if>
   <s:if test="dischargeStatus!=3">
    <option value='SURGICAL'>SURGICAL NOTES</option>
    <option class="hidden" value='DEATH'>CAUSE OF DEATH</option>
   </s:if>
   <option value='COURSE'>HOSPITAL COURSE</option>
   <option value='TG'>TREATMENT GIVEN</option>
   <option value='INVESTIGATIONS'>INVESTIGATIONS</option>
   <s:if test="dischargeStatus==3">
   <option class="hidden" value='MY1'>OTHER</option>
   </s:if>
   <s:if test="dischargeStatus!=3">
   <option value='MY1'>OTHER</option>
   </s:if>
   <option value='EME'>EMERGENCY DETAILS</option>

</select>
<div id="DIAGNOSIS" class="col-lg-10 col-md-10 col-sm-10 col-xs-12 tabcontent">
			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
				<h5>
					DIAGNOSIS&nbsp;<span class="tooltip"><i
						class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The
							act or process of identifying or determining the nature and cause
							of a disease or injury through evaluation of patient history,
							examination, and review of laboratory data and the opinion
							derived from such an evaluation</span></span>
				</h5>
			</div>

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="padding-bottom: 15px;">
					<table class="table">
						<thead>
							<tr>
								<th><b>Final Diagnosis<b> <input type="button"
											value="Add New" class="btn btn-info hidden"
											onclick="dispDIv()" /></th>
								<th></th>
								<th><b>Provisional Diagnosis<b></th>
							</tr>
						</thead>
						<tbody>

							<tr id="dispid" class="hidden">
								<td><input type="text" class="form-control"
									placeholder="Enter New Diagnosis" id="newcondition"
									style="border: 1px solid #ddd; margin-top: 8px; width: 39%;">
									<input type="text" class="form-control"
									placeholder="Enter ICD Code" id="icdcode"
									style="border: 1px solid #ddd; margin-top: 8px; width: 40%;">
									<input type="button" onclick="addnewCOndition(0)"
									class="btn btn-sm btn-warning" style="margin-top: 0px;"
									value="Save as New" /></td>
							</tr>
							<tr>
								<td><textarea 
									onkeyup="searchdiagnosisJSON(this.value)"
									class="form-control setbackcolor" id="newdiagnosis"
									placeholder="type diagnosis here" style="height: 50px !important"></textarea>
								<form action="predischargeIpd" id="predischarge"></form>	
									</td>
								<td><input type="button" value="Save as new"
									onclick="savediagnosisfastJSON()" class="btn btn-info" /></td>
								<td>
									<table>

										<s:iterator value="ipdconditionlist">

											<tr>
												<td><s:property value="conditionname" /></td>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
							<tr>

								<td width="50%;">
									<%
										ArrayList<Bed> ipdConditionids = (ArrayList<Bed>) session.getAttribute("finalConditions");
									%>
									<table id="searchDiagnosis" class="table"
										style="height: 200px; display: block; overflow: scroll; width: 100%"; >
										<%
											int i = 0;
										%>
										<%
											for (Bed bed : ipdConditionids) {
										%>
										<tr>
											<td><input type="checkbox" class="classD"
												onclick="reoveThisRow(this,'<%=bed.getConditionid()%>')"
												value="<%=bed.getConditionid()%>" checked='checked' /> <input
												type='hidden' value="<%=bed.getConditionid()%>"
												name='conditions[<%=i%>].conditionid' /></td>
											<td><%=bed.getConditionname()%></td>
											<td class="hidden"><a
												onclick="reoveThisRow('<%=bed.getConditionid()%>')"><i
													class='fa fa-trash-o'></i></a></td>
										</tr>
										<!--  
                                         <tr>
                                            <td><input type="checkbox" class="" /></td>
                                             <td>CSS</td>
                                         </tr> -->
										<%
											i++;
										%>
										<%
											}
										%>
									</table>

								</td>
								
							</tr>
						</tbody>

					</table>
					<div class=<%=kunalneg %>>
					<div class="form-group" style="margin-top:30px;" >
					<div class="form-inline">
									<div class="form-group">
								          <label for="exampleInputName2"><b>Final Diagnosis Text</b></label>
								               
								    </div>
								    <div class="form-group">
								    <s:select list="discharge_default_list"
										onchange="getkunalFdiagnosistemp(this.value)" listKey="id"
										listValue="name" cssClass="form-control" headerKey="0"
										headerValue="Select Template"></s:select>
								    </div>
					</div>			    
								    
										<s:textarea cssClass="form-control" rows="9" maxlength="" name="kunal_final_diagnosis_text" id="kunalfinaldiagnosis"></s:textarea>
					</div>
					
					</div>				
				</div>
			</div>
			<!-- <div class="text-right">
				<input type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save & Next" onclick="saveDiagnosisDivDataOfDischargeForm()">
			</div> -->

</div>
<div id="ADMISSION" class="col-lg-10 col-md-10 col-sm-10 col-xs-12 tabcontent" >

			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
				<s:if test="dischargeStatus==3">
					<h5>DEATH SUMMARY</h5>
				</s:if>
				<s:else>
					<h5>ADMISSION SUMMARY</h5>
				</s:else>

			</div>

                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
                        <%-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        	<div class="form-group hidden">
                        			<label for="exampleInputName2">Reason For Admission</label>
								   <s:textfield name="admission_reason" id="admission_reason" cssClass="form-control" />
							</div>
                        </div> --%>
                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        		<div class="form-inline">
								  <div class="form-group">
								    <label for="exampleInputName2">CHIEF/PRESENT COMPLAINTS AND REASON FOR ADMISSION</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A subjective statement made by a patient describing the most significant or serious symptoms or signs of illness or dysfunction that caused him or her to seek health care></span></span>
								   <span id='cfl'> <s:select cssClass="form-control chosen-select" list="chief_complaints_list"  listKey="id"  listValue="name" onchange="setChiefComplaints(this.value)" headerKey="0" headerValue="Select Template">
								    </s:select></span>
								  </div>
								  <div class="form-group">
								    <select class="form-control" onchange="setchiefcomp(this.value)">
									   <option>Select</option>
									  <option>1</option>
									  <option>2</option>
									  <option>3</option>
									  <option>4</option>
									  <option>5</option>
									  <option>6</option>
									  <option>7</option>
									  <option>8</option>
									  <option>9</option>
									  <option>10</option>
									  <option>11</option>
									  <option>12</option>
									  <option>13</option>
									  <option>14</option>
									  <option>15</option>
									  <option>16</option>
									  <option>17</option>
									  <option>18</option>
									  <option>19</option>
									  <option>20</option>
									  <option>21</option>
									  <option>22</option>
									  <option>23</option>
									  <option>24</option>
									  <option>25</option>
									  <option>26</option>
									  <option>27</option>
									  <option>28</option>
									  <option>29</option>
									  <option>30</option>
									</select>
								  </div>
								   <div class="form-group">
								    <select class="form-control" onchange="setchiefcomp(this.value)">
								        <option>Days</option>    
								        <option>Month</option>    
								        <option>Year</option>     
								    </select>
								  </div>
								  <div class="form-group">
								  	<input type="text" name="chiefcomplatetempname" id='chiefcomplatetempname' class="form-control setbackcolor " placeholder="Enter template Name">  
								  	<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('1',this)" >	
								  </div>
								  <div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'chiefcomplains1')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
								  <!--<div class="form-group">
								    <select class="form-control">
									  <option>Select Day</option>
									  <option>Day 1</option>
									  <option>Day 2</option>
									  <option>Day 3</option>
									  <option>Day 4</option>
									</select>
								  </div>
								  --><!--<button type="submit" class="btn btn-primary">+</button>
								  
								--></div>
								 <div class="form-group" style="margin-top: 10px;">
									<s:textarea cssClass="form-control" rows="6"  name="chiefcomplains" id="chiefcomplains1"/>
								</div> 
                        	</div>
                   </div>	

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">



				<%
					if (ipd.getAlergies() != null) {
				%>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
					<div class="form-group">
						<label for="inputEmail">H/O ALLERGIES</label>
						<%-- <p class="help-block"><%=ipd.getAlergies() %></p> --%>
						<s:textarea cssClass="form-control" rows="3" id="alergies"
							name="alergies" />
					</div>
				</div>
				<%
					}
				%>






				<%
					if (false) {
				%>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<%-- <div class="form-group">
								<label for="exampleInputName2">Chief Complaints</label>
									<p class="help-block text-justify"><%=ipd.getChiefcomplains() %> </p>
									<s:textarea cssClass="form-control" rows="6"  name="chiefcomplains" id="chiefcomplains"/>
								</div> --%>
					<div class="form-inline">
						<label for="exampleInputName2">Chief Complaints</label>
						<s:select cssClass="form-control" list="chief_complaints_list"
							listKey="id" listValue="name"
							onchange="setChiefComplaints(this.value)" headerKey="0"
							headerValue="Select Template">
						</s:select>
						<select class="form-control" onchange="setchiefcomp(this.value)">
							<option>Select</option>
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
							<option>13</option>
							<option>14</option>
							<option>15</option>
							<option>16</option>
							<option>17</option>
							<option>18</option>
							<option>19</option>
							<option>20</option>
							<option>21</option>
							<option>22</option>
							<option>23</option>
							<option>24</option>
							<option>25</option>
							<option>26</option>
							<option>27</option>
							<option>28</option>
							<option>29</option>
							<option>30</option>
						</select> <select class="form-control" onchange="setchiefcomp(this.value)">
							<option>Days</option>
							<option>Month</option>
							<option>Year</option>
						</select> <input type="text" name="chiefcomplatetempname"
							class="form-control setbackcolor"
							placeholder="Enter template Name">
						<!--<div class="form-group">
								    <select class="form-control">
									  <option>Select Day</option>
									  <option>Day 1</option>
									  <option>Day 2</option>
									  <option>Day 3</option>
									  <option>Day 4</option>
									</select>
								  </div>
								  -->
						<!--<button type="submit" class="btn btn-primary">+</button>
								  
								-->
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<s:textarea cssClass="form-control" rows="6" name="chiefcomplains"
							id="chiefcomplains1" />
					</div>
				</div>

				<%
					}
				%>






				<%
					if (ipd.getPresentillness() != null) {
				%>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<%-- <div class="form-group">
								<label for="exampleInputName2">Present Illness</label>
									<p class="help-block text-justify"><%=ipd.getPresentillness() %> </p>
									<s:textarea cssClass="form-control" rows="6" maxlength="" name="presentillness" id="presentillness"/>
								</div> --%>
					<div class="form-inline">
						<label for="exampleInputName2">Present Illness</label>
						<span id='pllist'>
						<s:select list="present_illness_list" cssClass="form-control"
							headerKey="0" onchange="getpresentIllness(this.value)"
							headerValue="Select Template" listKey="id" listValue="name" id='present_illness_list'>
						</s:select>
						</span>
						<input type="text" name="presentillnesstempname" id='presentillnesstempname'
							class="form-control setbackcolor "
							placeholder="Enter template Name">
						<input type="button" class='btn btn-primary' value="Save Template" onclick="saveIpdTemplates('2',this)">
								
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<s:textarea cssClass="form-control" rows="6" maxlength=""
							name="presentillness" id="presentillness" />
					</div>
				</div>
				<%
					}
				%>





			</div>

			<div
				class="col-lg-12 col-xs-12 col-md-12 textprime <%=substance_history%>">
				<h5>SUBSTANCE HISTORY</h5>
			</div>

			<div
				class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=substance_history%>">
				<div class="row <%=substance_history%>">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Alcohal</label><br>
									<s:property value="alcohal" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Drugs</label><br>
									<s:property value="drugs" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Other Medications</label><br>
									<s:property value="other_medication" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Smoking</label><br>
									<s:property value="smoking" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Tobaco</label><br>

									<s:property value="tobaconotes" />

								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Tobaco</label><br>
									<s:property value="tobaco" />
								</div>
							</div>
						</div>

					</div>
				</div>

			</div>

			<div
				class="col-lg-12 col-xs-12 col-md-12 textprime <%=menstrual_history%>">
				<h5>MENTRUAL HISTORY</h5>
			</div>

			<div
				class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=menstrual_history%>">
				<div class="row <%=menstrual_history%>">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="margin-bottom: 15px;">
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Age at menarche</label><br>
									<s:property value="age_menarche" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">L.M.P</label><br>
									<s:property value="lmp" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">L.L.M.P</label><br>
									<s:property value="llmp" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">PMC</label><br>
									<s:property value="pmc" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Cycle Length</label><br>
									<s:property value="cycle_length" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Duration of Flow</label><br>
									<s:property value="duration_flow" />
								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="margin-bottom: 15px;">
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Amount of Flow</label><br>
									<s:property value="amount_flow" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Dysmenorrhea</label><br>
									<s:property value="dysmenorrhea" />

								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Dyspareunia</label><br>
									<s:property value="dyspareunia" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">H/O Passing Clots</label><br>
									<s:property value="hopassing_clots" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">White Discharge &
										Itching</label><br>
									<s:property value="white_disc_itching" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Intercourse Frequency</label><br>
									<s:property value="intercourse_freq" />
								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="margin-bottom: 15px;">
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Galactorrea</label><br>
									<s:property value="galactorrea" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">H/O Contraception</label><br>
									<s:property value="ho_contraception" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Rubella Vaccine</label><br>
									<s:property value="rubella_vaccine" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
							<div class="">
								<div class="form-group">
									<label for="exampleInputName2">Menopause</label><br>
									<s:property value="menopause" />
								</div>
							</div>
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2"></div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2"></div>
					</div>
				</div>




			</div>


			<div
				class="col-lg-12 col-xs-12 col-md-12 textprime <%=obstretic_history%>">
				<h5>OBSTRETIC HISTORY</h5>
			</div>
			<div
				class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=obstretic_history%>">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="margin-bottom: 15px; border-bottom: 1px dashed #ddd; padding: 0px 0px 10px 0px;">
					<div class="col-lg-2 col-md-2 col-xs-2">

						<label class=""><i></i> Nulligravida</label> :
						<s:property value="nulligravida" />
						<br> <label class=""><i></i> Currently Pregnent</label> :
						<s:property value="current_pregnent" />
						<br> <label class=""><i></i> IUD</label> :
						<s:property value="iud" />
						<br>

					</div>
					<div class="col-lg-2 col-md-2 col-xs-2">
						<div class="form-group">
							<lable>Live Boys</lable>
							<br>
							<s:property value="live_boys" />
						</div>

					</div>
					<div class="col-lg-2 col-md-2 col-xs-2">

						<div class="form-group">
							<lable>Live Girls</lable>
							<br>
							<s:property value="live_girls" />
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-2">

						<div class="form-group">
							<lable>Stillbirths</lable>
							<br>
							<s:property value="stillbirths" />
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-2">

						<div class="form-group">
							<lable>Abortions</lable>
							<br>
							<s:property value="abortions" />
						</div>
					</div>

					<div class="col-lg-2 col-md-2 col-xs-2">
						<div class="form-group">
							<lable>Death Children</lable>
							<br>
							<s:property value="death_children" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="margin-bottom: 15px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<h5 style="color: brown;">Sequence of Parity/Abortions</h5>
							<table>

								<%
									i = 0;
								%>
								<tbody>
									<s:iterator value="gynicobsList">
										<tr>
											<td style="width: 5%; padding-right: 15px;"><%=i + 1%></td>
											<td style="width: 8%; padding-right: 15px;"><s:property
													value="year" /></td>
											<td style="width: 7%; padding-right: 15px;"><s:property
													value="type" /></td>
											<td style="width: 13%; padding-right: 15px;"><s:property
													value="type_delivery" /></td>

											<td style="width: 20%; padding-right: 15px;"><s:property
													value="indications" /></td>
											<td style="width: 20%; padding-right: 15px;"><s:property
													value="coamplications" /></td>
											<td style="width: 20%; padding-right: 15px;"><s:property
													value="institution" /></td>

										</tr>
										<%
											i++;
										%>
									</s:iterator>
								</tbody>
							</table>
						</div>

					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="margin-bottom: 15px;">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="form-group">
								<label>Parity/Abortions Notes</label> <br>
								<s:property value="parity_abortion_notes" />
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="col-lg-3 col-md-3 col-xs-3">
								<div class="form-group">
									<label>P</label><br>
									<s:property value="p" />
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-3">
								<div class="form-group">
									<label>L</label><br>
									<s:property value="l" />
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-3">
								<div class="form-group">
									<label>A</label><br>
									<s:property value="a" />
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-3">
								<div class="form-group">
									<label>D</label><br>
									<s:property value="d" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


	


			

<div class="form-group mar0">
             <input type="button" class="btn btn-info hidden" onclick="getIPDTempData1()" value="Get Data"  align="right" style="width:10%;"/>
          </div>
  <!-- by lokesh -->
		                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=pediatric_histry %> %>" id="newtablespediatric">
		                         	 <div class="">
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Head Circumference</label>
		                         	  <s:textfield name="headcircumference"  placeholder="" id="headcircumference"></s:textfield>
										
		                         	 </div>
		                        	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"> mid arm Circumference</label>
		                         	     <s:textfield name="midarmcircumference"  placeholder="" id='midarmcircumference' ></s:textfield>
		                         	 </div>
		                         	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Length</label>
		                         	     <s:textfield name="length"  placeholder="" id="leng"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Weight on Addmisssion</label>
		                         	     <s:textfield name="wtaddmission"  placeholder="" id='wtaddmission' ></s:textfield>
		                         	 </div>
		                         	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label">Weight on discharge</label>
		                         	     <s:textfield name="wtdischarge"  placeholder="" id='wtdischarge'></s:textfield>
		                         	 </div>
		                         	</div>
		                         	</div>
		                         	</div>
		                         <!-- 	<div class="text-right">
				<input type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save & Next" onclick="saveAdmissionDivDataOfDischargeForm()">
			</div> -->
 </div>
<div id="HISTORY" class="col-lg-10 col-md-10 col-sm-10 tabcontent">
<div>
			<div class="col-lg-12 col-xs-12 col-md-12 textprime <%=hstry%>">
				<h5>HISTORY</h5>
			</div>

		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=hstry%>">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		
			<div class="form-group" style="margin-top:10px;" >
			  <div class="form-group">
               <label for="exampleInputName2">PAST HISTORY</label>
               
             </div>
				<s:textarea cssClass="form-control" rows="3" maxlength="" name="pasthistory" id="pasthistorylok"></s:textarea>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="form-group" style="margin-top:10px;" >
		<div class="form-group">
               <label for="exampleInputName2">FAMILY HISTORY</label>
         </div>
		<div class="form-group" style="margin-top:10px;">
		
				<s:textarea cssClass="form-control" rows="3" maxlength="" name="familyhist" id="familyhistlok"></s:textarea>
		</div>
		</div>
		</div>
		</div>
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="form-group">
               <label for="exampleInputName2">PERSONAL HISTORY</label>
         </div>
			<div class="form-group" style="margin-top:10px;">
			<s:textarea cssClass="form-control" rows="3" maxlength="" name="personalhist" id="personalhistlok"></s:textarea>
			</div>
		</div>
		<%-- <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
		<div class="form-group">
               <label for="exampleInputName2">SUGICAL NOTES</label>
         </div>
		<div class="form-group" style="margin-top:10px;">
			<s:textarea cssClass="form-control" rows="3" maxlength="" name="surgicalnotes" id="surgicalnoteslok"></s:textarea>				</div>
		</div> --%>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="form-group">
               <label for="exampleInputName2">EARLIER INVESTIGATION</label>
         </div>
	<div class="form-group" style="margin-top:10px;">
		<s:textarea cssClass="form-control" rows="3" name="earlierinvest" id="earlierinvestlok"/>
	</div>
		</div>
		
		</div>
		
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="form-group">
               <label for="exampleInputName2">TREATMENT HISTORY</label>
         </div>
			<div class="form-group" style="margin-top:10px;">
			<s:textarea cssClass="form-control" rows="3" name="suggestedtrtment" id="suggestedtrtmentlok"/>
			</div>
		</div>
		</div>
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
		
	
		</div>
		</div>
		
		</div>
			<div
				class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=hstry%> hidden" onclick="triggerTempdata()">
				<div class="row <%=hstry%>">
					<%
						if (ipd.getPasthistory() != null) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-inline">
								<div class="form-group">
									<label for="exampleInputName2">Past History</label>
									<p class="help-block text-justify">
										<%=ipd.getPasthistory()%>
									</p>
								</div>
							</div>

						</div>
						<%
							}
						%>
						<%
							if (ipd.getFamilyhist() != null) {
						%>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-inline">
								<div class="form-group">
									<label for="exampleInputName2">Family History</label>
									<p class="help-block text-justify"><%=ipd.getFamilyhist()%></p>
								</div>

							</div>

						</div>
						<%
							}
						%>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<%
							if (ipd.getPersonalhist() != null) {
						%>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<div class="form-inline">
								<div class="form-group">
									<label for="exampleInputName2">Personal History</label>
									<p class="help-block text-justify"><%=ipd.getPersonalhist()%>
									</p>
								</div>

							</div>

						</div>
						<%
							}
						%>
						<%
							if (ipd.getOnexamination() != null) {
						%>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
							<div class="form-inline">
								<div class="form-group">
									<label for="exampleInputName2">On Examination</label>
									<p class="help-block text-justify"><%=ipd.getOnexamination()%>
									</p>
								</div>
							</div>

						</div>


						<%
							}
						%>



						<%
							if (ipd.getSuggestedtrtment() != null) {
						%>
						<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad hidden">
							<div class="form-group">
								<label for="exampleInputName2">Treatment Given</label>
								<div class="form-inline">
								
									<s:select list="treatment_given_list"
										onchange="gettreattemplate(this.value)" listKey="id"
										listValue="name" cssClass="form-control" headerKey="0"
										headerValue="Select Template"></s:select>
									<input type="text" name="treatmentgiventempname"
										class="form-control setbackcolor"
										placeholder="Enter template name">
										<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'suggestedtrtment')" title="Microphone" id="changer" ></img>
								</div>
								<s:textarea cssClass="form-control" rows="3" cols="5"
									id="treatmentgiven1" name="suggestedtrtment" />
							</div>
						</div>
						<%
							}
						%>

						<%
							if (ipd.getEarlierinvest() != null) {
						%>
						<%-- <div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad ">
							<div class="form-group">
								<label for="exampleInputName2">Earlier Investigation</label>
								<s:textarea cssClass="form-control" rows="3" cols="5"
									id="earlyinvs" name="earlierinvest" />
							</div>
						</div> --%>
						<%
							}
						%>
					
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
						<div class="col-lg-11 col-md-11 col-xs-11 col-sm-11 " style="margin-left: 30px;">
							<div class="form-group">
								<label for="exampleInputName2">Surgical Notes</label>
								<s:textarea cssClass="form-control" rows="3" cols="5"
									name="surgicalnotes"  id="surgicalnotes" />
							</div>
						</div>	
						</div>
				
					</div>
				</div>
				<s:hidden name="clientip" id="clientipdid1" />


			</div>

  <div class="col-lg-12 col-xs-12 col-md-12 textprime <%=pediatric_histry %>">
							<h5>PEDITRIC HISTORY</h5> 
						</div>
                      
                      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
                          <div class="row <%=hstry %>">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                             <div class="form-inline">
             <div class="form-group">
               <label for="exampleInputName2">BIRTH HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A narrative or record of past events and circumstances that are or may be relevant to a patient's current state of health. Informally, an account of past diseases, injuries, treatments, and other strictly medical facts"></span></span>
               
             </div>
          
           </div>
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="birthhist" id="birthhist"></s:textarea>
           </div>
                            </div>
                          <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                              <div class="form-inline">
             <div class="form-group">
               <label for="exampleInputName2">DIET HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The family structure and relationships within the family, including information about diseases in family members."></span></span>
              
             </div>
        
           </div>
           
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="diethist" id="diethist"></s:textarea>
           </div>
                            </div>
                           </div>
                          </div>
                          
                          <div class="row <%=hstry %>">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                             <div class="form-inline">
             <div class="form-group">
               <label for="exampleInputName2">DEVELOPMENT HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A personal history may include information about allergies, illnesses, surgeries, immunizations, and results of physical exams, tests, and screenings. It may also include information about medicines taken and health habits, such as diet and exercise. Also called personal health record, personal medical history, and PHR."></span></span>
              </div>
        
         
           </div>
           
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="developmenthist" id="developmenthist"></s:textarea>
           </div>
                            </div>
                            
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                              <div class="form-inline">
               <div class="form-group">
                 <label for="exampleInputName2">IMMUNIZATION HISTORY</label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">"A history of the surgical procedures—and complications, if any— that a particular person has had"></span></span>
               </div>
           </div>
           
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="emmunizationhist" id="emmunizationhist"></s:textarea>
           </div>
                            </div>
                           </div>
                          </div>
                          
                         
                       
                       </div>
                       
                       
                       
                       				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group">
						<br>
               <label for="exampleInputName2"><b>NOTES</b></label>
         </div>
							<div class="form-inline">
								<div class="form-group" style="width: 100%;">
								<span id='notesl'><s:select list="discharge_default_list"
										onchange="getdisctemplate(this.value)" listKey="id"
										listValue="name" cssClass="form-control" headerKey="0"
										headerValue="Select Template"></s:select></span>
									<input type="text" name="disdefaulttempname" id='disdefaulttempname'
										class="form-control setbackcolor "
										placeholder="Enter template name">
										
									<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('8',this)" >		
										 <!-- <img
										src="cicon/mic_off.png" class="img-responsive micimg"
										onclick="startConverting3(this)" title="Microphone"
										id="changer"></img> -->
										 <div class="form-group">
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'discharge_default')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </div>
								</div>
							</div>
							<div class="form-group" style="margin-top: 10px;">
								<s:textarea cssClass="form-control" rows="6" maxlength=""
									name="example" id="discharge_default" />
							</div>
						</div>

					</div>
				</div>
                       
                       
                       		<div class="col-lg-12 col-xs-12 col-md-12 textprime <%=sysreview%>">
				<h5>
					<a data-toggle="collapse" href="#collapseExample"
						aria-expanded="false" aria-controls="collapseExample"
						style="color: white;">SYSTEMIC REVIEW &nbsp;&nbsp;<i
						class="fa fa-chevron-down"></i></a>
				</h5>
			</div>
                  
                       
                     <div
				class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=sysreview%>">
				<div class="collapse" id="collapseExample">
					<div class="row <%=sysreview%>">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<p class="help-block">This all systemic review is "NO"</p>
								<p>
									<%
									if(ipd.getFpcondition()==null){
										ipd.setFpcondition("");
									}
										if (ipd.getFpcondition().equals("No")) {
									%>
									<label for="exampleInputName2">Fever at present</label>,
									<%
										}
									%>
									<s:if test="nauseacondition=='No'">
										<label for="exampleInputName2">Nausea</label>,
	                        			</s:if>
									<s:if test="fnucondition=='No'">
										<label for="exampleInputName2">Frequent Nocturnal
											Urination</label>,
	                        			</s:if>
									<s:if test="smcondition=='No'">
										<label for="exampleInputName2">Straining at
											micturation</label>,
	                        			</s:if>
									<s:if test="chestpaincond=='No'">
										<label for="exampleInputName2">Chest pain</label>,
	                        			</s:if>
									<s:if test="dimvisioncond=='No'">
										<label for="exampleInputName2">Dimness of vision</label>,
	                        			</s:if>
									<s:if test="hgucondition=='No'">
										<label for="exampleInputName2">Headache, Giddiness,
											Unconsciousness</label>,
	                        			</s:if>
									<s:if test="hmcondition=='No'">
										<label for="exampleInputName2">Haemoptysis, Malena</label>,
	                        			</s:if>
									<s:if test="jointpaincond=='No'">
										<label for="exampleInputName2">Joint pain</label>,
	                        			</s:if>
									<s:if test="edemafeetcondi=='No'">
										<label for="exampleInputName2">Edema feet</label>,
	                        			</s:if>
									<s:if test="hematuriacondi=='No'">
										<label for="exampleInputName2">Hematuria</label>,
	                        			</s:if>
									<s:if test="bmcondition=='No'">
										<label for="exampleInputName2">Burning micturation</label>,
	                        			</s:if>
									<s:if test="oliguriacondi=='No'">
										<label for="exampleInputName2">Oliguria</label>,
	                        			</s:if>
									<s:if test="pstgucondi=='No'">
										<label for="exampleInputName2">Passing stone or gravel
											in the urine</label>,
	                        			</s:if>
									<s:if test="ihcondition=='No'">
										<label for="exampleInputName2">Impaired hearing</label>,
	                        			</s:if>
									<s:if test="bmecondition=='No'">
										<label for="exampleInputName2">Breathlessness on mild
											exertion</label>,
	                        			</s:if>
									<s:if test="tnecondition=='No'">
										<label for="exampleInputName2">Tingling, Numbness in
											extremities</label>,
	                        			</s:if>
									<s:if test="weaknesscondi=='No'">
										<label for="exampleInputName2">Weakness</label>,
	                        			</s:if>
									<s:if test="constipationcond=='No'">
										<label for="exampleInputName2">Constipation</label>,
	                        			</s:if>
								</p>
								<p style="display: inline-block;">
									<label for="exampleInputName2">Special Notes/Remarks :
									</label><span class="help-block"><%=ipd.getSpecialnotes()%> </span>
								</p>

							</div>
						</div>
					</div>

				</div>
			</div>

   <div style="margin-top: 20px !important;margin-bottom: 20px !important;">
    <div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							On Examination &nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">
									</span></span>
						</h5>
					</div>                         
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 20px !important;margin-bottom: 20px !important;">
		<div class="form-group">
               <label for="exampleInputName2">ON EXAMINATION</label>
               <label>
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'onexaminationlok')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </label>
         </div>
         
			<div class="form-group" style="margin-top:10px;">
					<s:textarea cssClass="form-control" rows="6" maxlength="" name="onexamination" id="onexaminationlok"></s:textarea>
			</div>
			
		</div>
	</div>
	<!-- <div class="text-right">
				<input type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save & Next" onclick="saveHistoryDivDataOfDischargeForm()">
			</div> -->
	</div>
</div>	
		<div id="SURGICAL" class="col-lg-10 col-md-10 col-sm-10 tabcontent">
			<s:if test="dischargeStatus==3">
				<div class="hidden" id="surgicalnotesdiv">

					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							SURGICAL NOTES &nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">An
									Operative report is a report written in a patient's medical
									record to document the details of a surgery. The operative
									report is dictated right after a surgical procedure and later
									transcribed into the patient's record.</span></span>
						</h5>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
						<%--  <s:if test="otdatesandids.size!=0">
												<div class="form-group" style="margin-top:10px;">
													<label for="inputEmail" class="control-label ">Operative Date</label>
													<s:iterator value="otdatesandids">
		                        			 			<p><s:property value="date"/>&nbsp;&nbsp;&nbsp;<b>Procedure:</b><s:property value="procedurename"/></p> 
		                        			 		</s:iterator>
												</div>
											</s:if>  --%>
						<%
							ArrayList<Master> otdatesandids = (ArrayList<Master>) session.getAttribute("otdatesandids");
						String x="";
						%>
						<%
							int otid = 0;
						%>
						<s:hidden name="totalotids"></s:hidden>
						<div class="form-group" style="margin-top: 10px;">
							<label for="inputEmail" class="control-label ">Operative
								Date</label>
							<%
							if(otdatesandids!=null){	
							for (Master master : otdatesandids) {
								if(master!=null){
							%>
							
							<p><%=master.getDate()%>&nbsp;&nbsp;&nbsp;<b>Procedure:</b><input
									type="text" name="editotprocedure<%=otid%>"
									class="form-control" value="<%=master.getProcedurename()%>">
							</p>
							<%
								otid++;
							%>
							<%
								}}}
							%>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
							<div class="row">

								<%-- <s:if test="otdatesandids.size!=0">
												<div class="form-group" style="margin-top:10px;">
													<label for="inputEmail" class="control-label ">Operative Date</label>
													<s:iterator value="otdatesandids">
		                        			 			<p><s:property value="date"/>(<s:property value="id"/>)&nbsp;&nbsp;&nbsp;<b>Procedure:</b><s:property value="procedurename"/></p> 
		                        			 		</s:iterator>
												</div>
											</s:if> --%>
								<div class="col-lg-12 col-md-12 col-xs-12">
									<div class="col-lg-6 col-md-6 col-xs-6">
										<div class="form-group">
											<label for="inputEmail" class="control-label ">Operative
												Procedure</label>
											<div class="form-group">
												<!--<s:textfield name="appointmentText"  cssClass="form-control" />-->
												<s:select name="appointmentText" id="appointmentText"
													list="procedureList" listKey="name" listValue="name"
													cssClass="form-control showToolTip chosen" headerKey="0"
													headerValue="Select Procedure" />
												<!-- <input type="text" name="appointmentText" placeholder="Or Enter Procedure " class="form-control" /> -->
												<s:textfield name="appointmentText"
													placeholder="Or Enter Procedure " cssClass="form-control" />
											</div>

										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-6">
										<div class="form-group">
											<label for="inputEmail" class="control-label ">Operating
												Surgeon</label>
											<div class="form-group">
												<!--<s:textfield name="surgeon"  cssClass="form-control" />
														-->
												<s:select name="surgeon" id="surgeon" list="staffList"
													listKey="id" listValue="fullname"
													cssClass="form-control showToolTip chosen-select"
													headerKey="0" headerValue="Select Surgeon" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-6">
										<div class="form-group">
											<label for="inputEmail" class="control-label ">Anesthesiologist</label>
											<div class="form-group">
												<!--<s:textfield name="anesthesiologist"  cssClass="form-control" />-->
												<s:select name="anesthesiologist" id="anesthesiologist"
													list="anesthesiaList" listKey="id" listValue="reference"
													cssClass="form-control showToolTip chosen-select"
													headerKey="0" headerValue="Select Anesthesiologist" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-6 ">
										<div class="form-group">
											<label for="inputEmail" class="control-label ">Anesthesia
												Type</label>
											<div class="form-group">
												<!--<s:textfield name="anesthesia"  cssClass="form-control" />
														-->
												<s:select name="anesthesia" id="anesthesia"
													list="#{'0':'Select Anesthesia','1':'local','2':'General','3':'Spinal'}"
													cssClass="form-control showToolTip chosen-select" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12">
									<div class="col-lg-12 col-md-12 col-xs-12">
										<div class="form-inline">
											<div class="form-group" style="width: 100%;">
											<span id='ottempl'>
												<s:select list="otherTemplateList"
													onchange="getOTSurgicaltemplate(this.value)" listKey="id"
													listValue="name" cssClass="form-control" headerKey="0"
													headerValue="Select Template"></s:select>
											</span>		
												<input type="text" name="operativetempname"
													class="form-control setbackcolor"
													placeholder="Enter template name">
												<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('11',this)" >	
											</div>
											<div class="form-group">
											 <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'operation_notes')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
											</div>
										</div>
										<div class="form-group" style="margin-top: 10px;">
											<s:hidden name="otNotesids"></s:hidden>
											<label for="inputEmail" class="control-label ">Operative
												Notes</label>
												<label></label>
											<s:textarea cssClass="form-control" rows="6" maxlength=""
												name="otNotes" id="operation_notes" />
										</div>

										<%-- <s:if test="otdatesandids.size!=0">
												<div class="form-group" style="margin-top:10px;">
												<label for="inputEmail" class="control-label ">Operative Date</label>
													<s:iterator value="otdatesandids">
		                        			 			<p><s:property value="date"/>(<s:property value="id"/>)&nbsp;&nbsp;&nbsp;<b>Procedure:</b><s:property value="procedurename"/></p> 
		                        			 		</s:iterator>
												</div>
											</s:if>  --%>





									</div>

								</div>


							</div>
						</div>

					</div>
					</div>
			</s:if>
			<s:else>
				<div class="" id="surgicalnotesdiv">

					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							SURGICAL NOTES &nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">An
									Operative report is a report written in a patient's medical
									record to document the details of a surgery. The operative
									report is dictated right after a surgical procedure and later
									transcribed into the patient's record.</span></span>
						</h5>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
						<%--  <s:if test="otdatesandids.size!=0">
												<div class="form-group" style="margin-top:10px;">
													<label for="inputEmail" class="control-label ">Operative Date</label>
													<s:iterator value="otdatesandids">
		                        			 			<p><s:property value="date"/>&nbsp;&nbsp;&nbsp;<b>Procedure:</b><s:property value="procedurename"/></p> 
		                        			 		</s:iterator>
												</div>
											</s:if>  --%>


						<%
							ArrayList<Master> otdatesandids = (ArrayList<Master>) session.getAttribute("otdatesandids");
						if(otdatesandids==null){
							otdatesandids= new ArrayList<Master>();
						}
						
						%>
						<%
							int otid = 0;
						%>
						<s:hidden name="totalotids"></s:hidden>
						<div class="form-group" style="margin-top: 10px;">
							<label for="inputEmail" class="control-label ">Operative
								Date</label>
							<%
								for (Master master : otdatesandids) {
							%>
							<p><%=master.getDate()%>&nbsp;&nbsp;&nbsp;<b>Procedure:</b><input
									type="text" name="editotprocedure<%=master.getId()%>"
									class="form-control" value="<%=master.getProcedurename()%>">
							</p>
							<%
								otid++;
							%>
							<%
								}
							%>
						</div>

					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
						<div class="row">



							<div class="col-lg-12 col-md-12 col-xs-12">
								<div class="col-lg-6 col-md-6 col-xs-6">
									<div class="form-group">
										<label for="inputEmail" class="control-label ">Operative
											Procedure</label>
										<div class="form-group">
											<!--<s:textfield name="appointmentText"  cssClass="form-control" />-->
											<s:select name="appointmentText" id="appointmentText"
												list="procedureList" listKey="name" listValue="name"
												cssClass="form-control showToolTip chosen-select" headerKey="0"
												headerValue="Select Procedure" />
											<!-- <input type="text" name="appointmentText" placeholder="Or Enter Procedure " class="form-control" /> -->
											<s:textfield name="appointmentText"
												placeholder="Or Enter Procedure " cssClass="form-control" />
										</div>

									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-2 ">
									<div class="form-group">
										<label for="inputEmail" class="control-label ">Operating
											Surgeon</label>
										<div class="form-group">
											<!--<s:textfield name="surgeon"  cssClass="form-control" />
														-->
											<s:select name="surgeon" id="surgeon" list="staffList"
												listKey="id" listValue="fullname"
												cssClass="form-control showToolTip chosen-select"
												headerKey="0" headerValue="Select Surgeon" />
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-2">
									<div class="form-group">
										<label for="inputEmail" class="control-label ">Anesthesiologist</label>
										<div class="form-group">
											<!--<s:textfield name="anesthesiologist"  cssClass="form-control" />-->
											<s:select name="anesthesiologist" id="anesthesiologist"
												list="anesthesiaList" listKey="id" listValue="reference"
												cssClass="form-control showToolTip chosen-select"
												headerKey="0" headerValue="Select Anesthesiologist" />
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-2 ">
									<div class="form-group">
										<label for="inputEmail" class="control-label ">Anesthesia
											Type</label>
										<div class="form-group">
											<!--<s:textfield name="anesthesia"  cssClass="form-control" />
														-->
											<s:select name="anesthesia" id="anesthesia"
												list="#{'0':'Select Anesthesia','1':'local','2':'General','3':'Spinal'}"
												cssClass="form-control showToolTip chosen-select" />
										</div>
									</div>
								</div>

							</div>
							<div class="col-lg-12 col-md-12 col-xs-12">
								<div class="col-lg-12 col-md-12 col-xs-12">
									<div class="form-inline">
										<div class="form-group" style="width: 80%;">
										<span id='ottempl'>
											<s:select list="otherTemplateList"
												onchange="getOTSurgicaltemplate(this.value)" listKey="id"
												listValue="name" cssClass="form-control chosen-select" headerKey="0"
												headerValue="Select Template"></s:select>
										</span>		
											<input type="text" name="operativetempname" id='operativetempname'
												class="form-control setbackcolor"
												placeholder="Enter template name">
												
											<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('11',this)" >		
										</div>
											 <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'operation_notes')" title="Microphone" id="changer" ></img>
									</div>
									<div class="form-group" style="margin-top: 10px;">
										<s:hidden name="otNotesids"></s:hidden>

										<label for="inputEmail" class="control-label ">Operative
											Notes</label>
										<s:textarea cssClass="form-control" rows="6" maxlength=""
											name="otNotes" id="operation_notes" />
									</div>


									<%-- <s:if test="otdatesandids.size!=0">
												<div class="form-group" style="margin-top:10px;">
													<label for="inputEmail" class="control-label ">Operative Date</label>
													<s:iterator value="otdatesandids">
		                        			 			<p><s:property value="date"/>(<s:property value="id"/>)&nbsp;&nbsp;&nbsp;<b>Procedure:</b><s:property value="procedurename"/></p> 
		                        			 		</s:iterator>
												</div>
											</s:if> --%>
								</div>

							</div>

						</div>
					</div>

				</div>
			</s:else>
			<!-- <div class="text-right">
				<input  class="btn btn-primary savebtn savebigbtn"
					value="Save & Next"   onclick="saveSurgicalNotewsDivData()">
			</div> -->
     </div>			

			<!--  </div>-->
<div id="DEATH" class="col-lg-10 col-md-10 col-sm-10 tabcontent">			
			<s:if test="dischargeStatus==3">
				<div class="" id="">
					<div class="row">
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>
								CAUSE OF DEATH &nbsp;<span class="tooltip"><i
									class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">Death
										Reason</span></span>
							</h5>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

							
							<div class="form-group" style="margin-top: 10px;">

								<s:textarea cssClass="form-control" rows="6" maxlength="10"
									name="deathnote" id="death_note" />
							</div>

						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="hidden" id="deathcausediv">
					<div class="row">
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>
								CAUSE OF DEATH &nbsp;<span class="tooltip"><i
									class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">Death
										Reason</span></span>
							</h5>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

							<div class="form-inline">
								<div class="form-group" style="width: 100%;">
									<%-- <s:select list="hospital_course_list" onchange="gethosptemplate(this.value)" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template"></s:select>
								    <input type="text" name="hospitalcoursetempname" class="form-control setbackcolor" placeholder="Enter template name">
							    <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting1(this)" title="Microphone" id="changer"></img> --%>
								</div>
							</div>
							<div class="form-group" style="margin-top: 10px;">
								<s:textarea cssClass="form-control" rows="6" maxlength="10"
									name="deathnote" id="death_note" />
							</div>

						</div>
					</div>
				</div>
			</s:else>
			<!-- <div class="text-right">
				<input type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save & Next" onclick="">
			</div> -->
</div>
		<div id="COURSE" class="col-lg-10 col-md-10 col-sm-10 tabcontent">	
			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
				<h5>
					HOSPITAL COURSE &nbsp;<span class="tooltip"><i
						class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
							continuous progression from one point to the next in time or
							space; onward movement the course of his/her hospital stay</span></span>
				</h5>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<div class="form-inline">
					<div class="form-group" style="width: 100%;">
					<span id='hslist'>
						<s:select list="hospital_course_list"
							onchange="gethosptemplate(this.value)" listKey="id"
							listValue="name" cssClass="form-control" headerKey="0"
							headerValue="Select Template"></s:select>
					</span>		
						<input type="text" name="hospitalcoursetempname" id='hospitalcoursetempname'
							class="form-control setbackcolor "
							placeholder="Enter template name">
						<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('9',this)" >		
							 <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'hospitalcourse')" title="Microphone" id="changer"></img>
					</div>
				</div>
				<div class="form-group" style="margin-top: 10px;">
					<s:textarea cssClass="form-control" rows="6" maxlength=""
						name="hospitalcourse" id="hospitalcourse" />
				</div>

			</div>
			<!-- <div class="text-right">
				<input type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save & Next" onclick="saveHospitalCourseDivDataOfDischargeForm()">
			</div> -->
			</div>
		<div id="TG" class="col-lg-10 col-md-10 col-sm-10 tabcontent">	
			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<%-- <h5>TREATMENT GIVEN &nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The management and care of a patient,the combating of a disease or disorder called also therapy</span></span></h5> --%>
						<h5>
							TREATMENT GIVEN &nbsp;<a href="#" data-toggle="modal"
								data-target="#treatgiven" class="btn btn-info btn-sm">+</a>
						</h5>
					</div>


					<%-- <a href="#" data-toggle="modal"  onclick="getPriscriptionGiven(<s:property value="id"/>)" class="btn btn-info btn-sm">+</a></h5> --%>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline">
						<span id='ttreat'>
											<s:select list="treatmentgiventemplatelist"
							onchange="gettreatmentttemplate(this.value)" listKey="id"
							listValue="name" cssClass="form-control" headerKey="0"
							headerValue="Select Template"></s:select>
						</span>	
							
							<!-- <input type="text" name="treatmentgiventempname"
										class="form-control setbackcolor"
										placeholder="Enter template name">
								</div> -->
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="Add Template Name" class='form-control' id='treatmentgiventempnmae'>	
							<input type="button"  class='btn btn-primary' value ='save' onclick="saveIpdTemplates('7',this)">
							
						</div>
						<div class="form-group">
						<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'treatmentgiven')" title="Microphone" id="changer" ></img>
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="treatmentgiven" id="treatmentgiven" />
						</div>
					</div>
				<!-- 	<div class="text-right">
				<input type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save & Next" onclick="saveTreatmentGivenDivDataOfDischargeForm()">
			</div> -->
             </div>
<% String str="";%>
          <div id="INVESTIGATIONS" class="col-lg-10 col-md-10 col-sm-10 tabcontent">
					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							INVESTIGATIONS &nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">Investigation
									during hospital stay(blood/ct/mri etc)</span></span>
						</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline" style="margin-top: 10px;">
						<span id='invstl' >
							<s:select list="investigationList"
								onchange="getInvstemplate(this.value)" listKey="id"
								listValue="name" cssClass="form-control" headerKey="0"
								headerValue="Select Template"></s:select>
						</span>		
							<input type="text" name="investigationtempname" id='investigationtempname'
								class="form-control setbackcolor "
								placeholder="Enter template name">
							<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('',this)" >		
							<div class="form-group" >
							<s:select list="investigationtemplatelist" cssClass="form-control chosen-select" headerKey="" headerValue="Select Created Template" listKey="id" listValue="name"  onchange="getTabulardata(this.value)"></s:select>
							
							</div>	
							<div class="form-group" >
							<input type="button" class='btn btn-success' value="Include Investigations" onclick="entrInvestigation()">
							</div>
							
											 <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'investigation')" title="Microphone" id="changer" ></img>
											 	
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="investigation" id="investigation" />
						</div>
					</div>
			
			<s:hidden name="clientid" id="clientnewid"></s:hidden>
			<!-- <div class="text-right">
				<input type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save & Next" onclick="saveInvestigationDivDataOfDischargeForm()">
			</div> -->
        </div>
        
        <div id="MY1" class="col-lg-10 col-md-10 col-sm-10 tabcontent">
			<s:if test="dischargeStatus==3">
				<div class="hidden" id="treatmentgivendiv">

					<%-- <div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>TREATMENT GIVEN &nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The management and care of a patient,the combating of a disease or disorder called also therapy</span></span></h5>
						<h5>
							TREATMENT GIVEN &nbsp;<a href="#" data-toggle="modal"
								data-target="#treatgiven" class="btn btn-info btn-sm">+</a>
						</h5>
					</div>


					<a href="#" data-toggle="modal"  onclick="getPriscriptionGiven(<s:property value="id"/>)" class="btn btn-info btn-sm">+</a></h5>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline"></div>
						<div class="form-group" style="margin-top: 10px;"
							id="treatmentgivendiv">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="treatmentgiven" id="treatmentgiven" />
						</div>
					</div>



					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							INVESTIGATIONS &nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">Investigation
									during hospital stay(blood/ct/mri etc)</span></span>
						</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline">
							<s:select list="investigationList"
								onchange="getInvstemplate(this.value)" listKey="id"
								listValue="name" cssClass="form-control" headerKey="0"
								headerValue="Select Template"></s:select>
							<input type="text" name="investigationtempname"
								class="form-control setbackcolor"
								placeholder="Enter template name">
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="investigation" id="investigation" />
						</div>
					</div>
 --%>


					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							FINDING ON DISCHARGE&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">On
									Discharge patient condition (vitals0 and other things)</span></span>
						</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline">
						<span id='findingl'>
							<s:select list="finding_on_dischargeList"
								onchange="getFindOnDischtemplate(this.value)" listKey="id"
								listValue="name" cssClass="form-control" headerKey="0"
								headerValue="Select Template"></s:select>
						</span>
							<input type="text" name="findingondistempname" id='findingondistempname'
								class="form-control setbackcolor "
								placeholder="Enter template name">
						<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('16',this)" >			
						 <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'finddis')" title="Microphone" id="changer" ></img>
						</div>


						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="findondischarge" id="finddis" />
						</div>
					</div>






					<div class="col-lg-12 col-xs-12 col-md-12 textprime hidden" >
						<h5>
							TREATMENT ADVICE&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									written direction for the preparation, compounding, and
									administration of a medicine i.e. prescribed Medicine</span></span>
						</h5>
					</div>


					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
						<div class="form-inline"
							style="margin-bottom: 5px; margin-top: 5px;">
							<a style="cursor: pointer" class="btn btn-info"
								onclick="onAdd(<s:property value="clientid"/>,<s:property value="practitionerid"/>,<s:property value="conditionid"/>)">Add</a>
							<!-- <a style="cursor:pointer" class="btn btn-info" onclick=editdischargeprisc('<s:property value="parentpriscid"/>','<s:property value="clientid"/>','<s:property value="id"/>','<s:property value="practitionerid"/>','<s:property value="conditionid"/>') style="cursor: pointer;">Edit</a> -->
						</div>

						<div class="form-group">
							<table class="table table-bordered" id="priscTable">
								<thead>
									<tr class="headings">
										<th style="width: 5%;">Sr.No</th>
										<th class="uppercaseirf">Medicine1</th>
										<th>Dosage</th>
										<th>Days</th>
										<th>Notes</th>
										<th>Strength</th>
										<th>Remark</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="dischargedataid">
									<s:iterator value="dischargePriscList">
										<tr>
											<td><input type="number" class="form-control"
												name="dicpriscmed<s:property value="id"/>"
												value="<s:property value="dispriscsrno"/>"></td>
											<td><s:property value="mdicinenametxt" /></td>
											<td>
												<select id="discpriscdose<s:property value="id"/>" name="discpriscdose<s:property value="id"/>" class="form-control chosen-select">
													<s:iterator value="dosageList">
														<s:if test="name==priscdose">
															<option value="<s:property value="name" />" selected="selected"><s:property value="name" /></option>
														</s:if>
														<s:else>
															<option value="<s:property value="name" />"><s:property value="name" /></option>
														</s:else>
													</s:iterator>
												</select>
												<%-- <s:property value="priscdose" /> --%>
											</td>
											<td>
												<input type="number" class="form-control"
												name="dicpriscdays<s:property value="id"/>"
												value="<s:property value="priscdays"/>">
												<%-- <s:property value="priscdays" /> <s:property value="priscdurationtype" /> --%>
											</td>
											<td><s:property value="dosenotes" /></td>
											<td><s:property value="strengthnew" /> </td>
											<td><s:property value="priscindivisualremark" /> </td>
											<td><a
												onclick="removeMedicineDisc(this,<s:property value="id"/>)"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</s:iterator>
									<s:hidden name="totalchildmedids"></s:hidden>
								</tbody>
							</table>
						</div>
						
						<div id="priscnotes">
							<s:property value="advoice" />
						</div>
						
						
					</div>


					<br>

					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							DISCHARGE ADVICE/FOLLOW UP&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									Advice on discharge (follow up, dietary, do's and dont's)</span></span>
						</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

						<div class="form-inline">
							<div class="form-group" style="width: 100%;">
							<span id='disadvl'>	<s:select list="nursing_advice_list" listKey="id"
									onchange="getnursingtemplate(this.value)" listValue="name"
									cssClass="form-control" headerKey="0"
									headerValue="Select Template"></s:select></span>
								<input type="text" name="nursingadvicetempname" id='nursingadvicetempname'
									class="form-control setbackcolor "
									placeholder="Enter template name">
								<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('10',this)" >		
									 <img
									src="cicon/mic_off.png" class="img-responsive micimg"
									onclick="startConverting2(this)" title="Microphone"
									id="changer"></img>
									
							</div>


						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="discadvnotes" id="discadvnotes" />
						</div>
						
					</div>

				</div>
			</s:if>
			<s:else>
				<div class="" id="treatmentgivendiv">

					<%-- <div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>TREATMENT GIVEN &nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The management and care of a patient,the combating of a disease or disorder called also therapy</span></span></h5>
						<h5>
							TREATMENT GIVEN &nbsp;<a href="#" data-toggle="modal"
								data-target="#treatgiven" class="btn btn-info btn-sm">+</a>
						</h5>
					</div>


					<a href="#" data-toggle="modal"  onclick="getPriscriptionGiven(<s:property value="id"/>)" class="btn btn-info btn-sm">+</a></h5>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline">
							<s:select list="treatment_given_list"
								onchange="gettreattemplate(this.value)" listKey="id"
								listValue="name" cssClass="form-control" headerKey="0"
								headerValue="Select Template"></s:select>
							<input type="text" name="treatmentgiventempname"
								class="form-control setbackcolor"
								placeholder="Enter template name">
						</div>
						<div class="form-group" style="margin-top: 10px;"
							id="treatmentgivendiv">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="treatmentgiven" id="treatmentgiven" />
						</div>
					</div>



					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							INVESTIGATIONS &nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">Investigation
									during hospital stay(blood/ct/mri etc)</span></span>
						</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline">
							<s:select list="investigationList"
								onchange="getInvstemplate(this.value)" listKey="id"
								listValue="name" cssClass="form-control" headerKey="0"
								headerValue="Select Template"></s:select>
							<input type="text" name="investigationtempname"
								class="form-control setbackcolor"
								placeholder="Enter template name">
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="investigation" id="investigation" />
						</div>
					</div> --%>



					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							FINDING ON DISCHARGE&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">On
									Discharge patient condition (vitals0 and other things)</span></span>
						</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline">
						<span id='findingl'>	<s:select list="finding_on_dischargeList"
								onchange="getFindOnDischtemplate(this.value)" listKey="id"
								listValue="name" cssClass="form-control" headerKey="0"
								headerValue="Select Template"></s:select></span>

							<input type="text" name="findingondistempname" id='findingondistempname'
								class="form-control setbackcolor "
								placeholder="Enter template name">
								<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'finddis')" title="Microphone" id="changer" ></img>
						
						<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('16',this)" >			
						</div>


						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="findondischarge" id="finddis" />
						</div>
					</div>






					<div class="col-lg-12 col-xs-12 col-md-12 textprime ">
						<h5>
							TREATMENT ADVICE&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									written direction for the preparation, compounding, and
									administration of a medicine i.e. prescribed Medicine</span></span>
						</h5>
					</div>


					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="form-inline"
							style="margin-bottom: 5px; margin-top: 5px;">
							<a style="cursor: pointer" class="btn btn-info"
								onclick="onAdd(<s:property value="clientid"/>,<s:property value="practitionerid"/>,<s:property value="conditionid"/>)">Add</a>
							<!-- <a style="cursor:pointer" class="btn btn-info" onclick=editdischargeprisc('<s:property value="parentpriscid"/>','<s:property value="clientid"/>','<s:property value="id"/>','<s:property value="practitionerid"/>','<s:property value="conditionid"/>') style="cursor: pointer;">Edit</a> -->
						</div>

						<div class="form-group">
							<table class="table table-bordered" id="priscTable">
								<thead>
									<tr class="headings">
										<th style="width: 5%;">Sr.No</th>
										<th>Medicine</th>
										<th>Dosage</th>
										<th style="width: 5%;">Days</th>
										<th>Notes</th>
										<th>Strength</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="dischargedataid">
									<s:iterator value="dischargePriscList">
										<tr>
											<td><input type="number" class="form-control"
												name="dicpriscmed<s:property value="id"/>"
												value="<s:property value="dispriscsrno"/>"></td>
												<!--text-uppercase  -->
											<td class="uppercaseirf"><s:property value="mdicinenametxt" /></td>
											<td>
												<select id="discpriscdose<s:property value="id"/>" name="discpriscdose<s:property value="id"/>" class="form-control chosen-select">
													<s:iterator value="dosageList">
														<s:if test="name==priscdose">
															<option value="<s:property value="name" />" selected="selected"><s:property value="name" /></option>
														</s:if>
														<s:else>
															<option value="<s:property value="name" />"><s:property value="name" /></option>
														</s:else>
													</s:iterator>
												</select>
												<%-- <s:property value="priscdose" /> --%>
											</td>
											<td>
												<input type="number" class="form-control"
												name="dicpriscdays<s:property value="id"/>"
												value="<s:property value="priscdays"/>">
												<%-- <s:property value="priscdays" /> <s:property value="priscdurationtype" /> --%>
											</td>
											<td><s:property value="dosenotes" /></td>
											<td><s:property value="strength" /> </td>
											<td><a
												onclick="removeMedicineDisc(this,<s:property value="id"/>)"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</s:iterator>
									<s:hidden name="totalchildmedids"></s:hidden>
								</tbody>
							</table>
						</div>
						<div id="priscnotes">
							<s:property value="advoice" />
						</div>
						<div class="form-group <%=kunalneg %>" style="margin-top:30px;" >
					<div class="form-inline">
									<div class="form-group">
								          <label for="exampleInputName2"><b>Prescription Text</b></label>
								     </div>
								      <div class="form-group">
								    <s:select list="discharge_default_list"
										onchange="getkunalmedtext(this.value)" listKey="id"
										listValue="name" cssClass="form-control" headerKey="0"
										headerValue="Select Template"></s:select>
										</div>
										<div class="form-group">
										<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'kunal_manual_medicine_text')" title="Microphone" id="changer" style="width: 25% !important" ></img>
								    </div>
					</div>			     
										<s:textarea cssClass="form-control" rows="9" maxlength="" name="kunal_manual_medicine_text" id="kunal_manual_medicine_text"></s:textarea>
					</div>
					</div>


					<br>






					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							DISCHARGE ADVICE/FOLLOW UP&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									Advice on discharge (follow up, dietary, do's and dont's)</span></span>
						</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

						<div class="form-inline">
							<div class="form-group" style="width: 100%;">
							<span id='disadvl'>		<s:select list="nursing_advice_list" listKey="id"
									onchange="getnursingtemplate(this.value)" listValue="name"
									cssClass="form-control" headerKey="0"
									headerValue="Select Template"></s:select></span>
								<input type="text" name="nursingadvicetempname" id='nursingadvicetempname'
									class="form-control setbackcolor "
									placeholder="Enter template name">
									<input type="button" class='btn btn-primary'  value='Save Template' onclick="saveIpdTemplates('10',this)" >		
									
								<label class="hidden">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Follow up date:</label>	<input type="text" placeholder="followup date" name="followupdate1" id='followupdate1' class='form-control hidden' onchange="giveIpdFollowup(this.value)">
									 <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'discadvnotes')" title="Microphone" id="changer" ></img>
									
							</div>


						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="discadvnotes" id="discadvnotes" />
						</div>
					</div>

				</div>
			</s:else>
			<!-- <div class="text-right">
				<input type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save & Next" onclick="saveOtherDivDataOfDischargeForm()">
			</div> -->
			</div>
			<div id="EME" class="col-lg-10 col-md-10 col-sm-10 tabcontent">
			<div class="">
					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							Emergency Details&nbsp;<%-- <span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									Advice on discharge (follow up, dietary, do's and dont's)</span></span> --%>
						</h5>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline">
							<div class="form-group" style="width: 100%;">
							<span id='emem'>
								<s:select list="emergencydetailslist" listKey="id" headerKey="0" headerValue="Select template"
									onchange="getemergencytemplate(this.value)" listValue="name"
									cssClass="form-control" ></s:select></span>
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="Add Template Name" class='form-control' id='emercontdetailtemplatename'>	
							<input type="button"  class='btn btn-primary' value ='save' onclick="saveIpdTemplates('12',this)">
							<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'emercontdetail')" title="Microphone" id="changer" ></img>
							</div>
							<div class="form-group" >
							 
							</div>
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="emergencydetail" id="emercontdetail" />
						</div>
						
					</div>
			</div>
			<!-- <div class="text-right">
				<input type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save" onclick="saveEmergencydataDivDataOfDischargeForm()">
			</div> -->
			</div>
			<div class="text-right">
				<input id='b1'  type="button" class="btn btn-primary savebtn savebigbtn"
					value="Save" onclick='funchange()' style="margin-top: 62px;" >
			
			</div>
			
		</div>




		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
			<div class="">
				<div class="col-lg-12 col-md-12 addmissiontitle">
					<b>Discharge (Y / N)</b>
				</div>

			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<div class="form-group">
					<s:checkbox cssStyle="margin-right:110px;" theme="simple"
						name="chkDischarge" id="edchkDischarge" />
				</div>

			</div>
		</div>

		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">

				<div class="form-group">
					<label for="inputEmail" class="control-label">Admitting
						Consultant</label>
					<p class="help-block">
						<s:property value="practitionerid" />
					</p>
				</div>
				<div class="form-group">
					<label for="inputEmail" class="control-label">Referred From</label>
					<p class="help-block"><%=ipd.getRefferedby()%></p>

				</div>
				<div class="form-group">
					<label for="inputEmail" class="control-label">Admission For</label>
					<p class="help-block"><%=ipd.getAddmissionfor()%></p>

				</div>
				<div class="form-group">
					<label for="inputEmail" class="control-label">Reimbursement</label>
					<p class="help-block"><%=ipd.getReimbursment()%></p>

				</div>
				<div class="form-group">
					<label for="inputEmail" class="control-label">Sugested
						Treatment</label>
					<p class="help-block"><%=ipd.getSuggestedtrtment()%></p>

				</div>
				</form>

			</div>
			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">

				<div class="form-group">
					<label for="inputEmail" class="control-label">Associate
						Consultant</label>
					<p class="help-block">
						<s:property value="secndryconsult" />
					</p>
				</div>


				<div class="form-group">
					<label for="inputEmail" class="control-label">Ward</label>
					<p class="help-block">
						<s:property value="wardid" />
					</p>
				</div>


				<div class="form-group" id="bedlistdiv">
					<label for="inputEmail" class="control-label">Bed no.</label>
					<p class="help-block">
						<s:property value="bedid" />
					</p>

				</div>



				<div class="form-group">
					<label for="inputEmail" class="control-label">Admission
						Details</label>
					<p class="help-block"><%=ipd.getAdmissiondetails()%></p>

				</div>

				<div class="form-group">
					<label for="inputEmail" class="control-label">Hospital/Clinic</label>
					<p class="help-block"><%=ipd.getHosp()%></p>

				</div>
				</form>

			</div>
			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">

				<div class="form-group">
					<label for="inputEmail" class="control-label ">Location</label>
					<p class="help-block"><%=ipd.getDepartment()%>
					</p>
				</div>

				<div class="form-group">
					<label for="inputEmail" class="control-label">Insurance</label>
					<p class="help-block">
						<s:property value="thirdParty" />
					</p>
				</div>


				<div class="form-group" id="bedlistdiv">
					<label for="inputEmail" class="control-label">Status</label>
					<p class="help-block"><%=ipd.getStatus()%></p>
				</div>


				<div class="form-group">
					<label for="inputEmail" class="control-label">MLC no.</label>
					<p class="help-block"><%=ipd.getMlcno()%></p>
				</div>


				<div class="form-group">
					<label for="inputEmail" class="control-label">Package</label>
					<p class="help-block"><%=ipd.getPackagename()%></p>
				</div>
				</form>

			</div>

		</div>



<div id="jsondiamodal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Auto Population Of Discharge Card </h4>
				</div>
				
				<div class="modal-body">
					<div class="row">
					<h4 align="center"><b>We have found the historic discharge cards based on final diagnosis you have selected.</b></h4>
						<br><div class="col-lg-12" id='diagdiv'>
						
						</div>
					</div>	
				</div>
				
				<div class="modal-footer">
						<input type="button" class="btn btn-primary" value="Load" onclick="setdiagnosisIpdid()"
							/>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
				
			</div>
		</div>
	</div>
				

	<%-- </s:form>
 --%>
	<!-- Finding Discharge -->
	<div id="treatgiven" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">PRISCRITION GIVEN</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<!--<div class="">
											<table id="results" class="table table-hove table-bordered table-striped table-condensed">
												<thead>
													<tr>
														<th class="text-center">CheckBox</th>
														<th class="text-center">Preiscription Given</th>
														<th class="text-center">Dose</th>
													</tr>
												</thead>
												<tbody id="priscdiv">
												</tbody>
												<input type="hidden" id="icount">
											</table>
										</div>
									-->
							<div class="col-lg-6 col-md-6" style="padding-top: 15px;">
								<form class="form-inline">
									<p>Product Allocated</p>
									<div class="six columns">
										<article>
											<input id="search" name="search" class="form-control"
												placeholder="search here" type="text"
												data-list=".default_list" autocomplete="off"
												style="width: 100%;">
											<div id="scrolltable">
												<ul class="vertical default_list" id="allprod"
													style="list-style: none; padding: 0px;">
													<li><input type="checkbox" onclick="selectAll(this)" />
														Select All</li>
													<s:iterator value="medicineList">
														<li><input class="case" type="checkbox"
															value="<s:property value="id"/>" /> <s:property
																value="genericname" /></li>
													</s:iterator>
												</ul>
											</div>
										</article>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-primary" value="Add"
							onclick="addMedicinePriscription()" />
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>


	</div>






	<div class="modal fade" id="priscriptionpopup" tabindex="-1"
		role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="priscmyModalLabel">
						Create Prescription For <b class="pname">NAME: </b>
					</h5>
				</div>
				<div class="modal-body">


					
					 <s:include value="/diarymanagement/pages/addpriscription.jsp"></s:include>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						onclick="saveTemplae(0)">Save Template</button>
					<button type="button" class="btn btn-primary"
						onclick="insertDischargePriscription(0)" id="prescs_save_btn" >Save</button>
					<button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)" id="prescs_save_print_btn">Save & Print</button>

					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
		
	</div>

 <!-- Voice recorder  -->
<div id="miic" class="modal fade" role="dialog" aria-labelledby="myModalLabel" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-sm" style="width: 70%">
    <!-- Modal content-->
    <div class="modal-content" style="height: 202px !important">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Voice Over</h4>
      </div>
      <div class="modal-body" style=" padding-top: 15px !important">
      			 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="margin-left: -119px">
      			<img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting15(this)" title="Microphone" id="changer" style="width: 25%"></img>
      			
      			</div>
      				<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
      				</div>
		               <div class="form-group" style="margin-top:41px;">
												<s:textarea cssClass="form-control" rows="3" name="voice" id="voicemic"/>
											
					<div >

	                           
	                                  
									</div>	
		            </div>
        	
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
      </div>
    </div>
 </div>
 </div>

<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="disloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>
	<!-- create Prescription -->

	<script>
	var patientId = 0;
	var diaryuserId = 0;
	var editcondition_id = 0;
	function onAdd(cid,pid,conid){
		patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
		removeSession();
		repeatePriscDateAjax(cid,pid,conid);
		
	}
	
	function editviewparentprisc(cid,pid,conid,id){
		patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
		editparentpriscid = id;
		//editparentprisc(id);
		document.getElementById("repeatdate").value="0";
		
		repeateEditPriscDateAjax(cid,pid,conid);
		
	}
	
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
    startConvertinghoscourse();
   }
   else{
   //var textvalue=document.getElementById("otnotes").value;
  // localStorage.setItem("xx",textvalue);
   location.reload();
   }
    
     }
     
     
     function startConverting2(element) {

   var abc=element.src.split('/');
   
     var right = "cicon/mic_off.png";
         var left = "cicon/mic.png";
         element.src = element.bln ? right : left;
         element.bln = !element.bln;
         
       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
   if(abc[5]=="mic_off.png")
   {
    startConvertingdisadnotes();
   }
   else{
   //var textvalue=document.getElementById("otnotes").value;
  // localStorage.setItem("xx",textvalue);
   location.reload();
   }
    
     }
     
     function startConverting3(element) {

   var abc=element.src.split('/');
   
     var right = "cicon/mic_off.png";
         var left = "cicon/mic.png";
         element.src = element.bln ? right : left;
         element.bln = !element.bln;
         
       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
   if(abc[5]=="mic_off.png")
   {
    startConvertingdishistory();
   }
   else{
   //var textvalue=document.getElementById("otnotes").value;
  // localStorage.setItem("xx",textvalue);
   location.reload();
   }
    
     }
     
     
     
     
     function setfreedischargedata(val){
    	 setCheckedData(val);
    	 document.getElementById("finaldiagnosisnew").value=val.value;
    	 var cl=document.getElementById("clientid").value;
    	 document.getElementById("clientidnew").value=cl;
    	 var ipid=document.getElementById("clientipdid1").value;
    	 document.getElementById("clip").value=ipid;
    	  	 document.getElementById("predischarge").submit();
    	  	
     }
     
</script>


	<input type="hidden" name="invstcmyModalLabel" id="invstcmyModalLabel">
	<input type="hidden" name="invstdatetimeid" id="invstdatetimeid">
	<input type="hidden" name="invstpriscdoctornameid"
		id="invstpriscdoctornameid">

	<input type="hidden" name="eotpriscmyModalLabel"
		id="eotpriscmyModalLabel">
	<input type="hidden" name="eotdatetimeid" id="eotdatetimeid">
	<input type="hidden" name="eotpriscdoctornameid"
		id="eotpriscdoctornameid">



</body>

<!-- JS -->
<script type="text/javascript"
	src="inventory/js/searchtext/javascripts/vendor/jquery.hideseek.min.js"></script>
<script type="text/javascript"
	src="inventory/js/searchtext/javascripts/vendor/rainbow-custom.min.js"></script>
<script type="text/javascript"
	src="inventory/js/searchtext/javascripts/vendor/jquery.anchor.js"></script>
<script src="inventory/js/searchtext/javascripts/initializers.js"></script>
<!-- JS ends -->


<script type="text/javascript"
	src="common/tablesortnew/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="common/tablesortnew/dataTables.bootstrap.js"></script>
<script>
    	$(function() {
	    $('#scrolltable').slimScroll({
	   		height : '200px',
	   		railVisible: true,
			alwaysVisible: true
	  });
	 });
    </script>
    <form action="predischargeIpd" id="predischarge">
		<input type="hidden" name="finaldiagnosisnew" id="finaldiagnosisnew">
		<input type="hidden" name="clientidnew" id="clientidnew">
		<input type="hidden" name="clip" id="clip" >
		</form>
		
		
		
		<script>
  function voicerecord() {
	  $('#miic').modal( "show" );	
}
  
      function startConverting155(element,id) {

		   var abc=element.src.split('/');	
		   
		     var right = "cicon/mic_off.png";
		         var left = "cicon/mic.png";
		         element.src = element.bln ? right : left;
		         element.bln = !element.bln;
		         
		       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
		   if(abc[5]=="mic_off.png")
		   {
		    startConvertingadvicepres45(id);
		   }
		   else{
		   //var textvalue=document.getElementById("otnotes").value;
		  // localStorage.setItem("xx",textvalue);
		   
		   }
		     }
    
	
	</script> 
  <script type="text/javascript">
 
        // Load the Google Transliterate API
        google.load("elements", "1", {
            packages: "transliteration"
        });
 
        function onLoad() {
            var options = {
                sourceLanguage:
                google.elements.transliteration.LanguageCode.ENGLISH,
                destinationLanguage:
                [google.elements.transliteration.LanguageCode.HINDI],
                transliterationEnabled: true
            };
 
            // Create an instance on TransliterationControl with the required
            // options.
            var control =
            new google.elements.transliteration.TransliterationControl(options);
 
            // Enable transliteration in the textbox with id
            // 'transliterateTextarea'.
            control.makeTransliteratable(['voicemic']);
        }
        google.setOnLoadCallback(onLoad);
    </script>
<script>
  function startConvertingadvicepres45(id){
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
		nicEditors.findEditor(''+id).setContent(vtxt);
		
		
		
	};

}
  
  </script>
  <script type="text/javascript">
			function startConvertingadvicepres(){
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
					document.getElementById('voicemic').value=vtxt;
					
					
					
				};

			}

			</script>
</html>





