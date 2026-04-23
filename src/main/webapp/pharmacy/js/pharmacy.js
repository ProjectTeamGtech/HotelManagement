

var flag=0;

function calsubTotal(){
	try {
   var dummysubtotal=0;
   var subtotal=0;
   var vattotal=0;
   var pur_total=0;
   var totamt=0; 
   var gst=0; 
   var actualgst=0;
   var istp= document.getElementById("istp").checked;
   
   if(istp==true){
        flag=1;
   }
   
   if(finalsalesubmit==0){
	   if(istp==true){
	   		document.getElementById("tpid").value="1";
	   		document.getElementById("paytype").value="Credit";
	   		document.getElementById("paytype").disabled=true;
	   		document.getElementById("received").value=0;
	   		
	   		
	   } else {
	      document.getElementById("tpid").value="0";
	      document.getElementById("paytype").value="Cash";
	   		document.getElementById("paytype").disabled=false;
	   		
	   		
	   }
   }
   
   
   
   $('.pclass').each(function() {
     	
     	var i= this.value;
     	var pstock = Number(document.getElementById("prodd"+i).value);
        var sale=Number(document.getElementById("sale"+i).value);
        
        if(sale<=0){
        	
        	jAlert('error', 'Sale quantity can not be 0 or less.', 'Error Dialog');
		
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			document.getElementById("sale"+i).value = '0';
			 document.getElementById("totalmrp"+i).innerHTML='0';
        }else if(pstock<sale){
        	
        	jAlert('error', 'Sale quantity greater than stock.', 'Error Dialog');
		
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			document.getElementById("sale"+i).value = '0';
			 document.getElementById("totalmrp"+i).innerHTML='0';
			
        }
       else {
        
        
       var mrp=Number(document.getElementById("mrp"+i).value);
       var vat=Number(document.getElementById("vat"+i).value);
       var req=Number(document.getElementById("req"+i).value);
       var pur_price= Number(document.getElementById("pur_price"+i).value);
       var dis_perproduct= Number(document.getElementById("discount"+i).value);
       var pack=document.getElementById("pack"+i).value;
       //var hiddenclinicid=document.getElementById('hiddenclinicid').value;
       var pur_temp= sale* (pur_price/pack);
       pur_total =pur_total+pur_temp;
       
       if(istp==true){
             mrp= roundTwo(pur_price/pack);
             document.getElementById("mrp"+i).value=roundTwo(mrp);
             
       } else {
                 if(flag==1){
                    
                      var sale_price= Number(document.getElementById("sale_price"+i).value);
                  	  document.getElementById("mrp"+i).value=roundTwo(sale_price);
                      mrp=sale_price;
                 }
       }
       
       if(sale>req){
            //document.getElementById("sale"+i).value='0';
            document.getElementById("labelreq"+i).innerHTML=sale;
       }
       
	   var tt=sale*mrp;
	   var disc_amt=tt*dis_perproduct/100;
       tt=tt-disc_amt;
	   var dividevat=100+vat;
	   //var tgst=tt*vat/dividevat;  ////this code commented because gst not calculated properly
	   var tgst=tt*vat/100;
	   gst=gst+tgst;
	   
       subtotal=subtotal+tt;
       var tvat=tt*vat/100;
       vattotal=vattotal+tvat;
       tt=roundTwo(tt);
       document.getElementById("totalmrp"+i).innerHTML=tt;
       
       //Formula:- OC-[OC * {100/(100+GST%)}]
       /*var actualgst1 = tt-[tt*(100/(100+vat))]; 
       actualgst = actualgst+actualgst1;*/
      }
        
  });
  
  if(istp==false){
    
     flag=0;
  }
  var hiddenclinicid=document.getElementById('hiddenclinicid').value;
  subtotal=roundTwo(subtotal);
  gst=roundTwo(gst);
  vattotal=roundTwo(vattotal);
  pur_total=roundTwo(pur_total);
  tvat=roundTwo(tvat);
  
  if(!hiddenclinicid=="saimed" || hiddenclinicid=="infinyte"){   //  //for infinite
	 var dividevat= vattotal/2;
   }else{
	  var dividevat= gst/2;
  }
  //var dividevat= gst/2;
  //for infinite
  //var dividevat= vattotal/2;
  
  var margin= subtotal -  pur_total;
  
  var temptotal=subtotal;
  
  var includegst=subtotal+vattotal;
  
  document.getElementById("subtotal").innerHTML= (subtotal);
  var discsmt=document.getElementById("discsmt").value;
   //document.getElementById("vat").innerHTML=gst;
  // document.getElementById("tvat").value=gst;
   
   document.getElementById("cgst").innerHTML=roundTwo(dividevat);
   document.getElementById("sgst").innerHTML=roundTwo(dividevat);
   document.getElementById("tcgst").value=roundTwo(dividevat);
   document.getElementById("tsgst").value=roundTwo(dividevat);
   
   //document.getElementById("total").innerHTML= roundAbs(temptotal);
   //for infinite
   if(!hiddenclinicid=="saimed" || hiddenclinicid=="infinyte"){ 
	 document.getElementById("total").innerHTML= roundAbs(includegst);
	 document.getElementById("ttotal").value= roundAbs(includegst);
	 document.getElementById("vat").innerHTML=vattotal;
     document.getElementById("tvat").value=vattotal;
	 if(finalsalesubmit==0){
	   document.getElementById("received").value=roundAbs(includegst);
	   document.getElementById("baltotal").innerHTML = roundAbs(includegst);
    }
   
   }else{
	document.getElementById("total").innerHTML= roundAbs(temptotal);
	document.getElementById("ttotal").value= roundAbs(temptotal);
	document.getElementById("vat").innerHTML=gst;
    document.getElementById("tvat").value=gst;
	if(finalsalesubmit==0){
	   document.getElementById("received").value=roundAbs(temptotal);
	   document.getElementById("baltotal").innerHTML = roundAbs(temptotal);
   }
}
   
   //document.getElementById("total").innerHTML= roundAbs(includegst);
   //if(finalsalesubmit==0){
	  // document.getElementById("received").value=roundAbs(temptotal);
	   //document.getElementById("baltotal").innerHTML = roundAbs(temptotal);
  // }
  //for infinite
  //if(finalsalesubmit==0){
	  // document.getElementById("received").value=roundAbs(includegst);
	   //document.getElementById("baltotal").innerHTML = roundAbs(includegst);
   //}
   //document.getElementById("ttotal").value= roundAbs(temptotal);
   //for infinite
   //document.getElementById("ttotal").value= roundAbs(includegst);

  
   document.getElementById("lblSalePrice").innerText=subtotal;
   document.getElementById("lblPurPrice").innerText=pur_total;
   document.getElementById("lblMargin").innerText=roundAbs(margin);
   
   if(document.getElementById("subbtotal")){
  	 document.getElementById("subbtotal").value= roundAbs(subtotal);
   }
   
   if(!hiddenclinicid=="saimed" || hiddenclinicid=="infinyte"){
	 document.getElementById("grosssubtotal").value = roundTwo(includegst);
     document.getElementById("grosstotal").value= roundTwo(includegst);
     document.getElementById("grosstotalspan").innerHTML= roundTwo(includegst);
 }else{
	document.getElementById("grosssubtotal").value = roundTwo(subtotal);
}
     //for infinite
    if(!hiddenclinicid=="saimed" || hiddenclinicid=="infinyte"){
	  if(discsmt>0){
		calDiscount(discsmt);
	} 
	 if(discsmt==0){
       getRemainAmt(roundAbs(includegst));
     }
   }else{
	   calDiscount(discsmt);
   }
 
  
	} catch (e) {
		window.location.reload();	      
	}
}




function roundTwo(val){
 
   val =Math.round(val * 100) / 100;
   //val=Math.floor(val);
   
   return val;
}


function perorrs(){
 
    document.getElementById("discsmt").value=0;
    calDiscount(0); 
}


function calDiscount(val){
	
     try {
     
     var distype=document.getElementById("distype").value;
     var subtotal=parseFloat(document.getElementById("subtotal").innerHTML);
     var baltotal = Number(document.getElementById("baltotal").innerHTML);
     var grosssubtotal = parseFloat(document.getElementById("grosssubtotal").value);
     var max_phar_discount = Number(document.getElementById("max_phar_discount").value);
     var hiddenclinicid=document.getElementById('hiddenclinicid').value;
     var btemp=0;
     var temp=0;
     var discinper = 0;
     var gst=0;
     var dummysubtotal=0;
     var discperbyval=Number(val);
     if(distype=='0'){
         //%
    	 if(discperbyval>100){
    	      	jAlert('error', 'Discount exceed above 100% .', 'Error Dialog');
    	      		setTimeout(function() {
    	      			$("#popup_container").remove();
    	      			removeAlertCss();
    	      		}, alertmsgduration);
    	      		val=0;
    	      		document.getElementById("discsmt").value=0;
    	 }else if(discperbyval<0){
 	      	jAlert('error', 'Discount can not less than 0.', 'Error Dialog');
      		setTimeout(function() {
      			$("#popup_container").remove();
      			removeAlertCss();
      		}, alertmsgduration);
      		val=0;
      		document.getElementById("discsmt").value=0;
    	 }else if(discperbyval>10 && max_phar_discount==0){
 	      	jAlert('error', 'Discount exceed above 10% .', 'Error Dialog');
      		setTimeout(function() {
      			$("#popup_container").remove();
      			removeAlertCss();
      		}, alertmsgduration);
      		val=0;
      		document.getElementById("discsmt").value=0;
    	 }else{
			 temp=subtotal*val/100;
			 discinper =val;
    	 }
     } else {
        //rs
		temp=val;	        
     }
     if(distype=='1'){
         //rs to %
    	 discinper = (val/subtotal)*100;
    	 if(discperbyval>subtotal){
    		 jAlert('error', 'Amount exceed above Total .', 'Error Dialog');
    		 setTimeout(function() {
   			$("#popup_container").remove();
   			removeAlertCss();
    		 }, alertmsgduration);
    		 val=0;
    		 document.getElementById("discsmt").value=0;
    		 discinper=0;
    		 temp=0;
    	 }else if(discperbyval<0){
    			jAlert('error', 'Discount can not less than 0.', 'Error Dialog');
          		setTimeout(function() {
          			$("#popup_container").remove();
          			removeAlertCss();
          		}, alertmsgduration);
    		 val=0;
    		 document.getElementById("discsmt").value=0;
    		 discinper=0;
    		 temp=0;
    	 }else if(discinper<0){
 				jAlert('error', 'Discount can not less than 0.', 'Error Dialog');
 				setTimeout(function() {
 					$("#popup_container").remove();
 					removeAlertCss();
 				}, alertmsgduration);
			 val=0;
			 document.getElementById("discsmt").value=0;
			 discinper=0;
			 temp=0;
    	 }else if(discinper>10 && max_phar_discount==0){
 				jAlert('error', 'Discount exceed above 10%', 'Error Dialog');
 				setTimeout(function() {
 					$("#popup_container").remove();
 					removeAlertCss();
 				}, alertmsgduration);
			 val=0;
			 document.getElementById("discsmt").value=0;
			 discinper=0;
			 temp=0;
    	 }else{
    		 discinper = (val/subtotal)*100;
   		}
   	}
     discinper=roundTwo(discinper);
     document.getElementById("discinper").value =discinper;
     var tempvatss = temp;
     temp=roundTwo(temp);
     document.getElementById("tdisc").innerHTML=temp;
    
	   var total=grosssubtotal-tempvatss;
       var grosstotal = grosssubtotal-tempvatss;
       grosstotal = roundTwo(grosstotal);
       total=roundAbs(total);

     //var total=grosssubtotal-tempvatss;
     //var grosstotal = grosssubtotal-tempvatss;
     //grosstotal = roundTwo(grosstotal);
     //total=roundAbs(total);
     /*total=roundAbs(total);*/
     document.getElementById("total").innerHTML=total;
     document.getElementById("tdiscount").value=temp;
     document.getElementById("ttotal").value=total;
     if(finalsalesubmit==0){
    	 document.getElementById("received").value=total; 
    	 document.getElementById("baltotal").innerHTML=total;
     }
    
    
     if(document.getElementById("subbtotal")){
    	 document.getElementById("subbtotal").value=subtotal;
     }
     document.getElementById("grosstotal").value= grosstotal;
     document.getElementById("grosstotalspan").innerHTML= grosstotal;
     //var gst=0;
     var pur_total=0;
     var istp= document.getElementById("istp").checked;
     var flag=0;
     if(istp==true){
          flag=1;
     }
     
     $('.pclass').each(function() {
      	var i= this.value;
      	var pstock = Number(document.getElementById("prodd"+i).value);
        var sale=Number(document.getElementById("sale"+i).value);
         
        if(sale<=0){
         	jAlert('error', 'Sale quantity can not be 0 or less.', 'Error Dialog');
         		setTimeout(function() {
         			$("#popup_container").remove();
         			removeAlertCss();
         		}, alertmsgduration);
         		document.getElementById("sale"+i).value = '0';
        }else if(pstock<sale){
         	jAlert('error', 'Sale quantity greater than stock.', 'Error Dialog');
 				setTimeout(function() {
 					$("#popup_container").remove();
 					removeAlertCss();
 				}, alertmsgduration);
 				document.getElementById("sale"+i).value = '0';
 		}else {
        	var mrp=Number(document.getElementById("mrp"+i).value);
        	var vat=Number(document.getElementById("vat"+i).value);
        	var req=Number(document.getElementById("req"+i).value);
        	var pur_price= Number(document.getElementById("pur_price"+i).value);
        	var pack=document.getElementById("pack"+i).value;
        	var dis_perproduct= Number(document.getElementById("discount"+i).value);
        	if(pack==''){
        		pack ='1';
        	}
        	if(pack=='0'){
        		pack='1';
        	}
        	var pur_temp= sale* (pur_price/pack);
        	pur_total =pur_total+pur_temp;
        	if(istp==true){
        		mrp= roundTwo(pur_price/pack);
        		document.getElementById("mrp"+i).value=roundTwo(mrp);
        	} else {
                  if(flag==1){
                      var sale_price= Number(document.getElementById("sale_price"+i).value);
                   	  document.getElementById("mrp"+i).value=roundTwo(sale_price);
                      mrp=sale_price;
                  }
        	}
	        if(sale>req){
	             document.getElementById("labelreq"+i).innerHTML=sale;
	       	}
	        
	 	   	var temptt=sale*mrp;
	 	   	var disc_amt=temptt*dis_perproduct/100;
	 	   	temptt=temptt-disc_amt;
	 	   	dummysubtotal=dummysubtotal+temptt;
	 	   	var tt=0;
	 	   	var tempvat=0;
	 	   	if(distype=='0'){
	 	   		tempvat=temptt*discinper/100;
	 	   	}else{
	 	   		tempvat=temptt*discinper/100;
	 	   	}
	 	   	tt = temptt - tempvat;
	 	   	if((!hiddenclinicid=="saimed" || hiddenclinicid=="infinyte")){
		       
	 	   	   var tgst=tt*vat/100;
	 	   	   gst=gst+tgst;
	        }else{
		       var dividevat=100+vat;
	 	   	   //var tgst=tt*vat/dividevat;  //this code commented because gst not calculated properly
	 	   	   var tgst=tt*vat/100;
	 	   	   gst=gst+tgst;
	       }  
	 	   
 	   
                
	        //Formula:- OC-[OC * {100/(100+GST%)}]
	        /*var actualgst1 = tt-[tt*(100/(100+vat))]; 
	        actualgst = actualgst+actualgst1;*/
       }
     });
     
     gst=roundTwo(gst);
     var dividevat= gst/2;
      gst=roundTwo(gst);
     var dividevat= gst/2;
     subtotal=subtotal-tempvatss;
     if(val==0 || val==''){
	    var includegst=dummysubtotal+gst;
	 }else{
		var includegst=subtotal+gst;
	}
     //var includegst=subtotal+gst;
     if(!hiddenclinicid=="saimed" || hiddenclinicid=="infinyte"){
	    document.getElementById("grosstotal").value= roundTwo(includegst);
        document.getElementById("grosstotalspan").innerHTML= roundTwo(includegst);
        document.getElementById("total").innerHTML= roundAbs(includegst);
        document.getElementById("received").value= roundAbs(includegst);
        document.getElementById("baltotal").innerHTML= roundAbs(includegst);
        document.getElementById("ttotal").value=roundAbs(includegst);
        if(val==0 || val==''){
	     document.getElementById("subtotal").innerHTML=dummysubtotal;
        }else{
	     document.getElementById("subtotal").innerHTML=subtotal;
        } 
         //document.getElementById("subtotal").innerHTML=subtotal;
	 }
     document.getElementById("vat").innerHTML=gst;
     document.getElementById("tvat").value=gst;
     
     document.getElementById("cgst").innerHTML=roundTwo(dividevat);
     document.getElementById("sgst").innerHTML=roundTwo(dividevat);
     document.getElementById("tcgst").value=roundTwo(dividevat);
     document.getElementById("tsgst").value=roundTwo(dividevat);
     
      if(!hiddenclinicid=="saimed" || hiddenclinicid=="infinyte"){
	      getRemainAmt(roundAbs(includegst));
	  }else{
		
		getRemainAmt(total);
	}
     
     //getRemainAmt(total);
	} catch (e) {
		window.location.reload();	      
	}
}
     
    


var cell;
var row;
var counts=0;

/*function addnewRow(tableID){

      counts= Number(document.getElementById("tempcount").value);
  
     var qty=document.getElementById("qty").value;
     var newmid=document.getElementById("newmedicine").value;
     var fullname=document.getElementById("fullname").value;
     tmid=newmid;
     if(newmid=='0'){
                jAlert('error', "please select product!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
	 } else if(qty==''){
            jAlert('error', "please enter required quantity!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
     } else if(fullname==''){
	     jAlert('error', "Please enter fullname!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			isError=true;		
     }else{
  		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		row=table.insertRow(rowCount);
		var hdnphclientid = document.getElementById("hdnphclientid").value;
		var hdnispharmacy = document.getElementById("hdnispharmacy").value;
		var url="adnewmedicinePharmacy?pid="+newmid+"&count="+counts+"&qty="+qty+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";
		document.getElementById("qty").value = '';
	      if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = updateAccountinvoiceRequest;
		    req.open("GET", url, true); 
		    req.send(null);
     } 
     

}*/

/*var tmid=0;

function addBarcodeRequest(pid){
    
           if(pid!=''){
                   
            var table = document.getElementById('mytable');
			var rowCount = table.rows.length;
			row=table.insertRow(rowCount);
			counts = rowCount - 1;	
			cell=row.insertCell(0);
    
    		var url="adnewmedicinePharmacy?pid="+pid+"&count="+counts+"&qty=1";
    		document.getElementById("qty").value = '';

              if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = updateAccountinvoiceRequest;
			    req.open("GET", url, true); 
			    req.send(null);
                   
           }
           document.getElementById("barcode").value = "";

}



function updateAccountinvoiceRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
             
             		var dd=req.responseText;
             		if(dd=="0"){
             		 	 
						jAlert('error', "Barcode not generated!", 'Error Dialog');
				
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 		             		 	     
             		 	
             		} else {
             		       var flag=false;
             				$('.pclass').each(function() {
        						var i=this.value;
        						var tid= document.getElementById("product_id"+i).value;
        						if(tid==tmid){
        						    flag=true;
        						}
        					});	
             				if(flag){
             				 
             				 		jAlert('error', "This product already added!", 'Error Dialog');
				
									setTimeout(function() {
										$("#popup_container").remove();	
										removeAlertCss();
									}, alertmsgduration);   
             				 
             				} else {
             				
             				        row.innerHTML=dd;
		             				counts++;
								 	document.getElementById("tempcount").value=counts;	  
				                    calsubTotal();
				                    
             				}
             				
             				
             		}
              }
         }
  }
*/


function deleteRow(r){
   
        var i = r.parentNode.parentNode.rowIndex;
    	document.getElementById("mytable").deleteRow(i);
    	
    	calsubTotal();
    
}

function deleteRowForEdit(r,id){
	   
	var deletedcharges= document.getElementById("deletedcharges").value;
	deletedcharges=deletedcharges+","+id; 
	document.getElementById("deletedcharges").value=deletedcharges;
    var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("mytable").deleteRow(i);
	
	calsubTotal();

}


function saveqty(qty){
  
	 document.getElementById("tdbutton"+qty).innerHTML="<a href='#' onclick='deleteRow(this)'><i class='fa fa-minus-circle' style='color:#c50404;font-size: 17px;padding-top: 2px;' aria-hidden='true'></i></a>";
    	 	
}


