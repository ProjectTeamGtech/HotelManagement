<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript" src="appointment/js/registeration.js"></script>

<div class="main_layout_content">
	
	<div id="contact_details" class="block_div">
	<div><b>Registeration</b></div>
	<span id="mandatory_message" class="error">Note:Fields marked with asterisk(*) are required.</span> 
	<span class="error"><font color="red"><s:actionerror id="server_side_error"/></font></span>
	<s:form action="saveBranch" id="registerfrm" theme="simple">
		
		 	<table width="100%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
		 	<col width="10%"/>
		 	<col width="2%"/>
		 	<col width="18%"/>
		 		<tr>
		 			<td>UserID<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
		 				<s:textfield id="userid" name="userId"  theme="simple"
						title="Minimum 6 characters, no special characters except underscore, starts with alphabet, no whitespaces between words,
					 	eg. sachin_verma, sachinverma, sachin123" />
					</td>
		 		</tr>
		 		<tr>
		 			<td>PassWord<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:password	id="password" name="password" theme="simple" 
		 				title="Minimum 6 characters, make it hard to guess" />
					</td>
		 		</tr>
		 		<tr>
		 			<td>Confirm PassWord<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:password id="confirmPassword" name="confirmPassword" key="label.confirmPassword" labelposition="left" required="true" 
						title="Re-enter above password" />
					</td>
		 		</tr>
		 		<tr>
		 			<td>Company Name<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:textarea id="companyName" name="companyName" key="label.name" labelposition="left" required="true" 
		 				title="Letters only, specify at least firstname and lastname, seperate fisrt name, middle name (if any) and last name with whitespace,
		 		 		eg. Rahul Dravid, Rahul Sharad Dravid" />	  
					</td>
		 		</tr>
		 		<tr>
		 			<td>Email ID<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:textfield id="email" name="email" key="label.email" labelposition="left" required="true" 
						title="Enter valid email id, it will be used to send job notifications, eg. yourname@gmail.com, yourname@yahoo.co.in" />
					</td>
		 		</tr>
		 		
		 		<tr>
		 			<td>Mobile No<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:textfield id="mobileNo" name="mobileNo" key="label.mobileNo" labelposition="left" required="true" 
  						title="Specify valid mobile number, eg 9876543210" />
					</td>
		 		</tr>
		 		<tr>
		 			<td>Landline No<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:textfield id="landline" name="landLine" key="label.mobileNo" labelposition="left" required="true" 
  						title="Specify valid mobile number, eg 9876543210" />
					</td>
		 		</tr>
		 		<tr>
		 			<td>Address<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:textarea id="address" name="address" key="label.address" labelposition="left" required="true" rows="3" cols="40"
						title="current address" />
					</td>
		 		</tr>
		 		<tr>
		 			<td>Country<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:if test="%{#countryList != 'null'}" >
		   					<s:select id="country" name="country" list="countryList" headerKey="0" headerValue="Select Country" 
		   					key="label.country" labelposition="left"  
							title="Select your country from list" />
   	   					</s:if>
					</td>
		 		</tr>
		 		<tr>
		 			<td>State<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:if test="%{#stateList != 'null'}" >
		  					<s:select id="state" name="state" list="stateList" headerKey="0" headerValue="Select State" 
		  					key="label.state" labelposition="left"  
							title="Select your state from list" />
						</s:if>
					</td>
		 		</tr>
		 		<tr>
		 			<td>City<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:if test="%{#cityList != 'null'}" >			
	      					<s:select id="city" name="city" list="cityList" headerKey="0" headerValue="Select City" required="true" 
	      					key="label.city" labelposition="left"  
							title="Select your city from list" />
						</s:if>
					</td>
		 		</tr>
		 		
		 		<tr>
		 			<td>Pin Code<span class="reqd-info">*</span></td>
		 			<td align="center">:</td>
		 			<td>
	 					<s:textfield id="pinCode" name="pinCode" key="PinCode" labelposition="left" required="true"
						title="Specify PinCode" />
					</td>
		 		</tr>
		 		
		 		<tr>
		 			<td>
		 				<table align="center">
		 					<tr>
		 						<td><s:submit theme="simple"/></td>
		 						<td><s:reset theme="simple"/></td>
		 					</tr>
		 				</table>
		 			</td>
		 		</tr>
		 		
		 	</table>
		 	
		 	
			
		
	</s:form>
</div>										
 	
 	
		
</div>