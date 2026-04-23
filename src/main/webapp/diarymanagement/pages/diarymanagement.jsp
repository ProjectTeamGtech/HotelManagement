<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.DiaryManagement.eu.entity.DiaryManagement"%>
<script type="text/javascript" src="common/js/pagination.js"></script>

<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="diarymanagement/js/diarymanagement.js"></script>
<script type="text/javascript" src="diarymanagement/js/popupscript.js"></script>



<script src="diarymanagement/js/jquery.freezetablecolumns.1.1.js"></script>

<style>
.checkbox label {
    line-height: 23px;
}
.setline{
	margin-top: 3px;
    position: absolute;
    margin-left: 5px;
}
</style>

<script>
	$(function() {
		$( "#appointmentTableDiv" ).dialog({
			autoOpen: false,
			resizable: false,
			height:650,
			width:1000,
			modal: true,
			buttons: {
				
				Cancel: function() {
					window.location.reload();
					$( this ).dialog( "close" );
				},
		
				
			
				
			}
		
		
		});
		
		
		
		$( "#appointmendiv" ).dialog({
			autoOpen: false,
			resizable: false,
			height:400,
			width:450,
			modal: true,
			buttons: {
				
				Save: function() {
					$(this).saveSlot(editappointmentid);
					$( this ).dialog( "close" );
				},
				Cancel: function() {
					
					$( this ).dialog( "close" );
				}
				
			}
		});
	});
		
	
	/* $(document).on('click','.ui-dialog-titlebar-close',function(){
		window.location.reload();
	}); */
	
	function resetDiary(){
		document.getElementById('hdnactionid').value = "";
		document.getElementById('fullcalfrm').submit();
		//window.location.reload();
	}
	
	function setFullCalander(){
		document.getElementById('hdnactionid').value = "";
		document.getElementById('fullcalfrm').submit();
	}
	
	
	
	window.onload = function(){
		<%ArrayList<DiaryManagement> duserlist = (ArrayList<DiaryManagement>)session.getAttribute("userList");%>
		var p = 0;
		<%for(DiaryManagement dmngmnt: duserlist){%>
			p++;
			document.getElementById('uindex'+p).value = <%=dmngmnt.getUsrPosition()%>
		<%}%>
	}
	
	
	function changeUserPosition(prvposition, curposition){
		//alert(prvposition+'/'+curposition);
		
		
		document.getElementById('prevPosition').value = prvposition;
		document.getElementById('curPosition').value = curposition;
		document.getElementById('hdnactionid').value = "position";
		
		document.getElementById('fullcalfrm').submit();
	}
	
		
</script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Set Up Appointment Diaries</h4>

									</div>
								</div>
								<div class="col-lg-12 col-md-12 paddingnil topback2">
										<div class="col-lg-2 col-md-2 col-sm-2">
										<s:form action="DiaryMangent" id="fullcalfrm" theme="simple">
												<s:hidden name="prevPosition" id="prevPosition"/>
												<s:hidden name="curPosition" id="curPosition"/>
												<s:hidden name="action" id="hdnactionid"/>
												<s:checkbox  name="fullCalander" id="fullCalander" onclick="setFullCalander()"/><span class="setline">View Full Calendar</span>
											</s:form>
										
<%@taglib uri="/struts-tags" prefix="s"%>

										</div>
										<s:form action="DiaryMangent" theme="simple">
										<div class="col-lg-4 col-md-4 col-sm-4">
											
											<s:hidden name="fullCalander" id="fullCalander"/>
											<div class="input-group">
												<s:textfield theme="simple" name="searchText"
													placeholder="Search by Name" size="70" cssClass="form-control" />
								
												
												
											</div>
									
											
										</div>
										<div class="col-lg-1 col-md-1 col-sm-3">
											<span class="input-group-btn">
											<input type="submit" value="Go" class="btn btn-primary" /></span>
										</div>
										
									</s:form>
										<div class="col-lg-2 col-md-2 col-sm-2">
											<a href="#" class="btn btn-primary" onclick="opencPopup('rotaDiaryMangent')">View Rota</a>
										</div>
										<div class="col-lg-3 col-md-3 col-sm-3">
										<s:form action="nextprevDiaryMangent" theme="simple">
											<s:hidden name="fullCalander" id="fullCalander"/>
											<div class="col-lg-12 col-md-12 col-xs-12">
											<div class="col-lg-4 col-md-4 col-xs-4">
												<s:submit name="actionType" value="Prev" cssClass="btn btn-primary"
													style="width:100%" title="Previous" />
											</div>
											<div class="col-lg-4 col-md-4 col-xs-4">
												<s:textfield cssStyle="text-align:center;width:100%;"
													name="diaryYear" id="diaryYear" size="5" readonly="true"
													theme="simple" cssClass="form-control" title="Year"></s:textfield>
											</div>
											<div class="col-lg-4 col-md-4 col-xs-4">
												 <s:submit name="actionType" value="Next" cssClass="btn btn-primary"
													style="width:100%"  title="Next"/>
											</div>
											</div>
										</s:form>
										</div>
										</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>




