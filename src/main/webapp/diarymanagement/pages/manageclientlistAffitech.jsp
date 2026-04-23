<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.apm.common.utils.DateTimeUtils"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%--  <script type="text/javascript"
	src="diarymanagement/js/nonavailableslot.js"></script>  --%>
<%@page import="com.apm.common.web.common.helper.LoginInfo"%>
<%@page import="com.apm.common.web.common.helper.LoginHelper"%>
<link href="common/css/Style.css" rel="stylesheet" type="text/css" />
<link href="common/css/responsive.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="diarymanagement/js/manageclient.js"></script>
<script type="text/javascript" src="diarymanagement/js/otnotes.js"></script>
<%-- <script type="text/javascript" src="diarymanagement/js/addPatientTab.js"></script> --%>

<script type="text/javascript" src="accounts/js/commonaddcharge.js"></script>
<script type="text/javascript" src="emr/js/addInvestigation.js"></script>
<script type="text/javascript" src="emr/js/viewinvestigation.js"></script>
<script type="text/javascript" src="diarymanagement/js/sendsms.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/sendApmtAttachnment.js"></script>
<script type="text/javascript" src="common/js/pagination.js"></script>
<script type="text/javascript" src="thirdParties/js/nicEdit.js"></script>


<script type="text/javascript" src="tools/js/sendLetter.js"></script>
<script type="text/javascript"
	src="diarymanagement/js/newinvestigationclient.js"></script>
	<script type="text/javascript"
	src="diarymanagement/js/notavailableslotpopupscript.js"></script>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript"
	src="assesmentForms/js/jquery.table2excel.js"></script>


<%
LoginInfo loginfo = LoginHelper.getLoginInfo(request);
%>

<script>
  function voicerecord() {
	  $('#miic').modal( "show" );	
}
  
      function startConverting155(element,id) {

		   var abc=element.src.split('/');	
		   
		     var right = "cicon/mic_off.png";
		         var left = "cicon/mic.png";
		         element.src = element.bln ? right : left;
		         element.bln = !element.bln;
		         
		       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
		   if(abc[5]=="mic_off.png")
		   {
		    startConvertingadvicepres45(id);
		   }
		   else{
		   //var textvalue=document.getElementById("otnotes").value;
		  // localStorage.setItem("xx",textvalue);
		   
		   }
		     }
    
	
	</script>
<script type="text/javascript">
 
        // Load the Google Transliterate API
        google.load("elements", "1", {
            packages: "transliteration"
        });
 
        function onLoad() {
            var options = {
                sourceLanguage:
                google.elements.transliteration.LanguageCode.ENGLISH,
                destinationLanguage:
                [google.elements.transliteration.LanguageCode.HINDI],
                transliterationEnabled: true
            };
 
            // Create an instance on TransliterationControl with the required
            // options.
            var control =
            new google.elements.transliteration.TransliterationControl(options);
 
            // Enable transliteration in the textbox with id
            // 'transliterateTextarea'.
            control.makeTransliteratable(['voicemic']);
        }
        google.setOnLoadCallback(onLoad);
    </script>
<script>
  function startConvertingadvicepres45(id){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts  + interimtranscripts ;
		
		//var con = nicEditors.findEditor('adharsearch').getContent() + vtxt;
		nicEditors.findEditor(''+id).setContent(vtxt);
		//document.getElementById('voicemic').value=vtxt;
		
		
		
	};

}
  
  </script>
<script type="text/javascript">
			function startConvertingadvicepres(){
				var recognition = new webkitSpeechRecognition();
				recognition.continuous = true;
				recognition.interimResults = true;
				recognition.lang = "en-IN";
				recognition.start();

				var finalTranscripts = '';
				recognition.onresult = function(event){
					var interimtranscripts = '';
					for(var i=event.resultIndex;i<event.results.length;i++){
						var transcript = event.results[i][0].transcript;
						transcript.replace("/n","</br>");
						
						if(event.results[i].isFinal){
							finalTranscripts += transcript;
						}else{
							interimtranscripts += transcript;
						}
					}
					var vtxt  = finalTranscripts  + interimtranscripts ;
					
					//var con = nicEditors.findEditor('adharsearch').getContent() + vtxt;
				//	nicEditors.findEditor('adharsearch').setContent(vtxt);
					document.getElementById('voicemic').value=vtxt;
					
					
					
				};

			}

			</script>



<style>
.disabled {
	z-index: 1000 !important;
	background-color: lightgrey !important;
	opacity: 0.6 !important;
	pointer-events: none !important;
}

.thumbnail {
	height: 82px !important;
}

.micimg {
	float: left;
	width: 7%;
}
/* td {
	font-size: small !important;
} */

}
</style>
<script>
var patientId = 0;
var diaryuserId = 0;
var editcondition_id = 0;
var editAppointId = 0;

</script>
<script>
   $(document).ready(function () {
	    $(".disblebtnone").on("click", function() {
	        $(this).attr("disabled", "disabled");
	        doWork();
	    });
	    
	    $(".editadmndate").datepicker({

    		dateFormat : 'dd-mm-yy',
    		yearRange: yearrange,
    		minDate : '30-12-1880',
    		changeMonth : true,
    		changeYear : true
    	});
	   
	    $(".checkindate").datepicker({

    		dateFormat : 'dd-mm-yyyy',
    		yearRange: yearrange,
    		minDate : '30-12-1880',
    		changeMonth : true,
    		changeYear : true
    	});
	});
   </script>




<script>

bkLib.onDomLoaded(function() {
   
	 
	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 500}).panelInstance('remark');
	 new nicEditor({buttonList : ['fontSize','bold','italic','underline','strikeThrough','subscript','superscript','html','image'],maxHeight : 250}).panelInstance('emailBodyLetter');

	 $('.nicEdit-panelContain').parent().width('auto');
	 $('.nicEdit-panelContain').parent().next().width('auto');
	 
	 $('.nicEdit-panelContain').parent().height('auto');
	 $('.nicEdit-panelContain').parent().next().height('auto');
	 
	 $('.nicEdit-main').width('98%');
	 /* $('.nicEdit-main').height('100%'); */
	 
	// $('.nicEdit-main').min-height('100px');
});



function startConverting1(element) {

   var abc=element.src.split('/');
   
     var right = "cicon/mic_off.png";
         var left = "cicon/mic.png";
         element.src = element.bln ? right : left;
         element.bln = !element.bln;
         
       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
   if(abc[5]=="mic_off.png")
   {
    startConvertingsearchp();
   }
   else{
   //var textvalue=document.getElementById("otnotes").value;
  // localStorage.setItem("xx",textvalue);
   location.reload();
   }
}

function startConvertingforname(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertingsearchpforname();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   //location.reload();
	   }
	}
function startConvertingformobno(element) {

	   var abc=element.src.split('/');
	   
	     var right = "cicon/mic_off.png";
	         var left = "cicon/mic.png";
	         element.src = element.bln ? right : left;
	         element.bln = !element.bln;
	         
	       //  document.getElementById("otnotes").value=localStorage.getItem("xx");
	   if(abc[5]=="mic_off.png")
	   {
	    startConvertingsearchpformobno();
	   }
	   else{
	   //var textvalue=document.getElementById("otnotes").value;
	  // localStorage.setItem("xx",textvalue);
	   //location.reload();
	   }
	}
function searchbyipd(){
	document.getElementById('clientFrm').submit();
}

function printExcel() {

    $(".xlstable").table2excel({
					exclude: ".noExl",
					name: "Patient List",
					filename: "Patient_List",
					fileext: ".xls",
					exclude_img: true,
					exclude_links: true,
					exclude_inputs: true
				});
}

</script>

