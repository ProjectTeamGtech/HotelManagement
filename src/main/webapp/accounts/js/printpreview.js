function sendmail() {

	$("#sendEmailPopUp2").modal("show");
}

function sendPdfMail(id) {

	var cc = document.getElementById('ccEmail').value;
	var to = document.getElementById('thirdPartEmail').value;
	var subject = document.getElementById('subject').value;
	var notes = nicEditors.findEditor("emailBody").getContent();
	var clientid = document.getElementById('hiddenclientid').value;
	var type = "Invoice";

	var url = "emailCharges?to=" + to + "&cc=" + cc + "&subject=" + subject
			+ "&notes=" + notes + "&id=" + id + "&clientid=" + clientid
			+ "&type=" + type + " ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = sendPdfMailRequest;
	req.open("GET", url, true);

	req.send(null);
}

function sendPdfMailJson() {
	var cc = document.getElementById('ccEmail').value;
	var to = document.getElementById('thirdPartEmail').value;
	var subject = document.getElementById('subject').value;
	var notes = nicEditors.findEditor("emailBody").getContent();
	var clientid = document.getElementById('hiddenclientid').value;
	var type = "Invoice";

	var SCRIPT_REGEX = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi;

	var htmlContent = document.getElementById('ml').innerHTML;
	var c2 = document.getElementById('ml1').innerHTML;
	var c3 = document.getElementById('ml2').innerHTML;
	while (SCRIPT_REGEX.test(htmlContent)) {
		htmlContent = htmlContent.replace(SCRIPT_REGEX, "");
	}
	while (SCRIPT_REGEX.test(c2)) {
		c2 = c2.replace(SCRIPT_REGEX, "");
	}
	while (SCRIPT_REGEX.test(c3)) {
		c3 = c3.replace(SCRIPT_REGEX, "");
	}
	htmlContent = htmlContent + c2 + c3;
	var dataObj = {
		"cc" : "" + cc + "",
		"to" : "" + to + "",
		"subject" : "" + subject + "",
		"notes" : "" + notes + "",
		"clientid" : "" + clientid + "",
		"type" : "" + type + "",
		"htmlContent" : "" + htmlContent + "",
		"id" : "",
	};
	var data1 = JSON.stringify(dataObj);
	$.ajax({

		url : "emailCommonnew",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,
		// beforeSend: function () { LockScreen(); },
		success : function(data) {
			alert("Sent");
		},
		error : function(result) {
			console.log("error");
		}
	});

}

function sendPdfMailRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {

			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);

			jAlert('success', 'Email Send Sucessfully!!', 'Success Dialog');
			// tempAlert("Email Send Sucessfully", 5000);
			$('#sendEmailPopUp').dialog("close");
		}
	}

}

function showinvoicechargesByDate(billsummary, invoiceid, totalAmountx, seq) {
	var dataObj = {
		"billsummary" : "" + billsummary + "",
		"invoiceid" : "" + invoiceid + "",
		"totalAmountx" : "" + totalAmountx + "",
		"seq" : "" + seq + "",
	};
	var data1 = JSON.stringify(dataObj);
	$
			.ajax({

				url : "getinvoicechargeslistBookAppointmentAjax",
				data : data1,
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,
				success : function(data) {
					var condition = data.response1;
					document.getElementById("viewinvoicetbody").innerHTML = data.response1;
				},
				error : function(result) {
					jAlert('error', "Not Reloaded!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				}

			});
}

function showReferPatientPopUp() {
	/* var url = "showAllPatientListMrd"; */

	var url = "showReferPatientListBookAppointmentAjax";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = showReferPatientPopUpRequest;
	req.open("GET", url, true);

	req.send(null);

}
function showReferPatientPopUpRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("allPatient").innerHTML = req.responseText;
			$('#clientSearchDiv').modal("show");
		}
	}
}

function searchPatient() {
	var searchText = document.getElementById("searchText1").value;

	var url = "showReferPatientListBookAppointmentAjax?searchText="
			+ searchText + "";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = searchPatientRequest;
	req.open("GET", url, true);

	req.send(null);

}
function searchPatientRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {

			document.getElementById("allPatient").innerHTML = req.responseText;

		}
	}
}