<div class="row">
	<div class="col-lg-12">
		<!-- Modal -->
		<div class="modal fade" id="appointmentTableDiv2" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" onclick="resetDiary();" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel1">Set Schedule</h4>
					</div>
					<div class="modal-body">
					
					<div style="min-width:882px;"class="table-responsive" >
						<div id="slotpopupdiv"
							style="background-color: #cccccc; padding: 4px; width: 100%; height: 41px;">

							<font style="font-size: 12px; font-weight: bold;" >Diary
								User :</font>
							<s:textfield cssStyle="width:20%;font-size: 12px"
								cssClass="text ui-widget-content ui-corner-all" id="diaryUser"
								name="diaryUser" theme="simple" />
							<font style="font-size: 12px; font-weight: bold;">for Week
								Commencing :</font> <input type="text" 
								class="text ui-widget-content ui-corner-all" style="width: 20%;font-size: 12px"
								id="commencing" name="commencing">
								
						</div>
						<br>

						<table id="slotpopuptable" width="100%" cellpadding="0"
							cellspacing="0" class="timer-table" style="margin-top: -17px;">
							<col width="5%" />
							<col width="7%" />
							<col width="7%" />
							<col width="7%" />
							<col width="7%" />
							<col width="7%" />
							<col width="7%" />
							<col width="7%" />

							<thead
								style="position: relative; top: expression(offsetParent.scrollTop);">
								<tr>
									<th style="background-color: #DFD8D4"></th>
									<th id="mon_id">Monday</th>
									<th id="tue_id">Tuesday</th>
									<th id="wed_id">Wednesday</th>
									<th id="thu_id">Thursday</th>
									<th id="fri_id">Friday</th>
									<th id="sat_id">Saturday</th>
									<th id="sun_id">Sunday</th>

								</tr>
							</thead>
							<tbody>

								<%
									LoginInfo loginfo = LoginHelper.getLoginInfo(request);
									//int ct = loginfo.getClinicStartTime();
									int ct = 1;
									int countslot = 1;
									
									//int clinicendtime = loginfo.getClinicEndTime();
									int clinicendtime = 24;
									clinicendtime = clinicendtime - ct;
									clinicendtime = clinicendtime + 1;
									
									String weekName[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
											"Friday", "Saturday", "Sunday" };
								%>

								<%
									for (int i = 1; i <= clinicendtime; i++) {
								%>
								<tr>
									<td class="ui-state-default" valign="top"
										style="font-size: 14px; font-weight: bold;"><%=ct%>:00</td>

									<%
										for (int j = 0; j <= 6; j++) {
									%>
									<td valign="top" id="<%=countslot%>">
										<%
											for (int k = 0; k <= 11; k++) {
										%> <%
 	if (k == 6) {
 %>
										<div id="<%=(5 * k)%>min<%=countslot%>" class="yellow"
											title="<%=ct%>:<%=(5 * k)%>"
											onclick="$(this).MessageBox('<%=weekName[j]%>',<%=j%>,'save','0','0','','','','','','<%=ct%>')"
											style="background-image: url('diarymanagement/img/line.png'); background-repeat: repeat-x;"></div>
										<%
											} else {
										%>
										<div id="<%=(5 * k)%>min<%=countslot%>" class="yellow"
											title="<%=ct%>:<%=(5 * k)%>"
											onclick="$(this).MessageBox('<%=weekName[j]%>',<%=j%>,'save','0','0','','','','','','<%=ct%>')"></div>
										<%
											}
										%> <%
 	}
 %>

									</td>
									<%
										countslot++;
									%>
									<%
										}
									%>


									<%
										ct++;
									%>
								</tr>

								<%
									}
								%>


							</tbody>

						</table>
						</div>
					</div>
					<div class="modal-footer">
						<button onclick="resetDiary()" type="button" class="btn btn-primary" data-dismiss="modal">Close</button>

					</div>
				</div>
			</div>
		</div>

	</div>
</div>


