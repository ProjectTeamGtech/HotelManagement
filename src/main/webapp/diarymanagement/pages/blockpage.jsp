<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.ArrayList"%>

<%@ page import="java.io.*,java.util.Locale" %>
<%@ page import="java.text.DateFormat,java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormatSymbols" %>
<html>
<head>
<link href="common/css/datePicker1.css" rel="stylesheet"/>
<script src="common/js/date.js" type="text/javascript"></script>
<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
<script src="common/js/CalenderPicture.js" type="text/javascript"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />
<link type='text/css' href='diarymanagement/css/basic.css' rel='stylesheet' media='screen' />
<script type="text/javascript" src="common/js/pagination.js"></script>
<script src="common/js/date.js" type="text/javascript"></script>
<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
<script src="common/js/CalenderPicture.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="all" href="jsdatepick-calendar/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="jsdatepick-calendar/jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script>
<link rel="stylesheet" type="text/css" href="diarymanagement/css/subModal.css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>
<title>Non-Available Appointment Slot </title>
</head>

<div id="login_main" class="main_layout_content">	
	<div class="form_elements" >	
					
			<s:form action="saveNotAvailableSlot" id="notavailable_form" theme="simple" validate="True">
				                        <table style="font-size: 10px;">
										<input type="hidden" name="selecteddiaryUserid"	id="selecteddiaryUserid" />
										<input type="hidden" name="tdcode" id="tdcode" />
										<col width="2%" />
										<col width="2%" />
										<col width="30%" />
										
										
										<h4 align="left">	Non-Available Appointment Slot</h4>
											
										<tr>
											<td colspan="3"></td>
										</tr>
										<tr>
											<td>Diary User</td>
											
											<td><s:if test="%{#suserList != 'null'}" >
				 							<s:select id="susername" name="susername" list="suserList" listKey="id" listValue="susername" headerKey="0" theme="simple" onchange = "getSearch()"   />
										</s:if></td>
										</tr>
										<tr>
											<td>Location</td>
											
											<td><s:if test="%{#locationList != 'null'}">
												<s:select id="location" name="location" list="locationList" listKey="location"
													listValue="location" theme="simple" />
											</s:if></td>
										</tr>
										<tr>
											<td>Room</td>
											
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
											<td colspan="3">Start Time : <s:if
												test="%{#startTimeList != 'null'}">
												<s:select id="sTime" name="sTime" list="startTimeList" theme="simple" />
											</s:if> End Time : <s:if
												test="%{#endTimeList != 'null'}">
												<s:select id="endTime" name="endTime" list="endTimeList"
													theme="simple" />
											</s:if></td>
										</tr>
										<tr>
											<td colspan="3"> Duration : <s:if
												test="%{#apmtDurationList != 'null'}">
												<s:select id="apmtDuration" name="apmtDuration"
													list="apmtDurationList" theme="simple" />
											</s:if><s:checkbox name="allday" id="allday" theme="simple"></s:checkbox>All Day</td>
										</tr>
										
										<tr>
											<td colspan="3">
											<hr />
											</td>
										</tr>
										<tr>
											<td>Reason for non availability</td>
											<td><s:select list="{'Admin','Break','Lunch','Cancelation','Practice Meeting','Teaching','Teaching Session','Teaching Cources','Available'}" theme="simple"></s:select> </td>
										</tr>
										
										<tr>
											<td colspan="3">
											<hr/>
											</td>
										</tr>
										
										<tr>
										<td>Notes</td> 
										<td><s:textarea name="notes"  id = "notes"></s:textarea></td>
										</tr>
										
										<tr>
											<td colspan="3" align=" id="submitslot">
											<input type="button" value="Submit" onclick="$(this).saveSlot('0');" /> 
											<input type="button" value="Cancel" onclick="$(this).cancelSlot();" /></td>
										</tr>
									</table>
			
		
				</s:form>
		
	
</div>	
</div>

			
		
		
