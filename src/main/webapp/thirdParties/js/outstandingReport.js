var tpId = "";
var clientId ="";
var balanceTotal = 0.0;
var d = new Date();
var month = new Array();
month[0] = "January";
month[1] = "February";
month[2] = "March";
month[3] = "April";
month[4] = "May";
month[5] = "June";
month[6] = "July";
month[7] = "August";
month[8] = "September";
month[9] = "October";
month[10] = "November";
month[11] = "December";
var n = month[d.getMonth()];


$(document).ready(function(){
	
	if(document.getElementById('outstanding_report_frm_payby').value=='Client'){
		
		document.getElementById('showclientdivid').style.display='';
	}
	
	if(document.getElementById('outstanding_report_frm_payby').value=='Third Party'){
		
		document.getElementById('showtpdivid').style.display='';
	}
	
});	



function sendSaveEmail(){
	var id = document.getElementById('thirdPartyId').value;
	var name = document.getElementById('thirdPartyName').value;
	var email = document.getElementById('thirdPartEmail').value;
	var subject = document.getElementById('subject').value;
	var ccEmail = document.getElementById('ccEmail').value;
	var notes = nicEditors.findEditor( "emailBody" ).getContent();
	/* var regX = /(<([^>]+)>)/ig;
	 notes = notes.replace(regX, "");*/
     //alert(notes.replace(regX, ""));
    var date = new Date();
    
    var h1 = date.getHours();
    var m1 = date.getMinutes();
    var s1 = date.getSeconds();
    var time = h1+":"+m1+":"+s1;
   
    var d1 = date.getDate();
   
    var m1 = month[d.getMonth()];
    var y1 = date.getFullYear();
    date = d1+"/"+m1+"/"+y1;
   
    var url = "sendSaveEmailOutstandingReport?id="+id+"&name="+name+"&email="+email+"&subject="+subject+"&notes="+notes+"&date="+date+"&time="+time+"&ccEmail="+ccEmail+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = sendSaveEmailRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function sendSaveEmailRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
            jAlert('success', 'Email Send Sucessfully!!', 'Success modal');

			$('#sendEmailPopUp').modal( "hide" );
			$('#exceedLmtActionPopUp').modal( "hide" );
			location.reload();
			
         }
	}
}

function sendSaveSMS(){
	var id = document.getElementById('thirdPartyId').value;
	var name = document.getElementById('thirdPartyName').value;
	var contactno = document.getElementById('thirdPartyContactno').value;
	var notes = document.getElementById('smsNote').value;
	
    var date = new Date();
  
    var h1 = date.getHours();
    var m1 = date.getMinutes();
    var s1 = date.getSeconds();
    var time = h1+":"+m1+":"+s1;
   
    var d1 = date.getDate();
    var m1 = month[d.getMonth()];
    
    var y1 = date.getFullYear();
    date = d1+"/"+m1+"/"+y1;
   
    var url = "sendSaveMessageOutstandingReport?id="+id+"&name="+name+"&contactno="+contactno+"&notes="+notes+"&date="+date+"&time="+time+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange =sendSaveSMSRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function sendSaveSMSRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
            jAlert('success', 'Message Sent Sucessfully!!', 'Success modal');

			$('#sendEmailPopUp').modal( "hide" );
			$('#exceedLmtActionPopUp').modal( "hide" );
			location.reload();
			
         }
	}
}

function saveCommunication(){
	var id = document.getElementById('thirdPartyId').value;
	var name = document.getElementById('thirdPartyName').value;
	var contactno = document.getElementById('thirdPartyContactno').value;
	var notes = document.getElementById('callNote').value;
	
    var date = new Date();
    var h1 = date.getHours();
    var m1 = date.getMinutes();
    var s1 = date.getSeconds();
    var time = h1+":"+m1+":"+s1;
   
    var d1 = date.getDate();
    var m1 = month[d.getMonth()];
    var y1 = date.getFullYear();
    date = d1+"/"+m1+"/"+y1;
   
    var url = "callDataSaveOutstandingReport?id="+id+"&name="+name+"&contactno="+contactno+"&notes="+notes+"&date="+date+"&time="+time+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveCommunicationRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function saveCommunicationRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
            jAlert('success', 'Call Data save Sucessfully!!', 'Success modal');

			$('#sendEmailPopUp').modal( "hide" );
			$('#exceedLmtActionPopUp').modal( "hide" );
			location.reload();
			
         }
	}
}
function showClients(id){
	var url = "getAllClientOfThirdPartyOutstandingReport?id="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showClientsRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function showClientsRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("patientList").innerHTML = req.responseText;
			$('#clientListPopUp').modal( "show" );
			
         }
	}
}