function credit(paymode){

	document.getElementById("paymode").value=paymode;
	var total=document.getElementById("tempcount").value;
	var flag=true;
	
	for(var i=0;i<total;i++){

		var sale=document.getElementById("sale"+i).value;		   
	     if(sale==''){
	            
	            flag=false;
                 jAlert('error', "Please enter sale quantity!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
	     }  
	} 
  
 	if(flag){
 	   document.getElementById("creditform").submit();
 	}
 
     
}

function recordcash(paymode){

	document.getElementById("paymode").value=paymode;
	var total=document.getElementById("tempcount").value;
	var flag=true;
	
	$('.pclass').each(function() {
	   
	    var i=this.value; 

		var sale=document.getElementById("sale"+i).value;
		var avail=document.getElementById("prodd"+i).value; 
		
		 if(avail=="0"){
		     
		         flag=false;
                 jAlert('error', "Stock is not available!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
		 }
	     else if(sale=='0'){
	            
	            flag=false;
                 jAlert('error', "Please enter sale quantity!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
	     }  
	}); 
  
 	if(flag){
 	    
 	   document.getElementById("creditform").action="recordcashPharmacy";
 	   document.getElementById("creditform").submit();
 	}
     
}


function reorderbill(paymode){

	document.getElementById("paymode").value=paymode;
	var total=document.getElementById("tempcount").value;
	var flag=true;
	
	$('.pclass').each(function() {

        var i=this.value;
		var sale=document.getElementById("sale"+i).value;		   
	     if(sale==''){
	            
	            flag=false;
                 jAlert('error', "Please enter sale quantity!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
	     }  
	}); 
  
 	if(flag){
 	    
 	   document.getElementById("creditform").action="reorderbillPharmacy";
 	   document.getElementById("creditform").submit();
 	}

}




function showInnerDivPharmacy(hiddenDetailsDiv,invoiceId){
	invoiceId1 = invoiceId;
	if(document.getElementById(hiddenDetailsDiv).style.display == ""){
		document.getElementById(hiddenDetailsDiv).style.display = "none";
	}
	else{
	document.getElementById(hiddenDetailsDiv).style.display = ""; 
	
	var url = "showallpharmacyssessmentProcessingAccount?invoiceId="+invoiceId+"";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showInnerDivPharmacyRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function showInnerDivPharmacyRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("hiddenDetailsDiv1"+invoiceId1+"").style.display = ""; 
			document.getElementById("hiddenDetailsDiv1"+invoiceId1+"").innerHTML = req.responseText;
			
         }
	}

}


var finalsalesubmit=0;
function newsale(){
	
	
	
      var paymode=document.getElementById("paytype").value; 
      var hiddenclinicid=document.getElementById('hiddenclinicid').value;
	  var fullname=document.getElementById("fullname").value;
	  var mobile=document.getElementById("mobile").value;
	  var doctor=document.getElementById("doctor").value;
	  document.getElementById("paymode").value=paymode;
	  var phar_practid = document.getElementById("phar_practid").value;
	  var isError=false;
	  
	  if(paymode=="Cheque"){
	    
	       var chequeno=document.getElementById("chequeno").value;
	       if(chequeno.length<4){
	             jAlert('error', "Please enter cheque number!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 isError=true;		
			 document.getElementById("chequeno").focus();
	       }
	  }
	  if(paymode=="Card"){
	    
	       var card=document.getElementById("card").value;
	       if(card==""){
	             jAlert('error', "Please enter card number!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					document.getElementById("card").focus();
			 isError=true;		
	       }
	  }
	  if(paymode=="NEFT" || paymode=="Online Payment"){
	    
	       var card=document.getElementById("neftid").value;
	       if(card==""){
	             jAlert('error', "Please enter transaction id!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 isError=true;
			 document.getElementById("neftid").focus();		
	       }
	  }
	  		  
	  
	  if(fullname==''){
		  			jAlert('error', "Please enter fullname!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 isError=true;		
	  }

if(hiddenclinicid !="saimed" && hiddenclinicid !="infinyte"){
	  if(phar_practid==''){
			jAlert('error', "Please select doctor!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			
			isError=true;		
	  }
	  
	  if(phar_practid=='0'){
			jAlert('error', "Please select doctor!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			
			isError=true;		
	  }
	  
	  if(doctor=='null'){
			jAlert('error', "Please select doctor!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			
			isError=true;		
	  }
	  
	}  
	 /* else if(mobile==''){
	     jAlert('error', "please enter mobile!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 isError=true;		
	  }*/
	 /* else if(doctor==''){
	     jAlert('error', "please enter doctor!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 isError=true;		
	 }
*/

       if(!isError){
       
           var total=document.getElementById("tempcount").value;
		   var flag=true;
	
			$('.pclass').each(function() {
			    
                    var i=this.value;
					var sale=document.getElementById("sale"+i).value;
					var avail=document.getElementById("prodd"+i).value; 
		
		 			if(avail=="0"){
		     
		         		flag=false;
                 		jAlert('error', "Stock is not available!", 'Error Dialog');
				
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
		 			}
	     			else if(sale=='0'){
	            
	            		flag=false;
                 		jAlert('error', "Please enter sale quantity!", 'Error Dialog');
				
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
							}, alertmsgduration); 
	     			}  
			}); 
         
			if(flag){
				
			     var total= parseFloat(document.getElementById("ttotal").value);
			     var rec= parseFloat(document.getElementById("received").value);
			     if(rec>total){
			        document.getElementById("received").value=total;
			     }
			    
			    if(paymode=="Estimate"){
			    	document.getElementById("creditform").target="";
			        document.getElementById("creditform").action="estimatePharmacy";
			    	document.getElementById("creditform").submit();
			    } else {
			       var creditlimitflag= false;
                   if(paymode=="Credit"){
                        document.getElementById("received").value=0;
                        if(document.getElementById("creditbalaccess")){
                        	var creditbalaccess = document.getElementById("creditbalaccess").value;
                        	if(creditbalaccess=='1'){
                        		var creditprebalance = parseFloat(document.getElementById("creditprebalance").value);
                        		var creditlimit = parseFloat(document.getElementById("creditlimit").value);
                                if(creditprebalance>creditlimit){
                                	 jAlert('error', "Credit limit crossed!", 'Error Dialog');
                         		 	setTimeout(function() {
                         		 		$("#popup_container").remove();
                         		 		removeAlertCss();
                         		 }, alertmsgduration); 
                                	creditlimitflag = true;
                                }else{
                                	var totalll=parseFloat(document.getElementById("total").innerHTML);
                                	creditprebalance = creditprebalance+totalll;
                                	if(creditprebalance>creditlimit){
                                		 jAlert('error', "Credit limit crossed!", 'Error Dialog');
                                		 	setTimeout(function() {
                                		 		$("#popup_container").remove();
                                		 		removeAlertCss();
                                		 }, alertmsgduration); 
                                	}
                                }
                        	}
                        }
                        
                        getRemainAmt(0);
                   }
                   
                   if(!creditlimitflag){
                	   var flagcheck=true;
                	   $('.pclass').each(function() {
                		   flagcheck = false;
                	   }); 
                	   if(!flagcheck){
                		   finalsalesubmit=1;
                		   addtempsaledata();
                		  /* var n=document.getElementById("total").innerHTML;
                    	   var discnt =document.getElementById("tdiscount").value;
                           document.getElementById("npopup").innerHTML=n;
                           document.getElementById("paymodepopup").innerHTML=paymode;
                           document.getElementById("discountconfirm").innerHTML=discnt;
                           $('#deletedisc').modal( "show" );	*/	
                	   }else{
                		   jAlert('error', "Please select at least one product!", 'Error Dialog');
               		 	setTimeout(function() {
               		 		$("#popup_container").remove();
               		 		removeAlertCss();
               		 }, alertmsgduration); 
                	   }
                	  	
                   }
                 
                  			    
			      /* document.getElementById("creditform").submit();*/
			    }
				
			}                
       }
}

function newEditSale() {
	
	 var ff= document.getElementById('notes').value;
	 if(ff==''){

		 jAlert('error', "Please enter notes:!", 'Error Dialog');
		
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		 
		  
	 } else {
		 
		 newsale();
	 }
	 
}



function addpharmacybill(val){

      document.getElementById("billno").value=val;
      document.getElementById("category_form").action="addmedicinebillAccounts"; 						
	  document.getElementById("category_form").submit();	
}

	
function requestStock(pid,index){

    var re=document.getElementById("req"+index).value; 
    var url="requeststockPharmacy?pid="+pid+"&quantity="+re+"";

    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = requestStockRequest;
    req.open("GET", url, true); 
              
    req.send(null);
 }


function requestStockRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
				jAlert('success', 'Stock requested!', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
         }
	}

}

function calRefundTotal(){
       
       	   var totamt=Number(document.getElementById("subtotal").innerText);	
           var total=document.getElementById("tempcount").value;
           var vat=Number(document.getElementById("tvat").value);
           
  		   var refund=0;
  			$('.pclass').each(function() {
			    
                    var i=this.value;
					
					var previqty=Number(document.getElementById("tempsale"+i).value);
					var returnqty=Number(document.getElementById("returnqty"+i).value);    
					if(returnqty>previqty){
					    return false;
					}       
 		    		var mrp=document.getElementById("mrp"+i).value;
 		    		var tot=mrp*returnqty;
 		   			refund=refund+tot; 									      			
      		});  
  			//calsubTotal();
  			var nowtot=totamt-refund;
  			nowtot=roundTwo(nowtot+vat);
  			
      		document.getElementById("refund").innerText=refund;
      		document.getElementById("ttotal").value=nowtot;
      		document.getElementById("total").innerText=nowtot;
}		
	
function refundMedicine() {

    var returnmode= document.getElementById("returnmode").value;
    document.getElementById("paymode").value=returnmode;
	var total=document.getElementById("tempcount").value;
	var flag=true;
	var returnproductaddflag=false;
	if(returnmode!="0"){
	
	
	      if(returnmode=="Cheque"){
	    
	       var chequeno=document.getElementById("chequeno").value;
	       if(chequeno.length<4){
	             jAlert('error', "Please enter cheque number!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 flag=false;		
			 document.getElementById("chequeno").focus();
	       }
	  }
	  if(returnmode=="Card"){
	    
	       var card=document.getElementById("card").value;
	       if(card==""){
	             jAlert('error', "Please enter card number!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					document.getElementById("card").focus();
			 flag=false;		
	       }
	  }
	  if(returnmode=="NEFT"){
	    
	       var card=document.getElementById("neftid").value;
	       if(card==""){
	             jAlert('error', "Please enter neft transaction id!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 flag=false;
			 document.getElementById("neftid").focus();		
	       }
	  }
	
			$('.pclass').each(function() {
				returnproductaddflag =true;
		        var i=this.value;
				var sale=document.getElementById("returnqty"+i).value;		   
			     if(sale=='' || sale=='0'){
			            
			            flag=false;
		                 jAlert('error', "Please enter return quantity!", 'Error Dialog');
						
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
			     }else if(Number(sale)<0){
			    	 flag=false;
	                 jAlert('error', "Please enter return quantity!", 'Error Dialog');
					
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
			     }
			}); 
  
		 	if(flag){
		 	    if(returnproductaddflag){
		 	    	var ttotal=document.getElementById("ttotal").value;
		 	    	 document.getElementById("npopup").innerHTML=ttotal;
	                 document.getElementById("paymodepopup").innerHTML=returnmode;
	                 $('#confirmreturnpopup').modal( "show" );	
		 	    		/*document.getElementById("creditform").action="refundmedicinePharmacy";
		 	    		document.getElementById("creditform").submit();*/
		 	    }else{
		 	    	 jAlert('error', "Please select at least one product!", 'Error Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
		 	    }
		 	   
		 	}
	
  } else {
  
       jAlert('error', "Please select Paymode!", 'Error Dialog');
						
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
  
  }

}	
function checkSingleReturnmOde() {
	var npopup = Number(document.getElementById("npopup").innerHTML);
	 
	 if(npopup<=0){
		 
	 }else{
		 $("#dashboardloaderPopup").modal('show');
		 document.getElementById("creditform").action="refundmedicinePharmacy";
  		document.getElementById("creditform").submit();
	 }
	
}

var finalsalereturn=0;
function refundMedicineNew() {

    var returnmode= document.getElementById("returnmode").value;
    document.getElementById("paymode").value=returnmode;
	var total=document.getElementById("tempcount").value;
	var flag=true;
	
	if(returnmode!="0"){
	
	
	      if(returnmode=="Cheque"){
	    
	       var chequeno=document.getElementById("chequeno").value;
	       if(chequeno.length<4){
	             jAlert('error', "Please enter cheque number!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 flag=false;		
			 document.getElementById("chequeno").focus();
	       }
	  }
	  if(returnmode=="Card"){
	    
	       var card=document.getElementById("card").value;
	       if(card==""){
	             jAlert('error', "Please enter card number!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					document.getElementById("card").focus();
			 flag=false;		
	       }
	  }
	  if(returnmode=="NEFT"){
	    
	       var card=document.getElementById("neftid").value;
	       if(card==""){
	             jAlert('error', "Please enter neft transaction id!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 flag=false;
			 document.getElementById("neftid").focus();		
	       }
	  }
	  		var flagtests =false;
			$('.pclass').each(function() {
				flagtests = true;
		        var i=this.value;
				var sale=document.getElementById("returnqty"+i).value;		   
			     if(sale==''){
			            
			            flag=false;
		                 jAlert('error', "Please enter return quantity!", 'Error Dialog');
						
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
			     }  
			}); 
			if(flagtests){
				if(flag){
					finalsalereturn =1;
					addtempreturndata();
					
			 		/*if(document.getElementById('returnbtn')){
				 		  document.getElementById('returnbtn').style.visibility='hidden';
			 		}
			 		document.getElementById("creditform").action="refundmedicinenewPharmacy";
			 		document.getElementById("creditform").submit();*/
//			 	   	document.getElementById("returntn").className=hidden;
			 	   
			 	  
			 	}else{
			 		return flag;
			 	}
			}else{
				 jAlert('error', "Please select at least one return product!", 'Error Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					flag=false;
					return flag;
			}
		 	
	
  } else {
  
       jAlert('error', "Please select Paymode!", 'Error Dialog');
						
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
							 flag=false;
							 return flag;
  }

}	


	
	
	function openHistory(clientid,flag) {
	
	 		  var fromdate=document.getElementById("fromdate").value;
	 		  var todate=document.getElementById("todate").value;
	 		  $('#dashboardloaderPopup').modal( "show" );
	 		     	
	 		  var url="historyPharmacy?clientid="+clientid+"&flag="+flag+"&fromdate="+fromdate+"&todate="+todate+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = openHistoryRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	
	
	function openHistoryRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				var str=data.split("~");
				document.getElementById("hiddentitle").innerHTML=str[0];
				//document.getElementById("maintitle").innerHTML=str[0];
				document.getElementById("history").innerHTML=str[1];
				document.getElementById("billanddata").innerHTML=str[2];
				document.getElementById("letterHead").innerHTML=str[3];      
				$('#dashboardloaderPopup').modal( "hide" );
				$('#historymodal').modal( "show" );
		
         }
	}

}


 function setAllProduct(i) {

 			var val = document.getElementById("vendor"+i).value;
			document.getElementById("vendorid").value=val;
	       
			if(val=="0"){
					jAlert('error', "Please select supplier!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
						      
			
			}else { 
			
							   var total=0;
							   $('.case').each(function() { // loop through each checkbox
									if (this.checked == true) {
										total = total + ',' + this.value;
									}
								});     
							   
							    var url="addtostockPharmacy?totalcheck="+total+"";
							   
							     if (window.XMLHttpRequest) {
												req = new XMLHttpRequest();
										  }
										  else if (window.ActiveXObject) {
											isIE = true;
											req = new ActiveXObject("Microsoft.XMLHTTP");
										}   
							               
							   			 req.onreadystatechange = setAllProductRequest;
							   			 req.open("GET", url, true); 
							              
							   	 req.send(null);
			
			}        
   
  
 }

 function setAllProductRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				document.getElementById("alllist").innerHTML=req.responseText;
		
         }
	}

}


function calAll(val) {

 	var total=0;
    var count=Number(document.getElementById("tcount").value);
    var qty=Number(val);
    for(var i=1;i<=count;i++){
         
			var mrp=Number(document.getElementById("mrp"+i).value);            
            var sum=mrp*qty;
            document.getElementById("subtot"+i).innerHTML=sum;
     	    total=total+sum;
    }
    
    document.getElementById("sumall").innerHTML=total;

}
		
function createpo(){

    document.getElementById("poform").submit();
}	
	
function viewdoctorBill(doctorid,fullname) {

    var fromdate=document.getElementById("fromdate").value;
    var todate=document.getElementById("todate").value;
    $('#dashboardloaderPopup').modal( "show" );
    var url="viewdoctorbillPharmacy?doctorid="+doctorid+"&fromdate="+fromdate+"&todate="+todate+"&fullname="+fullname+"";
      if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = viewdoctorBillRequest;
   			 req.open("GET", url, true); 
              
   	 req.send(null);
     
	
}	

function viewdoctorBillRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var str=req.responseText;
				var data=str.split("~");
				document.getElementById("modaltitle").innerHTML=data[0];
 				document.getElementById("modelprint").innerHTML=data[0];
				document.getElementById("allbilllist").innerHTML=data[1];
				$('#dashboardloaderPopup').modal( "hide" );
				$('#history').modal( "show" );
		
         }
	}

}


function getRemainAmt(val) {
	try {
    //var total=document.getElementById("total").innerHTML;
    //var t=val-total; 
	var reciamttest = document.getElementById("received").value;
	var paymode=document.getElementById("paytype").value; 
	if(reciamttest=='' || reciamttest==' ' || reciamttest=='   ' || reciamttest=='    '){
		document.getElementById("received").value=0;
		//val=0;
		jAlert('error', "Please enter received amount!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}
	if(paymode=="Credit"){
		val=0;
	}
  	var total=Number(document.getElementById("baltotal").innerHTML);
    var t=val-total;
    
    var ret=total-val;
    if(ret<0){
       ret=0;
    }
    if(t<0){
      t=0;
    }
    document.getElementById("returnamt").value= roundAbs(t) ;
    if(finalsalesubmit==0){
    	document.getElementById("balance").value= ret;
    }
    
  	
  	var prebal=parseFloat(document.getElementById("prebalance").value);
  	prebal=prebal+ret;
  	document.getElementById("rebalance").innerHTML= roundAbs(prebal);
  	
  	if(finalsalesubmit==1){
  		finalsalesubmit=0;
  		var paymodenew=document.getElementById("paytype").value; 
  		var n=document.getElementById("total").innerHTML;
  		var discnt =document.getElementById("tdiscount").value;
        document.getElementById("npopup").innerHTML=n;
        document.getElementById("paymodepopup").innerHTML=paymodenew;
        document.getElementById("discountconfirm").innerHTML=discnt;
        $('#deletedisc').modal( "show" );	
  	}
	} catch (e) {
		window.location.reload();	      
	}
  	
}


function posalelist(){

		                      var total=0;
							   $('.case').each(function() { // loop through each checkbox
									if (this.checked == true) {
										total = total + ',' + this.value;
									}
								});  
		 
          if(total==0){
             	jAlert('error', "Please select one product at least!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
               
          } else {
             
                document.getElementById("allpolist").value=total;
                document.getElementById("plistform").submit();
                  
          }
}

function addNewuser(tableId){
		
			var table = document.getElementById(tableId);
			var rowCount = table.rows.length;
			row=table.insertRow(rowCount);
			var counts = rowCount - 1;	
    
    		var url="newuserPharmacy?count="+counts+"";

            if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			               
			req.onreadystatechange = addNewuserRequest;
			req.open("GET", url, true); 
			req.send(null);	
}

function addNewuserRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
					row.innerHTML=req.responseText;
         	}
		}
}

var userindex ='';
function validatepharmacyuser(indx){
		userindex = indx;
		var userid= document.getElementById("userid"+indx).value;
		var url="validatepharmacyuserPharmacy?userid="+userid+"";
        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = validatepharmacyuserRequest;
		req.open("GET", url, true); 
		req.send(null);	
}

function validatepharmacyuserRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
					var data = req.responseText;
					if(data=='1'){
						jAlert('error', "Userid already present!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					}else{
						saveuser(userindex);
					}
					
         	}
		}
}


function saveuser(indx){
		var fullname=document.getElementById("fullname"+indx).value;
		var phone=document.getElementById("phone"+indx).value;
		var userid= document.getElementById("userid"+indx).value;
		var password= document.getElementById("password"+indx).value;
		var location= document.getElementById("location"+indx).value;
		var stateid= document.getElementById("state"+indx).value;
		var decimal=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
		if(password.match(decimal)){
			var url="saveuserPharmacy?fullname="+fullname+"&phone="+phone+"&userid="+userid+"&password="+password+"&location="+location+"&state="+stateid+"";
       		 if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			req.onreadystatechange = saveuserRequest;
			req.open("GET", url, true); 
			req.send(null);	
		}else{
			jAlert('error', "Password is week!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
		}
    	
}

function saveuserRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
					window.location.reload();
         	}
		}
}



function showCard(){

   		document.getElementById("divcard").className="";
       
}


//user setting

function setLoginAccesss(id,status){
if(status==0){
	status = 1;
}else{
	status = 0;
}
	  
	   var url="loginaccessPharmacy?id="+id+"&status="+status+" ";

            if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			               
			req.onreadystatechange = setLoginAccesssRequest;
			req.open("GET", url, true); 
			req.send(null);	
}
function setLoginAccesssRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
					window.location.reload();
         	}
		}
}

function setaccessseting(id){
	 var url="accviewPharmacy?id="+id+"&status="+status+" ";

            if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			               
			req.onreadystatechange = setaccesssetingRequest;
			req.open("GET", url, true); 
			req.send(null);	

}

function setaccesssetingRequest(){

	if (req.readyState == 4) {
			if (req.status == 200) {
				//	window.location.reload();
         	}
		}
}


function checkUserIdExist(userId){
	var id = "1";
	
	var url = "checkUserIdExistClinicRegistration?userId="+userId+"&selectedid="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkUserIdExistRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkUserIdExistRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					jAlert('error', "UserId exists please use another!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
				}
	         }
		}
	}

 function openbillHistory(userid) {
	
	 		  var fromdate=document.getElementById("fromdate").value;
	 		  var todate=document.getElementById("todate").value;
	 		  $('#dashboardloaderPopup').modal( "show" );
	 		     	
	 		  var url="userprofilebillhistoryPharmacy?userid="+userid+"&fromdate="+fromdate+"&todate="+todate+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = openbillHistoryRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	
	
	function openbillHistoryRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				var str=data.split("~");
				document.getElementById("test").innerHTML=str[2];   
				document.getElementById("maintitle").innerHTML=str[0];
				document.getElementById("history").innerHTML=str[1];
				$('#dashboardloaderPopup').modal( "hide" );      
				$('#viewbill').modal( "show" );
		
         }
	}
}
 
function selectExternalPatient(id) {
	
	 		  var url="selectExternalPatientPharmacy?id="+id+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = selectExternalPatientRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	

var pclientid= 0;	
	
	function selectExternalPatientRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				var str=data.split("~");
				pclientid=str[0];
				document.getElementById("extpid").value = str[0];
				document.getElementById("fullname").value = str[1];
				document.getElementById("mobile").value = str[4];
				document.getElementById("wardname").value = str[2];
				document.getElementById("doctor").value = str[3];
				document.getElementById("rebalance").innerHTML = str[5];
				document.getElementById("prebalance").value = str[5];
         }
	}
}

function getprebaltotal(val) {
    var total=document.getElementById("total").innerHTML;
    var t=val-total; 
  	document.getElementById("returnamt").value= t;
}

function addbalttl(){
	var total=document.getElementById("total").innerHTML; 
	var prebal = document.getElementById("prebalance").value ;
	var totalamt = parseFloat(total)+parseFloat(prebal);
	document.getElementById("baltotal").value= totalamt;
}

function clearbaldb() {
	
	if (confirm('Are you sure you want to clear balance?')) {
    	var extpid = document.getElementById("extpid").value;	
		var url="clearbalfrmdbPharmacy?extpid="+extpid+"";   	
	 		     	
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
      	req.onreadystatechange = clearbaldbRequest;
   	  	req.open("GET", url, true); 
      	req.send(null);
    } 
	}
	
	
	function clearbaldbRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    	var total=document.getElementById("total").innerHTML; 
				var prebal = document.getElementById("rebalance").innerHTML ;
				var totalamt = parseFloat(total)+parseFloat(prebal);
				document.getElementById("baltotal").innerHTML = totalamt;
				var data=req.responseText;
				document.getElementById("baldiv").innerHTML = data;
			}
	}
}


function clearpatientbaldb() {
	
	   var d=window.confirm("Are you sure you want to clear balance?"); 
	   if(d){
	       var opdid = document.getElementById("opdid").value;	
		var url="clearpatientbaldbPharmacy?opdid="+opdid+"";   	
	 		     	
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
      	req.onreadystatechange = clearpatientbaldbRequest;
   	  	req.open("GET", url, true); 
      	req.send(null);
	      
	   }
	
	}
	
	
	function clearpatientbaldbRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    	var total=document.getElementById("total").innerHTML; 
				var prebal = Number(document.getElementById("rebalance").innerHTML);
				var totalamt = parseFloat(total)+parseFloat(prebal);
				document.getElementById("baltotal").innerHTML = totalamt;
				var data=req.responseText;
				document.getElementById("baldiv").innerHTML = data;
				
				total=document.getElementById("ttotal").value;
				var net=total-prebal;
				document.getElementById("total").innerHTML=net;
				document.getElementById("ttotal").value=net; 
				
			}
	}
}

