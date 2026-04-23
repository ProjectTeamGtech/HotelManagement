var invoiceId1 = "";
var clientId1 ="";
function showPopUp(){
	
	var url = "showListClient";

	$('#clientSearchDiv').dialog( "open" );
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
				
	         	
				
	         }
		}
}


function setPatientName(name,id,type,typeName){
	document.getElementById("clientId").value = id;
	clientId1 = id;
	
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
			
			
			
		}
	}

}


function showInnerDiv(hiddenDetailsDiv,invoiceId){
	invoiceId1 = invoiceId;
	if(document.getElementById(hiddenDetailsDiv).style.display == ""){
		document.getElementById(hiddenDetailsDiv).style.display = "none";
	}
	else{
	document.getElementById(hiddenDetailsDiv).style.display = ""; 
	
	var url = "showAllAssessmentProcessingAccount?invoiceId="+invoiceId+"";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showInnerDivRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function showInnerDivRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("hiddenDetailsDiv1"+invoiceId1+"").style.display = ""; 
			document.getElementById("hiddenDetailsDiv1"+invoiceId1+"").innerHTML = req.responseText;
			
			 
			
         }
	}

}	

function showInnerChildDiv(divid,invoiceId,payby){
	invoiceId1 = invoiceId;
	if(document.getElementById(divid).style.display == ""){
		document.getElementById(divid).style.display = "none";
	}
	else{
	document.getElementById(divid).style.display = ""; 
	
	var url = "showChildAssessmentProcessingAccount?invoiceId="+invoiceId+"&payby="+payby+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showInnerChildDivRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function showInnerChildDivRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("divid1"+invoiceId1+"").style.display = ""; 
			document.getElementById("divid1"+invoiceId1+"").innerHTML = req.responseText;
			
         }
	}

}	
function showInnerSubDiv(hiddenDetailsDiv,invoiceId){
	invoiceId1 = invoiceId;
	if(document.getElementById(hiddenDetailsDiv).style.display == ""){
		document.getElementById(hiddenDetailsDiv).style.display = "none";
	}
	else{
	document.getElementById(hiddenDetailsDiv).style.display = ""; 
	
	var url = "showSubAssessmentProcessingAccount?invoiceId="+invoiceId+"";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showInnerSubDivRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function showInnerSubDivRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("hiddenDetailsDiv1"+invoiceId1+"").style.display = ""; 
			document.getElementById("hiddenDetailsDiv1"+invoiceId1+"").innerHTML = req.responseText;
			
         }
	}

}	
function showTransactionPopup(invoiceid,clientId,payby){
var url = "transactionsProcessingAccount?invoiceid="+invoiceid+"&clientId="+clientId+"&payby="+payby+" ";
if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showTransactionPopupRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function showTransactionPopupRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("transactionList1").innerHTML = req.responseText;
			
         }
	}

}

function showrefundtranspopup(id){
	
	var url = "rfundtransProcessingAccount?invoiceid="+id+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showrefundtranspopupRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function showrefundtranspopupRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			$('#refundtransactions').modal('show');
			document.getElementById("refunddatassss").innerHTML = req.responseText;
			
			
			
         }
	}
	
}

function totalCashDesk(id,clientId){
	//alert(clientId);
	
	if(id=="Cheque"){
	     document.getElementById("shcheck").className="";
	} else {
	  document.getElementById("shcheck").className="hidden";
	}
	
	if(id == "ccd"){
	var url = "getTotalAmountCashDesk?clientId="+clientId+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = totalCashDeskRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function totalCashDeskRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				var amount = req.responseText;
				document.getElementById('ccdamt').value = amount;
				
	         }
		}

	}

