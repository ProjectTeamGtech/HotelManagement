<%@taglib uri="/struts-tags" prefix="s"%>


<link rel="stylesheet" href="inventory/js/jquery.multiselect.css">
    <link rel="stylesheet" href="inventory/js/app.css">
    
    <script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Product with Generic Name</h4>
								</div>
							</div>


<s:form action="savePrescriptiondetails" id="investigation_name_form" theme="simple">

 <input type='hidden' value="0" id="allcount" />


<div class="panel panel-primary">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12 col-md-12">						
						<div class="table-responsive">
							<table class="table my-table xlstable table-bordered" id = "prescriptiondetailsTable" width="100%" >
								<thead>
									<tr>
										<th class="hidden" align="center"></th>
										<th align="center">Sr.No</th>	
										<th class="hidden" align="center">Prescription Category</th>
										<th align="center">Product Name</th>
										<th align="center">Generic Name</th>
													
										<!--<th align="center">Strength</th>
										--><th align="center" class="hidden">MRP</th>
									   <th align="center" class="hidden">Purchase Price</th>
									   	<th align="center" class="hidden">Sale Price</th>
									   	<th align="center">Location</th>
									   	<th align="center">Specialization</th>
									<th align="center">Risk</th>
									<th align="center">Dose Note</th>
									<th align="center">Is weight based dose</th>
									<th align="center">Strength</th>
									<th align="center">Frequency</th>
									<th align="center">Dose</th>
									   	<!--
									   	<th align="center">Expiry Date</th>
									   	<th align="center">Package</th>
									   	<th align="center">Batch No</th>
									   	<th align="center">Mfg</th>
									--></tr>
								</thead>
								<tbody>
									<tr>
									    
										<td class="hidden"><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
										<td>1</td>
										<td class="hidden"><s:select list="prescriptioncategorylist" listKey="id" listValue="name" name="prescription_details[0].categoryid" title="select prescription category"
										cssClass="form-control showToolTip" > </s:select>
										</td>									
										<td>
											<input type="text" name="prescription_details[0].drug" placeholder="Enter Product Name" id="drug" class="form-control showToolTip filedname" data-toggle="tooltip"/>
											<br>
											<br>
											<p><b>Component:</b></p>
											<input type="text" name="prescription_details[0].component" placeholder="Enter Component" id="component" class="form-control showToolTip filedname" data-toggle="tooltip"/>
										</td>	
										<td><s:textfield name="prescription_details[0].genericname" cssClass="form-control" placeholder="Enter Generic Name" /></td>
										
										<!--<td><input type="text" name="prescription_details[0].strength" placeholder="enter strength" id="strength" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										--><td class="hidden"><input  type="text" name="prescription_details[0].mrp" placeholder="enter mrp" id="mrp" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td class="hidden"><input type="text" name="prescription_details[0].purchase_price" placeholder="enter purchase price" id="purchase_price" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td class="hidden"><input type="text" name="prescription_details[0].sale_price" placeholder="enter sale price" id="sale_price" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><div id=""> <input type='hidden' name="prescription_details[0].location" id="location0" />
										             <input type="checkbox" onclick="selectAll('casem0',this)"/> Select All <br>
													<s:iterator value="medicineLocationList">
												  			<input type="checkbox" class="casem0" value="<s:property value="id"/>" /> <s:property value="name"/> <br>
												  </s:iterator>
												
										  	</div>
										</td>	
										<td><!--
										    <div class="scroll hidden"> <input type='hidden' name=""  />
										        <input type="checkbox" onclick="selectAll('caser0',this)"/> Select All <br>
										        <s:iterator value="">
												  			<input type="checkbox" class="caser0" value="<s:property value="id"/>" /> <s:property value=""/> <br>
												  </s:iterator>
										  	</div>
										
										--><select name="users_list" id="users_list" multiple="multiple" size="15">
							              <optgroup label="Select All">
							                <s:iterator value="specializationList">	
							                	<option value="<s:property value="id"/>"> <s:property value="treatmentName"/> </option>
							                </s:iterator>
							              </optgroup>
							            </select>
										    <!--<s:select list="specializationList" cssClass="form-control" listKey="id" listValue="treatmentName" headerKey="0" headerValue="Select Specialization">
										    
										    </s:select>
										--></td>
										<td>
										  <s:select list="#{'0':'Select Risk','1':'High','2':'Low'}" cssClass="form-control" id="" name="prescription_details[0].risk" ></s:select>
										</td>
										<td class=""><s:select list="dosagenoteList" listKey="id" listValue="name" name="prescription_details[0].dosagenote" title="select dosage note"
												cssClass="form-control showToolTip" > </s:select>
												<br><p></p>
												<p>Prescription type</p>
											<s:select list="prisctypelist" listKey="name" listValue="name" name="prisctypename" title="select type" headerKey="" headerValue="All Types"
												cssClass="form-control  chosen-select" > </s:select>
												<p></p>
												<br>Remark :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div id="remlang"  style="width:100%;display:inline-flex;">
											<s:select name="remark" 
												list="nimairemarklist" listKey="id" 
												listValue="name" cssClass="form-control " 
												headerKey="0" headerValue="All Remarks" theme="simple" 
												id="priscindivisualremark" ></s:select>&nbsp;<i class="fa fa-plus" style="width:10px;" onclick="openEmrPopup('addremarkMaster')"></i>&nbsp;<i class="fa fa-refresh" style="width:10px;" onclick="refreshremarks()"></i>
								</div><p></p>
											<br>UOM :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<s:select name="uom" id="unitextension" list="priscUnitList" 
                  								listKey="name" listValue="name" headerKey="0" 
                  								headerValue="Select Type"  cssClass="form-control"></s:select>
                  							<p></p>
                  							<br>Frequency(Formula) :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<s:select list="dosageList" id="prisccode" name="dosefreq" onchange="setdosegiventiming(this.value)" headerKey="0" headerValue="Frequency" listKey="name" 
												listValue="name" cssClass="form-control "></s:select>
										<p></p>
												<br>No .of Days : &nbsp;&nbsp;&nbsp;<input type="number" name="noofdays" style="width:30%">	
												<p></p>
												<br>QTY :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" name="qty1" style="width:30%">	
												<p></p>
												
										</td>	
										
										<!--<td><input type="text" name="prescription_details[0].expiry_date" placeholder="enter expiry date" id="expiry_date" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="prescription_details[0].pkg" placeholder="enter package" id="pkg" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="prescription_details[0].batch_no" placeholder="enter batch_no" id="batch_no" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
										<td><input type="text" name="prescription_details[0].mfg" placeholder="enter mfg" id="mfg" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									-->
									
									<td><input type="checkbox" name="prescription_details[0].iswbd"  id="iswbd"  class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									<td><input type="number" name="prescription_details[0].strength" placeholder="Enter Product Strength" id="strength" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									<td><input type="number" name="prescription_details[0].frequency" placeholder="Enter Product Frequency" id="frequency" class="form-control showToolTip filedname" data-toggle="tooltip"/></td>
									<td><input type="text" name="prescription_details[0].caldose" placeholder="Enter Product Dose" id="caldose" class="form-control showToolTip filedname" data-toggle="tooltip"/>mq/kg/day</td>
									
									
									</tr>
								</tbody>
							</table>
						</div>
						
						<div class="form-group">
								<!-- <a onclick="addRow('prescriptiondetailsTable')" class="btn btn-primary"><i class="fa fa-plus"></i> Add More</a>
								<a onclick="deleteRow('prescriptiondetailsTable')" class="btn btn-primary" ><i class="fa fa-trash-o"></i> Delete Row</a> -->
								
								
			<%-- 	<label>Assessment Field Name</label><label class="text-danger">*</label>
				<s:textfield id="filedname" name="filedname"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter filedname" placeholder="Enter filedname"/> --%>
					</div>
			</div>
			
		</div>
	</div>
	<!-- <div class="col-lg-3 col-md-2"></div> -->
	
	
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<button class="btn btn-primary" onclick="saveValidate()" > Save </button> 
			<s:reset cssClass="btn btn-primary" />
			<a href="backPrescriptiondetails" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token> 
</s:form>



<script src="inventory/js/jquery.multiselect.js"></script>
<script src="master/js/prescriptiondetails.js"></script>
<script src="inventory/js/app.js"></script>

<script src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>


<script>
$(function () {
    $('.scroll').slimScroll({
        height: '300px'
    });
});

</script>





