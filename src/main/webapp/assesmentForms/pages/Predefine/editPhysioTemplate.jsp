<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="assesmentForms/js/Predefine/assessmentTemplate.js"></script>

<style>
.ppstyle{
padding: 3px 7px;
height: 20px;
font-size: 10px;
}
</style>
<script>
$(document).ready(function() {

	$("#examDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});

	$("#assessmentDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true
	});
	
	$("#treatmentDate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true
	});
});


</script>

<script type="text/javascript">
$(document).ready(function(){

	<%String imagedata = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata);%>
	var dataURL = '<%=imagedata%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas1 = document.getElementById('editphysioimgTemplate');
    var context1 = canvas1.getContext('2d');
    context1.drawImage(imageObj, 0, 0);
    };
	
});	

function editPhysioImageCanvas(){
	
	<%String imagedata1 = (String)session.getAttribute("imageData");
	System.out.println("Result:" +imagedata1);%>
	var dataURL = '<%=imagedata1%>'; 
    // load image from data url
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas = document.getElementById('Background');
    var context = canvas.getContext('2d');
    context.drawImage(imageObj, 0, 0);
   
    };
	
	 $( '#editphysioimageCanvas' ).modal( "show" );
	
}

function saveEditPhysioImageTemplate(){
	
	var Pic;
	 for(var i in LAYERS){
	  Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
	// alert(i);
	}

	 document.getElementById('imageDataphysio').value = Pic;

	 document.getElementById('physioeditimage_form').submit();
	 
}


var Pic;
for(var i in LAYERS){
 Pic = document.getElementById(LAYERS[i].name).toDataURL("image/png");
// alert(i);
}

document.getElementById('imageData').value = Pic;

</script>


<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-print">
		<ol class="breadcrumb">
			<li><a href="#">Assessment Forms</a></li>
			<li class="active">Edit Assessment Template Form</li>
		</ol>
	</div>
</div>

<s:form action="updateAssessmentTemplate" id="physioEdit_form" theme="simple">



<div class="row">
 <div class="panel panel-primary">
<div class="panel-body">

			<div class="row">
					<h3 align="center"><s:property value="templateName"/></h3>
				</div>	
			<br/>
			
			
	<div class="col-lg-3"></div>						
			<div class="col-lg-7" style="height: 400px; width: 500px;">
				<%-- <%@ include file="/minipaint/editor.jsp"%> --%>
				<canvas id="editphysioimgTemplate" height="400" width="500"></canvas>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 hidden-print">
					<!-- <input type="button"  class="btn btn-primary" value="Edit Image" onclick="editPhysioImageCanvas();" />	 -->				
					<h3><a href="#" class="text-warning" onclick="editPhysioImageCanvas();" title="Edit Image"><i class="fa fa-edit"></i></a></h3>
				</div>
</div>	
</div>
</div>
<s:hidden name="imageData" id="imageData"/>

<div class="panel panel-primary">
<div class="panel-body">			
<div class="col-lg-12">

<s:if test="actionType != 2">
	<div class="row">
		<div class="col-lg-1 col-md-1">	
			<label style="font-size: 10px;">Practitioner</label>
		</div>
		<div class="col-lg-2 col-md-2">
			<s:if test="%{#userList != 'null'}" >
				<s:select cssClass="form-control showToolTip chosen" id="physioDiaryUser" name="diaryUser" list="userList" listKey="id" listValue="diaryUser" headerKey="0" theme="simple" headerValue="Select User" onchange="setClientsAssessment(this.value)"   />
			</s:if>
		</div> 				
		<label  id = "diaryUserAssessmentError" class="text-danger"></label>	
			
			<div class="col-lg-1 col-md-1">	
					<label style="font-size: 10px;">Client Name</label>
				</div>
				 <div class="col-lg-2 col-md-2">
				<s:select cssClass="form-control showToolTip chosen" id="physioClient" name="client" list="clientList" listKey="id" listValue="clientName" headerKey="0" theme="simple" headerValue="Select Client"  onchange="setConditionAssessment(this.value)"  />
				</div> 
			<label  id = "clientnameAssessmentError" class="text-danger"></label>	
				
			<div class="col-lg-1 col-md-1">	
					<label style="font-size: 10px;">Condition</label>
				</div>
				 <div class="col-lg-2 col-md-2">
				<s:select cssClass="form-control showToolTip chosen" id="physioCondition" name="condition" list="conditionList" listKey="id" listValue="treatmentType" headerKey="0" theme="simple" headerValue="Select condition"    />
			</div> 
			 <label  id = "conditionAssessmentError" class="text-danger"></label>	
	</div>		
