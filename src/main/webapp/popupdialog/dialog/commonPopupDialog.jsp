<!doctype html>
<%@page import="com.apm.Master.eu.entity.Master"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	LoginInfo loginfo = LoginHelper.getLoginInfo(request);
%>

<html lang="en">
<head>
<meta charset="utf-8">


<script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/commonAppointmentView.js"></script>

<link href="common/assets/css/style.css" rel="stylesheet" />



<!-- JavaScript Includes -->
<script src="common/assets/js/jquery.knob.js"></script>
<script src="common/assets/js/script.js"></script>

<script type="text/javascript"
	src="diarymanagement/js/editCompleteApmt.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/dischargepopup.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>

<script type="text/javascript" src="diarymanagement/js/sendsms.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/addpriscription.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/addotequipment.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/sendApmtAttachnment.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/followupsreminder.js"></script>
<script type="text/javascript" src="diarymanagement/js/otdata.js"></script>
<script type="text/javascript" src="tools/js/sendLetter.js"></script>
<script type="text/javascript" src="diarymanagement/js/takepayment.js"></script>

<!-- jQuery File Upload Dependencies -->
<script src="common/assets/js/jquery.ui.widget.js"></script>
<script src="common/assets/js/jquery.iframe-transport.js"></script>
<script src="common/assets/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script>

<!-- JS -->
<script type="text/javascript"
	src="inventory/js/searchtext/javascripts/vendor/jquery.hideseek.min.js"></script>
<script type="text/javascript"
	src="inventory/js/searchtext/javascripts/vendor/rainbow-custom.min.js"></script>
<script type="text/javascript"
	src="inventory/js/searchtext/javascripts/vendor/jquery.anchor.js"></script>
<script src="inventory/js/searchtext/javascripts/initializers.js"></script>
<!-- JS ends -->

 <link rel="stylesheet" href="plugin/slidervitals/infinityCarousel.css">
      
      
      <script type="text/javascript" src="plugin/slidervitals/infinityCarousel.js"></script>
      	<script src="_assets/newtheme/js/vendor/hichart/highcharts.js"></script>
	<script src="_assets/newtheme/js/vendor/hichart/exporting.js"></script>

<script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>
<style>
 .carousel-control.left {
   	background-image: none !important;
   	line-height: 140px;
}
.carousel-control.right {
   	background-image: none !important;
   	line-height: 140px;
}

.modal-draggable .modal-backdrop {
	position: fixed;
}

.modal.modal-draggable {
	overflow: overflow-y;
}

.modal-draggable .modal-header:hover {
	cursor: move;
}
.divwh {
  height: 50px !important;
  width: 49% !important;
}
.divrecp {
max-height: 52px !important;
height: 52% !important;

}

</style>

<script>



var remingmedurationvar = '10000';
$(document).ready(function() {
	
	$("#dateTimeLetter").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});
	
	
	$("#priscdate").datepicker({

		dateFormat : 'dd/mm/yy',
		yearRange: yearrange,
		minDate : '30/12/1880',
		changeMonth : true,
		changeYear : true

	});




 
 
 




	
	<%String apmuserlist = (String) session.getAttribute("apmuserlist");%>
	apmuserlist = '<%=apmuserlist%>';
//	alert(apmuserlist)
		
		currencySign = '';	
});





	
	        bkLib.onDomLoaded(function() {
	           
	        	// new nicEditor().panelInstance('emailBodyLetter');
	        	 new nicEditor({maxHeight : 2500}).panelInstance('emailBodyLetter');
	        	// $('.nicEdit-panelContain').parent().width('100%');
	        	 //$('.nicEdit-panelContain').parent().next().width('100%');
	        	 
	        	// $('.nicEdit-main').width('100%');
	        	// $('.nicEdit-main').height('800px');
	        	 
	        	// new nicEditor().panelInstance('consNote');
	        	 new nicEditor({maxHeight : 250}).panelInstance('consNote');
	        	 new nicEditor({maxHeight : 450}).panelInstance('consNoteopd');
	        	 
	        	 $('.nicEdit-panelContain').parent().width('100%');
	        	 $('.nicEdit-panelContain').parent().next().width('98%');
	        	 
	        	 
	        	 $('.nicEdit-main').width('98%');
	        	// $('.nicEdit-main').height('2500px');
	        	
	        	 //document.getElementById('templateName').disabled = 'disabled';
	        	 document.getElementById("user").disabled = 'disabled';
	        	
	      });
	        
	        //document.getElementById('saveId').style.display = '';
	        
</script>

<style>
fieldset {
	padding: 0;
	border: 0;
	margin-top: 0px;
}

