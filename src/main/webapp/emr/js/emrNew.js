var globalTableId = "";
var c1 = 0;
var totalemrconlist = 0;
var totaleditemrconlist = 0;
function addMoreMedicalRecords(tableID){
	globalTableId = tableID;
	c1 = c1 + 1;
	var url = "getMedicalDropdownEmr?count="+c1+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addMoreMedicalRecordsRequest;
    req.open("GET", url, true); 
              
    req.send(null);
    
}

function addMoreMedicalRecordsRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				var dd = req.responseText;
				
				var table = document.getElementById(globalTableId);

			    var rowCount = table.rows.length;
			    var row = table.insertRow(rowCount);
			    var counts = rowCount;
				
			    
			     var cell1 = row.insertCell(0);
			     cell1.innerHTML = dd;
			     
			     //var cell2 = row.insertCell(1);
			     //cell2.innerHTML="<input type='text'  class='form-control showToolTip medicalRecordTypeOther' name = 'medicalHistory["+counts+"].medicalRecordTypeOther' id = 'medicalHistory["+counts+"].medicalRecordTypeOther' placeholder='Enter Document Note' style = 'display:none'>";
			     
			     var cell2 = row.insertCell(1);
			     cell2.innerHTML="<input type='text'  class='form-control showToolTip medicalHistoryNotes' name = 'medicalHistory[" + counts + "].medicalHistoryNotes' placeholder='Enter Document Note'>";

				
			}
	}

}

	
function setNew(type){
	if(type == 'Other'){
		document.getElementById("medicalHistory["+c1+"].medicalRecordTypeOther").style.display = "block";
	}
	else{
		document.getElementById("medicalHistory["+c1+"].medicalRecordTypeOther").style.display = "none";

	}
	
}	
	
	
	


function saveMedicalRecords(){
	
		$.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a moment .. Data Is Saving</h3>' });
		
		document.getElementById('addMedicalRecords').submit();


	
}

function insertMedicalRecordType(newtype){
	
	var url = "saveNewMedicalRecordTypeEmr?newtype="+newtype+"&c1="+c1+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = insertMedicalRecordTypeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function insertMedicalRecordTypeRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				alert(c1);
				var dd = req.responseText;
				
				document.getElementById("medicalHistory["+c1+"].medicalRecordType").innerHTML = dd;
				document.getElementById("medicalHistory["+c1+"].medicalRecordTypeOther").style.display = "none";

			   

				
			}
	}

}

function setEmrAction(action){
	//alert(action);
	document.getElementById('hdnaction').value = 'emr';
	getPatientRecord();
	//document.getElementById('getPatientRecord').submit();
	
}

function openAddConsultationPopup(){
	var data = '';
	nicEditors.findEditor('consNote').setContent("");
	var apmtid = document.getElementById('apmtId').value;
	var treatmentEpisodeid = document.getElementById('treatmentEpisodeid').value;
	
	if(treatmentEpisodeid!=0){
		if(apmtid==0){
			data = document.getElementById('hdn'+treatmentEpisodeid).value;
		}else{
			data = document.getElementById('hdn'+apmtid).value;
		}
		 
	 	var temp = data.split('#');
	 	
	 	var ddtd=temp[1].split("-");
	 	
	 	document.getElementById('hdntrtmentspan').innerHTML = temp[0];
	 	document.getElementById('hdnapmtspan').innerHTML =ddtd[1];
	 	
	 	
	 	
	 	var trtmentstatus = temp[2];
	 	var outcomes = temp[3];
	 	var dschargestatus = temp[4];
	 	
	 	if(trtmentstatus==1){
	 		document.getElementById('chkDischarge').checked=true;
	 	}else{
	 		document.getElementById('chkDischarge').checked=false;
	 	}
	 	
	 	if(trtmentstatus==0){
	 		document.getElementById('addconbtnsdiv').style.display = '';
	 		document.getElementById('dischrgeOutcomes').value = outcomes;
	 		document.getElementById('dischargeStatus').value = dschargestatus;
	 		document.getElementById('toggleDischargediv').style.display = 'none';
	 	}else{
	 		document.getElementById('toggleDischargediv').style.display = '';
	 		document.getElementById('addconbtnsdiv').style.display = '';
	 		document.getElementById('dischrgeOutcomes').value = outcomes;
	 		document.getElementById('dischargeStatus').value = dschargestatus;
	 	}
		
	}else{
		document.getElementById('toggleDischargediv').style.display = 'none';
		document.getElementById('dischargeclientdiv').style.display = 'none';
	}
	
 	
	getClientAllPriscriptionData();
	
	if(apmtid>0 && apmtid!=''){
		setopdselectedcondition();
	}
 	
 	//$('#addConsultationNote').modal( "show" );
 }
 
 function setopdselectedcondition(){
 	var constr = document.getElementById('odconditionstr').value;
 	var temp = constr.split(',');
 	for(var c=0;c<temp.length;c++){
 		document.getElementById('chh'+temp[c]).checked = true;
 	}
 }

function addpriscconsultationnote(){
	
	var notes = ''
	if(document.getElementById('maintextarea')){
		notes=nicEditors.findEditor ("maintextarea").getContent();
	}else{
		notes=nicEditors.findEditor ("consNote").getContent();
	}	
		
	notes = notes.replace(/&nbsp;/g,'');

	getAllPriscriptionList(notes);

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
				var data=req.responseText.split("~~");
			//	document.getElementById('prischdrdiv').style.display = '';
				
					str = '<table  cellspacing="0" width="70%">'
						
						if(document.getElementById("outoprisc").value==1){
							str = str + '<col width="3%">'		
						str = str + '<col width="3%">'	
						str = str + '<col width="3%">'
						str = str + '<col width="12%">'
						str = str + '<col width="5%">'
						
						str = str + '<col width="15%">'
						str = str + '<col width="3%">'
						str = str + '<col width="3%">'
						str = str + '<col width="5%">'
						str = str + '<col width="15%">'
						str = str + '<col width="12%">'
						}
					str = str + '<thead>'
					
					
						
					str = str + '<td><b>Prescription&nbsp;:</b></td>'
					str = str + ' <tr class="tableback" align="right" >'
					/*str = str + '<th>Type</th>'*/
					if(document.getElementById("outoprisc").value==0){
						
						
						
						var clinicid1=data[1];
						if(clinicid1=='raiprachi' || clinicid1=='dentalcl'){
						
						str = str + '<th>Sr. no</th>'
					    str = str + '<th>Drug Name</th>'
						str = str + ' <th>Dose</th>'
				        str = str + ' <th>Frequency</th>'
						str = str + ' <th>&nbsp;&nbsp;&nbsp;Day</th>'
					    str = str + ' <th>Remark</th>'
								
						}else{
						str = str + '<th>Sr. no</th>'
						str = str + '<th>Drug Name</th>'
						str = str + ' <th>Dose</th>'
						/*str = str + ' <th>Frequency</th>'*/
						str = str + ' <th>Duration</th>'
						str = str + ' <th>Routes</th>'
						str = str + ' <th>Frequency</th>'
						str = str + ' <th>Quantity</th>'
						str = str + ' <th>Remark</th>'
						}
					}else{
						str = str + '<th style="width:5%">Sr.no</th>'
						str = str + '<th>Medicine Name</th>'
						str = str + '<th>Dose</th>'
						str = str + '<th>Frequency</th>'
						str = str + '<th>Duration (in Days)</th>'
						
						str = str + '<th>Total Qty</th>'
						str = str + '<th>Remark Route</th>'
						str = str + '<th>Frequency Note</th>'
						str = str + '<th> Remark</th>'
						
					}
					str = str + ' </tr>'
					
					
					str = str + ' </thead>'
	                
					str = str + '<tbody>'
						str = str + data[0];
					str = str + '</tbody>'
					str = str + '</table>'
			}else{
				
				//document.getElementById('prischdrdiv').style.display = 'none';
			}
				
            
			if(document.getElementById('maintextarea')){
				nicEditors.findEditor ("maintextarea").setContent(nicEditors.findEditor('maintextarea').getContent() + "<br>" + str + "<br>");
			}else{
				nicEditors.findEditor ("consNote").setContent(updatedconsnote + "<br>" + str );
				nicEditors.findEditor ("editconsNote").setContent(nicEditors.findEditor('editconsNote').getContent() + "<br>" + str );
				
			}
			
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
			document.getElementById('emrprecrdiv').innerHTML = req.responseText;
			
			//shoe investigation list
			getinvestigationViewList();
		}
	}
	
}

