<%@page import="com.apm.common.utils.DateTimeUtils"%>
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
	
	Bed newDischCardFields=(Bed)request.getAttribute("newDischargeFields");
%>
 <%
 
String newAureusCardAccess="hidden"; 
 
 
 String kunalacces="";
 String aureusacces="";
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
 if(loginInfo.getIskunal()==1){
	 kunalacces="hidden";
 }
 if(loginInfo.getClinicUserid().equals("aureus")){
	 aureusacces="hidden";
 }
String kunalneg="";
String nicusetting="";

if(loginInfo.getIskunal()==0){
	kunalneg="hidden";
}
 
String islamadama="none";
String dischargeStatus=DateTimeUtils.isNull((String)request.getAttribute("dischargeStatus"));
if(dischargeStatus.equals("6")||dischargeStatus.equals("7")){
	islamadama="block";
}
 %>
 
<s:if test="nicuaccess">
<%nicusetting="hidden"; %>
</s:if> 
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admission Summary Form</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="_assets/newtheme/css/main.css" rel="stylesheet" />
<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
<link href="_assets/css/priscription/hospitalresponsive.css"
	rel="stylesheet" />
<link href="common/css/aureus_dcard.css" rel="stylesheet" />


<script type="text/javascript" src="ipd/js/admissionform.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<script type="text/javascript" src="ipd/js/aureus_dcard.js"></script>

<script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="ipd/js/dischargeform.js"></script>
<script type="text/javascript" src="emr/js/clinical_notes.js"></script>
<script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>
<script>
var patientId = 0;
var diaryuserId = 0;
var editcondition_id = 0;
function onAdd(cid,pid,conid){
	patientId = cid;
	diaryuserId = pid;
	editcondition_id = conid;
	treatmentadvc=0;
	removeSession();
	repeatePriscDateAjax(cid,pid,conid);
	
}
function onAddTreat(cid,pid,conid){
	patientId = cid;
	diaryuserId = pid;
	editcondition_id = conid;
	treatmentadvc=1;
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
<%-- <script>

setInterval(function(){ 
 submitaddmissionFor();
 
  }, 1000*5);
 

</script> --%>
</script>
<style>

.spc{

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
</style>
</head>

<script type="text/javascript">
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
		if(document.getElementById("emercontdetail")){
			new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('emercontdetail');
		}
		
		 /*  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('discadvnotes');
		 */// paditric 
		 if(document.getElementById("maternal_history")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('maternal_history');
				
		 }
		 if(document.getElementById("perinatal_history")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('perinatal_history');
		     
		 }
		 if(document.getElementById("tttr")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 50}).panelInstance('tttr'); 
				 
		 }
		 if(document.getElementById("chiefcomplains1")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('chiefcomplains1'); 
			   
		 }
		 if(document.getElementById("developmenthist")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('developmenthist');
			  
		 }
		 if(document.getElementById("birthhist")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('birthhist');
			  
		 }
		 if(document.getElementById("emmunizationhist")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('emmunizationhist');
			 
		 }
		 if(document.getElementById("diethist")){
			  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('diethist');
			    
		 }
		 if(document.getElementById("pasthistorylok")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('pasthistorylok');
				
		 }
		 if(document.getElementById("familyhistlok")){
			   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('familyhistlok'); 
		 }if(document.getElementById("earlierinvestlok")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('earlierinvestlok');
			   
		 }if(document.getElementById("suggestedtrtmentlok")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('suggestedtrtmentlok');
			   
		 }if(document.getElementById("personalhistlok")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('personalhistlok');
			   
		 }if(document.getElementById("onexaminationlok")){
			 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 100}).panelInstance('onexaminationlok');
			  
		 }
		 
		 
		    
		        
/* 	    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('surgicalnoteslok') */;
	       if(document.getElementById('investigation')){
			   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('investigation');

		   }
	    if(document.getElementById('presentillness')){
		   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('presentillness');   
	   } 
	   if(document.getElementById('death_note')){
		   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('death_note');
	   }
	  
	   if(document.getElementById('kunal_manual_medicine_text')){
		   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 100}).panelInstance('kunal_manual_medicine_text');
			  
	   }
	   if(document.getElementById('kunalfinaldiagnosis')){
		   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 100}).panelInstance('kunalfinaldiagnosis');
			  
	   }
	 
	   
	   
		 
	    
	    
	      if(document.getElementById('surgicalnotes')){
	    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('surgicalnotes');
	    }
	    

	
	/* new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('treatmenthistory'); */
    /* new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('treatmentgiven'); */
	
      if(document.getElementById('addmissionfor')){
    	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('addmissionfor');
    	    
      }
 if(document.getElementById('alergies')){
	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('alergies');
	  
      }
 if(document.getElementById('earlyinvs2')){
	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('earlyinvs2');
	      
 }

 if(document.getElementById('dietary_advice')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('dietary_advice');
	  
}
   
 if(document.getElementById('local_relevant_area')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('local_relevant_area');
	  
}
if(document.getElementById('tubes_and_training')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('tubes_and_training');
	  
}
if(document.getElementById('line_tube_drains')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('line_tube_drains');
	  
}
if(document.getElementById('when_to_get_help')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('when_to_get_help');
	  
}

if(document.getElementById('call_for_appointmant')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('call_for_appointmant');
	  
}

if(document.getElementById('consent_sign')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('consent_sign');
	  
}

if(document.getElementById('general_other')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('general_other');
	  
}

if(document.getElementById('discadvnotes1')){
	   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 100}).panelInstance('discadvnotes1');
		  
}


if(document.getElementById('discadvnotes')){
	   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 100}).panelInstance('discadvnotes');
		  
}


 if(document.getElementById('discharge_default')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('discharge_default');
	  
 }
 if(document.getElementById('finddis')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('finddis');
	  
}
 if(document.getElementById('treatmentgiven')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('treatmentgiven');
	  
}
 if(document.getElementById('hospitalcourse')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('hospitalcourse');
	  
}
    
 if(document.getElementById('operation_notes')){
	  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('operation_notes');
	  
}
    
    
 
   /*  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('treatmentgiven1');
     */
});


window.onload = function() {
    
    $('.classD').each(function() { 
		if(this.checked==true){
			selected=selected+","+this.value;
		}
		
    }); 
    setdischecked();
   };



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
	String daycareaccess="";
%>

<s:if test="daycare">
<%daycareaccess="hidden"; %>
</s:if>
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






