<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.Ipd.web.form.IpdForm"%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%> 

<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>


<%


String kunalhide="";
if(loginInfo.getIskunal()==1){
	kunalhide="hidden";
}
%>


<%
	String verification = "";
%>

<s:if test="verification==true">
	<%
		verification = "";
	%>
</s:if>
<s:else>
	<%
		verification = "hidden";
	%>
</s:else>



<%
	ArrayList<Priscription> dischargePriscList = (ArrayList<Priscription>) session
			.getAttribute("dischargePriscList");
ArrayList<Priscription> treatmentivendischargePriscList = (ArrayList<Priscription>) session
.getAttribute("treatmentivendischargePriscList");
%>
<%
	IpdForm ipdForm = (IpdForm) session.getAttribute("dischargeddata");
%>
<%
	if (session.getAttribute("bed") != null) {
%>
<%
	Bed ipd = (Bed) session.getAttribute("bed");
%>
<%
 String respiraMedText="";
 if(loginInfo.getClinicUserid().equals("respira")){ 
	respiraMedText="medicinename";
}%>

<!DOCTYPE html>

<html lang="en">
<head>
<title>Admission Summary Form</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
<link href="_assets/css/priscription/hospitalresponsive.css"
	rel="stylesheet" />

<script type="text/javascript" src="ipd/js/admissionform.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="common/JsBarcode.all.min.js"> </script>



<style>
.medicinename{
font-size: 14px !important;
font-weight: bold !important;
}
.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
}

.adformback {
	border: 1px solid;
	padding: 10px 0px 0px;
	margin-top: 0px;
	width: 98%;
	margin-left: 9px;
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

.textareaheight {
	height: 50px !important;;
}

.paddtop {
	padding: 0px 0px 14px 2px;
}

.widthtabhedth1 {
	width: 30%;
}

.widthtabhedth2 {
	width: 7%;
}

.admissionbackgreen {
	width: 210px;
}

.form-group {
	margin-top: 4px;
}

.pad8 {
	padding-top: 8px;
}

.backgrey {
	background-color: rgba(149, 222, 91, 0.19);
}

.pnameback {
	background-color: #f5f5f5;
	margin-top: -7px;
}

.panel-primary {
	border-color: #339966;
}

.padsign {
	padding-top: 100px;
	padding-left: 0px;
	padding-right: 0px;
}

.help-block {
	display: block;
	margin-top: 0px !important;
	margin-bottom: 0px !important;
	color: #737373;
}

body {
	color: #000;
}

.bordertopgreen1 {
	border-top: 2px solid #339966;
}

.panel-primary {
	border-color: #339966;
}

.padsign {
	padding-top: 40px;
}

.help-block {
	display: block;
	margin-top: 0px;
	margin-bottom: 0px;
	color: #737373;
}

h3, .h3 {
	font-size: 16px;
	font-weight: bold;
	color: #6699cc;
	margin-top: 0px;
	margin-bottom: 5px;
}

.form-group {
	margin-bottom: 4px !important;
}

p {
	margin: 0 0 5.5px !important;
}

.table {
	width: 100%;
	max-width: 100%;
	margin-bottom: 5px;
}

.settopbac {
	background-color: #ddd;
}

.totalbor {
	background-color: #f5f5f5;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	padding: 0px 5px 0px 0px !important;
	line-height: 1.42857143;
	vertical-align: top;
	border-top: 1px solid #ddd;
	font-weight: normal;
	font-size: 12px;
	border-right: none !important;
	border-left: none !important;
}

@media print {

.divpadleft{
padding-left: 0px !important;
}
.divpadright{
padding-right: 0px !important;
}
	.print_special {
		border: none !important;
	}
	.ll{
	margin-left: 15px;
	}
	
	.ll2{
	margin-left: 15px;
	margin-right: 15px;
	}
	.ll3{
	margin-left: -15px;
	margin-right: -15px;
	}
	.ll31{
	margin-left: 1px !important;
	margin-right: -15px;
	}
	label {
		font-size: 9px !important;
	}
	p {
		margin: 0 0 5.5px !important;
		font-size: 13px !important;
	}
	.form-group {
		margin-bottom: 4px !important;
	}
	body {
		margin-left: 50px;
	}
	#dcard {
		font-size: 14px !important;
	}
	#hscourse {
		font-size: 12px !important;
	}
	label {
		font-size: 12px !important;
	}
	#otn1 {
		margin-left: -5px;
	}
	#otn2 {
		margin-right: -9px;
	}
	#dcard1 {
		size: 100px !important;
	}
	.newdcard {
		margin-left: -15px;
	}
	.newdcard1 {
		margin-left: -55px;
	}
	.newdcard2 {
		margin-left: -155px;
	}
	#notes2 {
		margin-left: -10px;
	}
	.titleset {
		margin: 0px;
		color: #000;
		border-bottom: 1px dashed #efefef;
		font-size: 12px !important;
		line-height: 20px;
		background-color: #efefef !important;
	}
	h4, .h4 {
		font-size: 12px;
	}
	.backcolor {
		background-color: rgba(91, 192, 222, 0.16) !important;
	}
	.setticolors {
		border-bottom: 4px double #ddd;
		font-size: 17px !important;
		color: firebrick !important;
	}
	.table>thead>tr>th {
		vertical-align: bottom;
		border-bottom: transparent;
		background-color: #4E7894 !important;
		color: #fff !important;
		font-size: 9px !important;
	}
	.table>tbody>tr>td, .table>tfoot>tr>td {
		font-size: 10px !important;
	}
	.clicniaddress {
		font-size: 11px !important;
		font-weight: bold;
	}
	.my-table th{
	color: black !important;
	}
}
</style>
<style>
.borderbot {
	border-bottom: 2px solid #6699cc;
	padding-top: 36px;
	padding-bottom: 20px;
	height: 175px;
}

.clinicname {
	font-size: 20px;
	font-weight: bold;
}

.clicniaddress {
	font-size: 12px;
	font-weight: bold;
}

.rgeno {
	float: right;
	font-size: 11px;
	padding-top: 8px;
}

.titleset {
	margin: 0px;
	color: #6699cc;
	border-bottom: 1px dashed #efefef;
	font-size: 17px;
	line-height: 20px;
	background-color: #efefef;
	padding: 3px 2px 0px 4px;
}

label {
	display: inline-block;
	max-width: 100%;
	margin-bottom: 0px;
	font-weight: bold;
}

td, th {
	padding: 0px 3px 0px 5px !important;
	border-right: 1px solid #eee !important;
}

.setticolors {
	border-bottom: 4px double #ddd;
	font-size: 17px;
	color: firebrick;
}

label {
	font-size: 14px;
}

.font-button {
	background-color: #339966;
	height: 30px;
	width: 30px;
	display: inline-block;
	color: #fff;
	text-align: center;
	line-height: 30px;
	font-size: 15pt;
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


</head>

<%

ipd.setMaternal_history(DateTimeUtils.removeBreaks(ipd.getMaternal_history()));
ipd.setPerinatal_history(DateTimeUtils.removeBreaks(ipd.getPerinatal_history()));
ipd.setBirthhist((DateTimeUtils.removeBreaks(ipd.getBirthhist())));
%>

<script type="text/javascript">
$(document).ready(function() {
	var uhid=document.getElementById('uhiid').value;
	JsBarcode("#barcode", uhid, {
		  format: "CODE128",
		  height: 25,
		  width:1,
		  displayValue:false
		});
});
<!--
	$(document).ready(function() {

		$("#dischargedate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

	});

	bkLib.onDomLoaded(function() {

		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('admissiondetails');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 150
		}).panelInstance('hospitalcourse');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 150
		}).panelInstance('discadvnotes');

	});
	-->
</script>
<body>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

	<%
		String hstry = "";
			String sysreview = "";
			String obstretic_history = "";
			String menstrual_history = "";
			String substance_history = "";
			String pediatric = "";
	%>

	<s:if test="history==true">
	</s:if>
	<s:else>
		<%
			hstry = "hidden";
		%>
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

	<s:if test="paediatrichist">

	</s:if>
	<s:else>
		<%
			pediatric = "hidden";
		%>
	</s:else>
<%String nicuaccess=""; %>
<s:if test="nicuaccess">
<%nicuaccess="hidden"; %>
</s:if>
	 <s:form action="updatedischargeIpd" theme="simple">
		<s:hidden name="treatmentepisodeid" id="treatmentepisodeid" />
		<s:hidden name="id" />

		<%-- <span style="float: right;font-size: 18px;" class="hidden-print"> Font Size <a class="font-button plus" style="color:#fff;">A+</a> <a class="font-button minus" style="color:#fff;">A-</a></span> --%>

		<div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 0px;">
			<div class="" style="height: 155px;">
				<div id="newpost"
					class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id="newpost1"
						style="padding-left: 0px; padding-right: 0px;">
						<link href="common/css/printpreview.css" rel="stylesheet" />
