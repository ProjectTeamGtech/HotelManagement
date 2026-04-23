<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!-- start page content -->
<div class="page-content-wrapper">

<div class="page-content">
<!-- start widget -->
<!-- end widget -->
<!-- chart start -->

<div class="row">
	<s:form class="form-inline" action="Nabh" id="nabhaccreditationtrackerform" theme="simple" >
		<s:hidden name="action" id="action" value="accreditationtracker"></s:hidden>
		<s:hidden name="isKPI" id="isKPI" value="1"></s:hidden> 
	   	<div class="col-md-3" id="categorydiv0"> 
	    	<s:select cssClass="form-control" list="subcategorylist" onchange="changearealist(this.value)" listKey="id" listValue="name" id="filter_subcategoryid" name="subcategoryid" />
	   	</div>   
	   	<div class="col-md-3" id="changearealistdiv"> 
			<s:select headerKey="0" headerValue="Select KPI Area" cssClass="form-control" list="arealist" listKey="areaid" listValue="area" id="filter_areaid" name="areaid" />
	   	</div>
	   	<div class="col-md-2" id="categorydiv1">
	   		<!-- <button  type="submit" class="btn btn-primary "> Go</button> -->
	   		<a href="#" onclick="submitCompilanceTracker()" class="btn btn-info">Go</a>
	   	</div>
	   	<div class="col-md-8 hidden" id="categorydiv2">
	   		
	   	</div>
	   	<div class="col-md-4">
	    	<div class="form-group pull-right" >
				<!-- <a href="QI_Settings.jsp"  title="QI Settings"><i class="fa fa-cogs" aria-hidden="true"></i></a> -->
				<!-- <a href="#" onclick="openNABHBlankPopup('bedlistmasterBed?selectedid=22')" class="btn btn-info">Add New CheckList</a> -->
				<button onClick="switchVisibleNewchecklist();" type="button"
						class="btn btn-primary pull-right">
						<i class="fa fa-plus"></i> Add New CheckList
				</button>
			</div>
	    </div>
    </s:form>
 </div> 

