<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page import="java.util.ArrayList"%>

<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import = "java.util.Date"%>




<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>
<%if(loginfo.getUserType()==5) {%>
<jsp:include page="/dashboard/ndbmobile.jsp"/>
<%}else{ %>
<jsp:include page="/dashboard/ndb.jsp"/>
<%} %>
<script language="Javascript" type="text/javascript">

$(document).ready(function() { 
    
		//alert("hello");
    

	  
<%-- 	  <%String tableid="example";%>
			<%if(loginfo.getUserType()==5){
				tableid="";
			%>
			actionType=5;
			document.getElementById("actiontype").value = "5";
			puresevaclientname='<%=loginfo.getFullname()%>';
			 puresevaclientid=<%=loginfo.getPuresevaclientid()%>;
			var uhid=0;
			  var uhid=document.getElementById("mobhid").value;
			 document.getElementById("oriuhid").value=document.getElementById("mobhid").value; 
			 var dept='<%=loginfo.getDept()%>';
			if(uhid==0){ 
				jQuery.noConflict(); 
			$( "#confirmuhid" ).modal( "show" );
			 }else{
				 document.getElementById("ddpp").style.visibility = "visible";
			 }
				 
			
			<%}%>
 --%>			
<%-- 			<%if(loginfo.getTokenstatus()==1){%> --%>
			tokendisplay();
			<%-- <%}%> --%>
			
});

</script>

<style>  
    input[type="radio"]{
      display: none;
    }
    input[type="radio"] + label span {
      display: inline-block;
      width: 17px;
      height: 17px;
      background: transparent;
      vertical-align: middle;
      border: 2px solid #f56;
      border-radius: 50%;
      padding: 2px;
      margin:0 5px;
    }
    input[type="radio"]:checked + label span {
      width: 17px;
      height: 17px;
      background: #f56;
      background-clip: content-box;
    }  
  </style>
<style>
.b {
  list-style-type: square !important;
}
.tablecls{
  border-collapse: separate;
  border-spacing: 6px;
}
.tdavail{
	color: white;
    background-color: #539dde;
    height: 45px !important;
	font-family: "Times New Roman", Times, serif;
	font-size: 19px;
	font-weight: 600;
}
.tdbooked{
	color: white;
    background-color: black;
    height: 45px !important;
	font-family: "Times New Roman", Times, serif;
	font-size: 19px;
	font-weight: 600;
}

td{
text-align: center !important;
}
th{
text-align: center !important;
}
table{
width: 100%;
}
.labelhigh{
    background-color: black;
    color: white;
    height: 20px;
    font-size: 14px;
    
}
</style>



<link href="diarymanagement/css/popupstyle.css" rel="stylesheet" type="text/css" />

<link href="diarymanagement/css/subModal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>
<script type="text/javascript" src="diarymanagement/js/tokendisplay.js"></script>




<script type="text/javascript" src="diarymanagement/js/dayUsers.js"></script>
<script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script>
<script type="text/javascript" src="diarymanagement/js/notavailableslotpopupscript.js"></script>

