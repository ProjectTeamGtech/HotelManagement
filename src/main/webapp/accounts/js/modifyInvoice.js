var totalsubjectid = 0;
var ischeked = 0;
$(document).ready(function(){

//called when key is pressed in textbox
  $("#quantity").keypress(function (e) {
     //if the letter is not digit then display error and don't type anything
     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        //display error message
        $("#errmsg").html("Digits Only").show().fadeOut("slow");
               return false;
    }
   });
	
	
	/*document.getElementById('totalassesment').value = totalsubjectid;
	
	
	
	//$("#assementtable").click(function() {
		
		
		totalsubjectid=0;
		ischeked = 0;
		
		  var chk=$("#assementtable :checked").size();
		  ischeked = chk;
		 
		   if(chk > 1){
               
	  		  $("#assementtable :checked").each(function() {
				 //alert("value = " + $(this).val());
				totalsubjectid= totalsubjectid + "," +	 $(this).val();
				});
				
			}else{
				$("#assementtable :checked").each(function() {
					totalsubjectid = $(this).val();
				});
			}
			
		 //  alert(totalsubjectid)
			document.getElementById('totalassesment').value = totalsubjectid;
			
		 
	//});
		   
		   $("#assementtable").click(function() {
				
				
			   totalsubjectid=0;
				ischeked = 0;
				
				  var chk=$("#assementtable :checked").size();
				  ischeked = chk;
				 
				   if(chk > 1){
			           
			  		  $("#assementtable :checked").each(function() {
						 //alert("value = " + $(this).val());
						totalsubjectid= totalsubjectid + "," +	 $(this).val();
						});
						
					}else{
						$("#assementtable :checked").each(function() {
							totalsubjectid = $(this).val();
						});
					}
					
				   //alert(totalsubjectid)
					document.getElementById('totalassesment').value = totalsubjectid;
					
				 
			});*/
	
  $('.case').each(function() {
		if (this.checked == true) {
			var i = this.value;
			totalsubjectid= totalsubjectid + "," +	 this.value;
			ischeked =ischeked+1;
		}
	});
  	document.getElementById('totalassesment').value = totalsubjectid;
	
});	



