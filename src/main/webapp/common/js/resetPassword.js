var checkemail = 0;
var oldpassword = 0;
function validforgotPassword(){
	validatePromoJSON = {
            "validatorData": [
                  
                    { "Element": "#emailId", "FunctionName": "emailId" }
                    
	 
            ]
        };
	 	if(iterateThroughtElemsForValidations(validatePromoJSON))
		 {
	 		if(checkemail == 0){
				 document.getElementById('emailIdError').innerHTML="";

	 		return true;
	 		}
	 		else{
		 		 document.getElementById('emailIdError').innerHTML="";
				 var userId = document.createElement("label");
				 userId.innerHTML = "Email Id Not Registered";
			     document.getElementById('emailIdError').appendChild(userId);
		 		return false;
		 	}
			  

		 }
	 	else{
	 		return false;
	 	}
	 	
}


function checkExist1(){
	
	
	var r=false;
	var emailId = document.getElementById('emailId').value;
	
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	 if(emailId=="")
		 {
		 
		 document.getElementById('emailIdError').innerHTML="";
		 var userId = document.createElement("label");
		 userId.innerHTML = "Please Enter Email Id ";
	     document.getElementById('emailIdError').appendChild(userId);
       r= false;
		 }
	 else
		 {
		 
		 
	 
       if (reg.test(emailId) == false) 
       {
    	   document.getElementById('emailIdError').innerHTML="";
			 var userId = document.createElement("label");
			 userId.innerHTML = "Invalid Email Id ";
		     document.getElementById('emailIdError').appendChild(userId);
           r= false;
       }
       else
       	
       {
    	 		var url = "checkEmailIdResetPassword?emailId="+emailId+"";

    		if (window.XMLHttpRequest) {
    				req = new XMLHttpRequest();
    			}
    			else if (window.ActiveXObject) {
    				isIE = true;
    				req = new ActiveXObject("Microsoft.XMLHTTP");
    			}   
    		               
    		    req.onreadystatechange = checkExistRequest;
    		    req.open("GET", url, true); 
    		              
    		    req.send(null);
    		    
    		    r= true;
       	
      	}
       return r;
      
		 }
	 
	 
	 return r;
	 
	 
}
var r=false;
	function checkExist(){
		
	
		var emailId = document.getElementById('emailId').value;
		
		 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

		 if(emailId=="")
			 {
			 
			 document.getElementById('emailIdError').innerHTML="";
			 var userId = document.createElement("label");
			 userId.innerHTML = "Please Enter Email Id ";
		     document.getElementById('emailIdError').appendChild(userId);
	       r= false;
			 }
		 else
			 {
			 
			 
		 
	       if (reg.test(emailId) == false) 
	       {
	    	   document.getElementById('emailIdError').innerHTML="";
				 var userId = document.createElement("label");
				 userId.innerHTML = "Invalid Email Id ";
			     document.getElementById('emailIdError').appendChild(userId);
	           r= false;
	       }
	       else
	       	
	       {
	    	 		var url = "checkEmailIdResetPassword?emailId="+emailId+"";

	    		if (window.XMLHttpRequest) {
	    				req = new XMLHttpRequest();
	    			}
	    			else if (window.ActiveXObject) {
	    				isIE = true;
	    				req = new ActiveXObject("Microsoft.XMLHTTP");
	    			}   
	    		               
	    		    req.onreadystatechange = checkExistRequest;
	    		    req.open("GET", url, true); 
	    		              
	    		    req.send(null);
	    		    
	    		 //   r= true;
	       	
	      	}
	      
	       return r;
	      
			 }
		 
		 return r;

			/*var emailId = document.getElementById('emailId').value;
	 		var url = "checkEmailIdResetPassword?emailId="+emailId+"";

			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = checkExistRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);*/
			
	}
	function checkExistRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
				var exist = req.responseText;
				if(exist == 'true')
				{

					checkemail = 1;
					 document.getElementById('emailIdError').innerHTML="";
					 var userId = document.createElement("label");
					 userId.innerHTML = "Email Id Not Registered";
				     document.getElementById('emailIdError').appendChild(userId);
				     r= false;
				}else
				 {

					
					checkemail = 0;

					 document.getElementById('emailIdError').innerHTML="";
					 r= true;
					
					
					

				 }
				
				
	         	
				
	         }
		}
	}


