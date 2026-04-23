/*OT Note Voice recorder*/
function startConverting(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts + '<span style="color:#999">' + interimtranscripts + '</span>';
		
		var con = nicEditors.findEditor('otnotes').getContent() + vtxt;
		nicEditors.findEditor('otnotes').setContent(vtxt);
		
		
		
	};

}


/*EMR Consultation Note Voice recorder*/
function startConvertingemr(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts + '<span style="color:#999">' + interimtranscripts + '</span>';
		
		var con = nicEditors.findEditor('consNote').getContent() + vtxt;
		nicEditors.findEditor('consNote').setContent(vtxt);
		
		
	};

}

/*OPD Consultation Note Voice recorder*/
function startConvertingopd(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts + '<span style="color:#999">' + interimtranscripts + '</span>';
		
		var con = nicEditors.findEditor('consNoteopd').getContent() + vtxt;
		nicEditors.findEditor('consNoteopd').setContent(vtxt);
		
		
	};

}

/*Discharge Form Hospital Course Voice recorder*/
function startConvertinghoscourse(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts + '<span style="color:#999">' + interimtranscripts + '</span>';
		
		var con = nicEditors.findEditor('hospitalcourse').getContent() + vtxt;
		nicEditors.findEditor('hospitalcourse').setContent(vtxt);
		
		
		
	};

}

/*Discharge Form Nursing Advice Voice recorder*/
function startConvertingdisadnotes(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts + '<span style="color:#999">' + interimtranscripts + '</span>';
		
		var con = nicEditors.findEditor('discadvnotes').getContent() + vtxt;
		nicEditors.findEditor('discadvnotes').setContent(vtxt);
		
		
		
	};

}

/*Discharge Form History Voice recorder*/
function startConvertingdishistory(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts + '<span style="color:#999">' + interimtranscripts + '</span>';
		
		var con = nicEditors.findEditor('discharge_default').getContent() + vtxt;
		nicEditors.findEditor('discharge_default').setContent(vtxt);
		
		
		
	};

}

/*Patient Search Voice recorder*/
function startConvertingsearchp(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts  + interimtranscripts ;
		
		//var con = nicEditors.findEditor('adharsearch').getContent() + vtxt;
	//	nicEditors.findEditor('adharsearch').setContent(vtxt);
		document.getElementById('adharsearch').value=vtxt;
		document.getElementById('remark').value=vtxt;
		
		
	};

}
function startConvertingsearchpforname(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts  + interimtranscripts ;
		
		//var con = nicEditors.findEditor('adharsearch').getContent() + vtxt;
	//	nicEditors.findEditor('adharsearch').setContent(vtxt);
		document.getElementById('firstNameaff').value=vtxt;
		
		
		
	};

}

function startConvertingsearchpformobno(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts  + interimtranscripts ;
		
		//var con = nicEditors.findEditor('adharsearch').getContent() + vtxt;
	//	nicEditors.findEditor('adharsearch').setContent(vtxt);
		document.getElementById('mobNoaff').value=vtxt;
		
		
		
	};

}


/*Prescription Popup Voice recorder*/
function startConvertingadvicepres(){
	var recognition = new webkitSpeechRecognition();
	recognition.continuous = true;
	recognition.interimResults = true;
	recognition.lang = "en-IN";
	recognition.start();

	var finalTranscripts = '';
	recognition.onresult = function(event){
		var interimtranscripts = '';
		for(var i=event.resultIndex;i<event.results.length;i++){
			var transcript = event.results[i][0].transcript;
			transcript.replace("/n","</br>");
			
			if(event.results[i].isFinal){
				finalTranscripts += transcript;
			}else{
				interimtranscripts += transcript;
			}
		}
		var vtxt  = finalTranscripts  + interimtranscripts ;
		
		//var con = nicEditors.findEditor('adharsearch').getContent() + vtxt;
	//	nicEditors.findEditor('adharsearch').setContent(vtxt);
		document.getElementById('priscadvoice').value=vtxt;
		
		
		
	};

}



function showTemplateText(id){
	var url = "otemplateNotAvailableSlot?id="+id+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showTemplateTextRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function showTemplateTextRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				nicEditors.findEditor('otnotes').setContent(req.responseText);
			}
	}
}

function getOTtemplate(id){
	var url = "getipdtemplateIpd?id="+id+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getOTtemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function getOTtemplateRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				nicEditors.findEditor('otnotes').setContent(req.responseText);
			}
	}
}


function addsubmit() {
	var data =	document.getElementById('tempcount').value;
	if(data>=9){
		jAlert('error', "Not add more than 10!", 'Error Dialog');	
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		for (var i = 0; i <=data; i++) {
			document.getElementById('ottemp'+i).value=  nicEditors.findEditor('otnotes'+i).getContent();
		}
		document.getElementById('otaddd').submit();
	}
	
	
} 
var tmpid=0;
function getNewOTtemplate(id,val){
	tmpid = val;
	var url = "getipdtemplateIpd?id="+id+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getNewOTtemplateRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function getNewOTtemplateRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				nicEditors.findEditor('otnotes'+tmpid).setContent(req.responseText);
			}
	}
}

function changeviewtype(val) {
	document.getElementById('viewtyype').value=val;
	document.getElementById('otchangeview').submit()
}

function changetemplateList(val) {
	document.getElementById('tempsrno').value=val;
	$('#selectothertemlate').modal( "show" );
}
var inittemp=0;
function setTemplateBySpeciality(name){
	var tempsrno = document.getElementById("tempsrno").value;
	inittemp = tempsrno;
	var url = "chageotemplateNotAvailableSlot?name="+name+"&tempsrno="+tempsrno+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setTemplateBySpecialityRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setTemplateBySpecialityRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById('templistdiv'+inittemp).innerHTML=req.responseText;
				$('#selectothertemlate').modal('hide');
			}
	}
}
var newinittemp=0;
function setOTemplateBySpeciality(tempsrno,srgeonid){
	newinittemp = tempsrno;
	var url = "chageotemplateBookAppointmentAjax?srgeonid="+srgeonid+"&tempsrno="+tempsrno+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setOTemplateBySpecialityRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setOTemplateBySpecialityRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				document.getElementById('templistdiv'+newinittemp).innerHTML=req.responseText;
				$("#template"+newinittemp).trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				jAlert('success', 'Template refresh successfully.', 'success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}
	}
}