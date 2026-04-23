<%@page import="com.apm.Appointment.eu.entity.AppointmentType"%>
<%@page import="com.apm.DiaryManagement.eu.entity.DiaryManagement"%>
<%@page import="com.apm.Accounts.eu.entity.Accounts"%>
<%@page import="com.apm.Master.eu.entity.TreatmentType"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Notification</title>
    <link href="notification/Content/bootstrap-3.3.1-dist/dist/css/bootstrap.min.css" rel="stylesheet" />
  
    <link href="notification/Content/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" />
    <link href="notification/css/Notification.css" rel="stylesheet" />
    <link href="notification/css/bootstrap-select.css" rel="stylesheet" />
    

   
    
   <style>
       .bootstrap-select:not([class*="col-"]):not([class*="form-control"]):not(.input-group-btn) {
           width: 220px;
           float: right;
       }
   </style> 
    
  <script>
  
  
  var selectedval = 0;
 function show(){
	//Do it simple

	 var data="1,2,3,4";

	 //Make an array

	 var dataarray=data.split(",");

	 // Set the value

	 $("#reminder1").val(dataarray);

	 // Then refresh

	 $("#reminder1").multiselect("refresh");
 }
  	function getvalue(v){
  	  var selected = $("#reminder1 option:selected");
      var message = "";
      selected.each(function () {
          message += $(this).text() + " " + $(this).val() + "\n";
      });
    //  alert(message);
  	}
  </script>
  
</head>

<ol class="breadcrumb">
	<li><a href="#">Manage Notification</a></li>
	
</ol>

<body>
<!-- <input type="button" onclick="show()"> -->
    <div id="wrapper">
        <!-- Navigation -->