<div class="row">
<div id="switchVisibleNewCommittee1">
	<div class="col-md-12 ">
		<header class="inlinei">
			<b>Accreditation Tracker &nbsp; &nbsp; </b>
		</header>
		<div class="clearfix" style="height: 40px;"></div>
	</div>
	<div class="clearfix"></div>
	<div class="col-md-12">
		<%int x=1; %>
		<s:iterator value="accadmictrackerlist">
		<div class="panel-group" id="accordion<%=x%>">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 data-toggle="collapse" data-parent="#accordion<%=x%>"
								href="#collapse<%=x%>" class="panel-title expand">
							<div class="right-arrow pull-right">+</div>
							<a href="#"><s:property value="subcatname"/> <b class="text-center">&nbsp; &nbsp;&nbsp;</b></a> 
							<span class="label label-success pull-right labelright"><s:property value="completecount"/> Completed</span> 
							<span class="label label-primary pull-right labelright"><s:property value="submitcount"/> Submitted</span> 
							<span class="label label-warning pull-right labelright"><s:property value="pendingcount"/> Pending</span>
					</h4>
				</div>
			<div id="collapse<%=x%>" class="panel-collapse collapse out">
			<%int y=1; %>
			<s:iterator value="arealist">
			<div class="panel-body">
				<div class="panel-group marginbottomzero" id="accordionchild1<%=y%><s:property value="areaid"/>">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 data-toggle="collapse" data-parent="#accordionchild1<%=y%><s:property value="areaid"/>" href="#collapseLevel<%=y%><s:property value="areaid"/>" class="panel-title expand">
								<div class="right-arrow pull-right">+</div>
									<a href="#"><s:property value="area"/> <span class="pull-right rightmarginthreeezerozero">&nbsp;&nbsp; &nbsp; <b><!-- Child Level 1 --></b>
									</span></a>
								</h4>
							</div>
							<div id="collapseLevel<%=y%><s:property value="areaid"/>" class="panel-collapse collapse out">
									<%int z=1; %>
									<s:iterator value="indicatorlist">
									<div class="panel-body">
									<div class="panel-group marginbottomzero" id="accordionchild2<%=z%><s:property value="indicatorid"/>">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 data-toggle="collapse" data-parent="#accordionchild2<%=z%><s:property value="indicatorid"/>"
														href="#collapseLevel1a<%=z%><s:property value="indicatorid"/>" class="panel-title expand">
														<div class="right-arrow pull-right">+</div>
															<a href="#"><%=z%>.<s:property value="indicator"/> <span
															class="pull-right rightmarginthreeezerozero">&nbsp;
																			&nbsp; &nbsp; <b><!-- Child Level 2 --></b>
															</span></a> 
												</h4><!-- <a href="#" title="Add New CheckList"><i class="fa fa-plus-square" style="font-size:12px;color:red" ></i></a> -->
											</div>
											<div id="collapseLevel1a<%=z%><s:property value="indicatorid"/>" class="panel-collapse collapse out">
												
												<s:iterator value="indicatorchklist">
													<div class="panel-body">
														<div class="panel-group marginbottomzero" id="accordionchild3<s:property value="indicatorid"/>">
															<div class="panel panel-default">
																<div class="panel-heading">
																	<h4 data-toggle="collapse" data-parent="#accordionchild3<s:property value="chcklistid"/>"
																		href="#collapseLevel1c<s:property value="chcklistid"/>" class="panel-title expand">
																		<div class="right-arrow pull-right">+</div>
																		<a href="#"><s:property value="chcklistname"/></a>
																	</h4>
																</div>
																<div id="collapseLevel1c<s:property value="chcklistid"/>" class="panel-collapse collapse out">
																	<div class="panel-body">
																		<table class="table">
																			<thead>
																				<tr>
																					<th width="40%" scope="col">Check Points</th>
																					<th width="18%" scope="col">Submit Description</th>
																					<th width="18%" scope="col">Complete Description</th>
																					<th width="10%" scope="col">Evidence</th>
																					<th width="7%" scope="col">Status</th>
																					<th width="7%" scope="col">Action</th>
																				</tr>
																			</thead>
																			<tbody>
																					<tr>
																						<td><s:property value="chcklistname"/></td>
																						<td><s:property value="submit_discription"/></td>
																						<td><s:property value="complete_discription"/></td>
																						<td>
																							<s:if test="chkliststatus==0">
																								
																							</s:if>
																							<s:else>
																								<s:if test="filename!=''">
																									<a href="downloaddocchcklistNabh?id=<s:property value="chcklistid"/>"> <i class="fa fa-download"></i></a>
																								</s:if>
																							</s:else>
																						
																						<td>
																							<s:if test="chkliststatus==0">
																								<span class="label label-warning pull-left labelrighttable">Pending</span>
																							</s:if>
																							<s:elseif test="chkliststatus==1">
																								<span class="label label-primary pull-left labelrighttable">Submitted</span>
																							</s:elseif>
																							<s:else>
																								<span class="label label-success pull-left labelrighttable">Completed</span>
																							</s:else>
																						</td>
																						<td>
																							<s:if test="chkliststatus==0">
																								<button onClick="switchVisibleUpdateCheckPointStatusnewss(<s:property value="chcklistid"/>)" type="button"
																								class="btn btn-circle btn-info btn-xs m-b-10">
																								<i class="fa fa-pencil"></i>Update
																								</button>
																							</s:if>
																							<s:elseif test="chkliststatus==1">
																								<button onClick="switchVisibleUpdateCheckPointStatusnewss(<s:property value="chcklistid"/>)" type="button"
																								class="btn btn-circle btn-info btn-xs m-b-10">
																								<i class="fa fa-pencil"></i>Update
																								</button>
																							</s:elseif>
																						</td>
																					</tr>
																			</tbody>
																		</table>
																		<div id="switchVisibleUpdateNewCheckPointStatus<s:property value="chcklistid"/>"></div>
																			<div id="switchVisibleUpdateNewCheckPointStatusnew<s:property value="chcklistid"/>" style="display: none">
																			<form action="savechecklistdataNabh" id="filesubmitformids<s:property value="chcklistid"/>" class="form-horizontal" method="post" enctype="multipart/form-data">
																				<div class="col-md-12 pull-right">
																					<div class="modal-content">
																						<div class="modal-header">
																							<button onClick="switchVisibleUpdateCheckPointStatusnewss(<s:property value="chcklistid"/>)"
																								type="button" class="close" data-dismiss="modal"
																								aria-label="Close">
																								<span aria-hidden="true">&times;</span>
																							</button>
																							<h4 class="modal-title" id="exampleModalLabel">Update
																								Check Point Status With Evidence</h4>
																						</div>
																						<div class="modal-body">
																								<input type="hidden" value="<s:property value="chcklistid"/>" name="chcklistid">
																								<input type="hidden" id="action<s:property value="chcklistid"/>" value="accreditationtracker" name="action">
																								<input type="hidden" id="isKPI<s:property value="chcklistid"/>" value="1" name="isKPI">
																								<input type="hidden"  name="subcategoryid" id="chklistsubvatid<s:property value="chcklistid"/>">
																								<input type="hidden"  name="areaid" id="chklistareaid<s:property value="chcklistid"/>">
																								<input type="hidden"  name="chkliststatus" value="<s:property value="chkliststatus"/>">
																								<div class="form-group" style="padding-left: 15px;">
																									<label for="filesubremark<s:property value="chcklistid"/>" class="control-label">DESCRIPTION:</label>
																									<textarea rows="4" class="form-control"
																										id="filesubremark<s:property value="chcklistid"/>" name="filesubremark"></textarea>
																								</div>
																								<s:if test="chkliststatus==0">
																									<div class="form-group">
																										<%-- <label class="control-label col-md-3">Upload File <span class="required"> * </span>
																										</label> --%>
																											<div class="col-md-5">
																												<s:hidden name="subuploadfilesContentType"></s:hidden>
																												<s:hidden name="subuploadfilesFileName"></s:hidden>
																												<div class="clearfix" style="height: 10px;"></div>
																												<input type="file" accept="image/jpeg,video/mp4,.pdf,.zip,.rar" onchange="checksizeoffileKPI(<s:property value="chcklistid"/>)"  name="subuploadfiles" id="filesub_uploadfiles<s:property value="chcklistid"/>">
																												<%-- <s:file accept="image/jpeg,video/mp4,.pdf,.zip,.rar" onchange="checksizeoffileKPI(<s:property value="chcklistid"/>)"  name="subuploadfiles" id="filesub_uploadfiles<s:property value="chcklistid"/>"></s:file> --%>
																											</div>
																									</div>
																								</s:if>
																						</div>
																						<div class="modal-footer">
																							<s:if test="chkliststatus==0">
																								<!-- <button type="button" class="btn btn-primary">Submitted</button> -->
																								<a href="#" onclick="validatefilesubmissionKPI(<s:property value="chcklistid"/>,1)" class="btn btn-primary">Submit</a>
																							</s:if>
																							<s:elseif test="chkliststatus==1">
																								<!-- <button type="button" class="btn btn-success">Completed</button> -->
																								<a href="#" onclick="validatefilesubmissionKPI(<s:property value="chcklistid"/>,2)" class="btn btn-success">Complete</a>
																							</s:elseif>
																						</div>
																					</div>
																				</div>
																				</form>
																			</div>
																		</div>
																</div>
															</div>
														</div>
													</div>
												</s:iterator>
											</div>
										</div>
									</div>
								</div>
							<%z++; %>
						</s:iterator>
					</div>
				</div>
			</div>
			</div>
			<%y++; %>
			</s:iterator>
			</div>
		</div>
	</div>
	<%x++; %>
