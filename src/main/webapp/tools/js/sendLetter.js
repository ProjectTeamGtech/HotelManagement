
var tpId = "";
var companyName = "";
var companyEmail = "";
var tpId = "";
var userNameLetter = '';
var globalapptid = '';

function sendLetter(){
	
	var myString = wraperDivId;
	if(editAppointId==0){
		myString = myString.replace(/[^\d]/g, ''); 
		editAppointId = myString;
		
	}
	
	patientId = pppid;
	editClientName = pppcname;
	editwhopay = pppwhopay;
	
	//alert(globalTpType);
	globalapptid = editAppointId;
	
	
	document.getElementById('userNameletter').value = editClientName;
	document.getElementById('userNameletter1').value = editClientName;
	var ssss=document.getElementById("emaorwhatsup").value;
	userNameLetter = 'Client';
	
/*	$("#userNameletter").trigger("chosen:updated");
	$(".chosen").chosen({allow_single_deselect: true});
	*/
	setNameLetterPopup(userNameLetter);
	
	/*setGPName(patientId);
*/	
	/*document.getElementById('userletter').value = companyName;
	document.getElementById('id').value = tpId;*/
	
	document.getElementById('saveId').style.display = '';
	document.getElementById('pdfFileIdLetter').style.display = 'none';
	document.getElementById('sendmail').style.display = 'none';
	document.getElementById('printId').style.display = 'none';

	
}

function openSendEmailPopup(){
	

	
	//document.getElementById('templateName').disabled = true;
	
	
	
	var url = "showTemplateListInstantMsg?userName="+userNameLetter+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = openSendEmailPopupRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	    
	    return true;
	}
	
	
function openSendEmailPopupRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			//document.getElementById('templateName').value = 'GP Referal Template';
			document.getElementById("templateName").innerHTML = req.responseText;
			
			
			document.getElementById("templateName").value = 23;
    		$("#templateName").trigger("chosen:updated");
			$(".chosen").chosen({allow_single_deselect: true});
			
			//document.getElementById("templateName").disabled = true;
			
			editAppointId = saveAppointmntid;
			document.getElementById("id").value  = savedclient;
			document.getElementById('userNameletter').value = editClientName;
			
			showTemplateDetails(23);
			
			
			
			$('#sendLetterPopup').modal( "show" );
			
			
			
		}
	}
	
}






function setGPName(id){
	
	var url = "getGPNameInstantMsg?patientId="+id+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setGPNameLetterRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	    return true;
	

	}
	function setGPNameLetterRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				var data = req.responseText;
				var temp = data.split("/");
				tpId = temp[0];			
				//alert(clientFullName);		
				companyName= temp[1];
				companyEmail = temp[2];
											
				document.getElementById('userletter').value = companyName;
				document.getElementById('id').value = patientId;
				//document.getElementById('gpLetterEmail').value = companyEmail;
				
				var templateName = 'GP Referal Template';
				//getGPLetterData(templateName,userNameLetter,patientId);
				
				//alert(tpId);
				//if(tpId != 0){						
//				jQuery.noConflict();
                    var emailorwhstup=document.getElementById("emaorwhatsup").value;
					if(emailorwhstup==0){
						$('#sendLetterPopup').modal( "show" );
					}else{
					  $('#whatsupPopup').modal( "show" );
					}
					//}
					//else{
						//alert('hi');
						//jAlert('error', 'Sorry GP Name Not Available !!', 'Error Dialog');
						
					//}
				
	         }
		}
	}
	
	function getGPLetterData(templateName,userNameLetter,id1){
		
		var url = "getTemplateDetailsTPReferal?templateName="+templateName+"&userName="+userNameLetter+"&id1="+id1+" ";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = getGPLetterDataRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null); 
	    
		    }
			
	
	function getGPLetterDataRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				
				var data = req.responseText;
				//var temp = data.split("/");
				//var header = temp[0];			
				//var templatename = temp[4];
				//document.getElementById('pdfFileIdLetter').style.display = 'none';
				
				setTimeout(function(){
					$(".nicEdit-main").html(data);			
				}, 1000);
				
				document.getElementById('pdfFileIdLetter').style.display = 'none';
				document.getElementById('sendmail').style.display = 'none';
				document.getElementById('printId').style.display = 'none';
			}
		}
	}





