<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="registration/js/userprofile.js"></script>
<%-- <script type="text/javascript" src="registration/js/edituserprofile.js"></script> --%>
<script type="text/javascript" src="common/js/pagination.js"></script>

<link rel="stylesheet" href="_assets/newtheme/css/main.css">
<link rel="stylesheet" href="_assets/newtheme/css/responsive.css">



<style>
	.form-control {
	    border: 1px solid #ddd;
	    background-color: #fff;
	}
</style>


<%LoginInfo loginfo = LoginHelper.getLoginInfo(request); %>

<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>View Users</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										

<div class="col-lg-12 col-md-12 topback2">
	<s:form action="UserProfile" theme="simple">
		<div class="form-inline">
		<div class="form-group">
			<s:textfield theme="simple" name="searchText" placeholder="Search by Name" size="40" cssClass="form-control" />
		</div>
		<div class="form-group">
		<s:select  name="jobtitless" list="jobTitleList" 
						headerKey="0" headerValue="Select Job Title"
						title="Select Job title" theme="simple"
						cssClass="form-control chosen-select" />
		</div>
		<div class="form-group">
			<input type="submit" value="Go" class="btn btn-primary" />
		</div>
		
		<%-- <div class="form-group">
			<select class="form-control">
				<option>Globle Access</option>
				<option>ADMIN</option>
				<option>PHARMACIST</option>
			</select>
		</div> --%>
		<div class="form-group" style="float: right;">
			<!-- <a href="#" onclick="openPopup('roleAccessSettingClinicRegistration')" class="btn btn-primary">Access Setting</a> -->
			<% if(loginfo.getUserType()==2){%>
				<a href="inputUserProfile" class="btn btn-primary" style="text-decoration: none;"><i class="fa fa-plus"></i> Add New User</a>
			<% }%>
		</div>
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
	

		<div class="">
		<table class="table  table-condensed table-striped table-bordered text-uppercase">
			<thead>
			<tr>
				<th>User Name</th>
				<th>Mobile No</th>
				<th>Department</th>
				<th>Role</th>
				<th>User ID</th>
				<th>Global Access</th>
				<%if(loginfo.getUserType()==2){ %>
				<th>Active</th>
				<%} %>
				<th>Access</th>
				<th>Userwise Access</th>
				<th>Reset Password</th>
				<th>Edit</th>
				<th style="text-align: center;"> Profile</th>
				<th>Profile Photo</th>
				<%-- <th class=" sortable <s:if test="#attr.pagination.sortColumn.equals('firstname')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>" style="width: 20%;">
					<a href="#" onclick="fnPagination(6,'firstname');" style="color:#fff;"> Users Details</a></th> --%>
			</tr>
			</thead>
			<tbody>
			<%int i=0; %>
			<s:if test="userProfileList.size!=0">
				<s:iterator value="userProfileList" >
					<tr>
						<td><s:property value="firstname" /> <s:property value="lastname" /></td>
						<td><s:property value="mobile" /></td>
						<td><s:property value="discription"/></td>
						<td><s:property value="jobtitle" /></td>
						<td><s:property value="userid" /></td>
						<td>
							<s:if test="userid==''">
							
							</s:if>
							<s:else>
								<ul class="settings" style="padding: 0px;list-style: none;margin-bottom: 0px;">
								<li>
									<s:if test="globalaccess==0">
										<div class="control-label">
                                                <div class="onoffswitch greensea">
                                                	<input type="checkbox" name="onoffswitch" onclick="updateglobalaccess('<s:property value="jobtitle" />',this.checked)" class="onoffswitch-checkbox" id="show-offline<%=i%>">
                                                    <label class="onoffswitch-label" for="show-offline<%=i%>">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
									</s:if>
									<s:elseif test="globalaccess==1">
										<div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                	<input type="checkbox" name="onoffswitch" checked="checked" onclick="updateglobalaccess('<s:property value="jobtitle" />',this.checked)" class="onoffswitch-checkbox" id="show-offline<%=i%>">
                                                    <label class="onoffswitch-label" for="show-offline<%=i%>">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
									</s:elseif>
									<s:else>
										<div class="control-label">
                                                <div class="onoffswitch greensea" id="">
                                                	<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="show-offline<%=i%>">
                                                    <label class="onoffswitch-label" for="show-offline<%=i%>">
                                                        <span class="onoffswitch-inner"></span>
                                                        <span class="onoffswitch-switch"></span>
                                                    </label>
                                                </div>
                                            </div>
									</s:else>
                                        
                                    </li>
							</ul>
							</s:else>
							
						</td>
						<%if(loginfo.getUserType()==2){ %>
						<td>
								<ul class="settings" style="padding: 0px;list-style: none;margin-bottom: 0px;">
									<li>
										<s:if test="islogin==1">
										<div class="control-label">
	                                                <div class="onoffswitch greensea">
	                                                	<input type="checkbox" name="onoffswitch" checked="checked" onclick="updateactiveinactive(<s:property value="id" />,this.checked)" class="onoffswitch-checkbox" id="activeinactive<%=i%>">
	                                                    <label class="onoffswitch-label" for="activeinactive<%=i%>">
	                                                        <span class="onoffswitch-inner"></span>
	                                                        <span class="onoffswitch-switch"></span>
	                                                    </label>
	                                                </div>
	                                            </div>
										</s:if>
										<s:else>
												<div class="control-label">
	                                                <div class="onoffswitch greensea" id="">
	                                                	<input type="checkbox" name="onoffswitch"  onclick="updateactiveinactive(<s:property value="id" />,this.checked)" class="onoffswitch-checkbox" id="activeinactive<%=i%>">
	                                                    <label class="onoffswitch-label" for="activeinactive<%=i%>">
	                                                        <span class="onoffswitch-inner"></span>
	                                                        <span class="onoffswitch-switch"></span>
	                                                    </label>
	                                                </div>
	                                            </div>
										</s:else>
										
	                                    </li>
								</ul>
							
							
							
						
						</td>
						<%} %>
						<td>
							<s:if test="userid!=''">
								<a href="#" onclick="openPopup('roleaccessUserProfile?jobtitle=<s:property value="jobtitle" />&fname=<s:property value="firstname" />&lname=<s:property value="lastname" />&userid=<s:property value="userid" />')">setting</a>
							</s:if>
						</td>
						<td>
							<s:if test="userid!=''">
								<a class="form-control" href="userwiseaccessClinicRegistration?jobtitle=<s:property value="jobtitle" />&fname=<s:property value="firstname" />&lname=<s:property value="lastname" />&userid=<s:property value="userid" />">UserWise Access</a>
							</s:if>
						</td>
						<%-- <td><s:property value="email" /></td>
						<td></td> --%>
						
						<%-- <td><s:property value="discription" /></td> --%>
						<% if(loginfo.getJobTitle().equals("Admin")){%>
						<td><input onclick="resetpassword(<s:property value="id"/>)" type='button' value='Reset Password' class='btn btn-primary' ></td>
						<%} %>
						<s:hidden value="%{id}" name="id"></s:hidden>
						<s:url action="editUserProfile" id="edit">
							<s:param name="id" value="%{id}"></s:param>
						</s:url>
						
						<td>
							<s:if test="userid!=''">
								<s:a href="%{edit}">
									<i class="fa fa-edit"></i>
								</s:a>
							</s:if>
						</td>
							
							
								
								
						<s:url action="deleteUserProfile" id="delete">
							<s:param name="id" value="%{id}"></s:param>
						</s:url>
						<td style="text-align: center;"><a onclick="openPopup('practitionerUserProfile?id=<s:property value='id'/>')"><i class="fa fa-user-md" aria-hidden="true"></i></a></td>
						<s:if test="userImageFileName !=null">
						<td><img src="liveData/clinicLogo/<s:property value="userImageFileName"/>"  width="50" height="50"></td>
						</s:if>
						<s:else>
						
							<td><img src="liveData/clinicLogo/user1.png"  width="50" height="50"></td>
						</s:else>
						</tr>
				<%-- <td class="text-center hidden"><s:a href="%{delete}" onclick="return confirmedDelete()" cssClass="text-danger" >
								<i class="fa fa-trash-o"></i></s:a></td> --%> 
					<%i++; %>
				</s:iterator>
			</s:if>
			<s:else>
						There is no User Profile found!!
					</s:else>
