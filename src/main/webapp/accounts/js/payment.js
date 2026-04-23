function setPayment(invoiceid){
	var whoPay = "";
	var payBuy = document.getElementById('payby').value;
	
	if(payBuy==0){
		whoPay = document.getElementById('client').value;
	}
	else{
		whoPay = document.getElementById('insuranceCompany').value;
	}
	var clientId = document.getElementById('clientId').value;
	var payAmount = document.getElementById('payAmount').value;
	var howpaid = document.getElementById('howpaid').value;
	var invoiceDate = document.getElementById('invoiceDate').value;
	
	if(howpaid==0){
		jAlert('error', 'Please select payment mode.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}else{
		var url = "payAccounts?invoiceid="+invoiceid+"&payAmount="+payAmount+"&howpaid="+howpaid+"&invoiceDate="+invoiceDate+"&whoPay="+whoPay+"&clientId="+clientId+" ";	
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setPaymentRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	

}

function setPaymentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
            //jAlert('success', 'Payment has been done successfully!!', 'Payment');
			tempAlert("Payment has been done successfully", 5000);

		}
	}

}

function setPayBy(invoiceid,payby){
	var url = "paybyAccounts?invoiceid="+invoiceid+"&payby="+payby+" ";	
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setPayByRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setPayByRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
		}
	}
}
