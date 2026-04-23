<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.Emr.eu.entity.*" %>
 <script type="text/javascript" src="emr/js/consultation.js"></script>
  <script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
 
 <script type="text/javascript">
 window.onload = function(){
		imageAdder();
		imageAdderEdit();
	}
	        bkLib.onDomLoaded(function() {
	        	imageAdder();
	        	imageAdderEdit();
	        	 new nicEditor().panelInstance('consNoteText');
	        	 $('.nicEdit-panelContain').parent().width('700px');
	        	 $('.nicEdit-panelContain').parent().next().width('700px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('200px');
	        	 
	        	 new nicEditor().panelInstance('editconstext');
	        	 $('.nicEdit-panelContain').parent().width('700px');
	        	 $('.nicEdit-panelContain').parent().next().width('700px');
	        	 
	        	 $('.nicEdit-main').width('100%');
	        	 $('.nicEdit-main').height('300px');
	        	
	        	 
	        	
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

				


<div style="background-color: rgba(252, 158, 168, 0.44);">
<div class="row" style="margin-left: 3px">
<label>History</label>
</div>
<div id="showconshistory" style="background-color: rgb(243, 245, 202);margin-left: 10px;height: 200px;overflow: scroll;margin-right: 10px;">

<table style="margin-left: 20px;">
	<tr>
	<%-- <s:iterator value="consultationList"> --%>
	<%ArrayList<Emr>consultationList = (ArrayList<Emr>)session.getAttribute("consultationList"); %>
	<% for(Emr emr:consultationList ) {%>
	<tr>
		<td style="width: 20%"><b><%=emr.getLastModified() %></b></td>
		<td style="width: 20%"><b><%=emr.getPractitionerName() %></b></td>			
	</tr>
	<tr>
	<td style="width: 20%">Client given massage.</td>	
	<td style="width: 100%"><%=emr.getDescription() %></td>
	<td style="width: 20%"></td>
	<td><a href="javascript:void(0)" onclick="editConsultation(<%=emr.getId() %>,'editconsult')" style="padding-right: 20px"><i class="fa fa-edit"></i></a></td>
	<td style="width: 5%"><a href="javascript:void(0)" onclick="deleteConsultationNoteAjax(<%=emr.getId() %>)"><i class="fa fa-trash-o"></i></a></td>
	</tr>
	<tr>
	<td colspan = '5'><hr size='1' width='100%' style = 'margin-top: 10px;margin-bottom: 10px;border: 0;border-top: 1px solid #60CFD3;'/></td>
	</tr>
	<%} %>
	<%-- </s:iterator> --%>
	</tr>
</table>
</div>
<br>

<!-- add consultation history   -->
	
 <div class="row" id="addconshistory" style="display: none;margin-left: 20px;margin-right: 20px; background-color: rgba(89, 11, 19, 0.66);">
	<div class="col-lg-12" >
		<div class="form-group" >
		<div id="imagebrowser" onclick="imageAdder();" class="col-lg-12 col-md-12">
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
<%-- 		<label style="font-size: 13px;color:white">Consultation Notes</label>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setProblem('consNoteText')">Problem</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setExamination('consNoteText')">Examination</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setAssessment('consNoteText')">Assessment</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setTreatment('consNoteText')">Treatment</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setPlan('consNoteText')">Plan:</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setDateTime('consNoteText')">Date/Time</span>
 --%>	
 <label style="font-size: 13px;color:white">Consultation Notes</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Problem" onclick="setProblem('consNoteText')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Examination" onclick="setExamination('consNoteText')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Assessment" onclick="setAssessment('consNoteText')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Treatment" onclick="setTreatment('consNoteText')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Plan" onclick="setPlan('consNoteText')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="setDateTime('consNoteText')" value="Date/Time">
			<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="30" title="Enter Note" name="consNoteText"  id="consNoteText"></textarea>
		</div>
	<div class="form-group">
			<button type="button" class="btn btn-primary" onclick="saveConsultationNoteAjax();">Save</button>
			<button type="button" class="btn btn-primary"  onclick="closeConsultationNote()">Close</button>
	</div>
	
</div>

</div>
<!-- edit consultation history   -->
	
 <div class="row" id="editconsult" style="display: none;margin-left: 20px;margin-right: 20px;background-color: rgba(89, 11, 19, 0.66);">
	<div class="col-lg-12">
		<div class="form-group">
		<div id="imagebrowseredit" onclick="imageAdderEdit();" class="col-lg-12 col-md-12" style="padding-left: 113px;">
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
<%-- 	<label style="font-size: 13px;color:white">Consultation Notes</label>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setProblem('editconstext')">Problem</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setExamination('editconstext')">Examination</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setAssessment('editconstext')">Assessment</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setTreatment('editconstext')">Treatment</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setPlan('editconstext')">Plan:</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:white" onclick="setDateTime('editconstext')">Date/Time</span>
 --%>
 <label style="font-size: 13px;color:white">Consultation Notes</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Problem" onclick="setProblem('editconstext')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Examination" onclick="setExamination('editconstext')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Assessment" onclick="setAssessment('editconstext')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Treatment" onclick="setTreatment('editconstext')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Plan" onclick="setPlan('editconstext')">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="setDateTime('editconstext')" value="Date/Time">
	
				<textarea class="form-control showToolTip" data-toggle="tooltip" rows="3" cols="30" title="Enter Note" name="editconstext"  id="editconstext"></textarea>
				<input type="hidden" id = "hiddenidconsultnote" name="hiddenidconsultnote">
		</div>
	<div class="form-group">
			<button type="button" class="btn btn-primary" onclick="editsaveConsultationNoteAjax();">Update</button>
			<button type="button" class="btn btn-primary"  onclick="closeConsultationNote()">Close</button>
	</div>
</div>

</div> 
<br><br>
</div>