function deletepharmacyuser(id) {
   var r = confirm("Are You Sure!");
	if (r == true) {
   		 var url ="deletepharmacyuserPharmacy?id="+id+"";
   			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
   			 req.onreadystatechange = deletepharmacyuserRequest;
    		req.open("GET", url, true); 
    		req.send(null);
	}
 }
 function deletepharmacyuserRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
						window.location = 'pharmacysettingPharmacy';
		}
	}
 }
 
 
 var uid = '';
 
 function openUserSetting(userid,status) {
	 		  uid= userid;
	 		  var url="getUsrSettingPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = openUserSettingRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function openUserSettingRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				var str=data.split("~");
				document.getElementById("salebilldiv").innerHTML=str[0];
				document.getElementById("discountdiv").innerHTML=str[1];  
				document.getElementById("ledgerdiv").innerHTML=str[2];
				document.getElementById("accountdiv").innerHTML=str[3];      
				document.getElementById("purchasediv").innerHTML=str[4];
				document.getElementById("sendSMSdiv").innerHTML=str[5];
				document.getElementById("uid").value=str[6];
				document.getElementById("editbilldiv").innerHTML=str[7];
				document.getElementById("deletebilldiv").innerHTML=str[8];
				document.getElementById("purchaseordereditdiv").innerHTML=str[9];
				document.getElementById("purchaseorderdeletediv").innerHTML=str[10];
				document.getElementById("edit_cataloguediv").innerHTML=str[11];
				document.getElementById("delete_cataloguediv").innerHTML=str[12];
				document.getElementById("backdatediv").innerHTML=str[13];
				document.getElementById("thirdpartydiv").innerHTML=str[14];
				document.getElementById("requisitiondiv").innerHTML=str[15];
				document.getElementById("checkavailabilitydiv").innerHTML=str[16];
				document.getElementById("directtransferdiv").innerHTML=str[17];
				document.getElementById("inventoryreportdiv").innerHTML=str[18];
				document.getElementById("returnstockdiv").innerHTML=str[19];
				document.getElementById("cancelindentdiv").innerHTML=str[20];
				document.getElementById("medretauthdiv").innerHTML=str[21];
				document.getElementById("indentapprovediv").innerHTML=str[22];
				document.getElementById("poapprovediv").innerHTML=str[23];
				document.getElementById("cancelpo1").innerHTML=str[24];
				document.getElementById("editpurchase").innerHTML=str[25];
				document.getElementById("pharm_bkdt").innerHTML=str[26];
				document.getElementById("session_added_med").innerHTML=str[27];
				document.getElementById("patienteditdiv").innerHTML=str[28];
				$('#accessset').modal( "show" );
		 }
	}
}
function setSaleBillAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserSaleSetttingPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setSaleBillAccesssRequest;
   			 req.open("GET", url, true); 
             req.send(null);
	}
	function setSaleBillAccesssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		   		var data=req.responseText;
				document.getElementById("salebilldiv").innerHTML=data;
         }
	}
}

function setDisscountAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserDiscountSetttingPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setDisscountAccesssRequest;
   			 req.open("GET", url, true); 
             req.send(null);
}
	function setDisscountAccesssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    	var data=req.responseText;
				document.getElementById("discountdiv").innerHTML=data;  
         }
	}
}

function setLedgerAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserLedgerSetttingPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setLedgerAccesssRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setLedgerAccesssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("ledgerdiv").innerHTML=data;
				         }
	}
}

function setAccountAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserAccountSetttingPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setAccountAccesssRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setAccountAccesssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("accountdiv").innerHTML=data;      
			}
	}
}
function setPurchase_orderAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserPurchase_orderSetttingPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setPurchase_orderAccesssRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setPurchase_orderAccesssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("purchasediv").innerHTML=data;
			}
	}
}

function setSMSAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserSMSSendPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setSMSRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setSMSRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("sendSMSdiv").innerHTML=data;
			}
	}
}



var cell;
var row;

function addreturnmedicineRow(tableID){
  	var newmid=document.getElementById("returnmedicine").value;
     if(newmid=='0'){
                jAlert('error', "Please select product!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					  
       
     }
     else {
    		var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			row=table.insertRow(rowCount);
			var counts = rowCount - 1;	
			cell=row.insertCell(0);
    
    		var url="addreturnmedicinePharmacy?pid="+newmid+"&count="+counts+"";
    		
              if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = addreturnmedicineRequest;
			    req.open("GET", url, true); 
			    req.send(null);
     
     } 

}
function addreturnmedicineRequest() {
  
     if (req.readyState == 4) {
         if (req.status == 200) {     
             
             		var dd=req.responseText;
             		if(dd=="0"){
             		 	 
						jAlert('error', "Barcode not generated!", 'Error Dialog');
				
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 		             		 	     
             		 	
             		} else {
             		
             				row.innerHTML=dd;
		                 	var total=Number(document.getElementById("tempcount").value);
		    			 	total++; 
						 	document.getElementById("tempcount").value=total;	  
		                    //calsubTotaltemp();
		                    
		                    calRefundTotaltemp();
             		}
              }
         }
  }

function calsubTotaltemp(){

   var subtotal=0;
   var vattotal=0;
   var totamt=0; 

   $('.pclass').each(function() {
		    
     	var i=this.value;
     	//var pstock = Number(document.getElementById("prodd"+i).value);
        var sale=Number(document.getElementById("sale"+i).value);
        
        if(sale==0){
        	
        	jAlert('error', 'Sale quantity can not be 0.', 'Error Dialog');
		
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			document.getElementById("sale"+i).value = '0';
        }/*else if(pstock<sale){
        	
        	jAlert('error', 'Sale quantity greater than stock.', 'Error Dialog');
		
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			document.getElementById("sale"+i).value = '0';
			
        }*/
       else{
        
        
       var mrp=Number(document.getElementById("mrp"+i).value);
       //var vat=Number(document.getElementById("vat"+i).value);
       var req=Number(document.getElementById("req"+i).value);
       
       if(sale>req){
            //document.getElementById("sale"+i).value='0';
            document.getElementById("labelreq"+i).innerHTML=sale;
       }
       
	   var tt=sale*mrp;	       
       subtotal=subtotal+tt;
       
       //var tvat=tt*vat/100;
       //vattotal=vattotal+tvat;
       
       
       
       tt=roundTwo(tt);
       document.getElementById("totalmrp"+i).innerHTML=tt;
       
      }
        
   }); 
  
  subtotal=roundTwo(subtotal);
  //vattotal=roundTwo(vattotal);
  
  var temptotal=subtotal;
  
  document.getElementById("subtotal").innerHTML=roundAbs(temptotal);
  var discsmt=document.getElementById("discsmt").value;
   //document.getElementById("vat").innerHTML=vattotal;
   //document.getElementById("tvat").value=vattotal;
   document.getElementById("total").innerHTML=roundAbs(temptotal);
   document.getElementById("ttotal").value=roundAbs(temptotal);
  // document.getElementById("baltotal").innerHTML = temptotal;
  //calDiscount(discsmt);
}

function roundAbs(val){
 
  val =Math.round(val); 
  
     return val;
}


function calRefundTotaltemp(){
       try{
    	   var refund=0;
  		   var totalvat=0;
  		   var totaldisc=0;
  		   var gst=0;
			$('.pclass').each(function() {					
			        var i=this.value;
					var previqty=Number(document.getElementById("tempsale"+i).value);
					var returnqty=Number(document.getElementById("returnqty"+i).value);    
					if(returnqty<0){
						returnqty=0;
						document.getElementById("returnqty"+i).value=0;
						 jAlert('error', "Retrun qty cant negative!", 'Error Dialog');
							
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					}
					if(returnqty>previqty){
					    document.getElementById("returnqty"+i).value=0;
					      jAlert('error', "Retrun qty cant greater than sale qty!", 'Error Dialog');
				
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						returnqty=0;	 
				    }       
 		    		var mrp=document.getElementById("mrp"+i).value;
 		    		var vat=Number(document.getElementById("vat"+i).value);
 		    		
 		    		var tot=mrp*returnqty;
 		    		var discper= Number(document.getElementById("discper"+i).value);
 		    		var disct= tot* discper /100; 
 		    		document.getElementById("totalmrp"+i).innerHTML=roundTwo(tot);
 		    		totaldisc= totaldisc +disct;
 		    		
 		    		var tvat=tot*vat/100;
 		    		totalvat=totalvat+tvat;
 		    		//  12 Nov 2018 after discount gst calculation
 		    		var ttamt=0;
 		    		ttamt = tot - disct; 
 		    		var dividevat=100+vat;
	   				//var tgst=tot*vat/dividevat;
 		    		var tgst=ttamt*vat/dividevat;
	  			    gst=gst+tgst;
 		   			
	  			    refund=refund+tot; 		
 		   			if(document.getElementById("totalrefundrs"+i)){
 		   				var totalrefundrs = roundTwo(tot)-roundTwo(disct);
 		   				document.getElementById("totalrefundrs"+i).value =roundAbs(totalrefundrs);
 		   			}
      		});  
      		
      		var sgst= roundTwo(gst/2); 
      		
      		document.getElementById("tvat").value=roundTwo(gst);
      		document.getElementById("tcgst").innerText=roundTwo(sgst);
      		document.getElementById("tsgst").innerText=roundTwo(sgst);
      		document.getElementById("cgst").value=roundTwo(sgst);
      		document.getElementById("sgst").value=roundTwo(sgst);
  			document.getElementById("subtotal").innerText=roundAbs(refund);
  			document.getElementById("discsmt").value=roundAbs(totaldisc);
  			if(document.getElementById("subbtotal")){
      			document.getElementById("subbtotal").value= roundAbs(refund);
      		}
  			var subttotal = refund;
  			refund=refund-totaldisc;
  			document.getElementById("refund").innerText=roundAbs(refund);
      		document.getElementById("ttotal").value=roundAbs(refund);
      		document.getElementById("total").innerText=roundAbs(refund);
      		document.getElementById("trefund").value=roundAbs(refund);
      		
      		//Pikachu 17-12-2018
      		document.getElementById("grosstotal").value= roundTwo(refund);
      	    document.getElementById("grosstotalspan").innerHTML= roundTwo(refund);
      	    document.getElementById("grosssubtotal").value= roundTwo(subttotal);
      	    
      	    if(finalsalereturn==1){
      	    	 finalsalereturn = 0;
      	    	 var returnmode = document.getElementById("returnmode").value;
      	    	 var n=roundAbs(refund);
      	    	 var discnt =totaldisc;
                 document.getElementById("npopup").innerHTML=n;
                 document.getElementById("paymodepopup").innerHTML=returnmode;
                 document.getElementById("discountconfirm").innerHTML=discnt;
                 $('#confirmreturnpopup').modal( "show" );	
      	    }
       }catch (e) {
    	   window.location.reload();	      
       }
}		

function deleteRowtemp(r){
   
        var i = r.parentNode.parentNode.rowIndex;
    	document.getElementById("mytable").deleteRow(i);
    	
    	calRefundTotaltemp();
    
}
 
function getipdpatientmbill(ipdid){
			var url="getIpdPatientbillPharmacy?ipdid="+ipdid+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = getipdpatientmbillRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function getipdpatientmbillRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				var str=data.split("~");
				document.getElementById("fullname").value=str[0];
				document.getElementById("wardname").value=str[1];
				document.getElementById("practitionerName").value=str[2];
				document.getElementById("dateTime").value=str[3];
				document.getElementById("medicinediv").innerHTML=str[4];
				document.getElementById("ipdid").value=str[5];
				document.getElementById("selectedid").value=str[6];
				document.getElementById("rebalance").innerHTML=str[7];
				document.getElementById("prebalance").value=str[7];
				document.getElementById("opdid").value=str[8];
				document.getElementById("mobile").value=str[9];
				
			}
	}
}

function refundIpdMedicine(paymode) {

    document.getElementById("paymode").value=paymode;
	var total=document.getElementById("tempcount").value;
	var flag=true;
	
	$('.pclass').each(function() {
        var i=this.value;
		var sale=document.getElementById("returnqty"+i).value;		   
	     if(sale==''){
	            
	            flag=false;
                 jAlert('error', "Please enter return quantity!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
	     }  
	}); 
  
 	if(flag){
 	    
 	   document.getElementById("creditform").action="refundipdmedicinePharmacy";
 	   document.getElementById("creditform").submit();
 	}

}

function clearBalance(f,billno,clientid) {

           var a=window.confirm("Are you sure to clear this Balance?");  
           if(a==true){
            
               var url="clearbalancePharmacy?flag="+f+"&billno="+billno+"&clientid="+clientid+"";
           
               	   if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = clearBalanceRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
    	
           } 
             
}
function clearBalanceRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				window.location.reload();
				
			}
	}
}	

var patientId=0;
var flag=0;


function openExBal(){
   
     var bal= document.getElementById("rebalance").innerHTML;
     clearBal(1,pclientid,bal,'');
}


function clearBal(f,pid,bal,name){

       flag=f;
       patientId=pid;
       document.getElementById("flag").value=flag;
       document.getElementById("pid").value=pid;
       document.getElementById("patiName").innerText= name;
	   document.getElementById("bal").value=bal;	       
       $('#clearbal').modal( "show" );
      
}

function paybalance() {
   
      var payamt= document.getElementById("bal").value;
      var paymode= document.getElementById("paymode").value;
      var cardno= document.getElementById("cardno").value;
      var paynotes= document.getElementById("paynotes").value;
      

	         var url="getbalpaymentPharmacy?flag="+flag+"&clientid="+patientId+"&payamt="+payamt+"&paymode="+paymode+"&cardno="+cardno+"&paynotes="+paynotes+"";
           
             if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = paybalanceRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);	

}

function paybalanceRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				window.location.reload();
				
			}
	}
}	
 
 
function hideCardDiv(val){
  
     if(val=='Card'){
        
           document.getElementById("carddiv").className="form-group";
     } else {
     
          document.getElementById("carddiv").className="hidden form-group";	
     }

}

var index=0;

function editUserSetting(indx,id,status){

		 index=id;
         var url="edituserPharmacy?index="+indx+"&id="+id+"&status="+status+"";
          
             if (window.XMLHttpRequest) {
					 req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = editUserSettingRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);    

}

function editUserSettingRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				document.getElementById(index).innerHTML=req.responseText;
				
			}
	}
}	
 

function updateuser(indx,id){
		
			var fullname=document.getElementById("fullname"+indx).value;
			var phone=document.getElementById("phone"+indx).value;
			var userid= document.getElementById("userid"+indx).value;
			var password= document.getElementById("password"+indx).value;
			var location= document.getElementById("location"+indx).value;
			var state= document.getElementById("state"+indx).value;
    
    		var url="updateuserPharmacy?fullname="+fullname+"&phone="+phone+"&userid="+userid+"&password="+password+"&id="+id+"&location="+location+"&state="+state+"";

            if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			               
			req.onreadystatechange = updateuserRequest;
			req.open("GET", url, true); 
			req.send(null);	
}

function updateuserRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
					window.location.reload();
         	}
		}
}

function clearAll(){

    document.getElementById("tempcount").value=0;
    document.getElementById("innerTableBody").innerHTML="<tr></tr>";
    document.getElementById("baldiv").innerHTML="<h3 style='color:#d9534f;'>Balance : <span style='float: right;' >Rs.<span id='rebalance'>00.00</span> <input type='hidden' id='prebalance' value='0' /> </span></h3>";
    document.getElementById("fullname").value="";
    document.getElementById("mobile").value="";
    document.getElementById("wardname").value="";
    document.getElementById("doctor").value="";
    document.getElementById("extpid").value="";
    document.getElementById("balance").value="0";
    document.getElementById("baltotal").value="0";
    document.getElementById("discsmt").value="0";
    document.getElementById("tdiscount").value="0";
    document.getElementById("tdisc").innerText="0";
    document.getElementById("paytype").value="Cash";
    document.getElementById("card").className="form-control hidden";
    document.getElementById("neftid").className="form-control hidden";
    document.getElementById("chequeno").className="form-control hidden";
    calsubTotal();
}

function sendSms(bill){

       var url="sendsmsPharmacy?billno="+bill+"";
       if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			               
			req.onreadystatechange = sendSmsRequest;
			req.open("GET", url, true); 
			req.send(null);	   
}

function sendSmsRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
					
					 jAlert('success', "Message send successfully!", 'Success Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
         	}
		}
}

function clearBalanceTemp() {
	
	if (confirm('Are you sure you want to clear balance?')) {
    	//var extpid = document.getElementById("extpid").value;
    	var rebalance =	document.getElementById("rebalance").innerHTML;
		var url="getBalanceFromDBPharmacy?rebalance="+rebalance+"";   	
	 		     	
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
      	req.onreadystatechange = clearBalanceTempRequest;
   	  	req.open("GET", url, true); 
      	req.send(null);
    } 
	}
	
	
	function clearBalanceTempRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    	var total=document.getElementById("total").innerHTML; 
				var prebal = document.getElementById("rebalance").innerHTML ;
				var totalamt = parseFloat(total)+parseFloat(prebal);
				document.getElementById("baltotal").innerHTML = totalamt;
				var data=req.responseText;
				document.getElementById("baldiv").innerHTML = data;
			}
	}
}

function setReturnBalance(val) {
    var total=document.getElementById("total").innerHTML; 
	var prebal = val ;
	var totalamt = parseFloat(total)+parseFloat(prebal);
	document.getElementById("baltotal").innerHTML = totalamt;
  	
}

function setlocPharmacy(loc) {
 
      var url="setlocPharmacy?location="+loc+"";
      if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
      	req.onreadystatechange = setlocPharmacyRequest;
   	  	req.open("GET", url, true); 
      	req.send(null);
}


function setlocPharmacyRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    	 window.location.reload();
			}
	}
}

 
function confirmDel(){

      var t= window.confirm("Are you Sure to Cancel this Record?");
      if(t==true){
        	return true;
      }  else {
          return false;
      }
  
} 
 function setEdit_billAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserEdit_billPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setEdit_billAccesssRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setEdit_billAccesssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("editbilldiv").innerHTML=data;
			}
	}
}
function setDelete_billAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserDelete_billPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setDelete_billAccesssRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setDelete_billAccesssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("deletebilldiv").innerHTML=data;
			}
	}
}


function updateSetting(){

     var ipd="0";
     var opd="0";
     var inhouse="0";
      $('.ipd').each(function() { 
			if(this.checked == true){
			 
			     ipd=ipd+","+this.value;
			} 
									
		});
	 
	 $('.opd').each(function() { 
			if(this.checked == true){
			 
			     opd=opd+","+this.value;
			} 
									
		});	
		
		$('.inhouse').each(function() { 
			if(this.checked == true){
			 
			     inhouse=inhouse+","+this.value;
			} 
									
		});	
		
		
		var ptype = document.getElementsByName('prd');
		var p_value;
		for(var i = 0; i < ptype.length; i++){
    	  if(ptype[i].checked){
        		p_value = ptype[i].value;
    		}
		}
		
		var print = document.getElementsByName('print');
		var p_type;
		for(var i = 0; i < print.length; i++){
    	  if(print[i].checked){
        		p_type = print[i].value;
    		}
		}
		
		document.getElementById("procurementType").value=p_value;
		document.getElementById("ipdlocation").value= ipd;
		document.getElementById("opdlocation").value= opd;
		document.getElementById("printType").value=p_type;
		document.getElementById("inhousepatient").value=inhouse;
    
    return true;     
} 

function setEdit_PurchaseOrderAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserEdit_PurchaseOrderPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setEdit_PurchaseOrderAccesssRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setEdit_PurchaseOrderAccesssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("purchaseordereditdiv").innerHTML=data;
			}
	}
}
function setDelete_PurchaseOrderAccesss(userid,status) {
	 		  uid= userid;
	 		  var url="setUserDelete_PurchaseOrderPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setDelete_PurchaseOrderAccesssRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setDelete_PurchaseOrderAccesssRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("purchaseorderdeletediv").innerHTML=data;
			}
	}
}

function setEditCatalogue(userid,status) {
	 		  uid= userid;
	 		  var url="seteditcataloguePharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setEditCatalogueRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setEditCatalogueRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("edit_cataloguediv").innerHTML=data;
			}
	}
}

function setDeleteCatalogue(userid,status) {
	 		  uid= userid;
	 		  var url="setdeletecataloguePharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setDeleteCatalogueRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setDeleteCatalogueRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("delete_cataloguediv").innerHTML=data;
			}
	}
}

function setBackDateAccess(userid,status) {
	 		  uid= userid;
	 		  var url="setbackdatePharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setBackDateAccessRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setBackDateAccessRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("backdatediv").innerHTML=data;
			}
	}
}
function setTpBillAccess(userid,status) {
	 		  uid= userid;
	 		  var url="settpbillPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setTpBillAccessRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setTpBillAccessRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("thirdpartydiv").innerHTML=data;
			}
	}
}







function setinhousepatient() {
	 var status = document.getElementById("inhousepatient").checked;
	 var url="setInhousePatientPharmacy?status="+status+"";   	
	 	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
       	req.onreadystatechange = setinhousepatientRequest;
   		req.open("GET", url, true); 
        req.send(null);
	}
	function setinhousepatientRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    	var data=req.responseText;
				document.getElementById("inhousepatientdiv").innerHTML=data;
			}
	}
}


function showInhousePatientPopup(){
	var flag = true;
	if(document.getElementById('location')){
		if(document.getElementById('location').value==0){
			flag = false;
		}
	}
	if(flag){
		var url = "showAllPatientListPharmacyAjax";
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
	}else{
		jAlert('error', "Please select location!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 	
	}
		

	}
	function showAllPatientPopUpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allPatient").innerHTML = req.responseText;
				$('#clientSearchDiv').modal( "show" );	
	         }
		}
	}
	var clientid='';
	var ipdid='';
	function setInhousePatientInfo(clientid1,ipdid1,ispharmacy) {
		//if any parameter added in this function then add parameter in returnmultimedicine.jsp too.
		document.getElementById("hdnphclientid").value = clientid1;
		document.getElementById("hdnispharmacy").value = ispharmacy;
		clientid=clientid1;
		ipdid=ipdid1;
		if(ispharmacy==1){
			$("#fullname").attr("readonly", true); 
			$("#mobile").attr("readonly", true); 
			$("#wardname").attr("readonly", true); 
			$("#doctor").attr("readonly", true); 
		}else{
			$("#fullname").attr("readonly", true); 
			$("#mobile").attr("readonly", true); 
			$("#wardname").attr("readonly", true); 
			$("#doctor").attr("readonly", true); 
		}
		
		//document.getElementById("fullname").value = name;
		//document.getElementById("mobile").value =mobno;
		//document.getElementById("wardname").value =address;
		//document.getElementById("prebalance").value =balance;
		//document.getElementById("doctor").value =pract_name;
		//document.getElementById("patient_city").innerHTML = city;
		//document.getElementById("patient_gender").innerHTML = gender;
		//document.getElementById("patient_id").value = id;
		if(document.getElementById("hdnipdid")){
			//  16 August 2018 for return medicine bill 
			document.getElementById("hdnipdid").value = ipdid;
		}
		$('#clientSearchDiv').modal( "hide" );	
		/*if(document.getElementById("issalepriscdashboardakash")){
			checkPatientInformationInTemp(clientid,ipdid,ispharmacy);
		}else{
			getAllPatientInformation(clientid,ipdid,ispharmacy);
		}*/
		getAllPatientInformation(clientid,ipdid,ispharmacy);
		
}

	function checkPatientInformationInTemp(clientid,ipdid,ispharmacy){
		try {
			var hdnphclientid = clientid;
			var hdnispharmacy = ispharmacy;
			 var dataObj={
					"hdnphclientid":""+hdnphclientid+"",
					"hdnispharmacy":""+hdnispharmacy+"",
			 };
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "checkpatientintempPharmacyAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				  var dd=data.productlist;
		       		if(dd=="1"){
		       			if (confirm('Patient is already in use. Do you want to use it again. Yes, then system delete previous data of that patient if someone making bill of that patient?')) {
		       				getAllPatientInformation(clientid,ipdid,ispharmacy);
		       			} else {
		       				showInhousePatientPopup();
		       			}		             		 	     
		       		}else {
		       			getAllPatientInformation(clientid,ipdid,ispharmacy);
					}
			   },
			   error : function(result) {
				   jAlert('error', "Something wrong!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }
			}); 
		} catch (e) {
			window.location.reload();	      
		}
	}
	
	
