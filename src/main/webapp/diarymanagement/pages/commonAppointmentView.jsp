<%@ taglib uri="/struts-tags"  prefix="s"%>
<script type="text/javascript" src="diarymanagement/js/completeApmt.js"></script>
<%-- <script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script> --%>

<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>
<link href="diarymanagement/css/tabStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>
<script type="text/javascript" src="diarymanagement/js/commonAppointmentView.js"></script>


<div class="form_elements" >	
		
		
		<div id="dashboardDiv" style="background-color: white; left: 100px">
		<input type="hidden" name="choice"	id="choice" value="1" />
			
			
			<table width="100%">
				<col width="50%"/>
				 <col width="50%"/>
				
				<tr>
					<td valign="top">
							<div id="closediv" class="close" style="display: none;"></div>
							<span class="ecs_tooltip">Press Esc to close <span class="arrow" ></span></span>
						<div id = "appointments" style="display: none;">
										<div class="mddiv" id="apmtdiv"><font color="white" >New Appointment</font></div>
				                        <table width="100%" style="font-size: 12px;">
				                        <s:form action="saveAppoinmentNotAvailableSlot" id="notavailable_form" theme="simple" validate="True">
										
										<col width="12%" />
										<col width="2%" />
										<col width="20%" />
										
										
										<input type="hidden" name="slotId" id="slotId"/>
           								<input type="hidden" name="diaryUserId" id="diaryUserId"/>
										<tr>
											<td id="apmtheading" colspan="3" style="font-weight: bold; font-size: 12px;"><u></u>
											</td>
										</tr>
										
										<tr>
											<td colspan="3"></td>
										</tr>
										<tr>
											<td>Appointment with</td>
											<td align="center">:</td>
											<td><s:textfield id="user" name="user" readonly="true"/>
											</td>
											
										</tr>
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										<tr>
											<td>Location</td>
											<td align="center">:</td>
											<td><s:select id="dept" name="dept" list="{'Any Location','Physiotherapy','Podiatry','Osteopathy','Classes'}" theme="simple" />
											</td>
										</tr>
										<tr>
											<td class="hidden">Location</td>
											<td align="center">:</td>
											<td><s:if test="%{#locationList != 'null'}">
												<s:select id="location" name="location" list="locationList" listKey="location" headerValue="Select Location"
												disabled="true"	listValue="location" theme="simple" />
											</s:if></td>
										</tr>
										<tr>
											<td>Room</td>
											<td align="center">:</td>
											<td><select name="room" id="room">
												<option value="Room1">Room1</option>
												<option value="Room2">Room2</option>
											</select></td>
										</tr>
										
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										<tr>
											
										<tr>
										<td>Date</td>
										<td align="center">:</td>
										<td><s:textfield name="date" id = "date" readonly="true"></s:textfield></td>
										</tr>
										
										<tr>
										<td>Start Time </td>
										<td align="center">:</td>
										<td>
										<s:if test="%{#startTimeList != 'null'}">
												<s:select id="sTime" name="sTime" list="startTimeList" theme="simple" onchange="resetAppointmentField();" />
											</s:if>
										</td>
										</tr>
										<tr>
										<td>End Time</td>
										<td align="center">:</td>
										<td> 
										<s:if test="%{#endTimeList != 'null'}">
										<s:select id="endTime" name="endTime" list="endTimeList" theme="simple" disabled="true"/>
											</s:if>
										</td>
										</tr>
										<tr>
											<td>Appointment Duration</td>
											<td align="center">:</td>
											<td><s:if test="%{#apmtDurationList != 'null'}">
												<s:select id="apmtDuration" name="apmtDuration" list="apmtDurationList"  headerKey="0" headerValue="00:00"	 theme="simple" disabled="true" />
											</s:if>
											</td>
										</tr>
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										<tr>
											<td>Client</td>
											<td align="center">:</td>
											<s:hidden name="clientId" id = "clientId" ></s:hidden>  
											<td><s:textfield name="client" id = "client" readonly="true" size="18" onchange="setTreatmentEpisode()"></s:textfield> 
											 <input type="button" value = "Search" class = "buttons" onclick="showAnotherPopup();">
											
												<!--  <a href="Client" style="text-decoration: none" target="blank" onclick="window.open(this.href, 'mywin',
											'left=20,top=20,width=800,height=500,toolbar=1,resizable=0'); return false;"> <input type="button" value = "Search" class = "buttons"></a> -->
											</td>
										</tr>
										
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										
										<tr>
											<td>Appointment Type</td>
											<td align="center">:</td>
											<td><s:select name = "duration" id = "apmtType" list="appointmentTypeList" listKey="duration" listValue="name" headerKey="0" headerValue="Select Appointment Type" onchange="setAppointmentTypeTime(this.value)" style="width:157px"></s:select> </td>
											
										</tr>
										
										<tr>
											<td>Treatment Episode</td>
											<td align="center">:</td>
											<td id="treatmentepisodeajax">
												<select name="treatmentEpisode" id="treatmentEpisode" style="width:157px">
													<option value="0">Select Treatment Episode</option>
												</select>
												<!-- <a href="inputTreatmentEpisode" style="text-decoration: none" target="blank" onclick="window.open(this.href, 'mywin',
												'left=20,top=20,width=800,height=500,toolbar=1,resizable=0'); return false;"> <input type="button" value = "Add" class = "buttons" disabled="disabled"></a> -->
												<input type="button" value = "Add" onclick = "addTreatmentEpisode()" class="btn btn-primary"> 
											</td>
											
										</tr>
										
										<tr>
										<td>Notes</td>
										<td align="center">:</td>
										<td><s:textarea name = "notes" id = "notes" rows="3" cols="18" ></s:textarea></td>
										</tr>
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										
										
										<!-- <tr>
											<td colspan="3" align="center" id="submitslot"><input
												type="button" value="Submit" onclick="$(this).saveSlot('0');" /> <input
												type="button" value="Cancel" onclick="$(this).cancelSlot();" /></td>
										</tr> -->
								</s:form>
							</table>
