<%@taglib uri="/struts-tags" prefix="s"%>
<script src="master/js/stanardcharge.js"></script>



<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Standard Charge</h4>
								</div>
							</div>

<s:form action="saveStandardcharges" id="stanardchargeform" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">						
						<div class="table-responsive">
							<table class="table table-striped table-hover table-condensed" id = "stanardchargeTable" width="100%" >
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Ward</th>
										<th align="center">Pay By</th>
										<th align="center">Charge Name</th>			
										<th align="center">Amount</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<td>1</td>
										<td>
										 <s:select theme="simple" list="wardList" name="standardcharges[0].wardid" listKey="id" listValue="wardname" cssClass="form-control" />
										</td>	
										<td>
										   <select class="form-control" name="standardcharges[0].payby" >
										     <option value="Self">Self</option>
										     <option value="Third Party">Third Party</option>
										   </select>
										</td>								
										<td><input type="text" name="standardcharges[0].name" placeholder="enter charge name" id="name" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>	
										<td><input type="text" name="standardcharges[0].charge" placeholder="enter amount" id="charge" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="form-group">
								<a onclick="addRow('stanardchargeTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteRow('stanardchargeTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
			<%-- 	<label>Assessment Field Name</label><label class="text-danger">*</label>
				<s:textfield id="filedname" name="filedname"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter filedname" placeholder="Enter filedname"/> --%>
					</div>
			</div>
			
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="saveStandardcharges" />
			<s:reset cssClass="btn btn-primary" />
			<a href="Standardcharges" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
