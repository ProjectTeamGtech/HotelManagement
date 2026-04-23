<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Registration.eu.entity.*"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<script type="text/javascript" src="registration/js/clinic.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<!--<link href="registration/css/tabStyle.css" rel="stylesheet" />
--><link rel="stylesheet" href="common/BootstrapNew/bootstrap/css/bootstrapfornew.css">

<link href="_assets/newtheme/css/main.css" rel="stylesheet" />



<style>
.tab-content {
	min-height: 400px;
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
label {
    font-weight: 400;
    color: #000;
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
input[type=file] {
    display: block;
    width: 100%;
}
label {
    font-weight: bold;
    color: #000;
}
.panel-default>.panel-heading {
	    color: #fff;
	    background-color: #777;
	}
</style>
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<div class="col-lg-1 col-md-1"></div>

<div class="col-lg-10 col-md-10 col-xs-12">
<p class="headingset">
  	<label class="checkbox checkbox-custom-alt" style="color: #fff;"><input type="radio" name="type" value="Individual" id="type_0" checked="checked"><i></i> HOSPITAL PROFILE</label>&nbsp;&nbsp;&nbsp;&nbsp;
  	<!-- <label class="checkbox checkbox-custom-alt" style="color: #fff;"><input type="radio" name="type" value="Company" id="type_1"><i></i> PHARMACY PROFILE</label>  -->
  </p>
<s:form action="editClinicRegistration" id="registerfrm" theme="simple">
<s:hidden name="id" id="id"></s:hidden>
<s:submit theme="simple" cssClass="btn btn-primary hidden" value="Edit"  />
</s:form>	

				<div class="card">
						<ul class="nav nav-tabs" role="tablist">
                             <li role="presentation" class="active"><a href="#one" aria-controls="one" role="tab" data-toggle="tab">General Information</a></li>
                             <!-- <li role="presentation hidden"><a href="#two" aria-controls="two" role="tab" data-toggle="tab">Package Subscription</a></li>
                             <li role="presentation hidden"><a href="#three" aria-controls="three" role="tab" data-toggle="tab">Functionality Subscription</a></li> -->
                             <li role="presentation"><a href="#four" aria-controls="four" role="tab" data-toggle="tab">Setting</a></li>
                             <li role="presentation"><a href="#six" aria-controls="six" role="tab" data-toggle="tab">Email Setup</a></li>
                             <li role="presentation"><a href="#five" aria-controls="five" role="tab" data-toggle="tab">Account Information</a></li>
                             
                        </ul>
                        
                         <!-- Tab panes -->
                    <s:form action="updatesaveClinicRegistration" id="registerfrm" theme="simple" method="post" enctype="multipart/form-data">
						<s:hidden name="id" id="id"></s:hidden>     
                         <div class="tab-content">
                                 <div role="tabpanel" class="tab-pane active" id="one">
                                 	<div class="">
				
				<div class="row">
					<div class="col-lg-12">
					
					<div class="col-lg-3 col-md-3 col-xs-12" style="padding: 0px;">
						<s:hidden name = "userImageFileName"></s:hidden>
						
						<s:if test="userImageFileName!=null">
						<div class="form-group">
							<label>Logo</label>
							<img src="liveData/clinicLogo/<s:property value="userImageFileName"/>" style="width: 90%;">
						</div>
						</s:if>
						<div class="form-group">
							<label>Upload Logo</label>
							<s:file name="userImage"/>					
						</div>
					</div>
					<div class="col-lg-5 col-md-5 col-xs-12">
						<div class="form-group">
							<label><span class="red">*</span>Business Name</label>
							<s:textfield name="clinicName" id="clinicName" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Business Name" title="Enter Clinic Name"/>
						</div>
						<div class="form-group">
							<label><span class="red">*</span>Email-Address</label>
							<s:textfield type = "email" id="email" name="email" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Email" title="Enter Email"/>
						</div>
						<div class="form-group">
							<label><span class="red">*</span>Mobile No</label><br>
							<div class="form-inline">
								<div class="form-group" style="width: 42%;margin-top: -20px;">
									<s:select cssClass="form-control showToolTip" name="countrycode" id="countrycode" list="countrycodeList" listKey="countryid" listValue="countryName" headerKey="0" headerValue="Select Code" cssStyle="width:100%;"/>
								</div>
								<div class="form-group" style="width: 56%;">
									<s:textfield type = "number" id="mobileNo" name="mobileNo" onchange="checkMobileNoExist(this.value)" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Mobile Number" title="Enter only 10 digit mobile number, eg 9876543210" maxlength="10" cssStyle="width:100%;"/>
									<label id="errorMobileNo" class="text-danger"></label>
								</div>
							</div>
							
						</div>
						<div class="form-group">
							<label>Landline No</label>
							<s:textfield id="landline" name="landLine" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Landline"  title="Enter Landline"/>
						</div>
						<div class="form-group">
							<label>WebSite URL</label>
							<s:textfield id="websiteUrl" name="websiteUrl" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Website URL"  title="Enter Website URL"/>
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-xs-12">
					<div class="form-group">
							<label><span class="red">*</span>Business Owner Name(SPOC)</label>
							<s:textfield name="clinicOwner" id="clinicOwner" cssClass="form-control" placeholder = "Enter Business Owner Name" title="Enter Clinic Owner Name"/>
						</div>
						<div class="form-group">
							<label>Reg.No</label>
							<s:textfield name="owner_qualification" id="owner_qualification" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Business Owner Qualification" title="Enter Clinic Owner Qualification"/>
						</div>
						
						<div class="form-group">
							<label>Address</label>
							<s:textfield name="address" id="address" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Business Address" title="Enter Bussiness Address"/>
						</div>
					</div>
					
					
					
					</div>
					
				</div>
				<div class="form-group hidden">
							<input type="button" value="Next"  class="btn btn-primary next" data-target-div="#packageSubscription" data-target-tab="#PSTab">
						</div>
			</div>
                                 </div>  
                                 <div role="tabpanel" class="tab-pane" id="two">
                                 	<div class="">
				<div class="row">
				
				
				
					<div class="col-lg-12">
					<div class="plan">
					        <h4><span class="red">*</span>Select Package Subscription</h4>
					        <ul>
					            <li>
					            				<s:checkbox name="pkgsubscription" id="standalone"
								fieldValue="standalone" value="%{standalone}" theme="simple" cssClass="chkGrp" disabled="true"/>
								
							<s:label value="StanAlone"></s:label>
					            </li>
					            <li>
					            				<s:checkbox name="pkgsubscription" id="hostedDB"
								fieldValue="hostedDB" value="%{hostedDB}"
								title="(Only DB is backed up betn Local DB and Server)"  cssClass="chkGrp" disabled="true"/>
								
							<s:label value="Hosted DB"></s:label>
					           	</li>	
					           	<li>
					           		<s:checkbox name="pkgsubscription" id="onlineSingleDevice"
								fieldValue="onlineSingleDevice" value="%{onlineSingleDevice}"
								title="(Online Single Device (PC))"  cssClass="chkGrp" disabled="true"/>
							<s:label value="Online(single device (PC))"></s:label>
					           	</li>
					           	<li>
					           		<s:checkbox name="pkgsubscription" id="onlineMultiDevice"
								fieldValue="onlineMultiDevice" value="%{onlineMultiDevice}"
								title="(Online Multi Device StandAlone, Online, Online Mobile Apps, Responsive Apps"  cssClass="chkGrp" disabled="true"/>
							<s:label value="Online(Multi Device)"></s:label>
					           	</li>
					           		
					        </ul>
					    </div>
						<div class="form-group hidden">
							<input type="button" value="Previous"
								onclick="showGeneralInfoTab()" class="btn btn-primary previous" data-target-div="#generalInformation" data-target-tab="#GITab">
							<input type="button" value="Next" 
								class="btn btn-primary next"  value="Next" data-target-div="#functionalitySubscription" data-target-tab="#FSTab">
						</div>

					</div>
				</div>
			</div>
                                 </div> 
                                 <div role="tabpanel" class="tab-pane" id="three">
                                 	<div class="">
				
				<div class="row">
				<div class="">
				<div id="pricing-table" class="clear">
				<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;border-bottom: 1px solid #ddd;">
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
				
				<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
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
				
				</div>
				
				
				    
    
    
    
</div>
				</div>
				
				
				
				
				
					<div class="col-lg-12 hidden">
						<div class="form-group ">
							<input type="button" value="Previous" class="btn btn-primary previous" data-target-div="#packageSubscription" data-target-tab="#PSTab"> 
							<input type="button" value="Next" class="btn btn-primary next" data-target-div="#settingInformation" data-target-tab="#settingTab">
						</div>

					</div>
				</div>
				
			</div>
                                 </div> 
                                 <div role="tabpanel" class="tab-pane" id="four">
                                 
                                 <div class="row">
                                 	<div class="col-lg-4 col-md-4 col-xs-4 minhewithbor">
                                 		<div class="form-group">
                                 			<label>OPD Start Time</label>
									<s:if test="%{#startTimeList != 'null'}">
										<s:select id="sTime" name="sTime" list="startTimeList"
											cssClass="form-control" theme="simple" />
									</s:if>
                                 		</div>
                                 		<div class="form-group">
                                 			<label>OPD End Time</label>
									<s:if test="%{#endTimeList != 'null'}">
										<s:select id="endTime" name="endTime" list="endTimeList"
											cssClass="form-control" theme="simple" />
									</s:if>
                                 		</div>
                                 		
                                 		<div class="form-group">
                                 			<label>Advance Range</label>
										<s:textfield id="advancerenge" name="advancerenge" 
											cssClass="form-control" theme="simple" />
                                 		</div>
                                 		<div class="form-group">
                                 			<label>Setup Standard Charge</label>
										<s:select list="#{'0':'On Admission Date','1':'On Next Date Of Admission'}" cssClass="form-control" name="setupstdcharge" id="setupstdcharge" ></s:select>
                                 		</div>
                                 		<div class="form-group">
                                 			<label>OPD Payment</label><br>
									<s:if test="withpayment==0">
										<s:checkbox cssClass="" name="withpayment"></s:checkbox>Book With payment<br>
							    	</s:if>
							    	<s:else>
							    		<s:checkbox cssClass="" checked="checked" name="withpayment"></s:checkbox>Book with payment<br>
							    	</s:else>
							    	
							    	<s:if test="withoutpayment==0">
										<s:checkbox cssClass="" name="withoutpayment"></s:checkbox>Book without payment<br>
							    	</s:if>
							    	<s:else>
							    		<s:checkbox cssClass="" checked="checked" name="withoutpayment"></s:checkbox>Book without payment<br>
							    	</s:else>
							    	
							    	
			 						<button type="button" style="width: 100%;" class="btn btn-default btn-sm dropdown-toggle hidden" data-toggle="dropdown">Select Specialization <span class="caret"></span></button>
										<ul class="dropdown-menu hidden">
											<s:iterator value="specilizationList">
												<s:if test="status==1">
													<li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" checked="checked" class="spec" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												</s:if>
												<s:else>
													<li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" class="spec" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												</s:else>
											</s:iterator>
										</ul>
                                 		</div>
                                 		<div class="form-group">
                                 			<label>Invoice Date Hide</label><br>
			 						<s:if test="invoice_date==0">
										<s:checkbox cssClass="" name="invoice_date"></s:checkbox>Show<br>
							    	</s:if>
							    	<s:else>
							    		<s:checkbox cssClass="" checked="checked" name="invoice_date"></s:checkbox>Hide<br>
							    	</s:else>
                                 		</div>
                                 		
                                 		<div class="form-group">
                                 			<label>Show Discount</label><br>
			 						<s:if test="discount_show==0">
										<s:checkbox cssClass="" name="discount_show"></s:checkbox>Yes<br>
							    	</s:if>
							    	<s:else>
							    		<s:checkbox cssClass="" checked="checked" name="discount_show"></s:checkbox>Yes<br>
							    	</s:else>
                                 		</div>
                                 				<div class="form-group">
                                 					<label>Show Investigation</label><br>
			 						<s:if test="investigation_show==0">
										<s:checkbox cssClass="" name="investigation_show"></s:checkbox>Yes<br>
							    	</s:if>
							    	<s:else>
							    		<s:checkbox cssClass="" checked="checked" name="investigation_show"></s:checkbox>Yes<br>
							    	</s:else>
                                 		</div>
                                 		
                                 	
                                 	</div>
                                 	
                                 	
                                 	<div class="col-lg-8 col-md-8 col-xs-8">
                                 		
									<div class="scrolltable">
									<label>SMS Setting</label><br>
										<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">OPD</h5>
										<s:checkbox name="smscheck" id="smscheck"></s:checkbox> SMS to patient on OPD booking<br>
									    <s:checkbox name="smscheckdoctor" id="smscheckdoctor"></s:checkbox> SMS to doctor on OPD booking<br>
									    <h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">IPD</h5>
									   <%--  <s:checkbox name="smscheckrelativebedchange" id="smscheckrelative"></s:checkbox> SMS to relative on new admission/bed change<br> --%>
									    <s:checkbox name="sms_on_bedchange" id="sms_on_bedchange"></s:checkbox> SMS to doctor on bed change<br>
									     <s:checkbox name="sms_on_newadm" id="sms_on_newadm"></s:checkbox> SMS to doctor on new admission<br>
									   <%--  <s:checkbox name="smsrelativeDischarge" id="#"></s:checkbox> SMS to relative on discharge<br> --%>
									     <s:checkbox name="smsVisitingConslt" id="smsVisitingConslt"></s:checkbox> SMS to Visiting Consultant<br>
									    <%-- <h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Accounts</h5>
									    <s:checkbox name="smsPayment" id="smsPayment"></s:checkbox> SMS to patient on payment<br> --%>
									    <h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Investigation</h5>
									    <%-- <s:checkbox name="smspatientApproved" id="smspatientApproved"></s:checkbox> SMS to patient on approved<br>
									    <s:checkbox name="smspatientCompleted" id="smspatientCompleted"></s:checkbox> SMS to patient on Completed<br> --%>
									    <s:checkbox name="bdaysms" id="bdaysms"></s:checkbox> SMS to Patient on Birthday<br>
									     <s:checkbox name="immusms" id="immusms"></s:checkbox> SMS to Patient on a day before vaccination <br><br>
									    <label>Ward Name Show Setting</label><br>
                                 		<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Add Charge</h5>
                                 		<s:checkbox name="show_wardname" id="show_wardname"></s:checkbox> Show Wardname on charges <br><br>	
                                 		<label>OPD Setting</label><br>
                                 		<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">OPD Booking</h5>
                                 		<s:checkbox name="show_unpost" id="show_unpost"></s:checkbox> Show Invoice Unposted While Book With Payment <br><br>
                                 		<label>IPD Id Access Setting</label><br>
                                 		<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">IPD Id Access Setting</h5>
                                 		<s:checkbox name="show_new_ipd_id" id="show_new_ipd_id"></s:checkbox> Show New IPD ID Format<br><br>
                                 		<label>Hide LetterHead</label><br>
                                 		<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Hide LetterHead Setting</h5>
                                 		<s:checkbox name="hidelettinvst" id="hidelettinvst"></s:checkbox> Hide LetterHead in Investigation<br>
                                 		<s:checkbox name="hidelettemr" id="hidelettemr"></s:checkbox> Hide LetterHead in EMR<br>
                                 		<s:checkbox name="hidelettbillinv" id="hidelettbillinv"></s:checkbox> Hide LetterHead in Bill/Invoice<br>
                                 		<label>Save And Print</label><br>
                                 		<h5 style="color: darkred;background-color: #efefef;margin: 0px;padding: 3px 3px 3px 3px;margin-bottom: 3px;margin-top: 3px;">Save And Print Button Setting</h5>
                                 		<s:checkbox name="invest_savenprint" ></s:checkbox> Save and Print Investigation<br>
                                 		<s:checkbox name="prisc_savenprint" ></s:checkbox> Save and Print Prescription<br>
                                 		<label>Pharmacy</label><br>
                                 		<s:checkbox name="auto_generic_name" ></s:checkbox> Generic name and MFG from Master<br>
									</div>
											<br>
                                 			<div class="col-lg-4 col-md-4 col-xs-4">
                                 				<b>By Default Pharmacy</b>
                                 			</div>
                                 			<div class="col-lg-4 col-md-4 col-xs-4" style="float: right;">
                                 				 <s:select list="locationListPharmacy"  cssStyle="width:100%" name="default_phar_location" headerKey="0" headerValue="Select Location" listKey="id" listValue="name" cssClass="form-control chosen-select"></s:select> 
                                 				
                                 			</div>
                                 			<br>
                                 			<br>
	                               			<div class="col-lg-6 col-md-6 col-xs-6">
	                               				<b>Direct Prescription to Online Dashbaord</b>
	                               			</div>
	                               			<div class="col-lg-3 col-md-3 col-xs-3" style="float: right;">
	                               				<s:checkbox name="direct_prisc" ></s:checkbox>
	                               			</div>
								
                                 	</div>
                                 	
                                 		
                                 		
                                 		
                                 </div>
                                 
                                 
                                 
                                 	<div class="">
				<div class="">
					
					
					
						<div class="form-group">
							<div class="row">
								
								
								
								
								<div class="col-lg-3 col-md-3 hidden">
								<label>Select Fieleds</label>
			 						<button type="button" style="width: 100%;" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">Select Fields <span class="caret"></span></button>
												<ul class="dropdown-menu">
													<s:iterator value="ipdformfieldList">
												 	 
												 	 <s:if test="status==1">
												 	     <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" checked="checked" class="casef" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												 	 </s:if>
												 	 <s:else>
												 	   <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox" id="p<s:property value="id"/>" class="casef" value="<s:property value="id" />"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												 	 </s:else>
												 	 
												  </s:iterator>
												 
												</ul>
								</div>
								<s:hidden name="specializations" id="specializations"></s:hidden>
								<s:hidden name="fields" id="fields"></s:hidden>
								
							</div>
						</div>
						
						<div class="form-group hidden">
							<input type="button" value="Previous" class="btn btn-primary previous" data-target-div="#functionalitySubscription" data-target-tab="#FSTab"> 
							<input type="button" value="Next" class="btn btn-primary next" data-target-div="#accountInformation" data-target-tab="#AITab">
						</div>
				</div>
			</div>
                                 </div> 
                                 <div role="tabpanel" class="tab-pane" id="five">
                                 <div class="">
				
				<div class="row">
					<div class="col-lg-4 col-md-4 col-xs-12 minhewithbor">
						<div class="form-group">
							<label><span class="red">*</span>User Id</label>
							<s:textfield id="userId" name="userId" theme="simple"
								title="Minimum 6 characters, no special characters except underscore, starts with alphabet, no whitespaces between words,
					 	eg. sachin_verma, sachinverma, sachin123"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								onchange="checkUserIdExist(this.value)"
								placeholder="Enter User Id" disabled="true"/>
							<label id="errorUserId" class="text-danger"></label>
						</div>
						
						<div class="form-group hidden">
							<input type="button" value="Previous" class="btn btn-primary previous" data-target-div="#settingInformation" data-target-tab="#settingTab">
						<!-- 	<input id="nextClinic" type="button" value="Next" class="btn btn-primary next" 
data-target-div="#clinicLocation" data-target-tab="#CLTab"> -->

						</div>
					</div>
					
					<div class="col-lg-4 col-md-4 col-xs-12 minhewithbor">
					<div class="form-group">
						<label>Excess Limit</label>
										<s:textfield id="excess" name="excess" 
											cssClass="form-control" theme="simple" />
                                 		</div>
<div class="form-group">
                                 				<label>IPD Register Charge</label>
										<s:textfield id="ipdregcharge" name="ipdregcharge" 
											cssClass="form-control" theme="simple" />
                                 		</div>
                                 		<div class="form-group">
                                 			<label>IPD Register Charge Type</label><br>
								<s:checkbox cssClass="" name="ctp"></s:checkbox> Third Party<br>
							    <s:checkbox cssClass="" name="cself"></s:checkbox> Self<br>
                                 		</div>
                                 		<div class="form-group">
                                 				<label>OPD Follow Up Days</label>
										<s:textfield id="opdfollowup" name="opdfollowup" 
											cssClass="form-control" theme="simple" />
                                 		</div>
						
					</div>
					<div class="col-lg-4 col-md-4 col-xs-12">
						<div class="form-group">
                                 			<label>Auto Charge Time List</label>
										<s:if test="%{#advanceTimeList != 'null'}">
										<s:select id="advanceTime" name="advanceTime" list="advanceTimeList"
											cssClass="form-control" theme="simple" />
									</s:if>
                                 		</div>
                                 		<%if(loginInfo.getUserType()==2){ %>
                                 		<div class="form-group">
                                 			<label>Login Expiry Message</label>
                                 			<s:textarea cssClass="form-control" rows="9" maxlength="" name="loginexmsg" id="loginexmsg"></s:textarea>
                                 			</div>
                                 		<%} %>
					</div>
				</div>
			</div>
                                 </div>
                                 
                                 
                                 <div role="tabpanel" class="tab-pane" id="six" style="padding: 0px;">
                                 
                                 <div class="row">
                                 	<div class="col-lg-12 col-md-12 col-xs-12" style="background-color: rgba(239, 239, 239, 0.32);padding: 5px 0px 5px 20px;">
                                 		<div class="form-inline">
                                 			<div class="form-group">
                                 				<label>Host Name:</label>
                                 				<p><input class="form-control" /></p>
                                 			</div>
                                 		</div>
                                 	</div>
                                 	
                                 	<div class="col-lg-12 col-md-12 col-xs-12">
                                 		<div class="col-lg-3 col-md-3 col-xs-12 minhewithbor">
                                 			<h4 style="background-color: #777777;color: #fff;padding: 5px;">Information Mail
                                 				<ul class="settings" style="padding: 0px;list-style: none;margin-bottom: 0px;float: right;">
															<li>
																<div class="control-label">
							                                                <div class="onoffswitch greensea">
							                                                	<input type="checkbox" name="onoffswitch" checked="checked" onclick="setChangeValueCheckEmail()" class="onoffswitch-checkbox" id="checkMailSend">
							                                                    <label class="onoffswitch-label" for="checkMailSend">
							                                                        <span class="onoffswitch-inner"></span>
							                                                        <span class="onoffswitch-switch"></span>
							                                                    </label>
							                                                </div>
							                                            </div>
							                                    </li>
														</ul>
                                 			</h4>
                                 			<div class="form-group">
							      				<label>User Name</label>
							      				<p><input class="form-control" /></p>
							      			</div>
							      			<div class="form-group">
							      				<label>Password</label>
							      				<p><input class="form-control" /></p>
							      			</div>
							      			
							      			<div class="form-group">
							      				<label>Set Auto Reminder &nbsp;<small style="color: #999;">(24 / 48 / 72 hour)</small></label>
							      				<p><s:select name="adDuration" disabled="true" list="#{'1':'24 Hour','2':'48 Hour','3':'72 Hour','4':'96 Hour','5':'120 Hour'}" cssClass="form-control" headerKey="0" headerValue="Select Duration" ></s:select></p>
							      			</div>
                                 		</div>
                                 		<div class="col-lg-3 col-md-3 col-xs-12 minhewithbor">
                                 			<h4 style="background-color: #777777;color: #fff;padding: 5px;">Accounting Mail
                                 				<ul class="settings" style="padding: 0px;list-style: none;margin-bottom: 0px;float: right;">
															<li>
																
																<div class="control-label">
							                                                <div class="onoffswitch greensea">
							                                                	<input type="checkbox" name="onoffswitch" checked="checked" onclick="setChangeValueCheckEmail()" class="onoffswitch-checkbox" id="checkMailSend">
							                                                    <label class="onoffswitch-label" for="checkMailSend">
							                                                        <span class="onoffswitch-inner"></span>
							                                                        <span class="onoffswitch-switch"></span>
							                                                    </label>
							                                                </div>
							                                            </div>
							                                    </li>
														</ul>
                                 			</h4>
                                 			<div class="form-group">
							      				<label>User Name</label>
							      				<p><input class="form-control" /></p>
							      			</div>
							      			<div class="form-group">
							      				<label>Password</label>
							      				<p><input class="form-control" /></p>
							      			</div>
							      			
							      			<div class="form-group">
							      				<label>Set Auto Reminder &nbsp;<small style="color: #999;">(24 / 48 / 72 hour)</small></label>
							      				<p><s:select name="adDuration" disabled="true" list="#{'1':'24 Hour','2':'48 Hour','3':'72 Hour','4':'96 Hour','5':'120 Hour'}" cssClass="form-control" headerKey="0" headerValue="Select Duration" ></s:select></p>
							      			</div>
                                 		</div>
                                 		<div class="col-lg-3 col-md-3 col-xs-12 minhewithbor">
                                 			<h4 style="background-color: #777777;color: #fff;padding: 5px;">Marketing Mail
                                 				<ul class="settings" style="padding: 0px;list-style: none;margin-bottom: 0px;float: right;">
															<li>
																
																<div class="control-label">
							                                                <div class="onoffswitch greensea">
							                                                	<input type="checkbox" name="onoffswitch" checked="checked" onclick="setChangeValueCheckEmail()" class="onoffswitch-checkbox" id="checkMailSend">
							                                                    <label class="onoffswitch-label" for="checkMailSend">
							                                                        <span class="onoffswitch-inner"></span>
							                                                        <span class="onoffswitch-switch"></span>
							                                                    </label>
							                                                </div>
							                                            </div>
							                                    </li>
														</ul>
                                 			
                                 			</h4>
                                 			<div class="form-group">
							      				<label>User Name</label>
							      				<p><input class="form-control" /></p>
							      			</div>
							      			<div class="form-group">
							      				<label>Password</label>
							      				<p><input class="form-control" /></p>
							      			</div>
							      			
							      			<div class="form-group">
							      				<label>Set Auto Reminder &nbsp;<small style="color: #999;">(24 / 48 / 72 hour)</small></label>
							      				<p><s:select name="adDuration" disabled="true" list="#{'1':'24 Hour','2':'48 Hour','3':'72 Hour','4':'96 Hour','5':'120 Hour'}" cssClass="form-control" headerKey="0" headerValue="Select Duration" ></s:select></p>
							      			</div>
                                 		</div>
                                 		<div class="col-lg-3 col-md-3 col-xs-12 minhewithbor">
                                 			<h4 style="background-color: #777777;color: #fff;padding: 5px;">Procurement Mail
                                 				<ul class="settings" style="padding: 0px;list-style: none;margin-bottom: 0px;float: right;">
															<li>
																
																<div class="control-label">
							                                                <div class="onoffswitch greensea">
							                                                	<input type="checkbox" name="onoffswitch" checked="checked" onclick="setChangeValueCheckEmail()" class="onoffswitch-checkbox" id="checkMailSend">
							                                                    <label class="onoffswitch-label" for="checkMailSend">
							                                                        <span class="onoffswitch-inner"></span>
							                                                        <span class="onoffswitch-switch"></span>
							                                                    </label>
							                                                </div>
							                                            </div>
							                                    </li>
														</ul>
                                 			</h4>
                                 			<div class="form-group">
							      				<label>User Name</label>
							      				<p><input class="form-control" /></p>
							      			</div>
							      			<div class="form-group">
							      				<label>Password</label>
							      				<p><input class="form-control" /></p>
							      			</div>
							      			
							      			<div class="form-group">
							      				<label>Set Auto Reminder &nbsp;<small style="color: #999;">(24 / 48 / 72 hour)</small></label>
							      				<p><s:select name="adDuration" disabled="true" list="#{'1':'24 Hour','2':'48 Hour','3':'72 Hour','4':'96 Hour','5':'120 Hour'}" cssClass="form-control" headerKey="0" headerValue="Select Duration" ></s:select></p>
							      			</div>
                                 		</div>
                                 	</div>
                                 
                                 </div>
                                 
                                 
                                 
                                 </div>
                                  
                         </div>
                         
                         <div class="row">
								<div class="col-lg-12 col-md-12 text-right" style="padding-bottom: 10px;padding-right: 30px;">
									<s:submit theme="simple" cssClass="btn btn-primary" value="Update" onclick="return validateAI()" />
									<s:reset theme="simple" cssClass="btn btn-primary" />
								</div>
							</div>
                         
                         </s:form>
					</div>
					
					
					
</div>

<div class="col-lg-1 col-md-1">

</div>









<div id="docRegTabsContent" class="hidden">
	
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

<script type="text/javascript" src="common/slimScroll/jquery.slimscroll.min.js"></script>	
					<script>
    	$(function() {
	    $('.scrolltable').slimScroll({
	   		height : '380px',
	   		railVisible: true,
			alwaysVisible: true
	  });
	
	 });
    </script>
    
    <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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
    
