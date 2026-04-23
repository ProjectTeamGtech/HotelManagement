//Tab Validations handler
var validchkkemail = 0;
var validchkcomnyemail = 0;
var validchktelephoneline = 0;
var validchkcomanytele = 0;

$(document).ready(function(){
	var l1 = document.getElementById('danyamiclistpresent').value;
	
	if(document.getElementById('typeName1').value > 0 && l1 == 1)
	{
		
		document.getElementById("dynamicallyAddApmtTable").style.display = "block";
		
		document.getElementById("deletefiled").style.display = "none";
	}
	
	if(document.getElementById('typeName1').value == 0){
		document.getElementById("companyName").disabled = false;
	}
	var id = document.getElementById("type1").value;
	
if(id==1){
		
		document.getElementById("doctorsurgerysaveid").style.display = 'block';
		document.getElementById("gpcontactdiv").style.display = 'block';
		document.getElementById("insurenceorgroupsaveid").style.display = 'none';
		document.getElementById("accountSetting").style.display = 'none';

	}
	else{
		document.getElementById("doctorsurgerysaveid").style.display = 'none';
		document.getElementById("gpcontactdiv").style.display = 'none';
		document.getElementById("accountSetting").style.display = 'block';
		document.getElementById("insurenceorgroupsaveid").style.display = 'block';


	}


	
});	
$(document).on("click", ".tabAnchor", function () {
	var currentElem=$(this);
	var targetId = $(this).attr("data-target-div");
	var validationResule=true;
	$(".tab-pane").each(function (){
		if($(this).hasClass('active')){
			var validationFun = $(this).attr("data-validation-function");
			
			if(window[validationFun]()){				
				$(this).removeClass("active in");
				var activeTabId=$(this).attr("data-tab-id");
				$(activeTabId).parent().removeClass("active");
				
				$(currentElem).parent().addClass("active");
				$(targetId).addClass("active in");
				
				validationResule= false;
			}
			if(!validationResule){
				return validationResule;
			}
		}
	});
	
});

//Open Nex Tab
$(document).on("click", ".next, .previous", function () {
	//var currentElem=$(this);
	//var currentTab=$(this).attr("data-parent-tab");
	//var currentDiv=$(this).attr("data-parent-div");
	var targetDivId = $(this).attr("data-target-div");
	var targetTabId = $(this).attr("data-target-tab");
	var validationResule=true;
	$(".tab-pane").each(function (){
		if($(this).hasClass('active')){
			var validationFun = $(this).attr("data-validation-function");
			
			if(window[validationFun]()){				
				$(this).removeClass("active in");
				var activeTabId=$(this).attr("data-tab-id");
				$(activeTabId).parent().removeClass("active");
				
				$(targetTabId).parent().addClass("active");
				$(targetDivId).addClass("active in");
				
				validationResule= false;
			}
			if(!validationResule){
				return validationResule;
			}
		}
	});
	
});

//Validation functions goes here

