<%@ taglib uri="/struts-tags"  prefix="s"%>

 <style>
 #upload pre{
 background:#fff;
 border: 1px solid #fff;
 }
 
  #editupload pre{
 background:#fff;
 border: 1px solid #fff;
 }
 </style>

<table class="table table-hover table-condensed table-bordered" id="docList">
	<col width="2%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="30%"/>
	<col width="10%"/>
	<thead>
		<tr>
			<th>#</th>			
			<th class="text-center" style="width: 20%;">User Name</th>
			<th class="text-center" style="width: 20%;">File Name</th>
			<th class="text-center" style="width: 20%;">Date Time</th>
			<th class="text-center" style="width: 50%;">Note</th>
			<th class="text-center" style="width: 5%;">Download</th>
			<th class="text-center" style="width: 5%;">View</th>
			<th class="text-center" style="width: 5%;">Edit</th>
			<th class="text-center" style="width: 5%;">Delete</th>
		</tr>
	</thead>
	
	<tr>
	<% int doccnst = 1;%>
	<s:iterator value="docList">
	<tr>
		<td><%=doccnst%></td>		
		<td class="text-center" style="width: 20%;"><s:property value="practitionerName"/></td>
		<td class="text-center" style="width: 20%;"><s:property value="fileName"/></td>
		<td class="text-center" style="width: 20%;"><s:property value="lastModified"/></td>
		<td class="text-center" style="width: 50%;"><s:property value="description"/></td>
		<td class="text-center" style="width: 5%;"><a href="downloadDocEmr?filename=<s:property value="fileName"/>">Download</a></td>
		<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="viewDocument(<s:property value="id"/>,'viewdoc')">View</a></td>
		<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="editDocument(<s:property value = "id"/>,'editdoc')"><i class="fa fa-edit"></i></a></td>
		<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="deleteDocumentAjax(<s:property value = "id"/>)"><i class="fa fa-trash-o"></i></a></td>
	</tr>
	<%doccnst++;%>
	</s:iterator>
	</tr>
	</table>
	
	<!-- add Document  -->
	

<div class="row">
					<div class="col-lg-12"  id="adddocument" style="background-color: rgba(252, 158, 168, 0.44); display: none;">
					<s:form>
					<div class="row">
					<div class="col-lg-1"></div>
					<div class="col-lg-6">						
						<br/>
							<input type="file" name="fileUpload" id="fileUpload" />						
						</div>
						<div id="upload">
						</div>						
						<div class="col-lg-5">
						<br/>						
							<button type="button" class="btn btn-primary" data-dismiss="modal" id="uploadbtn" value="upload" onClick="fileUpload1(this.form,'uploadAjaxFilesEmr','upload'); return false;">Upload</button>
						</div></div>						
						</s:form>
						<br/>
						<div class="row">
						<div class="col-lg-1"></div>
						<div class="col-lg-10">													
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="Enter Document Note" name="docText"  id="docText" ></textarea>
						</div>
						</div>
						<br/>
						<div class="row">
						<div class="col-lg-2"></div>
						<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" id="savebtn" onclick="saveDocAjax()">Save</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddDoc()">Close</button>
						</div>
						</div>
						<br/>
					</div>
				</div>
				
	
	<!-- view Document -->
						
	<div class="row">
					<div class="col-lg-12"  id="viewdoc" style="background-color: rgba(252, 158, 168, 0.44); display: none;">					
					<br/>
					<div class="row">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>File Name</label>
					</div>
					<div class="col-lg-6">
						<input type="text" name="editfileUpload" id="editfileUpload" readonly="readonly">						
					</div>
					</div>
						<br/>
					<div class="row">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">						
						<label>Description</label>
					</div>
					<div class="col-lg-9">	
						<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
							title="View Document" name="viewdoctext"  id="viewdoctext" readonly="readonly">hello world</textarea>
					</div>
					</div>
					<br/>
					<div class="row">
					<div class="col-lg-2"></div>
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddDoc()">Close</button>
					</div>
					<br/>
				</div>
			</div>
			
				
				<!-- edit Documentation -->

	<div class="row">
					<div class="col-lg-12"  id="editdoc" style="background-color: rgba(252, 158, 168, 0.44); display: none;">
					<form>
					<br/>
					<div class="row">
					<div class="col-lg-1"></div>
					<div class="col-lg-10">
						<label>File Name</label>  :  <label id="fname"></label>
					</div>
					</div>
					<div class="row">
					<div class="col-lg-1"></div>
					<div class="col-lg-6">
							<input type="file" name="editfUpload" id="editfUpload"/>					
						</div>					
						<div id="editupload">
						</div>
					<div class="col-lg-4">	
						<button type="button" class="btn btn-primary" data-dismiss="modal" value="upload" onClick="editfileUpload1(this.form,'edituploadAjaxFilesEmr','editupload'); return false;">Upload</button>
					</div>
					</div>
						</form>
						<br/>
					<div class="row">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Description</label>
					</div>
					<div class="col-lg-9">							
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="Enter Body" name="editdoctext"  id="editdoctext" ></textarea>
					</div>
					</div>
					<br/>
					<div class="row">
					<div class="col-lg-4"></div>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="editsaveDocAjax();">Update</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddDoc()">Close</button>
					</div>
					<br/>
				</div>
			</div>	
	