function saveAddConsultationNote(){
	
	var notes = nicEditors.findEditor( "consNote" ).getContent();
	document.getElementById('consNote').value = notes;
	var apmntid = document.getElementById('apmtId').value;
	 document.getElementById("savenotesemrbtn").style.visibility = "hidden";
	if(apmntid>0){
		  $('.concase').each(function() { //loop through each checkbox
		  if(this.checked==true){
			  totalemrconlist  = totalemrconlist  + ',' + this.value;   
		  }
      });
      /*if(totalemrconlist==0){
      		jAlert('error', 'Please select condition.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
      }else{
        $('#baselayout1loaderPopup').modal( "show" );
      	 document.getElementById('odconditionstr').value = totalemrconlist;
		document.getElementById('addconsultationFrm').submit();
      }*/
		  
		  if(notes!=''){
	

	        $('#baselayout1loaderPopup').modal( "show" );
	      	 document.getElementById('odconditionstr').value = totalemrconlist;
			document.getElementById('addconsultationFrm').submit();
		  }
		  
	}else{
		if(notes!=''){
	  $('#baselayout1loaderPopup').modal( "show" );
		document.getElementById('addconsultationFrm').submit();
		}
	}
	
       
	
	 	
}


function saveOnlyConsultationNote(){
	
	
	$('#baselayout1loaderPopup').modal( "show" );
	
	var notes = nicEditors.findEditor( "consNote" ).getContent();
	document.getElementById('consNote').value = notes;
	//document.getElementById('chkDischarge').checked = false;
	document.getElementById('addconsultationFrm').submit();
	
	
}
function openeditemr(){
	$('#editdrop').modal( "show" );
}

function updatesaveConsultationNote(){
	
	var notes = nicEditors.findEditor( "editconsNote" ).getContent();
	document.getElementById('editconsNote').value = notes;
	var apmntid = document.getElementById('apmtId').value;
	
	if(apmntid>0){
		 $('.econcase').each(function() { //loop through each checkbox
		  if(this.checked==true){
			  totalemrconlist  = totalemrconlist  + ',' + this.value;   
		  }
      }); 
        /*if(totalemrconlist==0){
      		jAlert('error', 'Please select condition.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
      }else{
         $('#baselayout1loaderPopup').modal( "show" );
      	 document.getElementById('eodconditionstr').value = totalemrconlist;
	
		document.getElementById('editconsultationFrm').submit();
      }*/
		 
		 $('#baselayout1loaderPopup').modal( "show" );
      	 document.getElementById('eodconditionstr').value = totalemrconlist;
	
		document.getElementById('editconsultationFrm').submit();
	
	}else{
		$('#baselayout1loaderPopup').modal( "show" );
		document.getElementById('editconsultationFrm').submit();
	}
	 
	
	 
}

function editSaveOnlyConsultationNote(){
	$('#baselayout1loaderPopup').modal( "show" );
	var notes = nicEditors.findEditor( "editconsNote" ).getContent();
	document.getElementById('editconsNote').value = notes;
//	document.getElementById('edchkDischarge').checked = false;
	document.getElementById('editconsultationFrm').submit();
	
}

function confirmedDelete(){

	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {
	  return true;
	  }
	else
	  {
	  return false;
	  }

	} 


function setemruploaddocAjax(data,type){
	var url = "setEmr?data="+data+"&type="+type+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setemruploaddocAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function setemruploaddocAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
		}
	}
}


function setselectedtemplatedata(title,text){
	var str = '<b>' + title + '</b>' + ' :<br>'
	str = str + text;
	if(document.getElementById('maintextarea')){
		nicEditors.findEditor ("maintextarea").setContent(nicEditors.findEditor('maintextarea').getContent() + "<br>" + str+'<br>' );
	}else{
		nicEditors.findEditor ("consNote").setContent(nicEditors.findEditor('consNote').getContent() + "<br>" + str );
		nicEditors.findEditor ("editconsNote").setContent(nicEditors.findEditor('editconsNote').getContent() + "<br>" + str );
	
	}
	}


function setselectedtemplatedata(title,text){
   
    var url="getothertemplateEmr?id="+text+"";
    if (window.XMLHttpRequest) {
  req = new XMLHttpRequest();
 }
 else if (window.ActiveXObject) {
  isIE = true;
  req = new ActiveXObject("Microsoft.XMLHTTP");
 }   
               
    req.onreadystatechange = setselectedtemplatedataRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
 
}


function setselectedtemplatedataRequest(){
 
 if (req.readyState == 4) {
  if (req.status == 200) {
        // var str = '<b>' + title + '</b>' + ' :<br>'
        //str = str + text;
        var str=req.responseText;
        if(document.getElementById('maintextarea')){
    		nicEditors.findEditor ("maintextarea").setContent(nicEditors.findEditor('maintextarea').getContent() + "<br>" + str+'<br>' );
    	}else{
    		nicEditors.findEditor ("consNote").setContent(nicEditors.findEditor('consNote').getContent() + "<br>" + str );
            nicEditors.findEditor ("editconsNote").setContent(nicEditors.findEditor('editconsNote').getContent() + "<br>" + str );  	
    	}
        
  }
 }
}

function showdocfilter(){


$('#docfilterpopup').modal( "show" );
$('#document_details').modal( "hide" ); 
}

function showsharepopup(){
	$('#sharepopuo').modal( "show" );
}

