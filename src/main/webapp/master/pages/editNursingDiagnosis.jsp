<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="master/js/statecity.js"></script>
</head>
<body>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update Nursing Diagnosis</h4>
								</div>
							</div>

<s:form action="updateNursingPlan" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
			    <label>Diagnosis Name</label><label class="text-danger">*</label>
				<s:textfield id="nursing_diagnosis" name="nursing_diagnosis"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Diagnosis Name" placeholder="Enter Diagnosis Name"/>
			</div>
	<%-- 		<div class="panel-body">
		<label>Select Job Title</label><label class="text-danger">*</label>
			    <s:select list="newjobTitleList" id="jobTitle" headerKey="0" headerValue="Select Job Title" name="jobTitle"  cssClass="form-control"></s:select>
		
			    <label>Job Title</label><label class="text-danger">*</label>
				<s:textfield id="jobTitle" name="jobTitle"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Job Title" placeholder="Enter Job Title"/>
			</div> --%>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Update" onclick="return saveNursingPlan()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="NursingPlan" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
</body>
</html>