<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="diarymanagement/js/finder.js"></script>

<%-- <script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script> 
<script type="text/javascript" src="diarymanagement/js/commonAppointmentView.js"></script>
 --%>
  <script src="notification/js/bootstrap-select.js"></script>  
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="emr/js/emrNew.js"></script>
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<style>
 .table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
    background-color: #c7e7e8;
}



input[type="checkbox"] {  opacity: 1; z-index: -1; }
input[type="checkbox"]+span { font: 16pt sans-serif; color: #000; }
input[type="checkbox"]+span:before { font: 16pt FontAwesome; content: '\00f096'; display: inline-block; width: 16pt; padding: 2px 0 0 3px; margin-right: 0.5em; }
input[type="checkbox"]:checked+span:before { content: '\00f046'; }
input[type="checkbox"]:focus+span:before { outline: 1px dotted #aaa; }
input[type="checkbox"]:disabled+span { color: #999; }
input[type="checkbox"]:not(:disabled)+span:hover:before { text-shadow: 0 1px 2px #77F; }

.newbtnyes{
position: absolute !important;
    font-size: 14px !important;
    color: #fff !important;
    background-color: #38a538 !important;
    border-color: #15536E !important;
    margin-right: -203px !important;
    border-radius: 0.50rem; 
   
}
.newbtnno{
position: absolute !important;
    font-size: 14px !important;
    color: #fff !important;
    background-color: #e22626 !important;
    border-color: #15536E !important;
    margin-right: -203px !important;
    border-radius: 0.50rem; 
   
}

.newbtn{
position: absolute !important;
    font-size: 14px !important;
    color: #fff !important;
    background-color: #15536E !important;
    border-color: #15536E !important;
    margin-right: -203px !important;
    border-radius: 0.50rem; 
   
}
.btn{
background-color: #15536E !important;
border-radius: 0.50rem;
}
</style>  
<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<img src="manasmaindashboard/images/Appointment_Icon.svg" style="filter: brightness(5);margin-left: 7px;">
										 	<h4>Department OPD</h4>
										</div>
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
	
	
	
		
		<span class="error"><s:actionerror id="server_side_error"/></span>
		
		 
		
		<input type="hidden" id="invoicee" name="invoicee"/>
		<input type="hidden" id="commencing" name="commencing"/>
		<input type="hidden" id="caldate" name="caldate"/>
		
		


<script>
	$(document).ready(function() {

		
		
		$("#fromDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#followupDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#sittingDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#ed_sittingDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#sittingBackDate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#edit_sittingBackDate").datepicker({

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
	$(function () {
	    $('#chdept').selectpicker();
	});

	</script>

<input type="hidden" id='setp'>
<s:form action="departmentopdFinder" id="deptfrm" theme="simple">
<s:hidden name="clientId" id = "clientId" ></s:hidden>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2  hidden-print">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2  hidden-print">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2">
			<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
				<label>Department List</label>
				<% if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || !(loginInfo.getShow_dept_opd_list().equals("0"))) {%>
					<s:select list="disciplineList" name='dept' listKey="id" listValue="discipline" headerKey="" headerValue="All Department" cssClass="form-control chosen-select"></s:select>
			
				<%}else{ %>
				<s:select list="disciplineList"  disabled="true" name='dept' listKey="id" listValue="discipline" headerKey="" headerValue="All Department" cssClass="form-control chosen-select"></s:select>
				
				<%} %>	
			</div>
			<!-- <a href="Patientdata">Fake Data</a> -->
			<div class="col-lg-1 col-md-1 col-xs-11 col-sm-11">
				<label>Date : </label>
				<s:textfield readonly="true" name="fromDate" id="fromDate" placeholder="Search by Date"
				cssClass="form-control" theme="simple" />
			</div>
			<div class="col-lg-1 col-md-1 col-xs-11">
				<label for="exampleInputEmail1">Patient Type</label>
			    <s:select list="#{'0':'Self','1':'Third Party'}"  onchange="gettplist(this.value)"  name="payee" id="payee" cssClass="form-control chosen-select"/>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
				<label for="exampleInputEmail1" id="hstp">Select Payer</label>
				<div id="dstp">
				<s:select list="tpNameList" onchange='resetChargeAndWard()' listKey="tpName" listValue="tpName" id="tpid" headerKey="0" headerValue="Select Payer" name="patientcategory" title="select Third Party"
					cssClass="form-control chosen-select"   > </s:select>
				</div>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
				<label>Order by : </label>
				<s:select list="#{'desc':'Descending','asc':'Ascending'}" id="orderby" name="orderby" cssClass="form-control"></s:select>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
								<label>Primary Doctor : </label>
								<div id="primarydiv">
									<s:select list="userList" theme="simple" listKey="id"
										listValue="diaryUser" cssClass="form-control chosen-select"
										headerKey="" headerValue="Select Doctor" name="diaryUser"
										id="diaryUser" />
								</div>
							</div>
			<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12" style="margin-top: 20px;">
				<!-- <label>  &emsp; &emsp; &emsp;</label><br> -->
				<input type="submit" value="Go" class="btn btn-primary active"/>
				<!-- <a href="#" class="btn btn-info active" onclick="document.getElementById('deptfrm').submit();"><i class="fa fa-refresh" aria-hidden="true"></i>&emsp;Refresh</a> -->
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 topback2 hidden-print">
			<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
				<input id="myInput" type="text" placeholder="Search Patient" onkeyup="myFunction()" class="form-control">&emsp;
			</div>	
			<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12">
				<a href="#" class="btn btn-primary" onclick="openBlankPopup('Patientdata')">Add Clinical Patient</a>
				<a href="#"  onclick="openBlankPopup('referdepartmentclinicalPatientdata')" class="btn btn-primary">Refer & Assign (CP)</a>
				<%if(loginInfo.isDeptOpdReport() || loginInfo.getSuperadminid()==1) {%>
				<input type="button" value="Department OPD Report" class="btn btn-info active" onclick="openPopup('lmhopdpreviewFinder')">
				<%} %>
				<input type="button" value="Daily OPD" class="btn btn-info active" onclick="openPopup('dailyregistrationrptFinder')">
				<input type="button" value="Sitting And Follow Up Report" class="btn btn-info active" onclick="openPopup('sittingandfollouprptChargesRpt')">
				
				<%if(loginInfo.isAmravati() || loginInfo.isLmh()){ %>
				<input type="button" value="Patient History" class="btn btn-info active" onclick="openPopup('patienthistoryFinder')">
				<input type="button" value="Daily Opd Color Report" class="btn btn-info active" onclick="openPopup('dailyopdcolorrptChargesRpt')">
				<%if(loginInfo.isAmravati()){%>
				<input type="button" value="Refer Department Count Report" class="btn btn-info active" onclick="openPopup('referdepartmentcountPatientdata')">
				<%}else{ %>
				<input type="button" value="Department Wise Count Report" class="btn btn-info active" onclick="openPopup('deptwisecountPatientdata')">

				<%} %>
				<%} %>
				<div class="form-inline" style="float: right;margin-top: 5px;text-transform: uppercase;">
				  <div class="checkbox">
				    <label>
				      <i class="fa fa-square" aria-hidden="true" style="color:#f5c286;"></i> Dr Assign
				    </label>
				  </div>|
				  <div class="checkbox">
				    <label>
				      <i class="fa fa-square" aria-hidden="true" style="color:orange;"></i> Sitting & Follow Up
				    </label>
				  </div>|
				  <div class="checkbox">
				    <label>
				      <i class="fa fa-square" aria-hidden="true" style="color:orangered;"></i> Referred
				    </label>
				  </div>
				</div>
			</div>
		</div>
		
		
	</div>
	<%-- <div class="col-lg-4 col-md-4 col-xs-12 col-sm-12 topback2">
		<table class="table table-hover table-condensed table-bordered" style="border: black 1px solid;">
			<thead>
				<tr>
					<td>Department</td>
					<td>New Patient</td>
					<td>Old Patient</td>
					<td>Total Count</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="deptWiseCountList">
					<s:if test="totalPatientCount>0">
						<tr>
							<td><s:property value="discipline"/></td>
							<td><s:property value="newPatientCount"/></td>
							<td><s:property value="oldPatientCound"/></td>
							<td><s:property value="totalPatientCount"/></td>
						</tr>
					</s:if>
				</s:iterator>
			</tbody>
			<thead>
				<tr>
					<td>Total Patient</td>
					<td><s:property value="finalNewPatientCount"/></td>
					<td><s:property value="finalOldPatientCount"/></td>
					<td><s:property value="finalTotalPatientCount"/></td>
				</tr>
			</thead>
		</table>
	</div> --%>
		
</div>

</s:form>
<%String hideselect=""; %>
<s:if test="selectdepartment==''">
<%hideselect="disabled='disabled'"; %>
</s:if>
<s:else>
<%hideselect=""; %>
</s:else>
<br/>		
	<%int i=1; %>
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-hover table-condensed table-bordered">
							<thead style="text-align: center;">
						
					
								<tr>
									<td>Serial No</td>
									<td>Booking Date</td>
									<td>Referred Date</td>
									<!-- <td>Booked By</td> -->
									<td>U H I D</td>
									<td>Patient Name</td>
									<td>Type</td>
									<td>Age/Gender</td>
									<td>Contact No</td>
									<%if(!loginInfo.isAmravati()){ %>
									<td>Patient Category</td>
									<%} %>
									<td>Camp Area</td>
									<td>Enrollment No</td>
									<td>Allotted Department</td>
									<td>Referred From Dept</td>
									<td>Referred To & Remark</td>
									<%if(!loginInfo.isMatrusevasang()){ %>
									<td>Add Sitting</td>
									<td>Sitting</td>
									<%} %>
									<td>Referred To Dept</td>
									<%if(loginInfo.isPhysio()) {%>
									<td>Plan</td>
									<%} %>
									<td>Assign To</td>	
									<td class="hidden">Follow Up</td>
									<td>Modify</td>	
									<td>Print</td>	
								</tr>
								
							</thead>
					<tbody style="text-align: center;" id="newopdbodyid">
					<s:iterator value="departmentOPDList">
						<s:if test="diaryUserId==1">
						<tr>
						</s:if>
						<s:else>
						<tr style="background-color:#f5c286; ">
						</s:else>
								
									<td><%=i++ %></td>
									<td><s:property value="datetime"/></td>
									<td><s:property value="apptdate"/></td>
								<%-- 	<td><s:property value="addedBy"/></td> --%>
									<td><s:property value="abrivationid"/></td>
									<td><s:property value="clientName"/></td>
									<td>
										<s:if test="newpatient==1">
											NEW
										</s:if> 
										<s:else>
											OLD
										</s:else>
									</td>
									<td><s:property value="agegender"/></td>
									<td><s:property value="mobno"/></td>
									<%if(!loginInfo.isAmravati()) {%>
									<td><s:property value="patientcategory" /> </td>
									<%} %>
									<td><s:property value="campArea" /></td>
									<td><s:property value="enrollcode" /></td>
									<td><s:property value="departmentname"/></td>
									<td><s:property value="referredfromdept"/></td>
									<%-- <td><s:property value="planid"/></td> --%>
									<td style="vertical-align: middle;"><a href="#" onclick="showremarklist(<s:property value="id"/>)">Read Remark</a></td>
 								  <%--   <td><a href="#" onclick="showsitting(<s:property value="id"/>,<s:property value="clientId"/>)">Add Sitting</a></td>
 									<td><a href="#" class="btn btn-info" onclick="shositting(<s:property value="clientId"/>)">View Sitting</a></td> 
									  --%>
									
									<%-- <select name="depart" id="depart"  class="form-control chosen-select" onchange="showdeptpopup(this.value,<s:property value="id"/>,<s:property value="clientId"/>)">
									<s:iterator value="disciplineList">
									<s:if test="id==dept">
									<option value="<s:property value="id"/>" selected="selected" ><s:property value="discipline"/></option>
									</s:if>
									<s:else>
									<option value="<s:property value="id"/>"><s:property value="discipline"/></option>
									</s:else>
									</s:iterator>
									</select> --%>
									<%if(!loginInfo.isMatrusevasang()){ %>
									<s:if test="diaryUserId!=1">
										<s:if test="folloupGiven==1">
											<td style="background-color: orange;vertical-align: middle;">
												<input onclick="showsittingforPatient(<s:property value="id"/>,<s:property value="clientId"/>,'<s:property value="clientName"/>',<s:property value="dept"/>)" type='button' value='Add Sitting' class='btn btn-primary' >
											</td>
										</s:if>
										<s:else>
											<td style="vertical-align: middle;">
												<input onclick="showsittingforPatient(<s:property value="id"/>,<s:property value="clientId"/>,'<s:property value="clientName"/>',<s:property value="dept"/>)" type='button' value='Add Sitting' class='btn btn-primary' >
											</td>
										</s:else>
									</s:if>
									<s:else>
										<td></td>
									</s:else>
									<%} %>
									<%if(!loginInfo.isMatrusevasang()){ %>
									<td style="vertical-align: middle;">
										<s:if test="diaryUserId!=1">
										        <input onclick="shositting(<s:property value="clientId"/>)" type='button' value='View Sitting' class='btn btn-primary' >	
										</s:if>
										
									</td>
									
									<%} %>
									<s:if test="diaryUserId!=1">
										<s:if test="referStatus==1">
											<td style="background-color: orangered;vertical-align: middle;">
												<input onclick='changedepartment(<s:property value="clientId"/>,<s:property value="id"/>)' type='button' value='Refer To ' class='btn btn-primary' >
											</td>
										</s:if>
										<s:else>
											<td style="vertical-align: middle;">
												<input onclick='changedepartment(<s:property value="clientId"/>,<s:property value="id"/>)' type='button' value='Refer To ' class='btn btn-primary' >
											</td>
										</s:else>
									</s:if>
									<s:else>
										<td></td>
									</s:else>
									<%if(loginInfo.isPhysio()) {%>
									<td><s:property value="activeplan"/></td> 
									<%} %>
								<td style="vertical-align: middle;">
										<select name="doctid"  id="doctid" <%=hideselect %> class="form-control" onchange="showdoctpopup(this.value,<s:property value="id"/>,<s:property value="clientId"/>)" >
										<option value="0">Select Doctor (Not Assign)</option>
										<s:iterator value="userList">
										 <s:if test="id==diaryUserId">
									   <option value="<s:property value="id"/>" selected="selected" ><s:property value="diaryUser"/></option>
										</s:if>
										<s:else>
										<option value="<s:property value="id"/>"><s:property value="diaryUser"/></option>
										 </s:else>
										</s:iterator>
										</select> 
										<%-- <s:select list="userList" theme="simple" listKey="id" listValue="diaryUser" name="doctid" id="doctid"/> --%>
										
									</td>
									<td class="hidden"><a href="#" onclick="openFollowUpPopUp(<s:property value="id" />,<s:property value="clientId"/> )">Follow Up</a></td>
									<td style="vertical-align: middle;"><a href="#" onclick="openmodifypopup(<s:property value="id"/>,<s:property value="clientId"/>)"><i style="vertical-align: sub;" class="fa fa-edit"></i></a></td>
									<td style="vertical-align: middle;"><a href="#" onclick="openBlankPopup('printopdletterEmr?clientid=<s:property value="clientId"/>&isfromdeptopd=1&dept=<s:property value="dept"/>&datetime=<s:property value="datetime"/>')"><i style="vertical-align: sub;" class="fa fa-print" aria-hidden="true"></i></a></td>
								</tr>
					</s:iterator>
				</tbody>
				</table>
				
				
				
							<s:form action="departmentopdFinder" name="paginationForm" id="paginationForm" theme="simple">
							    
								     <s:hidden name="fromDate"></s:hidden>
								     <s:hidden name="orderby"></s:hidden>
								     <s:hidden name="dept"></s:hidden>
								     <s:hidden name="patientcategory"></s:hidden>
								     <s:hidden name="patienttype"></s:hidden>
								<div class="col-lg-12 col-md-12" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total: <label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<jsp:include page="/common/pages/pagination.jsp"></jsp:include>
								</div>
							</s:form> 
				
				</div>
				</div>
				</div>

							
						</div>
					</div>
					
				</div>
			</div>
		</div>
		
		
	<!-- Sitting List -->
		
		
<div class="modal fade modal-draggable" id="sitting_details"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog modal-md" role="document">
		  <div class="modal-content">
			  <div class="modal-header">
				  <button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="myModalLabel">Sitting</h3>
				
				 
			</div>
			
<div class="modal-body popmodals" id="newdiv">
			  <span id="clindname" style="font-size: 23px;font-weight: 800;"></span>
<%-- 				   <span style="padding-left: 660px;"><a href="#" class="btn btn-warning hidden-print" onclick="printDiv('newdiv')" style="margin-top: 15px;">print</a></span> 
 --%>			<div class="table-responsive" > 
					   <table  class="table table-hover table-condensed table-bordered" style="text-align: center;"> 
		      <thead>
				    <tr>  
				        <th class="text-center">Serial No</th>
					    <th class="text-center" hidden>ID</th>
						<th class="text-center">Department Name</th>
						<th class="text-center">Sitting</th>
					 	<th class="text-center" hidden>Followup</th> 
						<th class="text-center">Followup Date</th>
						<th class="text-center">Remark</th>
						<th class="text-center">Userid</th>
						<th class="text-center">Date_time</th>
						<th class="text-center">Procedure MasterName</th>
						<th class="text-center">Procedure Name</th>
						<th class="text-center">Sitting No</th>
						<%if(loginInfo.isPhysio()){ %>
					    <th class="text-center">Diagnosis</th>	
					    <%} %>
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
				   </tr>
		    </thead>
				
		              <tbody id="carttbody" style="text-align: center;">
           
                      </tbody>
			</table>
		</div>

                 <div class="modal-footer hidden">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				 </div>
			</div>
		</div>
	</div>
</div>       		
		
		
	 <!-- Sitting List End -->	
		
		
<!--Show ReMark  -->

<!-- Modal -->
  <div class="modal fade" id="showremark" role="dialog">
    <div class="modal-dialog modal-md" role="document">
    
  <!-- Modal content-->
  
     <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
             <h4 class="modal-title">Remark of <label id="patientname"></label></h4>
        </div>
        
     <div class="modal-body">
         <label>Refer From Department:</label>
        	<span id="referfromdept"></span><br>
        <label>Refer To Department:</label>
        	<span id="refertodept"></span><br>
        <label>Remark:</label>
          <p id="listremark" style="word-wrap:break-word;">Some text in the modal.</p>
        </div>
         <div class="modal-footer">
           <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
         </div>
        </div>
      
    </div>
  </div>
  
<!--End Show ReMark  -->


<!-- ADD Sitting Followup -->


<div id="showsitting" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         	<button type="button" class="close" data-dismiss="modal">&times;</button>
          	<h4 class="modal-title">Sitting of <label id="patientnm"></label></h4>
        </div>
  <div class="modal-body">
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      	
      	     <s:hidden id="sitting_Clientid"></s:hidden>
      	     <input type="hidden" id="dept_opdid">
             <input type="hidden" id="sitting_appointmentid">
                   	
      	 
      		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group" id="sittingDeptDiv">
	      			<label>Department List </label><label class="text-danger">*</label>
	      			<s:select list="disciplineList" name='department' id='dept' listKey="id"  listValue="discipline" headerKey="" headerValue="All Department" onchange="setDepartmentsitting(this.value)" cssClass="form-control chosen-select" ></s:select>
      			</div>
      		</div>
      <!-- 	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"> -->
      
              <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                    <div class="form-group" id="proddiv" >
        				 <label>Select Sitting</label>
        				 <select name="sitting_id" id="sitting_id" class="form-control chosen-select">
						 <option value="0">Select</option> 
						</select>
        			</div>
        	   </div>
      
            <%--  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<label>Procedure MasterList </label><label class="text-danger">*</label>
	      			<s:select list="procedurelist" name='all_procedure' id='all_procedure' listKey="id"  listValue="name" headerKey="" headerValue="All Procedure" onchange="setProceduremasterlist(this.value)" cssClass="form-control chosen-select" ></s:select>
      			</div>
      		</div> --%>  
      		
      		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                    <div class="form-group" id="mas_div" >
        				 <label>Select Procedure Master</label>
        				 <select name="master_id" id="master_id" class="form-control chosen-select">
						 <option value="0">Select</option> 
						</select>
        			</div>
        	   </div>
      		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                    <div class="form-group" id="pro_dure" >
        				 <label>Select Procedure</label>
        				 <select name="procedure_id" id="procedure_id" class="form-control chosen-select">
						 <option value="0">Select</option>
						</select>
        			</div>
        	 </div>
      
      	    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                <div class="form-group" id="sitting_num" >
        				 <label>Sitting No</label><label class="text-danger">*</label>
				              <input type="number" id="sittingno" name="sittingno"
				                Class="showToolTip form-control" data-toggle="tooltip"
					            title="Enter Sitting No " placeholder="Enter Sitting No"/>
        	    </div>
        	 </div>
        	 
        <%if(loginInfo.isLmh()){ %>	 
        	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<label>Sitting Date : </label>
	      			<s:textfield readonly="true" name="sittingbackdate" id="sittingBackDate" placeholder="Select Date"
					cssClass="form-control" theme="simple" />
				</div>
         </div>
       <%}else{ %>
       <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
      			<div class="form-group">
	      			<label>Sitting Date : </label>
	      			<s:textfield readonly="true" name="sittingbackdate" id="sittingBackDate" placeholder="Select Date"
					cssClass="form-control" theme="simple" />
				</div>
         </div>
       <%} %>	
      
           <%-- <div class="form-group">
      				<label>Sitting Followup</label><label class="text-danger">*</label>
      				<s:select list="sittinglist" id="sitting_id" name="sitting_id" listKey="id" listValue="sittingFollowup" headerKey="0" headerValue="Select Sitting" onchange="setProductofStore(this.value)" cssClass="form-control chosen-select"></s:select>
      				
                         <s:select list="sittinglist" id="sitting_id" name="" listKey="id" listValue="sittingFollowup" headerKey="0" headerValue="Select Sitting"  cssClass="form-control chosen-select"></s:select>
      				
                </div> --%>
                
          <!-- </div> -->    
      			
               
      	   <!--  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<div class="form-check">
	     				<input class="form-check-input" type="checkbox" value="" id="sittingFollowup">
	        			<label class="form-check-label" for="sittingFollowup">
	       					Followup
	  					</label>
			      	</div>
		      	</div>
      		</div> -->
      		
      	 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<label>Followup Date : </label>
	      			<s:textfield readonly="true" name="date" id="sittingDate" placeholder="Select Date"
					cssClass="form-control" theme="simple" />
				</div>
         </div>
      		     <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      		        <div class="form-group">
      		            <label>Remark</label>
						<s:textarea name="remark" id="referremark" cssClass="form-control setreferscroll"  rows="4" cols="5"/>
				   </div>
      		     </div>
      		     <%if(loginInfo.isPhysio()){ %>
      		      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      		        <div class="form-group">
      		            <label>Diagnosis</label>
						<s:textarea name="diagnosis" id="diagnosis" cssClass="form-control setreferscroll"  rows="4" cols="5"/>
				   </div>
      		     </div>
      		     <%} %>
      		</div>
     </div>
     <div class="modal-footer">
     	<button type="button" class="btn btn-primary"
							onclick="showsittingfollowuplist(<%=loginInfo.isPhysio() %>)" style="margin-top: 15px;">Save</button>
       </div>
     </div>
   </div>
</div>  

<!-- Edit Sitting --> 
<div id="editsitting" class="modal fade" role="dialog">

				<div class="modal-dialog modal-sm">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Sitting</h4>
							</div>
							
							<div class="modal-body">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

									 <s:hidden id="sittingClientid"></s:hidden>
      	                             <input type="hidden" id="opdid">
									
									<s:hidden id="id"></s:hidden>
									
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group" id="edit_sittingDeptDiv">
											<label>Department List </label><label class="text-danger">*</label>
											<s:select list="disciplineList" name='department' id='edit_dept'
												listKey="id" listValue="discipline" headerKey=""
												headerValue="All Department"
												onchange="setDepartmentsitting(this.value)"
												cssClass="form-control chosen-select"></s:select>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group" id="ed_div">
											<label>Select Sitting</label> <select name="sitting_id"
												id="edit_sittings" class="form-control chosen-select">
												<option value="0">Select</option>
											</select>
										</div>
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									    <div class="form-group" id="mased_div" >
        				                   <label>Procedure MasterList</label>
        				                      <select name="master_id" id="edit_master_id" class="form-control chosen-select">
						                      <option value="0">Select</option> 
						                     </select>
        								</div>
									
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group" id="pro_durediv">
											<label>Select Procedure</label> <select name="procedure_id"
												id="edit_procedure" class="form-control chosen-select">
												<option value="0">Select</option>
											</select>
										</div>
									</div>

                                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">        			
                                        <div class="form-group" id="edit_num" >
        				                     <label>Sitting No</label><label class="text-danger">*</label>
				                                <s:textfield id="si_no" name="sittingno" cssClass="form-control" 
					                             title="Enter Sitting No " placeholder="Enter Sitting No"/>
        	                            </div>
        	                        </div>
                                     
                                     
                                    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group">
											<label>Followup Date : </label>
											<s:textfield readonly="true" name="date" id="ed_sittingDate"
												placeholder="select Date" cssClass="form-control"
												theme="simple" />
										</div>
									</div>
							<%if(loginInfo.isLmh()){ %>	 
        	                   <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			                     <div class="form-group">
	      			                    <label>Sitting Date : </label>
	      			                     <s:textfield readonly="true" name="sittingbackdate" id="edit_sittingBackDate" placeholder="Select Date"
					                        cssClass="form-control" theme="simple" />
				                    </div>
                              </div>
                        <%}else{ %>
                             <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
      			                   <div class="form-group">
	      			                  <label>Sitting Date : </label>
	      			                      <s:textfield readonly="true" name="sittingbackdate" id="edit_sittingBackDate" placeholder="Select Date"
					                        cssClass="form-control" theme="simple" />
				                    </div>
                              </div>
                       <%} %>	
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="form-group">
											<label>Remark</label>
											<s:textarea name="remark" id="edit_remark"
												cssClass="form-control setreferscroll" rows="4" cols="5" />
										</div>
									</div>
									
								</div>
							</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									onclick="updatesittingfollowup()" style="margin-top: 15px;">Update</button>
							</div>
						</div>
					</div>
				</div>


<!--Change Department Code  -->
		<div class="modal fade" id="changedeoartment" tabindex="-1" role="dialog" style="line-height: 25px"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-md">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" style="margin-top: 0px !important;">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Refer To</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
						<label>Previous Department:</label><span id="predept"></span>
						<br>
						<label> Department List</label>
						<div id="depatylist"style="height: 109px;overflow: auto">
						
						</div>
						</div>
						<!-- <p style="color: red;">Note: For Multiple Select Press CTRL Button And Select Department </p> -->
						<input type="hidden" id="referselected">
						<div class="form-group ">
						<label>Remark</label>
						<s:textarea name="referremark" id="referrema" cssClass="form-control setreferscroll"  rows="5" cols="5"/>
						</div>
						 <div class="form-group" style="text-align: center">
						<a href="#" class="btn btn-primary" onclick="referdept();" id="refrbtn" style="margin-left: -41px;margin-top: 20px;">Submit</a>
						</div>
						
					</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>

<!--Department Confirm Popup  -->
<!-- Modal -->
<div class="modal fade" id="departmentconfirm" tabindex="-1" role="dialog" aria-labelledby="departmentconfirmLabel" aria-hidden="true">
  <div class="modal-dialog" role="document" style="width: 24%">
    <div class="modal-content" style="height: 148px;">
      <div class="modal-header" style="background-color: #15536E !important;">
        <h5 class="modal-title" id="exampleModalLabel" style="text-align: center;">Refer To Other Department</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -18px;">
        <input type="hidden" id="deptid">
        <input type="hidden" id="deptclientid">
        <input type="hidden" id="deptapt">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="text-align: center;">
      <i class="fa fa-warning" style="font-size:30px;color:red"></i>
       <h4>Do you want to Refer to Other Department</h4>
      </div>
      <div class="modal-footer" style="text-align: center;">
        <button type="button" class="btn newbtnyes" onclick="showconfirm()" style="margin-left: -51px;margin-top: 0px;">Yes</button>
        <button type="button" class="btn newbtnno" data-dismiss="modal" style="margin-top: 0px;">No&nbsp;</button>
        
      </div>
    </div>
  </div>
</div>



<!-- Modify Popup  -->
<div class="modal fade" id="modifypopup" tabindex="-1" role="dialog" aria-labelledby="departmentconfirmLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header" style="background-color: #1a79bd !important;">
        <h5 class="modal-title" id="exampleModalLabel" style="text-align: center;">Modify Department</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -18px;">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="line-height: 30px;">
					<!-- <input type="hidden" id="modifyappid">
					<input type="hidden" id="modifyappclientid"> -->
					
						<div class="col-lg-12">
							<div class="col-lg-4" style="padding: 0;">
								<label>Patient Name:</label>
							</div>
							<div class="col-lg-8" style="padding: 0;">
								<label id="modifyptname"></label>
							</div>
						</div>
						<div class="col-lg-12">
							<div class="col-lg-4" style="padding: 0;">
								<label>Booking Time:</label>
							</div>
							<div class="col-lg-8" style="padding: 0;">
								<label id="modifybooktime"></label>
							</div>
						</div>
						<div class="col-lg-12">
							<div class="col-lg-4" style="padding: 0;">
								<label>Department:</label>
							</div>
							<div class="col-lg-8" id="modifyselect" style="padding: 0;">
								
							</div>
						</div>
						
						
						
      </div>
      <div class="modal-footer" style="text-align: center;">
      <input type="button" value="Modify" class="btn btn-primary" style="margin-top: 10px;" onclick="updatemodifyappointment()">
      </div>
    </div>
  </div>
</div>



<!--Doctor Confirm Popup  -->
<!-- Modal -->
<div class="modal fade" id="doctorconfirm" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
      <div class="modal-header" style="background-color: #15536E !important;">
        <h5 class="modal-title" id="exampleModalLabel" style="text-align: center;">Book Appointment</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -18px;">
        <input type="hidden" id="docid">
        <input type="hidden" id="docclientid">
        <input type="hidden" id="docapt">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
	      <div style="text-align: center;">
			       <h4>Do you want to Assign Doctor</h4>
	       </div><br>
	       
	   <div style="margin-left: 10px;">    
	   <h3 style="font-size: 13px;color: black;">Primary Doctor : <span id="primarydocname" style="font-weight: 500;"> </span></h3>
			   <h3 style="font-size: 13px;color: black;">Secondary Doctor's : </h3>   
		       <div class="form-group setheight" id="secondarydoc">
			
		      </div>
		      <input type="hidden" id="selectedsecondary">
      </div>
      
      <div class="modal-footer" style="text-align: center;">
        <button type="button" class="btn btn-primary" onclick="opdconfirm()" >Yes</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="setdefaultdr()">No&nbsp;</button>
        
      </div>
    </div>
  </div>
</div>
</div>


 <!-- Add Follow Up Modal  -->
<div id="addfollowup" class="modal fade" role="dialog">
  <div class="modal-dialog modal-dialog-centered modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add Follow Up</h4>
      </div>
      <div class="modal-body">
       <s:form action="addfollowupFinder" id="addfollowup" theme="simple">
      	
      	<s:hidden name="id" id="followupid"></s:hidden>
      	<s:hidden name="dept_id" id="dept_id"></s:hidden>
      	<s:hidden name="client_id" id="client_id"></s:hidden>
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      		<label>Date</label><span style="color:red">*</span><br>
      		<s:textfield readonly="true" name="followupDate" id="followupDate" placeholder="Select Date" cssClass="form-control" theme="simple" /><br><br>
      	</div>
        <%-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      		<label>Procedure</label><span style="color:red">*</span><br>
      		<s:select list="additionalChargeListNew" listKey="id" listValue="name"  headerKey="0" headerValue="Select Procedure" name="procedure" id="procedure" cssClass="form-control"></s:select><br><br>
      	</div>  --%>
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      		<label>Procedure</label><span style="color:red">*</span><br>
      		 <select id="procedure" name="procedure" class="form-control chosen" >
      		 <option value="0">Select Procedure</option>
      		 </select><br><br>
      	</div>  
      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;margin-bottom: 10px">
      		<label>Follow Up Reason:</label><span style="color:red">*</span>
			<s:textarea  name="followupReason"  id="followupReason" cssClass="showToolTip form-control" 
					title="Follow Up Reason" placeholder="Enter Follow Up Reason"/><br>
      	</div>
      	
      <div class="modal-footer">
         <a href="#" class="btn btn-primary" onclick="saveFollowUp()" id="saveFollowUp">Submit</a> 
        <!--  <input type="submit" class="btn btn-primary"   value="Submit"> -->
      </div>  
      	
      </s:form>
      </div>
    </div>
   </div>
</div>      
<!-- End Follow Up --> 

	
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

<script>
    function myFunction() {
    	  
    	var $rows = $('#newopdbodyid tr');
    	$('#myInput').keyup(function() {
    	  var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase().split(' ');

    	  $rows.hide().filter(function() {
    	    var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
    	    var matchesSearch = true;
    	    $(val).each(function(index, value) {
    	      matchesSearch = (!matchesSearch) ? false : ~text.indexOf(value);
    	    });
    	    return matchesSearch;
    	  }).show();
    	});

    	}
    
    
</script>
<script src="toggale/js/slimScroll/jquery.slimscroll.min.js"></script>
 <script>
 $(function() {
		$('.setheight').slimScroll({
			height : '115px',
			railVisible : true,
			alwaysVisible : true
		});
	});
 $(function() {
		$('.setreferscroll').slimScroll({
			height : '115px',
			width : '100%',
			railVisible : true,
			alwaysVisible : true
		});
	});
 
    </script>


