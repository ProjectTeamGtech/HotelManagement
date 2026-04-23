<%@taglib uri="/struts-tags" prefix="s" %>

<%-- <script type="text/javascript" src="diarymanagement/js/nonavailableslot.js"></script> --%>

<div id="login_main" class="main_layout_content">
<h2 class="heading">Client Third Party Charge</h2>
	<div id="login" class="block_div">
		<span class="error"><s:actionerror id="server_side_error"/></span>
				<s:form action="updateThirdPartyDetailsClient" id="form" theme="simple">
		
			<div class="form_elements">	
				<table width="50%" style="border: 1px solid #e69623; height: auto; padding: 1em; ">
					<col width="10%"/>
					<col width="2%"/>
					<col width="30%"/>
					<tr>
					<td>Client</td>
					<td><s:textfield name="client" id="client" size="25" readonly="true" theme="simple"/></td>
					<s:hidden name="clientId" id = "clientId" ></s:hidden>  
					<td><a href="Client" target="blank" onclick="window.open(this.href, 'mywin',
					'left=20,top=20,width=600,height=500,toolbar=1,resizable=0'); return false;"><input type="button" value=" Search "></a></td>
					</tr>
					<tr>
					<td>Third Party Type</td>
					<td><s:select id="type" name="type" list="thirdPartyTypeList" listKey="id" listValue="type" headerKey="0" theme="simple" headerValue="Select Type" onchange="setTypeName(this.value)"/></td>
					
					</tr>
					<tr>
					<td>Third Party Name</td>
					<td><s:select id="typeName" name="typeName" list="thirdPartyTypeNameList" listKey="id" listValue="typeName" headerKey="0" theme="simple" headerValue="Select Name"/></td>
					
					</tr>
					<tr>
					<td><s:submit value = "Save"/></td>
					</tr>
					</table>
				</div>
				</s:form>
	</div>
</div>-