<body>
	<s:form action="updatedischargeCommonnew" onsubmit="return isValidForm()"
		theme="simple" id='upadtedis'>
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

		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 textprimegreen">
				<div class="col-lg-6 col-xs-6 col-md-6 text-left"
					style="padding-left: 0px;">
					<!--<h3>Hospital Name</h3>
		-->
		<s:hidden name="jsonipdid" id='jsonipdid'></s:hidden>
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

			<div class="col-lg-12 col-md-12 col-xs-12"
				style="padding-top: 5px; padding-bottom: 5px;">
				<span style="color: brown;">IMPORTANT: Please completed all
					required field <font color="red">*</font>
				</span>
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
				<h5>PERSONAL AND ADMINISTRATION DETAILS</h5>
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12"
				style="padding: 0px; background-color: rgba(245, 245, 245, 0.95);">
				<div class="col-lg-2 col-xs-2 col-md-2"
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
				<div class="col-lg-10 col-md-10 col-xs-6 col-sm-9"
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
							<label for="exampleInputEmail1"><s:if test="daycare">Daycare Id</s:if><s:else>Admission ID</s:else></label>
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
										cssClass="form-control" headerKey="00" headerValue="00"
										style="width:100%;" />
								</div>
								<div class="form-group" style="width: 35%;">
									<s:select name="minute" id="editminute" list="minuteList"
										cssClass="form-control" headerKey="0" headerValue="Select MM"
										style="width:100%;" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
						<div class="form-group <%=kunalacces%> <%=aureusacces%>">
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

			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
				<h5>
					<span id='audiagnosis'>DIAGNOSIS<s:if test="dischargeStatus==3"> / CAUSE OF DEATH</s:if></span>&nbsp;<span class="tooltip"><i
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
										style="height: 100px; display: block; overflow: scroll; width: 100%"; >
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


			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
				<s:if test="dischargeStatus==3">
					<h5>DEATH SUMMARY</h5>
				</s:if>
				<s:else>
					<h5>ADMISSION SUMMARY</h5>
				</s:else>

			</div>

                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
                        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        	<div class="form-group hidden">
                        			<label for="exampleInputName2">Reason For Admission</label>
								   <s:textfield name="admission_reason" id="admission_reason" cssClass="form-control" />
							</div>
                        </div>
                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                        		<div class="form-inline">
								  <div class="form-group">
								    <label for="exampleInputName2"><b><!-- CHIEF/PRESENT COMPLAINTS AND REASON FOR ADMISSION -->Clinical Presentation on Admission :</b></label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A subjective statement made by a patient describing the most significant or serious symptoms or signs of illness or dysfunction that caused him or her to seek health care></span></span>
								    <s:select cssClass="form-control chosen-select" list="chief_complaints_list"  listKey="id"  listValue="name" onchange="setChiefComplaints(this.value)" headerKey="0" headerValue="Select Template">
								    </s:select>
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
								  	<input type="text" name="chiefcomplatetempname" class="form-control setbackcolor" placeholder="Enter template Name">  
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

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">



				<%
					if (ipd.getAlergies() != null) {
				%>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
					<div class="form-group">
						<label for="inputEmail"><b>H/O ALLERGIES</b></label>
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
						<label for="exampleInputName2"><b>Chief Complaints</b></label>
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
							id="chiefcomplains" />
					</div>
				</div>

				<%
					}
				%>






				<%
					if (ipd.getPresentillness() != null) {
				%>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
					<%-- <div class="form-group">
								<label for="exampleInputName2">Present Illness</label>
									<p class="help-block text-justify"><%=ipd.getPresentillness() %> </p>
									<s:textarea cssClass="form-control" rows="6" maxlength="" name="presentillness" id="presentillness"/>
								</div> --%>
					<div class="form-inline">
						<label for="exampleInputName2"><b>PRESENT ILLNESS</b></label>
						<s:select list="present_illness_list" cssClass="form-control"
							headerKey="0" onchange="getpresentIllness(this.value)"
							headerValue="Select Template" listKey="id" listValue="name">
						</s:select>
						<input type="text" name="presentillnesstempname"
							class="form-control setbackcolor"
							placeholder="Enter template Name">
						<!--<button type="submit" class="btn btn-primary">+</button>
								-->
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
             <input type="button" class="btn btn-info" onclick="getIPDTempData1()" value="Get Data"  align="right" style="width:10%;"/>
          </div>
  <!-- by lokesh -->
		                         	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=pediatric_histry %> %>" id="newtablespediatric">
		                         	 <div class="">
		                         	 
		                         	  <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"><b>Weight on Birth</b></label>
		                         	     <s:textfield name="wtonbirth"  placeholder="Weight on Birth" cssClass="form-control"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	 
		                         	 <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"><b>Weight on Admission</b></label>
		                         	     <s:textfield name="wtaddmission"  placeholder="Weight on Admission" cssClass="form-control"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"><b>Head Circumference</b></label>
		                         	  <s:textfield name="headcircumference"  placeholder="Head Circumference" cssClass="form-control"></s:textfield>
										
		                         	 </div>
		                        	</div>
		                        	
		                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"><b>Length</b></label>
		                         	     <s:textfield name="length"  placeholder="Length" cssClass="form-control" ></s:textfield>
		                         	 </div>
		                         	</div>
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"><b>Gestational Age</b></label>
		                         	     <s:textfield name="gstureage"  placeholder="Gestational Age"  cssClass="form-control"></s:textfield>
		                         	 </div>
		                         	</div>
		                        	
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 hidden">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"><b>Mid Arm Circumference</b></label>
		                         	     <s:textfield name="midarmcircumference"  placeholder="mid arm Circumference" cssClass="form-control"></s:textfield>
		                         	 </div>
		                         	</div>
		                         
		                         	
		                         	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
		                         	 <div class="form-group">
		                         	   <label for="inputEmail" class="control-label"><b>Weight on discharge</b></label>
		                         	     <s:textfield name="wtdischarge"  placeholder="Weight on discharge" cssClass="form-control"></s:textfield>
		                         	 </div>
		                         	</div>
		                         	</div>
		                         	</div>
 
<%String nicuhide="hidden"; %>
<s:if test="nicuaccess">
<%nicuhide=""; %>
</s:if>
<div class='<%=nicuhide%>'>
  						 <div class="col-lg-12 col-xs-12 col-md-12 textprime ">
							<h5>NICU HISTORY</h5> 
						</div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"  style="padding-top: 10px;">
                        <h5><b>MATERNAL HISTORY</b></h5> 
                        </div> 
                        <div class="form-inline">
                        <div class="form-group"> 
                        <s:select list="maternal_histry_list" onchange="getipdmaternalhistryTemp(this.value)" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
						</s:select>
                        </div>
                        <div class="form-group">
								  				<input type="text" name="maternalhisttemp" class="form-control setbackcolor" placeholder="Enter template name">  
						</div>
                        </div>  
                         
                        <div class="form-group" style="margin-top:10px;">
								<s:textarea cssClass="form-control" rows="6" maxlength="" name="maternal_history" id="maternal_history"></s:textarea>
						</div>
                        </div>
                       
                       
                        <div class="col-lg-12 col-xs-12 col-md-12  " style="padding-top: 10px;">
							<h5><b>PERINATAL HISTORY</b></h5> 
						</div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                       
                        <div class="form-inline">
                        <div class="form-group"> 
                        <s:select list="perintal_hisry_list" onchange="getperinatalHistrTemp(this.value)" cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" headerValue="Select Template">
						</s:select>
                        </div>
                        <div class="form-group">
								  				<input type="text" name="perinataltemp" class="form-control setbackcolor" placeholder="Enter template name">  
						</div>
                        </div> 
                       
                         
                        <div class="form-group" style="margin-top:10px;">
								<s:textarea cssClass="form-control" rows="6" maxlength="" name="perinatal_history" id="perinatal_history"></s:textarea>
						</div>
                        </div>
                       
</div>

			<div class="col-lg-12 col-xs-12 col-md-12 textprime <%=hstry%>">
				<h5>HISTORY</h5>
			</div>

		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=hstry%>">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
		
			<div class="form-group"  >
			  <div class="form-group lokhaeders">
			  
               <label for="exampleInputName2"><b>PAST HISTORY</b></label>
               
             </div>
             <div class='<%=newAureusCardAccess%>'>
				<s:textarea cssClass="form-control" rows="3" maxlength="" name="pasthistory" id="pasthistorylok"></s:textarea>
			</div>
			
			<div class="">
			<table style="width: 100%" class='newloktable'>
					<col width="10%">
					<col width="3%">
					<col width="35%">
					<col width="4%">
					<col width="10%">
					<col width="3%">
					<col width="35%">
			<tr>
			<% String checker=""; %>
			<%-- <%if(newDischCardFields.getPast_hist_HTN().equals("")){checker="checked"; }else{checker="";}%>
			 --%>	
			
			<td><input type="button" class='btn lokbtn' value="HTN -"></td><td><input type="checkbox" class='lokchecker' id='past_hist_HTN'  value="<%=newDischCardFields.getPast_hist_HTN() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea'  placeholder="Enter Details" id='past_hist_HTN_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPast_hist_HTN_text() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="CVE -"></td><td><input type="checkbox" class='lokchecker' id='past_hist_CVE'  value="<%=newDischCardFields.getPast_hist_CVE()%>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea'  placeholder="Enter Details "  id='past_hist_CVE_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPast_hist_CVE_text()%></textarea></td>
			
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="DM -"></td><td><input type="checkbox" class='lokchecker' id='past_hist_DM'  value="<%=newDischCardFields.getPast_hist_DM() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea'  placeholder="Enter Details " id='past_hist_DM_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPast_hist_DM_text() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="Br. Asthama -"></td><td><input type="checkbox" class='lokchecker' id='past_hist_br_asthama'  value="<%=newDischCardFields.getPast_hist_br_asthama() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea'  placeholder="Enter Details" id='past_hist_br_asthama_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPast_hist_br_asthama_text() %></textarea></td>
			
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="IHD -"></td><td><input type="checkbox" class='lokchecker' id='past_hist_IHD'  value="<%=newDischCardFields.getPast_hist_IHD() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="2" cols="" placeholder="Enter Details" id='past_hist_IHD_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPast_hist_IHD_text() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="COAD -"></td><td><input type="checkbox" class='lokchecker' id='past_hist_COAD'  value="<%=newDischCardFields.getPast_hist_COAD() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="2" cols="" placeholder="Enter Details" id='past_hist_COAD_text'  onchange="saveDataToNewField(this)"><%=newDischCardFields.getPast_hist_COAD_text() %></textarea></td>
			
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="Thyroid  -"></td><td><input type="checkbox" class='lokchecker' id='past_hist_Thyroid'  value="<%=newDischCardFields.getPast_hist_Thyroid() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="2" cols="" placeholder="Enter Details " id='past_hist_Thyroid_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPast_hist_Thyroid_text() %></textarea></td>
			
		    <td></td>
		    <td><input type="button" class='btn lokbtn' value="Other's -"></td><td><input type="checkbox" class='lokchecker' id='past_hist_Other'  value="<%=newDischCardFields.getPast_hist_Other() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="2" cols="" placeholder="Enter Details " id='past_hist_Other_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPast_hist_Other_text() %></textarea></td>
	
			</tr>
			
			</table>
			</div>
			
			
			
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="form-group lokhaeders">
               <label for="exampleInputName2"><b>PERSONAL HISTORY</b></label>
         </div>
		<div class="form-group <%=newAureusCardAccess %>" style="margin-top:10px;">
		<s:textarea cssClass="form-control" rows="3" maxlength="" name="personalhist" id="personalhistlok"></s:textarea>
				
		</div>
		
		<div class="">
			<table style="width: 100%" class='newloktable'>
					<col width="10%">
					<col width="3%">
					<col width="35%">
					<col width="4%">
					<col width="10%">
					<col width="3%">
					<col width="35%">
			<tr>
			<td><input type="button" class='btn lokbtn' value="Smoking -"></td><td><input type="checkbox" class='lokchecker' id='person_hist_Smoking'  value="<%=newDischCardFields.getPerson_hist_Smoking() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='person_hist_Smoking_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPerson_hist_Smoking_text() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="Bowel/Bladder -"></td><td><input type="checkbox" class='lokchecker' id='person_hist_Bowel_Bladder'  value="<%=newDischCardFields.getPerson_hist_Bowel_Bladder() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='person_hist_Bowel_Bladder_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPerson_hist_Bowel_Bladder_text() %></textarea></td>
			
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="Alchohol -"></td><td><input type="checkbox" class='lokchecker' id='person_hist_Alchohol'  value="<%=newDischCardFields.getPerson_hist_Alchohol() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='person_hist_Alchohol_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPerson_hist_Alchohol_text() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="Sleep -"></td><td><input type="checkbox" class='lokchecker' id='person_hist_Sleep'  value="<%=newDischCardFields.getPerson_hist_Sleep() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details "  id='person_hist_Sleep_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPerson_hist_Sleep_text() %></textarea></td>
			
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="Tobacco -"></td><td><input type="checkbox" class='lokchecker' id='person_hist_Tobacco'  value="<%=newDischCardFields.getPerson_hist_Tobacco() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='person_hist_Tobacco_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPerson_hist_Tobacco_text() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="Other Addt  -"></td><td><input type="checkbox" class='lokchecker' id='person_hist_OtherAddt'  value="<%=newDischCardFields.getPerson_hist_OtherAddt() %>" onclick="checknewDisFields(this)"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='person_hist_OtherAddt_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPerson_hist_OtherAddt_text() %></textarea></td>
			</tr>
			
			
			
			</table>
			</div>
		</div>
		</div>
		
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		
		<%-- <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
		<div class="form-group">
               <label for="exampleInputName2">SUGICAL NOTES</label>
         </div>
		<div class="form-group" style="margin-top:10px;">
			<s:textarea cssClass="form-control" rows="3" maxlength="" name="surgicalnotes" id="surgicalnoteslok"></s:textarea>				</div>
		</div> --%>
	
		
		
		
		
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 hidden">
		<div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>EARLIER INVESTIGATION</b></label>
         </div>
	<div class="form-group" style="margin-top:10px;">
		<s:textarea cssClass="form-control" rows="3" name="earlierinvest" id="earlierinvestlok"/>
	</div>
		</div>
		</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 hidden">
		<div class="form-group lokhaeders">
               <label for="exampleInputName2"><b>TREATMENT HISTORY</b></label>
         </div>
			<div class="form-group" style="margin-top:10px;">
			<s:textarea cssClass="form-control" rows="3" name="suggestedtrtment" id="suggestedtrtmentlok"/>
			</div>
		</div>
		</div>
		
		</div>
		
		</div>
			<div
				class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad <%=hstry%> hidden" onclick="triggerTempdata()">
				<div class="row <%=hstry%>">
					<%
						if (false) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
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
							if (false) {
						%>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
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
							if (false) {
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
							if (false) {
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
								<div id="treatment">
									<s:select list="treatment_given_list"
										onchange="gettreattemplate(this.value)" listKey="id"
										listValue="name" cssClass="form-control" headerKey="0"
										headerValue="Select Template"></s:select>
								</div>		
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
						<%
							if (ipd.getSurgicalnotes() != null) {
						%>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
						<div class="col-lg-11 col-md-11 col-xs-11 col-sm-11 " style="margin-left: 30px;">
							<div class="form-group">
								<label for="exampleInputName2"><b>Surgical Notes</b></label>
								<s:textarea cssClass="form-control" rows="3" cols="5"
									id="surgicalnotes" name="surgicalnotes" />
							</div>
						</div>	
						</div>
						<%
							}
						%>

					</div>
				</div>
				<s:hidden name="clientip" id="clientipdid1" />


			</div>

  <div class="col-lg-12 col-xs-12 col-md-12 textprime hidden <%=pediatric_histry %>">
							<h5>PEDIATRIC HISTORY</h5> 
						</div>
                      
                      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad hidden">
                          <div class=" <%=hstry %>">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                             
             <div class="form-group lokhaeders">
               <label for="exampleInputName2"><b>BIRTH HISTORY</b></label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A narrative or record of past events and circumstances that are or may be relevant to a patient's current state of health. Informally, an account of past diseases, injuries, treatments, and other strictly medical facts"></span></span>
               
             </div>
          
       
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="birthhist" id="birthhist"></s:textarea>
           </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 <%=nicusetting%>">
                           
             <div class="form-group lokhaeders">
               <label for="exampleInputName2"><b>DIET HISTORY</b></label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The family structure and relationships within the family, including information about diseases in family members."></span></span>
              
             </div>
        
       
           
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="diethist" id="diethist"></s:textarea>
           </div>
                            </div>
                           </div>
                          </div>
                          
                          <div class=" <%=hstry %>">
                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 <%=nicusetting%>">
                            
             <div class="form-group lokhaeders">
               <label for="exampleInputName2"><b>DEVELOPMENT HISTORY</b></label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A personal history may include information about allergies, illnesses, surgeries, immunizations, and results of physical exams, tests, and screenings. It may also include information about medicines taken and health habits, such as diet and exercise. Also called personal health record, personal medical history, and PHR."></span></span>
              </div>
        
         
         
           
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="developmenthist" id="developmenthist"></s:textarea>
           </div>
                            </div>
                            
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                              
               <div class="form-group lokhaeders">
                 <label for="exampleInputName2"><b>IMMUNIZATION HISTORY</b></label>&nbsp;&nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">"A history of the surgical procedures—and complications, if any— that a particular person has had"></span></span>
               </div>
             
           <div class="form-group" style="margin-top:10px;">
            <s:textarea cssClass="form-control" rows="3" maxlength="" name="emmunizationhist" id="emmunizationhist"></s:textarea>
           </div>
                            </div>
                           </div>
                          </div>
                          
                         
                       
                       </div>
                       
 <div style="margin-top: 20px;">                      
 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
 <h5>    OBSTRETRICS & GYNECOLOGY  </h5>
 </div>  
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 <table style="width: 100%" class='newloktable'>
<col width="10%">
<col width="35%">
<col width="5%">
<col width="10%">
<col width="35%">
 			<tr>
			<td><input type="button" class='btn lokbtn' value="LMP -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='obng_lmp' onchange="saveDataToNewField(this)"><%=newDischCardFields.getObng_lmp() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="Menstrual History -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='obng_menstrual_hist' onchange="saveDataToNewField(this)"><%=newDischCardFields.getObng_menstrual_hist() %></textarea></td>
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="G P L A -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='obng_gpla' onchange="saveDataToNewField(this)"><%=newDischCardFields.getObng_gpla() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="Tubesctomy -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='obng_tubesctomy' onchange="saveDataToNewField(this)"><%=newDischCardFields.getObng_tubesctomy() %></textarea></td>
			</tr>
			
 
 </table>
 </div>                    
 </div>                      				
  
  
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
<div class="col-lg-12 col-xs-12 col-md-12 textprime">
 <h5>    FAMILY HISTORY  </h5>
 </div>  
			<div class="form-group hidden" style="margin-top:10px;">
			<s:textarea cssClass="form-control" rows="3" maxlength="" name="familyhist" id="familyhistlok"></s:textarea>
			</div>
			
					<div class="">
			<table style="width: 100%" class='newloktable'>
					
			<tr>
			<td><input type="button" class='btn lokbtn' value="Hypertension -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_hypertension'  value="<%=newDischCardFields.getFm_hist_hypertension() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_hypertension_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_hypertension_text() %></textarea></td>
			
			<td><input type="button" class='btn lokbtn' value="Asthma -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_asthma'  value="<%=newDischCardFields.getFm_hist_asthma() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_asthma_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_asthma_text() %></textarea></td>
			
			<td><input type="button" class='btn lokbtn' value="Heart Disease -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_heart_disease'  value="<%=newDischCardFields.getFm_hist_heart_disease() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_heart_disease_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_heart_disease_text() %></textarea></td>
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="Stroke -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_stroke'  value="<%=newDischCardFields.getFm_hist_stroke() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_stroke_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_stroke_text() %></textarea></td>
			
			<td><input type="button" class='btn lokbtn' value="Diabetes -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_diabetes'  value="<%=newDischCardFields.getFm_hist_diabetes() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_heart_disease_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_heart_disease_text() %></textarea></td>
			
			<td><input type="button" class='btn lokbtn' value="Arthiritis / Gout -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_arthritis_gout'  value="<%=newDischCardFields.getFm_hist_arthritis_gout() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_arthritis_gout_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_arthritis_gout_text() %></textarea></td>
			</tr>
			<tr>
			<td><input type="button" class='btn lokbtn' value="Tuberculosis -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_tuberculosis'  value="<%=newDischCardFields.getFm_hist_tuberculosis() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_tuberculosis_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_tuberculosis_text() %></textarea></td>
				
			<td><input type="button" class='btn lokbtn' value="Cancer -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_cancer'  value="<%=newDischCardFields.getFm_hist_cancer() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_cancer_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_cancer_text() %></textarea></td>
			
			<td><input type="button" class='btn lokbtn' value="Epilepsy -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_epilepsy'  value="<%=newDischCardFields.getFm_hist_epilepsy() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_epilepsy_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_epilepsy_text() %></textarea></td>
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="Other Chronic Disease -"></td><td><input type="checkbox" class='lokchecker' id='fm_hist_other_chronic'  value="<%=newDischCardFields.getFm_hist_other_chronic() %>" onclick="checknewDisFields(this)"></td>
			<td class='spc'><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='fm_hist_other_chronic_text' onchange="saveDataToNewField(this)"><%=newDischCardFields.getFm_hist_other_chronic_text() %></textarea></td>
			
			<td></td><td></td><td></td><td></td>
			<td></td><td></td><td></td><td></td>
			</tr>
		
			
			
			</table>
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
						EXAMINATION ON ADMISSION :	<!-- On Examination --> &nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">
									</span></span>
						</h5>
					</div>     
					
					
<!-- Vitals -->	
<div class="col-lg-12 col-xs-12 col-md-12 " >
<br>
<label><b>GENERAL EXAMINATION :</b></label>
</div> 
<div style="margin-top: 20px;">                      
<div class="col-lg-12 col-xs-12 col-md-12 ">
 
<div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>Vitals</b></label>
</div>
</div>  
 
 <%ArrayList<Master> vaitalList=(ArrayList<Master>)request.getAttribute("vitallist"); %>
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 <table style="width: 100%" class='newloktable'>
	<tr>
	<%for(Master vital:vaitalList){ %>
	 	<td><input type="button" class='btn lokbtn' value="<%=vital.getName()%> -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter <%=vital.getName()%>" id='<%=vital.getId()%>' onchange="savevitalsNew(this)"><%=vital.getFinding() %></textarea><%=vital.getUnit() %></td>
	<%} %>			
	</tr>			
 </table>
 </div>                    
 </div> 					
	
	
<!-- Appearance -->	
	 <div style="margin-top: 20px;">                      
 <div class="col-lg-12 col-xs-12 col-md-12 ">
 <div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>Appearance</b></label>
  </div>
 </div>  
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 <table style="width: 100%" class='newloktable'>
<col width="10%">
<col width="35%">
<col width="5%">
<col width="10%">
<col width="35%">
 			<tr>
			<td><input type="button" class='btn lokbtn' value="Pallor -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='apearnace_Pallor' onchange="saveDataToNewField(this)"><%=newDischCardFields.getApearnace_Pallor() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="Clubbing -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='apearnace_Clubbing' onchange="saveDataToNewField(this)"><%=newDischCardFields.getApearnace_Clubbing() %></textarea></td>
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="Cynosis -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='apearnace_Cynosis' onchange="saveDataToNewField(this)"><%=newDischCardFields.getApearnace_Cynosis() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="Icterus -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='apearnace_Icterus' onchange="saveDataToNewField(this)"><%=newDischCardFields.getApearnace_Icterus() %></textarea></td>
			</tr>
			
			<tr>
			<td><input type="button" class='btn lokbtn' value="L.N. -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='apearnace_ln' onchange="saveDataToNewField(this)"><%=newDischCardFields.getApearnace_ln() %></textarea></td>
			<td></td>
			<td><input type="button" class='btn lokbtn' value="Oedema -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='apearnace_Oedema' onchange="saveDataToNewField(this)"><%=newDischCardFields.getApearnace_Oedema() %></textarea></td>
			</tr>
			
 
 </table>
 </div>                    
 </div> 
	
	
	
					                    
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 20px !important;margin-bottom: 20px !important;">
		<div class="form-inline" style="width: 100%">
		<div class="form-group">
		<div class="form-group" style="padding-right: 20px">
               <label for="exampleInputName2"><b><!-- ON EXAMINATION -->LOCAL EXAMINATION</b></label>
               </div>
							               <div class="form-group" id='locexa'>
			            						<s:select list="on_exam_list" onchange="getonexamtemp123(this.value)" 
			            						cssClass="form-control chosen-select" listKey="id" listValue="name" headerKey="0" 
			            						headerValue="Select Template">
											   	</s:select> 
											</div>
											
											<div class="form-group">
								  				<input type="text" name="onexaminationtempname" id='loctempname' class="form-control setbackcolor" placeholder="Enter template name" >  
								  			</div>
				
											<div class="form-group">
								  				<input type="button"  class="btn btn-primary" value="save template " onclick="saveIpdTemplates('6',this)">  
								  			</div>
								  			
               <label>
								   <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'onexaminationlok')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
								 </label>
								 </div>
         </div>
         
			<div class="form-group" style="margin-top:10px;">
					<s:textarea cssClass="form-control" rows="6" maxlength="" name="onexamination" id="onexaminationlok"></s:textarea>
			</div>
			
		</div>
		
		
<!-- Systematic Examination -->	
	 <div style="margin-top: 20px;">                      
 <div class="col-lg-12 col-xs-12 col-md-12 ">
 <div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>Systematic Examination</b></label>
  </div>
 </div>  
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 <table style="width: 100%" class='newloktable'>
<col width="10%">
<col width="90%">
	<tr><td><input type="button" class='btn lokbtn' value="CVS -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='sys_exa_CVS' onchange="saveDataToNewField(this)"><%=newDischCardFields.getSys_exa_CVS() %></textarea></td></tr>
 	<tr><td><input type="button" class='btn lokbtn' value="RS -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='sys_exa_RS' onchange="saveDataToNewField(this)"><%=newDischCardFields.getSys_exa_RS() %></textarea></td></tr>
	<tr><td><input type="button" class='btn lokbtn' value="CNS -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='sys_exa_CNS' onchange="saveDataToNewField(this)"><%=newDischCardFields.getSys_exa_CNS() %></textarea></td></tr>
	<tr><td><input type="button" class='btn lokbtn' value="PA -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='sys_exa_PA' onchange="saveDataToNewField(this)"><%=newDischCardFields.getSys_exa_PA() %></textarea></td></tr>
	<tr><td><input type="button" class='btn lokbtn' value="PV / PR -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='sys_exa_PVPR' onchange="saveDataToNewField(this)"><%=newDischCardFields.getSys_exa_PVPR() %></textarea></td></tr>
	<tr><td><input type="button" class='btn lokbtn' value="Others -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='sys_exa_Others' onchange="saveDataToNewField(this)"><%=newDischCardFields.getSys_exa_Others() %></textarea></td></tr>
</table>
 </div>                    
 </div> 													
		
<!-- OTHERS:   : -->	
 <div style="margin-top: 20px;"> 
 <div class="col-lg-12 col-xs-12 col-md-12 ">
 <div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>OTHERS   :</b></label>
  </div>
 
 <div id=''>
 <span id='general_other_list'><s:select list="commonTemplateList" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template" 
 cssStyle="width:20%" theme="simple" onchange="getTemplateDataByAjax(this.value,'general_other')"></s:select></span> 
 <input type="text" class='form-control' placeholder="Save Template" style="width: 10%" id='general_other_tempname'>
 <input type="button" class='btn btn-primary' value="Save Template" onclick="saveTemplateAjax(this,'general_other')">
 </div>   
  
 </div> 
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 	<s:textarea cssClass="form-control" rows="6" maxlength="" name="newCardFields.general_other" id="general_other" />
 </div>
</div>	



	<div class="col-lg-12 col-xs-12 col-md-12 textprime <%=daycareaccess%>">
		<h5>
			<!-- HOSPITAL COURSE -->COURSE IN HOSPITAL &nbsp;<span class="tooltip"><i
				class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
					continuous progression from one point to the next in time or
					space; onward movement the course of his/her hospital stay</span></span>
		</h5>
	</div>
	
	
	
	
	
	
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=daycareaccess%>">

		<div class="form-inline">
			<div class="form-group" style="width: 100%;">
			<SPAN id='hslist'>
				<s:select list="hospital_course_list" onchange="gethosptemplate(this.value)" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template"></s:select>
			</SPAN>		
					
				<input type="text" name="hospitalcoursetempname"
					class="form-control setbackcolor" id='hospitalcoursetempname'
					placeholder="Enter template name">
					
				
				<input type="button" class='btn btn-primary' value='Save Template' onclick="saveIpdTemplates('9',this)">
					
					 <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'hospitalcourse')" title="Microphone" id="changer"></img>
			</div>
		</div>
		<div class="form-group" style="margin-top: 10px;">
			<s:textarea cssClass="form-control" rows="6" maxlength=""
				name="hospitalcourse" id="hospitalcourse" />
		</div>

	</div>
	
	
	
			<s:if test="dischargeStatus==3">
				<div class="hidden" id="surgicalnotesdiv">

					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
						OPERATION DETAILS	<!-- SURGICAL NOTES --> &nbsp;<span class="tooltip"><i
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
						%>
						<%
							int otid = 0;
						%>
						<s:hidden name="totalotids"></s:hidden>
						<div class="form-group" style="margin-top: 10px;">
							<label for="inputEmail" class="control-label "><b>Operative
								Date</b></label>
							<%
								for (Master master : otdatesandids) {
							%>
							<p><%=master.getDate()%>&nbsp;&nbsp;&nbsp;<b>Procedure:</b><input
									type="text" name="editotprocedure<%=otid%>"
									class="form-control" value="<%=master.getProcedurename()%>">
							</p>
							<%
								otid++;
							%>
							<%
								}
							%>
							
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
							<div class="row <%=daycareaccess%>">

								
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
											<div class="form-group" style="width: 100%;">
												<s:select list="otherTemplateList"
													onchange="getOTSurgicaltemplate(this.value)" listKey="id"
													listValue="name" cssClass="form-control" headerKey="0"
													headerValue="Select Template"></s:select>
												<input type="text" name="operativetempname"
													class="form-control setbackcolor"
													placeholder="Enter template name">
											</div>
											<div class="form-group">
											 <img src="cicon/mic_off.png" class="img-responsive micimg12" onclick="startConverting155(this,'operation_notes')" title="Microphone" id="changer" style="width: 2.5%;margin-left: 10px;margin-top: -16px;"></img>
											</div>
										</div>
										<div class="form-group" style="margin-top: 10px;">
											<s:hidden name="otNotesids"></s:hidden>
											<label for="inputEmail" class="control-label "><b>Operative
												Notes</b></label>
												<label></label>
											<s:textarea cssClass="form-control" rows="6" maxlength=""
												name="otNotes" id="operation_notes" />
										</div>






									</div>

								</div>


							</div>
						</div>

					</div>
					</div>
			</s:if>
			<s:else>
				<div class="<%=daycareaccess%>" id="surgicalnotesdiv">

					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
						OPERATION DETAILS	<!-- SURGICAL NOTES --> &nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">An
									Operative report is a report written in a patient's medical
									record to document the details of a surgery. The operative
									report is dictated right after a surgical procedure and later
									transcribed into the patient's record.</span></span>
						</h5>
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
										<div class="form-group" style="width: 40%;">
											<s:select list="otherTemplateList"
												onchange="getOTSurgicaltemplate(this.value)" listKey="id"
												listValue="name" cssClass="form-control chosen-select" headerKey="0"
												headerValue="Select Template"></s:select>
											<input type="text" name="operativetempname"
												class="form-control setbackcolor"
												placeholder="Enter template name">
										</div>
											 <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'operation_notes')" title="Microphone" id="changer" ></img>
									</div>
									<div class="form-group" style="margin-top: 10px;">
										<s:hidden name="otNotesids"></s:hidden>

										<label for="inputEmail" class="control-label "><b>OPERATIVE NOTES
											</b></label>
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
			

<!-- investigation		 -->
	<div class="col-lg-12 col-xs-12 col-md-12 textprime <%=daycareaccess%>">
						<h5>
							INVESTIGATIONS &nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">Investigation
									during hospital stay(blood/ct/mri etc)</span></span>
						</h5>
	</div>
	
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=daycareaccess%>">
						<div class="form-inline" style="margin-top: 10px;">
						<span id='invstl'>
							<s:select list="investigationList"
								onchange="getInvstemplate(this.value)" listKey="id"
								listValue="name" cssClass="form-control" headerKey="0"
								headerValue="Select Template"></s:select>
						</span>	
							<input type="text" name="investigationtempname" id='investigationtempname'
								class="form-control setbackcolor"
								placeholder="Enter template name">
							<input type="button" class='btn btn-primary' onclick="saveIpdTemplates('',this)" value ='Save Template'>
								
							<div class="form-group" >
							<s:select list="investigationtemplatelist" cssClass="form-control chosen-select" headerKey="" headerValue="Select Template" listKey="id" listValue="name"  onchange="getTabulardata(this.value)"></s:select>
							
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
				
		

	</div>

			<!--  </div>-->
			<s:if test="dischargeStatus==3">
				<div class="" id="deathcausediv">
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

								<s:textarea cssClass="form-control" rows="6" maxlength=""
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
								<s:textarea cssClass="form-control" rows="6" maxlength=""
									name="deathnote" id="death_note" />
							</div>

						</div>
					</div>
				</div>
			</s:else>


			
			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<%-- <h5>TREATMENT GIVEN &nbsp;<span class="tooltip"><i class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">The management and care of a patient,the combating of a disease or disorder called also therapy</span></span></h5> --%>
						<h5>
							TREATMENT GIVEN &nbsp;<a href="#" data-toggle="modal"
								data-target="#treatgiven" class="btn btn-info btn-sm hidden">+</a>
						</h5>
					</div>


					<%-- <a href="#" data-toggle="modal"  onclick="getPriscriptionGiven(<s:property value="id"/>)" class="btn btn-info btn-sm">+</a></h5> --%>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px;">
								<a style="cursor: pointer;margin-bottom: 10px" class="btn btn-info"
								onclick="onAddTreat(<s:property value="clientid"/>,<s:property value="practitionerid"/>,<s:property value="conditionid"/>)">Add</a>
					
					<div class="form-group">
							<table class="table table-bordered" id="priscTabletreat">
								<thead>
									<tr class="headings">
										<th style="width: 5%;">Sr.No</th>
										<th class="uppercaseirf">Medicine1</th>
										<th>Dosage</th>
										<th>Days</th>
										<th>Notes</th>
										<th>Strength</th>
										<!-- <th>Remark</th> -->
										<th>Del</th>
									</tr>
								</thead>
								<tbody id="dischargedataidtreat">
									<s:iterator value="treatmentivendischargePriscListt">
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
											<%-- <td><s:property value="priscindivisualremark" /> </td> --%>
											<td><a
												onclick="removeMedicineDisc1(this,<s:property value="id"/>)"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</s:iterator>
									<s:hidden name="totalchildmedids"></s:hidden>
								</tbody>
							</table>
						</div>
						
						<div id="priscnotestreat">
							<s:property value="advoice" />
						</div>
					</div>
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




<!--   Condition on Discharge ::   : -->	

 <div class=" ">
<div class="col-lg-12 col-xs-12 col-md-12 textprime">
<h5>CONDITION ON DISCHARGE</h5>
</div>

 <div style="margin-top: 20px;"> 
 <div class="col-lg-12 col-xs-12 col-md-12 ">
 <div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>Local /Relevant Area  :(Wound / Dressing)  :</b></label>
  </div>
 
 <div id=''>
 <span id='local_relevant_area_list'><s:select list="commonTemplateList" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template" 
 cssStyle="width:20%" theme="simple" onchange="getTemplateDataByAjax(this.value,'local_relevant_area')"></s:select></span> 
 <input type="text" class='form-control' placeholder="Save Template" style="width: 10%" id='local_relevant_area_tempname'>
 <input type="button" class='btn btn-primary' value="Save Template" onclick="saveTemplateAjax(this,'local_relevant_area')">
 </div>
  
 </div> 
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 	<s:textarea cssClass="form-control" rows="6" maxlength="" name="newCardFields.local_relevant_area" id="local_relevant_area" />
 </div>
</div>


<div style="margin-top: 20px;" class='hidden'> 
 <div class="col-lg-12 col-xs-12 col-md-12 ">
 <div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>Tubes & Drain   :</b></label>
  </div>
  
<div id=''>
 <span id='tubes_and_training_list'><s:select list="commonTemplateList" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template" 
 cssStyle="width:20%" theme="simple" onchange="getTemplateDataByAjax(this.value,'tubes_and_training')"></s:select></span> 
 <input type="text" class='form-control' placeholder="Save Template" style="width: 10%" id='tubes_and_training_tempname'>
 <input type="button" class='btn btn-primary' value="Save Template" onclick="saveTemplateAjax(this,'tubes_and_training')">
 </div>  
  
 </div> 
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 	<s:textarea cssClass="form-control" rows="6" maxlength="" name="newCardFields.tubes_and_training" id="tubes_and_training" />
 </div>
</div>

</div>





					
		
			<s:hidden name="clientid" id="clientnewid"></s:hidden>

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
								class="form-control setbackcolor"
								placeholder="Enter template name">
								
							<input type="button" class="btn btn-primary" value="save template" onclick="saveIpdTemplates('16',this)">
								
						 <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'finddis')" title="Microphone" id="changer" ></img>
						</div>


						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="findondischarge" id="finddis" />
						</div>
					</div>



	




					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							<!-- TREATMENT ADVICE -->DRUG TREATMENT ON DISCHARGE&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									written direction for the preparation, compounding, and
									administration of a medicine i.e. prescribed Medicine</span></span>
						</h5>
					</div>


					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
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
							FOLLOW UP ADVICE&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									Advice on discharge (follow up, dietary, do's and dont's)</span></span>
						</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

						<div class="form-inline">
							<div class="form-group" style="width: 100%;">
							<span  id='followup'>
								<s:select list="nursing_advice_list" listKey="id"
									onchange="getnursingtemplate(this.value)" listValue="name"
									cssClass="form-control" headerKey="0"
									headerValue="Select Template"></s:select>
							</span>		
								<input type="text" name="nursingadvicetempname"
									class="form-control setbackcolor"
									placeholder="Enter template name"> <img
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
						<span id='findingl'>
							<s:select list="finding_on_dischargeList"
								onchange="getFindOnDischtemplate(this.value)" listKey="id"
								listValue="name" cssClass="form-control" headerKey="0"
								headerValue="Select Template"></s:select>
						</span>	
							<input type="text" name="findingondistempname" id='findingondistempname'
								class="form-control setbackcolor"
								placeholder="Enter template name">
								
							<input type="button" class="btn btn-primary" value="save template" onclick="saveIpdTemplates('16',this)">
								<img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'finddis')" title="Microphone" id="changer" ></img>
						
						
						</div>


						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="findondischarge" id="finddis" />
						</div>
					</div>
