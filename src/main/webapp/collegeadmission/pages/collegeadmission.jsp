<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="collegeadmission/js/collegeadmission.js"></script>
<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>
<style type="text/css">

</style>

<link href=
'https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/ui-lightness/jquery-ui.css'
          rel='stylesheet'>
      
    <script src=
"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" >
    </script>
      
    <script src=
"https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" >
    </script>

<script type="text/javascript">
$(document).ready(function() {

	
	
	$("#dob").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

});	
</script>

<SCRIPT type="text/javascript">

     window.onload= function(){
    	var typeid=document.getElementById("dob").value;
    	if(typeid!=''){
    		getAgendDays(typeid);
    	}
    	var whopay = document.getElementById("whopay").value;
    	enabledFiled(whopay);
     	
    	if(whopay=='Third Party'){
    		var type=document.getElementById("type").value;
    		if(type!='0'){
    			setTPName(type);
    		}
    		
    	}
     }

 </script>

</head>
	<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<body>
<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader" style="padding: 15px">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<h4>College Admissiom</h4>
										</div>
									</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9" style="padding-top: 42px;">
										<div class="letterheadhgt" style="height: 135px;">
											<div id="newpost" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 borderbot">
												<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-left:0px;padding-right:0px;">
													 <link href="common/css/printpreview.css" rel="stylesheet" />
												<%@ include file="/accounts/pages/letterhead.jsp" %>
												</div>
											</div>
										</div>
									</div>
								
									<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
										<div class="col-lg-12 col-md-12">
													<div id="vdivid" style="display: none;"><video id="videoID" autoplay style="width:100%;"></video></div>
														<div id="cdivid" style="display: none;"><canvas id="canvasID" style="border: 1px solid black;width: 88%;height: 112px;"></canvas></div>
													<div id="imgdivid"><img src="liveData/<s:property value="clientimg"/>" style="width:88%;"></div>
													</div>
													<br>
													<div class="col-lg-12 col-md-12">
													<a href="#" onclick="capture()" class="btn btn-primary">Capture</a>
													<a href="#" class="btn btn-primary" onclick="retake()">Update Photo</a>
										</div>
										
										<div class="col-lg-12 col-md-12" style="margin-top: 15px;" >
											<label><%=loginfo.getPatientname_field() %> Image</label>
											<s:file name="userImage" id="userImage" cssStyle="width: 100%;"/>									
										</div>
									
									</div>
								</div>
					<form action="savecollegeadmissionFinder">	
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="line-height: 25px;">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> NAME OF THE APPLICANT(As entered in SSLC Marks card))</label>
										<s:textfield cssClass="form-control" name="name" id="name" style="text-transform:uppercase"/>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> BLOOD GROUP</label>
										<s:textfield cssClass="form-control" name="bloodgroup" id="bloodgroup"/>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> NAME OF THE FATHER</label>
										<s:textfield cssClass="form-control" name="fathername" id="fathername"/>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
										<label> DATE OF BIRTH</label>
										<%-- <s:textfield cssClass="form-control" name="dob" id="dob"/> --%>
										<s:textfield id="dob" name="dob"
														cssClass="form-control showToolTip " data-toggle="tooltip"
														placeholder="Enter DOB" readonly = "true" onchange="getAgendDays(this.value)"/>
														

									</div>
									<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
										<label> AGE</label><br>
										<%-- <span>21 year</span> --%>
										<input type="text" name="age" value="" id="age" class="form-control showToolTip" title="" onchange="allnumeric(this.value)"
 													data-toggle="tooltip" style="width:100%;" placeholder="Age" data-original-title="Enter Age">
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> NAME OF THE PARENT/GUARDIAN</label>
										<s:textfield cssClass="form-control" name="parentname" id="parentname"/>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> ADDRESS OF THE PARENT/GUARDIAN</label>
										<s:textfield cssClass="form-control" name="parentaddress" id="parentaddress"/>
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
										<label> MOBILE NO OF THE PARENT/GUARDIAN</label>
										<s:textfield cssClass="form-control" name="mobileno" id="mobileno"/>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> NAME OF THE INSTITUTION (where the candidate last studied)</label>
										<s:textfield cssClass="form-control" name="institudename" id="institudename"/>
									</div>
									<div class="col-lg-5 col-md-5 col-xs-12 col-sm-12">
										<label> ADDRESS OF THE INSTITUTION (where the candidate last studied)</label>
										<s:textfield cssClass="form-control" name="institudeaddress" id="institudeaddress"/>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
										<label> RELIGION/CASTE</label>
										<s:textfield cssClass="form-control" name="caste" id="caste"/>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
										<label> ACADEMIC QUALIFICATION</label><br>
										<s:checkboxlist list="#{'puc':'PUC Science','intermidiate':'Intermediate Science','higher':'Higher Secondary','predegree':'Pre-Degree Science'}" name="education" theme="simple" cssClass="form-control"></s:checkboxlist>
										<!-- <input type="checkbox" onclick="allids()" value="puc" class="sssc">&emsp;PUC Science &emsp;
										<input type="checkbox" onclick="allids()" value="intermidiate" class="sssc">&emsp;Intermediate Science&emsp;
										<input type="checkbox" onclick="allids()" value="higher" class="sssc">&emsp;Higher Secondary&emsp;
										<input type="checkbox" onclick="allids()" value="predegree" class="sssc">&emsp;Pre-Degree Science&emsp; -->
									</div>
									<input type="hidden" id="selected" name="selected">
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> Marks obtained in the Qualifying Subjects 10+2 Level</label>
											<table class="table  table-bordered text-center">
												<tr>
													<td>Subject</td>
													<td>Max Marks</td>
													<td>Marks Obtained</td>
													<td>%</td>
												</tr>
												<tr>
													<td>Physics</td>
													<td><input type="text" name="physicsmax" class="form-control"></td>
													<td><input type="text" name="physicsmark" class="form-control"></td>
													<td><input type="text" name="physicsper" class="form-control"></td>
												</tr>
												<tr>
													<td>Chemistry</td>
													<td><input type="text" name="chemistrymax" class="form-control"></td>
													<td><input type="text" name="chemistrymark" class="form-control"></td>
													<td><input type="text" name="chemistryper" class="form-control"></td>
												</tr>
												<tr>
													<td>Biology</td>
													<td><input type="text" name="biologymax" class="form-control"></td>
													<td><input type="text" name="biologymark" class="form-control"></td>
													<td><input type="text" name="biologyper" class="form-control"></td>
												</tr>
												<tr>
													<td>English</td>
													<td><input type="text" name="englishmax" class="form-control"></td>
													<td><input type="text" name="englishmark" class="form-control"></td>
													<td><input type="text" name="englishper" class="form-control"></td>
												</tr>
												<tr>
													<td>Total</td>
													<td><input type="text" name="totalmax" class="form-control"></td>
													<td><input type="text" name="totalmark" class="form-control"></td>
													<td><input type="text" name="totalper" class="form-control"></td>
												</tr>
											</table>
									</div>
									
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> CET/COMEDK-Rank if any</label>
										<s:textfield cssClass="form-control" name="cet" id="cet"/>
									</div><div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> Weather the candidate belongs to SC/ST/BC/BSG,if so mention</label>
										<s:textfield cssClass="form-control" name="subcaste" id="subcaste"/>
									</div>
							</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="text-align: end;padding-top: 15px;margin-bottom: 30px;">
								<input type="submit" value="Save" class="btn btn-primary">
								</div>
						</div>	
					</form>
		</div>						
</body>






<script type="text/javascript">

	
</script>


</html>