<%if(!loginInfo.isHidelogobillinv()){ %>
						<%@ include file="/accounts/pages/letterhead.jsp"%>
<%} %>

					</div>

				</div>

			</div>
			
		</div>
		
		
		<div class="container-fluid" id="dcard1">

			<div
				class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor  dischargecardbgni">
				<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
						style="padding: 0px;    padding-bottom: 20px">
						<div class="col-lg-4 col-md-4 col-xs-4">
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4"
								style="text-align: right; padding: 0px;">
								<div class="form-group" style="margin-bottom: 0px !important;">
									<a href="#" id="button" class="hidden-print"
										onclick="showhide()"
										style="float: right; background-color: grey; color: #fff; padding: 0px 5px 0px 5px;">Hide
										Letterhead</a>

								</div>
							</div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
							style="padding-left: 0px; padding-right: 0px;">
							
							<div class="col-lg-12 col-md-12 col-xs-12">
								<div class="form-group"
									style="margin-bottom: 0px !important; text-align: center;">
										<b class="setticolors"><s:property value="dischargehead"/></b>
										<%--  <s:if test="daycare">
										<b class="setticolors">Daycare Card</b>
										</s:if>  --%>
								</div>
							</div>
							
						</div>
					</div>
				</div>
				<s:hidden name='regno' id='uhiid'></s:hidden>
				<%if(loginInfo.getIskunal()==1){ %>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left"
					style="padding-left: 0px; padding-right: 0px;">
			
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadleft">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">UHID</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="regno"/></span>
						</div>
						</div>
						
			
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadleft">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Patient Name</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="client"/></span>
						</div>
						</div>
						
						
					
					<%-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<s:if test="agegender!=''">

									<b for="inputEmail3" class="control-label newdcard">Age /
										Gender</b>

								</s:if>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<s:if test="agegender!=''">
									<div class="form-group" style="margin-bottom: 0px;">
										<span><b>:</b> <s:property value="agegender" /></span>
									</div>
								</s:if>
							</div>
						</div>
					</div> --%>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadleft">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Age / Gender</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="agegender"/></span>
						</div>
						</div>
					<%
						if (loginInfo.isDischarge_new()) {
					%>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadleft">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Address</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:property value="addr" /> <s:if
										test="clientpostcode!=null">,&nbsp; <s:property
											value="clienttown" />
									</s:if> <s:if test="clientpostcode!=null">,&nbsp; <s:property
											value="clientpostcode" />
									</s:if></span>
							</div>
						</div>
					</div>
					<%
						}
					%>
					<%if(loginInfo.getIskunal()==1){ %>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadleft">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Consultant</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="doctor_name"/></span>
						</div>
						</div>
					<%} %>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=kunalhide%>">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<s:if test="contact!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Contact</b>
								</div>
							</s:if>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<s:if test="contact!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:property value="contact" /></span>
								</div>
							</s:if>
						</div>
					</div>
					<%
						if (loginInfo.isDischarge_new()) {
					%>
					<%
						if (!loginInfo.getClinicUserid().equals("aureus")) {
					%>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Bed Log</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:iterator value="bedLogList">
										<s:if test="status==1">
											<span><s:property value="wardname" /> / <s:property
													value="bedname" /></span>,</s:if>
										<s:else>
											<span><s:property value="wardname" /> / <s:property
													value="bedname" /></span>,</s:else>
									</s:iterator></span>
							</div>
						</div>
					</div>
					<%
						}
					%>
					<%
						}
					%>
					<s:if test="mlcno!=''">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=kunalhide%>">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">MLC NO</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b>
								<s:property value="mlcno" /></span>
							</div>
						</div>
					</div>
					</s:if>
	



				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left"
					style="padding-left: 0px; padding-right: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadright">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label"><s:if test="daycare">DayCare  NO</s:if><s:else>IPD NO</s:else></label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><%
 	if (loginInfo.getIpd_abbr_access() == 1) {
 %>
										<s:property value="newipdabbr" />
									<%
										} else {
									%>
									<s:property value="ipdseqno" />
									<%
										}
									%></span>
						</div>
						</div>
							
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadright">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Payee</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:property value="thirdParty"/></span>
						</div>
						</div>
						<%
							if (loginInfo.isDischarge_new()) {
						%>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadright">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Dis. Outcome</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style=""><s:if test="dischrgeOutcomes==''"> N/A</s:if>
										<s:else>
											<s:property value="dischrgeOutcomes" />
										</s:else></span>
						</div>
						
						</div>
						<%
							}
						%>
						<s:if test="dischargeStatusId!=4">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadright">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">Dis.
										Status</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style="" class=""><s:if test="dischargeStatus==''"> N/A</s:if>
										<s:else>
											<s:property value="dischargeStatus" />
										</s:else></span>
						</div>
						
						</div>
						
						</s:if>
						
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadright">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<label for="inputEmail3" class="control-label">D.O.A</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style="">
											<s:property value="admissiondate" />
										</span>
						</div>
						
						</div>
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 divpadright">
						<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 ">
						<s:if test="dischargeStatusId==3">
										<label for="inputEmail3" class="control-label">D.O.Death</label>
									</s:if>
									<s:else>
										<label for="inputEmail3" class="control-label">D.O.D</label>
									</s:else>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7">
						<label>:</label>&nbsp;&nbsp; <span style="">
											<s:if test="dischargedate==''">N/A</s:if>
										<s:else>
											<s:property value="dischargedate" />
										</s:else>
										</span>
						</div>
						
						</div>
						
<%}else{ %>

<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-left"
					style="padding-left: 0px; padding-right: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">UHID</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:property value="regno" /></span>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<b id="ptname" for="inputEmail3" class="control-label newdcard">Patient
									Name</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:property value="client" /> </span>
							</div>
						</div>
					</div>
					<s:if test="!nicuaccess">
						<s:if test="relativename!=null">
						<s:if test="relativename!=''">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<b id="ptname" for="inputEmail3" class="control-label newdcard">Relative
									Name</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:property value="relativename" /> </span>
							</div>
						</div>
					</div>
					</s:if>
					</s:if>
					</s:if>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<s:if test="agegender!=''">

									<b for="inputEmail3" class="control-label newdcard">Age /
										Gender</b>

								</s:if>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<s:if test="agegender!=''">
									<div class="form-group" style="margin-bottom: 0px;">
										<span><b>:</b> <s:property value="agegender" /></span>
									</div>
								</s:if>
							</div>
						</div>
					</div>
					
					<s:if test="fathername!=''">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<s:if test="fathername!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label" style="white-space: nowrap;">Father Name</b>
								</div>
							</s:if>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<s:if test="fathername!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:property value="fathername" /></span>
								</div>
							</s:if>
						</div>
					</div>
					</s:if>
					<s:if test="mothername!=''">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<s:if test="mothername!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label"style="white-space: nowrap;">Mother Name</b>
								</div>
							</s:if>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<s:if test="mothername!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:property value="mothername" /></span>
								</div>
							</s:if>
						</div>
					</div>
					</s:if>
					
					<s:if test="birthplace!=''">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<s:if test="birthplace!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Birth Place</b>
								</div>
							</s:if>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<s:if test="birthplace!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:property value="birthplace" /></span>
								</div>
							</s:if>
						</div>
					</div>
					</s:if>
	
					<%
						if (loginInfo.isDischarge_new()) {
					%>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Address </b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:property value="addr" /> <s:if
										test="clientpostcode!=null">,&nbsp <s:property
											value="clienttown" />
									</s:if> <s:if test="clientpostcode!=null">,&nbsp <s:property
											value="clientpostcode" />
									</s:if></span>
							</div>
						</div>
					</div>
					<%
						}
					%>
					<%if(loginInfo.getIskunal()==1){ %>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<s:if test="contact!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Consultant</b>
								</div>
							</s:if>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:property value="doctor_name" /></span>
								</div>
							
						</div>
					</div>
					<%} %>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=kunalhide%>">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<s:if test="contact!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Contact</b>
								</div>
							</s:if>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<s:if test="contact!=''">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:property value="contact" /></span>
								</div>
							</s:if>
						</div>
					</div>
					<%
						if (loginInfo.isDischarge_new()) {
					%>
					<%
						if (!loginInfo.getClinicUserid().equals("aureus")) {
					%>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Bed Log</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:iterator value="bedLogList">
										<s:if test="status==1">
											<span><s:property value="wardname" /> / <s:property
													value="bedname" /></span>,</s:if>
										<s:else>
											<span><s:property value="wardname" /> / <s:property
													value="bedname" /></span>,</s:else>
									</s:iterator></span>
							</div>
						</div>
					</div>
					<%
						}
					%>
					<%
						}
					%>
									
					<s:if test="mlcno!=''">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">MLC NO</b>
							</div>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b>
								<s:property value="mlcno" /></span>
							</div>
						</div>
					</div>
					</s:if>

<s:if test="mlcrefdoctor!=''">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<div class="form-group marbot3"
							style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label" style="white-space: nowrap;"> MLC Consultant</b>
						</div>
					</div>
					
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
					<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b>
								<s:property value="mlcrefdoctor" /></span>
							</div>
					<%-- <div class="form-group marbot3" style="margin-bottom: 0px !important;margin-left: -15px !important;">
								
								
									
										<span style=""><b>:</b> <s:property value="mlcrefdoctor" /></span>
									
								
							</div> --%>
					</div>
				</div>
				</s:if>	



<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<div class="form-group marbot3"
							style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label"> Consultant</b>
						</div>
					</div>
					
					
					
					
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
						<div class="form-group marbot3"
							style="margin-bottom: 0px !important; margin-left: 0px;">
							<span><b>:</b></span>
						<%-- 
							<s:if test="mlccase==1">


								<span class="text-left <%=kunalhide %>"> <s:if test="mlcrefdoctor!=''">
										<span><s:property value="mlcrefdoctor" /></span>
									</s:if> <s:if test="mlcno!=''">
										<span> / <s:property value="mlcno" /></span>
									</s:if>
								</span>
							<%if(loginInfo.getIskunal()==0){ %>	
								<br>
							<%} %>	
							</s:if>
 --%>


							<span><b></b> <s:property value="doctor_name" /> 
							<%
 								if (!DateTimeUtils.isNull(ipd.getDepartment()).equals("0")) {
 							%>
 								(<%=ipd.getDepartment()%>)
 							<%
 								}
 							%>
								<s:if test="useregno!=null">| Reg No:<s:property
										value="useregno" />
								</s:if><br>
							<%--  <s:property value="doctor_phone"/> --%></span>



							<%-- <s:if test="secndryconsult!=''">
								<%
									int akk = 0;
								%>
								<s:iterator value="allconsultantlistwithdepart">
									<%
										if (akk == 0) {
									%>
									<b></b>
									<s:property value="fullname" />(<s:property
										value="specialization" />),<br>
									<%
										} else {
									%>
									<b></b>
									<s:property value="fullname" />(<s:property
										value="specialization" />),<br>
									<%
										}
									%>
									<%
										akk++;
									%>
								</s:iterator>
								<br>
							</s:if>
					 --%>		</span>
						</div>
					</div>
</div>
					
					<s:if test="secndryconsult!=''">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=kunalhide%>">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<div class="form-group marbot3"
							style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label"> Secondary Consultant</b>
						</div>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
						<div class="form-group marbot3"
							style="margin-bottom: 0px !important; margin-left: 0px;">
							<span><b>:</b></span><span>
					<s:if test="secndryconsult!=''">
								<%
									int akk = 0;
								%>
								<s:iterator value="allconsultantlistwithdepart">
									<%
										if (akk == 0) {
									%>
									<b></b>
									<s:property value="fullname" />(<s:property
										value="specialization" />),<br>
									<%
										} else {
									%>
									<b></b>
									<s:property value="fullname" />(<s:property
										value="specialization" />),<br>
									<%
										}
									%>
									<%
										akk++;
									%>
								</s:iterator>
								<br>
							</s:if>
						</span>
					</div>
					</div>
							
					</div>
				</s:if>	
					
					
					
				

<s:if test="nicuaccess">
<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12  ">
							<%
							ipd.setWtonbirth(DateTimeUtils.isNull(ipd.getWtonbirth()));
								if (!ipd.getWtonbirth().equals("")) {
							%>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label newdcard1"> Birth Weight</b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important; margin-left: 0px;">
									<span><b>:</b> <%=ipd.getWtonbirth()%></span>
								</div>
							</div>
							<%
								}
							%>
						</div>
						
						
						<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12  ">
							<%
							ipd.setGstureage(DateTimeUtils.isNull(ipd.getGstureage()));
								if (!ipd.getGstureage().equals("")) {
							%>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label newdcard1">Gest Age</b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important; margin-left: 0px;">
									<span><b>:</b> <%=ipd.getGstureage()%></span>
								</div>
							</div>
							<%
								}
							%>
						</div>
						
</s:if>




				</div>
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right"
					style="padding-left: 0px; padding-right: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label"><s:if test="daycare">DayCare  NO</s:if><s:else>IPD NO</s:else></b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left"
								style="padding: 0px;">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<%-- <span>: <s:property value="id"/>/<s:property value="num_admission"/></span> --%>
									<span><b>:</b> <%
 	if (loginInfo.getIpd_abbr_access() == 1) {
 %>
										<s:property value="newipdabbr" /></span>
									<%
										} else {
									%>
									<s:property value="ipdseqno" />
									<%
										}
									%></span>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label">Payee</b>
									<%-- <s:if test="mlcno!=''">
							  <b for="inputEmail3" class="control-label">/MLC</b>
							</s:if> --%>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left"
								style="padding: 0px;">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:property value="thirdParty" /></span>
								</div>
							</div>
						</div>
						<%
							if (loginInfo.isDischarge_new()) {
								if (!loginInfo.getClinicUserid().equals("aureus")) {
						%>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label newdcard1">Dis.
										Outcome</b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left"
								style="padding: 0px;">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:if test="dischrgeOutcomes==''"> N/A</s:if>
										<s:else>
											<s:property value="dischrgeOutcomes" />
										</s:else></span>
								</div>
							</div>
						</div>
						<%
								}}
						%>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label newdcard1">Dis.
										Status</b><br>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left"
								style="padding: 0px;">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:if test="dischargeStatus==''"> N/A</s:if>
										<s:else>
											<s:property value="dischargeStatus" />
										</s:else></span>
								</div>
							</div>

						</div>
						
						
							
						
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<s:if test="admissiondate!=''">
										<b for="inputEmail3" class="control-label">D.O.A</b>
									</s:if>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left"
								style="padding: 0px;">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<s:if test="admissiondate!=''">
										<span><b>:</b> <s:property value="admissiondate" /> </span>
									</s:if>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group" style="margin-bottom: 0px !important;">
									<s:if test="dischargeStatusId==3">
										<b for="inputEmail3" class="control-label">D.O.Death</b>
									</s:if>
									<s:else>
										<b for="inputEmail3" class="control-label">D.O.D</b>
									</s:else>

								</div>

							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left"
								style="padding: 0px;">
								<div class="form-group" style="margin-bottom: 0px !important;">
									<span><b>:</b> <s:if test="dischargedate==''">N/A</s:if>
										<s:else>
											<s:property value="dischargedate" />
										</s:else></span>
								</div>
							</div>
						</div>
<div style="padding-right: 10px;">					
	<%if(!loginInfo.getClinicUserid().equals("iconhospital")){ %>
						<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=pediatric%>">
							<s:if test="ageonadmn!=''">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
									<div class="form-group marbot3"
										style="margin-bottom: 0px !important;">
										<b for="inputEmail3" class="control-label newdcard1">Age
											On Admission</b>
									</div>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
									<div class="form-group marbot3"
										style="margin-bottom: 0px !important; margin-left: -10px;">
										<span><b>:</b> <s:property value="ageonadmn" /></span>
									</div>
								</div>
							</s:if>
						</div>
						<%} %>
							<%if(!loginInfo.getClinicUserid().equals("iconhospital")){ %>
						<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=pediatric%> ">
							<s:if test="age!=''">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
									<div class="form-group marbot3"
										style="margin-bottom: 0px !important;">
										<b for="inputEmail3" class="control-label newdcard1">Age
											On Discharge</b>
									</div>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
									<div class="form-group marbot3"
										style="margin-bottom: 0px !important; margin-left: -10px;">
										<!-- for balgopal we commented -->
										<%-- <span><b>:</b> <s:property value="age" /></span> --%>
										<span><b>:</b> <s:property value="Ageondischarge" /></span>
									</div>
								</div>
							</s:if>
						</div>
<%} %>
<%} %>
<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=pediatric%>">
							<%
								if (!ipd.getHeadcircumference().equals("")) {
							%>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label newdcard2"> Head Cir.
								</b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important; margin-left: -10px;">
									<span><b>:&nbsp;&nbsp;</b><%=ipd.getHeadcircumference()%>  cm</span>
								</div>
							</div>
							<%
								}
							%>
						</div>
						<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=pediatric%> ">
							<%
								if (!ipd.getLength().equals("")) {
							%>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label newdcard1">Length</b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important; margin-left: -10px;">
									<span><b>:</b> <%=ipd.getLength()%>  cm</span>
								</div>
							</div>
							<%
								}
							%>
						</div>
						
						<div
							<%-- class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=pediatric%>"> --%>
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<!-- coomenting above line beacause show for all i,e pedtrician,gynic -->
							<%
								if (!ipd.getWtaddmission().equals("")) {
							%>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label newdcard2">Weight
										on Admission</b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important; margin-left: -10px;">
									<span><b>:&nbsp;&nbsp;</b><%=ipd.getWtaddmission()%>  </span>
								</div>
							</div>
							<%
								}
							%>
						</div>
						
						<div
							<%-- class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=pediatric%>"> --%>
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<!-- coomenting above line beacause show for all i,e pedtrician,gynic -->
							<%
								if (!ipd.getWtdischarge().equals("")) {
							%>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label newdcard2">Weight
										on Discharge</b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important; margin-left: -10px;">
									<span><b>:&nbsp;&nbsp;</b><%=ipd.getWtdischarge()%>  </span>
								</div>
							</div>
							<%
								}
							%>
						</div>
					
						<div
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=pediatric%>">
							<%
								if (!ipd.getMidarmcircumference().equals("")) {
							%>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important;">
									<b for="inputEmail3" class="control-label newdcard2">Mid
										Arm Circumference </b>
								</div>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left">
								<div class="form-group marbot3"
									style="margin-bottom: 0px !important; margin-left: -10px;">
									<span><b>:&nbsp;&nbsp;</b><%=ipd.getMidarmcircumference()%>  </span>
								</div>
							</div>
							<%
								}
							%>
							
						