var ispclient=0; 	
	
function getAllPatientInformation(clientid,ipdid,ispharmacy){
	ispclient=ispharmacy;
	var isfromsaledash = 0;
	var isfrompharmacymedreturn = 0;
	if(document.getElementById("issalepriscdashboardakash")){
		isfromsaledash=1;
	}
	if(document.getElementById("pharmacymedreturn")){
		isfrompharmacymedreturn=1;
	}
	var url = "getAllPatientInformationPharmacy?clientid="+clientid+"&ipdid="+ipdid+"&ispharmacy="+ispharmacy+"&isfromsaledash="+isfromsaledash+"&isfrompharmacymedreturn="+isfrompharmacymedreturn+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getAllPatientInformationRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}


	
function getAllPatientInformationRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				var data=req.responseText;
				var str=data.split("~");
	         	document.getElementById("fullname").value = str[0];
				document.getElementById("mobile").value = str[1];
				document.getElementById("wardname").value = str[2];
				document.getElementById("prebalance").value = str[4];
				document.getElementById("doctor").value = str[3];
	         	document.getElementById("rebalance").innerHTML = str[4];
	         	document.getElementById("payee").innerText=str[5];
	         	
	         	var ispharornot =  document.getElementById("hdnispharmacy").value;
	         	if(ispharornot==1){
	         		if(document.getElementById("pharpatienteditatag")){
	         			document.getElementById("pharpatienteditatag").className = "";
	         		}
	         	}else{
	         		if(document.getElementById("pharpatienteditatag")){
	         			document.getElementById("pharpatienteditatag").className = "hidden";
	         		}
	         	}
	         	
	         	// 	document.getElementById("ipdopd").value=clientid;
	         	if(document.getElementById("abbbbrivationid")){
	         		document.getElementById("abbbbrivationid").innerText = str[6];
	         	}
	         	if(document.getElementById("creditprebalance")){
	         		document.getElementById("creditprebalance").value = str[7];
	         	}
	         	
	         	
	         	if(document.getElementById("discsmt")){
	         		document.getElementById("discsmt").value = str[8];
	         	}
	         	
	         	if(document.getElementById("phar_practid")){
	         		if(document.getElementById("normalsaledashbbbord")){
	         			document.getElementById("phar_practid").className="";
	         			document.getElementById("phar_practid").value = str[9];
	         			document.getElementById("phar_practid").className="form-control chosen";
	         			 $("#phar_practid").trigger("chosen:updated");
	    			  	 $(".chosen").chosen({allow_single_deselect: true});
	         		}else{
	         			document.getElementById("phar_practid").value = str[9];
	         		}
	         	}
	         	
	         	var tt= document.getElementById('returnpage').value;
	         	if(document.getElementById('multireturndash')){
	         		
	         	}else{
	         		if(document.getElementById('ismultireturnprocess')){
	         			document.getElementById("clientid").value=clientid;
	         			document.getElementById("ispharmacy").value=ispclient;
	         			if(document.getElementById('ismultireturnnurceprocess')){
	         				calRefundTotaltemp();
	         			}
	         		}else{
		         		if(tt=='1'){
		         			//setAllMedicines(clientid,ispclient);
		         			getModewiseSingleBill(clientid,ispclient);
		         		}
		         	}
	         	}
	         	
	         	if(document.getElementById("issalepriscdashboardakash")){
	         		document.getElementById("innerTableBody").innerHTML ="<tr></tr>";
	         		getAllMedicineListPharmacy();
	         	}
	         	
	         }
		}
	}

function getAllMedicineListPharmacy(){
	 document.getElementById("innerTableBody").innerHTML ="<tr></tr>";
	 var url="getcataloguewiselistforsalePharmacyAjax";
	 if(document.getElementById("phar_batchwise_sale")){
		 if(document.getElementById("phar_batchwise_sale").value==0){
			 url="setallmedicineslistdaataPharmacyAjax";
		 }
	 }
	 
	 //var url="setallmedicineslistdaataPharmacy";
	 //var url="getcataloguewiselistforsalePharmacyAjax";
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = getAllMedicineListPharmacyRequest;
	req.open("GET", url, true); 
	req.send(null);
}
function getAllMedicineListPharmacyRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str= req.responseText;
			 var phar_batchwise_sale=1;
			 if(document.getElementById("phar_batchwise_sale")){
				 if(document.getElementById("phar_batchwise_sale").value==0){
					 phar_batchwise_sale=0;
				 }
			 }
			 
			 if(phar_batchwise_sale==1){
				 var data= str.split("~~~.~~~");
				 document.getElementById("cataloguewiselistid").innerHTML=data[0];
				 $("#newcataloguesaleid").trigger("chosen:updated");
			  	 $(".chosen").chosen({allow_single_deselect: true});
			  	 
			  	 document.getElementById("plist").innerHTML=data[1];
			     $("#newmedicine").trigger("chosen:updated");
			  	 $(".chosen").chosen({allow_single_deselect: true});
			 }else{
				 document.getElementById("plist").innerHTML=str;
			     $("#newmedicine").trigger("chosen:updated");
			  	 $(".chosen").chosen({allow_single_deselect: true});
			 }
			 addtempsaledata();
			 /*document.getElementById("plist").innerHTML=req.responseText;
		     $("#newmedicine").trigger("chosen:updated");
		  	 $(".chosen").chosen({allow_single_deselect: true});
		  	addtempsaledata();	  */   
		  	
		}
	}
}

function getProductListForSale(val){
	 //var url="setallmedicineslistdaataPharmacy";
	 var url="getallmedicinelistforsalePharmacyAjax?val="+val+"";
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = getProductListForSaleRequest;
	req.open("GET", url, true); 
	req.send(null);
}
function getProductListForSaleRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str= req.responseText;
			 document.getElementById("plist").innerHTML=str;
		     $("#newmedicine").trigger("chosen:updated");
		  	 $(".chosen").chosen({allow_single_deselect: true});
		  	 addtempsaledata();   
		}
	}
}



function updatePharmaClientInfo(val,column){
	var url = "updatepatientPharmacy?val="+val+"&column="+column+"&clientid="+clientid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updatePharmaClientInfoRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

}
function updatePharmaClientInfoRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
         }
	}
}

function savePharmaClient(){
	$("#dashboardloaderPopup").modal('show');
	var flagsetbtn=false;
	
	if(document.getElementById("addpharpatientbtnid")){
		document.getElementById("addpharpatientbtnid").disabled =true;
		flagsetbtn = true;
	}
	var adfullname = document.getElementById("adfullname").value;
	var adaddress = document.getElementById("adaddress").value;
	var hiddenclinicid=document.getElementById('hiddenclinicid').value;
	if(hiddenclinicid="parihar"){
		 var admobile = "";
	}
	else{
    var admobile = document.getElementById("admobile").value;
    var addoctor = document.getElementById("addoctor").value;
	var gstin=document.getElementById("gstin").value;
	var dlno=document.getElementById("dlno").value;
	}
	var regEx = /^\d{10}$/;
	
	if(adfullname=='' || adfullname==' ' || adfullname=='  ' || adfullname=='   '){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter patient full name!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 	
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}
	
	if(hiddenclinicid!="parihar"){
	 if(!regEx.test(admobile)) {
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid mobile no!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}
   else if(hiddenclinicid!="saimed" && hiddenclinicid!="infinyte"){
	 if(addoctor=='0') {
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please select doctor!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}else if(addoctor=='') {
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please select doctor!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}
	
}}else{
		var url = "addnewpatientPharmacy?adfullname="+adfullname+"&admobile="+admobile+"&adaddress="+adaddress+"&addoctor="+addoctor+"&gstin="+gstin+"&dlno="+dlno+" ";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = savePharmaClientRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		
	}
	
}

function savePharmaClientRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			showInhousePatientPopup();
			$('#addPatientDiv').modal( "hide" );
			$("#dashboardloaderPopup").modal('hide');
			if(document.getElementById("addpharpatientbtnid")){
				document.getElementById("addpharpatientbtnid").disabled =false;
			}
			document.getElementById("adfullname").value="";
			document.getElementById("admobile").value="";
			document.getElementById("adaddress").value="";
			document.getElementById("addoctor").value="";
         }
	}
	
}

function searchPatient(){
	var searchText = document.getElementById("searchText1").value;
	var url = "searchParticularPatientPharmacyAjax?searchText="+searchText+"";

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


  function setPaymode(val) {
      
      document.getElementById("paymode").value=val;
      var total=document.getElementById("ttotal").value;
      if(val=="Cash"){
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
      } else if(val=="Card"){
      
            document.getElementById("card").className="form-control";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
            document.getElementById("card").focus();
      } else if(val=="Cheque"){
      
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control";
            document.getElementById("chequeno").focus();
      }  
      else if(val=="NEFT" || val=="Online Payment"){
             document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control";
            document.getElementById("chequeno").className="form-control hidden";
            document.getElementById("neftid").focus();
            
      } else if(val=="Credit" || val=="Hospital" ) {
         
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
            document.getElementById("received").value=0;
      		getRemainAmt(0);	  
            
      }else {
         
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
            document.getElementById("onlinepaymentid").className="form-control hidden";
      }
      
      if(val!="Credit" && val!="Hospital" ){
        
                
                  total=document.getElementById("ttotal").value;   
                  document.getElementById("received").value=total;
      			  getRemainAmt(total);	    
      }
      
        
 }
 function setPaymodeNew(val) {
      
      if(val=="Cash"){
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
             document.getElementById("card1").className="form-control hidden";
            document.getElementById("neftid1").className="form-control hidden";
            document.getElementById("chequeno1").className="form-control hidden";
      } else if(val=="Card"){
      
            document.getElementById("card").className="form-control";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
             document.getElementById("card1").className="form-control";
            document.getElementById("neftid1").className="form-control hidden";
            document.getElementById("chequeno1").className="form-control hidden";
            document.getElementById("card").focus();
            document.getElementById("card1").focus();
      } else if(val=="Cheque"){
      
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control";
            document.getElementById("chequeno").focus();
            document.getElementById("card1").className="form-control hidden";
            document.getElementById("neftid1").className="form-control hidden";
            document.getElementById("chequeno1").className="form-control";
            document.getElementById("chequeno1").focus();
      }  
      else if(val=="NEFT" || val=="Online Payment"){
             document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control";
            document.getElementById("chequeno").className="form-control hidden";
            document.getElementById("neftid").focus();
             document.getElementById("card1").className="form-control hidden";
            document.getElementById("neftid1").className="form-control";
            document.getElementById("chequeno1").className="form-control hidden";
            document.getElementById("neftid1").focus();
            
      }  else {
         
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
            document.getElementById("card1").className="form-control hidden";
            document.getElementById("neftid1").className="form-control hidden";
            document.getElementById("chequeno1").className="form-control hidden";
      }
 }


function setRetunPaymode(val) {
      
      document.getElementById("paymode").value=val;
      if(val=="Cash"){
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
      } else if(val=="Card"){
      
            document.getElementById("card").className="form-control";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
            document.getElementById("card").focus();
      } else if(val=="Cheque"){
      
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control";
            document.getElementById("chequeno").focus();
      }  
      else if(val=="NEFT"){
             document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control";
            document.getElementById("chequeno").className="form-control hidden";
            document.getElementById("neftid").focus();
            
      } else if(val=="Credit") {
         
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
            
      } else {
         
            document.getElementById("card").className="form-control hidden";
            document.getElementById("neftid").className="form-control hidden";
            document.getElementById("chequeno").className="form-control hidden";
      }
        
 }
 
 
function showRequestPopupForAprove1(parentid,val){
	var url = "showrequestedmedicinefraprovePharmacy?parentid="+parentid+"&val="+val+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();1
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showRequestPopupForAprove1Request;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function showRequestPopupForAprove1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			var str = req.responseText;
				var data = str.split("~");
				document.getElementById("fromlocation4").innerHTML = data[0];
				document.getElementById("issuedate4").innerHTML = data[1];
				document.getElementById("tolocation4").innerHTML = data[2];
				document.getElementById("tbodyid4").innerHTML = data[3];
				document.getElementById("requestdate4").innerHTML = data[1];
				document.getElementById("issueno4").innerHTML = data[4];
				document.getElementById("hospitaltitlediv4").innerHTML = data[5];
				document.getElementById("username4").innerHTML = data[6];
				document.getElementById("userdatetime4").innerHTML = data[7];
				document.getElementById("parentid24").value = data[4];
				document.getElementById("buttondiv").innerHTML = data[8];
				$('#cart4').modal( "show" );	
	         }
		}
	}
 
function aproveTransferLog(){
 var parentid = document.getElementById("parentid24").value;
 var url = "updateAproveTransferLogPharmacy?parentid="+parentid+"&aprove=1";
  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();1
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = aproveTransferLogRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function aproveTransferLogRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			 }
		}
	}
	
	
function rejectTransferLog(){
 var parentid = document.getElementById("parentid24").value;
	var notes= document.getElementById("notes").value;
	if(notes==''){
			jAlert('error', "Please enter note!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
	}else{
		var url = "updaterejectTransferLogPharmacy?parentid="+parentid+"&notes="+notes+"&reject=1";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = rejectTransferLogRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	

	}
function rejectTransferLogRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			 }
		}
	}


 
 
 
 function finalrejectTransferLog(){
 var parentid = document.getElementById("parentid24").value;
 var notes= document.getElementById("notes").value;
 	if(notes==''){
			jAlert('error', "Please enter note!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
	}else{
		var url = "updatefinalrejectTransferLogPharmacy?parentid="+parentid+"&notes="+notes+"&reject=0";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();1
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = finalrejectTransferLogRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	

	}
function finalrejectTransferLogRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			 }
		}
	}


function finalaproveTransferLog(){
 	var parentid = document.getElementById("parentid24").value;
	var url = "updatefinalAproveTransferLogPharmacy?parentid="+parentid+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();1
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = finalaproveTransferLogRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	}

function finalaproveTransferLogRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			 }
		}
	}
 
 
 
 
 
 
 
 
 
 
 
 
 
 function setRequisition_authAccess(userid,status) {
	 		  uid= userid;
	 		  var url="setRequisition_authAccessPharmacy?userid="+userid+"&status="+status+"";   	
	 		     	
	 		  if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			  }
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			 req.onreadystatechange = setRequisition_authAccessRequest;
   			 req.open("GET", url, true); 
              
   			 req.send(null);
	
	}
	function setRequisition_authAccessRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				document.getElementById("requisitiondiv").innerHTML=data;
			}
	}
}

	var clientidd;
	var cflag;
	function showbillspatient(clientid,flag) {
		$("#dashboardloaderPopup").modal('show');
				clientidd = clientid;
				cflag = flag;
				var url="showclientaccountPharmacyAjax?clientid="+clientid+"&flag="+flag+"";
			
	            if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
				}
				  else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
	               
	   			req.onreadystatechange = showbillspatientRequest;
	   			req.open("GET", url, true); 
	              
	   			req.send(null);
	}
	function showbillspatientRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
			    
					var data=req.responseText;
					var str=data.split("~");
					document.getElementById("tbodydata").innerHTML=str[0];
					document.getElementById("tfootdata").innerHTML=str[1];
					document.getElementById("tempname").innerHTML=str[2];
					document.getElementById("paybillclientid").value=str[3];
					document.getElementById("paybillflag").value=str[4];
					document.getElementById("paybilldiscountstatus").value=str[5];
					if(str[5]==''){
						document.getElementById("fromdate1").value=document.getElementById("fromdate").value;
						document.getElementById("todate1").value=document.getElementById("todate").value;
					}else{
						document.getElementById("fromdate1").value=str[6];
						document.getElementById("todate1").value=str[7];
					}
					
					
					if(cflag==0){
						/*document.getElementById("printbtndiv").innerHTML='<a href="#" class="btn btn-warning hidden-print" onclick=openPopup("getallbillforprintPharmacy?clientid='+clientidd+'&flag=1") style="margin-top: 15px;">Print All</a>  <a href="#" class="btn btn-warning hidden-print" onclick=printDiv("newdiv") style="margin-top: 15px;">Print</a>';*/
						document.getElementById("printbtndiv").innerHTML='<a href="#" class="btn btn-warning hidden-print" onclick="printallbillDetails('+clientidd+','+1+')" style="margin-top: 15px;">Print All</a>  <a href="#" class="btn btn-warning hidden-print" onclick=printDiv("newdiv") style="margin-top: 15px;">Print</a>';
					}else{
						/*document.getElementById("printbtndiv").innerHTML='<a href="#" class="btn btn-warning hidden-print" onclick=openPopup("getallbillforprintPharmacy?clientid='+clientidd+'&flag=0") style="margin-top: 15px;">Print All</a>  <a href="#" class="btn btn-warning hidden-print" onclick=printDiv("newdiv") style="margin-top: 15px;">Print</a>';*/
						document.getElementById("printbtndiv").innerHTML='<a href="#" class="btn btn-warning hidden-print" onclick="printallbillDetails('+clientidd+','+0+')" style="margin-top: 15px;">Print All</a>  <a href="#" class="btn btn-warning hidden-print" onclick=printDiv("newdiv") style="margin-top: 15px;">Print</a>';
					}
					var total="0";
					var totalpaybillbalce=0;
					 $('.akashpaybil').each(function() {
						 if(this.checked){
				          var i=this.value;
				          total=total+","+this.value;
				          var paybillbalce = document.getElementById("paybillbalce"+i).value;
				          totalpaybillbalce =  parseFloat(totalpaybillbalce) + parseFloat(paybillbalce);
						 }
				     });
					 if(totalpaybillbalce>0){
						 document.getElementById("takepaymentdiv").innerHTML='<a href="#" class="btn btn-info hidden-print" onclick="openclearBalnewpopup()" style="margin-top: 15px;">Clear Balance('+totalpaybillbalce+')</a>';
					 }else{
						 document.getElementById("takepaymentdiv").innerHTML='<a href="#" class="btn btn-info hidden-print"  disable="true" style="margin-top: 15px;">Clear Balance('+totalpaybillbalce+')</a>';
					 }
					$("#dashboardloaderPopup").modal('hide');
					$('#detailsview').modal( "show" );
				}
		}
	}
	
	function getBillHistByDate(){
		$("#dashboardloaderPopup").modal('show');
		var cl=clientidd;
		var fl=cflag ;
		var fromdate=document.getElementById("fromdate1").value;
		var todate=document.getElementById("todate1").value;
		var url="showclientaccountPharmacyAjax?clientid="+cl+"&flag="+fl+"&fromdate="+fromdate+"&todate="+todate;
		
	    if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		}
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	       
			req.onreadystatechange = getBillHistByDateRequest;
			req.open("GET", url, true); 
	      
			req.send(null);

	}

	function getBillHistByDateRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				var str=data.split("~");
				document.getElementById("tbodydata").innerHTML=str[0];
				document.getElementById("tfootdata").innerHTML=str[1];
				document.getElementById("tempname").innerHTML=str[2];
				if(cflag==0){
					/*document.getElementById("printbtndiv").innerHTML='<a href="#" class="btn btn-warning hidden-print" onclick=openPopup("getallbillforprintPharmacy?clientid='+clientidd+'&flag=1") style="margin-top: 15px;">Print All</a>  <a href="#" class="btn btn-warning hidden-print" onclick=printDiv("newdiv") style="margin-top: 15px;">Print</a>';*/
					document.getElementById("printbtndiv").innerHTML='<a href="#" class="btn btn-warning hidden-print" onclick="printallbillDetails('+clientidd+','+1+')" style="margin-top: 15px;">Print All</a>  <a href="#" class="btn btn-warning hidden-print" onclick=printDiv("newdiv") style="margin-top: 15px;">Print</a>';
				}else{
					/*document.getElementById("printbtndiv").innerHTML='<a href="#" class="btn btn-warning hidden-print" onclick=openPopup("getallbillforprintPharmacy?clientid='+clientidd+'&flag=0") style="margin-top: 15px;">Print All</a>  <a href="#" class="btn btn-warning hidden-print" onclick=printDiv("newdiv") style="margin-top: 15px;">Print</a>';*/
					document.getElementById("printbtndiv").innerHTML='<a href="#" class="btn btn-warning hidden-print" onclick="printallbillDetails('+clientidd+','+0+')" style="margin-top: 15px;">Print All</a>  <a href="#" class="btn btn-warning hidden-print" onclick=printDiv("newdiv") style="margin-top: 15px;">Print</a>';
				}
				$("#dashboardloaderPopup").modal('hide');
			}
		}
	}
	
	function selectallclearbalcheck(val){
		
		if (val.checked) {
			$('.akashpaybil').each(function() { // loop through each checkbox
				this.checked = true; // deselect all checkboxes with class
										// "checkbox1"
			});
		} else {
			$('.akashpaybil').each(function() { // loop through each checkbox
				this.checked = false; // deselect all checkboxes with class
										// "checkbox1"
			});
		}
		var total="0";
		var totalpaybillbalce=0;
		 $('.akashpaybil').each(function() {
			 if(this.checked){
				 var i=this.value;
		          total=total+","+this.value;
		          var paybillbalce = document.getElementById("paybillbalce"+i).value;
		          totalpaybillbalce =  parseFloat(totalpaybillbalce) + parseFloat(paybillbalce);
			 }
	         
	     });
		 if(totalpaybillbalce>0){
			 document.getElementById("takepaymentdiv").innerHTML='<a href="#" class="btn btn-info hidden-print" onclick="openclearBalnewpopup()" style="margin-top: 15px;">Clear Balance('+totalpaybillbalce+')</a>';
		 }else{
			 document.getElementById("takepaymentdiv").innerHTML='<a href="#" class="btn btn-info hidden-print" disable="true" style="margin-top: 15px;">Clear Balance('+totalpaybillbalce+')</a>';
		 }
	}
	
	function addbalforclear() {
		var total="0";
		var totalpaybillbalce=0;
		 $('.akashpaybil').each(function() {
			 if(this.checked){
				 var i=this.value;
		          total=total+","+this.value;
		          var paybillbalce = document.getElementById("paybillbalce"+i).value;
		          totalpaybillbalce =  parseFloat(totalpaybillbalce) + parseFloat(paybillbalce);
			 }
	         
	     });
		 if(totalpaybillbalce>0){
			 document.getElementById("takepaymentdiv").innerHTML='<a href="#" class="btn btn-info hidden-print" onclick="openclearBalnewpopup()" style="margin-top: 15px;">Clear Balance('+totalpaybillbalce+')</a>';
		 }else{
			 document.getElementById("takepaymentdiv").innerHTML='<a href="#" class="btn btn-info hidden-print"  disable="true" style="margin-top: 15px;">Clear Balance('+totalpaybillbalce+')</a>';
		 }
	}
	
	function printallbillDetails(clientidd,flag){
		var fromdate = document.getElementById("fromdate1").value;
		var todate = document.getElementById("todate1").value;
		  openPopup("getallbillforprintPharmacy?clientid="+clientidd+"&flag="+flag+"&fromdate="+fromdate+"&todate="+todate+"");
	}
	function openclearBalnewpopup() {
		var paybillclientid =document.getElementById("paybillclientid").value;
		var paybillflag=document.getElementById("paybillflag").value;
		var paybilldiscountstatus=document.getElementById("paybilldiscountstatus").value;
		openclearBal(paybillclientid,paybillflag,paybilldiscountstatus);
		
	}
