<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Clinical Problem Master</h4>

									</div>
								</div>
								
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<br><p></p>
								<div class="col-lg-1 col-md-1">
			<a class="btn btn-primary" href="#" onclick="opencPopup('addclinicalproblemmsterMaster')"><i
				class="fa fa-plus"></i> Add</a>
		</div>
								</div>
								<br><p></p>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<p></p>
								<p></p>
								<table class="my-table xlstable" style="width: 100%">
								<tr>
								<th class="text-center">name</th>
								<th class="text-center">Edit</th>
								<th class="text-center">Delete</th>
								</tr>
								<s:iterator value="clinicalproblemlist">
								<tr>
								<td><s:property value="name"/></td>
								<td><a  href='editclinicalproblemMaster?id=<s:property value="id"/>'><i class="fa fa-edit"></i></a></td>
								<td><a href='deleteclinicalproblemmsterMaster?id=<s:property value="id"/>'><i class="fa fa-trash-o"></i></a></td>
								</tr>
								</s:iterator>
								</table>
								</div>
</div>