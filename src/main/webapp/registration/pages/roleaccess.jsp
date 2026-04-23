<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="registration/js/clinic.js"></script>
<script type="text/javascript" src="registration/js/userprofile.js"></script>
<link rel="stylesheet" href="_assets/newtheme/css/main.css">

<link rel="stylesheet" href="mis/kpiplugin/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style>
.alert.alert-callout {
	position: relative;
	padding-left: 20px;
	background: #ffffff;
	color: #313534;
	border-radius: 0px;
	border-color: #5cb85c;
	text-align: left;
	margin-bottom: 0px;
}

.opacity-50 {
	opacity: 0.5;
	filter: alpha(opacity = 50);
}

.text-xl {
	font-size: 180%;
}

nav li {
	border-bottom: 1px solid rgba(221, 221, 221, 0.33);
	line-height: 27px;
}

#sidebar .settings .onoffswitch {
	right: 0px;
}

.imgseth {
	width: 10%;
}

h1, .h1 {
	font-size: 15px !important;
	font-weight: bold !important;
	color: chocolate;
	border-bottom: 1px solid;
	line-height: 27px;
}

.onoffswitch-inner:after {
	content: "";
	padding-right: 9px;
	background-color: rgb(169, 169, 169);
	color: #999999;
	text-align: right;
}

#sidebar .settings .onoffswitch {
	right: 15px;
}
</style>

</head>
<body>
	<div class="">
		<div class="">
			<div class=" ">
				<div class="col-lg-1 col-md-1"></div>
				<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10 details">
					<h4>
						Access Setting for
						<s:property value="fullname" />
						-
						<s:property value="jobtitle" />
						<div style="float: right;">
							<s:hidden id="userwiseaceessid" name="userwiseaceess"></s:hidden>
							<s:hidden id="userIdval" name="userId"></s:hidden>
							<s:if test="userwiseaceess==0">
								<input type="radio" value="0" checked="checked" name="userwiseaceess" onclick="setTypeofAccesstoUser(this.value,'<s:property value="userId"/>','<s:property value="jobtitle"/>')">Jobtitle Wise Access
								<input type="radio" value="1" name="userwiseaceess" onclick="setTypeofAccesstoUser(this.value,'<s:property value="userId"/>','<s:property value="jobtitle"/>')">User Wise Access
							</s:if>
							<s:else>
								<input type="radio" value="0" name="userwiseaceess" onclick="setTypeofAccesstoUser(this.value,'<s:property value="userId"/>','<s:property value="jobtitle"/>')">Jobtitle Wise Access
								<input type="radio" value="1" checked="checked" name="userwiseaceess" onclick="setTypeofAccesstoUser(this.value,'<s:property value="userId"/>','<s:property value="jobtitle"/>')">User Wise Access
							</s:else>
						</div>
					</h4>
					
				</div>
				<div class="col-lg-1 col-md-1"></div>
			</div>

			<div class="row ">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div>
						<%-- <div class="col-lg-12 col-md-12 topback2">
	<div class="form-inline">
		<div class="form-group">
			 <s:select name="jobgroup" id="jobgroup" list="jobGroupList" listKey="id" listValue="name" title="Select Role Group"
					    headerKey="0" headerValue="Select Role Group" onchange="setJobTitleAjax(this.value)"
					    cssClass="form-control showToolTip chosen-select" data-toggle="tooltip">
			</s:select> 
		</div>
		<div class="form-group" id="jobtitlediv">
			<select name="jobtitle" id="jobtitle" class="form-control showToolTip chosen-select">
				<option value="0">Select</option>
			</select>
			<s:select list="jobtitlelist"
		cssClass="form-control showToolTip chosen-select" name="jobtitle" id="jobtitle" listKey="name" listValue="name" headerKey="0" headerValue="Select Job Title"></s:select>
		</div>
		<div class="form-group">
			<s:select list="accessmodulelist" 
		cssClass="form-control showToolTip chosen-select" name="modulename" id="modulename"
		listKey="id" listValue="name" headerKey="0" headerValue="Select Module Name"></s:select>
		</div>
		
		<div class="form-group">
			<a class="btn btn-primary" href="#" onclick="setAllRoleAccess()">Go</a>
		</div>
		
		<div class="form-group">
			<form id="live-search" action="" class="styled" method="post">
				<input type="text" placeholder="Search" id="filter" value="" class="form-control" style="width: 100%;text-transform: uppercase;"/>
			</form>
		</div>
	</div>