function openBalPopup(bill,name,bal){
	document.getElementById("patiName").innerText=name;
	document.getElementById("bal").value=bal;
	document.getElementById("billno").value=bill;
	document.getElementById("ttbal").value=bal;
	$('#clearbal').modal( "show" );
}

function openBalPopupNew(bill){
    		var url="getbilldiscPharmacy?billno="+bill+"";
            if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			req.onreadystatechange = openBalPopupNewRequest;
   			req.open("GET", url, true); 
              
   			req.send(null);    
      
}

function openBalPopupNewRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				var str=data.split("~");
				document.getElementById("patiName").innerText=str[0];
   				document.getElementById("bal").value=str[1];
   				document.getElementById("billno").value=str[2];
   				document.getElementById("ttbalamt").value=str[4];
   				document.getElementById("ttbal").value=str[1];
   				document.getElementById("singlefinalpayamt").value=str[1];
   				if(str[5]=='1'){
   					document.getElementById("singlebilldisc").readOnly=true;
   				}else{
   					if(str[3]=='1' || str[3]=='1.0'){
   		   				document.getElementById("singlebilldisc").readOnly=true;
   		   			}else{
   		   				document.getElementById("singlebilldisc").readOnly=false;
   		   			}
   				}
   				
   				$('#clearbal').modal( "show" );
				
			}
	}
}



function openclearBal(clientid,flag,discountbalstatus){
		var totalids="0";
		 $('.akashpaybil').each(function() {
			 if(this.checked){
				 var i=this.value;
				 totalids=totalids+","+this.value;
		     }
	     });
           var url="getpatientbalPharmacyAjax?clientid="+clientid+"&flag="+flag+"&discountbalstatus="+discountbalstatus+"&totalids="+totalids+"";
            if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}
			  else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
               
   			req.onreadystatechange = openclearBalRequest;
   			req.open("GET", url, true); 
              
   			req.send(null);    
      
}

function openclearBalRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    
				var data=req.responseText;
				var str=data.split("~");
				document.getElementById("flag").value=str[0];
       			document.getElementById("pid1").value=str[1];
       			document.getElementById("patiName1").innerText=str[2];
	   			document.getElementById("bal1").value=str[3];
	   			document.getElementById("finalpayamt").value=str[3];
	   			document.getElementById("tttbal").value=str[3];
	   			if(str[4]=='1'){
	   				document.getElementById("discrate").readOnly=true;
	   			}else{
	   				document.getElementById("discrate").readOnly=false;
	   			}
	   			document.getElementById("clearbilltotalids").value=str[5];
       			$('#clearbal1').modal( "show" );
				
			}
	}
}


function requestmedicine(){
  var total=0;
  $('.case').each(function() { // loop through each checkbox
   if (this.checked == true) {
      total = total + ',' + this.value;
    }
  });  
  if(total==0){
             jAlert('error', "Please select one product at least!", 'Error Dialog');
    setTimeout(function() {
    $("#popup_container").remove();
    removeAlertCss();
    }, alertmsgduration); 
          } else {
                document.getElementById("allrequestlist").value=total;
                //document.getElementById("plistform").submit();
                  $('#datemodel2').modal( "show" );
          }
}

function requestmedicine1(){
  var expecteddate = document.getElementById("expecteddate").value;
  var warehouse_id = document.getElementById("warehouse_id").value;
  if(expecteddate==''){
             jAlert('error', "Please enter expected date!", 'Error Dialog');
    setTimeout(function() {
    $("#popup_container").remove();
    removeAlertCss();
    }, alertmsgduration); 
          } else {
                document.getElementById("expecteddate1").value =expecteddate ;
                document.getElementById("warehouse_id1").value= warehouse_id;
                document.getElementById("plistform").submit();
          }
}



 

function setCheck_stock_availableAccess(userid,status) {
	  uid= userid;
	  var url="setCheck_stock_availableAccessPharmacy?userid="+userid+"&status="+status+"";   	
	     	
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
     
		 req.onreadystatechange = setCheck_stock_availableAccessRequest;
		 req.open("GET", url, true); 
    
		 req.send(null);

}
function setCheck_stock_availableAccessRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
  
		var data=req.responseText;
		document.getElementById("checkavailabilitydiv").innerHTML=data;
	}
}
}



function setDirect_TransferAccess(userid,status) {
	  uid= userid;
	  var url="setDirect_TransferAccessPharmacy?userid="+userid+"&status="+status+"";   	
	     	
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
   
		 req.onreadystatechange = setDirect_TransferAccessRequest;
		 req.open("GET", url, true); 
  
		 req.send(null);

}
function setDirect_TransferAccessRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {

		var data=req.responseText;
		document.getElementById("directtransferdiv").innerHTML=data;
	}
}
}


function setInventory_ReportAccess(userid,status) {
	   uid= userid;
	   var url="setInventory_ReportAccessPharmacy?userid="+userid+"&status="+status+"";    
	       
	   if (window.XMLHttpRequest) {
	   req = new XMLHttpRequest();
	   }
	   else if (window.ActiveXObject) {
	  isIE = true;
	  req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	   
	   req.onreadystatechange = setInventory_ReportAccessRequest;
	   req.open("GET", url, true); 
	  
	   req.send(null);

	}

	function setInventory_ReportAccessRequest(){
	if (req.readyState == 4) {
	 if (req.status == 200) {

	  var data=req.responseText;
	  document.getElementById("inventoryreportdiv").innerHTML=data;
	 }
	}
	}


function deletePharmacyBill(val,isbill){
	document.getElementById("bill_no").value = val;
	document.getElementById("isbillorestimate").value = isbill;
	$('#deletemodel2').modal( "show" );
}

function deletePharmacyBill1(){
	var bill_no = document.getElementById("bill_no").value;
	var delete_reason = document.getElementById("delete_reason").value;
	var isbillorestimate = document.getElementById("isbillorestimate").value;
	
	if(delete_reason==''){
		jAlert('error', "Please enter cancel reason!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var t=window.confirm("Do you want to cancel bill?");
		   if(t==true){
			   $("#dashboardloaderPopup").modal("show");
		var url="deletepharmacyBillPharmacy?bill_no="+bill_no+"&delete_reason="+delete_reason+"&isbillorestimate="+isbillorestimate+"";  	  
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = deletePharmacyBill1Request;
		  req.open("GET", url, true); 
		  req.send(null);
		}
	}
}
function deletePharmacyBill1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}

  function enabledAll(){
	  
	  document.getElementById("fullname").readOnly=false;
	  document.getElementById("fullname").focus();
	  document.getElementById("mobile").readOnly=false;
	  document.getElementById("wardname").readOnly=false;
	  document.getElementById("doctor").readOnly=false;
	    
  }
  function openPharmacyUserProfile(){
		var url = "openpharmacyuserprofileDiaryMangent";

			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = openPharmacyUserProfileRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

		}
	function openPharmacyUserProfileRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					var str = req.responseText;
					var data = str.split("~");
					document.getElementById("user_fullname").value = data[0];
					document.getElementById("user_userid").value = data[1];
					document.getElementById("user_state").value = data[2];
					document.getElementById("user_location").value = data[3];
					document.getElementById("phonediv").innerHTML = data[4];
					document.getElementById("user_id").value = data[5];
					//document.getElementById("user_oldpwd").value = data[6];
					$('#myProfile').modal("show");
					 
			     }
			}
		}
	
	function updatePharmacyUser(){
		var oldpwd= document.getElementById("user_oldpassword").value;
		var mobile = document.getElementById("user_mobile").value;
		//var regEx = /^[0789]\d{10}$/;
		var regEx = /^\d{10}$/;
		
		if(mobile==''){
			jAlert('error', "Plz enter mobile no!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}else if(!regEx.test(mobile)){
			jAlert('error', "Plz enter valid mobile no!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}else if(oldpwd==''){
			jAlert('error', "Plz enter old password!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			
		}else{
			var url = "validateoldpharmacypwdDiaryMangent?oldpwd="+oldpwd+"";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			               
			req.onreadystatechange = updatePharmacyUserRequest;
			req.open("GET", url, true); 
			req.send(null);

		}
	}
	function updatePharmacyUserRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					var str = req.responseText;
					if(str=='0'){
						jAlert('error', "Plz enter valid old password!", 'Error Dialog');	
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
					}else{
						updatePharmacyUser1();
					}
				}
			}
		}
	
	function updatePharmacyUser1(){
		var new_pwd= document.getElementById("user_newpassword").value;
		var confirm_pwd = document.getElementById("user_confirmpassword").value;
		var mobile = document.getElementById("user_mobile").value;
		var decimal=  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
		
		if(new_pwd==''){
			jAlert('error', "Plz enter new password!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			
		}else if(confirm_pwd==''){
			jAlert('error', "Plz enter confirm password!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			
		}else if(!new_pwd.match(decimal)){
			document.getElementById("user_newpassword").value='';
			document.getElementById("user_confirmpassword").value='';
			jAlert('error', "Plz enter password in valid format!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			
		}else if(confirm_pwd!=new_pwd){
			document.getElementById("user_newpassword").value='';
			
			jAlert('error', "New password and confirm password not match !", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			
		}else{
			var url = "sendotpforchangepasswordDiaryMangent?mobile="+mobile+"";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			               
			req.onreadystatechange = updatePharmacyUser1Request;
			req.open("GET", url, true); 
			req.send(null);

		}
	}
	function updatePharmacyUser1Request(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					var str = req.responseText;
					document.getElementById("newotp").value=str;
					$('#myProfile').modal( "hide" );
					
					$('#otpmodel').modal("show");
				}
			}
		}
	
	function confirmotp(){
		var otp = document.getElementById("otp").value;
		var oldotp = document.getElementById("newotp").value;
		var userid= document.getElementById("user_userid").value;
		var confirm_pwd = document.getElementById("user_confirmpassword").value;
		var mobile = document.getElementById("user_mobile").value;
		if(otp==''){
			jAlert('error', "Plz enter otp!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		
		}else if(otp!=oldotp){
			jAlert('error', "Plz enter valid OTP!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			document.getElementById("otp").value='';
			$('#otpmodel').modal("show");
		}else{
			var url = "updatepharmacyuserpwdDiaryMangent?mobile="+mobile+"&userid="+userid+"&confirm_pwd="+confirm_pwd+"";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			req.onreadystatechange = confirmotpRequest;
			req.open("GET", url, true); 
			req.send(null);
		}
	}
	function confirmotpRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					window.location = 'Logout';
				}
			}
		}
 
	function setReturn_StockAccess(userid,status) {
		  uid= userid;
		  var url="setReturn_StockAccessPharmacy?userid="+userid+"&status="+status+"";   	
		     	
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   
			 req.onreadystatechange = setReturn_StockAccessRequest;
			 req.open("GET", url, true); 
	  
			 req.send(null);

	}
	function setReturn_StockAccessRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {

			var data=req.responseText;
			document.getElementById("returnstockdiv").innerHTML=data;
		}
	}
	}
	
	
	
	function setCancel_IndentAccess(userid,status) {
		  uid= userid;
		  var url="setcancel_indentaccessPharmacy?userid="+userid+"&status="+status+"";   	
		     	
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   
			 req.onreadystatechange = setCancel_IndentAccessRequest;
			 req.open("GET", url, true); 
	  
			 req.send(null);

	}
	function setCancel_IndentAccessRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("cancelindentdiv").innerHTML=data;
		}
	}
	}
	function setReturn_MedicineAccess(userid,status) {
		  uid= userid;
		  var url="setreturn_medicineaccessPharmacy?userid="+userid+"&status="+status+"";   	
		     	
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   
			 req.onreadystatechange = setReturn_MedicineAccessRequest;
			 req.open("GET", url, true); 
	  
			 req.send(null);

	}
	function setReturn_MedicineAccessRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("medretauthdiv").innerHTML=data;
		}
	}
	}
	
	function setAllMedicines(clientid,ispharmacy){
	
		 document.getElementById("clientid").value=clientid;
		 document.getElementById("ispharmacy").value=ispharmacy;
		 var url="setallmedicinesPharmacy?clientid="+clientid+"&ispharmacy="+ispharmacy+"";
		 if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   
		req.onreadystatechange = setAllMedicinesRequest;
		req.open("GET", url, true); 
	    req.send(null);
	}
	function setAllMedicinesRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				 document.getElementById("plist").innerHTML=req.responseText;
			     $("#newmedicine").trigger("chosen:updated");
			  	 $(".chosen").chosen({allow_single_deselect: true});
				
			}
		}
	}
	
	
	
	function addnewreturnRow(tableID){
	     counts= Number(document.getElementById("tempcount").value);
	     var qty=document.getElementById("qty").value;
	     var chargeid=document.getElementById("newmedicine").value;
	     tmid=chargeid;
	     if(chargeid=='0'){
	                jAlert('error', "please select product!", 'Error Dialog');
					
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
						  
	       
	     } else if(qty==''){
	                jAlert('error', "please enter return quantity!", 'Error Dialog');
					
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
						  
	       
	     } else {
	      	var table = document.getElementById(tableID);
			var rowCount = table.rows.length;
			row=table.insertRow(rowCount);
	    	var url="addtoreturnPharmacy?chargeid="+chargeid+"&count="+counts+"&qty="+qty+"";
	    	document.getElementById("qty").value = '';
	        if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	        req.onreadystatechange = addnewreturnRowRequest;
			req.open("GET", url, true); 
			req.send(null);
	     } 

	}
	
	function addnewreturnRowRequest() {
		  
	     if (req.readyState == 4) {
				         if (req.status == 200) {     
			
				         	var dd= req.responseText;  
			
			   		       var flag=false;
			   				$('.pclass').each(function() {
									var i=this.value;
									var tid= document.getElementById("id"+i).value;
									if(tid==tmid){
									    flag=true;
									}
								});	
			   				if(flag){
			   				 
			   				 		jAlert('error', "This product already added!", 'Error Dialog');
					
										setTimeout(function() {
											$("#popup_container").remove();	
											removeAlertCss();
										}, alertmsgduration);   
			   				 
			   				} else {
			   				
					   				    row.innerHTML=dd;
					           		    counts++;
									 	document.getElementById("tempcount").value=counts;	  
									 	calRefundTotaltemp();
					                    
			   				}
			   				
			   				
			   		
				         }
	         }
	  }
	
	
	  function calBalDiscount(){
		  
		   var disctp= document.getElementById("disctp").value;
		   var discrate= Number(document.getElementById("discrate").value);
		   var total= document.getElementById("bal1").value;
		   var discount=0;
		   total=roundTwo(total);
		   if(disctp=='1'){
			   //per
			   discount = roundTwo(total*discrate/100);
		   } else {
			   //cash
			   discount=roundTwo(discrate);
		   }
		   
		   
		   /*document.getElementById("discamt").value=discount;*/
		   document.getElementById("discamt").value=0;
		   
		   /*var finalpayamt= total-discount;*/
		   var finalpayamt= total;
		   document.getElementById("finalpayamt").value=roundTwo(finalpayamt); 
		   
	  }
	
	
	  function setIndent_approveAccess(userid,status) {
		  uid= userid;
		  var url="setindent_approveaccessPharmacy?userid="+userid+"&status="+status+"";   	
		     	
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   
			 req.onreadystatechange = setIndent_approveAccessRequest;
			 req.open("GET", url, true); 
	  
			 req.send(null);

	}
	function setIndent_approveAccessRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("indentapprovediv").innerHTML=data;
		}
	}
	}
	
	
	
	
	function addnewRowIpd(tableID){

	      counts= Number(document.getElementById("tempcount").value);
	  
	     var qty=document.getElementById("qty").value;
	     var newmid=document.getElementById("newmedicine").value;
	     tmid=newmid;
	     if(newmid=='0'){
	                jAlert('error', "please select product!", 'Error Dialog');
					
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
						  
	       
	     }
	     else if(qty==''){
	                jAlert('error', "please enter required quantity!", 'Error Dialog');
					
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
						  
	       
	     } else {
	      
	           
	    		var table = document.getElementById(tableID);
				var rowCount = table.rows.length;
				row=table.insertRow(rowCount);
	    		/*var url="adnewmedicineipdPharmacy?pid="+newmid+"&count="+counts+"&qty="+qty+"";*/
				var hdnphclientid = document.getElementById("hdnphclientid").value;
				var hdnispharmacy = document.getElementById("hdnispharmacy").value;

				var url="adnewmedicinePharmacy?pid="+newmid+"&count="+counts+"&qty="+qty+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";
	    		document.getElementById("qty").value = '';

	              if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = updateAccountinvoiceRequest;
				    req.open("GET", url, true); 
				    req.send(null);
	     
	     } 
	     

	}
	
	
	function openMultipleRequestPopup(id) {
		  var url="openmultiplerequestPharmacy?id="+id+"";   	
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   	req.onreadystatechange = openMultipleRequestPopupRequest;
		req.open("GET", url, true); 
	  	req.send(null);
	}
	function openMultipleRequestPopupRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("priscrequesttbody").innerHTML=data;
			$('#priscrequestpopup').modal("show");
		}
	}
}

	function setPO_approveAccess(userid,status) {
		  uid= userid;
		  var url="setpo_approveaccessPharmacy?userid="+userid+"&status="+status+"";   	
		     	
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   
			 req.onreadystatechange = setPO_approveAccessRequest;
			 req.open("GET", url, true); 
	  
			 req.send(null);

	}
	function setPO_approveAccessRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("poapprovediv").innerHTML=data;
		}
	}
	}
	
	
	function setCancelPoAccess(userid,status) {
		  uid= userid;
		  var url="setcancelpoaccessPharmacy?userid="+userid+"&status="+status+"";   	
		     	
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   
			 req.onreadystatechange = setCancelPoAccessRequest;
			 req.open("GET", url, true); 
	  
			 req.send(null);

	}
	function setCancelPoAccessRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("cancelpo1").innerHTML=data;
		}
	}
	}
	
	function setPurchaseeditAccess(userid,status) {
		  uid= userid;
		  var url="setpurchaseeditaccessPharmacy?userid="+userid+"&status="+status+"";   	
		     	
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   
			 req.onreadystatechange = setPurchaseeditAccessRequest;
			 req.open("GET", url, true); 
	  
			 req.send(null);

	}
	function setPurchaseeditAccessRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("editpurchase").innerHTML=data;
		}
	}
	}
	
	function setpharm_print_backdate(userid,status) {
		  uid= userid;
		  var url="setpharm_print_backdatePharmacy?userid="+userid+"&status="+status+"";   	
		     	
		  if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	   
			 req.onreadystatechange = setpharm_print_backdateRequest;
			 req.open("GET", url, true); 
	  
			 req.send(null);

	}
	function setpharm_print_backdateRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("pharm_bkdt").innerHTML=data;
		}
	}
	}
	
	
	function calSingleBalDiscount(){
		  
		   var disctp= document.getElementById("singlebilldisctype").value;
		   var discrate= Number(document.getElementById("singlebilldisc").value);
		   var total= document.getElementById("bal").value;
		   var discount=0;
		   total=roundTwo(total);
		   if(disctp=='1'){
			   //per
			   discount = roundTwo(total*discrate/100);
		   } else {
			   //cash
			   discount=roundTwo(discrate);
		   }
			var discinper = 0;
		   	if(disctp=='1'){
		         //%
				 discinper =discrate;
		    }
		    if(disctp=='0'){
		         //rs to %
		    	 discinper = (discrate/total)*100;
			}
		    discinper=roundTwo(discinper);
		    /*document.getElementById("discinper1").value =discinper;*/
		    document.getElementById("discinper1").value =0;
		   
		   /*document.getElementById("singlediscamt").value=discount;*/
		    document.getElementById("singlediscamt").value="0";
		   
		   /*var finalpayamt= total-discount;*/
		   var finalpayamt= total;
		   document.getElementById("singlefinalpayamt").value=roundTwo(finalpayamt); 
		  
	  }
	
	
	function showpartpaymentpatient(billno) {
		var url="showpartpaymentreciptPharmacy?billno="+billno+"";
	
        if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		}
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
           
			req.onreadystatechange = showpartpaymentpatientRequest;
			req.open("GET", url, true); 
          
			req.send(null);
}
function showpartpaymentpatientRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
	    
			var data=req.responseText;
			document.getElementById("parttimepaymenttbody").innerHTML=data;
			
			$('#parttimepaymentlist').modal( "show" );
		}
}
}


function refundMedicineWithBill() {
    var returnmode= document.getElementById("returnmode").value;
    document.getElementById("paymode").value=returnmode;
	var total=document.getElementById("tempcount").value;
	var flag=true;
	$('.pclass').each(function() {
		var i=this.value;
		var sale=document.getElementById("returnqty"+i).value;		   
		if(sale==''){
			 flag=false;
		     jAlert('error', "Please enter return quantity!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
				}, alertmsgduration); 
			  }  
			}); 
  	 	if(flag){
  	 		document.getElementById("creditform").action="Pharmacy";
		 	document.getElementById("creditform").submit();
		}
}	

///getMultiBillList

function getMultiBillList(paymode) {
	var clientid = document.getElementById("hdnphclientid").value;
	var paymodereturn = document.getElementById("paymodereturn").value;
	var ispharmacy = document.getElementById("hdnispharmacy").value;
	
	if(clientid==''){
		document.getElementById("paymodereturn").value ="0";
		jAlert('error', "please select patient!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(paymodereturn!='0'){
		var url="setmultireportPharmacy?clientid="+clientid+"&paymodereturn="+paymodereturn+"&ispharmacy="+ispharmacy+"";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
		   isIE = true;
		   req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
        req.onreadystatechange = getMultiBillListRequest;
		req.open("GET", url, true); 
        req.send(null);
	}
}
function getMultiBillListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("innerTableBody").innerHTML=data;
		}
	}
}


function returnToBillPage() {
	var flag=false;
	var multireturnbillid='';
	$('.returnbillclass').each(function() { // loop through each checkbox
			if (this.checked == true) {
				if(multireturnbillid==''){
					multireturnbillid = this.value;
				}else{
					multireturnbillid =multireturnbillid+","+ this.value;
				}
				flag = true;
			}
	});
	document.getElementById("multireturnbillid").value=multireturnbillid;
	
	if(flag){
		document.getElementById("creditform").action="returnipdmedicinePharmacy";
		document.getElementById("creditform").submit();
	}else{
		 jAlert('error', "please select at least one bill no.!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			  
	}
}	

function settempmedtoper(id){
	
	var url="settopermanatPharmacy?id="+id;
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
	   isIE = true;
	   req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	
	
    req.onreadystatechange =settempmedtoperRequest;
   
	req.open("GET", url, true); 
    req.send(null);
	
}

function settempmedtoperRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 window.location.reload();	
		}
	}
}


