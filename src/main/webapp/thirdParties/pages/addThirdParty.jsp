<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="thirdParties/js/addThirdPartyValidaton.js"></script>
<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href = "#">Third Parties</a></li>
				<li ><a href = "/APM/ThirdParty">All Third Party List</a></li>
					<li class="active">Add New Third Party </li>
		</ol>
	</div>
</div>
<ul id="docRegTabs" class="nav nav-tabs" role="tablist">
	<li class="active"><a class="tabAnchor" role="tab" data-target-div="#personalDetails" id="PDTab">Personal Details</a></li>
	<li class="" ><a class="tabAnchor"  role="tab" data-target-div="#companyDetails" id="CDTab">Company Details</a></li>
	<li class="" ><a class="tabAnchor"  role="tab" data-target-div="#accountSetting" id="ASTab">Account Setting</a></li>
	<li class=""><a class="tabAnchor"  role="tab" data-target-div="#contactPreferences" id="CPTab">Contact Preferences</a></li>
	
</ul>
	


<s:form action="saveDetailsThirdParty" theme="simple" >
<div id="docRegTabsContent" class="tab-content">


	<div class="tab-pane fade active in" id="personalDetails" data-validation-function="validatePD" data-tab-id="#PDTab">
		<div class="panel panel-primary">
			<div class="panel-body">
`				<div class="row">
					<div class="col-lg-12">
						<h4>Personal Details</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						
						<div class="form-group">
							<label>Select Third Party Type </label><label class="text-danger reqd-info">*</label>
			
							<s:if test="%{#thirdPartyTypeList != 'null'}" >
								 	<s:select id="type" name="type" list="thirdPartyTypeList" listKey="id" listValue="name" headerKey="0" theme="simple" title="Select Third Party Type" headerValue="Select" cssClass="form-control showToolTip" data-toggle = "tooltip"/>
							</s:if>
						</div>
			<div class="form-group">			
				<label>Name</label><label class="text-danger reqd-info">*</label>
		 		<s:textfield id = "name" name = "name" theme="simple"  cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Name" title="Enter Name"/>
		 	</div>
		 	<div class="form-group">	
		 	<label>Salutation</label>
		 	<s:textfield id = "salutation" name = "salutation" theme="simple"  cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Salutation" title="Enter Salutation"/>
		 	</div>
		 	<div class="form-group">	
		 	<label>Title</label>
		 	<s:textfield id = "title" name = "title" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Title" title="Enter Title"/>
		 	</div>
		 	<div class="form-group">	
		 	<label>Location</label>
		 	<s:textfield id = "department" name = "department" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Location" title="Enter Location"/>
		 	</div>
		 	<div class="form-group">	
		 	<label>Index/Search Name</label>
		 	<s:textfield id = "searchName" name = "searchName" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Search Name" title="Enter Search Name"/>
		 	</div>
		 	
		 	<div class="form-group">	
		 	<label>Direct Telephone Line</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield id = "telephoneLine" name = "telephoneLine" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Telephone Line" title="Enter Telephone Line"/>
		 	</div>
		 	
		 	<div class="form-group">	
		 	<label>Email</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield id = "email" name = "email" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Email" title="Enter Email"/>
		 	</div>
		 	<div class="form-group">	
		 	<label>Email-CC</label>
		 	<s:textfield id = "emailCc" name = "emailCc" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Email-Cc" title="Enter Eamil-Cc"/>
		 	</div>
		 	<div class="form-group">	
		 	<label>Notes</label>
		 	
		 	<s:textarea name = "notes" id = "notes" rows="2" cols="24" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Notes" title="Enter Notes"/>
		 	</div>
		 	<div class="form-group">	
		 	<label>Company Details</label>
		 	<s:textarea id = "companyDetails1" name = "companyDetails" rows="2" cols="24" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Company Details" title="Enter Company Details"/>
		 	</div>
		 	<div class="form-group">	
		 	<a class="btn btn-primary next" data-target-div="#companyDetails"
									data-target-tab="#CDTab"><i class="fa fa-share"></i> Next</a>
			</div>						
		 	
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="companyDetails" data-validation-function="validateCD" data-tab-id="#CDTab">
		<div class="panel panel-primary">
			<div class="panel-body">
`				<div class="row">
					<div class="col-lg-12">
						<h4>Company Details</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						
			<div class="form-group">
			<label>Company Name</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield id = "companyName" name = "companyName" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Company Name" title="Enter Company Name"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Address</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield id = "address" name = "address" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Address" title="Enter Address"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Town</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield id = "town" name = "town" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Town" title="Enter Town"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Country</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield id = "country" name = "country" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Country" title="Enter Country" value="United Kingdom"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Post Code</label>
		 	<s:textfield id = "postcode" name = "postcode" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Post Code" title="Enter Post Code"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Telephone No</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield id = "compnyTelephone" name = "compnyTelephone" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Telephone No." title="Enter Telephone No."/>
		 	</div>
		 	<div class="form-group">
		 	<label>Fax</label>
		 	
		 	<s:textfield id = "fax" name = "fax" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Fax" title="Enter Fax"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Web</label>
		 	<s:textfield id = "web" name = "web" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Website" title="Enter Website"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Reference No</label>
		 	
		 	<s:textfield id = "referenceNo" name = "referenceNo" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Reference No." title="Enter Reference No."/>
		 	</div>
		 	<div class="form-group">
		 	<label>Company Email</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield id = "compnyEmail" name = "compnyEmail" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Comapny Email" title="Enter Comapny Email"/>
		 	</div>
		 		
		 		<div class="form-group">
		 		<a class="previous btn btn-primary"
									data-target-div="#personalDetails" data-target-tab="#PDTab"><i
									class="fa fa-reply"></i> Previous</a>
								<a class="next btn btn-primary" data-target-div="#accountSetting"
									data-target-tab="#ASTab"><i class="fa fa-share"></i> Next</a>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="tab-pane fade" id="accountSetting" data-validation-function="validateAS" data-tab-id="#ASTab">
		<div class="panel panel-primary">
			<div class="panel-body">
