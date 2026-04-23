<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="registration/js/clinic.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<link href="registration/css/tabStyle.css" rel="stylesheet" />


<ul id="docRegTabs" class="nav nav-tabs" role="tablist">
	<li class="active"><a class="tabAnchor" role="tab" data-target-div="#generalInformation" id="GITab">General
			Information</a></li>
	<li class="" ><a class="tabAnchor"  role="tab" data-target-div="#packageSubscription" id="PSTab">Package
			Subscription</a></li>
	<li class="" ><a class="tabAnchor"  role="tab" data-target-div="#functionalitySubscription" id="FSTab">Functionality
			Subscription</a></li>
	<li class=""><a class="tabAnchor"  role="tab" data-target-div="#accountInformation" id="AITab">Account
			Information</a></li>
	<li class=""><a class="tabAnchor"  role="tab" data-target-div="#clinicLocation" id="CLTab">Clinic
			Location</a></li>
</ul>
<s:form action="saveClinicRegistration" id="registerfrm" theme="simple" method="post" enctype="multipart/form-data">

<div id="docRegTabsContent" class="tab-content">


	<div class="tab-pane fade active in" id="generalInformation" data-validation-function="validateGI" data-tab-id="#GITab">
		<div class="panel panel-primary">
			<div class="panel-body">
`				<div class="row">
					<div class="col-lg-12">
						<h4>General Information</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>Clinic Name</label><label class="text-danger reqd-info">*</label>
							<s:textfield name="clinicName" id="clinicName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Clinic Name" title="Enter Clinic Name"/>
						</div>
						<div class="form-group">
							<label>Clinic Owner Name(SPOC)</label><label class="text-danger reqd-info">*</label>
							<s:textfield name="clinicOwner" id="clinicOwner" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Clinic Owner Name" title="Enter Clinic Owner Name"/>
						</div>
						<div class="form-group">
							<label>Qualification</label>
							<s:textfield name="owner_qualification" id="owner_qualification" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Clinic Owner Qualification" title="Enter Clinic Owner Qualification"/>
						</div>
						<div class="form-group">
							<label>Email-Address</label><label class="text-danger reqd-info">*</label>
							<s:textfield onchange="checkEmaildExist(this.value)"  type = "email" id="email" name="email" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Email" title="Enter Email"/>
							<label id="errorEmail" class="text-danger"></label>
						</div>
						<div class="form-group">
							<label>Mobile No</label>
							<s:textfield type = "number" id="mobileNo" name="mobileNo" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Mobile Number" title="Enter Mobile Number"/>
						</div>
						<div class="form-group">
							<label>Landline No</label>
							<s:textfield id="landline" name="landLine" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Landline"  title="Enter Landline"/>
						</div>
						<div class="form-group">
						<label>Clinic Logo</label>
							<s:file name="userImage" id="userImage"/>
						</div>
						<div class="form-group">
							<input type="button" value="Next"  class="btn btn-primary next" data-target-div="#packageSubscription" data-target-tab="#PSTab">
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<div class="tab-pane fade" id="packageSubscription" data-validation-function="validatePS" data-tab-id="#PSTab">
		<div class="panel panel-primary">
			<div class="panel-body">
`				<div class="row">
					<div class="col-lg-12">
						<h4>Package Subscription</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>Select Package Subscription</label><label class="text-danger reqd-info">*</label>
						</div>
						<div class="form-group">
							<s:checkbox name="pkgsubscription" id="standalone"
								fieldValue="standalone" value="%{isStandalone}" theme="simple" cssClass="chkGrp"/>
							<s:label value="StanAlone" for="standalone"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="pkgsubscription" id="hostedDB"
								fieldValue="hostedDB" value="%{isHostedDB}"
								title="(Only DB is backed up betn Local DB and Server)"  cssClass="chkGrp"/>
							<s:label value="Hosted DB"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="pkgsubscription" id="onlineSingleDevice"
								fieldValue="onlineSingleDevice" value="%{isOnlineSingleDevice}"
								title="(Online Single Device (PC))"  cssClass="chkGrp"/>
							<s:label value="Online(single device (PC))"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="pkgsubscription" id="onlineMultiDevice"
								fieldValue="onlineMultiDevice" value="%{isOnlineMultiDevice}"
								title="(Online Multi Device StandAlone, Online, Online Mobile Apps, Responsive Apps"  cssClass="chkGrp"/>
							<s:label value="Online(Multi Device)"></s:label>
						</div>
						<div class="form-group">
							<input type="button" value="Previous"
								onclick="showGeneralInfoTab()" class="btn btn-primary previous" data-target-div="#generalInformation" data-target-tab="#GITab">
							<input type="button" value="Next" 
								class="btn btn-primary next"  value="Next" data-target-div="#functionalitySubscription" data-target-tab="#FSTab">
						</div>

					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	<div class="tab-pane fade" id="functionalitySubscription" data-validation-function="validateFS" data-tab-id="#FSTab">
	<div class="panel panel-primary">
			<div class="panel-body">
