<%@taglib uri="/struts-tags" prefix="s"%>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li>Practitioner</li>
			<li class="active"> Practitioner Profile </li>
		</ol>
	</div>
</div>	

<div class="row" style="padding-left: 1071px; margin-top: 15px;">
<s:form action="editUserProfile" id="userfrm" theme="simple">
<s:hidden name="id" id="id"></s:hidden>
<s:submit theme="simple" cssClass="btn btn-primary" value="Update"  />
</s:form>		
</div>

<br>


<div class="panel panel-primary">
	<div class="panel-body">
		<div class="row">
			<div class="col-lg-12">
				<h3>User Details</h3>
			</div>
		</div>	
		<br/>	
		<div class="row">
			<div class="col-lg-12">
				<div class="form-group">
					<label>Initial : </label>
					<s:property value="initial"/>
				</div>
				<div class="form-group">					
					<label>First Name : </label>
					<s:property value="firstname"/>					
				</div>
				<div class="form-group">					
					<label>Last Name : </label>					
					<s:property value="lastname"/>					
				</div>
				<div class="form-group">					
					<label>Job Title : </label>					
					<s:property value="jobtitle"/>				
				</div>
				<div class="form-group">					
					<label>Discipline : </label>					
					<s:property value="diciplineName"/>
				</div>				
			</div>
		</div>
	</div>
</div>

<div class="panel panel-primary">
	<div class="panel-body">	
		<div class="row">
			<div class="col-lg-12">
				<h3>User Setup</h3>
			</div>
		</div>	
		<br/>	
		<div class="row">
			<div class="col-lg-12">
				<div class="form-group">
					<label>Diary Color : </label>
					<s:property value="diarycolor"/>
				</div>
				<div class="form-group">
					<label>Diary Column Position : </label>
					<s:property value="diarycolumnposition"/>
				</div>
				<div class="form-group">
					<label>Compression Rate % : </label>
					<s:property value="compressionrate"/>
				</div>
				<div class="form-group">
					<label>Name to display for online Booking : </label>
					<s:property value="onlinename"/>
				</div>
				<div class="form-group">
					<label>This User :</label>
				</div>				
				<div class="form-group">
					<s:checkbox name="isavailableonline" id="isavailableonline"	fieldValue="isavailableonline"
					 value="%{isavailableonline}" disabled="true" theme="simple" cssClass="chkGrp"/>
					<s:label value="isavailableonline"></s:label>					
				</div>				
			</div>
		</div>
	</div>
</div>
		
<div class="panel panel-primary">
	<div class="panel-body">
		<div class="row">
			<div class="col-lg-12">
				<h3>Login Details</h3>
			</div>
		</div>
		<br/>		
		<div class="row">
			<div class="col-lg-12">
				<div class="form-group">
					<label>UserId : </label>
					<s:property value="userId"/>
				</div>
			</div>
		</div>
	</div>
</div>
			
<div class="panel panel-primary">
	<div class="panel-body">			
		<div class="row">
			<div class="col-lg-12">
				<h3>Preference : </h3>
			</div>
		</div>
		<br/>		
		<div class="row">
			<div class="col-lg-12">		
				<div class="form-group">
					<label>Appointment Booking Confirmation Template : </label>
					<s:property value="appointmentbookingcontem"/>
				</div>
				<div class="form-group">
					<label>Appointment DNA Template : </label>
					<s:property value="appointmentbookingdnatem"/>
				</div>
				<div class="form-group">
					<label>Contact E-mail Address : </label>
					<s:property value="email"/>
				</div>
				<div class="form-group">
					<label>Mobile No (for SMS Confirmation) : </label>
					<s:property value="mobile"/>
				</div>
				<div class="form-group">
					<label>Notify of new appointments booked via : </label>					
				</div>	
				<div class="form-group">
					<s:checkbox name="apmremote" id="apmremote" fieldValue="apmremote" 
						value="%{apmremote}" disabled="true" theme="simple" cssClass="chkGrp"/>	
													
					<s:label value="APM Remote"></s:label>							
				</div>
				<div class="form-group">
					<s:checkbox name="onlinebooking" id="onlinebooking" fieldValue="onlinebooking" 
						value="%{onlinebooking}" disabled="true" theme="simple" cssClass="chkGrp"/>	
													
					<s:label value="Online Booking"></s:label>							
				</div>
							
			</div>
		</div>
		
	</div>
</div>