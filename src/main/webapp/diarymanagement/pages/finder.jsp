<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="diarymanagement/js/finder.js"></script>
<script type="text/javascript" src="accounts/js/printpreview.js"></script> 

<%-- <script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script> 
<script type="text/javascript" src="diarymanagement/js/commonAppointmentView.js"></script>
 --%>
 
 <style>
 .paddniltopase{
 padding: 0 2px 50px;
 }
 
 </style>
 
 <%
	LoginInfo loginfo = LoginHelper.getLoginInfo(request);
%>
 
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<img src="manasmaindashboard/images/Appointment_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
										 	<h4>Finder</h4>
										</div>
										<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
											<img src="dashboardicon/shirt.png" class="img-responsive prescripiconcircle">
										</div>
										<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
											<h4>Finder</h4>
										</div> -->
									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


	<div class="col-lg-12 col-md-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
		
		
		
	</s:if>
	</div>

			
	<%-- <script type="text/javascript">
	
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
		
		
		$( "#appointment" ).dialog({
			autoOpen: false,
			resizable: true,
			height: 400,
			width: 450,
			modal: true,
			
			buttons: {
				"Save": function() {
					//$( this ).dialog( "close" );
					var starttime = read_cookie("cookieNewStarttime");
					
						$(this).saveSlot(editAppointId,editStartTime);
						
					
				
					
				},
				Cancel: function() {
					//document.getElementById('addTreatment').disabled = true;
					$( this ).dialog( "close" );
				}
				
			}
		});
		
		
		$( "#checkavlbltydivpopup" ).dialog({
			autoOpen: false,
			resizable: true,
			height: 400,
			width: 700,
			modal: true,
			buttons: {
				"Ok": function() {
					//setClientDidNotComeConfirm();
					$( this ).dialog( "close" );
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
				
			}
		});
		
		$( "#addTreatmentEpisodeDiv" ).dialog({
			autoOpen: false,
			resizable: true,
			height: 500,
			width: 450,
			modal: true,
			buttons: {
				"Save": function() {
					saveTreatment();
					
				},
				Cancel: function() {
					$( this ).dialog( "close" );
					

				}
				
			}
		});
		
		
		$( "#addPatientDiv" ).dialog({
			autoOpen: false,
			resizable: true,
			height: 600,
			width: 500,
			modal: true,
			buttons: {
				"Save": function() {
					savePatient();
					
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
				
			}
		});
		
		
		
	});
	 
	</script>
	 --%>
	
	
	
	
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		
		 
		
		<input type="hidden" id="invoicee" name="invoicee"/>
		<input type="hidden" id="commencing" name="commencing"/>
		<input type="hidden" id="caldate" name="caldate"/>
		
		
<%-- <s:form action="Finder"  theme="simple">
			
<div class="row">
<div class="col-lg-1 col-md-1" style = "padding-left: 59px;">
	<label>Client:</label>
</div>
	<div class="col-lg-5 col-md-5">
	
		<div class="input-group">
			<s:hidden name="clientId" id = "clientId" ></s:hidden>
			<s:textfield  name="client" id="client" readonly="true"
				  cssClass="form-control" onclick="showPopUp()"></s:textfield> <span
				class="input-group-btn">
			<s:submit value="Go!" cssClass="btn btn-primary"></s:submit>	
			
		</span>
		</div>
	</div>
</div>
</s:form> --%>


<script>
	$(document).ready(function() {

		$("#fromDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true


		});
		
		$("#toDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

	});	
	
	
	function resetformcl(){
		document.getElementById('clientId').value="";
		document.getElementById('client').value="";
		
	}