<div class="row">
	<div class="col-lg-12">
		<!-- Modal -->
		<div class="modal fade" id="appointmendiv3" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content" style="margin-left: -8px;margin-right: 8px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel2">Set Schedule  <span style="font-weight:bold;" id="wcday"></span> <span id="wc"></span></h4>
					</div>
					<div class="modal-body">
					<div class="row">
					<div class="col-lg-12">
					<input type="hidden" name="diaryuserid" id="diaryuserid" /> <input
									type="hidden" name="selecteddiaryUserid"
									id="selecteddiaryUserid" /> <input type="hidden" name="tdcode"
									id="tdcode" />
									<div class="col-lg-8 col-md-8 col-sm-8">
									<div class="form-horizontal">
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-4 control-label">Diary User</label>
						    <div class="col-sm-8">
						      <input type="text" name="selectedDiaryUser" id="selectedDiaryUser" class="form-control" />
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-4 control-label">Location / Building / City</label>
						    <div class="col-sm-8">
						     <s:if test="%{#locationList != 'null'}">
									<s:select name="location" list="locationList" headerKey="0" headerValue="Select Your Location"
										listKey="locationid" listValue="location" theme="simple"
										cssClass="form-control" />
								</s:if>
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-4 control-label">Room / Area / Location</label>
						    <div class="col-sm-8">
						      <s:select name="room" id="room" list="wardList"
								listKey="name" listValue="name" cssClass="form-control"
								headerKey="0" headerValue="Select Your Location"/>
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-4 control-label">Start Time</label>
						    <div class="col-sm-8">
						     <s:if test="%{#startTimeList != 'null'}">
									<s:select id="sTime" name="sTime" list="startTimeList"
										cssClass="form-control" theme="simple" />
								</s:if>
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-4 control-label">End Time</label>
						    <div class="col-sm-8">
						      <s:if test="%{#endTimeList != 'null'}">
									<s:select id="endTime" name="endTime" list="endTimeList"
										cssClass="form-control" theme="simple" />
								</s:if>
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-4 control-label">Duration</label>
						    <div class="col-sm-8">
						     <s:if test="%{#apmtDurationList != 'null'}">
										<s:select id="apmtDuration" name="apmtDuration"
											list="apmtDurationList"  tabindex="110"
											theme="simple" onchange="setBookAppointmentEndTime(this.value)"
											cssClass="form-control showToolTip" title="Select Duration"
											 />
									</s:if>
						    </div>
						</div>
					</div>
									</div>
									<div class="col-lg-4 col-md-4 col-sm-4">
									<div style = "background-color: rgb(234, 238, 240);padding-left: 18px">
						
						<div class="row">
							<div class="col-lg-12" id="apmtheading">
								 <label
									class="text-info reapeatslot">Repeat for <select name="weekNumber" id="weekNumber" >
								<%for(int i=0;i<=24;i++){ %>
									<option value="<%=i %>"><%=i %></option>
								<%} %>	
								
								
							</select>
						 weeks</label> 
						
							</div>
							
							<div id="weekNameListdiv" style="padding-left: 16px;">
								
	   							<div class="checkbox">
							        <label>
							          <input type="checkbox" name="wholeweek" id="wholeweek" onclick="checkAllWeek();"/> Select All
							        </label>
							        <br>
							        <label>
							          <input type="checkbox" id="weekName-1" > Monday
							        </label>
							        <br>
							        <label>
							          <input type="checkbox" id="weekName-2" > Tuesday
							        </label>
							        <br>
							        <label>
							          <input type="checkbox" id="weekName-3" > Wednesday
							        </label>
							        <br>
							        <label>
							          <input type="checkbox" id="weekName-4" > Thursday
							        </label>
							        <br>
							        <label>
							          <input type="checkbox" id="weekName-5" > Friday
							        </label>
							        <br>
							        <label>
							          <input type="checkbox" id="weekName-6" > Saturday
							        </label>
							         <br>
							        <label>
							          <input type="checkbox" id="weekName-7" > Sunday
							        </label>
							      </div>
	   							
	   							
	   							
	   						</div>
						</div>
						
						
						<div class="row" style="display: none;">
							<div class="col-lg-12">
								<h4>Online Patient Booking</h4>
								<s:checkbox name="onlineBooking" id="onlineBooking"
									fieldValue="false" theme="simple"></s:checkbox>
								<label for="onlineBooking">is This Slot Available for
									Online Booking</label>
							</div>
						</div>
					</div>
									</div>
					
						
						
						
					</div>
					</div></div>
					<div class="modal-footer">
						<button  type="button" class="btn btn-primary" data-dismiss="modal" onclick="resetEditId()">Close</button>
						<button type="button" class="btn btn-primary"
							onclick="saveSlot1();">Save Changes</button>
							<button style="display: none;" id="deletediaryid" type="button" class="btn btn-primary"
							onclick="checkAppointmentExistOnSlot();">Delete</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>




