
var glbclientid = 0;
var glbclientname = "";
var glbconditionid = 0;
var glbwhopay = "";
var glbtpname = "";
var glbpolicyno = "";
var patientid="";

function showPopUp(){
	
	/*var url = "showListClient";*/
	var url = "showListBookAppointmentAjax";
	
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
				if(document.getElementById('age')){
					document.getElementById('age').value='0';
				}
				if(document.getElementById('month')){
					document.getElementById('month').value='0';
				}
				if(document.getElementById('days')){
					document.getElementById('days').value='0';
				}
				if(document.getElementById('title')){
					document.getElementById('title').value='0';
				}
				if(document.getElementById('gender')){
					document.getElementById('gender').value='0';
				}
				if(document.getElementById('state')){
					document.getElementById('state').className="";
					document.getElementById('state').value='0';
					document.getElementById('state').className="form-control showToolTip chosen-select";
					$("#state").trigger("chosen:updated");
				  	$(".chosen").chosen({allow_single_deselect: true});
				}
				if(document.getElementById('gender')){
					document.getElementById('town').className="";
					document.getElementById('town').value='0';
					document.getElementById('town').className="form-control showToolTip chosen-select";
					$("#town").trigger("chosen:updated");
				  	$(".chosen").chosen({allow_single_deselect: true});
				}
				
	         }
		}
	}
	
	
function getIpdClientInfo(clientid){
	//var url = "clientinfoIpd?clientid="+clientid+"";
	var url = "clientinfoIpdAjax?clientid="+clientid+"";
    patientid=clientid;
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getIpdClientInfoRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

var ispractchanged=0;
function getIpdClientInfoRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data = req.responseText;
			var temp = data.split('~');
			
			var fullname = temp[0];
			var diaryuser =  temp[1];
			var condition = temp[2];
			var trtmentepisode = temp[3];
			var tpname = temp[4];
			var whopay = temp[5];
			var cid = temp[6];
			 glbpolicyno = temp[7];
			 var agegender = temp[8];
			 var addres=temp[9]; 
			 var dob=temp[10];
			 var contname=temp[11];
			 var contpermob=temp[12];
			 
			 var relation=temp[13];
			 var num_admission=temp[14];
			 var reference=temp[15]; 
			 var uhid=temp[16];
			 glbclientid = cid;
			 glbclientname = fullname;
			 glbconditionid = condition;
			 glbwhopay = whopay;
			 glbtpname = tpname;
			 var pract=temp[17]
			
			
			document.getElementById("client").value = fullname;
			//document.getElementById("practitionerid").value = diaryuser;
			//document.getElementById("condition0").value = glbconditionid;
			document.getElementById("agegender").value = agegender;
			document.getElementById("cid").value = cid;
			document.getElementById("abrivationid").value = uhid;
			document.getElementById("taddress").value=addres;
			document.getElementById("dob1").value=dob;
			document.getElementById("relativename").value=contname;
			document.getElementById("relationcont").value=contpermob;
			document.getElementById("relation").value=relation;
			document.getElementById("num_admission").value=num_admission;
			document.getElementById("refferedby").value=reference;
			
			 setTreatmentEpisode();
			 document.getElementById("refferedby").value=reference; 
			 var pracload=sessionStorage.getItem(glbclientid);
			 
			 if(pracload!='1'){
				 document.getElementById('practitionerid').className=''; 
				 document.getElementById('practitionerid').value = pract;
				 $("#practitionerid").trigger("chosen:updated");
			  	  $(".chosen").chosen({allow_single_deselect: true});
			  	  
			  	  
			  	document.getElementById('department').className=''; 
			  	 document.getElementById('department').value = temp[18];
				 $("#department").trigger("chosen:updated");
			  	  $(".chosen").chosen({allow_single_deselect: true});
			  	  
			  	   
			  	document.getElementById('payee').value = temp[19];
				
			  	
			  	settpname(whopay);
			 }
			 
			
			 

		}
	}
}

function setClientData(name,id,tpcompany,tpid){
	
	document.getElementById("client").value = name;
	document.getElementById("clientid").value = id;
	document.getElementById("tpid").value = tpid;
	getIpdClientInfo(id);
  
	$('#clientSearchDiv').modal( "hide" );	

}

 function addNewPatientModal() {
    
         $('#addPatientDiv').modal('show');
 }


function searchPatient(){
	var searchText = document.getElementById("searchText1").value;
	var url = "searchParticularPatientClient?searchText="+searchText+"";

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

function setPatientName(id,typename,payee){
	var name1=document.getElementById("firstnameid"+id).value;
	name=name1;
	document.getElementById("clientid").value = id;
	//document.getElementById("payee").value = payee;
	document.getElementById("tpid").value = typename;
	
	checkPatientadmitted(id);
	
	     
		
}
var bedcid = 0;
function checkPatientadmitted(clientid){
	bedcid = clientid;
	//var url = "admittedIpd?clientid="+clientid+"";
	var url = "admittedIpdAjax?clientid="+clientid+"";
	
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkPatientadmittedRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null)
	
}

function checkPatientadmittedRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var bedid = req.responseText;
			if(bedid==0){
				getIpdClientInfo(bedcid);
				$('#clientSearchDiv').modal('hide'); 
			}else{
				jAlert('error', 'Patient admitted.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
		}
	}
}
	