.micimg {
	width: 5% !important;
	position: absolute;
	right: 21px;
	margin-top: 45px !important;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

#upload {
	background-color: #fff;
	padding: 0px;
	border-radius: 0px;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td, div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.managewidhe {
	margin-top: -33px;
	width: 116%;
	margin-left: -30px;
	background-color: #efefef;
	padding: 8px 5px 11px 6px !important;
	border: 1px solid #ccc;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}

.ui-tooltip, .arrow:after {
	background: black;
	border: 2px solid white;
}

.ui-tooltip {
	padding: 10px 20px;
	color: white;
	border-radius: 20px;
	font: bold 14px "Helvetica Neue", Sans-Serif;
	text-transform: uppercase;
	box-shadow: 0 0 7px black;
}

.arrow {
	width: 70px;
	height: 16px;
	overflow: hidden;
	position: absolute;
	left: 50%;
	margin-left: -35px;
	bottom: -16px;
}

.arrow.top {
	top: -16px;
	bottom: auto;
}

.arrow.left {
	left: 20%;
}

.arrow:after {
	content: "";
	position: absolute;
	left: 20px;
	top: -20px;
	width: 25px;
	height: 25px;
	box-shadow: 6px 5px 9px -9px black;
	-webkit-transform: rotate(45deg);
	-moz-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	-o-transform: rotate(45deg);
	tranform: rotate(45deg);
}

.arrow.top:after {
	bottom: -20px;
	top: auto;
}

.btn-new {
	background-color: #EFEFEF;
	border-color: #EFEFEF;
	color: #595959;
	border-radius: 0px;
}

.btn-new:hover {
	background-color: #EFEFEF;
	border-color: #bbbbbb;
	color: #7A7A7A;
	border-radius: 0px;
}

.topbarback {
	background-color: #EFEFEF;
	margin-left: 0px;
	width: 658px;
	padding: 2px 0px 2px 10px;
	border: 1px solid #ccc;
}

.marlft37 {
	margin-top: -37px;
}

.marleft80 {
	margin-left: -80px;
}

.fa-2x {
	font-size: 18px !important;
	line-height: 24px !important;
}

.seticon {
	margin-top: -22px !important;
	margin-right: 15px;
	cursor: pointer;
}

.marleftr {
	margin-left: -25px;
}

.marlft20 {
	margin-left: -20px;
}

.marlft124 {
	margin-left: -124px;
}

.wellform {
	border: 1px solid #000;
	padding: 10px 0px;
	width: 100%;
	background-color: #F8F8F8;
}

.manaboxwi {
	width: 100%;
	margin-left: 0px;
	margin-top: 0px;
}

.padimp {
	padding-right: 4px !important;
	padding-left: 0px !important;
}

.glyphicon {
	margin-top: -26px;
	margin-right: 10px;
}

.well1 {
	min-height: 0px;
	padding: 0px 13px 0px 22px;
	margin-bottom: 0px;
	background-color: none;
	border: none;
	border-radius: 0px;
	box-shadow: none;
}

.minheaight {
	min-height: 460px;
}

.smsbora {
	margin-top: 258px;
}

.heightsetbmi {
	background-color: rgba(51, 153, 102, 0.26);
	padding-top: 7px;
	padding-left: 0px;
	padding-right: 0px;
	margin-top: -1px;
	margin-bottom: 10px;
	width: 100%;
}

.marbo10form {
	margin-bottom: 10px !important;
}

.checkboxdemoBasicUsage legend {
	color: #339966;
}

.six {
	border: 2px solid #6699cc;
	padding: 8px;
}

md-content fieldset legend, md-dialog-content fieldset legend {
	font-size: 12px;
	margin-bottom: 0;
	border: 0;
	display: inline;
	width: auto;
	padding: 0 4px;
}

.checkboxdemoBasicUsage.checkboxDemo1 div {
	border-radius: 7px;
	margin-left: -2px;
}

.aadedf {
	background-color: rgb(239, 239, 239);
	padding: 3px 2px 0px 2px;
	min-height: 478px !important;
}

legend {
	background: none !important;
}

.topsave {
	float: right;
	margin-top: -4px;
	margin-right: 20px;
	background-color: #555;
}

.disabled {
	z-index: 1000 !important;
	background-color: lightgrey !important;
	opacity: 0.6 !important;
	pointer-events: none !important;
}

fieldset {
	font-size: 14px;
	border: 1px solid #6699CC;
	padding: 0px 0px 0px 10px;
	border-radius: 0.5em;
	margin-bottom: 5px;
}

.thumbnail>img, .thumbnail a>img {
	margin-left: auto;
	margin-right: auto;
	width: 45%;
	margin-top: -4px;
}

.thumbnail {
	height: 70px;
	width: 100%;
}

.thumbnail:hover {
	border: 1px solid #339966;
	box-shadow: 1px 1px 1px;
}

.fontpup {
	font-size: 12px;
	line-height: 15px;
}

.padrigset {
	padding: 0px 0px 0px 6px;
}

.popupbedheaight {
	min-height: 130px;
}

.paddingrih {
	padding-left: 0px !important;
	padding-right: 10px !important;
}


.modalfollowup {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1000000000 !important; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-contentfollowup {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 60%;
    z-index: 5;
}

/* The Close Button */
.closefollowup {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.closefollowup:hover,
.closefollowup:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

</style>


</head>
<body>


	<!-- Book Appointment Popup Modal -->
	<div class="modal fade modal-draggable" id="appointment" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: 9999">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>

					<div class="car-info">
						<ul>
							<li><h4 class="modal-title" id="bookApmtLbl">Book
									Appointment</h4></li>
							<li id="" class="">Appointment with: <span
								id="apmtwithz"></span></li>
							<li id="" class="hidden">Speciality: <span id="diciplinez">Nephrologist</span></li>
							<li id="" class="hidden">Department: <span id="locationz">Ramdaspeth</span></li>
							<li id="" class="hidden">Date: <span id="datez">2016-03-16</span></li>
						</ul>
					</div>
				</div>
				<div class="modal-body padd0">
					  
					   <jsp:include page="/diarymanagement/pages/bookAppointment.jsp" /> 

				</div>
				<div class="modal-footer">
				
				<%if(loginfo.getUserType()!=5){ %>
					<s:if test="withpayment==1">
						<button type="button" id="btnBookWithPayment"
							class="btn btn-primary hidden" onclick="bookSlot(1)">Book
							With Payment</button>
					</s:if>
					<s:else>
						<button type="button" id="btnBookWithPayment"
							class="btn btn-primary hidden" onclick="bookSlot(1)">Book
							With Payment</button>
					</s:else>
					<s:if test="withoutpayment==1">
						<button type="button" id="btnBookWithOutPayment"
							class="btn btn-primary" onclick="bookSlot(0)">Book
							Without Payment</button>
					</s:if>
					<s:else>
						<button type="button" id="btnBookWithOutPayment"
							class="btn btn-primary" onclick="bookSlot(0)">Book
							Without Payment</button>
					</s:else>
				</div>
				<%}else{ %>
				
				<div>
					<input type="hidden" id="btnBookWithPayment" name="btnBookWithPayment"/>
						<button type="button" id="btnBookWithOutPayment"
							class="btn btn-primary" onclick="bookSlot(0)" style="margin-right: 440px;margin-bottom: 10px;height: 35px !important;width: 102px;font-size: 18px;">Book Now
							</button>
					
					
				</div>
				<%} %>

				<button type="button" class="btn btn-primary hidden"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
	

  <!-- The modal For fo;;ow up -->
<div id="followupmodal" class="modalfollowup">

  <!-- Modal content -->
  <div class="modal-contentfollowup">
    <span class="closefollowup" id="followclose" onclick="followupclose()">&times;</span>
    <br>
    <p><b>Follow Up Date</b>  &nbsp; &nbsp; &nbsp; &nbsp;<input style="width:20%" type ="text" id="lokeshfollowdatenew" class="form-control" name="followupdatexyz"> &nbsp; &nbsp; &nbsp;<button class="btn btn-primary" onclick="setfollowupdata1()" required="required"">Set Follow up</button></p>
  </div>
  </div>
  <div id="toipd" class="modalfollowup">
   <div class="modal-contentfollowup" style="border: 2px solid blue; width: 22%;">
    <span class="closefollowup" id="shifttoipd" onclick="closeipd()">&times;</span>
    <br>
    
    <p><b>Select Ward</b>  &nbsp; &nbsp; &nbsp; &nbsp;<s:select name="" list="wardlist" listKey="id" listValue="wardname" cssClass=" chosen-select" headerKey="0" id="wardname" headerValue="All Wards" theme="simple" onchange = "getbeds(this.value)" style="width:20% !important"></s:select></p>
   <b> Select Bed</b>
   <div id="bedlist"> 
   </div>
     
 
</div>
</div>


	<!-- Take Payment Modal -->
	<div class="modal fade modal-draggable" id="takepaymentmodel"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true" style="background-color: rgba(0, 0, 0, 0.25);z-index: 10001">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>

					<div class="car-info">
						<ul>
							<li><h4 class="modal-title" id="tkepmntLbl">Take
									Payment</h4></li>
						</ul>
					</div>
				</div>
				<div class="modal-body padd0">
					<%@ include file="/diarymanagement/pages/takepayment.jsp"%>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveCashDesk()">Record Payment</button>
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Appointment Availability Modal -->

	<div class="modal fade" id="checkavlbltydivpopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Appointment
						Availability</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="/diarymanagement/pages/checkAvailability.jsp"></jsp:include>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary"
						onclick="setClientDidNotComeConfirm();">Ok</button>
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Confirmed Delete Modal -->
	<div class="modal fade" id="dialog-confirm" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Empty the recycle
						bin?</h4>
				</div>
				<div class="modal-body">
					<p>
						<span class=""></span>These items will be permanently deleted and
						cannot be recovered. Are you sure?
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>




	<!-- Modal All Client Search Div -->
	<div class="modal fade popoverpop" id="clientSearchDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:9999">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"><%=loginfo.getPatientname_field() %> Search</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="/diarymanagement/pages/allClient.jsp"></jsp:include>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Add New Patient -->
	<div class="modal fade" id="addPatientDiv" tabindex="-1" role="dialog" style="z-index: 100000"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-keyboard="false" data-backdrop="static" >
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New <%=loginfo.getPatientname_field() %></h4>
				</div>
				<div class="modal-body addnewpat">
					<jsp:include page="/diarymanagement/pages/addPatientPage.jsp"></jsp:include>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary pull-right"
						id="savePatientNow" onclick="return newcall()"
						style="margin-top: 15px;">Save</button>
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Add New Doctor Surgery Details Modal -->
	<div class="modal fade" id="doctorSurgeryPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Doctor
						Surgery</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="/thirdParties/pages/addNewDoctSurgery.jsp"></jsp:include>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveNewPopupSurgeryDetails()">Save</button>

					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Add GP Details Modal -->
	<div class="modal fade" id="gpDetailsPopup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New GP Details</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="/thirdParties/pages/addNewGp.jsp"></jsp:include>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveNewGpData()">Save</button>

					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Add Third Party Details Modal -->
	<div class="modal fade" id="thirdPartyDetailsPopup1" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add New Third Party</h4>
				</div>
				<div class="modal-body" style="height: 600px; overflow: scroll;">
					<jsp:include page="/thirdParties/pages/addNewTp.jsp"></jsp:include>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="saveTpDetails()">Save</button>

					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Add Treatment Episode -->
	<div class="modal fade" id="addTreatmentEpisodeDiv" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Create Treatment
						Episode</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="/treatmentEpisode/pages/addTreatmentEpisode.jsp"></jsp:include>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-primary disblebtnone"
						onclick="saveTreatment();">Save</button>
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Block Apmt Modal -->
	<div class="modal fade" id="blockdiv" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<div class="car-info">
						<ul>
							<li><h4 class="modal-title" id="myModalLabel">

									<%
										String openedb = (String) session.getAttribute("openedb");
									%>
									<%
										if (openedb.equals("opd")) {
									%>
									Block Slot
									<%
										} else {
									%>
									Scheduler
									<%
										}
									%>
								</h4></li>
							<li class="hidden"> User: <span id="apmtwithbz">Fr.Machine 01</span></li>
							<li class="hidden">Location: <span id="locationbz">Ramdaspeth</span></li>
							<li class="hidden">Date: <span id="datebz">2016-03-16</span></li>
						</ul>
					</div>

				</div>
				<div class="modal-body padd0">
					<s:form action="saveNotAvailableSlot" id="notavailable_form"
						theme="simple" validate="True">
						<div class="">
							<div class="col-lg-12 booknasd hidden" id="blockradio">
								<div class="car-info hidden">
									<div class="">
										<ul>
											<%
												if (openedb.equals("opd")) {
											%>
											<li title="OPD"><input type="radio" id="blockradio1"
												name="blockradio1" onclick="showAppointmentDialogBox()" />&nbsp;<label
												for="radio1">OPD</label></li>
											<li title="Block Slot"><input type="radio"
												id="blockradio2" name="blockradio1" checked="checked"
												onclick="showBlockingDialogBox()" />&nbsp;<label
												for="radio2">Block Slot</label></li>
											<%
												} else {
											%>
											<li style="display: none;" title="OPD"><input
												type="radio" id="blockradio1" name="blockradio1"
												onclick="showAppointmentDialogBox()" />&nbsp;<label
												for="radio1">OPD</label></li>
											<li title="Block Slot"><input type="radio"
												id="blockradio2" name="blockradio1" checked="checked"
												onclick="showBlockingDialogBox()" />&nbsp;<label
												for="radio2">Scheduler Slot</label></li>
											<%
												}
											%>
										</ul>
									</div>
								</div>


							</div>
						</div>
						<div class="bookapoinbac">
							<div class="row" id="repeatblockdivid">



								<div class="col-lg-12 hidden-sm hidden-xs" id="apmtheading">

									<label><a data-toggle="collapse" href="#blockslotcoll"
										aria-expanded="false" aria-controls="collapseExample">Repeat
											Booking for </a><select name="weekNumber" id="blockweekNumber">
											<%
												for (int i = 0; i <= 24; i++) {
											%>
											<option value="<%=i%>"><%=i%></option>
											<%
												}
											%>
									</select> week</label>
									<div class="collapse" id="blockslotcoll">
										<div class="well1">
											<input type="checkbox" name="wholeweek" id="blockwholeweek"
												onclick="checkBlockAllWeek();" /> <label>Select All</label>
											<div id="weekNameListdiv" tabindex="102"
												class=" ">

												<input class="weekne" type="checkbox" id="blockWeekName-1">
												<label>Monday</label> <input type="checkbox"
													id="blockWeekName-2"> <label>Tuesday</label> <input
													type="checkbox" id="blockWeekName-3"> <label>Wednesday</label>
												<input type="checkbox" id="blockWeekName-4"> <label>Thursday</label>
												<input class="weekne" type="checkbox" id="blockWeekName-5">
												<label>Friday</label> <input type="checkbox"
													id="blockWeekName-6"> <label>Saturday</label> <input
													type="checkbox" id="blockWeekName-7"> <label>Sunday</label>
											</div>
										</div>
									</div>
								</div>

							</div>

						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-12">
								<input type="hidden" name="blockslotId" id="blockslotId" /> <input
									type="hidden" name="blockdiaryUserId" id="blockdiaryUserId" />
								<input type="hidden" name="status" id="status" value="1" />
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-12" id="apmtheading"></div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden">
							<div class="col-lg-4 col-md-4 col-sm-4 popold">Diary User:</div>

							<div class="col-lg-8 col-md-8 col-sm-8">
								<s:textfield id="blockuser" name="blockuser" readonly="true"
									cssClass="form-control showToolTip" title="Diary User"
									placeholder="Diary User" />
							</div>
						</div>
						<hr class="hidden-sm hidden-xs hidden" />
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden">
							<div class="col-lg-4 col-md-4 col-sm-4 popold">Location:</div>

							<div class="col-lg-8 col-md-8 col-sm-8">
								<s:if test="%{#locationList != 'null'}">
									<s:select id="blocklocation" name="blocklocation"
										list="locationList" listKey="locationid" listValue="location"
										disabled="true" theme="simple"
										cssClass="form-control showToolTip" title="Select Location" />
								</s:if>
							</div>
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden">
							<div class="col-lg-3 col-md-3 col-sm-3 text-right col-md-5">Ward:</div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<select name="blockroom" id="blockroom"
									class="form-control showToolTip" title="Select Room">
									<option value="Room1">Ward 1</option>
									<option value="Room2">Ward 2</option>
								</select>
							</div>
						</div>
						<hr class="hidden hidden-sm hidden-xs" />
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden">
							<div class="col-lg-3 col-md-3 col-sm-3 text-right popold">Date:</div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<s:textfield name="blockdate" id="blockdate" readonly="true"
									cssClass="form-control showToolTip" title="Enter Date"
									placeholder="Enter Date"></s:textfield>
							</div>
						</div>
						<br />
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

							<div class="col-lg-3 col-md-3 col-sm-3 text-right  col-md-5">Start
								Time:</div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<s:if test="%{#startTimeList != 'null'}">
									<s:select id="blocksTime" name="blocksTime"
										list="startTimeList" onchange="resetBlockDivField();"
										theme="simple" cssClass="form-control showToolTip"
										title="Enter Start Time" placeholder="Enter Start Time" />
								</s:if>
							</div>
						</div>


						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-3 col-md-3 col-sm-3"></div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<label id="blocksTimeError" class="text-danger"></label>
							</div>
						</div>

						<br />
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 marbot15">
							<div class="col-lg-3 col-md-3 col-sm-3 text-right cond">End
								Time:</div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<s:if test="%{#endTimeList != 'null'}">
									<s:select id="blockendTime" name="blockendTime"
										onchange="setblockduration(this.value)" list="endTimeList"
										theme="simple" cssClass="form-control showToolTip"
										title="Select End Time" />
								</s:if>
							</div>
						</div>
						<br />

						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-3 col-md-3 col-sm-3 text-right">
								<label class="red">*</label>Duration:
							</div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<s:if test="%{#apmtBlockDurationList != 'null'}">
									<s:select id="blockapmtDuration" name="blockapmtDuration"
										onchange="setBlockDivEndTime(this.value)" headerKey="0"
										headerValue="00:00" list="apmtBlockDurationList"
										theme="simple" cssClass="form-control showToolTip"
										title="Select Duration" />
								</s:if>
							</div>
						</div>


						<div
							class="col-lg-12 col-md-12 col-sm-12 col-xs-12 margintopirfonefiven hidden">

							<div class="col-lg-3 col-md-3 col-sm-3 text-right  col-md-5">Select
								Task:</div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<s:select list="schedulerlist" headerKey="0"
									headerValue="Select Task" id="parentid" name="parentid"
									listKey="id" listValue="taskname"
									onchange="selectSchedulerTask(this.value)"
									cssClass="form-control showToolTip chosen-select"></s:select>
							</div>
						</div>

						<div
							class="col-lg-12 col-md-12 col-sm-12 col-xs-12 margintopirfonefiven hidden">

							<div class="col-lg-3 col-md-3 col-sm-3 text-right  col-md-5">Select
								Sub Task:</div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<div style="width: 30%" id="tdsubtask"></div>
							</div>
						</div>


						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-3 col-md-3 col-sm-3"></div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<label id="blockapmtDurationError" class="text-danger"></label>
							</div>
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 marbot15">

							<%
								if (openedb.equals("opd")) {
							%>
							<div class="col-lg-3 col-md-3 col-sm-3 text-right">
								<label class="red">*</label>Reason for non availability:
							</div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<s:select name="reasonforblock" id="reasonforblock"
									list="{'Break','Lunch','OT','Practice Meeting','Teaching Session'}"
									theme="simple" cssClass="form-control showToolTip"
									title="Select Reason"></s:select>
							</div>
							<%
								} else {
							%>
							<div class="hidden col-lg-3 col-md-3 col-sm-3 text-right">
								<label class="red">*</label>Reason for non availability:
							</div>

							<div class="hidden col-lg-9 col-md-9 col-sm-9 padrinil">
								<s:select name="reasonforblock" id="reasonforblock"
									list="{'Break','Lunch','OT','Practice Meeting','Teaching Session'}"
									theme="simple" cssClass="form-control showToolTip"
									title="Select Reason"></s:select>
							</div>
							<%
								}
							%>


						</div>




						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-3 col-md-3 col-sm-3 text-right popold">Notes:</div>

							<div class="col-lg-9 col-md-9 col-sm-9 padrinil">
								<s:textarea name="blocknotes" id="blocknotes"
									cssClass="form-control showToolTip" title="Enter Note"
									placeholder="Enter Note"></s:textarea>
							</div>
						</div>

						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5"></div>

							<div class="col-lg-8 col-md-8 col-sm-8">
							</div>
						</div>
					</s:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="bookNotAviSlot()">Block</button>
					<button type="button" class="btn btn-primary"
						onclick="deleteNotAviSlot()">Delete</button>
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Client Popup After Book Appointment  -->
	<div class="modal fade modal-draggable" id="modifyPopup" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-md divwh">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" style="margin-top: -7px !important;"
						onclick="movetosetCommonAction()">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Day-To-Day Admin &nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#"
							style="color: yellow;" onclick="showLetterHead()"
							title="Print Letterhead"><i class="fa fa-print"
								aria-hidden="true"></i></a></span> &nbsp;&nbsp;|&nbsp;&nbsp;<span
							id="editSave1"><a href="#" style="color: yellow;"
							onclick="editBMI(1)" title="Edit"><i class="fa fa-pencil"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="sendLetter()"
							title="Send Email"><i class="fa fa-envelope-o"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="sendsmspopupopen()"
							title="Send SMS"><i class="fa fa-commenting-o"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="printlabel()"
							title="print label"><i class="fa fa-tag"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="printbarcodea()"
							title="print barcode"><i class="fa fa-barcode"
								aria-hidden="true"></i></a></span>
								
								&nbsp;&nbsp;|&nbsp;&nbsp;<span>
								<% if(loginfo.getJobTitle().equals("Reception")){%>
								<a
							href="#" style="color: yellow;" onclick="openpatientmedia()"
							title="Body Chart"><i class="fa fa-female" aria-hidden="true"></i></a>
								<%}else{ %>
								<a
							href="#" style="color: yellow;" onclick="openeditedpatientmedia()"
							title="Body Chart"><i class="fa fa-female" aria-hidden="true"></i></a>
								<% }%>
								</span>
					</h4>
				</div>
				<div class="modal-body" style="padding: 0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 heightsetbmi">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Height <small>cm</small></label>
										<p id="height1"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Weight <small>Kg's</small></label>
										<p id="weight1"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">BMI</label>
										<p id="bmi1"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Pulse</label>
										<p id="pulse1"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sys-BP</label>
										<p id="sysbp1"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Dia-BP</label>
										<p id="diabp1"></p>
									</div>
								</div>
							</div>

							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sugar Fasting</label>
										<p id="sugarfasting1"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Post Meal</label>
										<p id="postmeal1"></p>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Head Circum</label>
										<p id="bsa1"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Temp</label>
										<p id="temprature1"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">SPO2</label>
										<p id="spo1"></p>
									</div>
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
								<div class="form-group marbo10form">
										<label for="exampleInputEmail1">LMP Date</label>
										<p id="lmpd1"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
							</div>
						</div>
					</div>
					<div id="modifyClient"
						style="font-size: 16px; color: rgb(61, 61, 61);">Manoj
						Mishra</div>
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 mdscr md9">
						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend><%=loginfo.getPatientname_field() %></legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset"
									id="clientarrived123">
									<div class="thumbnail" id="clienthasarrived"
										onclick="clienthasarrived(1)" style="cursor: pointer;">
										<img src="popicons/client_arrived.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf"><%=loginfo.getPatientname_field() %> has Arrived</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset"
									id="clientisbeingseen">
									<div class="thumbnail" id="clientseen111"
										onclick="setClientIsBeingSeen(2)" style="cursor: pointer;">
										<img src="popicons/client_seen.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" id="clientisbeingseentxt" class="dtdf"><%=loginfo.getPatientname_field() %> is Being Seen</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="completeapmt"
										onclick="completeApmt()" style="cursor: pointer;">
										<img src="popicons/cbs.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Complete Appointment</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset"
									id="clientdidnotcome">

									<div class="thumbnail" id="dna" onclick="setClientDidNotCome()"
										style="cursor: pointer;">
										<img src="popicons/cdna.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf" id="dnatextid"><%=loginfo.getPatientname_field() %> Did
												Not Attend</p>
										</div>
									</div>

								</div>
								<input type="hidden" id="opdmodifylogin" value="<%=loginfo.isOpd_modify() %>">
								<div id="opdmoddivid" class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_modify()) {
									%>
									 <div class="thumbnail" id="modify"
										onclick="modifythisAppointment()" style="cursor: pointer;">
										<img src="popicons/modify.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Modify Appointment</p>
										</div>
									</div> 
									<%
										} else 
										{
									%>
									<%
									
										}
									%>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_cancel()) {
									%>
									<div class="thumbnail" id="deleteapmtiddiv"
										style="cursor: pointer;" onclick="openCancelApmtPopup()">
										<img src="popicons/delete.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Cancel</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="deleteapmtiddiv">
										<img src="popicons/delete.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Cancel</p>
										</div>
									</div>
									<%
										}
									%>
									
								</div>
							</div>
						</fieldset>
						</md-content>

						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Smart Care</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div
									class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset hidden">
									<div class="thumbnail " id="reset"
										onclick="ResetToNotArrived(0)" style="cursor: pointer;">
										<img src="popicons/rc.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Reverse Completion</p>
										</div>
									</div>
									<input type="hidden" id="reminder" name="reminder" />

								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_prescription()) {
									%>
									<div class="thumbnail" id="" onclick="showopdpriscription()"
										style="cursor: pointer;">
										<img src="popicons/medicine.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request prescription</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/medicine.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request prescription</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_prescription()) {
									%>
									<div class="thumbnail" id="" onclick="showopdInvestigation()"
										style="cursor: pointer;">
										<img src="popicons/asmnt.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request Investigation</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/asmnt.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request Investigation</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_ot()) {
									%>
									<div class="thumbnail" id="" onclick="showotpriscription()"
										style="cursor: pointer;">
										<img src="popicons/addot.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add OT Equipment</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/addot.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add OT Equipment</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_ot()) {
									%>
									<div class="thumbnail" id="" onclick="listotequipmwnt()"
										style="cursor: pointer;">
										<img src="popicons/listot.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">List OT Equipment</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/listot.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">List OT Equipment</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="" onclick="openuploadPopup()"
										style="cursor: pointer;">
										<img src="popicons/uploaddocs.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="fontpup">Upload Docs</p>
										</div>
									</div>
								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openOtNotes()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">OT Notes</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openVitalClient()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vital</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openViewRecordVital()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">View Records</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openimmunizationchart()">
										<img src="cicon/pregnancysm.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vaccines Chart</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openclinicalnotes()">
										<img src="cicon/autocare.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Clinical Notes</p>
										</div>
									</div>
								</div>
								
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail followuppop" style="cursor: pointer;"
										onclick="openfollowpop()">
										<img src="cicon/followup.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Follow Up</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="opentoipd()">
										<img src="cicon/Add_client.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">To IPD</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openimmunizationchart1()">
										<img src="cicon/Anc_icon.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Antenatal Schedule</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="showinvst()">
										<img src="cicon/invst.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Show Investigation</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openconcentform()">
										<img src="popicons/eye.png" alt="..." class="">
										<div class="caption">
											<p align="center" class="dtdf">Declaration Form</p>
										</div>
									</div>
								</div>
								

							</div>
						</fieldset>
						</md-content>
						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Account</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<s:form action="Statement" id="view_acc_frm" method="post"
										onsubmit="return redirectToViewAcc()" target="formtarget">

										<s:hidden name="clientId" id="viewAccClientid" />
										<s:hidden name="thirdParty" id="viewAccthirdparty" />
										<s:hidden name="transactionType" id="viewAcctransactionType" />
										<s:hidden name="location" id="viewAccLocationid" />
										<s:hidden name="client" id="viewAccclientname" />
										<s:hidden name="payby" id="viewAccPayby"></s:hidden>
										<s:hidden name="fromDate" id="viewAccfromDateid" />
										<s:hidden name="toDate" id="viewAcctoDateid" />
										<%
											if (loginfo.isOpd_viewaccount()) {
										%>
										<div class="thumbnail" id="sendLetter"
											onclick="redirectToViewAcc()" style="cursor: pointer;">
											<img src="popicons/view_account.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">View Account</p>
											</div>
										</div>
										<%
											} else {
										%>
										<div class="thumbnail disabled" id="">
											<img src="popicons/view_account.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">View Account</p>
											</div>
										</div>
										<%
											}
										%>


									</s:form>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="sendLetter"
										onclick="showApptTreatment()" style="cursor: pointer;">
										<img src="popicons/view_treatment.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">View Treatment</p>
										</div>
									</div>

								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_viewaccount()) {
									%>
									<div class="thumbnail" id="sendLetter"
										onclick="redirectToApmtFinder()" style="cursor: pointer;">
										<img src="popicons/finder.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Appointment Finder</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="sendLetter">
										<img src="popicons/finder.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Appointment Finder</p>
										</div>
									</div>
									<%
										}
									%>
								</div>


								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_advandref()) {
									%>
									<div class="thumbnail" id="" onclick="showcrddebit()"
										style="cursor: pointer;">
										<img src="cicon/raise_credit.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Advance & Refund</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="cicon/raise_credit.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Advance & Refund</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_modifydiagnosis()) {
									%>
									<div class="thumbnail" id="" onclick="modfyDiagnosis()"
										style="cursor: pointer;">
										<img src="popicons/modify.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add Diagnosis</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/modify.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add Diagnosis</p>
										</div>
									</div>
									<%
										}
									%>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="mvmdappointment" onclick="moveappointmentbyappointment(0)"
										style="cursor: pointer;">
										<img src="popicons/modify.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Move / Modify Appointment</p>
										</div>
									</div>
								

								</div>
								<div
									class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset hidden">

									<div class="thumbnail" data-toggle="modal"
										data-target="#editConsultationNote" style="cursor: pointer;">
										<img src="popicons/modify.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">emr popup</p>
										</div>
									</div>

								</div>

							</div>
						</fieldset>
						</md-content>


					</div>







				</div>



				<div class="modal-footer">

					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal" onclick="movetosetCommonAction()">Close</button>
				</div>
			</div>
		</div>
	</div>




	<!-- Complete Appointment Popup  -->

	<div class="modal fade" id="completeAppointmentDivId" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle">Complete
						Appointment</h4>
				</div>
				<div class="modal-body">

					<jsp:include page="/diarymanagement/pages/completeApmt.jsp"></jsp:include>
				</div>

				<s:form action="estimateCharges" theme="simple" id="estimatefrm"
					target="formtarget">
					<s:hidden name="clientId" id="estimateclientid" />
					<s:hidden name="actionType" value="0" />
				</s:form>

				<div class="modal-footer">
					<!--   29-04-2020 -->
					<!-- <button type="button" class="btn btn-primary"
						onclick="createChargeAndUpdateAccount('cash')">Cash Desk</button>  -->
						
					<button type="button" class="btn btn-primary"
						onclick="createopdcashdesknew()">Cash Desk</button>
					
					<button type="button" class="btn btn-primary"
						onclick="createopdestimate()">View Estimate</button>

					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="createChargeAndUpdateAccount('Charge')">Create
						Charge</button>
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Edit Complete Appointment -->

	<div class="modal fade" id="editcompleteAppointmentDivId" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="completeAmtTitle1">Complete
						Appointment</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="/diarymanagement/pages/editCompleteApmt.jsp"></jsp:include>
				</div>


				<div class="modal-footer">



					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="updateChargeAccount('Charge')">Create Charge &
						Update Account</button>

					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Complete Appointment Action Popup -->
	<div class="modal fade modal-draggable" id="completeActionPopup"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true" data-keyboard="false" data-backdrop="static" style="margin-top: -7px !important;">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="movetosetCommonAction()" style="margin-top: -7px !important;">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Day-To-Day Admin &nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#"
							onclick="showLetterHead()" style="color: yellow;"
							title="Print Letterhead"><i class="fa fa-print"
								aria-hidden="true"></i></a></span> &nbsp;&nbsp;|&nbsp;&nbsp;<span
							id="editSave2"><a href="#" style="color: yellow;"
							onclick="editBMI(2)" title="Edit"><i class="fa fa-pencil"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="sendLetter()"
							title="Send Email"><i class="fa fa-envelope-o"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="sendsmspopupopen()"
							title="Send SMS"><i class="fa fa-commenting-o"
								aria-hidden="true"></i></a></span>
								
								&nbsp;&nbsp;|&nbsp;&nbsp;<span>
								<% if(loginfo.getJobTitle().equals("Reception")){%>
								<a
							href="#" style="color: yellow;" onclick="openpatientmedia()"
							title="Body Chart"><i class="fa fa-female" aria-hidden="true"></i></a>
								<%}else{ %>
								<a
							href="#" style="color: yellow;" onclick="openeditedpatientmedia()"
							title="Body Chart"><i class="fa fa-female" aria-hidden="true"></i></a>
								<% }%>
								</span>
					</h4>
				</div>
				<div class="modal-body" style="padding: 0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 heightsetbmi">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Height <small>cm</small></label>
										<p id="height2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Weight <small>Kg's</small></label>
										<p id="weight2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">BMI</label>
										<p id="bmi2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Pulse</label>
										<p id="pulse2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sys-BP</label>
										<p id="sysbp2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Dia-BP</label>
										<p id="diabp2"></p>
									</div>
								</div>
							</div>

							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sugar Fasting</label>
										<p id="sugarfasting2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Post Meal</label>
										<p id="postmeal2"></p>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Head Circum</label>
										<p id="bsa2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Temp</label>
										<p id="temprature2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">SPO2</label>
										<p id="spo2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
								<div class="form-group marbo10form">
										<label for="exampleInputEmail1">LMP Date</label>
										<p id="lmpd2"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
							</div>
						</div>
					</div>
					<div id="modifyClient1"
						style="font-size: 16px; color: rgb(61, 61, 61);">Manoj
						Mishra</div>

					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 mdscr md9">

						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Patient</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
									<%
										if (loginfo.isOpd_modify()) {
									%>
									<%
										} else {
									%>
									<%
										}
									%>




								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="completeapmt"
										onclick="checkAppointmentInvoiced()" style="cursor: pointer;">
										<img src="popicons/reset.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Reset To Not Complete</p>
										</div>
									</div>
								</div>

							</div>
						</fieldset>
						</md-content>

						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Smart Care</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_prescription()) {
									%>
									<div class="thumbnail" id="" onclick="showopdpriscription()"
										style="cursor: pointer;">
										<img src="popicons/medicine.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request prescription</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/medicine.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request prescription</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_prescription()) {
									%>
									<div class="thumbnail" id="" onclick="showopdInvestigation()"
										style="cursor: pointer;">
										<img src="popicons/asmnt.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request Investigation</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/asmnt.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request Investigation</p>
										</div>
									</div>
									<%
										}
									%>

								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_ot()) {
									%>
									<div class="thumbnail" id="" onclick="showotpriscription()"
										style="cursor: pointer;">
										<img src="popicons/addot.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add OT Equipment</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/addot.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add OT Equipment</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_ot()) {
									%>
									<div class="thumbnail" id="" onclick="listotequipmwnt()"
										style="cursor: pointer;">
										<img src="popicons/listot.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">List OT Equipment</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/listot.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">List OT Equipment</p>
										</div>
									</div>
									<%
										}
									%>

								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="" onclick="openuploadPopup()"
										style="cursor: pointer;">
										<img src="popicons/uploaddocs.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="fontpup">Upload Docs</p>
										</div>
									</div>
								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openOtNotes()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">OT Notes</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openVitalClient()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vital</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openViewRecordVital()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">View Records</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openimmunizationchart()">
										<img src="cicon/pregnancysm.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vaccines Chart</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openclinicalnotes()">
										<img src="cicon/autocare.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Clinical Notes</p>
										</div>
									</div>
								</div>
								
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail followuppop" style="cursor: pointer;"
										onclick="openfollowpop()">
										<img src="cicon/followup.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Follow Up</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail followuppop" style="cursor: pointer;"
										onclick="opentoipd()">
										<img src="cicon/Add_client.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">To IPD</p>
										</div>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openimmunizationchart1()">
										<img src="cicon/Anc_icon.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Antenatal Schedule</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="showinvst()">
										<img src="cicon/invst.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Show Investigation</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openconcentform()">
										<img src="popicons/eye.png" alt="..." class="">
										<div class="caption">
											<p align="center" class="dtdf">Declaration Form</p>
										</div>
									</div>
								</div>
								
								
							</div>
						</fieldset>
						</md-content>

						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Account</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<s:form action="Accounts" id="accountchargefrm" method="post"
										onsubmit="return redirectToCreateCharge()" target="formtarget">

										<s:hidden name="clientId" id="accountChargeClientid" />
										<s:hidden name="thirdParty" id="accountchargethirdparty" />
										<s:hidden name="transactionType"
											id="accountchargetransactionType" />
										<s:hidden name="location" id="accountsLocationid" />
										<s:hidden name="client" id="accountclientid" />
										<s:hidden name="payby" id="accountpayby"></s:hidden>
										<s:hidden name="appointmentid" id="crtchargeapmtid" />
										<input type="hidden" name="autoselect" id="autoselect" value="2">
										<div class="thumbnail" id="completeapmt"
											onclick="redirectToCreateCharge()" style="cursor: pointer;">
											<img src="popicons/invoice.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">Create Invoice</p>
											</div>
										</div>


									</s:form>
								</div>


								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<s:form action="Statement" id="view_acc_frm" method="post"
										onsubmit="return redirectToViewAcc()" target="formtarget">

										<s:hidden name="clientId" id="viewAccClientid" />
										<s:hidden name="thirdParty" id="viewAccthirdparty" />
										<s:hidden name="transactionType" id="viewAcctransactionType" />
										<s:hidden name="location" id="viewAccLocationid" />
										<s:hidden name="client" id="viewAccclientname" />
										<s:hidden name="payby" id="viewAccPayby"></s:hidden>
										<s:hidden name="fromDate" id="viewAccfromDateid" />
										<s:hidden name="toDate" id="viewAcctoDateid" />
										<%
											if (loginfo.isOpd_viewaccount()) {
										%>
										<div class="thumbnail" id="sendLetter"
											onclick="redirectToViewAcc()" style="cursor: pointer;">
											<img src="popicons/view_account.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">View Account</p>
											</div>
										</div>
										<%
											} else {
										%>
										<div class="thumbnail disabled" id="">
											<img src="popicons/view_account.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">View Account</p>
											</div>
										</div>
										<%
											}
										%>


									</s:form>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="sendLetter"
										onclick="setInstantCashDesk()" style="cursor: pointer;">
										<img src="popicons/cash_desk.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Cash Desk</p>
										</div>
									</div>


								</div>


								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_viewaccount()) {
									%>
									<div class="thumbnail" id="sendLetter"
										onclick="redirectToApmtFinder()" style="cursor: pointer;">
										<img src="popicons/finder.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Appointment Finder</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="sendLetter">
										<img src="popicons/finder.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Appointment Finder</p>
										</div>
									</div>
									<%
										}
									%>
								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="updatereport"
										onclick="updatestatusreportpopup()" style="cursor: pointer;">
										<img src="popicons/report.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Report Status</p>
										</div>
									</div>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="sendLetter"
										onclick="showApptTreatment()" style="cursor: pointer;">
										<img src="popicons/view_treatment.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">View Treatment</p>
										</div>
									</div>

								</div>


								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_advandref()) {
									%>
									<div class="thumbnail" id="" onclick="showcrddebit()"
										style="cursor: pointer;">
										<img src="cicon/raise_credit.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Advance & Refund</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="cicon/raise_credit.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Advance & Refund</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="" onclick="adddebitchargess()"
										style="cursor: pointer;">
										<img src="cicon/addcharge.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add Charge</p>
										</div>
									</div>

								</div>
								<div
									class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset hidden">
									<div class="thumbnail" id="" onclick="modfyDiagnosis()"
										style="cursor: pointer;">
										<img src="popicons/modify.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add Diagnosis</p>
										</div>
									</div>

								</div>

							</div>
						</fieldset>
						</md-content>


					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal" onclick="movetosetCommonAction()">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Modify DNA Popup -->
	<div class="modal fade modal-draggable" id="modifydnapopup"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="movetosetCommonAction()">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Day-To-Day Admin &nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#"
							style="color: yellow;" onclick="showLetterHead()"
							title="Print Letterhead"><i class="fa fa-print"
								aria-hidden="true"></i></a></span> &nbsp;&nbsp;|&nbsp;&nbsp;<span
							id="editSave3"><a href="#" style="color: yellow;"
							onclick="editBMI(3)" title="Edit"><i class="fa fa-pencil"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="sendLetter()"
							title="Send Email"><i class="fa fa-envelope-o"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="sendsmspopupopen()"
							title="Send SMS"><i class="fa fa-commenting-o"
								aria-hidden="true"></i></a></span>
					</h4>
				</div>
				<div class="modal-body" style="padding: 0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 heightsetbmi">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Height <small>cm</small></label>
										<p id="height3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Weight <small>Kg's</small></label>
										<p id="weight3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">BMI</label>
										<p id="bmi3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Pulse</label>
										<p id="pulse3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sys-BP</label>
										<p id="sysbp3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Dia-BP</label>
										<p id="diabp3"></p>
									</div>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sugar Fasting</label>
										<p id="sugarfasting3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Post Meal</label>
										<p id="postmeal3"></p>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Head Circum</label>
										<p id="bsa3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Temp</label>
										<p id="temprature3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">SPO2</label>
										<p id="spo3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
								<div class="form-group marbo10form">
										<label for="exampleInputEmail1">LMP Date</label>
										<p id="lmpd3"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
							</div>
						</div>
					</div>
					<div id="modifydnaClient1"
						style="font-size: 16px; color: rgb(61, 61, 61);">Manoj
						Mishra</div>
						<div style="font-size: 16px; color: rgb(61, 61, 61);">
					<label id="dnapatientname"></label>
							</div>

					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 mdscr md9">
						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1 hidden">
						<fieldset class="standard">
							<legend>Patient</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">


							</div>
						</fieldset>
						</md-content>
						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Smart Care</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<s:form action="Accounts" id="accountchargefrm" target="blank">
										<s:hidden name="clientId" id="accountChargeClientid" />
										<s:hidden name="thirdParty" id="accountchargethirdparty" />
										<s:hidden name="transactionType"
											id="accountchargetransactionType" />
										<s:hidden name="location" id="accountsLocationid" />
										<s:hidden name="client" id="accountclientid" />
										<s:hidden name="payby" id="accountpayby"></s:hidden>
										<input type="hidden" name="autoselect" id="autoselect" value="2">
										<div class="thumbnail" id="completeapmt"
											onclick="redirectToCreateCharge()" style="cursor: pointer;">
											<img src="popicons/invoice.png" alt="..."
												class="img-responsive">
											<div class="caption">
											<input type="hidden" name="autoselect" id="autoselect" value="2">
												<p align="center" class="dtdf">Create Invoice</p>
											</div>
										</div>


									</s:form>
								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<s:form action="Statement" id="view_acc_frm" target="blank">
										<s:hidden name="clientId" id="viewAccClientid" />
										<s:hidden name="thirdParty" id="viewAccthirdparty" />
										<s:hidden name="transactionType" id="viewAcctransactionType" />
										<s:hidden name="location" id="viewAccLocationid" />
										<s:hidden name="client" id="viewAccclientname" />
										<s:hidden name="payby" id="viewAccPayby"></s:hidden>
										<s:hidden name="fromDate" id="viewAccfromDateid" />
										<s:hidden name="toDate" id="viewAcctoDateid" />
										<input type="hidden" name="autoselect" id="autoselect" value="2">
										<div class="thumbnail" id="completeapmt"
											onclick="redirectToViewAcc()" style="cursor: pointer;">
											<img src="popicons/view_account.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">View Accounts</p>
											</div>
										</div>


									</s:form>
								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset"
									id="clientdidnotcome">
									<div class="thumbnail" id="dna"
										onclick="setModifyClientDidNotCome()" style="cursor: pointer;">
										<img src="popicons/reset.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf" id="dnatextid">Modify DNA</p>
										</div>
									</div>


								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="updatereport"
										onclick="updatestatusreportpopup()" style="cursor: pointer;">
										<img src="popicons/report.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Update Report Status</p>
										</div>
									</div>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="sendLetter"
										onclick="showApptTreatment()" style="cursor: pointer;">
										<img src="popicons/view_treatment.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">View Treatment</p>
										</div>
									</div>

								</div>



								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="" onclick="showcrddebit()"
										style="cursor: pointer;">
										<img src="popicons/consultation_note.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Advance & Refund</p>
										</div>
									</div>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="" onclick="adddebitchargess()"
										style="cursor: pointer;">
										<img src="cicon/addcharge.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add Charge</p>
										</div>
									</div>
								</div>

							</div>
						</fieldset>
						</md-content>
						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Account</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="sendLetter"
										onclick="redirectToApmtFinder()" style="cursor: pointer;">
										<img src="popicons/finder.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Appointment Finder</p>
										</div>
									</div>

								</div>


								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="" onclick="showotpriscription()"
										style="cursor: pointer;">
										<img src="popicons/addot.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add OT Equipment</p>
										</div>
									</div>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="" onclick="listotequipmwnt()"
										style="cursor: pointer;">
										<img src="popicons/listot.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">List OT Equipment</p>
										</div>
									</div>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="" onclick="openuploadPopup()"
										style="cursor: pointer;">
										<img src="popicons/uploaddocs.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="fontpup">Upload Docs</p>
										</div>
									</div>
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openVitalClient()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vital</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openViewRecordVital()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">View Records</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openimmunizationchart()">
										<img src="cicon/pregnancysm.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vaccines Chart</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openclinicalnotes()">
										<img src="cicon/autocare.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Clinical Notes</p>
										</div>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail followuppop" style="cursor: pointer;"
										onclick="openfollowpop()">
										<img src="cicon/followup.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Follow Up</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail followuppop" style="cursor: pointer;"
										onclick="opentoipd()">
										<img src="cicon/Add_client.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">To IPD</p>
										</div>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openimmunizationchart1()">
										<img src="cicon/Anc_icon.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Antenatal Schedule</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="showinvst()">
										<img src="cicon/invst.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Show Investigation</p>
										</div>
									</div>
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openconcentform()">
										<img src="popicons/eye.png" alt="..." class="">
										<div class="caption">
											<p align="center" class="dtdf">Declaration Form</p>
										</div>
									</div>
								</div>
								
							</div>
						</fieldset>
						</md-content>







					</div>




				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal" onclick="movetosetCommonAction()">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!--Block Appointment Popup -->
	<div class="modal fade modal-draggable" id="blockapmtdaytodsypopup"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Day-To-Day Admin</h4>
				</div>
				<div class="modal-body" style="padding: 0px; min-height: 300px;">
					<div
						class="col-lg-12 col-md-12 col-sm-12 col-xs-12 heightsetbmi hidden">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Height <small>cm</small></label>
										<p id="height4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Weight <small>Kg's</small></label>
										<p id="weight4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">BMI</label>
										<p id="bmi4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Pulse</label>
										<p id="pulse4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sys-BP</label>
										<p id="sysbp4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Dia-BP</label>
										<p id="diabp4"></p>
									</div>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sugar Fasting</label>
										<p id="sugarfasting4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Post Meal</label>
										<p id="postmeal4"></p>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Head Circum</label>
										<p id="bsa4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Temp</label>
										<p id="temprature4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">SPO2</label>
										<p id="spo4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
								<div class="form-group marbo10form">
										<label for="exampleInputEmail1">LMP Date</label>
										<p id="lmpd4"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
							</div>
						</div>
					</div>

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 mdscr md9">
						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Block Slot</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 padimp">
									<div class="thumbnail" id="deleteapmtiddiv"
										onclick="delOnlyBlockSlot()" style="cursor: pointer;">
										<img src="popicons/delete.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Cancel</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 padimp">
									<div class="thumbnail" id="deleteapmtiddiv"
										onclick="setworkcompleted(1)" style="cursor: pointer;">
										<img src="popicons/cbs.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Completed</p>
										</div>
									</div>
								</div>
					    	</div> -->

								<!-- schedular adarsh changes -->
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 padimp">
									<div class="thumbnail" id="deleteapmtiddiv"
										onclick="deletetask()" style="cursor: pointer;">
										<img src="popicons/cdna.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Not Completed</p>
										</div>
									</div>

								</div>
							</div>
						</fieldset>
						</md-content>








					</div>
				</div>

			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-primary hidden"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
	</div>

	<!-- End -->


	<!--OT Popup -->
	<div class="modal fade modal-draggable" id="otmodifypopup"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Day-To-Day Admin &nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#"
							onclick="showLetterHead()" title="Print Letterhead"><i
								class="fa fa-print" aria-hidden="true"></i></a></span>
						&nbsp;&nbsp;|&nbsp;&nbsp;<span id="editSave5"><a href="#"
							style="color: yellow;" onclick="editBMI(5)" title="Edit"><i
								class="fa fa-pencil" aria-hidden="true"></i></a></span>
					</h4>
				</div>
				<div class="modal-body" style="padding: 0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 heightsetbmi">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Height <small>cm</small></label>
										<p id="height5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Weight <small>Kg's</small></label>
										<p id="weight5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">BMI</label>
										<p id="bmi5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Pulse</label>
										<p id="pulse5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sys-BP</label>
										<p id="sysbp5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Dia-BP</label>
										<p id="diabp5"></p>
									</div>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sugar Fasting</label>
										<p id="sugarfasting5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Post Meal</label>
										<p id="postmeal5"></p>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Head Circum</label>
										<p id="bsa5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Temp</label>
										<p id="temprature5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">SPO2</label>
										<p id="spo5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
								<div class="form-group marbo10form">
										<label for="exampleInputEmail1">LMP Date</label>
										<p id="lmpd5"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
							</div>
						</div>
					</div>

					<div class="row manaboxwi">
						<div class="col-lg-12">
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 padimp">
								<div class="thumbnail" id="modify" onclick="adddebitchargess()"
									style="cursor: pointer;">
									<img src="cicon/addcharge.png" alt="..." class="img-responsive">
									<div class="caption">
										<p align="center" class="dtdf">Add Charge</p>
									</div>
								</div>
							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 padimp">
								<div class="thumbnail" id="modify" onclick="showcrddebit()"
									style="cursor: pointer;">
									<img src="cicon/raise_credit.png" alt="..."
										class="img-responsive">
									<div class="caption">
										<p align="center" class="dtdf">Advance & Refund</p>
									</div>
								</div>
							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 padimp">
								<div class="thumbnail" id="deleteapmtiddiv"
									onclick="checkotcharge()" style="cursor: pointer;">
									<img src="popicons/delete.png" alt="..." class="img-responsive">
									<div class="caption">
										<p align="center" class="dtdf">Cancel</p>
									</div>
								</div>
							</div>


							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 padimp">



								<s:form action="ProcessingAccount" id="accountpaymentfrm"
									method="post" onsubmit="return redirectToRecordPayment()"
									target="formtarget">

									<s:hidden name="clientId" id="accountpaymentClientid" />
									<s:hidden name="thirdParty" id="accountpaymentthirdparty" />
									<s:hidden name="transactionType"
										id="accountpaymenttransactionType" />
									<s:hidden name="location" id="accountspaymentLocationid" />
									<s:hidden name="client" id="accountpaymentclientid" />
									<s:hidden name="fromDate" id="accountspaymentfromDateid" />
									<s:hidden name="toDate" id="accountspaymenttoDateid" />
									<s:hidden name="payby" id="accountPaymentPayby"></s:hidden>
									<input type="hidden" name="autoselect" id="autoselect" value="2">

									<div class="thumbnail" id="completeapmt"
										onclick="redirectToRecordPayment()" style="cursor: pointer;">
										<img src="popicons/raise_credit.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center">Record Payment</p>
										</div>
									</div>


								</s:form>
							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 dtd padimp">
								<div class="thumbnail" id="sendLetter" onclick="sendLetter()"
									style="cursor: pointer;">
									<img src="popicons/send_letter.png" alt="..."
										class="img-responsive">
									<div class="caption">
										<p align="center">Send Email/Letter</p>
									</div>
								</div>
							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 padimp">

								<s:form action="Statement" id="view_acc_frm" target="blank">
									<s:hidden name="clientId" id="viewAccClientid" />
									<s:hidden name="thirdParty" id="viewAccthirdparty" />
									<s:hidden name="transactionType" id="viewAcctransactionType" />
									<s:hidden name="location" id="viewAccLocationid" />
									<s:hidden name="client" id="viewAccclientname" />
									<s:hidden name="payby" id="viewAccPayby"></s:hidden>
									<s:hidden name="fromDate" id="viewAccfromDateid" />
									<s:hidden name="toDate" id="viewAcctoDateid" />
									<input type="hidden" name="autoselect" id="autoselect" value="2">


									<div class="thumbnail" id="completeapmt"
										onclick="redirectToViewAcc()" style="cursor: pointer;">
										<img src="popicons/view_account.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center">View Accounts</p>
										</div>
									</div>



								</s:form>
							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 dtd padimp">
								<%-- <s:form action="Accounts" id="accountchargefrm" target="blank"> --%>

								<s:form action="Accounts" id="accountchargefrm" method="post"
									onsubmit="return redirectToCreateCharge()" target="formtarget">

									<s:hidden name="clientId" id="accountChargeClientid" />
									<s:hidden name="thirdParty" id="accountchargethirdparty" />
									<s:hidden name="transactionType"
										id="accountchargetransactionType" />
									<s:hidden name="location" id="accountsLocationid" />
									<s:hidden name="client" id="accountclientid" />
									<s:hidden name="payby" id="accountpayby"></s:hidden>
									<s:hidden name="appointmentid" id="crtchargeapmtid" />
									<input type="hidden" name="autoselect" id="autoselect" value="2">

									<div class="thumbnail" id="completeapmt"
										onclick="redirectToCreateCharge()" style="cursor: pointer;">
										<img src="popicons/invoice.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center">Create Invoice</p>
										</div>
									</div>
								</s:form>
							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 dtd padimp">

								<div class="thumbnail" id="" onclick="sendsmspopupopen()"
									style="cursor: pointer;">
									<img src="popicons/sms.png" alt="..." class="img-responsive">
									<div class="caption">
										<p align="center" class="dtdf">Send SMS</p>
									</div>
								</div>

							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 dtd padimp">

								<div class="thumbnail" id="" onclick="showotpriscription()"
									style="cursor: pointer;">
									<img src="popicons/addot.png" alt="..."
										style="width: 64px; height: 64px;">
									<div class="caption">
										<p align="center" class="dtdf">Add OT Equipment</p>
									</div>
								</div>

							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6 dtd padimp">

								<div class="thumbnail" id="" onclick="listotequipmwnt()"
									style="cursor: pointer;">
									<img src="popicons/listot.png" alt="..."
										style="width: 64px; height: 64px;">
									<div class="caption">
										<p align="center" class="dtdf">List OT Equipment</p>
									</div>
								</div>

							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
								<div class="thumbnail" id="" onclick="openuploadPopup()"
									style="cursor: pointer;">
									<img src="popicons/uploaddocs.png" alt="..."
										class="img-responsive">
									<div class="caption">
										<p align="center" class="fontpup">Upload Docs</p>
									</div>
								</div>
							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openVitalClient()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vital</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openViewRecordVital()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">View Records</p>
										</div>
									</div>
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openimmunizationchart()">
										<img src="cicon/pregnancysm.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vaccines Chart</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openclinicalnotes()">
										<img src="cicon/autocare.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Clinical Notes</p>
										</div>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail followuppop" style="cursor: pointer;"
										onclick="openfollowpop()">
										<img src="cicon/followup.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Follow Up</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail followuppop" style="cursor: pointer;"
										onclick="opentoipd()">
										<img src="cicon/Add_client.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">To IPD</p>
										</div>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openimmunizationchart1()">
										<img src="cicon/Anc_icon.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Antenatal Schedule</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="showinvst()">
										<img src="cicon/invst.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Show Investigation</p>
										</div>
									</div>
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openconcentform()">
										<img src="popicons/eye.png" alt="..." class="">
										<div class="caption">
											<p align="center" class="dtdf">Declaration Form</p>
										</div>
									</div>
								</div>
								
								
						</div>
					</div>

				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary hidden"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!-- End -->





	<!--Record Payment Action Popup -->
	<div class="modal fade modal-draggable" id="recordpaymentpopup"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true" data-keyboard="false" data-backdrop="static">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" style="margin-top: -7px !important;"
						onclick="movetosetCommonAction()">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Day-To-Day Admin &nbsp;&nbsp;|&nbsp;&nbsp;<span><a href="#"
							style="color: yellow;" onclick="showLetterHead()"
							title="Print Letterhead"><i class="fa fa-print"
								aria-hidden="true"></i></a></span> &nbsp;&nbsp;|&nbsp;&nbsp;<span
							id="editSave6"><a href="#" style="color: yellow;"
							onclick="editBMI(6)" title="Edit"><i class="fa fa-pencil"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="sendLetter()"
							title="Send Email"><i class="fa fa-envelope-o"
								aria-hidden="true"></i></a></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><a
							href="#" style="color: yellow;" onclick="sendsmspopupopen()"
							title="Send SMS"><i class="fa fa-commenting-o"
								aria-hidden="true"></i></a></span>
								
									&nbsp;&nbsp;|&nbsp;&nbsp;<span>
								<% if(loginfo.getJobTitle().equals("Reception")){%>
								<a
							href="#" style="color: yellow;" onclick="openpatientmedia()"
							title="Body Chart"><i class="fa fa-female" aria-hidden="true"></i></a>
								<%}else{ %>
								<a
							href="#" style="color: yellow;" onclick="openeditedpatientmedia()"
							title="Body Chart"><i class="fa fa-female" aria-hidden="true"></i></a>
								<% }%>
								</span>
								
					</h4>
				</div>
				<div class="modal-body" style="padding: 0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 heightsetbmi">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Height <small>cm</small></label>
										<p id="height6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Weight <small>Kg's</small></label>
										<p id="weight6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">BMI</label>
										<p id="bmi6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Pulse</label>
										<p id="pulse6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sys-BP</label>
										<p id="sysbp6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Dia-BP</label>
										<p id="diabp6"></p>
									</div>
								</div>
							</div>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Sugar Fasting</label>
										<p id="sugarfasting6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Post Meal</label>
										<p id="postmeal6"></p>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Head Circum</label>
										<p id="bsa6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Temp</label>
										<p id="temprature6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">SPO2</label>
										<p id="spo6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
								<div class="form-group marbo10form">
										<label for="exampleInputEmail1">LMP Date</label>
										<p id="lmpd6"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
							</div>
						</div>
					</div>
					<div id="modifyClient2"
						style="font-size: 16px; color: rgb(61, 61, 61);">Manoj
						Mishra</div>

					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-8 mdscr md9">

						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Patient</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="completeapmt"
										onclick="withpaymentCompleteAppointment()"
										style="cursor: pointer;">
										<img src="popicons/cbs.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" style="font-size: 9px !important" class="dtdf"><b>Final Complete Appointment</b></p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset"
									id="clientisbeingseen">
									<div class="thumbnail" id="clientseen"
										onclick="setClientIsBeingSeen(2)" style="cursor: pointer;">
										<img src="popicons/client_seen.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf"><%=loginfo.getPatientname_field() %> is Being Seen</p>
										</div>
									</div>
								</div>
							</div>
						</fieldset>
						</md-content>
						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Smart Care</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="sendLetter"
										onclick="redirectToApmtFinder()" style="cursor: pointer;">
										<img src="popicons/finder.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Appointment Finder</p>
										</div>
									</div>

								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_prescription()) {
									%>
									<div class="thumbnail" id="" onclick="showopdpriscription()"
										style="cursor: pointer;">
										<img src="popicons/medicine.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request prescription</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/medicine.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request prescription</p>
										</div>
									</div>
									<%
										}
									%>

								</div>



								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_prescription()) {
									%>
									<div class="thumbnail" id="" onclick="showopdInvestigation()"
										style="cursor: pointer;">
										<img src="popicons/asmnt.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request Investigation</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/asmnt.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Request Investigation</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_ot()) {
									%>
									<div class="thumbnail" id="" onclick="showotpriscription()"
										style="cursor: pointer;">
										<img src="popicons/addot.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add OT Equipment</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/addot.png" alt="..." class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add OT Equipment</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_ot()) {
									%>
									<div class="thumbnail" id="" onclick="listotequipmwnt()"
										style="cursor: pointer;">
										<img src="popicons/listot.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">List OT Equipment</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="popicons/listot.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">List OT Equipment</p>
										</div>
									</div>
									<%
										}
									%>

								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" id="" onclick="openuploadPopup()"
										style="cursor: pointer;">
										<img src="popicons/uploaddocs.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="fontpup">Upload Docs</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openVitalClient()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vital</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openViewRecordVital()">
										<img src="popicons/otnote.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">View Records</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openimmunizationchart()">
										<img src="cicon/pregnancysm.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Vaccines Chart</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail" style="cursor: pointer;"
										onclick="openclinicalnotes()">
										<img src="cicon/autocare.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Clinical Notes</p>
										</div>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail followuppop" style="cursor: pointer;"
										onclick="openfollowpop()">
										<img src="cicon/followup.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Follow Up</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail followuppop" style="cursor: pointer;"
										onclick="opentoipd()">
										<img src="cicon/Add_client.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">To IPD</p>
										</div>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openimmunizationchart1()">
										<img src="cicon/Anc_icon.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Antenatal Schedule</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="showinvst()">
										<img src="cicon/invst.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Show Investigation</p>
										</div>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<div class="thumbnail " style="cursor: pointer;"
										onclick="openconcentform()">
										<img src="popicons/eye.png" alt="..." class="">
										<div class="caption">
											<p align="center" class="dtdf">Declaration Form</p>
										</div>
									</div>
								</div>
								
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="updatereport"
										onclick="updatestatusreportpopup()" style="cursor: pointer;">
										<img src="popicons/report.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Update Report Status</p>
										</div>
									</div>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="sendLetter"
										onclick="showApptTreatment()" style="cursor: pointer;">
										<img src="popicons/view_treatment.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">View Treatment</p>
										</div>
									</div>

								</div>
							</div>
						</fieldset>
						</md-content>
						<md-content
							class="md-padding checkboxdemoBasicUsage checkboxDemo1">
						<fieldset class="standard">
							<legend>Account</legend>
							<div layout="row" layout-wrap="" class="layout-wrap layout-row">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<%-- <s:form action="ProcessingAccount" id="accountpaymentfrm" target="blank"> --%>

									<s:form action="ProcessingAccount" id="accountpaymentfrm"
										method="post" onsubmit="return redirectToRecordPayment()"
										target="formtarget">

										<s:hidden name="clientId" id="accountpaymentClientid" />
										<s:hidden name="thirdParty" id="accountpaymentthirdparty" />
										<s:hidden name="transactionType"
											id="accountpaymenttransactionType" />
										<s:hidden name="location" id="accountspaymentLocationid" />
										<s:hidden name="client" id="accountpaymentclientid" />
										<s:hidden name="fromDate" id="accountspaymentfromDateid" />
										<s:hidden name="toDate" id="accountspaymenttoDateid" />
										<s:hidden name="payby" id="accountPaymentPayby"></s:hidden>
										<input type="hidden" name="autoselect" id="autoselect" value="2">


										<div class="thumbnail" id="completeapmt"
											onclick="redirectToRecordPayment()" style="cursor: pointer;">
											<img src="popicons/raise_credit.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">Record Payment</p>
											</div>
										</div>


									</s:form>
								</div>


								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%-- <s:form action="Accounts" id="accountchargefrm" target="blank"> --%>

									<s:form action="Accounts" id="accountchargefrm" method="post"
										onsubmit="return redirectToCreateCharge()" target="formtarget">

										<s:hidden name="clientId" id="accountChargeClientid" />
										<s:hidden name="thirdParty" id="accountchargethirdparty" />
										<s:hidden name="transactionType"
											id="accountchargetransactionType" />
										<s:hidden name="location" id="accountsLocationid" />
										<s:hidden name="client" id="accountclientid" />
										<s:hidden name="payby" id="accountpayby"></s:hidden>
										<s:hidden name="appointmentid" id="crtchargeapmtid" />
										<input type="hidden" name="autoselect" id="autoselect" value="2">

										<div class="thumbnail" id="completeapmt"
											onclick="redirectToCreateCharge()" style="cursor: pointer;">
											<img src="popicons/invoice.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">Create Invoice</p>
											</div>
										</div>


									</s:form>
								</div>




								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%-- <s:form action="Statement" id="view_acc_frm" target="blank"> --%>

									<s:form action="Statement" id="view_acc_frm" method="post"
										onsubmit="return redirectToViewAcc()" target="formtarget">

										<s:hidden name="clientId" id="viewAccClientid" />
										<s:hidden name="thirdParty" id="viewAccthirdparty" />
										<s:hidden name="transactionType" id="viewAcctransactionType" />
										<s:hidden name="location" id="viewAccLocationid" />
										<s:hidden name="client" id="viewAccclientname" />
										<s:hidden name="payby" id="viewAccPayby"></s:hidden>
										<s:hidden name="fromDate" id="viewAccfromDateid" />
										<s:hidden name="toDate" id="viewAcctoDateid" />
										<input type="hidden" name="autoselect" id="autoselect" value="2">
										<%
											if (loginfo.isOpd_viewaccount()) {
										%>
										<div class="thumbnail" id="sendLetter"
											onclick="redirectToViewAcc()" style="cursor: pointer;">
											<img src="popicons/view_account.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">View Account</p>
											</div>
										</div>
										<%
											} else {
										%>
										<div class="thumbnail disabled" id="">
											<img src="popicons/view_account.png" alt="..."
												class="img-responsive">
											<div class="caption">
												<p align="center" class="dtdf">View Account</p>
											</div>
										</div>
										<%
											}
										%>


									</s:form>
								</div>



								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">
									<%
										if (loginfo.isOpd_advandref()) {
									%>
									<div class="thumbnail" id="" onclick="showcrddebit()"
										style="cursor: pointer;">
										<img src="cicon/raise_credit.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Advance & Refund</p>
										</div>
									</div>
									<%
										} else {
									%>
									<div class="thumbnail disabled" id="">
										<img src="cicon/raise_credit.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Advance & Refund</p>
										</div>
									</div>
									<%
										}
									%>

								</div>

								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="" onclick="adddebitchargess()"
										style="cursor: pointer;">
										<img src="cicon/addcharge.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add Charge</p>
										</div>
									</div>

								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 padrigset">

									<div class="thumbnail" id="mvmdappointment" onclick="moveappointmentbyappointment(1)"
										style="cursor: pointer;">
										<img src="cicon/addcharge.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Move Appointment</p>
										</div>
									</div>

								</div>
								
								
								<div
									class="col-lg-2 col-md-2 col-sm-2 col-xs-3 hidden padrigset">

									<div class="thumbnail" id="" onclick="modfyDiagnosis()"
										style="cursor: pointer;">
										<img src="popicons/modify.png" alt="..."
											class="img-responsive">
										<div class="caption">
											<p align="center" class="dtdf">Add Diagnosis</p>
										</div>
									</div>

								</div>

							</div>
						</fieldset>
						</md-content>


					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal" onclick="movetosetCommonAction()">Close</button>
					</div>
				</div>
			</div>
		</div>


		<!-- End -->

		<!-- Create Invoice Div -->
		<div class="modal fade" id="createinvoiceDiv" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Record Payment</h4>
					</div>
					<div class="modal-body">
						<jsp:include page="/diarymanagement/pages/createInvoice.jsp"></jsp:include>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="savePaymentForInvoice()">Pay</button>
						<button type="button" class="btn btn-primary"
							onclick="previewPaymentInvoice()">Preview</button>

						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Submit Invoice Modal -->
		<div class="modal fade" id="submitInvoicePopup" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Submit Invoice</h4>
					</div>
					<div class="modal-body">
						<jsp:include page="/diarymanagement/pages/submitInvoice.jsp"></jsp:include>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="updateInvoiceStatus()">Submit</button>

						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Reminder Modal -->
		<div class="modal fade" id="reminderPopup" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Appointment
							Confirmation and Reminder</h4>
					</div>
					<div class="modal-body">
						<div id="remiderClient" style="font-size: 16px;">
							<font color="white">Manoj Mishra</font>
						</div>

						<s:hidden name="practitionersName" id="practitionersName" />
						<s:hidden name="practitionersId" id="practitionersId" />
						<s:hidden name="clientName" id="clientName"></s:hidden>
						<s:hidden name="patientId" id="patientId" />
						<s:hidden name="aptmStartTime" id="aptmStartTime" />
						<s:hidden name="aptmDuration" id="aptmDuration" />
						<s:hidden name="practitionersEmail" id="practitionersEmail"></s:hidden>
						<s:hidden name="clientEmail" id="clientEmail"></s:hidden>
						<s:hidden name="aptmlocation" id="aptmlocation"></s:hidden>

						<div class="row">
							<div class="col-lg-12">
								<input type="button" class="btn btn-default width-full"
									value="Send Booking Confirmation (SMS)...Done!">
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<input type="button" class="btn btn-default width-full"
									value="Send Booking Confirmation (SMS)...Done!">
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<input type="button" class="btn btn-default width-full"
									value="Appointment Reminder (E-mail)...Scheduled!"
									onclick="emailSend()">
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<input type="button" class="btn btn-default width-full"
									value="Follow-Up Reminder (Email)...Scheduled!">
							</div>
						</div>

					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>



		<!-- client did not attend popup  -->

		<div class="modal fade" id="clientdidnotattentpopup" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							onclick="movetosetCommonAction()">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel"><%=loginfo.getPatientname_field() %> Did Not
							Attend</h4>
					</div>
					<div class="modal-body" id="cdna">
						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-12 col-md-12">
								<label> Appointment Details :</label>
							</div>
						</div>
						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5"><%=loginfo.getPatientname_field() %>
								Name :</div>
							<div class="col-lg-8 col-md-7">
								<input type="text" class="form-control" id="dnaclinentname"
									name="dnaclinentname" size="30" disabled="disabled" />
							</div>
						</div>


						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Practitioner
								Name :</div>
							<div class="col-lg-8 col-md-7">
								<input type="text" class="form-control"
									id="missedappointmentwith" name="missedappointmentwith"
									size="30" disabled="disabled" />
							</div>
						</div>



						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Date :</div>
							<div class="col-lg-8 col-md-7">
								<input type="text" class="form-control" id="didnotattendDate"
									name="didnotattendDate" readonly="readonly" disabled="disabled">
							</div>
						</div>


						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Time :</div>
							<div class="col-lg-8 col-md-7">
								<input type="text" class="form-control" name="didnotattentTime"
									id="didnotattentTime" disabled="disabled" size="5">
							</div>
						</div>

						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Location
								:</div>
							<div class="col-lg-8 col-md-7">
								<input type="text" class="form-control"
									name="didnotattentDuration" id="didnotattentDuration"
									disabled="disabled" size="5">
							</div>
						</div>

						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Pay By :</div>
							<div class="col-lg-8 col-md-7">

								<input type="text" class="form-control" name="dnapayby"
									id="dnapayby" disabled="disabled" size="5">
							</div>
						</div>

						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Appointment
								Name :</div>
							<div class="col-lg-8 col-md-7">
								<input type="text" class="form-control"
									name="dnaAppointmentName" id="dnaAppointmentName"
									disabled="disabled" size="5">
							</div>

						</div>

						<div class="row" style="margin-top: 5px;" id="dnatpnotesdiv">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">TP Notes
								:</div>
							<div class="col-lg-8 col-md-7">

								<textarea name="dnatpnotes" id="dnatpnotes" rows="3" cols="60"
									readonly="readonly"></textarea>
							</div>

						</div>




						<hr />

						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-12 col-md-12">
								<label> DNA Charge Details :</label>
							</div>
						</div>

						<div class="row" style="margin-top: 5px;" id="offsetdiv">
							<div class="col-lg-4 col-md-4">
								<label> Offset Treatment Session for DNA :</label>
							</div>
							<div class="col-lg-1 col-md-1">
								<input type="checkbox" name="dnaOffset" id="dnaOffset" />
							</div>

						</div>

						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Who will
								Pay :</div>
							<div class="col-lg-8 col-md-7">
								<input type="radio" name="dnawhopayradio" id="dnaclientrdo"
									value="0"> <%=loginfo.getPatientname_field() %>(Self) <input type="radio"
									name=dnawhopayradio id="dnatprdo" value="1">Third
								Party
							</div>
						</div>

						<div class="row" style="margin-top: 5px;" id="defineddnachargeid">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">TP
								Predefined DNA Charge :</div>
							<div class="col-lg-8 col-md-7">
								<span id="predefindtpcharge"></span>

							</div>

						</div>



						<div class="row" style="margin-top: 5px;" id="enterdnachargeid">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Enter DNA
								Charge:</div>
							<div class="col-lg-8 col-md-7">
								<span id="dnaChargespanid"><%=Constants.getCurrency(loginfo)%>
									<input type="text" name="dnaCharge" id="dnaCharge" value="0"
									size="5" style="text-align: center;" title="Enter DNA Charge"></span>
							</div>

						</div>

						<div class="row" style="margin-top: 5px;" id="showdnachargeid">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">
								DNA Charge
								<%=Constants.getCurrency(loginfo)%>
								:
							</div>
							<div class="col-lg-8 col-md-7">

								<span id="dnaammtid"> <%=Constants.getCurrency(loginfo)%>
									<input type="text" name="editdnaCharge" id="editdnaCharge"
									value="0" size="5" style="text-align: center;"
									title="Enter DNA Charge">
								</span>

							</div>

						</div>

						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Why :</div>
							<div class="col-lg-8 col-md-7">
								<select name="why" id="why" class="form-control"
									style="width: 55%; padding: .4em;">
									<option value="0">Select Reason</option>
									<option value="NO REASON">No Reason</option>
									<option value="DNA">No Money</option>
									<option value="NO REASON">No Care Given</option>
									<option value="NO REASON">No Transport</option>
									<option value="UNWELL">Unwell</option>
									<option value="LATE CANCELATION">Late Cancelation</option>
									<option value="NO REASON">Unsuitable Timing</option>
								</select>
							</div>
						</div>


						<hr />


						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Please
								Confirm :</div>
							<div class="col-lg-8 col-md-8">
								<input type="checkbox" name="dnachk" id="dnachk"
									onclick="increaseTreatmentEpisodeCnt()"> <label
									for="dnachk">D.N.A.</label>


							</div>
						</div>

						<div class="row" style="margin-top: 5px;">
							<div class="col-lg-4 col-md-4 col-sm-4  col-md-5">Notes :</div>
							<div class="col-lg-8 col-md-7">
								<textarea rows="3" cols="40" class="form-control"
									name="didnotattendNotes" id="didnotattendNotes"></textarea>

							</div>
						</div>





					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-primary"
							onclick="setClientDidNotComeConfirm()">Ok</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal" onclick="movetosetCommonAction()">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Add Patient Third Party Pop Up -->

		<div class="modal fade" id="addPatientThirdPartyPopup" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Select Third Party</h4>
					</div>
					<div class="modal-body">

						<jsp:include page="/thirdParties/pages/addThirdPartyOfClient.jsp"></jsp:include>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="savePatientThirdPartyDetail()">Save</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Add Third Party Details Modal -->
		<div class="modal fade" id="thirdPartyDetailsPopup" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Add New Third Party</h4>
					</div>
					<div class="modal-body" style="height: 600px; overflow: scroll;">
						<jsp:include page="/thirdParties/pages/addPopupNewTp.jsp"></jsp:include>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="saveNewTP4()">Save</button>

						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>


		<!-- Send Letter Details Modal -->
		<s:form id="upload" method="post" action="uploadSendMailInstantMsg"
			enctype="multipart/form-data" theme="simple">

			<div class="modal fade popoverpop " id="sendLetterPopup"
				tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-md">
					<div class="modal-content">


						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Send Email/Letter</h4>
						</div>
						<div class="modal-body">
							<div class="emailbodas">
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
									style="padding: 0px;">
									<div class="col-lg-4 col-md-4 col-xs-12">
										<div class="form-group">
											<label for="exampleInputEmail1"><%=loginfo.getPatientname_field() %></label>
											<s:textfield name="userName" id="userNameletter"
												readonly="true" cssClass="form-control" value="Client" />
											<label id="unameError" class="text-danger"></label>
										</div>
									</div>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<div class="form-group">
											<label for="exampleInputEmail1">Select Template</label> <select
												id="templateName" name="templateName"
												onchange="showTemplateDetails(this.id)"
												class="form-control showToolTip chosen"
												data-toggle="tooltip">
												<option value="0">Select Template Name</option>
											</select>
										</div>
									</div>
									<s:hidden name="id" id="id"></s:hidden>
									<s:hidden name="user" id="userletter"></s:hidden>
									<div class="col-lg-4 col-md-4 col-xs-12">
										<div class="form-group">
											<label for="exampleInputEmail1">Email</label>
											<s:textfield name="email" id="gpLetterEmail"
												cssClass="form-control showToolTip" title="Enter EmailId"
												data-toggle="tooltip" placeholder="Enter EmailId" />
											<label id="emailError" class="text-danger"></label>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
									style="padding: 0px;">
									<div class="col-lg-12 col-md-12 col-xs-12">
										<div class="form-group">
											<label for="exampleInputEmail1">Cc</label>
											<s:textfield theme="simple" id="gpLetterccEmail"
												name="ccEmail" cssClass="form-control showToolTip"
												title="Enter Cc" data-toggle="tooltip"
												placeholder="Enter Cc" />
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-xs-12">
										<div class="form-group">
											<label for="exampleInputEmail1">Subject</label> <input
												type="text" name="subject" id="gpLettersubject"
												class="form-control showToolTip" data-toggle="tooltip"
												title="Enter Subject" placeholder="Enter Subject">
										</div>
									</div>


								</div>

								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<div class="form-group">
										<label for="exampleInputEmail1">Attachments</label> <input
											type="hidden" id="fileUpload" name="fileUploadd" />
										<div id="drop">
											<!--FileUpload Controls will be added here -->
											<div id="upload"></div>
											<div id="draftAttachments"></div>
										</div>
										<input id="Button1" class="btn btn-default" type="button"
											value="Attach Files" onclick="AddFileUpload()" />
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
									<div class="form-group">
										<label for="exampleInputEmail1">Body</label>
										<s:textarea cssStyle="height:2500px;"
											class="form-control showToolTip textarea"
											data-toggle="tooltip" title="Write content"
											placeholder="Write content" name="body" id="emailBodyLetter" />
									</div>
								</div>

							</div>


						</div>

						<div class="modal-footer">
							<div class="row">
								<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2"></div>
								<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
									<div id="pdfFileIdLetter"></div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
									<div id="sendmail"></div>
								</div>
								<div class="col-lg-2 col-md-2 col-xs-2 col-sm-2">
									<div id="printId"></div>
								</div>
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<button type="button" id="saveId" class="btn btn-primary"
										onClick="fileUpload1(this.form,'sendLetterEmailInstantMsg','upload'); return false;">Send
										Mail</button>
									<button type="button" class="btn btn-primary"
										onclick="return createPdf()">Print</button>
									<button type="button" class="btn btn-primary hidden"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

		</s:form>

		<!-- Modal User Search-->

		<div class="modal fade" id="userSearch" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">User Search</h4>
					</div>
					<div class="modal-body">
						<jsp:include page="/tools/pages/allSendLetter.jsp"></jsp:include>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>


		<!-- Modal Email-->

		<div class="modal fade" id="sendEmailLetterPopup" tabindex="-1"
			role="dialog" aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Send Email</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="row">
									<div class="col-lg-1 col-md-1"></div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- Auto send Email -->
		<div class="modal fade" id="autosendemailpopup" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Send Booked
							Appointment Email</h4>
					</div>
					<div class="modal-body">
						<label>Do You Want To Send Email For Booked Appointment?</label>
					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-primary"
							onclick="openSendEmailPopup();" data-dismiss="modal">Yes</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- my loading -->
				<div class="modal fade" id="myloadingid" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
					</div>
					<div class="modal-body">
						<label>Loading....</label>
					</div>
					<div class="modal-footer">

						
					</div>
				</div>
			</div>
		</div>
		


		<!-- Add new ot charge popup -->
		<div class="modal fade" id="newotchargepopupid" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: 15000">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Add New Procedure
							Charge</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-3">
								<label>OT Charge</label>
							</div>
							<div class="col-lg-9">

								<input type="number" id="potcharge" name="potcharge"
									class="form-control showToolTip"
									onblur="setPuresevaExistingClientData()"
									placeholder="Enter OT Charge" title="Enter OT Charge"
									data-toggle="tooltip " />
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-3">
								<label>Anesthesia Charge</label>
							</div>
							<div class="col-lg-9">

								<input type="number" id="panetcharge" name="panetcharge"
									class="form-control showToolTip"
									onblur="setPuresevaExistingClientData()"
									placeholder="Enter Anesthesia  Charge"
									title="Enter Anesthesia Charge" data-toggle="tooltip " />
							</div>
						</div>
						<br>

						<div class="row">
							<div class="col-lg-3">
								<label>Surgeon Charge</label>
							</div>
							<div class="col-lg-9">

								<input type="number" id="psurcharge" name="psurcharge"
									class="form-control showToolTip"
									onblur="setPuresevaExistingClientData()"
									placeholder="Enter Surgeon Charge" title="Enter Surgeon Charge"
									data-toggle="tooltip " />
							</div>
						</div>
						<br>

						<div class="row">
							<div class="col-lg-3">
								<label>Surgical Item Charge</label>
							</div>
							<div class="col-lg-9">

								<input type="number" id="sic" name="sic"
									class="form-control showToolTip"
									onblur="setPuresevaExistingClientData()"
									placeholder="Enter Surgical Item Charge"
									title="Enter Surgical Item Charge" data-toggle="tooltip " />
							</div>
						</div>
						<br>
						
						<div class="row">
							<div class="col-lg-3">
								<label>Assisting staff Charge</label>
							</div>
							<div class="col-lg-9">
								<input type="number" id="assistaffcharge" name="assistaffcharge"
									class="form-control showToolTip"
									placeholder="Enter assisting staff Charge"
									title="Enter assisting staff Charge" data-toggle="tooltip " />
							</div>
						</div>
						<br>
						

						<div class="row">
							<div class="col-lg-6">
								<div class="col-lg-3">
									<label>Anesthesia Doctor</label>
								</div>
								<div class="col-lg-9">

									<input type="text" onchange="saveAnisthesiaDoctor(this.value)"
										id="anidoctor" name="anidoctor"
										class="form-control showToolTip"
										onblur="setPuresevaExistingClientData()"
										placeholder="Enter Anesthesia Doctor Name"
										title="Enter Anesthesia Doctor Name" data-toggle="tooltip " />
								</div>
							</div>

							<div class="col-lg-6">
								<div class="col-lg-3">
									<label>Fees</label>
								</div>
								<div class="col-lg-9">

									<input type="number" id="anifees" name="anifees"
										class="form-control showToolTip"
										placeholder="Enter Anesthesia Doctor Fees"
										title="Enter Anesthesia Doctor Fees" data-toggle="tooltip " />
								</div>
							</div>

						</div>
						<br>

					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-primary"
							onclick="saveproceduralOtCharge();" data-dismiss="modal">Save</button>
					</div>
				</div>
			</div>
		</div>


		<!-- Print Invoice of Booking with Payment -->

		<div class="modal fade" id="vieworprintnvoice" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Payment Received</h4>
					</div>
					<div class="modal-body">
						<label>Do You Want To Print Invoice?</label>
					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-primary"
							onclick="openprintOpdInvoice()" data-dismiss="modal">Yes</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
					</div>
				</div>
			</div>
		</div>






		
		<!--OTP popup  -->

		<div class="modal fade" id="atppopup" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Enter OTP sent to
							your Email ID/Mobile No</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-3">
								<label>OTP:</label>
							</div>
							<div class="col-lg-9">

								<input type="text" id="otptxt" name="otptxt"
									class="form-control showToolTip"  placeholder="OTP"
									title="OTP" data-toggle="tooltip" />
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<!-- <button type="button" class="btn btn-primary"
							onclick="otpConfirmed()">OK</button> -->
							<button type="button" class="btn btn-primary"
							onclick="otpConfirmed()">Submit</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
