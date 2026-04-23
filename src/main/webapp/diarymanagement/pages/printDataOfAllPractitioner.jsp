<%@taglib uri="/struts-tags" prefix="s" %>
<div id="login_main" class="main_layout_content">
	
	<div id="login" class="block_div">
		
		
					<div class="form_elements">	
						
				
				<table id="printData" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%; ">
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Practitioner</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Time</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Duration</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Patient</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Appointment Type</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Note</th>
				
					</tr>
					<s:if test="practitionerApmtList.size!=0">
						<s:iterator value="practitionerApmtList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									<td><s:property  value="diaryUser" /></td>
									<td><s:property  value="sTime" /></td>
									<td><s:property  value="apmtDuration" /></td>
									<td><s:property  value="client" /></td>
									<td><s:property  value="apmtType" /></td>
									<td><s:property  value="notes" /></td>	
										
							</tr>

						</s:iterator>
					</s:if>
					</table>
					
			
	</div>
</div>