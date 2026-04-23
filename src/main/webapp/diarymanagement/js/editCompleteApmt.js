//Edit Complete Appointment
var completeApmtSlotId = "";
var pname = "";
var pid = "";
var cname = "";
var cid = "";
var newapmttype = "";
var newcharge = "";
var newdate = "";
var newstarttime = "";
var deleteindex = "";

function editCompleteApmt(){
	
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	id = editAppointId;
	$( "#completeActionPopup" ).modal( "hide" );
	 $( "#editcompleteAppointmentDivId" ).modal( "show" );
	 $.ajax({
			url: "diarymanagement/pages/JQGridMasterEditCompleteAppointment.jsp?id="+id+" " ,
			dataType : "json",
			success : function(json) {
				//alert("Your JSON : " + JSON.stringify(json));
				
				
				var data = JSON.parse(JSON.stringify(json));   
				
	                     $.each(data,function(row,store)  {    
	                        $.each(store,function(key,value) {
	                            
	                          	
	                        	
	                          	
	                          	document.getElementById('selectedApmtType1').value = value.apmTypeText;
	                        	document.getElementById('appointmentcharge1').value = currencySign+value.charges;
	                        		
	                        	//getTreatmentEpisodeDetailsAjax();
	                        	
	                        	pid = value.practitionerId;
	                        	pname = value.practitionerName;
	                        	cid = value.clientId;
	                        	cname = value.client;
	                        	newdate = value.date;
	                        	newstarttime = value.starttime;
	                        	document.getElementById("editAppointId1").value = editAppointId;
	                        	document.getElementById("clientname1").value = value.client;
	                        	
	                        	
	                        	document.getElementById('completeAmtTitle1').innerHTML = "Complete Appointment for "+value.client+" on "+value.date+" at "+value.starttime+"";
	                        	document.getElementById('editcompPractName').innerHTML = ""+value.practitionerName+" at";
	                        	document.getElementById('editcomplocationid').value = value.location;
	                        	
	                        	if(value.treatmentEpisodeId !="0"){
	                        	 document.getElementById('tmntesode1').innerHTML = "Treatment Episode: "+value.usedSession+" of  ("+value.totalSession+")";
	                        	 
	                        	 //manoj
	                        	 if(value.payBuy == 'Client'){
	                        		 
	                        		//tp notes
	             			   	//	document.getElementById('tpnotesdivid').style.display = 'none';
	                        		 document.getElementById('tpnotesid').value = value.tpnotes;
	                        		 
	                        		document.getElementById('chargeTo11').innerHTML = value.client;
		 	         			   	document.getElementById('chargeTo22').innerHTML = value.client;	
		 	         			   	document.getElementById('payee1').innerHTML = "Self";
	                        	 }else{
	                        		document.getElementById('chargeTo11').innerHTML = value.invoicee;
	 	         			   		document.getElementById('chargeTo22').innerHTML = value.invoicee;
	 	         			   		document.getElementById('payee1').innerHTML = value.payBuy;
	 	         			   		
	 	         			   	  //tp notes
		 	       				  //  document.getElementById('tpnotesdivid').style.display = '';
		 	       				    document.getElementById('tpnotesid').value = value.tpnotes;
		 	         			  
	                        	 }
	                        	}
	                        	 
	                        	 if(value.treatmentEpisodeId =="0"){
	                        		 document.getElementById("payBuy1c").disabled = true;
	                        		 document.getElementById('chargeTo11').innerHTML = value.client;
	 	         			   		document.getElementById('chargeTo22').innerHTML = value.client;	
	 	         			   		document.getElementById('payee1').innerHTML = "Self";
	 	         			   		
	 	         			   		//tp notes
		 	       			   		//document.getElementById('tpnotesdivid').style.display = 'none';
	 	         			   		document.getElementById('tpnotesid').value = value.tpnotes;
	                        	 }
	                        	 /*else{
	         			   		document.getElementById('chargeTo11').innerHTML = value.invoicee;
	         			   		document.getElementById('chargeTo22').innerHTML = value.invoicee;
	         			   	document.getElementById('payee1').innerHTML = value.payBuy;
	                        	 }*/
	                        	document.getElementById('clientId1').value = value.clientId;
	                        	document.getElementById('practitionerName1').value = value.practitionerName;
	                        	document.getElementById('practitionerId1').value = value.practitionerId;
	                        	setAdditionalChargeTypeDropDown(value.payBuy);
	                           
	                            

	                        	if(value.payBuy == 'Third Party'){
	                        		document.getElementById("payBuy1c").checked = true;
	                        		document.getElementById('tpnotesid').value = value.tpnotes;
	                        	}
	                        	else{
	                        		document.getElementById("payBuyc").checked = true;
	                        	}
	                        	
	                        //	setCashDeskOfComplteApmt(id);
	                        	completeApmtSlotId = id;
	                        	cname = value.client;
								
								
	                        });        
	                     });
				
			}
		});
				
				
}
function setAdditionalChargeTypeDropDown(who){
	
	if(who=='Client'){
		document.getElementById('chargeTo22').innerHTML = cname
	}
	
	var url = "additionalCharge1AppointmentType?who="+who+"";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setDropDownRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setDropDownRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('additionaldropdown').innerHTML = req.responseText;
			setCashDeskOfComplteApmt(completeApmtSlotId);
			
		}
	}
}