<style type="text/css">
.manascommheader {
	padding: 6px;
	font-size: 20px;
	text-transform: uppercase;
	display: flex;
	border-bottom: 2px solid #10606f;
	background: #43b9be;
}

.manascommheader h4, h3 {
	font-weight: bold;
	font-size: 17px;
	font-family: 'Open Sans', sans-serif !important;
	color: white;
}

.manascommheader img {
	margin-right: 6px !important;
}

.manastableheader {
	background-color: #95d2de !important;
	color: black !important;
}

.btn-primary {
	background-color: #15536E !important;
	border-radius: 0.75rem;
}
.notification-area .dropdown-menu.show,
.user-profile .dropdown-menu.show {
    top: 100%!important;
    visibility: visible;
    opacity: 1;
    box-shadow: 0 0 45px 0 rgba(131, 23, 254, 0.06);
}
</style>
	<s:form action="Statement" id="view_acc_frm" method="post"
										onsubmit="return redirectToViewAcc1()" target="formtarget">

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
										<div class="thumbnail hidden" id="sendLetter"
											onclick="redirectToViewAcc1()" style="cursor: pointer;">
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

<div class="">
	<div class="">
		<div class="row details">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 manascommheader">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="display: -webkit-inline-box; padding: 0px;">
					<img src="manasmaindashboard/images/Patientes_Icon.svg"
						style="filter: brightness(5); margin-left: 7px;">
					<h4>Customer Details</h4>
				</div>
				 <div class="dropdown" style="padding-right: 131px;">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								CRM <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">




								<s:if test="mainaction=='marketingdashboard'">
									<li role="presentation" class="active"><a
										href="#dashboard" aria-controls="dashboard" role="tab"
										data-toggle="tab" aria-expanded="true">Dashboard</a></li>
								</s:if>
								<s:else>
									<li role="presentation"><a href="#dashboard"
										aria-controls="dashboard" role="tab" data-toggle="tab"
										aria-expanded="true">Dashboard</a></li>
								</s:else>

								<s:if test="mainaction=='marketing_connect'">
									<li role="presentation" class="active"><a href="#email"
										aria-controls="email" role="tab" data-toggle="tab"
										aria-expanded="true">Connect</a></li>
								</s:if>
								<s:else>
									<li role="presentation"><a href="#email"
										aria-controls="email" role="tab" data-toggle="tab"
										aria-expanded="true">Connect</a></li>
								</s:else>

								<s:if test="mainaction=='marketing_history'">
									<li role="presentation" class="active"><a href="#history"
										aria-controls="history" role="tab" data-toggle="tab"
										aria-expanded="false"> History</a></li>
								</s:if>
								<s:else>
									<li role="presentation"><a href="#history"
										aria-controls="history" role="tab" data-toggle="tab"
										aria-expanded="false"> History</a></li>
								</s:else>
								<li role="presentation" class="hidden"><a href="#bde"
									aria-controls="bde" role="tab" data-toggle="tab"
									aria-expanded="false"> BDE</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('followupdashboardClient')">Follow Up</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('diagnosisdashboardInitialDischarge')">Diagnosis
										Dashboard</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('addCompleteInfoforAfitechRegistration')">Add</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('manageClient')">Show Record</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('targetDiaryMangent')">Show Target</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('targetDiaryMangent')">Show Test</a></li>

								<s:form class="form-inline" action="marketingDiaryMangent"
									id="mischartfrm" theme="simple" style="display:inline-flex;">
									<s:hidden name="action" id="action"></s:hidden>
									<s:hidden name="mainaction" value="marketingdashboard"></s:hidden>
								</s:form>

							</ul>
						</div> 
				<%-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 oneseticonleft">
											<!-- <img src="dashboardicon/clints.png" class="img-responsive prescripiconcircle"> -->
											<img src="manasmaindashboard/images/Patientes_Icon.svg" style="filter: brightness(5);width: 21%;margin-top: 5px;margin-left: 7px;">
										</div>
										<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 titlestleftiocn">
											<h4><%=loginfo.getPatientname_field() %> Panel</h4>
										</div> --%>
							<div class="navbar-nav">
        <a class="btn btn-primary" href="logoutwithsessionLogout">
          <i class="fa-solid fa-right-from-bracket"  style='font-size:20px'></i>&nbsp;Logout
        </a>
      </div>
                             
			</div>
		</div>
		<div class="row ">
			<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
				<div>
					<div class="row">
						<div class="col-lg-12">
							<s:hidden name="message" id="message"></s:hidden>
							<s:if test="hasActionMessages()">
								<script>
		var msg = " " + document.getElementById('message').value;
		showGrowl('', msg, 'success', 'fa fa-check');
		</script>
							</s:if>
						</div>
					</div>






					<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12 clintbot"
						style="padding-top: 18px;">

						<s:form action="searchClient" theme="simple" id="clientFrm">
							<input type="hidden" id="commencing">
							<s:hidden name="message" id="message"></s:hidden>
							<input type="hidden" id="client" name="client">
							<input type="hidden" id="clientId" name="clientId">


							<div class="col-lg-2 col-md-3 col-sm-3">
								<div class="input-group">
									<s:textfield theme="simple" name="searchText" id="adharsearch"
										cssStyle=""
										placeholder="Search by Addhar No, Name, Surname, Pincode"
										cssClass="form-control" />
									<span class="input-group-btn"> 
									<%
                                     if (loginfo.isLmh()) {
                                       %> <a
										type="button" style="margin-left: 5px" id="btnxls"
										title="Save As XLS" onclick="printExcel()"
										class="btn btn-primary"><i class="fa fa-file-excel-o"></i></a>
										<%
										}
										%>
									</span>
								</div>
                                 <!--  <img src="cicon/mic_off.png" class="img-responsive micimg"
									onclick="startConverting1(this)" style="width: 28px;" title="Microphone"
									id="changer"></img> &nbsp; Search By Voice Over -->
							</div>

							<div class="col-xs-1 col-md-1">
								<input type="submit" value="Go" class="btn btn-primary" /> <a
									href="#" class="btn btn-primary"><i class="fa fa-refresh"
									aria-hidden="true"></i></a>
							</div>

							<div class="col-lg-2 col-md-2 col-sm-2 hidden">
								<s:select cssClass="form-control showToolTip chosen"
									data-toggle="tooltip" id="clientType" name="clientType"
									list="ClientTypeList" onchange="showByStatus()"
									listKey="clientType" listValue="clientType" headerKey="0"
									theme="simple" headerValue="Select Client Type" />
								<br> <label id="clientTypeError" class="text-danger"></label>
							</div>


							<div class="col-lg-2 col-md-2 col-sm-2 hidden">
								<s:select name="status" id="patientstatus12"
									list="#{'0':'All','Follow Up':'Follow Up','Done':'Done','Predicate':'Predicate','Hot':'Hot','Cold':'Cold','Wam':'Wam'}"
									cssClass="form-control" onchange="showByStatus()"></s:select>
							</div>
							
							<%-- div class="col-lg-2 col-md-2 col-sm-2 hidden" style="width: 20%">
				<s:select name="days" id="days" 
				list="#{'0':'Select Days','1':'0 to 30 Days','2':'30 to 60 Days','3':'60 to 90 Days'}"
				cssClass="form-control chosen-select" onchange="showByStatus()"></s:select>
				
			</div> --%>
			<div class="col-lg-2 col-md-2 col-sm-2" style="width: 20%">
