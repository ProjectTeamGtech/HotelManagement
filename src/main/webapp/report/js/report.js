function addItem()
    {	 
    	
        var selIndexes = "";
        var selectTag = document.getElementById("list2");
        for (var i = 0; i < selectTag.options.length; i++) {
            var optionTag = selectTag.options[i];
            if (optionTag.selected) {
                if (selIndexes.length > 0)
                    selIndexes += ", ";
                selIndexes += optionTag.text;
                var opt = document.createElement("option");
                document.getElementById("list1").options.add(opt);
                opt.text = optionTag.text;
                opt.value = optionTag.text;
                

               }
           
            
        }
        var src = document.getElementById("list2");
        
        // iterate through each option of the listbox
        for(var count= src.options.length-1; count >= 0; count--) {
     
             // if the option is selected, delete the option
            if(src.options[count].selected == true) {
      
                    try {
                             src.remove(count, null);
                             
                     } catch(error) {
                             
                             src.remove(count);
                    }
            }
        }
        
}

function removeItem(){
	var selIndexes = "";
    var selectTag = document.getElementById("list1");
    for (var i = 0; i < selectTag.options.length; i++) {
        var optionTag = selectTag.options[i];
        if (optionTag.selected) {
            if (selIndexes.length > 0)
                selIndexes += ", ";
            selIndexes += optionTag.text;
            var opt = document.createElement("option");
            document.getElementById("list2").options.add(opt);
            opt.text = optionTag.text;
            opt.value = optionTag.text;
            

           }
       
        
    }
    
	var src = document.getElementById("list1");
    
    // iterate through each option of the listbox
    for(var count= src.options.length-1; count >= 0; count--) {
 
         // if the option is selected, delete the option
        if(src.options[count].selected == true) {
  
                try {
                         src.remove(count, null);
                         
                 } catch(error) {
                         
                         src.remove(count);
                }
        }
    }
    
	 
     
}

function listbox_move(listID, direction) {
	 
    var listbox = document.getElementById(listID);
    var selIndex = listbox.selectedIndex;
 
    if(-1 == selIndex) {
        alert("Please select an option to move.");
        return;
    }
 
    var increment = -1;
    if(direction == 'up')
        increment = -1;
    else
        increment = 1;
 
    if((selIndex + increment) < 0 ||
        (selIndex + increment) > (listbox.options.length-1)) {
        return;
    }
 
    var selValue = listbox.options[selIndex].value;
    var selText = listbox.options[selIndex].text;
    listbox.options[selIndex].value = listbox.options[selIndex + increment].value;
    listbox.options[selIndex].text = listbox.options[selIndex + increment].text;
 
    listbox.options[selIndex + increment].value = selValue;
    listbox.options[selIndex + increment].text = selText;
 
    listbox.selectedIndex = selIndex + increment;
}

function showColumns(){
	document.getElementById('report_frm').submit();
	
}

var danAnalysisfilename = '';

function  dnaAnalysysPreview(){
// $.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a
// moment...</h3>' });
	var diaryUser = document.getElementById('diaryUser').value;			
	
	var url = "previewReportSummary?diaryUser="+diaryUser+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = dnaAnalysysPreviewRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function dnaAnalysysPreviewRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
		// $(document).ajaxStop($.unblockUI);
			
			danAnalysisfilename = req.responseText;
		
			/*
			 * document.getElementById('dnaAnalysysPreviewId').innerHTML = '<a
			 * href = liveData/DNAAnalysysReport/'+danAnalysisfilename+'
			 * target="blank" class="btn btn-primary">Preview</a>';
			 */
			
			document.getElementById('sendMailDNAAnalysysId').innerHTML =  '<button type="button" id="sendMailDNAAnalysisReportId" class="btn btn-primary" onclick="return sendEmailDNAAnalysis();">Send Mail</button>';
			
			// showGrowl('', 'PDF file is Created Successfully !!', 'success',
			// 'fa fa-check');
			jAlert('success', 'PDF Created successfully!!', 'DNA Analysis Report');
         }
	}

}

function sendEmailDNAAnalysis(){
	
	document.getElementById('DNAAnalysisReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/DNAAnalysysReport/'+danAnalysisfilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="dapdf" checked="checked"> </h4>';
	
	$('#sendEmaildnaAnalysisPopup').modal( "show" );
	
}

function senddnaAnalysisReportMail(){
	
	$('#sendEmaildnaAnalysisPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	var to = document.getElementById('danAnalysisEmail').value;
	var cc = document.getElementById('danAnalysisccEmail').value;
	var subject = document.getElementById('danAnalysissubject').value;
	var emailBody = document.getElementById('danAnalysisEmailBody').value;		
	var file = document.getElementById('dapdf').value;
	
	// alert(emailBody);
	var url = "sendEmailDNAAnalysisSummary?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = senddnaAnalysisReportMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function senddnaAnalysisReportMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				$('#sendEmaildnaAnalysisPopup').unblock();
				showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
				$('#sendEmaildnaAnalysisPopup').modal( "hide" );
				
	         }
		}
	
}

var appVsDnafilename = '';

function previewAppVsDNA(){
	
// $.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a
// moment...</h3>' });
	var fromDate = document.getElementById('fromDate').value;		
	var toDate = document.getElementById('toDate').value;	
	
	var url = "previewAPPTVsDnaReportSummary?fromDate="+fromDate+"&toDate="+toDate+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = previewAppVsDNARequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function previewAppVsDNARequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
						
		// $(document).ajaxStop($.unblockUI);
			appVsDnafilename = req.responseText;
		
			/*
			 * document.getElementById('previewAppVsDNAId').innerHTML = '<a
			 * href = liveData/ApptVsDNAReport/'+appVsDnafilename+'
			 * target="blank" class="btn btn-primary">Preview</a>';
			 */
			
			document.getElementById('sendMailAppVsDnaId').innerHTML = '<button type="button" id="sendMaildnaVsAppReportId" class="btn btn-primary" onclick="return sendEmailAppVsDna();">Send Mail</button>';
			
			// showGrowl('', 'PDF file is Created Successfully !!', 'success',
			// 'fa fa-check');
			jAlert('success', 'PDF Created successfully!!', 'Appointment Kept Vs DNA Report');
			
         }
	}

}

