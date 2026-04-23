/* 
	author: istockphp.com
*/
var diaryuserId = "";
var commencing = "";
var diaryUser1 = "";
var stime = "";
var etime = "";
var loc = " ";
var id1 = "";
var tempDiaryUserName="";
var tempStatus = "";
var editStartTime = "";
var editEndTime = "";
var editDuration = "";
var editClientName = "";
var editNotes = "";
var editAppointType = "";
var editAppointId = 0;
var practitionerEmailId="";
var clientEmailId="";
var patientId = "";
var editCommencing = "";
var blockDivTime = "";
var slotstarttime = "";
var imagename="";
var editTreatmentEpisode;
var wraperDivId = 0;
var editdiaryuseid = 0;
var editSlotId = 0;
var editapmttypetext = "";
var editLocationName = "";
var wraperTdList = "";
var editArrivedStatus = 0;
var editcondition_id="";
var editwhopay = "";
var mytitle = "";
var newTitle = "";	
var clientExclusiveName = "";
var diaryduration = 0;
var statusbookwithpayment=0;
var height=0;
var isotoropd=0;
var weight=0;
var bmi=0;
var pulse=0;
var sysbp=0; 
var diabp=0;
var grefenddate = '';
var greffromdate = '';
var otdb = '';
var rdddval = 0;

var pppid = 0;
var pppcname = '';
var pppwhopay = '';
var ppptepisode = 0;

var newsugarfasting =0;
var newpostmeal=0;
var newtemprature=0;
var mobselectedclient = '';
var newspo=0;
var bsa=0;
var tempDiaryUseridsss="";
/*var displayTooltip = false;
var messageDelay = 10000;
jQuery.fn.center = function () {
		
		this.css("position","absolute");
		//this.css("top", ( $(window).height() - this.height() ) / 2+$(window).scrollTop() + "px");
		//this.css("left", ( $(window).width() - this.width() ) / 2+$(window).scrollLeft() + "px");
		
		return this;
	};
	*/
/*$(document).ready(function(){
	$("#dashboardDiv").hide();
	

});	*/

function showdiv(){

	//$('#blockdiv').dialog( "open" );
	//document.getElementById('choice').value = 0;
	
	/*document.getElementById('modifyPopup').style.display = 'none';
	document.getElementById('appointment').style.display = 'none';
	document.getElementById('clientdidnotattentpopup').style.display = 'none';
	document.getElementById('blockdiv').style.display = '';
	document.getElementById('reminderPopup').style.display = 'none';
	//alert(diaryUser1);
	document.getElementById('blockapmtdiv').innerHTML = '<font color="white">Non Available Appointment Slot</font>';*/
	//document.getElementById('blocknotes').value = "";
	//$('#Non Available Appointment Slot').dialog('option', 'title', 'New Title');
	
		document.getElementById('blockuser').value = tempDiaryUserName;
		document.getElementById('blockdate').value = document.getElementById('date').value;
		document.getElementById('blocksTime').value = blockDivTime;
		document.getElementById('blockendTime').value = etime;
		document.getElementById('blockapmtDuration').value = 0;
		document.getElementById('blocklocation').value = loc
	 
   // $(this).MessageBox(null,0,null,id1,null,stime,etime,null,loc,null,null,diaryUser1,commencing,id1);
	
	
    
    $('.ui-dialog-buttonpane button:contains("Delete")').button().hide();
	
	
}

//set client has arrived
function clienthasarrived(status){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	var url = "setresetNotAvailableSlot?selectedid="+editAppointId+"&status="+status+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = clienthasarrivedRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function clienthasarrivedRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			//document.getElementById('clienthasarrived').style.display = 'none';
			//document.getElementById('clientisbeingseen').style.display = '';
			//document.getElementById('resetnotarrived').style.display = '';
			//document.getElementById('clientdidnotcome').style.display = 'none';
			//disable
			//$(this).cancelSlot();			
			
			/*getSearch();
			showAvailiability();
			getDaySearch();*/
			$( '#modifyPopup' ).modal( "hide" );
			getApmLoggedInUserList();
			setCommonAction();
			 var temp = apmuserlist.split(',');
				for(i=0;i<temp.length;i++){
					if(temp[i]!=$.cometChat.loginUserName){
						$.cometChat.send("apmt_other", temp[i],"");
					}
					
				}
				showdisplaynewopd();
		}
	}

}
function clearFiledCommonAction(){
	document.getElementById('exclusivetpname').innerHTML = '';
	document.getElementById('apmtTypeError').innerHTML="";
	document.getElementById('sTimeError').innerHTML="";
	//document.getElementById('notesError').innerHTML="";
	document.getElementById('treatmentEpisodeError').innerHTML="";
	document.getElementById('clientError').innerHTML="";
	document.getElementById('blockapmtDurationError').innerHTML="";
	document.getElementById('blocksTimeError').innerHTML="";
	//document.getElementById('blocknotesError').innerHTML="";
	document.getElementById('sessionDetail').innerHTML = "";
	document.getElementById('amountPending').innerHTML = "";
	document.getElementById('apmtTypeDuration').innerHTML = "";
	
	
	document.getElementById('radio').style.display = '';
	document.getElementById('blockradio').style.display = '';
	
	document.getElementById('radio1').checked = true;
	document.getElementById('blockradio1').checked = true;
	document.getElementById('radio2').checked = false;
	document.getElementById('blockradio2').checked = false;
	
	
		if(openedb=='otdb'){
				document.getElementById("radio3").checked = true;
			}
			
		resetot();
	
	document.getElementById('myModalLabel').innerHTML = "Block Appointment";
	
	
}

function resetot(){
	if(document.getElementById("radio3").checked == true){
				document.getElementById('motcatdiv').style.display = '';
				document.getElementById('otclientinfodiv').style.display = '';
				document.getElementById('prodepartmentdiv').style.display = '';
				document.getElementById('otprocedurediv').style.display = '';
				document.getElementById('otassistingstaffdiv').style.display = '';
				document.getElementById('otsurgeondiv').style.display = '';
				document.getElementById('otanesthesiadiv').style.display = '';
				document.getElementById('opdrepeatspan').style.display = 'none';
				document.getElementById('otplaned').value = '0';
				document.getElementById('otdepartment').className="";
				document.getElementById('otdepartment').value = '0';
				$("#otdepartment").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				
				document.getElementById('otprocedureplaned').className="";
				document.getElementById('otprocedureplaned').value = '0';
				$("#otprocedureplaned").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				
				document.getElementById('apmtType').className="";
				document.getElementById('apmtType').value = '0';
				$("#apmtType").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				
				document.getElementById('otsurgeonname').className="";
				document.getElementById('otsurgeonname').value = '0';
				$("#otsurgeonname").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				
				document.getElementById('otanesthesia').className="";
				document.getElementById('otanesthesia').value = '0';
				$("#otanesthesia").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				
				document.getElementById('otplaned').className="";
				document.getElementById('otplaned').value = '0';
				$("#otplaned").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				
				
				
				
			}else{
				document.getElementById('motcatdiv').style.display = 'none';
				document.getElementById('otclientinfodiv').style.display = 'none';
				document.getElementById('prodepartmentdiv').style.display = 'none';
				document.getElementById('otprocedurediv').style.display = 'none';
				document.getElementById('otassistingstaffdiv').style.display = 'none';
				document.getElementById('otsurgeondiv').style.display = 'none';
				document.getElementById('otanesthesiadiv').style.display = 'none';
				document.getElementById('opdrepeatspan').style.display = '';
			}

}

function movetosetCommonAction(){
	
	//editAppointId = 0;
	setCommonAction();
}

function setCommonAction(){
	
	clearFiledCommonAction();
	if(isnewopd==1){
		showdisplaynewopd();
		document.getElementById("editSave1").innerHTML="<a href='#' style='color: yellow;' onclick='editBMI("+1+")' title='Edit'><i class='fa fa-pencil' aria-hidden='true'></i></a>";
	}else{
	if(actionType == 0){
		getSearch();
	}else if(actionType==5){
		mobSearch();
	}
	else if(actionType == 1){
		showAvailiability();
	}else{
	
		getDaySearch();
	}
	}
}


//set redirectToCreateCharge
function redirectToCreateCharge(){

	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	
	//alert(editwhopay);
	document.getElementById('accountChargeClientid').value = patientId;
	document.getElementById('accountchargethirdparty').value = "All";
	document.getElementById('accountchargetransactionType').vlue = "All";
	document.getElementById('accountsLocationid').value = loc;
	document.getElementById('accountclientid').value = editClientName;
	document.getElementById('accountpayby').value = editwhopay;
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	document.getElementById('crtchargeapmtid').value = editAppointId;
	
	checkAppointmentInvoicedForCashDesk();
	
	if(isappointmentinvoiced=='1'){
		jAlert('error', "Appointment invoiced,can't Create invoice again!!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var t = 'formtarget';


		
		var left = (screen.width / 2) - (700 / 2);
		var top = (screen.height / 2) - (550 / 2);
		var oldwindow = window.open("", t,
				"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
						+ ",top=" + 50);
		
		oldwindow.focus();
		
		
		
		
		document.getElementById('accountchargefrm').submit();
	}
	
	
	
}

function redirectToRecordPayment(){

	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	
	document.getElementById('accountpaymentClientid').value = patientId;
	document.getElementById('accountpaymentthirdparty').value = "All";
	document.getElementById('accountpaymenttransactionType').vlue = "All";
	document.getElementById('accountspaymentLocationid').value = loc;
	document.getElementById('accountpaymentclientid').value = editClientName;
	document.getElementById('accountspaymentfromDateid').value = "";
	document.getElementById('accountspaymentfromDateid').value = "";
	document.getElementById('accountPaymentPayby').value = editwhopay;
	
var t = 'formtarget';


	
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	oldwindow.focus();
	
	
	document.getElementById('accountpaymentfrm').submit();
	
	
	
}
function redirectToViewAcc(){
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	
	document.getElementById('viewAccClientid').value = patientId;
	document.getElementById('viewAccclientname').value = editClientName;
	document.getElementById('viewAccPayby').value = editwhopay;
	document.getElementById('viewAccLocationid').value = loc;
	document.getElementById('viewAccthirdparty').value = "All";
	document.getElementById('viewAcctransactionType').vlue = "All";
	document.getElementById('viewAccfromDateid').value = "";
	document.getElementById('viewAcctoDateid').value = "";
	
	
	var t = 'formtarget';

	
	
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	oldwindow.focus();

//	$('#newloaderPopup').modal('show');
	
	
	document.getElementById('view_acc_frm').submit();
	


	return true;

}

function setInstantCashDesk(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	
	//checkAppointmentInvoicedForCashDesk();
	
	document.getElementById('cashClientid').value = patientId;
	document.getElementById('cashclientname').value = editClientName;
	document.getElementById('cashPayby').value = editwhopay;
	document.getElementById('cashLocationid').value = loc;
	document.getElementById('cashAppointmentid').value = editAppointId;
	
	
		var t = 'formtarget';

		
		
		var left = (screen.width / 2) - (700 / 2);
		var top = (screen.height / 2) - (550 / 2);
		var oldwindow = window.open("", t,
				"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
						+ ",top=" + 50);
		
		oldwindow.focus();

		
		
		
		document.getElementById('cashdeskfrm').submit();
	
	
	
	
	


	return true;
	
}


function redirectToApmtFinder(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	document.getElementById('finderClientid').value = patientId;

	
	var t = 'formtarget';

	
	
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	oldwindow.focus();

	
	
	
	document.getElementById('finderfrm').submit();
	


	return true;
}


//set work completed
function setworkcompleted(status){
		var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	/*adarsh changes*/
	var cancelnotes = document.getElementById("delete_reason").value;
	 var url = "workNotAvailableSlot?selectedid="+editAppointId+"&status="+status+"&cancelnotes="+cancelnotes+" ";
	/*var url = "workNotAvailableSlot?selectedid="+editAppointId+"&status="+status+" ";*/
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setworkcompletedRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setworkcompletedRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
		$( '#blockapmtdaytodsypopup' ).modal( "hide" );
			getApmLoggedInUserList();
			setCommonAction();
			
            tempAlert("Status Modified!",5000);
            
            var temp = apmuserlist.split(',');
			for(i=0;i<temp.length;i++){
				if(temp[i]!=$.cometChat.loginUserName){
					$.cometChat.send("apmt_other", temp[i],"");
				}
				
			}

		}	
	}
}

//set client is being seen
function setClientIsBeingSeen(status){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	var url = "clientIsBeingSeenNotAvailableSlot?selectedid="+editAppointId+"&status="+status+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setClientIsBeingSeenRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setClientIsBeingSeenRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			
			/*document.getElementById('clienthasarrived').style.display = 'none';
			document.getElementById('clientisbeingseen').style.display = 'none';
			document.getElementById('resetnotarrived').style.display = '';
			document.getElementById('clientdidnotcome').style.display = 'none';*/
			//disable
			//$(this).cancelSlot();			
			$( '#modifyPopup' ).modal( "hide" );
			if(document.getElementById('recordpaymentpopup')){
				$( '#recordpaymentpopup' ).modal( "hide" );
			}
			
			getApmLoggedInUserList();
			setCommonAction();
			
            tempAlert("Ok,Client has been Called!",5000);
            
            var temp = apmuserlist.split(',');
			for(i=0;i<temp.length;i++){
				if(temp[i]!=$.cometChat.loginUserName){
					$.cometChat.send("apmt_other", temp[i],"");
				}
				
			}
			showdisplaynewopd();
		}	
	}
}


//Reset Not Arrived
function ResetToNotArrived(status){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	var url = "resetNotArrivedNotAvailableSlot?selectedid="+editAppointId+"&status="+status+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = ResetToNotArrivedRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function ResetToNotArrivedRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			/*document.getElementById('clienthasarrived').style.display = '';
			document.getElementById('clientisbeingseen').style.display = '';
			document.getElementById('resetnotarrived').style.display = 'none';
			document.getElementById('clientdidnotcome').style.display = '';*/
			//disable
			//$(this).cancelSlot();			
			$( '#modifyPopup' ).modal( "hide" );
			getApmLoggedInUserList();
			setCommonAction();
			 var temp = apmuserlist.split(',');
				for(i=0;i<temp.length;i++){
					if(temp[i]!=$.cometChat.loginUserName){
						$.cometChat.send("apmt_other", temp[i],"");
					}
					
				}
		}
	}

}


function setModifyClientDidNotCome(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	//document.getElementById('modifyPopup').style.display = 'none';
	//document.getElementById('clientdidnotattentpopup').style.display = '';
	$('#modifyPopup').modal( "hide" );
	$('#clientdidnotattentpopup').modal( "show" );
	$('#modifydnapopup').modal( "hide" );
	
	//document.getElementById('dnatextid').innerHTML = 'Modify D.N.A.';
		
	setDNAPercentageAmount(editAppointId);
}

//set clint did not attend
function setClientDidNotCome(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	
	//alert("dna");
	//document.getElementById('clientdidnotattentpopup').style.display = '';
	//document.getElementById('modifyPopup').style.display = 'none';
//	jQuery.noConflict();
	$('#clientdidnotattentpopup').modal( "show" );
	document.getElementById('missedappointmentwith').value = tempDiaryUserName;
	document.getElementById('didnotattendDate').value = "17/03/2014";
	document.getElementById('didnotattentTime').value = editStartTime;
	document.getElementById('didnotattentDuration').value = editDuration;
	//document.getElementById('didnotattendNotes').value = editNotes;
	document.getElementById('dnachk').checked = true;
//	jQuery.noConflict();
	$('#modifyPopup').modal( "hide" );
	
	//document.getElementById('dnaammtid').innerHTML = "";
	
	//alert("HELLO"+editAppointId)
	//if(editwhopay=='Client'){
		setDNAPercentageAmount(editAppointId);
	//}
	
}


