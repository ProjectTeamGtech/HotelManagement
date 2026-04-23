<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Master.eu.entity.*"%>



<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Specialize</h4>

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
		<a class="btn btn-primary" href="#" style="margin-top:21px;" onclick="opencPopup('addSpecialize')"><i
			class="fa fa-plus"></i> Add</a>
	</div>
	<div class="col-lg-3 col-md-2"></div>
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
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-left sortable <s:if test="#attr.pagination.sortColumn.equals('specialization_text')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'specialization_text');" style="color:#fff;">Specialization</a></th>
						<th class="text-center">Description</th>
						<th class="text-left">Date & Time</th>
						<th class="text-center" style="width: 5%;">Edit</th>
						<th class="text-center" style="width: 5%;">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="specializationList.size!=0">
					<s:iterator value="specializationList" status="rowstatus">
					<tr>					
						<td><s:property value="specialization"/></td>
						<td><s:property value="description"/></td>
						<td><s:property value="datetime"/></td>
						
						<s:hidden value="%{id}" name="id"/>
						
						<s:url action="editSpecialize" id="edit">
							<s:param name="selectedid" value="%{id}"/>
						</s:url>
						<td class="text-center"><s:a href="%{edit}" class="text-warning">
								<i class="fa fa-edit"></i></s:a></td>
								
						<s:url action="deleteSpecialize" id="delete">
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
						<i class="fa fa-times text-danger"></i> No Specialization Found!!
					</h3>
				</s:else>
			</table>
		</div>
	</div>
</div>

<s:form action="Specialize" name="paginationForm" id="paginationForm" theme="simple">
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



  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
