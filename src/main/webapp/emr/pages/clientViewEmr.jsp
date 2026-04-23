<%@ taglib uri="/struts-tags"  prefix="s"%>

<table class="table table-hover table-condensed table-bordered" id="clientdata" border="">
	
	<tr><th><h4><b>Personal Details</b></h4></th></tr>
	<tr><td>Patient Name : <s:property value="firstName" /> <s:property value="lastName" /></td>
	<td>DOB : <s:property value="dob" /></td></tr>
	<tr><td>Gender : <s:property value="gender"/></td>
	<td>Practitioner : <s:property value="practitionerName" /></td></tr>
	<tr><td>Payee : <s:property value="whopay" /></td></tr>
	<tr><th><h4><b>Contact Details</b></h4></th></tr>
	<tr><td>Address : <s:property value="address" /></td>
	<td>Town : <s:property value="town" /></td></tr>
	<tr><td>PostCode : <s:property value="postCode" /></td>
	<td>Home No : <s:property value="homeNo" /></td></tr>
	<tr><td>Work No : <s:property value="workNo" /></td>
	<td>Mobile No : <s:property value="mobNo" /></td></tr>
	<tr><td>Email ID : <s:property value="email" /></td></tr>
	<tr><th><h4><b>Other Details</b></h4></th></tr>
	<tr><td>Employer Name : <s:property value="employerName" /></td>
	<td>Occupation : <s:property value="occupation" /></td></tr>
	<tr><td>Source Of Intro : <s:property value="sourceOfIntro"/></td>
	<td>Conditions : <s:property value="treatmentType" /></td></tr>
	<tr><th><h4><b>Third Party Details</b></h4></th></tr>
	<tr><td>GP Name : <s:property value="gpname" /></td>
	<td>Insurance Co : <s:property value="thirdPartyCompanyName" /></td></tr>
</table>