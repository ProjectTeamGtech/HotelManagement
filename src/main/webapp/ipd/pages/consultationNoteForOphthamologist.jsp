<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
%>
<%
	String kunalacces = "";
	String aureusacces = "";
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	if (loginInfo.getIskunal() == 1) {
		kunalacces = "hidden";
	}
	if (loginInfo.getClinicUserid().equals("aureus")) {
		aureusacces = "hidden";
	}
	String kunalneg = "";
	String nicusetting = "";

	if (loginInfo.getIskunal() == 0) {
		kunalneg = "hidden";
	}

	String islamadama = "none";
	String dischargeStatus = DateTimeUtils.isNull((String) request.getAttribute("dischargeStatus"));
	if (dischargeStatus.equals("6") || dischargeStatus.equals("7")) {
		islamadama = "block";
	}
%>

<s:if test="nicuaccess">
	<%
		nicusetting = "hidden";
	%>
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

<script type="text/javascript" src="ipd/js/admissionform.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="ipd/js/consultationforms.js"></script>


<script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="ipd/js/dischargeform.js"></script>

<script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>
<script type="text/javascript" src="diarymanagement/js/otdata.js"></script>
<script type="text/javascript" src="emr/js/emrNew.js"></script>
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

</script>
<style>
.nicEdit-panelContain {
	background-color: #9fcdd0 !important;
}

.nicEdit-main {
	background: #e8fbff;
}

.medname {
	font-size: 13px !important;
	font-weight: bold !important;
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

.lkclass {
	width: 50%;
}

.lkclass th {
	text-align: center !important;
}

.lkclass td {
	text-align: center !important;
}
</style>

<script>
$(document).ready(function() {
    
	$("#fromDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange : yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange : yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

});

function printExcel() {

	$(".xlstable").table2excel({
		exclude : ".noExl",
		name : "PRO Patients",
		filename : "PROPatientsReport",
		fileext : ".xls",
		exclude_img : true,
		exclude_links : true,
		exclude_inputs : true
	});
}

bkLib.onDomLoaded(function() {
    
	if(document.getElementById("chiefcomplains1")){
		new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('chiefcomplains1'); 
	}
	if(document.getElementById("eyereading")){
		new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 100}).panelInstance('eyereading'); 
	}
	if(document.getElementById('investigation')){
		   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 50}).panelInstance('investigation');

	}
	if(document.getElementById('consNote')){
		   new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('consNote');

	}
	if(document.getElementById('kunal_manual_medicine_text')){
		    new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 100}).panelInstance('kunal_manual_medicine_text');
    }
	if(document.getElementById("discadvnotes")){
		  new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 50}).panelInstance('discadvnotes');
    } 
	if(document.getElementById('history')){
	    	new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 150}).panelInstance('history');
	}
	
});

</script>


