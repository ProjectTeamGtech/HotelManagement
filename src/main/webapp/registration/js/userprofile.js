//Tab Validations handler
var validchkemail = 0;
var validchklmob = 0;
var validuser = 0;

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

function validateUD(){
	validatePromoJSON = {
            "validatorData": [
/*                    { "Element": "#initial", "FunctionName": "dropDown" },
*/                    { "Element": "#firstname", "FunctionName": "alphabetsOnlyWithSpace" },
                    { "Element": "#lastname", "FunctionName": "alphabetsOnlyWithSpace" },
                    { "Element": "#jobtitle", "FunctionName": "dropDown" },
                    { "Element": "#diciplineName", "FunctionName": "dropDown" }
                  
            ]
        };
	
	return iterateThroughtElemsForValidations(validatePromoJSON);
}
function validateUS(){
	validatePromoJSON = {
            "validatorData": [
                    { "Element": "#diarycolumnposition", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#compressionrate", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#onlinename", "FunctionName": "alphabetsOnlyWithSpaceOptional" }
                    
            ]
        };
	
	return iterateThroughtElemsForValidations(validatePromoJSON);
}

function validateLD(){
	var isPwedPresent = $("#password").length;
	if(isPwedPresent==0){
		validatePromoJSON = {
	            "validatorData": [
	                    { "Element": "#userId", "FunctionName": "notEmpty" }
	                    
	            ]
	        };
		
	}
	else{
		
		validatePromoJSON = {
	            "validatorData": [
	                    { "Element": "#userId", "FunctionName": "notEmpty" },
	                    { "Element": "#password", "ElementToCompare": "#confirmPassword", "FunctionName": "password" }
	            ]
	        };
	}
	
	
	return iterateThroughtElemsForValidations(validatePromoJSON);
}
function validatePF(){

	validatePromoJSON = {
            "validatorData": [
                    { "Element": "#email", "FunctionName": "emailId" },
                    { "Element": "#mobile", "FunctionName": "mobileNo" }
                   
                    
            ]
        };
	
	return iterateThroughtElemsForValidations(validatePromoJSON);
}



function validEntry(){


var email = document.getElementById('email').value;
var mobile = document.getElementById('mobile').value;

document.getElementById('errorDiv').innerHTML="";
var chk=0;
 var atpos=email.indexOf("@");
 var dotpos=email.lastIndexOf(".");
     if (email ==  "") {
      	var email = document.createElement("label");
        email.innerHTML = "Enter email in Preferences Tab";
        document.getElementById('errorDiv').appendChild(email);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
     }  
     
	 else if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
        var email = document.createElement("label");
        email.innerHTML = "Enter valid email in Preferences Tab";
        document.getElementById('errorDiv').appendChild(email);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
       	
        chk=1;
     } 
	if (mobile ==  "") {
      	var mobile = document.createElement("label");
        mobile.innerHTML = "Enter mobile no in Preferences Tab";
        document.getElementById('errorDiv').appendChild(mobile);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
     } 
     else if (isNaN(mobile)===true){
  	 var mobiledig = document.createElement("label");
     mobiledig.innerHTML = "Enter valid mobile no in Preferences Tab"; 
     document.getElementById('errorDiv').appendChild(mobiledig);
     document.getElementById('errorDiv').appendChild(document.createElement('br'));
  	chk=1;
	}
	
	  
     if(chk==1)
     {
        return false;
     }
     else
     {
    	
         jAlert('success', 'Details Added Sucessfully.', 'Success Dialog');
         
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



function showOtherJobTitle(){
	var e = document.getElementById('jobtitle');
			var jobtitle = e.options[e.selectedIndex].text;
			//alert(occupation);
 		 	if(jobtitle == 'Other') 
            {
                document.getElementById("jobTitle_other").style.display = ""; // This line makes the DIV visible
            } 
            else {            
                document.getElementById("jobTitle_other").style.display = "none"; // This line hides the DIV
            }
}

function addOtherJobTitle(jobtitle){

var url = "addOtherJobTitleUserProfile?jobtitle="+jobtitle+" ";
if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addOtherJobTitleRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}    
function addOtherJobTitleRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			
    		
         
         }
	}
}

