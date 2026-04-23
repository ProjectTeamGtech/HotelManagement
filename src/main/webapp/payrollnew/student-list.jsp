<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="tools/js/sendLetter.js"></script> 
<script type="text/javascript" src="diarymanagement/js/sendApmtAttachnment.js"></script>
<script>


window.onload = function () {
	
	/* document.getElementById("allemp").className="active";	 */
}
</script>
<script type="text/javascript">
    function showmenu(val){
    	 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right show"; 
    	 setTimeout(function() {
    		 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right"; 
    		}, alertmsgduration);
    }
    
    </script>
    
<html lang="en">
    <head>
     <link rel="stylesheet" type="text/css" href="payrollnew/assets/css/dataTables.bootstrap.min.css">
        <meta charset="utf-8">
        <script type="text/javascript" src="common/js/pagination.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <SCRIPT type="text/javascript" src="payroll/js/payrollvalidatation.js"></SCRIPT>
        <title>Employees - HRMS admin template</title>
    </head>
    <script type="text/javascript">
    $(document).ready(function() {
        $('#example').DataTable( {
            "pagingType": "full_numbers"
        } );
    } );
    </script>
     <script type="text/javascript">
  $(function() {
    $('#date_join').datetimepicker({
      language: 'pt-BR'
    });
  });
</script>
    <body>
					
				<!-- Page Content -->
                <div class="content container-fluid">
				
					<!-- Page Title -->
					<div class="row">
						<div class="col">
							<h4 class="page-title">Student</h4>
						</div>
						<div class="col-auto text-right float-right ml-auto m-b-30">
							<a href="#" class="btn add-btn" data-toggle="modal" data-target="#add_employee"><i class="fa fa-plus"></i> Add Student</a>
							<!-- <div class="view-icons">
								<a href="PayrollEmployee?status=0" class="grid-view btn btn-link active"><i class="fa fa-th"></i></a>
								<a href="PayrollEmployee?status=1" class="list-view btn btn-link"><i class="fa fa-bars"></i></a>
							</div> -->
						</div>
					</div>
					<!-- /Page Title -->
					
					<!-- Search Filter -->
					
					<form action="studentPayrollEmployee">
					<div class="row filter-row">
						<div class="col-sm-6 col-md-3">  
							<div class="form-group form-focus">
							<s:textfield name="searchText" cssClass="form-control floating"/>
								<label class="focus-label">Student ID</label>
							</div>
						</div>
						<div class="col-sm-6 col-md-3">  
							<div class="form-group form-focus">
								<s:textfield name="empnamesearch" cssClass="form-control floating"/>
								<label class="focus-label">Student Name</label>
							</div>
						</div>
							<div class="col-sm-6 col-md-3">  
							<div class="form-group form-focus">
								<s:select name="department" id="department" list="departmentlist" listKey="dept_id" listValue="department" cssClass="form-control showToolTip chosen-select" headerValue="Select Department" headerKey="0">
                                    </s:select>
							</div>
						</div>
						<%-- <div class="col-sm-6 col-md-3"> 
							<div class="form-group form-focus select-focus">
								<select class="select floating"> 
									<option>Select Designation</option>
									<option>Web Developer</option>
									<option>Web Designer</option>
									<option>Android Developer</option>
									<option>Ios Developer</option>
								</select>
								<label class="focus-label">Designation</label>
							</div>
						</div> --%>
						<div class="col-sm-6 col-md-3">  
							<input type="submit" class="btn btn-success btn-block" value="Search">  
						</div>
						</div>
						    </form>
                    
					<!-- /Search Filter -->
					
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table table-striped custom-table datatable">
									<thead>
									<%int i=1; %>
										<tr>
											<th>Sr No.</th>
											<th>Name</th>
											<th>Student ID</th>
											<th>Email</th>
											<th>Mobile</th>
											<th>Department</th>
										
											<!-- <th class="text-right no-sort">Action</th> -->
										</tr>
									</thead>
									<tbody>
									<s:iterator value="Employeelist">
									<s:hidden name="emp_id"/>
										<tr>
										<td><%=i++ %></td>
											<td>
												<h2 class="table-avatar">
													<a href="payrollprofilePayrollEmployee?empid=<s:property value="emp_id"/>" class="avatar"><img alt="" src="assets/img/profiles/avatar-02.jpg"></a>
													<a href="payrollprofilePayrollEmployee?empid=<s:property value="emp_id"/>"><s:property value="name"/><span></span></a>
												</h2>
											</td>
											<td><s:property value="abrivationid"/></td>
											<td><s:property value="email"/></td>
											<td><s:property value="mobNo"/></td>
											<td><s:property value="department"/></td>
											
