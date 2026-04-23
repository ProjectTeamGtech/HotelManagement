<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
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

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>

<style>
@media (min-width: 768px) and (max-width: 979px) {
.topback2{
    background-color: transparent !important;
    padding: 10px 10px 10px 10px;
}
}

@media (min-width: 980px) {
    .topback2{
    background-color: transparent !important;
    padding: 10px 10px 10px 10px;
}
}
.coloback{
 background-color: #efefef !important;
    padding: 10px 0px 10px 10px;
    margin-top: 0px;
    margin-bottom: 0px;
}
.setrole{
	background-color: #ddd;
    padding: 10px !important;
    margin-top: 10px;
    margin-bottom: 12px;
	}
	.file-upload {
  position: relative;
  overflow: hidden;
      margin: 0px;
    width: 100%; }

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

<div class="">
							<div class="panel-body">
									<div class="col-lg-1 col-md-1"></div>
									<div class="col-lg-10 col-md-10 row details">
									<div class="">
										<h4>Update Practitioner</h4>
									</div>
								</div>
								<div class="col-lg-1 col-md-1"></div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<s:form action="updatesaveUserProfile" id="userprofile_form" theme="simple" method="post" enctype="multipart/form-data" >
	<s:hidden name="id" id="id"></s:hidden>
	<s:hidden name="jobtitle" id="jobtitless"/>
	<s:hidden name="Empid" id="Empid"/>
	<s:hidden id="profileimg" name="profileimg"></s:hidden>
	<div class="">
	<div class="">
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
		<div class="col-md-10 col-lg-10 col-sm-12 col-xs-12" style="border: 1px solid #efefef;padding-left: 0px;padding-right: 0px;">
		<div class="col-lg-12" style="padding:0px;">
			<h4 class="coloback">Practitioner Details</h4>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" style="margin-top:10px;">
		
			<!-- 	<div class="col-lg-1 col-md-1 col-xs-1" style="padding: 0px;">
				<img src="cicon/profiledoc.jpg" class="img-responsive" style="width:100%;"></img>
				<br>
				<button class="file-upload"> <input type="file" class="file-input">Upload Pic</button>

				
				</div> -->
		 
		<div class="col-lg-12 col-md-12" style="padding: 0px;">
				<!-- <div class="col-lg-4 col-md-4 col-xs-4" style="padding: 0px;">
				<img src="cicon/profiledoc.jpg" class="img-responsive" style="width:55%;"></img>
				<br>
				<button class="file-upload"> <input type="file" class="file-input">Upload Pic</button>
				
				</div> -->
				
				<div class="col-lg-3 col-md-3 col-xs-12" style="padding: 0px;">
						<s:hidden name = "userImageFileName"></s:hidden> 
						
					
						<div class="form-group">
							<s:if test="userImageFileName !=''">
							<img src="liveData/clinicLogo/<s:property value="userImageFileName"/>"  width="80" height="80">
							</s:if>
							<s:else>
							<img src="cicon/profiledoc.jpg"  width="80" height="80">
							
							</s:else>
						</div>
					
						<div class="form-group">
							<label>Upload Logo</label>
							<s:file name="userImage"/>					
						</div>
			  </div>
		  </div>
	<div class="col-lg-5 col-sm-5 col-md-5">
			<div class="form-group" style="margin-bottom: 72px;">
			    <label for="exampleInputEmail1" class="col-lg-12 col-md-12" style="padding-left: 0px;"><span class="red">*</span>Full Name</label>
			    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;padding-right:0px;">
			    <div class="col-lg-2 col-md-2 col-sm-2" style="padding-left: 0px;">
			    	<s:select id="initial" name="initial" list="initialList" headerKey=""  title="Select Initial" cssClass="form-control showToolTip" data-toggle="tooltip" />
			    </div>
			    <div class="col-lg-5 col-md-5 col-sm-5" style="padding-left: 0px;">
			    	<s:textfield name="firstname" id="firstname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter First Name" title="Enter First name"></s:textfield>
			    </div>
			    <div class="col-lg-5 col-md-5 col-sm-5" style="padding-left: 0px;padding-right:0px;">
			    	<s:textfield name="lastname" id="lastname" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder="Enter Last Name" title="Enter Last Name"></s:textfield>
			    </div>
			    </div>
			  </div>
			  <div class="form-group">
		    <label for="exampleInputEmail1"><span class="red">*</span>E-mail ID</label>
		   <s:textfield id="email" name="email" onchange="checkEmailIdExist(this.value)" key="label.email"
						labelposition="left" required="true" size="50"
						title="Enter valid email id, it will be used to send job notifications, eg. yourname@gmail.com, yourname@yahoo.co.in"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter E-mail ID" />
						<label id="errorEmailId" class="text-danger"></label>
		  </div>
		  <div class="form-group">
		    <label for="exampleInputEmail1" class="col-lg-12 col-md-12" style="padding-left: 0px;margin-top: -15px;"><span class="red">*</span>Mobile No</label>
		    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;padding-right:0px;">
			    <div class="col-lg-5 col-md-5 col-sm-5" style="padding-left: 0px;">
			    	<s:select cssClass="form-control showToolTip" name="countrycode" id="countrycode" list="countrycodeList" listKey="countryid" listValue="countryName" headerKey="0" headerValue="Select Code"/>
			    </div>
			    <div class="col-lg-7 col-md-7 col-sm-7" style="padding-left: 0px;padding-right:0px;">
			    	<s:textfield id="mobile" name="mobile" onchange="checkMobileNoExist(this.value)" key="label.mobileNo"
						labelposition="left" required="true"
						title="Specify valid mobile number, eg 9876543210"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Mobile No (for SMS Confirmation)" maxlength="10"/>	
						<label id="errorMobileNo" class="text-danger"></label>
			    </div>
			    </div>
		  </div>
		   <div class="form-group">
		   <label for="exampleInputEmail1">Birth Date<%-- <span class="red">*</span> --%></label>
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;padding-right:0px;">
			    <div class="col-lg-5 col-md-5 col-sm-5" style="padding-left: 0px;">
			
				
				<s:textfield readonly="true" name="dob" id="dob"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Birth Date"></s:textfield>
			</div>
			</div>