</s:if>
		<s:else>
				<s:hidden name="diaryUser" id="diaryUser"/>
				<s:hidden name="client" id="client"/>
				<s:hidden name="condition" id="condition"/>
				<s:hidden name="actionType" id ="actionType"/>
		</s:else>
			<br/>
			
	<div class="row">
		<%-- <div class="col-lg-2">
		<label style="font-size: 10px;">Patient Name : </label>
		</div>		
		<div class="col-lg-2">
			<s:textfield name="client" id="client" readonly="true" cssClass="form-control" 
				     onclick="showAssessmentPopUp()" data-toggle="modal" data-target="#clientSearch" placeholder = "Enter Patient Name" title="Enter Patient Name"/>
			
		</div>	 --%>	
		
		 
		<div class="col-lg-1">
		<label style="font-size: 10px;">Unique Identifying No : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="clientId" id="clientId1" readonly="true" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Unique Identifying No" title="Enter Unique Identifying No"/>
		</div>
		
		<s:hidden name="id" id="id"/>
		
		<div class="col-lg-1 ">
		<label style="font-size: 10px;">Date of examination : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="examDate" id="examDate" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Date of examination" title="Enter Date of examination"/>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">Subjective History : </label>
		</div>	
		<div class="col-lg-9">
			<s:textarea name="subjectiveHistory" id="subjectiveHistory" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Subjective History" title="Enter Subjective History"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">For Outcome Measurements : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="outcomeMeasurement" id="outcomeMeasurement" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter For Outcome Measurements" title="Enter For Outcome Measurements"/>
		</div>	
	</div>
	<br/>
	<%--  <div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">Patient Name : </label>
		</div>		
		<div class="col-lg-2">
			<s:textfield name="client1" id="client1" readonly="true" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Patient Name" title="Enter Patient Name"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">Unique Identifying No : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="clientId1" id="clientId1" readonly="true" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Unique Identifying No" title="Enter Unique Identifying No"/>
		</div>
	</div>
	<br/> --%> 
	<div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">1. Primary Diagnosis : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="primaryDiagnosis" id="primaryDiagnosis" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Primary Diagnosis" title="Enter Primary Diagnosis"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">2. Secondary Diagnosis : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="secondaryDiagnosys" id="secondaryDiagnosys" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Secondary Diagnosis" title="Enter Secondary Diagnosis"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">3. Assessment date : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="assessmentDate" id="assessmentDate" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Assessment date" title="Enter Assessment date"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">4. Treatment date : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="treatmentDate" id="treatmentDate" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Treatment date" title="Enter Treatment date"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">5. Treatments used : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="treatmentUsed" id="treatmentUsed" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Treatments used" title="Enter Treatments used"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-2">
			<label style="font-size: 10px;">6. Discharge status : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="dischargeStatus" id="dischargeStatus" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Discharge status" title="Enter Discharge status"/>
		</div>
	</div>
	<br/>	

</div>
</div>
</div>

<div class="row">
<div class="col-lg-12">
<div class="col-lg-6">
<div class="panel panel-primary">
<div class="panel-body">	
<div class="col-lg-12">		
	 <div class="table-responsive">
		<table id="results"	class="table table-hove table-bordered table-striped table-condensed">
			<tr>
			<td>Primary Diagnosis</td>
			<!-- <td><input type="text" name="primaryDiagnosisDetails"  id="primaryDiagnosisDetails" class="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Primary Diagnosis" title="Enter Primary Diagnosis"/></td>
			 --><td><s:textfield name="primaryDiagnosisDetails" id="primaryDiagnosisDetails" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Primary Diagnosis" title="Enter Primary Diagnosis"></s:textfield></td>
			</tr>
			<tr>
			<td>Secondary Diagnosis</td>
			<td><s:textfield type="text" name="secondaryDiagnosysDetails"  id="secondaryDiagnosysDetails" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Secondary Diagnosis" title="Enter Secondary Diagnosis"/></td>
			</tr>
		</table>
	</div>
	
	
