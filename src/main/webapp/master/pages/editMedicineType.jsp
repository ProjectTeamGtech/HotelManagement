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
								<h4>Update Product Category</h4>
								</div>
							</div>

<s:form action="updateMedicineTypeMaster" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
			    <label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name"/>
					 <label>Is Strip</label><label class="text-danger"></label>
					<s:if test="isstrip==1"><input type="checkbox" name="isstrip" id="isstrip" checked="checked" class="checkbox"  style="margin-left: auto;margin-right: auto;"  title="Enter IsStrip in number"></s:if>
					<s:else><input type="checkbox" name="isstrip" id="isstrip" class="checkbox"  style="margin-left: auto;margin-right: auto;"  title="Enter IsStrip in number"></s:else>
					
					
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Update" onclick="return savemedicinetypemaster()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="MedicineTypeMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
</body>
</html>