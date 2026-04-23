	
function editstatus(id) {
    var url ="editstatusMrd?ipdid="+id+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = editstatusRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function editstatusRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
						window.location = 'Mrd';
		}
	}
 }
 
 
 function saveinfo(id){
 	var remark = document.getElementById("remark"+id).value;
	var shelf_no = document.getElementById("shelf_no"+id).value;
	var place = document.getElementById("place"+id).value;
  		if(shelf_no=='' && remark=='' && place==''){
      			 jAlert('error', "please enter valid information!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 
					
			 return false;	
      	}
      	else{
      		return true;
      	}
 }
 
 function showMrdPopUp(){
	
	var url = "showAllPatientListMrd";

	
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
				$('#clientSearchDiv').modal( "show" );	
	         }
		}
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
	document.getElementById("refError").innerHTML = "";	
	document.getElementById("fnameError").innerHTML = "";	
	document.getElementById("lnameError").innerHTML = "";	
	document.getElementById("dobError").innerHTML = "";	
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
				tpname.innerHTML = "Please select typeName";
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
	 }
	
	if(firstname == ""){
		var firstName = document.createElement("label");
		firstName.innerHTML = "Please Enter First Name";
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
	    document.getElementById('lnameError').appendChild(lastName);
	    chk=1;
	}
	var lastName = document.getElementById('lastName').value;
//	if(lastName.match(/\s/g)){
//		var lastName = document.createElement("label");
//		lastName.innerHTML = "Space Not Allow";
//	    document.getElementById('lnameError').appendChild(lastName);
//	    chk=1;
//	    
//	}
//	if(lastName.match(/[^a-zA-Z]+/)){
//		var firstName = document.createElement("label");
//		lastName.innerHTML = "Special Character or Number Not Allow";
//	    document.getElementById('fnameError').appendChild(firstName);
//	    chk=1;
//	}
//	if(middleName.match(/[^a-zA-Z]+/)){
//		var firstName = document.createElement("label");
//		middleName.innerHTML = "Special Character or Number Not Allow";
//	    document.getElementById('fnameError').appendChild(firstName);
//	    chk=1;
//	}
	/*if(dob == ""){
		var dob = document.createElement("label");
		dob.innerHTML = "Please Enter DOB";
	    document.getElementById('dobError').appendChild(dob);
	    chk=1;
	}*/
	if(age == ""){
		var age = document.createElement("label");
		age.innerHTML = "Please Enter Age";
	    document.getElementById('ageError').appendChild(age);
	    chk=1;
	}
	if(gender=="0"){
	 		var gen = document.createElement("label");
			gen.innerHTML = "Please Select Gender";
	   		 document.getElementById('genderError').appendChild(gen);
	    	chk=1;
	}
	
	if(address == ""){
		var address = document.createElement("label");
		address.innerHTML = "Please Enter Address";
	    document.getElementById('addressError').appendChild(address);
	    chk=1;
	}
	if(town == "" || town=="0"){
		var town = document.createElement("label");
		town.innerHTML = "Please Enter Town";
	    document.getElementById('townError').appendChild(town);
	    chk=1;
	}
	
	if(mobNo == "") {
		
	}
	else if(!regEx.test(mobNo)) {
		var mobNo = document.createElement("label");
		mobNo.innerHTML = "Please Enter Valid No.";
	    document.getElementById('mobNoError1').appendChild(mobNo);
	    chk=1;
	}else if(mobNo.length!=10) {
		var mobNo = document.createElement("label");
		mobNo.innerHTML = "Please Enter Valid No.";
	    document.getElementById('mobNoError1').appendChild(mobNo);
	    chk=1;
	}
	if(!adharnoregEx.test(adhno) && adhno !="") {
			var adhno = document.createElement("label");
			adhno.innerHTML = "Please Enter Valid Adhar No.";
		    document.getElementById('adhnoError').appendChild(adhno);
		    chk=1;
		}
	
	
	if(email == "") {
		
	}
	else if(!emailregEx.test(email)){
		var email = document.createElement("label");
		email.innerHTML = "Please Enter Valid No.";
	    document.getElementById('emailError1').appendChild(email);
	    chk=1;
	}
	 if(whopay == "Third Party"){
		 if(type == 0){		
				var type1 = document.createElement("label");
				type1.innerHTML = "Please select type";
			    document.getElementById('tpError').appendChild(type1);
			    chk=1;
			}
			if(typeName == 0){		
				var tpname = document.createElement("label");
				tpname.innerHTML = "Please select typeName";
			    document.getElementById('tpnameError').appendChild(tpname);
			    chk=1;
			}
	 }
		 
	
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
			 
			 var url = "savePatientClient?title="+title+"&firstName="+firstname+"&lastName="+lastName+"&middleName="+middleName+"&gender="+gender+"&dob="+dob+"&address="+address+"&town="+town+"&country="+country+"&postCode="+postCode+"&mobNo="+mobNo+"&email="+email+"&type="+type+"&company="+company+"&policyNo="+policyNo+"&expiryDate="+expiryDate+"&whopay="+whopay+"&gpname="+gpname+"&tratmentType="+tratmentType+"&reference="+reference+"&secondLineaddress="+secondLineaddress+"&doctorsurgery="+doctorsurgery+"&policyExcess="+policyExcess+"&occupation="+occupation+"&state="+state+"&age="+age+"&relativename="+relativename+"&relativeno="+relativeno+"&hospitalborn="+hospitalborn+"&compname="+compname+"&neisno="+neisno+"&designationbytp="+designationbytp+"&unitstation="+unitstation+"&claimbytp="+claimbytp+"&relationvbytp="+relationvbytp+"&colliery="+colliery+"&areabytp="+areabytp+"&policyholder="+policyholder+"&maritalsts="+maritalsts+" ";

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
	
	
 }
	function savePatientRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				//document.getElementById("allPatient").innerHTML = req.responseText;
				$('#addPatientDiv').modal( "hide" );
				resetAddClientFileds();
				$('#dashboardloaderPopup').modal( "hide" );
				showMrdPopUp();
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



