var cookieUserName = "";
var cookieStarttime = "";
var cookieDuration = "";
var cookiePractitioner = "";
var cookieapmtType = "";
var cookiePractitionerId = "";
var cookieClientId = "";
var cookiecommencing = " ";
var cookieCharge= "";
var cookieSelectedAppointmentid = "";
var practitionerName = "";
var TreatmentEpiode = "";
var compApmtinvoiceID = 0;
var selfInvoiceTotalAmount = 0;
var newWhoPay = " ";

	/*window.onload = function(){
		cookieUserName=read_cookie("cookieUserName");
		cookieStarttime=read_cookie("cookieStarttime");
		cookieDuration=read_cookie("cookieDuration");
		cookiePractitioner=read_cookie("cookiePractitioner");
		cookieapmtType=read_cookie("cookieapmtType");
		cookiePractitionerId=read_cookie("cookiePractitionerId");
		cookieClientId=read_cookie("cookieClientId"); 
		cookiecommencing=read_cookie("cookiecommencing");
		cookieCharge = read_cookie("cookieCharge");
		cookieSelectedAppointmentid = read_cookie("cookieSelectedAppointmentid");
		
		//alert(cookieClientId);
		//document.getElementById('usernamediv').innerHTML = cookieUserName;
		
		var doller = "$";
		document.getElementById('durationdiv').innerHTML = ""+cookieUserName+","+cookieStarttime+" - "+cookieDuration+" min Appointment with "+cookiePractitioner+" is Complete";
		document.getElementById('chargediv').innerHTML = "The charge has been created To "+cookieUserName+" "+cookieapmtType+" "+cookieCharge+" "+doller+"  ";
		document.getElementById('chargetodiv').innerHTML = cookieUserName;
		
		document.getElementById('clientId').value = cookieClientId;
		document.getElementById('practitionerName').value = cookiePractitioner;
		document.getElementById('practitionerId').value = cookiePractitionerId;
		
		
		getTreatmentEpisodeDetailsAjax();
		
}*/


function createInvoice(action){
	var appointmentid = read_cookie("cookieSelectedAppointmentid");
	
	var url = "updateInvoiceCompleteApmt?invoiceid="+compApmtinvoiceID+"&action="+action+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = createInvoiceRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
}

function createInvoiceRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
             // jAlert('success',clientName+" "+"Invoice has been Created successfully!!", 'Success Dialog');
              tempAlert(""+clientName+" Invoice has been Created successfully.", 5000);		
		      //setCommonAction();
		      
		      
		      var button2 = $(".ui-dialog-buttonpane button:contains('Invoice Now / Pay Later')");
		      console.log(button2);
		      $(button2).button("disable");
		      
		      
		      
		      var button1 = $(".ui-dialog-buttonpane button:contains('Submit Invoice')");
		      console.log(button1);
		      $(button1).button("enable");
		     
		      selfInvoiceTotal();
		     
			
		}
	}
	
}


function modifyCreateChargeUpdateAccount(action){
	/*alert('diaryUser1');
	
	var clientid = read_cookie("cookieClientId"); 
	var practitionerid = read_cookie("cookiePractitionerId");
	var clientName = read_cookie("cookieUserName");
	var practitionerName = read_cookie("cookiePractitioner");
	var appointmentid = read_cookie("cookieSelectedAppointmentid");
	var tratmentepisodeid = read_cookie("cookieTreatmentEpisode");
	var treatmenntsessions = read_cookie("cookieTreatmentEpidodeSessions");*/
	var practitionerName = read_cookie("cookiePractitioner");
	var practitionerid = read_cookie("cookiePractitionerId");
	//alert(practitionerid);
	
	var url = "modifyAccountLogCompleteApmt?appointmentid="+editAppointId+"&clientid="+patientId+"&practitionerid="+practitionerid+"&clientName="+editClientName+"&practitionerName="+practitionerName+"&action="+action+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = modifyCreateChargeUpdateAccountRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function modifyCreateChargeUpdateAccountRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
		}
}
}

