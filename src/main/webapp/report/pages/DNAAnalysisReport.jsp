<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript">

    function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "DNA Analysis Report List",
					filename: "dnaAnalysisReport",
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
    		minDate : '30/12/1880',
    		changeMonth : true,
    		changeYear : true
    	});
    });
    
</script>


<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>Appointment DNA Analysis Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>
<s:form action="userDNAAnalysisSummary" theme="simple" id = "dnaFrm">
	<div class="col-lg-12 col-md-12 topback2 hidden-print">
		<div class="form-inline">
			<div class="form-group">
				<s:if test="%{#userList != 'null'}" >
			<s:select cssClass="form-control chosen-select" 
						id="diaryUser" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" 
						headerKey="0" theme="simple" headerValue="Select User"  />
			</s:if>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="fromDate" id="fromDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="From date"></s:textfield>
			</div>
			<div class="form-group" style="width:7%;">
				<s:textfield readonly="true" name="toDate" id="toDate"
					cssClass="form-control" theme="simple" style="width:100%;" placeholder="To date"></s:textfield>
			</div>
			<div class="form-group">
				<s:submit value="Go" theme="simple" cssClass="btn btn-primary"></s:submit>
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" title="Save As PDF" class="btn btn-primary" onclick="return dnaAnalysysPreview();"><i class="fa fa-file-pdf-o"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
			</div>
			<div class="form-group">
				<div id="sendMailDNAAnalysysId"></div>
			</div>
		</div>
	
		
	</div>

	<s:if test="DNAAnalysisReport.size!=0">	
	
	<div class="row">
		<div class="col-lg-12">
			<s:actionmessage cssClass="messageDiv" />			
				<table class="my-table xlstable" style="width: 100%;">
					<thead>
						<tr>
							<th>Sr. No.</th>
							<th>Reg. No</th>
							<th>Patient Name</th>
							<th>Appointment Type</th>
							<th>Appointment Date</th>
							<th>Clinic/Location</th>							
							<th>DNA Reason</th>
							<th>Charge</th>	
							<th>Mobile No</th>
							<th>Email Id</th>
							<th>Address</th>
							<th>Town</th>
							<th>Country</th>						
						</tr>
					</thead>
					<tbody>										
						
						<% int srno = 1;%>					
						<s:iterator value="DNAAnalysisReport" status="rowstatus">
						<tr >
							<td><%=srno%></td>
							<td>0000<s:property  value="clientId" /></td>
							<td><s:property  value="clientname" /></td>
							<td><s:property  value="apmttypetext" /></td>
							<td><s:property  value="commencing" /></td>
							<td><s:property  value="location" /></td>
							<td><s:property  value="dnareason" /></td>
							<td><s:property  value="charge" /></td>
							<td><s:property  value="mobno" /></td>
							<td><s:property  value="email" /></td>
							<td><s:property  value="address" /></td>
							<td><s:property  value="town" /></td>
							<td><s:property  value="country" /></td>
							
						</tr>
						<%srno++;%>
						</s:iterator>					
				</tbody>
				<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i> Appointment DNA Analysis Report not found!!
					</h3>
				</s:else>	
			</table>
		</div>
	</div>
	</s:if>
</s:form>

	<!-- Modal Email-->
	
<div class="modal fade" id="sendEmaildnaAnalysisPopup" tabindex="-1" role="dialog"
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
							<s:textfield theme="simple" id = "danAnalysisEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "danAnalysisccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "danAnalysissubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="danAnalysisEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="DNAAnalysisReportMailId">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary"  onclick="senddnaAnalysisReportMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
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



 
