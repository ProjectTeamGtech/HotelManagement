<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="oracle.jdbc.util.Login"%>
<%@taglib uri="/struts-tags" prefix="s"%>



<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<%
	LoginInfo loginfoopd = LoginHelper.getLoginInfo(request);
String mobhide="";
%>
<%if(loginfoopd.getUserType()==5){ 
mobhide="hidden";
}%>
<%
String bghide="hidden";
if(loginfoopd.isBalgopal()){
	bghide="";
}
%>

<link href="common/css/responsive.css" rel="stylesheet" type="text/css" /> 
<style>
#BookappiontmentContiner hr{
margin-top: 5px;margin-bottom: 5px;
}
#BookappiontmentContiner {
/ text-align: left; /
}
.chosen-container{
text-align: left;
min-width: 28%;
}
.chosen-container, .chosen-container-single, .chosen{
	width:100%;
	min-width: 100%;
}

.checkboxLabel{
margin-right: 15px;
}
#weekNameListdiv{
padding: 0px;
}
#apmtheading{
padding-left: 22px;
padding-top: 5px;
}
.top15{
     margin-top: -15px;
}
.radiomana{
margin-top: 8px;
margin-bottom: -8px;
}
.toppad15{
     padding-top: 15px !important;
}

.tab-content {
   display: block !important;
       min-height: 460px;
}
.nav-tabs.tabs-dark {
    background-color: #339966;
}


.padnil{
 padding: 0px !important;
}
.balance {
    float: left !important;
    margin-top: 5px !important;
    font-weight: normal !important;
}
.weblable{
	font-weight: normal !important;
    font-size: 11px !important;
}
.text-danger {
    color: #A94442;
    font-weight: normal;
    margin-top: 2px;
}
</style>


<div id="BookappiontmentContiner">
<div class="">
<div class="col-lg-12 booknasd hidden id="bookapmtrdiodiv">	
				<div class="car-info <%=bghide%>">
						<div id="radio" class="">
						<ul>
							<li title="OPD"><input type="radio" tabindex="103" id="radio1" name="radio" onclick="showAppointmentDialogBox()" />&nbsp;<label for="radio1">OPD</label></li>
							<li title="OT"><input type="radio" id="radio3" name="radio" onclick="showotstaff()" />&nbsp;<label for="radio2">OT</label></li>
							<li title="Block slot"><input type="radio" tabindex="104" id="radio2" name="radio" onclick="showBlockingDialogBox()" />&nbsp;<label for="radio2">Block Slot</label></li>
						</ul>
						</div>
					</div>
						
						</div>
</div>
<div class="bookapoinbac" id="bookapmtrptdiv">

			<div class="row">
			
				<div class="col-lg-12" id="apmtheading" >
				
				<div class="hidden">
  <span id="opdrepeatspan" class="<%=mobhide %>">
 
  <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">Repeat Booking for </a>
  							<select name="weekNumber" id="weekNumber" onchange="showApmtWeekList(this.value)" >
								<%for(int i=0;i<=24;i++){ %>
									<option value="<%=i %>"><%=i %></option>
								<%} %>	
							</select>
						 week</span>
						 </div>
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong class="<%=mobhide %>"><label>Last Appointment:-</label><label id="lastappdate"></label>&nbsp;/&nbsp;<label id="lastapp"></label>&nbsp;/&nbsp;<label id="lastappcharges"></label>&nbsp;&nbsp;&nbsp;&nbsp;Days:<label id="lastapmtdays"></label></strong>
						 <div class="collapse hidden" id="collapseExample">
							  <div class="well1">
							   <input type="checkbox" name="wholeweek" id="wholeweek"  onclick="checkAllWeek();"/>&nbsp; <label>Select All</label>
							    <div class=" hidden-xs hidden-sm" id="weekNameListdiv" tabindex="102">
								<s:checkboxlist label="" list="weekNameList" theme="simple"
	   							name="weekName"/>
	   							</div>
							  </div>
							</div>
							</div>
							
							
							
						</div>
						
					</div>
						
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bookav">
								<input type="hidden" name="slotId" id="slotId" /> <input
									type="hidden" name="diaryUserId" id="diaryUserId" />
							</div>
						</div>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
