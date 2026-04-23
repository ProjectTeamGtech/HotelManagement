<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<s:hidden name='regno' id='uhiid'></s:hidden>

<%

LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
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


Bed newCardFields=(Bed) request.getAttribute("newCardFields");
%>
<%
	IpdForm ipdForm = (IpdForm) session.getAttribute("dischargeddata");
%>
<%
	
%>
<%
	Bed ipd = (Bed) session.getAttribute("bed");
if(ipd==null){
	ipd=new Bed();
}
ipd.setMaternal_history(DateTimeUtils.removeBreaks(ipd.getMaternal_history()));
ipd.setPerinatal_history(DateTimeUtils.removeBreaks(ipd.getPerinatal_history()));
ipd.setBirthhist((DateTimeUtils.removeBreaks(ipd.getBirthhist())));
	
%>


<!DOCTYPE html>
<%@page import="com.apm.Ipd.web.form.IpdForm"%>
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
<script>



function showhide()
{
      var div = document.getElementById("newpost");
if (div.style.display !== "none") {
   div.style.display = "none";
}
else {
   div.style.display = "block";
}
}
</script>
<style>
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

/* #otn1{
margin-left: -25px !important;
pad
} */


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
	/* #invstgg{ padding-top:-1px;} */
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
	height: 155px;
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

</script>

<body>

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
<% nicuaccess="hidden"; %>
</s:if>


<!-- MAin BODY -->
	<div class="col-lg-12 col-xs-12 col-md-12 " style="padding: 0px;">
			<div class=" letterheadhgt" style="height: 155px;">
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
				
				
				
				
				
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor  dischargecardbgni">
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


							<span><b></b> <s:property value="doctor_name" /> <%
 	if (!ipd.getDepartment().equals("0")) {
 %>(<%=ipd.getDepartment()%>)<%
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
							<b for="inputEmail3" class="control-label"> Allied Consultant</b>
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
										<span><b>:</b> <s:property value="age" /></span>
									</div>
								</div>
							</s:if>
						</div>
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
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=pediatric%>">
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
							class="col-lg-12 col-md-12 col-xs-12 col-sm-12  <%=pediatric%>">
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



					</div>
				</div>
				
								
				
			


			</div>
			
			
		</div>	
		
		<!-- Last -->	
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

				</div>
			</div>
		</div>
		<% ArrayList<String> ipdCondtitionList1 = (ArrayList<String>) request.getAttribute("finalConditions");
		
		if(ipdCondtitionList1==null){
			ipdCondtitionList1=new ArrayList<String>();
		}
		String diagdiv="";
		%>
		
		<%if (ipdForm.getKunal_final_diagnosis_text() == null) {
			ipdForm.setKunal_final_diagnosis_text("");
		} %>	
		<%if(ipdCondtitionList1.size()==0&&DateTimeUtils.removeBreaks(ipdForm.getKunal_final_diagnosis_text()).equals(""))
		{
			diagdiv="hidden";
		}	
			
		%>	
			
			
			<div class=" Diagnosisdiv <%=diagdiv%>">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
				style="margin-top: 10px;">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left"
					style="padding-left: 0px; padding-right: 0px;">
					<table class="table" style="width: 100%;">
						<thead style="color: #6699cc;">
							<tr>
								<td style="width: 100%;">
									<h4 class="text-left titleset"><b> DIAGNOSIS<s:if test="dischargeStatusId==3"> / CAUSE OF DEATH</s:if></b></h4>
								</td>
								<td style="" class="hidden">
									<h4 class="text-left titleset" style="color: #6699cc;"><b>PROVISIONAL
										DIAGNOSIS</b></h4>
								</td>
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
					<br>
				</div>
				<br>
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
		<h4 class="text-left titleset" style="color: #6699cc;">REASON FOR <%=islamadama %>	</h4>
		<div class=" ">
		<p><%=ipdForm.getLamadamareason() %></p>
		</div>	
	</div>
<%} %>
		<br>
		<br>
		
<%
String kunalnew="";
if(loginInfo.getIskunal()==1){
	kunalnew="CASE";
}
%>


<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
				<h4 class="text-left titleset" style="color: #6699cc;"><%=kunalnew %><b> <s:if test="dischargeStatusId==3">DEATH</s:if>  SUMMARY</b>
				</h4>