</script>
<input type="hidden" id='setp'>
<s:form action="Finder"  theme="simple">
<s:hidden name="clientId" id = "clientId" ></s:hidden>
<input type="hidden" name="IpdpatientType" value="0">
<div class="col-lg-12 col-md-12 topback2 hidden-print">
	<%-- <div class="col-lg-2 col-md-2" style="margin-left: -19px">
	
		<label class="findersearch" style="float: left;">Search : </label>
		<s:textfield  name="client" id="client" readonly="true"
				  cssClass="form-control" onclick="showPopUp()" placeholder="Search by patient"></s:textfield>
	</div>
	
		<div class="col-lg-1 col-md-1">
		<label>Pay By</label>
		<s:select list="#{'':'All','Client':'Self','Third Party':'Third Party'}" name='withpayment' cssClass="form-control"></s:select>
		</div>
		
		<div class="col-lg-2 col-md-2">
		<label>Practitioner List</label>
		<s:select list="userList" name='diaryUser' listKey="id" listValue="diaryUser" headerKey="" headerValue="Practitioner List" cssClass="form-control chosen-select"></s:select>
		</div> --%>
		
		
		<div class="col-lg-1 col-md-1">
			<label>From Date : </label>
					<s:textfield readonly="true" name="fromDate" id="fromDate" placeholder="Search by Date"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		<div class="col-lg-1 col-md-1">
			<label>To Date : </label>
					<s:textfield readonly="true" name="toDate" id="toDate" placeholder="Search by Date"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		
		<div class="col-lg-2 col-md-2">
		   <label>Department List</label>
		      <s:select list="Fakeconsultdeptlist" name='DisciplineName' listKey="id" listValue="DisciplineName" headerKey="" headerValue="Practitioner List" cssClass="form-control chosen-select"></s:select>
		</div> 
		
		<div class="col-lg-2 col-md-2" style="margin-left: -19px;margin-top: 20px;">
               <s:textfield  name="SearchText" id="SearchText" 
				    cssClass="form-control" placeholder="Search by patient"></s:textfield>			
				    
		</div>
		
		
			 <div class="col-lg-1 col-md-1">
				<label> Type : </label>
					<s:select name="appointmentTypeTest" id="appointmentTypeTest"  list="#{'':'All','1':'OPD','2':'OT'}" cssClass="form-control chosen-select" ></s:select>
				</div>
				<%-- <div class="col-lg-1 col-md-1">
				<label> Time </label>
					<s:select name="appointment.appointmentTypeTime" id="appointmentTypeTime"  list="#{'':'All','1':'Past(7 days)','2':'Future(7 days)'}" cssClass="form-control chosen-select" ></s:select>
				</div>
				<div class="col-lg-1 col-md-1" style="width: 9%">
				<label>Is Completed : </label>
					<s:select name="appointment.drcompleted" id="drcompleted"  list="#{'':'All','1':'Not Completed','2':'Completed'}" cssClass="form-control chosen-select" ></s:select>
				</div>  --%>
		
		
		
		
		<div class="col-lg-1 col-md-1">
		<br>
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary" cssStyle="width:95%" ></s:submit>
			</div>	
		<!-- 	<div class="col-lg-1 col-md-1">
		<br>
				<input type="button" class='btn btn-primary' value="Reset Client" onclick="resetformcl()" >
			</div>	 -->
</div>
</s:form>