<div class="col-lg-12 col-md-12 col-xs-12" style="margin-top: -10px; padding: 0px;">
<%String hideduration="";
String hidemobduration="";																					
if(loginfoopd.getUserType()==5){ 
hideduration="hidden";
}else{
	hidemobduration="hidden";
}
%>
                                                   		
<div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
<div class="form-group" style="margin-bottom: 0px;">
			
				<label for="inputPassword3"><%=loginfoopd.getPatientname_field() %> Name<a class="red">*</a> <s:hidden name="clientId" id="clientId"></s:hidden><s:hidden id = "findclient" name = "findclient"></s:hidden> &nbsp; (<span id = "amountPending" class="text-danger hidden" style="font-weight: normal !important;"></span>)</label><br>
			<div class="hidden">	
				<s:textfield name="client" id="client" readonly="true" onchange="setTreatmentEpisode()" onclick="showAnotherPopup();"  tabindex="113" cssClass=" form-control showToolTip  amber" placeholder = "Click to Select Patient"></s:textfield>
				</div>
				<div>
				<label id="puresevaclntname" style="font-size: medium;font-weight: 900;"></label>
</div>
				<div class="<%=mobhide%>">
				
				<input type="button" class="form-control btn" onchange="setTreatmentEpisode()" onclick="showAnotherPopup();" value="Select Patient" style="width: 41%;background-color: #4eb6c2 !important;color: white;font-size: medium;font-weight: 700;">
    			</div>
    			
				
				
				<div style="color: #7d7d7d;margin-top: 3px;" id="otclientinfodiv">
			        <small><b>IPD No : </b> <span id="otipdno"></span></small> &nbsp;|&nbsp; <small><b>Age / Gender : </b> <span id="otagegender"></span></small> &nbsp;|&nbsp; <small><b>Ward / Bed No : </b> <span id="otwardbedno"></span></small> 
			    </div>
		</div>
				<label id="clientError" class="text-danger balance"></label>
    			<label id="clientError" class="text-danger balance"></label>
</div>
<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12" id="motcatdiv">
                                                   				<div class="form-group">
																			<label for="inputPassword3">Category <span class="red">*</span></label><br>
															    			<select onchange="showPlanedDetails()" name="otplaned" id="otplaned" tabindex="110" class="form-control showToolTip chosen" title="">
															    									<option value="0">Select Category</option>
																								    <option value="Planed">Planned</option>
																								    <option value="Unplaned">Unplanned</option>
																								</select>
																	</div>
                                                   		</div>



<div class="col-lg-4 col-md-4 col-xs-12 col-sm-4 ">
<label></label>
			<div class="form-inline <%=hidemobduration%>" style="font-size: larger;">
			<div class="form-group">
			<strong>
			<label>Appointment Time:</label>
			</strong>
			</div>
			<div class="form-group">
			<strong><label id="sTimelable"></label></strong>
			</div>
			
			</div>	
			<div class="form-inline <%=hidemobduration%>" style="font-size: larger;">
			<div class="form-group">
			<strong>
			<label>Appointment Date:</label>
			</strong>
			</div>
			<div class="form-group">
			<strong><label id="bookdatelable"></label></strong>
			</div>
			
			</div>	
	<div class="form-group <%= hideduration%>">
			<label for="inputEmail3">Start Time / End Time <a class="red">*</a> / Duration</label>
			<div class="form-inline">
				<!-- <div class="form-group"> -->
					 <s:if test="%{#startTimeList != 'null'}">
								<s:select id="sTime" name="sTime" list="startTimeList"
									theme="simple" onchange="resetAppointmentField();"
									cssClass="form-control showToolTip chosen-select" title="Select Start Time"/>
							</s:if>
			 		 	<label id="sTimeError" class="text-danger"></label>
				<!-- </div> -->
				<div class="form-group">
						<s:if test="%{#endTimeList != 'null'}">
								<s:select id="endTime" name="endTime" list="endTimeList"
									theme="simple" disabled="true"
									cssClass="form-control showToolTip" title="Select End Time"/>
							</s:if>
				</div>
				<div class="form-group">
				 <s:if test="%{#apmtDurationList != 'null'}">
								<s:select id="apmtDuration" name="apmtDuration"
									list="apmtBlockDurationList" headerKey="0" headerValue="00:00" tabindex="110"
									theme="simple" onchange="setBookAppointmentEndTime(this.value)"
									cssClass="form-control showToolTip" title="Select Duration"
									 />
							</s:if>
				 
						
		</div>
			
			</div>
		</div>

