<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admission Summary Form</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link href="_assets/newtheme/css/main.css" rel="stylesheet" />
<link href="_assets/css/priscription/Notification.css" rel="stylesheet" />
<link href="_assets/css/priscription/hospitalresponsive.css"
	rel="stylesheet" />
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> -->

<script type="text/javascript" src="ipd/js/admissionform.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>
<script type="text/javascript" src="ipd/js/gynicform.js"></script>
<script type="text/javascript" src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<style>
.viewsetimagse {
	width: 68%;
	margin-left: auto;
	margin-right: auto;
	margin-top: 15px;
}

.chosen-container {
	font-size: 14px;
	border: 1px solid #ddd;
}

.setbackcolor {
	background-color: beige;
}

.adformback {
	border: 1px solid;
	padding: 10px 0px 0px;
	margin-top: 0px;
	width: 98%;
	margin-left: 9px;
}

.form-horizontal .control-label {
	padding-top: 7px;
	margin-bottom: 0px;
	text-align: right;
	font-size: 12px;
}

.marbot15 {
	margin-bottom: 15px;
}

.martop15 {
	margin-top: 15px;
}

.diagtitle {
	background-color: #000;
	color: #FFF;
	padding: 10px;
	font-weight: normal;
	padding-top: 12px !important;
}

.bednosele {
	width: 10%;
	margin-top: -40px;
}

.textareaheight {
	height: 30px !important;;
}

.paddtop {
	padding: 0px 0px 14px 2px;
}

.widthtabhedth1 {
	width: 22%;
}

.widthtabhedth2 {
	width: 6%;
}

.backgrey {
	background-color: rgba(149, 222, 91, 0.19);
}

.mar0 {
	margin-top: 23px;
}

.hhmm {
	width: 120%;
}

.pnameback {
	background-color: #f5f5f5;
	margin-top: -7px;
	padding-top: 5px;
}

.admissionbackgreen {
	width: 205px;
}

.minbarheaight {
	min-height: 65px;
}

.panel-primary {
	border-color: #339966;
}

.checkbox-custom, .checkbox-custom-alt {
	padding-left: 5px !important;
}

ul, ol {
	margin-top: 0;
	margin-bottom: 8.5px;
	list-style: none;
	padding: 0px;
}

.checkbox-custom>i, .checkbox-custom-alt>i {
	margin-left: 0px !important;
}

@media print {
	.gynicprint{
		border: none !important;
	}
}

.checkbox-custom-alt > i {
    border: inset !important;
    /* border-color: black !important; */
}
</style>
<script type="text/javascript">
	$(document).ready(function() {

		$("#date1").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#date2").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#date3").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#date4").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#lmp").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#tt_dose1").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#tt_dose2").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#influenza_vaccine").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		$("#dating_usg_date").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		var patientId = document.getElementById("clientid").value;

		/* var d=document.getElementById("clientid").value;
		if(d==0 || d==''){
			showPopUp();
		}  else {
		    getIpdClientInfo(d);
		}
		 */
		calculatedatingscan();
	});

	bkLib.onDomLoaded(function() {

		//new nicEditor().panelInstance('declarationNotes');
		//new nicEditor({maxHeight : 250}).panelInstance('admissiondetails');
		/*  new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('parity_abortion_notes');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('usg_report');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 150
		}).panelInstance('surgical_ho');

		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('diagonosis');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('additonal_notes');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('treatment_advice'); */
		
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('investigation_advice');
		
		$('.nicEdit-panelContain').parent().width('auto');
		$('.nicEdit-panelContain').parent().next().width('auto');

		$('.nicEdit-main').width('100%'); 
		// $('.nicEdit-main').height('480px');
	});
	
	
	var diaryuserId = 0;
	var editcondition_id = 0;
	function addtreatmentadvice(cid,pid,conid){
		patientId = cid;
		diaryuserId = pid;
		editcondition_id = conid;
		treatmentadvc=0;
		repeatePriscDateAjax(cid,pid,conid);
		
	}
</script>

</head>
<body>



	<s:form action="savenewgynicformCommonnew?action=0" theme="simple"
		id="ipdadmissionfrm">

		<s:hidden name="id" id="editselectedid" />
		<s:hidden name="visit_reason_ids" id="visit_reason_ids" />
		<s:hidden id="clientid" name="clientid" />
		<input type="hidden" id="disbedid" value="1" />
		<s:hidden name="treatmentepisodeid" id="treatmentepisodeid" />
		<s:hidden name="conditionid" id="conditionid" />
		<s:hidden name="formtype" id="formtype" />
		<s:hidden  id="selectedid" value="0" />
		<s:hidden name="commencing"></s:hidden>
		<s:hidden name="practitionerid" id="practitionerid"></s:hidden>
		<s:hidden name="lastappointmentid" id="lastappointmentid"></s:hidden>
		<s:hidden id="isnewgynicform11"></s:hidden>
		<style>
h3, .h3 {
	margin-top: 9px !important;
	margin-bottom: 9px !important;
}

.textprimegreen {
	background-color: #339966;
	color: #fff;
}

.textprime {
	background-color: rgba(102, 153, 204, 0.85) !important;
	color: #fff;
}

hr {
	margin-top: 4px;
	margin-bottom: 5px;
	border: 0;
	border-top: 1px solid #eee;
}

.secconbtn {
	width: 100%;
	background-color: #f5f5f5;
	color: #222222 !important;
	text-align: left;
	font-size: 12px;
	height: 24px !important;
}

.sebaclcons {
	background-color: rgb(246, 246, 246) !important;
	text-shadow: none;
	color: #222 !important;
	text-align: left;
	font-size: 12px;
}

.table>thead>tr>th {
	background-color: #f5f5f5;
	color: #222;
}

.savebigbtn {
	width: 13%;
	height: 61px !important;
	font-size: 20px;
	background-color: #339966 !important;
	margin-bottom: 15px;
}

h4, .h4, h5, .h5, h6, .h6 {
	margin-top: 3.5px;
	margin-bottom: 3.5px;
}

.form-control {
	border: 1px solid #ddd
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	border: 1px solid #ddd;
}

.lighbdsub {
	background-color: #f5f5f5;
	margin: 0px;
	padding: 5px 25px 5px 5px;
}

.form-group {
	margin-bottom: 10px;
	margin-top: 5px;
	border: none !important;
}

