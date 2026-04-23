<%@taglib uri="/struts-tags" prefix="s"%>

<link rel="stylesheet" href="inventory/js/jquery.multiselect.css">
    <link rel="stylesheet" href="inventory/js/app.css">

<script type="text/javascript">

  
function getsubcategories(id) {

   document.getElementById("xyzcategory").value=id;
   document.myformsub.submit();      
  
}

</script>









<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>
<s:form action="savebrandProductinventory" id="master_form" theme="simple" onsubmit="return isValid()">

 <s:hidden name="vendor" id="allvendor"></s:hidden>

<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
	<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Manufacture Name</h4>
								</div>
							</div>
		<div class="panel panel-primary">
			<div class="panel-body">
				<div class="form-group">
					<label>Select Category</label><label class="text-danger">*</label>
			    <s:select list="categoryList" listKey="id" listValue="name" name="category_id" id="category_id" onchange="getsubcategories(this.value)" cssClass="form-control"></s:select>
				</div>
				
				<div class="form-group">
				<label>Manufacture Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Manufacture Name"/>
					
				</div>
				
				<div class="form-group">
				<label>Select Supplier</label><label class="text-danger">*</label>
					
						<select name="users_list" id="users_list" multiple="multiple" size="15">
			              <optgroup label="Select All">
			                <s:iterator value="vendorList">	
			                	<option value="<s:property value="id"/>"> <s:property value="name"/> </option>
			                </s:iterator>
			              </optgroup>
			             
			            </select>
					
				</div>
			    <!--
			    <label>Select Sub Category</label><label class="text-danger">*</label>
			    <s:select list="subcategoryList" listKey="id" listValue="name" name="subcategory_id" cssClass="form-control"></s:select>
				-->
			    <!--<div id="scroll">
					 
					 <input type="checkbox" onclick="selectAll(this)" /> Select All <bR> 
					 <s:iterator value="">
					    
					      <input type="checkbox" class="case" value="<s:property value="id"/>" /> <s:property value="name"/>   <br> 
					 </s:iterator>			     
				    
			    </div>   		
			   -->
			   <div class="form-group hidden">
			   		<label>Description</label>
				<s:textfield id="description" name="description"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Description" placeholder="Enter Description"/>
			   </div>
			   
			   <div class="form-group">
			   	<s:submit cssClass="btn btn-primary" value="Save" />
			<s:reset cssClass="btn btn-primary" />
			<a href="listsubcategoriesProductinventory" class="btn btn-primary">Back</a>
			   
			   </div>
			   
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>

	
	<s:token></s:token>
</s:form>

<form action="getsubcategoryProductinventory" name="myformsub"  method="post">
 
    <input type="hidden" name="category" id="xyzcategory">
</form>
    <script src="inventory/js/app.js"></script>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/addbrand.js"></script>
<script src="inventory/js/jquery.multiselect.js"></script>