</div>

<%if(loginfoopd.isBalgopal()){ %>
<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
			
</div>
<%} %>
</div>

<!-- displaynonemobile -->
<% LoginInfo l22 = LoginHelper.getLoginInfo(request);%>
<div class="" id="otstaffdiv">
<%if(l22.getUserType()!=5){ %>
	<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;background-color: #f5f5f5;margin-bottom: 15px;">
<%}else{ %>
	<div class="col-lg-12 col-md-12 col-xs-12 " style="padding: 0px;background-color: #f5f5f5;margin-bottom: 15px;">
<%} %>
	
	
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0px;">
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 toppad15 <%=mobhide%>">
																	<div class="form-group">
																		<label for="inputPassword3">Who will Pay</label><br>
																		
																	      <input disabled="disabled" checked="checked" type="radio" name="paybypatient" id="paybypatient"  value="Client" onclick="paybyTreatmentEpisodeAjax(this.value)">&nbsp;<%=loginfoopd.getPatientname_field() %> (Self)
																	   &nbsp;&nbsp;
																	      <input disabled="disabled" type="radio" name="paybypatient" id="paybypatient1"  value="Third Party" onclick="paybyTreatmentEpisodeAjax(this.value)">&nbsp;Third Party <br><span id="exclusivetpname" class="fontnew thridpar"></span> 
																	   
																	</div>
																			
																	</div>
																	
																	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 toppad15" id="prodepartmentdiv">
			
			
																		<div class="form-group">
																			<label for="inputPassword3">Department</label>
																			
																			<s:select onchange="showotprocedureList(this.value)" list="otdepartmentList" name="otdepartment" id="otdepartment"
																			headerKey="0" headerValue="Select Department"
																			listKey="id" listValue="name" cssClass="form-control chosen-select"/>
																		</div>
																	</div>
																	<div id="" class="col-lg-3 col-md-3 col-sm-3 col-xs-12 toppad15 hidden <%=mobhide%>">
																		<div class="form-group">
					 <label for="inputPassword3">Procedure<span class="red">*</span></label>
					 <span><a href="#" data-toggle="modal" data-target="#addproocedurepopup">+Add Procedure</a></span>
					 <div id="otprocedurediv">
					 <s:select onchange="filterOtCharges(this.value)" name="otprocedureplaned" id="otprocedureplaned" list="procedureList"
					 listKey="id" listValue="name" cssClass="form-control showToolTip chosen"
					 headerKey="0" headerValue="Select Procedure"
					 />
					
					</div>		
					  <input type="hidden" id="procedureid" name="procedureid">
																</div>
																		
																	</div>
																	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 toppad15">
																	<div class="form-group">
																			<label for="inputPassword3">Select Appointment Type <a class="red">*</a>
																			<span id="morechargesspanid" style="display:none;"><a href="#" onclick="showaddotchargepopup()"> + More Charge</a></span>
																			</label>
																			<div id="tpappointmenttype">
																							<s:select name="duration" id="apmtType"
																								list="appointmentTypeList" listKey="id" listValue="name"
																								headerKey="0" headerValue="Charge Type"
																								onchange="setAppointmentTypeTimeAjax(this.value)"
																								cssClass="form-control chosen"
																								title="Charge Type" theme ="simple"
																								style="width:100%"
																								></s:select>
																								
																							
																							
																						</div>
																						
																							<label id = "apmtTypeDuration" class="text-danger  balance <%= hideduration%>" style="color: red !important;"></label>
																							<label id="apmtTypeError" class = "text-danger red balance <%= hideduration%>" style="color: red !important;"></label>
																						
																		</div>
																	</div>
																	
		</div>
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12" id="otassistingstaffdiv">
				<div class="form-group">
																		  	<input type="hidden" name="selectedotroom" id="selectedotroom"/>
																		    <label for="inputPassword3" class="control-label">Assisting staff</label><br>
																		    <div id="OT-height">
																		    	<s:iterator value="staffList">
																		      	<s:if test="jobtitle=='OT'">
																		      		<div id="otdiv">
																		      			<input type="radio" name="otroom" id="otst<s:property value="id"/>" value="<s:property value="id"/>">&nbsp;<s:property value="fullname"/><br>
																		      		</div>
																		      	</s:if>
																		      	<s:else>
																		      		<div id="staffdv">
																		      			<input class="casees" type="checkbox" name="otst<s:property value="id"/>" id="otst<s:property value="id"/>" value="<s:property value="id"/>">&nbsp;<s:property value="fullname"/><br>
																		      		</div>
																		      	</s:else>
																		      </s:iterator>
																		    </div>
																		  </div>
		</div>
		<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12" style="padding:0px;">
			<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 <%=mobhide%>" style="padding:0px;margin-top: -15px;">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 toppad15 hidden">
			
			
			<div class="form-group <%=bghide%>">
																		<label for="inputPassword3">Treatment Episode</label>
																		
																		   <div id="treatmentepisodeajax">
																			<select name="treatmentEpisode" id="treatmentEpisode" tabindex="116"
																									class="form-control showToolTip chosen" title="Treatment Episode"
																									onchange="setEpisodeDetails(this.value)"
																									>
																									<option value="0">Treatment Episode</option>
																								</select>
																								
																						
																			</div>	
																			
																			<div class="hidden" id="tpDIV" style="padding: 0px;margin-top: 5px;">	
																			<%if(loginfoopd.isTreatment_episode_acc()==false){ %>
																			<a href="#" id="addTreatment" class="btn btn-danger" tabindex="118"
																						    onclick="addTreatmentEpisode()" title="Create Treatment"><i class="fa fa-plus-square"></i></a>
																		<%} %>
																			 </div>	
																				
																						<span id="sessionDetail" class="weblable" style="padding-top: 10px;position: absolute;"></span>
																						<span id="treatmentEpisodeError" class = "text-danger balance"></span>
																	</div>
				
			</div>
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 toppad15" id="otsurgeondiv">
			
			<div class="form-group">
																				 <label for="inputPassword3">Surgeon Name <span class="red">*</span></label>
																				 <s:select name="otsurgeonname" id="otsurgeonname" list="surgeonlist"
																				 listKey="id" listValue="fullname" cssClass="form-control showToolTip chosen"
																				 headerKey="0" headerValue="Select Surgeon"
																				 />
																							
																		</div>
					
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 toppad15" id="otanesthesiadiv">
					<div class="form-group">
																				 <label for="inputPassword3">Anesthesia Doctor</label>
																				 
																				 <div id="newanidoctordiv">
																				  <s:select onchange="showdoctornameforfees()" name="otanesthesia" id="otanesthesia" list="anesthesiaList"
																				 listKey="id" listValue="reference" cssClass="form-control showToolTip chosen"
																				 headerKey="0" headerValue="Select Anesthesia"
																				 />
																				 
																				 </div>
																				
																		</div>
																			<div class="form-group hidden">
																				 <label for="inputPassword3">Diagnosis <span class="red">*</span></label>
																								<select name="otdiagnosis" id="otdiagnosis" tabindex="110" class="form-control showToolTip chosen" title="">
																								    <option value="DM-II">DM-II</option>
																								    <option value="Sever TVB">Sever TVB</option>
																								</select>
																		</div>
			</div>
				
			</div>
			<div class="row <%=mobhide%>">
			<div class="form-group" style="margin-left: 34px">
			<label>Height(cm)</label><br>
			<input type="number" name="height" id="height" class="form-control" style="width: 40%" onchange="calculatebmi()">
			<%-- <s:textfield name="height" id="height" cssClass=""/> --%>
			</div>
			<div class="form-group" style="margin-left: -70px">
			<label>Weight(Kg)</label><br>
			<input type="number" name="weight" id="weight" class="form-control" style="width: 40%;"onchange="calculatebmi()")>
			</div>
			<div class="form-group" style="margin-left: -70px">
			<label>BMI</label><br>
			<input type="number" name="bmi" id="bmi" class="form-control" style="width: 40%;">
			</div>
			<div class="form-group" style="margin-left: -70px">
			<label>Head Circ.</label><br>
			<input type="number" name="headcir" id="headcir" class="form-control" style="width: 40%;">
			</div>
			<div class="form-group" style="margin-left: -75px">
			<label>Temp(°F)</label><br>
			<input type="number" name="tempr" id="tempr" class="form-control" style="width: 40%;">
			</div>
			</div>
			<%if(loginfoopd.isSimpliclinic()){ %>
			<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6">
			<div class="form-group">
				<label for="inputPassword3">Notes</label>
				<s:textarea name="notes" id="notes" rows="1" cssClass="form-control showToolTip notebox"  tabindex="119" placeholder="Enter Notes"></s:textarea>
		</div>
			</div>
			<div class="col-lg-6 col-xs-6 col-md-6 col-sm-6">
			<label>Does Have an Allergy?</label>&emsp;
			<input type="radio" id="allergies" name="allergy" value="yes" onclick=""> <label >Yes</label>&emsp;&emsp;
			<input type="radio"  id="noallergy" name="allergy" value="no" checked="checked"> <label >No</label>
			 <div class="form-group">
				
				<s:textarea name="allergynotes" style="display: none;" id="allergynotes" rows="1" cssClass="form-control showToolTip notebox"  tabindex="119" placeholder="Enter Allergy Notes"></s:textarea>
		</div> 
			</div>
			<%}else{ %>
			<div class="col-lg-12 col-xs-12 col-md-12 col-sm-12">
			<div class="form-group">
				<label for="inputPassword3">Notes</label>
				<s:textarea name="notes" id="notes" rows="1" cssClass="form-control showToolTip notebox"  tabindex="119" placeholder="Enter Notes"></s:textarea>
		</div>
			</div>
			<%} %>
		<% String clinicid=loginfoopd.getClinicid1(); %>
		<input type="hidden" id="hiddenclientid" value="<%=clinicid%>">
		</div>
			
			
			
			
		
		</div>
		
		
	</div>
	

																	
																	
															</div>
															
															
															