<%if(loginfo.isLmh() || loginfo.isPhysio()) {%>			
	<div class="form-group">
        <s:if test="Empid==''">
         <label for="exampleInputEmail1">Employee ID</label>
         <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;padding-right:0px;">
			    <div class="col-lg-5 col-md-5 col-sm-5" style="padding-left: 0px;">
				<s:textfield  name="empid" id="empid" onchange="checkEmployeeIdExist(this.value)"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Employee ID"></s:textfield>
					<label id="errorEmployeeId" class="text-danger"></label>
			</div>
			</div>
			
		       
         </s:if> 
         </div>
       <div class="form-group">
	<s:else>
	<label for="exampleInputEmail1">Employee ID</label>
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left: 0px;padding-right:0px;">
			    <div class="col-lg-5 col-md-5 col-sm-5" style="padding-left: 0px;">
				<s:textfield  name="empid" id="empid" onchange="checkEmployeeIdExist(this.value)"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="Employee ID" readonly="true"></s:textfield>
					<label id="errorEmployeeId" class="text-danger"></label>
		</div>
		</div>
</s:else>
</div>
<%} %>
			
			</div>
		</div>	
		<div class="col-lg-6 col-md-6 col-sm-6">
		<div class="col-lg-12 col-md-12 setrole" style="padding:0px;">
				<div class="col-lg-4 col-md-4 col-sm-4" style="padding-left:0px;">
			 <div class="form-group">
			    <label for="exampleInputPassword1"><span class="red">*</span>Role Group</label>
			    <s:select name="jobgroup" id="jobgroup" list="jobGroupList"
			    listKey="id" listValue="name" title="Select JobGroup"
			    headerKey="0" headerValue="Select JobGroup" onchange="setJobTitleAjax(this.value)"
			    cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" >
			    </s:select>
			</div>
		</div>
		
		<div class="col-lg-4 col-md-4 col-sm-4" style="padding-right:0px;">
		  <div class="form-group">
			    <label for="exampleInputPassword1"><span class="red">*</span>Role Title</label>
			    <%if(loginfo.getUserType()==2) {%>
			    <div id="jobtitlediv">
					<s:select id="jobtitle" name="jobtitles" list="jobTitleLists"
						listKey="name" listValue="name"
						headerKey="0" headerValue="Select Job Title"
						title="Select Job title" onchange="checkDoctorSelect()"
						cssClass="form-control showToolTip chosen-select" data-toggle="tooltip" />
					<% } else{%>
							<s:select id="jobtitle" name="jobtitles" list="jobTitleLists"  disabled="true"
							listKey="name" listValue="name"
						headerKey="0" headerValue="Select Job Title"
						title="Select Job title" onchange="checkDoctorSelect()"
						cssClass="form-control showToolTip" data-toggle="tooltip" />
					<% }%>
			  </div>
			  </div>
			</div>  
			<div class="col-lg-4 col-md-4 col-sm-4" style="padding-right:0px;padding-top:5px;">
				<div class="form-group">
			    <label for="exampleInputPassword1">Practitoner</label>
			   <s:select onchange="checkDoctorSelect()" id="doctor" name="doctor" list="doctorList" listKey="id" listValue="name"
						headerKey="0" headerValue="Select Practitoner"
						title="Select Practitoner Type" 
						cssClass="form-control showToolTip" data-toggle="tooltip" />
						<label id="errordoctor" class="text-danger"></label>
			 	 </div>
			</div>
		
		</div>
		
		<div class="col-lg-12 col-md-12" style="padding:0px;">
			<div class="col-lg-4 col-md-4" style="padding:0px;">
				<div class="form-group">
			    <label for="exampleInputPassword1">Practitoner Type</label>
			   <s:select id="practitonerType" name="practitonerType" list="practitonerTypeList" listKey="id" listValue="name"
						headerKey="0" headerValue="Select Practitoner Type"
						title="Select Practitoner Type" 
						cssClass="form-control showToolTip" data-toggle="tooltip" />
			  </div>
			</div>
			
			<div class="col-lg-4 col-md-4" style="padding-right:0px;">
				<div class="form-group">
			    <label for="exampleInputPassword1">Speciality<span class="red">*</span></label>
			   <s:select id="diciplineName" name="diciplineName" list="disciplineList" listKey="id" listValue="discipline"
						headerKey="0" headerValue="Select Speciality"
						title="Select Discipline" 
						cssClass="form-control showToolTip chosen-select" data-toggle="tooltip"/>
			  </div>
			</div>
			<div class="col-lg-4 col-md-4" style="padding-right:0px;">
			<div class="form-group">
			    <label for="exampleInputPassword1">Qualification<span class="red">*</span></label>
			   <s:textfield name="qualification" id="qualification"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Qualification" title="Enter Qualification"></s:textfield>
			  </div>
				
			</div>
		
		</div>
		<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="form-group">
			    <label for="exampleInputPassword1">Reg. / License ID</label>
			   <s:textfield name="licenceId" id="licenceId"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Reg. / License ID" title="Reg. / License ID"></s:textfield>
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
<div class="">
	<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;" >
	<h4 class="coloback">Practitioner Setup</h4>
		<div class="col-lg-12 col-md-12" style="margin-top:10px;padding:0px;">
			<div class="col-lg-3 col-md-3 col-xs-3">
				<div class="form-group">
		    <label for="exampleInputEmail1">Diary Color</label>
		    <s:textfield name="diarycolor" id="diarycolor"
					cssClass="color form-control showToolTip" 
					data-toggle="tooltip" placeholder="Enter Dairy Color"
					title="Enter  Dairy Color"></s:textfield>
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">This User</label><br>
		   	<s:checkbox id="isavailableonline" name="isavailableonline">   Is Available for online Booking?   </s:checkbox>
		  </div>
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3">
				<div class="form-group">
		    <label for="exampleInputPassword1">Diary Column Position</label>
		    <s:textfield name="diarycolumnposition" id="diarycolumnposition"
					cssClass="form-control showToolTip" data-toggle="tooltip"
					placeholder="Enter Dairy Position"
					title="Enter  Dairy Position"></s:textfield>
		  </div>
		  
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3">
				<div class="form-group">
		    <label for="exampleInputPassword1">Compression Rate %</label>
		   <s:textfield name="compressionrate" id="compressionrate"
					cssClass="form-control showToolTip" data-toggle="tooltip"
					placeholder="Enter Compression Rate"
					title="Enter  Compression Rate"></s:textfield>
		  </div>
			</div>
			<div class="col-lg-3 col-md-3 col-xs-3">
				<div class="form-group">
		    <label for="exampleInputPassword1">Name to display for online Booking</label>
		  	<s:textfield name="onlinename" id="onlinename"
					cssClass="form-control showToolTip" data-toggle="tooltip"
					placeholder="Name to display for online Booking"
					title="Name to display for online Booking"></s:textfield>
		  </div>
			</div>
		
		
		
	</div>
