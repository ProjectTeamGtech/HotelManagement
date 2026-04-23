<%@taglib uri="/struts-tags" prefix="s"%>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li class="active">All DNA Charge List</li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<a class="width-full btn btn-primary" href="addDNAChargeClinicRegistration"><i
			class="fa fa-plus"></i> Add</a>
	</div>
	<div class="col-lg-3 col-md-2"></div>
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
						<th class="text-center">DNA Charge</th>						
						<th class="text-center" style="width: 5%;">Edit</th>
						<th class="text-center" style="width: 5%;">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="dnaChargeList.size!=0">
					<s:iterator value="dnaChargeList" status="rowstatus">
					<tr>					
						<td><s:property value="dnaCharges"/></td>						
						
						<s:hidden value="%{id}" name="id"/>
						
						<s:url action="editDnaChargeClinicRegistration" id="edit">
							<s:param name="selectedid" value="%{id}"/>
						</s:url>
						<td class="text-center"><s:a href="%{edit}" class="text-warning">
								<i class="fa fa-edit"></i></s:a></td>
								
						<s:url action="deleteDnaChargeClinicRegistration" id="delete">
							<s:param name="selectedid" value="%{id}"/>
						</s:url>
						<td class="text-center"><s:a href="%{delete}"
							onclick="return confirmedDelete()" cssClass="text-danger">
							<i class="fa fa-trash-o"></i></s:a></td>						
					</tr>
					</s:iterator>
					</s:if>
				</tbody>
				<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i> No DNA Charge List Found!!
					</h3>
				</s:else>
			</table>
		</div>
	</div>
</div>

