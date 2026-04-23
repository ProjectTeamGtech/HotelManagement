var treatEpisodeId = "";
var chkmob = 0;
var chkemail = 0;
var typeofTp = 0;
function showAnotherPopup(){
	
	if(editAppointId==0){
		
		showAllPatientPopUp();
	}
	
/*	$(document.getElementById('dashboardDiv')).css('width', '80%');
	document.getElementById('anothertd').style.display = '';
	document.getElementById('clientSearchDiv').style.display = '';
	document.getElementById('addPatientDiv').style.display = 'none';
	document.getElementById('addTreatmentEpisodeDiv').style.display = 'none';
	document.getElementById('treatmenttd').style.display = 'none';*/

}

function showAllPatientPopUp(){

var url = "Client";



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
//			jQuery.noConflict(); 
			$('#clientSearchDiv').modal( "show" );
			
         }
	}
}

function searchPatient(){
	var searchText = document.getElementById("searchText").value;
	var url = "searchPatientClient?searchText="+searchText+"";

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
	         	
				var language = document.getElementById("langbyusr").value;
				  var selectField = document.querySelector("#google_translate_element select");
				  for(var i=0; i < selectField.children.length; i++){
				    var option = selectField.children[i];
				    // find desired langauge and change the former language of the hidden selection-field 
				    if(option.value==language){
				       selectField.selectedIndex = i;
				       // trigger change event afterwards to make google-lib translate this side
				       selectField.dispatchEvent(new Event('change'));
				       break;
				    }
				  }
	         }
		}
	}

function addPatient(){
			
			/*$(document.getElementById('dashboardDiv')).css('width', '80%');
			document.getElementById('anothertd').style.display = '';
			document.getElementById('clientSearchDiv').style.display = 'none';
			document.getElementById('addPatientDiv').style.display = '';
			document.getElementById('addTreatmentEpisodeDiv').style.display = 'none';
			document.getElementById('treatmenttd').style.display = 'none';*/
			document.getElementById('savebtn').style.display = 'none';
			document.getElementById('breadcrumbid').style.display = 'none';
			document.getElementById("savePatientNow").disabled = false;
			$('#addPatientDiv').modal( "show" );
			
			
}

