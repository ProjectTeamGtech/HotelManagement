<%@taglib uri="/struts-tags" prefix="s"%>



<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>

<%
	LoginInfo loginfonew = LoginHelper.getLoginInfo(request);
%>

<script type="text/javascript" charset="utf-8">
	//Capitalize first letter while typing in side of input field
	jQuery(document).ready(
			function($) {
				$('#selector').keyup(
						function(event) {
							var textBox = event.target;
							var start = textBox.selectionStart;
							var end = textBox.selectionEnd;
							textBox.value = textBox.value.charAt(0)
									.toUpperCase()
									+ textBox.value.slice(1);
							textBox.setSelectionRange(start, end);
						});

				var todayDate = new Date().getDate();

				$("#dob").datepicker({

					dateFormat : 'dd/mm/yy',
					yearRange : yearrange,
					minDate : '30/12/1880',
					changeMonth : true,
					changeYear : true,
					maxDate : new Date(new Date().setDate(todayDate))

				});

			});

	function allnumeric(dd) {
		var numbers = /^[0-9]+$/;
		if (!dd.match(numbers)) {
			alert('only number permitted...');
		} else {
			var today = new Date();
			var yyyy = today.getFullYear();
			var year = parseInt(yyyy) - parseInt(dd);
			var dd = today.getDate();
			var mm = today.getMonth() + 1; //January is 0!

			if (dd < 10) {
				dd = '0' + dd
			}

			if (mm < 10) {
				mm = '0' + mm
			}

			var str = dd + '/' + mm + '/' + year;
			document.getElementById("dob").value = str;
			document.getElementById("days").value = 0;
		}

	}
</script>


<script type="text/javascript" src="inventory/js/addvendor.js"></script>
<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>

<style>
.tab-content {
	min-height: 445px !important;
}

.chosen-container-single .chosen-single {
	height: 24px;
	line-height: 12px;
	padding: 6px 6px;
	border-top-left-radius: 0px;
	border-top-right-radius: 0px;
	border-bottom-left-radius: 0px;
	border-bottom-right-radius: 0px;
	background-color: transparent;
	-webkit-box-shadow: none;
	box-shadow: none;
	border: 1px solid rgba(0, 0, 0, 0.1);
	border-top: none;
	border-left: none;
	border-right: none;
}
</style>

<style>
.tab-wizard .nav-tabs>li>a:before {
	top: 0px !important;
	right: -18px !important;
	z-index: 6 !important;
}

.tab-wizard .nav-tabs>li:before {
	background-color: rgba(22, 160, 133, 0.84);
}

.panel {
	margin-bottom: 0px;
}

.col-md-offset-3 {
	margin-left: 2%;
}

.martop10 {
	margin-top: 10px;
}

.tab-wizard .tab-content .pager.wizard {
	margin: 17px 0 !important;
}

ul, ol {
	list-style: none;
	padding: 0px;
}

.headset {
	font-size: 14px;
	color: brown;
	font-weight: bold;
	text-transform: uppercase;
}

label {
	font-weight: normal;
}

.checkbox-custom-alt input:checked+i:before {
	background-color: aquamarine;
}

.checkbox-custom, .checkbox-custom-alt {
	padding-left: 27px;
}

.checkbox-custom-alt>i {
	margin-top: -2px;
	margin-right: 4px;
	margin-left: -26px;
}

.micimg {
	float: left;
	width: 7%;
}

.tab-wizard .tab-content .tab-pane {
	margin-top: 0px;
}

.padding-zero{
	padding: 0;
}

@media (min-width:961px) {
	.left-padding{
		padding-left: 0 !important;
	}   
	.topp-margin{
		margin-top: 10px !important;
	}
	.padding-bottomm{
		padding-bottom: 10px !important;
	}
	.full-5-padding{
		padding: 5px;
	}
	.margin-button-top{
		 margin-top: 22px;
	}
}
@media (max-device-width: 480px) {
	.padding-zero-mobile{	
		padding: 0;
	}
	.mobile-bottom-padding{
		padding-bottom: 12px;
	}
}
</style>
<%String hiddiv="";
 String hideaniversary="";