<%-- 											<td style="text-align: center;"><a href="#" onclick="sendpayrollletter('<s:property value="email"/>')"><i class="fa fa-envelope" aria-hidden="true"></i></a></td>
 --%>											<%-- <td class="text-right">
												<div class="dropdown dropdown-action">
													<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onclick="showmenu(<s:property value="emp_id"/>)"><i class="material-icons">more_vert</i></a>
													<div class="dropdown-menu dropdown-menu-right" id="showmenu<s:property value="emp_id"/>">
														<a class="dropdown-item" href="#" data-toggle="modal" data-target="#edit_employee"><i class="fa fa-pencil m-r-5"></i> Edit</a>
														<a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_employee"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
													</div>
												</div>
											</td> --%>
										</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
					</div>
                </div>
				<!-- /Page Content -->
				
				<!-- Add Employee Modal -->
				<div id="add_employee" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Add Employee</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="addPayrollEmployee" id="addemployeefrm">
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">First Name <span class="text-danger">*</span></label>
												<input class="form-control" type="text" name="firstname" id="firstname" style="text-transform: capitalize;">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Last Name <span class="text-danger">*</span></label>
												<input class="form-control" type="text" name="lastname" id="lastname" style="text-transform: capitalize;">
											</div>
										</div>
										<%-- <div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Username <span class="text-danger">*</span></label>
												<input class="form-control" type="text" name="username">
											</div>
										</div> --%>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Email <span class="text-danger">*</span></label>
												<input class="form-control" type="email" name="email" id="email">
											</div>
										</div>
										<!-- <div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Password</label>
												<input class="form-control" type="password">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Confirm Password</label>
												<input class="form-control" type="password">
											</div>
										</div> -->
										<div class="col-sm-6">  
											<div class="form-group">
												<label class="col-form-label">Employee ID <span class="text-danger">*</span></label>
												<input type="text" id="empcode" name="empcode" class="form-control" placeholder="Enter Employee Code">
											</div>
										</div>
										<div class="col-sm-6">  
											<div class="form-group">
												<label class="col-form-label">Joining Date <span class="text-danger">*</span></label>
												<div class="cal-icon"><input class="form-control datetimepicker" type="text" name="date_join" id="date_join"></div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Phone </label>
												<input class="form-control" type="text" name="mobNo" maxLength="10">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Company</label>
												<!-- <select class="select">
													<option value="">Global Technologies</option>
													<option value="1">Delta Infotech</option>
												</select> -->
												 <s:select name="company" id="company" list="companylist" listKey="comp_id" listValue="company" theme="simple" cssClass="form-control showToolTip chosen-select">
                                    </s:select>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Department <span class="text-danger">*</span></label>
												<!-- <select class="select">
													<option>Select Department</option>
													<option>Web Development</option>
													<option>IT Management</option>
													<option>Marketing</option>
												</select> -->
												<s:select name="department" id="department" list="departmentlist" listKey="dept_id" listValue="department" cssClass="form-control showToolTip chosen-select" headerKey="0">
                                    </s:select>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group" id="designationdiv">
												<label>Designation <span class="text-danger">*</span></label>
												<%--  <select class="select" name="designation">
													<option>Select Designation</option>
													<option>Web Designer</option>
													<option>Web Developer</option>
													<option>Android Developer</option>
												</select> --%>
												<s:select name="designation" id="designation" list="designationlist" listKey="name" listValue="name" cssClass="form-control showToolTip chosen-select">
                                    </s:select>
												
												  
											</div>
										</div>
									</div>
									<!-- <div class="table-responsive m-t-15">
										<table class="table table-striped custom-table">
											<thead>
												<tr>
													<th>Module Permission</th>
													<th class="text-center">Read</th>
													<th class="text-center">Write</th>
													<th class="text-center">Create</th>
													<th class="text-center">Delete</th>
													<th class="text-center">Import</th>
													<th class="text-center">Export</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>Holidays</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Leaves</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Clients</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Projects</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Tasks</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Chats</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Assets</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Timing Sheets</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
											</tbody>
										</table>
									</div> -->
									<div class="submit-section">
										<!-- <button class="btn btn-primary submit-btn">Submit</button> -->
										<a href="#" onclick="addempvalidate()" class="btn btn-primary submit-btn" id="empreg">Submit</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Add Employee Modal -->
				
				<!-- Edit Employee Modal -->
				<div id="edit_employee" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Edit Employee</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">First Name <span class="text-danger">*</span></label>
												<input class="form-control" value='<s:property value="firstname"/>' type="text">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Last Name</label>
												<input class="form-control" value="Doe" type="text">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Username <span class="text-danger">*</span></label>
												<input class="form-control" value="johndoe" type="text">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Email <span class="text-danger">*</span></label>
												<input class="form-control" value="johndoe@example.com" type="email">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Password</label>
												<input class="form-control" value="johndoe" type="password">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Confirm Password</label>
												<input class="form-control" value="johndoe" type="password">
											</div>
										</div>
										<div class="col-sm-6">  
											<div class="form-group">
												<label class="col-form-label">Employee ID <span class="text-danger">*</span></label>
												<input type="text" value="FT-0001" readonly class="form-control floating">
											</div>
										</div>
										<div class="col-sm-6">  
											<div class="form-group">
												<label class="col-form-label">Joining Date <span class="text-danger">*</span></label>
												<div class="cal-icon"><input class="form-control datetimepicker" type="text"></div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Phone </label>
												<input class="form-control" value="9876543210" type="text">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="col-form-label">Company</label>
												<select class="select">
													<option>Global Technologies</option>
													<option>Delta Infotech</option>
													<option selected>International Software Inc</option>
												</select>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Department <span class="text-danger">*</span></label>
												<select class="select">
													<option>Select Department</option>
													<option>Web Development</option>
													<option>IT Management</option>
													<option>Marketing</option>
												</select>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Designation <span class="text-danger">*</span></label>
												<select class="select">
													<option>Select Designation</option>
													<option>Web Designer</option>
													<option>Web Developer</option>
													<option>Android Developer</option>
												</select>
											</div>
										</div>
									</div>
									<div class="table-responsive m-t-15">
										<table class="table table-striped custom-table">
											<thead>
												<tr>
													<th>Module Permission</th>
													<th class="text-center">Read</th>
													<th class="text-center">Write</th>
													<th class="text-center">Create</th>
													<th class="text-center">Delete</th>
													<th class="text-center">Import</th>
													<th class="text-center">Export</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>Holidays</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Leaves</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Clients</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Projects</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Tasks</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Chats</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Assets</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
												<tr>
													<td>Timing Sheets</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input checked="" type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
													<td class="text-center">
														<input type="checkbox">
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Save</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Edit Employee Modal -->
				
				<!-- Delete Employee Modal -->
				<div class="modal custom-modal fade" id="delete_employee" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-header">
									<h3>Delete Employee</h3>
									<p>Are you sure want to delete?</p>
								</div>
								<div class="modal-btn delete-action">
									<div class="row">
										<div class="col-6">
											<a href="javascript:void(0);" class="btn btn-primary continue-btn">Delete</a>
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
				<!-- /Delete Employee Modal -->
				
		 <!-- Loader -->
			<div id="loader-wrapper">
				<div id="loader">
					<div class="loader-ellips">
					  <span class="loader-ellips__dot"></span>
					  <span class="loader-ellips__dot"></span>
					  <span class="loader-ellips__dot"></span>
					  <span class="loader-ellips__dot"></span>
					</div>
				</div>
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
		
		<!-- Datetimepicker JS -->
		<script src="payrollnew/assets/js/moment.min.js"></script>
		<script src="payrollnew/assets/js/bootstrap-datetimepicker.min.js"></script>
		
		<!-- Datatable JS -->
		<script src="payrollnew/assets/js/jquery.dataTables.min.js"></script>
		<script src="payrollnew/assets/js/dataTables.bootstrap4.min.js"></script>
		
		<!-- Custom JS -->
		<%-- <script src="payrollnew/assets/js/app.js"></script> --%>
		 <s:form action="PayrollEmployee" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
							     <s:hidden name="searchText"></s:hidden>
							     <s:hidden name="empnamesearch"></s:hidden>
							      <s:hidden name="department"></s:hidden>
								<div class="row" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<s:include value="/common/pages/pagination.jsp"></s:include>
								</div>
							</s:form>
							
							
							
									<!-- Send Letter Details Modal -->
	
	<div class="modal fade" id="sendLetterPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			
     
				<div class="modal-header bg-primary">
				<h4 class="modal-title" id="myModalLabel">Send Email/Letter</h4>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					
				</div>
				<div class="modal-body" id="sendsms">
						<div class="row">
        
		
		
		
		 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
         <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
				<label>Select Template</label>
		</div>
			 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
				<%-- <select id = "templateName" name = "templateName" onchange="showTemplateDetails(this.id)" class="form-control showToolTip chosen" data-toggle="tooltip">
						<option value="0">Select Template Name</option>
				</select>	 --%>	
		<s:select onchange="showpayrollTemplateDetails(this.value)" cssClass="form-control showToolTip chosen" name="templateName" id="templateName" list="templatelist" listKey="id" listValue="templateName" headerKey="0" headerValue="Select Template"/>
							
			</div>
		</div>
		
					
	
		<s:hidden name="id" id="id"></s:hidden>
		<s:hidden name="user" id="userletter"></s:hidden>
		<%-- <div class="col-lg-12">
		 <div class="col-lg-3">
			<label>User Name</label>
		</div>
		 <div class="col-lg-8">
			<s:textfield name="user" id="userletter" readonly="true" cssClass="form-control" />
				     <label  id = "userError" class="text-danger"></label>	
		<s:hidden name="id" id="id"></s:hidden>
		<s:hidden name="user" id="userletter"></s:hidden>
		</div>
		</div> --%>
		
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		 <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			<label>Email ID</label>
		</div>
		 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
			<s:textfield name="email" id="gpLetterEmail" cssClass="form-control showToolTip" title="Enter EmailId"
								data-toggle="tooltip" placeholder="Enter EmailId"/>
			<label  id = "emailError" class="text-danger"></label>	
		</div>
		</div>
		
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
		 <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			<label>Cc</label>
		</div>
		 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
			<s:textfield theme="simple" id = "gpLetterccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />			
		</div>
		</div>
		
		
		
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
		 <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
			<label>Subject</label>
		</div>
		 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
			<input type="text" name= "subject" id = "gpLettersubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">			
		</div>
		</div>
		
       <br/>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
			 <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
				<label>Body:</label>
				</div>
				<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
				<s:textarea cssStyle="height:500px;" class="form-control showToolTip textarea"  data-toggle="tooltip" title="Write content" placeholder="Write content" name="body" id="emailBodyLetter" />
			</div>
			</div>
			
			
			   			
		</div>
		
      </div>
						
			<div class="modal-footer">
			<div class="row">
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="pdfFileIdLetter">
			</div></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="sendmail">
			</div></div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
			<div id="printId">
			</div></div>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<!-- <button type="button" id="saveId" class="btn btn-primary" onclick="return createPdf()">Save as pdf</button> -->
		<button type="button" id="saveId" class="btn btn-primary"  onclick="sendpayrollmail()">Send Mail</button>
		<button type="button" class="btn btn-primary"  onclick="return createPdf()">Print</button>
		<button type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
      </div></div></div>
     
    </div>
  </div>
