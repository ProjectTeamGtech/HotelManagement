<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="assesmentForms/js/Predefine/assessmentTemplate.js"></script>

<style>
.ppstyle{
padding: 3px 7px;
height: 20px;
font-size: 10px;
}
</style>


<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="#">Assessment Forms</a></li>
			<li class="active">Assessment Template Form</li>
		</ol>
	</div>
</div>

<s:form action="saveAssessmentTemplate" id="physio_form" theme="simple">
<div class="panel panel-primary">
<div class="panel-body">			
<div class="col-lg-12">
	<div class="row">
		<div class="col-lg-2">
		<label style="font-size: 10px;">Patient Name : </label>
		</div>		
		<div class="col-lg-2">
			<s:textfield name="client" id="client" readonly="true" cssClass="form-control" 
				     onclick="showAssessmentPopUp()" data-toggle="modal" data-target="#clientSearch" placeholder = "Enter Patient Name" title="Enter Patient Name"/>
			
			<%-- <s:textfield name="client" id="client" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Patient Name" title="Enter Patient Name" onclick="showEmrPopUp()" data-toggle="modal" data-target="#clientSearch"/>
		 --%></div>
		 
<!-- Modal Client Search-->
<div class="modal fade" id="clientSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Client Search</h4>
      </div>
      <div class="modal-body">
        <%@ include file="/tools/pages/allTemplatePatientList.jsp"%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

		 
		 
		<div class="col-lg-1">
		<label style="font-size: 10px;">Unique Identifying No : </label>
		</div>
		<div class="col-lg-2">
			<s:textfield name="clientId" id="clientId" readonly="true" cssClass="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Unique Identifying No" title="Enter Unique Identifying No"/>
		</div>
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
	<div class="row">
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
	<br/>
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
<div class="panel panel-primary">
<div class="panel-body">			
	<div class="table-responsive">
		<table id="results"	class="table table-hove table-bordered table-striped table-condensed">
			<tr>
			<td>Primary Diagnosis</td>
			<td><input type="text" name="primaryDiagnosisDetails"  id="primaryDiagnosisDetails" class="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Primary Diagnosis" title="Enter Primary Diagnosis"/></td>
			</tr>
			<tr>
			<td>Secondary Diagnosis</td>
			<td><input type="text" name="secondaryDiagnosysDetails"  id="secondaryDiagnosysDetails" class="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Secondary Diagnosis" title="Enter Secondary Diagnosis"/></td>
			</tr>
		</table>
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
</div>

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
				<td style="font-size: 10px;"><input type="text" id="diabetesyn" name="diabetesyn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="diabetesDetail" name="diabetesDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>2</td>
				<td style="font-size: 10px;">Epilepsy</td>
				<td style="font-size: 10px;"><input type="text" id="epilepsyyn" name="epilepsyyn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="epilepsyDetail" name="epilepsyDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>3</td>
				<td style="font-size: 10px;">Heart conditions</td>
				<td style="font-size: 10px;"><input type="text" id="heartConditionyn" name="heartConditionyn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="heartConditionDetail" name="heartConditionDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>4</td>
				<td style="font-size: 10px;">Lung disorders</td>
				<td style="font-size: 10px;"><input type="text" id="lungDisorderyn" name="lungDisorderyn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="lungDisorderDetail" name="lungDisorderDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>5</td>
				<td style="font-size: 10px;">Prostate</td>
				<td style="font-size: 10px;"><input type="text" id="prostateyn" name="prostateyn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="prostateDetail" name="prostateDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>6</td>
				<td style="font-size: 10px;">Carcinoma</td>
				<td style="font-size: 10px;"><input type="text" id="carcinomayn" name="carcinomayn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="carcinomaDetail" name="carcinomaDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>7</td>
				<td style="font-size: 10px;">RA</td>
				<td style="font-size: 10px;"><input type="text" id="rayn" name="rayn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="raDetail" name="raDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>8</td>
				<td style="font-size: 10px;">Osteopaenia/porosis</td>
				<td style="font-size: 10px;"><input type="text" id="osteopaeniayn" name="osteopaeniayn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="osteopaeniaDetail" name="osteopaeniaDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>9</td>
				<td style="font-size: 10px;">Wt stable</td>
				<td style="font-size: 10px;"><input type="text" id="wtStableyn" name="wtStableyn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="wtStableDetail" name="wtStableDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>10</td>
				<td style="font-size: 10px;">Bladder & Bowel</td>
				<td style="font-size: 10px;"><input type="text" id="bladderBowelyn" name="bladderBowelyn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="bladderBowelDetail" name="bladderBowelDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<td></td>
				<td>11</td>
				<td style="font-size: 10px;">Other</td>
				<td style="font-size: 10px;"><input type="text" id="otheryn" name="otheryn" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="otherDetail" name="otherDetail" class="form-control showToolTip ppstyle"/></td>
			</tr>
			</tbody>
		</table>
		
	<div class="form-group">
		<a onclick="addRowGeneralHealth('generalHealthTable')" class="btn btn-primary ppstyle"><i class="fa fa-plus"></i> Add More</a>								
		<a onclick="deleteRowGeneralHealth('generalHealthTable')" class="btn btn-primary ppstyle" ><i class="fa fa-trash-o"></i> Delete Row</a>
	</div>
	</div>
		
	
	<div class="table-responsive" id = "generalHealthdiv">	
		<table class="table table-striped table-hover table-condensed" id = "generalHealthTable1">			
	</table>
	</div>
	
	<div class="table-responsive">
		<table id="results"	class="table table-hove table-bordered table-striped table-condensed">
			<tr>
			<td>Medications</td>
			<td><input type="text" name="medication"  id="medication" class="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Medications" title="Enter Medications"/></td>
			</tr>
			<tr>
			<td>Investigations</td>
			<td><input type="text" name="investigation"  id="investigation" class="form-control showToolTip ppstyle ppstyle" data-toggle = "tooltip" placeholder = "Enter Investigations" title="Enter Investigations"/></td>
			</tr>
		</table>
		
	</div>
	
