<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>All GP List</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	



	<div class="col-lg-12 col-md-12 topback2">
		<s:form action="searchGP" theme="simple" id = "gpFrm">
		<div class="col-lg-8 col-md-8">
			<div class="input-group">			
				<s:textfield theme="simple" name="searchText"
					placeholder="Search GP Name" size="80" cssClass="form-control" />
				<span class="input-group-btn"> <input type="submit"
					value="Go" class="btn btn-primary" />
				</span>				
			</div>
			</div>
		</s:form>
		

<div class="col-lg-2 col-md-2">
	<a href="addGP" class="btn btn-primary"
		style="text-decoration: none"><i class="fa fa-plus"></i> Add New</a>
</div>

</div>



<div class="row">
	<div class="col-lg-12">
		<div >
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="">ThirdPartyCompanyName</th>
						<th class=" sortable <s:if test="#attr.pagination.sortColumn.equals('gpname')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'gpname');" style="color:#fff;">GP Name</a></th>
						<th class="">Note</th>						
						<th class="">Email Id</th>
						<th class="">Contact No</th>
						<th class="">Fax</th>						
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="gpList.size!=0">
						<s:iterator value="gpList" status="rowstatus">
							<tr>
								<td><s:property value="thirdPartyCompanyName" /></td>
								<td><s:property value="name" /></td>
								<td><s:property value="description" /></td>
								<td><s:property value="emailid" /></td>
								<td><s:property value="workphno" /></td>
								<td><s:property value="fax" /></td>

								<s:hidden value="%{id}" name="id"></s:hidden>
								<s:url action="editGP" id="edit">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{edit}"
										class="text-warning">
										<i class="fa fa-edit"></i>
									</s:a></td>
								<s:url action="deleteGP" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
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
						There is no GP List found!!
					</s:else>
			</table>
		</div>
	</div>
</div>
<s:form action="moveGP" name="paginationForm" id="paginationForm" theme="simple">
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