function createTreatmentEpisode(){
  var clientID= document.getElementById("clientid").value;
  getIpdClientInfo(clientID);
 
  window.setTimeout(function(){
	   
	  var treatmentpolicyno = glbpolicyno ;
		document.getElementById('selectTpbtn').style.display = 'none';
		document.getElementById('treatmentType1').value = document.getElementById('condition0').value;
		
		$("#treatmentType1").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		
		/*document.getElementById('selectTpbtn').style.display = 'none';
		document.getElementById('clientId').value = glbclientid ;*/

		/*var client = name;
		var conditionType = glbconditionid;
		clientname = glbclientname ;*/
		/*document.getElementById('tempclientid').value = glbclientid ;
		document.getElementById('tempclientname').value = glbclientname ;*/
		
		if(document.getElementById('client').value==''){
			jAlert('error', 'Please select Client.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
		
		/*document.getElementById('treatmentType1').value = glbconditionid  ;
		
		
		
		$("#treatmentType1").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});*/
		
		if(glbwhopay  == 'Third Party')
			
		{
			
			document.getElementById('invoicee').value = glbtpname ;	
			document.getElementById('authorisationCode').disabled = false;
			document.getElementById('payby').checked = true;
			document.getElementById('payby1').checked = false;
			
			document.getElementById('policynodiv').style.display='';
			document.getElementById('trtmentPolicyNo').value = treatmentpolicyno;
		}
		else{
			
			document.getElementById('invoicee').value = glbclientname ;	
			document.getElementById('authorisationCode').disabled = true;
			document.getElementById('payby1').checked = true;
			document.getElementById('payby').checked = false;
			
			document.getElementById('policynodiv').style.display='none';
		}
		
		$('#addTreatmentEpisodeDiv').modal( "show" );
		
		}
	  
  }
  ,500);
}


function saveTreatment(){
	
	var chk=0;
	var clientname = glbclientname;
	var clientId = glbclientid;
	
	//var date = document.getElementById('date').value;
	//var diaryUser = document.getElementById('diaryUserId').value;
	var condition = document.getElementById('treatmentType1').value;
	
	var treatmentEpisodeName = document.getElementById('treatmentEpisodeName').value;
	var referalDate = document.getElementById('referalDate').value;
	var referalendDate = document.getElementById('referalendDate').value;
	var referralName = document.getElementById('referralName').value;
	var referralSource = document.getElementById('referralSource').value;
	
	var payby = "";
	var invoicee1 = document.getElementById('invoicee').value;
	var authorisationCode = document.getElementById('authorisationCode').value;
	var spendLimit = document.getElementById('spendLimit').value;
	var consultationLimit = document.getElementById('consultationLimit').value;
	

	

	document.getElementById('authorisationCodeError').innerHTML = "";
	document.getElementById('treatmentTypeError').innerHTML = "";
	document.getElementById('consultationLimitError').innerHTML = "";
	document.getElementById('treatmentNameError').innerHTML ="";
	document.getElementById('invoiceeError').innerHTML ="";
	if(document.getElementById('payby1').checked){
		payby = "Client";
	}
	
	
	/*if (condition == 0) {
      	var condition = document.createElement("label");
      	condition.innerHTML = "Select Condition";
        document.getElementById('treatmentTypeError').appendChild(condition);
        chk=1;
     } */
	if (consultationLimit == "" || consultationLimit == undefined) {
      	var consultationLimit = document.createElement("label");
      	consultationLimit.innerHTML = "Enter Consultation Limit";
        document.getElementById('consultationLimitError').appendChild(consultationLimit);
        chk=1;
     }  
	if (invoicee1 ==  "" || invoicee1 == null || invoicee1 == undefined) {
      	var consultationLimit = document.createElement("label");
      	consultationLimit.innerHTML = "Enter Invoice";
        document.getElementById('invoiceeError').appendChild(consultationLimit);
        chk=1;
     }  
	
   	if (treatmentEpisodeName == "") {
      	var treatmentEpisodeName = document.createElement("label");
      	treatmentEpisodeName.innerHTML = "Enter Name";
        document.getElementById('treatmentNameError').appendChild(treatmentEpisodeName);
        chk=1;
     }
     
      if (referalDate == "") {
      	var treatmentEpisodeName = document.createElement("label");
      	treatmentEpisodeName.innerHTML = "Enter Referal From Date";
        document.getElementById('referalDateError').appendChild(treatmentEpisodeName);
        chk=1;
     }  
      
      if (referalendDate == "") {
      	var treatmentEpisodeName = document.createElement("label");
      	treatmentEpisodeName.innerHTML = "Enter Referal To Date";
        document.getElementById('referalendDateError').appendChild(treatmentEpisodeName);
        chk=1;
     }  
       
	
	if(document.getElementById('payby').checked){
		payby = "Third Party";
		if (authorisationCode ==  "") {
	      	var authorisationCode = document.createElement("label");
	      	authorisationCode.innerHTML = "Enter Code";
	        document.getElementById('authorisationCodeError').appendChild(authorisationCode);
	        chk=1;
	     }  
	}
	
   	
   	if(chk==1)
    {
       return false;
    }
    else
    {
     
    	
    var urgent = document.getElementById('urgent').checked;
    
    var ipdopd = '0';
	 if(document.getElementById('opd').checked==true){
		 ipdopd = '0';
	 }else{
		 ipdopd = '1';
	 }
	 
	 glbwhopay=payby;
	 
	// openIpdPopup('inputIpd');
    // var url = "saveTreatmentEpisode?treatmentEpisodeName="+treatmentEpisodeName+"&payby="+payby+"&invoicee="+invoicee1+"&authorisationCode="+authorisationCode+"&spendLimit="+spendLimit+"&consultationLimit="+consultationLimit+"&client="+editClientName+"&clientId="+patientId+"&date="+date+"&diaryUser="+diaryUser+"&treatmentType="+condition+"&referalDate="+referalDate+"&referralName="+referralName+"&referralSource="+referralSource+"";
     var url = "saveTreatmentEpisode?treatmentEpisodeName="+treatmentEpisodeName+"&payby="+payby+"&invoicee="+invoicee1+"&authorisationCode="+authorisationCode+"&spendLimit="+spendLimit+"&consultationLimit="+consultationLimit+"&client="+clientname+"&clientId="+clientId+"&treatmentType="+condition+"&referalDate="+referalDate+"&referralName="+referralName+"&referralSource="+referralSource+"&urgent="+urgent+"&ipdopd="+ipdopd+"&referalendDate="+referalendDate+" ";
 
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveTreatmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    }
	
	
}

function saveTreatmentRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			jAlert('success', 'Treatment Episode Created Successfully.', 'Success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		
			
			 $( "#addTreatmentEpisodeDiv" ).modal( "hide" );
			 
			 setTreatmentEpisode();
			 
			//tempAlert("Treatment Episode Added Successfully.",5000);

			//document.getElementById('redirectToDashboard').submit();
			
		}
	}
}



 

function setTreatmentEpisode(){
	//alert(editpateintpayby)
	var payee = glbwhopay;
	//lastAppointmentClientid = clientid;
	
	//var clientid = read_cookie("cookieClientId");

/*	if(glbwhopay == 'Self'){
		payee = "Client";
	}
	else{
		payee = "Third Party";
	}*/
	
	var url = "setTreatmentEpisode?clientid="+glbclientid+"&payby="+payee+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setTreatmentEpisodeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setTreatmentEpisodeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById('treatmentepisodeajax').innerHTML = req.responseText;
		}
	}
}



//save validation 

function checkTreatmentEpisodeStatusAjax(){
	var tepisodeid = document.getElementById('treatmentEpisode').value;
	if(tepisodeid=='0'){
		jAlert('error', 'Please create treatment episode', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else if(tepisodeid==''){
		jAlert('error', 'Please create treatment episode', 'Error Dialog');
		setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
		}, alertmsgduration);
	}else{
		var url = "disstatusIpd?tepisodeid="+tepisodeid+" ";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkTreatmentEpisodeStatusAjaxRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);
	}
	

}

function checkTreatmentEpisodeStatusAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data = req.responseText;
			var temp = data.split('~');
			
			status = temp[0];
			refenddate = temp[1];
			var currentdate = getCurrentDate();
			var disbedid= document.getElementById("disbedid").value;
			
			if(status=='1' && disbedid!='0'){
				jAlert('error', 'Treatment Episode has been discharged,use another.', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}/*else if (new Date(refenddate) < new Date(currentdate)) {
				jAlert('error', 'Treatment episode expired', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}*/else{
				savevalidate();
			}
		}
	}

}

