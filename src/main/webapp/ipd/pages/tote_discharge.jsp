<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@page import="com.apm.Ipd.web.form.IpdForm"%>
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
<script type="text/javascript" src="common/JsBarcode.all.min.js"> </script>
<style>

label{
font-size: 20px;
font-weight: bold;
}
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
<%-- 	<div class="letterheadhgt" >
		<div id="newpost"
			class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
				style="padding-left: 0px; padding-right: 0px;">
				<link href="common/css/printpreview.css" rel="stylesheet" />
				<%@ include file="/accounts/pages/letterhead.jsp"%>
			</div>
		</div>
	</div> --%>
	
	<div class="row" style="margin-top: 30px;">
		
			
	<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
						style="padding: 0px;    padding-bottom: 20px">
						<div class="col-lg-4 col-md-4 col-xs-4">
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
						</div>
					<!-- 	<div class="col-lg-4 col-md-4 col-xs-4 hidden"
								style="text-align: right; padding: 0px;">
								<div class="form-group" style="margin-bottom: 0px !important;">
									<a href="#" id="button" class="hidden-print"
										onclick="showhide()"
										style="float: right; background-color: grey; color: #fff; padding: 0px 5px 0px 5px;">Hide
										Letterhead</a>

								</div>
							</div> -->
						
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
							style="padding-left: 150px; padding-right: 0px;">
							
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
	<div class="row" style="padding: 0px; background-color: rgba(91, 192, 222, 0.16);">
		<div class="col-lg-12 col-xs-12 col-md-12" >
		   <!--  <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
				<div class="form-group">
					
					
				</div>
			</div> -->
		    	
				<div class="col-lg-6 col-xs-6 col-md-6">
				<label for="exampleInputEmail1" ><b>UHID: <s:property value="regno" /></b></label>
			
			</div>
			<div class="col-lg-6 col-xs-6 col-md-6">
					<label for="exampleInputEmail1"><b>IPD NO:
					
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
					</b></label>
				</div>
			
			<%-- <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8 text-right">
				<div class="form-group">
					<label for="inputEmail" class="control-label"><b>Primary Consultant : <s:property value="doctor_name" />( <s:property value="discipline" /> )</b></label>
					
			 </div> --%>
			</div>
			
	
			
			<div class="col-lg-12 col-xs-12 col-md-12" >
				
				<div class="col-lg-6 col-xs-6 col-md-6">
				<label for="exampleInputEmail1" onclick=""><b>Patient Name : <s:property value="client" /></b></label>
				</div>
				<div class="col-lg-6 col-xs-6 col-md-6">
					<label for="exampleInputEmail1"><b>Payee: <s:property value="thirdParty" /></b></label>
					</div>
				</div>
			
			
			
			<div class="col-lg-12 col-xs-12 col-md-12">
	
		
				<div class="col-lg-6 col-xs-6 col-md-6">
				<label for="exampleInputEmail1"><b>Age/Sex : <s:property value="agegender" /></b></label>
				</div>
				<div class="col-lg-6 col-xs-6 col-md-6">
					<label for="exampleInputEmail1" ><b>Dis. Outcome: <s:if test="dischrgeOutcomes==''"> N/A</s:if>
										<s:else>
											<s:property value="dischrgeOutcomes" />
										</s:else><s:property value="Datetime" /></b></label>
					
				</div>
		
	     </div>	
	
	
	
	     <div class="col-lg-12 col-xs-12 col-md-12">
	
		       <s:if test="fathername!=''">
				<div class="col-lg-6 col-xs-6 col-md-6">
			         
                    <label for="exampleInputEmail1" ><b >Father Name :  <s:property value="fathername" /></b></label>
				</div>		
				</s:if><s:else>
		      <div class="col-lg-6 col-xs-6 col-md-6">
			         
                  <label for="exampleInputEmail1" ><b >D.O.A :  
							<s:if test="admissiondate!=''">
										<span> <s:property value="admissiondate" /> </span>
									</s:if></b></label>
				</div>	
		    
		    </s:else>
				<div class="col-lg-6 col-xs-6 col-md-6">
						<label for="exampleInputEmail1" ><b >Dis. Status :  
							<s:if test="dischargeStatus==''"> N/A</s:if>
										<s:else>
											<s:property value="dischargeStatus" />
										</s:else></b></label>
						</div>
						
			   </div>
					
	<div class="col-lg-12 col-xs-12 col-md-12">
	
		     <s:if test="mothername!=''">
				<div class="col-lg-6 col-xs-6 col-md-6">
			        <label for="exampleInputEmail1" ><b >Mother Name :  <s:property value="mothername" /></b></label>
                </div>		
		 </s:if>  
		 <s:else >
				<div class="col-lg-6 col-xs-6 col-md-6">
			        <label for="exampleInputEmail1" ><b >Address : <s:property value="addr" /> <s:if
										test="clientpostcode!=null">,&nbsp <s:property
											value="clienttown" />
									</s:if> <s:if test="clientpostcode!=null">,&nbsp <s:property
											value="clientpostcode" />
									</s:if></b></label>
                </div>		
		    </s:else>
		 
		 
		 
		 <s:if test="fathername=''">
				<label for="exampleInputEmail1" ><b >Address : <s:property value="addr" /> <s:if
										test="clientpostcode!=null">,&nbsp <s:property
											value="clienttown" />
									</s:if> <s:if test="clientpostcode!=null">,&nbsp <s:property
											value="clientpostcode" />
									</s:if></b></label>
					</s:if>	
			   </div>
		<div class="col-lg-12 col-xs-12 col-md-12">
	
		     <s:if test="mothername=''">
				<div class="col-lg-6 col-xs-6 col-md-6">
			        <label for="exampleInputEmail1" ><b >Address : <s:property value="addr" /> <s:if
										test="clientpostcode!=null">,&nbsp <s:property
											value="clienttown" />
									</s:if> <s:if test="clientpostcode!=null">,&nbsp <s:property
											value="clientpostcode" />
									</s:if></b></label>
                </div>		
		    </s:if>
			   <div class="col-lg-6 col-xs-6 col-md-6">
						<label for="exampleInputEmail1" ><b >D.O.D :  
							
										<s:if test="dischargedate==''">N/A</s:if>
										<s:else>
											<s:property value="dischargedate" />
										</s:else></b></label>
			  </div>
						
 </div>
			   <div class="col-lg-12 col-xs-12 col-md-12">
	
		    
				<div class="col-lg-6 col-xs-6 col-md-6">
			        <label for="exampleInputEmail1" ><b >Contact : <s:property value="contact" /></b></label>
                </div>		
		 
			   <div class="col-lg-6 col-xs-6 col-md-6">
						<label for="exampleInputEmail1" ><b >Age On Admission :  
							
										<s:property value="ageonadmn" /></b></label>
			  </div>
						
			   </div>
			   
			   
			   
			   <div class="col-lg-12 col-xs-12 col-md-12">
	
		    
				<div class="col-lg-6 col-xs-6 col-md-6">
			        <label for="exampleInputEmail1" ><b >Bed Log : <s:iterator value="bedLogList">
										<s:if test="status==1">
											<span><s:property value="wardname" /> / <s:property
													value="bedname" /></span>,</s:if>
										<s:else>
											<span><s:property value="wardname" /> / <s:property
													value="bedname" /></span>,</s:else>
									</s:iterator></b></label>
                </div>		
		 
			   <div class="col-lg-6 col-xs-6 col-md-6">
						<label for="exampleInputEmail1" ><b >Age On Discharge :  
							
										<s:property value="Ageondischarge" /></b></label>
			  </div>
						
			   </div>
			   
			   <div class="col-lg-12 col-xs-12 col-md-12">
	
		    
				<div class="col-lg-6 col-xs-6 col-md-6">
			        <label for="exampleInputEmail1" ><b >Consultant : 	<s:if test="allconsultantlistwithdepart!=null">
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
						</s:if></b></label>
                </div>		
		 
			   <div class="col-lg-6 col-xs-6 col-md-6">
						<label for="exampleInputEmail1" ><b>Weight on Discharge :  
							
										<%=ipd.getWtdischarge()%></b></label>
			  </div>
						
			   </div>
			   
			   
			    <div class="col-lg-12 col-xs-12 col-md-12">
	
		    
				<div class="col-lg-6 col-xs-6 col-md-6">
			        <label for="exampleInputEmail1" ><b >Secondary Consultant : <s:if test="secndryconsult!=''">
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
							</s:if></b></label>
                </div>		
		 
			   <div class="col-lg-6 col-xs-6 col-md-6">
			   
						<label for="exampleInputEmail1" ><b>Barcode :  
							
										<svg id="barcode"></svg></b></label>
			  </div>
						
			   </div>
	</div>	
		
			<%-- <div class="col-lg-2 col-md-2 col-xs-6 col-sm-3">
				<div class="form-group">
					<label for="exampleInputEmail1"><b>Consultatation Date</b></label>
					<p>
						<s:property value="Datetime" />
					</p>
				</div>
			</div> --%>
		
		
	
		
		
		<%-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
			<%if(!DateTimeUtils.isNull(ipdForm.getPulse()).equals("")
			|| !DateTimeUtils.isNull(ipdForm.getSys_bp()).equals("")
			|| !DateTimeUtils.isNull(ipdForm.getSpo()).equals("")
			|| !DateTimeUtils.isNull(ipdForm.getTemp()).equals("")
			|| !DateTimeUtils.isNull(ipdForm.getSugarFasting()).equals("")
			|| !DateTimeUtils.isNull(ipdForm.getHeight()).equals("")
			|| !DateTimeUtils.isNull(ipdForm.getWeight()).equals("")
			|| !DateTimeUtils.isNull(ipdForm.getBmi()).equals("")
			){ %>
			<div class="col-lg-12 col-xs-12 col-md-12 " style="margin-bottom: 5px;" >
				<label>Vitals</label>
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="margin-bottom: 15px;">
				<%if(!DateTimeUtils.isNull(ipdForm.getPulse()).equals("")){ %>
					<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
						<label><b>Pulse:</b></label>
						<s:property value="pulse"/>
					</div>
				<%} %>
				<%if(!DateTimeUtils.isNull(ipdForm.getPulse()).equals("")){ %>
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
					<label><b>BP:</b></label>
					<s:property value="sys_bp"/>
				</div>
				<%} %>
				<%if(!DateTimeUtils.isNull(ipdForm.getPulse()).equals("")){ %>
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
					<label><b>SPO2:</b></label>
					<s:property value="spo"/>
				</div>
				<%} %>
				<%if(!DateTimeUtils.isNull(ipdForm.getPulse()).equals("")){ %>
				<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
					<label><b>TEMP:</b></label>
					<s:property value="temp"/>
				</div>
				<%} %>
				<%if(!DateTimeUtils.isNull(ipdForm.getPulse()).equals("")){ %>
				<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
					<label><b>SUGAR FASTING:</b></label>
					<s:property value="sugarFasting"/>
				</div>
				<%} %>
				</div>
				<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12" style="margin-bottom: 15px;">
				<%if(!DateTimeUtils.isNull(ipdForm.getPulse()).equals("")){ %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
					<label><b>HEIGHT:</b></label>
					<s:property value="height"/>
				</div>
				<%} %>
				<%if(!DateTimeUtils.isNull(ipdForm.getPulse()).equals("")){ %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
					<label><b>WEIGHT:</b></label>
					<s:property value="weight"/>
				</div>
				<%} %>
				<%if(!DateTimeUtils.isNull(ipdForm.getPulse()).equals("")){ %>
				<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
					<label><b>BMI:</b></label>
					<s:property value="bmi"/>
				</div>
				<%} %>
				<%if(loginInfo.isSimpliclinic()) {%>
				<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
					<label><b>ALLERGY:</b></label>
					<s:property value="allergynotes"/>
				</div>
				<%} %>
			</div>
			<%} %>
			<%if(loginInfo.isSimpliclinic()){ %>
			<%if(!DateTimeUtils.isNull(ipdForm.getExamination()).equals("")){ %>
				<div class="col-lg-12 col-xs-12 col-md-12 textprime" style="margin-bottom: 5px;">
					<h5>EXAMINATION / FINDINGS</h5>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-bottom: 15px;">
					<div class="form-group" style="margin-top: 10px;">
						<%=ipdForm.getExamination().toString()%>
					</div>
				</div>
			<%} %>
			<%} %>
			<%if(!DateTimeUtils.isNull(ipdForm.getChiefcomplains()).equals("")){ %>
				<div class="col-lg-12 col-xs-12 col-md-12 textprime" style="margin-bottom: 5px;">
					<h5>CHIEF/PRESENT COMPLAINTS AND REASON</h5>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-bottom: 15px;">
					<div class="form-group" style="margin-top: 10px;">
						<%=ipdForm.getChiefcomplains().toString()%>
					</div>
				</div>
			<%} %>
			<%
				ArrayList<Bed> ipdConditionids = (ArrayList<Bed>) session.getAttribute("finalConditions");
			%>
			<%if(ipdConditionids.size()>0){ %>
				<div class="col-lg-12 col-xs-12 col-md-12 ">
					<label>DIAGNOSIS</label>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-bottom: 15px;">
					<table class="" border="1">
						<tbody>
							<tr>
								<td>Sr. No</td>
								<td>Diagnosis</td>
							</tr>
							
									
									<%
										int i = 0;
									%>
									<%
										for (Bed bed : ipdConditionids) {
									%>
										<tr>
											<td><%=(++i) %></td>
											<td><%=bed.getConditionname()%></td>
											<%
												i++;
											%>
										</tr>
									<%
										}
									%>
						</tbody>
					</table>
				</div>
			<%} %>
			<%if(!DateTimeUtils.isNull(ipdForm.getInvestigation()).equals("")){ %>
				<div class="col-lg-12 col-xs-12 col-md-12 textprime">
					<h4>INVESTIGATIONS 
					</h4>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div class="form-group" style="margin-top: 10px;">
						<%=ipdForm.getInvestigation().toString()%>
					</div>
				</div>
			<%} %>
			<div class="">
			
				<%
					ArrayList<Priscription> dischargePriscList = (ArrayList<Priscription>) session.getAttribute("priscList");
				%>
				<%if(dischargePriscList.size()>0 || !DateTimeUtils.isNull(ipdForm.getKunal_manual_medicine_text()).equals("")){ %>
					<div class="col-lg-12 col-xs-12 col-md-12 ">
						<label>
							ADVICE MEDICINE
						</label>
					</div>
				<%} %>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<%if(dischargePriscList.size()>0){ %>
						<div class="form-group">
							<table class="" id="priscTable" border="1">
								<thead>
									<tr class="headings">
										<td style="width: 5%;">Sr.No</td>
										<td>Medicine</td>
										<td>Dosage</td>
										<td style="width: 5%;">Days</td>
										<td>Notes</td>
										<td>Strength</td>
										<td>Quantity</td>
										<td>FREQUENCY NOTE</td>
										<td>Remark</td>
									</tr>
								</thead>
								<tbody id="dischargedataid">
									<%for(Priscription priscription:dischargePriscList){ %>
										<tr>
											<td>
												<%=priscription.getDispriscsrno() %>
											</td>
											<td class="uppercaseirf medname">
												<%=priscription.getMdicinenametxt() %>
											</td>
											<td>
												<%if(!DateTimeUtils.numberCheck(priscription.getPriscdose()).equals("0")){ %>
													<%=priscription.getPriscdose() %>
												<%} %>
											</td>
											<td>
												<%=priscription.getPriscdays() %>
											</td>
											<td>
												<%if(!DateTimeUtils.isNull(priscription.getDosenotes()).equals("Select Routes")){ %>
													<%=priscription.getDosenotes() %>
												<%} %>
											</td>
											<td>
												<%=priscription.getStrength() %>
												<%=priscription.getUnitextension() %>
											</td>
											<td>
												<%=priscription.getDr_qty() %>
											</td>
											<td>
												<%=priscription.getPrisctimename() %>
											</td>
											<td>
												<%=priscription.getPriscindivisualremark() %>
											</td>
										</tr>
									<%} %>
									<s:iterator value="dischargePriscList">
										<tr>
											<td><s:property value="dispriscsrno"/>
											</td>
											<td class="uppercaseirf medname"><s:property
												value="mdicinenametxt" /></td>
											<td>
												<s:property value="priscdose"/>
											</td>
											<td><s:property value="priscdays"/>
											</td>
											<td>
												<s:property value="dosenotes" />
											</td>
											<td><s:property value="strength" /></td>
											<td><s:property value="dr_qty" /></td>
										</tr>
									</s:iterator>
									
								</tbody>
							</table>
						</div>
					<%} %>
					<%if(!DateTimeUtils.isNull(ipdForm.getKunal_manual_medicine_text()).equals("")){ %>
						<div class="form-inline">
							<div class="form-group">
								<label for="exampleInputName2"><b>PRESCRIPTION TEXT</b></label>
							</div>
						</div>
						<div class="form-group">
							<%=ipdForm.getKunal_manual_medicine_text().toString()%>
						</div>
					<%} %>
				</div>
				<br>
			</div>
				<%if(!DateTimeUtils.isNull(ipdForm.getDiscadvnotes()).equals("")){ %>
					<div class="">
						<div class="col-lg-12 col-xs-12 col-md-12 textprime" style="margin-bottom: 15px;">
							<h5>
								ADVICE/FOLLOW UP
							</h5>
						</div>
	
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	
							
							<div class="form-group" style="margin-top: 10px;">
								<%=ipdForm.getDiscadvnotes().toString()%>
							</div>
						</div>
	
					</div>
				<%} %>
			</div>
			<%if(!DateTimeUtils.isNull(ipdForm.getExample()).equals("")){ %>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="col-lg-12 col-xs-12 col-md-12 textprime" style="margin-bottom: 15px;">
						<h5>
							NOTES
						</h5>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="form-group" style="margin-top: 10px;">
							<s:property value="discharge_default"/>
							<%=ipdForm.getExample().toString()%>
						</div>
					</div>

				</div>
			</div>
			<%} %>
		</div> --%>
		<div class="col-lg-12 col-md-12"
				style="margin-top: 50px; padding: 0px;">
					<div class="text-right">
						<div class="col-lg-12 col-md-12" style="padding: 0px;">
							<p>
							<s:property value="doctor_name" />
							<br>
							<s:property value="discipline" />
							<br>
							</p>
						</div>
					</div>
		</div>			
				
				<div class="col-lg-12 col-md-12 hidden-print"
					style="margin-bottom: 10px; padding: 0px;" id="print">
					<div class="">
						<div class="col-lg-12 col-md-12" style="padding: 0px;">
							<input type="button" class="btn btn-primary savebtn savebigbtn"
								value="Print" onclick="printconsult()">
						</div>
					</div>
				</div>
	



	



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

			
			
			function printconsult() {
				document.getElementById("print").style.display='none';
				window.print();
				
			}
			</script>
</html>

<%} %>