function sendEmailAppVsDna(){
	
	document.getElementById('appVsDnaReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/ApptVsDNAReport/'+appVsDnafilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="avdpdf" checked="checked"> </h4>';
	
	$('#sendEmailAppVsDnaPopup').modal( "show" );
	
}

function sendAppVsDnaReportMail(){
	
	$('#sendEmailAppVsDnaPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	var to = document.getElementById('appVsDnaEmail').value;
	var cc = document.getElementById('appVsDnaccEmail').value;
	var subject = document.getElementById('appVsDnasubject').value;
	var emailBody = document.getElementById('appVsDnaEmailBody').value;		
	var file = document.getElementById('avdpdf').value;
	
	// alert(emailBody);
	var url = "sendEmailappVsDnaSummary?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendAppVsDnaReportMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function sendAppVsDnaReportMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				$('#sendEmailAppVsDnaPopup').unblock();
				showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
				$('#sendEmailAppVsDnaPopup').modal( "hide" );
				
	         }
		}
	
}

var referalTreatfilename = '';

function previewReferalTreatment(){
	
// $.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a
// moment...</h3>' });
	
	var referal = document.getElementById('referal').value;			
	
	var url = "previewReferalTreatmentReportSummary?referal="+referal+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = previewReferalTreatmentRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function previewReferalTreatmentRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
		// $(document).ajaxStop($.unblockUI);
			referalTreatfilename = req.responseText;
		
			/*
			 * document.getElementById('previewReferalTreatmentId').innerHTML = '<a
			 * href = liveData/ReferalTreatmentReport/'+referalTreatfilename+'
			 * target="blank" class="btn btn-primary">Preview</a>';
			 */
		
			document.getElementById('sendMailreferalTreatmentId').innerHTML = '<button type="button" id="sendMailreferalReportId" class="btn btn-primary" onclick="return sendEmailReferalTreat();">Send Mail</button>';
			
			// showGrowl('', 'PDF file is Created Successfully !!', 'success',
			// 'fa fa-check');
			jAlert('success', 'PDF Created successfully!!', 'Treatment States By Referal Report');
			
         }
	}

}

function sendEmailReferalTreat(){
	
	document.getElementById('referalReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/ReferalTreatmentReport/'+referalTreatfilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="rtpdf" checked="checked"> </h4>';
	
	$('#sendEmailReferalPopup').modal( "show" );
}

function sendReferalReportMail(){
	$('#sendEmailReferalPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	
	var to = document.getElementById('referalEmail').value;
	var cc = document.getElementById('referalccEmail').value;
	var subject = document.getElementById('referalsubject').value;
	var emailBody = document.getElementById('referalEmailBody').value;		
	var file = document.getElementById('rtpdf').value;
	
	// alert(emailBody);
	var url = "sendEmailreferalSummary?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendReferalReportMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function sendReferalReportMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				$('#sendEmailReferalPopup').unblock();
				showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
				$('#sendEmailReferalPopup').modal( "hide" );
				
	         }
		}
	
}



var noApptActivityFilename = "";

function previewNoApptActivity(){
	
	// $.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a
	// moment...</h3>' });
	var url = "previewNoApptActivityClientRpt";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = previewNoApptActivityRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function previewNoApptActivityRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
			// $(document).ajaxStop($.unblockUI);
			noApptActivityFilename = req.responseText;
		
			/*
			 * document.getElementById('previewNoApptActivityId').innerHTML = '<a
			 * href = liveData/NoApptActivityReport/'+noApptActivityFilename+'
			 * target="blank" class="btn btn-primary">Preview</a>';
			 */
						
			document.getElementById('sendMailNoApptActivityId').innerHTML = '<button type="button" id="sendMailNoApptActivityReportId" class="btn btn-primary" onclick="return sendEmailNoApptActivity();">Send Mail</button>';
			
			// showGrowl('', 'PDF file is Created Successfully !!', 'success',
			// 'fa fa-check');
			jAlert('success', 'PDF Created successfully!!', 'No Appointment Activity Report');
			
         }
	}

}

function sendEmailNoApptActivity(){
	
	document.getElementById('noApptActivityReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/NoApptActivityReport/'+noApptActivityFilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="naapdf" checked="checked"> </h4>';
	
	$('#sendEmailNoApptActivityPopup').modal( "show" );
}

function sendNoApptActivityMail(){
	$('#sendEmailNoApptActivityPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	
	var to = document.getElementById('noApptActivityEmail').value;
	var cc = document.getElementById('noApptActivityccEmail').value;
	var subject = document.getElementById('noApptActivitysubject').value;
	var emailBody = document.getElementById('noApptActivityEmailBody').value;		
	var file = document.getElementById('naapdf').value;
	
	// alert(emailBody);
	var url = "sendEmailNoApptActivityClientRpt?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendNoApptActivityMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function sendNoApptActivityMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				$('#sendEmailNoApptActivityPopup').unblock();
				showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
				$('#sendEmailNoApptActivityPopup').modal( "hide" );
				
	         }
		}
	
}

var dnaNoFuturefilename = "";

function dnaNoFutureApptPreview(){
	
// $.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a
// moment...</h3>' });
var url = "dnaNoFutureApptPreviewClientRpt";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = dnaNoFutureApptPreviewRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function dnaNoFutureApptPreviewRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
			// $(document).ajaxStop($.unblockUI);
			dnaNoFuturefilename = req.responseText;
		
			/*
			 * document.getElementById('dnaNoFutureApptPreviewId').innerHTML = '<a
			 * href = liveData/dnaNoFutureApptReport/'+dnaNoFuturefilename+'
			 * target="blank" class="btn btn-primary">Preview</a>';
			 */
			
			document.getElementById('sendMaildnaNoFutureApptId').innerHTML = '<button type="button" id="sendMaildnaNoFutureApptReportId" class="btn btn-primary" onclick="return sendEmaildnaNoFutureAppt();">Send Mail</button>';
						
			// showGrowl('', 'PDF file is Created Successfully !!', 'success',
			// 'fa fa-check');
			jAlert('success', 'PDF Created successfully!!', 'DNA With No Future Appointment Report');
			
         }
	}

}

