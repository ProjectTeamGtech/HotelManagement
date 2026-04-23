<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>
<div class="row">
	<div class="panel-body" align="center">
	    <s:label value="Select Master"></s:label> 
		<s:select id="masterlist" name="masterlist" list="#{'0':'Set Appointment Charges','Set Occupation':'Set Job Title','Discipline':'Set Reffered By','Set TP Type':'Set Client Declaration'}"></s:select> 
	</div>
</div>
<div class="row" >
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