</div>

<!--General Condition :     -->
 <div style="margin-top: 20px;">                      
 <div class="col-lg-12 col-xs-12 col-md-12 ">
 <div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>General Condition</b></label>
  </div>
 </div>  
 
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 <table style="width: 100%" class='newloktable'>
 <col width="10%">
<col width="20%"> 
<col width="5%">
<col width="10%"> 
<col width="20%">
<col width="5%"> 
<col width="10%">
<col width="20%"> 

	<tr><td><input type="button" class='btn lokbtn' value="Temp -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='gen_cond_Temp' onchange="saveDataToNewField(this)"><%=newDischCardFields.getGen_cond_Temp() %></textarea></td><td></td>
 	<td><input type="button" class='btn lokbtn' value="Pulse -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='gen_cond_Pulse' onchange="saveDataToNewField(this)"><%=newDischCardFields.getGen_cond_Pulse() %></textarea></td><td></td>
	<td><input type="button" class='btn lokbtn' value="BP -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='gen_cond_BP' onchange="saveDataToNewField(this)"><%=newDischCardFields.getGen_cond_BP() %></textarea></td></tr>
	
	<tr><td><input type="button" class='btn lokbtn' value="CVS -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='gen_cond_CVS' onchange="saveDataToNewField(this)"><%=newDischCardFields.getGen_cond_CVS() %></textarea></td><td></td>
	<td><input type="button" class='btn lokbtn' value="PS -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='gen_cond_PS' onchange="saveDataToNewField(this)"><%=newDischCardFields.getGen_cond_PS() %></textarea></td><td></td>
	<td><input type="button" class='btn lokbtn' value="CNS -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='gen_cond_CNS' onchange="saveDataToNewField(this)"><%=newDischCardFields.getGen_cond_CNS() %></textarea></td></tr>