</div>








<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 hidden">
<form class="form-horizontal">
  <div class="form-group hidden">
    <label for="inputEmail3" class="col-sm-5 control-label rightte">Appointment with:</label>
    <div class="col-sm-7">
      <s:textfield id="user" name="user" readonly="true" tabindex="105"
								cssClass="form-control showToolTip" title="Enter User Name"
								placeholder="Enter User Name"
								 />
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-5 control-label rightte">Discipline:</label>
    <div class="col-sm-7">
      <s:select id="diciplineName" name="diciplineName" list="disciplineList" listKey="id" listValue="discipline"
							headerKey="0" headerValue="Select Discipline"
							title="Select Discipline" 
							cssClass="form-control showToolTip" data-toggle="tooltip" disabled="true" />
    </div>
  </div>
  <div class="form-group hidden">
    <label for="inputPassword3" class="col-sm-5 control-label rightte">Location:</label>
    <div class="col-sm-7">
      <s:if test="%{#locationList != 'null'}">
								<s:select id="location" name="location" list="locationList"
									listKey="locationid" headerValue="Select Location"
									disabled="true" listValue="location" theme="simple"
									cssClass="form-control showToolTip" title="Select Location" tabindex="106"
									 />
							</s:if>
    </div>
  </div>
  <div class="form-group hidden">
    <label for="inputPassword3" class="col-sm-5 control-label rightte">Date:</label>
    <div class="col-sm-7">
      <s:textfield name="date" id="date" readonly="true"
								cssClass="form-control showToolTip" title="Select Date" tabindex="107"
								placeholder="Select Date"
								></s:textfield>
							<a class="hidden-lg hidden-md hidden-sm hidden-xs" href="#" onclick="showApmtAvalblty()" tabindex="107">Check Availability</a>
    </div>
  </div>
  
  
  
  
 
