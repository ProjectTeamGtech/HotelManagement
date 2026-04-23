<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>

<script type="text/javascript"
	src="diarymanagement/js/pendingCharges.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Track Pending Charges</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<s:form action="showPendingChargesCompleteApmt" id="pendingCharge_form"
	theme="simple">


	<div class="col-lg-12 col-md-12 topback2">
		<div class="col-lg-3 col-md-3">
			<s:if test="%{#userList != 'null'}">
				<s:select id="diaryUser" name="diaryUser" list="userList"
					listKey="id" listValue="diaryUser" headerKey="0" theme="simple"
					headerValue="All" cssClass="form-control" />
			</s:if>
		</div>
		<div class="col-lg-3 col-md-3">
			<s:if test="%{#locationList != 'null'}">
				<s:select id="location" name="location" list="locationList"
					listKey="locationid" headerKey="0" headerValue="All"
					listValue="location" theme="simple" cssClass="form-control" />
			</s:if>
		</div>


		<div class="col-lg-3 col-md-3">
			<%
				String currentDate = DateTimeUtils
							.getDateinSimpleFormate(new Date());
					String temp[] = currentDate.split(" ");
					String temp1[] = temp[0].split("-");
					String date = temp1[0] + "/" + temp1[1] + "/" + temp1[2];
			%>
			
			 <div class="input-group ">
                 
				<s:textfield id="commencing" name="commencing"
				cssClass="form-control" aria-describedby="basic-addon1"></s:textfield>              </div><!-- /input-group -->	
		</div>

		<div class="col-lg-3 col-md-3">
			<s:submit value="Show" cssClass="btn btn-primary"></s:submit>


		</div>
	</div>
	
</s:form>
<div class="">

	<table class="table table-hover table-condensed table-bordered">
		<thead>
			<tr>
				
								<th>Date</th>
                              <th>Start Time</th>
                              <th>End Time</th>
                              <th>Client</th>
                              <th>Appointment</th>
                              <th>Complete</th>


			</tr>
		</thead>
		<tbody>
			<s:if test="pendingChargesList.size!=0">
				<s:iterator value="pendingChargesList" status="rowstatus">
					<s:if test="dna==1">
						<tr style="background-color: orange;">
							<td><s:property value="commencing" /></td>
							<td><s:property value="startTime" /></td>
							<td><s:property value="endTime" /></td>
							<td><s:property value="client" /></td>
							
							<td><s:property value="apmtypeText" /></td>
							<td><input type="button" id="completeapmt"
								value="Action" class="btn btn-primary"
								onclick="completePendingApmt('<s:property value ="client"/>','<s:property value ="clientId"/>','<s:property value = "diaryUserId"/>','<s:property value = "diaryUser"/>','<s:property value = "treatmentEpisodeId"/>','<s:property value = "charges"/>','<s:property value = "duration"/>','<s:property value = "startTime"/>','<s:property value = "apmtType"/>','<s:property value = "appointmentTypeId"/>','<s:property value = "id"/>','<s:property value = "commencing"/>','<s:property value="apmtypeText" />','<s:property value = "location" />');"></td>

						</tr>

					</s:if>
					<s:else>
						<tr>

							<td><s:property value="commencing" /></td>
							<td><s:property value="startTime" /></td>
							<td><s:property value="endTime" /></td>
							<td><s:property value="client" /></td>
							<td><s:property value="apmtypeText" /></td>
							<td><input type="button" id="completeapmt"
								class="btn btn-primary" 
								value="Action"
								onclick="completePendingApmt('<s:property value ="client"/>','<s:property value ="clientId"/>','<s:property value = "diaryUserId"/>','<s:property value = "diaryUser"/>','<s:property value = "treatmentEpisodeId"/>','<s:property value = "charges"/>','<s:property value = "duration"/>','<s:property value = "startTime"/>','<s:property value = "apmtType"/>','<s:property value = "appointmentTypeId"/>','<s:property value = "id"/>','<s:property value = "commencing"/>','<s:property value="apmtypeText" />','<s:property value = "location" />');"></td>

						</tr>
					</s:else>
				</s:iterator>
			</s:if>

		</tbody>
	</table>

</div>


<div id="completeAppointmentDivId1" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
<h4 class="modal-title" id="completeAmtTitle">Complete Appointment</h4>			</div>
			<div class="modal-body" style="height: 600px;overflow: scroll;">
			
				<%@ include file="/diarymanagement/pages/pendingApmtComplete.jsp"%>
			
			</div>
			<div class="modal-footer">
					<button type="button" class="btn btn-primary" 
						onclick="createChargeAndUpdateAccount1('Charge')">Create
						Charge & Update Account</button>
					
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			
		</div>
	</div>
</div>





<%-- <s:if test="pendingChargesList!=null">
	
<s:form action="showPendingChargesCompleteApmt" name="paginationForm" id="paginationForm" theme="simple">
<s:hidden id="diaryUser1" name="diaryUser1"></s:hidden>
		<s:hidden id="location1" name="location1"></s:hidden>
		<s:hidden id="commencing1" name="commencing1"></s:hidden>
		<div class="row">
		<div class="col-lg-4 col-md-4 text-right">Showing all <b class="text-info">(<s:property value="pagerecords" /> of <s:property value="totalRecords" />
						Records)</b></div>		 
						<%@ include file="/common/pages/pagination.jsp"%>
		</div>
				
				

			
	</s:form>
</s:if> --%>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>



