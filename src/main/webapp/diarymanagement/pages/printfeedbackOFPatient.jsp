<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%> 
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<style>
@media print {
	.hh{
	color:white !important;
	}
}
img{
width: 16%;
}

label{
display: inline !important;
}
</style>
<script type="text/javascript" src="diarymanagement/js/feedback.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
	<div class="print-visible hidden-md hidden-lg letterheadhgt" style="height: 135px;">
		<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
				 <link href="common/css/printpreview.css" rel="stylesheet" />
			<%@ include file="/accounts/pages/letterhead.jsp" %>
			</div>
		</div>
	</div>
<div class="row details row details mainheader">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

</div><center><h4 class='hh'><i class="fa fa-comments"></i> &nbsp;Feedback Print</h4></center>
</div>

<div class="row ">

	<%Client clientDetails=(Client)request.getAttribute("clientData"); %>
	<%Client feedbackDetails=(Client)request.getAttribute("FeedBack"); %>			
	
	<%feedbackDetails.setAdmissiondate(DateTimeUtils.isNull(feedbackDetails.getAdmissiondate())); %>
			
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

	
		<div class="form-inline">
	
		<div align="right" class="form-inline"><a type="button" class="btn btn-info hidden-print"  title="Print" onclick="printpage()">Print</a></div>
<br>	
<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
<label>Client Name :</label>	<span><s:property value='client'/></span><br>
<label>Age / Gender:</label>	<span><%=clientDetails.getAge1() %>/ <%=clientDetails.getGender() %></span>
<br><label>Address:</label>	<span><%=clientDetails.getAddress() %>, <%=clientDetails.getTown() %>, <%=clientDetails.getCounty() %></span>
<br><label>Contact No :</label>	<span><%=clientDetails.getMobNo() %></span>
<br><label>Date :</label>	<span><s:property value='date'/></span>
</div>
<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
<label>UHID :</label>	<span><%=clientDetails.getAbrivationid() %></span>

<%if(feedbackDetails.getIpdid().equals("0")){ %>
<br><label>OPD ID :</label>	<span><%=feedbackDetails.getOpdid() %></span>
<%}else{ %>
<br><label>IPD ID :</label>	<span><%=feedbackDetails.getIpdid() %></span>
<%if(feedbackDetails.getAdmissiondate().contains("-")){ %>
	<br><label>Admission Date :</label>	<span><%= DateTimeUtils.getCommencingDate1(feedbackDetails.getAdmissiondate().split(" ")[0])  %> <%=feedbackDetails.getAdmissiondate().split(" ")[1] %></span>
<%} %>
<%} %>
</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<p><strong>Dear Patient,</strong></p>
<p>Your feedback is valuable to us.Kindly Rank our services and performance to help us serve better.</p>
<%if(loginInfo.isBalgopal()){ %>
<p>बाल गोपाल की पूरी टीम आपके परिवार /बच्चों  के बेहतर स्वास्थ के लिए वचन बद्ध है |  इस प्रक्रिया में आपका सुझाव हमें अपनी कार्यप्रणाली  को बेहतर बनाने में बड़ी भूमिका अदा कर सकता है |</p>
<%}else{ %>
<p><%=loginInfo.getClinicName() %> की पूरी टीम आपके परिवार /बच्चों  के बेहतर स्वास्थ के लिए वचन बद्ध है |  इस प्रक्रिया में आपका सुझाव हमें अपनी कार्यप्रणाली  को बेहतर बनाने में बड़ी भूमिका अदा कर सकता है |</p>
<%} %>
</div>
<table  class="my-table xlstable " style="width: 100% ;border:1px">
<tr style="color: white;">
<td style="background-color:#1c4773 "><b>Feedback Template</b></td>
<td style="background-color:#1c4773;width: 25% "><b>ratings</b></td>
</tr >

