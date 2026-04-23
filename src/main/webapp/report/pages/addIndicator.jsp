<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<SCRIPT type="text/javascript" src="report/js/report.js"></SCRIPT>


<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Indicator Details</h4>
								</div>
							</div>

<s:form action="saveIndicatorKPI" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<div class="form-group">
				 <label>Select KPI Area</label><label class="text-danger">*</label>
			    <s:select list="arealist" name="areaid"  id="areaid" headerKey="0" headerValue="Select Area" listKey="id" listValue="name" cssClass="form-control chosen-select"></s:select>			   
			</div>
			<div class="form-group">
				<label>Indicator</label><label class="text-danger">*</label>
				<s:textfield id="indicator" name="indicator" cssClass="showToolTip form-control" data-toggle="tooltip" title="Enter Indicator" placeholder="Enter Indicator"/>
			</div>
			<div class="form-group">
				<label>Formula Description</label><label class="text-danger">*</label>
				<s:textfield id="formula_desc" name="formula_desc" cssClass="showToolTip form-control" data-toggle="tooltip" title="Enter Formula Description" placeholder="Enter Formula Description"/>
				<small class="text-danger">Note For Formula Description:(Score Achieved / Maximum score Possible )</small>
			</div>
			<div class="form-group">
				<label>Formula In Expression</label><label class="text-danger">*</label>
				<s:textfield id="formula" name="formula" cssClass="showToolTip form-control" data-toggle="tooltip" title="Enter Formula" placeholder="Enter Formula"/>
				<small class="text-danger">Note For Formula:(input1 / input2 )</small>
			</div>
			<div class="form-group">
				<label>Number of Input</label><label class="text-danger">*</label>
				<s:textfield id="no_of_input" name="no_of_input" cssClass="showToolTip form-control" data-toggle="tooltip" title="Enter Number of Input" placeholder="Enter Number of Input"/>
			</div>
			
				
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return saveIndicator()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="kpidashboardKPI" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
