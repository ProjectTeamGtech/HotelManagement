var s1;
var chk;
//Tab Validations handler
var emailIdExist = 0;
var userIdExist = 0;
var validchklmob = 0;
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

function validateGI(){
	validatePromoJSON = {
            "validatorData": [
                    { "Element": "#clinicName", "FunctionName": "notEmpty" },
                   // { "Element": "#clinicOwner", "FunctionName": "notEmpty" },
                    { "Element": "#email", "FunctionName": "emailId" },
                    { "Element": "#mobileNo", "FunctionName": "mobileNo" }
                    
            ]
        };
	
	if(emailIdExist == 1){
		 document.getElementById('errorEmail').innerHTML="";
		 var userId = document.createElement("label");
		 userId.innerHTML = "Email id already Exist Please try another";
	     document.getElementById('errorEmail').appendChild(userId);
		return false;
	}
	else{
		return iterateThroughtElemsForValidations(validatePromoJSON);

	}
	if(validchkemail == 0 && validchklmob == 0 && validuser == 0){
		return iterateThroughtElemsForValidations(validatePromoJSON);
		}
		else{
			return false;
		}
}

function validatePS(){
	validatePromoJSON = {
            "validatorData": [
                    { "Element": ".chkGrp", "FunctionName": "validateChkGrp" }
            ]
        };
	return iterateThroughtElemsForValidations(validatePromoJSON);
}

function validateSetting(){

	validatePromoJSON = {
            "validatorData": [
                     { "Element": "#sTime", "FunctionName": "dropDown" },
                    { "Element": "#endTime", "FunctionName": "dropDown" }
            ]
        };
	return iterateThroughtElemsForValidations(validatePromoJSON);
}

function validateFS(){
	validatePromoJSON = {
            "validatorData": [
                    { "Element": ".chkGrp2", "FunctionName": "validateChkGrp" }
            ]
        };
	return iterateThroughtElemsForValidations(validatePromoJSON);
}

function validateAI(){
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
	
	if(userIdExist == 1){
		document.getElementById('errorUserId').innerHTML="";
		 var userId = document.createElement("label");
		 userId.innerHTML = "UserId already Exist Please try another";
	     document.getElementById('errorUserId').appendChild(userId);
	     return false;
	}
	else{
	    
	    var specialization=0;
	    var field=0;
	    $('.spec').each(function() { // loop through each checkbox
			if(this.checked == true)
			{
				specialization=specialization+","+this.value;			  
			} 
		});
		$('.casef').each(function() { // loop through each checkbox
			if(this.checked == true)
			{
				field=field+","+this.value;			  
			} 
		});
		
		
		document.getElementById("specializations").value=specialization;
		document.getElementById("fields").value=field;
		
     	return iterateThroughtElemsForValidations(validatePromoJSON);
	}
}