</form>
</div>
<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 hidden ">
<form class="form-horizontal">
  
  <div class="form-group">
   
    <div class="col-sm-7 col-xs-12 padrinil">
      
    </div>
  </div>
 <div class="form-group" style="display: none;">
    <label for="inputPassword3" class="col-sm-5 control-label rightte">Room:</label>
    <div class="col-sm-7 padrinil">
      <select name="room" id="room" class="form-control showToolTip chosen" tabindex="112"
								title="Select Room" style="height: 28px; padding: 3px 12px">
								<option value="Room1">Room1</option>
								<option value="Room2">Room2</option>
							</select>
    </div>
  </div>
  
  
  
  
  <div class="form-group ">
    <label for="inputPassword3" class="col-sm-5 control-label rightte"><a class="red">*</a>Condition / Diagnosis:</label>
    <div class="col-sm-7 padrinil">
    	<div id="dpartmntcondiv">
      <s:select id = "condition" name = "treatmentType" list="condtitionList" tabindex="115" headerValue="Select Condition" headerKey="0" listKey="id" listValue="treatmentType" cssClass="form-control showToolTip chosen amber"
						data-toggle="tooltip" theme="simple" onchange="updateClientCondition(this.value)"></s:select>
			</div>
			<label id = "conditionError" class="text-danger"></label>
    </div>
  </div>
  
  
   
      
    
  <div class="form-group">
    
    <div class="col-sm-7 padrinil">
      <div class="row">
						
								
							</div>
							<div class="row hidden">
							<div class="col-lg-2 col-md-2 col-xs-2 popold">
									<a href="#" id="vewTreatment" class="btn btn-danger" tabindex="117"
										onclick="viewTreatmentEpisodeCountData()" title="View Treatment"><i class="fa fa-eye"></i></a>
								</div>
							
								
								<div class="col-lg-2 col-md-2 col-xs-2 popold">
									<a href="#" id="" class="btn btn-danger" tabindex="118"
										onclick="addTreatmentEpisode()" title="Create Treatment"><i class="fa fa-plus-square"></i>
										</a>
								</div>
							</div>
      
    </div>
  </div>
  
  
