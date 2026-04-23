<%@taglib uri="/struts-tags" prefix="s" %>

<link href="diarymanagement/css/tabStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>
	
	
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Title</label><label><span class="text-danger">*</span></label>
				<s:select id="title1" name="title" list="initialList" title="Select" theme="simple" cssClass="form-control showToolTip" data-toggle="tooltip"  />
				<label  id = "titleError" class="text-danger"></label>
			</div>
			<div class="col-lg-3 col-md-3">
				<label>First Name</label><label><span class="text-danger">*</span></label>
				<s:textfield id="firstName1" name="firstName" title="Enter First Name" theme="simple"  cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter First Name"/>
				<label  id = "firstNameError" class="text-danger"></label>
			</div>
			<div class="col-lg-3 col-md-3">
				<label>Middle Name</label>
				<s:textfield id="middleName1" name="middleName" title="Enter Middle Name" theme="simple"  cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Middle Name"/>
				<label  id = "middleNameError" class="text-danger"></label>
			</div>
			<div class="col-lg-3 col-md-3">
				<label>Last Name</label><label><span class="text-danger">*</span></label>
				<s:textfield id="lastName1" name="lastName" title="Enter Last Name"  theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder="Enter Last Name" />
				<label id = "lastNameError" class="text-danger"></label>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Gender</label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:select id="gender1" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip" title="Select Gender"  headerKey="0" headerValue="Select"/>
									

			</div>
			<div class="col-lg-2 col-md-2">
				<label>DOB</label><label><span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-3 col-md-3">
				<s:textfield id = "dob1" name="dob" cssClass="form-control showToolTip"
									data-toggle="tooltip" title="Select DOB" theme="simple" placeholder = "Select DOB"></s:textfield>
				<label  id = "dobError" class="text-danger"></label>

			</div>
		</div>	
		
				  
	
		<hr>						
		
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Address</label><label><span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-10 col-md-10">
				<s:textarea id="address1" name="address" value="" title="Enter Address" theme="simple" cssClass="form-control showToolTip" onkeyup="allFirstInitCap(this.id);"
									data-toggle="tooltip"  placeholder = "Enter Address"/>
				<br>
				<s:textfield id="secondLineaddress1" name="secondLineaddress" title="Enter 2nd Line Address" onkeyup="allFirstInitCap(this.id);"
									cssClass="form-control showToolTip ppstyle" data-toggle="tooltip" placeholder="Enter 2nd Line Address" />										
				<label  id = "addressError" class="text-danger"></label>

			</div>
		</div>				
			</br>				
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Town</label><span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-4 col-md-4">
				<s:textfield id="town1" name="town" title="Enter Town" theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip"  placeholder = "Enter Town"/>
				<label  id = "townError" class="text-danger"></label>

			</div>
			<div class="col-lg-2 col-md-2">
				<label>Post Code</label><label><span class="text-danger"></span></label>
			</div>
			
			<div class="col-lg-4 col-md-4">
				<s:textfield id="postCode1" name="postCode" theme="simple" title="Enter PostCode" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder = "Enter PostCode" onblur="initialCap(this);" />

			<label  id = "postCodeError" class="text-danger"></label>
			</div>
		</div>	
		
							
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>State</label>
			</div>
			<div class="col-lg-4 col-md-4">
				<s:textfield id="county1" name="county" title="Enter State" theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder = "Enter State" />
			</div>
			<div class="col-lg-2 col-md-2">
				<label>Country</label><label><span class="text-danger"></span></label>
			</div>
			<div class="col-lg-4 col-md-4">
				<s:if test="%{#countryList != 'null'}" >
		   					<s:select id="country1" name="country" list="countryList" headerKey="0" headerValue="Select Country" 
		   					labelposition="left"  
							title="Select your country from list" theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip"  />
   	   					</s:if>
   	   				<label  id = "countryError" class="text-danger"></label>



			</div>
			
		</div>		
				
		
		
		<hr>
			<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Mobile No</label>
			</div>
			<div class="col-lg-4 col-md-4">
				<s:textfield id="mobNo1" name="mobNo" title="Enter Mobile No" theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder = "Enter Mobile No" onchange=""/>
							<label  id = "mobNoError" class="text-danger"></label>


			</div>
			<div class="col-lg-2 col-md-2">
				<label>Email</label>
			</div>
			<div class="col-lg-4 col-md-4">
				<s:textfield id="email1" name="email" title="Enter Email" theme="simple" cssClass="form-control showToolTip"
									data-toggle="tooltip" placeholder = "Enter Email" onchange="checkEmailValidation1(this.value)"/>
							<label name = "emailError" id = "emailError" class="text-danger"></label>


			</div>
			</div>
			<div class="row">
			<div class="col-lg-2 col-md-2">
					<label>Referred By</label><label class="text-danger reqd-info">*</label>
			</div>
		
			<div class="col-lg-4 col-md-4">
								<s:select id="reference1" name="reference" list="refrenceList"
									headerKey="0" headerValue="Select Reference"
									title="Select your Reference from list"
									cssClass="form-control showToolTip" data-toggle="tooltip"
									onchange="showOtherRefernce()" />
									<label id = "referenceError" class="text-danger"></label>
									<br>
			<div id="reference_other" style="display: none;">
									<s:textfield id="otherRef1" name="otherRef" type="text"
										onchange="addOtherReference(this.value)"
										cssClass="form-control showToolTip" data-toggle="tooltip"
										placeholder="Enter Other Reference" />
								</div>
			</div>					
			
			</div>


							
		<hr>	
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>GP</label><label><span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-8 col-md-8">
				<s:select id = "gpname" name = "gpname" list="gpList" headerValue="Select GP" headerKey="0" listKey="id" listValue="gpname" cssClass="form-control showToolTip chosen"
									data-toggle="tooltip" theme="simple"></s:select>

			</div>
		</div>	
		<br>		
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Pay By</label><label><span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-8 col-md-8">
				<s:select id = "whopay1" name = "whopay" list="{'Self','Third Party'}" headerValue="Select" headerKey="0" onchange="showHide()" cssClass="form-control showToolTip"
									data-toggle="tooltip" theme="simple"></s:select>
						<label  id = "whopayError" class="text-danger"></label>

			</div>
		</div>	
					
   	   			
   <div id="hidden_html" style="display:none;">					
 
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Type</label><label><span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-4 col-md-4">
				<s:select id="type1" name="type" list="thirdPartyTypeList" listKey="id" listValue="type" headerKey="0" cssClass="form-control showToolTip"
									data-toggle="tooltip" title="Select Third Party Type"  headerValue="Select Type" onchange="setTypeName(this.value)" theme="simple"/>
					<label id = "typeError" class="text-danger"></label>

			</div>
			<div class="col-lg-2 col-md-2">
				<label>Third Party Name</label><label><span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-4 col-md-4">
				<s:select cssClass="form-control showToolTip" data-toggle="tooltip" title="Select Company" id="typeName1" name="typeName" list="thirdPartyTypeNameList" listKey="id" listValue="thirdPartyCompanyName" headerKey="0" theme="simple" headerValue="Select 3rd Party Name"/>
					<label  id = "typeNameError" class="text-danger"></label>
					<br>
			<input type="button" onclick="setTypeField()" class="btn btn-primary" value="Add Third Party">
			</div>
			
			
		</div>  
		
		<br>
		<div class="row">
			<div class="col-lg-2 col-md-2">
				<label>Policy No.</label><label><span class="text-danger">*</span></label>
			</div>
			<div class="col-lg-4 col-md-4">
				<s:textfield name = "policyNo" id = "policyNo1" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder = "Enter Policy No." title="Enter Policy No." theme="simple"/>
						<label id = "policyNoError" class="text-danger"></label>

			</div>
			<div class="col-lg-2 col-md-2">
				<label>Expiry Date</label>
			</div>
			<div class="col-lg-4 col-md-4">
				<s:textfield name = "expiryDate" id = "expiryDate1" cssClass="form-control showToolTip" data-toggle="tooltip" placeholder = "Enter Expiry Date." theme="simple"/>
						<label  id = "expiryDateError" class="text-danger"></label>
			</div>
		</div>			
					
					
	</div>				
					
					
					
					
					
					 
					
					
					
					
			   
		
		
		
	