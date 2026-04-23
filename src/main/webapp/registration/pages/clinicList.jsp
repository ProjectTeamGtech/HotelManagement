<%@taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="common/js/pagination.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Clinic List</a></li>
			
		</ol>
	</div>
</div>


<div class="row">
	<!-- /.col-lg-6 -->
	
		<s:form action="ClinicRegistration" theme="simple" id = "clientFrm">
			<div class="col-lg-2 col-md-2">
			
			<s:select name="status"
				list="#{'1':'Active','0':'Inactive'}"
				cssClass="form-control" headerKey="All" headerValue="All" ></s:select>
			</div>
			<div class="col-lg-6 col-md-6">
			<div class="input-group">
			
				<s:textfield theme="simple" name="searchText"
					placeholder="Search By Clinic Id"  cssClass="form-control" />
				<span class="input-group-btn"> <input type="submit"
					value="Go" class="btn btn-primary" />
				</span>
			</div>
			</div>
			
		</s:form>
</div>

<br />


	<div class="table-responsive">
		<table
			class="table table-striped table-hover table-condensed table-bordered">
			<thead>
				<tr>
					<th>User Id</th>
					<th>User Name</th>
					<th>Login Status</th>
					
					<!-- <th>Clinic Owner</th>
					<th>LastModified</th> -->
					
					<th>Edit</th>
					<th>Delete</th>
					
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<s:if test="clinicList.size!=0">
					<s:iterator value="clinicList" status="rowstatus">
						<tr>
							
									<s:if test="userType==1">
										<td><s:property value="userId" /><a href="#"> (Admin)</a></td>
									</s:if>
									<s:if test="userType==2">
										<td><s:property value="userId" /><a href="#"> (Clinic)</a></td>
									</s:if>
									<s:if test="userType==4">
										<td><s:property value="userId" /><a href="#"> (Practitioner)</a></td>
									</s:if>
									
									
									
									
									
									<td><s:property  value="clinicName" /></td>
									<%-- <td><s:property value="clinicOwner" /></td>
									<td>Date</td> --%>
									
									<s:if test="loginstatus==true">
										<td><a href="logClinicRegistration?status=<s:property value="loginstatus"/>&userid=<s:property value="userId"/>">Logged In</a></td>
									</s:if>
									<s:else>
										<td><a href="logClinicRegistration?status=<s:property value="loginstatus"/>&userid=<s:property value="userId"/>">Logged Out</a></td>
									</s:else>
									
									<s:hidden value="%{id}" name="id"></s:hidden>
									<s:url action="editClinicRegistration" id="edit">
    								<s:param name="id" value="%{id}"></s:param>
									</s:url>
									
									
									<s:if test="userType==1">
	     								<td class="text-center"><a href="editClinicRegistration?idd=<s:property value="userId"/>"
											cssClass="text-warning">
											<i class="fa fa-edit"></i>
										</a>
										</td>
									</s:if>
									
									<s:if test="userType==2">
	     								<td class="text-center"><a href="editClinicRegistration?idd=<s:property value="userId"/>"
											cssClass="text-warning">
											<i class="fa fa-edit"></i>
										</a>
										</td>
									</s:if>
									
									<s:if test="userType==4">
	     								<td class="text-center"><a href="editUserProfile?idd=<s:property value="userId"/>&clinicid=<s:property value="clinicID"/>"
											cssClass="text-warning">
											<i class="fa fa-edit"></i>
										</a>
										</td>
									</s:if>
									
    								<s:url action="deleteClinicRegistration" id="delete">
									<s:param name="id" value="%{id}"></s:param>
									</s:url>
									
									<td class="text-center"><s:a href="#"
									onclick="return confirmedDelete()" cssClass="text-danger">
									<i class="fa fa-trash-o"></i>
									</s:a>
									</td>
									
									<s:if test="activestatus==0">
										<td><a href="statusClinicRegistration?selectedid=<s:property value="id"/>&activestatus=<s:property value="activestatus"/>">Inactive</a></td>
									</s:if>
									<s:else>
										<td><a href="statusClinicRegistration?selectedid=<s:property value="id"/>&activestatus=<s:property value="activestatus"/>">Active</a></td>
									</s:else>
									
						
						</tr>
					</s:iterator>
				</s:if>

		</tbody>
	</table>
</div>



	<s:form action="moveClient" name="paginationForm" id="paginationForm" theme="simple">
		<div class="col-lg-12 col-md-12" style="padding:0px;">
			<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
				Total:<b class="text-info"><s:property value="totalRecords" /></b>
			</div>
			<%@ include file="/common/pages/pagination.jsp"%>
		</div>
	</s:form>
</div>






















<%-- <div id="login_main" class="main_layout_content">
	<h2 class="heading" >Clinic List</h2>
	<div id="login" class="block_div">
		<div class="diaryuser" style="margin-top: -50px;">
			<s:form action="ClinicRegistration" theme="simple">
			<div align="center"><s:textfield theme="simple" name="searchText" placeholder="Search by Name" size="80" cssClass="Search"/>
					<input type="submit" value="Go" class="go"/> 
				</div>
			</s:form>
		</div>
	<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
		
			<div class="form_elements">	
						
				<table id="results" cellpadding="0" cellspacing="0" class="my-table"  style="width:100%;">
					<tr>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;" class="sortable <s:if test="#attr.pagination.sortColumn.equals('clinicname')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
						<a href="#" onclick="fnPagination(6,'fullname');">Clinic Name</a></th>
						
						<!--<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Description</th>
						--><th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Clinic Owner Name</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">User Name</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Password</th>
						
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Edit</th>
						<th style="background:transparent url('common/images/table_header.gif') no-repeat scroll 0 0;">Delete</th>
					</tr>
					<s:if test="clinicList.size!=0">
						<s:iterator value="clinicList" status="rowstatus">
						<tr>	
							<s:if test="#rowstatus.even == true">
								<tr class="ac_odd">
							</s:if>
									
									<td><s:property  value="clinicName" /></td>
									<!--<td><s:property  value="description" /></td>
									--><td><s:property value="clinicOwner" /></td>
									<td><s:property value="userId" /></td>
									<td><s:property value="password" /></td>
									
									<s:hidden value="%{id}" name="id"></s:hidden>
									<s:url action="editClinicRegistration" id="edit">
    								<s:param name="id" value="%{id}"></s:param>
									</s:url>
     								<td><s:a href="%{edit}"><img src="common/images/edit.jpg"></img></s:a></td>
    								<s:url action="deleteClinicRegistration" id="delete">
									<s:param name="id" value="%{id}"></s:param>
									</s:url>
									<td><s:a href="%{delete}"><img src="common/images/delete.gif"></img></s:a></td>	
							</tr>

						</s:iterator>
					</s:if>
					<s:else>
						There is no Clinic List found!!
					</s:else>
							
				</table> --%>
				
			<%-- 	<s:form action="ClinicRegistration.action" name="paginationForm" id="paginationForm" theme="simple">
			<table align="center">
				<tr>
					<td align="left">Showing all <b>(<s:property value="totalRecords"/> of <s:property value="pagerecords"/> Categories)</b></td>
				<td align="right"><%@ include file="/common/pages/pagination.jsp" %></td>
				
				</tr>
			</table>
		</s:form> --%>
				
				
				
				
       			
       			

		
