<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%request.setCharacterEncoding("UTF-8");response.setCharacterEncoding("UTF-8"); %>
<div class="row details">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

<h4>All Dosage List</h4>

</div>


</div>

	<div class="col-lg-3 col-md-2 hidden">
			<s:select list="masterlist"
					cssClass="form-control showToolTip chosen-select" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)"></s:select>
		</div>
		<div class="col-lg-1 col-md-1">
			<a class="btn btn-primary" href="#" onclick="opencPopup('adddosageMaster')"><i
				class="fa fa-plus"></i> Add</a>
		</div>
		
		
		
	<div class="col-lg-12 col-md-12">
	<table id="results"
	class="table table-hove table-bordered table-striped table-condensed">
	<thead>
		<tr>
			<th class="text-center">ID</th>
			<th class="text-center"> Name</th>
			<th class="text-center">Regional</th>
			
		</tr>
	</thead>
	<tbody>
	<%int index=0;
	ArrayList<Master> list=(ArrayList<Master>) request.getAttribute("list"); %>
	<s:iterator value="list" status="rowstatus">
				<tr>
					<td class="text-center"><s:property value="id" /></td>
					<td class="text-center"><s:property value="name" /></td>
					<td class="text-center"><%=DateTimeUtils.isNull(list.get(index).getRegional()).toString() %><%index++; %></td>
				</tr>
	</s:iterator>				
	</tbody>
	</table>
	</div>		