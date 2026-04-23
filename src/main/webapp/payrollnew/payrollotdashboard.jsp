<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <SCRIPT type="text/javascript" src="inventory/js/addproduct.js"></SCRIPT>
<SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
<script type="text/javascript" src="common/js/pagination.js"></script>
<SCRIPT type="text/javascript" src="payroll/js/payrollvalidatation.js"></SCRIPT>
<script type="text/javascript">
    function showmenu(val){
    	 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right show"; 
    	 setTimeout(function() {
    		 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right"; 
    		}, alertmsgduration);
    }
    
   
    </script>
      <SCRIPT type="text/javascript">
  
     window.onload=function(){
     		
     		/* document.getElementById("leaveemp").className="active";	
     		document.getElementById("empmenu").className="subdrop";	 */
     		
     		
     };
    /*  $(document).ready(function() {
     $('#leavetodate').datetimepicker({
			format: 'DD/MM/YYYY',
			
		}).on('dp.change', function() {
			getdiffirencedays();
    	});
    
     });
     $(document).ready(function() {
         $('#toleave').datetimepicker({
    			format: 'DD/MM/YYYY',
    			
    		}).on('dp.change', function() {
    			getdiffirencedaysedit();
        	});
        
         }); */
  </SCRIPT>
        <title>Leaves - HRMS admin template</title>
			
				<!-- Page Content -->
                <div class="content container-fluid">
                <%if(loginInfo.isPayrollaccess()){ %>
							<s:form action="payrollotdashboardPayrollDashBoard">
					<div class="row filter-row">
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<div class="form-group form-focus">
								<s:textfield cssClass="form-control floating" name="empname" theme="simple"/>
								<label class="focus-label">Employee Name</label>
							</div>
					   </div>
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12"> 
							<div class="form-group form-focus select-focus" style="width: 81%;">
							<s:select headerKey=""  cssStyle="width:90%" cssClass="select floating" list="#{'':'-- Select --', '0':'New','1':'Approved HOD','2':'Approved HR','3':'Rejected'}" name="otstatus" theme="simple"/>
							</div>
					   </div>
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<div class="form-group form-focus">
								<div class="cal-icon">
									<s:textfield  name="fromdate" id="fromdate"
					cssClass="form-control floating datetimepicker" theme="simple" style="width:100%;" placeholder="from date"></s:textfield>
								</div>
								<label class="focus-label">From</label>
							</div>
						</div>
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<div class="form-group form-focus">
								<div class="cal-icon">
									<s:textfield  name="todate" id="todate"
					cssClass="form-control floating datetimepicker" theme="simple" style="width:100%;" placeholder="to date"></s:textfield>
								</div>
								<label class="focus-label">To</label>
							</div>
						</div>
					   <div class="col-sm-3 col-md-3 col-lg-3 col-xl-2 col-6">  
							<!-- <a href="#" class="btn btn-success btn-block"> Search </a>   -->
							<%-- <s:submit value="Search" cssClass="btn btn-success btn-block"/> --%>
							<input type="submit" value="Search" class="btn btn-success btn-block">
					   </div>     
                    </div>
                    </s:form>
                    <%} %>
					<!-- Page Title -->
					<div class="row">
						<div class="col-sm-8 col-6">
							<h4 class="page-title">OT Dashboard</h4>
						</div>
						<div class="col-sm-4 col-6 text-right m-b-30">
							<a href="#" class="btn add-btn" data-toggle="modal" onclick="currenttime()"><i class="fa fa-plus"></i> Request OT</a>
							 <!-- <a href="#" class="btn add-btn" data-toggle="modal" onclick="showPosition()"><i class="fa fa-plus"></i> Location</a>  -->
						</div>
					</div>
					<!-- /Page Title -->
					
					<div class="row">
						<div class="col-md-12">
						 <div id="embedMap">
        <!--Google map will be embedded here-->
    </div>
							 <div class="table table-striped custom-table mb-0 datatable">
								<table class="table table-striped custom-table mb-0 datatable">
									<thead>
										<tr>
											<th>Name</th>
											<th style="width: 10%">From Time</th>
											<th style="width: 10%">To Time</th>
											<th>No of hour</th>
											<th>Requset Remark</th>
											<th class="text-center">Status</th>
											<th>Approve/Reject HOD</th>
											<th>Approve/Reject Remark</th>
											<th>Approve/Reject HR</th>
											<th>Approve/Reject Remark</th>
											<th class="text-right">Actions</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="leaveList">
										<tr>
											<td><s:property value="name" /></td>
											<td><s:property value="fromtime" /></td>
											<td><s:property value="totime" /></td>
											<td><s:property	value="time"/></td>
											<td><s:property	value="Requestremark"/></td>
											<td class="text-center">
											<s:if test="status==0">
              					   			Requested
              					   			</s:if>
              					   			 <s:elseif test="status==1">
              					   			APPROVED BY HOD</s:elseif> 
              					   			<s:elseif test="status==2">
              					   			APPROVED BY HR</s:elseif> 
              					   			<s:elseif test="status==3">
              					   			REJECTED
              					   			</s:elseif></td>
											<td class="text-center">
											<s:property	value="Approveby1"/>
											</td>
											<td class="text-center">
											<s:property	value="Approveremark1"/>
											</td>
											<td class="text-center">
											<s:property	value="Approveby2"/>
											</td>
											<td class="text-center">
											<s:property	value="Approveremark2"/>
											</td>
											<td class="text-center">
											<%if(!loginInfo.isPayrollaccess()){ %>
											<s:if test="status==0">
												<div class="dropdown dropdown-action">
													<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onclick="showmenu(<s:property value="id"/>)"><i class="material-icons">more_vert</i></a>
													<div class="dropdown-menu dropdown-menu-right"id="showmenu<s:property value="id"/>">
														<a class="dropdown-item" href="#" onclick="geteditot(<s:property value='id'/>)"><i class="fa fa-pencil m-r-5" ></i> Edit</a>
														 <a class="dropdown-item" href="#" data-toggle="modal" onclick="deleteleavepop(<s:property value="id"/>)"><i class="fa fa-trash-o m-r-5"></i> Delete</a> 
													</div>
												</div>
												</s:if>
												<s:elseif test="status==1">
												APPROVED BY HOD
												</s:elseif>
												<s:elseif test="status==2">
												APPROVED BY HR
												</s:elseif>
												<s:else>
												REJECTED
												</s:else>
												<%}else{ %>
												<s:if test="status==0">
												<a href="#" onclick="aproveot(<s:property value="id"/>,1,0)"><i class="fa fa-dot-circle-o text-purple"></i>Requested</a>
												</s:if>
												<s:elseif test="status==1">
              					   				<a href="#" onclick="aproveot(<s:property value="id"/>,2,1)"><i class="fa fa-dot-circle-o text-purple"></i>APPROVED BY HOD</a>
              					   				</s:elseif> 
              					   				<s:elseif test="status==2">
              					   				APPROVED BY HR</s:elseif> 
              					   				<s:elseif test="status==3">
              					   				REJECTED
              					   				</s:elseif></td>
												<%} %>
											</td>
										</tr>
										</s:iterator>
									</tbody>
								</table>
							</div> 
						</div>
					</div>
                </div>
               
               
				<!-- /Page Content -->
				<s:form action="payrollotdashboardPayrollDashBoard" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
							     <s:hidden name="empname"></s:hidden>
							     <s:hidden name="fromdate"></s:hidden>
							      <s:hidden name="todate"></s:hidden>
							      <s:hidden name="otstatus"></s:hidden>
								<div class="row" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<s:include value="/common/pages/pagination.jsp"></s:include>
								</div>
							</s:form>
				<!-- Add Leave Modal -->
				<div id="add_otrequest" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Add OT Request</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<s:form action="saveotPayrollDashBoard" id="otform">
								<s:hidden name="status"/>
								<input type="hidden" name="allleaveid" id="allleaveid">
									<%-- <div class="form-group">
										<label>Leave Type <span class="text-danger">*</span></label>
										<select class="select" name="leave_type" id="leavetype">
											<option value="0">Select Leave Type</option>
											<option value="1">Casual Leave</option>
											<option value="2">Medical Leave</option>
											<option value="3">Loss of Pay</option>
										</select>
									</div> --%>
									<div class="form-group">
										<label>From Time <span class="text-danger">*</span></label>
										<div class="">
										<input type="time" name="fromtime"
											id="fromtime"
											class="form-control " placeholder="HH:MM"/>
										</div>
									</div>
									<div class="form-group">
										<label>To Time<span class="text-danger">*</span></label>
										<div class="">
											<input type="time" name="totime"
											id="totime"  
											class="form-control" placeholder="HH:MM" onchange="getdiffirencetime()"/>
										</div>
									</div><!--onchange="getdiffirencedays()"  -->
									<div class="form-group">
										<label>Total Time </label>
										<input type="text" name="time" id="time"
											class="form-control" readonly="readonly">
									</div>
									<%-- <div class="form-group">
										<label>Remaining Leaves <span class="text-danger">*</span></label>
										<input class="form-control" readonly value="12" type="text">
									</div> --%>
									<div class="form-group">
										<label>Remark <span class="text-danger">*</span></label>
										<textarea rows="4" class="form-control" name="otreason" id="ot_reason"></textarea>
									</div>
									<div class="submit-section">
										<!-- <button class="btn btn-primary submit-btn">Submit</button> -->
										<a href="#" onclick="timevalidate()" class="btn btn-primary submit-btn" id="empreg">Submit</a>
									</div>
								</s:form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Add Leave Modal -->
				
				<!-- Edit Leave Modal -->
				<div id="edit_ot" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Edit OT</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<s:form action="updateotPayrollDashBoard">
									<input type="hidden" name="upleaveid" id="upleaveid">
									<div class="form-group">
										<label>From Time <span class="text-danger">*</span></label>
										<div class="">
										<input type="time" name="fromtime"
											id="fromtime1"
											class="form-control " placeholder="HH:MM"/>
										</div>
									</div>
									<div class="form-group">
										<label>To Time<span class="text-danger">*</span></label>
										<div class="">
											<input type="time" name="totime"
											id="totime1"  
											class="form-control" placeholder="HH:MM" onchange="getdiffirencetime1()"/>
										</div>
									</div>
									<div class="form-group">
										<label>Total Time </label>
										<input type="text" name="time" id="time1"
											class="form-control" readonly="readonly">
									</div>
									<%-- <div class="form-group">
										<label>Remaining Leaves <span class="text-danger">*</span></label>
										<input class="form-control" readonly value="12" type="text">
									</div> --%>
									<div class="form-group">
										<label>Remark <span class="text-danger">*</span></label>
										<textarea rows="4" class="form-control" name="otreason" id="otreason1"></textarea>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn" onclick="updateleave();">Save</button>
									</div>
								</s:form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Edit Leave Modal -->
				
				<!-- Delete Designation Modal -->
				<div class="modal custom-modal fade" id="delete_leave" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-header">
								<input type="hidden" id="desiid">

									<h3>Delete OT</h3>
									<p>Are you sure want to delete?</p>
								</div>
								<div class="modal-btn delete-action">
								<input type="hidden" id="leaveid">
									<div class="row">
										<div class="col-6">
											<a href="#" class="btn btn-primary continue-btn" onclick="deleteot()">Delete</a>
										</div>
										<div class="col-6">
											<a href="javascript:void(0);" data-dismiss="modal" class="btn btn-primary cancel-btn">Cancel</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /Delete Designation Modal -->
				<div id="othodapprove" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->

			<div class="modal-content">
				<s:form action="updateapproveotPayrollDashBoard" theme="simple"
					id="otrequestform">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">OT Request Note</h4>
					</div>

					<div class="modal-body">
						<div id="page_printer5">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style="padding: 0px;">

								<div class=" letterheadhgt"
									style="border-bottom: 2px solid #6699cc; padding-top: 36px; padding-bottom: 15px; height: 135px;">
									<div id="newpost"
										class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: -52px">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
											style="padding-left: 0px; padding-right: 0px;">
											<link href="common/css/printpreview.css" rel="stylesheet" />
											<jsp:include page="/accounts/pages/letterhead.jsp"></jsp:include>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 text-center">
									<h4>
										<b>OT Request Note</b>
									</h4>
								</div>
								<s:hidden name="empot_id" id="empot_id"></s:hidden>
								<s:hidden name="sts" id="sts"></s:hidden>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
									style="padding: 0px; margin-bottom: 15px; text-transform: uppercase;">
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
										<%-- <p style="margin: 0 0 2.5px;"><b>leave Number : </b><span id="leaveno"></span></p> --%>

										
										<p style="margin: 0 0 2.5px;">
											<b>Name : </b><span id="userid"></span>
										</p>
										<p style="margin: 0 0 2.5px;">
											<b>Requsted User : </b><span id="requestname"></span>
										</p>
										<s:hidden name="requserid" id="requserid"/>
										<p style="margin: 0 0 2.5px;">
											<b>Requsted Date : </b><span id="requestdate"></span>
										</p>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">

									</div>
								</div>
								<div></div>
							</div>
							<div>
								<table class="table" style="width: 100%;">
									<thead>
										<tr>

											<th style="width: 20%;">OT Reason</th>
											<th style="width: 20%;">From Time</th>
											<th style="width: 10%;">To Time</th>
											<th style="width: 10%;">Total Time</th>

										</tr>
									</thead>
									<tbody id="tbodyleaveid">

									</tbody>
								</table>
							</div>
							<div class="col-lg-12 col-md-12 col-xs-12"
								style="padding: 0px; margin-top: 30px;">
								<div class="col-lg-6 col-md-6 col-xs-6 text-right">
									<div>
										<s:textarea id="notes" cssClass="form-control" name="notes"
											theme="simple" placeholder="Write Note"></s:textarea>
									</div>

								</div>
							</div>

						</div>

					</div>


					<div class="modal-footer" id="cooldude">
						<!-- <input type="button" onclick="printDiv2('page_printer5');" class="btn btn-primary" value="Print"> -->
					</div>
				</s:form>
			</div>

		</div>
		<iframe width="600" height="450" frameborder="0" style="border:0" allowfullscreen id="mapholder">