</div>
<%
String bal="";
if(!loginInfo.isBalgopal()) {
	bal="hidden";
	
}%>
<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right <%=bal%>">
<div class="form-group marbot3"
				style="margin-bottom: 0px !important;">
			<b for="inputEmail3" class="control-label newdcard2">
				Barcode </b>
								</div>
</div>

<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8  <%=bal%>" style="text-align: left;"> 
	<svg id="barcode"></svg>
</div>


<%-- 						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left hidden"
							style="padding: 0px;">
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span>: <s:property value="id"/>/<s:property value="num_admission"/></span>
								<span><b>:</b> <s:property value="ipdseqno" /></span>
							</div>

							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:property value="thirdParty" /></span>
								<s:if test="mlcno!=''">
								  <span>/ <s:property value="mlcno"/></span>
								</s:if>
								<s:if test="mlcrefdoctor!=''">
							 	<span>/ <s:property value="mlcrefdoctor"/></span>
							 	</s:if>
							</div>
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:if test="dischrgeOutcomes==''"> N/A</s:if>
									<s:else>
										<s:property value="dischrgeOutcomes" />
									</s:else></span>
							</div>
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:if test="dischargeStatus==''"> N/A</s:if>
									<s:else>
										<s:property value="dischargeStatus" />
									</s:else></span>
							</div>
							<div class="form-group marbot3"
								style="margin-bottom: 0px !important;">
								<s:if test="admissiondate!=''">
									<span><b>:</b> <s:property value="admissiondate" /> </span>
								</s:if>
							</div>
							<div class="form-group" style="margin-bottom: 0px !important;">
								<span><b>:</b> <s:if test="dischargedate==''">N/A</s:if>
									<s:else>
										<s:property value="dischargedate" />
									</s:else></span>
							</div>


						</div> --%>
					</div>
				</div>
				
								
				
				<div
					class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=pediatric%> ">
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6  <%=pediatric%>">
				
						
					</div>


					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6  <%=pediatric%>">
						
							
					</div>
				</div>



			</div>



		</div>
		</div>

		<div class="">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
				style="padding-top: 10px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
					style="padding-left: 0px; padding-right: 0px;">
					<%-- <s:if test="mlcrefdoctor!='' || mlcno!=''"> --%>
					<s:if test="mlccase==1">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
							style="padding: 0px;">
							<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
								style="padding: 0px;">
								<div class="form-group">
									<b for="exampleInputName2"></b>
								</div>
							</div>
							<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10"
								style="padding-left: 0px;">
								<div class="form-group"></div>
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden "
							style="padding: 0px;">
							<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
								style="padding: 0px;">
								<div class="form-group">
									<b for="exampleInputName2"> Consultant </b>
								</div>
							</div>
							<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10"
								style="padding-left: 0px;"></div>
						</div>

						<%-- <s:if test="allConsultantList!=null">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <b for="exampleInputName2">Associate Consultants </b> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding-left: 0px;">
	                        			<div class="form-group">
	                        				 <span><s:if test="secndryconsult!=''"><s:iterator value="allConsultantList"><s:property />, </s:iterator></s:if></span>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	</s:if> --%>
						<s:if test="allconsultantlistwithdepart!=null">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden"
								style="padding: 0px;">
								<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
									style="padding: 0px;">
									<div class="form-group">
										<b for="exampleInputName2">Associate Consultants </b>
									</div>
								</div>
								<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10"
									style="padding-left: 0px;">
									<div class="form-group">
										<span> <s:if test="secndryconsult!=''">
												<%
													int akk = 0;
												%>
												<s:iterator value="allconsultantlistwithdepart">
													<%
														if (akk == 0) {
													%>
													<b>:</b>&nbsp;<s:property value="fullname" />(<s:property
														value="specialization" />),
	                        				 		<%
														} else {
													%>
													<b>:</b>&nbsp;<s:property value="fullname" />(<s:property
														value="specialization" />),
	                        				 		<%
														}
													%>
													<%
														akk++;
													%>
												</s:iterator>
											</s:if>
										</span>
									</div>
								</div>
							</div>
						</s:if>
					</s:else>
					<%if(!loginInfo.isAyushman()){ %>
					<%
						if (ipd.getRefferedby() != null) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="padding: 0px;">
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
							style="padding: 0px;">
							<div class="form-group">
								<b for="exampleInputName2">Reference From </b>
							</div>
						</div>
						<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10"
							style="padding-left: 0px;">
							<div class="form-group">
								:&nbsp;<span>
									<%
										if (ipd.getRefferedby() != null) {
									%>
									<%
										if (ipd.getRefferedby().equals("")) {
									%>N/A<%
										} else {
									%><%=ipd.getRefferedby()%>
									<%
										}
									%>
									<%
										} else {
									%>N/A<%
										}
									%>
								</span>
							</div>
						</div>
					</div>
					<%
						}
					%>
             <%
			   }
			%>
				</div>
			</div>
		</div>