</s:iterator>
</div>
</div>
<div id="switchVisibleEditCommittee2">
					
				</div>


				<div id="switchVisibleNewCommittee2">
					<div class="col-md-12 col-sm-12 padingzero">
						<div class="card card-topline-yellow">
							<div class="card-body" id="bar-parent">
								<div class="alert alert-info" role="alert">
									<ul>
										<li>Add New Checklist</li>
									</ul>
								</div>

								<!-- <form action="savefilesubmittedfileNabh" id="form_sample_1" class="form-horizontal"> -->
								<form action="savenewchecklistNabh" id="savenewchecklistform" class="form-horizontal" method="post" >
									<div class="form-body">
										<div class="form-group">
											<label class="control-label col-md-3">Chapter <span
												class="required"> * </span>
											</label>
											<div class="col-md-5">
												<s:select cssClass="form-control" disabled="true" list="subcategorylist" onchange="addchangearealist(this.value)" listKey="id" listValue="name" id="addsubcategoryid" name="subcategoryid" />
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label col-md-3">Area <span
												class="required"> * </span>
											</label>
											<div class="col-md-5" id="addareaiddiv">
												<s:select headerKey="0" headerValue="Select KPI Area" onchange="addchangeindicator(this.value)" cssClass="form-control select2-container" list="arealist" listKey="areaid" listValue="area" id="addareaid" name="areaid" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">Indicator <span
												class="required"> * </span>
											</label>
											<div class="col-md-5" id="addindicatoriddiv">
												<s:select headerKey="0" headerValue="Select Indicator" cssClass="form-control select2-container" list="indicatorlist" listKey="id" listValue="indicator" id="addindicator" name="indicator" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">Checklist <span class="required"> * </span></label>
											<div class="col-md-5">
												<textarea name="remark" id="addremark" placeholder="Checklist" class="form-control-textarea" rows="5"></textarea>
											</div>
										</div>
										
									</div>
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">
												<a href="#" onclick="validateindicatorsubmission()" class="btn btn-info">Save</a>
												<button onClick="switchVisibleNewCommittee();" type="button"
													class="btn btn-danger">Cancel</button>
											</div>
										</div>
									</div>
								</form>
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

<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>	
<!-- end page content -->
