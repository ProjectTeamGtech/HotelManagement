function openSendMailInvoiceRptPopup(){
	
	$('#sendEmailInvoiceRptPopup').modal( "show" );
}
function sendInvoiceReportMail(){
	$('#sendEmailInvoiceRptPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	var to = document.getElementById('invoiceReportEmail').value;
	var cc = document.getElementById('invoiceReportccEmail').value;
	var subject = document.getElementById('invoiceReportSubject').value;
	
	var emailBody = nicEditors.findEditor( "invoiceReportEmailBody" ).getContent();		
	//var file = document.getElementById('invoiceReportpdf').value;
	
	//alert(emailBody);
	var url = "sendmailMis?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendInvoiceReportMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function sendInvoiceReportMailRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			$('#sendEmailInvoiceRptPopup').unblock();
			jAlert('success', 'Mail Send Successfully !!', 'Invoice Report');
			$('#sendEmailInvoiceRptPopup').modal( "hide" );
         }
	}
}