function showUserSetupTab(){
	var firstname = document.getElementById('firstname').value;
	var lastname = document.getElementById('lastname').value;
	var initial = document.getElementById('initial').value;
	var jobtitle = document.getElementById('jobtitle').value;
	var diciplineName = document.getElementById('diciplineName').value;
	
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;
	if(firstname == ""){
	    	var firstname = document.createElement("label");
	        firstname.innerHTML = "Enter First Name in User Detail Tab";
	        document.getElementById('errorDiv').appendChild(firstname);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }  
	if(lastname == ""){
	    	var lastname = document.createElement("label");
	        lastname.innerHTML = "Enter last Name in User Detail Tab";
	        document.getElementById('errorDiv').appendChild(lastname);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }   
	 if(initial == 0){
	    	var initial = document.createElement("label");
	        initial.innerHTML = "Enter Initial in User Detail Tab";
	        document.getElementById('errorDiv').appendChild(initial);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }  
	 if(jobtitle == 0){
	    	var jobtitle = document.createElement("label");
	        jobtitle.innerHTML = "Enter Job Title in User Detail Tab";
	        document.getElementById('errorDiv').appendChild(jobtitle);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }  
	 if(diciplineName == 0){
	    	var discipline = document.createElement("label");
	    	discipline.innerHTML = "Please Select Discipline";
	        document.getElementById('errorDiv').appendChild(discipline);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }  
	 if(chk==1)
     {
        return false;
     }
     else
     {
    	 	var userSetup = document.getElementById("userSetup-tab");
    	 	userSetup.className="tab-content active";
    		var headuserSetup = document.getElementById("userSetup");
    		headuserSetup.className="active";
    		
    		var userDetail = document.getElementById("userDetails-tab");
    		userDetail.className="tab-content";
    		var headuserDetail = document.getElementById("userDetails");
    		headuserDetail.className="none"; 
        return true;
     }
	
	
	
}

function showPrevUserDetailTab(){
	var userSetup = document.getElementById("userSetup-tab");
 	userSetup.className="tab-content";
	var headuserSetup = document.getElementById("userSetup");
	headuserSetup.className="none";
	
	var userDetail = document.getElementById("userDetails-tab");
	userDetail.className="tab-content active";
	var headuserDetail = document.getElementById("userDetails");
	headuserDetail.className="active"; 
}

function showNextLoginDetailTab(){
	var diarycolumnposition = document.getElementById('diarycolumnposition').value;
	var compressionrate = document.getElementById('compressionrate').value;
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;

	    
	/* if(compressionrate == ""){
	    	var compressionrate = document.createElement("label");
	        compressionrate.innerHTML = "Enter Compression rate in User Setup Tab";
	        document.getElementById('errorDiv').appendChild(compressionrate);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }  */
	   if (isNaN(compressionrate)===true){
	  	 var compressionratedig = document.createElement("label");
	     compressionratedig.innerHTML = "Enter Digit ony in Compression Rate in User Setup Tab"; 
	     document.getElementById('errorDiv').appendChild(compressionratedig);
	     document.getElementById('errorDiv').appendChild(document.createElement('br'));
	  	chk=1;
	} 
	 if(chk==1)
     {
        return false;
     }
     else
     {
    	 	var loginDet = document.getElementById("loginDetails-tab");
    	 	loginDet.className="tab-content active";
    		var headloginDetails = document.getElementById("loginDetails");
    		headloginDetails.className="active";
    		
    		var userSet = document.getElementById("userSetup-tab");
    		userSet.className="tab-content";
    		var headuserSetup = document.getElementById("userSetup");
    		headuserSetup.className="none"; 
        return true;
     }
	
}

function showPrevUsersSetupTab(){
	var loginDet = document.getElementById("loginDetails-tab");
 	loginDet.className="tab-content";
	var headloginDetails = document.getElementById("loginDetails");
	headloginDetails.className="none";
	
	var userSet = document.getElementById("userSetup-tab");
	userSet.className="tab-content active";
	var headuserSetup = document.getElementById("userSetup");
	headuserSetup.className="active";
}