function givPayment(){
	var totalamt = document.getElementById('totalamt').value;
	var payAmount = document.getElementById('payAmount').value;
	var ccdamt = document.getElementById('ccdamt').value;
	
	if(parseFloat(payAmount) > parseFloat(totalamt)){
		jAlert('error', 'Paid amt is greater than balance Amount.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(document.getElementById('howpaid').value==0){
		jAlert('error', 'Please select payment mode.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(parseFloat(payAmount) > parseFloat(ccdamt) ){
		jAlert('error', 'Paid amt is greater than Total CashDisk Amount.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else{
		document.getElementById('paymentProcessingfrm').submit();
	}

}
function createReport(clientId,invoiceId,amount){
	var url = "createReportProcessingAccount?clientId="+clientId+"&invoiceId="+invoiceId+"&amount="+amount+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = createReportRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function createReportRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			
			
         }
	}

}


function checkAmount(){
	var totalamt = document.getElementById('totalamt').value;
	var payAmount = document.getElementById('payAmount').value;
	
	
	if(parseFloat(payAmount) > parseFloat(totalamt)){
		jAlert('error', 'Paid amt is greater than balance Amount.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	
}



function confirmedDelete(){

	var r=confirm("Are you sure you want to delete this Invoice");
	if (r==true)
	  {
	  return true;
	  }
	else
	  {
	  return false;
	  }

}


/*//modify discount code
var ginvd = 0;
function showdiscountpopup(invoiceId){
ginvd = invoiceId;
		$('#editdiscdrop').modal('show');
}*/

var isdirectdiscount=0;
function showadditionaldiscountpopup(invoiceId,payment,balance,amount,disctype,discount,discamt){
	isdirectdiscount=2;
	document.getElementById("directreqdiscinvoiceid").value = invoiceId;
	document.getElementById("additionaldiscbalance").value = balance;
	document.getElementById("additionaldiscpayment").value = payment;
	document.getElementById("directrequestdiscinvoiceamt").innerHTML = amount;
	document.getElementById("directrequestdiscinvoiceamtval").value = amount;
	ginvd = invoiceId;
	refundpaymentxx = payment;
	refundbalancexx = balance;
	
	if(disctype=='0'){
		document.getElementById("directrequestdisctype").value =0;
		document.getElementById("directrequestdiscval").value =discount;
	}else{
		document.getElementById("directrequestdisctype").value =1;
		document.getElementById("directrequestdiscval").value =discamt;
	}
	
	if(parseFloat(balance)<=0 && parseFloat(payment)>0){
		isneededrefund=1;
	}else{
		isneededrefund=0;
	}
	$('#directmodifyydiscpopup').modal('show');
}

function showdiscountpopupdirect(invoiceId,payment,balance,amount){
	isdirectdiscount=1;
	document.getElementById("directreqdiscinvoiceid").value = invoiceId;
	document.getElementById("directrequestdiscinvoiceamt").innerHTML = amount;
	document.getElementById("directrequestdiscinvoiceamtval").value = amount;
	/*document.getElementById("additionaldiscbalance").value = balance;
	document.getElementById("additionaldiscpayment").value = payment;*/
	ginvd = invoiceId;
	refundpaymentxx = payment;
	refundbalancexx = balance;
	if(parseFloat(balance)<=0 && parseFloat(payment)>0){
		isneededrefund=1;
	}else{
		isneededrefund=0;
	}
	$('#directmodifyydiscpopup').modal('show');
}

function validateDirectDiscountRefund() {
	var additionaldiscbalance = document.getElementById("additionaldiscbalance").value;
	var additionaldiscpayment = document.getElementById("additionaldiscpayment").value;
	
	var directdiscountgivenuserid= document.getElementById("directdiscountgivenuserid").value;
	var directrequestdiscval = document.getElementById("directrequestdiscval").value;
	var direct_discount_reason = document.getElementById("direct_discount_reason").value;
	var disctype = document.getElementById("directrequestdisctype").value;
	var invoiceamount = document.getElementById("directrequestdiscinvoiceamtval").value;
	if(directdiscountgivenuserid=='0'){
		jAlert('error', 'Please select user.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(directrequestdiscval=='' || directrequestdiscval=='0'){
		jAlert('error', 'Please enter disc. value.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(direct_discount_reason==''){
		jAlert('error', 'Please enter note.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var flag = true;
		var flag1 = true;
		if(disctype=='0'){
			if(parseFloat(directrequestdiscval)>100){
				flag=false;
			}
		}else{
			if(parseFloat(directrequestdiscval)>parseFloat(invoiceamount)){
				flag1=false;
			}
		}
		
		if(flag==false){
			jAlert('error', 'Entered amount is greater than 100%', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(flag1==false){
			jAlert('error', 'Entered amount is greater than invoice amount', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			refunddisctype= disctype;
	        refunddiscval = parseFloat(directrequestdiscval);
	        
			if(isneededrefund==0){
				applydirectdisc();
			}else{
				 $('#directmodifyydiscpopup').modal('hide');
				document.getElementById("directrequestrefundinvoiceamt").innerHTML = refundpaymentxx;
				var d =0;
				if(refunddisctype=='0'){
					d = (refundpaymentxx*refunddiscval)/100;
				}else{
					d = refunddiscval;
				}
				var amt_test = parseFloat(invoiceamount)-(parseFloat(additionaldiscpayment) + parseFloat(additionaldiscbalance));
				amt_test = amt_test-d;
				amt_test = Math.abs(amt_test);
				document.getElementById("directrefundamount").value = amt_test;
				 $('#directrequestrefundpopup').modal('show');
			}
		}
	}
	
}
function directchangedisctype() {
	document.getElementById("directrequestdiscval").value='';
}

function directrefundrequestafterdisc() {
	var refund_reason =document.getElementById("direct_refund_reason").value;
	if(refund_reason==''){
		jAlert('error', 'Please enter note.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		applydirectdisc();
	}
}

function applydirectdisc(){
	var disctype = document.getElementById('directrequestdisctype').value;
	var discval = document.getElementById('directrequestdiscval').value;
	var refundamount = document.getElementById("directrefundamount").value;
	var refund_reason =document.getElementById("direct_refund_reason").value;
	var direct_discount_reason = document.getElementById('direct_discount_reason').value;
	if(discval==''){
		jAlert('error', 'Please Enter Amount.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else{
		if(document.getElementById('refreqdisdiv')){
			document.getElementById('refreqdisdiv').innerHTML='';
		}
		var url = "editdiscProcessingAccount?invoiceId="+ginvd+"&discval="+discval+"&disctype="+disctype+"&refundamount="+refundamount+"&refund_reason="+refund_reason+"&isneededrefund="+isneededrefund+"&isdirectdiscount="+isdirectdiscount+"&direct_discount_reason="+direct_discount_reason+" ";
	
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
               
		req.onreadystatechange = applydirectdiscRequest;
		req.open("GET", url, true); 
		req.send(null);
    }
}

function applydirectdiscRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
		}
	}
}

//get approved discount data
var ginvd = 0;
var isneededrefund=0;
var refunddisctype=0;
var refunddiscval =0;
var refundpaymentxx =0;
var refundbalancexx =0;
function showdiscountpopup(invoiceId,payment,balance){
	ginvd = invoiceId;
	refundpaymentxx = payment;
	refundbalancexx = balance;
	if(parseFloat(balance)<=0 && parseFloat(payment)>0){
		isneededrefund=1;
	}else{
		isneededrefund=0;
	}

	var url = "getdiscountdataProcessingAccount?invoiceId="+ginvd+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showdiscountpopupRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function showdiscountpopupRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str=req.responseText;
	         var data=str.split("~");
	         document.getElementById("disctype").value=data[0];
	         document.getElementById("discval").value=data[1];
	         //document.getElementById("discountuserid").value=data[2];
	         document.getElementById("discount_notes").value=data[3];
	         refunddisctype= data[0];
	         refunddiscval = data[1];
	         document.getElementById("afterdiscountinvoiceamount1").value=data[4];
			 $('#editdiscdrop').modal('show');
		}
	}
}

function applyaproveddisccount() {
	if(isneededrefund==0){
		editdisctype();
	}else{
		 $('#editdiscdrop').modal('hide');
		document.getElementById("requestrefundinvoiceamt").innerHTML = refundpaymentxx;
		var d =0;
		if(refunddisctype=='0'){
			d = (refundpaymentxx*refunddiscval)/100;
		}else{
			d = refunddiscval;
		}
		document.getElementById("refundamount").value = d;
		 $('#requestrefundpopup').modal('show');
	}
}

function refundrequestafterdisc() {
	var refund_reason =document.getElementById("refund_reason").value;
	if(refund_reason==''){
		jAlert('error', 'Please enter note.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		editdisctype();
	}
}

function editdisctype(){
	var disctype = document.getElementById('disctype').value;
	var discval = document.getElementById('discval').value;
	var refundamount = document.getElementById("refundamount").value;
	var refund_reason =document.getElementById("refund_reason").value;
	var isdirectdiscount=0;
	if(discval==''){
	
	jAlert('error', 'Please Enter Amount.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else{
	if(document.getElementById('refreqdisdiv')){
		document.getElementById('refreqdisdiv').innerHTML='';
	}
	var url = "editdiscProcessingAccount?invoiceId="+ginvd+"&discval="+discval+"&disctype="+disctype+"&refundamount="+refundamount+"&refund_reason="+refund_reason+"&isneededrefund="+isneededrefund+"&isdirectdiscount="+isdirectdiscount+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = editdisctypeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
    }
}

function editdisctypeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
		}
	}
}


function cancelInvoice(clientid,invoiceid){

   
      document.getElementById("rclientId").value=clientid;
      document.getElementById("rinvoiceid").value=invoiceid;
      $('#resetInvoice').modal('show');
      
      
}

//  16 feb 2018 approve disc code

function approveinvoicediscout(ginvd){
	var url = "approveinvoicediscoutProcessingAccount?invoiceId="+ginvd+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = approveinvoicediscoutRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function approveinvoicediscoutRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
		}
	}
}


//discount request code
function showrequestdiscountpopup(invoiceId,amount,practid){
	document.getElementById("reqdiscinvoiceid").value = invoiceId;
	document.getElementById("requestdiscinvoiceamt").innerHTML = amount;
	
	document.getElementById("discountgivenuserid").className="";
   	document.getElementById("discountgivenuserid").value=practid;
	document.getElementById("discountgivenuserid").className="form-control chosen-select";
	$("#discountgivenuserid").trigger("chosen:updated");
  	$(".chosen-select").chosen({allow_single_deselect: true});
	
  	$('#requestdiscpopup').modal('show');
}

//  22 feb 2018 request disc code
function requestfordiscount(){
	 $('#dashboardloaderPopup').modal('show');
	var disctype = document.getElementById('requestdisctype').value;
	var discval = document.getElementById('requestdiscval').value;
	var invoiceId =document.getElementById("reqdiscinvoiceid").value;
	var invoiceamount = document.getElementById("requestdiscinvoiceamt").innerHTML;
	var discountgivenuserid = document.getElementById('discountgivenuserid').value;
	var discount_reason = document.getElementById('discount_reason').value;
	var flag = true;
	var flag1= true;
	if(disctype=='0'){
		if(parseFloat(discval)>100){
			flag=false;
		}
	}else{
		if(parseFloat(discval)>parseFloat(invoiceamount)){
			flag1=false;
		}
	}
	if(discountgivenuserid=='0'){
		jAlert('error', 'Please select practitioner name', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		 $('#dashboardloaderPopup').modal('hide');
	}else if(discount_reason==''){
		jAlert('error', 'Please enter discount note', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		 $('#dashboardloaderPopup').modal('hide');
	}else if(discval==''){
	jAlert('error', 'Please enter amount.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		 $('#dashboardloaderPopup').modal('hide');
	}else if(flag==false){
		jAlert('error', 'Entered amount is greater than 100%', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		 $('#dashboardloaderPopup').modal('hide');
	}else if(flag1==false){
		jAlert('error', 'Entered amount is greater than invoice amount', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		 $('#dashboardloaderPopup').modal('hide');
	}else{
		if(document.getElementById('reqrefbtndiv')){
			document.getElementById('reqrefbtndiv').innerHTML ='';
		}
	
	var url = "requestfordiscountProcessingAccount?invoiceId="+invoiceId+"&disctype="+disctype+"&discval="+discval+"&invoiceamount="+invoiceamount+"&discountgivenuserid="+discountgivenuserid+"&discount_reason="+discount_reason+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = requestfordiscountRequest;
    req.open("GET", url, true); 
    req.send(null);
	 }
}

function requestfordiscountRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 $('#dashboardloaderPopup').modal('show');
			window.location.reload();
		}
	}
}	  	
// Adarsh for Approve Notes
function changedisctype() {
	if(document.getElementById("afterdiscountinvoiceamount")){
		document.getElementById("afterdiscountinvoiceamount").value=0;
	}
	document.getElementById("requestdiscval").value='';
}

function approvedDiscount(val,invoiceId,isgroupdiscount){
	 document.getElementById("isgroupdiscount").value="0";
	 document.getElementById("approveddiscount_id").value = val;
	 document.getElementById("approveinvoiceid").value = invoiceId;
	 $('#approvedmodel').modal( "show" );
}
function selectallapprovedisc(val) {
	
	if (val.checked == true) {
		$('.aprovealldiscclass').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class// "checkbox1"
		});
	} else {
		$('.aprovealldiscclass').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class// "checkbox1"
		});
	}
}
function approvedDiscountAll(){
	document.getElementById("isgroupdiscount").value="1";
	var flagcheck= false;
	 $('.aprovealldiscclass').each(function() {
			if (this.checked == true) {
				flagcheck= true;
			}
	});
	 if(flagcheck){
		 $('#approvedmodel').modal( "show" );
	 }else{
		 jAlert('error', 'Please select at least one checkbox for all approve!!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	 }
	
}
	function approvedDiscountAdmin(){
	  var id = document.getElementById("approveddiscount_id").value;
	  var approve_notes = document.getElementById("approve_notes").value;
	  var approveinvoiceid = document.getElementById("approveinvoiceid").value;
	  var link = "aprovedinvoicediscountProcessingAccount?id="+id+"&approve_notes="+approve_notes+"&approveinvoiceid="+approveinvoiceid+"";
	    if (window.XMLHttpRequest) {
	     req = new XMLHttpRequest();
	    }
	    else if (window.ActiveXObject) {
	     isIE = true;
	     req = new ActiveXObject("Microsoft.XMLHTTP");
	    }   
	    req.onreadystatechange = approvedDiscountAdminRequest;
	    req.open("GET", link, true); 
	    req.send(null);  
	  }
	 function approvedDiscountAdminRequest(){
	  if (req.readyState == 4) {
	    if (req.status == 200) {
	     window.location.reload();  
	           }
	   }  
	} 
	 var allaprvenoerroe=false;
	 function approvedDiscountinvoice() {
		 var approve_notes = document.getElementById("approve_notes").value;
		 if(approve_notes==''){
			 jAlert('error', 'Please enter aprrove note!!!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		 }else{
			 allaprvenoerroe= false;
			 var isgroupdiscount =document.getElementById("isgroupdiscount").value;
			 if(isgroupdiscount==0){
				 var discountid =document.getElementById("approveddiscount_id").value;
				 var invoiceid = document.getElementById("approveinvoiceid").value;
				 $("#dashboardloaderPopup").modal('show');
				 checkDiscAlreadyAppliedOrReqCancel(discountid,invoiceid,0);
			 }else{
				 $("#dashboardloaderPopup").modal('show');
				 	var allaprovediscountids="0";
					 $('.aprovealldiscclass').each(function() {
							if (this.checked == true) {
								var i = this.value;
								allaprovediscountids = allaprovediscountids+","+i;
								var discountid =document.getElementById("discreqidmain"+ i).value;
								var invoiceid = document.getElementById("discreqinvoiceidmain"+ i).value;
								checkDiscAlreadyAppliedOrReqCancel(discountid,invoiceid,0);
							}
					});
					 if(!allaprvenoerroe){
						 document.getElementById("approveddiscount_id").value = allaprovediscountids;
						 document.getElementById("invoiceapproveddiscountid").submit();;
					 }
			 }
			
			// document.getElementById("invoiceapproveddiscountid").submit();
		 }
	 
	 }
	 
	 function deleteDiscRequestHIS() {
		 var reason = document.getElementById("delinddel").value;
		 var discountid = document.getElementById("deleteindid").value;
		 var invoiceid = document.getElementById("deleteInvoiceidnew").value;
		 if(reason==''){
			 jAlert('error', 'Please enter delete note!!!', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		 }else{
			 $("#dashboardloaderPopup").modal('show');
			 checkDiscAlreadyAppliedOrReqCancel(discountid,invoiceid,1);
		 }
	 
	 }
	 
	 	function checkDiscAlreadyAppliedOrReqCancel(discountid,invoiceid,isaprove){
		 		var link = "checkdisccancelorappliedBookAppointmentAjax?discountid="+discountid+"&invoiceid="+invoiceid+"&isaproveordisc="+isaprove+"";
			    if (window.XMLHttpRequest) {
			     req = new XMLHttpRequest();
			    }
			    else if (window.ActiveXObject) {
			     isIE = true;
			     req = new ActiveXObject("Microsoft.XMLHTTP");
			    }   
			    req.onreadystatechange = approvedDiscountAdminRequest;
			    req.open("GET", link, true); 
			    req.send(null);  
		 }
		 function approvedDiscountAdminRequest(){
			  if (req.readyState == 4) {
					  if (req.status == 200) {
						  var data=req.responseText;
						  var str=data.split("~~");
						  if(str[0]=='1'){
							  allaprvenoerroe = true;
							  $("#dashboardloaderPopup").modal('hide');
							  jAlert('error', 'Request alreday deleted', 'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
						  }else if(str[0]=='2'){
							  allaprvenoerroe = true;
							  $("#dashboardloaderPopup").modal('hide');
							  jAlert('error', 'Request alreday approved', 'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
						  }else{
							  if(str[1]==0){
									 var isgroupdiscount =document.getElementById("isgroupdiscount").value;
									 if(isgroupdiscount==0){
										 document.getElementById("invoiceapproveddiscountid").submit();
									 }
							  }else{
								  deleteinvdiscount();
							  }
							  
						  }
				      }
			   }  
		 } 

	 function validatenote() {
		var textnote=document.getElementById("notestxt").value;
		if(textnote==''){
			jAlert('error', 'Enter notes for invoice cancellation', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		}else{
			 $("#dashboardloaderPopup").modal('show');
			document.getElementById('resetForm').submit();
			return true;
		}
	
		
	 }
//	function modifyinvdisc(payby, numberOfChages1, creditCharge1, invoiceid1,
//		debitamt1, balance1, discount1, clientId) {
//		
//		document.getElementById("mod_payby").value = payby;
//		document.getElementById("mod_numberOfChages").value = numberOfChages1;
//		document.getElementById("mod_creditCharge").value = creditCharge1;
//		document.getElementById("mod_id").value = invoiceid1;
//		document.getElementById("mod_debitAmount").value = debitamt1;
//		document.getElementById("mod_balance").value = balance1;
//		document.getElementById("mod_discount").value = discount1;
//		document.getElementById("mod_clientId").value = clientId;
//		
//		
//	var r = confirm("Modifying Invoice will reset the discount to 0. Do you want to continue");
//	if (r == true) {
//		 discountzero(invoiceid1);
//		
//	}
//}
//	function discountzero(invoiceid){
//		
//var url = "discountzeroProcessingAccount?invoiceId="+invoiceid+" ";
//	
//	if (window.XMLHttpRequest) {
//		req = new XMLHttpRequest();
//	}
//	else if (window.ActiveXObject) {
//		isIE = true;
//		req = new ActiveXObject("Microsoft.XMLHTTP");
//	}   
//    req.onreadystatechange = approveinvoicediscoutRequest;
//    req.open("GET", url, true); 
//    req.send(null);
//}
//
//function approveinvoicediscoutRequest(){
//	if (req.readyState == 4) {
//		if (req.status == 200) {
//			document.getElementById('modifysform').submit();
//		}
//	}
//	
//
//}
	

		
		function recordpymnt(payby, numberOfChages1, creditCharge1, invoiceid1,
				debitamt1, balance1, discount1, clientId,balancex) {
				
				document.getElementById("pay_payby").value = payby;
				document.getElementById("pay_numberOfChages").value = numberOfChages1;
				document.getElementById("pay_creditCharge").value = creditCharge1;
				document.getElementById("pay_id").value = invoiceid1;
				document.getElementById("pay_debitAmount").value = debitamt1;
				document.getElementById("pay_balance").value = balance1;
				document.getElementById("pay_discount").value = discount1;
				document.getElementById("pay_clientId").value = clientId;
				document.getElementById("pay_balancex").value = balancex;
				var status=document.getElementById("disc_request").value;
				if(status==2){
					var r = confirm("Discount Already Approved. Do you want to apply approved discount");
					if (r == true) {
						showdiscountpopup(invoiceid1,creditCharge1,balancex);
					}else{
						document.getElementById('recordpymtform').submit();
					}
				}else{
					document.getElementById('recordpymtform').submit();
				}
				
				
		}
		
		function approvedIndDiscount(val){
			 
			 document.getElementById("approvedinvdiscount_id").value = val;
			 var url = "approveinvdiscoutStatement?parentid="+val+" ";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			    req.onreadystatechange = approveinvoicediscoutRequest;
			    req.open("GET", url, true); 
			    req.send(null);
			    $('#approveinvdmodel').modal( "show" );
			}

			function approveinvoicediscoutRequest(){
				if (req.readyState == 4) {
					if (req.status == 200) {
						 var str=req.responseText;
						 document.getElementById('indvdiv').innerHTML = str;
				        
					}
				}
			
			}
			
			
			function approvedInvDiscountinvoice() {
				 var approve_notes = document.getElementById("approve_notes1").value;
				 if(approve_notes==''){
					 jAlert('error', 'Please enter aprrove note!!!', 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				 }else{
					 $("#dashboardloaderPopup").modal('show');
					 document.getElementById("approveinddiscount").submit();
				 }
			 
			 }
			
		
			/* json fuction to store temp terms n cond */
function updateremark(invoiceeid,id) {
	var remark='';		
	 
						
					    remark = document.getElementById("remark"+id).value;
						remark = remark.replace("\t\t\t\t\t\t\t\t", "");
						
											

		
				
				
		
		var url = "updateProcessingAccount?remark="+remark+"&invoiceeid="+invoiceeid+"";


		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		   req.onreadystatechange = updateremarkRequest;
		   req.open("GET", url, true); 
		   req.send(null);		
				
				

	}
	
	function updateremarkRequest(){


	      if (req.readyState == 4) {
			 if (req.status == 200) {
				 var str = req.responseText;
				  $("#baselayout1loaderPopup").modal("hide");
				  if (str == '0') {
				      alert("data not added.");                   // error
				  } else {
					alert("updated Successfully.");
					window.location.reload();
	                     // success
				}
				  
			 }
		}
	          
	}	
			