function savevalidate(){
	
	sessionStorage.setItem("mlctemp","0");
	
    $( "#baselayout1loaderPopup" ).modal( "show" );
    var index=0;
    var diagosis=0;
    $('.classD').each(function() { 
		if(this.checked == true){
			if(index==0){
				document.getElementById("conditionid").value=this.value;
			} 
			diagosis=diagosis+","+this.value;
			index++;
		}
								
	});
    
	var refdoctors=0;
			$('.refdoctors').each(function() { 
				if(this.checked == true){
				    refdoctors=refdoctors+","+this.value; 
				} 
										
			});
    
    document.getElementById("refdoctofid").value= refdoctors;
	document.getElementById('wardid').disabled = false;
	document.getElementById('bedid').disabled = false;
	document.getElementById('tpid').disabled = false;
	if(document.getElementById('treatmentEpisode')){
		document.getElementById('treatmentepisodeid').value = document.getElementById('treatmentEpisode').value;
	}else{
		document.getElementById('treatmentepisodeid').value = "0";
	}
	
	var editselectedid = document.getElementById('editselectedid').value;
	var isok='1'; 
	
 	var relationcont= document.getElementById('relationcont').value;
	var payee=document.getElementById("payee").value;
	
	var weightsts="";
	if(document.getElementById("iconsts")){
		weightsts=document.getElementById("iconsts").value;
	}
	
	var weightfld=""
		if(document.getElementById("wtaddmission")){
			weightfld=document.getElementById("wtaddmission").value;
		}
		
	if(document.getElementById('client').value==''){
	   $( "#baselayout1loaderPopup" ).modal( "hide" );
		jAlert('error', 'Please Select Client.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(document.getElementById('admissiondate').value==''){
			    $( "#baselayout1loaderPopup" ).modal( "hide" );
				jAlert('error', 'Please Select Date of Admission .', 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}
	else if(document.getElementById('practitionerid').value==0){
	    $( "#baselayout1loaderPopup" ).modal( "hide" );
		jAlert('error', 'Please Select Primary Consultant.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	
	/*else if(document.getElementById('practitionerid').value==0 && editselectedid!=0 ){
	    $( "#baselayout1loaderPopup" ).modal( "hide" );
		jAlert('error', 'Please Select Admitting Consultant.', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}*/
	else if(document.getElementById('wardid').value==0){
	 $( "#baselayout1loaderPopup" ).modal( "hide" );
		jAlert('error', 'Please Select Ward.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);	
	}
	else if(payee=='s'){
	$( "#baselayout1loaderPopup" ).modal( "hide" );
	    jAlert('error', 'Please Select Payee.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);	
	}
	else if(document.getElementById('bedid').value==0){
	$( "#baselayout1loaderPopup" ).modal( "hide" );
		jAlert('error', 'Please Select Bed.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);	
	}
	//else if(diagosis==0 && editselectedid!=0){  //validation remove @vinod sir told
	//$( "#baselayout1loaderPopup" ).modal( "hide" );
		//jAlert('error', 'Please Select Diagnosis.', 'Error Dialog');
		
		//setTimeout(function() {
		//	$("#popup_container").remove();
		//	removeAlertCss();
		//}, alertmsgduration);	
//}
	
	/*else if(document.getElementById('treatmentEpisode').value==0 && payee==1 && editselectedid!=0){*/
/*	else if(payee ==1 && editselectedid!=0){
	$( "#baselayout1loaderPopup" ).modal( "hide" );
		jAlert('error', 'Please Select Treatment Episode.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}*/
	
	
	 else if(relationcont!="" && relationcont.length<10){
	    
 			$( "#baselayout1loaderPopup" ).modal( "hide" );
 			     jAlert('error', 'please enter valid mobile number!.', 'Error Dialog');
		          
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
		    }, alertmsgduration);
	}
	 else if(weightsts=='true' && weightfld==''){
	$( "#baselayout1loaderPopup" ).modal( "hide" );
		jAlert('error', 'Please enter Weight on Addmisssion in Admission Details.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, 2000);	
		document.getElementById("wtaddmission").focus();
	}
	 
	else{
		if(document.getElementById("admissiondetails")){
			var admissiondetails = nicEditors.findEditor( "admissiondetails" ).getContent();
			document.getElementById("admissiondetails").value=admissiondetails;
		}
		
		if(document.getElementById("suggestedtrtment")){
			var suggestedtrtment = nicEditors.findEditor( "suggestedtrtment" ).getContent();
			document.getElementById("suggestedtrtment").value=suggestedtrtment;
		}
		
		if(document.getElementById("chiefcomplains1")){
			var chiefcomplains = nicEditors.findEditor( "chiefcomplains1" ).getContent();
			document.getElementById("chiefcomplains1").value=chiefcomplains;
		}
		
		if(document.getElementById("addmissionfor")){
			var addmissionfor = nicEditors.findEditor( "addmissionfor" ).getContent();
			document.getElementById("addmissionfor").value=addmissionfor;
		}
		
		if(document.getElementById("reimbursment")){
			var reimbursment = nicEditors.findEditor( "reimbursment" ).getContent();
			document.getElementById("reimbursment").value=reimbursment;
		}
		
		if(document.getElementById("hosp")){
			var hosp = nicEditors.findEditor( "hosp" ).getContent();
			document.getElementById("hosp").value=hosp;
		}
		
		if(document.getElementById("packagename")){
			var packagename = nicEditors.findEditor( "packagename" ).getContent();
			document.getElementById("packagename").value=packagename;
		}
		
		if(document.getElementById("presentillness")){
			var presentillness = nicEditors.findEditor( "presentillness" ).getContent();
			document.getElementById("presentillness").value=presentillness;
		}
		
		
	/*	var suggestoper = nicEditors.findEditor( "suggestoper" ).getContent();
		var systdepartment = nicEditors.findEditor( "systdepartment" ).getContent();*/
	/*	var fpnotest = nicEditors.findEditor( "fpnotest" ).getContent();
		var nauseanotes = nicEditors.findEditor( "nauseanotes" ).getContent();
		var fnunotes = nicEditors.findEditor( "fnunotes" ).getContent();
		var smnotes = nicEditors.findEditor( "smnotes" ).getContent();
		var chestpainnotes = nicEditors.findEditor( "chestpainnotes" ).getContent();
		var dimvisionnotes = nicEditors.findEditor( "dimvisionnotes" ).getContent();
		var hgunotes = nicEditors.findEditor( "hgunotes" ).getContent();
		var hmnotes = nicEditors.findEditor( "hmnotes" ).getContent();
		var jointpainnotes = nicEditors.findEditor( "jointpainnotes" ).getContent();
		var specialnotes = nicEditors.findEditor( "specialnotes" ).getContent();
		var edemafeetnotes = nicEditors.findEditor( "edemafeetnotes" ).getContent();
		var hematurianotes = nicEditors.findEditor( "hematurianotes" ).getContent();
		var bmnotes = nicEditors.findEditor( "bmnotes" ).getContent();
		var oligurianotes = nicEditors.findEditor( "oligurianotes" ).getContent();
		var pstgunotes = nicEditors.findEditor( "pstgunotes" ).getContent();
		var ihnotes = nicEditors.findEditor( "ihnotes" ).getContent();
		var tnenotes = nicEditors.findEditor( "tnenotes" ).getContent();
		var weaknessnotes = nicEditors.findEditor( "weaknessnotes" ).getContent();
		var constpationnotes = nicEditors.findEditor( "constpationnotes" ).getContent();
		var bmenotes = nicEditors.findEditor( "bmenotes" ).getContent();*/
		
		
		var diethist = "", birthhist="",developmenthist="",emmunizationhist="",sugicalnote="";
		if(document.getElementById("diethist")){
			diethist=nicEditors.findEditor( "diethist" ).getContent();
		}
		if(document.getElementById("birthhist")){
			birthhist=nicEditors.findEditor( "birthhist" ).getContent();
		}
		if(document.getElementById("developmenthist")){
			developmenthist=nicEditors.findEditor( "developmenthist" ).getContent();
		}
		if(document.getElementById("emmunizationhist")){
			emmunizationhist=nicEditors.findEditor( "emmunizationhist" ).getContent();
		}
		if(document.getElementById("surgicalnotes")){
			sugicalnote=nicEditors.findEditor( "surgicalnotes" ).getContent();
		}
		
		if( document.getElementById("surgicalnotes")){
			document.getElementById("surgicalnotes").value= sugicalnote;
		}
		  
		  
		//var surgicalnotes=nicEditors.findEditor( "surgicalnotes" ).getContent();
		if( document.getElementById("diethist")){
			document.getElementById("diethist").value= diethist;
		}
		if (document.getElementById("birthhist")) {
			 document.getElementById("birthhist").value= birthhist;
		}
		if (document.getElementById("emmunizationhist")) {
			 document.getElementById("emmunizationhist").value= emmunizationhist;
		}
		if (document.getElementById("developmenthist")) {
			 document.getElementById("developmenthist").value= developmenthist
		}
		
		
		if (document.getElementById("pasthistory")) {
			var pasthistory = nicEditors.findEditor( "pasthistory" ).getContent();
			document.getElementById("pasthistory").value=pasthistory;
		}
		if (document.getElementById("personalhist")) {
			var personalhist = nicEditors.findEditor( "personalhist" ).getContent();
			document.getElementById("personalhist").value=personalhist;
		}
		

		if (document.getElementById("familyhist")) {
			var familyhist = nicEditors.findEditor( "familyhist" ).getContent();
			document.getElementById("familyhist").value=familyhist;
		}
		if (document.getElementById("onexamination")) {
			var onexamination = nicEditors.findEditor( "onexamination" ).getContent();
			document.getElementById("onexamination").value=onexamination;
		}
		if (document.getElementById("earlierinvest")) {
			var earlierinvest= nicEditors.findEditor( "earlierinvest" ).getContent();
			document.getElementById("earlierinvest").value=earlierinvest;
		}
		if (document.getElementById("alergies")) {
			var alergies=nicEditors.findEditor( "alergies" ).getContent();
			document.getElementById("alergies").value=alergies;
		}
			
		if(document.getElementById("maternal_history")){
			document.getElementById("maternal_history").value=nicEditors.findEditor( "maternal_history" ).getContent();
		}
		if(document.getElementById("perinatal_history")){
			document.getElementById("perinatal_history").value=nicEditors.findEditor( "perinatal_history" ).getContent();
		}
		if(document.getElementById("ondiet")){
			var ondiet = nicEditors.findEditor( "ondiet" ).getContent();
			document.getElementById("ondiet").value=ondiet;
		}
		if(document.getElementById("physio")){
			var physio = nicEditors.findEditor( "physio" ).getContent();
			document.getElementById("physio").value=physio;
		}
		/*document.getElementById("suggestoper").value=suggestoper;
		document.getElementById("systdepartment").value=systdepartment;*/
		/*document.getElementById("fpnotest").value=fpnotest;
		document.getElementById("nauseanotes").value=nauseanotes;
		document.getElementById("fnunotes").value=fnunotes;
		document.getElementById("smnotes").value=smnotes;
		document.getElementById("chestpainnotes").value=chestpainnotes;
		document.getElementById("dimvisionnotes").value=dimvisionnotes;
		document.getElementById("hgunotes").value=hgunotes;
		document.getElementById("hmnotes").value=hmnotes;
		document.getElementById("jointpainnotes").value=jointpainnotes;
		document.getElementById("specialnotes").value=specialnotes;
		document.getElementById("edemafeetnotes").value=edemafeetnotes;
		document.getElementById("hematurianotes").value=hematurianotes;
		document.getElementById("oligurianotes").value=oligurianotes;
		document.getElementById("pstgunotes").value=pstgunotes;
		document.getElementById("ihnotes").value=ihnotes;
		document.getElementById("bmenotes").value=bmenotes;
		document.getElementById("bmnotes").value=bmnotes;
		document.getElementById("tnenotes").value=tnenotes;
		document.getElementById("weaknessnotes").value=weaknessnotes;
		document.getElementById("constpationnotes").value=constpationnotes; */
		
		  
		//document.getElementById("surgicalnotes").value=surgicalnotes;
		var doctors=0;
		$('.doctors').each(function() { 
			if(this.checked == true){
			    doctors=doctors+","+this.value; 
			} 
									
		});
		document.getElementById("secndryconsult").value=doctors;
		
		document.getElementById('ipdadmissionfrm').submit();
	}
	
	
}





function editClientfromIpd(){

   var patient=document.getElementById("clientid").value;


   if(glbclientid==0 || glbclientid==''){
         
         if(patient==0 || patient==''){
            
		            jAlert('error', 'Please Select Client First.', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);   
         } else {
         
             openPopup("editClient?selectedid="+patient+"");
         }	
   }
   else {
      openPopup("editClient?selectedid="+glbclientid+"");
   }
}






function setBedList(wid){
	
	/*var url = "bedIpd?wid="+wid+" ";*/
	var url = "bedBookAppointmentAjax?wid="+wid+" ";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setBedListRequest;
    req.open("GET", url, true); 
    
    req.send(null);

}

function setBedListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('bedlistdiv').innerHTML = req.responseText;
		}
	}

}

var cell;
var row;
var index=0;
var btnid;

function addMoreCondition(tableID,id) {
	btnid=id;
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	row=table.insertRow(rowCount);
	var counts = rowCount - 1;	
	cell=row.insertCell(0);	
	index=counts+1;
    var url = "addnewrowIpd?rowcount="+counts+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addMoreConditionRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
}

function addMoreConditionRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          row.innerHTML=req.responseText;
	          document.getElementById(btnid).innerHTML="<a type='button' class='btn btn-primary' onclick=deleteCondition('innercondition','"+btnid+"')><i class='fa fa-trash-o'></i></a>";
	          
	              $("#condition"+index+"").trigger("chosen:updated");
    			  $(".chosen").chosen({allow_single_deselect: true});
	          
         }
	}	 
}


