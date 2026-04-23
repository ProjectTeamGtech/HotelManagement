function addReminder(id){
	
	document.getElementById('reminderList').style.display = 'none';
	document.getElementById('addreminder').style.display = '';
	document.getElementById('viewrem').style.display = 'none';	
	//document.getElementById(id).style.display = '';
	//document.getElementById('remTitle').innerHTML = "Add Reminder";
    document.getElementById('addrem').innerHTML = '';
}

function closeAddReminder(){
	
	document.getElementById('reminderList').style.display='';
	document.getElementById('addreminder').style.display = 'none';
	document.getElementById('viewrem').style.display = 'none';
	document.getElementById('editrem').style.display = 'none';
	document.getElementById('addrem').innerHTML = '<a class="width-full btn btn-primary" href="javascript:void(0)" onclick="addReminder(\'addreminder\')">Add New</a>';
	//document.getElementById('remTitle').innerHTML = "Reminder";
}


function editReminder(selectedid,divid){
	editSelectedid = selectedid;
	document.getElementById('reminderList').style.display = 'none';
	document.getElementById('addreminder').style.display = 'none';
	document.getElementById('viewrem').style.display = 'none';
	document.getElementById('editrem').style.display = 'none';
	
	document.getElementById(divid).style.display = '';
	//document.getElementById('remTitle').innerHTML = "Edit Reminder";
    document.getElementById('addrem').innerHTML = '';
    
    viewReminderAjax(selectedid);
	
}

function viewReminder(selectedid,divid){
	document.getElementById('reminderList').style.display = 'none';
	document.getElementById('addreminder').style.display = 'none';
	document.getElementById('viewrem').style.display = '';	
	document.getElementById(divid).style.display = '';
	//document.getElementById('remTitle').innerHTML = "View Reminder";
    document.getElementById('addrem').innerHTML = '';
  
  viewReminderAjax(selectedid);
}

function viewReminderAjax(selectedid){
	
	var url = "viewReminderEmr?selectedid="+selectedid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = viewReminderAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function viewReminderAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			//alert( req.responseText);
			var data = req.responseText;
			var temp = data.split("//");
			var date = temp[0];
			//alert(date);
			var desc = temp[1];
			var t2 = date.split(" ");
			var time = t2[1];
			//alert(time);
			var d1= t2[0];
			//alert(d1);
			var temptime = time.split(":");
			var hh = temptime[0];
			//alert(hh);
			var mm = temptime[1];
			//alert(mm);
			document.getElementById('viewremindertext').value = desc;
			document.getElementById('viewalert').value = date+" "+time;
			document.getElementById('editremindertext').value = desc;
			document.getElementById('editalert').value = d1;
			document.getElementById('edithh').value = hh;
			document.getElementById('editmin').value = mm;
			
		}
	}
}

function resetField(){
	document.getElementById('reminderText').value = "";	
}

function saveReminderAjax(){
	var reminder = document.getElementById('reminderText').value;
	var apmtid = document.getElementById('selectedid').value;
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	var alert = document.getElementById('alert').value;
	var hh = document.getElementById('hh').value;
	var min = document.getElementById('min').value;
	//var pid = document.getElementById('diaryUser').value;
	
	if(reminder == "" && alert == ""){
		 jAlert('error', 'Please add Alert date and Description!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}
	else if(reminder == ""){
		jAlert('error', 'Please Add Description !!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(alert == ""){
		jAlert('error', 'Please select Alert date !!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else{
	
	var url = "saveReminderDetailsEmr?selectedPatientId="+selectedPatientId+"&reminder="+reminder+"&apmtid="+apmtid+"&alert="+alert+"&hh="+hh+"&min="+min+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveReminderAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}


function saveReminderAjaxRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				document.getElementById('reminderList').innerHTML = req.responseText;
				document.getElementById('reminderText').value = "";	
				closeAddReminder();
			}
	}
}

//editsave Reminder

function editsaveReminderAjax(){
	
	var reminder = document.getElementById('editremindertext').value;
	var editalert = document.getElementById('editalert').value;
	var edithh = document.getElementById('edithh').value;
	var editmin = document.getElementById('editmin').value;
	//var pid = document.getElementById('diaryUser').value;
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	
	if(reminder == "" && editalert == ""){
		 jAlert('error', 'Please add Note and Alert date !!', 'Error Dialog');
		 
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}
	else if(reminder == ""){
		jAlert('error', 'Please Add Note!!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(editalert == ""){
		jAlert('error', 'Please select Alert date!!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else{
	
	var url = "editsaveReminderEmr?selectedid="+editSelectedid+"&reminder="+reminder+"&selectedPatientId="+selectedPatientId+"&editalert="+editalert+"&edithh="+edithh+"&editmin="+editmin+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editsaveReminderAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}


function editsaveReminderAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('reminderList').innerHTML = req.responseText;
			document.getElementById('editremindertext').value = "";	
			closeAddReminder();
			
		}
	}
	
}

function deleteReminderAjax(selectedid){
	
	//var pid = document.getElementById('diaryUser').value;
	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {
	var url = "deleteReminderEmr?selectedid="+selectedid+"&selectedPatientId="+selectedPatientId+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deleteReminderAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	  }
	else
	  {
	  return false;
	  }
}

function deleteReminderAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('reminderList').innerHTML = req.responseText;
			closeAddReminder();
		}
	}
	
}


