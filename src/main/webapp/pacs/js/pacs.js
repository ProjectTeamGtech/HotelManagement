var selectedmodality = 0;
function setInvstId(id){
	document.getElementById('selectedinvstid').value = id;
}

function savedcomfile(){
	if(document.getElementById('selectedinvstid').value==0){
			jAlert('error', 'Please select investigation.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		document.getElementById('upload').submit();
	}
}


function showPacsData(){
	  $('.pacss').each(function() { 
			  
			 if(this.checked==true) {
			 	selectedmodality = selectedmodality + ',' + this.value;
			 }              
		});
		
	document.getElementById('selectedmodality').value = selectedmodality;
	document.getElementById('showpacsfrms').submit();
}


var oldid = 0;
function setSelectedRows(id){
	
	$(document.getElementById(id)).css('background-color', 'rgb(229, 217, 129)');
	
	
	if(oldid!=0){
		$(document.getElementById(oldid)).css('background-color', 'white');
	}

	
	oldid = id;
	setshowdicomid(oldid)
}



var count = 0;
var clinicid = '';
function setshowdicomid(id,action){
count = id;
var url = "dicomEmr?id="+id+"&action="+action+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setshowdicomidRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}	

function setshowdicomidRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			clinicid = req.responseText;
				hrefclick();
			}
		}
}	


function hrefclick(){

	 $('#myAnchor'+count).click(function(){
	 setSelectedRows(count);
			       window.location.href = 'http://1:8080/'+clinicid+'.jnlp';
			    });
}	
	
function saveautofinding(txt){
	var url = "findingPacs?txt="+txt+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = saveautofindingRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function saveautofindingRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			}
		}

}

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


var editid = 0;
function showfindingdataAjax(id){
editid = id;
	var url = "editPacs?id="+id+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showfindingdataAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function showfindingdataAjaxRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				nicEditors.findEditor('otnotes').setContent(req.responseText);
			}
		}
}

function updatefindings(){
document.getElementById('fid').value = editid;
document.getElementById('otnotes').value = nicEditors.findEditor('otnotes').getContent();
//alert(document.getElementById('otnotes').value)
document.getElementById('findingfrm').submit();
}


function uploadfinding(id){
document.getElementById('selectedinvstid').value = id;
	$( '#uploadfileword' ).modal( "show" );
}


function updatepatientuhid(id,pacsid) {
	
	document.getElementById("abriid").value=id;
	document.getElementById("pacsid").value=pacsid;
	//document.getElementById("patientnm").innerHTML=clientName;
	
	$("#updatepatientuhid").modal( "show" );
	
}

function updateUHID(){
	var id=document.getElementById("abriid").value;
	var uhid1=document.getElementById("uhid").value;
	
	var chk=0;
	if (uhid1 == '' && chk==0) {

		jAlert('error', "Please Enter Uhid!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		chk=1;
	}
	else{
	document.getElementById('updateuhidfrm').submit();
	}
}
	/*function openPacsPopup(val,id){
		var url = "findingPacs?id="+id+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = openPacsPopupRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}

	function openPacsPopupRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
				}
			}

	}
	*/
