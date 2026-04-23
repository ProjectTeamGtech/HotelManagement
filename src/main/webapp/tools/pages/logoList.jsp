<script type="text/javascript" src="tools/js/emailTemplate.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<%@page import="com.apm.Tools.eu.entity.EmailTemplate"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li class="active">Logo</li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<a class="width-full btn btn-primary" href="addLogo"><i
			class="fa fa-plus"></i> Add</a><br><br>		
	</div>	
	<div class="col-lg-3 col-md-2"></div>
</div>
<br />

<div class="row">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="results"	class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-center">Logo Name</th>
						<th class="text-center" style="width: 5%;">Edit</th>
						<th class="text-center" style="width: 5%;">Delete</th>
						<th class="text-center" style="width: 5%;">Preview</th>
					</tr>					
				</thead>
				<tbody>	
					
						<tr>
						<%ArrayList<EmailTemplate>  logoList = (ArrayList<EmailTemplate>)session.getAttribute("logoList");
						int i = 0;
						%>
						<%for(EmailTemplate emailTemplate:logoList){ %>
						<td><%=emailTemplate.getUserImageFileName()%></td>
						<td class="text-center"><a href="editLogo?selectedid=<%=emailTemplate.getId()%>"
										class="text-warning" style="width: 5%;">
										<i class="fa fa-edit"></i>
									</a></td>		
						<td class="text-center"><a href="deleteLogo?selectedid=<%=emailTemplate.getId()%>"
										onclick="return confirmedDelete()" class="text-danger" style="width: 5%;">
										<i class="fa fa-trash-o"></i>										
									</a></td>		
						<td class="text-center"><a href="previewLogo?selectedid=<%=emailTemplate.getId()%>"
										value = "Preview" style="width: 5%;">Preview										
									</a></td>			
						</tr>
						<% i++;%>
						<%}%>
					
				</tbody>
			</table>
		</div>
	</div>
</div>			