function sendEmaildnaNoFutureAppt(){
	
	document.getElementById('dnaNoFutureReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/dnaNoFutureApptReport/'+dnaNoFuturefilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="dnfapdf" checked="checked"> </h4>';
	
	$('#sendEmaildnaNoFutureapptPopup').modal( "show" );
}

function senddnaNoFutureApptMail(){
	$('#sendEmaildnaNoFutureapptPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	
	var to = document.getElementById('dnaNoFutureEmail').value;
	var cc = document.getElementById('dnaNoFutureccEmail').value;
	var subject = document.getElementById('dnaNoFuturesubject').value;
	var emailBody = document.getElementById('dnaNoFutureEmailBody').value;		
	var file = document.getElementById('dnfapdf').value;
	
	// alert(emailBody);
	var url = "sendEmaildnaNoFutureApptClientRpt?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = senddnaNoFutureApptMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function senddnaNoFutureApptMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				$('#sendEmaildnaNoFutureapptPopup').unblock();
				showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
				$('#sendEmaildnaNoFutureapptPopup').modal( "hide" );
				
	         }
		}
	
}

var noActivityFilename = '';

function previewNoActivity(){
	
// $.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a
// moment...</h3>' });
var url = "previewNoActivityClientRpt";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = previewNoActivityRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}
function previewNoActivityRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
		// $(document).ajaxStop($.unblockUI);
			noActivityFilename = req.responseText;
		
			document.getElementById('previewNoActivityId').innerHTML = '<a href = liveData/NoActivityReport/'+noActivityFilename+' target="blank" class="btn btn-primary">Preview</a>';
						
			document.getElementById('sendMailNoActivityId').innerHTML = '<button type="button" id="sendMailNoActivityReportId" class="btn btn-primary" onclick="return sendEmailNoActivity();">Send Mail</button>';
			
			// showGrowl('', 'PDF file is Created Successfully !!', 'success',
			// 'fa fa-check');
			jAlert('success', 'PDF Created successfully!!', 'No Activity Report');
			
         }
	}

}

function sendEmailNoActivity(){
	
	document.getElementById('noActivityReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/NoActivityReport/'+noApptActivityFilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="napdf" checked="checked"> </h4>';
	
	$('#sendEmailNoActivityPopup').modal( "show" );
}


function sendNoActivityMail(){
	$('#sendEmailNoActivityPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	
	var to = document.getElementById('noActivityEmail').value;
	var cc = document.getElementById('noActivityccEmail').value;
	var subject = document.getElementById('noActivitysubject').value;
	var emailBody = document.getElementById('noActivityEmailBody').value;		
	var file = document.getElementById('napdf').value;
	
	// alert(emailBody);
	var url = "sendEmailNoActivityClientRpt?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendNoActivityMailRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
function sendNoActivityMailRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				$('#sendEmailNoActivityPopup').unblock();
				showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
				$('#sendEmailNoActivityPopup').modal( "hide" );
				
	         }
		}
	
}

