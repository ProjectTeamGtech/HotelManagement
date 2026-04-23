<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.ArrayList"%>
<%@page import = "java.util.Date"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.DiaryManagement.eu.entity.DiaryManagement"%>



<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="common/js/pagination.js"></script>

<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />

 	<!-- <link href="eventalertpopup/css/dailog.css" rel="stylesheet" type="text/css" />
    <link href="eventalertpopup/css/calendar.css" rel="stylesheet" type="text/css" /> 
    <link href="eventalertpopup/css/dp.css" rel="stylesheet" type="text/css" />   
    <link href="eventalertpopup/css/alert.css" rel="stylesheet" type="text/css" /> 
    <link href="eventalertpopup/css/main.css" rel="stylesheet" type="text/css" /> 
     <link rel="icon" href="eventalertpopup/images/icon.ico"> -->

<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>

<script>

var style = document.createElement('style');
style.type = 'text/css';
style.innerHTML = '.cssClass1 { min-height:20px; cursor:pointer; }';
document.getElementsByTagName('head')[0].appendChild(style);
var time=0; 



</script>


<%--  <script src="diarymanagement/js/freezealluserTable.js"></script> --%>

<script type="text/javascript" src="diarymanagement/js/alldiaryuser.js"></script>

<script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script> 


<style>
.new .new{
width:50%;
z-index:3;
}
</style>

