<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YUVICARE</title>
<script type="text/javascript" src="diarymanagement/js/marketing.js"></script>
<SCRIPT type="text/javascript">
 
 $(window).on('load', function() {
	$('#newloaderPopup').modal('hide');
});
       $(document).ready(function() {
    	   $('#newloaderPopup').modal('show');
		$("#bcg").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#opv0").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hep_b1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwp1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#ipv1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hep_b2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hib1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#rotavirus1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#pcv1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwp2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#ipv2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hib2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#rotavirus2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#pcv2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwp3").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#ipv3").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hib3").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#rotavirus3").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#pcv3").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#opv1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hepb3").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#opv2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#mmr1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#typhoid_conjugate").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#vaccine").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hepa1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#mmr2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#varicella1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#pcvbooster").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwpb1dtapb1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#ipvb1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hibb1").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hepa2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#boosteroftyphoid").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#conjugatevaccine").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwpb2dtapb2").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#tdaptd").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hpv").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		
		
		$(".lokesh").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			maxDate : '0',
			changeMonth : true,
			changeYear : true

		});
		
		$(".lokeshdue").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			
			changeMonth : true,
			changeYear : true

		});
		
		$("#opv0dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hep_b1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwp1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#ipv1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hep_b2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hib1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#rotavirus1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#pcv1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwp2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#ipv2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hib2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#rotavirus2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#pcv2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwp3dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#ipv3dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hib3dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#rotavirus3dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#pcv3dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#opv1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hepb3dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#opv2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#mmr1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#typhoid_conjugatedt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#vaccinedt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hepa1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#mmr2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#varicella1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#pcvboosterdt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwpb1dtapb1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#ipvb1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hibb1dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hepa2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#boosteroftyphoiddt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#conjugatevaccinedt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#dtwpb2dtapb2dt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#tdaptddt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#hpvdt").datepicker({
			dateFormat : 'dd-mm-yy',
			yearRange: yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
	});
    
    </SCRIPT>

<style>
/* select>option{
min-height:20px !important;
}
 @media print
{
.dd{
padding-bottom:15px;
}
body{
font-size:5px !important;
}
} */
.hideonload{
	display: none;
}
@media print {
	.hideonload{
		display: none !important;
	}
}
</style>
</head>

