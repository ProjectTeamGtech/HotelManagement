var invoiceid1 ="";
displayTooltip = false;
var messageDelay = 10000;
jQuery.fn.center = function () {
		
		this.css("position","absolute");
		
		
		return this;
	};
	

	
	
	
function goClose(){
	
	$("#background").fadeOut("slow");
	$("#previewDiv").hide();
	window.location.reload();
	
	
	
	
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
			
			
			
		}
	}

}




function deleteChargeAccountsTableAjax(){
	
	var url = "deleteAccounts";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deleteChargeAccountsTableAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function deleteChargeAccountsTableAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			resetAllSelect();
		}
	}
	
}


function closeDiv(){
	$("#background").fadeOut("slow");
	$("#previewDiv").hide();
	document.getElementById('allPateintPopUp').style.display = 'none';
}

function searchClient(){
	var searchText = document.getElementById("searchText").value;
	//alert(searchText);
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

function showTransactionPopup(payby,invoiceid,debitAmount,payAmount,clientid,client){
	
	var url = "transactionsAccounts?payby="+payby+"&invoiceid="+invoiceid+"&debitAmount="+debitAmount+"&creditAmount="+payAmount+"&clientid="+clientid+"&client="+client+"";

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
		
			document.getElementById("transactionList").innerHTML = req.responseText;
			//$('#transactionPopup').dialog( "open" );	
			
         }
	}

}	

function showDetailsDiv(hiddenDetailsDiv,invoiceid,payby){
	

	
	invoiceid1 = invoiceid;
	if(document.getElementById(hiddenDetailsDiv).style.display == ""){
		document.getElementById(hiddenDetailsDiv).style.display = "none";
	}
	else{
	document.getElementById(hiddenDetailsDiv).style.display = ""; 
	
	var url = "showAllAssessmentAccounts?invoiceid="+invoiceid+"&payby="+payby+"";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showDetailsDivRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function showDetailsDivRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			
			document.getElementById("hiddenDetailsDiv1"+invoiceid1+"").innerHTML = req.responseText;
			
			document.getElementById("hiddenDetailsDiv"+invoiceid1+"").style.display = ""; 
			document.getElementById("hiddenDetailsDiv1"+invoiceid1+"").style.display = ""; 
			 
			
         }
	}

}	


function setTempChargeInvoiceAjax(selectedid){
	//alert(selectedid);
	var chk = document.getElementById(selectedid).checked;
	var str = selectedid.split('-');
	
	var url = "tempCharges?invoiceid="+str[1]+"&chk="+chk+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setTempChargeInvoiceAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setTempChargeInvoiceAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
		}
	}
}


