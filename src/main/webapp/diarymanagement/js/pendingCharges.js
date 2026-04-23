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
var cookieTreatmentEpisode = "";
var practitionerName = "";
var TreatmentEpiode = "";
var compApmtinvoiceID = 0;
var selfInvoiceTotalAmount = 0;
var diaryUser= "";
var loc = "";
var commencing = "";
var whopay1="";

$(document).ready(function(){
	
		 $( "#commencing" ).datepicker({
			 
			 	dateFormat:'dd/mm/yy',
			 	yearRange: yearrange,
			 	minDate : '30/12/1880',
				 changeMonth: true,
			     changeYear: true	 
		 });
		 
		 
	});		


function completePendingApmt(client,clientId,diaryUserId,diaryUser,treatmentEpisodeId,charges,duration,startTime,apmtType,appointmentTypeId,selectedApmtId,commencing,apmtypeText,location){
	
	document.getElementById('selectedApmtType').value = apmtypeText;
	document.getElementById('appointmentcharge').value = currencySign+charges;
		
	getTreatmentEpisodeDetailsAjax1(treatmentEpisodeId,selectedApmtId);
	

	/*document.getElementById('durationdiv').innerHTML = ""+client+","+startTime+" - "+duration+" min Appointment with "+diaryUser+" is Complete";
	document.getElementById('chargediv').innerHTML = "The charge has been created To "+client+" "+apmtypeText+" "+currencySign+" "+charges+"   ";
	document.getElementById('chargetodiv').innerHTML = client;*/
	
	document.getElementById('completeAmtTitle').innerHTML = "Complete Appointment for "+diaryUser+" on "+commencing+" at "+startTime+"";
	document.getElementById('durationdiv').innerHTML = "Treatment By:   "+diaryUser+" at "+location+"";
	
	document.getElementById('clientId').value = clientId;
	document.getElementById('practitionerName').value = diaryUser;
	document.getElementById('practitionerId').value = diaryUserId;
	
	cookieUserName = client;
	cookieStarttime = startTime;
	cookieDuration = duration;
	cookiePractitioner = diaryUser;
	cookieapmtType = apmtType;
	cookiePractitionerId = diaryUserId;
	cookieClientId = clientId ;
	cookiecommencing = commencing;
	cookieCharge= charges;
	cookieSelectedAppointmentid = selectedApmtId;
	cookieTreatmentEpisode = treatmentEpisodeId; 
	$('#completeAppointmentDivId1').modal( "show" );
	
	
	
}

function getTreatmentEpisodeDetailsAjax1(treatmentEpisodeId,selectedApmtId){
	
	//var tratmentepisodeid = cookieTreatmentEpisode;
	
	var url = "detailsTreatmentEpisode?tratmentepisodeid="+treatmentEpisodeId+"&cookieSelectedAppointmentid="+selectedApmtId+"";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getTreatmentEpisodeDetailsAjax1Request;
    req.open("GET", url, true); 
              
    req.send(null);
}	


function getTreatmentEpisodeDetailsAjax1Request(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			TreatmentEpiode = req.responseText;
			 
			   var temp = TreatmentEpiode.split("/");
			   
			 //  document.getElementById('authcode').value = temp[0];
			  
			   
			   document.cookie = "cookieConsultationLimit=" + temp[1];
			   document.cookie = "cookieTreatmentEpidodeSessions=" + temp[3];
			   var payby = temp[2];
			   if(payby == 'null'){
				   payby = 'Client';
			   }
			   document.cookie = "cookiePayBy=" + 0;
			   whopay1 = payby;
			   if(payby == 'Client'){
			   		
			   		// document.getElementById('authcodetr').style.display = 'none';
			   		document.getElementById("payBuy1").disabled = true;
			   		// document.getElementById('self').style.display = '';
			   		//document.getElementById('tp').style.display = 'none';
			   		document.getElementById('payee').innerHTML = "Self";
			   		document.getElementById('chargeTo1').innerHTML = cookieUserName;
			   		document.getElementById('chargeTo2').innerHTML = cookieUserName;
			   		
			   		
			   }else if (payby=='Third Party'){
				   document.getElementById('tmntesode').innerHTML = "Treatment Episode: "+temp[3]+" of  ("+temp[1]+")";
				//   document.getElementById('authcodetr').style.display = '';
			   		 document.getElementById('payBuy').value = 1;
				   		document.getElementById("payBuy1").disabled = false;
				   		document.getElementById("payBuy1").checked = true;
				   		document.getElementById('payee').innerHTML = "Third Party";
				   		
			   		// document.getElementById('self').style.display = 'none';
				   	//document.getElementById('tp').style.display = '';
				   	
				    document.cookie = "cookiePayBy=" + 1;
				   	
				   	
			   }
			   else{
				   document.getElementById("payBuy1").disabled = true;
			   		// document.getElementById('self').style.display = '';
			   		//document.getElementById('tp').style.display = 'none';
			   		document.getElementById('payee').innerHTML = "Self";
			   		document.getElementById('chargeTo1').innerHTML = cookieUserName;
			   		document.getElementById('chargeTo2').innerHTML = cookieUserName;
			   }
			
			   setAdditionalChargeTypeAjax1(payby);
			   
			  
		}
	}
}	
function setAdditionalChargeTypeAjax1(who){
	whopay1 = who;
	var url = "additionalChargeAppointmentType?who="+who+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAdditionalChargeTypeAjax1Request;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setAdditionalChargeTypeAjax1Request(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('additionalChargeAjax').innerHTML = req.responseText;
			document.getElementById('charge').value = '';
			setSelectedThirdPartyNameAjax1();
		}
	}
}