function savePatient(){
	//alert('hi');
	
	var adhno = document.getElementById('adhno').value;
	var title = document.getElementById('title').value;
	var firstname = document.getElementById('firstName').value;
	var middleName = document.getElementById('middleName').value;
	
	var lastName = document.getElementById('lastName').value;
	var gender = document.getElementById('gender').value;
	var dob = document.getElementById('dob').value;
	var address = document.getElementById('address').value;
	var town = document.getElementById('town').value;
	var state=document.getElementById('state').value;
	//var county = document.getElementById('county').value;
	var country = document.getElementById('country').value;
	var postCode = document.getElementById('postCode').value;
	var mobNo = document.getElementById('mobNo').value;
	var email = document.getElementById('email').value;
	var reference = document.getElementById('reference').value;
	if(reference == 'Other'){
		reference = document.getElementById('otherRef').value;	
	}
	
	var age=document.getElementById("age").value;
	//var whopay = document.getElementById('whopay').value;
	//var type = document.getElementById('type').value;
	var company = document.getElementById('typeName').value;
	var policyNo = document.getElementById('policyNo').value;
	var expiryDate = document.getElementById('expiryDate').value;
	var gpname = document.getElementById('gpname').value;	
	var tratmentType = document.getElementById('treatmentType').value;
	var regEx = /^\d{10}$/;
    var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var chk = 0;
	//var reference = document.getElementById('reference').value;
	var occupation = document.getElementById('occupation').value;
	//var patientType = document.getElementById('patientType').value;
	//var sourceOfIntro = document.getElementById('sourceOfIntro').value;
	var treatmentType = document.getElementById('treatmentType').value;
	var whopay = document.getElementById('whopay').value;
	var type = document.getElementById('type').value;
	var typeName = document.getElementById('typeName').value;
	var doctorsurgery = document.getElementById('doctorsurgery').value;
	var gpname = document.getElementById('gpname').value;
	var secondLineaddress = document.getElementById('secondLineaddress').value;
	var policyExcess = document.getElementById('policyExcess').value;
	var relativename = document.getElementById('relative_name').value;
	var relativeno = document.getElementById('relativeno').value;
	var maritalsts=document.getElementById('maritalsts').value;
	var anniversary=document.getElementById('dob1').value;
	var mothername='', fathername='',birthplace='';
	fathername=document.getElementById('fathername').value;
	if(document.getElementById('mothername')){
		mothername=document.getElementById('mothername').value;
		fathername=document.getElementById('fathername').value;
		birthplace=document.getElementById('birthplace').value;
	}
	var fulltime='00:00:00';
	if(document.getElementById('hourls')){
	var hour = document.getElementById('hourls').value;
	var minute = document.getElementById('minutels').value;
	 fulltime=hour+":"+minute+":"+"00";
	}
	document.getElementById("refError").innerHTML = "";	
	document.getElementById("fnameError").innerHTML = "";	
	document.getElementById("lnameError").innerHTML = "";	
	//document.getElementById("dobError").innerHTML = "";	
	document.getElementById("addressError").innerHTML = "";	
	document.getElementById("townError").innerHTML = "";	
	document.getElementById("postCodeError").innerHTML = "";	
	document.getElementById("mobNoError1").innerHTML = "";	
	document.getElementById("emailError1").innerHTML = "";
	document.getElementById("conError").innerHTML = "";
	document.getElementById("wwpError").innerHTML = "";	
	document.getElementById("tpError").innerHTML = "";	
	document.getElementById("tpnameError").innerHTML = "";	
	document.getElementById("surError").innerHTML = "";	
	document.getElementById("gpnameError").innerHTML = "";
	document.getElementById("ageError").innerHTML = "";		
	document.getElementById("genderError").innerHTML = "";
	document.getElementById("adhnoError").innerHTML = "";
	var adharnoregEx = /^[0123456789]\d{11}$/;
	if(title == "" || title=="0"){
	    var title = document.createElement("label");
	    title.innerHTML = "please Select Initial";
	    title.setAttribute("class","text-danger");
	    document.getElementById('fnameError').appendChild(title);
	    chk=1;
	    }
	if(firstname == ""){
		var firstName = document.createElement("label");
		firstName.innerHTML = "Please Enter First Name";
		firstName.setAttribute("class","text-danger");
	    document.getElementById('fnameError').appendChild(firstName);
	    chk=1;
	}
//	if(firstname.match(/\s/g)){
//		var firstName = document.createElement("label");
//		firstName.innerHTML = "Space Not Allow";
//	    document.getElementById('fnameError').appendChild(firstName);
//	    chk=1;
//	}
//	if(firstname.match(/[^a-zA-Z]+/)){
//		var firstName = document.createElement("label");
//		firstName.innerHTML = "Special Character or Number Not Allow";
//	    document.getElementById('fnameError').appendChild(firstName);
//	    chk=1;
//	}
	if(lastName == ""){
		var lastName = document.createElement("label");
		lastName.innerHTML = "Please Enter Last Name";
		lastName.setAttribute("class","text-danger");
	    document.getElementById('lnameError').appendChild(lastName);
	    chk=1;
	}
	var lastName = document.getElementById('lastName').value;
//	if(lastName.match(/\s/g)){
//		var lastName = document.createElement("label");
//		lastName.innerHTML = "Space Not Allow";
//	    document.getElementById('lnameError').appendChild(lastName);
//	    chk=1;
//	}
//	if(lastName.match(/[^a-zA-Z]+/)){
//		var lastName = document.createElement("label");
//		lastName.innerHTML = "Special Character or Number Not Allow";
//	    document.getElementById('fnameError').appendChild(lastName);
//	    chk=1;
//	}
//	if(middleName.match(/[^a-zA-Z]+/)){
//		var middleName = document.createElement("label");
//		middleName.innerHTML = "Special Character or Number Not Allow";
//	    document.getElementById('fnameError').appendChild(middleName);
//	    chk=1;
//	}
	/*if(dob == ""){
		var dob = document.createElement("label");
		dob.innerHTML = "Please Enter DOB";
	    document.getElementById('dobError').appendChild(dob);
	    chk=1;
	}*/
	var hospitalborn="";
	var hospitalborn1 = document.getElementById('hospitalborn').checked;
	if(hospitalborn1){
		hospitalborn="1";
	}else{
		hospitalborn="0";
	}
	
	
	
	if(age == ""){
		var age = document.createElement("label");
		age.innerHTML = "Please Enter Age";
		age.setAttribute("class","text-danger");
	    document.getElementById('ageError').appendChild(age);
	    chk=1;
	}
	if(gender=="0"){
	 		var gen = document.createElement("label");
			gen.innerHTML = "Please Select Gender";
			gen.setAttribute("class","text-danger");
			document.getElementById('genderError').appendChild(gen);
	    	chk=1;
	}
	
	if(address == ""){
		var address = document.createElement("label");
		address.innerHTML = "Please Enter Address";
		address.setAttribute("class","text-danger");
	    document.getElementById('addressError').appendChild(address);
	    chk=1;
	}
	if(town == "" || town=="0"){
		var town = document.createElement("label");
		town.innerHTML = "Please Enter Town";
		town.setAttribute("class","text-danger");
	    document.getElementById('townError').appendChild(town);
	    chk=1;
	}
	
	if(mobNo == "") {
		
	}
	else if(!regEx.test(mobNo)) {
		var mobNo = document.createElement("label");
		mobNo.innerHTML = "Please Enter Valid No.";
		mobNo.setAttribute("class","text-danger");
	    document.getElementById('mobNoError1').appendChild(mobNo);
	    chk=1;
	}

	else if(mobNo.length!=10) {
		var mobNo = document.createElement("label");
		mobNo.innerHTML = "Please Enter Valid No.";
		mobNo.setAttribute("class","text-danger");
	    document.getElementById('mobNoError1').appendChild(mobNo);
	    chk=1;
	}
	
	if(!adharnoregEx.test(adhno) && adhno !="") {
			var adhno = document.createElement("label");
			adhno.innerHTML = "Please Enter Valid Adhar No.";
			adhno.setAttribute("class","text-danger");
		    document.getElementById('adhnoError').appendChild(adhno);
		    chk=1;
		}
	
	if(email == "") {
		
	}
	else if(!emailregEx.test(email)){
		var email = document.createElement("label");
		email.innerHTML = "Please Enter Valid No.";
		email.setAttribute("class","text-danger");
	    document.getElementById('emailError1').appendChild(email);
	    chk=1;
	}
	var typenamebyuse='';
	var compname='';
	var neisno='';
	var designationbytp='';
	var unitstation='';
	var claimbytp='';
	var relationvbytp='';
	var colliery='';
	var areabytp='';
	var policyholder='';
	 if(whopay == "Third Party"){
		 if(type == 0){		
				var type1 = document.createElement("label");
				type1.innerHTML = "Please select type";
			    document.getElementById('tpError').appendChild(type1);
			    chk=1;
			}
			if(typeName == 0){		
				var tpname = document.createElement("label");
				tpname.innerHTML = "Please select Third Party Name";
			    document.getElementById('tpnameError').appendChild(tpname);
			    chk=1;
			}
			
			 typenamebyuser=document.getElementById("type").options[document.getElementById('type').selectedIndex].text;
			 compname=document.getElementById("compname").value;
			 neisno=document.getElementById("neisno").value;
			 designationbytp=document.getElementById("designationbytp").value;
			 unitstation=document.getElementById("unitstation").value;
			 claimbytp=document.getElementById("claimbytp").value;
			 relationvbytp=document.getElementById("relationvbytpe").value;
			 colliery=document.getElementById("colliery").value;
			 areabytp=document.getElementById("areabytp").value;
			 policyholder=document.getElementById("policyholder").value;
			
//			if(typenamebyuser=='CGHS'){
//				if(compname==''){
//			        
//			          jAlert('error', "please enter Company name!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(neisno==''){
//				        
//			          jAlert('error', "please enter NEIS/Card No!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(designationbytp==''){
//				        
//			          jAlert('error', "please enter Designation!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(unitstation==''){
//				        
//			          jAlert('error', "please enter Unit/Station!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(claimbytp==''){
//				        
//			          jAlert('error', "please enter Claim ID!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(relationvbytp==''){
//				        
//			          jAlert('error', "please enter Relation!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }
//				
//				
//			}else if(typenamebyuser=='WCL'){
//				
//				if(compname==''){
//			        
//			          jAlert('error', "please enter Company name!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(neisno==''){
//				        
//			          jAlert('error', "please enter NEIS/Card No!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(designationbytp==''){
//				        
//			          jAlert('error', "please enter Designation!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(colliery==''){
//				        
//			          jAlert('error', "please enter Colliery!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(areabytp==''){
//				        
//			          jAlert('error', "please enter Area!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }else if(relationvbytp==''){
//				        
//			          jAlert('error', "please enter Relation!", 'Error Dialog');
//						
//							setTimeout(function() {
//								$("#popup_container").remove();
//								removeAlertCss();
//							}, alertmsgduration);
//							chk=1;
//			     }
//				
//			}
			 
	 }
		 
	/*if(reference == 0){		
		var ref = document.createElement("label");
		ref.innerHTML = "Please select Reference";
	    document.getElementById('refError').appendChild(ref);
	    chk=1;
	}*/
	
	/*if(treatmentType == 0){		
		var con = document.createElement("label");
		con.innerHTML = "Please select treatmentType";
	    document.getElementById('conError').appendChild(con);
	    chk=1;
	}*/
	
	
	/*if(doctorsurgery == 0){		
		var surgery = document.createElement("label");
		surgery.innerHTML = "Please select doctor surgery";
	    document.getElementById('surError').appendChild(surgery);
	    chk=1;
	}*/
	if(doctorsurgery!=0){
	if(gpname == 0){		
		var gpnme = document.createElement("label");
		gpnme.innerHTML = "Please select GPName";
	    document.getElementById('gpnameError').appendChild(gpnme);
	    chk=1;
	}
	}
	if(chk==1)
	{
		return false;
	}
	else{
		 if(validchk == 0 && validchk1 == 0){
				
			 $('#dashboardloaderPopup').modal( "show" );
			 
			 //var url = "savePatientClient?title="+title+"&firstName="+firstname+"&lastName="+lastName+"&middleName="+middleName+"&gender="+gender+"&adhno="+adhno+"&address="+address+"&town="+town+"&country="+country+"&postCode="+postCode+"&mobNo="+mobNo+"&email="+email+"&type="+type+"&company="+company+"&policyNo="+policyNo+"&expiryDate="+expiryDate+"&whopay="+whopay+"&gpname="+gpname+"&tratmentType="+tratmentType+"&reference="+reference+"&secondLineaddress="+secondLineaddress+"&doctorsurgery="+doctorsurgery+"&policyExcess="+policyExcess+"&occupation="+occupation+"&state="+state+"&age="+age+"";
				var url = "savePatientClient?title="+title+"&firstName="+firstname+"&lastName="+lastName+"&middleName="+middleName+"&gender="+gender+"&dob="+dob+"&address="+address+"&town="+town+"&country="+country+"&postCode="+postCode+"&mobNo="+mobNo+"&email="+email+"&type="+type+"&company="+company+"&policyNo="+policyNo+"&expiryDate="+expiryDate+"&whopay="+whopay+"&gpname="+gpname+"&tratmentType="+tratmentType+"&reference="+reference+"&secondLineaddress="+secondLineaddress+"&doctorsurgery="+doctorsurgery+"&policyExcess="+policyExcess+"&occupation="+occupation+"&state="+state+"&age="+age+"&adhno="+adhno+"&relativename="+relativename+"&relativeno="+relativeno+"&hospitalborn="+hospitalborn+"&compname="+compname+"&neisno="+neisno+"&designationbytp="+designationbytp+"&unitstation="+unitstation+"&claimbytp="+claimbytp+"&relationvbytp="+relationvbytp+"&colliery="+colliery+"&areabytp="+areabytp+"&policyholder="+policyholder+"&maritalsts="+maritalsts+"&mothername="+mothername+"&fathername="+fathername+"&birthplace="+birthplace+"&fulltime="+fulltime+"&anniversary="+anniversary+"";	
				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = savePatientRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);
				    return true;
			 }
		
	}
	
	
	 /*if(whopay == "Third Party" && typeName == 1){
		 
	 validatePromoJSON = {
            "validatorData": [
                    { "Element": "#firstName", "FunctionName": "alphabetsOnlyWithSpace" },
                    { "Element": "#middleName", "FunctionName": "alphabetsOnlyWithSpaceOptional" },
                    { "Element": "#lastName", "FunctionName": "alphabetsOnlyWithSpace" },
                    { "Element": "#dob", "FunctionName": "notEmpty" },
                    { "Element": "#address", "FunctionName": "notEmpty" }, 
                    { "Element": "#town", "FunctionName": "notEmpty" },
                    { "Element": "#postCode", "FunctionName": "notEmpty" },
                    { "Element": "#country", "FunctionName": "dropDown" }, 
                    
                    { "Element": "#mobNo", "FunctionName": "mobileNoOptional" },
                    { "Element": "#email", "FunctionName": "emailIdOptional" },
                    { "Element": "#whopay", "FunctionName": "dropDown" },
                    { "Element": "#type", "FunctionName": "dropDown" },
                    { "Element": "#typeName", "FunctionName": "dropDown" },
	 
            ]
        };
	 }
	 else{
		 validatePromoJSON = {
	             "validatorData": [
	                     { "Element": "#firstName", "FunctionName": "alphabetsOnlyWithSpace" },
	                     { "Element": "#middleName", "FunctionName": "alphabetsOnlyWithSpaceOptional" },
	                     { "Element": "#lastName", "FunctionName": "alphabetsOnlyWithSpace" },
	                     { "Element": "#dob", "FunctionName": "notEmpty" },
	                     { "Element": "#address", "FunctionName": "notEmpty" }, 
	                     { "Element": "#town", "FunctionName": "notEmpty" },
	                     { "Element": "#postCode", "FunctionName": "notEmpty" },
	                     { "Element": "#country", "FunctionName": "dropDown" }, 
	                     { "Element": "#mobNo", "FunctionName": "mobileNoOptional" },
	                     { "Element": "#email", "FunctionName": "emailIdOptional" },
	                     { "Element": "#whopay", "FunctionName": "dropDown" }
	                     
		 
	             ]
	         };
	 }*/
	 
	// alert(iterateThroughtElemsForValidations(validatePromoJSON));
	/* if(iterateThroughtElemsForValidations(validatePromoJSON))
		 {*/
	 
		
	
			    
		      
	
	 
	
 }
	function savePatientRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allPatient").innerHTML = req.responseText;
				$('#addPatientDiv').modal( "hide" );
				resetAddClientFileds();
				document.getElementById('title').value = "0";
				document.getElementById('gender').value = "0";
				document.getElementById('town').value = "0";
				document.getElementById('state').value = "0";
		        document.getElementById('fathername').value = "";
				
				document.getElementById('hospitalborn').checked=false;
				$('#dashboardloaderPopup').modal( "hide" );
				$("#savePatientNow").removeAttr("disabled");
				$("#savePatientNow").text("Save");
				
	         }
		}
	}
	
	function resetAddClientFileds(){
	//alert('hi');
	document.getElementById('title').value = "Mr.";
	document.getElementById('firstName').value ="";
	document.getElementById('middleName').value="";
	document.getElementById('lastName').value = "";
	document.getElementById('gender').value = "Male";
	document.getElementById('dob').value = "";
	document.getElementById('address').value = "";
	document.getElementById('town').value = "";
	document.getElementById('postCode').value = "";
/*	document.getElementById('reference').value = "0"; 
*/	document.getElementById('mobNo').value = "";
	document.getElementById('email').value = "";
	document.getElementById('doctorsurgery').value = "0";
	document.getElementById('gpname').value= "0";
	document.getElementById('treatmentType').value= "0";
	document.getElementById('whopay').value = "0";
	document.getElementById('type').value = "0";
	document.getElementById('typeName').value = "0";
	document.getElementById('policyNo').value = "";
	document.getElementById('expiryDate').value = "";
}
	
	
	
	function cancelAddPatient(){
		$(document.getElementById('dashboardDiv')).css('width', '80%');
		document.getElementById('anothertd').style.display = '';
		document.getElementById('clientSearchDiv').style.display = '';
		document.getElementById('addPatientDiv').style.display = 'none';
		document.getElementById('addTreatmentEpisodeDiv').style.display = 'none';
		document.getElementById('treatmenttd').style.display = 'none';
	}
