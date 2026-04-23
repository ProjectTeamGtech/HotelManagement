<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<script>


window.onload = function () {
	
	document.getElementById("design").className="active";	
}



</script>
   
    
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Designations - HRMS admin template</title>
		 <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
		
    </head>
    <body>
		
			<!-- Page Wrapper -->
			
				<!-- Page Content -->
                <div class="content container-fluid">
				
					<!-- Page Title -->
					<div class="row">
						<div class="col-sm-5 col-5">
							<h4 class="page-title">Designations</h4>
						</div>
						<div class="col-sm-7 col-7 text-right m-b-30">
							<a href="#" class="btn add-btn" data-toggle="modal" data-target="#add_designation"><i class="fa fa-plus"></i> Add Designation</a>
						</div>
					</div>
					<!-- /Page Title -->
					
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
							<%int i=1; %>
								<table class="table table-striped custom-table mb-0 datatable">
									<thead>
										<tr>
											<th style="width: 30px;">#</th>
											<th>Designation </th>
											<th>Department </th>
											<th class="text-right">Action</th>
										</tr>
									</thead>
									<tbody>
									<s:iterator value="designationList">
										<tr>
											<td><%=i++ %></td>
											<td><s:property value="name"/></td>
											<td><s:property value="dept_name"/></td>
											<td class="text-right">
                                            <div class="dropdown dropdown-action">
													<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onclick="showmenu(<s:property value="id"/>)"><i class="material-icons">more_vert</i></a>
                                                <div class="dropdown-menu dropdown-menu-right" id="showmenu<s:property value="id"/>">
                                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#edit_designation" onclick="designtnedit(<s:property value="id"/>)"><i class="fa fa-pencil m-r-5"></i> Edit</a>
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

				<!-- Add Designation Modal -->
				<div id="add_designation" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Add Designation</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="adddesignationPayrollDepartment" id="adddesignationid">
									<div class="form-group">
										<label>Designation Name <span class="text-danger">*</span></label>
										<input class="form-control" type="text" name="name">
									</div>
									<div class="form-group">
										<label>Department <span class="text-danger">*</span></label>
										<s:select name="dept_name" id="department" list="deptlist" listKey="dept_id" listValue="department" cssClass="form-control showToolTip chosen-select">
                                    </s:select>
									</div>
									<div class="submit-section">
										
										<a href="#" class="btn btn-primary submit-btn" onclick="submit()" id="submt">Submit</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Add Designation Modal -->
				
				<!-- Edit Designation Modal -->
				<div id="edit_designation" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Edit Designation</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="updatedesigntnPayrollDepartment">
								<s:hidden name="id" id="ids"/>
									<div class="form-group">
										<label>Designation Name <span class="text-danger">*</span></label>
										<s:textfield cssClass="form-control" id="designame" name="name"/>
									</div>
									<div class="form-group">
										<label>Department <span class="text-danger">*</span></label>
										<s:select name="dept_name" id="department1" list="deptlist" listKey="dept_id" listValue="department" cssClass="form-control showToolTip chosen-select">
                                    </s:select>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Save</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Edit Designation Modal -->
				
				<!-- Delete Designation Modal -->
				<div class="modal custom-modal fade" id="delete_designation" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-header">
									<h3>Delete Designation</h3>
									<p>Are you sure want to delete?</p>
								</div>
								<div class="modal-btn delete-action">
								<input type="hidden" id="desiid">
									<div class="row">
										<div class="col-6">
											<a href="#" class="btn btn-primary continue-btn" onclick="deletedesigntn()">Delete</a>
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
        <script src="assets/js/jquery-3.2.1.min.js"></script>
		
		<!-- Bootstrap Core JS -->
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
		
		<!-- Slimscroll JS -->
		<script src="assets/js/jquery.slimscroll.min.js"></script>
		
		<!-- Select2 JS -->
		<script src="assets/js/select2.min.js"></script>
		
		<!-- Datatable JS -->
		<script src="assets/js/jquery.dataTables.min.js"></script>
		<script src="assets/js/dataTables.bootstrap4.min.js"></script>
		
		<!-- Custom JS -->
		<script src="assets/js/app.js"></script>
<script type="text/javascript">
    function showmenu(val){
    	 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right show"; 
    	 setTimeout(function() {
    		 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right"; 
    		}, alertmsgduration);
    }
    
    </script>
     <script type="text/javascript">
   function designtnedit(val){
	   var url="editdesigntnPayrollDepartment?selectedid="+val+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   

	    req.onreadystatechange = designtneditRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	   
	}

	function designtneditRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var str=req.responseText;
				
				var data=str.split("/");
				  document.getElementById("ids").value=data[0];
				  document.getElementById("designame").value=data[1];
				  document.getElementById("department1").value=data[2];
	 		}
	    }
	}
	
	
	function showmenu(val){
		 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right show"; 
		 setTimeout(function() {
			 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right"; 
			}, alertmsgduration);
	}
	
	function deletedept(val) {
		document.getElementById("desiid").value=val;
		$('#delete_designation').modal( "show" ); 
	}
    </script>
    <script type="text/javascript">
		function submit() {
		document.getElementById("submt").style.display="none";
		document.getElementById("adddesignationid").submit();
		}
		</script>
    </body>
</html>