<div class="form-group">
					<div class="col-lg-12 col-md-12">
						<a href="#" data-toggle="modal" onclick="bookingpopup('<s:property value="checkindate"/>')"
							class="btn btn-primary" title="New Charges">Book
							Customer</a>
					</div>
				</div>
				</div>

							<%-- <div class="col-lg-2 col-md-3 col-sm-3" style="margin-top: 9px;">
								<img src="cicon/mic_off.png" class="img-responsive micimg"
									onclick="startConverting1(this)" title="Microphone"
									id="changer"></img> &nbsp; Search By Voice Over
								| &nbsp; <s:checkbox  name="showAll" id="showAll" onclick="sortByPractitioner(this.value)" title="Show all booked and non booked Appointment client list"/>
								<span>Select to view all Clients.</span>


							</div>
 --%>
						</s:form>

						<%-- <div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								Menu <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">




								<s:if test="mainaction=='marketingdashboard'">
									<li role="presentation" class="active"><a
										href="#dashboard" aria-controls="dashboard" role="tab"
										data-toggle="tab" aria-expanded="true">Dashboard</a></li>
								</s:if>
								<s:else>
									<li role="presentation"><a href="#dashboard"
										aria-controls="dashboard" role="tab" data-toggle="tab"
										aria-expanded="true">Dashboard</a></li>
								</s:else>

								<s:if test="mainaction=='marketing_connect'">
									<li role="presentation" class="active"><a href="#email"
										aria-controls="email" role="tab" data-toggle="tab"
										aria-expanded="true">Connect</a></li>
								</s:if>
								<s:else>
									<li role="presentation"><a href="#email"
										aria-controls="email" role="tab" data-toggle="tab"
										aria-expanded="true">Connect</a></li>
								</s:else>

								<s:if test="mainaction=='marketing_history'">
									<li role="presentation" class="active"><a href="#history"
										aria-controls="history" role="tab" data-toggle="tab"
										aria-expanded="false"> History</a></li>
								</s:if>
								<s:else>
									<li role="presentation"><a href="#history"
										aria-controls="history" role="tab" data-toggle="tab"
										aria-expanded="false"> History</a></li>
								</s:else>
								<li role="presentation" class="hidden"><a href="#bde"
									aria-controls="bde" role="tab" data-toggle="tab"
									aria-expanded="false"> BDE</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('followupdashboardClient')">Follow Up</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('diagnosisdashboardInitialDischarge')">Diagnosis
										Dashboard</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('addCompleteInfoforAfitechRegistration')">Add</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('manageClient')">Show Record</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('targetDiaryMangent')">Show Target</a></li>
								<li role="presentation"><a href="#" role="tab"
									onclick="openPopup('targetDiaryMangent')">Show Test</a></li>

								<s:form class="form-inline" action="marketingDiaryMangent"
									id="mischartfrm" theme="simple" style="display:inline-flex;">
									<s:hidden name="action" id="action"></s:hidden>
									<s:hidden name="mainaction" value="marketingdashboard"></s:hidden>
								</s:form>

							</ul>
						</div> --%>
					</div>


				</div>
				
				<s:form action="saveclientdetailsClient" theme="simple"
					name="clientfrm" id="clientfrm" enctype="multipart/form-data">
					
					
					
