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


</script>
<link href="diarymanagement/css/tabStyle.css" rel="stylesheet" type="text/css" />
<s:form action="saveeqclientInvestigation" id="patientFormeq"  theme="simple">


	
	<div class="">
				<div class="">
		<div class="row">
		<div class="col-lg-12 col-md-12">
		<div class="col-lg-4 col-md-4 col-sm-4">
		<label>First Name</label><label><span class="text-danger">*</span></label>
		<div class="form-inline">
		  <div class="form-group">
		    <label class="sr-only" for="exampleInputEmail3">Initial</label>
		    <s:select id="title" name="title" list="initialList" title="Select" theme="simple" cssClass="form-control showToolTip" data-toggle="tooltip" headerValue="Select" headerKey="" />
		  </div>
		  <div class="form-group" Style="width:70%;">
		    <label class="sr-only" for="exampleInputPassword3">First Name</label>
		    <s:textfield id="firstName" name="firstName" title="Enter First Name"  theme="simple"  cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter First Name" cssStyle="width:98%;"/>
				<label id = "fnameError" class="text-danger"></label>
		  </div>
		</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4">
		<label>Middle Name</label>
				<s:textfield id="middleName" name="middleName" title="Enter Middle Name"  theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Middle Name"/>
				<label id = "mnameError" class="text-danger"></label>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4">
		<label>Last Name</label><label><span class="text-danger">*</span></label>
				<s:textfield id="lastName" name="lastName" title="Enter Last Name"  theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Last Name" />
				<label id = "lnameError" class="text-danger"></label>
		
		</div>
		
		</div>
			
		</div>
		
		<div class="row">		
			<div class="col-lg-12 col-md-12">
			<div class="col-lg-4 col-md-4">
			<label>DOB</label><label><span class="text-danger">*</span></label>
				<s:textfield id = "dob" name="dob" cssClass="form-control showToolTip" readonly = "true"
				data-toggle="tooltip" title="Select DOB" theme="simple" placeholder = "Select DOB"></s:textfield>
				<label id = "dobError" class="text-danger"></label>
			</div>
			<div class="col-lg-4 col-md-4">
			<label>Gender</label>			
				<s:select id="gender" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip" title="Select Gender"  headerKey="0" headerValue="Select"/><label id = "genError" class="text-danger"></label>
			</div>
			<div class="col-lg-4 col-md-4"></div>
			</div>
		</div>	
		<div class="row">
			<div class="col-lg-12 col-md-12">
				<div class="col-lg-8 col-md-8">
				<label>Address</label><label><span class="text-danger">*</span></label>
				<s:textfield id="address" name="address" title="Enter Address" theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip"  placeholder = "Enter Address" />
				<label id = "addressError" class="text-danger"></label>			
				
				</div>
				<div class="col-lg-4 col-md-4">
				<label>Town</label><span class="text-danger">*</span>			
				<s:textfield id="town" name="town" title="Enter Town" theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip"  placeholder = "Enter Town" onblur="initialFirstCap(this);"/>
				<label id = "townError" class="text-danger"></label>
				</div>
			</div>
			
			
		</div>	
		</div>
		</div>		  
	
		<div class="">
				<div class="">
				<div class="row">
				<div class="col-lg-12 col-md-12">
				<div class="col-lg-4 col-md-4">
				<label>Mobile No</label>		
				<s:textfield id="mobNo" name="mobNo" title="Enter Mobile No" theme="simple" cssClass="form-control showToolTip"
				data-toggle="tooltip" placeholder = "Enter Mobile No" />
				<label  id = "mobNoError1" class="text-danger"></label>
				</div>
				<div class="col-lg-4 col-md-4">
				<label>Email</label>			
				<s:textfield id="email" name="email" title="Enter Email" theme="simple" cssClass="form-control showToolTip"
				data-toggle="tooltip" placeholder = "Enter Email" />
				<label id = "emailError1" class="text-danger"></label>
				</div>
				</div>	
				
			</div>	 				
			</div>
			</div>			
		   <div class="">
				<div class="">
				<div class="row">
				<div class="col-lg-12 col-md-12">
				<div class="col-lg-4 col-md-4">
				<label>Doctor Name</label><label><span class="text-danger">*</span></label><br>			
				<input type="text" value="Self Managed" disabled="disabled" class="form-control">
				</div>
				<div class="col-lg-4 col-md-4">
				<label>Select Condition</label><label><span class="text-danger">*</span></label>			
				<s:select id = "treatmentType" name = "treatmentType" list="condtitionList" headerValue="Select Condition" headerKey="0" listKey="id" listValue="treatmentType" cssClass="form-control showToolTip chosen-select"
									data-toggle="tooltip" theme="simple" ></s:select>
			    <label  id="conditionError" class="text-danger"></label>
				</div>		
				</div>
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
			