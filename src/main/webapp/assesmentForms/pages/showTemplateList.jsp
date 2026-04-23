<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Assessment Form</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>



<div class="col-lg-12 col-md-12">
	
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	
</div>

<s:form action="showtemplateAssesmentForms"  theme="simple">
			
<div class="col-lg-12 col-md-12 topback2">
<div class="col-lg-1 col-md-1">
	<label>Patient:</label>
</div>
	<div class="col-lg-5 col-md-5">
		<div class="input-group">
		<input type="hidden"  name='gfg' id='setp'>
			<s:hidden name="clientId" id = "clientId" ></s:hidden>
			<s:textfield  name="client" id="client" readonly="true"
				  cssClass="form-control" onclick="showPopUp()"></s:textfield> <span
				class="input-group-btn" >
			<s:submit value="Go!" cssClass="btn btn-primary" style="margin-right: 8px;"></s:submit> 		
			<a href="AssesmentMasterForms" class="btn btn-primary" Style="    margin-right: 8px;"><i
			class="fa fa-plus"></i> Add/Edit Field</a>
			<a href="inputAssesmentForms" class="btn btn-primary"><i
			class="fa fa-plus"></i> Create New  Template</a>
		</span>
		</div>
	</div>
</div>
</s:form>
<br/><br><br>	

<div class="row">
	<div class="col-lg-8 col-md-8">
		<div>
			<table id="results" class="table table-hove table-striped table-bordered">
				
				
				<tbody>
			
				<!--<s:if test="clientAssesmentformList.size!=0">
					<thead>
						<tr>
							<th>Existing Assessment Forms</th>
							<th>Date</th>
							<th class="text-center">Select</th>
							<th class="text-center">Delete</th>
						</tr>
					</thead>
						<s:iterator value="clientAssesmentformList" status="rowstatus">
							<tr>
								<td><s:property value="templateName" /></td>
								<s:hidden value="%{id}" name="id"></s:hidden>
								<td><s:property value="lastmodified" /></td>
								 <s:url action="addTemplateDetailsAssesmentForms" id="edit">
									<s:param name="templateId" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><a href="#" onclick="opencPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2')">
										<i class="fa fa-edit" ></i>
									</a></td>
								<s:url action="delassesmentAssesmentForms" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a cssClass="text-danger" href="%{delete}"
										onclick="return commonConfirmedDelete()" >
										<i class="fa fa-trash-o"></i>
									</s:a></td> 
							</tr>
							
						</s:iterator>
				</s:if>
					-->
					<thead>
						<tr>
							<th>Assessment Template</th>
							<th>Date</th>
							<th class="text-center">Select</th>
							<th class="text-center">Delete</th>
						</tr>
					</thead>
						
						<s:if test="clientAssesmentformList.size!=0">
						<s:iterator value="clientAssesmentformList" status="rowstatus">
							<tr>
								<td><s:property value="templateName" /></td>
								<s:hidden value="%{id}" name="id"></s:hidden>
								<td><s:property value="lastmodified" /></td>
								 <s:url action="addTemplateDetailsAssesmentForms" id="edit">
									<s:param name="templateId" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><a href="#" onclick="opencPopup('editListAssessmentForm?id=<s:property value="id"/>&actionType=2')">
										<i class="fa fa-edit" ></i>
									</a></td>
								<s:url action="delassesmentAssesmentForms" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a cssClass="text-danger" href="%{delete}"
										onclick="return commonConfirmedDelete()" >
										<i class="fa fa-trash-o"></i>
									</s:a></td> 
							</tr>
							
						</s:iterator>
						</s:if>
						
						
					<s:if test="templateNameList.size!=0">
						<thead>
							<tr>
								<th>Template Name</th>
								<th>Date</th>
								<th class="text-center">Select</th>
								<th class="text-center">Delete</th>
							</tr>
						</thead>
						<s:iterator value="templateNameList" status="rowstatus">
							<tr>
								<td><a href="#" onclick="opencPopup('addTemplateDetailsAssesmentForms?templateId=<s:property value="id"/>&formtype=<s:property value="formtype"/>')" class="text-primary">
										<s:property value="templateName" />
									</a></td>
								<s:hidden value="%{id}" name="id"></s:hidden>
								<td><s:property value="lastmodified" /></td>
								<s:if test="type==0">
									 <s:url action="addTemplateDetailsAssesmentForms" id="edit">
									<s:param name="templateId" value="%{id}"></s:param>
									<s:param name="formtype" value="%{formtype}"></s:param>
								</s:url>
								<td class="text-center"><a href="#" onclick="opencPopup('addTemplateDetailsAssesmentForms?templateId=<s:property value="id"/>&formtype=<s:property value="formtype"/>')" class="text-primary">
										<i class="fa fa-edit" ></i>
									</a></td>
								<s:url action="deltemplateAssesmentForms" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a cssClass="text-danger" href="%{delete}"
										onclick="return commonConfirmedDelete()" >
										<i class="fa fa-trash-o"></i>
									</s:a></td> 
								</s:if>
							
								
							</tr>

						</s:iterator>
					</s:if>
				
				<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i> There is no template list
						found!!
					</h3>

				</s:else>

			</table>
		</div>
	</div>
	<div class="col-lg-4 col-md-4"></div>
</div>


<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>







