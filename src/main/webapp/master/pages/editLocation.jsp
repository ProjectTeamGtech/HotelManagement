<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="master/js/statecity.js"></script>
<script type="text/javascript" src="master/js/vaidateWarehouse.js"></script>
</head>
<body>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update Locatiom Name</h4>
								</div>
							</div>

<s:form action="updateLocationMaster" id="master_editform" theme="simple">
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
					
				<%-- <div class="form-group" id="locationDiv">
	      			<label>Department List </label><label class="text-danger">*</label>
	      			<s:select list="DisciplineList" name='Discipline' id='speciality' listKey="id"  listValue="Discipline" headerKey="" headerValue="All Department"  cssClass="form-control chosen-select" ></s:select>
      			</div>  --%>
					
					<s:if test="checked1==1">
					 	
				             <input id="checkbox_id" class="case" type="checkbox" value="<s:property value="pharmacy"/>" checked="checked"/> <label for="checkbox_id">Pharmacy</label> <br>
				         </s:if>
				         <s:else>
				            <input id="checkbox_id" class="case" type="checkbox" value="<s:property value="pharmacy"/>"/> <label for="checkbox_id">Pharmacy</label> <br>
				         </s:else>
				           <s:hidden name="pharmacycheck" id="pharmacycheck"></s:hidden>
			
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<%-- <s:submit cssClass="n btn-primary" value="Update" onclick="return savelocation()"/> --%>
			<a onclick="updatelocation()" href="#" class="btn btn-primary">Update</a>
			<s:reset cssClass="btn btn-primary" />
			<a href="LocationMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
</body>
</html>