function savePatient() {

	var mothername = '', fathername = '', birthplace = '';

	if (document.getElementById('mothername')) {
		mothername = document.getElementById('mothername').value;
		fathername = document.getElementById('fathername').value;
		birthplace = document.getElementById('birthplace').value;
	}

	var adhno = document.getElementById('adhno').value;
	var title = document.getElementById('title').value;
	var firstname = document.getElementById('firstName').value;
	var middleName = document.getElementById('middleName').value;

	var lastName = document.getElementById('lastName').value;
	var gender = document.getElementById('gender').value;
	var dob = document.getElementById('dob').value;
	var address = document.getElementById('address').value;
	var town = document.getElementById('town').value;
	var state = document.getElementById('state').value;
	// var county = document.getElementById('county').value;
	var country = document.getElementById('country').value;
	var postCode = document.getElementById('postCode').value;
	var mobNo = document.getElementById('mobNo').value;
	var email = document.getElementById('email').value;
	var reference = document.getElementById('reference').value;
	if (reference == 'Other') {
		reference = document.getElementById('otherRef').value;
	}
	var fulltime = '00:00:00';
	if (document.getElementById('hourls')) {
		var hour = document.getElementById('hourls').value;
		var minute = document.getElementById('minutels').value;
		fulltime = hour + ":" + minute + ":" + "00";
	}
	var age = document.getElementById("age").value;
	// var whopay = document.getElementById('whopay').value;
	// var type = document.getElementById('type').value;
	var company = document.getElementById('typeName').value;
	var policyNo = document.getElementById('policyNo').value;
	var expiryDate = document.getElementById('expiryDate').value;
	var gpname = document.getElementById('gpname').value;
	var tratmentType = document.getElementById('treatmentType').value;
	var regEx = /^\d{10}$/;
	var emailregEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var chk = 0;
	// var reference = document.getElementById('reference').value;
	var occupation = document.getElementById('occupation').value;
	// var patientType = document.getElementById('patientType').value;
	// var sourceOfIntro = document.getElementById('sourceOfIntro').value;
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
	var maritalsts = document.getElementById('maritalsts').value;
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
	var hospitalborn = "";
	var hs = document.getElementById("hospitalborn").checked;
	if (hs) {
		hospitalborn = "1";
	} else {
		hospitalborn = "0";
	}
	if (title == "" || title == "0") {
		jAlert('error', "Please Select  Initial!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		chk = 1;
	}
	if (firstname == "") {
		var firstName = document.createElement("label");
		firstName.innerHTML = "Please Enter First Name";
		document.getElementById('fnameError').appendChild(firstName);
		chk = 1;
	}
	if (lastName == "") {
		var lastName = document.createElement("label");
		lastName.innerHTML = "Please Enter Last Name";
		document.getElementById('lnameError').appendChild(lastName);
		chk = 1;
	}
	if (dob == "") {
		var dob = document.createElement("label");
		dob.innerHTML = "Please Enter DOB";
		document.getElementById('dobError').appendChild(dob);
		jAlert('error', "please enter DOB!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		chk = 1;
	}
	if (age == "") {
		var age = document.createElement("label");
		age.innerHTML = "Please Enter Age";
		document.getElementById('ageError').appendChild(age);
		chk = 1;
	}
	if (gender == "0") {
		var gen = document.createElement("label");
		gen.innerHTML = "Please Select Gender";
		document.getElementById('genderError').appendChild(gen);
		chk = 1;
	}

	if (address == "") {
		var address = document.createElement("label");
		address.innerHTML = "Please Enter Address";
		document.getElementById('addressError').appendChild(address);
		chk = 1;
	}
	if (town == "" || town == "0") {
		var town = document.createElement("label");
		town.innerHTML = "Please Enter Town";
		document.getElementById('townError').appendChild(town);
		chk = 1;
	}

	if (mobNo == "") {

	} else if (!regEx.test(mobNo)) {
		var mobNo = document.createElement("label");
		mobNo.innerHTML = "Please Enter Valid No.";
		document.getElementById('mobNoError1').appendChild(mobNo);
		chk = 1;
	} else if (mobNo.length != 10) {
		var mobNo = document.createElement("label");
		mobNo.innerHTML = "Please Enter Valid No.";
		document.getElementById('mobNoError1').appendChild(mobNo);
		chk = 1;
	}
	if (!adharnoregEx.test(adhno) && adhno != "") {
		var adhno = document.createElement("label");
		adhno.innerHTML = "Please Enter Valid Adhar No.";
		document.getElementById('adhnoError').appendChild(adhno);
		chk = 1;
	}

	if (email == "") {

	} else if (!emailregEx.test(email)) {
		var email = document.createElement("label");
		email.innerHTML = "Please Enter Valid No.";
		document.getElementById('emailError1').appendChild(email);
		chk = 1;
	}
	var typenamebyuse = '';
	var compname = '';
	var neisno = '';
	var designationbytp = '';
	var unitstation = '';
	var claimbytp = '';
	var relationvbytp = '';
	var colliery = '';
	var areabytp = '';
	var policyholder = '';
	if (whopay == "Third Party") {
		if (type == 0) {
			var type1 = document.createElement("label");
			type1.innerHTML = "Please select type";
			document.getElementById('tpError').appendChild(type1);
			chk = 1;
		}
		if (typeName == 0) {
			var tpname = document.createElement("label");
			tpname.innerHTML = "Please select typeName";
			document.getElementById('tpnameError').appendChild(tpname);
			chk = 1;
		}

		typenamebyuser = document.getElementById("type").options[document
				.getElementById('type').selectedIndex].text;
		compname = document.getElementById("compname").value;
		neisno = document.getElementById("neisno").value;
		designationbytp = document.getElementById("designationbytp").value;
		unitstation = document.getElementById("unitstation").value;
		claimbytp = document.getElementById("claimbytp").value;
		relationvbytp = document.getElementById("relationvbytpe").value;
		colliery = document.getElementById("colliery").value;
		areabytp = document.getElementById("areabytp").value;
		policyholder = document.getElementById("policyholder").value;

	}

	if (doctorsurgery != 0) {
		if (gpname == 0) {
			var gpnme = document.createElement("label");
			gpnme.innerHTML = "Please select GPName";
			document.getElementById('gpnameError').appendChild(gpnme);
			chk = 1;
		}
	}
	if (chk == 1) {
		// document.getElementById("savePatientNow").disabled = false;
		return false;
	} else {
		alert
		$('#baselayout1loaderPopup').modal('show');
		// document.getElementById("savePatientNow").disabled = false;
		if (validchk == 0 && validchk1 == 0) {

			$('#dashboardloaderPopup').modal("show");

			var url = "savePatientClient?title=" + title + "&firstName="
					+ firstname + "&lastName=" + lastName + "&middleName="
					+ middleName + "&gender=" + gender + "&dob=" + dob
					+ "&address=" + address + "&town=" + town + "&country="
					+ country + "&postCode=" + postCode + "&mobNo=" + mobNo
					+ "&email=" + email + "&type=" + type + "&company="
					+ company + "&policyNo=" + policyNo + "&expiryDate="
					+ expiryDate + "&whopay=" + whopay + "&gpname=" + gpname
					+ "&tratmentType=" + tratmentType + "&reference="
					+ reference + "&secondLineaddress=" + secondLineaddress
					+ "&doctorsurgery=" + doctorsurgery + "&policyExcess="
					+ policyExcess + "&occupation=" + occupation + "&state="
					+ state + "&age=" + age + "&relativename=" + relativename
					+ "&relativeno=" + relativeno + "&hospitalborn="
					+ hospitalborn + "&compname=" + compname + "&neisno="
					+ neisno + "&designationbytp=" + designationbytp
					+ "&unitstation=" + unitstation + "&claimbytp=" + claimbytp
					+ "&relationvbytp=" + relationvbytp + "&colliery="
					+ colliery + "&areabytp=" + areabytp + "&policyholder="
					+ policyholder + "&maritalsts=" + maritalsts
					+ "&mothername=" + mothername + "&fathername=" + fathername
					+ "&birthplace=" + birthplace + "&fulltime=" + fulltime;

			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
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
function savePatientRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			$('#baselayout1loaderPopup').modal('hide');
			$('#addPatientDiv').modal("hide");
			resetAddClientFileds();
			$('#dashboardloaderPopup').modal("hide");
			showReferPatientPopUp();

		}
	}
}

function resetAddClientFileds() {
	// alert('hi');
	document.getElementById('title').value = "Mr.";
	document.getElementById('firstName').value = "";
	document.getElementById('middleName').value = "";
	document.getElementById('lastName').value = "";
	document.getElementById('gender').value = "Male";
	document.getElementById('dob').value = "";
	document.getElementById('address').value = "";
	document.getElementById('town').value = "";
	document.getElementById('postCode').value = "";
	/*
	 * document.getElementById('reference').value = "0";
	 */document.getElementById('mobNo').value = "";
	document.getElementById('email').value = "";
	document.getElementById('doctorsurgery').value = "0";
	document.getElementById('gpname').value = "0";
	document.getElementById('treatmentType').value = "0";
	document.getElementById('whopay').value = "0";
	document.getElementById('type').value = "0";
	document.getElementById('typeName').value = "0";
	document.getElementById('policyNo').value = "";
	document.getElementById('expiryDate').value = "";
}

function setReferPatientName(clientid) {
	var dataObj= {
			"clientId":""+clientid+"", 
		 };
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

		  url : "getreferepatientadataBookAppointmentAjax",
		  data : data1,
		  dataType : 'json',
		  contentType : 'application/json',
		  type : 'POST',
		  async : true,
		  // beforeSend: function () { LockScreen(); },
		  success : function(data) {
			  document.getElementById('clientid').value=data.clientid;
			  document.getElementById('refer_clientname').innerHTML=data.clientname;
			  document.getElementById('refer_abrivation').innerHTML=data.abrivationid;
			  $("#referpatient").modal("show");
		    },
		    error : function(result) {
		    	console.log("error");
		    }
		 });
	
}

