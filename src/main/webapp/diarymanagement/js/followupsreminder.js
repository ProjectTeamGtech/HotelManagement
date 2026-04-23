function saveReminderDuration(){
	var remingmeduration = document.getElementById('remingmeduration').value; 
	
	
	var url = "followupPrescription?remingmeduration="+remingmeduration+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveReminderDurationRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}

function saveReminderDurationRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			//document.getElementById('alldataprisctable').innerHTML = req.responseText;
			//remingmedurationvar = '90000';
		}
	}

}