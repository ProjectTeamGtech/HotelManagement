<%@taglib uri="/struts-tags" prefix="s"%>

<script src="common/js/date.js" type="text/javascript"></script>
<script src="common/js/jquery/jquery.datePicker.js"
	type="text/javascript"></script>
<script src="common/js/CalenderPicture.js" type="text/javascript"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<link href="common/css/datePicker1.css" rel="stylesheet" />
<script type="text/javascript"
	src="diarymanagement/js/appointmentdiaryReport.js"></script>


<div class="row">
	<div class="col-lg-12">
		<h4 class="text-center text-info">Appointment Diary User Report</h4>
	</div>
</div>



<div class="row">
	<div class="col-lg-12">
		<div class="table-responsive">
			<table id="results" class="table table-hover table-bordered table-striped table-condensed">
				<thead>
				<tr>
					<th class="text-center">Date</th>
					<th class="text-center">Time</th>
					<th class="text-center">Duration</th>
					<th class="text-center">Diary User</th>
					<th class="text-center">Patient Name</th>
					<th class="text-center">New</th>
					<th class="text-center">Exist</th>
					<th class="text-center">Attend</th>
					<th class="text-center">DNA</th>
					<th class="text-center">Details</th>
				</tr>
				</thead>
				
				<tbody>
				<s:if test="apmtDiaryReportList.size!=0">
					<s:iterator value="apmtDiaryReportList" status="rowstatus">
						<tr>
							<td><s:property value="date" /></td>
							<td><s:property value="startTime" /></td>
							<td><s:property value="duration" /></td>
							<td><s:property value="diaryUser" /></td>
							<td><s:property value="clientName" /></td>
							<td><s:property value="newClient" /></td>
							<td><s:property value="exist" /></td>
							<td><s:property value="attend" /></td>
							<td><s:property value="dna" /></td>
							<td><s:property value="notes" /></td>
						</tr>

					</s:iterator>
				</s:if>
				</tbody>
				<s:else>
						<h3 class="text-center"><i class="fa fa-times text-danger"></i> There is no Record found!!</h3>
					</s:else>

			</table>
		</div>
	</div>
</div>










