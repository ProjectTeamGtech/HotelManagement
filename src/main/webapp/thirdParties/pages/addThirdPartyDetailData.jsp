<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="thirdParties/js/addThirdPartyValidaton.js"></script>
<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href = "#">Third Parties</a></li>
				<li ><a href = "/APM/ThirdParty">All Third Party List</a></li>
					<li class="active">Add New Third Party</li>
		</ol>
	</div>
</div>

<s:form action="saveThirdParty" theme="simple" >

<div class="col-lg-12 col-md-12">
	<div class="row">
		<div class="col-lg-12">
			<h4>Personal Details</h4>
		</div>
	</div>	
	<div class="panel panel-primary" style="width: 100%;height: 100%;">
		<div class="panel-body">				
		<div class="form-group">
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Select Third Party Type</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">			
					<s:if test="%{#thirdPartyTypeList != 'null'}" >
						<s:select id="type" name="type" list="thirdPartyTypeList" listKey="id" listValue="name" headerKey="0" theme="simple" title="Select Third Party Type" headerValue="Select" cssClass="form-control showToolTip" data-toggle = "tooltip"/>
					</s:if>
				</div>
				<div class="col-lg-2 col-md-2">	
					<label>Contact Name/GP Name</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">
		 			<s:textfield id = "name" name = "name" theme="simple"  cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Name" title="Enter Name"/>
				</div>
				
			</div><br><br>
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Direct Telephone Line</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">	
		 			<s:textfield id = "telephoneLine" name = "telephoneLine" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Telephone Line" title="Enter Telephone Line" onchange="checTelePhoneLineValidation(this.value)"/>
		 			<label id = "telephoneLineError" class="text-danger"></label>
		 		</div>
		 		<div class="col-lg-2 col-md-2">	
		 			<label>Email</label>
		 		</div>
		 		<div class="col-lg-4 col-md-4">
		 			<s:textfield id = "email" name = "email" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Email" title="Enter Email" onchange="checkEmailValidation(this.value)"/>
		 			<label id = "emailError" class="text-danger"></label>
		 		</div>
			</div>
			
			</div>
			
				<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Town</label>
				</div>
				<div class="col-lg-4 col-md-4">
		 			<s:textfield id = "gptown" name = "gptown" theme="simple"  cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Town" title="Enter Town"/>
				</div>
				<div class="col-lg-2 col-md-2">	
					<label>GP Address</label>
				</div>
				<div class="col-lg-4 col-md-4">			 	
					<s:textfield id = "gpaddress" name = "gpaddress" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Address" title="Enter Address"/>
				</div>
				</div>
				
		</div>
	</div>	
	
	<div class="row">
		<div class="col-lg-12">
			<h4>Company Details</h4>
		</div>
	</div>
	<div class="panel panel-primary" style="width: 100%;height: 100%;">
		<div class="panel-body">				
		<div class="form-group">
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Company Name</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">		
					<s:textfield id = "companyName" name = "companyName" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Company Name" title="Enter Company Name"/>
				</div>
				<div class="col-lg-2 col-md-2">	
					<label>Address</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">			 	
					<s:textfield id = "address" name = "address" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Address" title="Enter Address"/>
				</div>
			</div><br><br>
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Town</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">		
					<s:textfield id = "town" name = "town" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Town" title="Enter Town"/>
				</div>
				<div class="col-lg-2 col-md-2">	
					<label>Country</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">		
					<s:textfield id = "country" name = "country" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Country" title="Enter Country" value="United Kingdom"/>
				</div>
			</div><br><br>
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Telephone No</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield id = "compnyTelephone" name = "compnyTelephone" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Telephone No." title="Enter Telephone No." onchange="checkComnyTelePhoneLineValidation(this.value)"/>
		 			<label id = "compnyTelephoneError" class="text-danger"></label>
				</div>
				<div class="col-lg-2 col-md-2">	
						<label>Company Email</label>
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield id = "compnyEmail" name = "compnyEmail" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Comapny Email" title="Enter Comapny Email" onchange="checkComapnyEmailValidation(this.value)"/>
		 			<label id = "compnyEmailError" class="text-danger"></label>
				</div>
			</div>
		</div>
	</div>	
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<h4>Account Setting</h4>
		</div>
	</div>
	<div class="panel panel-primary" style="width: 100%;height: 100%;">
		<div class="panel-body">				
		<div class="form-group">
		
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Agreed Credit Limit:</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">	
		 			<s:textfield name = "outInvoiceLimit" id = "outInvoiceLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Outstanding Invoice Limit" title="Enter Outstanding Invoice Limit"/>
				</div>
				<div class="col-lg-2 col-md-2">
					<label>Agreed Credit Duration:</label><label class="text-danger reqd-info">*</label>	
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield name = "creditDuration" id = "creditDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Agreed Duration In Days" title="Enter Agreed Duration In Days"/>
				</div>
			</div><br><br>
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Credit Reminder Limit:</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield name = "accountWarningLimit" id = "accountWarningLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Credit warning limit" title="Enter Credit warning limit"/>
				</div>
				<div class="col-lg-2 col-md-2">
					<label>Credit Reminder Duration:</label><label class="text-danger reqd-info">*</label>	
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield name = "creditReminderDuration" id = "creditReminderDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Credit Reminder Duration In Days" title="Enter Credit Reminder Duration In Days"/>
				</div>
			</div><br><br>
			<div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>DNA Limit in %:</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">	
		 			<s:textfield name = "dnaLimit" id = "dnaLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter DNA Limit" title="Enter DNA Limit"/>
		 		</div>
		 	</div>
		</div>
	</div>
	</div>
		<%--
	<div class="row">
		<div class="col-lg-12">
			<h4>Contact Preferences</h4>
		</div>
	</div>
	<div class="panel panel-primary" style="width: 100%;height: 100%;">
		<div class="panel-body">				
		<div class="form-group">
		<div class="row">
			<div class="col-lg-6 col-md-6" align="center">	
				<label><b>Appointment Booking Confirmation Template</b></label>
			</div>
			<div class="col-lg-6 col-md-6" align="center">	
				<label><b>Appointment DNA Template</b></label>
			</div>
		</div><br>
		 <div class="row">
				<div class="col-lg-2 col-md-2">	
					<label>Contact Email Address</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield id = "confContactEmail" name = "confContactEmail" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Contact Email Address" title="Enter Contact Email Address"/>
				</div>
				
				<div class="col-lg-2 col-md-2">	
					<label>Contact Email Address</label><label class="text-danger reqd-info">*</label>
				</div>
				<div class="col-lg-4 col-md-4">	
					<s:textfield id = "dnaContactEmail" name = "dnaContactEmail" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Contact Email Address" title="Enter Contact Email Address"/>
				</div>
			</div> --%>
		</div>
		</div>
	</div>
	
</div>

<div class="form-group">
	<div class="col-lg-12" align="center">		 	
		<s:submit value="Save" cssClass="btn btn-primary" onclick = "return validateTPDetail()"/>
	</div>
</div>
</s:form>