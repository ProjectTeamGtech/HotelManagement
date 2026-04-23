<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@page import="java.util.Date"%>
<script type="text/javascript" src="medical/records/js/medical.js"></script>

<script>
	

$(document).ready(function() {

	$("#date").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});

	
});
</script>
<div class="row">
	<div class="col-lg-13">
		<ol class="breadcrumb">
			<li><a href="#">Medical Records</a></li>
			<li><a href="/APM/MedicalRecord">Records</a></li>
			<li class="active">Add New Records </li>
		</ol>
	</div>
</div>

<s:form action="saveMedicalRecord" id="master_form" theme="simple" method="post" enctype="multipart/form-data">
<div class="col-lg-3 col-md-3"></div>
<div class="col-lg-6 col-md-6">
<div class="panel panel-primary">
	<div class="panel-body">
		
		<div class="row">
			<div class="col-lg-3 col-md-3">
				<label>Client</label><label class="text-danger">*</label>
			</div>
			<div class="col-lg-8 col-md-8">
					<s:textfield name="clientName" id="clientName"  readonly="true"
				cssClass="form-control" onclick="showPopUp()" data-toggle="modal" data-target="#clientSearch" placeholder = "Click here to select Client"/>
				<s:hidden name = "clientId" id="clientId"></s:hidden>
			</div>
		</div>	
		</br>	
		<div class="row">
			<div class="col-lg-3 col-md-3">
				<label>Date</label><label class="text-danger">*</label>
			</div>
			<div class="col-lg-8 col-md-8">
					<s:textfield id="date" name="date" 
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Date" placeholder="" />
			</div>
		</div>
		</br>
		<div class="row">
			<div class="col-lg-3 col-md-3">
				<label>Subject</label><label class="text-danger">*</label>
			</div>
			<div class="col-lg-8 col-md-8">
					<s:textfield id="subject" name="subject"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Subject" placeholder="Enter Subject" />
			</div>
		</div>
		</br>
		<div class="row">
			<div class="col-lg-3 col-md-3">
				<label>Description</label>
			</div>
			<div class="col-lg-8 col-md-8">
					<s:textarea id="description" name="description"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Description" placeholder="Enter Description" />
			</div>
		</div>
		</br>
		<div class="row">
			<div class="col-lg-3 col-md-3">
				<label>Upload File</label>
			</div>
			<div class="col-lg-8 col-md-8">
				
						<s:file name="userImage" label="User Image" />

			</div>
		</div>
	
	</div>
</div>
<div class="row">
	<s:submit value="Save" cssClass="btn btn-primary"></s:submit>
	<s:reset cssClass="btn btn-primary"></s:reset>
</div>
</div>


<div class="col-lg-3 col-md-3"></div>


</s:form>	

<!-- Modal -->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/diarymanagement/pages/allPatientsList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>