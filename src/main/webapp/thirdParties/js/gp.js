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
	
	var validchkemail = 0;
	var validchklmob = 0;
	
	var thirdPartyId = document.getElementById('thirdPartyId').value;
	/*var name = document.getElementById('name').value;
	var workphno = document.getElementById('workphno').value;
	var emailid = document.getElementById('emailid').value;
	var fax = document.getElementById('fax').value;
	var description = document.getElementById('description').value;
	*/
	
	document.getElementById("tnameError").innerHTML = "";
	/*document.getElementById("nameError").innerHTML = "";
	document.getElementById("workphnoError").innerHTML = "";
	document.getElementById("emailError").innerHTML = "";
	document.getElementById("faxError").innerHTML = "";
	document.getElementById("noteError").innerHTML = "";
	*/
	var chk = 0;	
	//alert(headerNote);
	
	if(thirdPartyId == 0){	
		
		var tid = document.createElement("label");
		tid.innerHTML = "Please select Doctor Surgery";
	    document.getElementById('tnameError').appendChild(tid);
	    chk=1;
	}
	/*if(name == ""){		
		var name = document.createElement("label");
		name.innerHTML = "Please add GP Name";
	    document.getElementById('nameError').appendChild(name);
	    chk=1;
	}
	if(workphno == ""){
		var workno = document.createElement("label");
		workno.innerHTML = "Please add Contact No";
	    document.getElementById('workphnoError').appendChild(workno);
	    chk=1;
	}
	if(emailid == ""){
		var email = document.createElement("label");
		email.innerHTML = "Please add EmailId";
	    document.getElementById('emailError').appendChild(email);
	    chk=1;
	}
	if(fax == ""){
		var fax1 = document.createElement("label");
		fax1.innerHTML = "Please add Fax";
	    document.getElementById('faxError').appendChild(fax1);
	    chk=1;
	}
	if(description == ""){
		var note = document.createElement("label");
		note.innerHTML = "Please add Description";
	    document.getElementById('noteError').appendChild(note);
	    chk=1;
	}*/
	
	if(chk==1)
    {
       return false;
    }
   
	
	
/*	var thirdPartyId = document.getElementById('thirdPartyId').value;
	alert(thirdPartyId);
	if(thirdPartyId == "0"){	
		
		if ($("#thirdPartyId").hasClass("chosen")) {
			alert("select type");
			 
        
		
		document.getElementById('thirdPartyId').className = 'chosen-container-single';
		document.getElementById("chosen").className = "";
		$("#thirdPartyId").removeClass('chosen');
		document.getElementById('thirdPartyId').style.borderColor = "red";
		return false;
		}

	    
	}*/
	
	validatePromoJSON = {
            "validatorData": [
					
					{ "Element": "#emailid", "FunctionName": "emailIdOptional" },
                    { "Element": "#workphno", "FunctionName": "phoneOptional" },
                    { "Element": "#thirdPartyId", "FunctionName": "notEmpty" },
                    { "Element": "#name", "FunctionName": "notEmpty" }                   
            ]
        };
	
	if(validchkemail == 0 && validchklmob == 0){
	return iterateThroughtElemsForValidations(validatePromoJSON);
	}
	else{
		return false;
	}
}
