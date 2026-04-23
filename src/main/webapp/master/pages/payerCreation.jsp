<%@taglib uri="/struts-tags" prefix="s"%>
<style>
.scrollclass{
	height: 400px;
	overflow-x: scroll; 
	overflow-y: scroll;
}
@media (min-width: 992px){
	#nonDiscountModal {
	    width: 1265px;
	}
}
</style>
<script type="text/javascript" src="common/js/fullscreen.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/payerCreation.js"></script>
<script type="text/javascript" src="diarymanagement/js/razorpayment.js"></script>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0;">
<div class="row details">
	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
		<h4>Payer Creation Master </h4>
	</div>
</div>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="height: 50px;padding:10px; ">
	<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
		<s:select list="masterlist"
		cssClass="form-control showToolTip chosen-select" name="mastername"
		listKey="id" listValue="name" onchange="selectAction(this.value)"></s:select>
	</div>
	<div class="col-lg-8 col-md-8 col-xs-6 col-sm-6">
		<input type="button" class="btn btn-primary" onclick="openPayerType()" value ="Payer Type" >
		<input type="button" class="btn btn-primary" value ="Register Payer" onclick="openSamePopup('addThirdParty?isFromPG=1')">
		<input type="button" class="btn btn-primary" onclick="openNonDiscountablePopup()" value ="Non Discountable Service" >
		<input type="button" class="btn btn-primary hidden" onclick="openDiscountablePopup()" value ="Discountable Service" >
		
		<a href="addchargesThirdParty?frompayercreation=1" class="btn btn-primary">Create Payer Charges</a> 
		<input type="button" class="btn btn-primary" value="TP Follower" onclick="opencPopup('TPFollower')"  >
		<input type="button" class="btn btn-primary" value="New ThirdParty Charges" onclick="openBlankPopup('newThirdpartyChargeThirdParty?action=0')"  >
	</div>
</div>

<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:10px; ">
	<input type="text" id="paymentAmount" placeholder="Enter Amount">
	<a href="#" class="btn btn-info" >Pay</a>
</div>

<%int i=1; %>
<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding: 0;">
	<table class="my-table xlstable" style="width:100%;text-align:left;" >
		<tr style="text-align: center !important;">
			<th>Sr.No</th>
			<th>Patient Type</th>
			<th>Payer Type</th>
			<th>Payer</th>
			<th>Short Name</th>
			<th>Town/City</th>
			<th>State</th>
			<th>Phone No.</th>
			<th>Email</th>
			<th style="text-align: center;">Status</th>
			<th style="text-align: center;">Payment Type</th>
		</tr>
		<s:iterator value="thirdPartyDetailsList">
			<tr>
				<td><%=i++%></td>
				<td><s:property value='patientType'/></td>
				<td><s:property value='type'/></td>
				<td><s:property value='companyName'/></td>
				<td><s:property value='shortname'/></td>
				<%-- <td><s:property value='town'/></td>
				<td><s:property value='county'/> </td> --%>
				<td><s:property value='city'/></td>
				<td><s:property value='state'/> </td>
				<td><s:property value='TelephoneLine'/></td>
				<td><s:property value='CompnyEmail'/></td>
				<td style="text-align: center;">
					<s:if test="validityStatus==0">
						Inactive
					</s:if>
					<s:else>
						Active
					</s:else>
				</td>
				<td style="text-align: center;"><s:property value='payementType'/></td>
			</tr>
		</s:iterator>
	</table>