function setNameLetterPopup(id){
	//alert(id);
	var userNameLetter1 = document.getElementById('userNameletter').value;	
	userNameLetter = '';
	//alert(userNameLetter);
	
	if(userNameLetter == "Select"){
		
		document.getElementById("userletter").value = '';
		document.getElementById("userletter").disabled = 'disabled';
		//document.getElementById("toEmailId").value = '';
		//document.getElementById("templateName").innerHTML = '';
		$("#templateName").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		var data = '';				
		setTimeout(function(){
			$(".nicEdit-main").html(data);			
		}, 1000);
		
		document.getElementById('gpLettersubject').value = '';
		
		return false;
	}
	else{
		
	var url = "showTemplateListInstantMsg?userName="+userNameLetter+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = setNameLetterPopupRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	    return true;
	}

	}
	function setNameLetterPopupRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				//document.getElementById('templateName').value = 'GP Referal Template';
				document.getElementById("templateName").innerHTML = req.responseText;
				
	    		$("#templateName").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				//document.getElementById('pdfFileIdLetter').style.display = 'none';
				var data = '';				
				setTimeout(function(){
					$(".nicEdit-main").html(data);			
				}, 1000);
				
				
					setGPName(patientId);

					
		}
	}
	}
	

function setNameLetter(id){
	//alert(id);
	var userNameLetter1 = document.getElementById('userNameletter').value;	
	userNameLetter = userNameLetter1;
	//alert(userNameLetter);
	
	if(userNameLetter == "Select"){
		
		document.getElementById("userletter").value = '';
		document.getElementById("userletter").disabled = 'disabled';
		//document.getElementById("toEmailId").value = '';
		//document.getElementById("templateName").innerHTML = '';
		$("#templateName").trigger("chosen:updated");
		$(".chosen").chosen({allow_single_deselect: true});
		var data = '';				
		setTimeout(function(){
			$(".nicEdit-main").html(data);			
		}, 1000);
		document.getElementById('gpLettersubject').value = '';
		
		return false;
	}
	else{
		
	var url = "showTemplateListInstantMsg?userName="+userNameLetter+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showNameLetterRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	    return true;
	}

	}
	function showNameLetterRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
			
				
				document.getElementById("templateName").innerHTML = req.responseText;
	    		$("#templateName").trigger("chosen:updated");
				$(".chosen").chosen({allow_single_deselect: true});
				//document.getElementById('pdfFileIdLetter').style.display = 'none';
				var data = '';				
				setTimeout(function(){
					$(".nicEdit-main").html(data);			
				}, 1000);
				
				
				//setGPName(patientId);
				
				document.getElementById('userletter').value = '';
				document.getElementById('pdfFileIdLetter').style.display = 'none';
				document.getElementById('sendmail').style.display = 'none';
				document.getElementById('printId').style.display = 'none';
					
		}
	}
	}
	