<div id="login_main" class="main_layoutdash_content">

	

	<!--<h2 class="title" >Appointment Dairy</h2>
	
		<div class="menu">
		<input type="submit" value="New" onclick="showPopWin('newAppoinmentNotAvailableSlot', 500, 400, null);" disabled="disabled" class="btn btn-primary">
		<input type="submit" value="Modify" onclick="$(this).MessageBox()" disabled="disabled" class="btn btn-primary">
		<input type="submit" value="Delete" disabled="disabled" class="btn btn-primary">
		
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
		
	
		
		
<!-- popup div -->	


	
			<jsp:include page="/popupdialog/dialog/commonPopupDialog.jsp" /> 
			<jsp:include page="/dashboard/Dashboard.jsp" /> 
		
			<!--		
		
		
		
		
		
		
		<div class="form_elements" >	
			
								
			<div class="dairyuser">
				Date: <s:textfield id="commencing" cssClass="date-pick dp-applied"  name="commencing" ></s:textfield>
				<input type="button" value="Show" onclick="showAvailiability()" class="btn btn-primary">
				<input type="button" value="Preview" onclick="getPrintDataOfAll()" class="btn btn-primary"/>
					
			</div>
			
			
			
			
			-->
			
					
			
			<br><br>
			
			
			
			<script type="text/javascript">
			
			function showAvailiability(){
				//set wraperdiv element
				  if(editAppointId!=0){
						getWraperDivChildren(wraperDivId);
				 } 
				
				jQuery('.new').children().unwrap();
				 //resetTableTdElement();
				 
				actionType = 1;
				
					<%ArrayList<DiaryManagement>list = (ArrayList<DiaryManagement>)session.getAttribute("allDiaryUser"); int col = 1; int length = 13*list.size();%>
					var length = <%=length%>
					
				 	/*  for(i=1;i<=length;i++){
						$(document.getElementById(i)).css('border-bottom', '1px solid #DFD8D4');
						document.getElementById(i).ondblclick = '';
						for(j=0;j<=11;j++){
							count = 5*j;
							$(document.getElementById(count+'min'+i)).css('background-color', 'white');
							document.getElementById(count+'min'+i).innerHTML = '';
							document.getElementById(count+'min'+i).ondblclick = '';
							document.getElementById(count+'min'+i).onclick = '';
							//$(document.getElementById(count+'min'+i)).css('border-bottom', '');
							count = parseInt(count)+1;
						}
					}   
				 	  */
				 	 
				 	 var location = document.getElementById('locationid').value;
					
					<%if(list.size()!=0){%>
					
					
					<%for(DiaryManagement diaryManagement : list){%>
							setjsonDataAllUser(<%=diaryManagement.getId()%>,document.getElementById('commencing').value,<%=col%>,<%=list.size()%>,location);
						<%col++;}%>
						
					<%}%>
				}
			
			
			
			window.onload = function () {
				//alert(document.getElementById('locationid').value);
				<%String odb = (String)session.getAttribute("openedb");%>
				openedb = '<%=odb%>';
				if(openedb==''){
					openedb = 'otdb';
				}
				if(openedb=='dsplay'){
					setTimeout(function() {
						
						window.location.reload();
					}, 15000);
				}
				
				var location = document.getElementById('locationid').value;
				
				actionType = 1;
				<%if(list.size()!=0){ int cols=1;%>
					<%for(DiaryManagement diaryManagement : list){%>
						setjsonDataAllUser(<%=diaryManagement.getId()%>,document.getElementById('commencing').value,<%=cols%>,<%=list.size()%>,location);
					<%cols++;}%>
				
				<%}%>
				
				 
				
				document.getElementById('alluserpagesize').value = 5;
				
			/* 	 $('#allusertable').freezeTableColumns({
				        width:       1500,   // required
				        height:      600,   // required
				        numFrozen:   1,     // optional
				        frozenWidth: 100,   // optional
				        clearWidths: false,  // optional
				      }); */
			
			}
			
			
			
			
			
			
			function resetTableTdElement(){
				var mtempCt = '';
				var mct=8;
				<% for(int i=1;i<=13;i++){ %>
					<%for(int j=i;j<=7;j++){%>
						document.getElementById(j).innerHTML='';
						mtempCt = mct;
					
						
						
						if(mct <= 9) {
							mtempCt = "0" + mct;
						}
						
						var str = '';
						for(k=0;k<=11;k++){
							var min = 5*k;
							if(min<=9){
								min = '0'+min;
							}
							if(k==6){
								str = str+'<div id="'+(5*i)+'min'+j+'" class="cssClass1" title="'+mtempCt+':'+min+'" class="yellow" style="background-image: url("diarymanagement/img/line.png");background-repeat:repeat-x; "></div>';
							}else{
								str = str+'<div id="'+(5*i)+'min'+j+'" class="cssClass1" title="'+mtempCt+':'+min+'" class="yellow"></div>';
							}
							document.getElementById(j).innerHTML=str;
						}
					
					<%}%>
					mct++;
				<%}%>
				
			}
			
			
			
					
			</script>
			
		
			<table width="100%" cellpadding="0" cellspacing="0" class="timer-table" id = "allusertable" style="table-layout: fixed;">
			<s:if test="userList.size==1">
				<col width="2%"/>
			</s:if>
			<s:elseif test="userList.size==2">
				<col width="4%"/>
			</s:elseif>
			<s:elseif test="userList.size==3">
				<col width="6%"/>
			</s:elseif>
			<s:elseif test="userList.size==4">
				<col width="8%"/>
			</s:elseif>
			<s:else>
				<col width="15%" class="mobadm"/>
			</s:else>
				
				<s:iterator value="userList" status="rowstatus"> 
					<col width="40%"/>
				</s:iterator>
				
				
				
				<tr>
					<th style="background-color: RGBA(234, 234, 234, 0.55);"></th>
					<s:iterator value="userList" status="rowstatus">
						<input type="hidden" name="diaryUser" id="diaryUser">
						<th id="thtestid" class="todayt"><s:property value="diaryUser"/>
						<%--  &nbsp; <span title="<s:property value="locationString"/>" style="color:gray;opacity:0.6;"><s:property value="locationString"/></span></th> --%>
						<s:iterator value="multiLicationList">
							<span  title="<s:property value="locationname"/>" style="color:gray;opacity:0.6;display:none;"><s:property value="locationname"/></span><br>
						</s:iterator>
						</th>
					</s:iterator>
					
				</tr>
				
				<% LoginInfo loginfo = LoginHelper.getLoginInfo(request);
				
				int ct=loginfo.getClinicStartTime(); 
					int countslot = 1;
					
					int clinicendtime = loginfo.getClinicEndTime();
					
					String openedb = (String) session.getAttribute("openedb");
					if(openedb.equals("dsplay")){
						ct = 7;
						clinicendtime = 10;
						
						String ctime = DateTimeUtils.getUKCurrentDataTime(loginfo.getTimeZone()).split(" ")[1];
						String hh = ctime.split(":")[0];
						if(Integer.parseInt(hh)>=11){
							ct = 10;
							clinicendtime = 13;
						}
						
						if(Integer.parseInt(hh)>=13){
							
							ct = 12;
							clinicendtime = 15;
						}
						
						if(Integer.parseInt(hh)>=14){
							
							ct = 14;
							clinicendtime = 17;
						}
						
						if(Integer.parseInt(hh)>=16){
							
							ct = 16;
							clinicendtime = 19;
						}
						
						if(Integer.parseInt(hh)>=18){
							
							ct = 18;
							clinicendtime = 21;
						}
						
						if(Integer.parseInt(hh)>=20){
							
							ct = 20;
							clinicendtime = 23;
						}
					}
					clinicendtime = clinicendtime - ct;
					clinicendtime = clinicendtime + 1;
					
					int size = (Integer)session.getAttribute("userListSize");
					String tempCt = "";
					String tempMinute = "";
				%>
				
				
						<div id="tgOver1" class="tg-col-overlaywrapper">
							<div class="tg-hourmarker tg-nowmarker" id="tgnowmarker" style="top: 0px;"> </div>
						</div>
				
				
				<%for(int i=1;i<=clinicendtime;i++){ %>
				
					<tr>
					
						<% String xct = "";if(ct<=9) {xct = "0"+ Integer.toString(ct);} else{xct = Integer.toString(ct); }%>
						
						
					
						<td class="ui-state-default"  valign="top" style="font-weight: bold;text-align: center;"><span class="timeset"><%=xct %>:00</span>
							<%-- <span style="float: right;font-size: 10px; margin-top: 6px; "><%=xct %>:30</span> --%>
						</td>
								
						<s:iterator value="userList" status="rowstatus">
							<td  valign="top" id="<%=countslot %>"  title="<s:property value="id"/>">
								
							<!-- <div style="width: 60%; float:left">  -->
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
								 <!-- </div>
								 <div style="width: 20%; float:right;">
								  hi
								</div> 
								 <div style="width: 20%; float:right;">
								  hi
								</div>  -->
							</td>
						
							<%countslot++; %>
						</s:iterator>
						
						
						<%ct++; %>
					
					</tr>
					
				<% }%>
				
			</table>
		
		
	
<%--  <s:form action="allUserNotAvailableSlot" name="paginationForm" id="paginationForm">
	<input type="hidden" name="alluserpagesize" id="alluserpagesize" value="<s:property value="pagerecords" />">
	<div class="row">
		<div class="col-lg-4 col-md-4">
			Showing all <label class="text-info">(<s:property
					value="pagerecords" /> of <s:property value="totalRecords" />
				Records)
			</label>
		</div>

	</div>
</s:form>   --%>


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


		
		