function showNextPreferencesTab(){
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;
	//var changefre = document.getElementById('changefre').value;
	var userId = document.getElementById('userId').value;
	var password = document.getElementById('password').value;
	if(userId == ""){
    	var userId = document.createElement("label");
    	userId.innerHTML = "Enter userId Login Details Tab";
        document.getElementById('errorDiv').appendChild(userId);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
 }
	if(password == ""){
    	var password = document.createElement("label");
    	password.innerHTML = "Enter Password Login Details Tab";
        document.getElementById('errorDiv').appendChild(password);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
 }

	
	  /*if (isNaN(changefre)===true){
	  	 var changefredig = document.createElement("label");
	     changefredig.innerHTML = "Enter Digit ony in Change Freq Login Details Tab"; 
	     document.getElementById('errorDiv').appendChild(changefredig);
	     document.getElementById('errorDiv').appendChild(document.createElement('br'));
	  	chk=1;
	}*/
	  if(chk==1)
	     {
	        return false;
	     }
	     else
	     {
	    	 	var loginDet = document.getElementById("loginDetails-tab");
	    	 	loginDet.className="tab-content";
	    		var headloginDetails = document.getElementById("loginDetails");
	    		headloginDetails.className="none";
	    		
	    		var preferences = document.getElementById("preferences-tab");
	    		preferences.className="tab-content active";
	    		var headuserpreferences = document.getElementById("preferences");
	    		headuserpreferences.className="active";
	        return true;
	     }
	
}

function showPrevLoginDEatils(){
	var loginDet = document.getElementById("loginDetails-tab");
 	loginDet.className="tab-content active";
	var headloginDetails = document.getElementById("loginDetails");
	headloginDetails.className="active";
	
	var preferences = document.getElementById("preferences-tab");
	preferences.className="tab-content";
	var headuserpreferences = document.getElementById("preferences");
	headuserpreferences.className="none";
	
}

function displayUserDetailsTab(){
	
	var userSetup = document.getElementById("userSetup-tab");
 	userSetup.className="tab-content";
	var headuserSetup = document.getElementById("userSetup");
	headuserSetup.className="none";
	
	var userDetail = document.getElementById("userDetails-tab");
	userDetail.className="tab-content active";
	var headuserDetail = document.getElementById("userDetails");
	headuserDetail.className="active"; 
	
	var loginDet = document.getElementById("loginDetails-tab");
 	loginDet.className="tab-content";
	var headloginDetails = document.getElementById("loginDetails");
	headloginDetails.className="none";
	
	var preferences = document.getElementById("preferences-tab");
	preferences.className="tab-content";
	var headuserpreferences = document.getElementById("preferences");
	headuserpreferences.className="none";
	
}

function displayUserSetupTab(){
	var firstname = document.getElementById('firstname').value;
	var lastname = document.getElementById('lastname').value;
	var initial = document.getElementById('initial').value;
	var jobtitle = document.getElementById('jobtitle').value;
	
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;
	if(firstname == ""){
	    	var firstname = document.createElement("label");
	        firstname.innerHTML = "Enter First Name in User Detail Tab";
	        document.getElementById('errorDiv').appendChild(firstname);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }  
	if(lastname == ""){
	    	var lastname = document.createElement("label");
	        lastname.innerHTML = "Enter last Name in User Detail Tab";
	        document.getElementById('errorDiv').appendChild(lastname);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }   
	 if(initial == 0){
	    	var initial = document.createElement("label");
	        initial.innerHTML = "Enter Initial in User Detail Tab";
	        document.getElementById('errorDiv').appendChild(initial);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }  
	 if(jobtitle == 0){
	    	var jobtitle = document.createElement("label");
	        jobtitle.innerHTML = "Enter Job Title in User Detail Tab";
	        document.getElementById('errorDiv').appendChild(jobtitle);
	        document.getElementById('errorDiv').appendChild(document.createElement('br'));
	        chk=1;
	 }  
	 if(chk==1)
     {
        return false;
     }
     else
     {
    	 var userSetup = document.getElementById("userSetup-tab");
    	 	userSetup.className="tab-content active";
    		var headuserSetup = document.getElementById("userSetup");
    		headuserSetup.className="active";
    		
    		var userDetail = document.getElementById("userDetails-tab");
    		userDetail.className="tab-content";
    		var headuserDetail = document.getElementById("userDetails");
    		headuserDetail.className="none"; 
    		
    		var loginDet = document.getElementById("loginDetails-tab");
    	 	loginDet.className="tab-content";
    		var headloginDetails = document.getElementById("loginDetails");
    		headloginDetails.className="none";
    		
    		var preferences = document.getElementById("preferences-tab");
    		preferences.className="tab-content";
    		var headuserpreferences = document.getElementById("preferences");
    		headuserpreferences.className="none";
        return true;
     }
	
	
}

