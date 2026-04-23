

function addConsultationNote(id){
	
	document.getElementById('showconshistory').style.display = 'none';
	document.getElementById('addconshistory').style.display = '';
	//document.getElementById('viewcons').style.display = 'none';
	document.getElementById('editconsult').style.display = 'none';
	document.getElementById(id).style.display = '';
	document.getElementById('consTitle').innerHTML = "Add Consultation Note";
    document.getElementById('addcons').innerHTML = '';
    
    
}






function closeConsultationNote(){
	
	document.getElementById('showconshistory').style.display = '';
	document.getElementById('addconshistory').style.display = 'none';
	//document.getElementById('viewcons').style.display = 'none';
	document.getElementById('editconsult').style.display = 'none';
	document.getElementById('addcons').innerHTML = '<a style="color: white;" href="javascript:void(0)" onclick="addConsultationNote(\'addconshistory\')">Add</a>| <a style="color: white;" href="">Print</a>';
	document.getElementById('consTitle').innerHTML = "Consultation Note";
}


function editConsultation(selectedid,divid){
	editSelectedid = selectedid;
	//alert(editSelectedid);
	document.getElementById('showconshistory').style.display = 'none';
	document.getElementById('addconshistory').style.display = 'none';
	//document.getElementById('viewcons').style.display = 'none';
	document.getElementById('editconsult').style.display = '';	
	document.getElementById(divid).style.display = '';
	document.getElementById('consTitle').innerHTML = "Edit Consultation Note";
    document.getElementById('addcons').innerHTML = '';
    
    viewConsultationAjax(selectedid);
	
}

/*function viewConsultation(selectedid,divid){
	
	document.getElementById('showconshistory').style.display = 'none';
	document.getElementById('addconshistory').style.display = 'none';
	//document.getElementById('viewcons').style.display = '';
	document.getElementById('editconsult').style.display = 'none';	
	document.getElementById(divid).style.display = '';
	document.getElementById('consTitle').innerHTML = "View Consultation Note";
    document.getElementById('addcons').innerHTML = 'none';
    
    viewConsultationAjax(selectedid);
	
}
*/


function viewConsultationAjax(selectedid){
	
	
	//alert(selectedid);
	var url = "viewconsEmr?selectedid="+selectedid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = viewConsultationAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}


function viewConsultationAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			//var viewcons =  req.responseText;
			//alert(req.responseText);
			//document.getElementById('editconstext').innerHTML = req.responseText;
			nicEditors.findEditor('editconstext').setContent(req.responseText);
			/*nicEditors.findEditor('viewconstext').setContent(viewcons);
			nicEditors.findEditor('editconstext').setContent(req.responseText);
			nicEditors.findEditor("viewconstext").disable(); */
		}
	}
}


//delete consultation note

function deleteConsultationNoteAjax(selectedid){
	var apmtid = document.getElementById('selectedid').value;
	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {	
	//var pid = document.getElementById('diaryUser').value;	
	var url = "deleteconsEmr?selectedid="+selectedid+"&selectedPatientId="+selectedPatientId+"&pid="+pid+"&apmtid="+apmtid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deleteConsultationNoteAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	    
	    return true;
	  }
	else
	  {
	  return false;
	  }

	
}

function deleteConsultationNoteAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('showconshistory').innerHTML = req.responseText;
			closeConsultationNote();
		}
	}
	
}


//editsave consultation note
function editsaveConsultationNoteAjax(){
	//var editconstext = nicEditors.findEditor('editconstext').getContent();
	var editconstext = nicEditors.findEditor('editconstext').getContent();
	var apmtid = document.getElementById('selectedid').value;
	if(editconstext == "" || editconstext =="<br>"){
		 jAlert('error', 'Please add consultation note!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
	var clientid = document.getElementById('selectedPatientId').value;
	//var pid = document.getElementById('diaryUser').value;	
	
	var url = "editsaveconsEmr?selectedid="+editSelectedid+"&editconstext="+editconstext+"&selectedPatientId="+selectedPatientId+"&pid="+pid+"&apmtid="+apmtid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editsaveConsultationNoteAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}

function editsaveConsultationNoteAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('showconshistory').innerHTML = req.responseText;
			closeConsultationNote();
			
		}
	}
	
}

function saveConsultationNoteAjax(){
	
	//var consNoteText = nicEditors.findEditor('consNoteText').getContent();
	
	var consNoteText =  nicEditors.findEditor('consNoteText').getContent();
	if(consNoteText == "" || consNoteText == "<br>"){
		 jAlert('error', 'Please add consultation note!!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else{
	var apmtid = document.getElementById('selectedid').value;
	var clientid = document.getElementById('selectedPatientId').value;	
	
	//alert(pid);
	var url = "saveconsEmr?selectedPatientId="+selectedPatientId+"&consNoteText="+consNoteText+"&apmtid="+apmtid+"&pid="+pid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveConsultationNoteAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}
function saveConsultationNoteAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('showconshistory').innerHTML = req.responseText;
			document.getElementById('consNoteText').value = "";	
			closeConsultationNote();
		}
}
	
}

var imageAdder = function(){
	var browser = document.getElementById('imagebrowser');
	var images = browser.getElementsByTagName('img');
	
	//Add click event to all images within image browser
	for(var i=0;i<images.length;i++){
		images[i].onclick = update;
	}
	
	
	
}

function update(){
	var con = nicEditors.findEditor( "consNoteText" ).getContent() + ' <img src="'+this.src+'" />';
	nicEditors.findEditor('consNoteText').setContent(con);
} 

var imageAdderEdit = function(){
	var browser = document.getElementById('imagebrowseredit');
	var images = browser.getElementsByTagName('img');
	
	//Add click event to all images within image browser
	for(var i=0;i<images.length;i++){
		images[i].onclick = updateEdit;
	}
	
	
	
}

function updateEdit(){
	var con = nicEditors.findEditor( "editconstext" ).getContent() + ' <img src="'+this.src+'" />';
	nicEditors.findEditor('editconstext').setContent(con);
} 

function setProblem(noteid){
	var con = nicEditors.findEditor(noteid).getContent() + '<br> <label>Problem:</label>';
	nicEditors.findEditor(noteid).setContent(con);
	
}
function setExamination(noteid){
	var con = nicEditors.findEditor(noteid).getContent() + '<br> <label>Examination:</label>';
	nicEditors.findEditor(noteid).setContent(con);
}
function setAssessment(noteid){
	var con = nicEditors.findEditor(noteid).getContent() + '<br> <label>Assessment:</label>';
	nicEditors.findEditor(noteid).setContent(con);
}

function setTreatment(noteid){
	var con = nicEditors.findEditor(noteid).getContent() + '<br> <label>Treatment::</label>';
	nicEditors.findEditor(noteid).setContent(con);
}
function setDateTime(noteid){
	var d1 = new Date();
	
	var date = d1.getDate();
	var month = d1.getMonth();
	var year = d1.getFullYear();
	var currentdate = date+"/"+month+"/"+year;
	
	var hour = d1.getHours();
	var minute = d1.getMinutes();
	var sec = d1.getSeconds();
	var time = hour+":"+minute+":"+sec;
	
	var con = nicEditors.findEditor(noteid).getContent() + '<br> <label>'+currentdate+' , '+time+' , SA:</label>';
	nicEditors.findEditor(noteid).setContent(con);
	
}
function setPlan(noteid){
	var con = nicEditors.findEditor(noteid).getContent() + '<br> <label>Plan:</label>';
	nicEditors.findEditor(noteid).setContent(con);
}