`				<div class="row">
					<div class="col-lg-12">
						<h4>Account Setting</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
			
		 	<div class="form-group">
		 	<label>Warning Message:</label>
		 	<s:textarea name="warningMsg" id ="warningMsg" rows="5" cols="33" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Warning Message" title="Enter Warning Message"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Account Note:</label>
		 	<s:textarea name="accountsNotes" id="accountsNotes" rows="5" cols="33" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Account Note" title="Enter Account Note"/>
		 	</div>
		 	
		 	<label><b>Other Account Setting</b></label>
		 	<div class="form-group">
		 	<label>Account must be in Credit:</label>
		 	<s:checkbox name="accountMustBeInCredit" id="accountMustBeInCredit" fieldValue="accountMustBeInCredit" value="%{isAccountMustBeInCredit}" title="Account must be in credit"/>
		 	</div>
		 	<%-- <div class="form-group">
		 	<label>Account Credit Limit:</label>
		 	<s:textfield name = "accountCreditLimit" id = "accountCreditLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Account Credit Limit" title="Enter Account Credit Limit"/>
		 	</div> --%>
		 	<div class="form-group">
		 	<label>Agreed Credit Limit:</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield name = "outInvoiceLimit" id = "outInvoiceLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Outstanding Invoice Limit" title="Enter Outstanding Invoice Limit"/>
		 	</div>
		 	
		 	<div class="form-group">
		 	<label>Agreed Credit Duration:</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield name = "creditDuration" id = "creditDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Agreed Duration In Days" title="Enter Agreed Duration In Days"/>
		 	</div>
		 	
		 	
		 	
		 	<div class="form-group">
		 	<label>Credit Reminder Limit:</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield name = "accountWarningLimit" id = "accountWarningLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Credit warning limit" title="Enter Credit warning limit"/>
		 	</div>
		 	
		 	<div class="form-group">
		 	<label>Credit Reminder Duration:</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield name = "creditReminderDuration" id = "creditReminderDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Credit Reminder Duration In Days" title="Enter Credit Reminder Duration In Days"/>
		 	</div>
		 	
		 	<div class="form-group">
		 	<label>DNA Limit in %:</label><label class="text-danger reqd-info">*</label>
		 	<s:textfield name = "dnaLimit" id = "dnaLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter DNA Limit" title="Enter DNA Limit"/>
		 	</div>
		 	<div class="form-group">
		 	<label>IPD Share to Practitioner in %:</label>
		 	<s:textfield name = "ipdShare" id = "ipdShare" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Ipd Share" title="Enter Ipd Share"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Surgeon Share in %:</label>
		 	<s:textfield name = "surgeonShare" id = "surgeonShare" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Surgeon Share" title="Enter Surgeon Share"/>
		 	</div>
		 	<div class="form-group">
		 		
		 			<a class="previous btn btn-primary"
									data-target-div="#companyDetail" data-target-tab="#CDTab"><i
									class="fa fa-reply"></i> Previous</a>
								<a class="next btn btn-primary" data-target-div="#contactPreferences"
									data-target-tab="#CPTab"><i class="fa fa-share"></i> Next</a>
		 	</div>
		 	
		 </div>
		</div>
	</div>
</div>
</div>
<div class="tab-pane fade" id="contactPreferences" data-validation-function="validateCP" data-tab-id="#CPTab">
		<div class="panel panel-primary">
			<div class="panel-body">
`				<div class="row">
					<div class="col-lg-12">
						<h4>Contact Preferences</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						
		 	
		 	<label><b>Appointment Booking Confirmation Template</b></label>
		 	<div class="form-group">
		 	<label>Default</label>
		 	
		 	<s:select id = "defaultApmtBookingConfmTemp" name = "defaultApmtBookingConfmTemp" list = "{'Appointment Confirmation','Appointment follow-up Reminder','Appointment Reminder','Appt Confirmation-Pilates','Diabetic Assessment Reminder','Patient Cancelation'}" cssClass="form-control showToolTip" data-toggle = "tooltip"  title="Select Appointment Booking Confirmation Template"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Contact Email Address</label><label class="text-danger reqd-info">*</label>
		 	
		 	<s:textfield id = "confContactEmail" name = "confContactEmail" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Contact Email Address" title="Enter Contact Email Address"/>
		 	</div>
		 	
		 	<label><b>Appointment DNA Template</b></label>
		 	<div class="form-group">
		 	<label>Default</label>
		 	
		 	<s:select id = "defaultApmtDnaTemp" name = "defaultApmtDnaTemp" list = "{'Appointment Confirmation','Appointment follow-up Reminder','Appointment Reminder','Appt Confirmation-Pilates','Diabetic Assessment Reminder','Patient Cancelation'}" cssClass="form-control showToolTip" data-toggle = "tooltip"  title="Select Appointment DNA Template"/>
		 	</div>
		 	<div class="form-group">
		 	<label>Contact Email Address</label><label class="text-danger reqd-info">*</label>
		 	
		 	<s:textfield id = "dnaContactEmail" name = "dnaContactEmail" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Contact Email Address" title="Enter Contact Email Address"/>
		 	</div>
		 	<div class="form-group">
		 	
		 	<a class="previous btn btn-primary"
									data-target-div="#accountSetting" data-target-tab="#ASTab"><i
									class="fa fa-reply"></i> Previous</a>
		 		<s:submit value="Save" cssClass="btn btn-primary" onclick = "return validateCP()"/>
		 		</div>
		 		</div>
		 </div>
		</div>
	</div>
	</div>
</div>
<s:token></s:token>
</s:form>
<a href="backThirdParty" class="btn btn-primary" >Back to List Page</a>
<br/>
			