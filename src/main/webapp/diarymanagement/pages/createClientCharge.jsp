<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.ArrayList"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import = "java.util.Date"%>

<script src="common/js/date.js" type="text/javascript"></script>

<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="common/js/pagination.js"></script>


<script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script>
<script type="text/javascript" src="diarymanagement/js/completeApmt.js"></script>
<script type="text/javascript" src="accounts/js/accounts.js"></script>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />
<script src="diarymanagement/js/jquery.freezetablecolumns.1.1.js"></script>
<script type="text/javascript" src="diarymanagement/js/popupscript.js"></script>
<script type="text/javascript" src="diarymanagement/js/commonAppointmentView.js"></script>

<script>
	$(function() {
		$( "#clientSearchDiv").dialog({
			autoOpen: false,
			resizable: true,
			height: 500,
			width: 650,
			modal: true,
			buttons: {
				
				Cancel: function() {
					$( this ).dialog( "close" );
				}
				
			}
		});
		
		
	
	});
	</script>



<div id="login_main" class="main_layout_content">
	<h2 class="heading" style="margin-left: 24px">Client Charge</h2>
		<div class="form_elements" >	
		<s:form action="paynowCompleteApmt" theme="simple" validate="True">
			    <table width="80%" class = "boxclientthirdpartycharge" style="font-size: 12px">
			<col width="10%" />
				<col width="2%" />
				<col width="10%" />		 
			
				
					<tr>
						<td>Client:</td>
						<td align="center">:</td>
						<td><s:textfield name="client" id = "client" readonly="true" cssClass="text ui-widget-content ui-corner-all" style = "width:91%" ></s:textfield>
						<label id = "clientError" style="color: red"></label>
						</td>
						<td>
						<s:hidden name="clientId" id = "clientId" ></s:hidden>  
					     <input type="button" value=" Search " class="btn btn-primary" onclick="showPopUp()">
											
											
						</td>
					</tr>
					<tr>
						<td>Practitioner</td>
						<td align="center">:</td>
						<td>
						<s:if test="%{#userList != 'null'}" >
				 		<s:select id="diaryUser" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User" onchange="setPractioner(this.value)" cssClass="select ui-widget-content ui-corner-all"  style = "width : 98%; padding: .4em;" />
				 		</s:if>
				 		<label id = "diaryUserError" style="color: red"></label>
						<s:hidden id = "practitionerName" name = "practitionerName"></s:hidden>
						<s:hidden name="practitionerId" id="practitionerId"/>
						</td>
				   </tr>
				   <tr>
						<td>Location:</td>
						<td align="center">:</td>
						<td><s:select id="dept" name="dept" list="{'Any Location','Physiotherapy','Podiatry','Osteopathy','Classes'}" theme="simple" cssClass="select ui-widget-content ui-corner-all" style = "width : 98%;padding: .4em;"/>
							
						
						</td>
				   </tr>
				   <tr>
						<td class="hidden">Location:</td>
						<td align="center">:</td>
						<td><s:if test="%{#locationList != 'null'}">
						<s:select id="location" name="location" list="locationList" listKey="location" headerValue="Select Location"
													listValue="location" theme="simple" cssClass="select ui-widget-content ui-corner-all" style = "width : 98%;padding: .4em;"/>
						</s:if></td>
					</tr>
				<%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
				String temp[] = currentDate.split(" ");
				String temp1[] = temp[0].split("-");
				String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
				
				%>							       
				<tr>
					<td>Date:</td>
					<td align="center">:</td>						        
					<td><input id="commencing" class="text ui-widget-content ui-corner-all" style="width: 91%" type="text" value="<%=date %>" name="commencing"></input>
					<label id = "commencingError" style="color: red"></label>
					</td>
				</tr>
				<tr>
					<td>Start Time </td>
					<td align="center">:</td>
					<td>
					<s:if test="%{#startTimeList != 'null'}">
					<s:select id="sTime" name="sTime" list="startTimeList" theme="simple" onchange="resetAppointmentField();" cssClass="select ui-widget-content ui-corner-all" style = "width: 98%;padding: .4em;"/>
					</s:if>
					<label id = "sTimeError" style="color: red"></label>
					</td>
				</tr>
				<tr>
					<td>End Time</td>
					<td align="center">:</td>
					<td><s:if test="%{#endTimeList != 'null'}">
					<s:select id="endTime" name="endTime" list="endTimeList" theme="simple" disabled="true" cssClass="select ui-widget-content ui-corner-all" style = "width: 98%;padding: .4em;"/>
					</s:if>
				</td>
				</tr>
				
				<tr>
				
					<td>Appointment Type</td>
					<td align="center">:</td>	
					<td><s:select name = "apmtType" id = "apmtType" list="appointmentTypeList" listKey="name" listValue="name" headerKey="0" headerValue="Select Appointment Type" onchange="setApmtChargeAndDuration(this.value)" cssClass="select ui-widget-content ui-corner-all" style = "padding: .3em;"></s:select>
					<label id = "apmtTypeError" style="color: red"></label>
					</td>
					<td id="chargeajax">
					<input type="text" id = "charge" name="charge" class="text ui-widget-content ui-corner-all" />
					Duration :<input type="text" id = "duration" name="duration" class="text ui-widget-content ui-corner-all"  />
				</td>
				
				</tr>
				<tr>
					
					
					<td>Total Charges: </td>
					<td align="center">:</td>
					<td id ="chargeTotalajax"><input type="text" id = "chargeTotal" name="chargeTotal" value="0" class="text ui-widget-content ui-corner-all" style="width: 93%"/></td>
				</tr>
				</table>
				
			    <table width="80%" class = "boxclientthirdpartycharge" style="font-size: 12px">
				<tr>
				<td><b>Select who will Pay this charge</b><input type="radio" id= "payBuy" name="payBuy" value="0"  onclick="setInvoiceeAsClient()">Client
				<input type="radio" id = "payBuy" name="payBuy" value="1" onclick="setInvoiceeAsThirdParty()">Third Party
				 <label id = "payBuyError" style="color: red"></label>
				</td> 
				</tr>
				<tr>
				<td></td>
				<td id = "thirdPartyAjax"><b>Invoicee</b><s:textfield name="invoicee" id = "invoicee" readonly="true" cssClass="text ui-widget-content ui-corner-all" style = "width:56%"/></td>
				
				<tr>
				<td><input type="button" value="Save Charge" onclick="return saveClientCharge()" class="btn btn-primary"/>
				<input type="submit" value="Pay Now" class="btn btn-primary"></td>
				</tr>
			</table>
		</s:form>
	 </div>
</div>

<div id="clientSearchDiv" title="Client Search" >
	<%@ include file="/diarymanagement/pages/allPatientsList.jsp" %>
</div>