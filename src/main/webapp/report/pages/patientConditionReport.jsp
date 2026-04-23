<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script> 
<script type="text/javascript" src="common/js/pagination.js"></script>

<style>
	.chosen-container, .chosen-container-single, .chosen {
    width: 100% !important;
    min-width: 100%;
}
</style>

<script>

function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Patient Conditon Report List",
					filename: "patientConditionList",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
   }


$(document).ready(function() {

	$("#fromDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true

	});

	$("#toDate").datepicker({

		dateFormat : 'dd-mm-yy',
		yearRange: yearrange,
		minDate : '30-12-1880',
		changeMonth : true,
		changeYear : true
	});
});

function setSorting(column,order){
	if(order=='asc'){
		order = 'desc';
	}else{
		order = 'asc';
	}
	document.getElementById('orderby').value = column;
	document.getElementById('order').value = order;
	document.getElementById('invoicerportfrm').submit();
}


</script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Patient Condition List</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											<s:form action="Conditionreport" theme="simple" id = "conditionreportfrm">
<s:hidden name="order" id="order"/>
<s:hidden name="orderby" id="orderby"/>

	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topback2 hidden-print">	
		<div class="form-inline">
			<div class="form-group">
				<SELECT class="form-control" name="type" id="type">
			  <option value="ALL">ALL Location</option>
			  <option value="IPD">IPD</option>
			  <option value="OPD">OPD</option> 
			</SELECT>
			</div>
			<div class="form-group">
				<s:select list="conditionList"  name="condition1" id="condition1" 
				cssClass="form-control chosen-select" theme="simple" listKey="id" listValue="treatmentName" headerKey="0" headerValue="Select Condition 1" />
			</div>
			<div class="form-group">
				<s:select list="conditionList"  name="condition2" id="condition2" 
				cssClass="form-control chosen-select" theme="simple" listKey="id" listValue="treatmentName" headerKey="0" headerValue="Select Condition 2" />
			</div>
			<div class="form-group">
				<s:select list="conditionList"  name="condition3" id="condition3"
				cssClass="form-control chosen-select" theme="simple" listKey="id" listValue="treatmentName" headerKey="0" headerValue="Select Condition 3" />
			</div>
			<div class="form-group">
				<s:select list="citylist"  name="city" id="city"
				cssClass="form-control chosen-select" theme="simple" listKey="city" listValue="city" headerKey="0" headerValue="Select City" />
			</div>
			<div class="form-group" style="width:7%">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
				cssClass="form-control" theme="simple" cssStyle="width:100%" placeholdr="from date"></s:textfield>
			</div>
			<div class="form-group" style="width:7%">
				<s:textfield readonly="true" name="toDate" id="toDate"
				cssClass="form-control" theme="simple" cssStyle="width:100%" placeholdr="to date"></s:textfield>
			</div>
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" title="Save As PDF" class="btn btn-primary" onclick="return treatmentEpisodePreview();"><i class="fa fa-file-pdf-o"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
			</div>
		</div>
		
		</div>
		
		<div class="col-lg-12" style="padding:0px;">
			<!--<s:actionmessage cssClass="messageDiv" />			
				--><table class="my-table xlstable" style="width: 100%;">
					<thead>
						<tr>
							<th>Sr. No.</th>
							<th>OPD/IPD</th>
							<th>Patient Name</th>
							<th>Address</th>
							<th>Contact</th>
							<th>Age </th>							
							<th>Condition 1</th>
							<th>Condition 2</th>
							<th>Condition 3</th>
						</tr>
					</thead>
					<tbody>										
					  <%int i=0; %>	
					  <s:iterator value="ipdConditionList">
						<tr>
						    <td><%=(++i) %></td>
							<td><s:property value="type"/></td>
							<td><s:property value="clientname"/></td>
							<td><s:property value="address"/></td>
							<td><s:property value="mobno"/></td>
							<td><s:property value="age"/></td>							
							<td><s:property value="condition"/></td>
							<td><s:property value="condition2"/></td>
							<td><s:property value="condition3"/></td>
						</tr>
					 </s:iterator>	
					 
					 <s:iterator value="opdConditionList">
						<tr>
						    <td><%=(++i) %></td>
							<td><s:property value="type"/></td>
							<td><s:property value="clientname"/></td>
							<td><s:property value="address"/></td>
							<td><s:property value="mobno"/></td>
							<td><s:property value="age"/></td>							
							<td><s:property value="condition"/></td>
							<td><s:property value="condition2"/></td>
							<td><s:property value="condition3"/></td>
						</tr>
					 </s:iterator>	
						
				  </tbody>
				
			</table>
		</div>
		
		<s:form action="Conditionreport" name="paginationForm" id="paginationForm" theme="simple">
				<div class="col-lg-12 col-md-12 hidden-print" style="padding:0px;margin-top:15px;">
					<div class="col-lg-4 col-md-4 text-left" style="padding:0px;">
						Total:<label class="text-info"><s:property value="totalRecords" /></label>
					</div>
					<%@ include file="/common/pages/pagination.jsp"%>
			  </div>
		</s:form>
		
		
		
		
		<div class="row hidden-print">
		
		
		
		<!-- <div id="treatmentPreviewId" class="col-lg-1 col-md-1" style="margin-top: 21px;">			
		</div> -->
		<div id="sendMailTreatmentId" class="col-lg-1 col-md-1">					
		</div> 
	</div>
	
	<!--<s:if test="treatmentEpisodeList.size!=0">	
	
	-->
	</s:if>
</s:form>

	<!-- Modal Email-->
	
<div class="modal fade" id="sendEmailTreatmentPopup" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
					<div class="row">
					<div class="col-lg-1 col-md-1">	
					</div>
				
					</div>
						<div class="form-group">
							<label>To:</label>
							<s:textfield theme="simple" id = "treatmentEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "treatmentccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "treatmentsubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="treatmentEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="treatmentReportMailId" style="display: none;">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary" onclick="sendTreatmentReportMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
 
 <br/> <br/>
 
 
											

											
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