var opdcash = '';
function createChargeAndUpdateAccount(action){

opdcash = action;

	var clientid = read_cookie("cookieClientId"); 
	var practitionerid = read_cookie("cookiePractitionerId");
	var clientName = read_cookie("cookieUserName");
	var practitionerName = read_cookie("cookiePractitioner");
	var appointmentid = read_cookie("cookieSelectedAppointmentid");
	var tratmentepisodeid = read_cookie("cookieTreatmentEpisode");
	var treatmenntsessions = read_cookie("cookieTreatmentEpidodeSessions");
	var location = document.getElementById('complocationid').value;
	
	if(location==0){
		jAlert('error', 'Please select location.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var ipd = 0;
		var url = "updateAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+"&location="+location+"&ipd="+ipd+" ";
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = createChargeAndUpdateAccountRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	
	
}

function createChargeAndUpdateAccountRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var clientName = read_cookie("cookieUserName");
			
			tempAlert(""+clientName+" Account has been updated successfully.", 5000);
			document.getElementById('cashDesk').disabled = true;

		      
			/*  var button2 = $(".ui-dialog-buttonpane button:contains('Invoice Now / Pay Later')");
		      console.log(button2);
		      $(button2).button("enable");
		      
		      var button3 = $(".ui-dialog-buttonpane button:contains('Cash Desk')");
		      console.log(button3);
		      $(button3).button("enable");
		      
		      var button1 = $(".ui-dialog-buttonpane button:contains('Create Charge & Update Account')");
		      console.log(button1);
		      $(button1).button("disable");
			
		      compApmtinvoiceID = req.responseText; 
		      
		      document.getElementById('addChargeType').disabled = true;
		      document.getElementById('addchargebtn').disabled = true;
		      
		      if(compApmtinvoiceID == 0){
		    	  var button2 = $(".ui-dialog-buttonpane button:contains('Invoice Now / Pay Later')");
			      console.log(button2);
			      $(button2).button("disable");
		      }*/
		      
		    	var invoiceid = req.responseText;
				
				if(opdcash=='cash'){
					setopdInstantCashDesk(invoiceid);
				}
		      
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



function updateChargeAccount(action){
	var clientid = document.getElementById('clientId1').value;
	
	var practitionerid = document.getElementById('practitionerId1').value;
	var clientName = document.getElementById('clientname1').value;
	var practitionerName =document.getElementById('practitionerName1').value;
	var appointmentid =document.getElementById('editAppointId1').value;
	var location = document.getElementById('editcomplocationid').value;
	
	if(location==0){
		jAlert('error', 'Please select location.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var url = "updateChargeAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&location="+location+" ";
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updateChargeAccountRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	
}

function updateChargeAccountRequest(){
	/*setCommonAction();
	   
    var temp = apmuserlist.split(',');
		for(i=0;i<temp.length;i++){
			if(temp[i]!=$.cometChat.loginUserName){
				$.cometChat.send("apmt_other", temp[i],"");
			}
			
		}*/
}

function submitInvoice(){
	
	var clientid = read_cookie("cookieClientId"); 
	var clientName = read_cookie("cookieUserName");
	var appointmentid = read_cookie("cookieSelectedAppointmentid");
	var date = read_cookie("cookiecommencing");
	var url = "submitInvoiceCompleteApmt?invoiceId="+compApmtinvoiceID+"";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = submitInvoiceRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function submitInvoiceRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("selfChargeDesk").innerHTML = req.responseText;
			document.getElementById('invoiceNo').value = compApmtinvoiceID;
			$('#submitInvoicePopup').modal( "show" );
			
			
		}
	}
	
}

function updateInvoiceStatus(){
	var invoicenotes = document.getElementById('invoicenotes').value;
	var url = "updateStatusInvoiceCompleteApmt?invoiceId="+compApmtinvoiceID+"&invoicenotes="+invoicenotes+"";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updateInvoiceStatusRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function updateInvoiceStatusRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//jAlert('success', "Invoice has been Submited successfully!!", 'Success Dialog');
			tempAlert("Invoice has been Submited successfully", 5000);
			$('#submitInvoicePopup').modal( "hide" );
			
			var button1 = $(".ui-dialog-buttonpane button:contains('Record Payment')");
		    console.log(button1);
		    $(button1).button("enable");
		    
		    var button2 = $(".ui-dialog-buttonpane button:contains('Submit Invoice')");
		      console.log(button2);
		      $(button2).button("disable");
		}
	}
	
}

function cancelCompleteApmt(){

	var appointmentid = read_cookie("cookieSelectedAppointmentid");
	
	
	var url = "cancelCompleteApmt?appointmentid="+appointmentid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = cancelCompleteApmtRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}


function cancelCompleteApmtRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			$(this).cancelSlot();
			
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




function setAdditionalChargeAjax(apmtTypeid){
	
	if(document.getElementById('addChargeType').value==0){
		document.getElementById('addchargebtn').disabled = 'disabled';
	}else{
		
		document.getElementById('addchargebtn').disabled = '';
	}
	
	if(document.getElementById('addChargeType').value==0){
		document.getElementById('mannualcharge').disabled = '';
		document.getElementById('charge').disabled = '';
		
	}else{
		document.getElementById('mannualcharge').value = '';
		document.getElementById('mannualcharge').disabled = 'disabled';
		document.getElementById('charge').disabled = 'disabled';
	}
	
	var masterchargetype = document.getElementById('masterchargetype').value;
	
	var url = "chargeAppointmentType?selectedAppointmentID="+apmtTypeid+"&masterchargetype="+masterchargetype+" ";
	
	//var url = "chargeAppointmentType?selectedAppointmentID="+apmtTypeid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAdditionalChargeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
}

function setAdditionalChargeAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str=req.responseText;
	         var data=str.split("~");
			
			/*document.getElementById('charge').value = currencySign+req.responseText;
			
			//var qty = document.getElementById('quantity').value;
			var charge = req.responseText;
			calcamount(charge);*/
	         
	         document.getElementById('charge').value = currencySign+data[0];
	         if(document.getElementById('hdncharge')){
	        	 document.getElementById('hdncharge').value = data[0];
	         }
	         
				//var qty = document.getElementById('quantity').value;
				var charge = data[0];
				calcamount(charge);
			
			
			if(document.getElementById('addChargeType').value==0){
				document.getElementById('charge').value = '';
				document.getElementById('amount').innerHTML = '';
			}
			
			if(document.getElementById('isindisharecharge')){
				document.getElementById('isindisharecharge').value = data[1]; 
			}
			/*var amount = parseFloat(charge) * qty;
			
			document.getElementById('amount').innerHTML = currencySign+amount;*/
			document.getElementById('isindisharecharge').value = data[1]; 
			var nonedit=data[2];
			if(nonedit==1){
				document.getElementById("charge").readOnly = true;
			}else{
				document.getElementById("charge").readOnly = false;
			}
		}
	}
	
}



function setAdditionalChargeTypeAjax(who){
	newWhoPay = who;
	var url = "additionalChargeAppointmentType?who="+who+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAdditionalChargeTypeAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setAdditionalChargeTypeAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('additionalChargeAjax').innerHTML = req.responseText;
			document.getElementById('charge').value = '';
		
			setSelectedThirdPartyNameAjax();
			
			
		}
	}
}

	
function getTreatmentEpisodeDetailsAjax(){
	
	var tratmentepisodeid = read_cookie("cookieTreatmentEpisode");
	var cookieSelectedAppointmentid = read_cookie("cookieSelectedAppointmentid");

	
	var url = "detailsTreatmentEpisode?tratmentepisodeid="+tratmentepisodeid+"&cookieSelectedAppointmentid="+cookieSelectedAppointmentid+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getTreatmentEpisodeDetailsAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}	


function getTreatmentEpisodeDetailsAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			   TreatmentEpiode = req.responseText;
			 
			   var temp = TreatmentEpiode.split("*");
			   var tratmentepisodeid = read_cookie("cookieTreatmentEpisode");
			   if(tratmentepisodeid!="0"){
			   document.getElementById('tmntesode').innerHTML = "Treatment Episode: "+temp[3]+" of  ("+temp[1]+")";
			   }
			 //  document.getElementById('authcode').value = temp[0];
			  
			   
			   document.cookie = "cookieConsultationLimit=" + temp[1];
			   document.cookie = "cookieTreatmentEpidodeSessions=" + temp[3];
			   var payby = temp[2];
			   var clientName = read_cookie("cookieUserName");
			  
			   document.cookie = "cookiePayBy=" + 0;
			  newWhoPay = payby;
			   if(payby == 'Client'){
				   cookieUserName=read_cookie("cookieUserName");
			   		// document.getElementById('authcodetr').style.display = 'none';
			   		document.getElementById("payBuy1").disabled = true;
			   		document.getElementById("payBuy").checked = true;
			   		// document.getElementById('self').style.display = '';
			   		//document.getElementById('tp').style.display = 'none';
			   		document.getElementById('payee').innerHTML = "Self";
			   		document.getElementById('chargeTo1').innerHTML = clientName;
			   		document.getElementById('chargeTo2').innerHTML = clientName;
			   		
			   		//tp notes
			   		//document.getElementById('tpnotesdiv').style.display = 'none';
			   		document.getElementById('tpnotes').value = temp[4];
			   }
			   else if(tratmentepisodeid == "0"){
				  // payby = 'Client';
				  // newWhoPay = payby;
				   cookieUserName=read_cookie("cookieUserName");
				   document.cookie = "cookiePayBy=" + 0;
				   document.getElementById("payBuy1").disabled = true;
				   document.getElementById("payBuy").checked = true;
			   		document.getElementById('payee').innerHTML = "Self";
			   		document.getElementById('chargeTo1').innerHTML = clientName;
			   		document.getElementById('chargeTo2').innerHTML = clientName;
			   		
			   		//tp notes
			   		//document.getElementById('tpnotesdiv').style.display = 'none';
			   		document.getElementById('tpnotes').value = temp[4];
			   }
			   else{
			   		
				//   document.getElementById('authcodetr').style.display = '';
			   		    document.getElementById('payBuy').value = 1;
				   		document.getElementById("payBuy1").disabled = false;
				   		document.getElementById("payBuy1").checked = true;
				   		document.getElementById('payee').innerHTML = "Third Party";
				   		
			   		// document.getElementById('self').style.display = 'none';
				   	//document.getElementById('tp').style.display = '';
				   	
				    document.cookie = "cookiePayBy=" + 1;
				  
				    //tp notes
				  //  document.getElementById('tpnotesdiv').style.display = '';
				    document.getElementById('tpnotes').value = temp[4];
			   }
			
			   setAdditionalChargeTypeAjax(payby);
		}
	}
}	


