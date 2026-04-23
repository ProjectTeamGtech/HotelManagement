<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="registration/js/clinic.js"></script>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>All Location List</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
										
										
										
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
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id="results"
				class="table  table-bordered ">
				<thead>
					<tr>
						<!-- <th class="text-center">Include</th> -->
						<th>Address Type</th>
						<th>City</th>
						<th>Location</th>
						<th>Post Code</th>
						<th>Contact No</th>
						<th>View</th>
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="cliniclocationList.size!=0">
						<s:iterator value="cliniclocationList" status="rowstatus">
							<tr>				
							<s:hidden name="locationid" id="locationid"></s:hidden>				
								<%-- <td class="text-center">
									<input type="checkbox" name="checkLocation" id="checkLocation"  title="In all Communications" onclick="checkLocationField('<s:property value="locationid"/>')" /> 								
								
									<s:checkbox name="checkLocation" id="checkLocation"  title="In all Communications" onclick="checkLocationField('%{locationid}')" theme="simple"/> 								
								</td> --%>
								<td><s:property value="addressType" /></td>
								<td> <s:property value="city" /></td>
								<td><s:property value="locationname" /></td>
								<td><s:property value="pinCode" /></td>
								<td><s:property value="contactNo" /></td>

								<s:hidden value="%{locationid}" name="id" id="id"></s:hidden>
								<s:url action="viewLocationClinicRegistration" id="edit">
									<s:param name="selectedid" value="%{locationid}"></s:param>
								</s:url>
								<td><s:a href="%{edit}"
										class="text-warning">
										View
									</s:a></td>
								<s:url action="editLocationClinicRegistration" id="edit">
									<s:param name="selectedid" value="%{locationid}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{edit}"
										class="text-warning">
										<i class="fa fa-edit"></i>
									</s:a></td>
								<s:url action="deleteLocationClinicRegistration" id="delete">
									<s:param name="selectedid" value="%{locationid}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{delete}"
										onclick="return confirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i>
										
									</s:a></td>
							</tr>

						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
						There is no Department found!!
					</s:else>
			</table>
		</div>
	</div>
</div>

											

											
										</div>
									</div>
								</div>
							</div>
						</div>