<%-- 					<div class="col-lg-12 col-md-12 ">
						<div class="col-lg-2 col-md-2">




							<div class="col-lg-12 col-md-12" style="margin-top: 10px">
								<label>Name</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-12 col-md-12">
								<s:textfield id="firstNameaff" name="firstName" theme="simple"
									cssClass="form-control showToolTip ajsolidborder"
									list="pdl_firstname"
									onkeyup="getpatientfordatalist(this.value,'pdl_firstname','firstname')"
									onblur="initialFirstCap(this);" data-toggle="tooltip"
									placeholder="Enter First Name" cssStyle="width:100%;" />
								<datalist id="pdl_firstname">
								</datalist>
								<img src="cicon/mic_off.png" style="width: 25px;"
									onclick="startConvertingforname(this)" title="Microphone"
									id="firstName1"></img> &nbsp; Search By Voice Over &nbsp; <label
									id="firstNameError" class="text-danger"></label>
							</div>
						</div>

						<div class="col-lg-2 col-md-2">
							<div class="col-lg-12 col-md-12" style="margin-top: 10px">
								<label>Mob no</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-12 col-md-12">
								<s:textfield id="mobNoaff" name="mobNo"
									cssClass="form-control showToolTip ajsolidborder"
									data-toggle="tooltip" list="pdl_mobNo"
									onkeyup="getpatientfordatalist(this.value,'pdl_mobNo','mobno')"
									placeholder="Enter Mobile No." maxlength="10" />
								<label id="firstNameError" class="text-danger"></label> <img
									src="cicon/mic_off.png" style="width: 25px;"
									onclick="startConvertingformobno(this)" title="Microphone"
									id="firstName12"></img> &nbsp; Search By Voice Over &nbsp; <label
									id="firstNameError" class="text-danger"></label>
							</div>
						</div>

						<div class="col-lg-2 col-md-2">
							<div class="col-lg-12 col-md-12" style="margin-top: 10px">
								<label>Mail Id</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-12 col-md-12">
								<s:textfield id="email" name="email"
									cssClass="form-control showToolTip ajsolidborder"
									data-toggle="tooltip" placeholder="Enter Email." />
								<label id="emailError" class="text-danger"></label>

							</div>
						</div>

						<div class="col-lg-2 col-md-2">
							<div class="col-lg-12 col-md-12" style="margin-top: 10px">
								<label>City</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-12 col-md-12" id="citydiv">
								<s:select list="citylist" listKey="city" listValue="city"
									id="town" onchange="getStateAjax1(this.value)"
									cssClass="form-control showToolTip chosen-select" headerKey="0"
									headerValue="Select City" name="town" />
								<label id="townError" class="text-danger hidden"></label>

							</div>
						</div>

						<div class="col-lg-2 col-md-2">
							<div class="col-lg-12 col-md-12" style="margin-top: 10px">
								<label>State</label><label><span class="text-danger">*</span></label>
							</div>
							<div class="col-lg-12 col-md-12" id="statediv">
								<s:select list="statelist"
									cssClass="form-control showToolTip chosen-select"
									onchange="getCitiesajax(this.value)" name="county" id="county"
									listKey="name" listValue="name" headerKey="0"
									headerValue="Select State" />
								<label id="firstNameError" class="text-danger"></label>
							</div>
						</div>

						<div class="col-lg-2 col-md-2">
							<div class="col-lg-12 col-md-12" style="margin-top: 10px">
								<label>Client Type</label><label><span
									class="text-danger">*</span></label>
							</div>
							<div class="col-lg-12 col-md-12" id="statediv" style="margin-bottom: 20px;">
								<s:select cssClass="form-control showToolTip chosen"
									data-toggle="tooltip" id="clientType" name="clientType"
									list="ClientTypeList" listKey="clientType"
									listValue="clientType" headerKey="0" theme="simple"
									headerValue="Select Client Type" />
								<br> <label id="clientTypeError" class="text-danger"></label>
								<label id="firstNameError" class="text-danger"></label>
							</div>
						</div>
					</div>

					<div style="margin-left: 50px; margin-bottom: 14px;">
						<s:submit value="Save"
							onclick="return validateAllDetailsaffitech()"
							cssClass="btn btn-primary" />
					</div> --%>
				</s:form>



				<div class="modal fade modal-draggable" id="bookcustomerhotel"
					tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog modal-sm" role="document">
						<div class="modal-content ">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h3 class="modal-title" id="myModalLabel">Book Customer</h3>
							</div>
							<div class="modal-body" id="">
								<div class="col-lg-12 col-md-12" id="">
									<div class="row">

										<div class="form-group">
											<label>Check-In</label> <input type="text" name="checkindate"
												id="checkindate" class="form-control editadmndate" style=""
												readonly>  
												<label for="exampleInputName2">HH :</label>

											<s:select name="hour1" cssStyle="width: 30%" id="hour1"
												list="hourList" cssClass="form-control" headerKey="0"
												headerValue="Select" />

											<label for="exampleInputName2">MM :</label>

											<s:select name="minute1" cssStyle="width: 30%" id="minute1"
												list="minuteList" cssClass="form-control " headerKey="0"
												headerValue="Select" />
										</div>
										<div class="form-group">
											<label>Days</label>
											<input type="text" id="days" name="days" class="form-control">
										</div>
										<div class="form-group">
											<label>Customer Name</label>
											<s:textfield id="firstNameaff" name="firstName"
												theme="simple"
												cssClass="form-control showToolTip ajsolidborder"
												list="pdl_firstname"
												onkeyup="getpatientfordatalist(this.value,'pdl_firstname','firstname')"
												onblur="initialFirstCap(this);" data-toggle="tooltip"
												placeholder="Enter First Name" cssStyle="width:100%;" />
											<label id="firstNameError" class="text-danger"></label>
										</div>
										<div class="form-group">
											<label>Mob no</label><label><span class="text-danger">*</span></label>
											<s:textfield id="mobNoaff" name="mobNo"
												cssClass="form-control showToolTip ajsolidborder"
												data-toggle="tooltip" list="pdl_mobNo"
												onkeyup="getpatientfordatalist(this.value,'pdl_mobNo','mobno')"
												placeholder="Enter Mobile No." maxlength="10" />
										</div>
										<div class="form-group">
											<label>Email Id</label><label><span
												class="text-danger">*</span></label>
											<s:textfield id="email" name="email"
												cssClass="form-control showToolTip ajsolidborder"
												data-toggle="tooltip" placeholder="Enter Email." />
										</div>
										<div class="form-group">
											<label>City</label><label><span class="text-danger">*</span></label>
											<s:select list="citylist" listKey="city" listValue="city"
												id="town" onchange="getStateAjax1(this.value)"
												cssClass="form-control showToolTip chosen-select"
												headerKey="0" headerValue="Select City" name="town" />
										</div>
										<div class="form-group">

											<label>State</label><label><span class="text-danger">*</span></label>
											<s:select list="statelist"
												cssClass="form-control showToolTip chosen-select"
												onchange="getCitiesajax(this.value)" name="county"
												id="county" listKey="name" listValue="name" headerKey="0"
												headerValue="Select State" />

										</div>

										<div style="margin-left: 50px; margin-bottom: 14px;"></div>
									</div>
								</div>
							</div>
							<div class="modal-footer ">
								<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
								<button type="button" class="btn btn-primary"
									onclick="saveCustomerDetails()">Save</button>
							</div>
						</div>
					</div>
				</div>

				<div class="">



					<input type="hidden" id="emaorwhatsup" name="fileUploadd" />
					<table
						class="table table-hover table-condensed table-bordered xlstable">
						<thead>

							<tr>



								<%
								if (loginfo.isLmh()) {
								%>
								<th class="manastableheader">Patient</th>
								<%
								}
								%>
								<th id="hideLink"
									class="manastableheader sortable <s:if test="#attr.pagination.sortColumn.equals('firstname')">sorted <s:property value="#attr.pagination.sortClass"/> </s:if>">
									Name <a href="#"
									onclick="hideRow('<s:property value = "allPatientList.size"/>')"
									style="color: yellow; display: none;">Hide</a>
								</th>
								<th class="manastableheader">State(Town)</th>
								<th class="manastableheader">Mobile No</th>
								<th class="manastableheader">Email</th>
								<th class="manastableheader noExl">Last Modified</th>
								
								
								<th class="manastableheader noExl">Days</th>
								<th class="manastableheader noExl">Status</th>
								<!-- <th class="manastableheader noExl">mail send date</th>
								<th class="manastableheader noExl">Whats send date</th> -->
								<th class="manastableheader noExl ">Edit</th>
								<!-- <th class="manastableheader noExl">Add Remark</th> -->
								<!-- <th class="manastableheader noExl">View Remark</th> -->
								<th class="manastableheader noExl"></th>
							    <th class="manastableheader noExl"></th>
                                <th class="manastableheader noExl">Billing</th>

							</tr>

						</thead>

						<tbody>

							<%
							int count = 1;
							%>
							<s:if test="allPatientList.size!=0">

								<s:iterator value="allPatientList" status="rowstatus">
									<tr id="<s:property value="id" />">

										<td class="hidden"><s:property value="id" /></td>
										<td class="hidden"><s:property value="abrivationid" /></td>

										<%
										if (loginfo.isLmh()) {
										%>
										<td><s:property value="pstatus" /></td>
										<%
										}
										%>
										<%
										if (loginfo.isSjivh()) {
										%>
										<td><span style="cursor: pointer;"
											onclick="setSelectedRows(<s:property value="id" />,'<s:property value="abrivationid"/>','<s:property value="firstName"/>/<s:property value="lastName"/>','<s:property value="age1"/>','<s:property value="gender"/>')"
											id="nametd<%=count%>"><s:property value="Middlename" /></span>
											<s:if test="ipdList.size>0">
												<i title="View Ipd Details" class="fa fa-arrow-down"
													style="cursor: pointer;"
													onclick="showhideClientIpdDetails(<s:property value="id"/>)"></i>
											</s:if></td>
										<%
										} else {
										%>
										<td><span style="cursor: pointer;"
											onclick="setSelectedRows(<s:property value="id" />,'<s:property value="abrivationid"/>','<s:property value="firstName"/>/<s:property value="lastName"/>','<s:property value="age1"/>','<s:property value="gender"/>')"
											id="nametd<%=count%>"><s:property value="title" /> <s:property
													value="firstName" /> <s:property value="middlename" /> <s:property
													value="lastName" /></span> <s:if test="ipdList.size>0">
												<i title="View Ipd Details" class="fa fa-arrow-down"
													style="cursor: pointer;"
													onclick="showhideClientIpdDetails(<s:property value="id"/>)"></i>
											</s:if></td>
										<%
										}
										%>
										<td><s:property value="Country" />(<s:property
												value="Town" />)</td>
										<td><s:property value="mobNo" /></td>
										<td><span id="emailtd<%=count%>"><s:property
													value="email" /></span></td>






										<td class="noExl"><s:property value="lastModified" /></td>
										
										<td class="noExl"><s:property value="Numberofdays" /></td>
										<%-- <td class="noExl"><s:property value="Patient_status" /></td> --%>
										<s:if test="Patient_status==''">
											<td style="cursor: pointer;" class="text-center noExl"><a
												href="#"
												onclick="modifyclientstatus(<s:property value="id"/>,'<s:property value="Patient_status" />')"><i
													class="fas fa-plus"></i></a></td>
										</s:if>
										<s:else>
											<td style="cursor: pointer;" class="text-center noExl"><a
												href="#"
												onclick="modifyclientstatus(<s:property value="id"/>,'<s:property value="Patient_status" />')"><s:property
														value="Patient_status" /></a></td>
										</s:else>

										<%-- <td style="cursor: pointer;" class="text-center noExl"><a href="#" onclick="modifyclientstatus(<s:property value="id"/>,'<s:property value="Patient_status" />')"><s:property value="Patient_status" /></a></td> --%>
										<%-- <td class="noExl"><s:property value="Sent_mail_date" /></td>
										<td class="noExl"><s:property value="Sent_whtup_date" /></td> --%>
										<s:hidden value="%{id}" name="id"></s:hidden>
										<s:url action="editRegistration" id="edit">
											<s:param name="selectedid" value="%{id}"></s:param>
											<s:param name="isfromaddpatient" value="1"></s:param>
										</s:url>

										<td style="cursor: pointer;" class="text-center noExl">
											<%
											if (!loginfo.isLmh()) {
											%> <s:a href="%{edit}" title="Edit">
												<i class="fa fa-edit"></i>
											</s:a> <%
 } else {
 %> <i class="fa fa-edit"></i> <%
 }
 %>
										</td>
										<%-- <td style="cursor: pointer;" class="text-center noExl"><a
											href="#" onclick="addremark(<s:property value="id"/>)"><i
												class="fa fa-edit"></i></a></td> --%>
										<%-- <td style="cursor: pointer;" class="text-center noExl"><a
											href="#"
											onclick="opencPopup('showremarkClient?selectedid=<s:property value="id"/>')"><i
												class="fa fa-edit"></i></a></td> --%>
										<td class="hidden-print"><span class=""
											onclick="sendinvstigationLetter1('<s:property value="firstName"/>',<s:property value="id"/>,0,'<s:property value="email" />')"><a
												href="#"><i class="fa fa-envelope-o "
													title="send Email Messege" style="color: #555;"></i></a></span></td>
										<td class="hidden-print"><span class=""
											onclick="sendinvstigationLetter1('<s:property value="firstName"/>',<s:property value="id"/>,1,<s:property value="email" />)"><a
												href="#"><i class="fa fa-commenting "
													title="send whatsup Messege" style="color: #555;"></i></a></span></td>
										<%-- <td><a href="#" onclick="addremark(<s:property value="id"/>)"><i class="fa fa-edit"></i></a>
												 / <a href="#" onclick="opencPopup('showremarkClient?selectedid=<s:property value="id"/>')"><i class='fa fa-file' title="Open Discharge Card"></i></a> --%>
