<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.main.common.constants.Constants"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="registration/js/userprofile.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script>
$(document).ready(function() {

	$("#dob").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true
	});
});

</script>
<style>

	.setrole{
		    background-color: #ddd;
    padding: 10px !important;
    margin-top: -18px;
    margin-bottom: 12px;
	}
	.coloback{
    background-color: #efefef !important;
    padding: 10px 0px 10px 10px;
    margin-top: 0px;
    margin-bottom: 0px;
}
.file-upload {
  position: relative;
  overflow: hidden;
      margin: 0px;
    width: 55%; }

.file-upload input.file-input {
  position: absolute;
  top: 0;
  right: 0;
  margin: 0;
  padding: 0;
  font-size: 20px;
  cursor: pointer;
  opacity: 0;
  filter: alpha(opacity=0); }
</style>
 <style>
 .field-icon {
  float: right;
  margin-left: -25px;
  margin-right: 11px;
  margin-top: -20px;
  position: relative;
  z-index: 2;
}

.container{
  padding-top:50px;
  margin: auto;
}
 </style>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>

<div class="">
							<div class="">
							<div class="col-lg-1 col-md-1"></div>
								<div class="col-lg-10 col-md-10 row details">
									<div class="">
										<h4>Add New	Practitioner</h4>
									</div>
								</div>
								<div class="col-lg-1 col-md-1"></div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
														<s:form action="saveDetailsUserProfile" id="userprofile_form" theme="simple" onsubmit="return validateDetail()" method="post" enctype="multipart/form-data"
	validate="True" >
	<s:hidden name="id" id="id"></s:hidden>
	<s:hidden name="jobtitle" id="jobtitless"/>
	<s:hidden id="profileimg" name="profileimg"></s:hidden>
	<div class="col-lg-1 col-md-1"></div>