function deleteCondition(tableID,id) {
	
	
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;	
        document.getElementById(id).innerHTML="<a type='button' class='btn btn-primary' onclick=addMoreCondition('innercondition','"+id+"')><i class='fa fa-plus'></i></a>";
        table.deleteRow(rowCount-1);
}


function setNamestoSelect() {

	    for(var i=0;i<=index;i++) {  
		  var mdicinenametxt = $("#condition"+i+" option:selected").text();
	      document.getElementById("conditionname"+i+"").value=mdicinenametxt;
	    }
}



function checkBalance(whopay,bal){
	if(whopay=='Client'){
		if(parseFloat(bal)>0){
			document.getElementById("edchkDischarge").checked = false;
			jAlert('error', "Client balance is pending.can't discharge!!.", 'Error Dialog');
		
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	}
}

/*function addRow() {

var url = "addnewrowIpd";
if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addRowRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function addRowRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
	          document.getElementById("newrow").innerHTML=req.responseText;	
	          
	          
         }
	}	 
}*/



function settpname(val){
	
     if(val==1 || val=='Third Party'){
           document.getElementById("tpdiv").style="";
           var tpid=document.getElementById("tpid").value;
     	   if(tpid=="" || tpid=="0"){
					document.getElementById("payee").value="0";	     		
				  
				   jAlert('error', "Cant Change To Third Party! Please Edit this Patient!!.", 'Error Dialog');
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, 5*1000);          
		        			
     		} else {
     			 if(document.getElementById("tprow")){
     				document.getElementById("tprow").style="";
     			 }
     			if(document.getElementById("treatmentepisodeajax")){
     				document.getElementById("treatmentepisodeajax").style="";
     			}
     			if(document.getElementById("treatmentepisodeajax")){
     				document.getElementById("viewtp").style="";
     			}
            	
     		}
           
     } else {
           document.getElementById("tpdiv").style="display:none;";
           if(document.getElementById("tprow")){
        	   document.getElementById("tprow").style="display:none;";
           }
           if(document.getElementById("tprow")){
        	   document.getElementById("viewtp").style="display:none;"; 
           }
           if(document.getElementById("treatmentepisodeajax")){
        	   document.getElementById("treatmentepisodeajax").style="display:none;";
           }
          
     }
    
	    

}



function setChiefComplaints(id){
  
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setChiefComplaintsRequest;
    req.open("GET", url, true); 
              
    req.send(null);
            

}

function setChiefComplaintsRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		        var chiefcomplains= nicEditors.findEditor( "chiefcomplains1" ).getContent();	  
				chiefcomplains=chiefcomplains+""+req.responseText;
				nicEditors.findEditor('chiefcomplains1').setContent(chiefcomplains);	          
         }
	}	 
}

function setInvestigationtemplate(id){
  
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setInvestigationtemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
            

}

function setInvestigationtemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			var onexamination= nicEditors.findEditor( "comment" ).getContent();	  
						onexamination=onexamination+req.responseText;        
						nicEditors.findEditor('comment').setContent(onexamination);	            
         }
	}	 
}


function getpresentIllness(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getpresentIllnessRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getpresentIllnessRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var presentillness= nicEditors.findEditor( "presentillness" ).getContent();	  
				presentillness=presentillness+req.responseText;        
				nicEditors.findEditor('presentillness').setContent(presentillness);	  	          
         }
	}	 
}

function setpasthistory(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setpasthistoryRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setpasthistoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var pasthistory= nicEditors.findEditor( "pasthistory" ).getContent();	  
				pasthistory=pasthistory+req.responseText;        
				nicEditors.findEditor('pasthistory').setContent(pasthistory);	  	          
         }
	}	 
}




function getFamilyhistory(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getFamilyhistoryRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getFamilyhistoryRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var familyhist= nicEditors.findEditor( "familyhist" ).getContent();	  
				familyhist=familyhist+req.responseText;        
				nicEditors.findEditor('familyhist').setContent(familyhist);	  	            
         }
	}	 
}


function getdisctemplate(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getdisctemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getdisctemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var discharge_default= nicEditors.findEditor( "discharge_default" ).getContent();	  
				discharge_default=discharge_default+req.responseText;        
				nicEditors.findEditor('discharge_default').setContent(discharge_default);	  	
         }
	}	 
}



function gethosptemplate(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = gethosptemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function gethosptemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var hospitalcourse= nicEditors.findEditor( "hospitalcourse" ).getContent();	  
				hospitalcourse=hospitalcourse+req.responseText;        
				nicEditors.findEditor('hospitalcourse').setContent(hospitalcourse);	     
         }
	}	 
}


function getnursingtemplate(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getnursingtemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getnursingtemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var discadvnotes= nicEditors.findEditor( "discadvnotes" ).getContent();	  
				discadvnotes=discadvnotes+req.responseText;        
				nicEditors.findEditor('discadvnotes').setContent(discadvnotes);	  	          
         }
	}	 
}

function getoperativetemplate(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getoperativetemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getoperativetemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var discadvnotes= nicEditors.findEditor( "operation_notes" ).getContent();	  
				discadvnotes=discadvnotes+req.responseText;        
				nicEditors.findEditor('operation_notes').setContent(discadvnotes);	  	          
         }
	}	 
}



function getpersonaltemp(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getpersonaltempRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getpersonaltempRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var personalhist= nicEditors.findEditor( "personalhist" ).getContent();	  
				personalhist=personalhist+req.responseText;        
				nicEditors.findEditor('personalhist').setContent(personalhist);	  	          
         }
	}	 
}

function getonexamtemp(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getonexamtempRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getonexamtempRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var onexamination= nicEditors.findEditor( "onexamination" ).getContent();	  
				onexamination=onexamination+req.responseText;        
				nicEditors.findEditor('onexamination').setContent(onexamination);	  	          
         }
	}	 
}



function getipdmaternalhistryTemp(id){

	var url="getipdtemplateCommonnew?id="+id+"";
if (window.XMLHttpRequest) {
req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange = getipdmaternalhistryRequest;
req.open("GET", url, true); 
          
req.send(null);
}

function getipdmaternalhistryRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
				          
			var onexamination= nicEditors.findEditor( "maternal_history" ).getContent();	  
			onexamination=onexamination+req.responseText;        
			nicEditors.findEditor('maternal_history').setContent(onexamination);	  	          
     }
}	 
}


