/**
 * 
 */
function getlist(type){
	
	document.getElementById("feedbackform").submit();	
		

	
	
}


function getPatient(id){
	document.getElementById("patient").value= id;
var x=document.getElementById("treatmenttype").value;
document.getElementById("treatmenttype1").value= x;
}
 


function checkifChecked(){
	document.getElementById("savefdfrm").style.visibility = "hidden";
	var x=document.getElementById("client").value;
	if(x==""){
		  jAlert('error', "Please Select Patient", 'Error Dialog');
        setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
        document.getElementById("savefdfrm").style.visibility = "visible";
	}
	else{
		document.getElementById("savefdfrm").style.visibility = "hidden";
		document.getElementById("invoicerportfrm").submit();
		
	}
	
}

function allfeedbackpatients(){
	var type=document.getElementById("treatmenttype").value;
	var url="getallpatientlistForfeedbackClient?type="+type+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
         
	
	
    req.onreadystatechange = allfeedbackpatientsRequest;
    req.open("GET", url, true); 
              
    req.send(null)
	
}


function allfeedbackpatientsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var txt = req.responseText;
			
			document.getElementById("allPatient").innerHTML=txt;
			
		}
	}
}
function showPopUp(){
	
	var url = "Client";

	$('#clientSearchDiv').modal( "show" );
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
	