<div class="col-lg-10 col-md-10" style="border: 1px solid #efefef;">
	<div class="">
		<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
			<h4 class="coloback">Practitioner Details</h4>
		</div>
		<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
		
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="margin-top: 10px;">
		<label for="exampleInputEmail1">Full Name<span class="red">*</span></label>
			<div class="col-lg-12 col-md-12" style="padding: 0px;">
				<div class="col-lg-2 col-md-2 form-group" style="padding: 0px;">
					<s:select id="initial" name="initial" list="initialList" headerKey=""  title="Select Initial" cssClass="form-control showToolTip" data-toggle="tooltip" />
				</div>
				<div class="col-lg-5 col-md-5 form-group" style="padding-right: 0px;">
					<s:textfield name="firstname" id="firstname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter First Name" title="Enter First name"></s:textfield>
				</div>
				<div class="col-lg-5 col-md-5 form-group" style="padding-right: 0px;">
					<s:textfield name="lastname" id="lastname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Last Name" title="Enter Last Name"></s:textfield>
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">E-mail ID<span class="red">*</span></label>
				<s:textfield id="email" name="email" onchange="checkEmailIdExist(this.value)" key="label.email"
						labelposition="left" required="true" size="50"
						title="Enter valid email id, it will be used to send job notifications, eg. yourname@gmail.com, yourname@yahoo.co.in"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter E-mail ID" />
						<label id="errorEmailId" class="text-danger"></label>
			</div>
			<div class="form-group" style="margin-top: -15px;">
				<label for="exampleInputEmail1">Mobile No<span class="red">*</span></label>
				<div class="col-lg-12 col-md-12" style="padding: 0px;">
					<div class="col-lg-4 col-md-4 form-group" style="padding: 0px;">
						<s:select cssClass="form-control showToolTip" name="countrycode" id="countrycode" list="countrycodeList" listKey="countryid" listValue="countryName" headerKey="0" headerValue="Select Code"/>
					</div>
					<div class="col-lg-8 col-md-8 form-group" style="padding-right: 0px;">
						<s:textfield id="mobile" name="mobile" onchange="checkMobileNoExist(this.value)" key="label.mobileNo"
						labelposition="left" required="true"
						title="Specify valid mobile number, eg 9876543210"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Mobile No (for SMS Confirmation)" maxlength="10"/>	
						<label id="errorMobileNo" class="text-danger"></label>
					</div>
				</div>
				<div class="col-lg-12 col-md-12" style="padding: 0px;">
			
				
			<div class="form-group" style="width: 20%"	>
				<label for="exampleInputEmail1">Birth Date</label>
				<s:textfield readonly="true" name="dob" id="dob"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Birth Date"></s:textfield>
			</div>
			<div class="form-inline" style="margin-bottom: 13px" >
			<div class="form-group" style="width: 30%">
				<label for="exampleInputEmail1">Employee ID</label>
				<s:textfield  name="empid" id="empid" onchange="checkEmployeeIdExist(this.value)"
					cssClass="form-control" theme="simple" style="width:90%;" placeholder="Employee ID"></s:textfield>
					<label id="errorEmployeeId" class="text-danger"></label>
					
			</div>
			
			<div class="form-group" style="width: 67%">
				<label for="exampleInputEmail1">Reg. / License ID</label>
				<s:textfield  name="licenceId" id="licenceId"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Reg. / License ID"></s:textfield>
			</div>
			<%if(loginfo.getClinicid1().equals("manaspro")) {%>
			<div class="form-group" style="width: 67%">
				<label for="exampleInputEmail1">Commercial</label>
				<s:textfield  name="commercial" id="commercial"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Commercial"></s:textfield>
			</div>
			
			<div class="form-group" style="width: 67%">
				<label for="exampleInputEmail1">Consultatant Share</label>
				<s:textfield  name="Consultatantshare" id="Consultatantshare"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Consultatant Share"></s:textfield>
			</div>
			
			<div class="form-group" style="width: 67%">
				<label for="exampleInputEmail1">City</label>
				<s:textfield  name="city" id="city"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="City"></s:textfield>
			</div>
			
			<div class="form-group" style="width: 67%">
			    <label for="exampleInputEmail1">Select Clinic Userid</label>
			   <s:select name="clinic_userid" list="Clinicuseridlist"
						   headerKey="0" headerValue="Select Clinic Userid" listKey="Userid" listValue="Userid"
						   cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" title="Select Clinic Userid"/>
						   
			</div>
			
			<% }%>
			<%if(loginfo.getClinicid1().equals("manasclinic")) {%>
			
			<div class="form-group" style="width: 67%">
			    <label for="exampleInputEmail1">Select Clinic Name</label>
			   <s:select name="Clinic_name" list="Clinicstafflist"
						   headerKey="0" headerValue="Select Clinic Name" listKey="id" listValue="Clinic_name"
						   cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" id="Clinic_name" title="Select Clinic Name"/>
						   
			</div>
			
			<div class="form-group" style="width: 67%">
				<label for="exampleInputEmail1">Hospital Name</label>
				<s:textfield  name="hospitalname" id="hospitalname"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Hospital Name"></s:textfield>
			
			</div>
			
			<%-- <div class="form-group" style="width: 67%">
			    <label for="exampleInputEmail1">Select Clinic Name</label>
			   <s:select name="Clinic_name" list="Clinicstafflist"
						   headerKey="0" headerValue="Select Clinic Name" listKey="id" listValue="Clinic_name"
						   cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" id="Clinic_name" title="Select Clinic Name"/>
						   
			</div> --%>
			
			<% }%>
			<br>
			
			</div>
				</div>
				
			</div>
			
		</div>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="margin-top: 35px;">
			<div class="form-group">
				<div class="col-lg-12 col-md-12 col-xs-12 setrole" style="padding:0px;">
					<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;">
						<label for="exampleInputPassword1">Role Group<span class="red">*</span></label>
						 <s:select name="jobgroup" id="jobgroup" list="jobGroupList"
					    listKey="id" listValue="name" 
					    headerKey="0" headerValue="Select Role Group" onchange="setJobTitleAjax(this.value)"
					    cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" title="Select Role Group">
					    	
					    </s:select>
					</div>
					<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right:0px;">
						 <label for="exampleInputPassword1">Role Title<span class="red">*</span></label>
			    <%if(loginfo.getUserType()==2) {%>
			    	<div id="jobtitlediv">
					<s:select id="jobtitle" name="jobtitle" list="jobTitleList"
						headerKey="0" headerValue="Select Role Title" listKey="id"
						 onchange="checkDoctorSelect()"
						cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" title="Select Role title"/>
						</div>
					<% } else{%>
							<s:select  id="jobtitle" name="jobtitle" list="jobTitleList"  disabled="true"
						headerKey="0" headerValue="Select Role Title"
						title="Select Role title" onchange="checkDoctorSelect()"
						cssClass="form-control showToolTip" data-toggle="tooltip" />
					<% }%>
					</div>
					<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right:0px;padding-top: 5px;">
						<label for="exampleInputPassword1">Responsible Doctor</label>
			   <s:select onchange="checkDoctorSelect()" id="doctor" name="doctor" list="doctorList" listKey="id" listValue="name"
						headerKey="0" headerValue="Select Responsible Doctor"
						
						cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" title="Select Responsible Doctor"  />
						<label id="errordoctor" class="text-danger"></label>
					</div>
					
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;">
				<div class="col-lg-4 col-md-4 col-xs-4" style="padding:0px;padding-top: 5px;">
					<div class="form-group">
			    <label for="exampleInputPassword1">Practitioner Type</label>
			   <s:select id="practitonerType" name="practitonerType" list="practitonerTypeList" listKey="id" listValue="name"
						headerKey="0" headerValue="Select User Type"
						title="Select User Type" 
						cssClass="form-control showToolTip" data-toggle="tooltip" />
			  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right:0px;">
					<div class="form-group">
			    <label for="exampleInputPassword1">Specialization<span class="red">*</span></label>
			   <s:select id="diciplineName" name="diciplineName" list="disciplineList" listKey="id" listValue="discipline"
						headerKey="0" headerValue="Select Specialization"
						 
						cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" title="Select Specialization"/>
			  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right:0px;">
					<div class="form-group">
			    <label for="exampleInputPassword1">Qualification<span class="red">*</span></label>
			   <s:textfield name="qualification" id="qualification"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Qualification" title="Enter Qualification"></s:textfield>
			  </div>
				</div>
			
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;">
		  <div class="col-lg-6 col-md-6 col-xs-6">
		   <s:checkbox name="hasDiary" id="hasDiary"/>
		    <label for="exampleInputPassword1" style="vertical-align: sub;">Select to Display on Dashboard</label>
		   
		  </div>
			 <div class="col-lg-6 col-md-6 col-xs-6">
		   <s:checkbox name="secondary_doc" id="secondary_doc"/>
		    <label for="exampleInputPassword1" style="vertical-align: sub;">Secondary Doctor</label>
		   
		  </div>
		</div>	
			
			
		</div>
		
	</div>
	 <script>
   function myShowPass() {
      var x = document.getElementById("password");
     
      if (x.type === "password") {
          x.type = "text";
      } else {
          x.type = "password";
      }
      }
   function myPassCon(){
    
      var y=   document.getElementById("confirmPassword");
      if (y.type === "password") {
          y.type = "text";
      } else {
          y.type = "password";
      }

   }
 </script>
	<div class="col-lg-12" style="padding:0px;">
			<h4 class="coloback">Account Details</h4>
		</div>
		
		<div class="col-lg-12 col-xs-12 col-md-12" style="padding:0px;">
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="margin-top: 10px;">
			<div style="background-color: rgba(245, 245, 220, 0.89);padding: 15px;margin-bottom: 10px;margin-top: 10px;">
				  <div class="form-group">
    <label for="exampleInputEmail1">UserId<span class="red">*</span></label>
    <s:textfield name="userId" id="userId"
						onchange="checkUserIdExist(this.value)"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter User Id" title="alphabets and numbers no special characters except underscore('_') min 4 and max 20 characters"></s:textfield>
				<label id="errorUserId" class="text-danger"></label>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password<span class="red">*</span></label>
    <s:password id="password" name="password" theme="simple"
      title="password must contain atleast 1 numeric character,1 lowercase letter,1 uppercase letter,1 special character and minimum 8 characters"
      cssClass="form-control showToolTip" data-toggle="tooltip"
      placeholder="Enter Password" />    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password" onclick="myShowPass()"></span>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Confirmed Password<span class="red">*</span></label>
    <s:password id="confirmPassword" name="confirmPassword"
      title="Re-enter above password"
      cssClass="form-control showToolTip" data-toggle="tooltip"
      placeholder="Enter Confirmed Password" /> <span toggle="#conpassword-field" class="fa fa-fw fa-eye field-icon toggle-password" onclick="myPassCon()"></span>
  </div>
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6" style="margin-top: 10px;">
			<div class="col-lg-12 col-md-12" style="padding: 0px;">
				<div class="col-lg-4 col-md-4 col-xs-4" style="padding: 0px;">
					<div class="form-group">
		    <label for="exampleInputEmail1">Select Charge Type</label>
		    <s:select name="chargeType" id="chargeType" 
						 list="chargeSignList" listKey="id" listValue="sign"
						cssClass="form-control" onchange="setActionForAll()"></s:select>
		  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right: 0px;">
					<div class="form-group">
		    <label for="exampleInputPassword1">DNA Charges</label>
		    <s:textfield id="dnaCharge" name="dnaCharge" labelposition="left" size="50"
						title="Enter dna charges in %" cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter DNA Charges in %" />
		  </div>
				</div>
			<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right: 0px;">
					<div class="form-group">
		    <label for="exampleInputPassword1">Appointment Charges</label>
		    <s:textfield id="compAppCharge" name="compAppCharge" labelposition="left"
						title="Enter Complete Appointment Charges in %"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Complete Appointment Charges in %" />
		  </div>
				</div>
			<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right: 0px;">
					<div class="form-group">
		    <label for="exampleInputPassword1">Ipd Charges</label>
		    <s:textfield id="ipdCharge" name="ipdCharge" labelposition="left"
						title="Enter Ipd Charges in %"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Ipd Charges in %" />
		  </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right: 0px;">
					<div class="form-group">
		    <label for="exampleInputPassword1">Surgeon Charges</label>
		    <s:textfield id="surgeonCharge" name="surgeonCharge" labelposition="left"
						title="Enter Surgeon Charges in %"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Surgeon Charges in %" />
		        </div>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right: 0px;">
					<div class="form-group">
					     <label for="exampleInputPassword1">is Visiting Doctor</label><br>
					     <s:checkbox name="visitingdoctor" id="visitingdoctor"></s:checkbox>  
				     </div>
				</div>
				
				<div class="col-lg-12 col-md-12" style="padding: 0px;">
					<div class="col-lg-4 col-md-4 col-xs-4 hidden" style="padding-right: 0px;">
						<div class="form-group">
		   					 <label for="exampleInputPassword1">Select Ward</label>
		   						<s:hidden name="wardid" id="wardid"/>
		   						 
		   						 <div style="height: 100px; overflow: scroll;">
													<s:iterator value="wardList">
												  <li><a href="#" class="small" data-value="option1" tabIndex="-1">
												  		<input id="wd<s:property value="id"/>" onclick="setselectedwardid()" type="checkbox" class="wardch" value="<s:property value="id"/>"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												  </s:iterator>
												 
												</div>
		  				</div>
					</div>
					
					<div class="col-lg-4 col-md-4 col-xs-4" style="padding-right: 0px;">
						<div class="form-group">
		   					 <label for="exampleInputPassword1">Investigation Access</label>
		   					 
		   						<s:hidden name="labname" id="labname"/>
		   						Select All<input type="checkbox"  onchange="selectAllAccess(this.checked)" id="select all" >
		   						 <div style="height: 100px; overflow: scroll;">
		   						
													<s:iterator value="labnameList">
												  <li><a href="#" class="small" data-value="option1" tabIndex="-1">
												  <input id="lab<s:property value="name"/>" onclick="setselectedlab()" type="checkbox" class="labch" value="<s:property value="name"/>"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
												  </s:iterator>
												 
												</div>
		  				</div>
					</div>
					
				</div>
			</div>
			
		  
		   <!--<div class="form-group">
		    	<label for="exampleInputPassword1">GEN PTNT</label>
		   		 <s:textfield id="genptn" name="genptn"></s:textfield>
		  </div>
		  <div class="form-group">
		    	<label for="exampleInputPassword1">TPA PTN</label>
		   		 <s:textfield id="tpaptn" name="tpaptn"></s:textfield>
		  </div>
		  <div class="form-group">
		    	<label for="exampleInputPassword1">WCL</label>
		   		 <s:textfield id="wcl" name="wcl"></s:textfield>
		  </div>
		  <div class="form-group">
		    	<label for="exampleInputPassword1">ISRO</label>
		   		 <s:textfield id="isro" name="isro"></s:textfield>
		  </div>
		  <div class="form-group">
		    	<label for="exampleInputPassword1">NTPC</label>
		   		 <s:textfield id="ntpc" name="ntpc"></s:textfield>
		  </div>
		  
		  --><div class="col-lg-12 col-md-12" style="padding: 0px;">
				<!-- <div class="col-lg-4 col-md-4 col-xs-4" style="padding: 0px;">
				<img src="cicon/profiledoc.jpg" class="img-responsive" style="width:55%;"></img>
				<br>
				<button class="file-upload"> <input type="file" class="file-input">Upload Pic</button>
				
				</div> -->
				
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
		  </div>
		  
		</div>
		</div>
	</div>	
	
</div>	
<div class="col-lg-1 col-md-1"></div>
<div class="col-lg-12 col-md-12 col-sm-12" style="padding-right: 0px;">
<div class="col-lg-1 col-md-1"></div>
<div id="savediv1">
<div class="col-lg-10 col-md-10" id="savediv" style="text-align: right;padding-right: 0px;margin-top: 15px;margin-bottom: 15px;">
<s:submit cssClass="btn btn-primary" value="Save" id="savebutton" onclick = "return validateDetail()"/>

<a href="back1UserProfile" class="btn btn-primary">Back To List Page</a>
</div>
</div>
</div>
</s:form>
</div>

<div class="col-lg-1 col-md-1"></div>
</div>				

										</div>
									</div>
								</div>
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