var treatmentfilename = "";
function treatmentEpisodePreview(){
	
	// $.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a
	// moment...</h3>' });
	var url = "treatmentEpisodePreviewClinical";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = treatmentEpisodePreviewRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	}
	function treatmentEpisodePreviewRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				
				// $(document).ajaxStop($.unblockUI);
				treatmentfilename = req.responseText;
			
				/*
				 * document.getElementById('treatmentPreviewId').innerHTML = '<label></label><a
				 * href = liveData/TreatmentEpisodeReport/'+treatmentfilename+'
				 * target="blank" class="btn btn-primary">Preview</a>';
				 */
				
				document.getElementById('sendMailTreatmentId').innerHTML = '<label></label><button type="button" id="sendMailTreatmentReportId" class="btn btn-primary" onclick="return sendEmailTreatment();">Send Mail</button>';
							
				// showGrowl('', 'PDF file is Created Successfully !!',
				// 'success', 'fa fa-check');
				jAlert('success', 'PDF Created successfully!!', 'Treatment Episode List');
				
	         }
		}
}
	
	function  sendEmailTreatment(){
		
		document.getElementById('treatmentReportMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/TreatmentEpisodeReport/'+treatmentfilename+' target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="treatmentpdf" checked="checked"> </h4>';
		
		$('#sendEmailTreatmentPopup').modal( "show" );
	}
	
	function sendTreatmentReportMail(){
		
		$('#sendEmailTreatmentPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
		
		var to = document.getElementById('treatmentEmail').value;
		var cc = document.getElementById('treatmentccEmail').value;
		var subject = document.getElementById('treatmentsubject').value;
		var emailBody = document.getElementById('treatmentEmailBody').value;		
		var file = document.getElementById('treatmentpdf').value;
		
		// alert(emailBody);
		var url = "sendEmailTreatmentEpisodeClinical?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&file="+file+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = sendTreatmentReportMailRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);

		}
	function sendTreatmentReportMailRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
				
					$('#sendEmailTreatmentPopup').unblock();
					showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
					$('#sendEmailTreatmentPopup').modal( "hide" );
					
		         }
			}
		
	}

	function selectKPIArea(){
		document.getElementById("kpidashboardform").submit();
	}
	
	function selectAllIndicator(val){
		  if(val.checked==true){
			   
			   $('.akash').each(function() {
				   
				     this.checked=true;
				    
			   });
			   
		   } else {
			   $('.akash').each(function() {
				   
				     this.checked=false;
				    
			   });
			   
			   
		   }
	}
	
	function saveKPIIndicator(){
		var ids="0";
		$('.akash').each(function() { 
			if(this.checked == true){
			    ids=ids+","+this.value;
			} 
		});
		if(ids=='0'){
			jAlert('error', "Please select at least one indicator!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}else{
			document.getElementById("allindicatorid").value = ids;
			document.getElementById("indicatorform").submit();
		}
		   
	}
	
	var val1 ="";
function disselectIndicatorCheckbox(val,indicator){
		val1 = val;
		 var t=confirm("Do you Want to Deselect?");
		    if(t==true){
		    	var url = "updateindicatormasterKPI?val="+val+"&indicator="+indicator+"";
				if (window.XMLHttpRequest) {
						req = new XMLHttpRequest();
					}
					else if (window.ActiveXObject) {
						isIE = true;
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}   
				               
				    req.onreadystatechange = disselectIndicatorCheckboxRequest;
				    req.open("GET", url, true); 
				              
				    req.send(null);

		    } else {
		       document.getElementById("checki"+val).checked = true;
		    }
		
		}
	function disselectIndicatorCheckboxRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					document.getElementById("kpitd"+val1).innerHTML = req.responseText;
		         }
			}
		
	}
	
	
	function saveIndicator(){
		var areaid = document.getElementById("areaid").value;
		var indicator = document.getElementById("indicator").value;
		var formula_desc = document.getElementById("formula_desc").value;
		var formula = document.getElementById("formula").value;
		var no_of_input = document.getElementById("no_of_input").value;
		
	  	 if(areaid=='0'){
	      	jAlert('error', "please select area!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
				 return false;	
	    
	      	}
	      	else if(indicator==''){
	      	jAlert('error', "please enter indicator!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
				 return false;	
	      	}
	      	else if(formula_desc==''){
		      	jAlert('error', "please enter formula description!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 	
					 return false;	
		      	}
	      	else if(formula==''){
		      	jAlert('error', "please enter formula!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 	
					 return false;	
		      	}
	      	else if(no_of_input==''){
		      	jAlert('error', "please enter number of input!", 'Error Dialog');	
					setTimeout(function() {
						$("#popup_container").remove();
							removeAlertCss();
						}, alertmsgduration); 	
					 return false;	
		      	}
	  	 
	      	else{
	      		return true;
	      	}

	}
	
	function setNabhSubcatagory(id) {
		var url="getnabhsubcatagoryKPI?id="+id+"";
	     if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		 }   
	     req.onreadystatechange = setNabhSubcatagoryRequest;
	     req.open("GET", url, true); 
	     req.send(null);   
	 }
	 
	 
	function setNabhSubcatagoryRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
		          document.getElementById("nabhsubcatdiv").innerHTML=req.responseText;
		           $("#nabhsubcatalistid").trigger("chosen:updated");
				   $(".chosen").chosen({allow_single_deselect: true});
	         }
		}	 
	}
	
	function submitmainform() {
		var todate = document.getElementById("todate").value;
		var fromdate = document.getElementById("fromdate").value;
		if(todate==''){
			jAlert('error', "please enter todate!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else if(fromdate=''){
			jAlert('error', "please enter fromdate!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
		}else{
			var vari = dateDiff(fromdate, todate);
		}
	}
	
	function dateDiff(d1str, d2str) {
	    var d1 = new Date(d1str),
	        d2 = new Date(d2str);
	    return (d2.getTime() - d1.getTime()) / 1000 / 60 / 60 / 24 // diff in
																	// days
	}
	
	function count(){
		
		var x= document.getElementById("count").innerHTML;
		document.getElementById("printcount").innerHTML = "Count : "+x;
	}
	
	
	/*
	 * function give_expirey_date(clinicid,value){ var url =
	 * "giveexpirydateUserAdministration?clinicid="+clinicid+"&value="+value+"";
	 * $('#baselayout1loaderPopup').modal( "show" ); if (window.XMLHttpRequest) {
	 * req = new XMLHttpRequest(); } else if (window.ActiveXObject) { isIE =
	 * true; req = new ActiveXObject("Microsoft.XMLHTTP"); }
	 * 
	 * req.onreadystatechange = give_expirey_dateRequest; req.open("GET", url,
	 * true);
	 * 
	 * req.send(null); }
	 * 
	 * function give_expirey_dateRequest(){ if (req.readyState == 4) { if
	 * (req.status == 200) { window.location.reload(); } } }
	 */
	
	
	
	
	function deactivateClinic(ip,active){
		var url = "active_deactive_clinicUserAdministration?ip="+ip+"&active="+active+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deactivateClinicRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function deactivateClinicRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
				}
			}
	}
	
	
	
	function submitmis(){
		var fromdate = document.getElementById("fromDate").value;
		var todate = document.getElementById("toDate").value;
		if(fromdate==''){
			jAlert('error', "Please enter from date!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		}else if(todate==''){
			jAlert('error', "Please enter to date!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			return false;
		}else{
			// parts[1] + "/" + parts[0] + "/" + parts[2]
			var parts = fromdate.split("-");
			fromdate = parts[1] + "/" + parts[0] + "/" + parts[2];
			
			var parts1 = todate.split("-");
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
				return false;
			}else{
				$('#baselayout1loaderPopup').modal( "show" );
				document.getElementById("mischartfrm").submit();
				return true;
			}
			
		}
		
	}

	
	
	$(document).ready(function() {
	     $(function() {
	         $("#search").autocomplete({     
	             source : function(request, response) {
	               $.ajax({
	                    url : "searchmedMaster",
	                    type : "GET",
	                    data : {
	                           term : request.term
	                    },
	                    dataType : "json",
	                    success : function(data) {
	                          response(data);
	                    }
	             });
	          }
	      });
	   });
	});
	
	
	function give_expirey_date(clinicid,value,type){
		var url = "giveexpirydateUserAdministration?clinicid="+clinicid+"&value="+value+"&type="+type;
		$('#baselayout1loaderPopup').modal( "show" );
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = give_expirey_dateRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function give_expirey_dateRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
				}
			}
	}
	
	
	
	
	function deactivateClinic(ip,active){
		var url = "active_deactive_clinicUserAdministration?ip="+ip+"&active="+active+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deactivateClinicRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function deactivateClinicRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
				}
			}
	}
	
	
	function setSupportReqStatus(status){
		
		var ticketid=document.getElementById("ticketid").value;
		
var url = "setsupportreqstatusSupport?ticketid="+ticketid+"&status="+status+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setSupportReqStatusRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function  setSupportReqStatusRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				/* window.location.reload(); */
				}
			}
	}
	
function setSupportReqExecutive(name){
		
		var ticketid=document.getElementById("ticketid").value;
		
var url = "setsupportreqexecutiveSupport?ticketid="+ticketid+"&name="+name+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setSupportReqExecutiveRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function  setSupportReqExecutiveRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				/* window.location.reload(); */
				}
			}
	}

