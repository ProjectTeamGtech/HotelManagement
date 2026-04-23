<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>

<%-- <script type="text/javascript" src="inventory/js/addvendor.js"></script> --%>
<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>
<%-- <script type="text/javascript" src="diarymanagement/js/addPatient.js"></script>
 --%>
    <link rel="icon" type="image/ico" href="assets/images/favicon.ico" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <SCRIPT type="text/javascript" src="payroll/js/payrollmaster.js"></SCRIPT>
<script type="text/javascript" src="common/js/pagination.js"></script>
 <%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<link href="_assets/newtheme/css/main.css" rel="stylesheet" />
 <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css" type="text/css" media="all">
 
 
  <style>
	.nav-tabs { border-bottom: 2px solid #DDD;background-color: #f5f5f5; }
	.nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover { border-width: 0; }
	.nav-tabs > li > a { border: none; color: #666; }
	.nav-tabs > li.active > a, .nav-tabs > li > a:hover { border: none; color: #339966 !important; background: transparent; }
	.nav-tabs > li > a::after { content: ""; background: #339966; height: 2px; position: absolute; width: 100%; left: 0px; bottom: -1px; transition: all 250ms ease 0s; transform: scale(0); }
	.nav-tabs > li.active > a::after, .nav-tabs > li:hover > a::after { transform: scale(1); }
	.tab-nav > li > a::after { background: #21527d none repeat scroll 0% 0%; color: #fff; }
	.tab-pane { padding: 15px 0; }
	.tab-content{padding: 0px 10px 0px 15px;}
	.card {background: #FFF none repeat scroll 0% 0%; box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.3); margin-bottom: 30px; }
 
 
 /*Now the CSS*/
* {margin: 0; padding: 0;}

.tree ul {
	padding-top: 15px; position: relative;
	
	-transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

.tree li {
	float: left; text-align: center;
	list-style-type: none;
	position: relative;
	padding: 20px 5px 0 5px;
	
	-transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/*We will use ::before and ::after to draw the connectors*/

.tree li::before, .tree li::after{
	content: '';
	position: absolute; top: 0; right: 50%;
	border-top: 1px solid #ccc;
	width: 50%; height: 20px;
}
.tree li::after{
	right: auto; left: 50%;
	border-left: 1px solid #ccc;
}

/*We need to remove left-right connectors from elements without 
any siblings*/
.tree li:only-child::after, .tree li:only-child::before {
	display: none;
}

/*Remove space from the top of single children*/
.tree li:only-child{ padding-top: 0;
	    width: 100%;
    position: absolute;
}

/*Remove left connector from first child and 
right connector from last child*/
.tree li:first-child::before, .tree li:last-child::after{
	border: 0 none;
}
/*Adding back the vertical connector to the last nodes*/
.tree li:last-child::before{
	border-right: 1px solid #ccc;
	border-radius: 0 5px 0 0;
	-webkit-border-radius: 0 5px 0 0;
	-moz-border-radius: 0 5px 0 0;
}
.tree li:first-child::after{
	border-radius: 5px 0 0 0;
	-webkit-border-radius: 5px 0 0 0;
	-moz-border-radius: 5px 0 0 0;
}

/*Time to add downward connectors from parents*/
.tree ul ul::before{
	content: '';
	position: absolute; top: 0; left: 50%;
	border-left: 1px solid #ccc;
	width: 0; height: 15px;
}

.tree li a{
	border: 1px solid #ccc;
	padding: 4px 8px;
	text-decoration: none;
	color: #666;
	font-family: arial, verdana, tahoma;
	font-size: 11px;
	display: inline-block;
	
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	
	-transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/*Time for some hover effects*/
/*We will apply the hover effect the the lineage of the element also*/
.tree li a:hover, .tree li a:hover+ul li a {
	background: #c8e4f8; color: #000; border: 1px solid #94a0b4;
}
/*Connector styles on hover*/
.tree li a:hover+ul li::after, 
.tree li a:hover+ul li::before, 
.tree li a:hover+ul::before, 
.tree li a:hover+ul ul::before{
	border-color:  #94a0b4;
}
.settitle{
	    margin: 0px;
    text-align: center;
    color: #5cb85c;
}
/*Thats all.*/
 
 
 </style>
 
 <script type="text/javascript">
 $(document).ready(function() {

		$("#date_join").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#dob").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true
		});
	});
    
   </script>

<div class="col-lg-1 col-md-1"></div>
 <div class="col-md-10 col-lg-10 col-xs-12 col-sm-12">
 						
 						
 
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"style="background-color: #339966">
										<h4>Update Employee</h4>
									</div>
								</div>
								<div class="row">
									 <s:form name="employeeform" theme="simple" action="updatePayrollEmployee">
								 <s:hidden id="emp_id" name="emp_id"></s:hidden>
								
									<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
                                    <!-- Nav tabs -->
                                    <div class="card">
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li role="presentation" class="active"><a href="#one" aria-controls="one" role="tab" data-toggle="tab">Name/Address</a></li>
                                       <!--  <li role="presentation"><a href="#two" aria-controls="two" role="tab" data-toggle="tab">Contacts</a></li>
                                        <li role="presentation"><a href="#three" aria-controls="three" role="tab" data-toggle="tab">Third Party</a></li>
                                        <li role="presentation"><a href="#four" aria-controls="four" role="tab" data-toggle="tab">Reference</a></li>
                                        <li role="presentation"><a href="#five" aria-controls="five" role="tab" data-toggle="tab">Others</a></li>
                                        <li role="presentation"><a href="#six" aria-controls="six" role="tab" data-toggle="tab">Notes</a></li>
                                        <li role="presentation"><a href="#seven" aria-controls="seven" role="tab" data-toggle="tab">Health Records</a></li> -->
                                        
                                    </ul>
                                    <!-- Tab panes -->
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane active" id="one">
                                        	<div class="row" id = "firstNameErrorlbl" >
                                        		<div class="col-lg-12 col-md-12">
													<div class="row">
														<div class="form-inline">
															<div class="form-group">
															<label>Aadhar Number</label>
															 <s:textfield id="adhno" name="adhno" title="Enter  Aadhar No." theme="simple"  cssClass="form-control showToolTip"
																 data-toggle="tooltip" placeholder="Enter  Aadhar No" cssStyle="width:100%;"/>
														</div>
														<div class="form-group">
														<label for="inputEmail3" class="control-label">PAN No.</label>
                                     <s:textfield id="panno" name="panno" title="Enter  PAN No." theme="simple"  cssClass="form-control showToolTip"
																 data-toggle="tooltip" placeholder="Enter  PAN No." cssStyle="width:100%;"/>
                                    </div>
														<div class="form-group">
															<label>Employee ID</label>
															 <s:textfield id="empcode" name="empcode" title="Enter  Employee No." theme="simple"  cssClass="form-control showToolTip"
																 data-toggle="tooltip" placeholder="Enter  Employee No" cssStyle="width:100%;"/>
														</div>
															</div>											
														<div class="col-lg-4 col-md-4">
															
														</div>
													</div>
													<div class="row" style="margin-top: 15px;">
														<div class="col-lg-4 col-md-4">
												<label> Name</label><label><span class="text-danger">*</span></label>
												<div class="form-inline">
												  <div class="form-group" style="width: 27%;">
												    <s:select  id="initial" name="initial" list="initialList" onchange="setGender(this.value)" title="Select" theme="simple" cssClass="form-control showToolTip" data-toggle="tooltip" headerValue="Select" headerKey="" cssStyle="width:100%;"/>
												<label  id = "titleError" class="text-danger"></label>
												  </div>
												  <div class="form-group" style="width: 70%;">
												  <s:textfield id="name" name="name" title="Enter  Name" theme="simple"  cssClass="form-control showToolTip"
																onblur="initialFirstCap(this);" data-toggle="tooltip" placeholder="Enter  Name" cssStyle="width:113%;"/>
												<label  id = "firstNameError" class="text-danger"></label>
												  </div>
												  
												</div>
												
												
											</div>
										 <div class="col-lg-4 col-md-4">
												<label for="inputEmail3" class="control-label">Designation</label>
												<s:textfield id="designation" name="designation" title="Enter  Designation" theme="simple"  cssClass="form-control showToolTip"
																 data-toggle="tooltip" placeholder="Enter  Designation" cssStyle="width:100%;"/>
											</div>
											<div class="col-lg-4 col-md-4" id = "lastNameErrorlbl">
												 <label for="inputEmail3" class="control-label">Qualification</label>
												 <s:textfield id="qualification" name="qualification" title="Enter  Qualification" theme="simple"  cssClass="form-control showToolTip"
																 data-toggle="tooltip" placeholder="Enter  Qualification" cssStyle="width:100%;"/>
											</div><br><br>
											
											
											</div>
													</div>
													<div class="col-lg-12 col-md-12">
													<div class="row">
													<div class="form-inline" >
											<div class="form-group"style="width: 18%">
                                	<label for="inputEmail3" class="control-label">Select Company</label><br><br>
                                    <s:select name="company" id="company" list="companylist" listKey="comp_id" listValue="company" theme="simple" cssClass="form-control showToolTip chosen-select">
                                    </s:select>
                            </div>&emsp;&emsp;&emsp;
                             <div class="form-group"style="width: 18%">
                                <label for="inputEmail3" class="control-label">Select Department</label><br><br>
                                     <s:select name="department" id="department" list="departmentlist" listKey="dept_id" listValue="department" cssClass="form-control showToolTip chosen-select">
                                    </s:select>
                            </div>&emsp;&emsp;&emsp;
                            <div class="form-group"style="width: 18%">
                                <label for="inputEmail3" class="control-label">Select Branch</label><br><br>
                                     <s:select name="branch" id="branch" list="branchlist" listKey="branch_id" listValue="branch" cssClass="form-control showToolTip chosen-select">
                                    </s:select>
                            </div>
                            <br><br><br>
                            </div>
                            </div>
                            </div>
													<div class="row">
                                          		<div class="col-lg-2 col-md-2">
													<label>DOB</label><label><span class="text-danger">*</span></label>
													<s:textfield id="dob" name="dob"
														cssClass="form-control showToolTip " data-toggle="tooltip"
														placeholder="Enter DOB" readonly = "true" onchange="getAgendDays(this.value)"/>	
													<label id = "dobError" class="text-danger" ></label>
																					
												</div>
														<div class="col-lg-2 col-md-2">
													<label>Age</label><label><span class="text-danger">*</span></label>
													<input type="text" name="age" value="" id="age" class="form-control showToolTip" title="" onchange="allnumeric(this.value)"
 													data-toggle="tooltip" style="width:100%;" placeholder="Age" data-original-title="Enter Age">
													
																					
												</div>
												<%-- <div class="col-lg-2 col-md-2">
												<label>Days</label><label><span class="text-danger"></span></label>
												  	<input type="number" placeholder="days"  id="days" class="form-control"  />
												  	</div> --%>
												<div class="col-lg-4 col-md-4" style="width: 16%">
													<label>Gender</label><label><span class="text-danger">*</span></label>
													<s:select id="gender" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip "
														data-toggle="tooltip" title="Select Gender"  headerKey="0" headerValue="Select"/>																	
													<label id = "genderError" class="text-danger"></label>
												</div>
												
												<div class="col-lg-4 col-md-4">
													 <label for="inputEmail3" class="control-label">Date of Joining</label>
													 <s:textfield readonly="true" name="date_join" id="date_join"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Date of Joining"></s:textfield>
												</div>
                                          </div>
                                          <div class="row">
											<div class="col-lg-8 col-md-8">
												<label>House No / Flat No / First Line of Address</label><label><span class="text-danger">*</span></label>
												<s:textfield id="currentaddress" name="currentaddress" title="Enter Address" onblur="allFirstInitCap(this.id);"
													cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Address" />
													
													<label id = "addressError" class="text-danger"></label>
											</div>
											
											<div class="col-lg-2 col-md-2">
												<label>Pin Code</label>
												<s:textfield id="postCode" name="postcode"
													title="Enter postCode" cssClass="form-control showToolTip"
													data-toggle="tooltip" placeholder="Enter Post Code" onblur="initialCap(this);" maxlength="6"/>	
												<label id = "postCodeError" class="text-danger"></label>
											</div>
                                          <div class="col-lg-2 col-md-2">
								<label>Marital Status</label>		
								<s:select id="maritalsts" name="maritalsts" list="{'Single','Married','Divorced','Separated'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control showToolTip"/>
				          	</div>
                                          </div>
                                          
                                          <div class="row">	
							
							
							<div class="col-lg-4 col-md-4" id="statediv">
								<label>State</label>
								<%-- <s:textfield id="county" name="county" title="Enter County"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter County" onblur="initialFirstCap(this);" />	 --%>							
							
							
								<s:select list="statelist" cssClass="form-control showToolTip chosen-select" onchange="getCitiesajax(this.value)" name="county" id="county"
							 			listKey="name" listValue="name" headerKey="0" headerValue="Select State"  />
									
							</div>
							
							<div class="col-lg-4 col-md-4" id="citydiv">
								<label>Town/City</label><label><span class="text-danger">*</span></label>
								<%-- <s:textfield id="town" name="town" title="Enter Town" onblur="initialFirstCap(this);"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Town" />		 --%>
								
							
								<s:select list="citylist"  listKey="city" listValue="city" id="town" onchange="getStateAjax(this.value)" cssClass="form-control showToolTip chosen-select" 
											headerKey="0" headerValue="Select City" name="town" />
							 	
								<label id = "townError" class="text-danger"></label>							
							</div>
							</div>
						
							
							<%-- <div class="col-lg-4 col-md-4">
								<label>Country</label><label><span class="text-danger">*</span></label>
								<s:if test="%{#countryList != 'null'}">
									<s:select id="country" name="country" list="countryList"
										headerKey="0" headerValue="Select Country"
										labelposition="left" title="Select your country from list"
										cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" />
								</s:if>								
							</div>
							
						</div> --%>
						<div class="row">	
							
							<div class="col-lg-4 col-md-4">
								<label>Mobile No.</label>
								<s:textfield id="mobNo" name="mobNo" title="Enter Mobile No"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Mobile No."  maxlength="10"/>	
								<label id = "mobNoError" class="text-danger"></label>									
							</div>	
							
							<div class="col-lg-4 col-md-4">
								<label>Email</label>
								<s:textfield id="email" name="email" title="Enter Valid Email"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Email." />
								<label id = "emailError" class="text-danger"></label>								
							</div>
                                	</div>
                                        	
                                        	</div>
                                        </div>
                                        
                                          </div>
                                          
                                          
                                          
                                          
                                          
						
                                          
                                          
                                          
                                        
                                        <%-- <div role="tabpanel" class="tab-pane" id="two">
                                        	<div class="row">
							<div class="col-lg-3 col-md-3">
								<label>Home Phone No.</label>
								<s:textfield id="homeNo" name="homeNo"
									title="Enter Home Contact No"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Home No." />		
								<label id = "homeNoError" class="text-danger"></label>
															
							</div>
							
							<div class="col-lg-3 col-md-3">
								<label>Work Phone No.</label>
								<s:textfield id="workNo" name="workNo"
									title="Enter Work Contact No"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Work Mobile." />	
								<label id = "workNoError" class="text-danger"></label>								
							</div>	
							
							
						
							
							<div class="col-lg-3 col-md-3">
								<label>Preferred Contact Mode</label>
								<s:select id="prefContactMode" name="prefContactMode"
									headerValue="Select Contact Mode"
									list="{'No Preference','Work','Home','Mobile','Email','EmailCc'}"
									title="Select Prefered Contact Mode" 
									cssClass="form-control showToolTip" data-toggle="tooltip" />
								
							</div>
							<div class="col-lg-3 col-md-3">
								
																						
							</div>
						
							
						</div>
						<div class="row">
								
							<div class="col-lg-3 col-md-3">
								<label>Email CC</label>
								<s:textfield id="emailCc" name="emailCc" title="Enter EmailCc"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Email Cc." />	
								<label id = "emailCcError" class="text-danger"></label>								
							</div>
							<div class="col-lg-3 col-md-3">
								<label>Emergency Contact Name</label>
								<s:textfield id="emergencyContName" name="emergencyContName"
									title="Enter Emergency Contact Name"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Emergency Contact Name" />								
							</div>	
							<div class="col-lg-3 col-md-3">
								<label>Emergency Contact Phone</label>
								<s:textfield id="emergencyContNo" name="emergencyContNo"
									title="Enter Emergency Contact No."
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Emergency Contact No." />
								<label id = "emergencyContNoError" class="text-danger"></label>								
									
							</div>
							<div class="col-lg-3 col-md-3" ></div>
							
							</div>
											
                    	</div> --%>
                                        <%-- <div role="tabpanel" class="tab-pane" id="three">
											<div class="row">
													<div class="col-lg-3 col-md-3">
									<label>Who will Pay?</label><span class="text-danger reqd-info">*</span>
									<s:select id="whopay" name="whopay"
										list="#{'Client':'Self','Third Party':'Third Party'}"
										cssClass="form-control showToolTip chosen" data-toggle="tooltip" onchange="enabledFiled(this.value)"></s:select>
									<label  id = "whopayError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3">
									 <label>TP (Insurance Co/Group)</label>
									 <s:select id="type"  name="type"
										list="thirdPartyTypeList" listKey="id" listValue="type" headerValue="Select TP Type" disabled="true"
										headerKey="0"  cssClass="form-control showToolTip chosen"
										data-toggle="tooltip" onchange="setTPName(this.value)" />
									<label  id = "tpError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3" id = "tpnameErrorlbl">
										<label>Third Party Name</label>		
										<select id = "typeName" name = "typeName" disabled="true" class="form-control showToolTip ppstyle chosen" data-toggle="tooltip">
											
												<option value="0">Select Third Party</option>
										</select>
										<label  id = "tpnameError" class="text-danger"></label>	
										
								</div>
								<div class="col-lg-3 col-md-3" style="margin-top: 22px;">
									<a href="javascript:void(0)" onclick="setTypeField()" class="btn btn-primary" > <i class="fa fa-plus"></i> New TP </a>
							
								</div>	
								
											</div>
											<div class="row">
											<div class="col-lg-3 col-md-3">
														<label style="color:orange;">Membership No</label><!--
													
														<input type="text" id="tpmemb" name="tpmemb" value="" class="form-control" placeholder="Enter Membership No">						
													
													--><s:textfield id="tpmemb" name="tpmemb" disabled="true" title="Enter Membership No" theme="simple"  cssClass="form-control showToolTip"
																 data-toggle="tooltip" placeholder="Enter Membership No"/>
													</div> 
									<div class="col-lg-3 col-md-3">
									<label>Policy No.</label>
									<s:textfield name="policyNo" id="policyNo" disabled="true"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Policy No." />								
								</div>
								<div class="col-lg-3 col-md-3">
									<label>Policy Expiry Date</label>
									<s:textfield name="expiryDate" id="expiryDate" disabled="true" readonly = "true"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Expiry Date" />								
								</div>
								<div class="col-lg-3 col-md-3">
									<label>Policy Excess</label>
									<s:textfield name="policyExcess" id="policyExcess" disabled="true"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder = "Enter Value"/>								
								</div>
								
								</div>
                                        
                                        <div id="addtpdiv" class="hidden">
                                        
                                        <div class="row" style="margin-top: 20px">
                                        <div class="col-lg-3 col-md-3" id="policyholderdiv" >
								<label>Policy Holder</label>
								<s:textfield name="policyholder" id="policyholder"  
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter colliery" required="true"/>	
								</div>
								<div class="col-lg-3 col-md-3" id="compnamediv" >
								<label>Employee Name</label>
								<s:textfield name="compname" id="compname"  
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Employee Name" required="true"/>	
								</div>
								<div class="col-lg-3 col-md-3" id="neisnodiv" >
								<label>NEIS/Card No</label>
								<s:textfield name="neisno" id="neisno"  
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter NEIS/Card No" required="true"/>	
								</div>
								<div class="col-lg-3 col-md-3" id="designationbytpdiv" >
								<label>Designation</label>
								<s:textfield name="designationbytp" id="designationbytp"  
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Designation" required="true"/>	
								</div>
								<div class="col-lg-3 col-md-3" id="relationvbytpdiv">
								<label>Relation</label>
								<s:textfield name="relationvbytpe" id="relationvbytpe"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Relation" required="true"/>	
								</div>
								
								</div>
								<div class="row" style="margin-top: 20px">
								<div class="col-lg-3 col-md-3" id="claimbytpdiv" >
								<label>Claim ID</label>
								<s:textfield name="claimbytp" id="claimbytp"  
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Claim ID" required="true"/>	
								</div>
								<div class="col-lg-3 col-md-3" id="unitstationdiv" >
								<label>Unit/Station</label>
								<s:textfield name="unitstation" id="unitstation"  
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter unit/station" required="true"/>	
								</div>
							<div class="col-lg-3 col-md-3" id="collierydiv" >
								<label>Colliery</label>
								<s:textfield name="colliery" id="colliery"  
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter colliery" required="true"/>	
								</div>
								
								<div class="col-lg-3 col-md-3" id="areabytpdiv" >
								<label>Area</label>
								<s:textfield name="areabytp" id="areabytp"  
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter colliery" required="true"/>	
								</div>
								
								</div>
								
                                        </div>
                                   </div>     
                                        <div role="tabpanel" class="tab-pane" id="four">
                                        	<div class="row">
												<div class="col-lg-3 col-md-3">
								<label>GP/Hospital Name</label>

								<s:select cssClass="form-control showToolTip chosen"
									data-toggle="tooltip"  id="gptypeName"
									name="gptypeName" list="surgeryList" listKey="id"
									listValue="gptypeName" headerKey="0" theme="simple"
									headerValue="Select GP/Hospital Name" onchange="setGPDataList(this.value)"/>
							</div>
												<div class="col-lg-3 col-md-3" style="margin-top: 22px;">
							  <a href="#" onclick="addNewDoctorSurgery()" class="btn btn-primary" > <i class="fa fa-plus"></i> New GP/Hospital</a>
								
							</div>
												<div class="col-lg-3 col-md-3">
								<label>GP/Consultant Name</label>
								
								<select id = "gpname" name = "gpid"  class="form-control showToolTip chosen" data-toggle="tooltip">
									
										<option value="0">Select GP/Consultant</option>
								</select>
								<label id = "gpnameError" class="text-danger"></label>
								
							</div>
												<div class="col-lg-3 col-md-3" style="margin-top: 22px;">
							
							
									<a href="#" onclick="addNewGpData()" class="btn btn-primary" > <i class="fa fa-plus"></i> New GP/Consultant</a>
								
							</div>
											</div>
                                        </div>
                                        <div role="tabpanel" class="tab-pane" id="five">
											<div class="row">
												<div class="col-lg-3 col-md-3">
									<label>Source of Introduction</label>
									<div id="sourceOfIntro_other" style="display: none;">
											<s:textfield id="txt_sourceOfIntro" name="otherSourceOfIntro"
												 onblur="addOtherSourceOfIntro(this.value)" onchange="initialCap(this)"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter New Source Of Introduction" />
											<br>	
										</div>
										<s:select id="sourceOfIntro" name="sourceOfIntro"
											list="sourceOfIntroList" headerKey="0" listValue="sourceOfIntro" listKey="id"
											headerValue="Select Source Of Introduction"
											title="Select Source Of Introduction" 
											cssClass="form-control showToolTip ddlAddNew" data-toggle="tooltip"/>								
								
									
									
								</div>
								<div class="col-lg-3 col-md-3">
									<label>Occupation</label>
									<div id="occupation_other" style="display: none;">
									
										<s:textfield id="txt_occupation" name="otherOccupation"
											type="text"  
											cssClass="form-control showToolTip" data-toggle="tooltip"
											placeholder="Enter New Occupation" />
										<br>	
									</div>		
									<s:select id="occupation" name="occupation" listValue="occupation" listKey="id"
										list="clientOccupationList" headerKey="0"
										headerValue="Select Occupation"
										title="Select your Occupation from list"
										cssClass="form-control showToolTip ddlAddNew" data-toggle="tooltip" />
														
								</div>
								
								<!--<div class="col-lg-3 col-md-3">
									<label>Referred By</label>
									<div id="reference_other" style="display: none;">
										<s:textfield id="txt_reference" name="otherRef" type="text"
											cssClass="form-control showToolTip" data-toggle="tooltip"
											placeholder="Enter New Reference" />
											<br>
									</div>
									<s:select id="reference" name="reference" list="refrenceList" listValue="reference" listKey="id"
										headerKey="0" headerValue="Select Reference"
										title="Select your Reference from list"
										cssClass="form-control showToolTip ddlAddNew" data-toggle="tooltip"
										/>
										<label  id = "refError" class="text-danger"></label>	
										
								</div>
								--><div class="col-lg-3 col-md-3">
									<label>Referred Date</label>
									<s:textfield name="referedDate" id="referedDate"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Refered Date" />								
								</div> 
								<div class="col-lg-3 col-md-3">
												<label>NHS Number</label>
												<div id="nhsdiv">
													<s:textfield id="nhsNumber" name="nhsNumber"
												
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter NHS Number" />
												
												</div>
											</div>
											</div>
										<div class="row" style="margin-top: 15px;">
											<div class="col-lg-3 col-md-3">
										<label>Speciality</label>
										<div id="treatmentType_other" style="display: none;">
										
											<s:textfield id="txt_treatmentType" name="otherCondition"
												
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter New Speciality" />
											<br>	
												
									</div>
												
									<s:select id = "treatmentType" name = "treatmentType" list="condtitionList" headerValue="Select Condition" headerKey="0" listKey="id" listValue="treatmentType" cssClass="form-control showToolTip ppstyle ddlAddNew"
										data-toggle="tooltip" theme="simple"></s:select>
									<label  id = "conError" class="text-danger"></label>
								
								</div>	
								<div class="col-lg-3 col-md-3">
										<label>Source Of Intro Name</label>			
										<s:textfield id="sourceOfIntroName" name="sourceOfIntroName"
											title="Enter Source Of Introduction Name"
											cssClass="form-control showToolTip" data-toggle="tooltip"
											placeholder="Enter Source Of Introduction Name" />
									</div>
									<div class="col-lg-3 col-md-3">
										<label>Employer Name</label>			
										<s:textfield id="employerName" name="employerName"
											title="Enter Employer Name"
											cssClass="form-control showToolTip" data-toggle="tooltip"
											placeholder="Enter Employer Name" />
									</div>
									<div class="col-lg-3 col-md-3">
									<label><%=loginfo.getPatientname_field() %> Type</label>
									<s:select id="patientType" name="patientType"
										list="{'Other','Private','NHS'}" title="Select Patient Type"
										headerValue="Select Patient Type"
										cssClass="form-control showToolTip chosen" data-toggle="tooltip" />
								
								</div>	
										
										</div>
										
										
											
                                        </div>
                                        
								</div>--%>
								<div class="row">
								<div class="col-lg-12 col-md-12 text-right">
									<s:submit value="Update Employee"  cssClass="btn btn-primary" style="background-color: #339966"/>
									
							<!-- 	</div>
							</div>
								 -->
								</div></div>
								</div>
								
                                </div> 
								</div>
								</s:form>
								</div>
								
								</div>
			
			<s:token />					

</div>
 
 
 
<div class="col-lg-1 col-md-1"></div>









 
 
 
 








  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
  
  
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
    
    
    
  </script>