<input type="hidden" name="slotId" id="slotId" />
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
		 <%String drids=(String)session.getAttribute("drids"); %>
		<input type="hidden" id="drids" value="<%=drids%>"> 
		<span class="error"><s:actionerror id="server_side_error"/></span>
			<%if(loginfo.getUserType()==5){ %>
			<br><br><br><br>
			<div>
			<label class="labelhigh" id="ddpp" style="margin-left: 55px; visibility: hidden;">Select Department</label>
			<label class="labelhigh" id="dddd" style="margin-left: 133px; visibility: hidden;">Select Doctor</label>
			<label class="labelhigh"  id="ddtt" style="margin-left: 113px;visibility: hidden;">Select Date</label>
			</div>
			<div style="text-align: center;">
			<div>
			<strong><span  style="font-size: 15px;"><%=loginfo.getFullname() %></span></strong>&emsp;
			<label>Appointment Log</label>
			<input onclick="showdisplaynewopd(0)" type="button" value="History" class="btn btn-primary"/>
			<input onclick="showdisplaynewopd(1)" type="button" value="Today" class="btn btn-primary"/>
			<input onclick="showdisplaynewopd(2)" type="button" value="Future" class="btn btn-primary"/>
			</div>
			</div>
			<%} %>
			<%if(loginfo.getTokenstatus()==1){ %>
			<br><br>
			<div style="text-align: center;padding-top: 40px">
			<div>
			<h2 id="headdr"></h2>
			</div>
			</div>
			<%} %>
			<table width="100%" cellpadding="0" cellspacing="0" class="timer-table hidden" id="tab1">
			
			<col class="tiemsl"/>
			<col width="60%"/>
			
			
				<tr>
					<th style="background-color: ">
						<input type="radio" id="rd0" name="rdapmt" 
						onclick="showpartapmt(this.id,0)"/> (All)
					</th>
					<th id="wn0" style="text-align: center;"><s:property value="dayWeekName"/></th>
					
				</tr>
				
				<% 
				
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
				<%-- <%for(int i=1;i<=clinicendtime;i++){ %>
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
					
				<% }%> --%>
			</table>
			
			
			<!-- new opd display -->
			
			<table class="table-bordered  table-condensed width-full  xlstable" style="font-size: 16px;" id="example">
				
				
					<!-- <colgroup><col width="10%">
					<col width="10%">
					<col width="5%">
					<col width="5%">
					<col width="20%">
					<col width="30%">
					
					
				
				
				
					</colgroup> --><thead>
					<%if(loginfo.getUserType()!=5){ %>
						<tr>
						
					
							<th style="width:2%; !important">Sr.</th>
							<th style="width:10%; !important">Booking Date/Time</th>
							<th style="width:10%; !important">Appointment Date/Time</th>
							<th style="width:3%; !important">Duration</th> 
							<th style="width:5%; !important">UHID</th>
							<th style="width:15%; !important"><%=loginfo.getPatientname_field() %></th>
							<th style="width:5%; !important">Token No.</th>
							<th style="width:8%; !important"></th>
							<th style="width:5%; !important">Age/Gender</th>
							<th style="width:15%; !important">Appointment Name</th>
							<th style="width:15%; !important">Patient isseen</th>
							<th style="width:15%; !important">Doctor Seen</th>
                            

							
							
							
						</tr>
						<%}else{ %>
						<tr>
						
					
							<th style="width:2%; !important">Sr.</th>
							<th style="width:6%; !important">Booking Date/Time</th>
							<th style="width:6%; !important">Appointment Date/Time</th>
							<th style="width:6%; !important;text-align: center;">Doctor Name</th>
							<th style="width:2%; !important">Payment</th>
							<th style="width:2%; !important">Video Call</th>
							<th style="width:2%; !important">Remark</th>
							<th style="width:2%; !important">Attachment</th>
							<th style="width:2%; !important">Cancel</th>
							<th style="width:3%; !important">Payment Status</th>
							<th style="width:3%; !important">Status</th>
							<!-- <th style="width:8%;"></th> -->
							
							
							
						</tr>
						
						<%} %>
					</thead>
					<tbody id="newopdbodyid">
					
					</tbody>
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
<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	