<td><a href="#" onclick="redirectToViewAcc1(<s:property value="id"/>)">Account</a></td>
									</tr>

									<tr class="noExl" id="ipd<s:property value="id"/>"
										style="display: none;">
										<td colspan="14" class="noExl">
											<table
												class="table table-striped table-bordered table-responsive noExl">
												<thead class="noExl">
													<tr class="noExl">
														<th>Admission ID</th>
														<th>Admission Date / Time</th>
														<th></th>
														<th>Discharge Date / Time</th>
														<th>Discharge Print / Form</th>

														<th>View Account</th>
														<th>label</th>

													</tr>
												</thead>
												<tbody class="noExl">
													<s:iterator value="ipdList">
														<tr>
															<td><a href="#"
																onclick="opencPopup('printCommonnew?selectedid=<s:property value="addmissionid"/>')">
																	<%
																	if (loginfo.isBalgopal()) {
																	%> <s:property
																		value="ipdabrivationid" /> <%
 } else {
 %> <s:property
																		value="ipdseqno" /> <%
 }
 %>
															</a></td>
															<td><s:property value="admissiondate" /></td>
															<s:if test="cancel==1">
																<td>Cancelled (<s:property value="cancelnote" />)
																	by <s:property value="cancelUser" /></td>
															</s:if>
															<s:else>
																<td></td>
															</s:else>

															<td><s:property value="dischargeDate" /></td>
															<td><a href="#"
																onclick="opencPopup('printdischargeCommonnew?selectedid=<s:property value="addmissionid"/>&clientid=<s:property value="clientid"/>')"><i
																	class='fa fa-print' title="Print Discharge Card"></i></a> /
																<a href="#"
																onclick="opencPopup('dischargeCommonnew?selectedid=<s:property value="addmissionid"/>&clientid=<s:property value="clientid"/>')"><i
																	class='fa fa-file' title="Open Discharge Card"></i></a></td>
															<td><a href="#"
																onclick="opencPopup('Statement?clientId=<s:property value="clientid"/>&ipdidnew=<s:property value="addmissionid"/>')"><i
																	class='fa fa-line-chart' title="Open Accounts"></i></a></td>
															<td><a href="#"
																onclick="openPopup('patientlabelIpd?labelipdid=<s:property value="addmissionid"/>&selectedid=<s:property value="clientid"/>')">Label</a></td>
														
														
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</td>
									</tr>

									<%
									count = count + 1;
									%>
								</s:iterator>
							</s:if>
							<s:else>
						There is no Client found!!
					</s:else>
						</tbody>
					</table>
				</div>

			</div>
		</div>
		<s:form action="searchClient" name="paginationForm"
			id="paginationForm" theme="simple">
			<s:hidden name="showAll" />
			<s:hidden name="status"></s:hidden>
			<s:hidden name="searchText"></s:hidden>
			<s:hidden name="diaryUser"></s:hidden>
			<s:hidden name="pstatus"></s:hidden>
			<div class="col-lg-12 col-md-12"
				style="padding: 0px; margin-top: 15px;">
				<div class="col-lg-4 col-md-4 col-sm-4  text-left"
					style="padding: 0px;">
					Total:<b class="text-info"><s:property value="totalRecords" /></b>
				</div>
				<%@ include file="/common/pages/pagination.jsp"%>
			</div>




		</s:form>
	</div>

	<!-- Send Letter Details Modal -->
