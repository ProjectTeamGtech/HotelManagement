  <%@taglib uri="/struts-tags" prefix="s"%>
  <%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
  	 <%
		
  	 LoginInfo loginInfo2 = LoginHelper.getLoginInfo(request);
  	 String notkunal="";
  	 if(loginInfo2.getIskunal()==0){
  		 notkunal="hidden";
  	 }
		   %>
<script type="text/javascript" src="diarymanagement/js/viewClientProfile.js"></script><%-- 
<script type="text/javascript" src="assesmentForms/js/Predefine/assessmentTemplate.js"></script> --%>
<div class="row">
<div class="col-lg-12 col-md-12">
	<div class="input-group">
		<input type="text" name="searchText" id="searchText1"
			placeholder="Search by first,last name,mob no.,postcode" class="form-control" style="width: 50%">
		
			
			<input type="text" name='ipdno' id='ipdno' style="width: 20%;margin-left: 30px;" class='form-control <%=notkunal %>' placeholder="Search By IP No">
			
			 <span class="input-group-btn">
			<button class="btn btn-primary" type="button"
				onclick="searchPatient()">Go!</button>
		</span>
	</div>
	<!-- /input-group -->
</div>
</div>
<!-- /.col-lg-6 -->
<br />

<input type="hidden" id="client" name="client">
<input type="hidden" id="clientId" name="clientId">
<div class="row">
	<div class="col-lg-12">
		<div>
			<table id="allPatient"
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