</div>
</div>
</div>
<!-- <div class="panel panel-primary">
<div class="panel-body">
	<div class="table-responsive">
		<table class="table table-striped table-hover table-condensed" id = "painareaTable">
			<thead>
			<tr>
				<th align="center"  style="font-size: 10px;">Delete</th>
				<th align="center"  style="font-size: 10px;">Pain Area</th>	
				<th align="center"  style="font-size: 10px;">DETAIL</th>	
				<th align="center"  style="font-size: 10px;">VAS</th>										
			</tr>
			</thead>
			<tbody>
			<tr>
				<TD><INPUT type="checkbox" name="chk" title="Delete row" /></TD>										
				<TD>1</TD>										
				<td><input type="text" name="painArea[0].detail" class="form-control showToolTip ppstyle detail" data-toggle="tooltip"></td>
				<td><input type="text" name="painArea[0].vas" class="form-control showToolTip ppstyle vas" data-toggle="tooltip"></td>
			</tr>
			</tbody>
		</table>
	</div>
	<div class="form-group">
		<a onclick="addRow('painareaTable')" class="btn btn-primary ppstyle"><i class="fa fa-plus"></i> Add More</a>								
		<a onclick="deleteRow('painareaTable')" class="btn btn-primary ppstyle" ><i class="fa fa-trash-o"></i> Delete Row</a>
	</div>
</div>
</div> -->
<div class="panel panel-primary">
<div class="panel-body">
	<div class="table-responsive">
		<table class="table table-striped table-hover table-condensed" id = "generalHealthTable">
			<thead>
			<tr>
				<th align="center"  style="font-size: 10px;">Delete</th> 
				<th align="center"  style="font-size: 10px;">#</th>
				<th align="center"  style="font-size: 10px;">General Health</th>	
				<th align="center"  style="font-size: 10px;">Y/N</th>	
				<th align="center"  style="font-size: 10px;">Details</th>														
			</tr>
			</thead>
			<tbody>
			<tr>
				<td></td>
				<td>1</td>
				<td style="font-size: 10px;">Diabetes</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="diabetesyn" name="diabetesyn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="diabetesDetail" name="diabetesDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>2</td>
				<td style="font-size: 10px;">Epilepsy</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="epilepsyyn" name="epilepsyyn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="epilepsyDetail" name="epilepsyDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>3</td>
				<td style="font-size: 10px;">Heart conditions</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="heartConditionyn" name="heartConditionyn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="heartConditionDetail" name="heartConditionDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>4</td>
				<td style="font-size: 10px;">Lung disorders</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="lungDisorderyn" name="lungDisorderyn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="lungDisorderDetail" name="lungDisorderDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>5</td>
				<td style="font-size: 10px;">Prostate</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="prostateyn" name="prostateyn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="prostateDetail" name="prostateDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>6</td>
				<td style="font-size: 10px;">Carcinoma</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="carcinomayn" name="carcinomayn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="carcinomaDetail" name="carcinomaDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>7</td>
				<td style="font-size: 10px;">RA</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="rayn" name="rayn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="raDetail" name="raDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>8</td>
				<td style="font-size: 10px;">Osteopaenia/porosis</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="osteopaeniayn" name="osteopaeniayn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="osteopaeniaDetail" name="osteopaeniaDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>9</td>
				<td style="font-size: 10px;">Wt stable</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="wtStableyn" name="wtStableyn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="wtStableDetail" name="wtStableDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>10</td>
				<td style="font-size: 10px;">Bladder & Bowel</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="bladderBowelyn" name="bladderBowelyn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="bladderBowelDetail" name="bladderBowelDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>11</td>
				<td style="font-size: 10px;">Other</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="otheryn" name="otheryn" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="otherDetail" name="otherDetail" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			</tbody>
		</table>
		
	<!-- <div class="form-group">
		<a onclick="addRowGeneralHealth('generalHealthTable')" class="btn btn-primary ppstyle"><i class="fa fa-plus"></i> Add More</a>								
		<a onclick="deleteRowGeneralHealth('generalHealthTable')" class="btn btn-primary ppstyle" ><i class="fa fa-trash-o"></i> Delete Row</a>
	</div> -->
	</div>
		
	
	<div class="table-responsive">
		<table id="results"	class="table table-hove table-bordered table-striped table-condensed">
			<tr>
			<td>Medications</td>
			<td><s:textfield type="text" name="medication"  id="medication" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Medications" title="Enter Medications"/></td>
			</tr>
			<tr>
			<td>Investigations</td>
			<td><s:textfield type="text" name="investigation"  id="investigation" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Investigations" title="Enter Investigations"/></td>
			</tr>
		</table>
		
	</div>
	
		
	 <div class="table-responsive">
		<table class="table table-striped table-hover table-condensed" id = "otherTable">
		<thead>
			<tr>
				<!-- <th align="center"  style="font-size: 10px;">Delete</th> 
				<th align="center"  style="font-size: 10px;">#</th> -->
				<th align="center"  style="font-size: 10px;">Type</th>			
				<th align="center"  style="font-size: 10px;">Details</th>														
			</tr>
			</thead>
		<tbody>
			<tr>
				<!-- <td></td>
				<td>1</td> -->
				<td style="font-size: 10px;">General Health</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="generalHealth" name="generalHealth" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td style="font-size: 10px;">Night pain</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="nightPain" name="nightPain" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td style="font-size: 10px;">Family history</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="familyHistory" name="familyHistory" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>4</td> -->
				<td style="font-size: 10px;">Psycho social</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="psychoSocial" name="psychoSocial" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>5</td> -->
				<td style="font-size: 10px;">Surgery</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="surgery" name="surgery" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>6</td> -->
				<td style="font-size: 10px;">Accidents</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="accident" name="accident" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
		</table>
		
		<!-- <div class="form-group">
			<a onclick="addRowOther('otherTable')" class="btn btn-primary ppstyle"><i class="fa fa-plus"></i> Add More</a>								
			<a onclick="deleteRowOther('otherTable')" class="btn btn-primary ppstyle" ><i class="fa fa-trash-o"></i> Delete Row</a>
		</div> -->
	</div>
