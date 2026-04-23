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
		$("#nst_date1").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#nst_date2").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#nst_date3").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#nst_date4").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#nst_date5").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#nst_date6").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#usgdate1").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#usgdate2").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true
		});
		$("#usgdate3").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		$("#usgdate4").datepicker({

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

		$("#correctededd").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});

		$("#ivf_date").datepicker({

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
		
		$("#pmp").datepicker({

			dateFormat : 'dd-mm-yy',
			yearRange : yearrange,
			minDate : '30-12-1880',
			changeMonth : true,
			changeYear : true

		});
		
		

		/* var d=document.getElementById("clientid").value;
		if(d==0 || d==''){
			showPopUp();
		}  else {
		    getIpdClientInfo(d);
		}
		 */
		 
		    var t="0";
		 
		    $('.classD').each(function() { 
				if(this.checked == true){
					t=t+","+this.value;
				}
			});
		 
		    document.getElementById("selectedid").value=t;

	});

	bkLib.onDomLoaded(function() {

		//new nicEditor().panelInstance('declarationNotes');
		//new nicEditor({maxHeight : 250}).panelInstance('admissiondetails');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('admissiondetails');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('suggestedtrtment');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 150
		}).panelInstance('chiefcomplains');

		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('addmissionfor');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('reimbursment');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('hosp');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('packagename');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 150
		}).panelInstance('presentillness');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('suggestoper');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('systdepartment');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('fpnotest');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('nauseanotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('fnunotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('chestpainnotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('dimvisionnotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hgunotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hmnotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('jointpainnotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('specialnotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('edemafeetnotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('hematurianotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('bmnotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('oligurianotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('pstgunotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('ihnotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('bmenotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('tnenotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('weaknessnotes');
		// new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('constpationnotes');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('pasthistory');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('personalhist');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('familyhist');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('onexamination');
		//new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 70}).panelInstance('smnotes');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('alergies');
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline',
					'strikeThrough', 'subscript', 'superscript', 'html',
					'image' ],
			maxHeight : 70
		}).panelInstance('earlierinvest');

		$('.nicEdit-panelContain').parent().width('auto');
		$('.nicEdit-panelContain').parent().next().width('auto');

		$('.nicEdit-main').width('100%');
		// $('.nicEdit-main').height('480px');
	});
</script>


</head>
<body>



	<s:form action="updategynicformIpd?action=0" theme="simple" id="ipdadmissionfrm">

		<s:hidden name="id" id="editselectedid"/>
		<s:hidden name="visit_reason_ids" id="visit_reason_ids" />
		<s:hidden id="clientid" name="clientid" />
		<input type="hidden" id="disbedid" value="1" />
		<s:hidden name="treatmentepisodeid" id="treatmentepisodeid" />
		<s:hidden name="conditionid" id="conditionid" />
		<s:hidden name="formtype" id="formtype" />
		<s:hidden  id="selectedid" value="0" />
		
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
}
</style>



		<div class="row">
			<div class="col-lg-12 col-md-12 col-xs-12 textprimegreen">
				<div class="col-lg-6 col-xs-12 col-md-6 text-left"
					style="padding-left: 0px;">
					<h3>
						<s:property value="clinicName" />
					</h3>
				</div>
				<div class="col-lg-6 col-xs-6 col-md-6 text-right hidden-xs"
					style="padding-right: 0px;">
					<h3>Obstretics and Gynaecology Assessment Form</h3>
				</div>
			</div>

			<div class="col-lg-12 col-md-12 col-xs-12"
				style="padding-top: 5px; padding-bottom: 5px;">
				<span style="color: brown;">IMPORTANT: Please completed all
					required field <font color="red">*</font>
				</span>
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12 textprime">
				<h5>PERSONAL DETAILS</h5>
			</div>
			<div class="col-lg-12 col-xs-12 col-md-12"
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

									<s:if test="formtype==1">

										<li class="active"><a href="#"
											onclick="switchgynicfomr(1)" data-toggle="tab"> <b>
													Obstetrics</b>
										</a></li>
										<li><a href="#" onclick="switchgynicfomr(2)"
										data-toggle="tab"> <!-- <a href="#tab_default_2" onclick="switchgynicfomr(2)"  data-toggle="tab"> -->
											<b>Gynecology</b>
									</a></li>
									   <li><a href="#" onclick="switchgynicfomr(3)"
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

				<div
					class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad bordertopgray hidden">
					<div class="col-lg-4 col-md-4 col-xs-12 col-sm-4">
						<div class="form-group">
							<label for="inputEmail" class="control-label">Admission
								For</label>
							<s:textarea cssClass="form-control" rows="3" cols="5"
								id="addmissionfor" name="addmissionfor" />
						</div>
						<div class="form-group hidden">
							<label for="inputEmail" class="control-label">Reimbursement</label>
							<s:textarea cssClass="form-control" rows="3" cols="5"
								id="reimbursment" name="reimbursment" />
						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-xs-12 col-sm-4">
						<div class="form-group">
							<label for="inputEmail" class="control-label">H/O
								ALLERGIES</label>
							<s:textarea cssClass="form-control" rows="3" id="alergies"
								name="alergies" />
						</div>
						<div class="form-group hidden">
							<label for="inputEmail" class="control-label">Admission
								Details</label>
							<s:textarea cssClass="form-control" rows="3" cols="5"
								name="admissiondetails" id="admissiondetails" />

						</div>
					</div>
					<div class="col-lg-4 col-md-4 col-xs-12 col-sm-4">
						<div class="form-group">
							<label for="inputEmail" class="control-label">Package</label>
							<s:textarea cssClass="form-control" rows="3" id="packagename"
								name="packagename" />
						</div>

					</div>

				</div>

				<div class="col-lg-12 col-xs-12 col-md-12 textprime hidden">
					<h5>ADMISSION SUMMARY</h5>
				</div>


				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad hidden">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-group hidden">
							<label for="exampleInputName2">Reason For Admission</label>
							<s:textfield name="admission_reason" id="admission_reason"
								cssClass="form-control" />

						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
						<div class="form-inline">
							<div class="form-group">
								<label for="exampleInputName2">Chief Complaints</label>
								<s:select cssClass="form-control" list="chief_complaints_list"
									listKey="id" listValue="name"
									onchange="setChiefComplaints(this.value)" headerKey="0"
									headerValue="Select Template">

								</s:select>
								<div class="form-group">
									<select class="form-control"
										onChange="setchiefcomp(this.value)">
										<option>Select</option>
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
										<option>6</option>
										<option>7</option>
										<option>8</option>
										<option>9</option>
										<option>10</option>
										<option>11</option>
										<option>12</option>
										<option>13</option>
										<option>14</option>
										<option>15</option>
										<option>16</option>
										<option>17</option>
										<option>18</option>
										<option>19</option>
										<option>20</option>
										<option>21</option>
										<option>22</option>
										<option>23</option>
										<option>24</option>
										<option>25</option>
										<option>26</option>
										<option>27</option>
										<option>28</option>
										<option>29</option>
										<option>30</option>
									</select>
								</div>
								<div class="form-group">
									<select class="form-control"
										onChange="setchiefcomp(this.value)">
										<option>Days</option>
										<option>Month</option>
										<option>Year</option>
									</select>
								</div>
								<div class="form-group">
									<input type="text" name="chiefcomplatetempname"
										class="form-control setbackcolor"
										placeholder="Enter template name">
								</div>
								<!--<select class="form-control" style="margin-right: 10px;">
									  <option>Select DD</option>
									  <option>1</option>
									  <option>2</option>
									  <option>3</option>
									  <option>4</option>
									</select>
								  -->
							</div>
							<!--
								  -->
							<!--<button type="submit" class="btn btn-primary">+</button>
								  
								-->
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="chiefcomplains" id="chiefcomplains" />
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden">
						<div class="form-inline">
							<div class="form-group">
								<label for="exampleInputName2">Present Illness</label>
								<s:select list="present_illness_list" cssClass="form-control"
									headerKey="0" onchange="getpresentIllness(this.value)"
									headerValue="Select Template" listKey="id" listValue="name">
								</s:select>
							</div>
							<div class="form-group">
								<input type="text" name="presentillnesstempname"
									class="form-control setbackcolor"
									placeholder="Enter template name">
							</div>
							<!--<button type="submit" class="btn btn-primary">+</button>
								-->
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<s:textarea cssClass="form-control" rows="6" maxlength=""
								name="presentillness" id="presentillness" />
						</div>
					</div>

				</div>


				<div">
					<div class="col-lg-12 col-xs-12 col-md-12 textprime hidden">
						<h5>HISTORY</h5>
					</div>

				</div>

				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad hidden">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<div class="form-inline">
									<div class="form-group">
										<label for="exampleInputName2">Past History</label>
										<s:select list="past_history_list" cssClass="form-control"
											onchange="setpasthistory(this.value)" listKey="id"
											listValue="name" headerKey="0" headerValue="Select Template">
										</s:select>
									</div>
									<!--<button type="submit" class="btn btn-primary">+</button>
											-->
									<div class="form-group">
										<input type="text" name="pasthistorytempname"
											class="form-control setbackcolor"
											placeholder="Enter template name">
									</div>
								</div>
								<div class="form-group" style="margin-top: 10px;">
									<s:textarea cssClass="form-control" rows="3" maxlength=""
										name="pasthistory" id="pasthistory"></s:textarea>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<div class="form-inline">
									<div class="form-group">
										<label for="exampleInputName2">Family History</label>
										<s:select list="family_history_list" cssClass="form-control"
											onchange="getFamilyhistory(this.value)" listKey="id"
											listValue="name" headerKey="0" headerValue="Select Template">
										</s:select>
									</div>
									<!--<button type="submit" class="btn btn-primary">+</button>
											-->
									<div class="form-group">
										<input type="text" name="familyhistorytempname"
											class="form-control setbackcolor"
											placeholder="Enter template name">
									</div>
								</div>
								<div class="form-group" style="margin-top: 10px;">
									<s:textarea cssClass="form-control" rows="3" maxlength=""
										name="familyhist" id="familyhist"></s:textarea>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<div class="form-inline">
									<div class="form-group">
										<label for="exampleInputName2">Personal History</label>
										<s:select list="personal_hist_list"
											onchange="getpersonaltemp(this.value)"
											cssClass="form-control" listKey="id" listValue="name"
											headerKey="0" headerValue="Select Template">
										</s:select>
									</div>
									<!--<button type="submit" class="btn btn-primary">+</button>
											-->
									<div class="form-group">
										<input type="text" name="personalhistorytempname"
											class="form-control setbackcolor"
											placeholder="Enter template name">
									</div>
								</div>
								<div class="form-group" style="margin-top: 10px;">
									<s:textarea cssClass="form-control" rows="3" maxlength=""
										name="personalhist" id="personalhist"></s:textarea>
								</div>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<div class="form-inline">
									<div class="form-group">
										<label for="exampleInputName2">On Examination</label>
										<s:select list="on_exam_list"
											onchange="getonexamtemp(this.value)" cssClass="form-control"
											listKey="id" listValue="name" headerKey="0"
											headerValue="Select Template">
										</s:select>
									</div>
									<!--<button type="submit" class="btn btn-primary">+</button>
											-->
									<div class="form-group">
										<input type="text" name="onexaminationtempname"
											class="form-control setbackcolor"
											placeholder="Enter template name">
									</div>
								</div>
								<div class="form-group" style="margin-top: 10px;">
									<s:textarea cssClass="form-control" rows="3" maxlength=""
										name="onexamination" id="onexamination"></s:textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="form-inline">
									<div class="form-group">
										<label for="exampleInputName2">Treatment Given</label>
										<s:select list="treatment_given_list"
											onchange="gettreatmenttemp(this.value)" listKey="id"
											cssClass="form-control" listValue="name" headerKey="0"
											headerValue="Select Template">
										</s:select>
									</div>
									<!--
											  <button type="submit" class="btn btn-primary">+</button>
											-->
									<div class="form-group">
										<input type="text" name="treatmentgiventempname"
											class="form-control setbackcolor"
											placeholder="Enter template name">
									</div>
								</div>
								<div class="form-group" style="margin-top: 10px;">
									<s:textarea cssClass="form-control" rows="3"
										name="suggestedtrtment" id="suggestedtrtment" />
								</div>
							</div>

						</div>
					</div>

				</div>


				<div class="tab-content" style="min-height: 50px !important;">
					<div class="tab-pane active" id="tab_default_1">


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
									<div class="scrolltable" id="scrolltable">
										 <s:iterator value="allVisitReasonList">
												<h5 style="margin-bottom: 5px;margin-top: 5px;"><b><s:property value="reasonvisit" /></b>&nbsp;&nbsp;<a href="#" data-toggle="modal" data-target="#rvisit"><i class="fa fa-pencil"></i></a></h5>
												<p style="margin: 0px;">Region : <s:property value="reason" /></p>
												<p style="margin: 0px;">Quality :<s:property value="quality" /></p>
												<p style="margin: 0px;">Periodicity :<s:property value="periodicity" /></p>
												<p style="margin: 0px;">Influencing Factor :<s:property value="influence"/></p>
												<p style="margin: 0px;">Since :<s:property value="since"/></p>
												<p style="margin: 0px;">Note :<s:property value="notes"/></p>
												<!-- <hr>
												<h5 style="margin-bottom: 5px;margin-top: 5px;"><b>Pain in lower abdomen</b>&nbsp;&nbsp;<a href="#" data-toggle="modal" data-target="#rvisit"><i class="fa fa-pencil"></i></a></h5>
												<p style="margin: 0px;">Region :</p>
												<p style="margin: 0px;">Quality :</p>
												<p style="margin: 0px;">Periodicity :</p>
												<p style="margin: 0px;">Influencing Factor :</p>
												<p style="margin: 0px;">Since :</p>
												<p style="margin: 0px;">Note :</p> -->
											</s:iterator>	
									</div>
								</div>

							</div>

							<div class="col-lg-3 col-md-3 col-xs-2 col-sm-3">
								<div class="scrolltable scrolltableheightleft">
									 <s:if test="formtype==1">
												     <h5 class="lighbdsub textalignleft">
													<b>Present Preg</b>
												</h5>
												<div class="form-group textalignleft">
													<label>LMP</label>
													<s:textfield name="lmp" id="lmp" readonly="true"
														onchange="calEdd(this.value)" cssClass="form-control" />
												</div>
												<div class="form-group textalignleft">
													<label>EDD</label><br>
													<s:textfield name="edd" id="edd" readonly="true"
														cssClass="form-control" />
												</div>
												<div class="form-group textalignleft">
													<label>USG/Corrected EDD</label>
													<s:textfield name="usg" id="correctededd"
														cssClass="form-control" />
												</div>
									 
									 </s:if>
									 <s:elseif test="formtype==2">
									    <!-- Gynic -->
									 		<!-- Infertility -->
									       <div class="form-group textalignleft">
													<label>LMP</label>
													<s:textfield name="lmp" id="lmp" readonly="true"
														 cssClass="form-control" />
												</div>
												 <div class="form-group textalignleft">
													<label>PMP</label>
													<s:textfield name="pmp" id="pmp" readonly="true"
														 cssClass="form-control" />
												</div>
									 
									 </s:elseif>
									 <s:else>
									     	<!-- Infertility -->
									       <div class="form-group textalignleft">
													<label>LMP</label>
													<s:textfield name="lmp" id="lmp" readonly="true"
														 cssClass="form-control" />
												</div>
												 <div class="form-group textalignleft">
													<label>PMP</label>
													<s:textfield name="pmp" id="pmp" readonly="true"
														 cssClass="form-control" />
												</div>
									 
									 </s:else>
								</div>
							</div>

						</div>


						<s:if test="formtype!=1">
						
						        	<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>PERSONAL H/O</h5>
						</div>

						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
							style="padding: 0px;">
							<div class="">

								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<h5 class="lighbdsub">
										<b>Habits</b>
									</h5>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"
										style="padding-left: 0px;">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Alcohol</label>
												<s:select
													list="#{'No':'No','Daily':'Daily','Weekly':'Weekly','Monthly':'Monthly','Occasionally':'Occasionally','Chronic':'Chronic','Quit Before Some Time':'Quit Before Some Time'}"
													theme="simple" name="alcohal" cssClass="form-control"></s:select>

											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Drugs</label>
												<%-- <s:textfield name="drugs" onkeyup="onlyNum(this)"  cssClass="form-control" />  --%>
												<s:textfield name="drugs" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Other Medications</label>
												<s:textfield name="other_medication" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Tobacco</label>
												<s:select list="#{'No':'No','Yes':'Yes'}" theme="simple"
													name="tobaco" cssClass="form-control"></s:select>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Smoking</label>
												<s:textfield name="smoking" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"
										style="padding-right: 0px;">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Tobacco</label>
												<s:textfield name="tobaconotes" cssClass="form-control" />
											</div>
										</div>
									</div>


								</div>

								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<h5 class="lighbdsub">
										<b></b>
									</h5>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"
										style="padding-left: 0px;">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Age at menarche</label>
												<s:textfield name="age_menarche" onkeyup="onlyNum(this)"
													placeholder="Yrs" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 hidden">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">L.M.P</label>

												<s:textfield name="" placeholder="" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">L.L.M.P</label>
												<s:textfield name="llmp" placeholder=""
													cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">PMC</label>
												<s:select
													list="#{'0':'Select','Regular':'Regular','Irregular':'Irregular'}"
													theme="simple" name="pmc" cssClass="form-control"></s:select>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Cycle Length</label>
												<s:textfield name="cycle_length" placeholder="Days"
													cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"
										style="padding-right: 0px;">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Duration of Flow</label>

												<s:textfield name="duration_flow" placeholder="Days"
													cssClass="form-control" />
											</div>
										</div>
									</div>

								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"
										style="padding-left: 0px;">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Amount of Flow</label>

												<s:select
													list="#{'0':'Select','Normal':'Normal','Light':'Light','Heavy':'Heavy','With Medication':'With Medication','Without Medication':'Without Medication'}"
													theme="simple" name="amount_flow" cssClass="form-control"></s:select>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Dysmenorrhea</label>

												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}"
													name="dysmenorrhea" theme="simple" cssClass="form-control"></s:select>


											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Dyspareunia</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}"
													name="dyspareunia" theme="simple" cssClass="form-control"></s:select>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">H/O Passing Clots</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}"
													name="hopassing_clots" theme="simple"
													cssClass="form-control"></s:select>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">White Discharge &
													Itching</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}"
													name="white_disc_itching" theme="simple"
													cssClass="form-control"></s:select>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"
										style="padding-right: 0px;">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Intercourse Frequency</label>
												<s:textfield name="intercourse_freq" placeholder=""
													cssClass="form-control" />
											</div>
										</div>
									</div>

								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"
										style="padding-left: 0px;">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Galactorrea</label>
												<s:select list="#{'0':'Select','No':'No','Yes':'Yes'}"
													name="galactorrea" theme="simple" cssClass="form-control"></s:select>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">H/O Contraception</label>
												<s:textfield name="ho_contraception" placeholder=""
													cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Rubella Vaccine</label>
												<s:textfield name="rubella_vaccine" placeholder=""
													cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
										<div class="">
											<div class="form-group">
												<label for="exampleInputName2">Menopause</label>
												<s:textfield name="menopause" placeholder="Yrs"
													cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"></div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"
										style="padding-right: 0px;"></div>
								</div>

							</div>

						</div>
						  
						
						</s:if>					



						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>OBSTRETIC HISTORY</h5>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style="margin-bottom: 15px; border-bottom: 1px dashed #ddd; padding: 0px 0px 10px 0px;">
								<div class="col-lg-2 col-md-2 col-xs-12">
									<form>
										<label class=""><s:checkbox theme="simple"
												name="nulligravida" /><i></i> Nulligravida</label> <label class=""><s:checkbox
												name="current_pregnent" /><i></i> Currently Pregnent</label> <label
											class=""><s:checkbox name="iud" /><i></i> IUD</label>
									</form>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-6">
									<div class="form-group">
										<lable>Live Boys</lable>
										<s:textfield name="live_boys"
											onkeyup="addObsRow(this.value,'Boys')" value=""
											cssClass="form-control" />
									</div>

								</div>
								<div class="col-lg-2 col-md-2 col-xs-6">

									<div class="form-group">
										<lable>Live Girls</lable>
										<s:textfield name="live_girls"
											onkeyup="addObsRow(this.value,'Girls')" value=""
											cssClass="form-control" />
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-6">

									<div class="form-group">
										<lable>Stillbirths</lable>
										<s:textfield name="stillbirths" value=""
											onkeyup="addObsRow(this.value,'Stillbirths')"
											cssClass="form-control" />
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-6">

									<div class="form-group">
										<lable>Abortions</lable>
										<s:textfield name="abortions" value=""
											onkeyup="addObsRow(this.value,'Abortions')"
											cssClass="form-control" />
									</div>
								</div>

								<div class="col-lg-2 col-md-2 col-xs-6">
									<div class="form-group">
										<label>Death Children</label>
										<s:textfield name="death_children" value=""
											onkeyup="addObsRow(this.value,'Death Children')"
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
	                        					
	                        						<%int i=0; %>
	                        					<tbody>
	                        					    <s:iterator value="gynicobsList">
	                        						<tr>
	                        							<td style="width: 5%;padding-right: 15px;"><input type="number" name="" value="<%=i+1%>" class="form-control"> <input type="hidden" name="obslist[<%=i%>].id" value="<s:property value="id"/>" /></td>
	                        							<td style="width: 8%;padding-right: 15px;"><input type="number" name="obslist[<%=i%>].year" value="<s:property value="year"/>"  class="form-control" placeholder="year"></td>
	                        							<td style="width: 7%;padding-right: 15px;"><s:property value="type"/>  <input type="hidden" name="obslist[<%=i%>].type" value="<s:property value="type"/>" />  </td>
	                        							<td style="width: 13%;padding-right: 15px;"> 
	                        							 
	                        							    <input type="hidden" id="tempD<%=i%>" value="<s:property value="type_delivery"/>" />
		                        							<select name="obslist[<%=i%>].type_delivery" id="tobs_type<%=i%>" class="form-control">
				    												<option value="0">Type of Delivery</option>
				    												<option value="Normal">Normal</option>
				    												<option value="Vaccume">Vaccume</option>
				    												<option value="Forceps">Forceps</option>
				    												<option value="LSCS">LSCS</option>
				    												
															</select>
															<script>
															     var tt=document.getElementById('tempD<%=i%>').value;
	                        							         document.getElementById('tobs_type<%=i%>').value=tt;
	                        							   </script>
															
	                        							</td>
	                        							
													    <td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist[<%=i%>].indications" value="<s:property value="indications"/>" class="form-control"  placeholder="Indications"></td>
	                        							<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist[<%=i%>].coamplications" value="<s:property value="coamplications"/>" class="form-control" placeholder="Coamplications"></td>
	                        							<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist[<%=i%>].institution" value="<s:property value="institution"/>" class="form-control" placeholder="Institution"></td>	                        							
	                        							<td><a href="" onclick="deleteRow('obstable')" ><i class="fa fa-trash" aria-hidden="true" style="color: #d9534f;"></i></a></td>
	                        						</tr>
	                        						 <%i++; %>
	                        						</s:iterator>
	                        					</tbody>
	                        				</table>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
										<div class="form-group">
											<lable>Parity/Abortions Notes</lable>
											<s:textarea cssClass="form-control" theme="simple"
												name="parity_abortion_notes" rows="3" />
										</div>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12"
										style="padding: 0px;">
										<div class="col-lg-3 col-md-3 col-xs-3">
											<div class="form-group">
												<lable>P</lable>
												<s:textfield name="p" onkeyup="onlyNum(this)"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-lg-3 col-md-3 col-xs-3">
											<div class="form-group">
												<lable>L</lable>
												<s:textfield name="l" onkeyup="onlyNum(this)"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-lg-3 col-md-3 col-xs-3">
											<div class="form-group">
												<lable>A</lable>
												<s:textfield name="a" onkeyup="onlyNum(this)"
													cssClass="form-control" />
											</div>
										</div>
										<div class="col-lg-3 col-md-3 col-xs-3">
											<div class="form-group">
												<lable>D</lable>
												<s:textfield name="d" onkeyup="onlyNum(this)"
													cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>





						<s:if test="issystemicreview==true">
							<div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>
									<a data-toggle="collapse" href="#collapseExample"
										aria-expanded="false" aria-controls="collapseExample"
										style="color: white;">SYSTEMIC REVIEW &nbsp;&nbsp;<i
										class="fa fa-chevron-down"></i></a>
								</h5>
							</div>
						</s:if>

						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 settopad">
							<div class="collapse" id="collapseExample">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
										<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
											<table class="table table-condensed table-responsive">
												<tbody>

													<tr>
														<td class="widthtabhedth1"><label
															for="exampleInputName2">Fever at present</label></td>
														<td class="widthtabhedth2"><s:select
																name="fpcondition" id="fpcondition"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="fpnotest" id="fpnotest" /></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Nausea</label></td>
														<td><s:select name="nauseacondition"
																id="nauseacondition" list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3" cols="5"
																placeholder="Note" name="nauseanotes" id="nauseanotes" /></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Frequent
																Nocturnal Urination</label></td>
														<td><s:select name="fnucondition" id="fnucondition"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3" cols="5"
																placeholder="Note" name="fnunotes" id="fnunotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Straining
																at micturation</label></td>
														<td><s:select name="smcondition" id="smcondition"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" cols="5" name="smnotes" id="smnotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Chest pain</label></td>
														<td><s:select name="chestpaincond" id="chestpaincond"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" cols="5" name="chestpainnotes"
																id="chestpainnotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Dimness of
																vision</label></td>
														<td><s:select name="dimvisioncond" id="dimvisioncond"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" cols="5" name="dimvisionnotes"
																id="dimvisionnotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Headache,
																Giddiness, Unconsciousness</label></td>
														<td><s:select name="hgucondition" id="hgucondition"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" cols="5" name="hgunotes"
																id="hgunotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Haemoptysis,
																Malena</label></td>
														<td><s:select name="hmcondition" id="hmcondition"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="hmnotes" id="hmnotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Joint pain</label></td>
														<td><s:select name="jointpaincond" id="jointpaincond"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="jointpainnotes"
																id="jointpainnotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Edema feet</label></td>
														<td><s:select name="edemafeetcondi"
																id="edemafeetcondi" list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="edemafeetnotes"
																id="edemafeetnotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Hematuria</label></td>
														<td><s:select name="hematuriacondi"
																id="hematuriacondi" list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="hematurianotes"
																id="hematurianotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Burning
																micturation</label></td>
														<td><s:select name="bmcondition" id="bmcondition"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="bmnotes" id="bmnotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Oliguria</label></td>
														<td><s:select name="oliguriacondi" id="oliguriacondi"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="oligurianotes"
																id="oligurianotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Passing
																stone or gravel in the urine</label></td>
														<td><s:select name="pstgucondi" id="pstgucondi"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="pstgunotes" id="pstgunotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Impaired
																hearing</label></td>
														<td><s:select name="ihcondition" id="ihcondition"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="ihnotes" id="ihnotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Breathlessness
																on mild exertion</label></td>
														<td><s:select name="bmecondition" id="bmecondition"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="bmenotes" id="bmenotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Tingling,
																Numbness in extremities</label></td>
														<td><s:select name="tnecondition" id="tnecondition"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="tnenotes" id="tnenotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Weakness</label></td>
														<td><s:select name="weaknesscondi" id="weaknesscondi"
																list="#{'No':'No','Yes':'Yes'}" cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="weaknessnotes"
																id="weaknessnotes"></s:textarea></td>
													</tr>
													<tr>
														<td><label for="exampleInputName2">Constipation</label></td>
														<td><s:select name="constipationcond"
																id="constipationcond" list="#{'No':'No','Yes':'Yes'}"
																cssClass="form-control"></s:select></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="constpationnotes"
																id="constpationnotes"></s:textarea></td>
													</tr>

													<tr>
														<td><label for="exampleInputName2">Special
																Notes/Remarks</label></td>
														<td></td>
														<td><s:textarea
																cssClass="form-control textareaheight" rows="3"
																placeholder="Note" name="specialnotes" id="specialnotes"></s:textarea></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>

							</div>
						</div>

						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="form-inline">
										<div class="form-group">
											<label for="exampleInputName2">Earlier Investigations</label>
										</div>
									</div>
									<div class="form-group" style="margin-top: 10px;">
										<s:textarea cssClass="form-control" rows="3"
											name="earlierinvest" id="earlierinvest" />
									</div>
								</div>

							</div>
						</div>


						<div class="col-lg-12 col-xs-12 col-md-12 textprime hidden">
							<h5>DIAGNOSIS</h5>
						</div>


					</div>


					<!-- Hide by Abhi 12 Dec 2017 create for gyne -->
					<div>
						<s:if test="formtype==3">
						
						      <div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>IVF</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">IVF Date</label>
									<s:textfield cssClass="form-control" id="ivf_date"
										name="ivf_date " />
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Down Regulation</label>
									<s:select
										list="#{'0':'Select','GnRH Agorust':'GnRH Agorust','GnRH Antagonist':'GnRH Antagonist'}"
										theme="simple" cssClass="form-control" name="down_regulation"></s:select>

								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Ovarian Stimulation</label>
									<s:select
										list="#{'0':'Select','Recombinant FSH':'Recombinant FSH','Gonal-F':'Gonal-F','hMG':'hMG'}"
										theme="simple" cssClass="form-control"
										name="ovarian_stimulation"></s:select>

								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">OS Dosage</label>
									<s:select
										list="#{'0':'Select','IU 37.5':'IU 37.5','IU 75':'IU 75','IU 150':'IU 150'}"
										theme="simple" cssClass="form-control" name="os_dosage"></s:select>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Sperm Quality</label>
									<s:textfield name="sperm_quality" cssClass="form-control" />
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">ET Day</label>
									<s:select
										list="#{'0':'Select','2':'Day 2','3':'Day 3','4':'Day 4'}"
										theme="simple" cssClass="form-control" name="et_day"></s:select>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Oocytes Obtained</label>
									<s:select
										list="#{'0':'Select','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13'}"
										theme="simple" cssClass="form-control" name="oocytes_obtained"></s:select>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Oocytes Quality</label>
									<s:textfield name="oocytes_quality" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"></div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"></div>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Embroyos Grade</label>
									<s:select list="#{'0':'Select','1':'I','2':'II','3':'III'}"
										theme="simple" cssClass="form-control" name="embroyos_grade"></s:select>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Embroyos Transfered</label>
									<s:select
										list="#{'0':'Select','1':'1','2':'2','3':'3','4':'4'}"
										theme="simple" cssClass="form-control" name="embroyos_grade"></s:select>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Embroyos Description</label>
									<s:textarea cssClass="form-control" name="embroyos_description"
										rows="3" />
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"></div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6"></div>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Freezing</label>
									<s:select list="#{'0':'Select','Yes':'Yes','No':'No'}"
										theme="simple" cssClass="form-control" name="freezing"></s:select>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Transfer Process</label>
									<s:select list="#{'0':'Select','Yes':'Yes','No':'No'}"
										theme="simple" cssClass="form-control" name="transfer_process"></s:select>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Beta HCG-mIU/ml</label>
									<s:textarea cssClass="form-control" name="betahcgcm" rows="3" />
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
								<div class="form-group">
									<label for="exampleInputName2">Remark</label>
									<s:textarea cssClass="form-control" name="ivf_remark" rows="3" />
								</div>
							</div>
						</div>
						      
						
								
						</s:if>
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>FAMILY HISTORY</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
								<div class="form-group">
									<label for="exampleInputName2">Any Medical D/O in
										Family</label>
									<s:textarea cssClass="form-control" name="do_family_history"
										rows="3" />
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
								<div class="form-group">
									<label for="exampleInputName2">H/O in Infertility in
										family</label>
									<s:textarea cssClass="form-control" name="ho_fertility_family"
										rows="3" />
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
								<div class="form-group">
									<label for="exampleInputName2">H/O genetic anomalies in
										Family</label>
									<s:textarea cssClass="form-control" name="ho_genetic_family"
										rows="3" />
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3">
								<div class="form-group">
									<label for="exampleInputName2">H/O premature menopause</label>
									<s:textarea cssClass="form-control" name="ho_premature_family"
										rows="3" />
								</div>
							</div>
						</div>

						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>MENSTRUAL H/O</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 0px;">
							<div class="col-lg-12 col-xs-12 col-md-12">

								<div class="col-lg-2 col-xs-2 col-md-2"
									style="padding-left: 0px;">
									<h5 class="lighbdsub">
										<b> Cycle Periodicity</b>
									</h5>
									<div class="form-group">
										<article>
											<div class="">
												  <ul class="vertical default_list" id="">
									                     
									                       
									                      
									                       <li>
									                       	<s:if test="Age_of_menarche=='Regular'">
									                       			<label class="checkbox checkbox-custom"><input type="radio" checked="checked"  name="age_of_menarche" value="Regular" /><i></i> Regular</label>
									                       	</s:if>
									                       	<s:else>
									                       			<label class="checkbox checkbox-custom"><input type="radio" name="age_of_menarche" value="Regular" /><i></i> Regular</label>
									                       	
									                       	</s:else> 
									                       
									                       </li>
									                       <div class="heightdiva"></div>
									                       <s:if test="Age_of_menarche=='Irregular'">
									                       			<li><label class="checkbox checkbox-custom"><input type="radio" checked="checked"  name="age_of_menarche" value="Irregular" /><i></i> Irregular</label></li>
									                       </s:if>
									                       <s:else>
									                       			<li><label class="checkbox checkbox-custom"><input type="radio"  name="age_of_menarche" value="Irregular" /><i></i> Irregular</label></li>
									                       </s:else>
									                      
									          </ul>
											</div>
										</article>
										<s:textarea cssClass="form-control"
											name="age_of_menarche_notes" rows="3" />
									</div>
								</div>
								<div class="col-lg-2 col-xs-2 col-md-2">
										<h5 class="lighbdsub"><b>Dysmenorrhoea</b></h5>
										<div class="form-group">
							       			<article>
										  <div class="">
										     <ul class="vertical default_list" id="">
										     			 <s:if test="dysmenorrhoe=='Present'">
										     			 	
									                       <li><label class="checkbox checkbox-custom"><input type="radio" checked="checked"  name="dysmenorrhoe" value="Present" /><i></i> Present</label></li>
									                       </s:if>
									                       <s:else>
									                       		<li><label class="checkbox checkbox-custom"><input type="radio"  name="dysmenorrhoe" value="Present" /><i></i> Present</label></li>
									                       </s:else>
									                       
									                       <div class="heightdiva"></div>
									                        <s:if test="dysmenorrhoe=='Absent'">
									                        		<li><label class="checkbox checkbox-custom"><input type="radio"  name="dysmenorrhoe" value="Absent"/><i></i> Absent</label></li>
									                        </s:if>
									                        <s:else>
									                        		<li><label class="checkbox checkbox-custom"><input type="radio"  name="dysmenorrhoe" value="Absent"/><i></i> Absent</label></li>
									                        </s:else>	
									                       
									                      
									          </ul>
										   </div>
										 </article>
										 <s:textarea cssClass="form-control" name="dysmenorrhoe_notes" rows="3" />
							       		</div>
									</div>
								<div class="col-lg-2 col-xs-2 col-md-2">
										<h5 class="lighbdsub"><b>Flow</b></h5>
										<div class="form-group">
							       			<article>
										  <div class="">
										     <ul class="vertical default_list" id="">
										                 <s:if test="flow=='Moderate'">
									                       <li><label class="checkbox checkbox-custom"><input type="radio" checked="checked"  name="flow" value="Moderate" /><i></i> Moderate</label></li>
									                       </s:if>
									                       <s:else>
									                       	<li><label class="checkbox checkbox-custom"><input type="radio"  name="flow" value="Moderate" /><i></i> Moderate</label></li>
									                       </s:else>
									                        <div class="heightdiva"></div>
									                        <s:if test="flow=='Severe'">
									                        		<li><label class="checkbox checkbox-custom"><input type="radio" checked="checked"  name="flow" value="Severe" /><i></i> Severe</label></li>
									                        </s:if>
									                        <s:else>
									                        		<li><label class="checkbox checkbox-custom"><input type="radio"  name="flow" value="Severe" /><i></i> Severe</label></li>
									                        </s:else>
									                       
									                      
									          </ul>
										   </div>
										 </article>
										 <s:textarea cssClass="form-control" name="flow_notes" rows="3" />
							       		</div>
									</div>
								<div class="col-lg-3 col-xs-3 col-md-3">
										<h5 class="lighbdsub"><b>Sleep disruption due to bleeding</b></h5>
										<div class="form-group">
							       			<article>
										  <div class="">
										     <ul class="vertical default_list" id="">
										                 <s:if test="sleep_disruption_bleeding=='Yes'">
										                 		 <li><label class="checkbox checkbox-custom"><input type="radio" checked="checked" name="sleep_disruption_bleeding" value="Yes" /><i></i> Yes</label></li>
										                 </s:if>
										                 <s:else>
										                 		 <li><label class="checkbox checkbox-custom"><input type="radio"  name="sleep_disruption_bleeding" value="Yes" /><i></i> Yes</label></li>
										                 </s:else>
									                      	
									                        <div class="heightdiva"></div>
									                      <s:if test="sleep_disruption_bleeding=='No'">
									                      		 <li><label class="checkbox checkbox-custom"><input type="radio"  name="sleep_disruption_bleeding" value="No" /><i></i> No</label></li>
									                      </s:if>
									                      <s:else>
									                      		 <li><label class="checkbox checkbox-custom"><input type="radio"  name="sleep_disruption_bleeding" value="No" /><i></i> No</label></li>
									                      </s:else>  	
									                      
									                      
									          </ul>
										   </div>
										 </article>
										 <s:textarea cssClass="form-control" name="sleep_disruption_bleeding_notes" rows="3" />
							       		</div>
									</div>
								<div class="col-lg-3 col-xs-3 col-md-3" style="padding-right:0px;">
										<h5 class="lighbdsub"><b>Episode of Giddiness / Blackouts</b></h5>
										<div class="form-group">
							       			<article>
										  <div class="">
										     <ul class="vertical default_list" id="">
										     		<s:if test="blachouts=='Yes'">
										     				 <li><label class="checkbox checkbox-custom"><input type="radio" checked="checked" name="blachouts" value="Yes" /><i></i> Yes</label></li>
										     		</s:if>
										     		<s:else>
										     			 <li><label class="checkbox checkbox-custom"><input type="radio"  name="blachouts" value="Yes" /><i></i> Yes</label></li>
										     		</s:else>
									                      
									                       <div class="heightdiva"></div>
									                <s:if test="blachouts=='No'">
									                		     <li><label class="checkbox checkbox-custom"><input type="radio" checked="checked"  name="blachouts" value="Yes" /><i></i> No</label></li>
									                </s:if>
									                <s:else>
									                		     <li><label class="checkbox checkbox-custom"><input type="radio"  name="blachouts" value="Yes" /><i></i> No</label></li>
									                </s:else>
									                       
									                  
									                      
									          </ul>
										   </div>
										 </article>
										 <s:textarea cssClass="form-control" name="blachouts_notes" rows="3" />
							       		</div>
									</div>
							</div>


						</div>





						<div class="col-lg-12 col-xs-12 col-md-12 textprime hidden">
							<h5>MEDICAL HISTORY</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12 hidden"
							style="padding: 0px 0px 0px 0px;">
							<div class="col-lg-2 col-md-2 col-xs-2">
								<h5 class="lighbdsub">
									<b>For all</b>
								</h5>
								<div class="form-group">
									<label>Known Case of</label>
									<s:select
										list="#{'0':'Select','Hypothyroidism':'Hypothyroidism','Asthma':'Asthma','Ulcerative Colitis':'Ulcerative Colitis','Hypertension':'Hypertension','Diahetes Meltitus':'Diahetes Meltitus','Ischaenmic Heart D/s':'Ischaenmic Heart D/s','Pelric Surgery':'Pelric Surgery','Obesity':'Obesity','STD':'STD','HIV':'HIV','Urticaria':'Urticaria','ITP':'ITP'}"
										theme="simple" name="known_case" cssClass="form-control"></s:select>
								</div>
							</div>
							<div class="col-lg-10 col-md-10 col-xs-10">
								<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
									<div class="col-lg-6 col-md-6 col-xs-6">
										<h5 class="lighbdsub">
											<b>Pt.History</b>
										</h5>
										<div class="col-lg-12 col-md-12" style="padding: 0px;">
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1a" type="radio"><i></i> Present
													</label>
												</div>
											</div>
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;"></div>
										</div>
										<div class="col-lg-12 col-md-12" style="padding: 0px;">
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1a" type="radio"><i></i> Absent
													</label>
												</div>
											</div>
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;"></div>
										</div>
										<div class="col-lg-12 col-md-12" style="padding: 0px;">
											<s:textarea cssClass="form-control" name="pt_history_notes"
												id="pt_history_notes" rows="3" />
										</div>


									</div>
									<div class="col-lg-6 col-md-6 col-xs-6"
										style="padding-right: 0px;">
										<h5 class="lighbdsub">
											<b>Family History</b>
										</h5>
										<div class="col-lg-12 col-md-12" style="padding: 0px;">
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1" type="radio"><i></i> Father
													</label>
												</div>
											</div>
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1" type="radio"><i></i> Mother
													</label>
												</div>

											</div>
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1" type="radio"><i></i> Brother
													</label>
												</div>

											</div>
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1" type="radio"><i></i> Sister
													</label>
												</div>

											</div>
										</div>
										<div class="col-lg-12 col-md-12" style="padding: 0px;">
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1" type="radio"><i></i> Spouse
													</label>
												</div>
											</div>
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1" type="radio"><i></i>
														Pat.Relative
													</label>
												</div>

											</div>
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1" type="radio"><i></i>
														Mat.Relative
													</label>
												</div>

											</div>
											<div class="col-lg-3 col-xs-3 col-md-3"
												style="padding-left: 0px;">
												<div class="form-group">
													<label class="checkbox checkbox-custom"> <input
														name="customRadio1" type="radio"><i></i> Other
													</label>
												</div>

											</div>
										</div>
										<div class="col-lg-12 col-md-12" style="padding: 0px;">
											<s:textarea cssClass="form-control"
												name="family_history_notes" id="family_history_notes"
												rows="3" />
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>SURGICAL H/O</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12"
							style="padding-top: 10px;">
							<s:textarea cssClass="form-control" name="surgical_ho" rows="3" />
						</div>

						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>DIAGNOSIS</h5>
						</div>
						
						 <s:if test="formtype==1">
						 		
						 			<div class="col-lg-12 col-xs-12 col-md-12"
							style="padding: 10px 0px 0px 0px;">
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
								<div class="form-group">
									<label>Gravida</label>
									<s:textfield type="number" name="gravida"
										cssClass="form-control" min="2" />

								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
								<div class="form-group">
									<label>Para</label>
									<s:textfield type="number" name="para" cssClass="form-control"
										min="2" />
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
								<div class="form-group">
									<label>Live</label>
									<s:textfield type="number" name="live" cssClass="form-control"
										min="2" />
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
								<div class="form-group">
									<label>Abortion</label>
									<s:textfield type="number" name="abortion"
										cssClass="form-control" min="2" />
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
								<div class="form-group">
									<label>MTP</label>
									<s:textfield type="number" name="mtp" cssClass="form-control"
										min="2" />
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
								<div class="form-group">
									<label>Still Born</label>
									<s:textfield type="number" name="still_born"
										cssClass="form-control" min="2" />
								</div>
							</div>
							<div class="col-lg-1 col-md-1 col-xs-1 col-sm-1">
								<div class="form-group">
									<label>Death</label>
									<s:textfield type="number" name="death" cssClass="form-control"
										min="2" />
								</div>
							</div>
							<div class="col-lg-5 col-md-5 col-xs-5 col-sm-5">
								<div class="form-group">
									<label>With High Risk Factor</label>
									<s:textarea cssClass="form-control" name="high_risk_factor"
										rows="3" />
								</div>
							</div>
						</div>
						 		
						 </s:if>
						 <s:else>
						 
						       <div class="col-lg-12 col-xs-12 col-md-12 col-sm-5">
								<div class="form-group">
									<s:textarea cssClass="form-control" name="diagnosisgyn" id="diagnosisgyn"
										rows="4" />
								</div>
								
							</div>
						 </s:else>
						

						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>Investigation</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12"
							style="padding-bottom: 15px;">
							<h5 class="lighbdsub">
								<b>Investigation</b>
							</h5>
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

						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>PERSONAL DETAILS</h5>
							<span style="float: right; margin-top: -17px;"><a href="#"
								style="color: #fff;" class="hidden">Add New</a></span>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12">
							<h5 class="lighbdsub">
								<b>11 Weeks</b> <span style="float: right;"><a href="#"
									style="color: #555;" class="hidden"><i class="fa fa-trash"></i></a></span>
							</h5>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>Anomaly Scan</label>
									<s:textfield name="anormaly_scan_11week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Cervical Length</label>
									<s:textfield name="cervical_length_11week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Double Marker</label>
									<s:textfield name="double_marker_11week"
										cssClass="form-control" />
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12">
							<h5 class="lighbdsub">
								<b>16 Weeks</b> <span style="float: right;"><a href="#"
									style="color: #555;" class="hidden"><i class="fa fa-trash"></i></a></span>
							</h5>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>All Investigation</label>
									<s:textfield name="all_investigation_16week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Sickling</label>
									<s:textfield name="sikling_16week" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Triple Marker</label>
									<s:textfield name="triple_marker_16week"
										cssClass="form-control" />
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12">
							<h5 class="lighbdsub">
								<b>1st Visit</b> <span style="float: right;"><a href="#"
									style="color: #555;" class="hidden"><i class="fa fa-trash"></i></a></span>
							</h5>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>Abstinence</label>
									<s:textfield name="abstinence_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Barrier Contra.</label>
									<s:textfield name="barrier_contra_1visit"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Bed Rest</label>
									<s:textfield name="bed_rest_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Book</label>
									<s:textfield name="book_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>CSV</label>
									<s:textfield name="csv_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-right: 0px;">
								<div class="form-group">
									<label>GTT</label>
									<s:textfield name="dispi_test_1visit" cssClass="form-control" />
								</div>
							</div>
						</div>

						<div class="col-lg-12 col-xs-12 col-md-12">
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>Drug Reaction</label>
									<s:textfield name="drug_reaction_1visit"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>HCG</label>
									<s:textfield name="hcg_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Heparin</label>
									<s:textfield name="heparin_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Oral Hygeine</label>
									<s:textfield name="oral_hygeine_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Other Test</label>
									<s:textfield name="other_test_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-right: 0px;">
								<div class="form-group">
									<label>Physio. & Diet</label>
									<s:textfield name="physio_diet_1visit" cssClass="form-control" />
								</div>
							</div>

						</div>
						<div class="col-lg-12 col-xs-12 col-md-12">
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>Rubelle Status</label>
									<s:textfield name="rubelle_status_1visit"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Smart Doc</label>
									<s:textfield name="smart_doc_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Stem cell</label>
									<s:textfield name="stream_cell_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Vaginitis</label>
									<s:textfield name="vaginities_1visit" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-right: 0px;"></div>

						</div>
						<div class="col-lg-12 col-xs-12 col-md-12">
							<h5 class="lighbdsub">
								<b>20 Weeks</b> <span style="float: right;"><a href="#"
									style="color: #555;" class="hidden"><i class="fa fa-trash"></i></a></span>
							</h5>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>Anomaly Scan</label>
									<s:textfield name="animally_scan_20week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Fetal Echo</label>
									<s:textfield name="fetal_eco_20week" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-right: 0px;"></div>
						</div>

						<div class="col-lg-12 col-xs-12 col-md-12">
							<h5 class="lighbdsub">
								<b>24 Weeks - 28 Weeks</b> <span style="float: right;"><a
									href="#" style="color: #555;" class="hidden"><i
										class="fa fa-trash"></i></a></span>
							</h5>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>ANTI-D</label>
									<s:textfield name="anti_d_24week" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>GTT</label>
									<s:textfield name="dipsi_24week" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>ITC</label>
									<s:textfield name="itc_24week" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-right: 0px;"></div>
						</div>

						<div class="col-lg-12 col-xs-12 col-md-12">
							<h5 class="lighbdsub">
								<b>30 Weeks</b> <span style="float: right;"><a href="#"
									style="color: #555;" class="hidden"><i class="fa fa-trash"></i></a></span>
							</h5>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>Investigaion SOS</label>
									<s:textfield name="investigation_sos_30week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Steroids</label>
									<s:textfield name="steroids_30week" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Vaginitis Treatment</label>
									<s:textfield name="vaginities_treatment_30week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-right: 0px;"></div>
						</div>

						<div class="col-lg-12 col-xs-12 col-md-12">
							<h5 class="lighbdsub">
								<b>34 Weeks - 36 Weeks</b> <span style="float: right;"><a
									href="#" style="color: #555;" class="hidden"><i
										class="fa fa-trash"></i></a></span>
							</h5>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>Breast Preparation</label>
									<s:textfield name="breast_preparation_34week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Color Doppler</label>
									<s:textfield name="color_doppler_34week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Epidural/Painless Delivery</label>
									<s:textfield name="labour_counselling_34week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>NST</label>
									<s:textfield name="nst_34week" cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Vaginitis Treatment</label>
									<s:textfield name="vaginities_treatment_34week"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-right: 0px;"></div>
						</div>
						
						<s:if test="formtype==1">
							
								<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>NST CHART</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12"
							style="margin-bottom: 10px; padding-top: 10px;">
							<table class="table table-responsive table-borderd">
								<tbody>
									<tr>
										<td>Date</td>
										<td><s:textfield name="nst_date1" id="nst_date1"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_date2" id="nst_date2"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_date3" id="nst_date3"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_date4" id="nst_date4"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_date5" id="nst_date5"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_date6" id="nst_date6"
												cssClass="form-control" /></td>
									</tr>
									<tr>
										<td>Time</td>
										<td><s:textfield name="nst_time1" cssClass="form-control" /></td>
										<td><s:textfield name="nst_time2" cssClass="form-control" /></td>
										<td><s:textfield name="nst_time3" cssClass="form-control" /></td>
										<td><s:textfield name="nst_time4" cssClass="form-control" /></td>
										<td><s:textfield name="nst_time5" cssClass="form-control" /></td>
										<td><s:textfield name="nst_time6" cssClass="form-control" /></td>
									</tr>
									<tr>
										<td>Indication for NST</td>
										<td><s:textfield name="nst_indication1"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_indication2"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_indication3"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_indication4"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_indication5"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_indication6"
												cssClass="form-control" /></td>
									</tr>
									<tr>
										<td>Duration of NST</td>
										<td><s:textfield name="nst_duration1"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_duration2"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_duration3"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_duration4"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_duration5"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_duration6"
												cssClass="form-control" /></td>
									</tr>
									<tr>
										<td>Interpretation</td>
										<td><s:textfield name="nst_interpretation1"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_interpretation2"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_interpretation3"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_interpretation4"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_interpretation5"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_interpretation6"
												cssClass="form-control" /></td>
									</tr>
									<tr>
										<td>Intervention</td>
										<td><s:textfield name="nst_intervention1"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_intervention2"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_intervention3"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_intervention4"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_intervention5"
												cssClass="form-control" /></td>
										<td><s:textfield name="nst_intervention6"
												cssClass="form-control" /></td>
									</tr>
								</tbody>
							</table>
						</div>
						
							<div class="col-lg-12 col-xs-12 col-md-12">
							<h5 class="lighbdsub">
								<b>Immunization</b>
							</h5>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-left: 0px;">
								<div class="form-group">
									<label>Inj. TT 1st Dose</label>
									<s:textfield name="tt_dose1" id="tt_dose1"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Inj. TT 2nd Dose</label>
									<s:textfield name="tt_dose2" id="tt_dose2"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2">
								<div class="form-group">
									<label>Influenza Vaccine</label>
									<s:textfield name="influenza_vaccine" id="influenza_vaccine"
										cssClass="form-control" />
								</div>
							</div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"></div>
							<div class="col-lg-2 col-xs-2 col-md-2"
								style="padding-right: 0px;"></div>
						</div>
						
						
						
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>USG DETAILS</h5>
						</div>

						<div class="col-lg-12 col-xs-12 col-md-12">

							<div class="col-lg-10 col-md-10 col-xs-10"
								style="padding: 0px; padding-top: 10px;">
								<table class="table table-responsive table-borderd">
									<tbody>
										<tr>
											<td style="width: 19%;">Date</td>
											<td><s:textfield name="usgdate1" id="usgdate1"
													cssClass="form-control" /></td>
											<td><s:textfield name="usgdate2" id="usgdate2"
													cssClass="form-control" /></td>
											<td><s:textfield name="usgdate3" id="usgdate3"
													cssClass="form-control" /></td>
											<td><s:textfield name="usgdate4" id="usgdate4"
													cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>Amenorrhea</td>
											<td><s:textfield name="amenorrhea1"
													cssClass="form-control" /></td>
											<td><s:textfield name="amenorrhea2"
													cssClass="form-control" /></td>
											<td><s:textfield name="amenorrhea3"
													cssClass="form-control" /></td>
											<td><s:textfield name="amenorrhea4"
													cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>Presentation</td>
											<td><s:textfield name="presentation1"
													cssClass="form-control" /></td>
											<td><s:textfield name="presentation2"
													cssClass="form-control" /></td>
											<td><s:textfield name="presentation3"
													cssClass="form-control" /></td>
											<td><s:textfield name="presentation4"
													cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>BPD/GS</td>
											<td><s:textfield name="bpdgs1" cssClass="form-control" /></td>
											<td><s:textfield name="bpdgs2" cssClass="form-control" /></td>
											<td><s:textfield name="bpdgs3" cssClass="form-control" /></td>
											<td><s:textfield name="bpdgs4" cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>HC</td>
											<td><s:textfield name="hc1" cssClass="form-control" /></td>
											<td><s:textfield name="hc2" cssClass="form-control" /></td>
											<td><s:textfield name="hc3" cssClass="form-control" /></td>
											<td><s:textfield name="hc4" cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>AC</td>
											<td><s:textfield name="ac1" cssClass="form-control" /></td>
											<td><s:textfield name="ac2" cssClass="form-control" /></td>
											<td><s:textfield name="ac3" cssClass="form-control" /></td>
											<td><s:textfield name="ac4" cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>FL/CRL</td>
											<td><s:textfield name="flcrl1" cssClass="form-control" /></td>
											<td><s:textfield name="flcrl2" cssClass="form-control" /></td>
											<td><s:textfield name="flcrl3" cssClass="form-control" /></td>
											<td><s:textfield name="flcrl4" cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>GA by USG</td>
											<td><s:textfield name="ga_usg1" cssClass="form-control" /></td>
											<td><s:textfield name="ga_usg2" cssClass="form-control" /></td>
											<td><s:textfield name="ga_usg3" cssClass="form-control" /></td>
											<td><s:textfield name="ga_usg4" cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>Liquor</td>
											<td><s:textfield name="liquor1" cssClass="form-control" /></td>
											<td><s:textfield name="liquor2" cssClass="form-control" /></td>
											<td><s:textfield name="liquor3" cssClass="form-control" /></td>
											<td><s:textfield name="liquor4" cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>Placenta</td>
											<td><s:textfield name="placenta1"
													cssClass="form-control" /></td>
											<td><s:textfield name="placenta2"
													cssClass="form-control" /></td>
											<td><s:textfield name="placenta3"
													cssClass="form-control" /></td>
											<td><s:textfield name="placenta4"
													cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>Foetal Weight</td>
											<td><s:textfield name="foetal_weight1"
													cssClass="form-control" /></td>
											<td><s:textfield name="foetal_weight2"
													cssClass="form-control" /></td>
											<td><s:textfield name="foetal_weight3"
													cssClass="form-control" /></td>
											<td><s:textfield name="foetal_weight4"
													cssClass="form-control" /></td>
										</tr>
										<tr>
											<td>Cervical Length</td>
											<td><s:textfield name="cervical_length1"
													cssClass="form-control" /></td>
											<td><s:textfield name="cervical_length2"
													cssClass="form-control" /></td>
											<td><s:textfield name="cervical_length3"
													cssClass="form-control" /></td>
											<td><s:textfield name="cervical_length4"
													cssClass="form-control" /></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-lg-2 col-md-2 col-xs-2"
								style="padding-right: 0px;">
								<div class="form-group">
									<label>Early anomaly / NT Scan</label>
									<s:textarea cssClass="form-control" name="nt_scan" rows="5" />
								</div>
								<div class="form-group">
									<label>Anomaly Scan</label>
									<s:textarea cssClass="form-control" name="anomaly_scan"
										rows="5" />
								</div>
								<div class="form-group">
									<label>Colour Doppler Scan</label>
									<s:textarea cssClass="form-control" name="colour_doppler_scan"
										rows="5" />
								</div>
							</div>
						</div>
						
						</s:if>
						

						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>EXAMINATION / VITALS</h5>
							<span style="float: right; margin-top: -17px;"><a href="#"
								style="color: #fff;" class="hidden">Add Examination</a><a
								href="#" style="color: #fff;">Sketch</a></span>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12"
							style="padding-left: 0px;">
							<div class="col-lg-8 col-md-8 col-sm-8"
								style="padding-left: 0px; border-right: 1px solid #ddd;">
								<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>Genral Condition</label>

											<s:select
												list="#{'0':'Select','Fair':'Fair','Moderate':'Moderate','Poor':'Poor','Obese':'Obese','Thin':'Thin'}"
												theme="simple" name="gen_condition" cssClass="form-control"></s:select>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>Temp</label>
											<s:select list="#{'Febrile':'Febrile','Afebrile':'Afebrile'}"
												theme="simple" name="temp" cssClass="form-control"></s:select>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
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
									<div class="col-lg-2 col-md-2 col-sm-2"></div>
									<div class="col-lg-2 col-md-2 col-sm-2"></div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>Pulse</label>
											<s:textfield name="pulse" cssClass="form-control" />
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>BMI</label>
											<s:textfield name="bmi" cssClass="form-control " />
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>Height</label>
											<s:textfield name="height" cssClass="form-control " />
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>Weight</label>
											<s:textfield name="weight" cssClass="form-control " />
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>Sys-BP</label>
											<s:textfield name="sys_bp" cssClass="form-control " />
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2">
										<div class="form-group">
											<label>Dia-BP</label>
											<s:textfield name="dia_bp" cssClass="form-control " />
										</div>
									</div>
								</div>

							</div>
							<div class="col-lg-2 col-md-2 col-sm-2" style="padding: 0px;">
								<img src="img/le2.jpg" class="img-responsive viewsetimagse" />
							</div>


							<div class="col-lg-8 col-md-8 col-sm-8 hidden"
								style="padding: 0px;">
								<div class="scrolltable1">
									<h5 class="lighbdsub">
										<b>P/S</b> <span style="float: right;"><a href="#"
											data-toggle="modal" data-target="#examiform"
											style="color: #555;"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
											href="#" style="color: #555;"><i class="fa fa-trash"></i></a></span>
									</h5>
									
									<div class="col-lg-12 col-xs-12 col-sm-12">
										<table class="table-responsive table">
											<tbody>
												<tr>
													<td style="width: 10%;">Cervix</td>
													<td style="width: 15%;"><select class="form-control"
														style="width: 100%;">
															<option>Select</option>
													</select></td>
													<td style="width: 72%;"><input type="text"
														class="form-control"></td>
													<td class="text-center"><a href="#"
														style="color: #555;"><i class="fa fa-trash"></i></a></td>
												</tr>
												<tr>
													<td>Vagina</td>
													<td><select class="form-control" style="width: 100%;">
															<option>Select</option>
													</select></td>
													<td><input type="text" class="form-control"></td>
													<td class="text-center"><a href="#"
														style="color: #555;"><i class="fa fa-trash"></i></a></td>
												</tr>
											</tbody>
										</table>


									</div>
								</div>



							</div>
							<div class="col-lg-2 col-md-2 col-sm-2"
								style="padding-right: 0px; padding-top: 10px;">
								<article>
									<input id="search" name="search" class="form-control"
										placeholder="Search Sketch" type="text"
										data-list="#skretchids" autocomplete="off">
									<div class="scrolltable7">
										<ul class="vertical default_list" id="skretchids">
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													type="checkbox"><i></i> cranio</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													type="checkbox"><i></i> Placenta Previa</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													type="checkbox"><i></i> Female Reproductive Organ</label></li>
											<li><label class="checkbox checkbox-custom-alt m-0 mt-5"><input
													type="checkbox"><i></i> Female Reproductive System</label></li>
										</ul>
									</div>
								</article>
								<div></div>

							</div>

						</div>

						
						
                        <s:if test="formtype==1">
								
								<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>P/A</h5>
						</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
									<h5 class="lighbdsub"><b>Abdominal Wall Edema</b></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<article>
										  <div class="">
										      <ul class="vertical" id="newid">
										      				
										      				<s:if test="wall_edema==1">
										      				<li><label class="checkbox checkbox-custom"><input name="wall_edema" value="1" checked="checked"  type="radio"><i></i>  Present</label></li>
									                      		<div class="heightdiva"></div>
									                       <li><label class="checkbox checkbox-custom"><input name="wall_edema" value="0" type="radio"><i></i>  Absent</label></li>
										      				</s:if>
										      				<s:else>
										      						<li><label class="checkbox checkbox-custom"><input name="wall_edema" value="1" type="radio"><i></i>  Present</label></li>
												                      <div class="heightdiva"></div>
												                       <li><label class="checkbox checkbox-custom"><input name="wall_edema" value="0" checked="checked" type="radio"><i></i>  Absent</label></li>
										      				</s:else>
									                       
									                      
									          </ul>
										   </div>
										 </article>
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
								 </div>
						<div class="col-lg-12 col-xs-12 col-md-12">
							<h5 class="lighbdsub">
								<b>Fundal Height</b>
							</h5>
							<div class="form-inline">
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 12 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 14 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 16 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 18 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 20 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 22 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 24 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 26 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 28 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 30 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 32 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 34 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 36 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 38 W
								</div>
								<div class="form-group" style="margin-right: 10px;">
									<label class="checkbox checkbox-custom"
										style="padding-bottom: 5px;"> <input
										name="customRadio" type="radio"><i></i>
									</label><br> 40 W
								</div>
							</div>
						</div>
						 <div class="col-lg-6 col-xs-6 col-md-6">
									<h5 class="lighbdsub"><b>Presentation</b></h5>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop1" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5">
									            
									            <s:if test="cephalic==0">
									               <input type="checkbox" name="cephalic">
									            </s:if>
									            <s:else>
									            		<input type="checkbox" checked="checked" name="cephalic">
									            </s:else>
									            
									            <i></i> Cephalic</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop1" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
												   
												    <s:if test="cephalic=='Mobile'">
												      <input name="cephalicVal" checked="checked" value="Mobile" type="radio"><i></i> Mobile
												    </s:if>
												    <s:else>
												    	<input name="cephalicVal" value="Mobile" type="radio"><i></i> Mobile
												    </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                                     <s:if test="cephalic=='Fixed'">
                                                    	<input name="cephalicVal" checked="checked" value="Fixed" type="radio"><i></i> Fixed
                                                    </s:if>
                                                    <s:else>
                                                    	<input name="cephalicVal" value="Fixed" type="radio"><i></i> Fixed
                                                    </s:else> 
                                             </label>
											</div>
										</div>
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
									             
									             
									             <i></i> Breech</label></li>
									          </ul>
										   </div>
										 </article>
							       		</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop2" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5">
									             <s:if test="baley_size==0">
									             	<input type="checkbox" name="baley_size">
									             </s:if>
									             <s:else>
									            	<input checked="checked" type="checkbox" name="baley_size"> 
									             </s:else>
									            
									            <i></i> Baby size</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop2" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
												     <s:if test="baley_size=='Average'">
												     	<input name="baley_sizeVal" checked="checked" value="Average" type="radio"><i></i> Average
												     </s:if>
												     <s:else>
												    	<input name="baley_sizeVal" value="Average" type="radio"><i></i> Average 
												     </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		<s:if test="baley_size=='Small'">
                                             			<input name="baley_sizeVal" checked="checked" value="Small" type="radio"><i></i> Small
                                             		</s:if>
                                             		<s:else>
                                             			<input name="baley_sizeVal" value="Small" type="radio"><i></i> Small
                                             		</s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		<s:if test="baley_size=='Large'">
                                             			<input name="baley_sizeVal" checked="checked" value="Large" type="radio"><i></i> Large
                                             		</s:if>
                                             		<s:else>
                                             			<input name="baley_sizeVal" value="Large" type="radio"><i></i> Large
                                             		</s:else>		
                                                     
                                             </label>
											</div>
										</div>
									</div>
									<div class="col-lg-3 col-xs-3 col-md-3">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#"  style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5">
									            <s:if test="transverse_fhs==0">
									            	<input type="checkbox" name="transverse_fhs">
									            </s:if>
									            <s:else>
									            	<input type="checkbox" checked="checked" name="transverse_fhs">
									            </s:else>
									            <i></i> Transverse</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop3" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
												    <s:if test="transverse_fhs=='Present'">
												    	<input type="checkbox" checked="checked" name="transverse_fhsVal" value="Present"><i></i> Present  
												    </s:if>
												    <s:else>
												    	<input type="checkbox" name="transverse_fhsVal" value="Present"><i></i> Present
												    </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                              <s:if test="transverse_fhs=='Absent'">
                                              			<input type="checkbox" checked="checked" name="transverse_fhsVal" value="Absent"><i></i> Absent
												    </s:if>
												    <s:else>
												    		<input type="checkbox" name="transverse_fhsVal" value="Absent"><i></i> Absent
												    </s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                              <s:if test="transverse_fhs=='Regular'">	
                                              			<input type="checkbox" name="transverse_fhsVal" value="Regular"><i></i> Regular
												    </s:if>
												    <s:else>
												    		<input type="checkbox" name="transverse_fhsVal" value="Regular"><i></i> Regular
												    </s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		 <s:if test="transverse_fhs=='Irregular'">
                                             		 	<input type="checkbox" checked="checked" name="transverse_fhsVal" value="Irregular"><i></i> Irregular
												    </s:if>
												    <s:else>
												    		<input type="checkbox" name="transverse_fhsVal" value="Irregular"><i></i> Irregular
												    </s:else>
                                                     
                                             </label>
											</div>
										</div>
									</div>
																																	 								 
								 </div>
						<div class="col-lg-2 col-xs-2 col-md-2">
									<h5 class="lighbdsub"><b>FHS</b></h5>										
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
                                            <div class="col-lg-8 col-xs-8 col-md-8" style="margin-left:2px;">
                                               <div class="form-group">
							       			       <label>Beats/min</label>
							       			       <s:textfield name="beats_min" cssClass="form-control " />
							       		       </div>
							       		   </div>
							       		</div>
								 </div>

						<div class="col-lg-2 col-xs-2 col-md-2">
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
						<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
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
                        
                        </s:if>
                        <s:elseif test="formtype==2">
							<div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>L/E</h5>
							</div>
  							<div class="col-lg-12 col-xs-12 col-md-12">
										
										
										<div class="col-lg-3 col-xs-3 col-md-3">
										<div class="form-group">
											
											    <label>Vulva</label>
											       <s:select list="#{'0':'Select','Normal':'Normal'}" cssClass="form-control" name="le_vulva"></s:select>
											       <input type="text" name="le_vulva_txt" class="form-control" placeholder="enter Here" />		
											
										</div>
										</div>
										<div class="col-lg-3 col-xs-3 col-md-3">
										<div class="form-group">
											
											    <label>Vagina</label>
											    <s:select list="#{'0':'Select','Normal':'Normal'}" cssClass="form-control" name="le_vagina"></s:select>
								       			<input type="text" class="form-control" name="le_vagina_txt" placeholder="enter text Here" />		
										</div>
										</div>
										
							</div>
							
							<div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>P/A</h5>
							</div>
  							<div class="col-lg-12 col-xs-12 col-md-12">
						
											<s:textarea rows="3" cssClass="form-control" name="pa_gynic" />
								</div>
                        
                        </s:elseif>


						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>P/S</h5>
						</div>
						<div class="col-lg-12 col-xs-12 col-md-12">
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-left:0px;">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop4" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5">
									            <s:if test="ps_cervix==0">
									            	<input type="checkbox" name="ps_cervix">	
									            </s:if>
									            <s:else>
									            		<input type="checkbox" checked="checked" name="ps_cervix">
									            </s:else>
									            
									            <i></i> Cervix</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop4" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
													<s:if test="ps_cervix=='Normal'">
														<input type="checkbox"  checked="checked" name="ps_cervixVal" value="Normal" ><i></i> Normal
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Normal" ><i></i> Normal
													</s:else>		
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                    <s:if test="ps_cervix=='Erosion'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Erosion" ><i></i> Erosion
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Erosion" ><i></i> Erosion
													</s:else>	
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                                      <s:if test="ps_cervix=='Bleeding'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Bleeding" ><i></i> Bleeding
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Bleeding" ><i></i> Bleeding
													</s:else>	 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		 <s:if test="ps_cervix=='Small Polyp - Present'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Small Polyp - Present" ><i></i> Small Polyp - Present
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Small Polyp - Present" ><i></i> Small Polyp - Present
													</s:else>	 
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		 <s:if test="ps_cervix=='Irregular Margins'">
														<input type="checkbox" checked="checked" name="ps_cervixVal" value="Irregular Margins" ><i></i> Irregular Margins
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_cervixVal" value="Irregular Margins" ><i></i> Irregular Margins
													</s:else>	
                                             </label>
											</div>
										</div>
							       		
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop5" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5">
									            
									            <s:if test="ps_vagine==0">
									            		<input type="checkbox" name="ps_vagine">
									            </s:if>
									            <s:else>
									            		<input type="checkbox" checked="checked" name="ps_vagine">
									            </s:else>
									            <i></i> Vagina</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop5" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
													 <s:if test="ps_vagine=='Normal'">
														<input type="checkbox" checked="checked" name="ps_vagineVal" value="Normal"><i></i> Normal 	
													 </s:if>
													 <s:else>
													 	<input type="checkbox" name="ps_vagineVal" value="Normal"><i></i> Normal
													 </s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vagine=='Vaginitis'">
                                             			<input type="checkbox" name="ps_vagineVal" value="Vaginitis"><i></i> Vaginitis
                                             		</s:if>
                                             		<s:else>
                                             			<input type="checkbox" name="ps_vagineVal" value="Vaginitis"><i></i> Vaginitis
                                             		</s:else>	
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vagine=='Candidiasis'">
                                             				<input type="checkbox" checked="checked" name="ps_vagineVal" value="Candidiasis" ><i></i> Candidiasis
                                             		</s:if>
                                             		<s:else>
                                             				<input type="checkbox" name="ps_vagineVal" value="Candidiasis" ><i></i> Candidiasis
                                             		</s:else>
                                                     
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vagine=='Anterior Vaginal wall cyst'">
                                             				<input type="checkbox" name="ps_vagineVal" value="Anterior Vaginal wall cyst"><i></i> Anterior Vaginal wall cyst
                                             		</s:if>
                                             		<s:else>
                                             				<input type="checkbox" name="ps_vagineVal" value="Anterior Vaginal wall cyst"><i></i> Anterior Vaginal wall cyst
                                             		</s:else>
                                                    
                                             </label>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										<div class="form-group">
							       			<article>
										  	<div class="">
										     <ul class="vertical default_list" id="">
									            <a href="#" data-toggle="collapse" data-target="#drop6" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5">
									            <s:if test="ps_vault==0">
									            	<input type="checkbox" name="ps_vault">
									            </s:if>
									            <s:else>
									            	<input type="checkbox" checked="checked" name="ps_vault">
									            </s:else>
									            <i></i> Vault</label></li></a> 
									          </ul>
										   </div>
										 </article>
							       		</div>
							       		<div id="drop6" class="collapse">
											<div class="form-group">
												<label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
													<s:if test="ps_vault=='Normal'">
														<input type="checkbox" checked="checked" name="ps_vaultVal" value="Normal"><i></i> Normal
													</s:if>
													<s:else>
														<input type="checkbox" name="ps_vaultVal" value="Normal"><i></i> Normal
													</s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom-alt m-0 mt-5" style="padding-bottom: 5px;">
                                             		<s:if test="ps_vault=='Granuletion Tissue'">
                                             			<input type="checkbox" checked="checked" name="ps_vaultVal" value="Granuletion Tissue"><i></i> Granulation Tissue
                                             		</s:if>
                                             		<s:else>
                                             			<input type="checkbox" name="ps_vaultVal" value="Granuletion Tissue"><i></i> Granulation Tissue
                                             		</s:else>
                                                     
                                             </label>
											</div>
										</div>
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
							       		
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2">
										
									</div>
									<div class="col-lg-2 col-xs-2 col-md-2" style="padding-right:0px;">
										
									</div>
								 </div>
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
							<h5>P/V</h5>
						</div>
						 <div class="col-lg-12 col-xs-12 col-md-12" style="margin-top:10px;">
         <div class="col-lg-6 col-md-6 col-xs-6 padingleferightzero">
            
            
             <div class="col-lg-12 col-md-12" style="padding:0px;">
             <div class="col-lg-4 col-xs-4 col-md-4 padinglefetzero">
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
         <div class="col-lg-4 col-xs-4 col-md-4 padinglefetzero">
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
         <div class="col-lg-4 col-xs-4 col-md-4 padinglefetzero">
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
          
            </div>
            <div class="col-lg-12 col-md-12" style="padding:0px;">
             <div class="col-lg-4 col-xs-4 col-md-4 padinglefetzero">
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
         <div class="col-lg-4 col-xs-4 col-md-4 padinglefetzero">
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
         <div class="col-lg-4 col-xs-4 col-md-4 padinglefetzero">
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
          
        </div>
         <div class="col-lg-12 col-md-12" style="padding:0px;">
             <div class="col-lg-4 col-xs-4 col-md-4 padinglefetzero">
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
         <div class="col-lg-4 col-xs-4 col-md-4 padinglefetzero">
             <div class="form-group">
                 <label class="checkbox checkbox-custom-alt m-0 mt-5">
                    <s:if test="pv_ettacced==1">
                     		<input name="pv_ettacced" checked="checked" type="checkbox"><i></i>  Effaced
                    </s:if>
                    <s:else>
                    		<input name="pv_ettacced" type="checkbox"><i></i>  Effaced
                    </s:else>  
                 </label>
             </div>               
         </div>
         <div class="col-lg-4 col-xs-4 col-md-4 padinglefetzero">
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
        
          
            </div>
       <div class="col-lg-12 col-md-12" style="padding:0px;">     
          <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6" style="margin-left: -11px;">
	        <div class="form-group">
                <label for="exampleInputName2">Station</label>
			         <s:select list="#{'0':'Select','-3':'-3','-2':'-2','-1':'-1','0':'0','+1':'+1','+2':'+2','+3':'+3'}" cssClass="form-control" name="pv_station" />
			</div>
	      </div>
	      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
	        <div class="form-group">
                <label for="exampleInputName2">Liquor</label>
			         <s:select list="#{'0':'Select','No Draining':'No Draining','Clear Liquor Draining':'Clear Liquor Draining','Thin Neconium Stained liquor':'Thin Neconium Stained liquor','Thick Neconium ':'Thick Neconium'}" cssClass="form-control" name="pv_liquor" />
			</div>
	      </div>
         <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6">
	        <div class="form-group">
                <label for="exampleInputName2">Pelvis</label>
			         <s:select list="#{'0':'Select','Adequate':'Adequate','Contracted':'Contracted'}"  cssClass="form-control" name="pv_pelvis" />
			</div>
	      </div>
	      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6" style="margin-left: -11px;">
	        <div class="form-group">
	           <label>Position</label>
				  <s:textfield name="pv_position" cssClass="form-control" />
           </div>
	      </div>
        
        </div>     
            </div>
            
            <div class="col-lg-6 col-md-6 col-xs-6 padingleferightzero">
            
             <div class="col-lg-4 col-xs-4 col-md-4" style="padding-left:0px;">
                <div class="form-group">
                  <article>
                    <div class="">
                      <ul class="vertical default_list" id="">
                        <a href="#" data-toggle="collapse" data-target="#pvnew1" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5">
                       <s:if test="pv_os=='0'">
                       		<input type="checkbox" name="pv_os">
                       </s:if>  
                       <s:else>
                       		<input type="checkbox" checked="checked" name="pv_os">
                       </s:else>
                        
                        <i></i> OS</label></li></a> 
                     </ul>
                   </div>
                </article>
              </div>
                <div id="pvnew1" class="collapse">
                   <div class="form-group">
                      <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                      								<s:if test="pv_os=='Closed'">
                      										<input name="pv_osVal" checked="checked" value="Closed" type="radio"><i></i> Closed
                      								</s:if>
                      								<s:else>
                      										<input name="pv_osVal" value="Closed" type="radio"><i></i> Closed
                      								</s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		<s:if test="pv_os=='Admits Tip of Fesyes'">
                                             				<input name="pv_osVal" checked="checked" value="Admits Tip of Fesyes" type="radio"><i></i> Admits Tip of Finger
                      								</s:if>
                      								<s:else>
                      									<input name="pv_osVal" value="Admits Tip of Fesyes" type="radio"><i></i> Admits Tip of Finger
                      								</s:else>	
                                                    
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             		<s:if test="pv_os=='Dilotation'">
                                             				 <input name="pv_osVal" checked="checked" value="Dilotation" type="radio"><i></i> Dilation
                      								</s:if>
                      								<s:else>
                      										 <input name="pv_osVal" value="Dilotation" type="radio"><i></i> Dilation
                      								</s:else>	
                                                    
                                             </label>
                 </div>
               </div>
          </div>
         
         <div class="col-lg-4 col-xs-4 col-md-4" style="padding-left:0px;">
            <div class="form-group">
                <article>
                   <div class="">
                      <ul class="vertical default_list" id="">
                           <a href="#" data-toggle="collapse" data-target="#pvnew2" style="color: #000;"><li><label class="checkbox checkbox-custom-alt m-0 mt-5">
                           <s:if test="pv_membrane=='0'">
                           		<input type="checkbox" name="pv_membrane">
                           </s:if>
                           <s:else>
                           		<input checked="checked" type="checkbox" name="pv_membrane">
                           </s:else>
                           
                           <i></i> Membrane</label></li></a> 
		              </ul>
		           </div>
		        </article>              
		    </div>                 
		       <div id="pvnew2" class="collapse">
                   <div class="form-group">
                      <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                      							<s:if test="pv_membrane=='Present'">
                      									<input name="pv_MVal" checked="checked" value="Present" type="radio"><i></i> Present
                      							</s:if>
                      							<s:else>
                      									<input name="pv_MVal" value="Present" type="radio"><i></i> Present
                      							</s:else>
                                                      
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             	<s:if test="pv_membrane=='Absent'">
                                             			<input name="pv_MVal" checked="checked" value="Absent" type="radio"><i></i> Absent
                                             	</s:if>
                                             	<s:else>
                                             			<input name="pv_MVal" value="Absent" type="radio"><i></i> Absent
                                             	</s:else>	
                                                    
                                             </label>
                                             <label class="checkbox checkbox-custom" style="padding-bottom: 5px;">
                                             	 <s:if test="pv_membrane=='Thin over head'">
                                             	 		<input name="pv_MVal" checked="checked" value="Thin over head" type="radio"><i></i> Thin over head
                                             	 </s:if>
                                             	 <s:else>
                                             	 		<input name="pv_MVal" value="Thin over head" type="radio"><i></i> Thin over head
                                             	 </s:else>	
                                                    
                                             </label>
                 </div>
               </div>                
		 </div>



   </div>		                         
