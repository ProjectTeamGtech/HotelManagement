<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
 <%@page import="java.util.*" %>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="ambulance/js/duty.js"></script>

           
 <%
				LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   %>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Duty Allocation</h4>
								</div>
							</div>

<s:form action="saveDuty" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			 
			 <label>Patient Name</label><label class="text-danger">*</label>
				<s:textfield id="patient" name="patient"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Patient Name" placeholder="Enter Patient Name"/>
			       <br><br>
                 <label>City</label><label class="text-danger">*</label>
				<s:textfield id="city" name="city"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter City" placeholder="Enter City"/>
					
					<br><br>
					
					<label>Patient Pick Up and Drop</label><label class="text-danger">*</label><br>
					<input type="radio" name="pickdrop" id="pickdrop" value="hospital" />  
					<label>Drop to Hospital</label>&nbsp;&nbsp;&nbsp;&nbsp;
				    <input type="radio" name="pickdrop" id="pickdrop" value="home" />  
					<label>Drop to Home</label>
				  
				     <br><br>
				     
				    <label>Hospital Name</label><label class="text-danger">*</label>
				    <s:textfield id="hosp" name="hosp"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Hospital Name" placeholder="Enter Hospital Name"/>
			
				     <br><br>
				     <%-- <label>Driver ID</label><br>
				     
				     <s:label id="driverid" name="driverid"><%=loginInfo.getUserId()%></s:label>
                     <br><br> --%>
                   <label>Date</label>
					<s:textfield  name="currdate" id="currdate"
					   cssClass="form-control" theme="simple" style="width:100%;"></s:textfield> 
            
					
					 
				  
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return savedutyallocate()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="MainDashBoard" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>

<script type="text/javascript">
var date=new Date();
var todaydate=String(date.getDate()).padStart(2,'0');
var month=String(date.getMonth()+1).padStart(2,'0');
var year=date.getFullYear();
var pattern=todaydate +'-'+ month +'-'+ year ;
document.getElementById("currdate").value=pattern;

</script>