`				<div class="row">
					<div class="col-lg-12">
						<h4>Functionality Subscription456</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>Basic Package</label><label class="text-danger reqd-info">*</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<s:checkbox name="funSubscription" id="basicPackage"
								fieldValue="basicPackage" onclick="setBasicPackage();"
								title="Select All Basic Package" cssClass="chkGrp2"/>
							<s:label value="Select All"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="diaryManagement" 
								fieldValue="diaryManagement" value="%{isDiaryManagement}"
								title="Diary Maangement" cssClass="chkGrp2"/>
							<s:label value="Diary Management"></s:label>
						</div>
						
						<div class="form-group">
							<s:checkbox name="funSubscription" id="appointmentBooking"
								fieldValue="appointmentBooking" value="%{isAppointmentBooking}"
								title="Appointment Booking"  cssClass="chkGrp2"/>
							<s:label value="Appointment Booking"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="basicFinance"
								fieldValue="basicFinance" value="%{isBasicFinance}"
								title="Basic Finance"  cssClass="chkGrp2"/>
							<s:label value="Basic Finance"></s:label>
						</div>
						<div class="form-group">
							<label>Advance Package ( All of above + )</label><label class="text-danger reqd-info">*</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<s:checkbox name="funSubscription" id="advancePackage"
								fieldValue="advancePackage" onclick="setAdvancePackage();"
								title="Select All Advance Package" cssClass="chkGrp2"/>
							<s:label value="Select All"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="fullFinance"
								fieldValue="fullFinance" value="%{isFullFinance}"
								title="Full Finance"  cssClass="chkGrp2"/>
							<s:label value="Full Finance"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="medicalRecord"
								fieldValue="medicalRecord" value="%{isMedicalRecord}"
								title="Medical Record"  cssClass="chkGrp2"/>
							<s:label value="Medical Record"></s:label>
						</div>
						<div class="form-group">
							<label>Premier Package ( All of above + ) : </label><label class="text-danger reqd-info">*</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<s:checkbox name="funSubscription" id="premierPackage"
								fieldValue="advancePackage" onclick="setPremierPackage();"
								title="Select All Premier Package" cssClass="chkGrp2"/>
							<s:label value="Select All"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="clinicResourceMngment"
								fieldValue="clinicResourceMngment"
								value="%{clinicResourceMngment}"
								title="Clinic Resource Management" cssClass="chkGrp2"/>
							<s:label value="Clinic Resource Management"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="clinicPayrollMngment"
								fieldValue="clinicPayrollMngment"
								value="%{clinicPayrollMngment}"
								title="Clinic Payroll Management" cssClass="chkGrp2" />
							<s:label value="Clinic Payroll Management"></s:label>
						</div>
						<div class="form-group">
							<label>Other Package</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<s:checkbox name="funSubscription" id="otherPackage"
								fieldValue="otherPackage" onclick="setOtherPackage();"
								title="Select All Other Package" cssClass="chkGrp2"/>
							<s:label value="Select All"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="communication"
								fieldValue="communication" value="%{isCommunication}"
								title="Communication" cssClass="chkGrp2"/>
							<s:label value="Communication"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="report"
								fieldValue="report" value="%{isReport}"
								title="Report" cssClass="chkGrp2"/>
							<s:label value="Report"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="assessmentForms"
								fieldValue="assessmentForms" value="%{isAssessmentForms}"
								title="Assessment Forms" cssClass="chkGrp2"/>
							<s:label value="Assessment Forms"></s:label>
						</div>
						<div class="form-group">
							<label>Excess Devices</label>&nbsp;&nbsp;&nbsp;&nbsp;
								<s:checkbox name="funSubscription" id="excessDevices"
								fieldValue="excessDevices" onclick="setExcessDevices();"
								title="Select All Excess Devices" cssClass="chkGrp2"/>
							<s:label value="Select All"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="desktop"
								fieldValue="desktop" value="%{isDesktop}"
								title="Desktop" cssClass="chkGrp2"/>
							<s:label value="Desktop"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="mobile"
								fieldValue="mobile" value="%{isMobile}"
								title="Mobile" cssClass="chkGrp2"/>
							<s:label value="Mobile"></s:label>
						</div>						
						<div class="form-group">
							<s:checkbox name="funSubscription" id="iOS"
								fieldValue="iOS" value="%{isIOS}"
								title="iOS" cssClass="chkGrp2"/>
							<s:label value="iOS"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="tablet"
								fieldValue="tablet" value="%{isTablet}"
								title="Tablet" cssClass="chkGrp2"/>
							<s:label value="Tablet"></s:label>
						</div>
						
						<div class="form-group">
							<input type="button" value="Previous" class="btn btn-primary previous" data-target-div="#packageSubscription" data-target-tab="#PSTab"> 
							<input type="button" value="Next" class="btn btn-primary next" data-target-div="#accountInformation" data-target-tab="#AITab">
						</div>

					</div>
				</div>
				
			</div>
		</div>
	
	</div>
	
	<div class="tab-pane fade" id="accountInformation" data-validation-function="validateAI" data-tab-id="#AITab">
	<div class="panel panel-primary">
			<div class="panel-body">				`
				<div class="row">
					<div class="col-lg-12">
						<h4>Account Information</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>User Id</label><label class="text-danger reqd-info">*</label>
							<s:textfield id="userId" name="userId" theme="simple"
								title="Minimum 6 characters, no special characters except underscore, starts with alphabet, no whitespaces between words,
					 	eg. sachin_verma, sachinverma, sachin123"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								onchange="checkUserIdExist(this.value)"
								placeholder="Enter User Id" />
							<label id="errorUserId" class="text-danger"></label>
						</div>
						<div class="form-group">
							<label>Password</label><label class="text-danger reqd-info">*</label>
							<s:password id="password" name="password" theme="simple"
								title="Minimum 6 characters, make it hard to guess"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Password" />

						</div>
						<div class="form-group">
							<label>Confirmed Password</label><label
								class="text-danger reqd-info">*</label>
							<s:password id="confirmPassword" name="confirmPassword"
								key="label.confirmPassword" labelposition="left" required="true"
								title="Re-enter above password"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Confirmed Password" />
						</div>
						<div class="form-group">
							<input type="button" value="Previous" class="btn btn-primary previous" 
