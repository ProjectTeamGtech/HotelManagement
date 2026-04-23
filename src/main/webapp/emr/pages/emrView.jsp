<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<script type="text/javascript" src="emr/js/emr.js"></script>
<script type="text/javascript" src="emr/js/consultation.js"></script> 
<script type="text/javascript" src="emr/js/reminder.js"></script>
<script type="text/javascript" src="emr/js/social.js"></script>
<script type="text/javascript" src="emr/js/prescription.js"></script>
<script type="text/javascript" src="emr/js/documentation.js"></script>
<script type="text/javascript" src="emr/js/allergy.js"></script>

<script type="text/javascript">
var selectedPatientId = 0;
var pid = 0;
var name = null;

$(document).ready(function() {
	
	
	document.getElementById('sortable1').style.display = '';
	document.getElementById('sortable').style.display = 'none';
	
	
	$(function() {
	    $( "#sortable" ).sortable({
	      revert: true
	    });
	    /* $( "#draggable" ).draggable({
	      connectToSortable: "#sortable",
	      helper: "clone",
	      revert: "invalid"
	    }); */
	   // $( "ul, li" ).disableSelection();
	  });

	$("#commencing").datepicker({

		dateFormat : 'dd/mm/yy',
		changeMonth : true,
		changeYear : true

	});
	
	
	$("#alert").datepicker({

		dateFormat : 'dd/mm/yy',
		changeMonth : true,
		changeYear : true

	});
	
	$("#editalert").datepicker({

		dateFormat : 'dd/mm/yy',
		changeMonth : true,
		changeYear : true

	});
	
	
	var apmtid = document.getElementById('selectedid').value;
	selectedPatientId = document.getElementById('selectedPatientId').value;
	
	$(document.getElementById('data'+apmtid)).css('background-color','orange');
	$(document.getElementById('img'+apmtid)).css('background-color','orange'); 
	
	//medical history 
	document.getElementById('addemrHisory').style.display = 'none';
	document.getElementById('viewmh').style.display = 'none';
	document.getElementById('editmh').style.display = 'none';
	
	//consultation Note
	document.getElementById('addconshistory').style.display = 'none';
	//document.getElementById('viewcons').style.display = 'none';
	document.getElementById('editconsult').style.display = 'none';
	
	//Documentation
	document.getElementById('adddocument').style.display = 'none';
	document.getElementById('viewdoc').style.display = 'none';
	document.getElementById('editdoc').style.display = 'none';
	
	//social History
	//document.getElementById('socialHistoryList').style.display = '';
	document.getElementById('addsocialHistory').style.display = 'none';
	document.getElementById('viewsh').style.display = 'none';
	document.getElementById('editsh').style.display = 'none';
	
	//reminder History
	document.getElementById('addreminder').style.display = 'none';
	document.getElementById('viewrem').style.display = 'none';
	document.getElementById('editrem').style.display = 'none';
	
	//prescription History
	document.getElementById('addprescription').style.display = 'none';
	document.getElementById('viewpre').style.display = 'none';
	document.getElementById('editpre').style.display = 'none';
	
	//Allergy
	document.getElementById('addallergy').style.display = 'none';
	document.getElementById('viewall').style.display = 'none';
	document.getElementById('editall').style.display = 'none';
	
	document.getElementById('report').style.display = 'none';
	document.getElementById('reportdoc').style.display = 'none';
	
	//client
	document.getElementById('clientdata').style.display = '';

	$('#sortable.a').css('color', '#7ecefd');

	
<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	/* String id = Integer.toString(loginInfo.getId());
	session.setAttribute("id", id); */
%>
	
	if(document.getElementById('diaryUser').value!=0){
		<%if(loginInfo.getUserType()==2){%>
		pid = document.getElementById('diaryUser').value;
		document.getElementById('sortable').style.display = '';
		document.getElementById('sortable1').style.display = 'none';
				
	<%}%>
	}	
	
if(document.getElementById('diaryUser').value==0){
	<%if(loginInfo.getUserType()==2){%>
	//pid = document.getElementById('diaryUser').value;
	document.getElementById('sortable').style.display = 'none';
	document.getElementById('sortable1').style.display = '';
			
<%}%>
}

if(document.getElementById('client').value!=""){
	<%if(loginInfo.getUserType()==2){%>
		document.getElementById('sortable').style.display = '';
		document.getElementById('sortable1').style.display = 'none';
	<%}%>
}


});


</script>

<style>
	
</style>


<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Emr</a></li>
			
		</ol>
	</div>