function searchPatient(){
	var searchText = document.getElementById("searchText1").value;
	var url = "searchParticularPatientMrd?searchText="+searchText+"";

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

function selectBedFromWardid(id) {
   var url ="selectBedFromWardidMrd?id="+id+"";
   if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = editstatusRequest;
    req.open("GET", url, true); 
    req.send(null);
 }
 function editstatusRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
						document.getElementById("beddiv").innerHTML = req.responseText;
						  $("#bedid").trigger("chosen:updated");
    					$(".chosen").chosen({allow_single_deselect: true});
		}
	}
 }
 
 function getspeciality(d) {

     var url = "getspecialityMrd?doctorid="+d+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getspecialityRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}


function getspecialityRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("specialitydiv").innerHTML = req.responseText;
				  $("#department").trigger("chosen:updated");
    			$(".chosen").chosen({allow_single_deselect: true});
	         }
		}
	}
	
function showMrdHide() {
	if(document.getElementById("new_whopay").value == 'Third Party') {
	      document.getElementById("new_hidden_html").style.display = ""; // This line makes the DIV visible
	} 
	else {            
		document.getElementById("new_hidden_html").style.display = "none"; // This line hides the DIV
	}
	if(document.getElementById("new_whopay").selectedIndex == 0) {
	    //document.getElementById("type").disabled = false;
	    //document.getElementById("typeName").disabled = false;
	    document.getElementById("new_policyNo").disabled = true;
	    document.getElementById("new_expiryDate").disabled = true;
	    document.getElementById("new_policyExcess").disabled = true;
	 }
	          
} 
function setMrdTPName(id){
		//alert('hi');
	var url = "setTypeNameClntDropDownMrd?id="+id+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	req.onreadystatechange = setMrdTPNameRequest;
	req.open("GET", url, true); 
	req.send(null);
	
	if(document.getElementById("new_type").value == 2 && document.getElementById("new_whopay").selectedIndex == 1) 
		{			
			//document.getElementById("type").disabled = false;
			//document.getElementById("typeName").disabled = false;
			document.getElementById("new_policyNo").disabled = false;
			document.getElementById("new_expiryDate").disabled = false;
			document.getElementById("new_policyExcess").disabled = false;
		}
	else{
		//document.getElementById("type").disabled = true;
		//document.getElementById("typeName").disabled = true;
		document.getElementById("new_policyNo").disabled = true;
		document.getElementById("new_expiryDate").disabled = true;
		document.getElementById("new_policyExcess").disabled = true;
	}
}
	
function setMrdTPNameRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//alert('hello');
	    	document.getElementById("new_typeNamediv").innerHTML = req.responseText;
	    	$("#new_typeName").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
		}
	}
}
	
function setPatientInforamtion(department){
	var depart = department;
	var clientid = document.getElementById("patient_id").value;
	var url = "setPatientInforamtionMrd?clientid="+clientid+"&department="+department+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = setPatientInforamtionRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	}
	
	function setPatientInforamtionRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				//window.reload = "Mrd";
				$('#newregister').modal( "hide" );	
			 	window.location = 'Mrd';
			 }
		}
	}
	
function saveToMrd(){
	var patient_id = document.getElementById("patient_id").value;
	var patient_ipdid = document.getElementById("patient_ipdid").value;
	var patientFrom = document.getElementById("patientFrom").value;
	var admission_date = document.getElementById("admission_date").value;
	var discharge_date = document.getElementById("discharge_date").value;
	var wardid = document.getElementById("wardid").value;
	var bedid = document.getElementById("bedid").value;
	var practitioner_name = document.getElementById("practitioner_name").value;
	var speciality = document.getElementById("speciality").value;
	var new_shelf = document.getElementById("new_shelf").value;
	var new_place = document.getElementById("new_place").value;
	var new_remark = document.getElementById("new_remark").value;
	var newmlc = document.getElementById("new_mlc").checked;
	
	var new_whopay = document.getElementById("new_whopay").value;
	var new_type = document.getElementById("new_type").value;
	var new_typeName = document.getElementById("new_typeName").value;
	var new_policyNo = document.getElementById("new_policyNo").value;
	var new_expiryDate = document.getElementById("new_expiryDate").value;
	var new_policyExcess = document.getElementById("new_policyExcess").value;
	var new_mlcno = document.getElementById("new_mlcno").value;
	document.getElementById("patientFromError").innerHTML = "";
	document.getElementById("admission_dateError").innerHTML = "";
	document.getElementById("discharge_dateError").innerHTML = "";
	document.getElementById("practitioner_nameError").innerHTML = "";
	document.getElementById("new_whopayError").innerHTML = "";
	document.getElementById("new_typeNameError").innerHTML = "";
	document.getElementById("new_typeError").innerHTML = "";
	var chk =0;
	
	if(patientFrom == "0"){
		var patientfrom = document.createElement("label");
		patientfrom.innerHTML = "Please Select Patient From which Department";
	    document.getElementById('patientFromError').appendChild(patientfrom);
	    chk=1;
	}
	if(patientFrom=='IPD'){
		if(admission_date == ""){
			var Admission_Date = document.createElement("label");
			Admission_Date.innerHTML = "Please Enter Admission Date";
		    document.getElementById('admission_dateError').appendChild(Admission_Date);
		    chk=1;
		}
		
		if(discharge_date == ""){
			var Discharge_date = document.createElement("label");
			Discharge_date.innerHTML = "Please Enter Discharge Date";
		    document.getElementById('discharge_dateError').appendChild(Discharge_date);
		    chk=1;
		}
	}
	
	
	if(practitioner_name == "0"){
		var Practitioner_name = document.createElement("label");
		Practitioner_name.innerHTML = "Please Select Practitioner Date";
	    document.getElementById('practitioner_nameError').appendChild(Practitioner_name);
	    chk=1;
	}
	
	/*if(new_whopay == "Third Party"){
		 if(new_type == 0){		
				var type1 = document.createElement("label");
				type1.innerHTML = "Please select type";
			    document.getElementById('new_typeError').appendChild(type1);
			    chk=1;
			}
			if(new_typeName == 0){		
				var tpname = document.createElement("label");
				tpname.innerHTML = "Please select typeName";
			    document.getElementById('new_typeNameError').appendChild(tpname);
			    chk=1;
			}
	 }*/
	 if(!chk==1){
		var url ="savePatientDetailsMrd?patient_id="+patient_id+"&patient_ipdid="+patient_ipdid+"&patientFrom="+patientFrom+"&admission_date="+admission_date+"&discharge_date="+discharge_date+"&wardid="+wardid+"&bedid="+bedid+"&practitioner_name="+practitioner_name+"&speciality="+speciality+"&new_shelf="+new_shelf+"&new_place="+new_place+"&new_remark="+new_remark+"&newmlc="+newmlc+"&new_whopay="+new_whopay+"&new_type="+new_type+"&new_typeName="+new_typeName+"&new_policyNo="+new_policyNo+"&new_expiryDate="+new_expiryDate+"&new_policyExcess="+new_policyExcess+"&new_mlcno="+new_mlcno+"";
		document.getElementById("saveMrd").style.visibility="hidden";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = saveToMrdRequest;
	    req.open("GET", url, true); 
	    req.send(null);		
	}
}

function saveToMrdRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				var data = req.responseText;
				if(data!='0'){
					
					jAlert('success', 'Patient Stored!', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					resetAddMrdFileds();
					$('#newregister').modal( "hide" );
					//window.reload = "Mrd";
					window.location = 'Mrd';
			}
		}
	}
}

 function resetAddMrdFileds(){
	document.getElementById('patientFrom').value ="0";
	document.getElementById('admission_date').value="";
	document.getElementById('discharge_date').value = "";
	document.getElementById('wardid').value = "0";
	document.getElementById('bedid').value = "0";
	document.getElementById('practitioner_name').value = "0";
	document.getElementById('speciality').value = "0";
	document.getElementById('new_shelf').value = "";
	document.getElementById('new_place').value = "";
	document.getElementById('new_remark').value = "";
	document.getElementById('new_mlc').value = "";
	document.getElementById('new_whopay').value= "0";
	document.getElementById('new_type').value= "0";
	document.getElementById('new_typeName').value = "0";
	document.getElementById('new_policyNo').value = "";
	document.getElementById('new_expiryDate').value = "";
	document.getElementById('new_policyExcess').value = "";
}



function editMrdDetails(id){
	var url = "getPatientForEditMrd?id="+id+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = setPatientInforamtionRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function setPatientInforamtionRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				var str = req.responseText;
				var data = str.split("~");
				document.getElementById("editpatientfrom").innerHTML = data[0];
				document.getElementById("editadmissiondatediv").innerHTML= data[1];
				document.getElementById("editdischargedatediv").innerHTML= data[2];
				document.getElementById("editwarddiv").innerHTML= data[3];
				document.getElementById("editbeddiv").innerHTML= data[4];
				document.getElementById("editpractinordiv").innerHTML= data[5];
				document.getElementById("editspecialitydiv").innerHTML= data[6];
				document.getElementById("editshelfdiv").innerHTML= data[7];
				document.getElementById("editplacediv").innerHTML= data[8];
				document.getElementById("editremarkdiv").innerHTML= data[9];
				document.getElementById("editmlcdiv").innerHTML= data[10];
				document.getElementById("editpatient_name").innerHTML= data[11];
				document.getElementById("editpatient_city").innerHTML= data[12];
				document.getElementById("editpatient_gender").innerHTML= data[13];
				document.getElementById("editmrd_id").value= data[14];
				document.getElementById("editpatient_ipdid").value= data[15];
				document.getElementById("editpatient_id").value= data[16];
				
				document.getElementById("editpatient_phone").innerHTML= data[17];
				document.getElementById("editpatient_address").innerHTML= data[18];
				document.getElementById("editmlcnodiv").innerHTML= data[19];
				document.getElementById("editpatient_uid").innerHTML= data[20];
				$('#editmrdregistered').modal( "show" );	
			 }
		}
	}
	
