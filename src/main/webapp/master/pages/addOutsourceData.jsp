<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Outsource Information</title>
<script type="text/javascript" src="master/js/statecity.js"></script>
</head><script>
</script>
<body>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add OutSource</h4>
								</div>
							</div>

<s:form action="saveOutSourceDataMaster" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="form-inline">
			<div class="form-inline">
			    <label>Investigation type</label><label class="text-danger">*</label><br>
					<div class="form-inline">
				<s:select name="investigstion_id" list="investigationlist" listKey="id" listValue="name" cssClass="form-control chosen-select" headerKey="0" headerValue="All Investigation" theme="simple" ></s:select>
			</div>
			</div>
			<div class="form-inline">
			    <label>Outsource List</label><label class="text-danger">*</label><br>
					<div class="form-group">
				<s:select name="outsource_id" list="outsourceDatalist" listKey="outsource_id" listValue="outsource_name" cssClass="form-control chosen-select" headerKey="0" headerValue="All Outsources" theme="simple"  ></s:select>
			</div>
			</div>
			<div class="form-inline">
			    <label>Ammount</label><label class="text-danger">*</label><br>
				<s:textfield id="ammount" name="ammount"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name" />
					<div class="form-inline">
					<s:select name="sharing_type" id="sharing_type" 
				list="#{'0':'%','1':'Rs'}"
				cssClass="form-control chosen-select" ></s:select>	
				<div>	
			</div>
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return check1()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="Book" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div></div></div>
	<s:token></s:token>
</s:form>
</body>
</html>