function savesharedata(){
	var email = document.getElementById('pureemail').value;
	var fname =  document.getElementById('purefname').value;
	var lname = document.getElementById('purelname').value;
	var mob = document.getElementById('puremob').value;
	
	var diaryUser = document.getElementById('diaryUser').value;
	var clientname = document.getElementById('clientname').value;
	var condition = document.getElementById('condition').value;
	
	if(email==''){
		jAlert('error', 'Please enter email.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(validateEmail(email)==false){
			jAlert('error', 'Please enter valid email address.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}
	
	else if(fname==''){
		jAlert('error', 'Please enter first name.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(lname==''){
		jAlert('error', 'Please enter last name.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}
	else if(mob==''){
		jAlert('error', 'Please enter mobile number.', 'Error Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
	}else{
		 $('#baselayout1loaderPopup').modal( "show" );
		  var url="shareEmr?email="+email+"&fname="+fname+"&lname="+lname+"&mob="+mob+"&diaryUser="+diaryUser+"&clientname="+clientname+"&condition="+condition+" ";
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
			 }
			 else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
			 }   
			               
		    req.onreadystatechange = savesharedataRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	
	
}

function savesharedataRequest(){
	 if (req.readyState == 4) {
  		if (req.status == 200) {
  			$('#sharepopuo').modal( "hide" );
  			$('#baselayout1loaderPopup').modal( "hide" );
  			
  			jAlert('success', 'Link Shared Successfully.', 'Success Dialog');
		
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
  		}
  	}
}
var otp = '';
var sharr = 0;
function checkSharedUser(){
	var mob = document.getElementById('shmob').value;
	  var url="chshuserEmr?mob="+mob+" ";
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
			 }
			 else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
			 }   
			               
		    req.onreadystatechange = checkSharedUserRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
}

function checkSharedUserRequest(){
	if (req.readyState == 4) {
  		if (req.status == 200) {
  		
  			  var data = req.responseText;
  			  var temp = data.split('~');
  			  var r = temp[0];
  			  otp = temp[1];
  			  document.getElementById('sharedmob').value = r;
  			  sharr = r;
  			  if(r==0){
  			  	jAlert('error', 'Mobile number not matched!!', 'Error Dialog');
		
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
  			  }else{
  			  		$('#sharedmob').modal( "hide" );	
  			  		$('#otpemroppup').modal( "show" );
  			  
  			  	 
  			  }
  			  
  		
  		}
  		
  	}
}

function checkemrotp(){
	 document.getElementById('sharedmob').value = sharr;
	var eotp = 	document.getElementById('emrotp').value;
  	if(otp==eotp){
  		document.getElementById('shuserfrm').submit();
  	}else{
  			jAlert('error', 'OTP not matched!!', 'Error Dialog');

	setTimeout(function() {
		$("#popup_container").remove();
		removeAlertCss();
	}, alertmsgduration);
  	}	
}


function showconfidentialpopup(){
 $('#confidentialpopup').modal( "show" );
}

function saveConfidentialPassword(){

	var diaryUser = document.getElementById('diaryUser').value;
	var clientname = document.getElementById('clientname').value;
	var condition = document.getElementById('condition').value;
	
	var confpassd = document.getElementById('confpassd').value;
	
	if(confpassd==''){
			jAlert('error', 'Please enter password!!', 'Error Dialog');
		
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{

  var url="confEmr?diaryUser="+diaryUser+"&clientname="+clientname+"&condition="+condition+"&confpassd="+confpassd+" ";
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
			 }
			 else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
			 }   
			               
		    req.onreadystatechange = saveConfidentialPasswordRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		  }

}
function saveConfidentialPasswordRequest(){
	if (req.readyState == 4) {
  		if (req.status == 200) {
  				
  				$('#confidentialpopup').modal( "hide" );
  				
  				jAlert('success', 'Record saved successfully!!', 'Success Dialog');
		
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
  		}
  	}
  		
}

function checkConfidentialPassword(){

	var diaryUser = document.getElementById('diaryUser').value;
	var clientname = document.getElementById('clientname').value;
	var condition = document.getElementById('condition').value;
	
	var confpassd = document.getElementById('confvalpassd').value;
	
	if(confpassd==''){
			jAlert('error', 'Please enter password!!', 'Error Dialog');
		
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
	}else{
		
		var url="chconfEmr?diaryUser="+diaryUser+"&clientname="+clientname+"&condition="+condition+"&confpassd="+confpassd+" ";
		  if (window.XMLHttpRequest) {
			  req = new XMLHttpRequest();
			 }
			 else if (window.ActiveXObject) {
			  isIE = true;
			  req = new ActiveXObject("Microsoft.XMLHTTP");
			 }   
			               
		    req.onreadystatechange = checkConfidentialPasswordRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		    
		}
}

function checkConfidentialPasswordRequest(){
	if (req.readyState == 4) {
  		if (req.status == 200) {
  			 var r = req.responseText;
  			  
  			  document.getElementById('confdentialpass').value = r;
  			  if(r==0){
  			  	jAlert('error', 'Password not matched!!', 'Error Dialog');
		
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
  			  }else{
  			  	 document.getElementById('conffrm').submit();
  			  }
  		}
  	}
  		
}


function loadchangesemr() {
	
	      var r=document.getElementById("project").value;
	      if(r=="Treatment Details") {
	         
	            $('#treatment_details').modal('show');
	      }
	      else if(r=="Documents"){
	         $('#document_details').modal('show');
	      }else if(r=="Medical Records"){
	         $('#medical_records').modal('show');
	      }else if(r=="Prescription"){
	         $('#presscription_details').modal('show');
	      }else if(r=="Investigation"){
	         $('#investigation_details').modal('show');
	      }else if(r=="Patient Media"){
	         $('#patient_media').modal('show');
	      }
	      
	     
	}
	
	
	function filtertype(val){

     var docslist=document.getElementById("docslist");
     var admissionlist=document.getElementById("admissionlist");
     var dischargelist=document.getElementById("dischargelist");
     var assessmentlist=document.getElementById("assessmentlist");
     var optionformlist = document.getElementById("optionformlist");
     var declarationformlist = document.getElementById("declarationformlist");
     var optionformlist = document.getElementById("optionformlist");
     if(val=="Admission Form"){
          admissionlist.style="";
          dischargelist.style="display:none";
          assessmentlist.style="display:none";
          docslist.style="display:none";
          optionformlist.style="display:none"
          declarationformlist.style="display:none"
          optionformlist.style="display:none"
     }
     else if(val=="Discharge Form"){
          dischargelist.style="";
          assessmentlist.style="display:none";
          docslist.style="display:none";
          admissionlist.style="display:none";
          optionformlist.style="display:none"
        	declarationformlist.style="display:none"
        	optionformlist.style="display:none"
     } else if(val=="Assessment Report"){
         assessmentlist.style="";
         dischargelist.style="display:none";
          docslist.style="display:none";
          admissionlist.style="display:none";
          optionformlist.style="display:none"
        	declarationformlist.style="display:none"
        	optionformlist.style="display:none"    		  
     }
     else if(val=="All"){
        docslist.style="";
        assessmentlist.style="";
         dischargelist.style="";
          admissionlist.style="";
          optionformlist.style=""
        declarationformlist.style="display:grid"
        optionformlist.style="display:grid"
     }else if(val=="Optional Form"){
    	 dischargelist.style="";
         assessmentlist.style="display:none";
         docslist.style="display:none";
         admissionlist.style="display:none";
         optionformlist.style=""
         declarationformlist.style="display:none"
         optionformlist.style="display:none" 
     }
     else if(val=="Declaration Form"){
    	 dischargelist.style="display:none";
         assessmentlist.style="display:none";
         docslist.style="display:none";
         admissionlist.style="display:none";
         optionformlist.style="display:none"
         declarationformlist.style=""
         optionformlist.style="display:none" 
     }
     else if(val=="OT Form"){
    	 dischargelist.style="display:none";
         assessmentlist.style="display:none";
         docslist.style="display:none";
         admissionlist.style="display:none";
         optionformlist.style="display:none"
         declarationformlist.style="display:none"
         optionformlist.style="" 
     }
     else {
        docslist.style="";
        assessmentlist.style="display:none";
        dischargelist.style="display:none";
        admissionlist.style="display:none";
        declarationformlist.style="display:none"
        	 optionformlist.style="display:none" 
     }
     

}
	
	
	 function dispDIv(){
		   
	        if(document.getElementById("dispid").className==""){
	          
	             document.getElementById("dispid").className="hidden";
	        } else {
	         	document.getElementById("dispid").className="";
	        }
	   } 
	 function addnewCOndition1() {
		  var condiName= document.getElementById("newcondition").value;
	      var icdcode=document.getElementById("icdcode").value;
	      var diaryUser = document.getElementById("diaryUser1").value;
	      var url="saveconditionEmr?condition="+condiName+"&icdcode="+icdcode+"&diaryUser="+diaryUser+"";
	 	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	               
	    req.onreadystatechange = addnewCOndition1Request;
	    req.open("GET", url, true); 
	    req.send(null);	 
	  
	 }

	 function addnewCOndition1Request(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					jAlert('success', "Diagosis Added!.", 'Success Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					document.getElementById("newcondition").value ='';
					document.getElementById("icdcode").value ='';
					document.getElementById("dispid").className="hidden";
					document.getElementById("conditiontbody").innerHTML=req.responseText;
		         }
			}
		}
	 
		function refreshTemplateList() {
		  
		var url="refreshtemplatelistEmr";
	 	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	               
	    req.onreadystatechange = refreshTemplateListRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);	 
	  
	 }

	 function refreshTemplateListRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					jAlert('success', "Refresh done!.", 'Success Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					document.getElementById("templatetableid").innerHTML=req.responseText;
		         }
			}
		}

	 function setselectedtemplatedataNew(text){
		   
		    var url="getothertemplateEmr?id="+text+"";
		    if (window.XMLHttpRequest) {
		  req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
		  isIE = true;
		  req = new ActiveXObject("Microsoft.XMLHTTP");
		 }   
		               
		    req.onreadystatechange = setselectedtemplatedataNewRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		   
		 
		}


		function setselectedtemplatedataNewRequest(){
		 
		 if (req.readyState == 4) {
		  if (req.status == 200) {
		        // var str = '<b>' + title + '</b>' + ' :<br>'
		        //str = str + text;
		        var str=req.responseText;
		        nicEditors.findEditor ("consNote").setContent(nicEditors.findEditor('consNote').getContent() + "<br>" + str );
		        nicEditors.findEditor ("editconsNote").setContent(nicEditors.findEditor('editconsNote').getContent() + "<br>" + str );  
		  }
		 }
		}
		
		var selected=0;
		
		
		function searchdiagnosis(d){
			
					var url="getdiagnosisEmr?search="+d+"&selected="+selected+"";
					if (window.XMLHttpRequest) {
						  req = new XMLHttpRequest();
					 }
					 else if (window.ActiveXObject) {
						  isIE = true;
						  req = new ActiveXObject("Microsoft.XMLHTTP");
					 }   
					               
				    req.onreadystatechange = searchdiagnosisRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);
			
		}
		
		 function searchdiagnosisRequest(){
			 
			 if (req.readyState == 4) {
			  if (req.status == 200) {
			       
				    document.getElementById("conditiontbody").innerHTML= req.responseText;
			   }
			 }
		 }
		 
		 function setCheckedData(d){
			   
			   var noteid=d.value;
			 
				if(d.checked==true){
					
					var str = '';
					var txt = document.getElementById("ccck"+noteid).innerHTML;
					var existing = ''+nicEditors.findEditor('consNote').getContent() ;
					if(existing.includes("<h3 style='color: #15536E !important;'>Diagnosis:</h3>")){
						str = str + '<br><label>' + txt + ':</label>' ;
					}else{
						str = str + '<br><p><h3 style="color: #15536E !important;">Diagnosis:</h3></p><label>' + txt + ':</label>' ;
					}
					var temp=nicEditors.findEditor('consNote').getContent();
					var diagnosis="";
					if(temp.includes("<h3 style='color: #15536E !important;'>Diagnosis:</h3>")){
						 diagnosis = nicEditors.findEditor('consNote').getContent() + str;
					}else{
						 diagnosis = ''+nicEditors.findEditor('consNote').getContent() + str;
					}
					
					nicEditors.findEditor('consNote').setContent(diagnosis); 
					
					selected=selected+","+noteid;
					setAllDiagosis();
					selectdia();
				} 
			}
		 function setCheckedDataEdit(d){
			   
			   var noteid=d.value;
			 
				if(d.checked==true){
					var boxId="editconsNote";
					if(document.getElementById("maintextarea")){
						boxId="maintextarea";
					}
					var str = '';
					var txt = document.getElementById("econtxt"+noteid).innerHTML;
					
					var existing = ''+nicEditors.findEditor(boxId).getContent() ;
					if(existing.includes("<h3>Diagnosis:</h3>")){
						str = str + '<br><label>' + txt + ':</label>' ;
					}else{
						str = str + '<br><p><h3>Diagnosis:</h3></p><label>' + txt + ':</label>' ;
					}
					
					var diagnosis = nicEditors.findEditor(boxId).getContent() + str;
					nicEditors.findEditor(boxId).setContent(diagnosis); 
					
					selected=selected+","+noteid;
					setAllDiagosisEdit();
				} 
			}
		 
		 
		  function savediagnosisfast(){
				
				var condition= document.getElementById("newdiagnosis").value;
				
				if(condition=='' || condition==' '){
					 
					 jAlert('error', 'Diagnosis will not empty!', 'Error Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					 
				} else {
					//Speed up moving to Book appointment ajax
//					var url="savediagnosisfastIpd?condition="+condition+"";
					var url="savediagnosisfastBookAppointmentAjax?condition="+condition+"";
					if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = savediagnosisfastRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);   
				}
				
				
			}
			function savediagnosisfastRequest(){
				
				if (req.readyState == 4) {
					if (req.status == 200) {
						var str = '';
						var temp=req.responseText;
						var data=temp.split("~!");
						var noteid=data[0];
						
						
						selected=selected+","+noteid;
						if(data[1]==1){
							 jAlert('error', 'Diagnosis Already Exist!', 'Error Dialog');
								
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								
						}else{
							 jAlert('success', "Diagnosis Added Successfully!.", 'Success Dialog');
								
								setTimeout(function() {
									$("#popup_container").remove();
									removeAlertCss();
								}, alertmsgduration);
								var txt = document.getElementById("newdiagnosis").value;
								str = str + '<br><label>' + txt + ':</label>' ;
								var diagnosis = nicEditors.findEditor('consNote').getContent() + str;
								nicEditors.findEditor('consNote').setContent(diagnosis); 
								setAllDiagosisEmrJSON();
						}
						
						
					}
				}
			}
			
			function savediagnosisfastedit(){
				
				var condition= document.getElementById("newdiagnosisedit").value;
				
				if(condition=='' || condition==' '){
					 
					 jAlert('error', 'Diagnosis will not empty!', 'Error Dialog');
						
						setTimeout(function() {
							$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration);
					 
				} else {
					var url="savediagnosisfastIpd?condition="+condition+"";
					if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = savediagnosisfasteditRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);   
				}
				
				
			}
			function savediagnosisfasteditRequest(){
				
				if (req.readyState == 4) {
					if (req.status == 200) {
						var str = '';
						var noteid=req.responseText;
						var txt = document.getElementById("newdiagnosisedit").value;
						str = str + '<br><label>' + txt + ':</label>' ;
						var diagnosis = nicEditors.findEditor('editconsNote').getContent() + str;
						nicEditors.findEditor('editconsNote').setContent(diagnosis); 
						
						selected=selected+","+noteid;
						setAllDiagosisEdit();
						
					}
				}
			}
		 

		 
		 function setAllDiagosis(){
				
				var url="setalldiagnosisEmr?selected="+selected+"";
				
				if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = setAllDiagosisRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);    	
				
			}	 

			function setAllDiagosisRequest(){
				
				if (req.readyState == 4) {
					if (req.status == 200) {
					 
						   document.getElementById("conditiontbody").innerHTML=req.responseText;
					}
				}
			}
           
			 function setAllDiagosisEdit(){
					
					var url="setalldiagnosisEmr?selected="+selected+"";
					
					if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = setAllDiagosisEditRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);    	
					
				}	 

				function setAllDiagosisEditRequest(){
					
					if (req.readyState == 4) {
						if (req.status == 200) {
						 
							   document.getElementById("conditiontbody2").innerHTML=req.responseText;
						}
					}
				}
			
			function searchdiagnosisedit(d){
				
				var url="getdiagnosiseditEmr?search="+d+"&selected="+selected+"";
				if (window.XMLHttpRequest) {
					  req = new XMLHttpRequest();
				 }
				 else if (window.ActiveXObject) {
					  isIE = true;
					  req = new ActiveXObject("Microsoft.XMLHTTP");
				 }   
				               
			    req.onreadystatechange = searchdiagnosiseditRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);
		
	}
	
	 function searchdiagnosiseditRequest(){
		 
		 if (req.readyState == 4) {
		  if (req.status == 200) {
		       
			    document.getElementById("conditiontbody2").innerHTML= req.responseText;
		   }
		 }
	 }
	var data='';
	 function setOtherTemplateBySpeciality(specialityid,drid,val) {
		 data =val; 
		 var url="refreshtemplatelistEmr?diciplineid="+specialityid+"&drid="+drid+"";
	 	  if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
	               
	    req.onreadystatechange = setOtherTemplateBySpecialityRequest;
	    req.open("GET", url, true); 
	    
	    req.send(null);	 
	  
	 }

	 function setOtherTemplateBySpecialityRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					jAlert('success', "Refresh done!.", 'Success Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					
					if(data=='add'){
						document.getElementById("templatetableid").innerHTML=req.responseText;
						$('#selectothertemlate').modal('hide');
					}else{
						document.getElementById("edittemplatetableid").innerHTML=req.responseText;
						$('#editselectothertemlate').modal('hide');
					}
					
					
		         }
			}
		}	
	 
	//json functions
	 
	 
	 
	 function savediagnosisfastJSON(){
		 
			
			var condition= document.getElementById("newdiagnosis").value;
			
			if(condition=='' || condition==' '){
				 
				 jAlert('error', 'Diagnosis will not empty!', 'Error Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				 
			}
			else{
				
				
				  var dataObj={
						    
						    "condition" : "" + condition + "",
						   
						  };
						  var data1 =  JSON.stringify(dataObj);
						  $.ajax({

						   url : "savediagnosisfastJSONBookAppointmentAjax",
						   data : data1,
						   dataType : 'json',
						   contentType : 'application/json',
						   type : 'POST',
						   async : true,
						   // beforeSend: function () { LockScreen(); },
						   success : function(data) {
							
							   
								var str = '';
								var noteid= data.condition;
								var txt = document.getElementById("newdiagnosis").value;
								str = str + '<br><label>' + txt + ':</label>' ;
								var diagnosis = nicEditors.findEditor('consNote').getContent() + str;
								nicEditors.findEditor('consNote').setContent(diagnosis); 
								
								selected=selected+","+noteid;
								setAllDiagosis();
								
							   
							    },
						   error : function(result) {
						    console.log("error in saving diagnosis");
						   }
						  });
						   
				
			}
		}   
	 
	 
	 function setAllDiagosisEmrJSON(){
			
			var url="setalldiagnosisEmr?selected="+selected+"";
			

			  var dataObj={
					    
					    "selected" : "" + selected + "",
					   
					  };
					  var data1 =  JSON.stringify(dataObj);
					  $.ajax({

					   url : "setalldiagnosisemrJSONBookAppointmentAjax",
					   data : data1,
					   dataType : 'json',
					   contentType : 'application/json',
					   type : 'POST',
					   async : true,
					   // beforeSend: function () { LockScreen(); },
					   success : function(data) {
						
						   
						   document.getElementById("conditiontbody").innerHTML=data.responsej;
						    },
					   error : function(result) {
					    console.log("error in saving diagnosis");
					   }
					  });   	
			
		}	 
		function searchdiagnosisEmrJSON(d){
			
			var url="getdiagnosisEmr?search="+d+"&selected="+selected+"";
			  var dataObj={
					    "search" : "" + d + "",
					    "selected" : "" + selected + "",
					   
					  };
			  var data1 =  JSON.stringify(dataObj);
			  $.ajax({

			   url : "searchdiagnosisemrJSONBookAppointmentAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   // beforeSend: function () { LockScreen(); },
			   success : function(data) {
				
				   
				   document.getElementById("conditiontbody").innerHTML=data.responsej;
				    },
			   error : function(result) {
			    console.log("error in saving diagnosis");
			   }
			  });   	
			  
			  
	
}

		function savediagnosisfasteditEmrJSON(){
			
			var condition= document.getElementById("newdiagnosisedit").value;
			
			if(condition=='' || condition==' '){
				 
				 jAlert('error', 'Diagnosis will not empty!', 'Error Dialog');
					
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				 
			} else {
				var url="savediagnosisfastIpd?condition="+condition+"";
				  var dataObj={
						    
						    "condition" : "" + condition + "",
						   
						  };
						  var data1 =  JSON.stringify(dataObj);
						  $.ajax({

						   url : "savediagnosisfastJSONBookAppointmentAjax",
						   data : data1,
						   dataType : 'json',
						   contentType : 'application/json',
						   type : 'POST',
						   async : true,
						   // beforeSend: function () { LockScreen(); },
						   success : function(data) {
										
							   
								var str = '';
								var noteid= data.condition;
								var txt = document.getElementById("newdiagnosisedit").value;
								str = str + '<br><label>' + txt + ':</label>' ;
								var diagnosis = nicEditors.findEditor('editconsNote').getContent() + str;
								nicEditors.findEditor('editconsNote').setContent(diagnosis); 
								
								selected=selected+","+noteid;
								setAllDiagosisEditEmrJSON();
								
								
							   
							    },
						   error : function(result) {
						    console.log("error in saving diagnosis");
						   }
						  });
						   
				
				  
				
			}
			
			
		}
		
		
		function searchdiagnosiseditEmrJSON(d){
			
			var url="getdiagnosiseditEmr?search="+d+"&selected="+selected+"";
		
			
			  var dataObj={
					    "search" : "" + d + "",
					    "selected" : "" + selected + "",
					   
					  };
			  var data1 =  JSON.stringify(dataObj);
			  $.ajax({

			   url : "searchdiagnosiseditemrJSONBookAppointmentAjax",
			   data : data1,
			   dataType : 'json',
			   contentType : 'application/json',
			   type : 'POST',
			   async : true,
			   // beforeSend: function () { LockScreen(); },
			   success : function(data) {
				
				   
				   document.getElementById("conditiontbody2").innerHTML=data.responsej;
				    },
			   error : function(result) {
			    console.log("error in saving diagnosis");
			   }
			  });   	
			  
			
}

 	
		 function setAllDiagosisEditEmrJSON(){
				
				var url="setalldiagnosisEmr?selected="+selected+"";

				  var dataObj={
						    
						    "selected" : "" + selected + "",
						   
						  };
						  var data1 =  JSON.stringify(dataObj);
						  $.ajax({

						   url : "setalldiagnosisemrJSONBookAppointmentAjax",
						   data : data1,
						   dataType : 'json',
						   contentType : 'application/json',
						   type : 'POST',
						   async : true,
						   // beforeSend: function () { LockScreen(); },
						   success : function(data) {
							
							   
							   document.getElementById("conditiontbody2").innerHTML=data.responsej;
							    },
						   error : function(result) {
						    console.log("error in saving diagnosis");
						   }
						  });   	
				
			}	 

	/*function settoemrfollowup(){
	var days=	document.getElementById("followupsqty").value;
	var temp=nicEditors.findEditor('consNote').getContent();
	if(temp.includes("<h3>Follow up:")){
		
	}else{
		nicEditors.findEditor('consNote').setContent(temp+'<p><h3>Follow up:After '+days+' days</h3></p>');
	}
	}*/
	
	function followUpStatusChange(val){
		var fllowupdays= document.getElementById("fllowupdays").value;
		if(fllowupdays==''){
			jAlert('error', "Please select followup Date!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			if(val==true){
				document.getElementById("isbookapmtfollowup").value=1;
			}else{
				document.getElementById("isbookapmtfollowup").value=0;
			}
		}
		
	}
	
	function givefollowuptoemr(){
		var dates=	document.getElementById("fllowupdays").value;
//		days= parseInt(days);
//		var date1= new Date();
//		date1.setDate(date1.getDate()+days);
		var data=dates.replace(/-/g,"/")
//		var showdate= '<br><p><b>Follow Up Date:</b> '+date1.getDate()+'/'+(date1.getMonth()+1)+'/'+date1.getFullYear()+'</p>';
		var showdate= '<br><p><b>Follow Up Date:</b> '+data+'</p>';
		var temp=nicEditors.findEditor('consNote').getContent();
		if(document.getElementById("bkapmtipd").checked){
			document.getElementById("isbookapmtfollowup").value=1;
		}
		if(temp.includes("<b>Follow Up Date:")){
			
		}else{
			nicEditors.findEditor('consNote').setContent(temp+''+showdate);
		}
	}
	
	
	function givefollowuptoemr1(){
		var days=	document.getElementById("fllowupdays1").value;
		days= parseInt(days);
		var date1= new Date();
		date1.setDate(date1.getDate()+days);
		var showdate= '<br><br><p><b>Follow Up Date:</b> '+date1.getDate()+'/'+(date1.getMonth()+1)+'/'+date1.getFullYear()+'</p>';
		var temp=nicEditors.findEditor('editconsNote').getContent();
		if(temp.includes("<b>Follow Up Date:")){
			
		}else{
			nicEditors.findEditor('editconsNote').setContent(temp+''+showdate);
		}
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

	function logwisedata(val){
/*//		var tmp=val.split('-');
//		var currdate=tmp[2]+'-'+tmp[1]+'-'+tmp[0];
*/		
		document.getElementById("fromDate").value=val;
		document.getElementById("toDate").value=val;
		document.getElementById('datefilterfrm').submit();
	}
	

	function setClientNameEmr(id,type,typeName,payby,gpid){
			document.getElementById('popupclientid').value = id;
			document.getElementById('datefilterfrm').submit();
			$('#clientSearchDiv').modal( "hide" );
		
}
	
	function showAllPatientPopUp(){

		var url = "Client";



		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showAllPatientPopUpRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

		}
		function showAllPatientPopUpRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					var data=req.responseText.replace(/setClientName/g,"setClientNameEmr");	 
					document.getElementById("allPatient").innerHTML = data;
//					jQuery.noConflict(); 
					
					$('#clientSearchDiv').modal( "show" );
					document.getElementById("addpatientbtn").className="hidden";
					
		         }
			}
		}
		
		
		function searchPatient(){
			var searchText = document.getElementById("searchText").value;
			var url = "searchPatientClient?searchText="+searchText+"";

			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = searchPatientRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);

			}
			function searchPatientRequest(){
			if (req.readyState == 4) {
					if (req.status == 200) {
					
						var data=req.responseText.replace(/setClientName/g,"setClientNameEmr");	 
						document.getElementById("allPatient").innerHTML = data;
			         	
						
			         }
				}
			}
		
	function getPatientVital(clientid){
		var url = "vitalsClient?clientid="+clientid+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = getPatientVitalRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
	function getPatientVitalRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					$('#vital_details').modal( "show" );
					var data = req.responseText;
		  			var temp = data.split("@#@");
		  			document.getElementById("allvital").innerHTML=temp[0];
		  			document.getElementById("clindname").innerHTML=temp[1];
		         }
			}
		}
	
	
	function calulatebmi(){

		   var w=document.getElementById("weight").value; 
		   var h=document.getElementById("height").value; 
		    
		   var d=h/100; 
		   var bmi=w/(d*d);
		   var result=Math.round(bmi*100)/100;
		   if(result=='Infinity'){
			   result="0";
		   }
		   document.getElementById("bmi").value=result;	    

		}
	
	function savevitals(id,apmtid){
		var weight=0,height=0,bmi=0,pulse=0,sysbp=0,diabp=0,tempr=0,spo2=0;
		weight=document.getElementById("weight").value
		height=document.getElementById("height").value
		bmi=document.getElementById("bmi").value
		pulse=document.getElementById("pulse").value
		sysbp=document.getElementById("sysbp").value
		diabp=document.getElementById("diabp").value
		tempr=document.getElementById("tempr").value
		spo2=document.getElementById("spo2").value
		
		 var url="savebmiBookAppointmentAjax?height="+height+"&weight="+weight+"&bmi="+bmi+"&pulse="+pulse+"&sysbp="+sysbp+"&diabp="+diabp+"&patientId="+id+"&temprature="+tempr+"&spo="+spo2+"&apmtid="+apmtid+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = savevitalsRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		
		}
	function savevitalsRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					$('#insertvital').modal( "hide" );
					document.getElementById("weight").value=0;
					document.getElementById("height").value=0;
					document.getElementById("bmi").value=0;
					document.getElementById("pulse").value=0;
					document.getElementById("sysbp").value=0;
					document.getElementById("diabp").value=0;
					document.getElementById("tempr").value=0;
					document.getElementById("spo2").value=0;
		         }
			}
		}
		