function validResetPswd(){
	validatePromoJSON = {
            "validatorData": [
                  
                    { "Element": "#emailId", "FunctionName": "emailId" },
                    { "Element": "#password", "ElementToCompare": "#confirmPassword", "FunctionName": "password" }
                    
	 
            ]
        };
	if(iterateThroughtElemsForValidations(validatePromoJSON) && checkemail == 0)
	 {
		document.getElementById('modifyResetPassword').submit();
		return true;
		
	 }
	else if(iterateThroughtElemsForValidations(validatePromoJSON) && checkemail == 1){
		document.getElementById('emailIdError').innerHTML="";
		 var userId = document.createElement("label");
		 userId.innerHTML = "Email Id Not Registered";
	     document.getElementById('emailIdError').appendChild(userId);
	     return false;
	}
else{
	 return false;
}
}
function checkThisOldPassword(oldpassword){
	var url = "validateoldpasswordResetPassword?oldpassword="+oldpassword+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkThisOldPasswordRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function checkThisOldPasswordRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			var exist = req.responseText;
			document.getElementById('oldPasswordError').innerHTML="";
			if(exist == 'false')
			{
				 oldpassword = 1;
				 /*document.getElementById('oldPasswordError').innerHTML="";*/
				 document.getElementById('oldPasswordError').style.display="";
				 var userId = document.createElement("label");
				 userId.innerHTML = "Please enter valid old password";
			     document.getElementById('oldPasswordError').appendChild(userId);
			     return false;

			}else
			 {
				oldpassword = 0;

				 document.getElementById('oldPasswordError').style.display="none";
				 return true;
			 }
			
			
         	
			
         }
	}
}
function validChangePassword(){
		validatePromoJSON = {
            "validatorData": [
                 	{ "Element": "#oldPassword", "FunctionName": "notEmpty" },
                    { "Element": "#password", "ElementToCompare": "#confirmPassword", "FunctionName": "password" },
            ]
        };
		document.getElementById('confirmPasswordError').innerHTML="";
		document.getElementById('passwordError').innerHTML="";
	 	if(iterateThroughtElemsForValidations(validatePromoJSON) && oldpassword == 0)
		 {
	 		
	 		var mobile = document.getElementById("mobile").value;
	 		var password = document.getElementById("password").value;
	 		var confirmPassword = document.getElementById("confirmPassword").value;
	 		
	 		if(mobile.length<9 || mobile==''){
	 			jAlert('error', 'please enter mobile number!', 'Error Dialog');
	 			setTimeout(function() {
	 				$("#popup_container").remove();
	 				removeAlertCss();
	 			}, alertmsgduration);
	 		}else if(password!=confirmPassword){
	 			jAlert('error', 'Password and Confirm Password does not match!', 'Error Dialog');
	 			setTimeout(function() {
	 				$("#popup_container").remove();
	 				removeAlertCss();
	 			}, alertmsgduration);
	 			
	 			document.getElementById('confirmPasswordError').style.display="";
	 			var userId = document.createElement("label");
	 			userId.innerHTML = "Password and Confirm Password does not match";
	 			document.getElementById('confirmPasswordError').appendChild(userId);
	 		}
	 		
	 		else {
	 			
	 			//var url = "sendotpforchangepasswordDiaryMangent?mobile="+mobile+"";
				
//				if (window.XMLHttpRequest) {
//					req = new XMLHttpRequest();
//				}
//				else if (window.ActiveXObject) {
//					isIE = true;
//					req = new ActiveXObject("Microsoft.XMLHTTP");
//				}   
//				               
//				req.onreadystatechange = updatepasswordRequest;
//				req.open("GET", url, true); 
//				req.send(null);
		 		
	 			
	 			var url = "updatepharmacyuserpwdDiaryMangent?mobile="+mobile+"&confirm_pwd="+confirmPassword+"";
	 			
	 			if (window.XMLHttpRequest) {
	 				req = new XMLHttpRequest();
	 			}
	 			else if (window.ActiveXObject) {
	 				isIE = true;
	 				req = new ActiveXObject("Microsoft.XMLHTTP");
	 			}   
	 			req.onreadystatechange = confirmotpRequest;
	 			req.open("GET", url, true); 
	 			req.send(null);
		 		return false;
	 		}
	 		
	 		
		 }else if(iterateThroughtElemsForValidations(validatePromoJSON) && oldpassword == 1){
	 		 document.getElementById('oldPasswordError').innerHTML="";
			 var userId = document.createElement("label");
			 userId.innerHTML = "Please enter valid old password";
		     document.getElementById('oldPasswordError').appendChild(userId);
		     return false;
	 	}else{
	 		var password = document.getElementById("password").value;
	 		var confirmPassword = document.getElementById("confirmPassword").value;
	 		if(password==''){
	 			document.getElementById('passwordError').style.display="";
	 			var userId = document.createElement("label");
	 			userId.innerHTML = "Please Enter New Password";
	 			document.getElementById('passwordError').appendChild(userId);
	 		}else{
	 			document.getElementById('passwordError').innerHTML="";
	 		}
	 		if(confirmPassword==''){
	 			document.getElementById('confirmPasswordError').style.display="";
	 			var userId = document.createElement("label");
	 			userId.innerHTML = "Please Enter Confirm Password";
	 			document.getElementById('confirmPasswordError').appendChild(userId);
	 		}else if(password!=confirmPassword){
	 			document.getElementById('confirmPasswordError').style.display="";
	 			var userId = document.createElement("label");
	 			userId.innerHTML = "Password and Confirm Password does not match";
	 			document.getElementById('confirmPasswordError').appendChild(userId);
	 		}else{
	 			document.getElementById('confirmPasswordError').innerHTML="";
	 		}
		 return false;
	 }
}


function updatepasswordRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById('confirmPasswordError').innerHTML="";
				var str = req.responseText;
				document.getElementById("newotp").value=str;
				$('#otpmodel').modal("show");
			}
		}
	}


function confirmotp(){
	var otp = document.getElementById("otp").value;
	var oldotp = document.getElementById("newotp").value;
	var confirm_pwd = document.getElementById("password").value;
	var mobile = document.getElementById("mobile").value;
	if(otp==''){
		jAlert('error', "Plz enter otp!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	
	}else if(otp!=oldotp){
		jAlert('error', "Plz enter valid OTP!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
		document.getElementById("otp").value='';
		$('#otpmodel').modal("show");
	}else{
		var url = "updatepharmacyuserpwdDiaryMangent?mobile="+mobile+"&confirm_pwd="+confirm_pwd+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = confirmotpRequest;
		req.open("GET", url, true); 
		req.send(null);
		
		/*jAlert('success', "Password has been changed.", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, 10000); 
		document.getElementById("changePaswdResetPassword").submit();*/
	}
}
function confirmotpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location = 'Logout';
			}
		}
	}


