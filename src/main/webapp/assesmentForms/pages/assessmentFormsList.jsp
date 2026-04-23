<%@page import="com.apm.AssesmentForms.eu.entity.Predefine.AssessmentTemplate"%>
<%@page import="com.apm.AssesmentForms.eu.entity.Assessment"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="assesmentForms/js/assementList.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li class="active">Assessment Forms List</li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	</div>
</div>


<div class="row">
<s:form action="ListAssessmentForm" theme="simple">
<div class="col-lg-2 col-md-2">
	 <s:if test="%{#userList != 'null'}" >
		<s:select cssClass="form-control showToolTip chosen" id="diaryUserAssessment" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select Practitioner" onchange="setClientsAssessment(this.value)"   />
	</s:if>
</div> 		

 <div class="col-lg-2 col-md-2">
	<s:select cssClass="form-control showToolTip chosen" id="clientnameAssessment" name="clientName" list="clientList" listKey="id" listValue="clientName" headerKey="0" theme="simple" headerValue="Select Client"  onchange="setConditionAssessment(this.value)"  />
</div> 

<div class="col-lg-2 col-md-2">
	<s:select cssClass="form-control showToolTip chosen" id="conditionAssessment" name="condition" list="conditionList" listKey="id" listValue="treatmentType" headerKey="0" theme="simple" headerValue="Select condition" onchange="showTemplateListDropDown(this.value)"   />
</div> 
<%--  <div class="col-lg-2 col-md-2">
		<s:textfield name="clientName"  id="client" cssClass="form-control showToolTip" onclick="showPopUp2()" data-toggle="modal" data-target="#clientSearch" readonly="true" />
		<s:hidden name="clientId" id="clientId"></s:hidden> 
</div> --%>


 <div class="col-lg-2 col-md-2">
  	<s:select id="templateName1" name="templateName" list="templateNameList" listKey="templateId" listValue="templateName" title="Select Template" theme="simple" headerKey="0" headerValue="Select Template"  cssClass="form-control showToolTip chosen" />
 </div>
 <div class="col-lg-2 col-md-2">	
	<s:submit value="Show"  cssClass="btn btn-primary"></s:submit>
</div>

</s:form>
	
	 <div class="col-lg-2 col-md-2">	
		<a href="#" onclick="openPopup('inputAssesmentForms')">Create New Form</a>
	</div>
</div>

<br>
<div class="row">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table  class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<%ArrayList<Assessment> columnlist = (ArrayList<Assessment>)session.getAttribute("columnList");
						if(columnlist!=null){
						for(Assessment a : columnlist){
							%>
							<th class="text-center"><%=a.getFiledname()%></th>
						<% }}else{%>
						
						<%ArrayList<AssessmentTemplate> physioTemplateList = (ArrayList<AssessmentTemplate>)session.getAttribute("physioTemplateList");
						if(physioTemplateList!=null){							
							%>
							<th class="text-center">Patient Name</th>
							<th class="text-center">Exam Date</th>
							<th class="text-center">Subjective History</th>
							<th class="text-center">Outcome Measurement</th>
							<th class="text-center">Primary Diagnosis</th>
							<th class="text-center">Secondary Diagnosys</th>
							<th class="text-center">Assessment Date</th>
							<th class="text-center">Treatment Date</th> 
							<th class="text-center">Treatment Used</th>
							<th class="text-center">Discharge Status</th>
						<% }}%>
						<th class="text-center">Edit</th>
						<th class="text-center">Print</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
				<s:if test="assessmentFormsList.size!=0">
				
				<%
							int count = 1;
							String rows = (String) session.getAttribute("rows");
							String cols=(String) session.getAttribute("cols");%>
                          
                          
   						

                         <% for (int i=0;i<Integer.parseInt(rows);i++){
                                  %>
                                         <tr>
                                                <%
                                                ArrayList<String> list = (ArrayList<String>)session.getAttribute("assessmentFormsList");
                                                for(String s : list){
                                                	
                                                	%>
                                                
												 <td> <%=s%></td> 
												 <%if(count==Integer.parseInt(cols)){
													 %>
												<td><a href="editListAssessmentForm?id=<%=s%>&actionType=1&action=edit"class="text-warning">
														<i class="fa fa-edit"></i></a></td> 
												<td><a href="editListAssessmentForm?id=<%=s%>&actionType=1&action=print"class="text-warning">
														<i class="fa fa-print"></i></a></td> 
												<td><a href="deleteListAssessmentForm?id=<%=s%>"onclick="return confirmedDelete()" class="text-danger">
														<i class="fa fa-trash-o"></i></a></td> 
												<% }%>
												<%count = count +1;%>
                                                <%
                                                     
                                                       }%>  
                                                  
                                             
                         						<% }
                                                %>

             </s:if>
				
							
					<% 
					ArrayList<AssessmentTemplate>physioTemplateList = (ArrayList<AssessmentTemplate>)session.getAttribute("physioTemplateList");
					if(physioTemplateList!=null){
							for(AssessmentTemplate assessmentTemplate : physioTemplateList){
							%>
							<tr>
							<td><%=assessmentTemplate.getClient() %></td>
							<td><%=assessmentTemplate.getExamDate() %></td>
							<td><%=assessmentTemplate.getSubjectiveHistory() %></td>
							<td><%=assessmentTemplate.getOutcomeMeasurement() %></td>
							<td><%=assessmentTemplate.getPrimaryDiagnosis() %></td>
							<td><%=assessmentTemplate.getSecondaryDiagnosys() %></td>
							<td><%=assessmentTemplate.getAssessmentDate() %></td>
							<td><%=assessmentTemplate.getTreatmentDate() %></td>
							<td><%=assessmentTemplate.getTreatmentUsed() %></td>
							<td><%=assessmentTemplate.getDischargeStatus() %></td>
							<td><a href="editAssessmentTemplate?id=<%=assessmentTemplate.getId()%>"class="text-warning"><i class="fa fa-edit"></i></a></td>
							<td><a href="printAssessmentTemplate?id=<%=assessmentTemplate.getId()%>"class="text-warning"><i class="fa fa-print"></i></a></td>
							<td><a href="deleteAssessmentTemplate?id=<%=assessmentTemplate.getId()%>"onclick="return confirmedDelete()" class="text-danger"><i class="fa fa-trash-o"></i></a></td> 
					<% }}%></tr>
				
				
			<%-- 	<s:else>
						There is no Assessment Field List found!!
					</s:else>  --%>
					<%-- <s:if test="assessmentFormsList.size!=0">
						<s:iterator value="assessmentFormsList" status="rowstatus">
							
							<tr>
							<td><s:property value="id" /></td>
								<td><s:property value="filedname" /></td>

								<s:hidden value="%{id}" name="id"></s:hidden>
								<s:url action="editAssesmentMasterForms" id="edit">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{edit}"
										class="text-warning">
										<i class="fa fa-edit"></i>
									</s:a></td>
								<s:url action="deleteAssesmentMasterForms" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{delete}"
										onclick="return confirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i>
										
									</s:a></td>
							</tr>
								

						</s:iterator>
					</s:if>
				
				<s:else>
						There is no Assessment Field List found!!
					</s:else> --%>
					</tbody>
					
			</table>
		</div>
	</div>
</div>				


<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/assesmentForms/pages/assessmentClientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>					 