function getperinatalHistrTemp(id){

	var url="getipdtemplateCommonnew?id="+id+"";
if (window.XMLHttpRequest) {
req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange = getperinatalHistrTempRequest;
req.open("GET", url, true); 
          
req.send(null);
}

function getperinatalHistrTempRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
				          
			var onexamination= nicEditors.findEditor( "perinatal_history" ).getContent();	  
			onexamination=onexamination+req.responseText;        
			nicEditors.findEditor('perinatal_history').setContent(onexamination);	  	          
     }
}	 
}




function gettreatmenttemp(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = gettreatmenttempRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function gettreatmenttempRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var suggestedtrtment= nicEditors.findEditor( "suggestedtrtment" ).getContent();	  
				suggestedtrtment=suggestedtrtment+req.responseText;        
				nicEditors.findEditor('suggestedtrtment').setContent(suggestedtrtment);	  	          
         }
	}	 
}


function getInvstemplate(id) {
 
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getInvstemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function getInvstemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var suggestedtrtment= nicEditors.findEditor( "investigation" ).getContent();	  
				suggestedtrtment=suggestedtrtment+req.responseText;        
				nicEditors.findEditor('investigation').setContent(suggestedtrtment);	  	          
         }
	}	 
}

function getFindOnDischtemplate(id) {

      var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getFindOnDischtemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function getFindOnDischtemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var suggestedtrtment= nicEditors.findEditor( "finddis" ).getContent();	  
				suggestedtrtment=suggestedtrtment+req.responseText;        
				nicEditors.findEditor('finddis').setContent(suggestedtrtment);	  	          
         }
	}	 
}




function setchiefcomp(val){

  			var chiefcomplains= nicEditors.findEditor( "chiefcomplains1" ).getContent();	  
			chiefcomplains=chiefcomplains+""+val;
			nicEditors.findEditor('chiefcomplains1').setContent(chiefcomplains);	      

}





function savePatient(){

	var mothername='', fathername='',birthplace='';
	document.getElementById("savePatientNow").disabled = true; 
	
	if(document.getElementById('mothername')){
		mothername=document.getElementById('mothername').value;
		fathername=document.getElementById('fathername').value;
		birthplace=document.getElementById('birthplace').value;
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
	var fulltime='00:00:00';
	if(document.getElementById('hourls')){
	var hour = document.getElementById('hourls').value;
	var minute = document.getElementById('minutels').value;
	 fulltime=hour+":"+minute+":"+"00";
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
	var hospitalborn="";
	var hs =document.getElementById("hospitalborn").checked;
	if(hs){
		hospitalborn="1";
	}else{
		hospitalborn="0";
	}
	if(title=="" || title=="0"){
 		jAlert('error', "Please Select  Initial!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
    	chk=1;
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
//	
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
//	var lastName = document.getElementById('lastName').value;
//	if(lastName.match(/\s/g)){
//		var lastName = document.createElement("label");
//		lastName.innerHTML = "Space Not Allow";
//	    document.getElementById('lnameError').appendChild(lastName);
//	    chk=1;
//	}
//	if(lastName.match(/[^a-zA-Z]+/)){
//		var firstName = document.createElement("label");
//		firstName.innerHTML = "Special Character or Number Not Allow";
//	    document.getElementById('fnameError').appendChild(firstName);
//	    chk=1;
//	}
//	if(middleName.match(/[^a-zA-Z]+/)){
//		var firstName = document.createElement("label");
//		firstName.innerHTML = "Special Character or Number Not Allow";
//	    document.getElementById('fnameError').appendChild(firstName);
//	    chk=1;
//	}
	if(dob == ""){
		var dob = document.createElement("label");
		dob.innerHTML = "Please Enter DOB";
	    document.getElementById('dobError').appendChild(dob);
	    jAlert('error', "please enter DOB!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	    chk=1;
	}
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
	}
	else if(mobNo.length!=10) {
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
//	 }	
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
		document.getElementById("savePatientNow").disabled = false; 
		return false;
	}
	else{
		alert
		$('#baselayout1loaderPopup').modal('show');
		document.getElementById("savePatientNow").disabled = false; 
		 if(validchk == 0 && validchk1 == 0){
				
			 $('#dashboardloaderPopup').modal( "show" );
			 
			 var url = "savePatientClient?title="+title+"&firstName="+firstname+"&lastName="+lastName+"&middleName="+middleName+"&gender="+gender+"&dob="+dob+"&address="+address+"&town="+town+"&country="+country+"&postCode="+postCode+"&mobNo="+mobNo+"&email="+email+"&type="+type+"&company="+company+"&policyNo="+policyNo+"&expiryDate="+expiryDate+"&whopay="+whopay+"&gpname="+gpname+"&tratmentType="+tratmentType+"&reference="+reference+"&secondLineaddress="+secondLineaddress+"&doctorsurgery="+doctorsurgery+"&policyExcess="+policyExcess+"&occupation="+occupation+"&state="+state+"&age="+age+"&relativename="+relativename+"&relativeno="+relativeno+"&hospitalborn="+hospitalborn+"&compname="+compname+"&neisno="+neisno+"&designationbytp="+designationbytp+"&unitstation="+unitstation+"&claimbytp="+claimbytp+"&relationvbytp="+relationvbytp+"&colliery="+colliery+"&areabytp="+areabytp+"&policyholder="+policyholder+"&maritalsts="+maritalsts+"&mothername="+mothername+"&fathername="+fathername+"&birthplace="+birthplace+"&fulltime="+fulltime;

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
				$('#baselayout1loaderPopup').modal('hide');
				//document.getElementById("allPatient").innerHTML = req.responseText;
				$('#addPatientDiv').modal( "hide" );
				resetAddClientFileds();
				$('#dashboardloaderPopup').modal( "hide" );
				showPopUp();
	         	
				
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

function getspeciality(d) {

     var url = "getspecialityIpd?doctorid="+d+" ";
	
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
			
				document.getElementById("deptDiv").innerHTML = req.responseText;
				  $("#department").trigger("chosen:updated");
    			$(".chosen").chosen({allow_single_deselect: true});
	         }
		}
	} 


function getFormDatacasualty(doctorid) {
	 
	sessionStorage.setItem(glbclientid,"1");
   /* document.getElementById("ipdadmissionfrm").action="ipdformdataIpd?action=1";*/

	if(document.getElementById("actiontype")){
		if(document.getElementById("actiontype").value=='2'){
			document.getElementById("ipdadmissionfrm").action="ipdformdataIpdAjax?action=2";
		}else{
			document.getElementById("ipdadmissionfrm").action="ipdformdataIpdAjax?action=1";
		}
	}else{
		document.getElementById("ipdadmissionfrm").action="ipdformdataIpdAjax?action=1";	
	}
	 
    document.getElementById("ipdadmissionfrm").submit();
    
}


 function getFormData(doctorid) {
 
	 
	 sessionStorage.setItem(glbclientid,"1");
      /* document.getElementById("ipdadmissionfrm").action="ipdformdataIpd?action=0";*/
	 document.getElementById("ipdadmissionfrm").action="ipdformdataIpdAjax?action=0";
       document.getElementById("ipdadmissionfrm").submit();
       
 }

 function getEditFormData(doctorid) {
 
	   var id= document.getElementById("editselectedid").value;	       
       document.getElementById("ipdadmissionfrm").action="editCommonnew?selectedid="+id+"";
       document.getElementById("ipdadmissionfrm").submit();
       
 }
 
 function addnewCOndition(indx) {
 
      index=indx;
      var condiName= document.getElementById("newcondition").value;
      var icdcode=document.getElementById("icdcode").value;
      var practitionerid=document.getElementById("practitionerid").value;
     
 	  var url="saveconditionIpd?condition="+condiName+"&icdcode="+icdcode+"&practitionerid="+practitionerid+"";
 	  if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
               
    req.onreadystatechange = addnewCOnditionRequest;
    req.open("GET", url, true); 
    
    req.send(null);	 
  
 }

 function addnewCOnditionRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				jAlert('success', "Diagosis Added!.", 'Success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				document.getElementById("dispid").className="hidden";
	         }
		}
	}
	
	
  function addObsRow(num,type) {
  
            var i=parseInt(num);
            for(var j=0;j<i;j++) {
  
		            var table = document.getElementById("obstable");
					var rowCount = table.rows.length;
					row=table.insertRow(rowCount);
					var str= '<td style="width: 5%;padding-right: 15px;"><input type="number" name="" value='+(rowCount+1)+' class="form-control"></td> '
			     	+ '<td style="width: 8%;padding-right: 15px;"><input type="number" name="obslist['+rowCount+'].year" value="" class="form-control" placeholder="year"></td>'
					+ '<td style="width: 7%;padding-right: 15px;">'+type+' <input type="hidden" value="'+type+'" name="obslist['+rowCount+'].type" /></td>'
					+ '<td style="width: 13%;padding-right: 15px;">'
					+ '<select name="obslist['+rowCount+'].type_delivery" class="form-control">'
					+ '<option value="0">Type of Delivery</option>'
					+ '<option value="Normal">Normal</option>'
					+ '<option value="Vaccume">Vaccume</option>'
					+ '<option value="Forceps">Forceps</option>'
					+ '<option value="LSCS">LSCS</option>'
					+ '</select>' 
					+ '</td>'
					+ '<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist['+rowCount+'].indications" value="" class="form-control" placeholder="Indications"></td>'
					+ '<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist['+rowCount+'].coamplications" value="" class="form-control" placeholder="Coamplications"></td>'
					+ '<td style="width: 20%;padding-right: 15px;"><input type="text" name="obslist['+rowCount+'].institution" value="" class="form-control" placeholder="Institution"></td>'
					+ '<td><a class="cursorpointer" onclick=deleteRow("obstable") ><i class="fa fa-trash" style="color: #d9534f;"></i></a></td>'; 
		          
		           row.innerHTML= str; 
          }
  }	
  
   
  function dispDIv(){
	   
	        if(document.getElementById("dispid").className==""){
	          
	             document.getElementById("dispid").className="hidden";
	        } else {
	         	document.getElementById("dispid").className="";
	        }
	   }     
	   
	   
	   
	   
   function deleteRow(tableID) {

        var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		table.deleteRow(rowCount-1);		
        
}	     
  
	  
   function onlyNum(d) {
   
       var numbers = /^[0-9]+$/;  
       
       if(d.value.match(numbers))  {
          return true;
       } else {
             d.value='';
            return false;
       }
      
   } 


function showOtTemplateText(id){
	var url = "otemplateNotAvailableSlot?id="+id+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showOtTemplateTextRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function showOtTemplateTextRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var hospitalcourse= nicEditors.findEditor( "operation_notes" ).getContent();	  
				hospitalcourse=hospitalcourse+req.responseText;        
				nicEditors.findEditor('operation_notes').setContent(hospitalcourse);
				//nicEditors.findEditor('otnotes').setContent(req.responseText);
			}
	}
}



function chkSpeciality() {
	
	 
		  
		  jAlert('error', 'Please select Admitting Consultant!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	
}	

var selected='0';

function searchdiagnosis(d){
	
	
	if(d==''){
		
	} else {
		
		/*var url="getdiagnosisIpd?search="+d+"&selected="+selected+"";*/
		//  16 April 2018 diagnosis
		var url="getdiagnosisBookAppointmentAjax?search="+d+"&selected="+selected+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = searchdiagnosisRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);    	
	}
	
}

function searchdiagnosisRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		 
			   document.getElementById("searchDiagnosis").innerHTML=req.responseText;
		}
	}
}


