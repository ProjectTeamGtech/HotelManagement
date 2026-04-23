<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>

<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Employee Profile - HRMS admin template</title>
		
		
    </head>
    <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="payroll/js/payrollvalidatation.js"></SCRIPT>
    <script type="text/javascript">
window.onload = function() {
	setnetearn();
}
</script>
    <body>
		
			
			
				<!-- Page Content -->
                <div class="content container-fluid">
				
					<!-- Page Title -->
					<div class="row">
						<div class="col-sm-12">
							<h4 class="page-title">My Profile</h4>
						</div>
					</div>
					<!-- /Page Title -->
					
					<div class="card-box mb-0">
						<div class="row">
							<div class="col-md-12">
								<div class="profile-view">
									<div class="profile-img-wrap">
										<div class="profile-img">
											<a href="#"><img alt="" src="payrollnew/assets/img/profiles/avatar-02.jpg"></a>
										</div>
									</div>
									<div class="profile-basic">
										<div class="row">
											<div class="col-md-5">
												<div class="profile-info-left">
													<h3 class="user-name m-t-0 mb-0"><s:property value="name"/></h3>
													<!-- <h6 class="text-muted">UI/UX Design Team</h6> -->
													<small class="text-muted"><s:property value="designation"/></small>
													<div class="staff-id">Employee ID : <s:property value="emp_id"/></div>
													<div class="staff-id">User ID : <s:property value="userid"/></div>
													<div class="small doj text-muted">Date of Join : <s:property value="date_join"/></div>
													<!-- <div class="staff-msg"><a class="btn btn-custom" href="chat.html">Send Message</a></div> -->
												</div>
											</div>
											<div class="col-md-7">
												<ul class="personal-info">
													<li>
														<div class="title">Phone:</div>
														<div class="text"><a href="#"><s:property value="mobNo"/></a></div>
													</li>
													<li>
														<div class="title">Email:</div>
														<s:if test="email!=null && email!=''">
														<div class="text"><a href="#"><s:property value="email"/></a></div>
														</s:if>
														<s:else>
														<div class="text"><a href="#">-</a></div>
														</s:else>
													</li>
													<li>
														<div class="title">Birthday:</div>
														<s:if test="dob!=null">
														<div class="text"><s:property value="dob"/></div>
														</s:if>
														<s:else>
														<div class="text">-</div>
														</s:else>
													</li>
													<li>
														<div class="title">Address:</div>
														<s:if test="currentaddress!=null && currentaddress!=''">
														<div class="text"><s:property value="currentaddress"/></div>
														</s:if>
														<s:else>
														<div class="text">-</div>
														</s:else>
													</li>
													<li>
														<div class="title">Gender:</div>
														<s:if test="gender!=null && gender!=''">
														<div class="text"><s:property value="gender"/></div>
														</s:if>
														<s:else>
														<div class="text">-</div>
														</s:else>
													</li>
													<!-- <li>
														<div class="title">Reports to:</div>
														<div class="text">
														   <div class="avatar-box">
															  <div class="avatar avatar-xs">
																 <img src="payrollnew/assets/img/profiles/avatar-16.jpg" alt="">
															  </div>
														   </div>
														   <a href="payrollnew/profile.jsp">
																Jeffery Lalor
															</a>
														</div>
													</li> -->
												</ul>
											</div>
										</div>
									</div>
									<%if(loginInfo.isPayrollaccess()) {%>
									<div class="pro-edit"><a data-target="#profile_info" data-toggle="modal" class="edit-icon" href="#"><i class="fa fa-pencil"></i></a></div>
									<%} %>
								</div>
							</div>
						</div>
					</div>
					
					<div class="card-box tab-box">
						<div class="row user-tabs">
							<div class="col-lg-12 col-md-12 col-sm-12 line-tabs">
								<ul class="nav nav-tabs nav-tabs-bottom">
									<li class="nav-item"><a href="#emp_profile" data-toggle="tab" class="nav-link active">Profile</a></li>
									<!-- <li class="nav-item"><a href="#emp_projects" data-toggle="tab" class="nav-link">Projects</a></li> -->
									<%if(loginInfo.isPayrollaccess()) {%>
									<li class="nav-item"><a href="#bank_statutory" data-toggle="tab" class="nav-link">Bank & Statutory <small class="text-danger">(Admin Only)</small></a></li>
									<li class="nav-item"><a href="#emp_projects" data-toggle="tab" class="nav-link">Document <small class="text-danger"></small></a></li>
								<%} %>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="tab-content">
					
						<!-- Profile Info Tab -->
						<div id="emp_profile" class="pro-overview tab-pane fade show active">
							<div class="row">
								<div class="col-md-6">
									<div class="card-box profile-box">
									<%if(loginInfo.isPayrollaccess()) {%>
										<h3 class="card-title">Personal Informations <a href="#" class="edit-icon" data-toggle="modal" data-target="#personal_info_modal"><i class="fa fa-pencil"></i></a></h3>
										<%} %>
										<ul class="personal-info">
											<li>
												<div class="title">Aadhar No.</div>
												<div class="text"><s:property value="adhno"/></div>
											</li>
											<li>
												<div class="title">PAN No.</div>
												<div class="text"><s:property value="panno"/></div>
											</li>
											<li>
												<div class="title">Nationality</div>
												<div class="text"><s:property value="nationality"/></div>
											</li>
											<li>
												<div class="title">Marital status</div>
												<div class="text"><s:property value="maritalsts"/></div>
											</li>
										</ul>
									</div>
								</div>
								<!-- <div class="col-md-6">
									<div class="card-box profile-box">
										<h3 class="card-title">Emergency Contact <a href="#" class="edit-icon" data-toggle="modal" data-target="#emergency_contact_modal"><i class="fa fa-pencil"></i></a></h3>
										<h5 class="section-title">Primary</h5>
										<ul class="personal-info">
											<li>
												<div class="title">Name</div>
												<div class="text">John Doe</div>
											</li>
											<li>
												<div class="title">Relationship</div>
												<div class="text">Father</div>
											</li>
											<li>
												<div class="title">Phone </div>
												<div class="text">9876543210, 9876543210</div>
											</li>
										</ul>
										<hr>
										<h5 class="section-title">Secondary</h5>
										<ul class="personal-info">
											<li>
												<div class="title">Name</div>
												<div class="text">Karen Wills</div>
											</li>
											<li>
												<div class="title">Relationship</div>
												<div class="text">Brother</div>
											</li>
											<li>
												<div class="title">Phone </div>
												<div class="text">9876543210, 9876543210</div>
											</li>
										</ul>
									</div>
								</div> -->
							<!-- </div>
							<div class="row"> -->
								<div class="col-md-6">
									<div class="card-box profile-box">
									<%if(loginInfo.isPayrollaccess()) {%>
										<h3 class="card-title">Bank information <a href="#" class="edit-icon" data-toggle="modal" data-target="#bank_modal"><i class="fa fa-pencil"></i></a></h3>
										<%} %>
										<ul class="personal-info">
											<li>
												<div class="title">Bank Name</div>
												<div class="text"><s:property value="bank_name"/></div>
											</li>
											<li>
												<div class="title">Bank Branch</div>
												<div class="text"><s:property value="bank_branch"/></div>
											</li>
											<li>
												<div class="title">Account No</div>
												<div class="text"><s:property value="account_no"/></div>
											</li>
											<li>
												<div class="title">IFSC Code</div>
												<div class="text"><s:property value="ifsc_code"/></div>
											</li>
											
										</ul>
									</div>
								</div>
								<div class="col-md-6">
									<div class="card-box profile-box">
									<%if(loginInfo.isPayrollaccess()) {%>
										<h3 class="card-title">UAN / PF Information<a href="#" class="edit-icon" data-toggle="modal" data-target="#uanmodal"><i class="fa fa-pencil"></i></a></h3>
										<%} %>
										<ul class="personal-info">
											<li>
												<div class="title">UAN No.</div>
												<div class="text"><s:property value="uanno"/></div>
											</li>
											<li>
												<div class="title">PF No.</div>
												<div class="text"><s:property value="pfno"/></div>
											</li>
											<li>
												<div class="title">ESIC No.</div>
												<div class="text"><s:property value="esicno"/></div>
											</li>
											
										</ul>
									</div>
								</div>
								<div class="col-md-6">
									<div class="card-box profile-box">
									<%if(loginInfo.isPayrollaccess()) {%>
										<h3 class="card-title">Leave<a href="#" class="edit-icon" data-toggle="modal" data-target="#casualleavemodal"><i class="fa fa-pencil"></i></a></h3>
										<%} %>
										<ul class="personal-info">
											<li>
												<div class="title">Casual Leave(Per Year)</div>
												<div class="text"><s:property value="casualleave"/></div>
											</li>
											<li>
												<div class="title">Remaining Casual Leave</div>
												<div class="text"><s:property value="remain_leave"/></div>
											</li>
											
										</ul>
									</div>
								</div>
								<div class="col-md-6">
									<div class="card-box profile-box">
									<%if(loginInfo.isPayrollaccess()) {%>
										<h3 class="card-title">Working Hour<a href="#" class="edit-icon" data-toggle="modal" data-target="#workinghourmodal"><i class="fa fa-pencil"></i></a></h3>
										<%} %>
										<ul class="personal-info">
											
											<li>
												<div class="title">Working Hour/Day</div>
												<div class="text"><s:property value="workinghourday"/> Hr.</div>
											</li>
											<li>
												<div class="title">Working Day/Week</div>
												<div class="text"><s:property value="workingdayweek"/> Days</div>
											</li>
											<li>
												<div class="title">Total Working Hour/Week</div>
												<div class="text"><s:property value="totworkinghourweek"/> Hr</div>
											</li>
										</ul>
									</div>
								</div>
								<!-- <div class="col-md-6">
									<div class="card-box profile-box">
										<h3 class="card-title">Family Informations <a href="#" class="edit-icon" data-toggle="modal" data-target="#family_info_modal"><i class="fa fa-pencil"></i></a></h3>
										<div class="table-responsive">
											<table class="table table-nowrap">
												<thead>
													<tr>
														<th>Name</th>
														<th>Relationship</th>
														<th>Date of Birth</th>
														<th>Phone</th>
														<th></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>Leo</td>
														<td>Brother</td>
														<td>Feb 16th, 2019</td>
														<td>9876543210</td>
														<td class="text-right">
															<div class="dropdown dropdown-action">
																<a aria-expanded="false" data-toggle="dropdown" class="action-icon dropdown-toggle" href="#"><i class="material-icons">more_vert</i></a>
																<div class="dropdown-menu dropdown-menu-right">
																	<a href="#" class="dropdown-item"><i class="fa fa-pencil m-r-5"></i> Edit</a>
																	<a href="#" class="dropdown-item"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
																</div>
															</div>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div> -->
							</div>
							<%-- <div class="row">
								<div class="col-md-6">
									<div class="card-box profile-box">
										<h3 class="card-title">Education Informations <a href="#" class="edit-icon" data-toggle="modal" data-target="#education_info"><i class="fa fa-pencil"></i></a></h3>
										<div class="experience-box">
											<ul class="experience-list">
												<li>
													<div class="experience-user">
														<div class="before-circle"></div>
													</div>
													<div class="experience-content">
														<div class="timeline-content">
															<a href="#/" class="name">International College of Arts and Science (UG)</a>
															<div>Bsc Computer Science</div>
															<span class="time">2000 - 2003</span>
														</div>
													</div>
												</li>
												<li>
													<div class="experience-user">
														<div class="before-circle"></div>
													</div>
													<div class="experience-content">
														<div class="timeline-content">
															<a href="#/" class="name">International College of Arts and Science (PG)</a>
															<div>Msc Computer Science</div>
															<span class="time">2000 - 2003</span>
														</div>
													</div>
												</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="card-box profile-box">
										<h3 class="card-title">Experience <a href="#" class="edit-icon" data-toggle="modal" data-target="#experience_info"><i class="fa fa-pencil"></i></a></h3>
										<div class="experience-box">
											<ul class="experience-list">
												<li>
													<div class="experience-user">
														<div class="before-circle"></div>
													</div>
													<div class="experience-content">
														<div class="timeline-content">
															<a href="#/" class="name">Web Designer at Zen Corporation</a>
															<span class="time">Jan 2013 - Present (5 years 2 months)</span>
														</div>
													</div>
												</li>
												<li>
													<div class="experience-user">
														<div class="before-circle"></div>
													</div>
													<div class="experience-content">
														<div class="timeline-content">
															<a href="#/" class="name">Web Designer at Ron-tech</a>
															<span class="time">Jan 2013 - Present (5 years 2 months)</span>
														</div>
													</div>
												</li>
												<li>
													<div class="experience-user">
														<div class="before-circle"></div>
													</div>
													<div class="experience-content">
														<div class="timeline-content">
															<a href="#/" class="name">Web Designer at Dalt Technology</a>
															<span class="time">Jan 2013 - Present (5 years 2 months)</span>
														</div>
													</div>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div> --%>
						</div>
						<!-- /Profile Info Tab -->
						
						<!-- Projects Tab -->
						<div class="tab-pane fade" id="emp_projects">
						<div class="card">
								<div class="card-body">
									<h3 class="card-title"> Documents<a style="margin-left: 630px" href="#" class="btn btn-custom" data-toggle="modal" data-target="#uploaddoc">Upload Documents</i></a></h3>
									<s:hidden name="emp_id"/>
									<table class="table table-striped  table-hover table-checkable order-column valign-middle" id="example4">
							<thead>
								<tr>
									<th width="15%" scope="col">Submitted File</th>
									<th width="10%" scope="col">Category</th>
									<th width="35%" scope="col">Descriptions</th>
									<th width="15%" scope="col">Reviewed Document</th>
									<th width="10%" scope="col">Delete</th>
									<!-- <th width="15%" scope="col">Reviewed on</th>
									<th width="10%" scope="col">Action</th> -->
								</tr>
							</thead>
							<tbody>
	
								<s:iterator value="filesubmmissionlist">
									<tr>
									<td><a href="downloadDocPayrollEmployee?id=<s:property value="id"/>"> <i class="fa fa-download"></i> Downlaod</a>
										<div style="height: 5px;" class="clearfix"></div> <small><i
											class="fa fa-clock-o"></i> <s:property value="datetime"/></small>
											&nbsp;
								      	<a href="<s:property value="filePathnew"/>" target="_blank"  class="btn btn-circle btn-info btn-xs m-b-10">
												<i class="fa fa-eye"></i>View
											</a>
											</td>
	
									<td><s:property value="category"/></td>
									<td><s:property value="discription"/></td>
									<td><a href="downloadDocPayrollEmployee?id=<s:property value="id"/>"> <i class="fa fa-download"></i> Downlaod
									</a></td>
									<td><a href="#" onclick="deleteuploadedfile(<s:property value="id"/>)"> <i class="fa fa-trash" aria-hidden="true"></i> Delete
									</a></td>
									</tr>
								</s:iterator>
								
	
							</tbody>
						</table>
								</div>
							</div>
						</div>
						<!-- /Projects Tab -->
						
						<!-- Bank Statutory Tab -->
						<div class="tab-pane fade" id="bank_statutory">
							<div class="card">
								<div class="card-body">
									<h3 class="card-title"> Basic Salary Information<a style="margin-left: 630px" href="#" class="btn btn-custom" data-toggle="modal" data-target="#emp_salary">Add Salary</i></a></h3>
									<form action="payrollbasicsalPayrollEmployee">
									<s:hidden name="emp_id"/>
										<div class="row">
											<div class="col-sm-4">
												<div class="form-group">
													<label class="col-form-label">Salary amount <small class="text-muted">per month</small></label>
													<div class="input-group">
														<div class="input-group-prepend">
															<span class="input-group-text"><%=Constants.getCurrency(loginInfo) %></span>
														</div>
														<input type="text" name="basicsal" class="form-control" readonly="readonly"  value='<s:property value="netsal"/>'>
													</div>
												</div>
											</div>
									</form>
								</div>
							</div>
						</div>
						<!-- /Bank Statutory Tab -->
						
					</div>
                </div>
				<!-- /Page Content -->
				
		<!-- Personal Info Modal -->
				<div id="personal_info_modal" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Personal Information</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
							<form action="updatepersonalPayrollEmployee" id="updatepersonalfrm">
							<s:hidden name="emp_id"/>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Aadhar No</label>
												<input type="text" class="form-control" maxlength="12" name="adhno" id="adhno" value='<s:property value="adhno"/>'>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>PAN No</label>
												<div class="cal-icon">
													<input class="form-control" type="text" maxlength="10" name="panno" style="text-transform: uppercase;" value='<s:property value="panno"/>'>
												</div>
											</div>
										</div>
										
										<div class="col-md-6">
											<div class="form-group">
												<label>Marital status <span class="text-danger">*</span></label>
												<s:select id="maritalsts" name="maritalsts" list="{'Single','Married','Divorced','Separated'}" headerKey="0" 
												headerValue="Select" theme="simple" cssClass="select form-control"/>
											</div>
										</div>
									<div class="submit-section">
									<a href="#" onclick="validatepersonal()" class="btn btn-primary submit-btn" style="margin-top: 27px;margin-left: 260px;">Submit</a>
									</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Personal Info Modal -->
				<!-- Add Salary Modal -->
					<div id="emp_salary" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Add Staff Salary</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="payrollbasicsalPayrollEmployee">
								<s:hidden name="emp_id"/>
									<div class="row"> 
										<div class="col-sm-6"> 
											<div class="form-group">
												<label>Staff</label>
												<input class="form-control" type="text" readonly="readonly" value='<s:property value="name"/>'>
											</div>
										</div>
										<div class="col-sm-6"> 
											<label>Net Salary</label>
											<input class="form-control" type="text" name="netsal" value='<s:property value="netsal"/>' id="netsal" readonly="readonly">
										</div>
									</div>
									<div class="row"> 
										<div class="col-sm-6"> 
										<div class="form-group">
												<label>Fixed Salary</label>
												<input class="form-control" type="text" name="fixedsal" value='<s:property value="fixedsal"/>'  id="fixedsal" onchange="setfixedsal()">
											</div>
											<h4 class="text-primary">Earnings</h4>
											<div class="form-group">
											<div class="form-inline">
											<div class="col-sm-6" style="margin-left: -17px">
												<label style="margin-left: -122px;">Basic</label>
												<input class="form-control" type="text" name="basicsal" value='<s:property value="basicsal"/>' onchange="setnetearn('basicsal')" id="basicsal" style="width: 118%">
											</div>&nbsp;&nbsp;
											<div class="col-sm-6" style="    margin-top: 24px">
											<input class="form-control" type="text" style="width: 85%" id="basicsalper" readonly="readonly" placeholder="%" value='<s:property value="basicsalper"/>' name="basicsalper" onchange="setbasicsal('basicsal',this.value)"> &nbsp;%
											</div>
											</div>
											</div>
											<div class="form-group">
											<div class="form-inline">
											<div class="col-sm-6" style="margin-left: -17px">
												<label style="margin-left: -122px;">DA</label>
												<input class="form-control" type="text" name="da" value='<s:property value="da"/>' id="da" onchange="setnetearn('da')" style="width: 118%">
												
											</div>&nbsp;&nbsp;
											<div class="col-sm-6" style="    margin-top: 24px">
											<input class="form-control" type="text" style="width: 85%" id="daper" placeholder="%" value='<s:property value="daper"/>' name="daper" onchange="setpersal('da',this.value,'daper')"> &nbsp;%
											</div>
											</div>
											</div>
										<%-- 	<div class="form-group">
												<label>HRA</label>
												<input class="form-control" type="text" name="hra" value='<s:property value="hra"/>' id="hra" onchange="setnetearn()">
											</div> --%>
											<div class="form-group">
											<div class="form-inline">
											<div class="col-sm-6" style="margin-left: -17px">
												<label style="margin-left: -122px;">HRA</label>
												<input class="form-control" type="text" name="hra" value='<s:property value="hra"/>' id="hra" onchange="setnetearn('hra')" style="width: 118%">
												
											</div>&nbsp;&nbsp;
											<div class="col-sm-6" style="    margin-top: 24px">
											<input class="form-control" type="text" style="width: 85%" id="hraper" value='<s:property value="hraper"/>' placeholder="%" name="hraper" onchange="setpersal('hra',this.value,'hraper')">&nbsp; %
											</div>
											</div>
											</div>
											<div class="form-group">
												<label>Conveyance</label>
												<input class="form-control" type="text" name="conveyance" value='<s:property value="conveyance"/>' id="conveyance" onchange="setnetearn('conveyance')">
											</div>
											<div class="form-group">
												<label>Allowance</label>
												<input class="form-control" type="text" name="perdevallow"  value='<s:property value="perdevallow"/>' id="perdevallow" onchange="setnetearn('perdevallow')">
											</div>
											<div class="form-group">
												<label>Medical  Allowance</label>
												<input class="form-control" type="text" name="medical_allownces" value='<s:property value="medical_allownces"/>' id="medical_allownces" onchange="setnetearn('medical_allownces')">
											</div>
											<!-- <div class="form-group">
												<label>Others</label>
												<input class="form-control" type="text">
											</div>
											<div class="add-more">
												<a href="#"><i class="fa fa-plus-circle"></i> Add More</a>
											</div> -->
										</div>
										<div class="col-sm-6">  
										<div class="form-group">
												<label>Gross Salary </label>
												<input class="form-control" type="text" readonly="readonly" id="gross" name="grossal">
											</div>
											<h4 class="text-primary">Deductions</h4><br>
											<div class="form-group">
											<div class="form-inline">
											<div class="col-sm-6" style="margin-left: -17px">
												<label style="margin-left: -122px;">TDS</label>
												<input class="form-control" type="text" name="tds" value='<s:property value="tds"/>' id="tds" onchange="setnetearn('tds')" style="width: 118%">
												
											</div>&nbsp;&nbsp;
											<div class="col-sm-6" style="    margin-top: 24px">
											<input class="form-control" type="text" style="width: 85%" placeholder="%" value='<s:property value="tdsper"/>' name="tdsper" id="tdsper" onchange="tdscal(this.value,'tdsper','tds')">&nbsp; %
											</div>
											</div>
											</div>
											<div class="form-group">
												<label>ESI</label>
												<input class="form-control" type="text" name="emp_esi" value='<s:property value="emp_esi"/>' id="emp_esi" onchange="setnetearn('emp_esi')">
											</div>
											<div class="form-group">
												<label>PF</label>
												<input class="form-control" type="text" name="emp_pf" value='<s:property value="emp_pf"/>' id="emp_pf" onchange="setnetearn('emp_pf')">
											</div>
											<%-- <div class="form-group">
												<label>Leave</label>
												<input class="form-control" type="text" name="leaves" value='<s:property value="leaves"/>'>
											</div> --%>
											<div class="form-group">
												<label>Prof. Tax</label>
												<input class="form-control" type="text" name="proftax" value='<s:property value="proftax"/>' id="proftax" onchange="setnetearn('proftax')">
											</div>
										</div>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Submit</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Bank detail Modal -->
				<div id="bank_modal" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Bank Information</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form action="updatebankPayrollEmployee" id="updatebankfrm">
							<div class="modal-body">
							<s:hidden name="emp_id"/>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>Bank Name <span class="text-danger">*</span></label>
												<input type="text" class="form-control"  name="bank_name" id="bank_name" value='<s:property value="bank_name"/>'>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Branch Address <span class="text-danger">*</span></label>
													<input class="form-control" type="text"  name="bank_branch" id="bank_branch" value='<s:property value="bank_branch"/>'>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Account No. <span class="text-danger">*</span></label>
												<input class="form-control" type="text" name="account_no" id="account_no" value='<s:property value="account_no"/>'>
											</div>
										</div>
										
										<div class="col-md-6">
											<div class="form-group">
												<label>IFSC Code <span class="text-danger">*</span></label>
												<input class="form-control" type="text" name="ifsc_code" id="ifsc_code" value='<s:property value="ifsc_code"/>'>
											</div>
										</div>
									<div class="submit-section">
										<a href="#" onclick="updateempbankvalidate()" class="btn btn-primary submit-btn" style="margin-top: 27px;margin-left: 260px;">Submit</a>
									</div>
									
							</div>
						</div>
						</form>
						</div>
						
					</div>
				</div>
				
				<!-- /Add Salary Modal -->
				<!-- UAN PF Modal -->
				<div id="uanmodal" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">UAN/ESI Information</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form action="uanpfupdatePayrollEmployee" id="uanpffrm">
							<div class="modal-body">
							<s:hidden name="emp_id"/>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label>UAN No.</label>
												<input type="text" class="form-control"  name="uanno" id="uanno" value='<s:property value="uanno"/>'>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>PF No. </label>
													<input class="form-control" type="text"  name="pfno" id="pfno" value='<s:property value="pfno"/>'>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>ESIC No. </label>
												<input class="form-control" type="text" name="esicno" id="esicno" value='<s:property value="esicno"/>'>
											</div>
										</div>
										
										 
									<div class="submit-section" style="margin-left: 291px;">
										 <a href="#" onclick="updateempuanpfvalidate()" class="btn btn-primary submit-btn" style="margin-top: 27px;">Submit</a> 
										<!-- <button class="btn btn-primary submit-btn">Submit</button> -->
									</div>
									
							</div>
						</div>
						</form>
						</div>
						
					</div>
				</div>
				<!--Leave Given  -->
				<div id="casualleavemodal" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Leaves</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form action="leavegivenPayrollEmployee" id="leavegivenfrm">
							<div class="modal-body">
									<div class="row">
										<div class="col-md-6">
										<s:hidden name="emp_id"/>
											<div class="form-group">
												<label>Casual Leaves</label>
												<input type="Number" class="form-control"  name="casualleave" id="casualleave" value='<s:property value="casualleave"/>'>
											</div>
										</div>
										 
									<div class="submit-section" style="margin-left: 291px;">
										 <a href="#" onclick="updateleavevalidate()" class="btn btn-primary submit-btn" style="margin-top: 27px;">Submit</a> 
										<!-- <button class="btn btn-primary submit-btn">Submit</button> -->
									</div>
									
							</div>
						</div>
						</form>
						</div>
						
					</div>
				</div>
				
				
			<!--Leave Given  -->
				<div id="workinghourmodal" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Working Hour Setting</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form action="workinghourPayrollEmployee" id="workinghourfrm">
							<div class="modal-body">
									<div class="row">
										<div class="col-md-6">
										<s:hidden name="emp_id"/>
											<div class="form-group">
												<label>Working Hour/Day (Hr.)</label>
												<input type="Number" class="form-control"  name="workinghourday" id="workinghourday" value='<s:property value="workinghourday"/>' onchange="calchour()">
											</div>
										</div>
										<div class="col-md-6">
										 <div class="form-group">
												<label>Working Days/Week (Days)</label>
												<input type="Number" class="form-control"  name="workingdayweek" id="workingdayweek" value='<s:property value="workingdayweek"/>' onchange="calchour()">
											</div>
										</div>
										<div class="col-md-6">
										<div class="form-group">
												<label>Total Working Hour/Week (Hr)</label>
												<input type="text" readonly="readonly" class="form-control" value='<s:property value="totworkinghourweek"/>' id="totworkinghourweek1">
												
												<input type="hidden" class="form-control"  name="totworkinghourweek" id="totworkinghourweek" value='<s:property value="totworkinghourweek"/>'>
											</div>
										</div>
										<input type="hidden" name="Otallow" id="Otallow" value="on">
									<div class="submit-section" style="margin-left: 291px;">
										 <a href="#" onclick="updateworkinghourvalidate()" class="btn btn-primary submit-btn" style="margin-top: 27px;">Submit</a> 
										<!-- <button class="btn btn-primary submit-btn">Submit</button> -->
									</div>
									
							</div>
						</div>
						</form>
						</div>
						
					</div>
				</div>	
				<!--Upload Model-->
