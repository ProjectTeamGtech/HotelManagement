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
	var userName = document.getElementById('userName').value;	
	var templateName = document.getElementById('templateName').value;
	var headerNote = nicEditors.findEditor('headerNote').getContent();
	var body1Note =	nicEditors.findEditor('body1Note').getContent();
	var body2Note =	nicEditors.findEditor('body2Note').getContent();
	var footerNote = nicEditors.findEditor('footerNote').getContent();
	
	document.getElementById("unameError").innerHTML = "";
	document.getElementById("tnameError").innerHTML = "";
	document.getElementById("headerError").innerHTML = "";
	document.getElementById("body1Error").innerHTML = "";
	document.getElementById("body2Error").innerHTML = "";
	document.getElementById("footerError").innerHTML = "";
	
	var chk = 0;	
	//alert(headerNote);
	if(userName == "Select"){		
		var uname = document.createElement("label");
		uname.innerHTML = "Please Select User name";
	    document.getElementById('unameError').appendChild(uname);
	    chk=1;
	}
	
	if(templateName == ""){		
		var tname = document.createElement("label");
		tname.innerHTML = "Please add Template name";
	    document.getElementById('tnameError').appendChild(tname);
	    chk=1;
	}
	if(headerNote == "" || headerNote == "<br>"){		
		var header = document.createElement("label");
		header.innerHTML = "Please add Header Note";
	    document.getElementById('headerError').appendChild(header);
	    chk=1;
	}
	if(body1Note == "" || body1Note == "<br>"){
		var body1 = document.createElement("label");
		body1.innerHTML = "Please add Body1 note";
	    document.getElementById('body1Error').appendChild(body1);
	    chk=1;
	}
	if(body2Note == "" || body2Note == "<br>"){
		var body2 = document.createElement("label");
		body2.innerHTML = "Please add Body2 note";
	    document.getElementById('body2Error').appendChild(body2);
	    chk=1;
	}
	if(footerNote == "" || footerNote == "<br>"){
		var footer = document.createElement("label");
		footer.innerHTML = "Please add Footer Note";
	    document.getElementById('footerError').appendChild(footer);
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

var userName = "";

function setName(id){	
	
	userName = document.getElementById('userName').value;	
	
	if(userName == "Select"){
		
		document.getElementById("user").value = '';
		document.getElementById("user").disabled = 'disabled';
		//document.getElementById("toEmailId").value = '';
		//document.getElementById("templateName").innerHTML = '';
		$("#templateName").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		var data = '';				
		setTimeout(function(){
			$(".nicEdit-main").html(data);			
		}, 1000);
		
		return false;
	}
	else{
		
	var url = "showTemplateListInstantMsg?userName="+userName+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showNameRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	    return true;
	}

	}
	function showNameRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				
				//document.getElementById('templateName').disabled = '';
				document.getElementById("templateName").innerHTML = req.responseText;
	    		$("#templateName").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				document.getElementById("user").disabled = '';
				document.getElementById("user").value = '';
				//document.getElementById("toEmailId").value = '';
				var data = '';				
				setTimeout(function(){
					$(".nicEdit-main").html(data);			
				}, 1000);
				
	         }
		}
	}
	


function showPopUp2(){
	
	//var userName = document.getElementById('userName').value;
	document.getElementById("unameError").innerHTML = "";
	var chk = 0;	
	//alert(headerNote);
	
	if(userName == ""){		
		var uname = document.createElement("label");
		uname.innerHTML = "Please select User name";
	    document.getElementById('unameError').appendChild(uname);
	    $('#userSearch').modal('hide');
	    return false;  
	}
    else
    {
	
	var url = "showUserListInstantMsg?userName="+userName+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAllPatientPopUpRequestemail;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	    return true;
    }
	}
	function showAllPatientPopUpRequestemail(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			//alert(req.responseText)
				document.getElementById("allTemplatePatient").innerHTML = req.responseText;
				
	         	
				
	         }
		}
	}
	
	function setUserDetail(id){
		//deleteChargeAccountsTableAjax();
		//document.getElementById("clientId").value = id;
	
		document.getElementById("id").value = id;
		var url = "getPatientDetailsInstantMsg?id="+id+"&userName="+userName+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setUserDetailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);        
			
	}
	
	function setUserDetailRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				var data = req.responseText;
				var temp = data.split("/");
				var clientFullName = temp[0];			
				//alert(clientFullName);		
				var email= temp[1];
							
				//document.getElementById('user').value = clientFullName;	
				document.getElementById("user").value = clientFullName;	
				//document.getElementById('templateName').disabled = '';
				
					document.getElementById('toEmailId').value = email;
			
				
				
				$('#userSearch').modal('hide');
				
				
				
			}
		}

	}
	
	function searchTemplatePatient(){
		
		var searchText = document.getElementById("searchText1").value;
		var url = "searchTemplatePatientInstantMsg?searchText="+searchText+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = searchTemplatePatientRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

		}
function searchTemplatePatientRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					
				
					document.getElementById("allTemplatePatient").innerHTML = req.responseText;
		         	
					
		         }
			}
		}
	
	function preview(){
		
		var templateName = document.getElementById('templateName').value;
		document.getElementById("tnameError").innerHTML = "";
		var chk = 0;	
		//alert(headerNote);
		
		if(templateName == "All"){		
			var tname = document.createElement("label");
			tname.innerHTML = "Please select Template name";
		    document.getElementById('tnameError').appendChild(tname);
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
	
	function showTemplateDetails(id){		
		
		var id1 = document.getElementById("id").value;
		var templateId = document.getElementById('templateName');
		 var templateName = templateId.options[templateId.selectedIndex].text;
		// alert(templateId);
		var url = "getTemplateDetailsTPReferal?templateName="+templateName+"&userName="+userName+"&id1="+id1+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setTemplateDetailsRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);        
			
	}
	function setTemplateDetailsRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				var data = req.responseText;
				//var temp = data.split("/");
				//var header = temp[0];			
				//var templatename = temp[4];
				
				
				setTimeout(function(){
					$(".nicEdit-main").html(data);			
				}, 1000);
				
					
			}
		}
	}
	
	function sendLetter(){
		
		$('#sendLetterPopup').modal( "show" );
		
	}
	
	function createPdf(){
		
		//alert('hi');
		var user = document.getElementById('user').value;
		//alert(user);
		var templateData = nicEditors.findEditor('emailBody').getContent();
		//alert(templateData);
		//var user = document.getElementById('user');
		
		var url = "createPDFEmailTemplate?templateData="+templateData+"&user="+user+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = createPdfRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);        
			
	}
	function createPdfRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				/*$('#sendLetterPopup').modal('hide');*/
				
									
			}
		}
	}
	
	function sendmail(){
		
		
		
		$( "#sendEmailPopUp2" ).modal( "show" );
	}

	function sendLetter(){
		//alert('hi');
		$('#sendLetterPopup').modal( "show" );
	}