function setFalseDNAPercentageAmount(editAppointId){
	
	 var url = "dnafalseNotAvailableSlot?selectedid="+editAppointId+" ";	
		$('#modifyPopup').modal( "hide" );
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setFalseDNAPercentageAmountRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function setFalseDNAPercentageAmountRequest(){
	 if (req.readyState == 4) {
			if (req.status == 200) {
				var data = req.responseText;
				
				var temp = data.split('/');
				var dnaamt = temp[0];
				var tpnotes = temp[1];
				var offset = temp[2];
			
				
				//document.getElementById('dnaammtid').innerHTML = '<b> '+currencySign + ' ' +dnaamt+'</b>';
				document.getElementById('predefindtpcharge').innerHTML =  '<b> '+currencySign + ' ' +dnaamt+'</b>';
				
				
				
				if(editwhopay=='Third Party'){
					
					document.getElementById('offsetdiv').style.display = '';
					document.getElementById('dnaOffset').checked = offset;
					//document.getElementById('dnaOffset').disabled = false;
					
					//tpnotes
					document.getElementById('dnatpnotesdiv').style.display = '';
					document.getElementById('dnatpnotes').value = tpnotes;
					
					document.getElementById('dnaclientrdo').disabled = false;
					document.getElementById('dnatprdo').disabled = false;
				
					if(dnaamt>0){
						document.getElementById('dnaChargespanid').style.display = '';
						document.getElementById('dnaammtid').style.display = '';
						document.getElementById('defineddnachargeid').style.display = '';
						
					}else{
						document.getElementById('dnaChargespanid').style.display = '';
						//document.getElementById('dnaammtid').style.display = 'none';
						document.getElementById('defineddnachargeid').style.display = '';
						
					}
					
				}else{
					
					document.getElementById('offsetdiv').style.display = 'none';
					document.getElementById('dnaOffset').checked = offset;
					//tpnotes
					document.getElementById('dnatpnotesdiv').style.display = 'none';
					
					document.getElementById('defineddnachargeid').style.display = 'none';
					document.getElementById('dnaChargespanid').style.display = '';
					document.getElementById('defineddnachargeid').style.display = 'none';
					document.getElementById('dnaclientrdo').disabled = true;
					document.getElementById('dnatprdo').disabled = true;
					//document.getElementById('dnaammtid').style.display = 'none';
				}
				
				
				
			}
			
			
			document.getElementById('enterdnachargeid').style.display = '';
			document.getElementById('showdnachargeid').style.display = 'none';
		
	 }
	
}


 function setDNAPercentageAmount(editAppointId){
	 var url = "dnaperNotAvailableSlot?selectedid="+editAppointId+" ";	
		$('#modifyPopup').modal( "hide" );
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setDNAPercentageAmountRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
 }
 
 function setDNAPercentageAmountRequest(){
	 if (req.readyState == 4) {
			if (req.status == 200) {
				
				var data = req.responseText;
				var temp = data.split('~');
				var dnaamt = temp[0];
				var pby = temp[1];
				var dnareason = temp[2];
				var dnanptes = temp[3];
				var tpnotes = temp[4];
				var offset = temp[5];
				if(offset=="true"){
					offset = true;
				}else{
					offset = false;
				}
				
				if(pby==0){
					
					document.getElementById('offsetdiv').style.display = 'none';
					document.getElementById('dnaOffset').checked = offset;
					
					document.getElementById('dnaclientrdo').checked = true;
					document.getElementById('dnatpnotesdiv').style.display = 'none';
				}else{
					document.getElementById('dnatprdo').checked = true;
					document.getElementById('dnatpnotesdiv').style.display = '';
					document.getElementById('dnatpnotes').value = tpnotes;
					
					document.getElementById('offsetdiv').style.display = '';
					document.getElementById('dnaOffset').checked = offset;
					//document.getElementById('dnaOffset').disabled = true;
					
				}
				
				document.getElementById('why').value = dnareason;
				if(dnanptes=='null' || dnanptes==null){
					document.getElementById('didnotattendNotes').value="";
				}else{
				document.getElementById('didnotattendNotes').value = dnanptes;
				}
				//document.getElementById('dnaammtid').innerHTML = '<b> '+currencySign + ' ' +dnaamt+'</b>';
				
				document.getElementById('dnaChargespanid').style.display = 'none';
				//document.getElementById('dnaammtid').style.display = '';
				document.getElementById('predefindtpcharge').innerHTML =  '<b> '+currencySign + ' ' +dnaamt+'</b>';
				
				document.getElementById('defineddnachargeid').style.display = 'none';
				document.getElementById('enterdnachargeid').style.display = 'none';
				document.getElementById('showdnachargeid').style.display = '';
				document.getElementById('editdnaCharge').value = dnaamt;
				
				
				/*if(editwhopay=='Third Party'){
					if(dnaamt>0){
						document.getElementById('dnaChargespanid').style.display = 'none';
						document.getElementById('dnaammtid').style.display = '';
					}else{
						document.getElementById('dnaChargespanid').style.display = '';
						document.getElementById('dnaammtid').style.display = 'none';
					}
					
				}*/
				
				
				
			}
	 }
 }
 
 
 function increaseTreatmentEpisodeCnt(){
	 
	 if(document.getElementById('dnachk').checked == false){
		 document.getElementById('countrd1').checked = true;
	 }
 }

//set client did not attent confirm
 function setClientDidNotComeConfirm(){
 	
 	var dnaNotes = document.getElementById('didnotattendNotes').value;
 	var dna = document.getElementById('dnachk').checked ;
 	var dnaReason = document.getElementById('why').value;
 	var dnaCharge = document.getElementById('dnaCharge').value;
 	var dnaOffset = document.getElementById('dnaOffset').checked;
 	
 	var checknumber = $('#dnaCharge').val();
 	var editdnaCharge = 0;
 		if(globalDna==true){
 			editdnaCharge = document.getElementById('editdnaCharge').value;
 			checknumber = $('#editdnaCharge').val();
 		}
 		
 	
 	var dnapayby = 0;
 	if(document.getElementById('dnatprdo').checked==true){
 		dnapayby = 1;
 	}
 	
 	
 	
 	var isdecimal = validateDecimalNumber(checknumber);

     if(isdecimal == false){
     	jAlert('error', 'Please enter valid value', 'Error Dialog');
         $('#special_price').focus();
     	setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
         return;
     }else if(parseInt(dnaCharge)>100){
     	jAlert('error', 'percentage value can not be greater than 100 ', 'Error Dialog');
    	setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
     }
    /* else if(document.getElementById('countrd1').checked==false && document.getElementById('countrd2').checked==false){
    	 jAlert('error', 'Please Select Treatment Episode Count Do You Want to Increase or Not ', 'Error Dialog');
    	
     }*/
     
     
     else{
    	 
    	 
     	var url = "didnotAttendNotAvailableSlot?selectedid="+editAppointId+"&dnaNotes="+dnaNotes+"&dna="+dna+"&dnaReason="+dnaReason+"&dnaCharge="+dnaCharge+"&dnapayby="+dnapayby+"&globalDna="+globalDna+"&editdnaCharge="+editdnaCharge+"&dnaOffset="+dnaOffset+" ";	
     	$('#clientdidnotattentpopup').modal( "hide" );
   	 $('#dashboardloaderPopup').modal( "show" );
     	$('#modifyPopup').modal( "hide" );
     	if (window.XMLHttpRequest) {
     		req = new XMLHttpRequest();
     	}
     	else if (window.ActiveXObject) {
     		isIE = true;
     		req = new ActiveXObject("Microsoft.XMLHTTP");
     	}   
                    
         req.onreadystatechange = setClientDidNotComeConfirmRequest;
         req.open("GET", url, true); 
                   
         req.send(null);
        
     }
 	
 	

 }

function setClientDidNotComeConfirmRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			document.getElementById('dnaCharge').value = '0';
						
			$( '#clientdidnotattentpopup' ).modal( "hide" );
				getApmLoggedInUserList();
				setCommonAction();
			 var temp = apmuserlist.split(',');
				for(i=0;i<temp.length;i++){
					if(temp[i]!=$.cometChat.loginUserName){
						 $('#dashboardloaderPopup').modal( "hide" );
						$.cometChat.send("apmt_other", temp[i],"");
						
					}
					
				}
				 $('#dashboardloaderPopup').modal( "hide" );
		}
	}
}




//set modify popup
function setModifyPopup(status,starttime,endtime,duration,clientName,notes,apmtType,appointmentId,arrivedstatus,dna,userid,clientId,commencing,practitionerEmail,clientEmail,charge,commencing,slotstarttime,treatmentEpisode,iscompleted,slotid,apmttypetext,locationName,conditionid,tptypeid,tpnameid,whopay,tpname,timagename,theight,tweight,tbmi,tpulse,tsysbp,tdiabp,plan,procedure,surgeon,anesthesia,ipdno,wardid,asisdoclist,sugarfasting,postmeal,temprature,spo,bsa){

 pppid = clientId;
 pppcname = clientName;
 pppwhopay = whopay;
 newsugarfasting = sugarfasting;
 newpostmeal = postmeal;
	//dna details
	document.getElementById('dnaAppointmentName').value = apmttypetext + ' ' + currencySign + charge;
	
	/*//  17 JAN 2018
	getnewbmidata(clientId);*/

//ot values
	document.getElementById('otplaned').value = plan;
	document.getElementById('otprocedureplaned').value = procedure;
	document.getElementById('otsurgeonname').value = surgeon;
	document.getElementById('otanesthesia').value = anesthesia;
	document.getElementById('otipdno').innerHTML =  ipdno;
	if(plan=='0'){
		sessionStorage.setItem("location", "opd");
	}else{
		sessionStorage.setItem("location", "ot");
	}
	
	
	if(appointmentId!=0){
		document.getElementById('btnBookWithPayment').style.display = 'none';
	}
	
	
	var isot = document.getElementById('radio3').checked;
	if(isot==true){
		
		document.getElementById('morechargesspanid').style.display = '';
		
		 if(asisdoclist!=0 && asisdoclist!=""){
			 if(asisdoclist!=null){
				 if(asisdoclist.length>1){
			   	 	 var asis = asisdoclist.split(',');
				   	 for(var a=0;a<asis.length;a++){
				   	 	document.getElementById('otst'+asis[a]).checked = true; 
				   	 }
			   	 }else{
			   	 	document.getElementById('otst'+asisdoclist).checked = true; 
			   	 }
			 }
		   	
		  }
		   	 
	}
	
	
	 $("#otplaned").trigger("chosen:updated");
   	 $(".chosen").chosen({allow_single_deselect: true});
	
	 $("#otprocedureplaned").trigger("chosen:updated");
   	 $(".chosen").chosen({allow_single_deselect: true});
   	 
   	  $("#otsurgeonname").trigger("chosen:updated");
   	 $(".chosen").chosen({allow_single_deselect: true});
   	 
   	  $('.casees').each(function() { //loop through each checkbox
           this.checked = false;  //select all checkboxes with class "checkbox1"               
       });
   	 
   
   	  $("#otanesthesia").trigger("chosen:updated");
   	 $(".chosen").chosen({allow_single_deselect: true});
	
if(status ==1){
	document.getElementById('completeapmt').disabled="disabled";
	document.getElementById('clientarrived').disabled="disabled";
	document.getElementById('clientseen').disabled="disabled";
	document.getElementById('dna').disabled="disabled";
	document.getElementById('reminder').disabled="disabled";
	
	
	
}
else{
	
	if(iscompleted == true){
		document.getElementById('completeapmt').disabled="disabled";
	}else{
		document.getElementById('completeapmt').disabled="";
	}
	document.getElementById('clientarrived').disabled="";
	document.getElementById('clientseen').disabled="";
	document.getElementById('dna').disabled="";
	document.getElementById('reminder').disabled="";
	
	//set treatment episode
	editpateintpayby = whopay;
	setTreatmentEpisode(clientId);
	
}
//disabled popup button
	
	tempStatus = status;
	editStartTime = starttime;
	editEndTime = endtime;
	editDuration = duration;
	editClientName = clientName;
	editNotes = notes;
	editAppointType = apmtType;
	editAppointId = appointmentId;
	editCommencing = commencing;
	slotstarttime = slotstarttime;
	editTreatmentEpisode = treatmentEpisode;
	editdiaryuseid = userid;
	editSlotId = slotid;
	editapmttypetext = apmttypetext;
	editLocationName = locationName;
	editArrivedStatus = arrivedstatus;
	
	globalTpType = tptypeid;
	globaltpid = tpnameid;
	editwhopay = whopay;
	editpateintpayby = whopay;
	editTpName = tpname;
	
	//for reminder mail
	clientName = clientName;
	diaryuserId  = userid;
	practitionerEmailId = practitionerEmail;
	clientEmailId = clientEmail;
	patientId = clientId;
	imagename= timagename;
	editcondition_id = conditionid;
	globalDna = dna;
	height=theight;
	weight=tweight;
	bmi=tbmi;
	pulse=tpulse;
	sysbp=tsysbp;
	diabp=tdiabp;
	if(temprature!=null){
	newtemprature=temprature;
	}else{
		newtemprature='';	
	}
	
	if(spo!=null){
		newspo=spo;
		}else{
			newspo='';	
		}
	
	if(arrivedstatus == 1){
		
		$(document.getElementById('clienthasarrived')).css('background-color', 'gray');
		document.getElementById('clienthasarrived').onclick = '';
		
		$(document.getElementById('clientseen')).css('background-color', 'white');
		document.getElementById('clientseen').onclick = function() {setClientIsBeingSeen(2)};
		
		$(document.getElementById('reset')).css('background-color', 'white');
		document.getElementById('reset').onclick = function() {ResetToNotArrived(0)};
		
		/*document.getElementById('clienthasarrived').style.display = 'none';
		document.getElementById('clientisbeingseen').style.display = '';
		document.getElementById('resetnotarrived').style.display = '';*/
		
	}else if(arrivedstatus == 2){
		
		$(document.getElementById('clienthasarrived')).css('background-color', 'gray');
		document.getElementById('clienthasarrived').onclick = '';
		
		document.getElementById('clientseen').onclick = '';
		$(document.getElementById('clientseen')).css('background-color', 'gray');
		
			
		$(document.getElementById('reset')).css('background-color', 'white');
		document.getElementById('reset').onclick = function() {ResetToNotArrived(0)};
		
			/*document.getElementById('clienthasarrived').style.display = 'none';
			document.getElementById('clientisbeingseen').style.display = 'none';
			document.getElementById('resetnotarrived').style.display = '';*/
	}else{
			
			$(document.getElementById('clienthasarrived')).css('background-color', 'white');
			document.getElementById('clienthasarrived').onclick = function() {clienthasarrived(1)};
			
			$(document.getElementById('clientseen')).css('background-color', 'white');
			document.getElementById('clientseen').onclick = function() {setClientIsBeingSeen(2)};
			
			$(document.getElementById('reset')).css('background-color', 'gray');
			document.getElementById('reset').onclick = '';
			
		
			/*document.getElementById('clienthasarrived').style.display = '';
			document.getElementById('clientisbeingseen').style.display = '';
			document.getElementById('resetnotarrived').style.display = 'none';*/
	}
	
	//document.getElementById('appointment').style.display = 'none';
	//document.getElementById('modifyPopup').style.display = '';
	if(status==0){
	    	document.getElementById("height1").innerHTML=height;			
			document.getElementById("weight1").innerHTML=weight;
			document.getElementById("bmi1").innerHTML=bmi;
			document.getElementById("pulse1").innerHTML=pulse;
			document.getElementById("sysbp1").innerHTML=sysbp;
			document.getElementById("diabp1").innerHTML=diabp;
			document.getElementById('sugarfasting1').innerHTML = newsugarfasting;
			document.getElementById('postmeal1').innerHTML = newpostmeal;
			document.getElementById('temprature1').innerHTML = newtemprature;
			document.getElementById('spo1').innerHTML = newspo;
			document.getElementById('bsa1').innerHTML = bsa;
		$('#modifyPopup').modal( "show" );
		if(isotoropd==1){
			document.getElementById('modify').className="thumbnail";		
			document.getElementById('mvmdappointment').className="hidden";
		}
		$('#blockapmtdaytodsypopup').modal( "hide" );
	}
	if(status==1){
		$('#modifyPopup').modal( "hide" );
		
			document.getElementById("height4").innerHTML=height;			
			document.getElementById("weight4").innerHTML=weight;
			document.getElementById("bmi4").innerHTML=bmi;
			document.getElementById("pulse4").innerHTML=pulse;
			document.getElementById("sysbp4").innerHTML=sysbp;
			document.getElementById("diabp4").innerHTML=diabp;
			document.getElementById('sugarfasting4').innerHTML = newsugarfasting;
			document.getElementById('postmeal4').innerHTML = newpostmeal;
			document.getElementById('temprature4').innerHTML = newtemprature;
			document.getElementById('spo4').innerHTML = newspo;
			document.getElementById('bsa4').innerHTML = bsa;
		$('#blockapmtdaytodsypopup').modal( "show" );
		$( "#blockdiv" ).modal( "hide" );
	}
	
	
	document.getElementById('user').value = tempDiaryUserName;
	
	
		/*document.getElementById('modifyHeading').innerHTML = 'Client Appointment';
		document.getElementById('modifyClient').innerHTML = editClientName;
		document.getElementById('modifyDate').innerHTML = commencing;*/
	
	if(status==0 && dna==true){
		$('#modifyPopup').modal( "hide" );
		
			document.getElementById("height3").innerHTML=height;			
			document.getElementById("weight3").innerHTML=weight;
			document.getElementById("bmi3").innerHTML=bmi;
			document.getElementById("pulse3").innerHTML=pulse;
			document.getElementById("sysbp3").innerHTML=sysbp;
			document.getElementById("diabp3").innerHTML=diabp;
			document.getElementById('sugarfasting3').innerHTML = newsugarfasting;
			document.getElementById('postmeal3').innerHTML = newpostmeal;
			document.getElementById('temprature3').innerHTML = newtemprature;
			document.getElementById('spo3').innerHTML = newspo;
			document.getElementById('bsa3').innerHTML = bsa;
		$('#modifydnapopup').modal( "show" );
		//document.getElementById('modifydnaClient1').innerHTML = '<a href="printProfileClient?selectedid='+patientId+'" target="blank">'+editClientName+'</a> (<a href="editClient?selectedid='+patientId+'" title="Edit Client Record" target="blank"><i class="fa fa-edit"></i></a>)  (<a href = "ClientLog?id='+patientId+'" title="Log" target="blank"><img src="common/images/log.png"/></a>)  (<a href = "redirectEmr?clientId='+patientId+'&practitionerId='+diaryuserId+'&appointId='+editAppointId+'" title="EMR" target="blank"><img src="common/images/emr.png"/></a>)  (<a href = "redirectListAssessmentForm?clientId='+patientId+'&practitionerId='+diaryuserId+'&appointId='+editAppointId+'" title="Assessment Form" target="blank"><i class="fa fa-list-alt"></i></a>)';
		if(imagename=='null'){
		   document.getElementById('modifydnaClient1').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding:5px "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Form</p></a></div>';
		}
		else if(imagename==''){
		  document.getElementById('modifydnaClient1').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding:5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Form</p></a></div>';
		}else {
		  document.getElementById('modifydnaClient1').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="liveData/'+imagename+'" style="width: 100% !important;"/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Form</p></a></div>';
		}
		
		document.getElementById('missedappointmentwith').value = tempDiaryUserName;
		document.getElementById('didnotattendDate').value = commencing;
		document.getElementById('didnotattentTime').value = editStartTime;
		document.getElementById('didnotattentDuration').value = editLocationName;
		//document.getElementById('didnotattendNotes').value = editNotes;
		document.getElementById('dnachk').checked = dna;
		document.getElementById('dnaclinentname').value = clientName;
		
		if(editpateintpayby=='Client'){
			document.getElementById('dnapayby').value = 'Client(Self)';
			document.getElementById('dnaclientrdo').checked = true;
		}else{
			document.getElementById('dnapayby').value = 'TP' + ' ' + '('+editTpName+')'
			document.getElementById('dnatprdo').checked = true;
		}
		
		//if appointment has treatment episode
		/*if(editTreatmentEpisode>0){
			document.getElementById('tepisodednadiv').style.display = '';
		}else{
			document.getElementById('tepisodednadiv').style.display = 'none';
		}*/
			
	}
	if(status==0 && dna==false){
		/*document.getElementById('modifyPopup').style.display = '';
		document.getElementById('clientdidnotattentpopup').style.display = 'none';*/
		$('#modifyPopup').modal( "show" );
		
		if(isotoropd==1){
			document.getElementById('modify').className="thumbnail";		
			document.getElementById('mvmdappointment').className="hidden";
		}
		 document.getElementById('dna').onclick = function() {
			 $('#clientdidnotattentpopup').modal( "show" );
				
				document.getElementById('missedappointmentwith').value = tempDiaryUserName;
				document.getElementById('didnotattendDate').value = commencing;
				document.getElementById('didnotattentTime').value = editStartTime;
				document.getElementById('didnotattentDuration').value = editLocationName;
				//document.getElementById('didnotattendNotes').value = editNotes;
				document.getElementById('dnachk').checked = true;
				document.getElementById('dnaclinentname').value = clientName;
				
				if(editpateintpayby=='Client'){
					document.getElementById('dnapayby').value = 'Client(Self)';
					document.getElementById('dnaclientrdo').checked = true;
				}else{
					document.getElementById('dnapayby').value = 'TP' + ' ' + '('+editTpName+')'
					document.getElementById('dnatprdo').checked = true;
				}
				
				//if appointment has treatment episode
				/*if(editTreatmentEpisode>0){
					document.getElementById('tepisodednadiv').style.display = '';
				}else{
					document.getElementById('tepisodednadiv').style.display = 'none';
				}*/
				
				setFalseDNAPercentageAmount(editAppointId);
		 }
	}
	/*if(status==1 && dna==false){
		document.getElementById('modifyPopup').style.display = '';
		document.getElementById('clientdidnotattentpopup').style.display = 'none';
		$('#modifyPopup').modal( "show" );
	}*/
	
	//document.getElementById('modifyHeading').innerHTML = 'Client Appointment';
		//document.getElementById('modifyClient').innerHTML = editClientName;

    if(imagename==null){
       document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg" /><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Form</p></a></div>';
    }
    else if(imagename==''){
       document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding: 5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Form</p></a></div>';
    }
	else {	
	   document.getElementById('modifyClient').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4 opdcustombox"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="liveData/'+imagename+'" style="width: 100% !important;"/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Form</p></a></div>';
	}
	if(actionType==5){
		document.getElementById('puremodifyClient').innerHTML = '<a href="#" onclick="openClientPrintPopup('+patientId+')">'+editClientName+'</a> <a href = "#" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png"/></a> ';
	}	
	
	document.cookie = "cookieUserName=" + clientName;//Cookie name with value
	document.cookie = "cookieStarttime=" + starttime;
	document.cookie = "cookieDuration=" + duration;
	document.cookie = "cookiePractitioner=" + tempDiaryUserName;
	document.cookie = "cookieapmtType=" + apmtType;
	document.cookie = "cookieapmtTypeid=" + editAppointType;
	document.cookie = "cookiePractitionerId=" + userid;
	document.cookie = "cookieClientId=" + clientId;
	document.cookie = "cookiecommencing=" + commencing;
	document.cookie = "cookieCharge=" + charge;
	document.cookie = "cookieSelectedAppointmentid=" + editAppointId;
	document.cookie = "cookieTreatmentEpisode=" + editTreatmentEpisode;
	
	
	
	//getbmiData(patientId);
	
	
	//$(this).MessageBox(null,editStartTime,editEndTime,editDuration,loc,tempDiaryUserName,null,userid,clientId,commencing,practitionerEmail,clientEmail,charge);
}


