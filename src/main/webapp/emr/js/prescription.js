function addPrescription(id){
	
	document.getElementById('prescriptionList').style.display = 'none';
	document.getElementById('addprescription').style.display = '';
	document.getElementById('viewpre').style.display = 'none';
	document.getElementById('editpre').style.display = 'none';	
	//document.getElementById(id).style.display = '';
	//document.getElementById('prescTitle').innerHTML = "Add Prescription";
    document.getElementById('addpresc').innerHTML = '';
}

function closeAddPrescription(){
	
	document.getElementById('prescriptionList').style.display = '';
	document.getElementById('addprescription').style.display = 'none';
	document.getElementById('viewpre').style.display = 'none';
	document.getElementById('editpre').style.display = 'none';	
	//document.getElementById('prescTitle').innerHTML = "Prescription";
    document.getElementById('addpresc').innerHTML = '<a class="width-full btn btn-primary" href="javascript:void(0)" onclick="addPrescription(\'addprescription\')">Add New</a>';
 	
}

//edit Prescription

function editPrescription(selectedid,divid){
	
	editpreid = selectedid;
	
	document.getElementById('prescriptionList').style.display = 'none';
	document.getElementById('addprescription').style.display = 'none';
	document.getElementById('viewpre').style.display = 'none';
	document.getElementById('editpre').style.display = '';	
	document.getElementById(divid).style.display = '';
	//document.getElementById('prescTitle').innerHTML = "Modify Prescription";
	document.getElementById('addpresc').innerHTML = '';
  
  viewPrescriptionAjax(selectedid);
	
}



function savePrescriptionAjax(){
	var prescription = document.getElementById('prescriptionText').value;
	var apmtid = document.getElementById('selectedid').value;
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	//var pid = document.getElementById('diaryUser').value;
	
	if(prescription == ""){
		 jAlert('error', 'Please add Prescription!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
		
	var url = "savePrescriptionEmr?selectedPatientId="+selectedPatientId+"&prescription="+prescription+"&apmtid="+apmtid+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = savePrescriptionAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}

function resetField(){
	document.getElementById('prescriptionText').value = "";	
}
function savePrescriptionAjaxRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('prescriptionList').innerHTML = req.responseText;
				document.getElementById('prescriptionText').value = "";	
				closeAddPrescription();
			}
	}
}

//view and close Prescription
function viewPrescription(selectedid,divid){
	
	document.getElementById('prescriptionList').style.display = 'none';
	document.getElementById('addprescription').style.display = 'none';
	document.getElementById('viewpre').style.display = '';
	document.getElementById('editpre').style.display = 'none';	
	document.getElementById(divid).style.display = '';
	//document.getElementById('prescTitle').innerHTML = "View Prescription";
	document.getElementById('addpresc').innerHTML = '';
  
	viewPrescriptionAjax(selectedid);
	
}

function viewPrescriptionAjax(selectedid){
	
	var url = "viewPrescriptionEmr?selectedid="+selectedid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = viewPrescriptionAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function viewPrescriptionAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('viewpretext').value = req.responseText;
			document.getElementById('editpretext').value = req.responseText;
			
		}
	}
}

//editsave Prescription

function editsavePrescriptionAjax(){
	
	var prescription = document.getElementById('editpretext').value;
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	var apmtid = document.getElementById('selectedid').value; 
	//var pid = document.getElementById('diaryUser').value;
	
	if(prescription == ""){
		 jAlert('error', 'Please add Prescription!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
	var url = "editsavePrescriptionEmr?selectedid="+editpreid+"&prescription="+prescription+"&selectedPatientId="+selectedPatientId+"&pid="+pid+"&apmtid="+apmtid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editsavePrescriptionAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}


function editsavePrescriptionAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('prescriptionList').innerHTML = req.responseText;
			document.getElementById('editpretext').value = "";	
			closeAddPrescription();
			
		}
	}
	
}

function deletePrescriptionAjax(selectedid){
	
	//var pid = document.getElementById('diaryUser').value;
	var apmtid = document.getElementById('selectedid').value; 
	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {
	var url = "deletePrescriptionEmr?selectedid="+selectedid+"&selectedPatientId="+selectedPatientId+"&pid="+pid+"&apmtid="+apmtid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deletePrescriptionAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	  }
	else
	  {
	  return false;
	  }
}

function deletePrescriptionAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('prescriptionList').innerHTML = req.responseText;
			closeAddPrescription();
		}
	}
	
}