<!--OTP popup  -->

		<div class="modal fade" id="atppopup2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Enter OTP sent to
							your Email ID/Mobile No</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<!--  <div class="col-lg-3">
								<div id="timer2">5:00</div>
							</div> -->
							<div class="col-lg-12">

								<input type="text" id="otptxt2" name="otptxt"
									class="form-control showToolTip" placeholder="OTP"
									 data-toggle="tooltip" />
							</div><br><br>
							<div class="col-lg-12">
							<div class="col-lg-6">
							<label>OTP Valid Till:</label>
							</div>
							<div class="col-lg-2" id="timer2">
							5:00
							</div>
							<div class="col-lg-4">
							<button type="button" onclick="confirm()" class="btn btn-success">Resend</button>
							</div>
							</div>
							
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<!-- <button type="button" class="btn btn-primary"
							onclick="otpConfirmed()">OK</button> -->
							<button type="button" class="btn btn-primary"
							onclick="otpConfirmedmob()">Submit</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!--update report status popup  -->


		<div class="modal fade" id="updatereportstatusdiv" tabindex="-1"
			role="dialog" aria-labelledby="lblsendEmailPopUp" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Assesment Report
							Status</h4>
					</div>
					<div class="modal-body">
						<div class="">
							<div class="col-lg-12 col-xs-12 col-md-12"
								style="background-color: #efefef; padding: 15px 0px 0px 0px;">
								<div class="col-lg-6 col-md-6">
									<div class="form-group">
										<label for="email"><%=loginfo.getPatientname_field() %> Name :</label> <span
											id="usclientdetailsid"> </span>
									</div>
								</div>
								<div class="col-lg-3 col-md-3">
									<div class="form-group">
										<label for="email">Child Name :</label> <span>Infant
											(surname) </span>
									</div>

								</div>
								<div class="col-lg-3 col-md-3">
									<div class="form-group">
										<label for="email">DOB :</label> <span>22/10/2011 </span>
									</div>

								</div>
							</div>
							<div class="col-lg-12 col-xs-12 col-md-12"
								style="background-color: #efefef; padding: 0px 0px 0px 0px;">
								<div class="col-lg-6 col-md-6">
									<div class="form-group">
										<label for="email">Treatment Episode Name :</label> <span
											id="ustpsession"> </span>
									</div>
								</div>
								<div class="col-lg-3 col-md-3">
									<div class="form-group">
										<label for="email">Third Party :</label> <span
											id="ustpnamediv"> </span>
									</div>
								</div>
								<div class="col-lg-3 col-md-3">
									<div class="form-group"></div>
								</div>
							</div>
						</div>


						<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
							<h5>Details of Immunisation - Birth to 3 year</h5>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th style="width: 27%;">Plan Name</th>
										<th style="width: 11%;">Time Line</th>
										<th style="width: 11%;">Treatment Episode</th>
										<th style="width: 9%;">Planned Date</th>
										<th>Actual Date</th>
										<th style="width: 35%;">Note</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>B.C.G</td>
										<td>1 <small>1/2</small> months
										</td>
										<td>1</td>
										<td>22/04/2011</td>
										<td>
												<label style="padding-top: 3px;"
											class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
												type="checkbox"  value="1"><i></i></label>
												</td>
										<td><input type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>OPV-1</td>
										<td>1 <small>1/2</small> months
										</td>
										<td>1</td>
										<td>22/04/2011</td>
										<td>
										
												<label style="padding-top: 3px;"
											class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
												type="checkbox"  value="1"><i></i></label>
												</td>
										<td><input type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>DTP-1</td>
										<td>1 <small>1/2</small> months
										</td>
										<td>1</td>
										<td>22/04/2011</td>
										<td>
												<label style="padding-top: 3px;"
											class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
												type="checkbox"  value="1"><i></i></label>
												</td>
										<td><input type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>Hepatities B-1</td>
										<td>1 <small>1/2</small> months
										</td>
										<td>1</td>
										<td>22/04/2011</td>
										<td>
												<label style="padding-top: 3px;"
											class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
												type="checkbox"  value="1"><i></i></label>
												</td>
										<td><input type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>OPV-0*</td>
										<td>2 <small>1/2</small> months
										</td>
										<td>2</td>
										<td>22/06/2011</td>
										<td>
												
												<label style="padding-top: 3px;"
											class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
												type="checkbox" value="1"><i></i></label>
												</td>
										<td><input type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>OPV-2</td>
										<td>2 <small>1/2</small> months
										</td>
										<td>2</td>
										<td>22/06/2011</td>
										<td>
												<label style="padding-top: 3px;"
											class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
												type="checkbox"  value="1"><i></i></label>
												</td>
										<td><input type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>DTP-2</td>
										<td>2 <small>1/2</small> months
										</td>
										<td>2</td>
										<td>22/06/2011</td>
										<td>
												<label style="padding-top: 3px;"
											class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
												type="checkbox"  value="1"><i></i></label>
												</td>
										<td><input type="text" class="form-control"></td>
									</tr>
									<tr>
										<td>Hepatities B-2</td>
										<td>2 <small>1/2</small> months
										</td>
										<td>2</td>
										<td>22/04/2011</td>
										<td>
												<label style="padding-top: 3px;"
											class="checkbox checkbox-custom-alt checkbox-custom-sm m-0 mail-select1"><input
												type="checkbox" value="1"><i></i></label>
												</td>
										<td><input type="text" class="form-control"></td>
									</tr>

								</tbody>
							</table>


						</div>
						<div class="col-lg-12 col-md-12 col-xs-12"
							style="background-color: dimgrey; color: #fff; padding: 5px 0px 5px 0px;">
							<div class="col-lg-2 col-md-2">Date</div>
							<div class="col-lg-3 col-md-3">Appointment Name</div>
							<div class="col-lg-1 col-md-1">Sent</div>
							<div class="col-lg-2 col-md-2">Sent Date</div>
							<div class="col-lg-4 col-md-4">Note (Max 50 Char)</div>
						</div>
						<div class="col-lg-12 co-xs-12 col-md-12 reportstate"
							id="statusrordajaxdiv"
							style="padding: 0px; background-color: rgba(239, 239, 239, 0.18);">

						</div>

					</div>



					<div class="modal-footer">

						<span style="float: left;"><input type="checkbox"
							name="treatmentepisodereportsent" id="treatmentepisodereportsent" />
							Select Checkbox for Report Sent</span>
						<button type="button" class="btn btn-primary"
							onclick="saveAssesmentReportStatus();">Save</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>




		<!--Treatment Episode Counter View  -->


		<div class="modal fade" id="treatmentEpisodeCounterViewPopup"
			tabindex="-1" role="dialog" aria-labelledby="lblsendEmailPopUp"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Treatment Session</h4>
					</div>
					<div class="modal-body" style="height: 500px; overflow: scroll;">
						<div class="row">
							<div class="col-lg-12 col-md-12" id="showtptitlediv">
								Treatment Episode Name</div>
						</div>
						<br>
						<div class="row">
							<div class="col-lg-12 col-md-12" id="showtpcountdiv">Show
								view</div>
						</div>
					</div>



					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>


		<!--Add Consultation Note Model-->
		<div id="addConsultationNote" />
		<div class="modal fade popoverpop" id="" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Consultation Note</h4>
					</div>
					<s:form action="addConsultationNoteEmr" method="post"
						theme="simple" id="addconsultationFrm">

						<div class="modal-body" style="">
							<div class="col-lg-12 topbarback">
								<input type="button" value="SOAP" onclick="setSOS('consNote')"
									class="btn btn-new"> <input type="button"
									value="Initial" onclick="setInitial('consNote')"
									class="btn btn-new"> <input type="button"
									value="Problem" onclick="setProblem('consNote')"
									class="btn btn-new"> <input type="button"
									value="Examination" onclick="setExamination('consNote')"
									class="btn btn-new"> <input type="button"
									value="Assessment" onclick="setAssessment('consNote')"
									class="btn btn-new"> <input type="button"
									value="Treatment" onclick="setTreatment('consNote')"
									class="btn btn-new"> <input type="button" value="Plan"
									onclick="setPlan('consNote')" class="btn btn-new">

								<div style="width: 23%; float: right;">
									<s:select theme="simple" cssStyle="width:22%"
										id="consCondition" name="consCondition" listKey="id"
										onchange="showdignosistxt('consNote')"
										listValue="treatmentName" headerKey="0"
										headerValue="Select Condition / Diagnosis"
										list="treatmentTypeList"
										cssClass="form-control showToolTip chosen"></s:select>
								</div>

							</div>




							<br> <br>
							<div class="row marlft37">
								<div class="col-lg-9 col-md-9">
									<s:textarea cssStyle="height:250px;"
										cssClass="form-control showToolTip"
										placeholder="Enter Note For Todays Appointment"
										data-toggle="tooltip" rows="10" cols="30" name="consNote"
										id="consNote" onmouseup="showOffset()"></s:textarea>
									<s:hidden id="apmtId" name="apmtId" />
								</div>


								<div class="col-lg-3 col-md-3">

									<div class="panel-group managewidhe" id="accordion"
										role="tablist" aria-multiselectable="true">
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingOne">
												<h4 class="panel-title">
													<span> <a role="button" data-toggle="collapse"
														data-parent="#accordion" href="#addcon1"
														aria-expanded="true" aria-controls="addcon1"
														style="color: #fff;"> Prescription </a>
													</span> <a href="#"><i onclick="showopdpriscription()"
														title="Add Prescription"
														class="fa fa-plus-square fa-2x aadpres"></i></a>
												</h4>
											</div>
											<div id="addcon1" class="panel-collapse collapse in"
												role="tabpanel" aria-labelledby="headingOne">
												<div class="panel-body">

													<div id="alldataprisctable" class="row rowblank">
														</table>
													</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingTwo">
												<h4 class="panel-title">
													<a class="collapsed" role="button" data-toggle="collapse"
														data-parent="#accordion" href="#addcon2"
														aria-expanded="false" aria-controls="addcon2"> Symbols
														<small style="color: #fff;">(Drag & Drop)</small>
													</a>
												</h4>
											</div>
											<div id="addcon2" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingTwo">
												<div class="panel-body" style="padding: 5px !important;">
													<img src="emr/img/downarow.png" name="image1" /> <img
														src="emr/img/leftarow.png" name="image2" /> <img
														src="emr/img/linea-arrows-10_e000(0)_48.png" name="image3" />
													<img src="emr/img/linea-arrows-10_e013(1)_48.png"
														name="image4" /> <img src="emr/img/linedownarow.png"
														name="image5" /> <img src="emr/img/linedownarowright.png"
														name="image6" /> <img src="emr/img/linedownbreakleft.png"
														name="image7" /> <img
														src="emr/img/linedownbreakright.png" name="image8" /> <img
														src="emr/img/lineupdownarow.png" name="image9" /> <img
														src="emr/img/rightarow.png" name="image10" /> <img
														src="emr/img/southpoll.png" name="image11" /> <img
														src="emr/img/uparow.png" name="image12" />
												</div>
											</div>
										</div>

										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingfour">
												<h4 class="panel-title">
													<a class="collapsed" role="button" data-toggle="collapse"
														data-parent="#accordion" href="#addcon4"
														aria-expanded="false" aria-controls="addcon4">
														Investigation </a>
												</h4>
											</div>
											<div id="addcon4" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingfour">
												<div class="panel-body"></div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading" role="tab" id="headingfive">
												<h4 class="panel-title">
													<a class="collapsed" role="button" data-toggle="collapse"
														data-parent="#accordion" href="#addcon5"
														aria-expanded="false" aria-controls="addcon5">
														Templates & Forms </a>
												</h4>
											</div>
											<div id="addcon5" class="panel-collapse collapse"
												role="tabpanel" aria-labelledby="headingfive">
												<div class="panel-body"></div>
											</div>
										</div>
									</div>


								</div>
								<br>
								<br>
							</div>



						</div>


						<div class="modal-footer">
							<div id="addconbtnsdiv">

								<input type="button" style="width: 115px; float: left;"
									value="Save" class="btn btn-primary"
									onclick="saveWithoutDischarge()"><br>
								<br>
							</div>
							<div class="row" id="dischargeclientdiv">
								<div class="col-lg-3 col-md-3 marleft80">
									<a href="#" onclick="toggleDischarge()"> Discharge Patient
									</a>
								</div>
							</div>

							<br>
							<div class="wellform" style="display: none;"
								id="toggleDischargediv">
								<div class="row">
									<div class="col-lg-2 col-md-2 col-xs-4">
										<label>Outcome</label>
									</div>
									<div class="col-lg-1 col-md-1 col-xs-1 marleftr">:</div>
									<div class="col-lg-3 col-md-3 col-xs-7 marlft20">
										<s:select cssClass="form-control" name="dischrgeOutcomes"
											id="dischrgeOutcomes" list="dischargeOutcomeList"
											listKey="id" listValue="name" headerKey="0"
											headerValue="Select Outcomes" />
									</div>
								</div>

								<br>

								<div class="row">
									<div class="col-lg-2 col-md-2 col-xs-4">
										<label>Discharge Status</label>
									</div>
									<div class="col-lg-1 col-md-1 col-xs-1 marleftr">:</div>
									<div class="col-lg-3 col-md-3 col-xs-7 marlft20">
										<s:select cssClass="form-control" name="dischargeStatus"
											id="dischargeStatus" list="dischargeStatusList" listKey="id"
											listValue="name" headerKey="0" headerValue="Select Status" />
									</div>
								</div>

								<br>
								<div class="row">
									<div class="col-lg-2 col-md-2 col-xs-4">
										<label>Discharge</label>
									</div>
									<div class="col-lg-1 col-md-1 col-xs-1 marleftr">:</div>
									<div class="col-lg-2 col-md-2 col-xs-7 marlft124">
										<s:checkbox name="chkDischarge" id="chkDischarge"
											checked="true" />
									</div>
								</div>

								<br>
								<div class="row">
									<div class="col-lg-4 col-md-4">

										<button style="width: 100px;" type="button"
											class="btn btn-primary" onclick="saveOnlyConsultationNote()">
											Save</button>
									</div>
								</div>

							</div>

						</div>
					</s:form>
				</div>
			</div>
		</div>

		<%-- </s:form> --%>

		<div class="modal fade" id="puremodifyPopup" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							onclick="movetosetCommonAction()">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							Day-To-Day Admin &nbsp;&nbsp;|&nbsp;&nbsp;<span><a
								href="#" onclick="showLetterHead()" title="Print Letterhead"><i
									class="fa fa-print" aria-hidden="true"></i></a></span>
							&nbsp;&nbsp;|&nbsp;&nbsp;<span id="editSave7"><a href="#"
								style="color: yellow;" onclick="editBMI(7)" title="Edit"><i
									class="fa fa-pencil" aria-hidden="true"></i></a></span>
						</h4>
					</div>
					<div class="modal-body" style="padding: 0px;">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 heightsetbmi">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden">
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
										<div class="form-group marbo10form">
											<label for="exampleInputEmail1">Height <small>cm</small></label>
											<p id="height7"></p>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
										<div class="form-group marbo10form">
											<label for="exampleInputEmail1">Weight <small>Kg's</small></label>
											<p id="weight7"></p>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
										<div class="form-group marbo10form">
											<label for="exampleInputEmail1">BMI</label>
											<p id="bmi7"></p>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
										<div class="form-group marbo10form">
											<label for="exampleInputEmail1">Pulse</label>
											<p id="pulse7"></p>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
										<div class="form-group marbo10form">
											<label for="exampleInputEmail1">Sys-BP</label>
											<p id="sysbp7"></p>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
										<div class="form-group marbo10form">
											<label for="exampleInputEmail1">Dia-BP</label>
											<p id="diabp7"></p>
										</div>
									</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden">
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
										<div class="form-group marbo10form">
											<label for="exampleInputEmail1">Sugar Fasting</label>
											<p id="sugarfasting7"></p>
										</div>
									</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
										<div class="form-group marbo10form">
											<label for="exampleInputEmail1">Post Meal</label>
											<p id="postmeal7"></p>
										</div>
									</div>
										<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Head Circum</label>
										<p id="bsa7"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">Temp</label>
										<p id="temprature7"></p>
									</div>
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
									<div class="form-group marbo10form">
										<label for="exampleInputEmail1">SPO2</label>
										<p id="spo7"></p>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
								<div class="form-group marbo10form">
										<label for="exampleInputEmail1">LMP Date</label>
										<p id="lmpd7"></p>
									</div>
								</div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3"></div>
								</div>

							</div>
						</div>
						<div id="puremodifyClient"
							style="font-size: 16px; margin-bottom: 12px; color: rgb(61, 61, 61);"></div>
						<div class="row">

							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								<div class="thumbnail" id="deleteapmtiddiv123"
									style="cursor: pointer;" onclick="openCancelApmtPopup()">
									<img src="popicons/delete.png" alt="..." class="img-responsive">
									<div class="caption">
										<p align="center" class="dtdf">Cancel Appointment</p>
									</div>
								</div>
								<div class="thumbnail" id="qrcodediv"
									style="cursor: pointer;" onclick="openscanpopup()">
									<img src="popicons/cash_desk.png" alt="..." class="img-responsive">
									<div class="caption">
										<p align="center" class="dtdf">QR Code</p>
									</div>
								</div>
								<div class="thumbnail" id="attchmentdiv"
									style="cursor: pointer;" onclick="openpatientattachment()">
									<img src="cicon/emr.png" alt="..." class="img-responsive">
									<div class="caption">
										<p align="center" class="dtdf">Attachment</p>
									</div>
								</div>
							</div>



						</div>


					</div>



					<div class="modal-footer">

						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal" onclick="movetosetCommonAction()">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Cancel Appointment Note Popup  -->

		<div class="modal fade popoverpop" id="cancelApmtNoteDiv"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">Cancel Appointment Note</h4>
					</div>
					<div class="modal-body">

						<label>Note:</label>
						<textarea id="cancelApmtNote" name="cancelApmtNote"
							class="form-control showToolTip"
							placeholder="Enter Cancellation Note"></textarea>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="deleteNotAviSlot()">Cancel Appointment</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>


		<!--Symbol Popup Model-->
		<div class="modal fade popoverpop" id="symbolPopup" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<i class="fa fa-plus"></i>Select Symbol
						</h4>
					</div>

					<div class="modal-body">
						<div class="row">
							<div id="imagebrowser" onclick="imageAdder();"
								class="col-lg-6 col-md-6">
								<img src="emr/img/downarow.png" name="image1" /> <img
									src="emr/img/leftarow.png" name="image2" /> <img
									src="emr/img/linea-arrows-10_e000(0)_48.png" name="image3" />
								<img src="emr/img/linea-arrows-10_e013(1)_48.png" name="image4" />
								<img src="emr/img/linedownarow.png" name="image5" /> <img
									src="emr/img/linedownarowright.png" name="image6" /> <img
									src="emr/img/linedownbreakleft.png" name="image7" /> <img
									src="emr/img/linedownbreakright.png" name="image8" /> <img
									src="emr/img/lineupdownarow.png" name="image9" /> <img
									src="emr/img/rightarow.png" name="image10" /> <img
									src="emr/img/southpoll.png" name="image11" /> <img
									src="emr/img/uparow.png" name="image12" />
							</div>
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default hidden"
							data-dismiss="modal">Close</button>

					</div>
				</div>
			</div>
		</div>
		<!-- followups reminder Modal -->
		<div class="modal fade popoverpop" id="followupsrminderpopup"
			tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup"
			aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h5 class="modal-title" id="">Followups Reminder</h5>
					</div>
					<div class="modal-body">


						<div class="row">
							<div class="col-lg-2">
								<label>Remind Me : </label>
							</div>
							<div class="col-lg-4">
								<select class="form-control" name="remingmeduration"
									id="remingmeduration">
									<option value="0">Select Duration</option>
									<option value="900000">After 15 Minute</option>
									<option value="3600000">After 1 Hour</option>
									<option value="1800000">After 30 Minute</option>
									<option value="7200000">After 2 Hour</option>
									<option value="14400000">After 2 Hour</option>
									<option value="1">Don't show this message again</option>
								</select>
							</div>
						</div>


					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-primary"
							onclick="saveReminderDuration()">Save</button>



						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Move Appointment -->
		<div class="modal fade popoverpop" id="moveappointmentpopup"
			tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup"
			aria-hidden="true">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							Move / Modify Appointment <span id="modfydiagnosisforid"></span>
						</h4>
					</div>
					<div class="modal-body">



						<div class="col-lg-12">
						<div class="col-lg-2">
								<div class="form-group">
									<label for="email">Select Date</label>
									<s:textfield theme="simple" cssClass="form-control" 
									cssStyle="height: 20px; padding: 3px" id="commencingmve"   name="commencing"></s:textfield>
								</div>
							</div>
							
							<div class="col-lg-6">
								<div class="form-group">
									<label for="email">Select User</label>
								
										
										<s:select cssClass="form-control showToolTip chosen-select"
											id="diaryUsersssmve" name="diaryUser" list="userList" listKey="id"
											listValue="diaryUser" headerKey="0" theme="simple"
											headerValue="Select Practitioner"
											 onchange="setmveappointmenttime(this.value)" />
										<br>
										<br>
										
										
								</div>
							</div>
							
							<div class="col-lg-4 col-md-4 col-xs-12 col-sm-4 hidden-xs">
	<div class="form-group">
			<label for="inputEmail3">Start Time / End Time <a class="red">*</a> / Duration</label>
			<div class="form-inline">
				<div class="form-group">
					 <s:if test="%{#startTimeList != 'null'}">
								<s:select readonly="true" id="sTimemve" name="sTime" list="startTimeList"
									theme="simple" onchange="adjustmovdendtime();"
									cssClass="form-control showToolTip" title="Select Start Time"/>
							</s:if>
			 		 	<label id="sTimemveError" class="text-danger"></label>
				</div>
				<div class="form-group">
						<s:if test="%{#endTimeList != 'null'}">
								<s:select  id="endTimemve" name="endTime" list="endTimeList"
									theme="simple" readonly="true"
									cssClass="form-control showToolTip" title="Select End Time"/>
							</s:if>
				</div>
				<div class="form-group">
				 <s:if test="%{#apmtDurationList != 'null'}">
								<s:select  readonly="true" id="apmtDurationmve" name="apmtDuration"
									list="apmtBlockDurationList" headerKey="0" headerValue="00:00" tabindex="110"
									theme="simple" onchange="adjustmovdendtime()"
									cssClass="form-control showToolTip" title="Select Duration"
									 />
							</s:if>
				 
						
		</div>
		
			</div>
		</div>

