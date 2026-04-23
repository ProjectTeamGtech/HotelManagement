<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="com.apm.Ipd.eu.entity.Bed"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Priscription"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% Bed ipd=(Bed)session.getAttribute("bed"); 
LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
%>
<s:hidden name='regno' id='uhiid'></s:hidden>
<!DOCTYPE html>
<html>
<head>
    <title>Admission Summary Form</title>
   <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
    <link href="_assets/css/priscription/hospitalresponsive.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">   
    <script type="text/javascript" src="ipd/js/admissionform.js"></script> 

<script type="text/javascript" src="common/JsBarcode.all.min.js"> </script>
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
        .textareaheight{
        height: 50px !important;;
        }
       
       .paddtop{
        padding: 0px 0px 14px 2px;
    	}
        .widthtabhedth1{
        	width: 30%;
        }
        .widthtabhedth2{
        	width: 7%;
        }
        .admissionbackgreen {
		    width: 210px;
		}
		.form-group {
    		margin-top: 4px;
		}
		.pad8{
			padding-top: 8px;
		}
		.backgrey{
			        background-color: rgba(149, 222, 91, 0.19);
		}
		.pnameback{
			    background-color: #f5f5f5;
    			margin-top: -7px;
		}
		.panel-primary {
		    border-color: #339966;
		}
		.padsign{
			padding-top: 100px;
			padding-left:0px;
			padding-right:0px;
		}
		.help-block {
		    display: block;
		    margin-top: 0px !important;
		    margin-bottom: 0px !important;
		    color: #737373;
		}
		 .bordertopgreen1 {
    border-top: 2px solid #339966;
}
  .panel-primary {
      border-color: #339966;
  }
  .padsign{
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
    margin-bottom: 0px !important;
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
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    padding: 2px 5px 2px 5px !important;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
    font-weight: normal;
    font-size: 12px;
    border-right: none !important;
    border-left: none !important;
}

 @media print
{
    .print_special { border: none !important; } 
    label {
    	font-size: 11px !important;
	}
	p {
	    margin: 0 0 5.5px !important;
	    font-size: 9px !important;
	}
	.form-group {
    margin-bottom: 0px !important;
}

.titleset {
    color: blank !important;
    border-bottom: 1px dashed #efefef;
    font-size: 12px !important;
    font-weight: bold !important;
    line-height: 20px;
    padding: 2px 0px 0px 6px !important;
    background-color: #efefef !important;
    -webkit-print-color-adjust: exact !important; 
}
h4, .h4 {
    font-size: 12px;
}
.backcolor{
	background-color: rgba(91, 192, 222, 0.16) !important;
}
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:12px !important;
	color: firebrick !important;
}

.table>thead>tr>th {
    vertical-align: bottom;
    border-bottom: transparent;
    background-color: #4E7894 !important;
    color: #fff !important;
    font-size: 9px !important;
}
}
    </style>
    <style>