</div> --%>

						<div class="row">
							<!-- <form action="updateRoleAccessClinicRegistration">
		<div class="col-lg-12 ">
		<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9" style="padding-left:0px;">
			<div class="setheight">
				<table id="results" class="table table-hover table-bordered table-striped table-condensed">
					<thead>
						<tr>
							<th style="width: 78%;">Roles Title</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody id="tbodydiv">
						<tr>
						
						</tr>
					</tbody>
					<input type="hidden" name="moduleid" id="moduleid">
					<input type="hidden" name="roleid" id="roleid">
				</table>
			</div>
		</div>
			
		</div>
		<div class="col-lg-12 col-md-12" style="margin-top: 15px;">
			<input type="submit" name="update" class="btn btn-primary" id="update" value="update">
		</div>
	</form> -->


							<!-- prototype created by abhi for access setting -->
							<div class="col-lg-1 col-md-1"></div>
							<div class="col-lg-10 col-md-10 col-xs-10 col-sm-10">
								<div id="wrapper">
									<div id="main">
										<div class=" clearfix">
											<div id="sidebar">
												<div id="nav-anchor"></div>
												<nav class="">
												<ul class="setheight">
													<li><a href="#access1">1. OPD</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="opdid==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'appointmentbooking,manageopd','<s:property value="jobtitle"/>')"
																				name="opdid" class="onoffswitch-checkbox" id="OPD">
																			<label class="onoffswitch-label" for="OPD"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox"
																				onclick="updateWidegetStatus(this.checked,'appointmentbooking,manageopd','<s:property value="jobtitle"/>')"
																				name="opdid" class="onoffswitch-checkbox" id="OPD">
																			<label class="onoffswitch-label" for="OPD"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
													<li><a href="#access2">2. IPD</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="ipdid==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'manageipd','<s:property value="jobtitle"/>')"
																				name="ipdid" class="onoffswitch-checkbox" id="IPD">
																			<label class="onoffswitch-label" for="IPD"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="ipdid"
																				onclick="updateWidegetStatus(this.checked,'manageipd','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="IPD"> <label
																				class="onoffswitch-label" for="IPD"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
													<li><a href="#access3">3. OT</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="ot==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'ot','<s:property value="jobtitle"/>')"
																				name="ot" class="onoffswitch-checkbox" id="OT">
																			<label class="onoffswitch-label" for="OT"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="ot"
																				onclick="updateWidegetStatus(this.checked,'ot','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="OT"> <label
																				class="onoffswitch-label" for="OT"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
													<li><a href="#access4">4. Casualty</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="casualty==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'casualty','<s:property value="jobtitle"/>')"
																				name="casualty" class="onoffswitch-checkbox"
																				id="Casualty"> <label
																				class="onoffswitch-label" for="Casualty"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="casualty"
																				onclick="updateWidegetStatus(this.checked,'casualty','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Casualty">
																			<label class="onoffswitch-label" for="Casualty">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access5">5. EMR</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="emr==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'medicalrecord,manageemr','<s:property value="jobtitle"/>')"
																				name="emr" class="onoffswitch-checkbox" id="EMR">
																			<label class="onoffswitch-label" for="EMR"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="emr"
																				onclick="updateWidegetStatus(this.checked,'medicalrecord,manageemr','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="EMR"> <label
																				class="onoffswitch-label" for="EMR"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access6">6. PAC's</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="packs==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'packs','<s:property value="jobtitle"/>')"
																				name="packs" class="onoffswitch-checkbox" id="PACs">
																			<label class="onoffswitch-label" for="PACs">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="packs"
																				onclick="updateWidegetStatus(this.checked,'packs','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="PACs"> <label
																				class="onoffswitch-label" for="PACs"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access7">7. Discharge</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="discharge==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'discharge','<s:property value="jobtitle"/>')"
																				name="discharge" class="onoffswitch-checkbox"
																				id="Discharge"> <label
																				class="onoffswitch-label" for="Discharge"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="discharge"
																				onclick="updateWidegetStatus(this.checked,'discharge','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Discharge">
																			<label class="onoffswitch-label" for="Discharge">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access8">8. Medicine</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="medicine==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'manageprisc','<s:property value="jobtitle"/>')"
																				name="medicine" class="onoffswitch-checkbox"
																				id="Medicine"> <label
																				class="onoffswitch-label" for="Medicine"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="medicine"
																				onclick="updateWidegetStatus(this.checked,'manageprisc','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Medicine">
																			<label class="onoffswitch-label" for="Medicine">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access9">9. Pharmacy</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="pharmacy==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'pharmacy','<s:property value="jobtitle"/>')"
																				name="pharmacy" class="onoffswitch-checkbox"
																				id="Pharmacy"> <label
																				class="onoffswitch-label" for="Pharmacy"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>

																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="pharmacy"
																				onclick="updateWidegetStatus(this.checked,'pharmacy','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Pharmacy">
																			<label class="onoffswitch-label" for="Pharmacy">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access10">10. Investigation</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="investigation==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				name="investigation"
																				onclick="updateWidegetStatus(this.checked,'manageinvst','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Investigation">
																			<label class="onoffswitch-label" for="Investigation">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="investigation"
																				onclick="updateWidegetStatus(this.checked,'manageinvst','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Investigation">
																			<label class="onoffswitch-label" for="Investigation">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access11">11. Blood Bank</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="bloodbank==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'bloodbak','<s:property value="jobtitle"/>')"
																				name="bloodbank" class="onoffswitch-checkbox"
																				id="Blood_Bank"> <label
																				class="onoffswitch-label" for="Blood_Bank">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>

																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="bloodbank"
																				onclick="updateWidegetStatus(this.checked,'bloodbak','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Blood_Bank">
																			<label class="onoffswitch-label" for="Blood_Bank">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>

																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access12">12. Accounts</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="accounts==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'basicfinance,fullfinance','<s:property value="jobtitle"/>')"
																				name="accounts" class="onoffswitch-checkbox"
																				id="Accounts"> <label
																				class="onoffswitch-label" for="Accounts"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="accounts"
																				onclick="updateWidegetStatus(this.checked,'basicfinance,fullfinance','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Accounts">
																			<label class="onoffswitch-label" for="Accounts">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access13">13. MRD</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="mrd==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'mrd','<s:property value="jobtitle"/>')"
																				name="mrd" class="onoffswitch-checkbox" id="MRD">
																			<label class="onoffswitch-label" for="MRD"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="mrd"
																				onclick="updateWidegetStatus(this.checked,'mrd','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="MRD"> <label
																				class="onoffswitch-label" for="MRD"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access14">14. Payroll</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="payroll==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'payroll','<s:property value="jobtitle"/>')"
																				name="payroll" class="onoffswitch-checkbox"
																				id="Payroll"> <label
																				class="onoffswitch-label" for="Payroll"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="payroll"
																				onclick="updateWidegetStatus(this.checked,'payroll','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Payroll"> <label
																				class="onoffswitch-label" for="Payroll"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access15">15. Expenses</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="expenses==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'expences','<s:property value="jobtitle"/>')"
																				name="expenses" class="onoffswitch-checkbox"
																				id="Expenses"> <label
																				class="onoffswitch-label" for="Expenses"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="expenses"
																				onclick="updateWidegetStatus(this.checked,'expences','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Expenses">
																			<label class="onoffswitch-label" for="Expenses">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access16">16. Inventory</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="inventory==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'inventory','<s:property value="jobtitle"/>')"
																				name="inventory" class="onoffswitch-checkbox"
																				id="Inventory"> <label
																				class="onoffswitch-label" for="Inventory"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="inventory"
																				onclick="updateWidegetStatus(this.checked,'inventory','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Inventory">
																			<label class="onoffswitch-label" for="Inventory">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access17">17. MIS</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="mis==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'report,managemis','<s:property value="jobtitle"/>')"
																				name="mis" class="onoffswitch-checkbox" id="MIS">
																			<label class="onoffswitch-label" for="MIS"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="mis"
																				onclick="updateWidegetStatus(this.checked,'report,managemis','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="MIS"> <label
																				class="onoffswitch-label" for="MIS"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access18">18. Consultants</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="consultants==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'communication,assessmentForm','<s:property value="jobtitle"/>')"
																				name="consultants" class="onoffswitch-checkbox"
																				id="show-offline18"> <label
																				class="onoffswitch-label" for="show-offline18">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="consultants"
																				onclick="updateWidegetStatus(this.checked,'communication,assessmentForm','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="show-offline18">
																			<label class="onoffswitch-label" for="show-offline18">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access19">19. Patient</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="patient==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'manageclient','<s:property value="jobtitle"/>')"
																				name="patient" class="onoffswitch-checkbox"
																				id="show-offline19"> <label
																				class="onoffswitch-label" for="show-offline19">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="patient"
																				onclick="updateWidegetStatus(this.checked,'manageclient','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="show-offline19">
																			<label class="onoffswitch-label" for="show-offline19">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access20">20. Appointment Finder</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="appointmentfinder==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'apmtfinder','<s:property value="jobtitle"/>')"
																				name="appointmentfinder"
																				class="onoffswitch-checkbox" id="show-offline20">
																			<label class="onoffswitch-label" for="show-offline20">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="appointmentfinder"
																				onclick="updateWidegetStatus(this.checked,'apmtfinder','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="show-offline20">
																			<label class="onoffswitch-label" for="show-offline20">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access21">21. Investigation Chart</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="investigation_chart==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'investigation_chart','<s:property value="jobtitle"/>')"
																				name="investigation_chart"
																				class="onoffswitch-checkbox" id="show-offline21">
																			<label class="onoffswitch-label" for="show-offline21">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="investigation_chart"
																				onclick="updateWidegetStatus(this.checked,'investigation_chart','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="show-offline21">
																			<label class="onoffswitch-label" for="show-offline21">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access22">22. Sheduler</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="sheduler==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				name="sheduler"
																				onclick="updateWidegetStatus(this.checked,'sheduler','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Sheduler">
																			<label class="onoffswitch-label" for="Sheduler">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="sheduler"
																				class="onoffswitch-checkbox"
																				onclick="updateWidegetStatus(this.checked,'sheduler','<s:property value="jobtitle"/>')"
																				id="Sheduler"> <label
																				class="onoffswitch-label" for="Sheduler"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access23">23. House Keeping</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="housekeeping==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				name="housekeeping"
																				onclick="updateWidegetStatus(this.checked,'housekeeping','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="House_Keeping">
																			<label class="onoffswitch-label" for="House_Keeping">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="housekeeping"
																				class="onoffswitch-checkbox"
																				onclick="updateWidegetStatus(this.checked,'housekeeping','<s:property value="jobtitle"/>')"
																				id="House_Keeping"> <label
																				class="onoffswitch-label" for="House_Keeping">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>

																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access24">24. Dieteary</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="dietery==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'dietery','<s:property value="jobtitle"/>')"
																				name="dietery" class="onoffswitch-checkbox"
																				id="Dieteary"> <label
																				class="onoffswitch-label" for="Dieteary"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="dietery"
																				onclick="updateWidegetStatus(this.checked,'dietery','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Dieteary">
																			<label class="onoffswitch-label" for="Dieteary">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access25">25. Cafeteria</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="cafeteria==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'cafeteria','<s:property value="jobtitle"/>')"
																				name="cafeteria" class="onoffswitch-checkbox"
																				id="Cafeteria"> <label
																				class="onoffswitch-label" for="Cafeteria"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="cafeteria"
																				onclick="updateWidegetStatus(this.checked,'cafeteria','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Cafeteria">
																			<label class="onoffswitch-label" for="Cafeteria">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>

													<li><a href="#access26">26. Packages</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="packages==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'packages','<s:property value="jobtitle"/>')"
																				name="packages" class="onoffswitch-checkbox"
																				id="Packages"> <label
																				class="onoffswitch-label" for="Packages"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="packages"
																				onclick="updateWidegetStatus(this.checked,'packages','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Packages">
																			<label class="onoffswitch-label" for="Packages">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access27">27. Ambulance</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="ambulance==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'ambulance','<s:property value="jobtitle"/>')"
																				name="ambulance" class="onoffswitch-checkbox"
																				id="Ambulance"> <label
																				class="onoffswitch-label" for="Ambulance"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="ambulance"
																				onclick="updateWidegetStatus(this.checked,'ambulance','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Ambulance">
																			<label class="onoffswitch-label" for="Ambulance">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access28">28. Bank Deposite</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="bank_deposite==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'bank_deposite','<s:property value="jobtitle"/>')"
																				name="bank_deposite" class="onoffswitch-checkbox"
																				id="Bank_Deposite"> <label
																				class="onoffswitch-label" for="Bank_Deposite">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox"
																				onclick="updateWidegetStatus(this.checked,'bank_deposite','<s:property value="jobtitle"/>')"
																				name="bank_deposite" class="onoffswitch-checkbox"
																				id="Bank_Deposite"> <label
																				class="onoffswitch-label" for="Bank_Deposite">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access29">29. Account Reconcilation</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="account_reconcilation==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'account_reconcilation','<s:property value="jobtitle"/>')"
																				name="account_reconcilation"
																				class="onoffswitch-checkbox"
																				id="Account_Reconcilation"> <label
																				class="onoffswitch-label"
																				for="Account_Reconcilation"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="account_reconcilation"
																				onclick="updateWidegetStatus(this.checked,'account_reconcilation','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox"
																				id="Account_Reconcilation"> <label
																				class="onoffswitch-label"
																				for="Account_Reconcilation"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access30">30. Marketing</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="marketing==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'marketing','<s:property value="jobtitle"/>')"
																				name="marketing" class="onoffswitch-checkbox"
																				id="Marketing"> <label
																				class="onoffswitch-label" for="Marketing"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="marketing"
																				onclick="updateWidegetStatus(this.checked,'marketing','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Marketing">
																			<label class="onoffswitch-label" for="Marketing">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>
																</div>
															</li>
														</ul></li>
													<li><a href="#access31">31. Voice Recorder</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;"><s:if
																	test="voice_recording==true">
																	<div class="control-label">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" checked="checked"
																				onclick="updateWidegetStatus(this.checked,'voice_recorder','<s:property value="jobtitle"/>')"
																				name="voice_recording" class="onoffswitch-checkbox"
																				id="Voice_Recorder"> <label
																				class="onoffswitch-label" for="Voice_Recorder">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</div>
																</s:if> <s:else>
																	<div class="control-label">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="voice_recording"
																				onclick="updateWidegetStatus(this.checked,'voice_recorder','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Voice_Recorder">
																			<label class="onoffswitch-label" for="Voice_Recorder">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</div>
																</s:else></li>
														</ul></li>
													<li><a href="#access32">32. Setting</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="setting==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'diarymanagement,managemaster,manageclinic','<s:property value="jobtitle"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="Setting"> <label
																				class="onoffswitch-label" for="Setting"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'diarymanagement,managemaster,manageclinic','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="Setting"> <label
																				class="onoffswitch-label" for="Setting"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
													<li><a href="#access33">33. Indent</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="indent==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'indent_access','<s:property value="jobtitle"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="indent"> <label
																				class="onoffswitch-label" for="indent"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'indent_access','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="indent"> <label
																				class="onoffswitch-label" for="indent"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>

													<li><a href="#access34">34. Day Dashboard</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="daily_opd==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'daily_opd','<s:property value="jobtitle"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="daily_opd"> <label
																				class="onoffswitch-label" for="daily_opd"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'daily_opd','<s:property value="jobtitle"/>')"
																				class="onoffswitch-checkbox" id="daily_opd">
																			<label class="onoffswitch-label" for="daily_opd">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
													<li class="hidden"><a href="#access34">35. Refund Discount Dashboard</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="refund_dashboard==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserIndivisualStatus(this.checked,'refund_dashboard','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="refund_dashboard"> <label
																				class="onoffswitch-label" for="refund_dashboard">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserIndivisualStatus(this.checked,'refund_dashboard','<s:property value="userId"/>')"
																				class="onoffswitch-checkbox" id="refund_dashboard">
																			<label class="onoffswitch-label"
																				for="refund_dashboard"> <span
																				class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
													<li><a href="#access35">35. Master</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="show_master==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'show_master','<s:property value="jobtitle"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="show_master"> <label
																				class="onoffswitch-label" for="show_master">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'show_master','<s:property value="jobtitle"/>')"
																				" class="onoffswitch-checkbox" id="show_master">
																			<label class="onoffswitch-label" for="show_master">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
													<li><a href="#access39">36. Token Display</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="token_display==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'token_display','<s:property value="jobtitle"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="token_display"> <label
																				class="onoffswitch-label" for="token_display">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'token_display','<s:property value="jobtitle"/>')"
																				" class="onoffswitch-checkbox" id="token_display">
																			<label class="onoffswitch-label" for="token_display">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
													<li class="hidden"><a href="#access39">37. Setup Master</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="setup_master==true">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'setup_master','<s:property value="jobtitle"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="setup_master"> <label
																				class="onoffswitch-label" for="setup_master">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'setup_master','<s:property value="jobtitle"/>')"
																				" class="onoffswitch-checkbox" id="setup_master">
																			<label class="onoffswitch-label" for="setup_master">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul>
													</li>
													<li class="hidden"><a href="#access40">38. Add Medicine</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="add_medicine">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				<%-- onclick="updateWidegetStatus(this.checked,'add_medicine','<s:property value="jobtitle"/>')" --%>
																				onclick="updateUserWiseAccess(this.checked,'add_prisc_medicine','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="add_medicine"> <label
																				class="onoffswitch-label" for="add_medicine">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'add_prisc_medicine','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="add_medicine">
																			<label class="onoffswitch-label" for="add_medicine">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
													<li><a href="#access41">37. Pharmacy Backdate
															print</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="pharm_print_backdate">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'pharm_print_backdate','<s:property value="jobtitle"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="pharm_print_backdate"> <label
																				class="onoffswitch-label" for="pharm_print_backdate">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateWidegetStatus(this.checked,'pharm_print_backdate','<s:property value="jobtitle"/>')"
																				" class="onoffswitch-checkbox"
																				id="pharm_print_backdate"> <label
																				class="onoffswitch-label" for="pharm_print_backdate">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
														<li class="hidden">
														<a href="#access41" >40. Prescription New Request
															print</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="prisc_new_req_access">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'prisc_new_req_access','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="prisc_new_req_access"> <label
																				class="onoffswitch-label" for="prisc_new_req_access">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'prisc_new_req_access','<s:property value="userId"/>')"
																				 class="onoffswitch-checkbox"
																				id="prisc_new_req_access"> <label
																				class="onoffswitch-label" for="prisc_new_req_access">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
								
													<%-- <li><a href="#access42">40. Discount Modify</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="modify_disc">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'modify_disc','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="modify_disc"> <label
																				class="onoffswitch-label" for="modify_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'modify_disc','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="modify_disc">
																			<label class="onoffswitch-label" for="modify_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li>
														
														<li><a href="#access43">40. Direct Discount Refund</a>
														<ul class="settings"
															style="list-style: none; margin-top: 7px; float: right;">
															<li style="border: none;">
																<div class="control-label">
																	<s:if test="direct_refund_disc">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'direct_refund_disc','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="direct_refund_disc"> <label
																				class="onoffswitch-label" for="direct_refund_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'direct_refund_disc','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="direct_refund_disc">
																			<label class="onoffswitch-label" for="direct_refund_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else>

																</div>
															</li>
														</ul></li> --%>


												</ul>
												</nav>
											</div>
											<!-- /sidebar -->

											<div id="content">
												<div class="setheight">


													<div id="access1">
														<s:if test="opdid==true">
															<h1>OPD</h1>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<%
																	int i = 1;
																%>
																<s:iterator value="opdlist">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="modulename" />
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if
																					test="flag==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox" checked="checked"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								class="onoffswitch-checkbox" id="opd<%=i%>">
																							<label class="onoffswitch-label" for="opd<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								id="opd<%=i%>"> <label
																								class="onoffswitch-label" for="opd<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>
															</div>
														</s:if>
													</div>

													<div id="access2">
														<s:if test="ipdid==true">
															<h1>IPD</h1>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<%
																	int i = 1;
																%>
																<s:iterator value="ipdlist">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="modulename" />
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if
																					test="flag==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox" checked="checked"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								class="onoffswitch-checkbox" id="ipd<%=i%>">
																							<label class="onoffswitch-label" for="ipd<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								id="ipd<%=i%>"> <label
																								class="onoffswitch-label" for="ipd<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>
															</div>
															<h1>
																Select Ward
																<div class="pull-right" style="margin-top: 4px;margin-right: 10px;">
																	<div class="onoffswitch greensea" id="">
																		<input type="checkbox" class="onoffswitch-checkbox"
																			onchange="selectAllWard(this.checked)"
																			id="selectallward"> <label
																			class="onoffswitch-label" for="selectallward">
																			<span class="onoffswitch-inner"></span> <span
																			class="onoffswitch-switch"></span>
																		</label>
																	</div>
															</h1>

															<!-- </div> -->
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<%
																	i = 1;
																%>
																<s:iterator value="wardList">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="name" />
																		</h4>

																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;">
																				<div class="control-label">
																					<s:if test="status==1">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								value="<s:property value="id" />"
																								checked="checked"
																								class="onoffswitch-checkbox wardch"
																								id="ward<%=i%>"> <label
																								class="onoffswitch-label" for="ward<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</s:if>
																					<s:else>
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								value="<s:property value="id" />"
																								class="onoffswitch-checkbox wardch"
																								id="ward<%=i%>"> <label
																								class="onoffswitch-label" for="ward<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</s:else>

																				</div>
																			</li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>
															</div>
														</s:if>
													</div>

													<div id="access12">
														<s:if test="accounts== true">
															<h1>Account</h1>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<%
																	int i = 1;
																%>
																<s:iterator value="accountlist">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="modulename" />
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if
																					test="flag==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox" checked="checked"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								class="onoffswitch-checkbox" id="account<%=i%>">
																							<label class="onoffswitch-label"
																								for="account<%=i%>"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								id="account<%=i%>"> <label
																								class="onoffswitch-label" for="account<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>
																<div class="col-lg-10 col-md-10 col-xs-10 hidden"
																		style="padding: 0px;">
																		<h4>
																			Refund Discount Pay
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2 hidden"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if
																					test="ref_dis_pay==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox" checked="checked"
																								onclick="updateUserWiseAccess(this.checked,'ref_dis_pay','<s:property value="userId"/>')"
																								class="onoffswitch-checkbox" id="ref_dis_pay">
																							<label class="onoffswitch-label"
																								for="ref_dis_pay"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateUserWiseAccess(this.checked,'ref_dis_pay','<s:property value="userId"/>')"
																								id="ref_dis_pay"> <label
																								class="onoffswitch-label" for="ref_dis_pay">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	
																	<div class="col-lg-10 col-md-10 col-xs-10 hidden"
																		style="padding: 0px;">
																		<h4>
																			Discount Modify
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2 hidden"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if test="modify_disc">
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'modify_disc','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="modify_disc"> <label
																				class="onoffswitch-label" for="modify_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'modify_disc','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="modify_disc">
																			<label class="onoffswitch-label" for="modify_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else></li>
																		</ul>
																	</div>
																	
																	<div class="col-lg-10 col-md-10 col-xs-10 hidden"
																		style="padding: 0px;">
																		<h4>
																			Direct Discount Refund
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2 hidden"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;">
																			<%int akash=0; %>
																			<s:if test="direct_refund_disc==true">
																		<div class="onoffswitch greensea" id="">
																		
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'direct_refund_disc','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="direct_refund_disc"> <label
																				class="onoffswitch-label" for="direct_refund_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'direct_refund_disc','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="direct_refund_disc">
																			<label class="onoffswitch-label" for="direct_refund_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else></li>
																		</ul>
																	</div>
																	
																	
															<!--manual Add charge Lokesh  -->		
															<div class="col-lg-10 col-md-10 col-xs-10 hidden"
																		style="padding: 0px;">
																		<h4>
																			Manual Add Charge
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2 hidden"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;">
																			
																			<s:if test="add_manual_charge==true">
																		<div class="onoffswitch greensea" id="">
																		
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'add_manual_charge','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="add_manual_charge"> <label
																				class="onoffswitch-label" for="add_manual_charge">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'add_manual_charge','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="add_manual_charge">
																			<label class="onoffswitch-label" for="add_manual_charge">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else></li>
																		</ul>
																	</div>
																	
														
																	
															<!--update Investigation Lokesh  -->		
															<div class="col-lg-10 col-md-10 col-xs-10 hidden"
																		style="padding: 0px;">
																		<h4>
																			Update Investigation Charge
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2 hidden"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;">
																			
																			<s:if test="update_investigation_charge">
																		<div class="onoffswitch greensea" id="">
																		
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'update_investigation_charge','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="update_investigation_charge"> <label
																				class="onoffswitch-label" for="update_investigation_charge">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'update_investigation_charge','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="update_investigation_charge">
																			<label class="onoffswitch-label" for="update_investigation_charge">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else></li>
																		</ul>
																	</div>
																	
															<div class="col-lg-10 col-md-10 col-xs-10 hidden"
																		style="padding: 0px;">
																		<h4>
																			Invoice Modify
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2 hidden"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;">
																			
																			<s:if test="invoicemodify">
																		<div class="onoffswitch greensea" id="">
																		
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'invoicemodify','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="invoicemodify"> <label
																				class="onoffswitch-label" for="invoicemodify">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'invoicemodify','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="invoicemodify">
																			<label class="onoffswitch-label" for="invoicemodify">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else></li>
																		</ul>
																	</div>
														
															<div class="col-lg-10 col-md-10 col-xs-10 hidden"
																		style="padding: 0px;">
																		<h4>
																			Individual Discount
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2 hidden"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;">
																			
																			<s:if test="invoicemodify">
																		<div class="onoffswitch greensea" id="">
																		
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'indv_discount','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="invoicemodify"> <label
																				class="onoffswitch-label" for="invoicemodify">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'indv_discount','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="indv_discount">
																			<label class="onoffswitch-label" for="indv_discount">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else></li>
																		</ul>
																	</div>
																	
														<div class="col-lg-10 col-md-10 col-xs-10 hidden"
																		style="padding: 0px;">
																		<h4>
																			Additional Discount
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2 hidden"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;">
																			
																			<s:if test="additional_disc">
																		<div class="onoffswitch greensea" id="">
																		
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'additional_disc','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="additional_disc"> <label
																				class="onoffswitch-label" for="additional_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'additional_disc','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="additional_disc">
																			<label class="onoffswitch-label" for="additional_disc">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else></li>
																		</ul>
																	</div>
														
														
														<div class="col-lg-10 col-md-10 col-xs-10 hidden"
																		style="padding: 0px;">
																		<h4>
																			Cancel Invoice
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2 hidden"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;">
																			
																			<s:if test="cancel_invoice">
																		<div class="onoffswitch greensea" id="">
																		
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'cancel_invoice','<s:property value="userId"/>')"
																				checked="checked" class="onoffswitch-checkbox"
																				id="cancel_invoice"> <label
																				class="onoffswitch-label" for="cancel_invoice">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:if>
																	<s:else>
																		<div class="onoffswitch greensea" id="">
																			<input type="checkbox" name="setting"
																				onclick="updateUserWiseAccess(this.checked,'cancel_invoice','<s:property value="userId"/>')"
																				" class="onoffswitch-checkbox" id="cancel_invoice">
																			<label class="onoffswitch-label" for="cancel_invoice">
																				<span class="onoffswitch-inner"></span> <span
																				class="onoffswitch-switch"></span>
																			</label>
																		</div>
																	</s:else></li>
																		</ul>
																	</div>
														
															</div>
														</s:if>
													</div>

													<div id="access5">
														<s:if test="emr==true">
															<h1>EMR</h1>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<%
																	int i = 1;
																%>
																<s:iterator value="emrlist">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="modulename" />
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if
																					test="flag==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox" checked="checked"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								class="onoffswitch-checkbox" id="emr<%=i%>">
																							<label class="onoffswitch-label" for="emr<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								id="emr<%=i%>"> <label
																								class="onoffswitch-label" for="emr<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>
															</div>
														</s:if>
													</div>

													<div id="access19">
														<s:if test="patient==true">
															<h1>Patient</h1>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<%
																	int i = 1;
																%>
																<s:iterator value="clientlist">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="modulename" />
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if
																					test="flag==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox" checked="checked"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								class="onoffswitch-checkbox" id="Client<%=i%>">
																							<label class="onoffswitch-label"
																								for="Client<%=i%>"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								id="Client<%=i%>"> <label
																								class="onoffswitch-label" for="Client<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>

															</div>
														</s:if>
													</div>
													<div id="access10">
														<s:if test="investigation== true">
															<h1>Investigation</h1>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<%
																	int i = 1;
																%>
																<s:iterator value="investigationrolelist">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="modulename" />
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if
																					test="flag==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox" checked="checked"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								class="onoffswitch-checkbox"
																								id="investigationrole<%=i%>"> <label
																								class="onoffswitch-label"
																								for="investigationrole<%=i%>"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								id="investigationrole<%=i%>"> <label
																								class="onoffswitch-label"
																								for="investigationrole<%=i%>"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>
															</div>
														</s:if>
													</div>

													<div id="access17">
														<s:if test="mis== true">
															<h1>MIS</h1>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<%
																	int i = 1;
																%>
																<s:iterator value="mislist">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="modulename" />
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if
																					test="flag==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">

																							<input type="checkbox" checked="checked"
																								onclick="updateAccesssStatusMis(this.checked,'<s:property value="name"/>','<s:property value="userId"/>')"
																								class="onoffswitch-checkbox"
																								id="misreporacces<%=i%>"> <label
																								class="onoffswitch-label"
																								for="misreporacces<%=i%>"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateAccesssStatusMis(this.checked,'<s:property value="name"/>','<s:property value="userId"/>')"
																								id="misreporacces<%=i%>"> <label
																								class="onoffswitch-label"
																								for="misreporacces<%=i%>"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>
															</div>
														</s:if>
													</div>



													<div id="access33">
														<s:if test="indent==true">
															<h1>Indent</h1>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12 hidden"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<%
																	int i = 1;
																%>
																<s:iterator value="indentrolelist">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="modulename" />
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;"><s:if
																					test="flag==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox" checked="checked"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								class="onoffswitch-checkbox"
																								id="indentrole<%=i%>"> <label
																								class="onoffswitch-label" for="indentrole<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateAccesssStatus(this.checked,'<s:property value="name"/>','<s:property value="jobtitle"/>')"
																								id="indentrole<%=i%>"> <label
																								class="onoffswitch-label" for="indentrole<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>
															</div>

															<h1>
																Select Indent Location
																<%-- <div class="pull-right" style="margin-top: 4px;">
                									<div class="onoffswitch greensea" id="">
                                                    <input type="checkbox" class="onoffswitch-checkbox" onchange="selectAllIndent(this.checked)" id="selectallindent">
                                                    <label class="onoffswitch-label" for="selectallindent">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                	</div> --%>
															</h1>

															<!-- </div> -->
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																<s:hidden name="location" id="location"></s:hidden>
																<div class="col-lg-10 col-md-10 col-xs-10"
																	style="padding: 0px;">
																	<h4>
																		<s:property value="locationname" />
																	</h4>
																</div>
																<div class="col-lg-2 col-md-2 col-xs-2"
																	style="padding: 0px; float: right;"></div>


																<%
																	i = 1;
																%>
																<s:iterator value="indentloclist">
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			<s:property value="name" />
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;margin-right: 10px;">
																			<li style="border: none;">
																				<div class="control-label">
																					<s:if test="status==1">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								value="<s:property value="id" />"
																								checked="checked"
																								class="onoffswitch-checkbox indentclass"
																								id="indentloc<%=i%>"> <label
																								class="onoffswitch-label" for="indentloc<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</s:if>
																					<s:else>
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								value="<s:property value="id" />"
																								class="onoffswitch-checkbox indentclass"
																								id="indentloc<%=i%>"> <label
																								class="onoffswitch-label" for="indentloc<%=i%>">
																								<span class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</s:else>

																				</div>
																			</li>
																		</ul>
																	</div>
																	<%
																		i++;
																	%>
																</s:iterator>
															</div>

															<div class="col-lg-12 col-md-12 col-xs-12 text-right"
																style="padding: 0px;">
																<button type="button" class="btn btn-primary"
																	onclick="updateindentLocation('<s:property value="userId"/>')">Save
																	Indent Locations</button>
															</div>

														</s:if>
													</div>

													<div id="access16" class="hidden">
														<s:if test="inventory== true">
															<h1>Inventory</h1>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			Supplier Payment Edit
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;">
																			<li style="border: none;"><s:if
																					test="edit_paypo==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">

																							<input type="checkbox" checked="checked"
																								onclick="updateUserWiseAccess(this.checked,'edit_paypo','<s:property value="userId"/>')"
																								class="onoffswitch-checkbox"
																								id="inven_edit_paypo"> <label
																								class="onoffswitch-label"
																								for="inven_edit_paypo"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateUserWiseAccess(this.checked,'edit_paypo','<s:property value="userId"/>')"
																								id="inven_edit_paypo"> <label
																								class="onoffswitch-label"
																								for="inven_edit_paypo"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	
															</div>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			Inventory Adjustment Process
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;">
																			<li style="border: none;"><s:if
																					test="adjustmentaccess==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">

																							<input type="checkbox" checked="checked"
																								onclick="updateUserWiseAccess(this.checked,'adjustmentaccess','<s:property value="userId"/>')"
																								class="onoffswitch-checkbox"
																								id="inven_adjustmentaccess"> <label
																								class="onoffswitch-label"
																								for="inven_adjustmentaccess"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateUserWiseAccess(this.checked,'adjustmentaccess','<s:property value="userId"/>')"
																								id="inven_adjustmentaccess"> <label
																								class="onoffswitch-label"
																								for="inven_adjustmentaccess"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	
															</div>
															<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12"
																style="padding: 0px; border-bottom: 1px dashed rgba(221, 221, 221, 0.59);">
																
																	<div class="col-lg-10 col-md-10 col-xs-10"
																		style="padding: 0px;">
																		<h4>
																			Inventory Supplier Add
																		</h4>
																	</div>
																	<div class="col-lg-2 col-md-2 col-xs-2"
																		style="padding: 0px;">
																		<ul class="settings"
																			style="list-style: none; margin-top: 7px; float: right;">
																			<li style="border: none;"><s:if
																					test="supplier_add==true">
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">

																							<input type="checkbox" checked="checked"
																								onclick="updateUserWiseAccess(this.checked,'supplier_add','<s:property value="userId"/>')"
																								class="onoffswitch-checkbox"
																								id="inven_supplier_add"> <label
																								class="onoffswitch-label"
																								for="inven_supplier_add"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:if> <s:else>
																					<div class="control-label">
																						<div class="onoffswitch greensea" id="">
																							<input type="checkbox"
																								class="onoffswitch-checkbox"
																								onclick="updateUserWiseAccess(this.checked,'supplier_add','<s:property value="userId"/>')"
																								id="inven_supplier_add"> <label
																								class="onoffswitch-label"
																								for="inven_supplier_add"> <span
																								class="onoffswitch-inner"></span> <span
																								class="onoffswitch-switch"></span>
																							</label>
																						</div>
																					</div>
																				</s:else></li>
																		</ul>
																	</div>
																	
															</div>
														</s:if>
													</div>

												</div>
											</div>
											<s:if test="ipdid==true">
												<div class="col-lg-12 col-md-12 col-xs-12 text-right"
													style="padding: 0px;">
													<button type="button" class="btn btn-primary"
														onclick="updatewardid('<s:property value="userId"/>')">Save</button>
												</div>
											</s:if>
										</div>
									</div>





								</div>
							</div>

							<div class="col-lg-1 col-md-1"></div>


						</div>







					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="common/chosen_v1.1.0/chosen.jquery.js"
		type="text/javascript"></script>
	<script src="common/chosen_v1.1.0/docsupport/prism.js"
		type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var config = {
			'.chosen-select' : {},
			'.chosen-select-deselect' : {
				allow_single_deselect : true
			},
			'.chosen-select-no-single' : {
				disable_search_threshold : 10
			},
			'.chosen-select-no-results' : {
				no_results_text : 'Oops, nothing found!'
			},
			'.chosen-select-width' : {
				width : "100%"
			}
		}
		for ( var selector in config) {
			$(selector).chosen(config[selector]);
		}
	</script>

	<script>
		$(window).load(function() {

			$('#select-all').change(function() {
				if ($(this).is(":checked")) {
					$('#mails-list .mail-select input').prop('checked', true);
				} else {
					$('#mails-list .mail-select input').prop('checked', false);
				}
			});
		});
	</script>

	<script>
		//Code By Abhi
		$(function() {
			$('.setheight').slimScroll({
				height : '550px',
				railVisible : true,
				alwaysVisible : true
			});
		});
	</script>

	<script src="mis/kpiplugin/js/jquery.scrollto.js"></script>
	<script>
		$(document)
				.ready(
						function() {

							/** 
							 * This part does the "fixed navigation after scroll" functionality
							 * We use the jQuery function scroll() to recalculate our variables as the 
							 * page is scrolled/
							 */
							$(window).scroll(function() {
								var window_top = $(window).scrollTop() + 12; // the "12" should equal the margin-top value for nav.stick
								var div_top = $('#nav-anchor').offset().top;
								if (window_top > div_top) {
									$('nav').addClass('stick');
								} else {
									$('nav').removeClass('stick');
								}
							});

							/**
							 * This part causes smooth scrolling using scrollto.js
							 * We target all a tags inside the nav, and apply the scrollto.js to it.
							 */
							$("nav a").click(function(evn) {
								evn.preventDefault();
								$('html,body').scrollTo(this.hash, this.hash);
							});

							/**
							 * This part handles the highlighting functionality.
							 * We use the scroll functionality again, some array creation and 
							 * manipulation, class adding and class removing, and conditional testing
							 */
							var aChildren = $("nav li").children(); // find the a children of the list items
							var aArray = []; // create the empty aArray
							for (var i = 0; i < aChildren.length; i++) {
								var aChild = aChildren[i];
								var ahref = $(aChild).attr('href');
								aArray.push(ahref);
							} // this for loop fills the aArray with attribute href values

							$(window)
									.scroll(
											function() {
												var windowPos = $(window)
														.scrollTop(); // get the offset of the window from the top of page
												var windowHeight = $(window)
														.height(); // get the height of the window
												var docHeight = $(document)
														.height();

												for (var i = 0; i < aArray.length; i++) {
													var theID = aArray[i];
													var divPos = $(theID)
															.offset().top; // get the offset of the div from the top of page
													var divHeight = $(theID)
															.height(); // get the height of the div in question
													if (windowPos >= divPos
															&& windowPos < (divPos + divHeight)) {
														$(
																"a[href='"
																		+ theID
																		+ "']")
																.addClass(
																		"nav-active");
													} else {
														$(
																"a[href='"
																		+ theID
																		+ "']")
																.removeClass(
																		"nav-active");
													}
												}

												if (windowPos + windowHeight == docHeight) {
													if (!$(
															"nav li:last-child a")
															.hasClass(
																	"nav-active")) {
														var navActiveCurrent = $(
																".nav-active")
																.attr("href");
														$(
																"a[href='"
																		+ navActiveCurrent
																		+ "']")
																.removeClass(
																		"nav-active");
														$("nav li:last-child a")
																.addClass(
																		"nav-active");
													}
												}
											});
						});
	</script>

</body>
</html>