function displayLoginDetailsTab(){
	var diarycolumnposition = document.getElementById('diarycolumnposition').value;
	var compressionrate = document.getElementById('compressionrate').value;
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;

	
	   if (isNaN(compressionrate)===true){
	  	 var compressionratedig = document.createElement("label");
	     compressionratedig.innerHTML = "Enter Digit ony in Compression Rate in User Setup Tab"; 
	     document.getElementById('errorDiv').appendChild(compressionratedig);
	     document.getElementById('errorDiv').appendChild(document.createElement('br'));
	  	chk=1;
	} 
	 if(chk==1)
     {
        return false;
     }
     else
     {
    	 var userSetup = document.getElementById("userSetup-tab");
    	 	userSetup.className="tab-content";
    		var headuserSetup = document.getElementById("userSetup");
    		headuserSetup.className="none";
    		
    		var userDetail = document.getElementById("userDetails-tab");
    		userDetail.className="tab-content";
    		var headuserDetail = document.getElementById("userDetails");
    		headuserDetail.className="none"; 
    		
    		var loginDet = document.getElementById("loginDetails-tab");
    	 	loginDet.className="tab-content active";
    		var headloginDetails = document.getElementById("loginDetails");
    		headloginDetails.className="active";
    		
    		var preferences = document.getElementById("preferences-tab");
    		preferences.className="tab-content";
    		var headuserpreferences = document.getElementById("preferences");
    		headuserpreferences.className="none";
        return true;
     }
	
}

function displayPreferenceTab(){
	document.getElementById('errorDiv').innerHTML="";
	var chk=0;
	//var changefre = document.getElementById('changefre').value;
	var userId = document.getElementById('userId').value;
	var password = document.getElementById('password').value;
	if(userId == ""){
    	var userId = document.createElement("label");
    	userId.innerHTML = "Enter userId Login Details Tab";
        document.getElementById('errorDiv').appendChild(userId);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
 }
	if(password == ""){
    	var password = document.createElement("label");
    	password.innerHTML = "Enter Password Login Details Tab";
        document.getElementById('errorDiv').appendChild(password);
        document.getElementById('errorDiv').appendChild(document.createElement('br'));
        chk=1;
 }

	
	 /* if (isNaN(changefre)===true){
	  	 var changefredig = document.createElement("label");
	     changefredig.innerHTML = "Enter Digit ony in Change Freq Login Details Tab"; 
	     document.getElementById('errorDiv').appendChild(changefredig);
	     document.getElementById('errorDiv').appendChild(document.createElement('br'));
	  	chk=1;
	}*/
	  if(chk==1)
	     {
	        return false;
	     }
	     else
	     {
	    	 var userSetup = document.getElementById("userSetup-tab");
	    	 	userSetup.className="tab-content";
	    		var headuserSetup = document.getElementById("userSetup");
	    		headuserSetup.className="none";
	    		
	    		var userDetail = document.getElementById("userDetails-tab");
	    		userDetail.className="tab-content";
	    		var headuserDetail = document.getElementById("userDetails");
	    		headuserDetail.className="none"; 
	    		
	    		var loginDet = document.getElementById("loginDetails-tab");
	    	 	loginDet.className="tab-content";
	    		var headloginDetails = document.getElementById("loginDetails");
	    		headloginDetails.className="none";
	    		
	    		var preferences = document.getElementById("preferences-tab");
	    		preferences.className="tab-content active";
	    		var headuserpreferences = document.getElementById("preferences");
	    		headuserpreferences.className="active";
	        return true;
	     }
	
}
function checkUserIdExist(userId){
	var id = document.getElementById('id').value;
	var reWhiteSpace = new RegExp("/^\s+$/");
	var flag = false;
	var patt1 = /\s/g;
	if(userId.match(patt1)){
		flag =true;
		validuser=1;
	}
	if(flag){
		document.getElementById('errorUserId').innerHTML="";
		var newuserId = document.createElement("label");
		newuserId.innerHTML = "Please remove space from userid";
	    document.getElementById('errorUserId').appendChild(newuserId);
	}else{
		var url = "checkUserIdExistClinicRegistration?userId="+userId+"&selectedid="+id+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = checkUserIdExistRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
}
function checkUserIdExistRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
					validuser = 1;
				}
				
				 if(chk==1)
			     {	
					 document.getElementById('errorUserId').innerHTML="";
					 var userId = document.createElement("label");
					 userId.innerHTML = "UserId already Exist Please try another";
				     document.getElementById('errorUserId').appendChild(userId);
				     chk = 1;
				     validuser = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('errorUserId').innerHTML="";
			    	 chk = 0;
			    	 validuser = 0;
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
	}

