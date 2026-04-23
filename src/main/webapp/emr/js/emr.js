var editmhid = 0;
var editSelectedid = 0;

function emrdata(){
	
	document.getElementById('emrfrm').submit();
}

function setAdminEmr(){
	
	
	document.getElementById('adminemrFrm').submit();
}

var prevId = 0;

function selectedUser(clientid,apmtid,clientname,dob,age,gender){		
	//alert(apmtid);
	var url = "checkReportFieldEmr?apmtid="+apmtid+"&clientid="+clientid+"";	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = checkReportFieldRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
    
}

function checkReportFieldRequest(){
	
	if (req.readyState == 4) {
		
		var chk = 0; 
			if (req.status == 200) {
				var reportField = req.responseText;
				//alert(reportField);
				if(reportField == 'true')
				{					
					chk = 1;
				}
				if(chk == 1)
				{
					 document.getElementById('errorReport').innerHTML="";
					 var report = document.createElement("label");
					 report.innerHTML = "Please send an email to Partitioner!!";
				     document.getElementById('errorReport').appendChild(report);
				     document.getElementById('report').style.display = '';
				     document.getElementById('reportdoc').style.display = '';
				     chk = 1;
				     return false;
			     }
			     else
			     {
			    	 document.getElementById('errorReport').innerHTML="";
			    	 document.getElementById('report').style.display = 'none';
				     document.getElementById('reportdoc').style.display = 'none';
			    	 chk = 0;
			    	 return true;
			     }		
				
			}
			
	}
	
}

//add and close medical history
function addMrdicalHistory(id){
	
	document.getElementById('shoemrHisory').style.display = 'none';
	document.getElementById('addemrHisory').style.display = 'none';
	document.getElementById('viewmh').style.display = 'none';
	
	document.getElementById(id).style.display = '';
	//document.getElementById('mhTitle').innerHTML = "Add Medical History";
    document.getElementById('addmh').innerHTML = '';
}

function closeAddMedicalHistory(){
	
	document.getElementById('shoemrHisory').style.display = '';
	document.getElementById('addemrHisory').style.display = 'none';
	document.getElementById('viewmh').style.display = 'none';
	document.getElementById('editmh').style.display = 'none';
	document.getElementById('addmh').innerHTML = '<a class="width-full btn btn-primary" href="javascript:void(0)" onclick="addMrdicalHistory(\'addemrHisory\')">Add New</a>'
	//document.getElementById('mhTitle').innerHTML = "Medical History";
}


//view and close medical History
function viewMedicalHistory(selectedid,divid){
	
	document.getElementById('shoemrHisory').style.display = 'none';
	document.getElementById('addemrHisory').style.display = 'none';
	document.getElementById('viewmh').style.display = 'none';
	
	document.getElementById(divid).style.display = '';
	//document.getElementById('mhTitle').innerHTML = "View Medical History";
    document.getElementById('addmh').innerHTML = '';
    
    viewMedicalHistoryAjax(selectedid);
	
}

//edit medical history

function editMedicalHistory(selectedid,divid){
	
	editmhid = selectedid;
	
	document.getElementById('shoemrHisory').style.display = 'none';
	document.getElementById('addemrHisory').style.display = 'none';
	document.getElementById('viewmh').style.display = 'none';
	document.getElementById('editmh').style.display = 'none';
	
	document.getElementById(divid).style.display = '';
	//document.getElementById('mhTitle').innerHTML = "Modify Medical History";
    document.getElementById('addmh').innerHTML = '';
    
    viewMedicalHistoryAjax(selectedid);
	
}




function viewMedicalHistoryAjax(selectedid){
	
	var url = "viewEmr?selectedid="+selectedid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = viewMedicalHistoryAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function viewMedicalHistoryAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('viewmhtext').value = req.responseText;
			document.getElementById('editmhtext').value = req.responseText;
			//document.getElementById('viewmhtext').value = "hello java world";
		}
	}
}


//editsave medical history
function editsaveMedicalHistoryAjax(){
	var medicalhistory = document.getElementById('editmhtext').value;
	if(medicalhistory == ""){
		 jAlert('error', 'Please add Medical History!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
	var clientid = document.getElementById('selectedPatientId').value;
	//var pid = document.getElementById('diaryUser').value;
	
	var url = "editsaveEmr?selectedid="+editmhid+"&medicalhistory="+medicalhistory+"&selectedPatientId="+selectedPatientId+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveMedicalHistoryAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}


function editsaveMedicalHistoryAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('shoemrHisory').innerHTML = req.responseText;
			document.getElementById('editmhtext').value = "";	
			closeAddMedicalHistory();
			
		}
	}
	
}


