<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Shelf Information</title>
<script type="text/javascript" src="master/js/statecity.js"></script>
</head>
<body>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Shelf</h4>
								</div>
							</div>

<s:form action="saveShelfMaster" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				<label>Department</label><label class="text-danger">*</label>
				<s:select list="locationlist" id="departmentid" name="departmentid" listKey="id" listValue="name" headerKey="0" headerValue="Select Department" cssClass="form-control chosen-select"></s:select>
				<br>
			    <label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name" cssClass="showToolTip form-control" data-toggle="tooltip" title="Enter Name" placeholder="Enter Name"/>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<%-- <s:submit cssClass="btn btn-primary" value="Save" onclick="return saveshelf()" /> --%>
			<a class="btn btn-primary" onclick="saveshelf()">Save</a>
			<s:reset cssClass="btn btn-primary" />
			<a href="ShelfMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>



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