</div>

	<div class="row">
		<div class="col-lg-6">
			<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
			<%if(loginInfo.getUserType() == 2){ %>
				<s:form action="inputEmr" id="adminemrFrm">
				
						<div class="col-lg-4 col-md-4">
							<label>Practitioner:</label>
							<s:if test="%{#userList != 'null'}" >
								<s:select cssStyle="height: 20px;
								padding: 0px" cssClass="text ui-widget-content ui-corner-all" id="diaryUser" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User" onchange="setAdminEmr(this.value)"   />
						</s:if>
					</div>
					
				</s:form>
			<%} %>
				
			</s:if>
			
	</div>
</div>

<div class="row">
		<div class="col-lg-12">
		<div class="col-lg-3"></div>
	<s:form action="showDetailsByClientEmr" theme="simple" >
	<div class="col-lg-1"></div>
	<div class="col-lg-1 col-md-1">
		<label>Client</label>
	</div>
	<div class="col-lg-3 col-md-3">
		<s:textfield name="client" id="client" readonly="true" cssClass="form-control" 
				     onclick="showEmrPopUp()" data-toggle="modal" data-target="#clientSearch"/>
		<s:hidden name="clientId" id="clientId"></s:hidden>
	</div>
	<div class="col-lg-1 col-md-1">
		<s:submit cssClass="btn btn-primary" value="Go"/>
	</div>
	</s:form>
	</div>
</div>

<!-- Modal Client Search-->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/tools/pages/allTemplatePatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<br/>

 <s:hidden name="selectedid" id="selectedid"></s:hidden> 
 <s:hidden name="selectedPatientId" id="selectedPatientId"></s:hidden>

<div class="col-lg-3 col-md-3"  style="border-bottom:1px solid #DFD8D4;border-left:1px solid #DFD8D4;border-top:1px solid #DFD8D4;">
<div align="center">
	<s:form action="showEmr" theme="simple" id="emrfrm">
		<s:textfield readonly="true" name="commencing" id="commencing" cssStyle="width:94%"
			cssClass="form-control" theme="simple" onchange="emrdata(this.value)"></s:textfield>
		</s:form>
	</div>
	<div style = "height:1000px; overflow-y: auto;">
		<table width="100%">
			<s:iterator value="emrList">
				<s:url action="Emr" id="Emr">
					<s:param name="id" value="%{id}"/>
					<s:param name="appointmentid" value="%{appointmentid}"/>
					<s:param name="patientName" value="%{patientName}"/>
					<s:param name="dob" value="%{dob}"/>
					<s:param name="age" value="%{age}"/>
					<s:param name="gender" value="%{gender}"/>
					<s:param name="commencing" value="%{commencing}"/>
					<s:param name="conditionName" value="%{conditionName}"/>
					<s:param name="treatmentEpisodeName" value="%{treatmentEpisodeName}"/>
				</s:url>
				
				<tr>
					<td valign="top" style="padding: 5px;">
					<s:a href="%{Emr}">
						<table onclick="selectedUser('<s:property value="id"/>','<s:property value="appointmentid"/>','<s:property value="patientName"/>','<s:property value="dob"/>','<s:property value="age"/>','<s:property value="gender"/>')" id="<s:property value="id"/>" width="100%" style="border: 1px solid red; padding: 2px; background-color: rgb(193, 193, 226) ;cursor: pointer;">
							<col width="80%"/>
							<col width="20%"/>
							<tr style="border: 1px solid red;  background-color: rgb(193, 193, 226)">
								<td style="padding: 2px;" id="data<s:property value="appointmentid"/>">
									<label style="cursor: pointer; color: black;"><b><s:property value="starttime"/>-<s:property value="endTime"/></b></label><br>
									<label style="font-weight: bold;cursor: pointer;"><font color="white"><s:property value="patientName"/></font></label><br>
									<label style="cursor: pointer;"> <font color="gray"><s:property value="gender"/>,Age : <s:property value="age"/></font></label><br>
								</td>
								<td id="img<s:property value="appointmentid"/>" valign="top" style="padding: 5px;"><img src="common/images/user.jpg"/></td>
							</tr>
						</table>
					</s:a>
					</td>
				</tr>
			</s:iterator>	
		
		</table>
	</div>

</div>


<div id="sortable1" class = "col-lg-9 col-md-9" style="border-bottom:1px solid #DFD8D4;border-right:1px solid #DFD8D4;border-top:1px solid #DFD8D4; border-left:1px solid #DFD8D4;" >

