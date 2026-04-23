<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/statecity.js"></script>

<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add NABH Sub Category</h4>
								</div>
							</div>

<s:form action="saveNABHSubCatagoryMaster" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			    <label>Select Category</label><label class="text-danger">*</label>
			    <s:select list="nabhcatagorylist" headerKey="0" headerValue="Seletc Category" id="catagoryid" name="catagoryid" listKey="id" listValue="name"  cssClass="form-control"></s:select>			   
				
				<label>Sub Category</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter sub category" placeholder="Enter sub category"/>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save" onclick="return savenabhsubcat()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="NABHSubCatagoryMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
