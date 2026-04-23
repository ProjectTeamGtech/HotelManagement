<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.ThirdParties.web.form.*"%>
<script type="text/javascript" src="thirdParties/js/addThirdPartyValidaton.js"></script>
<script type="text/javascript" src="inventory/js/addvendor.js"></script>

<style>


.tab-content {
	min-height: 515px;
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
	    min-height: 180px;
	    border-right:1px solid #ddd;
}
</style>
 <SCRIPT type="text/javascript" >
    $(document).ready(function() {
		$(".datepicker").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
	});
</script>

<div class="row details">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
		<h4>Register Payer</h4>
	</div>
</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:10px; ">
	<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 hidden">
		<%-- <s:select list="masterlist"
		cssClass="form-control showToolTip chosen-select" name="mastername"
		listKey="id" listValue="name" onchange="selectAction(this.value)"></s:select> --%>
	</div>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="text-align: right;">
		<!-- <input type="button" class="btn btn-primary" onclick="javascript:history.go(-1);return false;" value="Back"> -->
		<a href="payerCreationMaster?selectedid=84" class="btn btn-primary">Back</a>
	</div>
</div>

	
	
		
<s:form action="saveTPDataThirdParty" theme="simple">
	<s:hidden name="isFromPG"></s:hidden>
	<div class="col-lg-12 col-md-12 col-xs-12">
		<!-- <div class="" style="padding-top: 12px;"><h4 style="margin-top: 7px;font-size: 14px;">Add New Payer</h4></div> -->
		<div class="card" style="margin-bottom: 0px;">
			<ul class="nav nav-tabs" role="tablist">
            	<li role="presentation" class="active"><a href="#one" aria-controls="one" role="tab" data-toggle="tab">Payer Information</a></li>
                <li role="presentation"><a href="#two" aria-controls="two" role="tab" data-toggle="tab">Account Setting</a></li>
            </ul>
            <!-- Tab panes -->
            <div class="tab-content">
            	<div role="tabpanel" class="tab-pane active" id="one">
            		<div class="col-lg-12 col-md-12 col-xs-12">
		            	<div class="col-lg-3 col-md-3 col-xs-12">
		                	<div class="form-group">
								<label>Patient Type<span class="text-danger">*</span></label><br>
								<s:select id="patientType" name="patientType" list="#{'0':'Self','1':'Third Party'}"
								headerValue="Select Patient Type"
								headerKey="" cssClass="form-control showToolTip chosen-select"
								data-toggle="tooltip" onchange="setPayerType(this.value)" />
								<label  id = "patientTypeError" class="text-danger"></label>	
							</div>
		                </div>
		                <div class="col-lg-3 col-md-3 col-xs-12">
		                	<div class="form-group" id="typepayerdiv">
								<label>Payer Type<span class="text-danger">*</span></label><br>
								<s:select id="type1" name="type" list="thirdPartyTypeList"
								listKey="id" listValue="type" headerValue="Select Payer Type"
								headerKey="0" cssClass="form-control showToolTip chosen-select"
								data-toggle="tooltip" onchange="setTypeName(this.value)" />
								<label  id = "tpTypeError" class="text-danger"></label>	
							</div>
		                </div>
		                <div class="col-lg-3 col-md-3 col-xs-12">
		                	<div class="form-group">
								<label>Payer List<span class="text-danger">*</span></label><br>
							    <s:select id="typeName1" name="typeName" list="tpnameList"
								listKey="id" listValue="typeName" headerValue="Select Payer"
								headerKey="0" cssClass="form-control showToolTip chosen-select"
								data-toggle="tooltip" onchange="disableFiled(this.value)" /> 
								
							</div>
		                </div>
		                <div class="col-lg-3 col-md-3 col-xs-12">
			                <div class="form-group">
								<label>Main Payer </label><br>
								<s:checkbox name="maintp" id="maintp"></s:checkbox> 	
							</div>
		           		</div>
		                
		            </div>
		        	<div class="col-lg-12 col-md-12 col-xs-12" >
		        		<div class="col-lg-3 col-md-3 col-xs-12" >
			                 <div class="form-group">
							 	<label>Payer Name<span class="text-danger">*</span></label><br>
								<s:if test="typeName==0">	
									<s:textfield id="companyName" name="companyName" theme="simple"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter New Payer Name"
									onblur="initialFirstCap(this);" />
								</s:if>
								<s:else>
									<s:textfield id="companyName" name="companyName" theme="simple"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									placeholder="Enter New Payer Name"
									disabled="" onblur="initialFirstCap(this);" />
								</s:else>
								<label  id="payerNameError" class="text-danger"></label>	
							 </div>
		               	</div>
		            	<div class="col-lg-3 col-md-3 col-xs-12" >
		           			<div class="form-group">
								<label>Short Name</label><br>
								<s:textfield id="shortname" name="shortname" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Short Name"  onblur="" />
								<label  id="shortNameError" class="text-danger"></label>	
							</div>
		           		</div>
		           		
		           		<div class="col-lg-3 col-md-3 col-xs-12">
                        	<div class="form-group">
								<label>Phone No.</label><br>
								<s:textfield id="telephoneLine" name="telephoneLine"
								theme="simple" cssClass="form-control showToolTip"
								data-toggle="tooltip" placeholder="Enter Phone No."
								onchange="checTelePhoneLineValidation(this.value)" maxlength="10"/>
								<label id = "telephoneLineError" class="text-danger"></label>
							</div>
                        </div>
                        
                        <div class="col-lg-3 col-md-3 col-xs-12" >
                            <div class="form-group">
								<label>Fax</label><br>
								<s:textfield id = "fax" name = "fax" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Fax"/>
							</div>
                        </div>
		              </div> 
		           	<div class="col-lg-12 col-md-12 col-xs-12" >
                    	<div class="col-lg-3 col-md-3 col-xs-12">
	                         <div class="form-group">
								<label>First Line of Address</label><br>
								<s:textfield id="tpaddress" name="address" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Address" onblur="initialFirstCap(this);" />
							</div>
                         </div>
                         <div class="col-lg-3 col-md-3 col-xs-12">
                         	<div class="form-group">
								<label>Second Line of Address</label><br>
								<s:textfield name="secondLineAddress" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Second Line Address"  onblur="initialFirstCap(this);" />
							</div>
                         </div>
                    	<div class="col-lg-3 col-md-3 col-xs-12">
                        	<%-- <div class="form-group">
								<label>Town/City</label><br>
								<s:textfield id="tptown" name="town" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Town"  onblur="initialFirstCap(this);" />
							</div> --%>
							
							<div class="form-group" id="citydiv">
								<label for="email">City</label>
								<s:if test="cityList!=null">
									<s:select list="cityList" listKey="id" listValue="city"
									id="city" cssClass="form-control chosen-select"
									headerKey="0" headerValue="Select City" name="city"
									onchange="getStateAjaxnew(this.value)" />
								</s:if>
								<s:else>
									<select id="city" name="city" class="form-control chosen-select"> 
										<option value="0">Select City</option>
									</select>
								</s:else>
							</div>
						</div>
                        <div class="col-lg-3 col-md-3 col-xs-12">
                        	<div class="form-group" id="statediv">
								<label for="email">State</label>
								<s:if test="stateList!=null">
									<s:select list="stateList"
										cssClass="form-control chosen-select "
										onchange="getCities(this.value)" name="state" id="state"
										listKey="id" listValue="name" headerKey="0"
										headerValue="Select State" />
								</s:if>
								<s:else>
									<select id="state" name="state" class="form-control chosen-select"> 
										<option value="0">Select State</option>
									</select>
								</s:else>
							</div>
                        </div>
                        
                    </div>
                    <div class="col-lg-12 col-md-12 col-xs-12">
                    	<div class="col-lg-3 col-md-3 col-xs-12" >
                        	<div class="form-group">
								<label>Country</label><br>
								<s:if test="%{#countryList != 'null'}" >
				   					<s:select id="tpcountry" name="country" list="countryList" headerKey="0" headerValue="Select Country" 
				   					labelposition="left" title="Select your country from list" theme="simple" cssClass="form-control showToolTip chosen-select"
									data-toggle="tooltip"   />
		   	   					</s:if>	
							</div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-xs-12">
                        	<div class="form-group">
								<label>Post Code</label><br>
								<s:textfield id="tppostcode" name="postcode" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Post Code" maxlength="6" />
								<label id = "postCodeError" class="text-danger"></label>
							</div>
                        </div>
                       
                        <div class="col-lg-3 col-md-3 col-xs-12">
                        	<div class="form-group">
								<label>Email</label><br>
								<s:textfield id="compnyEmail" name="compnyEmail" theme="simple"
								cssClass="form-control showToolTip" data-toggle="tooltip"
								placeholder="Enter Email" 
								onchange="checkComapnyEmailValidation(this.value)" />
								<label id = "compnyEmailError" class="text-danger"></label>
							</div> 			
                        </div>
                         <div class="col-lg-3 col-md-3 col-xs-12">
                        	<div class="form-group">
								<label>Website</label><br>
								<s:textfield id = "web" name = "web" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Website" />
							</div>
                        </div>
                        
                        <div class="col-lg-3 col-md-3 col-xs-12 hidden">
                        	<div class="form-group">
								<label>Email-CC</label><br>
								<s:textfield id = "emailCc" name = "emailCc" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Email ID" />
							</div>
                        </div>
                     </div>
                     	
                     
                     <div class="col-lg-12 col-md-12 col-xs-12" >
                     	<div class="col-lg-3 col-md-3 col-xs-12">
							<div class="form-group">
								<label>Unit</label><br>
								<s:textfield id = "unit" name = "unit" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Unit" />
							</div>
	                    </div>
	                    <div class="col-lg-3 col-md-3 col-xs-12">
	                    	<div class="form-group">
								<label>Area</label><br>
								<s:textfield id = "area" name = "area" theme="simple" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Area" />
							</div>
                        </div>
                        
                        <div class="col-lg-3 col-md-3 col-xs-12" >
	                      	<div class="form-group">
	                      		<label>Status</label>
	                      		<s:select name="validityStatus" list="#{'1':'Active','0':'Inactive'}"
								cssClass="form-control showToolTip chosen-select"
								data-toggle="tooltip" />
	                      	</div>
	                    </div>
	                    <div class="col-lg-3 col-md-3 col-xs-12" >
	                      	<div class="form-group">
	                      		<label>Payment Type</label>
	                      		<s:select name="payementType" list="#{'Cash':'Cash','Cheque':'Cheque','NEFT':'NEFT','D/Card':'D/Card','UPI':'UPI','Credit':'Credit'}"
								cssClass="form-control showToolTip chosen-select"
								data-toggle="tooltip" />
	                      	</div>
	                    </div>
                        
	                    
                     </div>
                     <div class="col-lg-12 col-md-12 col-xs-12" >
                     	<div class="col-lg-3 col-md-3 col-xs-12">
	                      	<div class="form-group">
	                      		<label>Validity From</label>
	                      		<s:textfield readonly="true" id ="validity_fromdate" name="validityFrom" theme="simple" cssClass="form-control showToolTip datepicker" data-toggle = "tooltip" placeholder = "Enter Fromdate" />
	                      	</div>
	                    </div>
	                    <div class="col-lg-3 col-md-3 col-xs-12">
	                      	<div class="form-group">
	                      		<label>Validity To</label>
	                      		<s:textfield readonly="true" id ="validity_todate" name="validityTo" theme="simple" cssClass="form-control showToolTip datepicker" data-toggle = "tooltip" placeholder = "Enter Todate" />
	                      	</div>
	                    </div>
	                    
	                    <div class="col-lg-3 col-md-3 col-xs-12">
			                <div class="form-group">
								<label>Having Employee Code?</label><br>
								<s:checkbox name="enrollcode" id="enrollcode"></s:checkbox> 	
							</div>
							<div class="form-group">
								<label>Having Camp Area?</label><br>
								<s:checkbox name="campArea" id="campArea"></s:checkbox> 	
							</div>
		           		</div>
		           		
		           		 
		           		
                     	<div class="col-lg-3 col-md-3 col-xs-12" >
                     		<div class="form-group">
								<label>Remark</label><br>
								<s:textarea placeholder="Remark" rows="2" name = "tpAccountSettingNotes" id = "tpAccountSettingNotes" cssClass = "form-control showToolTip" onblur="initialFirstCap(this);"></s:textarea>
							</div>		
                     	</div>
                     </div>
    			</div>
                                
                                 <div role="tabpanel" class="tab-pane" id="two">
                                 
                                  <div id = "accountSetting">
                                 		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                                 			<div class="col-lg-3 col-md-3 col-xs-12" style="padding-left: 0px;">
                                 				<div class="form-group">
													<label>Agreed Credit Limit</label><br>
													<s:textfield name = "outInvoiceLimit" id = "outInvoiceLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "00.00" title="Enter Credit Limit"/>
												</div>
                                 			</div>
                                 			<div class="col-lg-3 col-md-3 col-xs-12">
                                 				<div class="form-group">
													<label>Credit Reminder Limit</label><br>
													<s:textfield name = "accountWarningLimit" id = "accountWarningLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "00.00" title="Enter Credit Reminder limit"/>
												</div>
                                 			</div>
                                 			<div class="col-lg-3 col-md-3 col-xs-12">
                                 				<div class="form-group">
													<label>Agreed Credit Duration</label><br>
													<s:textfield name = "creditDuration" id = "creditDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in Days" title="Enter Agreed Duration In Days"/>
												</div>
                                 			</div>
                                 			<div class="col-lg-3 col-md-3 col-xs-12">
                                 				<div class="form-group">
													<label>Credit Reminder Duration</label><br>
													<s:textfield name = "creditReminderDuration" id = "creditReminderDuration" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Value in Days" title="Enter Credit Reminder Duration In Days"/>
												</div>
                                 			</div>
                                 		</div>
                                 		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                                 			<div class="col-lg-3 col-md-3 col-xs-12" style="padding-left: 0px;">
                                 				<div class="form-group">
													<label>DNA Limit</label><br>
													<s:textfield name = "dnaLimit" id = "dnaLimit" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "0.00" title="Enter DNA Limit"/>
												</div>
                                 			</div>
                                 			<div class="col-lg-3 col-md-3 col-xs-12">
                                 				<div class="form-group">
													<label>Checked DNA Limit For All</label><br>
													<s:checkbox name="dnaForAll" id="dnaForAll"/>
												</div>
                                 			</div>
                                 			<div class="col-lg-3 col-md-3 col-xs-12">
                                 				<div class="form-group">
													<label>Ipd Share To Practitioner in %</label><br>
													<s:textfield name = "ipdShare" id = "ipdShare" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Ipd Share" title="Enter Ipd Share" />
												</div>
                                 			</div>
                                 			<div class="col-lg-3 col-md-3 col-xs-12">
                                 				<div class="form-group">
													<label>Surgeon Share in %</label><br>
													<s:textfield name = "surgeonShare" id = "surgeonShare" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Surgeon Share" title="Enter Surgeon Share"/>
												</div>
                                 			</div>
                                 		</div>
                                 		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                                 			<div class="col-lg-3 col-md-3 col-xs-12" style="padding-left: 0px;">
                                 				<div class="form-group">
													<label>Opd Share To Practitioner in %</label><br>
													<s:textfield name = "opdShare" id = "opdShare" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter Opd Share" title="Enter Opd Share" />
												</div>
                                 			</div>
                                 			<div class="col-lg-3 col-md-3 col-xs-12">
                                 			
                                 			</div>
                                 			<div class="col-lg-3 col-md-3 col-xs-12">
                                 			
                                 			</div>
                                 			<div class="col-lg-3 col-md-3 col-xs-12">
                                 			
                                 			</div>
                                 		</div>
                                 		<div class="col-lg-12" style="background-color: #ccc;padding: 4px 0px 0px 6px;margin-bottom: 10px;">
											<label>Appointment Charges</label>
										</div>
                                 		<div class="col-lg-12 col-md-12 col-xs-12" style="padding:0px;">
                                 			<div class="panel panel-primary">
					<div class="panel-body">
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class="col-lg-3 col-md-3" style="border-right: solid 1px #948E8E;margin-top: -5px;    background-color: #efefef;min-height: 43px;">
							<label>Default Appointment Name</label>
							
						</div>
						<div class="col-lg-3 col-md-3" style="border-right: solid 1px #948E8E;margin-top: -5px;    background-color: #efefef;min-height: 43px;">
							<label>Appointment Name</label>
							
						</div>
						<div class="col-lg-1 col-md-1" style="border-right: solid 1px #948E8E;min-height: 44px;margin-top: -5px;    background-color: #efefef;">
							
							<label>DNA Offset</label>
						</div>
						<div class="col-lg-3 col-md-3" style="border-right: solid 1px #948E8E;margin-top: -5px;    background-color: #efefef;">
								<label style="margin-left: 34px;">Appointment Charges For</label><br>
							<div class="row"style="margin-top: -3px; background-color: antiquewhite; padding: 2px 0px 0px;">
								<div class="col-lg-6 col-md-6" style="border-right: solid 1px #948E8E;">
							
									<label>DNA</label>
								</div>
								<div class="col-lg-6 col-md-6">
								
									<label>Completed</label>
								</div>
							</div>
						</div>
					
						<div class="col-lg-2 col-md-2" style="margin-top: -5px;background-color: #efefef;min-height: 43px;">
							<label>Duration</label>
						</div>
					</div>
					
					<br>
					 
						
							<div class="row">
									<div class="col-lg-3 col-md-3">
										<label>Initial Appointment</label>
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield onblur="initialFirstCap(this);" name = "dnaInitialApmt" id = "dnaInitialApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter name 15 char max" title="Enter Initail Apmt charges"/>
									</div>
									
									<div class="col-lg-1 col-md-1">
										<s:checkbox name="initialOffsetdna" id="initialOffsetdna"/>
									</div>
									
									<div class="col-lg-3 col-md-3">
										<div class="row">
											<div class="col-lg-6 col-md-6">
		 										<s:textfield cssStyle="text-align:center" maxlength="6" name = "dnaInitialCharge" id = "dnaInitialCharge" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "0.00" title="Enter Initail Apmt charges"/>
											</div>
											<div class="col-lg-6 col-md-6">
		 										<s:textfield cssStyle="text-align:center" maxlength="6" name = "completedInitialCharge" id = "completedInitialCharge" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "0.00" title="Enter Initail Apmt charges"/>
											</div>
										</div>
									</div>
									
									<div class="col-lg-2 col-md-2">
		 									<s:if test="%{#apmtDurationList != 'null'}">
											<s:select id="compltInitialApmtDuration" name="compltInitialApmtDuration" list="apmtDurationList"
												headerKey="0" headerValue="00:00" theme="simple"
												cssClass="showToolTip form-control" data-toggle="tooltip"
												title="Select Duration" />
										</s:if>
									</div>
									
							</div><br> 
							
							
							
							<div class="row">
									<div class="col-lg-3 col-md-3">
										<label>Follow-up Appointment</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield onblur="initialFirstCap(this);"  name = "dnafollowupApmt" id = "dnafollowupApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter name 15 char max" title="Enter FollowUP Apmt charges"/>
									</div>
								
									<div class="col-lg-1 col-md-1">
										<s:checkbox name="followupOffsetdna" id="followupOffsetdna"/>
									</div>
									
									
									<div class="col-lg-3 col-md-3">
										<div class="row">
											<div class="col-lg-6 col-md-6">
			 									<s:textfield cssStyle="text-align:center" maxlength="6" name = "dnaFollowupCharge" id = "dnaFollowupCharge" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "0.00" title="Enter FollowUP Apmt charges"/>
											</div>
											<div class="col-lg-6 col-md-6">
				 								<s:textfield cssStyle="text-align:center" maxlength="6" name = "completedFollowupCharge" id = "completedFollowupCharge" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "0.00" title="Enter FollowUP Apmt charges"/>
											</div>
										</div>
									</div>
									
									<div class="col-lg-2 col-md-2">
		 									<s:if test="%{#apmtDurationList != 'null'}">
											<s:select id="compltfollowupApmtDuration" name="compltfollowupApmtDuration" list="apmtDurationList"
												headerKey="0" headerValue="00:00" theme="simple"
												cssClass="showToolTip form-control" data-toggle="tooltip"
												title="Select Duration" />
										</s:if>
									</div>
									
							</div><br>
							
							
							<div class="row">
									<div class="col-lg-3 col-md-3">
										<label>Final Appointment</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield onblur="initialFirstCap(this);"  name = "dnafinalApmt" id = "dnafinalApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter name 15 char max" title="Enter Final Apmt charges"/>
									</div>
									
									<div class="col-lg-1 col-md-1">
										<s:checkbox name="finalOffsetdna" id="finalOffsetdna"/>
									</div>
									
									
									<div class="col-lg-3 col-md-3">
										<div class="row">
											<div class="col-lg-6 col-md-6">
				 								<s:textfield cssStyle="text-align:center" maxlength="6" name = "dnaFinalCharge" id = "dnaFinalCharge" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "0.00" title="Enter Final Apmt charges"/>
											</div>
											<div class="col-lg-6 col-md-6">
				 								<s:textfield cssStyle="text-align:center" maxlength="6" name = "completedFinalCharge" id = "completedFinalCharge" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "0.00" title="Enter Final Apmt charges"/>
											</div>
										</div>
									</div>
									
									<div class="col-lg-2 col-md-2">
		 								<s:if test="%{#apmtDurationList != 'null'}">
											<s:select id="compltfinalApmtDuration" name="compltfinalApmtDuration" list="apmtDurationList"
												headerKey="0" headerValue="00:00" theme="simple"
												cssClass="showToolTip form-control" data-toggle="tooltip"
												title="Select Duration" />
										</s:if>
										
									</div>
							</div> 
							<br>
							
							 <div class="row">
									<div class="col-lg-3 col-md-3">
										<label>Maintenance</label>
									
									</div>
									<div class="col-lg-3 col-md-3">
		 								<s:textfield onblur="initialFirstCap(this);"  name = "dnamaintenanceApmt" id = "dnamaintenanceApmt" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter name 15 char max" title="Enter Maintence charges"/>
									</div>
									
									<div class="col-lg-1 col-md-1">
										<s:checkbox name="maintnanceOffsetdna" id="finalOffsetdna"/>
									</div>
									
									
									<div class="col-lg-3 col-md-3">
										<div class="row">
											<div class="col-lg-6 col-md-6">
				 								<s:textfield cssStyle="text-align:center" maxlength="6" name = "dnaMaintnanceCharge" id = "dnaMaintnanceCharge" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "0.00" title="Enter Final Apmt charges"/>
											</div>
											<div class="col-lg-6 col-md-6">
				 								<s:textfield cssStyle="text-align:center" maxlength="6" name = "completedMaintnanceCharge" id = "completedMaintnanceCharge" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "0.00" title="Enter Final Apmt charges"/>
											</div>
										</div>
									</div>
									
									
									<div class="col-lg-2 col-md-2">
		 								<s:if test="%{#apmtDurationList != 'null'}">
											<s:select id="compltmaintenanceApmtDuration" name="compltmaintenanceApmtDuration" list="apmtDurationList"
												headerKey="0" headerValue="00:00" theme="simple"
												cssClass="showToolTip form-control" data-toggle="tooltip"
												title="Select Duration" />
										</s:if>
									</div>
							</div> 
							
							<a onclick="addRow('dynamicallyAddApmtTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
							<a onclick="deleteRow('dynamicallyAddApmtTable')" class="btn btn-primary" id = "deletefiled"><i class="fa fa-trash-o"></i> Delete Row</a>
							<table class="table" id = "dynamicallyAddApmtTable" style="display: none">
								<thead id = "showHeaderBlock">
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>
										<th align="center">Appointment Name</th>	
										<th align="center"> DNA Offset</th>	
										<th align="center">DNA Charge</th>	
										<th align="center">Completed Charge</th>	
										<th align="center">Appointment Duration</th>	
									</tr>
								</thead>
								
							
							<tbody>
						<s:hidden name = "danyamiclistpresent" id = "danyamiclistpresent"></s:hidden>
						<s:if test="typeName > 0">
								<%ArrayList<DynamicAppointment>dynamicApmtTypeList = (ArrayList<DynamicAppointment>)session.getAttribute("dynamicApmtTypeList"); 
								int i = 0;
								int count = 1;
							%><%if(dynamicApmtTypeList!=null){ %>
								<% for(DynamicAppointment dynamicAppointment:dynamicApmtTypeList ) {%>
									<tr>
										<td>
										<input type="checkbox" name="chk">
										</td>
            					 		<td> <%=count%> </td>
										<td><input placeholder="Enter name 15 char max" size="50" type="text" id = "dynamicApmt[<%=i%>].dnaName" name="dynamicApmt[<%=i%>].dnaName" value="<%=dynamicAppointment.getDnaName() %>" class="form-control showToolTip dnaName"></td>
										
										 <%if(dynamicAppointment.isOffset()){ %> 
										 	<input type="hidden"  name="dynamicApmt[<%=i%>].offset" id="dynamicApmt[<%=i%>].offset" value="true"/>
											 <td><input type="checkbox" name="chdna<%=i %>" id="chdna<%=i %>" onclick="setDnaOffsetValue(<%=i %>)" checked="checked"> </td>
										<% }else{%>
											<input type="hidden"  name="dynamicApmt[<%=i%>].offset" id="dynamicApmt[<%=i%>].offset" />
											 <td><input type="checkbox" name="chdna<%=i %>" id="chdna<%=i %>" onclick="setDnaOffsetValue(<%=i %>)"> </td>
										<% }%>
										<td><input type="text" style="text-align: center;" maxlength="6" id = "dynamicApmt[<%=i%>].dnaCharge" name="dynamicApmt[<%=i%>].dnaCharge" value="<%=dynamicAppointment.getDnaCharge() %>" class="form-control showToolTip dnaCharge"></td>
											
										<%-- <td><input type="text" name="dynamicApmt[<%=i%>].apmtName" value="<%=dynamicAppointment.getApmtName() %>" class="form-control showToolTip apmtName"></td> --%>
										<td><input type="text" style="text-align: center;" placeholder="0.00" maxlength="6" name="dynamicApmt[<%=i%>].apmtCharge" value="<%=dynamicAppointment.getApmtCharge() %>" class="form-control showToolTip apmtCharge"></td>
										<td>
										<select name="dynamicApmt[<%=i%>].apmtDuaration"  placeholder="0.00" value="<%=dynamicAppointment.getApmtDuaration()%>" class="form-control showToolTip apmtDuaration">
											<option value="<%=dynamicAppointment.getApmtDuaration()%>"><%=dynamicAppointment.getApmtDuaration()%></option>
											
											<option value="00:15">00:15</option>
											<option value="00:30">00:30</option>
											<option value="00:45">00:45</option>
											<option value="01:00">01:00</option>
											<option value="01:15">01:15</option>
											<option value="01:30">01:30</option>
										</select>
										</td>
										<td>
										<input type="hidden" id = "dynamicApmt[<%=i%>].id" name="dynamicApmt[<%=i%>].id" value="<%=dynamicAppointment.getId()%>">
										</td>
									</tr>
								<% i++;%>
								<% count++;%>
										<%} %>
										<%} %>
							</s:if>	
							</tbody>
							</table>
						</div>	
						</div>
                                 		
                                 		</div>
                                 		
                                 		
                                 </div>
                                 
                                 </div>
                                 
                        </div>
	</div>
		<s:token />			