</form>
</div>
</div>

			
					</div>
		<input type="hidden" id="dept" name="dept">		
		
		<!--   08 feb 2018 -->
<div id="addproocedurepopup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add Procedure</h4>
      </div>
      
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding-top: 9px;">
        			<div class="form-group">
        			<label>Name</label><label class="text-danger">*</label>
					<s:textfield id="addprocedurename"
						cssClass="showToolTip form-control" data-toggle="tooltip"
						title="Enter Name" placeholder="Enter Name"
						onkeyup="initialCap(this)" />
        			</div>
        	</div>
        </div>
        
        
        
		<div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 9px;">
        		<div class="form-group">
        			<label>Department</label><label class="text-danger">*</label>
        			<s:select list="disciplineList" listKey="id" headerKey="0" headerValue="Select department" listValue="discipline" name="addproceduredescription" title="select department" cssClass="form-control showToolTip chosen-select"  > </s:select>
        			</div>
        	</div>
        </div> 
     </div>
     
     <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="savenewprocedure()" style="margin-top: 20px;">Save</button>
     </div>
    </div>

  </div>
</div>             
		
		<script>
		$(function() {
		  $('#OT-height').slimScroll({
		   		height : '115px',
		   		railVisible: true,
				alwaysVisible: true
			  });
 });
		function calculatebmi(){

			   var w=document.getElementById("weight").value; 
			   var h=document.getElementById("height").value; 
			    
			   var d=h/100; 
			   var bmi=w/(d*d);
			   var result=Math.round(bmi*100)/100; 
			   document.getElementById("bmi").value=result;	    
			  /*  headcir=h*w;
			   headcir=headcir/3600
			   headcir=Math.sqrt( headcir);
			   headcir=Math.round(headcir*100)/100;
			   document.getElementById("headcir").value=headcir; */	
			}

		</script>
		<script type="text/javascript">
		
		 $(function () {
		      $("#allergies").click(function () {
		          if ($(this).is(":checked")) {
		              $("#allergynotes").show();
		          } else {
		              $("#allergynotes").hide();
		          }
		      });
		      $("#noallergy").click(function () {
		          if ($(this).is(":checked")) {
		              $("#allergynotes").hide();
		          } else {
		              $("#allergynotes").hide();
		          }
		      });
		     
		  });
		
		</script>