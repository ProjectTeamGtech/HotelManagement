<!doctype html>

<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>

<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<style>
.newbtn{
position: absolute !important;
    font-size: 14px !important;
    color: #fff !important;
    background-color: #15536E !important;
    border-color: #15536E !important;
    margin-right: -203px !important;
    border-radius: 0.50 srem; 
    padding-top: 1px;
}
</style>                
                    <!--  <div class="col-lg-3 col-md-3">
                                    <div class="search-box-1 pull-left">
                                        <form action="#">
                                           
                                            <input type="text" name="search" placeholder="Search patients, appointments, help or anything else " required class="opd-search" style="width: 158%">
                                            <a href="" class="btn" style="position: absolute !important;
                                            right: 21px;top: 8px;font-size: 14px !important;color: #fff !important;background-color: #15536E !important;border-color: #15536E !important;margin-right: -203px !important;border-radius: 1.25rem ">Search</a>
                                        </form>
                                    </div>
                                </div>
                                 -->
                               <!--  <div class="col-lg-3 col-md-3">
                                    <a href="" class="btn btn-light"><img src="manasopd/assets/images/Reportss_Icon.svg" style="padding-right: 15px;">Get Reports</a>
                                </div> -->
                                
                           <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                             
                             <div class="col-lg-1 col-md-1 col-xs-12 col-sm-12">
		                             <label style="font-size: medium;font-weight: 900;">Date</label>
		                              <s:textfield  name="commencing" readonly="true" cssClass="form-control" id="commencing" onchange="showdisplaynewopd()" cssStyle="text-align:center;width: 116%;"/>
                             </div> 
                             
                              <%if(loginInfo.isOpd_recep_sp_list()){ %>
                               <div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
                             	<label style="font-size: medium;font-weight: 900;">Select Department</label><br>
                             
				                      
				                 <%if(loginInfo.getActionType().equals("doctorday")){ %>
				                        <s:select id="diciplineName123" disabled="true" name="diciplineName" list="disciplineList" listKey="id" listValue="discipline"
										headerKey="0" headerValue="Select Speciality"
										title="Select Discipline" 
										cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"  theme="simple" onchange="showselecteddr(this.value)"/>
								<%}else{ %>
								 <s:select id="diciplineName123"  name="diciplineName" list="disciplineList" listKey="id" listValue="discipline"
										headerKey="0" headerValue="Select Speciality"
										title="Select Discipline" 
										cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"  theme="simple" onchange="showselecteddr(this.value)"/>
								
								<%} %>
					
						</div>
						<%} %>
                               <div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
                               
                             <%if(loginInfo.getClinicid1().equals("bams") || loginInfo.getClinicid1().equals("bams1") ){ %>
                                 <label style="font-size: medium;font-weight: 900;">Select Department</label><br>
                        <s:select id="diciplineName123" name="diciplineName" list="disciplineList" listKey="id" listValue="discipline"
						headerKey="0" headerValue="Select Speciality"
						title="Select Discipline" 
						cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"  theme="simple" onchange="showselecteddr(this.value)"/><br><br><br>
                       <%} %>
                               
                        		<label style="font-size: medium;font-weight: 900;">Select Doctor</label>
                        		<div id="selecteddrwithsp" class="notranslate">
                        	<%if(loginInfo.getActionType().equals("doctorday")){ %>
                       				<s:select cssClass="form-control showToolTip chosen-select"
											id="diaryUsersss" disabled="true" name="diaryUser" list="userList" listKey="id"
											listValue="diaryUser" headerKey="0" theme="simple"
											headerValue="Select Doctor"
											onchange="showdisplaynewopd()"/>
											
							<%}else{ %>
										<s:select cssClass="form-control showToolTip chosen-select"
											id="diaryUsersss" name="diaryUser" list="userList" listKey="id"
											listValue="diaryUser" headerKey="0" theme="simple"
											headerValue="Select Doctor"
											onchange="showdisplaynewopd()"/>
							<%} %>			
							
								</div>
                        			
                       	 	 </div>
                             <div class="col-lg-1 col-md-1 col-xs-12 col-sm-12" id='setdiarydiv' style="display: none;">
                             
                             <!-- <input type="button" value="Set Diary" id="setbtn" class="btn btn-info active" onclick="savediary(1)" style="margin-bottom: 5px;"> -->
                             <%if(!loginInfo.isBalgopal()){ %>
                             <input type="button" value="Set Diary" id="setbtn" class="btn btn-info active" onclick="savediary(1)" style="margin-bottom: 5px;">
                              <%} %>
                             
                             </div>
                              <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
	                            <!--  <label>.</label><br> -->
	                             <%String classHiddenOPD=""; %>
	                             <%if(loginInfo.isLmh()){ 
	                            	 classHiddenOPD ="hidden";
	                            	 if(loginInfo.isCommon_db_clinic()){
	                            		 classHiddenOPD="";
	                            	 }
	                             } %>
	                             <input onclick="showpopupfornewopd()" type="button" id="printcomdashboard" style="margin-bottom: 5px;" value=" Book Appointment " class="btn btn-primary <%=classHiddenOPD %>" />
	                             <input onclick="getslot()" type="button" value="Block Time" class="btn btn-primary  aftertrans" style="margin-bottom: 5px;">
	                              
	                          	 <a style="margin-bottom: 5px;" href="#" onclick="openopdpreviewwindow()"id="printcomdashboard" class="btn btn-primary">Preview</a>
	                          	  
	                            <input onclick="showdisplaynewopd()" style="margin-bottom: 5px;" type="button" value=" Refresh " class="btn btn-primary">
	                            <a href="#" onclick="openBlankPopup('AppointmentType?selectedid=2&onlyviewss=1')"id="ratelist" style="margin-bottom: 5px;" class="btn btn-primary">Rate List</a>
	                            <%if(loginInfo.isCommon_db_clinic()){ %>
	                            	<a style="margin-bottom: 5px;" href="referedpatientreportFinder"  id="printcomdashboard" class="btn btn-primary">Patient Refer Dash board (PRO)</a>
	                            <%}else{ %>
	                            	<a style="margin-bottom: 5px;" href="#" onclick="openPopup('referedpatientreportFinder')" id="printcomdashboard" class="btn btn-primary">Refred Patient Report</a>
	                            <%} %>
	                        </div>
                            
                            </div>
                            <s:form action="getAllPrintDataNotAvailableSlot?action=dashboard"   id="printdashboardfrm" method="post" 
              							target="formtarget">
										<s:hidden name="printCommencing" id="printCommencing"> </s:hidden>
										<s:hidden name="printLocation" id="printLocation"> </s:hidden>
										<s:hidden name="printDiaryserid" id="printDiaryserid"> </s:hidden>
										
										<s:hidden name="diaryUser" id="diaryUser"></s:hidden>
										
											<!-- <div class="btnseparator hidden-xs hidden-sm"></div> -->
            	 						<div  id="" class="">
            	 							<div style="margin-top: 2px;">
            	 								<span  class="">
              										<%-- <s:submit id="printcomdashboard" value=" Preview " onclick="getPrintData()" theme="simple" cssclass="btn btn-primary" style="margin-top: -1px; padding: 2px;"></s:submit> --%>						
              										<%-- <s:submit id="printcomdashboard" value=" Preview "  theme="simple" cssClass="btn newbtn" cssStyle="margin-top:-2px;margin-left: 293px;"></s:submit> --%>
                								</span>
                							</div>
                						</div> 
                						
                			</s:form>
                            <div  id="showmonthbtn" class="fbutton mago martop2das hidden">
                					<div class="input-group ">
                                          <span class="input-group-addon" id="basic-addon1"><i class="fa fa-map-marker"></i></span>
                							 <s:if test="%{#locationList != 'null'}" >
				 											<s:select onchange="getPractitionerDayView()"  cssClass="form-control mapmarw" id="locationid" name="locationid" list="locationList" listKey="locationid" listValue="location" headerKey="0" theme="simple" headerValue="All" />
														</s:if>
										</span>
									</div>
								</div>
                         <!--   <div class='col-lg-3 col-md-3' style="margin-left: -23px">
                            <label>.</label><br>
                           
                						</div> -->