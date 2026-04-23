<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>Dashboard APM</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link href="dashboard/css/dailog.css" rel="stylesheet" type="text/css" />
    <link href="dashboard/css/calendar.css" rel="stylesheet" type="text/css" /> 
    <link href="dashboard/css/dp.css" rel="stylesheet" type="text/css" />   
    <link href="dashboard/css/alert.css" rel="stylesheet" type="text/css" /> 
    <link href="dashboard/css/main.css" rel="stylesheet" type="text/css" />
    <link href="common/css/Style.css" rel="stylesheet" type="text/css" /> 
    <link href="common/css/responsive.css" rel="stylesheet" type="text/css" />
     <link rel="icon" href="dashboard/images/icon.ico">
     <style>
     div.cHead {
    margin-top: 0px !important;
}
.appdashspell {
    margin-top: 10px;
    font-size: 13px;
    font-weight: bold;
}
     </style>
     
<script>

window.onload=function(){
	
	 var uhid=document.getElementById("mobhid").value;
	 document.getElementById("oriuhid").value=document.getElementById("mobhid").value;
	if(uhid==0){ 
	$( "#confirmuhid" ).modal( "show" );
	 }
	
	
	
	

};


	$(function() {
		
		$( "#previewPopup" ).dialog({
			autoOpen: false,
			resizable: true,
			height: 600,
			width: 750,
			modal: true,
			buttons: {
				"Print": function() {
					
					$( this ).dialog( "close" );
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
				
			}
		});
		
	});
	</script>
  
</head>
<body>
<%String uhid=(String)session.getAttribute("mobuseruid");%>
<input type="hidden" id="mobhid" value="<%=uhid%>"/>
<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)" >
			<%@ include file="/application.jsp" %>
			
		</s:if>
 <div class="row calennw">

		
      <div id="calhead" class="col-sm-12 col-md-12" style="padding-left:0px;padding-right:0px;margin-top: 0;">          
               
            
            <div id="caltoolbar" class="col-sm-12 col-md-12 ctoolbar">
            
            	<div class="col-lg-2 col-md-2 hidden-xs hidden-sm">
            		 <div class="appdashspell"><div class="ftitle"><i class="fa fa-dashboard"></i> OPD (Day)</div></div>
            		</div>
            	
            	<div class="col-lg-10 col-md-10 padommmleft">
            	<table width="100%" cellpadding="0" cellspacing="0">
            				<col width="70%"/>
            			<col width="30%"/>
            			<tr >
            				<td class="tdtable">
            					<%--  <div id="faddbtn" class="fbutton">
				                	<div>
				                		<span title='Click to Create New Event' class="addcal">
											<a href="#" onclick="showdiv()">Block</a>         
				                		</span>
				                	</div>
				           		 </div> --%>
				           		 
				           		  <%String currentDate = DateTimeUtils.getDateinSimpleFormate(new Date());
										String temp[] = currentDate.split(" ");
										String temp1[] = temp[0].split("-");
										String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
										
									%>
								<!-- <div class="btnseparator"></div> -->
             					<div  id="showmonthbtn" class="fbutton dayme" style="margin-left: 84px;">
                					<%-- <div>
                						<span  class=""> 
                							<s:textfield theme="simple" cssClass="form-control" cssStyle="height: 20px; padding: 3px" id="commencing"   name="commencing" onchange="getcaldate(this.value)"></s:textfield>
                						</span>
                					</div> --%>
                					<div class="input-group">
                                          <span class="input-group-addon hidden-xs hidden-sm" id="basic-addon1"><i class="fa fa-calendar"></i></span>
                                          <s:textfield theme="simple"  name="commencing" cssClass="form-control calenderne"  aria-describedby="basic-addon1" id="commencing" onchange="getcaldate(this.value)" style="width: 74px !important;"/>
                                      </div><!-- /input-group -->
                				</div>
                				
                				<%--  <div class="btnseparator"></div>
             					<div id="showtodaybtn" class="fbutton">
                					<div>
                						
                							<a href="todayNotAvailableSlot?action=day"><span title='Click to go back to today ' class="showtoday" style="color:black;">Today</span></a>
                						
                					 </div>
           	 					 </div>
								</div>  --%>
                				
                				<div class="btnseparator hidden-xs hidden-sm"></div>
             					<div  id="showmonthbtn" class="fbutton practday">
                					<div id="showmonthbtn" class="fbutton">
								<div class="input-group">
									<span class="input-group-addon hidden-xs hidden-sm" id="basic-addon1"><i
										class="fa fa-user"></i></span>
									<s:if test="%{#userList != 'null'}">
										<s:select cssClass="form-control pracwid"
											id="diaryUsersss" name="diaryUser" list="userList" listKey="id"
											listValue="diaryUser" headerKey="0" theme="simple"
											headerValue="Select Doctor"
											onchange="getPractitionerDayView()" />
											<!--  onchange="setSelectedDiaryUser(this.value)" -->
									</s:if>

								</div>
							</div>
							
						</div>
						
						 <div class="btnseparator hidden-xs hidden-sm"></div>
             					<div  id="showmonthbtn" class="fbutton mago">
                					<div class="input-group allsbso hidden">
                                          <span class="input-group-addon hidden-xs hidden-sm" id="basic-addon1"><i class="fa fa-map-marker"></i></span>
                							 <s:if test="%{#locationList != 'null'}" >
				 											<s:select onchange="getPractitionerDayView()"  cssClass="form-control mapmarw" id="locationid" name="locationid" list="locationList" listKey="locationid" listValue="location" headerKey="0" theme="simple" headerValue="All" />
														</s:if>
										</span>
									</div>
								</div> 
						
								  <div class="btnseparator hidden-xs hidden-sm"></div>
            	 					<div  id="showmonthbtn" class="fbutton">
            	 						<div>
            	 							<span  class="">
            	 								<s:form action="calNotAvailableSlot?actionType=mob" id="dayfrm">
            	 									<s:hidden name="caldate" id="caldates"/>
            	 									<s:hidden name="diaryUser" id="selecteduserid"/>
            	 									<s:hidden name="locationid" id="hdnlocation"/>
            	 									<s:hidden name="oriuhid" id="oriuhidfrm"/>
            	 									<input type="hidden" id="oriemail"/>
            	 									<input  id="gobtnid" type="button" onclick="getPractitionerDayView()"  class="btn gobn gobtn hidden" value = "Go"/>
            	 								</s:form>		
               								</span>
               							</div>
									</div>
									
									<s:form action="getAllPrintDataNotAvailableSlot?action=day" id="printdashboardfrm" method="post" 
              							onsubmit="return getPrintDataOfAll(this.target)" target="formtarget">
										<s:hidden name="printCommencing" id="printCommencing"> </s:hidden>
										<s:hidden name="printLocation" id="printLocation"> </s:hidden>
										<s:hidden name="printDiaryserid" id="printDiaryserid"> </s:hidden>
										
										<s:hidden name="locationid" id="locationid"></s:hidden>
										
									<div class="btnseparator hidden-xs hidden-sm"></div>
            	 						<div  id="showmonthbtn" class="fbutton hidden-xs hidden-sm">
            	 							<div>
            	 								<span  class="">
              										<%-- <s:submit id="printcomdashboard" value=" Preview " onclick="getPrintData()" theme="simple" cssclass="btn btn-primary" style="margin-top: -1px; padding: 2px;"></s:submit> --%>						
              										<%-- <s:submit id="printcomdashboard" value=" Preview "  theme="simple" cssClass="btn" cssStyle="background-color: #eee;border-color: #ccc;color: black;"></s:submit> --%>
                								</span>
                							</div>
                						</div>
                						
                						</s:form>
                			</td>
            				
            				<td align="right daypad">
            					<% LoginInfo loginInfos = LoginHelper.getLoginInfo(request);
            						if(loginInfos.getUserType()==2){
            					%>
            					<div id="showtodaybtn" class="fbutton">
                					<div>
                							<%-- <a href="todayNotAvailableSlot?action=dashboard"><span title='Click to go back to today ' class="showtoday" style="color:black;">Today</span></a> --%>
                							<a href="calNotAvailableSlot?actionType=dashboard" title='Click to go back to today' class="btn hidden-xs hidden-sm" style="background-color: #eee;border-color: #ccc;color: black;" ><i class="fa fa-calendar"></i> Today</a>
                						
                					 </div>
           	 					 </div>
           	 					 
           	 					 <div class="btnseparator hidden-xs hidden-sm"></div>
           	 					 <%} %>
								 <div id="showdaybtn" class="fbutton  hidden-xs hidden-sm">
                					<div>
                						<%-- <a href="dayNotAvailableSlot"><span title='Day' class="showdayview" style="color:black">Day</span></a> --%>
                						<a href="dayNotAvailableSlot" title='Day' class="btn" style="background-color: #eee;border-color: #ccc;color: black;"><i class="fa fa-calendar"></i> Day</a>
                					</div>
            					</div>
            					<div id="showdaybtn" class="fbutton hidden-md hidden-lg">
                					<div>
                						<%-- <a href="dayNotAvailableSlot"><span title='Day' class="showdayview" style="color:black">Day</span></a> --%>
                						<!-- <a href="dayNotAvailableSlot" title='Day' class="btn da1" style="background-color: #eee;border-color: #ccc;color: black;">1D</a> -->
                					</div>
            					</div>
           	 					 <div class="btnseparator hidden-xs hidden-sm" id="weekforescdiv1"></div>
								 <div id="weekforescdiv2" class="fbutton hidden-xs hidden-sm" >
                					<div>
                						<%-- <a href="NotAvailableSlot"><span title='Week' class="showweekview" style="color:black">Week</span></a> --%>
                						<a href="NotAvailableSlot" title='Week' class="btn" style="background-color: #eee;border-color: #ccc;color: black;"><i class="fa fa-calendar"></i> Week </a>
                					</div>
            					</div>
            					<div id="weekforescdiv3" class="fbutton hidden-md hidden-lg">
                					<div>
                						<%-- <a href="NotAvailableSlot"><span title='Week' class="showweekview" style="color:black">Week</span></a> --%>
                					<!-- 	<a href="NotAvailableSlot" title='Week' class="btn da1" style="background-color: #eee;border-color: #ccc;color: black;">7D</a> -->
                					</div>
            					</div>
            					<%--  <div class="btnseparator"></div>
								 <div id="showdaybtn" class="fbutton">
                					<div>
                						<span title='Month' class="showmonthview">Month</span>
                					</div>
            					</div> --%>
            				</td>
            			</tr>
            		</table>
            	</div>
            		
         <input type="hidden" id="oriuhid"/>
      		<div class="modal fade" id="confirmuhid" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Confirmation</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<input type="hidden" name="cancelinv" value="1">
						<div class="form-group">
							<label>Enter Mobile<small> (If Already Registered)</small></label>
							<input type="text" class="form-control"  id="entermob" onchange="checkuhidexistornot()"/>
							
						</div>
 
						 
						
						<div class="form-group">
							<button type="button" class="btn btn-primary" onclick="confirm()" id="confirmmob" style="float: left;" disabled="disabled">Confirm</button>
						<button type="button" class="btn btn-primary" onclick="firstconfirmotp()" id="newregpatient" style="float: right;" >Registration</button>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>
            <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
            
            <s:form action="NotAvailableSlot" id="weekfrom">
            	<input type="hidden" name="selectedCommencing" id="selectedCommencing"/>
            	<input type="hidden" name="selecteduserid" id="selecteduserid" />
            </s:form>
            <s:form action="allUserNotAvailableSlot" id="alluserfrm">
            	<input type="hidden" name="selectedCommencing" id="selectedCommencing"/>
            	<input type="hidden" name="selecteduserid" id="selecteduserid" />
            </s:form>
           
            
            </div>
             <div id = "previewPopup" style="display: none" title="Appointment List">
            
            	<%@ include file="/diarymanagement/pages/practionerPrintData.jsp" %>
            
            </div>
           <div class="clear"></div>
            </div>
      </div>    
    <input type="hidden" id="clinicid" value="<%=loginInfos.getClinicUserid()%>"/>
    <input type="hidden" id="linkaddress" value="<%=loginInfos.getLinkaddress()%>"/>
    
   
    
    
    
    
    
     <script>
    	function getcaldate(date){
    		document.getElementById('caldates').value = date;
    		document.getElementById('selecteduserid').value = document.getElementById('diaryUser').value;
    		
    	}
    	
    	
    	function setSelectedDiaryUser(id){
    		
    		document.getElementById('selecteduserid').value = id;
    		document.getElementById('dayfrm').submit();
    		
    	}
    	
    	function getPractitionerDayView(){
    		document.getElementById('selecteduserid').value = document.getElementById('diaryUsersss').value;
    		document.getElementById('hdnlocation').value = document.getElementById('locationid').value;
    		document.getElementById("oriuhidfrm").value=document.getElementById("oriuhid").value;	
    		var diary=document.getElementById('diaryUsersss').value;
    		var clinicid=document.getElementById('clinicid').value;
    		document.getElementById('dayfrm').submit();
    		/* var url="http://localhost:8080/HISTEST/Pureseva?title=&firstname=&lastname=&email=&clinicid="+clinicid+"&mob=&date=&diaryuserid="+diary+"&gender=&dob=&uhid="+uhid+"";
			window.location=url; */
    	}
    	
    	
    function newpatient() {
    	$( "#confirmuhid" ).modal( "hide" );
    	$( "#puresevaclientdetailsdiv" ).modal( "show" );
	}
    </script>
    
    
    
    
    
    
    
    
      
     

    
</body>
</html>