data-target-div="#functionalitySubscription" data-target-tab="#FSTab">
							<input id="nextClinic" type="button" value="Next" class="btn btn-primary next" 
data-target-div="#clinicLocation" data-target-tab="#CLTab">
						</div>
					</div>
				</div>
			</div>


		</div>
	
	</div>
	<div class="tab-pane fade" id="clinicLocation"  data-validation-function="validateCL" data-tab-id="#CLTab">
	<div class="panel panel-primary">
			<div class="panel-body">
				`
				<div class="row">
					<div class="col-lg-12">
						<h4>Clinic Location</h4>
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "addressesTable">
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Address Type</th>	
										<th align="center">Address</th>	
										<th align="center">Postcode</th>	
										<th align="center">City Name</th>	
										<th align="center">Contact No.</th>	
										<th align="center">Email Id</th>					
										<th align="center">Colors123</th>
										<th align="center">Include</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<TD><INPUT type="checkbox" name="chk" title="Delete row" /></TD>
										
										<TD>1</TD>
										
										<!--  <td><input type="text" name="location[0].country"
											class="form-control showToolTip country" data-toggle="tooltip" value="United Kingdom"></td>
										-->
										
										<td>
										<%-- <select name="location[0].addressType" class="form-control showToolTip addressType" data-toggle="tooltip">
												<option value="Registered"></option>
												<option value="Branch"></option>
											</select> --%>
											
										<input type="text" value="Registered" disabled="true" name="location[0].addressType" class="form-control showToolTip addressType" data-toggle="tooltip">	
											
										</td>
										
										<td><textarea name="location[0].address"
												class="form-control showToolTip address" data-toggle="tooltip" rows="1"></textarea></td>
												
										<td><input type="text" name="location[0].pinCode"
											class="form-control showToolTip pinCode" data-toggle="tooltip"></td>
											
										<td><input type="text" name="location[0].locationname"
											class="form-control showToolTip locationname" data-toggle="tooltip"></td>
											
										<td><input type="text" name="location[0].contactNo"
											class="form-control showToolTip contactNo" data-toggle="tooltip">
											<label id = "noError" class="text-danger"></label>	</td>
											
										<td><input type="text" name="location[0].emailId"
											class="form-control showToolTip emailId" data-toggle="tooltip">
											<label id = "emailError" class="text-danger"></label></td>
											
										<td><input type="text" name="location[0].colorName"
											class="form-control showToolTip colorName" data-toggle="tooltip"
											 value=" "
											onchange="checkColorExist(this.value)">
											<label id = "errorColor" class = "text-danger"></label>
											</td>										
										
										<!--<td><input type="text" name="location[0].locationname"
											class="form-control showToolTip locationname" data-toggle="tooltip"></td>
										-->									
											
										<td><input type="checkbox" name="location[0].checkLocation" title="In all Communications" checked="checked" readonly="readonly" disabled="disabled"/>
									    <label id = "checkLocError" class = "text-danger"></label></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
								<a onclick="addRow('addressesTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteRow('addressesTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
								<a data-target-div="#accountInformation" data-target-tab="#AITab" class="btn btn-primary previous"><i class="fa fa-mail-reply"></i> Previous</a>
										 <s:submit theme="simple" cssClass="btn btn-primary"
												value="Save" onclick="return validateCL()" />
												<%-- <s:submit theme="simple" cssClass="btn btn-primary"
												value="Save" /> --%>
										<s:reset theme="simple" cssClass="btn btn-primary" />
								
						</div>
						
						
					</div>
				</div>
				
			</div>
		</div>
				
	
	</div>
</div>
</s:form>