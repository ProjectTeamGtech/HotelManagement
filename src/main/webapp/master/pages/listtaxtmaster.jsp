<%@taglib uri="/struts-tags" prefix="s"%>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

				<h4>YUVICARE Tax Master </h4>

			</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="height: 50px;padding:10px; ">
<input type="button" class="btn btn-primary" value ="Add New +" onclick="openPopup('addtoTaxmasterMaster')">

</div>


<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
	<table class="my-table xlstable" style="width:100%;text-align:left;" >
	<tr style="text-align: center !important;">
	<th>Sr.No</th>
	<th>Name</th>
	<th>Percent</th>
	<th>Edit</th>
	<th>Delete</th>
	</tr>
	<s:iterator value="list">
	<tr>
	<td><s:property value='id'/></td>
	<td><s:property value='name'/></td>
	<td><s:property value='percent'/></td>
	<td><a onclick="openPopup('edittaxmasterMaster?id=<s:property value='id'/>')"><i class="fa fa-edit"></i></a></td>
	<td><a href="deletetaxmasterMaster?id=<s:property value='id'/>"><i class='fa fa-trash'></i></a></td>
	</tr>
	</s:iterator>
	</table>
</div>
</div>