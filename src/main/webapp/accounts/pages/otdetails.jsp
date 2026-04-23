 <%@ taglib uri="/struts-tags" prefix="s"%>
 <div class="row col-lg-8 col-md-12 col-xs-12 col-sm-12">

                <div class="">
                    <div class="panel-body">
                       
                        <div class="row marto">
                           <link href="common/css/printpreview.css" rel="stylesheet" />
							<%@ include file="/accounts/pages/letterhead.jsp" %>
							
                        </div>
                 </div>   
             </div> 
          </div>
         
 <div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	</div>
</div>			<table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr>
					
					
		
					    	<th>OT Room</th>
							<th>Patient</th>
							<th>Payee</th>
							<th>Date</th>
							<th>Category</th>
							<th>Ward / Bed</th>
							<th>Procedures</th>
							<th>Charge Type</th>
							<th>Charge</th>
							<th>Status</th>
							<th>Send SMS</th>
						
						</tr>
						</thead>
					
							<tbody>
								<s:iterator value="otDetailsList" status="rowstatus">
							<tr>
								<td><s:property value="otname"/></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="whopay"/></td>
								<td><s:property value="commencing"/></td>
								<td><s:property value="category"/></td>
								<td><s:property value="wardbed"/></td>
								<td><s:property value="procedure"/></td>
								<td><s:property value="apmttypetext"/></td>
								<td><s:property value="chargeamout"/></td>
								<s:if test="status==0">
									<td>Pending</td>
								</s:if>
								<s:else>
									<td>Completed</td>
								</s:else>
								
								<td><a href="otsmStatement?id=<s:property value="id"/>&clientid=<s:property value="clientId"/>&procedure=<s:property value="procedure"/>&amount=<s:property value="chargeamout"/>">
								<s:if test="otmsg==0">
									Send SMS
								</s:if>
								<s:else>
									Sent
								</s:else>
								</a></td>
							</tr>
						
						</s:iterator>

					
							</tbody>
							
						</table>
						
					<br>	
					 <div class="row col-lg-12 col-md-12 col-xs-12 col-sm-12">
		            <div class="form-group text-right hidden-print">
                                    	<!--<a type="button" onclick="openPopup('checkavailabilityPharmacy?selectedid=<s:property value="id"/>')" class="btn btn-primary btn-lg">Check Availability</a>
                                    	--><a type="button" class="btn btn-primary btn-lg"  title="Print" onclick="printpage()">Print</a>
                                    </div>
                              </div>        