function validateCL(){

	$(".addressType").each(function () {
        var addressTypeValue = $(this).val();
        if (addressTypeValue != null && addressTypeValue != "" && addressTypeValue != undefined) {
            s1 = true;
            return true;
        }
        else {
            s1 = false;
            setError(this);
            return false;
        }
       
    });
	
	/*var checkLocation = document.getElementById('checkLocation').checked;
	//alert(checkLocation);
	document.getElementById('checkLocError').innerHTML = "";
	if(checkLocation != 'true'){
		var checkLoc = document.createElement("label");
		checkLoc.innerHTML = "Please Check Atleast 1 field";
	    document.getElementById('checkLocError').appendChild(checkLoc);
	    //setError(this);
	    s1 = false;
        return false;
	}
	else {
		return true;
    }*/
	
	/*$(".checkLocation").each(function () {
        var checkLocationValue = $(this).val();
        alert(checkLocationValue);
        if (checkLocationValue != null && checkLocationValue != "" && checkLocationValue != undefined) {
            s1 = true;
            return true;
        }
        else {
            s1 = false;
            setError(this);
            return false;
        }
       
    });
	*/
	
    if (s1) {

    $(".emailId").each(function () {
        var emailValue = $(this).val();
        document.getElementById('emailError').innerHTML = "";
       
     //   var regEx = /^[0123456789]\d{10}$/;
	    var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	   
        if (emailValue == null || emailValue == "" || emailValue == undefined) {  
        	 var emailErr = document.createElement("label");
        	 emailErr.innerHTML = "Please enter email Id";
		     document.getElementById('emailError').appendChild(emailErr);
        	 setError(this);
             s1 = false;
             return false;
        }
        else if(!emailregEx.test(emailValue)){
        	 var emailErr = document.createElement("label");
        	 emailErr.innerHTML = "Please enter valid email";
		     document.getElementById('emailError').appendChild(emailErr);
        	 setError(this);
             s1 = false;
             return false;
        }
        else {          	
        	
            return true;
        }
    });
    
    $(".address").each(function () {
        var addressValue = $(this).val();
        if (addressValue != null && addressValue != "" && addressValue != undefined) {
            return true;
        }
        else {
           
        	
        	setError(this);
            s1 = false;
            return false;
        }
    });
    $(".locationname").each(function () {
        var locationnameValue = $(this).val();
        if (locationnameValue != null && locationnameValue != "" && locationnameValue != undefined) {
            return true;
        }
        else {
            
        	
        	setError(this);
             s1 = false;
             return false;
        }
    });
    
    $(".contactNo").each(function () {
        var contactNoValue = $(this).val();
       // var regEx = /^[0123456789]\d{10}$/;
        var regEx = /^\d{10}$/;
        document.getElementById('noError').innerHTML = "";
        if (contactNoValue == null || contactNoValue == "" || contactNoValue == undefined) {
        	 var contactErr = document.createElement("label");
        	 contactErr.innerHTML = "Please enter Contact No";
		     document.getElementById('noError').appendChild(contactErr);
        		 setError(this);
                 s1 = false;
                 return false;
        	 }
        else if(!regEx.test(contactNoValue)){
        	 var contactErr = document.createElement("label");
        	 contactErr.innerHTML = "Please enter valid Contact No";
		     document.getElementById('noError').appendChild(contactErr);
        	setError(this);
            s1 = false;
            return false;
        }       
        else {            
        	
        	return true;
        }
    });
    
    $(".colorName").each(function () {
        var colorNameValue = $(this).val();
       
        checkColorExist(colorNameValue);
      
       
    });
    
    
    return s1;
    }
    else{
    	return false;
    }
	
}

function validateUpdateCL(){
	
	
	
	$(".addressType").each(function () {
        var addressTypeValue = $(this).val();
        if (addressTypeValue != null && addressTypeValue != "" && addressTypeValue != undefined) {
            s1 = true;
            return true;
        }
        else {
            s1 = false;
            setError(this);
            return false;
        }
       
    });
    if (s1) {

    $(".emailId").each(function () {
        var emailValue = $(this).val();
        if (emailValue != null && emailValue != "" && emailValue != undefined) {
            return true;
        }
        else {
            
        	
        	setError(this);
            s1 = false;
            return false;
        }
    });
    
    $(".address").each(function () {
        var addressValue = $(this).val();
        if (addressValue != null && addressValue != "" && addressValue != undefined) {
            return true;
        }
        else {
           
        	
        	setError(this);
            s1 = false;
            return false;
        }
    });
    $(".locationname").each(function () {
        var locationnameValue = $(this).val();
        if (locationnameValue != null && locationnameValue != "" && locationnameValue != undefined) {
            return true;
        }
        else {
            
        	
        	setError(this);
             s1 = false;
             return false;
        }
    });
    
    $(".contactNo").each(function () {
        var contactNoValue = $(this).val();
        if (contactNoValue != null && contactNoValue != "" && contactNoValue != undefined) {
            return true;
        }
        else {
            
        	
        	setError(this);
             s1 = false;
             return false;
        }
    });
    
    $(".colorName").each(function () {
        var colorNameValue = $(this).val();
       
        checkColorExist(colorNameValue);
      
       
    });
    
    
    return s1;
    }
    else{
    	return false;
    }
}



