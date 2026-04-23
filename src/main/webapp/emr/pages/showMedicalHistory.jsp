<%@taglib uri="/struts-tags" prefix="s"%>
<table class="table table-hover table-condensed table-bordered" id="shoemrHisory">
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
		
		<%int cnt = 1; %>
		<s:iterator value="medicalHistoryList">
			<tr>
				<td><%=cnt %></td>
				<td class="text-center" style="width: 20%;"><s:property value="practitionerName"/></td>
				<td class="text-center" style="width: 50%;"><s:property value="description"/></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="viewMedicalHistory(<s:property value="id"/>,'viewmh')">View</a></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="editMedicalHistory(<s:property value="id"/>,'editmh')"><i class="fa fa-edit"></i></a></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="deleteMedicalHistory(<s:property value="id"/>)"><i class="fa fa-trash-o"></i></a></td>
			</tr>
			<%cnt++; %>
		</s:iterator>
		
		
</table>

	<!-- add medical history -->
	
					<div class="col-lg-12"  id="addemrHisory" style="background-color: rgba(252, 158, 168, 0.44);">
					<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">							
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="50"
								title="Enter Medical History Note" name="medicalHistoryText"  id="medicalHistoryText" ></textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveMedicalHistoryAjax();">Save</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddMedicalHistory()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>	
		
		
	<!-- edit medical history -->
	
					<div class="col-lg-12"  id="editmh"  style="background-color: rgba(252, 158, 168, 0.44);">
					<br/>
						<div class="row">
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">							
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="Edit Medical History" name="editmhtext"  id="editmhtext" ></textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="editsaveMedicalHistoryAjax();">Update</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddMedicalHistory()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>						
						
	
					<div class="col-lg-12"  id="viewmh"  style="background-color: rgba(252, 158, 168, 0.44);">
					<br/>
					<div class="row">
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">							
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="View Medical History" name="viewmhtext"  id="viewmhtext" readonly="readonly">hello world</textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-9">
							<!-- <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveMedicalHistoryAjax();">Send</button> -->
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddMedicalHistory()">Close</button>
					</div>
					</div>
				</div>
				<br/>
			</div>		
						
	
