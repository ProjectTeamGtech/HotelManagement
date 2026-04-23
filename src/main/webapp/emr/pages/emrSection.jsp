<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>

<%LoginInfo loginInfo1 = LoginHelper.getLoginInfo(request);%> 
<%-- <%int id = Integer.parseInt((String)session.getAttribute("id")); %> --%>

<div class="row">
<br>
	<div class="col-lg-2"></div>
	<div class="col-lg-2 col-md-2"> 
		<label>Select Emr Type</label>
	</div>
	<div class="col-lg-4 col-md-2"> 
		<s:select id="emrType" name="emrType" list="{'Reminder','Appointment Schedule','Medical History','Prescription','Social History','Allergy'}" 
		cssClass="form-control  showToolTip chosen" value="emrType" onchange="setEmrType(this.value)"></s:select>	   		
	</div> 
	<div class="col-lg-2 col-md-2">
	<div id="reminderAddView">
		<span id="addrem"><a class="width-full btn btn-primary"  href="javascript:void(0)" onclick="addReminder('addreminder')">Add New</a></span>
	</div>
	
	 <div id="apptAddView" style="display: none;">
		<span id="addapp"><a class="width-full btn btn-primary" href="calNotAvailableSlot?actionType=week&selecteduserid=<%=loginInfo1.getId() %>">Add</a></span>
	</div>
	
	<div id="medicalAddView" style="display: none;">
		<span id="addmh"><a class="width-full btn btn-primary" href="javascript:void(0)" onclick="addMrdicalHistory('addemrHisory')">Add</a></span>
	</div>
	
	<div id="prescAddView" style="display: none;">
		<span id="addpresc"><a class="width-full btn btn-primary" href="javascript:void(0)" onclick="addPrescription(addprescription)">Add</a></span>
	</div>
	
	<div id="socialAddView" style="display: none;">
		<span id="addsh"><a class="width-full btn btn-primary" href="javascript:void(0)" onclick="addSocialHistory('addsocialHistory')">Add</a></span>
	</div>
	
	<div id="allergyAddView" style="display: none;">
		 <span id="addall"><a class="width-full btn btn-primary" href="javascript:void(0)" onclick="addAllergy(addallergy)">Add</a></span>
	</div> 
	</div>
</div>
<br/>

<div class="row" id="reminderListView">
	<%@ include file="/emr/pages/reminder.jsp" %>
</div>

 <div class="row" id="apptListView" style="display: none;">
	<%@ include file="/emr/pages/appointmentHistory.jsp" %>
</div>

<div class="row" id="medicalListView" style="display: none;">
	<%@ include file="/emr/pages/showMedicalHistory.jsp" %>
</div>

<div class="row" id="prescListView" style="display: none;">
	<%@ include file="/emr/pages/prescription.jsp" %>
</div>

<div class="row" id="socialListView" style="display: none;">
	<%@ include file="/emr/pages/socialHistory.jsp" %>
</div>

<div class="row" id="allergyListView" style="display: none;">
	<%@ include file="/emr/pages/allergy.jsp" %>
</div> 