function savereferpatient() {

	var hospitalname = document.getElementById("hname").value;
	var clientid = document.getElementById("clientid").value;
	var disease = document.getElementById("disease").value;

	if (hospitalname == '') {
		jAlert('error', "Please select Hospital!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {
		var url = "savereferClient?clientid=" + clientid + "&hospitalname="
				+ hospitalname + "&disease="+disease+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = savereferpatientRequest;
		req.open("GET", url, true);
		req.send(null);
	}

}

function savereferpatientRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
		}
	}
}


function editdisease(id){
	
 var url="editdiseaseClient?id="+id+"";
 
 if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = seteditDiseaseRequest;
    req.open("GET", url, true); 
    req.send(null);
 
 }

function seteditDiseaseRequest(){
	
	 if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  var data = str.split("~~");
			  
			    document.getElementById("editid").value= data[0];
			    document.getElementById("edit_disease").value = data[1];
			
			    $('#editdisease').modal( "show" );	
		 }
	}
	
}


function updatedisease(){
	
	var id=document.getElementById("editid").value;
	var disease=document.getElementById("edit_disease").value;
	
	
	if (disease == '') {

		jAlert('error', "Please enter disease!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} else{
	
	var url = "updatediseaseClient?id="+id+"&disease="+disease+"";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updatediseaseRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	}
	
}

function updatediseaseRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
		}
	}
	
}

