var counter = 0;

function AddFileUpload1()
{
	 //var fil=document.getElementById('fileuplod').value
	 //alert(fil);
     var div = document.createElement('DIV');
     div.id = counter;
     div.className = "col-lg-10 col-md-10 padnil";
     div.innerHTML = '<input id="fileUpload" name = "fileUpload" type="file" class="indopborder" />'; 
     
     var div1 = document.createElement('DIV');
     div1.id = counter;
     div1.className = "col-lg-2 col-md-2";
     
     div1.innerHTML =  '<a href="#" id="Button' + counter + '" ' + 'onclick = "RemoveFileUpload('+counter+')"><i class="fa fa-times text-danger" style="margin-top: -7px;position: absolute;"></i></a>';
                    
     document.getElementById("drop").appendChild(div);
     document.getElementById("drop").appendChild(div1);
     counter++;
     
     
    // alert(isFileSelected(counter));
}

function AddFileUpload()
{
     var div = document.createElement('DIV');
     div.id = counter;
     div.className = "col-lg-8 col-md-8";
     div.innerHTML = '<input id="fileUpload" name = "fileUpload" type="file" class="indopborder" />'; 
     
     var div1 = document.createElement('DIV');
     div1.id = counter;
     div1.className = "col-lg-4 col-md-4";
     
     div1.innerHTML =  '<a href="#" id="Button' + counter + '" ' + 'onclick = "RemoveFileUpload('+counter+')"><i class="fa fa-times text-danger" style="padding-top: 8px;"></i></a>';
                    
     document.getElementById("drop").appendChild(div);
     document.getElementById("drop").appendChild(div1);
     counter++;
     
     
    // alert(isFileSelected(counter));
}
function RemoveFileUpload(div)
{
	 var d = document.getElementById('drop');

	  var olddiv = document.getElementById(div);

	  d.removeChild(olddiv);
	  var d = document.getElementById('drop');

	  var olddiv = document.getElementById(div);

	  d.removeChild(olddiv);

}

function isFileSelected(l){
	 var result = 0;
	for(i=0;i<l;i++){
		var fileName = $("#file"+i).val();
		var photo = document.getElementById("file"+i);
	    var file = photo.files[0];
		   if(fileName) { // returns true if the string is not empty
		       result = 1;
		      alert(file.getAsBinary());
		    } 
	}
	   

	 return result;
}



function sendmailWithAttachment(){
	
	if(isFileSelected(counter)==0){
		sendPdfLetterMail();
	}else{
		document.getElementById('sendmailfrm').submit();
		
	}
}

function setEmailParameterInSession(){
	var to = document.getElementById('gpLetterEmail').value;
	var cc = document.getElementById('gpLetterccEmail').value;
	var subject = document.getElementById('gpLettersubject').value;
	var emailBody = nicEditors.findEditor('emailBodyLetter').getContent();
	emailBody =  emailBody.replace(/&nbsp/gi,'');
	var clientid = document.getElementById('id').value;
	document.getElementById("emailError").innerHTML = "";
	
	$('#sendLetterPopup').block({ message: '<h3><img src="common/images/loader1.GIF" /> Sending Mail, Just a moment...</h3>' }); 
	
	var url = "setInstantMsg?to="+to+"&subject="+subject+"&emailBody="+emailBody+"&cc="+cc+"&globalapptid="+globalapptid+"&clientid="+clientid+"&editAppointId="+editAppointId+" ";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setEmailParameterInSessionRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	    
}

function setEmailParameterInSessionRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			  
			var docfile = document.getElementById('fileUpload').value;
			
		    var iframe = document.createElement("iframe");
		     iframe.setAttribute("id", "upload_iframe");
		    iframe.setAttribute("name", "upload_iframe");
		    iframe.setAttribute("width", "0");
		    iframe.setAttribute("height", "0");
		    iframe.setAttribute("border", "0");
		    iframe.setAttribute("style", "width: 0; height: 0; border: none;"); 
		 
		    // Add to document...
		    form.parentNode.appendChild(iframe);
		    window.frames['upload_iframe'].name = "upload_iframe";
		 
		    iframeId = document.getElementById("upload_iframe");
		   
			
		    // Add event...
		    var eventHandler = function () {
		 
		            if (iframeId.detachEvent) iframeId.detachEvent("onload", eventHandler);
		            else iframeId.removeEventListener("load", eventHandler, false);
		 
		            // Message from server...
		           if (iframeId.contentDocument) {
		              /*  content = iframeId.contentDocument.body.innerHTML;
		                jAlert('success', 'File Uploaded Successfully !!', 'Success Dialog');
		                
		            	setTimeout(function() {
							$("#popup_container").remove();
							$("#popup_overlay").css({
								position: '',
								zIndex: 0,
								top: '0px',
								left: '0px',
								width: '',
								height: '',
								background: '',
								opacity:''
							});
						}, alertmsgduration);*/
		                
		            /*    document.getElementById('savebtn').disabled = '';
		                document.getElementById('uploadbtn').disabled = 'disabled';*/
		            } else if (iframeId.contentWindow) {
		                content = iframeId.contentWindow.document.body.innerHTML;
		               
		            } else if (iframeId.document) {
		                content = iframeId.document.body.innerHTML;
		               
		            }
		 
		          //  document.getElementById(div_id).innerHTML = content;
		 
		            // Del the iframe...
		            setTimeout('iframeId.parentNode.removeChild(iframeId)', 10);
		        }
		 
		    if (iframeId.addEventListener) iframeId.addEventListener("load", eventHandler, true);
		    if (iframeId.attachEvent) iframeId.attachEvent("onload", eventHandler);
		 
		    // Set properties of form...
		    form.setAttribute("target", "upload_iframe");
		    form.setAttribute("action", action_url);
		    form.setAttribute("method", "post");
		    form.setAttribute("enctype", "multipart/form-data");
		    form.setAttribute("encoding", "multipart/form-data");
		    	 // Submit the form...
		        form.submit();
			
		}
	}
}



function fileUpload1(form, action_url, div_id) {
	
	 var email=document.getElementById('gpLetterEmail').value;
	 var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	
	if(!filter.test(email)){		
		var userto = document.createElement("label");
		userto.innerHTML = "Please Enter Email ID";
	    document.getElementById('emailError').appendChild(userto);
	    return false;  
	}
    else
    {
			setEmailParameterInSession();
    }
	
	setTimeout(function() {
		$('#sendLetterPopup').unblock();
		showGrowl('', 'Mail Send Successfully !!', 'success', 'fa fa-check');
		$('#sendLetterPopup').modal( "hide" );
		$('#modifyPopup').modal( "hide" );
		
	}, alertmsgduration);
	
	
 
    //document.getElementById(div_id).innerHTML = "Uploading...";
    //document.getElementById('uploaded').innerHTML = 'Uploaded';
   // document.getElementById('savebtn').disabled = '';
//}
}
 function sendpayrollmail(){
	//alert("hello");
	var email=document.getElementById('gpLetterEmail').value;
	var subject=document.getElementById('gpLettersubject').value;
	var emailBody = nicEditors.findEditor('emailBodyLetter').getContent();
	var url="empmailsendingPayrollEmployee?email="+email+"&subject="+subject+"&emailBody="+emailBody+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = sendpayrollmailRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	    
}

function sendpayrollmailRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			     }
			
			}
}
