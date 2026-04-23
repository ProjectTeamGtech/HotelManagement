<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Registration.eu.entity.*"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
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
	<!-- <li class=""><a class="tabAnchor"  role="tab" data-target-div="#clinicLocation" id="CLTab">Clinic
			Location</a></li> -->
</ul>
<s:form action="updatesaveClinicRegistration" id="registerfrm" theme="simple" method="post" enctype="multipart/form-data">
<s:hidden name="id" id="id"></s:hidden>
<s:hidden name="selectedUserid" id="selectedUserid"/>

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
							<s:textfield type = "email" id="email" name="email" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Email" title="Enter Email"/>
						</div>
						<div class="form-group">
							<label>Mobile No</label><label class="text-danger reqd-info">*</label>
							<s:textfield type = "number" id="mobileNo" name="mobileNo" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Mobile Number" title="Enter Mobile Number"/>
						</div>
						<div class="form-group">
							<label>Landline No</label>
							<s:textfield id="landline" name="landLine" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Landline"  title="Enter Landline"/>
						</div>
						<div class="form-group">
							<label>WebSite URL</label>
							<s:textfield id="websiteUrl" name="websiteUrl" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Website URL"  title="Enter Website URL"/>
						</div>
						
						<s:hidden name = "userImageFileName"></s:hidden>
						
						<s:if test="userImageFileName!=null">
						<div class="form-group">
							<label>Clinic Logo</label>
							<img src="liveData/clinicLogo/<s:property value="userImageFileName"/>" height="70" width="80">
						</div>
						</s:if>
						<div class="form-group">
							<label>Upload Clinic Logo</label>
							<s:file name="userImage"/>					
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
								fieldValue="standalone" value="%{standalone}" theme="simple" cssClass="chkGrp" />
								
							<s:label value="StanAlone"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="pkgsubscription" id="hostedDB"
								fieldValue="hostedDB" value="%{hostedDB}"
								title="(Only DB is backed up betn Local DB and Server)"  cssClass="chkGrp" />
								
							<s:label value="Hosted DB"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="pkgsubscription" id="onlineSingleDevice"
								fieldValue="onlineSingleDevice" value="%{onlineSingleDevice}"
								title="(Online Single Device (PC))"  cssClass="chkGrp" />
							<s:label value="Online(single device (PC))"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="pkgsubscription" id="onlineMultiDevice"
								fieldValue="onlineMultiDevice" value="%{onlineMultiDevice}"
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
						<h4>Functionality Subscription123</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">						
						<h4><s:checkbox name="funSubscription" id="basicPackage" 
								fieldValue="basicPackage" onclick="setBasicPackage();"
								title="Select All Basic Package" cssClass="chkGrp2"/>
						<s:label value="Basic Package"></s:label><label class="text-danger reqd-info">*</label></h4>				
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="diaryManagement"
								fieldValue="diaryManagement" value="%{diaryManagement}"
								title="Diary Maangement" cssClass="chkGrp2"/>
							<s:label value="Diary Management"></s:label>
						</div>
						
						<div class="form-group">
							<s:checkbox name="funSubscription" id="appointmentBooking"
								fieldValue="appointmentBooking" value="%{appointmentBooking}"
								title="Appointment Booking"  cssClass="chkGrp2" />
							<s:label value="Appointment Booking"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="basicFinance"
								fieldValue="basicFinance" value="%{basicFinance}"
								title="Basic Finance"  cssClass="chkGrp2" />
							<s:label value="Basic Finance"></s:label>
						</div>
						<div class="form-group">						
							<h4><s:checkbox name="funSubscription" id="advancePackage" 
								fieldValue="advancePackage" onclick="setAdvancePackage();"
								title="Select All Advance Package" cssClass="chkGrp2"/>
							<s:label value="Advance Package ( All of above + )"></s:label><label class="text-danger reqd-info">*</label></h4>						
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="fullFinance"
								fieldValue="fullFinance" value="%{fullFinance}"
								title="Full Finance"  cssClass="chkGrp2" />
							<s:label value="Full Finance"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="medicalRecord"
								fieldValue="medicalRecord" value="%{medicalRecord}"
								title="Medical Record"  cssClass="chkGrp2" />
							<s:label value="Medical Record"></s:label>
						</div>
						<div class="form-group">
						<h4><s:checkbox name="funSubscription" id="premierPackage" 
								fieldValue="premierPackage" onclick="setPremierPackage();"
								title="Select All Premier Package" cssClass="chkGrp2"/>
							<s:label value="Premier Package ( All of above + ) "></s:label><label class="text-danger reqd-info">*</label></h4>						
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="clinicResourceMngment"
								fieldValue="clinicResourceMngment"
								value="%{clinicResourceMngment}"
								title="Clinic Resource Management" cssClass="chkGrp2" />
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
						<h4><s:checkbox name="funSubscription" id="otherPackage" 
								fieldValue="otherPackage" onclick="setOtherPackage();"
								title="Select All Other Package" cssClass="chkGrp2"/>
							<s:label value="Other Package"></s:label><label class="text-danger reqd-info">*</label></h4>						
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="communication"
								fieldValue="communication" value="%{communication}"
								title="Communication" cssClass="chkGrp2" />
							<s:label value="Communication"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="report"
								fieldValue="report" value="%{report}"
								title="Report" cssClass="chkGrp2" />
							<s:label value="Report"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="assessmentForms"
								fieldValue="assessmentForms" value="%{assessmentForms}"
								title="Assessment Forms" cssClass="chkGrp2" />
							<s:label value="Assessment Forms"></s:label>
						</div>
						<div class="form-group">
						<h4><s:checkbox name="funSubscription" id="excessDevices" 
								fieldValue="excessDevices" onclick="setExcessDevices();"
								title="Select All Excess Devices" cssClass="chkGrp2"/>
							<s:label value="Excess Devices"></s:label><label class="text-danger reqd-info">*</label></h4>						
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="desktop"
								fieldValue="desktop" value="%{desktop}"
								title="Desktop" cssClass="chkGrp2" />
							<s:label value="Desktop"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="mobile"
								fieldValue="mobile" value="%{mobile}"
								title="Mobile" cssClass="chkGrp2" />
							<s:label value="Mobile"></s:label>
						</div>						
						<div class="form-group">
							<s:checkbox name="funSubscription" id="iOS"
								fieldValue="iOS" value="%{iOS}"
								title="iOS" cssClass="chkGrp2" />
							<s:label value="iOS"></s:label>
						</div>
						<div class="form-group">
							<s:checkbox name="funSubscription" id="tablet"
								fieldValue="tablet" value="%{tablet}"
								title="Tablet" cssClass="chkGrp2" />
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
								placeholder="Enter User Id" disabled="true"/>
							<label id="errorUserId" class="text-danger"></label>
						</div>
						
						<div class="form-group">
							<input type="button" value="Previous" class="btn btn-primary previous" 
