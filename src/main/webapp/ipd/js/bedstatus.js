function changeBedtatus(id,status){
	var url = "statusBed?selectedid="+id+"&status="+status+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = changeBedtatusREequest;
    req.open("GET", url, true); 
    req.send(null);

}

function changeBedtatusREequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data = req.responseText;
			
			if(data==1){
				jAlert('success', 'Bed status updated successfully.', 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				goreferesh();
			}else{
				jAlert('error', 'Bed already occupied.', 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				goreferesh();
			}
			
			
			
			//document.getElementById('alldataprisctable').innerHTML = req.responseText;
		}
	}

}