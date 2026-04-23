<%@ taglib uri="/struts-tags"  prefix="s"%>
<table class="table table-hover table-condensed table-bordered" id="allergyList">
	<col width="2%"/>
	<col width="10%"/>
	<col width="10%"/>
	<col width="30%"/>
	<col width="10%"/>
	<thead>
		<tr>
			<th>#</th>			
			<th class="text-center" style="width: 20%;">Use Name</th>
			<th class="text-center" style="width: 20%;">Date Time</th>
			<th class="text-center" style="width: 50%;">Note</th>
			<th class="text-center" style="width: 5%;">View</th>
			<th class="text-center" style="width: 5%;">Edit</th>
			<th class="text-center" style="width: 5%;">Delete</th>
		</tr>
	</thead>
	
	<tr>
	<% int allcnst = 1;%>
	<s:iterator value="allergyList">
	<tr>
		<td><%=allcnst%></td>		
		<td class="text-center"><s:property value="practitionerName"/></td>
		<td class="text-center"><s:property value="lastModified"/></td>
		<td class="text-center"><s:property value="description"/></td>
		<td class="text-center"><a href="javascript:void(0)" onclick="viewAllergy(<s:property value="id"/>,'viewall')">View</a></td>
		<td class="text-center"><a href="javascript:void(0)" onclick="editAllergy(<s:property value = "id"/>,'editall')"><i class="fa fa-edit"></i></a></td>
		<td class="text-center"><a href="javascript:void(0)" onclick="deleteAllergyAjax(<s:property value = "id"/>)"><i class="fa fa-trash-o"></i></a></td>
	</tr>
	<%allcnst++;%>
	</s:iterator>
	</tr>
	</table>
	
	<!-- add Allergy  -->
	
			
					<div class="col-lg-12"  id="addallergy" style="background-color: rgba(252, 158, 168, 0.44);">
					<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">	
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="Enter Allergy Note" name="allergyText"  id="allergyText" ></textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveAllergyAjax();">Save</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddAllergy()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>
			
				
	<!-- view Allergy -->
						

					<div class="col-lg-12"  id="viewall" style="background-color: rgba(252, 158, 168, 0.44);">
						<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">	
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="View Allergy" name="viewalltext"  id="viewalltext" readonly="readonly">hello world</textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddAllergy()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>
				
	<!-- edit Allergy -->

	
					<div class="col-lg-12"  id="editall" style="background-color: rgba(252, 158, 168, 0.44);">
					<div class="row">
					<br/>
					<div class="col-lg-12">
					<div class="col-lg-1"></div>
					<div class="col-lg-2">
						<label>Note:</label>
					</div>
						<div class="col-lg-9">	
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="60"
								title="Edit Allergy Note" name="editalltext"  id="editalltext" ></textarea>
						</div>
					</div></div>
						<br/>
						<div class="row">	
						<div class="col-lg-12">						
						<div class="col-lg-2"></div>
							<div class="col-lg-10">
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="editsaveAllergyAjax();">Update</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeAddAllergy()">Close</button>
						</div>
					</div>
				</div>
				<br/>
			</div>					
								
				