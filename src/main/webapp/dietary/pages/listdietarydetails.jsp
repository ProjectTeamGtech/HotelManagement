<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="/dietary/js/dietary.js"></script>
<script type="text/javascript">
function ConfirmDelete()
	{
  		var x = confirm("Are you sure you want to delete?");
  		if (x)
      		return true;
  		else
    		return false;
	}
</script>
<div class="">
<div class="">
<div class="row details">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

<h4>All Dietary Details List</h4>

</div>
</div>
<div class="row ">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div>
<div class="col-lg-12 col-md-12 topback2">
<div class="col-lg-3 col-md-2">
	<label>Select Master</label> <s:select list="masterlist" name="mastername" listKey="id" listValue="name" onchange="selectAction(this.value)"
	cssClass="form-control showToolTip chosen-select"></s:select></div>
	
<div class="col-lg-6 col-md-8"><!--<a class="btn btn-primary" href="#" onclick="calladd()" style="margin-top:21px;"><i
			class="fa fa-plus"></i> Add</a>  --> <a class="btn btn-primary"
	href="addDietarydetails" style="margin-top: 21px;"><i
	class="fa fa-plus"></i> Add</a></div>
<div class="col-lg-3 col-md-2"></div>
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
			<th class="text-center">Category_ID</th>
			<th class="text-center">Name</th>
			<th class="text-center">Energy</th>
			<th class="text-center">Protein</th>
			<th class="text-center">Edit</th>
			<th class="text-center">Delete</th>
		</tr>
	</thead>
	<tbody>
		<s:if test="listdietarydetails!=null">
			<s:iterator value="listdietarydetails" status="rowstatus">
				<tr>
					<td class="text-center"><s:property value="id" /></td>
					<td class="text-center"><s:property value="categoryid" /></td>
					<td class="text-center"><s:property value="name" /></td>
					<td class="text-center"><s:property value="energy" /></td>
					<td class="text-center"><s:property value="protein" /></td>
					
					<td class="text-center"><a href="editDietarydetails?id=<s:property value="id"/>"> <i class="fa fa-edit"></i> </a></td>
					<td class="text-center"><a href="delDietarydetails?id=<s:property value="id"/>"
						onclick="return ConfirmDelete()" class="text-danger"> <i
						class="fa fa-trash-o"></i> </a></td>
				</tr>
			</s:iterator>
		</s:if>
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
