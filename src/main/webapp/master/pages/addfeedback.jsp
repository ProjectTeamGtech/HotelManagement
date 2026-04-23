<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add State Information</title>
<script type="text/javascript" src="master/js/statecity.js"></script>
</head>
<body>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add FeedBack Template</h4>
								</div>
							</div>
<br>
<s:form action="saveFeedbackTemplateMaster" id="master_form" theme="simple" onsubmit="return chhk()">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			    <label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name" 
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name"/>
			</div>
			<div class="panel-body">
			<input type="checkbox" name="isopd" value="Bike">OPD<br>
<input type="checkbox" name="isipd" value="Car">IPD
</div> 
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save"  />
			<s:reset cssClass="btn btn-primary" />
			
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>

<script>
function chhk(){
	$('#baselayout1loaderPopup').modal( "show" );
	var name=document.getElementById('name').value;
	if(name==''){
		alert('Please Add Name');
		$('#baselayout1loaderPopup').modal( "hide" );
		return false;
	}else{
		$('#baselayout1loaderPopup').modal( "show" );
	}
	
}

</script>
</body>
</html>