<div class="modal fade" id="uploaddoc" style="z-index: 9999"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="">Upload New Document</h4>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<form id="upload" method="post" action="uploaddocumentPayrollEmployee"
							enctype="multipart/form-data" theme="simple">
							<div class="form-body">
										<div class="form-group">
											<label class="control-label col-md-3">Category <span
												class="required"> * </span>
													<s:hidden name="emp_id"/>
											</label>
											<div class="col-md-5">
												<s:hidden name="action"></s:hidden>
												<s:select name="filesubmission_category" id="filesubmission_category" list="documentlist"
						listKey="name" listValue="name"
						cssClass="form-control"
						headerValue="Select document" headerKey="0" theme="simple">
					</s:select>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">Remark <span class="required"> * </span></label>
											<div class="col-md-5">
												<textarea name="filesubremark" id="filesubremark" placeholder="Remark" class="form-control-textarea" rows="5" cols="60"></textarea>
											</div>
										</div>



										<div class="form-group">
											<label class="control-label col-md-3">Upload File <span
												class="required"> * </span>
											</label>
											<div class="col-md-5">
												<s:hidden name="subuploadfilesContentType"></s:hidden>
												<s:hidden name="subuploadfilesFileName"></s:hidden>
												<div class="clearfix" style="height: 10px;"></div>
												<!-- <input type="file" name="filesub_uploadfiles" value="" id="filesub_uploadfiles"> -->
												<s:file accept="image/jpeg,video/mp4,.pdf,.zip,.rar" onchange="checksizeoffile()"  name="subuploadfiles" id="filesub_uploadfiles"></s:file>
											</div>
										</div>



									</div>
									<div class="form-actions"  style="margin-left: 188px">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">
												<button class="btn btn-info">Submit</button>
												<button " type="button"
													class="btn btn-info">Cancel</button>
											</div>
										</div>
									</div>

						</form>
					</div>

				</div>




			</div>

			</div>
		</div>