<div class="modal fade" id="confirmuhid" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-sm" style="width: 400px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Registration/Login</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<input type="hidden" name="cancelinv" value="1">
						<input type="hidden" id="diffyear"> 
						<div class="form-group" id="rtb">
						<!-- &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						<input type="radio" id="newpatient" name="patient" value="new" onclick="showinstruct(this.value)">New Patient&emsp;&emsp;&emsp; &nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						<input type="radio" id="oldpatient" name="patient" value="old" onclick="showinstruct(this.value)">Old Patient
						 -->
						&emsp;&emsp;<input id="newpatient" type="radio" name="patient" value="new" onclick="showdob()">
    <label for="newpatient"><span></span>New Patient</label>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

    <input id="oldpatient" type="radio" name="patient" value="old" onclick="showdob()">
    <label for="oldpatient"><span></span>Existing Patient</label>
						</div>
						<div class="form-group hidden" id="cdob">
						<label>Enter Date of Birth <span class="text-danger">*</span></label>
						<%-- <s:textfield readonly="true" name="initialdob" id="initialdob"
					cssClass="form-control" theme="simple" style="width:100%;" ></s:textfield> --%>
					<input type="text" id="initialdob" name="initialdob"
									class="form-control showToolTip"
									  readonly="readonly"
									data-toggle="tooltip" onchange="getAgendDays4(this.value)" style="width: 23%"/>&emsp;&emsp;
									<label>Age<span class="text-danger">*</span></label>
									<input type="text" name="age" value="0" id="age" class="form-control " 
									onchange="allnumeric4(this.value)" style="width: 16%">
									&emsp;<strong>(<label id="majorminor" style="font-weight: 700;font-size: 13px;"></label>)</strong>
						</div><br>
						 <div class="form-group" >
						 	<div id="new" class="hidden">
								<label><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMAP etc.)</b></label>								 
								<label><span class="text-danger"><b>1. Compulsory - ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label><b>2. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label><b>3. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="agenew" class="hidden">
								<label><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMAP etc.)</b></label>								 
								<label><span class="text-danger"><b>1. Compulsory - ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label><span class="text-danger"><b>2. Compulsory -  Parent's or Guardian ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label><b>3. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label><b>4. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="new1" class="hidden">
								<label><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMAP etc.)</b></label>
								<label><span class="text-danger"><b>1. Compulsory -  Parent's or Guardian ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>								 
								<label><b>2. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label><b>3. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="old" class="hidden">
								<label><b>Please keep following ready for Login- (Image Format in JPEG, PNG, BITMAP etc.)</b></label>								 
								<label><b>1. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label><b>2. Optional - Recent Photograph</b></label>
							</div>
						</div>
						
						<div class="form-group hidden" style="padding-bottom: 10px;" id="mobdiv">
							<label>Enter Mobile No. <span class="text-danger">*</span></label>
							<input type="text" class="form-control" maxlength="10"   id="entermob"/>
							
						</div>
 
						 
						
						<div class="form-group" style="text-align: center;">
						<button type="button" class="btn btn-success" onclick="checkuhidexistornot()" id="confirmmob" style="visibility:hidden;margin-right: -120px">Login</button>
						<button type="button" class="btn btn-primary" onclick="confirmpopupreg()" id="newregpatient" style="visibility:hidden;margin-left: 40px;">Registration</button>
						</div>
						
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="availablepatient" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Available Patient Name With This Mobile Number</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
				<table class="my-table xlstable">
				<thead>
				  <tr>
				  	<th>UHID</th>
				    <th>Name</th>
				  </tr>
				  </thead>
				  <tbody id="avalablept">
				  </tbody>
				  </table>					
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="checkaval" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="overflow: auto;">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" style="text-align: center;font-size: large;font-weight: 700;">Available Slots<br>Doctor Name : <span id="drnamewithqul"></span>&emsp;&emsp;Date : <span id="selecteddiarydate"></span></h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<input type="hidden" name="cancelinv" value="1">
						<div class="form-group">
							<!-- <ul id="TimingSlot" class=""></ul> -->
							<table id="avalslot" class="tablecls">
							
							</table>
							
						</div>
 
						 
						
						<div class="form-group">
							<!-- <button type="button" class="btn btn-primary" onclick="confirm()" id="confirmmob" style="float: left;" disabled="disabled">Confirm</button> -->
						<!-- <button type="button" class="btn btn-primary" onclick="departmentconfirm()" id="newregpatient" style="float: right;" >OK</button> -->
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