<s:form id="redirectToDashboard1" method="post"
		action="sendmailDiaryMangent" enctype="multipart/form-data"
		theme="simple">
		<s:hidden name="Description" id="em_body"></s:hidden>
		<s:hidden name="Clientid" id="aftid"></s:hidden>
		<div class="modal fade" id="sendLetterPopup" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">


					<div class="modal-header bg-primary">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Send Email/Letter</h4>
					</div>
					<div class="modal-body" id="sendsms">
						<div class="row">
							 <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
         <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
				<label> User</label>
		 </div>
		 <div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
				<%-- <s:select id="userNameletter" name="userName"  
				list="{'Client'}" cssClass="form-control  showToolTip chosen" 
				value="userName" onchange="setNameLetter(this.value)"></s:select>
				<label  id = "unameError" class="text-danger"></label>	 --%>
				<s:textfield name="userName" id="userNameletter" readonly="true" cssClass="form-control"  value="Client"  />
				<label  id = "unameError" class="text-danger"></label>	
		</div>
		</div>



							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<label>Select Template</label>
								</div>
								<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
									<a href="#" onclick="openBlankPopup('addEmailTemplate')">+Add
										Template</a> <select id="templateName" name="templateName"
										onchange="showTemplateDetails1(this.id)"
										class="form-control showToolTip chosen" data-toggle="tooltip">
										<option value="0">Select Template Name</option>
									</select>
								</div>
							</div>



							<s:hidden name="id" id="id"></s:hidden>
							<s:hidden name="user" id="userletter"></s:hidden>
							<%-- <div class="col-lg-12">
		 <div class="col-lg-3">
			<label>User Name</label>
		</div>
		 <div class="col-lg-8">
			<s:textfield name="user" id="userletter" readonly="true" cssClass="form-control" />
				     <label  id = "userError" class="text-danger"></label>	
		<s:hidden name="id" id="id"></s:hidden>
		<s:hidden name="user" id="userletter"></s:hidden>
		</div>
		</div> --%>



							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<label>Email ID</label>
								</div>
								<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
									<s:textfield name="email" id="gpLetterEmail"
										cssClass="form-control showToolTip" title="Enter EmailId"
										data-toggle="tooltip" placeholder="Enter EmailId" />
									<label id="emailError" class="text-danger"></label>
								</div>
							</div>



							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<label>Cc</label>
								</div>
								<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
									<s:textfield theme="simple" id="gpLetterccEmail" name="ccEmail"
										cssClass="form-control showToolTip" title="Enter Cc"
										data-toggle="tooltip" placeholder="Enter Cc" />
								</div>
							</div>



							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<label>Subject</label>
								</div>
								<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
									<input type="text" name="subject" id="gpLettersubject"
										class="form-control showToolTip" data-toggle="tooltip"
										title="Enter Subject" placeholder="Enter Subject">
								</div>
							</div>

							<!-- <div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
				<label>Attachments:</label>
				<input type="hidden" id="fileUpload" name = "fileUploadd" />
 				 <div id = "drop">
 					
      			  FileUpload Controls will be added here
      			  <div id="upload"></div>
      			  <div id = "draftAttachments"></div>
    			</div>
    			
    			<br/>
 				  <input id="Button1" class="btn btn-default"  type="button" value="Attach Files" onclick = "AddFileUpload()" />
    			
   			</div> -->

							<br />
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<label>Body:</label>
								</div>
								<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
									<s:textarea cssStyle="height:2500px;"
										class="form-control showToolTip textarea"
										data-toggle="tooltip" title="Write content"
										placeholder="Write content" name="Description"
										id="emailBodyLetter" />
								</div>
							</div>

							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									Attached File:
									<div id="attachdfilediv" class="attached"></div>
								</div>
								<div class="col-lg-7 col-md-7"
									style="float: right; margin-top: -4px;">
									<button id="Button1" class="btn btn-warning" type="button"
										onclick="AddFileUpload1()">
										<i class="fa fa-paperclip"></i> Attach Files
									</button>
								</div>
							</div>
							<br> <br> <br>
							<div class="">
								<input type="hidden" id="fileUpload" name="fileUploadd" /> <input
									type="hidden" id="fileuplo" name="fileUploadd" />
								<div id="drop" class="col-lg-12 col-md-12"
									style="padding-top: 5px;">
									<!--FileUpload Controls will be added here -->
									<div id="upload"></div>
									<div id="draftAttachments"></div>
								</div>
							</div>

							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<table class="table table-hover table-condensed table-bordered"
									style="text-align: center;">
									<thead>
										<tr>
											<th class="text-center">Serial No</th>
											<th class="text-center">File name</th>
											<th class="text-center">Delete</th>
										</tr>
									</thead>

									<tbody id="drop123" style="text-align: center;">

									</tbody>
								</table>
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
								<!-- <button type="button" id="saveId" class="btn btn-primary" onclick="return createPdf()">Save as pdf</button> -->
								<!-- <button type="button" id="saveId" class="btn btn-primary"  onClick="fileUpload1(this.form,'sendLetterEmailInstantMsg','upload'); return false;">Send Mail</button> -->
								<button type="button" id="saveId" class="btn btn-primary"
									onclick="setEmailParam1()">Send Mail</button>

								<!--  <button type="button" onclick="setEmailParam1()" class="btn btn-primary pull-right" style="height: 45px !important;font-size: 16px;">Send</button> -->
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


	<s:form id="whatsup" method="post" action="sendwhatsupDiaryMangent"
		enctype="multipart/form-data" theme="simple">
		<s:hidden name="Clientid" id="sms_clientid"></s:hidden>
		<s:hidden name="Clientid" id="aftid"></s:hidden>
		<div class="modal fade" id="whatsupPopup" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">


					<div class="modal-header bg-primary">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Send whatsup
							message</h4>
					</div>
					<div class="modal-body" id="sendsms">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<label> User</label>
								</div>
								<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
									<%-- <s:select id="userNameletter" name="userName"  
				list="{'Client'}" cssClass="form-control  showToolTip chosen" 
				value="userName" onchange="setNameLetter(this.value)"></s:select>
				<label  id = "unameError" class="text-danger"></label>	 --%>
									<s:textfield name="userName" id="userNameletter1"
										readonly="true" cssClass="form-control" value="Client" />
									<label id="unameError" class="text-danger"></label>
								</div>
							</div>







							<s:hidden name="id" id="id"></s:hidden>
							<s:hidden name="user" id="userletter"></s:hidden>




							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<label>Contact no</label>
								</div>
								<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
									<s:textfield name="email" id="gpLetterEmail"
										cssClass="form-control showToolTip" title="Enter number"
										data-toggle="tooltip" placeholder="Enter number" />
									<label id="emailError" class="text-danger"></label>
								</div>
							</div>


							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12 dtd bookav">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<label>Subject</label>
								</div>
								<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
									<input type="text" name="subject" id="gpLettersubject"
										class="form-control showToolTip" data-toggle="tooltip"
										title="Enter Subject" placeholder="Enter Subject">
								</div>
							</div>

							<br />
							<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
								<div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
									<label>Body:</label>
								</div>
								<div class="col-lg-8 col-md-8 col-xs-8 col-sm-8">
									<s:textarea rows="14" cols="87"
										class="form-control showToolTip textarea"
										data-toggle="tooltip" title="Write content"
										placeholder="Write content" name="Description"
										id="emailBodyLetter2" />
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
								<!-- <button type="button" id="saveId" class="btn btn-primary" onclick="return createPdf()">Save as pdf</button> -->
								<!-- <button type="button" id="saveId" class="btn btn-primary"  onClick="fileUpload1(this.form,'sendLetterEmailInstantMsg','upload'); return false;">Send Mail</button> -->
								<button type="button" id="saveId" class="btn btn-primary"
									onclick="setwhatsupParam1()">Send msg</button>

								<!--  <button type="button" onclick="setEmailParam1()" class="btn btn-primary pull-right" style="height: 45px !important;font-size: 16px;">Send</button> -->
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


	<div class="modal fade popoverpop" id="showremark"
		style="z-index: 10000;" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-md" style="margin-right: 100px">
			<s:hidden id="affClientid"></s:hidden>
			<div class="modal-content" style="width: 80%;">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">&times;</button>

					Add Remark
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
							<div style="margin-top: 9px;">
								<img src="cicon/mic_off.png" class="img-responsive micimg"
									onclick="startConverting155(this,'remark')" title="Microphone"
									id="changer1"></img> &nbsp; Search By Voice Over &nbsp;

							</div>

							<div class="form-group" style="margin-top: 10px;">
								<s:textarea cssClass="form-control" cssStyle="height:100%;"
									class="form-control showToolTip textarea" data-toggle="tooltip"
									name="Note" id="remark" />
							</div>

							<div class="form-group" style="margin-top: 10px;">
								<s:select name="status" id="patientstatus"
									list="#{'Follow Up':'Follow Up','Done':'Done','Predicate':'Predicate','Hot':'Hot','Cold':'Cold','Wam':'Wam'}"
									cssClass="form-control" headerKey="All" headerValue="All"></s:select>
							</div>

						</div>


					</div>


				</div>
				<div class="modal-footer" style="text-align: right;">
					<a href="#" class='btn btn-primary' id="savereqnote"
						onclick="save()">Save</a>
				</div>

				<!-- <div class="table-responsive" > 
					   <table  class="table table-hover table-condensed table-bordered" style="text-align: center;"> 	
				<thead>
				    <tr>  
				        <th class="text-center">Serial No</th>
						<th class="text-center">Date</th>
						<th class="text-center">userid</th>
						<th class="text-center">Note</th>
						<th class="text-center">Edit</th>
						<th class="text-center">Delete</th>
					 	
					</tr>
		    </thead>
		    
		              <tbody id="carttbody" style="text-align: center;">
           
                      </tbody>
			      </table>
		       </div> -->
			</div>
		</div>
	</div>




	<div class="modal fade popoverpop" id="modifystatus"
		style="z-index: 10000;" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-md" style="margin-right: 100px">
			<s:hidden id="modClientid"></s:hidden>
			<div class="modal-content" style="width: 50%;">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					Modify Patient Status
				</div>
				<div class="modal-body">

					<div class="form-group" style="margin-top: 10px;">
						<s:select name="status" id="patientstatus1"
							list="#{'Follow Up':'Follow Up','Done':'Done','Predicate':'Predicate'}"
							cssClass="form-control" headerKey="All" headerValue="All"></s:select>
					</div>

					<div class="modal-footer">
						<a href="#" class='btn btn-primary' id="updatestatus"
							onclick="updatestatus()">Update</a>
					</div>

				</div>


			</div>

		</div>
	</div>
