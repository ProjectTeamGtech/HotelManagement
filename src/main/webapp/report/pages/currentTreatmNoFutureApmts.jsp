<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.main.common.constants.Constants"%>	
<script type="text/javascript" src="report/js/client.js"></script>
<script type="text/javascript" src="assesmentForms/js/jquery.table2excel.js"></script>

<script type="text/javascript">

   function printExcel() {

       $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Current Treatment with No future Appointment List",
					filename: "curTreatementnoFutureApmt",
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

										<h4>Patient In Current Treatment With No Future Appointments Report</h4>

									</div>
								</div>
								<div class="row ">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div>


											<div class="col-lg-12 col-md-12 topback2 hidden-print">
		
		<div class="col-lg-2 col-md-2">
			
			<a type="button" class="btn btn-primary"  title="Print" onclick="printpage()"><i class="fa fa-print"></i></a>
			<a type="button" title="Save As PDF" onclick="return saveAsPdfCurrentTreatNofutureApmts();" class="btn btn-primary"><i class="fa fa-file-pdf-o"></i></a>
			<a type="button" id="btnxls" title="Save As XLS" onclick="printExcel()" class="btn btn-primary btnxls"><i class="fa fa-file-excel-o"></i></a>
		</div>
		
		
		<div class="col-lg-3 col-md-3" style="display: none" id = "previewCurrentTreNoApmt">
			<!-- <a href="liveData/ClientReport/CurrentTreatmentNoFutureApmts.pdf" class="btn btn-primary" target = "blank">Preview</a> -->
			<input type="button" value="Send PDF in Mail" onclick="return openSendMailCurrentTreatNoFutureApmtsPopup();" class="btn btn-primary">
			
		</div>	
</div>
		<s:if test="currentTreatmentNoFutureApmtsList!=null">
			<table class="my-table xlstable" style="width: 100%;">
				<thead>
					<tr>
					<th>Reg. No</th>
					<th>Name</th>
					<th>Address</th>
					<th>Town</th>
					<th>State</th>
					<th>PostCode</th>
					<th>Contact No.</th>
					<th>Email</th>
					<th>DOB</th>
					<th>Last Apmt Date</th>
					
				</tr>
					</thead>
					<tbody>
					<s:iterator value="currentTreatmentNoFutureApmtsList">
						<tr>
							<td>0000<s:property value="id"/></td>
							<td><s:property value="initial"/><s:property value="firstName"/> <s:property value="middlename"/> <s:property value="lastName"/></td>
							<td><s:property value="address"/></td>	
							<td><s:property value="town"/></td>
							<td><s:property value="county"/></td>
							<td><s:property value="postCode"/></td>
							<td><s:property value="mobNo"/></td>	
							<td><s:property value="email"/></td>
							<td><s:property value="dob"/></td>	
							<td><s:property value="lastApmtDate"/></td>	
						
							</tr>
					
					</s:iterator>
					 			
								</tbody>
							</table>
				
				
		</s:if>
<!-- Modal Email-->
<div class="modal fade" id="sendEmailCurrentTreatmentNoFutureApmtsPopup" tabindex="-1" role="dialog"
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
							<s:textfield theme="simple" id = "noFutAptsEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "noFutAptsccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "noFutAptsSubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="noFutAptsEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="pdfnoFutAptsMailId">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary" onclick="sendCurrentTreatNofutureApmtsMail();">Send</button>
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


	