function showPopUp(){
	
	var url = "showListClient";

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

	}
function showAllPatientPopUpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allPatient").innerHTML = req.responseText;
				
	         	
				
	         }
		}
}

function setPatientName(name,id,type,typeName){
	//deleteChargeAccountsTableAjax();
	document.getElementById("clientId").value = id;
	document.getElementById("searchClientId").value = id;
	
	var url = "getFullNameClient?id="+id+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setPatientNameRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
	
        
		
}
function setPatientNameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var clientFullName = req.responseText;
		
			document.getElementById("client").value = clientFullName;
			document.getElementById("clientName").value = clientFullName;


			
			
			$('#clientSearch').modal('hide');
			
			
			
		}
	}

}