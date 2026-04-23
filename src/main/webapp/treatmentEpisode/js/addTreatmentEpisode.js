var clientname = "";
var treatmentpolicyno = "";
$(document).ready(function(){
	 var today = new Date();
	 
	$( "#treatmentStartDate" ).datepicker({
		 
		 	 dateFormat:'dd/mm/yy',
		 	yearRange: yearrange,
		 	 minDate : '30/12/1880',
			 changeMonth: true,
		     changeYear: true	 
	 });
	
	$( "#referalDate" ).datepicker({
		 
		 	 dateFormat:'dd/mm/yy',
		 	yearRange: yearrange,
		 	 minDate : '30/12/1880', 
			 changeMonth: true,
		     changeYear: true	 
	 });
	 
	 $( "#referalendDate" ).datepicker({
		 
		 	 dateFormat:'dd/mm/yy',
		 	yearRange: yearrange,
		 	 minDate : '30/12/1880', 
			 changeMonth: true,
		     changeYear: true	 
	 });
	 

	$( "#thirdPartyExpiryDate" ).datepicker({
			
		 	 dateFormat:'dd/mm/yy',
		 	 yearRange: yearrange,
		 	 minDate: today,
			 changeMonth: true,
		     changeYear: true	 
	 });
	
	 clientname = read_cookie("cookieUserName");
	
	var cookiecommencing=read_cookie("cookiecommencing");
/*	var tempdate = cookiecommencing.split('-');
	cookiecommencing = tempdate[2]+'/'+tempdate[1]+'/'+tempdate[0];*/
	var practitionerid = read_cookie("cookiePractitionerId");
	var clientid = read_cookie("cookieClientId");
	
	
	//document.getElementById('namediv').innerHTML = clientname + ','
	//document.getElementById('treatmentStartDate').value = cookiecommencing;
	//document.getElementById('diaryUser').value = practitionerid;
	//document.getElementById('invoicee').value = clientname;
	document.getElementById('clientId').value = clientid;
	
});		
	
	
function closeTreatment(){

	document.getElementById('treatmentEpisodeFrm').submit();
	window.close();
	return false;
}








function updateTreatment(){
	var payby = "";
	var chk=0;
	if(document.getElementById('paybyClient').checked)
	{
		payby = "Client";
	}
	else{
		payby = "Third Party";
	}
 	
	/*var client = document.getElementById('client').value;
	var clientId = document.getElementById('clientId').value;
	var date = document.getElementById('date').value;
	var diaryUser = document.getElementById('user').value;*/
	var treatmentEpisodeName = document.getElementById('treatmentEpisodeName').value;
	/*var referalDate = document.getElementById('referalDate').value;
	var referralName = document.getElementById('referralName').value;
	var referralSource = document.getElementById('referralSource').value;
	var referralContact = document.getElementById('referralContact').value;
	var referralLetter = document.getElementById('referralLetter').value;*/
	var invoicee = document.getElementById('invoicee').value;
	var authorisationCode = document.getElementById('authorisationCode').value;
	var spendLimit = document.getElementById('spendLimit').value;
	var consultationLimit = document.getElementById('consultationLimit').value;
	var condition = document.getElementById('treatmentType2').value;
	
	document.getElementById('thirdPartyNameError').innerHTML = "";
	document.getElementById('invoiceeError').innerHTML = "";
	document.getElementById('authorisationCodeError').innerHTML = "";
	//document.getElementById('spendLimitError').innerHTML = "";
	document.getElementById('consultationLimitError').innerHTML = "";
	document.getElementById('treatmentNameError').innerHTML ="";
	
	
	
	if(payby == 'Third Party'){
		
		  if (authorisationCode ==  "") {
	      	var authorisationCode = document.createElement("label");
	      	authorisationCode.innerHTML = "Enter Code";
	        document.getElementById('authorisationCodeError').appendChild(authorisationCode);
	        chk=1;
	     }  
	}
	if(condition == "0"){
		var condition = document.createElement("label");
		condition.innerHTML = "Select Condition";
        document.getElementById('treatmentTypeError').appendChild(condition);
        chk=1;
	}
	if (invoicee ==  "") {
      	var invoicee = document.createElement("label");
      	invoicee.innerHTML = "Enter Invoicee";
        document.getElementById('invoiceeError').appendChild(invoicee);
        chk=1;
	}
		/*if (spendLimit ==  "") {
	      	var spendLimit = document.createElement("label");
	      	spendLimit.innerHTML = "Enter Spend Limit";
	        document.getElementById('spendLimitError').appendChild(spendLimit);
	        chk=1;
	     }  */
		if (consultationLimit ==  "") {
	      	var consultationLimit = document.createElement("label");
	      	consultationLimit.innerHTML = "Enter Consultation Limit";
	        document.getElementById('consultationLimitError').appendChild(consultationLimit);
	        chk=1;
	     }  
	
   	if (treatmentEpisodeName ==  "") {
      	var treatmentEpisodeName = document.createElement("label");
      	treatmentEpisodeName.innerHTML = "Enter Name";
        document.getElementById('treatmentNameError').appendChild(treatmentEpisodeName);
        chk=1;
     }  
   	
   	
   	if(chk == 1)
    {
       return false;
    }
    else
    {
   	 return true;
    }
	
	
}


function addThirdPartyName(){
	$('#addPatientThirdPartyPopup').modal( "show" );
}

function setCompanyName(id){
	//alert(id);

		var url = "setCompanyNameDropDownClient?id="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setTPCompanyNameRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}


function setTPCompanyNameRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {			
				
	    		document.getElementById("ctpName").innerHTML = req.responseText;
	    		$("#ctpName").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				
				
	         }
		}
	 
	}
	
	function savePatientThirdPartyDetail(){
		var chk = 0;
		var clientId = document.getElementById("clientId").value;
		var thirdPartyType = document.getElementById("ctpthirdPartyType").value;
		var companyName = document.getElementById("ctpName").value;
		var thirdPartyPolicyNo = document.getElementById("thirdPartyPolicyNo").value;
		var thirdPartyExpiryDate = document.getElementById("thirdPartyExpiryDate").value;
		var tppolicyExcess = document.getElementById("tppolicyExcess").value;
		
		 document.getElementById('thirdPartyTypeError').innerHTML="";
		 document.getElementById('companyNameError').innerHTML="";
		 document.getElementById('thirdPartyPolicyNoError').innerHTML="";
		 document.getElementById('thirdPartyExpiryDateError').innerHTML="";
		
		if (thirdPartyType ==  "0") {
	      	var thirdPartyType = document.createElement("label");
	      	thirdPartyType.innerHTML = "Select Type";
	        document.getElementById('thirdPartyTypeError').appendChild(thirdPartyType);
	        chk=1;
	     }  
		if (companyName ==  "0" || companyName ==  "") {
	      	var companyName = document.createElement("label");
	      	companyName.innerHTML = "Select Company";
	        document.getElementById('companyNameError').appendChild(companyName);
	        chk=1;
	     }  
		if(thirdPartyType == 2){
			if (thirdPartyPolicyNo ==  "") {
	      	var thirdPartyPolicyNo = document.createElement("label");
	      	thirdPartyPolicyNo.innerHTML = "Enter Policy No.";
	        document.getElementById('thirdPartyPolicyNoError').appendChild(thirdPartyPolicyNo);
	        chk=1;
			}  
			/*if (thirdPartyExpiryDate ==  "") {
	      	var thirdPartyExpiryDate = document.createElement("label");
	      	thirdPartyExpiryDate.innerHTML = "Enter Expiry Date";
	        document.getElementById('thirdPartyExpiryDateError').appendChild(thirdPartyExpiryDate);
	        chk=1;
	     }  */
			}
		if(chk==1)
	    {
	       return false;
	    }
	    else
	    {
	    
	    	treatmentpolicyno = thirdPartyPolicyNo;
	    	
	    	var url = "addThirdPartyDetailsClient?clientId="+clientId+"&thirdPartyType="+thirdPartyType+"&companyName="+companyName+"&thirdPartyPolicyNo="+thirdPartyPolicyNo+"&thirdPartyExpiryDate="+thirdPartyExpiryDate+"&tppolicyExcess="+tppolicyExcess+" ";

	    	if (window.XMLHttpRequest) {
	    			req = new XMLHttpRequest();
	    		}
	    		else if (window.ActiveXObject) {
	    			isIE = true;
	    			req = new ActiveXObject("Microsoft.XMLHTTP");
	    		}   
	    	               
	    	    req.onreadystatechange = savePatientThirdPartyDetailRequest;
	    	    req.open("GET", url, true); 
	    	              
	    	    req.send(null);


	    	    return true;
	    }
		
		
	}
function savePatientThirdPartyDetailRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			
    		document.getElementById("invoiceeTd").innerHTML = req.responseText;
    		
    		document.getElementById('policynodiv').style.display='';
			document.getElementById('trtmentPolicyNo').value = treatmentpolicyno;
    		
    		document.getElementById('authorisationCode').disabled = '';
			document.getElementById('spendLimit').disabled = '';
			document.getElementById('consultationLimit').disabled = '';
    		jAlert('success', 'Third Party Details added successfully.', 'Patient');
    		setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
    		
    		resetPatientThirdPartyDetails();
    		$('#addPatientThirdPartyPopup').modal( "hide" );
         
         }
	}
}

function setPayBy(payby){
	
	if(payby == 'Third Party'){
		document.getElementById('authorisationCode').disabled = '';
		
		document.getElementById('policynodiv').style.display='';
		document.getElementById('trtmentPolicyNo').value = treatmentpolicyno;
		
		
		setThirdPartyNameAjax();
	}else{
		
		document.getElementById('policynodiv').style.display='none';
		
		var client1 = document.getElementById('client').value;
		if(client1 == "" || client1 == undefined || client1 == null){
			client1 = document.getElementById('tempclientname').value;
		}
		document.getElementById('invoicee').value = client1;
		document.getElementById("authorisationCode").disabled = true;
		document.getElementById('spendLimit').disabled = false;
		document.getElementById('consultationLimit').disabled = false;
		
		
		
		
	}
	

}	

function setThirdPartyNameAjax(){
	
	//var clientid = clientId;
	
	var patientId = document.getElementById('clientId').value;
	if(patientId == "" || patientId == undefined || patientId == null){
		patientId = document.getElementById('tempclientid').value;
	}
	
	var url = "thirdpartyTreatmentEpisode?clientid="+patientId+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setThirdPartyNameAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setThirdPartyNameAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			//document.getElementById('invoicee').value = req.responseText;
			if(req.responseText == 'null' || req.responseText == " "){
				document.getElementById('invoicee').value = '';
				document.getElementById('authorisationCode').disabled = false;
				document.getElementById('spendLimit').disabled = false;
				document.getElementById('consultationLimit').disabled = false;
				
			}
			else{
				document.getElementById('invoicee').value = req.responseText;
			}
		}
	}

}

var selectedlimit=1;

function checkconsultationLinit(limit){
	if(parseInt(limit)<parseInt(selectedlimit)){
			document.getElementById('consultationLimit').value  = selectedlimit;
			jAlert('error', 'Consulattion limit can not be less than '+selectedlimit+'', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
}