function cancelReferPatient(val){
	document.getElementById("referParentId").value = val;
	$('#cancelmodel').modal( "show" );
}

function cancelReferPatient1(){
	var referParentId = document.getElementById("referParentId").value;
	var delete_reason = document.getElementById("delete_reason").value;
	if(delete_reason=='' || delete_reason==' ' || delete_reason=='   ' || delete_reason=='    '){
		jAlert('error', "Please enter cancel reason!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url="cancelreferpatientBookAppointmentAjax?parentid="+referParentId+"&delete_reason="+delete_reason+"";  	  
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = cancelReferPatient1Request;
		  req.open("GET", url, true); 
		  req.send(null);  
	}
}
function cancelReferPatient1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}

function confirmReferPatient(id){
	if(confirm("Are you sure?")){
		var url="confirmreferpatientBookAppointmentAjax?parentid="+id+"";  	  
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = confirmReferPatientRequest;
		  req.open("GET", url, true); 
		  req.send(null);  
	}
}
function confirmReferPatientRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}

function referPatientStatus(id) {
	document.getElementById("referParentStatusId").value = id;
	$('#patientstatusmodel').modal( "show" );
}

function referPatientStatus1(){
	var referParentStatusId = document.getElementById("referParentStatusId").value;
	var patientStatus = document.getElementById("patientStatus").value;
	if(patientStatus==0){
		jAlert('error', "Please select patient status!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url="updatereferpatientstatusBookAppointmentAjax?parentid="+referParentStatusId+"&patientStatus="+patientStatus+"";  	  
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = referPatientStatus1Request;
		  req.open("GET", url, true); 
		  req.send(null);  
	}
}
function referPatientStatus1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}

