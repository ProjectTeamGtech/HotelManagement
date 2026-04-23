<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="report/js/misreport.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>


<div class="row hidden-print">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Report</a></li>
			<li class="active">MIS Report</li>
		</ol>
	</div>
</div>

<div style='font-size: 18px; font-weight: bold; margin-left: -2px;'  class="row">AQP Physiotherapy MIS Reports</div><br/>

<script>
	$(document).ready(function() {

		$("#fromDates").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});

		$("#toDates").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true
		});
	});
	
	
	function misSubmit(){
		document.getElementById('hdnsendmail').value = "1";
		document.getElementById('misfrm').submit();
	}
	
	
	 bkLib.onDomLoaded(function() {
	        
      	 new nicEditor().panelInstance('invoiceReportEmailBody');
      	 $('.nicEdit-panelContain').parent().width('500px');
      	 $('.nicEdit-panelContain').parent().next().width('500px');
      	 
      	 $('.nicEdit-main').width('100%');
      	 $('.nicEdit-main').height('80px');
    });

	</script>
	
	<div class="row hidden-print">
	<s:form action="Mis" id="misfrm" theme="simple">
		<s:hidden name="hdnsendmail" id="hdnsendmail"/>
		<div class="col-lg-2 col-md-2">
			<label>From Date</label>
			<s:textfield readonly="true" name="fromDate" id="fromDates"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		
		<div class="col-lg-2 col-md-2">
			<label>To Date</label>
			<s:textfield readonly="true" name="toDate" id="toDates"
				cssClass="form-control" theme="simple"></s:textfield>
		</div>
		<div class="col-lg-1 col-md-1">
			 <input type="button" value="Go"  class="btn btn-primary" style="margin-top:21px;" onclick="misSubmit()">
			
		</div>	
		
		<div class="col-lg-1 col-md-1">
			<input type="button" class="btn btn-primary" style="margin-top:21px;" value="Print" onclick="printpage()">
		</div>
		
		</s:form>
		
		<s:if test="hdnsendmail==1">
			<div class="col-lg-1 col-md-1" id = "previewInvoiceRpt" style="display: none;">
				
				<input style="margin-top: 21px;" type="button" value="Send PDF in Mail" onclick="return openSendMailInvoiceRptPopup();" class="btn btn-primary">
			</div>
		</s:if>
		</div>
		
	
		
		
		


<br>

<table widht="100%">
	<tr>
		<td>Report 1 <span style="font-weight:bold;font-size:16px;">Monthly Activity Data</span></td>
	</tr>
</table>


			<table class="my-table" style="width: 100%">
				<thead>
					<tr>
						<%if(session.getAttribute("headerList")!=null){
							ArrayList<String>list = (ArrayList<String>)session.getAttribute("headerList");
						%>
							<%for(String s : list) { if(s.equals("If Other has been selected for Sub CCD, please give details")){s="Sub CCd Details";}%>
								
								<th><%=s %></th>
							<%} %>
						<%} %>
					</tr>
				</thead>
				<tbody>
					<s:if test="mislist.size!=0">
						<s:iterator value="mislist" status="rowstatus">
							<tr>
								<td><s:property value="clinicName" /></td>
								<s:if test="invoiceno!=0">
									<td>0000<s:property value="invoiceno" /></td>
								</s:if>
								<s:else>
									<td><s:property value="invoiceno" /></td>
								</s:else>
								<td><s:property value="location" /></td>
								<td><s:property value="nhsNumber" /></td>
								<td><s:property value="age" /></td>
								<td><s:property value="gender" /></td>
								<td><s:property value="practiceCode " /></td>
								<td><s:property value="CommissionerCCG " /></td>
								<td><s:property value="subCCD " /></td>
								<td><s:property value="otherSubCCD " /></td>
								<td><s:property value="sourceofRefferal" /></td>
								<td><s:property value="refferalRecievedDate" /></td>
								<td><s:property value="urgency" /></td>
								<td><s:property value="assesmentDate" /></td>
								<td><s:property value="reftoAssesmentDays" /></td>
								<td><s:property value="practitonerType" /></td>
								<td><s:property value="apptDate" /></td>
								<td><s:property value="apptTime" /></td>
								<td><s:property value="practitonerSurname" /></td>
								<td><s:property value="TreatmentOutcome" /></td>
								<td><s:property value="dischargeReason" /></td>
								<td><s:property value="apptName" /></td>
								<td><s:property value="apptCharge" /></td>
							</tr>

						</s:iterator>
					</s:if>
				</tbody>
				<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i> There is no record
						found!!
					</h3>

				</s:else> 

			</table>

