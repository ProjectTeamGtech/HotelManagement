<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="registration/js/userprofile.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>

<ol class="breadcrumb">
	<li class="active">Setup > View Practitioner > Add New
		User</li>
</ol>

<ul id="docRegTabs" class="nav nav-tabs" role="tablist">
	<li class="active"><a class="tabAnchor" role="tab"
		data-target-div="#userDetails" id="UDTab">User Details</a></li>
	<li class=""><a class="tabAnchor" role="tab"
		data-target-div="#userSetup" id="USTab">User Setup</a></li>
	<li class=""><a class="tabAnchor" role="tab"
		data-target-div="#loginDetails" id="LDTab">Login Details</a></li>
	<li class=""><a class="tabAnchor" role="tab"
		data-target-div="#preferences" id="PFTab">Preferences</a></li>

</ul>


<s:form action="saveUserProfile" id="userprofile_form" theme="simple"
	validate="True">
	<div id="docRegTabsContent" class="tab-content">

		<div class="tab-pane fade active in" id="userDetails"
			data-validation-function="validateUD" data-tab-id="#UDTab">
			<div class="panel panel-primary">
				<div class="panel-body">
					`
					<div class="row">
						<div class="col-lg-12">
							<h4>User Details</h4>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">


							<div class="form-group">
								<label>Initial:</label><label class="text-danger reqd-info">*</label>
								<s:select id="initial" name="initial" list="initialList"
									headerKey="" headerValue="Select Title" title="Select Initial"
									cssClass="form-control showToolTip" data-toggle="tooltip" />
							</div>

							<div class="form-group">
								<label>First Name:</label><label class="text-danger reqd-info">*</label>
								<s:textfield name="firstname" id="firstname"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter First Name" title="Enter First name"></s:textfield>
							</div>



							<div class="form-group">
								<label>Last Name:</label><label class="text-danger reqd-info">*</label>
								<s:textfield name="lastname" id="lastname"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Last Name" title="Enter Last Name"></s:textfield>
							</div>
							


							<div class="form-group">
								<label>Job Title:</label><label class="text-danger reqd-info">*</label>


								<s:select id="jobtitle" name="jobtitle" list="jobTitleList"
									headerKey="0" headerValue="Select Job Title"
									title="Select Job title" onchange="showOtherJobTitle()"
									cssClass="form-control showToolTip" data-toggle="tooltip" />


								<div id="jobTitle_other" style="display: none;">
									<s:textfield id="otherJobTitle" name="otherJobTitle"
										onchange="addOtherJobTitle(this.value)"
										cssClass="text ui-widget-content ui-corner-all" />
								</div>
							</div>

							<div class="form-group">
								<label>Discipline</label>

								<s:select name="diciplineName" list="disciplineList"
									id="id" value="discipline" cssClass="form-control showToolTip"
									data-toggle="tooltip">

								</s:select>
							</div>


							<div class="from-group">
								<a class="btn btn-primary next" data-target-div="#userSetup"
									data-target-tab="#USTab"><i class="fa fa-share"></i> Next</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="tab-pane fade" id="userSetup"
			data-validation-function="validateUS" data-tab-id="#USTab">
			<div class="panel panel-primary">
				<div class="panel-body">
					`
					<div class="row">
						<div class="col-lg-12">
							<h4>User Setup</h4>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">



							<div class="form-group">
								<label>Diary Color</label>


								<s:textfield name="diarycolor" id="diarycolor"
									cssClass="color form-control showToolTip" value="66ff00"
									data-toggle="tooltip" placeholder="Enter Dairy Color"
									title="Enter  Dairy Color"></s:textfield>
							</div>


							<div class="form-group">
								<label>Diary Column Position </label> (Next Release)

								<s:textfield name="diarycolumnposition" id="diarycolumnposition"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Dairy Position"
									title="Enter  Dairy Position"></s:textfield>
							</div>
							<div class="form-group">
								<label>Compression Rate %</label>(Next Release)


								<s:textfield name="compressionrate" id="compressionrate"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Compression Rate"
									title="Enter  Compression Rate"></s:textfield>
							</div>

							<div class="form-group">
								<label>Name to display for online Booking</label>(Next Release)

								<s:textfield name="onlinename" id="onlinename"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Name to display for online Booking"
									title="Name to display for online Booking"></s:textfield>
							</div>
							<div class="form-group">
								<label>This User</label>


								<s:checkbox id="isavailableonline" name="isavailableonline">Is Available for online Booking?</s:checkbox>
							</div>
							<div class="form-group">



								<a class="previous btn btn-primary"
									data-target-div="#userDetails" data-target-tab="#UDTab"><i
									class="fa fa-reply"></i> Previous</a>
								<a class="next btn btn-primary" data-target-div="#loginDetails"
									data-target-tab="#LDTab"><i class="fa fa-share"></i> Next</a>
							</div>




						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="tab-pane fade" id="loginDetails"
			data-validation-function="validateLD" data-tab-id="#LDTab">
			<div class="panel panel-primary">
				<div class="panel-body">
					`
					<div class="row">
						<div class="col-lg-12">
							<h4>Login Details</h4>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">





							<div class="form-group">
								<label>UserId</label><label class="text-danger reqd-info">*</label>


								<s:textfield name="userId" id="userId"
									onclick="checkUserIdExist(this.value)"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter User Id" title="Enter  USer Id"></s:textfield>
								<label id="errorUserId" style="color: red"></label>
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
									title="Re-enter above password"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Confirmed Password" />
							</div>

							<div class="form-group">


								<a class="previous btn btn-primary" data-target-div="#userSetup"
									data-target-tab="#USTab"><i class="fa fa-reply"></i>
									Previous</a> <a class="next btn btn-primary"
									data-target-div="#preferences" data-target-tab="#PFTab"><i
									class="fa fa-share"></i> Next</a>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="tab-pane fade" id="preferences"
			data-validation-function="validatePF" data-tab-id="#PFTab">
			<div class="panel panel-primary">
				<div class="panel-body">
					`
					<div class="row">
						<div class="col-lg-12">
							<h4>Login Details</h4>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">






							<div class="form-group">
								<label>Appointment Booking Confirmation Template</label>




								<s:select name="appointmentbookingcontem" list="abctList"
									id="appointmentbookingcontem"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									title="Select Appointment Booking Confirmation Template">

								</s:select>
							</div>
							<div class="form-group">
								<label>Appointment DNA Template</label>




								<s:select name="appointmentbookingdnatem" list="adtList"
									id="appointmentbookingdnatem"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									title="Select Appointment DNA Template">

								</s:select>
							</div>

							<div class="form-group">
								<label>Contact E-mail Address</label><label
									class="text-danger reqd-info">*</label>


								<s:textfield id="email" name="email" key="label.email"
									labelposition="left" required="true" size="50"
									title="Enter valid email id, it will be used to send job notifications, eg. yourname@gmail.com, yourname@yahoo.co.in"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Contact E-mail Address" />

							</div>

							<div class="form-group">
								<label>Mobile No (for SMS Confirmation)</label><label
									class="text-danger reqd-info">*</label>


								<s:textfield id="mobile" name="mobile" key="label.mobileNo"
									labelposition="left" required="true"
									title="Enter only 10 digit mobile number, eg 9876543210"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter Mobile No (for SMS Confirmation)" />
							</div>
							<div class="form-group">
								<label>Notify of new appointments booked via</label>


								<s:checkbox id="apmremote" name="apmremote"></s:checkbox>
								APM Remote



								<s:checkbox id="onlinebooking" name="onlinebooking"></s:checkbox>
								Online Booking
							</div>

							<div class="form-group">

								<a class="previous btn btn-primary"
									data-target-div="#loginDetails" data-target-tab="#LDTab"><i
									class="fa fa-reply"></i> Previous</a>
								<s:submit onclick="return validateLD()"
									cssClass="btn btn-primary" value="Save" />
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

<s:token></s:token>

	</div>
</s:form>








<a href="back1UserProfile" class="btn btn-primary">Back To List Page</a>
