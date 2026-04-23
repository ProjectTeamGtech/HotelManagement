<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="collegeadmission/js/collegeadmission.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

	<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader" style="padding: 15px">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
										 	<h4>College Admission Dashboard</h4>
										</div>
									</div>
								</div>
								<div class="row" style="padding-top: 10px;padding-bottom: 10px">
					           <div class="col-lg-12 text-right">
					              <a href="#" onclick="openBlankPopup('collegeadmissionFinder')" class="btn btn-primary">New Addmission</a>
					         </div>
					        </div>

		<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
					<table class="table table-condensed table-bordered">
						<thead style="text-align: center;">
						<tr>
							<td>Serial No</td>
							<td>Name</td>
							<td>DOB</td>
							<td>Mobile No</td>
							<td>Edit</td>
							<td>View</td>
							
						</tr>
					</thead>
					<tbody style="text-align: center;">
					<%int i=0; %>
					<s:iterator value="list">
						<tr>
							<td><%=++i %></td>
							<td><s:property value="name"/></td>
							<td><s:property value="dob"/></td>
							<td><s:property value="mobileno"/></td>
							<td><a href="#" onclick="openPopup('viewcollegeadmissionFinder?id=<s:property value="id"/>')">Edit</a></td>
							<td><a href="#" onclick="openPopup('viewcollegeadmissionFinder?id=<s:property value="id"/>')">View</a></td>
							
						</tr>
					</s:iterator>
					
					</tbody>
					</table>
		</div>
		</div>
		</div>
	</div>	
</body>
</html>