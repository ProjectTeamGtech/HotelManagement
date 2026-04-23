function sendsmspopupopen(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	
	//alert(globalTpType);
	globalapptid = editAppointId;
	
	
	document.getElementById('smsuserNameletter').value = editClientName;
	
	getSmsClientInfo(patientId);
	
	
	$('#semdsmspopup').modal( "show" );
}


function sendsms(){
	
	var mobno = document.getElementById('smsmobile').value; 
	var smstxt = document.getElementById('smstxt').value; 
	var url = "sendsmsNotAvailableSlot?apmtid="+editAppointId+"&mobno="+mobno+"&smstxt="+smstxt+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = sendsmsRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function sendsmsRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			$('#semdsmspopup').modal( "hide" );
			
			jAlert('success', 'sms sent successfully.', 'Success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	}
}


function getSmsClientInfo(id){
	var diaryuserId = '0';
	var url = "getInfoClient?clientId="+id+"&diaryuser="+diaryuserId+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getSmsClientInfoRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function getSmsClientInfoRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			
			var info = req.responseText;
			var temp = info.split("*");
		/*	document.getElementById("infoName").innerHTML = ' '+temp[0];
			document.getElementById("infoAddress").innerHTML = ' '+temp[1];
			document.getElementById("infoSecondAdress").innerHTML = temp[2];
			document.getElementById("infoTown").innerHTML = temp[3];
			document.getElementById("infoPostcode").innerHTML = temp[4];
			document.getElementById("infoDob").innerHTML = temp[5];
			document.getElementById("infotp").innerHTML = temp[6];
			document.getElementById("infomobile").innerHTML = temp[7];
			document.getElementById("infoemail").innerHTML = temp[8];*/
			
			document.getElementById("smsmobile").value = temp[7];
			
         }
	}
}


function getsmstemplatetxt(id){
	
	var url = "setSMSTemplate?id="+id+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getsmstemplatetxtRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getsmstemplatetxtRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("smstxt").value = req.responseText;
		}
	}
	
}


function getsmsInvsttemplatetxt(id){
	
	var url = "setSMSTemplate?id="+id+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getsmsInvsttemplatetxtRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getsmsInvsttemplatetxtRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("smstxt").value = sendsmsclientname + ': ' +  req.responseText;
		}
	}
	
}



function importContact() {

	   var file=document.getElementById("userFile").value ;
	   
	   var isError=false;
	   
	   if(file==''){
	   
	         isError=true;
	   }
	   
	   
	   if(isError==true){
	      return false;
	      
	   } else {
	   
	      return true;
	   }
	   
}


var mob="";

var flag=0;

function selectallcontact(){

    mob="";
   if (flag == 0) {
		$('.mcase').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
		flag = 1;
	} else {
		$('.mcase').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
	    								// "checkbox1"
		});
		flag = 0;
	}

   $('.mcase').each(function() { // loop through each checkbox
		if (this.checked == true) {
			if(mob==""){
			    mob=this.value;
			} else {
					mob = mob + ',' + this.value;
			}
		}
	}); 
   
   if(mob=="0"){
      mob="";
   }
   
    
   document.getElementById("numbers").value=mob;
}


function addtosms(phone,chkbox) {

   var t = (chkbox.checked) ? "true" : "false"; 
    
        if(t=="true") {
            var n=mob.search(""+phone+"");
            if(n==-1) {
              if(mob==""){
              		mob=phone;
              } else {
                   mob = mob + ',' +phone;
              }
              
              
     	    } 
     	} else {       
			    var n=mob.search(",");
			    if(n==-1){
			      var str=mob.replace(""+phone+"","");
			    } else {
			   		     	
     	           var str=mob.replace(","+phone+"","");
     	        }
     	        mob=str; 
     	} 

    document.getElementById("numbers").value=mob;        
}


function sendlotsms(){

     $('#baselayout1loaderPopup').modal( "show" );
     
     
     
	 var msg=document.getElementById("smstxt").value;
	 var numbers=document.getElementById("numbers").value;
     var url = "sendmultiSendsms?smstxt="+msg+"&numbers="+numbers+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = sendlotsmsRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function sendlotsmsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
		      $('#baselayout1loaderPopup').modal( "hide" );
		
		     jAlert('success', 'sms sent successfully.', 'Success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, 5*1000);
		     
		}
   	}
}