<!--pure seva client details popup  -->
		<%
			LoginInfo info = LoginHelper.getLoginInfo(request);
		%>
		<div class="modal fade" id="puresevaclientdetailsdiv" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" style="width: 80%">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Please confirm your
							details</h4>
					</div>
					<s:form id="upload" method="post" action="upopddocEmr"
							enctype="multipart/form-data" theme="simple">
					<div class="modal-body">
						<div class="col-lg-12" style="padding-bottom: 10px;background-color: lavenderblush;">
							<div id="registrationguide1" class="hidden" style="text-align: center;">
								<label><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMEP etc.)</b></label>								 
								<label><span class="text-danger"><b>1. Compulsory - ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label><b>2. Optional - Medical Record (Previous Report's, Prescription)</b></label>	
								<label><b>3. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="registrationguide2" class="hidden" style="text-align: center;">
								<label><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMEP etc.)</b></label>									 
								<label><span class="text-danger"><b>1. Compulsory - ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>
								<label><span class="text-danger"><b>2. Compulsory -  Parent's or Guardian ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label>	
								<label><b>3. Optional - Medical Record (Previous Report's, Prescription)</b></label>
								<label><b>4. Optional - Recent Photograph</b></label>
							</div>
							
							<div id="registrationguide3" class="hidden" style="text-align: center;">
								<label><b>Please keep following ready for Registration- (Image Format in JPEG, PNG, BITMEP etc.)</b></label>	
								<label><span class="text-danger"><b>1. Compulsory -  Parent's or Guardian ID Proof (Aadhaar Card, PAN Card, Driving license)</b></span></label> 
								<label><b>2. Optional - Medical Record (Previous Report's, Prescription)</b></label>	
								<label><b>3. Optional - Recent Photograph</b></label>
							</div>
						</div>
						<div class="col-lg-12" style="padding-bottom: 10px;">
						<div class="col-lg-3">
								
						</div>
						<div class="col-lg-3">
						<label><b>Document Type:</b></label>
							<select class='form-control' id='docType'>
								<option value="Adhaar">Adhaar</option>
								<option value="Pan Card">Pan Card</option>
								<option value="Driving License">Driving License</option>
							</select>
						</div>	
						
						<div class="col-lg-3">
						<input type="hidden" id='docimg'>
						<label><b>Patient's Identity Document</b></label><label><span class="text-danger" id="patientidentiyspan">*</span></label>
							<div id="drop" style="background-color: #efefef;">
								Document  <a>Upload</a>
								<s:file name="fileUpload" theme="simple"  accept="image/*" required="true" onchange="getf(this.value,'docimg')">
								</s:file>
							</div>
							<span id="docimg1"></span>
						</div>
						
						<div class="col-lg-3">
						<s:hidden name='hiddenval' id='profileimg'></s:hidden>
						<label><b>Patient's Profile Photo</b></label>
							<span id="filename" style="color: white"></span>
							<div id="drop" style="background-color: #efefef;">
								Photo <a>Upload</a>
								<s:file name="files" theme="simple" id='' accept="image/*" required="true" onchange="getf(this.value,'profileimg')">
								</s:file>
							</div>
							<span id="profileimg1"></span>
						</div>
						
						
						
						
						<!-- <div class="col-lg-3">
							<ul class="popmodals123">
								The file uploads will be shown here
							</ul>
						</div> -->
						
						
						</div>
						
						<div class="col-lg-12" style="padding-top: 20px;background-color: lavender;" id="relativedetailssdiv">
							<div class="col-lg-3">
								<h5><b>Details of Patient's Parent / Guardian: </b></h5> 
							</div>
							<div class="col-lg-3">
								<label><b>Patient's Parent / Guardian Name:</b></label><label><span class="text-danger" id="parentguardiannamespan">*</span></label> 
								<input type="text" id='relativename123' class='form-control' >
							</div>
							
							<div class="col-lg-3">
								<label><b>Parent / Guardian Contact Number:</b></label><label><span class="text-danger" id="parentguardiancontactspan">*</span></label>
								<input type="number" maxlength="10" id='relativecontact123' class='form-control' >
							</div>
							
							<div class="col-lg-3">
								<label><b>Relationship with Patient's:</b></label><label><span class="text-danger" id="parentguardianrelationspan">*</span></label>
								<select id='relativerelation123' class='form-control'>
									<option value="">Select Relation</option>
									<option value="Mother">Mother</option>
									<option value="Father">Father</option>
									<option value="Son">Son</option>
									<option value="Daughter">Daughter</option>
									<option value="Brother">Brother</option>
									<option value="Sister">Sister</option>
									<option value="Uncle">Uncle</option>
									<option value="Aunt">Aunt</option>
									<option value="Cousin">Cousin</option>
									<option value="Husband">Husband</option>
									<option value="Wife">Wife</option>
									<option value="Nephew">Nephew</option>
									<option value="Niece">Niece</option>
									<option value="Grandson">Grandson</option>
									<option value="Grand daughter">Grand daughter</option>
									<option value="Grand Father">Grand Father</option>
									<option value="Grand Mother">Grand Mother</option>
									<option value="Other">Other</option>
								</select>
							</div>
							
							
						</div>
						<div class="col-lg-12" style="padding-top: 20px;background-color: lavender;" id="relativedetailsdocdiv">
							<div class="col-lg-3">
								
							</div>
							<div class="col-lg-3">
								<input type="hidden" id='relativedocimg'>
								<label><b>Parent / Guardian Identity Document:</b></label><label><span class="text-danger" id="relativeidentiyspan">*</span></label>
								<div id="drop" style="background-color: #efefef;">
									Document  <a>Upload</a>
									<s:file name="relativefiles" theme="simple"  accept="image/*" required="true" onchange="getf(this.value,'relativedocimg')">
									</s:file>
								</div>
								<span id="relativedocimg1"></span>
							</div>
							
							<div class="col-lg-3">
								
							</div>
							
							<div class="col-lg-3">
								
							</div>
							
							
						</div>
						
						<br>
						
						
						<div class="hidden" id="hiddenpuresevareg">
						<input type="hidden" id="puresevadobyear">
						<input type="hidden" id="puresevadobmonth">
						<input type="hidden" id="puresevadobdays">
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label><b>Email ID:</b></label>
							</div>
							<div class="col-lg-9">
							
							<input type="hidden" name="puruhid" id="puruhid" value="<%=loginfo.getUhid() %>">

								<input type="text" id="pureemail" name="pureemail"
									class="form-control showToolTip"
									onblur=""
									data-toggle="tooltip " value="<%=info.getEmail()%>" />
							</div>
						</div>
						<br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label><b>Salutation:</b></label>
							</div>
							<div class="col-lg-9">
								<s:select id="title123" name="title"  list="initialList" title="Select" theme="simple" cssClass="form-control" headerValue="Select" headerKey="0" cssStyle="width:100%;"/>
							</div>
						</div><br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label><b>Patient's First / Given Name:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9">
								<input type="text" id="purefname" name="purefname"
									class="form-control showToolTip" 
									data-toggle="tooltip"
									value="<%=info.getFirstName()%>" />
							</div>
						</div>
						 
						<br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label><b>Patient's Last / Family Name:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9">

								<input type="text" id="purelname" name="purelname"
									class="form-control showToolTip" 
									data-toggle="tooltip"
									value="<%=info.getLastName()%>" />
							</div>
						</div><br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label><b>Gender:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9">

								<s:select id="gender123" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip "
														data-toggle="tooltip"   headerKey="0" headerValue="Select"/>
							</div>
						</div>
							
						<br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label><b>Patient's Mobile Number:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9">

								<input type="number" id="puremob" name="puremob"
									class="form-control showToolTip"
									 
									data-toggle="tooltip" maxlength="10" value="<%=info.getMob()%>"/>
							</div>
						</div>
						<br>
						<div class="col-lg-12">
							<div class="col-lg-3">
								<label><b>Patient's Date of Birth:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-3">
								<input type="text" id="puredob" name="puredob"
									class="form-control showToolTip"
									  readonly="readonly"
									data-toggle="tooltip" value="<%=info.getDob()%>"  onchange="getAgendDays2(this.value)"/>
							</div>
							<div class="col-lg-1">
								<label><b>Age:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-1">
								
								<input type="text" id="pureage" name="pureage"
									class="form-control showToolTip"
									data-toggle="tooltip" onchange="allnumeric5(this.value)"/>
							</div>
							<div class="col-lg-3" id='dobe'>
							</div>
						</div>
						
						<div class="col-lg-12" style="padding-top: 10px;">
							<div class="col-lg-3">
								<label><b>Patient's Address:</b></label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-9">
								<input type="text" id='address123' class='form-control'>
							</div>
							
							
							
						</div>
						<div class="col-lg-12" style="padding-top: 10px;">
							
							<div class="col-lg-3">
							</div>
							
							<div class="col-lg-3">
							<label><b>Country:</b></label><label><span class="text-danger">*</span></label>
							<s:select list="#{'India' : 'India'} " cssClass='form-control chosen-select' ></s:select>
							</div>
							
							
							<div class="col-lg-3">
							<label><b>State:</b></label><label><span class="text-danger">*</span></label>
							<span id='statediv2'>
								<s:select list="statelist" listKey="name" listValue="name" cssClass='form-control chosen-select' id='state123' onchange="getCitiesajax2(this.value)"></s:select>
							</span>
							</div>
							
							<div class="col-lg-3">
							<label><b>City / District:</b></label><label><span class="text-danger">*</span></label>
							<span id='citydiv2'>
								<s:select list="citylist" listKey="city" listValue="city" cssClass='form-control chosen-select' id='city123' onchange="getStateAjaxnew2(this.value)"></s:select>
							</span>
							</div>
							
							<input type="hidden" id="uploadhideul">
							
							
						</div>
						
						<div class="col-lg-12" style="padding-top: 10px;">
						
							<div class="col-lg-3">
							</div>
							
							<div class="col-lg-3">
							<label><b>Village / Town:</b></label><label><span class="text-danger">*</span></label>
								<input type="text" id='town123' class='form-control'>
							</div>
							
							
							<div class="col-lg-3">
								<label><b>Postal Code (Zip / Pin):</b></label>
								<input type="number" id='pincode123' maxlength="6" class='form-control'>
							</div>
							
						</div>
						
						
						
						</div>
						
						
					</div>
					</s:form>
					<div class="modal-footer" style="text-align: center !important;padding-top: 20px ">
						<button type="button"  class="btn btn-primary"
							onclick="confirm1()">Submit</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>





<!--confirm video request  -->
<div class="modal fade" id="confirmviddr" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Do you want to schedule your Video Call</h4>
			</div>
            <div class="modal-body">
            <input type="hidden" id="selectedcid">
            <input type="hidden" id="selectedappid">
            <strong><label></label></strong>
            <div id="doctornotes" style="display: none;">
            <textarea rows="5" cols="40" id="drrejectrem"></textarea>
            </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="setvideobydr()" id="yesbtnid">Yes Schedule</button>
                <button type="button" class="btn btn-default" onclick="confirmnotes()" id="nobtnid">No,Remark</button>
            </div>
        </div>
    </div>
</div>




<div class="modal fade" id="remarkbyhosp" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Remark</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<strong><label id="remm1"></label></strong><br>
						<strong><label id="remm2"></label></strong><br>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="showdrprofile" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Dr Profile</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<strong>Name: <label id="drname"></label></strong><br>
						<strong>Qualification: <label id="drqual"></label></strong><br>
						<strong>Speciality: <label id="despec"></label></strong><br>
						<strong>Registration Number: <label id="drreg"></label></strong><br>
						<strong>Phone Number: <label id="drphone"></label></strong><br>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>


<input type="hidden" id="oriuhid"/>
<input type="hidden" id="oriemail"/>

<input type="hidden" id="clinicid" value="<%=loginfo.getClinicUserid()%>"/>
 <input type="hidden" id="linkaddress" value="<%=loginfo.getLinkaddress()%>"/>
    <script>
    
   /*  document.onreadystatechange = function(){
        if (document.readyState === "complete") {
        	tokendisplay();
        }
    }    */

    
    var table;
    $(document).ready(function() {
    	
    	$("#commencingmve").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange : yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});

    	
    	openedb = 'opd';
    	isnewopd = 1;
    	showdisplaynewopd();
    	
         table = $('#example').DataTable( {
            lengthChange: false,
            buttons: [ 'colvis' ]
        	
        }
       );
      table.buttons().container()
            .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
       
        
         } );

    function bootstrapplugin(){
    	/*  table = $('#example').DataTable( {
             lengthChange: false,
             buttons: [ 'colvis' ]
         	
         } );
    	    table.buttons().container()
    	        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
    	    */
    	
    }



    </script>
    <script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
	
		
<%-- <jsp:include page="/popupdialog/dialog/commonPopupDialog.jsp"/> --%> 
<jsp:include page="/popupdialog/dialog/newcommonpopup.jsp"/>









<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>	