function setSelectedThirdPartyNameAjax(){
	
		
	var clientid = read_cookie("cookieClientId");
	
	var url = "thirdpartyTreatmentEpisode?clientid="+clientid+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setSelectedThirdPartyNameAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setSelectedThirdPartyNameAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			cookieUserName=read_cookie("cookieUserName");
			//document.getElementById('chargetodiv').innerHTML = req.responseText;
			document.cookie = "cookieThirdPartyName=" + req.responseText;
			
			
			if(newWhoPay == 'Client'){
				//document.getElementById('chargeTo1').innerHTML = cookieUserName;
				document.getElementById('chargeTo2').innerHTML = cookieUserName;
			}
			else if(newWhoPay == 'Third Party'){
				document.getElementById('chargeTo1').innerHTML = req.responseText;	
				document.getElementById('chargeTo2').innerHTML = req.responseText;
			}
			else{
				document.getElementById('chargeTo1').innerHTML = cookieUserName;
				document.getElementById('chargeTo2').innerHTML = cookieUserName;
			}
			setChargeAmount();
		}
	}

}	
	

function setAppointmentTypeCharge(apmtTypeId)
{
	//alert(apmtTypeId);

	var url = "getApmtChargeCompleteApmt?apmtTypeId="+apmtTypeId+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAppointmentTypeChargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
var tempDuration = 0;
function setAppointmentTypeChargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			   document.getElementById("chargeajax").innerHTML = req.responseText;
				
			
			
			
		}
	}
}


function setChargeAmount(){
	
	

var flag = 0;
var charge = "";
var apmtTypeId = "";
//var payBuy = $("input[name='payBuy']:checked").val();
var payBuy = "";

var markAppointment = 1;

if(document.getElementById('charge').value!=""){
	
	 charge = parseInt(document.getElementById('charge').value);
	 apmtTypeId = document.getElementById('addChargeType').value;
	 payBuy = $("input[name='payBuy']:checked").val();
	 
	 
}else{ 
	
	 charge = read_cookie("cookieCharge");
	 apmtTypeId = read_cookie("cookieapmtType");
	 payBuy = read_cookie("cookiePayBy");
	 
	
	 
}


cookieUserName=read_cookie("cookieUserName");
cookieStarttime=read_cookie("cookieStarttime");
cookieDuration=read_cookie("cookieDuration");
cookiePractitioner=read_cookie("cookiePractitioner");
cookieapmtType=read_cookie("cookieapmtType");
cookiePractitionerId=read_cookie("cookiePractitionerId");
cookieClientId=read_cookie("cookieClientId"); 
cookiecommencing=read_cookie("cookiecommencing");
cookieCharge = read_cookie("cookieCharge");
cookieSelectedAppointmentid = read_cookie("cookieSelectedAppointmentid");


var chargetype =  $("#masterchargetype option:selected").text();
var quantity =  document.getElementById("quantity").value;
var mannualcharge = document.getElementById("mannualcharge").value;

var visitingconsulatntdr =0;
var isindisharecharge = document.getElementById("isindisharecharge").value;
if(chargetype=='IPD Visiting Charge' || chargetype=='Consultation Charge' || isindisharecharge=='1'){
	visitingconsulatntdr = document.getElementById('consultantdr').value;
 }


var url = "saveChargeCompleteApmt?apmtTypeId="+apmtTypeId+"&cookieUserName="+cookieUserName+"&charge= "+charge+"&cookieDuration="+cookieDuration+"&cookieStarttime="+cookieStarttime+"&cookiePractitionerId="+cookiePractitionerId+"&cookieClientId="+cookieClientId+"&cookiePractitioner="+cookiePractitioner+"&cookiecommencing="+cookiecommencing+"&payBuy="+payBuy+"&markAppointment="+markAppointment+"&cookieSelectedAppointmentid="+editAppointId+"&chargetype="+chargetype+"&quantity="+quantity+"&mannualcharge="+mannualcharge+"&visitingconsulatntdr="+visitingconsulatntdr+"&isindisharecharge="+isindisharecharge+" ";


if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setChargeAmountRequest;
    req.open("GET", url, true); 
              
    req.send(null);


}

function setChargeAmountRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			   document.getElementById("chargeTotalajax").innerHTML = req.responseText;
				setCashDesk();		
			
			
			
		}
	}
}