function getPacsPopup(clientid1,id){
		/*var fromdate=document.getElementById("fromDate").value;
		var todate=document.getElementById("toDate").value;*/
	if(clientid1==null)
	{
	 clientid1=0;
	}
		var url ="getpacslistBookAppointmentAjax?clientid="+clientid1+"&abrivationid="+id+"" ;
		   //$( "#loaderPopup" ).modal( "show" );
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		
		req.onreadystatechange = getPacsPopupRequest;
		 
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}	
	
	
	function getPacsPopupRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				$( "#pacs_details" ).modal( "show" );
				$( "#loaderPopup" ).modal( "hide" );
				//var data = req.responseText;
				//alert(data);
		  			document.getElementById("pacsdetails").innerHTML=req.responseText; 
		  			
					 
			}}
	}
function showaddchargepopup(){
	$( "#addchargepopupipdopdinv" ).modal( "show" );
}

function showprocedure(clientid){
	
	$( "#procedure_details" ).modal( "show" );
}

function addsitting(clientid,opdid){
	var dataObj={
		"clientid" : ""+clientid+"",
		"opdid" : ""+opdid+"",
	}
	var data = JSON.stringify(dataObj);
	$.ajax({
		url : "getuserdiscriptionBookAppointmentAjax",
		data : ""+data+"",
		dataType : "json",
		contentType : "application/json",
		type : "POST",
		async : true,
		success : function(data) {
			document.getElementById("sitting_Clientid").value=clientid;
			document.getElementById("dept_opdid").value=opdid;
			$("#showsitting").modal( "show" );
			if(data.discription!=0){
				document.getElementById("dept").className='';
				document.getElementById("dept").value=data.discription;
				document.getElementById("dept").className='form-control chosen-select';
				$("#dept").trigger("chosen:updated");
				$(".chosen-select").chosen({allow_single_deselect: true});
				setDepartmentsitting(data.discription);
			}
		},
		error : function(result) {
			alert("Error");
		}
	});
	
	
}