function confirmedDelete12(id){
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
               
    req.onreadystatechange = confirmedDelete12Request;
    req.open("GET", url, true); 
              
    req.send(null);
	}

}

function confirmedDelete12Request(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			  // document.getElementById("thirdPartyAjax").innerHTML = req.responseText;
				setCashDeskTemp(completeApmtSlotId);
			
			
			
		}
	}
}


function getInventoryProductStock1(){
		var masterchargetype = document.getElementById("masterchargetype1").value;
		var prodid = document.getElementById("addChargeType1").value;
	
			var url = "prodstockIpd?masterchargetype="+masterchargetype+"&prodid="+prodid+" ";
			  
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = getInventoryProductStock1Request;
			    req.open("GET", url, true); 
			              
			    req.send(null);
	}

function getInventoryProductStock1Request(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var data = req.responseText;
				var temp = data.split('/');
				
				var isinventory = temp[0];
				var stock = temp[1];
				var sumqty = temp[2];
				var quantity = document.getElementById('quantity1').value;
				
				if(parseInt(stock)==parseInt(sumqty)){
					quantity =  quantity + sumqty;
				}
				
				if(isinventory>0){
					if(stock>0){
						if(parseInt(quantity)<=parseInt(stock)){
							addnewCharge();
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
					addnewCharge();	
				}
			}
		}
	}

function setCashDeskTemp(id){
completeApmtSlotId = id;
	
	var url = "assesmentListTempCompleteApmt?id="+id+"" ;


	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setCashDeskTempRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function setCashDeskTempRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			   document.getElementById("cashDesk1").innerHTML = req.responseText;
					document.getElementById('chargeTotal1').value = document.getElementById('hiddenTotal1').value;	
			
					
						
			
		}
	}
}
function setCashDeskOfComplteApmt(id){
	completeApmtSlotId = id;
	
	var url = "assesmentListCompleteApmt?id="+id+"" ;


	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setCashDeskOfComplteApmtRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);


	}

	function setCashDeskOfComplteApmtRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				   document.getElementById("cashDesk1").innerHTML = req.responseText;
						document.getElementById('chargeTotal1').value = document.getElementById('hiddenTotal1').value;	
				
						
							
				
			}
		}
	}
	
	function makeInActive(asessmentId,count){
		
		deleteindex = count;
		 var url = "makeInActiveCompleteApmt?id="+asessmentId+"";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = makeInActiveRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			

		}

		function makeInActiveRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					 document.getElementById("cashDesk1").deleteRow(deleteindex);

					//setCashDeskOfComplteApmt(completeApmtSlotId);

					
				}
			}
}
		
		function setAdditionalChargeAjax1(apmtTypeid){
			
			if(document.getElementById('addChargeType1').value==0){
				document.getElementById('addCompletechargeBtn').disabled = 'disabled';
			}else{
				
				document.getElementById('addCompletechargeBtn').disabled = '';
			}
			
	if(document.getElementById('addChargeType1').value==0){
		document.getElementById('mannualcharge1').disabled = '';
		document.getElementById('charge1').disabled = '';
		
	}else{
		document.getElementById('mannualcharge1').value = '';
		document.getElementById('mannualcharge1').disabled = 'disabled';
		document.getElementById('charge1').disabled = 'disabled';
	}
			
			var masterchargetype = document.getElementById('masterchargetype1').value;
	
			var url = "chargeAppointmentType?selectedAppointmentID="+apmtTypeid+"&masterchargetype="+masterchargetype+" ";
			//var url = "chargeAppointmentType?selectedAppointmentID="+apmtTypeid+" ";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setAdditionalChargeAjax1Request;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			
			
		}

		function setAdditionalChargeAjax1Request(){
			
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					 var str=req.responseText;
			         var data=str.split("~");
					
					/*document.getElementById('charge1').value = req.responseText;
					
					//var qty = document.getElementById('quantity1').value;
			var charge = req.responseText;
			document.getElementById('hdncharge1').value = charge;
			calcamount1(charge);*/
			
			         document.getElementById('charge1').value = data[0];
						
						//var qty = document.getElementById('quantity1').value;
				var charge = data[0];
				document.getElementById('hdncharge1').value = charge;
				calcamount1(charge);
			
			if(document.getElementById('addChargeType1').value==0){
				document.getElementById('charge1').value = '';
				document.getElementById('amount1').innerHTML = '';
			}
			
			if(document.getElementById('isindisharecharge')){
				document.getElementById('isindisharecharge').value = data[1]; 
			}
			/*var amount = parseFloat(charge) * qty;
			
			document.getElementById('amount1').innerHTML = currencySign+amount;*/
					
				}
			}
			
		}		
		
		
		