<br/>		
 <input type="hidden"  id="fake_uhid">
   <input type="hidden"  id="fake_clientid">
   <%-- <span id="patient_nm"></span> --%>
	
		<div style="color: red;">Note : Please note Appointment Finder will only show todays and future appointment in the dashboard.</div>
		<s:if test="finderList!=null">
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-hover table-condensed table-bordered">
							<thead>
						
					
					<tr>
					    <th class="manastableheader">Sr.No</th>
						<th class="manastableheader">Date</th>
						<th class="manastableheader">Practitioner</th>
						<th class="manastableheader">Patient</th>
						<th class="manastableheader">Department</th>
						<th class="manastableheader">Age/Gender</th>
						<% if(!loginfo.isMbbs()) {%>
						<th class="manastableheader">Time</th>
						<th class="manastableheader">Duration</th>
						<%}%>
						<th class="manastableheader">Appointment Type</th>
						<th class="manastableheader">Treatment Episode Name </th>
						<th class="manastableheader">Ipd No </th>
						<th class="manastableheader">Print </th>
						
						<th hidden class="manastableheader">Status</th>
						<th hidden class="manastableheader" > Pay By</th>
						<th hidden class="manastableheader">Notes</th>
						<th hidden class="manastableheader">Req. Date/Time</th>
						<th hidden class="manastableheader">Paid/Unpaid</th>
						<th hidden class="manastableheader">Cancel</th>
						<th hidden class="manastableheader">SMS</th>
						
					</tr>
					</thead>
					<tbody>
					<%int i=1; %>
					<s:iterator value="finderList">
					
						<s:if test="oldata==true">
						<tr id="<s:property value="id"/>" style="color: black;">
						    <td><%=i %></td>
							<td style="font-size: 13px;"><s:property value="commencing"/></td>
							<td style="font-size: 13px;"><s:property value="diaryUser"/></td>
							<%-- <td><s:property value="clientName"/></td> --%>
					        <td  style="vertical-align: middle;font-size: 13px;"><b><a href="#" onclick="fakepopup('<s:property value="Abrivationid"/>','<s:property value="ClientId"/>','<s:property value="ClientName"/>')"><s:property value="ClientName" /></a></b></td>
					        <td style="font-size: 13px;"><s:property value="Department"/></td>
					        <td style="font-size: 13px;"><s:property value="Agegender"/></td>
					        <% if(!loginfo.isMbbs()) {%>
							<td style="font-size: 13px;"><s:property value="sTime"/></td>
							<td style="font-size: 13px;"><s:property value="duration"/></td>
							<%}%>
							<td style="font-size: 13px;"><s:property value="apmttypetext"/></td>
							<td style="font-size: 13px;"><s:property value="treatmentSessions"/></td>
							<td style="font-size: 13px;"><s:property value="Mbbs_seq_no"/></td>
							<td style="font-size: 13px;"><i class='fa fa-print fa-1x' onclick="openPopup('printConsulatationFormCommonnew?clientId=<s:property value="ClientId"/>&apmtId=<s:property value="Id"/>')"></i></td>
							<%-- <s:if test="status==0">
								<td>Appointment Slot</td>
							</s:if>
							<s:else>
								<td>Blocked Slot</td>
							</s:else> --%>
							<td hidden><s:if test="whopay=='Client'">Self</s:if><s:else>Third party</s:else></td>
							<td hidden><s:property value="notes"/></td>
							<td hidden><s:property value="requestDateTime"/></td>
							<td hidden><s:property value="payBy"/></td>
							<s:url action="editUserProfile" id="edit">
	 							<s:param name="id" value="%{id}"></s:param>
							</s:url>
	  						 <%-- <td class="text-center"><a class="text-warning" href="#" onclick="modifyAppointment('<s:property value="commencing"/>','<s:property value="diaryUser"/>','<s:property value="sTime"/>','<s:property value="duration"/>','<s:property value="apmtType"/>','<s:property value="notes"/>','<s:property value="endTime"/>','<s:property value="location"/>','<s:property value="treatmentEpisodeId"/>','<s:property value="id"/>','<s:property value="apmtSlotId"/>','<s:property value="diaryUserId"/>','<s:property value="clientId"/>','<s:property value = "condition"/>');"><i class="fa fa-edit"></i></a></td> --%>
	  						
	 						<s:url action="deleteFinder" id="deleteFinder">
								<s:param name="id" value="%{id}"></s:param>
							</s:url>
							<%-- <td class="text-center"><s:a href="%{deleteFinder}" cssClass="text-danger" onclick = "return confirmedDelete()"><i class="fa fa-trash-o"></i></s:a></td> --%>	
							
							<td hidden class="text-left"><s:if test="payBy=='Paid'"><a href="#">Can't Delete </a></s:if><s:else><a href="#" onclick = "openFinderCancelApmtPopup(<s:property value="id"/>)"><i class="fa fa-trash-o"></i></a></s:else></td>
							<td hidden class="text-center"><a href="#" onclick = "openSmsPopup(<s:property value="clientEmail"/>)"><i class="fa fa-comments-o" aria-hidden="true"></i></a></td>
						   <%i++; %>
						</tr>
						</s:if>
						<s:else>
						<tr id="<s:property value="id"/>">
							<td><s:property value="commencing"/></td>
							<td><s:property value="diaryUser"/></td>
							<td><s:property value="clientName"/></td>
							<td><s:property value="sTime"/></td>
							<td><s:property value="duration"/></td>
							<td><s:property value="apmttypetext"/></td>
							<td><s:property value="treatmentSessions"/></td>
							<s:if test="status==0">
								<td>Appointment Slot</td>
							</s:if>
							<s:else>
								<td>Blocked Slot</td>
							</s:else>
							<td><s:property value="whopay"/></td>
							<td><s:property value="notes"/></td>
							<td><s:property value="requestDateTime"/></td>
							<td><s:property value="payBy"/></td>
							<s:url action="editUserProfile" id="edit">
	 							<s:param name="id" value="%{id}"></s:param>
							</s:url>
	  						 <%-- <td class="text-center"><a class="text-warning" href="#" onclick="modifyAppointment('<s:property value="commencing"/>','<s:property value="diaryUser"/>','<s:property value="sTime"/>','<s:property value="duration"/>','<s:property value="apmtType"/>','<s:property value="notes"/>','<s:property value="endTime"/>','<s:property value="location"/>','<s:property value="treatmentEpisodeId"/>','<s:property value="id"/>','<s:property value="apmtSlotId"/>','<s:property value="diaryUserId"/>','<s:property value="clientId"/>');"><i class="fa fa-edit"></i></a></td> --%>
	  						
	 						<s:url action="deleteFinder" id="deleteFinder">
								<s:param name="id" value="%{id}"></s:param>
							</s:url>
							<td class="text-center"><a href="#" onclick = "openFinderCancelApmtPopup(<s:property value="id"/>)"><i class="fa fa-trash-o"></i></a></td>
							<td class="text-center"><a href="#" onclick = "openSmsPopup(<s:property value="clientEmail"/>)"><i class="fa fa-comments-o" aria-hidden="true"></i></a></td>	
						</tr>
						</s:else>
					
						
					
					</s:iterator>
				</tbody>
				</table>
				</div>
				</div>
				</div>
				
			
			</s:if>
			
			
			<s:form action="deleteFinder" id="finderfrm">
				<s:hidden name="id" id="id"/>
				<s:hidden name="cancelApmtNote" id="cancelfinderApmtNote"/>
				<s:hidden name="clientId" id = "clientId" ></s:hidden>
			
			</s:form>
			
			
	<!-- Cancel Appointment Note Popup  -->

	<div class="modal fade" id="cancelApmtNoteDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Cancel Appointment Note</h4>
				</div>
				<div class="modal-body">
				
				<label>Note:</label>
				<textarea id = "cancelApmtNote" name="cancelApmtNote" class="form-control showToolTip" placeholder="Enter Cancellation Note"></textarea>
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" 
						onclick="deleteFinderNotAviSlot()">Cancel Appointment </button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Patient Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
				
			
  <div class="modal fade" id="smssend" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
		
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Send SMS</h4>
				</div>
				<div class="modal-body">
				
		<s:hidden name="clientid" id="clid"></s:hidden>
				<div class="form-inline">
				<div class="form-group">
				<input type="radio" value="English" id="smsenglish" name="smsrd" >
				 <label for="smsenglish">English</label>
				<input type="radio" value="Hindi" id="smsmarathi" onclick="changelang()" name="smsrd">
				 <label for="smsmarathi">Hindi</label>
				</div>
				<div class="form-group">
				<textarea id="transliterateTextarea" style="width:280px;height:100px"></textarea>
				</div>
				</div>
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="sendfollowsms()">Send</button>
				</div>
			</div>
			
		</div>
	</div>
	
	
	<!-- Ipd Fake POpUp -->
	<s:hidden name="clientid" id="otcount"  hidden="true"></s:hidden>
