<%@taglib uri="/struts-tags" prefix="s"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript"
	src="diarymanagement/js/nonavailableslot.js"></script>
	 <%
				LoginInfo loginInfo2 = LoginHelper.getLoginInfo(request);
		   %>

<style>
	.table>tbody>tr>th{
		background-color: #4E7894;
	    color: #fff;
	    font-size: 12px;
	}
</style>
<div class="searchpatient">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-color: #efefef;padding: 8px 0px 8px 0px;">
	
	<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
			<input type="text" name="searchText" id="searchText" autofocus="autofocus" onchange="searchPatient()"
				placeholder="Search by first,last name,mob no.,postcode" class="form-control" style="width:100% !important; border: 1px solid;">
				
				
				
		
	</div>
	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" style="text-align: left: ;">
		<button class="btn btn-primary" type="button"
					onclick="searchPatient()">Search</button>&emsp;
		<a href="#" class="btn btn-primary" id="addpatientbtn"	onclick="addPatient()">Add  <%=loginInfo2.getPatientname_field() %></a>
	</div>
</div>
<br />
<input type="hidden" id="client" name="client">
<input type="hidden" id="clientId" name="clientId">
<div class="">
	<div class="col-lg-12 col-md-12" style="padding: 0px;">
		<div class="table-responsive">
			<table id="allPatient"
				class="table table-bordered">
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

</div>

<!-- /.col-lg-6 -->






	<%-- <script>
	
    $(function() {
  $('.searchpatient').slimScroll({
        height: '450px',
        railVisible: true,
		alwaysVisible: true
  });
 
 });
 
	</script> --%>