function modifythisAppointment(){
	
	 //alert(wraperDivId);
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	//document.getElementById('btnBookWithPayment').style.display = 'none';
	document.getElementById('btnBookWithPayment').className = 'hidden';
	
	if(document.getElementById('otplaned').value==0){
		document.getElementById('motcatdiv').style.display = 'none';
		document.getElementById('otclientinfodiv').style.display = 'none';
		document.getElementById('prodepartmentdiv').style.display = 'none';
		document.getElementById('otprocedurediv').style.display = 'none';
		document.getElementById('otassistingstaffdiv').style.display = 'none';
		document.getElementById('otsurgeondiv').style.display = 'none';
		document.getElementById('otanesthesiadiv').style.display = 'none';
	}else{
		document.getElementById('motcatdiv').style.display = '';
		document.getElementById('otclientinfodiv').style.display = '';
		document.getElementById('otprocedurediv').style.display = '';
		document.getElementById('prodepartmentdiv').style.display = '';
		document.getElementById('otassistingstaffdiv').style.display = '';
		document.getElementById('otsurgeondiv').style.display = '';
		document.getElementById('otanesthesiadiv').style.display = '';
		
	}
	
	document.getElementById('radio').style.display = 'none';
	document.getElementById('blockradio').style.display = 'none';
	document.getElementById('myModalLabel').innerHTML = "Modify Blocked Appointment";
	
	checkEventExist = false;
	if(tempStatus == 0){
		
		
		//$('#appointment').dialog( "open" );
		 $( "#appointment" ).modal( "show" );
		$('#modifyPopup').modal( "hide" );
		$('#puremodifyPopup').modal( "hide" );
		$('#blockapmtdaytodsypopup').modal( "hide" );
	//alert(patientId);
		/*document.getElementById('appointment').style.display = '';
		document.getElementById('blockdiv').style.display = 'none';
		document.getElementById('modifyPopup').style.display = 'none';
		document.getElementById('reminderPopup').style.display = 'none';*/
	
		document.getElementById('user').value = tempDiaryUserName;
		document.getElementById('location').value = loc;
		document.getElementById('sTime').value = editStartTime;
		document.getElementById('endTime').value = editEndTime;
		document.getElementById('apmtDuration').value = editDuration;
		document.getElementById('client').value = editClientName;
		document.getElementById('clientId').value = patientId;
		document.getElementById('diaryUserId').value = editdiaryuseid;
		document.getElementById('slotId').value = editSlotId;
		document.getElementById('date').value = editCommencing;
		
		document.getElementById('condition').value = editcondition_id;
		$("#condition").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		
		//$("#apmtType option:contains(" + editAppointType + ")").attr('selected', 'selected');
		document.getElementById('apmtType').value = editAppointType;
		$("#apmtType").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		
		document.getElementById('treatmentEpisode').value = editTreatmentEpisode;
		$("#treatmentEpisode").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		
		//$("#apmtType:selected").text() = editAppointType;
		document.getElementById('notes').value = editNotes;
		
		//document.getElementById('apmtdiv').innerHTML = '<font color="white" > Modify New Appointment</font>';
		//$('#appointment').dialog('option', 'title', 'Modify Appointment');
		document.getElementById('apmtTypeDuration').innerHTML = "Duration: "+editDuration;
		
		
		
		document.getElementById('bookApmtLbl').innerHTML = "Modify Appointment";
		
		
		
		//set payby
		if(editwhopay == 'Third Party' && globalTpType==2){
			document.getElementById('paybypatient').checked=false;
			document.getElementById('paybypatient1').checked=true;
			setThirdPartyExclusiveAppointmentTypeEdit('tp',globaltpid);
			document.getElementById('exclusivetpname').innerHTML = '('+editTpName+')';
		}else{
			document.getElementById('paybypatient').checked=true;
			document.getElementById('paybypatient1').checked=false;
			
			setThirdPartyExclusiveAppointmentTypeEdit('Client', globaltpid);
			document.getElementById('exclusivetpname').innerHTML = '';
				
		}
		
	}else if(tempStatus == 1){
	
		
		/*document.getElementById('appointment').style.display = 'none';
		document.getElementById('blockdiv').style.display = '';
		document.getElementById('modifyPopup').style.display = 'none';
		document.getElementById('reminderPopup').style.display = 'none';*/
		
		document.getElementById('blockuser').value = tempDiaryUserName;
		document.getElementById('blocklocation').value = loc;
		document.getElementById('blocksTime').value = editStartTime;
		document.getElementById('blockendTime').value = editEndTime;
		document.getElementById('blockapmtDuration').value = editDuration;
		document.getElementById('blocknotes').value = editNotes;
		document.getElementById('diaryUserId').value = editdiaryuseid;
		document.getElementById('blockdate').value = editCommencing;
		$('#modifyPopup').modal( "hide" );
		$('#blockapmtdaytodsypopup').modal( "hide" );
		
		$('#blockdiv').modal( "show" );
		$('.ui-dialog-buttonpane button:contains("Delete")').button().show();
		//document.getElementById('blockapmtdiv').innerHTML = '<font color="white">Modify Non Available Appointment Slot</font>';
		//$('#blockdiv').dialog('option', 'title', 'Modify Non Available Appointment Slot');
		
		
	}else{
	
	}
	
	//  to set procedure selected 
	
	if(document.getElementById('otplaned').value==0){
	}else{
		setTimeout(function() {
			setappointmentprocedure(editAppointId);
		}, 100*2);
	}
	
	$(this).MessageBox(editSlotId,editStartTime,editEndTime,editDuration,loc,tempDiaryUserName,editdiaryuseid,null);
}

function reminder(){
		/*document.getElementById('appointment').style.display = 'none';
		document.getElementById('blockdiv').style.display = 'none';
		document.getElementById('modifyPopup').style.display = 'none';
		document.getElementById('reminderPopup').style.display = '';*/
		$('#reminderPopup').modal( "show" );
		
		document.getElementById('user').value = tempDiaryUserName;
		document.getElementById('location').value = loc;
		document.getElementById('sTime').value = editStartTime;
		document.getElementById('endTime').value = editEndTime;
		document.getElementById('apmtDuration').value = editDuration;
		document.getElementById('client').value = editClientName;
		document.getElementById('clientId').value = patientId;
		 
		document.getElementById('notes').value = editNotes;
		
		
		document.getElementById('remiderClient').innerHTML = editClientName;
		document.getElementById('remiderdate').innerHTML = commencing ;
		
		
		
		//Reminder filed set
		//alert(patientId);
		
		
		/*document.getElementById('practitionersName').value = tempDiaryUserName;
		document.getElementById('practitionersId').value = diaryuserId;
		document.getElementById('clientName').value = editClientName;
		document.getElementById('patientId').value = patientId;
		document.getElementById('aptmStartTime').value = editStartTime;
		document.getElementById('aptmDuration').value = editDuration;
		document.getElementById('aptmlocation').value = loc;
		document.getElementById('clientEmail').value = practitionerEmailId;
		document.getElementById('practitionersEmail').value = clientEmailId;*/
		
		
		
		
		

}

function getDepartmentCondition(location){
	
	var url = "departmentNotAvailableSlot?location="+location+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getDepartmentConditionRequrst;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function getDepartmentConditionRequrst(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("dpartmntcondiv").innerHTML = req.responseText;
				
	         	$("#condition").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				
	         }
		}
}


