<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" charset="utf-8">

    //Capitalize first letter while typing in side of input field
    jQuery(document).ready(function($) {
        $('#selector').keyup(function(event) {
            var textBox = event.target;
            var start = textBox.selectionStart;
            var end = textBox.selectionEnd;
            textBox.value = textBox.value.charAt(0).toUpperCase() + textBox.value.slice(1);
            textBox.setSelectionRange(start, end);
        });
    });
    
    
    
    $("#dob").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});
    
    function allnumeric(dd)  
    {  
      var numbers = /^[0-9]+$/;  
      if(!dd.match(numbers))  
      {  
     	alert('only number permitted...');  
      }  
      else 
      {
            var today= new Date();
            var yyyy = today.getFullYear();
            var year= parseInt(yyyy)- parseInt(dd);
            var dd = today.getDate();
    		var mm = today.getMonth()+1; //January is 0!
            
            if(dd<10) {
    			dd='0'+dd
    		} 

    		if(mm<10) {
    			mm='0'+mm
    		} 

    		var str = dd+'/'+mm+'/'+year;
    		document.getElementById("dob").value=str;
      		document.getElementById("days").value=0;
      }
      
    }
    $("#addPatientDiv").draggable({
        handle: ".modal-header"
    });
</script>
<%LoginInfo loginfo1223 = LoginHelper.getLoginInfo(request);%>
<script type="text/javascript" src="inventory/js/addvendor.js"></script>
<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>
<link href="diarymanagement/css/tabStyle.css" rel="stylesheet" type="text/css" />


<s:form action="saveinvestigationNewPatientInvestigation" id="patientForm"  theme="simple">


