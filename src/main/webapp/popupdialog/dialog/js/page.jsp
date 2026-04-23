<!-- client modify popup  -->

<div id="modifyPopup" title="Client Appointment" style="display: none">
				
				<div  id = "modifyClient" style="font-size: 16px;">Manoj Mishra</div>
				<div class="row">
					<div class="col-lg-12">
						<input type="button" id = "completeapmt" class="btn btn-default width-full" value=" Complete this Appointment " onclick="completeApmt();" >
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<input type="button" id = "modify" class="btn btn-default width-full" value=" Modify this Appointment " onclick="modifythisAppointment();" >
					</div>
				</div>	
				<div class="row" id="clienthasarrived">
					<div class="col-lg-12">
						<input type="button" id = "clientarrived" class="btn btn-default width-full" value="  Client has Arrived  " onclick="clienthasarrived(1)"> 
					</div>
				</div>	
				<div class="row" id="clientisbeingseen">
					<div class="col-lg-12">
						<input type="button" id = "clientseen" class="btn btn-default width-full" value="  Client is Being Seen  " onclick="setClientIsBeingSeen(2)">
						
					</div>
				</div>					
				<div class="row" id="clientdidnotcome">
					<div class="col-lg-12">
							<input type="button" id = "dna" class="btn btn-default width-full" value="  Client Did Not Attend  " onclick="setClientDidNotCome()"> 
						
					</div>
				</div>					
				<div class="row">
					<div class="col-lg-12">
							<input type="button" id = "reminder"  class="btn btn-default width-full" value="  Confirmation & Reminders " onclick = "reminder()"> 
						
					</div>
				</div>					
				<div class="row" style="display: none;">
					<div class="col-lg-12">
							<input type="button" class="btn btn-default width-full" value="  Reset to Not Arrived  " onclick="ResetToNotArrived(0)"> 
						
					</div>
				</div>	
						
</div>


<!-- Reminder Modal -->
	<div class="modal fade" id="reminderPopup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Appointment
						Confirmation and Reminder</h4>
				</div>
				<div class="modal-body">
					<div id="remiderClient" style="font-size: 16px;">
						<font color="white">Manoj Mishra</font>
					</div>
					
							<s:hidden name="practitionersName" id="practitionersName" />
							<s:hidden name="practitionersId" id="practitionersId" />
							<s:hidden name="clientName" id="clientName"></s:hidden>
							<s:hidden name="patientId" id="patientId" />
							<s:hidden name="aptmStartTime" id="aptmStartTime" />
							<s:hidden name="aptmDuration" id="aptmDuration" />
							<s:hidden name="practitionersEmail"id="practitionersEmail"></s:hidden>
							<s:hidden name="clientEmail" id="clientEmail"></s:hidden>
							<s:hidden name="aptmlocation" id="aptmlocation"></s:hidden>
						
							<div class="row">
								<div class="col-lg-12">
									<input type="button" class="btn btn-default width-full" value="Send Booking Confirmation (SMS)...Done!">
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<input type="button" class="btn btn-default width-full" value="Send Booking Confirmation (SMS)...Done!">
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<input type="button" class="btn btn-default width-full" value="Appointment Reminder (E-mail)...Scheduled!" onclick="emailSend()">
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<input type="button" class="btn btn-default width-full" value="Follow-Up Reminder (Email)...Scheduled!">
								</div>
							</div>
						
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