</div>


						</div>
<div class="col-lg-12" style="width: 35%">
<label for="email">Modify Charge Type</label>
<div class="form-group" id="tpappointmenttype2">

									<s:select name="duration" id="apmtType12"
									list="appointmentTypeList" listKey="id" listValue="name"
									headerKey="0" headerValue="Charge Type"
									cssClass="form-control showToolTip chosen"
									title="Charge Type" theme ="simple"
									style="width:100%">
									</s:select>
									</div>
</div>



					</div>
					<div class="modal-footer smsbora">
						<input type="button" class="btn btn-primary"
							onclick="updatemveappointment()" value="Move / Modify Appointment" > 
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>


		<!-- modify diagnosis -->
		<div class="modal fade popoverpop" id="modfydiagnosispopup"
			tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup"
			aria-hidden="true">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							Modify Diagnosis for <span id="modfydiagnosisforid"></span>
						</h4>
					</div>
					<div class="modal-body">



						<div class="col-lg-12">
							<div class="col-lg-6"></div>
							<div class="col-lg-6">
								<div class="form-group">
									<label for="email">Condition / Speciality</label>
										
										<input type="text" class="form-control" name="dia" id="dia" placeholder="Search Diagnosis" onkeyup="searchdiagnosisJSON(this.value)">
										<br>
										<br>
										<div class="" style="margin-bottom: 15px;">
											<div class="">
												<div id="scroll">
										<table id="searchDiagnosis" class="table"
										style="height: 200px; display: block; overflow: scroll; width: 100%"; >
										</table>

												</div>

											</div>

										</div>
										<s:hidden id="edtapmtid" name="apmtSlotId"></s:hidden>
										<s:hidden id="edtconid" name="conditionid"></s:hidden>
										<s:hidden id="edtpatientId" name="clientid"></s:hidden>
								</div>
							</div>


						</div>
					</div>
					<div class="modal-footer smsbora">
						<input type="button" class="btn btn-primary"
							onclick="saveDiagnosisofOpd()" value="Save Daignosis" > 
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- create Prescription -->
		<div class="modal fade popoverpop" id="priscriptionpopup"
			tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup"
			aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content" style="margin-left:-100px;margin-right:-100px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h5 class="modal-title" id="priscmyModalLabel">
							Create Prescription For <b class="pname">NAME: Mr.Pratik Raut
								| AGE:34 / Male</b>
						</h5>
					</div>
					<div class="modal-body" style="padding: 0px !important;">
						<jsp:include page="/diarymanagement/pages/addpriscription.jsp" />
					</div>
					<div class="modal-footer" id="hidesaveprint">
						<button type="button" class="btn btn-primary hidden"
							onclick="saveTemplae(0)">Save Template</button>
						<button type="button" class="btn btn-primary"
							onclick="addIpdPrisc(0)" id="prescs_save_btn">Save</button>
							<%if(loginfo.isPrisc_savenprint()){ %>
						<button type="button" class="btn btn-primary"
							onclick="insertEmrPriscription(1)" id="prescs_save_print_btn">Save & Print</button>
						<%} %>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>


		<!-- edit mdcine name popup -->
		<div class="modal fade" id="edtmdcinenamepopupid" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Edit Medicine Name
						</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-3">
								<label>Medicine Name</label>
							</div>
							<div class="col-lg-9">

								<input type="text" id="priscmdcineedit" name="priscmdcineedit"
									class="form-control showToolTip"
									onblur="setPuresevaExistingClientData()"
									placeholder="Enter Medicine Name" title="Enter Medicine Name"
									data-toggle="tooltip " />
							</div>
						</div>
						<br>
						
						<%if(loginfo.getOutoprisc()==1){ %>
						<div class="row">
							<div class="col-lg-3">
								<label>Search Medicine</label>
							</div>
							<div class="col-lg-9">
								
								<s:select cssClass="form-control showToolTip chosen-select" 
								name="mdicinenamesrch" id="mdicinenamesrch" 
								onchange="getsrchdmdicinename(this.value)" 
								list="medicineList" tabindex="1" listKey="id" 
								listValue="genericname" headerKey="0" 
								headerValue="Select Medicine">	</s:select>
							</div>
						</div>
						<br>
						<%} %>


					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-primary"
							onclick="updatemdcinename();" data-dismiss="modal">Save</button>
					</div>
				</div>
			</div>
		</div>


		<!-- create OT Equipment -->
		<div class="modal fade" id="otequipmentpopup" tabindex="-1"
			role="dialog" aria-labelledby="eotlblsemdsmspopup" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h5 class="modal-title" id="eotpriscmyModalLabel">Add OT
							Equipment</h5>
					</div>
					<div class="modal-body" id="otequipmentpopup">
						<jsp:include page="/diarymanagement/pages/addotequipment.jsp" />
					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-primary"
							onclick="saveoteqTemplae(0)">Save Template</button>

						<button type="button" class="btn btn-primary"
							onclick="addoteqdata(0)">Save</button>

						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- add invesgtigation Modal -->
		<div class="modal fade popoverpop" id="investigationpopup"
			tabindex="-1" role="dialog" aria-labelledby="lblsemdsmspopup"
			aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h5 class="modal-title" id="">
							Create Investigation For <b class="pname" id="invstcmyModalLabel">NAME:
								Mr.Pratik Raut | AGE:34 / Male</b>
						</h5>
					</div>
					<div class="modal-body" id="investipopup">


						<jsp:include page="/emr/pages/addInvestigation.jsp" />


					</div>
					 <div class="modal-footer">
						<button style="margin-top: -86px !important;
    position: relative !important;"  type="button" class="btn btn-primary" id='investigationsavebtn'
							onclick="saveIpdInvestigation(0)">Save </button>
						<%if(loginfo.isInvest_savenprint()){ %>
						<button style="margin-top: -86px !important;
    position: relative !important; type="button" class="btn btn-primary"
						onclick="saveIpdInvestigation(1)">Save & Print</button> 
						<%} %>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
					<!-- create btn -->
				</div>
			</div>
		</div>


		<!-- add ipd / opd investigation charge -->
		<input type="hidden" id="addchargehead">
		<div class="modal fade popoverpop" id="addchargepopupipdopdinv"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header bg-primary">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="completeAmtTitle">
							Add Charges for <span id="addinvnewchargehead"></span>
						</h4>
					</div>
					<div class="modal-body">

						<jsp:include page="/ipd/pages/invaddcharge.jsp" />
					</div>

					<s:form action="estimateCharges" theme="simple" id="estimatefrm"
						target="formtarget">
						<s:hidden name="clientId" id="estimateclientid" />
						<s:hidden name="actionType" value="0" />
					</s:form>

					<div class="modal-footer">

						<button type="button" class="btn btn-primary"
							onclick="createinvestigationopdinstantcashdesk()">Cash
							Desk</button>

						<button type="button" class="btn btn-primary"
							onclick="createestimate()">View Estimate</button>


						<button type="button" class="btn btn-primary"
							onclick="createipdopdChargeAndUpdateAccount('Charge')">Create
							Charge</button>

						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>




		<!-- Send sms Details Modal -->
		<div class="modal fade popoverpop" id="semdsmspopup" tabindex="-1"
			role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Send Sms</h4>
					</div>
					<div class="modal-body">

						<div class="col-lg-12 col-md-12 col-xs-12">
							<div class="form-group">
								<label for="exampleInputEmail1"><%=loginfo.getPatientname_field() %></label>
								<s:textfield name="smsuserName" id="smsuserNameletter"
									readonly="true" cssClass="form-control" value="Client" />
								<label id="smsunameError" class="text-danger"></label>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12">
							<div class="form-group">
								<label for="exampleInputEmail1">Select Template</label>
								<s:select onchange="getsmstemplatetxt(this.value)"
									cssClass="form-control showToolTip chosen" name="smstmlate"
									id="smstmlate" list="smsTemplateList" listKey="id"
									listValue="templateName" headerKey="0"
									headerValue="Select Template" />
								<label id="smstmplateError" class="text-danger"></label>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12">
							<div class="form-group">
								<label for="exampleInputEmail1">Mobile No</label>
								<s:textfield name="smsmobile" id="smsmobile"
									cssClass="form-control" />
								<label id="smsmobileError" class="text-danger"></label>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-xs-12">
							<div class="form-group">
								<label for="exampleInputEmail1">Enter Text</label>
								<s:textarea placeholder="Max 160 character" maxlength="160"
									cols="60" rows="5" name="smstxt" id="smstxt"
									cssClass="form-control" />
							</div>
						</div>


					</div>
					<div class="modal-footer smsbora">
						<button type="button" class="btn btn-primary" onclick="sendsms()">Send</button>

						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<s:form action="getPatientRecordEmr" id="getPatientRecord"
			method="post" target="formtarget">

			<s:hidden name="diaryUser" id="diaryUserApmt" />
			<s:hidden id="conditionsApmt" name="conditionsApmt"></s:hidden>
			<s:hidden id="clientnameApmt" name="clientname" />
			<s:hidden name="action" id="hdnaction" />
			<s:hidden name="caldate" id="caldate" />
			<s:hidden name="apmtId" id="emrapmtId" />

		</s:form>


		<s:form action="instantcashAdditional" id="cashdeskfrm1">

			<s:hidden name="clientId" id="cashClientid2" />
			<s:hidden name="thirdParty" id="cashthirdparty2" />
			<s:hidden name="location" id="cashLocationid2" />
			<s:hidden name="client" id="cashclientname2" />
			<s:hidden name="payby" id="cashPayby2"></s:hidden>
			<s:hidden name="creditDebitCharge" id="creditDebitCharge2" value="0" />
			<s:hidden name="appointmentid" id="cashAppointmentid2" />
		</s:form>
		<s:form action="instantcashAdditional" id="cashdeskfrm" method="post"
			target="formtarget">

			<s:hidden name="clientId" id="cashClientid" />
			<s:hidden name="thirdParty" id="cashthirdparty" />
			<s:hidden name="location" id="cashLocationid" />
			<s:hidden name="client" id="cashclientname" />
			<s:hidden name="payby" id="cashPayby"></s:hidden>
			<s:hidden name="creditDebitCharge" id="creditDebitCharge" value="0" />
			<s:hidden name="appointmentid" id="cashAppointmentid" />
		</s:form>
		<s:form action="invstcashAdditional" id="cashdeskfrm2">

			<s:hidden name="clientId" id="icashClientid" />
			<s:hidden name="thirdParty" id="icashthirdparty" />
			<s:hidden name="location" id="icashLocationid" />
			<s:hidden name="client" id="icashclientname" />
			<s:hidden name="payby" id="icashPayby"></s:hidden>
			<s:hidden name="creditDebitCharge" id="icreditDebitCharge" value="0" />
			<s:hidden name="appointmentid" id="icashAppointmentid" />
			<s:hidden name="invoiceid" id="icashinvoiceid" />

		</s:form>

		<s:form action="Finder" id="finderfrm" method="post"
			target="formtarget">
			<s:hidden name="clientId" id="finderClientid" />
		</s:form>

		<!--Upload Model-->
		<div class="modal fade" id="uploaddoc" style="z-index: 9999"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="uploadDocTitle">Upload New
							Document</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-xs-12">
								<s:form id="upload" method="post" action="uploadDocumentsEmr"
									enctype="multipart/form-data" theme="simple">
									<s:hidden name="isvideo" id="isvideo"></s:hidden>

									<div class="form-group">
										<s:select cssClass="form-control fbackwhi" headerKey="0"
											headerValue="Select Type" name="doctType" id="doctType"
											onchange="setemruploaddocAjax(this.value,'doctype')"
											list="{'GP Doc','TP Doc','Medical Record','Consultant Report','Assessment Report','Investigation','Admission Form','Discharge Form','Nursing','Others'}"
											cssStyle="margin-bottom: 10px !important;"></s:select>
									</div>

									<div class="form-group">
										<s:textarea cssClass="form-control fbackwhi"
											onblur="setemruploaddocAjax(this.value,'disc')"
											placeholder="Enter Document Note" rows="3"
											name="documentDesc" id="documentDesc"></s:textarea>
									</div>


									<span id="filename" style="color: white"></span>


									<div id="drop" style="background-color: #efefef;">
										Drop Here <a>Browse</a>
										<s:file name="files" theme="simple">
										</s:file>
									</div>

									<ul class="popmodals123">
										<!-- The file uploads will be shown here -->
									</ul>

								</s:form>
							</div>

						</div>




					</div>
					<div class="modal-footer">
						<s:form action="addDocumentsEmr" method="post" theme="simple">
							<s:hidden id="clientname" value="%{clientname}" name="clientname"></s:hidden>
							<s:hidden id="diaryUser" value="%{diaryUser}" name="diaryUser"></s:hidden>
							<s:hidden id="doccondition" value="%{condition}" name="condition"></s:hidden>
							<s:hidden id="editDoctId" name="editDoctId"></s:hidden>
							<s:hidden id="ipdopdemr" value="2" name="ipdopdemr"></s:hidden>
							<s:hidden id="docapmtId" name="apmtId" />

							<button type="submit" class="btn btn-primary start">Save</button>
						</s:form>

					</div>
				</div>
			</div>
		</div>
		<!--/Upload Model-->






		<!-- adarsh changes -->
		<div id="deletemodel" class="modal fade" role="dialog">
			<div class="modal-dialog modal-sm">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Are You Sure ?</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="parent_id">
						<textarea rows="3" class="form-control" id="delete_reason"
							placeholder="Not Completed Reason"
							style="background-color: beige;"></textarea>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-danger"
							onclick="setworkcompleted(0)" data-dismiss="modal" value="Ok">
					</div>
				</div>

			</div>
		</div>




		<!--Add Consultation Note Model-->
		<div class="modal fade modal-draggable" id="editConsultationNote"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-lg" style="width: 85%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							Update Notes : <span></span> | Date <span>07/04/2017</span>
							<button onclick="updatesaveConsultationNote()" type="button"
								class="btn btn-primary topsave">Save</button>
						</h4>
					</div>


					<div class="modal-body">
						<div class="col-lg-12 col-md-12 col-xs-12" style="padding: 0px;">
							<div class="col-lg-9 col-md-9 col-xs-12 col-sm-12 paddingnil">
								<div class="form-group" style="margin-top: -15px;">
									<img src="cicon/mic_off.png" class="img-responsive micimg"
										onclick="startConverting1(this)" title="Microphone"
										id="changer"></img>
								</div>
								<s:textarea cssStyle="height:450px;"
									cssClass="form-control showToolTip"
									placeholder="Enter Note For Todays Appointment"
									data-toggle="tooltip" rows="20" cols="30" name="consNote"
									id="consNoteopd" onmouseup="showOffset()"></s:textarea>
							</div>
							<div class="col-lg-3 col-md-3 col-xs-12 col-sm-12 aadedf"
								style="padding: 0px;">
								<div class="connotemr">
									<div class="panel-group" id="accordion">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a class="accordion-toggle" data-toggle="collapse"
														data-parent="#accordion" href="#collapseOne"> <i
														class="fa fa-angle-down" aria-hidden="true"></i>&nbsp;Medicine
													</a><i title="Add Prescription"
														class="fa fa-plus-square fa-2x aadpres seticon"></i>
												</h4>
											</div>
											<div id="collapseOne" class="panel-collapse collapse">
												<div class="panel-body">Anim pariatur cliche
													reprehenderit, enim eiusmod high life accusamus terry
													richardson ad squid. 3 wolf moon officia aute, non
													cupidatat skateboard dolor brunch. Food truck quinoa
													nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt
													aliqua put a bird on it squid single-origin coffee nulla
													assumenda shoreditch et. Nihil anim keffiyeh helvetica,
													craft beer labore wes anderson cred nesciunt sapiente ea
													proident. Ad vegan excepteur butcher vice lomo. Leggings
													occaecat craft beer farm-to-table, raw denim aesthetic
													synth nesciunt you probably haven't heard of them accusamus
													labore sustainable VHS.</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a class="accordion-toggle" data-toggle="collapse"
														data-parent="#accordion" href="#collapseTwo"> <i
														class="fa fa-angle-down" aria-hidden="true"></i>&nbsp;Symbols
													</a>
												</h4>
											</div>
											<div id="collapseTwo" class="panel-collapse collapse">
												<div class="panel-body">Anim pariatur cliche
													reprehenderit, enim eiusmod high life accusamus terry
													richardson ad squid. 3 wolf moon officia aute, non
													cupidatat skateboard dolor brunch. Food truck quinoa
													nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt
													aliqua put a bird on it squid single-origin coffee nulla
													assumenda shoreditch et. Nihil anim keffiyeh helvetica,
													craft beer labore wes anderson cred nesciunt sapiente ea
													proident. Ad vegan excepteur butcher vice lomo. Leggings
													occaecat craft beer farm-to-table, raw denim aesthetic
													synth nesciunt you probably haven't heard of them accusamus
													labore sustainable VHS.</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a class="accordion-toggle" data-toggle="collapse"
														data-parent="#accordion" href="#collapseThree"> <i
														class="fa fa-angle-down" aria-hidden="true"></i>&nbsp;Investigation
													</a><i title="Add Investigation"
														class="fa fa-plus-square fa-2x aadpres seticon"></i>
												</h4>
											</div>
											<div id="collapseThree" class="panel-collapse collapse">
												<div class="panel-body">Anim pariatur cliche
													reprehenderit, enim eiusmod high life accusamus terry
													richardson ad squid. 3 wolf moon officia aute, non
													cupidatat skateboard dolor brunch. Food truck quinoa
													nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt
													aliqua put a bird on it squid single-origin coffee nulla
													assumenda shoreditch et. Nihil anim keffiyeh helvetica,
													craft beer labore wes anderson cred nesciunt sapiente ea
													proident. Ad vegan excepteur butcher vice lomo. Leggings
													occaecat craft beer farm-to-table, raw denim aesthetic
													synth nesciunt you probably haven't heard of them accusamus
													labore sustainable VHS.</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a class="accordion-toggle" data-toggle="collapse"
														data-parent="#accordion" href="#collapseFour"> <i
														class="fa fa-angle-down" aria-hidden="true"></i>&nbsp;Templates
														& Forms
													</a>
												</h4>
											</div>
											<div id="collapseFour" class="panel-collapse collapse">
												<div class="panel-body">Anim pariatur cliche
													reprehenderit, enim eiusmod high life accusamus terry
													richardson ad squid. 3 wolf moon officia aute, non
													cupidatat skateboard dolor brunch. Food truck quinoa
													nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt
													aliqua put a bird on it squid single-origin coffee nulla
													assumenda shoreditch et. Nihil anim keffiyeh helvetica,
													craft beer labore wes anderson cred nesciunt sapiente ea
													proident. Ad vegan excepteur butcher vice lomo. Leggings
													occaecat craft beer farm-to-table, raw denim aesthetic
													synth nesciunt you probably haven't heard of them accusamus
													labore sustainable VHS.</div>
											</div>
										</div>
										<div class="six columns">
											<article>
												<label for="search">Conditions List</label>
												<div class="form-inline">
													<div class="form-group">
														<input id="search" class="form-control" name="search"
															placeholder="search" type="text"
															data-list=".default_list" autocomplete="off">
													</div>
													<div class="form-group">
														<button type="submit" class="btn btn-primary"
															style="background-color: #555; color: #fff; height: 24px !important;">Add</button>
													</div>
												</div>

												<ul class="vertical default_list heightsetas">
													<li>C</li>
													<li>Java</li>
													<li>PHP</li>
													<li>JavaScript</li>
													<li>C++</li>
													<li>Python</li>
													<li>Shell</li>
													<li>Ruby</li>
													<li>Objective C</li>
													<li>C#</li>
												</ul>
											</article>
										</div>
									</div>
								</div>

								<div></div>

							</div>
						</div>

					</div>

					<div class="modal-footer">
						<button onclick="updatesaveConsultationNote()" type="button"
							class="btn btn-primary" style="margin-top: 15px;">Save</button>
						<button class="hidden" title="Print"
							onclick="openEmrPopup('printconsnoteEmr?clientid=<s:property value="clientname"/>&diaryuserid=<s:property value="diaryUser"/>&conditionid=<s:property value="condition"/>&action=0');"
							type="button" class="btn btn-primary">
							<i class="fa fa-print fa-2x"></i>
						</button>
					</div>

				</div>
			</div>
		</div>
		
		
		 <!--Vitals  Modal Design By Abhi 16oct2017 -->
	<div id="vitals" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Daily Care for <label id="pnamevital"></label> &nbsp;|&nbsp; <a href="#" title="Edit"><i class="fa fa-pencil" style="color: #fff;"></i></a> &nbsp;|&nbsp; <a href="#" onclick="PrintDiv123();" title="Print"><i class="fa fa-print" style="color: yellow;"></i></a></h4>
	        <input type="hidden" id="vitaltime" value="00.00" />
	      </div>
	      <div class="modal-body" style="padding: 0px !important;background-color: rgba(221, 221, 221, 0.28);">
	      <div class="row">
	      	<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="margin-top: -5px;">
	      		<div role="tabpanel" style="padding: 0px !important;">
	      			<ul class="nav nav-tabs" role="tablist" style="background-color: #fff;">
                        	<li role="presentation" class="active" ><a href="#vitasl123" aria-controls="vitasl123" role="tab" data-toggle="tab" aria-expanded="true">Vitals</a></li>
                            <li role="presentation"><a href="#inout" aria-controls="inout" role="tab" data-toggle="tab" aria-expanded="true">Intake/Output</a></li>
                            <li role="presentation"><a href="#iv" aria-controls="iv" role="tab" data-toggle="tab" aria-expanded="false"> IV</a></li>
                            <li role="presentation"><a href="#equipment" aria-controls="equipment" role="tab" data-toggle="tab" aria-expanded="false"> Equipment</a></li>
                        </ul>
                        <%int k=0; %>
                        <!-- Tab panes -->
                        <div class="tab-content">
                        	<div role="tabpanel" class="tab-pane active" id="vitasl123" style="padding: 0px !important;">
                        		<div class="tile-body">
                        			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="infinity-carousel">
										  <button class="nav prev"></button>
										  <button class="nav next" title="Save & Next"></button>
										  <div class="center" >
										    <div class="slides">
										    
										     <s:iterator value="vitalMasterList">
										      <% if(k==0) { %>
										     <div class="active"> 
										         <%-- <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a> --%>
										         <a href="#"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>)</h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} else { %>
										         <div class=""> 
										         <a href="#"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>)</h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} k++;  %>
										      </s:iterator>
										     <div class="hidden"> 
										         anything in here 2
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										    </div>
										  </div>
										</div>
									<div id="dvContents" class="col-lg-12 col-md-12 col-xs-12 col-sm-12 hidden" style="padding:0px;">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md  visible-print" style="background-color: #fff;border-bottom:1px solid #ddd;">
											<center><h3>Vitals</h3></center>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
												<label>Patient Name:</label>
												<span>Mr. Nitin R Gawande</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Age / Gender:</label>
													<span>28 yr / Male (Self)</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Ward / Bed:</label>
													<span>1F-CASUALTY 135 </span>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
													<label>Date / Time:</label>
													<span>16-10-2017 / 06:34:45</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Heart Beat:</label>
													<span>80 BPM</span>
												</div>
											</div>
										</div>
									
										<div id="vitalgraph" class="collapse">
											<div id="vgraph1" style="min-width: 758px; height: 400px; margin: 0 auto"></div>
										</div>
									</div>
						        </div>
                        		</div>
                        	</div>
                        	<%k=0; %>
                        	<div role="tabpanel" class="tab-pane" id="inout" style="padding: 0px !important;">
                        		<div class="tile-body">
                        		    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="infinity-carousel">
										  <button class="nav prev"></button>
										  <button class="nav next" title="Save & Next"></button>
										  <div class="center" >
										    <div class="slides">
										    
										     <s:iterator value="vitalMasterIOList">
										      <% if(k==0) { %>
										     <div class="active"> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} else { %>
										         <div class=""> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} k++;  %>
										      </s:iterator>
										      
										     <div class="hidden"> 
										         anything in here 2
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										     
										    </div>
										  </div>
										</div>
									<div id="dvContents" class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md  visible-print" style="background-color: #fff;border-bottom:1px solid #ddd;">
											<center><h3>Vitals</h3></center>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
												<label>Patient Name:</label>
												<span>Mr. Nitin R Gawande</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Age / Gender:</label>
													<span>28 yr / Male (Self)</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Ward / Bed:</label>
													<span>1F-CASUALTY 135 </span>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
													<label>Date / Time:</label>
													<span>16-10-2017 / 06:34:45</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Heart Beat:</label>
													<span>80 BPM</span>
												</div>
											</div>
										</div>
									
										<div id="vitalgraph" class="collapse">
											<div id="vgraph1" style="min-width: 758px; height: 400px; margin: 0 auto"></div>
										</div>
									</div>
						        </div>
                        		</div>
                        	</div>
                        	<%k=0; %>
                        	<div role="tabpanel" class="tab-pane" id="iv" style="padding: 0px !important;">
                        		<div class="tile-body">
                        		    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="infinity-carousel">
										  <button class="nav prev"></button>
										  <button class="nav next" title="Save & Next"></button>
										  <div class="center" >
										    <div class="slides">
										    
										     <s:iterator value="vitalMasterIVList">
										      <% if(k==0) { %>
										     <div class="active"> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} else { %>
										         <div class=""> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} k++;  %>
										      </s:iterator>
										      
										     <div class="hidden"> 
										         anything in here 2
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										     
										    </div>
										  </div>
										</div>
									<div id="dvContents" class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md  visible-print" style="background-color: #fff;border-bottom:1px solid #ddd;">
											<center><h3>Vitals</h3></center>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
												<label>Patient Name:</label>
												<span>Mr. Nitin R Gawande</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Age / Gender:</label>
													<span>28 yr / Male (Self)</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Ward / Bed:</label>
													<span>1F-CASUALTY 135 </span>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
													<label>Date / Time:</label>
													<span>16-10-2017 / 06:34:45</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Heart Beat:</label>
													<span>80 BPM</span>
												</div>
											</div>
										</div>
									
										<div id="vitalgraph" class="collapse">
											<div id="vgraph1" style="min-width: 758px; height: 400px; margin: 0 auto"></div>
										</div>
									</div>
						        </div>
                        		</div>
                        	</div>
                        	<%k=0; %>
                        	<div role="tabpanel" class="tab-pane" id="equipment" style="padding: 0px !important;">
                        		<div class="tile-body">
                        		  <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="infinity-carousel">
										  <button class="nav prev"></button>
										  <button class="nav next" title="Save & Next"></button>
										  <div class="center" >
										    <div class="slides">
										    
										     <s:iterator value="vitalMasterEquipmentList">
										      <% if(k==0) { %>
										     <div class="active"> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} else { %>
										         <div class=""> 
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;"><s:property value="name"/> (<s:property value="unit"/>) <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										         <div class="img-wrap text" ><img src="liveData/vital/<s:property value="imagename"/>" alt=""></div>
										        <p class="text"><input type="text" onchange="saveClientFinding(this.value,<s:property value="id"/>)"  class="form-control" style="width: 40%;" /></p>
										      </div> 
										      <%} k++;  %>
										      </s:iterator>
										      
										     <div class="hidden"> 
										         anything in here 2
										         <a href="#" data-toggle="collapse" data-target="#vitalgraph"><h3 class="text" style="font-size: 12px;color: #000;">Blood Pressure <i class="fa fa-angle-down" aria-hidden="true"></i></h3></a>
										        <div class="img-wrap text" ><img src="plugin/slidervitals/images/icon1.png" alt=""></div>
										        <p class="text"><b>100/80</b></p>
										      </div> 
										     
										    </div>
										  </div>
										</div>
									<div id="dvContents" class="col-lg-12 col-md-12 col-xs-12 col-sm-12" style="padding:0px;">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 hidden-lg hidden-md  visible-print" style="background-color: #fff;border-bottom:1px solid #ddd;">
											<center><h3>Vitals</h3></center>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
												<label>Patient Name:</label>
												<span>Mr. Nitin R Gawande</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Age / Gender:</label>
													<span>28 yr / Male (Self)</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Ward / Bed:</label>
													<span>1F-CASUALTY 135 </span>
												</div>
											</div>
											<div class="col-xs-6 col-sm-6" style="padding:0px;">
												<div class="form-group" style="margin-bottom:0px;">
													<label>Date / Time:</label>
													<span>16-10-2017 / 06:34:45</span>
												</div>
												<div class="form-group" style="margin-bottom:0px;">
													<label>Heart Beat:</label>
													<span>80 BPM</span>
												</div>
											</div>
										</div>
									
										<div id="vitalgraph" class="collapse">
											<div id="vgraph1" style="min-width: 758px; height: 400px; margin: 0 auto"></div>
										</div>
									</div>
						        </div>
                        		</div>
                        	</div>
                        </div>
	      		</div>
	      	
	      	</div>
	      
	      	
	      </div>
	      </div>
	      <div class="modal-footer hidden">
	        <button type="button" class="btn btn-primary">Save</button>
	      </div>
	    </div>
	  </div>
	</div>
     <!--Scan Code  -->
		<div class="modal fade" id="scanandpay" tabindex="-1" role="dialog"
	aria-labelledby="lblsendEmailPopUp" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Scan Pay</h4>
			</div>
			<div class="modal-body">
			<s:form theme="simple" action="resetProcessingAccount" id="resetForm">
				<div class="row">
					<div class="col-lg-12">
						<input type="hidden" name="cancelinv" value="1">
						<div class="form-group">
							<img alt="" src="cicon/upiqr.png" class="img-responsive">
						</div>
						 <div class="form-group" style="text-align: center">
						<strong>Pay Amount: &nbsp;<label id="scanamount"></label></strong>
						</div>
						
						
					</div>
					</div>
					</s:form>
				</div>
				
			</div>
		</div>
	</div>