</div>			


				
<div id = "blockdivs" style="display: none;">
				<div class="mddiv" id="blockapmtdiv"><font color="white" >Non Available Appointment Slot</font></div>
				
				<table width="100%" style="font-size: 12px;">
				                        <s:form action="saveNotAvailableSlot" id="notavailable_form" theme="simple" validate="True">
										
										
										<input type="hidden" name="blockslotId" id="blockslotId"/>
           								<input type="hidden" name="blockdiaryUserId" id="blockdiaryUserId"/>
           								<input type="hidden" name="status" id="status" value = "1"/>
										<col width="35%" />
										<col width="2%" />
										<col width="30%" />
										<tr>
											<td id="apmtheading" colspan="3" style="font-weight: bold; font-size: 12px;"><u></u>
											</td>
										</tr>
										
										<tr>
											<td colspan="3"></td>
										</tr>
										<tr>
											<td>Diary User</td>
											<td align="center">:</td>
											<td><s:textfield id="blockuser" name="blockuser" readonly="true"/></td>

										</tr>
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										
										<tr>
											<td>Location:</td>
											<td align="center">:</td>
											<td><s:if test="%{#locationList != 'null'}">
											<s:select id="blocklocation" name="blocklocation" list="locationList" listKey="location" listValue="location" disabled="true" theme="simple" />
											</s:if>
											</td>
										</tr>
										<tr>
											<td>Room</td>
											<td align="center">:</td>
											<td><select name="blockroom" id="blockroom">
												<option value="Room1">Room1</option>
												<option value="Room2">Room2</option>
											</select></td>
										</tr>
										
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										<tr>
											<td>Date</td>
											<td align="center">:</td>
											<td><s:textfield name="blockdate" id = "blockdate" readonly="true"></s:textfield></td>
										</tr>
										<tr>
										<td>Start Time</td>
										<td align="center">:</td>
										<td><s:if test="%{#startTimeList != 'null'}">
												<s:select id="blocksTime" name="blocksTime" list="startTimeList" onchange="resetBlockDivField();" theme="simple" />
											</s:if>
										</td>
										</tr>
										<tr>
										<td>End Time</td>
										<td align="center">:</td> 
										 <td><s:if test="%{#endTimeList != 'null'}">
												<s:select id="blockendTime" name="blockendTime" list="endTimeList" disabled="true" theme="simple" />
											</s:if>
											</td>
										</tr>
										<tr>
											<td>Appointment Duration</td> 
											<td align="center">:</td> 
											<td><s:if test="%{#apmtBlockDurationList != 'null'}">
												<s:select id="blockapmtDuration" name="blockapmtDuration" onchange="setBlockDivEndTime(this.value)"
												 headerKey="0" headerValue="00:00"	list="apmtBlockDurationList" theme="simple" />
											</s:if>
											</td>
										</tr>
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										<tr>
											<td>Reason for non availability</td>
											<td align="center">:</td> 
											<td><s:select name = "reasonforblock" id = "reasonforblock" list="{'Admin','Break','Lunch','Cancelation','Practice Meeting','Teaching','Teaching Session','Teaching Cources','Available'}" theme="simple"></s:select> </td>
										</tr>
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										
										<tr>
										<td>Notes</td>
										<td align="center">:</td> 
										<td><s:textarea name = "blocknotes" id = "blocknotes"></s:textarea></td>
										</tr>
										
										
										
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										<tr>
											<td colspan="3" align="center" id="submitblockslot"><input
												type="button" value="Submit" onclick="$(this).blockSave();" /> <input
												type="button" value="Cancel" onclick="$(this).cancelSlot();" /></td>
										</tr>
										</s:form>
									</table>
							</div>
			
		