function setCheckedData(d){
	
	if(d.checked==true){
		if(document.getElementById("jsondiagnosis")){
			if(document.getElementById("jsondiagnosis").value=='1'){
				loadolddataofDiagnosis(d.value);
			}
			  
		}
		
		selected=selected+","+d.value;
		setAllDiagosisJSON();
	} 
}


function reoveThisRow(obj,d){
	
	selected= selected.replace(d,'0');
	deleteThisCondition(obj);
}

function deleteThisCondition(r){
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("searchDiagnosis").deleteRow(i);
}



function setAllDiagosis(){
	
	/*var url="setalldiagnosisIpd?selected="+selected+"";*/
	var url="setalldiagnosisBookAppointmentAjax?selected="+selected+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAllDiagosisRequest;
    req.open("GET", url, true); 
              
    req.send(null);    	
	
}	 

function setAllDiagosisRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		 
			   document.getElementById("searchDiagnosis").innerHTML=req.responseText;
		}
	}
}


function savediagnosisfast(){
	
	var condition= document.getElementById("newdiagnosis").value;
	
	if(condition=='' || condition==' '){
		 
		 jAlert('error', 'Diagnosis will not empty!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		 
	} else {
		var url="savediagnosisfastIpd?condition="+condition+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = savediagnosisfastRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);   
	}
	
	
}
function savediagnosisfastRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			selected=selected+","+req.responseText;
			setAllDiagosisJSON();
		}
	}
}

function getOTSurgicaltemplate(id) {
	 
    var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getOTSurgicaltemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function getOTSurgicaltemplateRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var suggestedtrtment= nicEditors.findEditor( "operation_notes" ).getContent();	  
				suggestedtrtment=suggestedtrtment+req.responseText;        
				nicEditors.findEditor('operation_notes').setContent(suggestedtrtment);	  	          
         }
	}	 
}

function submitaddmissionFor(){
	 
	 var ipdid= document.getElementById("editselectedid").value;
	 var clientid= document.getElementById("clientid").value;
	 //data
	 var addmissionfor =  nicEditors.findEditor( "addmissionfor" ).getContent(); 
	 var alergies= nicEditors.findEditor( "alergies" ).getContent();
	 var packagename=  nicEditors.findEditor( "packagename" ).getContent();
	 var chiefcomplains=  nicEditors.findEditor( "chiefcomplains" ).getContent();
	 var presentillness=  nicEditors.findEditor( "presentillness" ).getContent();
	 var onexamination=  nicEditors.findEditor( "onexamination" ).getContent();
	 var pasthistory=  nicEditors.findEditor( "pasthistory" ).getContent();
	 var familyhist=  nicEditors.findEditor( "familyhist" ).getContent();
	 var personalhist=  nicEditors.findEditor( "personalhist" ).getContent();

	 
	 var surgicalnotes=  nicEditors.findEditor( "surgicalnotes" ).getContent();
	 var suggestedtrtment=  nicEditors.findEditor( "suggestedtrtment" ).getContent();
	 var earlierinvest=  nicEditors.findEditor( "earlierinvest" ).getContent();
	 var birthhist=   nicEditors.findEditor( "birthhist" ).getContent();
	var diethist=  nicEditors.findEditor( "diethist" ).getContent();
	var emmunizationhist = nicEditors.findEditor( "emmunizationhist" ).getContent();
	 var developmenthist =   nicEditors.findEditor( "developmenthist" ).getContent();
	 var dataObj= {
	   "addmissionfor":""+addmissionfor+"",
	   "alergies":""+alergies+"",
	   "packagename":""+packagename+"",
	   "chiefcomplains":""+chiefcomplains+"",
	   "presentillness":""+presentillness+"",
	   "onexamination":""+onexamination+"",
	   "pasthistory":""+pasthistory+"",
	   "familyhist":""+familyhist+"",
	   "personalhist":""+personalhist+"",
	   "surgicalnotes":""+surgicalnotes+"",
	   "suggestedtrtment":""+suggestedtrtment+"",
	   "earlierinvest":""+earlierinvest+"",
	   "birthhist":""+birthhist+"",
	   "diethist":""+diethist+"",
	   "emmunizationhist":""+emmunizationhist+"",
	   "developmenthist":""+developmenthist+"",
	   
	   "ipdid" : "" + ipdid + "",
	   "clientid" : "" + clientid + "",
	   
	   };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "saveTempIPDDataBookAppointmentAjax",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  

		  
	    },
	  error : function(result) {
	   console.log("error");
	  }
	 });
}