</div>

				<!--OTP popup  -->

		<div class="modal fade" id="atppopup1" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Enter OTP sent to
							your Email ID/Mobile No</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<!-- <div class="col-lg-3">
								
							</div>  -->
							<div class="col-lg-12">

								<input type="text" id="otptxt1" name="otptxt"
									class="form-control showToolTip" placeholder="OTP"
									 data-toggle="tooltip" />
							</div><br><br>
							<div class="col-lg-12">
							<div class="col-lg-6">
							<label>OTP Valid Till:</label>
							</div>
							<div class="col-lg-2" id="timer1">
							5:00
							</div>
							<div class="col-lg-4">
							<button type="button" onclick="confirm1()" class="btn btn-success">Resend</button>
							</div>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<!-- <button type="button" class="btn btn-primary"
							onclick="otpConfirmed()">OK</button> -->
							<button type="button" class="btn btn-primary"
							onclick="otpConfirmedmob1()">OK</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="bookingconfirmpopupchecklist" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Admin Confirmation Popup</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<input type="hidden" id="book_conf_clientid">
							<input type="hidden" id="book_conf_id">
							<div class="col-lg-12">
								<div class="col-lg-10">
									<label>ID Proof Confirmation</label>
								</div>
								<div class="col-lg-2">
									<input type="checkbox" id="book_conf_registration">
								</div>
							</div>
							<div class="col-lg-12">
								<div class="col-lg-10">
									<label>"Medical Record Confirmaion</label>
								</div>
								<div class="col-lg-2">
									<input type="checkbox" id="book_conf_doc_conf">
								</div>
							</div>
							<div class="col-lg-12">
								<div class="col-lg-10">
									<label>Payment Confirmation</label>
								</div>
								<div class="col-lg-2">
									<input type="checkbox" id="book_conf_pay_conf">
								</div>
							</div>
							<div class="col-lg-12">
								<div class="col-lg-10">
									<label>Video Conference Confirmation</label>
								</div>
								<div class="col-lg-2">
									<input type="checkbox" id="book_conf_vid_conf">
								</div>
							</div>
							<div class="col-lg-12">
								<div class="col-lg-10">
									<label>Pending</label>
								</div>
								<div class="col-lg-2">
									<input type="checkbox" id="book_conf_pending">
								</div>
							</div>
							
							<div class="col-lg-12">
								<div class="col-lg-12">
									<label for="comment">Remark</label>
									<textarea class="form-control" rows="3" id="booking_confirm_remark"  style="background-color: rgba(255, 248, 220, 0.48);" placeholder="Write note here"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" class="btn btn-primary"
							onclick="savebookingconfirmchecklist()">Submit</button>
						<button type="button" class="btn btn-primary hidden"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		
				<input type="hidden" id="dept" name="dept">		
		
		<!--   08 feb 2018 -->
