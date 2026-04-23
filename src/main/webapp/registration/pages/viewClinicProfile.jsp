<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Registration.eu.entity.*"%>
<script type="text/javascript" src="registration/js/clinic.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<!--<link href="registration/css/tabStyle.css" rel="stylesheet" />
--><link rel="stylesheet" href="_assets/newtheme/css/vendor/font-awesome.min.css">
<link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrapfornew.css">

<link href="_assets/newtheme/css/main.css" rel="stylesheet" />

<style>
.tab-content {
	min-height: 500px;
}
	.nav-tabs { border-bottom: 2px solid #DDD;background-color: #f5f5f5; }
	.nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover { border-width: 0; }
	.nav-tabs > li > a { border: none; color: #666; }
	.nav-tabs > li.active > a, .nav-tabs > li > a:hover { border: none; color: #339966 !important; background: transparent; }
	.nav-tabs > li > a::after { content: ""; background: #339966; height: 2px; position: absolute; width: 100%; left: 0px; bottom: -1px; transition: all 250ms ease 0s; transform: scale(0); }
	.nav-tabs > li.active > a::after, .nav-tabs > li:hover > a::after { transform: scale(1); }
	.tab-nav > li > a::after { background: #21527d none repeat scroll 0% 0%; color: #fff; }
	.tab-pane { padding: 15px 0; }
	.tab-content{padding: 0px 10px 0px 10px;}
	
	.card {background: #FFF none repeat scroll 0% 0%; box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.3); margin-bottom: 30px; }
	h4 {
    font-size: 15px;
}
label {
    font-weight: 400;
    color: #000;
}
ul {
    margin-top: 0;
    margin-bottom: 10px;
    padding: 0px;
    list-style: none;
}
.minhewithbor{
	    min-height: 300px;
	    border-right:1px solid #ddd;
}
.headingset{
	display: inline-flex;
    width: 100%;
    background-color: #6699cc;
    padding: 10px 0px 10px 15px;
    margin-bottom: 0px;
    color: #fff;
}
.checkbox-custom-alt input:checked + i {
    background-color: transparent;
    border-color: #fff;
    color: #fff;
}
label {
    font-weight: bold;
    color: #000;
}
.onemail{
	color: #5cb85c;
    text-align: right;
    float: right;
    background-color: #fff;
    padding: 1px 5px 0px 5px;
    font-weight: bold;
}
.offemail{
	color: #d9534f;
    text-align: right;
    float: right;
    background-color: #fff;
    padding: 1px 5px 0px 5px;
    font-weight: bold;
}
</style>
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	
	
	
<div class="col-lg-1 col-md-1"></div>
<div class="col-lg-10 col-md-10 col-xs-12">


<div class="">


<div>
  <p class="headingset">
  	<label class="checkbox checkbox-custom-alt" style="color: #fff;"><input type="radio" name="type" value="Individual" id="type_0" checked="checked"><i></i> HOSPITAL PROFILE</label>&nbsp;&nbsp;&nbsp;&nbsp;
  	<!-- <label class="checkbox checkbox-custom-alt" style="color: #fff;"><input type="radio" name="type" value="Company" id="type_1"><i></i> PHARMACY PROFILE</label> -->
  </p>
  <div id="Individual_box">
    <div class="card">
					<ul class="nav nav-tabs" role="tablist">
                             <li role="presentation" class="active"><a href="#one" aria-controls="one" role="tab" data-toggle="tab">General Information</a></li>
                             <!-- <li role="presentation"><a href="#two" aria-controls="two" role="tab" data-toggle="tab">Package Subscription</a></li>
                             <li role="presentation"><a href="#three" aria-controls="three" role="tab" data-toggle="tab">Functionality Subscription</a></li> -->
                             <li role="presentation"><a href="#four" aria-controls="four" role="tab" data-toggle="tab">Setting</a></li>
                             <li role="presentation"><a href="#six" aria-controls="six" role="tab" data-toggle="tab">Email Setup</a></li>
                             <li role="presentation"><a href="#five" aria-controls="five" role="tab" data-toggle="tab">Account Information</a></li>
                             <li role="presentation"><a href="#" onclick="openPopup('checkEmailSendEmailTemplate')" role="tab" data-toggle="tab">Email Setup</a></li>
                              <%if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2){ %>
                            	<li role="presentation"><a href="#" onclick="openPopup('locationClinicRegistration')" role="tab" data-toggle="tab">Department</a></li>
                               	<li role="presentation"><a href="#" onclick="openPopup('hsaccessClinicRegistration')" role="tab" data-toggle="tab">Access</a></li>
                             <%} %>
                             <li role="presentation" style="margin-top: 8px;float: right; margin-right: 8px;">
                             	<div style="text-align: right;">
                             		
										<s:form action="editClinicRegistration" id="registerfrm" theme="simple">
										<s:hidden name="id" id="id"></s:hidden>
										<a href="smscountdashboardClinicRegistration" target="_blank" style="padding-top: 11px;" class="btn btn-info">SMS COUNT</a>
										<%if(loginInfo.getSuperadminid()==1 || loginInfo.getJobTitle().equals("Admin")  || loginInfo.getClinicid1().equals("manasclinic")){ %>
											<s:submit theme="simple" cssClass="btn btn-warning" value="Edit"  />
										<%} %>
										</s:form>	
									
								</div>
                             </li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                                 <div role="tabpanel" class="tab-pane active" id="one">
                                 	<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                                 		<div class="col-lg-3 col-md-3 col-xs-12">
                                 			<div class="form-group">
												<label>Logo</label>
												<s:if test="userImageFileName!=null">
												<img src="liveData/clinicLogo/<s:property value="userImageFileName"/>" height="100%" width="100%">
												</s:if>
												<s:else>
												<img src="common/images/uplaod_image.png" alt="log" style="width:100%;">
												</s:else>
											</div>		
                                 		</div>
                                 		<div class="col-lg-5 col-md-5 col-xs-12">
                                 			<div class="form-group">
												<label>Business Name</label><br>
												<s:property value="clinicName"/>
											</div>
											<div class="form-group">
												<label>Email-Address </label><br>
												<s:property value="email"/>
											</div>
											<div class="form-group">
												<label>Mobile No</label><br>
												<s:property value="mobileNo"/>
											</div>
											<div class="form-group">
												<label>Landline No</label><br>
												<s:property value="landLine"/>
											</div>
											<div class="form-group">
												<label>WebSite  URL</label><br>
												<s:property value="websiteUrl"/>
											</div>
                                 		</div>
                                 		<div class="col-lg-4 col-md-4 col-xs-12">
											<div class="form-group">
												<label>Subtitle</label><br>
												<s:property value="clinicOwner"/>
											</div>
											<div class="form-group">
												<label>Reg.No</label><br>
												<s:property value="owner_qualification"/>
											</div>
											<div class="form-group">
												<label>Address</label><br>
												<s:property value="address"/>
											</div>
                                 		</div>
                                 	</div>
                                 </div>
                                 <div role="tabpanel" class="tab-pane" id="two">
                                 		<div class="col-lg-12 col-md-12 col-xs-12">
                                 			<div class="plan">
                                 			
										        <h4><span class="red">*</span>Select Package Subscription</h4>
										        <ul>
										            <li>
										            			<s:checkbox name="pkgsubscription" id="standalone"
								fieldValue="standalone" value="%{standalone}" disabled="true" theme="simple" cssClass="chkGrp"/>
								
							<s:label value="StanAlone"></s:label>		
										            </li>
										            <li>
										            		<s:checkbox name="pkgsubscription" id="hostedDB"
								fieldValue="hostedDB" value="%{hostedDB}"
								title="(Only DB is backed up betn Local DB and Server)" disabled="true"  cssClass="chkGrp"/>
								
							<s:label value="Hosted DB"></s:label>		
										           	</li>	
										           	<li>
										           		<s:checkbox name="pkgsubscription" id="onlineSingleDevice"
								fieldValue="onlineSingleDevice" value="%{onlineSingleDevice}"
								title="(Online Single Device (PC))" disabled="true" cssClass="chkGrp"/>
							<s:label value="Online(single device (PC))"></s:label>
										           	</li>
										           	<li>
										           	<s:checkbox name="pkgsubscription" id="onlineMultiDevice"
								fieldValue="onlineMultiDevice" value="%{onlineMultiDevice}"
								title="(Online Multi Device StandAlone, Online, Online Mobile Apps, Responsive Apps" disabled="true"  cssClass="chkGrp"/>
							<s:label value="Online(Multi Device)"></s:label>
										           	</li>
										        </ul>
										    </div>
                                 		</div>
                                 </div>
                                 <div role="tabpanel" class="tab-pane" id="three">
                                 		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;border-bottom: 1px solid #ddd;">
                                 		<div class="col-lg-4 col-md-4 col-xs-12 minhewithbor">
                                 			<div class="plan">
										        <h4>Basic Package</h4>
										        <ul>
										           <li>
            				<s:checkbox name="funSubscription" id="diaryManagement"
								fieldValue="diaryManagement" value="%{diaryManagement}"
								title="Diary Maangement" cssClass="chkGrp2" disabled="true" />
							<s:label value="Diary Management"></s:label>
            </li>
            <li>
            				<s:checkbox name="funSubscription" id="appointmentBooking"
								fieldValue="appointmentBooking" value="%{appointmentBooking}"
								title="Appointment Booking"  cssClass="chkGrp2" disabled="true" />
							<s:label value="Appointment Booking"></s:label>
            </li>
            <li>
            				<s:checkbox name="funSubscription" id="basicFinance"
								fieldValue="basicFinance" value="%{basicFinance}"
								title="Basic Finance"  cssClass="chkGrp2" disabled="true" />
							<s:label value="Basic Finance"></s:label>
            </li>		
										        </ul>
										    </div>
                                 		</div>
                                 		<div class="col-lg-4 col-md-4 col-xs-12 minhewithbor">
                                 			<div class="plan">
										        <h4>Advance Package</h4>
										        <ul>
										           <li>
            				<s:checkbox name="funSubscription" id="fullFinance"
								fieldValue="fullFinance" value="%{fullFinance}"
								title="Full Finance"  cssClass="chkGrp2" disabled="true" />
							<s:label value="Full Finance"></s:label>
            </li>
            <li>
            				<s:checkbox name="funSubscription" id="medicalRecord"
								fieldValue="medicalRecord" value="%{medicalRecord}"
								title="Medical Record"  cssClass="chkGrp2" disabled="true" />
							<s:label value="Medical Record"></s:label>
            </li>		
										        </ul>
										    </div>
                                 		</div>
                                 		<div class="col-lg-4 col-md-4 col-xs-12">
                                 			<div class="plan">
										        <h4>Premier Package</h4>
										        <ul>
										            <li>
            				<s:checkbox name="funSubscription" id="clinicResourceMngment"
								fieldValue="clinicResourceMngment"
								value="%{clinicResourceMngment}"
								title="Clinic Resource Management" cssClass="chkGrp2" disabled="true" />
							<s:label value="Buisness Resource Management"></s:label>
            </li>
            <li>
            				<s:checkbox name="funSubscription" id="clinicPayrollMngment"
								fieldValue="clinicPayrollMngment"
								value="%{clinicPayrollMngment}"
								title="Clinic Payroll Management" cssClass="chkGrp2" disabled="true" />
							<s:label value="Buisness Payroll Management"></s:label>
           	</li>		
										        </ul>
										    </div>
                                 		</div>
                                 			
                                 		</div>
                                 		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                                 		<div class="col-lg-4 col-md-4 col-xs-12 minhewithbor">
                                 			<div class="plan">
										        <h4>Other Package</h4>
										        <ul>
										            <li>
            				<s:checkbox name="funSubscription" id="communication"
								fieldValue="communication" value="%{communication}"
								title="Communication" cssClass="chkGrp2" disabled="true"/>
							<s:label value="Communication"></s:label>
            </li>
            <li>
            				<s:checkbox name="funSubscription" id="report"
								fieldValue="report" value="%{report}"
								title="Report" cssClass="chkGrp2" disabled="true"/>
							<s:label value="Report"></s:label>
            </li>
            <li>
            				<s:checkbox name="funSubscription" id="assessmentForms"
								fieldValue="assessmentForms" value="%{assessmentForms}"
								title="Assessment Forms" cssClass="chkGrp2" disabled="true"/>
							<s:label value="Assessment Forms"></s:label>
            </li>		
										        </ul>
										    </div>
                                 		</div>
                                 		<div class="col-lg-4 col-md-4 col-xs-12">
                                 			<div class="plan">
										        <h4>Excess Devices</h4>
										        <ul>
										            <li>
										            		<s:checkbox name="funSubscription" id="desktop"
								fieldValue="desktop" value="%{desktop}"
								title="Desktop" cssClass="chkGrp2" disabled="true"/>
							<s:label value="Desktop"></s:label>		
										            </li>
										            <li>
										            		<s:checkbox name="funSubscription" id="mobile"
								fieldValue="mobile" value="%{mobile}"
								title="Mobile" cssClass="chkGrp2" disabled="true"/>
							<s:label value="Mobile"></s:label>		
										           	</li>	
										           	<li>
										           		<s:checkbox name="funSubscription" id="iOS"
								fieldValue="iOS" value="%{iOS}"
								title="iOS" cssClass="chkGrp2" disabled="true"/>
							<s:label value="iOS"></s:label>
										           	</li>
										           	<li>
										           	<s:checkbox name="funSubscription" id="tablet"
								fieldValue="tablet" value="%{tablet}"
								title="Tablet" cssClass="chkGrp2" disabled="true"/>
							<s:label value="Tablet"></s:label>
										           	</li>
										        </ul>
										    </div>
                                 		</div>
                                 		<div class="col-lg-4 col-md-4 col-xs-12">
                                 			
                                 		</div>
                                 		</div>
                                 </div>
                                 <div role="tabpanel" class="tab-pane" id="four">
                                 	<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                                 	<div class="col-lg-4 col-md-4 col-xs-4 minhewithbor" style="padding: 0px;">
                                 		<div class="form-group">
											<label>Buisness Start Time</label><br>
											<span><s:property value="sTime"/></span>
										</div>
										<div class="form-group">
											<label>Buisness End Time</label><br>
											<span><s:property value="endTime"/></span>
										</div>
										<div class="form-group">
										<label>Setup Standard Charge </label><br>
										<s:property value="setupstdcharge"/>
									</div>
									<div class="form-group">
										<label>OPD Book withpayment</label><br>
										<s:if test="withpayment==0">NO</s:if><s:else>YES</s:else>
									</div>
									<div class="form-group">
										<label>OPD Book withoutpayment</label><br>
										<s:if test="withoutpayment==0">NO</s:if><s:else>YES</s:else>
									</div>
									<div class="form-group">
										<label>Invoice Date</label><br>
										<s:if test="invoice_date==1">Hide</s:if> <s:else>Show</s:else>
									</div>
									<div class="form-group">
										<label>Discount Show</label><br>
										<s:if test="discount_show==1">Yes</s:if> <s:else>No</s:else>
									</div>
									<div class="form-group">
										<label>Investigation Show</label><br>
										<s:if test="investigation_show==1">Yes</s:if> <s:else>No</s:else>
									</div>
                                 	</div>
                                 	<div class="col-lg-8 col-md-8 col-xs-8">
                                 		
                                 		<div class="form-group scrolltable">
                                 		<label>SMS Setting</label><br>
                                 			<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">OPD</h5>
											SMS to patient on OPD booking <span style="float: right;"><s:if test="smscheck==true"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
											SMS to doctor on OPD booking <span style="float: right;"><s:if test="smscheckdoctor==true"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
											<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">IPD</h5>
											<%-- SMS to relative on new admission <span style="float: right;"><s:if test="==true"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>--%>
											SMS to doctor on new admission <span style="float: right;"><s:if test="sms_on_newadm"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br> 
											<%-- SMS to relative on bed change <span style="float: right;"><s:if test="==true"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br> --%>
											SMS to doctor on bed change <span style="float: right;"><s:if test="sms_on_bedchange"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
											<%-- SMS to relative on discharge <span style="float: right;"><s:if test="==true"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br> --%>
											SMS to Visiting Consultant <span style="float: right;"><s:if test="smsVisitingConslt"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
											<%-- <h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Accounts</h5>
											SMS to patient on payment <span style="float: right;"><s:if test="smsPayment==true"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br> --%>
											<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Investigation</h5>
											<%-- SMS to patient on approved <span style="float: right;"><s:if test="==true"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
											SMS to patient on Completed <span style="float: right;"><s:if test="==true"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br> --%>
                                 	<!--lokesh  -->
                                 			SMS to patient on Birthday <span style="float: right;"><s:if test="bdaysms"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
											SMS to patient on Vaccine <span style="float: right;"><s:if test="immusms"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br><br>
                                 			
                                 		<label>Ward Name Show Setting</label><br>
                                 			<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Add Charge</h5>
                                 		Show Wardname on charges <span style="float: right;"><s:if test="show_wardname"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br><br>
                                 		<label>OPD Setting</label><br>
                                 			<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">OPD Booking</h5>
                                 		Show Invoice Unposted While Book With Payment <span style="float: right;"><s:if test="show_unpost"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
                                 		<label>IPD Id Access Setting</label><br>
                                 			<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">IPD Id Access Setting</h5>
                                 		Show New IPD ID Format <span style="float: right;"><s:if test="show_new_ipd_id"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
                                 		
                                 		<label>Hide LetterHead</label><br>
                                 			<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Hide LetterHead Setting</h5>
                                 		Hide LetterHead in Investigation <span style="float: right;"><s:if test="hidelettinvst"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
							      		Hide LetterHead in EMR <span style="float: right;"><s:if test="hidelettemr"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
							      		Hide LetterHead in Bill/Invoice <span style="float: right;"><s:if test="hidelettbillinv"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
                                 		
                                 		
                                 		<label>Inventory</label><br>
                                 		Generic name and MFG from Master <span style="float: right;"><s:if test="auto_generic_name"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
							      		<label>Save And Print</label><br>
                                 			<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Save And Print Button Setting</h5>
                                 		Save and Print Investigation <span style="float: right;"><s:if test="invest_savenprint"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
							      		Save and Print Prescription <span style="float: right;"><s:if test="prisc_savenprint"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
                                 		
							      		<%-- <label>Prescription</label><br>
                                 		Prescription print on save and print <span style="float: right;"><s:if test="auto_generic_name"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span><br>
							      		  --%>
                                 		</div>
                                 		<br>
                               			<div class="col-lg-4 col-md-4 col-xs-4">
                               				<b>By Default Pharmacy</b>
                               			</div>
                               			<div class="col-lg-4 col-md-4 col-xs-4" style="float: right;">
                               				<%-- <s:select list="locationListPharmacy"  cssStyle="width:100%" name="default_phar_location" headerKey="0" headerValue="Select Location" listKey="id" listValue="name" cssClass="form-control chosen-select"></s:select> --%>
                               				<s:property value="default_phar_location_name"/>
                               			</div>
                               			
                               			<br>
                               			<div class="col-lg-6 col-md-6 col-xs-6">
                               				<b>Direct Prescription to Online Dashbaord</b>
                               			</div>
                               			<div class="col-lg-3 col-md-3 col-xs-3" style="float: right;">
                               				<s:if test="direct_prisc"><span style="color: green;">Enabled</span></s:if><s:else><span style="color: red;">Disabled</span></s:else></span>
                               			</div>
                                 	</div>
                                 	
                                 	
                                 		
										
									
									
									<div class="form-group hidden">
										<a href="roleAccessSettingClinicRegistration">Set Role Access</a>
									</div>
										
                                 	</div>
                                 </div>
                                 <div role="tabpanel" class="tab-pane" id="five">
                                 	<div class="col-clg-12 col-md-12 col-xs-12" style="padding:0px;">
                                 		<div class="col-lg-4 col-md-4 col-xs-12 minhewithbor">
                                 			<div class="form-group">
												<label>User Id </label><br>
												<s:property value="userId"/>
											</div>
                                 		</div>
                                 		<div class="col-lg-4 col-md-4 col-xs-12 minhewithbor">
                                 			<div class="form-group">
												<label>Excess Limit </label><br>
												<s:property value="excess"/>
											</div>
											<div class="form-group">
												<label>IPD Registration Charge</label><br>
												<s:property value="ipdregcharge"/>
											</div>
											<div class="form-group">
												<label>IPD Registration Charge Type </label><br>
												<s:property value="ipdregtype"/>
											</div>
											
											<div class="form-group">
												<label>OPD Follow Up Day</label><br>
												<s:property value="opdfollowup"/>
											</div>
                                 		</div>
                                 		<div class="col-lg-4 col-md-4 col-xs-12">
                                 				<div class="form-group">
													<label>Auto Charge Time </label><br>
													<s:property value="advanceTime"/>
												</div>
												<div class="form-group">
												<label>SMS Count </label><br>
												<s:property value="smscount"/>
											</div>
											<%if(loginInfo.getUserType()==2){ %>
											<div class="form-group">
                                 			<label>Login Expiry Message</label><br>
                                 			<s:property value='loginexmsg'/>
                                 			</div>
                                 			<%} %>
                                 		</div>
                                 	</div>
                                 </div>
                                 
                                 <div role="tabpanel" class="tab-pane" id="six" style="padding: 0px;">
                                 
                                 <div class="row">
                                 	<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: rgba(239, 239, 239, 0.32);padding: 5px 0px 5px 20px;">
                                 		<div class="form-inline">
                                 			<div class="form-group">
                                 				<label>Host Name: <span style="font-weight: normal;"></span></label>
                                 			</div>
                                 		</div>
                                 	</div>
                                 	
                                 	<div class="col-lg-12 col-md-12 col-xs-12">
                                 		<div class="col-lg-3 col-md-3 col-xs-12 minhewithbor">
                                 			<h4 style="background-color: #777777;color: #fff;padding: 5px;">Information Mail <span class="onemail">ON</span></h4>
                                 			<div class="form-group">
							      				<p></p>
							      			</div>
							      			<div class="form-group">
							      				<p>******</p>
							      			</div>
							      			<div class="form-group">
							      				<label>Set Auto Reminder</label>
							      				<p>48 hour</p>
							      			</div>
                                 		</div>
                                 		<div class="col-lg-3 col-md-3 col-xs-12 minhewithbor">
                                 			<h4 style="background-color: #777777;color: #fff;padding: 5px;">Accounting Mail <span class="onemail">ON</span></h4>
                                 			<div class="form-group">
							      				<p></p>
							      			</div>
							      			<div class="form-group">
							      				<p>******</p>
							      			</div>
							      			<div class="form-group">
							      				<label>Set Auto Reminder</label>
							      				<p>24 hour</p>
							      			</div>
                                 		</div>
                                 		<div class="col-lg-3 col-md-3 col-xs-12 minhewithbor">
                                 			<h4 style="background-color: #777777;color: #fff;padding: 5px;">Marketing Mail <span class="offemail">OFF</span></h4>
                                 			<div class="form-group">
							      				<p></p>
							      			</div>
							      			<div class="form-group">
							      				<p>******</p>
							      			</div>
							      			<div class="form-group">
							      				<label>Set Auto Reminder</label>
							      				<p>24 hour</p>
							      			</div>
                                 		</div>
                                 		<div class="col-lg-3 col-md-3 col-xs-12">
                                 			<h4 style="background-color: #777777;color: #fff;padding: 5px;">Procurement Mail <span class="onemail">ON</span></h4>
                                 			<div class="form-group">
							      				<p></p>
							      			</div>
							      			<div class="form-group">
							      				<p>******</p>
							      			</div>
							      			<div class="form-group">
							      				<label>Set Auto Reminder</label>
							      				<p>24 hour</p>
							      			</div>
                                 		</div>
                                 	</div>
                                 
                                 </div>
                                 
                                 
                                 
                                 </div>
                                 
                                 
                        </div>
	</div>
  </div>
  <div id="Company_box">
    
  </div>
</div>
</div>
	
<div class="col-lg-1 col-md-1"></div>
	
	
					<script type="text/javascript" src="common/slimScroll/jquery.slimscroll.min.js"></script>	
					<script>
    	$(function() {
	    $('.scrolltable').slimScroll({
	   		height : '350px',
	   		railVisible: true,
			alwaysVisible: true
	  });
	
	 });
    </script>		
    
    <script>
	$(document).ready(function(){
	  $("input[name$='type']").click(function(){
	  var value = $(this).val();
	  if(value=='Individual') {
	    $("#Individual_box").show();
	     $("#Company_box").hide();
	  }
	  else if(value=='Company') {
	   $("#Company_box").show();
	    $("#Individual_box").hide();
	   }
	  });
	  $("#Individual_box").show();
	  $("#Company_box").hide();
	});
</script>

	