</table>
 </div>                    
 </div> 


<!--Lines /Tubes/Drains :   : -->	
 <div style="margin-top: 20px;"> 
 <div class="col-lg-12 col-xs-12 col-md-12 ">
 <div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>Lines /Tubes/Drains    :</b></label>
  </div>
  
<div id=''>
 <span id='line_tube_drains_list'><s:select list="commonTemplateList" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template" 
 cssStyle="width:20%" theme="simple" onchange="getTemplateDataByAjax(this.value,'line_tube_drains')"></s:select></span> 
 <input type="text" class='form-control' placeholder="Save Template" style="width: 10%" id='line_tube_drains_tempname'>
 <input type="button" class='btn btn-primary' value="Save Template" onclick="saveTemplateAjax(this,'line_tube_drains')">
 </div>    
  
  
 </div> 
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 	<s:textarea cssClass="form-control" rows="6" maxlength="" name="newCardFields.line_tube_drains" id="line_tube_drains" />
 </div>
</div>



					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>DRUG TREATMENT ON DISCHARGE<!-- TREATMENT ADVICE -->&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									written direction for the preparation, compounding, and
									administration of a medicine i.e. prescribed Medicine</span></span>
						</h5>
					</div>


					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
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
						<div class="form-group " style="margin-top:30px;" >
					<div class="form-inline">
									<div class="form-group">
								          <label for="exampleInputName2"><b><!-- PRISCRIPTION TEXT -->TRATMENT CARE ON DISCHARGE</b></label>
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




					
			</s:else>
			

