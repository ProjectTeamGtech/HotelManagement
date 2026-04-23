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
         <script type="text/javascript" src="common/js/pagination.js"></script>
     <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
        <title>Holidays - HRMS admin template</title>
		
    </head>
     <script type="text/javascript">
    function showmenu(val){
    	 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right show"; 
    	 setTimeout(function() {
    		 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right"; 
    		}, alertmsgduration);
    }
    
    </script>
    <body>
				<!-- Page Content -->
                <div class="content container-fluid">
				
					<!-- Page Title -->
					<div class="row">
						<div class="col-sm-5 col-5">
							<h4 class="page-title">Holidays</h4>
						</div>
						<div class="col-sm-7 col-7 text-right m-b-30">
							<a href="#" class="btn add-btn" data-toggle="modal" data-target="#add_holiday"><i class="fa fa-plus"></i> Add Holiday</a>
						</div>
					</div>
					<!-- /Page Title -->
					
					<div class="row">
					<%int i=1; %>
						<div class="col-md-12">
							<div class="">
								<table class="table table-striped custom-table mb-0">
									<thead>
									 <s:iterator value="holidayList">
										<tr class="holiday-upcoming">
											<td><%=i++ %></td>
											<s:hidden name="id"/>
											<td><s:property value="event"/></td>
											<td><s:property value="date"/></td>
												<td class="text-right">
												<div class="dropdown dropdown-action">
													<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onclick="showmenu(<s:property value="id"/>)"><i class="material-icons">more_vert</i></a>
													<div class="dropdown-menu dropdown-menu-right"id="showmenu<s:property value="id"/>">
														<a class="dropdown-item" href="#" onclick="getholidays(<s:property value='id'/>)"><i class="fa fa-pencil m-r-5"></i> Edit</a>
														<a class="dropdown-item" href="#" onclick="deleteholiday(<s:property value='id'/>)"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
													</div>
												</div>
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
				
				<!-- Add Holiday Modal -->
				<div class="modal custom-modal fade" id="add_holiday" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Add Holiday</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="addholidayPayrollMaster" id="addholidayfrm">
									<div class="form-group">
										<label>Holiday Name <span class="text-danger">*</span></label>
										<input class="form-control" type="text" name="event">
									</div>
									<div class="form-group">
										<label>Holiday Date <span class="text-danger">*</span></label>
										<div class="cal-icon"><input class="form-control datetimepicker" type="text" name="date"></div>
									</div>
									<div class="submit-section">
										<a href="#" class="btn btn-primary submit-btn" onclick="submit()" id="submt">Submit</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Add Holiday Modal -->
				
				<!-- Edit Holiday Modal -->
				<div class="modal custom-modal fade" id="edit_holiday" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Edit Holiday</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="updateholidayPayrollMaster">
								<input type="hidden" name="selectedid" id="selectedid">
									<div class="form-group">
										<label>Holiday Name <span class="text-danger">*</span></label>
										<input class="form-control" type="text" id="eventname" name="event">
									</div>
									<div class="form-group">
										<label>Holiday Date <span class="text-danger">*</span></label>
										<div class="cal-icon"><input class="form-control datetimepicker" type="text" id="eventdate" name="date"></div>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Save</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Edit Holiday Modal -->

				<!-- Delete Holiday Modal -->
				<div class="modal custom-modal fade" id="delete_holiday" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-header">
									<h3>Delete Holiday</h3>
									<p>Are you sure want to delete?</p>
								</div>
								<input type="hidden" name="deletedid" id="deletedid">
								<div class="modal-btn delete-action">
									<div class="row">
										<div class="col-6">
											<a href="#" onclick="deleteselectedhol(<s:property value='deletedid'/>)" class="btn btn-primary continue-btn">Delete</a>
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
				<!-- /Delete Holiday Modal -->
		<!-- Sidebar Overlay -->
		<div class="sidebar-overlay" data-reff=""></div>
		
		<!-- jQuery -->
        <script src="assets/js/jquery-3.2.1.min.js"></script>
		
		<!-- Bootstrap Core JS -->
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
		
		<!-- Slimscroll JS -->
		<script src="assets/js/jquery.slimscroll.min.js"></script>
		
		<!-- Datetimepicker JS -->
		<script src="assets/js/moment.min.js"></script>
		<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
		
		<!-- Custom JS -->
		<script src="assets/js/app.js"></script>
		<script type="text/javascript">
		function submit() {
		document.getElementById("submt").style.display="none";
		document.getElementById("addholidayfrm").submit();
		}
		</script>
    </body>
</html>