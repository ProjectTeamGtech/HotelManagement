<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<%@page import="com.apm.main.common.constants.Constants"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<script>
$(window).on('load', function() {
	$('#dashboardloaderPopup').modal('hide');
});
		
	  
    $(document).ready(function() {
    	$('#dashboardloaderPopup').modal('show');
    });
    	</script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>New Charge Type</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="col-lg-12 col-md-12 topback2">
	<div class="col-lg-3 col-md-2">
			<s:select list="masterlist"
					cssClass="form-control showToolTip chosen-select" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)"></s:select>
		</div>
		<div class="col-lg-1 col-md-1">
			<a class="btn btn-primary" href="#" onclick="opencPopup('addNewChargeType')"><i
				class="fa fa-plus"></i> Add</a>
		</div>
		<div class="col-lg-3 col-md-2">
			
		</div>
		<div class="col-lg-1 col-md-1">
			
			</div>
<s:form action="NewChargeType">	
		<div class="col-lg-3 col-md-2">
			<s:textfield theme="simple" name="searchText" placeholder="Search By Name"  cssClass="form-control" />
			
		</div>
		<div class="col-lg-1 col-md-1">
			<input type="submit" value="Go" onclick="shownewpopup()" class="btn btn-primary"/>
		</div>
</s:form>
	
	<!--<div class="col-lg-3 col-md-2">
	<label>Select Master</label>
	<s:select list="masterlist" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
	</div>
	<div class="col-lg-6 col-md-8">
		<a class="btn btn-primary" href="addNewChargeType" style="margin-top:21px;"><i
			class="fa fa-plus"></i> Add</a>
	</div>
	<div class="col-lg-3 col-md-2"></div>
--></div>
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
					  <th class="text-center"><label style="margin-top: 4px !important;" class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input type="checkbox" id="selectallid" onclick="setAll(this)" /></label></th>
						<th class="text-center"><a href="#" onclick="fnPagination(6,'occupation');" style="color:#fff;">Name</a></th>
						<!-- <th class="text-center">Ward</th> -->
						<th class="text-center">Department</th>
						<th class="text-center">Procedure</th>
						<th class="text-center">Consultant Compulsory </th>
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="new_chargetye_list!=null">
						<s:iterator value="new_chargetye_list" status="rowstatus">
							<tr>
							<s:if test="breakage==1">
							 <td class="hidden-print"><input checked="checked" type="checkbox" class="case setchk" value="<s:property value="id"/>" class="form-control" name="breakage" id="breakage" onchange="updatebreakage(<s:property value="id"/>,this.checked);"/></td>
							</s:if>
							<s:else>
							 <td class="hidden-print"><input type="checkbox" class="case setchk" value="<s:property value="id"/>" class="form-control" name="breakage" id="breakage" onclick="updatebreakage(<s:property value="id"/>,this.checked);"/></td>
							</s:else>
							
								<td class="text-center"><s:property value="name" /></td>
								<%-- <td class="text-center"><s:property value="wardid" /></td> --%>
							   <td class="text-center"><s:property value="description" /></td> 
								
								<%-- <td><s:select list="schedulerlist" listKey="id" listValue="taskname" name="subtask_name[0].taskname" title="select task type"
										cssClass="form-control"  > </s:select>
										
										</td> --%>
								
								<s:if test="procedure==1">
									 <td class="text-center">Yes</td>                       
								</s:if>
								<s:else>
								 	<td class="text-center">No</td>
								</s:else>
								<s:if test="consultant_compulsay">
								 <td class="text-center">Yes</td>
								</s:if>
								<s:else>
								 <td class="text-center">No</td>
								</s:else>
								<s:hidden value="%{id}" name="id"></s:hidden>
								<s:url action="editNewChargeType" id="edit">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<!--<td class="text-center"><s:a href="%{edit}"
										class="text-warning">
										<i class="fa fa-edit"></i>
									</s:a></td>
								-->
								<td class="text-center"><a href="#" onclick="opencPopup('editNewChargeType?selectedid=<s:property value="id" />')"><i class="fa fa-edit"></i></a></td>
								
								<s:url action="deleteNewChargeType" id="delete">
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
						There is no New ChargeType found!!
					</s:else>
			</table>
		</div>
	</div>
</div>
											

<s:form action="NewChargeType" name="paginationForm" id="paginationForm" theme="simple">
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



<!-- Loader -->
	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>
	<!--End loader  -->						

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
<%-- <s:form action="Occupation" name="paginationForm" id="paginationForm"
	theme="simple">

	<div class="row">
		<div class="col-lg-4 col-md-4 text-right">
			Showing all <label class="text-info">(<s:property
					value="pagerecords" /> of <s:property value="totalRecords" />
				Records)
			</label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>

</s:form> --%>