</div> 


    </body>
		<script>

function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "report",
					filename: "investigationdashboard",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}


$(document).ready(function(){
	 $('#newloaderPopup').modal('show');

    var today = new Date();
	var date = today.getDate();
	var maonth = today.getMonth();
	var year = today.getFullYear(); 
	
	 $( "#dob" ).datepicker({
		 
		 dateFormat:'dd/mm/yy',
		 minDate : '30/12/1880',
		 yearRange: yearrange,
		 maxDate: today,
		 changeMonth: true,
	     changeYear: true
		 
   });
   
	 $("#datw").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
	 
	 $("#outexpdsate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : 0,
			changeMonth : true,
			changeYear : true

		});
	 
   
  $("#fromdate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
   
   $("#todate").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

   });
   $('input[type=text]').addClass('notranslate');
   $('input[type=textarea]').addClass('notranslate');
   $('.chosen-container').addClass('notranslate');
   $('.form-control').addClass('notranslate');
   $('form-group').addClass('notranslate');
   
   
});	




bkLib.onDomLoaded(function() {
   
	// new nicEditor().panelInstance('emailBodyLetter');
	 new nicEditor({maxHeight : 2500}).panelInstance('emailBodyLetter');
	
	 
	 $('.nicEdit-panelContain').parent().width('100%');
	 $('.nicEdit-panelContain').parent().next().width('98%');
	 
	 $('.nicEdit-main').width('98%');
	 $('.nicEdit-main').hieght('50px');
	 //document.getElementById("user").disabled = 'disabled';
	
});
</script>
<script>
 bkLib.onDomLoaded(function() {
		           
	        	 //new nicEditor().panelInstance('declarationNotes');
	        	 new nicEditor({maxHeight : 250}).panelInstance('templatesec');
	        	 $('.nicEdit-panelContain').parent().width('98%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 
	        	// $('.nicEdit-main').height('480px');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],minHeight : 70}).panelInstance('comment');
	        	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],minHeight : 70}).panelInstance('commentwriteup');
	      });

</script>