<!-- Dietary Advice:   : -->	
 <div style="margin-top: 20px;"> 
 <div class="col-lg-12 col-xs-12 col-md-12 ">
 <div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>Dietary Advice:   :</b></label>
  </div>
 
 <div id=''>
 <span id='dietary_advice_list'><s:select list="commonTemplateList" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template" 
 cssStyle="width:20%" theme="simple" onchange="getTemplateDataByAjax(this.value,'dietary_advice')"></s:select></span> 
 <input type="text" class='form-control' placeholder="Save Template" style="width: 10%" id='dietary_advice_tempname'>
 <input type="button" class='btn btn-primary' value="Save Template" onclick="saveTemplateAjax(this,'dietary_advice')">
 </div>    
  
  
  
 </div> 
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 	<s:textarea cssClass="form-control" rows="6" maxlength="" name="newCardFields.dietary_advice" id="dietary_advice" />
 </div>
</div>


<!-- Physiotherapy Advice: -->	
	 <div style="margin-top: 20px;">                      
 <div class="col-lg-12 col-xs-12 col-md-12 ">
 <div class="form-groupb lokhaeders">
               <label for="exampleInputName2"><b>Physiotherapy Advice:</b></label>
  </div>
 </div>  
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 <table style="width: 100%" class='newloktable'>
<col width="10%">
<col width="90%">
	<tr><td><input type="button" class='btn lokbtn' value="Mobilization -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='physio_th_adv_Mobilization' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPhysio_th_adv_Mobilization() %></textarea></td></tr>
 	<tr><td><input type="button" class='btn lokbtn' value="Fall Risk -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='physio_th_adv_fallRisk' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPhysio_th_adv_fallRisk() %></textarea></td></tr>
	<tr><td><input type="button" class='btn lokbtn' value="Driving -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details" id='physio_th_adv_Driving' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPhysio_th_adv_Driving() %></textarea></td></tr>
	<tr><td><input type="button" class='btn lokbtn' value="Sexual Activity -"></td><td><textarea class='form-control loktextarea' rows="3" cols="" placeholder="Enter Details " id='physio_th_adv_sexual_Activity' onchange="saveDataToNewField(this)"><%=newDischCardFields.getPhysio_th_adv_sexual_Activity() %></textarea></td></tr>
	
	</table>
 </div>                    
 </div> 													
		


					<br>

		<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
						<div class="form-group lokhaeders">
						<br>
               <label for="exampleInputName2"><b>Other Advices :</b></label>
         </div>
							<div class="form-inline">
								<div class="form-group" style="width: 100%;">
								<span id='notesl'>
									<s:select list="discharge_default_list"
										onchange="getdisctemplate(this.value)" listKey="id"
										listValue="name" cssClass="form-control" headerKey="0"
										headerValue="Select Template"></s:select>
								</span>		
									<input type="text" name="disdefaulttempname" id='disdefaulttempname'
										class="form-control setbackcolor"
										placeholder="Enter template name"> 
									
									<input type="button" class='btn btn-primary' value='Save Template' onclick="saveIpdTemplates('8',this)">	
										
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


			
			
			
			
			<div class="">
					<div class="col-lg-12 col-xs-12 col-md-12 ">
						<div class="form-group lokhaeders">
               				<label for="exampleInputName2"><b>How to get Emergency Help :                  </b></label>
 					 </div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline">
							<div class="form-group" style="width: 100%;">
							<span id="emem">
								<s:select list="emergencydetailslist" listKey="id" headerKey="0" headerValue="Select template"
									onchange="getemergencytemplate(this.value)" listValue="name"
									cssClass="form-control" ></s:select>
							</span>		
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
			
		<!-- When to get Emergency Help :                 : -->	
 <div style="margin-top: 20px;"> 
 <div class="col-lg-12 col-xs-12 col-md-12  ">
 <div class="form-group lokhaeders">
               <label for="exampleInputName2"><b>When to get Emergency Help :                  </b></label>
  </div>
  
 <div id=''>
 <span id='when_to_get_help_list'><s:select list="commonTemplateList" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template" 
 cssStyle="width:20%" theme="simple" onchange="getTemplateDataByAjax(this.value,'when_to_get_help')"></s:select></span> 
 <input type="text" class='form-control' placeholder="Save Template" style="width: 10%" id='when_to_get_help_tempname'>
 <input type="button" class='btn btn-primary' value="Save Template" onclick="saveTemplateAjax(this,'when_to_get_help')">
 </div>    
    
  
 </div> 
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 	<s:textarea cssClass="form-control" rows="6" maxlength="" name="newCardFields.when_to_get_help" id="when_to_get_help" />
 </div>
