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



<script type="text/javascript" src="diarymanagement/js/dayUsers.js"></script>
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
 			<jsp:include page="/dashboard/dayDashBoard.jsp" />  

					
								       
										        
										        
										       
			<br><br>
			<table width="100%" cellpadding="0" cellspacing="0" class="timer-table" id="tab1">
			
			<col class="tiemsl"/>
			<col width="60%"/>
			
			
				<tr>
					<th style="background-color: ">
						<input type="radio" id="rd0" name="rdapmt" 
						onclick="showpartapmt(this.id,0)"/> (All)
					</th>
					<th id="wn0" style="text-align: center;"><s:property value="dayWeekName"/></th>
					
				</tr>
				
				<% LoginInfo loginfo = LoginHelper.getLoginInfo(request);
				
				int ct=loginfo.getClinicStartTime(); 
					int countslot = 1;
					
					int clinicendtime = loginfo.getClinicEndTime();
					clinicendtime = clinicendtime - ct;
					clinicendtime = clinicendtime + 1;
					
					
					String weekName[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
					String tempCt = "";
					String tempMinute = "";
				%>
				
				<div id="tgOver1" class="tg-col-overlaywrapper" style="display: none;">
							<div class="tg-hourmarker tg-nowmarker" id="tgnowmarker" style="top: 0px;"> </div>
						</div>
				<%for(int i=1;i<=clinicendtime;i++){ %>
					<tr>
							<td class="ui-state-default"  valign="top" style="font-size: 14px; font-weight: bold;">
							<span class="timeset">
							<input type="radio" id="rd<%=ct %>" 
							name="rdapmt" onclick="showpartapmt(this.id,<%=ct %>)"/><%=ct %>:00</span></td>
						
						
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

<!-- Modal -->
   <div class="modal fade" id="loading" role="dialog">
    <div class="modal-dialog">
    
      Modal content
      <div class="modal-content">
       <!--  <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div> -->
        <div class="modal-body" >
          <img src="cicon/newloading.gif"  >
        </div>
        <div class="modal-footer">
          <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
        </div>
      </div>
      
    </div>
  </div> 

 
		
<script type="text/javascript">

$(document).ready(function(){
	openedb = 'opd';
	isnewopd = 0;
	<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	String ctime = DateTimeUtils.getUKCurrentDataTime("IST").split(" ")[1];
	String hh = ctime.split(":")[0];
	hh="0";
	if(session.getAttribute("sessionrddval")!=null){
		hh = (String)session.getAttribute("sessionrddval");
	}
	%>
	rdddval = '<%=hh%>';
	document.getElementById('rd<%=hh%>').checked =true;
	
	<%if(loginInfo.getUserType()==4){%>
	document.getElementById('diaryUser').value = <%=loginInfo.getId()%>
	
	document.getElementById('diaryUser').disabled = true;
	
	
	<%}%>
	
	getDaySearch();
});

</script>
		