<div class="">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
				style="margin-top: 10px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
					style="padding-left: 0px; padding-right: 0px;">
					<table class="table" style="width: 100%;">
						<thead style="color: #6699cc;">
							<tr>
							 <s:if test="finalConditions.size!=0">
								<td style="width: 100%;">
									<h4 class="text-left titleset"><b>FINAL DIAGNOSIS</b></h4>
								</td>
								<td style="" class="hidden">
									<h4 class="text-left titleset" style="color: #6699cc;"><b>PROVISIONAL
										DIAGNOSIS</b></h4>
								</td>
								</s:if>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<table style="border: 0px;" width="100%">
										<s:iterator value="finalConditions">
											<tr>
												<td><b style="font-size: 13px; font-weight: 700;"><s:property
															value="conditionname" /></b></td>
											</tr>
										</s:iterator>
										<tr>
											<td>
												<%
													if (ipdForm.getKunal_final_diagnosis_text() == null) {
																ipdForm.setKunal_final_diagnosis_text("");
															}
												%> <%=ipdForm.getKunal_final_diagnosis_text().toString()%>

											</td>
										</tr>
									</table>

								</td>
								<td class="hidden">
									<table id="innercondition" width="100%">
										<%
											ArrayList<String> ipdCondtitionList = (ArrayList<String>) session.getAttribute("ipdCondtitionList");
										%>
										<%
											int i = 0;
													for (String str : ipdCondtitionList) {
										%>

										<tr>
											<th></th>
											<td><b> <%=str%></b></td>
											<!-- <td><input type="hidden" name="conditions[0].conditionname" id="conditionname0"/></td> -->
										</tr>

										<%
											i++;
													}
										%>
									</table>
								</td>
							</tr>
						</tbody>

					</table>
					
				</div>
				
			</div>
		</div>

<%if(!DateTimeUtils.removeBreaks(ipdForm.getLamadamareason()).equals("")){ %>
<%
String islamadama="";
String dischargeStatus=DateTimeUtils.isNull((String)request.getAttribute("dischargeStatusId"));
if(dischargeStatus.equals("7")){
	islamadama="LAMA";
}else if(dischargeStatus.equals("8")){
	islamadama="DAMA";
}
System.out.println(islamadama);
%>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
		<h4 class="text-left titleset" style="color: #6699cc;"><b>REASON FOR <%=islamadama %>	</b></h4>
		<div class=" ">
		<p><%=ipdForm.getLamadamareason() %></p>
		</div>	
	</div>
<%} %>
		<br>
		<br>
		<div class="">
