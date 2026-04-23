<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>
<div class="">
<div class="">
<div class="row details">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

<h4>All NursingCare Plan </h4>

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
			<a class="btn btn-primary" href="#" onclick="opencPopup('addNursingCarePlanning')"><i
				class="fa fa-plus"></i> Add</a>
		</div>
		<div class="col-lg-3 col-md-2">
			
		</div>
		<div class="col-lg-1 col-md-1">
			
			</div>
	<s:form action="NursingCarePlanning">	
		<div class="col-lg-3 col-md-2">
			<s:textfield theme="simple" name="searchText" placeholder="Search By Plan"  cssClass="form-control" />
			
		</div>
		<div class="col-lg-1 col-md-1">
			<input type="submit" value="Go" class="btn btn-primary"/>
		</div>
	</s:form>
	</div>

<div class="row">
<div class="col-lg-12"><s:hidden name="message" id="message"></s:hidden>
<s:if test="hasActionMessages()">
	<script>
				var msg = " " + document.getElementById('message').value;
				showGrowl('', msg, 'success', 'fa fa-check');
			</script>
</s:if></div>
</div>

<div class="row">
<div class="col-lg-12">
<div class="">
<table id="results"
	class="table table-hove table-bordered table-striped table-condensed">
	<thead>
		<tr>
			<th class="text-center">ID</th>
			<th class="text-center">Diagnosis Name</th>
			<th class="text-center">Plan Name</th>
			<th class="text-center">Edit</th>
			<th class="text-center">Delete</th>
		</tr>
	</thead>
	<tbody>
		
			<s:iterator value="nursingplanninglist" status="rowstatus">
				<tr>
					<td class="text-center"><s:property value="id" /></td>
					<td class="text-center"><s:property value="nursing_diagnosis" /></td>
					<td class="text-center"><s:property value="nursing_planning" /></td>
					<td class="text-center"><a href="editNursingCarePlanning?id=<s:property value="id"/>"> <i class="fa fa-edit"></i> </a></td>
					<td class="text-center"><a href="deleteNursingCarePlanning?id=<s:property value="id"/>"
						onclick="return ConfirmDelete()" class="text-danger"> <i
						class="fa fa-trash-o"></i> </a></td>
				</tr>
			</s:iterator>
		
	</tbody>
</table>
</div>
</div>
</div>

<s:form action="NursingCarePlanning" name="paginationForm" id="paginationForm"
	theme="simple">

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