</head>
<body>

	<s:form action="saveOphtoconsultationFormCommonnew" theme="simple">
		<s:hidden name="clientid" id="clientid"></s:hidden>
		<s:hidden name="apmtId" id="apmtId"></s:hidden>
		<s:hidden name="practitionerid" id="practitionerId"></s:hidden>
		<s:hidden name="thirdParty" />
		<s:hidden name="whopay"></s:hidden>
		<%
			if (loginInfo.isSaimed() || loginInfo.isLmh() || loginInfo.isSimpliclinic()) {
		%>
		<s:hidden id="consulationForm"></s:hidden>
		<%
			}
		%>

		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 textprimegreen">
				<div class="col-lg-6 col-xs-6 col-md-6 text-left"
					style="padding-left: 0px;">
					<h3>Patient Consultation Form</h3>
				</div>
				<div class="col-lg-6 col-xs-6 col-md-6 text-right"
					style="padding-right: 0px;">
					<h3></h3>
				</div>
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
				<h5>PATIENT DETAILS</h5>
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12"
				style="padding: 0px; background-color: rgba(245, 245, 245, 0.95);">
				<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
					<div class="form-group">
						<label for="exampleInputEmail1" onclick="">Patient Name</label><br>
						<p>
							<%if( loginInfo.isSjivh()) {%>
					        <s:property value="Middlename" />
					<%}else{ %>
						    <s:property value="client" />
					<%} %>
							<br>
							<s:property value="address" />
							<br>
							<s:property value="contact" />
						</p>
					</div>
				</div>
				<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
					<div class="form-group">
						<label for="inputEmail" class="control-label">Primary
							Consultant</label>
						<p>
							<s:property value="doctor_name" />
							<br>
							<s:property value="discipline" />
							<br>
						</p>
						<input type="hidden" name="department"
							value="<s:property value="discipline"/>">
					</div>
				</div>
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
					</div>
				</div>
				<div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
					<div class="form-group">
						<label for="exampleInputEmail1">OPD ID</label>
						<p id="ipdseqno">
							<%
								if (loginInfo.getIpd_abbr_access() == 1) {
							%>
							<s:property value="newipdabbr" />
							<%
								} else {
							%>
							<s:property value="ipdseqno" />
							<%
								}
							%>
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
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
				<div class="col-lg-12 col-xs-12 col-md-12 textprime"
					style="margin-bottom: 5px;">
					<h5>Ophthamologist</h5>
				</div>
			
			 
			  <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12"
					style="margin-bottom: 15px;">
					<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
						<label>L.Spl RFI</label> <input type="text"
							value="<s:property value="LSplRFI"/>" id="LSplRFI" name="LSplRFI"
							class="form-control" style="border: #c1bfbf solid 1px;">
					</div>
					<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
						<label>L.Cyl RFI</label> <input value="<s:property value="LCylRFI"/>"
							id="LCylRFI" name="LCylRFI" type="text" class="form-control"
							style="border: #c1bfbf solid 1px;">
					</div>
					<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
						<label>L.Axis RFI</label> <input value="<s:property value="LAxisRFI"/>"
							type="text" id="LAxisRFI" name="LAxisRFI" class="form-control"
							style="border: #c1bfbf solid 1px;">
					</div>
					<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
						<label>R.Spl RFI</label> <input value="<s:property value="RSplRFI"/>"
							type="text" id="RSplRFI" name="RSplRFI" class="form-control"
							style="border: #c1bfbf solid 1px;">
					</div>
					<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
						<label>R.Cyl RFI</label> <input
							value="<s:property value="RCylRFI"/>" type="text"
							id="RCylRFI" name="RCylRFI" class="form-control"
							style="border: #c1bfbf solid 1px;">
					</div>
					<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
						<label>R.Axis RFI</label> <input value="<s:property value="RAxisRFI"/>"
							 type="text" id="RAxisRFI" name="RAxisRFI"
							class="form-control" style="border: #c1bfbf solid 1px;">
					</div>
					
					
				</div>
				<div class="col-lg-12 col-xs-12 col-md-12 ">
				 <div class="col-lg-5 col-xs-5 col-md-5">
			  <div class="col-lg-12 col-xs-12 col-md-12 textprime"
					style="margin-bottom: 5px;">
					<label>History Dates</label>
				
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="margin-bottom: 15px;">
					<div class="form-inline">
						<div class="form-group">
						<%int i=1; %>
			  	<s:iterator value="Consulthistorylist">
			  	        <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			  	        
			  
			     	<label id="datebtn<s:property value="id"/>" onclick="openconsultation('<s:property value="Datetime"/>','<s:property value='clientid'/>','<s:property value='Appointmentid'/>')"><s:property value="Datetime"/></label>
			  	     			  	     <input type="button" value="Edit" class='btn btn-primary' onclick="openPopup('consultationNotesCommonnew?clientId=<s:property value='clientid'/>&apmtId=<s:property value='Appointmentid'/>')">
			  	     			  	     <input type="button" value="Print" class='btn btn-primary' onclick="opencPopup('printophtomoConsultationFormCommonnew?clientId=<s:property value='clientid'/>&apmtId=<s:property value='Appointmentid'/>')">
			  	     
			  
			  	      <label>	<b><s:property value="Practitionername" /></b></label>
			  	       </div>
					  	<div class="container " id="consulthist<s:property value="id"/>" style="display: none;" >
				
					  </div>
					  <br>
					  <%i++; %>
					</s:iterator></div>
					</div>
					</div>
					
					</div>
			
		  	     <div class="col-lg-7 col-xs-7 col-md-7">		
				<div class="col-lg-12 col-xs-12 col-md-12 textprime"
					style="margin-bottom: 5px;">
					<h5>PRESENT COMPLAINTS</h5>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="margin-bottom: 15px;">
					<div class="form-inline">
						<div class="form-group">
							<s:select cssClass="form-control chosen-select"
								list="chief_complaints_list" listKey="id" listValue="name"
								onchange="setConsultantFormChiefComplaints(this.value)"
								headerKey="0" headerValue="Select Template">
							</s:select>
						</div>
						<div class="form-group">
							<select class="form-control"
								onchange="setConsultantChiefComp(this.value)">
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
							<select class="form-control"
								onchange="setConsultantChiefComp(this.value)">
								<option>Days</option>
								<option>Month</option>
								<option>Year</option>
							</select>
						</div>
						<div class="form-group">
							<input type="text" name="chiefcomplatetempname"
								class="form-control setbackcolor"
								placeholder="Enter template Name">
						</div>
						<div class="form-group">
							<img src="cicon/mic_off.png" class="img-responsive micimg12"
								onclick="startConverting155(this,'chiefcomplains1')"
								title="Microphone" id="changer"
								style="width: 2.5%; margin-left: 10px; margin-top: -16px;"></img>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<s:textarea cssClass="form-control" rows="6" name="chiefcomplains"
							id="chiefcomplains1" />
					</div>
				</div>
				
				
						<div class="row">
			<%if(loginInfo.isSaimed()){ %>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-12 col-xs-12 col-md-12 textprime"
						style="margin-bottom: 15px;">
						<h5>HISTORY</h5>
					</div>


					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

						<div class="form-inline">
							<div class="form-group" style="width: 100%;">
								<s:select list="Past_history_list"
									onchange="getHistemplateCF(this.value)" listKey="id"
									listValue="name" cssClass="form-control" headerKey="0"
									headerValue="Select Template"></s:select>
								<input type="text" name="pasthistory"
									class="form-control setbackcolor"
									placeholder="Enter template name">

								<div class="form-group">
									<img src="cicon/mic_off.png" class="img-responsive micimg12"
										onclick="startConverting155(this,'history')"
										title="Microphone" id="changer"
										style="width: 2.5%; margin-left: 10px; margin-top: -16px;"></img>
								</div>
							</div>
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="examplehist" id="history" />
						</div>
					</div>

				</div>
				<%} %>
			</div>
			   
			   <div class="col-lg-12 col-xs-12 col-md-12 textprime">
					<h5>DIAGNOSIS</h5>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="padding-bottom: 15px;">
					<table class="table">

						<tbody>
							<tr>
								<td><textarea onkeyup="searchdiagnosisJSONCF(this.value)"
										class="form-control setbackcolor" id="newdiagnosis"
										placeholder="type diagnosis here"
										style="height: 30px !important"></textarea></td>
								<td><input type="button" value="Save as new"
									onclick="savediagnosisfastJSONCF()" class="btn btn-info" /></td>
							</tr>
							<tr>
								<td width="50%;">
									<%
										ArrayList<Bed> ipdConditionids = (ArrayList<Bed>) session.getAttribute("finalConditions");
									%>
									
									<%if(loginInfo.isSaimed()){ %>
									<table id="searchDiagnosis" class="table"
										style="height: 100px; display: block; overflow: scroll; width: 100%"; >
										<%}else{ %>
										<table id="searchDiagnosis" class="table"
										style="height: 200px; display: block; overflow: scroll; width: 100%"; >
										<%} %>
										<%
											int k= 0;
										%>
										<%
											for (Bed bed : ipdConditionids) {
										%>
										<tr>
										
											<td><input type="checkbox" class="classD"
												onclick="reoveThisRowCF(this,'<%=bed.getConditionid()%>')"
												value="<%=bed.getConditionid()%>" checked='checked' /> <input
												type='hidden' value="<%=bed.getConditionid()%>"
												name='conditions[<%=k%>].conditionid' /></td>
											<td><%=bed.getConditionname()%></td>
											<td class="hidden"><a
												onclick="reoveThisRowCF('<%=bed.getConditionid()%>')"><i
													class='fa fa-trash-o'></i></a></td>
										</tr>
										<%
											k++;
										%>
										<%
											}
										%>
										
									<%if(loginInfo.isSaimed()){ %>
									</table>
								   <%} else{%>
								   </table>
								   <%} %>
							</td>
					</tr>
					</tbody>
					
					</table>
					
				</div>
			   
			   
			    <div class="col-lg-12 col-xs-12 col-md-12 textprime">
					<h5>INVESTIGATIONS</h5>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div class="form-inline" style="margin-top: 10px;">
						<s:select list="investigationList"
							onchange="getCFInvstemplate(this.value)" listKey="id"
							listValue="name" cssClass="form-control" headerKey="0"
							headerValue="Select Template"></s:select>
						<input type="text" name="investigationtempname"
							class="form-control setbackcolor"
							placeholder="Enter template name">

						<div class="form-group">
							<s:select list="investigationtemplatelist"
								cssClass="form-control chosen-select" headerKey=""
								headerValue="Select Template" listKey="id" listValue="name"
								onchange="getCFTabulardata(this.value)"></s:select>
						</div>
						<div class="form-group hidden">
							<input type="button" class='btn btn-success'
								value="Include Investigations" onclick="entrInvestigation()">
						</div>
						<div class="form-group">
							<input type="button" class='btn btn-success'
								value="Add Investigations"
								onclick="showCFInvestigation(<s:property value='clientid'/>,'<s:property value='client'/>',<s:property value='clientid'/>)">
						</div>
						<img src="cicon/mic_off.png" class="img-responsive micimg"
							onclick="startConverting155(this,'investigation')"
							title="Microphone" id="changer"></img>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<s:textarea cssClass="form-control" rows="6" maxlength=""
							name="investigation" id="investigation" />
					</div>
				</div>
				
						<div class="">
					<div class="col-lg-12 col-xs-12 col-md-12 textprime">
						<h5>ADVICE MEDICINE</h5>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-inline"
							style="margin-bottom: 5px; margin-top: 5px;">
							<a style="cursor: pointer" class="btn btn-info"
								onclick="onAdd(<s:property value="clientid"/>,<s:property value="practitionerid"/>,<s:property value="conditionid"/>)">Add
								Medicine</a>
						</div>
						<%if(loginInfo.isSaimed()){ %>
					<div class="form-group" style="margin-top: 10px;">
						<s:textarea cssClass="form-control" rows="6" maxlength=""
							name="consNote" id="consNote" />
					</div><%}else{ %>
						<div class="form-group">
							<table class="table table-bordered" id="priscTable">
								<thead>
									<tr class="headings">
										<td style="width: 5%;">Sr.No</td>
										<td>Medicine</td>
										<td style="width: 15%;">Frequency/Dosage</td>
										<td style="width: 5%;">Days</td>
										<td>Frequency Notes</td>
										<td style="width: 10%;">Strength</td>
										<td style="width: 14%;">Routes</td>
										<td style="width: 6%;">Quantity</td>
										<td></td>
									</tr>
								</thead>
								<tbody id="dischargedataid">
									<s:iterator value="dischargePriscList">
										<tr>
											<td><input type="number" class="form-control"
												name="dicpriscmed<s:property value="id"/>"
												value="<s:property value="dispriscsrno"/>"
												onchange="changeCFSrNo(this.value,<s:property value="id"/>,'sqno');">
											</td>
											<td class="uppercaseirf medname"><s:property
													value="mdicinenametxt" /></td>
											<td><select id="discpriscdose<s:property value="id"/>"
												name="discpriscdose<s:property value="id"/>"
												class="form-control chosen-select"
												onchange="changeCFSrNo(this.value,<s:property value="id"/>,'dose');">
													<s:iterator value="dosageList">
														<s:if test="name==priscdose">
															<option value="<s:property value="name" />"
																selected="selected"><s:property value="name" /></option>
														</s:if>
														<s:else>
															<option value="<s:property value="name" />"><s:property
																	value="name" /></option>
														</s:else>
													</s:iterator>
											</select></td>
											<td><input type="number" class="form-control"
												name="dicpriscdays<s:property value="id"/>"
												id="dicpriscdays<s:property value="id"/>"
												value="<s:property value="priscdays"/>"
												onchange="changeCFSrNo(this.value,<s:property value="id"/>,'days');">
											</td>
											<td>
												<%-- <s:property value="prisctimename"/> --%> <select
												id="doseFN<s:property value="id"/>"
												name="dosenotes<s:property value="id"/>"
												class="form-control chosen-select"
												onchange="changeCFSrNo(this.value,<s:property value="id"/>,'prisctimename');">
													<s:if test="prisctimename=='' || prisctimename==0">
														<option value="0">Frequency Note</option>
													</s:if>
													<s:else>
														<option value="<s:property value="prisctimename" />"
															selected="selected"><s:property
																value="prisctimename" /></option>
													</s:else>


													<s:iterator value="medicinetimelist">
														<s:if test="priscriptiontime==prisctimename">
															<option value="<s:property value="priscriptiontime" />"
																selected="selected"><s:property
																	value="priscriptiontime" /></option>
														</s:if>
														<s:else>
															<option value="<s:property value="priscriptiontime" />"><s:property
																	value="priscriptiontime" /></option>
														</s:else>
													</s:iterator>
											</select>
											</td>
											<td><input type="number" class="form-control"
												name="dicpriscunit<s:property value="id"/>"
												value="<s:property value="strength"/>"
												onchange="changeCFSrNo(this.value,<s:property value="id"/>,'unit');">
												<br> <select
												id="dicpriscunitextenstion<s:property value="id"/>"
												name="dicpriscunitextenstion<s:property value="id"/>"
												class="form-control chosen-select"
												onchange="changeCFSrNo(this.value,<s:property value="id"/>,'unitextension');">
													<s:if test="unitextension=='' || unitextension==0">
														<option value="0">Select Type</option>
													</s:if>
													<s:else>
														<option value="<s:property value="unitextension" />"
															selected="selected"><s:property
																value="unitextension" /></option>
													</s:else>


													<s:iterator value="priscUnitList">
														<s:if test="name==unitextension">
															<option value="<s:property value="name" />"
																selected="selected"><s:property value="name" /></option>
														</s:if>
														<s:else>
															<option value="<s:property value="name" />"><s:property
																	value="name" /></option>
														</s:else>
													</s:iterator>
											</select> <%-- <s:property value="unitextension" /> --%></td>
											<td>
												<%-- <s:property value="prisctimename" /> --%> <select
												id="doseSR<s:property value="id"/>"
												name="dosenotes<s:property value="id"/>"
												class="form-control chosen-select"
												onchange="changeCFSrNo(this.value,<s:property value="id"/>,'dosenotes');">
													<option value="">Select Routes</option>

													<s:iterator value="dosagenoteList">
														<s:if test="name==dosenotes">
															<option value="<s:property value="name" />"
																selected="selected"><s:property value="name" /></option>
														</s:if>
														<s:else>
															<option value="<s:property value="name" />"><s:property
																	value="name" /></option>
														</s:else>
													</s:iterator>
											</select>

											</td>
											<td><input type="number" class="form-control"
												name="dicpriscdrqty<s:property value="id"/>"
												id="dicpriscdrqty<s:property value="id"/>"
												value="<s:property value="dr_qty"/>"
												onchange="changeCFSrNo(this.value,<s:property value="id"/>,'dr_qty');">
											</td>
											<td><a
												onclick="removeMedicineDiscCF(this,<s:property value="id"/>)"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</s:iterator>
									<s:hidden name="totalchildmedids"></s:hidden>
								</tbody>
							</table>
						</div>
						<%} %>
						<div id="priscnotes">
							<s:property value="advoice" />
						</div>
					<%if(!loginInfo.isSimpliclinic() && !loginInfo.isTotehosp()){ %>	
						<div class="form-group " style="margin-top: 30px;">
							<div class="form-inline">
								<div class="form-group">
									<label for="exampleInputName2"><b>PRESCRIPTION TEXT</b></label>
								</div>
								<div class="form-group">
									<s:select list="discharge_default_list"
										onchange="getCFkunalmedtext(this.value)" listKey="id"
										listValue="name" cssClass="form-control" headerKey="0"
										headerValue="Select Template"></s:select>
								</div>
								<div class="form-group">
									<img src="cicon/mic_off.png" class="img-responsive micimg"
										onclick="startConverting155(this,'kunal_manual_medicine_text')"
										title="Microphone" id="changer" style="width: 25% !important"></img>
								</div>
							</div>
							<s:textarea cssClass="form-control" rows="9" maxlength=""
								name="kunal_manual_medicine_text"
								id="kunal_manual_medicine_text"></s:textarea>
						</div>
						<%} %>
					</div>
					<br>
				</div>
				
								<div class="">
					<div class="col-lg-12 col-xs-12 col-md-12 textprime"
						style="margin-bottom: 15px;">
						<h5>ADVICE/FOLLOW UP</h5>
					</div>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

						<div class="form-inline">
							<div class="form-group" style="width: 100%;">
								<s:select list="nursing_advice_list" listKey="id"
									onchange="getnursingtemplateCF(this.value)" listValue="name"
									cssClass="form-control" headerKey="0"
									headerValue="Select Template">
								</s:select>
								<input type="text" name="nursingadvicetempname"
									class="form-control setbackcolor"
									placeholder="Enter template name"> <label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Follow up
									date:</label> <input type="checkbox"
									onchange="setFollowUpDate(this.checked)" name='giveFollowUp'
									id='giveFollowUp'> <input type="text"
									readonly="readonly" placeholder="followup date"
									name="followupdate1" id='followupdate1' class='form-control'
									onchange="giveCFFollowup(this.value)"> <label class="">&nbsp;&nbsp;&nbsp;&nbsp;Book
									Follow Up Apmt &nbsp;&nbsp;</label> <input type="checkbox"
									name='bkapmtipd' id='bkapmtipd'> <img
									src="cicon/mic_off.png" class="img-responsive micimg"
									onclick="startConverting155(this,'discadvnotes')"
									title="Microphone" id="changer"></img>

							</div>
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="discadvnotes" id="discadvnotes" />
						</div>
					</div>

				</div>
				
				
			   <div class="col-lg-12 col-xs-12 col-md-12 textprime"
					style="margin-bottom: 5px;">
					<h5> Eye Readings</h5>
				</div>
			       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="margin-bottom: 15px;">
					<a href="#" type="button" id="left_eye" class="btn btn-primary" data-target="#addeye" data-toggle="modal">Add Readings</a>
			       </div>
			       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
					style="margin-bottom: 15px;">
			      <div class="form-group" style="margin-top: 10px;">
						<s:textarea cssClass="form-control" rows="6"
							name="eyereading" id="eyereading" />
					</div>
		       			
				</div>
			</div>
			</div>
			
				
		
						<!-- add Eye reading Modal -->
	<div class="modal fade popoverpop" id="addeye"
		tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup"
		aria-hidden="true" data-keyboard="false" data-backdrop="static"
		style="z-index: 10001;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content" style="width: 80%;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="margin-top: 0px !important">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">
						Eye Readings 
					</h5>
				</div>
				<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<h4><b>Left Eye</b></h4>
				</div>
			    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 form-group"
					style="padding-top: 15px;">
					
				
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>Distance</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			      <select id="left_dist" name="left_dist" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="6/1">6/1</option>
			       <option value="6/2">6/2</option>
			       <option value="6/3">6/3</option>
			       <option value="6/4">6/4</option>
			       <option value="6/5">6/5</option>
			       <option value="6/6">6/6</option>
			      </select>
			    </div>
			   
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>Near</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			    <select id="left_nr" name="left_nr" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="N/1">N/1</option>
			       <option value="N/2">N/2</option>
			       <option value="N/3">N/3</option>
			       <option value="N/4">N/4</option>
			       <option value="N/5">N/5</option>
			       <option value="N/6">N/6</option>
			    </select>
			    </div>
			    
			    
			    </div>
			    
			     <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 form-group"
					style="padding-top: 15px;">
					
					
				
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>With Glasses</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			      <select id="with_glass1" name="with_glass1" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="6/1">6/1</option>
			       <option value="6/2">6/2</option>
			       <option value="6/3">6/3</option>
			       <option value="6/4">6/4</option>
			       <option value="6/5">6/5</option>
			       <option value="6/6">6/6</option>
			      </select>
			    </div>
			   
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>Without Glasses</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			    <select id="withoutgls1" name="withoutgls1" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="N/1">N/1</option>
			       <option value="N/2">N/2</option>
			       <option value="N/3">N/3</option>
			       <option value="N/4">N/4</option>
			       <option value="N/5">N/5</option>
			       <option value="N/6">N/6</option>
			    </select>
			    </div>
			    
			    
			    </div>
			     <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 form-group"
					style="padding-top: 15px;">
					
					
				
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>PIN</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			      <select id="pin1" name="pin1" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="6/1">6/1</option>
			       <option value="6/2">6/2</option>
			       <option value="6/3">6/3</option>
			       <option value="6/4">6/4</option>
			       <option value="6/5">6/5</option>
			       <option value="6/6">6/6</option>
			      </select>
			    </div>
			   
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>IOP</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			    <select id="iop1" name="iop1" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="N/1">N/1</option>
			       <option value="N/2">N/2</option>
			       <option value="N/3">N/3</option>
			       <option value="N/4">N/4</option>
			       <option value="N/5">N/5</option>
			       <option value="N/6">N/6</option>
			    </select>
			    </div>
			    
			    
			    </div>
			    
			    <br>
			    
			    
			    <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<h4><b>Right Eye</b></h4>
				</div>
			    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 form-group"
					style="padding-top: 15px;">
					
				
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>Distance</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			      <select id="right_dist" name="right_dist" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="6/1">6/1</option>
			       <option value="6/2">6/2</option>
			       <option value="6/3">6/3</option>
			       <option value="6/4">6/4</option>
			       <option value="6/5">6/5</option>
			       <option value="6/6">6/6</option>
			      </select>
			    </div>
			   
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>Near</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			    <select id="right_nr" name="right_nr" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="N/1">N/1</option>
			       <option value="N/2">N/2</option>
			       <option value="N/3">N/3</option>
			       <option value="N/4">N/4</option>
			       <option value="N/5">N/5</option>
			       <option value="N/6">N/6</option>
			    </select>
			    </div>
			    
			    
			    </div>
			    
			     <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 form-group"
					style="padding-top: 15px;">
					
					
				
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>With Glasses</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			      <select id="with_glass2" name="with_glass2" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="6/1">6/1</option>
			       <option value="6/2">6/2</option>
			       <option value="6/3">6/3</option>
			       <option value="6/4">6/4</option>
			       <option value="6/5">6/5</option>
			       <option value="6/6">6/6</option>
			      </select>
			    </div>
			   
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>Without Glasses</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			    <select id="withoutgls2" name="withoutgls2" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="N/1">N/1</option>
			       <option value="N/2">N/2</option>
			       <option value="N/3">N/3</option>
			       <option value="N/4">N/4</option>
			       <option value="N/5">N/5</option>
			       <option value="N/6">N/6</option>
			    </select>
			    </div>
			    
			    
			    </div>
			     <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 form-group"
					style="padding-top: 15px;">
					
					
				
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>PIN</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			      <select id="pin2" name="pin2" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="6/1">6/1</option>
			       <option value="6/2">6/2</option>
			       <option value="6/3">6/3</option>
			       <option value="6/4">6/4</option>
			       <option value="6/5">6/5</option>
			       <option value="6/6">6/6</option>
			      </select>
			    </div>
			   
			    <div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			    <label>IOP</label>
			    </div>
			     <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			    <select id="iop2" name="iop2" class="form-control showToolTip chosen">
			       <option value="0">Select distance</option>
			       <option value="N/1">N/1</option>
			       <option value="N/2">N/2</option>
			       <option value="N/3">N/3</option>
			       <option value="N/4">N/4</option>
			       <option value="N/5">N/5</option>
			       <option value="N/6">N/6</option>
			    </select>
			    </div>
			    
			    
			    </div>
			    
				<div class="modal-footer">
					<button
						style="margin-bottom: 10px !important; position: relative !important;"
						type="button" class="btn btn-primary" id='leftbtn'
						onclick="saveeyereading()"  data-dismiss="modal">Save</button>
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
				<!-- create btn -->
			</div>
		</div> 
	</div>
	</div>
		</div>
			
			
			<%-- <%if(loginInfo.isBams1()){ %>
				
				<%}else{ %>
				     
               <%} %>
			</div>
			
				<div class="row">
			<%if(loginInfo.isBams1()){ %>
				
                  <%}else{ %>
				     
               <%} %>
                  
			</div>
				<div class="row">
			<%if(loginInfo.isBams1()){ %>
				
                  <%}else{ %>
				     
               <%} %>
			</div> --%>
			<div class="text-right">
				<input type="submit" class="btn btn-primary savebtn savebigbtn"
					value="Save">
			</div>
		
	</s:form>

	
	
	
	
	
         <!-- Procedure Model for jupiter 06/09/2024 -->
    <div id="addproocedurepopup" class="modal fade" role="dialog" style="z-index: 11000">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" style="margin-top: -7px !important">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
        <h4 class="modal-title">Add Procedure</h4>
      </div>
      
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding-top: 9px;">
        			<div class="form-group">
        			<label>Name</label><label class="text-danger">*</label>
					<s:textfield id="addprocedurename"
						cssClass="showToolTip form-control" data-toggle="tooltip"
						title="Enter Name" placeholder="Enter Name"
						onkeyup="initialCap(this)" />
        			</div>
        	</div>
        </div>
        
        
        
		<div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 9px;">
        		<div class="form-group">
        			<label>Department</label><label class="text-danger">*</label>
        			<s:select list="disciplineList" listKey="id" headerKey="0" headerValue="Select department" listValue="discipline" name="addproceduredescription" title="select department" cssClass="form-control showToolTip chosen-select"  > </s:select>
        			</div>
        	</div>
        </div> 
     </div>
     
     <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="savenewprocedure()" style="margin-top: 20px;">Save</button>
     </div>
    </div>

  </div>
