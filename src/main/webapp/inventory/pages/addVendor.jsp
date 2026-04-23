<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<script>

   
   $(document).ready(function (){
   
         $('#selecctall').click(function(event) {  //on click 
        if(this.checked) { // check select status
            $('.case').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"               
            });
        }else{
            $('.case').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
            });         
        }
      });   
   });
   
   var totalbrands=0;
   
   function dosubmit() {
     
         $('.case').each(function() { //loop through each checkbox
		  if(this.checked==true){
			  totalbrands = totalbrands + ',' + this.value;   
		  }
        }); 
	
	  document.getElementById('brand_name').value = totalbrands;
	  document.getElementById('master_form').submit();   
      
   
   }
   
   

</script>



<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="Inventory">All Vendor List</a></li>
			<li class="active">Add Vendor</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>




<s:form action="savevendorInventory" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
				<label>Vendor</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name" onkeyup="initialCap(this)"/>
				<label>Address</label><label class="text-danger">*</label>	
			   <s:textarea id="address" name="address" cssClass="form-control" title="Enter Address" placeholder="Enter Address" rows="3" cols="20">
			   </s:textarea>
			   <label>Email</label><label class="text-danger">*</label>
			   <s:textfield id="email" name="email" cssClass="form-control" title="Enter Email" placeholder="Enter Email"></s:textfield>
			   <label>Select Brand Names</label><label class="text-danger" cssClass="form-control">*</label>
			   <div id="scroll">
			<s:checkbox id="selecctall" name="selecctall" type="checkbox"  /> Select All <br>
			<s:iterator value="brandnameList">
				<input class="case" type="checkbox"  id="ch<s:property value="id"/>" name="ch<s:property value="id"/>" value="<s:property value="id"/>">
				<s:property value="name"/><br>
			</s:iterator>
			</div>
			   <input type="hidden" name="brand_name" id="brand_name">
			   <label>Enter Mobile Primary</label><label class="text-danger" cssClass="form-control">*</label>
			   <s:textfield id="mobile_pri" name="mobile_pri" cssClass="form-control" title="Enter Mobile Primary" placeholder="Enter Mobile Primary"></s:textfield>
			   
			   <label>Enter Mobile Second</label><label class="text-danger" cssClass="form-control">*</label>
			   <s:textfield id="mobile_sec" name="mobile_sec" cssClass="form-control" title="Enter Mobile 2" placeholder="Enter Mobile 2"></s:textfield>
			   
			   <label>Enter Phone1</label><label class="text-danger" cssClass="form-control">*</label>
			   <s:textfield id="phone1" name="phone1" cssClass="form-control" title="Enter Phone1" placeholder="Enter Phone1"></s:textfield>
			   
			   <label>Enter Phone2</label><label class="text-danger" cssClass="form-control">*</label>
			   <s:textfield id="phone2" name="phone2" cssClass="form-control" title="Enter Phone2" placeholder="Enter Phone2"></s:textfield>
			   
			   <label>Enter Minimum Delivery Time</label><label class="text-danger" cssClass="form-control">*</label>
			   <s:textfield id="min_delivery_time" name="min_delivery_time" cssClass="form-control" title="Enter Minimum Delivery Time" placeholder="Enter Minimum Delivery Time"></s:textfield>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>




	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<input type="button" class="btn btn-primary" value="Save" onclick="dosubmit()" />
			<s:reset cssClass="btn btn-primary" />
			<a href="Inventory" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>
