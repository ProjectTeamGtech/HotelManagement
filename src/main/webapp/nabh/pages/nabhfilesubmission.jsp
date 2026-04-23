<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!-- start page content -->
<div class="page-content-wrapper">


	<div class="page-content">

		<!-- start widget -->

		<!-- end widget -->
		<!-- chart start -->
		<div class="row">
			<div class="col-md-12 ">
				<header class="inlinei">
				<s:if test="action=='filesubmission'">
					<b>File Submissions &nbsp; &nbsp; </b>
				</s:if>
				<s:elseif test="action=='resource_video'">
					<b>Videos &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='PostersandVisuals'">
					<b>Posters and Visuals &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='resource_forms'">
					<b>Forms and SOPS &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='resource_qi_tools'">
					<b>QI Tools &nbsp; &nbsp; </b>
				</s:elseif>
				<s:elseif test="action=='resource_training_material'">
					<b>Training Materials &nbsp; &nbsp; </b>
				</s:elseif>

				</header>

				<button onClick="switchVisibleNewCommittee();" type="button"
					class="btn btn-primary pull-right">
					<i class="fa fa-plus"></i> New Submission
				</button>

				<div class="clearfix" style="height: 40px;"></div>

			</div>
			<div class="clearfix"></div>
			<div class="col-md-12 ">
				<div id="switchVisibleNewCommittee1">
					<s:if test="action=='filesubmission'">
						<table class="table table-striped  table-hover table-checkable order-column valign-middle" id="example4">
							<thead>
								<tr>
									<th width="15%" scope="col">Submitted File</th>
									<th width="10%" scope="col">Category</th>
									<th width="35%" scope="col">Descriptions</th>
									<th width="15%" scope="col">Reviewed Document</th>
									<th width="10%" scope="col">Delete</th>
									<!-- <th width="15%" scope="col">Reviewed on</th>
									<th width="10%" scope="col">Action</th> -->
								</tr>
							</thead>
							<tbody>
	
								<!-- <tr>
	
									<td><a href=""> <i class="fa fa-download"></i> Downlaod
									</a>
										<div style="height: 5px;" class="clearfix"></div> <small><i
											class="fa fa-clock-o"></i> 02 OCT 2017</small></td>
	
									<td>Others</td>
									<td>AAC Manual edited as per our hospital services. please
										huide further. Radology is not applicable. Ashwini kidney and
										Diaysis Centre Pvt. Ltd.</td>
									<td><a href=""> <i class="fa fa-download"></i> Downlaod
									</a></td>
									<td>20 OCT, 2017</td>
									<td><button type="button"
											class="btn btn-circle btn-info btn-xs m-b-10">
											<i class="fa fa-eye"></i>View
										</button></td>
								</tr> -->
								<s:iterator value="filesubmmissionlist">
									<tr>
									<td><a href="downloadDocNabh?id=<s:property value="id"/>"> <i class="fa fa-download"></i> Downlaod</a>
										<div style="height: 5px;" class="clearfix"></div> <small><i
											class="fa fa-clock-o"></i> <s:property value="datetime"/></small>
											&nbsp;
								      	<a href="<s:property value="filePathnew"/>" target="_blank"  class="btn btn-circle btn-info btn-xs m-b-10">
												<i class="fa fa-eye"></i>View
											</a>
											</td>
	
									<td><s:property value="category"/></td>
									<td><s:property value="remark"/></td>
									<td><a href="downloadDocNabh?id=<s:property value="id"/>"> <i class="fa fa-download"></i> Downlaod
									</a></td>
									<td><a href="#" onclick="deleteuploadedfile(<s:property value="id"/>)"> <i class="fa fa-trash" aria-hidden="true"></i> Delete
									</a></td>
									</tr>
								</s:iterator>
								
	
							</tbody>
						</table>
					</s:if>
					<s:elseif test="action=='resource_video' || action=='PostersandVisuals' || action=='resource_forms' || action=='resource_qi_tools' || action=='resource_training_material'">
						 <table class="table table-striped  table-hover table-checkable order-column valign-middle" id="example4">
                          <thead>
						    <tr>
						        <th class="text-center" width="10%" scope="col">Sr.No</th>
						      	<th width="35%" scope="col">File Name</th>
						        <th  width="45%" scope="col">Discription</th>
						        <th width="10%" scope="col">Delete</th>
						    </tr>
						  </thead>
						  <tbody>
						    <%-- <tr>
						      <td class="text-center">1</td><td>
						      <a href="">  <i class="fa fa-download"></i>  Doctors Initial Assessment </a> 
						      <div style="height:5px;" class="clearfix"></div>
						        <small>Published on: Jul 21, 2017</small>
						        <div style="height:3px;" class="clearfix"></div>
						         <small>Forms and Formats> Patient Medical Record</small>
						      </td>
						      <td>Doctors Initial Assessment</td>
						    </tr> --%>
						    <%int count=0; %>
						    <s:iterator value="filesubmmissionlist">
									<tr>
								      <td class="text-center"><%=(++count) %></td>
								      <td>
								    	<a href="downloadDocNabh?id=<s:property value="id"/>"> <i class="fa fa-download"></i> Downlaod</a>
								      	&nbsp;
								      	<a href="<s:property value="filePathnew"/>" target="_blank"  class="btn btn-circle btn-info btn-xs m-b-10">
												<i class="fa fa-eye"></i>View
											</a>
								      	<div style="height:5px;" class="clearfix"></div>
								        	<small>Published on: <s:property value="datetime"/></small>
								        		<div style="height:3px;" class="clearfix"></div>
								         		<small><s:property value="uploadtype"/> > <s:property value="category"/></small>
								      </td>
								      <td><s:property value="remark"/></td>
								      <td><a href="#" onclick="deleteuploadedfile(<s:property value="id"/>)"> <i class="fa fa-trash" aria-hidden="true"></i> Delete</a></td>
								    </tr>
							</s:iterator>
						    
						    </tbody>
						</table>
					</s:elseif>
				</div>


				<div id="switchVisibleEditCommittee2">
					
				</div>


				<div id="switchVisibleNewCommittee2">
					<div class="col-md-12 col-sm-12 padingzero">
						<div class="card card-topline-yellow">
							<div class="card-body" id="bar-parent">
								<div class="alert alert-warning" role="alert">
									<ul>
										<li>If you want to upload any evidence for
											checkpoint/committee meetting/task please use its won section
											to upload it.</li>
										<li>if you have multiple files to submit, please use
											zip/compress and upload it as a single file</li>
											<li>File format make sure file must be one of listed (jpeg,pdf,mp4,zip)</li>
											<li>File size restriction (Max file format size up to 2 MB)</li>

									</ul>
								</div>


								<!-- <form action="savefilesubmittedfileNabh" id="form_sample_1" class="form-horizontal"> -->
								<form action="savefilesubmissionNabh" id="filesubmitformids" class="form-horizontal" method="post" enctype="multipart/form-data">
									<div class="form-body">
										<div class="form-group">
											<label class="control-label col-md-3">Category <span
												class="required"> * </span>
											</label>
											<div class="col-md-5">
												<s:hidden name="action"></s:hidden>
												<select class="form-control input-height" name="filesubmission_category" id="filesubmission_category">
													<option value="0">Select Category</option>
													<option value="Documents">Documents</option>
													<option value="Statutory Documents">Statutory Documents</option>
													<option value="Patient Medical Record Documents">Patient Medical Record Documents</option>
													<option value="Video">Video</option>
													<option value="Others">Others</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">Remark <span class="required"> * </span></label>
											<div class="col-md-5">
												<textarea name="filesubremark" id="filesubremark" placeholder="Remark" class="form-control-textarea" rows="5"></textarea>
											</div>
										</div>



										<div class="form-group">
											<label class="control-label col-md-3">Upload File <span
												class="required"> * </span>
											</label>
											<div class="col-md-5">
												<s:hidden name="subuploadfilesContentType"></s:hidden>
												<s:hidden name="subuploadfilesFileName"></s:hidden>
												<div class="clearfix" style="height: 10px;"></div>
												<!-- <input type="file" name="filesub_uploadfiles" value="" id="filesub_uploadfiles"> -->
												<s:file accept="image/jpeg,video/mp4,.pdf,.zip,.rar" onchange="checksizeoffile()"  name="subuploadfiles" id="filesub_uploadfiles"></s:file>
											</div>
										</div>



									</div>
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">
												<a href="#" onclick="validatefilesubmission()" class="btn btn-info">Submit</a>
												<button onClick="switchVisibleNewCommittee();" type="button"
													class="btn btn-info">Cancel</button>
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
		<!-- Chart end -->

		<!-- start admited patient list -->

		<!-- end admited patient list -->
	</div>



</div>
<!-- end page content -->