<div id="addproocedurepopup" class="modal fade" role="dialog" style="z-index: 11000">
  <div class="modal-dialog modal-sm">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
        <h4 class="modal-title">Add Procedure</h4>
      </div>
      
      <div class="modal-body">
        <div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding-top: 9px;">
        			<div class="form-group">
        			<label>Name</label><label class="text-danger">*</label>
					<s:textfield id="addprocedurename"
						cssClass="showToolTip form-control" data-toggle="tooltip"
						title="Enter Name" placeholder="Enter Name"
						onkeyup="initialCap(this)" />
        			</div>
        	</div>
        </div>
        
        
        
		<div class="">
        	<div class="col-lg-12 col-xs-12 col-md-12" style="padding: 9px;">
        		<div class="form-group">
        			<label>Department</label><label class="text-danger">*</label>
        			<s:select list="disciplineList" listKey="id" headerKey="0" headerValue="Select department" listValue="discipline" name="addproceduredescription" title="select department" cssClass="form-control showToolTip chosen-select"  > </s:select>
        			</div>
        	</div>
        </div> 
     </div>
     
     <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="savenewprocedure()" style="margin-top: 20px;">Save</button>
     </div>
    </div>

  </div>
</div>             
		
		

		<script>
   $(document).ready(function () {
	    $(".disblebtnone").on("click", function() {
	        $(this).attr("disabled", "disabled");
	        doWork();
	    });
	});
   </script>

		<script>
		function toggleChevron(e) {
    $(e.target)
        .prev('.panel-heading')
        .find("i.indicator")
        .toggleClass('glyphicon-chevron-down glyphicon-chevron-up');
}
$('#accordion').on('hidden.bs.collapse', toggleChevron);
$('#accordion').on('shown.bs.collapse', toggleChevron);
	</script>

		<script>