</div>
</div> -->

<div class="panel panel-primary">
<div class="panel-body">			
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
				<td style="font-size: 10px;"><input type="text" id="generalHealth" name="generalHealth" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td style="font-size: 10px;">Night pain</td>
				<td style="font-size: 10px;"><input type="text" id="nightPain" name="nightPain" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td style="font-size: 10px;">Family history</td>
				<td style="font-size: 10px;"><input type="text" id="familyHistory" name="familyHistory" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>4</td> -->
				<td style="font-size: 10px;">Psycho social</td>
				<td style="font-size: 10px;"><input type="text" id="psychoSocial" name="psychoSocial" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>5</td> -->
				<td style="font-size: 10px;">Surgery</td>
				<td style="font-size: 10px;"><input type="text" id="surgery" name="surgery" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>6</td> -->
				<td style="font-size: 10px;">Accidents</td>
				<td style="font-size: 10px;"><input type="text" id="accident" name="accident" class="form-control showToolTip ppstyle"/></td>
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
	<div class="table-responsive">
		<table class="table table-striped table-hover table-condensed" id = "table2">
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
				<!-- <td></td>
				<td>1</td> -->
				<td></td>
				<td style="font-size: 10px;">Biceps</td>
				<td style="font-size: 10px;"><input type="text" id="bicepRight" name="bicepRight" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="bicepLeft" name="bicepLeft" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td></td>
				<td style="font-size: 10px;">Triceps</td>
				<td style="font-size: 10px;"><input type="text" id="tricepRight" name="tricepRight" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="tricepLeft" name="tricepLeft" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td></td>
				<td style="font-size: 10px;">Brachioradialis</td>
				<td style="font-size: 10px;"><input type="text" id="brachioradialisRight" name="brachioradialisRight" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="brachioradialisLeft" name="brachioradialisLeft" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr><h5><b>NEURAL TENSION</b></h5></tr>
			<tr>
				<!-- <td></td>
				<td>1</td> -->
				<td style="font-size: 10px;">ULNT</td>
				<td>1</td>
				<td style="font-size: 10px;"><input type="text" id="ulnt1Right" name="ulnt1Right" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="ulnt1Left" name="ulnt1Left" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td></td>
				<td>2</td>				
				<td style="font-size: 10px;"><input type="text" id="ulnt2Right" name="ulnt2Right" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="ulnt2Left" name="ulnt2Left" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td></td>
				<td>3</td>				
				<td style="font-size: 10px;"><input type="text" id="ulnt3Right" name="ulnt3Right" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="ulnt3Left" name="ulnt3Left" class="form-control showToolTip ppstyle"/></td>
			</tr>
			</tbody>
		</table>
	</div>
</div>
</div>

</div>
</div>

