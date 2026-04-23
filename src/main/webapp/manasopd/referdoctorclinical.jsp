<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="">
			<div class="row" style="padding: 0">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: -webkit-inline-box;padding: 0px;">
					 	<h4>Refer Doctor Clinical Patient</h4>
					</div>
				</div>
			</div>
		<div class="row " style="padding-top: 15px;">
		
		<table class="my-table table">
					<thead>
						<tr>
							<th>Sr No</th>
							<th style="text-align: center;">Title</th>
							<th style="text-align: center;">Name</th>
							<th style="text-align: center;">DOB</th>
						</tr>
					</thead>
					
					<tbody>
					<%int i=0; %>
							<tr>
								<td><%=++i %></td>
								<td style="text-align: center;"><input type="checkbox" style="margin: 0">&emsp;Master</td>
								<td style="text-align: center;">ABC</td>
								<td style="text-align: center;">12/05/1965</td>
							</tr>
							<tr>
								<td><%=++i %></td>
								<td style="text-align: center;"><input type="checkbox" style="margin: 0">&emsp;Master</td>
								<td style="text-align: center;">DEF</td>
								<td style="text-align: center;">12/05/1965</td>
							</tr>
							<tr>
								<td><%=++i %></td>
								<td style="text-align: center;"><input type="checkbox" style="margin: 0">&emsp;Master</td>
								<td style="text-align: center;">XYZ</td>
								<td style="text-align: center;">12/05/1965</td>
							</tr>
					</tbody>
					</table>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
					<label>Select Doctor</label>
					<select class="form-control">
					<option>Dr OD</option>
					<option>Dr Dental</option>
					<option>Dr Surgical</option>
					<option>Dr OD</option>
					</select>
					</div>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
					</div>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
					</div>
					<div class="col-lg-3 col-md-3 col-xs-3 col-sm-12">
					<label></label><br>
					<input type="button" class="btn btn-primary" value="Refer Doctor">
					</div>
					
					</div>
		</div>
		
</div>		
</body>
</html>