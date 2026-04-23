<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.DiaryManagement.eu.entity.Client"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	
var todayDate = new Date().getDate();

 $(document).ready(function() {
	$(".dob").datepicker({
		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});
	
	$("#fromDate").datepicker({
		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
	
	$("#toDate").datepicker({
		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
	
	$("#currentDate").datepicker({
		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		//minDate : new Date(new Date().setDate(todayDate-6)),
		changeMonth : true,
		changeYear : true,
		maxDate: new Date(new Date().setDate(todayDate))
	});
	
	$("#currentdate").datepicker({
		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		//minDate : new Date(new Date().setDate(todayDate-6)),
		changeMonth : true,
		changeYear : true,
		maxDate: new Date(new Date().setDate(todayDate))
	});
	
	$("#followUpDate").datepicker({
		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		//minDate : new Date(new Date().setDate(todayDate+1)),
		changeMonth : true,
		changeYear : true
	});
	
	
	
	$('.fakeclass').each(function() { 
		document.getElementById("title"+this.value).value = document.getElementById("initial"+this.value).value;
	     
	});
	$('.cityclass').each(function() { 
		
	    document.getElementById("city"+this.value).value = document.getElementById("cityname"+this.value).value; 
	});
	$('.stateclass').each(function() { 
		
	    document.getElementById("county"+this.value).value = document.getElementById("county1"+this.value).value; 
	    /* document.getElementById("savenshift"+this.value).value = document.getElementById("county1"this.value).value; */
	});
}); 
</script>
<%LoginInfo loginInfo=LoginHelper.getLoginInfo(request); %>
<script type="text/javascript" src="manasopd/js/adjustdata.js"></script>
<body>
<%String kaalmeghHidden=""; %>
<%String notKaalmegh=""; %>
<%if(loginInfo.isKalmegha()){ %>
	<input type="hidden" id="isfromKaalmegha" value="1">
	<%notKaalmegh="hidden"; %>
<%}else{ %>
	<input type="hidden" id="isfromKaalmegha" value="0">
	<%kaalmeghHidden="hidden"; %>
<%} %>
<div class="">
			<div class="row" style="padding: 0">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
					 	<h4>Add Clinical Patient</h4>
					</div>
				</div>
			</div>
			<%String hiddenVar = ""; %>
			<%String patientType0 = ""; %>
			<s:if test="patienttype==1">
				<%hiddenVar="hidden"; %>
			</s:if>
			<s:else>
				<%patientType0="hidden"; %>
			</s:else>
		<div class="row " style="padding-top: 15px;">
			  <div class="col-lg-8 col-md-8 col-xs-12 col-sm-12">
				<!--  -->
					<form action="Patientdata" id="mainform">
						<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
								<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12 <%=kaalmeghHidden%>">
									<label>From Date</label>
									<s:textfield  cssClass="form-control" name="fromDate" id="fromDate" readonly="true"></s:textfield>
								</div>
								<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12 <%=kaalmeghHidden%>">
									<label>To Date</label>
									<s:textfield  cssClass="form-control" name="toDate" id="toDate" readonly="true"></s:textfield>
								</div>
								<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12 <%=hiddenVar%>">
									<label>No. of Patients</label>
									<input type="number" class="form-control" name="count" min="0">
								</div>
								<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12 <%=notKaalmegh%>">
									<label>Patient Type : </label>
									<s:select list="#{'0':'NEW','1':'OLD'}" disabled="true" id="patienttype"  name="patienttype" cssClass="form-control"/>
								</div>
								<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12 <%=patientType0%>">
									<label>Date : </label>
									<s:textfield name="currentdate" onchange="getPatientDeptCount(this.value)" id="currentdate" readonly="true" cssClass="form-control"></s:textfield>
								</div>
								
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 <%=hiddenVar%> <%=notKaalmegh%>">
									<label>Select Patient Age</label>
									<s:select list="#{'0':'Pedo Patient','1':'Other Patient'}" headerKey="" headerValue="Select Patient Age" id="patientAgeType"  name="patientAgeType" cssClass="form-control"/>
								</div>
								<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
									<label>Select Department</label>
									<% if(loginInfo.getSuperadminid()==1 || loginInfo.getUserType()==2 || !(loginInfo.getShow_dept_opd_list().equals("0"))) {%>
										<s:select list="disciplineList" name='dept' listKey="id" listValue="discipline" headerKey="0" headerValue="Select Department" onchange="setdept(this.value)"  cssClass="form-control"></s:select>
									<%}else{ %>
										<s:select list="disciplineList" disabled="true" name='dept' listKey="id" listValue="discipline" headerKey="0" headerValue="Select Department" onchange="setdept(this.value)"  cssClass="form-control"></s:select>
									<%} %>
								</div>
								<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12 <%=kaalmeghHidden%>">
									<label>Select Doctor</label>
									<s:select list="userList" name='drId' id="drId" listKey="id" listValue="diaryUser" headerKey="0" headerValue="Select Doctor" cssClass="form-control chosen-select"></s:select>
								</div>
								
								<div class="col-md-1 col-lg-1 col-xs-12 col-sm-12">
									<label>&emsp;&emsp;&emsp;</label><br>
									<!-- <input type="submit" class="btn btn-primary" value="="> -->
									<a href="#" class="btn btn-primary" onclick="checkdeptselected()">Go</a>
								</div>
								<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12 <%=kaalmeghHidden%>">
									<label>&emsp;&emsp;&emsp;</label><br>
									<a href="#"  onclick="opencPopup('referdepartmentclinicalPatientdata')" class="btn btn-primary">Refer & Assign</a>
								</div>
						</div>
						<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12 <%=notKaalmegh%>">
							<div class="col-md-2 col-lg-2 col-xs-12 col-sm-12">
								<label>&emsp;&emsp;&emsp;</label><br>
								<a href="#"  onclick="opencPopup('referdepartmentclinicalPatientdata')" class="btn btn-primary">Refer & Assign</a>
							</div>
						</div>
				
					</form>
				</div>	
				<div hidden class="col-lg-4 col-md-4 col-xs-12 col-sm-12 topback2" id="patientDeptCountDiv">
					<table class="table table-hover table-condensed table-bordered" style="border: black 1px solid;">
						<thead>
							<tr>
								<td>Department</td>
								<td>New Patient</td>
								<td>Old Patient</td>
								<td>Total Count</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="deptWiseCountList">
								<%-- <s:if test="totalPatientCount>0"> --%>
									<tr>
										<td><s:property value="discipline"/></td>
										<td><s:property value="newPatientCount"/></td>
										<td><s:property value="oldPatientCound"/></td>
										<td><s:property value="totalPatientCount"/></td>
									</tr>
								<%-- </s:if> --%>
							</s:iterator>
						</tbody>
						<thead>
							<tr>
								<td>Total Patient</td>
								<td><s:property value="finalNewPatientCount"/></td>
								<td><s:property value="finalOldPatientCount"/></td>
								<td><s:property value="finalTotalPatientCount"/></td>
							</tr>
						</thead>
					</table>
				</div>
		</div>
		<form action="movepatientPatientdata" id="movepatientfrm">
		<s:hidden name="selectedids"/>
		<input type="hidden" value='<s:property value='selectedids'/>' id="referselected">
		<s:hidden name="patienttype"/>
		 <s:hidden name="dept" id="alldept"/> 
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 12px 0 0 0;">
					<table class="my-table table">
					<thead>
						<tr>
							<th style="width: 2%;text-align: center;" class="<%=patientType0%>"><input type="checkbox" onclick="selectFakeCheckBoxfun(this.checked)"></th>
							<th style="width: 3%;text-align: center;">Sr No</th>
							<th style="width: 5%;text-align: center;">Title</th>
							<th style="width: 10%;text-align: center;">First Name</th>
							<th style="width: 10%;text-align: center;">Last Name</th>
							<th style="width: 5%;text-align: center;">DOB</th>
							<th style="width: 5%;text-align: center;">Year</th>
							<!-- <th style="width: 5%;text-align: center;">Month</th>
							<th style="width: 5%;text-align: center;">Day</th> -->
							<th style="width: 20%;text-align: center;">Address</th>
							<th style="width: 8%;text-align: center;">State</th>
							<th style="width: 8%;text-align: center;">City</th>
							<th style="width: 8%;text-align: center;">Gender</th>
							<th style="width: 10%;text-align: center;">Mobile No</th>
							<%if(loginInfo.isKalmegha()){ %>
								<th style="width: 10%;text-align: center;">Dr. Name</th>
								<th style="width: 10%;text-align: center;">Apmt Date</th>
							<%} %>
							<th style="width: 10%;text-align: center;" class="<%=patientType0%>">Follow Up</th>
						</tr>
					</thead>
					
					<tbody>
					<%int i=0; %>
					<s:if test="patienttype==0">
						<s:iterator value="fakepatientlist">
							<tr>
								<td id="fakeid"><%=++i %>
								<input type="hidden" value="<s:property value="id" />" class="checkclass">
								<%-- <input type="hidden" id="fakeid<s:property value="id" />" name="fakeid<s:property value="id" />" value="<s:property value="fakeid1"/>" > --%>
								</td>
								
								<%-- <td style="text-align: center;" id="titleid"><s:property value="title"/></td> --%>
								<td style="text-align: center;" id="titleid">
								<input type="hidden" value="<s:property value="id" />" class="fakeclass">
								<input type="hidden" id="initial<s:property value="id" />" name="initial<s:property value="id" />" value="<s:property value="title"/>" >
								<%-- <s:select  id="title1"  name="title" list="initialList" onchange="setGender(this.value)"  theme="simple" cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip" headerValue="Select" headerKey="" cssStyle="width:60px"/> --%>
								<%-- <s:select list="initialList" id="title" name="title" onchange="initialUpdate(this.value)"  theme="simple" cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip" headerValue="Select" headerKey="0" cssStyle="width:60px"/> --%>
								<select class="form-control" id="title<s:property value="id" />" name="title<s:property value="id" />"  <%-- onchange="getFakeData(this.value,<s:property value="id" />)" --%>>
									<s:iterator value="initialList">
										<option  value='<s:property/>'  ><s:property/></option>
										
									</s:iterator>
								</select>
								</td> 
								
								<%-- <%-- <td style="text-align: center;" id="fnameid"><s:property value="fullname"/></td> --%>
								<%-- <td style="text-align: center;" id="fnameid" >
								<input type="text" class="form-control" value="<s:property value="fullname"/>" id="fullname<s:property value="id"/>" name="fullname<s:property value="id"/>" class="fullname"  onchange="getFullName(<s:property value="id"/>)"  >
								<input type="text" class="form-control" value="<s:property value="fullname"/>" id="fullname<s:property value="id"/>" name="fullname<s:property value="id"/>" class="fullname"  onchange="getFakeData(this.value,<s:property value="id"/>)"  >
								</td>  --%>
								
								<td style="text-align: center;">
								<input type="text" class="form-control" value="<s:property value="firstName"/>" id="firstName<s:property value="id"/>" name="firstName<s:property value="id"/>" class="firstName" onchange="validateFirstName(<s:property value="id"/>)" >
								</td>
								
								<td style="text-align: center;">
								<input type="text" class="form-control" value="<s:property value="lastName"/>" id="lastName<s:property value="id"/>" name="lastName<s:property value="id"/>" onchange="validateLastName(<s:property value="id"/>)" >
								</td>
								
								<td style="text-align: center;">
									<%--  <span id="dob<s:property value="id"/>"> <s:property value="dob"/></span> --%>   
								  <input type="text" readonly="readonly" name="dob<s:property value="id"/>"  id="dob<s:property value="id"/>" class="dob" value="<s:property value="dob"/>"  onchange="getAgendDaysFake(<s:property value="id"/>)" style="width:75px" class="form-control" >   
									
								</td>
								<td style="text-align: center;" >
									<%--  <span id="year<s:property value="id"/>"><s:property value="year"/></span> --%> 
								  <input type="text" class="form-control" value="<s:property value="year"/>" id="year<s:property value="id"/>" name="year<s:property value="id"/>" onchange="allnumericFake(<s:property value="id"/>)" >
								     
								</td>
								<%-- <td style="text-align: center;" >
									<span id="month<s:property value="id"/>"><s:property value="month"/></span>
								</td>
								<td style="text-align: center;">
									<span id="days<s:property value="id"/>"><s:property value="days"/></span>
								</td> --%>
								<td style="text-align: center;" id="addressid">
								<%-- <s:property value="address" /> --%>
								<%-- <input type="text" class="form-control" value="<s:property value="address"/>" id="address<s:property value="id"/>" name="address<s:property value="id"/>" class="address" onchange="getAddress(<s:property value="id"/>)"  > --%>
								<input type="text" class="form-control" value="<s:property value="address"/>" id="address<s:property value="id"/>" name="address<s:property value="id"/>" class="address"   >
								</td>
								
								<td style="text-align: center;" id="stateid">
								 <%-- <div class="" id="statediv<s:property value="id"/>">	
								     <input type="text" class="form-control" value="<s:property value="county"/>" id="county" name="county<s:property value="id"/>"> 		 		
								</div>  --%>
									<%--  <s:select  id="county"  name="county" list="stateList"  theme="simple" cssClass="form-control showToolTip ajsolidborder" data-toggle="tooltip" headerValue="Select State" headerKey="" /> --%> 
								<input type="hidden" value="<s:property value="id" />" class="stateclass">
								<input type="hidden" id="county1<s:property value="id" />" name="county1<s:property value="id" />" value="<s:property value="county"/>" >
								<select class="form-control" id="county<s:property value="id" />" name="county<s:property value="id" />" >
									<s:iterator value="statelist">
										<option value="<s:property value="name"/>"><s:property value="name"/></option>	
									</s:iterator>
								</select>
								
								</td>
								<td style="text-align: center;" id="cityid">
								   <%-- <div class="" id="citydiv<s:property value="id"/>">	
								 		<input type="text" class="form-control" value="<s:property value="city"/>" id="city" name="city<s:property value="id"/>">
									</div> --%> 
								<%--  <s:select list="cityList" id="city" name="city" listKey="id" listValue="city" theme="simple" cssClass="form-control" headerValue="Select City" headerKey="" ></s:select> --%> 
								
								<input type="hidden" value="<s:property value="id" />" class="cityclass">
								<input type="hidden" id="cityname<s:property value="id" />" name="cityname<s:property value="id" />" value="<s:property value="city"/>" >
								<select class="form-control" id="city<s:property value="id" />" name="city<s:property value="id" />" >
									<s:iterator value="citylist">
										<option value="<s:property value="city" />" ><s:property value="city"/></option>
										
									</s:iterator>
								</select>
								
								
								
								</td>
								<td style="text-align: center;" id="genderid">
								<%-- <s:property value="gender"/> --%>
								<%-- <s:select id="gender" name="gender" list="{'Male','Female','Other'}"  theme="simple" cssClass="form-control showToolTip ajsolidborder"
																	data-toggle="tooltip"  headerKey="0" headerValue="Select"/> --%>
							    <select class="form-control" id="gender<s:property value="id" />" name="gender<s:property value="id" />">
									
										
										<!-- <option value="Select">Select</option> -->
									<s:if test="gender=='Male'">
										<option value="Male" selected="selected">Male</option>
										<option value="Female">Female</option>
										<option value="Other">Other</option>
									</s:if>
									<s:elseif test="gender=='Female'">
										<option value="Male" >Male</option>
										<option value="Female" selected="selected">Female</option>
										<option value="Other">Other</option>
									</s:elseif>
									<s:elseif test="gender=='Other'">
										<option value="Male" >Male</option>
										<option value="Female" >Female</option>
										<option value="Other" selected="selected">Other</option>
									</s:elseif>
									<s:else>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
										<option value="Other">Other</option>
									</s:else>
								</select>									
								</td>
								<td style="text-align: center;" id="noid">
								<%-- <s:property value="mobNo"/> --%>
								<input type="text" class="form-control" value="<s:property value="mobNo"/>" id="mobNo<s:property value="id"/>" name="mobNo<s:property value="id"/>" class="mobNo" onchange="validateMobNo(<s:property value="id"/>)" pattern="[1-9]{1}[0-9]{9}" maxlength="10" >
								</td>
								<%if(loginInfo.isKalmegha()){ %>
									<td style="text-align: center;"><s:property value="drname"/></td>
									<td style="text-align: center;"><s:property value="apmtDate"/></td>
								<%} %>
								
							</tr>
						</s:iterator>
					</s:if>
					<s:elseif test="patienttype==1">
						<s:iterator value="fakepatientlist">
							<tr>
								<td style="text-align: center;"><input type="checkbox" value="<s:property value="id" />" class="checkrandomclass"></td>
								<td style="text-align: center;"><%=++i %></td>
								<td style="text-align: center;"><s:property value="title"/></td>
								<td style="text-align: center;"><s:property value="firstName"/></td>
								<td style="text-align: center;"><s:property value="lastName"/></td>
								<td style="text-align: center;"><s:property value="dob"/></td>
								<td style="text-align: center;"><s:property value="year"/></td>
								<%-- <td style="text-align: center;"><s:property value="month"/></td>
								<td style="text-align: center;"><s:property value="days"/></td> --%>
								<td style="text-align: center;"><s:property value="address"/></td>
								<td style="text-align: center;"><s:property value="state"/></td>
								<td style="text-align: center;"><s:property value="city"/></td>
								<td style="text-align: center;"><s:property value="gender"/></td>
								<td style="text-align: center;"><s:property value="mobNo"/></td>
								<td style="text-align: center;"><s:property value="Followupdate"/></td>
							</tr>
							
							
						</s:iterator>	
					</s:elseif>	
					</tbody>
					</table>
		</div>
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 12px 0 0 0;">
			<div class="col-lg-2 col-md-2 col-xs-6 col-sm-12 <%=hiddenVar%>">
				<s:textfield name="currentDate" onchange="getPatientDeptCount(this.value)" id="currentDate" readonly="true" cssClass="form-control"></s:textfield>
			</div> 
			<div class="col-lg-6 col-md-6 col-xs-6 col-sm-12">
				 
				 <input type="button" class="btn btn-primary" id="savenshift" onclick="chosemethod()" value="Save & Shift Data"> 
				<!-- <a href="#" onclick="chosemethod()"  class="btn btn-primary">Save & Shift Data</a> -->
			</div>
			<s:if test="patienttype==1">
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-12" style="text-align: right;">
					<input type="text" class="form-control" placeholder="Follow Up Date" style="width: 25%" readonly="readonly" id="followUpDate">
					<a href="#" onclick="changeFollowUpDate()" class="btn btn-primary">Change Follow Up Date</a>
				</div>
			</s:if>
			
			
		</div>
		</form>
	</div>
	
	
	
	
	 <!--Loader  -->
   <div class="modal fade" style="background: rgba(255, 255, 255, 0.93)" id="newloaderPopup" aria-labelledby="lblsendEmailPopUp" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- End Loader  -->
	
	<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
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
</body>
</html>



