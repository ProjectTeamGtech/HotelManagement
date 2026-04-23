<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.ArrayList"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import = "java.util.Date"%>



<script type="text/javascript" src="diarymanagement/js/completeApmt.js"></script>


<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>


<script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script>


<iframe id="background">
</iframe>

<div id="login_main" class="main_layoutdash_content">

<div id="login" class="blockdash_div">
		<s:if test="hasActionMessages()"> 
			<div id="common_message" class="message">
				<s:actionmessage id="common_message_text" theme="simple"/>
			</div>
		</s:if> 
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		
		<%@ include file="/popupdialog/dialog/commonPopupDialog.jsp" %>  
		<%@ include file="/dashboard/weekDashBoard.jsp" %>
		
			<br><br>
				<table width="100%" cellpadding="0" cellspacing="0" class="timer-table" id = "allusertable" style="table-layout: fixed;">
				<col width="5%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
			
			
				<tr>
					<th style="background-color: #DFD8D4"></th>
					<th id="wn0">Monday</th>
					<th id="wn1">Tuesday</th>
					<th id="wn2">Wednesday</th>
					<th id="wn3">Thursday</th>
					<th id="wn4">Friday</th>
					<th id="wn5">Saturday</th>
					<th id="wn6">Sunday</th>
					<th></th>
				</tr>
				<% int ct=8; 
					int countslot = 1;
					String weekName[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
					String tempCt = "";
					String tempMinute = "";
				%>
				
				
					<div id="tgOver1" class="tg-col-overlaywrapper" style="display: none;">
							<div class="tg-hourmarker tg-nowmarker" id="tgnowmarker" style="top: 0px;"> </div>
						</div>
				
				<%for(int i=1;i<=13;i++){ %>
					<tr>
					    <td class="ui-state-default"  valign="top" style="font-size: 14px; font-weight: bold;"><span style="float: right;font-size: 16px; "><%=ct %>:00</span></td>
						
						<% for(int j=0;j<=6;j++){%>
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
						<% }%>
						
					
						<%ct++; %>
					</tr>
					
				<% }%>
			</table>
				
</div>
</div>	


<script type="text/javascript">
window.onload = function(){
	getSearch();
}

</script>



<div  id="pop-up" style="display:none; z-index: 180; margin-top:-163px; margin-left:-130px; width: 320px; visibility: visible; left: 0px; top: 158px; display:none;" class="bubble">
<table class="bubble-table" cellspacing="0" cellpadding="0"><tbody><tr><td class="bubble-cell-side">
	<div id="tl1" class="bubble-corner"><div class="bubble-sprite bubble-tl"></div></div></td>
	<td class="bubble-cell-main"><div class="bubble-top"><a title="Close" href="javascript:void(0);" onclick="closeBuble();">X</a></div></td><td class="bubble-cell-side">
	<div id="tr1" class="bubble-corner"><div class="bubble-sprite bubble-tr"></div></div>  </td></tr>
	
	<tr><td class="bubble-mid" colspan="3"><div style="overflow-y:auto;height: 30px;" id="bubbleContent1"><div><div></div><div class="cb-root">
    
<table width="200" border="0" cellspacing="0" cellpadding="0">
<tr>
<td id="eventnote">
How r u ?
</td>
</tr>
</table>
    
&nbsp; </div></div></div></td></tr><tr><td><div id="bl1" class="bubble-corner"><div class="bubble-sprite bubble-bl"></div></div></td><td><div class="bubble-bottom"></div></td><td><div id="br1" class="bubble-corner"><div class="bubble-sprite bubble-br"></div></div></td></tr></tbody></table><!-- <div id="prong2" class="prong"><div class="bubble-sprite"></div></div></div> -->
      </td>
  </tr>
</table>
      
</div>			
		
				
</div>
</div>			
		