<body>

	<select
		onchange="openimmun(this.value, <s:property value='clientId'/>)"
		class="hidden" style="width: 20%; height: 40px;">
		<s:if test="vacine_type==0">
			<option value="0" selected>Child</option>
		</s:if>
		<s:else>
			<option value="0">Child</option>
		</s:else>

		<s:if test="vacine_type==1">
			<option value="1" selected>Gynic</option>
		</s:if>
		<s:else>
			<option value="1">Gynic</option>
		</s:else>
	</select>
	<div class="container" id="printableArea">
		<%-- <p><span style="background-color:red"></span> helo</p> --%>
		<s:if test="vacine_type==1">
			<h2 align="center">Antenatal Timetable</h2>
			<h3 align="center">Antenatal Recommended Routine Use</h3>

		</s:if>
		<s:elseif test="vacine_type==2">
			<h2 align="center">Nephrology Timetable</h2>
			<h3 align="center">Nephrology recommended routine use</h3>

		</s:elseif>
		<s:else>
			<h2 align="center">Table I. IAP Immunization Timetable</h2>
			<h3 align="center" style="color: black;">I. IAP recommended vaccines for routine use</h3>
		</s:else>

		<div class="row" align="center">
			<b>Patient Name: <s:property value="fullname" /> &nbsp; &nbsp;
				UHID: <s:property value="abrivationid" /><br> <s:if
					test="vacine_type==1">LMP :<s:property value="lmpdate" />
				</s:if> <s:else>Date of Birth: <s:property value="dob" />
				</s:else>
			</b>
		</div>

		<div align="right" style="padding: 5px;">
			<s:if test="!bookedstatus">
				<input type="button" class="btn btn-warning hidden-print hidden"
					value="Apporve Schedule"
					onclick="scheduleApmt(<s:property value='vacine_type'/>)">
			</s:if>
			<s:else>

				<input type="button" class="btn btn-success" value="Apporved ">
			</s:else>
		</div>


		<div class="row immunrowhead align-middle ">
			<s:hidden name="clientId" id="clientid"></s:hidden>

			<s:if test="vacine_type==1">
				<%-- <div
					class="col-md-2 displayinlineprint immuheadpadingprint colmdbirthprint">
					<b class="colorwhitprint">Gestation <br> <span
						class="hidden-print"></span></b>
				</div> --%>
				
				<div
					class="col-md-2 immuheadpading displayinlineprint colmdvaccinesprint">
					<b class="colorwhitprint">Gestation <br> <span
						class="hidden-print"></span></b>
				</div>
				 <div
					class="col-md-2 immuheadpading displayinlineprint colmdvaccinesprint">
					<b class="colorwhitprint">Aims of Visit </b>
				</div> 
				
				<div
					class="col-md-3 immuheadpadinga displayinlineprint colmddobprint">

					<b class="pull-right immudofrightmar colorwhitprint hidden-print">
						<%-- LMP <s:property value="lmpdate"/> --%>
					</b>
					<div class="duedatetextirfa" style="margin-top: 10px">Due Date</div>
					<div class="duedatetextirfb" style="margin-top: 10px">Actual Date</div>
				</div>

			</s:if>
			<s:else>
				<div
					class="col-md-2 displayinlineprint immuheadpadingprint  colmdbirthprint">
					<b class="colorwhitprint">Age <br> <span
						class="hidden-print">(completed weeks/months/years)</span></b>
				</div>
				<div
					class="col-md-2 immuheadpading displayinlineprint colmdvaccinesprint">
					<p></p>
					<b class="colorwhitprint">Vaccines</b>
				</div>

				<div
					class="col-md-3 immuheadpadinga displayinlineprint colmddobprint">

					<b class="pull-right immudofrightmar colorwhitprint hidden-print">
						<%-- Date of Birth <s:property value="dob"/> --%>
					</b>
					<div class="duedatetextirfa" style="margin-top: 10px">Due Date</div>
					<div class="duedatetextirfb" style="margin-top: 10px">Given on</div>
				</div>

			</s:else>

			 <div class="col-md-3 immuheadpading displayinlineprint colorwhitprint">
				
				<b class="colorwhitprint">Remarks</b>
			</div>
			

		</div>




		<div id="switchVisibleimmunizationindi1">
			<s:iterator value="vacinlist">
				<div onclick='switchVisibleVaccinationData(<s:property value="id"/>);'
					class="row immunrowhitbg align-middle"
					id='ddiv_<s:property value="id"/>'>
					
					<input type="hidden" class="aaclass" value="<s:property value="id"/>">
					
					<div class="col-md-2 displayinlineprint colmdbirthprint">
						<s:property value="duration" />
					</div>
					<div class="col-md-2 displayinlineprint colmdvaccinesprint"
						style="padding: 4px;">
						<div class="immutextinline">
							<s:property value="vacinname" />
						</div>
					</div>
					<div class="col-md-3 displayinlineprint colmddobprint">
						<div class="immutextboxblanknew ">
							<span id='spanbcg<s:property value="id"/>'><s:property
									value="scheduledate" />
								<%-- <s:property value="bcg"/> --%></span>
						</div>
						<div class="immutextboxblanknew ">
							<span id='spanbcgdt<s:property value="id"/>'>
								<%-- <s:property value="bcgdt"/> --%>
								<s:property value="vacine_givendate" />
							</span>
						</div>


					</div>
					<div class="col-md-3 ">
						<div class="form-group">
							<div class="">
								<span id='spanremark1<s:property value="id"/>'><s:property
										value="vacine_remark" /> </span>
							</div>
						</div>
					</div>

				</div>
				
				<!-- <div onclick="switchVisibleimmunizationindi();" -->
				<div class="row immunrowhitbg align-middle hideonload"
					id='ddiv1_<s:property value="id"/>'>
					<div class="col-md-2 displayinlineprint " onclick='switchVisibleVaccinationData(<s:property value="id"/>);'>
						<div class="immutextinline">
							<s:property value="duration" />
						</div>
					</div>
					<div class="col-md-2 displayinlineprint" onclick='switchVisibleVaccinationData(<s:property value="id"/>);'>
						<div class="immutextinline">
							<s:property value="vacinname" />
							<br> <span style="font-size: 12px">Depends On:<s:property
									value="dependent_name" /></span><br>
							<s:property value="vacine_info" />

						</div>
					</div>
					<div class="col-md-3 displayinlineprint">
						<div onclick="switchVisibleimmunizationindiinput();"
							class="input-group date immutextbox" id="dp3">
							<s:if test="!bookedstatus">
								<span> <input type="text" class="form-control lokeshdue"
									value="<s:property value="scheduledate"/>"
									onchange="changeDuedate('<s:property value="id"/>','<s:property value="clientId"/>',this.value)"></span>
							</s:if>
							<s:else>
								<span><s:property value="scheduledate" /></span>
							</s:else>

						</div>
						<div onclick="switchVisibleimmunizationindiinput();"
							class="input-group date immutextbox" id="dp3">
							<s:if test="vacine_iscompulsary==1">
								<input class="form-control lokesh"
									onchange="savevacinationjson('<s:property value="id"/>','<s:property value="vacinname"/>',this.value,'<s:property value="clientId"/>','<s:property value="scheduledate"/>');"
									onclick="checkgiven(<s:property value="dependant_given"/>)"
									type="text" id='<s:property value="id"/>'
									value='<s:property value="vacine_givendate"/>'
									style="background-color: #fff7d2">
							</s:if>
							<s:else>
								<input class="form-control lokesh"
									onchange="savevacinationjson('<s:property value="id"/>','<s:property value="vacinname"/>',this.value,'<s:property value="clientId"/>','<s:property value="scheduledate"/>');"
									onclick='checkgiven(<s:property value="dependant_given"/>)'
									type="text" id="<s:property value="id"/>"
									value='<s:property value="vacine_givendate"/>'>
							</s:else>
							<!-- <span class="input-group-addon btn"><i class="glyphicon glyphicon-calendar" id="butt"></i></span> -->
						</div>
					</div>


					<div class="col-md-3 displayinlineprint">
						<div class="form-group">
							<textarea onclick="switchVisibleimmunizationindiinput();"
								onchange="updateremarkvacinationjson('<s:property value="id"/>','<s:property value="clientId"/>',this.value)"
								class="form-control" rows="5"
								id="remark1<s:property value='id'/>" name="remark1"
								placeholder="Remarks"><s:property
									value="vacine_remark" /></textarea>
							<!-- <textarea onclick="switchVisibleimmunizationindiinput();"  onchange="saveimmurtizationdata(this.value,'remark1',1)"  class="form-control" rows="5" id="remark1" name="remark1" placeholder="Remarks"></textarea> -->
						</div>
					</div>
					<div class="col-md-2 ">
						<div class="form-group">
							<select class="" onclick="switchVisibleimmunizationindiinput();"
								onchange="settoRemark(this.value,<s:property value='id'/>,'<s:property value="clientId"/>')">
								<option value="Mandatory Vaccine">Mandatory</option>
								<option value="Non Mandatory Vaccine">Non Mandatory</option>

							</select>

						</div>
					</div>
				</div>
			</s:iterator>
		</div>
		<div id="switchVisibleimmunizationindi2">
			<%-- <s:iterator value="vacinlist">
				<div onclick="switchVisibleimmunizationindi();"
					class="row immunrowhitbg align-middle"
					id='ddiv1<s:property value="id"/>'>
					<div class="col-md-2 displayinlineprint ">
						<s:property value="duration" />
					</div>
					<div class="col-md-2 displayinlineprint">
						<div class="immutextinline">
							<s:property value="vacinname" />
							<br> <span style="font-size: 12px">Depends On:<s:property
									value="dependent_name" /></span><br>
							<s:property value="vacine_info" />

						</div>
					</div>
					<div class="col-md-3 displayinlineprint">
						<div onclick="switchVisibleimmunizationindiinput();"
							class="input-group date immutextbox" id="dp3">
							<s:if test="!bookedstatus">
								<span> <input type="text" class="form-control lokeshdue"
									value="<s:property value="scheduledate"/>"
									onchange="changeDuedate('<s:property value="id"/>','<s:property value="clientId"/>',this.value)"></span>
							</s:if>
							<s:else>
								<span><s:property value="scheduledate" /></span>
							</s:else>

						</div>
						<div onclick="switchVisibleimmunizationindiinput();"
							class="input-group date immutextbox" id="dp3">
							<s:if test="vacine_iscompulsary==1">
								<input class="form-control lokesh"
									onchange="savevacinationjson('<s:property value="id"/>','<s:property value="vacinname"/>',this.value,'<s:property value="clientId"/>','<s:property value="scheduledate"/>');"
									onclick="checkgiven(<s:property value="dependant_given"/>)"
									type="text" id='<s:property value="id"/>'
									value='<s:property value="vacine_givendate"/>'
									style="background-color: #fff7d2">
							</s:if>
							<s:else>
								<input class="form-control lokesh"
									onchange="savevacinationjson('<s:property value="id"/>','<s:property value="vacinname"/>',this.value,'<s:property value="clientId"/>','<s:property value="scheduledate"/>');"
									onclick='checkgiven(<s:property value="dependant_given"/>)'
									type="text" id="<s:property value="id"/>"
									value='<s:property value="vacine_givendate"/>'>
							</s:else>
							<!-- <span class="input-group-addon btn"><i class="glyphicon glyphicon-calendar" id="butt"></i></span> -->
						</div>
					</div>


					<div class="col-md-3 displayinlineprint">
						<div class="form-group">
							<textarea onclick="switchVisibleimmunizationindiinput();"
								onchange="updateremarkvacinationjson('<s:property value="id"/>','<s:property value="clientId"/>',this.value)"
								class="form-control" rows="5"
								id="remark1<s:property value='id'/>" name="remark1"
								placeholder="Remarks"><s:property
									value="vacine_remark" /></textarea>
							<!-- <textarea onclick="switchVisibleimmunizationindiinput();"  onchange="saveimmurtizationdata(this.value,'remark1',1)"  class="form-control" rows="5" id="remark1" name="remark1" placeholder="Remarks"></textarea> -->
						</div>
					</div>
					<div class="col-md-2 ">
						<div class="form-group">
							<select class="" onclick="switchVisibleimmunizationindiinput();"
								onchange="settoRemark(this.value,<s:property value='id'/>,'<s:property value="clientId"/>')">
								<option value="Mandatory Vaccine">Mandatory</option>
								<option value="Non Mandatory Vaccine">Non Mandatory</option>

							</select>

						</div>
					</div>
				</div>
			</s:iterator> --%>
		</div>




		<s:if test="vacine_type==1">
		</s:if>
		<s:else>


			<%-- <table class="hidden">