function closeSearchPatient(){
	$(document.getElementById('dashboardDiv')).css('width', '40%');
	document.getElementById('anothertd').style.display = 'none';
	document.getElementById('clientSearchDiv').style.display = 'none';
	document.getElementById('appointment').style.display = '';
	
	document.getElementById('addPatientDiv').style.display = 'none';
	document.getElementById('addTreatmentEpisodeDiv').style.display = 'none';
	document.getElementById('treatmenttd').style.display = 'none';
	
}


function addTreatmentEpisode(){
	resetTreatmentEpisodeFields();
	var client = document.getElementById('client').value;
	var clientId = document.getElementById('clientId').value;
	var conditionType = document.getElementById('condition').value;
	document.getElementById('treatmentType1').value = conditionType;
	$("#treatmentType1").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	
	
	if(client ==""){
        jAlert('error', 'Plese Select Client.', 'Error Dialog');
    	setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
    }
	
	else{
		
		
		var url = "setWhoWillPayClient?clientId="+clientId+"&client="+client+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setWhoWillPayClientRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);	
		
	}
	function setWhoWillPayClientRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					
					var data = req.responseText;
					
					var temp = data.split("/");
					var name = temp[0];			
					var whopay= temp[1];
					treatmentpolicyno = temp[2];
					if(document.getElementById("paybypatient1").checked == true)
						
					{
						
						document.getElementById('invoicee').value = name;	
						document.getElementById('authorisationCode').disabled = false;
						document.getElementById('payby').checked = true;
						document.getElementById('payby1').checked = false;
						document.getElementById('policynodiv').style.display='';
						document.getElementById('trtmentPolicyNo').value = treatmentpolicyno;
					}
					else{
						
						document.getElementById('invoicee').value = document.getElementById('client').value;;	
						document.getElementById('authorisationCode').disabled = true;
						document.getElementById('payby1').checked = true;
						document.getElementById('payby').checked = false;
						document.getElementById('policynodiv').style.display='none';
						
					}
						
					$('#addTreatmentEpisodeDiv').modal( "show" );
						
					
					
					
		         }
			}
		 
		}
	
}

