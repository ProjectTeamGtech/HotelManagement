<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <meta name="description" content="Smarthr - Bootstrap Admin Template">
		<meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, management, minimal, modern, accounts, invoice, html5, responsive, CRM, Projects">
        <meta name="author" content="Dreamguys - Bootstrap Admin Template">
        <meta name="robots" content="noindex, nofollow">
        <title>Salary - HRMS admin template</title>
		
		<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
    </head>
    <script type="text/javascript" src="common/js/pagination.js"></script>
     <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
    <SCRIPT type="text/javascript">
    
        function paySal() {
        	$('#loader-wrapper').modal( "show" );
        	 $('.pacss').each(function() { 
     			
     			if(this.checked){
     				this.value=true;
     			}
     		});
           document.myform1.submit();
        }
       
        function paySalslip() {
        	$('#loader-wrapper').modal( "show" );
       	 $('.pacss').each(function() { 
    			
    			if(this.checked){
    				this.value=true;
    			}
    		});
       	document.getElementById("paysalslip").value=1;
          document.myform1.submit();
       }
    </SCRIPT>
    <script  type="text/javascript">
	function setActionForAll(){
	/* 	var hideselect="";
	  $('.pacss').each(function() { 
			 if(this.checked==true) {
				if(hideselect==''){
					hideselect =this.value;
				} else{
					hideselect =hideselect + ',' + this.value;	
				}
			 }     
		}); */
		
	  $('.pacss').each(function() { 
			this.checked=true; 
		});
	  
	}
	
	  </script>
	  <SCRIPT type="text/javascript">
  
     window.onload=function(){
    	 var year=document.getElementById("selectyr").value;
     	document.getElementById("year").value=year;
     		
     		document.getElementById("empsalary").className="active";	
          
     };
    
  </SCRIPT>
  <s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
    <body>
		
			
				<!-- Page Content -->
                <div class="content container-fluid">
				
					<!-- Page Title -->
					<div class="row">
						<div class="col-sm-4 col-5">
							<h4 class="page-title">Employee Salary</h4>
						</div>
						<!-- <div class="col-sm-8 col-7 text-right m-b-30">
							<a href="#" class="btn add-btn" data-toggle="modal" data-target="#add_salary"><i class="fa fa-plus"></i> Add Salary</a>
						</div> -->
					</div>
					<!-- /Page Title -->
					
					<!-- Search Filter -->
					 <s:form action="salaryPayrollMaster" name="myform" method="post">
						
                   <s:hidden name="selectedyear" id="selectyr"/>
                   
					<div class="row filter-row">
					   <!-- <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<div class="form-group form-focus">
								<input type="text" class="form-control floating">
								<label class="focus-label">Employee Name</label>
							</div>
					   </div> -->
					   <!-- <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<div class="form-group form-focus select-focus">
								<select class="select floating"> 
									<option value=""> -- Select -- </option>
									<option value="">Employee</option>
									<option value="1">Manager</option>
								</select>
								<label class="focus-label">Role</label>
							</div>
					   </div> -->
					   <%if(loginfo.isPayrollaccess()){ %>
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12 hidden" > 
							<div class="form-group form-focus select-focus">
									<s:select cssClass="select floating"
										list="#{'0':'WIP', '1':'Processed', '2':'Paid'}"
										theme="simple" id="status" name="status" style="width: 45%" />
								<label class="focus-label">Status</label>
							</div>
					   </div>
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12"> 
							<div class="form-group form-focus">
							<s:select name="department" id="department" list="departmentlist"
						listKey="dept_id" listValue="department"
						cssClass="select floating"
						headerValue="Select Department" headerKey="0" theme="simple">
					</s:select>
					<label class="focus-label">Select Department</label>
							</div>
					   </div>
					   
					   <%} %>
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<div class="form-group form-focus">
								<div class="cal-icon">
								<s:select cssClass="select floating"
										list="#{'0':'January', '1':'February', '2':'March', '3':'April' , '4':'May', '5':'June', '6':'July','7':'August','8':'September','9':'October','10':'November','11':'December'}"
										theme="simple" id="month" name="month" style="width: 45%" />
								</div>
								<label class="focus-label">Select Month</label>
							</div>
						</div>
					   <div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<div class="form-group form-focus">
								<div>
									<select name="year"
										id="year" class="mdb-select md-form form-control " style="width: 65%;height: 48px;" placeholder="Year">
										<%
											for (int k = 1980; k <= 2100; k++) {
										%>
							<option value="<%=k%>"><%=k%></option>
										<%
											}
										%>
							</select>
								</div>
								<!-- <label class="focus-label">Select Year</label> -->
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-2 col-12">  
							<input type="submit" value="Go" class="btn btn-sm btn-primary">   
						</div>     
                    </div>
                    </s:form>
					<!-- /Search Filter -->
					
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
							<form action="finalizesalaryPayrollMaster" name="myform1" method="post">
                        <s:hidden name="selectedmonth"/>
                        <s:hidden name="year"/>
                        <s:hidden name="status"/>
                        <s:hidden name="paysalslip" value="0" id="paysalslip"/>
								<table class="table table-striped custom-table datatable">
									<thead>
										<tr>
										<%-- <s:if test="status!=2">
                                	<th rowspan="5">Select All <br>
                                	<input type="checkbox"  class="pacssw" onclick="setActionForAll()" value="<s:property value="emp_id"/>"/>
                                	</th>
                                	</s:if> --%>
											<th>Employee</th>
											<th>Employee ID</th>
											<th>Email</th>
											<th>Join Date</th>
											<th>Role</th>
											<th>Salary</th>
											<th>Status</th>
											<s:if test="status==2">
											<th>Payslip</th>
											</s:if>
											<s:if test="status==1">
											<th class="text-right">Action</th>
											</s:if>
										</tr>
									</thead>
									<tbody>
									<%int i=0; int j=0; %>
                                <s:iterator value="salaryList">
										<tr>
										<%-- <s:if test="status!='Paid'">
                                	<td><input type="checkbox" name='collectionsal[<%=j%>].iselect' class="pacss"></td>
                                	</s:if>
                                	<s:else>
                                	<td></td>
                                	</s:else> --%>
											<td>
												
													<a href="payrollprofilePayrollEmployee?empid=<s:property value="emp_id"/>"><s:property value="emp_name"/></a>
											</td>
											<input type="hidden" id="empid1" name="empid1" value="<s:property value="emp_id"/>">
											<td><s:property value="empcode"/></td>
											<td><s:property value="email"/></td>
											<td><s:property value="datejoin"/></td>
											<td>
												<s:property value="designation"/>
											</td>
											<td><s:property value="netpay"/><input type="hidden" name="calnetpay" value="<s:property value="calnetpay"/>"></td>
                                   		  		
                                   		  	<td>
                                   		  	<s:if test="status=='Paid'">
                                   		  	<s:property value="status"/>
                                   		  	</s:if>
                                   		   <s:elseif test="status=='Unpaid'">
                      			           <a href="#" type="button" class="btn btn-sm btn-primary" onclick="paySalslip1(<s:property value="emp_id"/>,1)"><s:property value="status"/></a>
                      			 	       </s:elseif>
                      			 	       <s:else>
                      			 	        <a href="#" type="button" class="btn btn-sm btn-primary" onclick="paySal1(<s:property value="emp_id"/>,0)"><s:property value="status"/></a>
                      			 	       
                      			 	       </s:else>
                                   		  	</td>
											<s:if test="status=='Paid'">
											<td><a class="btn btn-sm btn-primary" href="#" onclick="opencPopup('payslipPayrollincrement?emp_id=<s:property value="emp_id" />&month=<s:property value="selectedmonth" />&year=<s:property value="selectedyear" />')">Salary Slip</a></td>
											
											</s:if>
											<s:if test="status==1">
											<td class="text-right">
												<div class="dropdown dropdown-action">
													<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onclick="showmenu(<s:property value="emp_id"/>)"><i class="material-icons">more_vert</i></a>
													<div class="dropdown-menu dropdown-menu-right"id="showmenu<s:property value="emp_id"/>">
														<a class="dropdown-item" href="#" onclick="getallownces(<s:property value='emp_id'/>,<s:property value='selectedmonth'/>,<s:property value='selectedyear'/>)""><i class="fa fa-pencil m-r-5"></i> Edit</a>
														<!-- <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_salary"><i class="fa fa-trash-o m-r-5"></i> Delete</a> -->
													</div>
												</div>
											</td></s:if>
											<input type="hidden" name="collectionsal[<%=j%>].status" value="<s:property value="status"/>"/>
											<input type="hidden" name="collectionsal[<%=j%>].totaldays" value="<s:property value="totaldays"/>">
                                        <input type="hidden" name="collectionsal[<%=j%>].workingdays" value="<s:property value="workingdays"/>">
                                        <input type="hidden" name="collectionsal[<%=j%>].workeddays" value="<s:property value="workeddays"/>">
                                        <input type="hidden" name="collectionsal[<%=j%>].holidays" value="<s:property value="holidays"/>">
                                        <input type="hidden" name="collectionsal[<%=j%>].total_leaves" value="<s:property value="total_leaves"/>">
										<input type="hidden" name="collectionsal[<%=j%>].basic" value="<s:property value="basic"/>">
										<input type="hidden" name="collectionsal[<%=j%>].medical_allowance" value="<s:property value="medical_allowance"/>">
										<input type="hidden" name="collectionsal[<%=j%>].hra" value="<s:property value="hra"/>">
										<input type="hidden" name="collectionsal[<%=j%>].da" value="<s:property value="da"/>">
										<input type="hidden" name="collectionsal[<%=j%>].conveyance" value="<s:property value="conveyance"/>">
										<input type="hidden" name="collectionsal[<%=j%>].Perdevallow" value="<s:property value="Perdevallow"/>">
										<input type="hidden" name="collectionsal[<%=j%>].Emp_pf" value="<s:property value="Emp_pf"/>">
										<input type="hidden" name="collectionsal[<%=j%>].Emp_esi" value="<s:property value="Emp_esi"/>">
										<input type="hidden" name="collectionsal[<%=j%>].leave" value="<s:property value="leave"/>">
										<input type="hidden" name="collectionsal[<%=j%>].Prefessional_tax" value="<s:property value="Prefessional_tax"/>">
										<input type="hidden" name="collectionsal[<%=j%>].Tds" value="<s:property value="Tds"/>">
										<input type="hidden" name="collectionsal[<%=j%>].Gross_pay" value="<s:property value="Gross_pay"/>">
										<input type="hidden" name="collectionsal[<%=j%>].Allowances" value="<s:property value="Allowances"/>">
	                                    <input type="hidden" name="collectionsal[<%=j%>].deductions" value="<s:property value="deductions"/>">
	                                    <input type="hidden" name="collectionsal[<%=j%>].emp_id" value="<s:property value="emp_id"/>"> 
	                                    <input type="hidden" name="collectionsal[<%=j%>].id" value="<s:property value="id"/>">
										<s:hidden name="selectedempid" id="selectedempid"/>
										</tr>
										 <% j++; %>
										</s:iterator>
									</tbody>
								</table>
								 <input type="hidden" name="testnum" value="<%=j%>">
								</form>
							<%-- 	 <%if(loginfo.isPayrollaccess()){ %>
								<s:if test="status=='WIP'">
								 <a href="#" type="button" class="btn btn-sm btn-primary" onclick="paySal()">Process Payroll</a>
								 </s:if>
								 <s:elseif test="status=='Unpaid'">
                      			 <a href="#" type="button" class="btn btn-sm btn-primary" onclick="paySalslip()">Process PaySlip</a>
                      			 	 </s:elseif>
                      			 	 <%} %> --%>
							</div>
						</div>
						 
					</div>
					
                </div>
               <s:form action="salaryPayrollMaster" name="paginationForm" id="paginationForm" theme="simple" style="padding-left: 59px;">
							     <s:hidden name="year"></s:hidden>
							     <s:hidden name="month"></s:hidden>
							       <s:hidden name="status"></s:hidden>
								<div class="row" style="margin-top:15px;">
									<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
										Total:<label class="text-info"><s:property value="totalRecords" /></label>
									</div>
									<s:include value="/common/pages/pagination.jsp"></s:include>
								</div>
							</s:form>
				<!-- /Page Content -->
				
				<!-- Add Salary Modal -->
				<div id="add_salary" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Add Staff Salary</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="row"> 
										<div class="col-sm-6"> 
											<div class="form-group">
												<label>Select Staff</label>
												<select class="select"> 
													<option>John Doe</option>
													<option>Richard Miles</option>
												</select>
											</div>
										</div>
										<div class="col-sm-6"> 
											<label>Net Salary</label>
											<input class="form-control" type="text">
										</div>
									</div>
									<div class="row"> 
										<div class="col-sm-6"> 
											<h4 class="text-primary">Earnings</h4>
											<div class="form-group">
												<label>Basic</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>DA(40%)</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>HRA(15%)</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>Conveyance</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>Allowance</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>Medical  Allowance</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>Others</label>
												<input class="form-control" type="text">
											</div>
											<div class="add-more">
												<a href="#"><i class="fa fa-plus-circle"></i> Add More</a>
											</div>
										</div>
										<div class="col-sm-6">  
											<h4 class="text-primary">Deductions</h4>
											<div class="form-group">
												<label>TDS</label>
												<input class="form-control" type="text">
											</div> 
											<div class="form-group">
												<label>ESI</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>PF</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>Leave</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>Prof. Tax</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>Labour Welfare</label>
												<input class="form-control" type="text">
											</div>
											<div class="form-group">
												<label>Others</label>
												<input class="form-control" type="text">
											</div>
											<div class="add-more">
												<a href="#"><i class="fa fa-plus-circle"></i> Add More</a>
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
				<!-- /Add Salary Modal -->
				
				<!-- Edit Salary Modal -->
				<div id="edit_salary" class="modal custom-modal fade" role="dialog">
					<div class="modal-dialog modal-dialog-centered modal-md" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Edit Staff Salary</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="editprocesssalaryPayrollMaster">
									<s:hidden name="empid" id="empid"/>
												<s:hidden name="month" id="month"/>
												<s:hidden name="year" id="year"/>
												<s:hidden name="status" />
									<div class="row"> 
										<div class="col-sm-6"> 
											<div class="form-group">
												<label>Staff</label>
												<input type="text" class="form-control" readonly="readonly" name="empname" id="empname">
											</div>
										</div>
										<div class="col-sm-6"> 
											<label>Net Salary</label>
											<input class="form-control" type="text" name="netsal" id="netsal" readonly="readonly">
										</div>
									</div>
									<div class="row"> 
										<div class="col-sm-6"> 
											<h4 class="text-primary">Earnings</h4>
											<div class="form-group">
												<label>Basic</label>
												<input class="form-control" type="text" name="basicsal" id="basicsal"onchange="updateallownces('basic',this.value,'basicsal')">
											</div>
											<div class="form-group">
												<label>DA</label>
												<input class="form-control" type="text" name="da" id="da" onchange="updateallownces('da',this.value,'da')">
											</div>
											<div class="form-group">
												<label>HRA</label>
												<input class="form-control" type="text" name="hra" id="hra" onchange="updateallownces('hra',this.value,'hra')">
											</div>
											<div class="form-group">
												<label>Conveyance</label>
												<input class="form-control" type="text" name="conveyance" id="conveyance" onchange="updateallownces('conveyance',this.value,'conveyance')">
											</div>
											<div class="form-group">
												<label>Allowance</label>
												<input class="form-control" type="text" name="perdevallow" id="perdevallow" onchange="updateallownces('perdevallow',this.value,'perdevallow')">
											</div>
											<div class="form-group">
												<label>Medical  Allowance</label>
												<input class="form-control" type="text" name="medical_allownces" id="medical_allownces" onchange="updateallownces('medical_allowance',this.value,'medical_allownces')">
											</div>
											<div class="form-group">
												<label>OT</label>
												<input class="form-control" type="text" name="ot" id="ot" onchange="updateallownces('ot',this.value,'ot')">
											</div>
											<div class="form-group">
												<label>Advance</label>
												<input class="form-control" type="text" name="advance" id="advance" onchange="updateallownces('advance',this.value,'advance')">
											</div>
											<input type="hidden" id="originalbasicsal">
											<input type="hidden" id="originalda">
											<input type="hidden" id="originalhra">
											<input type="hidden" id="originalconveyance">
											<input type="hidden" id="originalperdevallow">
											<input type="hidden" id="originalmedical_allownces">
											<input type="hidden" id="originalot">
											<input type="hidden" id="originaladvance">
											<!-- <div class="form-group">
												<label>Others</label>
												<input class="form-control" type="text">
											</div>   -->
										</div>
										<div class="col-sm-6">  
											<h4 class="text-primary">Deductions</h4>
											<div class="form-group">
												<label>TDS</label>
												<input class="form-control" type="text" name="tds" id="tds" onchange="updatededuction('tds',this.value,'tds')">
											</div> 
											<div class="form-group">
												<label>ESI</label>
												<input class="form-control" type="text" name="emp_esi" id="emp_esi" onchange="updatededuction('emp_esi',this.value,'emp_esi')">
											</div>
											<div class="form-group">
												<label>PF</label>
												<input class="form-control" type="text" name="emp_pf" id="emp_pf" onchange="updatededuction('emp_pf',this.value,'emp_pf')">
											</div>
											<div class="form-group">
												<label>Leave</label>
												<input class="form-control" type="text" name="leaves" id="leaves" onchange="updatededuction('leaves',this.value,'leaves')">
											</div>
											<div class="form-group">
												<label>Prof. Tax</label>
												<input class="form-control" type="text" name="proftax" id="proftax" onchange="updatededuction('professional_tax',this.value,'proftax')">
											</div>
											<div class="form-group">
												<label>Loan</label>
												<input class="form-control" type="text" name="loan" id="loan" onchange="updatededuction('loan',this.value,'loan')">
											</div>
											<!-- div class="form-group">
												<label>Labour Welfare</label>
												<input class="form-control" type="text" name="tds" id="tds">
											</div> -->
											<!-- <div class="form-group">
												<label>Fund</label>
												<input class="form-control" type="text" value="$40">
											</div>
											<div class="form-group">
												<label>Others</label>
												<input class="form-control" type="text" value="$15">
											</div> -->
											<input type="hidden" id="originaltds">
											<input type="hidden" id="originalemp_esi">
											<input type="hidden" id="originalemp_pf">
											<input type="hidden" id="originalleaves">
											<input type="hidden" id="originalproftax">
											<input type="hidden" id="originalloan">
										</div>
									</div>
									<div class="submit-section">
										<button class="btn btn-primary submit-btn">Save</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /Edit Salary Modal -->
				
				<!-- Delete Salary Modal -->
				<div class="modal custom-modal fade" id="delete_salary" role="dialog">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<div class="form-header">
									<h3>Delete Salary</h3>
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
				<!-- /Delete Salary Modal -->
				

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
		
		<!-- Datatable JS -->
		<script src="assets/js/jquery.dataTables.min.js"></script>
		<script src="assets/js/dataTables.bootstrap4.min.js"></script>		

		<!-- Custom JS -->
		<script src="assets/js/app.js"></script>
		
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
		 <script type="text/javascript">
    function showmenu(val){
    	 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right show"; 
    	 setTimeout(function() {
    		 document.getElementById("showmenu"+val).className="dropdown-menu dropdown-menu-right"; 
    		}, alertmsgduration);
    }
    
    </script>
    
    </body>
</html>