</div>
</div>
 
<div class="panel panel-primary">
<div class="panel-body">
	<div class="row">
		<div class="col-lg-3">
			<label style="font-size: 10px;">Joint ranges (active movements) : </label>
		</div>	
		<div class="col-lg-9">
			<s:textarea name="jrActiveMoment" id="jrActiveMoment" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Joint ranges (active movements)" title="Enter Joint ranges (active movements)"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-3">
			<label style="font-size: 10px;">Joint ranges (passive movements) : </label>
		</div>	
		<div class="col-lg-9">
			<s:textarea name="jrPassiveMoment" id="jrPassiveMoment" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Joint ranges (passive movements)" title="Enter Joint ranges (passive movements)"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-3">
			<label style="font-size: 10px;">Myotomes (resisted movements) : </label>
		</div>	
		<div class="col-lg-9">
			<s:textarea name="myotomes" id="myotomes" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Myotomes (resisted movements)" title="Enter Myotomes (resisted movements)"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-3">
			<label style="font-size: 10px;">Dermatomes : </label>
		</div>	
		<div class="col-lg-9">
			<s:textarea name="dermatomes" id="dermatomes" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Dermatomes" title="Enter Dermatomes"/>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-3">
			<label style="font-size: 10px;">Sensory Loss/changes : </label>
		</div>	
		<div class="col-lg-9">
			<s:textarea name="sensoryLossChanges" id="sensoryLossChanges" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Sensory Loss/changes" title="Enter Sensory Loss/changes"/>
		</div>
	</div>
	<br/>		

</div>
</div>
</div>


