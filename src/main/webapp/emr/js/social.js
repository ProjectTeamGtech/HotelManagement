



//add and close social history
function addSocialHistory(id){
	
	document.getElementById('socialHistoryList').style.display = 'none';
	document.getElementById('viewsh').style.display = 'none';
	
	document.getElementById('addsocialHistory').style.display = '';
	//document.getElementById('socialTitle').innerHTML = "Add Social History";
    document.getElementById('addsh').style.display= 'none';
}

function closeAddSocialHistory(){
	
	document.getElementById('socialHistoryList').style.display='';
	document.getElementById('addsocialHistory').style.display = 'none';
	document.getElementById('viewsh').style.display = 'none';
	document.getElementById('editsh').style.display = 'none';
	document.getElementById('addsh').style.display = '';
	document.getElementById('addsh').innerHTML = '<a class="width-full btn btn-primary" href="javascript:void(0)" onclick="addSocialHistory(\'addsocialHistory\')">Add New</a>';
	//document.getElementById('socialTitle').innerHTML = "Social History";
}



//view and close social History
function viewSocialHistory(selectedid,divid){
	
	document.getElementById('socialHistoryList').style.display = 'none';
	document.getElementById('addsocialHistory').style.display = 'none';
	document.getElementById('viewsh').style.display = '';
	
	document.getElementById(divid).style.display = '';
	//document.getElementById('socialTitle').innerHTML = "View Social History";
  document.getElementById('addsh').innerHTML = '';
  
  viewSocialHistoryAjax(selectedid);
	
}

//edit social history

function editSocialHistory(selectedid,divid){
	
	editshid = selectedid;
	
	document.getElementById('socialHistoryList').style.display = 'none';
	document.getElementById('addsocialHistory').style.display = 'none';
	document.getElementById('viewsh').style.display = 'none';
	document.getElementById('editsh').style.display = '';
	
	document.getElementById(divid).style.display = '';
//	document.getElementById('socialTitle').innerHTML = "Modify Social History";
  document.getElementById('addsh').innerHTML = '';
  
  viewSocialHistoryAjax(selectedid);
	
}

function viewSocialHistoryAjax(selectedid){
	
	var url = "viewSocialEmr?selectedid="+selectedid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = viewSocialHistoryAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function viewSocialHistoryAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('viewshtext').value = req.responseText;
			document.getElementById('editshtext').value = req.responseText;
			
		}
	}
}

function resetField(){
	document.getElementById('socialHistoryText').value = "";	
}

function saveSocialHistoryAjax(){
	
	var socialhistory = document.getElementById('socialHistoryText').value;
	if(socialhistory == ""){
		 jAlert('error', 'Please add Social History!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
	var apmtid = document.getElementById('selectedid').value;
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	//var pid = document.getElementById('diaryUser').value;
	
	var url = "saveSocialEmr?selectedPatientId="+selectedPatientId+"&socialhistory="+socialhistory+"&apmtid="+apmtid+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveSocialHistoryAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}

function saveSocialHistoryAjaxRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('socialHistoryList').innerHTML = req.responseText;
				document.getElementById('socialHistoryText').value = "";
				closeAddSocialHistory();
			}
	}
}

//editsave social history

function editsaveSocialHistoryAjax(){
	var apmtid = document.getElementById('selectedid').value;
	var socialhistory = document.getElementById('editshtext').value;
	if(socialhistory == ""){
		 jAlert('error', 'Please add Social History!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	//var pid = document.getElementById('diaryUser').value;
	
	var url = "editsaveSocialEmr?selectedid="+editshid+"&socialhistory="+socialhistory+"&selectedPatientId="+selectedPatientId+"&pid="+pid+"&apmtid="+apmtid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editsaveSocialHistoryAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}


function editsaveSocialHistoryAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('socialHistoryList').innerHTML = req.responseText;
			document.getElementById('editshtext').value = "";
			closeAddSocialHistory();
			
		}
	}
	
}

function deleteSocialHistory(selectedid){
	var apmtid = document.getElementById('selectedid').value;
	//var pid = document.getElementById('diaryUser').value;
	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {
	var url = "deleteSocialEmr?selectedid="+selectedid+"&selectedPatientId="+selectedPatientId+"&pid="+pid+"&apmtid="+apmtid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deleteSocialHistoryRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	  }
	else
	  {
	  return false;
	  }
}

function deleteSocialHistoryRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('socialHistoryList').innerHTML = req.responseText;
			closeAddSocialHistory();
		}
	}
	
}

