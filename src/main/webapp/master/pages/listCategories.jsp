<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Category List</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="col-lg-12 col-md-12 topback2">
	<div class="col-lg-3 col-md-2">
	<label>Select Master</label>
	<s:select list="masterlist" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
	</div>
	<div class="col-lg-6 col-md-8">
		<a class="btn btn-primary" href="#" style="margin-top:21px;" onclick="opencPopup('addcategoryProductinventory')"><i
			class="fa fa-plus"></i> Add</a>
	</div>
	<div class="col-lg-3 col-md-2"></div>
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
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">Name</th>
						<th class="text-center">Description</th>
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="categoryList!=null">
						<s:iterator value="categoryList" status="rowstatus">
								<tr>
								<td class="text-center"><s:property value="id" /></td>
								<td class="text-center"><s:property value="name"/></td>
								<td class="text-center"><s:property value="description"/></td>
								<td class="text-center"><a href="editcategoryProductinventory?selectedid=<s:property value="id"/>" class="text-primary">
										<i class="fa fa-edit"></i>
									</a>
								</td>
								<td class="text-center"><a href="deletecategoryProductinventory?selectedid=<s:property value="id"/>"
										onclick="return confirmedDelete()" class="text-danger">
										<i class="fa fa-trash-o"></i>
								</a></td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
						There is no Category List found!!
					</s:else>
			</table>
		</div>
	</div>
</div>
<s:form action="categoriesProductinventory" name="paginationForm" id="paginationForm" theme="simple">
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