jQuery(function($) {

 $.fn.MessageBox = function(id,starttime,endtime,apmtduration,location,diaryUser,diaryUserId,divtime) {
	
	 var tepmtile = divtime.split(' '); 
	 divtime = tepmtile[0];
	 
	
	
 		document.getElementById('slotId').value = id;
 		document.getElementById('blockdiaryUserId').value = diaryUserId;
 		document.getElementById('blockslotId').value = id;
		//document.getElementById('sTime').value = starttime;
		//document.cookie = "cookieNewStarttime=" + starttime;
 		/*globalSlotStartTime = document.getElementById('sTime').value;*/
 		globalSlotStartTime = starttime;
 		document.getElementById('endTime').value = endtime;
 		//document.getElementById('apmtDuration').value = apmtduration;
 		document.getElementById('location').value = location;
 		
 		//radio button coding
 		/*if(document.getElementById('radio2').checked==true || document.getElementById('blockradio2')){
 			bookSlotid = id;
 	 		bookCommencing = editCommencing;
 	 		bookLocation = location;
 	 		bookDiaryUserid = diaryUserId;
 	 		bookDiaryUser = diaryUser;
 	 		bookStartTime = starttime;
 	 		bookEndTime = endtime;
 	 		bookAppointmentType = 0;
 			
 			document.getElementById('blockuser').value = tempDiaryUserName;
			document.getElementById('blockdate').value = commencing;
			document.getElementById('blocksTime').value = blockDivTime;
 			document.getElementById('blockendTime').value = etime;
 			document.getElementById('blockapmtDuration').value = 0;
 			document.getElementById('blocklocation').value = loc;
 		}
 		*/
 		
 		
 		//if(editAppointId==0){
 			document.getElementById('sTime').value = divtime;
 			document.getElementById('apmtDuration').value = 0;
 			
 		//}
 		
 		
 		
 			
			
			
			
			
			var date = editCommencing;
			var id = document.getElementById('diaryUser').value;
			
			
			document.getElementById('date').value = date;
			document.getElementById('diaryUserId').value = diaryUserId;
			document.getElementById('user').value = diaryUser;
			commencing = editCommencing;
							
			
			
			document.getElementById('date').value = commencing;
			document.getElementById('diaryUserId').value = diaryUserId;
			
			//getDepartmentCondition(location);
			
			var location = document.getElementById('location');
			
							
			
		
		
			
		///var str = '<input class = "buttons"  type="button" value="Submit" onclick="$(this).saveSlot(\''+editAppointId+'\',\''+starttime+'\');"/>'
 		//str = str + '<input class = "buttons"  type="button" value="Cancel" onclick="$(this).cancelSlot();"/>'
 		//document.getElementById('submitslot').innerHTML = str;
 		
 	/*	var str = '<input class = "buttons" type="button" value="Submit" onclick="$(this).blockSave(\''+editAppointId+'\',\''+stime+'\');"/>'
 		str = str + '<input class = "buttons"  type="button" value="Cancel" onclick="$(this).cancelSlot();"/>'
 		document.getElementById('submitblockslot').innerHTML = str;*/
			
			
			 loading(); // loading
			setTimeout(function(){ // then show popup, deley in .5 second
				loadPopup(); // function show popup 
			}, 500); // .5 second
			
			
			
			return false;
        };
	
	
	
	/* event for close the popup */
	$("div.close").hover(
					function() {
						$('span.ecs_tooltip').show();
					},
					function () {
    					$('span.ecs_tooltip').hide();
  					}
				);
	
	$("div.close").click(function() {
		//disablePopup();  // function close pop up
	});
	
	$(this).keyup(function(event) {
		if (event.which == 27) { // 27 is 'Ecs' in the keyboard
			//disablePopup();  // function close pop up
			//document.getElementById('addTreatment').disabled = true;

			
		}  	
	});
	
	$("div#backgroundPopup").click(function() {
		//disablePopup();  // function close pop up
	});
	
	$('a.livebox').click(function() {
		alert('Hello World!');
	return false;
	});
	

	 /************** start: functions. **************/
	function loading() {
		$("div.loader").show();  
	}
	function closeloading() {
		$("div.loader").fadeOut('normal');  
	}
	
	var popupStatus = 0; // set value
	
	function loadPopup() { 
		if(popupStatus == 0) { // if value is 0, show popup
			closeloading(); // fadeout loading
			
			
			//$("#background").css({"opacity" : "0.7"}).fadeIn("slow");
			//$("#dashboardDiv").center().fadeIn("slow");	
			
			//$("#previewDiv").fadeIn(0500); // fadein popup div
			
			//$("#backgroundPopup").css("opacity", "0.7"); // css opacity, supports IE7, IE8
			//$("#backgroundPopup").fadeIn(0001); 
			popupStatus = 1; // and set value to 1
		}	
	}
		
	function disablePopup() {
		/*if(popupStatus == 1) { // if value is 1, close popup
			$("#background").fadeOut("slow");
			$("#dashboardDiv").hide();
			popupStatus = 0;  // and set value to 0
			
			$(document.getElementById('dashboardDiv')).css('width', '40%');
			document.getElementById('anothertd').style.display = 'none';
			document.getElementById('clientSearchDiv').style.display = 'none';
			document.getElementById('addPatientDiv').style.display = 'none';
			document.getElementById('addTreatmentEpisodeDiv').style.display = 'none';
			
			//setCommonAction();
			
		}*/
	}
	/************** end: functions. **************/
	
	$.fn.saveSlot = function(id,starttime){
		var chk = 0;
		document.getElementById('apmtTypeError').innerHTML="";
		document.getElementById('sTimeError').innerHTML="";
		//document.getElementById('notesError').innerHTML="";
		
        document.getElementById('clientError').innerHTML="";
        document.getElementById('conditionError').innerHTML="";
        var v=diaryuserId;

		var tempDuration = document.getElementById('apmtDuration').value;
		 if(document.getElementById('client').value==""){
        	var l1 = document.createElement("label");
        	l1.innerHTML = "Please Enter Patient.";
        	l1.style.color ="red";
 	      	document.getElementById('clientError').appendChild(l1);
 	        chk=1;

    	}else if(document.getElementById('apmtDuration').value == 0){
           
           
        	var l1 = document.createElement("label");
        	l1.innerHTML = "Please Select Appointment Type.";
        	l1.style.color ="red";
 	      	document.getElementById('apmtTypeError').appendChild(l1);
 	        chk=1;

		}
		else if(checkEventExist=='true'){
			jAlert('error', 'This Slot Already Booked, Please Select Another Time !!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		
		}else{
		
			//chek start time
			var stime = document.getElementById('sTime').value;
			
			var initStime = stime+":"+"00";
			starttime = starttime+":"+"00";
			
			if(id > 0){
				starttime = slotstarttime;
			}
			
			//check end time
			var total = getTimeTotal(tempDuration,stime);
			
			total = total+":"+"00";
			var tempglobalEndTime = etime+":"+"00";
			
			var totalstarttime = total,
	    	endtime = tempglobalEndTime;
			var sessions = labelText = $("#sessionDetail1").text();
			
		
			var tempsessions = sessions.split(" ");
			var s5 = $('#sessionDetail1').text();
			
			if(s5 == null || s5 == "" || s5 == undefined){
				s5 = $('#treatmentEpisode1').text();
			}
//			var v=document.getElementById('apmtType').value;
//			if(actionType==5){
//				if(v=='' || v==null || v=='0'){
//					document.getElementById('apmtType').value=1;
//				}
//			}
//			var v=document.getElementById('apmtType').value;
			document.getElementById('treatmentEpisodeError').innerHTML="";		
	    	if(initStime < starttime){
            	var l1 = document.createElement("label");
            	l1.innerHTML = "Please Enter valid start time.";
     	      	document.getElementById('sTimeError').appendChild(l1);
     	        chk=1;

			}
	    	
	    	/*else if(totalstarttime > endtime){
               
            	var l1 = document.createElement("label");
            	l1.innerHTML = "Please Enter Valid Time.";
     	      	document.getElementById('sTimeError').appendChild(l1);
     	        chk=1;

	    	}*/
	    	
	    	else if(document.getElementById('apmtType').value==0){
              
            	var l1 = document.createElement("label");
            	l1.innerHTML = "Please Enter Appointmen Type.";
     	      	document.getElementById('apmtTypeError').appendChild(l1);
     	        chk=1;

	    	}/*else if(document.getElementById('condition').value == 0){
                
            	var l1 = document.createElement("label");
            	l1.id = "treatmentEpisode1";
            	l1.innerHTML = "Please Select Condition.";
     	      	document.getElementById('conditionError').appendChild(l1);
     	        chk=1;

	    	}*/
	   
	    	else if(s5 =="All authorised consumed, create new"){
	    		
	    		document.getElementById('sessionDetail1').innerHTML="";
				var l1 = document.createElement("label");
				l1.id = "treatmentEpisode1";

            	l1.innerHTML = "<span class='weblable'>All authorised consumed,<a href='#' onclick='addTreatmentEpisode()'> create new</a></span>";
     	      	document.getElementById('treatmentEpisodeError').appendChild(l1);
     	        chk=1;

	    	}
	    	
	    	
	    	/*else if(document.getElementById('notes').value==""){
                
            	var l1 = document.createElement("label");
            	l1.innerHTML = "Please Enter Notes.";
     	      	document.getElementById('notesError').appendChild(l1);
     	        chk=1;

	    	}*/
	    	else{
	    		
	    		var commencing = document.getElementById('date').value;
				var currentdate = getCurrentDate();
				
				if(chk==1){
					return false;
				}
				else{
					/*if (new Date(commencing) < new Date(currentdate)) {
		                jAlert('error', 'Please contact Admin to add or edit this slot!!', 'Error Dialog');

					}else if((new Date(commencing).getTime() == new Date(currentdate).getTime()) && (initStime < getCurrentTime())){
		                jAlert('error', 'Please contact Admin to add or edit this slot!!', 'Error Dialog');

					}*/ if(checkEventExist=='true'){
						jAlert('error', 'This Slot Already Booked, Please Select Another Time !!', 'Error Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					}
					/*else if(document.getElementById('paybypatient1').checked == true && document.getElementById('treatmentEpisode').value==0){
						jAlert('error', 'Please Select/Create Treatment Episode !!', 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					}*/
					else{
						
						
						if(document.getElementById('endTime').value!=''){
							if(document.getElementById('radio3').checked==false){
								if(bookwithpayment==0){
									if(document.getElementById('treatmentEpisode').value>0){
										if (new Date(grefenddate) < new Date(currentdate)) {
											
											document.getElementById('treatmentEpisode').value = 0;
											$("#treatmentEpisode").trigger("chosen:updated");
							
											document.getElementById('sessionDetail').innerHTML="";
											document.getElementById('treatmentEpisodeError').innerHTML="";
											
											jAlert('error', 'Please check treatment episode date!!', 'Error Dialog');
											setTimeout(function() {
												$("#popup_container").remove();
												removeAlertCss();
											}, alertmsgduration);
										}else{
											saveAppointmentSlot(id);
//											jQuery.noConflict(); 
											$('#appointment').modal("hide");
										}
									}else{
										saveAppointmentSlot(id);
//										jQuery.noConflict(); 
										$('#appointment').modal("hide");
									}
									
								}else{
									document.getElementById('checktext').innerHTML="<input type='checkbox' id='check' name='check' checked class='hidden'> "
									if(document.getElementById('treatmentEpisode').value>0){
										if (new Date(grefenddate) < new Date(currentdate)) {
											
											document.getElementById('treatmentEpisode').value = 0;
											$("#treatmentEpisode").trigger("chosen:updated");
							
											document.getElementById('sessionDetail').innerHTML="";
											document.getElementById('treatmentEpisodeError').innerHTML="";
											
											jAlert('error', 'Please check treatment episode date!!', 'Error Dialog');
											setTimeout(function() {
												$("#popup_container").remove();
												removeAlertCss();
											}, alertmsgduration);
										}else{
										
											$('#takepaymentmodel').modal("show");
											
											getTakePaymentCharge();
										}
									}else{
									    
										$('#takepaymentmodel').modal("show");
										
										getTakePaymentCharge();
									}
									
								}
							}else{
								if(document.getElementById('otplaned').value==0){
									jAlert('error', 'Please Select Category!!', 'Error Dialog');
									setTimeout(function() {
										$("#popup_container").remove();
										removeAlertCss();
									}, alertmsgduration);
								}
								else if(document.getElementById('otprocedureplaned').value==0){
									jAlert('error', 'Please Select Procedure!!', 'Error Dialog');
									setTimeout(function() {
										$("#popup_container").remove();
										removeAlertCss();
									}, alertmsgduration);
								}
								else if(document.getElementById('otsurgeonname').value==0){
									jAlert('error', 'Please Select Surgeon Doctor!!', 'Error Dialog');
									setTimeout(function() {
										$("#popup_container").remove();
										removeAlertCss();
									}, alertmsgduration);
								}/*else if(document.getElementById('otanesthesia').value==0){
									jAlert('error', 'Please Select Anesthesia Doctor!!', 'Error Dialog');
									setTimeout(function() {
										$("#popup_container").remove();
										removeAlertCss();
									}, alertmsgduration);
								}*/else if(document.getElementById('treatmentEpisode').value>0){
										if (new Date(grefenddate) < new Date(currentdate)) {
											
											document.getElementById('treatmentEpisode').value = 0;
											$("#treatmentEpisode").trigger("chosen:updated");
							
											document.getElementById('sessionDetail').innerHTML="";
											document.getElementById('treatmentEpisodeError').innerHTML="";
											
											jAlert('error', 'Please check treatment episode date!!', 'Error Dialog');
											setTimeout(function() {
												$("#popup_container").remove();
												removeAlertCss();
											}, alertmsgduration);
										}else{
											if(bookwithpayment==0){
												saveAppointmentSlot(id);
												$('#appointment').modal("hide");
											}else{
												$('#takepaymentmodel').modal("show");
												getTakePaymentCharge();
											}
											
										}
								
								}else{
									if(bookwithpayment==0){
											saveAppointmentSlot(id);
//											jQuery.noConflict(); 
											$('#appointment').modal("hide");
										}else{
											$('#takepaymentmodel').modal("show");
											getTakePaymentCharge();
										}
								}
								

							}
							
						}else{
							jAlert('error', 'Please enter valid time', 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}
					}
					return true;
				}
				
				
	    		
				
	    	}
		
		}
		
		
		
	
	};
	
	$.fn.blockSave = function(id,starttime){
		var chk = 0;
		document.getElementById('blockapmtDurationError').innerHTML="";
		document.getElementById('blocksTimeError').innerHTML="";
		//document.getElementById('blocknotesError').innerHTML="";
		

		if(document.getElementById('blockapmtDuration').value == 0){
			var l1 = document.createElement("label");
         	l1.innerHTML = "Plese Select Duration.";
  	      	document.getElementById('blockapmtDurationError').appendChild(l1);
  	        chk=1;
		}else{
			
			//check start time
			var stime = document.getElementById('blocksTime').value;
			
			var initStime = stime+":"+"00";
			starttime = starttime+":"+"00";
			
			if(id > 0){
				starttime = slotstarttime;
			}
			
			//check end time
			var duration = document.getElementById('blockapmtDuration').value;
			var total = getTimeTotal(duration,stime);
			
			total = total+":"+"00";
			var tempglobalEndTime = etime+":"+"00";
			
			var totalstarttime = total,
	    	endtime = tempglobalEndTime;
	    	
	    	if(initStime < starttime){
				 	var l1 = document.createElement("label");
		         	l1.innerHTML = "Please enter valid start time.";
		  	      	document.getElementById('blocksTimeError').appendChild(l1);
		  	        chk=1;

			}else if(totalstarttime > endtime){
	    		 var l1 = document.createElement("label");
		         l1.innerHTML = "Please enter valid time.";
		  	     document.getElementById('blocksTimeError').appendChild(l1);
		  	     chk=1;

	    	}
			/*else if(document.getElementById('blocknotes').value == 0){
	    		var l1 = document.createElement("label");
		         l1.innerHTML = "Please enter Notes.";
		  	     document.getElementById('blocknotesError').appendChild(l1);
		  	     chk=1;

	    	}*/
			
			else{
	    		var commencing = document.getElementById('date').value;
				var currentdate = getCurrentDate();
				//commencing = dateFormat(commencing);
				if(chk==1){
					return false;
				}
				else{
				
				
				/*if (new Date(commencing) < new Date(currentdate)) {
		    		 jAlert('error', 'Please contact Admin to add or edit this slot!!', 'Error Dialog');

				}else if((new Date(commencing).getTime() == new Date(currentdate).getTime()) && (initStime < getCurrentTime())){
		    		 jAlert('error', 'Please contact Admin to add or edit this slot!!', 'Error Dialog');

				}*/ if(checkEventExist=='true'){
					jAlert('error', 'This Slot Already Booked, Please Select Another Time !!', 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				}else{
					
					if(document.getElementById('blockendTime').value!=''){
						saveBlockAppointmentSlot(id);
		    			$('#blockdiv').modal("hide");
					}else{
						jAlert('error', 'Please enter valid time', 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					}
	    			
	    		}
				}
				
	    	}
	    	
		}
		
		
	
	};
	
	$.fn.cancelSlot = function(){
	
		//disablePopup(); 
	
	};
	
	
}); // jQuery End

var savewhopay = "";

function saveAppointmentSlot(id){
	
	  var stafflistid = 0;
			     $('.casees').each(function() { //loop through each checkbox
			        // this.checked = true;  //select all checkboxes with class "checkbox1" 
			        if(this.checked==true){
			        	stafflistid = stafflistid + ',' + this.value;
			        }
			         
			     });
			     
			     var isot = document.getElementById('radio3').checked;
			     var selectedot = document.getElementById('selectedotroom').value;
	
	
	var slotId = document.getElementById('slotId').value;
	var procedureid=document.getElementById('procedureid').value;
	commencing = document.getElementById('date').value;
	var location = document.getElementById('location').value;
	var room = document.getElementById('room').value;
	var sTime = document.getElementById('sTime').value;
	var endTime = document.getElementById('endTime').value;
	var apmtDuration = document.getElementById('apmtDuration').value;
	 diaryuserId = document.getElementById('diaryUserId').value;
	var client = document.getElementById('client').value;
	var dept = document.getElementById('dept').value;
	//var apmtType = $("#apmtType option:selected").text();
	var apmtType = document.getElementById('apmtType').value;
	var notes = document.getElementById('notes').value;
	var diaryUser = document.getElementById('user').value;
	var clientId = document.getElementById('clientId').value;
	var treatmentEpisodeId = document.getElementById('treatmentEpisode').value;
	
	//setting radio button
	if(actionType==2){
		var rdstime = sTime.split(":");
		var rrr = parseInt(rdstime[0]) * 1;
		document.getElementById('rd'+rrr).checked =true;
	}
	
	
	if(treatmentEpisodeId==""){
		treatmentEpisodeId = 0;
	}
	
	var condition = document.getElementById('condition').value;
	
	var weekNumber = document.getElementById('weekNumber').value;
	var wholeweek = document.getElementById('wholeweek').checked;
	
	var monday = document.getElementById('weekName-1').checked;
	var tuesday = document.getElementById('weekName-2').checked;
	var wednesday = document.getElementById('weekName-3').checked;
	var thursday = document.getElementById('weekName-4').checked;
	var friday = document.getElementById('weekName-5').checked;
	var saturday = document.getElementById('weekName-6').checked;
	var sunday = document.getElementById('weekName-7').checked;
	
	var whopay = "";
	if(document.getElementById('paybypatient1').checked == true){
		
		whopay = "Third Party";
	}else{
		
		whopay = "Client";
	}
	
	savewhopay = whopay;
	
	var invcetype = document.getElementById('invcetype').value;
	var howpaid = document.getElementById('howpaid').value;
	var upino=document.getElementById('upino').value;
	var remark=document.getElementById('remark').value;
	var totalamount = document.getElementById('totalamount').value;
	var discount = document.getElementById('discount').value;
	var payAmount = document.getElementById('payAmount').value;
	var disctype = document.getElementById('disctype').value;
	var bnkname = document.getElementById('bnkname').value;
	
	//var paymentNote = document.getElementById('paymentNote').value;
	
	
	//ot variables
	var otplaned = document.getElementById('otplaned').value;
	var otprocedureplaned = document.getElementById('otprocedureplaned').value;
	var otsurgeonname = document.getElementById('otsurgeonname').value;
	var otanesthesia = document.getElementById('otanesthesia').value;
	var otipdnos = document.getElementById('otipdno').innerHTML;
	
	var psurcharge = document.getElementById('psurcharge').value;
	var panetcharge = document.getElementById('panetcharge').value;
	var anifees = document.getElementById('anifees').value;
	var sic = document.getElementById('sic').value;
	var assistaffcharge = document.getElementById('assistaffcharge').value;
	var height = document.getElementById('height').value;
	var weight = document.getElementById('weight').value;
	var bmi = document.getElementById('bmi').value;
	var headcir = document.getElementById('headcir').value;
	var tempr = document.getElementById('tempr').value;
	var hiddenclientid=document.getElementById('hiddenclientid').value;
	var allergynotes="";
	var allergy="";
	if(hiddenclientid=="simpliclinic"){
	  allergynotes=document.getElementById('allergynotes').value;
	//var allergy=document.getElementById('allergy').value;
	  allergy = $("input:radio[name=allergy]:checked").val();
	}else{
		 allergynotes="";
	     allergy="";
	}
	var opdotcharge =  "0";
	var opdotregcharge ="0";
	if(document.getElementById('opdotcharge')){
		opdotcharge = document.getElementById('opdotcharge').value;
	}
	
	if(document.getElementById('opdotregcharge')){
		opdotregcharge = document.getElementById('opdotregcharge').value;
	}
	
	var url = "saveAppoinmentBookAppointmentAjax?slotId="+slotId+"&commencing="+commencing+"&location="+location+"&room="+room+"&sTime="+sTime+"&endTime="+endTime+"&apmtDuration="+apmtDuration+"&diaryuserId="+diaryuserId+"&client="+client+"&dept="+dept+"&apmtType="+apmtType+"&notes="+notes+"&diaryUser="+diaryUser+"&selectedid="+id+"&clientId="+clientId+"&treatmentEpisodeId="+treatmentEpisodeId+"&weekNumber="+weekNumber+"&wholeweek="+wholeweek+"&monday="+monday+"&tuesday="+tuesday+"&wednesday="+wednesday+"&thursday="+thursday+"&friday="+friday+"&saturday="+saturday+"&sunday="+sunday+"&condition="+condition+"&whopay="+whopay+"&stafflistid="+stafflistid+"&isot="+isot+"&selectedot="+selectedot+"&bookwithpayment="+bookwithpayment+"&invcetype="+invcetype+"&howpaid="+howpaid+"&totalamount="+totalamount+"&discount="+discount+"&payAmount="+payAmount+"&otplaned="+otplaned+"&otprocedureplaned="+otprocedureplaned+"&otsurgeonname="+otsurgeonname+"&otanesthesia="+otanesthesia+"&otipdnos="+otipdnos+"&disctype="+disctype+"&psurcharge="+psurcharge+"&panetcharge="+panetcharge+"&anifees="+anifees+"&sic="+sic+"&bnkname="+bnkname+"&assistaffcharge="+assistaffcharge+"&opdotcharge="+opdotcharge+"&opdotregcharge="+opdotregcharge+"&height="+height+"&weight="+weight+"&bmi="+bmi+"&headcir="+headcir+"&tempr="+tempr+"&allergynotes="+allergynotes+"&allergy="+allergy+"&upino="+upino+"&remark="+remark+"&procedureid="+procedureid+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveAppointmentSlotRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function saveAppointmentSlotRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//alert("Appointment slot has been set successfully!!");
			
			//setJsoData1(diaryUserId,commencing);
			//goPreview(tempusername,tempdate,tempyear,tempdiaryuserid,temptdcode)
			//window.location.reload();
			  document.getElementById('height').value="";
			  document.getElementById('weight').value="";
			  document.getElementById('bmi').value="";
				document.getElementById('headcir').value="";
				document.getElementById('tempr').value="";
				var hiddenclientid=document.getElementById('hiddenclientid').value;
				if(hiddenclientid=="simpliclinic"){
			    document.getElementById('noallergy').checked=true;
			     $("#allergynotes").hide();
              document.getElementById('allergynotes').value="";
			}
			
			 var temp = apmuserlist.split(',');
			 
			 if(temp.length==1){
				 if(editAppointId==0){
						//jAlert('success', 'Appointment Saved Successfully,Click on Go button to view update.', 'Success Dialog');
						tempAlert("Appointment has been saved successfully",5000);
					}else{
						
						
						//jAlert('success', 'Appointment Modified Successfully.Click on Go button to view update.', 'Success Dialog');
						tempAlert("Appointment has been Modified successfully",5000);
					}
				 
			 }
			 
			 var updatedAppointmentData = req.responseText;
			 var stemp = updatedAppointmentData.split('/');
			 
			 saveAppointmntid = stemp[6];
			 savedclient = stemp[7];
			 editClientName = stemp[4];
			// getApmLoggedInUserList();
			 
			 apmuserlist = stemp[11];
			 var temp = apmuserlist.split(',');
			 
			 var pur = updatedAppointmentData.split('/');
			 var utype = pur[9];
			 if(utype==5){
				sendAppointmentAutoMaticEmail(saveAppointmntid);
			 }else{
				 
				// sendAutoSMSAjax(saveAppointmntid);
				
				if(statusbookwithpayment==1){
					$( "#vieworprintnvoice" ).modal( "show" );
				} else {
					$( "#autosendemailpopup" ).modal( "show" );
				}
			 }
			
			 setCommonAction();
		
			
			
			 
			 for(i=0;i<temp.length;i++){
					if(temp[i]!=$.cometChat.loginUserName){
						if(editAppointId==0){
							$.cometChat.send("apmt_saved", temp[i],updatedAppointmentData);
						}else{
							$.cometChat.send("apmt_modified", temp[i],updatedAppointmentData,1);
						}
						
					}
					
				}
			 
			 
			 
			// setTpAppointmentTYpe(savewhopay);
			// mobileNotification(updatedAppointmentData);
			 
			
			 document.getElementById("notavailable_form").reset();
			
			
			
			
		}
	}
}

function mobileNotification(data){
	var tempt = data.split('/');
	$.ajax({
		url: "json/gcmnotify.jsp?data="+data+"&action=single&userid="+tempt[11]+"",
		dataType : "json",
		success : function(json) {
			//alert("Your JSON : " + JSON.stringify(json));
			
			var data = JSON.parse(JSON.stringify(json));        
                     $.each(data,function(row,store)  {    
                        $.each(store,function(key,value) {
                            //id = value.id;
                            //name = value.name;
                           // address = value.address;
                            //age = value.age;
                           // alert(id + " : " + name + "  : " +address + " : " + age); 
                          	
                    		
                        });        
                     });
			
		}
	});
			
	
}

//save block

function saveBlockAppointmentSlot(id){
	//alert(id);
	
	var slotId = document.getElementById('slotId').value;
	commencing = document.getElementById('commencing').value;
	var location = document.getElementById('blocklocation').value;
	var room = document.getElementById('blockroom').value;
	var sTime = document.getElementById('blocksTime').value;
	var endTime = document.getElementById('blockendTime').value;
	var apmtDuration = document.getElementById('blockapmtDuration').value;
	if(document.getElementById('diaryUsersss')){
		diaryuserId = document.getElementById('diaryUsersss').value;
	}else{
		diaryuserId = tempDiaryUseridsss;
	}
	
	var reasonforblock = document.getElementById('reasonforblock').value;
	var notes = document.getElementById('blocknotes').value;
	var diaryUser = tempDiaryUserName;
	
	var weekNumber = document.getElementById('blockweekNumber').value;
	var wholeweek = document.getElementById('blockwholeweek').checked;
	
	var monday = document.getElementById('blockWeekName-1').checked;
	var tuesday = document.getElementById('blockWeekName-2').checked;
	var wednesday = document.getElementById('blockWeekName-3').checked;
	var thursday = document.getElementById('blockWeekName-4').checked;
	var friday = document.getElementById('blockWeekName-5').checked;
	var saturday = document.getElementById('blockWeekName-6').checked;
	var sunday = document.getElementById('blockWeekName-7').checked;
	
	
	var status = document.getElementById('status').value;
	var url = "saveBlockNotAvailableSlot?slotId="+slotId+"&commencing="+commencing+"&location="+location+"&room="+room+"&sTime="+sTime+"&endTime="+endTime+"&apmtDuration="+apmtDuration+"&diaryuserId="+diaryuserId+"&reasonforblock="+reasonforblock+"&notes="+notes+"&diaryUser="+diaryUser+"&status="+status+"&selectedid="+id+"&weekNumber="+weekNumber+"&wholeweek="+wholeweek+"&monday="+monday+"&tuesday="+tuesday+"&wednesday="+wednesday+"&thursday="+thursday+"&friday="+friday+"&saturday="+saturday+"&sunday="+sunday+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveBlockRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function saveBlockRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//alert("Block slot has been set successfully!!");
			
			//setJsoData1(diaryUserId,commencing);
			//goPreview(tempusername,tempdate,tempyear,tempdiaryuserid,temptdcode)
			//window.location.reload();
			
			
			
			
			getApmLoggedInUserList();
			setCommonAction();
			 var temp = apmuserlist.split(',');
			 
			 
				 if(temp.length==1){
								 
					 if(editAppointId==0){
						 //jAlert('success', 'Appointment Saved Successfully.', 'Success Dialog');
						tempAlert("Slot Blocked Successfully",5000);
					}else{
						 //jAlert('success', 'Appointment Modified Successfully.', 'Success Dialog');
						tempAlert("Blocked Slot Modified Successfully",5000);
					}		 
				 }
				 
				 updatedAppointmentData = "Block Appointment";
			 
				for(i=0;i<temp.length;i++){
					if(temp[i]!=$.cometChat.loginUserName){
						if(editAppointId==0){
							$.cometChat.send("apmt_saved", temp[i],updatedAppointmentData);
						}else{
							$.cometChat.send("apmt_modified", temp[i],updatedAppointmentData);
						}
					}
					
				}
				
				
				
			document.getElementById("notavailable_form").reset();
		}
	}
}

function completeApmt(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	//if(editArrivedStatus == 2){
		$('#modifyPopup').modal( "hide" );
//		jQuery.noConflict();
//		$('#completeAppointmentDivId').modal( "show" );
		if(document.getElementById("yuviloader")){
		document.getElementById("yuviloader").style.display = "";
		}
		var cookieUserName=read_cookie("cookieUserName");
		var cookieStarttime=read_cookie("cookieStarttime");
		var cookieDuration=read_cookie("cookieDuration");
		var cookiePractitioner=tempDiaryUserName;
		var cookieapmtType=read_cookie("cookieapmtType");
		var cookiePractitionerId=read_cookie("cookiePractitionerId");
		var cookieClientId=read_cookie("cookieClientId"); 
		var cookiecommencing=read_cookie("cookiecommencing");
		var cookieCharge = read_cookie("cookieCharge");
		if(cookieCharge==null){
			cookieCharge=0;
		}
			
		var cookieSelectedAppointmentid = editAppointId;

		
		
		document.getElementById('selectedApmtType').value = editapmttypetext;
		document.getElementById('appointmentcharge').value = currencySign+cookieCharge;
			
		getTreatmentEpisodeDetailsAjax();
		
		
		
		var doller = "$";
		document.getElementById('completeAmtTitle').innerHTML = "Complete Appointment for "+cookieUserName+" on "+cookiecommencing+" at "+cookieStarttime+"";
		document.getElementById('compPractName').innerHTML = ""+cookiePractitioner+" at";
		document.getElementById('complocationid').value = loc;
		
		//document.getElementById('durationdiv').innerHTML = ""+cookieUserName+","+cookieStarttime+" - "+cookieDuration+" min Appointment with "+cookiePractitioner+" is Complete";
		//document.getElementById('chargediv').innerHTML = "The charge has been created To "+cookieUserName+" "+editapmttypetext+" "+currencySign+" "+cookieCharge+"   ";
		//document.getElementById('chargetodiv').innerHTML = cookieUserName;
		
		document.getElementById('clientId').value = cookieClientId;
		document.getElementById('practitionerName').value = cookiePractitioner;
		document.getElementById('practitionerId').value = cookiePractitionerId;
		$('#completeAppointmentDivId').modal( "show" );
	/*}else{
		 jAlert('error', 'Cannot complete appointment until client is being seen!!');
	}*/
    											
											
											
}	

function emailSend(){
//alert(commencing);
/*alert(diaryuserId);
alert(editClientName);
alert(patientId);
alert(editStartTime);
alert(editDuration);
alert(loc);
alert(practitionerEmailId);
alert(clientEmailId);*/
alert(editAppointId);	
alert(loc);
var url = "emailSendNotAvailableSlot?practitionerName="+tempDiaryUserName+"&practitionerId="+diaryuserId+"&clientName="+editClientName+"&clientId="+patientId+"&startTime="+editStartTime+"&duration="+editDuration+"&location="+loc+"&practitionerEmailId="+practitionerEmailId+"&clientEmailId="+clientEmailId+"&commencing="+commencing+"";

   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = emailSendRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function emailSendRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
            tempAlert("Email Send successfully!!",5000);
            $('#reminderPopup').modal("hide");
			
			
		}
	}
}


