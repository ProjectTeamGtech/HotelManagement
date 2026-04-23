<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>




<script type="text/javascript" src="diarymanagement/js/feedback.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>

 <%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>
<style>

.my-table td{
font-size: small !important;
}

 

.rating {
    float:left;
}
label{
display: inline !important;
}
/* :not(:checked) is a filter, so that browsers that don’t support :checked don’t 
   follow these rules. Every browser that supports :checked also supports :not(), so
   it doesn’t make the test unnecessarily selective */
/* .rating:not(:checked) > input {
    position:absolute;
    top:-9999px;
    clip:rect(0,0,0,0);
}

.rating:not(:checked) > label {
    float:right;
    width:1em;
    padding:0 .1em;
    overflow:hidden;
    white-space:nowrap;
    cursor:pointer;
    font-size:200%;
    line-height:1.2;
    color:#ddd;
    text-shadow:1px 1px #bbb, 2px 2px #666, .1em .1em .2em rgba(0,0,0,.5);
}

.rating:not(:checked) > label:before {
    content: '';
}

.rating > input:checked ~ label {
    color: #f70;
    text-shadow:1px 1px #c60, 2px 2px #940, .1em .1em .2em rgba(0,0,0,.5);
}

.rating:not(:checked) > label:hover,
.rating:not(:checked) > label:hover ~ label {
    color: gold;
    text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
}

.rating > input:checked + label:hover,
.rating > input:checked + label:hover ~ label,
.rating > input:checked ~ label:hover,
.rating > input:checked ~ label:hover ~ label,
.rating > label:hover ~ input:checked ~ label {
    color: #ea0;
    text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
}

.rating > label:active {
    position:relative;
    top:2px;
    left:2px;
} */
   [type=radio] { 
  position: absolute;
  opacity: 0;
  width: 0;
  height: 0;
} 

/* IMAGE STYLES */
[type=radio] + img {
  cursor: pointer;
}