function showDetails(who){
	
	document.getElementById('outstanding_report_frm').submit();
	
}
function showClientDiv(divId,id){
	tpId = id;
	if(document.getElementById(divId).style.display == ""){
		document.getElementById(divId).style.display = "none";
	}
	else{
	document.getElementById(divId).style.display = ""; 
	
	var url = "getClientListOutstandingReport?id="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showClientDivRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function showClientDivRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("hiddenClientsDiv1"+tpId+"").innerHTML = req.responseText;
				
				document.getElementById("hiddenClientsDiv"+tpId+"").style.display = ""; 
				document.getElementById("hiddenClientsDiv1"+tpId+"").style.display = ""; 
				
	         }
		}
	}
function showInnerChildInvoiceDiv(divid,clientId1,tpId1){
	tpId = tpId1;
	clientId = clientId1;
	if(document.getElementById(divid).style.display == ""){
		document.getElementById(divid).style.display = "none";
	}
	else{
	document.getElementById(divid).style.display = ""; 
	
	var url = "showChildInvoiceOutstandingReport?clientId="+clientId+"&tpId="+tpId+"";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showInnerChildInvoiceDivRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
	}
}
function showInnerChildInvoiceDivRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("divid1_"+clientId+"_"+tpId+"").style.display = ""; 
				document.getElementById("divid_"+clientId+"_"+tpId+"").style.display = ""; 
				document.getElementById("divid1_"+clientId+"_"+tpId+"").innerHTML = req.responseText;
				 
				
	         }
		}

	}


var addedId = "";
function addCredit(id,invoiceId,balance,payamtTrID,balanceTotal1,selectAllId){
	
	if(document.getElementById(id).checked){
		balanceTotal = parseFloat(balanceTotal) + parseFloat(balance);
		parseFloat(balanceTotal);
		document.getElementById(payamtTrID).innerHTML = "Payment Amount: "+currencySign+" "+balanceTotal+"";
	}
	else{
		balanceTotal = parseFloat(balanceTotal) - parseFloat(balance);
		document.getElementById(payamtTrID).innerHTML = "Payment Amount: "+currencySign+" "+balanceTotal+"";
	}
	
}


var payamtTrID = "";

function setSelectAll(tpId,clientId,balancetotal1,payamtTrID1,tableId){
	payamtTrID = payamtTrID1;
	balanceTotal = balancetotal1;
	var divid = "divid_"+clientId+"_"+tpId+"";
	 setInvoiceInSession(divid,tpId,clientId);
	 
	
	
	

}
function setInvoiceInSession(divid,tpId1,clientId1){
	tpId = tpId1;
	clientId = clientId1;
	var url = "showChildInvoiceInSessionOutstandingReport?clientId="+clientId+"&tpId="+tpId+"";
		
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setInvoiceInSessionRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}
	function setInvoiceInSessionRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					var data = req.responseText;
					
					
				
					var id = "selectAlls_"+tpId+"_"+clientId;
					
					
					
					if(document.getElementById(id).checked==true){
						
						document.getElementById(id).checked = true;
						var temp = data.split('/');
						
						 for(var i=1;i<temp.length;i++){
							 var chkid = "ch-"+temp[i];
							 
							 document.getElementById(chkid).checked = true;
						 }
						document.getElementById(payamtTrID).innerHTML = "Payment Amount: "+currencySign+" "+balanceTotal;
						
					}else{
						balanceTotal = 0;
						document.getElementById(id).checked = false;
						var temp = data.split('/');
						
						 for(var i=1;i<temp.length;i++){
							 var chkid = "ch-"+temp[i];
							 
							 document.getElementById(chkid).checked = false;
						 }
						document.getElementById(payamtTrID).innerHTML = "Payment Amount: "+currencySign+" 0.0";
						
						
					}
					 
					
		         }
			}

		}
	