</div>	
		
			<div class="">
			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>
							FOLLOW UP ADVICE&nbsp;<span class="tooltip"><i
								class="fa fa-info-circle fa-2x"></i><span class="tooltiptext">A
									Advice on discharge (follow up, dietary, do's and dont's)</span></span>
						</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

						<div class="form-inline">
							<div class="form-group" style="width: 100%;">
							<span id ='disadvl'>
								<s:select list="nursing_advice_list" listKey="id"
									onchange="getnursingtemplate(this.value)" listValue="name"
									cssClass="form-control" headerKey="0"
									headerValue="Select Template"  style="width: 20%;"></s:select>
							</span>	
							<span>	
								<input type="text" name="nursingadvicetempname" id='nursingadvicetempname'
									class="form-control "
									placeholder="Enter template name">
							</span>
							<span>	
							<input type="button" class="btn btn-primary" value="Save Template" onclick="saveIpdTemplates('10',this)">
							</span>		
								<label>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Follow up date:</label>	<input type="text" placeholder="followup date" name="followupdate1" id='followupdate1' class='form-control' onchange="giveIpdFollowup(this.value)">
								<label>&nbsp;&nbsp;&nbsp;&nbsp; Book Follow Up Apmt  &nbsp;&nbsp;</label><input type="checkbox" name='bkapmtipd' id='bkapmtipd'>
									 <img src="cicon/mic_off.png" class="img-responsive micimg" onclick="startConverting155(this,'discadvnotes')" title="Microphone" id="changer" ></img>
									
							</div>


						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="discadvnotes" id="discadvnotes" />
						</div>
					</div>

				</div>
				
			<!--CAll For Apmt :                 : -->	
 <div style="margin-top: 20px;"> 
 <div class="col-lg-12 col-xs-12 col-md-12  ">
 <div class="form-group lokhaeders">
               <label for="exampleInputName2"><b>Call for Appointment :                  </b></label>
  </div>
  
 <div id=''>
 <span id='call_for_appointmant_list'><s:select list="commonTemplateList" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template" 
 cssStyle="width:20%" theme="simple" onchange="getTemplateDataByAjax(this.value,'call_for_appointmant')"></s:select></span> 
 <input type="text" class='form-control' placeholder="Save Template" style="width: 10%" id='call_for_appointmant_tempname'>
 <input type="button" class='btn btn-primary' value="Save Template" onclick="saveTemplateAjax(this,'call_for_appointmant')">
 </div>    
    
  
 </div> 
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 	<s:textarea cssClass="form-control" rows="6" maxlength="" name="newCardFields.call_for_appointmant" id="call_for_appointmant" />
 </div>