</style>



		<div class="row">
			
			<div class="col-lg-12 col-md-12 col-xs-12 textprimegreen hidden-print">
				<div class="col-lg-6 col-xs-12 col-md-6 text-left"
					style="padding-left: 0px;">
					<h3 class="hidden">
						<s:property value="clinicName" />
					</h3>
				</div>
				<div class="col-lg-6 col-xs-6 col-md-6 text-right hidden-xs"
					style="padding-right: 0px;">
					<h3>Obstretics and Gynaecology Assessment Form</h3>
				</div>
			</div>

			<div class="col-lg-12 col-md-12 col-xs-12 hidden-print"
				style="padding-top: 5px; padding-bottom: 5px;">
				<span style="color: brown;">IMPORTANT: Please completed all
					required field <font color="red">*</font>
				</span>
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12 textprime hidden-print">
				<h5>PERSONAL DETAILS</h5>
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12 hidden-print"
				style="padding: 0px; background-color: rgba(245, 245, 245, 0.95);">

				<div class="col-lg-12 col-xs-12 col-md-12 ">


					<div class="col-lg-4 col-xs-4 col-md-4 ">

						<div class="heightdiva"></div>
						<b>Patient Name: - </b>
						<s:property value="client" />
						,
						<s:property value="address" />
						,
						<s:property value="mobNo" />
						<div class="heightdiva"></div>

					</div>


					<div class="col-lg-6 col-xs-6 col-md-6 ">

						<div class="tabbable-panel">
							<div class="tabbable-line">
								<ul class="nav nav-tabs ">

									<s:if test="formtype==4">
										<li class="active"><a href="#"
											onclick="switchgynicfomr(4)" data-toggle="tab"> <b>
													Obstetrics</b>
										</a></li>
										<li class="hidden"><a href="#"
											onclick="switchgynicfomr(1)" data-toggle="tab"> <b>
													Obstetrics</b>
										</a></li>
										<li class="hidden"><a href="#" onclick="switchgynicfomr(2)"
										data-toggle="tab"> <!-- <a href="#tab_default_2" onclick="switchgynicfomr(2)"  data-toggle="tab"> -->
											<b>Gynecology</b>
									</a></li>
									   <li class="hidden"><a href="#" onclick="switchgynicfomr(3)"
										data-toggle="tab"> <b>Infertility </b></a></li>
									</s:if>
									<s:elseif test="formtype==2" >
										
										<li ><a href="#"
											onclick="switchgynicfomr(1)" data-toggle="tab"> <b>
													Obstetrics</b>
										</a></li>
										<li class="active"><a href="#" onclick="switchgynicfomr(2)"
										data-toggle="tab"> <!-- <a href="#tab_default_2" onclick="switchgynicfomr(2)"  data-toggle="tab"> -->
											<b>Gynecology</b>
									</a></li>
									   <li><a href="#" onclick="switchgynicfomr(3)"
										data-toggle="tab"> <b>Infertility </b></a></li>
									 
									</s:elseif>
									<s:else>
									     	<li ><a href="#"
											onclick="switchgynicfomr(1)" data-toggle="tab"> <b>
													Obstetrics</b>
										</a></li>
										<li ><a href="#" onclick="switchgynicfomr(2)"
										data-toggle="tab"> <!-- <a href="#tab_default_2" onclick="switchgynicfomr(2)"  data-toggle="tab"> -->
											<b>Gynecology</b>
									</a></li>
									   <li class="active"><a href="#" onclick="switchgynicfomr(3)"
										data-toggle="tab"> <b>Infertility </b></a></li>
									</s:else>
									
								</ul>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="" id="">

				

				

				
				<div class="tab-content" style="min-height: 50px !important;">
					<div class="tab-pane active" id="tab_default_1">
						<input type="hidden" id="newgynicform1" value="1">

						<div class="col-lg-12 col-xs-12 col-md-12 textprime">




							<h5>REASON FOR VISIT</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">



							<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
								<article>
									<div class="scrolltable scrolltableheight">
										<ul class="vertical default_list" id="">
										
										<s:iterator value="reasonVisitList">
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('<s:property/>')"><i></i>
													<s:property/> </label></li>
										</s:iterator>
										
											<!-- <li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('Routine Follow up')"><i></i>
													Routine Follow up</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('Amenorrhoea')"><i></i>
													Amenorrhoea</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('UPT +ve')"><i></i> UPT +ve</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('Pain in lower abdomen')"><i></i>
													Pain in lower abdomen</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onclick="openVisitPopup('Nausea')"><i></i> Nausea</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('Vomiting')"><i></i>
													Vomiting</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('Nausea + Vomiting')"><i></i>
													Nausea + Vomiting</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('Bleeding PV')"><i></i>
													Bleeding PV</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('Burning Micturition')"><i></i>
													Burning Micturition</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('White discharge PV')"><i></i>
													White discharge PV</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('Bad Obstetric History')"><i></i>
													Bad Obstetric History</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													name="reasonforvisita" type="checkbox"
													onClick="openVisitPopup('Others')"><i></i> Others</label></li> -->
										</ul>
									</div>
								</article>
							</div>
							<div class="col-lg-7 col-md-7 col-xs-7 col-sm-7"
								style="padding: 0px;">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
									style="padding: 0px;">
									<div class="scrolltable" id="scrolltable" style="overflow-y: scroll;height: 200px;">
										<s:if test="id!=0">
											<s:iterator value="allVisitReasonList">
												<h5 style="margin-bottom: 5px;margin-top: 5px;"><b><s:property value="reasonvisit" /></b>&nbsp;&nbsp;<a href="#" data-toggle="modal" data-target="#rvisit"><i class="fa fa-pencil"></i></a></h5>
												<p style="margin: 0px;">Since :<s:property value="since"/></p>
												<p style="margin: 0px;">Note :<s:property value="notes"/></p>
												<s:if test="perceives==1">
													<p style='margin: 0px;'>Perceives</p>
												</s:if>
												<s:if test="notperceives==1">
													<p style='margin: 0px;'>Not Perceives</p>
												</s:if>
												<s:if test="decreased==1">
													<p style='margin: 0px;'>Decreased</p>
												</s:if>
											</s:iterator>
										</s:if>
											
									</div>
								</div>

							</div>

							<div class="col-lg-3 col-md-3 col-xs-2 col-sm-3">
								<div class="scrolltable scrolltableheightleft" style="direction: inherit !important;">
									<h5 class="lighbdsub textalignleft">
										<b>High Risk Factor</b>
									</h5>
									<ul class="vertical default_list"  id="">
										<li>
											<label class="checkbox checkbox-custom-alt m-0 mt-5">
												<s:if test="hrf_gdm==1">
									             	<input type="checkbox" checked="checked" name="hrf_gdm" >
									             </s:if>
									             <s:else>
									             	<input type="checkbox" name="hrf_gdm" >
									             </s:else>
												<i></i>
												GDM
											</label>
										</li>
										<li>
											<label class="checkbox checkbox-custom-alt m-0 mt-5">
												<s:if test="hrf_pih==1">
									             	<input type="checkbox" checked="checked" name="hrf_pih" >
									             </s:if>
									             <s:else>
									             	<input type="checkbox" name="hrf_pih" >
									             </s:else>
												<i></i>
												PIH
											</label>
										</li>
										<li>
											<label class="checkbox checkbox-custom-alt m-0 mt-5">
												<s:if test="hrf_boh==1">
									             	<input type="checkbox" checked="checked" name="hrf_boh" >
									             </s:if>
									             <s:else>
									             	<input type="checkbox" name="hrf_boh" >
									             </s:else>
												<i></i>
												BOH
											</label>
										</li>
										<li>
											<label class="checkbox checkbox-custom-alt m-0 mt-5">
												<s:if test="hrf_hypothyroid==1">
									             	<input type="checkbox" checked="checked" name="hrf_hypothyroid" >
									             </s:if>
									             <s:else>
									             	<input type="checkbox" name="hrf_hypothyroid" >
									             </s:else>
												<i></i>
												HYPOTHYROID
											</label>
										</li>
										<li>
											<label class="checkbox checkbox-custom-alt m-0 mt-5">
												<s:if test="hrf_hyperthyroid==1">
									             	<input type="checkbox" checked="checked" name="hrf_hyperthyroid" >
									             </s:if>
									             <s:else>
									             	<input type="checkbox" name="hrf_hyperthyroid" >
									             </s:else>
												<i></i>
												HYPERTHYROID
											</label>
										</li>
										<li>
											<label class="checkbox checkbox-custom-alt m-0 mt-5">
												 <s:if test="hrf_prev_lscs==1">
									             	<input type="checkbox" checked="checked" name="hrf_prev_lscs" >
									             </s:if>
									             <s:else>
									             	<input type="checkbox" name="hrf_prev_lscs" >
									             </s:else>
												<i></i>
												PREV LSCS
											</label>
										</li>
										<li>
											<label class="checkbox checkbox-custom-alt m-0 mt-5">
												<s:if test="hrf_rh_negative==1">
									             	<input type="checkbox" checked="checked" name="hrf_rh_negative" >
									             </s:if>
									             <s:else>
									             	<input type="checkbox" name="hrf_rh_negative" >
									             </s:else>
												<i></i>
												RH NEGATIVE
											</label>
										</li>
										<!-- <a href="#" data-toggle="collapse" data-target="#hrf_othertext" style="color: #000;"> -->
										<li>
											<label class="checkbox checkbox-custom-alt m-0 mt-5">
												<s:if test="hrf_other==1">
									             	<input type="checkbox" onclick="setcollapsOrNot('hrf_othertext',this.checked)" checked="checked" name="hrf_other" >
									             </s:if>
									             <s:else>
									             	<input type="checkbox" onclick="setcollapsOrNot('hrf_othertext',this.checked)" name="hrf_other" >
									             </s:else>
												<i></i>
												OTHER
											</label>
										</li>
										<!-- </a> -->
											
										
										<s:if test="hrf_other==1">
											<li id="hrf_othertext" class="collapse in">
												<div class="form-group">
													<s:textfield cssClass="form-control" name="hrf_otherval" ></s:textfield>
												</div>
											</li>
										</s:if>
										<s:else>
											<li id="hrf_othertext" class="collapse">
												<div class="form-group">
													<s:textfield cssClass="form-control" name="hrf_otherval" ></s:textfield>
												</div>
											</li>
										</s:else>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>OBSTRETIC HISTORY</h5>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style="margin-bottom: 15px; border-bottom: 1px dashed #ddd; padding: 0px 0px 10px 0px;">
								<div class="col-lg-2 col-md-2 col-xs-12">
									<form>
												<label class="">
													<s:if test="nulligravida==1">
									             		<input type="checkbox" checked="checked" name="nulligravida" >
										             </s:if>
										             <s:else>
										             	<input type="checkbox" name="nulligravida" >
										             </s:else>
													<i></i> Nulligravida
												</label> 
												<label class="">
													<s:if test="current_pregnent==1">
									             		<input type="checkbox" checked="checked" name="current_pregnent" >
										             </s:if>
										             <s:else>
										             	<input type="checkbox" name="current_pregnent" >
										             </s:else>
													<i></i> 
													Currently Pregnant
												</label> 
												<label class="">
													<s:if test="iud==1">
									             		<input type="checkbox" checked="checked" name="iud" >
										             </s:if>
										             <s:else>
										             	<input type="checkbox" name="iud" >
										             </s:else>
													<i></i> 
													IUD
												</label>
									</form>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-6">
									<div class="form-group">
										<lable>Live Boys</lable>
										<s:textfield name="live_boys"
											onchange="addObsRowNew(this.value,'Boys')" 
											cssClass="form-control" />
									</div>

								</div>
								<div class="col-lg-2 col-md-2 col-xs-6">

									<div class="form-group">
										<lable>Live Girls</lable>
										<s:textfield name="live_girls"
											onchange="addObsRowNew(this.value,'Girls')" 
											cssClass="form-control" />
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-6">

									<div class="form-group">
										<lable>Stillbirths</lable>
										<s:textfield name="stillbirths" 
											onchange="addObsRowNew(this.value,'Stillbirths')"
											cssClass="form-control" />
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-6">

									<div class="form-group">
										<lable>Abortions</lable>
										<%-- <s:textfield name="abortions" 
											onchange="addObsRowNew(this.value,'Abortions')"
											cssClass="form-control" /> --%>
										<s:textfield name="abortions" cssClass="form-control" />
									</div>
								</div>

								<div class="col-lg-2 col-md-2 col-xs-6">
									<div class="form-group">
										<label>Death Children</label>
										<s:textfield name="death_children" 
											onchange="addObsRowNew(this.value,'Death Children')"
											cssClass="form-control" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
									style="margin-bottom: 15px;">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<h5 style="color: brown;">Sequence of Parity/Abortions</h5>
										
											<table id="obstable" style="background-color: rgba(0, 191, 255, 0.12);">
	                        					
	                        						<%int sequenceparitycount=0; %>
	                        					<tbody>
	                        					    <s:iterator value="gynicobsList">
	                        						<tr>
	                        							<td style="width: 5%;padding-right: 15px;"><input type="number" name="" value="<%=sequenceparitycount+1%>" class="form-control"> <input type="hidden" name="obslist[<%=sequenceparitycount%>].id" value="<s:property value="id"/>" /></td>
	                        							<td style="width: 8%;padding-right: 15px;"><input type="number" name="obslist[<%=sequenceparitycount%>].year" value="<s:property value="year"/>"  class="form-control" placeholder="year"></td>
	                        							<td style="width: 7%;padding-right: 15px;"><s:property value="type"/>  <input type="hidden" name="obslist[<%=sequenceparitycount%>].type" value="<s:property value="type"/>" />  </td>
	                        							<td style="width: 13%;padding-right: 15px;"> 
	                        							 
	                        							    <input type="hidden" id="tempD<%=sequenceparitycount%>" value="<s:property value="type_delivery"/>" />
		                        							<select name="obslist[<%=sequenceparitycount%>].type_delivery" id="tobs_type<%=sequenceparitycount%>" class="form-control">
				    												<option value="0">Type of Delivery</option>
				    												<option value="Normal">Normal</option>
				    												<option value="Vaccume">Vaccume</option>
				    												<option value="Forceps">Forceps</option>
				    												<option value="LSCS">LSCS</option>
				    												
															</select>
															<script>
															     var tt=document.getElementById('tempD<%=sequenceparitycount%>').value;
	                        							         document.getElementById('tobs_type<%=sequenceparitycount%>').value=tt;
	                        							   </script>
															
	                        							</td>
	                        							
													    <td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist[<%=sequenceparitycount%>].indications" value="<s:property value="indications"/>" class="form-control"  placeholder="Indications"></td>
	                        							<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist[<%=sequenceparitycount%>].coamplications" value="<s:property value="coamplications"/>" class="form-control" placeholder="Coamplications"></td>
	                        							<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist[<%=sequenceparitycount%>].institution" value="<s:property value="institution"/>" class="form-control" placeholder="Institution"></td>	                        							
	                        							<td><a href="" onclick="deleteGynicRow(<%=sequenceparitycount%>,'obstable')" ><i class="fa fa-trash" aria-hidden="true" style="color: #d9534f;"></i></a></td>
	                        						</tr>
	                        						 <%sequenceparitycount++; %>
	                        						</s:iterator>
	                        					</tbody>
	                        				</table>
										<input type="hidden" id="parityabortioncount" value="<%=sequenceparitycount %>">
										
										
									</div>
								</div>
								<s:if test="formtype==4">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="col-lg-2 col-md-3 col-xs-3">
											<div class="form-group">
												<lable>G</lable>
												<s:textfield name="g" onkeyup="onlyNum(this)"
													cssClass="form-control gynicprint" />
											</div>
										</div>
										<div class="col-lg-2 col-md-3 col-xs-3">
											<div class="form-group">
												<lable>P</lable>
												<s:textfield name="p" onkeyup="onlyNum(this)"
													cssClass="form-control gynicprint" />
											</div>
										</div>
										<div class="col-lg-2 col-md-3 col-xs-3">
											<div class="form-group">
												<lable>L</lable>
												<s:textfield name="l" onkeyup="onlyNum(this)"
													cssClass="form-control gynicprint" />
											</div>
										</div>
										<div class="col-lg-2 col-md-3 col-xs-3">
											<div class="form-group">
												<lable>A</lable>
												<s:textfield name="a" onkeyup="onlyNum(this)"
													cssClass="form-control gynicprint" />
											</div>
										</div>
										<div class="col-lg-2 col-md-3 col-xs-3">
											<div class="form-group">
												<lable>D</lable>
												<s:textfield name="d" onkeyup="onlyNum(this)"
													cssClass="form-control gynicprint" />
											</div>
										</div>
									</div>
									
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group">
												<lable>Parity/Abortions Notes</lable>
												<s:textarea cssClass="form-control gynicprint" theme="simple" 
													name="parity_abortion_notes" id="parity_abortion_notes" rows="3" />
											</div>
										</div>
										
									</div>
								</s:if>
								
							</div>
						</div>
					<div>
						
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>MENSTRUAL H/O</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
								<div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-6 col-xs-6 col-md-6">
										<label>LMP</label>
										<s:textfield name="lmp" id="lmp" readonly="true" 
										onchange="calEdd(this.value)" cssClass="form-control" />
									</div>
									<div class="col-lg-6 col-xs-6 col-md-6">	
										<label>Dating USG Date</label>
										<s:textfield readonly="true" name="dating_usg_date" id="dating_usg_date"  onchange="calculatedatingscan()"  cssClass="form-control" />
									</div>
								</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-6 col-xs-6 col-md-6">
										<label>EDD</label><br>
										<s:textfield name="edd" id="edd" readonly="true"
											cssClass="form-control" />
									</div>
									<div class="col-lg-6 col-xs-6 col-md-6">
										<div class="col-lg-6 col-xs-6 col-md-6">
											<label>Weeks in Dating Scan</label>
											<input type="number" value='<s:property value="weeks_dating_scan"/>' onchange="calculatedatingscan()" name="weeks_dating_scan" id="weeks_dating_scan" class="form-control" > 
										</div>
										<div class="col-lg-6 col-xs-6 col-md-6">
											<label>Days in Dating Scan</label>
											<input type="number" value='<s:property value="days_dating_scan"/>' onchange="calculatedatingscan()" name="days_dating_scan" id="days_dating_scan" class="form-control" > 
										</div>
										
										<%-- <s:textfield  name="weeks_dating_scan" id="weeks_dating_scan" cssClass="form-control" /> --%>
									</div>
								</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-6 col-xs-6 col-md-6">
										<label>POG</label>
										<s:textfield  name="pog" id="pog" readonly="true" cssClass="form-control" />
									</div>
									<div class="col-lg-6 col-xs-6 col-md-6">
										<label>POG by Dating Scan</label>
										<s:textfield readonly="true" name="pog_dating_scan" id="pog_dating_scan" cssClass="form-control" />
									</div>
								</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-6 col-xs-6 col-md-6">
										<label>PMC</label>
										<s:textfield name="pmc" 
												cssClass="form-control" />
									</div>
								</div>
								
								<div class="col-lg-12 col-xs-12 col-md-12">
									<div class="form-group">
										<label>USG Report</label>
										<s:textarea cssClass="form-control" name="usg_report" id="usg_report"   theme="simple" rows="3" />
									</div>
								</div>
						</div>
						
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>SURGICAL H/O</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12"
							style="padding-top: 10px;">
							<s:textarea cssClass="form-control" name="surgical_ho" id="surgical_ho" rows="3" />
						</div>
						
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>Immunization</h5>
						</div>
						
						<div class="col-lg-12 col-xs-12 col-md-12">
							<div class="col-lg-3 col-xs-3 col-md-3"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>Inj. TT 1st Dose</label>
									<s:textfield name="tt_dose1" cssStyle="width: 30%;" readonly="true" id="tt_dose1"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-3 col-xs-3 col-md-3">
								<div class="form-group">
									<label>Inj. TT 2nd Dose</label>
									<s:textfield name="tt_dose2" cssStyle="width: 30%;" readonly="true" id="tt_dose2"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-3 col-xs-3 col-md-3">
								<div class="form-group">
								<label>Influenza Vaccine</label>
								<s:textfield name="influenza_vaccine" cssStyle="width: 30%;" readonly="true" id="influenza_vaccine"
									cssClass="form-control" />
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>Earlier Investigations</h5>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="form-group" style="margin-top: 10px;">
											<table class="table table-responsive table-borderd">
												<tbody>
													<tr>
														<td style="width: 10%;">Date</td>
														<td style="width: 10%;"><s:textfield
																cssClass="form-control" id="date1" name="date1" /></td>
														<td style="width: 10%;"><s:textfield
																cssClass="form-control" id="date2" name="date2" /></td>
														<td style="width: 10%;"><s:textfield
																cssClass="form-control" id="date3" name="date3" /></td>
														<td style="width: 10%;"><s:textfield
																cssClass="form-control" id="date4" name="date4" /></td>
														<td style="width: 10%;">HIV I & II</td>
														<td><s:textfield type="number" name="hv_1m"
																cssClass="form-control" /></td>
													</tr>
													<tr>
														<td>Hb</td>
														<td><s:textfield type="number" name="hb1"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="hb2"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="hb3"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="hb4"
																cssClass="form-control" /></td>
														<td>HBsAg</td>
														<td><s:textfield name="hbs_ag" type="number"
																cssClass="form-control" /></td>
													</tr>
													<tr>
														<td>FBS</td>
														<td><s:textfield type="number" name="fbs1"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="fbs2"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="fbs3"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="fbs4"
																cssClass="form-control" /></td>
														<td>VDRL</td>
														<td><s:textfield type="number" name="vdrl"
																cssClass="form-control" /></td>
													</tr>
													<tr>
														<td>PPBS</td>
														<td><s:textfield type="number" name="dpbs1"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="dpbs2"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="dpbs3"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="dpbs4"
																cssClass="form-control" /></td>
														<td>Sickling</td>
														<td><s:textfield type="number" name="hb_srecta"
																cssClass="form-control" /></td>
													</tr>
													<tr>
														<td>U < R M</td>
														<td><s:textfield type="number" name="urm1"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="urm2"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="urm3"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="urm4"
																cssClass="form-control" /></td>
														<td>HbA1c</td>
														<td><s:textfield type="number" name="hb_ac"
																cssClass="form-control" /></td>
													</tr>
													<tr>
														<td>TSH</td>
														<td><s:textfield type="number" name="tsh1"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="tsh2"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="tsh3"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="tsh4"
																cssClass="form-control" /></td>
														<td>Dual Marker</td>
														<td><s:textfield type="number" name="duet_markess"
																cssClass="form-control" /></td>
													</tr>
													<tr>
														<td>ICT</td>
														<td><s:textfield type="number" name="ict1"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="ict2"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="ict3"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="ict4"
																cssClass="form-control" /></td>
														<td>Triple Marker</td>
														<td><s:textfield type="number" name="triple"
																cssClass="form-control" /></td>
													</tr>
													<tr>
														<td>GTT</td>
														<td><s:textfield type="number" name="gtt1"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="gtt2"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="gtt3"
																cssClass="form-control" /></td>
														<td><s:textfield type="number" name="gtt4"
																cssClass="form-control" /></td>
														<td>Quradraple Marker</td>
														<td><s:textfield type="number" name="quadrple_maicers"
																cssClass="form-control" /></td>
													</tr>
												</tbody>
											</table>
										
										</div>
									
									
								</div>

							</div>
						</div>
						
						
						
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>EXAMINATION / VITALS</h5>
							<span style="float: right; margin-top: -17px;"><a href="#"
								style="color: #fff;" class="hidden">Add Examination</a></span>
						</div>
						<s:if test="formtype==4">
							<div class="col-lg-12 col-xs-12 col-md-12" style="padding-left: 0px;">
								<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>Genral Condition</label>

											<s:select
												list="#{'0':'Select','Fair':'Fair','Moderate':'Moderate','Poor':'Poor','Obese':'Obese','Thin':'Thin'}"
												theme="simple" name="gen_condition" cssClass="form-control"></s:select>
										</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
										<div class="form-group">
											<label>Temp</label>
											<s:select list="#{'0':'Select','Febrile':'Febrile','Afebrile':'Afebrile'}"
												theme="simple" name="temp" cssClass="form-control"></s:select>
										</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
										<div class="form-group">
											<label>Pallor</label>
											<s:select
												list="#{'0':'Select','Absent':'Absent','Mild':'Mild','Moderate':'Moderate','Severe':'Severe'}"
												theme="simple" name="pallor" cssClass="form-control"></s:select>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>Pedal Edema</label>
											<s:select
												list="#{'0':'Select','Absent':'Absent','Mild':'Mild','Moderate':'Moderate','Severe':'Severe'}"
												theme="simple" name="pedal_edema" cssClass="form-control"></s:select>
										</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
										<div class="form-group">
											<label>Pulse</label>
											<s:textfield name="pulse"  cssClass="form-control" />
										</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
										<div class="form-group">
											<label>Height</label>
											<s:textfield name="height" id="height" onchange="calculategynicbmi()" cssClass="form-control " />
										</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
										<div class="form-group">
											<label>Weight</label>
											<s:textfield name="weight" id="weight" onchange="calculategynicbmi()" cssClass="form-control " />
										</div>
									</div>
									
									<div class="col-lg-1 col-md-1 col-sm-1">
										<div class="form-group">
											<label>BMI</label>
											<s:textfield name="bmi" id="bmi"  cssClass="form-control " />
										</div>
									</div>
									
									<div class="col-lg-1 col-md-1 col-sm-1">
										<div class="form-group">
											<label>Sys-BP</label>
											<s:textfield name="sys_bp" cssClass="form-control " />
										</div>
									</div>
									<div class="col-lg-1 col-md-1 col-sm-1">
										<div class="form-group">
											<label>Dia-BP</label>
											<s:textfield name="dia_bp" cssClass="form-control " />
										</div>
									</div>
								</div>



						</div>
						</s:if>
						
                       
								<div class="col-lg-12 col-xs-12 col-md-12 textprime">
									<h5>P/A</h5>
								</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub">
										<b>Abdominal Wall Edema</b>
									</h5>
									<div class="col-lg-2 col-xs-2 col-md-2"
										style="padding-left: 0px;">
										<div class="form-group">
											<article>
												<div class="">
													<ul class="vertical" id="newid">
														<!-- <li><label class="checkbox checkbox-custom">
															<input name="wall_edema" value="1" type="radio"><i></i>
															Present
															</label>
														</li>
														<div class="heightdiva"></div>
														<li>
															<label class="checkbox checkbox-custom">
																<input name="wall_edema" value="0" type="radio"><i></i>
																Absent
															</label>
														</li> -->
														<s:if test="wall_edema==1">
										      				<li><label class="checkbox checkbox-custom"><input name="wall_edema" value="1" checked="checked"  type="radio"><i></i>  Present</label></li>
									                      	<div class="heightdiva"></div>
									                       	<li><label class="checkbox checkbox-custom"><input name="wall_edema" value="0" type="radio"><i></i>  Absent</label></li>
										      			</s:if>
										      			<s:elseif test="wall_edema==0">
										      				<li><label class="checkbox checkbox-custom"><input name="wall_edema" value="1" type="radio"><i></i>  Present</label></li>
												            <div class="heightdiva"></div>
												            <li><label class="checkbox checkbox-custom"><input name="wall_edema" value="0" checked="checked" type="radio"><i></i>  Absent</label></li>
										      			</s:elseif>
										      			<s:else>
										      				<li><label class="checkbox checkbox-custom"><input name="wall_edema" value="1" type="radio"><i></i>  Present</label></li>
												            <div class="heightdiva"></div>
												            <li><label class="checkbox checkbox-custom"><input name="wall_edema" value="0"  type="radio"><i></i>  Absent</label></li>
										      			</s:else>
													</ul>
												</div>
											</article>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2"></div>
									<div class="col-lg-2 col-xs-2 col-md-2"></div>
									<div class="col-lg-2 col-xs-2 col-md-2"></div>
									<div class="col-lg-2 col-xs-2 col-md-2"></div>
									<div class="col-lg-2 col-xs-2 col-md-2"
										style="padding-right: 0px;"></div>
								</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub">
										<b>Fundal Height</b>
									</h5>
									<!-- <div class="form-inline"> -->
										<div class="col-lg-1 col-xs-1 col-md-1">
											<div class="form-inline">
												
													 <s:if test="pa_soft==1">
									             		<input type="checkbox"  checked="checked" id="pa_soft" onclick="disablePAFiled(this.checked)" name="pa_soft" >
										             </s:if>
										             <s:else>
										             	<input type="checkbox" id="pa_soft" name="pa_soft" onclick="disablePAFiled(this.checked)" >
										             </s:else>
													<i></i>
												Soft
											</div>
										</div>
										<s:if test="pa_soft==1">
											<div class="col-lg-3 col-xs-3 col-md-3 hidden" style="margin-right: 10px;" id="fnhide_dv">
										</s:if>
										<s:else>
											<div class="col-lg-3 col-xs-3 col-md-3" style="margin-right: 10px;" id="fnhide_dv">
										</s:else>
											<div class="form-inline">
												<s:textfield name="fundal_height" id="fundal_height" cssClass="form-control"></s:textfield>weeks
											</div>
										</div>
										
										<div class="col-lg-4 col-xs-4 col-md-4" style="padding-left: 0px;padding-right: 0px;">
										<div class="form-group">
											<article>
												<div class="">
													<ul class="vertical default_list" id="">
														<li>
															<label class="checkbox checkbox-custom-alt m-0 mt-5">
																 <s:if test="external_ballotment==1">
													            	<input type="checkbox" checked="checked" name="external_ballotment">
													            </s:if>
													            <s:else>
													            	<input type="checkbox"  name="external_ballotment">
													            </s:else>
																<i></i> External Ballotment
															</label>
														</li>
													</ul>
												</div>
											</article>
										</div>
									  </div>
										
									
								</div>
								<s:if test="pa_soft==1">
									<div class="col-lg-6 col-xs-6 col-md-6 hidden" id="fnhide_div">
								</s:if>
								<s:else>
									<div class="col-lg-6 col-xs-6 col-md-6" id="fnhide_div">
								</s:else>
								
									<h5 class="lighbdsub">
										<b>Presentation</b>
									</h5>
									<div class="col-lg-2 col-xs-2 col-md-2"
										style="padding-left: 0px;">
										<div class="form-group">
											<article>
												<div class="">
													<ul class="vertical default_list" id="">
														<!-- <a href="#" data-toggle="collapse" data-target="#drop1"style="color: #000;"> -->
															<li>
																<label class="checkbox checkbox-custom-alt m-0 mt-5">
																	<s:if test="cephalic==1">
														               <input type="checkbox" onclick="setcollapsOrNot('drop1',this.checked)" checked="checked" name="cephalic">
														            </s:if>
														            <s:else>
														            		<input type="checkbox" onclick="setcollapsOrNot('drop1',this.checked)" name="cephalic">
														            </s:else>
																	<i></i> 
																	Cephalic
																</label>
															</li>
														<!-- </a> -->
													</ul>
												</div>
											</article>
										</div>
										<s:if test="cephalic==1">
							            	<div id="drop1" class="collapse in">
											<div class="form-group">
												<label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
												   <s:if test="cephalicVal=='Mobile'">
												      <input name="cephalicVal" checked="checked" value="Mobile" type="radio"><i></i> Mobile
												    </s:if>
												    <s:else>
												    	<input name="cephalicVal" value="Mobile" type="radio"><i></i> Mobile
												    </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                     <s:if test="cephalicVal=='Fixed'">
                                                    	<input name="cephalicVal" checked="checked" value="Fixed" type="radio"><i></i> Fixed
                                                    </s:if>
                                                    <s:else>
                                                    	<input name="cephalicVal" value="Fixed" type="radio"><i></i> Fixed
                                                    </s:else> 
                                             </label>
											</div>
										</div>
							            </s:if>
							            <s:else>
							            	<div id="drop1" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
												   <s:if test="cephalicVal=='Mobile'">
												      <input name="cephalicVal" checked="checked" value="Mobile" type="radio"><i></i> Mobile
												    </s:if>
												    <s:else>
												    	<input name="cephalicVal" value="Mobile" type="radio"><i></i> Mobile
												    </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                     <s:if test="cephalicVal=='Fixed'">
                                                    	<input name="cephalicVal" checked="checked" value="Fixed" type="radio"><i></i> Fixed
                                                    </s:if>
                                                    <s:else>
                                                    	<input name="cephalicVal" value="Fixed" type="radio"><i></i> Fixed
                                                    </s:else> 
                                             </label>
											</div>
										</div>
							            </s:else>
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
											<article>
												<div class="">
													<ul class="vertical default_list" id="">
														<li><label class="checkbox checkbox-custom-alt m-0 mt-5">
															<s:if test="breech==1">
												             	<input type="checkbox" checked="checked" name="breech" >
												             </s:if>
												             <s:else>
												             	<input type="checkbox" name="breech" >
												             </s:else>
												             <i></i> 
												             Breech
															</label>
														</li>
													</ul>
												</div>
											</article>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left: 0px;padding-right: 0px;">
										<div class="form-group">
											<article>
												<div class="">
													<ul class="vertical default_list" id="">
														<!-- <a href="#" data-toggle="collapse" data-target="#drop2"
															style="color: #000;"> -->
															<li>
																<label class="checkbox checkbox-custom-alt m-0 mt-5">
																	 <s:if test="baley_size==1">
														             	<input type="checkbox" checked="checked" onclick="setcollapsOrNot('drop2',this.checked)" name="baley_size">
														             </s:if>
														             <s:else>
														            	<input  type="checkbox" onclick="setcollapsOrNot('drop2',this.checked)" name="baley_size"> 
														             </s:else>
																	 <i></i> 
																	 <!-- Baby size -->
																	 Variable
																</label>
															</li>
														<!-- </a> -->
													</ul>
												</div>
											</article>
										</div>
										<s:if test="baley_size==1">
											<div id="drop2" class="collapse in">
											<div class="form-group">
												<label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
												     <s:if test="baley_sizeVal=='Average'">
												     	<input name="baley_sizeVal" checked="checked" value="Average" type="radio"><i></i> Average
												     </s:if>
												     <s:else>
												    	<input name="baley_sizeVal" value="Average" type="radio"><i></i> Average 
												     </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		<s:if test="baley_sizeVal=='Small'">
                                             			<input name="baley_sizeVal" checked="checked" value="Small" type="radio"><i></i> Small
                                             		</s:if>
                                             		<s:else>
                                             			<input name="baley_sizeVal" value="Small" type="radio"><i></i> Small
                                             		</s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		<s:if test="baley_sizeVal=='Large'">
                                             			<input name="baley_sizeVal" checked="checked" value="Large" type="radio"><i></i> Large
                                             		</s:if>
                                             		<s:else>
                                             			<input name="baley_sizeVal" value="Large" type="radio"><i></i> Large
                                             		</s:else>		
                                                     
                                             </label>
											</div>
										</div>
										</s:if>
										<s:else>
											<div id="drop2" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
												     <s:if test="baley_sizeVal=='Average'">
												     	<input name="baley_sizeVal" checked="checked" value="Average" type="radio"><i></i> Average
												     </s:if>
												     <s:else>
												    	<input name="baley_sizeVal" value="Average" type="radio"><i></i> Average 
												     </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		<s:if test="baley_sizeVal=='Small'">
                                             			<input name="baley_sizeVal" checked="checked" value="Small" type="radio"><i></i> Small
                                             		</s:if>
                                             		<s:else>
                                             			<input name="baley_sizeVal" value="Small" type="radio"><i></i> Small
                                             		</s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		<s:if test="baley_sizeVal=='Large'">
                                             			<input name="baley_sizeVal" checked="checked" value="Large" type="radio"><i></i> Large
                                             		</s:if>
                                             		<s:else>
                                             			<input name="baley_sizeVal" value="Large" type="radio"><i></i> Large
                                             		</s:else>		
                                                     
                                             </label>
											</div>
										</div>
										</s:else>
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left: 0px;padding-right: 0px;">
										<div class="form-group">
											<article>
												<div class="">
													<ul class="vertical default_list" id="">
														<!-- <a href="#" style="color: #000;"> -->
															<li>
																<label class="checkbox checkbox-custom-alt m-0 mt-5">
																		<s:if test="transverse_fhs==1">
															            	<input type="checkbox" checked="checked" name="transverse_fhs">
															            </s:if>
															            <s:else>
															            	<input type="checkbox"  name="transverse_fhs">
															            </s:else>
															            <i></i>
																		Transverse
																 </label>
															</li>
														<!-- </a> -->
													</ul>
												</div>
											</article>
										</div>
										
									
									</div>
									
		
								</div>
						
						
						<s:if test="pa_soft==1">
							<div class="col-lg-2 col-xs-2 col-md-2 hidden" id="fnhide_div1">
						</s:if>
						<s:else>
							<div class="col-lg-2 col-xs-2 col-md-2" id="fnhide_div1">
						</s:else>
						
							<h5 class="lighbdsub">
								<b>FHS</b>
							</h5>
							<div class="form-group">
								<label class="checkbox checkbox-custom" style="padding-bottom: 5px;"> 
										<s:if test="ps_fhs=='Prsent'">
                                           <input name="ps_fhs" checked="checked" value="Prsent" type="radio"><i></i> Present
                                        </s:if>
                                        <s:else>	
                                        	<input name="ps_fhs" value="Prsent" type="radio"><i></i> Present
                                        </s:else> 
								</label> 
								<label class="checkbox checkbox-custom" style="padding-bottom: 5px;"> 
									               <s:if test="ps_fhs=='Absent'">
                                                   		<input name="ps_fhs" checked="checked" value="Absent" type="radio"><i></i> Absent
                                                   </s:if>
                                                   <s:else>
                                                   		<input name="ps_fhs" value="Absent" type="radio"><i></i> Absent
                                                   </s:else> 
								</label> 
								<label class="checkbox checkbox-custom" style="padding-bottom: 5px;"> 
									                 <s:if test="ps_fhs=='Regular'">
							       			 		 		<input name="ps_fhs" checked="checked" value="Regular" type="radio"><i></i> Regular
							       			 		 </s:if>
							       			 		 <s:else>
							       			 			<input name="ps_fhs" value="Regular" type="radio"><i></i> Regular	 
							       			 		 </s:else>
								</label> 
								<label class="checkbox checkbox-custom" style="padding-bottom: 5px;"> 
									                <s:if test="ps_fhs=='Irregular'">
                                             				<input name="ps_fhs" checked="checked" value="Irregular" type="radio"><i></i> Irregular
                                             		</s:if>
                                             		<s:else>
                                             				<input name="ps_fhs" value="Irregular" type="radio"><i></i> Irregular
                                             		</s:else>
								</label>
								<div class="col-lg-8 col-xs-8 col-md-8"
									style="margin-left: 2px;">
									<div class="form-group">
										<label>Beats/min</label>
										<s:textfield name="beats_min" cssClass="form-control " />
									</div>
								</div>
							</div>
						</div>
						
						<s:if test="pa_soft==1">
							<div class="col-lg-2 col-xs-2 col-md-2 hidden" id="fnhide_div2">
						</s:if>
						<s:else>
							<div class="col-lg-2 col-xs-2 col-md-2" id="fnhide_div2">
						</s:else>
						
						
										<h5 class="lighbdsub"><b>Liquor</b></h5>
										
										<div class="form-group">
											
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
							       			       
							       			         <s:if test="liquor=='Adequate'">
							       			         		<input name="liquor" checked="checked" value="Adequate" type="radio"><i></i> Adequate
							       			         </s:if>
							       			         <s:else>
							       			        		<input name="liquor" value="Adequate" type="radio"><i></i> Adequate 
							       			         </s:else>
                                                    
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                     <s:if test="liquor=='Reduced'">
                                                    	<input name="liquor" checked="checked" value="Reduced" type="radio"><i></i> Reduced 
                                                     </s:if>
                                                     <s:else>
                                                     	<input name="liquor" value="Reduced" type="radio"><i></i> Reduced
                                                     </s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		 <s:if test="liquor=='More'">
                                             		 	 <input name="liquor" checked="checked"  value="Reduced" type="radio"><i></i> More		
                                             		 </s:if>
                                             		 <s:else>
                                             			<input name="liquor"  value="Reduced" type="radio"><i></i> More 
                                             		 </s:else>
                                             </label>
							       		</div>
									</div>
						<s:if test="pa_soft==1">
							<div class="col-lg-2 col-xs-2 col-md-2 hidden" style="padding-right:0px;" id="fnhide_div3">
						</s:if>
						<s:else>
							<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;" id="fnhide_div3">
						</s:else>
						
										<h5 class="lighbdsub"><b>Uterine Contractions</b></h5>
										<div class="form-group">
											
							       			 <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
							       			        <s:if test="uterine_contractions=='Relaxed'">
							       			        	<input name="uterine_contractions" checked="checked" value="Relaxed" type="radio"><i></i> Relaxed
							       			        </s:if>
							       			        <s:else>
							       			        	<input name="uterine_contractions" value="Relaxed" type="radio"><i></i> Relaxed
							       			        </s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
													<s:if test="uterine_contractions=='Irritable'">
														<input name="uterine_contractions" checked="checked" value="Irritable" type="radio"><i></i> Irritable	
													</s:if>
													<s:else>
														<input name="uterine_contractions" value="Irritable" type="radio"><i></i> Irritable
													</s:else>                                             	
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		<s:if test="uterine_contractions=='Acting'">
                                             				<input name="uterine_contractions" checked="checked" value="Acting" type="radio"><i></i> Acting
                                             		</s:if>
                                             		<s:else>
                                             				<input name="uterine_contractions" value="Acting" type="radio"><i></i> Acting
                                             		</s:else>
                                                     
                                             </label>
							       		</div>
									</div>
								                        
                        
                       

						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>P/S</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12">
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<article>
										<div class="">
											<ul class="vertical default_list" id="">
									            <!-- <a href="#" data-toggle="collapse" data-target="#drop4" style="color: #000;"> -->
									            <li><label class="checkbox checkbox-custom-alt m-0 mt-5">
									            <s:if test="ps_cervix==1">
									            	<input type="checkbox" onclick="setcollapsOrNot('drop4',this.checked)" checked="checked" name="ps_cervix">	
									            </s:if>
									            <s:else>
									            		<input type="checkbox" onclick="setcollapsOrNot('drop4',this.checked)"  name="ps_cervix">
									            </s:else>
									            <i></i> Cervix</label></li>
									            <!-- </a> --> 
									          </ul>
										</div>
									</article>
								</div>
								<s:if test="ps_cervix==1">
									<div id="drop4" class="collapse in">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
													<s:if test="ps_cervixVal=='Normal'">
														<input type="checkbox"  checked="checked" name="ps_cervixVal" value="Normal" ><i></i> Normal
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Normal" ><i></i> Normal
													</s:else>		
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <s:if test="ps_cervixVal=='Erosion'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Erosion" ><i></i> Erosion
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Erosion" ><i></i> Erosion
													</s:else>	
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                      <s:if test="ps_cervixVal=='Bleeding'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Bleeding" ><i></i> Bleeding
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Bleeding" ><i></i> Bleeding
													</s:else>	 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		 <s:if test="ps_cervixVal=='Small Polyp - Present'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Small Polyp - Present" ><i></i> Small Polyp - Present
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Small Polyp - Present" ><i></i> Small Polyp - Present
													</s:else>	 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		 <s:if test="ps_cervixVal=='Irregular Margins'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Irregular Margins" ><i></i> Irregular Margins
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Irregular Margins" ><i></i> Irregular Margins
													</s:else>	
                                             </label>
											</div>
										</div>
								</s:if>
					            <s:else>
					            	<div id="drop4" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
													<s:if test="ps_cervixVal=='Normal'">
														<input type="checkbox"  checked="checked" name="ps_cervixVal" value="Normal" ><i></i> Normal
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Normal" ><i></i> Normal
													</s:else>		
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <s:if test="ps_cervixVal=='Erosion'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Erosion" ><i></i> Erosion
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Erosion" ><i></i> Erosion
													</s:else>	
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                      <s:if test="ps_cervixVal=='Bleeding'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Bleeding" ><i></i> Bleeding
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Bleeding" ><i></i> Bleeding
													</s:else>	 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		 <s:if test="ps_cervixVal=='Small Polyp - Present'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Small Polyp - Present" ><i></i> Small Polyp - Present
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Small Polyp - Present" ><i></i> Small Polyp - Present
													</s:else>	 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		 <s:if test="ps_cervixVal=='Irregular Margins'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Irregular Margins" ><i></i> Irregular Margins
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Irregular Margins" ><i></i> Irregular Margins
													</s:else>	
                                             </label>
											</div>
										</div>
					            </s:else>
						
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<article>
										<div class="">
											<ul class="vertical default_list" id="">
									            <!-- <a href="#" data-toggle="collapse" data-target="#drop5" style="color: #000;"> -->
									            <li><label class="checkbox checkbox-custom-alt m-0 mt-5">
									            <s:if test="ps_vagine==1">
									            		<input type="checkbox" onclick="setcollapsOrNot('drop5',this.checked)" checked="checked" name="ps_vagine">
									            </s:if>
									            <s:else>
									            		<input type="checkbox"  onclick="setcollapsOrNot('drop5',this.checked)" name="ps_vagine">
									            </s:else>
									            <i></i> Vagina</label></li>
									            <!-- </a> --> 
									          </ul>
										</div>
									</article>
								</div>
								
								<s:if test="ps_vagine==1">
									<div id="drop5" class="collapse in">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
													 <s:if test="ps_vagineVal=='Normal'">
														<input type="checkbox" checked="checked" name="ps_vagineVal" value="Normal"><i></i> Normal 	
													 </s:if>
													 <s:else>
													 	<input type="checkbox" name="ps_vagineVal" value="Normal"><i></i> Normal
													 </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vagineVal=='Vaginitis'">
                                             			<input type="checkbox" name="ps_vagineVal" value="Vaginitis"><i></i> Vaginitis
                                             		</s:if>
                                             		<s:else>
                                             			<input type="checkbox" name="ps_vagineVal" value="Vaginitis"><i></i> Vaginitis
                                             		</s:else>	
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vagineVal=='Candidiasis'">
                                             				<input type="checkbox" checked="checked" name="ps_vagineVal" value="Candidiasis" ><i></i> Candidiasis
                                             		</s:if>
                                             		<s:else>
                                             				<input type="checkbox" name="ps_vagineVal" value="Candidiasis" ><i></i> Candidiasis
                                             		</s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vagineVal=='Anterior Vaginal wall cyst'">
                                             				<input type="checkbox" name="ps_vagineVal" value="Anterior Vaginal wall cyst"><i></i> Anterior Vaginal wall cyst
                                             		</s:if>
                                             		<s:else>
                                             				<input type="checkbox" name="ps_vagineVal" value="Anterior Vaginal wall cyst"><i></i> Anterior Vaginal wall cyst
                                             		</s:else>
                                                    
                                             </label>
											</div>
										</div>
								</s:if>
					            <s:else>
					            	<div id="drop5" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
													 <s:if test="ps_vagineVal=='Normal'">
														<input type="checkbox" checked="checked" name="ps_vagineVal" value="Normal"><i></i> Normal 	
													 </s:if>
													 <s:else>
													 	<input type="checkbox" name="ps_vagineVal" value="Normal"><i></i> Normal
													 </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vagineVal=='Vaginitis'">
                                             			<input type="checkbox" name="ps_vagineVal" value="Vaginitis"><i></i> Vaginitis
                                             		</s:if>
                                             		<s:else>
                                             			<input type="checkbox" name="ps_vagineVal" value="Vaginitis"><i></i> Vaginitis
                                             		</s:else>	
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vagineVal=='Candidiasis'">
                                             				<input type="checkbox" checked="checked" name="ps_vagineVal" value="Candidiasis" ><i></i> Candidiasis
                                             		</s:if>
                                             		<s:else>
                                             				<input type="checkbox" name="ps_vagineVal" value="Candidiasis" ><i></i> Candidiasis
                                             		</s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vagineVal=='Anterior Vaginal wall cyst'">
                                             				<input type="checkbox" name="ps_vagineVal" value="Anterior Vaginal wall cyst"><i></i> Anterior Vaginal wall cyst
                                             		</s:if>
                                             		<s:else>
                                             				<input type="checkbox" name="ps_vagineVal" value="Anterior Vaginal wall cyst"><i></i> Anterior Vaginal wall cyst
                                             		</s:else>
                                                    
                                             </label>
											</div>
										</div>
					            </s:else>
								
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<article>
										<div class="">
											<ul class="vertical default_list" id="">
									            <!-- <a href="#" data-toggle="collapse" data-target="#drop6" style="color: #000;"> -->
									            <li><label class="checkbox checkbox-custom-alt m-0 mt-5">
									            <s:if test="ps_vault==1">
									            	<input type="checkbox" onclick="setcollapsOrNot('drop6',this.checked)" checked="checked" name="ps_vault">
									            </s:if>
									            <s:else>
									            	<input type="checkbox" onclick="setcollapsOrNot('drop6',this.checked)"  name="ps_vault">
									            </s:else>
									            <i></i> Vault</label></li>
									            <!-- </a>  -->
									          </ul>
										</div>
									</article>
								</div>
								<s:if test="ps_vault==1">
					            	<div id="drop6" class="collapse in">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
													<s:if test="ps_vaultVal=='Normal'">
														<input type="checkbox" checked="checked" name="ps_vaultVal" value="Normal"><i></i> Normal
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_vaultVal" value="Normal"><i></i> Normal
													</s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vaultVal=='Granuletion Tissue'">
                                             			<input type="checkbox" checked="checked" name="ps_vaultVal" value="Granuletion Tissue"><i></i> Granulation Tissue
                                             		</s:if>
                                             		<s:else>
                                             			<input type="checkbox" name="ps_vaultVal" value="Granuletion Tissue"><i></i> Granulation Tissue
                                             		</s:else>
                                                     
                                             </label>
											</div>
										</div>
					            </s:if>
					            <s:else>
					            	<div id="drop6" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
													<s:if test="ps_vaultVal=='Normal'">
														<input type="checkbox" checked="checked" name="ps_vaultVal" value="Normal"><i></i> Normal
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_vaultVal" value="Normal"><i></i> Normal
													</s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vaultVal=='Granuletion Tissue'">
                                             			<input type="checkbox" checked="checked" name="ps_vaultVal" value="Granuletion Tissue"><i></i> Granulation Tissue
                                             		</s:if>
                                             		<s:else>
                                             			<input type="checkbox" name="ps_vaultVal" value="Granuletion Tissue"><i></i> Granulation Tissue
                                             		</s:else>
                                                     
                                             </label>
											</div>
										</div>
					            </s:else>
								
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-right: 0px;"></div>
						</div>
						
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>P/V</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12"
							style="margin-top: 10px;">
							

                               <s:if test="formtype==4">
                               		
                               		<!-- <div class="col-lg-6 col-md-6 col-xs-6 padingleferightzero"> -->


								<div class="col-lg-12 col-md-12" style="padding: 0px;">
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left: 0px;">
									<div class="form-group">
										<article>
											<div class="">
												<ul class="vertical default_list" id="">
						                           <!-- <a href="#" data-toggle="collapse" data-target="#pvnew3" style="color: #000;"> -->
						                           <li><label class="checkbox checkbox-custom-alt m-0 mt-5">
						                           <s:if test="pv_dialated==1">
						                           		<input type="checkbox" onclick="setcollapsOrNot('pvnew3',this.checked)" checked="checked" name="pv_dialated">
						                           </s:if>
						                           <s:else>
						                           		<input onclick="setcollapsOrNot('pvnew3',this.checked)"  type="checkbox" name="pv_dialated">
						                           </s:else>
						                           <i></i> Dialated</label></li>
						                           <!-- </a>  -->
								              </ul>
											</div>
										</article>
									</div>
									<s:if test="pv_dialated==1">
										<div id="pvnew3" class="collapse in">
											<div class="form-group">
												<s:textfield name="pv_dialatedcm" cssClass="form-control" placeholder="cm"></s:textfield>
											</div>
										</div>
		                           </s:if>
		                           <s:else>
			                           	<div id="pvnew3" class="collapse">
											<div class="form-group">
												<s:textfield name="pv_dialatedcm" cssClass="form-control" placeholder="cm"></s:textfield>
											</div>
										</div>
		                           </s:else>
									
								</div>
								
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
										<div class="form-group">
											<label class="checkbox checkbox-custom-alt m-0 mt-5"> 
													<s:if test="pv_trim==1">
                                                    	<input name="pv_trim" checked="checked" type="checkbox">
                                                    </s:if>
                                                    <s:else>
                                                    	<input name="pv_trim" type="checkbox">
                                                    </s:else>
                                                    <i></i>  Firm 
											</label>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
										<div class="form-group">
											<label class="checkbox checkbox-custom-alt m-0 mt-5"> 
													<s:if test="pv_unettacced==1">
                   										<input name="pv_unettacced" checked="checked" type="checkbox"><i></i>  Uneffeaced
                   									</s:if>
                   									<s:else>
                   										<input name="pv_unettacced" type="checkbox"><i></i>  Uneffeaced
                   									</s:else>	
											</label>
										</div>

									</div>
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
										<div class="form-group">
											<label class="checkbox checkbox-custom-alt m-0 mt-5"> 
													<s:if test="pv_ant==1">
                 											<input name="pv_ant" checked="checked" type="checkbox"><i></i>  ant
                 									</s:if>
                 									<s:else>
                 											<input name="pv_ant" type="checkbox"><i></i>  ant
                 									</s:else>	
											</label>
										</div>

									</div>
									
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
										<div class="form-group">
											<label class="checkbox checkbox-custom-alt m-0 mt-5"> 
													<s:if test="pv_tubular==1">		
                                                    	<input checked="checked" name="pv_tubular" type="checkbox"><i></i>  Tubular
                                                    </s:if>
                                                    <s:else>
                                                    	<input name="pv_tubular" type="checkbox"><i></i>  Tubular
                                                    </s:else>
											</label>
										</div>
									</div>
									
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
										<div class="form-group">
											<label class="checkbox checkbox-custom-alt m-0 mt-5"> 
												<s:if test="pv_just_ettacced==1">
                                                    <input checked="checked" name="pv_just_ettacced" type="checkbox"><i></i>  Just Effaced
                                                    </s:if>
                                                    <s:else>
                                                    	<input name="pv_just_ettacced" type="checkbox"><i></i>  Just Effaced
                                                    </s:else> 
											</label>
										</div>

									</div>

								</div>
								<div class="col-lg-12 col-md-12" style="padding: 0px;">
									
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
										<div class="form-group">
											<label class="checkbox checkbox-custom-alt m-0 mt-5"> 
												<s:if test="pv_mid_posits==1">
						                         <input name="pv_mid_posits" checked="checked" type="checkbox"><i></i>  Mid Post
						                        </s:if>
						                        <s:else>
						                        	<input name="pv_mid_posits" type="checkbox"><i></i>  Mid Post
						                        </s:else> 
											</label>
										</div>
									</div>
									
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
										<div class="form-group">
											<label class="checkbox checkbox-custom-alt m-0 mt-5"> 
											   <s:if test="pv_soft==1">
						                        	<input name="pv_soft" checked="checked" type="checkbox"><i></i>  Soft
						                       </s:if>
						                       <s:else>	
						                       		<input name="pv_soft" type="checkbox"><i></i>  Soft
						                       </s:else> 
											</label>
										</div>
									</div>
									
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
										<div class="form-group">
											<article>
												<div class="">
													<ul class="vertical default_list" id="">
														<!-- <a href="#" data-toggle="collapse" data-target="#pvnew4"
															style="color: #000;"> -->
																<li>
																	<label class="checkbox checkbox-custom-alt m-0 mt-5">
																	 <s:if test="pv_ettacced==1">
												                     		<input name="pv_ettacced" onclick="setcollapsOrNot('pvnew4',this.checked)" checked="checked" type="checkbox"><i></i>  Effaced
												                    </s:if>
												                    <s:else>
												                    		<input name="pv_ettacced" onclick="setcollapsOrNot('pvnew4',this.checked)" type="checkbox"><i></i>  Effaced
																	</s:else>
												                    </label>
												                    </li>
												                    <!-- </a>	 -->							                   
													</ul>
												</div>
											</article>
										</div>
										<s:if test="pv_ettacced==1">
											<div id="pvnew4" class="collapse in">
												<div class="form-group">
													<s:textfield cssClass="form-control" name="effaced_text" placeholder="%"></s:textfield>
												</div>
											</div>
										</s:if>
										<s:else>
											<div id="pvnew4" class="collapse">
												<div class="form-group">
													<s:textfield cssClass="form-control" name="effaced_text" placeholder="%"></s:textfield>
												</div>
											</div>
										</s:else>
										
										
									</div>
									
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
										<div class="form-group">
											<label class="checkbox checkbox-custom-alt m-0 mt-5"> 
												<s:if test="pv_post==1">
						                        	<input name="pv_post" checked="checked" type="checkbox"><i></i>  Post
						                        </s:if>
						                        <s:else>
						                       		<input name="pv_post" type="checkbox"><i></i>  Post 
						                        </s:else>
											</label>
										</div>

									</div>
									
									<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
									<div class="form-group">
										<article>
											<div class="">
												<ul class="vertical default_list" id="">
							                       <!--  <a href="#" data-toggle="collapse" data-target="#pvnew1" style="color: #000;"> -->
							                        <li><label class="checkbox checkbox-custom-alt m-0 mt-5">
							                       <s:if test="pv_os==1">
							                       		<input type="checkbox" onclick="setcollapsOrNot('pvnew1',this.checked)" checked="checked" name="pv_os">
							                       </s:if>  
							                       <s:else>
							                       		<input type="checkbox" onclick="setcollapsOrNot('pvnew1',this.checked)"  name="pv_os">
							                       </s:else>
							                        
							                        <i></i> OS</label></li>
							                        <!-- </a>  -->
							                     </ul>
											</div>
										</article>
									</div>
								   <s:if test="pv_os==1">
			                       	<div id="pvnew1" class="collapse in">
						                   <div class="form-group">
						                      <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                      								<s:if test="pv_osVal=='Closed'">
						                      										<input name="pv_osVal" checked="checked" value="Closed" type="radio"><i></i> Closed
						                      								</s:if>
						                      								<s:else>
						                      										<input name="pv_osVal" value="Closed" type="radio"><i></i> Closed
						                      								</s:else>
						                                                      
						                                             </label>
						                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                                             		<s:if test="pv_osVal=='Admits Tip of Fesyes'">
						                                             				<input name="pv_osVal" checked="checked" value="Admits Tip of Fesyes" type="radio"><i></i> Admits Tip of Finger
						                      								</s:if>
						                      								<s:else>
						                      									<input name="pv_osVal" value="Admits Tip of Fesyes" type="radio"><i></i> Admits Tip of Finger
						                      								</s:else>	
						                                                    
						                                             </label>
						                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                                             		<s:if test="pv_osVal=='Dilotation'">
						                                             				 <input name="pv_osVal" checked="checked" value="Dilotation" type="radio"><i></i> Dilation
						                      								</s:if>
						                      								<s:else>
						                      										 <input name="pv_osVal" value="Dilotation" type="radio"><i></i> Dilation
						                      								</s:else>	
						                                                    
						                                             </label>
						                 </div>
               						</div>
			                       </s:if>  
			                       <s:else>
			                       	<div id="pvnew1" class="collapse">
						                   <div class="form-group">
						                      <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                      								<s:if test="pv_osVal=='Closed'">
						                      										<input name="pv_osVal" checked="checked" value="Closed" type="radio"><i></i> Closed
						                      								</s:if>
						                      								<s:else>
						                      										<input name="pv_osVal" value="Closed" type="radio"><i></i> Closed
						                      								</s:else>
						                                                      
						                                             </label>
						                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                                             		<s:if test="pv_osVal=='Admits Tip of Fesyes'">
						                                             				<input name="pv_osVal" checked="checked" value="Admits Tip of Fesyes" type="radio"><i></i> Admits Tip of Finger
						                      								</s:if>
						                      								<s:else>
						                      									<input name="pv_osVal" value="Admits Tip of Fesyes" type="radio"><i></i> Admits Tip of Finger
						                      								</s:else>	
						                                                    
						                                             </label>
						                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                                             		<s:if test="pv_osVal=='Dilotation'">
						                                             				 <input name="pv_osVal" checked="checked" value="Dilotation" type="radio"><i></i> Dilation
						                      								</s:if>
						                      								<s:else>
						                      										 <input name="pv_osVal" value="Dilotation" type="radio"><i></i> Dilation
						                      								</s:else>	
						                                                    
						                                             </label>
						                 </div>
               						</div>
			                       </s:else>
								</div>

								<div class="col-lg-2 col-xs-2 col-md-2 padinglefetzero">
									<div class="form-group">
										<article>
											<div class="">
												<ul class="vertical default_list" id="">
						                           <!-- <a href="#" data-toggle="collapse" data-target="#pvnew2" style="color: #000;"> -->
						                           <li><label class="checkbox checkbox-custom-alt m-0 mt-5">
						                           <s:if test="pv_membrane==1">
						                           		<input type="checkbox" onclick="setcollapsOrNot('pvnew2',this.checked)" checked="checked" name="pv_membrane">
						                           </s:if>
						                           <s:else>
						                           		<input onclick="setcollapsOrNot('pvnew2',this.checked)" type="checkbox" name="pv_membrane">
						                           </s:else>
						                           
						                           <i></i> Membrane</label></li>
						                          <!--  </a>  -->
								              </ul>
											</div>
										</article>
									</div>
									<s:if test="pv_membrane==1">
										<div id="pvnew2" class="collapse in">
						                   <div class="form-group">
						                      <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                      							<s:if test="pv_MVal=='Present'">
						                      									<input name="pv_MVal" checked="checked" value="Present" type="radio"><i></i> Present
						                      							</s:if>
						                      							<s:else>
						                      									<input name="pv_MVal" value="Present" type="radio"><i></i> Present
						                      							</s:else>
						                                                      
						                                             </label>
						                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                                             	<s:if test="pv_MVal=='Absent'">
						                                             			<input name="pv_MVal" checked="checked" value="Absent" type="radio"><i></i> Absent
						                                             	</s:if>
						                                             	<s:else>
						                                             			<input name="pv_MVal" value="Absent" type="radio"><i></i> Absent
						                                             	</s:else>	
						                                                    
						                                             </label>
						                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                                             	 <s:if test="pv_MVal=='Thin over head'">
						                                             	 		<input name="pv_MVal" checked="checked" value="Thin over head" type="radio"><i></i> Thin over head
						                                             	 </s:if>
						                                             	 <s:else>
						                                             	 		<input name="pv_MVal" value="Thin over head" type="radio"><i></i> Thin over head
						                                             	 </s:else>	
						                                                    
						                                             </label>
						                 </div>
						               </div>                
		                           </s:if>
		                           <s:else>
		                           		<div id="pvnew2" class="collapse">
						                   <div class="form-group">
						                      <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                      							<s:if test="pv_MVal=='Present'">
						                      									<input name="pv_MVal" checked="checked" value="Present" type="radio"><i></i> Present
						                      							</s:if>
						                      							<s:else>
						                      									<input name="pv_MVal" value="Present" type="radio"><i></i> Present
						                      							</s:else>
						                                                      
						                                             </label>
						                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                                             	<s:if test="pv_MVal=='Absent'">
						                                             			<input name="pv_MVal" checked="checked" value="Absent" type="radio"><i></i> Absent
						                                             	</s:if>
						                                             	<s:else>
						                                             			<input name="pv_MVal" value="Absent" type="radio"><i></i> Absent
						                                             	</s:else>	
						                                                    
						                                             </label>
						                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
						                                             	 <s:if test="pv_MVal=='Thin over head'">
						                                             	 		<input name="pv_MVal" checked="checked" value="Thin over head" type="radio"><i></i> Thin over head
						                                             	 </s:if>
						                                             	 <s:else>
						                                             	 		<input name="pv_MVal" value="Thin over head" type="radio"><i></i> Thin over head
						                                             	 </s:else>	
						                                                    
						                                             </label>
						                 </div>
						               </div>        
		                           </s:else>
									
								</div>

								</div>
								
								<div class="col-lg-12 col-md-12" style="padding: 0px;">
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
										<div class="form-group">
											<label for="exampleInputName2">Station</label>
											<s:select
												list="#{'0':'Select','-3':'-3','-2':'-2','-1':'-1','0':'0','+1':'+1','+2':'+2','+3':'+3'}"
												theme="simple" cssClass="form-control" name="pv_station"></s:select>
										</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
										<div class="form-group">
											<label for="exampleInputName2">Liquor</label>
											<%-- <s:textfield name="pv_liquor" cssClass="form-control"></s:textfield> --%>
											<s:select list="#{'0':'Select','No Draining':'No Draining','Clear Liquor Draining':'Clear Liquor Draining',
											'Thin Neconium Stained liquor':'Thin Neconium Stained liquor','Thick Neconium ':'Thick Neconium','Clear':'Clear','Meconium Stained':'Meconium Stained'}"
												theme="simple" cssClass="form-control" name="pv_liquor">
											</s:select>
										</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
										<div class="form-group">
											<label for="exampleInputName2">Pelvis</label>
											<s:select
												list="#{'0':'Select','Adequate':'Adequate','Contracted':'Contracted'}"
												theme="simple" cssClass="form-control" name="pv_pelvis"></s:select>
										</div>
									</div>
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
										<div class="form-group">
											<label>Position</label>
											<s:textfield name="pv_position" cssClass="form-control" />
										</div>
									</div>

								</div>
							<!-- </div> -->

							
                               </s:if>
                               
							</div>
								<div class="col-lg-12 col-xs-12 col-md-12 textprime">
									
									<div class="col-lg-6 col-xs-6 col-md-6">
										<h5>DIAGNOSIS</h5>
									</div>
									<div class="col-lg-6 col-xs-6 col-md-6">
										<h5>Additional Notes</h5>
									</div>
								</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-6 col-xs-6 col-md-6">
										<label> </label>
										<s:textarea name="diagonosis" id="diagonosis" cssClass="form-control" rows="4" />
									</div>
									<div class="col-lg-6 col-xs-6 col-md-6">
										<label></label>
										<s:textarea name="additonal_notes" id="additonal_notes" cssClass="form-control" rows="4" />
									</div>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
										<h5>Treatment Advised</h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12">
								 		<s:if test="lastappointmentid!=0">
								 		<a style="cursor: pointer;margin-bottom: 10px;" class="btn btn-info"
										onclick="addtreatmentadvice(<s:property value="clientid"/>,<s:property value="practitionerid"/>,<s:property value="conditionid"/>)">Add</a>
										
										<div class="form-group">
											<table class="table table-bordered" id="priscTabletreat">
												<thead>
													<tr class="headings">
														<th style="width: 5%;">Sr.No</th>
														<th class="uppercaseirf">Medicine</th>
														<th>Dosage</th>
														<th>Days</th>
														<th>Notes</th>
														<th>Strength</th>
														<th>Del</th>
													</tr>
												</thead>
												<tbody id="gynicformtreat">
													<s:iterator value="gynicPriscList">
														<tr>
															<td><%-- <input type="number" class="form-control"
																name="dicpriscmed<s:property value="id"/>"
																value="<s:property value="dispriscsrno"/>"> --%>
																<s:property value="dispriscsrno"/>	
															</td>
															<td><s:property value="mdicinenametxt" /></td>
															<td>
																<%-- <select id="discpriscdose<s:property value="id"/>" name="discpriscdose<s:property value="id"/>" class="form-control chosen-select">
																	<s:iterator value="dosageList">
																		<s:if test="name==priscdose">
																			<option value="<s:property value="name" />" selected="selected"><s:property value="name" /></option>
																		</s:if>
																		<s:else>
																			<option value="<s:property value="name" />"><s:property value="name" /></option>
																		</s:else>
																	</s:iterator>
																</select> --%>
																 <s:property value="priscdose" /> 
															</td>
															<td>
																<%-- <input type="number" class="form-control"
																name="dicpriscdays<s:property value="id"/>"
																value="<s:property value="priscdays"/>"> --%>
																<s:property value="priscdays" />
															</td>
															<td><s:property value="dosenotes" /></td>
															<td><s:property value="strengthnew" /> </td>
															<%-- <td><s:property value="priscindivisualremark" /> </td> --%>
															<td><a
																onclick="deletegynicmed(this,<s:property value="id"/>)"><i
																	class="fa fa-trash"></i></a></td>
														</tr>
													</s:iterator>
													<s:hidden name="totalchildmedids"></s:hidden>
												</tbody>
											</table>
										</div>
										</s:if>
										
										
										<s:textarea name="treatment_advice" id="treatment_advice" cssClass="form-control" rows="4" />
								 </div>
								 
								 <div class="col-lg-12 col-xs-12 col-md-12 textprime">
										<h5>Investigations Advised</h5>
								 </div>
								 <div class="col-lg-12 col-xs-12 col-md-12">
										<a style="cursor: pointer;margin-bottom: 10px;" class="btn btn-info"
										onclick="showInvestigation()">Add</a>
										<s:textarea name="investigation_advice" id="investigation_advice" cssClass="form-control" rows="4" />
								 </div>
							
						  		<div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-6 col-md-6">
										
									</div>
									<div class="col-lg-6 col-md-6">
										<s:if test="id!=0">
											<input style="width: 100%; margin-right: -27px; margin-top: 141px;"
											class="btn btn-primary savebtn savebigbtn"
											value="Update" onclick="validdata()" />
										</s:if>
										<s:else>
											<input style="width: 100%; margin-right: -27px; margin-top: 141px;"
											class="btn btn-primary savebtn savebigbtn"
											value="Save" onclick="validdata()" />
										</s:else>
										
									</div>
								</div>
						</div>
					</div>


					<!-- Reason for Visit Modal -->
					<div id="rvisit" class="modal fade" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">
										Comes For Check Up <input type="hidden" id="reasonvisit" />
									</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-lg-12 col-xs-12 col-md-12 hidden"
											style="padding: 0px;">
											<div class="col-lg-3 col-md-3 col-xs-3">
												<div class="form-group">
													<label>Region</label> <input type="text" id="reason"
														class="form-control">
												</div>
											</div>
											<div class="col-lg-3 col-md-3 col-xs-3">
												<div class="form-group">
													<label>Quality</label> <input type="text" id="quality"
														class="form-control">
												</div>
											</div>
											<div class="col-lg-3 col-md-3 col-xs-3">
												<div class="form-group">
													<label>Periodicity</label> <input type="text"
														id="periodicity" class="form-control">
												</div>
											</div>
											<div class="col-lg-3 col-md-3 col-xs-3">
												<div class="form-group">
													<label>Influencing Factor</label> <input type="text"
														id="influence" class="form-control">
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-xs-12 col-md-12"
											style="padding: 0px;">
											<div class="col-lg-3 col-md-3 col-xs-3">
												<div class="form-group">
													<div class="col-lg-12 col-md-12" style="padding: 0px;">
														<label>Since</label>
													</div>

													<div class="col-lg-6 col-md-6" style="padding-left: 0px;">
														<input type="number" id="since" class="form-control">
													</div>
													<div class="col-lg-6 col-md-6" style="padding: 0px;">
														<select class="form-control" id="days">
															<option>Hours</option>
															<option>Days</option>
															<option>Week</option>
															<option>Month</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-9 col-md-9 col-xs-9">
												<div class="form-group">
													<label>Note</label>
													<textarea class="form-control" id="notes" rows="3"></textarea>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-xs-12 col-md-12" id="region_hide_div" style="padding: 0px;">
											<div class="col-lg-3 col-md-3 col-xs-3">
												<div class="form-group">
													
													<label class="checkbox checkbox-custom-alt m-0 mt-5"
																	style="padding-bottom: 5px;"> <input type="checkbox"
																	id="region_perceives" ><i></i>
																	Perceives
													</label>
													
												</div>
											</div>
											<div class="col-lg-3 col-md-3 col-xs-3">
												<div class="form-group">
													<label class="checkbox checkbox-custom-alt m-0 mt-5"
																	style="padding-bottom: 5px;"> <input type="checkbox"
																	id="region_notperceives" ><i></i>
																	Not perceives
													</label>
												</div>
											</div>
											<div class="col-lg-3 col-md-3 col-xs-3">
												<div class="form-group">
													<label class="checkbox checkbox-custom-alt m-0 mt-5"
																	style="padding-bottom: 5px;"> <input type="checkbox"
																	id="region_decreased" ><i></i>
																	Decreased
													</label>
													
													 
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" onClick="addReasonForVisit()"
										class="btn btn-primary">Save</button>
								</div>
							</div>

						</div>
					</div>
					
					<div class="modal fade" id="priscriptionpopup" tabindex="-1"
						role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true"
						data-keyboard="false" data-backdrop="static">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header bg-primary">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h5 class="modal-title" id="priscmyModalLabel">
										Create Prescription For <b class="pname">NAME: </b>
									</h5>
								</div>
								<div class="modal-body">
									<jsp:include page="/diarymanagement/pages/addpriscription.jsp"></jsp:include>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary hidden"
										onclick="saveTemplae(0)">Save Template</button>
									<button type="button" class="btn btn-primary"
										onclick="insertGynicPriscription(0)"  id="prescs_save_btn">Save</button>
									<!-- <button type="button" class="btn btn-primary"
										onclick="insertEmrPriscription(1)" id="prescs_save_print_btn">Save & Print</button> -->
										<button type="button" class="btn btn-primary hidden"
										onclick="insertGynicPriscription(1)" id="prescs_save_print_btn">Save & Print</button>
				
									<button type="button" class="btn btn-primary hidden"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
						
					</div>
	
					<!-- add invesgtigation Modal -->
					<div class="modal fade" id="investigationpopup" tabindex="-1"
						role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true"
						data-keyboard="false" data-backdrop="static"
						style="background-color: rgba(0, 0, 0, 0.72);">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header bg-primary">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h5 class="modal-title" id="">
										Create Investigation For <b class="pname" id="invstcmyModalLabel">NAME:
										</b>
									</h5>
								</div>
								<div class="modal-body" style="padding: 0px;">
					
					
									<%@ include file="/emr/pages/addInvestigation.jsp"%>
					
					
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary"
										onclick="insertInvestigation(0)">Save</button>
									<%-- <%if(loginInfo.isInvest_savenprint()){ %>
											 <button type="button" class="btn btn-primary"
											onclick="saveIpdInvestigation(1)">Save & Print</button> 
											<%} %> --%>
									<button type="button" class="btn btn-primary hidden"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>

					<!-- Examination Popup  -->
					<div id="examiform" class="modal fade" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Examination</h4>
								</div>
								<div class="modal-body">
									<div class="">
										<div class="col-lg-12 col-xs-12 col-md-12"
											style="padding: 0px;">
											<div class="form-inline">
												<div class="form-group" style="width: 92%;">
													<input type="text" placeholder="Add Examination"
														class="form-control" style="width: 100%;">
												</div>
												<div class="form-group">
													<a href="#" class="btn btn-primary">Add</a>
												</div>
											</div>
										</div>
										<div class="col-lg-12 col-xs-12 col-md-12"
											style="padding: 0px;">
											<div class="form-group">
												<a href="#" class="btn btn-info">P / S</a>&nbsp;&nbsp;<a
													href="#" class="btn btn-info">A / V</a>
											</div>
											<div class="form-group">
												<span class="btn btn-default">C.N.S &nbsp;&nbsp;<span
													style="float: right;"><a href="#"
														style="color: #555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;<a
														href="#" style="color: #555;"><i class="fa fa-trash"></i></a></span></span>&nbsp;&nbsp;
												<span class="btn btn-default">C.N.S &nbsp;&nbsp;<span
													style="float: right;"><a href="#"
														style="color: #555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;<a
														href="#" style="color: #555;"><i class="fa fa-trash"></i></a></span></span>&nbsp;&nbsp;
												<span class="btn btn-default">P / A &nbsp;&nbsp;<span
													style="float: right;"><a href="#"
														style="color: #555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;<a
														href="#" style="color: #555;"><i class="fa fa-trash"></i></a></span></span>&nbsp;&nbsp;
												<span class="btn btn-default">P / S &nbsp;&nbsp;<span
													style="float: right;"><a href="#"
														style="color: #555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;<a
														href="#" style="color: #555;"><i class="fa fa-trash"></i></a></span></span>&nbsp;&nbsp;
											</div>
										</div>
										<div class="col-lg-12 col-xs-12 col-md-12"
											style="padding: 0px;">
											<div class="col-lg-7 col-md-7 col-xs-7" style="padding: 0px;">
												<div class="form-inline">
													<div class="form-group" style="width: 88%;">
														<input type="text" placeholder="Add Parameter"
															class="form-control" style="width: 100%;">
													</div>
													<div class="form-group">
														<a href="#" class="btn btn-primary">Add</a>
													</div>
												</div>
												<div class="scrolltable" style="margin-top: 3px;">
													<table class="table-responsive table">
														<tbody>
															<tr>
																<td style="width: 65%;"><label
																	class="checkbox checkbox-custom-alt m-0 mt-5"><input
																		type="checkbox"><i></i> Cervix</label></td>
																<td style="width: 25%;"><select
																	class="form-control" style="width: 100%;">
																		<option>Text Box</option>
																		<option>Text Area</option>
																		<option>Dropdown</option>
																		<option>Multiselect</option>
																		<option>Date</option>
																</select></td>
																<td class="text-center"><a href="#"
																	style="color: #555;"><i class="fa fa-pencil"></i></a></td>
																<td class="text-center"><a href="#"
																	style="color: #555;"><i class="fa fa-trash"></i></a></td>
															</tr>
															<tr>
																<td><label
																	class="checkbox checkbox-custom-alt m-0 mt-5"><input
																		type="checkbox"><i></i> Vagina</label></td>
																<td><select class="form-control"
																	style="width: 100%;">
																		<option>Text Box</option>
																		<option>Text Area</option>
																		<option>Dropdown</option>
																		<option>Multiselect</option>
																		<option>Date</option>
																</select></td>
																<td class="text-center"><a href="#"
																	style="color: #555;"><i class="fa fa-pencil"></i></a></td>
																<td class="text-center"><a href="#"
																	style="color: #555;"><i class="fa fa-trash"></i></a></td>
															</tr>
														</tbody>
													</table>
												</div>

											</div>
											<div class="col-lg-5 col-md-5 col-xs-5"
												style="padding-right: 0px;">
												<div class="form-inline">
													<div class="form-group" style="width: 80%;">
														<input type="text" placeholder="Add Parameter"
															class="form-control" style="width: 100%;">
													</div>
													<div class="form-group">
														<a href="#" class="btn btn-primary">Add</a>
													</div>
												</div>
												<article>
													<div class="scrolltable">
														<ul class="vertical default_list" id="">
															<li><label
																class="checkbox checkbox-custom-alt m-0 mt-5"><input
																	type="checkbox"><i></i> Normal</label></li>
															<li><label
																class="checkbox checkbox-custom-alt m-0 mt-5"><input
																	type="checkbox"><i></i> Bleeding Through OS +</label></li>
														</ul>
													</div>
												</article>
											</div>

										</div>

									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary">Save</button>
								</div>
							</div>

						</div>
					</div>


					<div class="panel-body hidden">
						<div
							class="col-lg-12 col-md-12 col-xs-6 col-sm-12 settopad bordertopgray backgrey">
							<div class="col-lg-2 col-md-2 col-xs-12 col-sm-3">
								<%-- <div class="form-group">

									<!--
								   <s:select list="userList" listKey="id" listValue="diaryUser" 
                                               cssClass="form-control chosen-select"  name="secndryconsult" id="secndryconsult" 
                                               headerKey="0" headerValue="Select Consultant"/>
								  -->

									<button type="button" style="width: 100%;"
										class="btn btn-default btn-sm dropdown-toggle hidden"
										data-toggle="dropdown">
										Secondary Consultant <span class="caret"></span>
									</button>
									<ul class="dropdown-menu hidden"
										style="margin-top: -15px; margin-left: 15px; width: 85%;">
										<s:iterator value="userList">
											<s:if test="status==1">
												<li><a href="#" class="small" data-value="option1"
													tabIndex="-1"><input type="checkbox"
														id="p<s:property value="id"/>" checked="checked" class=""
														value="<s:property value="id" />" />&nbsp;<span
														class="spandrop"><s:property value="diaryUser" /></span></a></li>
											</s:if>
											<s:else>
												<li><a href="#" class="small" data-value="option1"
													tabIndex="-1"><input type="checkbox"
														id="p<s:property value="id"/>"
														value="<s:property value="id" />" />&nbsp;<span
														class="spandrop"><s:property value="diaryUser" /></span></a></li>
											</s:else>
										</s:iterator>
									</ul>

								</div> --%>
							</div>
						</div>

						<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4 hidden">

							<div class="col-lg-4 col-md-4 col-xs-4 col-sm-6"
								style="margin-top: 23px;">
								<div class="form-group hidden">
									<label for="inputEmail" class="control-label">Hospital/Clinic</label>
									<s:textarea cssClass="form-control" rows="3" id="hosp"
										name="hosp" />
								</div>
							</div>
						</div>
					</div>
	</s:form>

