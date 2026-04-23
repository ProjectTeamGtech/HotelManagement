<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>

<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<style>
.totcolor{
	    background-color: #8a6d3b !important;
}
</style>

<%LoginInfo loginfo = LoginHelper.getLoginInfo(request);%>
<script>

    function printExcel() {

       $(".tablexls").table2excel({
					exclude: ".noExl",
					name: "Doctor Share Report List",
					filename: "doctorShareReport",
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
	
	
</script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Doctor Share Report</h4>

									</div>
								</div>
								<div class="">
									
								<div class="col-lg-12 col-md-12 col-xs-12 topback2">
									<s:form action="Doctorsharereport" theme="simple" id="invoicerportfrm">
									<s:hidden name="order" id="order"/>
									<s:hidden name="orderby" id="orderby"/>
									<div class="form-inline">
										<div class="form-group" style="width:7%;">
											<s:textfield readonly="true" name="fromDate" id="fromDate"
											cssClass="form-control" theme="simple" placeholder="from date" style="width:100%;"></s:textfield>
										</div>
										<div class="form-group" style="width:7%;">
											<s:textfield readonly="true" name="toDate" id="toDate"
											cssClass="form-control" theme="simple" placeholder="to date" style="width:100%;"></s:textfield>
										</div>
										<div class="form-group">
											<s:select list="userProfileList" cssClass="form-control chosen-select" name="practitionerId" id="practitionerId" listKey="id" listValue="fullname" headerKey="0" headerValue="Select Practitioner"></s:select>
										</div>
										<div class="form-group">
											<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
											<a type="button" class="btn btn-primary" title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
											<a type="button" title="Save As PDF" onclick="return saveAsPdfChargesReport();" class="btn btn-primary"><i class="fa fa-file-pdf-o"></i></a>
										    <a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
										</div>
									</div>
								</div>
										

		
		<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
				<table class="my-table tablexls" id ="chargestbl1" style="width: 100%;font-size: 8px">
							<thead>
							<tr>
		
								<th>Bill No</th>
								<th>Patient Name</th>
								<th>Month</th>
								<th>Procedure Name</th>
								<th>Ward</th>
						 		<th>Qty</th>
						 		<th>Rate</th>
						 		<th>Amount</th>
						 		<th>Disc</th>
						 		<th>Share</th>
							</tr>
					</thead>
					
					<tbody>
					  
					  <s:iterator value="doctorShareList">
					      <tr style="background-color: #fcf8e3;">
					        <td><label><s:property value="practitionerName"/></label></td>
					        <td></td>
					        <td></td>
					        <td></td>
					        <td></td>
					        <td></td>
					        <td></td>
					        <td></td>
					        <td></td>
					        <td></td>
					      </tr>
					      
					     <s:iterator value="sharedChargeList">
					       <tr>
					     		<td><s:property value="id"/></td>
								<td><s:property value="clientName"/></td>
								<td><s:property value="month"/></td>
								<td><s:property value="appointmentType"/></td>
								<td><s:property value="ward"/></td>
						 		<td><s:property value="quantity"/></td>
						 		<td><s:property value="charges"/></td>
						 		<td><s:property value="totalAmount"/></td>
						 		<td><s:property value="discount"/></td>
						 		<td><s:property value="shareAmt"/></td>
					     	
					     </tr>
					     </s:iterator>
					     
					     <tr>
					      
					      <td></td>
					      <td></td>
					      <td></td>
					       <td></td>
					      <th class="totcolor">Paid Total</th>
					       <th class="totcolor"><s:property value="totalQty"/></th>
					        <td class="totcolor"></td>
					       <th class="totcolor"><s:property value="totalSub"/></th>
					       <th class="totcolor"><s:property value="totalDisc"/></th>
					       <th class="totcolor"><s:property value="shareTotal"/></th>
					     </tr>
					     
					  </s:iterator>
					</tbody>
							
		 </table>
		</s:form>
		</div>
		
		
				
		
						
					
		
		
   		
		
<!-- Modal Email-->
	
<div class="modal fade" id="sendEmailChargeRptPopup" tabindex="-1" role="dialog"
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
							<s:textfield theme="simple" id = "chargesReportEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "chargesReportccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "chargesReportSubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="chargesReportEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="pdfChargesReportMailId" style="display: none;">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary"  onclick="sendChargesReportMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
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




	