function addNewThirdParty(){
	document.getElementById('popuptype').value = document.getElementById('ctpthirdPartyType').value; 
	$("#popuptype").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	document.getElementById("savetpbtn").style.display = 'none';
	//document.getElementById("tpbreadcrum").style.display = 'none';
	document.getElementById('tpbreadcrum').style.display = 'none';
	$('#thirdPartyDetailsPopup').modal( "show" );
}

function saveNewTP4(){
	
	var companyName = document.getElementById('popuptypeName').value;
	var id = document.getElementById('popuptype').value;
	//get all values
	var type = document.getElementById('popuptype').value;
	typeofTp = type;
	var newtpname = document.getElementById('ppcompanyName').value;
	var telephoneLine = document.getElementById('pptelephoneLine').value;
	var compnyEmail = document.getElementById('ppcompnyEmail').value;
	var address = document.getElementById('pptpaddress').value;
	var town = document.getElementById('pptptown').value;
	
	var postcode = document.getElementById('pptppostcode').value;
	var country = document.getElementById('pptpcountry').value;
	var gpname = document.getElementById('ppgpname').value;
	var workphno = document.getElementById('ppworkphno').value;
	var gpemailid = document.getElementById('ppgpemailid').value;
	var gpfax = document.getElementById('ppgpfax').value;
	var notes = document.getElementById('ppnotes').value;
	var outInvoiceLimit = document.getElementById('ppoutInvoiceLimit').value;
	var accountWarningLimit = document.getElementById('ppaccountWarningLimit').value;
	var creditDuration = document.getElementById('ppcreditDuration').value;
	var creditReminderDuration = document.getElementById('ppcreditReminderDuration').value;
	var dnaLimit = document.getElementById('ppdnaLimit').value;
	var dnaInitialApmt = document.getElementById('ppdnaInitialApmt').value;
	var compltInitialApmt = document.getElementById('ppcompltInitialApmt').value;
	var dnafollowupApmt = document.getElementById('ppdnafollowupApmt').value;
	var compltfollowupApmt = document.getElementById('ppcompltfollowupApmt').value;
	var dnafinalApmt = document.getElementById('ppdnafinalApmt').value;
	var compltfinalApmt = document.getElementById('ppcompltfinalApmt').value;
	var dnamaintenanceApmt = document.getElementById('ppdnamaintenanceApmt').value;
	var compltmaintenanceApmt = document.getElementById('ppcompltmaintenanceApmt').value;
	
	
	 var chk = 0;
	 
		document.getElementById("popuptpError").innerHTML = "";	
		document.getElementById("popuptpnameError").innerHTML = "";	
		
		 if(type == 0){		
				var ptype = document.createElement("label");
				ptype.innerHTML = "Please select Third Party Type";
			    document.getElementById('popuptpError').appendChild(ptype);
			    chk=1;
			}			
			
			if(chk==1)
		    {
		       return false;
		    }
	 		

	 if(companyName==0){
	 validatePromoJSON = {
            "validatorData": [
                    { "Element": "#popuptype", "FunctionName": "dropDown" },
                    { "Element": "#ppcompnyEmail", "FunctionName": "emailIdOptional" },
                    { "Element": "#ppcompanyName", "FunctionName": "notEmpty" },
                    { "Element": "#ppoutInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppcreditDuration", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppaccountWarningLimit", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppcreditReminderDuration", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppdnaInitialApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppdnafollowupApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppdnafinalApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppdnamaintenanceApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppcompltInitialApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppcompltfollowupApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppcompltfinalApmt", "FunctionName": "numbersOnlyOptional" },
                    { "Element": "#ppcompltmaintenanceApmt", "FunctionName": "numbersOnlyOptional" }

            ]
        };
	 }
	 else{
		 validatePromoJSON = {
	             "validatorData": [
	                  { "Element": "#popuptype", "FunctionName": "dropDown" },
	                  { "Element": "#ppcompnyEmail", "FunctionName": "emailIdOptional" },
	                  { "Element": "#ppoutInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppcreditDuration", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppaccountWarningLimit", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppcreditReminderDuration", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppdnaInitialApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppdnafollowupApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppdnafinalApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppdnamaintenanceApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppcompltInitialApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppcompltfollowupApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppcompltfinalApmt", "FunctionName": "numbersOnlyOptional" },
	                  { "Element": "#ppcompltmaintenanceApmt", "FunctionName": "numbersOnlyOptional" }
	                     
		 
	             ]
	         };
	 }
	
	 if(id==1){
		 if(companyName==0){
			 validatePromoJSON = {
		             "validatorData": [
		                     { "Element": "#popuptype", "FunctionName": "dropDown" },
		                     { "Element": "#ppcompnyEmail", "FunctionName": "emailIdOptional" },
		                     { "Element": "#ppcompanyName", "FunctionName": "notEmpty" },
		                     { "Element": "#ppgpname", "FunctionName": "notEmpty" }

		             ]
		         };
			 }
			 else{
				 validatePromoJSON = {
			             "validatorData": [
			                  { "Element": "#popuptype", "FunctionName": "dropDown" },
			                  { "Element": "#ppcompnyEmail", "FunctionName": "emailIdOptional" },
			                  { "Element": "#ppgpname", "FunctionName": "notEmpty" }
			                     
				 
			             ]
			         };
			 }
		 
	 }
	 if(id==2){
		
		 if(companyName==0){
			 validatePromoJSON = {
		             "validatorData": [
		                     { "Element": "#popuptype", "FunctionName": "dropDown" },
		                     { "Element": "#ppcompnyEmail", "FunctionName": "emailIdOptional" },
		                     { "Element": "#ppcompanyName", "FunctionName": "notEmpty" },
		                     { "Element": "#ppoutInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppcreditDuration", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppaccountWarningLimit", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppcreditReminderDuration", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppdnaInitialApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppdnafollowupApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppdnafinalApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppdnamaintenanceApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppcompltInitialApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppcompltfollowupApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppcompltfinalApmt", "FunctionName": "numbersOnlyOptional" },
		                     { "Element": "#ppcompltmaintenanceApmt", "FunctionName": "numbersOnlyOptional" }

		                     
		                     

		             ]
		         };
			 }
			 else{
				 validatePromoJSON = {
			             "validatorData": [
			                  { "Element": "#popuptype", "FunctionName": "dropDown" },
			                  { "Element": "#ppcompnyEmail", "FunctionName": "emailIdOptional" },
			                  { "Element": "#ppoutInvoiceLimit", "FunctionName": "numbersOnlyOptional" },
			                  { "Element": "#ppcreditDuration", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppaccountWarningLimit", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppcreditReminderDuration", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppdnaInitialApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppdnafollowupApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppdnafinalApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppdnamaintenanceApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppcompltInitialApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppcompltfollowupApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppcompltfinalApmt", "FunctionName": "numbersOnlyOptional" },
			                     { "Element": "#ppcompltmaintenanceApmt", "FunctionName": "numbersOnlyOptional" }
			                     
				 
			             ]
			         };
			 }
	 }
		
		 if(iterateThroughtElemsForValidations(validatePromoJSON))
		 {
			 var url = "saveNewTpAjaxThirdParty?type="+type+"&newtpname="+newtpname+"&telephoneLine="+telephoneLine+"&compnyEmail="+compnyEmail+"" +
			 "&address="+address+"&town="+town+"&postcode="+postcode+"&country="+country+"&gpname="+gpname+"&workphno="+workphno+"" +
			 "&gpemailid="+gpemailid+"&gpfax="+gpfax+"&notes="+notes+"&outInvoiceLimit="+outInvoiceLimit+"&accountWarningLimit="+accountWarningLimit+"" +
			 "&creditDuration="+creditDuration+"&creditReminderDuration="+creditReminderDuration+"&dnaLimit="+dnaLimit+"&dnaInitialApmt="+dnaInitialApmt+"" +
			 "&compltInitialApmt="+compltInitialApmt+"&dnafollowupApmt="+dnafollowupApmt+"&compltfollowupApmt="+compltfollowupApmt+"&dnafinalApmt="+dnafinalApmt+"" +
			 "&compltfinalApmt="+compltfinalApmt+"&dnamaintenanceApmt="+dnamaintenanceApmt+"&compltmaintenanceApmt="+compltmaintenanceApmt+"";
			 
		    	if (window.XMLHttpRequest) {
		    			req = new XMLHttpRequest();
		    		}
		    		else if (window.ActiveXObject) {
		    			isIE = true;
		    			req = new ActiveXObject("Microsoft.XMLHTTP");
		    		}   
		    	               
		    	    req.onreadystatechange = saveNewTP4Request;
		    	    req.open("GET", url, true); 
		    	              
		    	    req.send(null);
		    	    return true;
		 }
		 else{
			 return false;
		 }
	
	
}
function saveNewTP4Request(){
if (req.readyState == 4) {
	if (req.status == 200) {
	
		
		document.getElementById("ctpName").innerHTML = req.responseText;
		$("#ctpName").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		
		
		document.getElementById('ctpthirdPartyType').value = typeofTp;
		$("#ctpthirdPartyType").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		tempAlert("Third Party Details added successfully.",5000);
		$('#thirdPartyDetailsPopup').modal( "hide" );
     
     }
}
}

function setEpisodeDetails(id){
	
	treatEpisodeId = id;
	var url = "getSessionsDetailsTreatmentEpisode?id="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setEpisodeDetailsRequest;
    req.open("GET", url, true); 
              
    req.send(null);


    return true;
}



function setEpisodeDetailsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			 var data = req.responseText;
			 var temp = data.split("/");
			
			document.getElementById('sessionDetail').innerHTML="";
			document.getElementById('treatmentEpisodeError').innerHTML="";
			
			grefenddate = temp[3];
			greffromdate = temp[4];
			
		 	var type = document.createElement("label");
		 	
		 	var episode = "";
		 	
		 	
		 	if(editAppointId==0){
		 	episode = parseInt(temp[1]) + 1;
		 	
		 	if(parseInt(episode)<=parseInt(temp[2])){
		 		
		 		type.id = "sessionDetail1";
		 		
			   	type.innerHTML = "(Treatment Session "+episode+" of "+temp[2]+" )";
			   	document.getElementById('sessionDetail').appendChild(type);
		 	}
		 	else if(document.getElementById('treatmentEpisode').value == "0"){
		 		document.getElementById('sessionDetail').innerHTML="";
				document.getElementById('treatmentEpisodeError').innerHTML="";
		 	}
		 	else{
		 		
		 		type.id = "sessionDetail1";
			   	type.innerHTML = "All authorised consumed,<a href='#' onclick='addTreatmentEpisode()'> create new</a>";
			   	document.getElementById('sessionDetail').appendChild(type);
		 		
		 	}
		 	
		 }
		 	else if(editAppointId!=0 && treatEpisodeId == editTreatmentEpisode){
		 		
		 		 episode = parseInt(temp[1]);
			 	 if(parseInt(episode)<=parseInt(temp[2])){
			 		
			 		type.id = "sessionDetail1";
			 		
				   	type.innerHTML = "(Treatment Session "+episode+" of "+temp[2]+" )";
				   	document.getElementById('sessionDetail').appendChild(type);
			 	}
			 	else if(document.getElementById('treatmentEpisode').value == "0"){
			 		
			 		document.getElementById('sessionDetail').innerHTML="";
					document.getElementById('treatmentEpisodeError').innerHTML="";
			 	}
			 	else{
			 		
			 		type.id = "sessionDetail1";
			 		type.innerHTML = "All authorised consumed,<a href='#' onclick='addTreatmentEpisode()'> create new</a>";
				   	document.getElementById('sessionDetail').appendChild(type);
			 		
			 	}
			 	
			 	 
			 	 
			 	 if(parseInt(episode)<=parseInt(temp[2])){
			 		
			 		type.id = "sessionDetail1";
			 		
				   	type.innerHTML = "(Treatment Session "+episode+" of "+temp[2]+" )";
				   	document.getElementById('sessionDetail').appendChild(type);
			 	}
			 	else{
			 		
			 		type.id = "sessionDetail1";
			 		type.innerHTML = "All authorised consumed,<a href='#' onclick='addTreatmentEpisode()'> create new</a>";
				   	document.getElementById('sessionDetail').appendChild(type);
			 		
			 	}
			 	if(episode == 0 || episode == '0'){
			 		episode = parseInt(temp[1]) + 1;
			 		type.id = "sessionDetail1";
			 		
				   	type.innerHTML = "(Treatment Session "+episode+" of "+temp[2]+" )";
				   	document.getElementById('sessionDetail').appendChild(type);
			 	}
		 		
		 	}
		 	else if(editAppointId!=0 && treatEpisodeId != editTreatmentEpisode){
		 		
		 	
			 	episode = parseInt(temp[1]) + 1;
			 	if(parseInt(episode)<=parseInt(temp[2])){
			 		
			 		type.id = "sessionDetail1";
			 		
				   	type.innerHTML = "(Treatment Session "+episode+" of "+temp[2]+" )";
				   	document.getElementById('sessionDetail').appendChild(type);
			 	}
			 	else if(document.getElementById('treatmentEpisode').value == "0"){
			 		document.getElementById('sessionDetail').innerHTML="";
					document.getElementById('treatmentEpisodeError').innerHTML="";
			 	}
			 	else{
			 		
			 		type.id = "sessionDetail1";
			 		type.innerHTML = "All authorised consumed,<a href='#' onclick='addTreatmentEpisode()'> create new</a>";
				   	document.getElementById('sessionDetail').appendChild(type);
			 		
			 	}
			 	
			 	if(document.getElementById('treatmentEpisode').value != "0" && editTreatmentEpisode != "0" ){
			 		//updatePreviousTreatmentSession(editTreatmentEpisode);
			 	}
			 	else if(editTreatmentEpisode != "0"){
			 		//updatePreviousTreatmentSession(editTreatmentEpisode);
			 	}
			 	 
			 	 
			 }
		 	
		 	

		}
	}
}

function updatePreviousTreatmentSession(treatEpisodeId){
	
	var url = "updatePreviousSessionsTreatmentEpisode?id="+treatEpisodeId+"";

	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = updatePreviousTreatmentSessionRequest;
    req.open("GET", url, true); 
              
    req.send(null);


    return true;
}



function updatePreviousTreatmentSessionRequest(){
if (req.readyState == 4) {
if (req.status == 200) {




}
}

}

function checkMobileValidation1(mob){
	
	
	var url = "checkMobileExistClient?mob="+mob+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkMobileValidation1Request;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkMobileValidation1Request(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
					chkmob = 1;
					
				}
				
				 if(false)
			     {	
					 document.getElementById('mobNoError').innerHTML="";
					 var mob = document.createElement("label");
					 mob.innerHTML = "Mobile No already Exist Please try another";
				     document.getElementById('mobNoError').appendChild(mob);
				     chk = 1;
				     chkmob = 1;
				     
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('mobNoError').innerHTML="";
			    	
			    	 chk = 0;
			    	 chkmob = 0;
			    	
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
	}

function checkEmailValidation1(email){
	
	
	var url = "checkEmailExistClient?email="+email+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkEmailValidation1Request;
    req.open("GET", url, true); 
              
    req.send(null);
}
function checkEmailValidation1Request(){
	if (req.readyState == 4) {
		var chk=0;
			if (req.status == 200) {
				//alert(req.responseText);
				var exist = req.responseText;
				if(exist == 'false')
				{
					
					chk = 1;
					chkemail = 1;
					
				}
				
				 if(chk == 1)
			     {	
					
					 document.getElementById('emailError').innerHTML="";
					 var email = document.createElement("label");
					 email.innerHTML = "Email Id already Exist Please try another";
				     document.getElementById('emailError').appendChild(email);
				     chk = 1;
				     chkemail = 1;
				     
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('emailError').innerHTML="";
			    	
			    	 chk = 0;
			    	 chkemail = 0;
			    	 return true;
			     }
				
				
	    		
	         
	         }
		}
}
function openCancelApmtPopup(){
	$('#recordpaymentpopup').modal( "hide" );
	$('#modifyPopup').modal( "hide" );
	$('#cancelApmtNoteDiv').modal( "show" );
	
}	

function selectSchedulerTask(id){
	 var url ="gettaskdetailsNotAvailableSlot?subtask="+id+"";
	




	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = selectTaskRequest;
	    req.open("GET", url, true); 
	    req.send(null);  
}
function selectTaskRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	  
			   document.getElementById("tdsubtask").innerHTML=req.responseText;
			   $("#subtask").trigger("chosen:updated");
			   $(".chosen").chosen({allow_single_deselect: true});
		}
	}
}


