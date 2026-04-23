<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="medical/records/js/medical.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Medical Records</a></li>
			<li class="active"> Records </li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-lg-1 col-md-1">
		<label>Client</label>
	</div>
	<s:form action="MedicalRecord" theme="simple">
	<div class="col-lg-2 col-md-2">
			
			<s:textfield name="client" id="client"  readonly="true"
				cssClass="form-control" onclick="showPopUp()" data-toggle="modal" data-target="#clientSearch"/>
				
	</div>
	<div class="col-lg-6 col-md-6">
	
		<div class="input-group">
			
				<s:textfield theme="simple" name="searchText"
					placeholder="Search by Subject" size="80" cssClass="form-control" />
				<span class="input-group-btn"> <input type="submit" value="Go" class="btn btn-primary" />
				</span>
			<s:hidden id = "searchClientId" name = "searchClientId"></s:hidden>

		</div>
	
	
	
	</div>
	</s:form>
<div class="col-lg-3 col-md-3">
	<s:form action="inputMedicalRecord">
			<s:hidden name="clientName" id = "clientName"></s:hidden>
			<s:hidden name="clientId" id="clientId"></s:hidden>
		<input type="submit" value= "Add New" class="btn btn-primary" >
		
	</s:form>
		
</div>
	
</div>
	<br>
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
<br>
<div class="row">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-center">Date</th>
						<th class="text-center">Subject</th>
						<th class="text-center">File Name</th>
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
						
					</tr>
				</thead>
				<tbody>
					<s:if test="medicalRecordList.size!=0">
						<s:iterator value="medicalRecordList" status="rowstatus">
							<tr>
								<td><s:property value="date" /></td>
								<td><s:property value="subject" /></td>
								<td><a href="downloadMedicalRecord?userImageFileName=<s:property value="userImageFileName" />"><s:property value="userImageFileName" /></a></td>
								<s:hidden value="%{id}" name="id"></s:hidden>
							<s:url action="editMedicalRecord" id="edit">
								<s:param name="id" value="%{id}"></s:param>
							</s:url>
								<td class="text-center"><s:a href="%{edit}" cssClass="text-warning">
								<i class="fa fa-edit"></i></s:a></td>
						<s:url action="deleteMedicalRecord" id="delete">
							<s:param name="id" value="%{id}"></s:param>
							<s:param name="userImageFileName" value="%{userImageFileName}"></s:param>
						</s:url>
						<td class="text-center"><s:a href="%{delete}" cssClass="text-danger">
								<i class="fa fa-trash-o"></i>
							</s:a></td>
							</tr>

						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
						There is no Occupation found!!
					</s:else>
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
        <%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<s:form action="MedicalRecord" name="paginationForm" id="paginationForm" theme="simple">
	<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>