<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="common/js/login.js"></script>

<div class="">
<div class="">
<div class="row details">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

<h4>Login Status</h4>

</div>
</div>
<div class="row ">
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
<div>
<div class="col-lg-12 col-md-12 topback2">
		<div class="col-lg-1 col-md-1">
			
	
	</div>
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
			<th class="text-center">User Id</th>
			<th class="text-center">LoginStatus</th>
			<th class="text-center">Edit</th>
			
		</tr>
	</thead>
	 <tbody>
	 <%int i=1; %>
		<s:if test="loginlist!=null">
			<s:iterator value="loginlist" status="rowstatus">
				<tr>
					<td class="text-center"><%=i %></td>
					<td class="text-center"><s:property value="userid" /></td>
					<td class="text-center"><button style="border-radius:10px;  background-color: green; color: white; width: 50px;">Active</button></td>
					<%-- <td class="text-center"><a href="updateStatusUserProfile?selectedid=<s:property value="id" />"
					onclick="return ConfirmUpdate()" class="text-success"><i class="fa fa-sign-out"></i> </a></td> --%>
		  <td class="text-center"><a href="#" onclick="updatelogin(<s:property value="id" />)" class="text-success"><i class="fa fa-sign-out"></i> </a></td> 
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

<script type="text/javascript">
function ConfirmUpdate() {
	var x = confirm("Are you sure you want to Update Login Status?");
		if (x)
  		return true;
		else
		return false;
}
</script>

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