<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="/struts-tags" prefix="s"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="row details mainheader">

								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Procedure</h4>
								</div>
							</div>

<s:form action="saveProcedure" id="master_form" theme="simple">

<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	   <div class="col-lg-6 col-md-8">
		 <div class="panel panel-primary">
			<div class="panel-body">
			
			              
			      <div class="form-group">
	      			<label>Sitting List</label><label class="text-danger">*</label>
	      			<s:select list="sittinglist" name="sitting" id="sid" listKey="name"  listValue="sittingFollowup" headerKey="" headerValue="All Sitting List" cssClass="form-control chosen-select" ></s:select>
                  </div>			
			     
			              
			      <div class="form-group">
	      			<label>Procedure Type </label><label class="text-danger">*</label>
	      			<s:select list="proceduretypelist" name="name" id="id" listKey="name"  listValue="name" headerKey="" headerValue="All Procedure" cssClass="form-control chosen-select" ></s:select>
                  </div>			
			
		        <label>Procedure Name</label><label class="text-danger">*</label>
				<s:textfield id="procedureName" name="procedureName"
				     
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Procedure Name " placeholder="Enter Procedure Name"/>
				<%-- 	
				<label>Charges</label>
				<s:textfield  id="charges" name="charges"
				     
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Charges" placeholder="Enter Charges"/>		 --%>	
					
					
				<!--  <label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
				     
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name " placeholder="Enter Name"/>	
					 -->
					
					
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
			<a href="Procedure" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
 
</body>
</html>