function deleteMedicalHistory(selectedid){
	//var pid = document.getElementById('diaryUser').value;
	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {	
	var url = "deleteEmr?selectedid="+selectedid+"&selectedPatientId="+selectedPatientId+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deleteMedicalHistoryRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	  }
	else
	  {
	  return false;
	  }
}

function deleteMedicalHistoryRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('shoemrHisory').innerHTML = req.responseText;
			closeAddMedicalHistory();
		}
	}
	
}

function saveMedicalHistoryAjax(){	
	
	var medicalhistory = document.getElementById('medicalHistoryText').value;	

	if(medicalhistory == ""){
		 jAlert('error', 'Please add Medical History!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
	var apmtid = document.getElementById('selectedid').value;
	var clientid = document.getElementById('selectedPatientId').value;
	//var pid = document.getElementById('diaryUser').value;
	
	var url = "saveEmr?selectedPatientId="+selectedPatientId+"&medicalhistory="+medicalhistory+"&apmtid="+apmtid+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveMedicalHistoryAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}

function saveMedicalHistoryAjaxRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('shoemrHisory').innerHTML = req.responseText;
				document.getElementById('medicalHistoryText').value = "";	
				closeAddMedicalHistory();
			}
	}
}

function showPopUp(){
var apmtid = document.getElementById('selectedid').value;
	var url = "showConsListEmr?selectedPatientId="+selectedPatientId+"&pid="+pid+"&apmtid="+apmtid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAllConsPopUpRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	function showAllConsPopUpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				document.getElementById("allConstation").innerHTML = req.responseText;
				
	         }
		}
	}

function sendmail(){
	
	var url = "getEmailPractionerEmr?pid="+pid+" ";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = sendMailAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function sendMailAjaxRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
			var email = req.responseText;
			document.getElementById('thirdPartEmail').value = email;
			$( "#sendEmailPopUp2" ).modal( "show" );
		}
}

	
	
}


