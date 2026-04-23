<%@ taglib uri="/struts-tags"  prefix="s"%>
 <script type="text/javascript" src="emr/js/consultationnote.js"></script>
 <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
 <%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Emr.eu.entity.*" %>
 
 <script type="text/javascript">
 window.onload = function(){
		imageAdder();
		imageAdderEdit();
	}
	        bkLib.onDomLoaded(function() {
	        	imageAdder();
	        	imageAdderEdit();
	        	 new nicEditor().panelInstance('consNote');
	        	 $('.nicEdit-panelContain').parent().width('750px');
	        	 $('.nicEdit-panelContain').parent().next().width('750px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('500px');
	        	 
	        	 new nicEditor().panelInstance('editconsNote');
	        	 $('.nicEdit-panelContain').parent().width('750px');
	        	 $('.nicEdit-panelContain').parent().next().width('750px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('500px');
	        	
	        	 
	        	
	      });
	       
</script>
 <style>
.backColorCon{
	color: white; 
	background-color: rgba(89, 11, 19, 0.66); 
	height: 25px;
}
.nicEdit-main{
     background-color: white;
}

</style>
<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Consultation Note</a></li>
			
		</ol>
	</div>
</div>
<div class="row" id = "selectdp">
<div class="col-lg-12 col-md-12">					

<div class="col-lg-1 col-md-1">					
<label>Practitioner:</label>
</div>
<div class="col-lg-2 col-md-2">					

<s:if test="%{#userList != 'null'}" >
	<s:select cssClass="form-control showToolTip chosen" id="diaryUser" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User" onchange="setClients(this.value)"   />
</s:if>
</div>

<div class="col-lg-1 col-md-1">					
<label>Client:</label>
</div>
<div class="col-lg-2 col-md-2">					
<%-- <select class="form-control showToolTip chosen" id="clientname" name="clientname" onchange="setCondition(this.value)">
<option>Select Client</option>
</select> --%>
	<s:select cssClass="form-control showToolTip chosen" id="clientname" name="clientname" list="clientList" listKey="id" listValue="clientName" headerKey="0" theme="simple" headerValue="Select Client"  onchange="setCondition(this.value)"  />


</div>

<div class="col-lg-1 col-md-1">					
<label>Condition:</label>
</div>
<div class="col-lg-2 col-md-2">					
<%-- <select class="form-control showToolTip chosen" id="condition" name="condition">
<option>Select Condition</option>
</select> --%>
	<s:select cssClass="form-control showToolTip chosen" id="condition" name="condition" list="conditionList" listKey="id" listValue="treatmentType" headerKey="0" theme="simple" headerValue="Select condition"    />

</div>
<div class="col-lg-1 col-md-1">
<input type="button" value="Show" onclick="showList()" class="btn btn-primary">					
</div>
</div>	
</div>
<label id = "errorselect" style="color: red"></label>
<hr>
<div class="row backColorCon" style="margin-left: 2px;margin-right: 2px;">
<div class="col-lg-10 col-md-10">					

<label>Consultation Note</label>  <a href="#" onclick="addConsultationNote()">Add</a>| <a href="">Print</a>

</div>
</div>
<hr>
<div style="background-color: rgba(252, 158, 168, 0.44);">
<div class="row" style="margin-left: 3px">
<label>History</label>
</div>
<div id="consHistory" style="background-color: rgb(243, 245, 202);margin-left: 10px;height: 300px;overflow: scroll;margin-right: 10px;">



 <table style="margin-left: 20px;">
	<tr>
	<%ArrayList<Emr>consultationList = (ArrayList<Emr>)session.getAttribute("consultationList"); %>
	<% for(Emr emr:consultationList ) {%>
	<tr>
		<td><%=emr.getLastModified() %></td>
		<td><%=emr.getPractitionerName() %></td>			
	</tr>
	<tr>
	<td>Client given massage.</td>	
	<td><%=emr.getDescription() %></td>
	<td><a href="javascript:void(0)" onclick="editConsultation(<%=emr.getId() %>,'editconsult')" style="padding-right: 20px"><i class="fa fa-edit"></i></a></td>
	<td style="width: 5%"><a href="javascript:void(0)" onclick="deleteConsultationNoteAjax(<%=emr.getId() %>)"><i class="fa fa-trash-o"></i></a></td>
	</tr>
	<tr>
	<td colspan = '5'><hr size='1' width='100%' style = 'margin-top: 10px;margin-bottom: 10px;border: 0;border-top: 1px solid #60CFD3;'/></td>
	</tr>
	<%} %>
</table> 


</div>
<br>

<!-- add consultation history   -->
 <div class = "row" id="addconsutationnote" style="display: none;margin-left: 20px;margin-right: 20px;background-color: rgba(89, 11, 19, 0.66);">
	<div class="col-lg-12">
		<div class="form-group">
		<div id="imagebrowser" onclick="imageAdder();">
			 <img src="emr/img/downarow.png" name="image1" />
			 <img src="emr/img/leftarow.png" name="image2" />
			 <img src="emr/img/linea-arrows-10_e000(0)_48.png" name="image3" />
			 <img src="emr/img/linea-arrows-10_e013(1)_48.png" name="image4" />
			 <img src="emr/img/linedownarow.png" name="image5" />
			 <img src="emr/img/linedownarowright.png" name="image6" />
			 <img src="emr/img/linedownbreakleft.png" name="image7" />
			 <img src="emr/img/linedownbreakright.png" name="image8" />
			 <img src="emr/img/lineupdownarow.png" name="image9" />
			 <img src="emr/img/rightarow.png" name="image10" />
			 <img src="emr/img/southpoll.png" name="image11" /> 
			 <img src="emr/img/uparow.png" name="image12" />
	</div>
<%-- 	<label style="font-size: 13px;color: white">Consultation Notes</label>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setProblem('consNote')">Problem</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setExamination('consNote')">Examination</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setAssessment('consNote')">Assessment</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setTreatment('consNote')">Treatment</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setPlan('consNote')">Plan:</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setDateTime('consNote')">Date/Time</span>
 --%>	<label style="font-size: 13px;color: white">Consultation Notes</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Problem" onclick="setProblem('consNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Examination" onclick="setExamination('consNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Assessment" onclick="setAssessment('consNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Treatment" onclick="setTreatment('consNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Plan" onclick="setPlan('consNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Date/Time" onclick="setDateTime('consNote')">
	
 				<textarea class="form-control showToolTip" data-toggle="tooltip" rows="10" cols="30" title="Enter Note" name="consNote"  id="consNote"></textarea>
		</div>
		<div class="form-group">
			<button type="button" class="btn btn-primary" onclick="saveConsultationNote();">Save</button>
			<button type="button" class="btn btn-primary"  onclick="closeAddConsultationNote()">Close</button>
	</div>
	</div>
		
	
	</div>
	
	


<!-- edit consultation history   -->
	
 <div class="row" id="editconsutationnote" style="display: none;margin-left: 20px;margin-right: 20px;background-color: rgba(89, 11, 19, 0.66);">
	<div class="col-lg-12">
	
		<div class="form-group">
		<div id="imagebrowseredit" onclick="imageAdderEdit();" class="col-lg-12 col-md-12">
			 <img src="emr/img/downarow.png" name="image1" />
			 <img src="emr/img/leftarow.png" name="image2" />
			 <img src="emr/img/linea-arrows-10_e000(0)_48.png" name="image3" />
			 <img src="emr/img/linea-arrows-10_e013(1)_48.png" name="image4" />
			 <img src="emr/img/linedownarow.png" name="image5" />
			 <img src="emr/img/linedownarowright.png" name="image6" />
			 <img src="emr/img/linedownbreakleft.png" name="image7" />
			 <img src="emr/img/linedownbreakright.png" name="image8" />
			 <img src="emr/img/lineupdownarow.png" name="image9" />
			 <img src="emr/img/rightarow.png" name="image10" />
			 <img src="emr/img/southpoll.png" name="image11" /> 
			 <img src="emr/img/uparow.png" name="image12" />
	</div>
	
			<label style="font-size: 13px;color: white">Consultation Notes</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Problem" onclick="setProblem('editconsNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Examination" onclick="setExamination('editconsNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Assessment" onclick="setAssessment('editconsNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Treatment" onclick="setTreatment('editconsNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Plan" onclick="setPlan('editconsNote')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Date/Time" onclick="setDateTime('editconsNote')">

				<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="30" title="Enter Note" name="editconsNote"  id="editconsNote"></textarea>
				<input type="hidden" id = "hiddenidconsultnote" name="hiddenidconsultnote">
				
		</div>
	<div class="form-group">
			<button type="button" class="btn btn-primary" onclick="updateConsultationNote();">Update</button>
			<button type="button" class="btn btn-primary"  onclick="closeEditConsultationNote()">Close</button>
	</div>
</div>

</div> 
<br><br>
</div>

  