</div>	


		<!-- Consent Signature :                 : -->	
 <div style="margin-top: 20px;"> 
 <div class="col-lg-12 col-xs-12 col-md-12  ">
 <div class="form-group lokhaeders">
               <label for="exampleInputName2"><b>Consent Signature :                  </b></label>
  </div>
  
 <div id=''>
 <span id='consent_sign_list'><s:select list="commonTemplateList" listKey="id" listValue="name" cssClass="form-control" headerKey="0" headerValue="Select Template" 
 cssStyle="width:20%" theme="simple" onchange="getTemplateDataByAjax(this.value,'consent_sign')"></s:select></span> 
 <input type="text" class='form-control' placeholder="Save Template" style="width: 10%" id='consent_sign_tempname'>
 <input type="button" class='btn btn-primary' value="Save Template" onclick="saveTemplateAjax(this,'consent_sign')">
 </div>    
    
  
 </div> 
 <div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 20px;">
 	<s:textarea cssClass="form-control" rows="6" maxlength="" name="newCardFields.consent_sign" id="consent_sign" />
 </div>
</div>	
			<div class="text-right">
				<input type="submit" class="btn btn-primary savebtn savebigbtn"
					value="Save">
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
				

			</div>

		</div>





	</s:form>

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


					<%@ include file="/diarymanagement/pages/addpriscription.jsp"%>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						onclick="saveTemplae(0)">Save Template</button>
					<button type="button" class="btn btn-primary"
						onclick="insertDischargePriscription(0)" id="prescs_save_btn">Save</button>
					<button type="button" class="btn btn-primary"
						onclick="insertDischargePriscription(1)" id="prescs_save_print_btn">Save & Print</button>

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


	<!-- create Prescription -->

	<script>
	var patientId = 0;
	var diaryuserId = 0;
	var editcondition_id = 0;
	function onAdd(cid,pid,conid){
		patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
		treatmentadvc=0;
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