</div>
<!--/Upload Model-->
				<!-- Profile Modal -->
				<div id="profile_info" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Profile Information</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="updatePayrollEmployee" id="updateempfrm">
									<div class="row">
										<div class="col-md-12">
											<div class="profile-img-wrap edit-img">
												<img class="inline-block" src="payrollnew/assets/img/profiles/avatar-02.jpg" alt="user">
												<div class="fileupload btn">
													<span class="btn-text">edit</span>
													<input class="upload" type="file">
												</div>
											</div>
											<s:hidden name="emp_id"/>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label>First Name<span class="text-danger">*</span></label>
														<input type="text" class="form-control" name="firstname" value="<s:property value='firstname'/>" id="firstname">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Last Name<span class="text-danger">*</span></label>
														<input type="text" class="form-control" name="lastname" value="<s:property value='lastname'/>" id="lastname">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Birth Date<span class="text-danger">*</span></label>
														<div class="cal-icon">
															<input class="form-control datetimepicker" type="text" name="dob" value="<s:property value='dob'/>" id="dob">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Gender<span class="text-danger">*</span></label>
														<s:select id="gender" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip "
														data-toggle="tooltip" title="Select Gender"  headerKey="0" headerValue="Select"/>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>Address<span class="text-danger">*</span></label>
												<input type="text" class="form-control" name="currentaddress" value="<s:property value='currentaddress'/>" id="currentaddress">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Email Id<span class="text-danger">*</span></label>
												<input type="text" class="form-control" name="email" value="<s:property value='email'/>" id="email">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group" id="statediv">
												<label>State<span class="text-danger">*</span></label>
												<s:select list="statelist" cssClass="form-control showToolTip chosen-select" onchange="getCitiesajax(this.value)" name="county" id="county"
							 			listKey="name" listValue="name" headerKey="0" headerValue="Select State"  />
											</div>
										</div>
										<div class="col-md-6" id="citydiv">
											<div class="form-group">
												<label>Town<span class="text-danger">*</span></label>
												<s:select list="citylist"  listKey="city" listValue="city" id="town" onchange="getStateAjax(this.value)" cssClass="form-control showToolTip chosen-select" 
											headerKey="0" headerValue="Select City" name="town" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
										<label>Nationality <span class="text-danger">*</span></label>
												<input class="form-control" type="text" name="nationality" id="nationality" value='<s:property value="nationality"/>'>
												</div>
												</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Pin Code</label>
												<input type="text" class="form-control" name="postcode" value="<s:property value='postcode'/>" maxlength="6">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Contact Number<span class="text-danger">*</span></label>
												<input type="text" class="form-control" name="mobNo" value="<s:property value='mobNo'/>" id="mobNo" maxlength="10">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label>Department <span class="text-danger">*</span></label>
												<s:select name="department" id="department" list="departmentlist" listKey="dept_id" listValue="department" cssClass="form-control showToolTip chosen-select">
                                    </s:select>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group" id="designationdiv">
												<label>Designation <span class="text-danger">*</span></label>
												<s:select name="designation" id="designation" list="designationlist" listKey="name" listValue="name" cssClass="form-control showToolTip chosen-select">
                                    </s:select>
											</div>
										</div>
										<%-- <div class="col-md-6">
											<div class="form-group">
												<label>Working Hour/Week<span class="text-danger">*</span></label>
												<input type="Number" class="form-control" name="workhour" value="<s:property value='workhour'/>" id="workhour" maxlength="2">
											</div>
										</div> --%>	
									<div class="col-md-6">
											<div class="form-group">
												<label>Employee Id<span class="text-danger">*</span></label>
												<input type="text" class="form-control" name="employid" value="<s:property value='employid'/>" id="employid">
											</div>
									</div>	
									<div class="col-md-6">
											<div class="form-group">
												<label>Joining Date<span class="text-danger">*</span></label>
												<input type="text" class="form-control datetimepicker" name="date_join" value="<s:property value='date_join'/>" id="date_join">
											</div>
									</div>	
									<div class="col-md-6">
											<div class="form-group">
												<label>Agency Name</label>
												<input type="text" class="form-control" name="agency" value="<s:property value='agency'/>" id="agency">
												<a href="#" onclick="updateempvalidate()" class="btn btn-primary submit-btn" style="margin-top: 27px;margin-left: -110px;">Submit</a>
											</div>
									</div>	
								</form>
							</div>
						</div>
					</div>
				</div>
				
				
				
				<!-- Family Info Modal -->
				<div id="family_info_modal" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title"> Family Informations</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-scroll">
										<div class="card-box">
											<h3 class="card-title">Family Member <a href="javascript:void(0);" class="delete-icon"><i class="fa fa-trash-o"></i></a></h3>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label>Name <span class="text-danger">*</span></label>
														<input class="form-control" type="text">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Relationship <span class="text-danger">*</span></label>
														<input class="form-control" type="text">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Date of birth <span class="text-danger">*</span></label>
														<input class="form-control" type="text">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Phone <span class="text-danger">*</span></label>
														<input class="form-control" type="text">
													</div>
												</div>
											</div>
										</div>
										<div class="card-box">
											<h3 class="card-title">Education Informations <a href="javascript:void(0);" class="delete-icon"><i class="fa fa-trash-o"></i></a></h3>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label>Name <span class="text-danger">*</span></label>
														<input class="form-control" type="text">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Relationship <span class="text-danger">*</span></label>
														<input class="form-control" type="text">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Date of birth <span class="text-danger">*</span></label>
														<input class="form-control" type="text">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label>Phone <span class="text-danger">*</span></label>
														<input class="form-control" type="text">
													</div>
												</div>
											</div>
											<div class="add-more">
												<a href="javascript:void(0);"><i class="fa fa-plus-circle"></i> Add More</a>
											</div>
										</div>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Submit</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Family Info Modal -->
				
				<!-- Emergency Contact Modal -->
				<div id="emergency_contact_modal" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Personal Information</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="card-box">
										<h3 class="card-title">Primary Contact</h3>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Name <span class="text-danger">*</span></label>
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Relationship <span class="text-danger">*</span></label>
													<input class="form-control" type="text">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Phone <span class="text-danger">*</span></label>
													<input class="form-control" type="text">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Phone 2</label>
													<input class="form-control" type="text">
												</div>
											</div>
										</div>
									</div>
									<div class="card-box">
										<h3 class="card-title">Primary Contact</h3>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Name <span class="text-danger">*</span></label>
													<input type="text" class="form-control">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Relationship <span class="text-danger">*</span></label>
													<input class="form-control" type="text">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Phone <span class="text-danger">*</span></label>
													<input class="form-control" type="text">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Phone 2</label>
													<input class="form-control" type="text">
												</div>
											</div>
										</div>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Submit</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Emergency Contact Modal -->
				
				<!-- Education Modal -->
				<div id="education_info" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title"> Education Informations</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-scroll">
										<div class="card-box">
											<h3 class="card-title">Education Informations <a href="javascript:void(0);" class="delete-icon"><i class="fa fa-trash-o"></i></a></h3>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<input type="text" value="Oxford University" class="form-control floating">
														<label class="focus-label">Institution</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<input type="text" value="Computer Science" class="form-control floating">
														<label class="focus-label">Subject</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<div class="cal-icon">
															<input type="text" value="01/06/2002" class="form-control floating datetimepicker">
														</div>
														<label class="focus-label">Starting Date</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<div class="cal-icon">
															<input type="text" value="31/05/2006" class="form-control floating datetimepicker">
														</div>
														<label class="focus-label">Complete Date</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<input type="text" value="BE Computer Science" class="form-control floating">
														<label class="focus-label">Degree</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<input type="text" value="Grade A" class="form-control floating">
														<label class="focus-label">Grade</label>
													</div>
												</div>
											</div>
										</div>
										<div class="card-box">
											<h3 class="card-title">Education Informations <a href="javascript:void(0);" class="delete-icon"><i class="fa fa-trash-o"></i></a></h3>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<input type="text" value="Oxford University" class="form-control floating">
														<label class="focus-label">Institution</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<input type="text" value="Computer Science" class="form-control floating">
														<label class="focus-label">Subject</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<div class="cal-icon">
															<input type="text" value="01/06/2002" class="form-control floating datetimepicker">
														</div>
														<label class="focus-label">Starting Date</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<div class="cal-icon">
															<input type="text" value="31/05/2006" class="form-control floating datetimepicker">
														</div>
														<label class="focus-label">Complete Date</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<input type="text" value="BE Computer Science" class="form-control floating">
														<label class="focus-label">Degree</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus focused">
														<input type="text" value="Grade A" class="form-control floating">
														<label class="focus-label">Grade</label>
													</div>
												</div>
											</div>
											<div class="add-more">
												<a href="javascript:void(0);"><i class="fa fa-plus-circle"></i> Add More</a>
											</div>
										</div>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Submit</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Education Modal -->
				
				<!-- Experience Modal -->
				<div id="experience_info" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Experience Informations</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-scroll">
										<div class="card-box">
											<h3 class="card-title">Experience Informations <a href="javascript:void(0);" class="delete-icon"><i class="fa fa-trash-o"></i></a></h3>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group form-focus">
														<input type="text" class="form-control floating" value="Digital Devlopment Inc">
														<label class="focus-label">Company Name</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus">
														<input type="text" class="form-control floating" value="United States">
														<label class="focus-label">Location</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus">
														<input type="text" class="form-control floating" value="Web Developer">
														<label class="focus-label">Job Position</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus">
														<div class="cal-icon">
															<input type="text" class="form-control floating datetimepicker" value="01/07/2007">
														</div>
														<label class="focus-label">Period From</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus">
														<div class="cal-icon">
															<input type="text" class="form-control floating datetimepicker" value="08/06/2018">
														</div>
														<label class="focus-label">Period To</label>
													</div>
												</div>
											</div>
										</div>
										<div class="card-box">
											<h3 class="card-title">Experience Informations <a href="javascript:void(0);" class="delete-icon"><i class="fa fa-trash-o"></i></a></h3>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group form-focus">
														<input type="text" class="form-control floating" value="Digital Devlopment Inc">
														<label class="focus-label">Company Name</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus">
														<input type="text" class="form-control floating" value="United States">
														<label class="focus-label">Location</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus">
														<input type="text" class="form-control floating" value="Web Developer">
														<label class="focus-label">Job Position</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus">
														<div class="cal-icon">
															<input type="text" class="form-control floating datetimepicker" value="01/07/2007">
														</div>
														<label class="focus-label">Period From</label>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group form-focus">
														<div class="cal-icon">
															<input type="text" class="form-control floating datetimepicker" value="08/06/2018">
														</div>
														<label class="focus-label">Period To</label>
													</div>
												</div>
											</div>
											<div class="add-more">
												<a href="javascript:void(0);"><i class="fa fa-plus-circle"></i> Add More</a>
											</div>
										</div>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Submit</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				
				
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
		
		<!-- Datetimepicker JS -->
		<script src="assets/js/moment.min.js"></script>
		<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
		
		<!-- Tagsinput JS -->
		<script src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>

		<!-- Custom JS -->
		<script src="assets/js/app.js"></script>
		
    </body>
</html>