function getInventoryProductStock(){
		var masterchargetype = document.getElementById("masterchargetype").value;
		var prodid = document.getElementById("addChargeType").value;
		var flag = false;
		var isindisharecharge = document.getElementById("isindisharecharge").value;
		if(masterchargetype=='IPD Visiting Charge' || masterchargetype=='Consultation Charge' || isindisharecharge=='1'){
			var consultantdr = document.getElementById("consultantdr").value;
			if(consultantdr=='0'){
				flag = true;
			}
		 }
		if(flag==true){
			jAlert('error', 'Please select consultant.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
		else{
		
			var url = "prodstockIpd?masterchargetype="+masterchargetype+"&prodid="+prodid+" ";
			  
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getInventoryProductStockRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
		}
	}

function getInventoryProductStockRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var data = req.responseText;
				var temp = data.split('/');
				
				var isinventory = temp[0];
				var stock = temp[1];
				var sumqty = temp[2];
				var quantity = document.getElementById('quantity').value;
				
				if(parseInt(stock)==parseInt(sumqty)){
					quantity =  quantity + sumqty;
				}
				
				if(isinventory>0){
					if(stock>0){
						if(parseInt(quantity)<=parseInt(stock)){
							setChargeAmount();
						}else{
							jAlert('error', 'Quantity can not be greater than stock!!', 'Error Dialog');
		
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						}
							
					}else{
						jAlert('error', 'No more stock!!', 'Error Dialog');
		
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					}
				}else{
					setChargeAmount();	
				}
			}
		}
	}

function setCashDesk(){
var selectedUser = pppid;
	//alert(selectedUser);
	//alert(cookiecommencing);
	//alert(cookieSelectedAppointmentid);
//var url = "cashDeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+editAppointId+"" ;
//  2018-May-04
var url = "cashDeskBookAppointmentAjax?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+editAppointId+"" ;

if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setCashDeskRequest;
    req.open("GET", url, true); 
              
    req.send(null);


}

function setCashDeskRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			   document.getElementById("cashDesk").innerHTML = req.responseText;
					document.getElementById('chargeTotal').value = document.getElementById('hiddenTotal').value;	
			
//					$( "#preloader" ).modal( "show" );
					if(document.getElementById("yuviloader")){
					document.getElementById("yuviloader").style.display = "none";
					}
			
		}
	}
}


//tptype temp charge updation
function updatetptypetempcharge(dbid,id,val){
	var url = "invnewchargeCompleteApmt?dbid="+dbid+"&charge="+val+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updatetptypetempchargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
   
}


function updatetptypetempchargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			setCashDesk();
		}
	}
   
}





function OpenCashdeskpopUP(){
	var selectedUser = document.getElementById('clientId').value;
	/*showPopWin("cashDeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"", 400, 400, null);*/
	//  2018-May-04
	showPopWin("cashDeskBookAppointmentAjax?selectedUser="+selectedUser+"&date="+cookiecommencing+"", 400, 400, null);
}



function saveApmtCompletedRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			   

			
			
			
		}
	}
}

//unnati 25 th march
/*function resetAppointmentField(){
	
	var duration = document.getElementById('apmtType').value;
			
			total = "00:00:00";
	//globalEndTime = etime;
	
	var stime = document.getElementById('sTime').value;
	
	var total = getTimeTotal(duration,stime);
	//alert(total)
	document.getElementById('endTime').value = total;
	
	var commencing = document.getElementById('date').value;
	var location = document.getElementById('location').value;
	var diaryuserId = document.getElementById('diaryUserId').value;
	
	//alert(commencing +"/"+ location +"/"+ diaryuserId);
	checkEventAllreadyExist(commencing,location,diaryuserId,stime,total,editAppointId);
	
}*/

function setApmtChargeAndDuration(apmtTypeId)
{
	//alert(apmtTypeId);
	
	var url = "getApChargeAndDurationCompleteApmt?apmtTypeId="+apmtTypeId+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setApmtChargeAndDurationRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
var tempDuration = 0;
function setApmtChargeAndDurationRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			   document.getElementById("chargeajax").innerHTML = req.responseText;
				
			var duration = document.getElementById('duration').value;
			
			total = "00:00:00";
	//globalEndTime = etime;
	
	var stime = document.getElementById('sTime').value;
	
	var total = getTimeTotal(duration,stime);
	//alert(total)
	document.getElementById('endTime').value = total;
	var charge = document.getElementById('charge').value;
	var totalcharge = document.getElementById('chargeTotal').value;
	
	document.getElementById('chargeTotal').value = parseInt(charge)+parseInt(totalcharge);
	
	
			
			
		}
	}
}

function setInvoiceeAsClient(){
var client = document.getElementById('client').value;
if(client =="")
{
    jAlert('error', 'Please select Client by Clicking on Search Button.', 'Error Dialog');
	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration);
  

}
var client = document.getElementById('client').value;
document.getElementById('invoicee').value = client;
}
function setInvoiceeAsThirdParty(){
var client = document.getElementById('client').value;
if(client =="")
{
    jAlert('error', 'Please select Client by Clicking on Search Button.', 'Error Dialog');

	
}
var clientId = document.getElementById('clientId').value;
var url = "getThirdPartyCompleteApmt?clientId="+clientId+"";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setInvoiceeAsThirdPartyRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setInvoiceeAsThirdPartyRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			   document.getElementById("thirdPartyAjax").innerHTML = req.responseText;
				
			
			
			
		}
	}
}



