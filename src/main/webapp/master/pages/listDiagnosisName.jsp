<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<div class="row">
	<div class="col-lg-12" align="center">
		<ol class="breadcrumb">
			<li class="active">Select Master<s:select list="masterlist" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)" cssClass="form-control showToolTip chosen"></s:select></li>
			</br>
			</br>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<a class="width-full btn btn-primary" href="Diagnosis"><i
			class="fa fa-plus"></i> Add</a>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>
<br />
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
		<div class="table-responsive">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-center">ID</th> 
						<th class="text-center">Diagnosis Name</th>
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="diagnosislist!=null">
						<s:iterator value="diagnosislist" status="diagnosislist">
							<tr>
								<td class="text-center"><s:property value="id" /></td> 
								<td class="text-center"><s:property value="name" /></td>
								<s:hidden value="%{id}" name="id"></s:hidden>
								<s:url action="editDiagnosisNameMaster" id="edit">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{edit}"
										class="text-warning">
										<i class="fa fa-edit"></i>
									</s:a></td>
								<s:url action="deleteDiagnosisNameMaster" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center">
									 <s:a href="%{delete}"
										onclick="return confirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i></s:a></td>

							</tr>

						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
						There is no Diagnosis Name List found!!
					</s:else>
			</table>
		</div>
	</div>
</div>
<s:form action="DiagnosisNameMaster" name="paginationForm" id="paginationForm" theme="simple">
	<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>

</s:form> 