<style>
	.tab-content {
    min-height: 400px !important;
}
</style>

			
	
	
			
		<div class="row">
				<div class="col-lg-12 col-md-12">
					<div class="">
						<div class="col-lg-12" style="background-color: #8e8e8e;color:#fff;padding: 4px 0px 0px 6px;margin-bottom: 10px;">
							<label>Name/Address</label>
						</div>
					</div>
				</div>
		
		
		<div class="col-lg-12 col-md-12">
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<div class="form-group">
					<label>Title</label><label><span class="text-danger">*</span></label>
					<s:select id="title"  onchange="setGender(this.value)" name="title" list="initialList" title="Select" theme="simple" cssClass="form-control" data-toggle="tooltip" headerValue="Select" headerKey="" />
					<label id = "titleError" class="text-danger"></label>
				</div>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<div class="form-group">
					<label>First Name</label><label><span class="text-danger">*</span></label>
					<s:textfield id="firstName" name="firstName" title="Enter First Name"  theme="simple"  cssClass="form-control "
										data-toggle="tooltip" placeholder="Enter First Name"/>
					<label id = "fnameError" class="text-danger"></label>
				</div>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<div class="form-group">
					<label>Middle Name</label>
					<s:textfield id="middleName" name="middleName" title="Enter Middle Name"  theme="simple" cssClass="form-control "
										data-toggle="tooltip" placeholder="Enter Middle Name"/>
					<label id = "mnameError" class="text-danger"></label>
				</div>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<div class="form-group">
					<label>Last Name</label><label><span class="text-danger">*</span></label>
					<s:textfield id="lastName" name="lastName" title="Enter Last Name"  theme="simple" cssClass="form-control "
										data-toggle="tooltip" placeholder="Enter Last Name" />
					<label id = "lnameError" class="text-danger"></label>
				</div>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
				<div class="form-group">
					<label>DOB</label><label><span class="text-danger">*</span></label>
					<s:textfield id="dob" name="dob" title="Select DOB"  theme="simple" onchange="getAgendDays(this.value)" cssClass="form-control "
										data-toggle="tooltip" placeholder="Select DOB" />
					<label id = "dobError" class="text-danger"></label>
				</div>
			</div>
		</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12 col-xs-12 col-md-12">
				<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
				<div class="form-group">
					<label>Age</label><label><span class="text-danger">*</span></label>
					<s:textfield id = "age" name="age" cssClass="form-control " 
					 title="Enter Age" theme="simple" placeholder = "Enter Age" onchange="allnumeric(this.value)"></s:textfield>
					<label id = "ageError" class="text-danger"></label>
				</div>
			</div>
			<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
				<div class="form-group">
					<label>Month</label>
					<s:textfield id = "month" name="month" cssClass="form-control " 
					 title="Enter days" theme="simple" readonly="true"></s:textfield>
					<label id = "daysError" class="text-danger"></label>
				</div>
			</div>
			<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
				<div class="form-group">
					<label>Days</label>
					<s:textfield id = "days" name="days" cssClass="form-control " 
					 title="Enter days" theme="simple" readonly="true"></s:textfield>
					<label id = "daysError" class="text-danger"></label>
				</div>
			</div>
			<div class="col-lg-2 col-md-2">
				<div class="form-group">
					<label>Gender<span class="text-danger">*</span></label>			
					<s:select id="gender" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control "
										data-toggle="tooltip" title="Select Gender"  headerKey="" headerValue="Select"/>
					<label  id = "genderError1" class="text-danger"></label>
				</div>
			</div>
		
				          	<div class="col-lg-2 col-md-2">
					<div class="form-group">
						<label>Marital Status</label>		
						<s:select id="maritalsts" name="maritalsts" list="{'Single','Married','Divorced','Separated'}" headerKey="0" headerValue="Select" theme="simple" cssClass="form-control "/>
				          	
					</div>
				</div>
			<div class="col-lg-2 col-md-2">
					<div class="form-group">
						<label>Mobile No</label><label><span class="text-danger">*</span></label>		
						<s:textfield id="mobNo" name="mobNo" title="Enter Mobile No" theme="simple" cssClass="form-control "
						data-toggle="tooltip" placeholder = "Enter Mobile No" maxlength="10"/>
						<label  id = "mobNoError1" class="text-danger"></label>
					</div>
				</div>
				<div class="col-lg-2 col-md-2">
					<div class="form-group">
						<label>Email</label>			
						<s:textfield id="email" name="email" title="Enter Email" theme="simple" cssClass="form-control "
						data-toggle="tooltip" placeholder = "Enter Email" />
						<label id = "emailError1" class="text-danger"></label>
					</div>
				</div>	
			</div>
		</div>
		
	
		<div class="">
			<div class="col-lg-6 col-md-6">
				<div class="form-group">
				    <label>Address</label><label><span class="text-danger">*</span></label>
					<s:textfield id="address" name="address1" title="Enter Address" theme="simple" cssClass="form-control "
										data-toggle="tooltip"  placeholder = "Enter Address" />
					<label id = "addressError" class="text-danger"></label>			
					<br>
					<s:textfield cssStyle="display:none;" id="secondLineaddress" name="secondLineaddress" title="Enter 2nd Line Address" 
										cssClass="form-control showToolTip ppstyle" data-toggle="tooltip" placeholder="Enter 2nd Line Address" />	
				</div>
			</div>
			<div class="col-lg-2 col-md-2">
				<div class="form-group" id="statediv">
					<label>State</label><span class="text-danger">*</span>			
					<s:select list="statelist" theme="simple" cssClass="form-control  chosen-select" onchange="getCitiesajax(this.value)" name="state" id="state"
							 listKey="name" listValue="name" headerKey="0" headerValue="Select State"  />
					<label id = "stateError" class="text-danger"></label>					
				</div>							
			</div>
			<div class="col-lg-2 col-md-2">
			<%if(loginfo1223.isLmh()){ %>
			<label>City</label><span class="text-danger">*</span>
			<div class="form-group" id="citydiv">
					<s:select list="citylist" theme="simple"  listKey="city" listValue="city" id="town" onchange="getStateAjaxnew(this.value)" cssClass="form-control  chosen-select" 
							headerKey="0" headerValue="Select City" name="town" />
					<label id = "townError" class="text-danger"></label>
				</div>			
			<%}else{ %>
			<div class="form-group" id="citydiv">
					<label>City</label><span class="text-danger">*</span>			
					<s:select list="citylist" theme="simple"  listKey="city" listValue="city" id="town" onchange="getStateAjaxnew(this.value)" cssClass="form-control  chosen-select" 
							headerKey="0" headerValue="Select City" name="town" />
					<label id = "townError" class="text-danger"></label>
				</div>			
			<%} %>
			
								
			</div>
			<div class="col-lg-3 col-md-3"></div>		
		</div>	
		
			  
				
		
				
								
		
				
			<div class="">
					<div class="">
						<div class="col-lg-12" style="background-color: #8e8e8e;color:#fff;padding: 4px 0px 0px 6px;margin-bottom: 10px;">
							<label>Doctor Details</label>
						</div>
					</div>
				</div>
							
		
				
		<div class="">
			<div class="col-lg-3 col-md-3">
				<div class="form-group">
					<label>Doctor Name</label><label><span class="text-danger">*</span></label>			
						<s:textfield id = "doctorname" name ="doctorname" cssClass="form-control" data-toggle="tooltip" theme="simple" placeholder = "Enter Doctor Name"
						/>
					<label  id = "doctorError" class="text-danger"></label>
				</div>
			</div>
			<div class="col-lg-3 col-md-3">
				<div class="form-group">
					<label>Department</label><label><span class="text-danger">*</span></label>			
					 <s:select id="diciplineName" name="diciplineName" list="disciplineList" listKey="discipline" listValue="discipline"
						headerKey="0" headerValue="Select Specialization"
						title="Select Specialization" 
						cssClass="form-control chosen-select" data-toggle="tooltip" />
						<label  id = "departmentError" class="text-danger"></label>
				</div>
			</div>		
			<div class="col-lg-3 col-md-3"></div>	
			<div class="col-lg-3 col-md-3"></div>	
		</div>	
		
			
   	  		
		
		
					
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


			