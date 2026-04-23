<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="tools/js/emailTemplate.js"></script>
<div class="row">
<div class="col-lg-12 col-md-12">
	<div class="input-group">
		<input type="text" name="searchText" id="searchText1"
			placeholder="Search by first,last name,mob no.,postcode" class="form-control">
			 <span class="input-group-btn">
			<button class="btn btn-primary" type="button"
				onclick="searchTemplatePatient()">Go!</button>
		</span>
	</div>
	<!-- /input-group -->
</div>
</div>
<!-- /.col-lg-6 -->
<br />

<input type="hidden" id="user" name="user">
<input type="hidden" id="id" name="id">
<div class="row">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="allTemplatePatient"
				class="table table-hover table-condensed table-bordered">
				<%-- <thead>

					<tr>

						<th>Name</th>
						<th>Mobile No</th>
						<th>Email</th>

					</tr>
				</thead>
				<tbody>
					<s:if test="allPatientList.size!=0">
						<s:iterator value="allPatientList" status="rowstatus">
							<tr>
								<s:if test="#rowstatus.even == true">
									<tr class="ac_odd">
								</s:if>



								<td style="corsor: pointer;"><a href=" "
									onclick="return setClientName('<s:property  value="firstName" /><s:property  value="lastName" />','<s:property  value="id" />','<s:property  value="thirdPartyType" />','<s:property  value="thirdPartyTypeName" />')"><s:property
											value="firstName" />
										<s:property value="lastName" /> </a></td>


								<td><s:property value="mobNo" /></td>
								<td><s:property value="email" /></td>

							</tr>

						</s:iterator>
					</s:if>

				</tbody> --%>
			</table>

		</div>
	</div>
</div>