/*function getTimeTotal(time1,time2)
{
var time1_hr = "";
var time1_min = "";
var time2_hr = "";
var time2_min = "";
var total_hrtime = "";
var total_mintime = "";
var generated_Hour = 0;
    time1_hr = time1.split(":")[0];
    time1_min = time1.split(":")[1];
    time2_hr = time2.split(":")[0];
    time2_min = time2.split(":")[1];

    total_hrtime = 1* time1_hr + 1*time2_hr;

    //alert(total_hrtime);
    total_mintime = 1* time1_min + 1*time2_min;
    //alert(total_mintime);

    if(total_mintime>=60)
    {
        total_mintime = total_mintime - 60;
        total_hrtime = total_hrtime + 1;
    }

    if(total_hrtime>=24)
    {
        total_hrtime = total_hrtime -24 ;
                if(total_hrtime<10)
    total_hrtime = "0"+total_hrtime;

    }
    
    if(total_hrtime <=9){
    	total_hrtime = "0" + total_hrtime;
    }
    if(total_mintime <= 5){
    	total_mintime = "0" + total_mintime;
    }
    

	return total_hrtime+":"+total_mintime;

//alert(total_hrtime+":"+total_mintime);  
}*/
function setPractioner(){
	var e = document.getElementById('diaryUser');
	practitionerName = e.options[e.selectedIndex].text;
	document.getElementById('practitionerName').value = practitionerName;
	document.getElementById('practitionerId').value = document.getElementById('diaryUser').value;

}
function saveClientCharge(){
	var payBuy = $("input[name='payBuy']:checked").val();
	
 	var markAppointment = 0;
	var charge = parseInt(document.getElementById('charge').value);
	var apmtTypeId = document.getElementById('apmtType').value;
	var client = document.getElementById('client').value;
	var clientId = document.getElementById('clientId').value;
	var duration = document.getElementById('duration').value;
	var startTime = document.getElementById('sTime').value;
	var endTime = document.getElementById('endTime').value;
	var practionerId = document.getElementById('diaryUser').value;
	var date = document.getElementById('commencing').value;
	cookieSelectedAppointmentid = 0;
	var chk = 0;
	document.getElementById('diaryUserError').innerHTML="";
	document.getElementById('apmtTypeError').innerHTML="";
	document.getElementById('clientError').innerHTML="";
	document.getElementById('sTimeError').innerHTML="";
	document.getElementById('commencingError').innerHTML="";
	document.getElementById('payBuyError').innerHTML="";
	
	if (practionerId ==  "0") {
      	var practionerId = document.createElement("label");
      	practionerId.innerHTML = "Select Practitioner";
        document.getElementById('diaryUserError').appendChild(practionerId);
        chk=1;
     }
	if (apmtTypeId ==  "0") {
      	var apmtTypeId = document.createElement("label");
      	apmtTypeId.innerHTML = "Select Appointment";
        document.getElementById('apmtTypeError').appendChild(apmtTypeId);
        chk=1;
     }
	if (client ==  "") {
      	var client = document.createElement("label");
      	client.innerHTML = "Select Client";
        document.getElementById('clientError').appendChild(client);
        chk=1;
     }
	if (startTime ==  "") {
      	var startTime = document.createElement("label");
      	startTime.innerHTML = "Select start Time";
        document.getElementById('sTimeError').appendChild(startTime);
        chk=1;
     }
	if (date ==  "") {
      	var date = document.createElement("label");
      	date.innerHTML = "Select Date";
        document.getElementById('commencingError').appendChild(date);
        chk=1;
     }
	if (payBuy ==  undefined) {
      	var payBuy = document.createElement("label");
      	payBuy.innerHTML = "Select Pay buy";
        document.getElementById('payBuyError').appendChild(payBuy);
        chk=1;
     }
	
	if(chk==1){
		return false;
	}
	else{
		var url = "saveChargeCompleteApmt?apmtTypeId="+apmtTypeId+"&cookieUserName="+client+"&charge= "+charge+"&cookieDuration="+duration+"&cookieStarttime="+startTime+"&cookiePractitionerId="+practionerId+"&cookieClientId="+clientId+"&cookiePractitioner="+practitionerName+"&cookiecommencing="+date+"&payBuy="+payBuy+"&markAppointment="+markAppointment+"&cookieSelectedAppointmentid="+cookieSelectedAppointmentid+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setChargeAmountRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		return true;
	}
	
	
	




}
function setDNACharge()
{
//alert(cookieCharge);
//alert(cookiePractitioner);
//alert(cookiePractitionerId);
document.getElementById('practitionerName').value = cookiePractitioner;
document.getElementById('practitionerId').value = cookiePractitionerId;

}

