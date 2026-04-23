<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<script type="text/javascript" src="master/js/NewChargetype.js"></script>

<script>
document.onreadystatechange = function() {
	if (document.readyState !== "complete") {
		$('#dashboardloaderPopup').modal('show');
	} else {
		$('#dashboardloaderPopup').modal('hide');
	}
	};

    	</script>
 <%
									LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		   						%>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="/APM/Occupation">All New ChargeTypeList</a></li>
			<li class="active">Add New ChargeType</li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>
<s:form action="saveNewChargeType" id="master_form" theme="simple">

<%if(loginInfo.isLmh()){ %>
	<input type="hidden" id="isFromLmh" value="1">
<%}else{ %>
	<input type="hidden" id="isFromLmh" value="0">
<%} %>

	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-body">
					<label>Name</label><label class="text-danger">*</label>
					<s:textfield id="name" name="name"
						cssClass="showToolTip form-control" data-toggle="tooltip"
						title="Enter Name" placeholder="Enter Name"
						onkeyup="initialCap(this)" />
						
						<label>Procedure</label><label class="text-danger"></label>
					<s:checkbox name="procedure" id="procedure"></s:checkbox>
					<div>
						
						
					<label>Department</label><label class="text-danger">*</label>
					<%-- <s:textfield id="description" name="description"
						cssClass="showToolTip form-control" data-toggle="tooltip"
						title="Enter Description" placeholder="Enter Description"
						onkeyup="initialCap(this)" />  --%>
						
						<s:select list="disciplineList" listKey="id" headerKey="0" headerValue="Select department" listValue="discipline" name="description" id='dept' title="select department"
							onchange="setsitting(this.value)" cssClass="form-control showToolTip chosen-select"  > </s:select>
										
					     			
                    <div class="form-group" id="proddiv" >
        				 <label>Select Sitting</label>
        				 <select name="sitting_id" id="sitting_id" class="form-control chosen-select">
						 <option value="0">Select</option>
						</select>
        			</div>
        	   
					<%-- <label>Procedure</label><label class="text-danger"></label>
					<s:checkbox name="procedure" id="procedure"></s:checkbox>
					<div> --%>
					<label>Is Consultant Mandatory</label><label class="text-danger">*</label>
					<s:checkbox name="consultant_compulsay" id="consultant_compulsay"></s:checkbox>
					</div>
					<%-- <br>
					<label>Ward</label><label class="text-danger"></label>
					<s:select list="wardList" id="wardnameid" name="wardid"
						listKey="id" listValue="wardname" headerKey="0"
						headerValue="Select Ward" cssClass="form-control">
					</s:select> --%>
				</div>
			</div>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" onclick="return validatecharges()" value="Save" />
			<s:reset cssClass="btn btn-primary" />
			<a href="backNewChargeType" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	<s:token></s:token>
</s:form>

<!-- Loader -->
	<div class="modal fade" style="background: rgba(255, 255, 255, 0.93);" id="dashboardloaderPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif" class="img-responsive middlelogo" style="margin-left:auto;margin-right:auto;"></img>
					
				</div>
			</div>
		</div>
	</div>
	<!--End loader  -->			
 <script src="common/chosen_v1.1.0/chosen.jquery.js" type="text/javascript"></script>
  <script src="common/chosen_v1.1.0/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>				