function validatePD(){
	validatePromoJSON = {
            "validatorData": [
                    { "Element": "#type", "FunctionName": "dropDown" }
                   /* { "Element": "#name", "FunctionName": "alphabetsOnlyWithSpace" },
                    { "Element": "#telephoneLine", "FunctionName": "numbersOnlyCompulsary" },
                    { "Element": "#email", "FunctionName": "emailId" },
                    { "Element": "#emailCc", "FunctionName": "emailIdOptional" }*/
                  
            ]
        };
	
	return iterateThroughtElemsForValidations(validatePromoJSON);
}
function validateCD(){
	validatePromoJSON = {
            "validatorData": [
                   // { "Element": "#companyName", "FunctionName": "alphabetsOnlyWithSpaceOptional" },
//                    { "Element": "#address", "FunctionName": "alphabetsOnlyWithSpace" },
//                    { "Element": "#town", "FunctionName": "alphabetsOnlyWithSpace" },
//                    { "Element": "#country", "FunctionName": "alphabetsOnlyWithSpace" },
                  //  { "Element": "#compnyTelephone", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" }
                  
            ]
        };
	
	return iterateThroughtElemsForValidations(validatePromoJSON);
}
function validateAS(){
	validatePromoJSON = {
            "validatorData": [
                   
                    { "Element": "#outInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#creditDuration", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#accountWarningLimit", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#creditReminderDuration", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#dnaLimit", "FunctionName": "numbersOnlyOptional" }
                    
                    
                    
                  
            ]
        };
	
	return iterateThroughtElemsForValidations(validatePromoJSON);
}

function validateCP(){
	validatePromoJSON = {
            "validatorData": [
                           //   { "Element": "#confContactEmail", "FunctionName": "emailId" },
                             // { "Element": "#dnaContactEmail", "FunctionName": "emailId" }
                  
            ]
        };
	
	return iterateThroughtElemsForValidations(validatePromoJSON);
}


function validateTPDetail(){
	validatePromoJSON = {
            "validatorData": [
                              { "Element": "#type", "FunctionName": "dropDown" }, 
                              { "Element": "#name", "FunctionName": "alphabetsOnlyWithSpace" },
                             // { "Element": "#telephoneLine", "FunctionName": "phoneOptional" },
                              { "Element": "#email", "FunctionName": "emailIdOptional" },
                              //{ "Element": "#companyName", "FunctionName": "alphabetsOnlyWithSpaceOptional" },
                              { "Element": "#address", "FunctionName": "alphabetsOnlyWithSpace" },
                              { "Element": "#town", "FunctionName": "alphabetsOnlyWithSpace" },
                              { "Element": "#compnyTelephone", "FunctionName": "numbersOnlyCompulsary" },
                              { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
                              { "Element": "#outInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
                              { "Element": "#creditDuration", "FunctionName": "numbersOnlyOptional" },
                              { "Element": "#accountWarningLimit", "FunctionName": "numbersOnlyOptional" },
                              { "Element": "#creditReminderDuration", "FunctionName": "numbersOnlyOptional" },
                              { "Element": "#dnaLimit", "FunctionName": "numbersOnlyOptional" }
                             
                  
            ]
        };
	
	if(validchkkemail == 0 && validchkcomnyemail == 0 && validchktelephoneline == 0 && validchkcomanytele == 0){
		return iterateThroughtElemsForValidations(validatePromoJSON);
	}
	else {
		return false;
	}


}

function checkEmailValidation(email){
	
	var url = "checkEmailExistThirdParty?email="+email+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkEmailValidationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkEmailValidationRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
					validchkkemail = 1;
					
				}
				
				 if(chk == 1)
			     {	
					 document.getElementById('emailError').innerHTML="";
					 var email = document.createElement("label");
					 email.innerHTML = "Email already Exist Please try another";
				     document.getElementById('emailError').appendChild(email);
				     chk = 1;
				     validchkkemail = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('emailError').innerHTML="";
			    	
			    	 chk = 0;
			    	 validchkkemail = 0;
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
	}

function checkComapnyEmailValidation(email){
	
	var url = "checkEmailExistThirdParty?email="+email+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkComapnyEmailValidationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkComapnyEmailValidationRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
					validchkcomnyemail = 1;
					
				}
				
				 if(chk == 1)
			     {	
					 document.getElementById('compnyEmailError').innerHTML="";
					 var email = document.createElement("label");
					 email.innerHTML = "Email already Exist Please try another";
				     document.getElementById('compnyEmailError').appendChild(email);
				     chk = 1;
				     validchkcomnyemail = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('compnyEmailError').innerHTML="";
			    	
			    	 chk = 0;
			    	 validchkcomnyemail = 0;
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
	}


function checTelePhoneLineValidation(mob){
	
	
	var url = "checkMobileExistThirdParty?mob="+mob+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checTelePhoneLineValidationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checTelePhoneLineValidationRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
					validchktelephoneline = 1;
				}
				
				 if(chk == 1)
			     {	
					 document.getElementById('telephoneLineError').innerHTML="";
					 var mob = document.createElement("label");
					 mob.innerHTML = "No already Exist Please try another";
				     document.getElementById('telephoneLineError').appendChild(mob);
				     chk = 1;
				     validchktelephoneline = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('telephoneLineError').innerHTML="";
			    	
			    	 chk = 0;
			    	 validchktelephoneline = 0;
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
	}

function checkComnyTelePhoneLineValidation(mob){
	
	
	var url = "checkMobileExistThirdParty?mob="+mob+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkComnyTelePhoneLineValidationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkComnyTelePhoneLineValidationRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
					validchkcomanytele = 1;
				}
				
				 if(chk == 1)
			     {	
					 document.getElementById('compnyTelephoneError').innerHTML="";
					 var mob = document.createElement("label");
					 mob.innerHTML = "No already Exist Please try another";
				     document.getElementById('compnyTelephoneError').appendChild(mob);
				     chk = 1;
				     validchkcomanytele = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('compnyTelephoneError').innerHTML="";
			    	
			    	 chk = 0;
			    	 validchkcomanytele = 0;
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
	}
function validEntry(){

var confContactEmail = document.getElementById('confContactEmail').value;
var dnaContactEmail = document.getElementById('dnaContactEmail').value;
document.getElementById('errorDiv').innerHTML="";
var chk=0;
       if (confContactEmail ==  "") {
      	var confContactEmail = document.createElement("label");
        confContactEmail.innerHTML = "Enter Email in Contact Preference Tab";
        document.getElementById('errorDiv').appendChild(confContactEmail);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
     }   
     if (dnaContactEmail ==  "") {
      	var dnaContactEmail = document.createElement("label");
        dnaContactEmail.innerHTML = "Enter  DNA Email in Contact Preference Tab";
        document.getElementById('errorDiv').appendChild(dnaContactEmail);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
     }     
     
     if(chk==1)
     {
        return false;
     }
     else
     { 
         jAlert('success', 'Third Party Details successfully.', 'Success Dialog');
         
     	setTimeout(function() {
			$("#popup_container").remove();
			$("#popup_overlay").css({
				position: '',
				zIndex: 0,
				top: '0px',
				left: '0px',
				width: '',
				height: '',
				background: '',
				opacity:''
			});
		}, alertmsgduration);

        return true;
     }

}
function validEntryUpadte(){

	var confContactEmail = document.getElementById('confContactEmail').value;
	var dnaContactEmail = document.getElementById('dnaContactEmail').value;
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;
	       if (confContactEmail ==  "") {
	      	var confContactEmail = document.createElement("label");
	        confContactEmail.innerHTML = "Enter Email in Contact Preference Tab";
	        document.getElementById('errorDiv').appendChild(confContactEmail);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }   
	     if (dnaContactEmail ==  "") {
	      	var dnaContactEmail = document.createElement("label");
	        dnaContactEmail.innerHTML = "Enter  DNA Email in Contact Preference Tab";
	        document.getElementById('errorDiv').appendChild(dnaContactEmail);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }     
	     
	     if(chk==1)
	     {
	        return false;
	     }
	     else
	     { 
	         jAlert('success', 'Third Party Details Updated successfully.', 'Success Dialog');
	         
	     	setTimeout(function() {
				$("#popup_container").remove();
				$("#popup_overlay").css({
					position: '',
					zIndex: 0,
					top: '0px',
					left: '0px',
					width: '',
					height: '',
					background: '',
					opacity:''
				});
			}, alertmsgduration);

	        return true;
	     }

	}

function showNextCompanyDetails(){
	var name = document.getElementById('name').value;
	var telephoneLine = document.getElementById('telephoneLine').value;
	var email = document.getElementById('email').value;
	var type = document.getElementById('type').value;
	
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;
	if (type ==  "0") {
      	var type = document.createElement("label");
      	type.innerHTML = "Select Type in Personal Details Tab";
        document.getElementById('errorDiv').appendChild(type);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
     }
	 	if (name ==  "") {
	      	var name = document.createElement("label");
	        name.innerHTML = "Enter Name in Personal Details Tab";
	        document.getElementById('errorDiv').appendChild(name);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }   
	     if (telephoneLine ==  "") {
	      	var telephoneLine = document.createElement("label");
	        telephoneLine.innerHTML = "Enter Telephone Line in Personal Details Tab";
	        document.getElementById('errorDiv').appendChild(telephoneLine);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }  
	      if (email ==  "") {
	      	var email = document.createElement("label");
	        email.innerHTML = "Enter Email in Personal Details Tab";
	        document.getElementById('errorDiv').appendChild(email);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }
	      if(chk==1)
	      {
	         return false;
	      }
	      else
	      {
	    	  	var companyDetails = document.getElementById("companyDetails-tab");
	    		companyDetails.className="tab-content active";
	    		var headCompanyDetails = document.getElementById("companyDetails");
	    		headCompanyDetails.className="active";
	    		
	    		var personalDetails = document.getElementById("personalDetails-tab");
	    		personalDetails.className="tab-content";
	    		var headPersonalDetails = document.getElementById("personalDetails");
	    		headPersonalDetails.className="none"; 
	    		return true;
	      }
	
}
function showPrevPersonalDetail(){
	var companyDetails = document.getElementById("companyDetails-tab");
	companyDetails.className="tab-content";
	var headCompanyDetails = document.getElementById("companyDetails");
	headCompanyDetails.className="none";
	
	var personalDetails = document.getElementById("personalDetails-tab");
	personalDetails.className="tab-content active";
	var headPersonalDetails = document.getElementById("personalDetails");
	headPersonalDetails.className="active"; 
}
function showNextAccSetting(){
	var companyName = document.getElementById('companyName').value;
	var address = document.getElementById('address').value;
	var town = document.getElementById('town').value;
	var country = document.getElementById('country').value;
	var postcode = document.getElementById('postcode').value;
	var compnyTelephone = document.getElementById('compnyTelephone').value;
	var compnyEmail = document.getElementById('compnyEmail').value;
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;
	 	
	     if (companyName ==  "") {
	      	var companyName = document.createElement("label");
	        companyName.innerHTML = "Enter Company Name in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(companyName);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }   
		if (address ==  "") {
	      	var address = document.createElement("label");
	        address.innerHTML = "Enter Address in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(address);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }  
	     if (town ==  "") {
	      	var town = document.createElement("label");
	        town.innerHTML = "Enter Town in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(town);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }  
	     if (country ==  "") {
	      	var country = document.createElement("label");
	        country.innerHTML = "Enter Country in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(country);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }        
	     if (postcode ==  "") {
	      	var postcode = document.createElement("label");
	        postcode.innerHTML = "Enter Post Code in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(postcode);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     } 
	      if (compnyTelephone ==  "") {
	      	var compnyTelephone = document.createElement("label");
	        compnyTelephone.innerHTML = "Enter Telephone no in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(compnyTelephone);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }   
	      if (compnyEmail ==  "") {
	      	var compnyEmail = document.createElement("label");
	        compnyEmail.innerHTML = "Enter Email in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(compnyEmail);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     } 
	      if(chk==1)
	      {
	         return false;
	      }
	      else
	      {
	    	  	var companyDetails = document.getElementById("companyDetails-tab");
	    		companyDetails.className="tab-content";
	    		var headCompanyDetails = document.getElementById("companyDetails");
	    		headCompanyDetails.className="none";
	    		
	    		var accStting = document.getElementById("accountSetting-tab");
	    		accStting.className="tab-content active";
	    		var headaccStting = document.getElementById("accountSetting");
	    		headaccStting.className="active"; 
	    		return true;
	      } 
	
}

function showPrevCompnyDetail(){
	
	var companyDetails = document.getElementById("companyDetails-tab");
	companyDetails.className="tab-content active";
	var headCompanyDetails = document.getElementById("companyDetails");
	headCompanyDetails.className="active";
	
	var accStting = document.getElementById("accountSetting-tab");
	accStting.className="tab-content";
	var headaccStting = document.getElementById("accountSetting");
	headaccStting.className="none"; 
	
}

function showNextContactPref(){
	
	var contactPreferences = document.getElementById("contactPreferences-tab");
	contactPreferences.className="tab-content active";
	var headcontactPreferences = document.getElementById("contactPreferences");
	headcontactPreferences.className="active";
	
	var accStting = document.getElementById("accountSetting-tab");
	accStting.className="tab-content";
	var headaccStting = document.getElementById("accountSetting");
	headaccStting.className="none";
}

function showPrevAccountSetting(){
	var contactPreferences = document.getElementById("contactPreferences-tab");
	contactPreferences.className="tab-content";
	var headcontactPreferences = document.getElementById("contactPreferences");
	headcontactPreferences.className="none";
	
	var accStting = document.getElementById("accountSetting-tab");
	accStting.className="tab-content active";
	var headaccStting = document.getElementById("accountSetting");
	headaccStting.className="active";
}

function displayPersonalDetailsTab(){
	var companyDetails = document.getElementById("companyDetails-tab");
	companyDetails.className="tab-content";
	var headCompanyDetails = document.getElementById("companyDetails");
	headCompanyDetails.className="none";
	
	var personalDetails = document.getElementById("personalDetails-tab");
	personalDetails.className="tab-content active";
	var headPersonalDetails = document.getElementById("personalDetails");
	headPersonalDetails.className="active"; 
	
	var contactPreferences = document.getElementById("contactPreferences-tab");
	contactPreferences.className="tab-content";
	var headcontactPreferences = document.getElementById("contactPreferences");
	headcontactPreferences.className="none";
	
	var accStting = document.getElementById("accountSetting-tab");
	accStting.className="tab-content";
	var headaccStting = document.getElementById("accountSetting");
	headaccStting.className="none";
	
}

function displayCompanyDetailsTab(){
	
	var name = document.getElementById('name').value;
	var telephoneLine = document.getElementById('telephoneLine').value;
	var email = document.getElementById('email').value;
	var type = document.getElementById('type').value;
	
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;
	if (type ==  "0") {
      	var type = document.createElement("label");
      	type.innerHTML = "Select Type in Personal Details Tab";
        document.getElementById('errorDiv').appendChild(type);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
     }
	 	if (name ==  "") {
	      	var name = document.createElement("label");
	        name.innerHTML = "Enter Name in Personal Details Tab";
	        document.getElementById('errorDiv').appendChild(name);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }   
	     if (telephoneLine ==  "") {
	      	var telephoneLine = document.createElement("label");
	        telephoneLine.innerHTML = "Enter Telephone Line in Personal Details Tab";
	        document.getElementById('errorDiv').appendChild(telephoneLine);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }  
	      if (email ==  "") {
	      	var email = document.createElement("label");
	        email.innerHTML = "Enter Email in Personal Details Tab";
	        document.getElementById('errorDiv').appendChild(email);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }
	      if(chk==1)
	      {
	         return false;
	      }
	      else
	      {
	    	  var companyDetails = document.getElementById("companyDetails-tab");
	    		companyDetails.className="tab-content active";
	    		var headCompanyDetails = document.getElementById("companyDetails");
	    		headCompanyDetails.className="active";
	    		
	    		var personalDetails = document.getElementById("personalDetails-tab");
	    		personalDetails.className="tab-content";
	    		var headPersonalDetails = document.getElementById("personalDetails");
	    		headPersonalDetails.className="none"; 
	    		
	    		var contactPreferences = document.getElementById("contactPreferences-tab");
	    		contactPreferences.className="tab-content";
	    		var headcontactPreferences = document.getElementById("contactPreferences");
	    		headcontactPreferences.className="none";
	    		
	    		var accStting = document.getElementById("accountSetting-tab");
	    		accStting.className="tab-content";
	    		var headaccStting = document.getElementById("accountSetting");
	    		headaccStting.className="none";
	    		return true;
	      }
	
}

function displayAccSettingTab(){
	var companyName = document.getElementById('companyName').value;
	var address = document.getElementById('address').value;
	var town = document.getElementById('town').value;
	var country = document.getElementById('country').value;
	var postcode = document.getElementById('postcode').value;
	var compnyTelephone = document.getElementById('compnyTelephone').value;
	var compnyEmail = document.getElementById('compnyEmail').value;
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;
	 	
	     if (companyName ==  "") {
	      	var companyName = document.createElement("label");
	        companyName.innerHTML = "Enter Company Name in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(companyName);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }   
		if (address ==  "") {
	      	var address = document.createElement("label");
	        address.innerHTML = "Enter Address in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(address);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }  
	     if (town ==  "") {
	      	var town = document.createElement("label");
	        town.innerHTML = "Enter Town in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(town);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }  
	     if (country ==  "") {
	      	var country = document.createElement("label");
	        country.innerHTML = "Enter Country in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(country);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }        
	     if (postcode ==  "") {
	      	var postcode = document.createElement("label");
	        postcode.innerHTML = "Enter Post Code in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(postcode);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     } 
	      if (compnyTelephone ==  "") {
	      	var compnyTelephone = document.createElement("label");
	        compnyTelephone.innerHTML = "Enter Telephone no in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(compnyTelephone);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     }   
	      if (compnyEmail ==  "") {
	      	var compnyEmail = document.createElement("label");
	        compnyEmail.innerHTML = "Enter Email in Company Details Tab";
	        document.getElementById('errorDiv').appendChild(compnyEmail);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	     } 
	      if(chk==1)
	      {
	         return false;
	      }
	      else
	      {
	    	  var companyDetails = document.getElementById("companyDetails-tab");
	    		companyDetails.className="tab-content";
	    		var headCompanyDetails = document.getElementById("companyDetails");
	    		headCompanyDetails.className="none";
	    		
	    		var personalDetails = document.getElementById("personalDetails-tab");
	    		personalDetails.className="tab-content";
	    		var headPersonalDetails = document.getElementById("personalDetails");
	    		headPersonalDetails.className="none"; 
	    		
	    		var contactPreferences = document.getElementById("contactPreferences-tab");
	    		contactPreferences.className="tab-content";
	    		var headcontactPreferences = document.getElementById("contactPreferences");
	    		headcontactPreferences.className="none";
	    		
	    		var accStting = document.getElementById("accountSetting-tab");
	    		accStting.className="tab-content active";
	    		var headaccStting = document.getElementById("accountSetting");
	    		headaccStting.className="active";
	    		return true;
	      } 
	
	
}
function displayContactPrefTab(){
	var companyDetails = document.getElementById("companyDetails-tab");
	companyDetails.className="tab-content";
	var headCompanyDetails = document.getElementById("companyDetails");
	headCompanyDetails.className="none";
	
	var personalDetails = document.getElementById("personalDetails-tab");
	personalDetails.className="tab-content";
	var headPersonalDetails = document.getElementById("personalDetails");
	headPersonalDetails.className="none"; 
	
	var contactPreferences = document.getElementById("contactPreferences-tab");
	contactPreferences.className="tab-content active";
	var headcontactPreferences = document.getElementById("contactPreferences");
	headcontactPreferences.className="active";
	
	var accStting = document.getElementById("accountSetting-tab");
	accStting.className="tab-content";
	var headaccStting = document.getElementById("accountSetting");
	headaccStting.className="none";
	
}



function setPopupTypeName(id){
	/*if(id==2){
		document.getElementById("accountSetting").style.display = 'block';
	}
	else{
		document.getElementById("accountSetting").style.display = 'none';

	}*/
	if(id==1){
		
		document.getElementById("popupgpcontactdiv").style.display = 'block';
		document.getElementById("popupaccountSetting").style.display = 'none';

	}
	else{
		document.getElementById("popupgpcontactdiv").style.display = 'none';
		document.getElementById("popupaccountSetting").style.display = 'block';


	}
	var url = "setPopupTypeNameDropDownClient?id="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setPopupTypeNameRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}    
	function setPopupTypeNameRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				
	    		document.getElementById("popuptypeName").innerHTML = req.responseText;
	    		$("#popuptypeName").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
	         }
		}
	}
	
	function popupdisableFiled(id){
		
		if(id==0){
			document.getElementById("ppcompanyName").disabled = false;
			
		}
		else{
			document.getElementById("ppcompanyName").disabled = true;
			
		}
		
		
	}
	
	
	


function setTypeName(id){
	/*if(id==2){
		document.getElementById("accountSetting").style.display = 'block';
	}
	else{
		document.getElementById("accountSetting").style.display = 'none';

	}*/
	if(id==1){
		
		document.getElementById("doctorsurgerysaveid").style.display = 'block';
		document.getElementById("gpcontactdiv").style.display = 'block';
		document.getElementById("insurenceorgroupsaveid").style.display = 'none';
		document.getElementById("accountSetting").style.display = 'none';

	}
	else{
		document.getElementById("doctorsurgerysaveid").style.display = 'none';
		document.getElementById("gpcontactdiv").style.display = 'none';
		document.getElementById("accountSetting").style.display = 'block';
		document.getElementById("insurenceorgroupsaveid").style.display = 'block';


	}
	var typeName1= document.getElementById('typeName1').value;
	if(typeName1!="0"){
		document.getElementById('patient_Type_add').value= document.getElementById('patientType').value;
		document.getElementById('payerType_add').value=id;
		document.getElementById('tpnameaddfrm').submit();
	}
	
	var url = "setTypeNameDropDownClient?id="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setTypeNameRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}    
	function setTypeNameRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				
	    		document.getElementById("typeName1").innerHTML = req.responseText;
	    		$("#typeName1").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
	         }
		}
	}
	
	function disableFiled(id){
		var typeName=document.getElementById('typeName1').value;
		document.getElementById('selectedid').value = id;
		document.getElementById('patient_Type').value= document.getElementById('patientType').value;
		document.getElementById('payer_Type').value = document.getElementById('type1').value;
		/*document.getElementById('companyName').value = typeName ;*/
		
		document.getElementById('tpnamefrm').submit();
		
		
		/*var type = document.getElementById('type1').value;
		if(id==0){
			document.getElementById("companyName").disabled = false;
			document.getElementById('telephoneLine').value = "";
			document.getElementById('tpaddress').value = "";
			document.getElementById('tptown').value = "";
			document.getElementById('tpcountry').value = "";
			document.getElementById('tppostcode').value = "";
			document.getElementById('compnyEmail').value= "";
			
			document.getElementById('accountWarningLimit').value = "";
			document.getElementById('dnaLimit').value = "";
			document.getElementById('outInvoiceLimit').value = "";
			
			document.getElementById('creditDuration').value = "";
			document.getElementById('creditReminderDuration').value = "";
			
			document.getElementById('dnaInitialApmt').value = "";
			document.getElementById('dnafollowupApmt').value = "";
			document.getElementById('dnafinalApmt').value = "";
			document.getElementById('dnamaintenanceApmt').value = "";
			
			document.getElementById('compltInitialApmt').value = "";
			document.getElementById('compltfollowupApmt').value = "";
			document.getElementById('compltfinalApmt').value = "";
			
			document.getElementById('compltmaintenanceApmt').value = "";
			document.getElementById('compltInitialApmtDuration').value = "";
			document.getElementById('compltfollowupApmtDuration').value = "";
			document.getElementById('compltfinalApmtDuration').value = "";

			document.getElementById('compltmaintenanceApmtDuration').value = "";
		}
		else{
			document.getElementById("companyName").disabled = true;
			var url = "getTPDetailsThirdParty?typename="+id+"&type="+type+"";

			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = disableFiledRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);

		}*/
	}
	
	function disableFiledRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
				
					var data = req.responseText;
					var temp = data.split("/");
					
					
					document.getElementById('type1').value = temp[0];
					document.getElementById('typeName1').value = temp[1];
					document.getElementById('telephoneLine').value = temp[2];
					document.getElementById('tpaddress').value = temp[4];
					document.getElementById('tptown').value = temp[5];
					document.getElementById('tpcountry').value = temp[6];
					document.getElementById('tppostcode').value = temp[7];
					document.getElementById('compnyEmail').value= temp[8];
					
					document.getElementById('accountWarningLimit').value = temp[9];
					document.getElementById('dnaLimit').value = temp[10];
					document.getElementById('outInvoiceLimit').value = temp[11];
					
					document.getElementById('creditDuration').value = temp[12];
					document.getElementById('creditReminderDuration').value = temp[13];
					
					document.getElementById('dnaInitialApmt').value = temp[14];
					document.getElementById('dnafollowupApmt').value = temp[15];
					document.getElementById('dnafinalApmt').value = temp[16];
					document.getElementById('dnamaintenanceApmt').value = temp[17];
					
					document.getElementById('compltInitialApmt').value = temp[18];
					document.getElementById('compltfollowupApmt').value = temp[19];
					document.getElementById('compltfinalApmt').value = temp[20];
					
					document.getElementById('compltmaintenanceApmt').value = temp[21];
					document.getElementById('compltInitialApmtDuration').value = temp[22];
					document.getElementById('compltfollowupApmtDuration').value = temp[23];
					document.getElementById('compltfinalApmtDuration').value = temp[24];

					document.getElementById('compltmaintenanceApmtDuration').value = temp[25];
					
					
					
		         }
			}
		}
	
	function checkValidations(){
		 var payer = document.getElementById('typeName1').value;
		 var id = document.getElementById('type1').value;
		 var type = document.getElementById('type1').value;
		 var chk = 0;
		
		var typeName1 = document.getElementById('typeName1').value;
		var companyName = document.getElementById('companyName').value; 
		var shortname= document.getElementById("shortname").value;
		
		var telephoneLine = document.getElementById("telephoneLine").value;
		var tppostcode= document.getElementById("tppostcode").value;
		var patientType = document.getElementById("patientType").value;
		document.getElementById("tpTypeError").innerHTML = "";	
		//document.getElementById("tpnameError").innerHTML = "";	
		
		
		var compnyEmail = document.getElementById('compnyEmail').value;
		
		var regEx = /^\d{10}$/;
		var regExPostCode = /^\d{6}$/;
		var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		document.getElementById('patientTypeError').innerHTML="";
		if(patientType==''){
			document.getElementById('patientTypeError').innerHTML="Please Select Patient Type";
			chk=1;
		}
		
		document.getElementById('tpTypeError').innerHTML="";
		if(chk==0 && type == 0){		
			document.getElementById('tpTypeError').innerHTML ="Please Select Payer Type";
			chk=1;
		}
		document.getElementById("payerNameError").innerHTML ="";
		if(chk==0 && typeName1=='0' && companyName==''){
			document.getElementById("payerNameError").innerHTML ="Please Enter Payer Name";
			chk=1;
		}
		/*if(chk==0 && typeName1=='0' && companyName!='' && companyName.match(/[^a-zA-Z]+/)){
			document.getElementById("payerNameError").innerHTML ="Special Symbol & Number Not Allowed.";
			chk=1;
		}
		document.getElementById("shortNameError").innerHTML ="";
		if(chk==0 && shortname!='' && shortname.match(/[^a-zA-Z]+/)){
			document.getElementById("shortNameError").innerHTML ="Special Symbol & Number Not Allowed.";
			chk=1;
		}*/
		document.getElementById("postCodeError").innerHTML ="";
		if(chk==0 && tppostcode!='' && !regExPostCode.test(tppostcode)){
			document.getElementById("postCodeError").innerHTML ="Please Enter Valid Post Code.";
			chk=1;
		}
		document.getElementById("telephoneLineError").innerHTML ="";
		if(chk==0 && telephoneLine!='' && !regEx.test(telephoneLine)){
			document.getElementById("telephoneLineError").innerHTML ="Please Enter Valid Phone Number.";
			chk=1;
		}
		
		document.getElementById("compnyEmailError").innerHTML ="";
		if(chk==0 && compnyEmail!="" && !emailregEx.test(compnyEmail)){
			document.getElementById("compnyEmailError").innerHTML ="Please Enter Valid Email.";
			chk=1;
		}
		
		
		if(chk==1){
			return false;
		}

		 if(payer==0){ 
		 validatePromoJSON = {
	             "validatorData": [
	                     { "Element": "#type1", "FunctionName": "dropDown" },
	                     { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
	                     { "Element": "#companyName", "FunctionName": "notEmpty" }
	                     

	             ]
	         };
		 }
		 else{
			 validatePromoJSON = {
		             "validatorData": [
		                  { "Element": "#type1", "FunctionName": "dropDown" },
		                  { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" }
		                 
		                     
			 
		             ]
		         };
		 }
		
		 if(id==1){
			 if(payer==0){
				 validatePromoJSON = {
			             "validatorData": [
			                     { "Element": "#type1", "FunctionName": "dropDown" },
			                     { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
			                     { "Element": "#companyName", "FunctionName": "notEmpty" },
			                     { "Element": "#gpname", "FunctionName": "notEmpty" }

			             ]
			         };
				 }
				 else{
					 validatePromoJSON = {
				             "validatorData": [
				                  { "Element": "#type1", "FunctionName": "dropDown" },
				                  { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
				                  { "Element": "#gpname", "FunctionName": "notEmpty" }
				                     
					 
				             ]
				         };
				 }
			 
		 }
		 if(id==2){
			
			 if(payer==0){
				 validatePromoJSON = {
			             "validatorData": [
			                     { "Element": "#type1", "FunctionName": "dropDown" },
			                     { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
			                     { "Element": "#companyName", "FunctionName": "notEmpty" }
			                    

			                     
			                     

			             ]
			         };
				 }
				 else{
					 validatePromoJSON = {
				             "validatorData": [
				                  { "Element": "#type1", "FunctionName": "dropDown" },
				                  { "Element": "#compnyEmail", "FunctionName": "emailIdOptional" },
				                 
				                     
					 
				             ]
				         };
				 }
		 }
			
			 return iterateThroughtElemsForValidations(validatePromoJSON);
		 
	}
	
	function addRow(tableID) 
    {
		var l1 = document.getElementById('danyamiclistpresent').value;

		if(document.getElementById('typeName1').value > 0 && l1 == 1)
		{
		}
		
		else{
		document.getElementById("dynamicallyAddApmtTable").style.display = "block";
		}
    	var table = document.getElementById(tableID);

            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
            var counts = rowCount - 1;
			
			var cell1 = row.insertCell(0);
        	var element1 = document.createElement("input");
       		element1.type = "checkbox";
        	element1.name="chkbox[]";
        	element1.title="Delete Row";
        	cell1.appendChild(element1);

			var cell2 = row.insertCell(1);
        	cell2.innerHTML = counts + 1;
        	
        	var cell3 = row.insertCell(2);
            cell3.innerHTML="<input type='text' onblur='initialFirstCap(this);'  maxlength='15' placeholder='Enter name 15 char max' size='50'  class='form-control showToolTip dnaName;' name = dynamicApmt["+counts+"].dnaName>";
            
        	
            
            var cell4 = row.insertCell(3);
            var element3 = document.createElement("input");
       		element3.type = "checkbox";
        	element3.name="chdna"+counts;
        	element3.id="chdna"+counts;
        	/*element3.onclick = "setDnaOffsetValue("+counts+")";*/
        	element3.title="Delete Row";
        	cell4.appendChild(element3);
        	
        	document.getElementById('chdna'+counts).onclick = function() {
        		setDnaOffsetValue(counts);
        	}

            var cell5 = row.insertCell(4);
            cell5.innerHTML="<input type='text' placeholder='0.00'  class='form-control showToolTip apmtName' name = dynamicApmt["+counts+"].dnaCharge>";
            
            var cell6 = row.insertCell(5);
            cell6.innerHTML="<input type='text'  placeholder='0.00' class='form-control showToolTip apmtCharge' name = dynamicApmt["+counts+"].apmtCharge>";
            
            
        	var cell7 = row.insertCell(6);
            var element2 = document.createElement("select");
            element2.name = "dynamicApmt[" + counts + "].apmtDuaration";
            element2.id = "apmtDuaration"+counts;
            element2.setAttribute("class", "form-control showToolTip apmtDuaration");

            var option1 =  document.createElement("option");
            option1.innerHTML = "00:00";
            option1.value = "00:00";
            element2.add(option1,null);
            
            var option2 =  document.createElement("option");
            option2.innerHTML = "00:15";
            option2.value = "00:15";
            element2.add(option2,null);
            
            var option3 =  document.createElement("option");
            option3.innerHTML = "00:30";
            option3.value = "00:30";
            element2.add(option3,null);
            
            var option4 =  document.createElement("option");
            option4.innerHTML = "00:45";
            option4.value = "00:45";
            element2.add(option4,null);
            
            var option5 =  document.createElement("option");
            option5.innerHTML = "01:00";
            option5.value = "01:00";
            element2.add(option5,null);
            
            var option6 =  document.createElement("option");
            option6.innerHTML = "01:15";
            option6.value = "01:15";
            element2.add(option6,null);
            
            var option7 =  document.createElement("option");
            option7.innerHTML = "01:30";
            option7.value = "01:30";
            element2.add(option7,null);
            
            cell7.appendChild(element2);
            
            var cell8 = row.insertCell(7);
            cell8.innerHTML="<input type='hidden'  class='form-control showToolTip id' name = dynamicApmt["+counts+"].id>";
            
            var cell9 = row.insertCell(8);
            cell9.innerHTML="<input type='hidden' name = 'dynamicApmt["+counts+"].offset' id='dynamicApmt["+counts+"].offset' >";
            
          
}
	
	function deleteRow(tableID){
		try {
	            var table = document.getElementById(tableID);
	            var rowCount = table.rows.length;
	            
	            for(var i=0; i<rowCount; i++) {
	                var row = table.rows[i];
	                var chkbox = row.cells[0].childNodes[0];
	                if(null != chkbox && true == chkbox.checked) {
	                    
	                    table.deleteRow(i);
	                    rowCount--;
	                    i--;
	                }
	                
	               
	 
	            }
	            }catch(e) {
	            	jAlert('error', e, 'Error Dialog');

	            }
	}	
	
	
	function setDnaOffsetValue(id){
		//alert(document.getElementById('chdna'+id).checked)
		//alert(id)
		document.getElementById('dynamicApmt['+id+'].offset').value = document.getElementById('chdna'+id).checked;
	}
	
	function setTypeNameNew(val){
		document.getElementById('patient_Type_add').value= document.getElementById('patientType').value;
		document.getElementById('payerType_add').value ="";
		document.getElementById('tpnameaddfrm').submit();
	}
	
	function setPayerType(val){
		var typeName1= document.getElementById('typeName1').value;
		if(typeName1!="0"){
			document.getElementById('patient_Type_add').value= document.getElementById('patientType').value;
			document.getElementById('payerType_add').value="0";
			document.getElementById('tpnameaddfrm').submit();
		}
		
		
		var url = "getPayerTypeListDataThirdParty?id="+val+" ";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		               
		req.onreadystatechange = setPayerTypeRequest;
		req.open("GET", url, true); 
		req.send(null);

	}    
	function setPayerTypeRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("typepayerdiv").innerHTML = req.responseText;
	    		$("#type1").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
		    }
		}
	}