function addRow(tableID) 
        {
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
            	
            	
            	/* var cell3 = row.insertCell(2);
                 cell3.innerHTML="<select id='addressType' class='form-control showToolTip addressType' name = 'location[" + counts + "].addressType'>";
                 var x = document.getElementById("addressType");
                 var option1 = document.createElement("option");
                 option1.text = "Registered";
                 x.add(option1);
                 var option2 = document.createElement("option");
                 option2.text = "Branch";
                 x.add(option2);*/
            	
            	 var cell3 = row.insertCell(2);
               // cell3.innerHTML="<select id='addressType' class='form-control showToolTip addressType' name = 'location[" + counts + "].addressType'>";
                var element2 = document.createElement("select");
                element2.name = "location[" + counts + "].addressType";
                element2.id = "addressType"+counts;
                var option1 =  document.createElement("option");
                option1.innerHTML = "Branch";
                option1.value = "Branch";
                element2.add(option1,null);
                var option2 =  document.createElement("option");
                option2.innerHTML = "Registered";
                option2.value = "Registered";
                element2.add(option2,null);
                element2.onchange = new Function("setCheckLocation('"+counts+"')");
                cell3.appendChild(element2);
            					
                var cell4 = row.insertCell(3);
                //var houseNo = document.createElement("input");
                //houseNo.type = "text";
                //houseNo.size = "10";
                //houseNo.name = "location[" + counts + "].country";
                //houseNo.class = "text ui-widget-content ui-corner-all country";
                //cell3.appendChild(houseNo);
                cell4.innerHTML="<textarea rows = '1' class='form-control showToolTip address' data-toggle='tooltip' name = 'location[" + counts + "].address'>";
                
                var cell9 = row.insertCell(4);
                cell9.innerHTML="<input type='text'  class='form-control showToolTip pinCode' name = 'location[" + counts + "].pinCode'>";

              /*  var colorname = document.createElement("input");
                colorname.type = "text";
                colorname.size = "10";
                colorname.name = "location[" + counts + "].colorName";
                colorname.setAttribute("class", "form-control showToolTip color colorName");
              	colorname.value = "FFFFFF"; 
                cell8.appendChild(colorname);
                jscolor.init();*/
               

                var cell5 = row.insertCell(5);
/*                var street = document.createElement("input");
                street.type = "text";
                street.size = "10";
                street.name = "location[" + counts + "].city";
                cell4.appendChild(street);*/
                cell5.innerHTML="<input type='text'  class='form-control showToolTip locationname' name = 'location[" + counts + "].locationname'>";

                

                var cell6 = row.insertCell(6);
               /* var city = document.createElement("input");
                city.type = "text";
                city.size = "10";
                city.name = "location[" + counts + "].pinCode";
                cell5.appendChild(city);*/
                cell6.innerHTML="<input type='text'  class='form-control showToolTip contactNo' name = 'location[" + counts + "].contactNo'>";


                var cell7 = row.insertCell(7);
                /*var country = document.createElement("textarea");
                country.type = "text";
                country.size = "10";
                country.name = "location[" + counts + "].address";
                cell6.appendChild(country);*/
                cell7.innerHTML="<input type='text'  class='form-control showToolTip emailId' name = 'location[" + counts + "].emailId'>";

                
                var cell8 = row.insertCell(8);
                var colorname = document.createElement("input");
                colorname.type = "text";
                colorname.size = "10";
                colorname.name = "location[" + counts + "].colorName";
                colorname.setAttribute("class", "form-control showToolTip color colorName");
              	colorname.value = "FFFFFF"; 
                cell8.appendChild(colorname);
                jscolor.init();
               /* var llocation = document.createElement("input");
                llocation.type = "text";
                llocation.size = "10";
                llocation.name = "location[" + counts + "].locationname";
                cell7.appendChild(llocation);*/
               // cell7.innerHTML="<input type='text'  class='form-control showToolTip locationname' name = 'location[" + counts + "].locationname'>";

                
               // var cell8 = row.insertCell(8);
              //  cell8.innerHTML="<input type='text'  class='form-control showToolTip contactNo' name = 'location[" + counts + "].contactNo'>";
                
                var cell9 = row.insertCell(9);
                var element2 = document.createElement("input");
           		element2.type = "checkbox";           		
           		element2.name="chkbox[]";
            	cell9.appendChild(element2);
            	cell9.innerHTML="<input type='checkbox' id='checkLocation"+counts+"' title = 'In all Communications' readonly='readonly' disabled='disabled' name = 'location[" + counts + "].checkLocation'>";
            	
            	

        }