</div>

<!-- Refer Patient -->

<div id="referpatient" class="modal fade" role="dialog">

	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Refer To</h4>
			</div>
			<div class="modal-body">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

					<s:hidden id="Clientid"></s:hidden>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-group">
							<label>Hospital List </label><label class="text-danger">*</label>
							<s:select list="Referhospitallist" name='Hosp_name' id='hname'
								listKey="id" listValue="Hosp_name" headerKey=""
								headerValue="All Hospital" cssClass="form-control chosen-select"></s:select>
						</div>
					</div>





				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					onclick="savepatient()" style="margin-top: 60px;">Save</button>
			</div>
		</div>
	</div>
</div>
<!-- Update Patient plan -->

<div id="updatepatientplan" class="modal fade" role="dialog">

	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Update Plan</h4>
			</div>
			<div class="modal-body">
				<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">

					<s:hidden id="Clientid1"></s:hidden>

					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="form-group">
							<label>Plans </label><label class="text-danger">*</label>
							<s:select list="planlist" onchange="getDaysAjax(this.value)"
								name='plans' id='plans' listKey="id" listValue="plan"
								headerKey="" headerValue="Select Plan"
								cssClass="form-control chosen-select"></s:select>
						</div>
						<div class="form-group" id="daysdiv">
							<label>Total Days </label><label class="text-danger">*</label>
							<s:select list="plandayslist" name='planday' id='planday'
								listKey="id" listValue="days" headerKey=""
								headerValue="Select Days" cssClass="form-control chosen-select"></s:select>
						</div>
						<div class="form-group">
							<label>Days Completed </label><label class="text-danger">*</label>
							<input type="text" class="form-control" id="cdays" name="cdays">
						</div>
						<div class="form-group">
							<label>Current Date </label><label class="text-danger">*</label>
							<s:textfield name="currdate" id="currdate"
								cssClass="form-control" theme="simple" style="width:100%;"></s:textfield>
						</div>
					</div>





				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="modifyplan()"
					style="margin-top: 60px;">Save</button>
			</div>
		</div>
	</div>
</div>






<!-- Patient Panel Modal -->
<div class="modal fade" id="clickicon" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><%=loginfo.getPatientname_field()%>
					Panel -<label id="pname"></label>
				</h4>
			</div>
			<!-- <div class="modal-body" style="min-height: 179px;"> -->
			<%
			String heightDyn = "268px";
			%>
			<%
			if (loginfo.isAdmsn_date_edit()) {
			%>
			<%
			heightDyn = "360px";
			%>
			<%
			}
			%>
			<div class="modal-body" style="min-height: <%=heightDyn%>;">
				<div class="col-lg-12 borderdesing">
					<div class="row">

						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isClient_emai()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="openprintletter()"> <img
									src="cicon/email.png" alt="Email" style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Email/Letter</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="cicon/email.png" alt="Email" style="width: 45px;">
								<div class="caption">
									<p class="textclinet">Email/Letter</p>
								</div>

							</div>
							<%
							}
							%>
						</div>
						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isClient_print()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="opendislayprofile()"> <img
									src="cicon/print.png" alt="Email" style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Print Record</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="cicon/print.png" alt="Email" style="width: 45px;">
								<div class="caption">
									<p class="textclinet">Print Record</p>
								</div>

							</div>
							<%
							}
							%>
						</div>

						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isClient_emai()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="opentreatmentpopup()"> <img
									src="cicon/treatment.png" alt="Email" style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Treatment Episode</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="cicon/treatment.png" alt="Email" style="width: 45px;">
								<div class="caption">
									<p class="textclinet">Treatment Episode</p>
								</div>

							</div>
							<%
							}
							%>
						</div>
						<div class="col-xs-2 col-md-2 panthumb">
							<div class="thumbnail">
								<a href="#" onclick="openappointmentopopup()"> <img
									src="cicon/appointment.png" alt="Email" style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Appointment</p>
									</div>
								</a>
							</div>
						</div>
						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isClient_treatment()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="openlogpopup()"> <img
									src="cicon/log.png" alt="log" style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Log</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="cicon/log.png" alt="log" style="width: 45px;">
								<div class="caption">
									<p class="textclinet">Log</p>
								</div>

							</div>
							<%
							}
							%>
						</div>
						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isClient_recordpayment()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="openrecordpaymentpopup()"> <img
									src="cicon/cash_desk.png" alt="record_payment"
									style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Record Payment</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="cicon/cash_desk.png" alt="record_payment"
									style="width: 45px;">
								<div class="caption">
									<p class="textclinet">Record Payment</p>
								</div>

							</div>
							<%
							}
							%>
						</div>

					</div>
					<div class="row">



						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isClient_cashdesk()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="openaccountpopup()"> <img
									src="cicon/cash_desk.png" alt="cash_desk" style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Cash Desk</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="cicon/cash_desk.png" alt="cash_desk"
									style="width: 45px;">
								<div class="caption">
									<p class="textclinet">Cash Desk</p>
								</div>

							</div>
							<%
							}
							%>
						</div>
						<div class="col-xs-2 col-md-2 panthumb">
							<div class="thumbnail">
								<a href="#" onclick="openadvrefundpopup()"> <img
									src="cicon/raise_credit.png" alt="raise_credit"
									style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Advance & Refund</p>
									</div>
								</a>
							</div>
						</div>

						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isClient_addcharge()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="openaddchargepopup()"> <img
									src="cicon/raise_credit.png" alt="raise_credit"
									style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Add Charge</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="cicon/raise_credit.png" alt="raise_credit"
									style="width: 45px;">
								<div class="caption">
									<p class="textclinet">Add Charge</p>
								</div>

							</div>
							<%
							}
							%>
						</div>

						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isClient_viewaccount()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="openaccount()"> <img
									src="cicon/view_ccount.png" alt="view_Account"
									style="width: 45px;">
									<div class="caption">
										<p class="textclinet">View Account</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="cicon/view_ccount.png" alt="view_Account"
									style="width: 45px;">
								<div class="caption">
									<p class="textclinet">View Account</p>
								</div>

							</div>
							<%
							}
							%>
						</div>
						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isClient_emr()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="openemrhere()"> <img
									src="cicon/emr.png" alt="Email" style="width: 45px;">
									<div class="caption">
										<p class="textclinet">EMR</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="cicon/emr.png" alt="Email" style="width: 45px;">
								<div class="caption">
									<p class="textclinet">EMR</p>
								</div>

							</div>
							<%
							}
							%>
						</div>
						<div class="col-xs-2 col-md-2 panthumb">
							<%
							if (loginfo.isIpd_declaration()) {
							%>
							<div class="thumbnail">
								<a href="#" onclick="openClientDeclarationPrintPopup()"> <img
									src="popicons/eye.png" alt="..." style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Declaration</p>
									</div>
								</a>
							</div>
							<%
							} else {
							%>
							<div class="thumbnail disabled ">
								<img src="popicons/eye.png" alt="..." style="width: 45px;">
								<div class="caption">
									<p class="textclinet">Declaration</p>
								</div>

							</div>
							<%
							}
							%>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-2 col-md-2 panthumb">
							<div class="thumbnail">
								<a href="#" onclick="opennewimmunizationchartpopup()"> <img
									src="cicon/pregnancysm.png" alt="raise_credit"
									style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Immunization Chart</p>
									</div>
								</a>
							</div>
						</div>
						<div class="col-xs-2 col-md-2 panthumb">
							<div class="thumbnail">
								<a href="#" onclick="openheadcircumferencechartpopup()"> <img
									src="cicon/ChildGrowthChart.png" alt="raise_credit"
									style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Child Growth Chart</p>
									</div>
								</a>
							</div>
						</div>

						<div class="col-xs-2 col-md-2 panthumb">
							<div class="thumbnail">
								<a href="#" onclick="opennewimmunizationchartpopup1()"> <img
									src="cicon/Anc_icon.png" alt="raise_credit"
									style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Antenatal Schedule</p>
									</div>
								</a>
							</div>
						</div>

						<div class="col-xs-2 col-md-2 panthumb">
							<div class="thumbnail">
								<a href="#" onclick="openInvestigationdash()"> <img
									src="cicon/invst.png" alt="raise_credit" style="width: 45px;">
									<div class="caption">
										<p class="textclinet">View Investigation</p>
									</div>
								</a>
							</div>
						</div>
						<div class="col-xs-2 col-md-2 panthumb">
							<div class="thumbnail" style="text-align: center;">
								<a href="#" onclick="generatePatientbarcode()"> <!-- <img src="cicon/invst.png" alt="raise_credit" style="width: 45px;">   -->
									<span style="text-align: center"><i
										class='fa fa-barcode fa-3x' aria-hidden="true"
										style="color: #333"></i></span>
									<div class="caption">
										<p class="textclinet">Barcode</p>
									</div>
								</a>
							</div>
						</div>

						<div class="col-xs-2 col-md-2 panthumb">
							<div class="thumbnail" style="text-align: center;">
								<a href="#" onclick="opennephrologyvacc()"> <!-- <img src="cicon/invst.png" alt="raise_credit" style="width: 45px;">   -->
									<span style="text-align: center"><i
										class='fa fa-drupal fa-3x' aria-hidden="true"
										style="color: #333"></i></span>
									<div class="caption">
										<p class="textclinet">Nephrology Vaccination</p>
									</div>
								</a>
							</div>
						</div>
						<%
						if (loginfo.isAdmsn_date_edit()) {
						%>
						<%
						heightDyn = "360px";
						%>
						<div class="col-xs-2 col-md-2 panthumb">
							<div class="thumbnail">
								<a href="#" onclick="editdisdate()"> <img
									src="cicon/log.png" alt="Change Admission Date"
									style="width: 45px;">
									<div class="caption">
										<p class="textclinet">Change Admission Date</p>
									</div>
								</a>
							</div>
						</div>
						<%
						}
						%>
						<!--   <div class="col-xs-2 col-md-2 panthumb">
                                                   <div class="thumbnail">
                                                      	<a href="#"  onclick="openInvestigationdash1()">
                                                        <img src="cicon/invst.png" alt="raise_credit" style="width: 45px;">  
                                                         <div class="caption">
                                                              <p class="textclinet">Give Investigation</p>
                                                          </div>
                                                         </a>
                                                      </div>
                                                  </div> -->
						<div class="col-xs-2 col-md-2 panthumb"></div>
						<div class="col-xs-2 col-md-2 panthumb"></div>
						<div class="col-xs-2 col-md-2 panthumb"></div>
					</div>

				</div>
			</div>
			<div class="modal-footer hidden">
				<button type="button" class="btn btn-default hidden"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary hidden">Save
					changes</button>
			</div>
		</div>
	</div>