<br>

<table widht="100%">
	<tr>
		<td>Report 2 <span style="font-weight:bold;font-size:16px;">AQP Physiotherapy Quality Indicators - Monthly KPI Report</span></td>
	</tr>
</table>

<table width="60%" class="my-table">
	<col width="50%">
	<col width="10%">
	
	<tr>	
		<th>Key Performance Indicator</th>
		<th>Calculate Data for Given Month for Following</th>
		
	</tr>
	<tr>
		<td>Number of Urgent patients seen within 3 working days of receipt of referral</td>
		<td style="text-align: center;"><s:property value="count3"/></td>
	</tr>
	<tr>
		<td>Threshold 100%</td>
		<td style="text-align: center;"><s:property value="per3"/>%</td>
	</tr>
	<tr>
		<td>Routine patients seen within 28 days of receipt of referral</td>
		<td style="text-align: center;"><s:property value="count28"/></td>
	</tr>
	<tr>
		<td>Threshold 100%</td>
		<td style="text-align: center;"><s:property value="per28"/>%</td>
	</tr>
	<tr>
		<td>Number of DNAs</td>
		<td style="text-align: center;"><s:property value="dnaCount"/></td>
	</tr>
	<tr>
		<td>Threshold less than 6% of total referrals</td>
		<td style="text-align: center;"><s:property value="perless6"/></td>
	</tr>
	<tr>
		<td>Discharge letters to be sent to the referrer within 5 working days</td>
		<td style="text-align: center;"><s:property value="dschargeCount"/></td>
	</tr>
	<tr>
		<td>Threshold 100%</td>
		<td style="text-align: center;"><s:property value="per5"/>%</td>
	</tr>
	<tr>
		<td>Patients to be offered information leaflets prior to and during their treatment</td>
		<td style="text-align: center;"><s:property value="letterCount"/></td>
	</tr>
	<tr>
		<td>Threshold 100%</td>
		<td style="text-align: center;"><s:property value="letterPer"/>%</td>
	</tr>
</table>

<br>
<table widht="100%">
	<tr>
		<td>Report 3 <span style="font-weight:bold;font-size:16px;"> Monthly Activity Summary</span></td>
	</tr>
</table>

