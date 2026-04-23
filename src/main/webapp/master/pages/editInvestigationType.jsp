<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script>
bkLib.onDomLoaded(function() {
	new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('invstremark');
});

window.onload = function() {
	var rem=document.getElementById('hiddenremark').value;
	nicEditors.findEditor( "invstremark" ).setContent(rem); 
};

function submitfr(){
	var extrarm=nicEditors.findEditor( "invstremark" ).getContent(); 
	document.getElementById('hiddenremark').value=extrarm;
	return true;
}
</script>


<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Edit Investigation Type</h4>
								</div>
						</div>	
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<s:form action="updateInvestigationMaster" id="master_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
			<div class="panel-body">
			<s:hidden id="id" name="id" />
			
			   <label>Department</label> 
			  <!-- <s:select list="allInvsUserList" listKey="id" listValue="fullname" name="department" placeholder="Select Department" title="Select Department" headerKey="0" headerValue="Select Department"
											cssClass="form-control showToolTip filedname"></s:select> --> 
				
				<s:select list="alljobtitlelist" listKey="id" listValue="name" name="department" placeholder="Select Department" title="Select Department" headerKey="0" headerValue="Select Department"
											cssClass="form-control showToolTip filedname"></s:select>
				<label>Name</label><label class="text-danger">*</label>
				<s:textfield id="name" name="name"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Name" placeholder="Enter Name" />
			
			
				<label>Group Name</label><label class="text-danger">*</label>
				<s:textfield id="group" name="group"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter group name" placeholder="Enter group name" />
			
			
				<label>Report Type</label><label class="text-danger">*</label>
				
				<%-- <s:select list="reporttype_list" name="reporttype_list" id="reporttype_list" listKey="id" listValue="name" cssClass="form-control"></s:select>   --%>	
				
					<s:select name="report_type" id="reporttype_list" 
					list="#{'Numerical':'Numerical','Writeup':'Writeup','Text':'Text','Hybreed':'HyBreed'}"
					cssClass="form-control" onchange=""></s:select>
					
			   <label>Charge </label><label class="text-danger">*</label>
			    
			   <s:textfield id="charge" name="charge"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Charge Value" placeholder="Enter charge Value" />		
				
				<label>Section</label>
				<s:select name="sectionid" id="sectionid" 
					list="sectionlist"
					cssClass="form-control" listKey="id" listValue="name" headerKey="0" headerValue="Select Section" ></s:select>
					<label>Ward Name</label>
					<s:select list="wardlist" listKey="id" listValue="wardname" name="wardid" id="wardid" title="Select Ward" headerKey="0" headerValue="Select Ward"
											cssClass="form-control chosen-select"   > </s:select>
				<label>Round Charge </label><label class="text-danger">*</label>
			   	<s:checkbox name="roundcharge" />	
			   	<br>
			   	<br>
			   	<label>Add Default Remark</label>
							 <s:textarea cssClass="form-control" rows="3" maxlength="" name="invstremark" id="invstremark"></s:textarea>
							<input type="hidden" name='hiddenremark' id='hiddenremark' value='<s:property value='defaultremark'/>'>	
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary"  value="Update" onclick="return submitfr()"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="backInvestigationMaster" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