//delete block slot
function deleteBlockSlot(editAppointId){
	var cancelApmtNote = document.getElementById('cancelApmtNote').value;
	var url = "deleteBookAppointmentAjax?selectedid="+editAppointId+"&cancelApmtNote="+cancelApmtNote+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = deleteBlockSlotRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function deleteBlockSlotRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			tempAlert("Blocked Slot Deleted Successfully",5000);
			//editAppointId = 0;
			
			
			 $('#blockdiv').modal("hide");
			$('#modifyPopup').modal("hide");
			$('#blockapmtdaytodsypopup').modal( "hide" );
			
			//getApmLoggedInUserList();
			//setCommonAction();
			
			showdisplaynewopd();
			
			/* var temp = apmuserlist.split(',');
				for(var i=0;i<temp.length;i++){
					if(temp[i]!=$.cometChat.loginUserName){
						$.cometChat.send("apmt_modified", temp[i],"");
					}
					
				}*/
		}
	}
}


//drag and drop javascript

function submitDragAndDropAjax(practitionerid,evalTime,availableSlotID,endTime,location){
	var commencingDate = document.getElementById('commencing').value;
	var url = "dragAndDropNotAvailableSlot?practitionerid="+practitionerid+"&evalTime="+evalTime+"&availableSlotID="+availableSlotID+"&endTime="+endTime+"&commencingDate="+commencingDate+"&location="+location+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = submitDragAndDropAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function submitDragAndDropAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//tempAlert("Appointment Dropped Successfully",5000);
			editAppointId = 0;
			 $( "#modifyPopup" ).modal( "hide" );
			
			 
			 var updatedAppointmentData = req.responseText;
			 
			 getApmLoggedInUserList();
			 //alert(apmuserlist)
			 
			 setCommonAction();
			 
			 var temp = apmuserlist.split(',');
			 
			 if(temp.length==1){
				 tempAlert("Appointment Dropped Successfully",5000);
				 
			 }
			
			 
				for(i=0;i<temp.length;i++){
					if(temp[i]!=$.cometChat.loginUserName){
						$.cometChat.send("apmt_modified", temp[i],updatedAppointmentData);
					}
					
				}
				
				
				 var stemp = updatedAppointmentData.split('/');
				 
				 saveAppointmntid = stemp[6];
				 savedclient = stemp[7];
				 editClientName = stemp[4];
				 
				// sendAppointmentAutoMaticEmail(saveAppointmntid);
				 
				// $( "#autosendemailpopup" ).modal( "show" );
					
				
				
				
		
			
		}
	}
	
}



function renitilizeWrapdedEvent(el,apmtid){
	
	 var container = document.getElementById(el);
	    var childArray = container.children;
	    
	  /* document.getElementById('new15s').onclick = function() {
		   getrewrapedDataAjax('new15s');
	    }*/
	    
	   
	    
	    for(k = 0; k < childArray.length; k++){
	    	var className = $('#'+childArray[k].id+'').attr('class');
	    	var eventid = "";
	    	if(className=='new'){
	    		 eventid = childArray[k].id;
	    		 
	    		  demofunction(eventid);
	    	
	    	}
	    }
	    
	    var but = document.getElementById(apmtid);
		but.setAttribute("onclick",getrewrapedDataAjax(apmtid));
	   
}


function demofunction(eventid){
	
	
	document.getElementById(eventid).onclick = function() {
		getrewrapedDataAjax(eventid);
		
	}
}

function getrewrapedDataAjax(myString){
	
	myString = myString.replace(/[^\d]/g, ''); 
	//alert(myString)
	
	var url = "rewrapedBookAppointmentAjax?id="+myString+" ";
	
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getrewrapedDataAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}


function getrewrapedDataAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var str = req.responseText;
			
			//alert(str)
			
			var temp = str.split("#");
			
			var id = temp[0];
			var clientname = temp[1];
			var apmttype = temp[2];
			var starttime = temp[3];
			var endtime = temp[4];
			var duration = temp[5];
			var status = temp[6];
			var notes = temp[7]
			var arrivedstatus = temp[8];
			var dna = temp[9];
			
			if(dna=="true"){
				dna = true;
			}else{
				dna = false;
			}
			
			var clientId = temp[10];
			var commencing = temp[11];
			var userid =  temp[12];
			var charge = temp[13];
			var reasonforblock = temp[14];
			var treatmentepisodeid = temp[15];
			var apmttypetext = temp[16];
			var clientname = temp[17];
			var usedsession = temp[18];
			var tptypeid = temp[19];
			var tpnameid = temp[20];
			var practitionerEmail = temp[21];
			var clientEmail = temp[22];
			loc = temp[23];
			var location = temp[24];
			var conditionid = temp[25];
			var whopay = temp[26];
			var iscompleted = temp[27];
			var chargecompleted = temp[28];
			tempDiaryUserName = temp[29];
			var diaryuserid = temp[30];
			var tpname = temp[31];
			var loggedemail = temp[32];
			var otid = temp[33];
			imagename=temp[34];
			height=temp[35];
			weight=temp[36];
			bmi=temp[37];
			pulse=temp[38];
			sysbp=temp[39];
			diabp=temp[40];
			
			//ot values
			var plan=temp[41];
			var procedure=temp[42];
			var surgeon=temp[43];
			var anesthesia=temp[44];
			var ipdno=temp[45];
			var wardid=temp[46];
			var asistdoclist=temp[47];
			var token=temp[48];
			
			newsugarfasting = temp[49];
			newpostmeal = temp[50];
			
			var luhid = temp[51];
			var uhid = temp[52];
			newtemprature=temp[53];
			newspo=temp[54];
			bsa=temp[55];
			wraperDivId = 'new'+id+'s';
			if(newtemprature==null){
				newtemprature='';
			}
			if(newspo==null){
				newspo='';
			}
			//alert(req.responseText);
			//setModifyPopup(status, starttime, endtime, duration, clientname, notes, apmttype, id, arrivedstatus, dna, userid, clientId, commencing, practitionerEmail, clientEmail, charge, commencing, starttime, treatmentepisodeid, iscompleted, diaryuserid, apmttypetext, location, conditionid, tptypeid, tpnameid, whopay);
			
			
			 //modify popup
			 if(iscompleted == "false"){
				 
				/* var but = document.getElementById(wraperDivId);
		    		but.setAttribute("onclick",setModifyPopup(status, starttime, endtime, duration, clientname, notes, apmttype, id, arrivedstatus, dna, userid, clientId, commencing, practitionerEmail, clientEmail, charge, commencing, starttime, treatmentepisodeid, iscompleted, diaryuserid, apmttypetext, location, conditionid, tptypeid, tpnameid, whopay,tpname));*/
				
				 document.getElementById(wraperDivId).onclick = function() {

					 if(actionType==5){
						 if(luhid!=''){
							 if(luhid==uhid){
								 $( "#appointment" ).modal( "hide" );
								 $( "#atppopup" ).modal( "hide" );
								 wraperDivId = 'new'+id+'s';
								 	
								 		setModifyPopup(status, starttime, endtime, duration, clientname, notes, apmttype, id, arrivedstatus, dna, userid, clientId, commencing, practitionerEmail, clientEmail, charge, commencing, starttime, treatmentepisodeid, iscompleted, diaryuserid, apmttypetext, location, conditionid, tptypeid, tpnameid, whopay,tpname,imagename,height,weight,bmi,pulse,sysbp,diabp,plan,procedure,surgeon,anesthesia,ipdno,wardid,asistdoclist,newsugarfasting,newpostmeal,newtemprature,newspo,bsa);
									$('#modifyPopup').modal( "hide" );
									
									if(isotpconfirmd==0){
										$('#puremodifyPopup').modal( "hide" );
										 $( "#puresevaclientdetailsdiv" ).modal( "show" );
									}else{
										 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
										$('#puremodifyPopup').modal( "show" );
									}
									
									
							}else{
								editAppointId='invalid';
								 $( "#appointment" ).modal( "hide" );
								 $( "#atppopup" ).modal( "hide" );
								 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
								 document.getElementById(wraperDivId).onclick = "";
							}
						 }else{
							 editAppointId='invalid';
							 $( "#appointment" ).modal( "hide" );
							 $( "#atppopup" ).modal( "hide" );
							 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
							 document.getElementById(wraperDivId).onclick = "";
						 }
						
					 }else{
						 $( "#appointment" ).modal( "hide" );
						 wraperDivId = 'new'+id+'s';
						 
						 if(otid==0){
							 setModifyPopup(status, starttime, endtime, duration, clientname, notes, apmttype, id, arrivedstatus, dna, userid, clientId, commencing, practitionerEmail, clientEmail, charge, commencing, starttime, treatmentepisodeid, iscompleted, diaryuserid, apmttypetext, location, conditionid, tptypeid, tpnameid, whopay,tpname,imagename,height,weight,bmi,pulse,sysbp,diabp,plan,procedure,surgeon,anesthesia,ipdno,wardid,asistdoclist,newsugarfasting,newpostmeal,newtemprature,newspo,bsa);
						}else{
							showotmodifypopuop(id,clientId,clientname,location,whopay,imagename,height,weight,bmi,pulse,sysbp,diabp,temprature,spo,bsa);
							
						}
					 }	
					
						
					}
			 }
			 else if(iscompleted == "true"){

				 patientId = clientId;
				 editClientName = clientname;
				 editwhopay = whopay;
				
				 document.getElementById(wraperDivId).onclick = function() {
					 wraperDivId = 'new'+id+'s';

					 $( "#appointment" ).modal( "hide" );	
					 
					 if(chargecompleted=="false"){
						 //editCompleteApmt(value.id);
						 patientId = clientId;
						 
						 pppid = clientId;
						 pppcname  = clientname;
						 pppwhopay  = whopay;
						 ppptepisode  = treatmentepisodeid;
						 
						 editAppointId = id;
						 editwhopay = whopay;
						 editClientName = clientname;
						 loc = temp[23];
						 diaryuserId = userid;
						 editcondition_id = conditionid;
						 editTreatmentEpisode = treatmentepisodeid;
						 if(actionType==5){
							 editAppointId='invalid';
							 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
							 $( "#appointment" ).modal( "hide" );
							 $( "#atppopup" ).modal( "hide" );
						 }else{
							 openCompleteActionPopup(id,height,weight,bmi,pulse,sysbp,diabp,temprature,spo,bsa); 
						 }
						 
					 }else{
						 patientId = clientId;
						 
						  pppid = clientId;
						 pppcname  = clientname;
						 pppwhopay  = whopay;
						 ppptepisode  = treatmentepisodeid;
						 
						 editAppointId = id;
						 editwhopay = whopay;
						 editClientName = clientname;
						 loc = temp[23];
						 diaryuserId = userid;
						 editcondition_id = conditionid;
						 editTreatmentEpisode = treatmentepisodeid;
							//document.getElementById('modifyClient2').innerHTML = '<a href="printProfileClient?selectedid='+patientId+'" target="blank">'+editClientName+'</a> (<a href = "ClientLog?id='+patientId+'" target="blank"> Log </a>)';
							
						if(imagename==null){
						   document.getElementById('modifyClient2').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;height: padding:5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Form</p></a></div>';
						}					
						else if(imagename==''){			
						   document.getElementById('modifyClient2').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding:5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Form</p></a></div>';
						} else {
						   document.getElementById('modifyClient2').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="liveData/'+imagename+'" style="width: 100% !important;"/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Form</p></a></div>';
						}
						 //jAlert('info', 'Invoice has been created for this appointment', 'Info Dialog');
						 if(actionType==5){
							 editAppointId='invalid';
							 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
							 $( "#appointment" ).modal( "hide" );
							 $( "#atppopup" ).modal( "hide" );
						 }else{
						     document.getElementById("height6").innerHTML=height;			
							 document.getElementById("weight6").innerHTML=weight;
			 				 document.getElementById("bmi6").innerHTML=bmi;
			                 document.getElementById("pulse6").innerHTML=pulse;
							 document.getElementById("sysbp6").innerHTML=sysbp;
							 document.getElementById("diabp6").innerHTML=diabp;
							 document.getElementById('sugarfasting6').innerHTML = newsugarfasting;
							document.getElementById('postmeal6').innerHTML = newpostmeal;
							document.getElementById('temprature6').innerHTML = newtemprature;
							document.getElementById('spo6').innerHTML = newspo;
							document.getElementById('bsa6').innerHTML = bsa;
							 $( "#recordpaymentpopup" ).modal( "show" );
							 if(isotoropd==1){
									document.getElementById('modify').className="thumbnail";		
									document.getElementById('mvmdappointment').className="hidden";
								}
						 }
						 
					 }
					 
				
				 }
			 }

			 else{

				 document.getElementById(wraperDivId).onclick = function() {
					 
					 if(actionType==5){
						 editAppointId='invalid';
						 $( "#puresevaclientdetailsdiv" ).modal( "hide" );
						 $( "#appointment" ).modal( "hide" );
						 $( "#atppopup" ).modal( "hide" );
					 }else{
						 $( "#appointment" ).modal( "hide" );
						  $( "#blockdiv" ).modal( "hide" );
					 }
					
					 
				 }
			 }
			
			
		}
	}
}

var initslotid = "";
var initstarttime = "";
var initendtime = "";
var initlocation = "";
var inittitle = "";
var initapmduration = "";
var initdiaryusername = "";
var initdiaryuserid = "";
var initcommencing = "";
var initclassName = "";

function setMultiLocationDataAjax(title,endtime,userid,commencing,className){
	
	inittitle = title;
	initclassName = className;
	initcommencing = commencing;
	
	var url = "multilocBookAppointmentAjax?title="+title+"&endtime="+endtime+"&userid="+userid+"&commencing="+commencing+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setMultiLocationDataAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}


function setMultiLocationDataAjaxRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
			var data = req.responseText;
			var temp = data.split("/");
			
			 initslotid = temp[0];
			 initstarttime = temp[1];
			 initendtime = temp[2];
			 initlocation = temp[3];
			 var apmtduration = initapmduration;
			 var diaryusername = initdiaryusername;
			 var diaryUserId = initdiaryuserid;
			 var disciplineid = temp[4];
			 
			 document.getElementById('diciplineName').value = disciplineid;
			 
			 document.getElementById('bookApmtLbl').innerHTML = "New Appointment";
			
			editCommencing = initcommencing;
			
			document.getElementById('diaryUserId').value = initdiaryuserid;
			
			//set cookie commencing
			document.cookie = "cookiecommencing=" + initcommencing;
			document.cookie = "cookiePractitionerId=" + initdiaryuserid;
			
			document.getElementById('client').value = "";
			document.getElementById('notes').value = "";
			
			
			document.getElementById('apmtType').value = 0;
			$("#apmtType").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			document.getElementById('condition').value = disciplineid;
			$("#condition").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
	         	
			 $(this).MessageBox(initslotid,initstarttime,initendtime,apmtduration,initlocation,diaryusername,diaryUserId,inittitle);
			 
			 document.getElementById('date').value = initcommencing;
			
		
		
			
			//block appointment initilization
			 if(initclassName=='cssClass1' || initclassName=='yellow'){
				 editAppointId = 0;
			 }
			
			 var bdivtime = inittitle; 
			 var tepmtile =  bdivtime.split(' ');
			 bdivtime = tepmtile[0];
			
			diaryUser1 = initslotid;
			stime = initstarttime;
			blockDivTime = bdivtime;
			document.getElementById('blocksTime').value = bdivtime;
			etime = initendtime;
			document.getElementById('blockendTime').value = etime;
			loc = initlocation;
			id1 = initdiaryuserid;
			tempDiaryUserName = initdiaryusername;
			editCommencing = initcommencing;
			
			initdata = 0;
				
	         }
		}
	
}