function referPatientPayment(id,clinicShare,consultantShare){
	document.getElementById("referParentPaymentId").value=id;
	document.getElementById("clinicShare").value=clinicShare;
	document.getElementById("consultantShare").value=consultantShare;
	$("#referPaymentModel").modal('show');
}

function calculateClinicPayment(val){
	var clinicShare = document.getElementById("clinicShare").value;
	var consultantShare = document.getElementById("consultantShare").value;
	var clinicShareAmt = (clinicShare/100)*val;
	var consultantShareAmt = (consultantShare/100)*val;
	document.getElementById("clinicShareAmt").value=Math.round(clinicShareAmt * 100) / 100;
	document.getElementById("consultantShareAmt").value=Math.round(consultantShareAmt * 100) / 100;
	
}

function referPatientPayment1(){
	var parentid = document.getElementById("referParentPaymentId").value;
	var totalBillAmt = document.getElementById("totalBillAmt").value;
	var clinicShare = document.getElementById("clinicShare").value;
	var consultantShare = document.getElementById("consultantShare").value;
	var clinicShareAmt = document.getElementById("clinicShareAmt").value;
	var consultantShareAmt = document.getElementById("consultantShareAmt").value;
	if(totalBillAmt==''){
		jAlert('error', "Please enter bill amount!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(totalBillAmt==0 || totalBillAmt<0){
		jAlert('error', "Please enter bill amount!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var url="updatereferpatientpaymentBookAppointmentAjax?parentid="+parentid+"" +
				"&totalBillAmt="+totalBillAmt+"&clinicShare="+clinicShare+"&consultantShare="+consultantShare+"" +
						"&clinicShareAmt="+clinicShareAmt+"&consultantShareAmt="+consultantShareAmt+"";  	  
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = referPatientStatus1Request;
		  req.open("GET", url, true); 
		  req.send(null);  
	}
}
function referPatientStatus1Request(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();	 
	         }
		}	 
	}

function openViewAccount(val,clinicUserId){
	  var url="getclinicaccountdetailsBookAppointmentAjax?val="+val+"&clinicUserId="+clinicUserId+"";  	  
	  if (window.XMLHttpRequest) {
		  req = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) {
		  isIE = true;
		  req = new ActiveXObject("Microsoft.XMLHTTP");
	  }   
	  req.onreadystatechange = openViewAccountRequest;
	  req.open("GET", url, true); 
	  req.send(null);  
}
function openViewAccountRequest() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText.split("~~");
			if(data[4]==0){
				document.getElementById("bankName").value=data[0];
				document.getElementById("bankName1").innerHTML=data[0];
				document.getElementById("accountNo").value=data[1];
				document.getElementById("accountNo1").innerHTML=data[1];
				document.getElementById("ifscCode").value=data[2];
				document.getElementById("ifscCode1").innerHTML=data[2];
				document.getElementById("upiId").value=data[3];
				document.getElementById("upiId1").innerHTML=data[3];
				$("#viewAccount").modal("show");
				if(data[0]!='' || data[3]!=''){
					editAccountDetails(1);
				}else{
					editAccountDetails(0);
				}
			}else{
				document.getElementById("viewBankName").innerHTML=data[0];
				document.getElementById("viewAccountNo").innerHTML=data[1];
				document.getElementById("viewIfscCode").innerHTML=data[2];
				document.getElementById("viewupiId").innerHTML=data[3];
				$("#viewAccount1").modal("show");
			}
		 }
	}	
}

