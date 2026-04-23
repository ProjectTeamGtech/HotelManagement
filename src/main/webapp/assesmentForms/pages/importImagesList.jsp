<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="assesmentForms/js/assessment.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li class="active">All Images List</li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-3 col-md-3">
		<a class="width-full btn btn-primary" href="inputImportImageForAssessment"><i
			class="fa fa-plus"></i> Add</a>
	</div>

</div>
<br />
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
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-center">Id</th>
						<th class="text-center">Image Name</th>
						<th class="text-center">Image Path</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="importImageList.size!=0">
						<s:iterator value="importImageList" status="rowstatus">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="imagename" /></td>
								<td><a href= "<s:property value="filepath" />"><s:property value="filename" /></a></td>
								<s:hidden value="%{id}" name="id"></s:hidden>
								<s:hidden value="%{filename}" name="filename"></s:hidden>
								<s:url action="deleteImportImageForAssessment" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
									<s:param name="filename" value="%{filename}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{delete}"
										onclick="return confirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i>
										
									</s:a></td>
							</tr>

						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
						There is no Images found!!
					</s:else>
			</table>
		</div>
	</div>
</div>