function updateInformation(){
	var id = document.getElementById("editmrd_id").value;
	var shelf_no = document.getElementById("editnew_shelf").value;
	var place = document.getElementById("editnew_place").value;
	var remark = document.getElementById("edit_new_remark").value;
	var mlc = document.getElementById("editnew_mlc").checked;
	var mlcno = document.getElementById("editnew_mlcno").value;
	var url = "updateInformationMrd?id="+id+"&shelf_no="+shelf_no+"&place="+place+"&remark="+remark+"&mlc="+mlc+"&mlcno="+mlcno+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updateInformationRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function updateInformationRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				var str = req.responseText;
				$('#editmrdregistered').modal( "hide" );
				window.location = 'Mrd';	
			 }
		}
	}

function setPatientForMrd(id,gender,city) {
	var name=document.getElementById("firstnameid"+id).value;
	/*document.getElementById("patient_name").innerHTML = name;
	document.getElementById("patient_city").innerHTML = city;
	document.getElementById("patient_gender").innerHTML = gender;
	document.getElementById("patient_id").value = id;
	$('#newregister').modal( "show" );
    $('#clientSearchDiv').modal( "hide" );*/
	getAllPatientInfor(id);
}



function getAllPatientInfor(clientid){
	
	var url = "getallpatientinfoMrd?id="+clientid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = getAllPatientInforRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function getAllPatientInforRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				var str = req.responseText;
				var data = str.split("~");
				document.getElementById("patient_id").value = data[0];
				document.getElementById("patient_ipdid").value = data[1];
				document.getElementById("patient_name").innerHTML = data[2];
				document.getElementById("patientFrom").value = data[3];
				document.getElementById("patient_gender").innerHTML = data[4];
				document.getElementById("patient_phone").innerHTML = data[5];
				document.getElementById("patient_city").innerHTML = data[6];
				document.getElementById("patient_address").innerHTML = data[7];
				document.getElementById("admission_date").value = data[8];
				document.getElementById("discharge_date").value = data[9];
				document.getElementById("warddiv").innerHTML = data[10];
				document.getElementById("beddiv").innerHTML = data[11];
				document.getElementById("practinordiv").innerHTML = data[12];
				document.getElementById("specialitydiv").innerHTML = data[13];
				document.getElementById("patientypediv").innerHTML = data[14];
				document.getElementById("patient_uid").innerHTML = data[15];
				$('#newregister').modal( "show" );
			    $('#clientSearchDiv').modal( "hide" );
			}
		}
	}

function selectMLCNOO(val) {
	if(val==false){
		document.getElementById("smlcnodiv").className = '';
		document.getElementById("smlcnodiv").className = 'col-lg-3 col-md-3 col-xs-3 col-sm-3 hidden';
	}else{
		document.getElementById("smlcnodiv").className = '';
		document.getElementById("smlcnodiv").className = 'col-lg-3 col-md-3 col-xs-3 col-sm-3';
	}
}

function shiftMrdDetails(){
	 $('#saveShiftMrdDetails').modal( "show" );
	}