</iframe>
	</div>
 
		<!-- Sidebar Overlay -->
		<div class="sidebar-overlay" data-reff=""></div>

		<!-- jQuery -->
        <script src="payrollnew/assets/js/jquery-3.2.1.min.js"></script>

		<!-- Bootstrap Core JS -->
        <script src="payrollnew/assets/js/popper.min.js"></script>
        <script src="payrollnew/assets/js/bootstrap.min.js"></script>

		<!-- Slimscroll JS -->
		<script src="payrollnew/assets/js/jquery.slimscroll.min.js"></script>
		
		<!-- Select2 JS -->
		<script src="payrollnew/assets/js/select2.min.js"></script>
		
		<!-- Datatable JS -->
		<script src="payrollnew/assets/js/jquery.dataTables.min.js"></script>
		<script src="payrollnew/assets/js/dataTables.bootstrap4.min.js"></script>
		
		<!-- Datetimepicker JS -->
		<script src="payrollnew/assets/js/moment.min.js"></script>
		<script src="payrollnew/assets/js/bootstrap-datetimepicker.min.js"></script>

		<!-- Custom JS -->
		<script src="payrollnew/assets/js/app.js"></script>
		<script type="text/javascript">
		function getdiffirencetime(){
			 var fromtime=document.getElementById("fromtime").value;
			 var totime=document.getElementById("totime").value;
			 var startTime = moment(fromtime, "HH:mm:ss a"),
			 endTime = moment(totime, "HH:mm:ss a");

			 //method 1
			/*  var dif = moment.duration(endTime.diff(startTime)); */
			var dif = moment.duration(endTime.diff(startTime));
			 var hours=dif.hours();
			 var minutes=dif.minutes();
			 var second=dif.seconds();
			 if(hours<=9){
				 hours= "0"+hours;
			 }
			 if(minutes<=9){
				 minutes= "0"+minutes;
			 }
			 document.getElementById("time").value=[hours, minutes].join(':');

   		 }
    		 
          
		function getdiffirencetime1(){
			 var fromtime=document.getElementById("fromtime1").value;
			 var totime=document.getElementById("totime1").value;
			 var startTime = moment(fromtime, "HH:mm:ss a"),
			 endTime = moment(totime, "HH:mm:ss a");

			 //method 1
			/*  var dif = moment.duration(endTime.diff(startTime)); */
			var dif = moment.duration(endTime.diff(startTime));
			 var hours=dif.hours();
			 var minutes=dif.minutes();
			 var second=dif.seconds();
			 if(hours<=9){
				 hours= "0"+hours;
			 }
			 if(minutes<=9){
				 minutes= "0"+minutes;
			 }
			 document.getElementById("time1").value=[hours, minutes].join(':');

  		 }
		</script>
		<script type="text/javascript">
    function showmenu(val){
    	 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right show"; 
    	 setTimeout(function() {
    		 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right"; 
    		}, 3000);
    }
    function deleteleavepop(val) {
		document.getElementById("leaveid").value=val;
		$('#delete_leave').modal( "show" ); 
	}
    </script>
  <%--  <script src="https://maps.google.com/maps/api/js?sensor=false"></script>
<script>
function showPosition() {
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showMap, showError);
    } else {
        alert("Sorry, your browser does not support HTML5 geolocation.");
    }
}
 
// Define callback function for successful attempt
function showMap(position) {
    // Get location data
    var latlong = position.coords.latitude + "," + position.coords.longitude;
  /*   var latlong = new google.maps.LatLng(lat, long); */
    
    var myOptions = {
        center: latlong,
        zoom: 16,
        mapTypeControl: true,
        navigationControlOptions: {
            style:google.maps.NavigationControlStyle.SMALL
        }
    }
    
    var map = new google.maps.Map(document.getElementById("embedMap"), myOptions);
    var marker = new google.maps.Marker({ position:latlong, map:map, title:"You are here!" });
}
 
// Define callback function for failed attempt
function showError(error) {
    if(error.code == 1) {
        result.innerHTML = "You've decided not to share your position, but it's OK. We won't ask you again.";
    } else if(error.code == 2) {
        result.innerHTML = "The network is down or the positioning service can't be reached.";
    } else if(error.code == 3) {
        result.innerHTML = "The attempt timed out before it could get the location data.";
    } else {
        result.innerHTML = "Geolocation failed due to unknown error.";
    }
}
</script> --%>
    </body>
</html>