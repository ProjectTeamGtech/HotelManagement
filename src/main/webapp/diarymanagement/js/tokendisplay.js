/**
 * 
 */
 var count=0;
 function setClientIsBeingSeentoken(editAppointId,i){
	
	count=i;
	var status=2;
	
	var url = "clientIsBeingSeenNotAvailableSlot?selectedid="+editAppointId+"&status="+status+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setClientIsBeingSeentokenRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setClientIsBeingSeentokenRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			
			/*document.getElementById('clienthasarrived').style.display = 'none';
			document.getElementById('clientisbeingseen').style.display = 'none';
			document.getElementById('resetnotarrived').style.display = '';
			document.getElementById('clientdidnotcome').style.display = 'none';*/
			//disable
			//$(this).cancelSlot();
			document.getElementById('token'+count).style.backgroundColor='';
			document.getElementById('token'+count).style.backgroundColor='green';
			tempAlert("Ok,Client has been Called!",5000);			
			//var drid=document.getElementById("drids").value;
	        //var dridsdata=drid.split(",");
	       // showdisplaynewopd1(dridsdata[3]);
	        
		}	
	}
}


	function withpaymentCompleteAppointmenttoken(editAppointId,i){
	        count=i;
			var url = "opdcompleteCompleteApmt?appointmentid="+editAppointId+"";
			
		    if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = withpaymentCompleteAppointmenttokenRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);		
	}
	
	
	function withpaymentCompleteAppointmenttokenRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
				tempAlert("Ok,Client has been seen!",5000);
				document.getElementById('token'+count).style.backgroundColor='';
			document.getElementById('token'+count).style.backgroundColor='blue';
				//document.getElementById('token'+count).style.backgroundColor='';
			   // document.getElementById('token'+count).style.backgroundColor='green';
							
			//var drid=document.getElementById("drids").value;
	        //var dridsdata=drid.split(",");
	        //showdisplaynewopd1(dridsdata[3]);
				
		}
	}
	
}