<tr>
<td>Age </td>
<td>Vaccine</td>
<td>Due Date</td>
<td>Given On</td>
<td>Remark</td>
</tr>
<s:iterator value="vacinlist">
<tr>
<td><s:property value="duration"/> </td>
<td><s:property value="vacinname"/> </td>
<td><s:property value="duration"/> </td>
<td><s:property value="duration"/> </td>
<td><s:property value="duration"/> </td>
</tr>
</s:iterator>
</table>

 --%>


			<h3 align="left">II. IAP recommended vaccines for High-risk*
				children (Vaccines under special circumstances) #:</h3>
			<h4>
				<ul>
					<li>Influenza Vaccine</li>
					<li>Meningococcal Vaccine</li>
					<li>Japanese Encephalitis Vaccine</li>
					<li>Cholera Vaccine</li>
					<li>Rabies Vaccine</li>
					<li>Yellow Fever Vaccine</li>
					<li>Pneumococcal Polysaccharide vaccine (PPSV 23)</li>
				</ul>
			</h4>


			<h3 align="left">High-risk category of children:</h3>
			<h4>
				<ul>
					<li>Congenital or acquired immunodeficiency (including HIV
						infection),</li>
					<li>Chronic cardiac, pulmonary (including asthma if treated
						with prolonged high-dose oral corticosteroids), hematologic, renal
						(including nephrotic syndrome), liver disease and diabetes
						mellitus</li>
					<li>Children on long term steroids, salicylates,
						immunosuppressive or radiation therapy</li>
					<li>Diabetes mellitus, Cerebrospinal fluid leak, Cochlear
						implant, Malignancies,</li>
					<li>Children with functional/ anatomic asplenia/ hyposplenia</li>
					<li>During disease outbreaks</li>
					<li>Laboratory personnel and healthcare workers</li>
					<li>Travelers</li>
					<li>Children having pets in home</li>
					<li>Children perceived with higher threat of being bitten by
						dogs such as hostellers, risk of stray dog menace while going
						outdoor.</li>

				</ul>
			</h4>

		</s:else>

		<!--  <a class="immunizsavebtn" href="#">Save </a> -->

		<!-- <a class="immunizprintbtn hidden-print" href="#"
			onclick="printDiv5('printableArea')"> Print</a> <br> -->
		
		<a class="immunizprintbtn hidden-print" href="#"
			onclick="printFullPage()"> Print</a> <br>
		
		<br>
		<br>
		<br>


	</div>


	<!--Loader  -->
	<div class="modal fade"
		style="background: rgba(255, 255, 255, 0.93); z-index: 99"
		id="newloaderPopup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="">
				<div class="modal-body text-center">
					<img src="common/images/hourglass1.gif"
						class="img-responsive middlelogo"
						style="margin-left: auto; margin-right: auto;"></img>

				</div>
			</div>
		</div>
	</div>
	<!-- End Loader  -->


	<script>
function checkgiven(val){
	if(val==false){
		alert("You've missed the previous vaccination ");
	}
}



</script>

	<script>
	function printDiv5(divName) {
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
	}
	</script>

	<script>
    function openimmun(val,patientId){
    	openPopup('newimmunizationchartSlotAvailable?clientId='+patientId+'&type='+val);
    }
    
    function settoRemark(val, id, clientid){
    	var c=document.getElementById('remark1'+id).value;
    	val= val +' '+ c;
    	document.getElementById('remark1'+id).value=val;
    	document.getElementById('spanremark1'+id).innerHTML=val;
    	
    	updateremarkvacinationjson(id,clientid,val);
    }
    </script>

	<script src="common/chosen_v1.1.0/chosen.jquery.js"
		type="text/javascript"></script>
	<script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>

</body>

</html>