<%@page import="com.apm.main.common.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	 <%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>

<style>
.infoback{
	background-color: #6699CC;
    padding: 10px 10px 0px 10px !important;
    margin-bottom: 10px;
    color:#ffffff;
}
.nav-tabs {
    border-bottom: 1px solid #ddd;
    background-color: #f5f5f5;
}
.nav>li>a {
    position: relative;
    display: block;
    padding: 10px 15px;
    color: #555;
    font-size: 12px;
    font-weight: bold;
}
.nav-tabs>li {
    float: left;
    margin-bottom: -1px;
    cursor: pointer;
    border: 1px solid #ddd;
}
.counter{
	background-color: #e22b2b;
    color: #fff;
    border-radius: 50%;
    padding: 2px 5px 2px 5px;
    text-align: right;
}
.sethead{
	float: right !important;
    margin-top: 0px !important;
    border: none !important;
    padding-right: 10px !important;
    color: #fff!important;
    letter-spacing: 1px !important;
    background-color: #6699CC !important;
    padding: 11px !important;
}
.blink_me {
  animation: blinker 1s linear infinite;
  color:red;
}

@keyframes blinker {  
  50% { opacity: 0; }
}
</style>
<script type="text/javascript" src="diarymanagement/js/diarymanagement.js"></script>
</head>
<body>