function startConverting1(element) {

   var abc=element.src.split('/');
   
     var right = "cicon/mic_off.png";
         var left = "cicon/mic.png";
         element.src = element.bln ? right : left;
         element.bln = !element.bln;
         
       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
   if(abc[5]=="mic_off.png")
   {
    startConvertingopd();
   }
   else{
   //var textvalue=document.getElementById("otnotes").value;
  // localStorage.setItem("xx",textvalue);
   //location.reload();
   }
   
   

       
         
     }
</script>
		<script>
			
		
	$(".modal-draggable .modal-dialog").draggable({
        handle: ".modal-header"
    });
    $(function() {
  $('#cdna').slimScroll({
        height: '450px',
        railVisible: true,
		alwaysVisible: true
  });
   $('#scrollpan').slimScroll({
        height: '250px',
        railVisible: true,
		alwaysVisible: true
  });
  $('.emailbodas').slimScroll({
        height: '450px',
        railVisible: true,
		alwaysVisible: true
  });
   $('.connotemr').slimScroll({
        height: '450px',
        railVisible: true,
		alwaysVisible: true
  });
  $('.reportstate').slimScroll({
        height: '390px',
        railVisible: true,
		alwaysVisible: true
  });
  $('.heightsetas').slimScroll({
        height: '218px',
        railVisible: true,
		alwaysVisible: true
  });
  $('.addnewpat').slimScroll({
        height: '500px',
        railVisible: true,
		alwaysVisible: true
  });
  
 });
 
    
 
 /*Modal Dropdown Choosen Call*/						
      $('#modfydiagnosispopup').on('shown.bs.modal', function () {
	  $('.chosen-select', this).chosen();
	});
 
      var followupbtn= document.getElementById("followuppop");
      var followupmodal= document.getElementById("followupmodal");
      var followclose= document.getElementById("followclose");
      followupbtn.onclick = function() {
      	followupmodal.style.display = "block";
      	$("#lokeshfollowdatenew").datepicker({

      		dateFormat : 'dd-mm-yy',
      		yearRange: yearrange,
      		
      		changeMonth : true,
      		changeYear : true,
      		minDate:'0',
      	});
      }

      function openfollowpop(){
    	  document.getElementById("followupmodal").style.display = "block";
          	$("#lokeshfollowdatenew").datepicker({

          		dateFormat : 'dd-mm-yy',
          		yearRange: yearrange,
          		
          		changeMonth : true,
          		changeYear : true,
          		minDate:'0',
          	});
      }
      
     
      function followupclose(){
    	  document.getElementById("followupmodal").style.display = "none";
      }
      followclose.onclick = function() {
      	followupmodal.style.display = "none";
      }


      window.onclick = function(event) {
          if (event.target == followupmodal) {
          	followupmodal.style.display = "none";
          }
      }

      
      function closeipd(){
    	  document.getElementById("toipd").style.display = "none";
      }

	</script>
	<script type="text/javascript">
	/* $(document).ready(function () {
	    $("#savePatientNow").on("click", function() {
	    	 $("#savePatientNow").text("Please Wait");
	        $(this).attr("disabled", "disabled");
	        setTimeout('$("#savePatientNow").removeAttr("disabled")', 5000);
	        setTimeout('$("#savePatientNow").text("Save")', 5000);
	        });
	}); */
	
	
	function newcall(){
		/* $("#savePatientNow").text("Please Wait");
        $(this).attr("disabled", "disabled"); */
        document.getElementById("savePatientNow").disabled = true;
		var x= savePatient();
		if(x==false){
			/* $("#savePatientNow").removeAttr("disabled");
			$("#savePatientNow").text("Save"); */
			document.getElementById("savePatientNow").disabled = false;
		}
		return x;
		
	}
	
	</script>
</body>


</html>


<s:if test="(#session.LOGIN_INFO != null)&&(#session.LOGGED_IN)">
<%String openedbb = (String)session.getAttribute("openedb"); if(!openedbb.equals("otdb")){%>
	<jsp:include page="/application.jsp" />
	
<%} %>

</s:if>
