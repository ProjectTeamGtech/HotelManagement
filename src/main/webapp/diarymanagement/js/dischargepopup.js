function showDischargePopup(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	var url = "dischistoryNotAvailableSlot?apmtid="+editAppointId+" ";
	
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showDischargePopupRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
	
}

function showDischargePopupRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data = req.responseText;
			
			var temp = data.split('#');
			var trtmntstatus = temp[0];
			var outcomes = temp[1];
			var dschargstatus = temp[2];
			var trtmntepisodeid = temp[3];
			
			if(trtmntstatus==1){
		 		document.getElementById('chkDischarge').checked=true;
		 	}else{
		 		document.getElementById('chkDischarge').checked=false;
		 	}
			
		if(trtmntepisodeid>0){
			
			if(trtmntstatus==0){
				//document.getElementById('dischargeclientdiv').style.display = '';
				document.getElementById('addconbtnsdiv').style.display = '';
		 		document.getElementById('dischrgeOutcomes').value = outcomes;
		 		document.getElementById('dischargeStatus').value = dschargstatus;
		 		document.getElementById('toggleDischargediv').style.display = '';
		 	}else{
		 		document.getElementById('toggleDischargediv').style.display = '';
		 		document.getElementById('addconbtnsdiv').style.display = '';
		 		document.getElementById('dischrgeOutcomes').value = outcomes;
		 		document.getElementById('dischargeStatus').value = dschargstatus;
		 	}
			
			
		}else{
			document.getElementById('dischargeclientdiv').style.display = 'none';
			document.getElementById('dischrgeOutcomes').value = 0;
	 		document.getElementById('dischargeStatus').value = 0;
		}	
			
		
			getConsultationNoteDetailAjax();
			
			
		}
	}
	
}


function getConsultationNoteDetailAjax(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	var url = "consnoteNotAvailableSlot?apmtid="+editAppointId+" ";
	
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getConsultationNoteDetailAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function getConsultationNoteDetailAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data = req.responseText;
			
			//nicEditors.findEditor ("consNote").setContent(data);
			
			getAllPriscriptionList(data);
			
			$('#addConsultationNote').modal( "show" );
		}
	}
}
var updatedconsnote = "";

function getAllPriscriptionList(data){
	
	updatedconsnote = data;
	
	var url = "viewallpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";
	
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getAllPriscriptionListRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}


function getAllPriscriptionListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			
			var str = "";
			
			if(req.responseText!=""){
				
				//document.getElementById('prischdrdiv').style.display = '';
				
					str = '<table  cellspacing="0" width="70%">'
					str = str + '<thead>'
					str = str + ' <tr class="tableback">'
					str = str + '<th>Type</th>'
					str = str + '<th>Drug Name</th>'
					str = str + ' <th>Dose</th>'
					str = str + ' <th>Frequency</th>'
					str = str + ' <th>Duration</th>'
					str = str + ' <th>Total</th>'
					str = str + ' </tr>'
					str = str + ' </thead>'
	                
					str = str + '<tbody>'
						str = str + req.responseText;
					str = str + '</tbody>'
					str = str + '</table>'
			}else{
				
				//document.getElementById('prischdrdiv').style.display = 'none';
			}
				
			setTimeout(function(){
				$(".nicEdit-main").html(updatedconsnote + "<br><b>Prescription : </b><br>" + str);			
			}, 1000);
			//nicEditors.findEditor ("consNote").setContent(updatedconsnote + "<br>" + str );
			
			getClientAllPriscriptionData();
		}
	}
}

function getClientAllPriscriptionData(){
	var url = "viewallclientpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";
	
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getClientAllPriscriptionDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function getClientAllPriscriptionDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('alldataprisctable').innerHTML = req.responseText;
		}
	}
	
}




function toggleDischarge(){
	if(document.getElementById('toggleDischargediv').style.display=='none'){
		document.getElementById('toggleDischargediv').style.display = '';
	}else{
		document.getElementById('toggleDischargediv').style.display = 'none';
	}
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
	var con = nicEditors.findEditor(noteid).getContent() + '<br> <label>Treatment:</label>';
	nicEditors.findEditor(noteid).setContent(con);
}
function setSOS(noteid){
	var con = nicEditors.findEditor(noteid).getContent() + '<br> <label>Subjective:</label>' +'<br> <label>Objective:</label>'+'<br> <label>Appraisal:</label>'+'<br> <label>Plan:</label>';
	nicEditors.findEditor(noteid).setContent(con);
}
function setInitial(noteid){
	var con = nicEditors.findEditor(noteid).getContent() + '<br> <label>Initial:</label>';
	nicEditors.findEditor(noteid).setContent(con);
}

function showdignosistxt(noteid){
	var diagnosis = nicEditors.findEditor(noteid).getContent() + '<br><label>' + $("#consCondition option:selected").text() + ':</label>' ;
	nicEditors.findEditor(noteid).setContent(diagnosis);	
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


function openSymbolPopup(){
	
	$('#symbolPopup').modal( "show" );
}


function saveWithoutDischarge(){
	document.getElementById('chkDischarge').checked = false;
	saveOnlyConsultationNote();
}



function saveOnlyConsultationNote(){
	
	//$('#addConsultationNote').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' });
	
	$('#dashboardloaderPopup').modal( "show" );
	
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	var notes = nicEditors.findEditor ("consNote").getContent();
	//var notes = document.getElementById('consNote').value; 
	
	notes = notes.replace(/&nbsp;/g,'');
	
	
	var outcomes = document.getElementById('dischrgeOutcomes').value;
	var dschargestatus = document.getElementById('dischargeStatus').value;
	var chkdischarge = document.getElementById('chkDischarge').checked;
	var url = "dischargeNotAvailableSlot?apmtid="+editAppointId+"&outcomes="+outcomes+"&dschargestatus="+dschargestatus+"&chkdischarge="+chkdischarge+"&consnote="+notes+" ";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveOnlyConsultationNoteRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}



function saveOnlyConsultationNoteRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		
			
			$('#addConsultationNote').unblock();
			showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
			
			$('#addConsultationNote').modal( "hide" );
			
			$('#dashboardloaderPopup').modal( "hide" );
			
			jAlert('success', 'Record Updated.', 'Success Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			//window.location.reload();
			
		}
	}
}