function viewsitting(clientid){
	
	var url = "sittingfollowuplistEmr?clientid="+clientid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
	
    req.onreadystatechange = setsittingfollowuplistRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function setsittingfollowuplistRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str = req.responseText;
			 var data = str.split("~");
				document.getElementById("carttbody").innerHTML = data[0];
			
			$( "#sitting_details" ).modal( "show" );		
		}
	}
	
}

var newchargetype = "";
function filterEmrCharges(chargetype){
	//chargetype=document.getElementById("masterchargetype").options[document.getElementById('masterchargetype').selectedIndex].text;
	newchargetype = chargetype;
    var tpid=0;
    var ipdwhopay='Client';
    var ipdaddmissionid=0;
    var clientId = document.getElementById("clientname").value;
  	//var url = "chargeIpd?chargetype="+chargetype+"&ipdwhopay="+ipdwhopay+"&ipdtpid="+tpid+"&ipdaddmissionid="+ipdaddmissionid+"&clientId="+clientId+" ";
  	//  16 May 2018
    var url = "chargeBookAppointmentAjax?chargetype="+chargetype+"&ipdwhopay="+ipdwhopay+"&ipdtpid="+tpid+"&ipdaddmissionid="+ipdaddmissionid+"&clientId="+clientId+" ";
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = filterEmrChargesRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
		
		function filterEmrChargesRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
				var data= req.responseText;
				var data1= data.split("!@");
			 //lokesh 22/11/2018
				if(document.getElementById("compulsaryconsultant")){
					 document.getElementById("compulsaryconsultant").value =data1[1];
				}
				
			
			 document.getElementById("additionalChargeAjax").innerHTML = data1[0];
			  $("#chargeTYpe").trigger("chosen:updated");
		 	 $(".chosen").chosen({allow_single_deselect: true});
		 	 /*if(newchargetype=='IPD Visiting Charge' || newchargetype=='Consultation Charge'||data1[1]=='1'){
		 		//akash 20 July 2018
		 		 //lokesh on the req of praful sir 29/11/2018
		 		 setvisitingdrlist();
		 	 }else{
		 		document.getElementById("visitingconsltantdiv").innerHTML = "<input type='hidden' id='consultantdr' value='0'>";
		 	 }*/
		 	 //akash 20 July 2018
    			 	
				}
			}
		}
		
