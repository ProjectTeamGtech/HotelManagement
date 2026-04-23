<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.ThirdParties.web.form.*"%>
 <script type="text/javascript"
	src="thirdParties/js/addThirdPartyValidaton.js"></script> 
<div class="row" id = "tpbreadcrum">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Third Parties</a></li>
			<li><a href="/APM/ThirdParty">All Third Party List</a></li>
			<li class="active">Add New Third Party</li>
		</ol>
	</div>
</div>

<div class="col-lg-12 col-md-12">
		<div class="row" style="padding-left: 15px;padding-right: 15px;">
			<div class="col-lg-12 " style="background-color: #65c4fd">
				<label><h4><b>Add New Third Party (Insurance Co/ Groups)</b></h4></label>
			</div>
		</div>
		
		<div class="panel panel-primary">
			<div class="panel-body">

<s:form action="saveTPDataThirdParty" theme="simple">

	<div class="col-lg-12 col-md-12">
		<div class="row" style="padding-left: 15px;padding-right: 15px;">
			<div class="col-lg-12" style="background-color: #65c4fd">
				<label>TP/GP Name/Address</label>
			</div>
		</div>
	
		<div class="panel panel-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="row">
						<div class="col-lg-3 col-md-3">
							<label>TP Type (Surgery / Insurance Co /Group)</label><label><span
								class="text-danger">*</span></label>
							<s:select id="type1" name="type" list="thirdPartyTypeList"
								listKey="id" listValue="type" headerValue="Select TP Type"
								headerKey="0" cssClass="form-control showToolTip chosen"
								data-toggle="tooltip" onchange="setTypeName(this.value)" />
								<label  id = "tpTypeError" class="text-danger"></label>	
						</div>
						
						<div class="col-lg-3 col-md-3">
								<label>TP Name</label><label>
								<span class="text-danger">*</span></label>			
								
								<s:select id="typeName1" name="typeName" list="tpnameList"
								listKey="id" listValue="typeName" headerValue="Select InsuranceCo/Surgery Name"
								headerKey="0" cssClass="form-control showToolTip chosen"
								data-toggle="tooltip" onchange="disableFiled(this.value)" />
						</div>
						
						<div class="col-lg-1 col-md-1" style="margin-top: 24px;">
							<label></label>
							<span style="font-weight:bold;font-size:16px;">OR</span>
						</div>
						
						<div class="col-lg-3 col-md-3">
							<label>TP Name</label><label><span
								class="text-danger">*</span></label>
							<s:textfield id="companyName" name="companyName" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter New InsuranceCo/Surgery Name"
								title="Enter New InsuranceCo/Surgery Name" />
						</div>

					</div>
					
					<div class="row">
						<div class="col-lg-3 col-md-3"></div>
						<div class="col-lg-3 col-md-3">
							<label>Direct Phone No.</label>
							<s:textfield id="telephoneLine" name="telephoneLine"
								theme="simple" cssClass="form-control showToolTip"
								data-toggle="tooltip" placeholder="Enter Phone No."
								title="Direct Telephone No."
								onchange="checTelePhoneLineValidation(this.value)" />
								<label id = "telephoneLineError" class="text-danger"></label>
								


						</div>
						<div class="col-lg-3 col-md-3">
							<label>Email</label>
							<s:textfield id="compnyEmail" name="compnyEmail" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Email" title="Enter Email ID"
								onchange="checkComapnyEmailValidation(this.value)" />
										 			<label id = "compnyEmailError" class="text-danger"></label>
								
								
						</div>
						<div class="col-lg-3 col-md-3">
		 				<label>Email-CC</label>
		 				<s:textfield id = "emailCc" name = "emailCc" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Email ID" title="Enter Eamil-Cc"/>
		 				</div>
					</div>
					
					
					<div class="row">
					
						<div class="col-lg-3 col-md-3">
						</div>
						
						<div class="col-lg-3 col-md-3">
							<label>First Line of Address</label>
							<s:textfield id="tpaddress" name="address" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Address" title="Enter Address" />
						</div>
						
						<div class="col-lg-3 col-md-3">
							<label>Second Line of Address</label>
							<s:textfield name="secondLineAddress" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Second Line Address" title="Enter Second Line Address" />
						</div>
						
						<div class="col-lg-3 col-md-3">

							<label>Website</label>
							<s:textfield id = "web" name = "web" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Website" title="Enter Website"/>

						</div>
						
					
					
					</div>
					<br>
					<div class="row">
						<div class="col-lg-3 col-md-3"></div>
					
							<div class="col-lg-3 col-md-3">
							<label>Town/City</label>
							<s:textfield id="tptown" name="town" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Town" title="Enter Town" />
						</div>
						<div class="col-lg-3 col-md-3">

							<label>Post Code</label>
							<s:textfield id="tppostcode" name="postcode" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Post Code" title="Enter Post Code" onblur="initialCap(this);"/>
						</div>
					
						<div class="col-lg-3 col-md-3">
							<label>Fax</label>
							<s:textfield id = "fax" name = "fax" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Fax" title="Enter Fax"/>

						</div>
						
					

					</div>
					<br>
					<div class="row">
					 <div class="col-lg-3 col-md-3"></div>
					
						 <div class="col-lg-3 col-md-3">
							<label>Country</label>
							
							<s:if test="%{#countryList != 'null'}" >
				   			<s:select id="tpcountry" name="country" list="countryList" headerKey="0" headerValue="Select Country" 
				   			labelposition="left" title="Select your country from list" theme="simple" cssClass="form-control showToolTip"
							data-toggle="tooltip"  />
		   	   				</s:if>	
						</div> 
						
					
					</div>
					<br>
					<div class="row">
						<div class="col-lg-3 col-md-3"></div>
						
						 <div class="col-lg-6 col-md-6">
							<label>Note</label>
							<s:textarea placeholder="TP Note" rows="5" name = "tpAccountSettingNotes" id = "tpAccountSettingNotes" cssClass = "form-control showToolTip"></s:textarea>
						</div>
						
						 <div class="col-lg-3 col-md-3" style="margin-top: 86px;">
							
							<input type="submit" class="btn btn-primary" value="  Save  " onclick="return checkValidations()">
						</div>
						
				
					</div>
					<br>
					
					
								<div class="col-lg-12 col-md-12" id = "gpcontactdiv" style="display: none;">
									<div class="row" style="padding-left: 15px;padding-right: 15px;">
										<div class="col-lg-12" style="background-color: #65c4fd">
											<label>Contact Details</label>
										</div>
									</div>
							<div class="panel panel-primary">
							<div class="panel-body">
							<div class="row">
								<div class="col-lg-3 col-md-3">
									<label>Contact/GP Name</label><label><span
										class="text-danger">*</span></label>
									<s:textfield id="gpname" name="gpname" title="Enter GP Name"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter GP Name" />
								</div>

								<div class="col-lg-3 col-md-3">
									<label>Work Phone No.</label>
									<s:textfield id="workphno" name="workphno"
										title="Enter work ph no" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter work ph no" />
								</div>



								<div class="col-lg-3 col-md-3">
									<label>Email</label>
									<s:textfield id="gpemailid" name="gpemailid"
										title="Enter Email" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter Email" />
								</div>

								<div class="col-lg-3 col-md-3">
									<label>Fax</label>
									<s:textfield id="gpfax" name="gpfax" title="Enter Fax"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Fax" />
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-lg-6 col-md-6">

									<label>Notes</label>

									<s:textarea id="notes" name="notes" title="Enter Notes"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter notes"></s:textarea>
								</div>
								<div class="col-lg-3 col-md-3" style="margin-top: 38px;">
									<input type="submit" class="btn btn-primary" value="  Save  " onclick="return checkValidations()">
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-lg-12 col-md-12" id = "accountSetting">
		<div class="row" style="padding-left: 15px;padding-right: 15px;">
			<div class="col-lg-12" style="background-color: #65c4fd">
				<label>Account Setting</label>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-body">
				<div class="form-group">
		
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Agreed Credit Limit:</label>
				</div>
				<div class="col-lg-4 col-md-4">	
		 			<s:textfield name = "outInvoiceLimit" id = "outInvoiceLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "00.00" title="Enter Credit Limit"/>
				</div>
				<div class="col-lg-2 col-md-2">	
					<label>Credit Reminder Limit:</label>
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield name = "accountWarningLimit" id = "accountWarningLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "00.00" title="Enter Credit Reminder limit"/>
				</div>
				
			</div><br>
			<div class="row">
				<div class="col-lg-2 col-md-2">
					<label>Agreed Credit Duration:</label>	
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield name = "creditDuration" id = "creditDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in Days" title="Enter Agreed Duration In Days"/>
				</div>
				<div class="col-lg-2 col-md-2">
					<label>Credit Reminder Duration:</label>
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield name = "creditReminderDuration" id = "creditReminderDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in Days" title="Enter Credit Reminder Duration In Days"/>
				</div>
			</div><br>
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>DNA Limit:</label>
				</div>
				<div class="col-lg-4 col-md-4">	
		 			<s:textfield name = "dnaLimit" id = "dnaLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "00%" title="Enter DNA Limit"/>
		 		</div>
		 	</div>
		</div>
					
					
					<br>
					
					<div class="col-lg-12 col-md-12">
					<div class="row" style="padding-left: 15px;padding-right: 15px;">
						<div class="col-lg-12" style="background-color: orange">
							<label>Appointment Charges</label>
						</div>
					</div>
					<div class="panel panel-primary">
					<div class="panel-body">
					<div class="row" style="background-color: rgb(226, 222, 222);margin-top: -16px; ">
						<div class="col-lg-2 col-md-2" style="border-right: solid 1px #948E8E">
							<label>Default Appointment</label><br>
							<label>Name</label>
						</div>
						<div class="col-lg-2 col-md-2" style="border-right: solid 1px #948E8E">
							<label>Appointment</label><br>
							<label>Name</label>
						</div>
						<div class="col-lg-2 col-md-2" style="border-right: solid 1px #948E8E">
							<label>Offset Treatment</label><br>
							<label>Session for DNA</label>
						</div>
						<div class="col-lg-5 col-md-5" style="border-right: solid 1px #948E8E">
							<label>Appointment Charges For</label>
							<div class="row">
								<div class="col-lg-6 col-md-6" style="border-right: solid 1px #948E8E">
									<label>DNA</label>
								</div>
								<div class="col-lg-6 col-md-6">
									<label>Completed</label>
								</div>
							</div>
						</div>
					
						<div class="col-lg-1 col-md-1">
							<label>Duration</label>
						</div>
					</div>
					
					<br>
					
						
							<div class="row">
									<div class="col-lg-2 col-md-2">
										<label>Initial Appointment</label>
									</div>
									<div class="col-lg-2 col-md-2">
		 								<s:textfield name = "dnaInitialApmt" id = "dnaInitialApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in " title="Enter Initail Apmt charges"/>
									</div>
									
									<div class="col-lg-2 col-md-2">
										<s:checkbox name="initialOffsetdna" id="initialOffsetdna"/>
									</div>
									
									<div class="col-lg-5 col-md-5">
										<div class="row">
											<div class="col-lg-6 col-md-6">
		 										<s:textfield maxlength="15" name = "compltInitialApmtName" id = "compltInitialApmtName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value " title="Enter Initail Apmt charges"/>
											</div>
											<div class="col-lg-6 col-md-6">
		 										<s:textfield name = "compltInitialApmt" id = "compltInitialApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value" title="Enter Initail Apmt charges"/>
											</div>
										</div>
									</div>
									
									<div class="col-lg-1 col-md-1">
		 									<s:if test="%{#apmtDurationList != 'null'}">
											<s:select id="compltInitialApmtDuration" name="compltInitialApmtDuration" list="apmtDurationList"
												headerKey="0" headerValue="00:00" theme="simple"
												cssClass="showToolTip form-control" data-toggle="tooltip"
												title="Select Duration" />
										</s:if>
									</div>
									
							</div><br>
							
							
							
							<div class="row">
									<div class="col-lg-2 col-md-2">
										<label>Follow-up Appointment</label>
									
									</div>
									<div class="col-lg-2 col-md-2">
		 								<s:textfield name = "dnafollowupApmt" id = "dnafollowupApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in " title="Enter FollowUP Apmt charges"/>
									</div>
								
									<div class="col-lg-2 col-md-2">
										<s:checkbox name="compltOffsetdna" id="compltOffsetdna"/>
									</div>
									
									
									<div class="col-lg-5 col-md-5">
										<div class="row">
											<div class="col-lg-6 col-md-6">
			 									<s:textfield maxlength="15" name = "compltfollowupApmtName" id = "compltfollowupApmtName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value" title="Enter FollowUP Apmt charges"/>
											</div>
											<div class="col-lg-6 col-md-6">
				 								<s:textfield name = "compltfollowupApmt" id = "compltfollowupApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value" title="Enter FollowUP Apmt charges"/>
											</div>
										</div>
									</div>
									
									<div class="col-lg-1 col-md-1">
		 									<s:if test="%{#apmtDurationList != 'null'}">
											<s:select id="compltfollowupApmtDuration" name="compltfollowupApmtDuration" list="apmtDurationList"
												headerKey="0" headerValue="00:00" theme="simple"
												cssClass="showToolTip form-control" data-toggle="tooltip"
												title="Select Duration" />
										</s:if>
									</div>
									
							</div><br>
							
							
							<div class="row">
									<div class="col-lg-2 col-md-2">
										<label>Final Appointment</label>
									
									</div>
									<div class="col-lg-2 col-md-2">
		 								<s:textfield name = "dnafinalApmt" id = "dnafinalApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Final Apmt charges"/>
									</div>
									
									<div class="col-lg-2 col-md-2">
										<s:checkbox name="finalOffsetdna" id="finalOffsetdna"/>
									</div>
									
									
									<div class="col-lg-5 col-md-5">
										<div class="row">
											<div class="col-lg-6 col-md-6">
				 								<s:textfield maxlength="15" name = "compltfinalApmtName" id = "compltfinalApmtName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value" title="Enter Final Apmt charges"/>
											</div>
											<div class="col-lg-6 col-md-6">
				 								<s:textfield name = "compltfinalApmt" id = "compltfinalApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value" title="Enter Final Apmt charges"/>
											</div>
										</div>
									</div>
									
									<div class="col-lg-1 col-md-1">
		 								<s:if test="%{#apmtDurationList != 'null'}">
											<s:select id="compltfinalApmtDuration" name="compltfinalApmtDuration" list="apmtDurationList"
												headerKey="0" headerValue="00:00" theme="simple"
												cssClass="showToolTip form-control" data-toggle="tooltip"
												title="Select Duration" />
										</s:if>
										
									</div>
									
							</div><br>
							<%-- <div class="row">
									<div class="col-lg-2 col-md-2">
										<label>Maintenance</label>
									
									</div>
									<div class="col-lg-1 col-md-1">
		 								<s:textfield name = "dnamaintenanceApmt" id = "dnamaintenanceApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Maintence charges"/>
									</div>
									<!-- <div class="col-lg-2 col-md-2"></div> -->
									
									<div class="col-lg-2 col-md-2">
										<label>Maintenance Name</label>
									
									</div>
									<div class="col-lg-1 col-md-1">
		 								<s:textfield maxlength="15" name = "compltmaintenanceApmtName" id = "compltmaintenanceApmtName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value" title="Enter Maintence charges"/>
									</div>
									
									<div class="col-lg-2 col-md-2">
										<label>Maintenance Charge</label>
									
									</div>
									<div class="col-lg-1 col-md-1">
		 								<s:textfield name = "compltmaintenanceApmt" id = "compltmaintenanceApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in £" title="Enter Maintence charges"/>
									</div>
									<div class="col-lg-2 col-md-2">
										<label>Maintenance Duration</label>
									
									</div>
									<div class="col-lg-1 col-md-1">
		 								<s:if test="%{#apmtDurationList != 'null'}">
											<s:select id="compltmaintenanceApmtDuration" name="compltmaintenanceApmtDuration" list="apmtDurationList"
												headerKey="0" headerValue="00:00" theme="simple"
												cssClass="showToolTip form-control" data-toggle="tooltip"
												title="Select Duration" />
										</s:if>
										
									</div>
									
							</div> --%>
							
							<a onclick="addRow('dynamicallyAddApmtTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
							<a onclick="deleteRow('dynamicallyAddApmtTable')" class="btn btn-primary" id = "deletefiled"><i class="fa fa-trash-o"></i> Delete Row</a>
							<table class="table" id = "dynamicallyAddApmtTable" style="display: none">
								<thead id = "showHeaderBlock">
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>
										<th align="center">DNA Appointment Name</th>	
										<th align="center">DNA Appointment Charge</th>	
										<th align="center">Offset Treatment Sesion For DNA</th>	
										<th align="center">Appointment Name</th>	
										<th align="center">Appointment Charge</th>	
										<th align="center">Appointment Duration</th>	
										
									</tr>
								</thead>
								
							
							<tbody>
						<s:hidden name = "danyamiclistpresent" id = "danyamiclistpresent"></s:hidden>
						<s:if test="typeName > 0">
							
								<%ArrayList<DynamicAppointment>dynamicApmtTypeList = (ArrayList<DynamicAppointment>)session.getAttribute("dynamicApmtTypeList"); 
								int i = 0;
								int count = 1;
							%>
								<% for(DynamicAppointment dynamicAppointment:dynamicApmtTypeList ) {%>
									<tr>
										<td>
										<input type="checkbox" name="chk">
										</td>
            					 		<td> <%=count%> </td>
	
										
										
										<td><input type="text" id = "dynamicApmt[<%=i%>].dnaName" name="dynamicApmt[<%=i%>].dnaName" value="<%=dynamicAppointment.getDnaName() %>" class="form-control showToolTip dnaName"></td>
										<td><input type="text" id = "dynamicApmt[<%=i%>].dnaCharge" name="dynamicApmt[<%=i%>].dnaCharge" value="<%=dynamicAppointment.getDnaCharge() %>" class="form-control showToolTip dnaCharge"></td>
										<td><input type="checkbox" name="dynamicApmt[<%=i%>].offset" ></td>
										<td><input type="text" name="dynamicApmt[<%=i%>].apmtName" value="<%=dynamicAppointment.getApmtName() %>" class="form-control showToolTip apmtName"></td>
										<td><input type="text" name="dynamicApmt[<%=i%>].apmtCharge" value="<%=dynamicAppointment.getApmtCharge() %>" class="form-control showToolTip apmtCharge"></td>
										
										<td>
										<select name="dynamicApmt[<%=i%>].apmtDuaration" value="<%=dynamicAppointment.getApmtDuaration()%>" class="form-control showToolTip apmtDuaration">
											<option value="<%=dynamicAppointment.getApmtDuaration()%>"><%=dynamicAppointment.getApmtDuaration()%></option>
											
											<option value="00:15">00:15</option>
											<option value="00:30">00:30</option>
											<option value="00:45">00:45</option>
											<option value="01:00">01:00</option>
											<option value="01:15">01:15</option>
											<option value="01:30">01:30</option>
										</select>
										</td>
										<td>
										<input type="hidden" id = "dynamicApmt[<%=i%>].id" name="dynamicApmt[<%=i%>].id" value="<%=dynamicAppointment.getId()%>">
										</td>		
										
									
									</tr>
								<% i++;%>
								<% count++;%>
										<%} %>
							</s:if>	
							</tbody>
							</table>
						
						
						</div>	
						</div>
					</div>
					
					
					<div>
				<%-- 	<div class="col-lg-5 col-md-5">
					<label>TP Notes:</label>
					<s:textarea rows="5" name = "tpAccountSettingNotes" id = "tpAccountSettingNotes" cssClass = "form-control showToolTip"></s:textarea>
					</div>
					</div> --%>
				</div>
				
			</div>
					
			
		</div>
		
			<div class="row" >
				<div class="col-lg-12 col-md-12">
					<span style="color:red">Note: Appointment name allowed only 15 character name</span>
			
				</div>
				</div><br>
		
		<div class="row" id = "savetpbtn">
				<div class="col-lg-2 col-md-2">
			<input type="submit" class="btn btn-primary" value="Save" onclick="return checkValidations()">
			
				</div>
				</div><br><br>
<s:token />			
</s:form>
</div>
</div>



