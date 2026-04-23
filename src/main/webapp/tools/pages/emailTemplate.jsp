<script type="text/javascript" src="tools/js/emailTemplate.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<%@page import="com.apm.Tools.eu.entity.EmailTemplate"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Email Template</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="">
	
	<div class="col-lg-12 col-md-12 topback2">
		<a class=" btn btn-primary" href="addEmailTemplate"><i
			class="fa fa-plus"></i> Add</a>
	</div>	
	
</div>

<div class="row">
	<div class="col-lg-12">
		<s:hidden name="message" id="message"></s:hidden>
			<s:if test="hasActionMessages()">
				<script>
					var msg = " " + document.getElementById('message').value;
					showGrowl('', msg, 'success', 'fa fa-check');
				</script>
			</s:if>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"	class="table table-bordered  table-condensed">
				<thead>
					<tr>
						<th class="text-left">User Name</th>
						<th class="text-left">Template Name</th>
						<th class="text-center" style="width: 5%;">Edit</th>
						<th class="text-center" style="width: 5%;">Delete</th>
						<th class="text-center" style="width: 5%;">Preview</th>
					</tr>					
				</thead>
				<tbody>
					
						<s:if test="emailTemplateList.size!=0">
						
						<tr>
						<%ArrayList<EmailTemplate>  emailTemplateList = (ArrayList<EmailTemplate>)session.getAttribute("emailTemplateList");
						int i = 0;
						%>
						<%for(EmailTemplate emailTemplate:emailTemplateList){ %>
						<td><%=emailTemplate.getUserName()%></td>
						<td><%=emailTemplate.getTemplateName()%></td>
						<%int type = Integer.parseInt(emailTemplate.getTemplateId());
						if(type == 1){ %>
						
							<td class="text-center">Predefined</td>
								
						<%}else{ %>
						<td class="text-center"><a href="editEmailTemplate?selectedid=<%=emailTemplate.getId()%>"
										style="width: 5%;">
										<i class="fa fa-edit"></i>
									</a></td>	
						<%} %>				
						<td class="text-center"><a href="deleteEmailTemplate?selectedid=<%=emailTemplate.getId()%>"
										onclick="return confirmedDelete()" class="text-danger" style="width: 5%;">
										<i class="fa fa-trash-o"></i>										
									</a></td>		
						<td class="text-center"><a href="previewEmailTemplate?selectedid=<%=emailTemplate.getId()%>"
										value = "Preview" style="width: 5%;">Preview										
									</a></td>			
						</tr>
						<% i++;%>
						<%}%>
					</s:if>
				</tbody>
				<s:else>
						There is no Template Name found!!
				</s:else>
			</table>
		</div>
	</div>
</div>
<s:form action="EmailTemplate" name="paginationForm" id="paginationForm" theme="simple">
	<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>