/* CHECKED STYLES */
 [type=radio]:checked + img {
  outline: 2px solid #f00;
}  
img{
width: 10%;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<div class="row details row details mainheader">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
</div><center><h4><i class="fa fa-comments"></i> &nbsp; Feedback Form</h4></center>
</div>
	<div class="row ">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<br>									
<s:form action="feedbackFormClient" theme="simple"   align="center"  id = "feedbackform">


<div class="form-inline">
			<div class="form-group">
			<div class="form-group" style="width: 18%">
		
				<s:select name="treatmenttype" id="treatmenttype" 
				list="#{'opd':'OPD','ipd':'IPD','hd':'HD'}"
				cssClass="form-control chosen-select" onchange="getlist(this.value)"></s:select>
			</div>
			
			<div class="form-group">
				<s:textfield  name="patient" id="client" readonly="true"
				  cssClass="form-control" onclick="showPopUpwithfilter()" placeholder="Search by patient"></s:textfield>
			</div>
					
					<div class="form-group hidden">
					<s:submit cssClass="btn btn-primary" value="go"></s:submit>
					</div>
					<div class="form-group">
					 <button type="button" class="btn btn-success"  onclick="opencPopup('seeallFeedbacksClient')">See All feedBacks</button>
				
					</div>
			
			</div>
			</div>
			
			</s:form>
			<div class="form-group ">
					<button  onclick="allfeedbackpatients()" class="btn btn-primary hidden">Patients</button>
					</div>	
			<p><strong>Dear Patient,</strong></p>
			<p>Your feedback is valuable to us.Kindly Rank our services and performance to help us serve better.</p>
			<%if(loginInfo.isBalgopal()){ %>
			<p>बाल गोपाल की पूरी टीम आपके परिवार /बच्चों  के बेहतर स्वास्थ के लिए वचन बद्ध है |  इस प्रक्रिया में आपका सुझाव हमें अपनी कार्यप्रणाली  को बेहतर बनाने में बड़ी भूमिका अदा कर सकता है|</p>
			<%}else{ %>
			<p><%=loginInfo.getClinicName() %> की पूरी टीम आपके परिवार /बच्चों  के बेहतर स्वास्थ के लिए वचन बद्ध है |  इस प्रक्रिया में आपका सुझाव हमें अपनी कार्यप्रणाली  को बेहतर बनाने में बड़ी भूमिका अदा कर सकता है|</p>
			<%} %>
			<p>The ranking may be done on 1 to 5 scale where:</p>
			<p>1 -<b> Very Poor</b>  &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;        2 -<b> Poor </b>   &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;    3 -<b> Satisfactory</b>
		 &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;  4 -<b> Good  </b>    &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;  &nbsp;&nbsp; &nbsp; &nbsp;    5 - <b>Very Good </b></p>
			<p>[Please give your free and frank opinion. This information will be kept confidential and shall be used to improve our performance]</p>
						
			<s:form action="savefeedbackClient" theme="simple"  cssClass="modal-content "   id = "invoicerportfrm">
			<table class="my-table xlstable " style="width: 100%">
			<tr></tr>
			<tr>
			<th width="5%"> &nbsp;&nbsp;Sr. no</th>
			<th>Feedback Template</th>
			<th style='width:23%;'>Rating</th>
			
			<s:hidden name="feedbackids" id="feedbackids"/>
			<s:hidden name="patient" id="patient"/>
				<s:hidden name="treatmenttype" id="treatmenttype1"/>
			
			</tr>
			
			<%ArrayList<Client> list=(ArrayList<Client>)request.getAttribute("feedbacklist");
			int val=0;
			%>
			<% int i=0; %>
				<s:iterator value="feedbacklist">
			<tr><td> &nbsp;&nbsp;<%=++i %></td>
				
				 <td><%=list.get(val).getFeedbackname().toString() %><input type="hidden" name='feedback<s:property value="feedbackid"/>' value='<s:property value="feedbackname"/>'/></td> 
		<td>
   					<!-- <fieldset class=""> -->
   				<label>
 					 <input type="radio" name="rating<s:property value="feedbackid"/>" value="1"  id="star1<%=val %>" title="Very Poor">
 					 <img src="popicons/emoji/verysad.png" title="Very Poor">
				</label>
				<label>
					  <input type="radio" name="rating<s:property value="feedbackid"/>" value="2" id="star2<%=val %>" title="Poor">
					  <img src="popicons/emoji/sad.png" title="Poor">
				</label>
				<label>
					  <input type="radio" name="rating<s:property value="feedbackid"/>" value="3" id="star3<%=val %>" title="Satisfactory">
					  <img src="popicons/emoji/avrge.png" style="width: 9%" title="Satisfactory">
				</label>
				<label>
					  <input type="radio" name="rating<s:property value="feedbackid"/>" value="4"  id="star4<%=val %>" title="Good">
					  <img src="popicons/emoji/happy1.png" title="Good">
				</label>
				<label>
					  <input type="radio" name="rating<s:property value="feedbackid"/>" value="5" id="star5<%=val %>" title="Very Good">
					  <img src="popicons/emoji/very happy.png" title="Very Good">
				</label>
					   <%--  <input type="radio" id="star5<%=val %>"  name="rating<s:property value="feedbackid"/>" value="5" /><label for="star5<%=val %>" title="Rocks!">5 stars</label>
					    <input type="radio" id="star4<%=val %>"  name="rating<s:property value="feedbackid"/>" value="4" /><label for="star4<%=val %>" title="Pretty good">4 stars</label>
					    <input type="radio" id="star3<%=val %>"  name="rating<s:property value="feedbackid"/>" value="3" /><label for="star3<%=val %>" title="Meh">3 stars</label>
					    <input type="radio" id="star2<%=val %>"  name="rating<s:property value="feedbackid"/>" value="2" /><label for="star2<%=val %>" title="Kinda bad">2 stars</label>
					    <input type="radio" id="star1<%=val %>"  name="rating<s:property value="feedbackid"/>" value="1" /><label for="star1<%=val %>" title="Sucks big time">1 star</label> --%>
					<!-- </fieldset> -->
					<%val++; %>
   					
  		</td>
			</tr>
			</s:iterator>
		</table>
		<br>
		 <div class="form-group col-lg-12 col-md-12 col-xs-12 col-sm-12">
		 <p>उन स्टाफ का नाम लिखे जिनके कार्य एव  व्यव्हार से आप ज्यादा प्रभावित हुए।</p> <br>
<p>अ. सीनियर  डॉ. &emsp;&emsp;&nbsp;1._______________________________&emsp;&emsp;2.______________________________</p>
<p>ब. जूनियर  डॉ.&emsp;&emsp;&emsp;1._______________________________&emsp;&emsp;2.______________________________</p>
<p>क. नर्सिंग स्टॉफ &emsp;&emsp;1._______________________________&emsp;&emsp;2.______________________________</p>
<p>ड. रिसेप्शन &emsp;&emsp;&emsp;&nbsp;&nbsp;1._______________________________&emsp;&emsp;2.______________________________</p>
<br>
    <label for="inputlg">Your Valued Remark, Suggestion For Improving Our Performance </label><br>
    <%if(loginInfo.isBalgopal()){ %>
    <label for="inputlg">बाल गोपाल हॉस्पिटल के प्रति आपके विचार या सुझाव :</label>
    <%}else{ %>
    <label for="inputlg"><%=loginInfo.getClinicName() %> के प्रति आपके विचार या सुझाव :</label>
    <%} %>
    <textarea class="form-control input-lg" id="inputlg"  name="manualfeedback" ></textarea>
  &nbsp;&nbsp;&nbsp;<p>Thank you  for taking the time to fill our feedback form. By providing your valuable feedback you are helping us to understand what we do better and what improvements  we need to implement </p>
  <p>Information within feedback form will be used for service improvement only and will be confidential.</p>
  </div>
  
		<br>
<center><%-- <s:submit value="Save FeedBack" align="center" theme="simple" cssClass="btn btn-primary" style="margin:5px" onclick="return checkifChecked()"></s:submit> --%>
	<input type="button" value="Save FeedBack" id="savefdfrm" style="text-align: center;margin:5px" onclick="checkifChecked()" class="btn btn-primary"></center>
</s:form>	
</div>


<input type="hidden" id='feedb'>
	<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
			

<br>
<br>
<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>

</div>
