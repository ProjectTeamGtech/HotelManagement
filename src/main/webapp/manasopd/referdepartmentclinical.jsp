<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<style>

 .table-hover>tbody>tr:hover>td, .table-hover>tbody>tr:hover>th {
    background-color: #c7e7e8;
}
</style>

<script type="text/javascript">
	
var todayDate = new Date().getDate();

 $(document).ready(function() {
	
	$("#currentDate").datepicker({
		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		//minDate : new Date(new Date().setDate(todayDate-6)),
		changeMonth : true,
		changeYear : true,
		maxDate: new Date(new Date().setDate(todayDate))
	});
	
}); 
</script>

<script type="text/javascript" src="manasopd/js/adjustdata.js"></script>
	<body>
		<div class="">
					<div class="row" style="padding: 0">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
							 	<h4>Refer & Assign Patient</h4>
							</div>
						</div>
					</div>
					
				<div class="row " style="padding-top: 15px;">
					<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
				<!--  -->
						<form action="referdepartmentclinicalPatientdata" id="mainform">
							<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
								<div class="col-md-4 col-lg-4 col-xs-12 col-sm-12">
									<s:textfield name="fromDate" id="currentDate" readonly="true" cssClass="form-control"></s:textfield>
								</div>
								<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12">
									<button type="submit" class="btn btn-primary">Go</button>
								</div>
							</div>
						</form>
					</div>	
					
					<div class="col-lg-8 col-md-8 col-xs-12 col-sm-12 topback2">
						<table class="table table-hover table-condensed table-bordered" style="border: black 1px solid;">
							<thead>
								<tr>
									<td>Department</td>
									<td style="text-align: center;">New Patient</td>
									<td style="text-align: center;">Old Patient</td>
									<td style="text-align: center;">Total Count</td>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="deptWiseCountList">
									<%-- <s:if test="totalPatientCount>0"> --%>
										<tr>
											<td><s:property value="discipline"/></td>
											<td style="text-align: center;"><s:property value="newPatientCount"/></td>
											<td style="text-align: center;"><s:property value="oldPatientCound"/></td>
											<td style="text-align: center;"><s:property value="totalPatientCount"/></td>
										</tr>
									<%-- </s:if> --%>
								</s:iterator>
							</tbody>
							<%-- <thead>
								<tr>
									<td>Total Patient</td>
									<td><s:property value="finalNewPatientCount"/></td>
									<td><s:property value="finalOldPatientCount"/></td>
									<td><s:property value="finalTotalPatientCount"/></td>
								</tr>
							</thead> --%>
						</table>
					</div>
					
					
				</div>
					
					
				<%int i=1; %>
					<div class="row">
						<div class="col-lg-12">
							<h3>Refer Doctor</h3>
								<div class="table-responsive">
									<table class="table table-hover table-condensed table-bordered">
										<thead style="text-align: center;">
									
								
											<tr>
												<td><input type="checkbox" onclick="selectallCheckBox(this.checked)">Serial No</td>
												<td>Booking Date/Time</td>
												<td>Referred Time/Date</td>
												<td>U H I D</td>
												<td>Patient Name</td>
												<td>Age/Gender</td>
												<td>Contact No</td>
												<td>Patient Category</td>
												<td>Allotted Department</td>
												<td>Referred From Dept</td>
												<td>Assigned Doctor</td>
											</tr>
											
										</thead>
										<tbody style="text-align: center;" id="newopdbodyid">
											<s:iterator value="departmentOPDList">
											<s:if test="drselected==1">
												<tr style="background-color: #f5dfbd">
											</s:if>
											<s:else>
											<tr >
											</s:else>	
													<input type="hidden" id="drselect<s:property value="id"/>" value="<s:property value="drselected"/>">	
													<input type="hidden" id="clientid<s:property value="id"/>" value="<s:property value="clientId"/>">
													<td>
													<input type="checkbox" class="muldrselect" id="chk<s:property value="id"/>" onchange="multipledrselect(this.value)" value="<s:property value="id"/>" style="margin: 0"> <%=i++%>
													</td>
													<td><s:property value="datetime"/></td>
													<td><s:property value="apptdate"/></td>
													<td><s:property value="abrivationid"/></td>
													<td><s:property value="clientName"/></td>
													<td><s:property value="agegender"/></td>
													<td><s:property value="mobno"/></td>
													<td><s:property value="patientcategory"/></td>
													<td><s:property value="departmentname"/></td>
													<td><s:property value="referredfromdept"/></td>
													<td><s:property value="doctorname"/></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									
							
							
							
								</div>
							</div>
						</div>
						<s:hidden name="userdepartment" id="userdepartment"/>
						<div class="row " style="padding-top: 10px;">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-bottom: 15px">
									<s:hidden name="fromDate" id="fromDate"></s:hidden>
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
										<label>Select Doctor</label>
										<s:select list="userList" id="diaryuser" name='diaryuser' listKey="id" listValue="diaryUser" headerKey="0" headerValue="Select Doctor" onchange="showdoctpopupNew(this.value)"  cssClass="form-control chosen-select"></s:select>
									</div>
									
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
									</div>
									
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
										<label>Select Department</label>
										<s:select list="disciplineList" id="dept" name='dept' listKey="id" listValue="discipline" headerKey="0" headerValue="Select Department" onchange="setdept(this.value)"  cssClass="form-control chosen-select"></s:select>
									</div>
									
								<s:hidden name="date" id="date"/>	
							</div>	
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-bottom: 15px">
									
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
										<a href="#" onclick="showconfirmpopup()" class="btn btn-primary">Assign Doctor</a>
									</div>
									
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
									</div>
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
									</div>
									
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
										<!-- <a href="#" onclick="this.disabled=true;refertootherdepartment()" class="btn btn-primary">Refer Department</a> -->
										<button onclick="refertootherdepartment()" id="referdeptbtn" class="btn btn-primary">Refer Department</button>
									</div>
									<input type="hidden" id="referselected" >
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
	        <button type="button" class="btn btn-primary" id="opdyes" onclick="opdconfirmfake()" >Yes</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="setdefaultdr()">No&nbsp;</button>
	        
	      </div>
	    </div>
	  </div>
  </div>
</div>
		
		 <!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93)" id="newloaderPopup" aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->
		
	</body>
</html>