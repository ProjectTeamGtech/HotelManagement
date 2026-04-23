<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="payroll/js/payrollvalidatation.js"></SCRIPT>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Settings - HRMS admin template</title>
		
    </head>
     <script type="text/javascript">
  $(function() {
    $('.datetimepicker').datetimepicker({
      language: 'pt-BR'
    });
  });
</script>
    <body>
			<!-- Sidebar -->
            
			
				<!-- Page Content -->
                <div class="content container-fluid">
					<div class="row">
						<div class="col-md-8 offset-md-2">
							<form action="updatePayrollDashBoard">
								<h3 class="page-title">Company Settings</h3>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label>Company Name <span class="text-danger">*</span></label>
											<input class="form-control" type="text" value='<s:property value="company_name"/>' readonly="readonly">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<label>Working Hour <span class="text-danger">*</span></label>
											<input class="form-control"  value='<s:property value="no_hours"/>' name="no_hours" type="Number" min="1" onkeypress="return event.charCode != 45">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label>Address</label>
											<input class="form-control " value='<s:property value="address"/>' type="text" readonly="readonly">
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-lg-3">
										<div class="form-group">
											<label>Country</label>
											<input class="form-control"  value='<s:property value="country"/>' type="text" readonly="readonly">
										</div>
									</div>
									<div class="col-sm-6 col-md-6 col-lg-3" id="citydiv">
											<div class="form-group">
												<label>Town<span class="text-danger">*</span></label>
												<%-- <s:select list="citylist"  listKey="city" listValue="city" id="town" onchange="getStateAjax(this.value)" cssClass="form-control showToolTip chosen-select" 
											headerKey="0" headerValue="Select City" name="town" readonly="readonly"/> --%>
											<input class="form-control"  value='<s:property value="city"/>' type="text" readonly="readonly">
											</div>
										</div>
									<div class="col-sm-6 col-md-6 col-lg-3"id="statediv">
										<div class="form-group">
												<label>State<span class="text-danger">*</span></label>
												<%-- <s:select list="statelist" cssClass="form-control showToolTip chosen-select" onchange="getCitiesajax(this.value)" name="county" id="county"
							 			listKey="name" listValue="name" headerKey="0" headerValue="Select State"  readonly="readonly"/> --%>
											<input class="form-control"  value='<s:property value="state"/>' type="text" readonly="readonly">
											</div>
										</div>
									<div class="col-sm-6 col-md-6 col-lg-3">
										<div class="form-group">
											<label>Postal Code</label>
											<input class="form-control" value='<s:property value="pincode"/>' type="text" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label>Email</label>
											<input class="form-control" value='<s:property value="email"/>' type="email" readonly="readonly">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<label>Phone Number</label>
											<input class="form-control" value='<s:property value="landLine"/>' type="text" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label>Mobile Number</label>
											<input class="form-control" value='<s:property value="mobile"/>' type="text" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group">
											<label>Website Url</label>
											<input class="form-control" value='<s:property value="websiteurl"/>' type="text" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="submit-section">
									<button class="btn btn-primary submit-btn">Save</button>
								</div>
								 <s:hidden name="id" id="id"/>
							</form>
						</div>
					</div>
                </div>
				<!-- /Page Content -->
				


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

		<!-- Custom JS -->
		<script src="assets/js/app.js"></script>
		
    </body>
</html>