<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="jscolor/jscolor.js"></script>
<script type="text/javascript" src="master/js/masterValidation.js"></script>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<script type="text/javascript" src="master/js/treatmentType.js"></script>
<script type="text/javascript" src="common/js/masters.js"></script>

<%LoginInfo loginInfo = LoginHelper.getLoginInfo(request);%>

<div class="row details mainheader">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<h4>Edit Condition</h4>
								</div>
							</div>

<div class="row">
	<div class="col-lg-12">
		<span class="error"><s:actionerror id="server_side_error" /></span>
	</div>
</div>

<s:form action="updateTreatmentType" id="treatmentType_form" theme="simple">
<div class="row">
	<div class="col-lg-3 col-md-2"></div>
	<div class="col-lg-6 col-md-8">
		<div class="panel panel-primary">
		<%-- <div class="panel-body">
		<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
			<%if(loginInfo.getUserType() == 2){ %>				
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>Practitioner:</label>
							<s:if test="%{#userList != 'null'}" >
								<s:select cssStyle="height: 20px;
								padding: 0px" cssClass="text ui-widget-content ui-corner-all" id="diaryUser" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User"/>
						<label  id = "practitionerError" class="text-danger"></label>
						</s:if>
						
					</div>
					</div>
				</div>			
			<%} %>
				
			</s:if>
			</div> --%>
			
			<div class="panel-body">
				<label>Location </label><label class="text-danger">*</label>
				<s:select name="location" id="location" list="locationList"
				listKey="locationid" listValue="location" cssClass="showToolTip form-control" data-toggle="tooltip"
				headerKey="0" headerValue="Select Location"/>
			</div>
			
			
			<div class="panel-body">
			<s:hidden id="id" name="id" />
				<label>Condition / Diagnosis</label><label class="text-danger">*</label>
				<s:textfield id="treatmentName" name="treatmentName"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Condition Name" placeholder="Enter Condition Name" onblur="initialCap(this)"/>
			</div>
			
			<div class="panel-body">
				<label>ICD code</label>
				<s:textfield id="icd_code" name="icd_code"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter ICD Code" placeholder="Enter ICD Code" />
			</div>
			<%-- <div class="panel-body">
				<label>Treatment Type Note</label><label class="text-danger">*</label>
				<s:textfield id="treatmentNotes" name="treatmentNotes"
					cssClass="showToolTip form-control" data-toggle="tooltip"
					title="Enter Treatment Notes" placeholder="Enter Treatment Notes" />
					
			</div>		 --%>	
		</div>
	</div>
	<div class="col-lg-3 col-md-2"></div>
</div>




	<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary"  value="Update"/>
			<s:reset cssClass="btn btn-primary" />
			<a href="backTreatmentType" class="btn btn-primary">Back</a>
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
</s:form>