</div>
						
							
							
						<div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>Plan</h5>
							</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
								
							    <s:textarea cssClass="form-control" name="plan" rows="3" />
							 </div>

							<div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>Final Diagnosis</h5>
								
							</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
								
							   <s:hidden name="finaldiagnosis" id="finaldiagnosis"  />
							    
							    <div style="margin-top: 5px;border: 2px solid #6699CC;">
						  <div id="paragraphs" style="padding: 5px;">
						 <!--   <a href="#" class="btn btn-sm btn-success" style="float: right;background-color: #555;" onclick="showopdcontxtoneditor('consNote')">Add</a><br> -->
						  <!-- <a href="#" type="button" class="btn btn-info" onclick="dispDIv()" >+</a>  -->
						    
						   <div class="heiige">
						     <input type="text" class="form-control" placeholder="Search diagnosis here" id="newdiagnosis" onkeyup="searchdiagnosis(this.value)" style="width: 80%;"/>
						   	<a href="#" class="btn btn-sm btn-success" style="float: right;background-color: #555;" onclick="savediagnosisfast()">Save</a><br> 
						  	</div>
						   </div>
						  	<div class="form-inline">
						  <table class="table table-responsive" style="width: 100%;border:none;">
						     <tbody style='height:275px;display:block;overflow:scroll;width:100% !important;' id="searchDiagnosis">
						             <s:iterator value="diagnosisListGynic">
										<tr>
											<td><input type="checkbox" class="classD" value="<s:property value="id"/>" checked="checked" ></td>
											<td><s:property value="name" /></td>								
										</tr>								     
							    </s:iterator>
									
					        </tbody>
					    </table> 
						  	</div>
						  </div>
						  </div>
							    
							    
							    
							    
							    
							    
							 </div>
								
							<%-- <div class="col-lg-12 col-xs-12 col-md-12 textprime">
								<h5>Priscription</h5>
							</div>
								<div class="col-lg-12 col-xs-12 col-md-12">
								
							    <s:textarea cssClass="form-control" name="priscription" rows="3" />
							 </div>
							 --%>

						  <div class="col-lg-12 col-xs-12 col-md-12">
										<div class="col-lg-6 col-md-6">
											
										</div>
										<div class="col-lg-6 col-md-6">
											<input style="width: 100%; margin-right: -27px; margin-top: 141px;"
												class="btn btn-primary savebtn savebigbtn"
												value="Update" onclick="validdata()" />
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
										<div class="col-lg-12 col-xs-12 col-md-12"
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
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" onClick="addReasonForVisit()"
										class="btn btn-primary">Save</button>
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
								<div class="form-group">

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

								</div>
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
<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog"
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
</div>









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