/*function setvisitingdrlist() {
	
	//var url = "getvisitingconsultantlistIpd";
	var url = "getvisitingconsultantlistBookAppointmentAjax";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	req.onreadystatechange = setvisitingdrlistRequest;
	req.open("GET", url, true);

	req.send(null);

}

function setvisitingdrlistRequest() {

	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("visitingconsltantdiv").innerHTML = req.responseText;
			$("#consultantdr").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			
		}
	}

}*/

function setAdditionalChargeAjax1(apmtTypeid){
	glbapmtTypeid = apmtTypeid;
	if(document.getElementById('chargeTYpe').value==0){
		document.getElementById('addchargebtn').disabled = 'disabled';
	}else{
		
		document.getElementById('addchargebtn').disabled = '';
	}
	
	if(document.getElementById('chargeTYpe').value==0){
		document.getElementById('mannualcharge').disabled = '';
		document.getElementById('charge').disabled = '';
		
	}else{
		document.getElementById('mannualcharge').value = '';
		document.getElementById('mannualcharge').disabled = 'disabled';
		document.getElementById('charge').disabled = '';
	}
	
	var masterchargetype = document.getElementById('masterchargetype').value;
	var url = "chargeAppointmentType?selectedAppointmentID="+apmtTypeid+"&masterchargetype="+masterchargetype+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setAdditionalChargeAjax1Request;
    req.open("GET", url, true); 
              
    req.send(null);
	
	
}