function modifysave(){
	document.getElementById('submitInvoiceNotes').value=""+nicEditors.findEditor('submitInvoiceNotes').getContent();
	document.getElementById('modifysavebtn').style.display='none';
	//akash 06 jun 2018
	var isforrefund='0';
	
	if(document.getElementById('isforrefund')){
		isforrefund =document.getElementById('isforrefund').value;
	}
	if(isforrefund=='1'){
		ischeked =1;
	}
	if(ischeked==0){
		jAlert('error', 'Please select atleast one charge', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		$("#dashboardloaderPopup").modal('show');
		document.getElementById('modifyinvoicefrm').submit();
	}
	
}

var tempinvid = 0;
var gdiscamt = 0;
function modifyChargePopup(invoiceid){
	/*if(document.getElementById("deletechargeamt")){
		document.getElementById("deletechargeamt").value ='0';
	}*/
	 $( "#modifychargePopupDiv" ).modal( "show" );	
	 $("#dashboardloaderPopup").modal('show');
	 setCashDeskOfComplteApmt(invoiceid);
	 tempinvid = invoiceid;
}

function discmodifychargepopup(discamt){
	var disctype = document.getElementById("discforall").value;
	var discamt = document.getElementById("allchargedisc").value;
	if(disctype==0){
		if(discamt>100){
		jAlert('error', 'Discount can not more than 100%.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		discval();
		}else if(discamt<0){
			jAlert('error', 'Discount can not less than 0% ', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			discval();
		}else{
			gdiscamt = discamt;
			setCashDeskOfComplteApmt(tempinvid);
		}
	}

	else if(disctype==1){
		if(discamt<0){
			jAlert('error', 'Discount Amount can not less than 0% ', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			discval();
		}else{
			gdiscamt = discamt;
			setCashDeskOfComplteApmt(tempinvid);
		}
	}
		 else{
			gdiscamt = discamt;
			setCashDeskOfComplteApmt(tempinvid);
		
	}
	
}


function setCashDeskOfComplteApmt(id){
	var disctype = document.getElementById("discforall").value;
	var url = "updteassementlistCompleteApmt?id="+id+"&gdiscamt="+gdiscamt+"&disctype="+disctype+" " ;


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
				  	
				
				setCashDeskTemp();		
							
				
			}
		}
	}
	
	
	function getInventoryProductStock(){
		var masterchargetype = document.getElementById("masterchargetype").value;
		var prodid = document.getElementById("addChargeType").value;
		var isindisharecharge = document.getElementById("isindisharecharge").value;
		var flag = false;
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
			/*var url = "prodstockIpd?masterchargetype="+masterchargetype+"&prodid="+prodid+" ";*/
			//17 April 2018 for speed up
			var url = "prodstockBookAppointmentAjax?masterchargetype="+masterchargetype+"&prodid="+prodid+" "; 
			 
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
	
	function setCashDeskTemp(){
		
			
			var url = "updtecashdeskCompleteApmt?id="+tempinvid+"" ;


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
					   document.getElementById("cashDesk").innerHTML = req.responseText;
							//document.getElementById('chargeTotal').value = document.getElementById('hiddenTotal1').value;
					   document.getElementById("chargeTo2").innerHTML =   document.getElementById("hdnchargeto").value;
					   document.getElementById("paybydiv").innerHTML =   document.getElementById("hdnpayby").value;
					   if(document.getElementById('quantity')){
							document.getElementById('quantity').value=1;
						}
						if(document.getElementById('addChargeType')){
							document.getElementById('addChargeType').className="";
							document.getElementById('addChargeType').value='0';
							document.getElementById('addChargeType').className="form-control showToolTip chosen";
							  $("#addChargeType").trigger("chosen:updated");
						  	   $(".chosen").chosen({allow_single_deselect: true});
						  	 setAdditionalChargeAjax(document.getElementById('addChargeType').value);
							if(document.getElementById('mannualcharge')){
								document.getElementById('mannualcharge').value="";
							}
						}
							
						 $("#dashboardloaderPopup").modal('hide');			
					
				}
			}
		}


		
function setAdditionalChargeAjax(apmtTypeid){
			
			if(document.getElementById('addChargeType').value==0){
				document.getElementById('addchargebtn').disabled = 'disabled';
					document.getElementById('mannualcharge').value = '';
					document.getElementById('mannualcharge').disabled = '';
			}else{
					
					document.getElementById('mannualcharge').disabled = 'disabled';
				document.getElementById('addchargebtn').disabled = '';
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
		               
		    req.onreadystatechange = setAdditionalChargeAjax1Request;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			
			
		}

		function setAdditionalChargeAjax1Request(){
			
			if (req.readyState == 4) {
				if (req.status == 200) {
					 var str=req.responseText;
			         var data=str.split("~");
					
					/*document.getElementById('charge').value = currencySign+req.responseText;
					var qty = document.getElementById('quantity').value;
					var charge = req.responseText;
					var amount = parseFloat(charge) * qty;
					document.getElementById('amount').innerHTML = currencySign+amount;*/
			         
			         document.getElementById('charge').value = data[0];
						var qty = document.getElementById('quantity').value;
						var charge = data[0];
						if(charge==""){
							charge=0;
						}
						var amount = parseFloat(charge) * qty;
						
						document.getElementById('amount').innerHTML = amount;
						document.getElementById('isindisharecharge').value = data[1]; 
					
				}
			}
			
		}	
		
		
		
		function setChargeAmount(){
			
			var chargetypeid = document.getElementById('addChargeType').value;
			var quantity = document.getElementById('quantity').value;
			var masterchargetype =  $("#masterchargetype option:selected").text();
			var mannualcharge = document.getElementById("mannualcharge").value;
			var manualprice = document.getElementById("charge").value;
			var date = document.getElementById("date").value;
			var compulsary_con="";
			if(document.getElementById("compulsaryconsultant")){
				 compulsary_con=document.getElementById("compulsaryconsultant").value;
			}
			//akash 02 feb 2018
			var visitingconsulatntdr =0;
			var isindisharecharge = 0;
			if(document.getElementById("isindisharecharge")){
				isindisharecharge =document.getElementById("isindisharecharge").value;
			}
			if(masterchargetype=='IPD Visiting Charge' || masterchargetype=='Consultation Charge' || isindisharecharge=='1'||compulsary_con=='1'){
				if(document.getElementById('consultantdr')){
					visitingconsulatntdr = document.getElementById('consultantdr').value;
				}
			 }
			
			var taxtypes='0';
			var chargedescription='';
			if(document.getElementById("taxtypes")){
				taxtypes=document.getElementById("taxtypes").value;
				chargedescription=document.getElementById("chargeNote1").value;
				
			}
			
			if(document.getElementById("charge").value==''){
				jAlert('error', 'Please enter price', 'Error Dialog');
		
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
			
			var url = "addnewmodifyinvoicechargeCompleteApmt?chargetypeid="+chargetypeid+"&invoiceid="+tempinvid+"&quantity="+quantity+"&masterchargetype="+masterchargetype+"&mannualcharge="+mannualcharge+"&manualprice="+manualprice+"&isindisharecharge="+isindisharecharge+"&visitingconsulatntdr="+visitingconsulatntdr+"&date="+date+"&taxtypes="+taxtypes+"&chargedescription="+chargedescription ;


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
			
		}
		
		function setChargeAmountRequest(){
			
			if (req.readyState == 4) {
				if (req.status == 200) {
					//document.getElementById('charge').value = currencySign+req.responseText;
					$('.lokclass').each(function() {
						if(this.checked){
							this.checked=false;
						}
					});
					setCashDeskTemp();
				}
			}
		}

		
		function calcmanualcharge(){
			document.getElementById('addchargebtn').disabled = '';
			document.getElementById('charge').disabled = '';
			 document.getElementById('charge').value = '';
		}
		
		
		function calcamount(){
			var charge = (document.getElementById('charge').value);
			var qty = Number(document.getElementById('quantity').value);
			
			var taxper=0;
			
			var amount = parseFloat(charge) * qty;
			document.getElementById('amount').innerHTML = currencySign+amount;
			var taxtypes='0';
			$('.lokclass').each(function() {
				if(this.checked){
					taxper=Number(taxper)+Number(this.value);
					taxtypes=taxtypes+','+this.id+'~'+this.value;
				}
			});
			var taxamt=(amount)*(taxper/100);
			taxamt=taxamt.toFixed(2);
			document.getElementById('amount').innerHTML = currencySign+(parseFloat(amount)+parseFloat(taxamt));
			if(document.getElementById('txamt')){
				document.getElementById('txamt').innerHTML=taxamt;
			}
			if(document.getElementById('taxtypes')){
				document.getElementById('taxtypes').value=taxtypes;
			}
			
	
			
		} 
		
		
		function confirmedDelete1(id,val,count){
			//alert(id);
			var d=confirm("Want to delete this Item???")
			if (d==true) {
				var flag = false;
				
				/*var i = count;
		 		var qty = document.getElementById("qty"+i).value;
		 		var perunitqty = document.getElementById("perunitqty"+i).value;
		 		var perunitprice = document.getElementById("perunitprice"+i).value;
		 		var isnumberqty = checkNumberOrNotofmdfyinvoice(qty);
		 		if(qty==''){
		 			flag = true;
		 			jAlert('error', "please enter quantity!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
						
		 		}else if(!isnumberqty){
					flag = true;
					 jAlert('error', "please enter valid quantity!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
				}
				if(!flag){
					if(document.getElementById("paymentdone")){
						var paymentdone = document.getElementById("paymentdone").value;
						var paidamt = document.getElementById("paidamt").value;
						if(paymentdone==1){
							var amt = document.getElementById("deletechargeamt").value;
							val = val +parseFloat(amt);
							
							if(parseFloat(val)>parseFloat(paidamt)){
								flag = true;
							}else{
								document.getElementById("deletechargeamt").value = val;
							}
						}
					}
				}
				*/
					
				if(!flag){
						var url = "deleteCashDeskCompleteApmt?selectedid="+id+"";
					   	if (window.XMLHttpRequest) {
							req = new XMLHttpRequest();
						}else if (window.ActiveXObject) {
							isIE = true;
							req = new ActiveXObject("Microsoft.XMLHTTP");
						}   
					    req.onreadystatechange = confirmedDeleteRequest;
					    req.open("GET", url, true); 
					    req.send(null);
				}
			}
			
		}

		function confirmedDeleteRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					  // document.getElementById("thirdPartyAjax").innerHTML = req.responseText;
					setCashDeskTemp();
				}
			}
		}

		
		