function showPopUpLetter(){
	
	document.getElementById("unameError").innerHTML = "";
	var chk = 0;	
	//alert(headerNote);
	
	/*if(userNameLetter == ""){		
		var uname = document.createElement("label");
		uname.innerHTML = "Please select User name";
	    document.getElementById('unameError').appendChild(uname);
	    $('#userSearch').modal('hide');
	    return false;  
	}
    else
    {
	*/
	var url = "showUserListInstantMsg";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = showAllPatientPopUpLetterRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);

	/*    return true;
    }*/
	}
	function showAllPatientPopUpLetterRequest(){
	if (req.readyState == 4) {
			if (req.status == 200) {
				//alert(req.responseText);
			
				document.getElementById("allSendLetters").innerHTML = req.responseText;
				
	         	
				
	         }
		}
	}

	function setUserDetail(id){
		
		document.getElementById("id").value = id;
		//	id1 = id;
			var url = "getPatientDetailsInstantMsg?id="+id+"";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setUserDetailLetterRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);        
				
		}
		
		function setUserDetailLetterRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					var data = req.responseText;
					var temp = data.split("/");
					var clientFullName = temp[0];			
					//alert(clientFullName);		
					var email= temp[1];
					var appointmentid = temp[2];
					globalapptid = appointmentid;
					//alert(globalapptid);
								
					//document.getElementById('user').value = clientFullName;	
					document.getElementById("userletter").value = clientFullName;	
					//document.getElementById("gpLetterEmail").value = email;
								
					$('#userSearch').modal('hide');
					
					var clientid  = document.getElementById("id").value;
					setSelectedClientSessionRecordAjax(clientid);
					
					
				}
			}

		
	}
		
		function showTemplateDetailsLetter(){
			
			var id1 = document.getElementById("id").value;
			var templateId = document.getElementById('templateName');
			 var templateName = templateId.options[templateId.selectedIndex].text;
			 var tempId = document.getElementById('templateName').value;
			 var letterUserName ='';
			 if(document.getElementById('userNameletter')){
				 letterUserName= document.getElementById('userNameletter').value;
			 }
			 
			 //alert(tempId);
			// document.getElementById("userError").innerHTML = "";
			 var user = document.getElementById('userletter').value;

				if(user == ""){		
					var userl = document.createElement("label");
					userl.innerHTML = "Please select User name";
				    document.getElementById('userError').appendChild(userl);
				    return false;  
				}
			    else
			    {
			// alert(id1);
			// userNameLetter = 'Client';
			var url = "getTemplateDetailsPrintTPReferal?templateName="+templateName+"&id1="+id1+"&tempId="+tempId+"&userName="+letterUserName+" ";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showTemplateDetailsLetterRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null); 
		    
		    return true;  
			    }
				
		}
		function showTemplateDetailsLetterRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					var data = req.responseText;
					var temp = data.split("#");
					var template = temp[0];			
					var email = temp[1];
					var subject = temp[2];
					//document.getElementById('pdfFileIdLetter').style.display = 'none';
					
					document.getElementById('gpLetterEmail').value = email;
					document.getElementById('gpLettersubject').value = subject;
					
					setTimeout(function(){
						$(".nicEdit-main").html(template);			
					}, 1000);
					
					document.getElementById('pdfFileIdLetter').style.display = 'none';
					document.getElementById('sendmail').style.display = 'none';
					document.getElementById('printId').style.display = 'none';
				}
			}
		}

		function showTemplateDetails(){
		//	alert(userNameLetter);
			
			var id1 = document.getElementById("id").value;
			var templateId = document.getElementById('templateName');
			 var templateName = templateId.options[templateId.selectedIndex].text;
			 var tempId = document.getElementById('templateName').value;
			 //alert(tempId);
			// document.getElementById("userError").innerHTML = "";
			/* var user = document.getElementById('userletter').value;

				if(user == ""){		
					var userl = document.createElement("label");
					userl.innerHTML = "Please select User name";
				    document.getElementById('userError').appendChild(userl);
				    return false;  
				}
			    else
			    {*/
			// alert(id1);
			 userNameLetter = 'Client';
			var url = "getTemplateDetailsTPReferal?templateName="+templateName+"&userName="+userNameLetter+"&id1="+id1+"&tempId="+tempId+"&aptmtId="+editAppointId+" ";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = setTemplateDetailsRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null); 
		    
		   // return true;  
			   // }
				
		}
		function setTemplateDetailsRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					var data = req.responseText;
					var temp = data.split("#");
					var template = temp[0];			
					var email = temp[1];
					var subject = temp[2];
					//document.getElementById('pdfFileIdLetter').style.display = 'none';
					
					document.getElementById('gpLetterEmail').value = email;
					document.getElementById('gpLettersubject').value = subject;
					
					setTimeout(function(){
						$(".nicEdit-main").html(template);			
					}, 1000);
					
					document.getElementById('pdfFileIdLetter').style.display = 'none';
					document.getElementById('sendmail').style.display = 'none';
					document.getElementById('printId').style.display = 'none';
				}
			}
		}
		
		var user1 = '';
		var id1 = '';
		var templateId = '';
		
		function createPdf(){
			
			  	childWindow = window.open('','childWindow','location=yes, menubar=yes, toolbar=yes');
		        childWindow.document.open();
		        childWindow.document.write('<html><head></head><body>');
		        childWindow.document.write(nicEditors.findEditor('emailBodyLetter').getContent());
		        childWindow.document.write('</body></html>');
		        childWindow.print();
		        childWindow.document.close();
		        childWindow.close();
			
			//$('#sendLetterPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Creating PDF, Just a moment...</h3>' }); 
			
		/*	var user = document.getElementById('userletter').value;
			var id = document.getElementById('id').value;
			id1 = id;
			//alert(id1);
			user1 = user;
			var templateData = nicEditors.findEditor('emailBodyLetter').getContent();
			//alert(templateData);
			//var user = document.getElementById('user');
			templateId = document.getElementById('templateName').value;
			
			var url = "createPDFEmailTemplate?templateData="+templateData+"&user="+user1+"&id="+id1+"&userName="+userNameLetter+"&templateId="+templateId+" ";
			
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = createPdfLetterRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);        
				
		}
		function createPdfLetterRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					//$('#sendLetterPopup').unblock();
					$('#sendLetterPopup').modal('hide');
					
					showGrowl('', 'PDF file is Created Successfully !!', 'success', 'fa fa-check');
					document.getElementById('pdfFileIdLetter').style.display = '';
					document.getElementById('sendmail').style.display = '';
					document.getElementById('printId').style.display = '';
					
					document.getElementById('pdfFileIdLetter').innerHTML = '<h4> PDF File  : <a href = liveData/template/'+userNameLetter+'_'+id1+'_template'+templateId+'.pdf target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a> </h4>';
					showGrowl('', 'PDF file is Created Successfully !!', 'success', 'fa fa-check');
					
					document.getElementById('sendmail').innerHTML = '<button type="button" id="sendMailId" class="btn btn-primary" onclick="return sendEmailLetter()">Send Mail</button>';
						
					document.getElementById('printId').innerHTML = '<h4> <a href = liveData/template/'+userNameLetter+'_'+id1+'_template'+templateId+'.pdf target="blank" class="btn btn-primary" title="Click and press Ctrl+P to print GP Latter"> Print </a>  </h4>';

					
				}
			}*/
		}
		
		function searchTemplatePatientLetter(){
			
			var searchText = document.getElementById("searchTextLetter").value;
			var url = "searchTemplatePatientInstantMsg?searchText="+searchText+" ";

			if (window.XMLHttpRequest) {
					req = new XMLHttpRequest();
				}
				else if (window.ActiveXObject) {
					isIE = true;
					req = new ActiveXObject("Microsoft.XMLHTTP");
				}   
			               
			    req.onreadystatechange = searchTemplatePatientLetterRequest;
			    req.open("GET", url, true); 
			              
			    req.send(null);

			}
	function searchTemplatePatientLetterRequest(){
			if (req.readyState == 4) {
					if (req.status == 200) {
					
						document.getElementById("allSendLetters").innerHTML = req.responseText;
			         	
						
			         }
				}
			}
		
	
	function sendEmailLetter(){
		
		document.getElementById('gpLetterEmail').value = companyEmail;		
		
		document.getElementById('pdfMailId').innerHTML = '<h4> PDF File  : <span style="margin-left:3px;"><a href = liveData/template/'+userNameLetter+'_'+id1+'_template'+templateId+'.pdf target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a></span> &nbsp; <input type="checkbox" name="ltrpdf" id="ltrpdf" checked="checked"> </h4>';
		
		$('#sendEmailLetterPopup').modal( "show" );
		
	}
	
	function sendPdfLetterMail(){
		
		var myString = wraperDivId;
		if(editAppointId==0){
			myString = myString.replace(/[^\d]/g, ''); 
			editAppointId = myString;
			
		}
		
		
		
		var to = document.getElementById('gpLetterEmail').value;
		var cc = document.getElementById('gpLetterccEmail').value;
		var subject = document.getElementById('gpLettersubject').value;
		var emailBody = nicEditors.findEditor('emailBodyLetter').getContent();
		emailBody =  emailBody.replace(/&nbsp/gi,'');
		var clientid = document.getElementById('id').value;
		document.getElementById("emailError").innerHTML = "";
		
		if(to == ""){		
			var userto = document.createElement("label");
			userto.innerHTML = "Please Enter Email ID";
		    document.getElementById('emailError').appendChild(userto);
		    return false;  
		}
	    else
	    {
	    	$('#sendLetterPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
		
		
		//var templateData = nicEditors.findEditor('emailBodyLetter').getContent();
		//var file = document.getElementById('ltrpdf').value;
		
	    	//alert(globalapptid);
		var url = "sendLetterEmailInstantMsg?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&globalapptid="+globalapptid+"&clientid="+clientid+"&editAppointId="+editAppointId+" ";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = sendLetterEmailRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		    
		    return true;  
	    }

		}
	function sendLetterEmailRequest(){
		if (req.readyState == 4) {
				if (req.status == 200) {
					$('#sendLetterPopup').unblock();
					showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
					$('#sendLetterPopup').modal( "hide" );
					$('#modifyPopup').modal( "hide" );
					
		         }
			}
		
	}
	
	
	function sendEmailAsPdfTemplate(){
		
		var to = document.getElementById('gpLetterEmail').value;
		var cc = document.getElementById('gpLetterccEmail').value;
		var subject = document.getElementById('gpLettersubject').value;
		var emailBody = nicEditors.findEditor('emailBodyLetter').getContent();
		emailBody =  emailBody.replace(/&nbsp;/gi,'');
		var clientid = document.getElementById('id').value;
		document.getElementById("emailError").innerHTML = "";
		if(to == ""){		
			var userto = document.createElement("label");
			userto.innerHTML = "Please Enter Email ID";
		    document.getElementById('emailError').appendChild(userto);
		    return false;  
		}else{
			var dataObj= {
					"cc":""+cc+"", 
					"to":""+to+"", 
					"subject":""+subject+"", 
					"notes":""+""+"", 
					"clientid":""+clientid+"", 
					"type":""+"Template Email"+"", 
					"htmlContent":""+emailBody+"",
					"id":"",
			 };
			 var data1 =  JSON.stringify(dataObj);
			 $.ajax({

			  url : "emailCommonnew",
			  data : data1,
			  dataType : 'json',
			  contentType : 'application/json',
			  type : 'POST',
			  async : true,
			  // beforeSend: function () { LockScreen(); },
			  success : function(data) {
				  alert("Sent");
			    },
			  error : function(result) {
			   console.log("error");
			  }
			 });
		}
		 
	}
	
	function createPrintLetterPdf(){
		
		var to = document.getElementById('gpLetterEmail').value;
		var cc = document.getElementById('gpLetterccEmail').value;
		var subject = document.getElementById('gpLettersubject').value;
		var emailBody = nicEditors.findEditor('emailBodyLetter').getContent();
		var clientid = document.getElementById('id').value;
		document.getElementById("emailError").innerHTML = "";
		
		//alert(globalapptid);
		
		if(to == ""){		
			var userto = document.createElement("label");
			userto.innerHTML = "Please Enter Email ID";
		    document.getElementById('emailError').appendChild(userto);
		    return false;  
		}
	    else
	    {
		
	//	$.blockUI({ message: '<h3><img src="common/images/loader1.GIF" /> Just a moment...</h3>' });
		
		var url = "sendLetterEmailInstantMsg?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&globalapptid="+globalapptid+"&clientid="+clientid+"";

		if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = createPrintLetterPdfRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null);
		    
		    return true;  
	    }
	}
	function createPrintLetterPdfRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {		
				//alert('hi');
				//$(document).ajaxStop($.unblockUI);				
				//showGrowl('', 'Mail Sent Successfully !!', 'success', 'fa fa-check');
				
				
				setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);
				
				
				
				jAlert('success', 'Mail Sent Successfully.', 'Success Dialog');
				
			}
		}
	}
		/*	var user = document.getElementById('userletter').value;
		var id = document.getElementById('id').value;
		id1 = id;
		//alert(id1);
		user1 = user;
		var templateData = nicEditors.findEditor('emailBodyLetter').getContent();
		//alert(templateData);
		//var user = document.getElementById('user');
		templateId = document.getElementById('templateName').value;
		
		var url = "createPDFEmailTemplate?templateData="+templateData+"&user="+user1+"&id="+id1+"&userName="+userNameLetter+"&templateId="+templateId+" ";
		
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = createPrintLetterPdfRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);        
			
	}
	function createPrintLetterPdfRequest(){
		if (req.readyState == 4) {
			if (req.status == 200) {
				$('#sendLetterPopup').unblock();
				$('#sendLetterPopup').modal('hide');
				$(document).ajaxStop($.unblockUI);
				showGrowl('', 'PDF file is Created Successfully !!', 'success', 'fa fa-check');
				document.getElementById('pdfFileIdLetter').style.display = '';
				document.getElementById('sendmail').style.display = '';
				document.getElementById('printId').style.display = '';
				
				document.getElementById('pdfFileIdLetter').innerHTML = '<h4> PDF File  : <a href = liveData/template/'+userNameLetter+'_'+id1+'_template'+templateId+'.pdf target=blank> <i class="fa fa-file-pdf-o fa-2x text-danger"></i></a> </h4>';
				showGrowl('', 'PDF file is Created Successfully !!', 'success', 'fa fa-check');
				
				document.getElementById('sendmail').innerHTML = '<button type="button" id="sendMailId" class="btn btn-primary" onclick="return sendEmailLetter()">Send Mail</button>';
					
				document.getElementById('printId').innerHTML = '<a href = liveData/template/'+userNameLetter+'_'+id1+'_template'+templateId+'.pdf target="blank" class="btn btn-primary" title="Click and press Ctrl+P to print GP Latter"> Print </a> ';

				
			}
		}
	}*/
	
	
	
	function showpayrollTemplateDetails(id){
		//alert(id);
			
			
			var templateId = document.getElementById('templateName');
			 var templateName = templateId.options[templateId.selectedIndex].text;
			 var tempId = document.getElementById('templateName').value;
		
			var url = "sendmailtoempPayrollEmployee?templateName="+templateName+"&tempId="+tempId+" ";
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			}
			else if (window.ActiveXObject) {
				isIE = true;
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}   
		               
		    req.onreadystatechange = showpayrollTemplateDetailsRequest;
		    req.open("GET", url, true); 
		              
		    req.send(null); 
		    
		   // return true;  
			   // }
				
		}
		function showpayrollTemplateDetailsRequest(){
			if (req.readyState == 4) {
				if (req.status == 200) {
					
					var data = req.responseText;
					var temp = data.split("#");
					var template = temp[0];			
					var subject = temp[1];
					//document.getElementById('pdfFileIdLetter').style.display = 'none';
					
					/*document.getElementById('gpLetterEmail').value = email;*/
					document.getElementById('gpLettersubject').value = subject;
					
					setTimeout(function(){
						$(".nicEdit-main").html(template);			
					}, 1000);
					
					document.getElementById('pdfFileIdLetter').style.display = 'none';
					document.getElementById('sendmail').style.display = 'none';
					document.getElementById('printId').style.display = 'none';
				}
			}
		}
		
	
	
	
	