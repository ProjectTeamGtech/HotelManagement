<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>
<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Edit City Details</h4>
								</div>
							</div>

<s:form action="updateCityMaster" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				<s:hidden name="id"></s:hidden>
			    <label>Select State</label><label class="text-danger">*</label>
			    <s:select list="statelist" id="stateid" headerKey="0" headerValue="Select state" name="stateid" listKey="id" listValue="name" cssClass="form-control"></s:select>			   
				<label>City</label><label class="text-danger">*</label>
				<s:textfield id="city" name="city"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter City" placeholder="Enter City"/>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="update" onclick="return savecity()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="CityMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