.borderbot{
	border-bottom: 2px solid #6699cc;
    padding-top: 36px;
    padding-bottom: 15px;
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
.rgeno{
	    float: right;
    font-size: 11px;
    padding-top: 8px;
}
.titleset{
	margin: 0px;
    color: #6699cc;
    border-bottom: 1px dashed #efefef;
    font-size: 17px;
    line-height: 20px;
    background-color: #efefef;
     
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
.setticolors{
	border-bottom: 4px double #ddd;
	font-size:16px;
	color: firebrick;
}
.form-group {
    margin-top: 0px;
}

</style>

<script>
$(document).ready(function() {
	var uhid=document.getElementById('uhiid').value;
	JsBarcode("#barcode", uhid, {
		  format: "CODE128",
		  height: 25,
		  width:1,
		  displayValue:false
		});
});
function updateipdDeclration(id){
	document.getElementById("hdndecid").value = id;
	var ipdid = document.getElementById("id").value;
	document.getElementById("ipdid").value =ipdid;
	document.getElementById("decfrmid").submit();
	
}

</script>

<s:form action="decIpd" id="decfrmid">
	<s:hidden name="hdndecid" id="hdndecid"/>
	<s:hidden name="ipdid" id="ipdid"/>
</s:form>

</head>
<body>


<%

   String hstry="";
   String sysreview="";
   String obstretic_history="";
   String menstrual_history="";
   String substance_history="";
   String pediatric="";
%>

 <s:if test="history==true"> 
 </s:if>
 <s:else>
   <%hstry="hidden"; %> 
 </s:else>
 
 <s:if test="issystemicreview==true">
 
 </s:if>
 <s:else>
  <% sysreview="hidden"; %>
 </s:else>
  
  <s:if test="obstretic_history==true">
            
  </s:if>
  <s:else>
      <% obstretic_history="hidden"; %>
 </s:else>
    
    <s:if test="menstrual_history==true">
            
  </s:if>
  <s:else>
      <% menstrual_history="hidden"; %>
 </s:else>
  
   <s:if test="substance_history==true">
            
  </s:if>
  <s:else>
      <% substance_history="hidden"; %>
 </s:else>
  
<s:if test="paediatrichist==true">
            
  </s:if>
  <s:else>
      <% pediatric="hidden"; %>
 </s:else>


<s:form action="updateIpd" theme="simple" id="ipdadmissionfrm">
<s:hidden name="treatmentepisodeid" id="treatmentepisodeid"/>
	<s:hidden name="id" id="id"/>
	
	<div class="col-lg-12 col-md-12 col-sm-12 topback2 hidden-print">
		<s:select name="dectitle" id="dectitle" list="declerationTitleList"
		listKey="id" listValue="name" cssClass="form-control chosen-select" onchange="updateipdDeclration(this.value)"
		headerKey="0" headerValue="Select Decleration"/>
	</div>
	
	<div class="col-lg-12 col-xs-12 col-md-12 printmarginleft" style="padding: 0px;">
		<div class=" letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
				 <%if(!loginInfo.isHidelogobillinv()){ %>
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			<%} %>
			</div>
			
		</div>
	</div>
	
	<div class="">
	
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 backcolor" style="padding-top: 5px;border-bottom: 1px solid #6699cc;padding-bottom: 5px;background-color: rgba(91, 192, 222, 0.16);">
					<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;    margin-bottom: 10px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:2px;">
						<div class="col-lg-4 col-md-4 col-xs-4">
						
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<div class="form-group" style="margin-bottom: 0px !important;text-align: center;">
							<s:if test="casualtyipd==0">
								<b class="setticolors">ADMISSION SUMMARY</b>
							</s:if>
							<s:elseif test="casualtyipd==2"><b class="setticolors">DAYCARE SUMMARY</b></s:elseif>
							<s:else>
								<b class="setticolors">Casualty SUMMARY</b>
							</s:else>
								
						</div>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4" style="text-align: right;padding:0px;">
							<div class="form-group" style="margin-bottom: 0px !important;">
								<a href="#" id="button" class="hidden-print" onclick="showhide()" style="float:right;background-color: grey;color: #fff;padding: 0px 5px 0px 5px;">Hide Letterhead</a>
							</div>
						</div>
						
					</div>
					</div>
				</div>
				
				
				
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding-left:1px;padding-right:1px;">
				<!--  Lok new div-->
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">UHID </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="regno"/></span> 
					</div>
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Patient Name   </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="client"/></span> 
					</div>
					
					<%-- <s:if test="relativename!=''">
					<s:if test="relativename!=null">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Guardian Name   </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left h" style="padding: 0px;">
						<span>: <s:property value="birthplace"/></span> 
					</div>
					</s:if>
					</s:if> --%>
					
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Age / Gender   </b>
					</div>
					<%if(!loginInfo.isBalgopal()){ %>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="agegender"/></span> 
					</div>
					<%}else{ %>
					 <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="Ageonadmn"/></span>/<span>: <s:property value="gender"/></span> 
					</div>
					
					<%} %>
					
					<%-- <s:if test="familyDetails!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Family Details1  </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="familyDetails"/></span> 
					</div>
					</s:if> --%>
					<s:if test="familyDetails!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Family Details</b> 
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
								<span>:
								<s:if test="relativename!=null">
								 <s:property value="relativename"/>,
								</s:if>
								
								<s:if test="relationcont!=null">
								M.<s:property value="relationcont"/>
								</s:if>
								
								<s:if test="relation!=null">
								(<s:property value="relation"/>)
								</s:if>
								 </span> 
					</div>
					</s:if>
					
					<s:if test="fathername!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Father Name   </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="fathername"/></span> 
					</div>
					</s:if>
					
					<s:if test="mothername!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Mother Name  </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="mothername"/></span> 
					</div>
					</s:if>
					
					<s:if test="birthplace!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Birth Place </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="birthplace"/></span> 
					</div>
					</s:if>
					
					<s:if test="contact!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Contact  </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="contact"/></span> 
					</div>
					</s:if>
					<s:if test="address!=''">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Address  </b>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
						<span>: <s:property value="address"/></span> 
					</div>
					</s:if>
					<%if(loginInfo.isBams1()){ %>
					                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
						<b for="inputEmail3" class="control-label">Opd Vitals : </b>
					</div>
					
												Height:
												<span> <s:property value="Height"/></span> |
											
												Weight:
				                                <span><s:property value="weight"/></span> |
											
											    BMI:
												<span><s:property value="Bmi"/></span> |
											
												Pulse:
												<span><s:property value="pulse"/></span> |
											
												SysBP:
												<span><s:property value="Sys_bp"/></span> |
											
												DiaBP:
												<span><s:property value="Dia_bp"/></span> |
											
												Sugarfasting:
												<span><s:property value="sugarfasting"/></span> |
											
												Postmeal:
												<span><s:property value="postmeal"/></span> |
											
												Temp:
												<span><s:property value="Temp"/></span> |
											
												SPO2:
												<span><s:property value="Spo"/></span> 
					
					<%} %>
				</div>
				
				
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 hidden" style="padding-left:1px;padding-right:1px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">UHID</b>
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Patient Name</b>
							</div>
							
							<s:if test="relativename!=''">
							<s:if test="relativename!=null">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Guardian Name</b>
							</div>
							</s:if>
							</s:if>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<s:if test="agegender!=''"><b for="inputEmail3" class="control-label">Age / Gender</b></s:if>
							</div>
							
			<s:if test="familyDetails!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Family Details</b> 
							</div> 
			</s:if>			
						<s:if test="fathername!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Father Name</b> 
							</div> 
						</s:if>
						
						<s:if test="mothername!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Mother Name</b> 
							</div> 
						</s:if>
						
						<s:if test="birthplace!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Birth Place</b> 
							</div> 
						</s:if>
						
						
						
						<s:if test="contact!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								  <b for="inputEmail3" class="control-label">Contact</b>
							</div>
							</s:if>
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Address</b>
							</div>
			
			
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="regno"/></span> 
							</div>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="client"/> </span>
							</div>
							<s:if test="relativename!=''">
							<s:if test="relativename!=null">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="relativename"/></span> 
							</div>
							</s:if>
							</s:if>
							 <div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<s:if test="agegender!=''"><span>: <s:property value="agegender" /></span></s:if>
							</div>
							
							<s:if test="familyDetails!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="relativename"/>,<s:property value="relationcont"/> (<s:property value="relation"/>)</span> 
							</div>
						</s:if>
						
						<s:if test="fathername!=''">
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="fathername"/></span> 
						</div>
						</s:if>
						
						<s:if test="mothername!=''">
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="mothername"/></span> 
						</div>
						</s:if>
						
						<s:if test="birthplace!=''">
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="birthplace"/></span> 
						</div>
						</s:if>
						
						<s:if test="contact!=''">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
									  <span>: <s:property value="contact"/></span> <s:if test="relationcont!=''"> |<span><s:property value="relationcont"/></span></s:if>
								</div>
							</s:if>
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="address"/> </span> 
							</div>
							
							
						</div>
						
						
						
							
						
						
					</div>
					<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 text-right" style="padding-left:0px;padding-right:0px;">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-right">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">IPD NO</b>           		 
								<s:if test="admissiondate!=''">
								<br><b for="inputEmail3" class="control-label">Adm. Date</b>
								<br><b for="inputEmail3" class="control-label hidden">Age On Admission</b>
							</s:if>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label">Ward / Bed</b>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<b for="inputEmail3" class="control-label">Payee</b>
							  <s:if test="mlcno!=''">
							 <b for="inputEmail3" class="control-label">/MLC</b>
							 </s:if>
							 
						</div>
						
										<!--  lokesh-->
			
			
					
			<div class="<%=pediatric%>">
				<s:if test="wtonbirth!=''">	
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Birth Weight</b>
							</div>
			</s:if>				
			<s:if test="wtaddmission!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Admn Weight </b>
							</div>
							</s:if>
			<s:if test="headcircumference!=''">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">HC</b>
							</div>
							</s:if>
							<s:if test="midarmcircumference!=''">
								<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Mid Arm Cir</b>
							</div>
							</s:if>
							<s:if test="length!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Length</b>
							</div>
							</s:if>
							
					<s:if test="gstureage!=''">		
							
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Gest Age</b>
							</div>
							
					</s:if>		
							<s:if test="wtdischarge!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">WT On Dis</b>
							</div>
							</s:if>
	<%
	String bal="";
	if(!loginInfo.isBalgopal()) {
	bal="hidden";
	
}%>						
							<!--lokesh  -->
							<div class="form-group marbot3 <%=bal %>" style="margin-bottom: 0px !important;">
								<b for="inputEmail3" class="control-label">Barcode</b>
							</div>
							</div>
						
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-left" style="padding: 0px;">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<%-- <span>: <s:property value="id"/>/<s:property value="num_admission"/></span>   --%>
							<span>: <%if(loginInfo.getIpd_abbr_access()==1){ %>
							<s:property value="newipdabbr"/>
							<%}else{ %>
							<s:property value="ipdseqno"/> 
							<%} %></span>        
									 
						<s:if test="admissiondate!=''">
								  <br><span>: <s:property value="admissiondate" /> </span>
								  <br><span class="hidden">: <s:property value="Ageonadmn" /> </span>
								  </s:if>
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="wardid" /> / <s:property value="bedid"/></span> 
						</div>
						<div class="form-group marbot3" style="margin-bottom: 0px !important;">
							<span>: <s:property value="thirdParty"/></span>
							  <s:if test="mlcno!=''">
							 <span>/ <s:property value="mlcno"/></span>
							 </s:if>
							
						</div>
						<!--  LOKESH-->
							<div >
							
						<s:if test="wtonbirth!=''">	
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="wtonbirth"/></span> 
							</div>
						</s:if>
								<s:if test="wtaddmission!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="wtaddmission"/></span> 
							</div>
							</s:if>
							<s:if test="headcircumference!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="headcircumference"/> cm</span> 
							</div>
							</s:if>
							<s:if test="midarmcircumference!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="midarmcircumference"/> cm</span> 
							</div>
							</s:if>
							<s:if test="length!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="length"/> cm</span> 
							</div>
							</s:if>
							
						<s:if test="gstureage!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="gstureage"/> </span> 
							</div>
						</s:if>
						
							
							<s:if test="wtdischarge!=''">
							<div class="form-group marbot3" style="margin-bottom: 0px !important;">
								<span>: <s:property value="wtdischarge"/></span> 
							</div>
							</s:if>
							
							<div class="form-group marbot3 <%=bal %>" style="margin-bottom: 0px !important;">
								<svg id="barcode"></svg>
							</div>	
							</div>
						</div>
					
					
						
						
						
						
						
						
						
					</div>
					</div>
				</div>
				
				<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;padding-left: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 text-left" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Primary Consultant</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group" style="padding:2px;margin-left: -22px;">
	                        			<%if(ipd==null){
	                        				ipd=new Bed();
	                        			} %>
	                        			<%ipd.setDepartment(DateTimeUtils.isNull(ipd.getDepartment())); %>
	                        				<span><s:property value="doctor_name"/> <%if(!ipd.getDepartment().equals("0")){ %>(<%=ipd.getDepartment()%>)<%} %></span>  
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	 <s:if test="mlcrefdoctor!=''">
	                        	 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        	 <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 text-left" style="padding: 0px;">
	                        	 <div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">MLC Consultant</label> 
	                        			</div>
	                        			</div>
	                        			<div class="col-lg-8 col-md-8 col-xs-8 col-sm-10" style="padding: 8px 8px 8px 8px;">
	                        			<div class="form-group" style="margin-left: -22px;">
							 	<span> <s:property value="mlcrefdoctor"/></span>
							 	</div>
							 	</div>
							 	</div>
							 </s:if>
	                        	<%if(ipd.getSecndryconsult()!=null){ %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 text-left" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Secondary Consultants</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group"  style="padding:2px;margin-left: -22px;">
	                        				<span>
	                        				<%-- <%String name = "";for(String s : ipd.getAllConsultantList()){name = name + s + ", "; %>
	                        					
	                        				<%}name = name.substring(0,name.length()-2); %>
	                        				<%=name %> --%>
	                        				 <%int akk=0; %>
	                        				 <s:iterator value="allconsultantlistwithdepart">
	                        				 		<%if(akk==0){ %>
	                        				 			<s:property value="fullname" />(<s:property value="specialization" />),
	                        				 		<%}else{%>
	                        				 			<s:property value="fullname" />(<s:property value="specialization" />),
	                        				 		<%} %>
	                        				 		<%akk++; %>
	                        				 </s:iterator>
	                        				</span>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<% }%>
	                        <%if(!loginInfo.isAyushman()){ %>	
	                        	 <%if(ipd.getRefferedby()!=null){ %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-3 col-md- col-xs-3 col-sm-3 text-left" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Referred From</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8" style="padding: 0px 0px 0px 12px;margin-left: -22px;">
	                        			<div class="form-group">
	                        				<span> <%=ipd.getRefferedby() %></span>
	                        			</div>
	                        		</div>
	                        	</div> 
							<%} %>
						<%} %>	
					</div>
					
							
					</div>
				</div>
				
				<div class="">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: 10px;padding: 0px;">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-left" style="padding-left:0px;padding-right:0px;">
                                 <% if(ipd.getAddmissionfor()!=null){  %>
                                 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group"  style="padding:2px">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Admission For</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group"  style="padding:2px">
	                        				 <span><%=ipd.getAddmissionfor() %></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	 <%} %>
	                        	 
	                        	<!--<% if(ipd.getReimbursment()!=null){  %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-3 col-sm-3">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Reimbursement</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-7 col-sm-7">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="font-weight: normal;">: <%=ipd.getReimbursment() %></label> 
	                        			</div>
	                        		</div>
	                        	</div> 
                                <%} %>
                                --><% if(ipd.getAlergies()!=null) {  %>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group"  style="padding:2px">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">H/O Allergies</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getAlergies() %></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	
	                        	<!--<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-3 col-sm-3">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Admission Details</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-7 col-sm-7">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="font-weight: normal;">: <%=ipd.getAdmissiondetails() %></label> 
	                        			</div>
	                        		</div>
	                        	</div> 
                                --><%} %>
                                
                                <%if(ipd.getPackagename()!=null){ %>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Package</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getPackagename()%></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
                                <%} %>
                             <% if(ipd.getSummary()!=null) { %>   
                            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="padding: 10px 0px 0px 0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">SUMMARY </h4>
	                        </div>
	                        <%} %>
	                        
	                     <%if(ipd.getChiefcomplains()!=null){ %>
	                     	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Chief Complaints</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getChiefcomplains() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
                        	<%} %>   
	                        
	                     <%if(ipd.getAdmission_reason()!=null){ %> 
	                     <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Reason For Admission</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getAdmission_reason() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>  
                        <%} %>
                        	
                        	<%if(ipd.getPresentillness()!=null) { %>
                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">H/O Present Illness</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getPresentillness() %></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
                        	<%} %>
                        </div>
					</div>
				</div>
				
				<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:10px 0px 0px 0px;">
						<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=sysreview%>" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">SYSTEMIC REVIEW</h4>
	                        </div>
	                        <div class="row <%=sysreview%>">
	                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        		<p style="margin: 0 0 0px;font-size: 13px;text-align:justify;">
	                        			<b>There is NO H/O -</b>
	                        		    <%if(DateTimeUtils.isNull(ipd.getFpcondition()).equals("No")) {%>
	                        			<label style="font-weight: normal;" for="exampleInputName2">Fever at present</label>,
	                        			<%} %>
	                        			 <s:if test="nauseacondition=='No'">
	                        			<label style="font-weight: normal;" for="exampleInputName2">Nausea</label>,
	                        			</s:if>
	                        			<s:if test="fnucondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Frequent Nocturnal Urination</label>,
	                        			</s:if>
	                        			<s:if test="smcondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Straining at micturation</label>,
	                        			</s:if>
	                        			<s:if test="chestpaincond=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Chest pain</label>,
	                        			</s:if>
	                        			<s:if test="dimvisioncond=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Dimness of vision</label>,
	                        			</s:if>
	                        			 <s:if test="hgucondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Headache</label>,
	                        			</s:if>
	                        			<s:if test="giddinesscondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Giddiness</label>,
	                        			</s:if>
	                        			<s:if test="unconcondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Unconsciousness</label>,
	                        			</s:if>
	                        			<s:if test="hmcondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Haemoptysis, Malena</label>,
	                        			</s:if>
	                        			<s:if test="jointpaincond=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Joint pain</label>,
	                        			</s:if>
	                        			<s:if test="edemafeetcondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Edema feet</label>,
	                        			</s:if>
	                        			 <s:if test="hematuriacondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Hematuria</label>,
	                        			</s:if>
	                        			 <s:if test="bmcondition=='No'">
	                        			    <label style="font-weight: normal;" for="exampleInputName2">Burning micturation</label>,
	                        			</s:if>
	                        			  <s:if test="oliguriacondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Oliguria</label>,
	                        			</s:if>
	                        			 <s:if test="pstgucondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Passing stone or gravel in the urine</label>,
	                        			</s:if>
	                        			<s:if test="ihcondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Impaired hearing</label>,
	                        			</s:if>
	                        			<s:if test="bmecondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Breathlessness on mild exertion</label>,
	                        			</s:if>
	                        			 <s:if test="tnecondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Tingling, Numbness in extremities</label>,
	                        			</s:if>
	                        			<s:if test="weaknesscondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Weakness</label>,
	                        			</s:if>
	                        			<s:if test="constipationcond=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Constipation</label>
	                        			</s:if>
	                        		</p>
	                        		<p style="display:inline-block;margin: 0 0 0px;">
	                        		    <%if(ipd.getSpecialnotes()!=null){ %>
	                        			<label for="exampleInputName2">Special Notes/Remarks : </label><span class="help-block"><%=ipd.getSpecialnotes() %> </span>
	                        			<%} %>
	                        		</p>
		                        </div>
	                        	</div>
	                        	</div>
	                        	 <%if(ipd.getEarlierinvest()!=null){ %>
                       	<div class="row">
                       	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Earlier Investigations</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getEarlierinvest() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	</div>
                        <%} %>
					</div>
				</div>
				
				<s:if test="nicuaccess">
					<div>
					<%ipd.setMaternal_history(DateTimeUtils.removeBreaks(ipd.getMaternal_history()));
					ipd.setPerinatal_history(DateTimeUtils.removeBreaks(ipd.getPerinatal_history()));
					ipd.setBirthhist(DateTimeUtils.removeBreaks(ipd.getBirthhist()));
					%>
					 <%if(!("".equals(ipd.getMaternal_history()))||!("".equals(ipd.getPerinatal_history()))){%>
					 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="padding: 10px 0px 0px 0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">NICU HISTORY </h4>
	                        </div>
	                 <%} %>       
					   <%if(!(ipd.getMaternal_history()==null||ipd.getMaternal_history().equals(""))){%>
					   
					 <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12  " style="padding: 10px;padding-top: 10px !important;">
					 
					 
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">MATERNAL HISTORY</label> 
	                        			</div>
	                        		</div>
	                       
	                        
					<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getMaternal_history() %></span> 
	                        			</div>
	                       
	                        		</div>
	                       </div>  		
	                <%} %>
	                <%if(!(ipd.getPerinatal_history()==null||ipd.getPerinatal_history().equals(""))){%>
	                 <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12  " style="padding: 10px;padding-top: 10px !important;">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">PERINATAL HISTORY</label> 
	                        			</div>
	                        		</div>
	                       
	                        
					<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getPerinatal_history() %></span> 
	                        			</div>
	                       
	                        		</div>
	                       </div>  		
	                <%} %>
	                
	                </div>        		
	                </s:if>        		
	                
	                
	             <%if(ipd.getOnexamination()!=null){ %>
	                        			
	             <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center " style="padding: 0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;text-transform: uppercase;">On Examination</h4>
	                        	
	                        </div>           			
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				  
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getOnexamination() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<%} %>   
				<div class="">
				<% boolean hisstatus=true;  %>
				<%if(DateTimeUtils.isNull(ipd.getPasthistory()).equals("")||DateTimeUtils.isNull(ipd.getFamilyhist()).equals("")||DateTimeUtils.isNull(ipd.getSurgicalnotes()).equals("")||DateTimeUtils.isNull(ipd.getPersonalhist()).equals("")||DateTimeUtils.isNull(ipd.getSuggestedtrtment()).equals("")){ %>
			<%hisstatus=false; %>
				<%} %>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
							 <%if(ipd.getHistory()!=null){ %>
							 <%if(hisstatus){ %>
	                         <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center <%=hstry %>" style="padding: 0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">HISTORY </h4>
	                        </div>
	                        <%} %>
	                        <%} %>
	                        <div class="row <%=hstry%>">
	                           <%if(ipd.getPasthistory()!=null){ %>
	                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Past History</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getPasthistory() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<%} %>
	                        			<%if(ipd.getFamilyhist()!=null){ %>
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Family History</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getFamilyhist() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        			<%} %>
	                        			
	                        		<%if(ipd.getSurgicalnotes()!=null){ %>
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">ANY SURGICAL HISTORY</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getSurgicalnotes() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<%} %>
	                        		
	                        			<%if(ipd.getPersonalhist()!=null){ %>
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Personal History</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getPersonalhist() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<%} %>
	                        	
	                        	
	                        	
	                        	
	                        			
	                        	<% if(ipd.getSuggestedtrtment()!=null){ %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Treatment History</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getSuggestedtrtment() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<%} %>
	                        		</div>
	                        </div>
					</div>
					
					
					
					<!-- //peditric
 -->					
					        		
						<div class="<%=pediatric%>">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 10px 0px 0px 0px;">
							 <%if(ipd.isPeditric()){ %>
	                         <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center " style="padding: 0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">PEDIATRIC HISTORY </h4>
	                        </div>
	                        
	                        <div class="row <%=hstry%>">
	                        
	                        
	                           <%boolean diet=true;
	       					if(ipd.getDiethist() == null||ipd.getDiethist().equals("")||ipd.getDiethist().equals("<br>")){
	    						diet=false;
	    					}
	    						if (diet) {%>
	                           <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Diet Hst</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getDiethist() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<%} %>
	                        	
	                        	<% 
	                        	boolean devep=true;
	                        							if(ipd.getDevelopmenthist()==null||ipd.getDevelopmenthist().equals("")||ipd.getDevelopmenthist().equals("<br>")){
	                        								devep=false;
	                        							}
	                        								
	                        	%>
	                        			<%if (devep) { %>
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Development History</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getDevelopmenthist() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        			<%} %>
	                        			<%if(ipd.getBirthhist()!=null){ %>
	                        			<%if(!(ipd.getBirthhist().equals("")||ipd.getBirthhist().equals("<br>")||ipd.getBirthhist().equals("<br>"))){ %>
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Birth History</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%= ipd.getBirthhist() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<%} %>
	                        	
	                        	<%} %>
	                        			<%if(ipd.getEmmunizationhist()!=null){ %>
	                        				<%if(!(ipd.getEmmunizationhist().equals("")||ipd.getEmmunizationhist().equals("<br>")||ipd.getEmmunizationhist().equals("<br>"))){ %>
	                        			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Immunization </label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%= ipd.getEmmunizationhist() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<%} %>
	                        	<%} %>
	                        	<%} %>
	                        		</div>
	                        </div>
					</div>
					
					
					<!-- <peditric>
 -->					<div class="">
				
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=substance_history %> " style="padding:10px 0px 0px 0px;">
					    <%if(ipd.getSubstancehistory()!=null) { %>	
						<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">SUBSTANCE HISTORY </h4>
	                        </div>
	                        <%} %>
	                        <div class="row <%=substance_history %>">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	   <%if(ipd.getAlcohal()!=null) { %>
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Alcohal <span style="font-weight: normal;"><%=ipd.getAlcohal() %></span></label>, <label for="exampleInputName2">Tobacco <span style="font-weight: normal;">: <%=ipd.getTobaco() %></span></label>  
	                        			</div>
	                        	  </div>
	                        	   <%} %>
	                        	  <%if(ipd.getOther_medication()!=null) { %> 
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;"> 
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Other Medication</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getOther_medication() %></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	<%} %>
	                        	<%if(ipd.getDrugs()!=null) { %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Drugs</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getDrugs() %></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	<%} %>
	                        	<%if(ipd.getSmoking()!=null) { %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Smoking</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getSmoking() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	<%} %> 
	                        	<%if(ipd.getTobaconotes()!=null) { %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Tobaco</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getTobaconotes() %></span> 
	                        			</div>
	                        		</div>
	                        	</div> 
	                        	<%} %>
	                        	
	                        	</div>
	                        </div>
					</div>
				</div>
				<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=menstrual_history %>" style="padding:10px 0px 0px 0px;">
					   
					    <%if(ipd.getMenstraulhistory()!=null) { %>
						<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">MENSTRUAL HISTORY </h4>
	                        </div>
	                        <%} %>
	                        <div class="row <%=menstrual_history %>">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        	      
	                        	        <%if(ipd.getAge_menarche()!=null){ %>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Age at Minarche </label> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getLmp()!=null) { %>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">L.M.P </label> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getLlmp()!=null) { %>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">L.L.M.P </label> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getPmc()!=null) { %>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">PMC </label> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getCycle_length()!=null){ %>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Cycle Length </label> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getDuration_flow()!=null){ %>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Duration of Flow </label> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getAmount_flow()!=null){ %>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Amount of Flow </label> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getDysmenorrhea()!=null){ %>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Dysmenorrhea </label> 
	                        			</div>
	                        			<%} %>
	                        	</div>
	                        	
	                        	
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 <%=menstrual_history %>" style="padding:0px 0px 0px 12px;">
	                        	       
	                        	        <%if(ipd.getAge_menarche()!=null) { %>            
	                        			<div class="form-group">
	                        				<span><%=ipd.getAge_menarche() %> </span> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getLmp()!=null){ %>
	                        			<div class="form-group">
	                        				<span><%=ipd.getLmp() %></span> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getLlmp()!=null){ %>
	                        			<div class="form-group">
	                        				<span><%=ipd.getLlmp() %> </span> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getPmc()!=null){ %>
	                        			<div class="form-group">
	                        				<span><%=ipd.getPmc() %> </span> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getCycle_length()!=null){ %>
	                        			<div class="form-group">
	                        				<span><%=ipd.getCycle_length() %> </span> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getDuration_flow()!=null) { %>
	                        			<div class="form-group">
	                        				<span><%=ipd.getDuration_flow() %> </span> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getAmount_flow()!=null){ %>
	                        			<div class="form-group">
	                        				<span><%=ipd.getAmount_flow() %> </span> 
	                        			</div>
	                        			<%} %>
	                        			<%if(ipd.getDysmenorrhea()!=null){ %>
	                        			<div class="form-group">
	                        				<span><%=ipd.getDysmenorrhea() %> </span> 
	                        			</div>
	                        			<%} %>
	                        			
	                        	</div>
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Dyspareunia </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">HO passing clt </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">White Discharge and Itching </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">InterCourse Freq </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Galactorea </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Hocontrarec </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Rubbela Vaccine </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Menopause </label> 
	                        			</div>
	                        			
	                        			
	                        	</div>
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				<span><%=ipd.getDyspareunia() %> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getHopassing_clots() %></span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getWhite_disc_itching() %></span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getIntercourse_freq() %></span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getGalactorrea() %></span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getHo_contraception() %></span>
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getRubella_vaccine() %></span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getRubella_vaccine() %></span> 
	                        			</div>
	                        	</div>
	                        	</div>
	                        	
	                        </div>
					</div>
				</div>
				<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=obstretic_history %>" style="padding:10px 0px 0px 0px;">
							<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">OBSTRETIC HISTORY</h4>
	                        </div>
	                        <div class="row <%=obstretic_history %>">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        	<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Nulligravida </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Currently Pregnent </label> 
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">IUD </label> 
	                        			</div>
	                        	</div>
	                        	
	                        	<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				<span><%=ipd.getNulligravida() %> </span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getCurrent_pregnent() %></span> 
	                        			</div>
	                        			<div class="form-group">
	                        				<span><%=ipd.getIud() %> </span> 
	                        			</div>
	                        	</div>
	                        	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="padding:0px">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">Live Boys <span style="font-weight: normal;"><%=ipd.getLive_boys() %></span></label>, <label for="exampleInputName2">Live Girls <span style="font-weight: normal;">: <%=ipd.getLive_girls() %></span></label>, <label for="exampleInputName2">Still Births <span style="font-weight: normal;">: <%=ipd.getStillbirths() %></span></label>, <label for="exampleInputName2">Abortions <span style="font-weight: normal;">: <%=ipd.getAbortions() %></span></label>, <label for="exampleInputName2">Death Child <span style="font-weight: normal;">: <%=ipd.getDeath_children() %></span></label>  
	                        			</div>
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2">P <span style="font-weight: normal;"><%=ipd.getP()%></span></label>, <label for="exampleInputName2">L <span style="font-weight: normal;">: <%=ipd.getL() %></span></label>, <label for="exampleInputName2">A <span style="font-weight: normal;">: <%=ipd.getA() %> </span></label>, <label for="exampleInputName2">D <span style="font-weight: normal;">: <%=ipd.getD() %> </span></label>  
	                        			</div>
	                        	</div>
	                        	</div>
	                        	</div>
	                        	<%int i=0; %>
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        	  <s:if test="gynicobsList.size>0">
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
	                        					<tbody>
	                        					    <s:iterator value="gynicobsList">
	                        						<tr style="border-bottom: 1px solid #eee;">
	                        							<td style="width: 4%;padding-right: 15px;"> <%=i+1 %></td>
	                        							<td style="width: 5%;padding-right: 15px;"><s:property value="year"/></td>
	                        							<td style="width: 13%;padding-right: 15px;"><s:property value="type"/></td>
	                        							<td style="width: 14%;padding-right: 15px;">
															<s:property value="type_delivery"/>
	                        							</td>
	                        							
	                        							<td style="width: 20%;padding-right: 15px;"><s:property value="indications"/></td>
	                        							<td style="width: 20%;padding-right: 15px;"><s:property value="coamplications"/></td>
	                        							<td style="width: 20%;padding-right: 15px;"><s:property value="institution"/></td>
	                        						</tr>
	                        						 <%i++; %>
	                        						</s:iterator>
	                        					</tbody>
	                        				</table>
	                        		</div>
	                        		</s:if>
	                        		 <s:if test="parity_abortion_notes!=''">
	                        		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding: 0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Parity/Abortion Notes</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding: 0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><s:property value="parity_abortion_notes"/> </span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	</s:if>
	                        	</div>
	                        </div>
	                        
					</div>
				</div>
				<%-- <div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:10px 0px 0px 0px;">
						<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 <%=sysreview%>" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">SYSTEMIC REVIEW</h4>
	                        </div>
	                        <div class="row <%=sysreview%>">
	                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	                        		<p style="margin: 0 0 0px;font-size: 13px;text-align:justify;">
	                        			<b>There is NO H/O -</b>
	                        		    <%if(ipd.getFpcondition().equals("No")) {%>
	                        			<label style="font-weight: normal;" for="exampleInputName2">Fever at present</label>,
	                        			<%} %>
	                        			 <s:if test="nauseacondition=='No'">
	                        			<label style="font-weight: normal;" for="exampleInputName2">Nausea</label>,
	                        			</s:if>
	                        			<s:if test="fnucondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Frequent Nocturnal Urination</label>,
	                        			</s:if>
	                        			<s:if test="smcondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Straining at micturation</label>,
	                        			</s:if>
	                        			<s:if test="chestpaincond=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Chest pain</label>,
	                        			</s:if>
	                        			<s:if test="dimvisioncond=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Dimness of vision</label>,
	                        			</s:if>
	                        			 <s:if test="hgucondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Headache, Giddiness, Unconsciousness</label>,
	                        			</s:if>
	                        			<s:if test="hmcondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Haemoptysis, Malena</label>,
	                        			</s:if>
	                        			<s:if test="jointpaincond=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Joint pain</label>,
	                        			</s:if>
	                        			<s:if test="edemafeetcondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Edema feet</label>,
	                        			</s:if>
	                        			 <s:if test="hematuriacondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Hematuria</label>,
	                        			</s:if>
	                        			 <s:if test="bmcondition=='No'">
	                        			    <label style="font-weight: normal;" for="exampleInputName2">Burning micturation</label>,
	                        			</s:if>
	                        			  <s:if test="oliguriacondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Oliguria</label>,
	                        			</s:if>
	                        			 <s:if test="pstgucondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Passing stone or gravel in the urine</label>,
	                        			</s:if>
	                        			<s:if test="ihcondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Impaired hearing</label>,
	                        			</s:if>
	                        			<s:if test="bmecondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Breathlessness on mild exertion</label>,
	                        			</s:if>
	                        			 <s:if test="tnecondition=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Tingling, Numbness in extremities</label>,
	                        			</s:if>
	                        			<s:if test="weaknesscondi=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Weakness</label>,
	                        			</s:if>
	                        			<s:if test="constipationcond=='No'">
	                        				<label style="font-weight: normal;" for="exampleInputName2">Constipation</label>
	                        			</s:if>
	                        		</p>
	                        		<p style="display:inline-block;margin: 0 0 0px;">
	                        		    <%if(ipd.getSpecialnotes()!=null){ %>
	                        			<label for="exampleInputName2">Special Notes/Remarks : </label><span class="help-block"><%=ipd.getSpecialnotes() %> </span>
	                        			<%} %>
	                        		</p>
		                        </div>
	                        	</div>
	                        	</div>
	                        	 <%if(ipd.getEarlierinvest()!=null){ %>
                       	<div class="row">
                       	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                        		<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2 text-right" style="padding:0px;">
	                        			<div class="form-group">
	                        				 <label for="exampleInputName2" style="text-transform: uppercase;">Earlier Investigations</label> 
	                        			</div>
	                        		</div>
	                        		<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10" style="padding:0px 0px 0px 12px;">
	                        			<div class="form-group">
	                        				 <span><%=ipd.getEarlierinvest() %></span> 
	                        			</div>
	                        		</div>
	                        	</div>
	                        	</div>
                        <%} %>
					</div>
				</div> --%>
				
				
				<%ArrayList<Priscription> dischargePriscList = (ArrayList<Priscription>) session
						.getAttribute("dischargePriscList"); %>
				<%if(dischargePriscList.size()>0){ %>
			<div class="ll3">

				
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12  "
					style="padding-top: 10px;padding-left: 0">
					
						<h4 class="text-left titleset" style="color: #6699cc;">TREATMENT
							ADVICE</h4>
					
					<%--  <%if(!ipdForm.getKunal_manual_medicine_text().equals("")){ %>
	                       <h4 class="text-left titleset" style="color:#6699cc;">TREATMENT ADVICE </h4>
	                       <%} %> --%>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
					<div class=""
						style="padding-left: 0px; padding-right: 0px;">
						<s:if test="admissionPriscList.size>0">
							<div class="form-group">
								<div style="padding-left: 0px;">
									<s:if test="admissionPriscList.size>0">
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
													<td class="uppercaseirf"><%=priscription.getMdicinenametxt()%></td>
													<td><%=priscription.getDosenotes()%></td>
													<s:if test="strengthflag==true">
														<td><%=priscription.getStrengthnew()%><%=priscription.getUnitextension()%></td>
													</s:if>
													
													<td><%=priscription.getRegional() %>   </td>
													<td><%=priscription.getPriscdays()%> Days</td>
													
													
													<td><%=DateTimeUtils.isNull(priscription.getPriscqty())%></td>

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
					
					</div>
				</div>
			</div>
			<%}%>
				
				
				
				<div class="">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:10px 0px 0px 0px;">
						<div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
	                        	<h4 class="text-left titleset" style="color:#6699cc;">PROVISIONAL DIAGNOSIS</h4>
	                        </div>
	                        <div class="row">
	                        		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
                                <div class="col-lg-12 col-md-12 col-xs-12">
                                		<table class="table" style="border: 1px solid #eee;width: 100%;">
                                     <thead> 
                                        <tr> 
                                         <th>Provisional Diagnosis</th> 
                                         <th class="hidden">Treatment Episode</th> 
                                         
                                         </tr> 
                                        </thead> 
                                     <tbody> 
                                        <tr> 
                                        <td>
                                         <table id="innercondition" width="100%" >
                                          <% ArrayList<String> ipdCondtitionList = (ArrayList<String>)session.getAttribute("ipdCondtitionList"); %>
                                          <%  i = 0; for(String str : ipdCondtitionList){%>
                                          
                                           <tr>
                                              <th scope="row"><%=i+1 %></th> 
                                              <td>
                                                <%=str %>
                                                 
                                               </td>
                                               <!-- <td><input type="hidden" name="conditions[0].conditionname" id="conditionname0"/></td> -->
                                           </tr>
                                               
                                           <%i++;} %>
                                        </table>
                                        </td>
                                        
                                          <td id="treatmentepisodeajax" class="hidden"> 
                                           <s:property value="treatmentEpisode"/>
                                         </td>
                                        
                                        
                                        </tr> 
                                        </tbody> 
                                    
                                    </table>
                                
                                </div>
                    </div>
	                        </div>
					</div>
				</div>
				
				<div class="col-lg-12" style="text-align:center ;padding-top: 15px;-webkit-print-color-adjust: exact;">
						<label style="font-size: 22px"></label><!--HIGH RISK CONSENT by   04 oct 2017   -->
					</div>
					
					<div class="col-lg-12 col-md-12" style="text-align: justify;">
					<% String decartionnotes = (String) session.getAttribute("declarationNotes");
						if(decartionnotes==null){
							decartionnotes="";
						}%>
					<%=decartionnotes%> 
					</div>
				<div class="">
	                        <div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
	                        	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padsign">
	                        	
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-left" style="padding:0px;">
										<%if(!loginInfo.getClinicUserid().equals("nelson")){ %>
										<span><s:property value="doctor_name"/><br>
										<s:if test="qualification==null||qualification==''"></s:if><s:else><s:iterator value="qualificationList">  <s:property value="name"/> <br></s:iterator></s:else></span>
										<s:if test="supportiveDoctorName==null||supportiveDoctorName==''"></s:if><s:else><span><s:property value="supportiveDoctorName"/><br>(<s:property value="supportiveQualification"/>)</span></s:else>
										<br><span>[FOR <s:property value = "clinicName"/>]</span>
										<%} %>
									</div>
									
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-right" style="padding-right:15px !important;">
										<br>
										<span>Name & Signature of Patient / Attendant</span>
										<br><br>
										<span>Admitted By: <s:property value="addmitedbyuserid"/> <s:property value="admissiondate" /></span><br>
										<span>Printed By: <s:property value="printedBy"/></span>
									</div>
								</div>
	                        <div class="col-lg-12 col-md-12 hidden-print" style="padding: 10px 0px 0px 0px;">
		                            <div class="">
		                                <div class="col-lg-12 col-md-12" style="padding: 0px;">
		                                <input type="button" class="btn btn-primary savebtn savebigbtn"  value="Print" onclick="printpage()">
		                                </div>
		                            </div>
		                        </div>
	                        </div>
	                        
	                        </div>
					
					
					
					
					
					
					
					
					
				</div>
				
				
				
				
				
				
				
				
				
				
	
	 
 
</s:form>

 	


<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
                            
                            <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
                                <form>
								 <div class="form-group hidden">
                                            <label for="inputEmail" class="control-label">Hospital/Clinic</label>
                                                <p class="help-block"><%=ipd.getHosp() %></p>
                                        </div>
								</form>
                                </div>
                                
                                <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="display:none">
                                                <div class="from-horizontal">


                                                    <div class="form-group col-lg-12 col-md-12 col-xs-12 col-sm-12" style="display:none">
                                                     <div class="col-xs-4 col-md-4 col-lg-4 col-sm-4">
			                                        <label for="inputEmail" class="control-label textright"><b>Suggested Opr.:</b></label>
			                                        </div>
                                                        
                                                        <div class="col-xs-8 col-md-8 col-lg-8 col-sm-8">
                                                              <%=ipd.getSuggestoper() %>
                                                        </div>
                                                    </div>

                                                    <div class="form-group col-lg-12 col-md-12 col-xs-12 col-sm-12" style="display:none">
                                                     <div class="col-xs-4 col-md-4 col-lg-4 col-sm-4">
				                                         <label for="inputEmail" class="control-label textright"><b>Location:</b></label>
				                                        </div>
                                                       
                                                        <div class="col-xs-8 col-md-8 col-lg-8 col-sm-8">
                                                          <%=ipd.getDepartment() %>
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                            </div>
                            </div>

</body>

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
					

</html>