function setAdditionalChargeAjax1Request(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			 var str=req.responseText;
	         var data=str.split("~");
			/*document.getElementById('charge').value = currencySign+req.responseText; 
			ipdcharge = req.responseText;
			document.getElementById('hdncharge').value = req.responseText; 
			var charge = req.responseText;
			calcamount(charge);*/
			
	         document.getElementById('charge').value = data[0]; 
				ipdcharge = data[0];
				document.getElementById('hdncharge').value = data[0]; 
				var charge = data[0];
				calcamount(charge);
				
			
			if(document.getElementById('chargeTYpe').value==0){
				document.getElementById('charge').value = '';
				document.getElementById('amount').innerHTML = '';
			}
			document.getElementById('isindisharecharge').value = data[1]; 
			var nonedit=data[2];
			if(nonedit==1){
				document.getElementById("charge").readOnly = true;
			}else{
				document.getElementById("charge").readOnly = false;
			}
			if(data[3]=='true'){
				document.getElementById("charge").readOnly = false;
			}else{
				document.getElementById("charge").readOnly = true;
			}
		}
	}
	
}

var clientid='';
var clientname='';
function setChargeAmountEmr(){
	document.getElementById('addchargebtn').disabled = 'true';
	var packageid ='';
	var ipdclientid = document.getElementById('clientname').value;
	var ipdclientname = document.getElementById('patientname').value;
	var ipdpractitionername=document.getElementById('emrDoctorName').value;
	var chargetype = document.getElementById("chargeTYpe").value;
	var quantity = document.getElementById("quantity").value;
	var charge = document.getElementById("hdncharge").value;
	var mannualcharge = document.getElementById("mannualcharge").value;
	var ipdpractitionerid=document.getElementById("diaryUser1").value;
	if(document.getElementById("packageid")){
	 packageid = document.getElementById("packageid").value;
	}
	var date = document.getElementById("date").value;
	var payby = $("input[name='payBuy']:checked").val();
	
	var masterchargetype =  $("#masterchargetype option:selected").text();
	
	var markappointment = 1;
	
	
	clientid = document.getElementById('clientname').value;
	clientname = document.getElementById('patientname').value;
	//var type = document.getElementById('apmtType').value;
	var location = 0;
	var creditDebitCharge = $("input[name='creditDebitCharge']:checked").val();
	
//	var currentipdid=0;
//	if(document.getElementById("lastipdid")){
//		currentipdid=document.getElementById("lastipdid").value;
//	}
	
	document.getElementById("hdncdclientid").value = clientid;
	document.getElementById("hdncdclient").value = clientname;
	//document.getElementById("hdncdapmttype").value = type;
	document.getElementById("hdncdloc").value = location;
	document.getElementById("hdncdpayby").value = payby;
	document.getElementById("casdeskAdditional_creditDebitCharge").value = creditDebitCharge;
	
/*	document.getElementById("hdnciclientid").value = clientid;
	document.getElementById("hdncliclient").value = clientname;
	//document.getElementById("hdnciapmttype").value = type;
	document.getElementById("hdnciloc").value = location;
	document.getElementById("hdncipayby").value = payby;
	document.getElementById("hdncddate").value = date;
	document.getElementById("hdncidate").value = date;*/
	//lokesh 29/11/18
	
	var compulsary_con="0";
	if(document.getElementById("compulsaryconsultant")){
		 compulsary_con=document.getElementById("compulsaryconsultant").value;
	}
	var taxtypes="";
	var chargedescription="";
	if(document.getElementById("taxtypes")){
		if(document.getElementById('pcsaccount')){
			taxtypes=document.getElementById("taxtypes").value;
			chargedescription=document.getElementById("chargedescriptionnew").value;
			chargedescription=nicEditors.findEditor( "chargedescriptionnew" ).getContent();
			chargedescription=replaceAll(chargedescription,'&nbsp;',' ');
			chargedescription=replaceAll(chargedescription,'&','and');
			nicEditors.findEditor( "chargedescriptionnew" ).setContent("");
		}
	}
	//akash 02 feb 2018
	var visitingconsulatntdr =0;
	var isindisharecharge = 0;
	if(document.getElementById("isindisharecharge")){
		isindisharecharge =document.getElementById("isindisharecharge").value;
	}
	if(masterchargetype=='IPD Visiting Charge' || masterchargetype=='Consultation Charge' || isindisharecharge=='1'||compulsary_con=='1'){
		visitingconsulatntdr = document.getElementById('consultantdr').value;
	 }
	 visitingconsulatntdr =0;

	var cookieSelectedAppointmentid=document.getElementById("hdncondapmtId").value;

	/*var url = "savechargeIpdDashboard?ipdclientname="+ipdclientname+"&ipdpractitionername="+ipdpractitionername+"&ipdclientid="+ipdclientid+"&ipdpractitionerid="+ipdpractitionerid+"&chargetype="+chargetype+"&charge="+charge+"&payby="+payby+"&markappointment="+markappointment+"&quantity="+quantity+"&masterchargetype="+masterchargetype+"&mannualcharge="+mannualcharge+"&date="+date+"&visitingconsulatntdr="+visitingconsulatntdr+"";*/
	var url = "savechargeBookAppointmentAjax?ipdclientname="+ipdclientname+"&ipdpractitionername="+ipdpractitionername+"&ipdclientid="+ipdclientid+"&ipdpractitionerid="+ipdpractitionerid+"&chargetype="+chargetype+"&charge="+charge+"&payby="+payby+"&markappointment="+markappointment+"&quantity="+quantity+"&masterchargetype="+masterchargetype+"&mannualcharge="+mannualcharge+"&date="+date+"&visitingconsulatntdr="+visitingconsulatntdr+"&isindisharecharge="+isindisharecharge+"&packageid="+packageid+"&taxtypes="+taxtypes+"&chargedescriptnew="+chargedescription+"&apmtid="+cookieSelectedAppointmentid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setChargeAmountEmrRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);


	}
	function setChargeAmountEmrRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				   //document.getElementById("chargeTotalajax").innerHTML = req.responseText;
					setCashDesk1();		
				
				
			}
		}
	}
	
	
	
	function setCashDesk1(){
		var selectedUser = document.getElementById('clientname').value;;
		var cookiecommencing = document.getElementById("date").value;
		var cookieSelectedAppointmentid = 0;
		
		
		if(cookiecommencing!=''){
		 
		     var str= cookiecommencing.split("-");
		     var yymmdd= str[2]+"-"+str[1]+"-"+str[0];
		     cookiecommencing= yymmdd;
		}
		var cookieSelectedAppointmentid=document.getElementById("hdncondapmtId").value;
			//alert(selectedUser);
			//alert(cookiecommencing);
			//alert(cookieSelectedAppointmentid);
		/*var url = "cashDeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;*/
		var url = "cashDeskBookAppointmentAjax?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setCashDesk1Request;
		    req.open("GET", url, true); 
		              
		    req.send(null);


		}

		function setCashDesk1Request(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					   document.getElementById("cashDesk").innerHTML = req.responseText.replace(/confirmedDelete1/g,"confirmedDeleteEmr");
							//document.getElementById('chargeTotal').value = document.getElementById('hiddenTotal').value;	
					
					   if(document.getElementById('quantity')){
							document.getElementById('quantity').value=1;
						}
						if(document.getElementById('chargeTYpe')){
							document.getElementById('chargeTYpe').className="";
							document.getElementById('chargeTYpe').value='0';
							document.getElementById('chargeTYpe').className="form-control chosen";
							  $("#chargeTYpe").trigger("chosen:updated");
						  	   $(".chosen").chosen({allow_single_deselect: true});
							setAdditionalChargeAjax1(document.getElementById('chargeTYpe').value);
							if(document.getElementById('mannualcharge')){
								document.getElementById('mannualcharge').value="";
							}
						}
								
					
				}
			}
		}
		
			function confirmedDeleteEmr(id){
			//alert(id);
			
		  	var url = "deleteCashDeskCompleteApmt?selectedid="+id+"";
		  
		 
		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = confirmedDeleteEmrRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
			
		}

		function confirmedDeleteEmrRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					  // document.getElementById("thirdPartyAjax").innerHTML = req.responseText;
						setCashDesk1();
					
					
					
				}
			}
		}
