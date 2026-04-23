var chargesReportFilename = "";
var invoiceReportFilename = "";
var paymentReportFilename = "";



function saveAsPdfChargesReport(){
	//$.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a moment...</h3>' });

	var fromDate = document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;
	var url = "saveAsPdfChargeReportChargesRpt?fromDate="+fromDate+"&toDate="+toDate+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveChargePdfRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function saveChargePdfRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			chargesReportFilename = req.responseText;
			
		//	$(document).ajaxStop($.unblockUI);
			document.getElementById('previewChargeRpt').style.display = "block";
			jAlert('success', 'PDF Created successfully!!', 'Charge Report');
			document.getElementById('sendMailChargeRpt').style.display = "block";
			
			
         }
	}
}

function saveAsPdfInvoiceReport(){
	//$.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a moment...</h3>' });

	var fromDate = document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;
	var url = "saveAsPdfInvoiceReportChargesRpt?fromDate="+fromDate+"&toDate="+toDate+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveInvoicePdfRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}
function saveInvoicePdfRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			invoiceReportFilename = req.responseText;
		//	$(document).ajaxStop($.unblockUI);
			
			document.getElementById('previewInvoiceRpt').style.display = "block";
			jAlert('success', 'PDF Created successfully!!', 'Invoive Report');
			 
			
         }
	}
}
function saveAsPdfPaymentReport(){
//	$.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a moment...</h3>' });

	var fromDate = document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;
	var url = "saveAsPdfPaymentReportChargesRpt?fromDate="+fromDate+"&toDate="+toDate+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveAsPdfPaymentReportRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function saveAsPdfPaymentReportRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			paymentReportFilename = req.responseText;
			
		//	$(document).ajaxStop($.unblockUI);
			document.getElementById('previewPaymentRpt').style.display = "block";
			jAlert('success', 'PDF Created successfully!!', 'Payment Report');
			
			 
			
         }
	}
}
function openSendMailChargeRptPopup(){
	document.getElementById('pdfChargesReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/chargesReport/'+chargesReportFilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="chargesReportpdf" checked="checked"> </h4>';

	$('#sendEmailChargeRptPopup').modal( "show" );
}
function sendChargesReportMail(){
	$('#sendEmailChargeRptPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	var to = document.getElementById('chargesReportEmail').value;
	var cc = document.getElementById('chargesReportccEmail').value;
	var subject = document.getElementById('chargesReportSubject').value;
	
	var emailBody = nicEditors.findEditor( "chargesReportEmailBody" ).getContent();	
	var file = chargesReportFilename;
	
	//alert(emailBody);
	var url = "sendMailChargeReportChargesRpt?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"&filename="+chargesReportFilename+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendChargesReportMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function sendChargesReportMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				$('#sendEmailChargeRptPopup').unblock();
				
				jAlert('success', 'Mail Send Successfully !!', 'Charges Report');
				$('#sendEmailChargeRptPopup').modal( "hide" );
				
	         }
		}
	
}
function openSendMailInvoiceRptPopup(){
	
	$('#sendEmailInvoiceRptPopup').modal( "show" );
}
function sendInvoiceReportMail(){
	$('#sendEmailInvoiceRptPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	var to = document.getElementById('invoiceReportEmail').value;
	var cc = document.getElementById('invoiceReportccEmail').value;
	var subject = document.getElementById('invoiceReportSubject').value;
	
	var emailBody = nicEditors.findEditor( "invoiceReportEmailBody" ).getContent();		
	var file = invoiceReportFilename;
	
	//alert(emailBody);
	var url = "sendMailInvoiceReportChargesRpt?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"&filename="+chargesReportFilename+"";

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
function openSendMailPaymentRptPopup(){
	document.getElementById('pdfPaymentReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/chargesReport/'+paymentReportFilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="paymentReportpdf" checked="checked"> </h4>';
	$('#sendEmailPaymentRptPopup').modal( "show" );
}
function sendPaymentReportMail(){
	$('#sendEmailPaymentRptPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	var to = document.getElementById('paymentReportEmail').value;
	var cc = document.getElementById('paymentReportccEmail').value;
	var subject = document.getElementById('paymentReportSubject').value;
	
	var emailBody = nicEditors.findEditor( "paymentReportEmailBody" ).getContent();		
	var file = paymentReportFilename;
	
	//alert(emailBody);
	var url = "sendMailPaymentReportChargesRpt?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"&filename="+chargesReportFilename+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendPaymentReportMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function sendPaymentReportMailRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			$('#sendEmailPaymentRptPopup').unblock();
			jAlert('success', 'Mail Send Successfully !!', 'Payment Report');
			$('#sendEmailPaymentRptPopup').modal( "hide" );
			
         }
	}
}

function printinvreport(){
	document.getElementById('datesortdid').innerHTML = 'Invoice Date';
	window.print();
}

function movetosecondary(){
 var selectedinvoiceid = 0;
			     $('.caseh').each(function() { //loop through each checkbox
			        // this.checked = true;  //select all checkboxes with class "checkbox1" 
			        if(this.checked==true){
			        	selectedinvoiceid = selectedinvoiceid + ',' + this.value;
			        }
			         
			     });
			     
	if(document.getElementById('invoicecategory').value==2 ){
		jAlert('error', 'Please select Primary or Secondary category to move invoice ', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		document.getElementById('hdnprimaryinvoice').value = selectedinvoiceid;
		document.getElementById('invoicerportfrm').submit();
	}
	
}

function showInvoicereportonly(){
	var fromdate = document.getElementById("fromDate").value;
	var todate = document.getElementById("toDate").value;
	if(fromdate==''){
		jAlert('error', "Please enter from date!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(todate==''){
		jAlert('error', "Please enter to date!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		//parts[1] + "/" + parts[0] + "/" + parts[2]
		var parts = fromdate.split("/");
		fromdate = parts[1] + "/" + parts[0] + "/" + parts[2];
		
		var parts1 = todate.split("/");
		todate = parts1[1] + "/" + parts1[0] + "/" + parts1[2];
		
		var date1 = new Date(fromdate);
		var date2 = new Date(todate);
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
		if(diffDays>=31){
			jAlert('error', "Date differnce is greater one month!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			document.getElementById('hdnprimaryinvoice').value = '0';
			document.getElementById('invoicerportfrm').submit();
		}
		
	}
	
	
}