<!-- client did not attend popup  -->
			
			<div id="clientdidnotattentpopup" style="display: none; font-size: 11px;">
				<div class="mddiv"><font color="black">Client Did Not Attend</font></div>
				<div>
					Missed Appointment With <input type="text" id="missedappointmentwith" name="missedappointmentwith" size="30" disabled="disabled" style="text-align: center;"/>
				</div>
				<div><hr/></div>
				<div>Date <input type="text" id="didnotattendDate" name="didnotattendDate" readonly="readonly" disabled="disabled"></div>
				<div>Time <input type="text" name="didnotattentTime" id="didnotattentTime" disabled="disabled" size="5">
					Duration <input type="text" name="didnotattentDuration" id="didnotattentDuration" disabled="disabled" size="5">
				</div>
				<div><hr/></div>
				<div>
					Notes <textarea rows="3" cols="33" name="didnotattendNotes" id="didnotattendNotes"></textarea>&nbsp;&nbsp;&nbsp;
					<font style="font-size: 10px;"> 5 charges raised against this appointment</font>
				</div>
				<div><hr/></div>
				<div>
					Please Confirm <input type="checkbox" name="dnachk" id="dnachk"> D.N.A.
				</div>
				<div>
					Why <select name="why" id="why">
							<option value="0">Select Reason</option>	
							<option value="CANCELLED">CANCELLED</option>
							<option value="DNA">DNA</option>
							<option value="LATE CANCELATION">LATE CANCELATION</option>
							<option value="NO REASON">NO REASON</option>
							<option value="UNWELL">UNWELL</option>
						</select>
				</div>
				<!--<div>
					 <input type="checkbox" name="reopen" id="reopen"> Re Open Appointment
				</div>
				--><div><hr/></div>
				
				<div>
					<input type="button" value="OK" onclick="setClientDidNotComeConfirm()">
					<input type="button" value="Cancel" onclick="$(this).cancelSlot();">
					<%-- <input type="button" value="Raise Charge" onclick="setDNACharge()">
					<s:form action="paynowCompleteApmt" theme="simple">
					<input type="submit" value="Pay Now">
					</s:form> --%>
				</div>
				
			</div>
			
			

<div id="completeAppointmentDivId" style="display: none;">
	<%@ include file="/diarymanagement/pages/completeApmt.jsp" %>
</div>

