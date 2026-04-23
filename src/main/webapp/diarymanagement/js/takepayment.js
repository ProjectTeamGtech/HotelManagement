function ShowChashPaymentPopup(){
$( "#takepaymentmodel" ).modal( "show" );
}

function setAmountDue(){
	var totalAmount = document.getElementById('totalamount').value;
	var disctype = document.getElementById('disctype').value;
	var discount = document.getElementById('discount').value;
	var discountAmt = totalAmount * (discount/100);
	if(disctype==1){
	 discountAmt = discount;
	}
	var amountDue = totalAmount - discountAmt;
	
	document.getElementById('payAmount').value = amountDue;
}

var totalamt1 = 0;

function saveCashDesk(){

	var t1 = document.getElementById('payAmount').value;
	// totalamt1 = document.getElementById('hdntotal').value;
	 var check= document.getElementById('check');
	 var hiddenclinic=document.getElementById('hiddenclinic').value;
	 if(document.getElementById('howpaid').value==0){
		
		jAlert('error', 'Please select payment mode.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else if((hiddenclinic=='markhosp' || hiddenclinic=='marktest') && !check.checked){
	
	  
		jAlert('error', 'Please Check Checkbox .', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		
		
	  
    }
	else if( document.getElementById('howpaid').value=='prepayment' && document.getElementById('hiddenbalence').value=='false'){
		jAlert('error', 'No credit balence. Please select other payment mode ', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	
	}else if(parseFloat(t1) > parseFloat(totalamt1)){
		//jAlert('error', 'Paid amt is greater than balance Amount.', 'Error Dialog');
		//document.getElementById('crdpaidamt').innerHTML = currencySign+t1;
		//document.getElementById('crdbalamt').innerHTML = currencySign+totalamt1;
		var r=confirm("Are you sure you want to Take Payment");
				if (r==true){
				 $( "#takepaymentmodel" ).modal( "hide" );
				 if(isafterbookapmtcashdesk==0){
					 saveAppointmentSlot(0);
				 }else if(isafterbookapmtcashdesk==1){
					 saveopdcashdesknews();
				 }
				 //saveAppointmentSlot(0);
				 $( "#appointment" ).modal( "hide" );
				}
		var remain = t1 - totalamt1;
		
		//document.getElementById('crdremainamt').innerHTML = currencySign + remain;
		$( '#creditnotepopup' ).modal( "show" );
		
	}else if(document.getElementById('howpaid').value=='prepayment'){
		if(parseFloat(t1)>parseFloat(document.getElementById('prepaymntamntid').value)){
			jAlert('error', 'Credit balence can not be less than payamount. ', 'Error Dialog')
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			
			if(document.getElementById('invcetype').value==0){
				jAlert('error', 'Please select invoice type', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				//var creditnote = document.getElementById('creditnote').value;
		
				//document.getElementById('creditNotes').value = creditnote;
				var r=confirm("Are you sure you want to Take Payment");
				if (r==true){
				 $( "#takepaymentmodel" ).modal( "hide" );
				 if(isafterbookapmtcashdesk==0){
					 saveAppointmentSlot(0);
				 }else if(isafterbookapmtcashdesk==1){
					 saveopdcashdesknews();
				 }
				 //saveAppointmentSlot(0);
				 $( "#appointment" ).modal( "hide" );
				}
			}
			
		}
	}else if(t1<0){
		jAlert('error', 'Payment Received Amount is Not less than 0', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	
	
	else{
		
		
		
		if(document.getElementById('invcetype').value==0){
				jAlert('error', 'Please select invoice type', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				//var creditnote = document.getElementById('creditnote').value;
		
				//document.getElementById('creditNotes').value = creditnote;
				var r=confirm("Are you sure you want to Take Payment");
				if (r==true){
				 $( "#takepaymentmodel" ).modal( "hide" );
				 if(isafterbookapmtcashdesk==0){
					 saveAppointmentSlot(0);
				 }else if(isafterbookapmtcashdesk==1){
					 saveopdcashdesknews();
				 }
				 //saveAppointmentSlot(0);
				 $( "#appointment" ).modal( "hide" );
				}
			}
	}
}

function getTakePaymentCharge(){
	
	
	document.getElementById('disctype').value = 0;
	document.getElementById('discount').value = 0;
	
	var clientId = "0";
	if(document.getElementById('clientId')){
		clientId = document.getElementById('clientId').value;
	}
	var apmtType = document.getElementById('apmtType').value;
	/*var url = "chargeNotAvailableSlot?apmtType="+apmtType+" ";*/
	var url = "chargeopdotBookAppointmentAjax?apmtType="+apmtType+"&clientId="+clientId+" ";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getTakePaymentChargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getTakePaymentChargeRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			isafterbookapmtcashdesk=0;
			var str=req.responseText;
	        var data=str.split("~");
			document.getElementById('totalamount').value = parseFloat(data[0])+parseFloat(data[1]);
			totalamt1 = parseFloat(data[0])+parseFloat(data[1]);
			document.getElementById('payAmount').value  = totalamt1;
			document.getElementById('opdotcharge').value=data[0];
			document.getElementById('opdotregcharge').value=data[1];
			
			if(!(data[1]>0)){
				document.getElementById('opdotregcharge').readOnly =false;
			}
			
			 var isot = document.getElementById('radio3').checked;
			 if(isot==true){
				 totalamt1 = 0;
				 var potcharge = document.getElementById('potcharge').value;
				 var psurcharge = document.getElementById('psurcharge').value;
				 var panetcharge = document.getElementById('panetcharge').value;
				 var sic = document.getElementById('sic').value;
				 if(potcharge==""){
					 potcharge="0";
				 }
				 if(psurcharge==""){
					 psurcharge="0";
				 }
				 if(panetcharge==""){
					 panetcharge="0";
				 }
				 if(sic==""){
					 sic="0";
				 }
				 var assistaffcharge = 0;
				 if(document.getElementById("assistaffcharge")){
					 assistaffcharge = document.getElementById("assistaffcharge").value;
					 if(assistaffcharge==""){
						 assistaffcharge="0";
					 }
				 }
				 totalamt1 = parseFloat(potcharge) + parseFloat(psurcharge) + parseFloat(panetcharge) + parseFloat(sic) + parseFloat(assistaffcharge);
				 document.getElementById('totalamount').value = totalamt1;
				 document.getElementById('payAmount').value  = totalamt1;
				 
				document.getElementById('opdotcharge').value=totalamt1;
				document.getElementById('opdotregcharge').value="0";
				document.getElementById('opdotregcharge').readOnly =true;
			 }
			
		}
		
		//if payee is tp set paid amount to be 0
		if(data[2]=='true'){
		if(document.getElementById('paybypatient1').checked == true){
			document.getElementById('payAmount').value  = 0;
			document.getElementById('howpaid').value  = 'Cash';
			
		}
		}
	}
}


function opdPaymentAjax(){
	
	var url = "chargeNotAvailableSlot?apmtType="+apmtType+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = opdPaymentAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function opdPaymentAjaxRequest(){

	if (req.readyState == 4) {
		if (req.status == 200) {
		
		}
	}

}

function changeOPDRegcharge(val){
	var opdotcharge = parseFloat(document.getElementById('opdotcharge').value);
	var total = opdotcharge + parseFloat(val);
	document.getElementById('totalamount').value=total;
	setAmountDue();
}

function changeOPDcharge(val){
	var opdotregcharge = parseFloat(document.getElementById('opdotregcharge').value);
	var total = opdotregcharge + parseFloat(val);
	document.getElementById('totalamount').value=total;
	setAmountDue();
}
var isafterbookapmtcashdesk=0;
function createopdcashdesknew(){
	
	
	document.getElementById('disctype').value = 0;
	document.getElementById('discount').value = 0;
	
	var url = "getchargeofbookedopdBookAppointmentAjax?appmtid="+editAppointId+" ";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = createopdcashdesknewRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function createopdcashdesknewRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			isafterbookapmtcashdesk=1;
			
			var str=req.responseText;
	        var data=str.split("~");
			document.getElementById('totalamount').value = parseFloat(data[0])+parseFloat(data[1]);
			totalamt1 = parseFloat(data[0])+parseFloat(data[1]);
			document.getElementById('payAmount').value  = totalamt1;
			document.getElementById('opdotcharge').value=data[0];
			document.getElementById('opdotregcharge').value=data[1];
			
			if(data[3]=='markhosp' || data[3]=='marktest'){
				var newcharge=0;
			  if(data[6] !=0){
				if(parseFloat(data[5])<totalamt1){
					
					newcharge=totalamt1-parseFloat(data[5]);
					document.getElementById('checktext').innerHTML="<input type='checkbox' id='check' name='check'> "+data[5]+" has already been taken from "+data[4]+"; "+newcharge+"  more needs to be collected from Patient."
			     
				}else{
					
			        document.getElementById('checktext').innerHTML="<input type='checkbox' id='check' name='check'> "+totalamt1+" has already been received from "+data[4]+"; hence, no additional payment is required from the Patient.";
				}
		      }else{
				document.getElementById('checktext').innerHTML="<input type='checkbox' id='check' name='check' checked class='hidden'> ";
			  }
			}
			if(!(data[1]>0)){
				document.getElementById('opdotregcharge').readOnly =true;
			}
			
			var isot = document.getElementById('radio3').checked;
			if(isot==true){
				 totalamt1 = 0;
				 var potcharge = document.getElementById('potcharge').value;
				 var psurcharge = document.getElementById('psurcharge').value;
				 var panetcharge = document.getElementById('panetcharge').value;
				 var sic = document.getElementById('sic').value;
				 if(potcharge==""){
					 potcharge="0";
				 }
				 if(psurcharge==""){
					 psurcharge="0";
				 }
				 if(panetcharge==""){
					 panetcharge="0";
				 }
				 if(sic==""){
					 sic="0";
				 }
				 var assistaffcharge = 0;
				 if(document.getElementById("assistaffcharge")){
					 assistaffcharge = document.getElementById("assistaffcharge").value;
					 if(assistaffcharge==""){
						 assistaffcharge="0";
					 }
				 }
				 totalamt1 = parseFloat(potcharge) + parseFloat(psurcharge) + parseFloat(panetcharge) + parseFloat(sic) + parseFloat(assistaffcharge);
				 document.getElementById('totalamount').value = totalamt1;
				 document.getElementById('payAmount').value  = totalamt1;
				 
				document.getElementById('opdotcharge').value=totalamt1;
				document.getElementById('opdotregcharge').value="0";
				document.getElementById('opdotregcharge').readOnly =true;
			}
		}
		
		//if payee is tp set paid amount to be 0
		if(data[2]=='true'){
			if(document.getElementById('paybypatient1').checked == true){
				document.getElementById('payAmount').value  = 0;
				document.getElementById('howpaid').value  = 'Cash';
				
			}
		}
		$( "#completeAppointmentDivId" ).modal( "hide" );
		$( "#takepaymentmodel" ).modal( "show" );
		
	}
}


function saveopdcashdesknews(){
	
	var invcetype = document.getElementById('invcetype').value;
	var howpaid = document.getElementById('howpaid').value;
	var totalamount = document.getElementById('totalamount').value;
	var discount = document.getElementById('discount').value;
	var payAmount = document.getElementById('payAmount').value;
	var disctype = document.getElementById('disctype').value;
	var bnkname = document.getElementById('bnkname').value;
	
	var opdotcharge =  "0";
	var opdotregcharge ="0";
	if(document.getElementById('opdotcharge')){
		opdotcharge = document.getElementById('opdotcharge').value;
	}
	
	if(document.getElementById('opdotregcharge')){
		opdotregcharge = document.getElementById('opdotregcharge').value;
	}
	var url = "saveopdcashdesknewBookAppointmentAjax?selectedid="+editAppointId+"&invcetype="+invcetype+"&howpaid="+howpaid+"&totalamount="+totalamount+"&discount="+discount+"&payAmount="+payAmount+"&disctype="+disctype+"&bnkname="+bnkname+"&opdotcharge="+opdotcharge+"&opdotregcharge="+opdotregcharge+"";
 
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
             
	req.onreadystatechange = saveopdcashdesknewsRequest;
	req.open("GET", url, true); 
	req.send(null);

}

function saveopdcashdesknewsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			saveAppointmntid=editAppointId;
			//$( "#vieworprintnvoice" ).modal( "show" );
			
			showdisplaynewopd();
			//window.location.reload();
		}
	}
}


function paymentTransaction(clientid,val){
	alert(clientid);
	var apmttype=document.getElementById("apmtType").value;
	var url="paymenttransactionUserProfile?clientid="+clientid+"&apmttype="+apmttype+"";

	 if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	              
	    req.onreadystatechange = paymentTransactionRequest;
	    req.open("GET", url, true); 
	             
	    req.send(null);   
}

function paymentTransactionRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			  var data=req.responseText;
	          var data1=data.split("~~")
			 
			  	    
			        document.getElementById("amount1").value=data1[0];
					document.getElementById("order_id").value=data1[1];
			        document.getElementById('platinumplanForm').submit();

			                }
			  
	         }
		}	
	
		
		function makePayment(clientid) {
		  // Retrieve values from DOM
		  var apmttype = document.getElementById("apmtType").value;
		  if (!apmttype) {
		    console.error('Appointment type is missing!');
		    return; // Exit if apmttype is missing
		  }

		  if (!clientid) {
		    console.error('Client ID is missing!');
		    return; // Exit if clientid is missing
		  }

		  const url = "createOrderUserProfile"; // Replace with your backend API URL

		  // Constructing body using URLSearchParams for cleaner code
		  const body = new URLSearchParams();
		  body.append('clientid', clientid);
		  body.append('apmtType', apmttype);

		  fetch(url, {
		    method: 'POST',
		    headers: {
		      'Content-Type': 'application/x-www-form-urlencoded'
		    },
		    body: body.toString() // Convert the URLSearchParams to a query string
		  })
		  .then(response => response.json())
		  .then(order => {
		    if (!order.id || !order.amount || !order.currency) {
		      throw new Error('Invalid order details');
		    }

		    // Razorpay payment options
		    const options = {
		      key: 'rzp_live_kw4aux9EmjvJIZ',
		      amount: order.amount,
		      currency: order.currency,
		      name: 'PRIYANKA',
		      description: 'Payment for Appointment Booking',
		      order_id: order.id,
		      prefill: {
		        name: 'Priyanka Gadbail',
		        email: 'priyankagadbail90@gmail.com'
		      }
		    };

		    // Open Razorpay payment gateway
		    const rzp1 = new Razorpay(options);
		    rzp1.open();
		  })
		  .catch(error => {
		    console.error('Error:', error);
		  });
		}