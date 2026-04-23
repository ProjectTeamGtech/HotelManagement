	<%@taglib uri="/struts-tags" prefix="s"%>
	
	<table class="table table-hover table-condensed table-bordered" id = "prescriptionList">
		<thead>
			<tr>
				<th>#</th>
				<th class="text-center" style="width: 20%;">User Name</th>
				<th class="text-center" style="width: 50%;">Treatment</th>
				<th class="text-center" style="width: 20%;">Date/Time</th>
				<th class="text-center" style="width: 5%;">View</th>
				<th class="text-center" style="width: 5%;">Edit</th>
				<th class="text-center" style="width: 5%;">Delete</th>				
			</tr>
		</thead>
		<tr>
		<%int precnt = 1; %>
		<s:iterator value="prescriptionList">
			<tr>
				<td> <%=precnt%></td>
				<td class="text-center" style="width: 20%;"><s:property value="practitionerName"/></td>
				<td class="text-center" style="width: 50%;"><s:property value="description"/></td>
				<td class="text-center" style="width: 20%;"><s:property value="lastModified"/></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="viewPrescription(<s:property value="id"/>,'viewpre')">View</a></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="editPrescription(<s:property value="id"/>,'editpre')"><i class="fa fa-edit"></i></a></td>
				<td class="text-center" style="width: 5%;"><a href="javascript:void(0)" onclick="deletePrescriptionAjax(<s:property value="id"/>)"><i class="fa fa-trash-o"></i></a></td>
			</tr>	
			<%precnt++; %>	
		</s:iterator>
		</tr>			
	
</table>


<!-- add prescription history -->
	
					<div class="col-lg-12"  id="addprescription" style="background-color: rgba(252, 158, 168, 0.44);">
					<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">	
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="50"
								title="Enter Prescription" name="prescriptionText"  id="prescriptionText" ></textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="savePrescriptionAjax();">Save</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddPrescription()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>
				
<!-- view prescription -->
						
	
					<div class="col-lg-12"  id="viewpre" style="background-color: rgba(252, 158, 168, 0.44);">
						<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">	
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="View Prescription" name="viewpretext"  id="viewpretext" readonly="readonly">hello world</textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddPrescription()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>

<!-- edit prescription -->

	
					<div class="col-lg-12"  id="editpre" style="background-color: rgba(252, 158, 168, 0.44);">
						<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">	
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="Edit Prescription" name="editpretext"  id="editpretext" ></textarea>
							</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="editsavePrescriptionAjax();">Update</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddPrescription()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>				
								