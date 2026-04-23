var globaleditconsultnote = "";
var globalClientId = "";
function setClients(practId){
	
var url = "setClientsConsultationNote?practId="+practId+"";	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setClientsdRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setClientsdRequest(){
if (req.readyState == 4) {
		
		
			if (req.status == 200) {
				document.getElementById("clientname").innerHTML =  req.responseText;
				$("#clientname").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});	
				
			}
			
			//getPatientRecord();
			
	}
}

function setClientsajax(practId){
$('#baselayout1loaderPopup').modal( "show" );
	
var url = "setClientsajaxConsultationNote?practId="+practId+"";	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setClientsdajaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}

function setClientsdajaxRequest(){
if (req.readyState == 4) {
		
		
			if (req.status == 200) {
			$('#baselayout1loaderPopup').modal( "hide" );
				document.getElementById("emrclientdataajaxdiv").innerHTML =  req.responseText;
			/*	$("#clientname").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});	*/
				
					$("#clientname").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				
			}
			
			//getPatientRecord();
			
	}
}

function setCondition(clientId){
	var url = "setClientConditionConsultationNote?clientId="+clientId+"";	
	globalClientId = clientId;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setConditionRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function setConditionRequest(){
	if (req.readyState == 4) {
		
		
		if (req.status == 200) {
			document.getElementById("condition").innerHTML =  req.responseText;
			$("#condition").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});	
			
			
			
			setClientDetailsDiv(globalClientId);
		}
		
}
}


function setConditionajax(clientId){
$('#baselayout1loaderPopup').modal( "show" );
	var url = "setClientConditionajaxConsultationNote?clientId="+clientId+"";	
	globalClientId = clientId;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setConditionajaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function setConditionajaxRequest(){
	if (req.readyState == 4) {
		
		
		if (req.status == 200) {
		$('#baselayout1loaderPopup').modal( "hide" );
			document.getElementById("conditionajaxdiv").innerHTML =  req.responseText;
			/*$("#condition").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});*/	
			
				$("#condition").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
			
			setClientDetailsDiv(globalClientId);
		}
		
}
}


function showList(){
	var practId = document.getElementById('diaryUser').value;
	var clientId = document.getElementById('clientname').value;
	var conditionId = document.getElementById('condition').value;
	document.getElementById('errorselect').innerHTML = "";
	if(practId == 0 || clientId == 0 || conditionId == 0) {
		
		setErrorCN(selectdp);
		
		var errorselect = document.createElement("label");
		errorselect.innerHTML = "Please Select all 3 Dropdown";
	    document.getElementById('errorselect').appendChild(errorselect);
	}
	else{
		document.getElementById('errorselect').innerHTML = "";
		removeErrorCN(selectdp);
	var url = "showAllConsultationNote?&clientId="+clientId+"&practId="+practId+"&conditionId="+conditionId+"";	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = showListRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function showListRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("consHistory").innerHTML =  req.responseText;
		}
	}
	

}
function addConsultationNote(){
	document.getElementById('errorselect').innerHTML = "";
	var practId = document.getElementById('diaryUser').value;
	var clientId = document.getElementById('clientname').value;
	var conditionId = document.getElementById('condition').value;
	if(practId == 0 || clientId == 0 || conditionId == 0) {
		
		setErrorCN(selectdp);
		
		var errorselect = document.createElement("label");
		errorselect.innerHTML = "Please Select all 3 Dropdown";
	    document.getElementById('errorselect').appendChild(errorselect);
	}
	else{
		document.getElementById('errorselect').innerHTML = "";
		removeErrorCN(selectdp);
	document.getElementById('addconsutationnote').style.display = '';
	document.getElementById('editconsutationnote').style.display = 'none';
	}


}
function saveConsultationNote(){
	
	var consNote = nicEditors.findEditor( "consNote" ).getContent();
	
	var practId = document.getElementById('diaryUser').value;
	var clientId = document.getElementById('clientname').value;
	var conditionId = document.getElementById('condition').value;
	
	var url = "saveConsultationNote?consNote="+consNote+"&clientId="+clientId+"&practId="+practId+"&conditionId="+conditionId+"";	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = saveConsultationNoteRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function saveConsultationNoteRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("consHistory").innerHTML =  req.responseText;
			document.getElementById('addconsutationnote').style.display = 'none';
		}
	}
	
	
}

var edtconsappmtid = 0;
function editConsultationNote(id,appointmentid){
	globaleditconsultnote = id;
	document.getElementById("hiddenidconsultnote").value =  id;
	edtconsappmtid = appointmentid;
	var url = "editConsultationNote?id="+id+"";	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = editConsultationNoteRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}
function editConsultationNoteRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			nicEditors.findEditor('editconsNote').setContent(req.responseText);
			$('#editConsultationNote').modal( "show" );
			
			var treatmentEpisodeid = document.getElementById('treatmentEpisodeid').value;
			
			if(treatmentEpisodeid!=0){
				var data = '';
				if(edtconsappmtid==0){
					data = document.getElementById('hdn'+treatmentEpisodeid).value;
				}else{
					data = document.getElementById('hdn'+edtconsappmtid).value;
				}
				
			
	        	var temp = data.split('#');
	        	document.getElementById('ehdntrtmentspan').innerHTML = temp[0];
	        	document.getElementById('ehdnapmtspan').innerHTML = temp[1];
	        	
	        	
	        	var trtmentstatus = temp[2];
	         	var outcomes = temp[3];
	         	var dschargestatus = temp[4];
	         	var dischargedate = temp[5];
	         	var hour = temp[6];
	         	var minute = temp[7]
	         	
	        	if(trtmentstatus==1){
	    	 		document.getElementById('edchkDischarge').checked=true;
	    	 	}else{
	    	 		document.getElementById('edchkDischarge').checked=false;
	    	 	}
	         	
	         	if(trtmentstatus==0){
	         		document.getElementById('edaddconbtnsdiv').style.display = '';
	         		document.getElementById('eddischrgeOutcomes').value = outcomes;
	         		document.getElementById('eddischargeStatus').value = dschargestatus;
	         		
	         		document.getElementById('editdischargedate').value = dischargedate;
	         		document.getElementById('edithour').value = hour;
	         		document.getElementById('editminute').value = minute;
	         		
	         		document.getElementById('edtoggleDischargediv').style.display = 'none';
	         	}else{
	         		document.getElementById('edtoggleDischargediv').style.display = '';
	         		document.getElementById('edaddconbtnsdiv').style.display = '';
	         		document.getElementById('eddischrgeOutcomes').value = outcomes;
	         		document.getElementById('eddischargeStatus').value = dschargestatus;
	         		
	         		document.getElementById('editdischargedate').value = dischargedate;
	         		document.getElementById('edithour').value = hour;
	         		document.getElementById('editminute').value = minute;
	         	}
	        	
			}else{
				document.getElementById('edtoggleDischargediv').style.display = 'none';
				document.getElementById('eddischargeclientdiv').style.display = 'none';
				
			}
			
			
			
			
		//	document.getElementById('editconsNote').value = req.responseText;
		//	document.getElementById('editconsutationnote').style.display = '';
		//	document.getElementById('addconsutationnote').style.display = 'none';
			seteditopdselectedcondition();
			geteditClientAllPriscriptionData();
			
		}
	}
	
	
	
}

function seteditopdselectedcondition(){
 	var constr = document.getElementById('eodconditionstr').value;
 	var temp = constr.split(',');
 	for(var c=0;c<temp.length;c++){
 		document.getElementById('echh'+temp[c]).checked = true;
 	}
 }



function geteditClientAllPriscriptionData(){
	var url = "viewallclientpriscEmr?clientId="+patientId+"&prectionerid="+diaryuserId+"&conditionid="+editcondition_id+" ";
	
	 if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = geteditClientAllPriscriptionDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function geteditClientAllPriscriptionDataRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			if(req.responseText!=""){
				document.getElementById('editalldataprisctable').innerHTML = req.responseText;
				getinvestigationViewList();
			}else{
				//document.getElementById('prischdrdiv').style.display = 'none';
			}
			
		}
	}
	
}


function updateConsultationNote(){
	
	var consNote = nicEditors.findEditor( "editconsNote" ).getContent();
	var practId = document.getElementById('diaryUser').value;
	var clientId = document.getElementById('clientname').value;
	var conditionId = document.getElementById('condition').value;
	
	var url = "updateConsultationNote?consNote="+consNote+"&clientId="+clientId+"&practId="+practId+"&conditionId="+conditionId+"&id="+globaleditconsultnote+"";	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updateConsultationNoteRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	
}
function updateConsultationNoteRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("consHistory").innerHTML =  req.responseText;
			document.getElementById('editconsutationnote').style.display = 'none';
		}
	}
	
}
function deleteConsultationNote(id){
	var practId = document.getElementById('diaryUser').value;
	var clientId = document.getElementById('clientname').value;
	var conditionId = document.getElementById('condition').value;
	var url = "deleteConsultationNote?id="+id+"&clientId="+clientId+"&practId="+practId+"&conditionId="+conditionId+"";	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = deleteConsultationNoteRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function deleteConsultationNoteRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		document.getElementById("consHistory").innerHTML =  req.responseText;
		}
	}
	
}
function closeAddConsultationNote(){
	document.getElementById('addconsutationnote').style.display = 'none';
}
function closeEditConsultationNote(){
	document.getElementById('editconsutationnote').style.display = 'none';
}