<div class="col-lg-6">
<div class="panel panel-primary">
<div class="panel-body">			
<div class="table-responsive">
		<table class="table table-striped table-hover table-condensed" id = "neuralTestTable">
			<thead>
			<tr><h5><b>NEURAL TESTS</b></h5></tr>
			<tr>
				<!-- <th align="center"  style="font-size: 10px;">Delete</th> 
				<th align="center"  style="font-size: 10px;">#</th> -->
				<th align="center"  style="font-size: 10px;">LOWER LIMB REFLEXES</th>	
				<th align="center"  style="font-size: 10px;">RIGHT</th>	
				<th align="center"  style="font-size: 10px;">LEFT</th>	<th></th><th></th>													
			</tr>
			</thead>
			<tbody>
			<tr>
				<!-- <td></td>
				<td>1</td> -->
				<td style="font-size: 10px;">Knee</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="kneeRight" name="kneeRight" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="kneeLeft" name="kneeLeft" cssClass="form-control showToolTip ppstyle"/></td><td></td><td></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td style="font-size: 10px;">Ankle</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="ankleRight" name="ankleRight" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="ankleLeft" name="ankleLeft" cssClass="form-control showToolTip ppstyle"/></td><td></td><td></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td style="font-size: 10px;">Babinski</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="babinskiRight" name="babinskiRight" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="babinskiLeft" name="babinskiLeft" cssClass="form-control showToolTip ppstyle"/></td><td></td><td></td>
			</tr>
			<tr>
				<!-- <th></th>
				<th></th> -->
				<th align="center"  style="font-size: 10px;">NEURAL TENSION</th><th></th><th></th><th></th><th></th>	
			</tr>
			<tr>
				<!-- <td></td>
				<td>1</td> -->
				<td style="font-size: 10px;">Slump</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="slumpRight" name="slumpRight" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="slumpLeft" name="slumpLeft" cssClass="form-control showToolTip ppstyle"/></td><td></td><td></td>
			</tr>
			<tr>
				<!-- <th align="center"  style="font-size: 10px;">Delete</th> 
				<th align="center"  style="font-size: 10px;">#</th> -->
				<th align="center"  style="font-size: 10px;">SLR</th>	
				<th align="center"  style="font-size: 10px;">ROM</th>	
				<th align="center"  style="font-size: 10px;">Pain</th>		
				<th align="center"  style="font-size: 10px;">ROM</th>	
				<th align="center"  style="font-size: 10px;">Pain</th>														
			</tr>
			<tr>
				<!-- <td></td>
				<td>1</td> -->
				<td style="font-size: 10px;">+Dorsiflexion</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dorsiflexionRom1" name="dorsiflexionRom1" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dorsiflexionPain1" name="dorsiflexionPain1" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dorsiflexionRom2" name="dorsiflexionRom2" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dorsiflexionPain2" name="dorsiflexionPain2" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td style="font-size: 10px;">+Medial rotation</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="medicalRotaionRom1" name="medicalRotaionRom1" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="medicalRotaionPain1" name="medicalRotaionPain1" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="medicalRotaionRom2" name="medicalRotaionRom2" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="medicalRotaionPain2" name="medicalRotaionPain2" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td style="font-size: 10px;">+Neck flexion</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="neckFlexionRom1" name="neckFlexionRom1" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="neckFlexionPain1" name="neckFlexionPain1" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="neckFlexionRom2" name="neckFlexionRom2" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="neckFlexionPain2" name="neckFlexionPain2" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>4</td> -->
				<td style="font-size: 10px;">Passive Knee Bend</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="passiveKneeBendRom1" name="passiveKneeBendRom1" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="passiveKneeBendPain1" name="passiveKneeBendPain1" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="passiveKneeBendRom2" name="passiveKneeBendRom2" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="passiveKneeBendPain2" name="passiveKneeBendPain2" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			</tbody>
		</table>
</div>
</div>
</div>
<div class="panel panel-primary">
<div class="panel-body">			
<div class="table-responsive">
		<table class="table table-striped table-hover table-condensed" id = "table1">
			<thead>
			<tr><h5><b>NEURAL TESTS</b></h5></tr>
			<tr>
				<!-- <th align="center"  style="font-size: 10px;">Delete</th> 
				<th align="center"  style="font-size: 10px;">#</th> -->
				<th align="center"  style="font-size: 10px;">CNS</th>	
				<th align="center"  style="font-size: 10px;">Coughing</th>	
				<th align="center"  style="font-size: 10px;">Sneezing</th>													
			</tr>
			</thead>
			<tbody>
				<tr>
				<!-- <td></td>
				<td>1</td> -->
				<td style="font-size: 10px;">S4</td>
				<td></td>
				<td style="font-size: 10px;">Saddle anaesthesia</td>
				</tr>
				<tr>
				<!-- <td></td>
				<td>1</td> -->
				<td style="font-size: 10px;">Bladder/bowel</td>
				<td></td>
				<td style="font-size: 10px;">HYPERMOBILITY Beighton Scale /9</td>
				</tr>
			</tbody>
			</table>