function recordPayment(tpId,clientId,tableId){
	clientId1 = clientId;
	tpId1 = tpId;
	var allCheck = "";
	var id = "selectAlls_"+tpId+"_"+clientId;
	if(document.getElementById(id).checked==true){
		allCheck = 'Yes';
	}
	else{
		allCheck = 'No';
	}
	
	var modeofPaymentId = "howpaid_"+tpId+"_"+clientId+"";
	var modeofPayment = document.getElementById(modeofPaymentId).value;
	var data = "";
	  var chk=$("#"+tableId+" :checked").size();
	  
	  if(chk > 1){
          
  		  $("#"+tableId+" :checked").each(function() {
			
			 data = data + "," + $(this).val();
			 
		});
  		if(document.getElementById(modeofPaymentId).value == "0"){
  			jAlert('error', 'Please Select Mode of Payment', 'Error Dialog');
  			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
  		}
  		else{
  		 var url = "savePaymentOutstandingReport?tpId="+tpId+"&clientId="+clientId+"&allCheck="+allCheck+"&modeofPayment="+modeofPayment+"&data="+data+"&balanceTotal="+balanceTotal+"";
			
		}
	  }else{
			$("#"+tableId+" :checked").each(function() {
				jAlert('error', 'Please select atleast one charge', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			});
		}
		
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = recordPaymentRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		
		
		
}	

function recordPaymentRequest(){
	
		if (req.readyState == 4) {
				if (req.status == 200) {
					document.getElementById("divid1_"+clientId1+"_"+tpId1+"").style.display = ""; 
					document.getElementById("divid_"+clientId1+"_"+tpId1+"").style.display = ""; 
					document.getElementById("divid1_"+clientId1+"_"+tpId1+"").innerHTML = req.responseText;
					
				
					 
					
		         
			}

		}
}

function showClientInvoiceDiv(divid,clientId1){
	clientId = clientId1;
	if(document.getElementById(divid).style.display == ""){
		document.getElementById(divid).style.display = "none";
	}
	else{
	document.getElementById(divid).style.display = ""; 
	
	var url = "showClientSeflInvoiceOutstandingReport?clientId="+clientId+"";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showClientInvoiceDivRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function showClientInvoiceDivRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("hiddenClientsInvoiceDiv1"+clientId+"").style.display = ""; 
				document.getElementById("hiddenClientsInvoiceDiv"+clientId+"").style.display = ""; 
				document.getElementById("hiddenClientsInvoiceDiv1"+clientId+"").innerHTML = req.responseText;

				 
				
	         }
		}

	}



function showNewClientDiv(id){
	if(document.getElementById(id).style.display=='none'){
		document.getElementById(id).style.display = '';
	}else{
		document.getElementById(id).style.display = 'none';
	}
}

function showNewClientInvoiceDiv(id){
	if(document.getElementById(id).style.display=='none'){
		document.getElementById(id).style.display = '';
	}else{
		document.getElementById(id).style.display = 'none';
	}
}


function showNewClientInvoiceDiv(id){
	if(document.getElementById(id).style.display=='none'){
		document.getElementById(id).style.display = '';
	}else{
		document.getElementById(id).style.display = 'none';
	}
}


//checkbox selection


//totalsubjectid= totalsubjectid + "," +	 $(this).val();