function setErrorCN(element) {
    $(element).css("background-color", "#F2DEDE");
   // $(element).parent().addClass("has-error");
    $(element).focus();
   $(element).tooltip('show');
}

//function to remove error for client side validation
function removeErrorCN(element) {
    $(element).css("background-color", "#FFFFFF");
    if ($(element).parent().hasClass("has-error")) {
        $(element).parent().removeClass("has-error");
    }
   $(element).tooltip('hide');
}

var imageAdder = function(){
	/*var browser = document.getElementById('imagebrowser');
	var images = browser.getElementsByTagName('img');
	
	//Add click event to all images within image browser
	for(var i=0;i<images.length;i++){
		images[i].onclick = update;
	}
	*/
	
	
}



function update(){
	
		var con = nicEditors.findEditor( "consNote" ).getContent() + ' <img src="'+this.src+'" />';
		nicEditors.findEditor('consNote').setContent(con);
	 
		$('#symbolPopup').modal( "hide" );
} 

var imageAdderEdit = function(){
	/*var browser = document.getElementById('imagebrowseredit');
	var images = browser.getElementsByTagName('img');
	
	//Add click event to all images within image browser
	for(var i=0;i<images.length;i++){
		images[i].onclick = updateEdit;
	}*/
	
	
	
}






function updateEdit(){
	var con = nicEditors.findEditor( "editconsNote" ).getContent() + ' <img src="'+this.src+'" />';
	nicEditors.findEditor('editconsNote').setContent(con);
	
	$('#editsymbolPopup').modal( "hide" );
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


function setVoiceoverText(){
var con = nicEditors.findEditor("consNote").getContent() + '<br>' + nicEditors.findEditor("otnotes").getContent();
nicEditors.findEditor("consNote").setContent(con);
$('#commonvoiceover').modal( "hide" );

}

function showopdcontxtoneditor(noteid){
	var str = '';
	 $('.concase').each(function() { //loop through each checkbox
          if(this.checked==true){
          	var txt = document.getElementById('contxt'+this.value).innerHTML;
          	str = str + '<br><label>' + txt + ':</label>' ;
          }              
      });
      
      var diagnosis = nicEditors.findEditor(noteid).getContent() + str;
      nicEditors.findEditor(noteid).setContent(diagnosis);
}

function editshowopdcontxtoneditor(noteid){
	var str = '';
	 $('.econcase').each(function() { //loop through each checkbox
          if(this.checked==true){
          	var txt = document.getElementById('econtxt'+this.value).innerHTML;
          	str = str + '<br><label>' + txt + ':</label>' ;
          }              
      });
      
      var diagnosis = nicEditors.findEditor(noteid).getContent() + str;
      nicEditors.findEditor(noteid).setContent(diagnosis);
}

function showdignosistxt(noteid){
	var diagnosis = nicEditors.findEditor(noteid).getContent() + '<br><label>' + $("#consCondition option:selected").text() + ':</label>' ;
	nicEditors.findEditor(noteid).setContent(diagnosis);	
}

function showeditdignosistxt(noteid){
	var diagnosis = nicEditors.findEditor(noteid).getContent() + '<br><label>' + $("#editconsCondition option:selected").text() + ':</label>' ;
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


var tempt="";

function setAllDiagnosis(noteid) {
   
    tempt=noteid;
    
    var apmtId=document.getElementById("apmtId").value;
    var condition=document.getElementById("condition").value;
    var url = "allconditionsEmr?apmtId="+apmtId+"&condition="+condition+"";	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setAllDiagnosisRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}


function setAllDiagnosisRequest(){
if (req.readyState == 4) {
		
		if (req.status == 200) {
			var data =  req.responseText;
			var con = nicEditors.findEditor(tempt).getContent() + data;
	        nicEditors.findEditor(tempt).setContent(con);	
	    }	
   }
}


function getPatientRecord(){
	document.getElementById('hdncondapmtId').value = "";
	

	
	document.getElementById('saveemrfrm').submit();
	
}




function setClientDetailsDiv(clientId){
	var url = "getClientDetailsEmr?clientId="+clientId+"";	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = setClientDetailsDivRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setClientDetailsDivRequest(){
if (req.readyState == 4) {
		
		
		if (req.status == 200) {
			document.getElementById("clientDetailsDiv").innerHTML =  req.responseText;
			
			
			
		}
		
}
}

function deleteConsultationNote(id){
   
   var t=confirm("Are you sure to Delete?"); 
   if(t==true){ 
 
	document.getElementById('deleteAllConsultationNote_'+id+'').submit();
	
   }
}
function deleteContNote(id){
	var t=confirm("Are you sure to Delete?"); 
   if(t==true){ 
	   document.getElementById('deleteConsultationNote_'+id+'').submit();
	}
}
function openSymbolPopup(){
	
	$('#symbolPopup').modal( "show" );
}
function openEditSymbolPopup(){
	$('#editsymbolPopup').modal( "show" );
}

function setImageInPopup(imagedata,clientImageDataId){
	
	ZOOM = 100;
	MAIN.init();
	 
	 document.getElementById('clientImageDataId').value = clientImageDataId;
	//var dataURL = imagedata; 
    // load image from data url
	 var dataURL = imagedata; 
	var imageObj = new Image();
    imageObj.src = dataURL;
    imageObj.onload = function() {
    var canvas = document.getElementById('Background');
    var context = canvas.getContext('2d');
    context.drawImage(imageObj, 0, 0);	
   
    $('#editQuickChartPopup').modal( "show" );
}
}

function updateImageData(){
	
	var imageData = "";
	
	 for(var i in LAYERS){
		 imageData = document.getElementById(LAYERS[i].name).toDataURL("image/png");
		
	}
	 document.getElementById('clientImageData').value = imageData;
	 document.getElementById('updateImageDateOfClient').submit();

}

function deleteClientImage(){
	document.getElementById('deleteClientImage').submit();
}

function editMedicalRecords(id,type,desc){
	document.getElementById('medicalHistoryNotes').value = desc;
	document.getElementById('medicalRecordId').value = id;
	document.getElementById('medicalRecordType').value = type;
	
	
	
}

function updateMedicalRecords(){
	document.getElementById('editMedicalRecords').submit();
}

function deleteMedicalRecord(id){
	document.getElementById('deleteMedicalRecord_'+id+'').submit();
	
}

function editDocuments(id,type,desc,filename){
	document.getElementById('documentDesc').value = desc;
	document.getElementById('editDoctId').value = id;
	document.getElementById('doctType').value = type;
	document.getElementById('filename').innerHTML = "Uploaded File: "+filename;
}

function deleteDocuments(id){
	document.getElementById('deleteDocuments_'+id+'').submit();
}
function setEditor(){
	
	document.getElementById('clientImageDataId').value = "";
	
	ZOOM = 100;
	MAIN.init();	
   
    

    
}

function clearConsultationNoteEditor(){
	
	var con = nicEditors.findEditor( "consNote" ).getContent();
	nicEditors.findEditor('consNote').setContent(con);
	
	var con = nicEditors.findEditor( "editconsNote" ).getContent();
	nicEditors.findEditor('editconsNote').setContent(con);
}

function clearUplaodDocumentPopup(){
    
	document.getElementById('documentDesc').value = "";
	document.getElementById('editDoctId').value = "";
	document.getElementById('doctType').value = "0";
	document.getElementById('filename').innerHTML = "";
}
function downloadDocuments(){
	document.getElementById('downloadDocuments').submit();

}
function filterConsultation(id){
	document.getElementById('hdneditapmtid').value = id;
	document.getElementById('apmtId').value = id;
	//document.getElementById('filterConsultation_'+id+'').submit();
}
function uploadVideoPopup(){
	
	document.getElementById("uploadDocTitle").innerHTML = "Upload New Video Clip";
	document.getElementById("isvideo").value = "1";
	document.getElementById("doctType").style.display = "none";
	document.getElementById("documentDesc").style.display = "none";
	
	$('#uploaddoc').modal('show');
}
function openVideoClipPopup(videofilename,id){
	 $('#videoClipPlay').modal('show');
	var player = document.getElementById('videoPlayer');

    var mp4Vid = document.getElementById('mp4Source');
    var oggid = document.getElementById('oggSource');
    player.pause();

  
    var oggname = videofilename.split(".");
   $(mp4Vid).attr('src', videofilename);
   $(oggid).attr('src', oggname[0]+".ogg");
    
  
    
   
    player.load();
    player.play();
   
    
}


function toggleDischarge(){
	if(document.getElementById('toggleDischargediv').style.display=='none'){
		document.getElementById('toggleDischargediv').style.display = '';
	}else{
		document.getElementById('toggleDischargediv').style.display = 'none';
	}
}

function edtoggleDischarge(){
	if(document.getElementById('edtoggleDischargediv').style.display=='none'){
		document.getElementById('edtoggleDischargediv').style.display = '';
	}else{
		document.getElementById('edtoggleDischargediv').style.display = 'none';
	}
}


function showopdcontxtoneditornew(noteid){
		var str = '';
		var txt = document.getElementById("ccck"+noteid).innerHTML;
		str = str + '<br><label style="font-size: 14px !important;">' + txt + ':</label>' ;
		var diagnosis = nicEditors.findEditor('consNote').getContent() + str;
		nicEditors.findEditor('consNote').setContent(diagnosis); 
		
}

function selectdia(){
	var total=0;
	$('.concase').each(function() {
		 if(this.checked){
	    var i=this.value;
	    total=total+","+this.value;
		 }
		 document.getElementById("finaldiagnosis").value=total;
	});
	}