</body>


<!-- Modal -->


<!-- Add New Patient -->
<%-- <div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Add New Patient</h4>
			</div>
			<div class="modal-body addnewpat">
				<%@ include file="/diarymanagement/pages/addPatientPage.jsp"%>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="savePatientNow"
					onclick="return savePatient()">Save</button>
				<button type="button" class="btn btn-primary hidden"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div> --%>









<script src="common/chosen_v1.1.0/chosen.jquery.js"
	type="text/javascript"></script>
<script src="common/chosen_v1.1.0/docsupport/prism.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var config = {
		'.chosen-select' : {},
		'.chosen-select-deselect' : {
			allow_single_deselect : true
		},
		'.chosen-select-no-single' : {
			disable_search_threshold : 10
		},
		'.chosen-select-no-results' : {
			no_results_text : 'Oops, nothing found!'
		},
		'.chosen-select-width' : {
			width : "100%"
		}
	}
	for ( var selector in config) {
		$(selector).chosen(config[selector]);
	}
</script>

<script
	src="_assets/newtheme/js/vendor/slimscroll/jquery.slimscroll.min.js"></script>
<script>
	$(".modal-draggable .modal-dialog").draggable({
		handle : ".modal-header"
	});
	$(function() {
		$('.addnewpat').slimScroll({
			height : '500px',
			railVisible : true,
			alwaysVisible : true
		});
		$('.patientlist').slimScroll({
			height : '430px',
			railVisible : true,
			alwaysVisible : true
		});
		$('.secoconsullist').slimScroll({
			height : '300px',
			railVisible : true,
			alwaysVisible : true
		});
		$('.scrolltable').slimScroll({
			height : '210px',
			railVisible : true,
			alwaysVisible : true
		});
		$('.scrolltable7').slimScroll({
			height : '98px',
			railVisible : true,
			alwaysVisible : true
		});
		$('.scrolltable1').slimScroll({
			height : '240px',
			railVisible : true,
			alwaysVisible : true
		});

	});
</script>
<!-- JS -->
<script type="text/javascript"
	src="inventory/js/searchtext/javascripts/vendor/jquery.hideseek.min.js"></script>
<script type="text/javascript"
	src="inventory/js/searchtext/javascripts/vendor/rainbow-custom.min.js"></script>
<script type="text/javascript"
	src="inventory/js/searchtext/javascripts/vendor/jquery.anchor.js"></script>
<script src="inventory/js/searchtext/javascripts/initializers.js"></script>
<!-- JS ends -->




</html>