<table width="60%" class="my-table">
	<col width="30%">
	<col width="40%">
	
	<tr>	
		<th>Key Performance Indicator</th>
		<th>Calculate Data for Given Month for Following</th>
		
	</tr>
	<tr>
		<td>Number of Appointments for the month by:</td>
		<td>
			<table width="100%">
				<tr>
					<td>New Referrals (<s:property value="newReferalCount"/>)</td>
				</tr>
				<tr>
					<td>New Referrals Triaged (<s:property value="newRefralTriggered"/>)</td>
				</tr>
				<tr>
					<td>New Referrals Not Triaged within 3 working days (<s:property value="notrigerdCount3"/>)</td>
				</tr>
				<tr>
					<td>New Referrals Triaged and sent back to GP as inappropriate (N/A)</td>
				</tr>
				<tr>
					<td>Initial Assessments (<s:property value="countInitialAssessment"/>)</td>
				</tr>
				<tr>
					<td>Follow up attendances (<s:property value="countFollowupAttendence"/>)</td>
				</tr>
				<tr>
					<td>Attended (N/A)</td>
				</tr>
				<tr>
					<td>DNA (<s:property value="dnaCount"/>)</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>Number of patients for the month by:</td>
		<td>
			<table width="100%">
				<tr>
					<td>Requiring an onward referral to secondary care (N/A)</td>
				</tr>
				<tr>
					<td>Routine (<s:property value="routineCount"/>)</td>
				</tr>
				<tr>
					<td>Urgent (<s:property value="misUrgentCount"/>)</td>
				</tr>
				<tr>
					<td>Visited at home (N/A)</td>
				</tr>
				<tr>
					<td>Discharged from the Service (<s:property value="dischargeCount"/>)</td>
				</tr>
				
			</table>
		</td>
	</tr>
	<tr>
		<td>Number of Complaints of each type:</td>
		<td>
			<table width="100%">
				<tr>
					<td>Type of Complaint (N/A)</td>
				</tr>
				<tr>
					<td>No of Complaints (N/A)</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>Number of Cancellations for the month by:</td>
		<td>
			<table width="100%">
				<tr>
					<td>Patient (N/A)</td>
				</tr>
				<tr>
					<td>Provider (<s:property value="countCanceldApmt"/>)</td>
				</tr>
			</table>
		</td>
	</tr>
	
	
</table>

<br>
<table widht="100%">
	<tr>
		<td>Report 4 <span style="font-weight:bold;font-size:16px;">Monthly Follow Up Report: Patients having more than 6 follow ups in a month</span></td>
	</tr>
</table>
<table width="60%" class="my-table">
	<col width="35%">
	<col width="15%">
	<col width="10%">
	
	<tr>	
		<th>Patient Name</th>
		<th>NHS Number</th>
		<th>Number of Follow ups> 6</th>
		
	</tr>
	<s:if test="greaterThanSixFollowupsList.size>0">
		<s:iterator value="greaterThanSixFollowupsList">
			<tr>
				<td><s:property value="clientName"/></td>
				<td><s:property value="nhsNumber"/></td>
				<td style="text-align: center;"><s:property value="usedsession"/></td>
			<tr>
		</s:iterator>
	</s:if>
	<s:else>
		<tr>
			<td colspan="3">No Record Found!!</td>
		</tr>
	</s:else>
	
	
</table>

<!-- Modal Email-->
<div class="modal fade" id="sendEmailInvoiceRptPopup" tabindex="-1" role="dialog"
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
							<s:textfield theme="simple" id = "invoiceReportEmail" name = "email" cssClass="form-control showToolTip"
								placeholder="Enter email address of receiver" title="Enter Email Id" data-toggle="tooltip" />
						</div>
						<div class="form-group">
							<label>Cc:</label>
							<s:textfield theme="simple" id = "invoiceReportccEmail" name = "ccEmail"	cssClass="form-control showToolTip" title="Enter Cc"
								data-toggle="tooltip" placeholder="Enter Cc" />
						</div>
						<div class="form-group">
							<label>Subject:</label> <input type="text" name= "subject" id = "invoiceReportSubject" class="form-control showToolTip"
								data-toggle="tooltip" title="Enter Subject" placeholder="Enter Subject">
						</div>
						<div class="form-group">
							<label>Body:</label>
							<textarea class="form-control showToolTip" data-toggle="tooltip" rows="20" cols="60"
								title="Enter Body" name="emailBody"  id="invoiceReportEmailBody" ></textarea>
						</div>
						<div class="form-group">
							<s:property value="filename"/><span style="margin-left:3px;"><a href="invoice/<s:property value="filename"/>" target="blank"><i
								class="fa fa-file-pdf-o fa-2x text-danger" title="Attached PDF"></i></a></span> 
						</div>
						<div class="form-group" id="pdfInvoiceReportMailId">
							
						</div>
						<div class="form-group">
						<button type="button" class="btn btn-primary"  onclick="sendInvoiceReportMail();">Send</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>		