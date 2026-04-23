<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

	<%
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	%>
	

<!-- start page content --> 
 <div class="page-content-wrapper" oncontextmenu="return false;" >   
 <!--  <div class="page-content-wrapper"  >  -->
<!--  -->

	<div class="page-content">

		<!-- start widget -->

		<!-- end widget -->
		<!-- chart start -->
		<div class="row">
			<div class="col-md-12 " style="text-align: center;">
				<header class="inlinei" style="font-size: 29px;float: none !important;color: #5ea2a2;">
				<s:if test="action=='OPD'">
					<b>OPD &nbsp; &nbsp; </b>
				</s:if>
				<s:elseif test="action=='Video_Training_Dash'">
					<b>About Yuvicare &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='IPD'">
					<b>IPD &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='OT'">
					<b>OT &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Pharmacy'">
					<b>Pharmacy &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Inventory'">
					<b>Inventory &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Investigation'">
					<b>Investigation &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='MRD'">
					<b>MRD &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Account'">
					<b>Account &nbsp; &nbsp; </b>
				</s:elseif>
				
				<s:elseif test="action=='Casualty'">
					<b>Casualty &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='PACs'">
					<b>PAC's &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Discharge'">
					<b>Discharge Dashboard &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Prescription'">
					<b>Prescription Dashboard &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Indent'">
					<b>Indent &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='TPA'">
					<b>TPA &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Analytics'">
					<b>Analytics &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Payroll'">
					<b>Payroll &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Patients'">
					<b>Patients &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Dietary'">
					<b>Dietary &nbsp; &nbsp; </b>
				</s:elseif>
				
				<s:elseif test="action=='Day_Care'">
					<b>Day Care &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='Vaccination'">
					<b>Vaccination &nbsp; &nbsp; </b>
				</s:elseif>
				
				
				</header>
				<%if(loginInfo.getUserType()==2){ %>
					<button onClick="switchVisibleNewCommittee();" type="button"
						class="btn btn-primary pull-right">
						<i class="fa fa-plus"></i> New Submission
					</button>
				<%} %>
				<div class="clearfix" style="height: 0px;"></div>

			</div>
			<div class="clearfix"></div>
			<div class="col-md-12 scrollfixeddown1"  style="overflow-y: scroll;height: 550px;" >
				<div id="switchVisibleNewCommittee1" >
					<table class="table table-striped  table-hover table-checkable order-column valign-middle" id="example4">
							<thead>
								<tr>
									<th width="40%" style="text-align: left;font-size: 20px;"  scope="col">Title</th>
									<th width="5%" scope="col"></th>
									
									
								</tr>
							</thead>
							<tbody>
							<%int counts=0; %>
								<s:iterator value="filesubmmissionlist">
									<%-- <%if(counts%2==0){ %>
										<tr onclick="showmoredetailsoffile(<%=counts%>)" style="border-left: 1px dashed royalblue;">
									<%}else{ %>
											<tr onclick="showmoredetailsoffile(<%=counts%>)" style="border-left: 1px dashed red;">
									<%} %> --%>
									<tr onclick="showmoredetailsoffile(<%=counts%>)">
										<td style="text-align: left;">
											<i class="fa fa-arrow-down" id="downtoupss<%=counts%>" aria-hidden="true"></i> &nbsp;&nbsp;<s:property value="doctitle"/>
										</td>
										<td id="tdchangevt<%=counts%>" style="text-align: right;font-size: 25px;">+</td>
										
									</tr>
									<tr id="trchangevt<%=counts%>" class="hidden">
										<td>
											<s:if test="action=='Video_Training_Dash'">
													
											</s:if>
											<s:else>
												<b>Sub-Module:</b> <s:property value="filesubmodule"/> <br>
												<b>Feature:</b> <s:property value="docfeature"/> <br>
											</s:else>
											
											<b>Description:</b> <s:property value="remark"/> <br>
											
										</td>
										<td>
											<s:if test="category=='Video'">
												<video width="512" height="380"  controls controlsList="nodownload">
												    <source src="<s:property value="filePathnew"/>" type="video/mp4">
												</video> 
											</s:if>
											<s:elseif test="category=='Images'">
												<img width="612" height="380" alt="Can't Read" src="<s:property value="filePathnew"/>">
											</s:elseif>
											<s:elseif test="category=='Documents'">
												<object  data="<s:property value="filePathnew"/>" type="application/x-pdf" title="SamplePdf" width="500" height="720">
    													<a  href="<s:property value="filePathnew"/>#toolbar=0"><i class="fa fa-eye"></i>View</a> 
												</object>
											</s:elseif>
										</td>
										
									</tr>
									<tr id="trdatechangevt<%=counts%>" class="hidden">
										<td style="text-align: left;">
											<s:if test="category=='Video'">
												
											</s:if>
											<s:elseif test="category=='Images'">
												<%-- <a href="<s:property value="filePathnew"/>" target="_blank"  class="btn btn-circle btn-info btn-xs m-b-10">
													<i class="fa fa-eye"></i>View Full Size
												</a> --%>
											</s:elseif>
											<s:elseif test="category=='Documents'">
												
											</s:elseif>
										</td>
										<td style="text-align: right;">
											<div style="height: 5px;" class="clearfix"></div> 
											<small><i class="fa fa-clock-o"></i> <s:property value="datetime"/></small>
										</td>
										
									</tr>
									<%counts++; %>
								</s:iterator>
							</tbody>
						</table>
						
						<%-- <table class="table table-striped  table-hover table-checkable order-column valign-middle" id="example4">
							<thead>
								<tr>
									<th width="15%" scope="col">Submitted File</th>
									<th width="10%" scope="col">Category</th>
									<th width="10%" scope="col">Sub-Module</th>
									<th width="10%" scope="col">Search-Key</th>
									<th width="35%" scope="col">Descriptions</th>
									<th width="15%" scope="col">Reviewed Document</th>
									<%if(loginInfo.getUserType()==2){ %>
										<th width="10%" scope="col">Delete</th>
									<%} %>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="filesubmmissionlist">
									<tr>
										<td><div style="height: 5px;" class="clearfix"></div> <small><i
												class="fa fa-clock-o"></i> <s:property value="datetime"/></small>
												&nbsp;
									      	
										</td>
										<td><s:property value="category"/></td>
										<td><s:property value="filesubmodule"/></td>
										<td><s:property value="filesearchkey"/></td>
										<td><s:property value="remark"/></td>
										<td>
											<s:if test="category=='Video'">
												<video width="512" height="380"  controls controlsList="nodownload">
												    <source src="<s:property value="filePathnew"/>" type="video/mp4">
												</video> 
											</s:if>
											<s:elseif test="category=='Images'">
												<a href="<s:property value="filePathnew"/>" target="_blank"  class="btn btn-circle btn-info btn-xs m-b-10">
													<i class="fa fa-eye"></i>View
												</a>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<img width="150" height="50" alt="Can't Read" src="<s:property value="filePathnew"/>">
												
												
											</s:elseif>
											<s:elseif test="category=='Documents'">
												<a href="<s:property value="filePathnew"/>" target="_blank"  class="btn btn-circle btn-info btn-xs m-b-10">
													<i class="fa fa-eye"></i>View
												</a>
												
												<object  data="<s:property value="filePathnew"/>" type="application/x-pdf" title="SamplePdf" width="500" height="720">
    													<a  href="<s:property value="filePathnew"/>#toolbar=0"><i class="fa fa-eye"></i>View</a> 
												</object>
											</s:elseif>
											
											<a href="<s:property value="filePathnew"/>" target="_blank"  class="btn btn-circle btn-info btn-xs m-b-10">
												<i class="fa fa-eye"></i>View
											</a>
											
										</td>
										<%if(loginInfo.getUserType()==2){ %>
											<td><a href="#" onclick="deletevideotutorialuploadedfile(<s:property value="id"/>)"> <i class="fa fa-trash" aria-hidden="true"></i> 
											</a></td>
										<%} %>
									</tr>
								</s:iterator>
							</tbody>
						</table> --%>
				</div>


				<div id="switchVisibleEditCommittee2">
					
				</div>


				<div id="switchVisibleNewCommittee2" >
					<div class="col-md-12 col-sm-12 padingzero">
						<div class="card card-topline-yellow">
							<div class="card-body" id="bar-parent">
								<div class="alert alert-warning" role="alert">
									<ul>
										<li>File format make sure file must be one of listed (jpeg,pdf,mp4)</li>
										<!-- <li>File size restriction (Max file format size up to 100 MB)</li> -->
									</ul>
								</div>


								<!-- <form action="savefilesubmittedfileNabh" id="form_sample_1" class="form-horizontal"> -->
								<form action="savevideotutorialNabh" id="filesubmitformids" class="form-horizontal" method="post" enctype="multipart/form-data">
									<div class="form-body">
										<div class="form-group">
											<label class="control-label col-md-2">Category <span
												class="required"> * </span>
											</label>
											<div class="col-md-4">
												<s:hidden name="action" id="videotutorialmodule_action"></s:hidden>
												<select class="form-control input-height" name="filesubmission_category" id="filesubmission_category">
													<option value="0">Select Category</option>
													<option value="Documents">Documents</option>
													<option value="Video">Video</option>
													<option value="Images">Images</option>
													<!-- <option value="Others">Others</option> -->
												</select>
											</div>
											<label class="control-label col-md-2">Module <span
												class="required"> * </span>
											</label>
											<div class="col-md-4">
												<label>
													<s:if test="action=='Video_Training_Dash'">
														<b>About Yuvicare</b>
													</s:if>
													<s:else>
														<b><s:property value="action"/></b>
													</s:else>
												</label>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-2">Sub-Module <span
												class="required"> * </span>
											</label>
											<div class="col-md-4" id="submodulediv">
												<s:select list="submoduleList" cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Select Sub Module" name="filesubsubmodule" id="filesubsubmodule"></s:select>
											</div>
											<div class="col-md-2">
											</div>
											<div class="col-md-4">
												<a class="btn btn-success" id="newsubmoduleaddtag" style="padding-left: 2px;padding-right: 3px;" onclick="addnewsubmoduleopenhidden()">Add New Submodule</a>
											</div>
										</div>
										<div class="form-group hidden" id="addnewsubmoduleopendiv">
											<label class="control-label col-md-2">New Submodule <span class="required"> * </span>
											</label>
											<div class="col-md-4">
												<input type="text" style="width: 100%"  name="filesubnewsubmodule" id="filesubnewsubmodule" placeholder="New Submodule" class="form-control-textarea" >
											</div>
											<div class="col-md-2">
												<a class="btn btn-success" style="padding-left: 2px;padding-right: 3px;" onclick="savenewsubmodule()">Save New</a>
											</div>
											<div class="col-md-4">
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-2">Features <span class="required"> * </span></label>
											<div class="col-md-4">
												<input type="text"  name="filesubfeatures" style="width: 100%;" id="filesubfeatures" placeholder="Features" class="form-control-textarea" >
											</div>
											
											<label class="control-label col-md-2">Search-Key <span class="required"> * </span></label>
											<div class="col-md-4">
												<input type="text"  name="filesubserachkey" style="width: 100%;" id="filesubserachkey" placeholder="Search-Key" class="form-control-textarea" >
											</div>
											
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-2">Title <span class="required"> * </span></label>
											<div class="col-md-4">
												<textarea name="filesubtitle" id="filesubtitle" placeholder="Title" class="form-control-textarea" rows="5"></textarea>
											</div>
											<label class="control-label col-md-2">Description <span class="required"> * </span></label>
											<div class="col-md-4">
												<textarea name="filesubremark" id="filesubremark" placeholder="Description" class="form-control-textarea" rows="5"></textarea>
											</div>
										</div>



										<div class="form-group">
											<label class="control-label col-md-2">Upload File <span
												class="required"> * </span>
											</label>
											<div class="col-md-4">
												<s:hidden name="subuploadfilesContentType"></s:hidden>
												<s:hidden name="subuploadfilesFileName"></s:hidden>
												<div class="clearfix" style="height: 10px;"></div>
												<!-- <input type="file" name="filesub_uploadfiles" value="" id="filesub_uploadfiles"> -->
												<s:file accept="image/jpeg,video/mp4,.pdf" onchange="checksizeofvideotutorial()"  name="subuploadfiles" id="filesub_uploadfiles"></s:file>
											</div>
										</div>

										<div class="form-group">
												<div class="col-md-12" style="text-align: center;" >
													<a href="#" onclick="validatevideotutorial()" class="btn btn-info">Submit</a>
													<button onClick="switchVisibleNewCommittee();" type="button"
													class="btn btn-info">Cancel</button>
												</div>
												
										</div>

									</div>
									<!-- <div class="form-actions hidden">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">
												<a href="#" onclick="validatevideotutorial()" class="btn btn-info">Submit</a>
												<button onClick="switchVisibleNewCommittee();" type="button"
													class="btn btn-info">Cancel</button>
											</div>
										</div>
									</div> -->
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Chart end -->

		<!-- start admited patient list -->

		<!-- end admited patient list -->
	</div>



</div>
    <div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="baselayout1loaderPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
	
<!-- end page content -->