function setCategoryID(id){

	var url = "setCategory?id="+id+" ";
	
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setBranchRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setBranchRequest(){

if (req.readyState == 4) {
		if (req.status == 200) {
			
         
         }
	}
}