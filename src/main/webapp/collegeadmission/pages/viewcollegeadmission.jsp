<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="collegeadmission/js/collegeadmission.js"></script>
<style type="text/css">
span {
	font-size: 15px !important;

	
}
</style>

</head>
	<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<body>
<div class="">
								
								
									<%-- <div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
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
									
									</div> --%>
					<form action="savecollegeadmissionFinder">	
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="line-height: 25px;">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> NAME OF THE APPLICANT(As entered in SSLC Marks card))</label>
										<br><span><s:property value="notAvailableSlot.name"/></span>
									
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> BLOOD GROUP</label>
										<br><span><s:property value="notAvailableSlot.bloodgroup"/></span>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> NAME OF THE FATHER</label>
										<br><span><s:property value="notAvailableSlot.fathername"/></span>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
										<label> DATE OF BIRTH</label>
										<br><span><s:property value="notAvailableSlot.dob"/></span>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
										<label> AGE</label>
										<br><span><s:property value="notAvailableSlot.name"/></span>
										<span>21 year</span>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> NAME OF THE PARENT/GUARDIAN</label>
										<br><span><s:property value="notAvailableSlot.parentname"/></span>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> ADDRESS OF THE PARENT/GUARDIAN</label>
										<br><span><s:property value="notAvailableSlot.parentaddress"/></span>
									</div>
									<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12">
										<label> MOBILE NO OF THE PARENT/GUARDIAN</label>
										<br><span><s:property value="notAvailableSlot.mobileno"/></span>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> NAME OF THE INSTITUTION (where the candidate last studied)</label>
										<br><span><s:property value="notAvailableSlot.institudename"/></span>
									</div>
									<div class="col-lg-5 col-md-5 col-xs-12 col-sm-12">
										<label> ADDRESS OF THE INSTITUTION (where the candidate last studied)</label>
										<br><span><s:property value="notAvailableSlot.institudeaddress"/></span>
									</div>
									<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
										<label> RELIGION/CASTE</label>
										<br><span><s:property value="notAvailableSlot.caste"/></span>
									</div>
									<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
										<label> ACADEMIC QUALIFICATION</label><br>
										<s:checkboxlist disabled="true" list="#{'puc':'PUC Science','intermidiate':'Intermediate Science','higher':'Higher Secondary','predegree':'Pre-Degree Science'}" name="education" theme="simple" cssClass="form-control"></s:checkboxlist>
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
													<td><span><s:property value="notAvailableSlot.physicsmax"/></span></td>
													<td><span><s:property value="notAvailableSlot.physicsmark"/></span></td>
													<td><span><s:property value="notAvailableSlot.physicsper"/></span></td>
												</tr>
												<tr>
													<td>Chemistry</td>
													<td><span><s:property value="notAvailableSlot.chemistrymax"/></span></td>
													<td><span><s:property value="notAvailableSlot.chemistrymark"/></span></td>
													<td><span><s:property value="notAvailableSlot.chemistryper"/></span></td>
												</tr>
												<tr>
													<td>Biology</td>
													<td><span><s:property value="notAvailableSlot.biologymax"/></span></td>
													<td><span><s:property value="notAvailableSlot.biologymark"/></span></td>
													<td><span><s:property value="notAvailableSlot.biologyper"/></span></td>
												</tr>
												<tr>
													<td>English</td>
													<td><span><s:property value="notAvailableSlot.englishmax"/></span></td>
													<td><span><s:property value="notAvailableSlot.englishmark"/></span></td>
													<td><span><s:property value="notAvailableSlot.englishper"/></span></td>
												</tr>
												<tr>
													<td>Total</td>
													<td><span><s:property value="notAvailableSlot.totalmax"/></span></td>
													<td><span><s:property value="notAvailableSlot.totalmark"/></span></td>
													<td><span><s:property value="notAvailableSlot.totalper"/></span></td>
												</tr>
											</table>
									</div>
									
									<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> CET/COMEDK-Rank if any</label>
										<br><span><s:property value="notAvailableSlot.cet"/></span>
										
									</div><div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
										<label> Weather the candidate belongs to SC/ST/BC/BSG,if so mention</label>
										<br><span><s:property value="notAvailableSlot.subcaste"/></span>
									</div>
							</div>
								<!-- <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="text-align: end;padding-top: 15px;margin-bottom: 30px;">
								<input type="submit" value="Save" class="btn btn-primary">
								</div> -->
						</div>	
					</form>
		</div>						
</body>






<script type="text/javascript">

	
</script>


</html>