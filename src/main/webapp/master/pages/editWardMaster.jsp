<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>



<script>

	function getincrement() {
	
		var proc = document.getElementsByName('proc');
		var procval;
		for(var i = 0; i < proc.length; i++){
	    	if(proc[i].checked){
	        	procval = proc[i].value;
	    	}
		}
		
		document.getElementById("increment").value=procval;
		return true;
	}

</script>




<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Add Ward</h4>
								</div>
							</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<s:form action="updatewardmasterBed" id="master_form" onsubmit="return getincrement()" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
			<s:hidden id="increment" name="increment" />
				<label>Wardname</label><label class="text-danger">*</label>
				<s:textfield id="wardname" name="wardname"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Wardname" placeholder="Enter Wardname" onkeyup="initialCap(this)"/>
					
				<label>Procedure</label>	
				
				Plus 
				 <s:if test="increment==1">
					<input type="radio" checked="checked" name="proc" value="1" /> 
				 </s:if>
				 <s:else>
				  <input type="radio" name="proc" value="1" />
				 </s:else>
				Minus 
				
				<s:if test="increment==2">
					<input type="radio" name="proc" checked="checked" value="2" />
			    </s:if>		
				<s:else>
				 	<input type="radio" name="proc" value="2" />
				</s:else>
				<s:textfield name="procedure" placeholder="Enter Procedure" cssClass="form-control"></s:textfield>					
				
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
			<a href="wardlistmasterBed" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