%>
<%if(loginfonew.isSimpliclinic()){ %>
<%hiddiv="hidden"; %>
<%} %>
<%if(!loginfonew.isSimpliclinic()) {%>
<% hideaniversary="hidden";%>
<%} %>
<div class="row hidden" id="breadcrumbid"></div>
<s:form action="saveNewPatientClient" theme="simple">



	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<!-- page content -->
		<div class="">

			<div id="rootwizard" class="tab-container tab-wizard">
				<ul class="nav nav-tabs nav-justified" style="display: contents;">
					<li><a href="#patientregistertab1" data-toggle="tab"><%=loginfonew.getPatientname_field()%>
							Details<span class="badge badge-default pull-right wizard-step">1</span></a></li>
					<li><a href="#patientregistertab2" data-toggle="tab">Reference
							/ Payment Details<span
							class="badge badge-default pull-right wizard-step">2</span>
					</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="patientregistertab1">
						<div name="step1" role="form">
							<div class="row <%=hiddiv %>"
								style="background-color: rgba(239, 239, 239, 0.19); padding: 8px 0px 0px 0px;">
								<div class="col-lg-3 col-md-3 col-xs-8 col-sm-8">
									<label style="color: orange;">Aadhar Number</label> <input
										type="text" id="adhno" name="adhno" class="form-control"
										placeholder="Enter <%=loginfonew.getPatientname_field()%> Aadhar No"
										style="border-color: chocolate;" maxlength="12"> <label
										id="adhnoError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-4 col-sm-4">
									<a href="#" class="btn btn-default"
										style="margin-top: 22px; border-color: chocolate;"><i
										class="fa fa-search" aria-hidden="true"></i></a>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12"></div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12"></div>
							</div>
					    	<div class="row topp-margin">
								<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12 mobile-bottom-padding">
									<label>Initial</label><label><span
										class="text-danger">*</span></label>
										<%if(loginfonew.isSjivh()){ %>
										<s:select id="title" name="title"
									onchange="" list="initialList"
									title="Select" theme="simple" cssClass="form-control"
									headerValue="Select" headerKey="0" />
										
										<%}else{ %>
									<s:select id="title" name="title"
									onchange="setGender(this.value)" list="initialList"
									title="Select" theme="simple" cssClass="form-control"
									headerValue="Select" headerKey="0" />
									<%} %>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 left-padding" >
									<label>First Name</label><label><span
										class="text-danger">*</span></label>
											<s:textfield id="firstName" name="firstName"
												title="Enter First Name" theme="simple"
												cssClass="form-control showToolTip" list="pdl_firstname"
												onkeyup="getpatientfordatalist(this.value,'pdl_firstname','firstname')"
												placeholder="Enter First Name"
												onblur="initialFirstCap(this);"
												autocomplete="off" />
											<datalist id="pdl_firstname">
											</datalist>
										<label id="fnameError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 mobile-bottom-padding">
									<label>Middle Name</label>
									<s:textfield id="middleName" name="middleName"
										title="Enter Middle Name" theme="simple"
										cssClass="form-control showToolTip" list="pdl_middlename"
										onkeyup="getpatientfordatalist(this.value,'pdl_middlename','middlename')"
										placeholder="Enter Middle Name"
										onblur="initialFirstCap(this);" autocomplete="off" />
									<datalist id="pdl_middlename">
									</datalist>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Last Name</label><label><span
										class="text-danger">*</span></label>
									<s:textfield id="lastName" name="lastName"
										title="Enter Last Name" theme="simple"
										cssClass="form-control showToolTip" list="pdl_lastName"
										onkeyup="getpatientfordatalist(this.value,'pdl_lastName','surname')"
										placeholder="Enter Last Name" onblur="initialFirstCap(this);"
										autocomplete="off" />
									<datalist id="pdl_lastName">
									</datalist>
									<label id="lnameError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Date Of Birth</label><label><span
										class="text-danger"></span></label>
									<s:textfield id="dob" name="dob" readonly="true"
										title="Select Date Of Bith"
										onchange="getAgendDays(this.value)" theme="simple"
										cssClass="form-control showToolTip"
										placeholder="Select Date of Birth" autocomplete="off" />
									<label id="lnameError" class="text-danger"></label>
								</div>
							</div>

                           
							<div class="row padding-bottomm <%=hiddiv%>">
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 mobile-bottom-padding">
								   <%if(loginfonew.isSjivh()){ %>
									<label>Pet Name</label>
									<s:textfield name="fathername" id='fathername'
										title="Add Pet Name" theme="simple"
										cssClass="form-control showToolTip" list="pdl_fathername"
										onkeyup="getpatientfordatalist(this.value,'pdl_fathername','fathername')"
										placeholder="Add Pet Name" />
									<datalist id="pdl_fathername">
									</datalist>
									<%}else{ %>
									 <label>Father Name</label>
									
									<s:textfield name="fathername" id='fathername'
										title="Add Father Name" theme="simple"
										cssClass="form-control showToolTip" list="pdl_fathername"
										onkeyup="getpatientfordatalist(this.value,'pdl_fathername','fathername')"
										placeholder="Add Father Name" />
									<datalist id="pdl_fathername">
									</datalist>
									<%} %>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 mobile-bottom-padding">
									<label>Mother Name</label>
									<s:textfield name="mothername" id='mothername'
										title="Add Mother Name" theme="simple"
										cssClass="form-control showToolTip" list="pdl_mothername"
										onkeyup="getpatientfordatalist(this.value,'pdl_mothername','mothername')"
										placeholder="Add Mother Name" />
									<datalist id="pdl_mothername">
									</datalist>
								</div>
								<%-- <div class="col-lg-3 col-md-3 col-xs-3">
								           <label>Birth place </label>
								          			<s:textfield  name="birthplace" id='birthplace' title="Add Father Name"  theme="simple" cssClass="form-control showToolTip"
														placeholder="Add Birth Place "  autocomplete="off"/>
								           </div> --%>
								<s:if test="birthPlaceList==null">
									<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 mobile-bottom-padding">
										<label>Birth place </label>
										<s:textfield name="birthplace" id='birthplace'
											title="Add Father Name" theme="simple"
											cssClass="form-control showToolTip"
											placeholder="Add Birth Place " autocomplete="off" />
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12" id="">
										<label for="exampleInputName2">Birth Time(HH:MM)</label>
										<div class="form-inline hhmm">
											<div class="form-group" style="margin-top: 0;">
												<s:select name="hourls" id="hourls" list="hourList"
													cssClass="form-control" headerKey="00" headerValue="00" />
											</div>
											<div class="form-group hidden-xs" style="margin-top: 0;">
												:</div>
											<div class="form-group" style="margin-top: 0;">
												<s:select name="minutels" id="minutels" list="minuteList"
													cssClass="form-control mmwidthmang" headerKey="00"
													headerValue="00" />

											</div>
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
										<label>Birth Place</label>
										<%--  <s:textfield  name="birthplace" title="Birth Place"  theme="simple" cssClass="form-control showToolTip"
												 data-toggle="tooltip" placeholder="Enter Birth Place"/>  --%>

										<span id='bpldiv'> <s:select list="birthPlaceList"
												headerKey="" headerValue="Select birth place" listKey="name"
												id="birthplace" listValue="name" name="birthplace"
												theme="simple" cssClass="form-control chosen-select"></s:select>
										</span>
									</div>


									<div class="col-lg-1 col-md-1 col-xs-12 col-sm-12" id="">
										<br> <input type="button" value="Add" class='btn'
											onclick='addbirthplacePopup()'>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12"  id="">
										<label for="exampleInputName2">Birth Time(HH:MM)</label>
										<div class="form-inline hhmm">
											<div class="form-group" style="margin-top: 0;">
												<s:select name="hourls" id="hourls" list="hourList"
													cssClass="form-control" headerKey="00" headerValue="00" />
											</div>
											<div class="form-group hidden-xs" style="margin-top: 0;">
												:</div>
											<div class="form-group" style="margin-top: 0;">
												<s:select name="minutels" id="minutels" list="minuteList"
													cssClass="form-control mmwidthmang" headerKey="00"
													headerValue="00" />

											</div>
										</div>
									</div>
								</s:else>

							</div>
							
							<div class="row">
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12 full-5-padding padding-zero-mobile">
										<label>Age/Year</label><label><span class="text-danger">*</span></label>
										<s:textfield id="age" name="age"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												title="Enter Age" theme="simple"
												onchange="allnumeric(this.value)" placeholder="Age"
												autocomplete="off"></s:textfield>
										<label id="ageError" class="text-danger"></label>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12 full-5-padding padding-zero-mobile mobile-bottom-padding">
										<label>Month</label>
										<input type="number" placeholder="Month"
												id="month" class="form-control"
												onchange="agecalculate()" autocomplete="off" />
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12 full-5-padding padding-zero-mobile mobile-bottom-padding">
										<label>Days</label>
										<input type="number" placeholder="days"
												id="days" class="form-control" 
												autocomplete="off" />
									</div>
									
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12 full-5-padding padding-zero-mobile">
										<label>Gender</label> <label><span class="text-danger">*</span></label>
										<s:select id="gender" name="gender"
											list="{'Male','Female','Other'}" headerKey="0"
											headerValue="Select" theme="simple"
											cssClass="form-control showToolTip" />
										<label id="genderError" class="text-danger"></label>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12 full-5-padding padding-zero-mobile mobile-bottom-padding">
										<label>Marital Status</label>
										<s:select id="maritalsts" name="maritalsts"
											list="{'Single','Married','Divorced','Separated'}"
											headerKey="0" headerValue="Select" theme="simple"
											cssClass="form-control showToolTip" />
									</div>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 <%=hideaniversary %>" style="display: none;" id="anniversary">
										<label>Anniversary Date</label>		
									<s:textfield id = "dob1" name="dob1" cssClass="form-control showToolTip" readonly = "true"
									data-toggle="tooltip" title="Select Anniversary" theme="simple" placeholder = "Select Anniversary"></s:textfield>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 hidden">
										<!--<label>DOB</label><label><span class="text-danger">*</span></label>			
									<s:textfield id = "dob" name="dob" cssClass="form-control showToolTip" readonly = "true"
									data-toggle="tooltip" title="Select DOB" theme="simple" placeholder = "Select DOB"></s:textfield>
									
					          	-->
										<label id="dobError" class="text-danger"></label>
										<s:hidden id="dob" name="dob"></s:hidden>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 mobile-bottom-padding <%=hiddiv%>">
									<label>Relative Name</label>
									<s:textfield id="relative_name" name="relativename"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										title="Enter Relative Name" theme="simple"
										placeholder="Enter Relative Name"></s:textfield>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 mobile-bottom-padding <%=hiddiv%>">
									<label>Relative Contact No</label>
									<s:textfield id="relativeno" name="relativeno"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										title="Enter Relative Contact No" theme="simple"
										placeholder="Enter Relative Contact No"></s:textfield>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
									<label>Address</label> <label><span class="text-danger">*</span></label>
									<s:textfield id="address" name="" title="Enter Address"
										theme="simple" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter Address"
										onkeyup="allFirstInitCap(this.id);" />
									<label id="addressError" class="text-danger" autocomplete="off"></label>
									<br>
									<s:textfield cssStyle="display:none;" id="secondLineaddress"
										name="secondLineaddress" title="Enter 2nd Line Address"
										onkeyup="allFirstInitCap(this.id);"
										cssClass="form-control showToolTip ppstyle"
										data-toggle="tooltip" placeholder="Enter 2nd Line Address" />
								</div>

								<div class="col-lg-3 col-xs-12 col-sm-12" id="citydiv">
									<label>City</label><span class="text-danger">*</span>
									<!--<s:textfield id="town" name="town" title="Enter Town" theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip"  placeholder = "Enter Town" onblur="initialFirstCap(this);"/>
				-->
									<s:select list="citylist" theme="simple" listKey="city"
										listValue="city" id="town"
										onchange="getStateAjaxnew(this.value)"
										cssClass="form-control showToolTip chosen-select"
										headerKey="0" headerValue="Select City" name="town" />

									<label id="townError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12" id="statediv">
									<label>State</label><span class="text-danger">*</span>
									<!--<s:textfield id="state" name="state" title="Enter State" theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip"  placeholder = "Enter State" onblur="initialFirstCap(this);"/>
				-->
									<s:select list="statelist" theme="simple"
										cssClass="form-control showToolTip chosen-select"
										onchange="getCitiesajax(this.value)" name="state" id="state"
										listKey="name" listValue="name" headerKey="0"
										headerValue="Select State" />
									<label id="stateError" class="text-danger"></label>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Post Code</label>
									<s:textfield id="postCode" name="postCode" theme="simple"
										title="Enter PostCode" cssClass="form-control showToolTip"
										list="pdl_postCode"
										onkeyup="getpatientfordatalist(this.value,'pdl_postCode','postcode')"
										data-toggle="tooltip" placeholder="Enter PostCode"
										onblur="initialCap(this);" />
									<datalist id="pdl_postCode">
									</datalist>
									<label id="postCodeError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Country</label><label><span class="text-danger">*</span></label>
									<s:if test="%{#countryList != 'null'}">
										<s:select id="country" name="country" list="countryList"
											headerKey="0" headerValue="Select Country"
											labelposition="left" title="Select your country from list"
											theme="simple"
											cssClass="form-control showToolTip chosen-select"
											data-toggle="tooltip" />
									</s:if>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12"></div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12"></div>
							</div>
							<div class="row">
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Mobile No</label>
									<s:textfield id="mobNo" name="mobNo" title="Enter Mobile No"
										theme="simple" cssClass="form-control showToolTip"
										list="pdl_mobNo"
										onkeyup="getpatientfordatalist(this.value,'pdl_mobNo','mobno')"
										data-toggle="tooltip" placeholder="Enter Mobile No"
										onchange="checkMobileValidation(this.value)" maxlength="10"/>
									<datalist id="pdl_mobNo">
									</datalist>
									<label id="mobNoError1" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Email</label>
									<s:textfield id="email" name="email" title="Enter Email"
										theme="simple" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter Email"
										onchange="checkEmailValidation(this.value)" />
									<label id="emailError1" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<p>.</p>
									<label><input type="checkbox" name="hospitalborn"
										id="hospitalborn" value="1"> Hospital Born</label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12"></div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="patientregistertab2">
						<div name="step2" role="form">
							<div class="row">
								<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12">
									<h5 style="color: brown; border-bottom: 1px solid #ddd;">Reference</h5>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Referred By</label>

									<s:select id="reference" name="reference" list="refrenceList"
										listValue="reference" listKey="id" headerKey="0"
										headerValue="Select Reference"
										title="Select your Reference from list"
										cssClass="form-control showToolTip chosen"
										data-toggle="tooltip" onchange="showOtherRefernce()" />
									<label id="refError" class="text-danger"></label>

									<div id="reference_other" style="display: none;">
										<s:textfield id="otherRef" name="otherRef" type="text"
											onblur="addOtherReference(this.value)"
											onchange="initialCap(this)"
											cssClass="form-control showToolTip" data-toggle="tooltip"
											placeholder="Enter New Reference" />
									</div>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Condition</label>
									<s:select id="treatmentType" name="treatmentType"
										list="diagnosisList" headerValue="Select Condition"
										headerKey="0" listKey="id" listValue="treatmentType"
										cssClass="form-control showToolTip chosen"
										data-toggle="tooltip" theme="simple"
										onchange="showOtherCondition()"></s:select>
									<label id="conError" class="text-danger"></label>
									<div id="condition_other" style="display: none;">

										<s:textfield id="otherCondition" name="otherCondition"
											onblur="addOtherCondition(this.value)"
											onchange="initialCap(this)"
											cssClass="form-control showToolTip" data-toggle="tooltip"
											placeholder="Enter New Condition" />
									</div>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Occupation</label>
									<s:select id="occupation" name="occupation"
										listValue="occupation" listKey="id"
										list="clientOccupationList" headerKey="0"
										headerValue="Select Occupation"
										title="Select your Occupation from list"
										cssClass="form-control showToolTip ddlAddNew"
										data-toggle="tooltip" />
									<label id="occError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12"></div>
							</div>
							<div class="row">
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Select Hospital</label>
									<s:select id="doctorsurgery" name="doctorsurgery"
										list="surgeryList" listKey="id" listValue="gptypeName"
										headerValue="Select Hospital Name" headerKey="0"
										cssClass="form-control showToolTip chosen"
										data-toggle="tooltip" onchange="setGPDataList(this.value)" />
									<label id="surError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 mobile-bottom-padding">
									<a href="#" onclick="addNewDoctorSurgery()"
										class="btn btn-primary margin-button-top" style="width: 100%;">
										<i class="fa fa-plus"></i> New Hospital
									</a>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12" id="ajaxgp">
									<label>GP/Consultant Name</label> <select id="gpname"
										name="gpname" class="form-control showToolTip chosen"
										data-toggle="tooltip">
										<option value="0">Select GP/Consultant</option>
									</select> <label id="gpnameError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<a href="#" onclick="addNewGpDataPopup()"
										class="btn btn-primary margin-button-top" style="width: 100%;">
										<i class="fa fa-plus"></i> New Consultant
									</a>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12 col-xs-12 col-sm-12 col-md-12">
									<h5 style="color: brown; border-bottom: 1px solid #ddd;">Payment
										Details</h5>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Pay By</label><label><span class="text-danger">*</span></label>
									<s:select id="whopay" name="whopay"
										list="#{'Client':'Self','Third Party':'Third Party'}"
										headerKey="0" onchange="showHide()"
										cssClass="form-control showToolTip chosen"
										data-toggle="tooltip" theme="simple"></s:select>
									<label id="wwpError" class="text-danger"></label>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12"></div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12"></div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12"></div>

							</div>
							<div class="row">
								<div id="hidden_html" style="display: none;">

									<div class="class=" col-lg-12 col-md-12
										col-xs-12" style="padding: 0px;"">
										<div class="col-lg-3 col-md-3">
											<label>Type</label>
											<s:select id="type" name="type" list="thirdPartyTypeList"
												listKey="id" listValue="type" headerKey="0"
												cssClass="form-control showToolTip chosen"
												data-toggle="tooltip" title="Select Third Party Type"
												headerValue="Select Type" onchange="setTPName(this.value)"
												theme="simple" />
											<label id="tpError" class="text-danger"></label>
										</div>

										<div class="col-lg-3 col-md-3">
											<label>Third Party Name</label> <select id="typeName"
												name="typeName" class="form-control showToolTip chosen"
												data-toggle="tooltip" theme="simple">
												<option value="0">Select Third Party</option>
											</select> <label id="tpnameError" class="text-danger"></label>
										</div>


										<div class="col-lg-3 col-md-3">
											<br> <input type="button" onclick="setTypeFieldofTp()"
												class="btn btn-primary" value="Add Third Party">
										</div>
										<div class="col-lg-3 col-md-3"></div>
									</div>

									<div class="col-lg-12 col-md-12 col-xs-12"
										style="padding: 0px;">
										<div class="col-lg-3 col-md-3">
											<label>Policy No.</label>
											<s:textfield name="policyNo" id="policyNo"
												cssClass="form-control showToolTip" disabled="true"
												data-toggle="tooltip" placeholder="Enter Policy No."
												title="Enter Policy No." theme="simple" />
										</div>
										<div class="col-lg-3 col-md-3">
											<label>Policy Expiry Date</label>
											<s:textfield name="expiryDate" id="expiryDate"
												cssClass="form-control showToolTip" disabled="true"
												data-toggle="tooltip" placeholder="Enter Expiry Date."
												theme="simple" readonly="true" />
										</div>
										<div class="col-lg-3 col-md-3">
											<label>Policy Excess</label>
											<s:textfield name="policyExcess" id="policyExcess"
												disabled="true" cssClass="form-control showToolTip"
												data-toggle="tooltip" placeholder="Enter Value" />
										</div>


										<div class="col-lg-3 col-md-3"></div>

									</div>
								</div>

								<div id="ipdtpadd" class="hidden">

									<div class="col-lg-12 col-md-12">
										<div class="col-lg-3 col-md-3" id="policyholderdiv"
											style="margin-left: -14px; padding-top: 17px">
											<label>Policy Holder</label>
											<s:textfield name="policyholder" id="policyholder"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter policyholder name" required="true" />
										</div>
										<div class="col-lg-3 col-md-3 " id="compnamediv"
											style="margin-left: -15px;">
											<br> <label>Employee Name</label>
											<s:textfield name="compname" id="compname"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter Employee Name" size="178px" />
										</div>
										<div class="col-lg-3 col-md-3" id="neisnodiv"
											style="margin-left: 10px;">
											<br> <label>NEIS/Card No</label>
											<s:textfield name="neisno" id="neisno"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter NEIS/Card No" size="178px" />
										</div>
										<div class="col-lg-3 col-md-3" id="designationbytpdiv"
											style="margin-left: 5px;">
											<br> <label>Designation</label>
											<s:textfield name="designationbytp" id="designationbytp"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter Designation" size="178px" />
										</div>
										<div class="col-lg-3 col-md-3" id="relationvbytpdiv">
											<br> <label>Relation</label>
											<s:textfield name="relationvbytpe" id="relationvbytpe"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter Relation" size="178px" />
										</div>

									</div>

									<div class="row" style="margin-left: 2px; padding-top: 190px;">

										<div class="col-lg-3 col-md-3" id="claimbytpdiv">
											<label>Claim ID</label>
											<s:textfield name="claimbytp" id="claimbytp"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter Claim ID" size="178px" />
										</div>
										<div class="col-lg-3 col-md-3  %>" id="unitstationdiv">
											<label>Unit/Station</label>
											<s:textfield name="unitstation" id="unitstation"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter unit/station" size="178px" />
										</div>

										<div class="col-lg-3 col-md-3 " id="collierydiv">
											<label>Colliery</label>
											<s:textfield name="colliery" id="colliery"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter colliery" size="178px" />
										</div>

										<div class="col-lg-3 col-md-3 " id="areabytpdiv">
											<label>Area</label>
											<s:textfield name="areabytp" id="areabytp"
												cssClass="form-control showToolTip" data-toggle="tooltip"
												placeholder="Enter colliery" size="178px" />
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /page content -->
	</div>

	<input type="button" value="Save" onclick="return validateAllDetails()"
		class="btn btn-primary hidden" id="savebtn" />

</s:form>

<div id="babyconfirm" class="modal fade lok" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Is Baby Born In This Hospital ?</h4>
			</div>
			<div class="modal-body">

				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-6 col-md-6 col-sm-6" style="text-align: center;">
						<button class="btn btn-primary" onclick="sethospitalborn()">Yes</button>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6"
						onclick="closehospitalborn()" style="text-align: center;">
						<button class="btn btn-danger">No</button>
					</div>
				</div>
				<div class="modal-footer">
					<!--  <input type="button" class="btn btn-danger" onclick="openIpd()" data-dismiss="modal" value="Ok"> -->


				</div>
			</div>

		</div>
	</div>
</div>

<div class="modal fade" id="bpmodal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	style="z-index: 1000003;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Add Birth Place</h4>
			</div>
			<div class="modal-body">

				<input type="text" class='form-control' id='bptext'>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					onclick="saveBirthplace()">Save</button>

				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


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

	
	$(document).ready(function () {
        $('#maritalsts').change(function () {
            if ($('#maritalsts').val() == 'Married') {
                $('#anniversary').show();
            }
            else {
                $('#anniversary').hide();
            }
        });
    });



</script>

