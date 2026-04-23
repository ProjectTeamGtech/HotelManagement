<%@taglib uri="/struts-tags" prefix="s"%>
<table class="table table-hover table-condensed table-bordered" id="socialHistoryList">
		<thead>
			<tr>
				<th>#</th>
				<th class="text-center" style="width: 20%;">User Name</th>
				<th class="text-center" style="width: 50%;">Description</th>
				<th class="text-center" style="width: 5%;">View</th>
				<th class="text-center" style="width: 5%;">Edit</th>
				<th class="text-center" style="width: 5%;">Delete</th>				
			</tr>
		</thead>
		
		<%int cnt1 = 1; %>
		<s:iterator value="socialHistoryList">
			<tr>
				<td><%=cnt1 %></td>
				<td class="text-center" style="width: 20%;"><s:property value="practitionerName"/></td>
				<td class="text-center" style="width: 50%;"><s:property value="description"/></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="viewSocialHistory(<s:property value="id"/>,'viewsh')">View</a></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="editSocialHistory(<s:property value="id"/>,'editsh')"><i class="fa fa-edit"></i></a></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="deleteSocialHistory(<s:property value="id"/>)"><i class="fa fa-trash-o"></i></a></td>
			</tr>
			<%cnt1++; %>
		</s:iterator>
		
</table>


	<!-- add social history -->
	
					<div class="col-lg-12"  id="addsocialHistory" style="background-color: rgba(252, 158, 168, 0.44);">
					<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">	
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="50"
								title="Enter Social History" name="socialHistoryText"  id="socialHistoryText" ></textarea>
							</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveSocialHistoryAjax();">Save</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddSocialHistory()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>
				
	<!-- edit social history -->
	
					<div class="col-lg-12"  id="editsh" style="background-color: rgba(252, 158, 168, 0.44);">
					<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">	
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="Edit Social History" name="editshtext"  id="editshtext" ></textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="editsaveSocialHistoryAjax();">Update</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddSocialHistory()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>				
				
	<!-- view social history -->
						
	
					<div class="col-lg-12"  id="viewsh" style="background-color: rgba(252, 158, 168, 0.44);">
						<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">	
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="View Social History" name="viewshtext"  id="viewshtext" readonly="readonly">hello world</textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddSocialHistory()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>		
				
						