function getIPDTempData(){
	  
	  var ipdid= document.getElementById("editselectedid").value;
	  var clientid= document.getElementById("clientid").value;
	  
	  var dataObj={
	    
	    "ipdid" : "" + ipdid + "",
	    "clientid" : "" + clientid + "",
	  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "getIPDTempDataBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {

		   
		   
		   var addmissionfor= data.addmissionfor;
		   var alergies= data.alergies;
		   var packagename= data.packagename;
		   var chiefcomplains= data.chiefcomplains;
		   var presentillness= data.presentillness;
		   var onexamination= data.onexamination;
		   var pasthistory= data.pasthistory;
		   var familyhist= data.familyhist;
		   var surgicalnotes= data.surgicalnotes;
		   var suggestedtrtment= data.suggestedtrtment;
		   var earlierinvest= data.earlierinvest;
		
			   
			   
		   nicEditors.findEditor( "addmissionfor" ).setContent(data.addmissionfor); 
		   nicEditors.findEditor( "alergies" ).setContent(data.alergies); 
		   nicEditors.findEditor( "packagename" ).setContent( data.packagename); 
		   nicEditors.findEditor( "chiefcomplains" ).setContent( data.chiefcomplains); 
		   nicEditors.findEditor( "presentillness" ).setContent( data.presentillness);
		   nicEditors.findEditor( "onexamination" ).setContent(data.onexamination); 
		   nicEditors.findEditor( "pasthistory" ).setContent(data.pasthistory); 
		   nicEditors.findEditor( "familyhist" ).setContent(data.familyhist); 
		   nicEditors.findEditor( "surgicalnotes" ).setContent( data.surgicalnotes); 
		   nicEditors.findEditor( "suggestedtrtment" ).setContent( data.suggestedtrtment); 
		   nicEditors.findEditor( "earlierinvest" ).setContent(data.earlierinvest); 
		   nicEditors.findEditor( "birthhist" ).setContent(data.birthhist); 
		   nicEditors.findEditor( "diethist" ).setContent(data.diethist); 
		   nicEditors.findEditor( "developmenthist" ).setContent(data.developmenthist); 
		   nicEditors.findEditor( "emmunizationhist" ).setContent(data.emmunizationhist); 
		   
		   console.log(data);
	     },
	   error : function(result) {
	    console.log("error");
	   }
	  });
	   
	              
	 
	 }





function savediagnosisfastJSON(){
	 
	
	var condition= document.getElementById("newdiagnosis").value;
	var conditiontext = condition;
	if(condition=='' || condition==' '){
		 
		 jAlert('error', 'Diagnosis will not empty!', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		 
	}
	else{
		
		
		  var dataObj={
				    
				    "condition" : "" + condition + "",
				   
				  };
				  var data1 =  JSON.stringify(dataObj);
				  $.ajax({

				   url : "savediagnosisfastJSONBookAppointmentAjax",
				   data : data1,
				   dataType : 'json',
				   contentType : 'application/json',
				   type : 'POST',
				   async : true,
				   // beforeSend: function () { LockScreen(); },
				   success : function(data) {
					   var condition= data.condition;
					   if(condition==0){
						   jAlert('error', 'Diagnosis already exist!', 'Error Dialog');
							
							setTimeout(function() {
								$("#popup_container").remove();
								removeAlertCss();
							}, alertmsgduration);
					   }else{
//					   if (document.getElementById('addmissionfor')) {
//						   nicEditors.findEditor( "addmissionfor" ).setContent(data.condition); 
//					   }
				   }
					   selected=selected+","+data.condition;
					   //  21 May 2018
					   setAllDiagosisJSON();
					   //searchdiagnosisJSON(conditiontext);
					   
					    },
				   error : function(result) {
				    console.log("error in saving diagnosis");
				   }
				  });
				   
		
	}
}   
function setAllDiagosisJSON(){
	
	  var dataObj={
			    
			    "selected" : "" + selected + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "setAllDiagosisJSONBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchDiagnosis").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving diagnosis");
	   }
	  });
	
}
function searchdiagnosisJSON(d){
	
	
	if(d==''){
		
	} else {
		
		
  var dataObj={
		    "search" : "" + d + "",
			    "selected" : "" + selected + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "getdiagnosisJSONBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchDiagnosis").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving diagnosis");
	   }

	  });
	}
	}

function searchdiagnosisproJSON(d){
	
	
	if(d==''){
		
	} else {
		
		
  var dataObj={
		    "search" : "" + d + "",
			    "selected" : "" + selected + "",
			   
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "getprodiagnosisJSONBookAppointmentAjax",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var condition= data.responsej;
		   document.getElementById("searchDiagnosis").innerHTML=data.responsej;
		   
		   
		    },
	   error : function(result) {
	    console.log("error in saving diagnosis");
	   }

	  });
	}
	}




function getemergencytemplate(id){
var url="getipdtemplateCommonnew?id="+id+"";
if (window.XMLHttpRequest) {
req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange = getemergencytemplateRequest;
req.open("GET", url, true); 
          
req.send(null);
}

function getemergencytemplateRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
				          
			var discadvnotes= nicEditors.findEditor( "emercontdetail" ).getContent();	  
			discadvnotes=discadvnotes+req.responseText;        
			nicEditors.findEditor('emercontdetail').setContent(discadvnotes);	  	          
     }
}	 
}



function gettreatmentttemplate(id){

	var url="getipdtemplateCommonnew?id="+id+"";
if (window.XMLHttpRequest) {
req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange = gettreatmentttemplateRequest;
req.open("GET", url, true); 
          
req.send(null);
}

function gettreatmentttemplateRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
				          
			var treatmentgvn= nicEditors.findEditor( "treatmentgiven" ).getContent();	
			var newdata= req.responseText;
			if(newdata=="null"){
				newdata="";
			}
			treatmentgvn=treatmentgvn+" "+newdata;        
			 nicEditors.findEditor( "treatmentgiven" ).setContent(treatmentgvn);	     
     }
}	 
}




function getkunalFdiagnosistemp(id){

	var url="getipdtemplateCommonnew?id="+id+"";
if (window.XMLHttpRequest) {
req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange = getkunalFdiagnosistempRequest;
req.open("GET", url, true); 
          
req.send(null);
}

function getkunalFdiagnosistempRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
				          
			var discharge_default= nicEditors.findEditor( "kunalfinaldiagnosis" ).getContent();	  
			discharge_default=discharge_default+req.responseText;        
			nicEditors.findEditor('kunalfinaldiagnosis').setContent(discharge_default);	  	
     }
}	 
}

function getkunalmedtext(id){

	var url="getipdtemplateCommonnew?id="+id+"";
if (window.XMLHttpRequest) {
req = new XMLHttpRequest();
}
else if (window.ActiveXObject) {
	isIE = true;
	req = new ActiveXObject("Microsoft.XMLHTTP");
}   
           
req.onreadystatechange = getkunalmedtextRequest;
req.open("GET", url, true); 
          
req.send(null);
}

function getkunalmedtextRequest(){
if (req.readyState == 4) {
	if (req.status == 200) {
				          
			var discharge_default= nicEditors.findEditor( "kunal_manual_medicine_text" ).getContent();	  
			discharge_default=discharge_default+req.responseText;        
			nicEditors.findEditor('kunal_manual_medicine_text').setContent(discharge_default);	  	
     }
}	 
}
var it="";
function saveIpdTemplates(id,obj){
	var data='';
	var name='';
	it=id;
	if(id=='7'){
		name=document.getElementById('treatmentgiventempnmae').value;
		data=nicEditors.findEditor('treatmentgiven').getContent();
	}else if(id=='12'){
		name=document.getElementById('emercontdetailtemplatename').value;
		data=nicEditors.findEditor('emercontdetail').getContent();
	}else if(id=='2'){
		name=document.getElementById('presentillnesstempname').value;
		data=nicEditors.findEditor('presentillness').getContent();
	}else if(id=='11'){
		name=document.getElementById('operativetempname').value;
		data=nicEditors.findEditor('operation_notes').getContent();
	}else if(id=='9'){
		name=document.getElementById('hospitalcoursetempname').value;
		data=nicEditors.findEditor('hospitalcourse').getContent();
	}else if(id==''){
		name=document.getElementById('investigationtempname').value;
		data=nicEditors.findEditor('investigation').getContent();
	}else if(id=='16'){
		name=document.getElementById('findingondistempname').value;
		data=nicEditors.findEditor('finddis').getContent();
	}else if(id=='10'){
		name=document.getElementById('nursingadvicetempname').value;
		data=nicEditors.findEditor('discadvnotes').getContent();
	}else if(id=='1'){
		name=document.getElementById('chiefcomplatetempname').value;
		data=nicEditors.findEditor('chiefcomplains1').getContent();
	}else if(id=='8'){
		name=document.getElementById('disdefaulttempname').value;
		data=nicEditors.findEditor('discharge_default').getContent();
	}else if(id=='6'){
		name=document.getElementById('loctempname').value;
		data=nicEditors.findEditor('onexaminationlok').getContent();
	}
	
	if(name==''){
		jAlert('error', 'Name the template', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else
	if(data==''||name==''){
		jAlert('error', 'There is an error', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} else {

		obj.style.visibility = 'hidden'; 
		
  var dataObj={
		    "dataa" : "" + data + "",
			    "id" : "" + id + "",
			    "name" : "" + name + "",
			  };
	  var data1 =  JSON.stringify(dataObj);
	  $.ajax({

	   url : "dynamictemplatesaveCommonnew",
	   data : data1,
	   dataType : 'json',
	   contentType : 'application/json',
	   type : 'POST',
	   async : true,
	   // beforeSend: function () { LockScreen(); },
	   success : function(data) {
		   var x= data.noob;
		   obj.style.visibility = 'visible';
		   
			  
		
		   alert("saved");
		   refreshIPTemplate(it);
		    },
	   error : function(result) {
	    console.log("error in saving ");
	   }

	  });
	}

}
var tempidd="";
function refreshIPTemplate(listid){
	tempidd=listid;
	var url="refreshAjaxipTemplateListCommonnew?reqtempid="+listid+"&tempid="+tempidd+"";
	if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	           
	req.onreadystatechange =refreshIPTemplateRequest;
	req.open("GET", url, true); 
	          
	req.send(null);
}
 

function refreshIPTemplateRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;  
			if(tempidd=='7'){
				document.getElementById('ttreat').innerHTML=data;	
			}else if(tempidd=='12'){
				document.getElementById('emem').innerHTML=data;	
			}else if(tempidd=='2'){
				document.getElementById('pllist').innerHTML=data;	
			}else if(tempidd=='11'){
				document.getElementById('ottempl').innerHTML=data;	
			}else if(tempidd=='9'){
				document.getElementById('hslist').innerHTML=data;	
			}else if(tempidd==''){
				document.getElementById('invstl').innerHTML=data;	
			}else if(tempidd=='16'){
				document.getElementById('findingl').innerHTML=data;	
			}else if(tempidd=='10'){
				document.getElementById('disadvl').innerHTML=data;	
			}else if(tempidd=='1'){
				document.getElementById('cfl').innerHTML=data;	
			}else if(tempidd=='8'){
				document.getElementById('notesl').innerHTML=data;	
			}
			else if(tempidd=='6'){
				document.getElementById('locexa').innerHTML=data;	
			}
			
		}
		}	
}