function checkEmailIdExist(email){
	var id = document.getElementById('id').value;
	
	var url = "checkEmailIdExistUserProfile?email="+email+"&selectedid="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkEmailIdExistRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkEmailIdExistRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					validchkemail = 1;
					chk = 1;
				}
				
				 if(chk==1)
			     {	
					 document.getElementById('errorEmailId').innerHTML="";
					 var emailId = document.createElement("label");
					 emailId.innerHTML = "Email Id already Exist Please try another";
				     document.getElementById('errorEmailId').appendChild(emailId);
				     chk = 1;
				     validchkemail = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('errorEmailId').innerHTML="";
			    	 chk = 0;
			    	 validchkemail = 0;
			    	 return true;
			     }
				
	         }
		}
	}

function checkMobileNoExist(mobile){
//	mobile = '0'+mobile;
	// document.getElementById('mobileNo').value = mobile;
	
	var url = "checkMobileNoExistUserProfile?mobile="+mobile+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkMobileNoExistRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkMobileNoExistRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				var exist = req.responseText;
				
				if(exist == 'false')
				{
					validchklmob = 1;
					chk = 1;
				}
				
				 if(chk==1)
			     {	
					 document.getElementById('errorMobileNo').innerHTML="";
					 var mobileNo = document.createElement("label");
					 mobileNo.innerHTML = "Mobile No already Exist Please try another";
				     document.getElementById('errorMobileNo').appendChild(mobileNo);
				     chk = 1;
				     validchklmob = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('errorMobileNo').innerHTML="";
			    	 chk = 0;
			    	 validchklmob = 0;
			    	 return true;
			     }
				
	         }
		}
	}
