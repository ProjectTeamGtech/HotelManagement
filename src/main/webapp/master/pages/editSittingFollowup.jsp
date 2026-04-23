<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


 <%-- <s:form action="updateSitting">
 
 <s:hidden name="id"></s:hidden>
      
                      <s:textfield name="sittingFollowup" label="Sitting Followup"/>
                      
                      
                      <s:submit/>
         </s:form>
 --%>
 
 <script type="text/javascript" src="master/js/Sitting.js"></script>
 
 <div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update Sitting Folloup</h4>
								</div>
							</div>

<s:form action="updateSitting"  theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			
			     <s:hidden id="id" name="id" />
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      			<div class="form-group">
	      			<label>Department List </label><label class="text-danger">*</label>
	      			<s:select list="departmentlist" name='department' id='dept' listKey="id"  listValue="discipline" headerKey="" headerValue="All Department" cssClass="form-control chosen-select" ></s:select>
      			</div>
      		</div>			
			     
			  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">   
			    <label>sitting Followup</label><label class="text-danger">*</label>
				<s:textfield id="sittingFollowup" name="sittingFollowup"
					 cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter sittingFollowup" placeholder="Enter sittingFollowup"/>
			</div>	
		 </div>
	  </div>
  </div>
 <div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
	  <div class="col-lg-3 col-md-2"></div>
		 <div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" onclick="return updatesitting()" value="Update" />
			<s:reset cssClass="btn btn-primary" />
			<a href="Sitting" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
 
</body>
</html>