function sendReportMail(id){
	
	var cc = document.getElementById('ccEmail').value;
	var to = document.getElementById('thirdPartEmail').value;
	var subject = document.getElementById('subject').value;
	var notes = document.getElementById('emailBody').value;
	//var notes = nicEditors.findEditor( "emailBody" ).getContent();	
	
	 var url = "emailReportEmr?to="+to+"&cc="+cc+"&subject="+subject+"&notes="+notes+" ";
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendPdfMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function sendPdfMailRequest(){
	
		if (req.readyState == 4) {
				if (req.status == 200) {
				
		            jAlert('success', 'Email Send Sucessfully!!', 'Success Dialog');
		            
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
		            
		            //tempAlert("Email Send Sucessfully", 5000);
		            $('#sendEmailPopUp').dialog( "close" );
		             document.getElementById('errorReport').innerHTML="";
			    	 document.getElementById('report').style.display = 'none';
				     document.getElementById('reportdoc').style.display = 'none';
		            
				}
		}
	
}

function setEmrType(){
	
	var emrType = document.getElementById('emrType').value;
	
	if(emrType == 'Reminder'){
		document.getElementById('reminderAddView').style.display = '';
		document.getElementById('reminderListView').style.display = '';
		document.getElementById('medicalAddView').style.display = 'none';
		document.getElementById('medicalListView').style.display = 'none';
		document.getElementById('prescAddView').style.display = 'none';
		document.getElementById('prescListView').style.display = 'none';
		document.getElementById('socialAddView').style.display = 'none';
		document.getElementById('socialListView').style.display = 'none';
		document.getElementById('allergyAddView').style.display = 'none';
		document.getElementById('allergyListView').style.display = 'none';
		document.getElementById('apptAddView').style.display = 'none';
		document.getElementById('apptListView').style.display = 'none';
	}
	else if(emrType == 'Appointment Schedule'){
		document.getElementById('apptAddView').style.display = '';
		document.getElementById('apptListView').style.display = '';
		document.getElementById('reminderAddView').style.display = 'none';
		document.getElementById('reminderListView').style.display = 'none';
		document.getElementById('medicalAddView').style.display = 'none';
		document.getElementById('medicalListView').style.display = 'none';
		document.getElementById('prescAddView').style.display = 'none';
		document.getElementById('prescListView').style.display = 'none';
		document.getElementById('socialAddView').style.display = 'none';
		document.getElementById('socialListView').style.display = 'none';
		document.getElementById('allergyAddView').style.display = 'none';
		document.getElementById('allergyListView').style.display = 'none';
	}
	else if(emrType == 'Medical History'){
		document.getElementById('medicalAddView').style.display = '';
		document.getElementById('medicalListView').style.display = '';
		document.getElementById('reminderAddView').style.display = 'none';
		document.getElementById('reminderListView').style.display = 'none';
		document.getElementById('prescAddView').style.display = 'none';
		document.getElementById('prescListView').style.display = 'none';
		document.getElementById('socialAddView').style.display = 'none';
		document.getElementById('socialListView').style.display = 'none';
		document.getElementById('allergyAddView').style.display = 'none';
		document.getElementById('allergyListView').style.display = 'none';
		document.getElementById('apptAddView').style.display = 'none';
		document.getElementById('apptListView').style.display = 'none';
	}
	else if(emrType == 'Prescription'){
		document.getElementById('prescAddView').style.display = '';
		document.getElementById('prescListView').style.display = '';
		document.getElementById('medicalAddView').style.display = 'none';
		document.getElementById('medicalListView').style.display = 'none';
		document.getElementById('reminderAddView').style.display = 'none';
		document.getElementById('reminderListView').style.display = 'none';
		document.getElementById('socialAddView').style.display = 'none';
		document.getElementById('socialListView').style.display = 'none';
		document.getElementById('allergyAddView').style.display = 'none';
		document.getElementById('allergyListView').style.display = 'none';
		document.getElementById('apptAddView').style.display = 'none';
		document.getElementById('apptListView').style.display = 'none';
	}
	else if(emrType == 'Social History'){
		document.getElementById('socialAddView').style.display = '';
		document.getElementById('socialListView').style.display = '';
		document.getElementById('prescAddView').style.display = 'none';
		document.getElementById('prescListView').style.display = 'none';
		document.getElementById('medicalAddView').style.display = 'none';
		document.getElementById('medicalListView').style.display = 'none';
		document.getElementById('reminderAddView').style.display = 'none';
		document.getElementById('reminderListView').style.display = 'none';
		document.getElementById('allergyAddView').style.display = 'none';
		document.getElementById('allergyListView').style.display = 'none';
		document.getElementById('apptAddView').style.display = 'none';
		document.getElementById('apptListView').style.display = 'none';
	}
	else if(emrType == 'Allergy'){
		document.getElementById('allergyAddView').style.display = '';
		document.getElementById('allergyListView').style.display = '';
		document.getElementById('socialAddView').style.display = 'none';
		document.getElementById('socialListView').style.display = 'none';
		document.getElementById('prescAddView').style.display = 'none';
		document.getElementById('prescListView').style.display = 'none';
		document.getElementById('medicalAddView').style.display = 'none';
		document.getElementById('medicalListView').style.display = 'none';
		document.getElementById('reminderAddView').style.display = 'none';
		document.getElementById('reminderListView').style.display = 'none';
		document.getElementById('apptAddView').style.display = 'none';
		document.getElementById('apptListView').style.display = 'none';
	}
}


function showEmrPopUp(){
	
	var url = "showListClient";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showEmrPopUpRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	function showEmrPopUpRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById("allTemplatePatient").innerHTML = req.responseText;
				
	         }
		}
	}
	
	function setPatientName(name,id,type,typeName){
		//deleteChargeAccountsTableAjax();
		document.getElementById("clientId").value = id;
		var url = "getFullNameClient?id="+id+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setPatientNameRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
		
		
		
	        
			
	}
	function setPatientNameRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var clientFullName = req.responseText;
			
				document.getElementById("client").value = clientFullName;
				$('#clientSearch').modal('hide');
				
				
				
			}
		}

	}
	
	function sendfollowupsms(docter, datenew1, clientid1,idnew){
		var url ="sendfollowupsmsClient?clientid="+clientid1+"&doctor="+docter+"&followdate="+datenew1+"&id="+idnew+"" ;
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		
		req.onreadystatechange = sendfollowupsmsRequest;
		 
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}	
	
	
	function sendfollowupsmsRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();    
			}}
	}
	
	
	function setStatusFollowup(status){
		var id=document.getElementById("followupid").value;
		var fdate= document.getElementById("followupdatenew").value;
		var url ="setFollowupsatusClient?id="+id+"&status="+status+"&fdate="+fdate ;
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		
		req.onreadystatechange = setStatusFollowupRequest;
		 
	    req.open("GET", url, true); 
	              
	    req.send(null);
	   
	}
	
	function setStatusFollowupRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();    
			}
			}
	}
	
	
	