function createChargeAndUpdateAccount(type){
	document.getElementById("updtebtnmodifyinvoice").disabled =true;
	 $("#dashboardloaderPopup").modal('show');
	var flag =false;
	$('.ajclassnew').each(function() { 
 		var i = this.value;
 		var qty = document.getElementById("qty"+i).value;
 		var isnumberqty = checkNumberOrNotofmdfyinvoice(qty);
 		if(qty==''){
 			flag = true;
 			jAlert('error', "please enter quantity!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				
 		}else if(!isnumberqty){
			flag = true;
			
			 jAlert('error', "please enter valid quantity!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
		}
	});
	if(!flag){
		var url = "upteinvoicechargeCompleteApmt?invoiceid="+tempinvid+" " ;

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
	}else{
		document.getElementById("updtebtnmodifyinvoice").disabled =false;
		$("#dashboardloaderPopup").modal('hide');
	}
	
	
}

function createChargeAndUpdateAccountRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			jAlert('success', 'The invoice has been updated successfully . ', 'Success Dialog')
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			window.location.reload();
		}
	}
}
var newchargetype = "";
function filterCharges(chargetype){
			newchargetype = chargetype;
			var whopay = document.getElementById('payby').value;
			var tpid = document.getElementById('tpid').value;
			var clientId = document.getElementById('tpid').value;
		  	var url = "apmtchargeIpd?chargetype="+chargetype+"&whopay="+whopay+"&tpid="+tpid+"&clientId="+clientId+" ";
			  
			 
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
					var data=req.responseText;
					var data1= data.split("!@");
					 //lokesh 22/11/2018
					if(document.getElementById("compulsaryconsultant")){
						 document.getElementById("compulsaryconsultant").value =data1[1];
					}
					
					
					 document.getElementById("additionalChargeAjax").innerHTML = data1[0];
//					 document.getElementById("additionalChargeAjax").innerHTML = req.responseText;
					 
					 if(newchargetype=='IPD Visiting Charge' || newchargetype=='Consultation Charge' || data1[1]=='1'){
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
		
	
	
  function checkTp(val) {
  
         
         if(val=='Third Party'){
              
              var tpname=document.getElementById("tpname").value;
              if(tpname==0){
              
              		jAlert('error', 'Please Edit this Patient Payee to Third Party', 'Error Dialog');
		
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					
					document.getElementById("invoicepayby").value="Client";
              } else {
              
              		document.getElementById("tptype").className="col-lg-2 col-md-2";
              }
              
         }else{
         
				document.getElementById("tptype").className="col-lg-2 col-md-2 hidden";	          	
         }   
  }	
	
  function changeQty(iid,qty) {
        
		document.getElementById("qty"+iid).innerHTML="<input type='text' id='vqty"+iid+"' value='"+qty+"' onchange='modifyQty(this.value,"+iid+")' />";
		document.getElementById("qty"+iid).onclick=null;
  }	
	

  var tid="";

  function modifyQty(val,tempid){
  		
  		tid=tempid;
  		var url="updateqtyCompleteApmt?id="+tempid+"&qty="+val+"";
  	
  		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = modifyQtyRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);	         
          
  }
  
  
  		function modifyQtyRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
						var d=req.responseText;
						document.getElementById("qty"+tid).innerHTML=d;	
					//	document.getElementById("qty"+tid).addEventListener("click", function() {
    				//			changeQty(tid,d);
					//	}, false);
						var mrp=Number(document.getElementById("mrp"+tid).innerHTML);
						var cc=mrp*Number(d);
						document.getElementById("tottemp"+tid).innerHTML="Rs. "+cc+"";		
						countTotal();									 
				}
			}
		}
		
	function countTotal(){
	
	   var v=Number(document.getElementById("counttemp").value);
	   var sum=0;
	   for(var i=1;i<=v;i++){
	   
	   		var qty=Number(document.getElementById("qty"+i).innerHTML);
	   		var mrp=Number(document.getElementById("mrp"+i).innerHTML);
	   		var t=qty*mrp;
	   		sum=sum+t;
	   			       
	   }	
	   document.getElementById("total").innerHTML=sum;
	
	}	
		
  
	function setindivisdualdisc(discamt,id,unitcharge){
		var indcharge = document.getElementById("chargedisc").value;
		var disctype=document.getElementById("discforall").value;
		if(disctype==0){
			if(indcharge>100){
				jAlert('error', 'Discount can not more than 100%..', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				discval();
			}else if(indcharge<0){
				jAlert('error', 'Discount can not less than 0%.', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				discval();
			}else{
				var url = "individiscCompleteApmt?id="+id+"&gdiscamt="+discamt+"&unitcharge="+unitcharge+"&disctype="+disctype+" " ;
			}
		}else{
			var url = "individiscCompleteApmt?id="+id+"&gdiscamt="+discamt+"&unitcharge="+unitcharge+"&disctype="+disctype+" " ;
		}
		


		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setindivisdualdiscRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	
	function setindivisdualdiscRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				setCashDeskTemp();
			}
		}
		
	}
	
	function updateIndivQuantity(qty,id,count){
		var flag = false;
		var isnumberqty = checkNumberOrNotofmdfyinvoice(qty);
 		if(qty==''){
 			flag = true;
 			jAlert('error', "please enter quantity!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				
 		}else if(!isnumberqty){
			flag = true;
			
			 jAlert('error', "please enter valid quantity!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
		}
 		
 		/*if(!flag){
 			var perunitqty = document.getElementById("perunitqty"+count).value;
	 		var perunitprice = document.getElementById("perunitprice"+count).value;
	 		if(document.getElementById("paymentdone")){
				var paymentdone = document.getElementById("paymentdone").value;
				var paidamt = document.getElementById("paidamt").value;
				if(paymentdone==1){
					if(Number(perunitqty)>Number(qty)){
						
						var amt = document.getElementById("deletechargeamt").value;
						var diff = Number(perunitqty)-Number(qty);
						var val = diff * parseFloat(perunitprice);
						val = val +parseFloat(amt);
						
						if(parseFloat(val)>parseFloat(paidamt)){
							flag = true;
						}else{
							document.getElementById("deletechargeamt").value = val;
						}
					}
				}
			}
 		}
 		*/
 		if(!flag){
 			var url = "indiviqtyCompleteApmt?id="+id+"&qty="+qty+" " ;

 			if (window.XMLHttpRequest) {
 					req = new XMLHttpRequest();
 				}
 				else if (window.ActiveXObject) {
 					isIE = true;
 					req = new ActiveXObject("Microsoft.XMLHTTP");
 				}   
 			               
 			    req.onreadystatechange = updateIndivQuantityRequest;
 			    req.open("GET", url, true); 
 			              
 			    req.send(null);
 		}
		
	}
	function updateIndivQuantityRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				setCashDeskTemp();
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
		
	
	function changeWardininvoiceAss(invchargeid,wardid){
		/* var chargeid=document.getElementById("assesmentid"+invchargeid).value;*/
			 
		var url = "changeWardidinChargesProcessingAccount?chargeid="+invchargeid+"&wardid="+wardid;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = changeWardininvoiceAssRequest;
		req.open("GET", url, true);

		req.send(null);
	}
	
	function changeWardininvoiceAssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var x=req.responseText;
				if(parseInt(x)>0){
					jAlert('success', 'The Ward has been updated successfully . ', 'Success Dialog');
				}
				
			}
		}
			
	}
	
	
	
	function changeWardininvoiceAssByInv(invchargeid,wardid){
		/* var chargeid=document.getElementById("assesmentid"+invchargeid).value;*/
		var d=confirm("Are you sure to change Ward")
		if(d){
		var url = "changeWardidinChargesByInvidProcessingAccount?chargeid="+invchargeid+"&wardid="+wardid;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = changeWardininvoiceAssByInvRequest;
		req.open("GET", url, true);

		req.send(null);
	}else{
		window.location.reload();
	}
	}
	
	function changeWardininvoiceAssByInvRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var x=req.responseText;
				if(parseInt(x)>0){
					jAlert('success', 'The Ward has been updated successfully . ', 'Success Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				}
				
			}
		}
			
	}
	var chhhhh='';
	function changeApptChgargeMaster(id,charge){
		chhhhh=charge;
		var url = "changeapptchgargemasterProcessingAccount?chargeid="+id+"&mastercharge="+charge;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = changeApptChgargeMasterRequest;
		req.open("GET", url, true);

		req.send(null);
	}
	
	function changeApptChgargeMasterRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var x=req.responseText;
				if(parseInt(x)>0){
					document.getElementById('chargeid').innerHTML=chhhhh;
				}
				
			}
		}
			
	}
	function discval(){
		if(document.getElementById('allchargedisc')){
			document.getElementById('allchargedisc').value=0;
		}
		if(document.getElementById('chargedisc')){
			document.getElementById('chargedisc').value=0;
		}
		
		 var v=Number(document.getElementById("counttemp").value);
		 discmodifychargepopup(0);
		   for(var i=1;i<=v;i++){
			   var unit=document.getElementById('unitmt'+i).value;
			   var ids=document.getElementById('chrgeid'+i).value;
			   setindivisdualdisc(0,ids,unit);
//			   var maincharge=document.getElementById('mrp'+i).textContent;
//				document.getElementById('tottemp'+i).innerHTML =maincharge;
//				document.getElementById('discshow'+i).innerHTML=0;
		   }
		
	}
	
	function checkNumberOrNotofmdfyinvoice(inputtxt)
	{
	/*	 var numbers = /^[0-9]+$/;  */
	     var numbers=/^-{0,1}\d*\.{0,1}\d+$/;
	     if(inputtxt.match(numbers))  {
	        return true;
	     } else {
	          return false;
	     }
	} 