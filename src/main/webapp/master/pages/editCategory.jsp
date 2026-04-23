<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/vaidateWarehouse.js"></script>


<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Update Main Category</h4>
								</div>
							</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<s:form action="updatecategoryProductinventory" id="master_form" theme="simple" onsubmit="return validteWarehouse()">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
			<label for="exampleInputPassword1">Warehouse List</label><label class="text-danger">*</label><br>
			 <s:iterator value="warehouseListNew">
				         <s:if test="checked==true">
				             <input class="case" type="checkbox" value="<s:property value="warehouse_id"/>" checked="checked"/> <s:property value="warehouse_name"/> <br>
				         </s:if>
				         <s:else>
				            <input class="case" type="checkbox" value="<s:property value="warehouse_id"/>"/> <s:property value="warehouse_name"/> <br>
				         </s:else>
				     </s:iterator>
				     <s:hidden name="warehouseids" id="warehouseids"></s:hidden>
				<label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name" onkeyup="initialCap(this)"/>
					<label>Description</label><label class="text-danger">*</label>
				<s:textfield id="description" name="description"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Description" placeholder="Enter Description" onkeyup="initialCap(this)"/>
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
			<a href="categoriesProductinventory" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
