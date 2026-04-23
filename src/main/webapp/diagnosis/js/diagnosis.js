/**
 * 
 */


var id;
var problem_id;
var intervention_id;

function saveDiagnosisandContinue() {
	
	var name=document.getElementById("name").value;
	var diagnosisname = document.getElementById("diagnosisname").value;	
	var url="savediagnosisDiagnosis?name="+name+"&diagnosisname="+diagnosisname+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveDiagnosisandContinueRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function saveDiagnosisandContinueRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("step2").innerHTML = req.responseText;
         }
	}
}
	

function setproblemlist(id) {
	
	this.id=id;
	var url="listproblemsinidDiagnosis?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setproblemlistRequest;
    req.open("GET", url, true); 
              
    req.send(null);      

}

function setproblemlistRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("step3").innerHTML = req.responseText;
				
	         }
		}
	}

function setproblemlist1(id) {
	
	this.id=id;
	var url="listproblemsinidDiagnosis?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setproblemlistRequest1;
    req.open("GET", url, true); 
              
    req.send(null);      

}

function setproblemlistRequest1(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("stepp3").innerHTML = req.responseText;
				
	         }
		}
	}

   




   function setproblemid(id) {
	   
	   problem_id=id;
   }
   
   function setinterventionid(id) {
	   intervention_id=id;
   }
   
   function saveProblemandContinue() {
	   
	    var problemname=document.getElementById("problem_name").value;	
		var url="saveproblemDiagnosis?problemname="+problemname+"&id="+id+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveProblemandContinueRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);	  
   }
   
   
   function saveProblemandContinueRequest() {
	   
	   if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("stepp2").innerHTML = req.responseText;
				
	         }
		}
   }
   
   function saveintervention() {
	   
	   
	   var intervention=document.getElementById("intervention").value;
	   
	   var url="saveinterventionDiagnosis?intervention="+intervention+"&id="+id+"&problem_id="+problem_id+"";
	   
	   if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveinterventionRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);	  
	   
	   
   }
   
 function saveinterventionRequest() {
	   
	   if (req.readyState == 4) {
			if (req.status == 200) {
			
			
			}
		}
   }
	