<!-- client modify popup  -->

			<div id="modifyPopup" style="display: none;">
				<div class="mddiv" id = "modifyHeading"><font color="white">Client Appointment</font></div>
				<div class="mddiv" id = "modifyClient" style="font-size: 16px;"><font color="white">Manoj Mishra</font></div>
					<table width="100%">
						<tr>
							<!--<td><a href="inputCompleteApmt?selectedUser=<s:property value="user"/>" target="blank" onclick="window.open(this.href, 'mywin',
											'left=20,top=20,width=600,height=400,toolbar=1,resizable=0'); return false;"><input type="button" class="mdbtn" value="  Complete this Appointment" > </a></td>-->
							<td>
							<input type="button" id = "completeapmt" class="mdbtn" value="  Complete this Appointment" onclick="completeApmt();" ></td>
							</tr>
						<tr>
							<td><input type="button" id = "modify" class="mdbtn" value="  Modify this Appointment  " onclick="modifythisAppointment()"> </td>
						</tr>
						<tr id="clienthasarrived">
							<td><input type="button" id = "clientarrived" class="mdbtn" value="  Client has Arrived  " onclick="clienthasarrived(1)"> </td>
						</tr>
						<tr id="clientisbeingseen">
							<td><input type="button" id = "clientseen" class="mdbtn" value="  Client is Being Seen  " onclick="setClientIsBeingSeen(2)"> </td>
						</tr>
						<tr id="clientdidnotcome">
							<td><input type="button" id = "dna" class="mdbtn" value="  Client Did Not Attend  " onclick="setClientDidNotCome()"> </td>
						</tr>
						<tr>
							<td><input type="button" id = "reminder"  class="mdbtn" value="  Confirmation & Reminders " onclick = "reminder()"> </td>
						</tr>
						<tr id="resetnotarrived" style="display: none;">
							<td><input type="button" class="mdbtn" value="  Reset to Not Arrived  " onclick="ResetToNotArrived(0)"> </td>
						</tr>
					</table>
					<div class="mddiv"></div>
					
			</div>
			
			<div id="reminderPopup" style="display: none;">
				<div class="reminderdiv" id = "remiderHeading"><font color="white">Client Appointment</font></div>
				<div class="reminderdiv" id = "remiderClient" style="font-size: 16px;"><font color="white">Manoj Mishra</font></div>
				<div class="reminderdiv" id = "remiderdate" style="font-size: 14px;"><font color="brown"> Monday 03/03/2014 10:00</font></div>
					<table width="100%">
						<tr>
						<td><s:hidden name="practitionersName" id = "practitionersName"/></td>
						<td><s:hidden name="practitionersId" id = "practitionersId"/></td>
						<td><s:hidden name="clientName" id = "clientName"></s:hidden></td>
						<td><s:hidden name = "patientId" id = "patientId" /></td>
						<td><s:hidden name ="aptmStartTime" id = "aptmStartTime"/></td>
						<td><s:hidden name ="aptmDuration" id = "aptmDuration"/></td>
						<td><s:hidden name ="practitionersEmail" id = "practitionersEmail"></s:hidden>
						<td><s:hidden name ="clientEmail" id = "clientEmail"></s:hidden>
						<td><s:hidden name ="aptmlocation" id = "aptmlocation"></s:hidden>
						
						</tr>
						<tr>
							<td><input type="button" class="reminderbtn" value="Send Booking Confirmation (SMS)...Done!"> </td>
						</tr>
						<tr>
							<td><input type="button" class="reminderbtn" value="Appointment Reminder (E-mail)...Scheduled!" onclick="emailSend()">  </td>
						</tr>
						<tr>
							<td><input type="button" class="reminderbtn" value="Follow-Up Reminder (Email)...Scheduled!"> </td>
						</tr>
						
					</table>
					<div class="mddiv"></div>
					
			</div>
					
					</td>
					
					<td id="anothertd" valign="top" style="display:none; " >
						
						<div id="addPatientDiv" style="display:none;">
							<%@ include file="/diarymanagement/pages/addPatient.jsp" %>
						</div>
						<div id="clientSearchDiv" style="display:none; height: 550px;" >
							<%@ include file="/diarymanagement/pages/allClient.jsp" %>
						</div>	
						
						
					</td>
					<td id="treatmenttd" valign="top" style="display:none; ">
					<div id = "addTreatmentEpisodeDiv" style="display: none">
							<%@ include file = "/treatmentEpisode/pages/addTreatmentEpisode.jsp"%>
						</div>
					
					</td>
					
				</tr>
			</table>
			
			
			
			
			
			</div>	
		</div>	
