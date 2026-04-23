<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>YUVICARE</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link href="dashboard/css/dailog.css" rel="stylesheet" type="text/css" />
    <link href="dashboard/css/calendar.css" rel="stylesheet" type="text/css" /> 
    <link href="dashboard/css/dp.css" rel="stylesheet" type="text/css" />   
    <link href="dashboard/css/alert.css" rel="stylesheet" type="text/css" /> 
    <link href="dashboard/css/main.css" rel="stylesheet" type="text/css" /> 
    <link href="common/css/responsive.css" rel="stylesheet" type="text/css" /> 
     <link rel="icon" href="dashboard/images/icon.ico">
     
      <%@ include file="/common/pages/pagination1.jsp"%> 

  <script>
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
	
	$(document).ready(function(){
		
		$("#commencing").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});
		
   });
	</script>
	<!--For Notification Css-->
	<style>
	
	.dropdown-menu.extended {
    	margin-left: 39px;
	}
	.notify-arrow {
    left: 206px;
	}
	.notify-arrow-green {
    border-color: transparent transparent #6699CC !important;
	}
	.dropdown-menu.extended li p {
    padding: 5px 5px 18px 10px !important;
	}
	.dropdown-menu.inbox li a .message {
    color: #555555 !important;
	}
	.dropdown-menu.inbox li a .subject .time {
	    font-size: 9px !important;
	    position: relative !important;
	    right: 0px !important;
	    float: right !important;
	}
	.numbermanage{
		margin-left: 10px;
	    margin-top: 7px;
	    position: absolute;
	}
	.badge {
    margin-top: -22px;
    position: absolute;
    margin-left: 2px;
    background-color: transparent;
}
	.appdashspell {
    margin-top: 10px;
    font-size: 13px;
    font-weight: bold;
}
.bellicasa{
    color: rgb(85, 85, 85);
}
.input-group .form-control {
    position: relative;
    z-index: 0;
    float: left;
    width: 70%;
    margin-bottom: 0;
}

.pdas{
    margin: 3px 0px 0.5px !important;
    font-size: 18px !important;
}
	</style>