<table width="100%" style="border: 1px solid #e69623; height:800px ; padding: 1em; ">
 			<tr>
 				<td align="center">
 					<font size="3" color="red"><b>No Record to Display !!</b></font>		
 				</td>
 			</tr>
 		</table>
 		
</div>
<div id="sortable" class = "col-lg-9 col-md-9" style="border-bottom:1px solid #DFD8D4;border-right:1px solid #DFD8D4;border-top:1px solid #DFD8D4; border-left:1px solid #DFD8D4;" >

<%-- <div class="row">
	<div class= "col-lg-3 col-md-3">
		<img src="common/images/userc.jpg"/>
	</div>
	<div id="docdiv" class= "col-lg-9 col-md-9 ">
		<table width="100%">
					
			<tr>
				<td>
					<label style="font-weight: bold;" id="docname"><font><s:property value="patientName"/></font></label><br>
					<label id="docgender"> <font color="gray"><s:property value="gender"/>,Age:<s:property value="age"/></font></label>
					<label id="docdob" style="margin-left: 100px;"> <font color="gray">DOB : <s:property value="dob"/>  </font></label>
				</td>
				
			</tr>
			
		</table>		
	</div>
	<hr>
</div> --%>

<div class="row">
	<div class="col-lg-4 col-md-4">
	
		<s:if test="treatmentEpisodeName!=null">
			<h4><label  style="font-weight: bold; margin-left: 20px;"><font color="gray"></font><s:property value="conditionName"/> (<s:property value="treatmentEpisodeName"/>)</label></h4>
			
		</s:if>
		<s:else>

		<h4><label  style="font-weight: bold; margin-left: 20px;"><font color="gray"></font><s:property value="conditionName"/></label></h4>
	</s:else>
	</div>
</div>


<%-- <div class="row ui-state-default">
	<div class= "col-lg-12 col-md-12 " style="background-color: white;">
		<h4><b id="consTitle">Consultation Note</b> <span id="addcons"><a href="javascript:void(0)" onclick="addConsultationNote('addconshistory')">Add</a></span> &nbsp;&nbsp;&nbsp;&nbsp;
		<button id="preview" type="button" class="btn btn-primary" data-dismiss="modal" onclick="showPopUp()" data-toggle="modal" data-target="#consSearch">Preview</button>
		
		</h4>		
		<div id="consultation" style = "height:200px; overflow-y: auto;">
			<%@ include file="/emr/pages/consultation.jsp" %>
		</div>	
	</div>
	<br><br>
</div>
 --%>
 <div class="row ui-state-default">
	<div class= "col-lg-12 col-md-12 " style="background-color: white;">
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"></div>
		<div id="reminder" style = "height:300px; overflow-y: auto; background-color: rgba(146, 129, 129, 0.14);">
			<%@ include file="/emr/pages/emrSection.jsp" %>			
		</div>	
	</div>
</div>
 
 
 <div class="row ui-state-default">
	<div class= "col-lg-12 col-md-12 " style="background-color: white;">	
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"><h4><b id="clientTitle">Client Details</b></h4></div>	
		<div id="clientdetails" style = "height:200px; overflow-y: auto; background-color: rgba(146, 129, 129, 0.14);" >
			<%@ include file="/emr/pages/clientViewEmr.jsp" %>		
		</div>	
	</div>
 </div>

<div class="row ui-state-default"> 
	<div class= "col-lg-12 col-md-12 " style="background-color: white;">
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"><h4><b id="docTitle">Document Records</b> <span id="adddoc" ><a style="color: white; "  href="javascript:void(0)" onclick="addDocument('adddocument')">Add</a></span></h4></div>
		<div id="report">
			<label id="errorReport" class="text-danger"></label>
			<button id="reportdoc" type="button" class="btn btn-primary" data-dismiss="modal" onclick="sendmail();">Send Mail</button>
			<!--  <span id="reportdoc"><a href="javascript:void(0)" onclick="addDocument('adddocument')">Send Mail</a></span>
		--></div>
		<div id="document" style = "height:200px; overflow-y: auto; background-color: rgba(146, 129, 129, 0.14);">
			<%@ include file="/emr/pages/documentationRecords.jsp" %>
		</div>	
	</div>
</div>


<%-- <div class="row ui-state-default">
	<div class= "col-lg-6 col-md-6 " style="background-color: white;">
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"><h4><b id="remTitle">Reminder</b> <span id="addrem"  ><a style="color: white;" href="javascript:void(0)" onclick="addReminder('addreminder')">Add</a></span></h4></div>
		<div id="reminder" style = "height:200px; overflow-y: auto; background-color: rgba(146, 129, 129, 0.14);">
			<%@ include file="/emr/pages/reminder.jsp" %>
			
		</div>	
	</div>
