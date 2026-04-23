var isfromviewacct=0;
function showPopUp(val){
	isfromviewacct = val;
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
				
				$('#clientSearch').modal('show');
				
	         }
		}
	}
	function displayProfile(){
		alert('hi');
		
	} 
	
	
	
	function setPatientName(name,id,type,typeName){
		//deleteChargeAccountsTableAjax();
		if(isfromviewacct==1){
			document.getElementById("clientId").value = name;
			id=  name;
		}else if(document.getElementById("setp")){
			document.getElementById("clientId").value = name;
			id=  name;
		}
		else if(document.getElementById("feedb")){
			document.getElementById("clientId").value = name;
			id=  name;
			getPatient(id);
		}
		else{
			document.getElementById("clientId").value = id;
		}
		
		
		if(document.getElementById("clidinv")){
			document.getElementById("clidinv").value=name;
		}
		
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
				var clientid = document.getElementById("clientId").value;
				if(isfromviewacct==1){
					opencPopup('Statement?clientId='+clientid+'');
				}else{
					setSelectedClientSessionRecordAjax(clientid);
				}
				
				
				$('#clientSearch').modal('hide');
				
				
				
			}
		}

	}
	
	function searchPatient(){
		var searchText = document.getElementById("searchText1").value;
		var ipdno=document.getElementById("ipdno").value;
		var url = "searchParticularPatientClient?searchText="+searchText+"&ipdno="+ipdno;

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

function updateDeclration(id){
	var url = "decDeclaration?id="+id+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updateDeclrationRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function updateDeclrationRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			goreferesh();
		}
	}
}

function saveDeclarationData() {
	 $('#newloaderPopup').modal('show');
	var declarationTitle = document.getElementById('declarationTitle').value;
	var declarationNotes = nicEditors.findEditor("declarationNotes").getContent()
	document.getElementById('declarationNotes1').value = declarationNotes;
	document.getElementById('declarationTitle1').value = declarationTitle;
	document.getElementById('savedeclarationform').submit();
}
function showPopUpwithfilter(){
	
var type=document.getElementById('treatmenttype').value;
	var url = "showclientwithfilterClient?type="+type+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showPopUpwithfilterRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}



	function showPopUpwithfilterRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allPatient").innerHTML = req.responseText;
				
				$('#clientSearch').modal('show');
				
	         }
		}
	}
	
	function closewithpopup(val){
		 $('#newloaderPopup').modal('show');
		openSamePopup("iclosedProcessingAccount?id="+val+"");
	}
	function postwithpopup(val){
		 $('#newloaderPopup').modal('show');
		openSamePopup("ipostProcessingAccount?id="+val+"");
	}

    function generatedeclaration(id){
	var url = "payrolldecDeclaration?id="+id+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = generatedeclarationRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function generatedeclarationRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			goreferesh();
		}
	}
}