function showpatientpaymentmode(billno) {
	var url="showpatientpaymentmodePharmacy?billno="+billno+"";

    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	}
	  else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
       
		req.onreadystatechange = showpatientpaymentmodeRequest;
		req.open("GET", url, true); 
      
		req.send(null);
}
function showpatientpaymentmodeRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
    
		var data=req.responseText;
		document.getElementById("patientpaymentmodeltbody").innerHTML=data;
		
		$('#patientpaymentmodel').modal( "show" );
	}
}
}
function changePaymentMode(id,amt,paymode) {
	document.getElementById("changepaymode").value=paymode;
	document.getElementById("change_paymode_id").value=id;
	document.getElementById("chngepaymodeamt").innerHTML="Rs."+amt;
	$('#changepaymodemodels').modal( "show" );	
}

function changePaymodeNew(val) {
    
    if(val=="Cash"){
          document.getElementById("changepaymodecard").className="form-control hidden";
          document.getElementById("changepaymodechequeno").className="form-control hidden";
          document.getElementById("changepaymodeneftid").className="form-control hidden";
    } else if(val=="Card"){
          document.getElementById("changepaymodecard").className="form-control";
          document.getElementById("changepaymodechequeno").className="form-control hidden";
          document.getElementById("changepaymodeneftid").className="form-control hidden";
          document.getElementById("changepaymodecard").focus();
    } else if(val=="Cheque"){
          document.getElementById("changepaymodecard").className="form-control hidden";
          document.getElementById("changepaymodeneftid").className="form-control hidden";
          document.getElementById("changepaymodechequeno").className="form-control";
          document.getElementById("changepaymodechequeno").focus();
       
    } else if(val=="NEFT"){
           document.getElementById("changepaymodecard").className="form-control hidden";
          document.getElementById("changepaymodeneftid").className="form-control";
          document.getElementById("changepaymodechequeno").className="form-control hidden";
          document.getElementById("changepaymodeneftid").focus();
          
    }  else {
       
          document.getElementById("changepaymodecard").className="form-control hidden";
          document.getElementById("changepaymodeneftid").className="form-control hidden";
          document.getElementById("changepaymodechequeno").className="form-control hidden";
    }
}
function checkCashmOde() {
	
	 var npopup = Number(document.getElementById("npopup").innerHTML);
	 
	 ///if(npopup<=0){
		 
	 //}
	 if(npopup<0){
		 
	 }else{
		 $("#dashboardloaderPopup").modal('show');
		 var paymode=document.getElementById("paytype").value; 
		 if(paymode=="Card"){
			 var discsmt = document.getElementById("discsmt").value;
			 var distype = document.getElementById("distype").value;
			 var discinper= document.getElementById("discinper").value;
			 var hdnphclientid = document.getElementById("hdnphclientid").value;
			 var hdnispharmacy = document.getElementById("hdnispharmacy").value;
			 var url="savecarddiscountamountPharmacy?discsmt="+discsmt+"&distype="+distype+"&discinper="+discinper+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";

			 if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			 }else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			 }   
		     req.onreadystatechange = checkCashmOdeRequest;
		     req.open("GET", url, true); 
		     req.send(null);
		 }else{
			 
			 submitformsale();
		 }
	 }
	
}

function checkCashmOdeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
	    	submitformsale();
		}
	}
}


function submitformsale(){
			document.getElementById("salebutton").className="hidden";
			document.getElementById("creditform").submit();
		
	
}

function getModewiseSingleBill(clientid,ispharmacy) {
	document.getElementById("innerTableBody").innerHTML="<tr></tr>";
	document.getElementById("plist").innerHTML="";
	document.getElementById("returnpaymodeselect").value=0;
	document.getElementById("clientid").value=clientid;
	document.getElementById("ispharmacy").value=ispharmacy;
	calRefundTotaltemp();
}
function getModewiseMultiBillList(payode){
	 var clientid= document.getElementById("clientid").value;
	 var ispharmacy =document.getElementById("ispharmacy").value;
	 if(clientid==''){
		 jAlert('error', "Please select patient!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			document.getElementById("returnpaymodeselect").value="0";
	 }else if(payode=='0'){
		 jAlert('error', "Please select return paymode!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			document.getElementById("returnmode").value="0";
			document.getElementById("plist").innerHTML='';
	 }else{
		 document.getElementById("innerTableBody").innerHTML="<tr></tr>";
		 document.getElementById("returnmode").value=payode;
		 if(payode=='Credit'){
			 var selectobject=document.getElementById("returnmode");
			 var flagreturntest=false;
			 for (var i=0; i<selectobject.length; i++){
			 if (selectobject.options[i].value == 'Credit' )
				 flagreturntest=true;
			 }
			 
			 if(!flagreturntest){
				 var opt = document.createElement('option');
				 opt.value = 'Credit';
				 opt.innerHTML = 'CREDIT';
				 selectobject.appendChild(opt);
			 }
			 document.getElementById("returnmode").value=payode;
			 document.getElementById("returnmode").disabled = true;
		 }else{
			 document.getElementById("returnmode").disabled = false;
			 var selectobject=document.getElementById("returnmode");
			 for (var i=0; i<selectobject.length; i++){
			 if (selectobject.options[i].value == 'Credit' )
			    selectobject.remove(i);
			 }
		 }
		 
		 if(payode=="Cash"){
	            document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control hidden";
	      } else if(payode=="Card"){
	            document.getElementById("card").className="form-control";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control hidden";
	            document.getElementById("card").focus();
	      } else if(payode=="Cheque"){
	            document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control";
	            document.getElementById("chequeno").focus();
	      }else if(payode=="NEFT"){
	             document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control";
	            document.getElementById("chequeno").className="form-control hidden";
	            document.getElementById("neftid").focus();
	      } else if(payode=="Credit") {
	            document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control hidden";
	      } else {
	            document.getElementById("card").className="form-control hidden";
	            document.getElementById("neftid").className="form-control hidden";
	            document.getElementById("chequeno").className="form-control hidden";
	            document.getElementById("returnmode").value='0';
	      }
		 
		 var url="setallmedicinesmodewisePharmacy?clientid="+clientid+"&ispharmacy="+ispharmacy+"&paymode="+payode+"";
		 if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	  
		req.onreadystatechange = getModewiseMultiBillListRequest;
		req.open("GET", url, true); 
	   req.send(null); 
	 }
}
function getModewiseMultiBillListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 document.getElementById("plist").innerHTML=req.responseText;
		     $("#newmedicine").trigger("chosen:updated");
		  	 $(".chosen").chosen({allow_single_deselect: true});
/*		  	 calRefundTotaltemp();*/
		 	addtempreturndata();
		}
	}
}

function deleteRowFromSession(r,chargesessionid,count,chargetempid){
	/*var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("mytable").deleteRow(i);*/
	var hdnphclientid = document.getElementById("hdnphclientid").value;
	var hdnispharmacy = document.getElementById("hdnispharmacy").value;
	 var url="deleteproductfromsessionPharmacyAjax?chargesessionid="+chargesessionid+"&chargetempid="+chargetempid+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	 }else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	req.onreadystatechange = deleteRowFromSessionRequest;
	req.open("GET", url, true); 
	req.send(null);    
}
function deleteRowFromSessionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("innerTableBody").innerHTML=req.responseText;
			calsubTotal();
		}
	}
}

function changeQtyFromSale(count,chargesessionid,chargetempid){
	try {
	 	var pstock = Number(document.getElementById("prodd"+count).value);
	    var sale=document.getElementById("sale"+count).value;
	    var freeqty=document.getElementById("freeqty"+count).value;
	    var discount_perprodt=document.getElementById("discount"+count).value;
	    var isnumberqty = checkNumberOrNot(sale);
	    
	    if(!isnumberqty){
	    	sale =0;
	    }else{
	    	sale = Number(sale);
	    }
	    var saleqty=false;
	    if(sale<=0){
	    	saleqty = true;
	    	document.getElementById("sale"+count).value=0;
	    	jAlert('error', "please enter sale qty!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	    }else if(pstock<sale){
	    	saleqty = true;
	    	document.getElementById("sale"+count).value=0;
	    	jAlert('error', "Entered qty is greater than available instock("+pstock+"). So entered qty change from "+sale+" to 0. Please enter valid qty!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, 3000); 
		}
		if(saleqty){
			sale =0;
			setTimeout(function(){  
				var hdnphclientid = document.getElementById("hdnphclientid").value;
				var hdnispharmacy = document.getElementById("hdnispharmacy").value;
			    var url="changeproductfromsessionPharmacyAjax?chargesessionid="+chargesessionid+"&saleqty="+sale+"&chargetempid="+chargetempid+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"&freeqty="+freeqty+"&discount_perprodt="+discount_perprodt+"";
				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
				}else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
				req.onreadystatechange = changeQtyFromSaleRequest;
				req.open("GET", url, true); 
				req.send(null);    
				
			}, 1500);
		}else{
				var hdnphclientid = document.getElementById("hdnphclientid").value;
				var hdnispharmacy = document.getElementById("hdnispharmacy").value;
			    var url="changeproductfromsessionPharmacyAjax?chargesessionid="+chargesessionid+"&saleqty="+sale+"&chargetempid="+chargetempid+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"&freeqty="+freeqty+"&discount_perprodt="+discount_perprodt+"";
				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
				}else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
				req.onreadystatechange = changeQtyFromSaleRequest;
				req.open("GET", url, true); 
				req.send(null);    
		}
		
		
	} catch (e) {
		  window.location.reload();	      
	}
}
function changeQtyFromSaleRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("innerTableBody").innerHTML=req.responseText;
			calsubTotal();
		}
	}
}