</div>				
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden"
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
		
		
		
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">		
			
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
					if (ipd.getPresentillness() != null) {
				%>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden"
					style="padding: 0px;">
					<div class="form-group">
						<b>HISTORY OF PRESENT ILLNESS </b>
					</div>
				</div>
				<div class="hidden" style="padding-right: 2px;">
					<div class="form-group">
						<p><%=ipd.getPresentillness()%></p>
						<%--  <div  style="font-weight: normal;text-align:justify;"><%=ipd.getPresentillness() %></div>   --%>
					</div>
				</div>
			
			<%
				}
			%>
			
</div>	



<%if (!DateTimeUtils.removeBreaks(ipd.getChiefcomplains()).equals("")) {%>

				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<h4 class="text-left titleset" style="color: #6699cc;"> <b>CLINICAL PRESENTATION ON ADMISSION</b></h4>
				</div>

				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">	
					
					<div class="form-group">
						<p><%=ipd.getChiefcomplains()%></p>
					</div>

				</div>

<%}%>			
<s:if test="dischargeStatusId==3">
</s:if>	

<s:else>
<!--SYSTEMIC REVIEW -->
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
										if (ipd.getFpcondition().equals("No")) {
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

<!-- HISTORY  -->
<div class="histydiv">
			<%
			boolean hisflag=true;
			if((DateTimeUtils.removeBreaks(ipd.getPasthistory()).equals(""))&&(DateTimeUtils.removeBreaks(ipd.getFamilyhist()).equals(""))&&(DateTimeUtils.removeBreaks(ipd.getSurgicalnotes()).equals(""))&&DateTimeUtils.removeBreaks(ipd.getPersonalhist()).equals("")&&DateTimeUtils.removeBreaks(ipd.getExample()).equals("")){
				hisflag=false;
			}
			
				if (true) {
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
					
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
<%
boolean pastflag=true;
if(newCardFields.getPast_hist_HTN().equals("")&&newCardFields.getPast_hist_DM().equals("")&&newCardFields.getPast_hist_IHD().equals("")&&newCardFields.getPast_hist_br_asthama().equals("")&&newCardFields.getPast_hist_CVE().equals("")&&newCardFields.getPast_hist_COAD().equals("")&&newCardFields.getPast_hist_COAD().equals("")&&newCardFields.getPast_hist_Other().equals("")){
	pastflag=false;
}
	if (true) {
%>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="border-left: 3px solid ;">
					<b>PAST HISTORY</b>
						<div class="" style="padding-bottom: 10px;">
							
							<div class="pstnew">
							
							<%if(newCardFields.getPast_hist_HTN().equals("1")){ %>
							<div class='pst'><span class='psth'>HTN : Yes</span> 
							<%if(!newCardFields.getPast_hist_HTN_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPast_hist_HTN_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>HTN : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPast_hist_DM().equals("1")){ %>
							<div class='pst'><span class='psth'>DM : Yes</span> 
							<%if(!newCardFields.getPast_hist_DM_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPast_hist_DM_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>DM : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPast_hist_IHD().equals("1")){ %>
							<div class='pst'><span class='psth'>IHD : Yes</span> 
							<%if(!newCardFields.getPast_hist_IHD_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPast_hist_IHD_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>IHD : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPast_hist_CVE().equals("1")){ %>
							<div class='pst'><span class='psth'>CVE : Yes</span> 
							<%if(!newCardFields.getPast_hist_CVE_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPast_hist_CVE_text() %></span><%}%>
							</div>	
							<%}else{%>
							<div class='pst'><span class='psth'>CVE : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPast_hist_br_asthama().equals("1")){ %>
							<div class='pst'><span class='psth'>Br. Asthama : Yes</span> 
							<%if(!newCardFields.getPast_hist_br_asthama_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPast_hist_br_asthama_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>Br. Asthama : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPast_hist_COAD().equals("1")){ %>
							<div class='pst'><span class='psth'>COAD : Yes</span> 
							<%if(!newCardFields.getPast_hist_COAD_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPast_hist_COAD_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>COAD : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPast_hist_Thyroid().equals("1")){ %>
							<div class='pst'><span class='psth'>Thyroid : Yes</span> 
							<%if(!newCardFields.getPast_hist_Thyroid_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPast_hist_Thyroid_text() %></span></div>	<%}%>
							<%}else{%>
							<div class='pst'><span class='psth'>Thyroid : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPast_hist_Other().equals("1")){ %>
							<div class='pst'><span  class='psth'>Others : Yes</span> 
							<%if(!newCardFields.getPast_hist_Other_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPast_hist_Other_text() %></span></div>	<%}%>
							<%}else{%>
							<div class='pst'><span class='psth'>Others : No</span></div> 							
							<%} %>
							
							
							</div>	
						</div>		
					</div>
					
					<%
						}
					%>
					<%
					boolean personalhflag=true;
					if(newCardFields.getPerson_hist_Smoking().equals("")&&newCardFields.getPerson_hist_Alchohol().equals("")&&newCardFields.getPerson_hist_Bowel_Bladder().equals("")&&newCardFields.getPerson_hist_Sleep().equals("")&&newCardFields.getPerson_hist_Tobacco().equals("")&&newCardFields.getPerson_hist_OtherAddt().equals("")){
						personalhflag=false;
					}
					
						if (true) {
					%>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"
						style="padding: 0px;border-left: 3px solid; ">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<b>	PERSONAL HISTORY</b> 
							

						
									
							<%if(newCardFields.getPerson_hist_Smoking().equals("1")){ %>
							<div class='pst'><span class='psth'>Smoking : Yes</span> 
							<%if(!newCardFields.getPerson_hist_Smoking_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPerson_hist_Smoking_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>Smoking : No</span></div> 							
							<%} %>							
							
							
							<%if(newCardFields.getPerson_hist_Alchohol().equals("1")){ %>
							<div class='pst'><span class='psth'>Alchohol : Yes</span> 
							<%if(!newCardFields.getPerson_hist_Alchohol_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPerson_hist_Alchohol_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>Alchohol : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPerson_hist_Bowel_Bladder().equals("1")){ %>
							<div class='pst'><span class='psth'>Bowel Bladder : Yes</span> 
							<%if(!newCardFields.getPerson_hist_Bowel_Bladder_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPerson_hist_Bowel_Bladder_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>Bowel Bladder : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPerson_hist_Sleep().equals("1")){ %>
							<div class='pst'><span class='psth'>Sleep : Yes</span> 
							<%if(!newCardFields.getPerson_hist_Sleep_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPerson_hist_Sleep_text() %></span><%}%>
							</div>	
							<%}else{%>
							<div class='pst'><span class='psth'>Sleep : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPerson_hist_Tobacco().equals("1")){ %>
							<div class='pst'><span class='psth'>Tobacco : Yes</span> 
							<%if(!newCardFields.getPerson_hist_Tobacco_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPerson_hist_Tobacco_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>Tobacco : No</span></div> 							
							<%} %>
							
							<%if(newCardFields.getPerson_hist_OtherAddt().equals("1")){ %>
							<div class='pst'><span class='psth'>Other Addt : Yes</span> 
							<%if(!newCardFields.getPerson_hist_OtherAddt_text().equals("")){ %> <span class='spst'>Reason : <%=newCardFields.getPerson_hist_OtherAddt_text() %></span>	<%}%>
							</div>
							<%}else{%>
							<div class='pst'><span class='psth'>Other Addt : No</span></div> 							
							<%} %>
							
						
							
						</div>		
					</div>
					<%
						}
					%>
					
	<%
	boolean obs_gyn_flag=true;
	if(newCardFields.getObng_menstrual_hist().equals("")&&newCardFields.getObng_gpla().equals("")&&newCardFields.getObng_tubesctomy().equals("")&&newCardFields.getObng_lmp().equals("")){
		obs_gyn_flag=false;
	}
	if(obs_gyn_flag){
	%>					
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="border-left: 3px solid;">
						
							<div class="form-group">
								<b >   OBSTETRICS & GYNECOLOGY    </b>
							</div>
							
							
	<%if(!newCardFields.getObng_menstrual_hist().equals("")){ %> <div class='othh'>Menstrual History : <%=newCardFields.getObng_menstrual_hist() %></div>	<%}%>
	<%if(!newCardFields.getObng_gpla().equals("")){ %> <div class='othh'>G P L A : <%=newCardFields.getObng_gpla() %></div>	<%}%>
	<%if(!newCardFields.getObng_tubesctomy().equals("")){ %> <div class='othh'>Tubesctomy : <%=newCardFields.getObng_tubesctomy() %></div>	<%}%>
	<%if(!newCardFields.getObng_lmp().equals("")){ %> <div class='othh'>LMP : <%=newCardFields.getObng_lmp() %></div>	<%}%>
	
							
							
							
						
		</div>				
	<%} %>				
</div>					
		<%
			if (true) {
		%>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-top: 10px;">
		
				<div class="form-group">
					<b for="exampleInputName2">FAMILY HISTORY</b>
				</div>

				<div class="" style="padding-left: 0px;">
					<div class="form-group hidden">
						<p><%=ipd.getFamilyhist()%></p>
					</div>
					
					<%if(newCardFields.getFm_hist_hypertension().equals("1")){ %>
						<div class='pst'><span class='psth'>Hypertension : Yes</span> &nbsp;&nbsp;<%=newCardFields.getFm_hist_hypertension_text() %></div>
					 <%}else{%>
					 	<div class='pst'><span class='psth'>Hypertension : No</span></div> 							
					<%} %>
					
					
					<%if(newCardFields.getFm_hist_asthma().equals("1")){ %>
						<div class='pst'><span class='psth'>Asthma : Yes</span> &nbsp;&nbsp;<%=newCardFields.getFm_hist_asthma_text() %></div>
					<%}else{%>
						<div class='pst'><span class='psth'>Asthma : No</span></div> 							
					<%} %>
					
					
					<%if(newCardFields.getFm_hist_heart_disease().equals("1")){ %>
						<div class='pst'><span class='psth'>Heart Disease : Yes</span> &nbsp;&nbsp;<%=newCardFields.getFm_hist_heart_disease_text() %></div>
					<%}else{%>
						<div class='pst'><span class='psth'>Heart Disease : No</span></div> 							
					<%} %>
					
					
					<%if(newCardFields.getFm_hist_stroke().equals("1")){ %>
						<div class='pst'><span class='psth'>Stroke : Yes</span>&nbsp;&nbsp; <%=newCardFields.getFm_hist_stroke_text() %></div>
					<%}else{%>
						<div class='pst'><span class='psth'>Stroke : No</span></div> 							
					<%} %>
					
					
					<%if(newCardFields.getFm_hist_hypertension().equals("1")){ %>
						<div class='pst'><span class='psth'>Hypertension : Yes</span> &nbsp;&nbsp; <%=newCardFields.getFm_hist_hypertension_text() %></div>
					<%}else{%>
						<div class='pst'><span class='psth'>Hypertension : No</span></div> 							
					<%} %>
					
					
					<%if(newCardFields.getFm_hist_diabetes().equals("1")){ %>
						<div class='pst'><span class='psth'>Diabetes : Yes</span> &nbsp;&nbsp; <%=newCardFields.getFm_hist_diabetes_text() %></div>
					<%}else{%>
						<div class='pst'><span class='psth'>Diabetes : No</span></div> 							
					<%} %>
					
					<%if(newCardFields.getFm_hist_arthritis_gout().equals("1")){ %>
						<div class='pst'><span class='psth'>Arthiritis / Gout : Yes</span> &nbsp;&nbsp; <%=newCardFields.getFm_hist_arthritis_gout_text() %> </div>
					<%}else{%>
						<div class='pst'><span class='psth'>Arthiritis / Gout : No</span></div> 							
					<%} %>
					
					<%if(newCardFields.getFm_hist_tuberculosis().equals("1")){ %>
						<div class='pst'><span class='psth'>Tuberculosis : Yes</span>  &nbsp;&nbsp;<%=newCardFields.getFm_hist_tuberculosis_text() %> </div>
					<%}else{%>
						<div class='pst'><span class='psth'>Tuberculosis : No</span></div> 							
					<%} %>
					
					<%if(newCardFields.getFm_hist_cancer().equals("1")){ %>
						<div class='pst'><span class='psth'>Cancer : Yes</span> &nbsp;&nbsp;<%=newCardFields.getFm_hist_cancer_text() %></div>
					<%}else{%>
						<div class='pst'><span class='psth'>Cancer : No</span></div> 							
					<%} %>
					
					<%if(newCardFields.getFm_hist_epilepsy().equals("1")){ %>
						<div class='pst'><span class='psth'>Epilepsy : Yes</span> &nbsp;&nbsp;  <%=newCardFields.getFm_hist_epilepsy_text() %> </div>
					<%}else{%>
						<div class='pst'><span class='psth'>Epilepsy : No</span></div> 							
					<%} %>
					
					<%if(newCardFields.getFm_hist_other_chronic().equals("1")){ %>
						<div class='pst'><span class='psth'>Other Chronic Disease : Yes</span>  &nbsp;&nbsp; <%=newCardFields.getFm_hist_other_chronic_text() %> </div>
					<%}else{%>
						<div class='pst'><span class='psth'>Other Chronic Disease : No</span></div> 							
					<%} %>
				</div>
			
		</div>
		<%
			}
		%>
					
					
					<%
						if (ipd.getSuggestedtrtment() != null) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden"
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
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden"
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
						if (ipd.getSurgicalnotes() != null) {
					%>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden"
						style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<b for="exampleInputName2">SURGICAL NOTES </b>
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
					
					
					
					
				</div>
			</div>
		</div>


	<%ipd.setDiethist(DateTimeUtils.removeBreaks(ipd.getDiethist()));
					ipd.setDevelopmenthist(DateTimeUtils.removeBreaks(ipd.getDevelopmenthist()));
					ipd.setBirthhist(DateTimeUtils.removeBreaks(ipd.getBirthhist()));
					ipd.setEmmunizationhist(DateTimeUtils.removeBreaks(ipd.getEmmunizationhist()));
					
					
	%>

<%
boolean showpedi=true;
if(ipd.getDiethist().equals("")&&ipd.getDevelopmenthist().equals("")&&ipd.getBirthhist().equals("")&&ipd.getEmmunizationhist().equals("")){ %>
<%showpedi=false; %>
<%} %>

<!--PEDIATRIC HISTORY  -->		

<div class=" col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden <%=pediatric%>">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 "
				style="padding: 10px 0px 0px 0px;">
				
				<%
					if (ipd.isPeditric()&&showpedi) {
				%>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  "
					style="padding: 0px;">
					<h4 class="text-left titleset" style="color: #6699cc;"><b>PEDIATRICS HISTORY</b>
						</h4>
				</div>

				<div class="row ">
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
								<label for="exampleInputName2"
									style="text-transform: uppercase;">BIRTH HISTORY</label>
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
								<label for="exampleInputName2"
									style="text-transform: uppercase;">IMMUNIZATION </label>
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
					<%
						}
					%>
				</div>


			</div>

		</div>

<!-- On EXAMINATION  -->
<%
if (ipd.getOnexamination() != null) {
%>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >					
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
<h4 class="text-left titleset" style="color: #6699cc;"><b>EXAMINATION ON ADMISSION </b></h4>

<label><b>GENERAL EXAMINATION :</b></label>


 <div id='vit'>
 <label>Vitals</label>
 <div class='row'>
 <s:iterator value="vitalList">
 <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
 <s:property value="name"/> : <s:property  value="finding"/>   <s:property  value="unit"/>
 </div>
 </s:iterator>
 </div>
 </div>

<%
boolean apearnceflag=true;
if(newCardFields.getApearnace_Pallor().equals("")&&newCardFields.getApearnace_Cynosis().equals("")&&newCardFields.getApearnace_Clubbing().equals("")&&newCardFields.getApearnace_Icterus().equals("")&&newCardFields.getApearnace_ln().equals("")&&newCardFields.getApearnace_Oedema().equals("")){
	apearnceflag=false;
}
if(apearnceflag){
%>
<br>
<div id='appear'>
 <label>Appearance</label>
	<%if(!newCardFields.getApearnace_Pallor().equals("")){ %> <div class='othh'>Pallor : <%=newCardFields.getApearnace_Pallor() %></div>	<%}%>
	<%if(!newCardFields.getApearnace_Cynosis().equals("")){ %> <div class='othh'>Cynosis : <%=newCardFields.getApearnace_Cynosis() %></div>	<%}%>
	<%if(!newCardFields.getApearnace_Clubbing().equals("")){ %> <div class='othh'>Clubbing : <%=newCardFields.getApearnace_Clubbing() %></div>	<%}%>
	<%if(!newCardFields.getApearnace_Icterus().equals("")){ %> <div class='othh'>Icterus : <%=newCardFields.getApearnace_Icterus() %></div>	<%}%>
	<%if(!newCardFields.getApearnace_ln().equals("")){ %> <div class='othh'>L.N. : <%=newCardFields.getApearnace_ln() %></div>	<%}%>
	<%if(!newCardFields.getApearnace_Oedema().equals("")){ %> <div class='othh'>Oedema : <%=newCardFields.getApearnace_Oedema() %></div>	<%}%>
	
							
</div>

<br>
<%} %>


<%if(DateTimeUtils.removeBreaks(ipd.getOnexamination()).equals("")){ %>
 <div id='locex'>
<label>LOCAL EXAMINATION</label>
<p><%=ipd.getOnexamination().toString()%></p>
</div>
<%} %>


<%
boolean flagsysexam=true;
if(newCardFields.getSys_exa_CVS().equals("")&&newCardFields.getSys_exa_RS().equals("")&&newCardFields.getSys_exa_CNS().equals("")&&newCardFields.getSys_exa_PA().equals("")&&newCardFields.getSys_exa_PVPR().equals("")&&newCardFields.getSys_exa_Others().equals("")){
flagsysexam=false;
}
	if(flagsysexam){
	%>
<br>
<div id='sysexa'>
<label>SYSTMATIC EXAMINATION</label>
	<%if(!newCardFields.getSys_exa_CVS().equals("")){ %> <div class='othh'>CVS : <%=newCardFields.getSys_exa_CVS() %></div>	<%}%>
	<%if(!newCardFields.getSys_exa_RS().equals("")){ %> <div class='othh'>RS : <%=newCardFields.getSys_exa_RS() %></div>	<%}%>
	<%if(!newCardFields.getSys_exa_CNS().equals("")){ %> <div class='othh'>CNS : <%=newCardFields.getSys_exa_CNS() %></div>	<%}%>
	<%if(!newCardFields.getSys_exa_PA().equals("")){ %> <div class='othh'>PA : <%=newCardFields.getSys_exa_PA() %></div>	<%}%>
	<%if(!newCardFields.getSys_exa_PVPR().equals("")){ %> <div class='othh'>PV / PR : <%=newCardFields.getSys_exa_PVPR() %></div>	<%}%>
	<%if(!newCardFields.getSys_exa_Others().equals("")){ %> <div class='othh'>Others : <%=newCardFields.getSys_exa_Others() %></div>	<%}%>
	
</div>
<%} %>

<%if(!DateTimeUtils.removeBreaks(newCardFields.getGeneral_other()).equals("")){ %>
<br>
<div id='otherss'>
<label>OTHERS</label>
<p><%=newCardFields.getGeneral_other().toString()%></p>
</div>
<%} %>
</div>






<%
	}
%>


<!--Hospital Course  -->
<%if (ipdForm.getHospitalcourse() != null) {%>	

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<h4 class="text-left titleset" style="color: #6699cc;"><b>COURSE IN HOSPITAL </b></h4>
		<p><%=ipdForm.getHospitalcourse().toString()%></p>
</div>


<%} %>







<!--OT NOTES  -->
<s:if test="dischargeStatusId!=3">
<%
				if (ipdForm.getOtNotes() != null) {
%>


		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 " >
			
					<div class=" "
						style="padding-top: 10px;">
						<h4 class="text-left titleset" style="color: #6699cc;" id="otn2"><b>OPERATION DETAILS
							</b></h4>
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
									
									<%=ipdForm.getAppointmentText()%></p>
								<%-- <td><s:property value="operatingsurgeon"/></td> --%>
						</div>
						</div>
						<%
							}
						%>
						<%
							if (ipdForm.getSurgeon() != null) {
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
							}
						%>

						<%
							if (ipdForm.getAnesthesiologist() != null) {
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
							}
						%>
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

<%} %>
</s:if>			
	

<!--Investigations  -->
	<%if (ipdForm.getInvestigation() != null){%>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<h4 class="text-left titleset" style="color: #6699cc;"><b>INVESTIGATION</b></h4>		
<p><%=ipdForm.getInvestigation().toString()%></p>				
</div>	
	<%} %>		
				
	
<!-- CAUSE OF DEATH -->	
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
			if (ipdForm.getTreatmentgiven() != null||treatmentivendischargePriscList.size()>0) {
	%>	
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
	
	<%} %>	
	
<%if(!(DateTimeUtils.removeBreaks(newCardFields.getLocal_relevant_area()).equals("")&&DateTimeUtils.removeBreaks(newCardFields.getTubes_and_training()).equals(""))){ %>
<!-- CONDITION ON DISCHARGE -->		
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<h4 class="text-left titleset" style="color: #6699cc;"><b>CONDITION ON DISCHARGE</b></h4>
		
<%if(!(DateTimeUtils.removeBreaks(newCardFields.getLocal_relevant_area()).equals(""))){%>		
<br>
<label>LOCAL / RELEVENT AREA :(WOUND / DRESSING) </label>		
<p><%=newCardFields.getLocal_relevant_area().toString() %></p>		
<%} %>

<%-- <%if(!(DateTimeUtils.removeBreaks(newCardFields.getTubes_and_training()).equals(""))){%>	 --%>
 <%if(false){%>	
<br>
<label>TUBES & DRAIN :</label>		
<p><%=newCardFields.getTubes_and_training().toString() %></p>		
<%} %>
<br>
</div>
<%} %>

<s:if test="dischargeStatusId!=3">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<%if (ipdForm.getFindondischarge() != null) {%>

		<h4 class="text-left titleset" style="color: #6699cc;"><b>FINDING ON DISCHARGE</b></h4>
<p><%=ipdForm.getFindondischarge().toString()%></p>
	<%}%>
<br>

<%
boolean genCondFlag=true;
if(newCardFields.getGen_cond_Temp().equals("")&&newCardFields.getGen_cond_Pulse().equals("")&&newCardFields.getGen_cond_BP().equals("")&&newCardFields.getGen_cond_CVS().equals("")&&newCardFields.getGen_cond_PS().equals("")&&newCardFields.getGen_cond_CNS().equals("")){
	genCondFlag=false;
}
if(genCondFlag){
%>
<!-- Gneral Condition  -->
<label>GENEARAL CONDIDTION</label>

	<%if(!newCardFields.getGen_cond_Temp().equals("")){ %> <div class='othh'>Temp : <%=newCardFields.getGen_cond_Temp() %></div>	<%}%>
	<%if(!newCardFields.getGen_cond_Pulse().equals("")){ %> <div class='othh'>Pulse : <%=newCardFields.getGen_cond_Pulse() %></div>	<%}%>
	<%if(!newCardFields.getGen_cond_BP().equals("")){ %> <div class='othh'>BP : <%=newCardFields.getGen_cond_BP() %></div>	<%}%>
	<%if(!newCardFields.getGen_cond_CVS().equals("")){ %> <div class='othh'>CVS : <%=newCardFields.getGen_cond_CVS() %></div>	<%}%>
	<%if(!newCardFields.getGen_cond_PS().equals("")){ %> <div class='othh'>PS : <%=newCardFields.getGen_cond_PS() %></div>	<%}%>
	<%if(!newCardFields.getGen_cond_CNS().equals("")){ %> <div class='othh'>CNS : <%=newCardFields.getGen_cond_CNS() %></div>	<%}%>
	
<br>
<%} %>


<%if(!DateTimeUtils.removeBreaks(newCardFields.getLine_tube_drains()).equals("")){ %>
<label>LINES / TUBES/ DRAIN </label>
<p><%=newCardFields.getLine_tube_drains().toString()%></p>
<%} %>
</div>	


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
						<h4 class="text-left titleset" style="color: #6699cc;"><b>DRUG TREATMENT ON DISCHARGE</b></h4>
							
				<%} %>
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
												
												<%
													int priscsrno = 1;
												%>
												<%
													for (Priscription priscription : dischargePriscList) {
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
							<label>TRATMENT CARE ON DISCHARGE</label><br>
							<%=ipdForm.getKunal_manual_medicine_text().toString()%>
							<%
								}
							%>
						</div>
				
			</div>
			
			<%} %>
</s:if>	

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px">
<%if(!DateTimeUtils.removeBreaks(newCardFields.getDietary_advice()).equals("")){ %>
<h4 class="text-left titleset" style="color: #6699cc;"><b><!-- DIETRY --> ADVICE</b></h4>
<br>
<label>DIETRY ADVICE</label>
<p><%=newCardFields.getDietary_advice().toString() %></p>


<br>
<%} %>


<% boolean physio_adv_flag=true;
if(newCardFields.getPhysio_th_adv_Mobilization().equals("")&&newCardFields.getPhysio_th_adv_fallRisk().equals("")&&newCardFields.getPhysio_th_adv_Driving().equals("")&&newCardFields.getPhysio_th_adv_Mobilization().equals("")&&newCardFields.getPhysio_th_adv_sexual_Activity().equals("")){
physio_adv_flag=false;	
}
if(physio_adv_flag){
%>
<label>PHYSIOTHEREPY ADVICE</label>

	<%if(!newCardFields.getPhysio_th_adv_Mobilization().equals("")){ %> <div class='othh'>Mobilization : <%=newCardFields.getPhysio_th_adv_Mobilization() %></div>	<%}%>
	<%if(!newCardFields.getPhysio_th_adv_fallRisk().equals("")){ %> <div class='othh'>Fall Risk  : <%=newCardFields.getPhysio_th_adv_fallRisk() %></div>	<%}%>
	<%if(!newCardFields.getPhysio_th_adv_Driving().equals("")){ %> <div class='othh'>Driving : <%=newCardFields.getPhysio_th_adv_Driving() %></div>	<%}%>
	<%if(!newCardFields.getPhysio_th_adv_sexual_Activity().equals("")){ %> <div class='othh'>Sexual Activity : <%=newCardFields.getPhysio_th_adv_sexual_Activity() %></div>	<%}%>
<br>
<%} %>


<%if (ipd.getExample() != null) {%>
<label> OTHER ADVICE </label>
<p><%=ipd.getExample()%></p>
<%}%>


	
</div>



	<%if (ipdForm.getEmergencydetail() != null) {
			if (!ipdForm.getEmergencydetail().toString().equals("")) {%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px">
				<h4 class="text-left titleset" style="color: #6699cc;"><b>How to Get Emergency Help ?</b></h4>
		<p><%=ipdForm.getEmergencydetail().toString()%></p>
		</div>
		<%}}			
		%>
			
<%if (newCardFields.getWhen_to_get_help() != null) {
			if (!DateTimeUtils.removeBreaks(newCardFields.getWhen_to_get_help()).equals("")) {%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px">
				<h4 class="text-left titleset" style="color: #6699cc;"><b>When to Get Emergency Help ?</b></h4>
		<p><%=newCardFields.getWhen_to_get_help().toString()%></p>
		</div>
		<%}}			
		%>
			




<%if (ipdForm.getDiscadvnotes() != null) {%>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px">
		<h4 class="text-left titleset" style="color: #6699cc;"><b>FOLLOW UP ADVICE </b></h4>
		<p><%=ipdForm.getDiscadvnotes().toString()%></p>		
		</div>
		<%}%>		
	


			
<%if (newCardFields.getCall_for_appointmant() != null) {
			if (!DateTimeUtils.removeBreaks(newCardFields.getCall_for_appointmant()).equals("")) {%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px">
				<h4 class="text-left titleset" style="color: #6699cc;"><b>Call For Appointent :</b></h4>
		<p><%=newCardFields.getCall_for_appointmant().toString()%></p>
		</div>
		<%}}			
		%>
			
			
			
<%if (newCardFields.getConsent_sign() != null) {
			if (!DateTimeUtils.removeBreaks(newCardFields.getConsent_sign()).equals("")) {%>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px">
				<h4 class="text-left titleset" style="color: #6699cc;"><b>Consent Sign :</b></h4>
		<p><%=newCardFields.getConsent_sign().toString()%></p>
		</div>
		<%}}			
		%>
			
						

<script type="text/javascript">

</script>
<!--vERIFICATION   -->	
	<div class="<%=verification%> ">
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
	
	
	<div class='' style="margin-top: 20px;">
	
	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="text-align: left;">
	<br><br><br><br><br>
	<label>Patient / Relative Sign</label>
	</div>
	
	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"  style="text-align: right;">
	
				<s:if test="mlccase==1">
						<div class="">
							<p><s:property value="doctor_name" /><br>
								<%if (loginInfo.isDischarge_new()) {%> <s:property
									value="qualification" /><%
									}
								%></p>
							<span>[FOR <s:property value="clinicName" />]
							</span>
							<br>
						</div>
					</s:if>
					<s:else>
						<div class=" " >
						<p><s:property value="doctor_name" /></p><br>
						
						<s:if test="qualification==null||qualification==''"></s:if><s:else><s:iterator value="qualificationList">  <s:property value="name"/> <br></s:iterator></s:else></span>
			
							<span>[FOR <s:property value="clinicName" />]
							</span> <br>
							<br>
						</div>
						
					
					</s:else>
	
	<label>Consultant Sign</label>
	</div>
	
	</div>
	
	</div>
	
	<!-- Print -->
	<div class="col-lg-12 col-md-12 hidden-print" style="padding: 0px;text-align: left;">
							<input type="button" class="btn btn-primary savebtn savebigbtn"
								value="Print" onclick="printpage()">
	</div>
	
	
	</body></html>	