data-target-div="#functionalitySubscription" data-target-tab="#FSTab">
							<input id="nextClinic" type="button" value="Next" class="btn btn-primary next" 
data-target-div="#clinicLocation" data-target-tab="#CLTab">
<s:submit theme="simple" cssClass="btn btn-primary"
												value="Update" onclick="return validateAI()" />
										<s:reset theme="simple" cssClass="btn btn-primary" />
						</div>
					</div>
				</div>
			</div>


		</div>
	
	</div>
<%-- 	<div class="tab-pane fade" id="clinicLocation"  data-validation-function="validateUpdateCL" data-tab-id="#CLTab">
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
										<th align="center">Check</th>
										<th align="center">#</th>
										<th align="center">Country</th>
										<th align="center">Color</th>
										<th align="center">City</th>
										<th align="center">Postcode</th>
										<th align="center">Address</th>
										<th align="center">Location</th>
										<th align="center">Contact No</th>
									</tr>
								</thead>
								<tbody>
							<%int lcount = (Integer)session.getAttribute("locationcount"); %>
							
							<%ArrayList<Clinic>clinicLocationlist = (ArrayList<Clinic>)session.getAttribute("clinicLocationlist"); 
								int i = 0;
							%>
							<%ArrayList<Clinic>colorList = (ArrayList<Clinic>)session.getAttribute("colorList"); 
								int j = 0;
							%>
								
										<% for(Clinic clinicList:clinicLocationlist ) {%>
											<tr>
												<TD><INPUT type="checkbox" name="chk"/></TD>
            					 				<TD> <%=i%> </TD>
	
										
										
										
										<td><input  type="text" name="location[<%=i%>].country" value="<%=clinicList.getCountry() %>" class="form-control showToolTip country"></td>
												<td><input type="text" name="location[<%=i%>].colorName" class="form-control color colorName" value="<%=clinicList.getColorName()%>" onchange = "checkColorExist(this.value)">
												<label id = "errorColor" class = "text-danger"></label>
												</td>	
												
												<td><input  type="text" name="location[<%=i%>].city" value="<%=clinicList.getCity() %>" class="form-control showToolTip city" data-toggle="tooltip"></td>
												<td><input  type="text" name="location[<%=i%>].pinCode" value="<%=clinicList.getPinCode() %>" class="tform-control showToolTip pinCode" ></td>
												<td><textarea  name="location[<%=i%>].address" class="form-control address" ><%=clinicList.getAddress() %></textarea></td>
												<td><input  type="text" name="location[<%=i%>].locationname" value="<%=clinicList.getLocationname() %>" class="form-control showToolTip locationname" data-toggle="tooltip" ></td>
												<td><input  type="text" name="location[<%=i%>].contactNo" value="<%=clinicList.getContactNo()%>" class="form-control showToolTip contactNo" data-toggle="tooltip" ></td>
												
												<td><input style="display:none" type="text" name="location[<%=i%>].locationid" value="<%=clinicList.getLocationid() %>" class="form-control showToolTip locationname" data-toggle="tooltip"></td>
										
									
									</tr>
								<% i++;%>
										<%} %>
								
								</tbody>
							</table>
						</div>
						<div class="form-group">
							<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request); %>
							<%if(loginInfo.getUserType() == 1){ %>
								<a onclick="addRow('addressesTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
							<%}%>	
								<a onclick="deleteRow('addressesTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
								<a data-target-div="#accountInformation" data-target-tab="#AITab" class="btn btn-primary previous"><i class="fa fa-mail-reply"></i> Previous</a>
										<s:submit theme="simple" cssClass="btn btn-primary"
												value="Update" onclick="return validateUpdateCL()" />
										<s:reset theme="simple" cssClass="btn btn-primary" />
								
						</div>
						
						
					</div>
				</div>
				
			</div>
		</div>
				
	
	</div> --%>
</div>
</s:form>