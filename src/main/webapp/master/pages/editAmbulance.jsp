<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/ambulance.js"></script>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Edit Ambulance Details</h4>
								</div>
							</div>

<s:form action="updateAmbulance" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				<s:hidden name="id"></s:hidden>
								
			  <label>Vehicle No.</label><label class="text-danger">*</label>
				<s:textfield id="vehicleno" name="vehicleno"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Vehicle No" placeholder="Enter Vehicle No"/><br><br>
					
			    <label>Select Ambulance Type</label><label class="text-danger">*</label>
			    <s:select list="ambulancetypelist" id="ambulancetype" headerKey="" headerValue="Select Ambulance Type" name="ambulancetype" listKey="id" listValue="ambulancetype" cssClass="form-control"/>			   
				<br><br>
				<label>Driver Name</label><label class="text-danger">*</label>
			   <s:select list="driverlist" headerKey="0" headerValue="Select Driver" id="driverfname" name="driverfname" listKey="id" listValue="driverfname"  cssClass="form-control"/>			   
				
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="update" onclick="return saveambulance()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="Ambulance" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