<%
String kunalnew="";
if(loginInfo.getIskunal()==1){
	kunalnew="CASE";
}
%>
          
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
			<%
			if (ipd.getChiefcomplains() != null || ipd.getAlergies() != null || ipd.getAlergies() != null) {
		    %>
				<h4 class="text-left titleset" style="color: #6699cc;"><b> <%=kunalnew %> SUMMARY</b>
				</h4>
			<%
			  }
			 %>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
					style="margin-top: 0px; padding: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
						style="padding-left: 0px; padding-right: 0px;">

						<s:if test="mlccase==1">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden"
								style="padding: 0px;">
								<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
									style="padding: 0px;">
									<div class="form-group">
										<b for="exampleInputName2">Primary Consultant </b>
									</div>
								</div>
								<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10"
									style="padding-left: 0px;">
									<div class="form-group">
										<span> <s:property value="doctor_name" /> <%
 	if (!ipd.getDepartment().equals("0")) {
 %>(<%=ipd.getDepartment()%>)<%
 	}
 %>
											<s:if test="useregno!=null">| Reg No:<s:property
													value="useregno" />
											</s:if>
											<%--  <s:property value="doctor_phone"/> --%></span>
									</div>
								</div>
							</div>

							<%-- <s:if test="allConsultantList!=null">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <b for="exampleInputName2">Associate Consultants </b> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding-left: 0px;">
	                        			<div class="form-group">
	                        				 <span><s:if test="secndryconsult!=''"><s:iterator value="allConsultantList"><s:property />, </s:iterator></s:if></span>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	</s:if> --%>
							<s:if test="allconsultantlistwithdepart!=null">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden"
									style="padding: 0px;">
									<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"
										style="padding: 0px;">
										<div class="form-group">
											<b for="exampleInputName2">Associate Consultants </b>
										</div>
									</div>
									<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10"
										style="padding-left: 0px;">
										<div class="form-group">
											<span> <s:if test="secndryconsult!=''">
													<%
														int akk = 0;
													%>
													<s:iterator value="allconsultantlistwithdepart">
														<%
															if (akk == 0) {
														%>
														<s:property value="fullname" />(<s:property
															value="specialization" />),
	                        				 		<%
															} else {
														%>
														<s:property value="fullname" />(<s:property
															value="specialization" />),
	                        				 		<%
															}
														%>
														<%
															akk++;
														%>
													</s:iterator>
												</s:if>
											</span>
										</div>
									</div>
								</div>

							</s:if>
						</s:if>
					</div>

				</div>



				<%-- <%if(ipd.getAddmissionfor()!=null){ %> --%>






				<%
					if (ipd.getAddmissionfor() != null) {
				%>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="padding: 0px;">
					<div class="">
						<div class="">
							<b for="exampleInputName2">Admission For </b>
						</div>
					</div>
					<div class="" style="padding-left: 0px;">
						<div class="form-group">
							<p><%=ipd.getAddmissionfor()%></p>
						</div>
					</div>
				</div>

				<%
					}
				%>

				<%
					if (ipd.getAlergies() != null) {
				%>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="padding: 0px;">
					<div class="">
						<div class="">
							<b for="exampleInputName2">H/O Allergies </b>
						</div>
					</div>
					<div class="" style="padding-left: 0px;">
						<div class="form-group">
							<p><%=ipd.getAlergies()%></p>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<%
					if (ipd.getChiefcomplains() != null) {
				%>

				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-left "
					style="padding: 0px;">
					<div class="form-group">
					<%if(loginInfo.getIskunal()==0){ %>
						<b for="">CHIEF COMPLAINTS </b>
					<%} %>	
					</div>
					<div class="form-group">
						<p><%=ipd.getChiefcomplains()%></p>
					</div>

				</div>

				<%
					}
				%>
				</div>
				</div>

                <%
					if (ipd.getPresentillness() != null) {
				%>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="padding: 0px;">
					<div class="form-group">
						<b>HISTORY OF PRESENT ILLNESS </b>
					</div>
				</div>
				<div class="" style="padding-right: 2px;">
					<div class="form-group">
						<p><%=ipd.getPresentillness()%></p>
						<%--  <div  style="font-weight: normal;text-align:justify;"><%=ipd.getPresentillness() %></div>   --%>
					</div>
				</div>
			
			<%
				}
			%>
			
					<s:if test="dischargeStatusId==3">
		</s:if>
		<s:else>
			<div class="">
				<div
					class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=sysreview%>"
					style="padding-top: 10px;">
					<h4 class="text-left titleset" style="color: #6699cc;">SYSTEMIC
						REVIEW</h4>
				</div>
				<div
					class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=sysreview%>"
					style="margin-top: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
						style="padding-left: 0px; padding-right: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
							style="padding: 0px;">
							<div class="form-group">
								<p style="margin: 0 0 0px; font-size: 13px">
									<b>There is NO H/O -</b>
									<%
										if (("No").equals(ipd.getFpcondition())) {
									%>
									<label style="font-weight: normal;" for="exampleInputName2">Fever
										at present</label>,
									<%
										}
									%>
									<s:if test="nauseacondition=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Nausea</label>,
	                        			</s:if>
									<s:if test="fnucondition=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Frequent
											Nocturnal Urination</label>,
	                        			</s:if>
									<s:if test="smcondition=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Straining
											at micturation</label>,
	                        			</s:if>
									<s:if test="chestpaincond=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Chest
											pain</label>,
	                        			</s:if>
									<s:if test="dimvisioncond=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Dimness
											of vision</label>,
	                        			</s:if>
									<s:if test="hgucondition=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Headache,
											Giddiness, Unconsciousness</label>,
	                        			</s:if>
									<s:if test="hmcondition=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Haemoptysis,
											Malena</label>,
	                        			</s:if>
									<s:if test="jointpaincond=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Joint
											pain</label>,
	                        			</s:if>
									<s:if test="edemafeetcondi=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Edema
											feet</label>,
	                        			</s:if>
									<s:if test="hematuriacondi=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Hematuria</label>,
	                        			</s:if>
									<s:if test="bmcondition=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Burning
											micturation</label>,
	                        			</s:if>
									<s:if test="oliguriacondi=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Oliguria</label>,
	                        			</s:if>
									<s:if test="pstgucondi=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Passing
											stone or gravel in the urine</label>,
	                        			</s:if>
									<s:if test="ihcondition=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Impaired
											hearing</label>,
	                        			</s:if>
									<s:if test="bmecondition=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Breathlessness
											on mild exertion</label>,
	                        			</s:if>
									<s:if test="tnecondition=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Tingling,
											Numbness in extremities</label>,
	                        			</s:if>
									<s:if test="weaknesscondi=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Weakness</label>,
	                        			</s:if>
									<s:if test="constipationcond=='No'">
										<label style="font-weight: normal;" for="exampleInputName2">Constipation</label>
									</s:if>
								</p>

								<%
									if (ipd.getSpecialnotes() != null) {
								%>
								<label for="exampleInputName2">Special Notes/Remarks : </label><span
									class="help-block" style="text-align: justify;"><%=ipd.getSpecialnotes()%>
								</span>
								<%
									}
								%>

							</div>
						</div>
					</div>
				</div>
			</div>
		</s:else>
     <s:if test="nicuaccess">
					<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12'>
					 <%if((!ipd.getMaternal_history().equals(""))||(!ipd.getPerinatal_history().equals(""))){%>
					<h4 class="text-left titleset" style="color: #6699cc;"><b> NICU HISTORY</b>
					<%} %>
				</h4>
					   <%if(!ipd.getMaternal_history().equals("")){%>
					 <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12  " style="padding: 0px;padding-top: 10px !important;">
	                        	<b>MATERNAL HISTORY </b>
	                        </div>
	                        
					       			<div class="form-group">
	                        				 <span><%=ipd.getMaternal_history()%></span> 
	                        			</div>
	               
	                <%} %>
	                <%if(!(ipd.getPerinatal_history().equals(""))){%>
	                <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12  " style="padding: 0px;padding-top: 10px !important;">
	                        	<b>PERINATAL HISTORY </b>
	                        </div>
	                        
				
	                        			<div class="form-group">
	                        				 <span><%=ipd.getPerinatal_history()%></span> 
	                        			</div>
	            
	                <%} %>
	    </div>            
</s:if>

		<div class="">
			<%
			boolean hisflag=true;
			if((DateTimeUtils.removeBreaks(ipd.getPasthistory()).equals("")) && (DateTimeUtils.removeBreaks(ipd.getFamilyhist()).equals("")) && (DateTimeUtils.removeBreaks(ipd.getSurgicalnotes()).equals("")) && DateTimeUtils.removeBreaks(ipd.getPersonalhist()).equals("") && DateTimeUtils.removeBreaks(ipd.getExample()).equals("")){
				hisflag=false;
			}
			
				if (hisflag) {
			%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=hstry%>"
				style="padding-top: 10px;">
				<h4 class="text-left titleset" style="color: #6699cc;"><b>HISTORY</b>
				</h4>
			</div>
			<%
				}
			%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=hstry%>"
				style="margin-top: 0px; padding: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
					style="padding-left: 0px; padding-right: 0px;">
					<%
						if (ipd.getHistory() != null) {
					%>
					<%
						if (ipd.getExample() != null) {
					%>


					<%-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

							<p><%=ipd.getExample()%></p>

						</div>
					</div> --%>

					<%
						}
					%>

					<%
					boolean pastflag=true;
					if(DateTimeUtils.removeBreaks(ipd.getPasthistory()) == null || DateTimeUtils.isNull(ipd.getPasthistory()).equals("") || DateTimeUtils.isNull(ipd.getPasthistory()).equals("<br>")){
						pastflag=false;
					}
						if (pastflag) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<b>PAST HISTORY</b>
							

						
									<p><%=ipd.getPasthistory() %></p>
						</div>		
					</div>
					<br>
					<%
						}
					%>
					
					<%
						if (ipd.getFamilyhist() != null) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<b for="exampleInputName2">FAMILY HISTORY</b>
							</div>

							<div class="" style="padding-left: 0px;">
								<div class="form-group">
									<p><%=ipd.getFamilyhist()%></p>
								</div>
							</div>
						</div>
					</div>
					<%
						}
					%>
					<%
						if (ipd.getSurgicalnotes() != null) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<b for="exampleInputName2">SURGICAL HISTORY </b>
							</div>

							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style="padding-left: 0px;">
								<div class="form-group">
									<p><%=ipd.getSurgicalnotes()%></p>
								</div>

							</div>
						</div>
					</div>
					<%
						}
					%>
					
					<%
						if (ipd.getPersonalhist() != null ) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<b>	PERSONAL HISTORY</b> 
							

						
									<p><%=ipd.getPersonalhist()%></p>
						</div>		
					</div>
					<%
						}
					%>
					
					

					
					<%
						if (ipd.getSuggestedtrtment() != null) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<b for="exampleInputName2">TREATMENT HISTORY </b>
							</div>

							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style="padding-left: 0px;">
								<div class="form-group">
									<p><%=ipd.getSuggestedtrtment()%></p>
								</div>
							</div>
						</div>
					</div>

					<%
						}
					%>
					<%
						if (ipd.getEarlierinvest() != null) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<b for="exampleInputName2">EARLIER INVESTIGATION </b>
							</div>

							<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10"
								style="padding-left: 0px;">
								<div class="form-group">
									<p><%=ipd.getEarlierinvest()%></p>
								</div>
							</div>
						</div>
					</div>

					<%
						}
					%>
					
					
					
					<%
						}
					%>
				</div>
			</div>
		</div>
			<%ipd.setDiethist(DateTimeUtils.removeBreaks(ipd.getDiethist()));
					ipd.setDevelopmenthist(DateTimeUtils.removeBreaks(ipd.getDevelopmenthist()));
					ipd.setBirthhist(DateTimeUtils.removeBreaks(ipd.getBirthhist()));
					ipd.setEmmunizationhist(DateTimeUtils.removeBreaks(ipd.getEmmunizationhist()));
					
					
					%>
		
			
<!-- //peditric
 -->

<%
boolean showpedi=true;
if(ipd.getDiethist().equals("")&&ipd.getDevelopmenthist().equals("")&&ipd.getBirthhist().equals("")&&ipd.getEmmunizationhist().equals("")){ %>
<%showpedi=false; %>
<%} %>
	
			<!-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
				style="padding: 10px 0px 0px 0px;"> -->
				
				<%
					if (ipd.isPeditric()&&showpedi) {
				%>
					<div class=" <%=pediatric%>">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  "
					>
					
					<h4 class="text-left titleset" style="color: #6699cc;"><b>PEDIATRICS HISTORY</b>
						</h4>
				</div>

				<!-- <div class="row "> -->
					<%
					boolean diet=true;
					if(ipd.getDiethist() == null||ipd.getDiethist().equals("")||ipd.getDiethist().equals("<br>")){
						diet=false;
					}
						if (diet) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 printmarginright"
							style="padding: 0px;">
							<div class="form-group">
								<p>DIET HISTORY</p>
							</div>
						</div>

					</div>
				
					
					
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="form-group">
							<span><%=ipd.getDiethist()%></span>
						</div>
					</div>
					<%
						}
					%>
					<%
					boolean devep=true;
					if(ipd.getDevelopmenthist()==null||ipd.getDevelopmenthist().equals("")||ipd.getDevelopmenthist().equals("<br>")){
						devep=false;
					}
						if (!ipd.getDevelopmenthist().equals("")) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 "
							style="padding: 0px;">
							<div class="form-group">
								<p>DEVELOPMENT HISTORY</p>
							</div>
						</div>

					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="form-group">
							<span><%=ipd.getDevelopmenthist()%></span>
						</div>
					</div>
					<%
						}
					%>
					<%
						if (!ipd.getBirthhist().equals("")) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 "
							style="padding: 0px;">
							<div class="form-group">
								<b for="exampleInputName2"
									style="text-transform: uppercase;">BIRTH HISTORY</b>
							</div>
						</div>

					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="form-group">
							<span><%=ipd.getBirthhist()%></span>
						</div>
					</div>
					<%
						}
					%>
					<%
						if (!ipd.getEmmunizationhist().equals("")) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 "
							style="padding: 0px;">
							<div class="form-group">
								<b for="exampleInputName2"
									style="text-transform: uppercase;">IMMUNIZATION </b>
							</div>
						</div>

					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
						<div class="form-group">
							<span><%=ipd.getEmmunizationhist()%></span>
						</div>
					</div>
					<%
						}
					%>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
				style="padding: 10px 0px 0px 0px;">
				<div class="row "></div>
			</div> 
		</div>
					<%
						}
					%>
				<!-- </div> -->


			<!-- </div> -->

			<!-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
				style="padding: 10px 0px 0px 0px;">
				<div class="row "></div>
			</div> 
		</div> -->
<%
						if (ipd.getOnexamination() != null) {
					%>
					
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<h4 class="text-left titleset" style="color: #6699cc;"><b><s:if test="nicuaccess"></s:if><s:else>ON</s:else> EXAMINATION</b>
						</h4>
					</div>
					
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<p><%=ipd.getOnexamination()%></p>
					</div>
					
					
					<%
						}
					%>

		
