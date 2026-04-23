
var checkMailSend = '';

function setChangeValueCheckEmail(id){
	
	//alert(document.getElementById('checkMailSend').checked);
	checkMailSend = document.getElementById('checkMailSend').checked;
	
	var url = "checkMailSendClinicRegistration?checkMailSend="+checkMailSend+"";

	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setChangeValueCheckEmailRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
	


function setChangeValueCheckEmailRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
			//alert(checkMailSend);
			//var msg = " " + document.getElementById('message').value;
			if(checkMailSend == true){
				
				showGrowl('', 'Email Sending Enabled !!', 'success', 'fa fa-check');
				
			}else{
				
				showGrowl('', ' Email Sending Disabled !!', 'success', 'fa fa-check');
			}
			
			document.getElementById("checkMailSend").innerHTML = req.responseText;
			
			//showGrowl('', 'Changed Email Sending Setting Successfully !!', 'success', 'fa fa-check');
		}
}
}


function  saveValidation(){
	validatePromoJSON = {
			
            "validatorData": [
                              { "Element": "#emailUserName", "FunctionName": "emailId" },
                              { "Element": "#emailUserName", "FunctionName": "notEmpty" },
                             /* { "Element": "#emailPassword", "FunctionName": "notEmpty" },
                              { "Element": "#emailConfirmPassword", "FunctionName": "notEmpty" },
                              { "Element": "#emailPassword", "ElementToCompare": "#emailConfirmPassword", "FunctionName": "password" },
                             */ { "Element": "#emailHostName", "FunctionName": "webmailOptional" },
                              { "Element": "#emailHostName", "FunctionName": "notEmpty" }
            ]
        };
	return iterateThroughtElemsForValidations(validatePromoJSON);
}

function saveValidationDNA(){
	
validatePromoJSON = {
			
            "validatorData": [
                              { "Element": "#dnaCharges", "FunctionName": "numbersOnlyOptional" },
                              { "Element": "#dnaCharges", "FunctionName": "notEmpty" }
                              ]
};
return iterateThroughtElemsForValidations(validatePromoJSON);
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

function changePwdValidate(){
	
validatePromoJSON = {
			
            "validatorData": [
                             // { "Element": "#emailUserName", "FunctionName": "emailId" },
                            //  { "Element": "#emailUserName", "FunctionName": "notEmpty" },
                              { "Element": "#emailPassword", "FunctionName": "notEmpty" },
                              { "Element": "#emailConfirmPassword", "FunctionName": "notEmpty" },
                              { "Element": "#emailPassword", "ElementToCompare": "#emailConfirmPassword", "FunctionName": "password" }
                             // { "Element": "#emailHostName", "FunctionName": "webmailOptional" },
                             // { "Element": "#emailHostName", "FunctionName": "notEmpty" }
            ]
        };
	return iterateThroughtElemsForValidations(validatePromoJSON);
	
}


function showPassword(){
	
	var showPwd = document.getElementById('showPwd').checked;
	if(showPwd == true){
		
		var url = "showEmailPasswordEmailTemplate?showPwd="+showPwd+"";

		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showPasswordRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	else{
		document.getElementById('showpd').style.display = 'none';
	}
}

function showPasswordRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('showpd').style.display = '';
				document.getElementById('showpd').innerHTML = req.responseText;
			}
	}
}
			
