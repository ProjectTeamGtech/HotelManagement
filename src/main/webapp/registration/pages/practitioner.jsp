

<%@taglib uri="/struts-tags" prefix="s"%>
<style>
.card {
	box-shadow: 8px 4px 8px 8px rgba(0, 0, 0, 0.2);
	transition: 0.3s;
	width: 100%;
	padding:10px;
	display: flex;
	
}

.card:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.container-data {
	padding: 4px 16px;
	text-align: center;;
}

.prof_pic {
	padding: 10px;
	
	display: inline-block;
	
}
.prof_pic_data{
text-align: center;
}
.details {
	padding-left: 10px;
}
.prof_data{
display: inline-block;
font-size: 14px;
padding-left: 20px;
}
.prof_data label{
text-transform: uppercase;
padding-right: 4px;
}
</style>

<div class="row details">
	<h4>
		<b><s:property value='userDetails.fullname' /></b>
	</h4>
</div>

<div class="card">
	<div class='prof_pic'>
		<img src="cicon/profiledoc.jpg" class="img-responsive">
		<div class='prof_pic_data'>
			<h4>
				<b><s:property value='userDetails.fullname' /></b>
			</h4>
			<p>
				<s:property value='userDetails.specialization' />
				<s:property value='userDetails.qualification' />
			</p>
		</div>
		
	</div>
<div class='prof_data'>
<label> Clinic Name : </label><span><s:property value='userDetails.clinicname' /> </span><br>
<label> JOBTITLE : </label><span><s:property value='userDetails.jobtitle' /> </span><br>
<label> Contact Number : </label><span><s:property value='userDetails.mobile' /> </span><br>
<label> E-MAIL : </label><span><s:property value='userDetails.email' /> </span><br>

<label> Registration Number : </label><span><s:property value='userDetails.licenceId' /> </span><br>
<label> Date Of Birth : </label><span><s:property value='userDetails.dob' /> </span><br>

</div>
	

</div>













