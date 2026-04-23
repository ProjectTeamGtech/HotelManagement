<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="registration/js/checkMailSend.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li class="active">Edit DNA Charge </li>
		</ol>
	</div>
</div>


<div class="panel panel-primary">
<div class="panel-body">
<s:form action="updateDNAChargeClinicRegistration" theme="simple" method="post" enctype="multipart/form-data">

<div class="row">
	<div class="col-lg-12">	
		<div class="form-group">			
		<div class="row">
		<div class="col-lg-3"></div>	
		<div class="col-lg-3">
			<label>Set DNA Charges % </label>
			</div>
			<div class="col-lg-2">
				<s:textfield name="dnaCharges" id="dnaCharges" cssClass="form-control showToolTip" data-toggle = "tooltip" placeholder = "Enter DNA Charge %" title="Enter DNA Charge %"/>
			</div>
		</div>
		<s:hidden name="id" id="id"/>
		<br/>
		
		<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Update" onclick="return saveValidationDNA()"/>						
			<s:reset cssClass="btn btn-primary" />		
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</div>
</div>
</div>
</s:form>

</div>
</div>