function initilizeTdElement(el,slotid,starttime,endtime,apmtduration,location,diaryusername,userid,commencing){
	
	
		initcommencing = commencing;
		
		 var container = document.getElementById(el);
		    var childArray = container.children;
		    
		    for(var i = 0; i < childArray.length; i++){
		       // results.innerHTML += childArray[i].id+"<br />";
		        //childArray[i].style.color = "#F00";
		    	var className = $('#'+childArray[i].id+'').attr('class');
		    	
	    		
		    	document.getElementById(childArray[i].id).onclick = function() {
		    		
		    		//if(className=='cssClass1' || className=='yellow'){
			    		setMultiLocationDataAjax(this.title,endtime,userid,commencing,className);
			    		
			    		slotid = initslotid;
			    		starttime = initstarttime;
			    		endtime = initendtime;
			    		location = initlocation;
			    		apmtduration =  initapmduration;
			    		initdiaryusername = diaryusername;
			    		initdiaryuserid = userid;
			    		
			    		
			    		
						//$(this).MessageBox(slotid,starttime,endtime,apmtduration,location,diaryusername,diaryUserId,this.title);
						//$( "#appointment" ).dialog( "open" );
						
						clearFiledCommonAction();
						if(actionType==5){
							/*if(isotpconfirmd==0){
								 $( "#puresevaclientdetailsdiv" ).modal( "show" );
							}else{
								// $( "#appointment" ).modal( "show" );
								otpConfirmed();
							}*/
							otpConfirmed();
						}else{
							$('#appointment').modal("show");						}
							$( "#blockdiv" ).modal( "hide" );
						
		    		//}
		
	
	
	

				
				
			
				//$('#appointment').dialog('option', 'title', 'New Appointment');
			/*	document.getElementById('bookApmtLbl').innerHTML = "New Appointment";
				editAppointId = 0;
				editCommencing = commencing;
				document.getElementById('date').value = commencing;
				document.getElementById('diaryUserId').value = userid;
			
			//set cookie commencing
			document.cookie = "cookiecommencing=" + commencing;
			document.cookie = "cookiePractitionerId=" + userid;
			
		document.getElementById('client').value = "";
		document.getElementById('notes').value = "";
		document.getElementById('apmtType').value = 0;*/
		
		
		//block appointment initilization
	/*	 var bdivtime = this.title; 
		 var tepmtile =  bdivtime.split(' ');
		 bdivtime = tepmtile[0];
		
		diaryUser1 = slotid;
		stime = starttime;
		blockDivTime = bdivtime;
		etime = endtime;
		loc = location;
		id1 = userid;
		tempDiaryUserName = diaryusername;
		editCommencing = commencing;*/
		
	    	}
	    	
	    	
	    }
}


function initilizeTdElementstaff(el,slotid,starttime,endtime,apmtduration,location,diaryusername,userid,commencing){
	
	
		initcommencing = commencing;
		
		 var container = document.getElementById(el);
		    var childArray = container.children;
		    
		    for(var i = 0; i < childArray.length; i++){
		       // results.innerHTML += childArray[i].id+"<br />";
		        //childArray[i].style.color = "#F00";
		    	var className = $('#'+childArray[i].id+'').attr('class');
		    	
	    		
		    	document.getElementById(childArray[i].id).onclick = function() {
		    		
		    		//if(className=='cssClass1' || className=='yellow'){
			    		setMultiLocationDataAjax(this.title,endtime,userid,commencing,className);
			    		
			    		slotid = initslotid;
			    		starttime = initstarttime;
			    		endtime = initendtime;
			    		location = initlocation;
			    		apmtduration =  initapmduration;
			    		initdiaryusername = diaryusername;
			    		initdiaryuserid = userid;
			    		
			    		
			    		
						//$(this).MessageBox(slotid,starttime,endtime,apmtduration,location,diaryusername,diaryUserId,this.title);
						//$( "#appointment" ).dialog( "open" );
						
						clearFiledCommonAction();
						if(actionType==5){
							/*if(isotpconfirmd==0){
								 $( "#puresevaclientdetailsdiv" ).modal( "show" );
							}else{
								// $( "#appointment" ).modal( "show" );
								otpConfirmed();
							}*/
							otpConfirmed();
						}else{
						
							$('#blockdiv').modal("show");
							
							document.getElementById('blockradio1').checked = false;
							document.getElementById('blockradio2').checked = true;
							
							document.getElementById('blockuser').value = initdiaryusername;
							document.getElementById('blockdate').value = commencing;
							document.getElementById('blockapmtDuration').value = 0;
							document.getElementById('blocklocation').value = location	
							
												}
						
		    		//}
		
	
	
	

				
				
			
		
	    	}
	    	
	    	
	    }
}

// radio toggle

function showAppointmentDialogBox(){
resetot();
	
	//document.getElementById('otstaffdiv').style.display = 'none';
	
	document.getElementById('radio1').checked = true;
	document.getElementById('radio2').checked = false;
	
	//$( "#appointment" ).dialog( "open" );
	$('#appointment').modal("show");
	$( "#blockdiv" ).modal( "hide" );
	
}

function showBlockingDialogBox(){
	
	document.getElementById('otstaffdiv').style.display = 'none';
	
	document.getElementById('blockradio1').checked = false;
	document.getElementById('blockradio2').checked = true;
	
	
	//$( "#appointment" ).dialog( "close" );
	$('#appointment').modal("hide");
	$( "#blockdiv" ).modal( "show" );
	showdiv();
}



function bookSlot(p){
	bookwithpayment = p;
    statusbookwithpayment=p;
	var starttime = globalSlotStartTime;
	if(editAppointId == 0){
		$(this).saveSlot('0',starttime);
		
	}else{
		$(this).saveSlot(editAppointId,editStartTime);
		
	}
}

function bookNotAviSlot(){
	if(blockDivTime==""){
		blockDivTime=document.getElementById('blocksTime').value;		
	}
	if(editAppointId == 0){
		$(this).blockSave('0',blockDivTime);
	}else{
		$(this).blockSave(editAppointId,editStartTime);
	}
}


function delOnlyBlockSlot(){
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	deleteBlockSlot(editAppointId);
	$( "#cancelApmtNoteDiv" ).modal( "hide" );
}

function deleteNotAviSlot(){
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	if(document.getElementById('cancelApmtNote').value==''){
		jAlert('error', 'Please enter cancel appointment note.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else{
//		var r=confirm("Are you sure you want to delete this entry");
//		 $( "#cancelApmtNoteDiv" ).modal( "hide" );
//		   if(r == true){
			   $( "#cancelApmtNoteDiv" ).modal( "hide" );
			deleteBlockSlot(editAppointId);
			
//			//$( "#otmodifypopup" ).modal( "hide" );
//		   }
//	}
//	
//	if(actionType==5){
	//window.location.reload();
//		$( "#puremodifyPopup" ).modal( "hide" );
//		
//	}
	}
}

function addNewAppointment(){
	document.getElementById('radio').style.display = 'none';
	document.getElementById('blockradio').style.display = 'none';
	
	checkEventExist = false;
	editAppointId = 0;
		
		
	
		$( "#appointment" ).modal( "show" );
		$('#modifyPopup').modal( "hide" );
		

	
		document.getElementById('user').value = tempDiaryUserName;
		document.getElementById('location').value = loc;
		document.getElementById('sTime').value = editStartTime;
		document.getElementById('endTime').value = editEndTime;
		document.getElementById('apmtDuration').value = editDuration;
		document.getElementById('client').value = editClientName;
		document.getElementById('clientId').value = patientId;
		document.getElementById('diaryUserId').value = editdiaryuseid;
		document.getElementById('slotId').value = editSlotId;
		document.getElementById('apmtType').value = editAppointType;
		document.getElementById('treatmentEpisode').value = editTreatmentEpisode;
		setEpisodeDetails(editTreatmentEpisode);
		document.getElementById('notes').value = editNotes;
		document.getElementById('bookApmtLbl').innerHTML = "Add New Appointment";
		$(this).MessageBox(editSlotId,editStartTime,editEndTime,editDuration,loc,tempDiaryUserName,editdiaryuseid,null);
}


//drag and drop code
function getChildren(el) {
    var container = document.getElementById(el);
    var childArray = container.children;
    //var results = document.getElementById('results');
    //results.innerHTML = container.id+" has "+childArray.length+" children.<br />";
    for(var i = 0; i < childArray.length; i++){
       // results.innerHTML += childArray[i].id+"<br />";
        //childArray[i].style.color = "#F00";
       // alert(childArray[i].id)
    	
    	var className = $('#'+childArray[i].id+'').attr('class');
    	
    	  return className;
    }
}

function setDragAndDropAjaxData(practitionerid,starttime,duration,ymove,availableSlotID,location){
	//alert(practitionerid);
	//starttime = getTimeTotal(duration,starttime);
	var evalTime = 0;
	var endTime = 0;
	if(ymove > 0){
		if(ymove%20 == 0){
			var px = ymove/20;
			var minute = px*5;
			minute = minutesToStr(minute);
		
			evalTime = getTimeTotal(minute,starttime);
		}else{
			for(i=1;i<=20;i++){
				ymove++;
				if(ymove%20==0)
					break;
			}
			var px = ymove/20;
			var minute = px*5;
			minute = minutesToStr(minute);
		
			evalTime = getTimeTotal(minute,starttime);
		}
		
	}else{
		
		if(ymove%20 == 0){
			var px = ymove/20;
			var minute = px*5;
			minute = minutesToStr(minute);
		
			evalTime = getTimeSubstration(starttime,minute);
		}else{
			for(i=1;i<=20;i++){
				ymove++;
				if(ymove%20==0)
					break;
			}
			var px = ymove/20;
			var minute = px*5;
			minute = minutesToStr(minute);
		
			evalTime = getTimeSubstration(starttime,minute);
		}
		
	}
	
	endTime = getTimeTotal(evalTime,duration);
	
	if(evalTime < '08:00:00' || evalTime > '20:00:00'){
		//alert(evalTime+'/'+endTime);
		$("#new"+availableSlotID+'s').draggable({ revert: 'valid' });
	}else{
		submitDragAndDropAjax(practitionerid,evalTime,availableSlotID,endTime,location);
	}
	
	//alert(evalTime+'/'+endTime);
}


function minutesToStr(minutes) {
	 var sign ='';
	 if(minutes < 0){
	  sign = '-';
	 }

	 var hours = leftPad(Math.floor(Math.abs(minutes) / 60));
	 var minutes = leftPad(Math.abs(minutes) % 60);

	 return  hours +':'+minutes ;

	}


function leftPad(number) {
	 return ((number < 10 && number >= 0) ? '0' : '') + number;
}



function openCompleteActionPopup(id,height,weight,bmi,pulse,sysbp,diabp,temprature,spo,bsa){
	if(imagename==null){
	  document.getElementById('modifyClient1').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding:5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
	} else if(imagename==''){
	   document.getElementById('modifyClient1').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="popicons/avatar2.png" style="width: 100% !important;padding:5px; "/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';
	}else {
       document.getElementById('modifyClient1').innerHTML = '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-4"><a href="#" class="leftproimage" onclick="openClientPrintPopup('+patientId+')"><img src="liveData/'+imagename+'" style="width: 100% !important;"/><p style="color:#fff !important; font-size: 12px !important;padding: 0px 0px !important;margin-top: 6px !important;">'+editClientName+'</p></a><a href="#" class="belowimgiconset" title="Edit Client Record" onclick="openEditClientPrintPopup('+patientId+')"><img src="popicons/edit.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Edit</p></a> <a href = "#" class="belowimgiconset" title="Log" onclick="openClientLogPopup('+patientId+')"><img src="popicons/log.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Log</p></a>  <a href = "#" class="belowimgiconset" title="EMR" onclick="redircttoNewEmr('+patientId+','+diaryuserId+','+editcondition_id+')"><img src="popicons/emr.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">EMR</p></a>  <a href = "#" class="belowimgiconset" title="Assessment Form" onclick="openAssesmentFormPopup('+patientId+','+diaryuserId+','+editAppointId+')"><img src="popicons/asmnt.png" class="setsizeimg"/><p style="color: rgb(61, 61, 61) !important; font-size: 11px !important;">Forms</p></a></div>';	
	}
	
			document.getElementById("height2").innerHTML=height;			
			document.getElementById("weight2").innerHTML=weight;
			document.getElementById("bmi2").innerHTML=bmi;
			document.getElementById("pulse2").innerHTML=pulse;
			document.getElementById("sysbp2").innerHTML=sysbp;
			document.getElementById("diabp2").innerHTML=diabp;
			document.getElementById('sugarfasting2').innerHTML = newsugarfasting;
			document.getElementById('postmeal2').innerHTML = newpostmeal;
			document.getElementById('temprature2').innerHTML = newtemprature;
			document.getElementById('spo2').innerHTML = newspo;
			document.getElementById('bsa2').innerHTML = bsa;
	$( "#completeActionPopup" ).modal( "show" );
}


function updatestatusreportpopup(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	 $( "#updatereportstatusdiv" ).modal( "show" );
	
	 clientAddressAjax();
	
	
	
}

var ussize = 0;
function clientAddressAjax(){
	
	var url = "clientaddressTreatmentEpisode?clientid="+patientId+"&treatmentepisodeid="+editTreatmentEpisode+"&appointmentid="+editAppointId+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = clientAddressAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function clientAddressAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data = req.responseText;
			var temp = data.split('#');
			
			var clientDetails = temp[0];
			var tpName = temp[1];
			var tpepisodedata = temp[2];
			ussize = temp[3];
			var reportsent = temp[4];
			
			if(reportsent=='true'){
				
				document.getElementById('treatmentepisodereportsent').checked=true;
			}else{
				document.getElementById('treatmentepisodereportsent').checked=false;
			}
			
			
			document.getElementById('usclientdetailsid').innerHTML = clientDetails;
			document.getElementById('ustpnamediv').innerHTML = tpName;
			document.getElementById('ustpsession').innerHTML = tpepisodedata;
			
			updatestatusreportpopupAjax();
			
		}
	}
}


function updatestatusreportpopupAjax(){
	
	var url = "statusTreatmentEpisode?appointmentid="+editAppointId+"&treatmentepisodeid="+editTreatmentEpisode+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updatestatusreportpopupRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function updatestatusreportpopupRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			//alert(req.responseText)
			
			document.getElementById('statusrordajaxdiv').innerHTML = req.responseText;
			
			
			var stemp = ussize.split(',');
			for(i=0;i<stemp.length;i++){
				
				$("#sentdate"+stemp[i]).datepicker({

					dateFormat : 'dd/mm/yy',
					yearRange: yearrange,
					minDate : '30/12/1880',
					changeMonth : true,
					changeYear : true

				});
				
			}
			
		}
	}
	
}