</head>
<body>
   <div class="calennw">

		<%String display="block";
			if(session.getAttribute("pgview")!=null){
				String pgview = (String)session.getAttribute("pgview");
				if(pgview.equals("display")){
					display = "none";
				}
			}
		%>
      <div id="calhead" class="col-sm-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">          
                
            
        
            <div id="caltoolbar" class="col-sm-12 col-md-12 col-xs-12 col-sm-12 ctoolbar" style="display: <%=display %>">
            	
            		<div class="col-lg-2 col-md-2 hidden hidden-xs hidden-sm">
            		 <div class="appdashspell"><div class="ftitle"><i class="fa fa-dashboard"></i> OPD</div><!--
             <div id="loadingpannel" class="ptogtitle loadicon" style="display: none;">Loading data...</div> 
            --></div> 
            		</div>
				           		 
				           	<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">	 
								
								
																
								
             					<div  id="showmonthbtn" class="fbutton martop2das">

                					<div class="input-group">
                                        
                                          <s:textfield theme="simple" title="calender" name="commencing" cssClass="form-control calenderne"  aria-describedby="basic-addon1" cssStyle="width:85px !important;" id="commencing" onchange="getcaldate(this.value)"/>
                                      </div><!-- /input-group -->
                                  </div> 
                                 
                				
                			
                				 <div class="btnseparator"></div>
             					<div  id="showmonthbtn" class="fbutton martop2das">
                					<div class="input-group ">
                							 <s:if test="%{#userList != 'null'}" >
				 											<s:select cssStyle="display:none"  cssClass="form-control weekdash" title="Location" id="locationid" name="locationid" list="locationList" listKey="locationid" listValue="location" headerKey="0" theme="simple" headerValue="All"  onchange="setAppointmentByLocation(this.value)" />
														</s:if>
														
														  <s:select name="jobgroup" id="jobgroup" list="jobGroupList"
														    listKey="id" listValue="name" title="Select JobGroup"
														    headerKey="0" headerValue="Select JobGroup" onchange="setAppointmentByLocation(this.value)"
														    cssClass="form-control showToolTip" data-toggle="tooltip" cssStyle="width: 100%;">
														  </s:select>
										</span>
									</div>
								</div> 
								
							
								<div  id="showmonthbtn" class="fbutton hidden-xs hidden-sx">
            	 						<div>
            	 							<span  class="">
            	 								<s:form action="allUserNotAvailableSlot?actionType=dashboard" id="alluserfrm">
            	 									<s:hidden id="selectedLocation" name="selectedLocation"/>
            	 									<s:hidden name="caldate" id="caldates"/>
            	 									<s:hidden name="actionType" id="actionType"/>
            	 									<s:hidden name="selectedjobgroup" id="selectedjobgroup"/>
            	 									<input   id="gobtnid" type="submit"  class="btn btn-primary" value = "Go" style="padding:0 12px; display: none;"/>
            	 								</s:form>	
            	 								
            	 								<s:form action="calNotAvailableSlot?actionType=dashboard" id="calalluserfrm">
            	 									<s:hidden id="selectedLocation" name="selectedLocation"/>
            	 									<s:hidden name="caldate" id="caldatess"/>
            	 									<s:hidden name="actionType" id="actionType"/>
            	 									<s:hidden name="selectedjobgroup" id="selectedjobgroup"/>
            	 									<input   id="gobtnid" type="submit"  class="btn btn-primary" value = "Go" style="padding:0 12px; display: none;"/>
            	 								</s:form>	
               								</span>
               							</div>
									</div>
								 
            	 				
									<s:form action="getstaffsheduleprintdataNotAvailableSlot" id="printdashboardfrm" method="post" >
										<s:hidden name="printCommencing" id="printCommencing"> </s:hidden>
										<s:hidden name="printJobtitle" id="printJobtitle"> </s:hidden>
										<s:hidden name="printLocation" id="printLocation"> </s:hidden>
										<s:hidden name="printDiaryserid" id="printDiaryserid"> </s:hidden>
										<s:hidden name="diaryUser" id="diaryUser"></s:hidden>
										<div class="btnseparator hidden-xs hidden-sm"></div>
            	 						<div  id="showmonthbtn" class="fbutton hidden-xs hidden-sm">
            	 							<div>
            	 								<span  class="">
              										<%-- <s:submit id="printcomdashboard" value=" Preview " onclick="getPrintData()" theme="simple" cssclass="btn btn-primary" style="margin-top: -1px; padding: 2px;"></s:submit> --%>						
              										<%-- <s:submit id="printcomdashboard" value=" Report "  theme="simple" cssClass="btn" cssStyle="background-color: #eee;border-color: #ccc;color: black;"></s:submit> --%> 
                									<a href="#" class="btn btn-info" onclick="setdataofprintstaffshedule()">Report</a>
                								</span>
                							</div>
                						</div>
                					</s:form>
                					
                					
            	 								
	                						
	                					
	                			
	                		</div>
	                		
	                		
            				
            				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
            					<% LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
            						if(loginInfo.getUserType()==2){
            					%>
            					<div id="showtodaybtn" class="fbutton hidden-xs hidden-sx matop10res martop2das ">
                					<div class="sepratorspav">
                						
                						
                							<a id="tdyhref" href="calNotAvailableSlot?actionType=dashboard" title='Click to go back to today' class="btn hidden" style="background-color: #eee;border-color: #ccc;color: black;">
                								OPD Dashboard
                							</a>
                							<p class="pdas"><% String opendb = (String)session.getAttribute("openedb");%>
                							<%if(opendb.equals("opd")){ %>
                								OPD Dashboard
                							<% }else{%>
                								Scheduler Dashboard
                							<% }%></p>
                					
                						
                					 </div>
           	 					 </div>
           	 					 
           	 					
           	 					 <% }%>
								 <div id="showdaybtn" class="fbutton hidden hidden-xs hidden-sm matop10res">
                					<div>
                						<%--  <a href="dayNotAvailableSlot"><span title='Day' class="showdayview" style="color:black;">Day</span></a> --%> 
                						<a href="dayNotAvailableSlot" title="Day" class="btn" style="background-color: #eee;border-color: #ccc;color: black;"><i class="fa fa-calendar" ></i> Day</a>
                						
                					</div>
            					</div>
            					<div id="showdaybtn" class="fbutton hidden hidden-md hidden-lg matop10res martop2das">
                					<div>
                						<%--  <a href="dayNotAvailableSlot"><span title='Day' class="showdayview" style="color:black;">Day</span></a> --%> 
                						<a href="dayNotAvailableSlot" title="Day" class="btn" style="background-color: #eee;border-color: #ccc;color: black;"> 1D</a>
                						
                					</div>
            					</div>
           	 					 <div class="btnseparator hidden matop10res"></div>
								 <div id="showdaybtn" class="fbutton hidden hidden-xs hidden-sm matop10res">
                					<div>
                						<%-- <a href="NotAvailableSlot"><span title='Still In Work' class="showweekview" style="color:black;">Week</span></a> --%>
                						 <a href="NotAvailableSlot" title="Week" class="btn" style="background-color: #eee;border-color: #ccc;color: black;"><i class="fa fa-calendar"></i> Week</a>
                						
                					</div>
            					</div>
            					 <div id="showdaybtn" class="fbutton hidden hidden-md hidden-lg matop10res martop2das">
                					<div>
                						<%-- <a href="NotAvailableSlot"><span title='Still In Work' class="showweekview" style="color:black;">Week</span></a> --%>
                						<a href="NotAvailableSlot" title="Week" class="btn" style="background-color: #eee;border-color: #ccc;color: black;"> 7D</a>
                						
                					</div>
            					</div>
            					
            					
            					
            					<s:form action="allUserNotAvailableSlot" name="paginationForm" id="paginationForm">
										<input type="hidden" name="alluserpagesize" id="alluserpagesize" value="<s:property value="pagerecords" />">
										<s:hidden name="selectedjobgroup"/>
										<div class="btnseparator hidden-xs hidden-sx"></div>
            	 						<div  id="showmonthbtn" class="fbutton hidden-xs hidden-sx">
            	 						
                						
                						<input  name="pagination.page_number" id="page_number" class="pagination-textbox" <%if(!previous && !next) {%>readonly="readonly"<%}%>
			  							style="width: 10px;" maxlen="1" value="<%=pagination.getPage_number()%>" type="hidden" onclick="fnPagination(7,<%=pagination.getTotal_pages()%>);"/>
                						
										
            	 							<div  id="showmonthbtn" class="fbutton">
            	 								<%if(previous){%>
			  										<a href="#" onclick="fnPagination(4,<%=pagination.getTotal_pages()%>);">
			  											<!-- <input type="button" value="First"/> -->
			  											
			  										</a>
											  	<%}else{ %>
											  		<!-- <input type="button" value="First" disabled="disabled"/> -->
											  		
											  	<%} %>
			  								</div>
			  								
			  							
			  								
			  								<div  id="showmonthbtn hidden-xs hidden-sx" >
											  	<%if(previous){%>
											  	<a href="#" onclick="fnPagination(3,<%=pagination.getTotal_pages()%>);">
											  		<!-- <input type="button" value="Previous"/> -->
											  		<button class="btn btn-info" style="font-size: 16px; height: 30px; padding:0px; width: 35px; background-color: #eee;border-color: #ccc;color: black;"   title="Previous"><i class="fa fa-angle-double-left"></i></button>
											  	</a>
											  	<%}else{ %>
											  		<!-- <input type="button" value="Previous" disabled="disabled"/> -->
											  		<button class="btn btn-info" style="font-size: 16px; height: 30px; padding:0px; width: 35px; background-color: #eee;border-color: #ccc;color: black;"   title="Previous"><i class="fa fa-angle-double-left"></i></button>
											  	<%} %>
											 </div>
											
											 
										
											<div  id="showmonthbtn" >
												<% if(next){ %>
											  	<a href="#" onclick="fnPagination(1,<%=pagination.getTotal_pages()%>);">
											  		<!-- <input type="button" value="Next"/> -->
											  		<button class="btn btn-info" style="font-size: 16px; height: 30px; padding:0px; width: 35px; background-color: #eee;border-color: #ccc;color: black;"   title="Next">  <i class="fa fa-angle-double-right"></i></button>
											  	</a>
											  	<%}else{ %>
											  		<!-- <input type="button" value="Next" disabled="disabled"/> -->
											  		<button class="btn btn-info" style="font-size: 16px; height: 30px; padding:0px; width: 35px; background-color: #eee;border-color: #ccc;color: black;"   title="Next">  <i class="fa fa-angle-double-right"></i></button>
											  	<%} %>
											</div>
											
											
											
											<div  id="showmonthbtn" class="fbutton">
													<% if(next){ %>
												  	<a href="#" onclick="fnPagination(2,<%=pagination.getTotal_pages()%>);">
												  		<!-- <input type="button" value="Last"/> -->
												  	</a>
												  	<%}else{ %>
												  		<!-- <input type="button" value="Last" disabled="disabled"/> -->
												  	<%} %>
											
											</div>
											
											<div  id="showmonthbtn" class="fbutton">
												<div class="pagination-linkoff" style="" nowrap="nowrap">
			  										<s:select cssClass="form-control" onchange="fnPagination(5,0);" list="#{'5':'5','8':'8','12':'12','16':'16','20':'20'}" theme="simple" name="pagination.page_size" id="page_size" value="#request.pagination.page_size"/>
			  									</div>
											</div>
											
											
											<div  id="showmonthbtn" class="fbutton">
											
											</div>
                						
	                			</s:form>
            					
            					
            					<div class="btnseparator matop10res hidden"></div>
            					 <!-- Notification dropdown start-->
                    <div  class="dropdown nitific hidden">
                       
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-green"></div>
                            
                           <!--  <li>
                                <a href="index.html#">See all messages</a>
                            </li> -->
                        </ul>
                    </div>
                    <!-- Notification dropdown end -->	
                    <div style="display: " id="header_inbox_bar">
                     <ul class="nav-right list-inline hidden">
                        <li class="dropdown notifications notibell">

                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-bell fa-2x" style="color:#D9534F"></i>
                                <span class="badge"><s:property value="followupapmtsize"/></span>
                            </a>

                            <div class="dropdown-menu pull-right with-arrow panel panel-default animated littleFadeInLeft">

                                <div class="panel-heading">
                                    You have <strong><s:property value="followupapmtsize"/></strong> notifications
                                </div>

                                <ul class="list-group">
                                
                                <div id="Notifyscroll">
	                            	<s:iterator value="followupApmtList">
	                                <li class="list-group-item">
	                                    <a role="button" tabindex="0" class="media" href="#" onclick="openClientPrintPopup(<s:property value="clientId"/>)">
	                                        <span class="pull-left media-object media-icon photo">
                                               <img style="width:100%;" alt="avatar" src="popicons/avatar2.png"/>
                                            </span>
	                                        
	                                        <span class="media-body">
	                                            <span class="block"><s:property value="clientname"/></span>
	                                            <span class="text-muted"><s:property value="followupdate"/></span>
	                                        </span>
	                                        <span class="message">
	                                           <s:property value="advoice"/>
	                                        </span>
	                                    </a>
	                                </li>
	                              </s:iterator>
	                            </div>
                                
                                    
                                </ul>
                            </div>

                        </li>
                    </ul>
                    
                    </div>
                    		
                    
            				</div>
          
      		
            
            
          
            
         
            
            </div>
            
            <div id = "previewPopup" style="display: none" title="Appointment List">
            
            	<%@ include file="/diarymanagement/pages/printDataOfAllPractitioner.jsp" %>
            
            </div>
            
           <div class="clear"></div>
            </div>
      </div>    
    
    
    
    
    <script>
    	function getcaldate(date){
    		
    		document.getElementById('selectedLocation').value = document.getElementById('locationid').value;
    		document.getElementById('caldatess').value = date;
    		
    		document.getElementById('actionType').value = 'dashboard';
    		document.getElementById('calalluserfrm').submit();
    	}
    	
    	
    
    	
    	function setAppointmentByLocation(locationid){
    		
    		document.getElementById('selectedLocation').value = document.getElementById('locationid').value;;
    		document.getElementById('caldates').value = document.getElementById('commencing').value;
    		document.getElementById('selectedjobgroup').value = document.getElementById('jobgroup').value;
    		
    		document.getElementById('actionType').value = 'dashboard';
    		document.getElementById('alluserfrm').submit();
    	}
    	
    	function setdataofprintstaffshedule(){
    		document.getElementById('printCommencing').value = document.getElementById('commencing').value;;
    		document.getElementById('printdashboardfrm').submit();
    	}
    
    </script>
    
    
    
    
    
    
    
    
    <%--   <script src="common/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
     <script>
     $(function(){
    	    $('#Notifyscroll').slimScroll({
    	        height: '350px'
    	    });
    	});
     </script> --%>

    
</body>
</html>