function loadolddataofDiagnosis(diagid){
	var cli=document.getElementById('clientid').value;
	var url="loadolddataofdiagnosisCommonnew?diagid="+diagid+"&clientid="+cli;
	if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	           
	req.onreadystatechange =loadolddataofDiagnosisRequest;
	req.open("GET", url, true); 
	          
	req.send(null);
}


function loadolddataofDiagnosisRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;  
			document.getElementById('diagdiv').innerHTML=data;
			$('#jsondiamodal').modal( "show" );	
			}
		}
}




function setdiagnosisIpdid(){
	
	
			document.getElementById('diagnosisipdid').value=$("input:radio[name=diagipdid]:checked").val();	
	
	$('#jsondiamodal').modal( "hide" );
}


function setsecondaryCon(){
	var dr="";
	var i=0;
	 $('.doctors').each(function() { 
			if(this.checked){
				var id=this.value;
				if(i==0){
					dr=dr+''+document.getElementById('lok'+id).innerHTML;
				}else{
					dr=dr+'<br>'+document.getElementById('lok'+id).innerHTML;
				}
				
				i++;
			}
									
		});
	 document.getElementById('ttt').innerHTML=dr;
}

function setrefereddoc(){
	var dr="";
	var i=0;
	 $('.refdoctors').each(function() { 
			if(this.checked){
				var id=this.value;
				if(i==0){
					dr=dr+''+document.getElementById('reflok'+id).innerHTML;
				}else{
					dr=dr+'<br>'+document.getElementById('reflok'+id).innerHTML;
				}
				
				i++;
			}
									
		});
	 document.getElementById('ref').innerHTML=dr;
	 document.getElementById('referedby').value=dr;
	 
}

function alreadyadmit(val){
	document.getElementById('saveandprintadmission').value = val;
	var bedid=document.getElementById('bedid').value
	var url="alreadybookedBookAppointmentAjax?bedid="+bedid;
	if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	           
	req.onreadystatechange =alreadyadmitRequest;
	req.open("GET", url, true); 
	          
	req.send(null);
	
}
function alreadyadmitRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;  
			if(data==1){
				jAlert('error', 'Bed is already Booked', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				savevalidate();
			}
			}
		}
}

function removeMedicineDisc(r,id){
	 
	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("priscTable").deleteRow(i);
	
    removeTHisMedicine(id);	
	
}

function removeTHisMedicine(id){
	var url = "removemedicineBookAppointmentAjax?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = removeTHisMedicineRequest;
    req.open("GET", url, true); 
    
    req.send(null);
}

function removeTHisMedicineRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		    
		}
	}

}

function updatealreadyadmit(val){
	document.getElementById('saveandprintadmission').value = val;
	var bedid=document.getElementById('bedid').value
	var origbedid=document.getElementById('origbedid').value
	if(bedid!=origbedid){
		var url="alreadybookedBookAppointmentAjax?bedid="+bedid;
		if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		           
		req.onreadystatechange =updatealreadyadmitRequest;
		req.open("GET", url, true); 
		          
		req.send(null);
	
	}else{
		savevalidate();
	}
}
function updatealreadyadmitRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;  
			if(data==1){
				jAlert('error', 'Bed is already Booked', 'Error Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				savevalidate();
			}
			}
		}
}

function getspecialityData(val){
	var url="getspecialitydataBookAppointmentAjax?val="+val;
	if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
	           
	req.onreadystatechange =getspecialityDataRequest;
	req.open("GET", url, true); 
	          
	req.send(null);
	
}
function getspecialityDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;  
			document.getElementById('department').className="";
			document.getElementById('department').value=data;
			document.getElementById('department').className="form-control chosen-select";
			$("#department").trigger("chosen:updated");
		  	$(".chosen-select").chosen({allow_single_deselect: true});
		}
	}
}


function getondiet(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getondietRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getondietRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var ondiet= nicEditors.findEditor( "ondiet" ).getContent();	  
				ondiet=ondiet+req.responseText;        
				nicEditors.findEditor('ondiet').setContent(ondiet);	  	          
         }
	}	 
}

function getphysio(id){

		var url="getipdtemplateCommonnew?id="+id+"";
    if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getphysioRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function getphysioRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
					          
				var physio= nicEditors.findEditor( "physio" ).getContent();	  
				physio=physio+req.responseText;        
				nicEditors.findEditor('physio').setContent(physio);	  	          
         }
	}	 
}


function savenewdoctor() {
	var doctor = document.getElementById("adddoctor").value;
	var share = document
			.getElementById("share").value;
	if (doctor == '') {
		jAlert('error', 'Please enter doctor name', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	} //else if (addproceduredescription == '0') {
		//jAlert('error', 'Please Select Department', 'Error Dialog');
		//setTimeout(function() {
			//$("#popup_container").remove();
			//removeAlertCss();
		//}, alertmsgduration);
	//} 
	else {
		var dataObj = {
			"doctor" : "" + doctor + "",
			"share" : "" + share + ""
		};
		var data1 = JSON.stringify(dataObj);

		$.ajax({

			url : "addnewdoctorCommonnew",
			data : data1,
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : true,
			// beforeSend: function () { LockScreen(); },
			success : function(res) {
				$( "#adddoctorpopup" ).modal( "hide" );	
				
				showdoctorlist();
			},
			error : function(result) {
				console.log("error");
			}
		});

	}
}

function showdoctorlist(){
		var url = "doctorlistNotAvailableSlot";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showotprocedureListRequest;
		    req.open("GET", url, true); 
		    
		    
		              
		    req.send(null);
	}
	
	function showotprocedureListRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('otprocedurediv').innerHTML = req.responseText;
				
				 $("#otprocedureplaned").trigger("chosen:updated");
			  	  $(".chosen").chosen({allow_single_deselect: true});
			}
		}
	}
	
	
	
	function updateadmissionid(ipdseqno){
		
		var admissionid=document.getElementById("sramadmtid").value;
		var url = "updateAdmissionidCharges?admissionid="+admissionid+"&ipdseqno="+ipdseqno+"";
				  
				 
				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = updateadmissionidRequest;
				    req.open("GET", url, true); 
				    
				    
				              
				    req.send(null);
			}
			
			function updateadmissionidRequest(){
				if (req.readyState == 4) {
					if (req.status == 200) {
						window.location.reload();
						
					}
				}
			}
			
			

				function sendmail(){
					
					
					
					$( "#sendEmailPopUp2" ).modal( "show" );
				}
				
				
				
				
				function sendPdfMailJson() {
					var cc = document.getElementById('ccEmail').value;
					var to = document.getElementById('thirdPartEmail').value;
					var subject = document.getElementById('subject').value;
					var notes = document.getElementById('emailBody').value;;
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