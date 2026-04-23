<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    	 var emphour=document.getElementById("workinghourday").value;
    	 document.getElementById("hours").value=emphour; 
    	 document.getElementById("days").value='1'; 
     		document.getElementById("leaveemp").className="active";	
     		document.getElementById("empmenu").className="subdrop";	
     		
     };
    
     $(document).ready(function() {
    	 var days = 7;
    	 var date = new Date();
    	 var res = date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
			
    	 var d = new Date(res);
    	 var month = d.getMonth() + 1;
    	 var day = d.getDate();

    	 var output = d.getFullYear() + '/' +
    	     (month < 10 ? '0' : '') + month + '/' +
    	     (day < 10 ? '0' : '') + day;
         $('#toleave').datetimepicker({
    			format: 'DD/MM/YYYY',
    			minDate: 0, 
    		}).on('dp.change', function() {
    			getdiffirencedaysedit();
        	});
        
         
         
         $('#leavetodate').datetimepicker({
    			format: 'DD/MM/YYYY',
    			 minDate: 0, 
    		}).on('dp.change', function() {
    			getdiffirencedays();
        	});
         
         $('#leavefromdate').datetimepicker({
 			format: 'DD/MM/YYYY',
 			 minDate: 0, 
 		}).on('dp.change', function() {
 			getdiffirencedays();
     	});
         });

    function datefilter(val){
    	var emphour=document.getElementById("workinghourday").value;
	   	 var days = 7;
	   	 var date = new Date();
	   	 var res = date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
	
	   	 var d = new Date(res);
	   	 var month = d.getMonth() + 1;
	   	 var day = d.getDate();
	   	 var output = d.getFullYear() + '/' +
	   	     (month < 10 ? '0' : '') + month + '/' +
	   	     (day < 10 ? '0' : '') + day;
	   	 var tmp=output.split('/');
	   	 var dated=tmp[2]+'/'+tmp[1]+'/'+tmp[0];
	   	 
	   	var today = new Date();
	   	var dd = today.getDate();
	   	var mm = today.getMonth()+1; 
	   	var yyyy = today.getFullYear();
	   	if(dd<10) 
	   	{
	   	    dd='0'+dd;
	   	} 

	   	if(mm<10) 
	   	{
	   	    mm='0'+mm;
	   	} 
	   	today = dd+'/'+mm+'/'+yyyy;
	   	if(val!=1){
	   		output="0";
	   		dated=today;
	   		document.getElementById("leavetodate").className="form-control datetimepicker";
	   		document.getElementById("leavefromdate").className="form-control datetimepicker";
	   	}
       	document.getElementById("leavetodate").value=dated; 
    	document.getElementById("leavefromdate").value=dated; 
    	 document.getElementById("days").value='1'; 
    	 document.getElementById("hours").value=emphour; 
    	 
        $('#leavetodate').datetimepicker({
   			format: 'DD/MM/YYYY',
   		 minDate: ''+output, 
   		}).on('dp.change', function() {
   			getdiffirencedays();
       	});
        
        $('#leavefromdate').datetimepicker({
			format: 'DD/MM/YYYY',
			 minDate: ''+output, 
		}).on('dp.change', function() {
			getdiffirencedays();
    	});
    	
    }
  </SCRIPT>
  <style type="text/css">
 @media only screen and (max-width: 992px) {
  .scrollmenu {
  overflow: auto;
  white-space: nowrap;
}
}
 .scrollit {
    overflow:scroll;
}
  </style>
        <title>Leaves - HRMS admin template</title>
			<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
				<!-- Page Content -->
                <div class="content container-fluid scrollit">
				
					<!-- Page Title -->
					<div class="row">
						<div class="col-sm-8 col-6">
							<h4 class="page-title">Leaves</h4>
						</div>
						<div class="col-sm-4 col-6 text-right m-b-30">
							<a href="#" class="btn add-btn" data-toggle="modal" data-target="#add_leave"><i class="fa fa-plus"></i> Apply New Leave</a>
						</div>
					</div>
					<!-- /Page Title -->
					
					<!-- Leave Statistics -->
					 <div class="row">
						<div class="col-md-3">
							<div class="stats-info" style="height: 33px;background-color: #5babf9;">
								<h6 style="margin-top: -9px;color: white;">Casual Leave : <s:property	value="totalleave"/></h6>
								<%-- <h4 style=";color: white;"><s:property	value="totalleave"/></h4> --%>
							</div>
						</div>
						<!-- <div class="col-md-3">
							<div class="stats-info">
								<h6>Medical Leave</h6>
								<h4>3</h4>
							</div>
						</div>
						<div class="col-md-3">
							<div class="stats-info">
								<h6>Other Leave</h6>
								<h4>4</h4>
							</div>
						</div> -->
						<div class="col-md-3">
							<div class="stats-info" style="height: 33px;background-color: #5babf9;">
								<h6 style="margin-top: -9px;color: white;">Remaining Leave : <s:property	value="remainingleave"/></h6>
								<input type="hidden" id="remleave" value="<s:property	value="remainingleave"/>">
								<s:hidden id="workinghourday" name="workinghourday"/>
								<!-- <h4></h4> -->
							</div>
						</div>
					</div> 
					<!-- /Leave Statistics -->
					
					<div class="row scrollmenu ">
						<div class="col-md-12">
							<div class="table table-striped custom-table mb-0">
								<table class="table table-striped custom-table mb-0">
									<thead>
										<tr>
											<th>Name</th>
											<th>Leave Type</th>
											<th>From</th>
											<th>To</th>
											<th>No of Days</th>
											<th>Request Date</th>
											<th>Reason</th>
											<th class="text-center">Status</th>
											<th>APP/REJ Note</th>
											<th>APP/REJ by</th>
											<th class="text-right">Actions</th>
											<th style="text-align: center; width: 1%;">Print</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="leaveList">
										<tr>
											<td><s:property value="name" /></td>
											<td>
											<s:if test="leave_type==1">
											Casual Leave
											</s:if>
											<s:elseif test="leave_type==2">
											Medical Leave
											</s:elseif>
											<s:else>
											Loss of Pay
											</s:else>
											</td>
											<td><s:property	value="fromdate"/></td>
											<td><s:property	value="todate"/></td>
											<td><s:property	value="days"/></td>
											<td><s:property	value="date"/></td>
											<td><s:property	value="leave_reason"/></td>
											<td class="text-center">
											<s:if test="status==0">
              					   			Requested
              					   			</s:if>
              					   			 <s:elseif test="status==1">
              					   			APPROVED</s:elseif> 
              					   			<s:elseif test="status==3">
              					   			APPROVED BY HR</s:elseif> 
              					   			<s:elseif test="status==2">
              					   			REJECTED
              					   		</s:elseif></td>
              					   		<td>
											<s:property	value="comment"/>
											</td>
											<td>
											<s:property	value="approvedby"/>
											</td>
											<td class="text-right">
											<s:if test="status==0">
												<div class="dropdown dropdown-action">
													<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onclick="showmenu(<s:property value="id"/>)"><i class="material-icons">more_vert</i></a>
													<div class="dropdown-menu dropdown-menu-right"id="showmenu<s:property value="id"/>">
														<a class="dropdown-item" href="#" onclick="geteditleaves(<s:property value='id'/>)"><i class="fa fa-pencil m-r-5" ></i> Edit</a>
														 <a class="dropdown-item" href="#" data-toggle="modal" onclick="deleteleavepop(<s:property value="id"/>)"><i class="fa fa-trash-o m-r-5"></i> Delete</a> 
													</div>
												</div>
												</s:if>
											</td>
											<td style="text-align: center;"><a href="#"
									onclick="openPopup('leavePrintPayrollDashBoard?id=<s:property value="id" />&status=1&mainstatus=4&printbeforeapprove=0')"><i
										class="fa fa-print"></i></a></td>
										</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</div>
                </div>
				<!-- /Page Content -->
				<s:form action="leaverequestPayrollDashBoard" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
								<div class="row" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<s:include value="/common/pages/pagination.jsp"></s:include>
								</div>
							</s:form>
				<!-- Add Leave Modal -->
				<div id="add_leave" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Apply New Leave</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<s:form action="saveleavePayrollDashBoard" id="leaveform">
								<s:hidden name="status"/>
								<input type="hidden" name="allleaveid" id="allleaveid">
									<div class="form-group">
									<%if(loginInfo.getClinicUserid().equals("pcsadmin")){ %>
										<label>Leave Type <span class="text-danger">*</span></label>
										<select class="select" name="leave_type" id="leavetype" onchange="datefilter(this.value)">
											<option value="0">Select Leave Type</option>
											<option value="1">Casual Leave</option>
											<option value="2">Medical Leave</option>
											<option value="3">Loss of Pay</option>
										</select>
										<%}else{ %>
										<label>Leave Type <span class="text-danger">*</span></label>
										<select class="select" name="leave_type" id="leavetype">
											<option value="0">Select Leave Type</option>
											<option value="1">Casual Leave</option>
											<option value="2">Medical Leave</option>
											<option value="3">Loss of Pay</option>
										</select>
										<%} %>
									</div>
									<div class="form-group">
										<label>From <span class="text-danger">*</span></label>
										<div class="cal-icon">
										<input type="text" name="fromdate"
											id="leavefromdate"
											class="form-control " placeholder="Select Date">
										</div>
									</div>
									<div class="form-group">
										<label>To <span class="text-danger">*</span></label>
										<div class="cal-icon">
											<input type="text" name="todate"
											id="leavetodate"  
											class="form-control" placeholder="Select Date" >
										</div>
									</div><!--onchange="getdiffirencedays()"  -->
									<div class="form-group">
										<label>Number of days </label>
										<input type="text" name="days" id="days"
											class="form-control" readonly="readonly">
									</div>
									<div class="form-group">
										<label>Total Hour </label>
										<input type="text" name="hours" id="hours"
											class="form-control">
									</div>
									<%-- <div class="form-group">
										<label>Remaining Leaves <span class="text-danger">*</span></label>
										<input class="form-control" readonly value="12" type="text">
									</div> --%>
									<div class="form-group">
										<label>Leave Reason <span class="text-danger">*</span></label>
										<textarea rows="4" class="form-control" name="leave_reason" id="leave_reason"></textarea>
									</div>
									<div class="submit-section">
										<!-- <button class="btn btn-primary submit-btn">Submit</button> -->
										<a href="#" onclick="leavevalidate()" class="btn btn-primary submit-btn" id="empreg">Submit</a>
									</div>
								</s:form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Add Leave Modal -->
				
				<!-- Edit Leave Modal -->
				<div id="edit_leave" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Edit Leave</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<s:form action="updateleavePayrollDashBoard">
									<input type="hidden" name="upleaveid" id="upleaveid">
									<div class="form-group">
										
										<%-- <select class="select" name="leave_type" id="leavetype1">
											<option value="0">Select Leave Type</option>
											<option value="1">Casual Leave</option>
											<option value="2">Medical Leave</option>
											<option value="3">Loss of Pay</option>
										</select> --%>
										<label>Leave Type <span class="text-danger">*</span></label>
										<s:select cssClass="form-control"
										list="#{'1':'Casual Leave', '2':'Medical Leave', '3':'Loss of Pay'}"
										theme="simple" id="leavetype1" name="leave_type"  headerKey="0" headerValue="Select Leave Type"/>
									</div>
									<div class="form-group">
										<label>From <span class="text-danger">*</span></label>
										<div class="cal-icon">
											<input class="form-control datetimepicker" id="fromleave" name="fromdate" type="text">
										</div>
									</div>
									<div class="form-group">
										<label>To <span class="text-danger">*</span></label>
										<div class="cal-icon">
											<input class="form-control datetimepicker" id="toleave" name="todate" type="text">
										</div>
									</div>
									<div class="form-group">
										<label>Number of days <span class="text-danger">*</span></label>
										<input class="form-control" readonly type="text" name="days" id="leavedays">
									</div>
									
									<div class="form-group">
										<label>Total Hours<span class="text-danger">*</span></label>
										<input class="form-control"  type="text" name="hours" id="edithours">
									</div>
									<%-- <div class="form-group">
										<label>Remaining Leaves <span class="text-danger">*</span></label>
										<input class="form-control" readonly value="12" type="text">
									</div> --%>
									<div class="form-group">
										<label>Leave Reason <span class="text-danger">*</span></label>
										<textarea rows="4" class="form-control" name="leave_reason" id="levereason"></textarea>
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

									<h3>Delete Leave</h3>
									<p>Are you sure want to delete?</p>
								</div>
								<div class="modal-btn delete-action">
								<input type="hidden" id="leaveid">
									<div class="row">
										<div class="col-6">
											<a href="#" class="btn btn-primary continue-btn" onclick="deleteleave()">Delete</a>
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
		<%-- <script src="payrollnew/assets/js/jquery.dataTables.min.js"></script>
		<script src="payrollnew/assets/js/dataTables.bootstrap4.min.js"></script> --%>
		
		<!-- Datetimepicker JS -->
		<script src="payrollnew/assets/js/moment.min.js"></script>
		<script src="payrollnew/assets/js/bootstrap-datetimepicker.min.js"></script>

		<!-- Custom JS -->
		<script src="payrollnew/assets/js/app.js"></script>
		<script type="text/javascript">
		 function getdiffirencedays(){
    		var leavefromdate=document.getElementById("leavefromdate").value;
          	var leavetodate=document.getElementById("leavetodate").value;
          	
          	var tmp=leavefromdate.split('/');
          	var tmp1=leavetodate.split('/');
			var date1=tmp[1]+'/'+tmp[0]+'/'+tmp[2];
			var date2=tmp1[1]+'/'+tmp1[0]+'/'+tmp1[2];
          	var firstDate = new Date(date1);
          	var secondDate = new Date(date2);
            if (!isNaN(firstDate) && !isNaN(secondDate)) {
              firstDate.setHours(0, 0, 0, 0); //ignore time part
              secondDate.setHours(0, 0, 0, 0); //ignore time part
              var dayDiff = secondDate - firstDate;
              dayDiff = dayDiff / 86400000; 
            }
    	document.getElementById("days").value=dayDiff+1; 
          	 var emphour=document.getElementById("workinghourday").value;
          		document.getElementById("hours").value=emphour*Number(document.getElementById("days").value);
                       /*   if(leavefromdate!='' && leavetodate!=''){
    			 var date1 = leavefromdate.split('/');
    	          	var date2 = leavetodate.split('/');
    	          	date1 = new Date(date1[2], date1[1], date1[0]);
    	          	date2 = new Date(date2[2], date2[1], date2[0]);
    	          	var days = days_between(date1,date2);
    	          	days=days+1;
    	          	document.getElementById("days").value=days;
    		 } */
    		 
          
          	
    	 }
		 function getdiffirencedaysedit(){
    		 var leavefromdate=document.getElementById("fromleave").value;
          	 var leavetodate=document.getElementById("toleave").value;
    		 
         	var tmp=leavefromdate.split('/');
          	var tmp1=leavetodate.split('/');
			var date1=tmp[1]+'/'+tmp[0]+'/'+tmp[2];
			var date2=tmp1[1]+'/'+tmp1[0]+'/'+tmp1[2];
          	var firstDate = new Date(date1);
          	var secondDate = new Date(date2);
            if (!isNaN(firstDate) && !isNaN(secondDate)) {
              firstDate.setHours(0, 0, 0, 0); //ignore time part
              secondDate.setHours(0, 0, 0, 0); //ignore time part
              var dayDiff = secondDate - firstDate;
              dayDiff = dayDiff / 86400000; 
            }
    		 /* if(leavefromdate!='' && leavetodate!=''){
    			 var date1 = leavefromdate.split('/');
    	          	var date2 = leavetodate.split('/');
    	          	date1 = new Date(date1[2], date1[1], date1[0]);
    	          	date2 = new Date(date2[2], date2[1], date2[0]);
    	          	var days = days_between(date1,date2);
    	          	document.getElementById("leavedays").value=days;
    		 } */
        	document.getElementById("leavedays").value=dayDiff+1;
        	var emphour=document.getElementById("workinghourday").value;
      		document.getElementById("edithours").value=emphour*Number(document.getElementById("leavedays").value);
          	
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
    </body>
</html>