function addnewCharge(){
			var payby = ""; 
			if(document.getElementById("payBuy1c").checked == true){
				payby = "1";
			}
			else{
				payby = "0";
			}
			newapmttype = $("#addChargeType1 :selected").text(); 
			var apmttypeid = document.getElementById('addChargeType1').value;	
			newcharge = document.getElementById('charge1').value;
			var masterchargetype = document.getElementById("masterchargetype1").value;
			var inventprodid = document.getElementById('addChargeType1').value;
			
			var chargetype =  $("#masterchargetype1 option:selected").text();
			var quantity =  document.getElementById("quantity1").value;
			var mannualcharge = document.getElementById("mannualcharge1").value;
			
			var url = "addNewCharge1CompleteApmt?id="+completeApmtSlotId+"&payby="+payby+"&pid="+pid+"&pname="+pname+"&cid="+cid+"&cname="+cname+"&date="+newdate+"&starttime="+newstarttime+"&apmttype="+newapmttype+"&charge="+newcharge+"&chargetype="+chargetype+"&quantity="+quantity+"&masterchargetype="+masterchargetype+"&inventprodid="+inventprodid+"&mannualcharge="+mannualcharge+"&apmttypeid="+apmttypeid+" ";
			  
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = addnewChargeRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
				

}

			function addnewChargeRequest(){
				if (req.readyState == 4) {
					if (req.status == 200) {
						
						setCashDeskTemp(completeApmtSlotId);
						
						
						
					}
				}
			}

	
			
			

			function checkAppointmentInvoicedForCashDesk(){
				
				var myString = wraperDivId;
				if(editAppointId==0){
					myString = myString.replace(/[^\d]/g, ''); 
					editAppointId = myString;
					
				}
				
				var url = "invoicedCompleteApmt?appointmentid="+editAppointId+" ";
				  
				 
				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = checkAppointmentInvoicedForCashDeskRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);
			}

			function checkAppointmentInvoicedForCashDeskRequest(){
				if (req.readyState == 4) {
					if (req.status == 200) {
						isappointmentinvoiced = req.responseText;
					}
				}
			}

function checkAppointmentInvoiced(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	var url = "invoicedCompleteApmt?appointmentid="+editAppointId+" ";
	  
	 
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkAppointmentInvoicedRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function checkAppointmentInvoicedRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			isappointmentinvoiced = req.responseText;
			
			if(isappointmentinvoiced=='1'){
				jAlert('error', "Appointment invoiced,can't reset!!", 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				redirectToNotCompleteApmt();
			}
		}
	}
}


			
function redirectToNotCompleteApmt(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
		var practitionerName = read_cookie("cookiePractitioner");
		var practitionerid = read_cookie("cookiePractitionerId");
		
		var url = "redirectToNotCompleteApmt?appointmentid="+editAppointId+"&clientid="+patientId+"&practitionerid="+practitionerid+"&clientName="+editClientName+"&practitionerName="+practitionerName+" ";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = redirectToNotCompleteApmtRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		
	
	
	
		

}

	function redirectToNotCompleteApmtRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				$( "#modifyPopup" ).modal( "hide" );
				editAppointId = 0;
				setCommonAction();
				
				
				
			}
		}
	}	
	
	
	function editfilterCharges(chargetype){
	
		var whopay = "";
			if(document.getElementById('payBuy1c').checked == true){
				
				whopay = "Third Party";
			}else{
				
				whopay = "Client";
			}
	
	
		  	var url = "editapmtchargeIpd?chargetype="+chargetype+"&tpid="+globaltpid+"&whopay="+whopay+"&clientid="+pppid+" ";
			  
			 
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = editfilterChargesRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
				
		}
		
		function editfilterChargesRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					 document.getElementById("additionaldropdown").innerHTML = req.responseText;
					 
			              $("#addChargeType1").trigger("chosen:updated");
		    			  $(".chosen").chosen({allow_single_deselect: true});
					 
				}
			}
		}
		


function calcmanualcharge1(){
document.getElementById('hdncharge1').value = document.getElementById('charge1').value;
if(document.getElementById('mannualcharge1').value=='' || document.getElementById('hdncharge1').value==''){
	document.getElementById('addCompletechargeBtn').disabled = 'disabled';
}else{
	document.getElementById('addCompletechargeBtn').disabled = '';
}
document.getElementById('hdncharge1').value = document.getElementById('charge1').value;
var charge = document.getElementById('hdncharge1').value;
calcamount1(charge);
}	

function calcamount1(){
	var charge = document.getElementById('hdncharge1').value;
	var qty = document.getElementById('quantity1').value;
	var amount = parseFloat(charge) * qty;
	document.getElementById('amount1').innerHTML = currencySign+amount;
}	