function selectSubTask(id){
	var url = "getsubtaskdetailsNotAvailableSlot?notes="+id+"";
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = selectSubTaskRequest;
	    req.open("GET", url, true); 
	    req.send(null); 
}
function selectSubTaskRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {	 
			var data = document.getElementById("blocknotes").value;
			var str = req.responseText;
			document.getElementById("blocknotes").value= data +' '+str;
			 
		}
	}
}

/*function deletetask(){
	$('#deletemodel').modal( "show" );
}
function deletetask1(){
	var parentid = document.getElementById("parent_id").value;
	var delete_reason = document.getElementById("delete_reason").value;
	
	var url="deleteindentProduct?parentid="+parentid+"&delete_reason="+delete_reason+"";  	  
	  if (window.XMLHttpRequest) {
		  req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		  isIE = true;
		  req = new ActiveXObject("Microsoft.XMLHTTP");
	  }   
	  req.onreadystatechange = deletetask1Request;
	  req.open("GET", url, true); 
	  req.send(null);  
	 }
function deletetask1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}*/
/*adarsh changes*/

function deletetask(){
	 $('#deletemodel').modal( "show" );
	}
	function deletetask1(){
	 var parentid = document.getElementById("parent_id").value;
	 var delete_reason = document.getElementById("delete_reason").value;
	 
	 var url="deleteTaskNotAvailableSlot?parentid="+parentid+"&delete_reason="+delete_reason+"";     
	   if (window.XMLHttpRequest) {
	    req = new XMLHttpRequest();
	   }
	   else if (window.ActiveXObject) {
	    isIE = true;
	    req = new ActiveXObject("Microsoft.XMLHTTP");
	   }   
	   req.onreadystatechange = deletetask1Request;
	   req.open("GET", url, true); 
	   req.send(null);  
	  }
	function deletetask1Request(){
	 if (req.readyState == 4) {
	   if (req.status == 200) {
	    window.location.reload();  
	          }
	  }  
	 }
	
	function openCancelApmtPopupNEW(id){
		editAppointId = id;
		$('#recordpaymentpopup').modal( "hide" );
		$('#modifyPopup').modal( "hide" );
		$('#cancelApmtNoteDiv').modal( "show" );
		
	}	
	
	function resetfiels(){
		document.getElementById("town").className="";
		 document.getElementById("town").value="0";
		 $("#town").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});

		document.getElementById("state").className="";
		document.getElementById("state").value="0";
		$("#state").trigger("chosen:updated");
		$(".chosen-select").chosen({allow_single_deselect: true});
		
		document.getElementById("title").value="0";
		
		document.getElementById("gender").value="0";
	}