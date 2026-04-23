<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
<style>
.hidekar{
display: none;
}
.mybtn{
	position: absolute !important;
    font-size: 14px !important;
    color: #fff !important;
    background-color: #15536E !important;
    border-color: #15536E !important;
    margin-right: -203px !important;
    border-radius: 1.25rem ;
}
.clinicname {
    font-size: 26px !important;
}
#lttertext{
margin-left: -74px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YUVICARE</title>
</head>
<body >
<%int i=1; %>
<table class="my-table" style="font-family: 'Open Sans', sans-serif !important;width: 100%">
<thead>
<tr>
<th style="text-align: center;">Sr No.</th>
<th style="text-align: center;">Hospital Name</th>
<th style="text-align: center;">Book Appointment</th>
</tr>
</thead>
<tbody>
<s:iterator value="allhospital">
<tr>
<td style="text-align: center;"><%=i++ %></td>
<td style="text-align: center;">
<div class="row letterheadhgt" style="height: 135px;">
		<div class="col-lg-1 col-md-1"></div>
		<div id="newpost" class="col-lg-10 col-md-10 col-xs-12 col-sm-12 borderbot">
				 	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" id="ltterimg">
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 logoimg">
			<div id="newletter">
				<img class="img-responsive logoste1" src="liveData/clinicLogo/<s:property value="clinicLogo"/>" />
				</div>
			</div>
			
		<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" id="lttertext">
	
			<div class="clinicname" id="clinicnamenew">
				<s:property value = "clinicName"/>
			
			
			
			</div>
			<S:IF TEST="CLINICOWNER!='' ">
				<DIV CLASS="CLIQUALIF"><S:PROPERTY VALUE = "CLINICOWNER"/> <S:PROPERTY VALUE = "OWNER_QUALIFICATION"/> </DIV>
				<s:if test="clinicOwner!='' ">
				
				<div class="cliqualif" style="font-weight: lighter;"><s:property value = "clinicOwner"/> </div>
			</s:if>
			</S:IF>
			<div class="clicniaddress">
					<s:property value = "address"/> <br>
				
			</div>
			<div class="bottext">Phone:<s:property value ="landLine"/>,	 Email: <s:property value = "clinicemail"/>
				
				<s:property value = "owner_qualification"/>
				<s:if test="websiteUrl!='' ">
					<span style="white-space: nowrap;">Website: <s:property value = "websiteUrl"/></span>
				</s:if>
			 </div>
		
		</div>
	</div>
		</div>
		<div class="col-lg-1 col-md-1"></div>
	</div>
	</td>
	<td style="text-align: center;">
	<a class="btn mybtn" style="margin-left: -67px;" href="http://<s:property value="linkaddress"/>:8080/YUVICARE/Pureseva?title=&firstname=&lastname=&email=&clinicid=<s:property value="userId"/>&mob=&date=&diaryuserid=&gender=&dob=&uhid=&dept=">Book Appointment</a>
	</td>
	</tr>
</s:iterator>
</tbody>
</table>
</body>
</html>