</div>
</div>
<div class="">
	
	<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;">
	<h4 class="coloback">Login Details</h4>
		<div class="col-lg-12 col-md-12" style="margin-top:10px;padding:0px;">
		<div class="col-lg-3 col-md-3 col-sm-3">
			<div class="form-group">
		    <label for="exampleInputEmail1">UserId<span class="red">*</span></label>
		    <s:textfield name="userId" id="userId"
					onchange="checkUserIdExist(this.value)"
					cssClass="form-control showToolTip" data-toggle="tooltip"
					placeholder="Enter User Id" title="alphabets and numbers no special characters except underscore('_') min 8 and max 20 characters" readonly="true"></s:textfield>
					<label id="errorUserId" style="color: red"></label>
		  </div>
		</div>			
		
	</div>
</div>
</div>
<div class="">
	
	<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;">
	<h4 class="coloback">Account Setting (Commission/Fee)</h4>
		<div class="col-lg-12 col-md-12" style="margin-top:10px;padding:0px;">	
		<div class="col-lg-3 col-md-3 col-sm-3">
			<div class="form-group">
		    <label for="exampleInputEmail1">Select Charge Type</label>
		    <s:select name="chargeType" id="chargeType" 
						 list="chargeSignList" listKey="id" listValue="sign"
						cssClass="form-control" onchange="setActionForAll()"></s:select>
		  </div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3">
			<div class="form-group">
		    <label for="exampleInputEmail1">DNA Charges</label>
		    <s:textfield id="dnaCharge" name="dnaCharge" labelposition="left" size="50"
						title="Enter dna charges in %" cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter DNA Charges in %" />
		  </div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3">
			<div class="form-group">
		    <label for="exampleInputEmail1">Complete Appointment Charges</label>
		    <s:textfield id="compAppCharge" name="compAppCharge" labelposition="left"
						title="Enter Complete Appointment Charges in %"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Complete Appointment Charges in %" />
		  </div>
		</div>
		
		<div class="col-lg-3 col-md-3 col-sm-3" >
					<div class="form-group">
		    <label for="exampleInputPassword1">Ipd Charges</label>
		    <s:textfield id="ipdCharge" name="ipdCharge" labelposition="left"
						title="Enter Ipd Charges in %"
						cssClass="form-control showToolTip" data-toggle="tooltip"
						placeholder="Enter Ipd Charges in %" />
		  </div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3" >
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
		   					 <label for="exampleInputPassword1">Investigation Access1</label>
		   						<s:hidden name="labname" id="labname"/>
		   						 
		   						 <div style="height: 100px; overflow: scroll;">
									<s:iterator value="labnameList">
								  <li><a href="#" class="small" data-value="option1" tabIndex="-1">
								  <input id="lab<s:property value="name"/>" onclick="setselectedlab()" type="checkbox" class="labch" value="<s:property value="name"/>"/>&nbsp;<span class="spandrop"><s:property value="name"/></span></a></li>
								  </s:iterator>
								 
								</div>
		  				</div>
					</div>
					
				</div>
		
		<%-- <div class="col-lg-3 col-md-3 col-sm-3">
			<div class="form-group" style="padding-top: 20px;">
			<s:checkbox name="hasDiary" id="hasDiary" />
		    <label for="exampleInputEmail1">Select to Display on Dashboard</label>
		    
		  </div>
		</div> --%>
		
		</div>
	</div>	