<div class="row" id="insurenceorgroupsaveid">
				<div class="col-lg-12 col-md-12">
					<span style="color:red">Note:</span>
				</div>
				<div class="col-lg-10 col-md-10">
					<ul>
						<li>Appointment name allow 15 character max</li>
						<li>Select Offset Check box to Not count/Offset the DNA toward the Consultation Session for the Treatment Episode</li>
					</ul>
				</div>
				<div class="col-lg-2 col-md-2" style="text-align: right;">
					<input type="submit" class="btn btn-primary" value="Save" onclick="return checkValidations()">
					<a href="payerCreationMaster?selectedid=84" class="btn btn-primary">Back</a>
				</div>
				</div>
		</div>
		
	
	
	
	


											


<div class="">
		<div class="">
			<div class="">
				<div class="form-group">
					
						
									
					<div class="col-lg-12 col-md-12" id = "gpcontactdiv" style="display: none;">
									<div class="row" style="padding-left: 15px;padding-right: 15px;">
										<div class="col-lg-12" style="background-color: #65c4fd">
											<label>Contact Details</label>
										</div>
									</div>
							<div class="panel panel-primary">
							<div class="panel-body">
							<div class="row">
								<div class="col-lg-3 col-md-3">
									<label>Contact/GP Name</label><label><span
										class="text-danger">*</span></label>
									<s:textfield id="gpname" name="gpname" title="Enter GP Name"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter GP Name" onblur="initialFirstCap(this);" />
								</div>

								<div class="col-lg-3 col-md-3">
									<label>Work Phone No.</label>
									<s:textfield id="workphno" name="workphno"
										title="Enter work ph no" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter work ph no" />
								</div>



								<div class="col-lg-3 col-md-3">
									<label>Email</label>
									<s:textfield id="gpemailid" name="gpemailid"
										title="Enter Email" cssClass="form-control showToolTip"
										data-toggle="tooltip" placeholder="Enter Email" />
								</div>

								<div class="col-lg-3 col-md-3">
									<label>Fax</label>
									<s:textfield id="gpfax" name="gpfax" title="Enter Fax"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Fax" />
								</div>
								
							</div>
							<br>
							<div class="row">
								<div class="col-lg-6 col-md-6">

									<label>Notes</label>

									<s:textarea id="notes" name="notes" title="Enter Notes"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter notes" onblur="initialFirstCap(this);"></s:textarea>
								</div>
								<div class="col-lg-3 col-md-3" style="margin-top: 38px;" id="doctorsurgerysaveid">
									<input type="submit" class="btn btn-primary" value="  Save  " onclick="return checkValidations()">
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>	
	
</s:form>
	
					
			
		
		
		
		
			<!--<div class="row" id = "savetpbtn">
					<div class="col-lg-2 col-md-2"></div>
				</div> -->
		


<s:form action="editTPDetailsNewThirdParty" id="tpnamefrm">
	<input type="hidden" name="selectedid" id="selectedid">
	<input type="hidden" name="patientType" id="patient_Type">
	<input type="hidden" name="payer_Type" id="payer_Type">
	<s:hidden name="isFromPG"></s:hidden>
</s:form>

<s:form action="addThirdParty" id="tpnameaddfrm">
	<s:hidden name="isfromadd"></s:hidden>
	<input type="hidden" name="patientType" id="patient_Type_add">
	<input type="hidden" name="payerType" id="payerType_add">
	<s:hidden name="isFromPG"></s:hidden>
</s:form>
									
	
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


	
			
