<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="assesmentForms/js/assessment.js"></script>
<script type="text/javascript" src="appointment/js/appointment_type.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Prescription Details</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<div class="col-lg-12 col-md-12 topback2">
			<div class="col-lg-3 col-md-2">
			<s:select list="masterlist"
					cssClass="form-control showToolTip chosen-select" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)"></s:select>
		</div>
		<div class="col-lg-1 col-md-1">
			<a class="btn btn-primary" href="#" onclick="opencPopup('addPrescriptiondetails')"><i
				class="fa fa-plus"></i> Add</a>
		</div>
		<div class="col-lg-3 col-md-2">
			<input type="text" name="hh" id="priscsearch" class="form-control" placeholder="Search " onkeyup="searchpriscmaster(this.value)" >
			<p></p>
		</div>
		<div class="col-lg-1 col-md-1">
			
			</div>
			
<s:form action="Prescriptiondetails">	
		<%-- <div class="col-lg-3 col-md-2">
			<s:textfield theme="simple" name="searchText" placeholder="Search By Drug"  cssClass="form-control" />
			<s:select name="searchText" list="priscriptiondetails" listKey="drug" listValue="drug" cssClass="form-control chosen-select" headerKey="" headerValue="All Drugs" theme="simple"  ></s:select>
		</div>
		<div class="col-lg-1 col-md-1">
			<input type="submit" value="Go" class="btn btn-primary"/>
		</div> --%>
</s:form><!--
	
	<div class="col-lg-3 col-md-2">
	<label>Select Master</label>
	<s:select list="masterlist" name="mastername"
					listKey="id" listValue="name" onchange="selectAction(this.value)" cssClass="form-control showToolTip chosen-select"></s:select>
	</div>
	<div class="col-lg-6 col-md-8">
		<a class="btn btn-primary" href="addPrescriptiondetails" style="margin-top:21px;"><i
			class="fa fa-plus"></i> Add</a>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>
--><div class="row">
	<div class="col-lg-12">
		<s:hidden name = "message" id = "message"></s:hidden>
	<s:if test="hasActionMessages()">
	<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
	</s:if>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="">
			<table id=""
				class="table table-hove table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<th class="text-left">Sr No</th>
						<!-- <th class="text-left">Prescription Category</th> -->
						<th class="text-left">Generic Name</th>
						<th class="text-left">Drug</th>
						<!--<th class="text-left">Strength</th>
						--><th class="text-left hidden">MRP</th>
						<th class="text-left hidden">Purchase Price</th>
						<th class="text-left hidden">Sale Price</th>
						<!--<th class="text-left">Expiry Date</th>
						<th class="text-left">Package</th>
						<th class="text-left">Batch No</th>
						<th class="text-left">Mfg</th>
						--><th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
					</tr>
				</thead>
				<tbody id="innertablep">
				
					<s:if test="priscriptiondetails.size!=0">
						<s:iterator value="priscriptiondetails" status="rowstatus">
							<tr>
								<td><s:property value="id" /></td>
								<%-- <td><s:property value="categoryid" /></td> --%>
								<td><s:property value="genericname" /></td>
								<td><s:property value="drug" /></td>
								<!--<td><s:property value="strength" /></td>                
								--><td class="hidden"><s:property value="mrp" /></td>
								<td class="hidden"><s:property value="purchase_price" /></td>    
								<td class="hidden"><s:property value="sale_price" /></td>  
								<!--<td><s:property value="expiry_date" /></td>  
								<td><s:property value="pkg" /></td>  
								<td><s:property value="batch_no" /></td>
								<td><s:property value="mfg" /></td>                                                                       
								--><s:hidden value="%{id}" name="id"></s:hidden>
								<s:url action="editPrescriptiondetails" id="edit">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<!--<td class="text-center"><s:a href="%{edit}"
										class="text-warning">
										<i class="fa fa-edit"></i>
									</s:a></td>
								-->
								<td class="text-center"><a href="#" onclick="opencPopup('editPrescriptiondetails?selectedid=<s:property value="id" />')"><i class="fa fa-edit"></i></a></td>
								<s:url action="deletePrescriptiondetails" id="delete">
									<s:param name="selectedid" value="%{id}"></s:param>
								</s:url>
								<td class="text-center"><s:a href="%{delete}"
										onclick="return confirmedDelete()" cssClass="text-danger">
										<i class="fa fa-trash-o"></i>
										
									</s:a></td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
						There is no Investigation Name List found!!
					</s:else>
			</table>
		</div>
	</div>
</div>

 <s:form action="Prescriptiondetails" name="paginationForm" id="paginationForm" theme="simple">
	<div class="col-lg-12 col-md-12" style="padding:0px;">
		<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
			Total:<label class="text-info"><s:property value="totalRecords" /></label>
		</div>
		<%@ include file="/common/pages/pagination.jsp"%>
	</div>
</s:form>
			 								

											
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
  
  <script>
     $(document).ready(function() {
    var table = $('#tablesort').DataTable( {
        lengthChange: false,
        buttons: [ 'excel', 'colvis' ]
    } );
 
    table.buttons().container()
        .appendTo( '#example_wrapper .col-sm-6:eq(0)' );
} );
    </script>

  

<%-- <script type="text/javascript" src="pharmacy/searchexport/jquery.dataTables.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/dataTables.buttons.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.bootstrap.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/jszip.js"></script>
    <script type="text/javascript" src="pharmacy/searchexport/buttons.html5.js"></script>
     <script type="text/javascript" src="pharmacy/searchexport/buttons.colVis.js"></script>
 --%>