function setSelectedThirdPartyNameAjax1(){

	
	var clientid = cookieClientId;
	
	var url = "thirdpartyTreatmentEpisode?clientid="+clientid+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setSelectedThirdPartyNameAjax1Request;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setSelectedThirdPartyNameAjax1Request(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			//document.getElementById('chargetodiv').innerHTML = req.responseText;
			document.cookie = "cookieThirdPartyName=" + req.responseText;
			document.getElementById('chargeTo1').innerHTML = req.responseText;
			if(whopay1=='Client'){
				document.getElementById('chargeTo2').innerHTML = cookieUserName;		
			}
			else{
			document.getElementById('chargeTo2').innerHTML = req.responseText;	
			}
			setChargeAmount1();
		}
	}

}	
function setChargeAmount1(){
	var flag = 0;
	var charge = "";
	var apmtTypeId = "";
	//var payBuy = $("input[name='payBuy']:checked").val();
	var payBuy = "";

	var markAppointment = 1;

	if(document.getElementById('charge').value!=""){
		
		 charge = parseInt(document.getElementById('charge').value);
		 apmtTypeId = document.getElementById('addChargeType').value;
		 payBuy = payBuy = $("input[name='payBuy']:checked").val();
		 
		 
	}else{ 
		
		 charge = cookieCharge;
		 apmtTypeId = cookieapmtType;
		 payBuy = read_cookie("cookiePayBy");
		 
		
		 
	}
	
	/*cookieUserName=read_cookie("cookieUserName");
	cookieStarttime=read_cookie("cookieStarttime");
	cookieDuration=read_cookie("cookieDuration");
	cookiePractitioner=read_cookie("cookiePractitioner");
	cookieapmtType=read_cookie("cookieapmtType");
	cookiePractitionerId=read_cookie("cookiePractitionerId");
	cookieClientId=read_cookie("cookieClientId"); 
	cookiecommencing=read_cookie("cookiecommencing");
	cookieCharge = read_cookie("cookieCharge");*/
	//cookieSelectedAppointmentid = read_cookie("cookieSelectedAppointmentid");
	
	

	var url = "saveChargeCompleteApmt?apmtTypeId="+apmtTypeId+"&cookieUserName="+cookieUserName+"&charge= "+charge+"&cookieDuration="+cookieDuration+"&cookieStarttime="+cookieStarttime+"&cookiePractitionerId="+cookiePractitionerId+"&cookieClientId="+cookieClientId+"&cookiePractitioner="+cookiePractitioner+"&cookiecommencing="+cookiecommencing+"&payBuy="+payBuy+"&markAppointment="+markAppointment+"&cookieSelectedAppointmentid="+cookieSelectedAppointmentid+" ";


	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setChargeAmount1Request;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function setChargeAmount1Request(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				   document.getElementById("chargeTotalajax").innerHTML = req.responseText;
					setCashDesk1();		
					
					
				
				
				
			}
		}
	}
function setCashDesk1(){
	
		var selectedUser = document.getElementById('clientId').value;
			//alert(selectedUser);
			//alert(cookiecommencing);
			//alert(cookieSelectedAppointmentid);
		//var url = "cashDeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;
		//  2018-May-04
		var url = "cashDeskBookAppointmentAjax?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setCashDesk1Request;
		    req.open("GET", url, true); 
		              
		    req.send(null);


		}

		function setCashDesk1Request(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					   document.getElementById("cashDesk").innerHTML = req.responseText;
							document.getElementById('chargeTotal').value = document.getElementById('hiddenTotal').value;	
					
							
					
				}
			}
		}

		function createChargeAndUpdateAccount1(action){
			diaryUser = document.getElementById("diaryUser").value;
			loc = document.getElementById("location").value;
			commencing = document.getElementById("commencing").value;
			
				
			var clientid = cookieClientId; 
			var practitionerid = cookiePractitionerId;
			var clientName = cookieUserName;
			var practitionerName = cookiePractitioner;
			var appointmentid = cookieSelectedAppointmentid;
			var tratmentepisodeid = cookieTreatmentEpisode;
			var treatmenntsessions = read_cookie("cookieTreatmentEpidodeSessions");
			
			
			var url = "updateAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+" ";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = createChargeAndUpdateAccount1Request;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		    
		    
			
		}

		function createChargeAndUpdateAccount1Request(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					var clientName = cookieUserName;
					
					/*document.getElementById('updatebtnid').disabled = "disabled";
					document.getElementById('invoicebtnid').disabled = "";*/
					jAlert('success', clientName+" "+"Account has been updated successfully!!", 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						$("#popup_overlay").css({
							position: '',
							zIndex: 0,
							top: '0px',
							left: '0px',
							width: '',
							height: '',
							background: '',
							opacity:''
						});
					}, alertmsgduration);
					
					//tempAlert(""+clientName+" Account has been updated successfully.", 5000);

					  
					
				      compApmtinvoiceID = req.responseText;
				      
				      document.getElementById('addChargeType').disabled = true;
				      document.getElementById('addchargebtn').disabled = true;
				      
				      	document.getElementById("diaryUser").value = diaryUser;
						document.getElementById("location").value = loc ;
						document.getElementById("commencing").value = commencing;
						
						if(compApmtinvoiceID == 0){
					    	 
					      }
						$('#completeAppointmentDivId1').modal( "hide" );
				       location.reload();
						
				}
			}
			
		}	

		