function setsupportremark(){
		
		var ticketid=document.getElementById("ticketid").value;
		var remark=document.getElementById("remark").value;
		var priority=document.getElementById("priority").value;
		var time_taken=document.getElementById("time_taken").value;
		var ischargable=document.getElementById("is_charged").checked;
		var chargable="0";
		if(ischargable){
			chargable="1";
		}
		var url = "setsupportremarkSupport?ticketid="+ticketid+"&remark="+remark+"&priority="+priority+"&time_taken="+time_taken+"&chargable="+chargable;
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setsupportremarkRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
	function  setsupportremarkRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
				}
			}
	}
	
	
var tick='';
var whoglo='';
function sendsupportmsg(who,ticketid){
		tick=ticketid;
		whoglo=who;
		var msg=document.getElementById("msg"+ticketid).value;
		
		var url = "sendmsgSupport?ticketid="+ticketid+"&msg="+msg+"&who="+who+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = sendsupportmsgRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	
function sendsupportmsgRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			loadsupportconversation(tick,whoglo);
			}
		}
}
	

function createticket(){

	$('#ticketadd').modal( "show" );
}

function  getuserlistsupport(value){
	var url = "getalluserlistSupport?clinicid="+value;
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = getuserlistsupportReq;
    req.open("GET", url, true); 
              
    req.send(null);

}
	
	
function getuserlistsupportReq(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			document.getElementById("userlist").innerHTML="<label>Clinic Users</label>"+req.responseText;
			$("#useres").trigger("chosen:updated");
			  $(".chosen-select").chosen({allow_single_deselect: true});
			}
		}

}



function getusermobile(obj){
	var value=obj.value;
	var str= value.split("(");
	var text=str[1].replace(')','');
	document.getElementById("mbl").value=text;

	
	
}


function submitticketgenform(){
	var clinicids=document.getElementById("clinicids").value;
	if(document.getElementById("clinicids").value==''){
		document.getElementById("error").innerHTML="Select Clinic First !";
		return false;
	}
	var tu=document.getElementById("useres").value;
	if(document.getElementById("useres").value==''||document.getElementById("useres").value=='()'){
		document.getElementById("error").innerHTML="Select User First !";
		return false;
	}
	
	var qt=document.getElementById("query_type").value;
	if(document.getElementById("query_type").value=='0'){
		document.getElementById("error").innerHTML="Select Query Type First !";
		return false;
	}
	var q=document.getElementById("query").value;
	if(document.getElementById("query").value==''){
		document.getElementById("error").innerHTML="Enter Query  First !";
		return false;
	}
	if(document.getElementById("query").value==''){
		document.getElementById("error").innerHTML="Enter Query  First !";
		return false;
	}
	else{
		$('#baselayout1loaderPopup').modal( "show" );
		document.getElementById("ticketgenmodalform").submit();
	}
}



function sethospitalstatus(clinicid,status){
var url = "active_deactive_clinicUserAdministration?clinicid="+clinicid+"&status="+status;

$('#baselayout1loaderPopup').modal( "show" );
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = sethospitalstatusReq;
    req.open("GET", url, true); 
              
    req.send(null);
}

function sethospitalstatusReq(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
			}
		}
}



function setpymentreportstatus(status){
	var id=document.getElementById('statusid').value
	var url = "setpaymentreportnotesstatusChargesRpt?id="+id+"&status="+status;

	$('#baselayout1loaderPopup').modal( "show" );
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setpymentreportstatusReq;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}

	function setpymentreportstatusReq(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				window.location.reload();
				}
			}
	}
	
	
	function getconversationall(id,type){
		var x=document.getElementById('newrow'+id).className;
		if(x=='hidden'){
			document.getElementById('newrow'+id).className='';
			document.getElementById('newcol'+id).innerHTML='';
		}else{
			document.getElementById('newrow'+id).className='hidden';
		}
		loadsupportconversation(id,type);
	}
	
	
	
	function loadsupportconversation(ticket,type){
		
		 var dataObj= {
				"ticketid":""+ticket+"", 
				"type":""+type+"", 
		 };
		 var data1 =  JSON.stringify(dataObj);
		 $.ajax({

		  url : "loadsupportconversationSupport",
		  data : data1,
		  dataType : 'json',
		  contentType : 'application/json',
		  type : 'POST',
		  async : true,
		  // beforeSend: function () { LockScreen(); },
		  success : function(data) {
			
			  document.getElementById('newcol'+ticket).innerHTML=data.datax;
			  
		    },
		  error : function(result) {
		   console.log("error");
		  }
		 });
	}
	
	
	
	function submitl(id){
		$('#baselayout1loaderPopup').modal( "show" );
		document.getElementById('ppp'+id).innerHTML="<input type='hidden' name='ticketid' value='"+id+"' >";
		document.getElementById('form'+id).submit();
	
		
	}
	
	function requestforotp(btn){
		var mobileNo=document.getElementById('mbl').value;
		if(mobileNo==''){
			alert('Please Enter Contact Number !');
		}else{
			btn.style.display = "none";
				setTimeout(function(){
					btn.style.display = "block"; 
				 }, 10000);
			 var dataObj= {
						"mobileNo":""+mobileNo+"", 
						
				 };
				 var data1 =  JSON.stringify(dataObj);
				 $.ajax({

				  url : "generateotpSupport",
				  data : data1,
				  dataType : 'json',
				  contentType : 'application/json',
				  type : 'POST',
				  async : true,
				  // beforeSend: function () { LockScreen(); },
				  success : function(data) {
					
					  document.getElementById('otpbtn').value=data.datax;
					  
				    },
				  error : function(result) {
				   console.log("error");
				  }
				 });
		}
	}
	
	
function openConsumePopup(vaccinationid){
	 var dataObj= {
		"vaccinationid":""+vaccinationid+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "openconsumedataBookAppointmentAjax",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  document.getElementById('consume_medlist').innerHTML=data.datax;
		  document.getElementById('consume_clientid').value=data.clientid;
		  document.getElementById('consume_vaccinationid').value=vaccinationid;
		  document.getElementById('consume_clientname').innerHTML=data.clientname;
		  document.getElementById('consume_vaccinationname').innerHTML=data.vaccinationname;
		  $("#newmedicine").trigger("chosen:updated");
		  $(".chosen").chosen({allow_single_deselect: true});
		  $('#consumemodel').modal( "show" );
	    },
	    error : function(result) {
	    	console.log("error");
	    }
	 });
}