</div>  


<!-- add invesgtigation Modal -->
	<div class="modal fade popoverpop" id="investigationpopup"
		tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup"
		aria-hidden="true" data-keyboard="false" data-backdrop="static"
		style="z-index: 10001;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						style="margin-top: 0px !important">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">
						Create Investigation For <b class="pname" id="invstcmyModalLabel">NAME:</b>
					</h5>
				</div>
				<div class="" id="investipopup">
					<s:hidden id="savetoemr" value="1"></s:hidden>

					<jsp:include page="/emr/pages/addInvestigation.jsp" />


				</div>
				<div class="modal-footer">
					<button
						style="margin-bottom: 10px !important; position: relative !important;"
						type="button" class="btn btn-primary" id='investigationsavebtn'
						onclick="saveIpdInvestigation(0)">Save</button>
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
				<!-- create btn -->
			</div>
		</div>
	</div>
	
<div class="modal fade" id="priscriptionpopup" tabindex="-1"
		role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg" style="width: 100%">
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
						<%if(loginInfo.isSaimed()){ %>
					<button type="button" class="btn btn-primary"
						onclick="addEmrPrisc()" id="prescs_save_btn">Save</button>
						<%}else{ %>
						<button type="button" class="btn btn-primary"
						onclick="insertDischargePriscription1(0)" id="prescs_save_btn">Save</button>
					<!-- <button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)" id="prescs_save_print_btn">Save & Print</button> -->
					<button type="button" class="btn btn-primary"
						onclick="insertDischargePriscription1(1)"
						id="prescs_save_print_btn">Save & Print</button>
              <%} %>
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>

	</div>

<!-- procedure end -->
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
     
     
     
     
    
     
</script>






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
	function setFollowUpDate(val){
		if(val==true){
			$("#followupdate1").datepicker({

				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true

			});
			$('#followupdate1').datepicker('enable');
		}else{
			document.getElementById("followupdate1").value="";
			/* $("#followupdate1").datepicker("disable"); */
			/* $( "#followupdate1" ).datepicker( "option", "disabled", true ); */
			$("#followupdate1").datepicker("disable");
			
		}
	}
    	$(function() {
	    $('#scrolltable').slimScroll({
	   		height : '200px',
	   		railVisible: true,
			alwaysVisible: true
	  });
	 });
    </script>




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