<div class="row">
 <div class="col-lg-12">
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
				<td style="font-size: 10px;"><input type="text" id="kneeRight" name="kneeRight" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="kneeLeft" name="kneeLeft" class="form-control showToolTip ppstyle"/></td><td></td><td></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td style="font-size: 10px;">Ankle</td>
				<td style="font-size: 10px;"><input type="text" id="ankleRight" name="ankleRight" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="ankleLeft" name="ankleLeft" class="form-control showToolTip ppstyle"/></td><td></td><td></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td style="font-size: 10px;">Babinski</td>
				<td style="font-size: 10px;"><input type="text" id="babinskiRight" name="babinskiRight" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="babinskiLeft" name="babinskiLeft" class="form-control showToolTip ppstyle"/></td><td></td><td></td>
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
				<td style="font-size: 10px;"><input type="text" id="slumpRight" name="slumpRight" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="slumpLeft" name="slumpLeft" class="form-control showToolTip ppstyle"/></td><td></td><td></td>
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
				<td style="font-size: 10px;"><input type="text" id="dorsiflexionRom1" name="dorsiflexionRom1" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="dorsiflexionPain1" name="dorsiflexionPain1" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="dorsiflexionRom2" name="dorsiflexionRom2" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="dorsiflexionPain2" name="dorsiflexionPain2" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td style="font-size: 10px;">+Medial rotation</td>
				<td style="font-size: 10px;"><input type="text" id="medicalRotaionRom1" name="medicalRotaionRom1" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="medicalRotaionPain1" name="medicalRotaionPain1" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="medicalRotaionRom2" name="medicalRotaionRom2" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="medicalRotaionPain2" name="medicalRotaionPain2" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td style="font-size: 10px;">+Neck flexion</td>
				<td style="font-size: 10px;"><input type="text" id="neckFlexionRom1" name="neckFlexionRom1" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="neckFlexionPain1" name="neckFlexionPain1" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="neckFlexionRom2" name="neckFlexionRom2" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="neckFlexionPain2" name="neckFlexionPain2" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>4</td> -->
				<td style="font-size: 10px;">Passive Knee Bend</td>
				<td style="font-size: 10px;"><input type="text" id="passiveKneeBendRom1" name="passiveKneeBendRom1" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="passiveKneeBendPain1" name="passiveKneeBendPain1" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="passiveKneeBendRom2" name="passiveKneeBendRom2" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="passiveKneeBendPain2" name="passiveKneeBendPain2" class="form-control showToolTip ppstyle"/></td>
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
				<td style="font-size: 10px;"><input type="text" id="dizzyy" name="dizzyy" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="dizzyn" name="dizzyn" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>2</td> -->
				<td style="font-size: 10px;">Double Vision</td>
				<td style="font-size: 10px;"><input type="text" id="doubleVisiony" name="doubleVisiony" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="doubleVisionn" name="doubleVisionn" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>3</td> -->
				<td style="font-size: 10px;">Dysarthria</td>
				<td style="font-size: 10px;"><input type="text" id="dysarthriay" name="dysarthriay" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="dysarthrian" name="dysarthrian" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>4</td> -->
				<td style="font-size: 10px;">Dysphagia</td>
				<td style="font-size: 10px;"><input type="text" id="dysphagiay" name="dysphagiay" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="dysphagian" name="dysphagian" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>5</td> -->
				<td style="font-size: 10px;">Drop attacks</td>
				<td style="font-size: 10px;"><input type="text" id="dropAttacky" name="dropAttacky" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="dropAttackn" name="dropAttackn" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>6</td> -->
				<td style="font-size: 10px;">Nystagmus</td>
				<td style="font-size: 10px;"><input type="text" id="nystagmusy" name="nystagmusy" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="nystagmusn" name="nystagmusn" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>7</td> -->
				<td style="font-size: 10px;">Nausea</td>
				<td style="font-size: 10px;"><input type="text" id="nauseay" name="nauseay" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="nausean" name="nausean" class="form-control showToolTip ppstyle"/></td>
			</tr>
			<tr>
				<!-- <td></td>
				<td>8</td> -->
				<td style="font-size: 10px;">Numbness</td>
				<td style="font-size: 10px;"><input type="text" id="numbnessy" name="numbnessy" class="form-control showToolTip ppstyle"/></td>
				<td style="font-size: 10px;"><input type="text" id="numbnessn" name="numbnessn" class="form-control showToolTip ppstyle"/></td>
			</tr>
			
			</tbody>
		</table>
	</div>		
</div>
</div>
</div> 
 </div> 

<div class="row">
 <div class="panel panel-primary">
<div class="panel-body">
	<div class="col-lg-2"></div>						
		<div class="col-lg-8">
			<%@ include file="/minipaint/editor.jsp"%>
		</div>
</div>	
</div>
</div> 


<div class="row">
		<div class="col-lg-3 col-md-2"></div>
		<div class="col-lg-6 col-md-8">
			<s:submit cssClass="btn btn-primary" value="Save"/>
			<s:reset cssClass="btn btn-primary" />			
		</div>
		<div class="col-lg-3 col-md-2"></div>
	</div>
	
	
</s:form>
<br/>