function setAmountDue(){
	var totalAmount = document.getElementById('hdndiscdebit').value;
	var disctype = document.getElementById('disctype').value;
	var discount = document.getElementById('discount').value;
	var discountperbyval=Number(discount);
	var discountAmt=0;
	if(disctype==0){
		if(discountperbyval>100){
		
		 jAlert('error', 'Discount exceed above 100%.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			document.getElementById('discount').value=0;
	}else{
		 discountAmt = totalAmount * (discount/100);
		
	}
	}else{
		
	if(disctype==1){
	 discountAmt = discount;
	}
}
	var amountDue = totalAmount - discountAmt;
	
	
	
	
	document.getElementById('payAmount').value = amountDue;
	if(amountDue==0){
		document.getElementById('totalamount').value = parseFloat(amountDue);
	}else{
		document.getElementById('totalamount').value = amountDue;
	}
	
	
}


function saveInvoiceCashDesk(){
	var ledgerservicestr = '0,';
	//ledger services
	var advance=document.getElementById('adv').value;
		var debit=document.getElementById('debt').value;
	  $('.lservicechidcase').each(function() { //loop through each checkbox
		  ledgerservicestr = ledgerservicestr +  this.checked + ','  //select all checkboxes with class "checkbox1"               
      });
	  document.getElementById('ledgerservicestr').value = ledgerservicestr;
	var clinicid = document.getElementById('clinicid').value
	var t1 = document.getElementById('payAmount').value;
	var totalamt1 = document.getElementById('totalamount').value;
	
	 if(document.getElementById('howpaid').value==0){
		
		jAlert('error', 'Please select payment mode.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else if( document.getElementById('payAmount').value<0){
		jAlert('error', 'Payment Received Amont is not less than 0 ', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	
	}else if( document.getElementById('howpaid').value=='prepayment' && document.getElementById('hiddenbalence').value=='false'){
		jAlert('error', 'No credit balence. Please select other payment mode ', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	
	}else if(parseFloat(t1) > parseFloat(totalamt1)){
		//jAlert('error', 'Paid amt is greater than balance Amount.', 'Error Dialog');
		/*document.getElementById('crdpaidamt').innerHTML = currencySign+t1+"";
		document.getElementById('crdbalamt').innerHTML = currencySign+totalamt1+"";
		var remain = t1 - totalamt1;
		
		document.getElementById('crdremainamt').innerHTML = currencySign + remain;
		$( '#creditnotepopup' ).modal( "show" );*/
		jAlert('error', 'Paid amt is greater than balance Amount. ', 'Error Dialog')
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else if(document.getElementById('howpaid').value=='prepayment'){
		var crdpaymnt = document.getElementById('prepaymntamntid').value;
		var morepaymt = document.getElementById('hdnmorepaudamount').value;
		if(crdpaymnt!=morepaymt){
			var totalcmpaymnt=parseFloat(t1);
		}else{
		    var totalcmpaymnt = parseFloat(crdpaymnt) + parseFloat(morepaymt);
		}
		if(parseFloat(t1)>parseFloat(totalcmpaymnt)){
			jAlert('error', 'Credit balence can not be less than payamount. ', 'Error Dialog')
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			
			var creditnote = document.getElementById('creditnote').value;
			document.getElementById('creditNotes').value = creditnote;
			
			$( "#baselayout1loaderPopup" ).modal( "show" );
			
			
			var r=confirm("Are you sure you want to clear advance payment.");
					if (r==true)
					  {
					  //alert('y')
					  document.getElementById('clraradv').value='1';
					 /* finalconfirmsubmitPopupNew()*/
					  
					  
					  $( "#baselayout1loaderPopup" ).modal( "hide" );
						 /* finalconfirmsubmitPopup();*/
						 
						/* previous code of record payment advance 09/08/24 */
				   	if(clinicid=='balgopal' || clinicid=='testbg'){
						
					    if(t1==crdpaymnt){
							finalconfirmsubmitPopupNew();	
						}else{
						  if(document.getElementById('refmodeppup')){
							  $( "#refmodeppup" ).modal( "show" );
						  }else{
							  finalconfirmsubmitPopupNew();
						  }
						 }
						}else{
						  //new code of record payment advance
						 if(parseFloat(advance)>parseFloat(debit)){
						  				      if(document.getElementById('refmodeppup')){
						  					  $( "#refmodeppup" ).modal( "show" );
						  				      }
						  				        else{
						  				    		finalconfirmsubmitPopup();
						  				   		}
						  				  }else{
						  					  finalconfirmsubmitPopup();
						  				  }
						 }
					  
					 // document.getElementById('paymentProcessingfrm').submit();
					//  return true;
					  }
					else
					  {
					 //  alert('n')
					 document.getElementById('clraradv').value='0';
					 finalconfirmsubmitPopupNew()
					// document.getElementById('paymentProcessingfrm').submit();
					 /* return false;*/
					  }
		}
	}
	
	
	else{
		
		var creditnote = document.getElementById('creditnote').value;
		document.getElementById('creditNotes').value = creditnote;
		
		$( "#baselayout1loaderPopup" ).modal( "show" );
		finalconfirmsubmitPopupNew();
		//document.getElementById('paymentProcessingfrm').submit();
	}
}

function finalconfirmsubmitPopupNew() {
	$( "#baselayout1loaderPopup" ).modal( "hide" );
	var payamount = document.getElementById("payAmount").value;
	var howpaid =document.getElementById("howpaid").value;
	document.getElementById("finalconfirmPaymode").innerHTML = howpaid;
	document.getElementById("finalconfirmAmount").innerHTML = payamount;
	$('#finalconfirmpopup').modal( "show" );	
}
function submitFinalConfirmNew() {
	
	$( "#baselayout1loaderPopup" ).modal( "show" );
	document.getElementById('paymentProcessingfrm').submit();
}


function saveCashDesk(){
	
	var ledgerservicestr = '0,';
	//ledger services
	  $('.lservicechidcase').each(function() { //loop through each checkbox
		  ledgerservicestr = ledgerservicestr +  this.checked + ','  //select all checkboxes with class "checkbox1"               
      });
	  document.getElementById('ledgerservicestr').value = ledgerservicestr;
	  
totalsubjectid = 0;
			     $('.chcaseinvst').each(function() { //loop through each checkbox
			        // this.checked = true;  //select all checkboxes with class "checkbox1" 
			        if(this.checked==true){
			        	totalsubjectid = totalsubjectid + ',' + this.value;
			        }
			         
			     });

document.getElementById('totalassesment').value = totalsubjectid;
var howpaid11=document.getElementById('howpaid').value;
	var chk=$("#assementtable :checked").size();
	var t1 = document.getElementById('payAmount').value;
	var totalamt1 = document.getElementById('hdntotal').value;
	var payAmount=document.getElementById('payAmount').value;
	var doctorid = document.getElementById('doctorid').value;
	var lmhac=document.getElementById('lmhac').value;
	var advance=document.getElementById('adv').value;
	var debit=document.getElementById('debt').value;
	var clinicid = document.getElementById('clinicid').value
	if(doctorid=='0' && lmhac!='true'){
		jAlert('error', 'Please select doctor', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(chk==0){
		jAlert('error', 'Please select atleast one charge', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
		
	}else if(document.getElementById('howpaid').value==0){
		
		jAlert('error', 'Please select payment mode.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}
else if((howpaid11=='D/Card'||howpaid11=='Cheque'||howpaid11=='NEFT')&& document.getElementById('paymentNote').value==''){
		
		jAlert('error', 'Please Add Payment Credentials(Payment Note)!', 'Error Dialog');
		
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
		document.getElementById('crdpaidamt').innerHTML = currencySign+t1;
		document.getElementById('crdbalamt').innerHTML = currencySign+totalamt1;
		var remain = t1 - totalamt1;
		
		document.getElementById('crdremainamt').innerHTML = currencySign + remain;
		$( '#creditnotepopup' ).modal( "show" );
		
	}else if(document.getElementById('howpaid').value=='prepayment'){
		var crdpaymnt = document.getElementById('prepaymntamntid').value;
		var morepaymt = document.getElementById('hdnmorepaudamount').value;
		var totalcmpaymnt = parseFloat(crdpaymnt) + parseFloat(morepaymt);
		if(parseFloat(t1)>parseFloat(totalcmpaymnt)){
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
				var creditnote = document.getElementById('creditnote').value;
		
				document.getElementById('creditNotes').value = creditnote;
				
				$( "#baselayout1loaderPopup" ).modal( "show" );
				if(document.getElementById('howpaid').value=='prepayment'){
					var r=confirm("Are you sure you want to clear advance payment.");
					if (r==true)
					 {
					  //alert('y')
					  document.getElementById('clraradv').value='1';
					  $( "#baselayout1loaderPopup" ).modal( "hide" );
					 /* finalconfirmsubmitPopup();*/
					 
					if(clinicid=='balgopal' || clinicid=='testbg'){
						
						if(t1==crdpaymnt){
								finalconfirmsubmitPopupNew();	
					   }else{
						 if(document.getElementById('refmodeppup')){
							 $( "#refmodeppup" ).modal( "show" );
						}else{
							 finalconfirmsubmitPopupNew();
						 }
	                  }
						
					}else{
					 
					  if(parseFloat(advance)>parseFloat(debit)){
					      if(document.getElementById('refmodeppup')){
						  $( "#refmodeppup" ).modal( "show" );
					      }
					        else{
					    		finalconfirmsubmitPopup();
					   		}
					  }else{
						  finalconfirmsubmitPopup();
					  }
					}
					 
					 // document.getElementById('cashdeskfrm').submit();
					  /*return true;*/
					  }
					else
					  {
					 //  alert('n')
					 document.getElementById('clraradv').value='0';
					 finalconfirmsubmitPopup();
					 //document.getElementById('cashdeskfrm').submit();
					 /* return false;*/
					  }
									
				
				}else{
					finalconfirmsubmitPopup();
					//document.getElementById('cashdeskfrm').submit();
				}
				
			}
			
		}
	}else if(Number(payAmount)<0){
		jAlert('error', 'Payment Received Not Less Than 0', 'Error Dialog');
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
				if(parseFloat(payAmount)==0){
					var new_t1 = document.getElementById('totalamount').value;
					if(parseFloat(new_t1)==0){
						document.getElementById('totalamount').value = "0.0";
					}
					var new_totalamt1 = document.getElementById('hdndebittotal').value;
					if(parseFloat(new_totalamt1)==0){
						document.getElementById('hdndebittotal').value=  "0.0";
					}
					
					
				}
				
				
				var creditnote = document.getElementById('creditnote').value;
		
				document.getElementById('creditNotes').value = creditnote;
				
				$( "#baselayout1loaderPopup" ).modal( "show" );
				finalconfirmsubmitPopup();
				//document.getElementById('cashdeskfrm').submit();
			}
	}
}



function goforrefundprepay(type){
	var refpaymode = document.getElementById("refhowpaid").value;
	document.getElementById("refpayMode").value=refpaymode;
	 $( "#refmodeppup" ).modal( "hide" );
	if(type=='1'){
		finalconfirmsubmitPopupNew();
	}else{
		finalconfirmsubmitPopup();
	}
	
}

function finalconfirmsubmitPopup() {
	$( "#baselayout1loaderPopup" ).modal( "hide" );
	var payamount = document.getElementById("payAmount").value;
	var howpaid =document.getElementById("howpaid").value;
	var invcetype = document.getElementById("invcetype").value;
	var invtype = $("#invcetype option:selected").text();
	 document.getElementById("finalconfirmPaymode").innerHTML = howpaid;
	 document.getElementById("finalconfirmInvtype").innerHTML = invtype;
	 document.getElementById("finalconfirmAmount").innerHTML = payamount;
	 $('#finalconfirmpopup').modal( "show" );	
}
function submitFinalConfirm() {
	document.getElementById("cnfrmid").disabled = true;
	$( "#baselayout1loaderPopup" ).modal( "show" );
	document.getElementById('cashdeskfrm').submit();
}



function setTotalCashDeskCharges(charges,selectedid){
	var total = document.getElementById('hdntotal').value;
	var chk = document.getElementById(selectedid).checked;
	
	if(chk == true ){
		total = parseInt(total)+ parseInt(charges);
	}
	if(chk == false ){
		total = parseInt(total)- parseInt(charges);
	}
	 document.getElementById('hdntotal').value = total;
	document.getElementById('totaltdid').innerHTML = currencySign + total;
	document.getElementById('balancetotalid').innerHTML = currencySign + total;
	document.getElementById('payAmount').value = total;
	document.getElementById('totalamount').value = total;
	document.getElementById('hdndebittotal').value = total;
	
}

function raisesubmitinvoiceChargesChecked(){
	//var chk=$("#chargestbl1 :checked").size();
	var ids ="";
	$(".selectClass").each(
		function() {
			if(this.checked == true){
				if(ids==''){
					ids= this.value;
				}else{
					ids = ids+","+this.value;
				}
			}
		}
	);
	if(document.getElementById('payby').value == 'All'){
		jAlert('error', 'Please select Payed By', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(document.getElementById('location').value == 'All'){
		jAlert('error', 'Please select Location', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}/*else if(chk==0){*/
	else if(ids==''){
		jAlert('error', 'Please select atleast one charge', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		 $('#baselayout1loaderPopup').modal( "show" );
		 document.getElementById('selectedCharges1').value = ids;
		 document.getElementById('raisesubmitinvoiceCharges').submit();
	}
}

function casdeskChargesChecked(){
	//var chk=$("#chargestbl1 :checked").size();
	var ids ="";
	$(".selectClass").each(
		function(){
			if(this.checked==true){
				if(ids==''){
					ids = this.value;
				}else{
					ids = ids+","+this.value;
				}
			}
		}
	);
	
	/*var assmtid="";
	$(".selectClass1").each(
			function(){
				if(this.checked==true){
					if(assmtid==''){
						assmtid = this.value;
					}else{
						assmtid = assmtid+","+this.value;
					}
				}
			}
		);*/
	if(document.getElementById('payby').value == 'All'){
		jAlert('error', 'Please select Payed By', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(document.getElementById('location').value == 'All'){
		jAlert('error', 'Please select Location', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}/*else if(chk==0){*/
	else if(ids==''){
		jAlert('error', 'Please select atleast one charge', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	}else{
		$( "#baselayout1loaderPopup" ).modal( "show" );
		document.getElementById('selectedCharges').value= ids;
		//document.getElementById('selectedinvcharge').value= assmtid;
		document.getElementById('casdeskCharges').submit();
	}
}

function setData(){
	alert("hi");
}

function saveCreditAmt(){
//	alert(document.getElementById('payby').value)
	var paymode = document.getElementById('howpaid').value;
	var clientId = document.getElementById('clientId').value;
	var type = "Pre_Payment";
	var payBuy = document.getElementById('payby').value;
	var client = document.getElementById('client').value;
	var creditnote = document.getElementById('creditnote').value;
	
	var t1 = document.getElementById('payAmount').value;
	var totalamt1 = document.getElementById('totalamount').value;
	var remain = t1 - totalamt1;
	
	document.getElementById('crdAmount').value = remain;
	document.getElementById('payAmount').value = document.getElementById('totalamount').value;
	
	
	//var url = "creditsaveAdditional?paymode="+paymode+"&clientId="+clientId+"&type="+type+"&payBuy="+payBuy+"&charge="+remain+"&="+client+"&creditnote="+creditnote+" ";
	
  /*  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveCreditAmtRequies;
    req.open("GET", url, true); 
              
    req.send(null);*/
	
}

/*function saveCreditAmtRequies(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//document.getElementById('payAmount').value = document.getElementById('hdntotal').value;
		}
	}
	
}
*/

function goAccountSubmit(){
	$("#dashboardloaderPopup").modal('show');
	document.getElementById('category_form').submit();
}

function addfrmtdate(val) {
  
    var payby=document.getElementById("payby").value; 
    var fmdate=document.getElementById("tfromdate");
    var tmdate=document.getElementById("ttodate");
    
    if(payby=='Third Party'){
    
            if(val==3){
            
                 fmdate.style="padding-top: 16px;"; 
           		 tmdate.style="padding-top: 16px;";
            } else {
            
             	fmdate.style="padding-top: 16px; display: none;"; 
                tmdate.style="padding-top: 16px; display: none;";
            }
            
            
    } else {
           fmdate.style="padding-top: 16px; display: none;"; 
           tmdate.style="padding-top: 16px; display: none;";   
         
    }
 
}



function setAutocharge(){
	
	   var clientid= document.getElementById("clientId").value;
	   var flag= document.getElementById("autocharge").checked;
	   if(flag==true){
		   flag="1";
	   } else {
		   flag="0";
	   }
	   var url="switchautochargeStatement?clientid="+clientid+"&flag="+flag+"";
	   
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setAutochargeRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}
function setAutochargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
	
}

function getchno(val,debit,balance){
	if(balance<debit){
		if(document.getElementById('howpaid').value=='prepayment'){
				document.getElementById('morepaymntdivid').style.display = '';
			}else{
				document.getElementById('morepaymntdivid').style.display = 'none';
			}
	}
	/*if(document.getElementById('howpaid').value=='prepayment'){
		document.getElementById('morepaymntdivid').style.display = '';
	}else{
		document.getElementById('morepaymntdivid').style.display = 'none';
	}*/
	
}

function showmorepaymentpopup(debit,balance){
	var discount=document.getElementById('discount').value;
	var morepayment=parseInt(debit)-parseInt(balance)-parseInt(discount);
	$('#morepaymentmodelpopupid').modal('show');
	document.getElementById("morepayAmount").value=morepayment;
}

function setmorepaymenttoform(){
	/*<s:hidden name="hdnmorehowpaid" id="hdnmorehowpaid"/>
		<s:hidden name="hdnmorepaudamount" id="hdnmorepaudamount"/>" +
				"
*/	
	
	
     
     if(document.getElementById("morehowpaid").value==0){
    	 jAlert('error', 'Please select payment mode.', 'Error Dialog');
 		
 		setTimeout(function() {
 			$("#popup_container").remove();
 			removeAlertCss();
 		}, alertmsgduration);
     }
     else if(document.getElementById("morepayAmount").value==0){
    	 jAlert('error', 'Please enter amount.', 'Error Dialog');
  		
  		setTimeout(function() {
  			$("#popup_container").remove();
  			removeAlertCss();
  		}, alertmsgduration);
     }else{
    	 document.getElementById("hdnmorehowpaid").value=document.getElementById("morehowpaid").value;
         document.getElementById("hdnmorepaudamount").value=document.getElementById("morepayAmount").value;
         document.getElementById("hdnbnkname").value=document.getElementById("morepaybank").value;
         
         
    	 $('#morepaymentmodelpopupid').modal('hide');
     }
     
	
}
	  
function openRequestedRefundList() {
	var clientId = document.getElementById("clientId").value;
	openBlankPopup('openclientrequestedrefundAdditional?clientId='+clientId+'');
}


function calcledgerrecamt(charges,selectedid){
	var total = document.getElementById('hdntotal').value;
	var chk = document.getElementById(selectedid).checked;
	
	if(chk == true ){
		total = parseInt(total)+ parseInt(charges);
	}
	if(chk == false ){
		total = parseInt(total)- parseInt(charges);
	}
	document.getElementById('hdntotal').value=total;
	 document.getElementById('payAmount').value = total;
	 
	
}

 function changepaymodeinadv(id,paymode){
	 document.getElementById('recpid').value = id;
	 document.getElementById('howpaid').value = paymode;
	 $('#changepymode').modal( "show" );
	 
 } 
 
 function changetypeadvref(){
	 
	 var id1=document.getElementById('recpid').value;
	 var paymode=document.getElementById('howpaid').value;
	 var url="changepaymodeadvAdditional?id="+id1+"&paymode="+paymode+"";
	   
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = changetypeadvrefRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	 
 }
 function changetypeadvrefRequest(){
	 if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			}
		}
 }
 
 function checkdisclimit(){
	 var totalAmount = document.getElementById('hdndiscdebit').value;
		var disctype = document.getElementById('disctype').value;
		var discount = document.getElementById('discount').value;
		var discountperbyval=Number(discount);
		var discountAmt=0;
		if(disctype==0){
			if(discountperbyval>100){
			
			 jAlert('error', 'Discount exceed above 100%.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				document.getElementById('discount').value=0;
		}else{
			 discountAmt = totalAmount * (discount/100);
			document.getElementById('discount').value=0;
			
		}
		}else{
			
		if(disctype==1){
		 discountAmt = discount;
			document.getElementById('discount').value=0;
		}
	}
		var amountDue = totalAmount - discountAmt;
		
		document.getElementById('payAmount').value = totalAmount;
		document.getElementById('totalamount').value = totalAmount;
 }
 
 function selectAllCheckBox(val){
	 if(val==true){
		 $(".selectClass").each(
			function() {
				this.checked = true;	
			}
		);
	 }else{
		 $(".selectClass").each(
			function(){
				this.checked = false;
			}
		 );
	 }
 }
 
 function changecreditinadv(id,credit,clientId){
	 document.getElementById('recpid1').value = id;
	 document.getElementById('credit').value = credit;
	 document.getElementById('clientId').value = clientId;
	 $('#changecredit').modal( "show" );
	 
 } 



function changecreditadvref(){
	 
	 var id1=document.getElementById('recpid1').value;
	 var credit=document.getElementById('credit').value;
     var clientId=document.getElementById('clientId').value;
	 var url="changeCreditAdditional?id="+id1+"&credit="+credit+"&clientId="+clientId+"";
	   
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = changecreditadvrefRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	 
 }
 function changecreditadvrefRequest(){
	 if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			}
		}
 }

 
 function changedebitamt(val,invoiceid){
	
	 var url="changeDebitamtAdditional?debit="+val+"&invoiceid="+invoiceid+"";
	   
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = changedebitamtRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	 
	}
	function changedebitamtRequest(){
	 if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			}
		}
	}