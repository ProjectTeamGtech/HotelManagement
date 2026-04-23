<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@taglib prefix="s" uri="/struts-tags"%>   
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Departments - HRMS admin template</title>
		 <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
		
    </head>
    <script>


window.onload = function () {
	
	document.getElementById("department").className="active";	
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
    <body>
				<!-- Page Content -->
                <div class="content container-fluid">
				
					<!-- Page Title -->
					<div class="row">
						<div class="col-sm-5 col-5">
							<h4 class="page-title">Department</h4>
						</div>
						<div class="col-sm-7 col-7 text-right m-b-30">
							<a href="#" class="btn add-btn" data-toggle="modal" data-target="#add_department"><i class="fa fa-plus"></i> Add Department</a>
						</div>
					</div>
					<!-- /Page Title -->
					
					<div class="row">
						<div class="col-md-12">
							<div>
							<%int i=1; %>
								<table class="table table-striped custom-table mb-0 datatable">
									<thead>
										<tr>
											<th style="width: 30px;">#</th>
											<th>Department Name</th>
											<th>Email</th>
											<th class="text-right">Action</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="departmentList">
										<tr>
											<td><%=i++ %></td>
											<td><s:property value="dept_name"/></td>
											<td><s:property value="email"/></td>
											 <td class="text-right">
                                            <div class="dropdown dropdown-action">
													<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onclick="showmenu(<s:property value="id"/>)"><i class="material-icons">more_vert</i></a>
                                                <div class="dropdown-menu dropdown-menu-right" id="showmenu<s:property value="id"/>">
                                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#edit_department" onclick="deprtmntedit(<s:property value="id"/>)"><i class="fa fa-pencil m-r-5"></i> Edit</a>
                                                    <a class="dropdown-item" href="#" data-toggle="modal" onclick="deletedept(<s:property value="id"/>)"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
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
				
				<!-- Add Department Modal -->
				<div id="add_department" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Add Department</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="addPayrollDepartment" id="adddepartmntid">
									<div class="form-group">
										<label>Department Name <span class="text-danger">*</span></label>
										<input class="form-control" type="text" name="dept_name">
									</div>
									<div class="form-group">
										<label>Department HOD Email <span class="text-danger">*</span></label>
										<input class="form-control" type="text" id="emailadd" name="email">
									</div>
									<div class="submit-section">
										<a href="#" class="btn btn-primary submit-btn" onclick="submit()" id="submt">Submit</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Add Department Modal -->
				
				<!-- Edit Department Modal -->
				<div id="edit_department" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Edit Department</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="updatePayrollDepartment">
								<s:hidden id="selectid" name="id"/>
									<div class="form-group">
										<label>Department Name <span class="text-danger">*</span></label>
										<input class="form-control" id="dept_name" type="text" name="dept_name">
									</div>
									<div class="form-group">
										<label>Department HOD Email <span class="text-danger">*</span></label>
										<input class="form-control" id="email" type="text" name="email">
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Save</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Edit Department Modal -->

				<!-- Delete Department Modal -->
				<div class="modal custom-modal fade" id="delete_department" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-header">
									<h3>Delete Department</h3>
									<p>Are you sure want to delete?</p>
								</div>
								<div class="modal-btn delete-action">
								<input type="hidden" id="deptid">
									<div class="row">
										<div class="col-6">
											<a href="#" class="btn btn-primary continue-btn" onclick="deletedeparment()">Delete</a>
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
				<!-- /Delete Department Modal -->
		
		<!-- Sidebar Overlay -->
		<div class="sidebar-overlay" data-reff=""></div>
		
		<!-- jQuery -->
        <script src="assets/js/jquery-3.2.1.min.js"></script>
		
		<!-- Bootstrap Core JS -->
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
		
		<!-- Slimscroll JS -->
		<script src="assets/js/jquery.slimscroll.min.js"></script>
		
		<!-- Datatable JS -->
		<script src="assets/js/jquery.dataTables.min.js"></script>
		<script src="assets/js/dataTables.bootstrap4.min.js"></script>
		
		<!-- Custom JS -->
		<script src="assets/js/app.js"></script>
		
    </body>
    <script type="text/javascript">
   function deprtmntedit(val){
	   var url="editPayrollDepartment?selectedid="+val+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   

	    req.onreadystatechange = deprtmnteditRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	   
	}

	function deprtmnteditRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var str=req.responseText;
				
				var data=str.split("/");
				  document.getElementById("selectid").value=data[0];
				  document.getElementById("dept_name").value=data[1];
				  document.getElementById("email").value=data[4];
	 		}
	    }
	}
	function deletedept(val) {
		document.getElementById("deptid").value=val;
		$('#delete_department').modal( "show" ); 
	}
    </script>
    <script type="text/javascript">
		function submit() {
			var isError = false;
			var email=document.getElementById("emailadd").value;
			var emailregEx = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			 if(!emailregEx.test(email)){
				jAlert('error', "Please Enter Email correctly!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				isError = true;
			}else {
				if (isError == true) {
				} else {
					document.getElementById("submt").style.display="none";
					document.getElementById("adddepartmntid").submit();
				}
			}
		
		}
		</script>
</html>