function updateAccountDeatils(){
	var bankName = document.getElementById("bankName").value;
	var accountNo = document.getElementById("accountNo").value;
	var ifscCode = document.getElementById("ifscCode").value;
	var upiId = document.getElementById("upiId").value;
	if(bankName==''){
		jAlert('error', "Please enter bank name!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(accountNo==''){
		jAlert('error', "Please enter account number!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(ifscCode==''){
		jAlert('error', "Please enter IFSC code!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		  var url="saveclinicaccountdetailsBookAppointmentAjax?bankName="+bankName+"&accountNo="+accountNo+"&ifscCode="+ifscCode+"&upiId="+upiId+"";  	  
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
		  }   
		  req.onreadystatechange = updateAccountDeatilsRequest;
		  req.open("GET", url, true); 
		  req.send(null);  
	}
	  
}
function updateAccountDeatilsRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();	 
		}
	}
}

function editAccountDetails(val){
	if(val==1){
		document.getElementById("bankNameDiv1").style.display="";
		document.getElementById("bankNameDiv2").style.display="none";
		document.getElementById("accountNoDiv1").style.display="";
		document.getElementById("accountNoDiv2").style.display="none";
		document.getElementById("ifscCodeDiv1").style.display="";
		document.getElementById("ifscCodeDiv2").style.display="none";
		document.getElementById("upidId1").style.display="";
		document.getElementById("upidId2").style.display="none";
		document.getElementById("bankbtnId").style.display="none";
		document.getElementById("editDiv").style.display="";
	}else{
		document.getElementById("bankNameDiv1").style.display="none";
		document.getElementById("bankNameDiv2").style.display="";
		document.getElementById("accountNoDiv1").style.display="none";
		document.getElementById("accountNoDiv2").style.display="";
		document.getElementById("ifscCodeDiv1").style.display="none";
		document.getElementById("ifscCodeDiv2").style.display="";
		document.getElementById("upidId1").style.display="none";
		document.getElementById("upidId2").style.display="";
		document.getElementById("bankbtnId").style.display="";
		document.getElementById("editDiv").style.display="none";
	}
}

function openEditPatient(id){
	alert("hi")
}