function addsalenewRowJson(tableID){
	try {
		
	   counts= Number(document.getElementById("tempcount").value);
	   var qty=document.getElementById("qty").value;
	   var newmid=document.getElementById("newmedicine").value;
	   var fullname=document.getElementById("fullname").value;
	   tmid=newmid;
	   //$('#newmedicine select:first').focus();
	   var isnumberqty = checkNumberOrNot(qty);
	   if(newmid=='0'){
	          jAlert('error', "please select product!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
		 } else if(!isnumberqty){
			  jAlert('error', "please enter valid quantity!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
		 }else if(qty=='0'){
	          jAlert('error', "please enter required quantity!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
	   } else if(fullname==''){
		     jAlert('error', "Please enter fullname!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				isError=true;		
	   }else{
		   var flag=false;
		    $('.pclass').each(function() {
				var i=this.value;
				var tid= document.getElementById("product_id"+i).value;
				if(tid==newmid){
					 flag=true;
				}
			});	
			if(flag){
			 		jAlert('error', "This product already added!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();	
						removeAlertCss();
					}, alertmsgduration);   
			} else {
				/*var table = document.getElementById(tableID);
				var rowCount = table.rows.length;
				row=table.insertRow(rowCount);*/
				var hdnphclientid = document.getElementById("hdnphclientid").value;
				var hdnispharmacy = document.getElementById("hdnispharmacy").value;
				//var url="adnewmedicinePharmacy?pid="+newmid+"&count="+counts+"&qty="+qty+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";
				var isbarcodeproductsale=0;
				var dataObj={
					  	"pid":""+newmid+"",
						"count":""+counts+"",
						"qty":""+qty+"",
						"hdnphclientid":""+hdnphclientid+"",
						"hdnispharmacy":""+hdnispharmacy+"",
						"isbarcodeproductsale":""+isbarcodeproductsale+"",
				 };
				var data1 =  JSON.stringify(dataObj);
				$.ajax({
				   url : "adnewmedicinePharmacyAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   success : function(data) {
					  var dd=data.productlist;
			       		if(dd=="0"){
			       				jAlert('error', "Barcode not generated!", 'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration); 		             		 	     
			       		}else if(dd=="1"){
			       			var avilstock=data.avilstock;
			       			jAlert('error', "This product has been sold just now, please enter quantity less then the currenly Instock ("+avilstock+").", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, 3000); 		             		 	     
			       		}else if(dd=="2"){
			       		 /* row.innerHTML=dd;
			       		      counts++;
			       		      document.getElementById("tempcount").value=counts;	*/  
			       			addtempsaledata();	             		 	     
			       		}else if(dd=="3"){
			       			jAlert('error', "This product is in adjustment process!", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, 3000); 		              		 	     
			       		}else if(dd=="4"){
			       			jAlert('error', "This product already added!", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, 3000); 		              		 	     
			       		}else {
			       			window.location.reload();	   
						}
				   },
				   error : function(result) {
					   jAlert('error', "Something wrong!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				   }
				}); 
				
			}
	   } //$('#newcataloguesaleid option:selected').focus();
            
			
                // document.getElementById("newcataloguesaleid").style.backgroundColor="#FFFF00";
		        $("#newcataloguesaleid").trigger("chosen:activate");
                document.getElementById("qty").classList.remove("focusdrop");
                document.getElementById("cataloguewiselistid").classList.add("focusdrop");
                //alert(document.activeElement.tabIndex);
				//$(".chosen").chosen({allow_single_deselect: true});
			     //Element("newcataloguesaleid").ClassNames.add("chosen");
                  
			     //newcataloguesaleid.classList.add("chosen");
	} catch (e) {
		  window.location.reload();	      
	}
}



function checkBarcodeRequest(newpid){
	try {
		var nonsystembarcodemew = document.getElementById("nonsystembarcode").checked;
		var nonsystembarcode =0;
		if(nonsystembarcodemew==true){
			nonsystembarcode = 1;
		}
		var flagerror = false;
		if(nonsystembarcode==0){
			var isnumberpid = checkNumberOrNot(newpid);
			if(newpid==''){
				/* jAlert('error', "please select valid barcode!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);*/
				flagerror = true;
			}else if(!isnumberpid){
				  jAlert('error', "please select valid barcode!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					flagerror = true;
			 }else if(newpid=='0'){
				  jAlert('error', "please select valid barcode!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					flagerror = true;
			 }
		}else if(newpid==''){
			flagerror = true;
		}
		
		 
		 if(!flagerror){
    		var dataObj={
				"pid":""+newpid+"",
				"nonsystembarcode":""+nonsystembarcode+"",
			};
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "checkbarcodedataPharmacyAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				   	var dd=data.productlist;
		       		if(dd=="0"){
		       				jAlert('error', "Barcode not generated!", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 		             		 	     
		       		}else if(dd=="-2"){
		       			jAlert('error', "Barcode not store in system!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 	
					}else if(dd=="-3"){
		       			jAlert('error', "Stock not avilable of that product!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 	
					}else{
						addBarcodeRequest(dd);
					}
			   },
			   error : function(result) {
				   jAlert('error', "Something wrong!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }
			}); 
		}
	} catch (e) {
		  window.location.reload();	      
	}
}



var tmid=0;
//barcode code
function addBarcodeRequest(newpid){
	try {
		
		var isnumberpid = checkNumberOrNot(newpid);
		if(newpid==''){
			 jAlert('error', "please select valid barcode!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(!isnumberpid){
			  jAlert('error', "please select valid barcode!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
		 }else if(newpid=='0'){
			  jAlert('error', "please select valid barcode!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
		 }else{
		   counts= Number(document.getElementById("tempcount").value);
		   var qty=1;
		   document.getElementById("qty").value = '';
		   document.getElementById("barcode").value = "";
		   document.getElementById("barcode").focus(); 
		   var newmid=newpid;
		   var fullname=document.getElementById("fullname").value;
		   tmid=newmid;
		   if(newmid=='0'){
		          jAlert('error', "please select product!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
			 } else if(qty=='0'){
		          jAlert('error', "please enter required quantity!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
		   } else if(fullname==''){
			     jAlert('error', "Please enter fullname!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					isError=true;		
		   }else{
			   var flag=false;
			    $('.pclass').each(function() {
					var i=this.value;
					var tid= document.getElementById("product_id"+i).value;
					if(tid==newmid){
						 flag=true;
					}
				});	
				if(flag){
				 		jAlert('error', "This product already added!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();	
							removeAlertCss();
						}, alertmsgduration); 
						
				} else {
					var hdnphclientid = document.getElementById("hdnphclientid").value;
					var hdnispharmacy = document.getElementById("hdnispharmacy").value;
					var isbarcodeproductsale=1;
					 var dataObj={
						  	"pid":""+newmid+"",
							"count":""+counts+"",
							"qty":""+qty+"",
							"hdnphclientid":""+hdnphclientid+"",
							"hdnispharmacy":""+hdnispharmacy+"",
							"isbarcodeproductsale":""+isbarcodeproductsale+"",
					 };
					var data1 =  JSON.stringify(dataObj);
					$.ajax({
					   url : "adnewmedicinePharmacyAjax",
					   data : data1,
					   dataType : 'json',
					   contentType : 'application/json',
					   type : 'POST',
					   async : true,
					   success : function(data) {
						  var dd=data.productlist;
				       		if(dd=="0"){
				       				jAlert('error', "Barcode not generated!", 'Error Dialog');
									setTimeout(function() {
										$("#popup_container").remove();
										removeAlertCss();
									}, alertmsgduration); 		             		 	     
				       		}else if(dd=="1"){
				       			var avilstock=data.avilstock;
				       			jAlert('error', "This product has been sold just now, please enter quantity less then the currenly Instock ("+avilstock+").", 'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, 3000); 		             		 	     
				       		}else if(dd=="2"){
				       			addtempsaledata();	             		 	     
				       		}else if(dd=="3"){
				       			jAlert('error', "Product in adjustment process!", 'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration); 	 		 	     
				       		}else {
				       			window.location.reload();	   
							}
					   },
					   error : function(result) {
						   jAlert('error', "Something wrong!", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					   }
					}); 
				}
		   } 
		}
	} catch (e) {
		  window.location.reload();	      
	}
}


function addtempsaledata(){
	try {
		var hdnphclientid = document.getElementById("hdnphclientid").value;
		var hdnispharmacy = document.getElementById("hdnispharmacy").value;
		 var dataObj={
				"hdnphclientid":""+hdnphclientid+"",
				"hdnispharmacy":""+hdnispharmacy+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "gettempsaledataPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			  var dd=data.productlist;
	       		if(dd=="0"){
	       				jAlert('error', "Barcode not generated!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 		             		 	     
	       		}else if(dd=="1"){
       				jAlert('error', "Entered qty greater than available stock!!!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 		             		 	     
	       		}else {
	       			document.getElementById("innerTableBody").innerHTML=dd;
	       		    /*  row.innerHTML=dd;
	       		      counts++;
	       		      document.getElementById("tempcount").value=counts;	  */
				      calsubTotal();
				}
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
	} catch (e) {
		window.location.reload();	      
	}
}

function checkNumberOrNot(inputtxt)
{
	 var numbers = /^[0-9]+$/;  
     
     if(inputtxt.match(numbers))  {
        return true;
     } else {
          return false;
     }
} 

function roundAbsceil(val){
	 
	  val =Math.ceil(val); 
	  
	     return val;
	}


function addReturnProductRowJson(tableID){
	try {
		 counts= Number(document.getElementById("tempcount").value);
	     var qty=document.getElementById("qty").value;
	     var chargeid=document.getElementById("newmedicine").value;
	     tmid=chargeid;
	     var isnumberqty = checkNumberOrNot(qty);
	     if(!isnumberqty){
			  jAlert('error', "please enter valid quantity!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
		 }else if(chargeid=='0'){
                jAlert('error', "please select product!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
		 } else if(qty=='' || qty=='0'){
                jAlert('error', "please enter return quantity!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
		 } else {
			 	//var url="addtoreturnPharmacy?chargeid="+chargeid+"&count="+counts+"&qty="+qty+"";
			 	/*var table = document.getElementById(tableID);
				var rowCount = table.rows.length;
				row=table.insertRow(rowCount);*/
				var hdnphclientid = document.getElementById("hdnphclientid").value;
				var hdnispharmacy = document.getElementById("hdnispharmacy").value;
				 var dataObj={
					  	"chargeid":""+chargeid+"",
						"count":""+counts+"",
						"qty":""+qty+"",
						"hdnphclientid":""+hdnphclientid+"",
						"hdnispharmacy":""+hdnispharmacy+"",
				 };
				var data1 =  JSON.stringify(dataObj);
				$.ajax({
				   url : "adnewretrunmedicinePharmacyAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   success : function(data) {
					  var dd=data.productlist;
			       		if(dd=="0"){
			       				jAlert('error', "Product already added!", 'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration); 		             		 	     
			       		}else if(dd=="1"){
		       				jAlert('error', "Entered qty greater than available stock!!!", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 		             		 	     
			       		}else if(dd=="2"){
			       			addtempreturndata();	             		 	     
			       		}else {
			       			window.location.reload();	   
						}
				   },
				   error : function(result) {
					   jAlert('error', "Something wrong!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				   }
				}); 
	   } 
	} catch (e) {
		  window.location.reload();	      
	}
}

function addtempreturndata(){
	try {
		var hdnphclientid = document.getElementById("hdnphclientid").value;
		var hdnispharmacy = document.getElementById("hdnispharmacy").value;
		 var dataObj={
				"hdnphclientid":""+hdnphclientid+"",
				"hdnispharmacy":""+hdnispharmacy+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "addtempreturndataPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			  var dd=data.productlist;
	       		if(dd=="0"){
	       				jAlert('error', "Barcode not generated!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 		             		 	     
	       		}else if(dd=="1"){
       				jAlert('error', "Entered qty greater than available stock!!!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 		             		 	     
	       		}else {
	       			document.getElementById("innerTableBody").innerHTML=dd;
	       		    /*  row.innerHTML=dd;
	       		      counts++;
	       		      document.getElementById("tempcount").value=counts;	  */
	       			  calRefundTotaltemp();
				}
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
	} catch (e) {
		window.location.reload();	      
	}
}


function changeQtyFromReturn(tempreturnid,chargeid){
	try {
		var previqty=document.getElementById("tempsale"+chargeid).value;
		var returnqty=document.getElementById("returnqty"+chargeid).value;    
		var isnumberpreqty = checkNumberOrNot(previqty);
		var isnumberretty = checkNumberOrNot(returnqty);
		if(!isnumberpreqty){
			previqty =0;
	    }else{
	    	previqty = Number(previqty);
	    }
		
		if(!isnumberretty){
			returnqty =0;
	    }else{
	    	returnqty = Number(returnqty);
	    }
		 
	    var saleqty=false;
	    if(returnqty<=0){
	    	saleqty = true;
	    	document.getElementById("returnqty"+chargeid).value=0;
	    	jAlert('error', "please enter return qty!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	    }else if(returnqty>previqty){
	    	saleqty = true;
		    document.getElementById("returnqty"+chargeid).value=0;
		      jAlert('error', "Retrun qty cant greater than sold qty!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			returnqty=0;	 
	    } 
		if(saleqty){
			returnqty =0;
		}
		var hdnphclientid = document.getElementById("hdnphclientid").value;
		var hdnispharmacy = document.getElementById("hdnispharmacy").value;
	    var url="changeproductretrunfromsessionPharmacyAjax?tempreturnid="+tempreturnid+"&returnqty="+returnqty+"&chargeid="+chargeid+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = changeQtyFromReturnRequest;
		req.open("GET", url, true); 
		req.send(null);    
	} catch (e) {
		  window.location.reload();	      
	}
}
function changeQtyFromReturnRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("innerTableBody").innerHTML=req.responseText;
			addtempreturndata();
		}
	}
}


function deleteReturnRowFromSession(tempreturnid,chargeid){
	/*var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("mytable").deleteRow(i);*/
	var hdnphclientid = document.getElementById("hdnphclientid").value;
	var hdnispharmacy = document.getElementById("hdnispharmacy").value;
	 var url="deletereturnproductfromsessionPharmacyAjax?tempreturnid="+tempreturnid+"&chargeid="+chargeid+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	 }else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	req.onreadystatechange = deleteReturnRowFromSessionRequest;
	req.open("GET", url, true); 
	req.send(null);    
}
function deleteReturnRowFromSessionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("innerTableBody").innerHTML=req.responseText;
			addtempreturndata();
		}
	}
}

function checkReturnmOde() {
	var npopup = Number(document.getElementById("npopup").innerHTML);
	 
	 if(npopup<=0){
		 
	 }else{
		 $("#dashboardloaderPopup").modal('show');
		 if(document.getElementById('returnbtn')){
			  document.getElementById('returnbtn').style.visibility='hidden';
		}
		document.getElementById("creditform").action="refundmedicinenewPharmacy";
		document.getElementById("creditform").submit();
	 }
	
}

function changepharmacybillno(billno,date){
	document.getElementById("changepopup_bill_div").innerHTML = billno;
	document.getElementById("changepopup_date").innerHTML = date;
	document.getElementById("changepopup_bill_no").value = billno;
	document.getElementById("dummybillno").value = '';
	document.getElementById("dummybilldate").value = '';
	$('#changebillnopopup').modal( "show" );
}

function validatechangedummybill(){
	try {
		var billno=document.getElementById("changepopup_bill_no").value;
		var dummybillno=document.getElementById("dummybillno").value;
		var dummybilldate=document.getElementById("dummybilldate").value;    
		var isnumberdummybillno = checkNumberOrNot(dummybillno);
		var errorflag = false;
		if(dummybillno==''){
			errorflag= true;
			jAlert('error', "Please enter new bill no!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}else if(!isnumberdummybillno){
			errorflag= true;
			jAlert('error', "Please enter valid bill no!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	    }else if(Number(dummybillno)<0){
	    	errorflag= true;
			jAlert('error', "Please enter new bill no greater than 0!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}else if(dummybilldate==''){
			errorflag= true;
			jAlert('error', "Please enter new bill date!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}
		if(!errorflag){
			var url="checkdummybillnoexistPharmacyAjax?billno="+billno+"&dummybillno="+dummybillno+"&dummybilldate="+dummybilldate+"";
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			req.onreadystatechange = validatechangedummybillRequest;
			req.open("GET", url, true); 
			req.send(null);    
		}
	   
		
	} catch (e) {
		  window.location.reload();	      
	}
}
function validatechangedummybillRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			if(data=='1'){
				jAlert('error', "Entered bill no already present!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}else{
				savechangedummybill();
			}
		}
	}
}

function savechangedummybill(){
	try {
		var billno=document.getElementById("changepopup_bill_no").value;
		var dummybillno=document.getElementById("dummybillno").value;
		var dummybilldate=document.getElementById("dummybilldate").value;    
		var isnumberdummybillno = checkNumberOrNot(dummybillno);
		var errorflag = false;
		if(dummybillno==''){
			errorflag= true;
			jAlert('error', "Please enter new bill no!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}else if(!isnumberdummybillno){
			errorflag= true;
			jAlert('error', "Please enter valid bill no!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	    }else if(Number(dummybillno)<0){
	    	errorflag= true;
			jAlert('error', "Please enter new bill no greater than 0!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}else if(dummybilldate==''){
			errorflag= true;
			jAlert('error', "Please enter new bill date!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}
		if(!errorflag){
			var url="savedummybillnoPharmacyAjax?billno="+billno+"&dummybillno="+dummybillno+"&dummybilldate="+dummybilldate+"";
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			req.onreadystatechange = savechangedummybillRequest;
			req.open("GET", url, true); 
			req.send(null);    
		}else{
			
		}
	   
		
	} catch (e) {
		  window.location.reload();	      
	}
}
function savechangedummybillRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			window.location.reload();	      
		}
	}
}


function changePriceFromSale(count,chargesessionid,chargetempid,sale_price){
	try {
		//var sale_price =document.getElementById("mrp"+count).value;
		if(sale_price==''){
	    	document.getElementById("mrp"+count).value=0;
	    	jAlert('error', "please enter sale price!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	    }else if(parseFloat(sale_price)<=0){
	    	document.getElementById("mrp"+count).value=0;
	    	jAlert('error', "please enter sale price!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	    }else{
	    	var hdnphclientid = document.getElementById("hdnphclientid").value;
			var hdnispharmacy = document.getElementById("hdnispharmacy").value;
		    var url="changeproductfromsessionsalepricePharmacyAjax?chargesessionid="+chargesessionid+"&sale_price="+sale_price+"&chargetempid="+chargetempid+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			req.onreadystatechange = changePriceFromSaleRequest;
			req.open("GET", url, true); 
			req.send(null);    
	    }
		
		
	} catch (e) {
		  window.location.reload();	      
	}
}
function changePriceFromSaleRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("innerTableBody").innerHTML=req.responseText;
			calsubTotal();
		}
	}
}


function applypharmacydiscount(billno){
	try {
		 var dataObj={
				"billno":""+billno+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "getpharmdatafordiscountPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   var debit=data.debit;
			   var clientid=data.clientid;
			   var pclientid=data.pclientid;
			   var billno=data.billno;
			   var paymode=data.paymode;
			   var balance=data.balance;
			   document.getElementById("requestdisc_bill_div").innerHTML = billno;
			   document.getElementById("requestdisc_total_p").innerHTML = debit;
			   document.getElementById("requestdisc_totalamt").value = debit;
			   document.getElementById("requestdisc_billno").value = billno;
			   document.getElementById("requestdisc_disctype").value = '0';
			   document.getElementById("requestdisc_amt").value = '0';
			   document.getElementById("requestdisc_paymode").innerHTML = paymode;
			   document.getElementById("requestdisc_balance").innerHTML = balance;
			   document.getElementById("requestdisc_balanceamt").value = balance;
			   if(paymode=='Credit'){
				   document.getElementById("requestdiscbutn").disabled =false;
			   }else{
				   document.getElementById("requestdiscbutn").disabled =true;
			   }
			   document.getElementById("requestdiscnewbutn").disabled =false;
			   $('#requestdiscopopup').modal( "show" );
	       },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
	} catch (e) {
		window.location.reload();	      
	}
}

function requestdiscount() {
	try {
		document.getElementById("requestdiscbutn").disable=true;
	     var distype=document.getElementById("requestdisc_disctype").value;
	     var subtotal=parseFloat(document.getElementById("requestdisc_totalamt").value);
	     var requestdisc_amt = document.getElementById("requestdisc_amt").value;
	     var discinper = 0;
	     var discperbyval=parseFloat(requestdisc_amt);
	     var errorflag = false;
	     if(requestdisc_amt==''){
	    	 errorflag= true;
	    	 document.getElementById("requestdiscbutn").disabled =false;
	    	 jAlert('error', 'Please enter discount', 'Error Dialog');
	      		setTimeout(function() {
	      			$("#popup_container").remove();
	      			removeAlertCss();
	      		}, alertmsgduration);
	     }else if(parseFloat(requestdisc_amt)<0){
	    	 errorflag= true;
	    	 document.getElementById("requestdiscbutn").disabled =false;
	    	 jAlert('error', 'Please enter valid discount', 'Error Dialog');
	      		setTimeout(function() {
	      			$("#popup_container").remove();
	      			removeAlertCss();
	      		}, alertmsgduration);
	     }
	     if(distype=='0' && errorflag==false){
	         //%
	    	 discinper = discperbyval;
	    	 if(discperbyval>100){
	    	      	jAlert('error', 'Discount exceed above 100% .', 'Error Dialog');
	    	      		setTimeout(function() {
	    	      			$("#popup_container").remove();
	    	      			removeAlertCss();
	    	      		}, alertmsgduration);
	    	      		errorflag= true;
	    	      		document.getElementById("requestdiscbutn").disabled =false;
	    	 }else if(discperbyval<0 && errorflag==false){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscbutn").disabled =false;
	 	      	jAlert('error', 'Discount can not less than 0.', 'Error Dialog');
	      		setTimeout(function() {
	      			$("#popup_container").remove();
	      			removeAlertCss();
	      		}, alertmsgduration);
	      	 }else if(discperbyval<=10 && errorflag==false){
	      		errorflag= true;
	      		document.getElementById("requestdiscbutn").disabled =false;
	 	      	jAlert('error', 'Discount must be above 10% .', 'Error Dialog');
	      		setTimeout(function() {
	      			$("#popup_container").remove();
	      			removeAlertCss();
	      		}, alertmsgduration);
	      		
	    	 }/*else{
				 temp=subtotal*val/100;
				 discinper =val;
	    	 }*/
	     }
	     if(distype=='1' && errorflag==false){
	         //rs to %
	    	 discinper = (discperbyval/subtotal)*100;
	    	 if(discperbyval>subtotal){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscbutn").disabled =false;
	    		 jAlert('error', 'Amount exceed above Total .', 'Error Dialog');
	    		 setTimeout(function() {
	   			$("#popup_container").remove();
	   			removeAlertCss();
	    		 }, alertmsgduration);
	    	 }else if(discperbyval<0){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscbutn").disabled =false;
	    			jAlert('error', 'Discount can not less than 0.', 'Error Dialog');
	          		setTimeout(function() {
	          			$("#popup_container").remove();
	          			removeAlertCss();
	          		}, alertmsgduration);
	    	 }else if(discinper<0){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscbutn").disabled =false;
	 				jAlert('error', 'Discount can not less than 0.', 'Error Dialog');
	 				setTimeout(function() {
	 					$("#popup_container").remove();
	 					removeAlertCss();
	 				}, alertmsgduration);
			 }else if(discinper<=10){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscbutn").disabled =false;
	 				jAlert('error', 'Discount must be above 10% .', 'Error Dialog');
	 				setTimeout(function() {
	 					$("#popup_container").remove();
	 					removeAlertCss();
	 				}, alertmsgduration);
			 }/*else{
	    		 discinper = (val/subtotal)*100;
	   		}*/
	   	}
	     if(errorflag==false){
	    	 	var t=confirm("Are you sure?");
	    	    if(t==true){
	    	    	$("#dashboardloaderPopup").modal('show');
	    	    	document.getElementById("discinpernewdiscount").value = discinper;
	    	    	document.getElementById("requestdisc_typee").value = 1;
	   	    	 	savepharmacydiscountrequest();
	    	    }else{
	    	    	document.getElementById("requestdiscbutn").disabled =false;
	    	    } 
	    	 
	     }
	    
	    
		} catch (e) {
			window.location.reload();	      
		}
}

function requestdiscountupto10() {
	try {
		 document.getElementById("requestdiscnewbutn").disabled=true;
	     var distype=document.getElementById("requestdisc_disctype").value;
	     var subtotal=parseFloat(document.getElementById("requestdisc_totalamt").value);
	     var requestdisc_amt = document.getElementById("requestdisc_amt").value;
	     var requestdisc_balanceamt = document.getElementById("requestdisc_balanceamt").value;
	     var discinper = 0;
	     var discperbyval=parseFloat(requestdisc_amt);
	     var disscount=0;
	     var errorflag = false;
	     if(requestdisc_amt==''){
	    	 errorflag= true;
	    	 document.getElementById("requestdiscnewbutn").disabled =false;
	    	 jAlert('error', 'Please enter discount', 'Error Dialog');
	      		setTimeout(function() {
	      			$("#popup_container").remove();
	      			removeAlertCss();
	      		}, alertmsgduration);
	     }else if(parseFloat(requestdisc_amt)<0){
	    	 errorflag= true;
	    	 document.getElementById("requestdiscnewbutn").disabled =false;
	    	 jAlert('error', 'Please enter valid discount', 'Error Dialog');
	      		setTimeout(function() {
	      			$("#popup_container").remove();
	      			removeAlertCss();
	      		}, alertmsgduration);
	     }
	     if(distype=='0' && errorflag==false){
	         //%
	    	 discinper = discperbyval;
	    	 var discvalcheck = (discinper*subtotal)/100;
	    	 disscount = discvalcheck;
	    	 if(discperbyval>100){
	    	      	jAlert('error', 'Discount exceed above 100% .', 'Error Dialog');
	    	      		setTimeout(function() {
	    	      			$("#popup_container").remove();
	    	      			removeAlertCss();
	    	      		}, alertmsgduration);
	    	      		errorflag= true;
	    	      		document.getElementById("requestdiscnewbutn").disabled =false;
	    	 }else if(discperbyval<=0 && errorflag==false){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscnewbutn").disabled =false;
	 	      	jAlert('error', 'Discount can not less than 0.', 'Error Dialog');
	      		setTimeout(function() {
	      			$("#popup_container").remove();
	      			removeAlertCss();
	      		}, alertmsgduration);
	      	 }else if(discperbyval>10 && errorflag==false){
	      		errorflag= true;
	      		document.getElementById("requestdiscnewbutn").disabled =false;
	 	      	jAlert('error', 'Discount must be less than 10% .', 'Error Dialog');
	      		setTimeout(function() {
	      			$("#popup_container").remove();
	      			removeAlertCss();
	      		}, alertmsgduration);
	      		
	    	 }else if(parseFloat(discvalcheck)>parseFloat(requestdisc_balanceamt)){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscnewbutn").disabled =false;
	    		 jAlert('error', 'Amount exceed above balance .', 'Error Dialog');
	    		 setTimeout(function() {
	   			$("#popup_container").remove();
	   			removeAlertCss();
	    		 }, alertmsgduration);
	    	 }
	    	 
	    	 /*else{
				 temp=subtotal*val/100;
				 discinper =val;
	    	 }*/
	     }
	     if(distype=='1' && errorflag==false){
	         //rs to %
	    	 discinper = (discperbyval/subtotal)*100;
	    	 disscount = discperbyval;
	    	 if(discperbyval>parseFloat(requestdisc_balanceamt)){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscnewbutn").disabled =false;
	    		 jAlert('error', 'Amount exceed above balance .', 'Error Dialog');
	    		 setTimeout(function() {
	   			$("#popup_container").remove();
	   			removeAlertCss();
	    		 }, alertmsgduration);
	    	 }else if(discperbyval<=0){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscnewbutn").disabled =false;
	    			jAlert('error', 'Discount can not less than 0.', 'Error Dialog');
	          		setTimeout(function() {
	          			$("#popup_container").remove();
	          			removeAlertCss();
	          		}, alertmsgduration);
	    	 }else if(discinper<=0){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscnewbutn").disabled =false;
	 				jAlert('error', 'Discount can not less than 0.', 'Error Dialog');
	 				setTimeout(function() {
	 					$("#popup_container").remove();
	 					removeAlertCss();
	 				}, alertmsgduration);
			 }else if(discinper>10){
	    		 errorflag= true;
	    		 document.getElementById("requestdiscnewbutn").disabled =false;
	 				jAlert('error', 'Discount must be less than 10% .', 'Error Dialog');
	 				setTimeout(function() {
	 					$("#popup_container").remove();
	 					removeAlertCss();
	 				}, alertmsgduration);
			 }/*else{
	    		 discinper = (val/subtotal)*100;
	   		}*/
	   	}
	     if(errorflag==false){
	    	 	var t=confirm("Are you sure?");
	    	    if(t==true){
	    	    	$("#dashboardloaderPopup").modal('show');
	    	    	document.getElementById("discinpernewdiscount").value = discinper;
	    	    	document.getElementById("requestdisc_typee").value = 0;
	    	    	disscount = roundTwo(disscount);
	   	    	 	savepharmacydiscountrequest();
	    	    }else{
	    	    	document.getElementById("requestdiscnewbutn").disabled =false;
	    	    }
	    	 
	     }
	    
	    
		} catch (e) {
			window.location.reload();	      
		}
}
function changediscrequestval(val) {
	document.getElementById("requestdisc_amt").value=0;
}

function savepharmacydiscountrequest(){
	try {
		var billno = document.getElementById("requestdisc_billno").value;
		var debit =document.getElementById("requestdisc_totalamt").value;
		var requestdisc_disctype = document.getElementById("requestdisc_disctype").value;
		var requestdisc_amt =  document.getElementById("requestdisc_amt").value ;
		var discinper = document.getElementById("discinpernewdiscount").value ;
		var requestdisc_typee = document.getElementById("requestdisc_typee").value ;
		 var dataObj={
				"billno":""+billno+"",
				"debit":""+debit+"",
				"requestdisc_disctype":""+requestdisc_disctype+"",
				"requestdisc_amt":""+requestdisc_amt+"",
				"discinper":""+discinper+"",
				"requestdisc_typee":""+requestdisc_typee+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "requestdiscountPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   window.location.reload();	    
	       },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
	} catch (e) {
		window.location.reload();	      
	}
}
function approvedPharmacyDiscount(val,billno){
	 document.getElementById("isgroupdiscount").value="0";
	 document.getElementById("approveddiscount_id").value = val;
	 document.getElementById("approvebillno").value = billno;
	 $('#approvedmodel').modal( "show" );
}

function selectallapprovedisc(val) {
	if (val.checked == true) {
		$('.aprovealldiscclass').each(function() { 
			this.checked = true; 
		});
	} else {
		$('.aprovealldiscclass').each(function() { 
			this.checked = false; 
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
		 document.getElementById("approveddiscount_id").value = 0;
		 document.getElementById("approvebillno").value = 0;
		 $('#approvedmodel').modal( "show" );
	 }else{
	 	jAlert('error', 'Please select at least one checkbox for all approve!!!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	 }
	
}

var newallaprvenoerroe=false;
var error_message_number=0;
function approvedDiscountBill() {
	var approve_notes = document.getElementById("approve_notes").value;
	var billno = document.getElementById("approvebillno").value;
	
	if(approve_notes==''){
		jAlert('error', 'Please enter note', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		newallaprvenoerroe= false;
		var isgroupdiscount =document.getElementById("isgroupdiscount").value;
		 if(isgroupdiscount==0){
			 checkaproveordeleteofpharmacydiscreq(billno,0)
		 }else{
			 $("#dashboardloaderPopup").modal('show');
		 	 var allaprovediscountids="0";
			 $('.aprovealldiscclass').each(function() {
				if (this.checked == true) {
					var i = this.value;
					allaprovediscountids = allaprovediscountids+","+i;
					var newbillno =document.getElementById("disc_bill_idno"+ i).value;
					checkaproveordeleteofpharmacydiscreq(newbillno,1)
				}
			});
			if(!newallaprvenoerroe){
				 document.getElementById("aprovediscids").value = allaprovediscountids;
				 var flag = confirm("Are you sure?");
					if(flag){
					   $("#dashboardloaderPopup").modal('show');
					   document.getElementById("discountmodelform").submit();
					}
			}else{
				if(error_message_number==1){
					error_message_number=0;
					jAlert('error', 'Payment already taken of this bill. So not applying discount.', 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				}else if(error_message_number==2){
					error_message_number=0;
					jAlert('error', 'Product Return against this bill. So not applying discount.', 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				}
			}
		 }
		
	}
}
function checkaproveordeleteofpharmacydiscreq(billno,val){
	try {
		 var dataObj={
				"billno":""+billno+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "checkpaymentdoneagainstbillPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   var paymentstatus=data.paymentstatus;
			   if(paymentstatus==1){
				   error_message_number=1;
				   newallaprvenoerroe =true;
				   jAlert('error', 'Payment already taken of this bill. So not applying discount.', 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }else if(paymentstatus==2){
				   error_message_number=2;
				   newallaprvenoerroe =true;
				   jAlert('error', 'Product Return against this bill. So not applying discount.', 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }else{
				   if(val==0){
					    var flag = confirm("Are you sure?");
						if(flag){
						   $("#dashboardloaderPopup").modal('show');
						   document.getElementById("discountmodelform").submit();
						}
				   }
			   }
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
	} catch (e) {
		window.location.reload();	      
	}
}


function deletePharDiscRequest(val,id){
	var flag = confirm("Are you sure?");
	if(flag){
		  $("#dashboardloaderPopup").modal('show');
		 	var url="deletepharmacydiscrequestPharmacyAjax?val="+val+"&id="+id+"";
			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
			}else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
			req.onreadystatechange = deletePharDiscRequestRequest;
			req.open("GET", url, true); 
			req.send(null);    
	}
}
function deletePharDiscRequestRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();	     
		}
	}
}

function setSession_added_med(userid,status) {
	  uid= userid;
	  var url="setsession_added_medPharmacy?userid="+userid+"&status="+status+"";   	
	     	
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
 
		 req.onreadystatechange = setSession_added_medRequest;
		 req.open("GET", url, true); 

		 req.send(null);

}
function setSession_added_medRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
}

function checkReturnsaleBarcodeRequest(newpid){
	try {
		var nonsystembarcodemew = document.getElementById("nonsystembarcode").checked;
		var clientid= document.getElementById("clientid").value;
		var ispharmacy =document.getElementById("ispharmacy").value;
		var returnpaymodeselect = document.getElementById("returnpaymodeselect").value;
		var nonsystembarcode =0;
		if(nonsystembarcodemew==true){
			nonsystembarcode = 1;
		}
		var flagerror = false;
		var test= false;
		if(document.getElementById("location")){
			var loctioid = document.getElementById("location").value;
			if(loctioid==''){
				test= true;
				
			}else if(loctioid=='0'){
				test= true;
				
			}
		}
		if(test){
			document.getElementById("barcode").value="";
			jAlert('error', "Please select location from top!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
			flagerror = true;
		}else if(nonsystembarcode==0){
			var isnumberpid = checkNumberOrNot(newpid);
			if(newpid==''){
				flagerror = true;
			}else if(!isnumberpid){
				  document.getElementById("barcode").value="";
				  jAlert('error', "please select valid barcode!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					flagerror = true;
			 }else if(newpid=='0'){
				  document.getElementById("barcode").value="";
				  jAlert('error', "please select valid barcode!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					flagerror = true;
			 }else if(clientid==''){
				 document.getElementById("barcode").value="";
				 jAlert('error', "Please select patient!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					flagerror = true;
			 }else if(returnpaymodeselect=='0'){
				 	document.getElementById("barcode").value="";
				 	jAlert('error', "Please select return paymode!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					flagerror = true;
			 }
		}else if(newpid==''){
			flagerror = true;
		}else if(clientid==''){
			 document.getElementById("barcode").value="";
			 jAlert('error', "Please select patient!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				flagerror = true;
		 }else if(returnpaymodeselect=='0'){
			 	document.getElementById("barcode").value="";
			 	jAlert('error', "Please select return paymode!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
				flagerror = true;
		 }
		
		 
		 if(!flagerror){
    		var dataObj={
				"pid":""+newpid+"",
				"nonsystembarcode":""+nonsystembarcode+"",
				"clientid":""+clientid+"",
				"ispharmacy":""+ispharmacy+"",
				"paymode":""+returnpaymodeselect+"",
			};
			var data1 =  JSON.stringify(dataObj);
			$.ajax({
			   url : "checkreturnsalebarcodedataPharmacyAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   success : function(data) {
				   	var dd=data.productlist;
				   	var chargeid = data.chargeid;
		       		if(dd=="0"){
		       				document.getElementById("barcode").value="";
		       				jAlert('error', "Barcode not generated!", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 		             		 	     
		       		}else if(dd=="-2"){
		       			document.getElementById("barcode").value="";
		       			jAlert('error', "Barcode not store in system!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 	
					}else if(chargeid==0){
						document.getElementById("barcode").value="";
						jAlert('error', "Product not belong to this patient!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 	
					}else{
						document.getElementById("barcode").value="";
						addReturnBarocdeRequest(chargeid);
					}
			   },
			   error : function(result) {
				   document.getElementById("barcode").value="";
				   jAlert('error', "Something wrong!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			   }
			}); 
		}
	} catch (e) {
		  window.location.reload();	      
	}
}

function addReturnBarocdeRequest(childchargeid){
	try {
		 counts= Number(document.getElementById("tempcount").value);
	     var qty=1;
	     var chargeid=childchargeid;
	     tmid=chargeid;
	     if(chargeid=='0'){
                jAlert('error', "please select product!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
		 } else {
				var hdnphclientid = document.getElementById("hdnphclientid").value;
				var hdnispharmacy = document.getElementById("hdnispharmacy").value;
				 var dataObj={
					  	"chargeid":""+chargeid+"",
						"count":""+counts+"",
						"qty":""+qty+"",
						"hdnphclientid":""+hdnphclientid+"",
						"hdnispharmacy":""+hdnispharmacy+"",
				 };
				var data1 =  JSON.stringify(dataObj);
				$.ajax({
				   url : "adnewretrunmedicinePharmacyAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   success : function(data) {
					  var dd=data.productlist;
			       		if(dd=="0"){
			       				jAlert('error', "Product already added!", 'Error Dialog');
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration); 		             		 	     
			       		}else if(dd=="1"){
		       				jAlert('error', "Entered qty greater than available stock!!!", 'Error Dialog');
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 		             		 	     
			       		}else if(dd=="2"){
			       			addtempreturndata();	             		 	     
			       		}else {
			       			window.location.reload();	   
						}
				   },
				   error : function(result) {
					   jAlert('error', "Something wrong!", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
				   }
				}); 
	   } 
	} catch (e) {
		  window.location.reload();	      
	}
}

function deletemultiplepriscproduct(){
	
	var hdnphclientid = document.getElementById("hdnphclientid").value;
	var hdnispharmacy = document.getElementById("hdnispharmacy").value;
	 var url="deletemultiplepriscproductPharmacyAjax?hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	 }else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	req.onreadystatechange = deletemultiplepriscproductRequest;
	req.open("GET", url, true); 
	req.send(null);    
}
function deletemultiplepriscproductRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("innerTableBody").innerHTML=req.responseText;
			calsubTotal();
		}
	}
}


function setnewtokenentry() {
	var fromdate = document.getElementById("fromdate").value;
	var todate = document.getElementById("todate").value;
	  var url="setnewtokenentryPharmacyAjax?fromdate="+fromdate+"&todate="+todate+"";   	
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
 	req.onreadystatechange = setnewtokenentryRequest;
	req.open("GET", url, true); 
	req.send(null);
}
function setnewtokenentryRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
		var data=req.responseText;
		document.getElementById("priscrequesttbody").innerHTML=data;
		
	}
}
}

function printPriscReqCSV(oldparentid,newparentid) {
	var url="printpriscreqcsvPharmacyAjax?oldparentid="+oldparentid+"&newparentid="+newparentid+"";   	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
 	req.onreadystatechange = printPriscReqCSVRequest;
	req.open("GET", url, true); 
	req.send(null);
}
function printPriscReqCSVRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			document.getElementById("printpriscreqcsvbody").innerHTML=data;
			$(".xlsprintprisc").table2excel({
				exclude: ".csv",
				name: "Priscription",
				filename: "priscription",
				fileext: ".csv",
				exclude_img: true,
				exclude_links: true,
				exclude_inputs: true
			});          
			
		}
	}
}


function getHisPatientNameData(val){
	var url = "getpatientnamedataPharmacyAjax?val="+val+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = getHisPatientNameDataRequest;
	req.open("GET", url, true); 
	req.send(null);
}
function getHisPatientNameDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("brow").innerHTML = req.responseText;
		}
	}
}

function addnewDrInSystem() {
	document.getElementById("phardocadddocdiv").className='';
}


function closenewDrInSystem() {
	document.getElementById("phardocadddocdiv").className='hidden';
}

function savenewDrInSystem(){
	var phardoc_inital = document.getElementById("phardoc_inital").value;
	var phardoc_firstname = document.getElementById("phardoc_firstname").value;
	var phardoc_lastname = document.getElementById("phardoc_lastname").value;
	var phardoc_address = document.getElementById("phardoc_address").value;
	var phardoc_qualification = document.getElementById("phardoc_qualification").value;
	if(phardoc_firstname==''){
		jAlert('error', "Please enter doctor first name!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(phardoc_lastname==''){
		jAlert('error', "Please enter doctor last name!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		var url = "savedrdetailsinhisPharmacyAjax?phardoc_inital="+phardoc_inital+"&phardoc_firstname="+phardoc_firstname+"&phardoc_lastname="+phardoc_lastname+"&phardoc_qualification="+phardoc_qualification+"&phardoc_address="+phardoc_address+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = savenewDrInSystemRequest;
		req.open("GET", url, true); 
		req.send(null);
	}
	
}
function savenewDrInSystemRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			 jAlert('success', "Doctor Added successfully!", 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			document.getElementById("phardocadddocdiv").className='hidden';
			document.getElementById("phardocadddiv").innerHTML = req.responseText;
			$("#addoctor").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
		}
	}
}

function setpractnameinsale(val){
	var doctorid=$("#phar_practid option:selected").text();
	document.getElementById("doctor").value = doctorid;
	
}

function openpatientpreviousbill() {
	var fullname=document.getElementById("fullname").value;
	var hdnphclientid = document.getElementById("hdnphclientid").value;
	var hdnispharmacy = document.getElementById("hdnispharmacy").value;
	
	if(fullname==''){
		jAlert('error', "Please select patient!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
			}, alertmsgduration); 
		isError=true;		
	}else{
		document.getElementById("dtp_patientname").value =fullname;
		var fromdate=document.getElementById("dtp_fromdate").value;
		var todate=document.getElementById("dtp_todate").value;
		
		$("#dashboardloaderPopup").modal('show');
		var url="openpatientpreviousbillPharmacyAjax?clientid="+hdnphclientid+"&ispharmacy="+hdnispharmacy+"&fromdate="+fromdate+"&todate="+todate+"";
	
        if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		}
		  else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
        req.onreadystatechange = openpatientpreviousbillRequest;
		req.open("GET", url, true); 
        req.send(null);
	}
}

function openpatientpreviousbillRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
		    	var data=req.responseText;
				var str=data.split("~~~");
				document.getElementById("dtp_tbody").innerHTML=str[0];
				document.getElementById("dtp_fromdate").value=str[1];
				document.getElementById("dtp_todate").value=str[2];
				$("#dashboardloaderPopup").modal('hide');
				$('#detailspreviousbill').modal( "show" );
			}
	   }
}

function repeatbillsale(billno){
	try {
		$("#dashboardloaderPopup").modal('show');
		var hdnphclientid = document.getElementById("hdnphclientid").value;
		var hdnispharmacy = document.getElementById("hdnispharmacy").value;
		 var dataObj={
				"hdnphclientid":""+hdnphclientid+"",
				"hdnispharmacy":""+hdnispharmacy+"",
				"billno":""+billno+"",
		 };
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "repeatbillsaledataPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   	var dd=data.productlist;
	       		if(dd=="1"){
	       			$("#dashboardloaderPopup").modal('hide');
	       			$('#detailspreviousbill').modal( "hide" );
	       			addtempsaledata();	             		 	     
	       		}
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
	} catch (e) {
		window.location.reload();	      
	}
}

function editpharmacypatient(){
	$("#dashboardloaderPopup").modal('show');
	var hdnphclientid = document.getElementById("hdnphclientid").value;
	var hdnispharmacy = document.getElementById("hdnispharmacy").value;
	if(hdnispharmacy==0){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please select pharmacy patient!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		$("#dashboardloaderPopup").modal('hide');
		$("#editPatientDiv").modal('show');
		var dataObj={
			"hdnphclientid":""+hdnphclientid+"",
			"hdnispharmacy":""+hdnispharmacy+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "getpharmacypatientdataPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   	var dd=data.productlist;
			   	document.getElementById("editpclinetid").value=data.pharid;
			   	document.getElementById("editfullname").value=data.pharfullname;
			   	document.getElementById("editmobile").value=data.pharmobile;
			   	document.getElementById("editaddress").value=data.pharaddress;
			   	document.getElementById("editdoctor").className="";
			   	document.getElementById("editdoctor").value=data.phardrid;
				document.getElementById("editdoctor").className="form-control chosen-select";
				$("#editdoctor").trigger("chosen:updated");
			  	$(".chosen-select").chosen({allow_single_deselect: true});
			   	$("#dashboardloaderPopup").modal('hide');
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
	}
}


function updatePharmaClient(){
	$("#dashboardloaderPopup").modal('show');
	var flagsetbtn=false;
	var adfullname = document.getElementById("editfullname").value;
	var admobile = document.getElementById("editmobile").value;
	var adaddress = document.getElementById("editaddress").value;
	var addoctor = document.getElementById("editdoctor").value;
	var editpclinetid = document.getElementById("editpclinetid").value;
	var regEx = /^\d{10}$/;
	
	if(adfullname=='' || adfullname==' ' || adfullname=='  ' || adfullname=='   '){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter patient full name!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 	
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}else if(!regEx.test(admobile)) {
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid mobile no!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}else if(addoctor=='0') {
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please select doctor!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}else if(addoctor=='') {
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please select doctor!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}else{
		var url = "updatepatientPharmacyAjax?adfullname="+adfullname+"&admobile="+admobile+"&adaddress="+adaddress+"&addoctor="+addoctor+"&editpclinetid="+editpclinetid+" ";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = updatePharmaClientRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		
	}
	
}

function updatePharmaClientRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			showInhousePatientPopup();
			$('#editPatientDiv').modal( "hide" );
			$("#dashboardloaderPopup").modal('hide');
			var clientid = document.getElementById("hdnphclientid").value;
			var ispharmacy = document.getElementById("hdnispharmacy").value ;
			getAllPatientInformation(clientid,0,ispharmacy);  
         }
	}
	
}


function updatepharmacyaccess(userid,status,colname) {
	  uid= userid;
	  var url="updatepharmacyaccessPharmacy?userid="+userid+"&status="+status+"&colname="+colname+"";   	
	     	
	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	  }   
	 req.onreadystatechange = updatepharmacyaccessRequest;
	 req.open("GET", url, true); 
	 req.send(null);

}
function updatepharmacyaccessRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
}


function calEditTotaltemp(){
    try{
 	   var refund=0;
		   var totalvat=0;
		   var totaldisc=0;
		   var gst=0;
			$('.pclass').each(function() {					
			        var i=this.value;
					var previqty=Number(document.getElementById("tempsale"+i).value);
					var returnqty=Number(document.getElementById("returnqty"+i).value);    
					if(returnqty<0){
						returnqty=0;
						document.getElementById("returnqty"+i).value=0;
						 jAlert('error', "Edit qty can't negative!", 'Error Dialog');
							
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					}
					if(returnqty>previqty){
					    document.getElementById("returnqty"+i).value=0;
					      jAlert('error', "Edit qty can't greater than sale qty!", 'Error Dialog');
				
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
						returnqty=0;	 
				    }       
		    		var mrp=document.getElementById("mrp"+i).value;
		    		var vat=Number(document.getElementById("vat"+i).value);
		    		
		    		var tot=mrp*returnqty;
		    		var discper= Number(document.getElementById("discper"+i).value);
		    		var disct= tot* discper /100; 
		    		document.getElementById("totalmrp"+i).innerHTML=roundTwo(tot);
		    		totaldisc= totaldisc +disct;
		    		
		    		var tvat=tot*vat/100;
		    		totalvat=totalvat+tvat;
		    		//  12 Nov 2018 after discount gst calculation
		    		var ttamt=0;
		    		ttamt = tot - disct; 
		    		var dividevat=100+vat;
	   				//var tgst=tot*vat/dividevat;
		    		var tgst=ttamt*vat/dividevat;
	  			    gst=gst+tgst;
		   			
	  			    refund=refund+tot; 		
		   			if(document.getElementById("totalrefundrs"+i)){
		   				var totalrefundrs = roundTwo(tot)-roundTwo(disct);
		   				document.getElementById("totalrefundrs"+i).value =roundAbs(totalrefundrs);
		   			}
   		});  
   		
   		var sgst= roundTwo(gst/2); 
   		
   		document.getElementById("tvat").value=roundTwo(gst);
   		document.getElementById("tcgst").innerText=roundTwo(sgst);
   		document.getElementById("tsgst").innerText=roundTwo(sgst);
   		document.getElementById("cgst").value=roundTwo(sgst);
   		document.getElementById("sgst").value=roundTwo(sgst);
			document.getElementById("subtotal").innerText=roundAbs(refund);
			document.getElementById("discsmt").value=roundAbs(totaldisc);
			if(document.getElementById("subbtotal")){
   			document.getElementById("subbtotal").value= roundAbs(refund);
   		}
		var subttotal = refund;
		refund=refund-totaldisc;
		document.getElementById("refund").innerText=roundAbs(refund);
   		document.getElementById("ttotal").value=roundAbs(refund);
   		document.getElementById("total").innerText=roundAbs(refund);
   		document.getElementById("trefund").value=roundAbs(refund);
   		
   		//Pikachu 17-12-2018
   		document.getElementById("grosstotal").value= roundTwo(refund);
   	    document.getElementById("grosstotalspan").innerHTML= roundTwo(refund);
   	    document.getElementById("grosssubtotal").value= roundTwo(subttotal);
   	    
   	    if(finalsalereturn==1){
   	    	 finalsalereturn = 0;
   	    	 var returnmode = document.getElementById("returnmode").value;
   	    	 var n=roundAbs(refund);
   	    	 var discnt =totaldisc;
             document.getElementById("npopup").innerHTML=n;
             document.getElementById("paymodepopup").innerHTML=returnmode;
             document.getElementById("discountconfirm").innerHTML=discnt;
             $('#confirmreturnpopup').modal( "show" );	
   	    }
    }catch (e) {
 	   window.location.reload();	      
    }
}

function updatepharmacybill() {

    var returnmode= document.getElementById("returnmode").value;
    document.getElementById("paymode").value=returnmode;
	var total=document.getElementById("tempcount").value;
	var flag=true;
	var returnproductaddflag=false;
	if(returnmode!="0"){
	
	
	      if(returnmode=="Cheque"){
	    
	       var chequeno=document.getElementById("chequeno").value;
	       if(chequeno.length<4){
	             jAlert('error', "Please enter cheque number!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 flag=false;		
			 document.getElementById("chequeno").focus();
	       }
	  }
	  if(returnmode=="Card"){
	    
	       var card=document.getElementById("card").value;
	       if(card==""){
	             jAlert('error', "Please enter card number!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					document.getElementById("card").focus();
			 flag=false;		
	       }
	  }
	  if(returnmode=="NEFT"){
	    
	       var card=document.getElementById("neftid").value;
	       if(card==""){
	             jAlert('error', "Please enter neft transaction id!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 flag=false;
			 document.getElementById("neftid").focus();		
	       }
	  }
	
			$('.pclass').each(function() {
				returnproductaddflag =true;
		        var i=this.value;
				var sale=document.getElementById("returnqty"+i).value;		   
			     if(Number(sale)<0){
			    	 flag=false;
	                 jAlert('error', "Please enter return quantity!", 'Error Dialog');
					
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 
			     }
			}); 
  
		 	if(flag){
		 	    if(returnproductaddflag){
		 	    	var ttotal=document.getElementById("ttotal").value;
		 	    	 document.getElementById("npopup").innerHTML=ttotal;
	                 document.getElementById("paymodepopup").innerHTML=returnmode;
	                 $('#confirmreturnpopup').modal( "show" );	
		 	    	 /*document.getElementById("creditform").action="refundmedicinePharmacy";
		 	    	 document.getElementById("creditform").submit();*/
		 	    }else{
		 	    	 jAlert('error', "Please select at least one product!", 'Error Dialog');
					 setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
					 }, alertmsgduration); 
		 	    }
		 	   
		 	}
	
  } else {
  
       jAlert('error', "Please select Paymode!", 'Error Dialog');
						
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration); 
  
  }

}	

function checkeditmode() {
	var npopup = Number(document.getElementById("npopup").innerHTML);
	 
	 if(npopup<=0){
		 
	 }else{
		 $("#dashboardloaderPopup").modal('show');
		 document.getElementById("creditform").action="updatepharmacybillPharmacy";
  		 document.getElementById("creditform").submit();
	 }
	
}


function editpharmacypatientdashboard(hdnphclientid){
	$("#dashboardloaderPopup").modal('show');
	var hdnispharmacy = "1";
	if(hdnispharmacy==0){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please select pharmacy patient!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		$("#dashboardloaderPopup").modal('hide');
		$("#editPatientDiv").modal('show');
		var dataObj={
			"hdnphclientid":""+hdnphclientid+"",
			"hdnispharmacy":""+hdnispharmacy+"",
		};
		var data1 =  JSON.stringify(dataObj);
		$.ajax({
		   url : "getpharmacypatientdataPharmacyAjax",
		   data : data1,
		   dataType : 'json',
		   contentType : 'application/json',
		   type : 'POST',
		   async : true,
		   success : function(data) {
			   	var dd=data.productlist;
			   	document.getElementById("editpclinetid").value=data.pharid;
			   	document.getElementById("editfullname").value=data.pharfullname;
			   	document.getElementById("editmobile").value=data.pharmobile;
			   	document.getElementById("editaddress").value=data.pharaddress;
			   	document.getElementById("editdoctor").className="";
			   	document.getElementById("editdoctor").value=data.phardrid;
				document.getElementById("editdoctor").className="form-control chosen-select";
				$("#editdoctor").trigger("chosen:updated");
			  	$(".chosen-select").chosen({allow_single_deselect: true});
			  	$("#dashboardloaderPopup").modal('hide');
			  	$('#editPatientDiv').modal( "show" );
		   },
		   error : function(result) {
			   jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		   }
		}); 
	}
}

function updatePharmaClientDashboard(){
	$("#dashboardloaderPopup").modal('show');
	var flagsetbtn=false;
	var adfullname = document.getElementById("editfullname").value;
	var admobile = document.getElementById("editmobile").value;
	var adaddress = document.getElementById("editaddress").value;
	var addoctor = document.getElementById("editdoctor").value;
	var editpclinetid = document.getElementById("editpclinetid").value;
	var regEx = /^\d{10}$/;
	
	if(adfullname=='' || adfullname==' ' || adfullname=='  ' || adfullname=='   '){
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter patient full name!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 	
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}else if(!regEx.test(admobile)) {
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please enter valid mobile no!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}else if(addoctor=='0') {
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please select doctor!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}else if(addoctor=='') {
		$("#dashboardloaderPopup").modal('hide');
		jAlert('error', "Please select doctor!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		if(document.getElementById("addpharpatientbtnid")){
			document.getElementById("addpharpatientbtnid").disabled =false;
		}
	}else{
		var url = "updatepatientPharmacyAjax?adfullname="+adfullname+"&admobile="+admobile+"&adaddress="+adaddress+"&addoctor="+addoctor+"&editpclinetid="+editpclinetid+" ";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = updatePharmaClientDashboardRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		
	}
	
}

function updatePharmaClientDashboardRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			$('#editPatientDiv').modal( "hide" );
			window.location.reload();	 
			 
         }
	}
	
}

var unirprice=0;
function changeQtyFromSale1(count,chargesessionid,chargetempid,unitprice){
	try {
	 	var pstock = Number(document.getElementById("prodd"+count).value);
	    var sale=document.getElementById("sale"+count).value;
	    var freeqty=document.getElementById("freeqty"+count).value;
	    var discount_perprodt=document.getElementById("discount"+count).value;
	    var isnumberqty = checkNumberOrNot(sale);
	    
	    if(!isnumberqty){
	    	sale =0;
	    }else{
	    	sale = Number(sale);
	    }
	    var saleqty=false;
	    if(sale<=0){
	    	saleqty = true;
	    	document.getElementById("sale"+count).value=0;
	    	jAlert('error', "please enter sale qty!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
	    }else if(pstock<sale){
	    	saleqty = true;
	    	document.getElementById("sale"+count).value=0;
	    	jAlert('error', "Entered qty is greater than available instock("+pstock+"). So entered qty change from "+sale+" to 0. Please enter valid qty!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, 3000); 
		}
		if(saleqty){
			sale =0;
			setTimeout(function(){  
				var hdnphclientid = document.getElementById("hdnphclientid").value;
				var hdnispharmacy = document.getElementById("hdnispharmacy").value;
			    var url="changeproductfromsessionPharmacyAjax?chargesessionid="+chargesessionid+"&saleqty="+sale+"&chargetempid="+chargetempid+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"";
				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
				}else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
				req.onreadystatechange = changeQtyFromSaleRequest;
				req.open("GET", url, true); 
				req.send(null);    
				
			}, 1500);
		}else{
				var hdnphclientid = document.getElementById("hdnphclientid").value;
				var hdnispharmacy = document.getElementById("hdnispharmacy").value;
			    var url="changeproductfromsessionPharmacyAjax?chargesessionid="+chargesessionid+"&saleqty="+sale+"&chargetempid="+chargetempid+"&hdnphclientid="+hdnphclientid+"&hdnispharmacy="+hdnispharmacy+"&freeqty="+freeqty+"&discount_perprodt="+discount_perprodt+"";
				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
				}else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
				req.onreadystatechange = changeQtyFromSaleRequest;
				req.open("GET", url, true); 
				req.send(null);    
		}
		
		
	} catch (e) {
		  window.location.reload();	      
	}
}
function changeQtyFromSaleRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("innerTableBody").innerHTML=req.responseText;
			calsubTotal();
		}
	}
}


function myfun(val){
	//alert(val);
	if("plist"==val){
		 document.getElementById("cataloguewiselistid").classList.remove("focusdrop");
		 document.getElementById("plist").classList.add("focusdrop");
	    // $("#newmedicine").trigger("chosen:activate");
	     document.getElementById("qty").classList.remove("focusdrop");
      
	}
	
	
}
function func(val){
	
	if("qty"==val){
		 document.getElementById("plist").classList.remove("focusdrop");
		 document.getElementById("cataloguewiselistid").classList.remove("focusdrop");
	     document.getElementById("qty").classList.add("focusdrop");
	}
	
}

