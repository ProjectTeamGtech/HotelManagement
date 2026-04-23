var clientListfilename = "";
var currentTreatmentNofutureApmtsfilename = "";

function saveAsPdfClientList(){
	//$.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a moment...</h3>' });
	var url = "saveAsPdfClientListClientRpt";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveAsPdfClientListRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function saveAsPdfClientListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			clientListfilename = req.responseText;
		//	$(document).ajaxStop($.unblockUI);
			
			document.getElementById('previewClientList').style.display = "block";
			jAlert('success', 'PDF Created successfully!!', 'Client List');
			 
			
         }
	}
}
function saveAsPdfCurrentTreatNofutureApmts(){
	//$.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a moment...</h3>' });

	var url = "saveAsPdfCurrentTreatNofutureApmtsClientRpt";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveAsPdfCurrentTreatNofutureApmtsRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function saveAsPdfCurrentTreatNofutureApmtsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			currentTreatmentNofutureApmtsfilename = req.responseText;
		//	$(document).ajaxStop($.unblockUI);
			document.getElementById('previewCurrentTreNoApmt').style.display = "block";
			jAlert('success', 'PDF Created successfully!!', 'No Future Apmts');
			 
			
         }
	}
}
function openSendMailClientListPopup(){
	document.getElementById('pdfClientListMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/ClientReport/'+clientListfilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="clientListpdf" checked="checked"> </h4>';
	$('#sendEmailClientListPopup').modal( "show" );
}
function sendClientListMail(){
	//$.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' });
	$('#sendEmailClientListPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	var to = document.getElementById('clientListEmail').value;
	var cc = document.getElementById('clientListccEmail').value;
	var subject = document.getElementById('clientListSubject').value;
	
	var emailBody = nicEditors.findEditor( "clientListEmailBody" ).getContent();		
	var file = document.getElementById('clientListpdf').value;
	
	//alert(emailBody);
	var url = "sendMailClientListClientRpt?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"&filename="+clientListfilename+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendClientListMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}
function sendClientListMailRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			$('#sendEmailClientListPopup').unblock();
			jAlert('success', 'Mail Send Successfully !!', 'Client List');
			$('#sendEmailClientListPopup').modal( "hide" );

			
         }
	}
}
function openSendMailCurrentTreatNoFutureApmtsPopup(){
	document.getElementById('pdfnoFutAptsMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/ClientReport/'+currentTreatmentNofutureApmtsfilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="noFutureApmtspdf" checked="checked"> </h4>';
	$('#sendEmailCurrentTreatmentNoFutureApmtsPopup').modal( "show" );
}
function sendCurrentTreatNofutureApmtsMail(){
	$('#sendEmailCurrentTreatmentNoFutureApmtsPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	var to = document.getElementById('noFutAptsEmail').value;
	var cc = document.getElementById('noFutAptsccEmail').value;
	var subject = document.getElementById('noFutAptsSubject').value;
	var emailBody = document.getElementById('noFutAptsEmailBody').value;		
	var file = document.getElementById('noFutureApmtspdf').value;
	
	//alert(emailBody);
	var url = "sendCurrentTreatNofutureApmtsMailClientRpt?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"&filename="+currentTreatmentNofutureApmtsfilename+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendCurrentTreatNofutureApmtsMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function sendCurrentTreatNofutureApmtsMailRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			$('#sendEmailCurrentTreatmentNoFutureApmtsPopup').unblock();
			
			jAlert('success', 'Mail Send Successfully !!', 'Client List');
			$('#sendEmailCurrentTreatmentNoFutureApmtsPopup').modal( "hide" );
			
         }
	}
}