<s:if test="dischargeStatusId==3">
				</s:if>
				<s:else>
				<div class="">
				
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=substance_history%>" style="margin-top: 0px;padding:0px;">
						<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">SUBSTANCE HISTORY </h4>
	                        </div>
	                        <div class="row <%=substance_history%>">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Alcohal <span style="font-weight: normal;text-align:justify;">: <%=ipd.getAlcohal()%></span></label>, <label for="exampleInputName2">Tobacco <span style="font-weight: normal;">: <%=ipd.getTobaco()%></span></label>  
	                        			</div>
	                        	</div>
	                        	<%if (ipd.getOther_medication() != null) {%>
	                        	<%if (!ipd.getOther_medication().equals("")) {%>
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
	                        				<div class="form-group">
	                        					 <b for="exampleInputName2">Other Medication </b> 
	                        				</div>
	                        			</div>
	                        			<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding-left: 0px;">
	                        				<div class="form-group">
	                        					 <label for="exampleInputName2" style="font-weight: normal;text-align:justify;"><%=ipd.getOther_medication()%></label> 
	                        				</div>
	                        			</div>
	                        		</div> 
	                        		<%}%>
	                        <%}%>
	                        <%if (ipd.getDrugs() != null) {%>
	                        	<%if (!ipd.getDrugs().equals("0")) {%>
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
	                        				<div class="form-group">
	                        					 <b for="exampleInputName2">Drugs </b> 
	                        				</div>
	                        			</div>
	                        			<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding-left: 0px;">
	                        				<div class="form-group">
	                        					 <label for="exampleInputName2" style="font-weight: normal;text-align:justify;"><%=ipd.getDrugs()%></label> 
	                        				</div>
	                        			</div>
	                        		</div> 
	                        	<%}%>
	                        <%}%>
	                        <%if (ipd.getSmoking() != null) {%>
	                        	<%if (!ipd.getSmoking().equals("0")) {%>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
	                        			<div class="form-group">
	                        				 <b for="exampleInputName2">Smoking </b> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding-left: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="font-weight: normal; text-align:justify;"><%=ipd.getSmoking()%></label> 
	                        			</div>
	                        		</div>
	                        	</div> 
	                       		<%}%>
	                        <%}%>
	                        <%if (ipd.getTobaconotes() != null) {%>
	                        	<%if (!ipd.getTobaconotes().equals("")) {%>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Tobaco </label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding-left: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="font-weight: normal;text-align:justify;"><%=ipd.getTobaconotes()%></label> 
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	<%}%>
	                        <%}%>
	                        	</div>
	                        </div>
					</div>
				</div>

				<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=menstrual_history%> " style="margin-top: 0px;padding:0px;">
						<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">MENSTRUAL HISTORY </h4>
	                        </div>
	                        <div class="row <%=menstrual_history%>">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-3 col-sm-3">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Age at Minarche </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">L.M.P </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">L.L.M.P </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">PMC </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Cycle Length </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Duration of Flow </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Amount of Flow </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Dysmenorhea </label> 
	                        			</div>
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-2 col-sm-2 <%=menstrual_history%>">
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getAge_menarche()%> </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getLmp()%></label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getLlmp()%> </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getPmc()%> </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getCycle_length()%> </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getDuration_flow()%> </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getAmount_flow()%> </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getDysmenorrhea()%> </label> 
	                        			</div>
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-4 col-sm-4">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Dyspareunia </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">HO passing clt </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">White Discharge and Itching </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">InterCourse Freq </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Galactorea </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Hocontrarec </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Rubbela Vaccine </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Menopause </label> 
	                        			</div>
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-3 col-sm-3">
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getDyspareunia()%> </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getHopassing_clots()%></label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getWhite_disc_itching()%></label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getIntercourse_freq()%></label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getGalactorrea()%></label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getHo_contraception()%></label>
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getRubella_vaccine()%></label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getRubella_vaccine()%></label> 
	                        			</div>
	                        	</div>
	                        	</div>
	                        </div>
					</div>
				</div>
				
					<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=obstretic_history%>" style="margin-top: 0px;padding:0px;">
							<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">OBSTRETIC HISTORY</h4>
	                        </div>
	                        <div class="row <%=obstretic_history%>">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        	<div class="col-lg-2 col-md-2 col-xs-3 col-sm-3">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Nulligravida </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Currently Pregnent </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">IUD </label> 
	                        			</div>
	                        	</div>
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-2 col-sm-2">
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getNulligravida()%> </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getCurrent_pregnent()%></label> 
	                        			</div>
	                        			<div class="form-group">
	                        				<label for="exampleInputName2" style="font-weight: normal;text-align:justify;">: <%=ipd.getIud()%> </label> 
	                        			</div>
	                        	</div>
	                        	<div class="col-lg-6 col-md-6 col-xs-7 col-sm-7">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Live Boys <span style="font-weight: normal;text-align:justify;">: <%=ipd.getLive_boys()%></span></label>, <label for="exampleInputName2">Live Girls <span style="font-weight: normal;">: <%=ipd.getLive_girls()%></span></label>, <label for="exampleInputName2">Still Births <span style="font-weight: normal;">: <%=ipd.getStillbirths()%></span></label>, <label for="exampleInputName2">Abortions <span style="font-weight: normal;">: <%=ipd.getAbortions()%></span></label>, <label for="exampleInputName2">Death Child <span style="font-weight: normal;">: <%=ipd.getDeath_children()%></span></label>  
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">P <span style="font-weight: normal;">: 5</span></label>, <label for="exampleInputName2">L <span style="font-weight: normal;">: 3</span></label>, <label for="exampleInputName2">A <span style="font-weight: normal;">: 1</span></label>, <label for="exampleInputName2">D <span style="font-weight: normal;">: 1</span></label>  
	                        			</div>
	                        	</div>
	                        	</div>
	                        	</div>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-bottom: 15px;">
	                        			<h5 style="color: brown;">Sequence of Parity/Abortions</h5>
	                        				<table class="table" style="border: 1px solid #eee;width: 100%;">
	                        					<thead>
	                        						<tr>
	                        							<th>Sr.No</th>
	                        							<th>Year</th>
	                        							<th>Child</th>
	                        							<th>Type of Delivery</th>
	                        							<th>Indications</th>
	                        							<th>Complications</th>
	                        							<th>Institutions</th>
	                        						</tr>
	                        					</thead>
	                        				   <%i = 0;%>
	                        					<tbody>
	                        					    <s:iterator value="gynicobsList">
	                        						 <tr style="border-bottom: 1px solid #eee;">
	                        							<td style="width: 4%;padding-right: 15px;"> <%=i + 1%></td>
	                        							<td style="width: 5%;padding-right: 15px;"><s:property value="year"/></td>
	                        							<td style="width: 13%;padding-right: 15px;"><s:property value="type"/></td>
	                        							<td style="width: 14%;padding-right: 15px;">
															<s:property value="type_delivery"/>
	                        							</td>
	                        							<td style="width: 20%;padding-right: 15px;"><s:property value="indications"/></td>
	                        							<td style="width: 20%;padding-right: 15px;"><s:property value="coamplications"/></td>
	                        							<td style="width: 20%;padding-right: 15px;"><s:property value="institution"/></td>
	                        						  </tr>
	                        						 <%i++;%>
	                        					   </s:iterator>
	                        					</tbody>
	                        				</table>
	                        		</div>
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-3 col-sm-3">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Parity/Abortion Notes</label> <br>
	                        				 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-7 col-sm-7">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="font-weight: normal;">:<s:property value="parity_abortion_notes"/> </label> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	</div>
	                        </div>
	                        
					</div>
				</div>
				</s:else>

<!--<%-- <%String ipdotnotes = (String)session.getAttribute("ipdotnotes"); %>
								  <%if(ipdotnotes==null){
									  ipdotnotes="";
								  } else { %> --%>
								  
					<div class="">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " style="margin-top: 10px;padding:0px;">
							<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">OPERATING SURGEON </h4>
	                        </div>
	                        <div class="col-lg-12 col-md-12 col-xs-12">
	                        	<label for="exampleInputName2" style="font-weight: normal;">: <s:property value="surgeon"/></label>   
	                        </div>
	            		</div>
					</div>
								  
					<div class="">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;padding:0px;">
							<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">ANESTHESIOLOGIST </h4>
	                        </div>
	                        <div class="col-lg-12 col-md-12 col-xs-12">
	                        	<label for="exampleInputName2" style="font-weight: normal;">: <s:property value="anesthesia" /></label>  
	                        </div>
	                    </div>
					</div>
								  
					<div class="">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;padding:0px;">
							<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">ANESTHESIA </h4>
	                        </div>
	                        <div class="col-lg-12 col-md-12 col-xs-12">
	                        	<label for="exampleInputName2" style="font-weight: normal;">: <s:property value="ansintime"/></label> 
	                        </div>
	                     </div>
					</div>
					
					<div class="">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;padding:0px;">
							<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">OPERATIVE NOTES </h4>
	                        </div>
	                        <div class="col-lg-12 col-md-12 col-xs-12">
	                        	 <div for="exampleInputName2" style="font-weight: normal;">: <%-- <%=ipdotnotes.toString() %> --%></div> 
	                        </div>
	                    </div>
					</div>
				<%-- <%} %> --%>
				
				-->

