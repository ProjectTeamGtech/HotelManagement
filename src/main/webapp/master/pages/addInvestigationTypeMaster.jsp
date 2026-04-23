<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="master/js/investigationType.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script>
bkLib.onDomLoaded(function() {
	new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('invstremark');
});
</script>

<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Investigation Type</h4>
								</div>
							</div>

<s:form action="saveInvestigationMaster" id="investigation_type_form" theme="simple">
<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">						
						<div class="table-responsive">
							
							<table class="table table-striped table-hover table-condensed" id = "investigationTable">
								<thead>
									<tr>
										<th align="center">Delete</th>
										<th align="center">#</th>	
										<th align="center">Department</th>
										<th align="center">Investigation Type</th>	
										<th align="center">Investigation Group</th>
										<th align="center">Report Type</th>
										<th align="center">Charge</th>		
										<th align="center">Section</th>						
										<th align="center">Is Round Charge</th>
										<th align="center">Select Ward</th>					
									</tr>
								</thead>
								<tbody>
									<tr>
										<TD><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<TD>1</TD>		
										<td><!--<s:select list="allInvsUserList" listKey="id" listValue="fullname" name="investigation_type[0].department" placeholder="Select Department" title="Select Department" headerKey="0" headerValue="Select Department"
											cssClass="form-control showToolTip filedname"></s:select>-->
											<s:select list="alljobtitlelist" listKey="id" listValue="name" name="investigation_type[0].department" placeholder="Select Department" title="Select Department" headerKey="0" headerValue="Select Department" onchange="selectjobTitle(this.value)"
											cssClass="form-control showToolTip filedname"></s:select>
											</td>
										<td><input type="text" name="investigation_type[0].name" maxlength="50" placeholder="Enter Invetstigation Type"
											class="form-control showToolTip filedname" data-toggle="tooltip"></td>
											
											
										 <td><input type="text" name="investigation_type[0].group" maxlength="50" placeholder="enter group name"
											class="form-control showToolTip filedname" data-toggle="tooltip"></td>
									   
									  
									   
									    <td>
									    	<%-- <s:select list="reporttype_list" id="reporttype_list" listKey="id" listValue="name" name="investigation_type[0].report_type"  cssClass="form-control"></s:select> --%>
									    	
									    		<s:select name="investigation_type[0].report_type" id="reporttype_list" 
												list="#{'Numerical':'Numerical','Writeup':'Writeup','Text':'Text','Hybreed':'Hybreed'}"
												cssClass="form-control" onchange="setActionForAll()"></s:select>
									    </td>
									    
									    <td><input type="text" name="investigation_type[0].charge" maxlength="50" placeholder="enter charge"
											class="form-control showToolTip filedname" data-toggle="tooltip"></td>
									 <td ><s:select list="sectionlist" listKey="id" listValue="name" name="investigation_type[0].sectionid" headerKey="0" headerValue="Select Section" cssClass="form-control" placeholder="enter section id" id="sectionid"></s:select></td>
									 <td>
									   <s:checkbox name="investigation_type[0].roundcharge"  ></s:checkbox>
									 </td>
									   <td><s:select list="wardlist" listKey="id" listValue="wardname" name="investigation_type[0].wardid" id="wardid" title="Select Ward" headerKey="0" headerValue="Select Ward"
											cssClass="form-control chosen-select"   > </s:select></td> 		
									
								</tbody>
							</table>
							<label>Add Default Remark</label>
							 <s:textarea cssClass="form-control" rows="3" maxlength="" name="invstremark" id="invstremark"></s:textarea>
							<input type="hidden" name='hiddenremark' id='hiddenremark' value="">
						</div>
						<div class="form-group">
								<a onclick="addRow('investigationTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								
								<a onclick="deleteRow('investigationTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a>
								
								
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
			<s:submit cssClass="btn btn-primary" value="Save" id="addinvst" onclick="validateAF();"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="backInvestigationMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