function saveAssesmentReportStatus(){
	
	var stemp = ussize.split(',');
	var er = 0;
	for(j=0;j<stemp.length;j++){
		
		if(stemp[j]!=""){
			var usent = document.getElementById('usent'+stemp[j]).checked;
			var sentdate = document.getElementById('sentdate'+stemp[j]).value;
			
			if(usent==true && sentdate==""){
				er = 1;
				break;
			}
		}
		
	}
	
	if(er==1){
		jAlert('error', 'Please Select Sent Date.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else{
		
		
		for(i=0;i<stemp.length;i++){
			saveAssesmentReportStatusAjax(stemp[i]);
		}
	}
	
	
}

function saveAssesmentReportStatusAjax(usapmtid){
	
	var usent = document.getElementById('usent'+usapmtid).checked;
	var sentdate = document.getElementById('sentdate'+usapmtid).value;
	var sentnote = document.getElementById('sentNote'+usapmtid).value;
	var tpreportsent = document.getElementById('treatmentepisodereportsent').checked;
	
	
		
		var url = "savereportTreatmentEpisode?appointmentid="+usapmtid+"&treatmentepisodeid="+editTreatmentEpisode+"&usent="+usent+"&sentdate="+sentdate+"&sentnote="+sentnote+"&tpreportsent="+tpreportsent+" ";
		
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveAssesmentReportStatusAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		
	
	
	
}

function saveAssesmentReportStatusAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			 $( "#updatereportstatusdiv" ).modal( "hide" );
			
			jAlert('success', 'Report sent saved successfully.', 'Success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	}
}


function closeBuble(){
	$('div#pop-up').hide();
}


function setTpExclusiveAppointmentType(){
	alert('hello world');
}


function openprintOpdInvoice(saveAppointmntid){

    openPopup('viewOpdInvoiceCharges?appointmentid='+saveAppointmntid+'');
    
}

function openprintOpdInvoice1(saveAppointmntid){

    openPopup('viewOpdInvoiceCharges?appointmentid='+saveAppointmntid+'&action=1');
    
}


function showLetterHead(){

   openPopup('blankletterheadNotAvailableSlot?patientid='+patientId+'&appointmentid='+editAppointId+'');

}

function editBMI(index) {

	  var dheight=document.getElementById("height"+index+"").innerHTML;
	  var dweight=document.getElementById("weight"+index+"").innerHTML;
	  var dbmi=document.getElementById("bmi"+index+"").innerHTML;
	  var dpulse=document.getElementById("pulse"+index+"").innerHTML
	  var dsysbp=document.getElementById("sysbp"+index+"").innerHTML;
	  var ddiabp=document.getElementById("diabp"+index+"").innerHTML;
	  var sugarfasting = document.getElementById("sugarfasting"+index+"").innerHTML;
	  var postmeal = document.getElementById("postmeal"+index+"").innerHTML;
	  var bsa= document.getElementById("bsa"+index+"").innerHTML;
	  var temprature = document.getElementById("temprature"+index+"").innerHTML;
	  var spo = document.getElementById("spo"+index+"").innerHTML;
	  var lmpd=document.getElementById("lmpd"+index+"").innerHTML;
	  document.getElementById("height"+index+"").innerHTML="<input type='text' value='"+dheight+"' id='vheight"+index+"' onchange='calbmi("+index+")' class='form-control' placeholder='enter height'>";    
	  document.getElementById("weight"+index+"").innerHTML="<input type='text' value='"+dweight+"' id='vweight"+index+"' onchange='calbmi("+index+")' class='form-control' placeholder='enter weight'>";
	  document.getElementById("bmi"+index+"").innerHTML="<input type='text' value='"+dbmi+"' id='vbmi"+index+"' class='form-control' placeholder='enter bmi'>";
	  document.getElementById("pulse"+index+"").innerHTML="<input type='text' value='"+dpulse+"' id='vpulse"+index+"' class='form-control' placeholder='enter pulse'>";
	  document.getElementById("sysbp"+index+"").innerHTML="<input type='text' value='"+dsysbp+"' id='vsysbp"+index+"' class='form-control' placeholder='enter sysbp'>";
	  document.getElementById("diabp"+index+"").innerHTML="<input type='text' value='"+ddiabp+"' id='vdiabp"+index+"' class='form-control' placeholder='enter diabp'>";
	 
	 
	  //  17 jan 2018
	  document.getElementById("sugarfasting"+index+"").innerHTML = "<input type='text' value='"+sugarfasting+"' id='vsugarfasting"+index+"' class='form-control' placeholder='enter sugarfasting'>";
	  document.getElementById("postmeal"+index+"").innerHTML = "<input type='text' value='"+postmeal+"' id='vpostmeal"+index+"' class='form-control' placeholder='enter postmeal'>";
	//lokesh 9-7-18
	//  bsa=dheight*dweight;
	//  bsa=bsa/3600
	//  bsa=Math.sqrt( bsa);
	  document.getElementById("bsa"+index+"").innerHTML="<input type='text' value='"+bsa+"' id='vbsa"+index+"' class='form-control' placeholder='enter Head circ.'>";
	  
	  //  18/12/18
	  document.getElementById("temprature"+index+"").innerHTML = "<input type='text' value='"+temprature+"' id='vtemprature"+index+"' class='form-control' placeholder='enter temp'>";
	  document.getElementById("spo"+index+"").innerHTML = "<input type='text' value='"+spo+"' id='vspo"+index+"' class='form-control' placeholder='enter SPO2'>";
	  
	  document.getElementById("lmpd"+index+"").innerHTML = "<input type='text' value='"+lmpd+"' id='vlmpd"+index+"' class='form-control lmpc' readonly='readonly' placeholder='enter LMP Date'>";
	  document.getElementById("editSave"+index+"").innerHTML="<a href='#' style='color: yellow;' onclick='saveBMI("+index+")' title='Save'><i class='fa fa-save' aria-hidden='true'></i></a>";

	  $(".lmpc").datepicker({

			dateFormat : 'dd/mm/yy',
			yearRange: yearrange,
			minDate : '30/12/1880',
			changeMonth : true,
			changeYear : true

		});


}

	var height="";
	var weight="";
	var bmi="";
	var pulse="";
	var sysbp="";
	var diabp="";
	var sugarfasting="";
	var postmeal="";
	var lmpda="";

function saveBMI(index){
	bmiindex=index;
	height= document.getElementById("vheight"+index+"").value;
 	weight=document.getElementById("vweight"+index+"").value;
	bmi=document.getElementById("vbmi"+index+"").value;
	pulse=document.getElementById("vpulse"+index+"").value;
	sysbp=document.getElementById("vsysbp"+index+"").value;
    diabp=document.getElementById("vdiabp"+index+"").value;
	//  17 jan 2018
    sugarfasting = document.getElementById("vsugarfasting"+index+"").value;
    postmeal = document.getElementById("vpostmeal"+index+"").value;
    //  18/12/18
    temprature=document.getElementById("vtemprature"+index+"").value;
    spo=document.getElementById("vspo"+index+"").value;
    lmpda=document.getElementById("vlmpd"+index+"").value;
    bsa=document.getElementById("vbsa"+index+"").value;
//  var url="savebmiNotAvailableSlot?height="+height+"&weight="+weight+"&bmi="+bmi+"&pulse="+pulse+"&sysbp="+sysbp+"&diabp="+diabp+"&patientId="+patientId+"&sugarfasting="+sugarfasting+"&postmeal="+postmeal+"&temprature="+temprature+"&spo="+spo+"&bsa="+bsa+"&apmtid="+editAppointId+"";
    //remove this method from notavailableslot and move to bookappointmentajax for speed up
     var url="savebmiBookAppointmentAjax?height="+height+"&weight="+weight+"&bmi="+bmi+"&pulse="+pulse+"&sysbp="+sysbp+"&diabp="+diabp+"&patientId="+patientId+"&sugarfasting="+sugarfasting+"&postmeal="+postmeal+"&temprature="+temprature+"&spo="+spo+"&bsa="+bsa+"&lmpd="+lmpda+"&apmtid="+editAppointId+"";
     if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveBMIRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
  
}


function saveBMIRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 //window.location.reload();
			  document.getElementById("height"+bmiindex+"").innerHTML=height;
  			  document.getElementById("weight"+bmiindex+"").innerHTML=weight;
  			  document.getElementById("bmi"+bmiindex+"").innerHTML=bmi;
  			  document.getElementById("pulse"+bmiindex+"").innerHTML=pulse;
  			  document.getElementById("sysbp"+bmiindex+"").innerHTML=sysbp;
  			  document.getElementById("diabp"+bmiindex+"").innerHTML=diabp;
  			  //  17 jan 2018
  			  document.getElementById("sugarfasting"+bmiindex+"").innerHTML=sugarfasting;
 			  document.getElementById("postmeal"+bmiindex+"").innerHTML=postmeal;
 			  //  19-dec-18
 			 document.getElementById("temprature"+bmiindex+"").innerHTML=temprature;
 			document.getElementById("spo"+bmiindex+"").innerHTML=spo;
 			document.getElementById("bsa"+bmiindex+"").innerHTML=bsa;
 			
 			document.getElementById("lmpd"+bmiindex+"").innerHTML=lmpda;
 			
			  document.getElementById("editSave"+bmiindex+"").innerHTML="<a href='#' style='color: yellow;' onclick='editBMI("+bmiindex+")' title='Edit'><i class='fa fa-pencil' aria-hidden='true'></i></a>";
			  createAntinatalSchedule(patientId,lmpda);
		}
	}
}


function createAntinatalSchedule(clientId, lmpDate){
	if(lmpDate.includes("/")){
		 var url="saveantinatalfromvitalsNotAvailableSlot?clientId="+clientId+"&type=1";
		 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = createAntinatalScheduleRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
}

function createAntinatalScheduleRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			}
		}
}
var bmiindex="";

function getbmiData(patientid,index){
		bmiindex=index;
		var url="getbmidataNotAvailableSlot?clientid="+patientid+"&apmtid="+editAppointId+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = getbmiDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
  
}


function getbmiDataRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data=req.responseText;
			var str=data.split("~");
			document.getElementById("height"+bmiindex).innerHTML=str[0];			
			document.getElementById("weight"+bmiindex).innerHTML=str[1];
			document.getElementById("bmi"+bmiindex).innerHTML=str[2];
			document.getElementById("pulse"+bmiindex).innerHTML=str[3];
			document.getElementById("sysbp"+bmiindex).innerHTML=str[4];
			document.getElementById("diabp"+bmiindex).innerHTML=str[5];
			document.getElementById("sugarfasting"+bmiindex).innerHTML=str[6];
			document.getElementById("postmeal"+bmiindex).innerHTML=str[7];
			document.getElementById("temprature"+bmiindex).innerHTML=str[8];
			document.getElementById("spo"+bmiindex).innerHTML=str[9];
			document.getElementById("bsa"+bmiindex).innerHTML=str[10];
			
		}
	}
}


function calbmi(index){

   var w=document.getElementById("vweight"+index).value; 
   var h=document.getElementById("vheight"+index).value; 
    
   var d=h/100; 
   var bmi=w/(d*d);
   var result=Math.round(bmi*100)/100; 
   document.getElementById("vbmi"+index).value=result;	    

}

function openOtNotes(){
	 var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	openPopup('otnotesNotAvailableSlot?apmtid='+editAppointId+'');
}


function setemruploaddocAjax(data,type){
	var url = "setEmr?data="+data+"&type="+type+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setemruploaddocAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setemruploaddocAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
}

//diaryuserId,editAppointId, patientId ,editcondition_id

function openuploadPopup(){
	
	
	  document.getElementById("clientname").value=patientId;
	  document.getElementById("diaryUser").value=diaryuserId;
	  document.getElementById("doccondition").value=editcondition_id;
	  document.getElementById("editDoctId").value="";
	  document.getElementById("docapmtId").value=editAppointId;
	  
	  $('#uploaddoc').modal( "show" );
	
}

function openVitalClient(){
	 
	    var url="getvitalmasteranddataIpdDashboard?ipdid=0&clientid="+patientId+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = openVitalClientRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);    
	 
	  
}

function openVitalClientRequest() {
	  
    if (req.readyState == 4) {
        if (req.status == 200) {
       	 
       	   var str=	req.responseText;
       	   var data=str.split("~");
       	   document.getElementById("pnamevital").innerHTML=data[0];
       	   document.getElementById("vitaltime").value=data[1];
       	   $('#vitals').modal( "show" );
            }
        }
 }


function saveClientFinding(val,vitalid){
	 
	 saveFinding(val,0,patientId,vitalid);
}




 function saveFinding(val,ipdid,clientid,vitalid){
	  
	  var time=document.getElementById("vitaltime").value;
	  
	  var url="saveclientfindingIpdDashboard?finding="+val+"&ipdid="+ipdid+"&clientid="+clientid+"&vitalid="+vitalid+"&time="+time+"";
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveFindingRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);    
	  
	  
 } 
 function saveFindingRequest() {
	  
	     if (req.readyState == 4) {
	         if (req.status == 200) {
	        	   
	        	   
	             }
	         }
	  }  

//vitalstatisticsIpd?clientid=9227&ipdid=1006
 function openViewRecordVital(){
	 
	 openPopup('vitalstatisticsIpd?clientid='+patientId+'&ipdid=0');
 }
 
 function openimmunizationchart(){
	 openPopup('newimmunizationchartSlotAvailable?clientId='+patientId);
 }
 
 function openimmunizationchart1(){
	 openPopup('newimmunizationchartSlotAvailable?clientId='+patientId+'&type=1');
 }
 
 function openclinicalnotes(){
	 
	 openPopup('clinicalnotesEmr?clientid='+patientId+'&diaryuser='+diaryuserId);
 }
/*function getnewbmidata(patientid){
	var url="getbmidataNotAvailableSlot?clientid="+patientid+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = getnewbmidataRequest;
    req.open("GET", url, true); 
    req.send(null);

}


function getnewbmidataRequest(){
		var data=req.responseText;
		var str=data.split("~");
		document.getElementById("height"+bmiindex).innerHTML=str[0];			
		document.getElementById("weight"+bmiindex).innerHTML=str[1];
		document.getElementById("bmi"+bmiindex).innerHTML=str[2];
		document.getElementById("pulse"+bmiindex).innerHTML=str[3];
		document.getElementById("sysbp"+bmiindex).innerHTML=str[4];
		document.getElementById("diabp"+bmiindex).innerHTML=str[5];
		newsugarfasting = str[6];
		newpostmeal= str[7];
}*/


//  31 March 2018
 function setappointmentprocedure(editAppointId){
		var url="setappointmentprocedureBookAppointmentAjax?editAppointId="+editAppointId+"";
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = setappointmentprocedureRequest;
	    req.open("GET", url, true); 
	    req.send(null);

	}


	function setappointmentprocedureRequest(){
		var data=req.responseText;
		var str=data.split("~");
		
		document.getElementById('otprocedurediv').innerHTML = str[0];
		$("#otprocedureplaned").trigger("chosen:updated");
	  	$(".chosen").chosen({allow_single_deselect: true});
	  	    
	  	document.getElementById("tpappointmenttype").innerHTML = str[1];
		$("#apmtType").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
	}
	
	
	
	
	
	function setfollowupdata1(){
		
		var folowdate= document.getElementById("lokeshfollowdatenew").value;
		
		var clientid= patientId;
		var type="2";
		if(folowdate==""){
			alert("insert date");
			return;
		}
		var url ="savetofollowupClient?clientid="+patientId+"&followdate="+folowdate+"&reqtype="+type+"" ;
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		document.getElementById("followupmodal").style.display = "none";           
	    req.onreadystatechange = setfollowupdataRequest1;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	function setfollowupdataRequest1(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
			}
		}
		
	}

	function getbeds(wardid){
		var url ="getallnotoccupiedbedsBookAppointmentAjax?wardid="+wardid;
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		document.getElementById("followupmodal").style.display = "none";           
	    req.onreadystatechange = getbedsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	function getbedsRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("bedlist").innerHTML=req.responseText;
			}
		}
		
	}
function savepatienttoipd(){
	var ward=document.getElementById("wardname").value;
	var bed=document.getElementById("bedname").value;
	var dtr=document.getElementById("practitionersId").value;
	var client=document.getElementById("patientId").value;
	var cookiePractitionerId=read_cookie("cookiePractitionerId");
	var cookieClientId=read_cookie("cookieClientId"); 
	
	
	
	if(bed==""){
		alert("please select the bed");
	}else{
		var url="savefromopdtoipdIpd?ward="+ward+"&bed="+bed+"&clientid="+cookieClientId+"&practitioner="+cookiePractitionerId;
		document.getElementById("toipd").style.display = "none";
		
		$( "#otmodifypopup" ).modal( "hide" );
		$( "#modifyPopup" ).modal( "hide" );
		$( "#completeActionPopup" ).modal( "hide" );
		$( "#recordpaymentpopup" ).modal( "hide" );
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		document.getElementById("followupmodal").style.display = "none";           
	    req.onreadystatechange = savepatienttoipdRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	
}
function savepatienttoipdRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			/*document.getElementById("bedlist").innerHTML=req.responseText;*/
			var x=req.responseText;
			alert(x);
			document.getElementById("toipd").style.display = "none";
		}
	}
}

function checkptamitted(clientid){
	bedcid = clientid;
	//var url = "admittedIpd?clientid="+clientid+"";
	var url = "admittedIpdAjax?clientid="+clientid+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkptamittedRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null)
	return checkptamittedRequest();
}

function checkptamittedRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var bedid = req.responseText;
			if(bedid==0){
				return true;
			}else{
				return false;
			}
		}
	}
}
	

function showinvst(){
	
	//var url = "admittedIpd?clientid="+clientid+"";
	var url = "getuhidnewClient?clientid="+patientId+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showinvstRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null)
	
}

function showinvstRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var uhid = req.responseText;
		openPopup('Investigation?uhid='+uhid);
		}
	}
}
function openconcentform(){
	openPopup('displayProfileClient?selectedid='+patientId+'&isopd=1&drid='+read_cookie("cookiePractitionerId")+'&apmtId='+editAppointId);
}


function saveDiagnosisofOpd(){
	
	var diagosis="0";
	 $('.classD').each(function() { 
	  		if(this.checked == true){
	  			
	  			diagosis=diagosis+","+this.value;
	  		}
	  								
	  	});
	      
var url = "saveopddiagnosisBookAppointmentAjax?opdid="+editAppointId+"&diagnosisid="+diagosis;
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveDiagnosisofOpdRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null)
}

function saveDiagnosisofOpdRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			$( "#modfydiagnosispopup" ).modal( "hide" );
			alert("Saved");
		}
	}
}


//option media
function openpatientmedia(){
	openDisplayPopup('opdbodyAssesmentForms?templateId='+pbodytmplate+'&formtype=0&apmtid='+editAppointId+'&patientId='+patientId+'&diaryuserId='+diaryuserId+'&editcondition_id='+editcondition_id+'');
}

function openeditedpatientmedia(){
	
	if(pbodyeditedtmplate==0){
jAlert('error', "No Record Found!!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		openDisplayPopup('editListAssessmentForm?id='+pbodyeditedtmplate+'&actionType=2&formtype=0');
	}
	
			
}


function moveappointment(){
	/*$( "#patient_media" ).modal( "show" );*/
	$( "#moveappointmentpopup" ).modal( "show" );
	document.getElementById("diaryUsersssmve").value=diaryuserId;
}

function updatemveappointment(){
	if(document.getElementById('diaryUsersssmve').value==0){
jAlert('error', "Please Select Practitioner!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		
		var duserid = document.getElementById('diaryUsersssmve').value;
		var commencing = document.getElementById("commencingmve").value;
		var stime = document.getElementById("sTimemve").value;
		var endtime = document.getElementById("endTimemve").value;
		var duration = document.getElementById("apmtDurationmve").value;
		var apmttype = document.getElementById("apmtType12").value;
		
		var url = "updmveapmtBookAppointmentAjax?duserid="+duserid+"&commencing="+commencing+"&stime="+stime+"&endtime="+endtime+"&duration="+duration+"&editAppointId="+editAppointId+"&apmttype="+apmttype+" ";
		
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();	
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = updatemveappointmentRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		
	}
}


function updatemveappointmentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			jAlert('success', "Appointment Updation Successful!", 'Success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			$( "#moveappointmentpopup" ).modal( "hide" );
			$( "#modifyPopup" ).modal( "hide" );
			showdisplaynewopd();
		}
	}
}

function setmveappointmenttime(duserid){
	var commencing = document.getElementById("commencingmve").value; 
	
var url = "mveapmtBookAppointmentAjax?duserid="+duserid+"&commencing="+commencing+" ";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setmveappointmenttimeRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function setmveappointmenttimeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data=req.responseText;
			var t = data.split('~');
			var slotid = t[0];
			var stime = t[1];
			var duration = t[2];
			var apmtType=t[3];
			document.getElementById("sTimemve").value = stime;
			document.getElementById("apmtDurationmve").value = duration;
			document.getElementById("apmtType12").innerHTML='';
			document.getElementById("apmtType12").innerHTML=apmtType;
			var endtime = getTimeTotal(stime,duration);
			
			if(endtime=='000:00'){
				endtime = '24:00';
			}
			
			document.getElementById('endTimemve').value = endtime;

			
		}
	}
}

function adjustmovdendtime(){
	var stime = document.getElementById("sTimemve").value;
	var duration = document.getElementById("apmtDurationmve").value;
	
	var endtime = getTimeTotal(stime,duration);
	
	if(endtime=='000:00'){
		endtime = '24:00';
	}
	
	document.getElementById('endTimemve').value = endtime;

}

function resetmveappmnt(){
	
	
	document.getElementById('diaryUsersssmve').value = 0;
	
	 $("#diaryUsersssmve").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});

}


var ii='';
function setHISBMIData(clientid, index){
	ii=index;
var url = "getbmidataBookAppointmentAjax?clientid="+clientid+"&apmtid="+editAppointId+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setHISBMIDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}


function setHISBMIDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
//			var bmiindex=ii;
			var bmiindex=2;
			var data=req.responseText;
			var dt=data.split("$$~$$");
			if(document.getElementById("height"+bmiindex+"")){
			
				bmiindex=6;
				document.getElementById("height"+bmiindex+"").innerHTML=dt[0];
				  document.getElementById("weight"+bmiindex+"").innerHTML=dt[1];
				  document.getElementById("bmi"+bmiindex+"").innerHTML=dt[2];
				  document.getElementById("pulse"+bmiindex+"").innerHTML=dt[3];
				  document.getElementById("sysbp"+bmiindex+"").innerHTML=dt[4];
				  document.getElementById("diabp"+bmiindex+"").innerHTML=dt[5];
				  
				  document.getElementById("sugarfasting"+bmiindex+"").innerHTML=dt[6];
				  document.getElementById("postmeal"+bmiindex+"").innerHTML=dt[7];
				  document.getElementById("lmpd"+bmiindex+"").innerHTML=dt[23];
				 document.getElementById("temprature"+bmiindex+"").innerHTML=dt[8];
				 document.getElementById("spo"+bmiindex+"").innerHTML=dt[9]; 
				 document.getElementById("bsa"+bmiindex+"").innerHTML=dt[10];
				/* document.getElementById("editSave"+bmiindex+"").innerHTML="<a href='#' style='color: yellow;' onclick='saveBMI("+bmiindex+")' title='Save'><i class='fa fa-save' aria-hidden='true'></i></a>";
*/
				 bmiindex=1;
					document.getElementById("height"+bmiindex+"").innerHTML=dt[0];
					  document.getElementById("weight"+bmiindex+"").innerHTML=dt[1];
					  document.getElementById("bmi"+bmiindex+"").innerHTML=dt[2];
					  document.getElementById("pulse"+bmiindex+"").innerHTML=dt[3];
					  document.getElementById("sysbp"+bmiindex+"").innerHTML=dt[4];
					  document.getElementById("diabp"+bmiindex+"").innerHTML=dt[5];
					  
					  document.getElementById("sugarfasting"+bmiindex+"").innerHTML=dt[6];
					  document.getElementById("postmeal"+bmiindex+"").innerHTML=dt[7];
					  
					 document.getElementById("temprature"+bmiindex+"").innerHTML=dt[8];
					 document.getElementById("spo"+bmiindex+"").innerHTML=dt[9]; 
					 document.getElementById("bsa"+bmiindex+"").innerHTML=dt[10];
					 document.getElementById("dnaclinentname").value=dt[11];
					 document.getElementById("lmpd"+bmiindex+"").innerHTML=dt[23];
					 editStartTime=dt[12];
					 editDuration=dt[13];
					 document.getElementById("dnapayby").value =dt[14];
					 
//					 document.getElementById("missedappointmentwith").value =dt[15];
					 tempDiaryUserName=dt[15]
					 document.getElementById("didnotattentDuration").value  = dt[16];
					 document.getElementById("dnaAppointmentName").value  = dt[17];
					 document.getElementById("didnotattendDate").value  = dt[18];
					 
					 bmiindex=4;
						
					 document.getElementById("height"+bmiindex+"").innerHTML=dt[0];
					  document.getElementById("weight"+bmiindex+"").innerHTML=dt[1];
					  document.getElementById("bmi"+bmiindex+"").innerHTML=dt[2];
					  document.getElementById("pulse"+bmiindex+"").innerHTML=dt[3];
					  document.getElementById("sysbp"+bmiindex+"").innerHTML=dt[4];
					  document.getElementById("diabp"+bmiindex+"").innerHTML=dt[5];
					  
					  document.getElementById("sugarfasting"+bmiindex+"").innerHTML=dt[6];
					  document.getElementById("postmeal"+bmiindex+"").innerHTML=dt[7];
					  
					 document.getElementById("temprature"+bmiindex+"").innerHTML=dt[8];
					 document.getElementById("spo"+bmiindex+"").innerHTML=dt[9]; 
					 document.getElementById("bsa"+bmiindex+"").innerHTML=dt[10];
					 document.getElementById("lmpd"+bmiindex+"").innerHTML=dt[23];
//					 if(dt[19]==true){
//					$('#modifydnapopup').modal( "show" );
//					$('#modifyPopup').modal( "hide" );
//					 }
					document.getElementById('missedappointmentwith').value = tempDiaryUserName;
					document.getElementById('didnotattendDate').value = dt[18];;
					document.getElementById('didnotattentTime').value = editStartTime;
					document.getElementById('didnotattentDuration').value = dt[16];;
					//document.getElementById('didnotattendNotes').value = editNotes;
					document.getElementById('dnachk').checked = dna;
					document.getElementById('dnaclinentname').value = dt[11];
					document.getElementById('dnapatientname').innerHTML=dt[11];
					document.getElementById('modifydnaClient1').className="hidden";
					document.cookie = "cookiePractitioner=" + tempDiaryUserName;
					if(dt[14]=='Client'){
						document.getElementById('dnapayby').value = 'Client(Self)';
						document.getElementById('dnaclientrdo').checked = true;
					}else{
						document.getElementById('dnapayby').value = 'Third Party)'
						document.getElementById('dnatprdo').checked = true;
					}
					if(dt[20]>0){
						document.getElementById('apmtType12').disabled=true;
					}
					editdiaryuseid=dt[21];
					var apptype=dt[22];
					if(apptype==0){
						document.getElementById('modify').className="thumbnail hidden";
					}
					
					document.getElementById('patientnameptp').innerHTML=dt[11];
					document.getElementById('patientuhidptp').innerHTML=dt[24];
					
			}
			}
		}
}


function moveappointmentbyappointment() {
var url = "getappointmentdataBookAppointmentAjax?apmtid="+editAppointId+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = moveappointmentbyappointmentRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function moveappointmentbyappointmentRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str=req.responseText;
			var data=str.split("~~");
			 document.getElementById("sTimemve").value=data[0];
			 document.getElementById("endTimemve").value=data[1];
			 document.getElementById("commencingmve").value=data[2];
			 document.getElementById('diaryUsersssmve').className="";
			 document.getElementById("diaryUsersssmve").value=editdiaryuseid;
			 document.getElementById('diaryUsersssmve').className="form-control chosen";
			  $("#diaryUsersssmve").trigger("chosen:updated");
		  	   $(".chosen").chosen({allow_single_deselect: true});
			 document.getElementById("apmtDurationmve").value=data[4];
			 document.getElementById('tpappointmenttype2').innerHTML = data[8];
			 document.getElementById("apmtType12").value=data[5];
			 if(data[6]>0){
				if(data[9]==1){
					document.getElementById('apmtType12').disabled=false;	
				}else{
				 document.getElementById('apmtType12').disabled=true;
				 }
			 }
			 
				 if(data[7]>0){
					 document.getElementById('apmtType12').disabled=true;
				 }
			 document.getElementById("commencingmve").value=data[2];
	$( "#moveappointmentpopup" ).modal( "show" );
		}
	}
	
}




function printbarcodea(){
	openPopup('patientbarcodeCommonnew?id='+patientId+"&abrivation=&patientNameOrig=");
}






function openpatientattachment(clietid,appitid){
	$('#recordpaymentpopup').modal( "hide" );
	$('#modifyPopup').modal( "hide" );
	var doctorid = document.getElementById("diaryUsersss").value;
	openBlankPopup("emrdocsEmr?clientId="+clietid+"&condition=0&diaryUser="+doctorid+"&apmtId="+appitid+"");
}

var puresavefname="";
var puresavelname="";
function checkuhidexistornot() {
	var mobNo=document.getElementById("entermob").value;
	var regEx = /^\d{10}$/;
	var isError = false;
	
	if(!regEx.test(mobNo)) {
	    jAlert('error', "Please Enter Valid Mobile No.", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		isError = true;
	}else if(mobNo.length!=10) {
	    jAlert('error', "Please Enter Valid Mobile No.", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		isError = true;
	}else{
		
	}
	if (isError == true) {
	} else {
		var url = "existmobilenoNotAvailableSlot?mob="+mobNo+" ";	
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkuhidexistornotRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
		
		
	}
	function checkuhidexistornotRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			var str=req.responseText;
			var data=str.split("~~~");
			if(data[0]==0){
				jAlert('error', 'Mobile No Not Registerd.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				document.getElementById('oriuhid').value=data[0];
				document.getElementById('oriemail').value=data[1];
				email = data[1];
				puresavefname=data[2];
				puresavelname=data[3];
				confirmopdotp();
			}	
		}
	}
	}
	function savepuredata(){			
		var email = document.getElementById('pureemail').value;
		var fname =  document.getElementById('purefname').value;
		var lname = document.getElementById('purelname').value;
		var mob = document.getElementById('puremob').value;
		var dob = document.getElementById('puredob').value;
		var title = document.getElementById('title123').value;
		var gender =document.getElementById("gender123").options[document.getElementById('gender123').selectedIndex].text;
		
		var state=document.getElementById('state123').value;
		var city=document.getElementById('city123').value;
		var address=document.getElementById('address123').value;
		var town = document.getElementById('town123').value;

		var relativename=document.getElementById('relativename123').value;
		var pin=document.getElementById('pincode123').value;
		
		if(fname=='undefined'){
			fname = '';
		}
		if(lname=='undefined'){
			lname = '';
		}
		if(mob=='undefined'){
			mob = '';
		}
		if(dob=='undefined'){
			dob = '';
		}
		var relativecontact=document.getElementById('relativecontact123').value;
		var relation=document.getElementById('relativerelation123').value;
		
		var docType=document.getElementById('docType').value;
		
			var url = "saveclientdataNotAvailableSlot?email="+email+"&fname="+fname+"&lname="+lname+"&mob="+mob+"&dob="+dob+"&gender="+gender+"&title="+title+"" +
					"&state="+state+"&city="+city+"&address="+address+"&relativename="+relativename+"&relativecontact="+relativecontact+"" +
							"&relation="+relation+"&docType="+docType+"&pin="+pin+"&town_village="+town;

			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = savepuredataRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);



		}

		function savepuredataRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var str= req.responseText;
				var data=str.split("!@");
				document.getElementById('oriuhid').value=data[0];
				var linkaddress=document.getElementById("linkaddress").value;
				jAlert('success', "Patient Registered Successfully!", 'Success Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, 10000);
//				var url="https://"+linkaddress+":8443/"+warname+"/Pureseva?title=&firstname=&lastname=&email=&clinicid="+data[1]+"&mob=&date=&diaryuserid=&gender=&dob=&uhid="+data[0]+"";
				var url="http://"+linkaddress+":8080/"+warname+"/Pureseva?title=&firstname=&lastname=&email=&clinicid="+data[1]+"&mob=&date=&diaryuserid=&gender=&dob=&uhid="+data[0]+"&flag=1";
				//var url="http://localhost:8081/"+warname+"/Pureseva?title=&firstname=&lastname=&email=&clinicid="+data[1]+"&mob=&date=&diaryuserid=&gender=&dob=&uhid="+data[0]+"&flag=1";

				//				var url="calNotAvailableSlot?actionType=newopd&doctor=1";
				window.location=url;
			}
		}
		}
		
		function openbookingconfirmchecklist(clientid,apmtid) {
			document.getElementById("book_conf_clientid").value=clientid;
			document.getElementById("book_conf_id").value=apmtid;
			var url = "getbookingcofirmdataBookAppointmentAjax?apmtid="+apmtid+"";
				
				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();	
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = openbookingconfirmchecklistRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);
			}
			function openbookingconfirmchecklistRequest() {
				if (req.readyState == 4) {
					if (req.status == 200) {
						var str=req.responseText;
						var data=str.split("~~~~");
						 if(data[0]==1){
							 document.getElementById("book_conf_registration").checked =true;
							 document.getElementById("book_conf_registration").disabled = true;
						 }else{
							 document.getElementById("book_conf_registration").checked =false;
						 }
						 
						 if(data[1]==1){
							 document.getElementById("book_conf_doc_conf").checked =true;
						 }else{
							 document.getElementById("book_conf_doc_conf").checked =false;
						 }
						 
						 if(data[2]==1){
							 document.getElementById("book_conf_pay_conf").checked =true;
						 }else{
							 document.getElementById("book_conf_pay_conf").checked =false;
						 }
						 document.getElementById("book_conf_pay_conf").disabled = true;
						 
						 if(data[3]==1){
							 document.getElementById("book_conf_vid_conf").disabled = false;
							 if(data[4]==1){
								 document.getElementById("book_conf_vid_conf").checked =true;
							 }else{
								 document.getElementById("book_conf_vid_conf").checked =false;
							 }
						 }else{
							 document.getElementById("book_conf_vid_conf").disabled = true;
							 if(data[4]==1){
								 document.getElementById("book_conf_vid_conf").checked =true;
							 }else{
								 document.getElementById("book_conf_vid_conf").checked =false;
							 }
						 }
						 
						 if(data[5]==1){
							 document.getElementById("book_conf_pending").checked =true;
						 }else{
							 document.getElementById("book_conf_pending").checked =false;
						 }
						 document.getElementById("booking_confirm_remark").value=data[6];
						 $( "#bookingconfirmpopupchecklist" ).modal( "show" );
					}
				}
				
			}
			
			function savebookingconfirmchecklist() {
				var clientid =document.getElementById("book_conf_clientid").value;
				var apmtid = document.getElementById("book_conf_id").value;
				var pending = document.getElementById("book_conf_pending").checked;
				var remark = document.getElementById("booking_confirm_remark").value;
				var registration = document.getElementById("book_conf_registration").checked;
				var doc_confirm = document.getElementById("book_conf_doc_conf").checked;
				var pay_confirm = document.getElementById("book_conf_pay_conf").checked;
				var vid_confirm = document.getElementById("book_conf_vid_conf").checked;
				
				var flagerror= false;
				if(pending){
					if(remark==''){
						jAlert('error', 'Please enter remark.', 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
						flagerror = true;
					}
				}
				if(!flagerror){
					var pending1 =0;
					if(pending){
						pending1 =1;
					}
					var registration1 =0;
					if(registration){
						registration1 =1;
					}
					var doc_confirm1 =0;
					if(doc_confirm){
						doc_confirm1 =1;
					}
					var vid_confirm1 =0;
					if(vid_confirm){
						vid_confirm1 =1;
					}
					
					var url = "savebookingcofirmdataBookAppointmentAjax?apmtid="+apmtid+"&pending="+pending1+"&remark="+remark+"&registration="+registration1+"&doc_confirm="+doc_confirm1+"&vid_confirm="+vid_confirm1+"&clientid="+clientid+"";
					if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();	
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = savebookingconfirmchecklistRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);
				}
				
			}
			function savebookingconfirmchecklistRequest() {
				if (req.readyState == 4) {
					if (req.status == 200) {
						 $( "#bookingconfirmpopupchecklist" ).modal( "hide" );
						 
						 showdisplaynewopd() ;
					}
				}
				
			}
		
function opdnewfun(id,starttime,endtime,apmtduration,location,diaryUser,diaryUserId,divtime) {
					
				 var tepmtile = divtime.split(' '); 
				 divtime = tepmtile[0];
				 
				
				
			 		document.getElementById('slotId').value = id;
			 		document.getElementById('blockdiaryUserId').value = diaryUserId;
			 		document.getElementById('blockslotId').value = id;
					//document.getElementById('sTime').value = starttime;
					//document.cookie = "cookieNewStarttime=" + starttime;
			 		/*globalSlotStartTime = document.getElementById('sTime').value;*/
			 		globalSlotStartTime = starttime;
			 		document.getElementById('endTime').value = endtime;
			 		//document.getElementById('apmtDuration').value = apmtduration;
			 		document.getElementById('location').value = location;
			 		
			 		//radio button coding
			 		/*if(document.getElementById('radio2').checked==true || document.getElementById('blockradio2')){
			 			bookSlotid = id;
			 	 		bookCommencing = editCommencing;
			 	 		bookLocation = location;
			 	 		bookDiaryUserid = diaryUserId;
			 	 		bookDiaryUser = diaryUser;
			 	 		bookStartTime = starttime;
			 	 		bookEndTime = endtime;
			 	 		bookAppointmentType = 0;
			 			
			 			document.getElementById('blockuser').value = tempDiaryUserName;
						document.getElementById('blockdate').value = commencing;
						document.getElementById('blocksTime').value = blockDivTime;
			 			document.getElementById('blockendTime').value = etime;
			 			document.getElementById('blockapmtDuration').value = 0;
			 			document.getElementById('blocklocation').value = loc;
			 		}
			 		*/
			 		
			 		
			 		//if(editAppointId==0){
			 			document.getElementById('sTime').value = divtime;
			 			$("#sTime").trigger("chosen:updated");
                		$(".chosen").chosen({allow_single_deselect: true});
			 			document.getElementById('apmtDuration').value = 0;
			 			
			 		//}
			 		
			 		
			 		
			 			
						
						
						
						
						var date = editCommencing;
						//var id = document.getElementById('diaryUser').value;
						
						
						document.getElementById('date').value = date;
						document.getElementById('diaryUserId').value = diaryUserId;
						document.getElementById('user').value = diaryUser;
						commencing = editCommencing;
										
						
						
						document.getElementById('date').value = commencing;
						document.getElementById('diaryUserId').value = diaryUserId;
						
						//getDepartmentCondition(location);
						
						var location = document.getElementById('location');
						
										
						
					
					
						
					///var str = '<input class = "buttons"  type="button" value="Submit" onclick="$(this).saveSlot(\''+editAppointId+'\',\''+starttime+'\');"/>'
			 		//str = str + '<input class = "buttons"  type="button" value="Cancel" onclick="$(this).cancelSlot();"/>'
			 		//document.getElementById('submitslot').innerHTML = str;
			 		
			 	/*	var str = '<input class = "buttons" type="button" value="Submit" onclick="$(this).blockSave(\''+editAppointId+'\',\''+stime+'\');"/>'
			 		str = str + '<input class = "buttons"  type="button" value="Cancel" onclick="$(this).cancelSlot();"/>'
			 		document.getElementById('submitblockslot').innerHTML = str;*/
						
						
//						 loading(); // loading
//						setTimeout(function(){ // then show popup, deley in .5 second
//							loadPopup(); // function show popup 
//						}, 500); // .5 second
//						
						
						
						return false;
			 }


function printlabel(){
	openPopup('patientlabelIpd?selectedid='+patientId+'');
}
function openopdpreviewwindow(){
	var t = 'formtarget';

	
	
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	oldwindow.focus();

//	$('#newloaderPopup').modal('show');
	
	
	document.getElementById('printdashboardfrm').submit();
}

function showLetterHeadforayush(editAppointId,patientId){

   openPopup('blankletterheadNotAvailableSlot?patientid='+patientId+'&appointmentid='+editAppointId+'');

}

function redirectToViewAcc1(clientid){
	patientId = clientid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	
	document.getElementById('viewAccClientid').value = patientId;
	document.getElementById('viewAccclientname').value = editClientName;
	document.getElementById('viewAccPayby').value = editwhopay;
	document.getElementById('viewAccLocationid').value = loc;
	document.getElementById('viewAccthirdparty').value = "All";
	document.getElementById('viewAcctransactionType').vlue = "All";
	document.getElementById('viewAccfromDateid').value = "";
	document.getElementById('viewAcctoDateid').value = "";
	
	
	var t = 'formtarget';

	
	
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	
	var oldwindow = window.open("", t,
			"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
					+ ",top=" + 50);
	
	oldwindow.focus();

//	$('#newloaderPopup').modal('show');
	
	
	document.getElementById('view_acc_frm').submit();
	


	return true;

}
