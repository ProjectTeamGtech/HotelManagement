<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="Inventory">All Inventory Vendor List</a></li>
			<li class="active">Update Inventory Vendor Name</li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<s:form action="updatevendorInventory" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
				<label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name" />
				<label>Address</label><label class="text-danger"></label>	
			     <s:textarea id="address" name="address" cssClass="form-control" title="Enter Address" placeholder="Enter Address" rows="3" cols="20">
			   </s:textarea>
			   <label>Email</label><label class="text-danger"></label>
			   <s:textfield id="email" name="email" cssClass="form-control" title="Enter Email" placeholder="Enter Email"></s:textfield>
			   <label>Brand Name</label><label class="text-danger"></label>
			   <s:textfield id="brand_name" name="brand_name" cssClass="form-control" title="Enter Brand Name" placeholder="Enter Brand Name"></s:textfield>
			   <label>Mobile (Primary)</label><label class="text-danger"></label>
			   <s:textfield id="mobile_pri" name="mobile_pri" cssClass="form-control" title="Enter Mobile Primary" placeholder="Enter Mobile Primary"></s:textfield>
			   <label>Mobile 2</label><label class="text-danger"></label>
			   <s:textfield id="mobile_sec" name="mobile_sec" cssClass="form-control" title="Enter Mobile 2" placeholder="Enter Mobile 2"></s:textfield>
			   <label>Phone 1</label><label class="text-danger"></label>
			   <s:textfield id="phone1" name="phone1" cssClass="form-control" title="Enter Phone1" placeholder="Enter Phone1"></s:textfield>
			   <label>Phone 2</label><label class="text-danger"></label>
			   <s:textfield id="phone2" name="phone2" cssClass="form-control" title="Enter Phone2" placeholder="Enter Phone2"></s:textfield>
			   <label>Minimum Delivery Time</label><label class="text-danger"></label>
			   <s:textfield id="min_delivery_time" name="min_delivery_time" cssClass="form-control" title="Enter Minimum Delivery Time" placeholder="Enter Minimum Delivery Time"></s:textfield>		
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary"  value="Update"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="Inventory" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