function createipdopdChargeAndUpdateAccountEmr(action){
		gcash = action;
		
		var clientid = document.getElementById('clientname').value; 
		var practitionerid = document.getElementById("diaryUser1").value;
		var clientName = document.getElementById('patientname').value;
		
		var practitionerName = document.getElementById("emrDoctorName").value;
		var appointmentid = document.getElementById("hdncondapmtId").value;
		var tratmentepisodeid = '0';
		var treatmenntsessions = '0';
		//var location = document.getElementById('complocationid3').value;
		var location =1; 
		var date= document.getElementById("date").value;
		var ipd = 0;
		
		if(location==0){
			jAlert('error', 'Please select location.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			
			$('#baselayout1loaderPopup').modal( "show" );
			var ginventeredcharges = '0~';
			 $('.invunitchargecase').each(function() { //loop through each checkbox
				 ginventeredcharges = ginventeredcharges + this.value + '~';               
             });  
			
			var url = "updateAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+"&location="+location+"&ipd="+ipd+"&gpriscid="+gpriscid+"&ginvstid="+ginvstid+"&date="+date+"&ginventeredcharges="+ginventeredcharges+"";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = createipdopdChargeAndUpdateAccountEmrRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
		
		
		
	}

	function createipdopdChargeAndUpdateAccountEmrRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
			
			var invoiceid = req.responseText;
			
			if(gcash=='cash'){
				setInstantCashDesk(invoiceid);
			}else{
				
				$('#addchargepopupinv').modal( "hide" );
				
				$('#baselayout1loaderPopup').modal( "hide" );
				
				jAlert('success', 'Charge added successfully.', 'Success Dialog');
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				
			//	window.location.reload();
				
				}
			}
			
		}
	}