<s:if test="dischargeStatusId==3">
		</s:if>
		<s:else>
			<%
				if (ipdForm.getOtNotes() != null) {
			%>


			<%-- <div class="" id="notes2">
				<s:hidden name="clientip" />
				<%
					if (false) {
				%>
				<div class="ll">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  "
					style="padding-top: 10px;">
					<h4 class="text-left titleset" style="color: #6699cc;"><s:if test="daycare">PROCEDURE</s:if> NOTES</h4>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  "
					style="margin-top: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
						style="padding-left: 0px; padding-right: 0px;">
						<p><%=ipdForm.getExample().toString()%></p>
					</div>
				</div>
				</div>
				<%
					}
				%>
			</div> --%>

			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " >
			
					<div class=" "
						style="padding-top: 10px;">
						<h4 class="text-left titleset" style="color: #6699cc;" id="otn2"><b>OPERATIVE
							NOTES</b></h4>
					</div>

					<s:if test="otdatesandids.size!=0">
						<div class="">
							<div class="form-group" style="margin-top: 10px;">
								<label for="inputEmail" class="control-label ">Operative
									Date</label>
								<s:iterator value="otdatesandids">
									<p>
										<s:property value="date" />
										&nbsp;&nbsp;&nbsp;<b>Procedure:</b>
										<s:property value="procedurename" />
									</p>
								</s:iterator>
							</div>
						</div>
					</s:if>
					
						<%
							if (ipdForm.getAppointmentText() != null) {
						%>
						<div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0">
						<div class="form-group">
								<label style="padding: 5px"><b>OPERATIVE PROCEDURE</b></label>
								<p for="exampleInputName2"
									style="margin: 0 0 0px; font-size: 13px; text-align: justify; padding: 5px">
									<%=ipdForm.getAppointmentText().toString()%></p>
								<%-- <td><s:property value="operatingsurgeon"/></td> --%>
						</div>
						</div>
						<%
							}
						%>
						<%Bed treatmentEpisode=(Bed) request.getAttribute("treatmentEpisodeData"); %>
						<%
						ipdForm.setSurgeon(DateTimeUtils.isNull(ipdForm.getSurgeon()));
							if (!ipdForm.getSurgeon().equals("")) {
						%>
						<div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0">
							<div class="form-group">
								<label style="padding: 5px"><b>OPERATING SURGEON</b></label>
								<p for="exampleInputName2"
									style="margin: 0 0 0px; font-size: 13px; text-align: justify; padding: 5px">
									<%=ipdForm.getSurgeon().toString()%></p>
									
									

							</div>
						</div>
						<%
							}else if(!treatmentEpisode.getOt_manual_surg().equals("")){
						%>
							<div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0">
							<div class="form-group">
								<label style="padding: 5px"><b>OPERATING SURGEON</b></label>
								<p for="exampleInputName2"
									style="margin: 0 0 0px; font-size: 13px; text-align: justify; padding: 5px">
									<%=treatmentEpisode.getOt_manual_surg().toString()%></p>
									
									

							</div>
						</div>
						<%} %>

						<%
						ipdForm.setAnesthesiologist(DateTimeUtils.isNull(ipdForm.getAnesthesiologist()));
							if (!ipdForm.getAnesthesiologist().equals("")) {
						%>
						<div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0">
							<div class="form-group">
								<label style="padding: 5px"><b>ANESTHESIOLOGIST</b></label>
								<p for="exampleInputName2"
									style="margin: 0 0 0px; font-size: 13px; text-align: justify; padding: 5px">
									<%=ipdForm.getAnesthesiologist().toString()%></p>
							</div>
						</div>
						<%
							}else if(!treatmentEpisode.getOt_manual_anesthesio().equals("")){
						%>
						<div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0">
							<div class="form-group">
								<label style="padding: 5px"><b>ANESTHESIOLOGIST</b></label>
								<p for="exampleInputName2"
									style="margin: 0 0 0px; font-size: 13px; text-align: justify; padding: 5px">
									<%=treatmentEpisode.getOt_manual_anesthesio().toString()%></p>
							</div>
						</div>
						<%} %>
						
						
						<%
							if (ipdForm.getAnesthesia() != null) {
						%>
						<div class="col-lg-3 col-md-3 col-sm-3" style="padding: 0">
							<div class="form-group">
								<label style="padding: 5px"><b>ANESTHESIA TYPE</b></label>
								<p for="exampleInputName2"
									style="margin: 0 0 0px; font-size: 13px; text-align: justify; padding: 5px">
									<%=ipdForm.getAnesthesia().toString()%></p>
							</div>
						</div>
						<%
							}
						%>
					
					<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0">
						
						<p>	<%=ipdForm.getOtNotes().toString()%></p>
					</div>


				</div>



			<%
				}
			%>

			<%-- <s:if test="otnoteslist.empty">
			</s:if>
			<s:else>
				<!-- <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">OPERATIVE NOTES </h4>
	                        </div> -->
			 <div class="col-lg-12 col-md-12 col-xs-12">
	                        	<!--  <div class="form-group" style="margin-top:10px;">
											  <label for="inputEmail" class="control-label ">Operative Notes</label> -->
	                        			 		<s:iterator value="otnoteslist">
													<p><s:property value="otnotes"/></p>
												</s:iterator>
											<!-- </div>  -->
	                        </div>
			</s:else> --%>





		</s:else>



       <s:if test="dischargeStatusId==3">
			<%
				if (ipdForm.getDeathnote() != null) {
			%>
			<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
					style="padding-top: 10px;">
					<h4 class="text-left titleset" style="color: #6699cc;"><b>CAUSE
						OF DEATH</b></h4>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
					style="margin-top: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
						style="padding-left: 0px; padding-right: 0px;">
						<p><%=ipdForm.getDeathnote().toString()%></p>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</s:if>

		<%
			if (ipdForm.getHospitalcourse() != null) {
		%>
		<%-- <div class="ll3">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
				style="padding-top: 10px;">
				<h4 class="text-left titleset" style="color: #6699cc;">HOSPITAL
					COURSE</h4>
			</div>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
				style="margin-top: 0px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
					style="padding-left: 0px; padding-right: 0px;">
					<p><%=ipdForm.getHospitalcourse().toString()%></p>
				</div>
			</div>
		</div> --%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<h4 class="text-left titleset" style="color: #6699cc;"><b>HOSPITAL
					COURSE</b></h4>
		<p><%=ipdForm.getHospitalcourse().toString()%></p>
		</div>
		<%
			}
		%>
       
       
       
       <%
			if (ipdForm.getTreatmentgiven() != null||treatmentivendischargePriscList.size()>0) {
		%>
	<%-- 	<div class="ll3">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
			style="padding-top: 10px;">
			<h4 class="text-left titleset" style="color: #6699cc;">TREATMENT
				GIVEN</h4>
		</div>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  "
			style="margin-top: 0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
				style="padding-left: 0px; padding-right: 0px;">
				<p><%=ipdForm.getTreatmentgiven().toString()%></p>
			</div>
		</div>
		</div> --%>
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<h4 class="text-left titleset" style="color: #6699cc;"><b>TREATMENT
				GIVEN</b></h4>
				
		
						
							<div class="form-group">
								<div style="padding-left: 0px;">
									<s:if test="treatmentivendischargePriscListt.size>0">
										<table class="table table-bordered">
											<thead style="text-transform: uppercase">
												<tr class="headings">
													<th style="width: 5%;">Sr.</th>
													<th>Medicine</th>
													<th>Route</th>
													<s:if test="strengthflag==true">
														<th>Strength</th>
													</s:if>
													<th>Dosage</th>
													<th>Days</th>
													
													
													<th>Quantity</th>
													<s:if test="remarkflag==true">
														<th>Remark</th>
													</s:if>
												</tr>
											</thead>
											<tbody>
												<%-- <s:iterator value="dischargePriscList">
												   				<tr>
													   				<td><s:property value="mdicinenametxt"/></td>
													   				<td><s:property value="priscdose"/></td>
													   				<td><s:property value="priscdays"/> Days</td>
													   				<td><s:property value="dosenotes"/></td>
												   				</tr>
												   			</s:iterator> --%>
												<%
													int priscsrno = 1;
												%>
												<%
													for (Priscription priscription : treatmentivendischargePriscList) {
												%>

												<tr>
													<td><%=priscsrno%></td>
													<td class="uppercaseirf"><%=priscription.getMdicinenametxt()%></td>
													<%priscription.setDosenotes(DateTimeUtils.isNull(priscription.getDosenotes())); %>
													<td><%=priscription.getDosenotes().toString()%></td>
													<s:if test="strengthflag==true">
														<td><%=priscription.getStrengthnew()%><%=priscription.getUnitextension()%></td>
													</s:if>
													<td><%=priscription.getRegional()%></td>
													<td><%=priscription.getPriscdays()%> Days</td>
													
													
													<td><%=priscription.getDr_qty()%></td>

													<s:if test="remarkflag==true">
														<td><%=priscription.getPriscindivisualremark()%></td>
													</s:if>
													<%
														priscsrno++;
													%>
												</tr>

												<%
													}
												%>

											</tbody>
										</table>
									</s:if>		
				
				
		<p><%ipdForm.setTreatmentgiven(DateTimeUtils.isNull(ipdForm.getTreatmentgiven()));%><%=ipdForm.getTreatmentgiven().toString()%></p>
		</div>
		</div>
		</div>
		<%
			}
		%>
       


<%
			if (DateTimeUtils.isNull(ipdForm.getInvestigation()) != "" || DateTimeUtils.isNull(ipdForm.getDis_investigation())!= ""){
		%>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		
				<h4 class="text-left titleset" style="color: #6699cc;"><b>INVESTIGATION</b>
				</h4>
		        <%
				if (loginInfo.isParihar()) {
			    %>
			    <table class="" border="1">
			    <s:iterator value="Invtypelist">
									
									<tr>
									<s:if test="Investigation_name!=null ">
									<td>
									<s:property value="Inv_date" />:-<b><s:property value="Investigation_type" />:-</b>
									<s:property value="Investigation_name" />
									</td>
									</s:if>
									</tr>
									
								</s:iterator>
								<td><%=ipdForm.getDis_investigation().toString()%></td>
							</table>
							
							
							
			    
			   <%
				 } else {
			   %>
					<p><%=ipdForm.getInvestigation().toString()%></p>
					
				<%
			   }
		    %>	
			
		</div>


		<%
			}
		%>
		
		
		
		
		<s:if test="dischargeStatusId==3">
		</s:if>
		<s:else>




			<%
				if (ipdForm.getFindondischarge() != null) {
			%>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<h4 class="text-left titleset" style="color: #6699cc;"><b>FINDING
					ON DISCHARGE</b></h4>
		
		
					<p><%=ipdForm.getFindondischarge().toString()%></p>
				
		</div>
			<%
				}
			%>
			<%
			boolean kunalprisc=true;
					if (ipdForm.getKunal_manual_medicine_text() == null) {
									ipdForm.setKunal_manual_medicine_text("");
								}
					if(ipdForm.getKunal_manual_medicine_text().equals("")){
						kunalprisc=false;
					}
						
				%>
			<%if(dischargePriscList.size()>0||kunalprisc){ %>
		<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			
					<%if((dischargePriscList.size()>0||!(DateTimeUtils.removeBreaks(ipdForm.getKunal_manual_medicine_text()).equals("")))){ %>
						<h4 class="text-left titleset" style="color: #6699cc;"><b>TREATMENT
							ADVICE</b></h4>
				<%} %>
					<%--  <%if(!ipdForm.getKunal_manual_medicine_text().equals("")){ %>
	                       <h4 class="text-left titleset" style="color:#6699cc;">TREATMENT ADVICE </h4>
	                       <%} %> --%>
			
				
				
						<s:if test="dischargePriscList.size>0">
							<div class="form-group">
								<div style="padding-left: 0px;">
									<s:if test="dischargePriscList.size>0">
										<table class="table table-bordered">
											<thead style="text-transform: uppercase">
												<tr class="headings">
													<th style="width: 5%;">Sr.</th>
													<th>Medicine</th>
													<th>Route</th>
													<s:if test="strengthflag==true">
														<th>Strength</th>
													</s:if>
													<th>Dosage</th>
													<th>Days</th>
													
													
													<th>Quantity</th>
													<s:if test="remarkflag==true">
														<th>Remark</th>
													</s:if>
												</tr>
											</thead>
											<tbody>
												<%-- <s:iterator value="dischargePriscList">
												   				<tr>
													   				<td><s:property value="mdicinenametxt"/></td>
													   				<td><s:property value="priscdose"/></td>
													   				<td><s:property value="priscdays"/> Days</td>
													   				<td><s:property value="dosenotes"/></td>
												   				</tr>
												   			</s:iterator> --%>
												<%
													int priscsrno = 1;
												%>
												<%
													for (Priscription priscription : dischargePriscList) {
												%>

												<tr>
													<td><%=priscsrno%></td>
													<td class="uppercaseirf <%=respiraMedText%>"><%=priscription.getMdicinenametxt()%></td>
													<%priscription.setDosenotes(DateTimeUtils.isNull(priscription.getDosenotes())); %>
													<td><%=priscription.getDosenotes().toString()%></td>
													<s:if test="strengthflag==true">
														<td><%=priscription.getStrengthnew()%><%=priscription.getUnitextension()%></td>
													</s:if>
													<td><%=priscription.getRegional()%></td>
													<td><%=priscription.getPriscdays()%> Days</td>
													
													
													<td><%=priscription.getDr_qty()%></td>

													<s:if test="remarkflag==true">
														<td><%=priscription.getPriscindivisualremark()%></td>
													</s:if>
													<%
														priscsrno++;
													%>
												</tr>

												<%
													}
												%>

											</tbody>
										</table>
									</s:if>
									<div class="form-group">
										<p>
											<s:property value="advoice" />
										</p>
									</div>
								</div>

							</div>
						</s:if>
						<div style="margin-top: 10px;">
							<%
								if (!ipdForm.getKunal_manual_medicine_text().equals("")) {
							%>
							<%=ipdForm.getKunal_manual_medicine_text().toString()%>
							<%
								}
							%>
						</div>
				
			</div>
			
			<%} %>


			

		</s:else>
	
		
		
		
		
		
		
		
		
		<%
				if (ipdForm.getEmergencydetail() != null) {
			%>
			<%
				if (!ipdForm.getEmergencydetail().toString().equals("")) {
			%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px">
				<h4 class="text-left titleset" style="color: #6699cc;"><b>How and When Get Emergency
					Details</b></h4>
		
		
					<p><%=ipdForm.getEmergencydetail().toString()%></p>
				
		</div>
		<%
			}
		%>
		<%
			}
		%>
		
            <%
				if (ipdForm.getDiscadvnotes() !=null) {
			            if(!ipdForm.getDiscadvnotes().toString().equals("")){
			%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px">
			
					<h4 class="text-left titleset" style="color: #6699cc;"><b>FOLLOW
						UP INSTRUCTIONS</b></h4>
		
						<p><%=ipdForm.getDiscadvnotes().toString()%></p>
				
			</div>
            <%
			            }}
			%>

<%
						if (ipd.getExample() != null) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<b for="exampleInputName2"> <s:if test="daycare">PROCEDURE</s:if> NOTES </b>
							</div>

							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style="padding-left: 0px;">
								<div class="form-group">
									<p><%=ipd.getExample()%></p>
								</div>

							</div>
							
						</div>
						
					</div>
					
					<%
						}
					%>

