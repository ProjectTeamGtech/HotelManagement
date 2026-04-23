
<%@taglib uri="/struts-tags" prefix="s" %>		
			
		

<s:if test="actionType!='dashboard'">
	
<table width="1000%" align="center" style="background-color: rgb(216, 212, 212)">
	<tr>
	<s:if test="actionType=='week'">
		<td style="font-size: 20px;"><span style="font-weight:bold;">Practitioner : </span> <s:property value="diaryUser"/></td>
	</s:if>
	<s:if test="actionType=='day'">
		<td style="font-size: 20px; font-weight: bold;">Daily Schedule For <s:property value="commencing"/></td>
	</s:if>	
		
	</tr>
	<s:if test="actionType=='day'">
		<tr>
			<td style="font-weight: bold; font-size: 18px;">Practitioner : <s:property value="diaryUser"/><span style="margin-left:70px;font-size:14px;">Location : <s:property value="locationName"/></span>
			<br><br><span style="font-size: 15px;font-weight: bold;">Daily Notes:</span></td>
			
			
		</tr>
		
		
	</s:if>
	
</table>

</s:if>
	
	<div class="row">
		<div class="col-lg-12">
			<s:actionmessage cssClass="messageDiv" />
			<div class="table-responsive">
				<table class="table-bordered table-hover table-condensed width-full" style="font-size: 16px;">
				<s:if test="actionType=='day'">
					<col width="5%"/>
					<col width="5%"/>
					<col width="20%"/>
					<col width="40%"/>
					<col width="20%"/>
				</s:if>
				<s:elseif test="actionType=='dashboard'">
					<col width="10%"/>
					<col width="10%"/>
					<col width="5%"/>
					<col width="5%"/>
					<col width="20%"/>
					<col width="30%"/>
					<col width="20%"/>
					
				</s:elseif>
				<s:else>
					<col width="10%"/>
					<col width="5%"/>
					<col width="5%"/>
					<col width="20%"/>
					<col width="30%"/>
					<col width="20%"/>
					
					
				</s:else>
				
					<thead>
						<tr>
						
					
						
							<!-- <th>Date</th> -->
							<s:if test="actionType=='dashboard'">
								<th>User</th>
							</s:if>
							<s:if test="actionType!='day'">
								<th style="text-align: center;">Date</th>
							</s:if>
							<th style="text-align: center;">Time</th>
							<th style="text-align: center;">Duration</th>
							<!--<th>Patient</th>
							<th>Appointment Name</th>
							--><th>Note</th>
							
							<th>Completed</th>
							
						</tr>
					</thead>
					<tbody>
						<s:if test="practitionerApmtList.size!=0">
							<s:iterator value="practitionerApmtList" status="rowstatus">
								<tr id="<s:property  value="id" />">
									
									<s:if test="actionType=='dashboard'">
										<td><s:property value="diaryUser" /></td>
									</s:if>
									<s:if test="actionType!='day'">
										<td style="text-align: center;"><s:property value="commencing" /></td>
									</s:if>
									<td style="text-align: center;"><s:property value="sTime" /></td>
									<td style="text-align: center;"><s:property value="apmtDuration" /></td>
									<s:if test="status==0">
										<!--<td><s:property value="client" /></td>
										<td><s:property value="apmtType" /></td>
										--><td><s:property value="notes" /></td>
										
									</s:if>
									<s:elseif  test="status==1">
									<!--<td><s:property value="client" /></td>
										--><s:if test="status==0">
											<!--<td><s:property value="apmtType" /></td>
										--></s:if>
										<s:else>
											<!--<td><s:property value="apmtType" /></td>
										--></s:else>
										
										<td><s:property value="notes" /></td>
										
										<td>
											<s:if test="workcompleted==0">
												Not Completed
											</s:if>
											<s:else>
												<s:property value="workcompleted" />
											</s:else>
											
										</td>
										
									</s:elseif>
									<s:else>
										<td></td>
										<td></td>
										<td></td>
									</s:else>
									
									
									
									
									
									</tr>
								</s:iterator>
								
								
								
							</s:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		
		<div class="row" >
			
				<div class="col-lg-2 col-md-2 hidden-print">
						<input style="font-size: 16px;padding-left: 20px;padding-right: 20px;" type="submit" class="btn btn-primary" value="Print" onclick="printpage()">
			
				</div>
				</div><br>
