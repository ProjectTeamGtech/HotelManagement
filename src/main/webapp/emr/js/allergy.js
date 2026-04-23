function addAllergy(id){
	
	document.getElementById('allergyList').style.display = 'none';
	document.getElementById('addallergy').style.display = '';
	document.getElementById('viewall').style.display = 'none';
	document.getElementById('editall').style.display = 'none';
	//document.getElementById('allergyTitle').innerHTML = "Add Allergy";
	document.getElementById('addall').innerHTML = '';
	
}

function closeAddAllergy(){
	
	document.getElementById('allergyList').style.display = '';
	document.getElementById('addallergy').style.display = 'none';
	document.getElementById('viewall').style.display = 'none';
	document.getElementById('editall').style.display = 'none';
	//document.getElementById('allergyTitle').innerHTML = 'Allergy';
	document.getElementById('addall').innerHTML = '<a class="width-full btn btn-primary" href="javascript:void(0)" onclick="addAllergy(\'addallergy\')">Add New</a>';
}

//edit Allergy

function editAllergy(selectedid,divid){
	
	editallid = selectedid;
	
	document.getElementById('allergyList').style.display = 'none';
	document.getElementById('addallergy').style.display = 'none';
	document.getElementById('viewall').style.display = 'none';
	document.getElementById('editall').style.display = '';	
	document.getElementById(divid).style.display = '';
	//document.getElementById('allergyTitle').innerHTML = "Modify Allergy";
	document.getElementById('addall').innerHTML = '';
  
  viewAllergyAjax(selectedid);
	
}

function saveAllergyAjax(){
	
	var allergy = document.getElementById('allergyText').value;
	var apmtid = document.getElementById('selectedid').value;
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	//var pid = document.getElementById('diaryUser').value;	
	if(allergy == ""){
		 jAlert('error', 'Please add allergy!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
	var url = "saveAllergyEmr?selectedPatientId="+selectedPatientId+"&allergy="+allergy+"&apmtid="+apmtid+"&pid="+pid+"";

	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveAllergyAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
	return true;
}


function saveAllergyAjaxRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('allergyList').innerHTML = req.responseText;
			document.getElementById('allergyText').value = "";	
			closeAddAllergy();
		}
}
}


//view and close Prescription
function viewAllergy(selectedid,divid){
	
	document.getElementById('allergyList').style.display = 'none';
	document.getElementById('addallergy').style.display = 'none';
	document.getElementById('viewall').style.display = '';
	document.getElementById('editall').style.display = 'none';	
	document.getElementById(divid).style.display = '';
	//document.getElementById('allergyTitle').innerHTML = "View Allergy";
	document.getElementById('addall').innerHTML = '';
  
	viewAllergyAjax(selectedid);
	
}

function viewAllergyAjax(selectedid){
	
	var url = "viewAllergyEmr?selectedid="+selectedid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = viewAllergyAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function viewAllergyAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('viewalltext').value = req.responseText;
			document.getElementById('editalltext').value = req.responseText;
			
		}
	}
}


//editsave Allergy

function editsaveAllergyAjax(){
	
	var allergy = document.getElementById('editalltext').value;
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	//var pid = document.getElementById('diaryUser').value;
	if(allergy == ""){
		 jAlert('error', 'Please add allergy!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
	var url = "editsaveAllergyEmr?selectedid="+editallid+"&allergy="+allergy+"&selectedPatientId="+selectedPatientId+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editsaveAllergyAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}


function editsaveAllergyAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('allergyList').innerHTML = req.responseText;
			document.getElementById('editalltext').value = "";	
			closeAddAllergy();
			
		}
	}
	
}



function deleteAllergyAjax(selectedid){
	
	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {	
	var url = "deleteAllergyEmr?selectedid="+selectedid+"&selectedPatientId="+selectedPatientId+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deleteAllergyAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	    return true;
	  }
	else
	  {
	  return false;
	  }
}

function deleteAllergyAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('allergyList').innerHTML = req.responseText;
			closeAddAllergy();
		}
	}
	
}

