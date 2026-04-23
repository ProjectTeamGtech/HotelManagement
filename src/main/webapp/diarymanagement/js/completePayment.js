window.onload = function(){

var payby = read_cookie("cookiePayBy");
	if(payby == 'Third Party'){
		document.getElementById('user').value = read_cookie("cookieThirdPartyName");
		document.getElementById('address').value = read_cookie("cookieThirdPartyName");
	}
}


function updatePayment(invoiceid){
	if(document.getElementById('paid').checked==true){
		setPayment(invoiceid,1);
		
		var payby = read_cookie("cookiePayBy");
		if(payby == 'Third Party'){
			var cookieConsultationLimit = read_cookie("cookieConsultationLimit");
			var tratmentepisodeid = read_cookie("cookieTreatmentEpisode"); 
			var sessions = parseInt(cookieConsultationLimit) - 1;
			updateConsultationLimit(sessions,tratmentepisodeid);
		}
		
		
	}else{
		setPayment(invoiceid,0);
		
		var payby = read_cookie("cookiePayBy");
		if(payby == 'Third Party'){
			var cookieConsultationLimit = read_cookie("cookieConsultationLimit");
			var tratmentepisodeid = read_cookie("cookieTreatmentEpisode"); 
			updateConsultationLimit(cookieConsultationLimit,tratmentepisodeid);
		}
	}
}


function updateConsultationLimit(sessions,tratmentepisodeid){
	
	var url = "changeTreatmentEpisode?sessions="+sessions+"&tratmentepisodeid="+tratmentepisodeid+" ";	
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updateConsultationLimitRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function updateConsultationLimitRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
		}
	}
}




function setPayment(invoiceid,paid){
	
	var payAmount = document.getElementById('payAmount').value;
	var howpaid = document.getElementById('howpaid').value;
	var invoiceDate = document.getElementById('invoiceDate').value;
	
	var url = "payCompleteApmt?invoiceid="+invoiceid+"&payAmount="+payAmount+"&howpaid="+howpaid+"&invoiceDate="+invoiceDate+"&paid="+paid+" ";	
   
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

function setPaymentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
		}
	}

}