function addCommercial(){
	var referHospitalId = document.getElementById("referHospitalId").value;
	if(referHospitalId==0){
		
	}else{
		var dataObj={
			"referHospitalId" : ""+referHospitalId+"",	
		};
		var data1 = JSON.stringify(dataObj);
		$.ajax({
			url : "addcliniccommercialBookAppointmentAjax",
			data : data1,
			dataType : 'json',
			contentType : "application/json",
			type : "POST",
			async : true,
			success : function(data) {
				showClinicHospitalComercial(0);
			},
			error : function(result){
				jAlert('error', "Something wrong!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
		});
	}
}

function showClinicHospitalComercial(val){
	var dataObj={
		"val" : ""+val+"",
	}
	var data = JSON.stringify(dataObj);
	$.ajax({
		url : "showclinicalcomercialBookAppointmentAjax",
		data : ""+data+"",
		dataType : 'json',
		contentType : 'application/json',
		type : "POST",
		async :true,
		success : function(data) {
			document.getElementById("commercialbody").innerHTML = data.comercialList;
			if(val==1){
				$("#commercialPopup").modal('show');
			}
		},
		error : function(result) {
			jAlert('error', "Something wrong!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	});
}

function saveCommercialClinic(){
	var flag =false;
	var errorFlag = false;
	var ids = '0';
	$(".dclass").each(function() {
		ids = ids +","+this.value;
		flag = true;
		var commercial = document.getElementById("hosp_commercial"+this.value).value;
		if(commercial==''){
			errorFlag = true;
			jAlert('error', "Please enter commercial!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(commercial==0){
			errorFlag = true;
			jAlert('error', "Please enter commercial!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else if(commercial<0 || commercial>100){
			errorFlag = true;
			jAlert('error', "Please enter valid comercial!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	});
	
	if(!flag){
		jAlert('error', "Please select hospital!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(!errorFlag){
		document.getElementById("clinicHospTempIds").value=ids;
		document.getElementById("clinicHospitalForm").submit();
	}
}

function getSittingListFromDepartment(departmentId){
	var dataObj={
		"departmentId" : ""+departmentId+"",
	}
	var data = JSON.stringify(dataObj);
	$.ajax({
		url  : "getsittinglistdataBookAppointmentAjax",
		data : ""+data+"",
		dataType : "json",
		contentType : "application/json",
		type : "POST",
		async : true,
		success : function(data) {
			document.getElementById("sittingDiv").innerHTML = data.sittingList;
			$("#sittingName").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
		},
		error : function(result) {
			alert("Error")
		}
	});
}

function setProcedureMasterList(sittingId){
	var dataObj={
		"sittingId" : ""+sittingId+"",	
	}
	var data = JSON.stringify(dataObj);
	$.ajax({
		url : "getproceduremasterlistBookAppointmentAjax",
		data : ""+data+"",
		dataType : "json",
		contentType : "application/json",
		type : "POST",
		async : true,
		success : function(data){
			document.getElementById("procedureMasterDiv").innerHTML = data.procedureMasterList;
			$("#proceduerMasterName").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
		},
		error : function(result) {
			alert("Error");
		}
	});
}

function setProcedureList(proMasterId){
	var dataObj={
		"proMasterId" : ""+proMasterId+"",
	}
	var data = JSON.stringify(dataObj);
	$.ajax({
		url : "setprocedurelistBookAppointmentAjax",
		data : ""+data+"",
		dataType : "json",
		contentType : "application/json",
		type : "POST",
		async : true,
		success : function(data) {
			document.getElementById("procedureDiv").innerHTML = data.procedureList;
			$("#procedureName").trigger("chosen:updated");
			$(".chosen-select").chosen({allow_single_deselect: true});
		},
		error : function(result) {
			alert("Error");
		}
	});
}


function editdate(clientid,id){
	
	document.getElementById("sitting_Clientid").value=clientid;
	
	var url="editdischargedateCommonnew?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = seteditdischargedateRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	}


function seteditdischargedateRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  var data = str.split("~~");
			
			
			document.getElementById("id").value= data[0];
			document.getElementById("dischargedate").value = data[1];
			document.getElementById("hour").value = data[2];
			document.getElementById("minute").value = data[3];
		
			$('#updatedisdate').modal( "show" );	
		 }
	}
	
	
}

function updatedischargedate(){
	
	var clientid=document.getElementById("sitting_Clientid").value;
	var dischargedate=document.getElementById("dischargedate").value;
	var hour=document.getElementById("hour").value;
	var min=document.getElementById("minute").value;
	
	 var url = "updatedischargedateCommonnew?clientid="+clientid+"&dischargedate="+dischargedate+"&hour="+hour+"&min="+min+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();	
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updatedischargedateRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
	}


function updatedischargedateRequest(){
	
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


function fakepopup(uhid,clientid,clientname){
	
	document.getElementById("fake_uhid").value=uhid;
	document.getElementById("fake_clientid").value=clientid;
	document.getElementById("patient_nm").innerHTML=clientname;
	
	counsultationcount(clientid);
	
	//$('#fakepopup').modal("show");
	
}

function openInvestigationdash(){
	var uhid=document.getElementById("fake_uhid").value;
	var fake_clientid=document.getElementById("fake_clientid").value;
	openDisplayPopup("Investigation?uhid="+uhid+"&fake_clientid="+fake_clientid+"&IpdpatientType="+1);
}

function openPriscriptiondash(){
	
	var uhid=document.getElementById("fake_uhid").value;
	var fake_clientid=document.getElementById("fake_clientid").value;
	openDisplayPopup("onlinerequestpharPharmacy?uhid="+uhid+"&fake_clientid="+fake_clientid+"&IpdpatientType="+1);
	
}

function openPharmacydash(){
	
	var uhid=document.getElementById("fake_uhid").value;
	var fake_clientid=document.getElementById("fake_clientid").value;
	openDisplayPopup("Pharmacy?uhid="+uhid+"&fake_clientid="+fake_clientid+"&IpdpatientType="+1);
}


function openPriscDashboard(){
	var uhid=document.getElementById("fake_uhid").value;
	var fake_clientid=document.getElementById("fake_clientid").value;
	openDisplayPopup("Prescription?uhid="+uhid+"&fake_clientid="+fake_clientid+"&IpdpatientType="+1);
}

function openopddash(){
	
	var clientname=document.getElementById("patient_nm").value;
	var uhid=document.getElementById("fake_uhid").value;
	var fake_clientid=document.getElementById("fake_clientid").value;
	openDisplayPopup("Finder?uhid="+uhid+"&fake_clientid="+fake_clientid+"&clientname="+clientname+"&IpdpatientType="+1+"&aptf="+1);
}

function openipd(){
	
	var uhid=document.getElementById("fake_uhid").value;
	var fake_clientid=document.getElementById("fake_clientid").value;
	openDisplayPopup("ipdfakeConsultreportCommonnew?uhid="+uhid+"&fake_clientid="+fake_clientid+"&IpdpatientType="+1);
}

function openotddash(){
	
	var clientname=document.getElementById("patient_nm").value;
	var uhid=document.getElementById("fake_uhid").value;
	var fake_clientid=document.getElementById("fake_clientid").value;
	openDisplayPopup("Finder?uhid="+uhid+"&fake_clientid="+fake_clientid+"&clientname="+clientname+"&IpdpatientType="+1+"&aptf="+2);
}

function delte(){
	
	var fake_clientid=document.getElementById("fake_clientid").value;
	//openDisplayPopup("deleteconsultfakeFinder?fake_clientid="+fake_clientid+"");
	var check= confirm("Do you want to delete?");
		if (check == true){
	var url = "deleteconsultfakeFinder?fake_clientid="+fake_clientid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = deleteunitRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}
}
function deleteunitRequest() {
		
		if (req.readyState == 4) {
			if (req.status == 200) {
				var str = req.responseText;
				  $("#baselayout1loaderPopup").modal("hide");
					window.location.reload();
		          
			 }
		}
		
	}


function counsultationcount(clientid){
	
	var url="consultCountFinder?clientid="+clientid+"";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = setconsultcountRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function setconsultcountRequest(){

if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  var data = str.split("~~");
			
			document.getElementById("invcount").innerHTML= "("+data[0]+")";
			document.getElementById("ipdcount").innerHTML = "("+data[1]+")";
			document.getElementById("precount").innerHTML= "("+data[2]+")";
			document.getElementById("pharcount").innerHTML ="("+data[3]+")";
			document.getElementById("opdcount").innerHTML ="("+data[4]+")";
			document.getElementById("otcount").innerHTML ="("+data[5]+")";
	        document.getElementById("prisccount").innerHTML ="("+data[6]+")";
			
			
			$('#fakepopup').modal("show");
	
		 }
	}

}

function openOTdash(){
	
	var uhid=document.getElementById("fake_uhid").value;
	var fake_clientid=document.getElementById("fake_clientid").value;
	openDisplayPopup("allUserNotAvailableSlot?uhid="+uhid+"&fake_clientid="+fake_clientid+"&IpdpatientType="+1);
	
}
	
function opendischargedash(){
	
	var uhid=document.getElementById("fake_uhid").value;
	var fake_clientid=document.getElementById("fake_clientid").value;
	openDisplayPopup("InitialDischarge?uhid="+uhid+"&fake_clientid="+fake_clientid+"&IpdpatientType="+1);
	
}	
function showdischargeprintpage(Clientid){
	openEmrPopup('printdischargeCommonnew?clientid='+Clientid+'')
}
	
	
/*$(document).ready(function(){
	    
              
	          var tt= Number(document.getElementById("tthidden").value);

		      document.getElementById("word").innerHTML=convertNumberToWords(tt);});*/