<div class="modal fade" id="fakepopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><%=loginfo.getPatientname_field() %> Panel - <span id="patient_nm"></span></h4>
      </div>
      <!-- <div class="modal-body" style="min-height: 179px;"> -->
      <%String heightDyn="268px"; %>
        <%if(loginfo.isAdmsn_date_edit()){ %>
                                                  <%heightDyn="360px"; %>
        <%} %>                                          
       <div class="modal-body" style="min-height: <%=heightDyn%>;">
       <div class="col-lg-12 borderdesing">
                                              <div class="row">
                                                 
                                                  
                                                <div class="col-xs-2 col-md-2 panthumb">
                                                     <div class="thumbnail">
                                                      	<a href="#"  onclick="openopddash()">
                                                        <img src="manasmaindashboard/images/My opd_icon.svg" alt="raise_credit" style="width: 40px;">  
                                                         <div class="caption">
                                                              <p class="textclinet">Opd</p>
                                              
                                                          </div>
                                                         </a>
                                                        <div align="centre"> 
                                                        <div style="margin-left: 40%; color: red;">
                                                         <span style="" id="opdcount"></span>
                                                         </div>
                                                         </div>
                                                      </div>
                                                   
                                                  </div>
                                                  <div class="col-xs-2 col-md-2 panthumb">
                                                   <div class="thumbnail">
                                                      	<a href="#"  onclick="openInvestigationdash()">
                                                        <img src="manasmaindashboard/images/Investigation)Icon.svg" alt="raise_credit" style="width: 45px;">  
                                                         <div class="caption">
                                                              <p class="textclinet">View Investigation</p>
                                                          </div>
                                                         </a>
                                                         <div style="margin-left: 40%; color: red;">
                                                         <span style="centre" id="invcount"></span>
                                                         </div>
                                                      </div>
                                                  </div>
                                                  
                                                  <div class="col-xs-2 col-md-2 panthumb">
                                                   <div class="thumbnail">
                                                      	<a href="#"  onclick="openPriscriptiondash()">
                                                        <img src="manasmaindashboard/images/Prescription_Icon.svg" alt="raise_credit" style="width: 25px;">  
                                                         <div class="caption">
                                                              <p class="textclinet">Pharmacy Dashboard</p>
                                                          </div>
                                                         </a>
                                                         <div style="margin-left: 40%; color: red;">
                                                         <span style="centre" id="precount"></span>
                                                         </div>
                                                      </div>
                                                  </div>
                                                  
                                                  <div class="col-xs-2 col-md-2 panthumb">
                                                   <div class="thumbnail">
                                                      	<a href="#"  onclick="openPharmacydash()">
                                                        <img src="manasmaindashboard/images/Pharmacy_Icon.svg" alt="raise_credit" style="width: 45px;">  
                                                         <div class="caption">
                                                              <p class="textclinet">Pharmacy Bill</p>
                                                          </div>
                                                         </a>
                                                         <div style="margin-left: 40%; color: red;">
                                                         <span style="centre" id="pharcount"></span>
                                                         </div>
                                                      </div>
                                                  </div>
                                                  <div class="col-xs-2 col-md-2 panthumb">
                                                   <div class="thumbnail">
                                                      	<a href="#"  onclick="openPriscDashboard()">
                                                        <img src="manasmaindashboard/images/Pharmacy_Icon.svg" alt="raise_credit" style="width: 45px;">  
                                                         <div class="caption">
                                                              <p class="textclinet">Priscription</p>
                                                          </div>
                                                         </a>
                                                         <div style="margin-left: 40%; color: red;">
                                                         <span style="centre" id="prisccount"></span>
                                                         </div>
                                                      </div>
                                                  </div>
                                                   <div class="col-xs-2 col-md-2 panthumb">
                                                   <div class="thumbnail">
                                                      	<a href="#"  onclick="openipd()">
                                                        <img src="manasmaindashboard/images/Pharmacy_Icon.svg" alt="raise_credit" style="width: 45px;">  
                                                         <div class="caption">
                                                              <p class="textclinet">Ipd</p>
                                                          </div>
                                                         </a>
                                                         <div style="margin-left: 40%; color: red;">
                                                         <span style="centre" id="ipdcount"></span>
                                                         </div>
                                                      </div>
                                                  </div>
                                                  
                                                  
                                                  
                                              </div>

                                              </div>
                                              
                                                 
                                 
                                                  <div class="col-xs-2 col-md-2 panthumb">
                                                 
                                                  </div>
                                                  <div class="col-xs-2 col-md-2 panthumb" >
                                                  
                                                  </div> 
                                                  <div class="col-xs-2 col-md-2 panthumb" >
                                                  
                                                  </div>
                                                <input type="button" value="delete" style="margin-top: 5%;margin-left: -7%" class="btn btn-info active" onclick="delte()">   
                                              </div>
                                              
                                          </div>
      </div>
      <div class="modal-footer hidden">
        <button type="button" class="btn btn-default hidden" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary hidden">Save changes</button>
      </div>
    </div>
  </div>
	
	
	
	
	<%--
	<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg">			
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
				</div>
				<div class="modal-body" >
					<%@ include file="/diarymanagement/pages/addPatientPage.jsp"%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="return savePatient()">Save</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" >Close</button>
				</div>
			</div>
		</div>
	</div>
			