</div>
</div>
</div>
<div class="panel panel-primary">
<div class="panel-body">
	<div class="table-responsive">
		<table class="table table-striped table-hover table-condensed" id = "vadTable">
			<thead>
			<tr>
				<!-- <th align="center"  style="font-size: 10px;">Delete</th> 
				<th align="center"  style="font-size: 10px;">#</th> -->
				<th align="center"  style="font-size: 10px;">VAD/HA</th>	
				<th align="center"  style="font-size: 10px;">YES</th>	
				<th align="center"  style="font-size: 10px;">NO</th>														
			</tr>
			</thead>
			<tbody>
			<tr>
				<!-- <td></td>
				<td>1</td> -->
				<td style="font-size: 10px;">Dizzy</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dizzyy" name="dizzyy" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dizzyn" name="dizzyn" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td style="font-size: 10px;">Double Vision</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="doubleVisiony" name="doubleVisiony" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="doubleVisionn" name="doubleVisionn" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td style="font-size: 10px;">Dysarthria</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dysarthriay" name="dysarthriay" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dysarthrian" name="dysarthrian" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>4</td> -->
				<td style="font-size: 10px;">Dysphagia</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dysphagiay" name="dysphagiay" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dysphagian" name="dysphagian" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>5</td> -->
				<td style="font-size: 10px;">Drop attacks</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dropAttacky" name="dropAttacky" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="dropAttackn" name="dropAttackn" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>6</td> -->
				<td style="font-size: 10px;">Nystagmus</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="nystagmusy" name="nystagmusy" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="nystagmusn" name="nystagmusn" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>7</td> -->
				<td style="font-size: 10px;">Nausea</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="nauseay" name="nauseay" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="nausean" name="nausean" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>8</td> -->
				<td style="font-size: 10px;">Numbness</td>
				<td style="font-size: 10px;"><s:textfield type="text" id="numbnessy" name="numbnessy" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield type="text" id="numbnessn" name="numbnessn" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			
			</tbody>
		</table>
	</div>		
</div>
</div>

<div class="panel panel-primary">
<div class="panel-body">
	<div class="table-responsive">
		<table class="table table-striped table-hover table-condensed">
			<thead>
			<tr>
				<!-- <th align="center"  style="font-size: 10px;">Delete</th> 
				<th align="center"  style="font-size: 10px;">#</th> -->
				<th align="center"  style="font-size: 10px;">UPPER LIMB</th>	
				<th align="center"  style="font-size: 10px;">REFLEXES</th>	
				<th align="center"  style="font-size: 10px;">Right</th>	
				<th align="center"  style="font-size: 10px;">Left</th>														
			</tr>
			</thead>
			<tbody>
			<tr>
				<td></td>			
				<td style="font-size: 10px;">Biceps</td>
				<td style="font-size: 10px;"><s:textfield id="bicepRight" name="bicepRight" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield id="bicepLeft" name="bicepLeft" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>				
				<td style="font-size: 10px;">Triceps</td>
				<td style="font-size: 10px;"><s:textfield id="tricepRight" name="tricepRight" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield id="tricepLeft" name="tricepLeft" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>				
				<td style="font-size: 10px;">Brachioradialis</td>
				<td style="font-size: 10px;"><s:textfield id="brachioradialisRight" name="brachioradialisRight" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield id="brachioradialisLeft" name="brachioradialisLeft" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			
			<tr>				
				<th></th><th align="center"  style="font-size: 10px;">NEURAL TENSION</th><th></th>
			</tr>
			
			<tr>
				<td>ULNT</td>			
				<td style="font-size: 10px;">1</td>
				<td style="font-size: 10px;"><s:textfield id="ulnt1Right" name="ulnt1Right" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield id="ulnt1Left" name="ulnt1Left" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>				
				<td style="font-size: 10px;">2</td>
				<td style="font-size: 10px;"><s:textfield id="ulnt2Right" name="ulnt2Right" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield id="ulnt2Left" name="ulnt2Left" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>				
				<td style="font-size: 10px;">3</td>
				<td style="font-size: 10px;"><s:textfield id="ulnt3Right" name="ulnt3Right" cssClass="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><s:textfield id="ulnt3Left" name="ulnt3Left" cssClass="form-control showToolTip ppstyle"/></td>
			</tr>
			</tbody>
		</table>
	</div>
</div>
</div>

</div>
</div>
</div>

 


<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8 col-sm-6 col-xs-6 hidden-print">
			<button type="button"  class="btn btn-primary"  value="Update" onclick="editImagePhysio()">Update</button>
			<a class="btn btn-primary"	style="text-decoration: none" onclick="window.print();"> Print</a>
			<s:reset cssClass="btn btn-primary" />			
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	
	
</s:form>


<!-- Modal Client Search-->
<div class="modal fade" id="editphysioimageCanvas" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" style="width: 1000px; height: 900px;">
  <s:form action="updateImageAssessmentTemplate" id="physioeditimage_form" theme="simple"> 
  
  <s:hidden name="imageData" id="imageDataphysio"/>
  <s:hidden name="id" id="id1"> </s:hidden>
  <s:hidden name="actionType" id ="actionType1"/>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Edit Image Canvas</h4>
      </div>
      <div class="modal-body" >
      		<%@ include file="/minipaint/editor.jsp"%>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-primary" onclick="saveEditPhysioImageTemplate();">Update Image</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
    </s:form>
  </div>
</div>	
<br/>