</div>

<!-- add invesgtigation Modal -->
<input type="hidden" id="invchargeinfodata" />
<input type="hidden" id="invstdateandtime" value="Today" />
<div class="modal fade" id="investigationpopup" tabindex="-1"
	role="dialog" aria-labelledby="lblsemdsmspopup" aria-hidden="true"
	data-keyboard="false" data-backdrop="static">
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
			<div class="modal-body" id="investi">


				<%@ include file="/emr/pages/addInvestigation.jsp"%>


			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					onclick="saveViewInvestigation()">Save</button>

				<!-- <button type="button" class="btn btn-primary"
						onclick="insertEmrPriscription(1)">Save & Print</button> -->

				<button type="button" class="btn btn-primary hidden"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<!-- Add Treatment Episode -->
<div class="modal fade" id="showallnotepopupdiv" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><%=loginfo.getPatientname_field()%>
					Note
				</h4>
			</div>
			<div class="modal-body">
				<textarea rows="8" cols="80" readonly="readonly" name="cnote"
					id="cnote"></textarea>
			</div>
			<div class="modal-footer">

				<!-- <button type="button" class="btn btn-primary"
						onclick="saveTreatment();">Save</button> -->
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>



<!-- Add Treatment Episode -->
<div class="modal fade" id="addTreatmentEpisodeDiv" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Create Treatment
					Episode</h4>
			</div>
			<div class="modal-body">
				<%@ include file="/treatmentEpisode/pages/addTreatmentEpisode.jsp"%>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-primary disblebtnone"
					onclick="saveTreatment();">Save</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- Create Treatment Episode or Not -->
<div class="modal fade" id="tratementAddDiv" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Create Treatment
					Episode</h4>
			</div>
			<div class="modal-body">
				<label>New client has been added successfully. Do you want
					to create treatment episode?</label>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-primary"
					onclick="getCurrentClientDetails();" data-dismiss="modal">Yes</button>
				<button type="button" class="btn btn-primary"
					onclick="redirectToDashboard()" data-dismiss="modal">No</button>
			</div>
		</div>
	</div>
</div>
<div id="editadmndatemodal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center;">Change
					Admission Date</h4>
			</div>

			<div class="modal-body">
				<div class="col-lg-12 col-md-12 col-sm-12" style="padding: 0px;">

					<label>Edit Date : </label> <input type="text" name='editadmndate'
						id='editadmndate' class="form-control editadmndate" style=""
						readonly> <br>
					<br>
					<div class="form-group">
						<label for="exampleInputName2">HH :</label>

						<s:select name="hour" cssStyle="width: 30%" id="hour"
							list="hourList" cssClass="form-control" headerKey="0"
							headerValue="Select" />

						<label for="exampleInputName2">MM :</label>

						<s:select name="minute" cssStyle="width: 30%" id="minute"
							list="minuteList" cssClass="form-control " headerKey="0"
							headerValue="Select" />

					</div>
				</div>
			</div>
			<div class="modal-footer">
				<input type="button" value="Save" class="btn btn-danger"
					onclick="saaveadmndate()">
			</div>
		</div>
	</div>
</div>
<s:form action="newsavedClient" id="redirectToDashboard">

</s:form>


</div>
</div>
</div>
</div>




<!-- others plugins -->

<script>
    Highcharts.setOptions({
    colors: ['#0a467c', '#50c4ca', '#27b7ff', '#0fd7da', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
});

    Highcharts.chart('container', {
  chart: {
    type: 'variablepie'
  },
  title: {
    text: 'These Weeks Top 4 Cases '
  tooltip: {
   
    headerFormat: '',
    pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' 
      
  },
  series: [{
    minPointSize: 10,
    innerSize: '20%',
    zMin: 0,
    name: 'countries',
    data: [{
      name: 'Fever',
      y: 505370,
      z: 42
    }, {
      name: 'Viral',
      y: 551500,
      z: 118.7
    }, {
      name: 'Cold',
      y: 312685,
      z: 124.6
    }, {
      name: 'Diabetes',
      y: 78867,
      z: 137.5
    },  ]
  }]
});

</script>
<script type="text/javascript">
var date=new Date();
var todaydate=String(date.getDate()).padStart(2,'0');
var month=String(date.getMonth()+1).padStart(2,'0');
var year=date.getFullYear();
var pattern=year +'-'+ month +'-'+ todaydate +" "+date.getHours() + ":" 
+ date.getMinutes() + ":" + date.getSeconds(); ;
document.getElementById("currdate").value=pattern;

</script>