function sendVaccineSMS(vaccineid){
			$("#dashboardloaderPopup").modal('show');
	 		var dataObj= {
				"vaccineid":""+vaccineid+""
			 };
			 var data1 =  JSON.stringify(dataObj);
			 $.ajax({

			  url : "sendvaccinesmsmanualCommonnew",
			  data : data1,
			  dataType : 'json',
			  contentType : 'application/json',
			  type : 'POST',
			  async : true,
			  // beforeSend: function () { LockScreen(); },
			  success : function(data) {
				  
				  document.getElementById("vaccinationfrm").submit();
			  },
			    error : function(result) {
			    	console.log("error");
			    }
			 });
}

function consumevaccination() {
	var newmid=document.getElementById("newmedicine").value;
	if(newmid=='0'){
		jAlert('error', "Please select product!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		$("#dashboardloaderPopup").modal('show');
			var clientid = document.getElementById('consume_clientid').value;
			var vaccinationid = document.getElementById('consume_vaccinationid').value;
			 var dataObj= {
				"clientid":""+clientid+"", 
				"newmid":""+newmid+"", 
				"vaccinationid":""+vaccinationid+"", 
			 };
			 var data1 =  JSON.stringify(dataObj);
			 $.ajax({

			  url : "saveconsumedataBookAppointmentAjax",
			  data : data1,
			  dataType : 'json',
			  contentType : 'application/json',
			  type : 'POST',
			  async : true,
			  // beforeSend: function () { LockScreen(); },
			  success : function(data) {
				  
				  document.getElementById("vaccinationfrm").submit();
			  },
			    error : function(result) {
			    	console.log("error");
			    }
			 });
	}
}

// 13/08/2020
var consume_vacinationid="0";
var consume_clientid="0";
function showAddchargePopupConsume(id,clientid){
	consume_vacinationid=id;
	consume_clientid =clientid;
	var url = "truncateBookAppointmentAjax";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = showAddchargePopupConsumeRequest;
    req.open("GET", url, true); 
    req.send(null);
}

function showAddchargePopupConsumeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			addOutoConsumeCharge();
		}
	}
	
}


function addOutoConsumeCharge(){
	var url = "setchargesofconsumptionBookAppointmentAjax?vaccinationid="+consume_vacinationid+"&clientid="+consume_clientid+" ";
	   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = addOutoConsumeChargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}
function addOutoConsumeChargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			setconsumptionCashDesk();
			$('#addchargepopupconsume').modal( "show" );
		}
	}
}

// ipd and opd add charges

function setconsumptionCashDesk(){
	var selectedUser = consume_clientid;
	var cookiecommencing = '';
	var cookieSelectedAppointmentid = 0;
	var url = "cashDeskBookAppointmentAjax?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+cookieSelectedAppointmentid+"" ;
		
	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setconsumptionCashDeskRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

	function setconsumptionCashDeskRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				   document.getElementById("cashDesk31").innerHTML = req.responseText;
				   document.getElementById('chargeTotal3').value = document.getElementById('hiddenTotal').value;
			}
		}
	}
	
	
	var opdcash = '';
	function createChargeAndUpdateAccount(action){
		$("#dashboardloaderPopup").modal('show');
		opdcash = action;
		var clientid = consume_clientid; 
		var practitionerid = "0";
		var clientName = "0";
		var practitionerName = "";
		var appointmentid = "0";
		var tratmentepisodeid = "0";
		var treatmenntsessions = "0";
		var location = "1";
		
		if(location==0){
			jAlert('error', 'Please select location.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			var ipd = 0;
			var url = "updateAccountCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+"&location="+location+"&ipd="+ipd+"&vaccineId="+consume_vacinationid+"";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = createChargeAndUpdateAccountRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
	}
	var cashdesh_invoiceid="";
	function createChargeAndUpdateAccountRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var invoiceid = req.responseText;
				cashdesh_invoiceid = invoiceid;
				updateConsumptionStatus();
			}
		}
	}
	
	function updateConsumptionStatus(){
		var url = "updatevaccinationstatusBookAppointmentAjax?vacinationid="+consume_vacinationid+"";
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = updateConsumptionStatusRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	
function updateConsumptionStatusRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			$("#dashboardloaderPopup").modal('show');
			
			if(opdcash=='cash'){
				$('#addchargepopupconsume').modal( "hide" );
				setInstantCashDesk(cashdesh_invoiceid);
			}else{
				$("#dashboardloaderPopup").modal('hide');
				jAlert('success', 'Charge added successfully.', 'Success Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				document.getElementById("vaccinationfrm").submit();
			}
		}
	}
}

function setInstantCashDesk(invoiceid){
	document.getElementById('cashClientid').value = consume_clientid;
	document.getElementById('cashclientname').value = '';
	document.getElementById('cashPayby').value = '';
	document.getElementById('cashLocationid').value = 1;
	document.getElementById('cashAppointmentid').value = 0;
	document.getElementById('cashinvoiceid').value = invoiceid;
	document.getElementById('cashdeskfrm1').submit();
}
	
function setrefresh(){
	  
	 
	  window.location.reload();
}


function warnToDeleteInvoice(invId){
	var flag=confirm("Are you sure? you want To delete a paid invoice, will delete all data related to this admission/appointment including investigations, prescriptions and other invoices and payments ");
	if(flag){
		deleteInvoiceRelatedData(invId);
	}
}


function deleteInvoiceRelatedData(invId){
	
	var url="deleteinvoicerelateddataCommonnew?invId="+invId+"";
    if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	 }   
    req.onreadystatechange = deleteInvoiceRelatedDataResponse;
    req.open("GET", url, true); 
    req.send(null);   
	
}

function deleteInvoiceRelatedDataResponse(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			window.location.reload();
			}
		}
}

function openMultiConsumePopup(clientId){
	 var dataObj= {
		"clientId":""+clientId+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "openmulticonsumeBookAppointmentAjax",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  document.getElementById('multiconsumetbody').innerHTML=data.datax;
		  document.getElementById('multiconsume_clientid').value=data.clientid;
		  document.getElementById('multiconsume_clientname').innerHTML=data.clientname;
		  document.getElementById('multiconsume_abrivation').innerHTML=data.abrivationid;
		  $('.multiclass').each(function() { 
			  $("#newmedicine"+this.value).trigger("chosen:updated");
			  $(".chosen").chosen({allow_single_deselect: true});
		  });
		  $('#multiconsumemodel').modal( "show" ); 
	    },
	    error : function(result) {
	    	console.log("error");
	    }
	 });
}