<%ArrayList<Client> feedbacklist= (ArrayList<Client>) request.getAttribute("feedbacklist"); %>
<%int i=0; %>
<s:iterator value="feedbacklist">
<tr style="border:1px #dfd8d4 solid !important">
<td><%= DateTimeUtils.isNull(feedbacklist.get(i).getFeedbackname()).toString()%></td>
<%i++; %>
<td><%-- <meter value="<s:property value="rating"/>" min="0" max="5"></meter>  <s:property value="rating"/> / 5 --%>
<label>
				<s:if test="rating==1">
 					 <img src="popicons/emoji/verysad.png" title="Very Poor" style="outline: 2px solid #f00;">
 			    </s:if>
 			    <s:else>
 			    <img src="popicons/emoji/verysad.png" title="Very Poor">
 			    </s:else>
				</label>
				<label>
				<s:if test="rating==2">
					  <img src="popicons/emoji/sad.png" title="Poor" style="outline: 2px solid #f00;">
				</s:if>
				<s:else>
				<img src="popicons/emoji/sad.png" title="Poor"  >
				</s:else>	  
				</label>
				<label>
				<s:if test="rating==3">
					  <img src="popicons/emoji/avrge.png" style="width: 14%;outline: 2px solid #f00;" title="Satisfactory">
				</s:if>
				<s:else>
				 <img src="popicons/emoji/avrge.png" style="width: 14%" title="Satisfactory">
				</s:else>
				</label>
				<label>
				<s:if test="rating==4">
					  <img src="popicons/emoji/happy1.png" style="outline: 2px solid #f00" title="Good">
				</s:if>
				<s:else>
				<img src="popicons/emoji/happy1.png" title="Good">
				</s:else>
				</label>
				<label>
				<s:if test="rating==5">
					  <img src="popicons/emoji/very happy.png" title="Very Good"  style="outline: 2px solid #f00;">
					</s:if>
				<s:else>
				<img src="popicons/emoji/very happy.png" title="Very Good" >
				</s:else>  
				</label>
</td>
</tr>
</s:iterator>
</table>
</div>
</div>
<br>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 ">
<br><p>उन स्टाफ का नाम लिखे जिनके कार्य एव  व्यव्हार से आप ज्यादा प्रभावित हुए।</p> <br>
<p>अ. सीनियर  डॉ. &emsp;&emsp;&nbsp;1._______________________________&emsp;&emsp;2.______________________________</p>
<p>ब. जूनियर  डॉ.&emsp;&emsp;&emsp;1._______________________________&emsp;&emsp;2.______________________________</p>
<p>क. नर्सिंग स्टॉफ &emsp;&emsp;1._______________________________&emsp;&emsp;2.______________________________</p>
<p>ड. रिसेप्शन &emsp;&emsp;&emsp;&nbsp;&nbsp;1._______________________________&emsp;&emsp;2.______________________________</p>
<br>
 <%if(loginInfo.isBalgopal()){ %>
<p>बाल गोपाल हॉस्पिटल के प्रति आपके विचार या सुझाव </p>
<%}else{ %>
<p><%=loginInfo.getClinicName() %> के प्रति आपके विचार या सुझाव </p>
<%} %>
<p>_________________________________________________________________________________________________________________________________________________________________________________
____________________________________________________________________________________________________________________________________________________________________________________
____________________________________________________________________________________________________________________________________________________________________________________
___________________________________________________________________________________</p>
<p>Thank you <strong><s:property value="client"/></</strong> for taking the time to fill our feedback form. By providing your valuable feedback you are helping us to understand what we do better and what improvements  we need to implement </p>
<p> Your Remark was :  <%=DateTimeUtils.isNull((String)request.getAttribute("manualfeedback")).toString() %></p>
<p><b>Date: </b>  <s:property value="date"/></p>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 print-visible hidden-md hidden-lg" align='right'>
<br>
<br>
<br>
<br>

<h3>Signature</h3>

<p></p>
<p>Information within feedback form will be used for service improvement only and will be confidential</p>
</div>
</div>

</div>