<div class="modal fade" id="addTreatmentEpisodeDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add Treatment Episode</h4>
				</div>
				<div class="modal-body">
						<%@ include file = "/treatmentEpisode/pages/addTreatmentEpisode.jsp"%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>	

<div class="modal fade" id="checkavlbltydivpopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Appointment Availability</h4>
				</div>
				<div class="modal-body">
					<%@ include file="/diarymanagement/pages/checkAvailability.jsp" %>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>	

	 --%>
											

											
										</div>
									</div>
								</div>
							</div>
						</div>







<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }	
  </script>


<script>
function openSmsPopup(ccid){
	document.getElementById("clid").value=ccid;
	$('#smssend').modal( "show" );
}

</script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script>

google.load("elements", "1", {
      packages: "transliteration"
    });

function onLoad() {
	var options = {
            sourceLanguage:
                google.elements.transliteration.LanguageCode.ENGLISH,
            destinationLanguage:
                [google.elements.transliteration.LanguageCode.HINDI],
            shortcutKey: 'ctrl+g',
            transliterationEnabled: true
        };

  // Create an instance on TransliterationControl with the required
  // options.
  var control =
      new google.elements.transliteration.TransliterationControl(options);

  // Enable transliteration in the textbox with id
  // 'transliterateTextarea'.
  control.makeTransliteratable(['transliterateTextarea']);
}
google.setOnLoadCallback(onLoad);
</script>