function selftblechkbox(tableid,size){
	
	
	if(document.getElementById('outstanding_report_frm_payby').value==0){
		
		document.getElementById(tableid).checked = false;
		
		jAlert('error', 'Please Select Payment By', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else if(document.getElementById("client").value==""){
		
		document.getElementById(tableid).checked = false;
		
		jAlert('error', 'Please Search Client', 'Error Dialog');
				
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else{
		
		var totalsubjectid=0;
		
		var ch = document.getElementById(tableid).checked;
		
		
		if(ch==true){
			for(i=0;i<size;i++){
				document.getElementById(i+tableid).checked=true;
				totalsubjectid= totalsubjectid + "," +	 document.getElementById(i+tableid).value;
			}
		}else{
			for(i=0;i<size;i++){
				document.getElementById(i+tableid).checked=false;
				totalsubjectid= totalsubjectid + "," +	 document.getElementById(i+tableid).value;
			}
		}
		
		collectInvoiceListAjax(totalsubjectid,ch);
		
		
	}
	
	
	 
}

function tptblcheckbox(tableid,size){
	
	if(document.getElementById('outstanding_report_frm_payby').value==0){
		
		document.getElementById(tableid).checked = false;
		
		jAlert('error', 'Please Select Payment By', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else if(document.getElementById('thirdParty').value==0){
			document.getElementById(tableid).checked = false;
			
			jAlert('error', 'Please Select Third Party', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}
	
	else{
		
		var totalsubjectid=0;
		
		var ch = document.getElementById(tableid).checked;
		
		if(ch==true){
			for(i=0;i<size;i++){
				document.getElementById(i+tableid).checked=true;
				totalsubjectid= totalsubjectid + "," +	 document.getElementById(i+tableid).value;
			}
		}else{
			for(i=0;i<size;i++){
				document.getElementById(i+tableid).checked=false;
				totalsubjectid= totalsubjectid + "," +	 document.getElementById(i+tableid).value;
			}
		}
		
		collectInvoiceListAjax(totalsubjectid,ch);
		
	}
	
	
}


function selfeachinvoice(id,value){
	
	if(document.getElementById('outstanding_report_frm_payby').value==0){
		
		document.getElementById(id).checked = false;
		
		jAlert('error', 'Please Select Payment By', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else if(document.getElementById("client").value==""){
		
		document.getElementById(id).checked = false;
		
		jAlert('error', 'Please Search Client', 'Error Dialog');
				
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	
	else{
		
		var totalsubjectid=0;
		var ch = document.getElementById(id).checked;
		totalsubjectid= totalsubjectid + "," +	 value;
		collectInvoiceListAjax(totalsubjectid,ch);
	}
	
	
}


function tpeachinvoice(id,value){
	if(document.getElementById('outstanding_report_frm_payby').value==0){
		
		document.getElementById(id).checked = false;
		
		jAlert('error', 'Please Select Payment By', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else if(document.getElementById('thirdParty').value==0){
		
		document.getElementById(id).checked = false;
		
		jAlert('error', 'Please Select Third Party', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
}
	
	else{
		
		var totalsubjectid=0;
		var ch = document.getElementById(id).checked;
		totalsubjectid= totalsubjectid + "," +	 value;
		collectInvoiceListAjax(totalsubjectid,ch);
		
	}
	
}



function collectInvoiceListAjax(invoicelist,status){

	var url = "collectOutstandingReport?invoicelist="+invoicelist+"&status="+status+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = collectInvoiceListAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function collectInvoiceListAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
}


function createList(){
	
	if(document.getElementById('outstanding_report_frm_payby').value==0){
		
		jAlert('error', 'Please Select Payment By', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else{
		
		
		
		document.getElementById('topThirdpartyid').value = document.getElementById('thirdParty').value;
		
		var t = 'formtargetoutstanding';


		
		var left = (screen.width / 2) - (700 / 2);
		var top = (screen.height / 2) - (550 / 2);
		var oldwindow = window.open("", t,
				"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
						+ ",top=" + 50);
		
		oldwindow.focus();
		
		document.getElementById('outstandingrportfrm').submit();
	}
	
}


function reordPayment(){
	if(document.getElementById('outstanding_report_frm_payby').value==0){
		
		jAlert('error', 'Please Select Payment By', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else if(document.getElementById('howpaid').value==0){
		
		jAlert('error', 'Please Select Payment Mode', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	
	else{
		
		document.getElementById('recThirdpartyid').value = document.getElementById('thirdParty').value;
		document.getElementById('recpaymentfrm').submit();
	}
}


function showPopUp(){
	
	var url = "showListClient";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAllPatientPopUpRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	function showAllPatientPopUpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allPatient").innerHTML = req.responseText;
				
				$('#clientSearch').modal('show');
				
	         }
		}
	}
	
	
function setPatientName(name,id,type,typeName){
	//deleteChargeAccountsTableAjax();
	document.getElementById("clientId").value = id;
	var url = "getFullNameClient?id="+id+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setPatientNameRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}
function setPatientNameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var clientFullName = req.responseText;
		
			document.getElementById("client").value = clientFullName;
			
			
			
			$('#clientSearch').modal('hide');
			
			document.getElementById("outstanding_report_frm").submit();
			
			
			
		}
	}

}


function searchPatient(){
	var searchText = document.getElementById("searchText1").value;
	var url = "searchParticularPatientClient?searchText="+searchText+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = searchPatientRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function searchPatientRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allPatient").innerHTML = req.responseText;
	         	
				
	         }
		}
	}



function checkNumericOnly(chvalue,balance,id){
	if (isNaN(chvalue)){
		//alert('numeric value')
		
		document.getElementById(id).value = balance;
		
		jAlert('error', 'Please enter valid ammount. ', 'Error Dialog')
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
}

function sendsmsormailpopup(clientid){
	document.getElementById("newclientid").value=clientid;
	//$('#exceedLmtActionPopUp2').model('show');
}
function opensendsmspopup() {
	var clientid = document.getElementById("newclientid").value;
	var url = "getclientmoboremailOutstandingReport?clientid="+clientid+"&val=0";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = opensendsmspopupRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function opensendsmspopupRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("thirdPartyContactno").value = req.responseText;
			//$('#sendSMSPopUp2').model('show');
	    }
	}
}

function opensendemailpopup() {
	var clientid = document.getElementById("newclientid").value;
	var url = "getclientmoboremailOutstandingReport?clientid="+clientid+"&val=1";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = opensendemailpopupRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function opensendemailpopupRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("thirdPartEmail").value = req.responseText;
			//$('#sendEmailPopUp2').model('show');
	    }
	}
}