function confirmedDelete1(id){
	//alert(id);
	if(compApmtinvoiceID==0 || compApmtinvoiceID == null || compApmtinvoiceID == undefined){
  	var url = "deleteCashDeskCompleteApmt?selectedid="+id+"";
  
 
if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = confirmedDeleteRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}

}

function confirmedDeleteRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			  // document.getElementById("thirdPartyAjax").innerHTML = req.responseText;
				setCashDesk();
			
			
			
		}
	}
}


function selfInvoiceTotal(){
	
	var url = "selfInvSumCompleteApmt?invoiceid="+compApmtinvoiceID+"";
	  
	 
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = selfInvoiceTotalRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function selfInvoiceTotalRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			selfInvoiceTotalAmount =  req.responseText;
			
			 if(selfInvoiceTotalAmount == 0){
		    	  
		    	  var button1 = $(".ui-dialog-buttonpane button:contains('Record Payment')");
			      console.log(button1);
			      $(button1).button("disable");
		      }
			
		}
	}
}


function paynowForInvoice(){
	
	$( "#createinvoiceDiv" ).modal( "show" );
	var clientName = read_cookie("cookieUserName");
	document.getElementById('compApmtClient').value = clientName;
	document.getElementById('compApmtInvoiceId').value = compApmtinvoiceID;
	var url = "selfInvSumCompleteApmt?invoiceid="+compApmtinvoiceID+"";
	  
	 
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = paynowForInvoiceRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

}

function paynowForInvoiceRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			 document.getElementById("selfChargeDesk1").innerHTML = req.responseText;
			 var charge = document.getElementById('hiddentotal1').value;
			 document.getElementById('creditCharge').value = charge;
			 document.getElementById('payAmount').value = charge;
			 document.getElementById('totalamount').value = charge;
			 
			 var button1 = $(".ui-dialog-buttonpane button:contains('Preview')");
		     console.log(button1);
		     $(button1).button("disable");
		     
		     var button2 = $(".ui-dialog-buttonpane button:contains('Pay')");
			 console.log(button2);
			 $(button2).button("enable");
			    
			
		}
	}
	
}



function savePaymentForInvoice(){
	var clientName = read_cookie("cookieUserName");
	var clientid = read_cookie("cookieClientId"); 
	var date = document.getElementById('invoiceDate').value;
	var paymode = document.getElementById('howpaid').value;
	var payAmount = document.getElementById('payAmount').value;
	var discount = document.getElementById('discount').value;
	
	if(paymode==0){
		 jAlert('error', 'Please select payment mode.', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);

	}else{
		var url = "savePaymentCompleteApmt?invoiceid="+compApmtinvoiceID+"&clientName="+clientName+"&clientid="+clientid+"&date="+date+"&paymode="+paymode+"&payAmount="+payAmount+"&discount="+discount+" ";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = savePaymentForInvoiceRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	


}

function savePaymentForInvoiceRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
            //jAlert('success', 'Payment done successfully!!', 'Success Dialog');
            tempAlert("Payment done successfully.",5000);
            var button1 = $(".ui-dialog-buttonpane button:contains('Preview')");
		    console.log(button1);
		    $(button1).button("enable");
		    
		    var button2 = $(".ui-dialog-buttonpane button:contains('Pay')");
		    console.log(button2);
		    $(button2).button("disable");
		    
		    var button3 = $(".ui-dialog-buttonpane button:contains('Submit Invoice')");
		    console.log(button3);
		    $(button3).button("disable");
		    
		    var button4 = $(".ui-dialog-buttonpane button:contains('Record Payment')");
		    console.log(button4);
		    $(button4).button("disable");
		    
		    var button5 = $(".ui-dialog-buttonpane button:contains('Cash Desk')");
		    console.log(button5);
		    $(button5).button("disable");
		      
			/*$( '#createinvoiceDiv' ).dialog( "close" );
			$( '#completeAppointmentDivId' ).dialog( "close" );
			$( '#modifyPopup' ).dialog( "close" );*/
		}
	}
}

function previewPaymentInvoice(payby,compApmtInvoiceId,payAmount,creditamt){
	
	
	var payby = "0";
	var payAmount = 0; var debitamt = 0;
	var invoiceid = document.getElementById('compApmtInvoiceId').value;
	var discount = document.getElementById('discount').value;
	if(discount == 0 || discount == null || discount == undefined){
		payAmount = document.getElementById('payAmount').value;
		var creditamt = document.getElementById('creditCharge').value;
		 debitamt = creditamt - payAmount;
	}
	else{
		 payAmount = document.getElementById('payAmount').value;
		var totalAmount = document.getElementById('totalamount').value;
		 discount = document.getElementById('discount').value;
		var discountAmt = totalAmount * (discount/100);
		var amountDue = totalAmount - discountAmt;
		debitamt = amountDue - payAmount;
	}
	document.getElementById('payby').value = payby;
	document.getElementById('invoiceid').value = invoiceid;
	document.getElementById('debitamt').value = debitamt;
	document.getElementById('creditamt').value = payAmount;
	document.getElementById('discountany').value = discount;
	document.getElementById('previewfrm').submit();
	
	var button1 = $(".ui-dialog-buttonpane button:contains('Pay')");
    console.log(button1);
    $(button1).button("disable");
	
	
}