function selectAllVaccination(val){
	if (val== true) {
		$('.multiclass').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
	} else {
		$('.multiclass').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
}

function setVaccineConsumeProduct(val){
	var vaccinationId = document.getElementById("vaccinationId"+val).checked;
	var globalProductId =  document.getElementById("newmedicine"+val).value;
	if(!vaccinationId){
		document.getElementById("newmedicine"+val).className="";
		document.getElementById("newmedicine"+val).value="0";
		document.getElementById("newmedicine"+val).className="form-control chosen";
		$("#newmedicine"+val).trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		
		jAlert('error', "Please select checkbox!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		var alreadySelectFlag= false;
		var totalStock=0;
		$(".multiclass").each(function(){
			if(this.checked==true){
				var val1 = this.value;
				var localProductId =  document.getElementById("newmedicine"+val1).value;
				if(val!=val1 && Number(globalProductId)==Number(localProductId)){
					if(localProductId!=0){
						var localStock = document.getElementById("consumeQty"+val1).value;
						totalStock = totalStock + Number(localStock);
						alreadySelectFlag = true;
					}
				}
			}
		});
		if(alreadySelectFlag){
			if(confirm("Do you use same product for multiple vaccination")){
				document.getElementById("consumeQty"+val).value = 0;
			}else{
				document.getElementById("consumeQty"+val).value = 1;
			}
		}else{
			if(globalProductId!=0){
				document.getElementById("consumeQty"+val).value = 1;
			}
		}
		
	}
}

function consumeMultipleVaccination() {
	var errorFlag = false;
	var ids="0";
	$('.multiclass').each(function() { 
		if(this.checked==true){
			var val = this.value;
			ids = ids+","+val;
			var newmid=document.getElementById("newmedicine"+val).value;
			if(newmid=='0'){
				errorFlag = true;
				jAlert('error', "Please select product!", 'Error Dialog');
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 
			}
		}
	});
	
	if(ids=='0'){
		jAlert('error', "Please select at least one vaccine!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else if(errorFlag){
		jAlert('error', "Please select product!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		document.getElementById("multipleVaccineIds").value=ids;
		document.getElementById("multiVaccineForm").submit();
	}
}

function openMultiAddChargePopup(clientId){
	 var dataObj= {
		"clientId":""+clientId+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({
	  url : "openmultivaccinechargeBookAppointmentAjax",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  document.getElementById('multiaddtbody').innerHTML=data.datax;
		  document.getElementById('multiadd_clientid').value=data.clientid;
		  document.getElementById('multiadd_clientname').innerHTML=data.clientname;
		  document.getElementById('multiadd_abrivation').innerHTML=data.abrivationid;
		  $('#multiaddmodel').modal( "show" ); 
		  
		  document.getElementById("multaddallcheck").checked = true;
		  selectAllAddVaccination(true);
	    },
	    error : function(result) {
	    	console.log("error");
	    }
	 });
}

function selectAllAddVaccination(val){
	if (val== true) {
		$('.multiaddclass').each(function() { // loop through each checkbox
			this.checked = true; // deselect all checkboxes with class
									// "checkbox1"
		});
	} else {
		$('.multiaddclass').each(function() { // loop through each checkbox
			this.checked = false; // deselect all checkboxes with class
									// "checkbox1"
		});
	}
}

function consumeAddVaccination() {
	var errorFlag = false;
	var ids="0";
	$('.multiaddclass').each(function() { 
		if(this.checked==true){
			ids = ids+","+this.value;
		}
	});
	
	if(ids=='0'){
		jAlert('error', "Please select at least one vaccine!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration); 
	}else{
		document.getElementById("multipleaddIds").value=ids;
		var clientid = document.getElementById('multiadd_clientid').value;
		$("#multiaddmodel").modal("hide");
		showAddchargePopupConsume(ids,clientid);
	}
}


function checkdoctordiary(docid){
	
	var commencing=document.getElementById("fromDate").value;
    document.getElementById("docid").value=docid;
	
	var url="checkDiaryOpdConsultation?commencing="+commencing+"&docid="+docid+"";

        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = doctordiaryRequest;
		req.open("GET", url, true);
		req.send(null);
	
	
}

function doctordiaryRequest(){

 if (req.readyState == 4) {
	    if (req.status == 200) {
		  var str = req.responseText;
		  
		  if(str==0){
		
						jAlert('error', 'Please set Doctor Diary.', 'Error Dialog');
						setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					document.getElementById("gobtn").disabled=true;
					document.getElementById("setbtn").disabled=false;
					
             }else{
					document.getElementById("gobtn").disabled=false;
					document.getElementById("setbtn").disabled=true;
					
				}
         }	
		    
		  	
	        
				
	}

  }

function setdoctordiary(actiontype){
	
	var commencing=document.getElementById("fromDate").value;
	var docid=document.getElementById("docid").value;
	
	var url="insertDoctordiaryOpdConsultation?commencing="+commencing+"&docid="+docid+"&actiontype="+actiontype+"";

        if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = setdoctorRequest;
		req.open("GET", url, true);
		req.send(null);
	
	
}

function setdoctorRequest(){
	if (req.readyState == 4) {
	    if (req.status == 200) {
		  var str = req.responseText;
		  
		 if (str == '0') {
			 alert("data not added.");                   // error
			} else {
				alert("Added Successfully.");
                 document.getElementById("gobtn").disabled=false;
                 document.getElementById("setbtn").disabled=true;
			}
		}
	}
}

function setopdcount(count){
	
	if(count>20){
		jAlert('error', 'Please Enter Valid Count Less than 20.', 'Error Dialog');
						setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					document.getElementById("gobtn").disabled=true;
	}else{
		document.getElementById("gobtn").disabled=false;
	}
	
	
}


function updateduedate(duedate,dueid,vaccname,clientid,val){
	
		
		var url="updateVaccdatevetFinder?duedate="+duedate+"&mastername="+vaccname+"&clientid="+clientid+"&action="+val+"&dueid="+dueid+" ";

	        if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
	        req.onreadystatechange = updateduedateRequest;
			req.open("GET", url, true);
			req.send(null);
}

function updateduedateRequest(){
	if (req.readyState == 4) {
		    if (req.status == 200) {
				var id = req.responseText;
				/*document.getElementById(id).className = "MyClass";
				document.getElementById(id).style.backgroundColor='';
				document.getElementById(id).style.backgroundColor='green';*/
				window.location.reload();
				}
			}
}


function openMultiConsumePopupdeworm(clientId){
	 var dataObj= {
		"clientId":""+clientId+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({

	  url : "openmulticonsumedewormBookAppointmentAjax",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  document.getElementById('multiconsumetbody').innerHTML=data.datax;
		  document.getElementById('multiconsume_clientid').value=data.clientid;
		  document.getElementById('multiconsume_clientname').innerHTML=data.clientname;
		  document.getElementById('multiconsume_abrivation').innerHTML=data.abrivationid;
		  $('.multiclass').each(function() { 
			  $("#newmedicine"+this.value).trigger("chosen:updated");
			  $(".chosen").chosen({allow_single_deselect: true});
		  });
		  $('#multiconsumemodel').modal( "show" ); 
	    },
	    error : function(result) {
	    	console.log("error");
	    }
	 });
}


function openMultiAddChargePopupVetMed(clientId){
	 var dataObj= {
		"clientId":""+clientId+"", 
	 };
	 var data1 =  JSON.stringify(dataObj);
	 $.ajax({
	  url : "openmultimedicinechargeBookAppointmentAjax",
	  data : data1,
	  dataType : 'json',
	  contentType : 'application/json',
	  type : 'POST',
	  async : true,
	  // beforeSend: function () { LockScreen(); },
	  success : function(data) {
		  document.getElementById('multiaddtbody').innerHTML=data.datax;
		  document.getElementById('multiadd_clientid').value=data.clientid;
		  document.getElementById('multiadd_clientname').innerHTML=data.clientname;
		  document.getElementById('multiadd_abrivation').innerHTML=data.abrivationid;
		  $('#multiaddmodel').modal( "show" ); 
		  
		  document.getElementById("multaddallcheck").checked = true;
		  selectAllAddVaccination(true);
	    },
	    error : function(result) {
	    	console.log("error");
	    }
	 });
}



function createChargeAndUpdateAccountvet(action){
		$("#dashboardloaderPopup").modal('show');
		opdcash = action;
		var clientid = consume_clientid; 
		var practitionerid = "0";
		var clientName = "0";
		var practitionerName = "";
		var appointmentid = "0";
		var tratmentepisodeid = "0";
		var treatmenntsessions = "0";
		var location = "1";
		
		if(location==0){
			jAlert('error', 'Please select location.', 'Error Dialog');
			
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
		}else{
			var ipd = 0;
			var url = "updateAccountvetCompleteApmt?appointmentid="+appointmentid+"&clientid="+clientid+"&practitionerid="+practitionerid+"&clientName="+clientName+"&practitionerName="+practitionerName+"&action="+action+"&tratmentepisodeid="+tratmentepisodeid+"&treatmenntsessions="+treatmenntsessions+"&location="+location+"&ipd="+ipd+"&vaccineId="+consume_vacinationid+"";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = createChargeAndUpdateAccountvetRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		}
	}
	
	function createChargeAndUpdateAccountvetRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				var invoiceid = req.responseText;
				cashdesh_invoiceid = invoiceid;
				updateConsumptionStatusdeworm();
			}
		}
	}
	
	
	
	function updateConsumptionStatusdeworm(){
			var url = "updatevaccinationstatusdewormBookAppointmentAjax?vacinationid="+consume_vacinationid+"";
			   
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = updateConsumptionStatusdewormRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
	}
		
	function updateConsumptionStatusdewormRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				$("#dashboardloaderPopup").modal('show');
				
				if(opdcash=='cash'){
					$('#addchargepopupconsume').modal( "hide" );
					setInstantCashDesk(cashdesh_invoiceid);
				}else{
					$("#dashboardloaderPopup").modal('hide');
					jAlert('success', 'Charge added successfully.', 'Success Dialog');
					setTimeout(function() {
						$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration);
					document.getElementById("vaccinationfrm").submit();
				}
			}
		}
	}

	function consumeAddMedicine() {
		var errorFlag = false;
		var ids="0";
		$('.multiaddclass').each(function() { 
			if(this.checked==true){
				ids = ids+","+this.value;
			}
		});
		
		if(ids=='0'){
			jAlert('error', "Please select at least one vaccine!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration); 
		}else{
			document.getElementById("multipleaddIds").value=ids;
			var clientid = document.getElementById('multiadd_clientid').value;
			$("#multiaddmodel").modal("hide");
			showAddchargePopupConsumeMed(ids,clientid);
		}
	}
	
	
	function showAddchargePopupConsumeMed(id,clientid){
		consume_vacinationid=id;
		consume_clientid =clientid;
		var url = "truncateBookAppointmentAjax";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	    req.onreadystatechange = showAddchargePopupConsumeMedRequest;
	    req.open("GET", url, true); 
	    req.send(null);
	}

	function showAddchargePopupConsumeMedRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				addOutMedoConsumeCharge();
			}
		}
		
	}
	
	
	function addOutMedoConsumeCharge(){
		var url = "setMedchargesofconsumptionBookAppointmentAjax?vaccinationid="+consume_vacinationid+"&clientid="+consume_clientid+" ";
		   
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = addOutMedoConsumeChargeRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	}
	function addOutMedoConsumeChargeRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				setconsumptionCashDesk();
				$('#addchargepopupconsume').modal( "show" );
			}
		}
	}
	
	
	function updateFollowup(clientid,duedate,mastername,action){
		
		var url="insertFollowupFinder?clientId="+clientid+"&mastername="+mastername+"&duedate="+duedate+"&action="+action+"";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
		            
		 req.onreadystatechange = updateFollowupRequest;
		 req.open("GET", url, true); 
		           
		 req.send(null);
	}
	
	function updateFollowupRequest(){
		if (req.readyState == 4) {
					if (req.status == 200) {
						
					}
				}
	}
	