<!-- </div>

<div class="row ui-state-default"> -->
	<div class= "col-lg-6 col-md-6 " style="background-color: white;">
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"><h4><b id="appTitle">Appointment Schedule</b> <span id="addapp"><a style="color: white;" href="calNotAvailableSlot?actionType=week&selecteduserid=<%=loginInfo.getId() %>">Add</a></span></h4></div>
		<div id="appointment" style = "height:200px; overflow-y: auto; background-color: rgba(146, 129, 129, 0.14);">
			<%@ include file="/emr/pages/appointmentHistory.jsp" %>
		</div>	
	</div>
</div>

<div class="row ui-state-default">
	<div class= "col-lg-6 col-md-6 " style="background-color: white;">
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"><h4><b id="mhTitle">Medical History</b> <span id="addmh"><a style="color: white;" href="javascript:void(0)" onclick="addMrdicalHistory('addemrHisory')">Add</a></span></h4></div>
		<div id="medicalhistory" style = "height:200px; overflow-y: auto; background-color: rgba(146, 129, 129, 0.14);" >
			<%@ include file="/emr/pages/showMedicalHistory.jsp" %>
		</div>	
	</div>
<!-- </div>

<div class="row ui-state-default"> -->
	<div class= "col-lg-6 col-md-6 " style="background-color: white;">
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"><h4><b id="prescTitle">Prescription</b> <span id="addpresc"><a style="color: white;" href="javascript:void(0)" onclick="addPrescription(addprescription)">Add</a></span></h4></div>
		<div id="prescription" style = "height:200px; overflow-y: auto; background-color: rgba(146, 129, 129, 0.14);">
			<%@ include file="/emr/pages/prescription.jsp" %>
		</div>	
	</div>
</div>

<div class="row ui-state-default">
	<div class= "col-lg-6 col-md-6 " style="background-color: white;">
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"><h4><b id="socialTitle">Social History</b> <span id="addsh"><a style="color: white;" href="javascript:void(0)" onclick="addSocialHistory('addsocialHistory')">Add</a></span></h4></div>
		<div id="socialhistory" style = "height:200px; overflow-y: auto; background-color: rgba(146, 129, 129, 0.14);">
			<%@ include file="/emr/pages/socialHistory.jsp" %>
			
		</div>	
	</div>
<!-- </div>

<div class="row ui-state-default"> -->
	<div class= "col-lg-6 col-md-6 " style="background-color: white;">
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"><h4><b id="allergyTitle">Allergy</b> <span id="addall"><a style="color: white;" href="javascript:void(0)" onclick="addAllergy(addallergy)">Add</a></span></h4></div>
		<div id="allergy" style = "height:200px; overflow-y: auto; background-color: rgba(146, 129, 129, 0.14);">
			<%@ include file="/emr/pages/allergy.jsp" %>
		</div>	
	</div>
</div> --%>

<div class="row ui-state-default">
	<div class= "col-lg-12 col-md-12 " style="background-color: white;">
		<div style="color: white; background-color: rgba(89, 11, 19, 0.66); height: 25px;" align="center"><h4><b id="consTitle">Consultation Note</b> <span id="addcons"><a style="color: white;" href="javascript:void(0)" onclick="addConsultationNote('addconshistory')">Add</a>| <a style="color: white;" href="">Print</a></span></h4></div>
		<!-- <button id="preview" type="button" class="btn btn-primary" data-dismiss="modal" onclick="showPopUp()" data-toggle="modal" data-target="#consSearch">Preview</button>
		 -->
			
		<div id="consultation" style = "height:200px; overflow-y: auto;">
			<%@ include file="/emr/pages/consultation.jsp" %>
		</div>	
	</div>
	<br><br>
</div>

</div>




<!-- Modal Consaltaion Search-->

<div class="modal fade" id="consSearch"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog " style="width:80%;">
    <div class="modal-content ">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Consultation Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/emr/pages/allConsultation.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>



<!-- Modal Email-->

<div class="modal fade" id="sendEmailPopUp2" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id = "thirdPartEmail" name = "email"
								cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver"
								title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "ccEmail" name = "ccEmail"
								cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "subject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject"
								placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="25" cols="60"
								title="Enter Body" name="emailBody"  id="emailBody" ></textarea>
						</div>
						
						<div class="form-group">
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="sendReportMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



</br></br>