function setAmountDue(){
	var totalAmount = document.getElementById('totalamount').value;
	var disctype = document.getElementById('disctype').value;
	var discount = document.getElementById('discount').value;
	
	if(disctype==1){
	 discountAmt = discount;
	 var amountDue = totalAmount - discountAmt;
	 document.getElementById('payAmount').value = amountDue;
	}else if(disctype==0){
		if(discount>100){
			 jAlert('error', 'Discount Should be less than 100%.', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				document.getElementById('discount').value='0';
		
		}else{
		var discountAmt = totalAmount * (discount/100);
		var amountDue = totalAmount - discountAmt;
		document.getElementById('payAmount').value = amountDue;
		}
	}else{
	   
	
	document.getElementById('payAmount').value = totalAmount;
	}
}


	var newchargetype = "";
	function filterCharges(chargetype){
			newchargetype = chargetype;
			var whopay = "";
			if(document.getElementById('paybypatient1').checked == true){
				
				whopay = "Third Party";
			}else{
				
				whopay = "Client";
			}
		
		  	/*var url = "apmtchargeIpd?chargetype="+chargetype+"&tpid="+globaltpid+"&whopay="+whopay+" ";*/
			var url = "apmtchargeBookAppointmentAjax?chargetype="+chargetype+"&tpid="+globaltpid+"&whopay="+whopay+" ";
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = filterChargesRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
				
		}
		
		function filterChargesRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					 document.getElementById("additionalChargeAjax").innerHTML = req.responseText;
					 $("#addChargeType").trigger("chosen:updated");
    			  	  $(".chosen").chosen({allow_single_deselect: true});
    			  	  
    			  	 if(newchargetype=='IPD Visiting Charge' || newchargetype=='Consultation Charge'){
    			  		 //akash 20 July 2018
        			 	 //setvisitingdrlist();
    			 	 }else{
    			 		document.getElementById("visitingconsltantdiv").innerHTML = "<input type='hidden' id='consultantdr' value='0'>";
    			 	 }
    			  	 //akash 20 July 2018
    			 	 setvisitingdrlist();
				}
			}
		}
		
		function setvisitingdrlist() {
			
			//var url = "getvisitingconsultantlistIpd";
			var url = "getvisitingconsultantlistBookAppointmentAjax";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}

			req.onreadystatechange = setvisitingdrlistRequest;
			req.open("GET", url, true);

			req.send(null);

		}

		function setvisitingdrlistRequest() {

			if (req.readyState == 4) {
				if (req.status == 200) {
					document.getElementById("visitingconsltantdiv").innerHTML = req.responseText;
					   $("#consultantdr").trigger("chosen:updated");
					   $(".chosen").chosen({allow_single_deselect: true});
				}
			}

		}
		
	function calcmanualcharge(){
document.getElementById('hdncharge').value = document.getElementById('charge').value;
if(document.getElementById('mannualcharge').value=='' || document.getElementById('hdncharge').value==''){
	document.getElementById('addchargebtn').disabled = 'disabled';
}else{
	document.getElementById('addchargebtn').disabled = '';
}
document.getElementById('hdncharge').value = document.getElementById('charge').value;
var charge = document.getElementById('hdncharge').value;
calcamount(charge);
}	

function calcamount(){
	var charge = document.getElementById('hdncharge').value;
	var qty = document.getElementById('quantity').value;
	var amount = parseFloat(charge) * qty;
	document.getElementById('amount').innerHTML = currencySign+amount;
}	

function checkdiscper() {
	var totalAmount = document.getElementById('totalamount').value;
	var disctype = document.getElementById('disctype').value;
	var discount = document.getElementById('discount').value;
	
	if(disctype==1){
	 discountAmt = discount;
	 var amountDue = totalAmount - discountAmt;
	 document.getElementById('payAmount').value = amountDue;
	}else if(disctype==0){
		if(discount>100){
			 jAlert('error', 'Discount Should be less than 100%.', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				document.getElementById('discount').value='0';
				document.getElementById('payAmount').value=totalAmount;
		}else{
		var discountAmt = totalAmount * (discount/100);
		var amountDue = totalAmount - discountAmt;
		document.getElementById('payAmount').value = amountDue;
		}
	}else{
	   
	
	document.getElementById('payAmount').value = totalAmount;
	}
}