</tbody>
		</table>

</div>
	
	
<div id="reset" class="modal fade" role="dialog">

  <div class="modal-dialog modal-sm">
  
    <!-- Modal content-->
    <div class="modal-content">
       <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Reset Password</h4>
        
      </div>
      
      
      
<div class="modal-body">
      
  <div class="row">
	 <div class="col-lg-12 col-md-12"></div>
	   <div class="col-lg-12 col-md-12">
		 <div class="">
			<div class="panel-body">
			<input type="hidden" id="id">
			    <label>Reset Password</label><label class="text-danger">*</label>
				<s:textfield id="resetpassword" name="resetpassword"
				     
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Reset password " placeholder="Enter Reset password"/>
					
					
			</div>
		</div>
	</div>
	
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
	
		<div class="col-lg-5 col-md-4"></div>
		
		<div class="col-lg-6 col-md-8">
			
		</div>
		<div class="col-lg-3 col-md-2"></div>
		
	</div>
	
	<s:token></s:token>
	
</div>

                       <%-- <div class="modal-footer">
                                 <s:submit cssClass="btn btn-primary" value="Save"/>  --%>
        
            <div class="modal-footer">
                     <button type="button" class="btn btn-primary"
							   onclick="updatepassword()" style="margin-top: 30px;">Save</button>
		    </div>
         
		
        </div>
     </div>
 </div>
	
	

<s:form action="UserProfile" name="paginationForm" id="paginationForm" theme="simple">
		<s:hidden name="searchText"></s:hidden>
		<div class="col-lg-12 col-md-12" style="padding:0px;margin-top:15px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<b class="text-info"><s:property value="totalRecords" /></b>
		</div>		 
			<%@ include file="/common/pages/pagination.jsp"%>
			
		</div>
	</s:form>

											

											
										
									</div>
								</div>
							</div>
						</div>



<script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }	
  </script>









