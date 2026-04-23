<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.Date"%>

	<div class="row details">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
</div><h4>&nbsp;&nbsp;Vaccination master</h4>
</div>
	<div class="row ">
		<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
		<br>									



<div class="form-inline">
			<div class="form-group">
			<div class="form-group">
		
		<%-- 		<s:select name="treatmenttype" id="treatmenttype" 
				list="#{'opd':'OPD','ipd':'IPD'}"
				cssClass="form-control chosen-select" ></s:select> --%>
			</div>
			
			
				<%-- 	<div class="form-group">
					<s:submit value="go" theme="simple" cssClass="btn btn-primary"></s:submit>
					</div> --%>
					<div class="form-group">
					<button class="btn btn-primary" onclick="opencPopup('addvacinationmstrMaster')">Add</button>
					</div>
			<br>
			<br>
			</div>
			</div>
			
		
			<table class="my-table xlstable" style="width: 100%">
			<tr>
				<th>Sr no</th>
				<th ><center>Vaccination Name</center></th>
				<th class="text-center"><center>edit</center></th>
				<th class="text-center"><center>delete</center></th>
			</tr><%int i=0; %>
				<s:iterator value="vacinationlist">
					<tr>
						<td class=""><%=++i %></td>
						<td class="text-center"><s:property value="vacinname"/></td>
						<td class="text-center"> <a href="#" onclick="opencPopup('editvacinationmstrMaster?id=<s:property value="id" />')"> <i class="fa fa-edit"></i></a></td>
						<td class="text-center"><a href="deletevacinationmstrMaster?id=<s:property value="id"/>"><i class="fa fa-trash-o"></i></a></td>	
		  
					</tr>
				</s:iterator>
			</table>
		<br>
	
</div>
<br>
<br>

</div>
