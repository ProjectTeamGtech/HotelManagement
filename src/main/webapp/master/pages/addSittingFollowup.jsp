<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="/struts-tags" prefix="s"%>
     
     
<script type="text/javascript" src="master/js/Sitting.js"></script>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<div class="row details mainheader">

								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Sitting And followup</h4>
								</div>
							</div>

<s:form action="saveSitting" id="master_form" theme="simple">

<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	   <div class="col-lg-6 col-md-8">
		 <div class="panel panel-primary">
			<div class="panel-body">
			
			
			    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			   <div class="form-group">
	      			<label>Department List </label><label class="text-danger">*</label>
	      			<s:select list="departmentlist" name='department' id="id" listKey="id"  listValue="discipline" headerKey="" headerValue="All Department" cssClass="form-control chosen-select" ></s:select>
      			</div>
      		</div>			
			
			 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			  <div class="form-group">
			           <label>Sitting Followup</label><label class="text-danger">*</label>
				       <s:textfield id="sittingFollowup" name="sittingFollowup"
				        cssClass="showToolTip form-control" data-toggle="tooltip"
					   title="Enter seating and Followup " placeholder="Enter seating and Followup "/>
				</div>
			</div>
					
					
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" onclick="return addsitting()" value="Save"  />
			<s:reset cssClass="btn btn-primary" />
			<a href="Sitting" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
 
</body>
</html>