<div class="">

			<div class="col-lg-12 col-md-12 col-xs-12 " style="padding: 0px;padding-top: 60px;">
				<%

				if (loginInfo.isDischarge_new()) {
				%>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padsign" style="padding-top: 10px !important;margin-left: -10px !important">


				<s:if test="mlccase==1">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-left">
							<span><s:property value="doctor_name" /><br>
								<%
									if (loginInfo.isDischarge_new()) {
								%> <s:property
									value="qualification" /><%
									}
								%></span><br>
							<span>[FOR <s:property value="clinicName" />]
							</span> <br>
							<br>
						</div>
					</s:if>
					<s:else>
						<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-left" style="padding-left: 7px">
						<span><s:property value="doctor_name" /></span><br>
						
						<s:if test="qualification==null||qualification==''"></s:if><s:else><s:iterator value="qualificationList">  <s:property value="name"/> <br></s:iterator></s:else></span>
						 <br>
							<%-- <span><s:property value="supportiveDoctorName"/><br><s:property value="supportiveQualification"/></span> --%>
							<br>
							<span>[FOR <s:property value="clinicName" />]
							</span> <br>
							<br>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3  text-left">
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4  text-right">
					<span>Authorised Sign</span>
						</div>
					</s:else>


					<s:if test="mlccase==1">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-centre">
							<span><s:property value="mlcrefdoctor" /></span><br>
							<span>[FOR <s:property value="clinicName" />]
							</span> <br>
							<br>
						</div>
					</s:if>
					<s:else>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-centre hh">
							
						</div>
					</s:else>

					
					<s:if test="mlccase==1">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						</div>
					</s:if>
					<s:else>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<span><s:property value="supportiveDoctorName" /><br>
							<s:property value="supportiveQualification" /></span>
						</div>
					</s:else>
					
						<s:if test="mlccase==1">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4  text-right">
					<span>Authorised Sign</span>
						</div>
						</s:if>
				</div>
				<%
					}
				String kunalside="text-left";
				if(loginInfo.getIskunal()==1){
					kunalside="text-right";
				}
				%>
<div class='col-lg-12 col-md-12'>
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 "
					style="padding-top: 0px;">
					
					<%-- <span>Authorised Sign</span> --%>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3  "
					style="padding-top: 0px;">
					
					
					<%-- <span>Authorised Sign</span> --%>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5 text-right"
					style="padding-top: 0px;">
					<!-- <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6" > -->
					<br>
					<%
						if (loginInfo.isDischarge_new()) {
					%>
					<span >Name & Signature of Patient / Attendant</span>
						
					<%
						}
					%>
				</div>	
				
				<div class='col-lg-12 col-md-12'>
					<br>
					<br>
					
					
					 <%if(loginInfo.getClinicUserid().equals("nelson")){ %>
					<span>Nature of illness, prognosis, risk & complications of disease explained to the parents & relatives in detail.</span><br>
					<span>All the above medicines are explained to me and I understood them. All investigations Report including Radiology report & film are handed over to me.</span><br>
					<span>सभी जांच रिपोर्ट और रेडियोलॉजिकल रिपोर्ट और फिल्म मुझे सौंपी गई है  |</span>
					<br><br><br><br>
					<%} %>
					<s:if test="!nicuaccess">
					
					<span style="margin-right: 10px">Admitted By: <s:property
							value="addmitedbyuserid" /> <s:property value="admissiondate" /></span><br>
					<s:if test="dischargeStatusId==3">
						<span style="margin-right: 10px">Discharge By: <s:property
								value="dischargebyid" /> <s:property value="dischargedate" /></span>
						<br>
					</s:if>
					<%if(!loginInfo.isBalgopal()){ %>
					<span style="margin-right: 10px">Printed By: <s:property
							value="printedBy" /></span><br>	<%} %>
						</s:if>
					<%if(!loginInfo.isBalgopal()){ %>	
					<span class='' style="margin-right: 10px">Updated By: <s:property value="dischargteLastUpdatedId" /></span>	<br>
					<span class='' style="margin-right: 10px">Ended By: <s:property value="enddischargename" /></span>	
					<%} %>
					</div>
					
				</div>
				</div>
				
<div class="col-lg-12 col-md-12 hidden-print"
					style="margin-bottom: 10px; padding: 0px;">
					<div class="">
						<div class="col-lg-12 col-md-12" style="padding: 0px;">
							<input type="button" class="btn btn-primary savebtn savebigbtn"
								value="Print" onclick="printpage()">
						</div>
					</div>
				</div>
			</div>
					</div>
<div class="<%=verification%> hidden">
			<div class="col-lg-12 col-md-12 col-xs-12 " style="padding: 0px;">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="padding: 0px;">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-left">
						<div class="form-group"
							style="border: 1px solid #000; padding: 8px 0px 8px 15px;">
							<p>Name of RMO / Register</p>
							<br>
							<p>Signature:___________________
								&nbsp;Date:__________________</p>
							<br>
							<p>Time:_______________________</p>
							<br>
						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-left">
						<div class="form-group"
							style="border: 1px solid #000; padding: 8px 0px 8px 15px;">
							<p>History verified by Consultant</p>
							<br>
							<p>Name:___________________
								&nbsp;Signature:__________________</p>
							<br>
							<p>Date:_______________________</p>
							<br>
						</div>
					</div>
				</div>
			</div>

		</div>

		

		</div>

</s:form>
<%-- <div class="container-fluid hidden">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
			<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
				<form>
					<div class="form-group">
						<label for="inputEmail" class="control-label">Admitting
							Consultant</label>
						<p class="help-block">
							<s:property value="practitionerid" />
						</p>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="control-label">Referred By</label>
						<p class="help-block"><%=ipd.getRefferedby()%></p>

					</div>
					<div class="form-group">
						<label for="inputEmail" class="control-label">Admission
							For</label>
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
				<form>



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
				<form>
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
</div> --%>

		<script>
			function showhide() {
				var div = document.getElementById("newpost");
				if (div.style.display !== "none") {
					div.style.display = "none";
				} else {
					div.style.display = "block";
				}
			}
		</script>

		<script type="text/javascript">
			$(function() {
				$(".font-button").bind("click", function() {
					var size = parseInt($('body').css("font-size"));
					if ($(this).hasClass("plus")) {
						size = size + 2;
					} else {
						size = size - 2;
						if (size <= 10) {
							size = 10;
						}
					}
					$('body').css("font-size", size);
				});
			});
		</script>
</body>
</html>
<%
	}
%>