function validateDetail(){

	/*var mob = document.getElementById('mobileNo').value;*/
	setTimeout(function(){
	document.getElementById('savebutton').disabled = true;
	},30);
	
	setTimeout(function(){
		document.getElementById('savebutton').disabled = false;
		},7000);
	/*validatePromoJSON = {
            "validatorData": [
					{ "Element": "#initial", "FunctionName": "dropDown" },
					{ "Element": "#firstname", "FunctionName": "notEmpty" },
					{ "Element": "#lastname", "FunctionName": "notEmpty" },
					{ "Element": "#jobgroup", "FunctionName": "dropDown" },
					{ "Element": "#jobtitle", "FunctionName": "dropDown" },
					{ "Element": "#diciplineName", "FunctionName": "dropDown" },
					{ "Element": "#userId", "FunctionName": "maxeightchar" },
                    { "Element": "#password", "ElementToCompare": "#confirmPassword", "FunctionName": "password" },
                    { "Element": "#email", "FunctionName": "emailId" },
                    { "Element": "#mobile", "FunctionName": "mobileNo" },
                    { "Element": "#dnaCharge", "FunctionName": "numbersOnlyCompulsary" },
                    { "Element": "#compAppCharge", "FunctionName": "numbersOnlyCompulsary" }
                    
            ]
        };*/
	validatePromoJSON = {
            "validatorData": [
					{ "Element": "#userId", "FunctionName": "maxeightchar" },
                    { "Element": "#password", "ElementToCompare": "#confirmPassword", "FunctionName": "password" }
                    
            ]
        };
	var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var qualification = document.getElementById('qualification').value;
	var initial = document.getElementById('initial').value;
	var firstname = document.getElementById('firstname').value;
	var lastname = document.getElementById('lastname').value;
	var jobgroup = document.getElementById('jobgroup').value;
	var diciplineName = document.getElementById('diciplineName').value;
	var email = document.getElementById('email').value;
	var mobile = document.getElementById('mobile').value;
	var dnaCharge = document.getElementById('dnaCharge').value;
	var compAppCharge = document.getElementById('compAppCharge').value;
	var dob = document.getElementById('dob').value;
	var jobtitle = document.getElementById('jobtitle').value;
	var iserror=0;
	 var numbers = /^\d+$/;;
	if(initial == "" || initial=="0"){
	    jAlert('error', "Please Select Initial!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(firstname == ""){
	    jAlert('error', "Please Enter Firstname!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}
	else if(lastname == ""){
	    jAlert('error', "Please Enter Lastname!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(mobile.length!=10){
		jAlert('error', "No. should 10 digit!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(!numbers.test(mobile)){
		jAlert('error', "Please enter valid mobile No.!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(!emailregEx.test(email)){
		jAlert('error', "Please Enter Email!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}/*else if(dob==""){
		jAlert('error', "Please Enter DOB!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}*/else if(jobgroup=="" || jobgroup=="0"){
		jAlert('error', "Please Select  Role Group!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(jobtitle=="" || jobtitle=="0"){
		jAlert('error', "Please Select  Role Title!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(diciplineName=="" || diciplineName=="0"){
		jAlert('error', "Please Select  Specialization!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(qualification==""){
		jAlert('error', "Please Enter Qualification!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}
	if(validchkemail == 0 && validchklmob == 0 && validuser == 0 && iserror==0){
		
		return iterateThroughtElemsForValidations(validatePromoJSON);
	}
	
	else{

		
				
		
		return false;
	}

}

function validateEditDetail(){
//	var mob = document.getElementById('mobileNo').value;
//	document.getElementById('mobileNo').value = '0'+mob;
	/*validatePromoJSON = {
            "validatorData": [
					{ "Element": "#firstname", "FunctionName": "notEmpty" },
					{ "Element": "#lastname", "FunctionName": "notEmpty" },
					{ "Element": "#jobgroup", "FunctionName": "dropDown" },
					{ "Element": "#jobtitle", "FunctionName": "dropDown" },
					{ "Element": "#diciplineName", "FunctionName": "dropDown" },
                    { "Element": "#emailId", "FunctionName": "emailId" },
                    { "Element": "#mobileNo", "FunctionName": "mobileNo" },
                    { "Element": "#dnaCharge", "FunctionName": "numbersOnlyCompulsary" },
                    { "Element": "#compAppCharge", "FunctionName": "numbersOnlyCompulsary" }
                    
            ]
        };*/
	var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var qualification = document.getElementById('qualification').value;
	var initial = document.getElementById('initial').value;
	var firstname = document.getElementById('firstname').value;
	var lastname = document.getElementById('lastname').value;
	var jobgroup = document.getElementById('jobgroup').value;
	var diciplineName = document.getElementById('diciplineName').value;
	var email = document.getElementById('email').value;
	var mobile = document.getElementById('mobile').value;
	var dnaCharge = document.getElementById('dnaCharge').value;
	var compAppCharge = document.getElementById('compAppCharge').value;
	var dob = document.getElementById('dob').value;
	var jobtitle = document.getElementById('jobtitle').value;
	var iserror=0;
	 var numbers = /^\d+$/;;
	if(initial == "" || initial=="0"){
	    jAlert('error', "Please Select Initial!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(firstname == ""){
	    jAlert('error', "Please Enter Firstname!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}
	else if(lastname == ""){
	    jAlert('error', "Please Enter Lastname!", 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(mobile.length!=10){
		jAlert('error', "No. should 10 digit!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(!numbers.test(mobile)){
		jAlert('error', "Please enter valid mobile No.!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(!emailregEx.test(email)){
		jAlert('error', "Please Enter Email!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}/*else if(dob==""){
		jAlert('error', "Please Enter DOB!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}*/else if(jobgroup=="" || jobgroup=="0"){
		jAlert('error', "Please Select  Role Group!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(jobtitle=="" || jobtitle=="0"){
		jAlert('error', "Please Select  Role Title!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(diciplineName=="" || diciplineName=="0"){
		jAlert('error', "Please Select  Specialization!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}else if(qualification==""){
		jAlert('error', "Please Enter Qualification!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		iserror=1;
	}
	if(validchkemail == 0 && validchklmob == 0 && iserror==0){
	return iterateThroughtElemsForValidations(validatePromoJSON);
	}
	else{
		return false;
	}
}


function setJobTitleAjax(id){
	var url = "jobtitleUserProfile?selectedid="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setJobTitleAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function setJobTitleAjaxRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
			
			
				document.getElementById("jobtitlediv").innerHTML = req.responseText;
				
	         		
				
	         }
		}
}

function checkDoctorSelect(){
	var chk=0;
	document.getElementById('jobtitless').value = document.getElementById('jobtitle').value;
	if(document.getElementById('jobgroup').value==4 && document.getElementById('doctor').value==0){
	         		
						 document.getElementById('errordoctor').innerHTML="";
						 var doctor = document.createElement("label");
						 doctor.innerHTML = "Please select practitioner!!";
					     document.getElementById('errordoctor').appendChild(doctor);
						
						 chk = 1;
					     validchklmob = 1;
					     return false;
					}else{
						 document.getElementById('errordoctor').innerHTML="";
				    	 chk = 0;
				    	 validchklmob = 0;
				    	 return true;
					}
}

function setselectedlab(){
	 var selectedwardid = 0;
     $('.labch').each(function() { //loop through each checkbox
        // this.checked = true;  //select all checkboxes with class "checkbox1" 
        if(this.checked==true){
        	selectedwardid = selectedwardid + ',' + this.value;
        }
         
     });
     document.getElementById('labname').value = selectedwardid;
}

function setselectedwardid(){
	
	 var selectedwardid = 0;
     $('.wardch').each(function() { //loop through each checkbox
        // this.checked = true;  //select all checkboxes with class "checkbox1" 
        if(this.checked==true){
        	selectedwardid = selectedwardid + ',' + this.value;
        }
         
     });
     document.getElementById('wardid').value = selectedwardid;
     
}
function updateglobalaccess(jobtitle,val){
	var url = "updateglobalaccessssUserProfile?jobtitle="+jobtitle+"&val="+val+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updateglobalaccessRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function updateglobalaccessRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			 }
		}
}

function updateWidegetStatus(val,cname,jobtitle){
	var userwiseaceessid = 0;
	if(document.getElementById("userwiseaceessid")){
		userwiseaceessid = document.getElementById("userwiseaceessid").value;
	}
	if(userwiseaceessid==1){
		if(document.getElementById("userIdval")){
			jobtitle = document.getElementById("userIdval").value;
		}
	}
	
	var url = "updatewidegetstatusUserProfile?jobtitle="+jobtitle+"&val="+val+"&cname="+cname+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updateWidegetStatusRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function updateWidegetStatusRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			 }
		}
}

function updateAccesssStatus(val,cname,jobtitle){
	var userwiseaceessid = 0;
	if(document.getElementById("userwiseaceessid")){
		userwiseaceessid = document.getElementById("userwiseaceessid").value;
	}
	if(userwiseaceessid==1){
		if(document.getElementById("userIdval")){
			jobtitle = document.getElementById("userIdval").value;
		}
	}
	var url = "updatewidegetstatusUserProfile?jobtitle="+jobtitle+"&val="+val+"&cname="+cname+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updateAccesssStatusRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function updateAccesssStatusRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
			 }
		}
}


function updateactiveinactive(id,val){
	var url = "updateactiveinactiveUserProfile?id="+id+"&val="+val+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updateactiveinactiveRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function updateactiveinactiveRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
			 }
		}
}


function updatewardid(userid){
	var selectedwardid = 0;
	$('.wardch').each(function() { //loop through each checkbox
	   // this.checked = true;  //select all checkboxes with class "checkbox1" 
	   if(this.checked==true){
	   	selectedwardid = selectedwardid + ',' + this.value;
	   }
	    
	});
	var url = "updatewardidUserProfile?userid="+userid+"&val="+selectedwardid+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updatewardidRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function updatewardidRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				jAlert('success', 'Update successfully.', 'success Dialog');
				   
				   setTimeout(function() {
				    $("#popup_container").remove();
				    removeAlertCss();
				   }, alertmsgduration);
			 }
		}
}
function selectAllWard(val){
	 var selectedwardid = 0;
    $('.wardch').each(function() { //loop through each checkbox
        
       if(val==true){
    	   this.checked = true;  //select all checkboxes with class "checkbox1"
       }else{
    	   this.checked = false;
       }
        
    });
    
}

function updateindentLocation(userid){
	var selectedindentlocid = document.getElementById("location").value;
	$('.indentclass').each(function() { //loop through each checkbox
	   // this.checked = true;  //select all checkboxes with class "checkbox1" 
	   if(this.checked==true){
		   selectedindentlocid = selectedindentlocid + ',' + this.value;
	   }
	    
	});
	var url = "updateindentlocationsUserProfile?userid="+userid+"&val="+selectedindentlocid+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updateindentLocationRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function updateindentLocationRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				jAlert('success', 'Update successfully.', 'success Dialog');
				   
				   setTimeout(function() {
				    $("#popup_container").remove();
				    removeAlertCss();
				   }, alertmsgduration);
			 }
		}
}
//Adarsh
function selectAllAccess(val){
	 var selectaccess = 0;
	    $('.labch').each(function() { //loop through each checkbox
	        
	       if(val==true){
	    	   this.checked = true;  //select all checkboxes with class "checkbox1"
	       }else{
	    	   this.checked = false;
	       }
	        
	    });

	
	     $('.labch').each(function() { //loop through each checkbox
	        // this.checked = true;  //select all checkboxes with class "checkbox1" 
	        if(this.checked==true){
	        	selectaccess = selectaccess + ',' + this.value;
	        }
	         
	     });
	     document.getElementById('labname').value = selectaccess;
}

function updateUserIndivisualStatus(val,cname,userid){
	var url = "updateuserindivisualstatusUserProfile?userid="+userid+"&val="+val+"&cname="+cname+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updateUserIndivisualStatusRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function updateUserIndivisualStatusRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
			 }
		}
}


function updateAccesssStatusMis(val,cname,userid){
	 var url = "updatemiswidegetstatusUserProfile?userid="+userid+"&val="+val+"&cname="+cname+"";
	  if (window.XMLHttpRequest) {
	   req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
	   isIE = true;
	   req = new ActiveXObject("Microsoft.XMLHTTP");
	  }   
	     req.onreadystatechange = updateAccesssStatusRequest1;
	     req.open("GET", url, true); 
	     req.send(null);
	}
	function updateAccesssStatusRequest1(){
	 if (req.readyState == 4) {
	   if (req.status == 200) {
	    
	    }
	  }
	}
	
	
	
	function updateUserWiseAccess(val,cname,userid){
		var url = "updateuserwiseaccessUserProfile?userid="+userid+"&val="+val+"&cname="+cname+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = updateUserWiseAccessRequest;
		    req.open("GET", url, true); 
		    req.send(null);
	}
	function updateUserWiseAccessRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					window.location.reload();
				 }
			}
	}
	
	function setTypeofAccesstoUser(val,userid,jobtitle){
			var url = "settypeofaccessUserProfile?userid="+userid+"&val="+val+"&jobtitle="+jobtitle+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = setTypeofAccesstoUserRequest;
		    req.open("GET", url, true); 
		    req.send(null);
	}
	function setTypeofAccesstoUserRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					window.location.reload();
				 }
			}
	}
	
	function checkEmployeeIdExist(empId){
		var id = document.getElementById('id').value;
		var reWhiteSpace = new RegExp("/^\s+$/");
		var flag = false;
		var patt1 = /\s/g;
		if(empId.match(patt1)){
			flag =true;
			validuser=1;
		}
		if(flag){
			document.getElementById('errorEmployeeId').innerHTML="";
			var newuserId = document.createElement("label");
			newuserId.innerHTML = "Please remove space from employeeid";
		    document.getElementById('errorEmployeeId').appendChild(newuserId);
		}else{
			var url = "checkEmpIdExistUserProfile?empId="+empId+"&selectedid="+id+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		    req.onreadystatechange = checkEmpCodeExistRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
		
	}
	function checkEmpCodeExistRequest(){
		if (req.readyState == 4) {
			var chk=0;
				if (req.status == 200) {
					//alert(req.responseText);
					var exist = req.responseText;
					if(exist == 'false')
					{
						
						chk = 1;
						validuser = 1;
					}
					
					 if(chk==1)
				     {	
						 document.getElementById('errorEmployeeId').innerHTML="";
						 var empcode = document.createElement("label");
						 empcode.innerHTML = "Empcode already Exist";
					     document.getElementById('errorEmployeeId').appendChild(empcode);
					     chk = 1;
					     validuser = 1;
					     return false;
				     }
				     else
				     {
				    	 document.getElementById('errorEmployeeId').innerHTML="";
				    	 chk = 0;
				    	 validuser = 0;
				    	 return true;
				     }
					
					
		    		
		         
		         }
			}
		}

	
function resetpassword(val){
	
	document.getElementById("id").value=val;
	
	$('#reset').modal( "show" );
	
}

function updatepassword(){
	
	var id=document.getElementById("id").value;
	var resetpassword=document.getElementById("resetpassword").value;
		
	if (resetpassword == '') {

		jAlert('error', "Please Enter Password", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}else{
	 $("#reset").modal( "hide" );
	var url = "updatepasswordUserProfile?id="+id+"&resetpassword="+resetpassword+"";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updatepasswordRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	}
	
}

function updatepasswordRequest(){


      if (req.readyState == 4) {
		 if (req.status == 200) {
			 var str = req.responseText;
			  $("#baselayout1loaderPopup").modal("hide");
			  if (str == '0') {
			      alert("data not added.");                   // error
			  } else {
				alert("update Successfully.");
				window.location.reload();
                     // success
			}
			  
		 }
	}
          
}

	