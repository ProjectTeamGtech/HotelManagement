<%@taglib uri="/struts-tags" prefix="s"%>
<div class="row" style="text-align: center;">
	<div class="col-lg-12">
		<label style="font-size: 22px"><s:property value="clinicName"/></label>
	</div>
</div>
<div style="padding-top: 11px;">	
		<div class="col-lg-12 bg-primary" style="height: 37px;padding-top: 10px;padding-top: 4px;font-size: 20px"">
			<label><s:property value="title" /> <s:property
					value="firstName" /> <s:property value="lastName" /> DOB: <s:property
					value="dob" /></label>
		</div>
		
		
		<table width ="100%">
			<col width="30%"/>
			<col width="30%"/>
			<col width="30%"/>
			<tr class="bg-grey" style="height: 46px;padding-top: 13px;">
				<td style="padding-left: 10px;">
					<label>Client No:</label> 000<s:property value="id" />
				</td>
				<td> <label>Practitioner:</label>
						<s:property value="practitionerName" /></td>
				<td><label>Payee:</label>
						<s:property value="whopay" /></td>
			</tr>
			<tr>
				<td colspan="3" style="background-color: #d9edf7;height: 27px;padding-top: 5px;padding-left: 10px;"> <label>Name and Address</label> </td>
			</tr>
			<tr>
				<td style="padding-left: 37px;"><label>Title:</label>
				<s:property value="title" /></td>
				<td><label>First Name:</label>
				<s:property value="firstName" /></td>
				<td><label>Last Name:</label>
				<s:property value="lastName" /></td>
			</tr>
			
			<tr>
				<td style="padding-left: 37px;">
					<label>Address:</label>
				</td>
				<td>
					<s:property value="address" />
				</td>
				<td></td>
			</tr>
			
			<tr>
				<td style="padding-left: 37px;">
					<label>Town:</label>
				</td>
				<td>
					<s:property value="town" />
				</td>
				<td></td>
			</tr>
		
			<tr>
				<td style="padding-left: 37px;">
					<label>Post Code:</label>
				</td>
				<td>
					<s:property value="postCode" />
				</td>
				<td></td>
			</tr>
			
			<tr>
				<td colspan="3" style="background-color: #d9edf7;height: 27px;padding-top: 5px;"padding-left: 10px;> <label>Contact Details</label> </td>
			</tr>
			
			<tr>
				<td style="padding-left: 37px;">
					<label>Telephone No:</label>
				</td>
				<td>
					
				</td>
				<td></td>
			</tr>
			<tr>
				<td style="padding-left: 37px;">
					<label>Mobile:</label>
				</td>
				<td>
					<s:property value="mobNo" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td style="padding-left: 37px;">
					<label>Email ID:</label>
				</td>
				<td>
					<s:property value="email" />
				</td>
				<td></td>
			</tr>
			
			<tr>
				<td colspan="3" style="background-color: #d9edf7;height: 27px;padding-top: 5px;padding-left: 10px;"> <label>Other Details</label> </td>
			</tr>
			
			<tr>
				<td style="padding-left: 37px;">
					<label>Employer Name:</label>
				</td>
				<td>
					
				</td>
				<td></td>
			</tr>
			<tr>
				<td style="padding-left: 37px;">
					<label>Occupation:</label>
				</td>
				<td>
					<s:property value="occupation" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td style="padding-left: 37px;"> 
					<label>Source Of Introduction:</label>
				</td>
				<td>
					<s:property value="sourceOfIntro" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td style="padding-left: 37px;">
					<label>GP Details:</label>
				</td>
				<td>
					<s:property value="gpname" />, <s:property value="gpAddress"/>, <s:property value="gpTown"/>, <s:property value="gpPostCode"/>
				</td>
				<td></td>
			</tr>
			
			<tr>
				<td colspan="3" style="background-color: #d9edf7;height: 50px;padding-top: 4px;padding-left: 10px;"> <label>Note</label> </td>
			</tr>
			
			
			
	
		</table>
		<div class="col-lg-12 bg-primary" style="text-align: center;height: 25px;padding-top: 5px;">
			<label><s:property value="declarationTitle"/></label>
		</div>	
		
	<%=session.getAttribute("declarationNotes")%> 	
		
</div>			
		