</div>


 <div class="modal fade" id="nonDiscoutablePopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.43);">
		<div class="modal-dialog modal-lg" id="nonDiscountModal">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal" onclick="closedPopUpWindow()">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Non Discount Services</h5>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
							<label>Patient Type</label>
							<select class="form-control chosen-select" id="nds_payerType" onchange="setPayerType_NDS(this.value)">
								<option value="">Select Patient Type</option>
								<option value="0">Self</option>
								<option value="1">Third Party</option>
							</select>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12" id="typepayerdiv">
							<label>Payer Type</label>
							<s:select id="type1" name="type" list="thirdPartyTypeList"
								listKey="id" listValue="type" headerValue="Select Payer Type"
								headerKey="0" cssClass="form-control showToolTip chosen-select"
								data-toggle="tooltip" onchange="setTypeName(this.value)" />
						</div>
						<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
							<label>Payer<span class="text-danger">*</span></label><br>
							<s:select id="typeName1" onchange='changeNDSDepartment()' name="typeName" list="tpnameList"
							listKey="id" listValue="typeName" headerValue="Select Payer"
							headerKey="0" cssClass="form-control showToolTip chosen-select"
							data-toggle="tooltip" />
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label>Department</label>
							<s:select id="nds_department" onchange="displayChargesData()" multiple="true" list="masterChargeList"
							listKey="name" listValue="name" cssClass="form-control showToolTip chosen-select"
							data-toggle="tooltip" />
						</div>
						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<!-- <input type="button" onclick="displayChargesData()" class="btn btn-primary" value="Search"> -->
							<%-- <label>Service Name</label>	
							<s:textfield cssClass="form-control" placeholder="Search by Service Name"></s:textfield> --%>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
						<div class="col-lg-5 col-md-5 col-xs-12 col-sm-12">
							<label>Services</label>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-12 col-sm-12">
							<label>Non Discount Services</label>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
						<input type="hidden" id="ndsId" >
						<div class="col-lg-5 col-md-5 col-xs-12 col-sm-12 table-responsive scrollclass">
							<table class="my-table" style="width: 100%;">
								<thead>
									<tr>
										<th><input type="checkbox" onclick="selectNDSCheckBox(this.checked)"></th>
										<th>Name</th>
										<th>Code</th>
										<th>Amount</th>
										<th>Department</th>
									</tr>
								</thead>
								<tbody id="nds_tbody">
									
								</tbody>
							</table>
						</div>
						<div class="col-lg-7 col-md-7 col-xs-12 col-sm-12 table-responsive scrollclass">
							<table class="my-table" style="width: 100%;">
								<thead>
									<tr>
										<th>Department</th>
										<th>Service Name</th>
										<th>Code</th>
										<th>Amount</th>
										<th style="text-align: center;">Delete</th>
									</tr>
								</thead>
								<tbody id="nds_service_tbody">
									
								</tbody>
							</table>
							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-lg-5 col-md-5 col-xs-12 col-sm-12">
						<button type="button" class="btn btn-primary" onclick="saveNDS()" onclick="">Save</button>
						<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button> 
					</div>
					<div class="col-lg-7 col-md-7 col-xs-12 col-sm-12">
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="discoutablePopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.43);">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Discount Services</h5>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
							<label>Type</label>
							<select class="form-control">
								<option>Self</option>
								<option>Third Party</option>
							</select>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12">
							<label>Payer</label>
							<select class="form-control">
								<option>General</option>
								<option>Employee</option>
								<option>Student</option>
							</select>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<label>Department</label>	
							<div class="form-inline">
								<select class="form-control" style="width:31%;">
									<option>Central Pathology LAB</option>
									<option>Dental</option>
									<option>ODRM</option>
								</select>
								<input type="button" class="btn btn-primary form-control" value="Search">
							</div>
						</div>
					</div>
					
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding-top: 10px;">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 table-responsive">
							<table class="my-table" style="width: 100%">
								<tbody>
									<tr>
										<td style="width: 10%;"></td>
										<td style="width: 40%;"></td>
										<td style="width: 20%;"></td>
										<td style="width: 20%;vertical-align: bottom;text-align: right;">Discount All</td>
										<td style="width: 10%;"><input type="text" class="form-control"></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 table-responsive">
							<table class="my-table" style="width: 100%">
								<thead>
									<tr>
										<th style="width: 10%;"><input type="checkbox"></th>
										<th style="width: 40%;">Service Name</th>
										<th style="width: 20%;">Department Name</th>
										<th style="width: 20%;">Service Code</th>
										<th style="width: 10%;">OPD Discount</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="checkbox"></td>
										<td>24 HR URINE TEST</td>
										<td>Central Pathology LAB</td>
										<td>LABB1003</td>
										<td><input type="text" class="form-control"></td>
									</tr>
									<tr>
										<td><input type="checkbox"></td>
										<td>TEST TEST</td>
										<td>Central Pathology LAB</td>
										<td>LABB1004</td>
										<td><input type="text" class="form-control"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="">Save</button>
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="payergrouptype" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.43);">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal" onclick="reloadPayerPage()">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Payer Type</h5>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="text-align: right;">
						<a href="#" class="btn btn-primary" onclick="addNewHidden()">Add New</a>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 table-responsive" style="padding-top: 10px;">
						<table class="my-table" style="width: 100%">
							<thead>
								<tr>
									<th style="width: 10%;">Sr No</th>
									<th style="width: 30%;">Patient Type</th>
									<th style="width: 40%;">Payer Type</th>
									<th style="width: 20%;">Edit</th>
								</tr>
							</thead>
							<tbody id="payerlist">
																	
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="addnewdiv" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.43);">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Add Payer Type</h5>
				</div>
				<div class="modal-body">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
						<div class="form-group">
							<label>Patient Type</label>
							<select class="form-control" id="pt_patientType">
								<option value="">Select Patient Type</option>
								<option value='0'>Self</option>
								<option value='1'>Third Party</option>
							</select>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
						<div class="form-group">
							<label>Payer</label>
							<input type="text" class="form-control" id="pt_payerType" placeholder="Enter Payer Type">
						</div>
					</div>
					
				</div>
				<div class="modal-footer">
					<a href="#" onclick="validateNewPayerType()" class="btn btn-primary">Save</a>
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="editpayertype" tabindex="-1" role="dialog"
	aria-labelledby="lblsemdsmspopup" aria-hidden="true" data-keyboard="false" data-backdrop="static" style="background-color: rgba(0, 0, 0, 0.43);">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button  type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="">Update Payer Type</h5>
				</div>
				<div class="modal-body">
					<input type="hidden" id="edit_pt_payerID">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
						<div class="form-group">
							<label>Patient Type</label>
							<select class="form-control" id="edit_pt_patientType">
								<option value="">Select Patient Type</option>
								<option value='0'>Self</option>
								<option value='1'>Third Party</option>
							</select>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" >
						<div class="form-group">
							<label>Payer</label>
							<input type="text" class="form-control" id="edit_pt_payerType" placeholder="Enter Payer Type">
						</div>
					</div>
					
				</div>
				<div class="modal-footer">
					<a href="#" onclick="validateUpdatePayerType()" class="btn btn-primary">Update</a>
					<button  type="button" class="btn btn-primary hidden" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


</div>

	 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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
						