<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Daily Notes</h4>

									</div>
								</div>
								<%int i=1; %>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<br><p></p>
								<div class="col-lg-1 col-md-1">
								<s:hidden name="ipdid" id="ipdid"></s:hidden>
			<a class="btn btn-primary" href="openDailynotesStatement?isnote=1&ipdid=<s:property value="ipdid"/>&clientId=<s:property value="clientId"/>" ><i
				class="fa fa-plus"></i>Add New</a>
		</div>
								</div>
								<br><p></p>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<p></p>
								<p></p>
								<table class="my-table xlstable" style="width: 100%">
								<tr>
								<th class="text-center">Id</th>
								<th class="text-center">Date</th>
								<th class="text-center">View</th>
								<th class="text-center">Edit</th>
								
								</tr>
								<s:iterator value="dailynoteslist">
								<tr>
								<td><%=i++ %></td>
								<td><s:property value="date_time"/></td>
								<!-- @ manoj sir change url printConsulatationForm --><td><a href="printnoteCommonnew?clientId=<s:property value="clientId"/>&id=<s:property value="Id"/>&apmtId=<s:property value="apmt_id"/>&date_time=<s:property value="date_time"/>"><i class="fa fa-print" ></i></a></td>    
								<td><a href='editdailynotesStatement?id=<s:property value="id"/>'><i class="fa fa-edit"></i></a></td>
								<%-- <td><a href='deleteclinicalnotesmstrMaster?id=<s:property value="id"/>'><i class="fa fa-trash-o"></i></a></td> --%>
								</tr>
								</s:iterator>
								</table>
								</div>
</div>