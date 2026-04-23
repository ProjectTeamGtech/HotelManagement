<%@taglib uri="/struts-tags" prefix="s"%>
<link href="thirdParties/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="thirdParties/css/subModal.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="thirdParties/js/common.js"></script>
<script type="text/javascript" src="thirdParties/js/subModal.js"></script>
<script type="text/javascript" src="thirdParties/js/thirdParty.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>View Third Party</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>

<div class="col-lg-12 col-md-12 topback2">
	<!-- /.col-lg-6 -->
	<div class="col-lg-2 col-md-2 hidden">
	<label>Select Master</label>
	<s:select list="masterlist" listKey="id" listValue="name" onchange="selectAction(this.value)" cssClass="form-control showToolTip chosen"></s:select>
	</div>
	<s:form action="showListThirdParty" id="thirdParty_form" theme="simple">
	
		<div class="col-lg-2 col-md-2">
			<label>List Third Party</label>
			<s:select id="name" name="name" list="thirdPartyTypeList"
				listKey="id" listValue="name" headerKey="" theme="simple" 
				headerValue="All" cssClass="form-control" onchange="showListOfTP()">
				
				</s:select>
			
			</div>
<%-- 			<s:form action="searchThirdParty" theme="simple" id = "search_thirdParty_form">
 --%>			
			<div class="col-lg-6 col-md-6">
			
			<div class="input-group" style="margin-top:21px;">
				<s:textfield theme="simple" name="searchText"
					placeholder="Search By Company Name"  cssClass="form-control" />
				<span class="input-group-btn"> <input type="submit"
					value="Go" class="btn btn-primary" />
				</span>
			</div>
			
			</div>
			</s:form>
			
		<!-- /input-group -->
	
	
	
	<!-- /.col-lg-6 -->
	
	<div class="col-lg-2 col-md-2">
		<a href="addThirdParty" class="btn btn-primary"
			style="text-decoration: none;margin-top:21px;"><i class="fa fa-plus"></i> Add New
			TP</a>

	</div>
	
	<div class="col-lg-2 col-md-2">
		<a href="addchargesThirdParty" class="btn btn-primary"
			style="text-decoration: none;margin-top:21px;"><i class="fa fa-plus"></i> Add New TP Charges</a>

	</div>
	
</div>




	
	<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	<div class="row">
		<div class="col-lg-12">
			<div class="">
				<table id="results"
					class="table table-bordered">

					<thead>
						<tr>
						      
							<th > Third Party Type</th>
							<th class=" sortable <s:if test="#attr.pagination.sortColumn.equals('company_name')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'company_name');" style="color:#fff;">Company</a></th>
							
							<th >Contact Details</th>
							<th class="text-center">Edit</th>
							<th class="text-center"> Delete</th>
						</tr>
					</thead>
				
					<s:if test="thirdPartyDetailList.size!=0">
						<s:iterator value="thirdPartyDetailList">
							<tr>

								<td><s:property value="type" /></td>
								<td><s:property value="companyName" /></td>
								
								<td><s:property value="email" />,&nbsp;<s:property
										value="telephoneLine" /></td>
										<s:url action="editTPDetailsNewThirdParty" id="edit">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<s:if test="type == 'Doctor Surgery' || type == 'DOCTOR SURGERY'">
								<s:url action="editDoctorSurgeryThirdParty" id="editDS">
									<s:param name="selectedid" value="%{id}"></s:param>
									</s:url>
								
									<td class="text-center"><s:a href="%{editDS}">
										<i class="fa fa-edit"></i>
									</s:a></td>
									
								</s:if>
								<s:else>
								<td class="text-center"><s:a href="%{edit}">
										<i class="fa fa-edit"></i>
									</s:a></td>
								</s:else>

								
								

								<s:url action="deleteDetailsThirdParty" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{delete}"
										onclick="return confirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i>
									</s:a></td>

							</tr>
						
						</s:iterator>
					</s:if>
				</table>
				<s:else>
					<div class="row">
						<div class="col-lg-12">
							<h3 class="text-center">
								<i class="fa fa-times text-danger"></i> No Record found!!
							</h3>
						</div>
					</div>
				</s:else>

			</div>
		</div>
	</div>



<s:if test="thirdPartyDetailList!=null">

		<s:form action="showListThirdParty" name="paginationForm" id="paginationForm" theme="simple">
			<s:hidden name="name1"></s:hidden>
			<div class="col-lg-12 col-md-12" style="padding:0px;">
				<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
					Total:<label class="text-info"><s:property value="totalRecords" /></label>
				</div>
				<%@ include file="/common/pages/pagination.jsp"%>
			</div>
		</s:form>

</s:if>
											

											
										</div>
									</div>
								</div>
							</div>
						</div>