</div>
<div class="">
	<div class="col-lg-12 col-md-12 col-sm-12" style="padding:0px;">
	<h4 class="coloback">Preference</h4>
		<div class="col-lg-12 col-md-12" style="margin-top:10px;padding:0px;">	
		<div class="col-lg-3 col-md-3 col-sm-3">
			<div class="form-group">
			    <label for="exampleInputEmail1">Booking Confirmation Template</label>
			    <s:select name="appointmentbookingcontem" list="abctList"
					id="appointmentbookingcontem"
					cssClass="form-control showToolTip" data-toggle="tooltip"
					title="Select Appointment Booking Confirmation Template">
					</s:select>
		  	</div>
		</div>	
		<div class="col-lg-3 col-md-3 col-sm-3">
			<div class="form-group">
			    <label for="exampleInputEmail1">Appointment DNA Template</label>
			    <s:select name="appointmentbookingdnatem" list="adtList"
					id="appointmentbookingdnatem"
					cssClass="form-control showToolTip" data-toggle="tooltip"
					title="Select Appointment DNA Template">
					</s:select>
		  	</div>
		</div>	
		<div class="col-lg-3 col-md-3 col-sm-3">
			<div class="form-group">
			    <label for="exampleInputEmail1">Notify of new appointments booked via</label><br>
			    <s:checkbox id="apmremote" name="apmremote"></s:checkbox>
					Smart YUVICARE Remote
					<s:checkbox id="onlinebooking" name="onlinebooking"></s:checkbox>
					Online Booking
		  	</div>
		</div>	
		
		<div class="col-lg-3 col-md-3 col-sm-3">
		
		</div>	
			
	</div>
</div>
</div>
<div class="text-right" style="padding-bottom: 15px;padding-top: 15px;margin-right: 15px;">
<s:submit onclick="return validateEditDetail()" cssClass="btn btn-primary" value="Update" />
<a href="back1UserProfile" class="btn btn-primary">Back To List Page</a>
</s:form>
</div>
		</div>
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
	</div>			
										</div>


<script>	

window.onload = function(){
	
	var wardid =  document.getElementById('wardid').value;
	var wt = wardid.split(',');
	for(i=0;i<wt.length;i++){
		if(i!=0){
			document.getElementById('wd'+wt[i]).checked = true
		}
		
	}
	
	var labname =  document.getElementById('labname').value;
	var lt = labname.split(',');
	for(l=0;l<lt.length;l++){
		if(l!=0){
			document.getElementById('lab'+lt[l]).checked = true
		}
		
	}
	
}
</script>								</div>
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
						