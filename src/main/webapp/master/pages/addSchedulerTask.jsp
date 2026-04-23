<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Scheduler Task</title>
<script type="text/javascript" src="master/js/statecity.js"></script>
</head>
<body>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Scheduler Task</h4>
								</div>
							</div>

<s:form action="saveSchedulerTask" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			    <label>Task Name</label><label class="text-danger">*</label>
				<s:textfield id="taskname" name="taskname"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Task Name" placeholder="Enter Task Name"/>
			</div>
			
			 <label>Select Job Title</label><label class="text-danger">*</label>
			    <s:select list="newjobTitleList" headerKey="0" headerValue="Select jobTitle" id="jobTitle" name="jobTitle"   cssClass="form-control"></s:select>	
			<%-- <div class="panel-body">
			    <label>Job Title</label><label class="text-danger">*</label>
				<s:textfield id="jobTitle" name="jobTitle"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter jobTitle" placeholder="Enter jobTitle"/>
			</div> --%>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return savestate()" />
			<s:reset cssClass="btn btn-primary" />
			<a href="State" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
</body>
</html>