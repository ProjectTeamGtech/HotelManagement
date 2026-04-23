<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>Dashboard APM</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<link href="dashboard/css/dailog.css" rel="stylesheet" type="text/css" />
	<link href="dashboard/css/calendar.css" rel="stylesheet"
		type="text/css" />
	<link href="dashboard/css/dp.css" rel="stylesheet" type="text/css" />
	<link href="dashboard/css/alert.css" rel="stylesheet" type="text/css" />
	<link href="common/css/Style.css" rel="stylesheet" type="text/css" /> 
	<link href="dashboard/css/main.css" rel="stylesheet" type="text/css" />
	<link href="common/css/responsive.css" rel="stylesheet" type="text/css" />
	<link rel="icon" href="dashboard/images/icon.ico">
	 <style>
     .appdashspell {
    margin-top: 10px;
    font-size: 13px;
    font-weight: bold;
}
     </style>
		<script>
			$(function() {

				$("#previewPopup").dialog({
					autoOpen : false,
					resizable : true,
					height : 600,
					width : 750,
					modal : true,
					buttons : {
						"Print" : function() {

							$(this).dialog("close");
						},
						Cancel : function() {
							$(this).dialog("close");
						}

					}
				});

			});
		</script>
</head>
<body>
	  <div class="calennw">

		
      <div id="calhead" class="col-sm-12 col-md-12" style="padding-left:0px;padding-right:0px;">          
               
            
            <div id="caltoolbar" class="col-sm-12 col-md-12 ctoolbar">
					<div class="col-lg-2 col-md-2 hidden hidden-xs hidden-sm">
            		 <div class="appdashspell"><div class="ftitle"><i class="fa fa-dashboard"></i> OPD (Week)</div></div>
            		</div>

					<div class="col-lg-10 col-md-10">
					<table width="100%" cellpadding="0" cellspacing="0">
					<col width="70%" />
					<col width="30%" />
					<tr>
						<td class="weekwidt">
							<%--  <div id="faddbtn" class="fbutton">
				                	<div>
				                		<span title='Click to Create New Event' class="addcal">
											<a href="#" onclick="showdiv()">Block</a>         
				                		</span>
				                	</div>
				           		 </div>
				           		  --%> <%
									 	String currentDate = DateTimeUtils
									 			.getDateinSimpleFormate(new Date());
									 	String temp[] = currentDate.split(" ");
									 	String temp1[] = temp[0].split("-");
									 	String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
									 %> <!-- <div class="btnseparator"></div> -->
							<div id="showmonthbtn" class="fbutton dayme martop2das">
								<%-- <div>
                						<span  class=""> 
                							<s:textfield theme="simple" cssClass="form-control" cssStyle="height:20px" id="commencing"   name="commencing" onchange="getcaldate(this.value)"></s:textfield>
                						</span>
                					</div> --%>

								<div class="input-group ">
									<span class="input-group-addon " id="basic-addon1"><i
										class="fa fa-calendar"></i></span>
									<s:textfield theme="simple" name="commencing"
										cssClass="form-control calenderne" aria-describedby="basic-addon1"
										 id="commencing"
										onchange="getcaldate(this.value)" />
								</div>
								<!-- /input-group -->
							</div> <%--  <div class="btnseparator"></div>
             					<div id="showtodaybtn" class="fbutton">
                					<div>
                						
                							<a href="todayNotAvailableSlot?action=week"><span title='Click to go back to today ' class="showtoday" style="color:black;">Today</span></a>
                						
                					 </div>
           	 					 </div>
								</div>  --%>

							<div class="btnseparator hidden-xs hidden-sm"></div>
							<div id="showmonthbtn" class="fbutton hidden-xs hidden-sm martop2das">
								<div class="input-group">
									<span class="input-group-addon " id="basic-addon1"><i
										class="fa fa-user"></i></span>
									<s:if test="%{#userList != 'null'}">
										<s:select cssStyle="width:182px;" cssClass="form-control weekdash"
											id="diaryUser" name="diaryUser" list="userList" listKey="id"
											listValue="diaryUser" headerKey="0" theme="simple"
											headerValue="Select Practitioner"
											onchange="setSelectedDiaryUser()" />
									</s:if>

								</div>
							</div>
							
							 <div class="btnseparator hidden-xs hidden-sm"></div>
             					<div  id="showmonthbtn" class="fbutton mago">
                					<div class="input-group ">
                                          <span class="input-group-addon " id="basic-addon1"><i class="fa fa-map-marker"></i></span>
                							 <s:if test="%{#locationList != 'null'}" >
				 											<s:select onchange="setSelectedDiaryUser()"  cssClass="form-control mapmarw"  id="locationid" name="locationid" list="locationList" listKey="locationid" listValue="location" headerKey="0" theme="simple" headerValue="All" />
														</s:if>
										</span>
									</div>
								</div> 
							
							<div class="btnseparator hidden-xs hidden-sm"></div>
							<div id="showmonthbtn" class="fbutton martop2das">
								<div>
									<span class=""> <s:form
											action="calNotAvailableSlot?actionType=week" id="weekfrm">
											<s:hidden name="caldate" id="caldates" />
											<s:hidden name="selecteduserid" id="selecteduserid" />
											<s:hidden name="locationid" id="hdnlocation"/>
											<input id="gobtnid" type="button" class="btn gobn"
												value="Go" onclick="setSelectedDiaryUser()" />
										</s:form>
									</span>
								</div>
							</div> <s:form action="getAllPrintDataNotAvailableSlot?action=week"
								id="printdashboardfrm" method="post"
								onsubmit="return getPrintDataOfAll(this.target)"
								target="formtarget">
								<s:hidden name="printCommencing" id="printCommencing">
								</s:hidden>
								<s:hidden name="printLocation" id="printLocation">
								</s:hidden>
								<s:hidden name="printDiaryserid" id="printDiaryserid">
								</s:hidden>

								<s:hidden name="locationid" id="locationid"></s:hidden>

								<div class="btnseparator hidden-xs hidden-sm"></div>
								<div id="showmonthbtn" class="fbutton hidden-xs hidden-sm">
									<div>
										<span class=""> <%-- <s:submit id="printcomdashboard" value=" Preview " onclick="getWeekPrintData()" theme="simple" cssclass="btn btn-primary" ></s:submit> --%>
											<s:submit id="printcomdashboard" value=" Preview "
												theme="simple" cssClass="btn" cssStyle="background-color: #eee;border-color: #ccc;color: black;"></s:submit>
										</span>
									</div>
								</div>

							</s:form>
							<div class="btnseparator hidden-xs hidden-sm"></div>
							<% LoginInfo loginInfos = LoginHelper.getLoginInfo(request);
            						if(loginInfos.getUserType()==2){
            					%>
							<div id="showtodaybtn" class="fbutton martop2das">
								<div>




									<%-- <a href="todayNotAvailableSlot?action=dashboard"><span title='Click to go back to today ' class="showtoday" style="color:black;">Today</span></a> --%>
									<a href="calNotAvailableSlot?actionType=dashboard"
										title='Click to go back to today' class="btn hidden-xs hidden-sm" style="background-color: #eee;border-color: #ccc;color: black;">
										OPD Dashboard</a>



								</div>
							</div>

							<div class="btnseparator hidden-xs hidden-sm"></div>
						<%} %>
							 <div id="showdaybtn" class="fbutton  hidden-xs hidden-sm martop2das">
                					<div>
                						<%-- <a href="dayNotAvailableSlot"><span title='Day' class="showdayview" style="color:black">Day</span></a> --%>
                						<a href="dayNotAvailableSlot" title='Day' class="btn" style="background-color: #eee;border-color: #ccc;color: black;"><i class="fa fa-calendar"></i> Day</a>
                					</div>
            					</div>
            					<div id="showdaybtn" class="fbutton hidden-md hidden-lg">
                					<div>
                						<%-- <a href="dayNotAvailableSlot"><span title='Day' class="showdayview" style="color:black">Day</span></a> --%>
                						<a href="dayNotAvailableSlot" title='Day' class="btn da1" style="background-color: #eee;border-color: #ccc;color: black;">1D</a>
                					</div>
            					</div>
           	 					 <div class="btnseparator hidden hidden-xs hidden-sm"></div>
								 <div id="showdaybtn" class="fbutton hidden hidden-xs hidden-sm">
                					<div>
                						<%-- <a href="NotAvailableSlot"><span title='Week' class="showweekview" style="color:black">Week</span></a> --%>
                						<a href="NotAvailableSlot" title='Week' class="btn hidden" style="background-color: #eee;border-color: #ccc;color: black;"><i class="fa fa-calendar"></i> Week </a>
                					</div>
            					</div>
            					<div id="showdaybtn" class="fbutton hidden-md hidden-lg">
                					<div>
                						<%-- <a href="NotAvailableSlot"><span title='Week' class="showweekview" style="color:black">Week</span></a> --%>
                						<a href="NotAvailableSlot" title='Week' class="btn da1" style="background-color: #eee;border-color: #ccc;color: black;">7D</a>
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
				





				<s:form action="NotAvailableSlot" id="weekfrom">
					<input type="hidden" name="selectedCommencing"
						id="selectedCommencing" />
					<input type="hidden" name="selecteduserid" id="selecteduserid" />
				</s:form>
				<s:form action="allUserNotAvailableSlot" id="alluserfrm">
					<input type="hidden" name="selectedCommencing"
						id="selectedCommencing" />
					<input type="hidden" name="selecteduserid" id="selecteduserid" />
				</s:form>


			</div>
			<div id="previewPopup" style="display: none" title="Appointment List">

				<%@ include
					file="/diarymanagement/pages/practitionerWeekPrintData.jsp"%>

			</div>
			<div class="clear"></div>
		</div>
	</div>






	<script>
		function getcaldate(date) {
			document.getElementById('caldates').value = date;
			document.getElementById('selecteduserid').value = document
					.getElementById('diaryUser').value;

		}

		function setSelectedDiaryUser() {
			document.getElementById('caldates').value = document
					.getElementById('commencing').value;
			document.getElementById('selecteduserid').value = document.getElementById('diaryUser').value;
			document.getElementById('hdnlocation').value = document.getElementById('locationid').value;
			 document.getElementById('weekfrm').submit();
		}
	</script>










</body>
</html>
