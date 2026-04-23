<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<div class='row'>
<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12'  style="padding-top: 10px">
		<a style="float: right;" class="btn btn-primary" href="editpatientnewClient?selectedid=<s:property value="clientData.id"/>">Edit Profile</a>
</div>
</div>
<div class='row'>
<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12'>
<a href="#" onclick="openSamll('liveData/document/<s:property value='clientData.profileImg'/>')">
<img alt="" src="liveData/document/<s:property value='clientData.profileImg'/>" style="height: 100px;width: 100px;">
</a>
</div>
<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>UHID : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.abrivationid"/>  </label>
	</div>
</div>
<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Name : </label>
</div>
<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.title"/> <s:property value="clientData.firstName"/>  <s:property value="clientData.lastName"/> </label>
</div>
</div>

<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Gender : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.gender"/>  </label>
	</div>
</div>	

<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>DOB : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.dob"/>  </label>	
	</div>
</div>	

	<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Age : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.age3"/> 
	<s:if test="clientData.age<=16">
		(Minor)
	</s:if>
	<s:else>
		(Major)
	</s:else>
	</label>	
	</div>
</div>	

<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Contact No : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.mobNo"/>  </label>
	</div>
</div>	

 <div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Email ID : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.email"/>  </label>
	</div>
</div>	

<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Address : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.address"/>,<s:property value="clientData.town_village"/>,<s:property value="clientData.town"/>,<s:property value="clientData.county"/>  </label>
	</div>
</div>


<s:if test="clientData.pincode!=''">
<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Postal Code (Zip / Pin) : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.pincode"/>  </label>	
	</div>
</div>	

</s:if>

<s:if test="clientData.relativename!=''">
<s:if test="clientData.relativename!=null">
<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px;background-color: lavender;">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Parent / Guardian Name : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.relativename"/>  </label>
	</div>
</div>

<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px;background-color: lavender;">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Relation with Patient's: </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.relation"/>  </label>	
	</div>
</div>

<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px;background-color: lavender;">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Parent / Guardian Contact Number: </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.relativeno"/>  </label>
	</div>
</div>

<s:if test="clientData.relativeImg!=''">
<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px;background-color: lavender;">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Parent / Guardian Identity Document : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
		<a href="#" onclick="openSamll('liveData/document/<s:property value='clientData.relativeImg'/>')">
			<img alt="" src="liveData/document/<s:property value='clientData.relativeImg'/>" style="height: 100px;width: 100px;">
		</a>
	</div>
</div>

</s:if>

</s:if>
</s:if>




</div>
<div class='row'>

<s:if test="clientData.docType!=''">
<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' style="padding-top: 10px">
	<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
	<label>Document : </label>
	</div>
	<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
	<label><s:property value="clientData.docType"/>  </label>	
	</div>
</div>

<div class='col-lg-12' >
<div class='col-lg-3 col-md-3 col-xs-3 col-sm-3 ' >
</div>
<div class='col-lg-5 col-md-5 col-xs-5 col-sm-5'>
<a href="#" onclick="openSamll('liveData/document/<s:property value='clientData.docImg'/>')">
<img alt="" src="liveData/document/<s:property value='clientData.docImg'/>" style="height: 100px;width: 100px;">
</a>
</div>
</div>
</s:if>


</div>
<div class='row'>
<div class='col-lg-12' >
		<s:if test="clientData.confirm_regi==0">
			<%if(loginfo.isClientprof()) {%>
			<i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;&nbsp;Not Verified Patient
			<%}else if(loginfo.getUserType()!=5){ %>
				<input type="hidden" value="<s:property value="clientData.id"/>" id="pvclientid">
				<a href="#" class="btn btn-primary" onclick="verifyPatientFromVP()">Verify Patient</a>
			<%}else{ %>
				<i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;&nbsp;Not Verified Patient
			<%} %>
			
			
		</s:if>
		<s:else>
			<i class="fa fa-check-square" aria-hidden="true"></i>&nbsp;&nbsp;Verified Patient
		</s:else>
</div>
</div>

<script>
	function verifyPatientFromVP(){
		var clientid = document.getElementById("pvclientid").value
		var registration1 =1;
		if(confirm("Are you sure!")){
			var url = "verifypatientregBookAppointmentAjax?registration="+registration1+"&clientid="+clientid+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();	
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}            
		    req.onreadystatechange = verifyPatientFromVPRequest;
		    req.open("GET", url, true);         
		    req.send(null);	
		}
		
	}
	function verifyPatientFromVPRequest() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				 window.location.reload();
			}
			}
	}
	
</script>