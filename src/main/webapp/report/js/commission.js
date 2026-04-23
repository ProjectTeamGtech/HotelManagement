function showInnerDiv(hiddenDetailsDiv,practitionerId){
	practitionerId1 = practitionerId;
	if(document.getElementById(hiddenDetailsDiv).style.display == ""){
		document.getElementById(hiddenDetailsDiv).style.display = "none";
	}
	else{
	document.getElementById(hiddenDetailsDiv).style.display = ""; 
	
	var url = "showClientDetailsCommission?practitionerId="+practitionerId+"";
	
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showInnerDivRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
}
function showInnerDivRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		
			document.getElementById("hiddenDetailsDiv1"+practitionerId1+"").style.display = ""; 
			document.getElementById("hiddenDetailsDiv1"+practitionerId1+"").innerHTML = req.responseText;
			
			 
			
         }
	}

}	


function saveValidation(){
	
	var diaryUser = document.getElementById("diaryUser").value;
	
	document.getElementById("practitionerError").innerHTML = "";
	
	var chk = 0;
	
	if(diaryUser == 0){
		var dUser = document.createElement("label");
		dUser.innerHTML = "Please Select Practitioner name";
	    document.getElementById('practitionerError').appendChild(dUser);
	    chk=1;
	}
	
	if(chk==1)
    {
       return false;
    }
    else
    {
    	return true;
    }
	
}

 function showDiv(){
	 
	 var url = "showPatientListClient";

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
				
					document.getElementById("allClient").innerHTML = req.responseText;
					
		         	
					
		         }
			}
	 
 }
		
		var filename = "";
		
		function preview(){
			
			//$.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a moment...</h3>' });
			var toDate = document.getElementById('toDate').value;			
			var fromDate = document.getElementById('fromDate').value;
			
			var url = "previewReportCommission?toDate="+toDate+"&fromDate="+fromDate+"";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = previewCommissionRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

		}
		function previewCommissionRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					
				//	$(document).ajaxStop($.unblockUI);
					filename = req.responseText;
				
					/*document.getElementById('previewId').innerHTML = '<h4><a href = liveData/commissionReport/'+filename+' target="blank" class="btn btn-primary">Preview</a></h4>';*/
					
					document.getElementById('sendMailCommissionId').innerHTML = '<button type="button" id="sendMailCommReportId" class="btn btn-primary" onclick="return sendEmailCommission()">Send Mail</button>';
					
					//showGrowl('', 'PDF file is Created Successfully !!', 'success', 'fa fa-check');
					jAlert('success', 'PDF Created successfully!!', 'Commission Report');
					
		         }
			}
	 
 }
		
		function sendEmailCommission(){
			
			document.getElementById('pdfReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/commissionReport/'+filename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="reportpdf" checked="checked"> </h4>';
			
			$('#sendEmailCommissionPopup').modal( "show" );
			
		}
		
		function sendReportrMail(){
			$('#sendEmailCommissionPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
			
			var to = document.getElementById('reportEmail').value;
			var cc = document.getElementById('reportccEmail').value;
			var subject = document.getElementById('reportsubject').value;
			var emailBody = nicEditors.findEditor( "emailBody" ).getContent();		
			var file = document.getElementById('reportpdf').value;
			
			//alert(emailBody);
			var url = "sendEmailCommission?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"";

			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = sendReportrMailRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);

			}
		function sendReportrMailRequest(){
			if (req.readyState == 4) {
					if (req.status == 200) {
					
						$('#sendEmailCommissionPopup').unblock();
						showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
						$('#sendEmailCommissionPopup').modal( "hide" );
						
			         }
				}
			
		}
		function submitPractitionershareReport(){
			var fromdate = document.getElementById("fromDate").value;
			var todate = document.getElementById("toDate").value;
			if(fromdate==''){
				jAlert('error', "Please enter from date!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else if(todate==''){
				jAlert('error', "Please enter to date!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
			}else{
				//parts[1] + "/" + parts[0] + "/" + parts[2]
				var parts = fromdate.split("/");
				fromdate = parts[1] + "/" + parts[0] + "/" + parts[2];
				
				var parts1 = todate.split("/");
				todate = parts1[1] + "/" + parts1[0] + "/" + parts1[2];
				
				var date1 = new Date(fromdate);
				var date2 = new Date(todate);
				var timeDiff = Math.abs(date2.getTime() - date1.getTime());
				var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
				if(diffDays>=32){
					jAlert('error', "Date differnce is greater one month!", 'Error Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
				}else{
					document.getElementById("userFrm").submit();
				}
				
			}
			
		}		
		