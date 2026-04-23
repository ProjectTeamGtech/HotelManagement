<%@taglib uri="/struts-tags" prefix="s" %>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>
<script src="common/js/date.js" type="text/javascript"></script>
<script src="common/js/jquery/jquery.datePicker.js" type="text/javascript"></script>
<script src="common/js/CalenderPicture.js" type="text/javascript"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<link href="common/css/datePicker1.css" rel="stylesheet"/>

<script type="text/javascript" src="diarymanagement/js/appointmentdiaryReport.js"></script>
<script type="text/javascript" src="diarymanagement/js/walkInPreBooked.js"></script>


<div id="login_main" class="main_layout_content">
	<h2 class="title" style="margin-left: 10px">Walk In and Pre-booked </h2>
	
	<div id="login" class="block_div">
	<div class="form_elements">	
		<s:form action="showWalkInAndPreBookedApmtDiaryReport" id = "walkInPreBooked_frm">
		<span class="error"><s:actionerror id="server_side_error"/></span>
		<div class = "dairyuser">
		
			<table>
			
			<tr>
			<td>Dairy User:</td>
			
			<s:if test="%{#diaryUserList != 'null'}" >
				 			<td><s:select id="diaryUser" name="diaryUser" list="diaryUserList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User"   /></td>
				</s:if>
				
			<td>Location:</td>
			
			<s:if test="%{#deptList != 'null'}" >
				 			<td><s:select id="dept" name="dept" list="deptList" listKey="dept" listValue="dept" headerKey="0" theme="simple" headerValue="Select Location"   /></td>
			</s:if>
				
				<td>Date :</td>
				<td><s:textfield name = "date" id = "date" cssClass="date-pick dp-applied" theme="simple"></s:textfield></td>
				
				
				<td>
				<s:checkbox name="status" id="arrived" fieldValue="arrived" value="%{arrived}" title="Arrived" theme="simple"/><s:label value="Arrived" theme="simple"></s:label>
				</td>
				
				<td><s:checkbox name="status" id="beingSeen" fieldValue="beingSeen" value="%{beingSeen}" title="Being Seen" theme="simple"/><s:label value="Being Seen" theme="simple"></s:label></td>
				
				<td><s:checkbox name="status" id="completed" fieldValue="completed" value="%{completed}" title="Completed" theme="simple"/><s:label value="Completed" theme="simple"></s:label></td>
				
				<td><s:checkbox name="status" id="doNotAttend" fieldValue="doNotAttend" value="%{doNotAttend}" title="DO Not Attend" theme="simple"/><s:label value="DNA" theme="simple"></s:label></td>
				<td><s:submit value="Show" theme="simple" cssclass="btn btn-primary"/></td> 
				</tr>
				
						
				
				
				
				
					
				
			
			</table>
			
		</div>
		
		
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Time</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Client</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Type</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Start Time</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">End Time</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Room</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Diary User</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Location</th>
						
						
						
					</tr>
					<s:if test="allWalkInPreBookedList.size!=0">
						<s:iterator value="allWalkInPreBookedList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									<td><s:property  value="startTime" /></td>
									<td><s:property  value="clientName" /></td>
									<td><s:property  value="aptmtype" /></td>
									<td><s:property  value="startTime" /></td>
									<td><s:property  value="endTime" /></td>
									<td><s:property  value="room" /></td>
									<td><s:property  value="diaryUser" /></td>
									<td><s:property  value="dept" /></td>
									
									
									
									
									
									
							</tr>

						</s:iterator>
					</s:if>
					<s:else>
						There is no Record found!!
					</s:else>
							
				</table>
				
			</s:form>	
				
				
				
				
       			
       			

		
		
	</div>
</div>