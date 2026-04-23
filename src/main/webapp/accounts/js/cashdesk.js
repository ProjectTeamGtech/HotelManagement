function confirmedDelete(){

var r=confirm("Are you sure you want to delete this entry");
if (r==true)
  {
  return true;
  }
else
  {
  return false;
  }

}

function saveValidation(){
	
	var client = document.getElementById("client").value;
	var amount = document.getElementById("amount").value;
	
	document.getElementById("clientError").innerHTML = "";
	document.getElementById("amountError").innerHTML = "";
	
	var chk = 0;
	
	if(client == ""){
		var clientname = document.createElement("label");
		clientname.innerHTML = "Please add Client name";
	    document.getElementById('clientError').appendChild(clientname);
	    chk=1;
	}
	if(amount == ""){
		var amt = document.createElement("label");
		amt.innerHTML = "Please add Amount";
	    document.getElementById('amountError').appendChild(amt);
	    chk=1;
	}
	if(chk==1)
    {
       return false;
    }
    else
    {
    	return true;
    }
	
}


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
				
				$('#clientSearch').modal('hide');
				
				
				
			}
		}

	}
