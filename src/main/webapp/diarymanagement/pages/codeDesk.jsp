<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<script type="text/javascript" src="diarymanagement/js/completeApmt.js"></script>

<body>
<div>
<table>
<tr>
<td>Client: </td>
<td><s:textfield name = "user" id = "user" theme="simple"/></td>
<td><s:textfield name = "commencing" id = "commencing" theme="simple"/></td>

</tr>
<tr>
<td>Practitioner:</td>
<td><s:textfield name = "practitionerName" id = "practitionerName" theme="simple"/></td>
</tr>


</table>
</div>

<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:30%;">
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Id</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Appointment Type</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Charge</th>
						<!--<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
						
					--></tr>
					<s:if test="clientChargeListDetail.size!=0">
						<s:iterator value="clientChargeListDetail">
						<tr>	
									<td><s:property  value="id" /></td>
									<td><s:property  value="apmtType" /></td>
									<td><s:property  value="charges" /></td><!--
									
									<td><a href="" onclick = "return confirmedDelete('<s:property  value="id" />')"><img src="common/images/delete.gif"></img></a></td>	
						--></tr>
					
						</s:iterator>
						<tr>
						<td colspan="2"><b>Total</b></td>
						<td><b><s:property  value="chargeTotal" /><b></td>
						
						<tr>
					</s:if>
					<s:else>
						There is no Client found!!
					</s:else>
							
				</table>



</body>
</html>