function setCheckLocation(counts){
	var addressType = document.getElementById('addressType'+counts).value;
	
	if(addressType == "Registered"){
		document.getElementById('checkLocation'+counts).checked = true;
	}
	else{
		document.getElementById('checkLocation'+counts).checked = false;
	}
}


function deleteRow(tableID){
	try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
 
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    if(rowCount <= 2) {
                        
                        jAlert('info', 'Cannot delete all the rows.', 'Info Dialog');

                        break;
                    }
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
 
 
            }
            }catch(e) {
            	jAlert('error', e, 'Error Dialog');

            }
}


function checkEmaildExist(emailid){
	document.getElementById('errorEmail').innerHTML="";
	var url = "checkEmaildExistClinicRegistration?emailid="+emailid+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkEmaildExistRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function checkEmaildExistRequest(){
	
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
				}
				
				 if(chk==1)
			     {	
					 document.getElementById('errorEmail').innerHTML="";
					 var userId = document.createElement("label");
					 userId.innerHTML = "Email id already Exist Please try another";
				     document.getElementById('errorEmail').appendChild(userId);
				     chk = 1;
				     emailIdExist = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('errorEmail').innerHTML="";
			    	 emailIdExist = 0;
			    	 chk = 0;
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

function checkUserIdExist(userId){
	document.getElementById('errorUserId').innerHTML="";
	var url = "checkUserIdExistClinicRegistration?userId="+userId+"";
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
function checkUserIdExistRequest(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
				}
				
				 if(chk==1)
			     {	
					 document.getElementById('errorUserId').innerHTML="";
					 var userId = document.createElement("label");
					 userId.innerHTML = "UserId already Exist Please try another";
				     document.getElementById('errorUserId').appendChild(userId);
				     chk = 1;
				     userIdExist = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('errorUserId').innerHTML="";
			    	 userIdExist = 0;
			    	 chk = 0;
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
	}
function checkColorExist(color){
	//alert(color);
	var chk=0;
	document.getElementById('errorColor').innerHTML="";
	if(color=='FFFFFF')
 	{
 	
		 var color = document.createElement("label");
		 color.innerHTML = "Color already Exist Please try another";
	     document.getElementById('errorColor').appendChild(color);
	     document.getElementById('errorColor').appendChild(document.createElement('br')); 
	     //chk = 2;
	     s1 = false;
	     //chk = 2;
	     return false;
	     
 	}
	else{
	var url = "checkColorExistClinicRegistration?color="+color+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = checkColorExistRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}    
function checkColorExistRequest(){

	
if (req.readyState == 4) {
	var chk=0;
		if (req.status == 200) {
			//alert(req.responseText);
			var exist = req.responseText;
			if(exist == 'false')
			{
				
				chk = 1;
			}
			
			 if(chk==1)
		     {	
				
				 var color = document.createElement("label");
				 color.innerHTML = "Color already Exist Please try another";
			     document.getElementById('errorColor').appendChild(color);
			     
			     s1 = false;
			    // chk = 2;		     
			     
		        return false;
		     }
		     else
		     {
		    	// chk = 1;
		    	 return true;
		    	 
		     }
			
			
    		
         
         }
	}
}

function checkLocationField(id){
	
	
	//var id = document.getElementById('locationid').value;	
	var checkLoc = document.getElementById('checkLocation').checked;
	//alert(id);
	var url = "checkLocationClinicRegistration?checkLoc="+checkLoc+"&id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = checkLocationRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
    
function checkLocationRequest(){	
if (req.readyState == 4) {	
		if (req.status == 200) {
						
			showGrowl('', 'Location Setting changed Successfully !!', 'success', 'fa fa-check');
}
}
}


function editValidate(){
	
	var body2Note =	nicEditors.findEditor('emailBody').getContent();
	document.getElementById('loc_direction').value = body2Note;
	
	validatePromoJSON = {
            "validatorData": [
                              { "Element": "#locationname", "FunctionName": "notEmpty" },
                              { "Element": "#addressType", "FunctionName": "dropDown" },
                              { "Element": "#address", "FunctionName": "notEmpty" },
                              { "Element": "#contactNo", "FunctionName": "notEmpty" },
                              { "Element": "#emailId", "FunctionName": "notEmpty" },
                              { "Element": "#emailId", "FunctionName": "emailId" },
                              { "Element": "#contactNo", "FunctionName": "numbersOnlyOptional" }
            ]
        };
	return iterateThroughtElemsForValidations(validatePromoJSON);
}



function setEditCheckLocation(){
	
	var addressType = document.getElementById('addressType').value;
	
	if(addressType == "Registered"){
		document.getElementById('checkLocation').checked = true;
	}
	else{
		document.getElementById('checkLocation').checked = false;
	}
}

function setBasicPackage(){
	
	var basicPkg = document.getElementById('basicPackage').checked;
	if(basicPkg == true){
		document.getElementById('diaryManagement').checked = true;
		document.getElementById('appointmentBooking').checked = true;
		document.getElementById('basicFinance').checked = true;
	}
	else{
		document.getElementById('diaryManagement').checked = false;
		document.getElementById('appointmentBooking').checked = false;
		document.getElementById('basicFinance').checked = false;
	}
}

function setAdvancePackage(){
	var advancePkg = document.getElementById('advancePackage').checked;
	if(advancePkg == true){
		document.getElementById('fullFinance').checked = true;
		document.getElementById('medicalRecord').checked = true;		
	}
	else{
		document.getElementById('fullFinance').checked = false;
		document.getElementById('medicalRecord').checked = false;
	}
}

function setPremierPackage(){
	var premierPkg = document.getElementById('premierPackage').checked;
	if(premierPkg == true){
		document.getElementById('clinicResourceMngment').checked = true;
		document.getElementById('clinicPayrollMngment').checked = true;		
	}
	else{
		document.getElementById('clinicResourceMngment').checked = false;
		document.getElementById('clinicPayrollMngment').checked = false;
	}
}

function setOtherPackage(){
	var otherPkg = document.getElementById('otherPackage').checked;
	if(otherPkg == true){
		document.getElementById('communication').checked = true;
		document.getElementById('report').checked = true;
		document.getElementById('assessmentForms').checked = true;
	}
	else{
		document.getElementById('communication').checked = false;
		document.getElementById('report').checked = false;
		document.getElementById('assessmentForms').checked = false;
	}
}

function setExcessDevices(){
	var excessDevices = document.getElementById('excessDevices').checked;
	if(excessDevices == true){
		document.getElementById('desktop').checked = true;
		document.getElementById('mobile').checked = true;
		document.getElementById('iOS').checked = true;
		document.getElementById('tablet').checked = true;
	}
	else{
		document.getElementById('desktop').checked = false;
		document.getElementById('mobile').checked = false;
		document.getElementById('iOS').checked = false;
		document.getElementById('tablet').checked = false;
	}
}

function setJobTitleSetting(id){
	var url = "getJobTitleSettingClinicRegistration?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = checkLocationRequest;
    req.open("GET", url, true); 
    req.send(null);
	}
    
function checkLocationRequest(){	
if (req.readyState == 4) {	
		if (req.status == 200) {
	
}
}
}


function setAllRoleAccess(){
	var modulename = document.getElementById("modulename").value;
	var job_title = document.getElementById("jobtitle").value;
	if(job_title=='0'){
		jAlert('error', "Please select jobtitle!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
	var url = "getAllAccessAuthorityClinicRegistration?modulename="+modulename+"&job_title="+job_title+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAllRoleAccessRequest;
    req.open("GET", url, true); 
    req.send(null);
	}
	
	}
    
function setAllRoleAccessRequest(){	
if (req.readyState == 4) {	
		if (req.status == 200) {
			var data1 = req.responseText;
			var data = data1.split("~");
			document.getElementById("tbodydiv").innerHTML = data[0];
			document.getElementById("moduleid").value = data[1];
			document.getElementById("roleid").value = data[2];
		}
	}
}