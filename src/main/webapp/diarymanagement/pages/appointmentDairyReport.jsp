<%@taglib uri="/struts-tags" prefix="s"%>
<link href="diarymanagement/css/popupstyle.css" rel="stylesheet"
	type="text/css" />
<link href="diarymanagement/css/subModal.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="diarymanagement/js/common.js"></script>
<script type="text/javascript" src="diarymanagement/js/subModal.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/appointmentdiaryReport.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/apmtDiaryReport.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>


<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Reports</a></li>
			<li class="active">Appointment Diary User Report</li>
		</ol>
	</div>
</div>


<s:form action="showApmtDiaryReport" id="apmtDiaryReport_frm">
	<div class="row">
		<div class="col-lg-3 col-md-6">
			<label>Dairy User:</label>
			<s:if test="%{#diaryUserList != 'null'}">
				<s:select id="diaryUser" name="diaryUser" list="diaryUserList"
					listKey="id" cssClass="form-control" listValue="diaryUser"
					headerKey="0" theme="simple" headerValue="Select User" />
				<label id="diaryUserError" class="text-danger"></label>
			</s:if>
		</div>
		<div class="hidden-lg visible-sm-block visible-xs-block">
			<br />
		</div>
		<div class="col-lg-3 col-md-6">
			<label>From Date :</label>
			<s:textfield name="fromDate" id="fromDate" cssClass="form-control"
				theme="simple"></s:textfield>
			<label id="fromDateError" class="text-danger"></label>
		</div>
		<div
			class="hidden-lg visible-md-block visible-sm-block visible-xs-block">
			<br />
		</div>
		<div class="col-lg-3 col-md-6">
			<label>To Date :</label>
			<s:textfield name="toDate" id="toDate" cssClass="form-control"
				theme="simple"></s:textfield>
			<label id="toDateError" class="text-danger"></label>
		</div>
		<div class="hidden-lg visible-sm-block visible-xs-block">
			<br />
		</div>
		<div class="col-lg-3 col-md-6">
			<label style="font-weight: normal">.</label><br />
			<s:submit value="Go" theme="simple" cssclass="btn btn-primary"
				onclick="return validEntry()"></s:submit>

			<a onclick="return showPreview()" class="btn btn-primary" data-toggle="modal" data-target="#reportPreviewDiv2"><i
				class="fa fa-eye"></i> Print Preview</a>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">

			<div class="table-responsive">

				<table
					class="table table-bordered table-hover table-striped table-condensed">
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
				</table>
				<s:else>
					<h3 class="text-center">
						<i class="fa fa-times text-danger"></i> There Record found!!
					</h3>
				</s:else>

			</div>

		</div>
	</div>

</s:form>

<s:if test="apmtDiaryReportList!=null">
	<s:form action="showApmtDiaryReport" name="paginationForm"
		id="paginationForm" theme="simple">
		<s:hidden id="diaryUser1" name="diaryUser1"></s:hidden>
		<s:hidden id="fromDate1" name="fromDate1"></s:hidden>
		<s:hidden id="toDate1" name="toDate1"></s:hidden>
		<div class="col-lg-12 col-xs-12" style="padding:0px;margin-top:15px;">		
			<div class="col-lg-4 col-md-4" style="padding:0px">
				Total:<label class="text-info"><s:property value="totalRecords" /></label>
			</div>
			<%@ include file="/common/pages/pagination.jsp"%>
		</div>

	</s:form>

</s:if>



<div class="modal fade" id="reportPreviewDiv2" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Preview</h4>
			</div>
			<div class="modal-body">
				<%@ include file="/diarymanagement/pages/reportPreview.jsp"%>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>






