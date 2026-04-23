var clientIdOfAssessmnet = ""; 
var globalClientId = "";
var globalpractId = "";

function showPopUp2(){
		var url = "showListListAssessmentForm";
		
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
	
	function searchPatientAssessment(){
		var searchText = document.getElementById("searchText1").value;
		var url = "searchPatientAssesmentForms?searchText="+searchText+"";

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
	
	function setPatientNameAssessment(name,id,type,typeName){
		clientIdOfAssessmnet = id;
		document.getElementById("clientId").value = id;
		var url = "getFullNameClient?id="+id+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setPatientNameAssessmentRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		
		
		
	        
			
	}
	function setPatientNameAssessmentRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var clientFullName = req.responseText;
			
				document.getElementById("client").value = clientFullName;
				
				 showTemplateListDropDown(clientIdOfAssessmnet);
				
				$('#clientSearch').modal('hide');
				
				
				
			}
		}

	}
	
	function showTemplateListDropDown(id){
		var clientId = document.getElementById('clientnameAssessment').value;
		var practId = document.getElementById('diaryUserAssessment').value;
		
		var url = "setDropDownOfTemplateListAssessmentForm?clientId="+clientId+"&practId="+practId+"&conditionId="+id+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showTemplateListDropDownRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	function showTemplateListDropDownRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				
	    		//document.getElementById("templateName1").innerHTML = req.responseText;

	    		document.getElementById("templateName1").innerHTML =  req.responseText;
				$("#templateName1").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});	
				
				
				
				
				
			}
		}

	}
	
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
	
	
	function setClientsAssessment(practId){
		
		var url = "setClientsAssesmentForms?practId="+practId+"";	
			
		globalpractId = practId;
		
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = setClientsAssessmentRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			
		}

		function setClientsAssessmentRequest(){
		if (req.readyState == 4) {
				
				
					if (req.status == 200) {
						document.getElementById("clientnameAssessment").innerHTML =  req.responseText;
						$("#clientnameAssessment").trigger("chosen:updated");
						$(".chosen").chosen({allow_single_deselect: true});	
						
					}
					
			}
		}
		
		function setConditionAssessment(clientId){
			
			var url = "setClientConditionAssesmentForms?clientId="+clientId+"";	
			
			globalClientId = clientId;
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = setConditionAssessmentRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
		function setConditionAssessmentRequest(){
			if (req.readyState == 4) {
				
				
				if (req.status == 200) {
					document.getElementById("conditionAssessment").innerHTML =  req.responseText;
					$("#conditionAssessment").trigger("chosen:updated");
					$(".chosen").chosen({allow_single_deselect: true});				
					
				}
				
		}

		}
		
		