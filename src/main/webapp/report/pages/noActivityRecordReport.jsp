<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="report/js/report.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript">

    function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "No Appointment Activity Record List",
					filename: "noApmtActivityRecordRpt",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});

   }

</script>



<div class="">
							<div class="">
								<div class="row details">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

										<h4>No Appointment Activity Record Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											<div class="col-lg-12 col-md-12 topback2 hidden-print">

		<div class="col-lg-2 col-md-2">			
		<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
		<a type="button" title="Save As PDF" class="btn btn-primary" onclick="return previewNoApptActivity();"><i class="fa fa-file-pdf-o"></i></a>
		<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
		</div>
		
		
		<!-- <div id="previewNoApptActivityId" class="col-lg-1 col-md-1">					
		</div> -->
		
		<div id="sendMailNoApptActivityId" class="col-lg-1 col-md-1">			
		</div>
</div>



	<s:if test="noActivityRecordList.size!=0">	
	
	<div class="row">
		<div class="col-lg-12">
			<s:actionmessage cssClass="messageDiv" />			
				<table class="my-table xlstable" style="width: 100%;">
					<thead>
						<tr>
							<th>Sr. No.</th>
							<th>Reg.No</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>DOB</th>
							<th>Mobile No</th>
							<th>Email Id</th>
							<th>Address</th>
							<th>PostCode</th>
							<th>Town</th>
							<th>Country</th>						
						</tr>
					</thead>
					<tbody>										
						
						<% int srno = 1;%>					
						<s:iterator value="noActivityRecordList" status="rowstatus">
						<tr >
							<td><%=srno%></td>							
							<td>
							<s:if test="abrivationid==1">
								0000<s:property  value="id" />
							</s:if>
							<s:else>
								<s:property value="abrivationid"/>/<s:property  value="id" />
							</s:else>
							</td>
							<td><s:property  value="firstName" /></td>
							<td><s:property  value="lastName" /></td>
							<td><s:property  value="dob" /></td>
							<td><s:property  value="mobNo" /></td>
							<td><s:property  value="email" /></td>
							<td><s:property  value="address" /></td>
							<td><s:property  value="postCode" /></td>
							<td><s:property  value="town" /></td>
							<td><s:property  value="country" /></td>							
						</tr>
						<%srno++;%>
						</s:iterator>					
				</tbody>
				
			</table>
		</div>
	</div>
	</s:if>

	<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i>No Activity Record of client Report not found!!
					</h3>
				</s:else>	
				
				<!-- Modal Email-->
	
<div class="modal fade" id="sendEmailNoApptActivityPopup" tabindex="-1" role="dialog"
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
							<s:textfield theme="simple" id = "noApptActivityEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "noApptActivityccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "noApptActivitysubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="noApptActivityEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="noApptActivityReportMailId">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary" onclick="sendNoApptActivityMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
 <br/>
 
 
 <s:form action="noApptActivityRecordClientRpt" name="paginationForm" id="paginationForm" theme="simple">

	<div class="col-lg-12 col-md-12 hidden-print" style="padding:0px;">
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





