

 function getcharges(val) {
			
			var flag=false;
			var wardid=document.getElementById("wardid").value;
			var tpid=document.getElementById("tpid").value;
			var payee=document.getElementById("payee").value;
			
			if(payee=="Select Payee"){
			     jAlert('error', "please select payee!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			    flag=true;
			} /*else if(payee=="0" && wardid=="0"){
			
				jAlert('error', "please select ward!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			    flag=true;    
			
			}*/ else if(payee=="1" && tpid=="0"){
			
			     jAlert('error', "please select third party!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			    flag=true;
			}
			
			
			if(flag==false && val=='INVESTIGATION'){
				
				document.getElementById("sinv").className="form-group";
				return false;
			} else {
				document.getElementById("sinv").className="form-group hidden";
			    
			}
			
			
			
			
			
			 if(flag==false){
			 
					var url = "getchargesThirdParty?chargetype="+val+"&tpid="+tpid+"&wardid="+wardid+"";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			    req.onreadystatechange = getchargesRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);   		 
			 
			 }
		
  }
		
  function getchargesRequest() {
  
		  if (req.readyState == 4) {
				if(req.status == 200) {					
						
						document.getElementById("chargeTBody").innerHTML=req.responseText;
						
			 }
		 }
  }
  
  
  
  function getinvestigationcharges(invtype){
  
               var wardid=document.getElementById("wardid").value;
			   var tpid=document.getElementById("tpid").value;
  
  		       var url = "getinvnamesThirdParty?invstype="+invtype+"&tpid="+tpid+"&wardid="+wardid+"";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			    req.onreadystatechange = getinvestigationchargesRequest;
			    req.open("GET", url, true); 
			    
			    req.send(null);   		 	
			      
       
      
  }
  function getinvestigationchargesRequest() {
			
		  if (req.readyState == 4) {
				if(req.status == 200) {					
						
						document.getElementById("chargeTBody").innerHTML=req.responseText;
						
			 }
		 }
  }
  
  function checkDecimal(inputtxt) 
  { 
	  var decimal1 = /^[+-]?([0-9]*[.])?[0-9]+$/;
	  if(inputtxt.match(decimal1)) { 
		  //correct
		  return true;
	  }else{ 
		  //wrong
		  return false;
	  }
  } 
  
  
  
  function saveAllCharges(){

	var tempcharges="0";
	var flag=false;
	var index=0;
	var tflag=false;
	
	var payee=document.getElementById("payee").value;
	var wardid=document.getElementById("wardid").value;
	var chargeType=document.getElementById("chargeType").value;
	var invstype=document.getElementById("invstype").value;
	
	var validNumber=false;;
	var validDiscount = false;
	
	
	$('.case').each(function() { // loop through each checkbox
		if (this.checked == true) {
			var d=this.value;
			var data2 = document.getElementById("mrp"+d).value;
			if(data2==''){
				data2 = '0';
			}
			var dis= document.getElementById("discount"+d).value;
			if(dis==''){
				dis ='0';
			}
			if(!checkDecimal(data2)){
				validNumber = true;
			}
			
			if(!checkDecimal(dis)){
				validDiscount = true;
			}
			if(parseFloat(dis)>100 || parseFloat(dis)<0){
				validDiscount = true;
			}
			var val =  data2 * (dis/100);
			var summ = Number(data2)-Number(val);
			document.getElementById("discountAmt"+d).value = summ;
			document.getElementById("chargeAmount"+this.value).value = summ;
		}else{
			document.getElementById("discount"+this.value).value = '';
			document.getElementById("discountAmt"+this.value).value = 0;
			document.getElementById("chargeAmount"+this.value).value = 0;
		}
		/*var numbers = /^[0-9]+$/;
		var discount = document.getElementById("discount"+d).value;
		if(!discount.match(numbers)){
			jAlert('error', "Special Symbol & Character not allowed!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}*/
		
	 });
	
	
	if(payee=='Select Payee'){
	     tflag=true;
	      jAlert('error', "please select payee!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	}/*else if(payee==0 && wardid==0){
	
	     tflag=true;
	      jAlert('error', "please select Ward!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	
	}*/ else if(chargeType=="0"){
	
			 tflag=true;
	      jAlert('error', "please Select Charge Type!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	}else if(chargeType=="5" && invstype=="0"){
	
			 tflag=true;
	      jAlert('error', "please Select Investigation Type!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
	}
	
	if(tflag==false){
	
	    	 $('.case').each(function() { // loop through each checkbox
			if (this.checked == true) {
			
			 var d=this.value;
			 
			 tempcharges=tempcharges+","+d;
			 var mrp=document.getElementById("mrp"+d).value;
			 var code=document.getElementById("code"+d).value;
			 var standardcharge= document.getElementById("standardcharge"+d).value;
			 var hour= document.getElementById("hour"+d).value;
			 var ratio= document.getElementById("ratio"+d).value; 
			 var numbers = /^[0-9]+$/
		 // 	var standardcharge = 0;
	
			 if(mrp==''){
			 
			     jAlert('error', "please enter mrp!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			    flag=true;
			 }
			 else if(standardcharge=='1' && wardid=="0") {
			 	 
			 	   jAlert('error', "please select ward for standard charge!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			     flag=true;
			 }
			 
			 /* else if(code=='') {
			 	 
			 	   jAlert('error', "please enter code!", 'Error Dialog');
				
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
			     flag=true;
			 }*/
		
		   index++;	 				
		}
	});	
	
  }
	
	
	
     
   
  
   document.getElementById("notes").value=tempcharges;
   if(validNumber){
	   jAlert('error', "Please enter valid Amount!", 'Error Dialog');
		   setTimeout(function() {
		   $("#popup_container").remove();
	   removeAlertCss();
	   }, alertmsgduration);
   }else if(validDiscount){
	   jAlert('error', "Please enter valid Discount!", 'Error Dialog');
	   setTimeout(function() {
	   $("#popup_container").remove();
	   removeAlertCss();
   }, alertmsgduration);
   }/*else if(!numbers.match(document.getElementById("discount"+d).value)){
	   jAlert('error', "Please enter valid Discount!", 'Error Dialog');
	   setTimeout(function() {
	   $("#popup_container").remove();
	   removeAlertCss();
   }, alertmsgduration);
   }*/
   else if(tflag==false){
	   if(index>0){
		   if(flag==false){
			   document.getElementById("tpchargeform").submit();     
	  	   } 
  	   } else {
  		   jAlert('error', "please select at least one charge!", 'Error Dialog');
  		   setTimeout(function() {
  		   $("#popup_container").remove();
		   removeAlertCss();
		   }, alertmsgduration);
	  } 
   }
}
  
  
  
  
  function settpchanges(payee){
  
       if(payee=="1"){
           
         	document.getElementById("hstp").className="";
         	document.getElementById("dstp").className="";  
           
       } else {
       
        	document.getElementById("hstp").className="";
         	document.getElementById("dstp").className="";  
         
       }
       
       if(payee=="0"){
    	   document.getElementById("perdiv").className="form-group";
       }else{
    	   document.getElementById("perdiv").className="form-group"; 
       }
  }
  
  
  function setOnOff(obj,indx) {
  
        if(obj.checked==true){
        
             document.getElementById("standardcharge"+indx).value=1;
             document.getElementById("ch2"+indx).disabled='';
         
        } else {
        	 document.getElementById("standardcharge"+indx).value=0;
        	 document.getElementById("ch2"+indx).disabled='disabled';
        }
  
  }
  
  function setOnVal(obj,indx) {
  
        if(obj.checked==true){
        		
            	document.getElementById("onoff"+indx).value=1;
        }
        else {
        		document.getElementById("onoff"+indx).value=0;
        }
  
  }
  
 function calculateandincrese() {
	 var data = document.getElementById("peramount").value;
	 if(data==''){
		 jAlert('error', "please enter percentage value through which charges increase!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	 }else{
		 $('.case').each(function() { // loop through each checkbox
				if (this.checked == true) {
					var d=this.value;
					var data2 = document.getElementById("mrp"+d).value;
					if(data2==''){
						data2 = 0;
					}
					var val =  data2 * (data/100);
					var summ = Number(val)+Number(data2);
					document.getElementById("mrp"+d).value = summ;
				}
			 });	
	 }
	
}


function gettplist(patientType) {
	var dataObj={
		"patientType":""+patientType+"",
	};
	var data1 =  JSON.stringify(dataObj);
	$.ajax({
	   url : "getMainTpListBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   success : function(data) {
		  var tpList= data.tpList;
		  document.getElementById("dstp").innerHTML= data.tpList;
		  $("#tpid").trigger("chosen:updated");
		  $(".chosen-select").chosen({allow_single_deselect: true});
		  resetChargeAndWard();
		  
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

function resetChargeAndWard(){
	if(document.getElementById("isfromaddchargepage")){
		  document.getElementById("wardid").className="form-control";
		  document.getElementById("wardid").value ="0";
		  document.getElementById("wardid").className="form-control chosen-select"; 
		  $("#wardid").trigger("chosen:updated");
		  $(".chosen-select").chosen({allow_single_deselect: true});
		  
		  document.getElementById("chargeType").className="form-control";
		  document.getElementById("chargeType").value ="0";
		  document.getElementById("chargeType").className="form-control chosen-select"; 
		  $("#chargeType").trigger("chosen:updated");
		  $(".chosen-select").chosen({allow_single_deselect: true});
		  
		  document.getElementById("invstype").className="form-control";
		  document.getElementById("invstype").value ="0";
		  document.getElementById("invstype").className="form-control chosen-select"; 
		  $("#invstype").trigger("chosen:updated");
		  $(".chosen-select").chosen({allow_single_deselect: true});
		  
		  document.getElementById("chargeTBody").innerHTML="";
		  document.getElementById("tpDiscountAll").value ="";
	  }
}
 

function calculateChargeDiscount() {
	
	
	 	$('.case').each(function() { // loop through each checkbox
			if (this.checked == true) {
				var d=this.value;
				var data2 = document.getElementById("mrp"+d).value;
				if(data2==''){
					data2 = 0;
				}
				var dis= document.getElementById("discount"+d).value;
				if(dis==''){
					dis =0;
				}
				var val =  data2 * (dis/100);
				var summ = Number(data2)-Number(val);
				document.getElementById("discountAmt"+d).value = summ;
				document.getElementById("chargeAmount"+d).value = summ;
			}else{
				document.getElementById("discount"+this.value).value = '';
				document.getElementById("discountAmt"+this.value).value = 0;
				document.getElementById("chargeAmount"+this.value).value = 0;
			}
			
			/*var numbers = /^[0-9]+$/;
			var discount = document.getElementById("discount"+d).value;
			
		 	if(!discount.match(numbers)){
				jAlert('error', "Special Symbol & Character not allowed!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}*/
			
			
		 });
	 	
}

function discountAll(dis){
	var numbers = /^[0-9]+$/;
	
	var discount = document.getElementById("tpDiscountAll").value;
	
	
	$('.case').each(function() { // loop through each checkbox
		
		
		if (this.checked == true) {
			var d=this.value;
			var isndscharge = document.getElementById("isndscharge"+d).value;
			if(isndscharge==0){
				var data2 = document.getElementById("mrp"+d).value;
				if(data2==''){
					data2 = 0;
				}
				if(dis==''){
					dis =0;
				}
				var val =  data2 * (dis/100);
				var summ = Number(data2)-Number(val);
				document.getElementById("discountAmt"+d).value = summ;
				document.getElementById("discount"+this.value).value = dis;
				document.getElementById("chargeAmount"+this.value).value = summ;
			}
		}else{
			document.getElementById("discount"+this.value).value = '';
			document.getElementById("discountAmt"+this.value).value = 0;
			document.getElementById("chargeAmount"+this.value).value = 0;
		}
		
	 });
	if(!discount.match(numbers)){
		jAlert('error', "Special Symbol & Character not allowed!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		document.getElementById("tpDiscountAll").value="";
	}
	
	
	
}
  

function selectChargeCheckBox(val){
	if (val== true) {
		$('.case').each(function() { 
			this.checked = true; 	
		});
	} else {
		$('.case').each(function() { 
			this.checked = false; 
		});
	}
}

function savenewtpcharges(action){
	
	var tpidold=document.getElementById("tpidold").value;
	var tpidnew=document.getElementById("tpid").value;
	
	 var url="newThirdpartyChargeThirdParty?action="+action+"&tpidold="+tpidold+"&tpidnew="+tpidnew+"";
           
	        if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = savenewtpchargesRequest;
		    req.open("GET", url, true); 
		    
		    req.send(null);   		 	
		      
     
    
}
function savenewtpchargesRequest() {
		
	  if (req.readyState == 4) {
			if(req.status == 200) {					
					
				alert("Data Moved to Create Charges in Setup Masters");	
					
		 }
	 }
} 
  