<div role="tabpanel" style="padding: 0px !important;">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#email" aria-controls="email" role="tab" data-toggle="tab" aria-expanded="true">Query Dashboard</a></li>
                            <%-- <li role="presentation" class=""><a href="#history" aria-controls="history" role="tab" data-toggle="tab" aria-expanded="false">History Dashboard <span class="counter">2</span></a></li> --%>
                            <li class="sethead"><s:property value="clinicname"/> &nbsp; | &nbsp; <s:property value="userid"/></li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="email" style="padding: 0px !important;">
                            	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
									<div>	
									<div class="form-group">	
									
									<!-- <MARQUEE BEHAVIOR=ALTERNATE  style="color: red;font-size: 140%;" scrollamount="2"><p align="center" style="padding-left: 100px;padding-right: 100px;font-family: -webkit-body;">IF YOU ARE UNABLE TO LOGIN OR YUVICARE IS NOT AVAILABLE THEN CALL 9404022226
										</p ><p align="center" style="padding-left: 100px;padding-right: 100px;font-family: -webkit-body;">OTHERWISE PLEASE SUBMIT ONLINE SERVICE REQUEST	</p>	</MARQUEE>
								 -->
									</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="border: 1px solid #ddd;">
											<h3 style="color:#339966">Leave a Message</h3>
												<small>Your clinic details will not be published. Required fields are marked *</small>
											 <div class="form-group" style="margin-top: 10px;">
											 <div class="col-lg-4 col-md-4 col-xs-4">
											    <label for="exampleInputEmail1">Query Type <span style="color:red;">*</span></label>
											    <select id="query_type" class="form-control" required>
												  <option value="0">Select Query Type</option>
												  <option value="Improvement">Improvement</option>
												  <option value="Compatibility Issue">Compatibility Issue</option>
												  <option value="User Training">User Training</option>
												  <option value="Billing Support">Billing Support</option>
												  <option value="Data Correction">Data Correction</option>
												  <option value="Report Requirement">Report Requirement</option>
												  <option value="Performance Issue">Performance Issue</option>
												  <option value="Feedback">Feedback</option>
												</select>
											</div>
											
											
											<div class="col-lg-4 col-md-4 col-xs-4">
											 <label>Select Module : </label>
											<s:select list="moduleList" listKey="name"
												listValue="name" headerKey="" headerValue="Select module"
												cssClass="form-control chosen-select"
												 name='modulename'
												id='modulename'>
											</s:select>
											</div>
											
											<div class="col-lg-4 col-md-4 col-xs-4">
											 <label>Add Alternate Mobile Number : </label>
											 <s:textfield  name="altmobno" id="altmobno"
												cssClass="form-control" theme="simple" style="width:100%;" placeholder=" Send Mob. No."></s:textfield>
											</div>	
											
											
											  </div>
											  <div class="form-group">
											    <label for="exampleInputEmail1">Comment <span style="color:red;">*</span></label>
											    <textarea name="message" id="message" class="form-control" rows="4" required></textarea>
											  </div>
											  <div class="form-group text-right">
											  	<!--<a href="#" type="submit" class="btn btn-primary">Submit</a>-->
											  	<input type="button" value="Submit" class="btn btn-primary" onclick="submitSupportMail()" id="submitbutt">
											  </div>
										</div>
										
										<div class="col-lg-2 col-md-2 col-xs-2" style="padding:0px;margin-top:10px;">
									<span style="color: #a94442;"><a onclick="openBlankPopup('http://<%=Constants.SUPPORT_HOST%>:8080/YUVICARE/requestqueueSupport?clinicid=<%=loginInfo.getClinicUserid() %>&ownuserid=<%=loginInfo.getUserId()%>')" ><button class="btn btn-primary" >My Tickets</button></a></span> 
					  		 <%-- <span style="color: #a94442;"><a onclick="openBlankPopup('http://localhost:8080/YUVICARE/requestqueueSupport?clinicid=<%=loginInfo.getClinicUserid() %>&ownuserid=<%=loginInfo.getUserId()%>')" ><button class="btn btn-primary" >My Tickets</button></a></span> --%>
						
										</div>
										
										
										<%if(loginInfo.getUserType()==2&&(loginInfo.isSupport_Access())){ %>
										<div class="col-lg-2 col-md-2 col-xs-2" style="padding:0px;margin-top:10px;">
											<span style="color: #a94442;"><button class="btn btn-danger" onclick="openPopup('adminrequestqueueSupport')">All Tickets</button></span>
										</div>
										<%} %>
										
										
										<div class="col-lg-3 col-md-3 col-xs-3" style="padding:0px;margin-top:10px;">
										<input type="button" class='btn btn-primary' value='Released Notes'  onclick="openPopup('all_relesed_notesCommonnew')">
										</div>
										
										
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-top:10px;">
											<span style="color: #a94442;"><b>Note:</b> Please share your AnyDesk Id or Skype Id.</span>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-top:10px;">
											<span style="color: green;">Download AnyDesk</span>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-top:10px;">
											<span style="color: green;">1) Windows <a href="http://escapeq.com/software/AnyDesk.exe" target="_new">click here</a></span>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-top:10px;">
											<span style="color: green;">2) Linux <a href="https://anydesk.com/download?os=linux" target="_new">click here</a></span>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-top:10px;">
											<span style="color: green;">3) macOS <a href="https://anydesk.com/download?os=mac" target="_new">click here</a></span>
										</div>
										<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-top:10px;">
											<span>After submitting the comment your ticket number is genrated and our support team will contact you as soon as possible.</span>
										</div>
										</div>
								</div>
                            </div>

                            <div role="tabpanel" class="tab-pane" id="history" style="padding:0px;">
                            	<table class="table table-hover">
                            		<thead>
                            			<tr>
                            				<th style="width: 8%;">#Tkt No</th>
                            				<th style="width: 17%;">Date | Time</th>
                            				<th style="width: 15%;">Query</th>
                            				<th style="width: 35%;">Comment</th>
                            				<th>Query Owner</th>
                            				<th style="text-align: right;">Status</th>
                            			</tr>
                            		</thead>
                            		<tbody>
                            			<tr>
                            				<td>#121</td>
                            				<td>1706/2017 | 04:47 PM</td>
                            				<td>Improvement</td>
                            				<td>Improve your Module</td>
                            				<td>--</td>
                            				<td style="text-align: right;"><span  class="blink_me"><i class="fa fa-circle" aria-hidden="true"></i></span> Pending</td>
                            			</tr>
                            			<tr>
                            				<td>#122</td>
                            				<td>1706/2017 | 04:45 PM</td>
                            				<td>Improvement</td>
                            				<td>Improve your Module</td>
                            				<td>Adarsh Pande</td>
                            				<td style="text-align: right;"><span><i class="fa fa-spinner fa-pulse text-danger"></i></span> Process</td>
                            			</tr>
                            			<tr>
                            				<td>#123</td>
                            				<td>1706/2017 | 04:40 PM</td>
                            				<td>Improvement</td>
                            				<td>Improve your Module</td>
                            				<td>Adarsh Pande</td>
                            				<td style="text-align: right;"><span><i class="fa fa-check text-success"></i></span> Completed</td>
                            			</tr>
                            			<tr>
                            				<td>#124</td>
                            				<td>1706/2017 | 04:35 PM</td>
                            				<td>Improvement</td>
                            				<td>Improve your Module</td>
                            				<td>Ajay Agrawal</td>
                            				<td style="text-align: right;"><span><i class="fa fa-check text-success"></i></span> Completed</td>
                            			</tr>
                            		</tbody>
                            	</table>
                            </div>
                        </div>
                    </div>




</body>
</html>