<s:form action="" theme="simple">
        <div class="container-fluid">
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-8">
                        <div class="panel panel-default">
                                <table class="table table-responsive">
                                    <thead class="tableheadback">
                                        <tr>
                                            <th class="thwid">TASK <small>(Send SMS or Email)</small></th>
                                            <th class="text-center">USER</th>
                                            <th class="text-center">PRACTITIONER</th>
                                            <th class="text-center">ADMIN</th>
                                            <th class="text-center">EMAIL</th>
                                            <th class="text-center">SMS</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            
                                            <td class="backleftcor"><b>Appointment Confirmation To</b></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtConfirmationUser" name="apmtConfirmationUser"  /></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtConfirmationPractitioner" name="apmtConfirmationPractitioner" /></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtConfirmationAdmin" name="apmtConfirmationAdmin"/></td>
                                             <td class="text-center"><s:checkbox id="apmtConfirmationEmail" name="apmtConfirmationEmail"/></td>
                                            <td class="text-center"><s:checkbox id="apmtConfirmationSms" name="apmtConfirmationSms"/></td>
                                        </tr>
                                        <tr>
                                            <td class="backleftcor"><b>Appointment Confirmation To</b></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtCancelUser" name="apmtCancelUser"  /></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtCancelPractitioner" name="apmtCancelPractitioner" /></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtCancelAdmin" name="apmtCancelAdmin"/></td>
                                             <td class="text-center"><s:checkbox id="apmtCancelEmail" name="apmtCancelEmail"/></td>
                                            <td class="text-center"><s:checkbox id="apmtCancelSms" name="apmtCancelSms"/></td>
                                            
                                        </tr>
                                        <tr>
                                            <td class="backleftcor">
                                                <b>Appointment Reminder 1 </b>
                                                <select onchange="getvalue(this.value)" id="reminder1" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                  
                                                   
                                                </select>
                                            </td>
                                             <td class="text-center clibac"><s:checkbox id="apmtReminder1User" name="apmtReminder1User"  /></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtReminder1Practitioner" name="apmtReminder1Practitioner" /></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtReminder1Admin" name="apmtReminder1Admin"/></td>
                                             <td class="text-center"><s:checkbox id="apmtReminder1Email" name="apmtReminder1Email"/></td>
                                            <td class="text-center"><s:checkbox id="apmtReminder1Sms" name="apmtReminder1Sms"/></td>
                                            
                                        </tr>
                                        <tr>
                                            <td class="backleftcor">
                                                <b>Appointment Reminder 2 </b>
                                                <select id="reminder2" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                   
                                                </select>
                                            </td>
                                              <td class="text-center clibac"><s:checkbox id="apmtReminder2User" name="apmtReminder2User"  /></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtReminder2Practitioner" name="apmtReminder2Practitioner" /></td>
                                            <td class="text-center clibac"><s:checkbox id="apmtReminder2Admin" name="apmtReminder2Admin"/></td>
                                             <td class="text-center"><s:checkbox id="apmtReminder2Email" name="apmtReminder2Email"/></td>
                                            <td class="text-center"><s:checkbox id="apmtReminder2Sms" name="apmtReminder2Sms"/></td>
                                            
                                        </tr>
                                        <tr>
                                            <td class="backleftcor"><b>Feedback For </b></td>
                                             <td class="text-center clibac"><s:checkbox id="feedbackUser" name="feedbackUser"  /></td>
                                            <td class="text-center clibac"><s:checkbox id="feedbackPractitioner" name="feedbackPractitioner" /></td>
                                            <td class="text-center clibac"><s:checkbox id="feedbackAdmin" name="feedbackAdmin"/></td>
                                             <td class="text-center"><s:checkbox id="feedbackEmail" name="feedbackEmail"/></td>
                                            <td class="text-center"><s:checkbox id="feedbackSms" name="feedbackSms"/></td>

                                        </tr>
                                        <tr>
                                            <td class="backleftcor">Location
                                                <% if(session.getAttribute("notilocationlist")!=null){%>
                                                	<% ArrayList<Accounts> list = (ArrayList<Accounts>)session.getAttribute("notilocationlist");%>	
                                                	 <select id="location" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                                                		<%for(Accounts acc : list){ %>
                                                  			<option value="<%=acc.getId() %>"><%=acc.getLocation() %></option>
                                                  		<% }%>
                                              		  </select>
                                                	
                                                <% }%>
                                            </td>
                                             <td class="text-center clibac">-</td>
                                            <td class="text-center clibac">-</td>
                                            <td class="text-center clibac">-</td>
                                            <td class="text-center">-</td>
                                            <td class="text-center">-</td>

                                        </tr>
                                        <tr>
                                            <td class="backleftcor">
                                                Practitioner
                                                 <% if(session.getAttribute("notiuserlist")!=null){%>
                                                	<% ArrayList<DiaryManagement> list = (ArrayList<DiaryManagement>)session.getAttribute("notiuserlist");%>	
                                                	 <select id="practi" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                                                		<%for(DiaryManagement dm : list){ %>
                                                  			<option value="<%=dm.getId() %>"><%=dm.getDiaryUser() %></option>
                                                  		<% }%>
                                              		  </select>
                                                	
                                                <% }%>
                                            </td>
                                             <td class="text-center clibac">-</td>
                                            <td class="text-center clibac">-</td>
                                            <td class="text-center clibac">-</td>
                                            <td class="text-center">-</td>
                                            <td class="text-center">-</td>

                                        </tr>
                                        <tr>
                                            <td class="backleftcor">
                                                Appointment Type
                                                <% if(session.getAttribute("notiapmtlist")!=null){%>
                                                	<% ArrayList<AppointmentType> list = (ArrayList<AppointmentType>)session.getAttribute("notiapmtlist");%>	
                                                	 <select id="aponttype" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                                                		<%for(AppointmentType appointmentType : list){ %>
                                                  			<option value="<%=appointmentType.getId() %>"><%=appointmentType.getName() %></option>
                                                  		<% }%>
                                              		  </select>
                                                	
                                                <% }%>
                                            </td>
                                             <td class="text-center clibac">-</td>
                                            <td class="text-center clibac">-</td>
                                            <td class="text-center clibac">-</td>
                                            <td class="text-center">-</td>
                                            <td class="text-center">-</td>

                                        </tr>
                                        <tr>
                                            <td class="backleftcor">
                                                Condition
                                                
                                                <% if(session.getAttribute("noticonditionlist")!=null){%>
                                                	<% ArrayList<TreatmentType> clist = (ArrayList<TreatmentType>)session.getAttribute("noticonditionlist");%>	
                                                	 <select id="condition" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                                                		<%for(TreatmentType treatmentType : clist){ %>
                                                  			<option value="<%=treatmentType.getId() %>"><%=treatmentType.getTreatmentName() %></option>
                                                  		<% }%>
                                              		  </select>
                                                	
                                                <% }%>
                                               
                                            </td>
                                         	<td class="text-center clibac">-</td>
                                            <td class="text-center clibac">-</td>
                                            <td class="text-center clibac">-</td>
                                            <td class="text-center">-</td>
                                            <td class="text-center">-</td>
                                        </tr>
                                       
                                      
                                        <tr>
                                            <td class="backleftcor">
                                                <b>On Discharge</b>
                                            </td>
                                            <td class="text-center clibac"><s:checkbox id="dischargeUser" name="dischargeUser"  /></td>
                                            <td class="text-center clibac"><s:checkbox id="dischargePractitioner" name="dischargePractitioner" /></td>
                                            <td class="text-center clibac"><s:checkbox id="dischargeAdmin" name="dischargeAdmin"/></td>
                                             <td class="text-center"><s:checkbox id="dischargeEmail" name="dischargeEmail"/></td>
                                            <td class="text-center"><s:checkbox id="dischargeSms" name="dischargeSms"/></td>


                                        </tr>
                                        <tr>
                                            <td class="backleftcor">
                                                <b>On Report Status</b>
                                            </td>
                                           <td class="text-center clibac"><s:checkbox id="reportUser" name="reportUser"  /></td>
                                            <td class="text-center clibac"><s:checkbox id="reportPractitioner" name="reportPractitioner" /></td>
                                            <td class="text-center clibac"><s:checkbox id="reportAdmin" name="reportAdmin"/></td>
                                             <td class="text-center"><s:checkbox id="reportEmail" name="reportEmail"/></td>
                                            <td class="text-center"><s:checkbox id="reportSms" name="reportSms"/></td>


                                        </tr>
                                    </tbody>
                                </table>
                                
                          
                        </div>
                        <button class="btn btn-primary savebtn">Save</button>
                    </div>
                </div>
                <!-- /.row -->
              
            </div>
            
            </s:form>
        <!-- /.container-fluid -->
    </div>  
    
    
   
    <script src="notification/js/bootstrap-select.js"></script>  

    <script>
        $(document).ready(function () {
            var mySelect = $('#reminder1');
            var mySelect = $('#reminder2');
            var mySelect = $('#location');
            var mySelect = $('#practi');
            var mySelect = $('#aponttype');
            var mySelect = $('#condition');
        });

    </script>
   
</body>
</html>