<div id="login" class="block_div">

	<s:if test="hasActionMessages()">
		<div id="common_message" class="message">
			<s:actionmessage id="common_message_text" theme="simple" />
		</div>
	</s:if>

	<span class="error"><s:actionerror id="server_side_error" /></span>


	<div class="" style="width: 100%;" align="center">

		<div></div>

		<div class="diaryuser"></div>
		<br>
		<table align="center" id="freeze-table" width="100%"
			style="font-size: 10px;" class="diarymngmnt-table" cellpadding="0"
			cellspacing="0">
			<tr>
				<th>Diary
					Users</th>
					<th>Set Position</th>
				<s:iterator value="monthList">
				
					<s:iterator value="DateStringList">
						<th><s:property
								value="dateName" /></th>
								
					</s:iterator>

				</s:iterator>

			</tr>

			<%
				int dcount = 1;
			%>
			<%
				ArrayList<DiaryManagement> userList = (ArrayList<DiaryManagement>) session
						.getAttribute("tdUserList");
				
				ArrayList<DiaryManagement> ulist = (ArrayList<DiaryManagement>)session.getAttribute("userList");
			
				int i = 0;
			%>
			<s:iterator value="tdUserList">
				<%
					i++;
				%>
				<tr id="<%=i%>">

					<%
						int j = 0;
					%>
					<s:iterator value="monthtdList">
						<%
							if (j == 0) {
						%>
						<td style="background-color: rgba(126, 206, 253, 0.17)"><div
								style="height: 30px; font-weight: bold;">
								<s:property value="firstName" />
								<s:property value="lastName" />
								
							</div></td>
							<td>
								<%int d = 0;int q=0; %>
								<span style="float:center;">
									<select name="uindex<%=i %>" id="uindex<%=i %>" onchange="(changeUserPosition(<%=i %>,this.value))">
										<% for(DiaryManagement dm : ulist){ d++;%>
											
											<option value="<%=d %>"><%=d %></option>
										<%} %>
									</select>
									
								</span>
							</td>
						<%
							}
						%>
						<%
							j++;
						%>

						<s:iterator value="DateStringList">
						
							<s:if test="weekListname!=null">
								<td
									style="height: 5px; background-color: <s:property value="colorName"/>"
									id="<s:property value="dateName"/>"
									onclick="goPreview('<s:property value="firstName"/> <s:property value="lastName"/>','<s:property value="dateName"/>','<s:property value="diaryYear"/>','<s:property value="diarUserid"/>','<s:property value="tdDateName"/>')">
									<s:property value="weekListname" />
								<%-- 	<a onclick="return confirmedDelete()" title="Delete" class="deld" href="delDiaryMangent?selectedid=<s:property value="tdDateName"/>">x</a> --%>
									<%-- <div class="deld" title="Delete" onclick="deleteDiarySchedule('<s:property value="tdDateName"/>')">x</div> --%>
								</td>
							</s:if>
							<s:else>
								<td
									style="height: 5px; background-color: <s:property value="colorName"/>"
									id="<s:property value="dateName"/>"
									onclick="goPreview('<s:property value="firstName"/> <s:property value="lastName"/>','<s:property value="dateName"/>','<s:property value="diaryYear"/>','<s:property value="diarUserid"/>','<s:property value="tdDateName"/>')">
									<div style="width: 49px;"></div>
									
								</td>
							</s:else>
							<%
								dcount++;
							%>
						</s:iterator>

					</s:iterator>
				</tr>
			</s:iterator>
			
		</table>
	</div>
</div>

<s:form action="DiaryMangent" name="paginationForm" id="paginationForm" theme="simple">
<s:hidden name="fullCalander" id="fullCalander"/>
	<div class="row hidden-xs hidden-sm" id="paginationForm">
		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;margin-top:15px;">
			<div class="col-lg-4 col-md-4 col-xs-4 text-left" style="padding:0px;">
				Total:<label class="text-info"><s:property value="totalRecords" /></label>
			</div>
			<%@ include file="/common/pages/pagination.jsp"%>
			
		</div>
	</div>
</s:form>
											
										</div>
									</div>
								</div>
							</div>
						</div>







