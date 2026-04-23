<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.ArrayList"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import = "java.util.Date"%>



<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />

<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>


<script>

var style = document.createElement('style');
style.type = 'text/css';
style.innerHTML = '.cssClass1 { min-height:20px; cursor:pointer; }';
document.getElementsByTagName('head')[0].appendChild(style);
var time=0; 

</script>


<script type="text/javascript" src="diarymanagement/js/mobusers1.js"></script>
<script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script>


<script type="text/javascript">


</script>
<div id="login_main" class="main_layoutdash_content"><!--

	

		<h2 class="title" >Appointment Dairy</h2>
	
		<div class="menu">
		
		<input type="submit" value="Block" onclick="showdiv()" class="btn btn-primary">
		
		
		<a href="allUserNotAvailableSlot"><input type="button" style="text-decoration: none" value="All Show" class="allShowButtons"/></a> 
		<a href="dayNotAvailableSlot"><input type="button" style="text-decoration: none" value="Day Work" class="allShowButtons"></a> 
		<a href="NotAvailableSlot"><input type="button" style="text-decoration: none;" value="User Work Week" class="allShowButtons"> </a>
	  </div>
	--><div id="login" class="blockdash_div">
		<s:if test="hasActionMessages()"> 
			<div id="common_message" class="message">
				<s:actionmessage id="common_message_text" theme="simple"/>
			</div>
		</s:if> 
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		

					
			<jsp:include page="/popupdialog/dialog/commonPopupDialog.jsp" /> 
			<jsp:include page="/dashboard/mobDashBoard.jsp" /> 				       
										        
										        
										       
			<br><br>
			<table width="100%" cellpadding="0" cellspacing="0" class="timer-table" id="tab1">
			
			<col class="tiemsl"/>
			<col width="60%"/>
			
			
				<tr>
					<th style="background-color: #DFD8D4"></th>
					<th id="wn0" style="text-align: center;"><s:property value="dayWeekName"/></th>
					
				</tr>
				
				<%  
				LoginInfo loginfo = LoginHelper.getLoginInfo(request);
				
				int ct=loginfo.getClinicStartTime(); 
				
				int clinicendtime = loginfo.getClinicEndTime();
				clinicendtime = clinicendtime - ct;
				clinicendtime = clinicendtime + 1;
				
				
				
					int countslot = 1;
					String weekName[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
					String tempCt = "";
					String tempMinute = "";
				%>
				
				<div id="tgOver1" class="tg-col-overlaywrapper" style="display: none;">
							<div class="tg-hourmarker tg-nowmarker" id="tgnowmarker" style="top: 0px;"> </div>
						</div>
				<%for(int i=1;i<=clinicendtime;i++){ %>
					<tr>
							<td class="ui-state-default ad"  valign="top" style="font-size: 14px; font-weight: bold;"><span class="timeset"><%=ct %>:00</span></td>
						
						
							<td valign="top" id="<%=countslot %>" >
								<%for(int k=0;k<=11;k++){ %>
										<%
											tempCt =  Integer.toString(ct);
											tempMinute = Integer.toString((5*k));
											if(ct <= 9) {
												tempCt = "0" + Integer.toString(ct);
											}
											if((5*k) <= 9) {
												tempMinute = "0" + Integer.toString((5*k));
											}
										%>
											
										
									<%if(k==6){ %>
										<div id="<%=(5*k) %>min<%=countslot %>"  class="yellow" title="<%=tempCt %>:<%=tempMinute %>" style="background-image: url('diarymanagement/img/line.png');background-repeat:repeat-x; " ></div>
									<%}else{ %>
										<div id="<%=(5*k) %>min<%=countslot %>"  class="yellow" title="<%=tempCt %>:<%=tempMinute %>" ></div>
									<% }%>
        							
        						<% }%>
								
							</td>
							<%countslot++; %>
						
						
						
						<%ct++; %>
					</tr>
					
				<% }%>
			</table>
				
</div>
</div>			
		
<script type="text/javascript">

$(document).ready(function(){
	openedb = 'opd';
	<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request);%>
	<%if(loginInfo.getUserType()==4){%>
	document.getElementById('diaryUser').value = <%=loginInfo.getId()%>
	
	document.getElementById('diaryUser').disabled = true;
	
	
	
	
	<%}%>
	<%if(loginInfo.getUserType()==5){%>
	
	document.getElementById('weekforescdiv1').style.display = 'none';
	document.getElementById('weekforescdiv2').style.display = 'none'; 
	document.getElementById('weekforescdiv3').style.display = 'none'; 
	document.getElementById('showdaybtn').style.display = 'none';
	
	
	<%}%>
	
	isotpconfirmd = <%=loginInfo.getIsotpconfirmd()%>
	mobSearch();
});

</script>

<div class="modal fade" id="loaderPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" style="width: 150px;">
			<div class="modal-content">
				
				<div class="modal-body" style="background-color: rgb(245, 178, 34)">
					<span style="font-weight:bold">Sending OTP....</span>
					
				</div>
				
			</div>
		</div>
	</div>	
		