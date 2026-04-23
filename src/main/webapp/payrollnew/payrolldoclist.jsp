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
        <title>Document Type - HRMS admin template</title>
		 <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
		
    </head>
    <script>


window.onload = function () {
	
	document.getElementById("qualification").className="active";	
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
							<h4 class="page-title">Document Type</h4>
						</div>
						<div class="col-sm-7 col-7 text-right m-b-30">
							<a href="#" class="btn add-btn" data-toggle="modal" data-target="#add_documentlist"><i class="fa fa-plus"></i> Add Document Type</a>
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
											<th>Document Type</th>
											<th class="text-right">Action</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="documentlist">
										<tr>
											<td><%=i++ %></td>
											<td><s:property value="name"/></td>
											 <td class="text-right">
                                            <div class="dropdown dropdown-action">
													<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onclick="showmenu(<s:property value="id"/>)"><i class="material-icons">more_vert</i></a>
                                                <div class="dropdown-menu dropdown-menu-right" id="showmenu<s:property value="id"/>">
                                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#edit_documentlist" onclick="documentlistedit(<s:property value="id"/>)"><i class="fa fa-pencil m-r-5"></i> Edit</a>
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
				<div id="add_documentlist" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Add Document Type</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="adddocumentlistPayrollMaster" id="adddocumentlistid">
									<div class="form-group">
										<label>Document Type <span class="text-danger">*</span></label>
										<input class="form-control" type="text" name="name">
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
				<div id="edit_documentlist" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Edit Document Type</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="updatedocumentlistPayrollMaster">
								<s:hidden id="selectid" name="id"/>
									<div class="form-group">
										<label>Document Type <span class="text-danger">*</span></label>
										<input class="form-control" id="name" type="text" name="name">
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
				<div class="modal custom-modal fade" id="delete_qualification" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-header">
									<h3>Delete Document Type</h3>
									<p>Are you sure want to delete?</p>
								</div>
								<div class="modal-btn delete-action">
								<input type="hidden" id="qualificid">
									<div class="row">
										<div class="col-6">
											<a href="#" class="btn btn-primary continue-btn" onclick="deletequalification()">Delete</a>
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
   function documentlistedit(val){
	   var url="editdocumentlistPayrollMaster?selectedid="+val+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   

	    req.onreadystatechange = documentlisteditRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	   
	}

	function documentlisteditRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var str=req.responseText;
				
				var data=str.split("/");
				  document.getElementById("selectid").value=data[0];
				  document.getElementById("name").value=data[1];
	 		}
	    }
	}
	function deletedept(val) {
		document.getElementById("qualificid").value=val;
		$('#delete_qualification').modal( "show" ); 
	}
    </script>
    <script type="text/javascript">
		function submit() {
		document.getElementById("submt").style.display="none";
		document.getElementById("adddocumentlistid").submit();
		}
		</script>
</html>