function shiftMrdDetails1(mrdid){
var url = "getPatientForShiftMrd?mrdid="+mrdid+"";

if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = shiftMrdDetailsRequest;
    req.open("GET", url, true); 
    req.send(null);
}
function shiftMrdDetailsRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			var data = str.split("~");
			document.getElementById("shiftpatientuhid").innerHTML = data[0];
			document.getElementById("shiftpatientname").innerHTML = data[1];
			document.getElementById("shiftpatientgender").innerHTML = data[2];
			document.getElementById("shiftpatientcity").innerHTML = data[3];
			document.getElementById("shiftmrdtbody").innerHTML = data[4];
		    document.getElementById("shiftpatient_id").value = data[5];
		    document.getElementById("shiftpatient_ipdid").value = data[6];
		    document.getElementById("shiftpatienmrd_id").value = data[7];
            document.getElementById("shiftstatus").value = data[8];
			$('#saveShiftMrdDetails').modal( "show" );	
		 }
	}
}

function addmrdshiftrow(){
	
	
	var id = document.getElementById("shiftpatient_id").value;
	var ipdid = document.getElementById("shiftpatient_ipdid").value;
	var mrdid = document.getElementById("shiftpatienmrd_id").value;
	var shiftstatus = document.getElementById("shiftstatus").value;
	
	if(shiftstatus=='1'){
		jAlert('error', "Allready shifted!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
	var url = "addnewrowMrd?mrdid="+mrdid+"&id="+id+"&ipdid="+ipdid+"&shiftstatus="+shiftstatus+"";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addmrdshiftRowRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
}

function addmrdshiftRowRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById("shiftmrdtbody").innerHTML=req.responseText;	
				
				$("#shiftmrdgivendatedetails").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});
				/*$("#shiftmrdreceiveddatedetails").datepicker({

					dateFormat : 'dd-mm-yy',
					yearRange: yearrange,
					minDate : '30-12-1880',
					changeMonth : true,
					changeYear : true

				});*/
	         }
		}	 
	

}
function saveshiftToMrd(){
	/*var patient_id = document.getElementById("patient_id").value;
	var patient_ipdid = document.getElementById("patient_ipdid").value;
	var patientFrom = document.getElementById("patientFrom").value;
	var admission_date = document.getElementById("admission_date").value;
	var discharge_date = document.getElementById("discharge_date").value;
	var wardid = document.getElementById("wardid").value;*/
	var shiftpatientid = document.getElementById("shiftpatient_id").value;
	var shiftpatientipdid = document.getElementById("shiftpatient_ipdid").value;
	var shiftpatienmrdid = document.getElementById("shiftpatienmrd_id").value;
	var shiftmrdgiventodetails = document.getElementById("shiftmrdgiventodetails").value;
	var shiftmrdgivendatedetails = document.getElementById("shiftmrdgivendatedetails").value;
	var shiftmrdreceivedfromdetails = document.getElementById("shiftmrdreceivedfromdetails").value;
	var shiftmrdreceiveddatedetails = document.getElementById("shiftmrdreceiveddatedetails").value;
	var shiftmrdremark = document.getElementById("shiftmrdremark").value;
	

		var url ="saveshiftPatientDetailsMrd?shiftpatient_id="+shiftpatientid+"&shiftpatientipdid="+shiftpatientipdid+"&shiftpatienmrd_id="+shiftpatienmrdid+"&shiftmrdgiventodetails="+shiftmrdgiventodetails+"&shiftmrdgivendatedetails="+shiftmrdgivendatedetails+"&shiftmrdreceivedfromdetails="+shiftmrdreceivedfromdetails+"&shiftmrdreceiveddatedetails="+shiftmrdreceiveddatedetails+"&shiftmrdremark="+shiftmrdremark+"";
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		req.onreadystatechange = saveshiftToMrdRequest;
	    req.open("GET", url, true); 
	    req.send(null);		
	}


function saveshiftToMrdRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				var data = req.responseText;
				if(data!='0'){
					jAlert('success', 'Shifting Record saved successfully!', 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					resetAddMrdFileds();
					$('#newregister').modal( "hide" );
				
					window.location = 'Mrd';
					
			}
		}
	}
}
var temp = 0;
function editshiftpatientMrd(i,id){
	var url ="editshiftpatientMrd?id="+id+"&count="+i+"";
	temp=i;
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = editshiftpatientRequest;
    req.open("GET", url, true); 
    req.send(null);
}
function editshiftpatientRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('shifttrid'+temp).innerHTML = req.responseText;
			$('#shiftmrdgivendatedetails'+temp).datepicker({

				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true

			});
			$('#shiftmrdreceiveddatedetails'+temp).datepicker({

				dateFormat : 'dd-mm-yy',
				yearRange: yearrange,
				minDate : '30-12-1880',
				changeMonth : true,
				changeYear : true

			});
		 }
	}

}

function updateshiftToMrd(i,id){
	var shiftpatientid = document.getElementById('shiftpatient_id').value;
	var shiftpatientipdid = document.getElementById('shiftpatient_ipdid').value;
	var shiftpatienmrdid = document.getElementById('shiftpatienmrd_id').value;
	var shiftmrdgiventodetails = document.getElementById('shiftmrdgiventodetails'+i).value;
	var shiftmrdgivendatedetails = document.getElementById('shiftmrdgivendatedetails'+i).value;
	var shiftmrdreceivedfromdetails = document.getElementById('shiftmrdreceivedfromdetails'+i).value;
	var shiftmrdreceiveddatedetails = document.getElementById('shiftmrdreceiveddatedetails'+i).value;
	var shiftmrdremark = document.getElementById('shiftmrdremark'+i).value;
	

		var url ="updateshiftpatientdetailsMrd?id="+id+"&shiftpatient_id="+shiftpatientid+"&shiftpatientipdid="+shiftpatient_ipdid+"&shiftpatienmrd_id="+shiftpatienmrdid+"&shiftmrdgiventodetails="+shiftmrdgiventodetails+"&shiftmrdgivendatedetails="+shiftmrdgivendatedetails+"&shiftmrdreceivedfromdetails="+shiftmrdreceivedfromdetails+"&shiftmrdreceiveddatedetails="+shiftmrdreceiveddatedetails+"&shiftmrdremark="+shiftmrdremark+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = updateInformationRequest;
	    req.open("GET", url, true); 
	    req.send(null);
}
function updateInformationRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				var str = req.responseText;
			
				window.location = 'Mrd';	
			 }
		}
}

 function deleteMrd(val){
	document.getElementById("cancelmrdid").value = val;
	$('#deletemodel').modal( "show" );
}
 function deleteMrdDetails(){
	 var id = document.getElementById("cancelmrdid").value;
		var delete_reason = document.getElementById("delete_reason").value;
		var adarsh = "deletedataMrd?id="+id+"&delete_reason="+delete_reason+"";
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = deleteMrdDetailsRequest;
		  req.open("GET", adarsh, true); 
		  req.send(null);  
   }
	function deleteMrdDetailsRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					window.location.reload();	 
		         }
			}	 
 } 
	function redircttoNewEmr(clientid,practitionerid,conditionid){
		
		
		var t = 'formtarget';

		document.getElementById('clientnameApmt').value = clientid;
		document.getElementById('diaryUserApmt').value = practitionerid;
		document.getElementById('conditionsApmt').value = conditionid;
		document.getElementById('hdnaction').value = 'emr';
		document.getElementById('apmtId').value = 0;

		/* document.getElementById('getPatientRecord').submit(); */

		var left = (screen.width / 2) - (700 / 2);
		var top = (screen.height / 2) - (550 / 2);
		
		var oldwindow = window.open("", t,
				"status = 1,height = "+openpopupheight +",width = "+openpopupwidth +",resizable = 1,scrollbars=yes,left=" + 0
						+ ",top=" + 50);
		
		oldwindow.focus();

		document.getElementById('getPatientRecord').submit();
		
		
	}
	
	

    function indsmstoclient(val){
    	document.getElementById("clientid").value=val;
    	$('#smsmodel').modal( "show" );
    	
    	
    }
    
 
 
 
 
 function groupsms(){
 	var val=document.getElementById("hideselect").value;
 	var msg=document.getElementById("groupsmstosend").value;
 	if(msg==''){
 		 jAlert('error', 'SMS will not empty!', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
 	}else{
 		
 		document.getElementById('groupsmsid').style.visibility='hidden';
			var url="sendgroupsmsInitialDischarge?val="+val+"&sms="+msg+"";
			if (window.XMLHttpRequest) {
				  req = new XMLHttpRequest();
			 }
			 else if (window.ActiveXObject) {
				  isIE = true;
				  req = new ActiveXObject("Microsoft.XMLHTTP");
			 }   
			               
		    req.onreadystatechange = groupsmsRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
 	}
}

function groupsmsRequest(){
	 
	 if (req.readyState == 4) {
	  if (req.status == 200) {
		  window.location.reload();
	   }
	 }
}
function saveSmsTemplates(){
 	var msg=document.getElementById("groupsmstosend").value;
 	var tempname=document.getElementById("smstemplate").value;
 	
 	if(msg==''){
 		 jAlert('error', 'SMS will not empty!', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
 	}else{
 		
			var url="savesmstemplateInitialDischarge?tempname="+tempname+"&sms="+msg+"";
			if (window.XMLHttpRequest) {
				  req = new XMLHttpRequest();
			 }
			 else if (window.ActiveXObject) {
				  isIE = true;
				  req = new ActiveXObject("Microsoft.XMLHTTP");
			 }   
			               
		    req.onreadystatechange = saveSmsTemplatesRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
 	}
}

function saveSmsTemplatesRequest(){
	 
	 if (req.readyState == 4) {
	  if (req.status == 200) {
		  refresharraylist();
	   }
	 }
}
function getsmstemplate(val) {
	var url="getsmstemplateInitialDischarge?tempid="+val+"";
	if (window.XMLHttpRequest) {
		  req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		  isIE = true;
		  req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	               
    req.onreadystatechange = getsmstemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getsmstemplateRequest(){
	 
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
		
			document.getElementById("groupsmstosend").value=str;	
		 }
	}
}

function refresharraylist(){
	var url="getsmstemplatelistInitialDischarge";
	if (window.XMLHttpRequest) {
		  req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		  isIE = true;
		  req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	               
  req.onreadystatechange = refresharraylistRequest;
  req.open("GET", url, true); 
            
  req.send(null);
}
function refresharraylistRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			document.getElementById("smstemplist").innerHTML=str;
		}
	}
}


function getsmstypelist(val){
	var url="getsmstypelistMaster?id="+val+"";
	if (window.XMLHttpRequest) {
		  req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		  isIE = true;
		  req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
	               
  req.onreadystatechange = getsmstypelistRequest;
  req.open("GET", url, true); 
            
  req.send(null);
}
function getsmstypelistRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			document.getElementById("smstypelist").innerHTML=str;
		}
	}
}
function validsave(){
	var itype=document.getElementById("smsitypeid").value;
	var smstype=document.getElementById("smstypeid").value;
	var smstxt=document.getElementById("smstxt").value;
	if(itype==0){
		 jAlert('error', 'Select Invoice Type!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else if(smstype==0){
		 jAlert('error', 'Select SMS Type!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else if(smstxt==''){
		 jAlert('error', 'SMS Field is Empty!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}else{
		document.getElementById('savesmsfrm').submit();
	}
}

function checkalreadyexist(val) {
	var url = "checkalreadyexistsmsMaster?id="+val+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkalreadyexistRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
function checkalreadyexistRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var val=req.responseText;
			if(val==1){
				jAlert('error', 'SMS Type Already Added!', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				document.getElementById("smstypeid").value=0;
		}else{
			
		}	 
}
	}
}